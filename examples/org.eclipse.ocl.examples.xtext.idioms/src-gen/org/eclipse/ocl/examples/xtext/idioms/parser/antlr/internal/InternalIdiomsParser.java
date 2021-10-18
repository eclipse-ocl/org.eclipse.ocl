package org.eclipse.ocl.examples.xtext.idioms.parser.antlr.internal;

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
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
public class InternalIdiomsParser extends org.eclipse.ocl.examples.xtext.idioms.CompatibilityAbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_STRING", "RULE_INT", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'model'", "'.'", "'import'", "'as'", "';'", "'with'", "'locator'", "'any-assignment'", "'any-element'", "'assignment'", "'::'", "'final'", "'returns'", "'segment'", "'custom'", "'half-new-line'", "'new-line'", "'no-space'", "'pop'", "'post-comment'", "'pre-comment'", "'push'", "'soft-new-line'", "'soft-space'", "'string'", "'printable'", "'value'", "'wrap-anchor'", "'wrap-begin-all'", "'wrap-begin-some'", "'wrap-end'", "'wrap-here'", "'mixin'", "'idiom'", "'for'", "'in'", "'{'", "'}'", "'at'", "'all'", "'each'", "'do'"
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
    // InternalIdioms.g:86:1: ruleIdiomsModel returns [EObject current=null] : (otherlv_0= 'model' ( (lv_names_1_0= RULE_ID ) ) (otherlv_2= '.' ( (lv_names_3_0= RULE_ID ) ) )* ( (lv_ownedWiths_4_0= ruleIdiomsImport ) )* ( (lv_ownedImports_5_0= ruleEPackageImport ) )* ( ( (lv_ownedLocatorDeclarations_6_0= ruleLocatorDeclaration ) ) | ( (lv_ownedSegmentDeclarations_7_0= ruleSegmentDeclaration ) ) | ( (lv_ownedIdioms_8_0= ruleIdiom ) ) )* ) ;
    public final EObject ruleIdiomsModel() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_names_1_0=null;
        Token otherlv_2=null;
        Token lv_names_3_0=null;
        EObject lv_ownedWiths_4_0 = null;

        EObject lv_ownedImports_5_0 = null;

        EObject lv_ownedLocatorDeclarations_6_0 = null;

        EObject lv_ownedSegmentDeclarations_7_0 = null;

        EObject lv_ownedIdioms_8_0 = null;



        	enterRule();

        try {
            // InternalIdioms.g:92:2: ( (otherlv_0= 'model' ( (lv_names_1_0= RULE_ID ) ) (otherlv_2= '.' ( (lv_names_3_0= RULE_ID ) ) )* ( (lv_ownedWiths_4_0= ruleIdiomsImport ) )* ( (lv_ownedImports_5_0= ruleEPackageImport ) )* ( ( (lv_ownedLocatorDeclarations_6_0= ruleLocatorDeclaration ) ) | ( (lv_ownedSegmentDeclarations_7_0= ruleSegmentDeclaration ) ) | ( (lv_ownedIdioms_8_0= ruleIdiom ) ) )* ) )
            // InternalIdioms.g:93:2: (otherlv_0= 'model' ( (lv_names_1_0= RULE_ID ) ) (otherlv_2= '.' ( (lv_names_3_0= RULE_ID ) ) )* ( (lv_ownedWiths_4_0= ruleIdiomsImport ) )* ( (lv_ownedImports_5_0= ruleEPackageImport ) )* ( ( (lv_ownedLocatorDeclarations_6_0= ruleLocatorDeclaration ) ) | ( (lv_ownedSegmentDeclarations_7_0= ruleSegmentDeclaration ) ) | ( (lv_ownedIdioms_8_0= ruleIdiom ) ) )* )
            {
            // InternalIdioms.g:93:2: (otherlv_0= 'model' ( (lv_names_1_0= RULE_ID ) ) (otherlv_2= '.' ( (lv_names_3_0= RULE_ID ) ) )* ( (lv_ownedWiths_4_0= ruleIdiomsImport ) )* ( (lv_ownedImports_5_0= ruleEPackageImport ) )* ( ( (lv_ownedLocatorDeclarations_6_0= ruleLocatorDeclaration ) ) | ( (lv_ownedSegmentDeclarations_7_0= ruleSegmentDeclaration ) ) | ( (lv_ownedIdioms_8_0= ruleIdiom ) ) )* )
            // InternalIdioms.g:94:3: otherlv_0= 'model' ( (lv_names_1_0= RULE_ID ) ) (otherlv_2= '.' ( (lv_names_3_0= RULE_ID ) ) )* ( (lv_ownedWiths_4_0= ruleIdiomsImport ) )* ( (lv_ownedImports_5_0= ruleEPackageImport ) )* ( ( (lv_ownedLocatorDeclarations_6_0= ruleLocatorDeclaration ) ) | ( (lv_ownedSegmentDeclarations_7_0= ruleSegmentDeclaration ) ) | ( (lv_ownedIdioms_8_0= ruleIdiom ) ) )*
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
              						"org.eclipse.ocl.examples.xtext.idioms.Idioms.ID");

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
            	      							"org.eclipse.ocl.examples.xtext.idioms.Idioms.ID");

            	    }

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);

            // InternalIdioms.g:140:3: ( (lv_ownedWiths_4_0= ruleIdiomsImport ) )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==16) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // InternalIdioms.g:141:4: (lv_ownedWiths_4_0= ruleIdiomsImport )
            	    {
            	    // InternalIdioms.g:141:4: (lv_ownedWiths_4_0= ruleIdiomsImport )
            	    // InternalIdioms.g:142:5: lv_ownedWiths_4_0= ruleIdiomsImport
            	    {
            	    if ( state.backtracking==0 ) {

            	      					newCompositeNode(grammarAccess.getIdiomsModelAccess().getOwnedWithsIdiomsImportParserRuleCall_3_0());

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
            	      						"org.eclipse.ocl.examples.xtext.idioms.Idioms.IdiomsImport");
            	      					afterParserOrEnumRuleCall();

            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);

            // InternalIdioms.g:159:3: ( (lv_ownedImports_5_0= ruleEPackageImport ) )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==13) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // InternalIdioms.g:160:4: (lv_ownedImports_5_0= ruleEPackageImport )
            	    {
            	    // InternalIdioms.g:160:4: (lv_ownedImports_5_0= ruleEPackageImport )
            	    // InternalIdioms.g:161:5: lv_ownedImports_5_0= ruleEPackageImport
            	    {
            	    if ( state.backtracking==0 ) {

            	      					newCompositeNode(grammarAccess.getIdiomsModelAccess().getOwnedImportsEPackageImportParserRuleCall_4_0());

            	    }
            	    pushFollow(FollowSets000.FOLLOW_6);
            	    lv_ownedImports_5_0=ruleEPackageImport();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      					if (current==null) {
            	      						current = createModelElementForParent(grammarAccess.getIdiomsModelRule());
            	      					}
            	      					add(
            	      						current,
            	      						"ownedImports",
            	      						lv_ownedImports_5_0,
            	      						"org.eclipse.ocl.examples.xtext.idioms.Idioms.EPackageImport");
            	      					afterParserOrEnumRuleCall();

            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);

            // InternalIdioms.g:178:3: ( ( (lv_ownedLocatorDeclarations_6_0= ruleLocatorDeclaration ) ) | ( (lv_ownedSegmentDeclarations_7_0= ruleSegmentDeclaration ) ) | ( (lv_ownedIdioms_8_0= ruleIdiom ) ) )*
            loop4:
            do {
                int alt4=4;
                switch ( input.LA(1) ) {
                case 17:
                    {
                    alt4=1;
                    }
                    break;
                case 24:
                    {
                    alt4=2;
                    }
                    break;
                case 43:
                case 44:
                    {
                    alt4=3;
                    }
                    break;

                }

                switch (alt4) {
            	case 1 :
            	    // InternalIdioms.g:179:4: ( (lv_ownedLocatorDeclarations_6_0= ruleLocatorDeclaration ) )
            	    {
            	    // InternalIdioms.g:179:4: ( (lv_ownedLocatorDeclarations_6_0= ruleLocatorDeclaration ) )
            	    // InternalIdioms.g:180:5: (lv_ownedLocatorDeclarations_6_0= ruleLocatorDeclaration )
            	    {
            	    // InternalIdioms.g:180:5: (lv_ownedLocatorDeclarations_6_0= ruleLocatorDeclaration )
            	    // InternalIdioms.g:181:6: lv_ownedLocatorDeclarations_6_0= ruleLocatorDeclaration
            	    {
            	    if ( state.backtracking==0 ) {

            	      						newCompositeNode(grammarAccess.getIdiomsModelAccess().getOwnedLocatorDeclarationsLocatorDeclarationParserRuleCall_5_0_0());

            	    }
            	    pushFollow(FollowSets000.FOLLOW_7);
            	    lv_ownedLocatorDeclarations_6_0=ruleLocatorDeclaration();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      						if (current==null) {
            	      							current = createModelElementForParent(grammarAccess.getIdiomsModelRule());
            	      						}
            	      						add(
            	      							current,
            	      							"ownedLocatorDeclarations",
            	      							lv_ownedLocatorDeclarations_6_0,
            	      							"org.eclipse.ocl.examples.xtext.idioms.Idioms.LocatorDeclaration");
            	      						afterParserOrEnumRuleCall();

            	    }

            	    }


            	    }


            	    }
            	    break;
            	case 2 :
            	    // InternalIdioms.g:199:4: ( (lv_ownedSegmentDeclarations_7_0= ruleSegmentDeclaration ) )
            	    {
            	    // InternalIdioms.g:199:4: ( (lv_ownedSegmentDeclarations_7_0= ruleSegmentDeclaration ) )
            	    // InternalIdioms.g:200:5: (lv_ownedSegmentDeclarations_7_0= ruleSegmentDeclaration )
            	    {
            	    // InternalIdioms.g:200:5: (lv_ownedSegmentDeclarations_7_0= ruleSegmentDeclaration )
            	    // InternalIdioms.g:201:6: lv_ownedSegmentDeclarations_7_0= ruleSegmentDeclaration
            	    {
            	    if ( state.backtracking==0 ) {

            	      						newCompositeNode(grammarAccess.getIdiomsModelAccess().getOwnedSegmentDeclarationsSegmentDeclarationParserRuleCall_5_1_0());

            	    }
            	    pushFollow(FollowSets000.FOLLOW_7);
            	    lv_ownedSegmentDeclarations_7_0=ruleSegmentDeclaration();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      						if (current==null) {
            	      							current = createModelElementForParent(grammarAccess.getIdiomsModelRule());
            	      						}
            	      						add(
            	      							current,
            	      							"ownedSegmentDeclarations",
            	      							lv_ownedSegmentDeclarations_7_0,
            	      							"org.eclipse.ocl.examples.xtext.idioms.Idioms.SegmentDeclaration");
            	      						afterParserOrEnumRuleCall();

            	    }

            	    }


            	    }


            	    }
            	    break;
            	case 3 :
            	    // InternalIdioms.g:219:4: ( (lv_ownedIdioms_8_0= ruleIdiom ) )
            	    {
            	    // InternalIdioms.g:219:4: ( (lv_ownedIdioms_8_0= ruleIdiom ) )
            	    // InternalIdioms.g:220:5: (lv_ownedIdioms_8_0= ruleIdiom )
            	    {
            	    // InternalIdioms.g:220:5: (lv_ownedIdioms_8_0= ruleIdiom )
            	    // InternalIdioms.g:221:6: lv_ownedIdioms_8_0= ruleIdiom
            	    {
            	    if ( state.backtracking==0 ) {

            	      						newCompositeNode(grammarAccess.getIdiomsModelAccess().getOwnedIdiomsIdiomParserRuleCall_5_2_0());

            	    }
            	    pushFollow(FollowSets000.FOLLOW_7);
            	    lv_ownedIdioms_8_0=ruleIdiom();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      						if (current==null) {
            	      							current = createModelElementForParent(grammarAccess.getIdiomsModelRule());
            	      						}
            	      						add(
            	      							current,
            	      							"ownedIdioms",
            	      							lv_ownedIdioms_8_0,
            	      							"org.eclipse.ocl.examples.xtext.idioms.Idioms.Idiom");
            	      						afterParserOrEnumRuleCall();

            	    }

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop4;
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


    // $ANTLR start "entryRuleEPackageImport"
    // InternalIdioms.g:243:1: entryRuleEPackageImport returns [EObject current=null] : iv_ruleEPackageImport= ruleEPackageImport EOF ;
    public final EObject entryRuleEPackageImport() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEPackageImport = null;


        try {
            // InternalIdioms.g:243:55: (iv_ruleEPackageImport= ruleEPackageImport EOF )
            // InternalIdioms.g:244:2: iv_ruleEPackageImport= ruleEPackageImport EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getEPackageImportRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleEPackageImport=ruleEPackageImport();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleEPackageImport;
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
    // $ANTLR end "entryRuleEPackageImport"


    // $ANTLR start "ruleEPackageImport"
    // InternalIdioms.g:250:1: ruleEPackageImport returns [EObject current=null] : (otherlv_0= 'import' ( (otherlv_1= RULE_STRING ) ) (otherlv_2= 'as' ( (lv_as_3_0= RULE_ID ) ) )? (otherlv_4= ';' )? ) ;
    public final EObject ruleEPackageImport() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;
        Token lv_as_3_0=null;
        Token otherlv_4=null;


        	enterRule();

        try {
            // InternalIdioms.g:256:2: ( (otherlv_0= 'import' ( (otherlv_1= RULE_STRING ) ) (otherlv_2= 'as' ( (lv_as_3_0= RULE_ID ) ) )? (otherlv_4= ';' )? ) )
            // InternalIdioms.g:257:2: (otherlv_0= 'import' ( (otherlv_1= RULE_STRING ) ) (otherlv_2= 'as' ( (lv_as_3_0= RULE_ID ) ) )? (otherlv_4= ';' )? )
            {
            // InternalIdioms.g:257:2: (otherlv_0= 'import' ( (otherlv_1= RULE_STRING ) ) (otherlv_2= 'as' ( (lv_as_3_0= RULE_ID ) ) )? (otherlv_4= ';' )? )
            // InternalIdioms.g:258:3: otherlv_0= 'import' ( (otherlv_1= RULE_STRING ) ) (otherlv_2= 'as' ( (lv_as_3_0= RULE_ID ) ) )? (otherlv_4= ';' )?
            {
            otherlv_0=(Token)match(input,13,FollowSets000.FOLLOW_8); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getEPackageImportAccess().getImportKeyword_0());

            }
            // InternalIdioms.g:262:3: ( (otherlv_1= RULE_STRING ) )
            // InternalIdioms.g:263:4: (otherlv_1= RULE_STRING )
            {
            // InternalIdioms.g:263:4: (otherlv_1= RULE_STRING )
            // InternalIdioms.g:264:5: otherlv_1= RULE_STRING
            {
            if ( state.backtracking==0 ) {

              					/* */

            }
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElement(grammarAccess.getEPackageImportRule());
              					}

            }
            otherlv_1=(Token)match(input,RULE_STRING,FollowSets000.FOLLOW_9); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					newLeafNode(otherlv_1, grammarAccess.getEPackageImportAccess().getEPackageEPackageCrossReference_1_0());

            }

            }


            }

            // InternalIdioms.g:278:3: (otherlv_2= 'as' ( (lv_as_3_0= RULE_ID ) ) )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==14) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // InternalIdioms.g:279:4: otherlv_2= 'as' ( (lv_as_3_0= RULE_ID ) )
                    {
                    otherlv_2=(Token)match(input,14,FollowSets000.FOLLOW_3); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_2, grammarAccess.getEPackageImportAccess().getAsKeyword_2_0());

                    }
                    // InternalIdioms.g:283:4: ( (lv_as_3_0= RULE_ID ) )
                    // InternalIdioms.g:284:5: (lv_as_3_0= RULE_ID )
                    {
                    // InternalIdioms.g:284:5: (lv_as_3_0= RULE_ID )
                    // InternalIdioms.g:285:6: lv_as_3_0= RULE_ID
                    {
                    lv_as_3_0=(Token)match(input,RULE_ID,FollowSets000.FOLLOW_10); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						newLeafNode(lv_as_3_0, grammarAccess.getEPackageImportAccess().getAsIDTerminalRuleCall_2_1_0());

                    }
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElement(grammarAccess.getEPackageImportRule());
                      						}
                      						setWithLastConsumed(
                      							current,
                      							"as",
                      							lv_as_3_0,
                      							"org.eclipse.ocl.examples.xtext.idioms.Idioms.ID");

                    }

                    }


                    }


                    }
                    break;

            }

            // InternalIdioms.g:302:3: (otherlv_4= ';' )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==15) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // InternalIdioms.g:303:4: otherlv_4= ';'
                    {
                    otherlv_4=(Token)match(input,15,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_4, grammarAccess.getEPackageImportAccess().getSemicolonKeyword_3());

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
    // $ANTLR end "ruleEPackageImport"


    // $ANTLR start "entryRuleIdiomsImport"
    // InternalIdioms.g:312:1: entryRuleIdiomsImport returns [EObject current=null] : iv_ruleIdiomsImport= ruleIdiomsImport EOF ;
    public final EObject entryRuleIdiomsImport() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIdiomsImport = null;


        try {
            // InternalIdioms.g:312:53: (iv_ruleIdiomsImport= ruleIdiomsImport EOF )
            // InternalIdioms.g:313:2: iv_ruleIdiomsImport= ruleIdiomsImport EOF
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
    // InternalIdioms.g:319:1: ruleIdiomsImport returns [EObject current=null] : (otherlv_0= 'with' ( (otherlv_1= RULE_STRING ) ) (otherlv_2= 'as' ( (lv_as_3_0= RULE_ID ) ) )? (otherlv_4= ';' )? ) ;
    public final EObject ruleIdiomsImport() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;
        Token lv_as_3_0=null;
        Token otherlv_4=null;


        	enterRule();

        try {
            // InternalIdioms.g:325:2: ( (otherlv_0= 'with' ( (otherlv_1= RULE_STRING ) ) (otherlv_2= 'as' ( (lv_as_3_0= RULE_ID ) ) )? (otherlv_4= ';' )? ) )
            // InternalIdioms.g:326:2: (otherlv_0= 'with' ( (otherlv_1= RULE_STRING ) ) (otherlv_2= 'as' ( (lv_as_3_0= RULE_ID ) ) )? (otherlv_4= ';' )? )
            {
            // InternalIdioms.g:326:2: (otherlv_0= 'with' ( (otherlv_1= RULE_STRING ) ) (otherlv_2= 'as' ( (lv_as_3_0= RULE_ID ) ) )? (otherlv_4= ';' )? )
            // InternalIdioms.g:327:3: otherlv_0= 'with' ( (otherlv_1= RULE_STRING ) ) (otherlv_2= 'as' ( (lv_as_3_0= RULE_ID ) ) )? (otherlv_4= ';' )?
            {
            otherlv_0=(Token)match(input,16,FollowSets000.FOLLOW_8); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getIdiomsImportAccess().getWithKeyword_0());

            }
            // InternalIdioms.g:331:3: ( (otherlv_1= RULE_STRING ) )
            // InternalIdioms.g:332:4: (otherlv_1= RULE_STRING )
            {
            // InternalIdioms.g:332:4: (otherlv_1= RULE_STRING )
            // InternalIdioms.g:333:5: otherlv_1= RULE_STRING
            {
            if ( state.backtracking==0 ) {

              					/* */

            }
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElement(grammarAccess.getIdiomsImportRule());
              					}

            }
            otherlv_1=(Token)match(input,RULE_STRING,FollowSets000.FOLLOW_9); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					newLeafNode(otherlv_1, grammarAccess.getIdiomsImportAccess().getIdiomsModelIdiomsModelCrossReference_1_0());

            }

            }


            }

            // InternalIdioms.g:347:3: (otherlv_2= 'as' ( (lv_as_3_0= RULE_ID ) ) )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==14) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // InternalIdioms.g:348:4: otherlv_2= 'as' ( (lv_as_3_0= RULE_ID ) )
                    {
                    otherlv_2=(Token)match(input,14,FollowSets000.FOLLOW_3); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_2, grammarAccess.getIdiomsImportAccess().getAsKeyword_2_0());

                    }
                    // InternalIdioms.g:352:4: ( (lv_as_3_0= RULE_ID ) )
                    // InternalIdioms.g:353:5: (lv_as_3_0= RULE_ID )
                    {
                    // InternalIdioms.g:353:5: (lv_as_3_0= RULE_ID )
                    // InternalIdioms.g:354:6: lv_as_3_0= RULE_ID
                    {
                    lv_as_3_0=(Token)match(input,RULE_ID,FollowSets000.FOLLOW_10); if (state.failed) return current;
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
                      							"org.eclipse.ocl.examples.xtext.idioms.Idioms.ID");

                    }

                    }


                    }


                    }
                    break;

            }

            // InternalIdioms.g:371:3: (otherlv_4= ';' )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==15) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // InternalIdioms.g:372:4: otherlv_4= ';'
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
    // InternalIdioms.g:381:1: entryRuleLocatorDeclaration returns [EObject current=null] : iv_ruleLocatorDeclaration= ruleLocatorDeclaration EOF ;
    public final EObject entryRuleLocatorDeclaration() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLocatorDeclaration = null;


        try {
            // InternalIdioms.g:381:59: (iv_ruleLocatorDeclaration= ruleLocatorDeclaration EOF )
            // InternalIdioms.g:382:2: iv_ruleLocatorDeclaration= ruleLocatorDeclaration EOF
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
    // InternalIdioms.g:388:1: ruleLocatorDeclaration returns [EObject current=null] : (otherlv_0= 'locator' ( (lv_name_1_0= RULE_ID ) ) ( (lv_ownedLocator_2_0= ruleLocator ) ) otherlv_3= ';' ) ;
    public final EObject ruleLocatorDeclaration() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_1_0=null;
        Token otherlv_3=null;
        EObject lv_ownedLocator_2_0 = null;



        	enterRule();

        try {
            // InternalIdioms.g:394:2: ( (otherlv_0= 'locator' ( (lv_name_1_0= RULE_ID ) ) ( (lv_ownedLocator_2_0= ruleLocator ) ) otherlv_3= ';' ) )
            // InternalIdioms.g:395:2: (otherlv_0= 'locator' ( (lv_name_1_0= RULE_ID ) ) ( (lv_ownedLocator_2_0= ruleLocator ) ) otherlv_3= ';' )
            {
            // InternalIdioms.g:395:2: (otherlv_0= 'locator' ( (lv_name_1_0= RULE_ID ) ) ( (lv_ownedLocator_2_0= ruleLocator ) ) otherlv_3= ';' )
            // InternalIdioms.g:396:3: otherlv_0= 'locator' ( (lv_name_1_0= RULE_ID ) ) ( (lv_ownedLocator_2_0= ruleLocator ) ) otherlv_3= ';'
            {
            otherlv_0=(Token)match(input,17,FollowSets000.FOLLOW_3); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getLocatorDeclarationAccess().getLocatorKeyword_0());

            }
            // InternalIdioms.g:400:3: ( (lv_name_1_0= RULE_ID ) )
            // InternalIdioms.g:401:4: (lv_name_1_0= RULE_ID )
            {
            // InternalIdioms.g:401:4: (lv_name_1_0= RULE_ID )
            // InternalIdioms.g:402:5: lv_name_1_0= RULE_ID
            {
            lv_name_1_0=(Token)match(input,RULE_ID,FollowSets000.FOLLOW_11); if (state.failed) return current;
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
              						"org.eclipse.ocl.examples.xtext.idioms.Idioms.ID");

            }

            }


            }

            // InternalIdioms.g:418:3: ( (lv_ownedLocator_2_0= ruleLocator ) )
            // InternalIdioms.g:419:4: (lv_ownedLocator_2_0= ruleLocator )
            {
            // InternalIdioms.g:419:4: (lv_ownedLocator_2_0= ruleLocator )
            // InternalIdioms.g:420:5: lv_ownedLocator_2_0= ruleLocator
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getLocatorDeclarationAccess().getOwnedLocatorLocatorParserRuleCall_2_0());

            }
            pushFollow(FollowSets000.FOLLOW_12);
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
              						"org.eclipse.ocl.examples.xtext.idioms.Idioms.Locator");
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
    // InternalIdioms.g:445:1: entryRuleLocator returns [EObject current=null] : iv_ruleLocator= ruleLocator EOF ;
    public final EObject entryRuleLocator() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLocator = null;


        try {
            // InternalIdioms.g:445:48: (iv_ruleLocator= ruleLocator EOF )
            // InternalIdioms.g:446:2: iv_ruleLocator= ruleLocator EOF
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
    // InternalIdioms.g:452:1: ruleLocator returns [EObject current=null] : (this_AnyAssignmentLocator_0= ruleAnyAssignmentLocator | this_AnyElementLocator_1= ruleAnyElementLocator | this_AssignmentLocator_2= ruleAssignmentLocator | this_FinalLocator_3= ruleFinalLocator | this_KeywordLocator_4= ruleKeywordLocator | this_ReferredLocator_5= ruleReferredLocator | this_ReturnsLocator_6= ruleReturnsLocator ) ;
    public final EObject ruleLocator() throws RecognitionException {
        EObject current = null;

        EObject this_AnyAssignmentLocator_0 = null;

        EObject this_AnyElementLocator_1 = null;

        EObject this_AssignmentLocator_2 = null;

        EObject this_FinalLocator_3 = null;

        EObject this_KeywordLocator_4 = null;

        EObject this_ReferredLocator_5 = null;

        EObject this_ReturnsLocator_6 = null;



        	enterRule();

        try {
            // InternalIdioms.g:458:2: ( (this_AnyAssignmentLocator_0= ruleAnyAssignmentLocator | this_AnyElementLocator_1= ruleAnyElementLocator | this_AssignmentLocator_2= ruleAssignmentLocator | this_FinalLocator_3= ruleFinalLocator | this_KeywordLocator_4= ruleKeywordLocator | this_ReferredLocator_5= ruleReferredLocator | this_ReturnsLocator_6= ruleReturnsLocator ) )
            // InternalIdioms.g:459:2: (this_AnyAssignmentLocator_0= ruleAnyAssignmentLocator | this_AnyElementLocator_1= ruleAnyElementLocator | this_AssignmentLocator_2= ruleAssignmentLocator | this_FinalLocator_3= ruleFinalLocator | this_KeywordLocator_4= ruleKeywordLocator | this_ReferredLocator_5= ruleReferredLocator | this_ReturnsLocator_6= ruleReturnsLocator )
            {
            // InternalIdioms.g:459:2: (this_AnyAssignmentLocator_0= ruleAnyAssignmentLocator | this_AnyElementLocator_1= ruleAnyElementLocator | this_AssignmentLocator_2= ruleAssignmentLocator | this_FinalLocator_3= ruleFinalLocator | this_KeywordLocator_4= ruleKeywordLocator | this_ReferredLocator_5= ruleReferredLocator | this_ReturnsLocator_6= ruleReturnsLocator )
            int alt9=7;
            switch ( input.LA(1) ) {
            case 18:
                {
                alt9=1;
                }
                break;
            case 19:
                {
                alt9=2;
                }
                break;
            case 20:
                {
                alt9=3;
                }
                break;
            case 22:
                {
                alt9=4;
                }
                break;
            case RULE_STRING:
                {
                alt9=5;
                }
                break;
            case RULE_ID:
                {
                alt9=6;
                }
                break;
            case 23:
                {
                alt9=7;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 0, input);

                throw nvae;
            }

            switch (alt9) {
                case 1 :
                    // InternalIdioms.g:460:3: this_AnyAssignmentLocator_0= ruleAnyAssignmentLocator
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
                    // InternalIdioms.g:472:3: this_AnyElementLocator_1= ruleAnyElementLocator
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
                    // InternalIdioms.g:484:3: this_AssignmentLocator_2= ruleAssignmentLocator
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
                    // InternalIdioms.g:496:3: this_FinalLocator_3= ruleFinalLocator
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
                    // InternalIdioms.g:508:3: this_KeywordLocator_4= ruleKeywordLocator
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
                    // InternalIdioms.g:520:3: this_ReferredLocator_5= ruleReferredLocator
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
                    // InternalIdioms.g:532:3: this_ReturnsLocator_6= ruleReturnsLocator
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
    // InternalIdioms.g:547:1: entryRuleAnyAssignmentLocator returns [EObject current=null] : iv_ruleAnyAssignmentLocator= ruleAnyAssignmentLocator EOF ;
    public final EObject entryRuleAnyAssignmentLocator() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAnyAssignmentLocator = null;


        try {
            // InternalIdioms.g:547:61: (iv_ruleAnyAssignmentLocator= ruleAnyAssignmentLocator EOF )
            // InternalIdioms.g:548:2: iv_ruleAnyAssignmentLocator= ruleAnyAssignmentLocator EOF
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
    // InternalIdioms.g:554:1: ruleAnyAssignmentLocator returns [EObject current=null] : ( () otherlv_1= 'any-assignment' ) ;
    public final EObject ruleAnyAssignmentLocator() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;


        	enterRule();

        try {
            // InternalIdioms.g:560:2: ( ( () otherlv_1= 'any-assignment' ) )
            // InternalIdioms.g:561:2: ( () otherlv_1= 'any-assignment' )
            {
            // InternalIdioms.g:561:2: ( () otherlv_1= 'any-assignment' )
            // InternalIdioms.g:562:3: () otherlv_1= 'any-assignment'
            {
            // InternalIdioms.g:562:3: ()
            // InternalIdioms.g:563:4:
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

            otherlv_1=(Token)match(input,18,FollowSets000.FOLLOW_2); if (state.failed) return current;
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
    // InternalIdioms.g:580:1: entryRuleAnyElementLocator returns [EObject current=null] : iv_ruleAnyElementLocator= ruleAnyElementLocator EOF ;
    public final EObject entryRuleAnyElementLocator() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAnyElementLocator = null;


        try {
            // InternalIdioms.g:580:58: (iv_ruleAnyElementLocator= ruleAnyElementLocator EOF )
            // InternalIdioms.g:581:2: iv_ruleAnyElementLocator= ruleAnyElementLocator EOF
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
    // InternalIdioms.g:587:1: ruleAnyElementLocator returns [EObject current=null] : ( () otherlv_1= 'any-element' ) ;
    public final EObject ruleAnyElementLocator() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;


        	enterRule();

        try {
            // InternalIdioms.g:593:2: ( ( () otherlv_1= 'any-element' ) )
            // InternalIdioms.g:594:2: ( () otherlv_1= 'any-element' )
            {
            // InternalIdioms.g:594:2: ( () otherlv_1= 'any-element' )
            // InternalIdioms.g:595:3: () otherlv_1= 'any-element'
            {
            // InternalIdioms.g:595:3: ()
            // InternalIdioms.g:596:4:
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

            otherlv_1=(Token)match(input,19,FollowSets000.FOLLOW_2); if (state.failed) return current;
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
    // InternalIdioms.g:613:1: entryRuleAssignmentLocator returns [EObject current=null] : iv_ruleAssignmentLocator= ruleAssignmentLocator EOF ;
    public final EObject entryRuleAssignmentLocator() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAssignmentLocator = null;


        try {
            // InternalIdioms.g:613:58: (iv_ruleAssignmentLocator= ruleAssignmentLocator EOF )
            // InternalIdioms.g:614:2: iv_ruleAssignmentLocator= ruleAssignmentLocator EOF
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
    // InternalIdioms.g:620:1: ruleAssignmentLocator returns [EObject current=null] : (otherlv_0= 'assignment' ( ( ( (otherlv_1= RULE_ID ) ) otherlv_2= '::' )? ( (otherlv_3= RULE_ID ) ) otherlv_4= '::' )? ( (otherlv_5= RULE_ID ) ) ) ;
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
            // InternalIdioms.g:626:2: ( (otherlv_0= 'assignment' ( ( ( (otherlv_1= RULE_ID ) ) otherlv_2= '::' )? ( (otherlv_3= RULE_ID ) ) otherlv_4= '::' )? ( (otherlv_5= RULE_ID ) ) ) )
            // InternalIdioms.g:627:2: (otherlv_0= 'assignment' ( ( ( (otherlv_1= RULE_ID ) ) otherlv_2= '::' )? ( (otherlv_3= RULE_ID ) ) otherlv_4= '::' )? ( (otherlv_5= RULE_ID ) ) )
            {
            // InternalIdioms.g:627:2: (otherlv_0= 'assignment' ( ( ( (otherlv_1= RULE_ID ) ) otherlv_2= '::' )? ( (otherlv_3= RULE_ID ) ) otherlv_4= '::' )? ( (otherlv_5= RULE_ID ) ) )
            // InternalIdioms.g:628:3: otherlv_0= 'assignment' ( ( ( (otherlv_1= RULE_ID ) ) otherlv_2= '::' )? ( (otherlv_3= RULE_ID ) ) otherlv_4= '::' )? ( (otherlv_5= RULE_ID ) )
            {
            otherlv_0=(Token)match(input,20,FollowSets000.FOLLOW_3); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getAssignmentLocatorAccess().getAssignmentKeyword_0());

            }
            // InternalIdioms.g:632:3: ( ( ( (otherlv_1= RULE_ID ) ) otherlv_2= '::' )? ( (otherlv_3= RULE_ID ) ) otherlv_4= '::' )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==RULE_ID) ) {
                int LA11_1 = input.LA(2);

                if ( (LA11_1==21) ) {
                    alt11=1;
                }
            }
            switch (alt11) {
                case 1 :
                    // InternalIdioms.g:633:4: ( ( (otherlv_1= RULE_ID ) ) otherlv_2= '::' )? ( (otherlv_3= RULE_ID ) ) otherlv_4= '::'
                    {
                    // InternalIdioms.g:633:4: ( ( (otherlv_1= RULE_ID ) ) otherlv_2= '::' )?
                    int alt10=2;
                    int LA10_0 = input.LA(1);

                    if ( (LA10_0==RULE_ID) ) {
                        int LA10_1 = input.LA(2);

                        if ( (LA10_1==21) ) {
                            int LA10_2 = input.LA(3);

                            if ( (LA10_2==RULE_ID) ) {
                                int LA10_3 = input.LA(4);

                                if ( (LA10_3==21) ) {
                                    alt10=1;
                                }
                            }
                        }
                    }
                    switch (alt10) {
                        case 1 :
                            // InternalIdioms.g:634:5: ( (otherlv_1= RULE_ID ) ) otherlv_2= '::'
                            {
                            // InternalIdioms.g:634:5: ( (otherlv_1= RULE_ID ) )
                            // InternalIdioms.g:635:6: (otherlv_1= RULE_ID )
                            {
                            // InternalIdioms.g:635:6: (otherlv_1= RULE_ID )
                            // InternalIdioms.g:636:7: otherlv_1= RULE_ID
                            {
                            if ( state.backtracking==0 ) {

                              							/* */

                            }
                            if ( state.backtracking==0 ) {

                              							if (current==null) {
                              								current = createModelElement(grammarAccess.getAssignmentLocatorRule());
                              							}

                            }
                            otherlv_1=(Token)match(input,RULE_ID,FollowSets000.FOLLOW_13); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              							newLeafNode(otherlv_1, grammarAccess.getAssignmentLocatorAccess().getEPackageEPackageCrossReference_1_0_0_0());

                            }

                            }


                            }

                            otherlv_2=(Token)match(input,21,FollowSets000.FOLLOW_3); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              					newLeafNode(otherlv_2, grammarAccess.getAssignmentLocatorAccess().getColonColonKeyword_1_0_1());

                            }

                            }
                            break;

                    }

                    // InternalIdioms.g:655:4: ( (otherlv_3= RULE_ID ) )
                    // InternalIdioms.g:656:5: (otherlv_3= RULE_ID )
                    {
                    // InternalIdioms.g:656:5: (otherlv_3= RULE_ID )
                    // InternalIdioms.g:657:6: otherlv_3= RULE_ID
                    {
                    if ( state.backtracking==0 ) {

                      						/* */

                    }
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElement(grammarAccess.getAssignmentLocatorRule());
                      						}

                    }
                    otherlv_3=(Token)match(input,RULE_ID,FollowSets000.FOLLOW_13); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						newLeafNode(otherlv_3, grammarAccess.getAssignmentLocatorAccess().getEClassEClassCrossReference_1_1_0());

                    }

                    }


                    }

                    otherlv_4=(Token)match(input,21,FollowSets000.FOLLOW_3); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_4, grammarAccess.getAssignmentLocatorAccess().getColonColonKeyword_1_2());

                    }

                    }
                    break;

            }

            // InternalIdioms.g:676:3: ( (otherlv_5= RULE_ID ) )
            // InternalIdioms.g:677:4: (otherlv_5= RULE_ID )
            {
            // InternalIdioms.g:677:4: (otherlv_5= RULE_ID )
            // InternalIdioms.g:678:5: otherlv_5= RULE_ID
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
    // InternalIdioms.g:696:1: entryRuleFinalLocator returns [EObject current=null] : iv_ruleFinalLocator= ruleFinalLocator EOF ;
    public final EObject entryRuleFinalLocator() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFinalLocator = null;


        try {
            // InternalIdioms.g:696:53: (iv_ruleFinalLocator= ruleFinalLocator EOF )
            // InternalIdioms.g:697:2: iv_ruleFinalLocator= ruleFinalLocator EOF
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
    // InternalIdioms.g:703:1: ruleFinalLocator returns [EObject current=null] : ( () otherlv_1= 'final' ) ;
    public final EObject ruleFinalLocator() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;


        	enterRule();

        try {
            // InternalIdioms.g:709:2: ( ( () otherlv_1= 'final' ) )
            // InternalIdioms.g:710:2: ( () otherlv_1= 'final' )
            {
            // InternalIdioms.g:710:2: ( () otherlv_1= 'final' )
            // InternalIdioms.g:711:3: () otherlv_1= 'final'
            {
            // InternalIdioms.g:711:3: ()
            // InternalIdioms.g:712:4:
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

            otherlv_1=(Token)match(input,22,FollowSets000.FOLLOW_2); if (state.failed) return current;
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
    // InternalIdioms.g:729:1: entryRuleKeywordLocator returns [EObject current=null] : iv_ruleKeywordLocator= ruleKeywordLocator EOF ;
    public final EObject entryRuleKeywordLocator() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKeywordLocator = null;


        try {
            // InternalIdioms.g:729:55: (iv_ruleKeywordLocator= ruleKeywordLocator EOF )
            // InternalIdioms.g:730:2: iv_ruleKeywordLocator= ruleKeywordLocator EOF
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
    // InternalIdioms.g:736:1: ruleKeywordLocator returns [EObject current=null] : ( (lv_string_0_0= RULE_STRING ) ) ;
    public final EObject ruleKeywordLocator() throws RecognitionException {
        EObject current = null;

        Token lv_string_0_0=null;


        	enterRule();

        try {
            // InternalIdioms.g:742:2: ( ( (lv_string_0_0= RULE_STRING ) ) )
            // InternalIdioms.g:743:2: ( (lv_string_0_0= RULE_STRING ) )
            {
            // InternalIdioms.g:743:2: ( (lv_string_0_0= RULE_STRING ) )
            // InternalIdioms.g:744:3: (lv_string_0_0= RULE_STRING )
            {
            // InternalIdioms.g:744:3: (lv_string_0_0= RULE_STRING )
            // InternalIdioms.g:745:4: lv_string_0_0= RULE_STRING
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
              					"org.eclipse.ocl.examples.xtext.idioms.Idioms.STRING");

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
    // InternalIdioms.g:764:1: entryRuleReturnsLocator returns [EObject current=null] : iv_ruleReturnsLocator= ruleReturnsLocator EOF ;
    public final EObject entryRuleReturnsLocator() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleReturnsLocator = null;


        try {
            // InternalIdioms.g:764:55: (iv_ruleReturnsLocator= ruleReturnsLocator EOF )
            // InternalIdioms.g:765:2: iv_ruleReturnsLocator= ruleReturnsLocator EOF
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
    // InternalIdioms.g:771:1: ruleReturnsLocator returns [EObject current=null] : (otherlv_0= 'returns' ( ( (otherlv_1= RULE_ID ) ) otherlv_2= '::' )? ( (otherlv_3= RULE_ID ) ) ) ;
    public final EObject ruleReturnsLocator() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;


        	enterRule();

        try {
            // InternalIdioms.g:777:2: ( (otherlv_0= 'returns' ( ( (otherlv_1= RULE_ID ) ) otherlv_2= '::' )? ( (otherlv_3= RULE_ID ) ) ) )
            // InternalIdioms.g:778:2: (otherlv_0= 'returns' ( ( (otherlv_1= RULE_ID ) ) otherlv_2= '::' )? ( (otherlv_3= RULE_ID ) ) )
            {
            // InternalIdioms.g:778:2: (otherlv_0= 'returns' ( ( (otherlv_1= RULE_ID ) ) otherlv_2= '::' )? ( (otherlv_3= RULE_ID ) ) )
            // InternalIdioms.g:779:3: otherlv_0= 'returns' ( ( (otherlv_1= RULE_ID ) ) otherlv_2= '::' )? ( (otherlv_3= RULE_ID ) )
            {
            otherlv_0=(Token)match(input,23,FollowSets000.FOLLOW_3); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getReturnsLocatorAccess().getReturnsKeyword_0());

            }
            // InternalIdioms.g:783:3: ( ( (otherlv_1= RULE_ID ) ) otherlv_2= '::' )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==RULE_ID) ) {
                int LA12_1 = input.LA(2);

                if ( (LA12_1==21) ) {
                    alt12=1;
                }
            }
            switch (alt12) {
                case 1 :
                    // InternalIdioms.g:784:4: ( (otherlv_1= RULE_ID ) ) otherlv_2= '::'
                    {
                    // InternalIdioms.g:784:4: ( (otherlv_1= RULE_ID ) )
                    // InternalIdioms.g:785:5: (otherlv_1= RULE_ID )
                    {
                    // InternalIdioms.g:785:5: (otherlv_1= RULE_ID )
                    // InternalIdioms.g:786:6: otherlv_1= RULE_ID
                    {
                    if ( state.backtracking==0 ) {

                      						/* */

                    }
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElement(grammarAccess.getReturnsLocatorRule());
                      						}

                    }
                    otherlv_1=(Token)match(input,RULE_ID,FollowSets000.FOLLOW_13); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						newLeafNode(otherlv_1, grammarAccess.getReturnsLocatorAccess().getEPackageEPackageCrossReference_1_0_0());

                    }

                    }


                    }

                    otherlv_2=(Token)match(input,21,FollowSets000.FOLLOW_3); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_2, grammarAccess.getReturnsLocatorAccess().getColonColonKeyword_1_1());

                    }

                    }
                    break;

            }

            // InternalIdioms.g:805:3: ( (otherlv_3= RULE_ID ) )
            // InternalIdioms.g:806:4: (otherlv_3= RULE_ID )
            {
            // InternalIdioms.g:806:4: (otherlv_3= RULE_ID )
            // InternalIdioms.g:807:5: otherlv_3= RULE_ID
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
    // InternalIdioms.g:825:1: entryRuleReferredLocator returns [EObject current=null] : iv_ruleReferredLocator= ruleReferredLocator EOF ;
    public final EObject entryRuleReferredLocator() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleReferredLocator = null;


        try {
            // InternalIdioms.g:825:56: (iv_ruleReferredLocator= ruleReferredLocator EOF )
            // InternalIdioms.g:826:2: iv_ruleReferredLocator= ruleReferredLocator EOF
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
    // InternalIdioms.g:832:1: ruleReferredLocator returns [EObject current=null] : ( ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '::' )? ( (otherlv_2= RULE_ID ) ) ) ;
    public final EObject ruleReferredLocator() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;


        	enterRule();

        try {
            // InternalIdioms.g:838:2: ( ( ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '::' )? ( (otherlv_2= RULE_ID ) ) ) )
            // InternalIdioms.g:839:2: ( ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '::' )? ( (otherlv_2= RULE_ID ) ) )
            {
            // InternalIdioms.g:839:2: ( ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '::' )? ( (otherlv_2= RULE_ID ) ) )
            // InternalIdioms.g:840:3: ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '::' )? ( (otherlv_2= RULE_ID ) )
            {
            // InternalIdioms.g:840:3: ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '::' )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==RULE_ID) ) {
                int LA13_1 = input.LA(2);

                if ( (LA13_1==21) ) {
                    alt13=1;
                }
            }
            switch (alt13) {
                case 1 :
                    // InternalIdioms.g:841:4: ( (otherlv_0= RULE_ID ) ) otherlv_1= '::'
                    {
                    // InternalIdioms.g:841:4: ( (otherlv_0= RULE_ID ) )
                    // InternalIdioms.g:842:5: (otherlv_0= RULE_ID )
                    {
                    // InternalIdioms.g:842:5: (otherlv_0= RULE_ID )
                    // InternalIdioms.g:843:6: otherlv_0= RULE_ID
                    {
                    if ( state.backtracking==0 ) {

                      						/* */

                    }
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElement(grammarAccess.getReferredLocatorRule());
                      						}

                    }
                    otherlv_0=(Token)match(input,RULE_ID,FollowSets000.FOLLOW_13); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						newLeafNode(otherlv_0, grammarAccess.getReferredLocatorAccess().getIdiomsModelIdiomsModelCrossReference_0_0_0());

                    }

                    }


                    }

                    otherlv_1=(Token)match(input,21,FollowSets000.FOLLOW_3); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_1, grammarAccess.getReferredLocatorAccess().getColonColonKeyword_0_1());

                    }

                    }
                    break;

            }

            // InternalIdioms.g:862:3: ( (otherlv_2= RULE_ID ) )
            // InternalIdioms.g:863:4: (otherlv_2= RULE_ID )
            {
            // InternalIdioms.g:863:4: (otherlv_2= RULE_ID )
            // InternalIdioms.g:864:5: otherlv_2= RULE_ID
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


    // $ANTLR start "entryRuleSegmentDeclaration"
    // InternalIdioms.g:882:1: entryRuleSegmentDeclaration returns [EObject current=null] : iv_ruleSegmentDeclaration= ruleSegmentDeclaration EOF ;
    public final EObject entryRuleSegmentDeclaration() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSegmentDeclaration = null;


        try {
            // InternalIdioms.g:882:59: (iv_ruleSegmentDeclaration= ruleSegmentDeclaration EOF )
            // InternalIdioms.g:883:2: iv_ruleSegmentDeclaration= ruleSegmentDeclaration EOF
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
    // InternalIdioms.g:889:1: ruleSegmentDeclaration returns [EObject current=null] : (otherlv_0= 'segment' ( (lv_name_1_0= RULE_ID ) ) ( (lv_ownedSegment_2_0= ruleSegment ) ) otherlv_3= ';' ) ;
    public final EObject ruleSegmentDeclaration() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_1_0=null;
        Token otherlv_3=null;
        EObject lv_ownedSegment_2_0 = null;



        	enterRule();

        try {
            // InternalIdioms.g:895:2: ( (otherlv_0= 'segment' ( (lv_name_1_0= RULE_ID ) ) ( (lv_ownedSegment_2_0= ruleSegment ) ) otherlv_3= ';' ) )
            // InternalIdioms.g:896:2: (otherlv_0= 'segment' ( (lv_name_1_0= RULE_ID ) ) ( (lv_ownedSegment_2_0= ruleSegment ) ) otherlv_3= ';' )
            {
            // InternalIdioms.g:896:2: (otherlv_0= 'segment' ( (lv_name_1_0= RULE_ID ) ) ( (lv_ownedSegment_2_0= ruleSegment ) ) otherlv_3= ';' )
            // InternalIdioms.g:897:3: otherlv_0= 'segment' ( (lv_name_1_0= RULE_ID ) ) ( (lv_ownedSegment_2_0= ruleSegment ) ) otherlv_3= ';'
            {
            otherlv_0=(Token)match(input,24,FollowSets000.FOLLOW_3); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getSegmentDeclarationAccess().getSegmentKeyword_0());

            }
            // InternalIdioms.g:901:3: ( (lv_name_1_0= RULE_ID ) )
            // InternalIdioms.g:902:4: (lv_name_1_0= RULE_ID )
            {
            // InternalIdioms.g:902:4: (lv_name_1_0= RULE_ID )
            // InternalIdioms.g:903:5: lv_name_1_0= RULE_ID
            {
            lv_name_1_0=(Token)match(input,RULE_ID,FollowSets000.FOLLOW_14); if (state.failed) return current;
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
              						"org.eclipse.ocl.examples.xtext.idioms.Idioms.ID");

            }

            }


            }

            // InternalIdioms.g:919:3: ( (lv_ownedSegment_2_0= ruleSegment ) )
            // InternalIdioms.g:920:4: (lv_ownedSegment_2_0= ruleSegment )
            {
            // InternalIdioms.g:920:4: (lv_ownedSegment_2_0= ruleSegment )
            // InternalIdioms.g:921:5: lv_ownedSegment_2_0= ruleSegment
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getSegmentDeclarationAccess().getOwnedSegmentSegmentParserRuleCall_2_0());

            }
            pushFollow(FollowSets000.FOLLOW_12);
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
              						"org.eclipse.ocl.examples.xtext.idioms.Idioms.Segment");
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
    // InternalIdioms.g:946:1: entryRuleSegment returns [EObject current=null] : iv_ruleSegment= ruleSegment EOF ;
    public final EObject entryRuleSegment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSegment = null;


        try {
            // InternalIdioms.g:946:48: (iv_ruleSegment= ruleSegment EOF )
            // InternalIdioms.g:947:2: iv_ruleSegment= ruleSegment EOF
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
    // InternalIdioms.g:953:1: ruleSegment returns [EObject current=null] : (this_CustomSegment_0= ruleCustomSegment | this_HalfNewLineSegment_1= ruleHalfNewLineSegment | this_NewLineSegment_2= ruleNewLineSegment | this_NoSpaceSegment_3= ruleNoSpaceSegment | this_PopSegment_4= rulePopSegment | this_PostCommentSegment_5= rulePostCommentSegment | this_PreCommentSegment_6= rulePreCommentSegment | this_PushSegment_7= rulePushSegment | this_SoftNewLineSegment_8= ruleSoftNewLineSegment | this_SoftSpaceSegment_9= ruleSoftSpaceSegment | this_StringSegment_10= ruleStringSegment | this_ValueSegment_11= ruleValueSegment | this_WrapAnchorSegment_12= ruleWrapAnchorSegment | this_WrapBeginAllSegment_13= ruleWrapBeginAllSegment | this_WrapBeginSomeSegment_14= ruleWrapBeginSomeSegment | this_WrapEndSegment_15= ruleWrapEndSegment | this_WrapHereSegment_16= ruleWrapHereSegment ) ;
    public final EObject ruleSegment() throws RecognitionException {
        EObject current = null;

        EObject this_CustomSegment_0 = null;

        EObject this_HalfNewLineSegment_1 = null;

        EObject this_NewLineSegment_2 = null;

        EObject this_NoSpaceSegment_3 = null;

        EObject this_PopSegment_4 = null;

        EObject this_PostCommentSegment_5 = null;

        EObject this_PreCommentSegment_6 = null;

        EObject this_PushSegment_7 = null;

        EObject this_SoftNewLineSegment_8 = null;

        EObject this_SoftSpaceSegment_9 = null;

        EObject this_StringSegment_10 = null;

        EObject this_ValueSegment_11 = null;

        EObject this_WrapAnchorSegment_12 = null;

        EObject this_WrapBeginAllSegment_13 = null;

        EObject this_WrapBeginSomeSegment_14 = null;

        EObject this_WrapEndSegment_15 = null;

        EObject this_WrapHereSegment_16 = null;



        	enterRule();

        try {
            // InternalIdioms.g:959:2: ( (this_CustomSegment_0= ruleCustomSegment | this_HalfNewLineSegment_1= ruleHalfNewLineSegment | this_NewLineSegment_2= ruleNewLineSegment | this_NoSpaceSegment_3= ruleNoSpaceSegment | this_PopSegment_4= rulePopSegment | this_PostCommentSegment_5= rulePostCommentSegment | this_PreCommentSegment_6= rulePreCommentSegment | this_PushSegment_7= rulePushSegment | this_SoftNewLineSegment_8= ruleSoftNewLineSegment | this_SoftSpaceSegment_9= ruleSoftSpaceSegment | this_StringSegment_10= ruleStringSegment | this_ValueSegment_11= ruleValueSegment | this_WrapAnchorSegment_12= ruleWrapAnchorSegment | this_WrapBeginAllSegment_13= ruleWrapBeginAllSegment | this_WrapBeginSomeSegment_14= ruleWrapBeginSomeSegment | this_WrapEndSegment_15= ruleWrapEndSegment | this_WrapHereSegment_16= ruleWrapHereSegment ) )
            // InternalIdioms.g:960:2: (this_CustomSegment_0= ruleCustomSegment | this_HalfNewLineSegment_1= ruleHalfNewLineSegment | this_NewLineSegment_2= ruleNewLineSegment | this_NoSpaceSegment_3= ruleNoSpaceSegment | this_PopSegment_4= rulePopSegment | this_PostCommentSegment_5= rulePostCommentSegment | this_PreCommentSegment_6= rulePreCommentSegment | this_PushSegment_7= rulePushSegment | this_SoftNewLineSegment_8= ruleSoftNewLineSegment | this_SoftSpaceSegment_9= ruleSoftSpaceSegment | this_StringSegment_10= ruleStringSegment | this_ValueSegment_11= ruleValueSegment | this_WrapAnchorSegment_12= ruleWrapAnchorSegment | this_WrapBeginAllSegment_13= ruleWrapBeginAllSegment | this_WrapBeginSomeSegment_14= ruleWrapBeginSomeSegment | this_WrapEndSegment_15= ruleWrapEndSegment | this_WrapHereSegment_16= ruleWrapHereSegment )
            {
            // InternalIdioms.g:960:2: (this_CustomSegment_0= ruleCustomSegment | this_HalfNewLineSegment_1= ruleHalfNewLineSegment | this_NewLineSegment_2= ruleNewLineSegment | this_NoSpaceSegment_3= ruleNoSpaceSegment | this_PopSegment_4= rulePopSegment | this_PostCommentSegment_5= rulePostCommentSegment | this_PreCommentSegment_6= rulePreCommentSegment | this_PushSegment_7= rulePushSegment | this_SoftNewLineSegment_8= ruleSoftNewLineSegment | this_SoftSpaceSegment_9= ruleSoftSpaceSegment | this_StringSegment_10= ruleStringSegment | this_ValueSegment_11= ruleValueSegment | this_WrapAnchorSegment_12= ruleWrapAnchorSegment | this_WrapBeginAllSegment_13= ruleWrapBeginAllSegment | this_WrapBeginSomeSegment_14= ruleWrapBeginSomeSegment | this_WrapEndSegment_15= ruleWrapEndSegment | this_WrapHereSegment_16= ruleWrapHereSegment )
            int alt14=17;
            switch ( input.LA(1) ) {
            case 25:
                {
                alt14=1;
                }
                break;
            case 26:
                {
                alt14=2;
                }
                break;
            case 27:
                {
                alt14=3;
                }
                break;
            case 28:
                {
                alt14=4;
                }
                break;
            case 29:
                {
                alt14=5;
                }
                break;
            case 30:
                {
                alt14=6;
                }
                break;
            case 31:
                {
                alt14=7;
                }
                break;
            case 32:
                {
                alt14=8;
                }
                break;
            case 33:
                {
                alt14=9;
                }
                break;
            case 34:
                {
                alt14=10;
                }
                break;
            case 35:
                {
                alt14=11;
                }
                break;
            case 37:
                {
                alt14=12;
                }
                break;
            case 38:
                {
                alt14=13;
                }
                break;
            case 39:
                {
                alt14=14;
                }
                break;
            case 40:
                {
                alt14=15;
                }
                break;
            case 41:
                {
                alt14=16;
                }
                break;
            case 42:
                {
                alt14=17;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 14, 0, input);

                throw nvae;
            }

            switch (alt14) {
                case 1 :
                    // InternalIdioms.g:961:3: this_CustomSegment_0= ruleCustomSegment
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
                    // InternalIdioms.g:973:3: this_HalfNewLineSegment_1= ruleHalfNewLineSegment
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
                    // InternalIdioms.g:985:3: this_NewLineSegment_2= ruleNewLineSegment
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
                    // InternalIdioms.g:997:3: this_NoSpaceSegment_3= ruleNoSpaceSegment
                    {
                    if ( state.backtracking==0 ) {

                      			/* */

                    }
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getSegmentAccess().getNoSpaceSegmentParserRuleCall_3());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_NoSpaceSegment_3=ruleNoSpaceSegment();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_NoSpaceSegment_3;
                      			afterParserOrEnumRuleCall();

                    }

                    }
                    break;
                case 5 :
                    // InternalIdioms.g:1009:3: this_PopSegment_4= rulePopSegment
                    {
                    if ( state.backtracking==0 ) {

                      			/* */

                    }
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getSegmentAccess().getPopSegmentParserRuleCall_4());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_PopSegment_4=rulePopSegment();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_PopSegment_4;
                      			afterParserOrEnumRuleCall();

                    }

                    }
                    break;
                case 6 :
                    // InternalIdioms.g:1021:3: this_PostCommentSegment_5= rulePostCommentSegment
                    {
                    if ( state.backtracking==0 ) {

                      			/* */

                    }
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getSegmentAccess().getPostCommentSegmentParserRuleCall_5());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_PostCommentSegment_5=rulePostCommentSegment();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_PostCommentSegment_5;
                      			afterParserOrEnumRuleCall();

                    }

                    }
                    break;
                case 7 :
                    // InternalIdioms.g:1033:3: this_PreCommentSegment_6= rulePreCommentSegment
                    {
                    if ( state.backtracking==0 ) {

                      			/* */

                    }
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getSegmentAccess().getPreCommentSegmentParserRuleCall_6());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_PreCommentSegment_6=rulePreCommentSegment();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_PreCommentSegment_6;
                      			afterParserOrEnumRuleCall();

                    }

                    }
                    break;
                case 8 :
                    // InternalIdioms.g:1045:3: this_PushSegment_7= rulePushSegment
                    {
                    if ( state.backtracking==0 ) {

                      			/* */

                    }
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getSegmentAccess().getPushSegmentParserRuleCall_7());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_PushSegment_7=rulePushSegment();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_PushSegment_7;
                      			afterParserOrEnumRuleCall();

                    }

                    }
                    break;
                case 9 :
                    // InternalIdioms.g:1057:3: this_SoftNewLineSegment_8= ruleSoftNewLineSegment
                    {
                    if ( state.backtracking==0 ) {

                      			/* */

                    }
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getSegmentAccess().getSoftNewLineSegmentParserRuleCall_8());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_SoftNewLineSegment_8=ruleSoftNewLineSegment();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_SoftNewLineSegment_8;
                      			afterParserOrEnumRuleCall();

                    }

                    }
                    break;
                case 10 :
                    // InternalIdioms.g:1069:3: this_SoftSpaceSegment_9= ruleSoftSpaceSegment
                    {
                    if ( state.backtracking==0 ) {

                      			/* */

                    }
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getSegmentAccess().getSoftSpaceSegmentParserRuleCall_9());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_SoftSpaceSegment_9=ruleSoftSpaceSegment();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_SoftSpaceSegment_9;
                      			afterParserOrEnumRuleCall();

                    }

                    }
                    break;
                case 11 :
                    // InternalIdioms.g:1081:3: this_StringSegment_10= ruleStringSegment
                    {
                    if ( state.backtracking==0 ) {

                      			/* */

                    }
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getSegmentAccess().getStringSegmentParserRuleCall_10());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_StringSegment_10=ruleStringSegment();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_StringSegment_10;
                      			afterParserOrEnumRuleCall();

                    }

                    }
                    break;
                case 12 :
                    // InternalIdioms.g:1093:3: this_ValueSegment_11= ruleValueSegment
                    {
                    if ( state.backtracking==0 ) {

                      			/* */

                    }
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getSegmentAccess().getValueSegmentParserRuleCall_11());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_ValueSegment_11=ruleValueSegment();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_ValueSegment_11;
                      			afterParserOrEnumRuleCall();

                    }

                    }
                    break;
                case 13 :
                    // InternalIdioms.g:1105:3: this_WrapAnchorSegment_12= ruleWrapAnchorSegment
                    {
                    if ( state.backtracking==0 ) {

                      			/* */

                    }
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getSegmentAccess().getWrapAnchorSegmentParserRuleCall_12());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_WrapAnchorSegment_12=ruleWrapAnchorSegment();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_WrapAnchorSegment_12;
                      			afterParserOrEnumRuleCall();

                    }

                    }
                    break;
                case 14 :
                    // InternalIdioms.g:1117:3: this_WrapBeginAllSegment_13= ruleWrapBeginAllSegment
                    {
                    if ( state.backtracking==0 ) {

                      			/* */

                    }
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getSegmentAccess().getWrapBeginAllSegmentParserRuleCall_13());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_WrapBeginAllSegment_13=ruleWrapBeginAllSegment();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_WrapBeginAllSegment_13;
                      			afterParserOrEnumRuleCall();

                    }

                    }
                    break;
                case 15 :
                    // InternalIdioms.g:1129:3: this_WrapBeginSomeSegment_14= ruleWrapBeginSomeSegment
                    {
                    if ( state.backtracking==0 ) {

                      			/* */

                    }
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getSegmentAccess().getWrapBeginSomeSegmentParserRuleCall_14());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_WrapBeginSomeSegment_14=ruleWrapBeginSomeSegment();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_WrapBeginSomeSegment_14;
                      			afterParserOrEnumRuleCall();

                    }

                    }
                    break;
                case 16 :
                    // InternalIdioms.g:1141:3: this_WrapEndSegment_15= ruleWrapEndSegment
                    {
                    if ( state.backtracking==0 ) {

                      			/* */

                    }
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getSegmentAccess().getWrapEndSegmentParserRuleCall_15());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_WrapEndSegment_15=ruleWrapEndSegment();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_WrapEndSegment_15;
                      			afterParserOrEnumRuleCall();

                    }

                    }
                    break;
                case 17 :
                    // InternalIdioms.g:1153:3: this_WrapHereSegment_16= ruleWrapHereSegment
                    {
                    if ( state.backtracking==0 ) {

                      			/* */

                    }
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getSegmentAccess().getWrapHereSegmentParserRuleCall_16());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_WrapHereSegment_16=ruleWrapHereSegment();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_WrapHereSegment_16;
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
    // InternalIdioms.g:1168:1: entryRuleCustomSegment returns [EObject current=null] : iv_ruleCustomSegment= ruleCustomSegment EOF ;
    public final EObject entryRuleCustomSegment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCustomSegment = null;


        try {
            // InternalIdioms.g:1168:54: (iv_ruleCustomSegment= ruleCustomSegment EOF )
            // InternalIdioms.g:1169:2: iv_ruleCustomSegment= ruleCustomSegment EOF
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
    // InternalIdioms.g:1175:1: ruleCustomSegment returns [EObject current=null] : (otherlv_0= 'custom' ( (lv_supportClassName_1_0= RULE_STRING ) ) ) ;
    public final EObject ruleCustomSegment() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_supportClassName_1_0=null;


        	enterRule();

        try {
            // InternalIdioms.g:1181:2: ( (otherlv_0= 'custom' ( (lv_supportClassName_1_0= RULE_STRING ) ) ) )
            // InternalIdioms.g:1182:2: (otherlv_0= 'custom' ( (lv_supportClassName_1_0= RULE_STRING ) ) )
            {
            // InternalIdioms.g:1182:2: (otherlv_0= 'custom' ( (lv_supportClassName_1_0= RULE_STRING ) ) )
            // InternalIdioms.g:1183:3: otherlv_0= 'custom' ( (lv_supportClassName_1_0= RULE_STRING ) )
            {
            otherlv_0=(Token)match(input,25,FollowSets000.FOLLOW_8); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getCustomSegmentAccess().getCustomKeyword_0());

            }
            // InternalIdioms.g:1187:3: ( (lv_supportClassName_1_0= RULE_STRING ) )
            // InternalIdioms.g:1188:4: (lv_supportClassName_1_0= RULE_STRING )
            {
            // InternalIdioms.g:1188:4: (lv_supportClassName_1_0= RULE_STRING )
            // InternalIdioms.g:1189:5: lv_supportClassName_1_0= RULE_STRING
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
              						"org.eclipse.ocl.examples.xtext.idioms.Idioms.STRING");

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
    // InternalIdioms.g:1209:1: entryRuleHalfNewLineSegment returns [EObject current=null] : iv_ruleHalfNewLineSegment= ruleHalfNewLineSegment EOF ;
    public final EObject entryRuleHalfNewLineSegment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleHalfNewLineSegment = null;


        try {
            // InternalIdioms.g:1209:59: (iv_ruleHalfNewLineSegment= ruleHalfNewLineSegment EOF )
            // InternalIdioms.g:1210:2: iv_ruleHalfNewLineSegment= ruleHalfNewLineSegment EOF
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
    // InternalIdioms.g:1216:1: ruleHalfNewLineSegment returns [EObject current=null] : ( () otherlv_1= 'half-new-line' ) ;
    public final EObject ruleHalfNewLineSegment() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;


        	enterRule();

        try {
            // InternalIdioms.g:1222:2: ( ( () otherlv_1= 'half-new-line' ) )
            // InternalIdioms.g:1223:2: ( () otherlv_1= 'half-new-line' )
            {
            // InternalIdioms.g:1223:2: ( () otherlv_1= 'half-new-line' )
            // InternalIdioms.g:1224:3: () otherlv_1= 'half-new-line'
            {
            // InternalIdioms.g:1224:3: ()
            // InternalIdioms.g:1225:4:
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

            otherlv_1=(Token)match(input,26,FollowSets000.FOLLOW_2); if (state.failed) return current;
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
    // InternalIdioms.g:1242:1: entryRuleNewLineSegment returns [EObject current=null] : iv_ruleNewLineSegment= ruleNewLineSegment EOF ;
    public final EObject entryRuleNewLineSegment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNewLineSegment = null;


        try {
            // InternalIdioms.g:1242:55: (iv_ruleNewLineSegment= ruleNewLineSegment EOF )
            // InternalIdioms.g:1243:2: iv_ruleNewLineSegment= ruleNewLineSegment EOF
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
    // InternalIdioms.g:1249:1: ruleNewLineSegment returns [EObject current=null] : ( () otherlv_1= 'new-line' ) ;
    public final EObject ruleNewLineSegment() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;


        	enterRule();

        try {
            // InternalIdioms.g:1255:2: ( ( () otherlv_1= 'new-line' ) )
            // InternalIdioms.g:1256:2: ( () otherlv_1= 'new-line' )
            {
            // InternalIdioms.g:1256:2: ( () otherlv_1= 'new-line' )
            // InternalIdioms.g:1257:3: () otherlv_1= 'new-line'
            {
            // InternalIdioms.g:1257:3: ()
            // InternalIdioms.g:1258:4:
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

            otherlv_1=(Token)match(input,27,FollowSets000.FOLLOW_2); if (state.failed) return current;
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


    // $ANTLR start "entryRuleNoSpaceSegment"
    // InternalIdioms.g:1275:1: entryRuleNoSpaceSegment returns [EObject current=null] : iv_ruleNoSpaceSegment= ruleNoSpaceSegment EOF ;
    public final EObject entryRuleNoSpaceSegment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNoSpaceSegment = null;


        try {
            // InternalIdioms.g:1275:55: (iv_ruleNoSpaceSegment= ruleNoSpaceSegment EOF )
            // InternalIdioms.g:1276:2: iv_ruleNoSpaceSegment= ruleNoSpaceSegment EOF
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
    // InternalIdioms.g:1282:1: ruleNoSpaceSegment returns [EObject current=null] : ( () otherlv_1= 'no-space' ) ;
    public final EObject ruleNoSpaceSegment() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;


        	enterRule();

        try {
            // InternalIdioms.g:1288:2: ( ( () otherlv_1= 'no-space' ) )
            // InternalIdioms.g:1289:2: ( () otherlv_1= 'no-space' )
            {
            // InternalIdioms.g:1289:2: ( () otherlv_1= 'no-space' )
            // InternalIdioms.g:1290:3: () otherlv_1= 'no-space'
            {
            // InternalIdioms.g:1290:3: ()
            // InternalIdioms.g:1291:4:
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

            otherlv_1=(Token)match(input,28,FollowSets000.FOLLOW_2); if (state.failed) return current;
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
    // InternalIdioms.g:1308:1: entryRulePopSegment returns [EObject current=null] : iv_rulePopSegment= rulePopSegment EOF ;
    public final EObject entryRulePopSegment() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePopSegment = null;


        try {
            // InternalIdioms.g:1308:51: (iv_rulePopSegment= rulePopSegment EOF )
            // InternalIdioms.g:1309:2: iv_rulePopSegment= rulePopSegment EOF
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
    // InternalIdioms.g:1315:1: rulePopSegment returns [EObject current=null] : ( () otherlv_1= 'pop' ) ;
    public final EObject rulePopSegment() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;


        	enterRule();

        try {
            // InternalIdioms.g:1321:2: ( ( () otherlv_1= 'pop' ) )
            // InternalIdioms.g:1322:2: ( () otherlv_1= 'pop' )
            {
            // InternalIdioms.g:1322:2: ( () otherlv_1= 'pop' )
            // InternalIdioms.g:1323:3: () otherlv_1= 'pop'
            {
            // InternalIdioms.g:1323:3: ()
            // InternalIdioms.g:1324:4:
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

            otherlv_1=(Token)match(input,29,FollowSets000.FOLLOW_2); if (state.failed) return current;
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
    // InternalIdioms.g:1341:1: entryRulePostCommentSegment returns [EObject current=null] : iv_rulePostCommentSegment= rulePostCommentSegment EOF ;
    public final EObject entryRulePostCommentSegment() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePostCommentSegment = null;


        try {
            // InternalIdioms.g:1341:59: (iv_rulePostCommentSegment= rulePostCommentSegment EOF )
            // InternalIdioms.g:1342:2: iv_rulePostCommentSegment= rulePostCommentSegment EOF
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
    // InternalIdioms.g:1348:1: rulePostCommentSegment returns [EObject current=null] : ( () otherlv_1= 'post-comment' ) ;
    public final EObject rulePostCommentSegment() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;


        	enterRule();

        try {
            // InternalIdioms.g:1354:2: ( ( () otherlv_1= 'post-comment' ) )
            // InternalIdioms.g:1355:2: ( () otherlv_1= 'post-comment' )
            {
            // InternalIdioms.g:1355:2: ( () otherlv_1= 'post-comment' )
            // InternalIdioms.g:1356:3: () otherlv_1= 'post-comment'
            {
            // InternalIdioms.g:1356:3: ()
            // InternalIdioms.g:1357:4:
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

            otherlv_1=(Token)match(input,30,FollowSets000.FOLLOW_2); if (state.failed) return current;
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
    // InternalIdioms.g:1374:1: entryRulePreCommentSegment returns [EObject current=null] : iv_rulePreCommentSegment= rulePreCommentSegment EOF ;
    public final EObject entryRulePreCommentSegment() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePreCommentSegment = null;


        try {
            // InternalIdioms.g:1374:58: (iv_rulePreCommentSegment= rulePreCommentSegment EOF )
            // InternalIdioms.g:1375:2: iv_rulePreCommentSegment= rulePreCommentSegment EOF
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
    // InternalIdioms.g:1381:1: rulePreCommentSegment returns [EObject current=null] : ( () otherlv_1= 'pre-comment' ) ;
    public final EObject rulePreCommentSegment() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;


        	enterRule();

        try {
            // InternalIdioms.g:1387:2: ( ( () otherlv_1= 'pre-comment' ) )
            // InternalIdioms.g:1388:2: ( () otherlv_1= 'pre-comment' )
            {
            // InternalIdioms.g:1388:2: ( () otherlv_1= 'pre-comment' )
            // InternalIdioms.g:1389:3: () otherlv_1= 'pre-comment'
            {
            // InternalIdioms.g:1389:3: ()
            // InternalIdioms.g:1390:4:
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

            otherlv_1=(Token)match(input,31,FollowSets000.FOLLOW_2); if (state.failed) return current;
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
    // InternalIdioms.g:1407:1: entryRulePushSegment returns [EObject current=null] : iv_rulePushSegment= rulePushSegment EOF ;
    public final EObject entryRulePushSegment() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePushSegment = null;


        try {
            // InternalIdioms.g:1407:52: (iv_rulePushSegment= rulePushSegment EOF )
            // InternalIdioms.g:1408:2: iv_rulePushSegment= rulePushSegment EOF
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
    // InternalIdioms.g:1414:1: rulePushSegment returns [EObject current=null] : ( () otherlv_1= 'push' ) ;
    public final EObject rulePushSegment() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;


        	enterRule();

        try {
            // InternalIdioms.g:1420:2: ( ( () otherlv_1= 'push' ) )
            // InternalIdioms.g:1421:2: ( () otherlv_1= 'push' )
            {
            // InternalIdioms.g:1421:2: ( () otherlv_1= 'push' )
            // InternalIdioms.g:1422:3: () otherlv_1= 'push'
            {
            // InternalIdioms.g:1422:3: ()
            // InternalIdioms.g:1423:4:
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

            otherlv_1=(Token)match(input,32,FollowSets000.FOLLOW_2); if (state.failed) return current;
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
    // InternalIdioms.g:1440:1: entryRuleSoftNewLineSegment returns [EObject current=null] : iv_ruleSoftNewLineSegment= ruleSoftNewLineSegment EOF ;
    public final EObject entryRuleSoftNewLineSegment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSoftNewLineSegment = null;


        try {
            // InternalIdioms.g:1440:59: (iv_ruleSoftNewLineSegment= ruleSoftNewLineSegment EOF )
            // InternalIdioms.g:1441:2: iv_ruleSoftNewLineSegment= ruleSoftNewLineSegment EOF
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
    // InternalIdioms.g:1447:1: ruleSoftNewLineSegment returns [EObject current=null] : ( () otherlv_1= 'soft-new-line' ) ;
    public final EObject ruleSoftNewLineSegment() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;


        	enterRule();

        try {
            // InternalIdioms.g:1453:2: ( ( () otherlv_1= 'soft-new-line' ) )
            // InternalIdioms.g:1454:2: ( () otherlv_1= 'soft-new-line' )
            {
            // InternalIdioms.g:1454:2: ( () otherlv_1= 'soft-new-line' )
            // InternalIdioms.g:1455:3: () otherlv_1= 'soft-new-line'
            {
            // InternalIdioms.g:1455:3: ()
            // InternalIdioms.g:1456:4:
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

            otherlv_1=(Token)match(input,33,FollowSets000.FOLLOW_2); if (state.failed) return current;
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
    // InternalIdioms.g:1473:1: entryRuleSoftSpaceSegment returns [EObject current=null] : iv_ruleSoftSpaceSegment= ruleSoftSpaceSegment EOF ;
    public final EObject entryRuleSoftSpaceSegment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSoftSpaceSegment = null;


        try {
            // InternalIdioms.g:1473:57: (iv_ruleSoftSpaceSegment= ruleSoftSpaceSegment EOF )
            // InternalIdioms.g:1474:2: iv_ruleSoftSpaceSegment= ruleSoftSpaceSegment EOF
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
    // InternalIdioms.g:1480:1: ruleSoftSpaceSegment returns [EObject current=null] : ( () otherlv_1= 'soft-space' ) ;
    public final EObject ruleSoftSpaceSegment() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;


        	enterRule();

        try {
            // InternalIdioms.g:1486:2: ( ( () otherlv_1= 'soft-space' ) )
            // InternalIdioms.g:1487:2: ( () otherlv_1= 'soft-space' )
            {
            // InternalIdioms.g:1487:2: ( () otherlv_1= 'soft-space' )
            // InternalIdioms.g:1488:3: () otherlv_1= 'soft-space'
            {
            // InternalIdioms.g:1488:3: ()
            // InternalIdioms.g:1489:4:
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

            otherlv_1=(Token)match(input,34,FollowSets000.FOLLOW_2); if (state.failed) return current;
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
    // InternalIdioms.g:1506:1: entryRuleStringSegment returns [EObject current=null] : iv_ruleStringSegment= ruleStringSegment EOF ;
    public final EObject entryRuleStringSegment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleStringSegment = null;


        try {
            // InternalIdioms.g:1506:54: (iv_ruleStringSegment= ruleStringSegment EOF )
            // InternalIdioms.g:1507:2: iv_ruleStringSegment= ruleStringSegment EOF
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
    // InternalIdioms.g:1513:1: ruleStringSegment returns [EObject current=null] : (otherlv_0= 'string' ( (lv_string_1_0= RULE_STRING ) ) ( (lv_printable_2_0= 'printable' ) ) ) ;
    public final EObject ruleStringSegment() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_string_1_0=null;
        Token lv_printable_2_0=null;


        	enterRule();

        try {
            // InternalIdioms.g:1519:2: ( (otherlv_0= 'string' ( (lv_string_1_0= RULE_STRING ) ) ( (lv_printable_2_0= 'printable' ) ) ) )
            // InternalIdioms.g:1520:2: (otherlv_0= 'string' ( (lv_string_1_0= RULE_STRING ) ) ( (lv_printable_2_0= 'printable' ) ) )
            {
            // InternalIdioms.g:1520:2: (otherlv_0= 'string' ( (lv_string_1_0= RULE_STRING ) ) ( (lv_printable_2_0= 'printable' ) ) )
            // InternalIdioms.g:1521:3: otherlv_0= 'string' ( (lv_string_1_0= RULE_STRING ) ) ( (lv_printable_2_0= 'printable' ) )
            {
            otherlv_0=(Token)match(input,35,FollowSets000.FOLLOW_8); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getStringSegmentAccess().getStringKeyword_0());

            }
            // InternalIdioms.g:1525:3: ( (lv_string_1_0= RULE_STRING ) )
            // InternalIdioms.g:1526:4: (lv_string_1_0= RULE_STRING )
            {
            // InternalIdioms.g:1526:4: (lv_string_1_0= RULE_STRING )
            // InternalIdioms.g:1527:5: lv_string_1_0= RULE_STRING
            {
            lv_string_1_0=(Token)match(input,RULE_STRING,FollowSets000.FOLLOW_15); if (state.failed) return current;
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
              						"org.eclipse.ocl.examples.xtext.idioms.Idioms.STRING");

            }

            }


            }

            // InternalIdioms.g:1543:3: ( (lv_printable_2_0= 'printable' ) )
            // InternalIdioms.g:1544:4: (lv_printable_2_0= 'printable' )
            {
            // InternalIdioms.g:1544:4: (lv_printable_2_0= 'printable' )
            // InternalIdioms.g:1545:5: lv_printable_2_0= 'printable'
            {
            lv_printable_2_0=(Token)match(input,36,FollowSets000.FOLLOW_2); if (state.failed) return current;
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
    // InternalIdioms.g:1561:1: entryRuleValueSegment returns [EObject current=null] : iv_ruleValueSegment= ruleValueSegment EOF ;
    public final EObject entryRuleValueSegment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleValueSegment = null;


        try {
            // InternalIdioms.g:1561:53: (iv_ruleValueSegment= ruleValueSegment EOF )
            // InternalIdioms.g:1562:2: iv_ruleValueSegment= ruleValueSegment EOF
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
    // InternalIdioms.g:1568:1: ruleValueSegment returns [EObject current=null] : ( () otherlv_1= 'value' ) ;
    public final EObject ruleValueSegment() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;


        	enterRule();

        try {
            // InternalIdioms.g:1574:2: ( ( () otherlv_1= 'value' ) )
            // InternalIdioms.g:1575:2: ( () otherlv_1= 'value' )
            {
            // InternalIdioms.g:1575:2: ( () otherlv_1= 'value' )
            // InternalIdioms.g:1576:3: () otherlv_1= 'value'
            {
            // InternalIdioms.g:1576:3: ()
            // InternalIdioms.g:1577:4:
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

            otherlv_1=(Token)match(input,37,FollowSets000.FOLLOW_2); if (state.failed) return current;
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
    // InternalIdioms.g:1594:1: entryRuleWrapAnchorSegment returns [EObject current=null] : iv_ruleWrapAnchorSegment= ruleWrapAnchorSegment EOF ;
    public final EObject entryRuleWrapAnchorSegment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleWrapAnchorSegment = null;


        try {
            // InternalIdioms.g:1594:58: (iv_ruleWrapAnchorSegment= ruleWrapAnchorSegment EOF )
            // InternalIdioms.g:1595:2: iv_ruleWrapAnchorSegment= ruleWrapAnchorSegment EOF
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
    // InternalIdioms.g:1601:1: ruleWrapAnchorSegment returns [EObject current=null] : ( () otherlv_1= 'wrap-anchor' ) ;
    public final EObject ruleWrapAnchorSegment() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;


        	enterRule();

        try {
            // InternalIdioms.g:1607:2: ( ( () otherlv_1= 'wrap-anchor' ) )
            // InternalIdioms.g:1608:2: ( () otherlv_1= 'wrap-anchor' )
            {
            // InternalIdioms.g:1608:2: ( () otherlv_1= 'wrap-anchor' )
            // InternalIdioms.g:1609:3: () otherlv_1= 'wrap-anchor'
            {
            // InternalIdioms.g:1609:3: ()
            // InternalIdioms.g:1610:4:
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

            otherlv_1=(Token)match(input,38,FollowSets000.FOLLOW_2); if (state.failed) return current;
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
    // InternalIdioms.g:1627:1: entryRuleWrapBeginAllSegment returns [EObject current=null] : iv_ruleWrapBeginAllSegment= ruleWrapBeginAllSegment EOF ;
    public final EObject entryRuleWrapBeginAllSegment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleWrapBeginAllSegment = null;


        try {
            // InternalIdioms.g:1627:60: (iv_ruleWrapBeginAllSegment= ruleWrapBeginAllSegment EOF )
            // InternalIdioms.g:1628:2: iv_ruleWrapBeginAllSegment= ruleWrapBeginAllSegment EOF
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
    // InternalIdioms.g:1634:1: ruleWrapBeginAllSegment returns [EObject current=null] : ( () otherlv_1= 'wrap-begin-all' ) ;
    public final EObject ruleWrapBeginAllSegment() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;


        	enterRule();

        try {
            // InternalIdioms.g:1640:2: ( ( () otherlv_1= 'wrap-begin-all' ) )
            // InternalIdioms.g:1641:2: ( () otherlv_1= 'wrap-begin-all' )
            {
            // InternalIdioms.g:1641:2: ( () otherlv_1= 'wrap-begin-all' )
            // InternalIdioms.g:1642:3: () otherlv_1= 'wrap-begin-all'
            {
            // InternalIdioms.g:1642:3: ()
            // InternalIdioms.g:1643:4:
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

            otherlv_1=(Token)match(input,39,FollowSets000.FOLLOW_2); if (state.failed) return current;
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
    // InternalIdioms.g:1660:1: entryRuleWrapBeginSomeSegment returns [EObject current=null] : iv_ruleWrapBeginSomeSegment= ruleWrapBeginSomeSegment EOF ;
    public final EObject entryRuleWrapBeginSomeSegment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleWrapBeginSomeSegment = null;


        try {
            // InternalIdioms.g:1660:61: (iv_ruleWrapBeginSomeSegment= ruleWrapBeginSomeSegment EOF )
            // InternalIdioms.g:1661:2: iv_ruleWrapBeginSomeSegment= ruleWrapBeginSomeSegment EOF
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
    // InternalIdioms.g:1667:1: ruleWrapBeginSomeSegment returns [EObject current=null] : ( () otherlv_1= 'wrap-begin-some' ) ;
    public final EObject ruleWrapBeginSomeSegment() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;


        	enterRule();

        try {
            // InternalIdioms.g:1673:2: ( ( () otherlv_1= 'wrap-begin-some' ) )
            // InternalIdioms.g:1674:2: ( () otherlv_1= 'wrap-begin-some' )
            {
            // InternalIdioms.g:1674:2: ( () otherlv_1= 'wrap-begin-some' )
            // InternalIdioms.g:1675:3: () otherlv_1= 'wrap-begin-some'
            {
            // InternalIdioms.g:1675:3: ()
            // InternalIdioms.g:1676:4:
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

            otherlv_1=(Token)match(input,40,FollowSets000.FOLLOW_2); if (state.failed) return current;
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
    // InternalIdioms.g:1693:1: entryRuleWrapEndSegment returns [EObject current=null] : iv_ruleWrapEndSegment= ruleWrapEndSegment EOF ;
    public final EObject entryRuleWrapEndSegment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleWrapEndSegment = null;


        try {
            // InternalIdioms.g:1693:55: (iv_ruleWrapEndSegment= ruleWrapEndSegment EOF )
            // InternalIdioms.g:1694:2: iv_ruleWrapEndSegment= ruleWrapEndSegment EOF
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
    // InternalIdioms.g:1700:1: ruleWrapEndSegment returns [EObject current=null] : ( () otherlv_1= 'wrap-end' ) ;
    public final EObject ruleWrapEndSegment() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;


        	enterRule();

        try {
            // InternalIdioms.g:1706:2: ( ( () otherlv_1= 'wrap-end' ) )
            // InternalIdioms.g:1707:2: ( () otherlv_1= 'wrap-end' )
            {
            // InternalIdioms.g:1707:2: ( () otherlv_1= 'wrap-end' )
            // InternalIdioms.g:1708:3: () otherlv_1= 'wrap-end'
            {
            // InternalIdioms.g:1708:3: ()
            // InternalIdioms.g:1709:4:
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

            otherlv_1=(Token)match(input,41,FollowSets000.FOLLOW_2); if (state.failed) return current;
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
    // InternalIdioms.g:1726:1: entryRuleWrapHereSegment returns [EObject current=null] : iv_ruleWrapHereSegment= ruleWrapHereSegment EOF ;
    public final EObject entryRuleWrapHereSegment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleWrapHereSegment = null;


        try {
            // InternalIdioms.g:1726:56: (iv_ruleWrapHereSegment= ruleWrapHereSegment EOF )
            // InternalIdioms.g:1727:2: iv_ruleWrapHereSegment= ruleWrapHereSegment EOF
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
    // InternalIdioms.g:1733:1: ruleWrapHereSegment returns [EObject current=null] : ( () otherlv_1= 'wrap-here' ) ;
    public final EObject ruleWrapHereSegment() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;


        	enterRule();

        try {
            // InternalIdioms.g:1739:2: ( ( () otherlv_1= 'wrap-here' ) )
            // InternalIdioms.g:1740:2: ( () otherlv_1= 'wrap-here' )
            {
            // InternalIdioms.g:1740:2: ( () otherlv_1= 'wrap-here' )
            // InternalIdioms.g:1741:3: () otherlv_1= 'wrap-here'
            {
            // InternalIdioms.g:1741:3: ()
            // InternalIdioms.g:1742:4:
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

            otherlv_1=(Token)match(input,42,FollowSets000.FOLLOW_2); if (state.failed) return current;
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
    // InternalIdioms.g:1759:1: entryRuleReferredSegment returns [EObject current=null] : iv_ruleReferredSegment= ruleReferredSegment EOF ;
    public final EObject entryRuleReferredSegment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleReferredSegment = null;


        try {
            // InternalIdioms.g:1759:56: (iv_ruleReferredSegment= ruleReferredSegment EOF )
            // InternalIdioms.g:1760:2: iv_ruleReferredSegment= ruleReferredSegment EOF
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
    // InternalIdioms.g:1766:1: ruleReferredSegment returns [EObject current=null] : ( ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '::' )? ( (otherlv_2= RULE_ID ) ) ) ;
    public final EObject ruleReferredSegment() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;


        	enterRule();

        try {
            // InternalIdioms.g:1772:2: ( ( ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '::' )? ( (otherlv_2= RULE_ID ) ) ) )
            // InternalIdioms.g:1773:2: ( ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '::' )? ( (otherlv_2= RULE_ID ) ) )
            {
            // InternalIdioms.g:1773:2: ( ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '::' )? ( (otherlv_2= RULE_ID ) ) )
            // InternalIdioms.g:1774:3: ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '::' )? ( (otherlv_2= RULE_ID ) )
            {
            // InternalIdioms.g:1774:3: ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '::' )?
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
                    // InternalIdioms.g:1775:4: ( (otherlv_0= RULE_ID ) ) otherlv_1= '::'
                    {
                    // InternalIdioms.g:1775:4: ( (otherlv_0= RULE_ID ) )
                    // InternalIdioms.g:1776:5: (otherlv_0= RULE_ID )
                    {
                    // InternalIdioms.g:1776:5: (otherlv_0= RULE_ID )
                    // InternalIdioms.g:1777:6: otherlv_0= RULE_ID
                    {
                    if ( state.backtracking==0 ) {

                      						/* */

                    }
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElement(grammarAccess.getReferredSegmentRule());
                      						}

                    }
                    otherlv_0=(Token)match(input,RULE_ID,FollowSets000.FOLLOW_13); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						newLeafNode(otherlv_0, grammarAccess.getReferredSegmentAccess().getIdiomsModelIdiomsModelCrossReference_0_0_0());

                    }

                    }


                    }

                    otherlv_1=(Token)match(input,21,FollowSets000.FOLLOW_3); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_1, grammarAccess.getReferredSegmentAccess().getColonColonKeyword_0_1());

                    }

                    }
                    break;

            }

            // InternalIdioms.g:1796:3: ( (otherlv_2= RULE_ID ) )
            // InternalIdioms.g:1797:4: (otherlv_2= RULE_ID )
            {
            // InternalIdioms.g:1797:4: (otherlv_2= RULE_ID )
            // InternalIdioms.g:1798:5: otherlv_2= RULE_ID
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
    // InternalIdioms.g:1816:1: entryRuleIdiom returns [EObject current=null] : iv_ruleIdiom= ruleIdiom EOF ;
    public final EObject entryRuleIdiom() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIdiom = null;


        try {
            // InternalIdioms.g:1816:46: (iv_ruleIdiom= ruleIdiom EOF )
            // InternalIdioms.g:1817:2: iv_ruleIdiom= ruleIdiom EOF
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
    // InternalIdioms.g:1823:1: ruleIdiom returns [EObject current=null] : ( ( (lv_mixin_0_0= 'mixin' ) )? otherlv_1= 'idiom' ( (lv_name_2_0= RULE_ID ) ) (otherlv_3= 'for' ( ( (otherlv_4= RULE_ID ) ) otherlv_5= '::' )? ( (otherlv_6= RULE_ID ) ) )? (otherlv_7= 'in' ( (lv_inRuleRegex_8_0= RULE_STRING ) ) )? ( ( (lv_ownedSubIdioms_9_0= ruleSubIdiom ) ) | (otherlv_10= '{' ( (lv_ownedSubIdioms_11_0= ruleSubIdiom ) )* otherlv_12= '}' ) ) ) ;
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
            // InternalIdioms.g:1829:2: ( ( ( (lv_mixin_0_0= 'mixin' ) )? otherlv_1= 'idiom' ( (lv_name_2_0= RULE_ID ) ) (otherlv_3= 'for' ( ( (otherlv_4= RULE_ID ) ) otherlv_5= '::' )? ( (otherlv_6= RULE_ID ) ) )? (otherlv_7= 'in' ( (lv_inRuleRegex_8_0= RULE_STRING ) ) )? ( ( (lv_ownedSubIdioms_9_0= ruleSubIdiom ) ) | (otherlv_10= '{' ( (lv_ownedSubIdioms_11_0= ruleSubIdiom ) )* otherlv_12= '}' ) ) ) )
            // InternalIdioms.g:1830:2: ( ( (lv_mixin_0_0= 'mixin' ) )? otherlv_1= 'idiom' ( (lv_name_2_0= RULE_ID ) ) (otherlv_3= 'for' ( ( (otherlv_4= RULE_ID ) ) otherlv_5= '::' )? ( (otherlv_6= RULE_ID ) ) )? (otherlv_7= 'in' ( (lv_inRuleRegex_8_0= RULE_STRING ) ) )? ( ( (lv_ownedSubIdioms_9_0= ruleSubIdiom ) ) | (otherlv_10= '{' ( (lv_ownedSubIdioms_11_0= ruleSubIdiom ) )* otherlv_12= '}' ) ) )
            {
            // InternalIdioms.g:1830:2: ( ( (lv_mixin_0_0= 'mixin' ) )? otherlv_1= 'idiom' ( (lv_name_2_0= RULE_ID ) ) (otherlv_3= 'for' ( ( (otherlv_4= RULE_ID ) ) otherlv_5= '::' )? ( (otherlv_6= RULE_ID ) ) )? (otherlv_7= 'in' ( (lv_inRuleRegex_8_0= RULE_STRING ) ) )? ( ( (lv_ownedSubIdioms_9_0= ruleSubIdiom ) ) | (otherlv_10= '{' ( (lv_ownedSubIdioms_11_0= ruleSubIdiom ) )* otherlv_12= '}' ) ) )
            // InternalIdioms.g:1831:3: ( (lv_mixin_0_0= 'mixin' ) )? otherlv_1= 'idiom' ( (lv_name_2_0= RULE_ID ) ) (otherlv_3= 'for' ( ( (otherlv_4= RULE_ID ) ) otherlv_5= '::' )? ( (otherlv_6= RULE_ID ) ) )? (otherlv_7= 'in' ( (lv_inRuleRegex_8_0= RULE_STRING ) ) )? ( ( (lv_ownedSubIdioms_9_0= ruleSubIdiom ) ) | (otherlv_10= '{' ( (lv_ownedSubIdioms_11_0= ruleSubIdiom ) )* otherlv_12= '}' ) )
            {
            // InternalIdioms.g:1831:3: ( (lv_mixin_0_0= 'mixin' ) )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==43) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // InternalIdioms.g:1832:4: (lv_mixin_0_0= 'mixin' )
                    {
                    // InternalIdioms.g:1832:4: (lv_mixin_0_0= 'mixin' )
                    // InternalIdioms.g:1833:5: lv_mixin_0_0= 'mixin'
                    {
                    lv_mixin_0_0=(Token)match(input,43,FollowSets000.FOLLOW_16); if (state.failed) return current;
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

            otherlv_1=(Token)match(input,44,FollowSets000.FOLLOW_3); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getIdiomAccess().getIdiomKeyword_1());

            }
            // InternalIdioms.g:1849:3: ( (lv_name_2_0= RULE_ID ) )
            // InternalIdioms.g:1850:4: (lv_name_2_0= RULE_ID )
            {
            // InternalIdioms.g:1850:4: (lv_name_2_0= RULE_ID )
            // InternalIdioms.g:1851:5: lv_name_2_0= RULE_ID
            {
            lv_name_2_0=(Token)match(input,RULE_ID,FollowSets000.FOLLOW_17); if (state.failed) return current;
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
              						"org.eclipse.ocl.examples.xtext.idioms.Idioms.ID");

            }

            }


            }

            // InternalIdioms.g:1867:3: (otherlv_3= 'for' ( ( (otherlv_4= RULE_ID ) ) otherlv_5= '::' )? ( (otherlv_6= RULE_ID ) ) )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==45) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // InternalIdioms.g:1868:4: otherlv_3= 'for' ( ( (otherlv_4= RULE_ID ) ) otherlv_5= '::' )? ( (otherlv_6= RULE_ID ) )
                    {
                    otherlv_3=(Token)match(input,45,FollowSets000.FOLLOW_3); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_3, grammarAccess.getIdiomAccess().getForKeyword_3_0());

                    }
                    // InternalIdioms.g:1872:4: ( ( (otherlv_4= RULE_ID ) ) otherlv_5= '::' )?
                    int alt17=2;
                    int LA17_0 = input.LA(1);

                    if ( (LA17_0==RULE_ID) ) {
                        int LA17_1 = input.LA(2);

                        if ( (LA17_1==21) ) {
                            alt17=1;
                        }
                    }
                    switch (alt17) {
                        case 1 :
                            // InternalIdioms.g:1873:5: ( (otherlv_4= RULE_ID ) ) otherlv_5= '::'
                            {
                            // InternalIdioms.g:1873:5: ( (otherlv_4= RULE_ID ) )
                            // InternalIdioms.g:1874:6: (otherlv_4= RULE_ID )
                            {
                            // InternalIdioms.g:1874:6: (otherlv_4= RULE_ID )
                            // InternalIdioms.g:1875:7: otherlv_4= RULE_ID
                            {
                            if ( state.backtracking==0 ) {

                              							/* */

                            }
                            if ( state.backtracking==0 ) {

                              							if (current==null) {
                              								current = createModelElement(grammarAccess.getIdiomRule());
                              							}

                            }
                            otherlv_4=(Token)match(input,RULE_ID,FollowSets000.FOLLOW_13); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              							newLeafNode(otherlv_4, grammarAccess.getIdiomAccess().getForEPackageEPackageCrossReference_3_1_0_0());

                            }

                            }


                            }

                            otherlv_5=(Token)match(input,21,FollowSets000.FOLLOW_3); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              					newLeafNode(otherlv_5, grammarAccess.getIdiomAccess().getColonColonKeyword_3_1_1());

                            }

                            }
                            break;

                    }

                    // InternalIdioms.g:1894:4: ( (otherlv_6= RULE_ID ) )
                    // InternalIdioms.g:1895:5: (otherlv_6= RULE_ID )
                    {
                    // InternalIdioms.g:1895:5: (otherlv_6= RULE_ID )
                    // InternalIdioms.g:1896:6: otherlv_6= RULE_ID
                    {
                    if ( state.backtracking==0 ) {

                      						/* */

                    }
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElement(grammarAccess.getIdiomRule());
                      						}

                    }
                    otherlv_6=(Token)match(input,RULE_ID,FollowSets000.FOLLOW_18); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						newLeafNode(otherlv_6, grammarAccess.getIdiomAccess().getForEClassEClassCrossReference_3_2_0());

                    }

                    }


                    }


                    }
                    break;

            }

            // InternalIdioms.g:1911:3: (otherlv_7= 'in' ( (lv_inRuleRegex_8_0= RULE_STRING ) ) )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==46) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // InternalIdioms.g:1912:4: otherlv_7= 'in' ( (lv_inRuleRegex_8_0= RULE_STRING ) )
                    {
                    otherlv_7=(Token)match(input,46,FollowSets000.FOLLOW_8); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_7, grammarAccess.getIdiomAccess().getInKeyword_4_0());

                    }
                    // InternalIdioms.g:1916:4: ( (lv_inRuleRegex_8_0= RULE_STRING ) )
                    // InternalIdioms.g:1917:5: (lv_inRuleRegex_8_0= RULE_STRING )
                    {
                    // InternalIdioms.g:1917:5: (lv_inRuleRegex_8_0= RULE_STRING )
                    // InternalIdioms.g:1918:6: lv_inRuleRegex_8_0= RULE_STRING
                    {
                    lv_inRuleRegex_8_0=(Token)match(input,RULE_STRING,FollowSets000.FOLLOW_19); if (state.failed) return current;
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
                      							"org.eclipse.ocl.examples.xtext.idioms.Idioms.STRING");

                    }

                    }


                    }


                    }
                    break;

            }

            // InternalIdioms.g:1935:3: ( ( (lv_ownedSubIdioms_9_0= ruleSubIdiom ) ) | (otherlv_10= '{' ( (lv_ownedSubIdioms_11_0= ruleSubIdiom ) )* otherlv_12= '}' ) )
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==49) ) {
                alt21=1;
            }
            else if ( (LA21_0==47) ) {
                alt21=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 21, 0, input);

                throw nvae;
            }
            switch (alt21) {
                case 1 :
                    // InternalIdioms.g:1936:4: ( (lv_ownedSubIdioms_9_0= ruleSubIdiom ) )
                    {
                    // InternalIdioms.g:1936:4: ( (lv_ownedSubIdioms_9_0= ruleSubIdiom ) )
                    // InternalIdioms.g:1937:5: (lv_ownedSubIdioms_9_0= ruleSubIdiom )
                    {
                    // InternalIdioms.g:1937:5: (lv_ownedSubIdioms_9_0= ruleSubIdiom )
                    // InternalIdioms.g:1938:6: lv_ownedSubIdioms_9_0= ruleSubIdiom
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
                      							"org.eclipse.ocl.examples.xtext.idioms.Idioms.SubIdiom");
                      						afterParserOrEnumRuleCall();

                    }

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalIdioms.g:1956:4: (otherlv_10= '{' ( (lv_ownedSubIdioms_11_0= ruleSubIdiom ) )* otherlv_12= '}' )
                    {
                    // InternalIdioms.g:1956:4: (otherlv_10= '{' ( (lv_ownedSubIdioms_11_0= ruleSubIdiom ) )* otherlv_12= '}' )
                    // InternalIdioms.g:1957:5: otherlv_10= '{' ( (lv_ownedSubIdioms_11_0= ruleSubIdiom ) )* otherlv_12= '}'
                    {
                    otherlv_10=(Token)match(input,47,FollowSets000.FOLLOW_20); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					newLeafNode(otherlv_10, grammarAccess.getIdiomAccess().getLeftCurlyBracketKeyword_5_1_0());

                    }
                    // InternalIdioms.g:1961:5: ( (lv_ownedSubIdioms_11_0= ruleSubIdiom ) )*
                    loop20:
                    do {
                        int alt20=2;
                        int LA20_0 = input.LA(1);

                        if ( (LA20_0==49) ) {
                            alt20=1;
                        }


                        switch (alt20) {
                    	case 1 :
                    	    // InternalIdioms.g:1962:6: (lv_ownedSubIdioms_11_0= ruleSubIdiom )
                    	    {
                    	    // InternalIdioms.g:1962:6: (lv_ownedSubIdioms_11_0= ruleSubIdiom )
                    	    // InternalIdioms.g:1963:7: lv_ownedSubIdioms_11_0= ruleSubIdiom
                    	    {
                    	    if ( state.backtracking==0 ) {

                    	      							newCompositeNode(grammarAccess.getIdiomAccess().getOwnedSubIdiomsSubIdiomParserRuleCall_5_1_1_0());

                    	    }
                    	    pushFollow(FollowSets000.FOLLOW_20);
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
                    	      								"org.eclipse.ocl.examples.xtext.idioms.Idioms.SubIdiom");
                    	      							afterParserOrEnumRuleCall();

                    	    }

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop20;
                        }
                    } while (true);

                    otherlv_12=(Token)match(input,48,FollowSets000.FOLLOW_2); if (state.failed) return current;
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
    // InternalIdioms.g:1990:1: entryRuleSubIdiom returns [EObject current=null] : iv_ruleSubIdiom= ruleSubIdiom EOF ;
    public final EObject entryRuleSubIdiom() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSubIdiom = null;


        try {
            // InternalIdioms.g:1990:49: (iv_ruleSubIdiom= ruleSubIdiom EOF )
            // InternalIdioms.g:1991:2: iv_ruleSubIdiom= ruleSubIdiom EOF
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
    // InternalIdioms.g:1997:1: ruleSubIdiom returns [EObject current=null] : (otherlv_0= 'at' ( ( (lv_all_1_0= 'all' ) ) | otherlv_2= 'each' )? ( (lv_ownedLocator_3_0= ruleLocator ) ) (otherlv_4= 'do' ( ( (lv_ownedSegments_5_1= ruleSegment | lv_ownedSegments_5_2= ruleReferredSegment ) ) )* )? otherlv_6= ';' ) ;
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
            // InternalIdioms.g:2003:2: ( (otherlv_0= 'at' ( ( (lv_all_1_0= 'all' ) ) | otherlv_2= 'each' )? ( (lv_ownedLocator_3_0= ruleLocator ) ) (otherlv_4= 'do' ( ( (lv_ownedSegments_5_1= ruleSegment | lv_ownedSegments_5_2= ruleReferredSegment ) ) )* )? otherlv_6= ';' ) )
            // InternalIdioms.g:2004:2: (otherlv_0= 'at' ( ( (lv_all_1_0= 'all' ) ) | otherlv_2= 'each' )? ( (lv_ownedLocator_3_0= ruleLocator ) ) (otherlv_4= 'do' ( ( (lv_ownedSegments_5_1= ruleSegment | lv_ownedSegments_5_2= ruleReferredSegment ) ) )* )? otherlv_6= ';' )
            {
            // InternalIdioms.g:2004:2: (otherlv_0= 'at' ( ( (lv_all_1_0= 'all' ) ) | otherlv_2= 'each' )? ( (lv_ownedLocator_3_0= ruleLocator ) ) (otherlv_4= 'do' ( ( (lv_ownedSegments_5_1= ruleSegment | lv_ownedSegments_5_2= ruleReferredSegment ) ) )* )? otherlv_6= ';' )
            // InternalIdioms.g:2005:3: otherlv_0= 'at' ( ( (lv_all_1_0= 'all' ) ) | otherlv_2= 'each' )? ( (lv_ownedLocator_3_0= ruleLocator ) ) (otherlv_4= 'do' ( ( (lv_ownedSegments_5_1= ruleSegment | lv_ownedSegments_5_2= ruleReferredSegment ) ) )* )? otherlv_6= ';'
            {
            otherlv_0=(Token)match(input,49,FollowSets000.FOLLOW_21); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getSubIdiomAccess().getAtKeyword_0());

            }
            // InternalIdioms.g:2009:3: ( ( (lv_all_1_0= 'all' ) ) | otherlv_2= 'each' )?
            int alt22=3;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==50) ) {
                alt22=1;
            }
            else if ( (LA22_0==51) ) {
                alt22=2;
            }
            switch (alt22) {
                case 1 :
                    // InternalIdioms.g:2010:4: ( (lv_all_1_0= 'all' ) )
                    {
                    // InternalIdioms.g:2010:4: ( (lv_all_1_0= 'all' ) )
                    // InternalIdioms.g:2011:5: (lv_all_1_0= 'all' )
                    {
                    // InternalIdioms.g:2011:5: (lv_all_1_0= 'all' )
                    // InternalIdioms.g:2012:6: lv_all_1_0= 'all'
                    {
                    lv_all_1_0=(Token)match(input,50,FollowSets000.FOLLOW_11); if (state.failed) return current;
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
                    // InternalIdioms.g:2025:4: otherlv_2= 'each'
                    {
                    otherlv_2=(Token)match(input,51,FollowSets000.FOLLOW_11); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_2, grammarAccess.getSubIdiomAccess().getEachKeyword_1_1());

                    }

                    }
                    break;

            }

            // InternalIdioms.g:2030:3: ( (lv_ownedLocator_3_0= ruleLocator ) )
            // InternalIdioms.g:2031:4: (lv_ownedLocator_3_0= ruleLocator )
            {
            // InternalIdioms.g:2031:4: (lv_ownedLocator_3_0= ruleLocator )
            // InternalIdioms.g:2032:5: lv_ownedLocator_3_0= ruleLocator
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getSubIdiomAccess().getOwnedLocatorLocatorParserRuleCall_2_0());

            }
            pushFollow(FollowSets000.FOLLOW_22);
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
              						"org.eclipse.ocl.examples.xtext.idioms.Idioms.Locator");
              					afterParserOrEnumRuleCall();

            }

            }


            }

            // InternalIdioms.g:2049:3: (otherlv_4= 'do' ( ( (lv_ownedSegments_5_1= ruleSegment | lv_ownedSegments_5_2= ruleReferredSegment ) ) )* )?
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==52) ) {
                alt25=1;
            }
            switch (alt25) {
                case 1 :
                    // InternalIdioms.g:2050:4: otherlv_4= 'do' ( ( (lv_ownedSegments_5_1= ruleSegment | lv_ownedSegments_5_2= ruleReferredSegment ) ) )*
                    {
                    otherlv_4=(Token)match(input,52,FollowSets000.FOLLOW_23); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_4, grammarAccess.getSubIdiomAccess().getDoKeyword_3_0());

                    }
                    // InternalIdioms.g:2054:4: ( ( (lv_ownedSegments_5_1= ruleSegment | lv_ownedSegments_5_2= ruleReferredSegment ) ) )*
                    loop24:
                    do {
                        int alt24=2;
                        int LA24_0 = input.LA(1);

                        if ( (LA24_0==RULE_ID||(LA24_0>=25 && LA24_0<=35)||(LA24_0>=37 && LA24_0<=42)) ) {
                            alt24=1;
                        }


                        switch (alt24) {
                    	case 1 :
                    	    // InternalIdioms.g:2055:5: ( (lv_ownedSegments_5_1= ruleSegment | lv_ownedSegments_5_2= ruleReferredSegment ) )
                    	    {
                    	    // InternalIdioms.g:2055:5: ( (lv_ownedSegments_5_1= ruleSegment | lv_ownedSegments_5_2= ruleReferredSegment ) )
                    	    // InternalIdioms.g:2056:6: (lv_ownedSegments_5_1= ruleSegment | lv_ownedSegments_5_2= ruleReferredSegment )
                    	    {
                    	    // InternalIdioms.g:2056:6: (lv_ownedSegments_5_1= ruleSegment | lv_ownedSegments_5_2= ruleReferredSegment )
                    	    int alt23=2;
                    	    int LA23_0 = input.LA(1);

                    	    if ( ((LA23_0>=25 && LA23_0<=35)||(LA23_0>=37 && LA23_0<=42)) ) {
                    	        alt23=1;
                    	    }
                    	    else if ( (LA23_0==RULE_ID) ) {
                    	        alt23=2;
                    	    }
                    	    else {
                    	        if (state.backtracking>0) {state.failed=true; return current;}
                    	        NoViableAltException nvae =
                    	            new NoViableAltException("", 23, 0, input);

                    	        throw nvae;
                    	    }
                    	    switch (alt23) {
                    	        case 1 :
                    	            // InternalIdioms.g:2057:7: lv_ownedSegments_5_1= ruleSegment
                    	            {
                    	            if ( state.backtracking==0 ) {

                    	              							newCompositeNode(grammarAccess.getSubIdiomAccess().getOwnedSegmentsSegmentParserRuleCall_3_1_0_0());

                    	            }
                    	            pushFollow(FollowSets000.FOLLOW_23);
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
                    	              								"org.eclipse.ocl.examples.xtext.idioms.Idioms.Segment");
                    	              							afterParserOrEnumRuleCall();

                    	            }

                    	            }
                    	            break;
                    	        case 2 :
                    	            // InternalIdioms.g:2073:7: lv_ownedSegments_5_2= ruleReferredSegment
                    	            {
                    	            if ( state.backtracking==0 ) {

                    	              							newCompositeNode(grammarAccess.getSubIdiomAccess().getOwnedSegmentsReferredSegmentParserRuleCall_3_1_0_1());

                    	            }
                    	            pushFollow(FollowSets000.FOLLOW_23);
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
                    	              								"org.eclipse.ocl.examples.xtext.idioms.Idioms.ReferredSegment");
                    	              							afterParserOrEnumRuleCall();

                    	            }

                    	            }
                    	            break;

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop24;
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
        public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000180001033002L});
        public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000180001032002L});
        public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000180001022002L});
        public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000180001020002L});
        public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000000000020L});
        public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x000000000000C002L});
        public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000000008002L});
        public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000000DC0030L});
        public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000000008000L});
        public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000000200000L});
        public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x000007EFFE000000L});
        public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000001000000000L});
        public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x0000100000000000L});
        public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x0002E00000000000L});
        public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x0002C00000000000L});
        public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x0002800000000000L});
        public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x0003000000000000L});
        public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x000C000000DC0030L});
        public static final BitSet FOLLOW_22 = new BitSet(new long[]{0x0010000000008000L});
        public static final BitSet FOLLOW_23 = new BitSet(new long[]{0x000007EFFE008010L});
    }


}
