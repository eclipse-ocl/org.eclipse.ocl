package org.eclipse.ocl.xtext.idioms.parser.antlr.internal;

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import org.eclipse.ocl.xtext.idioms.services.IdiomsGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
/*******************************************************************************
 * Copyright (c) 2011, 2025 Willink Transformations and others.
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
public class InternalIdiomsParser extends org.eclipse.ocl.xtext.base.utilities.CompatibilityAbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_STRING", "RULE_INT", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'model'", "'.'", "'import'", "'as'", "';'", "'grammar'", "'with'", "'locator'", "'any-assignment'", "'any-element'", "'assignment'", "'::'", "'final'", "'returns'", "'rule'", "'segment'", "'custom'", "'half-new-line'", "'new-line'", "'no-new-line'", "'no-space'", "'pop'", "'post-comment'", "'pre-comment'", "'push'", "'soft-new-line'", "'soft-space'", "'string'", "'printable'", "'value'", "'wrap-anchor'", "'wrap-begin-all'", "'wrap-begin-some'", "'wrap-end'", "'wrap-here'", "'mixin'", "'idiom'", "'for'", "'in'", "'{'", "'}'", "'at'", "'all'", "'each'", "'do'"
    };
    public static final int T__50=50;
    public static final int T__19=19;
    public static final int T__15=15;
    public static final int T__16=16;
    public static final int T__17=17;
    public static final int T__18=18;
    public static final int T__11=11;
    public static final int T__55=55;
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



    /*
      This grammar contains a lot of empty actions to work around a bug in ANTLR.
      Otherwise the ANTLR tool will create synpreds that cannot be compiled in some rare cases.
    */

     	private IdiomsGrammarAccess grammarAccess;

        public InternalIdiomsParser(TokenStream input, IdiomsGrammarAccess grammarAccess) {
            this(input);
            this.grammarAccess = grammarAccess;
            registerRules(grammarAccess.getGrammar());
        }

        @Override
        protected String getFirstRuleName() {
        	return "IdiomsModel";
       	}

       	@Override
       	protected IdiomsGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}




    // $ANTLR start "entryRuleIdiomsModel"
    // InternalIdioms.g:79:1: entryRuleIdiomsModel returns [EObject current=null] : iv_ruleIdiomsModel= ruleIdiomsModel EOF ;
    public final EObject entryRuleIdiomsModel() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIdiomsModel = null;


        try {
            // InternalIdioms.g:79:52: (iv_ruleIdiomsModel= ruleIdiomsModel EOF )
            // InternalIdioms.g:80:2: iv_ruleIdiomsModel= ruleIdiomsModel EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getIdiomsModelRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleIdiomsModel=ruleIdiomsModel();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleIdiomsModel;
            }
            match(input,EOF,FollowSets000.FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleIdiomsModel"


    // $ANTLR start "ruleIdiomsModel"
    // InternalIdioms.g:86:1: ruleIdiomsModel returns [EObject current=null] : (otherlv_0= 'model' ( (lv_names_1_0= RULE_ID ) ) (otherlv_2= '.' ( (lv_names_3_0= RULE_ID ) ) )* ( ( (lv_ownedWiths_4_0= ruleIdiomsImport ) ) | ( (lv_ownedImportDeclarations_5_0= ruleEPackageDeclaration ) ) | ( (lv_ownedGrammarDeclarations_6_0= ruleGrammarDeclaration ) ) )* ( ( (lv_ownedLocatorDeclarations_7_0= ruleLocatorDeclaration ) ) | ( (lv_ownedSegmentDeclarations_8_0= ruleSegmentDeclaration ) ) | ( (lv_ownedIdioms_9_0= ruleIdiom ) ) )* ) ;
    public final EObject ruleIdiomsModel() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_names_1_0=null;
        Token otherlv_2=null;
        Token lv_names_3_0=null;
        EObject lv_ownedWiths_4_0 = null;

        EObject lv_ownedImportDeclarations_5_0 = null;

        EObject lv_ownedGrammarDeclarations_6_0 = null;

        EObject lv_ownedLocatorDeclarations_7_0 = null;

        EObject lv_ownedSegmentDeclarations_8_0 = null;

        EObject lv_ownedIdioms_9_0 = null;



        	enterRule();

        try {
            // InternalIdioms.g:92:2: ( (otherlv_0= 'model' ( (lv_names_1_0= RULE_ID ) ) (otherlv_2= '.' ( (lv_names_3_0= RULE_ID ) ) )* ( ( (lv_ownedWiths_4_0= ruleIdiomsImport ) ) | ( (lv_ownedImportDeclarations_5_0= ruleEPackageDeclaration ) ) | ( (lv_ownedGrammarDeclarations_6_0= ruleGrammarDeclaration ) ) )* ( ( (lv_ownedLocatorDeclarations_7_0= ruleLocatorDeclaration ) ) | ( (lv_ownedSegmentDeclarations_8_0= ruleSegmentDeclaration ) ) | ( (lv_ownedIdioms_9_0= ruleIdiom ) ) )* ) )
            // InternalIdioms.g:93:2: (otherlv_0= 'model' ( (lv_names_1_0= RULE_ID ) ) (otherlv_2= '.' ( (lv_names_3_0= RULE_ID ) ) )* ( ( (lv_ownedWiths_4_0= ruleIdiomsImport ) ) | ( (lv_ownedImportDeclarations_5_0= ruleEPackageDeclaration ) ) | ( (lv_ownedGrammarDeclarations_6_0= ruleGrammarDeclaration ) ) )* ( ( (lv_ownedLocatorDeclarations_7_0= ruleLocatorDeclaration ) ) | ( (lv_ownedSegmentDeclarations_8_0= ruleSegmentDeclaration ) ) | ( (lv_ownedIdioms_9_0= ruleIdiom ) ) )* )
            {
            // InternalIdioms.g:93:2: (otherlv_0= 'model' ( (lv_names_1_0= RULE_ID ) ) (otherlv_2= '.' ( (lv_names_3_0= RULE_ID ) ) )* ( ( (lv_ownedWiths_4_0= ruleIdiomsImport ) ) | ( (lv_ownedImportDeclarations_5_0= ruleEPackageDeclaration ) ) | ( (lv_ownedGrammarDeclarations_6_0= ruleGrammarDeclaration ) ) )* ( ( (lv_ownedLocatorDeclarations_7_0= ruleLocatorDeclaration ) ) | ( (lv_ownedSegmentDeclarations_8_0= ruleSegmentDeclaration ) ) | ( (lv_ownedIdioms_9_0= ruleIdiom ) ) )* )
            // InternalIdioms.g:94:3: otherlv_0= 'model' ( (lv_names_1_0= RULE_ID ) ) (otherlv_2= '.' ( (lv_names_3_0= RULE_ID ) ) )* ( ( (lv_ownedWiths_4_0= ruleIdiomsImport ) ) | ( (lv_ownedImportDeclarations_5_0= ruleEPackageDeclaration ) ) | ( (lv_ownedGrammarDeclarations_6_0= ruleGrammarDeclaration ) ) )* ( ( (lv_ownedLocatorDeclarations_7_0= ruleLocatorDeclaration ) ) | ( (lv_ownedSegmentDeclarations_8_0= ruleSegmentDeclaration ) ) | ( (lv_ownedIdioms_9_0= ruleIdiom ) ) )*
            {
            otherlv_0=(Token)match(input,11,FollowSets000.FOLLOW_3); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getIdiomsModelAccess().getModelKeyword_0());

            }
            // InternalIdioms.g:98:3: ( (lv_names_1_0= RULE_ID ) )
            // InternalIdioms.g:99:4: (lv_names_1_0= RULE_ID )
            {
            // InternalIdioms.g:99:4: (lv_names_1_0= RULE_ID )
            // InternalIdioms.g:100:5: lv_names_1_0= RULE_ID
            {
            lv_names_1_0=(Token)match(input,RULE_ID,FollowSets000.FOLLOW_4); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					newLeafNode(lv_names_1_0, grammarAccess.getIdiomsModelAccess().getNamesIDTerminalRuleCall_1_0());

            }
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElement(grammarAccess.getIdiomsModelRule());
              					}
              					addWithLastConsumed(
              						current,
              						"names",
              						lv_names_1_0,
              						"org.eclipse.ocl.xtext.idioms.Idioms.ID");

            }

            }


            }

            // InternalIdioms.g:116:3: (otherlv_2= '.' ( (lv_names_3_0= RULE_ID ) ) )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==12) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // InternalIdioms.g:117:4: otherlv_2= '.' ( (lv_names_3_0= RULE_ID ) )
            	    {
            	    otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_3); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      				newLeafNode(otherlv_2, grammarAccess.getIdiomsModelAccess().getFullStopKeyword_2_0());

            	    }
            	    // InternalIdioms.g:121:4: ( (lv_names_3_0= RULE_ID ) )
            	    // InternalIdioms.g:122:5: (lv_names_3_0= RULE_ID )
            	    {
            	    // InternalIdioms.g:122:5: (lv_names_3_0= RULE_ID )
            	    // InternalIdioms.g:123:6: lv_names_3_0= RULE_ID
            	    {
            	    lv_names_3_0=(Token)match(input,RULE_ID,FollowSets000.FOLLOW_4); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      						newLeafNode(lv_names_3_0, grammarAccess.getIdiomsModelAccess().getNamesIDTerminalRuleCall_2_1_0());

            	    }
            	    if ( state.backtracking==0 ) {

            	      						if (current==null) {
            	      							current = createModelElement(grammarAccess.getIdiomsModelRule());
            	      						}
            	      						addWithLastConsumed(
            	      							current,
            	      							"names",
            	      							lv_names_3_0,
            	      							"org.eclipse.ocl.xtext.idioms.Idioms.ID");

            	    }

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);

            // InternalIdioms.g:140:3: ( ( (lv_ownedWiths_4_0= ruleIdiomsImport ) ) | ( (lv_ownedImportDeclarations_5_0= ruleEPackageDeclaration ) ) | ( (lv_ownedGrammarDeclarations_6_0= ruleGrammarDeclaration ) ) )*
            loop2:
            do {
                int alt2=4;
                switch ( input.LA(1) ) {
                case 17:
                    {
                    alt2=1;
                    }
                    break;
                case 13:
                    {
                    alt2=2;
                    }
                    break;
                case 16:
                    {
                    alt2=3;
                    }
                    break;

                }

                switch (alt2) {
            	case 1 :
            	    // InternalIdioms.g:141:4: ( (lv_ownedWiths_4_0= ruleIdiomsImport ) )
            	    {
            	    // InternalIdioms.g:141:4: ( (lv_ownedWiths_4_0= ruleIdiomsImport ) )
            	    // InternalIdioms.g:142:5: (lv_ownedWiths_4_0= ruleIdiomsImport )
            	    {
            	    // InternalIdioms.g:142:5: (lv_ownedWiths_4_0= ruleIdiomsImport )
            	    // InternalIdioms.g:143:6: lv_ownedWiths_4_0= ruleIdiomsImport
            	    {
            	    if ( state.backtracking==0 ) {

            	      						newCompositeNode(grammarAccess.getIdiomsModelAccess().getOwnedWithsIdiomsImportParserRuleCall_3_0_0());

            	    }
            	    pushFollow(FollowSets000.FOLLOW_5);
            	    lv_ownedWiths_4_0=ruleIdiomsImport();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      						if (current==null) {
            	      							current = createModelElementForParent(grammarAccess.getIdiomsModelRule());
            	      						}
            	      						add(
            	      							current,
            	      							"ownedWiths",
            	      							lv_ownedWiths_4_0,
            	      							"org.eclipse.ocl.xtext.idioms.Idioms.IdiomsImport");
            	      						afterParserOrEnumRuleCall();

            	    }

            	    }


            	    }


            	    }
            	    break;
            	case 2 :
            	    // InternalIdioms.g:161:4: ( (lv_ownedImportDeclarations_5_0= ruleEPackageDeclaration ) )
            	    {
            	    // InternalIdioms.g:161:4: ( (lv_ownedImportDeclarations_5_0= ruleEPackageDeclaration ) )
            	    // InternalIdioms.g:162:5: (lv_ownedImportDeclarations_5_0= ruleEPackageDeclaration )
            	    {
            	    // InternalIdioms.g:162:5: (lv_ownedImportDeclarations_5_0= ruleEPackageDeclaration )
            	    // InternalIdioms.g:163:6: lv_ownedImportDeclarations_5_0= ruleEPackageDeclaration
            	    {
            	    if ( state.backtracking==0 ) {

            	      						newCompositeNode(grammarAccess.getIdiomsModelAccess().getOwnedImportDeclarationsEPackageDeclarationParserRuleCall_3_1_0());

            	    }
            	    pushFollow(FollowSets000.FOLLOW_5);
            	    lv_ownedImportDeclarations_5_0=ruleEPackageDeclaration();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      						if (current==null) {
            	      							current = createModelElementForParent(grammarAccess.getIdiomsModelRule());
            	      						}
            	      						add(
            	      							current,
            	      							"ownedImportDeclarations",
            	      							lv_ownedImportDeclarations_5_0,
            	      							"org.eclipse.ocl.xtext.idioms.Idioms.EPackageDeclaration");
            	      						afterParserOrEnumRuleCall();

            	    }

            	    }


            	    }


            	    }
            	    break;
            	case 3 :
            	    // InternalIdioms.g:181:4: ( (lv_ownedGrammarDeclarations_6_0= ruleGrammarDeclaration ) )
            	    {
            	    // InternalIdioms.g:181:4: ( (lv_ownedGrammarDeclarations_6_0= ruleGrammarDeclaration ) )
            	    // InternalIdioms.g:182:5: (lv_ownedGrammarDeclarations_6_0= ruleGrammarDeclaration )
            	    {
            	    // InternalIdioms.g:182:5: (lv_ownedGrammarDeclarations_6_0= ruleGrammarDeclaration )
            	    // InternalIdioms.g:183:6: lv_ownedGrammarDeclarations_6_0= ruleGrammarDeclaration
            	    {
            	    if ( state.backtracking==0 ) {

            	      						newCompositeNode(grammarAccess.getIdiomsModelAccess().getOwnedGrammarDeclarationsGrammarDeclarationParserRuleCall_3_2_0());

            	    }
            	    pushFollow(FollowSets000.FOLLOW_5);
            	    lv_ownedGrammarDeclarations_6_0=ruleGrammarDeclaration();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      						if (current==null) {
            	      							current = createModelElementForParent(grammarAccess.getIdiomsModelRule());
            	      						}
            	      						add(
            	      							current,
            	      							"ownedGrammarDeclarations",
            	      							lv_ownedGrammarDeclarations_6_0,
            	      							"org.eclipse.ocl.xtext.idioms.Idioms.GrammarDeclaration");
            	      						afterParserOrEnumRuleCall();

            	    }

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);

            // InternalIdioms.g:201:3: ( ( (lv_ownedLocatorDeclarations_7_0= ruleLocatorDeclaration ) ) | ( (lv_ownedSegmentDeclarations_8_0= ruleSegmentDeclaration ) ) | ( (lv_ownedIdioms_9_0= ruleIdiom ) ) )*
            loop3:
            do {
                int alt3=4;
                switch ( input.LA(1) ) {
                case 18:
                    {
                    alt3=1;
                    }
                    break;
                case 26:
                    {
                    alt3=2;
                    }
                    break;
                case 46:
                case 47:
                    {
                    alt3=3;
                    }
                    break;

                }

                switch (alt3) {
            	case 1 :
            	    // InternalIdioms.g:202:4: ( (lv_ownedLocatorDeclarations_7_0= ruleLocatorDeclaration ) )
            	    {
            	    // InternalIdioms.g:202:4: ( (lv_ownedLocatorDeclarations_7_0= ruleLocatorDeclaration ) )
            	    // InternalIdioms.g:203:5: (lv_ownedLocatorDeclarations_7_0= ruleLocatorDeclaration )
            	    {
            	    // InternalIdioms.g:203:5: (lv_ownedLocatorDeclarations_7_0= ruleLocatorDeclaration )
            	    // InternalIdioms.g:204:6: lv_ownedLocatorDeclarations_7_0= ruleLocatorDeclaration
            	    {
            	    if ( state.backtracking==0 ) {

            	      						newCompositeNode(grammarAccess.getIdiomsModelAccess().getOwnedLocatorDeclarationsLocatorDeclarationParserRuleCall_4_0_0());

            	    }
            	    pushFollow(FollowSets000.FOLLOW_6);
            	    lv_ownedLocatorDeclarations_7_0=ruleLocatorDeclaration();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      						if (current==null) {
            	      							current = createModelElementForParent(grammarAccess.getIdiomsModelRule());
            	      						}
            	      						add(
            	      							current,
            	      							"ownedLocatorDeclarations",
            	      							lv_ownedLocatorDeclarations_7_0,
            	      							"org.eclipse.ocl.xtext.idioms.Idioms.LocatorDeclaration");
            	      						afterParserOrEnumRuleCall();

            	    }

            	    }


            	    }


            	    }
            	    break;
            	case 2 :
            	    // InternalIdioms.g:222:4: ( (lv_ownedSegmentDeclarations_8_0= ruleSegmentDeclaration ) )
            	    {
            	    // InternalIdioms.g:222:4: ( (lv_ownedSegmentDeclarations_8_0= ruleSegmentDeclaration ) )
            	    // InternalIdioms.g:223:5: (lv_ownedSegmentDeclarations_8_0= ruleSegmentDeclaration )
            	    {
            	    // InternalIdioms.g:223:5: (lv_ownedSegmentDeclarations_8_0= ruleSegmentDeclaration )
            	    // InternalIdioms.g:224:6: lv_ownedSegmentDeclarations_8_0= ruleSegmentDeclaration
            	    {
            	    if ( state.backtracking==0 ) {

            	      						newCompositeNode(grammarAccess.getIdiomsModelAccess().getOwnedSegmentDeclarationsSegmentDeclarationParserRuleCall_4_1_0());

            	    }
            	    pushFollow(FollowSets000.FOLLOW_6);
            	    lv_ownedSegmentDeclarations_8_0=ruleSegmentDeclaration();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      						if (current==null) {
            	      							current = createModelElementForParent(grammarAccess.getIdiomsModelRule());
            	      						}
            	      						add(
            	      							current,
            	      							"ownedSegmentDeclarations",
            	      							lv_ownedSegmentDeclarations_8_0,
            	      							"org.eclipse.ocl.xtext.idioms.Idioms.SegmentDeclaration");
            	      						afterParserOrEnumRuleCall();

            	    }

            	    }


            	    }


            	    }
            	    break;
            	case 3 :
            	    // InternalIdioms.g:242:4: ( (lv_ownedIdioms_9_0= ruleIdiom ) )
            	    {
            	    // InternalIdioms.g:242:4: ( (lv_ownedIdioms_9_0= ruleIdiom ) )
            	    // InternalIdioms.g:243:5: (lv_ownedIdioms_9_0= ruleIdiom )
            	    {
            	    // InternalIdioms.g:243:5: (lv_ownedIdioms_9_0= ruleIdiom )
            	    // InternalIdioms.g:244:6: lv_ownedIdioms_9_0= ruleIdiom
            	    {
            	    if ( state.backtracking==0 ) {

            	      						newCompositeNode(grammarAccess.getIdiomsModelAccess().getOwnedIdiomsIdiomParserRuleCall_4_2_0());

            	    }
            	    pushFollow(FollowSets000.FOLLOW_6);
            	    lv_ownedIdioms_9_0=ruleIdiom();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      						if (current==null) {
            	      							current = createModelElementForParent(grammarAccess.getIdiomsModelRule());
            	      						}
            	      						add(
            	      							current,
            	      							"ownedIdioms",
            	      							lv_ownedIdioms_9_0,
            	      							"org.eclipse.ocl.xtext.idioms.Idioms.Idiom");
            	      						afterParserOrEnumRuleCall();

            	    }

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleIdiomsModel"


    // $ANTLR start "entryRuleEPackageDeclaration"
    // InternalIdioms.g:266:1: entryRuleEPackageDeclaration returns [EObject current=null] : iv_ruleEPackageDeclaration= ruleEPackageDeclaration EOF ;
    public final EObject entryRuleEPackageDeclaration() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEPackageDeclaration = null;


        try {
            // InternalIdioms.g:266:60: (iv_ruleEPackageDeclaration= ruleEPackageDeclaration EOF )
            // InternalIdioms.g:267:2: iv_ruleEPackageDeclaration= ruleEPackageDeclaration EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getEPackageDeclarationRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleEPackageDeclaration=ruleEPackageDeclaration();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleEPackageDeclaration;
            }
            match(input,EOF,FollowSets000.FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleEPackageDeclaration"


    // $ANTLR start "ruleEPackageDeclaration"
    // InternalIdioms.g:273:1: ruleEPackageDeclaration returns [EObject current=null] : (otherlv_0= 'import' ( (otherlv_1= RULE_STRING ) ) (otherlv_2= 'as' ( (lv_as_3_0= RULE_ID ) ) )? (otherlv_4= ';' )? ) ;
    public final EObject ruleEPackageDeclaration() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;
        Token lv_as_3_0=null;
        Token otherlv_4=null;


        	enterRule();

        try {
            // InternalIdioms.g:279:2: ( (otherlv_0= 'import' ( (otherlv_1= RULE_STRING ) ) (otherlv_2= 'as' ( (lv_as_3_0= RULE_ID ) ) )? (otherlv_4= ';' )? ) )
            // InternalIdioms.g:280:2: (otherlv_0= 'import' ( (otherlv_1= RULE_STRING ) ) (otherlv_2= 'as' ( (lv_as_3_0= RULE_ID ) ) )? (otherlv_4= ';' )? )
            {
            // InternalIdioms.g:280:2: (otherlv_0= 'import' ( (otherlv_1= RULE_STRING ) ) (otherlv_2= 'as' ( (lv_as_3_0= RULE_ID ) ) )? (otherlv_4= ';' )? )
            // InternalIdioms.g:281:3: otherlv_0= 'import' ( (otherlv_1= RULE_STRING ) ) (otherlv_2= 'as' ( (lv_as_3_0= RULE_ID ) ) )? (otherlv_4= ';' )?
            {
            otherlv_0=(Token)match(input,13,FollowSets000.FOLLOW_7); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getEPackageDeclarationAccess().getImportKeyword_0());

            }
            // InternalIdioms.g:285:3: ( (otherlv_1= RULE_STRING ) )
            // InternalIdioms.g:286:4: (otherlv_1= RULE_STRING )
            {
            // InternalIdioms.g:286:4: (otherlv_1= RULE_STRING )
            // InternalIdioms.g:287:5: otherlv_1= RULE_STRING
            {
            if ( state.backtracking==0 ) {

              					/* */

            }
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElement(grammarAccess.getEPackageDeclarationRule());
              					}

            }
            otherlv_1=(Token)match(input,RULE_STRING,FollowSets000.FOLLOW_8); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					newLeafNode(otherlv_1, grammarAccess.getEPackageDeclarationAccess().getEPackageEPackageCrossReference_1_0());

            }

            }


            }

            // InternalIdioms.g:301:3: (otherlv_2= 'as' ( (lv_as_3_0= RULE_ID ) ) )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==14) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // InternalIdioms.g:302:4: otherlv_2= 'as' ( (lv_as_3_0= RULE_ID ) )
                    {
                    otherlv_2=(Token)match(input,14,FollowSets000.FOLLOW_3); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_2, grammarAccess.getEPackageDeclarationAccess().getAsKeyword_2_0());

                    }
                    // InternalIdioms.g:306:4: ( (lv_as_3_0= RULE_ID ) )
                    // InternalIdioms.g:307:5: (lv_as_3_0= RULE_ID )
                    {
                    // InternalIdioms.g:307:5: (lv_as_3_0= RULE_ID )
                    // InternalIdioms.g:308:6: lv_as_3_0= RULE_ID
                    {
                    lv_as_3_0=(Token)match(input,RULE_ID,FollowSets000.FOLLOW_9); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						newLeafNode(lv_as_3_0, grammarAccess.getEPackageDeclarationAccess().getAsIDTerminalRuleCall_2_1_0());

                    }
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElement(grammarAccess.getEPackageDeclarationRule());
                      						}
                      						setWithLastConsumed(
                      							current,
                      							"as",
                      							lv_as_3_0,
                      							"org.eclipse.ocl.xtext.idioms.Idioms.ID");

                    }

                    }


                    }


                    }
                    break;

            }

            // InternalIdioms.g:325:3: (otherlv_4= ';' )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==15) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // InternalIdioms.g:326:4: otherlv_4= ';'
                    {
                    otherlv_4=(Token)match(input,15,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_4, grammarAccess.getEPackageDeclarationAccess().getSemicolonKeyword_3());

                    }

                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleEPackageDeclaration"


    // $ANTLR start "entryRuleGrammarDeclaration"
    // InternalIdioms.g:335:1: entryRuleGrammarDeclaration returns [EObject current=null] : iv_ruleGrammarDeclaration= ruleGrammarDeclaration EOF ;
    public final EObject entryRuleGrammarDeclaration() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleGrammarDeclaration = null;


        try {
            // InternalIdioms.g:335:59: (iv_ruleGrammarDeclaration= ruleGrammarDeclaration EOF )
            // InternalIdioms.g:336:2: iv_ruleGrammarDeclaration= ruleGrammarDeclaration EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getGrammarDeclarationRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleGrammarDeclaration=ruleGrammarDeclaration();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleGrammarDeclaration;
            }
            match(input,EOF,FollowSets000.FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleGrammarDeclaration"


    // $ANTLR start "ruleGrammarDeclaration"
    // InternalIdioms.g:342:1: ruleGrammarDeclaration returns [EObject current=null] : (otherlv_0= 'grammar' ( (otherlv_1= RULE_STRING ) ) (otherlv_2= 'as' ( (lv_as_3_0= RULE_ID ) ) )? (otherlv_4= ';' )? ) ;
    public final EObject ruleGrammarDeclaration() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;
        Token lv_as_3_0=null;
        Token otherlv_4=null;


        	enterRule();

        try {
            // InternalIdioms.g:348:2: ( (otherlv_0= 'grammar' ( (otherlv_1= RULE_STRING ) ) (otherlv_2= 'as' ( (lv_as_3_0= RULE_ID ) ) )? (otherlv_4= ';' )? ) )
            // InternalIdioms.g:349:2: (otherlv_0= 'grammar' ( (otherlv_1= RULE_STRING ) ) (otherlv_2= 'as' ( (lv_as_3_0= RULE_ID ) ) )? (otherlv_4= ';' )? )
            {
            // InternalIdioms.g:349:2: (otherlv_0= 'grammar' ( (otherlv_1= RULE_STRING ) ) (otherlv_2= 'as' ( (lv_as_3_0= RULE_ID ) ) )? (otherlv_4= ';' )? )
            // InternalIdioms.g:350:3: otherlv_0= 'grammar' ( (otherlv_1= RULE_STRING ) ) (otherlv_2= 'as' ( (lv_as_3_0= RULE_ID ) ) )? (otherlv_4= ';' )?
            {
            otherlv_0=(Token)match(input,16,FollowSets000.FOLLOW_7); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getGrammarDeclarationAccess().getGrammarKeyword_0());

            }
            // InternalIdioms.g:354:3: ( (otherlv_1= RULE_STRING ) )
            // InternalIdioms.g:355:4: (otherlv_1= RULE_STRING )
            {
            // InternalIdioms.g:355:4: (otherlv_1= RULE_STRING )
            // InternalIdioms.g:356:5: otherlv_1= RULE_STRING
            {
            if ( state.backtracking==0 ) {

              					/* */

            }
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElement(grammarAccess.getGrammarDeclarationRule());
              					}

            }
            otherlv_1=(Token)match(input,RULE_STRING,FollowSets000.FOLLOW_8); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					newLeafNode(otherlv_1, grammarAccess.getGrammarDeclarationAccess().getGrammarGrammarCrossReference_1_0());

            }

            }


            }

            // InternalIdioms.g:370:3: (otherlv_2= 'as' ( (lv_as_3_0= RULE_ID ) ) )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==14) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // InternalIdioms.g:371:4: otherlv_2= 'as' ( (lv_as_3_0= RULE_ID ) )
                    {
                    otherlv_2=(Token)match(input,14,FollowSets000.FOLLOW_3); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_2, grammarAccess.getGrammarDeclarationAccess().getAsKeyword_2_0());

                    }
                    // InternalIdioms.g:375:4: ( (lv_as_3_0= RULE_ID ) )
                    // InternalIdioms.g:376:5: (lv_as_3_0= RULE_ID )
                    {
                    // InternalIdioms.g:376:5: (lv_as_3_0= RULE_ID )
                    // InternalIdioms.g:377:6: lv_as_3_0= RULE_ID
                    {
                    lv_as_3_0=(Token)match(input,RULE_ID,FollowSets000.FOLLOW_9); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						newLeafNode(lv_as_3_0, grammarAccess.getGrammarDeclarationAccess().getAsIDTerminalRuleCall_2_1_0());

                    }
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElement(grammarAccess.getGrammarDeclarationRule());
                      						}
                      						setWithLastConsumed(
                      							current,
                      							"as",
                      							lv_as_3_0,
                      							"org.eclipse.ocl.xtext.idioms.Idioms.ID");

                    }

                    }


                    }


                    }
                    break;

            }

            // InternalIdioms.g:394:3: (otherlv_4= ';' )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==15) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // InternalIdioms.g:395:4: otherlv_4= ';'
                    {
                    otherlv_4=(Token)match(input,15,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_4, grammarAccess.getGrammarDeclarationAccess().getSemicolonKeyword_3());

                    }

                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleGrammarDeclaration"


    // $ANTLR start "entryRuleIdiomsImport"
    // InternalIdioms.g:404:1: entryRuleIdiomsImport returns [EObject current=null] : iv_ruleIdiomsImport= ruleIdiomsImport EOF ;
    public final EObject entryRuleIdiomsImport() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIdiomsImport = null;


        try {
            // InternalIdioms.g:404:53: (iv_ruleIdiomsImport= ruleIdiomsImport EOF )
            // InternalIdioms.g:405:2: iv_ruleIdiomsImport= ruleIdiomsImport EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getIdiomsImportRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleIdiomsImport=ruleIdiomsImport();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleIdiomsImport;
            }
            match(input,EOF,FollowSets000.FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleIdiomsImport"


    // $ANTLR start "ruleIdiomsImport"
    // InternalIdioms.g:411:1: ruleIdiomsImport returns [EObject current=null] : (otherlv_0= 'with' ( (otherlv_1= RULE_STRING ) ) (otherlv_2= 'as' ( (lv_as_3_0= RULE_ID ) ) )? (otherlv_4= ';' )? ) ;
    public final EObject ruleIdiomsImport() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;
        Token lv_as_3_0=null;
        Token otherlv_4=null;


        	enterRule();

        try {
            // InternalIdioms.g:417:2: ( (otherlv_0= 'with' ( (otherlv_1= RULE_STRING ) ) (otherlv_2= 'as' ( (lv_as_3_0= RULE_ID ) ) )? (otherlv_4= ';' )? ) )
            // InternalIdioms.g:418:2: (otherlv_0= 'with' ( (otherlv_1= RULE_STRING ) ) (otherlv_2= 'as' ( (lv_as_3_0= RULE_ID ) ) )? (otherlv_4= ';' )? )
            {
            // InternalIdioms.g:418:2: (otherlv_0= 'with' ( (otherlv_1= RULE_STRING ) ) (otherlv_2= 'as' ( (lv_as_3_0= RULE_ID ) ) )? (otherlv_4= ';' )? )
            // InternalIdioms.g:419:3: otherlv_0= 'with' ( (otherlv_1= RULE_STRING ) ) (otherlv_2= 'as' ( (lv_as_3_0= RULE_ID ) ) )? (otherlv_4= ';' )?
            {
            otherlv_0=(Token)match(input,17,FollowSets000.FOLLOW_7); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getIdiomsImportAccess().getWithKeyword_0());

            }
            // InternalIdioms.g:423:3: ( (otherlv_1= RULE_STRING ) )
            // InternalIdioms.g:424:4: (otherlv_1= RULE_STRING )
            {
            // InternalIdioms.g:424:4: (otherlv_1= RULE_STRING )
            // InternalIdioms.g:425:5: otherlv_1= RULE_STRING
            {
            if ( state.backtracking==0 ) {

              					/* */

            }
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElement(grammarAccess.getIdiomsImportRule());
              					}

            }
            otherlv_1=(Token)match(input,RULE_STRING,FollowSets000.FOLLOW_8); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					newLeafNode(otherlv_1, grammarAccess.getIdiomsImportAccess().getIdiomsModelIdiomsModelCrossReference_1_0());

            }

            }


            }

            // InternalIdioms.g:439:3: (otherlv_2= 'as' ( (lv_as_3_0= RULE_ID ) ) )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==14) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // InternalIdioms.g:440:4: otherlv_2= 'as' ( (lv_as_3_0= RULE_ID ) )
                    {
                    otherlv_2=(Token)match(input,14,FollowSets000.FOLLOW_3); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_2, grammarAccess.getIdiomsImportAccess().getAsKeyword_2_0());

                    }
                    // InternalIdioms.g:444:4: ( (lv_as_3_0= RULE_ID ) )
                    // InternalIdioms.g:445:5: (lv_as_3_0= RULE_ID )
                    {
                    // InternalIdioms.g:445:5: (lv_as_3_0= RULE_ID )
                    // InternalIdioms.g:446:6: lv_as_3_0= RULE_ID
                    {
                    lv_as_3_0=(Token)match(input,RULE_ID,FollowSets000.FOLLOW_9); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						newLeafNode(lv_as_3_0, grammarAccess.getIdiomsImportAccess().getAsIDTerminalRuleCall_2_1_0());

                    }
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElement(grammarAccess.getIdiomsImportRule());
                      						}
                      						setWithLastConsumed(
                      							current,
                      							"as",
                      							lv_as_3_0,
                      							"org.eclipse.ocl.xtext.idioms.Idioms.ID");

                    }

                    }


                    }


                    }
                    break;

            }

            // InternalIdioms.g:463:3: (otherlv_4= ';' )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==15) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // InternalIdioms.g:464:4: otherlv_4= ';'
                    {
                    otherlv_4=(Token)match(input,15,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_4, grammarAccess.getIdiomsImportAccess().getSemicolonKeyword_3());

                    }

                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleIdiomsImport"


    // $ANTLR start "entryRuleLocatorDeclaration"
    // InternalIdioms.g:473:1: entryRuleLocatorDeclaration returns [EObject current=null] : iv_ruleLocatorDeclaration= ruleLocatorDeclaration EOF ;
    public final EObject entryRuleLocatorDeclaration() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLocatorDeclaration = null;


        try {
            // InternalIdioms.g:473:59: (iv_ruleLocatorDeclaration= ruleLocatorDeclaration EOF )
            // InternalIdioms.g:474:2: iv_ruleLocatorDeclaration= ruleLocatorDeclaration EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getLocatorDeclarationRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleLocatorDeclaration=ruleLocatorDeclaration();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleLocatorDeclaration;
            }
            match(input,EOF,FollowSets000.FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleLocatorDeclaration"


    // $ANTLR start "ruleLocatorDeclaration"
    // InternalIdioms.g:480:1: ruleLocatorDeclaration returns [EObject current=null] : (otherlv_0= 'locator' ( (lv_name_1_0= RULE_ID ) ) ( (lv_ownedLocator_2_0= ruleLocator ) ) otherlv_3= ';' ) ;
    public final EObject ruleLocatorDeclaration() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_1_0=null;
        Token otherlv_3=null;
        EObject lv_ownedLocator_2_0 = null;



        	enterRule();

        try {
            // InternalIdioms.g:486:2: ( (otherlv_0= 'locator' ( (lv_name_1_0= RULE_ID ) ) ( (lv_ownedLocator_2_0= ruleLocator ) ) otherlv_3= ';' ) )
            // InternalIdioms.g:487:2: (otherlv_0= 'locator' ( (lv_name_1_0= RULE_ID ) ) ( (lv_ownedLocator_2_0= ruleLocator ) ) otherlv_3= ';' )
            {
            // InternalIdioms.g:487:2: (otherlv_0= 'locator' ( (lv_name_1_0= RULE_ID ) ) ( (lv_ownedLocator_2_0= ruleLocator ) ) otherlv_3= ';' )
            // InternalIdioms.g:488:3: otherlv_0= 'locator' ( (lv_name_1_0= RULE_ID ) ) ( (lv_ownedLocator_2_0= ruleLocator ) ) otherlv_3= ';'
            {
            otherlv_0=(Token)match(input,18,FollowSets000.FOLLOW_3); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getLocatorDeclarationAccess().getLocatorKeyword_0());

            }
            // InternalIdioms.g:492:3: ( (lv_name_1_0= RULE_ID ) )
            // InternalIdioms.g:493:4: (lv_name_1_0= RULE_ID )
            {
            // InternalIdioms.g:493:4: (lv_name_1_0= RULE_ID )
            // InternalIdioms.g:494:5: lv_name_1_0= RULE_ID
            {
            lv_name_1_0=(Token)match(input,RULE_ID,FollowSets000.FOLLOW_10); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					newLeafNode(lv_name_1_0, grammarAccess.getLocatorDeclarationAccess().getNameIDTerminalRuleCall_1_0());

            }
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElement(grammarAccess.getLocatorDeclarationRule());
              					}
              					setWithLastConsumed(
              						current,
              						"name",
              						lv_name_1_0,
              						"org.eclipse.ocl.xtext.idioms.Idioms.ID");

            }

            }


            }

            // InternalIdioms.g:510:3: ( (lv_ownedLocator_2_0= ruleLocator ) )
            // InternalIdioms.g:511:4: (lv_ownedLocator_2_0= ruleLocator )
            {
            // InternalIdioms.g:511:4: (lv_ownedLocator_2_0= ruleLocator )
            // InternalIdioms.g:512:5: lv_ownedLocator_2_0= ruleLocator
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getLocatorDeclarationAccess().getOwnedLocatorLocatorParserRuleCall_2_0());

            }
            pushFollow(FollowSets000.FOLLOW_11);
            lv_ownedLocator_2_0=ruleLocator();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getLocatorDeclarationRule());
              					}
              					set(
              						current,
              						"ownedLocator",
              						lv_ownedLocator_2_0,
              						"org.eclipse.ocl.xtext.idioms.Idioms.Locator");
              					afterParserOrEnumRuleCall();

            }

            }


            }

            otherlv_3=(Token)match(input,15,FollowSets000.FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_3, grammarAccess.getLocatorDeclarationAccess().getSemicolonKeyword_3());

            }

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleLocatorDeclaration"


    // $ANTLR start "entryRuleLocator"
    // InternalIdioms.g:537:1: entryRuleLocator returns [EObject current=null] : iv_ruleLocator= ruleLocator EOF ;
    public final EObject entryRuleLocator() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLocator = null;


        try {
            // InternalIdioms.g:537:48: (iv_ruleLocator= ruleLocator EOF )
            // InternalIdioms.g:538:2: iv_ruleLocator= ruleLocator EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getLocatorRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleLocator=ruleLocator();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleLocator;
            }
            match(input,EOF,FollowSets000.FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleLocator"


    // $ANTLR start "ruleLocator"
    // InternalIdioms.g:544:1: ruleLocator returns [EObject current=null] : (this_AnyAssignmentLocator_0= ruleAnyAssignmentLocator | this_AnyElementLocator_1= ruleAnyElementLocator | this_AssignmentLocator_2= ruleAssignmentLocator | this_FinalLocator_3= ruleFinalLocator | this_KeywordLocator_4= ruleKeywordLocator | this_ReferredLocator_5= ruleReferredLocator | this_ReturnsLocator_6= ruleReturnsLocator | this_RuleLocator_7= ruleRuleLocator ) ;
    public final EObject ruleLocator() throws RecognitionException {
        EObject current = null;

        EObject this_AnyAssignmentLocator_0 = null;

        EObject this_AnyElementLocator_1 = null;

        EObject this_AssignmentLocator_2 = null;

        EObject this_FinalLocator_3 = null;

        EObject this_KeywordLocator_4 = null;

        EObject this_ReferredLocator_5 = null;

        EObject this_ReturnsLocator_6 = null;

        EObject this_RuleLocator_7 = null;



        	enterRule();

        try {
            // InternalIdioms.g:550:2: ( (this_AnyAssignmentLocator_0= ruleAnyAssignmentLocator | this_AnyElementLocator_1= ruleAnyElementLocator | this_AssignmentLocator_2= ruleAssignmentLocator | this_FinalLocator_3= ruleFinalLocator | this_KeywordLocator_4= ruleKeywordLocator | this_ReferredLocator_5= ruleReferredLocator | this_ReturnsLocator_6= ruleReturnsLocator | this_RuleLocator_7= ruleRuleLocator ) )
            // InternalIdioms.g:551:2: (this_AnyAssignmentLocator_0= ruleAnyAssignmentLocator | this_AnyElementLocator_1= ruleAnyElementLocator | this_AssignmentLocator_2= ruleAssignmentLocator | this_FinalLocator_3= ruleFinalLocator | this_KeywordLocator_4= ruleKeywordLocator | this_ReferredLocator_5= ruleReferredLocator | this_ReturnsLocator_6= ruleReturnsLocator | this_RuleLocator_7= ruleRuleLocator )
            {
            // InternalIdioms.g:551:2: (this_AnyAssignmentLocator_0= ruleAnyAssignmentLocator | this_AnyElementLocator_1= ruleAnyElementLocator | this_AssignmentLocator_2= ruleAssignmentLocator | this_FinalLocator_3= ruleFinalLocator | this_KeywordLocator_4= ruleKeywordLocator | this_ReferredLocator_5= ruleReferredLocator | this_ReturnsLocator_6= ruleReturnsLocator | this_RuleLocator_7= ruleRuleLocator )
            int alt10=8;
            switch ( input.LA(1) ) {
            case 19:
                {
                alt10=1;
                }
                break;
            case 20:
                {
                alt10=2;
                }
                break;
            case 21:
                {
                alt10=3;
                }
                break;
            case 23:
                {
                alt10=4;
                }
                break;
            case RULE_STRING:
                {
                alt10=5;
                }
                break;
            case RULE_ID:
                {
                alt10=6;
                }
                break;
            case 24:
                {
                alt10=7;
                }
                break;
            case 25:
                {
                alt10=8;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 10, 0, input);

                throw nvae;
            }

            switch (alt10) {
                case 1 :
                    // InternalIdioms.g:552:3: this_AnyAssignmentLocator_0= ruleAnyAssignmentLocator
                    {
                    if ( state.backtracking==0 ) {

                      			/* */

                    }
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getLocatorAccess().getAnyAssignmentLocatorParserRuleCall_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_AnyAssignmentLocator_0=ruleAnyAssignmentLocator();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_AnyAssignmentLocator_0;
                      			afterParserOrEnumRuleCall();

                    }

                    }
                    break;
                case 2 :
                    // InternalIdioms.g:564:3: this_AnyElementLocator_1= ruleAnyElementLocator
                    {
                    if ( state.backtracking==0 ) {

                      			/* */

                    }
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getLocatorAccess().getAnyElementLocatorParserRuleCall_1());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_AnyElementLocator_1=ruleAnyElementLocator();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_AnyElementLocator_1;
                      			afterParserOrEnumRuleCall();

                    }

                    }
                    break;
                case 3 :
                    // InternalIdioms.g:576:3: this_AssignmentLocator_2= ruleAssignmentLocator
                    {
                    if ( state.backtracking==0 ) {

                      			/* */

                    }
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getLocatorAccess().getAssignmentLocatorParserRuleCall_2());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_AssignmentLocator_2=ruleAssignmentLocator();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_AssignmentLocator_2;
                      			afterParserOrEnumRuleCall();

                    }

                    }
                    break;
                case 4 :
                    // InternalIdioms.g:588:3: this_FinalLocator_3= ruleFinalLocator
                    {
                    if ( state.backtracking==0 ) {

                      			/* */

                    }
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getLocatorAccess().getFinalLocatorParserRuleCall_3());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_FinalLocator_3=ruleFinalLocator();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_FinalLocator_3;
                      			afterParserOrEnumRuleCall();

                    }

                    }
                    break;
                case 5 :
                    // InternalIdioms.g:600:3: this_KeywordLocator_4= ruleKeywordLocator
                    {
                    if ( state.backtracking==0 ) {

                      			/* */

                    }
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getLocatorAccess().getKeywordLocatorParserRuleCall_4());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_KeywordLocator_4=ruleKeywordLocator();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_KeywordLocator_4;
                      			afterParserOrEnumRuleCall();

                    }

                    }
                    break;
                case 6 :
                    // InternalIdioms.g:612:3: this_ReferredLocator_5= ruleReferredLocator
                    {
                    if ( state.backtracking==0 ) {

                      			/* */

                    }
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getLocatorAccess().getReferredLocatorParserRuleCall_5());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_ReferredLocator_5=ruleReferredLocator();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_ReferredLocator_5;
                      			afterParserOrEnumRuleCall();

                    }

                    }
                    break;
                case 7 :
                    // InternalIdioms.g:624:3: this_ReturnsLocator_6= ruleReturnsLocator
                    {
                    if ( state.backtracking==0 ) {

                      			/* */

                    }
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getLocatorAccess().getReturnsLocatorParserRuleCall_6());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_ReturnsLocator_6=ruleReturnsLocator();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_ReturnsLocator_6;
                      			afterParserOrEnumRuleCall();

                    }

                    }
                    break;
                case 8 :
                    // InternalIdioms.g:636:3: this_RuleLocator_7= ruleRuleLocator
                    {
                    if ( state.backtracking==0 ) {

                      			/* */

                    }
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getLocatorAccess().getRuleLocatorParserRuleCall_7());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_RuleLocator_7=ruleRuleLocator();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_RuleLocator_7;
                      			afterParserOrEnumRuleCall();

                    }

                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleLocator"


    // $ANTLR start "entryRuleAnyAssignmentLocator"
    // InternalIdioms.g:651:1: entryRuleAnyAssignmentLocator returns [EObject current=null] : iv_ruleAnyAssignmentLocator= ruleAnyAssignmentLocator EOF ;
    public final EObject entryRuleAnyAssignmentLocator() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAnyAssignmentLocator = null;


        try {
            // InternalIdioms.g:651:61: (iv_ruleAnyAssignmentLocator= ruleAnyAssignmentLocator EOF )
            // InternalIdioms.g:652:2: iv_ruleAnyAssignmentLocator= ruleAnyAssignmentLocator EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getAnyAssignmentLocatorRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleAnyAssignmentLocator=ruleAnyAssignmentLocator();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleAnyAssignmentLocator;
            }
            match(input,EOF,FollowSets000.FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAnyAssignmentLocator"


    // $ANTLR start "ruleAnyAssignmentLocator"
    // InternalIdioms.g:658:1: ruleAnyAssignmentLocator returns [EObject current=null] : ( () otherlv_1= 'any-assignment' ) ;
    public final EObject ruleAnyAssignmentLocator() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;


        	enterRule();

        try {
            // InternalIdioms.g:664:2: ( ( () otherlv_1= 'any-assignment' ) )
            // InternalIdioms.g:665:2: ( () otherlv_1= 'any-assignment' )
            {
            // InternalIdioms.g:665:2: ( () otherlv_1= 'any-assignment' )
            // InternalIdioms.g:666:3: () otherlv_1= 'any-assignment'
            {
            // InternalIdioms.g:666:3: ()
            // InternalIdioms.g:667:4:
            {
            if ( state.backtracking==0 ) {

              				/* */

            }
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getAnyAssignmentLocatorAccess().getAnyAssignmentLocatorAction_0(),
              					current);

            }

            }

            otherlv_1=(Token)match(input,19,FollowSets000.FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getAnyAssignmentLocatorAccess().getAnyAssignmentKeyword_1());

            }

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAnyAssignmentLocator"


    // $ANTLR start "entryRuleAnyElementLocator"
    // InternalIdioms.g:684:1: entryRuleAnyElementLocator returns [EObject current=null] : iv_ruleAnyElementLocator= ruleAnyElementLocator EOF ;
    public final EObject entryRuleAnyElementLocator() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAnyElementLocator = null;


        try {
            // InternalIdioms.g:684:58: (iv_ruleAnyElementLocator= ruleAnyElementLocator EOF )
            // InternalIdioms.g:685:2: iv_ruleAnyElementLocator= ruleAnyElementLocator EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getAnyElementLocatorRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleAnyElementLocator=ruleAnyElementLocator();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleAnyElementLocator;
            }
            match(input,EOF,FollowSets000.FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAnyElementLocator"


    // $ANTLR start "ruleAnyElementLocator"
    // InternalIdioms.g:691:1: ruleAnyElementLocator returns [EObject current=null] : ( () otherlv_1= 'any-element' ) ;
    public final EObject ruleAnyElementLocator() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;


        	enterRule();

        try {
            // InternalIdioms.g:697:2: ( ( () otherlv_1= 'any-element' ) )
            // InternalIdioms.g:698:2: ( () otherlv_1= 'any-element' )
            {
            // InternalIdioms.g:698:2: ( () otherlv_1= 'any-element' )
            // InternalIdioms.g:699:3: () otherlv_1= 'any-element'
            {
            // InternalIdioms.g:699:3: ()
            // InternalIdioms.g:700:4:
            {
            if ( state.backtracking==0 ) {

              				/* */

            }
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getAnyElementLocatorAccess().getAnyElementLocatorAction_0(),
              					current);

            }

            }

            otherlv_1=(Token)match(input,20,FollowSets000.FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getAnyElementLocatorAccess().getAnyElementKeyword_1());

            }

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAnyElementLocator"


    // $ANTLR start "entryRuleAssignmentLocator"
    // InternalIdioms.g:717:1: entryRuleAssignmentLocator returns [EObject current=null] : iv_ruleAssignmentLocator= ruleAssignmentLocator EOF ;
    public final EObject entryRuleAssignmentLocator() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAssignmentLocator = null;


        try {
            // InternalIdioms.g:717:58: (iv_ruleAssignmentLocator= ruleAssignmentLocator EOF )
            // InternalIdioms.g:718:2: iv_ruleAssignmentLocator= ruleAssignmentLocator EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getAssignmentLocatorRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleAssignmentLocator=ruleAssignmentLocator();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleAssignmentLocator;
            }
            match(input,EOF,FollowSets000.FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAssignmentLocator"


    // $ANTLR start "ruleAssignmentLocator"
    // InternalIdioms.g:724:1: ruleAssignmentLocator returns [EObject current=null] : (otherlv_0= 'assignment' ( ( ( (otherlv_1= RULE_ID ) ) otherlv_2= '::' )? ( (otherlv_3= RULE_ID ) ) otherlv_4= '::' )? ( (otherlv_5= RULE_ID ) ) ) ;
    public final EObject ruleAssignmentLocator() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_5=null;


        	enterRule();

        try {
            // InternalIdioms.g:730:2: ( (otherlv_0= 'assignment' ( ( ( (otherlv_1= RULE_ID ) ) otherlv_2= '::' )? ( (otherlv_3= RULE_ID ) ) otherlv_4= '::' )? ( (otherlv_5= RULE_ID ) ) ) )
            // InternalIdioms.g:731:2: (otherlv_0= 'assignment' ( ( ( (otherlv_1= RULE_ID ) ) otherlv_2= '::' )? ( (otherlv_3= RULE_ID ) ) otherlv_4= '::' )? ( (otherlv_5= RULE_ID ) ) )
            {
            // InternalIdioms.g:731:2: (otherlv_0= 'assignment' ( ( ( (otherlv_1= RULE_ID ) ) otherlv_2= '::' )? ( (otherlv_3= RULE_ID ) ) otherlv_4= '::' )? ( (otherlv_5= RULE_ID ) ) )
            // InternalIdioms.g:732:3: otherlv_0= 'assignment' ( ( ( (otherlv_1= RULE_ID ) ) otherlv_2= '::' )? ( (otherlv_3= RULE_ID ) ) otherlv_4= '::' )? ( (otherlv_5= RULE_ID ) )
            {
            otherlv_0=(Token)match(input,21,FollowSets000.FOLLOW_3); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getAssignmentLocatorAccess().getAssignmentKeyword_0());

            }
            // InternalIdioms.g:736:3: ( ( ( (otherlv_1= RULE_ID ) ) otherlv_2= '::' )? ( (otherlv_3= RULE_ID ) ) otherlv_4= '::' )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==RULE_ID) ) {
                int LA12_1 = input.LA(2);

                if ( (LA12_1==22) ) {
                    alt12=1;
                }
            }
            switch (alt12) {
                case 1 :
                    // InternalIdioms.g:737:4: ( ( (otherlv_1= RULE_ID ) ) otherlv_2= '::' )? ( (otherlv_3= RULE_ID ) ) otherlv_4= '::'
                    {
                    // InternalIdioms.g:737:4: ( ( (otherlv_1= RULE_ID ) ) otherlv_2= '::' )?
                    int alt11=2;
                    int LA11_0 = input.LA(1);

                    if ( (LA11_0==RULE_ID) ) {
                        int LA11_1 = input.LA(2);

                        if ( (LA11_1==22) ) {
                            int LA11_2 = input.LA(3);

                            if ( (LA11_2==RULE_ID) ) {
                                int LA11_3 = input.LA(4);

                                if ( (LA11_3==22) ) {
                                    alt11=1;
                                }
                            }
                        }
                    }
                    switch (alt11) {
                        case 1 :
                            // InternalIdioms.g:738:5: ( (otherlv_1= RULE_ID ) ) otherlv_2= '::'
                            {
                            // InternalIdioms.g:738:5: ( (otherlv_1= RULE_ID ) )
                            // InternalIdioms.g:739:6: (otherlv_1= RULE_ID )
                            {
                            // InternalIdioms.g:739:6: (otherlv_1= RULE_ID )
                            // InternalIdioms.g:740:7: otherlv_1= RULE_ID
                            {
                            if ( state.backtracking==0 ) {

                              							/* */

                            }
                            if ( state.backtracking==0 ) {

                              							if (current==null) {
                              								current = createModelElement(grammarAccess.getAssignmentLocatorRule());
                              							}

                            }
                            otherlv_1=(Token)match(input,RULE_ID,FollowSets000.FOLLOW_12); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              							newLeafNode(otherlv_1, grammarAccess.getAssignmentLocatorAccess().getEPackageEPackageCrossReference_1_0_0_0());

                            }

                            }


                            }

                            otherlv_2=(Token)match(input,22,FollowSets000.FOLLOW_3); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              					newLeafNode(otherlv_2, grammarAccess.getAssignmentLocatorAccess().getColonColonKeyword_1_0_1());

                            }

                            }
                            break;

                    }

                    // InternalIdioms.g:759:4: ( (otherlv_3= RULE_ID ) )
                    // InternalIdioms.g:760:5: (otherlv_3= RULE_ID )
                    {
                    // InternalIdioms.g:760:5: (otherlv_3= RULE_ID )
                    // InternalIdioms.g:761:6: otherlv_3= RULE_ID
                    {
                    if ( state.backtracking==0 ) {

                      						/* */

                    }
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElement(grammarAccess.getAssignmentLocatorRule());
                      						}

                    }
                    otherlv_3=(Token)match(input,RULE_ID,FollowSets000.FOLLOW_12); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						newLeafNode(otherlv_3, grammarAccess.getAssignmentLocatorAccess().getEClassEClassCrossReference_1_1_0());

                    }

                    }


                    }

                    otherlv_4=(Token)match(input,22,FollowSets000.FOLLOW_3); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_4, grammarAccess.getAssignmentLocatorAccess().getColonColonKeyword_1_2());

                    }

                    }
                    break;

            }

            // InternalIdioms.g:780:3: ( (otherlv_5= RULE_ID ) )
            // InternalIdioms.g:781:4: (otherlv_5= RULE_ID )
            {
            // InternalIdioms.g:781:4: (otherlv_5= RULE_ID )
            // InternalIdioms.g:782:5: otherlv_5= RULE_ID
            {
            if ( state.backtracking==0 ) {

              					/* */

            }
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElement(grammarAccess.getAssignmentLocatorRule());
              					}

            }
            otherlv_5=(Token)match(input,RULE_ID,FollowSets000.FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					newLeafNode(otherlv_5, grammarAccess.getAssignmentLocatorAccess().getEStructuralFeatureEStructuralFeatureCrossReference_2_0());

            }

            }


            }


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAssignmentLocator"


    // $ANTLR start "entryRuleFinalLocator"
    // InternalIdioms.g:800:1: entryRuleFinalLocator returns [EObject current=null] : iv_ruleFinalLocator= ruleFinalLocator EOF ;
    public final EObject entryRuleFinalLocator() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFinalLocator = null;


        try {
            // InternalIdioms.g:800:53: (iv_ruleFinalLocator= ruleFinalLocator EOF )
            // InternalIdioms.g:801:2: iv_ruleFinalLocator= ruleFinalLocator EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getFinalLocatorRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleFinalLocator=ruleFinalLocator();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleFinalLocator;
            }
            match(input,EOF,FollowSets000.FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleFinalLocator"


    // $ANTLR start "ruleFinalLocator"
    // InternalIdioms.g:807:1: ruleFinalLocator returns [EObject current=null] : ( () otherlv_1= 'final' ) ;
    public final EObject ruleFinalLocator() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;


        	enterRule();

        try {
            // InternalIdioms.g:813:2: ( ( () otherlv_1= 'final' ) )
            // InternalIdioms.g:814:2: ( () otherlv_1= 'final' )
            {
            // InternalIdioms.g:814:2: ( () otherlv_1= 'final' )
            // InternalIdioms.g:815:3: () otherlv_1= 'final'
            {
            // InternalIdioms.g:815:3: ()
            // InternalIdioms.g:816:4:
            {
            if ( state.backtracking==0 ) {

              				/* */

            }
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getFinalLocatorAccess().getFinalLocatorAction_0(),
              					current);

            }

            }

            otherlv_1=(Token)match(input,23,FollowSets000.FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getFinalLocatorAccess().getFinalKeyword_1());

            }

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleFinalLocator"


    // $ANTLR start "entryRuleKeywordLocator"
    // InternalIdioms.g:833:1: entryRuleKeywordLocator returns [EObject current=null] : iv_ruleKeywordLocator= ruleKeywordLocator EOF ;
    public final EObject entryRuleKeywordLocator() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKeywordLocator = null;


        try {
            // InternalIdioms.g:833:55: (iv_ruleKeywordLocator= ruleKeywordLocator EOF )
            // InternalIdioms.g:834:2: iv_ruleKeywordLocator= ruleKeywordLocator EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getKeywordLocatorRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleKeywordLocator=ruleKeywordLocator();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleKeywordLocator;
            }
            match(input,EOF,FollowSets000.FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleKeywordLocator"


    // $ANTLR start "ruleKeywordLocator"
    // InternalIdioms.g:840:1: ruleKeywordLocator returns [EObject current=null] : ( (lv_string_0_0= RULE_STRING ) ) ;
    public final EObject ruleKeywordLocator() throws RecognitionException {
        EObject current = null;

        Token lv_string_0_0=null;


        	enterRule();

        try {
            // InternalIdioms.g:846:2: ( ( (lv_string_0_0= RULE_STRING ) ) )
            // InternalIdioms.g:847:2: ( (lv_string_0_0= RULE_STRING ) )
            {
            // InternalIdioms.g:847:2: ( (lv_string_0_0= RULE_STRING ) )
            // InternalIdioms.g:848:3: (lv_string_0_0= RULE_STRING )
            {
            // InternalIdioms.g:848:3: (lv_string_0_0= RULE_STRING )
            // InternalIdioms.g:849:4: lv_string_0_0= RULE_STRING
            {
            lv_string_0_0=(Token)match(input,RULE_STRING,FollowSets000.FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              				newLeafNode(lv_string_0_0, grammarAccess.getKeywordLocatorAccess().getStringSTRINGTerminalRuleCall_0());

            }
            if ( state.backtracking==0 ) {

              				if (current==null) {
              					current = createModelElement(grammarAccess.getKeywordLocatorRule());
              				}
              				setWithLastConsumed(
              					current,
              					"string",
              					lv_string_0_0,
              					"org.eclipse.ocl.xtext.idioms.Idioms.STRING");

            }

            }


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleKeywordLocator"


    // $ANTLR start "entryRuleReturnsLocator"
    // InternalIdioms.g:868:1: entryRuleReturnsLocator returns [EObject current=null] : iv_ruleReturnsLocator= ruleReturnsLocator EOF ;
    public final EObject entryRuleReturnsLocator() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleReturnsLocator = null;


        try {
            // InternalIdioms.g:868:55: (iv_ruleReturnsLocator= ruleReturnsLocator EOF )
            // InternalIdioms.g:869:2: iv_ruleReturnsLocator= ruleReturnsLocator EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getReturnsLocatorRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleReturnsLocator=ruleReturnsLocator();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleReturnsLocator;
            }
            match(input,EOF,FollowSets000.FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleReturnsLocator"


    // $ANTLR start "ruleReturnsLocator"
    // InternalIdioms.g:875:1: ruleReturnsLocator returns [EObject current=null] : (otherlv_0= 'returns' ( ( (otherlv_1= RULE_ID ) ) otherlv_2= '::' )? ( (otherlv_3= RULE_ID ) ) ) ;
    public final EObject ruleReturnsLocator() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;


        	enterRule();

        try {
            // InternalIdioms.g:881:2: ( (otherlv_0= 'returns' ( ( (otherlv_1= RULE_ID ) ) otherlv_2= '::' )? ( (otherlv_3= RULE_ID ) ) ) )
            // InternalIdioms.g:882:2: (otherlv_0= 'returns' ( ( (otherlv_1= RULE_ID ) ) otherlv_2= '::' )? ( (otherlv_3= RULE_ID ) ) )
            {
            // InternalIdioms.g:882:2: (otherlv_0= 'returns' ( ( (otherlv_1= RULE_ID ) ) otherlv_2= '::' )? ( (otherlv_3= RULE_ID ) ) )
            // InternalIdioms.g:883:3: otherlv_0= 'returns' ( ( (otherlv_1= RULE_ID ) ) otherlv_2= '::' )? ( (otherlv_3= RULE_ID ) )
            {
            otherlv_0=(Token)match(input,24,FollowSets000.FOLLOW_3); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getReturnsLocatorAccess().getReturnsKeyword_0());

            }
            // InternalIdioms.g:887:3: ( ( (otherlv_1= RULE_ID ) ) otherlv_2= '::' )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==RULE_ID) ) {
                int LA13_1 = input.LA(2);

                if ( (LA13_1==22) ) {
                    alt13=1;
                }
            }
            switch (alt13) {
                case 1 :
                    // InternalIdioms.g:888:4: ( (otherlv_1= RULE_ID ) ) otherlv_2= '::'
                    {
                    // InternalIdioms.g:888:4: ( (otherlv_1= RULE_ID ) )
                    // InternalIdioms.g:889:5: (otherlv_1= RULE_ID )
                    {
                    // InternalIdioms.g:889:5: (otherlv_1= RULE_ID )
                    // InternalIdioms.g:890:6: otherlv_1= RULE_ID
                    {
                    if ( state.backtracking==0 ) {

                      						/* */

                    }
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElement(grammarAccess.getReturnsLocatorRule());
                      						}

                    }
                    otherlv_1=(Token)match(input,RULE_ID,FollowSets000.FOLLOW_12); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						newLeafNode(otherlv_1, grammarAccess.getReturnsLocatorAccess().getEPackageEPackageCrossReference_1_0_0());

                    }

                    }


                    }

                    otherlv_2=(Token)match(input,22,FollowSets000.FOLLOW_3); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_2, grammarAccess.getReturnsLocatorAccess().getColonColonKeyword_1_1());

                    }

                    }
                    break;

            }

            // InternalIdioms.g:909:3: ( (otherlv_3= RULE_ID ) )
            // InternalIdioms.g:910:4: (otherlv_3= RULE_ID )
            {
            // InternalIdioms.g:910:4: (otherlv_3= RULE_ID )
            // InternalIdioms.g:911:5: otherlv_3= RULE_ID
            {
            if ( state.backtracking==0 ) {

              					/* */

            }
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElement(grammarAccess.getReturnsLocatorRule());
              					}

            }
            otherlv_3=(Token)match(input,RULE_ID,FollowSets000.FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					newLeafNode(otherlv_3, grammarAccess.getReturnsLocatorAccess().getEClassEClassCrossReference_2_0());

            }

            }


            }


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleReturnsLocator"


    // $ANTLR start "entryRuleReferredLocator"
    // InternalIdioms.g:929:1: entryRuleReferredLocator returns [EObject current=null] : iv_ruleReferredLocator= ruleReferredLocator EOF ;
    public final EObject entryRuleReferredLocator() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleReferredLocator = null;


        try {
            // InternalIdioms.g:929:56: (iv_ruleReferredLocator= ruleReferredLocator EOF )
            // InternalIdioms.g:930:2: iv_ruleReferredLocator= ruleReferredLocator EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getReferredLocatorRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleReferredLocator=ruleReferredLocator();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleReferredLocator;
            }
            match(input,EOF,FollowSets000.FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleReferredLocator"


    // $ANTLR start "ruleReferredLocator"
    // InternalIdioms.g:936:1: ruleReferredLocator returns [EObject current=null] : ( ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '::' )? ( (otherlv_2= RULE_ID ) ) ) ;
    public final EObject ruleReferredLocator() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;


        	enterRule();

        try {
            // InternalIdioms.g:942:2: ( ( ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '::' )? ( (otherlv_2= RULE_ID ) ) ) )
            // InternalIdioms.g:943:2: ( ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '::' )? ( (otherlv_2= RULE_ID ) ) )
            {
            // InternalIdioms.g:943:2: ( ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '::' )? ( (otherlv_2= RULE_ID ) ) )
            // InternalIdioms.g:944:3: ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '::' )? ( (otherlv_2= RULE_ID ) )
            {
            // InternalIdioms.g:944:3: ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '::' )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==RULE_ID) ) {
                int LA14_1 = input.LA(2);

                if ( (LA14_1==22) ) {
                    alt14=1;
                }
            }
            switch (alt14) {
                case 1 :
                    // InternalIdioms.g:945:4: ( (otherlv_0= RULE_ID ) ) otherlv_1= '::'
                    {
                    // InternalIdioms.g:945:4: ( (otherlv_0= RULE_ID ) )
                    // InternalIdioms.g:946:5: (otherlv_0= RULE_ID )
                    {
                    // InternalIdioms.g:946:5: (otherlv_0= RULE_ID )
                    // InternalIdioms.g:947:6: otherlv_0= RULE_ID
                    {
                    if ( state.backtracking==0 ) {

                      						/* */

                    }
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElement(grammarAccess.getReferredLocatorRule());
                      						}

                    }
                    otherlv_0=(Token)match(input,RULE_ID,FollowSets000.FOLLOW_12); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						newLeafNode(otherlv_0, grammarAccess.getReferredLocatorAccess().getIdiomsModelIdiomsModelCrossReference_0_0_0());

                    }

                    }


                    }

                    otherlv_1=(Token)match(input,22,FollowSets000.FOLLOW_3); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_1, grammarAccess.getReferredLocatorAccess().getColonColonKeyword_0_1());

                    }

                    }
                    break;

            }

            // InternalIdioms.g:966:3: ( (otherlv_2= RULE_ID ) )
            // InternalIdioms.g:967:4: (otherlv_2= RULE_ID )
            {
            // InternalIdioms.g:967:4: (otherlv_2= RULE_ID )
            // InternalIdioms.g:968:5: otherlv_2= RULE_ID
            {
            if ( state.backtracking==0 ) {

              					/* */

            }
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElement(grammarAccess.getReferredLocatorRule());
              					}

            }
            otherlv_2=(Token)match(input,RULE_ID,FollowSets000.FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					newLeafNode(otherlv_2, grammarAccess.getReferredLocatorAccess().getLocatorDeclarationLocatorDeclarationCrossReference_1_0());

            }

            }


            }


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleReferredLocator"


    // $ANTLR start "entryRuleRuleLocator"
    // InternalIdioms.g:986:1: entryRuleRuleLocator returns [EObject current=null] : iv_ruleRuleLocator= ruleRuleLocator EOF ;
    public final EObject entryRuleRuleLocator() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRuleLocator = null;


        try {
            // InternalIdioms.g:986:52: (iv_ruleRuleLocator= ruleRuleLocator EOF )
            // InternalIdioms.g:987:2: iv_ruleRuleLocator= ruleRuleLocator EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getRuleLocatorRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleRuleLocator=ruleRuleLocator();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleRuleLocator;
            }
            match(input,EOF,FollowSets000.FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleRuleLocator"


    // $ANTLR start "ruleRuleLocator"
    // InternalIdioms.g:993:1: ruleRuleLocator returns [EObject current=null] : (otherlv_0= 'rule' ( ( (otherlv_1= RULE_ID ) ) otherlv_2= '::' )? ( (otherlv_3= RULE_ID ) ) ) ;
    public final EObject ruleRuleLocator() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;


        	enterRule();

        try {
            // InternalIdioms.g:999:2: ( (otherlv_0= 'rule' ( ( (otherlv_1= RULE_ID ) ) otherlv_2= '::' )? ( (otherlv_3= RULE_ID ) ) ) )
            // InternalIdioms.g:1000:2: (otherlv_0= 'rule' ( ( (otherlv_1= RULE_ID ) ) otherlv_2= '::' )? ( (otherlv_3= RULE_ID ) ) )
            {
            // InternalIdioms.g:1000:2: (otherlv_0= 'rule' ( ( (otherlv_1= RULE_ID ) ) otherlv_2= '::' )? ( (otherlv_3= RULE_ID ) ) )
            // InternalIdioms.g:1001:3: otherlv_0= 'rule' ( ( (otherlv_1= RULE_ID ) ) otherlv_2= '::' )? ( (otherlv_3= RULE_ID ) )
            {
            otherlv_0=(Token)match(input,25,FollowSets000.FOLLOW_3); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getRuleLocatorAccess().getRuleKeyword_0());

            }
            // InternalIdioms.g:1005:3: ( ( (otherlv_1= RULE_ID ) ) otherlv_2= '::' )?
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
                    // InternalIdioms.g:1006:4: ( (otherlv_1= RULE_ID ) ) otherlv_2= '::'
                    {
                    // InternalIdioms.g:1006:4: ( (otherlv_1= RULE_ID ) )
                    // InternalIdioms.g:1007:5: (otherlv_1= RULE_ID )
                    {
                    // InternalIdioms.g:1007:5: (otherlv_1= RULE_ID )
                    // InternalIdioms.g:1008:6: otherlv_1= RULE_ID
                    {
                    if ( state.backtracking==0 ) {

                      						/* */

                    }
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElement(grammarAccess.getRuleLocatorRule());
                      						}

                    }
                    otherlv_1=(Token)match(input,RULE_ID,FollowSets000.FOLLOW_12); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						newLeafNode(otherlv_1, grammarAccess.getRuleLocatorAccess().getReferredGrammarGrammarDeclarationCrossReference_1_0_0());

                    }

                    }


                    }

                    otherlv_2=(Token)match(input,22,FollowSets000.FOLLOW_3); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_2, grammarAccess.getRuleLocatorAccess().getColonColonKeyword_1_1());

                    }

                    }
                    break;

            }

            // InternalIdioms.g:1027:3: ( (otherlv_3= RULE_ID ) )
            // InternalIdioms.g:1028:4: (otherlv_3= RULE_ID )
            {
            // InternalIdioms.g:1028:4: (otherlv_3= RULE_ID )
            // InternalIdioms.g:1029:5: otherlv_3= RULE_ID
            {
            if ( state.backtracking==0 ) {

              					/* */

            }
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElement(grammarAccess.getRuleLocatorRule());
              					}

            }
            otherlv_3=(Token)match(input,RULE_ID,FollowSets000.FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					newLeafNode(otherlv_3, grammarAccess.getRuleLocatorAccess().getReferredRuleAbstractRuleCrossReference_2_0());

            }

            }


            }


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleRuleLocator"


    // $ANTLR start "entryRuleSegmentDeclaration"
    // InternalIdioms.g:1047:1: entryRuleSegmentDeclaration returns [EObject current=null] : iv_ruleSegmentDeclaration= ruleSegmentDeclaration EOF ;
    public final EObject entryRuleSegmentDeclaration() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSegmentDeclaration = null;


        try {
            // InternalIdioms.g:1047:59: (iv_ruleSegmentDeclaration= ruleSegmentDeclaration EOF )
            // InternalIdioms.g:1048:2: iv_ruleSegmentDeclaration= ruleSegmentDeclaration EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getSegmentDeclarationRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleSegmentDeclaration=ruleSegmentDeclaration();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleSegmentDeclaration;
            }
            match(input,EOF,FollowSets000.FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleSegmentDeclaration"


    // $ANTLR start "ruleSegmentDeclaration"
    // InternalIdioms.g:1054:1: ruleSegmentDeclaration returns [EObject current=null] : (otherlv_0= 'segment' ( (lv_name_1_0= RULE_ID ) ) ( (lv_ownedSegment_2_0= ruleSegment ) ) otherlv_3= ';' ) ;
    public final EObject ruleSegmentDeclaration() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_1_0=null;
        Token otherlv_3=null;
        EObject lv_ownedSegment_2_0 = null;



        	enterRule();

        try {
            // InternalIdioms.g:1060:2: ( (otherlv_0= 'segment' ( (lv_name_1_0= RULE_ID ) ) ( (lv_ownedSegment_2_0= ruleSegment ) ) otherlv_3= ';' ) )
            // InternalIdioms.g:1061:2: (otherlv_0= 'segment' ( (lv_name_1_0= RULE_ID ) ) ( (lv_ownedSegment_2_0= ruleSegment ) ) otherlv_3= ';' )
            {
            // InternalIdioms.g:1061:2: (otherlv_0= 'segment' ( (lv_name_1_0= RULE_ID ) ) ( (lv_ownedSegment_2_0= ruleSegment ) ) otherlv_3= ';' )
            // InternalIdioms.g:1062:3: otherlv_0= 'segment' ( (lv_name_1_0= RULE_ID ) ) ( (lv_ownedSegment_2_0= ruleSegment ) ) otherlv_3= ';'
            {
            otherlv_0=(Token)match(input,26,FollowSets000.FOLLOW_3); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getSegmentDeclarationAccess().getSegmentKeyword_0());

            }
            // InternalIdioms.g:1066:3: ( (lv_name_1_0= RULE_ID ) )
            // InternalIdioms.g:1067:4: (lv_name_1_0= RULE_ID )
            {
            // InternalIdioms.g:1067:4: (lv_name_1_0= RULE_ID )
            // InternalIdioms.g:1068:5: lv_name_1_0= RULE_ID
            {
            lv_name_1_0=(Token)match(input,RULE_ID,FollowSets000.FOLLOW_13); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					newLeafNode(lv_name_1_0, grammarAccess.getSegmentDeclarationAccess().getNameIDTerminalRuleCall_1_0());

            }
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElement(grammarAccess.getSegmentDeclarationRule());
              					}
              					setWithLastConsumed(
              						current,
              						"name",
              						lv_name_1_0,
              						"org.eclipse.ocl.xtext.idioms.Idioms.ID");

            }

            }


            }

            // InternalIdioms.g:1084:3: ( (lv_ownedSegment_2_0= ruleSegment ) )
            // InternalIdioms.g:1085:4: (lv_ownedSegment_2_0= ruleSegment )
            {
            // InternalIdioms.g:1085:4: (lv_ownedSegment_2_0= ruleSegment )
            // InternalIdioms.g:1086:5: lv_ownedSegment_2_0= ruleSegment
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getSegmentDeclarationAccess().getOwnedSegmentSegmentParserRuleCall_2_0());

            }
            pushFollow(FollowSets000.FOLLOW_11);
            lv_ownedSegment_2_0=ruleSegment();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getSegmentDeclarationRule());
              					}
              					set(
              						current,
              						"ownedSegment",
              						lv_ownedSegment_2_0,
              						"org.eclipse.ocl.xtext.idioms.Idioms.Segment");
              					afterParserOrEnumRuleCall();

            }

            }


            }

            otherlv_3=(Token)match(input,15,FollowSets000.FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_3, grammarAccess.getSegmentDeclarationAccess().getSemicolonKeyword_3());

            }

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSegmentDeclaration"


    // $ANTLR start "entryRuleSegment"
    // InternalIdioms.g:1111:1: entryRuleSegment returns [EObject current=null] : iv_ruleSegment= ruleSegment EOF ;
    public final EObject entryRuleSegment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSegment = null;


        try {
            // InternalIdioms.g:1111:48: (iv_ruleSegment= ruleSegment EOF )
            // InternalIdioms.g:1112:2: iv_ruleSegment= ruleSegment EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getSegmentRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleSegment=ruleSegment();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleSegment;
            }
            match(input,EOF,FollowSets000.FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleSegment"


    // $ANTLR start "ruleSegment"
    // InternalIdioms.g:1118:1: ruleSegment returns [EObject current=null] : (this_CustomSegment_0= ruleCustomSegment | this_HalfNewLineSegment_1= ruleHalfNewLineSegment | this_NewLineSegment_2= ruleNewLineSegment | this_NoNewLineSegment_3= ruleNoNewLineSegment | this_NoSpaceSegment_4= ruleNoSpaceSegment | this_PopSegment_5= rulePopSegment | this_PostCommentSegment_6= rulePostCommentSegment | this_PreCommentSegment_7= rulePreCommentSegment | this_PushSegment_8= rulePushSegment | this_SoftNewLineSegment_9= ruleSoftNewLineSegment | this_SoftSpaceSegment_10= ruleSoftSpaceSegment | this_StringSegment_11= ruleStringSegment | this_ValueSegment_12= ruleValueSegment | this_WrapAnchorSegment_13= ruleWrapAnchorSegment | this_WrapBeginAllSegment_14= ruleWrapBeginAllSegment | this_WrapBeginSomeSegment_15= ruleWrapBeginSomeSegment | this_WrapEndSegment_16= ruleWrapEndSegment | this_WrapHereSegment_17= ruleWrapHereSegment ) ;
    public final EObject ruleSegment() throws RecognitionException {
        EObject current = null;

        EObject this_CustomSegment_0 = null;

        EObject this_HalfNewLineSegment_1 = null;

        EObject this_NewLineSegment_2 = null;

        EObject this_NoNewLineSegment_3 = null;

        EObject this_NoSpaceSegment_4 = null;

        EObject this_PopSegment_5 = null;

        EObject this_PostCommentSegment_6 = null;

        EObject this_PreCommentSegment_7 = null;

        EObject this_PushSegment_8 = null;

        EObject this_SoftNewLineSegment_9 = null;

        EObject this_SoftSpaceSegment_10 = null;

        EObject this_StringSegment_11 = null;

        EObject this_ValueSegment_12 = null;

        EObject this_WrapAnchorSegment_13 = null;

        EObject this_WrapBeginAllSegment_14 = null;

        EObject this_WrapBeginSomeSegment_15 = null;

        EObject this_WrapEndSegment_16 = null;

        EObject this_WrapHereSegment_17 = null;



        	enterRule();

        try {
            // InternalIdioms.g:1124:2: ( (this_CustomSegment_0= ruleCustomSegment | this_HalfNewLineSegment_1= ruleHalfNewLineSegment | this_NewLineSegment_2= ruleNewLineSegment | this_NoNewLineSegment_3= ruleNoNewLineSegment | this_NoSpaceSegment_4= ruleNoSpaceSegment | this_PopSegment_5= rulePopSegment | this_PostCommentSegment_6= rulePostCommentSegment | this_PreCommentSegment_7= rulePreCommentSegment | this_PushSegment_8= rulePushSegment | this_SoftNewLineSegment_9= ruleSoftNewLineSegment | this_SoftSpaceSegment_10= ruleSoftSpaceSegment | this_StringSegment_11= ruleStringSegment | this_ValueSegment_12= ruleValueSegment | this_WrapAnchorSegment_13= ruleWrapAnchorSegment | this_WrapBeginAllSegment_14= ruleWrapBeginAllSegment | this_WrapBeginSomeSegment_15= ruleWrapBeginSomeSegment | this_WrapEndSegment_16= ruleWrapEndSegment | this_WrapHereSegment_17= ruleWrapHereSegment ) )
            // InternalIdioms.g:1125:2: (this_CustomSegment_0= ruleCustomSegment | this_HalfNewLineSegment_1= ruleHalfNewLineSegment | this_NewLineSegment_2= ruleNewLineSegment | this_NoNewLineSegment_3= ruleNoNewLineSegment | this_NoSpaceSegment_4= ruleNoSpaceSegment | this_PopSegment_5= rulePopSegment | this_PostCommentSegment_6= rulePostCommentSegment | this_PreCommentSegment_7= rulePreCommentSegment | this_PushSegment_8= rulePushSegment | this_SoftNewLineSegment_9= ruleSoftNewLineSegment | this_SoftSpaceSegment_10= ruleSoftSpaceSegment | this_StringSegment_11= ruleStringSegment | this_ValueSegment_12= ruleValueSegment | this_WrapAnchorSegment_13= ruleWrapAnchorSegment | this_WrapBeginAllSegment_14= ruleWrapBeginAllSegment | this_WrapBeginSomeSegment_15= ruleWrapBeginSomeSegment | this_WrapEndSegment_16= ruleWrapEndSegment | this_WrapHereSegment_17= ruleWrapHereSegment )
            {
            // InternalIdioms.g:1125:2: (this_CustomSegment_0= ruleCustomSegment | this_HalfNewLineSegment_1= ruleHalfNewLineSegment | this_NewLineSegment_2= ruleNewLineSegment | this_NoNewLineSegment_3= ruleNoNewLineSegment | this_NoSpaceSegment_4= ruleNoSpaceSegment | this_PopSegment_5= rulePopSegment | this_PostCommentSegment_6= rulePostCommentSegment | this_PreCommentSegment_7= rulePreCommentSegment | this_PushSegment_8= rulePushSegment | this_SoftNewLineSegment_9= ruleSoftNewLineSegment | this_SoftSpaceSegment_10= ruleSoftSpaceSegment | this_StringSegment_11= ruleStringSegment | this_ValueSegment_12= ruleValueSegment | this_WrapAnchorSegment_13= ruleWrapAnchorSegment | this_WrapBeginAllSegment_14= ruleWrapBeginAllSegment | this_WrapBeginSomeSegment_15= ruleWrapBeginSomeSegment | this_WrapEndSegment_16= ruleWrapEndSegment | this_WrapHereSegment_17= ruleWrapHereSegment )
            int alt16=18;
            switch ( input.LA(1) ) {
            case 27:
                {
                alt16=1;
                }
                break;
            case 28:
                {
                alt16=2;
                }
                break;
            case 29:
                {
                alt16=3;
                }
                break;
            case 30:
                {
                alt16=4;
                }
                break;
            case 31:
                {
                alt16=5;
                }
                break;
            case 32:
                {
                alt16=6;
                }
                break;
            case 33:
                {
                alt16=7;
                }
                break;
            case 34:
                {
                alt16=8;
                }
                break;
            case 35:
                {
                alt16=9;
                }
                break;
            case 36:
                {
                alt16=10;
                }
                break;
            case 37:
                {
                alt16=11;
                }
                break;
            case 38:
                {
                alt16=12;
                }
                break;
            case 40:
                {
                alt16=13;
                }
                break;
            case 41:
                {
                alt16=14;
                }
                break;
            case 42:
                {
                alt16=15;
                }
                break;
            case 43:
                {
                alt16=16;
                }
                break;
            case 44:
                {
                alt16=17;
                }
                break;
            case 45:
                {
                alt16=18;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 16, 0, input);

                throw nvae;
            }

            switch (alt16) {
                case 1 :
                    // InternalIdioms.g:1126:3: this_CustomSegment_0= ruleCustomSegment
                    {
                    if ( state.backtracking==0 ) {

                      			/* */

                    }
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getSegmentAccess().getCustomSegmentParserRuleCall_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_CustomSegment_0=ruleCustomSegment();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_CustomSegment_0;
                      			afterParserOrEnumRuleCall();

                    }

                    }
                    break;
                case 2 :
                    // InternalIdioms.g:1138:3: this_HalfNewLineSegment_1= ruleHalfNewLineSegment
                    {
                    if ( state.backtracking==0 ) {

                      			/* */

                    }
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getSegmentAccess().getHalfNewLineSegmentParserRuleCall_1());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_HalfNewLineSegment_1=ruleHalfNewLineSegment();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_HalfNewLineSegment_1;
                      			afterParserOrEnumRuleCall();

                    }

                    }
                    break;
                case 3 :
                    // InternalIdioms.g:1150:3: this_NewLineSegment_2= ruleNewLineSegment
                    {
                    if ( state.backtracking==0 ) {

                      			/* */

                    }
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getSegmentAccess().getNewLineSegmentParserRuleCall_2());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_NewLineSegment_2=ruleNewLineSegment();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_NewLineSegment_2;
                      			afterParserOrEnumRuleCall();

                    }

                    }
                    break;
                case 4 :
                    // InternalIdioms.g:1162:3: this_NoNewLineSegment_3= ruleNoNewLineSegment
                    {
                    if ( state.backtracking==0 ) {

                      			/* */

                    }
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getSegmentAccess().getNoNewLineSegmentParserRuleCall_3());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_NoNewLineSegment_3=ruleNoNewLineSegment();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_NoNewLineSegment_3;
                      			afterParserOrEnumRuleCall();

                    }

                    }
                    break;
                case 5 :
                    // InternalIdioms.g:1174:3: this_NoSpaceSegment_4= ruleNoSpaceSegment
                    {
                    if ( state.backtracking==0 ) {

                      			/* */

                    }
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getSegmentAccess().getNoSpaceSegmentParserRuleCall_4());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_NoSpaceSegment_4=ruleNoSpaceSegment();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_NoSpaceSegment_4;
                      			afterParserOrEnumRuleCall();

                    }

                    }
                    break;
                case 6 :
                    // InternalIdioms.g:1186:3: this_PopSegment_5= rulePopSegment
                    {
                    if ( state.backtracking==0 ) {

                      			/* */

                    }
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getSegmentAccess().getPopSegmentParserRuleCall_5());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_PopSegment_5=rulePopSegment();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_PopSegment_5;
                      			afterParserOrEnumRuleCall();

                    }

                    }
                    break;
                case 7 :
                    // InternalIdioms.g:1198:3: this_PostCommentSegment_6= rulePostCommentSegment
                    {
                    if ( state.backtracking==0 ) {

                      			/* */

                    }
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getSegmentAccess().getPostCommentSegmentParserRuleCall_6());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_PostCommentSegment_6=rulePostCommentSegment();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_PostCommentSegment_6;
                      			afterParserOrEnumRuleCall();

                    }

                    }
                    break;
                case 8 :
                    // InternalIdioms.g:1210:3: this_PreCommentSegment_7= rulePreCommentSegment
                    {
                    if ( state.backtracking==0 ) {

                      			/* */

                    }
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getSegmentAccess().getPreCommentSegmentParserRuleCall_7());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_PreCommentSegment_7=rulePreCommentSegment();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_PreCommentSegment_7;
                      			afterParserOrEnumRuleCall();

                    }

                    }
                    break;
                case 9 :
                    // InternalIdioms.g:1222:3: this_PushSegment_8= rulePushSegment
                    {
                    if ( state.backtracking==0 ) {

                      			/* */

                    }
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getSegmentAccess().getPushSegmentParserRuleCall_8());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_PushSegment_8=rulePushSegment();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_PushSegment_8;
                      			afterParserOrEnumRuleCall();

                    }

                    }
                    break;
                case 10 :
                    // InternalIdioms.g:1234:3: this_SoftNewLineSegment_9= ruleSoftNewLineSegment
                    {
                    if ( state.backtracking==0 ) {

                      			/* */

                    }
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getSegmentAccess().getSoftNewLineSegmentParserRuleCall_9());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_SoftNewLineSegment_9=ruleSoftNewLineSegment();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_SoftNewLineSegment_9;
                      			afterParserOrEnumRuleCall();

                    }

                    }
                    break;
                case 11 :
                    // InternalIdioms.g:1246:3: this_SoftSpaceSegment_10= ruleSoftSpaceSegment
                    {
                    if ( state.backtracking==0 ) {

                      			/* */

                    }
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getSegmentAccess().getSoftSpaceSegmentParserRuleCall_10());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_SoftSpaceSegment_10=ruleSoftSpaceSegment();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_SoftSpaceSegment_10;
                      			afterParserOrEnumRuleCall();

                    }

                    }
                    break;
                case 12 :
                    // InternalIdioms.g:1258:3: this_StringSegment_11= ruleStringSegment
                    {
                    if ( state.backtracking==0 ) {

                      			/* */

                    }
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getSegmentAccess().getStringSegmentParserRuleCall_11());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_StringSegment_11=ruleStringSegment();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_StringSegment_11;
                      			afterParserOrEnumRuleCall();

                    }

                    }
                    break;
                case 13 :
                    // InternalIdioms.g:1270:3: this_ValueSegment_12= ruleValueSegment
                    {
                    if ( state.backtracking==0 ) {

                      			/* */

                    }
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getSegmentAccess().getValueSegmentParserRuleCall_12());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_ValueSegment_12=ruleValueSegment();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_ValueSegment_12;
                      			afterParserOrEnumRuleCall();

                    }

                    }
                    break;
                case 14 :
                    // InternalIdioms.g:1282:3: this_WrapAnchorSegment_13= ruleWrapAnchorSegment
                    {
                    if ( state.backtracking==0 ) {

                      			/* */

                    }
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getSegmentAccess().getWrapAnchorSegmentParserRuleCall_13());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_WrapAnchorSegment_13=ruleWrapAnchorSegment();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_WrapAnchorSegment_13;
                      			afterParserOrEnumRuleCall();

                    }

                    }
                    break;
                case 15 :
                    // InternalIdioms.g:1294:3: this_WrapBeginAllSegment_14= ruleWrapBeginAllSegment
                    {
                    if ( state.backtracking==0 ) {

                      			/* */

                    }
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getSegmentAccess().getWrapBeginAllSegmentParserRuleCall_14());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_WrapBeginAllSegment_14=ruleWrapBeginAllSegment();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_WrapBeginAllSegment_14;
                      			afterParserOrEnumRuleCall();

                    }

                    }
                    break;
                case 16 :
                    // InternalIdioms.g:1306:3: this_WrapBeginSomeSegment_15= ruleWrapBeginSomeSegment
                    {
                    if ( state.backtracking==0 ) {

                      			/* */

                    }
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getSegmentAccess().getWrapBeginSomeSegmentParserRuleCall_15());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_WrapBeginSomeSegment_15=ruleWrapBeginSomeSegment();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_WrapBeginSomeSegment_15;
                      			afterParserOrEnumRuleCall();

                    }

                    }
                    break;
                case 17 :
                    // InternalIdioms.g:1318:3: this_WrapEndSegment_16= ruleWrapEndSegment
                    {
                    if ( state.backtracking==0 ) {

                      			/* */

                    }
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getSegmentAccess().getWrapEndSegmentParserRuleCall_16());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_WrapEndSegment_16=ruleWrapEndSegment();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_WrapEndSegment_16;
                      			afterParserOrEnumRuleCall();

                    }

                    }
                    break;
                case 18 :
                    // InternalIdioms.g:1330:3: this_WrapHereSegment_17= ruleWrapHereSegment
                    {
                    if ( state.backtracking==0 ) {

                      			/* */

                    }
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getSegmentAccess().getWrapHereSegmentParserRuleCall_17());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_WrapHereSegment_17=ruleWrapHereSegment();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_WrapHereSegment_17;
                      			afterParserOrEnumRuleCall();

                    }

                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSegment"


    // $ANTLR start "entryRuleCustomSegment"
    // InternalIdioms.g:1345:1: entryRuleCustomSegment returns [EObject current=null] : iv_ruleCustomSegment= ruleCustomSegment EOF ;
    public final EObject entryRuleCustomSegment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCustomSegment = null;


        try {
            // InternalIdioms.g:1345:54: (iv_ruleCustomSegment= ruleCustomSegment EOF )
            // InternalIdioms.g:1346:2: iv_ruleCustomSegment= ruleCustomSegment EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getCustomSegmentRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleCustomSegment=ruleCustomSegment();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleCustomSegment;
            }
            match(input,EOF,FollowSets000.FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleCustomSegment"


    // $ANTLR start "ruleCustomSegment"
    // InternalIdioms.g:1352:1: ruleCustomSegment returns [EObject current=null] : (otherlv_0= 'custom' ( (lv_supportClassName_1_0= RULE_STRING ) ) ) ;
    public final EObject ruleCustomSegment() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_supportClassName_1_0=null;


        	enterRule();

        try {
            // InternalIdioms.g:1358:2: ( (otherlv_0= 'custom' ( (lv_supportClassName_1_0= RULE_STRING ) ) ) )
            // InternalIdioms.g:1359:2: (otherlv_0= 'custom' ( (lv_supportClassName_1_0= RULE_STRING ) ) )
            {
            // InternalIdioms.g:1359:2: (otherlv_0= 'custom' ( (lv_supportClassName_1_0= RULE_STRING ) ) )
            // InternalIdioms.g:1360:3: otherlv_0= 'custom' ( (lv_supportClassName_1_0= RULE_STRING ) )
            {
            otherlv_0=(Token)match(input,27,FollowSets000.FOLLOW_7); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getCustomSegmentAccess().getCustomKeyword_0());

            }
            // InternalIdioms.g:1364:3: ( (lv_supportClassName_1_0= RULE_STRING ) )
            // InternalIdioms.g:1365:4: (lv_supportClassName_1_0= RULE_STRING )
            {
            // InternalIdioms.g:1365:4: (lv_supportClassName_1_0= RULE_STRING )
            // InternalIdioms.g:1366:5: lv_supportClassName_1_0= RULE_STRING
            {
            lv_supportClassName_1_0=(Token)match(input,RULE_STRING,FollowSets000.FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					newLeafNode(lv_supportClassName_1_0, grammarAccess.getCustomSegmentAccess().getSupportClassNameSTRINGTerminalRuleCall_1_0());

            }
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElement(grammarAccess.getCustomSegmentRule());
              					}
              					setWithLastConsumed(
              						current,
              						"supportClassName",
              						lv_supportClassName_1_0,
              						"org.eclipse.ocl.xtext.idioms.Idioms.STRING");

            }

            }


            }


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleCustomSegment"


    // $ANTLR start "entryRuleHalfNewLineSegment"
    // InternalIdioms.g:1386:1: entryRuleHalfNewLineSegment returns [EObject current=null] : iv_ruleHalfNewLineSegment= ruleHalfNewLineSegment EOF ;
    public final EObject entryRuleHalfNewLineSegment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleHalfNewLineSegment = null;


        try {
            // InternalIdioms.g:1386:59: (iv_ruleHalfNewLineSegment= ruleHalfNewLineSegment EOF )
            // InternalIdioms.g:1387:2: iv_ruleHalfNewLineSegment= ruleHalfNewLineSegment EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getHalfNewLineSegmentRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleHalfNewLineSegment=ruleHalfNewLineSegment();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleHalfNewLineSegment;
            }
            match(input,EOF,FollowSets000.FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleHalfNewLineSegment"


    // $ANTLR start "ruleHalfNewLineSegment"
    // InternalIdioms.g:1393:1: ruleHalfNewLineSegment returns [EObject current=null] : ( () otherlv_1= 'half-new-line' ) ;
    public final EObject ruleHalfNewLineSegment() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;


        	enterRule();

        try {
            // InternalIdioms.g:1399:2: ( ( () otherlv_1= 'half-new-line' ) )
            // InternalIdioms.g:1400:2: ( () otherlv_1= 'half-new-line' )
            {
            // InternalIdioms.g:1400:2: ( () otherlv_1= 'half-new-line' )
            // InternalIdioms.g:1401:3: () otherlv_1= 'half-new-line'
            {
            // InternalIdioms.g:1401:3: ()
            // InternalIdioms.g:1402:4:
            {
            if ( state.backtracking==0 ) {

              				/* */

            }
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getHalfNewLineSegmentAccess().getHalfNewLineSegmentAction_0(),
              					current);

            }

            }

            otherlv_1=(Token)match(input,28,FollowSets000.FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getHalfNewLineSegmentAccess().getHalfNewLineKeyword_1());

            }

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleHalfNewLineSegment"


    // $ANTLR start "entryRuleNewLineSegment"
    // InternalIdioms.g:1419:1: entryRuleNewLineSegment returns [EObject current=null] : iv_ruleNewLineSegment= ruleNewLineSegment EOF ;
    public final EObject entryRuleNewLineSegment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNewLineSegment = null;


        try {
            // InternalIdioms.g:1419:55: (iv_ruleNewLineSegment= ruleNewLineSegment EOF )
            // InternalIdioms.g:1420:2: iv_ruleNewLineSegment= ruleNewLineSegment EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getNewLineSegmentRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleNewLineSegment=ruleNewLineSegment();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleNewLineSegment;
            }
            match(input,EOF,FollowSets000.FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleNewLineSegment"


    // $ANTLR start "ruleNewLineSegment"
    // InternalIdioms.g:1426:1: ruleNewLineSegment returns [EObject current=null] : ( () otherlv_1= 'new-line' ) ;
    public final EObject ruleNewLineSegment() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;


        	enterRule();

        try {
            // InternalIdioms.g:1432:2: ( ( () otherlv_1= 'new-line' ) )
            // InternalIdioms.g:1433:2: ( () otherlv_1= 'new-line' )
            {
            // InternalIdioms.g:1433:2: ( () otherlv_1= 'new-line' )
            // InternalIdioms.g:1434:3: () otherlv_1= 'new-line'
            {
            // InternalIdioms.g:1434:3: ()
            // InternalIdioms.g:1435:4:
            {
            if ( state.backtracking==0 ) {

              				/* */

            }
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getNewLineSegmentAccess().getNewLineSegmentAction_0(),
              					current);

            }

            }

            otherlv_1=(Token)match(input,29,FollowSets000.FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getNewLineSegmentAccess().getNewLineKeyword_1());

            }

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleNewLineSegment"


    // $ANTLR start "entryRuleNoNewLineSegment"
    // InternalIdioms.g:1452:1: entryRuleNoNewLineSegment returns [EObject current=null] : iv_ruleNoNewLineSegment= ruleNoNewLineSegment EOF ;
    public final EObject entryRuleNoNewLineSegment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNoNewLineSegment = null;


        try {
            // InternalIdioms.g:1452:57: (iv_ruleNoNewLineSegment= ruleNoNewLineSegment EOF )
            // InternalIdioms.g:1453:2: iv_ruleNoNewLineSegment= ruleNoNewLineSegment EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getNoNewLineSegmentRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleNoNewLineSegment=ruleNoNewLineSegment();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleNoNewLineSegment;
            }
            match(input,EOF,FollowSets000.FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleNoNewLineSegment"


    // $ANTLR start "ruleNoNewLineSegment"
    // InternalIdioms.g:1459:1: ruleNoNewLineSegment returns [EObject current=null] : ( () otherlv_1= 'no-new-line' ) ;
    public final EObject ruleNoNewLineSegment() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;


        	enterRule();

        try {
            // InternalIdioms.g:1465:2: ( ( () otherlv_1= 'no-new-line' ) )
            // InternalIdioms.g:1466:2: ( () otherlv_1= 'no-new-line' )
            {
            // InternalIdioms.g:1466:2: ( () otherlv_1= 'no-new-line' )
            // InternalIdioms.g:1467:3: () otherlv_1= 'no-new-line'
            {
            // InternalIdioms.g:1467:3: ()
            // InternalIdioms.g:1468:4:
            {
            if ( state.backtracking==0 ) {

              				/* */

            }
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getNoNewLineSegmentAccess().getNoNewLineSegmentAction_0(),
              					current);

            }

            }

            otherlv_1=(Token)match(input,30,FollowSets000.FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getNoNewLineSegmentAccess().getNoNewLineKeyword_1());

            }

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleNoNewLineSegment"


    // $ANTLR start "entryRuleNoSpaceSegment"
    // InternalIdioms.g:1485:1: entryRuleNoSpaceSegment returns [EObject current=null] : iv_ruleNoSpaceSegment= ruleNoSpaceSegment EOF ;
    public final EObject entryRuleNoSpaceSegment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNoSpaceSegment = null;


        try {
            // InternalIdioms.g:1485:55: (iv_ruleNoSpaceSegment= ruleNoSpaceSegment EOF )
            // InternalIdioms.g:1486:2: iv_ruleNoSpaceSegment= ruleNoSpaceSegment EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getNoSpaceSegmentRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleNoSpaceSegment=ruleNoSpaceSegment();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleNoSpaceSegment;
            }
            match(input,EOF,FollowSets000.FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleNoSpaceSegment"


    // $ANTLR start "ruleNoSpaceSegment"
    // InternalIdioms.g:1492:1: ruleNoSpaceSegment returns [EObject current=null] : ( () otherlv_1= 'no-space' ) ;
    public final EObject ruleNoSpaceSegment() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;


        	enterRule();

        try {
            // InternalIdioms.g:1498:2: ( ( () otherlv_1= 'no-space' ) )
            // InternalIdioms.g:1499:2: ( () otherlv_1= 'no-space' )
            {
            // InternalIdioms.g:1499:2: ( () otherlv_1= 'no-space' )
            // InternalIdioms.g:1500:3: () otherlv_1= 'no-space'
            {
            // InternalIdioms.g:1500:3: ()
            // InternalIdioms.g:1501:4:
            {
            if ( state.backtracking==0 ) {

              				/* */

            }
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getNoSpaceSegmentAccess().getNoSpaceSegmentAction_0(),
              					current);

            }

            }

            otherlv_1=(Token)match(input,31,FollowSets000.FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getNoSpaceSegmentAccess().getNoSpaceKeyword_1());

            }

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleNoSpaceSegment"


    // $ANTLR start "entryRulePopSegment"
    // InternalIdioms.g:1518:1: entryRulePopSegment returns [EObject current=null] : iv_rulePopSegment= rulePopSegment EOF ;
    public final EObject entryRulePopSegment() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePopSegment = null;


        try {
            // InternalIdioms.g:1518:51: (iv_rulePopSegment= rulePopSegment EOF )
            // InternalIdioms.g:1519:2: iv_rulePopSegment= rulePopSegment EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getPopSegmentRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_rulePopSegment=rulePopSegment();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_rulePopSegment;
            }
            match(input,EOF,FollowSets000.FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulePopSegment"


    // $ANTLR start "rulePopSegment"
    // InternalIdioms.g:1525:1: rulePopSegment returns [EObject current=null] : ( () otherlv_1= 'pop' ) ;
    public final EObject rulePopSegment() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;


        	enterRule();

        try {
            // InternalIdioms.g:1531:2: ( ( () otherlv_1= 'pop' ) )
            // InternalIdioms.g:1532:2: ( () otherlv_1= 'pop' )
            {
            // InternalIdioms.g:1532:2: ( () otherlv_1= 'pop' )
            // InternalIdioms.g:1533:3: () otherlv_1= 'pop'
            {
            // InternalIdioms.g:1533:3: ()
            // InternalIdioms.g:1534:4:
            {
            if ( state.backtracking==0 ) {

              				/* */

            }
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getPopSegmentAccess().getPopSegmentAction_0(),
              					current);

            }

            }

            otherlv_1=(Token)match(input,32,FollowSets000.FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getPopSegmentAccess().getPopKeyword_1());

            }

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePopSegment"


    // $ANTLR start "entryRulePostCommentSegment"
    // InternalIdioms.g:1551:1: entryRulePostCommentSegment returns [EObject current=null] : iv_rulePostCommentSegment= rulePostCommentSegment EOF ;
    public final EObject entryRulePostCommentSegment() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePostCommentSegment = null;


        try {
            // InternalIdioms.g:1551:59: (iv_rulePostCommentSegment= rulePostCommentSegment EOF )
            // InternalIdioms.g:1552:2: iv_rulePostCommentSegment= rulePostCommentSegment EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getPostCommentSegmentRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_rulePostCommentSegment=rulePostCommentSegment();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_rulePostCommentSegment;
            }
            match(input,EOF,FollowSets000.FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulePostCommentSegment"


    // $ANTLR start "rulePostCommentSegment"
    // InternalIdioms.g:1558:1: rulePostCommentSegment returns [EObject current=null] : ( () otherlv_1= 'post-comment' ) ;
    public final EObject rulePostCommentSegment() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;


        	enterRule();

        try {
            // InternalIdioms.g:1564:2: ( ( () otherlv_1= 'post-comment' ) )
            // InternalIdioms.g:1565:2: ( () otherlv_1= 'post-comment' )
            {
            // InternalIdioms.g:1565:2: ( () otherlv_1= 'post-comment' )
            // InternalIdioms.g:1566:3: () otherlv_1= 'post-comment'
            {
            // InternalIdioms.g:1566:3: ()
            // InternalIdioms.g:1567:4:
            {
            if ( state.backtracking==0 ) {

              				/* */

            }
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getPostCommentSegmentAccess().getPostCommentSegmentAction_0(),
              					current);

            }

            }

            otherlv_1=(Token)match(input,33,FollowSets000.FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getPostCommentSegmentAccess().getPostCommentKeyword_1());

            }

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePostCommentSegment"


    // $ANTLR start "entryRulePreCommentSegment"
    // InternalIdioms.g:1584:1: entryRulePreCommentSegment returns [EObject current=null] : iv_rulePreCommentSegment= rulePreCommentSegment EOF ;
    public final EObject entryRulePreCommentSegment() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePreCommentSegment = null;


        try {
            // InternalIdioms.g:1584:58: (iv_rulePreCommentSegment= rulePreCommentSegment EOF )
            // InternalIdioms.g:1585:2: iv_rulePreCommentSegment= rulePreCommentSegment EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getPreCommentSegmentRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_rulePreCommentSegment=rulePreCommentSegment();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_rulePreCommentSegment;
            }
            match(input,EOF,FollowSets000.FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulePreCommentSegment"


    // $ANTLR start "rulePreCommentSegment"
    // InternalIdioms.g:1591:1: rulePreCommentSegment returns [EObject current=null] : ( () otherlv_1= 'pre-comment' ) ;
    public final EObject rulePreCommentSegment() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;


        	enterRule();

        try {
            // InternalIdioms.g:1597:2: ( ( () otherlv_1= 'pre-comment' ) )
            // InternalIdioms.g:1598:2: ( () otherlv_1= 'pre-comment' )
            {
            // InternalIdioms.g:1598:2: ( () otherlv_1= 'pre-comment' )
            // InternalIdioms.g:1599:3: () otherlv_1= 'pre-comment'
            {
            // InternalIdioms.g:1599:3: ()
            // InternalIdioms.g:1600:4:
            {
            if ( state.backtracking==0 ) {

              				/* */

            }
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getPreCommentSegmentAccess().getPreCommentSegmentAction_0(),
              					current);

            }

            }

            otherlv_1=(Token)match(input,34,FollowSets000.FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getPreCommentSegmentAccess().getPreCommentKeyword_1());

            }

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePreCommentSegment"


    // $ANTLR start "entryRulePushSegment"
    // InternalIdioms.g:1617:1: entryRulePushSegment returns [EObject current=null] : iv_rulePushSegment= rulePushSegment EOF ;
    public final EObject entryRulePushSegment() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePushSegment = null;


        try {
            // InternalIdioms.g:1617:52: (iv_rulePushSegment= rulePushSegment EOF )
            // InternalIdioms.g:1618:2: iv_rulePushSegment= rulePushSegment EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getPushSegmentRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_rulePushSegment=rulePushSegment();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_rulePushSegment;
            }
            match(input,EOF,FollowSets000.FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulePushSegment"


    // $ANTLR start "rulePushSegment"
    // InternalIdioms.g:1624:1: rulePushSegment returns [EObject current=null] : ( () otherlv_1= 'push' ) ;
    public final EObject rulePushSegment() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;


        	enterRule();

        try {
            // InternalIdioms.g:1630:2: ( ( () otherlv_1= 'push' ) )
            // InternalIdioms.g:1631:2: ( () otherlv_1= 'push' )
            {
            // InternalIdioms.g:1631:2: ( () otherlv_1= 'push' )
            // InternalIdioms.g:1632:3: () otherlv_1= 'push'
            {
            // InternalIdioms.g:1632:3: ()
            // InternalIdioms.g:1633:4:
            {
            if ( state.backtracking==0 ) {

              				/* */

            }
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getPushSegmentAccess().getPushSegmentAction_0(),
              					current);

            }

            }

            otherlv_1=(Token)match(input,35,FollowSets000.FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getPushSegmentAccess().getPushKeyword_1());

            }

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePushSegment"


    // $ANTLR start "entryRuleSoftNewLineSegment"
    // InternalIdioms.g:1650:1: entryRuleSoftNewLineSegment returns [EObject current=null] : iv_ruleSoftNewLineSegment= ruleSoftNewLineSegment EOF ;
    public final EObject entryRuleSoftNewLineSegment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSoftNewLineSegment = null;


        try {
            // InternalIdioms.g:1650:59: (iv_ruleSoftNewLineSegment= ruleSoftNewLineSegment EOF )
            // InternalIdioms.g:1651:2: iv_ruleSoftNewLineSegment= ruleSoftNewLineSegment EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getSoftNewLineSegmentRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleSoftNewLineSegment=ruleSoftNewLineSegment();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleSoftNewLineSegment;
            }
            match(input,EOF,FollowSets000.FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleSoftNewLineSegment"


    // $ANTLR start "ruleSoftNewLineSegment"
    // InternalIdioms.g:1657:1: ruleSoftNewLineSegment returns [EObject current=null] : ( () otherlv_1= 'soft-new-line' ) ;
    public final EObject ruleSoftNewLineSegment() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;


        	enterRule();

        try {
            // InternalIdioms.g:1663:2: ( ( () otherlv_1= 'soft-new-line' ) )
            // InternalIdioms.g:1664:2: ( () otherlv_1= 'soft-new-line' )
            {
            // InternalIdioms.g:1664:2: ( () otherlv_1= 'soft-new-line' )
            // InternalIdioms.g:1665:3: () otherlv_1= 'soft-new-line'
            {
            // InternalIdioms.g:1665:3: ()
            // InternalIdioms.g:1666:4:
            {
            if ( state.backtracking==0 ) {

              				/* */

            }
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getSoftNewLineSegmentAccess().getSoftNewLineSegmentAction_0(),
              					current);

            }

            }

            otherlv_1=(Token)match(input,36,FollowSets000.FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getSoftNewLineSegmentAccess().getSoftNewLineKeyword_1());

            }

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSoftNewLineSegment"


    // $ANTLR start "entryRuleSoftSpaceSegment"
    // InternalIdioms.g:1683:1: entryRuleSoftSpaceSegment returns [EObject current=null] : iv_ruleSoftSpaceSegment= ruleSoftSpaceSegment EOF ;
    public final EObject entryRuleSoftSpaceSegment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSoftSpaceSegment = null;


        try {
            // InternalIdioms.g:1683:57: (iv_ruleSoftSpaceSegment= ruleSoftSpaceSegment EOF )
            // InternalIdioms.g:1684:2: iv_ruleSoftSpaceSegment= ruleSoftSpaceSegment EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getSoftSpaceSegmentRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleSoftSpaceSegment=ruleSoftSpaceSegment();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleSoftSpaceSegment;
            }
            match(input,EOF,FollowSets000.FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleSoftSpaceSegment"


    // $ANTLR start "ruleSoftSpaceSegment"
    // InternalIdioms.g:1690:1: ruleSoftSpaceSegment returns [EObject current=null] : ( () otherlv_1= 'soft-space' ) ;
    public final EObject ruleSoftSpaceSegment() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;


        	enterRule();

        try {
            // InternalIdioms.g:1696:2: ( ( () otherlv_1= 'soft-space' ) )
            // InternalIdioms.g:1697:2: ( () otherlv_1= 'soft-space' )
            {
            // InternalIdioms.g:1697:2: ( () otherlv_1= 'soft-space' )
            // InternalIdioms.g:1698:3: () otherlv_1= 'soft-space'
            {
            // InternalIdioms.g:1698:3: ()
            // InternalIdioms.g:1699:4:
            {
            if ( state.backtracking==0 ) {

              				/* */

            }
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getSoftSpaceSegmentAccess().getSoftSpaceSegmentAction_0(),
              					current);

            }

            }

            otherlv_1=(Token)match(input,37,FollowSets000.FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getSoftSpaceSegmentAccess().getSoftSpaceKeyword_1());

            }

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSoftSpaceSegment"


    // $ANTLR start "entryRuleStringSegment"
    // InternalIdioms.g:1716:1: entryRuleStringSegment returns [EObject current=null] : iv_ruleStringSegment= ruleStringSegment EOF ;
    public final EObject entryRuleStringSegment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleStringSegment = null;


        try {
            // InternalIdioms.g:1716:54: (iv_ruleStringSegment= ruleStringSegment EOF )
            // InternalIdioms.g:1717:2: iv_ruleStringSegment= ruleStringSegment EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getStringSegmentRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleStringSegment=ruleStringSegment();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleStringSegment;
            }
            match(input,EOF,FollowSets000.FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleStringSegment"


    // $ANTLR start "ruleStringSegment"
    // InternalIdioms.g:1723:1: ruleStringSegment returns [EObject current=null] : (otherlv_0= 'string' ( (lv_string_1_0= RULE_STRING ) ) ( (lv_printable_2_0= 'printable' ) )? ) ;
    public final EObject ruleStringSegment() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_string_1_0=null;
        Token lv_printable_2_0=null;


        	enterRule();

        try {
            // InternalIdioms.g:1729:2: ( (otherlv_0= 'string' ( (lv_string_1_0= RULE_STRING ) ) ( (lv_printable_2_0= 'printable' ) )? ) )
            // InternalIdioms.g:1730:2: (otherlv_0= 'string' ( (lv_string_1_0= RULE_STRING ) ) ( (lv_printable_2_0= 'printable' ) )? )
            {
            // InternalIdioms.g:1730:2: (otherlv_0= 'string' ( (lv_string_1_0= RULE_STRING ) ) ( (lv_printable_2_0= 'printable' ) )? )
            // InternalIdioms.g:1731:3: otherlv_0= 'string' ( (lv_string_1_0= RULE_STRING ) ) ( (lv_printable_2_0= 'printable' ) )?
            {
            otherlv_0=(Token)match(input,38,FollowSets000.FOLLOW_7); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getStringSegmentAccess().getStringKeyword_0());

            }
            // InternalIdioms.g:1735:3: ( (lv_string_1_0= RULE_STRING ) )
            // InternalIdioms.g:1736:4: (lv_string_1_0= RULE_STRING )
            {
            // InternalIdioms.g:1736:4: (lv_string_1_0= RULE_STRING )
            // InternalIdioms.g:1737:5: lv_string_1_0= RULE_STRING
            {
            lv_string_1_0=(Token)match(input,RULE_STRING,FollowSets000.FOLLOW_14); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					newLeafNode(lv_string_1_0, grammarAccess.getStringSegmentAccess().getStringSTRINGTerminalRuleCall_1_0());

            }
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElement(grammarAccess.getStringSegmentRule());
              					}
              					setWithLastConsumed(
              						current,
              						"string",
              						lv_string_1_0,
              						"org.eclipse.ocl.xtext.idioms.Idioms.STRING");

            }

            }


            }

            // InternalIdioms.g:1753:3: ( (lv_printable_2_0= 'printable' ) )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==39) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // InternalIdioms.g:1754:4: (lv_printable_2_0= 'printable' )
                    {
                    // InternalIdioms.g:1754:4: (lv_printable_2_0= 'printable' )
                    // InternalIdioms.g:1755:5: lv_printable_2_0= 'printable'
                    {
                    lv_printable_2_0=(Token)match(input,39,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					newLeafNode(lv_printable_2_0, grammarAccess.getStringSegmentAccess().getPrintablePrintableKeyword_2_0());

                    }
                    if ( state.backtracking==0 ) {

                      					if (current==null) {
                      						current = createModelElement(grammarAccess.getStringSegmentRule());
                      					}
                      					setWithLastConsumed(current, "printable", lv_printable_2_0 != null, "printable");

                    }

                    }


                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleStringSegment"


    // $ANTLR start "entryRuleValueSegment"
    // InternalIdioms.g:1771:1: entryRuleValueSegment returns [EObject current=null] : iv_ruleValueSegment= ruleValueSegment EOF ;
    public final EObject entryRuleValueSegment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleValueSegment = null;


        try {
            // InternalIdioms.g:1771:53: (iv_ruleValueSegment= ruleValueSegment EOF )
            // InternalIdioms.g:1772:2: iv_ruleValueSegment= ruleValueSegment EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getValueSegmentRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleValueSegment=ruleValueSegment();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleValueSegment;
            }
            match(input,EOF,FollowSets000.FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleValueSegment"


    // $ANTLR start "ruleValueSegment"
    // InternalIdioms.g:1778:1: ruleValueSegment returns [EObject current=null] : ( () otherlv_1= 'value' ) ;
    public final EObject ruleValueSegment() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;


        	enterRule();

        try {
            // InternalIdioms.g:1784:2: ( ( () otherlv_1= 'value' ) )
            // InternalIdioms.g:1785:2: ( () otherlv_1= 'value' )
            {
            // InternalIdioms.g:1785:2: ( () otherlv_1= 'value' )
            // InternalIdioms.g:1786:3: () otherlv_1= 'value'
            {
            // InternalIdioms.g:1786:3: ()
            // InternalIdioms.g:1787:4:
            {
            if ( state.backtracking==0 ) {

              				/* */

            }
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getValueSegmentAccess().getValueSegmentAction_0(),
              					current);

            }

            }

            otherlv_1=(Token)match(input,40,FollowSets000.FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getValueSegmentAccess().getValueKeyword_1());

            }

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleValueSegment"


    // $ANTLR start "entryRuleWrapAnchorSegment"
    // InternalIdioms.g:1804:1: entryRuleWrapAnchorSegment returns [EObject current=null] : iv_ruleWrapAnchorSegment= ruleWrapAnchorSegment EOF ;
    public final EObject entryRuleWrapAnchorSegment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleWrapAnchorSegment = null;


        try {
            // InternalIdioms.g:1804:58: (iv_ruleWrapAnchorSegment= ruleWrapAnchorSegment EOF )
            // InternalIdioms.g:1805:2: iv_ruleWrapAnchorSegment= ruleWrapAnchorSegment EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getWrapAnchorSegmentRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleWrapAnchorSegment=ruleWrapAnchorSegment();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleWrapAnchorSegment;
            }
            match(input,EOF,FollowSets000.FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleWrapAnchorSegment"


    // $ANTLR start "ruleWrapAnchorSegment"
    // InternalIdioms.g:1811:1: ruleWrapAnchorSegment returns [EObject current=null] : ( () otherlv_1= 'wrap-anchor' ) ;
    public final EObject ruleWrapAnchorSegment() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;


        	enterRule();

        try {
            // InternalIdioms.g:1817:2: ( ( () otherlv_1= 'wrap-anchor' ) )
            // InternalIdioms.g:1818:2: ( () otherlv_1= 'wrap-anchor' )
            {
            // InternalIdioms.g:1818:2: ( () otherlv_1= 'wrap-anchor' )
            // InternalIdioms.g:1819:3: () otherlv_1= 'wrap-anchor'
            {
            // InternalIdioms.g:1819:3: ()
            // InternalIdioms.g:1820:4:
            {
            if ( state.backtracking==0 ) {

              				/* */

            }
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getWrapAnchorSegmentAccess().getWrapAnchorSegmentAction_0(),
              					current);

            }

            }

            otherlv_1=(Token)match(input,41,FollowSets000.FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getWrapAnchorSegmentAccess().getWrapAnchorKeyword_1());

            }

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleWrapAnchorSegment"


    // $ANTLR start "entryRuleWrapBeginAllSegment"
    // InternalIdioms.g:1837:1: entryRuleWrapBeginAllSegment returns [EObject current=null] : iv_ruleWrapBeginAllSegment= ruleWrapBeginAllSegment EOF ;
    public final EObject entryRuleWrapBeginAllSegment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleWrapBeginAllSegment = null;


        try {
            // InternalIdioms.g:1837:60: (iv_ruleWrapBeginAllSegment= ruleWrapBeginAllSegment EOF )
            // InternalIdioms.g:1838:2: iv_ruleWrapBeginAllSegment= ruleWrapBeginAllSegment EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getWrapBeginAllSegmentRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleWrapBeginAllSegment=ruleWrapBeginAllSegment();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleWrapBeginAllSegment;
            }
            match(input,EOF,FollowSets000.FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleWrapBeginAllSegment"


    // $ANTLR start "ruleWrapBeginAllSegment"
    // InternalIdioms.g:1844:1: ruleWrapBeginAllSegment returns [EObject current=null] : ( () otherlv_1= 'wrap-begin-all' ) ;
    public final EObject ruleWrapBeginAllSegment() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;


        	enterRule();

        try {
            // InternalIdioms.g:1850:2: ( ( () otherlv_1= 'wrap-begin-all' ) )
            // InternalIdioms.g:1851:2: ( () otherlv_1= 'wrap-begin-all' )
            {
            // InternalIdioms.g:1851:2: ( () otherlv_1= 'wrap-begin-all' )
            // InternalIdioms.g:1852:3: () otherlv_1= 'wrap-begin-all'
            {
            // InternalIdioms.g:1852:3: ()
            // InternalIdioms.g:1853:4:
            {
            if ( state.backtracking==0 ) {

              				/* */

            }
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getWrapBeginAllSegmentAccess().getWrapBeginAllSegmentAction_0(),
              					current);

            }

            }

            otherlv_1=(Token)match(input,42,FollowSets000.FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getWrapBeginAllSegmentAccess().getWrapBeginAllKeyword_1());

            }

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleWrapBeginAllSegment"


    // $ANTLR start "entryRuleWrapBeginSomeSegment"
    // InternalIdioms.g:1870:1: entryRuleWrapBeginSomeSegment returns [EObject current=null] : iv_ruleWrapBeginSomeSegment= ruleWrapBeginSomeSegment EOF ;
    public final EObject entryRuleWrapBeginSomeSegment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleWrapBeginSomeSegment = null;


        try {
            // InternalIdioms.g:1870:61: (iv_ruleWrapBeginSomeSegment= ruleWrapBeginSomeSegment EOF )
            // InternalIdioms.g:1871:2: iv_ruleWrapBeginSomeSegment= ruleWrapBeginSomeSegment EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getWrapBeginSomeSegmentRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleWrapBeginSomeSegment=ruleWrapBeginSomeSegment();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleWrapBeginSomeSegment;
            }
            match(input,EOF,FollowSets000.FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleWrapBeginSomeSegment"


    // $ANTLR start "ruleWrapBeginSomeSegment"
    // InternalIdioms.g:1877:1: ruleWrapBeginSomeSegment returns [EObject current=null] : ( () otherlv_1= 'wrap-begin-some' ) ;
    public final EObject ruleWrapBeginSomeSegment() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;


        	enterRule();

        try {
            // InternalIdioms.g:1883:2: ( ( () otherlv_1= 'wrap-begin-some' ) )
            // InternalIdioms.g:1884:2: ( () otherlv_1= 'wrap-begin-some' )
            {
            // InternalIdioms.g:1884:2: ( () otherlv_1= 'wrap-begin-some' )
            // InternalIdioms.g:1885:3: () otherlv_1= 'wrap-begin-some'
            {
            // InternalIdioms.g:1885:3: ()
            // InternalIdioms.g:1886:4:
            {
            if ( state.backtracking==0 ) {

              				/* */

            }
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getWrapBeginSomeSegmentAccess().getWrapBeginSomeSegmentAction_0(),
              					current);

            }

            }

            otherlv_1=(Token)match(input,43,FollowSets000.FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getWrapBeginSomeSegmentAccess().getWrapBeginSomeKeyword_1());

            }

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleWrapBeginSomeSegment"


    // $ANTLR start "entryRuleWrapEndSegment"
    // InternalIdioms.g:1903:1: entryRuleWrapEndSegment returns [EObject current=null] : iv_ruleWrapEndSegment= ruleWrapEndSegment EOF ;
    public final EObject entryRuleWrapEndSegment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleWrapEndSegment = null;


        try {
            // InternalIdioms.g:1903:55: (iv_ruleWrapEndSegment= ruleWrapEndSegment EOF )
            // InternalIdioms.g:1904:2: iv_ruleWrapEndSegment= ruleWrapEndSegment EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getWrapEndSegmentRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleWrapEndSegment=ruleWrapEndSegment();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleWrapEndSegment;
            }
            match(input,EOF,FollowSets000.FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleWrapEndSegment"


    // $ANTLR start "ruleWrapEndSegment"
    // InternalIdioms.g:1910:1: ruleWrapEndSegment returns [EObject current=null] : ( () otherlv_1= 'wrap-end' ) ;
    public final EObject ruleWrapEndSegment() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;


        	enterRule();

        try {
            // InternalIdioms.g:1916:2: ( ( () otherlv_1= 'wrap-end' ) )
            // InternalIdioms.g:1917:2: ( () otherlv_1= 'wrap-end' )
            {
            // InternalIdioms.g:1917:2: ( () otherlv_1= 'wrap-end' )
            // InternalIdioms.g:1918:3: () otherlv_1= 'wrap-end'
            {
            // InternalIdioms.g:1918:3: ()
            // InternalIdioms.g:1919:4:
            {
            if ( state.backtracking==0 ) {

              				/* */

            }
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getWrapEndSegmentAccess().getWrapEndSegmentAction_0(),
              					current);

            }

            }

            otherlv_1=(Token)match(input,44,FollowSets000.FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getWrapEndSegmentAccess().getWrapEndKeyword_1());

            }

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleWrapEndSegment"


    // $ANTLR start "entryRuleWrapHereSegment"
    // InternalIdioms.g:1936:1: entryRuleWrapHereSegment returns [EObject current=null] : iv_ruleWrapHereSegment= ruleWrapHereSegment EOF ;
    public final EObject entryRuleWrapHereSegment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleWrapHereSegment = null;


        try {
            // InternalIdioms.g:1936:56: (iv_ruleWrapHereSegment= ruleWrapHereSegment EOF )
            // InternalIdioms.g:1937:2: iv_ruleWrapHereSegment= ruleWrapHereSegment EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getWrapHereSegmentRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleWrapHereSegment=ruleWrapHereSegment();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleWrapHereSegment;
            }
            match(input,EOF,FollowSets000.FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleWrapHereSegment"


    // $ANTLR start "ruleWrapHereSegment"
    // InternalIdioms.g:1943:1: ruleWrapHereSegment returns [EObject current=null] : ( () otherlv_1= 'wrap-here' ) ;
    public final EObject ruleWrapHereSegment() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;


        	enterRule();

        try {
            // InternalIdioms.g:1949:2: ( ( () otherlv_1= 'wrap-here' ) )
            // InternalIdioms.g:1950:2: ( () otherlv_1= 'wrap-here' )
            {
            // InternalIdioms.g:1950:2: ( () otherlv_1= 'wrap-here' )
            // InternalIdioms.g:1951:3: () otherlv_1= 'wrap-here'
            {
            // InternalIdioms.g:1951:3: ()
            // InternalIdioms.g:1952:4:
            {
            if ( state.backtracking==0 ) {

              				/* */

            }
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getWrapHereSegmentAccess().getWrapHereSegmentAction_0(),
              					current);

            }

            }

            otherlv_1=(Token)match(input,45,FollowSets000.FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getWrapHereSegmentAccess().getWrapHereKeyword_1());

            }

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleWrapHereSegment"


    // $ANTLR start "entryRuleReferredSegment"
    // InternalIdioms.g:1969:1: entryRuleReferredSegment returns [EObject current=null] : iv_ruleReferredSegment= ruleReferredSegment EOF ;
    public final EObject entryRuleReferredSegment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleReferredSegment = null;


        try {
            // InternalIdioms.g:1969:56: (iv_ruleReferredSegment= ruleReferredSegment EOF )
            // InternalIdioms.g:1970:2: iv_ruleReferredSegment= ruleReferredSegment EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getReferredSegmentRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleReferredSegment=ruleReferredSegment();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleReferredSegment;
            }
            match(input,EOF,FollowSets000.FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleReferredSegment"


    // $ANTLR start "ruleReferredSegment"
    // InternalIdioms.g:1976:1: ruleReferredSegment returns [EObject current=null] : ( ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '::' )? ( (otherlv_2= RULE_ID ) ) ) ;
    public final EObject ruleReferredSegment() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;


        	enterRule();

        try {
            // InternalIdioms.g:1982:2: ( ( ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '::' )? ( (otherlv_2= RULE_ID ) ) ) )
            // InternalIdioms.g:1983:2: ( ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '::' )? ( (otherlv_2= RULE_ID ) ) )
            {
            // InternalIdioms.g:1983:2: ( ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '::' )? ( (otherlv_2= RULE_ID ) ) )
            // InternalIdioms.g:1984:3: ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '::' )? ( (otherlv_2= RULE_ID ) )
            {
            // InternalIdioms.g:1984:3: ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '::' )?
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
                    // InternalIdioms.g:1985:4: ( (otherlv_0= RULE_ID ) ) otherlv_1= '::'
                    {
                    // InternalIdioms.g:1985:4: ( (otherlv_0= RULE_ID ) )
                    // InternalIdioms.g:1986:5: (otherlv_0= RULE_ID )
                    {
                    // InternalIdioms.g:1986:5: (otherlv_0= RULE_ID )
                    // InternalIdioms.g:1987:6: otherlv_0= RULE_ID
                    {
                    if ( state.backtracking==0 ) {

                      						/* */

                    }
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElement(grammarAccess.getReferredSegmentRule());
                      						}

                    }
                    otherlv_0=(Token)match(input,RULE_ID,FollowSets000.FOLLOW_12); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						newLeafNode(otherlv_0, grammarAccess.getReferredSegmentAccess().getIdiomsModelIdiomsModelCrossReference_0_0_0());

                    }

                    }


                    }

                    otherlv_1=(Token)match(input,22,FollowSets000.FOLLOW_3); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_1, grammarAccess.getReferredSegmentAccess().getColonColonKeyword_0_1());

                    }

                    }
                    break;

            }

            // InternalIdioms.g:2006:3: ( (otherlv_2= RULE_ID ) )
            // InternalIdioms.g:2007:4: (otherlv_2= RULE_ID )
            {
            // InternalIdioms.g:2007:4: (otherlv_2= RULE_ID )
            // InternalIdioms.g:2008:5: otherlv_2= RULE_ID
            {
            if ( state.backtracking==0 ) {

              					/* */

            }
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElement(grammarAccess.getReferredSegmentRule());
              					}

            }
            otherlv_2=(Token)match(input,RULE_ID,FollowSets000.FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					newLeafNode(otherlv_2, grammarAccess.getReferredSegmentAccess().getSegmentDeclarationSegmentDeclarationCrossReference_1_0());

            }

            }


            }


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleReferredSegment"


    // $ANTLR start "entryRuleIdiom"
    // InternalIdioms.g:2026:1: entryRuleIdiom returns [EObject current=null] : iv_ruleIdiom= ruleIdiom EOF ;
    public final EObject entryRuleIdiom() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIdiom = null;


        try {
            // InternalIdioms.g:2026:46: (iv_ruleIdiom= ruleIdiom EOF )
            // InternalIdioms.g:2027:2: iv_ruleIdiom= ruleIdiom EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getIdiomRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleIdiom=ruleIdiom();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleIdiom;
            }
            match(input,EOF,FollowSets000.FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleIdiom"


    // $ANTLR start "ruleIdiom"
    // InternalIdioms.g:2033:1: ruleIdiom returns [EObject current=null] : ( ( (lv_mixin_0_0= 'mixin' ) )? otherlv_1= 'idiom' ( (lv_name_2_0= RULE_ID ) ) (otherlv_3= 'for' ( ( (otherlv_4= RULE_ID ) ) otherlv_5= '::' )? ( (otherlv_6= RULE_ID ) ) )? (otherlv_7= 'in' ( (lv_inRuleRegex_8_0= RULE_STRING ) ) )? ( ( (lv_ownedSubIdioms_9_0= ruleSubIdiom ) ) | (otherlv_10= '{' ( (lv_ownedSubIdioms_11_0= ruleSubIdiom ) )* otherlv_12= '}' ) ) ) ;
    public final EObject ruleIdiom() throws RecognitionException {
        EObject current = null;

        Token lv_mixin_0_0=null;
        Token otherlv_1=null;
        Token lv_name_2_0=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Token otherlv_6=null;
        Token otherlv_7=null;
        Token lv_inRuleRegex_8_0=null;
        Token otherlv_10=null;
        Token otherlv_12=null;
        EObject lv_ownedSubIdioms_9_0 = null;

        EObject lv_ownedSubIdioms_11_0 = null;



        	enterRule();

        try {
            // InternalIdioms.g:2039:2: ( ( ( (lv_mixin_0_0= 'mixin' ) )? otherlv_1= 'idiom' ( (lv_name_2_0= RULE_ID ) ) (otherlv_3= 'for' ( ( (otherlv_4= RULE_ID ) ) otherlv_5= '::' )? ( (otherlv_6= RULE_ID ) ) )? (otherlv_7= 'in' ( (lv_inRuleRegex_8_0= RULE_STRING ) ) )? ( ( (lv_ownedSubIdioms_9_0= ruleSubIdiom ) ) | (otherlv_10= '{' ( (lv_ownedSubIdioms_11_0= ruleSubIdiom ) )* otherlv_12= '}' ) ) ) )
            // InternalIdioms.g:2040:2: ( ( (lv_mixin_0_0= 'mixin' ) )? otherlv_1= 'idiom' ( (lv_name_2_0= RULE_ID ) ) (otherlv_3= 'for' ( ( (otherlv_4= RULE_ID ) ) otherlv_5= '::' )? ( (otherlv_6= RULE_ID ) ) )? (otherlv_7= 'in' ( (lv_inRuleRegex_8_0= RULE_STRING ) ) )? ( ( (lv_ownedSubIdioms_9_0= ruleSubIdiom ) ) | (otherlv_10= '{' ( (lv_ownedSubIdioms_11_0= ruleSubIdiom ) )* otherlv_12= '}' ) ) )
            {
            // InternalIdioms.g:2040:2: ( ( (lv_mixin_0_0= 'mixin' ) )? otherlv_1= 'idiom' ( (lv_name_2_0= RULE_ID ) ) (otherlv_3= 'for' ( ( (otherlv_4= RULE_ID ) ) otherlv_5= '::' )? ( (otherlv_6= RULE_ID ) ) )? (otherlv_7= 'in' ( (lv_inRuleRegex_8_0= RULE_STRING ) ) )? ( ( (lv_ownedSubIdioms_9_0= ruleSubIdiom ) ) | (otherlv_10= '{' ( (lv_ownedSubIdioms_11_0= ruleSubIdiom ) )* otherlv_12= '}' ) ) )
            // InternalIdioms.g:2041:3: ( (lv_mixin_0_0= 'mixin' ) )? otherlv_1= 'idiom' ( (lv_name_2_0= RULE_ID ) ) (otherlv_3= 'for' ( ( (otherlv_4= RULE_ID ) ) otherlv_5= '::' )? ( (otherlv_6= RULE_ID ) ) )? (otherlv_7= 'in' ( (lv_inRuleRegex_8_0= RULE_STRING ) ) )? ( ( (lv_ownedSubIdioms_9_0= ruleSubIdiom ) ) | (otherlv_10= '{' ( (lv_ownedSubIdioms_11_0= ruleSubIdiom ) )* otherlv_12= '}' ) )
            {
            // InternalIdioms.g:2041:3: ( (lv_mixin_0_0= 'mixin' ) )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==46) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // InternalIdioms.g:2042:4: (lv_mixin_0_0= 'mixin' )
                    {
                    // InternalIdioms.g:2042:4: (lv_mixin_0_0= 'mixin' )
                    // InternalIdioms.g:2043:5: lv_mixin_0_0= 'mixin'
                    {
                    lv_mixin_0_0=(Token)match(input,46,FollowSets000.FOLLOW_15); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					newLeafNode(lv_mixin_0_0, grammarAccess.getIdiomAccess().getMixinMixinKeyword_0_0());

                    }
                    if ( state.backtracking==0 ) {

                      					if (current==null) {
                      						current = createModelElement(grammarAccess.getIdiomRule());
                      					}
                      					setWithLastConsumed(current, "mixin", lv_mixin_0_0 != null, "mixin");

                    }

                    }


                    }
                    break;

            }

            otherlv_1=(Token)match(input,47,FollowSets000.FOLLOW_3); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getIdiomAccess().getIdiomKeyword_1());

            }
            // InternalIdioms.g:2059:3: ( (lv_name_2_0= RULE_ID ) )
            // InternalIdioms.g:2060:4: (lv_name_2_0= RULE_ID )
            {
            // InternalIdioms.g:2060:4: (lv_name_2_0= RULE_ID )
            // InternalIdioms.g:2061:5: lv_name_2_0= RULE_ID
            {
            lv_name_2_0=(Token)match(input,RULE_ID,FollowSets000.FOLLOW_16); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					newLeafNode(lv_name_2_0, grammarAccess.getIdiomAccess().getNameIDTerminalRuleCall_2_0());

            }
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElement(grammarAccess.getIdiomRule());
              					}
              					setWithLastConsumed(
              						current,
              						"name",
              						lv_name_2_0,
              						"org.eclipse.ocl.xtext.idioms.Idioms.ID");

            }

            }


            }

            // InternalIdioms.g:2077:3: (otherlv_3= 'for' ( ( (otherlv_4= RULE_ID ) ) otherlv_5= '::' )? ( (otherlv_6= RULE_ID ) ) )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==48) ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // InternalIdioms.g:2078:4: otherlv_3= 'for' ( ( (otherlv_4= RULE_ID ) ) otherlv_5= '::' )? ( (otherlv_6= RULE_ID ) )
                    {
                    otherlv_3=(Token)match(input,48,FollowSets000.FOLLOW_3); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_3, grammarAccess.getIdiomAccess().getForKeyword_3_0());

                    }
                    // InternalIdioms.g:2082:4: ( ( (otherlv_4= RULE_ID ) ) otherlv_5= '::' )?
                    int alt20=2;
                    int LA20_0 = input.LA(1);

                    if ( (LA20_0==RULE_ID) ) {
                        int LA20_1 = input.LA(2);

                        if ( (LA20_1==22) ) {
                            alt20=1;
                        }
                    }
                    switch (alt20) {
                        case 1 :
                            // InternalIdioms.g:2083:5: ( (otherlv_4= RULE_ID ) ) otherlv_5= '::'
                            {
                            // InternalIdioms.g:2083:5: ( (otherlv_4= RULE_ID ) )
                            // InternalIdioms.g:2084:6: (otherlv_4= RULE_ID )
                            {
                            // InternalIdioms.g:2084:6: (otherlv_4= RULE_ID )
                            // InternalIdioms.g:2085:7: otherlv_4= RULE_ID
                            {
                            if ( state.backtracking==0 ) {

                              							/* */

                            }
                            if ( state.backtracking==0 ) {

                              							if (current==null) {
                              								current = createModelElement(grammarAccess.getIdiomRule());
                              							}

                            }
                            otherlv_4=(Token)match(input,RULE_ID,FollowSets000.FOLLOW_12); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              							newLeafNode(otherlv_4, grammarAccess.getIdiomAccess().getForEPackageEPackageCrossReference_3_1_0_0());

                            }

                            }


                            }

                            otherlv_5=(Token)match(input,22,FollowSets000.FOLLOW_3); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              					newLeafNode(otherlv_5, grammarAccess.getIdiomAccess().getColonColonKeyword_3_1_1());

                            }

                            }
                            break;

                    }

                    // InternalIdioms.g:2104:4: ( (otherlv_6= RULE_ID ) )
                    // InternalIdioms.g:2105:5: (otherlv_6= RULE_ID )
                    {
                    // InternalIdioms.g:2105:5: (otherlv_6= RULE_ID )
                    // InternalIdioms.g:2106:6: otherlv_6= RULE_ID
                    {
                    if ( state.backtracking==0 ) {

                      						/* */

                    }
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElement(grammarAccess.getIdiomRule());
                      						}

                    }
                    otherlv_6=(Token)match(input,RULE_ID,FollowSets000.FOLLOW_17); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						newLeafNode(otherlv_6, grammarAccess.getIdiomAccess().getForEClassEClassCrossReference_3_2_0());

                    }

                    }


                    }


                    }
                    break;

            }

            // InternalIdioms.g:2121:3: (otherlv_7= 'in' ( (lv_inRuleRegex_8_0= RULE_STRING ) ) )?
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==49) ) {
                alt22=1;
            }
            switch (alt22) {
                case 1 :
                    // InternalIdioms.g:2122:4: otherlv_7= 'in' ( (lv_inRuleRegex_8_0= RULE_STRING ) )
                    {
                    otherlv_7=(Token)match(input,49,FollowSets000.FOLLOW_7); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_7, grammarAccess.getIdiomAccess().getInKeyword_4_0());

                    }
                    // InternalIdioms.g:2126:4: ( (lv_inRuleRegex_8_0= RULE_STRING ) )
                    // InternalIdioms.g:2127:5: (lv_inRuleRegex_8_0= RULE_STRING )
                    {
                    // InternalIdioms.g:2127:5: (lv_inRuleRegex_8_0= RULE_STRING )
                    // InternalIdioms.g:2128:6: lv_inRuleRegex_8_0= RULE_STRING
                    {
                    lv_inRuleRegex_8_0=(Token)match(input,RULE_STRING,FollowSets000.FOLLOW_18); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						newLeafNode(lv_inRuleRegex_8_0, grammarAccess.getIdiomAccess().getInRuleRegexSTRINGTerminalRuleCall_4_1_0());

                    }
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElement(grammarAccess.getIdiomRule());
                      						}
                      						setWithLastConsumed(
                      							current,
                      							"inRuleRegex",
                      							lv_inRuleRegex_8_0,
                      							"org.eclipse.ocl.xtext.idioms.Idioms.STRING");

                    }

                    }


                    }


                    }
                    break;

            }

            // InternalIdioms.g:2145:3: ( ( (lv_ownedSubIdioms_9_0= ruleSubIdiom ) ) | (otherlv_10= '{' ( (lv_ownedSubIdioms_11_0= ruleSubIdiom ) )* otherlv_12= '}' ) )
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( (LA24_0==52) ) {
                alt24=1;
            }
            else if ( (LA24_0==50) ) {
                alt24=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 24, 0, input);

                throw nvae;
            }
            switch (alt24) {
                case 1 :
                    // InternalIdioms.g:2146:4: ( (lv_ownedSubIdioms_9_0= ruleSubIdiom ) )
                    {
                    // InternalIdioms.g:2146:4: ( (lv_ownedSubIdioms_9_0= ruleSubIdiom ) )
                    // InternalIdioms.g:2147:5: (lv_ownedSubIdioms_9_0= ruleSubIdiom )
                    {
                    // InternalIdioms.g:2147:5: (lv_ownedSubIdioms_9_0= ruleSubIdiom )
                    // InternalIdioms.g:2148:6: lv_ownedSubIdioms_9_0= ruleSubIdiom
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getIdiomAccess().getOwnedSubIdiomsSubIdiomParserRuleCall_5_0_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    lv_ownedSubIdioms_9_0=ruleSubIdiom();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElementForParent(grammarAccess.getIdiomRule());
                      						}
                      						add(
                      							current,
                      							"ownedSubIdioms",
                      							lv_ownedSubIdioms_9_0,
                      							"org.eclipse.ocl.xtext.idioms.Idioms.SubIdiom");
                      						afterParserOrEnumRuleCall();

                    }

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalIdioms.g:2166:4: (otherlv_10= '{' ( (lv_ownedSubIdioms_11_0= ruleSubIdiom ) )* otherlv_12= '}' )
                    {
                    // InternalIdioms.g:2166:4: (otherlv_10= '{' ( (lv_ownedSubIdioms_11_0= ruleSubIdiom ) )* otherlv_12= '}' )
                    // InternalIdioms.g:2167:5: otherlv_10= '{' ( (lv_ownedSubIdioms_11_0= ruleSubIdiom ) )* otherlv_12= '}'
                    {
                    otherlv_10=(Token)match(input,50,FollowSets000.FOLLOW_19); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					newLeafNode(otherlv_10, grammarAccess.getIdiomAccess().getLeftCurlyBracketKeyword_5_1_0());

                    }
                    // InternalIdioms.g:2171:5: ( (lv_ownedSubIdioms_11_0= ruleSubIdiom ) )*
                    loop23:
                    do {
                        int alt23=2;
                        int LA23_0 = input.LA(1);

                        if ( (LA23_0==52) ) {
                            alt23=1;
                        }


                        switch (alt23) {
                    	case 1 :
                    	    // InternalIdioms.g:2172:6: (lv_ownedSubIdioms_11_0= ruleSubIdiom )
                    	    {
                    	    // InternalIdioms.g:2172:6: (lv_ownedSubIdioms_11_0= ruleSubIdiom )
                    	    // InternalIdioms.g:2173:7: lv_ownedSubIdioms_11_0= ruleSubIdiom
                    	    {
                    	    if ( state.backtracking==0 ) {

                    	      							newCompositeNode(grammarAccess.getIdiomAccess().getOwnedSubIdiomsSubIdiomParserRuleCall_5_1_1_0());

                    	    }
                    	    pushFollow(FollowSets000.FOLLOW_19);
                    	    lv_ownedSubIdioms_11_0=ruleSubIdiom();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      							if (current==null) {
                    	      								current = createModelElementForParent(grammarAccess.getIdiomRule());
                    	      							}
                    	      							add(
                    	      								current,
                    	      								"ownedSubIdioms",
                    	      								lv_ownedSubIdioms_11_0,
                    	      								"org.eclipse.ocl.xtext.idioms.Idioms.SubIdiom");
                    	      							afterParserOrEnumRuleCall();

                    	    }

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop23;
                        }
                    } while (true);

                    otherlv_12=(Token)match(input,51,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					newLeafNode(otherlv_12, grammarAccess.getIdiomAccess().getRightCurlyBracketKeyword_5_1_2());

                    }

                    }


                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleIdiom"


    // $ANTLR start "entryRuleSubIdiom"
    // InternalIdioms.g:2200:1: entryRuleSubIdiom returns [EObject current=null] : iv_ruleSubIdiom= ruleSubIdiom EOF ;
    public final EObject entryRuleSubIdiom() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSubIdiom = null;


        try {
            // InternalIdioms.g:2200:49: (iv_ruleSubIdiom= ruleSubIdiom EOF )
            // InternalIdioms.g:2201:2: iv_ruleSubIdiom= ruleSubIdiom EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getSubIdiomRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleSubIdiom=ruleSubIdiom();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleSubIdiom;
            }
            match(input,EOF,FollowSets000.FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleSubIdiom"


    // $ANTLR start "ruleSubIdiom"
    // InternalIdioms.g:2207:1: ruleSubIdiom returns [EObject current=null] : (otherlv_0= 'at' ( ( (lv_all_1_0= 'all' ) ) | otherlv_2= 'each' )? ( (lv_ownedLocator_3_0= ruleLocator ) ) (otherlv_4= 'do' ( ( (lv_ownedSegments_5_1= ruleSegment | lv_ownedSegments_5_2= ruleReferredSegment ) ) )* )? otherlv_6= ';' ) ;
    public final EObject ruleSubIdiom() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_all_1_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        EObject lv_ownedLocator_3_0 = null;

        EObject lv_ownedSegments_5_1 = null;

        EObject lv_ownedSegments_5_2 = null;



        	enterRule();

        try {
            // InternalIdioms.g:2213:2: ( (otherlv_0= 'at' ( ( (lv_all_1_0= 'all' ) ) | otherlv_2= 'each' )? ( (lv_ownedLocator_3_0= ruleLocator ) ) (otherlv_4= 'do' ( ( (lv_ownedSegments_5_1= ruleSegment | lv_ownedSegments_5_2= ruleReferredSegment ) ) )* )? otherlv_6= ';' ) )
            // InternalIdioms.g:2214:2: (otherlv_0= 'at' ( ( (lv_all_1_0= 'all' ) ) | otherlv_2= 'each' )? ( (lv_ownedLocator_3_0= ruleLocator ) ) (otherlv_4= 'do' ( ( (lv_ownedSegments_5_1= ruleSegment | lv_ownedSegments_5_2= ruleReferredSegment ) ) )* )? otherlv_6= ';' )
            {
            // InternalIdioms.g:2214:2: (otherlv_0= 'at' ( ( (lv_all_1_0= 'all' ) ) | otherlv_2= 'each' )? ( (lv_ownedLocator_3_0= ruleLocator ) ) (otherlv_4= 'do' ( ( (lv_ownedSegments_5_1= ruleSegment | lv_ownedSegments_5_2= ruleReferredSegment ) ) )* )? otherlv_6= ';' )
            // InternalIdioms.g:2215:3: otherlv_0= 'at' ( ( (lv_all_1_0= 'all' ) ) | otherlv_2= 'each' )? ( (lv_ownedLocator_3_0= ruleLocator ) ) (otherlv_4= 'do' ( ( (lv_ownedSegments_5_1= ruleSegment | lv_ownedSegments_5_2= ruleReferredSegment ) ) )* )? otherlv_6= ';'
            {
            otherlv_0=(Token)match(input,52,FollowSets000.FOLLOW_20); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getSubIdiomAccess().getAtKeyword_0());

            }
            // InternalIdioms.g:2219:3: ( ( (lv_all_1_0= 'all' ) ) | otherlv_2= 'each' )?
            int alt25=3;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==53) ) {
                alt25=1;
            }
            else if ( (LA25_0==54) ) {
                alt25=2;
            }
            switch (alt25) {
                case 1 :
                    // InternalIdioms.g:2220:4: ( (lv_all_1_0= 'all' ) )
                    {
                    // InternalIdioms.g:2220:4: ( (lv_all_1_0= 'all' ) )
                    // InternalIdioms.g:2221:5: (lv_all_1_0= 'all' )
                    {
                    // InternalIdioms.g:2221:5: (lv_all_1_0= 'all' )
                    // InternalIdioms.g:2222:6: lv_all_1_0= 'all'
                    {
                    lv_all_1_0=(Token)match(input,53,FollowSets000.FOLLOW_10); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						newLeafNode(lv_all_1_0, grammarAccess.getSubIdiomAccess().getAllAllKeyword_1_0_0());

                    }
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElement(grammarAccess.getSubIdiomRule());
                      						}
                      						setWithLastConsumed(current, "all", lv_all_1_0 != null, "all");

                    }

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalIdioms.g:2235:4: otherlv_2= 'each'
                    {
                    otherlv_2=(Token)match(input,54,FollowSets000.FOLLOW_10); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_2, grammarAccess.getSubIdiomAccess().getEachKeyword_1_1());

                    }

                    }
                    break;

            }

            // InternalIdioms.g:2240:3: ( (lv_ownedLocator_3_0= ruleLocator ) )
            // InternalIdioms.g:2241:4: (lv_ownedLocator_3_0= ruleLocator )
            {
            // InternalIdioms.g:2241:4: (lv_ownedLocator_3_0= ruleLocator )
            // InternalIdioms.g:2242:5: lv_ownedLocator_3_0= ruleLocator
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getSubIdiomAccess().getOwnedLocatorLocatorParserRuleCall_2_0());

            }
            pushFollow(FollowSets000.FOLLOW_21);
            lv_ownedLocator_3_0=ruleLocator();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getSubIdiomRule());
              					}
              					set(
              						current,
              						"ownedLocator",
              						lv_ownedLocator_3_0,
              						"org.eclipse.ocl.xtext.idioms.Idioms.Locator");
              					afterParserOrEnumRuleCall();

            }

            }


            }

            // InternalIdioms.g:2259:3: (otherlv_4= 'do' ( ( (lv_ownedSegments_5_1= ruleSegment | lv_ownedSegments_5_2= ruleReferredSegment ) ) )* )?
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==55) ) {
                alt28=1;
            }
            switch (alt28) {
                case 1 :
                    // InternalIdioms.g:2260:4: otherlv_4= 'do' ( ( (lv_ownedSegments_5_1= ruleSegment | lv_ownedSegments_5_2= ruleReferredSegment ) ) )*
                    {
                    otherlv_4=(Token)match(input,55,FollowSets000.FOLLOW_22); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_4, grammarAccess.getSubIdiomAccess().getDoKeyword_3_0());

                    }
                    // InternalIdioms.g:2264:4: ( ( (lv_ownedSegments_5_1= ruleSegment | lv_ownedSegments_5_2= ruleReferredSegment ) ) )*
                    loop27:
                    do {
                        int alt27=2;
                        int LA27_0 = input.LA(1);

                        if ( (LA27_0==RULE_ID||(LA27_0>=27 && LA27_0<=38)||(LA27_0>=40 && LA27_0<=45)) ) {
                            alt27=1;
                        }


                        switch (alt27) {
                    	case 1 :
                    	    // InternalIdioms.g:2265:5: ( (lv_ownedSegments_5_1= ruleSegment | lv_ownedSegments_5_2= ruleReferredSegment ) )
                    	    {
                    	    // InternalIdioms.g:2265:5: ( (lv_ownedSegments_5_1= ruleSegment | lv_ownedSegments_5_2= ruleReferredSegment ) )
                    	    // InternalIdioms.g:2266:6: (lv_ownedSegments_5_1= ruleSegment | lv_ownedSegments_5_2= ruleReferredSegment )
                    	    {
                    	    // InternalIdioms.g:2266:6: (lv_ownedSegments_5_1= ruleSegment | lv_ownedSegments_5_2= ruleReferredSegment )
                    	    int alt26=2;
                    	    int LA26_0 = input.LA(1);

                    	    if ( ((LA26_0>=27 && LA26_0<=38)||(LA26_0>=40 && LA26_0<=45)) ) {
                    	        alt26=1;
                    	    }
                    	    else if ( (LA26_0==RULE_ID) ) {
                    	        alt26=2;
                    	    }
                    	    else {
                    	        if (state.backtracking>0) {state.failed=true; return current;}
                    	        NoViableAltException nvae =
                    	            new NoViableAltException("", 26, 0, input);

                    	        throw nvae;
                    	    }
                    	    switch (alt26) {
                    	        case 1 :
                    	            // InternalIdioms.g:2267:7: lv_ownedSegments_5_1= ruleSegment
                    	            {
                    	            if ( state.backtracking==0 ) {

                    	              							newCompositeNode(grammarAccess.getSubIdiomAccess().getOwnedSegmentsSegmentParserRuleCall_3_1_0_0());

                    	            }
                    	            pushFollow(FollowSets000.FOLLOW_22);
                    	            lv_ownedSegments_5_1=ruleSegment();

                    	            state._fsp--;
                    	            if (state.failed) return current;
                    	            if ( state.backtracking==0 ) {

                    	              							if (current==null) {
                    	              								current = createModelElementForParent(grammarAccess.getSubIdiomRule());
                    	              							}
                    	              							add(
                    	              								current,
                    	              								"ownedSegments",
                    	              								lv_ownedSegments_5_1,
                    	              								"org.eclipse.ocl.xtext.idioms.Idioms.Segment");
                    	              							afterParserOrEnumRuleCall();

                    	            }

                    	            }
                    	            break;
                    	        case 2 :
                    	            // InternalIdioms.g:2283:7: lv_ownedSegments_5_2= ruleReferredSegment
                    	            {
                    	            if ( state.backtracking==0 ) {

                    	              							newCompositeNode(grammarAccess.getSubIdiomAccess().getOwnedSegmentsReferredSegmentParserRuleCall_3_1_0_1());

                    	            }
                    	            pushFollow(FollowSets000.FOLLOW_22);
                    	            lv_ownedSegments_5_2=ruleReferredSegment();

                    	            state._fsp--;
                    	            if (state.failed) return current;
                    	            if ( state.backtracking==0 ) {

                    	              							if (current==null) {
                    	              								current = createModelElementForParent(grammarAccess.getSubIdiomRule());
                    	              							}
                    	              							add(
                    	              								current,
                    	              								"ownedSegments",
                    	              								lv_ownedSegments_5_2,
                    	              								"org.eclipse.ocl.xtext.idioms.Idioms.ReferredSegment");
                    	              							afterParserOrEnumRuleCall();

                    	            }

                    	            }
                    	            break;

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop27;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_6=(Token)match(input,15,FollowSets000.FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_6, grammarAccess.getSubIdiomAccess().getSemicolonKeyword_4());

            }

            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSubIdiom"

    // Delegated rules





    private static class FollowSets000 {
        public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000000000010L});
        public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000C00004073002L});
        public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000C00004072002L});
        public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000C00004040002L});
        public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000000000020L});
        public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x000000000000C002L});
        public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000000008002L});
        public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000003B80030L});
        public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000000008000L});
        public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000000400000L});
        public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x00003F7FF8000000L});
        public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000008000000002L});
        public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000800000000000L});
        public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x0017000000000000L});
        public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x0016000000000000L});
        public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x0014000000000000L});
        public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x0018000000000000L});
        public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x0060000003B80030L});
        public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x0080000000008000L});
        public static final BitSet FOLLOW_22 = new BitSet(new long[]{0x00003F7FF8008010L});
    }


}
