package org.eclipse.ocl.xtext.completeocl.parser.antlr.internal;

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import org.eclipse.ocl.xtext.completeocl.services.CompleteOCLGrammarAccess;



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
public class InternalCompleteOCLParser extends org.eclipse.ocl.xtext.base.utilities.CompatibilityAbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_UNQUOTED_STRING", "RULE_ML_DOCUMENTATION", "RULE_SIMPLE_ID", "RULE_ESCAPED_ID", "RULE_INT", "RULE_SINGLE_QUOTED_STRING", "RULE_ESCAPED_CHARACTER", "RULE_LETTER_CHARACTER", "RULE_DOUBLE_QUOTED_STRING", "RULE_ML_SINGLE_QUOTED_STRING", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'^'", "'^^'", "'context'", "'inv'", "'('", "')'", "':'", "'post'", "'pre'", "'static'", "'def'", "','", "'='", "'import'", "'include'", "'library'", "'::*'", "'package'", "'endpackage'", "'body'", "'derive'", "'init'", "'<'", "'>'", "'?'", "'Boolean'", "'Integer'", "'Real'", "'String'", "'UnlimitedNatural'", "'OclAny'", "'OclInvalid'", "'OclMessage'", "'OclState'", "'OclVoid'", "'-'", "'not'", "'not2'", "'*'", "'/'", "'+'", "'>='", "'<='", "'<>'", "'and'", "'and2'", "'implies'", "'implies2'", "'or'", "'or2'", "'xor'", "'xor2'", "'.'", "'->'", "'?.'", "'?->'", "'Map'", "'Tuple'", "'::'", "'Set'", "'Bag'", "'Sequence'", "'Collection'", "'OrderedSet'", "'{'", "'}'", "'..'", "'++'", "'Lambda'", "'<-'", "'true'", "'false'", "'invalid'", "'null'", "'@'", "'['", "']'", "'in'", "'|'", "';'", "'if'", "'then'", "'else'", "'endif'", "'elseif'", "'let'", "'self'", "'|?'", "'|1'", "'extends'", "'&&'"
    };
    public static final int T__50=50;
    public static final int T__59=59;
    public static final int T__55=55;
    public static final int T__56=56;
    public static final int T__57=57;
    public static final int T__58=58;
    public static final int T__51=51;
    public static final int T__52=52;
    public static final int T__53=53;
    public static final int T__54=54;
    public static final int T__60=60;
    public static final int T__61=61;
    public static final int RULE_INT=8;
    public static final int T__66=66;
    public static final int RULE_ML_COMMENT=14;
    public static final int T__67=67;
    public static final int T__68=68;
    public static final int T__69=69;
    public static final int T__62=62;
    public static final int T__63=63;
    public static final int T__64=64;
    public static final int T__65=65;
    public static final int T__37=37;
    public static final int T__38=38;
    public static final int T__39=39;
    public static final int T__33=33;
    public static final int T__34=34;
    public static final int T__35=35;
    public static final int T__36=36;
    public static final int T__30=30;
    public static final int T__31=31;
    public static final int T__32=32;
    public static final int RULE_SINGLE_QUOTED_STRING=9;
    public static final int T__48=48;
    public static final int T__49=49;
    public static final int RULE_DOUBLE_QUOTED_STRING=12;
    public static final int T__44=44;
    public static final int T__45=45;
    public static final int T__46=46;
    public static final int T__47=47;
    public static final int RULE_ESCAPED_ID=7;
    public static final int T__40=40;
    public static final int T__41=41;
    public static final int T__42=42;
    public static final int T__43=43;
    public static final int T__91=91;
    public static final int T__100=100;
    public static final int T__92=92;
    public static final int T__93=93;
    public static final int T__102=102;
    public static final int T__94=94;
    public static final int T__101=101;
    public static final int RULE_ML_DOCUMENTATION=5;
    public static final int T__90=90;
    public static final int RULE_LETTER_CHARACTER=11;
    public static final int T__19=19;
    public static final int T__18=18;
    public static final int T__99=99;
    public static final int RULE_ESCAPED_CHARACTER=10;
    public static final int T__95=95;
    public static final int RULE_ML_SINGLE_QUOTED_STRING=13;
    public static final int T__96=96;
    public static final int T__97=97;
    public static final int T__98=98;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int T__29=29;
    public static final int T__22=22;
    public static final int T__23=23;
    public static final int T__24=24;
    public static final int T__25=25;
    public static final int T__20=20;
    public static final int T__21=21;
    public static final int T__70=70;
    public static final int T__71=71;
    public static final int T__72=72;
    public static final int RULE_UNQUOTED_STRING=4;
    public static final int RULE_SL_COMMENT=15;
    public static final int T__77=77;
    public static final int T__78=78;
    public static final int T__79=79;
    public static final int T__73=73;
    public static final int EOF=-1;
    public static final int T__74=74;
    public static final int T__75=75;
    public static final int T__76=76;
    public static final int T__80=80;
    public static final int T__81=81;
    public static final int T__82=82;
    public static final int RULE_SIMPLE_ID=6;
    public static final int T__83=83;
    public static final int RULE_WS=16;
    public static final int RULE_ANY_OTHER=17;
    public static final int T__88=88;
    public static final int T__108=108;
    public static final int T__89=89;
    public static final int T__107=107;
    public static final int T__84=84;
    public static final int T__104=104;
    public static final int T__85=85;
    public static final int T__103=103;
    public static final int T__86=86;
    public static final int T__106=106;
    public static final int T__87=87;
    public static final int T__105=105;

    // delegates
    // delegators


        public InternalCompleteOCLParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalCompleteOCLParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);

        }


    public String[] getTokenNames() { return InternalCompleteOCLParser.tokenNames; }
    public String getGrammarFileName() { return "InternalCompleteOCL.g"; }



    /*
      This grammar contains a lot of empty actions to work around a bug in ANTLR.
      Otherwise the ANTLR tool will create synpreds that cannot be compiled in some rare cases.
    */

     	private CompleteOCLGrammarAccess grammarAccess;

        public InternalCompleteOCLParser(TokenStream input, CompleteOCLGrammarAccess grammarAccess) {
            this(input);
            this.grammarAccess = grammarAccess;
            registerRules(grammarAccess.getGrammar());
        }

        @Override
        protected String getFirstRuleName() {
        	return "CompleteOCLDocumentCS";
       	}

       	@Override
       	protected CompleteOCLGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}



    // $ANTLR start "entryRuleCompleteOCLDocumentCS"
    // InternalCompleteOCL.g:80:1: entryRuleCompleteOCLDocumentCS returns [EObject current=null] : iv_ruleCompleteOCLDocumentCS= ruleCompleteOCLDocumentCS EOF ;
    public final EObject entryRuleCompleteOCLDocumentCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCompleteOCLDocumentCS = null;


        try {
            // InternalCompleteOCL.g:81:2: (iv_ruleCompleteOCLDocumentCS= ruleCompleteOCLDocumentCS EOF )
            // InternalCompleteOCL.g:82:2: iv_ruleCompleteOCLDocumentCS= ruleCompleteOCLDocumentCS EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getCompleteOCLDocumentCSRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleCompleteOCLDocumentCS=ruleCompleteOCLDocumentCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleCompleteOCLDocumentCS;
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
    // $ANTLR end "entryRuleCompleteOCLDocumentCS"


    // $ANTLR start "ruleCompleteOCLDocumentCS"
    // InternalCompleteOCL.g:89:1: ruleCompleteOCLDocumentCS returns [EObject current=null] : ( ( (lv_ownedAnnotations_0_0= ruleCommentCS ) )? ( (lv_ownedImports_1_0= ruleImportCS ) )* ( ( (lv_ownedPackages_2_0= rulePackageDeclarationCS ) ) | ( (lv_ownedContexts_3_0= ruleContextDeclCS ) ) )* ) ;
    public final EObject ruleCompleteOCLDocumentCS() throws RecognitionException {
        EObject current = null;

        EObject lv_ownedAnnotations_0_0 = null;

        EObject lv_ownedImports_1_0 = null;

        EObject lv_ownedPackages_2_0 = null;

        EObject lv_ownedContexts_3_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:92:28: ( ( ( (lv_ownedAnnotations_0_0= ruleCommentCS ) )? ( (lv_ownedImports_1_0= ruleImportCS ) )* ( ( (lv_ownedPackages_2_0= rulePackageDeclarationCS ) ) | ( (lv_ownedContexts_3_0= ruleContextDeclCS ) ) )* ) )
            // InternalCompleteOCL.g:93:1: ( ( (lv_ownedAnnotations_0_0= ruleCommentCS ) )? ( (lv_ownedImports_1_0= ruleImportCS ) )* ( ( (lv_ownedPackages_2_0= rulePackageDeclarationCS ) ) | ( (lv_ownedContexts_3_0= ruleContextDeclCS ) ) )* )
            {
            // InternalCompleteOCL.g:93:1: ( ( (lv_ownedAnnotations_0_0= ruleCommentCS ) )? ( (lv_ownedImports_1_0= ruleImportCS ) )* ( ( (lv_ownedPackages_2_0= rulePackageDeclarationCS ) ) | ( (lv_ownedContexts_3_0= ruleContextDeclCS ) ) )* )
            // InternalCompleteOCL.g:93:2: ( (lv_ownedAnnotations_0_0= ruleCommentCS ) )? ( (lv_ownedImports_1_0= ruleImportCS ) )* ( ( (lv_ownedPackages_2_0= rulePackageDeclarationCS ) ) | ( (lv_ownedContexts_3_0= ruleContextDeclCS ) ) )*
            {
            // InternalCompleteOCL.g:93:2: ( (lv_ownedAnnotations_0_0= ruleCommentCS ) )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==RULE_ML_DOCUMENTATION) ) {
                int LA1_1 = input.LA(2);

                if ( (synpred1_InternalCompleteOCL()) ) {
                    alt1=1;
                }
            }
            switch (alt1) {
                case 1 :
                    // InternalCompleteOCL.g:94:1: (lv_ownedAnnotations_0_0= ruleCommentCS )
                    {
                    // InternalCompleteOCL.g:94:1: (lv_ownedAnnotations_0_0= ruleCommentCS )
                    // InternalCompleteOCL.g:95:3: lv_ownedAnnotations_0_0= ruleCommentCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getCompleteOCLDocumentCSAccess().getOwnedAnnotationsCommentCSParserRuleCall_0_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_3);
                    lv_ownedAnnotations_0_0=ruleCommentCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getCompleteOCLDocumentCSRule());
                      	        }
                             		add(
                             			current,
                             			"ownedAnnotations",
                              		lv_ownedAnnotations_0_0,
                              		"org.eclipse.ocl.xtext.base.Base.CommentCS");
                      	        afterParserOrEnumRuleCall();

                    }

                    }


                    }
                    break;

            }

            // InternalCompleteOCL.g:111:3: ( (lv_ownedImports_1_0= ruleImportCS ) )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==RULE_ML_DOCUMENTATION) ) {
                    int LA2_1 = input.LA(2);

                    if ( ((LA2_1>=31 && LA2_1<=33)) ) {
                        alt2=1;
                    }


                }
                else if ( ((LA2_0>=31 && LA2_0<=33)) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // InternalCompleteOCL.g:112:1: (lv_ownedImports_1_0= ruleImportCS )
            	    {
            	    // InternalCompleteOCL.g:112:1: (lv_ownedImports_1_0= ruleImportCS )
            	    // InternalCompleteOCL.g:113:3: lv_ownedImports_1_0= ruleImportCS
            	    {
            	    if ( state.backtracking==0 ) {

            	      	        newCompositeNode(grammarAccess.getCompleteOCLDocumentCSAccess().getOwnedImportsImportCSParserRuleCall_1_0());

            	    }
            	    pushFollow(FollowSets000.FOLLOW_3);
            	    lv_ownedImports_1_0=ruleImportCS();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getCompleteOCLDocumentCSRule());
            	      	        }
            	             		add(
            	             			current,
            	             			"ownedImports",
            	              		lv_ownedImports_1_0,
            	              		"org.eclipse.ocl.xtext.completeocl.CompleteOCL.ImportCS");
            	      	        afterParserOrEnumRuleCall();

            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);

            // InternalCompleteOCL.g:129:3: ( ( (lv_ownedPackages_2_0= rulePackageDeclarationCS ) ) | ( (lv_ownedContexts_3_0= ruleContextDeclCS ) ) )*
            loop3:
            do {
                int alt3=3;
                switch ( input.LA(1) ) {
                case RULE_ML_DOCUMENTATION:
                    {
                    int LA3_2 = input.LA(2);

                    if ( (LA3_2==20) ) {
                        alt3=2;
                    }
                    else if ( (LA3_2==35) ) {
                        alt3=1;
                    }


                    }
                    break;
                case 35:
                    {
                    alt3=1;
                    }
                    break;
                case 20:
                    {
                    alt3=2;
                    }
                    break;

                }

                switch (alt3) {
            	case 1 :
            	    // InternalCompleteOCL.g:129:4: ( (lv_ownedPackages_2_0= rulePackageDeclarationCS ) )
            	    {
            	    // InternalCompleteOCL.g:129:4: ( (lv_ownedPackages_2_0= rulePackageDeclarationCS ) )
            	    // InternalCompleteOCL.g:130:1: (lv_ownedPackages_2_0= rulePackageDeclarationCS )
            	    {
            	    // InternalCompleteOCL.g:130:1: (lv_ownedPackages_2_0= rulePackageDeclarationCS )
            	    // InternalCompleteOCL.g:131:3: lv_ownedPackages_2_0= rulePackageDeclarationCS
            	    {
            	    if ( state.backtracking==0 ) {

            	      	        newCompositeNode(grammarAccess.getCompleteOCLDocumentCSAccess().getOwnedPackagesPackageDeclarationCSParserRuleCall_2_0_0());

            	    }
            	    pushFollow(FollowSets000.FOLLOW_4);
            	    lv_ownedPackages_2_0=rulePackageDeclarationCS();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getCompleteOCLDocumentCSRule());
            	      	        }
            	             		add(
            	             			current,
            	             			"ownedPackages",
            	              		lv_ownedPackages_2_0,
            	              		"org.eclipse.ocl.xtext.completeocl.CompleteOCL.PackageDeclarationCS");
            	      	        afterParserOrEnumRuleCall();

            	    }

            	    }


            	    }


            	    }
            	    break;
            	case 2 :
            	    // InternalCompleteOCL.g:148:6: ( (lv_ownedContexts_3_0= ruleContextDeclCS ) )
            	    {
            	    // InternalCompleteOCL.g:148:6: ( (lv_ownedContexts_3_0= ruleContextDeclCS ) )
            	    // InternalCompleteOCL.g:149:1: (lv_ownedContexts_3_0= ruleContextDeclCS )
            	    {
            	    // InternalCompleteOCL.g:149:1: (lv_ownedContexts_3_0= ruleContextDeclCS )
            	    // InternalCompleteOCL.g:150:3: lv_ownedContexts_3_0= ruleContextDeclCS
            	    {
            	    if ( state.backtracking==0 ) {

            	      	        newCompositeNode(grammarAccess.getCompleteOCLDocumentCSAccess().getOwnedContextsContextDeclCSParserRuleCall_2_1_0());

            	    }
            	    pushFollow(FollowSets000.FOLLOW_4);
            	    lv_ownedContexts_3_0=ruleContextDeclCS();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getCompleteOCLDocumentCSRule());
            	      	        }
            	             		add(
            	             			current,
            	             			"ownedContexts",
            	              		lv_ownedContexts_3_0,
            	              		"org.eclipse.ocl.xtext.completeocl.CompleteOCL.ContextDeclCS");
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
    // $ANTLR end "ruleCompleteOCLDocumentCS"


    // $ANTLR start "entryRuleCompleteOCLNavigationOperatorName"
    // InternalCompleteOCL.g:174:1: entryRuleCompleteOCLNavigationOperatorName returns [String current=null] : iv_ruleCompleteOCLNavigationOperatorName= ruleCompleteOCLNavigationOperatorName EOF ;
    public final String entryRuleCompleteOCLNavigationOperatorName() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleCompleteOCLNavigationOperatorName = null;


        try {
            // InternalCompleteOCL.g:175:2: (iv_ruleCompleteOCLNavigationOperatorName= ruleCompleteOCLNavigationOperatorName EOF )
            // InternalCompleteOCL.g:176:2: iv_ruleCompleteOCLNavigationOperatorName= ruleCompleteOCLNavigationOperatorName EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getCompleteOCLNavigationOperatorNameRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleCompleteOCLNavigationOperatorName=ruleCompleteOCLNavigationOperatorName();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleCompleteOCLNavigationOperatorName.getText();
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
    // $ANTLR end "entryRuleCompleteOCLNavigationOperatorName"


    // $ANTLR start "ruleCompleteOCLNavigationOperatorName"
    // InternalCompleteOCL.g:183:1: ruleCompleteOCLNavigationOperatorName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= '^' | kw= '^^' ) ;
    public final AntlrDatatypeRuleToken ruleCompleteOCLNavigationOperatorName() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule();

        try {
            // InternalCompleteOCL.g:186:28: ( (kw= '^' | kw= '^^' ) )
            // InternalCompleteOCL.g:187:1: (kw= '^' | kw= '^^' )
            {
            // InternalCompleteOCL.g:187:1: (kw= '^' | kw= '^^' )
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==18) ) {
                alt4=1;
            }
            else if ( (LA4_0==19) ) {
                alt4=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }
            switch (alt4) {
                case 1 :
                    // InternalCompleteOCL.g:188:2: kw= '^'
                    {
                    kw=(Token)match(input,18,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getCompleteOCLNavigationOperatorNameAccess().getCircumflexAccentKeyword_0());

                    }

                    }
                    break;
                case 2 :
                    // InternalCompleteOCL.g:195:2: kw= '^^'
                    {
                    kw=(Token)match(input,19,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getCompleteOCLNavigationOperatorNameAccess().getCircumflexAccentCircumflexAccentKeyword_1());

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
    // $ANTLR end "ruleCompleteOCLNavigationOperatorName"


    // $ANTLR start "entryRuleClassifierContextDeclCS"
    // InternalCompleteOCL.g:208:1: entryRuleClassifierContextDeclCS returns [EObject current=null] : iv_ruleClassifierContextDeclCS= ruleClassifierContextDeclCS EOF ;
    public final EObject entryRuleClassifierContextDeclCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleClassifierContextDeclCS = null;


        try {
            // InternalCompleteOCL.g:209:2: (iv_ruleClassifierContextDeclCS= ruleClassifierContextDeclCS EOF )
            // InternalCompleteOCL.g:210:2: iv_ruleClassifierContextDeclCS= ruleClassifierContextDeclCS EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getClassifierContextDeclCSRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleClassifierContextDeclCS=ruleClassifierContextDeclCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleClassifierContextDeclCS;
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
    // $ANTLR end "entryRuleClassifierContextDeclCS"


    // $ANTLR start "ruleClassifierContextDeclCS"
    // InternalCompleteOCL.g:217:1: ruleClassifierContextDeclCS returns [EObject current=null] : ( ( (lv_ownedAnnotations_0_0= ruleCommentCS ) )? otherlv_1= 'context' ( (lv_ownedSignature_2_0= ruleTemplateSignatureCS ) )? ( (lv_selfName_3_0= ruleUnrestrictedName ) )? ( (lv_ownedPathName_4_0= rulePathNameCS ) ) ( ( (lv_ownedInvariants_5_0= ruleInvConstraintCS ) ) | ( (lv_ownedDefinitions_6_0= ruleDefCS ) ) )+ ) ;
    public final EObject ruleClassifierContextDeclCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject lv_ownedAnnotations_0_0 = null;

        EObject lv_ownedSignature_2_0 = null;

        AntlrDatatypeRuleToken lv_selfName_3_0 = null;

        EObject lv_ownedPathName_4_0 = null;

        EObject lv_ownedInvariants_5_0 = null;

        EObject lv_ownedDefinitions_6_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:220:28: ( ( ( (lv_ownedAnnotations_0_0= ruleCommentCS ) )? otherlv_1= 'context' ( (lv_ownedSignature_2_0= ruleTemplateSignatureCS ) )? ( (lv_selfName_3_0= ruleUnrestrictedName ) )? ( (lv_ownedPathName_4_0= rulePathNameCS ) ) ( ( (lv_ownedInvariants_5_0= ruleInvConstraintCS ) ) | ( (lv_ownedDefinitions_6_0= ruleDefCS ) ) )+ ) )
            // InternalCompleteOCL.g:221:1: ( ( (lv_ownedAnnotations_0_0= ruleCommentCS ) )? otherlv_1= 'context' ( (lv_ownedSignature_2_0= ruleTemplateSignatureCS ) )? ( (lv_selfName_3_0= ruleUnrestrictedName ) )? ( (lv_ownedPathName_4_0= rulePathNameCS ) ) ( ( (lv_ownedInvariants_5_0= ruleInvConstraintCS ) ) | ( (lv_ownedDefinitions_6_0= ruleDefCS ) ) )+ )
            {
            // InternalCompleteOCL.g:221:1: ( ( (lv_ownedAnnotations_0_0= ruleCommentCS ) )? otherlv_1= 'context' ( (lv_ownedSignature_2_0= ruleTemplateSignatureCS ) )? ( (lv_selfName_3_0= ruleUnrestrictedName ) )? ( (lv_ownedPathName_4_0= rulePathNameCS ) ) ( ( (lv_ownedInvariants_5_0= ruleInvConstraintCS ) ) | ( (lv_ownedDefinitions_6_0= ruleDefCS ) ) )+ )
            // InternalCompleteOCL.g:221:2: ( (lv_ownedAnnotations_0_0= ruleCommentCS ) )? otherlv_1= 'context' ( (lv_ownedSignature_2_0= ruleTemplateSignatureCS ) )? ( (lv_selfName_3_0= ruleUnrestrictedName ) )? ( (lv_ownedPathName_4_0= rulePathNameCS ) ) ( ( (lv_ownedInvariants_5_0= ruleInvConstraintCS ) ) | ( (lv_ownedDefinitions_6_0= ruleDefCS ) ) )+
            {
            // InternalCompleteOCL.g:221:2: ( (lv_ownedAnnotations_0_0= ruleCommentCS ) )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==RULE_ML_DOCUMENTATION) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // InternalCompleteOCL.g:222:1: (lv_ownedAnnotations_0_0= ruleCommentCS )
                    {
                    // InternalCompleteOCL.g:222:1: (lv_ownedAnnotations_0_0= ruleCommentCS )
                    // InternalCompleteOCL.g:223:3: lv_ownedAnnotations_0_0= ruleCommentCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getClassifierContextDeclCSAccess().getOwnedAnnotationsCommentCSParserRuleCall_0_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_5);
                    lv_ownedAnnotations_0_0=ruleCommentCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getClassifierContextDeclCSRule());
                      	        }
                             		add(
                             			current,
                             			"ownedAnnotations",
                              		lv_ownedAnnotations_0_0,
                              		"org.eclipse.ocl.xtext.base.Base.CommentCS");
                      	        afterParserOrEnumRuleCall();

                    }

                    }


                    }
                    break;

            }

            otherlv_1=(Token)match(input,20,FollowSets000.FOLLOW_6); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getClassifierContextDeclCSAccess().getContextKeyword_1());

            }
            // InternalCompleteOCL.g:243:1: ( (lv_ownedSignature_2_0= ruleTemplateSignatureCS ) )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==22||LA6_0==40) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // InternalCompleteOCL.g:244:1: (lv_ownedSignature_2_0= ruleTemplateSignatureCS )
                    {
                    // InternalCompleteOCL.g:244:1: (lv_ownedSignature_2_0= ruleTemplateSignatureCS )
                    // InternalCompleteOCL.g:245:3: lv_ownedSignature_2_0= ruleTemplateSignatureCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getClassifierContextDeclCSAccess().getOwnedSignatureTemplateSignatureCSParserRuleCall_2_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_6);
                    lv_ownedSignature_2_0=ruleTemplateSignatureCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getClassifierContextDeclCSRule());
                      	        }
                             		set(
                             			current,
                             			"ownedSignature",
                              		lv_ownedSignature_2_0,
                              		"org.eclipse.ocl.xtext.completeocl.CompleteOCL.TemplateSignatureCS");
                      	        afterParserOrEnumRuleCall();

                    }

                    }


                    }
                    break;

            }

            // InternalCompleteOCL.g:261:3: ( (lv_selfName_3_0= ruleUnrestrictedName ) )?
            int alt7=2;
            switch ( input.LA(1) ) {
                case RULE_SIMPLE_ID:
                    {
                    int LA7_1 = input.LA(2);

                    if ( ((LA7_1>=RULE_SIMPLE_ID && LA7_1<=RULE_ESCAPED_ID)||(LA7_1>=31 && LA7_1<=33)) ) {
                        alt7=1;
                    }
                    }
                    break;
                case RULE_ESCAPED_ID:
                    {
                    int LA7_2 = input.LA(2);

                    if ( ((LA7_2>=RULE_SIMPLE_ID && LA7_2<=RULE_ESCAPED_ID)||(LA7_2>=31 && LA7_2<=33)) ) {
                        alt7=1;
                    }
                    }
                    break;
                case 31:
                    {
                    int LA7_3 = input.LA(2);

                    if ( ((LA7_3>=RULE_SIMPLE_ID && LA7_3<=RULE_ESCAPED_ID)||(LA7_3>=31 && LA7_3<=33)) ) {
                        alt7=1;
                    }
                    }
                    break;
                case 32:
                    {
                    int LA7_4 = input.LA(2);

                    if ( ((LA7_4>=RULE_SIMPLE_ID && LA7_4<=RULE_ESCAPED_ID)||(LA7_4>=31 && LA7_4<=33)) ) {
                        alt7=1;
                    }
                    }
                    break;
                case 33:
                    {
                    int LA7_5 = input.LA(2);

                    if ( ((LA7_5>=RULE_SIMPLE_ID && LA7_5<=RULE_ESCAPED_ID)||(LA7_5>=31 && LA7_5<=33)) ) {
                        alt7=1;
                    }
                    }
                    break;
            }

            switch (alt7) {
                case 1 :
                    // InternalCompleteOCL.g:262:1: (lv_selfName_3_0= ruleUnrestrictedName )
                    {
                    // InternalCompleteOCL.g:262:1: (lv_selfName_3_0= ruleUnrestrictedName )
                    // InternalCompleteOCL.g:263:3: lv_selfName_3_0= ruleUnrestrictedName
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getClassifierContextDeclCSAccess().getSelfNameUnrestrictedNameParserRuleCall_3_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_6);
                    lv_selfName_3_0=ruleUnrestrictedName();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getClassifierContextDeclCSRule());
                      	        }
                             		set(
                             			current,
                             			"selfName",
                              		lv_selfName_3_0,
                              		"org.eclipse.ocl.xtext.completeocl.CompleteOCL.UnrestrictedName");
                      	        afterParserOrEnumRuleCall();

                    }

                    }


                    }
                    break;

            }

            // InternalCompleteOCL.g:279:3: ( (lv_ownedPathName_4_0= rulePathNameCS ) )
            // InternalCompleteOCL.g:280:1: (lv_ownedPathName_4_0= rulePathNameCS )
            {
            // InternalCompleteOCL.g:280:1: (lv_ownedPathName_4_0= rulePathNameCS )
            // InternalCompleteOCL.g:281:3: lv_ownedPathName_4_0= rulePathNameCS
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getClassifierContextDeclCSAccess().getOwnedPathNamePathNameCSParserRuleCall_4_0());

            }
            pushFollow(FollowSets000.FOLLOW_7);
            lv_ownedPathName_4_0=rulePathNameCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getClassifierContextDeclCSRule());
              	        }
                     		set(
                     			current,
                     			"ownedPathName",
                      		lv_ownedPathName_4_0,
                      		"org.eclipse.ocl.xtext.base.Base.PathNameCS");
              	        afterParserOrEnumRuleCall();

            }

            }


            }

            // InternalCompleteOCL.g:297:2: ( ( (lv_ownedInvariants_5_0= ruleInvConstraintCS ) ) | ( (lv_ownedDefinitions_6_0= ruleDefCS ) ) )+
            int cnt8=0;
            loop8:
            do {
                int alt8=3;
                switch ( input.LA(1) ) {
                case RULE_ML_DOCUMENTATION:
                    {
                    int LA8_2 = input.LA(2);

                    if ( ((LA8_2>=27 && LA8_2<=28)) ) {
                        alt8=2;
                    }
                    else if ( (LA8_2==21) ) {
                        alt8=1;
                    }


                    }
                    break;
                case 21:
                    {
                    alt8=1;
                    }
                    break;
                case 27:
                case 28:
                    {
                    alt8=2;
                    }
                    break;

                }

                switch (alt8) {
            	case 1 :
            	    // InternalCompleteOCL.g:297:3: ( (lv_ownedInvariants_5_0= ruleInvConstraintCS ) )
            	    {
            	    // InternalCompleteOCL.g:297:3: ( (lv_ownedInvariants_5_0= ruleInvConstraintCS ) )
            	    // InternalCompleteOCL.g:298:1: (lv_ownedInvariants_5_0= ruleInvConstraintCS )
            	    {
            	    // InternalCompleteOCL.g:298:1: (lv_ownedInvariants_5_0= ruleInvConstraintCS )
            	    // InternalCompleteOCL.g:299:3: lv_ownedInvariants_5_0= ruleInvConstraintCS
            	    {
            	    if ( state.backtracking==0 ) {

            	      	        newCompositeNode(grammarAccess.getClassifierContextDeclCSAccess().getOwnedInvariantsInvConstraintCSParserRuleCall_5_0_0());

            	    }
            	    pushFollow(FollowSets000.FOLLOW_8);
            	    lv_ownedInvariants_5_0=ruleInvConstraintCS();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getClassifierContextDeclCSRule());
            	      	        }
            	             		add(
            	             			current,
            	             			"ownedInvariants",
            	              		lv_ownedInvariants_5_0,
            	              		"org.eclipse.ocl.xtext.completeocl.CompleteOCL.InvConstraintCS");
            	      	        afterParserOrEnumRuleCall();

            	    }

            	    }


            	    }


            	    }
            	    break;
            	case 2 :
            	    // InternalCompleteOCL.g:316:6: ( (lv_ownedDefinitions_6_0= ruleDefCS ) )
            	    {
            	    // InternalCompleteOCL.g:316:6: ( (lv_ownedDefinitions_6_0= ruleDefCS ) )
            	    // InternalCompleteOCL.g:317:1: (lv_ownedDefinitions_6_0= ruleDefCS )
            	    {
            	    // InternalCompleteOCL.g:317:1: (lv_ownedDefinitions_6_0= ruleDefCS )
            	    // InternalCompleteOCL.g:318:3: lv_ownedDefinitions_6_0= ruleDefCS
            	    {
            	    if ( state.backtracking==0 ) {

            	      	        newCompositeNode(grammarAccess.getClassifierContextDeclCSAccess().getOwnedDefinitionsDefCSParserRuleCall_5_1_0());

            	    }
            	    pushFollow(FollowSets000.FOLLOW_8);
            	    lv_ownedDefinitions_6_0=ruleDefCS();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getClassifierContextDeclCSRule());
            	      	        }
            	             		add(
            	             			current,
            	             			"ownedDefinitions",
            	              		lv_ownedDefinitions_6_0,
            	              		"org.eclipse.ocl.xtext.completeocl.CompleteOCL.DefCS");
            	      	        afterParserOrEnumRuleCall();

            	    }

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt8 >= 1 ) break loop8;
            	    if (state.backtracking>0) {state.failed=true; return current;}
                        EarlyExitException eee =
                            new EarlyExitException(8, input);
                        throw eee;
                }
                cnt8++;
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
    // $ANTLR end "ruleClassifierContextDeclCS"


    // $ANTLR start "entryRuleInvConstraintCS"
    // InternalCompleteOCL.g:342:1: entryRuleInvConstraintCS returns [EObject current=null] : iv_ruleInvConstraintCS= ruleInvConstraintCS EOF ;
    public final EObject entryRuleInvConstraintCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleInvConstraintCS = null;


        try {
            // InternalCompleteOCL.g:343:2: (iv_ruleInvConstraintCS= ruleInvConstraintCS EOF )
            // InternalCompleteOCL.g:344:2: iv_ruleInvConstraintCS= ruleInvConstraintCS EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getInvConstraintCSRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleInvConstraintCS=ruleInvConstraintCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleInvConstraintCS;
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
    // $ANTLR end "entryRuleInvConstraintCS"


    // $ANTLR start "ruleInvConstraintCS"
    // InternalCompleteOCL.g:351:1: ruleInvConstraintCS returns [EObject current=null] : ( ( (lv_ownedAnnotations_0_0= ruleCommentCS ) )? otherlv_1= 'inv' ( ( (lv_name_2_0= ruleUnrestrictedName ) ) (otherlv_3= '(' ( (lv_ownedMessageSpecification_4_0= ruleSpecificationCS ) ) otherlv_5= ')' )? )? otherlv_6= ':' ( (lv_ownedSpecification_7_0= ruleSpecificationCS ) ) ) ;
    public final EObject ruleInvConstraintCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_6=null;
        EObject lv_ownedAnnotations_0_0 = null;

        AntlrDatatypeRuleToken lv_name_2_0 = null;

        EObject lv_ownedMessageSpecification_4_0 = null;

        EObject lv_ownedSpecification_7_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:354:28: ( ( ( (lv_ownedAnnotations_0_0= ruleCommentCS ) )? otherlv_1= 'inv' ( ( (lv_name_2_0= ruleUnrestrictedName ) ) (otherlv_3= '(' ( (lv_ownedMessageSpecification_4_0= ruleSpecificationCS ) ) otherlv_5= ')' )? )? otherlv_6= ':' ( (lv_ownedSpecification_7_0= ruleSpecificationCS ) ) ) )
            // InternalCompleteOCL.g:355:1: ( ( (lv_ownedAnnotations_0_0= ruleCommentCS ) )? otherlv_1= 'inv' ( ( (lv_name_2_0= ruleUnrestrictedName ) ) (otherlv_3= '(' ( (lv_ownedMessageSpecification_4_0= ruleSpecificationCS ) ) otherlv_5= ')' )? )? otherlv_6= ':' ( (lv_ownedSpecification_7_0= ruleSpecificationCS ) ) )
            {
            // InternalCompleteOCL.g:355:1: ( ( (lv_ownedAnnotations_0_0= ruleCommentCS ) )? otherlv_1= 'inv' ( ( (lv_name_2_0= ruleUnrestrictedName ) ) (otherlv_3= '(' ( (lv_ownedMessageSpecification_4_0= ruleSpecificationCS ) ) otherlv_5= ')' )? )? otherlv_6= ':' ( (lv_ownedSpecification_7_0= ruleSpecificationCS ) ) )
            // InternalCompleteOCL.g:355:2: ( (lv_ownedAnnotations_0_0= ruleCommentCS ) )? otherlv_1= 'inv' ( ( (lv_name_2_0= ruleUnrestrictedName ) ) (otherlv_3= '(' ( (lv_ownedMessageSpecification_4_0= ruleSpecificationCS ) ) otherlv_5= ')' )? )? otherlv_6= ':' ( (lv_ownedSpecification_7_0= ruleSpecificationCS ) )
            {
            // InternalCompleteOCL.g:355:2: ( (lv_ownedAnnotations_0_0= ruleCommentCS ) )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==RULE_ML_DOCUMENTATION) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // InternalCompleteOCL.g:356:1: (lv_ownedAnnotations_0_0= ruleCommentCS )
                    {
                    // InternalCompleteOCL.g:356:1: (lv_ownedAnnotations_0_0= ruleCommentCS )
                    // InternalCompleteOCL.g:357:3: lv_ownedAnnotations_0_0= ruleCommentCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getInvConstraintCSAccess().getOwnedAnnotationsCommentCSParserRuleCall_0_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_9);
                    lv_ownedAnnotations_0_0=ruleCommentCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getInvConstraintCSRule());
                      	        }
                             		add(
                             			current,
                             			"ownedAnnotations",
                              		lv_ownedAnnotations_0_0,
                              		"org.eclipse.ocl.xtext.base.Base.CommentCS");
                      	        afterParserOrEnumRuleCall();

                    }

                    }


                    }
                    break;

            }

            otherlv_1=(Token)match(input,21,FollowSets000.FOLLOW_10); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getInvConstraintCSAccess().getInvKeyword_1());

            }
            // InternalCompleteOCL.g:377:1: ( ( (lv_name_2_0= ruleUnrestrictedName ) ) (otherlv_3= '(' ( (lv_ownedMessageSpecification_4_0= ruleSpecificationCS ) ) otherlv_5= ')' )? )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( ((LA11_0>=RULE_SIMPLE_ID && LA11_0<=RULE_ESCAPED_ID)||(LA11_0>=31 && LA11_0<=33)) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // InternalCompleteOCL.g:377:2: ( (lv_name_2_0= ruleUnrestrictedName ) ) (otherlv_3= '(' ( (lv_ownedMessageSpecification_4_0= ruleSpecificationCS ) ) otherlv_5= ')' )?
                    {
                    // InternalCompleteOCL.g:377:2: ( (lv_name_2_0= ruleUnrestrictedName ) )
                    // InternalCompleteOCL.g:378:1: (lv_name_2_0= ruleUnrestrictedName )
                    {
                    // InternalCompleteOCL.g:378:1: (lv_name_2_0= ruleUnrestrictedName )
                    // InternalCompleteOCL.g:379:3: lv_name_2_0= ruleUnrestrictedName
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getInvConstraintCSAccess().getNameUnrestrictedNameParserRuleCall_2_0_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_11);
                    lv_name_2_0=ruleUnrestrictedName();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getInvConstraintCSRule());
                      	        }
                             		set(
                             			current,
                             			"name",
                              		lv_name_2_0,
                              		"org.eclipse.ocl.xtext.completeocl.CompleteOCL.UnrestrictedName");
                      	        afterParserOrEnumRuleCall();

                    }

                    }


                    }

                    // InternalCompleteOCL.g:395:2: (otherlv_3= '(' ( (lv_ownedMessageSpecification_4_0= ruleSpecificationCS ) ) otherlv_5= ')' )?
                    int alt10=2;
                    int LA10_0 = input.LA(1);

                    if ( (LA10_0==22) ) {
                        alt10=1;
                    }
                    switch (alt10) {
                        case 1 :
                            // InternalCompleteOCL.g:395:4: otherlv_3= '(' ( (lv_ownedMessageSpecification_4_0= ruleSpecificationCS ) ) otherlv_5= ')'
                            {
                            otherlv_3=(Token)match(input,22,FollowSets000.FOLLOW_12); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                  	newLeafNode(otherlv_3, grammarAccess.getInvConstraintCSAccess().getLeftParenthesisKeyword_2_1_0());

                            }
                            // InternalCompleteOCL.g:399:1: ( (lv_ownedMessageSpecification_4_0= ruleSpecificationCS ) )
                            // InternalCompleteOCL.g:400:1: (lv_ownedMessageSpecification_4_0= ruleSpecificationCS )
                            {
                            // InternalCompleteOCL.g:400:1: (lv_ownedMessageSpecification_4_0= ruleSpecificationCS )
                            // InternalCompleteOCL.g:401:3: lv_ownedMessageSpecification_4_0= ruleSpecificationCS
                            {
                            if ( state.backtracking==0 ) {

                              	        newCompositeNode(grammarAccess.getInvConstraintCSAccess().getOwnedMessageSpecificationSpecificationCSParserRuleCall_2_1_1_0());

                            }
                            pushFollow(FollowSets000.FOLLOW_13);
                            lv_ownedMessageSpecification_4_0=ruleSpecificationCS();

                            state._fsp--;
                            if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElementForParent(grammarAccess.getInvConstraintCSRule());
                              	        }
                                     		set(
                                     			current,
                                     			"ownedMessageSpecification",
                                      		lv_ownedMessageSpecification_4_0,
                                      		"org.eclipse.ocl.xtext.completeocl.CompleteOCL.SpecificationCS");
                              	        afterParserOrEnumRuleCall();

                            }

                            }


                            }

                            otherlv_5=(Token)match(input,23,FollowSets000.FOLLOW_14); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                  	newLeafNode(otherlv_5, grammarAccess.getInvConstraintCSAccess().getRightParenthesisKeyword_2_1_2());

                            }

                            }
                            break;

                    }


                    }
                    break;

            }

            otherlv_6=(Token)match(input,24,FollowSets000.FOLLOW_12); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_6, grammarAccess.getInvConstraintCSAccess().getColonKeyword_3());

            }
            // InternalCompleteOCL.g:425:1: ( (lv_ownedSpecification_7_0= ruleSpecificationCS ) )
            // InternalCompleteOCL.g:426:1: (lv_ownedSpecification_7_0= ruleSpecificationCS )
            {
            // InternalCompleteOCL.g:426:1: (lv_ownedSpecification_7_0= ruleSpecificationCS )
            // InternalCompleteOCL.g:427:3: lv_ownedSpecification_7_0= ruleSpecificationCS
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getInvConstraintCSAccess().getOwnedSpecificationSpecificationCSParserRuleCall_4_0());

            }
            pushFollow(FollowSets000.FOLLOW_2);
            lv_ownedSpecification_7_0=ruleSpecificationCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getInvConstraintCSRule());
              	        }
                     		set(
                     			current,
                     			"ownedSpecification",
                      		lv_ownedSpecification_7_0,
                      		"org.eclipse.ocl.xtext.completeocl.CompleteOCL.SpecificationCS");
              	        afterParserOrEnumRuleCall();

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
    // $ANTLR end "ruleInvConstraintCS"


    // $ANTLR start "entryRulePostConstraintCS"
    // InternalCompleteOCL.g:451:1: entryRulePostConstraintCS returns [EObject current=null] : iv_rulePostConstraintCS= rulePostConstraintCS EOF ;
    public final EObject entryRulePostConstraintCS() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePostConstraintCS = null;


        try {
            // InternalCompleteOCL.g:452:2: (iv_rulePostConstraintCS= rulePostConstraintCS EOF )
            // InternalCompleteOCL.g:453:2: iv_rulePostConstraintCS= rulePostConstraintCS EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getPostConstraintCSRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_rulePostConstraintCS=rulePostConstraintCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_rulePostConstraintCS;
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
    // $ANTLR end "entryRulePostConstraintCS"


    // $ANTLR start "rulePostConstraintCS"
    // InternalCompleteOCL.g:460:1: rulePostConstraintCS returns [EObject current=null] : ( ( (lv_ownedAnnotations_0_0= ruleCommentCS ) )? otherlv_1= 'post' ( ( (lv_name_2_0= ruleUnrestrictedName ) ) (otherlv_3= '(' ( (lv_ownedMessageSpecification_4_0= ruleSpecificationCS ) ) otherlv_5= ')' )? )? otherlv_6= ':' ( (lv_ownedSpecification_7_0= ruleSpecificationCS ) ) ) ;
    public final EObject rulePostConstraintCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_6=null;
        EObject lv_ownedAnnotations_0_0 = null;

        AntlrDatatypeRuleToken lv_name_2_0 = null;

        EObject lv_ownedMessageSpecification_4_0 = null;

        EObject lv_ownedSpecification_7_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:463:28: ( ( ( (lv_ownedAnnotations_0_0= ruleCommentCS ) )? otherlv_1= 'post' ( ( (lv_name_2_0= ruleUnrestrictedName ) ) (otherlv_3= '(' ( (lv_ownedMessageSpecification_4_0= ruleSpecificationCS ) ) otherlv_5= ')' )? )? otherlv_6= ':' ( (lv_ownedSpecification_7_0= ruleSpecificationCS ) ) ) )
            // InternalCompleteOCL.g:464:1: ( ( (lv_ownedAnnotations_0_0= ruleCommentCS ) )? otherlv_1= 'post' ( ( (lv_name_2_0= ruleUnrestrictedName ) ) (otherlv_3= '(' ( (lv_ownedMessageSpecification_4_0= ruleSpecificationCS ) ) otherlv_5= ')' )? )? otherlv_6= ':' ( (lv_ownedSpecification_7_0= ruleSpecificationCS ) ) )
            {
            // InternalCompleteOCL.g:464:1: ( ( (lv_ownedAnnotations_0_0= ruleCommentCS ) )? otherlv_1= 'post' ( ( (lv_name_2_0= ruleUnrestrictedName ) ) (otherlv_3= '(' ( (lv_ownedMessageSpecification_4_0= ruleSpecificationCS ) ) otherlv_5= ')' )? )? otherlv_6= ':' ( (lv_ownedSpecification_7_0= ruleSpecificationCS ) ) )
            // InternalCompleteOCL.g:464:2: ( (lv_ownedAnnotations_0_0= ruleCommentCS ) )? otherlv_1= 'post' ( ( (lv_name_2_0= ruleUnrestrictedName ) ) (otherlv_3= '(' ( (lv_ownedMessageSpecification_4_0= ruleSpecificationCS ) ) otherlv_5= ')' )? )? otherlv_6= ':' ( (lv_ownedSpecification_7_0= ruleSpecificationCS ) )
            {
            // InternalCompleteOCL.g:464:2: ( (lv_ownedAnnotations_0_0= ruleCommentCS ) )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==RULE_ML_DOCUMENTATION) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // InternalCompleteOCL.g:465:1: (lv_ownedAnnotations_0_0= ruleCommentCS )
                    {
                    // InternalCompleteOCL.g:465:1: (lv_ownedAnnotations_0_0= ruleCommentCS )
                    // InternalCompleteOCL.g:466:3: lv_ownedAnnotations_0_0= ruleCommentCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getPostConstraintCSAccess().getOwnedAnnotationsCommentCSParserRuleCall_0_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_15);
                    lv_ownedAnnotations_0_0=ruleCommentCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getPostConstraintCSRule());
                      	        }
                             		add(
                             			current,
                             			"ownedAnnotations",
                              		lv_ownedAnnotations_0_0,
                              		"org.eclipse.ocl.xtext.base.Base.CommentCS");
                      	        afterParserOrEnumRuleCall();

                    }

                    }


                    }
                    break;

            }

            otherlv_1=(Token)match(input,25,FollowSets000.FOLLOW_10); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getPostConstraintCSAccess().getPostKeyword_1());

            }
            // InternalCompleteOCL.g:486:1: ( ( (lv_name_2_0= ruleUnrestrictedName ) ) (otherlv_3= '(' ( (lv_ownedMessageSpecification_4_0= ruleSpecificationCS ) ) otherlv_5= ')' )? )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( ((LA14_0>=RULE_SIMPLE_ID && LA14_0<=RULE_ESCAPED_ID)||(LA14_0>=31 && LA14_0<=33)) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // InternalCompleteOCL.g:486:2: ( (lv_name_2_0= ruleUnrestrictedName ) ) (otherlv_3= '(' ( (lv_ownedMessageSpecification_4_0= ruleSpecificationCS ) ) otherlv_5= ')' )?
                    {
                    // InternalCompleteOCL.g:486:2: ( (lv_name_2_0= ruleUnrestrictedName ) )
                    // InternalCompleteOCL.g:487:1: (lv_name_2_0= ruleUnrestrictedName )
                    {
                    // InternalCompleteOCL.g:487:1: (lv_name_2_0= ruleUnrestrictedName )
                    // InternalCompleteOCL.g:488:3: lv_name_2_0= ruleUnrestrictedName
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getPostConstraintCSAccess().getNameUnrestrictedNameParserRuleCall_2_0_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_11);
                    lv_name_2_0=ruleUnrestrictedName();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getPostConstraintCSRule());
                      	        }
                             		set(
                             			current,
                             			"name",
                              		lv_name_2_0,
                              		"org.eclipse.ocl.xtext.completeocl.CompleteOCL.UnrestrictedName");
                      	        afterParserOrEnumRuleCall();

                    }

                    }


                    }

                    // InternalCompleteOCL.g:504:2: (otherlv_3= '(' ( (lv_ownedMessageSpecification_4_0= ruleSpecificationCS ) ) otherlv_5= ')' )?
                    int alt13=2;
                    int LA13_0 = input.LA(1);

                    if ( (LA13_0==22) ) {
                        alt13=1;
                    }
                    switch (alt13) {
                        case 1 :
                            // InternalCompleteOCL.g:504:4: otherlv_3= '(' ( (lv_ownedMessageSpecification_4_0= ruleSpecificationCS ) ) otherlv_5= ')'
                            {
                            otherlv_3=(Token)match(input,22,FollowSets000.FOLLOW_12); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                  	newLeafNode(otherlv_3, grammarAccess.getPostConstraintCSAccess().getLeftParenthesisKeyword_2_1_0());

                            }
                            // InternalCompleteOCL.g:508:1: ( (lv_ownedMessageSpecification_4_0= ruleSpecificationCS ) )
                            // InternalCompleteOCL.g:509:1: (lv_ownedMessageSpecification_4_0= ruleSpecificationCS )
                            {
                            // InternalCompleteOCL.g:509:1: (lv_ownedMessageSpecification_4_0= ruleSpecificationCS )
                            // InternalCompleteOCL.g:510:3: lv_ownedMessageSpecification_4_0= ruleSpecificationCS
                            {
                            if ( state.backtracking==0 ) {

                              	        newCompositeNode(grammarAccess.getPostConstraintCSAccess().getOwnedMessageSpecificationSpecificationCSParserRuleCall_2_1_1_0());

                            }
                            pushFollow(FollowSets000.FOLLOW_13);
                            lv_ownedMessageSpecification_4_0=ruleSpecificationCS();

                            state._fsp--;
                            if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElementForParent(grammarAccess.getPostConstraintCSRule());
                              	        }
                                     		set(
                                     			current,
                                     			"ownedMessageSpecification",
                                      		lv_ownedMessageSpecification_4_0,
                                      		"org.eclipse.ocl.xtext.completeocl.CompleteOCL.SpecificationCS");
                              	        afterParserOrEnumRuleCall();

                            }

                            }


                            }

                            otherlv_5=(Token)match(input,23,FollowSets000.FOLLOW_14); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                  	newLeafNode(otherlv_5, grammarAccess.getPostConstraintCSAccess().getRightParenthesisKeyword_2_1_2());

                            }

                            }
                            break;

                    }


                    }
                    break;

            }

            otherlv_6=(Token)match(input,24,FollowSets000.FOLLOW_12); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_6, grammarAccess.getPostConstraintCSAccess().getColonKeyword_3());

            }
            // InternalCompleteOCL.g:534:1: ( (lv_ownedSpecification_7_0= ruleSpecificationCS ) )
            // InternalCompleteOCL.g:535:1: (lv_ownedSpecification_7_0= ruleSpecificationCS )
            {
            // InternalCompleteOCL.g:535:1: (lv_ownedSpecification_7_0= ruleSpecificationCS )
            // InternalCompleteOCL.g:536:3: lv_ownedSpecification_7_0= ruleSpecificationCS
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getPostConstraintCSAccess().getOwnedSpecificationSpecificationCSParserRuleCall_4_0());

            }
            pushFollow(FollowSets000.FOLLOW_2);
            lv_ownedSpecification_7_0=ruleSpecificationCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getPostConstraintCSRule());
              	        }
                     		set(
                     			current,
                     			"ownedSpecification",
                      		lv_ownedSpecification_7_0,
                      		"org.eclipse.ocl.xtext.completeocl.CompleteOCL.SpecificationCS");
              	        afterParserOrEnumRuleCall();

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
    // $ANTLR end "rulePostConstraintCS"


    // $ANTLR start "entryRulePreConstraintCS"
    // InternalCompleteOCL.g:560:1: entryRulePreConstraintCS returns [EObject current=null] : iv_rulePreConstraintCS= rulePreConstraintCS EOF ;
    public final EObject entryRulePreConstraintCS() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePreConstraintCS = null;


        try {
            // InternalCompleteOCL.g:561:2: (iv_rulePreConstraintCS= rulePreConstraintCS EOF )
            // InternalCompleteOCL.g:562:2: iv_rulePreConstraintCS= rulePreConstraintCS EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getPreConstraintCSRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_rulePreConstraintCS=rulePreConstraintCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_rulePreConstraintCS;
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
    // $ANTLR end "entryRulePreConstraintCS"


    // $ANTLR start "rulePreConstraintCS"
    // InternalCompleteOCL.g:569:1: rulePreConstraintCS returns [EObject current=null] : ( ( (lv_ownedAnnotations_0_0= ruleCommentCS ) )? otherlv_1= 'pre' ( ( (lv_name_2_0= ruleUnrestrictedName ) ) (otherlv_3= '(' ( (lv_ownedMessageSpecification_4_0= ruleSpecificationCS ) ) otherlv_5= ')' )? )? otherlv_6= ':' ( (lv_ownedSpecification_7_0= ruleSpecificationCS ) ) ) ;
    public final EObject rulePreConstraintCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_6=null;
        EObject lv_ownedAnnotations_0_0 = null;

        AntlrDatatypeRuleToken lv_name_2_0 = null;

        EObject lv_ownedMessageSpecification_4_0 = null;

        EObject lv_ownedSpecification_7_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:572:28: ( ( ( (lv_ownedAnnotations_0_0= ruleCommentCS ) )? otherlv_1= 'pre' ( ( (lv_name_2_0= ruleUnrestrictedName ) ) (otherlv_3= '(' ( (lv_ownedMessageSpecification_4_0= ruleSpecificationCS ) ) otherlv_5= ')' )? )? otherlv_6= ':' ( (lv_ownedSpecification_7_0= ruleSpecificationCS ) ) ) )
            // InternalCompleteOCL.g:573:1: ( ( (lv_ownedAnnotations_0_0= ruleCommentCS ) )? otherlv_1= 'pre' ( ( (lv_name_2_0= ruleUnrestrictedName ) ) (otherlv_3= '(' ( (lv_ownedMessageSpecification_4_0= ruleSpecificationCS ) ) otherlv_5= ')' )? )? otherlv_6= ':' ( (lv_ownedSpecification_7_0= ruleSpecificationCS ) ) )
            {
            // InternalCompleteOCL.g:573:1: ( ( (lv_ownedAnnotations_0_0= ruleCommentCS ) )? otherlv_1= 'pre' ( ( (lv_name_2_0= ruleUnrestrictedName ) ) (otherlv_3= '(' ( (lv_ownedMessageSpecification_4_0= ruleSpecificationCS ) ) otherlv_5= ')' )? )? otherlv_6= ':' ( (lv_ownedSpecification_7_0= ruleSpecificationCS ) ) )
            // InternalCompleteOCL.g:573:2: ( (lv_ownedAnnotations_0_0= ruleCommentCS ) )? otherlv_1= 'pre' ( ( (lv_name_2_0= ruleUnrestrictedName ) ) (otherlv_3= '(' ( (lv_ownedMessageSpecification_4_0= ruleSpecificationCS ) ) otherlv_5= ')' )? )? otherlv_6= ':' ( (lv_ownedSpecification_7_0= ruleSpecificationCS ) )
            {
            // InternalCompleteOCL.g:573:2: ( (lv_ownedAnnotations_0_0= ruleCommentCS ) )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==RULE_ML_DOCUMENTATION) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // InternalCompleteOCL.g:574:1: (lv_ownedAnnotations_0_0= ruleCommentCS )
                    {
                    // InternalCompleteOCL.g:574:1: (lv_ownedAnnotations_0_0= ruleCommentCS )
                    // InternalCompleteOCL.g:575:3: lv_ownedAnnotations_0_0= ruleCommentCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getPreConstraintCSAccess().getOwnedAnnotationsCommentCSParserRuleCall_0_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_16);
                    lv_ownedAnnotations_0_0=ruleCommentCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getPreConstraintCSRule());
                      	        }
                             		add(
                             			current,
                             			"ownedAnnotations",
                              		lv_ownedAnnotations_0_0,
                              		"org.eclipse.ocl.xtext.base.Base.CommentCS");
                      	        afterParserOrEnumRuleCall();

                    }

                    }


                    }
                    break;

            }

            otherlv_1=(Token)match(input,26,FollowSets000.FOLLOW_10); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getPreConstraintCSAccess().getPreKeyword_1());

            }
            // InternalCompleteOCL.g:595:1: ( ( (lv_name_2_0= ruleUnrestrictedName ) ) (otherlv_3= '(' ( (lv_ownedMessageSpecification_4_0= ruleSpecificationCS ) ) otherlv_5= ')' )? )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( ((LA17_0>=RULE_SIMPLE_ID && LA17_0<=RULE_ESCAPED_ID)||(LA17_0>=31 && LA17_0<=33)) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // InternalCompleteOCL.g:595:2: ( (lv_name_2_0= ruleUnrestrictedName ) ) (otherlv_3= '(' ( (lv_ownedMessageSpecification_4_0= ruleSpecificationCS ) ) otherlv_5= ')' )?
                    {
                    // InternalCompleteOCL.g:595:2: ( (lv_name_2_0= ruleUnrestrictedName ) )
                    // InternalCompleteOCL.g:596:1: (lv_name_2_0= ruleUnrestrictedName )
                    {
                    // InternalCompleteOCL.g:596:1: (lv_name_2_0= ruleUnrestrictedName )
                    // InternalCompleteOCL.g:597:3: lv_name_2_0= ruleUnrestrictedName
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getPreConstraintCSAccess().getNameUnrestrictedNameParserRuleCall_2_0_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_11);
                    lv_name_2_0=ruleUnrestrictedName();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getPreConstraintCSRule());
                      	        }
                             		set(
                             			current,
                             			"name",
                              		lv_name_2_0,
                              		"org.eclipse.ocl.xtext.completeocl.CompleteOCL.UnrestrictedName");
                      	        afterParserOrEnumRuleCall();

                    }

                    }


                    }

                    // InternalCompleteOCL.g:613:2: (otherlv_3= '(' ( (lv_ownedMessageSpecification_4_0= ruleSpecificationCS ) ) otherlv_5= ')' )?
                    int alt16=2;
                    int LA16_0 = input.LA(1);

                    if ( (LA16_0==22) ) {
                        alt16=1;
                    }
                    switch (alt16) {
                        case 1 :
                            // InternalCompleteOCL.g:613:4: otherlv_3= '(' ( (lv_ownedMessageSpecification_4_0= ruleSpecificationCS ) ) otherlv_5= ')'
                            {
                            otherlv_3=(Token)match(input,22,FollowSets000.FOLLOW_12); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                  	newLeafNode(otherlv_3, grammarAccess.getPreConstraintCSAccess().getLeftParenthesisKeyword_2_1_0());

                            }
                            // InternalCompleteOCL.g:617:1: ( (lv_ownedMessageSpecification_4_0= ruleSpecificationCS ) )
                            // InternalCompleteOCL.g:618:1: (lv_ownedMessageSpecification_4_0= ruleSpecificationCS )
                            {
                            // InternalCompleteOCL.g:618:1: (lv_ownedMessageSpecification_4_0= ruleSpecificationCS )
                            // InternalCompleteOCL.g:619:3: lv_ownedMessageSpecification_4_0= ruleSpecificationCS
                            {
                            if ( state.backtracking==0 ) {

                              	        newCompositeNode(grammarAccess.getPreConstraintCSAccess().getOwnedMessageSpecificationSpecificationCSParserRuleCall_2_1_1_0());

                            }
                            pushFollow(FollowSets000.FOLLOW_13);
                            lv_ownedMessageSpecification_4_0=ruleSpecificationCS();

                            state._fsp--;
                            if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElementForParent(grammarAccess.getPreConstraintCSRule());
                              	        }
                                     		set(
                                     			current,
                                     			"ownedMessageSpecification",
                                      		lv_ownedMessageSpecification_4_0,
                                      		"org.eclipse.ocl.xtext.completeocl.CompleteOCL.SpecificationCS");
                              	        afterParserOrEnumRuleCall();

                            }

                            }


                            }

                            otherlv_5=(Token)match(input,23,FollowSets000.FOLLOW_14); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                  	newLeafNode(otherlv_5, grammarAccess.getPreConstraintCSAccess().getRightParenthesisKeyword_2_1_2());

                            }

                            }
                            break;

                    }


                    }
                    break;

            }

            otherlv_6=(Token)match(input,24,FollowSets000.FOLLOW_12); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_6, grammarAccess.getPreConstraintCSAccess().getColonKeyword_3());

            }
            // InternalCompleteOCL.g:643:1: ( (lv_ownedSpecification_7_0= ruleSpecificationCS ) )
            // InternalCompleteOCL.g:644:1: (lv_ownedSpecification_7_0= ruleSpecificationCS )
            {
            // InternalCompleteOCL.g:644:1: (lv_ownedSpecification_7_0= ruleSpecificationCS )
            // InternalCompleteOCL.g:645:3: lv_ownedSpecification_7_0= ruleSpecificationCS
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getPreConstraintCSAccess().getOwnedSpecificationSpecificationCSParserRuleCall_4_0());

            }
            pushFollow(FollowSets000.FOLLOW_2);
            lv_ownedSpecification_7_0=ruleSpecificationCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getPreConstraintCSRule());
              	        }
                     		set(
                     			current,
                     			"ownedSpecification",
                      		lv_ownedSpecification_7_0,
                      		"org.eclipse.ocl.xtext.completeocl.CompleteOCL.SpecificationCS");
              	        afterParserOrEnumRuleCall();

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
    // $ANTLR end "rulePreConstraintCS"


    // $ANTLR start "entryRuleContextDeclCS"
    // InternalCompleteOCL.g:669:1: entryRuleContextDeclCS returns [EObject current=null] : iv_ruleContextDeclCS= ruleContextDeclCS EOF ;
    public final EObject entryRuleContextDeclCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleContextDeclCS = null;


        try {
            // InternalCompleteOCL.g:670:2: (iv_ruleContextDeclCS= ruleContextDeclCS EOF )
            // InternalCompleteOCL.g:671:2: iv_ruleContextDeclCS= ruleContextDeclCS EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getContextDeclCSRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleContextDeclCS=ruleContextDeclCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleContextDeclCS;
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
    // $ANTLR end "entryRuleContextDeclCS"


    // $ANTLR start "ruleContextDeclCS"
    // InternalCompleteOCL.g:678:1: ruleContextDeclCS returns [EObject current=null] : (this_PropertyContextDeclCS_0= rulePropertyContextDeclCS | this_ClassifierContextDeclCS_1= ruleClassifierContextDeclCS | this_OperationContextDeclCS_2= ruleOperationContextDeclCS ) ;
    public final EObject ruleContextDeclCS() throws RecognitionException {
        EObject current = null;

        EObject this_PropertyContextDeclCS_0 = null;

        EObject this_ClassifierContextDeclCS_1 = null;

        EObject this_OperationContextDeclCS_2 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:681:28: ( (this_PropertyContextDeclCS_0= rulePropertyContextDeclCS | this_ClassifierContextDeclCS_1= ruleClassifierContextDeclCS | this_OperationContextDeclCS_2= ruleOperationContextDeclCS ) )
            // InternalCompleteOCL.g:682:1: (this_PropertyContextDeclCS_0= rulePropertyContextDeclCS | this_ClassifierContextDeclCS_1= ruleClassifierContextDeclCS | this_OperationContextDeclCS_2= ruleOperationContextDeclCS )
            {
            // InternalCompleteOCL.g:682:1: (this_PropertyContextDeclCS_0= rulePropertyContextDeclCS | this_ClassifierContextDeclCS_1= ruleClassifierContextDeclCS | this_OperationContextDeclCS_2= ruleOperationContextDeclCS )
            int alt18=3;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==RULE_ML_DOCUMENTATION) ) {
                int LA18_1 = input.LA(2);

                if ( (synpred20_InternalCompleteOCL()) ) {
                    alt18=1;
                }
                else if ( (synpred21_InternalCompleteOCL()) ) {
                    alt18=2;
                }
                else if ( (true) ) {
                    alt18=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return current;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 18, 1, input);

                    throw nvae;
                }
            }
            else if ( (LA18_0==20) ) {
                int LA18_2 = input.LA(2);

                if ( (synpred20_InternalCompleteOCL()) ) {
                    alt18=1;
                }
                else if ( (synpred21_InternalCompleteOCL()) ) {
                    alt18=2;
                }
                else if ( (true) ) {
                    alt18=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return current;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 18, 2, input);

                    throw nvae;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 18, 0, input);

                throw nvae;
            }
            switch (alt18) {
                case 1 :
                    // InternalCompleteOCL.g:683:2: this_PropertyContextDeclCS_0= rulePropertyContextDeclCS
                    {
                    if ( state.backtracking==0 ) {

                      	  /* */

                    }
                    if ( state.backtracking==0 ) {

                              newCompositeNode(grammarAccess.getContextDeclCSAccess().getPropertyContextDeclCSParserRuleCall_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_PropertyContextDeclCS_0=rulePropertyContextDeclCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_PropertyContextDeclCS_0;
                              afterParserOrEnumRuleCall();

                    }

                    }
                    break;
                case 2 :
                    // InternalCompleteOCL.g:696:2: this_ClassifierContextDeclCS_1= ruleClassifierContextDeclCS
                    {
                    if ( state.backtracking==0 ) {

                      	  /* */

                    }
                    if ( state.backtracking==0 ) {

                              newCompositeNode(grammarAccess.getContextDeclCSAccess().getClassifierContextDeclCSParserRuleCall_1());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_ClassifierContextDeclCS_1=ruleClassifierContextDeclCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_ClassifierContextDeclCS_1;
                              afterParserOrEnumRuleCall();

                    }

                    }
                    break;
                case 3 :
                    // InternalCompleteOCL.g:709:2: this_OperationContextDeclCS_2= ruleOperationContextDeclCS
                    {
                    if ( state.backtracking==0 ) {

                      	  /* */

                    }
                    if ( state.backtracking==0 ) {

                              newCompositeNode(grammarAccess.getContextDeclCSAccess().getOperationContextDeclCSParserRuleCall_2());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_OperationContextDeclCS_2=ruleOperationContextDeclCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_OperationContextDeclCS_2;
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
    // $ANTLR end "ruleContextDeclCS"


    // $ANTLR start "entryRuleDefCS"
    // InternalCompleteOCL.g:728:1: entryRuleDefCS returns [EObject current=null] : iv_ruleDefCS= ruleDefCS EOF ;
    public final EObject entryRuleDefCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDefCS = null;


        try {
            // InternalCompleteOCL.g:729:2: (iv_ruleDefCS= ruleDefCS EOF )
            // InternalCompleteOCL.g:730:2: iv_ruleDefCS= ruleDefCS EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getDefCSRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleDefCS=ruleDefCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleDefCS;
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
    // $ANTLR end "entryRuleDefCS"


    // $ANTLR start "ruleDefCS"
    // InternalCompleteOCL.g:737:1: ruleDefCS returns [EObject current=null] : (this_DefOperationCS_0= ruleDefOperationCS | this_DefPropertyCS_1= ruleDefPropertyCS ) ;
    public final EObject ruleDefCS() throws RecognitionException {
        EObject current = null;

        EObject this_DefOperationCS_0 = null;

        EObject this_DefPropertyCS_1 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:740:28: ( (this_DefOperationCS_0= ruleDefOperationCS | this_DefPropertyCS_1= ruleDefPropertyCS ) )
            // InternalCompleteOCL.g:741:1: (this_DefOperationCS_0= ruleDefOperationCS | this_DefPropertyCS_1= ruleDefPropertyCS )
            {
            // InternalCompleteOCL.g:741:1: (this_DefOperationCS_0= ruleDefOperationCS | this_DefPropertyCS_1= ruleDefPropertyCS )
            int alt19=2;
            alt19 = dfa19.predict(input);
            switch (alt19) {
                case 1 :
                    // InternalCompleteOCL.g:742:2: this_DefOperationCS_0= ruleDefOperationCS
                    {
                    if ( state.backtracking==0 ) {

                      	  /* */

                    }
                    if ( state.backtracking==0 ) {

                              newCompositeNode(grammarAccess.getDefCSAccess().getDefOperationCSParserRuleCall_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_DefOperationCS_0=ruleDefOperationCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_DefOperationCS_0;
                              afterParserOrEnumRuleCall();

                    }

                    }
                    break;
                case 2 :
                    // InternalCompleteOCL.g:755:2: this_DefPropertyCS_1= ruleDefPropertyCS
                    {
                    if ( state.backtracking==0 ) {

                      	  /* */

                    }
                    if ( state.backtracking==0 ) {

                              newCompositeNode(grammarAccess.getDefCSAccess().getDefPropertyCSParserRuleCall_1());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_DefPropertyCS_1=ruleDefPropertyCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_DefPropertyCS_1;
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
    // $ANTLR end "ruleDefCS"


    // $ANTLR start "entryRuleDefOperationCS"
    // InternalCompleteOCL.g:774:1: entryRuleDefOperationCS returns [EObject current=null] : iv_ruleDefOperationCS= ruleDefOperationCS EOF ;
    public final EObject entryRuleDefOperationCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDefOperationCS = null;


        try {
            // InternalCompleteOCL.g:775:2: (iv_ruleDefOperationCS= ruleDefOperationCS EOF )
            // InternalCompleteOCL.g:776:2: iv_ruleDefOperationCS= ruleDefOperationCS EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getDefOperationCSRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleDefOperationCS=ruleDefOperationCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleDefOperationCS;
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
    // $ANTLR end "entryRuleDefOperationCS"


    // $ANTLR start "ruleDefOperationCS"
    // InternalCompleteOCL.g:783:1: ruleDefOperationCS returns [EObject current=null] : ( ( (lv_ownedAnnotations_0_0= ruleCommentCS ) )? ( (lv_isStatic_1_0= 'static' ) )? otherlv_2= 'def' ( ruleUnrestrictedName )? otherlv_4= ':' ( (lv_ownedSignature_5_0= ruleTemplateSignatureCS ) )? ( (lv_name_6_0= ruleUnrestrictedName ) ) otherlv_7= '(' ( ( (lv_ownedParameters_8_0= ruleDefParameterCS ) ) (otherlv_9= ',' ( (lv_ownedParameters_10_0= ruleDefParameterCS ) ) )* )? otherlv_11= ')' otherlv_12= ':' ( (lv_ownedType_13_0= ruleTypeExpCS ) )? otherlv_14= '=' ( (lv_ownedSpecification_15_0= ruleSpecificationCS ) ) ) ;
    public final EObject ruleDefOperationCS() throws RecognitionException {
        EObject current = null;

        Token lv_isStatic_1_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_7=null;
        Token otherlv_9=null;
        Token otherlv_11=null;
        Token otherlv_12=null;
        Token otherlv_14=null;
        EObject lv_ownedAnnotations_0_0 = null;

        EObject lv_ownedSignature_5_0 = null;

        AntlrDatatypeRuleToken lv_name_6_0 = null;

        EObject lv_ownedParameters_8_0 = null;

        EObject lv_ownedParameters_10_0 = null;

        EObject lv_ownedType_13_0 = null;

        EObject lv_ownedSpecification_15_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:786:28: ( ( ( (lv_ownedAnnotations_0_0= ruleCommentCS ) )? ( (lv_isStatic_1_0= 'static' ) )? otherlv_2= 'def' ( ruleUnrestrictedName )? otherlv_4= ':' ( (lv_ownedSignature_5_0= ruleTemplateSignatureCS ) )? ( (lv_name_6_0= ruleUnrestrictedName ) ) otherlv_7= '(' ( ( (lv_ownedParameters_8_0= ruleDefParameterCS ) ) (otherlv_9= ',' ( (lv_ownedParameters_10_0= ruleDefParameterCS ) ) )* )? otherlv_11= ')' otherlv_12= ':' ( (lv_ownedType_13_0= ruleTypeExpCS ) )? otherlv_14= '=' ( (lv_ownedSpecification_15_0= ruleSpecificationCS ) ) ) )
            // InternalCompleteOCL.g:787:1: ( ( (lv_ownedAnnotations_0_0= ruleCommentCS ) )? ( (lv_isStatic_1_0= 'static' ) )? otherlv_2= 'def' ( ruleUnrestrictedName )? otherlv_4= ':' ( (lv_ownedSignature_5_0= ruleTemplateSignatureCS ) )? ( (lv_name_6_0= ruleUnrestrictedName ) ) otherlv_7= '(' ( ( (lv_ownedParameters_8_0= ruleDefParameterCS ) ) (otherlv_9= ',' ( (lv_ownedParameters_10_0= ruleDefParameterCS ) ) )* )? otherlv_11= ')' otherlv_12= ':' ( (lv_ownedType_13_0= ruleTypeExpCS ) )? otherlv_14= '=' ( (lv_ownedSpecification_15_0= ruleSpecificationCS ) ) )
            {
            // InternalCompleteOCL.g:787:1: ( ( (lv_ownedAnnotations_0_0= ruleCommentCS ) )? ( (lv_isStatic_1_0= 'static' ) )? otherlv_2= 'def' ( ruleUnrestrictedName )? otherlv_4= ':' ( (lv_ownedSignature_5_0= ruleTemplateSignatureCS ) )? ( (lv_name_6_0= ruleUnrestrictedName ) ) otherlv_7= '(' ( ( (lv_ownedParameters_8_0= ruleDefParameterCS ) ) (otherlv_9= ',' ( (lv_ownedParameters_10_0= ruleDefParameterCS ) ) )* )? otherlv_11= ')' otherlv_12= ':' ( (lv_ownedType_13_0= ruleTypeExpCS ) )? otherlv_14= '=' ( (lv_ownedSpecification_15_0= ruleSpecificationCS ) ) )
            // InternalCompleteOCL.g:787:2: ( (lv_ownedAnnotations_0_0= ruleCommentCS ) )? ( (lv_isStatic_1_0= 'static' ) )? otherlv_2= 'def' ( ruleUnrestrictedName )? otherlv_4= ':' ( (lv_ownedSignature_5_0= ruleTemplateSignatureCS ) )? ( (lv_name_6_0= ruleUnrestrictedName ) ) otherlv_7= '(' ( ( (lv_ownedParameters_8_0= ruleDefParameterCS ) ) (otherlv_9= ',' ( (lv_ownedParameters_10_0= ruleDefParameterCS ) ) )* )? otherlv_11= ')' otherlv_12= ':' ( (lv_ownedType_13_0= ruleTypeExpCS ) )? otherlv_14= '=' ( (lv_ownedSpecification_15_0= ruleSpecificationCS ) )
            {
            // InternalCompleteOCL.g:787:2: ( (lv_ownedAnnotations_0_0= ruleCommentCS ) )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==RULE_ML_DOCUMENTATION) ) {
                alt20=1;
            }
            switch (alt20) {
                case 1 :
                    // InternalCompleteOCL.g:788:1: (lv_ownedAnnotations_0_0= ruleCommentCS )
                    {
                    // InternalCompleteOCL.g:788:1: (lv_ownedAnnotations_0_0= ruleCommentCS )
                    // InternalCompleteOCL.g:789:3: lv_ownedAnnotations_0_0= ruleCommentCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getDefOperationCSAccess().getOwnedAnnotationsCommentCSParserRuleCall_0_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_17);
                    lv_ownedAnnotations_0_0=ruleCommentCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getDefOperationCSRule());
                      	        }
                             		add(
                             			current,
                             			"ownedAnnotations",
                              		lv_ownedAnnotations_0_0,
                              		"org.eclipse.ocl.xtext.base.Base.CommentCS");
                      	        afterParserOrEnumRuleCall();

                    }

                    }


                    }
                    break;

            }

            // InternalCompleteOCL.g:805:3: ( (lv_isStatic_1_0= 'static' ) )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==27) ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // InternalCompleteOCL.g:806:1: (lv_isStatic_1_0= 'static' )
                    {
                    // InternalCompleteOCL.g:806:1: (lv_isStatic_1_0= 'static' )
                    // InternalCompleteOCL.g:807:3: lv_isStatic_1_0= 'static'
                    {
                    lv_isStatic_1_0=(Token)match(input,27,FollowSets000.FOLLOW_18); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_isStatic_1_0, grammarAccess.getDefOperationCSAccess().getIsStaticStaticKeyword_1_0());

                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getDefOperationCSRule());
                      	        }
                             		setWithLastConsumed(current, "isStatic", true, "static");

                    }

                    }


                    }
                    break;

            }

            otherlv_2=(Token)match(input,28,FollowSets000.FOLLOW_10); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_2, grammarAccess.getDefOperationCSAccess().getDefKeyword_2());

            }
            // InternalCompleteOCL.g:824:1: ( ruleUnrestrictedName )?
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( ((LA22_0>=RULE_SIMPLE_ID && LA22_0<=RULE_ESCAPED_ID)||(LA22_0>=31 && LA22_0<=33)) ) {
                alt22=1;
            }
            switch (alt22) {
                case 1 :
                    // InternalCompleteOCL.g:825:2: ruleUnrestrictedName
                    {
                    if ( state.backtracking==0 ) {

                      	  /* */

                    }
                    if ( state.backtracking==0 ) {

                              newCompositeNode(grammarAccess.getDefOperationCSAccess().getUnrestrictedNameParserRuleCall_3());

                    }
                    pushFollow(FollowSets000.FOLLOW_14);
                    ruleUnrestrictedName();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              afterParserOrEnumRuleCall();

                    }

                    }
                    break;

            }

            otherlv_4=(Token)match(input,24,FollowSets000.FOLLOW_6); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_4, grammarAccess.getDefOperationCSAccess().getColonKeyword_4());

            }
            // InternalCompleteOCL.g:839:1: ( (lv_ownedSignature_5_0= ruleTemplateSignatureCS ) )?
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==22||LA23_0==40) ) {
                alt23=1;
            }
            switch (alt23) {
                case 1 :
                    // InternalCompleteOCL.g:840:1: (lv_ownedSignature_5_0= ruleTemplateSignatureCS )
                    {
                    // InternalCompleteOCL.g:840:1: (lv_ownedSignature_5_0= ruleTemplateSignatureCS )
                    // InternalCompleteOCL.g:841:3: lv_ownedSignature_5_0= ruleTemplateSignatureCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getDefOperationCSAccess().getOwnedSignatureTemplateSignatureCSParserRuleCall_5_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_19);
                    lv_ownedSignature_5_0=ruleTemplateSignatureCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getDefOperationCSRule());
                      	        }
                             		set(
                             			current,
                             			"ownedSignature",
                              		lv_ownedSignature_5_0,
                              		"org.eclipse.ocl.xtext.completeocl.CompleteOCL.TemplateSignatureCS");
                      	        afterParserOrEnumRuleCall();

                    }

                    }


                    }
                    break;

            }

            // InternalCompleteOCL.g:857:3: ( (lv_name_6_0= ruleUnrestrictedName ) )
            // InternalCompleteOCL.g:858:1: (lv_name_6_0= ruleUnrestrictedName )
            {
            // InternalCompleteOCL.g:858:1: (lv_name_6_0= ruleUnrestrictedName )
            // InternalCompleteOCL.g:859:3: lv_name_6_0= ruleUnrestrictedName
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getDefOperationCSAccess().getNameUnrestrictedNameParserRuleCall_6_0());

            }
            pushFollow(FollowSets000.FOLLOW_20);
            lv_name_6_0=ruleUnrestrictedName();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getDefOperationCSRule());
              	        }
                     		set(
                     			current,
                     			"name",
                      		lv_name_6_0,
                      		"org.eclipse.ocl.xtext.completeocl.CompleteOCL.UnrestrictedName");
              	        afterParserOrEnumRuleCall();

            }

            }


            }

            otherlv_7=(Token)match(input,22,FollowSets000.FOLLOW_21); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_7, grammarAccess.getDefOperationCSAccess().getLeftParenthesisKeyword_7());

            }
            // InternalCompleteOCL.g:879:1: ( ( (lv_ownedParameters_8_0= ruleDefParameterCS ) ) (otherlv_9= ',' ( (lv_ownedParameters_10_0= ruleDefParameterCS ) ) )* )?
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( ((LA25_0>=RULE_SIMPLE_ID && LA25_0<=RULE_ESCAPED_ID)||(LA25_0>=31 && LA25_0<=33)) ) {
                alt25=1;
            }
            switch (alt25) {
                case 1 :
                    // InternalCompleteOCL.g:879:2: ( (lv_ownedParameters_8_0= ruleDefParameterCS ) ) (otherlv_9= ',' ( (lv_ownedParameters_10_0= ruleDefParameterCS ) ) )*
                    {
                    // InternalCompleteOCL.g:879:2: ( (lv_ownedParameters_8_0= ruleDefParameterCS ) )
                    // InternalCompleteOCL.g:880:1: (lv_ownedParameters_8_0= ruleDefParameterCS )
                    {
                    // InternalCompleteOCL.g:880:1: (lv_ownedParameters_8_0= ruleDefParameterCS )
                    // InternalCompleteOCL.g:881:3: lv_ownedParameters_8_0= ruleDefParameterCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getDefOperationCSAccess().getOwnedParametersDefParameterCSParserRuleCall_8_0_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_22);
                    lv_ownedParameters_8_0=ruleDefParameterCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getDefOperationCSRule());
                      	        }
                             		add(
                             			current,
                             			"ownedParameters",
                              		lv_ownedParameters_8_0,
                              		"org.eclipse.ocl.xtext.completeocl.CompleteOCL.DefParameterCS");
                      	        afterParserOrEnumRuleCall();

                    }

                    }


                    }

                    // InternalCompleteOCL.g:897:2: (otherlv_9= ',' ( (lv_ownedParameters_10_0= ruleDefParameterCS ) ) )*
                    loop24:
                    do {
                        int alt24=2;
                        int LA24_0 = input.LA(1);

                        if ( (LA24_0==29) ) {
                            alt24=1;
                        }


                        switch (alt24) {
                    	case 1 :
                    	    // InternalCompleteOCL.g:897:4: otherlv_9= ',' ( (lv_ownedParameters_10_0= ruleDefParameterCS ) )
                    	    {
                    	    otherlv_9=(Token)match(input,29,FollowSets000.FOLLOW_19); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_9, grammarAccess.getDefOperationCSAccess().getCommaKeyword_8_1_0());

                    	    }
                    	    // InternalCompleteOCL.g:901:1: ( (lv_ownedParameters_10_0= ruleDefParameterCS ) )
                    	    // InternalCompleteOCL.g:902:1: (lv_ownedParameters_10_0= ruleDefParameterCS )
                    	    {
                    	    // InternalCompleteOCL.g:902:1: (lv_ownedParameters_10_0= ruleDefParameterCS )
                    	    // InternalCompleteOCL.g:903:3: lv_ownedParameters_10_0= ruleDefParameterCS
                    	    {
                    	    if ( state.backtracking==0 ) {

                    	      	        newCompositeNode(grammarAccess.getDefOperationCSAccess().getOwnedParametersDefParameterCSParserRuleCall_8_1_1_0());

                    	    }
                    	    pushFollow(FollowSets000.FOLLOW_22);
                    	    lv_ownedParameters_10_0=ruleDefParameterCS();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = createModelElementForParent(grammarAccess.getDefOperationCSRule());
                    	      	        }
                    	             		add(
                    	             			current,
                    	             			"ownedParameters",
                    	              		lv_ownedParameters_10_0,
                    	              		"org.eclipse.ocl.xtext.completeocl.CompleteOCL.DefParameterCS");
                    	      	        afterParserOrEnumRuleCall();

                    	    }

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

            otherlv_11=(Token)match(input,23,FollowSets000.FOLLOW_14); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_11, grammarAccess.getDefOperationCSAccess().getRightParenthesisKeyword_9());

            }
            otherlv_12=(Token)match(input,24,FollowSets000.FOLLOW_23); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_12, grammarAccess.getDefOperationCSAccess().getColonKeyword_10());

            }
            // InternalCompleteOCL.g:927:1: ( (lv_ownedType_13_0= ruleTypeExpCS ) )?
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( ((LA26_0>=RULE_SIMPLE_ID && LA26_0<=RULE_ESCAPED_ID)||(LA26_0>=31 && LA26_0<=33)||(LA26_0>=43 && LA26_0<=52)||(LA26_0>=74 && LA26_0<=75)||(LA26_0>=77 && LA26_0<=81)) ) {
                alt26=1;
            }
            switch (alt26) {
                case 1 :
                    // InternalCompleteOCL.g:928:1: (lv_ownedType_13_0= ruleTypeExpCS )
                    {
                    // InternalCompleteOCL.g:928:1: (lv_ownedType_13_0= ruleTypeExpCS )
                    // InternalCompleteOCL.g:929:3: lv_ownedType_13_0= ruleTypeExpCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getDefOperationCSAccess().getOwnedTypeTypeExpCSParserRuleCall_11_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_24);
                    lv_ownedType_13_0=ruleTypeExpCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getDefOperationCSRule());
                      	        }
                             		set(
                             			current,
                             			"ownedType",
                              		lv_ownedType_13_0,
                              		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.TypeExpCS");
                      	        afterParserOrEnumRuleCall();

                    }

                    }


                    }
                    break;

            }

            otherlv_14=(Token)match(input,30,FollowSets000.FOLLOW_12); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_14, grammarAccess.getDefOperationCSAccess().getEqualsSignKeyword_12());

            }
            // InternalCompleteOCL.g:949:1: ( (lv_ownedSpecification_15_0= ruleSpecificationCS ) )
            // InternalCompleteOCL.g:950:1: (lv_ownedSpecification_15_0= ruleSpecificationCS )
            {
            // InternalCompleteOCL.g:950:1: (lv_ownedSpecification_15_0= ruleSpecificationCS )
            // InternalCompleteOCL.g:951:3: lv_ownedSpecification_15_0= ruleSpecificationCS
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getDefOperationCSAccess().getOwnedSpecificationSpecificationCSParserRuleCall_13_0());

            }
            pushFollow(FollowSets000.FOLLOW_2);
            lv_ownedSpecification_15_0=ruleSpecificationCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getDefOperationCSRule());
              	        }
                     		set(
                     			current,
                     			"ownedSpecification",
                      		lv_ownedSpecification_15_0,
                      		"org.eclipse.ocl.xtext.completeocl.CompleteOCL.SpecificationCS");
              	        afterParserOrEnumRuleCall();

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
    // $ANTLR end "ruleDefOperationCS"


    // $ANTLR start "entryRuleDefParameterCS"
    // InternalCompleteOCL.g:975:1: entryRuleDefParameterCS returns [EObject current=null] : iv_ruleDefParameterCS= ruleDefParameterCS EOF ;
    public final EObject entryRuleDefParameterCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDefParameterCS = null;


        try {
            // InternalCompleteOCL.g:976:2: (iv_ruleDefParameterCS= ruleDefParameterCS EOF )
            // InternalCompleteOCL.g:977:2: iv_ruleDefParameterCS= ruleDefParameterCS EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getDefParameterCSRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleDefParameterCS=ruleDefParameterCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleDefParameterCS;
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
    // $ANTLR end "entryRuleDefParameterCS"


    // $ANTLR start "ruleDefParameterCS"
    // InternalCompleteOCL.g:984:1: ruleDefParameterCS returns [EObject current=null] : ( ( (lv_name_0_0= ruleUnrestrictedName ) ) otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) ) ) ;
    public final EObject ruleDefParameterCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_name_0_0 = null;

        EObject lv_ownedType_2_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:987:28: ( ( ( (lv_name_0_0= ruleUnrestrictedName ) ) otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) ) ) )
            // InternalCompleteOCL.g:988:1: ( ( (lv_name_0_0= ruleUnrestrictedName ) ) otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) ) )
            {
            // InternalCompleteOCL.g:988:1: ( ( (lv_name_0_0= ruleUnrestrictedName ) ) otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) ) )
            // InternalCompleteOCL.g:988:2: ( (lv_name_0_0= ruleUnrestrictedName ) ) otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) )
            {
            // InternalCompleteOCL.g:988:2: ( (lv_name_0_0= ruleUnrestrictedName ) )
            // InternalCompleteOCL.g:989:1: (lv_name_0_0= ruleUnrestrictedName )
            {
            // InternalCompleteOCL.g:989:1: (lv_name_0_0= ruleUnrestrictedName )
            // InternalCompleteOCL.g:990:3: lv_name_0_0= ruleUnrestrictedName
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getDefParameterCSAccess().getNameUnrestrictedNameParserRuleCall_0_0());

            }
            pushFollow(FollowSets000.FOLLOW_14);
            lv_name_0_0=ruleUnrestrictedName();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getDefParameterCSRule());
              	        }
                     		set(
                     			current,
                     			"name",
                      		lv_name_0_0,
                      		"org.eclipse.ocl.xtext.completeocl.CompleteOCL.UnrestrictedName");
              	        afterParserOrEnumRuleCall();

            }

            }


            }

            otherlv_1=(Token)match(input,24,FollowSets000.FOLLOW_25); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getDefParameterCSAccess().getColonKeyword_1());

            }
            // InternalCompleteOCL.g:1010:1: ( (lv_ownedType_2_0= ruleTypeExpCS ) )
            // InternalCompleteOCL.g:1011:1: (lv_ownedType_2_0= ruleTypeExpCS )
            {
            // InternalCompleteOCL.g:1011:1: (lv_ownedType_2_0= ruleTypeExpCS )
            // InternalCompleteOCL.g:1012:3: lv_ownedType_2_0= ruleTypeExpCS
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getDefParameterCSAccess().getOwnedTypeTypeExpCSParserRuleCall_2_0());

            }
            pushFollow(FollowSets000.FOLLOW_2);
            lv_ownedType_2_0=ruleTypeExpCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getDefParameterCSRule());
              	        }
                     		set(
                     			current,
                     			"ownedType",
                      		lv_ownedType_2_0,
                      		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.TypeExpCS");
              	        afterParserOrEnumRuleCall();

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
    // $ANTLR end "ruleDefParameterCS"


    // $ANTLR start "entryRuleDefPropertyCS"
    // InternalCompleteOCL.g:1036:1: entryRuleDefPropertyCS returns [EObject current=null] : iv_ruleDefPropertyCS= ruleDefPropertyCS EOF ;
    public final EObject entryRuleDefPropertyCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDefPropertyCS = null;


        try {
            // InternalCompleteOCL.g:1037:2: (iv_ruleDefPropertyCS= ruleDefPropertyCS EOF )
            // InternalCompleteOCL.g:1038:2: iv_ruleDefPropertyCS= ruleDefPropertyCS EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getDefPropertyCSRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleDefPropertyCS=ruleDefPropertyCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleDefPropertyCS;
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
    // $ANTLR end "entryRuleDefPropertyCS"


    // $ANTLR start "ruleDefPropertyCS"
    // InternalCompleteOCL.g:1045:1: ruleDefPropertyCS returns [EObject current=null] : ( ( (lv_ownedAnnotations_0_0= ruleCommentCS ) )? ( (lv_isStatic_1_0= 'static' ) )? otherlv_2= 'def' ( ruleUnrestrictedName )? otherlv_4= ':' ( (lv_name_5_0= ruleUnrestrictedName ) ) otherlv_6= ':' ( (lv_ownedType_7_0= ruleTypeExpCS ) ) otherlv_8= '=' ( (lv_ownedSpecification_9_0= ruleSpecificationCS ) ) ) ;
    public final EObject ruleDefPropertyCS() throws RecognitionException {
        EObject current = null;

        Token lv_isStatic_1_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        EObject lv_ownedAnnotations_0_0 = null;

        AntlrDatatypeRuleToken lv_name_5_0 = null;

        EObject lv_ownedType_7_0 = null;

        EObject lv_ownedSpecification_9_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:1048:28: ( ( ( (lv_ownedAnnotations_0_0= ruleCommentCS ) )? ( (lv_isStatic_1_0= 'static' ) )? otherlv_2= 'def' ( ruleUnrestrictedName )? otherlv_4= ':' ( (lv_name_5_0= ruleUnrestrictedName ) ) otherlv_6= ':' ( (lv_ownedType_7_0= ruleTypeExpCS ) ) otherlv_8= '=' ( (lv_ownedSpecification_9_0= ruleSpecificationCS ) ) ) )
            // InternalCompleteOCL.g:1049:1: ( ( (lv_ownedAnnotations_0_0= ruleCommentCS ) )? ( (lv_isStatic_1_0= 'static' ) )? otherlv_2= 'def' ( ruleUnrestrictedName )? otherlv_4= ':' ( (lv_name_5_0= ruleUnrestrictedName ) ) otherlv_6= ':' ( (lv_ownedType_7_0= ruleTypeExpCS ) ) otherlv_8= '=' ( (lv_ownedSpecification_9_0= ruleSpecificationCS ) ) )
            {
            // InternalCompleteOCL.g:1049:1: ( ( (lv_ownedAnnotations_0_0= ruleCommentCS ) )? ( (lv_isStatic_1_0= 'static' ) )? otherlv_2= 'def' ( ruleUnrestrictedName )? otherlv_4= ':' ( (lv_name_5_0= ruleUnrestrictedName ) ) otherlv_6= ':' ( (lv_ownedType_7_0= ruleTypeExpCS ) ) otherlv_8= '=' ( (lv_ownedSpecification_9_0= ruleSpecificationCS ) ) )
            // InternalCompleteOCL.g:1049:2: ( (lv_ownedAnnotations_0_0= ruleCommentCS ) )? ( (lv_isStatic_1_0= 'static' ) )? otherlv_2= 'def' ( ruleUnrestrictedName )? otherlv_4= ':' ( (lv_name_5_0= ruleUnrestrictedName ) ) otherlv_6= ':' ( (lv_ownedType_7_0= ruleTypeExpCS ) ) otherlv_8= '=' ( (lv_ownedSpecification_9_0= ruleSpecificationCS ) )
            {
            // InternalCompleteOCL.g:1049:2: ( (lv_ownedAnnotations_0_0= ruleCommentCS ) )?
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( (LA27_0==RULE_ML_DOCUMENTATION) ) {
                alt27=1;
            }
            switch (alt27) {
                case 1 :
                    // InternalCompleteOCL.g:1050:1: (lv_ownedAnnotations_0_0= ruleCommentCS )
                    {
                    // InternalCompleteOCL.g:1050:1: (lv_ownedAnnotations_0_0= ruleCommentCS )
                    // InternalCompleteOCL.g:1051:3: lv_ownedAnnotations_0_0= ruleCommentCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getDefPropertyCSAccess().getOwnedAnnotationsCommentCSParserRuleCall_0_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_17);
                    lv_ownedAnnotations_0_0=ruleCommentCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getDefPropertyCSRule());
                      	        }
                             		add(
                             			current,
                             			"ownedAnnotations",
                              		lv_ownedAnnotations_0_0,
                              		"org.eclipse.ocl.xtext.base.Base.CommentCS");
                      	        afterParserOrEnumRuleCall();

                    }

                    }


                    }
                    break;

            }

            // InternalCompleteOCL.g:1067:3: ( (lv_isStatic_1_0= 'static' ) )?
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==27) ) {
                alt28=1;
            }
            switch (alt28) {
                case 1 :
                    // InternalCompleteOCL.g:1068:1: (lv_isStatic_1_0= 'static' )
                    {
                    // InternalCompleteOCL.g:1068:1: (lv_isStatic_1_0= 'static' )
                    // InternalCompleteOCL.g:1069:3: lv_isStatic_1_0= 'static'
                    {
                    lv_isStatic_1_0=(Token)match(input,27,FollowSets000.FOLLOW_18); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_isStatic_1_0, grammarAccess.getDefPropertyCSAccess().getIsStaticStaticKeyword_1_0());

                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getDefPropertyCSRule());
                      	        }
                             		setWithLastConsumed(current, "isStatic", true, "static");

                    }

                    }


                    }
                    break;

            }

            otherlv_2=(Token)match(input,28,FollowSets000.FOLLOW_10); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_2, grammarAccess.getDefPropertyCSAccess().getDefKeyword_2());

            }
            // InternalCompleteOCL.g:1086:1: ( ruleUnrestrictedName )?
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( ((LA29_0>=RULE_SIMPLE_ID && LA29_0<=RULE_ESCAPED_ID)||(LA29_0>=31 && LA29_0<=33)) ) {
                alt29=1;
            }
            switch (alt29) {
                case 1 :
                    // InternalCompleteOCL.g:1087:2: ruleUnrestrictedName
                    {
                    if ( state.backtracking==0 ) {

                      	  /* */

                    }
                    if ( state.backtracking==0 ) {

                              newCompositeNode(grammarAccess.getDefPropertyCSAccess().getUnrestrictedNameParserRuleCall_3());

                    }
                    pushFollow(FollowSets000.FOLLOW_14);
                    ruleUnrestrictedName();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              afterParserOrEnumRuleCall();

                    }

                    }
                    break;

            }

            otherlv_4=(Token)match(input,24,FollowSets000.FOLLOW_19); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_4, grammarAccess.getDefPropertyCSAccess().getColonKeyword_4());

            }
            // InternalCompleteOCL.g:1101:1: ( (lv_name_5_0= ruleUnrestrictedName ) )
            // InternalCompleteOCL.g:1102:1: (lv_name_5_0= ruleUnrestrictedName )
            {
            // InternalCompleteOCL.g:1102:1: (lv_name_5_0= ruleUnrestrictedName )
            // InternalCompleteOCL.g:1103:3: lv_name_5_0= ruleUnrestrictedName
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getDefPropertyCSAccess().getNameUnrestrictedNameParserRuleCall_5_0());

            }
            pushFollow(FollowSets000.FOLLOW_14);
            lv_name_5_0=ruleUnrestrictedName();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getDefPropertyCSRule());
              	        }
                     		set(
                     			current,
                     			"name",
                      		lv_name_5_0,
                      		"org.eclipse.ocl.xtext.completeocl.CompleteOCL.UnrestrictedName");
              	        afterParserOrEnumRuleCall();

            }

            }


            }

            otherlv_6=(Token)match(input,24,FollowSets000.FOLLOW_25); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_6, grammarAccess.getDefPropertyCSAccess().getColonKeyword_6());

            }
            // InternalCompleteOCL.g:1123:1: ( (lv_ownedType_7_0= ruleTypeExpCS ) )
            // InternalCompleteOCL.g:1124:1: (lv_ownedType_7_0= ruleTypeExpCS )
            {
            // InternalCompleteOCL.g:1124:1: (lv_ownedType_7_0= ruleTypeExpCS )
            // InternalCompleteOCL.g:1125:3: lv_ownedType_7_0= ruleTypeExpCS
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getDefPropertyCSAccess().getOwnedTypeTypeExpCSParserRuleCall_7_0());

            }
            pushFollow(FollowSets000.FOLLOW_24);
            lv_ownedType_7_0=ruleTypeExpCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getDefPropertyCSRule());
              	        }
                     		set(
                     			current,
                     			"ownedType",
                      		lv_ownedType_7_0,
                      		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.TypeExpCS");
              	        afterParserOrEnumRuleCall();

            }

            }


            }

            otherlv_8=(Token)match(input,30,FollowSets000.FOLLOW_12); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_8, grammarAccess.getDefPropertyCSAccess().getEqualsSignKeyword_8());

            }
            // InternalCompleteOCL.g:1145:1: ( (lv_ownedSpecification_9_0= ruleSpecificationCS ) )
            // InternalCompleteOCL.g:1146:1: (lv_ownedSpecification_9_0= ruleSpecificationCS )
            {
            // InternalCompleteOCL.g:1146:1: (lv_ownedSpecification_9_0= ruleSpecificationCS )
            // InternalCompleteOCL.g:1147:3: lv_ownedSpecification_9_0= ruleSpecificationCS
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getDefPropertyCSAccess().getOwnedSpecificationSpecificationCSParserRuleCall_9_0());

            }
            pushFollow(FollowSets000.FOLLOW_2);
            lv_ownedSpecification_9_0=ruleSpecificationCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getDefPropertyCSRule());
              	        }
                     		set(
                     			current,
                     			"ownedSpecification",
                      		lv_ownedSpecification_9_0,
                      		"org.eclipse.ocl.xtext.completeocl.CompleteOCL.SpecificationCS");
              	        afterParserOrEnumRuleCall();

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
    // $ANTLR end "ruleDefPropertyCS"


    // $ANTLR start "entryRuleImportCS"
    // InternalCompleteOCL.g:1171:1: entryRuleImportCS returns [EObject current=null] : iv_ruleImportCS= ruleImportCS EOF ;
    public final EObject entryRuleImportCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleImportCS = null;


        try {
            // InternalCompleteOCL.g:1172:2: (iv_ruleImportCS= ruleImportCS EOF )
            // InternalCompleteOCL.g:1173:2: iv_ruleImportCS= ruleImportCS EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getImportCSRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleImportCS=ruleImportCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleImportCS;
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
    // $ANTLR end "entryRuleImportCS"


    // $ANTLR start "ruleImportCS"
    // InternalCompleteOCL.g:1180:1: ruleImportCS returns [EObject current=null] : ( ( (lv_ownedAnnotations_0_0= ruleCommentCS ) )? (otherlv_1= 'import' | otherlv_2= 'include' | otherlv_3= 'library' ) ( ( (lv_name_4_0= ruleIdentifier ) ) otherlv_5= ':' )? ( (lv_ownedPathName_6_0= ruleURIPathNameCS ) ) ( (lv_isAll_7_0= '::*' ) )? ) ;
    public final EObject ruleImportCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token lv_isAll_7_0=null;
        EObject lv_ownedAnnotations_0_0 = null;

        AntlrDatatypeRuleToken lv_name_4_0 = null;

        EObject lv_ownedPathName_6_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:1183:28: ( ( ( (lv_ownedAnnotations_0_0= ruleCommentCS ) )? (otherlv_1= 'import' | otherlv_2= 'include' | otherlv_3= 'library' ) ( ( (lv_name_4_0= ruleIdentifier ) ) otherlv_5= ':' )? ( (lv_ownedPathName_6_0= ruleURIPathNameCS ) ) ( (lv_isAll_7_0= '::*' ) )? ) )
            // InternalCompleteOCL.g:1184:1: ( ( (lv_ownedAnnotations_0_0= ruleCommentCS ) )? (otherlv_1= 'import' | otherlv_2= 'include' | otherlv_3= 'library' ) ( ( (lv_name_4_0= ruleIdentifier ) ) otherlv_5= ':' )? ( (lv_ownedPathName_6_0= ruleURIPathNameCS ) ) ( (lv_isAll_7_0= '::*' ) )? )
            {
            // InternalCompleteOCL.g:1184:1: ( ( (lv_ownedAnnotations_0_0= ruleCommentCS ) )? (otherlv_1= 'import' | otherlv_2= 'include' | otherlv_3= 'library' ) ( ( (lv_name_4_0= ruleIdentifier ) ) otherlv_5= ':' )? ( (lv_ownedPathName_6_0= ruleURIPathNameCS ) ) ( (lv_isAll_7_0= '::*' ) )? )
            // InternalCompleteOCL.g:1184:2: ( (lv_ownedAnnotations_0_0= ruleCommentCS ) )? (otherlv_1= 'import' | otherlv_2= 'include' | otherlv_3= 'library' ) ( ( (lv_name_4_0= ruleIdentifier ) ) otherlv_5= ':' )? ( (lv_ownedPathName_6_0= ruleURIPathNameCS ) ) ( (lv_isAll_7_0= '::*' ) )?
            {
            // InternalCompleteOCL.g:1184:2: ( (lv_ownedAnnotations_0_0= ruleCommentCS ) )?
            int alt30=2;
            int LA30_0 = input.LA(1);

            if ( (LA30_0==RULE_ML_DOCUMENTATION) ) {
                alt30=1;
            }
            switch (alt30) {
                case 1 :
                    // InternalCompleteOCL.g:1185:1: (lv_ownedAnnotations_0_0= ruleCommentCS )
                    {
                    // InternalCompleteOCL.g:1185:1: (lv_ownedAnnotations_0_0= ruleCommentCS )
                    // InternalCompleteOCL.g:1186:3: lv_ownedAnnotations_0_0= ruleCommentCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getImportCSAccess().getOwnedAnnotationsCommentCSParserRuleCall_0_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_26);
                    lv_ownedAnnotations_0_0=ruleCommentCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getImportCSRule());
                      	        }
                             		add(
                             			current,
                             			"ownedAnnotations",
                              		lv_ownedAnnotations_0_0,
                              		"org.eclipse.ocl.xtext.base.Base.CommentCS");
                      	        afterParserOrEnumRuleCall();

                    }

                    }


                    }
                    break;

            }

            // InternalCompleteOCL.g:1202:3: (otherlv_1= 'import' | otherlv_2= 'include' | otherlv_3= 'library' )
            int alt31=3;
            switch ( input.LA(1) ) {
            case 31:
                {
                alt31=1;
                }
                break;
            case 32:
                {
                alt31=2;
                }
                break;
            case 33:
                {
                alt31=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 31, 0, input);

                throw nvae;
            }

            switch (alt31) {
                case 1 :
                    // InternalCompleteOCL.g:1202:5: otherlv_1= 'import'
                    {
                    otherlv_1=(Token)match(input,31,FollowSets000.FOLLOW_27); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_1, grammarAccess.getImportCSAccess().getImportKeyword_1_0());

                    }

                    }
                    break;
                case 2 :
                    // InternalCompleteOCL.g:1207:7: otherlv_2= 'include'
                    {
                    otherlv_2=(Token)match(input,32,FollowSets000.FOLLOW_27); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_2, grammarAccess.getImportCSAccess().getIncludeKeyword_1_1());

                    }

                    }
                    break;
                case 3 :
                    // InternalCompleteOCL.g:1212:7: otherlv_3= 'library'
                    {
                    otherlv_3=(Token)match(input,33,FollowSets000.FOLLOW_27); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_3, grammarAccess.getImportCSAccess().getLibraryKeyword_1_2());

                    }

                    }
                    break;

            }

            // InternalCompleteOCL.g:1216:2: ( ( (lv_name_4_0= ruleIdentifier ) ) otherlv_5= ':' )?
            int alt32=2;
            int LA32_0 = input.LA(1);

            if ( (LA32_0==RULE_SIMPLE_ID) ) {
                int LA32_1 = input.LA(2);

                if ( (LA32_1==24) ) {
                    alt32=1;
                }
            }
            else if ( (LA32_0==RULE_ESCAPED_ID) ) {
                int LA32_2 = input.LA(2);

                if ( (LA32_2==24) ) {
                    alt32=1;
                }
            }
            switch (alt32) {
                case 1 :
                    // InternalCompleteOCL.g:1216:3: ( (lv_name_4_0= ruleIdentifier ) ) otherlv_5= ':'
                    {
                    // InternalCompleteOCL.g:1216:3: ( (lv_name_4_0= ruleIdentifier ) )
                    // InternalCompleteOCL.g:1217:1: (lv_name_4_0= ruleIdentifier )
                    {
                    // InternalCompleteOCL.g:1217:1: (lv_name_4_0= ruleIdentifier )
                    // InternalCompleteOCL.g:1218:3: lv_name_4_0= ruleIdentifier
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getImportCSAccess().getNameIdentifierParserRuleCall_2_0_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_14);
                    lv_name_4_0=ruleIdentifier();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getImportCSRule());
                      	        }
                             		set(
                             			current,
                             			"name",
                              		lv_name_4_0,
                              		"org.eclipse.ocl.xtext.base.Base.Identifier");
                      	        afterParserOrEnumRuleCall();

                    }

                    }


                    }

                    otherlv_5=(Token)match(input,24,FollowSets000.FOLLOW_27); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_5, grammarAccess.getImportCSAccess().getColonKeyword_2_1());

                    }

                    }
                    break;

            }

            // InternalCompleteOCL.g:1238:3: ( (lv_ownedPathName_6_0= ruleURIPathNameCS ) )
            // InternalCompleteOCL.g:1239:1: (lv_ownedPathName_6_0= ruleURIPathNameCS )
            {
            // InternalCompleteOCL.g:1239:1: (lv_ownedPathName_6_0= ruleURIPathNameCS )
            // InternalCompleteOCL.g:1240:3: lv_ownedPathName_6_0= ruleURIPathNameCS
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getImportCSAccess().getOwnedPathNameURIPathNameCSParserRuleCall_3_0());

            }
            pushFollow(FollowSets000.FOLLOW_28);
            lv_ownedPathName_6_0=ruleURIPathNameCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getImportCSRule());
              	        }
                     		set(
                     			current,
                     			"ownedPathName",
                      		lv_ownedPathName_6_0,
                      		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.URIPathNameCS");
              	        afterParserOrEnumRuleCall();

            }

            }


            }

            // InternalCompleteOCL.g:1256:2: ( (lv_isAll_7_0= '::*' ) )?
            int alt33=2;
            int LA33_0 = input.LA(1);

            if ( (LA33_0==34) ) {
                alt33=1;
            }
            switch (alt33) {
                case 1 :
                    // InternalCompleteOCL.g:1257:1: (lv_isAll_7_0= '::*' )
                    {
                    // InternalCompleteOCL.g:1257:1: (lv_isAll_7_0= '::*' )
                    // InternalCompleteOCL.g:1258:3: lv_isAll_7_0= '::*'
                    {
                    lv_isAll_7_0=(Token)match(input,34,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_isAll_7_0, grammarAccess.getImportCSAccess().getIsAllColonColonAsteriskKeyword_4_0());

                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getImportCSRule());
                      	        }
                             		setWithLastConsumed(current, "isAll", true, "::*");

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
    // $ANTLR end "ruleImportCS"


    // $ANTLR start "entryRuleOperationContextDeclCS"
    // InternalCompleteOCL.g:1279:1: entryRuleOperationContextDeclCS returns [EObject current=null] : iv_ruleOperationContextDeclCS= ruleOperationContextDeclCS EOF ;
    public final EObject entryRuleOperationContextDeclCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOperationContextDeclCS = null;


        try {
            // InternalCompleteOCL.g:1280:2: (iv_ruleOperationContextDeclCS= ruleOperationContextDeclCS EOF )
            // InternalCompleteOCL.g:1281:2: iv_ruleOperationContextDeclCS= ruleOperationContextDeclCS EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getOperationContextDeclCSRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleOperationContextDeclCS=ruleOperationContextDeclCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleOperationContextDeclCS;
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
    // $ANTLR end "entryRuleOperationContextDeclCS"


    // $ANTLR start "ruleOperationContextDeclCS"
    // InternalCompleteOCL.g:1288:1: ruleOperationContextDeclCS returns [EObject current=null] : ( ( (lv_ownedAnnotations_0_0= ruleCommentCS ) )? otherlv_1= 'context' ( (lv_ownedSignature_2_0= ruleTemplateSignatureCS ) )? ( (lv_ownedPathName_3_0= rulePathNameCS ) ) otherlv_4= '(' ( ( (lv_ownedParameters_5_0= ruleParameterCS ) ) (otherlv_6= ',' ( (lv_ownedParameters_7_0= ruleParameterCS ) ) )* )? otherlv_8= ')' otherlv_9= ':' ( (lv_ownedType_10_0= ruleTypeExpCS ) )? ( ( (lv_ownedPreconditions_11_0= rulePreConstraintCS ) ) | ( (lv_ownedPostconditions_12_0= rulePostConstraintCS ) ) | ( (lv_ownedBodies_13_0= ruleBodySpecificationCS ) ) )* ) ;
    public final EObject ruleOperationContextDeclCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token otherlv_9=null;
        EObject lv_ownedAnnotations_0_0 = null;

        EObject lv_ownedSignature_2_0 = null;

        EObject lv_ownedPathName_3_0 = null;

        EObject lv_ownedParameters_5_0 = null;

        EObject lv_ownedParameters_7_0 = null;

        EObject lv_ownedType_10_0 = null;

        EObject lv_ownedPreconditions_11_0 = null;

        EObject lv_ownedPostconditions_12_0 = null;

        EObject lv_ownedBodies_13_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:1291:28: ( ( ( (lv_ownedAnnotations_0_0= ruleCommentCS ) )? otherlv_1= 'context' ( (lv_ownedSignature_2_0= ruleTemplateSignatureCS ) )? ( (lv_ownedPathName_3_0= rulePathNameCS ) ) otherlv_4= '(' ( ( (lv_ownedParameters_5_0= ruleParameterCS ) ) (otherlv_6= ',' ( (lv_ownedParameters_7_0= ruleParameterCS ) ) )* )? otherlv_8= ')' otherlv_9= ':' ( (lv_ownedType_10_0= ruleTypeExpCS ) )? ( ( (lv_ownedPreconditions_11_0= rulePreConstraintCS ) ) | ( (lv_ownedPostconditions_12_0= rulePostConstraintCS ) ) | ( (lv_ownedBodies_13_0= ruleBodySpecificationCS ) ) )* ) )
            // InternalCompleteOCL.g:1292:1: ( ( (lv_ownedAnnotations_0_0= ruleCommentCS ) )? otherlv_1= 'context' ( (lv_ownedSignature_2_0= ruleTemplateSignatureCS ) )? ( (lv_ownedPathName_3_0= rulePathNameCS ) ) otherlv_4= '(' ( ( (lv_ownedParameters_5_0= ruleParameterCS ) ) (otherlv_6= ',' ( (lv_ownedParameters_7_0= ruleParameterCS ) ) )* )? otherlv_8= ')' otherlv_9= ':' ( (lv_ownedType_10_0= ruleTypeExpCS ) )? ( ( (lv_ownedPreconditions_11_0= rulePreConstraintCS ) ) | ( (lv_ownedPostconditions_12_0= rulePostConstraintCS ) ) | ( (lv_ownedBodies_13_0= ruleBodySpecificationCS ) ) )* )
            {
            // InternalCompleteOCL.g:1292:1: ( ( (lv_ownedAnnotations_0_0= ruleCommentCS ) )? otherlv_1= 'context' ( (lv_ownedSignature_2_0= ruleTemplateSignatureCS ) )? ( (lv_ownedPathName_3_0= rulePathNameCS ) ) otherlv_4= '(' ( ( (lv_ownedParameters_5_0= ruleParameterCS ) ) (otherlv_6= ',' ( (lv_ownedParameters_7_0= ruleParameterCS ) ) )* )? otherlv_8= ')' otherlv_9= ':' ( (lv_ownedType_10_0= ruleTypeExpCS ) )? ( ( (lv_ownedPreconditions_11_0= rulePreConstraintCS ) ) | ( (lv_ownedPostconditions_12_0= rulePostConstraintCS ) ) | ( (lv_ownedBodies_13_0= ruleBodySpecificationCS ) ) )* )
            // InternalCompleteOCL.g:1292:2: ( (lv_ownedAnnotations_0_0= ruleCommentCS ) )? otherlv_1= 'context' ( (lv_ownedSignature_2_0= ruleTemplateSignatureCS ) )? ( (lv_ownedPathName_3_0= rulePathNameCS ) ) otherlv_4= '(' ( ( (lv_ownedParameters_5_0= ruleParameterCS ) ) (otherlv_6= ',' ( (lv_ownedParameters_7_0= ruleParameterCS ) ) )* )? otherlv_8= ')' otherlv_9= ':' ( (lv_ownedType_10_0= ruleTypeExpCS ) )? ( ( (lv_ownedPreconditions_11_0= rulePreConstraintCS ) ) | ( (lv_ownedPostconditions_12_0= rulePostConstraintCS ) ) | ( (lv_ownedBodies_13_0= ruleBodySpecificationCS ) ) )*
            {
            // InternalCompleteOCL.g:1292:2: ( (lv_ownedAnnotations_0_0= ruleCommentCS ) )?
            int alt34=2;
            int LA34_0 = input.LA(1);

            if ( (LA34_0==RULE_ML_DOCUMENTATION) ) {
                alt34=1;
            }
            switch (alt34) {
                case 1 :
                    // InternalCompleteOCL.g:1293:1: (lv_ownedAnnotations_0_0= ruleCommentCS )
                    {
                    // InternalCompleteOCL.g:1293:1: (lv_ownedAnnotations_0_0= ruleCommentCS )
                    // InternalCompleteOCL.g:1294:3: lv_ownedAnnotations_0_0= ruleCommentCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getOperationContextDeclCSAccess().getOwnedAnnotationsCommentCSParserRuleCall_0_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_5);
                    lv_ownedAnnotations_0_0=ruleCommentCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getOperationContextDeclCSRule());
                      	        }
                             		add(
                             			current,
                             			"ownedAnnotations",
                              		lv_ownedAnnotations_0_0,
                              		"org.eclipse.ocl.xtext.base.Base.CommentCS");
                      	        afterParserOrEnumRuleCall();

                    }

                    }


                    }
                    break;

            }

            otherlv_1=(Token)match(input,20,FollowSets000.FOLLOW_6); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getOperationContextDeclCSAccess().getContextKeyword_1());

            }
            // InternalCompleteOCL.g:1314:1: ( (lv_ownedSignature_2_0= ruleTemplateSignatureCS ) )?
            int alt35=2;
            int LA35_0 = input.LA(1);

            if ( (LA35_0==22||LA35_0==40) ) {
                alt35=1;
            }
            switch (alt35) {
                case 1 :
                    // InternalCompleteOCL.g:1315:1: (lv_ownedSignature_2_0= ruleTemplateSignatureCS )
                    {
                    // InternalCompleteOCL.g:1315:1: (lv_ownedSignature_2_0= ruleTemplateSignatureCS )
                    // InternalCompleteOCL.g:1316:3: lv_ownedSignature_2_0= ruleTemplateSignatureCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getOperationContextDeclCSAccess().getOwnedSignatureTemplateSignatureCSParserRuleCall_2_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_6);
                    lv_ownedSignature_2_0=ruleTemplateSignatureCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getOperationContextDeclCSRule());
                      	        }
                             		set(
                             			current,
                             			"ownedSignature",
                              		lv_ownedSignature_2_0,
                              		"org.eclipse.ocl.xtext.completeocl.CompleteOCL.TemplateSignatureCS");
                      	        afterParserOrEnumRuleCall();

                    }

                    }


                    }
                    break;

            }

            // InternalCompleteOCL.g:1332:3: ( (lv_ownedPathName_3_0= rulePathNameCS ) )
            // InternalCompleteOCL.g:1333:1: (lv_ownedPathName_3_0= rulePathNameCS )
            {
            // InternalCompleteOCL.g:1333:1: (lv_ownedPathName_3_0= rulePathNameCS )
            // InternalCompleteOCL.g:1334:3: lv_ownedPathName_3_0= rulePathNameCS
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getOperationContextDeclCSAccess().getOwnedPathNamePathNameCSParserRuleCall_3_0());

            }
            pushFollow(FollowSets000.FOLLOW_20);
            lv_ownedPathName_3_0=rulePathNameCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getOperationContextDeclCSRule());
              	        }
                     		set(
                     			current,
                     			"ownedPathName",
                      		lv_ownedPathName_3_0,
                      		"org.eclipse.ocl.xtext.base.Base.PathNameCS");
              	        afterParserOrEnumRuleCall();

            }

            }


            }

            otherlv_4=(Token)match(input,22,FollowSets000.FOLLOW_29); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_4, grammarAccess.getOperationContextDeclCSAccess().getLeftParenthesisKeyword_4());

            }
            // InternalCompleteOCL.g:1354:1: ( ( (lv_ownedParameters_5_0= ruleParameterCS ) ) (otherlv_6= ',' ( (lv_ownedParameters_7_0= ruleParameterCS ) ) )* )?
            int alt37=2;
            int LA37_0 = input.LA(1);

            if ( ((LA37_0>=RULE_SIMPLE_ID && LA37_0<=RULE_ESCAPED_ID)||(LA37_0>=31 && LA37_0<=33)||(LA37_0>=43 && LA37_0<=52)||(LA37_0>=74 && LA37_0<=75)||(LA37_0>=77 && LA37_0<=81)) ) {
                alt37=1;
            }
            switch (alt37) {
                case 1 :
                    // InternalCompleteOCL.g:1354:2: ( (lv_ownedParameters_5_0= ruleParameterCS ) ) (otherlv_6= ',' ( (lv_ownedParameters_7_0= ruleParameterCS ) ) )*
                    {
                    // InternalCompleteOCL.g:1354:2: ( (lv_ownedParameters_5_0= ruleParameterCS ) )
                    // InternalCompleteOCL.g:1355:1: (lv_ownedParameters_5_0= ruleParameterCS )
                    {
                    // InternalCompleteOCL.g:1355:1: (lv_ownedParameters_5_0= ruleParameterCS )
                    // InternalCompleteOCL.g:1356:3: lv_ownedParameters_5_0= ruleParameterCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getOperationContextDeclCSAccess().getOwnedParametersParameterCSParserRuleCall_5_0_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_22);
                    lv_ownedParameters_5_0=ruleParameterCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getOperationContextDeclCSRule());
                      	        }
                             		add(
                             			current,
                             			"ownedParameters",
                              		lv_ownedParameters_5_0,
                              		"org.eclipse.ocl.xtext.completeocl.CompleteOCL.ParameterCS");
                      	        afterParserOrEnumRuleCall();

                    }

                    }


                    }

                    // InternalCompleteOCL.g:1372:2: (otherlv_6= ',' ( (lv_ownedParameters_7_0= ruleParameterCS ) ) )*
                    loop36:
                    do {
                        int alt36=2;
                        int LA36_0 = input.LA(1);

                        if ( (LA36_0==29) ) {
                            alt36=1;
                        }


                        switch (alt36) {
                    	case 1 :
                    	    // InternalCompleteOCL.g:1372:4: otherlv_6= ',' ( (lv_ownedParameters_7_0= ruleParameterCS ) )
                    	    {
                    	    otherlv_6=(Token)match(input,29,FollowSets000.FOLLOW_25); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_6, grammarAccess.getOperationContextDeclCSAccess().getCommaKeyword_5_1_0());

                    	    }
                    	    // InternalCompleteOCL.g:1376:1: ( (lv_ownedParameters_7_0= ruleParameterCS ) )
                    	    // InternalCompleteOCL.g:1377:1: (lv_ownedParameters_7_0= ruleParameterCS )
                    	    {
                    	    // InternalCompleteOCL.g:1377:1: (lv_ownedParameters_7_0= ruleParameterCS )
                    	    // InternalCompleteOCL.g:1378:3: lv_ownedParameters_7_0= ruleParameterCS
                    	    {
                    	    if ( state.backtracking==0 ) {

                    	      	        newCompositeNode(grammarAccess.getOperationContextDeclCSAccess().getOwnedParametersParameterCSParserRuleCall_5_1_1_0());

                    	    }
                    	    pushFollow(FollowSets000.FOLLOW_22);
                    	    lv_ownedParameters_7_0=ruleParameterCS();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = createModelElementForParent(grammarAccess.getOperationContextDeclCSRule());
                    	      	        }
                    	             		add(
                    	             			current,
                    	             			"ownedParameters",
                    	              		lv_ownedParameters_7_0,
                    	              		"org.eclipse.ocl.xtext.completeocl.CompleteOCL.ParameterCS");
                    	      	        afterParserOrEnumRuleCall();

                    	    }

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop36;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_8=(Token)match(input,23,FollowSets000.FOLLOW_14); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_8, grammarAccess.getOperationContextDeclCSAccess().getRightParenthesisKeyword_6());

            }
            otherlv_9=(Token)match(input,24,FollowSets000.FOLLOW_30); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_9, grammarAccess.getOperationContextDeclCSAccess().getColonKeyword_7());

            }
            // InternalCompleteOCL.g:1402:1: ( (lv_ownedType_10_0= ruleTypeExpCS ) )?
            int alt38=2;
            int LA38_0 = input.LA(1);

            if ( ((LA38_0>=RULE_SIMPLE_ID && LA38_0<=RULE_ESCAPED_ID)||(LA38_0>=31 && LA38_0<=33)||(LA38_0>=43 && LA38_0<=52)||(LA38_0>=74 && LA38_0<=75)||(LA38_0>=77 && LA38_0<=81)) ) {
                alt38=1;
            }
            switch (alt38) {
                case 1 :
                    // InternalCompleteOCL.g:1403:1: (lv_ownedType_10_0= ruleTypeExpCS )
                    {
                    // InternalCompleteOCL.g:1403:1: (lv_ownedType_10_0= ruleTypeExpCS )
                    // InternalCompleteOCL.g:1404:3: lv_ownedType_10_0= ruleTypeExpCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getOperationContextDeclCSAccess().getOwnedTypeTypeExpCSParserRuleCall_8_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_31);
                    lv_ownedType_10_0=ruleTypeExpCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getOperationContextDeclCSRule());
                      	        }
                             		set(
                             			current,
                             			"ownedType",
                              		lv_ownedType_10_0,
                              		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.TypeExpCS");
                      	        afterParserOrEnumRuleCall();

                    }

                    }


                    }
                    break;

            }

            // InternalCompleteOCL.g:1420:3: ( ( (lv_ownedPreconditions_11_0= rulePreConstraintCS ) ) | ( (lv_ownedPostconditions_12_0= rulePostConstraintCS ) ) | ( (lv_ownedBodies_13_0= ruleBodySpecificationCS ) ) )*
            loop39:
            do {
                int alt39=4;
                switch ( input.LA(1) ) {
                case RULE_ML_DOCUMENTATION:
                    {
                    switch ( input.LA(2) ) {
                    case 37:
                        {
                        alt39=3;
                        }
                        break;
                    case 25:
                        {
                        alt39=2;
                        }
                        break;
                    case 26:
                        {
                        alt39=1;
                        }
                        break;

                    }

                    }
                    break;
                case 26:
                    {
                    alt39=1;
                    }
                    break;
                case 25:
                    {
                    alt39=2;
                    }
                    break;
                case 37:
                    {
                    alt39=3;
                    }
                    break;

                }

                switch (alt39) {
            	case 1 :
            	    // InternalCompleteOCL.g:1420:4: ( (lv_ownedPreconditions_11_0= rulePreConstraintCS ) )
            	    {
            	    // InternalCompleteOCL.g:1420:4: ( (lv_ownedPreconditions_11_0= rulePreConstraintCS ) )
            	    // InternalCompleteOCL.g:1421:1: (lv_ownedPreconditions_11_0= rulePreConstraintCS )
            	    {
            	    // InternalCompleteOCL.g:1421:1: (lv_ownedPreconditions_11_0= rulePreConstraintCS )
            	    // InternalCompleteOCL.g:1422:3: lv_ownedPreconditions_11_0= rulePreConstraintCS
            	    {
            	    if ( state.backtracking==0 ) {

            	      	        newCompositeNode(grammarAccess.getOperationContextDeclCSAccess().getOwnedPreconditionsPreConstraintCSParserRuleCall_9_0_0());

            	    }
            	    pushFollow(FollowSets000.FOLLOW_31);
            	    lv_ownedPreconditions_11_0=rulePreConstraintCS();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getOperationContextDeclCSRule());
            	      	        }
            	             		add(
            	             			current,
            	             			"ownedPreconditions",
            	              		lv_ownedPreconditions_11_0,
            	              		"org.eclipse.ocl.xtext.completeocl.CompleteOCL.PreConstraintCS");
            	      	        afterParserOrEnumRuleCall();

            	    }

            	    }


            	    }


            	    }
            	    break;
            	case 2 :
            	    // InternalCompleteOCL.g:1439:6: ( (lv_ownedPostconditions_12_0= rulePostConstraintCS ) )
            	    {
            	    // InternalCompleteOCL.g:1439:6: ( (lv_ownedPostconditions_12_0= rulePostConstraintCS ) )
            	    // InternalCompleteOCL.g:1440:1: (lv_ownedPostconditions_12_0= rulePostConstraintCS )
            	    {
            	    // InternalCompleteOCL.g:1440:1: (lv_ownedPostconditions_12_0= rulePostConstraintCS )
            	    // InternalCompleteOCL.g:1441:3: lv_ownedPostconditions_12_0= rulePostConstraintCS
            	    {
            	    if ( state.backtracking==0 ) {

            	      	        newCompositeNode(grammarAccess.getOperationContextDeclCSAccess().getOwnedPostconditionsPostConstraintCSParserRuleCall_9_1_0());

            	    }
            	    pushFollow(FollowSets000.FOLLOW_31);
            	    lv_ownedPostconditions_12_0=rulePostConstraintCS();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getOperationContextDeclCSRule());
            	      	        }
            	             		add(
            	             			current,
            	             			"ownedPostconditions",
            	              		lv_ownedPostconditions_12_0,
            	              		"org.eclipse.ocl.xtext.completeocl.CompleteOCL.PostConstraintCS");
            	      	        afterParserOrEnumRuleCall();

            	    }

            	    }


            	    }


            	    }
            	    break;
            	case 3 :
            	    // InternalCompleteOCL.g:1458:6: ( (lv_ownedBodies_13_0= ruleBodySpecificationCS ) )
            	    {
            	    // InternalCompleteOCL.g:1458:6: ( (lv_ownedBodies_13_0= ruleBodySpecificationCS ) )
            	    // InternalCompleteOCL.g:1459:1: (lv_ownedBodies_13_0= ruleBodySpecificationCS )
            	    {
            	    // InternalCompleteOCL.g:1459:1: (lv_ownedBodies_13_0= ruleBodySpecificationCS )
            	    // InternalCompleteOCL.g:1460:3: lv_ownedBodies_13_0= ruleBodySpecificationCS
            	    {
            	    if ( state.backtracking==0 ) {

            	      	        newCompositeNode(grammarAccess.getOperationContextDeclCSAccess().getOwnedBodiesBodySpecificationCSParserRuleCall_9_2_0());

            	    }
            	    pushFollow(FollowSets000.FOLLOW_31);
            	    lv_ownedBodies_13_0=ruleBodySpecificationCS();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getOperationContextDeclCSRule());
            	      	        }
            	             		add(
            	             			current,
            	             			"ownedBodies",
            	              		lv_ownedBodies_13_0,
            	              		"org.eclipse.ocl.xtext.completeocl.CompleteOCL.BodySpecificationCS");
            	      	        afterParserOrEnumRuleCall();

            	    }

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop39;
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
    // $ANTLR end "ruleOperationContextDeclCS"


    // $ANTLR start "entryRulePackageDeclarationCS"
    // InternalCompleteOCL.g:1484:1: entryRulePackageDeclarationCS returns [EObject current=null] : iv_rulePackageDeclarationCS= rulePackageDeclarationCS EOF ;
    public final EObject entryRulePackageDeclarationCS() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePackageDeclarationCS = null;


        try {
            // InternalCompleteOCL.g:1485:2: (iv_rulePackageDeclarationCS= rulePackageDeclarationCS EOF )
            // InternalCompleteOCL.g:1486:2: iv_rulePackageDeclarationCS= rulePackageDeclarationCS EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getPackageDeclarationCSRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_rulePackageDeclarationCS=rulePackageDeclarationCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_rulePackageDeclarationCS;
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
    // $ANTLR end "entryRulePackageDeclarationCS"


    // $ANTLR start "rulePackageDeclarationCS"
    // InternalCompleteOCL.g:1493:1: rulePackageDeclarationCS returns [EObject current=null] : ( ( (lv_ownedAnnotations_0_0= ruleCommentCS ) )? otherlv_1= 'package' ( (lv_ownedPathName_2_0= rulePathNameCS ) ) ( (lv_ownedInvariants_3_0= ruleInvConstraintCS ) )* ( (lv_ownedContexts_4_0= ruleContextDeclCS ) )* otherlv_5= 'endpackage' ) ;
    public final EObject rulePackageDeclarationCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_5=null;
        EObject lv_ownedAnnotations_0_0 = null;

        EObject lv_ownedPathName_2_0 = null;

        EObject lv_ownedInvariants_3_0 = null;

        EObject lv_ownedContexts_4_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:1496:28: ( ( ( (lv_ownedAnnotations_0_0= ruleCommentCS ) )? otherlv_1= 'package' ( (lv_ownedPathName_2_0= rulePathNameCS ) ) ( (lv_ownedInvariants_3_0= ruleInvConstraintCS ) )* ( (lv_ownedContexts_4_0= ruleContextDeclCS ) )* otherlv_5= 'endpackage' ) )
            // InternalCompleteOCL.g:1497:1: ( ( (lv_ownedAnnotations_0_0= ruleCommentCS ) )? otherlv_1= 'package' ( (lv_ownedPathName_2_0= rulePathNameCS ) ) ( (lv_ownedInvariants_3_0= ruleInvConstraintCS ) )* ( (lv_ownedContexts_4_0= ruleContextDeclCS ) )* otherlv_5= 'endpackage' )
            {
            // InternalCompleteOCL.g:1497:1: ( ( (lv_ownedAnnotations_0_0= ruleCommentCS ) )? otherlv_1= 'package' ( (lv_ownedPathName_2_0= rulePathNameCS ) ) ( (lv_ownedInvariants_3_0= ruleInvConstraintCS ) )* ( (lv_ownedContexts_4_0= ruleContextDeclCS ) )* otherlv_5= 'endpackage' )
            // InternalCompleteOCL.g:1497:2: ( (lv_ownedAnnotations_0_0= ruleCommentCS ) )? otherlv_1= 'package' ( (lv_ownedPathName_2_0= rulePathNameCS ) ) ( (lv_ownedInvariants_3_0= ruleInvConstraintCS ) )* ( (lv_ownedContexts_4_0= ruleContextDeclCS ) )* otherlv_5= 'endpackage'
            {
            // InternalCompleteOCL.g:1497:2: ( (lv_ownedAnnotations_0_0= ruleCommentCS ) )?
            int alt40=2;
            int LA40_0 = input.LA(1);

            if ( (LA40_0==RULE_ML_DOCUMENTATION) ) {
                alt40=1;
            }
            switch (alt40) {
                case 1 :
                    // InternalCompleteOCL.g:1498:1: (lv_ownedAnnotations_0_0= ruleCommentCS )
                    {
                    // InternalCompleteOCL.g:1498:1: (lv_ownedAnnotations_0_0= ruleCommentCS )
                    // InternalCompleteOCL.g:1499:3: lv_ownedAnnotations_0_0= ruleCommentCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getPackageDeclarationCSAccess().getOwnedAnnotationsCommentCSParserRuleCall_0_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_32);
                    lv_ownedAnnotations_0_0=ruleCommentCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getPackageDeclarationCSRule());
                      	        }
                             		add(
                             			current,
                             			"ownedAnnotations",
                              		lv_ownedAnnotations_0_0,
                              		"org.eclipse.ocl.xtext.base.Base.CommentCS");
                      	        afterParserOrEnumRuleCall();

                    }

                    }


                    }
                    break;

            }

            otherlv_1=(Token)match(input,35,FollowSets000.FOLLOW_6); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getPackageDeclarationCSAccess().getPackageKeyword_1());

            }
            // InternalCompleteOCL.g:1519:1: ( (lv_ownedPathName_2_0= rulePathNameCS ) )
            // InternalCompleteOCL.g:1520:1: (lv_ownedPathName_2_0= rulePathNameCS )
            {
            // InternalCompleteOCL.g:1520:1: (lv_ownedPathName_2_0= rulePathNameCS )
            // InternalCompleteOCL.g:1521:3: lv_ownedPathName_2_0= rulePathNameCS
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getPackageDeclarationCSAccess().getOwnedPathNamePathNameCSParserRuleCall_2_0());

            }
            pushFollow(FollowSets000.FOLLOW_33);
            lv_ownedPathName_2_0=rulePathNameCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getPackageDeclarationCSRule());
              	        }
                     		set(
                     			current,
                     			"ownedPathName",
                      		lv_ownedPathName_2_0,
                      		"org.eclipse.ocl.xtext.base.Base.PathNameCS");
              	        afterParserOrEnumRuleCall();

            }

            }


            }

            // InternalCompleteOCL.g:1537:2: ( (lv_ownedInvariants_3_0= ruleInvConstraintCS ) )*
            loop41:
            do {
                int alt41=2;
                int LA41_0 = input.LA(1);

                if ( (LA41_0==RULE_ML_DOCUMENTATION) ) {
                    int LA41_1 = input.LA(2);

                    if ( (LA41_1==21) ) {
                        alt41=1;
                    }


                }
                else if ( (LA41_0==21) ) {
                    alt41=1;
                }


                switch (alt41) {
            	case 1 :
            	    // InternalCompleteOCL.g:1538:1: (lv_ownedInvariants_3_0= ruleInvConstraintCS )
            	    {
            	    // InternalCompleteOCL.g:1538:1: (lv_ownedInvariants_3_0= ruleInvConstraintCS )
            	    // InternalCompleteOCL.g:1539:3: lv_ownedInvariants_3_0= ruleInvConstraintCS
            	    {
            	    if ( state.backtracking==0 ) {

            	      	        newCompositeNode(grammarAccess.getPackageDeclarationCSAccess().getOwnedInvariantsInvConstraintCSParserRuleCall_3_0());

            	    }
            	    pushFollow(FollowSets000.FOLLOW_33);
            	    lv_ownedInvariants_3_0=ruleInvConstraintCS();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getPackageDeclarationCSRule());
            	      	        }
            	             		add(
            	             			current,
            	             			"ownedInvariants",
            	              		lv_ownedInvariants_3_0,
            	              		"org.eclipse.ocl.xtext.completeocl.CompleteOCL.InvConstraintCS");
            	      	        afterParserOrEnumRuleCall();

            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop41;
                }
            } while (true);

            // InternalCompleteOCL.g:1555:3: ( (lv_ownedContexts_4_0= ruleContextDeclCS ) )*
            loop42:
            do {
                int alt42=2;
                int LA42_0 = input.LA(1);

                if ( (LA42_0==RULE_ML_DOCUMENTATION||LA42_0==20) ) {
                    alt42=1;
                }


                switch (alt42) {
            	case 1 :
            	    // InternalCompleteOCL.g:1556:1: (lv_ownedContexts_4_0= ruleContextDeclCS )
            	    {
            	    // InternalCompleteOCL.g:1556:1: (lv_ownedContexts_4_0= ruleContextDeclCS )
            	    // InternalCompleteOCL.g:1557:3: lv_ownedContexts_4_0= ruleContextDeclCS
            	    {
            	    if ( state.backtracking==0 ) {

            	      	        newCompositeNode(grammarAccess.getPackageDeclarationCSAccess().getOwnedContextsContextDeclCSParserRuleCall_4_0());

            	    }
            	    pushFollow(FollowSets000.FOLLOW_34);
            	    lv_ownedContexts_4_0=ruleContextDeclCS();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getPackageDeclarationCSRule());
            	      	        }
            	             		add(
            	             			current,
            	             			"ownedContexts",
            	              		lv_ownedContexts_4_0,
            	              		"org.eclipse.ocl.xtext.completeocl.CompleteOCL.ContextDeclCS");
            	      	        afterParserOrEnumRuleCall();

            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop42;
                }
            } while (true);

            otherlv_5=(Token)match(input,36,FollowSets000.FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_5, grammarAccess.getPackageDeclarationCSAccess().getEndpackageKeyword_5());

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
    // $ANTLR end "rulePackageDeclarationCS"


    // $ANTLR start "entryRuleParameterCS"
    // InternalCompleteOCL.g:1585:1: entryRuleParameterCS returns [EObject current=null] : iv_ruleParameterCS= ruleParameterCS EOF ;
    public final EObject entryRuleParameterCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleParameterCS = null;


        try {
            // InternalCompleteOCL.g:1586:2: (iv_ruleParameterCS= ruleParameterCS EOF )
            // InternalCompleteOCL.g:1587:2: iv_ruleParameterCS= ruleParameterCS EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getParameterCSRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleParameterCS=ruleParameterCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleParameterCS;
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
    // $ANTLR end "entryRuleParameterCS"


    // $ANTLR start "ruleParameterCS"
    // InternalCompleteOCL.g:1594:1: ruleParameterCS returns [EObject current=null] : ( ( ( (lv_name_0_0= ruleUnrestrictedName ) ) otherlv_1= ':' )? ( (lv_ownedType_2_0= ruleTypeExpCS ) ) ) ;
    public final EObject ruleParameterCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_name_0_0 = null;

        EObject lv_ownedType_2_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:1597:28: ( ( ( ( (lv_name_0_0= ruleUnrestrictedName ) ) otherlv_1= ':' )? ( (lv_ownedType_2_0= ruleTypeExpCS ) ) ) )
            // InternalCompleteOCL.g:1598:1: ( ( ( (lv_name_0_0= ruleUnrestrictedName ) ) otherlv_1= ':' )? ( (lv_ownedType_2_0= ruleTypeExpCS ) ) )
            {
            // InternalCompleteOCL.g:1598:1: ( ( ( (lv_name_0_0= ruleUnrestrictedName ) ) otherlv_1= ':' )? ( (lv_ownedType_2_0= ruleTypeExpCS ) ) )
            // InternalCompleteOCL.g:1598:2: ( ( (lv_name_0_0= ruleUnrestrictedName ) ) otherlv_1= ':' )? ( (lv_ownedType_2_0= ruleTypeExpCS ) )
            {
            // InternalCompleteOCL.g:1598:2: ( ( (lv_name_0_0= ruleUnrestrictedName ) ) otherlv_1= ':' )?
            int alt43=2;
            switch ( input.LA(1) ) {
                case RULE_SIMPLE_ID:
                    {
                    int LA43_1 = input.LA(2);

                    if ( (LA43_1==24) ) {
                        alt43=1;
                    }
                    }
                    break;
                case RULE_ESCAPED_ID:
                    {
                    int LA43_2 = input.LA(2);

                    if ( (LA43_2==24) ) {
                        alt43=1;
                    }
                    }
                    break;
                case 31:
                    {
                    int LA43_3 = input.LA(2);

                    if ( (LA43_3==24) ) {
                        alt43=1;
                    }
                    }
                    break;
                case 32:
                    {
                    int LA43_4 = input.LA(2);

                    if ( (LA43_4==24) ) {
                        alt43=1;
                    }
                    }
                    break;
                case 33:
                    {
                    int LA43_5 = input.LA(2);

                    if ( (LA43_5==24) ) {
                        alt43=1;
                    }
                    }
                    break;
            }

            switch (alt43) {
                case 1 :
                    // InternalCompleteOCL.g:1598:3: ( (lv_name_0_0= ruleUnrestrictedName ) ) otherlv_1= ':'
                    {
                    // InternalCompleteOCL.g:1598:3: ( (lv_name_0_0= ruleUnrestrictedName ) )
                    // InternalCompleteOCL.g:1599:1: (lv_name_0_0= ruleUnrestrictedName )
                    {
                    // InternalCompleteOCL.g:1599:1: (lv_name_0_0= ruleUnrestrictedName )
                    // InternalCompleteOCL.g:1600:3: lv_name_0_0= ruleUnrestrictedName
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getParameterCSAccess().getNameUnrestrictedNameParserRuleCall_0_0_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_14);
                    lv_name_0_0=ruleUnrestrictedName();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getParameterCSRule());
                      	        }
                             		set(
                             			current,
                             			"name",
                              		lv_name_0_0,
                              		"org.eclipse.ocl.xtext.completeocl.CompleteOCL.UnrestrictedName");
                      	        afterParserOrEnumRuleCall();

                    }

                    }


                    }

                    otherlv_1=(Token)match(input,24,FollowSets000.FOLLOW_25); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_1, grammarAccess.getParameterCSAccess().getColonKeyword_0_1());

                    }

                    }
                    break;

            }

            // InternalCompleteOCL.g:1620:3: ( (lv_ownedType_2_0= ruleTypeExpCS ) )
            // InternalCompleteOCL.g:1621:1: (lv_ownedType_2_0= ruleTypeExpCS )
            {
            // InternalCompleteOCL.g:1621:1: (lv_ownedType_2_0= ruleTypeExpCS )
            // InternalCompleteOCL.g:1622:3: lv_ownedType_2_0= ruleTypeExpCS
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getParameterCSAccess().getOwnedTypeTypeExpCSParserRuleCall_1_0());

            }
            pushFollow(FollowSets000.FOLLOW_2);
            lv_ownedType_2_0=ruleTypeExpCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getParameterCSRule());
              	        }
                     		set(
                     			current,
                     			"ownedType",
                      		lv_ownedType_2_0,
                      		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.TypeExpCS");
              	        afterParserOrEnumRuleCall();

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
    // $ANTLR end "ruleParameterCS"


    // $ANTLR start "entryRulePropertyContextDeclCS"
    // InternalCompleteOCL.g:1646:1: entryRulePropertyContextDeclCS returns [EObject current=null] : iv_rulePropertyContextDeclCS= rulePropertyContextDeclCS EOF ;
    public final EObject entryRulePropertyContextDeclCS() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePropertyContextDeclCS = null;


        try {
            // InternalCompleteOCL.g:1647:2: (iv_rulePropertyContextDeclCS= rulePropertyContextDeclCS EOF )
            // InternalCompleteOCL.g:1648:2: iv_rulePropertyContextDeclCS= rulePropertyContextDeclCS EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getPropertyContextDeclCSRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_rulePropertyContextDeclCS=rulePropertyContextDeclCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_rulePropertyContextDeclCS;
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
    // $ANTLR end "entryRulePropertyContextDeclCS"


    // $ANTLR start "rulePropertyContextDeclCS"
    // InternalCompleteOCL.g:1655:1: rulePropertyContextDeclCS returns [EObject current=null] : ( ( (lv_ownedAnnotations_0_0= ruleCommentCS ) )? otherlv_1= 'context' ( (lv_ownedPathName_2_0= rulePathNameCS ) ) otherlv_3= ':' ( (lv_ownedType_4_0= ruleTypeExpCS ) ) ( ( (lv_ownedDefaultExpressions_5_0= ruleDeriveSpecificationCS ) ) | ( (lv_ownedDefaultExpressions_6_0= ruleInitSpecificationCS ) ) )* ) ;
    public final EObject rulePropertyContextDeclCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_ownedAnnotations_0_0 = null;

        EObject lv_ownedPathName_2_0 = null;

        EObject lv_ownedType_4_0 = null;

        EObject lv_ownedDefaultExpressions_5_0 = null;

        EObject lv_ownedDefaultExpressions_6_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:1658:28: ( ( ( (lv_ownedAnnotations_0_0= ruleCommentCS ) )? otherlv_1= 'context' ( (lv_ownedPathName_2_0= rulePathNameCS ) ) otherlv_3= ':' ( (lv_ownedType_4_0= ruleTypeExpCS ) ) ( ( (lv_ownedDefaultExpressions_5_0= ruleDeriveSpecificationCS ) ) | ( (lv_ownedDefaultExpressions_6_0= ruleInitSpecificationCS ) ) )* ) )
            // InternalCompleteOCL.g:1659:1: ( ( (lv_ownedAnnotations_0_0= ruleCommentCS ) )? otherlv_1= 'context' ( (lv_ownedPathName_2_0= rulePathNameCS ) ) otherlv_3= ':' ( (lv_ownedType_4_0= ruleTypeExpCS ) ) ( ( (lv_ownedDefaultExpressions_5_0= ruleDeriveSpecificationCS ) ) | ( (lv_ownedDefaultExpressions_6_0= ruleInitSpecificationCS ) ) )* )
            {
            // InternalCompleteOCL.g:1659:1: ( ( (lv_ownedAnnotations_0_0= ruleCommentCS ) )? otherlv_1= 'context' ( (lv_ownedPathName_2_0= rulePathNameCS ) ) otherlv_3= ':' ( (lv_ownedType_4_0= ruleTypeExpCS ) ) ( ( (lv_ownedDefaultExpressions_5_0= ruleDeriveSpecificationCS ) ) | ( (lv_ownedDefaultExpressions_6_0= ruleInitSpecificationCS ) ) )* )
            // InternalCompleteOCL.g:1659:2: ( (lv_ownedAnnotations_0_0= ruleCommentCS ) )? otherlv_1= 'context' ( (lv_ownedPathName_2_0= rulePathNameCS ) ) otherlv_3= ':' ( (lv_ownedType_4_0= ruleTypeExpCS ) ) ( ( (lv_ownedDefaultExpressions_5_0= ruleDeriveSpecificationCS ) ) | ( (lv_ownedDefaultExpressions_6_0= ruleInitSpecificationCS ) ) )*
            {
            // InternalCompleteOCL.g:1659:2: ( (lv_ownedAnnotations_0_0= ruleCommentCS ) )?
            int alt44=2;
            int LA44_0 = input.LA(1);

            if ( (LA44_0==RULE_ML_DOCUMENTATION) ) {
                alt44=1;
            }
            switch (alt44) {
                case 1 :
                    // InternalCompleteOCL.g:1660:1: (lv_ownedAnnotations_0_0= ruleCommentCS )
                    {
                    // InternalCompleteOCL.g:1660:1: (lv_ownedAnnotations_0_0= ruleCommentCS )
                    // InternalCompleteOCL.g:1661:3: lv_ownedAnnotations_0_0= ruleCommentCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getPropertyContextDeclCSAccess().getOwnedAnnotationsCommentCSParserRuleCall_0_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_5);
                    lv_ownedAnnotations_0_0=ruleCommentCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getPropertyContextDeclCSRule());
                      	        }
                             		add(
                             			current,
                             			"ownedAnnotations",
                              		lv_ownedAnnotations_0_0,
                              		"org.eclipse.ocl.xtext.base.Base.CommentCS");
                      	        afterParserOrEnumRuleCall();

                    }

                    }


                    }
                    break;

            }

            otherlv_1=(Token)match(input,20,FollowSets000.FOLLOW_6); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getPropertyContextDeclCSAccess().getContextKeyword_1());

            }
            // InternalCompleteOCL.g:1681:1: ( (lv_ownedPathName_2_0= rulePathNameCS ) )
            // InternalCompleteOCL.g:1682:1: (lv_ownedPathName_2_0= rulePathNameCS )
            {
            // InternalCompleteOCL.g:1682:1: (lv_ownedPathName_2_0= rulePathNameCS )
            // InternalCompleteOCL.g:1683:3: lv_ownedPathName_2_0= rulePathNameCS
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getPropertyContextDeclCSAccess().getOwnedPathNamePathNameCSParserRuleCall_2_0());

            }
            pushFollow(FollowSets000.FOLLOW_14);
            lv_ownedPathName_2_0=rulePathNameCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getPropertyContextDeclCSRule());
              	        }
                     		set(
                     			current,
                     			"ownedPathName",
                      		lv_ownedPathName_2_0,
                      		"org.eclipse.ocl.xtext.base.Base.PathNameCS");
              	        afterParserOrEnumRuleCall();

            }

            }


            }

            otherlv_3=(Token)match(input,24,FollowSets000.FOLLOW_25); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_3, grammarAccess.getPropertyContextDeclCSAccess().getColonKeyword_3());

            }
            // InternalCompleteOCL.g:1703:1: ( (lv_ownedType_4_0= ruleTypeExpCS ) )
            // InternalCompleteOCL.g:1704:1: (lv_ownedType_4_0= ruleTypeExpCS )
            {
            // InternalCompleteOCL.g:1704:1: (lv_ownedType_4_0= ruleTypeExpCS )
            // InternalCompleteOCL.g:1705:3: lv_ownedType_4_0= ruleTypeExpCS
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getPropertyContextDeclCSAccess().getOwnedTypeTypeExpCSParserRuleCall_4_0());

            }
            pushFollow(FollowSets000.FOLLOW_35);
            lv_ownedType_4_0=ruleTypeExpCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getPropertyContextDeclCSRule());
              	        }
                     		set(
                     			current,
                     			"ownedType",
                      		lv_ownedType_4_0,
                      		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.TypeExpCS");
              	        afterParserOrEnumRuleCall();

            }

            }


            }

            // InternalCompleteOCL.g:1721:2: ( ( (lv_ownedDefaultExpressions_5_0= ruleDeriveSpecificationCS ) ) | ( (lv_ownedDefaultExpressions_6_0= ruleInitSpecificationCS ) ) )*
            loop45:
            do {
                int alt45=3;
                switch ( input.LA(1) ) {
                case RULE_ML_DOCUMENTATION:
                    {
                    int LA45_2 = input.LA(2);

                    if ( (LA45_2==38) ) {
                        alt45=1;
                    }
                    else if ( (LA45_2==39) ) {
                        alt45=2;
                    }


                    }
                    break;
                case 38:
                    {
                    alt45=1;
                    }
                    break;
                case 39:
                    {
                    alt45=2;
                    }
                    break;

                }

                switch (alt45) {
            	case 1 :
            	    // InternalCompleteOCL.g:1721:3: ( (lv_ownedDefaultExpressions_5_0= ruleDeriveSpecificationCS ) )
            	    {
            	    // InternalCompleteOCL.g:1721:3: ( (lv_ownedDefaultExpressions_5_0= ruleDeriveSpecificationCS ) )
            	    // InternalCompleteOCL.g:1722:1: (lv_ownedDefaultExpressions_5_0= ruleDeriveSpecificationCS )
            	    {
            	    // InternalCompleteOCL.g:1722:1: (lv_ownedDefaultExpressions_5_0= ruleDeriveSpecificationCS )
            	    // InternalCompleteOCL.g:1723:3: lv_ownedDefaultExpressions_5_0= ruleDeriveSpecificationCS
            	    {
            	    if ( state.backtracking==0 ) {

            	      	        newCompositeNode(grammarAccess.getPropertyContextDeclCSAccess().getOwnedDefaultExpressionsDeriveSpecificationCSParserRuleCall_5_0_0());

            	    }
            	    pushFollow(FollowSets000.FOLLOW_35);
            	    lv_ownedDefaultExpressions_5_0=ruleDeriveSpecificationCS();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getPropertyContextDeclCSRule());
            	      	        }
            	             		add(
            	             			current,
            	             			"ownedDefaultExpressions",
            	              		lv_ownedDefaultExpressions_5_0,
            	              		"org.eclipse.ocl.xtext.completeocl.CompleteOCL.DeriveSpecificationCS");
            	      	        afterParserOrEnumRuleCall();

            	    }

            	    }


            	    }


            	    }
            	    break;
            	case 2 :
            	    // InternalCompleteOCL.g:1740:6: ( (lv_ownedDefaultExpressions_6_0= ruleInitSpecificationCS ) )
            	    {
            	    // InternalCompleteOCL.g:1740:6: ( (lv_ownedDefaultExpressions_6_0= ruleInitSpecificationCS ) )
            	    // InternalCompleteOCL.g:1741:1: (lv_ownedDefaultExpressions_6_0= ruleInitSpecificationCS )
            	    {
            	    // InternalCompleteOCL.g:1741:1: (lv_ownedDefaultExpressions_6_0= ruleInitSpecificationCS )
            	    // InternalCompleteOCL.g:1742:3: lv_ownedDefaultExpressions_6_0= ruleInitSpecificationCS
            	    {
            	    if ( state.backtracking==0 ) {

            	      	        newCompositeNode(grammarAccess.getPropertyContextDeclCSAccess().getOwnedDefaultExpressionsInitSpecificationCSParserRuleCall_5_1_0());

            	    }
            	    pushFollow(FollowSets000.FOLLOW_35);
            	    lv_ownedDefaultExpressions_6_0=ruleInitSpecificationCS();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getPropertyContextDeclCSRule());
            	      	        }
            	             		add(
            	             			current,
            	             			"ownedDefaultExpressions",
            	              		lv_ownedDefaultExpressions_6_0,
            	              		"org.eclipse.ocl.xtext.completeocl.CompleteOCL.InitSpecificationCS");
            	      	        afterParserOrEnumRuleCall();

            	    }

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop45;
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
    // $ANTLR end "rulePropertyContextDeclCS"


    // $ANTLR start "entryRuleSpecificationCS"
    // InternalCompleteOCL.g:1766:1: entryRuleSpecificationCS returns [EObject current=null] : iv_ruleSpecificationCS= ruleSpecificationCS EOF ;
    public final EObject entryRuleSpecificationCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSpecificationCS = null;


        try {
            // InternalCompleteOCL.g:1767:2: (iv_ruleSpecificationCS= ruleSpecificationCS EOF )
            // InternalCompleteOCL.g:1768:2: iv_ruleSpecificationCS= ruleSpecificationCS EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getSpecificationCSRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleSpecificationCS=ruleSpecificationCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleSpecificationCS;
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
    // $ANTLR end "entryRuleSpecificationCS"


    // $ANTLR start "ruleSpecificationCS"
    // InternalCompleteOCL.g:1775:1: ruleSpecificationCS returns [EObject current=null] : ( ( (lv_ownedExpression_0_0= ruleExpCS ) ) | ( (lv_exprString_1_0= RULE_UNQUOTED_STRING ) ) ) ;
    public final EObject ruleSpecificationCS() throws RecognitionException {
        EObject current = null;

        Token lv_exprString_1_0=null;
        EObject lv_ownedExpression_0_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:1778:28: ( ( ( (lv_ownedExpression_0_0= ruleExpCS ) ) | ( (lv_exprString_1_0= RULE_UNQUOTED_STRING ) ) ) )
            // InternalCompleteOCL.g:1779:1: ( ( (lv_ownedExpression_0_0= ruleExpCS ) ) | ( (lv_exprString_1_0= RULE_UNQUOTED_STRING ) ) )
            {
            // InternalCompleteOCL.g:1779:1: ( ( (lv_ownedExpression_0_0= ruleExpCS ) ) | ( (lv_exprString_1_0= RULE_UNQUOTED_STRING ) ) )
            int alt46=2;
            int LA46_0 = input.LA(1);

            if ( ((LA46_0>=RULE_SIMPLE_ID && LA46_0<=RULE_SINGLE_QUOTED_STRING)||LA46_0==22||(LA46_0>=31 && LA46_0<=33)||(LA46_0>=43 && LA46_0<=56)||(LA46_0>=74 && LA46_0<=75)||(LA46_0>=77 && LA46_0<=81)||LA46_0==86||(LA46_0>=88 && LA46_0<=91)||LA46_0==98||(LA46_0>=103 && LA46_0<=104)) ) {
                alt46=1;
            }
            else if ( (LA46_0==RULE_UNQUOTED_STRING) ) {
                alt46=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 46, 0, input);

                throw nvae;
            }
            switch (alt46) {
                case 1 :
                    // InternalCompleteOCL.g:1779:2: ( (lv_ownedExpression_0_0= ruleExpCS ) )
                    {
                    // InternalCompleteOCL.g:1779:2: ( (lv_ownedExpression_0_0= ruleExpCS ) )
                    // InternalCompleteOCL.g:1780:1: (lv_ownedExpression_0_0= ruleExpCS )
                    {
                    // InternalCompleteOCL.g:1780:1: (lv_ownedExpression_0_0= ruleExpCS )
                    // InternalCompleteOCL.g:1781:3: lv_ownedExpression_0_0= ruleExpCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getSpecificationCSAccess().getOwnedExpressionExpCSParserRuleCall_0_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    lv_ownedExpression_0_0=ruleExpCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getSpecificationCSRule());
                      	        }
                             		set(
                             			current,
                             			"ownedExpression",
                              		lv_ownedExpression_0_0,
                              		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.ExpCS");
                      	        afterParserOrEnumRuleCall();

                    }

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalCompleteOCL.g:1798:6: ( (lv_exprString_1_0= RULE_UNQUOTED_STRING ) )
                    {
                    // InternalCompleteOCL.g:1798:6: ( (lv_exprString_1_0= RULE_UNQUOTED_STRING ) )
                    // InternalCompleteOCL.g:1799:1: (lv_exprString_1_0= RULE_UNQUOTED_STRING )
                    {
                    // InternalCompleteOCL.g:1799:1: (lv_exprString_1_0= RULE_UNQUOTED_STRING )
                    // InternalCompleteOCL.g:1800:3: lv_exprString_1_0= RULE_UNQUOTED_STRING
                    {
                    lv_exprString_1_0=(Token)match(input,RULE_UNQUOTED_STRING,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			newLeafNode(lv_exprString_1_0, grammarAccess.getSpecificationCSAccess().getExprStringUNQUOTED_STRINGTerminalRuleCall_1_0());

                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getSpecificationCSRule());
                      	        }
                             		setWithLastConsumed(
                             			current,
                             			"exprString",
                              		lv_exprString_1_0,
                              		"org.eclipse.ocl.xtext.completeocl.CompleteOCL.UNQUOTED_STRING");

                    }

                    }


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
    // $ANTLR end "ruleSpecificationCS"


    // $ANTLR start "entryRuleBodySpecificationCS"
    // InternalCompleteOCL.g:1824:1: entryRuleBodySpecificationCS returns [EObject current=null] : iv_ruleBodySpecificationCS= ruleBodySpecificationCS EOF ;
    public final EObject entryRuleBodySpecificationCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleBodySpecificationCS = null;


        try {
            // InternalCompleteOCL.g:1825:2: (iv_ruleBodySpecificationCS= ruleBodySpecificationCS EOF )
            // InternalCompleteOCL.g:1826:2: iv_ruleBodySpecificationCS= ruleBodySpecificationCS EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getBodySpecificationCSRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleBodySpecificationCS=ruleBodySpecificationCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleBodySpecificationCS;
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
    // $ANTLR end "entryRuleBodySpecificationCS"


    // $ANTLR start "ruleBodySpecificationCS"
    // InternalCompleteOCL.g:1833:1: ruleBodySpecificationCS returns [EObject current=null] : ( ( (lv_ownedAnnotations_0_0= ruleCommentCS ) )? otherlv_1= 'body' ( ruleUnrestrictedName )? otherlv_3= ':' ( ( (lv_ownedExpression_4_0= ruleExpCS ) ) | ( (lv_exprString_5_0= RULE_UNQUOTED_STRING ) ) ) ) ;
    public final EObject ruleBodySpecificationCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token lv_exprString_5_0=null;
        EObject lv_ownedAnnotations_0_0 = null;

        EObject lv_ownedExpression_4_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:1836:28: ( ( ( (lv_ownedAnnotations_0_0= ruleCommentCS ) )? otherlv_1= 'body' ( ruleUnrestrictedName )? otherlv_3= ':' ( ( (lv_ownedExpression_4_0= ruleExpCS ) ) | ( (lv_exprString_5_0= RULE_UNQUOTED_STRING ) ) ) ) )
            // InternalCompleteOCL.g:1837:1: ( ( (lv_ownedAnnotations_0_0= ruleCommentCS ) )? otherlv_1= 'body' ( ruleUnrestrictedName )? otherlv_3= ':' ( ( (lv_ownedExpression_4_0= ruleExpCS ) ) | ( (lv_exprString_5_0= RULE_UNQUOTED_STRING ) ) ) )
            {
            // InternalCompleteOCL.g:1837:1: ( ( (lv_ownedAnnotations_0_0= ruleCommentCS ) )? otherlv_1= 'body' ( ruleUnrestrictedName )? otherlv_3= ':' ( ( (lv_ownedExpression_4_0= ruleExpCS ) ) | ( (lv_exprString_5_0= RULE_UNQUOTED_STRING ) ) ) )
            // InternalCompleteOCL.g:1837:2: ( (lv_ownedAnnotations_0_0= ruleCommentCS ) )? otherlv_1= 'body' ( ruleUnrestrictedName )? otherlv_3= ':' ( ( (lv_ownedExpression_4_0= ruleExpCS ) ) | ( (lv_exprString_5_0= RULE_UNQUOTED_STRING ) ) )
            {
            // InternalCompleteOCL.g:1837:2: ( (lv_ownedAnnotations_0_0= ruleCommentCS ) )?
            int alt47=2;
            int LA47_0 = input.LA(1);

            if ( (LA47_0==RULE_ML_DOCUMENTATION) ) {
                alt47=1;
            }
            switch (alt47) {
                case 1 :
                    // InternalCompleteOCL.g:1838:1: (lv_ownedAnnotations_0_0= ruleCommentCS )
                    {
                    // InternalCompleteOCL.g:1838:1: (lv_ownedAnnotations_0_0= ruleCommentCS )
                    // InternalCompleteOCL.g:1839:3: lv_ownedAnnotations_0_0= ruleCommentCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getBodySpecificationCSAccess().getOwnedAnnotationsCommentCSParserRuleCall_0_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_36);
                    lv_ownedAnnotations_0_0=ruleCommentCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getBodySpecificationCSRule());
                      	        }
                             		add(
                             			current,
                             			"ownedAnnotations",
                              		lv_ownedAnnotations_0_0,
                              		"org.eclipse.ocl.xtext.base.Base.CommentCS");
                      	        afterParserOrEnumRuleCall();

                    }

                    }


                    }
                    break;

            }

            otherlv_1=(Token)match(input,37,FollowSets000.FOLLOW_10); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getBodySpecificationCSAccess().getBodyKeyword_1());

            }
            // InternalCompleteOCL.g:1859:1: ( ruleUnrestrictedName )?
            int alt48=2;
            int LA48_0 = input.LA(1);

            if ( ((LA48_0>=RULE_SIMPLE_ID && LA48_0<=RULE_ESCAPED_ID)||(LA48_0>=31 && LA48_0<=33)) ) {
                alt48=1;
            }
            switch (alt48) {
                case 1 :
                    // InternalCompleteOCL.g:1860:2: ruleUnrestrictedName
                    {
                    if ( state.backtracking==0 ) {

                      	  /* */

                    }
                    if ( state.backtracking==0 ) {

                              newCompositeNode(grammarAccess.getBodySpecificationCSAccess().getUnrestrictedNameParserRuleCall_2());

                    }
                    pushFollow(FollowSets000.FOLLOW_14);
                    ruleUnrestrictedName();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              afterParserOrEnumRuleCall();

                    }

                    }
                    break;

            }

            otherlv_3=(Token)match(input,24,FollowSets000.FOLLOW_12); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_3, grammarAccess.getBodySpecificationCSAccess().getColonKeyword_3());

            }
            // InternalCompleteOCL.g:1874:1: ( ( (lv_ownedExpression_4_0= ruleExpCS ) ) | ( (lv_exprString_5_0= RULE_UNQUOTED_STRING ) ) )
            int alt49=2;
            int LA49_0 = input.LA(1);

            if ( ((LA49_0>=RULE_SIMPLE_ID && LA49_0<=RULE_SINGLE_QUOTED_STRING)||LA49_0==22||(LA49_0>=31 && LA49_0<=33)||(LA49_0>=43 && LA49_0<=56)||(LA49_0>=74 && LA49_0<=75)||(LA49_0>=77 && LA49_0<=81)||LA49_0==86||(LA49_0>=88 && LA49_0<=91)||LA49_0==98||(LA49_0>=103 && LA49_0<=104)) ) {
                alt49=1;
            }
            else if ( (LA49_0==RULE_UNQUOTED_STRING) ) {
                alt49=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 49, 0, input);

                throw nvae;
            }
            switch (alt49) {
                case 1 :
                    // InternalCompleteOCL.g:1874:2: ( (lv_ownedExpression_4_0= ruleExpCS ) )
                    {
                    // InternalCompleteOCL.g:1874:2: ( (lv_ownedExpression_4_0= ruleExpCS ) )
                    // InternalCompleteOCL.g:1875:1: (lv_ownedExpression_4_0= ruleExpCS )
                    {
                    // InternalCompleteOCL.g:1875:1: (lv_ownedExpression_4_0= ruleExpCS )
                    // InternalCompleteOCL.g:1876:3: lv_ownedExpression_4_0= ruleExpCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getBodySpecificationCSAccess().getOwnedExpressionExpCSParserRuleCall_4_0_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    lv_ownedExpression_4_0=ruleExpCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getBodySpecificationCSRule());
                      	        }
                             		set(
                             			current,
                             			"ownedExpression",
                              		lv_ownedExpression_4_0,
                              		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.ExpCS");
                      	        afterParserOrEnumRuleCall();

                    }

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalCompleteOCL.g:1893:6: ( (lv_exprString_5_0= RULE_UNQUOTED_STRING ) )
                    {
                    // InternalCompleteOCL.g:1893:6: ( (lv_exprString_5_0= RULE_UNQUOTED_STRING ) )
                    // InternalCompleteOCL.g:1894:1: (lv_exprString_5_0= RULE_UNQUOTED_STRING )
                    {
                    // InternalCompleteOCL.g:1894:1: (lv_exprString_5_0= RULE_UNQUOTED_STRING )
                    // InternalCompleteOCL.g:1895:3: lv_exprString_5_0= RULE_UNQUOTED_STRING
                    {
                    lv_exprString_5_0=(Token)match(input,RULE_UNQUOTED_STRING,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			newLeafNode(lv_exprString_5_0, grammarAccess.getBodySpecificationCSAccess().getExprStringUNQUOTED_STRINGTerminalRuleCall_4_1_0());

                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getBodySpecificationCSRule());
                      	        }
                             		setWithLastConsumed(
                             			current,
                             			"exprString",
                              		lv_exprString_5_0,
                              		"org.eclipse.ocl.xtext.completeocl.CompleteOCL.UNQUOTED_STRING");

                    }

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
    // $ANTLR end "ruleBodySpecificationCS"


    // $ANTLR start "entryRuleDeriveSpecificationCS"
    // InternalCompleteOCL.g:1919:1: entryRuleDeriveSpecificationCS returns [EObject current=null] : iv_ruleDeriveSpecificationCS= ruleDeriveSpecificationCS EOF ;
    public final EObject entryRuleDeriveSpecificationCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDeriveSpecificationCS = null;


        try {
            // InternalCompleteOCL.g:1920:2: (iv_ruleDeriveSpecificationCS= ruleDeriveSpecificationCS EOF )
            // InternalCompleteOCL.g:1921:2: iv_ruleDeriveSpecificationCS= ruleDeriveSpecificationCS EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getDeriveSpecificationCSRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleDeriveSpecificationCS=ruleDeriveSpecificationCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleDeriveSpecificationCS;
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
    // $ANTLR end "entryRuleDeriveSpecificationCS"


    // $ANTLR start "ruleDeriveSpecificationCS"
    // InternalCompleteOCL.g:1928:1: ruleDeriveSpecificationCS returns [EObject current=null] : ( ( (lv_ownedAnnotations_0_0= ruleCommentCS ) )? otherlv_1= 'derive' ( ruleUnrestrictedName )? otherlv_3= ':' ( ( (lv_ownedExpression_4_0= ruleExpCS ) ) | ( (lv_exprString_5_0= RULE_UNQUOTED_STRING ) ) ) ) ;
    public final EObject ruleDeriveSpecificationCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token lv_exprString_5_0=null;
        EObject lv_ownedAnnotations_0_0 = null;

        EObject lv_ownedExpression_4_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:1931:28: ( ( ( (lv_ownedAnnotations_0_0= ruleCommentCS ) )? otherlv_1= 'derive' ( ruleUnrestrictedName )? otherlv_3= ':' ( ( (lv_ownedExpression_4_0= ruleExpCS ) ) | ( (lv_exprString_5_0= RULE_UNQUOTED_STRING ) ) ) ) )
            // InternalCompleteOCL.g:1932:1: ( ( (lv_ownedAnnotations_0_0= ruleCommentCS ) )? otherlv_1= 'derive' ( ruleUnrestrictedName )? otherlv_3= ':' ( ( (lv_ownedExpression_4_0= ruleExpCS ) ) | ( (lv_exprString_5_0= RULE_UNQUOTED_STRING ) ) ) )
            {
            // InternalCompleteOCL.g:1932:1: ( ( (lv_ownedAnnotations_0_0= ruleCommentCS ) )? otherlv_1= 'derive' ( ruleUnrestrictedName )? otherlv_3= ':' ( ( (lv_ownedExpression_4_0= ruleExpCS ) ) | ( (lv_exprString_5_0= RULE_UNQUOTED_STRING ) ) ) )
            // InternalCompleteOCL.g:1932:2: ( (lv_ownedAnnotations_0_0= ruleCommentCS ) )? otherlv_1= 'derive' ( ruleUnrestrictedName )? otherlv_3= ':' ( ( (lv_ownedExpression_4_0= ruleExpCS ) ) | ( (lv_exprString_5_0= RULE_UNQUOTED_STRING ) ) )
            {
            // InternalCompleteOCL.g:1932:2: ( (lv_ownedAnnotations_0_0= ruleCommentCS ) )?
            int alt50=2;
            int LA50_0 = input.LA(1);

            if ( (LA50_0==RULE_ML_DOCUMENTATION) ) {
                alt50=1;
            }
            switch (alt50) {
                case 1 :
                    // InternalCompleteOCL.g:1933:1: (lv_ownedAnnotations_0_0= ruleCommentCS )
                    {
                    // InternalCompleteOCL.g:1933:1: (lv_ownedAnnotations_0_0= ruleCommentCS )
                    // InternalCompleteOCL.g:1934:3: lv_ownedAnnotations_0_0= ruleCommentCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getDeriveSpecificationCSAccess().getOwnedAnnotationsCommentCSParserRuleCall_0_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_37);
                    lv_ownedAnnotations_0_0=ruleCommentCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getDeriveSpecificationCSRule());
                      	        }
                             		add(
                             			current,
                             			"ownedAnnotations",
                              		lv_ownedAnnotations_0_0,
                              		"org.eclipse.ocl.xtext.base.Base.CommentCS");
                      	        afterParserOrEnumRuleCall();

                    }

                    }


                    }
                    break;

            }

            otherlv_1=(Token)match(input,38,FollowSets000.FOLLOW_10); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getDeriveSpecificationCSAccess().getDeriveKeyword_1());

            }
            // InternalCompleteOCL.g:1954:1: ( ruleUnrestrictedName )?
            int alt51=2;
            int LA51_0 = input.LA(1);

            if ( ((LA51_0>=RULE_SIMPLE_ID && LA51_0<=RULE_ESCAPED_ID)||(LA51_0>=31 && LA51_0<=33)) ) {
                alt51=1;
            }
            switch (alt51) {
                case 1 :
                    // InternalCompleteOCL.g:1955:2: ruleUnrestrictedName
                    {
                    if ( state.backtracking==0 ) {

                      	  /* */

                    }
                    if ( state.backtracking==0 ) {

                              newCompositeNode(grammarAccess.getDeriveSpecificationCSAccess().getUnrestrictedNameParserRuleCall_2());

                    }
                    pushFollow(FollowSets000.FOLLOW_14);
                    ruleUnrestrictedName();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              afterParserOrEnumRuleCall();

                    }

                    }
                    break;

            }

            otherlv_3=(Token)match(input,24,FollowSets000.FOLLOW_12); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_3, grammarAccess.getDeriveSpecificationCSAccess().getColonKeyword_3());

            }
            // InternalCompleteOCL.g:1969:1: ( ( (lv_ownedExpression_4_0= ruleExpCS ) ) | ( (lv_exprString_5_0= RULE_UNQUOTED_STRING ) ) )
            int alt52=2;
            int LA52_0 = input.LA(1);

            if ( ((LA52_0>=RULE_SIMPLE_ID && LA52_0<=RULE_SINGLE_QUOTED_STRING)||LA52_0==22||(LA52_0>=31 && LA52_0<=33)||(LA52_0>=43 && LA52_0<=56)||(LA52_0>=74 && LA52_0<=75)||(LA52_0>=77 && LA52_0<=81)||LA52_0==86||(LA52_0>=88 && LA52_0<=91)||LA52_0==98||(LA52_0>=103 && LA52_0<=104)) ) {
                alt52=1;
            }
            else if ( (LA52_0==RULE_UNQUOTED_STRING) ) {
                alt52=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 52, 0, input);

                throw nvae;
            }
            switch (alt52) {
                case 1 :
                    // InternalCompleteOCL.g:1969:2: ( (lv_ownedExpression_4_0= ruleExpCS ) )
                    {
                    // InternalCompleteOCL.g:1969:2: ( (lv_ownedExpression_4_0= ruleExpCS ) )
                    // InternalCompleteOCL.g:1970:1: (lv_ownedExpression_4_0= ruleExpCS )
                    {
                    // InternalCompleteOCL.g:1970:1: (lv_ownedExpression_4_0= ruleExpCS )
                    // InternalCompleteOCL.g:1971:3: lv_ownedExpression_4_0= ruleExpCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getDeriveSpecificationCSAccess().getOwnedExpressionExpCSParserRuleCall_4_0_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    lv_ownedExpression_4_0=ruleExpCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getDeriveSpecificationCSRule());
                      	        }
                             		set(
                             			current,
                             			"ownedExpression",
                              		lv_ownedExpression_4_0,
                              		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.ExpCS");
                      	        afterParserOrEnumRuleCall();

                    }

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalCompleteOCL.g:1988:6: ( (lv_exprString_5_0= RULE_UNQUOTED_STRING ) )
                    {
                    // InternalCompleteOCL.g:1988:6: ( (lv_exprString_5_0= RULE_UNQUOTED_STRING ) )
                    // InternalCompleteOCL.g:1989:1: (lv_exprString_5_0= RULE_UNQUOTED_STRING )
                    {
                    // InternalCompleteOCL.g:1989:1: (lv_exprString_5_0= RULE_UNQUOTED_STRING )
                    // InternalCompleteOCL.g:1990:3: lv_exprString_5_0= RULE_UNQUOTED_STRING
                    {
                    lv_exprString_5_0=(Token)match(input,RULE_UNQUOTED_STRING,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			newLeafNode(lv_exprString_5_0, grammarAccess.getDeriveSpecificationCSAccess().getExprStringUNQUOTED_STRINGTerminalRuleCall_4_1_0());

                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getDeriveSpecificationCSRule());
                      	        }
                             		setWithLastConsumed(
                             			current,
                             			"exprString",
                              		lv_exprString_5_0,
                              		"org.eclipse.ocl.xtext.completeocl.CompleteOCL.UNQUOTED_STRING");

                    }

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
    // $ANTLR end "ruleDeriveSpecificationCS"


    // $ANTLR start "entryRuleInitSpecificationCS"
    // InternalCompleteOCL.g:2014:1: entryRuleInitSpecificationCS returns [EObject current=null] : iv_ruleInitSpecificationCS= ruleInitSpecificationCS EOF ;
    public final EObject entryRuleInitSpecificationCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleInitSpecificationCS = null;


        try {
            // InternalCompleteOCL.g:2015:2: (iv_ruleInitSpecificationCS= ruleInitSpecificationCS EOF )
            // InternalCompleteOCL.g:2016:2: iv_ruleInitSpecificationCS= ruleInitSpecificationCS EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getInitSpecificationCSRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleInitSpecificationCS=ruleInitSpecificationCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleInitSpecificationCS;
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
    // $ANTLR end "entryRuleInitSpecificationCS"


    // $ANTLR start "ruleInitSpecificationCS"
    // InternalCompleteOCL.g:2023:1: ruleInitSpecificationCS returns [EObject current=null] : ( ( (lv_ownedAnnotations_0_0= ruleCommentCS ) )? otherlv_1= 'init' ( ruleUnrestrictedName )? otherlv_3= ':' ( ( (lv_ownedExpression_4_0= ruleExpCS ) ) | ( (lv_exprString_5_0= RULE_UNQUOTED_STRING ) ) ) ) ;
    public final EObject ruleInitSpecificationCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token lv_exprString_5_0=null;
        EObject lv_ownedAnnotations_0_0 = null;

        EObject lv_ownedExpression_4_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:2026:28: ( ( ( (lv_ownedAnnotations_0_0= ruleCommentCS ) )? otherlv_1= 'init' ( ruleUnrestrictedName )? otherlv_3= ':' ( ( (lv_ownedExpression_4_0= ruleExpCS ) ) | ( (lv_exprString_5_0= RULE_UNQUOTED_STRING ) ) ) ) )
            // InternalCompleteOCL.g:2027:1: ( ( (lv_ownedAnnotations_0_0= ruleCommentCS ) )? otherlv_1= 'init' ( ruleUnrestrictedName )? otherlv_3= ':' ( ( (lv_ownedExpression_4_0= ruleExpCS ) ) | ( (lv_exprString_5_0= RULE_UNQUOTED_STRING ) ) ) )
            {
            // InternalCompleteOCL.g:2027:1: ( ( (lv_ownedAnnotations_0_0= ruleCommentCS ) )? otherlv_1= 'init' ( ruleUnrestrictedName )? otherlv_3= ':' ( ( (lv_ownedExpression_4_0= ruleExpCS ) ) | ( (lv_exprString_5_0= RULE_UNQUOTED_STRING ) ) ) )
            // InternalCompleteOCL.g:2027:2: ( (lv_ownedAnnotations_0_0= ruleCommentCS ) )? otherlv_1= 'init' ( ruleUnrestrictedName )? otherlv_3= ':' ( ( (lv_ownedExpression_4_0= ruleExpCS ) ) | ( (lv_exprString_5_0= RULE_UNQUOTED_STRING ) ) )
            {
            // InternalCompleteOCL.g:2027:2: ( (lv_ownedAnnotations_0_0= ruleCommentCS ) )?
            int alt53=2;
            int LA53_0 = input.LA(1);

            if ( (LA53_0==RULE_ML_DOCUMENTATION) ) {
                alt53=1;
            }
            switch (alt53) {
                case 1 :
                    // InternalCompleteOCL.g:2028:1: (lv_ownedAnnotations_0_0= ruleCommentCS )
                    {
                    // InternalCompleteOCL.g:2028:1: (lv_ownedAnnotations_0_0= ruleCommentCS )
                    // InternalCompleteOCL.g:2029:3: lv_ownedAnnotations_0_0= ruleCommentCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getInitSpecificationCSAccess().getOwnedAnnotationsCommentCSParserRuleCall_0_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_38);
                    lv_ownedAnnotations_0_0=ruleCommentCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getInitSpecificationCSRule());
                      	        }
                             		add(
                             			current,
                             			"ownedAnnotations",
                              		lv_ownedAnnotations_0_0,
                              		"org.eclipse.ocl.xtext.base.Base.CommentCS");
                      	        afterParserOrEnumRuleCall();

                    }

                    }


                    }
                    break;

            }

            otherlv_1=(Token)match(input,39,FollowSets000.FOLLOW_10); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getInitSpecificationCSAccess().getInitKeyword_1());

            }
            // InternalCompleteOCL.g:2049:1: ( ruleUnrestrictedName )?
            int alt54=2;
            int LA54_0 = input.LA(1);

            if ( ((LA54_0>=RULE_SIMPLE_ID && LA54_0<=RULE_ESCAPED_ID)||(LA54_0>=31 && LA54_0<=33)) ) {
                alt54=1;
            }
            switch (alt54) {
                case 1 :
                    // InternalCompleteOCL.g:2050:2: ruleUnrestrictedName
                    {
                    if ( state.backtracking==0 ) {

                      	  /* */

                    }
                    if ( state.backtracking==0 ) {

                              newCompositeNode(grammarAccess.getInitSpecificationCSAccess().getUnrestrictedNameParserRuleCall_2());

                    }
                    pushFollow(FollowSets000.FOLLOW_14);
                    ruleUnrestrictedName();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              afterParserOrEnumRuleCall();

                    }

                    }
                    break;

            }

            otherlv_3=(Token)match(input,24,FollowSets000.FOLLOW_12); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_3, grammarAccess.getInitSpecificationCSAccess().getColonKeyword_3());

            }
            // InternalCompleteOCL.g:2064:1: ( ( (lv_ownedExpression_4_0= ruleExpCS ) ) | ( (lv_exprString_5_0= RULE_UNQUOTED_STRING ) ) )
            int alt55=2;
            int LA55_0 = input.LA(1);

            if ( ((LA55_0>=RULE_SIMPLE_ID && LA55_0<=RULE_SINGLE_QUOTED_STRING)||LA55_0==22||(LA55_0>=31 && LA55_0<=33)||(LA55_0>=43 && LA55_0<=56)||(LA55_0>=74 && LA55_0<=75)||(LA55_0>=77 && LA55_0<=81)||LA55_0==86||(LA55_0>=88 && LA55_0<=91)||LA55_0==98||(LA55_0>=103 && LA55_0<=104)) ) {
                alt55=1;
            }
            else if ( (LA55_0==RULE_UNQUOTED_STRING) ) {
                alt55=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 55, 0, input);

                throw nvae;
            }
            switch (alt55) {
                case 1 :
                    // InternalCompleteOCL.g:2064:2: ( (lv_ownedExpression_4_0= ruleExpCS ) )
                    {
                    // InternalCompleteOCL.g:2064:2: ( (lv_ownedExpression_4_0= ruleExpCS ) )
                    // InternalCompleteOCL.g:2065:1: (lv_ownedExpression_4_0= ruleExpCS )
                    {
                    // InternalCompleteOCL.g:2065:1: (lv_ownedExpression_4_0= ruleExpCS )
                    // InternalCompleteOCL.g:2066:3: lv_ownedExpression_4_0= ruleExpCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getInitSpecificationCSAccess().getOwnedExpressionExpCSParserRuleCall_4_0_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    lv_ownedExpression_4_0=ruleExpCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getInitSpecificationCSRule());
                      	        }
                             		set(
                             			current,
                             			"ownedExpression",
                              		lv_ownedExpression_4_0,
                              		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.ExpCS");
                      	        afterParserOrEnumRuleCall();

                    }

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalCompleteOCL.g:2083:6: ( (lv_exprString_5_0= RULE_UNQUOTED_STRING ) )
                    {
                    // InternalCompleteOCL.g:2083:6: ( (lv_exprString_5_0= RULE_UNQUOTED_STRING ) )
                    // InternalCompleteOCL.g:2084:1: (lv_exprString_5_0= RULE_UNQUOTED_STRING )
                    {
                    // InternalCompleteOCL.g:2084:1: (lv_exprString_5_0= RULE_UNQUOTED_STRING )
                    // InternalCompleteOCL.g:2085:3: lv_exprString_5_0= RULE_UNQUOTED_STRING
                    {
                    lv_exprString_5_0=(Token)match(input,RULE_UNQUOTED_STRING,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			newLeafNode(lv_exprString_5_0, grammarAccess.getInitSpecificationCSAccess().getExprStringUNQUOTED_STRINGTerminalRuleCall_4_1_0());

                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getInitSpecificationCSRule());
                      	        }
                             		setWithLastConsumed(
                             			current,
                             			"exprString",
                              		lv_exprString_5_0,
                              		"org.eclipse.ocl.xtext.completeocl.CompleteOCL.UNQUOTED_STRING");

                    }

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
    // $ANTLR end "ruleInitSpecificationCS"


    // $ANTLR start "entryRuleTemplateSignatureCS"
    // InternalCompleteOCL.g:2109:1: entryRuleTemplateSignatureCS returns [EObject current=null] : iv_ruleTemplateSignatureCS= ruleTemplateSignatureCS EOF ;
    public final EObject entryRuleTemplateSignatureCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTemplateSignatureCS = null;


        try {
            // InternalCompleteOCL.g:2110:2: (iv_ruleTemplateSignatureCS= ruleTemplateSignatureCS EOF )
            // InternalCompleteOCL.g:2111:2: iv_ruleTemplateSignatureCS= ruleTemplateSignatureCS EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getTemplateSignatureCSRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleTemplateSignatureCS=ruleTemplateSignatureCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleTemplateSignatureCS;
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
    // $ANTLR end "entryRuleTemplateSignatureCS"


    // $ANTLR start "ruleTemplateSignatureCS"
    // InternalCompleteOCL.g:2118:1: ruleTemplateSignatureCS returns [EObject current=null] : ( (otherlv_0= '(' ( (lv_ownedParameters_1_0= ruleTypeParameterCS ) ) (otherlv_2= ',' ( (lv_ownedParameters_3_0= ruleTypeParameterCS ) ) )* otherlv_4= ')' ) | (otherlv_5= '<' ( (lv_ownedParameters_6_0= ruleTypeParameterCS ) ) (otherlv_7= ',' ( (lv_ownedParameters_8_0= ruleTypeParameterCS ) ) )* otherlv_9= '>' ) ) ;
    public final EObject ruleTemplateSignatureCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_9=null;
        EObject lv_ownedParameters_1_0 = null;

        EObject lv_ownedParameters_3_0 = null;

        EObject lv_ownedParameters_6_0 = null;

        EObject lv_ownedParameters_8_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:2121:28: ( ( (otherlv_0= '(' ( (lv_ownedParameters_1_0= ruleTypeParameterCS ) ) (otherlv_2= ',' ( (lv_ownedParameters_3_0= ruleTypeParameterCS ) ) )* otherlv_4= ')' ) | (otherlv_5= '<' ( (lv_ownedParameters_6_0= ruleTypeParameterCS ) ) (otherlv_7= ',' ( (lv_ownedParameters_8_0= ruleTypeParameterCS ) ) )* otherlv_9= '>' ) ) )
            // InternalCompleteOCL.g:2122:1: ( (otherlv_0= '(' ( (lv_ownedParameters_1_0= ruleTypeParameterCS ) ) (otherlv_2= ',' ( (lv_ownedParameters_3_0= ruleTypeParameterCS ) ) )* otherlv_4= ')' ) | (otherlv_5= '<' ( (lv_ownedParameters_6_0= ruleTypeParameterCS ) ) (otherlv_7= ',' ( (lv_ownedParameters_8_0= ruleTypeParameterCS ) ) )* otherlv_9= '>' ) )
            {
            // InternalCompleteOCL.g:2122:1: ( (otherlv_0= '(' ( (lv_ownedParameters_1_0= ruleTypeParameterCS ) ) (otherlv_2= ',' ( (lv_ownedParameters_3_0= ruleTypeParameterCS ) ) )* otherlv_4= ')' ) | (otherlv_5= '<' ( (lv_ownedParameters_6_0= ruleTypeParameterCS ) ) (otherlv_7= ',' ( (lv_ownedParameters_8_0= ruleTypeParameterCS ) ) )* otherlv_9= '>' ) )
            int alt58=2;
            int LA58_0 = input.LA(1);

            if ( (LA58_0==22) ) {
                alt58=1;
            }
            else if ( (LA58_0==40) ) {
                alt58=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 58, 0, input);

                throw nvae;
            }
            switch (alt58) {
                case 1 :
                    // InternalCompleteOCL.g:2122:2: (otherlv_0= '(' ( (lv_ownedParameters_1_0= ruleTypeParameterCS ) ) (otherlv_2= ',' ( (lv_ownedParameters_3_0= ruleTypeParameterCS ) ) )* otherlv_4= ')' )
                    {
                    // InternalCompleteOCL.g:2122:2: (otherlv_0= '(' ( (lv_ownedParameters_1_0= ruleTypeParameterCS ) ) (otherlv_2= ',' ( (lv_ownedParameters_3_0= ruleTypeParameterCS ) ) )* otherlv_4= ')' )
                    // InternalCompleteOCL.g:2122:4: otherlv_0= '(' ( (lv_ownedParameters_1_0= ruleTypeParameterCS ) ) (otherlv_2= ',' ( (lv_ownedParameters_3_0= ruleTypeParameterCS ) ) )* otherlv_4= ')'
                    {
                    otherlv_0=(Token)match(input,22,FollowSets000.FOLLOW_19); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_0, grammarAccess.getTemplateSignatureCSAccess().getLeftParenthesisKeyword_0_0());

                    }
                    // InternalCompleteOCL.g:2126:1: ( (lv_ownedParameters_1_0= ruleTypeParameterCS ) )
                    // InternalCompleteOCL.g:2127:1: (lv_ownedParameters_1_0= ruleTypeParameterCS )
                    {
                    // InternalCompleteOCL.g:2127:1: (lv_ownedParameters_1_0= ruleTypeParameterCS )
                    // InternalCompleteOCL.g:2128:3: lv_ownedParameters_1_0= ruleTypeParameterCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getTemplateSignatureCSAccess().getOwnedParametersTypeParameterCSParserRuleCall_0_1_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_22);
                    lv_ownedParameters_1_0=ruleTypeParameterCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getTemplateSignatureCSRule());
                      	        }
                             		add(
                             			current,
                             			"ownedParameters",
                              		lv_ownedParameters_1_0,
                              		"org.eclipse.ocl.xtext.base.Base.TypeParameterCS");
                      	        afterParserOrEnumRuleCall();

                    }

                    }


                    }

                    // InternalCompleteOCL.g:2144:2: (otherlv_2= ',' ( (lv_ownedParameters_3_0= ruleTypeParameterCS ) ) )*
                    loop56:
                    do {
                        int alt56=2;
                        int LA56_0 = input.LA(1);

                        if ( (LA56_0==29) ) {
                            alt56=1;
                        }


                        switch (alt56) {
                    	case 1 :
                    	    // InternalCompleteOCL.g:2144:4: otherlv_2= ',' ( (lv_ownedParameters_3_0= ruleTypeParameterCS ) )
                    	    {
                    	    otherlv_2=(Token)match(input,29,FollowSets000.FOLLOW_19); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_2, grammarAccess.getTemplateSignatureCSAccess().getCommaKeyword_0_2_0());

                    	    }
                    	    // InternalCompleteOCL.g:2148:1: ( (lv_ownedParameters_3_0= ruleTypeParameterCS ) )
                    	    // InternalCompleteOCL.g:2149:1: (lv_ownedParameters_3_0= ruleTypeParameterCS )
                    	    {
                    	    // InternalCompleteOCL.g:2149:1: (lv_ownedParameters_3_0= ruleTypeParameterCS )
                    	    // InternalCompleteOCL.g:2150:3: lv_ownedParameters_3_0= ruleTypeParameterCS
                    	    {
                    	    if ( state.backtracking==0 ) {

                    	      	        newCompositeNode(grammarAccess.getTemplateSignatureCSAccess().getOwnedParametersTypeParameterCSParserRuleCall_0_2_1_0());

                    	    }
                    	    pushFollow(FollowSets000.FOLLOW_22);
                    	    lv_ownedParameters_3_0=ruleTypeParameterCS();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = createModelElementForParent(grammarAccess.getTemplateSignatureCSRule());
                    	      	        }
                    	             		add(
                    	             			current,
                    	             			"ownedParameters",
                    	              		lv_ownedParameters_3_0,
                    	              		"org.eclipse.ocl.xtext.base.Base.TypeParameterCS");
                    	      	        afterParserOrEnumRuleCall();

                    	    }

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop56;
                        }
                    } while (true);

                    otherlv_4=(Token)match(input,23,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_4, grammarAccess.getTemplateSignatureCSAccess().getRightParenthesisKeyword_0_3());

                    }

                    }


                    }
                    break;
                case 2 :
                    // InternalCompleteOCL.g:2171:6: (otherlv_5= '<' ( (lv_ownedParameters_6_0= ruleTypeParameterCS ) ) (otherlv_7= ',' ( (lv_ownedParameters_8_0= ruleTypeParameterCS ) ) )* otherlv_9= '>' )
                    {
                    // InternalCompleteOCL.g:2171:6: (otherlv_5= '<' ( (lv_ownedParameters_6_0= ruleTypeParameterCS ) ) (otherlv_7= ',' ( (lv_ownedParameters_8_0= ruleTypeParameterCS ) ) )* otherlv_9= '>' )
                    // InternalCompleteOCL.g:2171:8: otherlv_5= '<' ( (lv_ownedParameters_6_0= ruleTypeParameterCS ) ) (otherlv_7= ',' ( (lv_ownedParameters_8_0= ruleTypeParameterCS ) ) )* otherlv_9= '>'
                    {
                    otherlv_5=(Token)match(input,40,FollowSets000.FOLLOW_19); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_5, grammarAccess.getTemplateSignatureCSAccess().getLessThanSignKeyword_1_0());

                    }
                    // InternalCompleteOCL.g:2175:1: ( (lv_ownedParameters_6_0= ruleTypeParameterCS ) )
                    // InternalCompleteOCL.g:2176:1: (lv_ownedParameters_6_0= ruleTypeParameterCS )
                    {
                    // InternalCompleteOCL.g:2176:1: (lv_ownedParameters_6_0= ruleTypeParameterCS )
                    // InternalCompleteOCL.g:2177:3: lv_ownedParameters_6_0= ruleTypeParameterCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getTemplateSignatureCSAccess().getOwnedParametersTypeParameterCSParserRuleCall_1_1_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_39);
                    lv_ownedParameters_6_0=ruleTypeParameterCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getTemplateSignatureCSRule());
                      	        }
                             		add(
                             			current,
                             			"ownedParameters",
                              		lv_ownedParameters_6_0,
                              		"org.eclipse.ocl.xtext.base.Base.TypeParameterCS");
                      	        afterParserOrEnumRuleCall();

                    }

                    }


                    }

                    // InternalCompleteOCL.g:2193:2: (otherlv_7= ',' ( (lv_ownedParameters_8_0= ruleTypeParameterCS ) ) )*
                    loop57:
                    do {
                        int alt57=2;
                        int LA57_0 = input.LA(1);

                        if ( (LA57_0==29) ) {
                            alt57=1;
                        }


                        switch (alt57) {
                    	case 1 :
                    	    // InternalCompleteOCL.g:2193:4: otherlv_7= ',' ( (lv_ownedParameters_8_0= ruleTypeParameterCS ) )
                    	    {
                    	    otherlv_7=(Token)match(input,29,FollowSets000.FOLLOW_19); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_7, grammarAccess.getTemplateSignatureCSAccess().getCommaKeyword_1_2_0());

                    	    }
                    	    // InternalCompleteOCL.g:2197:1: ( (lv_ownedParameters_8_0= ruleTypeParameterCS ) )
                    	    // InternalCompleteOCL.g:2198:1: (lv_ownedParameters_8_0= ruleTypeParameterCS )
                    	    {
                    	    // InternalCompleteOCL.g:2198:1: (lv_ownedParameters_8_0= ruleTypeParameterCS )
                    	    // InternalCompleteOCL.g:2199:3: lv_ownedParameters_8_0= ruleTypeParameterCS
                    	    {
                    	    if ( state.backtracking==0 ) {

                    	      	        newCompositeNode(grammarAccess.getTemplateSignatureCSAccess().getOwnedParametersTypeParameterCSParserRuleCall_1_2_1_0());

                    	    }
                    	    pushFollow(FollowSets000.FOLLOW_39);
                    	    lv_ownedParameters_8_0=ruleTypeParameterCS();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = createModelElementForParent(grammarAccess.getTemplateSignatureCSRule());
                    	      	        }
                    	             		add(
                    	             			current,
                    	             			"ownedParameters",
                    	              		lv_ownedParameters_8_0,
                    	              		"org.eclipse.ocl.xtext.base.Base.TypeParameterCS");
                    	      	        afterParserOrEnumRuleCall();

                    	    }

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop57;
                        }
                    } while (true);

                    otherlv_9=(Token)match(input,41,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_9, grammarAccess.getTemplateSignatureCSAccess().getGreaterThanSignKeyword_1_3());

                    }

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
    // $ANTLR end "ruleTemplateSignatureCS"


    // $ANTLR start "entryRuleTypedRefCS"
    // InternalCompleteOCL.g:2227:1: entryRuleTypedRefCS returns [EObject current=null] : iv_ruleTypedRefCS= ruleTypedRefCS EOF ;
    public final EObject entryRuleTypedRefCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTypedRefCS = null;


        try {
            // InternalCompleteOCL.g:2228:2: (iv_ruleTypedRefCS= ruleTypedRefCS EOF )
            // InternalCompleteOCL.g:2229:2: iv_ruleTypedRefCS= ruleTypedRefCS EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getTypedRefCSRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleTypedRefCS=ruleTypedRefCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleTypedRefCS;
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
    // $ANTLR end "entryRuleTypedRefCS"


    // $ANTLR start "ruleTypedRefCS"
    // InternalCompleteOCL.g:2236:1: ruleTypedRefCS returns [EObject current=null] : (this_TypeLiteralCS_0= ruleTypeLiteralCS | this_TypedTypeRefCS_1= ruleTypedTypeRefCS ) ;
    public final EObject ruleTypedRefCS() throws RecognitionException {
        EObject current = null;

        EObject this_TypeLiteralCS_0 = null;

        EObject this_TypedTypeRefCS_1 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:2239:28: ( (this_TypeLiteralCS_0= ruleTypeLiteralCS | this_TypedTypeRefCS_1= ruleTypedTypeRefCS ) )
            // InternalCompleteOCL.g:2240:1: (this_TypeLiteralCS_0= ruleTypeLiteralCS | this_TypedTypeRefCS_1= ruleTypedTypeRefCS )
            {
            // InternalCompleteOCL.g:2240:1: (this_TypeLiteralCS_0= ruleTypeLiteralCS | this_TypedTypeRefCS_1= ruleTypedTypeRefCS )
            int alt59=2;
            int LA59_0 = input.LA(1);

            if ( ((LA59_0>=43 && LA59_0<=52)||(LA59_0>=74 && LA59_0<=75)||(LA59_0>=77 && LA59_0<=81)) ) {
                alt59=1;
            }
            else if ( ((LA59_0>=RULE_SIMPLE_ID && LA59_0<=RULE_ESCAPED_ID)||(LA59_0>=31 && LA59_0<=33)) ) {
                alt59=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 59, 0, input);

                throw nvae;
            }
            switch (alt59) {
                case 1 :
                    // InternalCompleteOCL.g:2241:2: this_TypeLiteralCS_0= ruleTypeLiteralCS
                    {
                    if ( state.backtracking==0 ) {

                      	  /* */

                    }
                    if ( state.backtracking==0 ) {

                              newCompositeNode(grammarAccess.getTypedRefCSAccess().getTypeLiteralCSParserRuleCall_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_TypeLiteralCS_0=ruleTypeLiteralCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_TypeLiteralCS_0;
                              afterParserOrEnumRuleCall();

                    }

                    }
                    break;
                case 2 :
                    // InternalCompleteOCL.g:2254:2: this_TypedTypeRefCS_1= ruleTypedTypeRefCS
                    {
                    if ( state.backtracking==0 ) {

                      	  /* */

                    }
                    if ( state.backtracking==0 ) {

                              newCompositeNode(grammarAccess.getTypedRefCSAccess().getTypedTypeRefCSParserRuleCall_1());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_TypedTypeRefCS_1=ruleTypedTypeRefCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_TypedTypeRefCS_1;
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
    // $ANTLR end "ruleTypedRefCS"


    // $ANTLR start "entryRuleUnrestrictedName"
    // InternalCompleteOCL.g:2273:1: entryRuleUnrestrictedName returns [String current=null] : iv_ruleUnrestrictedName= ruleUnrestrictedName EOF ;
    public final String entryRuleUnrestrictedName() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleUnrestrictedName = null;


        try {
            // InternalCompleteOCL.g:2274:2: (iv_ruleUnrestrictedName= ruleUnrestrictedName EOF )
            // InternalCompleteOCL.g:2275:2: iv_ruleUnrestrictedName= ruleUnrestrictedName EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getUnrestrictedNameRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleUnrestrictedName=ruleUnrestrictedName();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleUnrestrictedName.getText();
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
    // $ANTLR end "entryRuleUnrestrictedName"


    // $ANTLR start "ruleUnrestrictedName"
    // InternalCompleteOCL.g:2282:1: ruleUnrestrictedName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_EssentialOCLUnrestrictedName_0= ruleEssentialOCLUnrestrictedName | kw= 'import' | kw= 'include' | kw= 'library' ) ;
    public final AntlrDatatypeRuleToken ruleUnrestrictedName() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        AntlrDatatypeRuleToken this_EssentialOCLUnrestrictedName_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:2285:28: ( (this_EssentialOCLUnrestrictedName_0= ruleEssentialOCLUnrestrictedName | kw= 'import' | kw= 'include' | kw= 'library' ) )
            // InternalCompleteOCL.g:2286:1: (this_EssentialOCLUnrestrictedName_0= ruleEssentialOCLUnrestrictedName | kw= 'import' | kw= 'include' | kw= 'library' )
            {
            // InternalCompleteOCL.g:2286:1: (this_EssentialOCLUnrestrictedName_0= ruleEssentialOCLUnrestrictedName | kw= 'import' | kw= 'include' | kw= 'library' )
            int alt60=4;
            switch ( input.LA(1) ) {
            case RULE_SIMPLE_ID:
            case RULE_ESCAPED_ID:
                {
                alt60=1;
                }
                break;
            case 31:
                {
                alt60=2;
                }
                break;
            case 32:
                {
                alt60=3;
                }
                break;
            case 33:
                {
                alt60=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 60, 0, input);

                throw nvae;
            }

            switch (alt60) {
                case 1 :
                    // InternalCompleteOCL.g:2287:5: this_EssentialOCLUnrestrictedName_0= ruleEssentialOCLUnrestrictedName
                    {
                    if ( state.backtracking==0 ) {

                              newCompositeNode(grammarAccess.getUnrestrictedNameAccess().getEssentialOCLUnrestrictedNameParserRuleCall_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_EssentialOCLUnrestrictedName_0=ruleEssentialOCLUnrestrictedName();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      		current.merge(this_EssentialOCLUnrestrictedName_0);

                    }
                    if ( state.backtracking==0 ) {

                              afterParserOrEnumRuleCall();

                    }

                    }
                    break;
                case 2 :
                    // InternalCompleteOCL.g:2299:2: kw= 'import'
                    {
                    kw=(Token)match(input,31,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getUnrestrictedNameAccess().getImportKeyword_1());

                    }

                    }
                    break;
                case 3 :
                    // InternalCompleteOCL.g:2306:2: kw= 'include'
                    {
                    kw=(Token)match(input,32,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getUnrestrictedNameAccess().getIncludeKeyword_2());

                    }

                    }
                    break;
                case 4 :
                    // InternalCompleteOCL.g:2313:2: kw= 'library'
                    {
                    kw=(Token)match(input,33,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getUnrestrictedNameAccess().getLibraryKeyword_3());

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
    // $ANTLR end "ruleUnrestrictedName"


    // $ANTLR start "entryRuleNavigatingArgExpCS"
    // InternalCompleteOCL.g:2326:1: entryRuleNavigatingArgExpCS returns [EObject current=null] : iv_ruleNavigatingArgExpCS= ruleNavigatingArgExpCS EOF ;
    public final EObject entryRuleNavigatingArgExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNavigatingArgExpCS = null;


        try {
            // InternalCompleteOCL.g:2327:2: (iv_ruleNavigatingArgExpCS= ruleNavigatingArgExpCS EOF )
            // InternalCompleteOCL.g:2328:2: iv_ruleNavigatingArgExpCS= ruleNavigatingArgExpCS EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getNavigatingArgExpCSRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleNavigatingArgExpCS=ruleNavigatingArgExpCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleNavigatingArgExpCS;
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
    // $ANTLR end "entryRuleNavigatingArgExpCS"


    // $ANTLR start "ruleNavigatingArgExpCS"
    // InternalCompleteOCL.g:2335:1: ruleNavigatingArgExpCS returns [EObject current=null] : ( ( () otherlv_1= '?' ) | this_ExpCS_2= ruleExpCS ) ;
    public final EObject ruleNavigatingArgExpCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject this_ExpCS_2 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:2338:28: ( ( ( () otherlv_1= '?' ) | this_ExpCS_2= ruleExpCS ) )
            // InternalCompleteOCL.g:2339:1: ( ( () otherlv_1= '?' ) | this_ExpCS_2= ruleExpCS )
            {
            // InternalCompleteOCL.g:2339:1: ( ( () otherlv_1= '?' ) | this_ExpCS_2= ruleExpCS )
            int alt61=2;
            int LA61_0 = input.LA(1);

            if ( (LA61_0==42) ) {
                alt61=1;
            }
            else if ( ((LA61_0>=RULE_SIMPLE_ID && LA61_0<=RULE_SINGLE_QUOTED_STRING)||LA61_0==22||(LA61_0>=31 && LA61_0<=33)||(LA61_0>=43 && LA61_0<=56)||(LA61_0>=74 && LA61_0<=75)||(LA61_0>=77 && LA61_0<=81)||LA61_0==86||(LA61_0>=88 && LA61_0<=91)||LA61_0==98||(LA61_0>=103 && LA61_0<=104)) ) {
                alt61=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 61, 0, input);

                throw nvae;
            }
            switch (alt61) {
                case 1 :
                    // InternalCompleteOCL.g:2339:2: ( () otherlv_1= '?' )
                    {
                    // InternalCompleteOCL.g:2339:2: ( () otherlv_1= '?' )
                    // InternalCompleteOCL.g:2339:3: () otherlv_1= '?'
                    {
                    // InternalCompleteOCL.g:2339:3: ()
                    // InternalCompleteOCL.g:2340:2:
                    {
                    if ( state.backtracking==0 ) {

                      	  /* */

                    }
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElement(
                                  grammarAccess.getNavigatingArgExpCSAccess().getOCLMessageArgCSAction_0_0(),
                                  current);

                    }

                    }

                    otherlv_1=(Token)match(input,42,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_1, grammarAccess.getNavigatingArgExpCSAccess().getQuestionMarkKeyword_0_1());

                    }

                    }


                    }
                    break;
                case 2 :
                    // InternalCompleteOCL.g:2354:2: this_ExpCS_2= ruleExpCS
                    {
                    if ( state.backtracking==0 ) {

                      	  /* */

                    }
                    if ( state.backtracking==0 ) {

                              newCompositeNode(grammarAccess.getNavigatingArgExpCSAccess().getExpCSParserRuleCall_1());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_ExpCS_2=ruleExpCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_ExpCS_2;
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
    // $ANTLR end "ruleNavigatingArgExpCS"


    // $ANTLR start "entryRuleNavigationOperatorName"
    // InternalCompleteOCL.g:2373:1: entryRuleNavigationOperatorName returns [String current=null] : iv_ruleNavigationOperatorName= ruleNavigationOperatorName EOF ;
    public final String entryRuleNavigationOperatorName() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleNavigationOperatorName = null;


        try {
            // InternalCompleteOCL.g:2374:2: (iv_ruleNavigationOperatorName= ruleNavigationOperatorName EOF )
            // InternalCompleteOCL.g:2375:2: iv_ruleNavigationOperatorName= ruleNavigationOperatorName EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getNavigationOperatorNameRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleNavigationOperatorName=ruleNavigationOperatorName();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleNavigationOperatorName.getText();
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
    // $ANTLR end "entryRuleNavigationOperatorName"


    // $ANTLR start "ruleNavigationOperatorName"
    // InternalCompleteOCL.g:2382:1: ruleNavigationOperatorName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_EssentialOCLNavigationOperatorName_0= ruleEssentialOCLNavigationOperatorName | this_CompleteOCLNavigationOperatorName_1= ruleCompleteOCLNavigationOperatorName ) ;
    public final AntlrDatatypeRuleToken ruleNavigationOperatorName() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        AntlrDatatypeRuleToken this_EssentialOCLNavigationOperatorName_0 = null;

        AntlrDatatypeRuleToken this_CompleteOCLNavigationOperatorName_1 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:2385:28: ( (this_EssentialOCLNavigationOperatorName_0= ruleEssentialOCLNavigationOperatorName | this_CompleteOCLNavigationOperatorName_1= ruleCompleteOCLNavigationOperatorName ) )
            // InternalCompleteOCL.g:2386:1: (this_EssentialOCLNavigationOperatorName_0= ruleEssentialOCLNavigationOperatorName | this_CompleteOCLNavigationOperatorName_1= ruleCompleteOCLNavigationOperatorName )
            {
            // InternalCompleteOCL.g:2386:1: (this_EssentialOCLNavigationOperatorName_0= ruleEssentialOCLNavigationOperatorName | this_CompleteOCLNavigationOperatorName_1= ruleCompleteOCLNavigationOperatorName )
            int alt62=2;
            int LA62_0 = input.LA(1);

            if ( ((LA62_0>=70 && LA62_0<=73)) ) {
                alt62=1;
            }
            else if ( ((LA62_0>=18 && LA62_0<=19)) ) {
                alt62=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 62, 0, input);

                throw nvae;
            }
            switch (alt62) {
                case 1 :
                    // InternalCompleteOCL.g:2387:5: this_EssentialOCLNavigationOperatorName_0= ruleEssentialOCLNavigationOperatorName
                    {
                    if ( state.backtracking==0 ) {

                              newCompositeNode(grammarAccess.getNavigationOperatorNameAccess().getEssentialOCLNavigationOperatorNameParserRuleCall_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_EssentialOCLNavigationOperatorName_0=ruleEssentialOCLNavigationOperatorName();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      		current.merge(this_EssentialOCLNavigationOperatorName_0);

                    }
                    if ( state.backtracking==0 ) {

                              afterParserOrEnumRuleCall();

                    }

                    }
                    break;
                case 2 :
                    // InternalCompleteOCL.g:2399:5: this_CompleteOCLNavigationOperatorName_1= ruleCompleteOCLNavigationOperatorName
                    {
                    if ( state.backtracking==0 ) {

                              newCompositeNode(grammarAccess.getNavigationOperatorNameAccess().getCompleteOCLNavigationOperatorNameParserRuleCall_1());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_CompleteOCLNavigationOperatorName_1=ruleCompleteOCLNavigationOperatorName();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      		current.merge(this_CompleteOCLNavigationOperatorName_1);

                    }
                    if ( state.backtracking==0 ) {

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
    // $ANTLR end "ruleNavigationOperatorName"


    // $ANTLR start "entryRulePrimitiveTypeIdentifier"
    // InternalCompleteOCL.g:2417:1: entryRulePrimitiveTypeIdentifier returns [String current=null] : iv_rulePrimitiveTypeIdentifier= rulePrimitiveTypeIdentifier EOF ;
    public final String entryRulePrimitiveTypeIdentifier() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulePrimitiveTypeIdentifier = null;


        try {
            // InternalCompleteOCL.g:2418:2: (iv_rulePrimitiveTypeIdentifier= rulePrimitiveTypeIdentifier EOF )
            // InternalCompleteOCL.g:2419:2: iv_rulePrimitiveTypeIdentifier= rulePrimitiveTypeIdentifier EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getPrimitiveTypeIdentifierRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_rulePrimitiveTypeIdentifier=rulePrimitiveTypeIdentifier();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_rulePrimitiveTypeIdentifier.getText();
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
    // $ANTLR end "entryRulePrimitiveTypeIdentifier"


    // $ANTLR start "rulePrimitiveTypeIdentifier"
    // InternalCompleteOCL.g:2426:1: rulePrimitiveTypeIdentifier returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= 'Boolean' | kw= 'Integer' | kw= 'Real' | kw= 'String' | kw= 'UnlimitedNatural' | kw= 'OclAny' | kw= 'OclInvalid' | kw= 'OclMessage' | kw= 'OclState' | kw= 'OclVoid' ) ;
    public final AntlrDatatypeRuleToken rulePrimitiveTypeIdentifier() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule();

        try {
            // InternalCompleteOCL.g:2429:28: ( (kw= 'Boolean' | kw= 'Integer' | kw= 'Real' | kw= 'String' | kw= 'UnlimitedNatural' | kw= 'OclAny' | kw= 'OclInvalid' | kw= 'OclMessage' | kw= 'OclState' | kw= 'OclVoid' ) )
            // InternalCompleteOCL.g:2430:1: (kw= 'Boolean' | kw= 'Integer' | kw= 'Real' | kw= 'String' | kw= 'UnlimitedNatural' | kw= 'OclAny' | kw= 'OclInvalid' | kw= 'OclMessage' | kw= 'OclState' | kw= 'OclVoid' )
            {
            // InternalCompleteOCL.g:2430:1: (kw= 'Boolean' | kw= 'Integer' | kw= 'Real' | kw= 'String' | kw= 'UnlimitedNatural' | kw= 'OclAny' | kw= 'OclInvalid' | kw= 'OclMessage' | kw= 'OclState' | kw= 'OclVoid' )
            int alt63=10;
            switch ( input.LA(1) ) {
            case 43:
                {
                alt63=1;
                }
                break;
            case 44:
                {
                alt63=2;
                }
                break;
            case 45:
                {
                alt63=3;
                }
                break;
            case 46:
                {
                alt63=4;
                }
                break;
            case 47:
                {
                alt63=5;
                }
                break;
            case 48:
                {
                alt63=6;
                }
                break;
            case 49:
                {
                alt63=7;
                }
                break;
            case 50:
                {
                alt63=8;
                }
                break;
            case 51:
                {
                alt63=9;
                }
                break;
            case 52:
                {
                alt63=10;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 63, 0, input);

                throw nvae;
            }

            switch (alt63) {
                case 1 :
                    // InternalCompleteOCL.g:2431:2: kw= 'Boolean'
                    {
                    kw=(Token)match(input,43,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getPrimitiveTypeIdentifierAccess().getBooleanKeyword_0());

                    }

                    }
                    break;
                case 2 :
                    // InternalCompleteOCL.g:2438:2: kw= 'Integer'
                    {
                    kw=(Token)match(input,44,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getPrimitiveTypeIdentifierAccess().getIntegerKeyword_1());

                    }

                    }
                    break;
                case 3 :
                    // InternalCompleteOCL.g:2445:2: kw= 'Real'
                    {
                    kw=(Token)match(input,45,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getPrimitiveTypeIdentifierAccess().getRealKeyword_2());

                    }

                    }
                    break;
                case 4 :
                    // InternalCompleteOCL.g:2452:2: kw= 'String'
                    {
                    kw=(Token)match(input,46,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getPrimitiveTypeIdentifierAccess().getStringKeyword_3());

                    }

                    }
                    break;
                case 5 :
                    // InternalCompleteOCL.g:2459:2: kw= 'UnlimitedNatural'
                    {
                    kw=(Token)match(input,47,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getPrimitiveTypeIdentifierAccess().getUnlimitedNaturalKeyword_4());

                    }

                    }
                    break;
                case 6 :
                    // InternalCompleteOCL.g:2466:2: kw= 'OclAny'
                    {
                    kw=(Token)match(input,48,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getPrimitiveTypeIdentifierAccess().getOclAnyKeyword_5());

                    }

                    }
                    break;
                case 7 :
                    // InternalCompleteOCL.g:2473:2: kw= 'OclInvalid'
                    {
                    kw=(Token)match(input,49,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getPrimitiveTypeIdentifierAccess().getOclInvalidKeyword_6());

                    }

                    }
                    break;
                case 8 :
                    // InternalCompleteOCL.g:2480:2: kw= 'OclMessage'
                    {
                    kw=(Token)match(input,50,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getPrimitiveTypeIdentifierAccess().getOclMessageKeyword_7());

                    }

                    }
                    break;
                case 9 :
                    // InternalCompleteOCL.g:2487:2: kw= 'OclState'
                    {
                    kw=(Token)match(input,51,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getPrimitiveTypeIdentifierAccess().getOclStateKeyword_8());

                    }

                    }
                    break;
                case 10 :
                    // InternalCompleteOCL.g:2494:2: kw= 'OclVoid'
                    {
                    kw=(Token)match(input,52,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getPrimitiveTypeIdentifierAccess().getOclVoidKeyword_9());

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
    // $ANTLR end "rulePrimitiveTypeIdentifier"


    // $ANTLR start "entryRuleEssentialOCLUnaryOperatorName"
    // InternalCompleteOCL.g:2511:1: entryRuleEssentialOCLUnaryOperatorName returns [String current=null] : iv_ruleEssentialOCLUnaryOperatorName= ruleEssentialOCLUnaryOperatorName EOF ;
    public final String entryRuleEssentialOCLUnaryOperatorName() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleEssentialOCLUnaryOperatorName = null;


        try {
            // InternalCompleteOCL.g:2512:2: (iv_ruleEssentialOCLUnaryOperatorName= ruleEssentialOCLUnaryOperatorName EOF )
            // InternalCompleteOCL.g:2513:2: iv_ruleEssentialOCLUnaryOperatorName= ruleEssentialOCLUnaryOperatorName EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getEssentialOCLUnaryOperatorNameRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleEssentialOCLUnaryOperatorName=ruleEssentialOCLUnaryOperatorName();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleEssentialOCLUnaryOperatorName.getText();
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
    // $ANTLR end "entryRuleEssentialOCLUnaryOperatorName"


    // $ANTLR start "ruleEssentialOCLUnaryOperatorName"
    // InternalCompleteOCL.g:2520:1: ruleEssentialOCLUnaryOperatorName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= '-' | kw= 'not' | kw= 'not2' ) ;
    public final AntlrDatatypeRuleToken ruleEssentialOCLUnaryOperatorName() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule();

        try {
            // InternalCompleteOCL.g:2523:28: ( (kw= '-' | kw= 'not' | kw= 'not2' ) )
            // InternalCompleteOCL.g:2524:1: (kw= '-' | kw= 'not' | kw= 'not2' )
            {
            // InternalCompleteOCL.g:2524:1: (kw= '-' | kw= 'not' | kw= 'not2' )
            int alt64=3;
            switch ( input.LA(1) ) {
            case 53:
                {
                alt64=1;
                }
                break;
            case 54:
                {
                alt64=2;
                }
                break;
            case 55:
                {
                alt64=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 64, 0, input);

                throw nvae;
            }

            switch (alt64) {
                case 1 :
                    // InternalCompleteOCL.g:2525:2: kw= '-'
                    {
                    kw=(Token)match(input,53,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getEssentialOCLUnaryOperatorNameAccess().getHyphenMinusKeyword_0());

                    }

                    }
                    break;
                case 2 :
                    // InternalCompleteOCL.g:2532:2: kw= 'not'
                    {
                    kw=(Token)match(input,54,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getEssentialOCLUnaryOperatorNameAccess().getNotKeyword_1());

                    }

                    }
                    break;
                case 3 :
                    // InternalCompleteOCL.g:2539:2: kw= 'not2'
                    {
                    kw=(Token)match(input,55,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getEssentialOCLUnaryOperatorNameAccess().getNot2Keyword_2());

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
    // $ANTLR end "ruleEssentialOCLUnaryOperatorName"


    // $ANTLR start "entryRuleEssentialOCLInfixOperatorName"
    // InternalCompleteOCL.g:2552:1: entryRuleEssentialOCLInfixOperatorName returns [String current=null] : iv_ruleEssentialOCLInfixOperatorName= ruleEssentialOCLInfixOperatorName EOF ;
    public final String entryRuleEssentialOCLInfixOperatorName() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleEssentialOCLInfixOperatorName = null;


        try {
            // InternalCompleteOCL.g:2553:2: (iv_ruleEssentialOCLInfixOperatorName= ruleEssentialOCLInfixOperatorName EOF )
            // InternalCompleteOCL.g:2554:2: iv_ruleEssentialOCLInfixOperatorName= ruleEssentialOCLInfixOperatorName EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getEssentialOCLInfixOperatorNameRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleEssentialOCLInfixOperatorName=ruleEssentialOCLInfixOperatorName();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleEssentialOCLInfixOperatorName.getText();
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
    // $ANTLR end "entryRuleEssentialOCLInfixOperatorName"


    // $ANTLR start "ruleEssentialOCLInfixOperatorName"
    // InternalCompleteOCL.g:2561:1: ruleEssentialOCLInfixOperatorName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= '*' | kw= '/' | kw= '+' | kw= '-' | kw= '>' | kw= '<' | kw= '>=' | kw= '<=' | kw= '=' | kw= '<>' | kw= 'and' | kw= 'and2' | kw= 'implies' | kw= 'implies2' | kw= 'or' | kw= 'or2' | kw= 'xor' | kw= 'xor2' ) ;
    public final AntlrDatatypeRuleToken ruleEssentialOCLInfixOperatorName() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule();

        try {
            // InternalCompleteOCL.g:2564:28: ( (kw= '*' | kw= '/' | kw= '+' | kw= '-' | kw= '>' | kw= '<' | kw= '>=' | kw= '<=' | kw= '=' | kw= '<>' | kw= 'and' | kw= 'and2' | kw= 'implies' | kw= 'implies2' | kw= 'or' | kw= 'or2' | kw= 'xor' | kw= 'xor2' ) )
            // InternalCompleteOCL.g:2565:1: (kw= '*' | kw= '/' | kw= '+' | kw= '-' | kw= '>' | kw= '<' | kw= '>=' | kw= '<=' | kw= '=' | kw= '<>' | kw= 'and' | kw= 'and2' | kw= 'implies' | kw= 'implies2' | kw= 'or' | kw= 'or2' | kw= 'xor' | kw= 'xor2' )
            {
            // InternalCompleteOCL.g:2565:1: (kw= '*' | kw= '/' | kw= '+' | kw= '-' | kw= '>' | kw= '<' | kw= '>=' | kw= '<=' | kw= '=' | kw= '<>' | kw= 'and' | kw= 'and2' | kw= 'implies' | kw= 'implies2' | kw= 'or' | kw= 'or2' | kw= 'xor' | kw= 'xor2' )
            int alt65=18;
            switch ( input.LA(1) ) {
            case 56:
                {
                alt65=1;
                }
                break;
            case 57:
                {
                alt65=2;
                }
                break;
            case 58:
                {
                alt65=3;
                }
                break;
            case 53:
                {
                alt65=4;
                }
                break;
            case 41:
                {
                alt65=5;
                }
                break;
            case 40:
                {
                alt65=6;
                }
                break;
            case 59:
                {
                alt65=7;
                }
                break;
            case 60:
                {
                alt65=8;
                }
                break;
            case 30:
                {
                alt65=9;
                }
                break;
            case 61:
                {
                alt65=10;
                }
                break;
            case 62:
                {
                alt65=11;
                }
                break;
            case 63:
                {
                alt65=12;
                }
                break;
            case 64:
                {
                alt65=13;
                }
                break;
            case 65:
                {
                alt65=14;
                }
                break;
            case 66:
                {
                alt65=15;
                }
                break;
            case 67:
                {
                alt65=16;
                }
                break;
            case 68:
                {
                alt65=17;
                }
                break;
            case 69:
                {
                alt65=18;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 65, 0, input);

                throw nvae;
            }

            switch (alt65) {
                case 1 :
                    // InternalCompleteOCL.g:2566:2: kw= '*'
                    {
                    kw=(Token)match(input,56,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getEssentialOCLInfixOperatorNameAccess().getAsteriskKeyword_0());

                    }

                    }
                    break;
                case 2 :
                    // InternalCompleteOCL.g:2573:2: kw= '/'
                    {
                    kw=(Token)match(input,57,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getEssentialOCLInfixOperatorNameAccess().getSolidusKeyword_1());

                    }

                    }
                    break;
                case 3 :
                    // InternalCompleteOCL.g:2580:2: kw= '+'
                    {
                    kw=(Token)match(input,58,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getEssentialOCLInfixOperatorNameAccess().getPlusSignKeyword_2());

                    }

                    }
                    break;
                case 4 :
                    // InternalCompleteOCL.g:2587:2: kw= '-'
                    {
                    kw=(Token)match(input,53,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getEssentialOCLInfixOperatorNameAccess().getHyphenMinusKeyword_3());

                    }

                    }
                    break;
                case 5 :
                    // InternalCompleteOCL.g:2594:2: kw= '>'
                    {
                    kw=(Token)match(input,41,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getEssentialOCLInfixOperatorNameAccess().getGreaterThanSignKeyword_4());

                    }

                    }
                    break;
                case 6 :
                    // InternalCompleteOCL.g:2601:2: kw= '<'
                    {
                    kw=(Token)match(input,40,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getEssentialOCLInfixOperatorNameAccess().getLessThanSignKeyword_5());

                    }

                    }
                    break;
                case 7 :
                    // InternalCompleteOCL.g:2608:2: kw= '>='
                    {
                    kw=(Token)match(input,59,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getEssentialOCLInfixOperatorNameAccess().getGreaterThanSignEqualsSignKeyword_6());

                    }

                    }
                    break;
                case 8 :
                    // InternalCompleteOCL.g:2615:2: kw= '<='
                    {
                    kw=(Token)match(input,60,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getEssentialOCLInfixOperatorNameAccess().getLessThanSignEqualsSignKeyword_7());

                    }

                    }
                    break;
                case 9 :
                    // InternalCompleteOCL.g:2622:2: kw= '='
                    {
                    kw=(Token)match(input,30,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getEssentialOCLInfixOperatorNameAccess().getEqualsSignKeyword_8());

                    }

                    }
                    break;
                case 10 :
                    // InternalCompleteOCL.g:2629:2: kw= '<>'
                    {
                    kw=(Token)match(input,61,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getEssentialOCLInfixOperatorNameAccess().getLessThanSignGreaterThanSignKeyword_9());

                    }

                    }
                    break;
                case 11 :
                    // InternalCompleteOCL.g:2636:2: kw= 'and'
                    {
                    kw=(Token)match(input,62,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getEssentialOCLInfixOperatorNameAccess().getAndKeyword_10());

                    }

                    }
                    break;
                case 12 :
                    // InternalCompleteOCL.g:2643:2: kw= 'and2'
                    {
                    kw=(Token)match(input,63,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getEssentialOCLInfixOperatorNameAccess().getAnd2Keyword_11());

                    }

                    }
                    break;
                case 13 :
                    // InternalCompleteOCL.g:2650:2: kw= 'implies'
                    {
                    kw=(Token)match(input,64,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getEssentialOCLInfixOperatorNameAccess().getImpliesKeyword_12());

                    }

                    }
                    break;
                case 14 :
                    // InternalCompleteOCL.g:2657:2: kw= 'implies2'
                    {
                    kw=(Token)match(input,65,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getEssentialOCLInfixOperatorNameAccess().getImplies2Keyword_13());

                    }

                    }
                    break;
                case 15 :
                    // InternalCompleteOCL.g:2664:2: kw= 'or'
                    {
                    kw=(Token)match(input,66,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getEssentialOCLInfixOperatorNameAccess().getOrKeyword_14());

                    }

                    }
                    break;
                case 16 :
                    // InternalCompleteOCL.g:2671:2: kw= 'or2'
                    {
                    kw=(Token)match(input,67,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getEssentialOCLInfixOperatorNameAccess().getOr2Keyword_15());

                    }

                    }
                    break;
                case 17 :
                    // InternalCompleteOCL.g:2678:2: kw= 'xor'
                    {
                    kw=(Token)match(input,68,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getEssentialOCLInfixOperatorNameAccess().getXorKeyword_16());

                    }

                    }
                    break;
                case 18 :
                    // InternalCompleteOCL.g:2685:2: kw= 'xor2'
                    {
                    kw=(Token)match(input,69,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getEssentialOCLInfixOperatorNameAccess().getXor2Keyword_17());

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
    // $ANTLR end "ruleEssentialOCLInfixOperatorName"


    // $ANTLR start "entryRuleEssentialOCLNavigationOperatorName"
    // InternalCompleteOCL.g:2698:1: entryRuleEssentialOCLNavigationOperatorName returns [String current=null] : iv_ruleEssentialOCLNavigationOperatorName= ruleEssentialOCLNavigationOperatorName EOF ;
    public final String entryRuleEssentialOCLNavigationOperatorName() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleEssentialOCLNavigationOperatorName = null;


        try {
            // InternalCompleteOCL.g:2699:2: (iv_ruleEssentialOCLNavigationOperatorName= ruleEssentialOCLNavigationOperatorName EOF )
            // InternalCompleteOCL.g:2700:2: iv_ruleEssentialOCLNavigationOperatorName= ruleEssentialOCLNavigationOperatorName EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getEssentialOCLNavigationOperatorNameRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleEssentialOCLNavigationOperatorName=ruleEssentialOCLNavigationOperatorName();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleEssentialOCLNavigationOperatorName.getText();
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
    // $ANTLR end "entryRuleEssentialOCLNavigationOperatorName"


    // $ANTLR start "ruleEssentialOCLNavigationOperatorName"
    // InternalCompleteOCL.g:2707:1: ruleEssentialOCLNavigationOperatorName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= '.' | kw= '->' | kw= '?.' | kw= '?->' ) ;
    public final AntlrDatatypeRuleToken ruleEssentialOCLNavigationOperatorName() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule();

        try {
            // InternalCompleteOCL.g:2710:28: ( (kw= '.' | kw= '->' | kw= '?.' | kw= '?->' ) )
            // InternalCompleteOCL.g:2711:1: (kw= '.' | kw= '->' | kw= '?.' | kw= '?->' )
            {
            // InternalCompleteOCL.g:2711:1: (kw= '.' | kw= '->' | kw= '?.' | kw= '?->' )
            int alt66=4;
            switch ( input.LA(1) ) {
            case 70:
                {
                alt66=1;
                }
                break;
            case 71:
                {
                alt66=2;
                }
                break;
            case 72:
                {
                alt66=3;
                }
                break;
            case 73:
                {
                alt66=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 66, 0, input);

                throw nvae;
            }

            switch (alt66) {
                case 1 :
                    // InternalCompleteOCL.g:2712:2: kw= '.'
                    {
                    kw=(Token)match(input,70,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getEssentialOCLNavigationOperatorNameAccess().getFullStopKeyword_0());

                    }

                    }
                    break;
                case 2 :
                    // InternalCompleteOCL.g:2719:2: kw= '->'
                    {
                    kw=(Token)match(input,71,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getEssentialOCLNavigationOperatorNameAccess().getHyphenMinusGreaterThanSignKeyword_1());

                    }

                    }
                    break;
                case 3 :
                    // InternalCompleteOCL.g:2726:2: kw= '?.'
                    {
                    kw=(Token)match(input,72,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getEssentialOCLNavigationOperatorNameAccess().getQuestionMarkFullStopKeyword_2());

                    }

                    }
                    break;
                case 4 :
                    // InternalCompleteOCL.g:2733:2: kw= '?->'
                    {
                    kw=(Token)match(input,73,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getEssentialOCLNavigationOperatorNameAccess().getQuestionMarkHyphenMinusGreaterThanSignKeyword_3());

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
    // $ANTLR end "ruleEssentialOCLNavigationOperatorName"


    // $ANTLR start "entryRuleBinaryOperatorName"
    // InternalCompleteOCL.g:2746:1: entryRuleBinaryOperatorName returns [String current=null] : iv_ruleBinaryOperatorName= ruleBinaryOperatorName EOF ;
    public final String entryRuleBinaryOperatorName() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleBinaryOperatorName = null;


        try {
            // InternalCompleteOCL.g:2747:2: (iv_ruleBinaryOperatorName= ruleBinaryOperatorName EOF )
            // InternalCompleteOCL.g:2748:2: iv_ruleBinaryOperatorName= ruleBinaryOperatorName EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getBinaryOperatorNameRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleBinaryOperatorName=ruleBinaryOperatorName();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleBinaryOperatorName.getText();
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
    // $ANTLR end "entryRuleBinaryOperatorName"


    // $ANTLR start "ruleBinaryOperatorName"
    // InternalCompleteOCL.g:2755:1: ruleBinaryOperatorName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_InfixOperatorName_0= ruleInfixOperatorName | this_NavigationOperatorName_1= ruleNavigationOperatorName ) ;
    public final AntlrDatatypeRuleToken ruleBinaryOperatorName() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        AntlrDatatypeRuleToken this_InfixOperatorName_0 = null;

        AntlrDatatypeRuleToken this_NavigationOperatorName_1 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:2758:28: ( (this_InfixOperatorName_0= ruleInfixOperatorName | this_NavigationOperatorName_1= ruleNavigationOperatorName ) )
            // InternalCompleteOCL.g:2759:1: (this_InfixOperatorName_0= ruleInfixOperatorName | this_NavigationOperatorName_1= ruleNavigationOperatorName )
            {
            // InternalCompleteOCL.g:2759:1: (this_InfixOperatorName_0= ruleInfixOperatorName | this_NavigationOperatorName_1= ruleNavigationOperatorName )
            int alt67=2;
            int LA67_0 = input.LA(1);

            if ( (LA67_0==30||(LA67_0>=40 && LA67_0<=41)||LA67_0==53||(LA67_0>=56 && LA67_0<=69)) ) {
                alt67=1;
            }
            else if ( ((LA67_0>=18 && LA67_0<=19)||(LA67_0>=70 && LA67_0<=73)) ) {
                alt67=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 67, 0, input);

                throw nvae;
            }
            switch (alt67) {
                case 1 :
                    // InternalCompleteOCL.g:2760:5: this_InfixOperatorName_0= ruleInfixOperatorName
                    {
                    if ( state.backtracking==0 ) {

                              newCompositeNode(grammarAccess.getBinaryOperatorNameAccess().getInfixOperatorNameParserRuleCall_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_InfixOperatorName_0=ruleInfixOperatorName();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      		current.merge(this_InfixOperatorName_0);

                    }
                    if ( state.backtracking==0 ) {

                              afterParserOrEnumRuleCall();

                    }

                    }
                    break;
                case 2 :
                    // InternalCompleteOCL.g:2772:5: this_NavigationOperatorName_1= ruleNavigationOperatorName
                    {
                    if ( state.backtracking==0 ) {

                              newCompositeNode(grammarAccess.getBinaryOperatorNameAccess().getNavigationOperatorNameParserRuleCall_1());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_NavigationOperatorName_1=ruleNavigationOperatorName();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      		current.merge(this_NavigationOperatorName_1);

                    }
                    if ( state.backtracking==0 ) {

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
    // $ANTLR end "ruleBinaryOperatorName"


    // $ANTLR start "entryRuleInfixOperatorName"
    // InternalCompleteOCL.g:2790:1: entryRuleInfixOperatorName returns [String current=null] : iv_ruleInfixOperatorName= ruleInfixOperatorName EOF ;
    public final String entryRuleInfixOperatorName() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleInfixOperatorName = null;


        try {
            // InternalCompleteOCL.g:2791:2: (iv_ruleInfixOperatorName= ruleInfixOperatorName EOF )
            // InternalCompleteOCL.g:2792:2: iv_ruleInfixOperatorName= ruleInfixOperatorName EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getInfixOperatorNameRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleInfixOperatorName=ruleInfixOperatorName();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleInfixOperatorName.getText();
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
    // $ANTLR end "entryRuleInfixOperatorName"


    // $ANTLR start "ruleInfixOperatorName"
    // InternalCompleteOCL.g:2799:1: ruleInfixOperatorName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_EssentialOCLInfixOperatorName_0= ruleEssentialOCLInfixOperatorName ;
    public final AntlrDatatypeRuleToken ruleInfixOperatorName() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        AntlrDatatypeRuleToken this_EssentialOCLInfixOperatorName_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:2802:28: (this_EssentialOCLInfixOperatorName_0= ruleEssentialOCLInfixOperatorName )
            // InternalCompleteOCL.g:2804:5: this_EssentialOCLInfixOperatorName_0= ruleEssentialOCLInfixOperatorName
            {
            if ( state.backtracking==0 ) {

                      newCompositeNode(grammarAccess.getInfixOperatorNameAccess().getEssentialOCLInfixOperatorNameParserRuleCall());

            }
            pushFollow(FollowSets000.FOLLOW_2);
            this_EssentialOCLInfixOperatorName_0=ruleEssentialOCLInfixOperatorName();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              		current.merge(this_EssentialOCLInfixOperatorName_0);

            }
            if ( state.backtracking==0 ) {

                      afterParserOrEnumRuleCall();

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
    // $ANTLR end "ruleInfixOperatorName"


    // $ANTLR start "entryRuleUnaryOperatorName"
    // InternalCompleteOCL.g:2822:1: entryRuleUnaryOperatorName returns [String current=null] : iv_ruleUnaryOperatorName= ruleUnaryOperatorName EOF ;
    public final String entryRuleUnaryOperatorName() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleUnaryOperatorName = null;


        try {
            // InternalCompleteOCL.g:2823:2: (iv_ruleUnaryOperatorName= ruleUnaryOperatorName EOF )
            // InternalCompleteOCL.g:2824:2: iv_ruleUnaryOperatorName= ruleUnaryOperatorName EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getUnaryOperatorNameRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleUnaryOperatorName=ruleUnaryOperatorName();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleUnaryOperatorName.getText();
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
    // $ANTLR end "entryRuleUnaryOperatorName"


    // $ANTLR start "ruleUnaryOperatorName"
    // InternalCompleteOCL.g:2831:1: ruleUnaryOperatorName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_EssentialOCLUnaryOperatorName_0= ruleEssentialOCLUnaryOperatorName ;
    public final AntlrDatatypeRuleToken ruleUnaryOperatorName() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        AntlrDatatypeRuleToken this_EssentialOCLUnaryOperatorName_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:2834:28: (this_EssentialOCLUnaryOperatorName_0= ruleEssentialOCLUnaryOperatorName )
            // InternalCompleteOCL.g:2836:5: this_EssentialOCLUnaryOperatorName_0= ruleEssentialOCLUnaryOperatorName
            {
            if ( state.backtracking==0 ) {

                      newCompositeNode(grammarAccess.getUnaryOperatorNameAccess().getEssentialOCLUnaryOperatorNameParserRuleCall());

            }
            pushFollow(FollowSets000.FOLLOW_2);
            this_EssentialOCLUnaryOperatorName_0=ruleEssentialOCLUnaryOperatorName();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              		current.merge(this_EssentialOCLUnaryOperatorName_0);

            }
            if ( state.backtracking==0 ) {

                      afterParserOrEnumRuleCall();

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
    // $ANTLR end "ruleUnaryOperatorName"


    // $ANTLR start "entryRuleEssentialOCLUnrestrictedName"
    // InternalCompleteOCL.g:2854:1: entryRuleEssentialOCLUnrestrictedName returns [String current=null] : iv_ruleEssentialOCLUnrestrictedName= ruleEssentialOCLUnrestrictedName EOF ;
    public final String entryRuleEssentialOCLUnrestrictedName() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleEssentialOCLUnrestrictedName = null;


        try {
            // InternalCompleteOCL.g:2855:2: (iv_ruleEssentialOCLUnrestrictedName= ruleEssentialOCLUnrestrictedName EOF )
            // InternalCompleteOCL.g:2856:2: iv_ruleEssentialOCLUnrestrictedName= ruleEssentialOCLUnrestrictedName EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getEssentialOCLUnrestrictedNameRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleEssentialOCLUnrestrictedName=ruleEssentialOCLUnrestrictedName();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleEssentialOCLUnrestrictedName.getText();
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
    // $ANTLR end "entryRuleEssentialOCLUnrestrictedName"


    // $ANTLR start "ruleEssentialOCLUnrestrictedName"
    // InternalCompleteOCL.g:2863:1: ruleEssentialOCLUnrestrictedName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_Identifier_0= ruleIdentifier ;
    public final AntlrDatatypeRuleToken ruleEssentialOCLUnrestrictedName() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        AntlrDatatypeRuleToken this_Identifier_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:2866:28: (this_Identifier_0= ruleIdentifier )
            // InternalCompleteOCL.g:2868:5: this_Identifier_0= ruleIdentifier
            {
            if ( state.backtracking==0 ) {

                      newCompositeNode(grammarAccess.getEssentialOCLUnrestrictedNameAccess().getIdentifierParserRuleCall());

            }
            pushFollow(FollowSets000.FOLLOW_2);
            this_Identifier_0=ruleIdentifier();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              		current.merge(this_Identifier_0);

            }
            if ( state.backtracking==0 ) {

                      afterParserOrEnumRuleCall();

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
    // $ANTLR end "ruleEssentialOCLUnrestrictedName"


    // $ANTLR start "entryRuleEssentialOCLUnreservedName"
    // InternalCompleteOCL.g:2886:1: entryRuleEssentialOCLUnreservedName returns [String current=null] : iv_ruleEssentialOCLUnreservedName= ruleEssentialOCLUnreservedName EOF ;
    public final String entryRuleEssentialOCLUnreservedName() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleEssentialOCLUnreservedName = null;


        try {
            // InternalCompleteOCL.g:2887:2: (iv_ruleEssentialOCLUnreservedName= ruleEssentialOCLUnreservedName EOF )
            // InternalCompleteOCL.g:2888:2: iv_ruleEssentialOCLUnreservedName= ruleEssentialOCLUnreservedName EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getEssentialOCLUnreservedNameRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleEssentialOCLUnreservedName=ruleEssentialOCLUnreservedName();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleEssentialOCLUnreservedName.getText();
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
    // $ANTLR end "entryRuleEssentialOCLUnreservedName"


    // $ANTLR start "ruleEssentialOCLUnreservedName"
    // InternalCompleteOCL.g:2895:1: ruleEssentialOCLUnreservedName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_UnrestrictedName_0= ruleUnrestrictedName | this_CollectionTypeIdentifier_1= ruleCollectionTypeIdentifier | this_PrimitiveTypeIdentifier_2= rulePrimitiveTypeIdentifier | kw= 'Map' | kw= 'Tuple' ) ;
    public final AntlrDatatypeRuleToken ruleEssentialOCLUnreservedName() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        AntlrDatatypeRuleToken this_UnrestrictedName_0 = null;

        AntlrDatatypeRuleToken this_CollectionTypeIdentifier_1 = null;

        AntlrDatatypeRuleToken this_PrimitiveTypeIdentifier_2 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:2898:28: ( (this_UnrestrictedName_0= ruleUnrestrictedName | this_CollectionTypeIdentifier_1= ruleCollectionTypeIdentifier | this_PrimitiveTypeIdentifier_2= rulePrimitiveTypeIdentifier | kw= 'Map' | kw= 'Tuple' ) )
            // InternalCompleteOCL.g:2899:1: (this_UnrestrictedName_0= ruleUnrestrictedName | this_CollectionTypeIdentifier_1= ruleCollectionTypeIdentifier | this_PrimitiveTypeIdentifier_2= rulePrimitiveTypeIdentifier | kw= 'Map' | kw= 'Tuple' )
            {
            // InternalCompleteOCL.g:2899:1: (this_UnrestrictedName_0= ruleUnrestrictedName | this_CollectionTypeIdentifier_1= ruleCollectionTypeIdentifier | this_PrimitiveTypeIdentifier_2= rulePrimitiveTypeIdentifier | kw= 'Map' | kw= 'Tuple' )
            int alt68=5;
            switch ( input.LA(1) ) {
            case RULE_SIMPLE_ID:
            case RULE_ESCAPED_ID:
            case 31:
            case 32:
            case 33:
                {
                alt68=1;
                }
                break;
            case 77:
            case 78:
            case 79:
            case 80:
            case 81:
                {
                alt68=2;
                }
                break;
            case 43:
            case 44:
            case 45:
            case 46:
            case 47:
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
                {
                alt68=3;
                }
                break;
            case 74:
                {
                alt68=4;
                }
                break;
            case 75:
                {
                alt68=5;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 68, 0, input);

                throw nvae;
            }

            switch (alt68) {
                case 1 :
                    // InternalCompleteOCL.g:2900:5: this_UnrestrictedName_0= ruleUnrestrictedName
                    {
                    if ( state.backtracking==0 ) {

                              newCompositeNode(grammarAccess.getEssentialOCLUnreservedNameAccess().getUnrestrictedNameParserRuleCall_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_UnrestrictedName_0=ruleUnrestrictedName();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      		current.merge(this_UnrestrictedName_0);

                    }
                    if ( state.backtracking==0 ) {

                              afterParserOrEnumRuleCall();

                    }

                    }
                    break;
                case 2 :
                    // InternalCompleteOCL.g:2912:5: this_CollectionTypeIdentifier_1= ruleCollectionTypeIdentifier
                    {
                    if ( state.backtracking==0 ) {

                              newCompositeNode(grammarAccess.getEssentialOCLUnreservedNameAccess().getCollectionTypeIdentifierParserRuleCall_1());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_CollectionTypeIdentifier_1=ruleCollectionTypeIdentifier();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      		current.merge(this_CollectionTypeIdentifier_1);

                    }
                    if ( state.backtracking==0 ) {

                              afterParserOrEnumRuleCall();

                    }

                    }
                    break;
                case 3 :
                    // InternalCompleteOCL.g:2924:5: this_PrimitiveTypeIdentifier_2= rulePrimitiveTypeIdentifier
                    {
                    if ( state.backtracking==0 ) {

                              newCompositeNode(grammarAccess.getEssentialOCLUnreservedNameAccess().getPrimitiveTypeIdentifierParserRuleCall_2());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_PrimitiveTypeIdentifier_2=rulePrimitiveTypeIdentifier();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      		current.merge(this_PrimitiveTypeIdentifier_2);

                    }
                    if ( state.backtracking==0 ) {

                              afterParserOrEnumRuleCall();

                    }

                    }
                    break;
                case 4 :
                    // InternalCompleteOCL.g:2936:2: kw= 'Map'
                    {
                    kw=(Token)match(input,74,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getEssentialOCLUnreservedNameAccess().getMapKeyword_3());

                    }

                    }
                    break;
                case 5 :
                    // InternalCompleteOCL.g:2943:2: kw= 'Tuple'
                    {
                    kw=(Token)match(input,75,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getEssentialOCLUnreservedNameAccess().getTupleKeyword_4());

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
    // $ANTLR end "ruleEssentialOCLUnreservedName"


    // $ANTLR start "entryRuleUnreservedName"
    // InternalCompleteOCL.g:2956:1: entryRuleUnreservedName returns [String current=null] : iv_ruleUnreservedName= ruleUnreservedName EOF ;
    public final String entryRuleUnreservedName() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleUnreservedName = null;


        try {
            // InternalCompleteOCL.g:2957:2: (iv_ruleUnreservedName= ruleUnreservedName EOF )
            // InternalCompleteOCL.g:2958:2: iv_ruleUnreservedName= ruleUnreservedName EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getUnreservedNameRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleUnreservedName=ruleUnreservedName();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleUnreservedName.getText();
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
    // $ANTLR end "entryRuleUnreservedName"


    // $ANTLR start "ruleUnreservedName"
    // InternalCompleteOCL.g:2965:1: ruleUnreservedName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_EssentialOCLUnreservedName_0= ruleEssentialOCLUnreservedName ;
    public final AntlrDatatypeRuleToken ruleUnreservedName() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        AntlrDatatypeRuleToken this_EssentialOCLUnreservedName_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:2968:28: (this_EssentialOCLUnreservedName_0= ruleEssentialOCLUnreservedName )
            // InternalCompleteOCL.g:2970:5: this_EssentialOCLUnreservedName_0= ruleEssentialOCLUnreservedName
            {
            if ( state.backtracking==0 ) {

                      newCompositeNode(grammarAccess.getUnreservedNameAccess().getEssentialOCLUnreservedNameParserRuleCall());

            }
            pushFollow(FollowSets000.FOLLOW_2);
            this_EssentialOCLUnreservedName_0=ruleEssentialOCLUnreservedName();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              		current.merge(this_EssentialOCLUnreservedName_0);

            }
            if ( state.backtracking==0 ) {

                      afterParserOrEnumRuleCall();

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
    // $ANTLR end "ruleUnreservedName"


    // $ANTLR start "entryRuleURIPathNameCS"
    // InternalCompleteOCL.g:2988:1: entryRuleURIPathNameCS returns [EObject current=null] : iv_ruleURIPathNameCS= ruleURIPathNameCS EOF ;
    public final EObject entryRuleURIPathNameCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleURIPathNameCS = null;


        try {
            // InternalCompleteOCL.g:2989:2: (iv_ruleURIPathNameCS= ruleURIPathNameCS EOF )
            // InternalCompleteOCL.g:2990:2: iv_ruleURIPathNameCS= ruleURIPathNameCS EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getURIPathNameCSRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleURIPathNameCS=ruleURIPathNameCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleURIPathNameCS;
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
    // $ANTLR end "entryRuleURIPathNameCS"


    // $ANTLR start "ruleURIPathNameCS"
    // InternalCompleteOCL.g:2997:1: ruleURIPathNameCS returns [EObject current=null] : ( ( (lv_ownedPathElements_0_0= ruleURIFirstPathElementCS ) ) (otherlv_1= '::' ( (lv_ownedPathElements_2_0= ruleNextPathElementCS ) ) )* ) ;
    public final EObject ruleURIPathNameCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject lv_ownedPathElements_0_0 = null;

        EObject lv_ownedPathElements_2_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:3000:28: ( ( ( (lv_ownedPathElements_0_0= ruleURIFirstPathElementCS ) ) (otherlv_1= '::' ( (lv_ownedPathElements_2_0= ruleNextPathElementCS ) ) )* ) )
            // InternalCompleteOCL.g:3001:1: ( ( (lv_ownedPathElements_0_0= ruleURIFirstPathElementCS ) ) (otherlv_1= '::' ( (lv_ownedPathElements_2_0= ruleNextPathElementCS ) ) )* )
            {
            // InternalCompleteOCL.g:3001:1: ( ( (lv_ownedPathElements_0_0= ruleURIFirstPathElementCS ) ) (otherlv_1= '::' ( (lv_ownedPathElements_2_0= ruleNextPathElementCS ) ) )* )
            // InternalCompleteOCL.g:3001:2: ( (lv_ownedPathElements_0_0= ruleURIFirstPathElementCS ) ) (otherlv_1= '::' ( (lv_ownedPathElements_2_0= ruleNextPathElementCS ) ) )*
            {
            // InternalCompleteOCL.g:3001:2: ( (lv_ownedPathElements_0_0= ruleURIFirstPathElementCS ) )
            // InternalCompleteOCL.g:3002:1: (lv_ownedPathElements_0_0= ruleURIFirstPathElementCS )
            {
            // InternalCompleteOCL.g:3002:1: (lv_ownedPathElements_0_0= ruleURIFirstPathElementCS )
            // InternalCompleteOCL.g:3003:3: lv_ownedPathElements_0_0= ruleURIFirstPathElementCS
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getURIPathNameCSAccess().getOwnedPathElementsURIFirstPathElementCSParserRuleCall_0_0());

            }
            pushFollow(FollowSets000.FOLLOW_40);
            lv_ownedPathElements_0_0=ruleURIFirstPathElementCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getURIPathNameCSRule());
              	        }
                     		add(
                     			current,
                     			"ownedPathElements",
                      		lv_ownedPathElements_0_0,
                      		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.URIFirstPathElementCS");
              	        afterParserOrEnumRuleCall();

            }

            }


            }

            // InternalCompleteOCL.g:3019:2: (otherlv_1= '::' ( (lv_ownedPathElements_2_0= ruleNextPathElementCS ) ) )*
            loop69:
            do {
                int alt69=2;
                int LA69_0 = input.LA(1);

                if ( (LA69_0==76) ) {
                    alt69=1;
                }


                switch (alt69) {
            	case 1 :
            	    // InternalCompleteOCL.g:3019:4: otherlv_1= '::' ( (lv_ownedPathElements_2_0= ruleNextPathElementCS ) )
            	    {
            	    otherlv_1=(Token)match(input,76,FollowSets000.FOLLOW_41); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_1, grammarAccess.getURIPathNameCSAccess().getColonColonKeyword_1_0());

            	    }
            	    // InternalCompleteOCL.g:3023:1: ( (lv_ownedPathElements_2_0= ruleNextPathElementCS ) )
            	    // InternalCompleteOCL.g:3024:1: (lv_ownedPathElements_2_0= ruleNextPathElementCS )
            	    {
            	    // InternalCompleteOCL.g:3024:1: (lv_ownedPathElements_2_0= ruleNextPathElementCS )
            	    // InternalCompleteOCL.g:3025:3: lv_ownedPathElements_2_0= ruleNextPathElementCS
            	    {
            	    if ( state.backtracking==0 ) {

            	      	        newCompositeNode(grammarAccess.getURIPathNameCSAccess().getOwnedPathElementsNextPathElementCSParserRuleCall_1_1_0());

            	    }
            	    pushFollow(FollowSets000.FOLLOW_40);
            	    lv_ownedPathElements_2_0=ruleNextPathElementCS();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getURIPathNameCSRule());
            	      	        }
            	             		add(
            	             			current,
            	             			"ownedPathElements",
            	              		lv_ownedPathElements_2_0,
            	              		"org.eclipse.ocl.xtext.base.Base.NextPathElementCS");
            	      	        afterParserOrEnumRuleCall();

            	    }

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop69;
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
    // $ANTLR end "ruleURIPathNameCS"


    // $ANTLR start "entryRuleURIFirstPathElementCS"
    // InternalCompleteOCL.g:3049:1: entryRuleURIFirstPathElementCS returns [EObject current=null] : iv_ruleURIFirstPathElementCS= ruleURIFirstPathElementCS EOF ;
    public final EObject entryRuleURIFirstPathElementCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleURIFirstPathElementCS = null;


        try {
            // InternalCompleteOCL.g:3050:2: (iv_ruleURIFirstPathElementCS= ruleURIFirstPathElementCS EOF )
            // InternalCompleteOCL.g:3051:2: iv_ruleURIFirstPathElementCS= ruleURIFirstPathElementCS EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getURIFirstPathElementCSRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleURIFirstPathElementCS=ruleURIFirstPathElementCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleURIFirstPathElementCS;
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
    // $ANTLR end "entryRuleURIFirstPathElementCS"


    // $ANTLR start "ruleURIFirstPathElementCS"
    // InternalCompleteOCL.g:3058:1: ruleURIFirstPathElementCS returns [EObject current=null] : ( ( ( ruleUnrestrictedName ) ) | ( () ( ( ruleURI ) ) ) ) ;
    public final EObject ruleURIFirstPathElementCS() throws RecognitionException {
        EObject current = null;

         enterRule();

        try {
            // InternalCompleteOCL.g:3061:28: ( ( ( ( ruleUnrestrictedName ) ) | ( () ( ( ruleURI ) ) ) ) )
            // InternalCompleteOCL.g:3062:1: ( ( ( ruleUnrestrictedName ) ) | ( () ( ( ruleURI ) ) ) )
            {
            // InternalCompleteOCL.g:3062:1: ( ( ( ruleUnrestrictedName ) ) | ( () ( ( ruleURI ) ) ) )
            int alt70=2;
            int LA70_0 = input.LA(1);

            if ( ((LA70_0>=RULE_SIMPLE_ID && LA70_0<=RULE_ESCAPED_ID)||(LA70_0>=31 && LA70_0<=33)) ) {
                alt70=1;
            }
            else if ( (LA70_0==RULE_SINGLE_QUOTED_STRING) ) {
                alt70=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 70, 0, input);

                throw nvae;
            }
            switch (alt70) {
                case 1 :
                    // InternalCompleteOCL.g:3062:2: ( ( ruleUnrestrictedName ) )
                    {
                    // InternalCompleteOCL.g:3062:2: ( ( ruleUnrestrictedName ) )
                    // InternalCompleteOCL.g:3063:1: ( ruleUnrestrictedName )
                    {
                    // InternalCompleteOCL.g:3063:1: ( ruleUnrestrictedName )
                    // InternalCompleteOCL.g:3064:3: ruleUnrestrictedName
                    {
                    if ( state.backtracking==0 ) {

                      		  /* */

                    }
                    if ( state.backtracking==0 ) {

                      			if (current==null) {
                      	            current = createModelElement(grammarAccess.getURIFirstPathElementCSRule());
                      	        }

                    }
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getURIFirstPathElementCSAccess().getReferredElementNamedElementCrossReference_0_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    ruleUnrestrictedName();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        afterParserOrEnumRuleCall();

                    }

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalCompleteOCL.g:3081:6: ( () ( ( ruleURI ) ) )
                    {
                    // InternalCompleteOCL.g:3081:6: ( () ( ( ruleURI ) ) )
                    // InternalCompleteOCL.g:3081:7: () ( ( ruleURI ) )
                    {
                    // InternalCompleteOCL.g:3081:7: ()
                    // InternalCompleteOCL.g:3082:2:
                    {
                    if ( state.backtracking==0 ) {

                      	  /* */

                    }
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElement(
                                  grammarAccess.getURIFirstPathElementCSAccess().getPathElementWithURICSAction_1_0(),
                                  current);

                    }

                    }

                    // InternalCompleteOCL.g:3090:2: ( ( ruleURI ) )
                    // InternalCompleteOCL.g:3091:1: ( ruleURI )
                    {
                    // InternalCompleteOCL.g:3091:1: ( ruleURI )
                    // InternalCompleteOCL.g:3092:3: ruleURI
                    {
                    if ( state.backtracking==0 ) {

                      		  /* */

                    }
                    if ( state.backtracking==0 ) {

                      			if (current==null) {
                      	            current = createModelElement(grammarAccess.getURIFirstPathElementCSRule());
                      	        }

                    }
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getURIFirstPathElementCSAccess().getReferredElementNamespaceCrossReference_1_1_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    ruleURI();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        afterParserOrEnumRuleCall();

                    }

                    }


                    }


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
    // $ANTLR end "ruleURIFirstPathElementCS"


    // $ANTLR start "entryRulePrimitiveTypeCS"
    // InternalCompleteOCL.g:3118:1: entryRulePrimitiveTypeCS returns [EObject current=null] : iv_rulePrimitiveTypeCS= rulePrimitiveTypeCS EOF ;
    public final EObject entryRulePrimitiveTypeCS() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePrimitiveTypeCS = null;


        try {
            // InternalCompleteOCL.g:3119:2: (iv_rulePrimitiveTypeCS= rulePrimitiveTypeCS EOF )
            // InternalCompleteOCL.g:3120:2: iv_rulePrimitiveTypeCS= rulePrimitiveTypeCS EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getPrimitiveTypeCSRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_rulePrimitiveTypeCS=rulePrimitiveTypeCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_rulePrimitiveTypeCS;
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
    // $ANTLR end "entryRulePrimitiveTypeCS"


    // $ANTLR start "rulePrimitiveTypeCS"
    // InternalCompleteOCL.g:3127:1: rulePrimitiveTypeCS returns [EObject current=null] : ( (lv_name_0_0= rulePrimitiveTypeIdentifier ) ) ;
    public final EObject rulePrimitiveTypeCS() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_name_0_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:3130:28: ( ( (lv_name_0_0= rulePrimitiveTypeIdentifier ) ) )
            // InternalCompleteOCL.g:3131:1: ( (lv_name_0_0= rulePrimitiveTypeIdentifier ) )
            {
            // InternalCompleteOCL.g:3131:1: ( (lv_name_0_0= rulePrimitiveTypeIdentifier ) )
            // InternalCompleteOCL.g:3132:1: (lv_name_0_0= rulePrimitiveTypeIdentifier )
            {
            // InternalCompleteOCL.g:3132:1: (lv_name_0_0= rulePrimitiveTypeIdentifier )
            // InternalCompleteOCL.g:3133:3: lv_name_0_0= rulePrimitiveTypeIdentifier
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getPrimitiveTypeCSAccess().getNamePrimitiveTypeIdentifierParserRuleCall_0());

            }
            pushFollow(FollowSets000.FOLLOW_2);
            lv_name_0_0=rulePrimitiveTypeIdentifier();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getPrimitiveTypeCSRule());
              	        }
                     		set(
                     			current,
                     			"name",
                      		lv_name_0_0,
                      		"org.eclipse.ocl.xtext.completeocl.CompleteOCL.PrimitiveTypeIdentifier");
              	        afterParserOrEnumRuleCall();

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
    // $ANTLR end "rulePrimitiveTypeCS"


    // $ANTLR start "entryRuleCollectionTypeIdentifier"
    // InternalCompleteOCL.g:3157:1: entryRuleCollectionTypeIdentifier returns [String current=null] : iv_ruleCollectionTypeIdentifier= ruleCollectionTypeIdentifier EOF ;
    public final String entryRuleCollectionTypeIdentifier() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleCollectionTypeIdentifier = null;


        try {
            // InternalCompleteOCL.g:3158:2: (iv_ruleCollectionTypeIdentifier= ruleCollectionTypeIdentifier EOF )
            // InternalCompleteOCL.g:3159:2: iv_ruleCollectionTypeIdentifier= ruleCollectionTypeIdentifier EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getCollectionTypeIdentifierRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleCollectionTypeIdentifier=ruleCollectionTypeIdentifier();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleCollectionTypeIdentifier.getText();
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
    // $ANTLR end "entryRuleCollectionTypeIdentifier"


    // $ANTLR start "ruleCollectionTypeIdentifier"
    // InternalCompleteOCL.g:3166:1: ruleCollectionTypeIdentifier returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= 'Set' | kw= 'Bag' | kw= 'Sequence' | kw= 'Collection' | kw= 'OrderedSet' ) ;
    public final AntlrDatatypeRuleToken ruleCollectionTypeIdentifier() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule();

        try {
            // InternalCompleteOCL.g:3169:28: ( (kw= 'Set' | kw= 'Bag' | kw= 'Sequence' | kw= 'Collection' | kw= 'OrderedSet' ) )
            // InternalCompleteOCL.g:3170:1: (kw= 'Set' | kw= 'Bag' | kw= 'Sequence' | kw= 'Collection' | kw= 'OrderedSet' )
            {
            // InternalCompleteOCL.g:3170:1: (kw= 'Set' | kw= 'Bag' | kw= 'Sequence' | kw= 'Collection' | kw= 'OrderedSet' )
            int alt71=5;
            switch ( input.LA(1) ) {
            case 77:
                {
                alt71=1;
                }
                break;
            case 78:
                {
                alt71=2;
                }
                break;
            case 79:
                {
                alt71=3;
                }
                break;
            case 80:
                {
                alt71=4;
                }
                break;
            case 81:
                {
                alt71=5;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 71, 0, input);

                throw nvae;
            }

            switch (alt71) {
                case 1 :
                    // InternalCompleteOCL.g:3171:2: kw= 'Set'
                    {
                    kw=(Token)match(input,77,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getCollectionTypeIdentifierAccess().getSetKeyword_0());

                    }

                    }
                    break;
                case 2 :
                    // InternalCompleteOCL.g:3178:2: kw= 'Bag'
                    {
                    kw=(Token)match(input,78,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getCollectionTypeIdentifierAccess().getBagKeyword_1());

                    }

                    }
                    break;
                case 3 :
                    // InternalCompleteOCL.g:3185:2: kw= 'Sequence'
                    {
                    kw=(Token)match(input,79,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getCollectionTypeIdentifierAccess().getSequenceKeyword_2());

                    }

                    }
                    break;
                case 4 :
                    // InternalCompleteOCL.g:3192:2: kw= 'Collection'
                    {
                    kw=(Token)match(input,80,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getCollectionTypeIdentifierAccess().getCollectionKeyword_3());

                    }

                    }
                    break;
                case 5 :
                    // InternalCompleteOCL.g:3199:2: kw= 'OrderedSet'
                    {
                    kw=(Token)match(input,81,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getCollectionTypeIdentifierAccess().getOrderedSetKeyword_4());

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
    // $ANTLR end "ruleCollectionTypeIdentifier"


    // $ANTLR start "entryRuleCollectionTypeCS"
    // InternalCompleteOCL.g:3212:1: entryRuleCollectionTypeCS returns [EObject current=null] : iv_ruleCollectionTypeCS= ruleCollectionTypeCS EOF ;
    public final EObject entryRuleCollectionTypeCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCollectionTypeCS = null;


        try {
            // InternalCompleteOCL.g:3213:2: (iv_ruleCollectionTypeCS= ruleCollectionTypeCS EOF )
            // InternalCompleteOCL.g:3214:2: iv_ruleCollectionTypeCS= ruleCollectionTypeCS EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getCollectionTypeCSRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleCollectionTypeCS=ruleCollectionTypeCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleCollectionTypeCS;
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
    // $ANTLR end "entryRuleCollectionTypeCS"


    // $ANTLR start "ruleCollectionTypeCS"
    // InternalCompleteOCL.g:3221:1: ruleCollectionTypeCS returns [EObject current=null] : ( ( (lv_name_0_0= ruleCollectionTypeIdentifier ) ) (otherlv_1= '(' ( (lv_ownedType_2_0= ruleTypeExpWithoutMultiplicityCS ) ) ( (lv_ownedCollectionMultiplicity_3_0= ruleMultiplicityCS ) )? otherlv_4= ')' )? ) ;
    public final EObject ruleCollectionTypeCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_4=null;
        AntlrDatatypeRuleToken lv_name_0_0 = null;

        EObject lv_ownedType_2_0 = null;

        EObject lv_ownedCollectionMultiplicity_3_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:3224:28: ( ( ( (lv_name_0_0= ruleCollectionTypeIdentifier ) ) (otherlv_1= '(' ( (lv_ownedType_2_0= ruleTypeExpWithoutMultiplicityCS ) ) ( (lv_ownedCollectionMultiplicity_3_0= ruleMultiplicityCS ) )? otherlv_4= ')' )? ) )
            // InternalCompleteOCL.g:3225:1: ( ( (lv_name_0_0= ruleCollectionTypeIdentifier ) ) (otherlv_1= '(' ( (lv_ownedType_2_0= ruleTypeExpWithoutMultiplicityCS ) ) ( (lv_ownedCollectionMultiplicity_3_0= ruleMultiplicityCS ) )? otherlv_4= ')' )? )
            {
            // InternalCompleteOCL.g:3225:1: ( ( (lv_name_0_0= ruleCollectionTypeIdentifier ) ) (otherlv_1= '(' ( (lv_ownedType_2_0= ruleTypeExpWithoutMultiplicityCS ) ) ( (lv_ownedCollectionMultiplicity_3_0= ruleMultiplicityCS ) )? otherlv_4= ')' )? )
            // InternalCompleteOCL.g:3225:2: ( (lv_name_0_0= ruleCollectionTypeIdentifier ) ) (otherlv_1= '(' ( (lv_ownedType_2_0= ruleTypeExpWithoutMultiplicityCS ) ) ( (lv_ownedCollectionMultiplicity_3_0= ruleMultiplicityCS ) )? otherlv_4= ')' )?
            {
            // InternalCompleteOCL.g:3225:2: ( (lv_name_0_0= ruleCollectionTypeIdentifier ) )
            // InternalCompleteOCL.g:3226:1: (lv_name_0_0= ruleCollectionTypeIdentifier )
            {
            // InternalCompleteOCL.g:3226:1: (lv_name_0_0= ruleCollectionTypeIdentifier )
            // InternalCompleteOCL.g:3227:3: lv_name_0_0= ruleCollectionTypeIdentifier
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getCollectionTypeCSAccess().getNameCollectionTypeIdentifierParserRuleCall_0_0());

            }
            pushFollow(FollowSets000.FOLLOW_42);
            lv_name_0_0=ruleCollectionTypeIdentifier();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getCollectionTypeCSRule());
              	        }
                     		set(
                     			current,
                     			"name",
                      		lv_name_0_0,
                      		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.CollectionTypeIdentifier");
              	        afterParserOrEnumRuleCall();

            }

            }


            }

            // InternalCompleteOCL.g:3243:2: (otherlv_1= '(' ( (lv_ownedType_2_0= ruleTypeExpWithoutMultiplicityCS ) ) ( (lv_ownedCollectionMultiplicity_3_0= ruleMultiplicityCS ) )? otherlv_4= ')' )?
            int alt73=2;
            int LA73_0 = input.LA(1);

            if ( (LA73_0==22) ) {
                alt73=1;
            }
            switch (alt73) {
                case 1 :
                    // InternalCompleteOCL.g:3243:4: otherlv_1= '(' ( (lv_ownedType_2_0= ruleTypeExpWithoutMultiplicityCS ) ) ( (lv_ownedCollectionMultiplicity_3_0= ruleMultiplicityCS ) )? otherlv_4= ')'
                    {
                    otherlv_1=(Token)match(input,22,FollowSets000.FOLLOW_25); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_1, grammarAccess.getCollectionTypeCSAccess().getLeftParenthesisKeyword_1_0());

                    }
                    // InternalCompleteOCL.g:3247:1: ( (lv_ownedType_2_0= ruleTypeExpWithoutMultiplicityCS ) )
                    // InternalCompleteOCL.g:3248:1: (lv_ownedType_2_0= ruleTypeExpWithoutMultiplicityCS )
                    {
                    // InternalCompleteOCL.g:3248:1: (lv_ownedType_2_0= ruleTypeExpWithoutMultiplicityCS )
                    // InternalCompleteOCL.g:3249:3: lv_ownedType_2_0= ruleTypeExpWithoutMultiplicityCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getCollectionTypeCSAccess().getOwnedTypeTypeExpWithoutMultiplicityCSParserRuleCall_1_1_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_43);
                    lv_ownedType_2_0=ruleTypeExpWithoutMultiplicityCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getCollectionTypeCSRule());
                      	        }
                             		set(
                             			current,
                             			"ownedType",
                              		lv_ownedType_2_0,
                              		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.TypeExpWithoutMultiplicityCS");
                      	        afterParserOrEnumRuleCall();

                    }

                    }


                    }

                    // InternalCompleteOCL.g:3265:2: ( (lv_ownedCollectionMultiplicity_3_0= ruleMultiplicityCS ) )?
                    int alt72=2;
                    int LA72_0 = input.LA(1);

                    if ( (LA72_0==93) ) {
                        alt72=1;
                    }
                    switch (alt72) {
                        case 1 :
                            // InternalCompleteOCL.g:3266:1: (lv_ownedCollectionMultiplicity_3_0= ruleMultiplicityCS )
                            {
                            // InternalCompleteOCL.g:3266:1: (lv_ownedCollectionMultiplicity_3_0= ruleMultiplicityCS )
                            // InternalCompleteOCL.g:3267:3: lv_ownedCollectionMultiplicity_3_0= ruleMultiplicityCS
                            {
                            if ( state.backtracking==0 ) {

                              	        newCompositeNode(grammarAccess.getCollectionTypeCSAccess().getOwnedCollectionMultiplicityMultiplicityCSParserRuleCall_1_2_0());

                            }
                            pushFollow(FollowSets000.FOLLOW_13);
                            lv_ownedCollectionMultiplicity_3_0=ruleMultiplicityCS();

                            state._fsp--;
                            if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElementForParent(grammarAccess.getCollectionTypeCSRule());
                              	        }
                                     		set(
                                     			current,
                                     			"ownedCollectionMultiplicity",
                                      		lv_ownedCollectionMultiplicity_3_0,
                                      		"org.eclipse.ocl.xtext.base.Base.MultiplicityCS");
                              	        afterParserOrEnumRuleCall();

                            }

                            }


                            }
                            break;

                    }

                    otherlv_4=(Token)match(input,23,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_4, grammarAccess.getCollectionTypeCSAccess().getRightParenthesisKeyword_1_3());

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
    // $ANTLR end "ruleCollectionTypeCS"


    // $ANTLR start "entryRuleMapTypeCS"
    // InternalCompleteOCL.g:3295:1: entryRuleMapTypeCS returns [EObject current=null] : iv_ruleMapTypeCS= ruleMapTypeCS EOF ;
    public final EObject entryRuleMapTypeCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMapTypeCS = null;


        try {
            // InternalCompleteOCL.g:3296:2: (iv_ruleMapTypeCS= ruleMapTypeCS EOF )
            // InternalCompleteOCL.g:3297:2: iv_ruleMapTypeCS= ruleMapTypeCS EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getMapTypeCSRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleMapTypeCS=ruleMapTypeCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleMapTypeCS;
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
    // $ANTLR end "entryRuleMapTypeCS"


    // $ANTLR start "ruleMapTypeCS"
    // InternalCompleteOCL.g:3304:1: ruleMapTypeCS returns [EObject current=null] : ( ( (lv_name_0_0= 'Map' ) ) (otherlv_1= '(' ( (lv_ownedKeyType_2_0= ruleTypeExpCS ) ) otherlv_3= ',' ( (lv_ownedValueType_4_0= ruleTypeExpCS ) ) otherlv_5= ')' )? ) ;
    public final EObject ruleMapTypeCS() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        EObject lv_ownedKeyType_2_0 = null;

        EObject lv_ownedValueType_4_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:3307:28: ( ( ( (lv_name_0_0= 'Map' ) ) (otherlv_1= '(' ( (lv_ownedKeyType_2_0= ruleTypeExpCS ) ) otherlv_3= ',' ( (lv_ownedValueType_4_0= ruleTypeExpCS ) ) otherlv_5= ')' )? ) )
            // InternalCompleteOCL.g:3308:1: ( ( (lv_name_0_0= 'Map' ) ) (otherlv_1= '(' ( (lv_ownedKeyType_2_0= ruleTypeExpCS ) ) otherlv_3= ',' ( (lv_ownedValueType_4_0= ruleTypeExpCS ) ) otherlv_5= ')' )? )
            {
            // InternalCompleteOCL.g:3308:1: ( ( (lv_name_0_0= 'Map' ) ) (otherlv_1= '(' ( (lv_ownedKeyType_2_0= ruleTypeExpCS ) ) otherlv_3= ',' ( (lv_ownedValueType_4_0= ruleTypeExpCS ) ) otherlv_5= ')' )? )
            // InternalCompleteOCL.g:3308:2: ( (lv_name_0_0= 'Map' ) ) (otherlv_1= '(' ( (lv_ownedKeyType_2_0= ruleTypeExpCS ) ) otherlv_3= ',' ( (lv_ownedValueType_4_0= ruleTypeExpCS ) ) otherlv_5= ')' )?
            {
            // InternalCompleteOCL.g:3308:2: ( (lv_name_0_0= 'Map' ) )
            // InternalCompleteOCL.g:3309:1: (lv_name_0_0= 'Map' )
            {
            // InternalCompleteOCL.g:3309:1: (lv_name_0_0= 'Map' )
            // InternalCompleteOCL.g:3310:3: lv_name_0_0= 'Map'
            {
            lv_name_0_0=(Token)match(input,74,FollowSets000.FOLLOW_42); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                      newLeafNode(lv_name_0_0, grammarAccess.getMapTypeCSAccess().getNameMapKeyword_0_0());

            }
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElement(grammarAccess.getMapTypeCSRule());
              	        }
                     		setWithLastConsumed(current, "name", lv_name_0_0, "Map");

            }

            }


            }

            // InternalCompleteOCL.g:3323:2: (otherlv_1= '(' ( (lv_ownedKeyType_2_0= ruleTypeExpCS ) ) otherlv_3= ',' ( (lv_ownedValueType_4_0= ruleTypeExpCS ) ) otherlv_5= ')' )?
            int alt74=2;
            int LA74_0 = input.LA(1);

            if ( (LA74_0==22) ) {
                alt74=1;
            }
            switch (alt74) {
                case 1 :
                    // InternalCompleteOCL.g:3323:4: otherlv_1= '(' ( (lv_ownedKeyType_2_0= ruleTypeExpCS ) ) otherlv_3= ',' ( (lv_ownedValueType_4_0= ruleTypeExpCS ) ) otherlv_5= ')'
                    {
                    otherlv_1=(Token)match(input,22,FollowSets000.FOLLOW_25); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_1, grammarAccess.getMapTypeCSAccess().getLeftParenthesisKeyword_1_0());

                    }
                    // InternalCompleteOCL.g:3327:1: ( (lv_ownedKeyType_2_0= ruleTypeExpCS ) )
                    // InternalCompleteOCL.g:3328:1: (lv_ownedKeyType_2_0= ruleTypeExpCS )
                    {
                    // InternalCompleteOCL.g:3328:1: (lv_ownedKeyType_2_0= ruleTypeExpCS )
                    // InternalCompleteOCL.g:3329:3: lv_ownedKeyType_2_0= ruleTypeExpCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getMapTypeCSAccess().getOwnedKeyTypeTypeExpCSParserRuleCall_1_1_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_44);
                    lv_ownedKeyType_2_0=ruleTypeExpCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getMapTypeCSRule());
                      	        }
                             		set(
                             			current,
                             			"ownedKeyType",
                              		lv_ownedKeyType_2_0,
                              		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.TypeExpCS");
                      	        afterParserOrEnumRuleCall();

                    }

                    }


                    }

                    otherlv_3=(Token)match(input,29,FollowSets000.FOLLOW_25); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_3, grammarAccess.getMapTypeCSAccess().getCommaKeyword_1_2());

                    }
                    // InternalCompleteOCL.g:3349:1: ( (lv_ownedValueType_4_0= ruleTypeExpCS ) )
                    // InternalCompleteOCL.g:3350:1: (lv_ownedValueType_4_0= ruleTypeExpCS )
                    {
                    // InternalCompleteOCL.g:3350:1: (lv_ownedValueType_4_0= ruleTypeExpCS )
                    // InternalCompleteOCL.g:3351:3: lv_ownedValueType_4_0= ruleTypeExpCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getMapTypeCSAccess().getOwnedValueTypeTypeExpCSParserRuleCall_1_3_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_13);
                    lv_ownedValueType_4_0=ruleTypeExpCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getMapTypeCSRule());
                      	        }
                             		set(
                             			current,
                             			"ownedValueType",
                              		lv_ownedValueType_4_0,
                              		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.TypeExpCS");
                      	        afterParserOrEnumRuleCall();

                    }

                    }


                    }

                    otherlv_5=(Token)match(input,23,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_5, grammarAccess.getMapTypeCSAccess().getRightParenthesisKeyword_1_4());

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
    // $ANTLR end "ruleMapTypeCS"


    // $ANTLR start "entryRuleTupleTypeCS"
    // InternalCompleteOCL.g:3379:1: entryRuleTupleTypeCS returns [EObject current=null] : iv_ruleTupleTypeCS= ruleTupleTypeCS EOF ;
    public final EObject entryRuleTupleTypeCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTupleTypeCS = null;


        try {
            // InternalCompleteOCL.g:3380:2: (iv_ruleTupleTypeCS= ruleTupleTypeCS EOF )
            // InternalCompleteOCL.g:3381:2: iv_ruleTupleTypeCS= ruleTupleTypeCS EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getTupleTypeCSRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleTupleTypeCS=ruleTupleTypeCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleTupleTypeCS;
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
    // $ANTLR end "entryRuleTupleTypeCS"


    // $ANTLR start "ruleTupleTypeCS"
    // InternalCompleteOCL.g:3388:1: ruleTupleTypeCS returns [EObject current=null] : ( ( (lv_name_0_0= 'Tuple' ) ) (otherlv_1= '(' ( ( (lv_ownedParts_2_0= ruleTuplePartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleTuplePartCS ) ) )* )? otherlv_5= ')' )? ) ;
    public final EObject ruleTupleTypeCS() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        EObject lv_ownedParts_2_0 = null;

        EObject lv_ownedParts_4_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:3391:28: ( ( ( (lv_name_0_0= 'Tuple' ) ) (otherlv_1= '(' ( ( (lv_ownedParts_2_0= ruleTuplePartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleTuplePartCS ) ) )* )? otherlv_5= ')' )? ) )
            // InternalCompleteOCL.g:3392:1: ( ( (lv_name_0_0= 'Tuple' ) ) (otherlv_1= '(' ( ( (lv_ownedParts_2_0= ruleTuplePartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleTuplePartCS ) ) )* )? otherlv_5= ')' )? )
            {
            // InternalCompleteOCL.g:3392:1: ( ( (lv_name_0_0= 'Tuple' ) ) (otherlv_1= '(' ( ( (lv_ownedParts_2_0= ruleTuplePartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleTuplePartCS ) ) )* )? otherlv_5= ')' )? )
            // InternalCompleteOCL.g:3392:2: ( (lv_name_0_0= 'Tuple' ) ) (otherlv_1= '(' ( ( (lv_ownedParts_2_0= ruleTuplePartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleTuplePartCS ) ) )* )? otherlv_5= ')' )?
            {
            // InternalCompleteOCL.g:3392:2: ( (lv_name_0_0= 'Tuple' ) )
            // InternalCompleteOCL.g:3393:1: (lv_name_0_0= 'Tuple' )
            {
            // InternalCompleteOCL.g:3393:1: (lv_name_0_0= 'Tuple' )
            // InternalCompleteOCL.g:3394:3: lv_name_0_0= 'Tuple'
            {
            lv_name_0_0=(Token)match(input,75,FollowSets000.FOLLOW_42); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                      newLeafNode(lv_name_0_0, grammarAccess.getTupleTypeCSAccess().getNameTupleKeyword_0_0());

            }
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElement(grammarAccess.getTupleTypeCSRule());
              	        }
                     		setWithLastConsumed(current, "name", lv_name_0_0, "Tuple");

            }

            }


            }

            // InternalCompleteOCL.g:3407:2: (otherlv_1= '(' ( ( (lv_ownedParts_2_0= ruleTuplePartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleTuplePartCS ) ) )* )? otherlv_5= ')' )?
            int alt77=2;
            int LA77_0 = input.LA(1);

            if ( (LA77_0==22) ) {
                alt77=1;
            }
            switch (alt77) {
                case 1 :
                    // InternalCompleteOCL.g:3407:4: otherlv_1= '(' ( ( (lv_ownedParts_2_0= ruleTuplePartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleTuplePartCS ) ) )* )? otherlv_5= ')'
                    {
                    otherlv_1=(Token)match(input,22,FollowSets000.FOLLOW_21); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_1, grammarAccess.getTupleTypeCSAccess().getLeftParenthesisKeyword_1_0());

                    }
                    // InternalCompleteOCL.g:3411:1: ( ( (lv_ownedParts_2_0= ruleTuplePartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleTuplePartCS ) ) )* )?
                    int alt76=2;
                    int LA76_0 = input.LA(1);

                    if ( ((LA76_0>=RULE_SIMPLE_ID && LA76_0<=RULE_ESCAPED_ID)||(LA76_0>=31 && LA76_0<=33)) ) {
                        alt76=1;
                    }
                    switch (alt76) {
                        case 1 :
                            // InternalCompleteOCL.g:3411:2: ( (lv_ownedParts_2_0= ruleTuplePartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleTuplePartCS ) ) )*
                            {
                            // InternalCompleteOCL.g:3411:2: ( (lv_ownedParts_2_0= ruleTuplePartCS ) )
                            // InternalCompleteOCL.g:3412:1: (lv_ownedParts_2_0= ruleTuplePartCS )
                            {
                            // InternalCompleteOCL.g:3412:1: (lv_ownedParts_2_0= ruleTuplePartCS )
                            // InternalCompleteOCL.g:3413:3: lv_ownedParts_2_0= ruleTuplePartCS
                            {
                            if ( state.backtracking==0 ) {

                              	        newCompositeNode(grammarAccess.getTupleTypeCSAccess().getOwnedPartsTuplePartCSParserRuleCall_1_1_0_0());

                            }
                            pushFollow(FollowSets000.FOLLOW_22);
                            lv_ownedParts_2_0=ruleTuplePartCS();

                            state._fsp--;
                            if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElementForParent(grammarAccess.getTupleTypeCSRule());
                              	        }
                                     		add(
                                     			current,
                                     			"ownedParts",
                                      		lv_ownedParts_2_0,
                                      		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.TuplePartCS");
                              	        afterParserOrEnumRuleCall();

                            }

                            }


                            }

                            // InternalCompleteOCL.g:3429:2: (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleTuplePartCS ) ) )*
                            loop75:
                            do {
                                int alt75=2;
                                int LA75_0 = input.LA(1);

                                if ( (LA75_0==29) ) {
                                    alt75=1;
                                }


                                switch (alt75) {
                            	case 1 :
                            	    // InternalCompleteOCL.g:3429:4: otherlv_3= ',' ( (lv_ownedParts_4_0= ruleTuplePartCS ) )
                            	    {
                            	    otherlv_3=(Token)match(input,29,FollowSets000.FOLLOW_19); if (state.failed) return current;
                            	    if ( state.backtracking==0 ) {

                            	          	newLeafNode(otherlv_3, grammarAccess.getTupleTypeCSAccess().getCommaKeyword_1_1_1_0());

                            	    }
                            	    // InternalCompleteOCL.g:3433:1: ( (lv_ownedParts_4_0= ruleTuplePartCS ) )
                            	    // InternalCompleteOCL.g:3434:1: (lv_ownedParts_4_0= ruleTuplePartCS )
                            	    {
                            	    // InternalCompleteOCL.g:3434:1: (lv_ownedParts_4_0= ruleTuplePartCS )
                            	    // InternalCompleteOCL.g:3435:3: lv_ownedParts_4_0= ruleTuplePartCS
                            	    {
                            	    if ( state.backtracking==0 ) {

                            	      	        newCompositeNode(grammarAccess.getTupleTypeCSAccess().getOwnedPartsTuplePartCSParserRuleCall_1_1_1_1_0());

                            	    }
                            	    pushFollow(FollowSets000.FOLLOW_22);
                            	    lv_ownedParts_4_0=ruleTuplePartCS();

                            	    state._fsp--;
                            	    if (state.failed) return current;
                            	    if ( state.backtracking==0 ) {

                            	      	        if (current==null) {
                            	      	            current = createModelElementForParent(grammarAccess.getTupleTypeCSRule());
                            	      	        }
                            	             		add(
                            	             			current,
                            	             			"ownedParts",
                            	              		lv_ownedParts_4_0,
                            	              		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.TuplePartCS");
                            	      	        afterParserOrEnumRuleCall();

                            	    }

                            	    }


                            	    }


                            	    }
                            	    break;

                            	default :
                            	    break loop75;
                                }
                            } while (true);


                            }
                            break;

                    }

                    otherlv_5=(Token)match(input,23,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_5, grammarAccess.getTupleTypeCSAccess().getRightParenthesisKeyword_1_2());

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
    // $ANTLR end "ruleTupleTypeCS"


    // $ANTLR start "entryRuleTuplePartCS"
    // InternalCompleteOCL.g:3463:1: entryRuleTuplePartCS returns [EObject current=null] : iv_ruleTuplePartCS= ruleTuplePartCS EOF ;
    public final EObject entryRuleTuplePartCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTuplePartCS = null;


        try {
            // InternalCompleteOCL.g:3464:2: (iv_ruleTuplePartCS= ruleTuplePartCS EOF )
            // InternalCompleteOCL.g:3465:2: iv_ruleTuplePartCS= ruleTuplePartCS EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getTuplePartCSRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleTuplePartCS=ruleTuplePartCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleTuplePartCS;
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
    // $ANTLR end "entryRuleTuplePartCS"


    // $ANTLR start "ruleTuplePartCS"
    // InternalCompleteOCL.g:3472:1: ruleTuplePartCS returns [EObject current=null] : ( ( (lv_name_0_0= ruleUnrestrictedName ) ) otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) ) ) ;
    public final EObject ruleTuplePartCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_name_0_0 = null;

        EObject lv_ownedType_2_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:3475:28: ( ( ( (lv_name_0_0= ruleUnrestrictedName ) ) otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) ) ) )
            // InternalCompleteOCL.g:3476:1: ( ( (lv_name_0_0= ruleUnrestrictedName ) ) otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) ) )
            {
            // InternalCompleteOCL.g:3476:1: ( ( (lv_name_0_0= ruleUnrestrictedName ) ) otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) ) )
            // InternalCompleteOCL.g:3476:2: ( (lv_name_0_0= ruleUnrestrictedName ) ) otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) )
            {
            // InternalCompleteOCL.g:3476:2: ( (lv_name_0_0= ruleUnrestrictedName ) )
            // InternalCompleteOCL.g:3477:1: (lv_name_0_0= ruleUnrestrictedName )
            {
            // InternalCompleteOCL.g:3477:1: (lv_name_0_0= ruleUnrestrictedName )
            // InternalCompleteOCL.g:3478:3: lv_name_0_0= ruleUnrestrictedName
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getTuplePartCSAccess().getNameUnrestrictedNameParserRuleCall_0_0());

            }
            pushFollow(FollowSets000.FOLLOW_14);
            lv_name_0_0=ruleUnrestrictedName();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getTuplePartCSRule());
              	        }
                     		set(
                     			current,
                     			"name",
                      		lv_name_0_0,
                      		"org.eclipse.ocl.xtext.completeocl.CompleteOCL.UnrestrictedName");
              	        afterParserOrEnumRuleCall();

            }

            }


            }

            otherlv_1=(Token)match(input,24,FollowSets000.FOLLOW_25); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getTuplePartCSAccess().getColonKeyword_1());

            }
            // InternalCompleteOCL.g:3498:1: ( (lv_ownedType_2_0= ruleTypeExpCS ) )
            // InternalCompleteOCL.g:3499:1: (lv_ownedType_2_0= ruleTypeExpCS )
            {
            // InternalCompleteOCL.g:3499:1: (lv_ownedType_2_0= ruleTypeExpCS )
            // InternalCompleteOCL.g:3500:3: lv_ownedType_2_0= ruleTypeExpCS
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getTuplePartCSAccess().getOwnedTypeTypeExpCSParserRuleCall_2_0());

            }
            pushFollow(FollowSets000.FOLLOW_2);
            lv_ownedType_2_0=ruleTypeExpCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getTuplePartCSRule());
              	        }
                     		set(
                     			current,
                     			"ownedType",
                      		lv_ownedType_2_0,
                      		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.TypeExpCS");
              	        afterParserOrEnumRuleCall();

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
    // $ANTLR end "ruleTuplePartCS"


    // $ANTLR start "entryRuleCollectionLiteralExpCS"
    // InternalCompleteOCL.g:3524:1: entryRuleCollectionLiteralExpCS returns [EObject current=null] : iv_ruleCollectionLiteralExpCS= ruleCollectionLiteralExpCS EOF ;
    public final EObject entryRuleCollectionLiteralExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCollectionLiteralExpCS = null;


        try {
            // InternalCompleteOCL.g:3525:2: (iv_ruleCollectionLiteralExpCS= ruleCollectionLiteralExpCS EOF )
            // InternalCompleteOCL.g:3526:2: iv_ruleCollectionLiteralExpCS= ruleCollectionLiteralExpCS EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getCollectionLiteralExpCSRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleCollectionLiteralExpCS=ruleCollectionLiteralExpCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleCollectionLiteralExpCS;
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
    // $ANTLR end "entryRuleCollectionLiteralExpCS"


    // $ANTLR start "ruleCollectionLiteralExpCS"
    // InternalCompleteOCL.g:3533:1: ruleCollectionLiteralExpCS returns [EObject current=null] : ( ( (lv_ownedType_0_0= ruleCollectionTypeCS ) ) otherlv_1= '{' ( ( (lv_ownedParts_2_0= ruleCollectionLiteralPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleCollectionLiteralPartCS ) ) )* )? otherlv_5= '}' ) ;
    public final EObject ruleCollectionLiteralExpCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        EObject lv_ownedType_0_0 = null;

        EObject lv_ownedParts_2_0 = null;

        EObject lv_ownedParts_4_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:3536:28: ( ( ( (lv_ownedType_0_0= ruleCollectionTypeCS ) ) otherlv_1= '{' ( ( (lv_ownedParts_2_0= ruleCollectionLiteralPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleCollectionLiteralPartCS ) ) )* )? otherlv_5= '}' ) )
            // InternalCompleteOCL.g:3537:1: ( ( (lv_ownedType_0_0= ruleCollectionTypeCS ) ) otherlv_1= '{' ( ( (lv_ownedParts_2_0= ruleCollectionLiteralPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleCollectionLiteralPartCS ) ) )* )? otherlv_5= '}' )
            {
            // InternalCompleteOCL.g:3537:1: ( ( (lv_ownedType_0_0= ruleCollectionTypeCS ) ) otherlv_1= '{' ( ( (lv_ownedParts_2_0= ruleCollectionLiteralPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleCollectionLiteralPartCS ) ) )* )? otherlv_5= '}' )
            // InternalCompleteOCL.g:3537:2: ( (lv_ownedType_0_0= ruleCollectionTypeCS ) ) otherlv_1= '{' ( ( (lv_ownedParts_2_0= ruleCollectionLiteralPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleCollectionLiteralPartCS ) ) )* )? otherlv_5= '}'
            {
            // InternalCompleteOCL.g:3537:2: ( (lv_ownedType_0_0= ruleCollectionTypeCS ) )
            // InternalCompleteOCL.g:3538:1: (lv_ownedType_0_0= ruleCollectionTypeCS )
            {
            // InternalCompleteOCL.g:3538:1: (lv_ownedType_0_0= ruleCollectionTypeCS )
            // InternalCompleteOCL.g:3539:3: lv_ownedType_0_0= ruleCollectionTypeCS
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getCollectionLiteralExpCSAccess().getOwnedTypeCollectionTypeCSParserRuleCall_0_0());

            }
            pushFollow(FollowSets000.FOLLOW_45);
            lv_ownedType_0_0=ruleCollectionTypeCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getCollectionLiteralExpCSRule());
              	        }
                     		set(
                     			current,
                     			"ownedType",
                      		lv_ownedType_0_0,
                      		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.CollectionTypeCS");
              	        afterParserOrEnumRuleCall();

            }

            }


            }

            otherlv_1=(Token)match(input,82,FollowSets000.FOLLOW_46); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getCollectionLiteralExpCSAccess().getLeftCurlyBracketKeyword_1());

            }
            // InternalCompleteOCL.g:3559:1: ( ( (lv_ownedParts_2_0= ruleCollectionLiteralPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleCollectionLiteralPartCS ) ) )* )?
            int alt79=2;
            int LA79_0 = input.LA(1);

            if ( ((LA79_0>=RULE_SIMPLE_ID && LA79_0<=RULE_SINGLE_QUOTED_STRING)||LA79_0==22||LA79_0==24||(LA79_0>=31 && LA79_0<=33)||(LA79_0>=43 && LA79_0<=56)||(LA79_0>=74 && LA79_0<=75)||(LA79_0>=77 && LA79_0<=81)||LA79_0==86||(LA79_0>=88 && LA79_0<=91)||LA79_0==98||(LA79_0>=103 && LA79_0<=104)) ) {
                alt79=1;
            }
            switch (alt79) {
                case 1 :
                    // InternalCompleteOCL.g:3559:2: ( (lv_ownedParts_2_0= ruleCollectionLiteralPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleCollectionLiteralPartCS ) ) )*
                    {
                    // InternalCompleteOCL.g:3559:2: ( (lv_ownedParts_2_0= ruleCollectionLiteralPartCS ) )
                    // InternalCompleteOCL.g:3560:1: (lv_ownedParts_2_0= ruleCollectionLiteralPartCS )
                    {
                    // InternalCompleteOCL.g:3560:1: (lv_ownedParts_2_0= ruleCollectionLiteralPartCS )
                    // InternalCompleteOCL.g:3561:3: lv_ownedParts_2_0= ruleCollectionLiteralPartCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getCollectionLiteralExpCSAccess().getOwnedPartsCollectionLiteralPartCSParserRuleCall_2_0_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_47);
                    lv_ownedParts_2_0=ruleCollectionLiteralPartCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getCollectionLiteralExpCSRule());
                      	        }
                             		add(
                             			current,
                             			"ownedParts",
                              		lv_ownedParts_2_0,
                              		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.CollectionLiteralPartCS");
                      	        afterParserOrEnumRuleCall();

                    }

                    }


                    }

                    // InternalCompleteOCL.g:3577:2: (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleCollectionLiteralPartCS ) ) )*
                    loop78:
                    do {
                        int alt78=2;
                        int LA78_0 = input.LA(1);

                        if ( (LA78_0==29) ) {
                            alt78=1;
                        }


                        switch (alt78) {
                    	case 1 :
                    	    // InternalCompleteOCL.g:3577:4: otherlv_3= ',' ( (lv_ownedParts_4_0= ruleCollectionLiteralPartCS ) )
                    	    {
                    	    otherlv_3=(Token)match(input,29,FollowSets000.FOLLOW_48); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_3, grammarAccess.getCollectionLiteralExpCSAccess().getCommaKeyword_2_1_0());

                    	    }
                    	    // InternalCompleteOCL.g:3581:1: ( (lv_ownedParts_4_0= ruleCollectionLiteralPartCS ) )
                    	    // InternalCompleteOCL.g:3582:1: (lv_ownedParts_4_0= ruleCollectionLiteralPartCS )
                    	    {
                    	    // InternalCompleteOCL.g:3582:1: (lv_ownedParts_4_0= ruleCollectionLiteralPartCS )
                    	    // InternalCompleteOCL.g:3583:3: lv_ownedParts_4_0= ruleCollectionLiteralPartCS
                    	    {
                    	    if ( state.backtracking==0 ) {

                    	      	        newCompositeNode(grammarAccess.getCollectionLiteralExpCSAccess().getOwnedPartsCollectionLiteralPartCSParserRuleCall_2_1_1_0());

                    	    }
                    	    pushFollow(FollowSets000.FOLLOW_47);
                    	    lv_ownedParts_4_0=ruleCollectionLiteralPartCS();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = createModelElementForParent(grammarAccess.getCollectionLiteralExpCSRule());
                    	      	        }
                    	             		add(
                    	             			current,
                    	             			"ownedParts",
                    	              		lv_ownedParts_4_0,
                    	              		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.CollectionLiteralPartCS");
                    	      	        afterParserOrEnumRuleCall();

                    	    }

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop78;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_5=(Token)match(input,83,FollowSets000.FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_5, grammarAccess.getCollectionLiteralExpCSAccess().getRightCurlyBracketKeyword_3());

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
    // $ANTLR end "ruleCollectionLiteralExpCS"


    // $ANTLR start "entryRuleCollectionLiteralPartCS"
    // InternalCompleteOCL.g:3611:1: entryRuleCollectionLiteralPartCS returns [EObject current=null] : iv_ruleCollectionLiteralPartCS= ruleCollectionLiteralPartCS EOF ;
    public final EObject entryRuleCollectionLiteralPartCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCollectionLiteralPartCS = null;


        try {
            // InternalCompleteOCL.g:3612:2: (iv_ruleCollectionLiteralPartCS= ruleCollectionLiteralPartCS EOF )
            // InternalCompleteOCL.g:3613:2: iv_ruleCollectionLiteralPartCS= ruleCollectionLiteralPartCS EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getCollectionLiteralPartCSRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleCollectionLiteralPartCS=ruleCollectionLiteralPartCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleCollectionLiteralPartCS;
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
    // $ANTLR end "entryRuleCollectionLiteralPartCS"


    // $ANTLR start "ruleCollectionLiteralPartCS"
    // InternalCompleteOCL.g:3620:1: ruleCollectionLiteralPartCS returns [EObject current=null] : ( ( ( (lv_ownedExpression_0_0= ruleExpCS ) ) (otherlv_1= '..' ( (lv_ownedLastExpression_2_0= ruleExpCS ) ) )? ) | ( (lv_ownedExpression_3_0= rulePatternExpCS ) ) ) ;
    public final EObject ruleCollectionLiteralPartCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject lv_ownedExpression_0_0 = null;

        EObject lv_ownedLastExpression_2_0 = null;

        EObject lv_ownedExpression_3_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:3623:28: ( ( ( ( (lv_ownedExpression_0_0= ruleExpCS ) ) (otherlv_1= '..' ( (lv_ownedLastExpression_2_0= ruleExpCS ) ) )? ) | ( (lv_ownedExpression_3_0= rulePatternExpCS ) ) ) )
            // InternalCompleteOCL.g:3624:1: ( ( ( (lv_ownedExpression_0_0= ruleExpCS ) ) (otherlv_1= '..' ( (lv_ownedLastExpression_2_0= ruleExpCS ) ) )? ) | ( (lv_ownedExpression_3_0= rulePatternExpCS ) ) )
            {
            // InternalCompleteOCL.g:3624:1: ( ( ( (lv_ownedExpression_0_0= ruleExpCS ) ) (otherlv_1= '..' ( (lv_ownedLastExpression_2_0= ruleExpCS ) ) )? ) | ( (lv_ownedExpression_3_0= rulePatternExpCS ) ) )
            int alt81=2;
            switch ( input.LA(1) ) {
            case RULE_INT:
            case RULE_SINGLE_QUOTED_STRING:
            case 22:
            case 43:
            case 44:
            case 45:
            case 46:
            case 47:
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
            case 56:
            case 74:
            case 75:
            case 77:
            case 78:
            case 79:
            case 80:
            case 81:
            case 86:
            case 88:
            case 89:
            case 90:
            case 91:
            case 98:
            case 103:
            case 104:
                {
                alt81=1;
                }
                break;
            case RULE_SIMPLE_ID:
                {
                int LA81_2 = input.LA(2);

                if ( (LA81_2==24) ) {
                    alt81=2;
                }
                else if ( (LA81_2==EOF||(LA81_2>=18 && LA81_2<=19)||LA81_2==22||(LA81_2>=29 && LA81_2<=30)||(LA81_2>=40 && LA81_2<=41)||LA81_2==53||(LA81_2>=56 && LA81_2<=73)||LA81_2==76||(LA81_2>=82 && LA81_2<=84)||(LA81_2>=92 && LA81_2<=93)) ) {
                    alt81=1;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return current;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 81, 2, input);

                    throw nvae;
                }
                }
                break;
            case RULE_ESCAPED_ID:
                {
                int LA81_3 = input.LA(2);

                if ( (LA81_3==EOF||(LA81_3>=18 && LA81_3<=19)||LA81_3==22||(LA81_3>=29 && LA81_3<=30)||(LA81_3>=40 && LA81_3<=41)||LA81_3==53||(LA81_3>=56 && LA81_3<=73)||LA81_3==76||(LA81_3>=82 && LA81_3<=84)||(LA81_3>=92 && LA81_3<=93)) ) {
                    alt81=1;
                }
                else if ( (LA81_3==24) ) {
                    alt81=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return current;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 81, 3, input);

                    throw nvae;
                }
                }
                break;
            case 31:
                {
                int LA81_4 = input.LA(2);

                if ( (LA81_4==24) ) {
                    alt81=2;
                }
                else if ( (LA81_4==EOF||(LA81_4>=18 && LA81_4<=19)||LA81_4==22||(LA81_4>=29 && LA81_4<=30)||(LA81_4>=40 && LA81_4<=41)||LA81_4==53||(LA81_4>=56 && LA81_4<=73)||LA81_4==76||(LA81_4>=82 && LA81_4<=84)||(LA81_4>=92 && LA81_4<=93)) ) {
                    alt81=1;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return current;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 81, 4, input);

                    throw nvae;
                }
                }
                break;
            case 32:
                {
                int LA81_5 = input.LA(2);

                if ( (LA81_5==EOF||(LA81_5>=18 && LA81_5<=19)||LA81_5==22||(LA81_5>=29 && LA81_5<=30)||(LA81_5>=40 && LA81_5<=41)||LA81_5==53||(LA81_5>=56 && LA81_5<=73)||LA81_5==76||(LA81_5>=82 && LA81_5<=84)||(LA81_5>=92 && LA81_5<=93)) ) {
                    alt81=1;
                }
                else if ( (LA81_5==24) ) {
                    alt81=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return current;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 81, 5, input);

                    throw nvae;
                }
                }
                break;
            case 33:
                {
                int LA81_6 = input.LA(2);

                if ( (LA81_6==EOF||(LA81_6>=18 && LA81_6<=19)||LA81_6==22||(LA81_6>=29 && LA81_6<=30)||(LA81_6>=40 && LA81_6<=41)||LA81_6==53||(LA81_6>=56 && LA81_6<=73)||LA81_6==76||(LA81_6>=82 && LA81_6<=84)||(LA81_6>=92 && LA81_6<=93)) ) {
                    alt81=1;
                }
                else if ( (LA81_6==24) ) {
                    alt81=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return current;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 81, 6, input);

                    throw nvae;
                }
                }
                break;
            case 24:
                {
                alt81=2;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 81, 0, input);

                throw nvae;
            }

            switch (alt81) {
                case 1 :
                    // InternalCompleteOCL.g:3624:2: ( ( (lv_ownedExpression_0_0= ruleExpCS ) ) (otherlv_1= '..' ( (lv_ownedLastExpression_2_0= ruleExpCS ) ) )? )
                    {
                    // InternalCompleteOCL.g:3624:2: ( ( (lv_ownedExpression_0_0= ruleExpCS ) ) (otherlv_1= '..' ( (lv_ownedLastExpression_2_0= ruleExpCS ) ) )? )
                    // InternalCompleteOCL.g:3624:3: ( (lv_ownedExpression_0_0= ruleExpCS ) ) (otherlv_1= '..' ( (lv_ownedLastExpression_2_0= ruleExpCS ) ) )?
                    {
                    // InternalCompleteOCL.g:3624:3: ( (lv_ownedExpression_0_0= ruleExpCS ) )
                    // InternalCompleteOCL.g:3625:1: (lv_ownedExpression_0_0= ruleExpCS )
                    {
                    // InternalCompleteOCL.g:3625:1: (lv_ownedExpression_0_0= ruleExpCS )
                    // InternalCompleteOCL.g:3626:3: lv_ownedExpression_0_0= ruleExpCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getCollectionLiteralPartCSAccess().getOwnedExpressionExpCSParserRuleCall_0_0_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_49);
                    lv_ownedExpression_0_0=ruleExpCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getCollectionLiteralPartCSRule());
                      	        }
                             		set(
                             			current,
                             			"ownedExpression",
                              		lv_ownedExpression_0_0,
                              		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.ExpCS");
                      	        afterParserOrEnumRuleCall();

                    }

                    }


                    }

                    // InternalCompleteOCL.g:3642:2: (otherlv_1= '..' ( (lv_ownedLastExpression_2_0= ruleExpCS ) ) )?
                    int alt80=2;
                    int LA80_0 = input.LA(1);

                    if ( (LA80_0==84) ) {
                        alt80=1;
                    }
                    switch (alt80) {
                        case 1 :
                            // InternalCompleteOCL.g:3642:4: otherlv_1= '..' ( (lv_ownedLastExpression_2_0= ruleExpCS ) )
                            {
                            otherlv_1=(Token)match(input,84,FollowSets000.FOLLOW_50); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                  	newLeafNode(otherlv_1, grammarAccess.getCollectionLiteralPartCSAccess().getFullStopFullStopKeyword_0_1_0());

                            }
                            // InternalCompleteOCL.g:3646:1: ( (lv_ownedLastExpression_2_0= ruleExpCS ) )
                            // InternalCompleteOCL.g:3647:1: (lv_ownedLastExpression_2_0= ruleExpCS )
                            {
                            // InternalCompleteOCL.g:3647:1: (lv_ownedLastExpression_2_0= ruleExpCS )
                            // InternalCompleteOCL.g:3648:3: lv_ownedLastExpression_2_0= ruleExpCS
                            {
                            if ( state.backtracking==0 ) {

                              	        newCompositeNode(grammarAccess.getCollectionLiteralPartCSAccess().getOwnedLastExpressionExpCSParserRuleCall_0_1_1_0());

                            }
                            pushFollow(FollowSets000.FOLLOW_2);
                            lv_ownedLastExpression_2_0=ruleExpCS();

                            state._fsp--;
                            if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElementForParent(grammarAccess.getCollectionLiteralPartCSRule());
                              	        }
                                     		set(
                                     			current,
                                     			"ownedLastExpression",
                                      		lv_ownedLastExpression_2_0,
                                      		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.ExpCS");
                              	        afterParserOrEnumRuleCall();

                            }

                            }


                            }


                            }
                            break;

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalCompleteOCL.g:3665:6: ( (lv_ownedExpression_3_0= rulePatternExpCS ) )
                    {
                    // InternalCompleteOCL.g:3665:6: ( (lv_ownedExpression_3_0= rulePatternExpCS ) )
                    // InternalCompleteOCL.g:3666:1: (lv_ownedExpression_3_0= rulePatternExpCS )
                    {
                    // InternalCompleteOCL.g:3666:1: (lv_ownedExpression_3_0= rulePatternExpCS )
                    // InternalCompleteOCL.g:3667:3: lv_ownedExpression_3_0= rulePatternExpCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getCollectionLiteralPartCSAccess().getOwnedExpressionPatternExpCSParserRuleCall_1_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    lv_ownedExpression_3_0=rulePatternExpCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getCollectionLiteralPartCSRule());
                      	        }
                             		set(
                             			current,
                             			"ownedExpression",
                              		lv_ownedExpression_3_0,
                              		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.PatternExpCS");
                      	        afterParserOrEnumRuleCall();

                    }

                    }


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
    // $ANTLR end "ruleCollectionLiteralPartCS"


    // $ANTLR start "entryRuleCollectionPatternCS"
    // InternalCompleteOCL.g:3691:1: entryRuleCollectionPatternCS returns [EObject current=null] : iv_ruleCollectionPatternCS= ruleCollectionPatternCS EOF ;
    public final EObject entryRuleCollectionPatternCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCollectionPatternCS = null;


        try {
            // InternalCompleteOCL.g:3692:2: (iv_ruleCollectionPatternCS= ruleCollectionPatternCS EOF )
            // InternalCompleteOCL.g:3693:2: iv_ruleCollectionPatternCS= ruleCollectionPatternCS EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getCollectionPatternCSRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleCollectionPatternCS=ruleCollectionPatternCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleCollectionPatternCS;
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
    // $ANTLR end "entryRuleCollectionPatternCS"


    // $ANTLR start "ruleCollectionPatternCS"
    // InternalCompleteOCL.g:3700:1: ruleCollectionPatternCS returns [EObject current=null] : ( ( (lv_ownedType_0_0= ruleCollectionTypeCS ) ) otherlv_1= '{' ( ( (lv_ownedParts_2_0= rulePatternExpCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= rulePatternExpCS ) ) )* (otherlv_5= '++' ( (lv_restVariableName_6_0= ruleIdentifier ) ) ) )? otherlv_7= '}' ) ;
    public final EObject ruleCollectionPatternCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        EObject lv_ownedType_0_0 = null;

        EObject lv_ownedParts_2_0 = null;

        EObject lv_ownedParts_4_0 = null;

        AntlrDatatypeRuleToken lv_restVariableName_6_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:3703:28: ( ( ( (lv_ownedType_0_0= ruleCollectionTypeCS ) ) otherlv_1= '{' ( ( (lv_ownedParts_2_0= rulePatternExpCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= rulePatternExpCS ) ) )* (otherlv_5= '++' ( (lv_restVariableName_6_0= ruleIdentifier ) ) ) )? otherlv_7= '}' ) )
            // InternalCompleteOCL.g:3704:1: ( ( (lv_ownedType_0_0= ruleCollectionTypeCS ) ) otherlv_1= '{' ( ( (lv_ownedParts_2_0= rulePatternExpCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= rulePatternExpCS ) ) )* (otherlv_5= '++' ( (lv_restVariableName_6_0= ruleIdentifier ) ) ) )? otherlv_7= '}' )
            {
            // InternalCompleteOCL.g:3704:1: ( ( (lv_ownedType_0_0= ruleCollectionTypeCS ) ) otherlv_1= '{' ( ( (lv_ownedParts_2_0= rulePatternExpCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= rulePatternExpCS ) ) )* (otherlv_5= '++' ( (lv_restVariableName_6_0= ruleIdentifier ) ) ) )? otherlv_7= '}' )
            // InternalCompleteOCL.g:3704:2: ( (lv_ownedType_0_0= ruleCollectionTypeCS ) ) otherlv_1= '{' ( ( (lv_ownedParts_2_0= rulePatternExpCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= rulePatternExpCS ) ) )* (otherlv_5= '++' ( (lv_restVariableName_6_0= ruleIdentifier ) ) ) )? otherlv_7= '}'
            {
            // InternalCompleteOCL.g:3704:2: ( (lv_ownedType_0_0= ruleCollectionTypeCS ) )
            // InternalCompleteOCL.g:3705:1: (lv_ownedType_0_0= ruleCollectionTypeCS )
            {
            // InternalCompleteOCL.g:3705:1: (lv_ownedType_0_0= ruleCollectionTypeCS )
            // InternalCompleteOCL.g:3706:3: lv_ownedType_0_0= ruleCollectionTypeCS
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getCollectionPatternCSAccess().getOwnedTypeCollectionTypeCSParserRuleCall_0_0());

            }
            pushFollow(FollowSets000.FOLLOW_45);
            lv_ownedType_0_0=ruleCollectionTypeCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getCollectionPatternCSRule());
              	        }
                     		set(
                     			current,
                     			"ownedType",
                      		lv_ownedType_0_0,
                      		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.CollectionTypeCS");
              	        afterParserOrEnumRuleCall();

            }

            }


            }

            otherlv_1=(Token)match(input,82,FollowSets000.FOLLOW_46); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getCollectionPatternCSAccess().getLeftCurlyBracketKeyword_1());

            }
            // InternalCompleteOCL.g:3726:1: ( ( (lv_ownedParts_2_0= rulePatternExpCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= rulePatternExpCS ) ) )* (otherlv_5= '++' ( (lv_restVariableName_6_0= ruleIdentifier ) ) ) )?
            int alt83=2;
            int LA83_0 = input.LA(1);

            if ( ((LA83_0>=RULE_SIMPLE_ID && LA83_0<=RULE_ESCAPED_ID)||LA83_0==24||(LA83_0>=31 && LA83_0<=33)) ) {
                alt83=1;
            }
            switch (alt83) {
                case 1 :
                    // InternalCompleteOCL.g:3726:2: ( (lv_ownedParts_2_0= rulePatternExpCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= rulePatternExpCS ) ) )* (otherlv_5= '++' ( (lv_restVariableName_6_0= ruleIdentifier ) ) )
                    {
                    // InternalCompleteOCL.g:3726:2: ( (lv_ownedParts_2_0= rulePatternExpCS ) )
                    // InternalCompleteOCL.g:3727:1: (lv_ownedParts_2_0= rulePatternExpCS )
                    {
                    // InternalCompleteOCL.g:3727:1: (lv_ownedParts_2_0= rulePatternExpCS )
                    // InternalCompleteOCL.g:3728:3: lv_ownedParts_2_0= rulePatternExpCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getCollectionPatternCSAccess().getOwnedPartsPatternExpCSParserRuleCall_2_0_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_51);
                    lv_ownedParts_2_0=rulePatternExpCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getCollectionPatternCSRule());
                      	        }
                             		add(
                             			current,
                             			"ownedParts",
                              		lv_ownedParts_2_0,
                              		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.PatternExpCS");
                      	        afterParserOrEnumRuleCall();

                    }

                    }


                    }

                    // InternalCompleteOCL.g:3744:2: (otherlv_3= ',' ( (lv_ownedParts_4_0= rulePatternExpCS ) ) )*
                    loop82:
                    do {
                        int alt82=2;
                        int LA82_0 = input.LA(1);

                        if ( (LA82_0==29) ) {
                            alt82=1;
                        }


                        switch (alt82) {
                    	case 1 :
                    	    // InternalCompleteOCL.g:3744:4: otherlv_3= ',' ( (lv_ownedParts_4_0= rulePatternExpCS ) )
                    	    {
                    	    otherlv_3=(Token)match(input,29,FollowSets000.FOLLOW_48); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_3, grammarAccess.getCollectionPatternCSAccess().getCommaKeyword_2_1_0());

                    	    }
                    	    // InternalCompleteOCL.g:3748:1: ( (lv_ownedParts_4_0= rulePatternExpCS ) )
                    	    // InternalCompleteOCL.g:3749:1: (lv_ownedParts_4_0= rulePatternExpCS )
                    	    {
                    	    // InternalCompleteOCL.g:3749:1: (lv_ownedParts_4_0= rulePatternExpCS )
                    	    // InternalCompleteOCL.g:3750:3: lv_ownedParts_4_0= rulePatternExpCS
                    	    {
                    	    if ( state.backtracking==0 ) {

                    	      	        newCompositeNode(grammarAccess.getCollectionPatternCSAccess().getOwnedPartsPatternExpCSParserRuleCall_2_1_1_0());

                    	    }
                    	    pushFollow(FollowSets000.FOLLOW_51);
                    	    lv_ownedParts_4_0=rulePatternExpCS();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = createModelElementForParent(grammarAccess.getCollectionPatternCSRule());
                    	      	        }
                    	             		add(
                    	             			current,
                    	             			"ownedParts",
                    	              		lv_ownedParts_4_0,
                    	              		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.PatternExpCS");
                    	      	        afterParserOrEnumRuleCall();

                    	    }

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop82;
                        }
                    } while (true);

                    // InternalCompleteOCL.g:3766:4: (otherlv_5= '++' ( (lv_restVariableName_6_0= ruleIdentifier ) ) )
                    // InternalCompleteOCL.g:3766:6: otherlv_5= '++' ( (lv_restVariableName_6_0= ruleIdentifier ) )
                    {
                    otherlv_5=(Token)match(input,85,FollowSets000.FOLLOW_52); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_5, grammarAccess.getCollectionPatternCSAccess().getPlusSignPlusSignKeyword_2_2_0());

                    }
                    // InternalCompleteOCL.g:3770:1: ( (lv_restVariableName_6_0= ruleIdentifier ) )
                    // InternalCompleteOCL.g:3771:1: (lv_restVariableName_6_0= ruleIdentifier )
                    {
                    // InternalCompleteOCL.g:3771:1: (lv_restVariableName_6_0= ruleIdentifier )
                    // InternalCompleteOCL.g:3772:3: lv_restVariableName_6_0= ruleIdentifier
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getCollectionPatternCSAccess().getRestVariableNameIdentifierParserRuleCall_2_2_1_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_53);
                    lv_restVariableName_6_0=ruleIdentifier();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getCollectionPatternCSRule());
                      	        }
                             		set(
                             			current,
                             			"restVariableName",
                              		lv_restVariableName_6_0,
                              		"org.eclipse.ocl.xtext.base.Base.Identifier");
                      	        afterParserOrEnumRuleCall();

                    }

                    }


                    }


                    }


                    }
                    break;

            }

            otherlv_7=(Token)match(input,83,FollowSets000.FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_7, grammarAccess.getCollectionPatternCSAccess().getRightCurlyBracketKeyword_3());

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
    // $ANTLR end "ruleCollectionPatternCS"


    // $ANTLR start "entryRuleShadowPartCS"
    // InternalCompleteOCL.g:3800:1: entryRuleShadowPartCS returns [EObject current=null] : iv_ruleShadowPartCS= ruleShadowPartCS EOF ;
    public final EObject entryRuleShadowPartCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleShadowPartCS = null;


        try {
            // InternalCompleteOCL.g:3801:2: (iv_ruleShadowPartCS= ruleShadowPartCS EOF )
            // InternalCompleteOCL.g:3802:2: iv_ruleShadowPartCS= ruleShadowPartCS EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getShadowPartCSRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleShadowPartCS=ruleShadowPartCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleShadowPartCS;
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
    // $ANTLR end "entryRuleShadowPartCS"


    // $ANTLR start "ruleShadowPartCS"
    // InternalCompleteOCL.g:3809:1: ruleShadowPartCS returns [EObject current=null] : ( ( ( ( ruleUnrestrictedName ) ) otherlv_1= '=' ( ( (lv_ownedInitExpression_2_1= ruleExpCS | lv_ownedInitExpression_2_2= rulePatternExpCS ) ) ) ) | ( (lv_ownedInitExpression_3_0= ruleStringLiteralExpCS ) ) ) ;
    public final EObject ruleShadowPartCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject lv_ownedInitExpression_2_1 = null;

        EObject lv_ownedInitExpression_2_2 = null;

        EObject lv_ownedInitExpression_3_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:3812:28: ( ( ( ( ( ruleUnrestrictedName ) ) otherlv_1= '=' ( ( (lv_ownedInitExpression_2_1= ruleExpCS | lv_ownedInitExpression_2_2= rulePatternExpCS ) ) ) ) | ( (lv_ownedInitExpression_3_0= ruleStringLiteralExpCS ) ) ) )
            // InternalCompleteOCL.g:3813:1: ( ( ( ( ruleUnrestrictedName ) ) otherlv_1= '=' ( ( (lv_ownedInitExpression_2_1= ruleExpCS | lv_ownedInitExpression_2_2= rulePatternExpCS ) ) ) ) | ( (lv_ownedInitExpression_3_0= ruleStringLiteralExpCS ) ) )
            {
            // InternalCompleteOCL.g:3813:1: ( ( ( ( ruleUnrestrictedName ) ) otherlv_1= '=' ( ( (lv_ownedInitExpression_2_1= ruleExpCS | lv_ownedInitExpression_2_2= rulePatternExpCS ) ) ) ) | ( (lv_ownedInitExpression_3_0= ruleStringLiteralExpCS ) ) )
            int alt85=2;
            int LA85_0 = input.LA(1);

            if ( ((LA85_0>=RULE_SIMPLE_ID && LA85_0<=RULE_ESCAPED_ID)||(LA85_0>=31 && LA85_0<=33)) ) {
                alt85=1;
            }
            else if ( (LA85_0==RULE_SINGLE_QUOTED_STRING) ) {
                alt85=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 85, 0, input);

                throw nvae;
            }
            switch (alt85) {
                case 1 :
                    // InternalCompleteOCL.g:3813:2: ( ( ( ruleUnrestrictedName ) ) otherlv_1= '=' ( ( (lv_ownedInitExpression_2_1= ruleExpCS | lv_ownedInitExpression_2_2= rulePatternExpCS ) ) ) )
                    {
                    // InternalCompleteOCL.g:3813:2: ( ( ( ruleUnrestrictedName ) ) otherlv_1= '=' ( ( (lv_ownedInitExpression_2_1= ruleExpCS | lv_ownedInitExpression_2_2= rulePatternExpCS ) ) ) )
                    // InternalCompleteOCL.g:3813:3: ( ( ruleUnrestrictedName ) ) otherlv_1= '=' ( ( (lv_ownedInitExpression_2_1= ruleExpCS | lv_ownedInitExpression_2_2= rulePatternExpCS ) ) )
                    {
                    // InternalCompleteOCL.g:3813:3: ( ( ruleUnrestrictedName ) )
                    // InternalCompleteOCL.g:3814:1: ( ruleUnrestrictedName )
                    {
                    // InternalCompleteOCL.g:3814:1: ( ruleUnrestrictedName )
                    // InternalCompleteOCL.g:3815:3: ruleUnrestrictedName
                    {
                    if ( state.backtracking==0 ) {

                      		  /* */

                    }
                    if ( state.backtracking==0 ) {

                      			if (current==null) {
                      	            current = createModelElement(grammarAccess.getShadowPartCSRule());
                      	        }

                    }
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getShadowPartCSAccess().getReferredPropertyPropertyCrossReference_0_0_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_24);
                    ruleUnrestrictedName();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        afterParserOrEnumRuleCall();

                    }

                    }


                    }

                    otherlv_1=(Token)match(input,30,FollowSets000.FOLLOW_48); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_1, grammarAccess.getShadowPartCSAccess().getEqualsSignKeyword_0_1());

                    }
                    // InternalCompleteOCL.g:3835:1: ( ( (lv_ownedInitExpression_2_1= ruleExpCS | lv_ownedInitExpression_2_2= rulePatternExpCS ) ) )
                    // InternalCompleteOCL.g:3836:1: ( (lv_ownedInitExpression_2_1= ruleExpCS | lv_ownedInitExpression_2_2= rulePatternExpCS ) )
                    {
                    // InternalCompleteOCL.g:3836:1: ( (lv_ownedInitExpression_2_1= ruleExpCS | lv_ownedInitExpression_2_2= rulePatternExpCS ) )
                    // InternalCompleteOCL.g:3837:1: (lv_ownedInitExpression_2_1= ruleExpCS | lv_ownedInitExpression_2_2= rulePatternExpCS )
                    {
                    // InternalCompleteOCL.g:3837:1: (lv_ownedInitExpression_2_1= ruleExpCS | lv_ownedInitExpression_2_2= rulePatternExpCS )
                    int alt84=2;
                    switch ( input.LA(1) ) {
                    case RULE_INT:
                    case RULE_SINGLE_QUOTED_STRING:
                    case 22:
                    case 43:
                    case 44:
                    case 45:
                    case 46:
                    case 47:
                    case 48:
                    case 49:
                    case 50:
                    case 51:
                    case 52:
                    case 53:
                    case 54:
                    case 55:
                    case 56:
                    case 74:
                    case 75:
                    case 77:
                    case 78:
                    case 79:
                    case 80:
                    case 81:
                    case 86:
                    case 88:
                    case 89:
                    case 90:
                    case 91:
                    case 98:
                    case 103:
                    case 104:
                        {
                        alt84=1;
                        }
                        break;
                    case RULE_SIMPLE_ID:
                        {
                        int LA84_2 = input.LA(2);

                        if ( (LA84_2==24) ) {
                            alt84=2;
                        }
                        else if ( (LA84_2==EOF||(LA84_2>=18 && LA84_2<=19)||LA84_2==22||(LA84_2>=29 && LA84_2<=30)||(LA84_2>=40 && LA84_2<=41)||LA84_2==53||(LA84_2>=56 && LA84_2<=73)||LA84_2==76||(LA84_2>=82 && LA84_2<=83)||(LA84_2>=92 && LA84_2<=93)) ) {
                            alt84=1;
                        }
                        else {
                            if (state.backtracking>0) {state.failed=true; return current;}
                            NoViableAltException nvae =
                                new NoViableAltException("", 84, 2, input);

                            throw nvae;
                        }
                        }
                        break;
                    case RULE_ESCAPED_ID:
                        {
                        int LA84_3 = input.LA(2);

                        if ( (LA84_3==EOF||(LA84_3>=18 && LA84_3<=19)||LA84_3==22||(LA84_3>=29 && LA84_3<=30)||(LA84_3>=40 && LA84_3<=41)||LA84_3==53||(LA84_3>=56 && LA84_3<=73)||LA84_3==76||(LA84_3>=82 && LA84_3<=83)||(LA84_3>=92 && LA84_3<=93)) ) {
                            alt84=1;
                        }
                        else if ( (LA84_3==24) ) {
                            alt84=2;
                        }
                        else {
                            if (state.backtracking>0) {state.failed=true; return current;}
                            NoViableAltException nvae =
                                new NoViableAltException("", 84, 3, input);

                            throw nvae;
                        }
                        }
                        break;
                    case 31:
                        {
                        int LA84_4 = input.LA(2);

                        if ( (LA84_4==24) ) {
                            alt84=2;
                        }
                        else if ( (LA84_4==EOF||(LA84_4>=18 && LA84_4<=19)||LA84_4==22||(LA84_4>=29 && LA84_4<=30)||(LA84_4>=40 && LA84_4<=41)||LA84_4==53||(LA84_4>=56 && LA84_4<=73)||LA84_4==76||(LA84_4>=82 && LA84_4<=83)||(LA84_4>=92 && LA84_4<=93)) ) {
                            alt84=1;
                        }
                        else {
                            if (state.backtracking>0) {state.failed=true; return current;}
                            NoViableAltException nvae =
                                new NoViableAltException("", 84, 4, input);

                            throw nvae;
                        }
                        }
                        break;
                    case 32:
                        {
                        int LA84_5 = input.LA(2);

                        if ( (LA84_5==EOF||(LA84_5>=18 && LA84_5<=19)||LA84_5==22||(LA84_5>=29 && LA84_5<=30)||(LA84_5>=40 && LA84_5<=41)||LA84_5==53||(LA84_5>=56 && LA84_5<=73)||LA84_5==76||(LA84_5>=82 && LA84_5<=83)||(LA84_5>=92 && LA84_5<=93)) ) {
                            alt84=1;
                        }
                        else if ( (LA84_5==24) ) {
                            alt84=2;
                        }
                        else {
                            if (state.backtracking>0) {state.failed=true; return current;}
                            NoViableAltException nvae =
                                new NoViableAltException("", 84, 5, input);

                            throw nvae;
                        }
                        }
                        break;
                    case 33:
                        {
                        int LA84_6 = input.LA(2);

                        if ( (LA84_6==EOF||(LA84_6>=18 && LA84_6<=19)||LA84_6==22||(LA84_6>=29 && LA84_6<=30)||(LA84_6>=40 && LA84_6<=41)||LA84_6==53||(LA84_6>=56 && LA84_6<=73)||LA84_6==76||(LA84_6>=82 && LA84_6<=83)||(LA84_6>=92 && LA84_6<=93)) ) {
                            alt84=1;
                        }
                        else if ( (LA84_6==24) ) {
                            alt84=2;
                        }
                        else {
                            if (state.backtracking>0) {state.failed=true; return current;}
                            NoViableAltException nvae =
                                new NoViableAltException("", 84, 6, input);

                            throw nvae;
                        }
                        }
                        break;
                    case 24:
                        {
                        alt84=2;
                        }
                        break;
                    default:
                        if (state.backtracking>0) {state.failed=true; return current;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 84, 0, input);

                        throw nvae;
                    }

                    switch (alt84) {
                        case 1 :
                            // InternalCompleteOCL.g:3838:3: lv_ownedInitExpression_2_1= ruleExpCS
                            {
                            if ( state.backtracking==0 ) {

                              	        newCompositeNode(grammarAccess.getShadowPartCSAccess().getOwnedInitExpressionExpCSParserRuleCall_0_2_0_0());

                            }
                            pushFollow(FollowSets000.FOLLOW_2);
                            lv_ownedInitExpression_2_1=ruleExpCS();

                            state._fsp--;
                            if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElementForParent(grammarAccess.getShadowPartCSRule());
                              	        }
                                     		set(
                                     			current,
                                     			"ownedInitExpression",
                                      		lv_ownedInitExpression_2_1,
                                      		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.ExpCS");
                              	        afterParserOrEnumRuleCall();

                            }

                            }
                            break;
                        case 2 :
                            // InternalCompleteOCL.g:3853:8: lv_ownedInitExpression_2_2= rulePatternExpCS
                            {
                            if ( state.backtracking==0 ) {

                              	        newCompositeNode(grammarAccess.getShadowPartCSAccess().getOwnedInitExpressionPatternExpCSParserRuleCall_0_2_0_1());

                            }
                            pushFollow(FollowSets000.FOLLOW_2);
                            lv_ownedInitExpression_2_2=rulePatternExpCS();

                            state._fsp--;
                            if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElementForParent(grammarAccess.getShadowPartCSRule());
                              	        }
                                     		set(
                                     			current,
                                     			"ownedInitExpression",
                                      		lv_ownedInitExpression_2_2,
                                      		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.PatternExpCS");
                              	        afterParserOrEnumRuleCall();

                            }

                            }
                            break;

                    }


                    }


                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalCompleteOCL.g:3872:6: ( (lv_ownedInitExpression_3_0= ruleStringLiteralExpCS ) )
                    {
                    // InternalCompleteOCL.g:3872:6: ( (lv_ownedInitExpression_3_0= ruleStringLiteralExpCS ) )
                    // InternalCompleteOCL.g:3873:1: (lv_ownedInitExpression_3_0= ruleStringLiteralExpCS )
                    {
                    // InternalCompleteOCL.g:3873:1: (lv_ownedInitExpression_3_0= ruleStringLiteralExpCS )
                    // InternalCompleteOCL.g:3874:3: lv_ownedInitExpression_3_0= ruleStringLiteralExpCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getShadowPartCSAccess().getOwnedInitExpressionStringLiteralExpCSParserRuleCall_1_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    lv_ownedInitExpression_3_0=ruleStringLiteralExpCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getShadowPartCSRule());
                      	        }
                             		set(
                             			current,
                             			"ownedInitExpression",
                              		lv_ownedInitExpression_3_0,
                              		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.StringLiteralExpCS");
                      	        afterParserOrEnumRuleCall();

                    }

                    }


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
    // $ANTLR end "ruleShadowPartCS"


    // $ANTLR start "entryRulePatternExpCS"
    // InternalCompleteOCL.g:3898:1: entryRulePatternExpCS returns [EObject current=null] : iv_rulePatternExpCS= rulePatternExpCS EOF ;
    public final EObject entryRulePatternExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePatternExpCS = null;


        try {
            // InternalCompleteOCL.g:3899:2: (iv_rulePatternExpCS= rulePatternExpCS EOF )
            // InternalCompleteOCL.g:3900:2: iv_rulePatternExpCS= rulePatternExpCS EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getPatternExpCSRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_rulePatternExpCS=rulePatternExpCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_rulePatternExpCS;
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
    // $ANTLR end "entryRulePatternExpCS"


    // $ANTLR start "rulePatternExpCS"
    // InternalCompleteOCL.g:3907:1: rulePatternExpCS returns [EObject current=null] : ( ( (lv_patternVariableName_0_0= ruleUnrestrictedName ) )? otherlv_1= ':' ( (lv_ownedPatternType_2_0= ruleTypeExpCS ) ) ) ;
    public final EObject rulePatternExpCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_patternVariableName_0_0 = null;

        EObject lv_ownedPatternType_2_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:3910:28: ( ( ( (lv_patternVariableName_0_0= ruleUnrestrictedName ) )? otherlv_1= ':' ( (lv_ownedPatternType_2_0= ruleTypeExpCS ) ) ) )
            // InternalCompleteOCL.g:3911:1: ( ( (lv_patternVariableName_0_0= ruleUnrestrictedName ) )? otherlv_1= ':' ( (lv_ownedPatternType_2_0= ruleTypeExpCS ) ) )
            {
            // InternalCompleteOCL.g:3911:1: ( ( (lv_patternVariableName_0_0= ruleUnrestrictedName ) )? otherlv_1= ':' ( (lv_ownedPatternType_2_0= ruleTypeExpCS ) ) )
            // InternalCompleteOCL.g:3911:2: ( (lv_patternVariableName_0_0= ruleUnrestrictedName ) )? otherlv_1= ':' ( (lv_ownedPatternType_2_0= ruleTypeExpCS ) )
            {
            // InternalCompleteOCL.g:3911:2: ( (lv_patternVariableName_0_0= ruleUnrestrictedName ) )?
            int alt86=2;
            int LA86_0 = input.LA(1);

            if ( ((LA86_0>=RULE_SIMPLE_ID && LA86_0<=RULE_ESCAPED_ID)||(LA86_0>=31 && LA86_0<=33)) ) {
                alt86=1;
            }
            switch (alt86) {
                case 1 :
                    // InternalCompleteOCL.g:3912:1: (lv_patternVariableName_0_0= ruleUnrestrictedName )
                    {
                    // InternalCompleteOCL.g:3912:1: (lv_patternVariableName_0_0= ruleUnrestrictedName )
                    // InternalCompleteOCL.g:3913:3: lv_patternVariableName_0_0= ruleUnrestrictedName
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getPatternExpCSAccess().getPatternVariableNameUnrestrictedNameParserRuleCall_0_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_14);
                    lv_patternVariableName_0_0=ruleUnrestrictedName();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getPatternExpCSRule());
                      	        }
                             		set(
                             			current,
                             			"patternVariableName",
                              		lv_patternVariableName_0_0,
                              		"org.eclipse.ocl.xtext.completeocl.CompleteOCL.UnrestrictedName");
                      	        afterParserOrEnumRuleCall();

                    }

                    }


                    }
                    break;

            }

            otherlv_1=(Token)match(input,24,FollowSets000.FOLLOW_25); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getPatternExpCSAccess().getColonKeyword_1());

            }
            // InternalCompleteOCL.g:3933:1: ( (lv_ownedPatternType_2_0= ruleTypeExpCS ) )
            // InternalCompleteOCL.g:3934:1: (lv_ownedPatternType_2_0= ruleTypeExpCS )
            {
            // InternalCompleteOCL.g:3934:1: (lv_ownedPatternType_2_0= ruleTypeExpCS )
            // InternalCompleteOCL.g:3935:3: lv_ownedPatternType_2_0= ruleTypeExpCS
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getPatternExpCSAccess().getOwnedPatternTypeTypeExpCSParserRuleCall_2_0());

            }
            pushFollow(FollowSets000.FOLLOW_2);
            lv_ownedPatternType_2_0=ruleTypeExpCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getPatternExpCSRule());
              	        }
                     		set(
                     			current,
                     			"ownedPatternType",
                      		lv_ownedPatternType_2_0,
                      		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.TypeExpCS");
              	        afterParserOrEnumRuleCall();

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
    // $ANTLR end "rulePatternExpCS"


    // $ANTLR start "entryRuleLambdaLiteralExpCS"
    // InternalCompleteOCL.g:3959:1: entryRuleLambdaLiteralExpCS returns [EObject current=null] : iv_ruleLambdaLiteralExpCS= ruleLambdaLiteralExpCS EOF ;
    public final EObject entryRuleLambdaLiteralExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLambdaLiteralExpCS = null;


        try {
            // InternalCompleteOCL.g:3960:2: (iv_ruleLambdaLiteralExpCS= ruleLambdaLiteralExpCS EOF )
            // InternalCompleteOCL.g:3961:2: iv_ruleLambdaLiteralExpCS= ruleLambdaLiteralExpCS EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getLambdaLiteralExpCSRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleLambdaLiteralExpCS=ruleLambdaLiteralExpCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleLambdaLiteralExpCS;
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
    // $ANTLR end "entryRuleLambdaLiteralExpCS"


    // $ANTLR start "ruleLambdaLiteralExpCS"
    // InternalCompleteOCL.g:3968:1: ruleLambdaLiteralExpCS returns [EObject current=null] : (otherlv_0= 'Lambda' otherlv_1= '{' ( (lv_ownedExpressionCS_2_0= ruleExpCS ) ) otherlv_3= '}' ) ;
    public final EObject ruleLambdaLiteralExpCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_ownedExpressionCS_2_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:3971:28: ( (otherlv_0= 'Lambda' otherlv_1= '{' ( (lv_ownedExpressionCS_2_0= ruleExpCS ) ) otherlv_3= '}' ) )
            // InternalCompleteOCL.g:3972:1: (otherlv_0= 'Lambda' otherlv_1= '{' ( (lv_ownedExpressionCS_2_0= ruleExpCS ) ) otherlv_3= '}' )
            {
            // InternalCompleteOCL.g:3972:1: (otherlv_0= 'Lambda' otherlv_1= '{' ( (lv_ownedExpressionCS_2_0= ruleExpCS ) ) otherlv_3= '}' )
            // InternalCompleteOCL.g:3972:3: otherlv_0= 'Lambda' otherlv_1= '{' ( (lv_ownedExpressionCS_2_0= ruleExpCS ) ) otherlv_3= '}'
            {
            otherlv_0=(Token)match(input,86,FollowSets000.FOLLOW_45); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getLambdaLiteralExpCSAccess().getLambdaKeyword_0());

            }
            otherlv_1=(Token)match(input,82,FollowSets000.FOLLOW_50); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getLambdaLiteralExpCSAccess().getLeftCurlyBracketKeyword_1());

            }
            // InternalCompleteOCL.g:3980:1: ( (lv_ownedExpressionCS_2_0= ruleExpCS ) )
            // InternalCompleteOCL.g:3981:1: (lv_ownedExpressionCS_2_0= ruleExpCS )
            {
            // InternalCompleteOCL.g:3981:1: (lv_ownedExpressionCS_2_0= ruleExpCS )
            // InternalCompleteOCL.g:3982:3: lv_ownedExpressionCS_2_0= ruleExpCS
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getLambdaLiteralExpCSAccess().getOwnedExpressionCSExpCSParserRuleCall_2_0());

            }
            pushFollow(FollowSets000.FOLLOW_53);
            lv_ownedExpressionCS_2_0=ruleExpCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getLambdaLiteralExpCSRule());
              	        }
                     		set(
                     			current,
                     			"ownedExpressionCS",
                      		lv_ownedExpressionCS_2_0,
                      		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.ExpCS");
              	        afterParserOrEnumRuleCall();

            }

            }


            }

            otherlv_3=(Token)match(input,83,FollowSets000.FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_3, grammarAccess.getLambdaLiteralExpCSAccess().getRightCurlyBracketKeyword_3());

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
    // $ANTLR end "ruleLambdaLiteralExpCS"


    // $ANTLR start "entryRuleMapLiteralExpCS"
    // InternalCompleteOCL.g:4010:1: entryRuleMapLiteralExpCS returns [EObject current=null] : iv_ruleMapLiteralExpCS= ruleMapLiteralExpCS EOF ;
    public final EObject entryRuleMapLiteralExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMapLiteralExpCS = null;


        try {
            // InternalCompleteOCL.g:4011:2: (iv_ruleMapLiteralExpCS= ruleMapLiteralExpCS EOF )
            // InternalCompleteOCL.g:4012:2: iv_ruleMapLiteralExpCS= ruleMapLiteralExpCS EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getMapLiteralExpCSRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleMapLiteralExpCS=ruleMapLiteralExpCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleMapLiteralExpCS;
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
    // $ANTLR end "entryRuleMapLiteralExpCS"


    // $ANTLR start "ruleMapLiteralExpCS"
    // InternalCompleteOCL.g:4019:1: ruleMapLiteralExpCS returns [EObject current=null] : ( ( (lv_ownedType_0_0= ruleMapTypeCS ) ) otherlv_1= '{' ( ( (lv_ownedParts_2_0= ruleMapLiteralPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleMapLiteralPartCS ) ) )* )? otherlv_5= '}' ) ;
    public final EObject ruleMapLiteralExpCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        EObject lv_ownedType_0_0 = null;

        EObject lv_ownedParts_2_0 = null;

        EObject lv_ownedParts_4_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:4022:28: ( ( ( (lv_ownedType_0_0= ruleMapTypeCS ) ) otherlv_1= '{' ( ( (lv_ownedParts_2_0= ruleMapLiteralPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleMapLiteralPartCS ) ) )* )? otherlv_5= '}' ) )
            // InternalCompleteOCL.g:4023:1: ( ( (lv_ownedType_0_0= ruleMapTypeCS ) ) otherlv_1= '{' ( ( (lv_ownedParts_2_0= ruleMapLiteralPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleMapLiteralPartCS ) ) )* )? otherlv_5= '}' )
            {
            // InternalCompleteOCL.g:4023:1: ( ( (lv_ownedType_0_0= ruleMapTypeCS ) ) otherlv_1= '{' ( ( (lv_ownedParts_2_0= ruleMapLiteralPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleMapLiteralPartCS ) ) )* )? otherlv_5= '}' )
            // InternalCompleteOCL.g:4023:2: ( (lv_ownedType_0_0= ruleMapTypeCS ) ) otherlv_1= '{' ( ( (lv_ownedParts_2_0= ruleMapLiteralPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleMapLiteralPartCS ) ) )* )? otherlv_5= '}'
            {
            // InternalCompleteOCL.g:4023:2: ( (lv_ownedType_0_0= ruleMapTypeCS ) )
            // InternalCompleteOCL.g:4024:1: (lv_ownedType_0_0= ruleMapTypeCS )
            {
            // InternalCompleteOCL.g:4024:1: (lv_ownedType_0_0= ruleMapTypeCS )
            // InternalCompleteOCL.g:4025:3: lv_ownedType_0_0= ruleMapTypeCS
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getMapLiteralExpCSAccess().getOwnedTypeMapTypeCSParserRuleCall_0_0());

            }
            pushFollow(FollowSets000.FOLLOW_45);
            lv_ownedType_0_0=ruleMapTypeCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getMapLiteralExpCSRule());
              	        }
                     		set(
                     			current,
                     			"ownedType",
                      		lv_ownedType_0_0,
                      		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.MapTypeCS");
              	        afterParserOrEnumRuleCall();

            }

            }


            }

            otherlv_1=(Token)match(input,82,FollowSets000.FOLLOW_54); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getMapLiteralExpCSAccess().getLeftCurlyBracketKeyword_1());

            }
            // InternalCompleteOCL.g:4045:1: ( ( (lv_ownedParts_2_0= ruleMapLiteralPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleMapLiteralPartCS ) ) )* )?
            int alt88=2;
            int LA88_0 = input.LA(1);

            if ( ((LA88_0>=RULE_SIMPLE_ID && LA88_0<=RULE_SINGLE_QUOTED_STRING)||LA88_0==22||(LA88_0>=31 && LA88_0<=33)||(LA88_0>=43 && LA88_0<=56)||(LA88_0>=74 && LA88_0<=75)||(LA88_0>=77 && LA88_0<=81)||LA88_0==86||(LA88_0>=88 && LA88_0<=91)||LA88_0==98||(LA88_0>=103 && LA88_0<=104)) ) {
                alt88=1;
            }
            switch (alt88) {
                case 1 :
                    // InternalCompleteOCL.g:4045:2: ( (lv_ownedParts_2_0= ruleMapLiteralPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleMapLiteralPartCS ) ) )*
                    {
                    // InternalCompleteOCL.g:4045:2: ( (lv_ownedParts_2_0= ruleMapLiteralPartCS ) )
                    // InternalCompleteOCL.g:4046:1: (lv_ownedParts_2_0= ruleMapLiteralPartCS )
                    {
                    // InternalCompleteOCL.g:4046:1: (lv_ownedParts_2_0= ruleMapLiteralPartCS )
                    // InternalCompleteOCL.g:4047:3: lv_ownedParts_2_0= ruleMapLiteralPartCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getMapLiteralExpCSAccess().getOwnedPartsMapLiteralPartCSParserRuleCall_2_0_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_47);
                    lv_ownedParts_2_0=ruleMapLiteralPartCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getMapLiteralExpCSRule());
                      	        }
                             		add(
                             			current,
                             			"ownedParts",
                              		lv_ownedParts_2_0,
                              		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.MapLiteralPartCS");
                      	        afterParserOrEnumRuleCall();

                    }

                    }


                    }

                    // InternalCompleteOCL.g:4063:2: (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleMapLiteralPartCS ) ) )*
                    loop87:
                    do {
                        int alt87=2;
                        int LA87_0 = input.LA(1);

                        if ( (LA87_0==29) ) {
                            alt87=1;
                        }


                        switch (alt87) {
                    	case 1 :
                    	    // InternalCompleteOCL.g:4063:4: otherlv_3= ',' ( (lv_ownedParts_4_0= ruleMapLiteralPartCS ) )
                    	    {
                    	    otherlv_3=(Token)match(input,29,FollowSets000.FOLLOW_50); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_3, grammarAccess.getMapLiteralExpCSAccess().getCommaKeyword_2_1_0());

                    	    }
                    	    // InternalCompleteOCL.g:4067:1: ( (lv_ownedParts_4_0= ruleMapLiteralPartCS ) )
                    	    // InternalCompleteOCL.g:4068:1: (lv_ownedParts_4_0= ruleMapLiteralPartCS )
                    	    {
                    	    // InternalCompleteOCL.g:4068:1: (lv_ownedParts_4_0= ruleMapLiteralPartCS )
                    	    // InternalCompleteOCL.g:4069:3: lv_ownedParts_4_0= ruleMapLiteralPartCS
                    	    {
                    	    if ( state.backtracking==0 ) {

                    	      	        newCompositeNode(grammarAccess.getMapLiteralExpCSAccess().getOwnedPartsMapLiteralPartCSParserRuleCall_2_1_1_0());

                    	    }
                    	    pushFollow(FollowSets000.FOLLOW_47);
                    	    lv_ownedParts_4_0=ruleMapLiteralPartCS();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = createModelElementForParent(grammarAccess.getMapLiteralExpCSRule());
                    	      	        }
                    	             		add(
                    	             			current,
                    	             			"ownedParts",
                    	              		lv_ownedParts_4_0,
                    	              		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.MapLiteralPartCS");
                    	      	        afterParserOrEnumRuleCall();

                    	    }

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop87;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_5=(Token)match(input,83,FollowSets000.FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_5, grammarAccess.getMapLiteralExpCSAccess().getRightCurlyBracketKeyword_3());

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
    // $ANTLR end "ruleMapLiteralExpCS"


    // $ANTLR start "entryRuleMapLiteralPartCS"
    // InternalCompleteOCL.g:4097:1: entryRuleMapLiteralPartCS returns [EObject current=null] : iv_ruleMapLiteralPartCS= ruleMapLiteralPartCS EOF ;
    public final EObject entryRuleMapLiteralPartCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMapLiteralPartCS = null;


        try {
            // InternalCompleteOCL.g:4098:2: (iv_ruleMapLiteralPartCS= ruleMapLiteralPartCS EOF )
            // InternalCompleteOCL.g:4099:2: iv_ruleMapLiteralPartCS= ruleMapLiteralPartCS EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getMapLiteralPartCSRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleMapLiteralPartCS=ruleMapLiteralPartCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleMapLiteralPartCS;
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
    // $ANTLR end "entryRuleMapLiteralPartCS"


    // $ANTLR start "ruleMapLiteralPartCS"
    // InternalCompleteOCL.g:4106:1: ruleMapLiteralPartCS returns [EObject current=null] : ( ( (lv_ownedKey_0_0= ruleExpCS ) ) otherlv_1= '<-' ( (lv_ownedValue_2_0= ruleExpCS ) ) ) ;
    public final EObject ruleMapLiteralPartCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject lv_ownedKey_0_0 = null;

        EObject lv_ownedValue_2_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:4109:28: ( ( ( (lv_ownedKey_0_0= ruleExpCS ) ) otherlv_1= '<-' ( (lv_ownedValue_2_0= ruleExpCS ) ) ) )
            // InternalCompleteOCL.g:4110:1: ( ( (lv_ownedKey_0_0= ruleExpCS ) ) otherlv_1= '<-' ( (lv_ownedValue_2_0= ruleExpCS ) ) )
            {
            // InternalCompleteOCL.g:4110:1: ( ( (lv_ownedKey_0_0= ruleExpCS ) ) otherlv_1= '<-' ( (lv_ownedValue_2_0= ruleExpCS ) ) )
            // InternalCompleteOCL.g:4110:2: ( (lv_ownedKey_0_0= ruleExpCS ) ) otherlv_1= '<-' ( (lv_ownedValue_2_0= ruleExpCS ) )
            {
            // InternalCompleteOCL.g:4110:2: ( (lv_ownedKey_0_0= ruleExpCS ) )
            // InternalCompleteOCL.g:4111:1: (lv_ownedKey_0_0= ruleExpCS )
            {
            // InternalCompleteOCL.g:4111:1: (lv_ownedKey_0_0= ruleExpCS )
            // InternalCompleteOCL.g:4112:3: lv_ownedKey_0_0= ruleExpCS
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getMapLiteralPartCSAccess().getOwnedKeyExpCSParserRuleCall_0_0());

            }
            pushFollow(FollowSets000.FOLLOW_55);
            lv_ownedKey_0_0=ruleExpCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getMapLiteralPartCSRule());
              	        }
                     		set(
                     			current,
                     			"ownedKey",
                      		lv_ownedKey_0_0,
                      		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.ExpCS");
              	        afterParserOrEnumRuleCall();

            }

            }


            }

            otherlv_1=(Token)match(input,87,FollowSets000.FOLLOW_50); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getMapLiteralPartCSAccess().getLessThanSignHyphenMinusKeyword_1());

            }
            // InternalCompleteOCL.g:4132:1: ( (lv_ownedValue_2_0= ruleExpCS ) )
            // InternalCompleteOCL.g:4133:1: (lv_ownedValue_2_0= ruleExpCS )
            {
            // InternalCompleteOCL.g:4133:1: (lv_ownedValue_2_0= ruleExpCS )
            // InternalCompleteOCL.g:4134:3: lv_ownedValue_2_0= ruleExpCS
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getMapLiteralPartCSAccess().getOwnedValueExpCSParserRuleCall_2_0());

            }
            pushFollow(FollowSets000.FOLLOW_2);
            lv_ownedValue_2_0=ruleExpCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getMapLiteralPartCSRule());
              	        }
                     		set(
                     			current,
                     			"ownedValue",
                      		lv_ownedValue_2_0,
                      		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.ExpCS");
              	        afterParserOrEnumRuleCall();

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
    // $ANTLR end "ruleMapLiteralPartCS"


    // $ANTLR start "entryRulePrimitiveLiteralExpCS"
    // InternalCompleteOCL.g:4158:1: entryRulePrimitiveLiteralExpCS returns [EObject current=null] : iv_rulePrimitiveLiteralExpCS= rulePrimitiveLiteralExpCS EOF ;
    public final EObject entryRulePrimitiveLiteralExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePrimitiveLiteralExpCS = null;


        try {
            // InternalCompleteOCL.g:4159:2: (iv_rulePrimitiveLiteralExpCS= rulePrimitiveLiteralExpCS EOF )
            // InternalCompleteOCL.g:4160:2: iv_rulePrimitiveLiteralExpCS= rulePrimitiveLiteralExpCS EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getPrimitiveLiteralExpCSRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_rulePrimitiveLiteralExpCS=rulePrimitiveLiteralExpCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_rulePrimitiveLiteralExpCS;
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
    // $ANTLR end "entryRulePrimitiveLiteralExpCS"


    // $ANTLR start "rulePrimitiveLiteralExpCS"
    // InternalCompleteOCL.g:4167:1: rulePrimitiveLiteralExpCS returns [EObject current=null] : (this_NumberLiteralExpCS_0= ruleNumberLiteralExpCS | this_StringLiteralExpCS_1= ruleStringLiteralExpCS | this_BooleanLiteralExpCS_2= ruleBooleanLiteralExpCS | this_UnlimitedNaturalLiteralExpCS_3= ruleUnlimitedNaturalLiteralExpCS | this_InvalidLiteralExpCS_4= ruleInvalidLiteralExpCS | this_NullLiteralExpCS_5= ruleNullLiteralExpCS ) ;
    public final EObject rulePrimitiveLiteralExpCS() throws RecognitionException {
        EObject current = null;

        EObject this_NumberLiteralExpCS_0 = null;

        EObject this_StringLiteralExpCS_1 = null;

        EObject this_BooleanLiteralExpCS_2 = null;

        EObject this_UnlimitedNaturalLiteralExpCS_3 = null;

        EObject this_InvalidLiteralExpCS_4 = null;

        EObject this_NullLiteralExpCS_5 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:4170:28: ( (this_NumberLiteralExpCS_0= ruleNumberLiteralExpCS | this_StringLiteralExpCS_1= ruleStringLiteralExpCS | this_BooleanLiteralExpCS_2= ruleBooleanLiteralExpCS | this_UnlimitedNaturalLiteralExpCS_3= ruleUnlimitedNaturalLiteralExpCS | this_InvalidLiteralExpCS_4= ruleInvalidLiteralExpCS | this_NullLiteralExpCS_5= ruleNullLiteralExpCS ) )
            // InternalCompleteOCL.g:4171:1: (this_NumberLiteralExpCS_0= ruleNumberLiteralExpCS | this_StringLiteralExpCS_1= ruleStringLiteralExpCS | this_BooleanLiteralExpCS_2= ruleBooleanLiteralExpCS | this_UnlimitedNaturalLiteralExpCS_3= ruleUnlimitedNaturalLiteralExpCS | this_InvalidLiteralExpCS_4= ruleInvalidLiteralExpCS | this_NullLiteralExpCS_5= ruleNullLiteralExpCS )
            {
            // InternalCompleteOCL.g:4171:1: (this_NumberLiteralExpCS_0= ruleNumberLiteralExpCS | this_StringLiteralExpCS_1= ruleStringLiteralExpCS | this_BooleanLiteralExpCS_2= ruleBooleanLiteralExpCS | this_UnlimitedNaturalLiteralExpCS_3= ruleUnlimitedNaturalLiteralExpCS | this_InvalidLiteralExpCS_4= ruleInvalidLiteralExpCS | this_NullLiteralExpCS_5= ruleNullLiteralExpCS )
            int alt89=6;
            switch ( input.LA(1) ) {
            case RULE_INT:
                {
                alt89=1;
                }
                break;
            case RULE_SINGLE_QUOTED_STRING:
                {
                alt89=2;
                }
                break;
            case 88:
            case 89:
                {
                alt89=3;
                }
                break;
            case 56:
                {
                alt89=4;
                }
                break;
            case 90:
                {
                alt89=5;
                }
                break;
            case 91:
                {
                alt89=6;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 89, 0, input);

                throw nvae;
            }

            switch (alt89) {
                case 1 :
                    // InternalCompleteOCL.g:4172:2: this_NumberLiteralExpCS_0= ruleNumberLiteralExpCS
                    {
                    if ( state.backtracking==0 ) {

                      	  /* */

                    }
                    if ( state.backtracking==0 ) {

                              newCompositeNode(grammarAccess.getPrimitiveLiteralExpCSAccess().getNumberLiteralExpCSParserRuleCall_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_NumberLiteralExpCS_0=ruleNumberLiteralExpCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_NumberLiteralExpCS_0;
                              afterParserOrEnumRuleCall();

                    }

                    }
                    break;
                case 2 :
                    // InternalCompleteOCL.g:4185:2: this_StringLiteralExpCS_1= ruleStringLiteralExpCS
                    {
                    if ( state.backtracking==0 ) {

                      	  /* */

                    }
                    if ( state.backtracking==0 ) {

                              newCompositeNode(grammarAccess.getPrimitiveLiteralExpCSAccess().getStringLiteralExpCSParserRuleCall_1());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_StringLiteralExpCS_1=ruleStringLiteralExpCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_StringLiteralExpCS_1;
                              afterParserOrEnumRuleCall();

                    }

                    }
                    break;
                case 3 :
                    // InternalCompleteOCL.g:4198:2: this_BooleanLiteralExpCS_2= ruleBooleanLiteralExpCS
                    {
                    if ( state.backtracking==0 ) {

                      	  /* */

                    }
                    if ( state.backtracking==0 ) {

                              newCompositeNode(grammarAccess.getPrimitiveLiteralExpCSAccess().getBooleanLiteralExpCSParserRuleCall_2());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_BooleanLiteralExpCS_2=ruleBooleanLiteralExpCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_BooleanLiteralExpCS_2;
                              afterParserOrEnumRuleCall();

                    }

                    }
                    break;
                case 4 :
                    // InternalCompleteOCL.g:4211:2: this_UnlimitedNaturalLiteralExpCS_3= ruleUnlimitedNaturalLiteralExpCS
                    {
                    if ( state.backtracking==0 ) {

                      	  /* */

                    }
                    if ( state.backtracking==0 ) {

                              newCompositeNode(grammarAccess.getPrimitiveLiteralExpCSAccess().getUnlimitedNaturalLiteralExpCSParserRuleCall_3());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_UnlimitedNaturalLiteralExpCS_3=ruleUnlimitedNaturalLiteralExpCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_UnlimitedNaturalLiteralExpCS_3;
                              afterParserOrEnumRuleCall();

                    }

                    }
                    break;
                case 5 :
                    // InternalCompleteOCL.g:4224:2: this_InvalidLiteralExpCS_4= ruleInvalidLiteralExpCS
                    {
                    if ( state.backtracking==0 ) {

                      	  /* */

                    }
                    if ( state.backtracking==0 ) {

                              newCompositeNode(grammarAccess.getPrimitiveLiteralExpCSAccess().getInvalidLiteralExpCSParserRuleCall_4());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_InvalidLiteralExpCS_4=ruleInvalidLiteralExpCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_InvalidLiteralExpCS_4;
                              afterParserOrEnumRuleCall();

                    }

                    }
                    break;
                case 6 :
                    // InternalCompleteOCL.g:4237:2: this_NullLiteralExpCS_5= ruleNullLiteralExpCS
                    {
                    if ( state.backtracking==0 ) {

                      	  /* */

                    }
                    if ( state.backtracking==0 ) {

                              newCompositeNode(grammarAccess.getPrimitiveLiteralExpCSAccess().getNullLiteralExpCSParserRuleCall_5());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_NullLiteralExpCS_5=ruleNullLiteralExpCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_NullLiteralExpCS_5;
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
    // $ANTLR end "rulePrimitiveLiteralExpCS"


    // $ANTLR start "entryRuleTupleLiteralExpCS"
    // InternalCompleteOCL.g:4256:1: entryRuleTupleLiteralExpCS returns [EObject current=null] : iv_ruleTupleLiteralExpCS= ruleTupleLiteralExpCS EOF ;
    public final EObject entryRuleTupleLiteralExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTupleLiteralExpCS = null;


        try {
            // InternalCompleteOCL.g:4257:2: (iv_ruleTupleLiteralExpCS= ruleTupleLiteralExpCS EOF )
            // InternalCompleteOCL.g:4258:2: iv_ruleTupleLiteralExpCS= ruleTupleLiteralExpCS EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getTupleLiteralExpCSRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleTupleLiteralExpCS=ruleTupleLiteralExpCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleTupleLiteralExpCS;
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
    // $ANTLR end "entryRuleTupleLiteralExpCS"


    // $ANTLR start "ruleTupleLiteralExpCS"
    // InternalCompleteOCL.g:4265:1: ruleTupleLiteralExpCS returns [EObject current=null] : (otherlv_0= 'Tuple' otherlv_1= '{' ( (lv_ownedParts_2_0= ruleTupleLiteralPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleTupleLiteralPartCS ) ) )* otherlv_5= '}' ) ;
    public final EObject ruleTupleLiteralExpCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        EObject lv_ownedParts_2_0 = null;

        EObject lv_ownedParts_4_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:4268:28: ( (otherlv_0= 'Tuple' otherlv_1= '{' ( (lv_ownedParts_2_0= ruleTupleLiteralPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleTupleLiteralPartCS ) ) )* otherlv_5= '}' ) )
            // InternalCompleteOCL.g:4269:1: (otherlv_0= 'Tuple' otherlv_1= '{' ( (lv_ownedParts_2_0= ruleTupleLiteralPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleTupleLiteralPartCS ) ) )* otherlv_5= '}' )
            {
            // InternalCompleteOCL.g:4269:1: (otherlv_0= 'Tuple' otherlv_1= '{' ( (lv_ownedParts_2_0= ruleTupleLiteralPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleTupleLiteralPartCS ) ) )* otherlv_5= '}' )
            // InternalCompleteOCL.g:4269:3: otherlv_0= 'Tuple' otherlv_1= '{' ( (lv_ownedParts_2_0= ruleTupleLiteralPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleTupleLiteralPartCS ) ) )* otherlv_5= '}'
            {
            otherlv_0=(Token)match(input,75,FollowSets000.FOLLOW_45); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getTupleLiteralExpCSAccess().getTupleKeyword_0());

            }
            otherlv_1=(Token)match(input,82,FollowSets000.FOLLOW_19); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getTupleLiteralExpCSAccess().getLeftCurlyBracketKeyword_1());

            }
            // InternalCompleteOCL.g:4277:1: ( (lv_ownedParts_2_0= ruleTupleLiteralPartCS ) )
            // InternalCompleteOCL.g:4278:1: (lv_ownedParts_2_0= ruleTupleLiteralPartCS )
            {
            // InternalCompleteOCL.g:4278:1: (lv_ownedParts_2_0= ruleTupleLiteralPartCS )
            // InternalCompleteOCL.g:4279:3: lv_ownedParts_2_0= ruleTupleLiteralPartCS
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getTupleLiteralExpCSAccess().getOwnedPartsTupleLiteralPartCSParserRuleCall_2_0());

            }
            pushFollow(FollowSets000.FOLLOW_47);
            lv_ownedParts_2_0=ruleTupleLiteralPartCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getTupleLiteralExpCSRule());
              	        }
                     		add(
                     			current,
                     			"ownedParts",
                      		lv_ownedParts_2_0,
                      		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.TupleLiteralPartCS");
              	        afterParserOrEnumRuleCall();

            }

            }


            }

            // InternalCompleteOCL.g:4295:2: (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleTupleLiteralPartCS ) ) )*
            loop90:
            do {
                int alt90=2;
                int LA90_0 = input.LA(1);

                if ( (LA90_0==29) ) {
                    alt90=1;
                }


                switch (alt90) {
            	case 1 :
            	    // InternalCompleteOCL.g:4295:4: otherlv_3= ',' ( (lv_ownedParts_4_0= ruleTupleLiteralPartCS ) )
            	    {
            	    otherlv_3=(Token)match(input,29,FollowSets000.FOLLOW_19); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_3, grammarAccess.getTupleLiteralExpCSAccess().getCommaKeyword_3_0());

            	    }
            	    // InternalCompleteOCL.g:4299:1: ( (lv_ownedParts_4_0= ruleTupleLiteralPartCS ) )
            	    // InternalCompleteOCL.g:4300:1: (lv_ownedParts_4_0= ruleTupleLiteralPartCS )
            	    {
            	    // InternalCompleteOCL.g:4300:1: (lv_ownedParts_4_0= ruleTupleLiteralPartCS )
            	    // InternalCompleteOCL.g:4301:3: lv_ownedParts_4_0= ruleTupleLiteralPartCS
            	    {
            	    if ( state.backtracking==0 ) {

            	      	        newCompositeNode(grammarAccess.getTupleLiteralExpCSAccess().getOwnedPartsTupleLiteralPartCSParserRuleCall_3_1_0());

            	    }
            	    pushFollow(FollowSets000.FOLLOW_47);
            	    lv_ownedParts_4_0=ruleTupleLiteralPartCS();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getTupleLiteralExpCSRule());
            	      	        }
            	             		add(
            	             			current,
            	             			"ownedParts",
            	              		lv_ownedParts_4_0,
            	              		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.TupleLiteralPartCS");
            	      	        afterParserOrEnumRuleCall();

            	    }

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop90;
                }
            } while (true);

            otherlv_5=(Token)match(input,83,FollowSets000.FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_5, grammarAccess.getTupleLiteralExpCSAccess().getRightCurlyBracketKeyword_4());

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
    // $ANTLR end "ruleTupleLiteralExpCS"


    // $ANTLR start "entryRuleTupleLiteralPartCS"
    // InternalCompleteOCL.g:4329:1: entryRuleTupleLiteralPartCS returns [EObject current=null] : iv_ruleTupleLiteralPartCS= ruleTupleLiteralPartCS EOF ;
    public final EObject entryRuleTupleLiteralPartCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTupleLiteralPartCS = null;


        try {
            // InternalCompleteOCL.g:4330:2: (iv_ruleTupleLiteralPartCS= ruleTupleLiteralPartCS EOF )
            // InternalCompleteOCL.g:4331:2: iv_ruleTupleLiteralPartCS= ruleTupleLiteralPartCS EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getTupleLiteralPartCSRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleTupleLiteralPartCS=ruleTupleLiteralPartCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleTupleLiteralPartCS;
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
    // $ANTLR end "entryRuleTupleLiteralPartCS"


    // $ANTLR start "ruleTupleLiteralPartCS"
    // InternalCompleteOCL.g:4338:1: ruleTupleLiteralPartCS returns [EObject current=null] : ( ( (lv_name_0_0= ruleUnrestrictedName ) ) (otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) ) )? otherlv_3= '=' ( (lv_ownedInitExpression_4_0= ruleExpCS ) ) ) ;
    public final EObject ruleTupleLiteralPartCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        AntlrDatatypeRuleToken lv_name_0_0 = null;

        EObject lv_ownedType_2_0 = null;

        EObject lv_ownedInitExpression_4_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:4341:28: ( ( ( (lv_name_0_0= ruleUnrestrictedName ) ) (otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) ) )? otherlv_3= '=' ( (lv_ownedInitExpression_4_0= ruleExpCS ) ) ) )
            // InternalCompleteOCL.g:4342:1: ( ( (lv_name_0_0= ruleUnrestrictedName ) ) (otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) ) )? otherlv_3= '=' ( (lv_ownedInitExpression_4_0= ruleExpCS ) ) )
            {
            // InternalCompleteOCL.g:4342:1: ( ( (lv_name_0_0= ruleUnrestrictedName ) ) (otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) ) )? otherlv_3= '=' ( (lv_ownedInitExpression_4_0= ruleExpCS ) ) )
            // InternalCompleteOCL.g:4342:2: ( (lv_name_0_0= ruleUnrestrictedName ) ) (otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) ) )? otherlv_3= '=' ( (lv_ownedInitExpression_4_0= ruleExpCS ) )
            {
            // InternalCompleteOCL.g:4342:2: ( (lv_name_0_0= ruleUnrestrictedName ) )
            // InternalCompleteOCL.g:4343:1: (lv_name_0_0= ruleUnrestrictedName )
            {
            // InternalCompleteOCL.g:4343:1: (lv_name_0_0= ruleUnrestrictedName )
            // InternalCompleteOCL.g:4344:3: lv_name_0_0= ruleUnrestrictedName
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getTupleLiteralPartCSAccess().getNameUnrestrictedNameParserRuleCall_0_0());

            }
            pushFollow(FollowSets000.FOLLOW_56);
            lv_name_0_0=ruleUnrestrictedName();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getTupleLiteralPartCSRule());
              	        }
                     		set(
                     			current,
                     			"name",
                      		lv_name_0_0,
                      		"org.eclipse.ocl.xtext.completeocl.CompleteOCL.UnrestrictedName");
              	        afterParserOrEnumRuleCall();

            }

            }


            }

            // InternalCompleteOCL.g:4360:2: (otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) ) )?
            int alt91=2;
            int LA91_0 = input.LA(1);

            if ( (LA91_0==24) ) {
                alt91=1;
            }
            switch (alt91) {
                case 1 :
                    // InternalCompleteOCL.g:4360:4: otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) )
                    {
                    otherlv_1=(Token)match(input,24,FollowSets000.FOLLOW_25); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_1, grammarAccess.getTupleLiteralPartCSAccess().getColonKeyword_1_0());

                    }
                    // InternalCompleteOCL.g:4364:1: ( (lv_ownedType_2_0= ruleTypeExpCS ) )
                    // InternalCompleteOCL.g:4365:1: (lv_ownedType_2_0= ruleTypeExpCS )
                    {
                    // InternalCompleteOCL.g:4365:1: (lv_ownedType_2_0= ruleTypeExpCS )
                    // InternalCompleteOCL.g:4366:3: lv_ownedType_2_0= ruleTypeExpCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getTupleLiteralPartCSAccess().getOwnedTypeTypeExpCSParserRuleCall_1_1_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_24);
                    lv_ownedType_2_0=ruleTypeExpCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getTupleLiteralPartCSRule());
                      	        }
                             		set(
                             			current,
                             			"ownedType",
                              		lv_ownedType_2_0,
                              		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.TypeExpCS");
                      	        afterParserOrEnumRuleCall();

                    }

                    }


                    }


                    }
                    break;

            }

            otherlv_3=(Token)match(input,30,FollowSets000.FOLLOW_50); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_3, grammarAccess.getTupleLiteralPartCSAccess().getEqualsSignKeyword_2());

            }
            // InternalCompleteOCL.g:4386:1: ( (lv_ownedInitExpression_4_0= ruleExpCS ) )
            // InternalCompleteOCL.g:4387:1: (lv_ownedInitExpression_4_0= ruleExpCS )
            {
            // InternalCompleteOCL.g:4387:1: (lv_ownedInitExpression_4_0= ruleExpCS )
            // InternalCompleteOCL.g:4388:3: lv_ownedInitExpression_4_0= ruleExpCS
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getTupleLiteralPartCSAccess().getOwnedInitExpressionExpCSParserRuleCall_3_0());

            }
            pushFollow(FollowSets000.FOLLOW_2);
            lv_ownedInitExpression_4_0=ruleExpCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getTupleLiteralPartCSRule());
              	        }
                     		set(
                     			current,
                     			"ownedInitExpression",
                      		lv_ownedInitExpression_4_0,
                      		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.ExpCS");
              	        afterParserOrEnumRuleCall();

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
    // $ANTLR end "ruleTupleLiteralPartCS"


    // $ANTLR start "entryRuleNumberLiteralExpCS"
    // InternalCompleteOCL.g:4412:1: entryRuleNumberLiteralExpCS returns [EObject current=null] : iv_ruleNumberLiteralExpCS= ruleNumberLiteralExpCS EOF ;
    public final EObject entryRuleNumberLiteralExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNumberLiteralExpCS = null;


        try {
            // InternalCompleteOCL.g:4413:2: (iv_ruleNumberLiteralExpCS= ruleNumberLiteralExpCS EOF )
            // InternalCompleteOCL.g:4414:2: iv_ruleNumberLiteralExpCS= ruleNumberLiteralExpCS EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getNumberLiteralExpCSRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleNumberLiteralExpCS=ruleNumberLiteralExpCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleNumberLiteralExpCS;
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
    // $ANTLR end "entryRuleNumberLiteralExpCS"


    // $ANTLR start "ruleNumberLiteralExpCS"
    // InternalCompleteOCL.g:4421:1: ruleNumberLiteralExpCS returns [EObject current=null] : ( (lv_symbol_0_0= ruleNUMBER_LITERAL ) ) ;
    public final EObject ruleNumberLiteralExpCS() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_symbol_0_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:4424:28: ( ( (lv_symbol_0_0= ruleNUMBER_LITERAL ) ) )
            // InternalCompleteOCL.g:4425:1: ( (lv_symbol_0_0= ruleNUMBER_LITERAL ) )
            {
            // InternalCompleteOCL.g:4425:1: ( (lv_symbol_0_0= ruleNUMBER_LITERAL ) )
            // InternalCompleteOCL.g:4426:1: (lv_symbol_0_0= ruleNUMBER_LITERAL )
            {
            // InternalCompleteOCL.g:4426:1: (lv_symbol_0_0= ruleNUMBER_LITERAL )
            // InternalCompleteOCL.g:4427:3: lv_symbol_0_0= ruleNUMBER_LITERAL
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getNumberLiteralExpCSAccess().getSymbolNUMBER_LITERALParserRuleCall_0());

            }
            pushFollow(FollowSets000.FOLLOW_2);
            lv_symbol_0_0=ruleNUMBER_LITERAL();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getNumberLiteralExpCSRule());
              	        }
                     		set(
                     			current,
                     			"symbol",
                      		lv_symbol_0_0,
                      		"org.eclipse.ocl.xtext.base.Base.NUMBER_LITERAL");
              	        afterParserOrEnumRuleCall();

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
    // $ANTLR end "ruleNumberLiteralExpCS"


    // $ANTLR start "entryRuleStringLiteralExpCS"
    // InternalCompleteOCL.g:4451:1: entryRuleStringLiteralExpCS returns [EObject current=null] : iv_ruleStringLiteralExpCS= ruleStringLiteralExpCS EOF ;
    public final EObject entryRuleStringLiteralExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleStringLiteralExpCS = null;


        try {
            // InternalCompleteOCL.g:4452:2: (iv_ruleStringLiteralExpCS= ruleStringLiteralExpCS EOF )
            // InternalCompleteOCL.g:4453:2: iv_ruleStringLiteralExpCS= ruleStringLiteralExpCS EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getStringLiteralExpCSRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleStringLiteralExpCS=ruleStringLiteralExpCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleStringLiteralExpCS;
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
    // $ANTLR end "entryRuleStringLiteralExpCS"


    // $ANTLR start "ruleStringLiteralExpCS"
    // InternalCompleteOCL.g:4460:1: ruleStringLiteralExpCS returns [EObject current=null] : ( (lv_segments_0_0= ruleStringLiteral ) )+ ;
    public final EObject ruleStringLiteralExpCS() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_segments_0_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:4463:28: ( ( (lv_segments_0_0= ruleStringLiteral ) )+ )
            // InternalCompleteOCL.g:4464:1: ( (lv_segments_0_0= ruleStringLiteral ) )+
            {
            // InternalCompleteOCL.g:4464:1: ( (lv_segments_0_0= ruleStringLiteral ) )+
            int cnt92=0;
            loop92:
            do {
                int alt92=2;
                int LA92_0 = input.LA(1);

                if ( (LA92_0==RULE_SINGLE_QUOTED_STRING) ) {
                    alt92=1;
                }


                switch (alt92) {
            	case 1 :
            	    // InternalCompleteOCL.g:4465:1: (lv_segments_0_0= ruleStringLiteral )
            	    {
            	    // InternalCompleteOCL.g:4465:1: (lv_segments_0_0= ruleStringLiteral )
            	    // InternalCompleteOCL.g:4466:3: lv_segments_0_0= ruleStringLiteral
            	    {
            	    if ( state.backtracking==0 ) {

            	      	        newCompositeNode(grammarAccess.getStringLiteralExpCSAccess().getSegmentsStringLiteralParserRuleCall_0());

            	    }
            	    pushFollow(FollowSets000.FOLLOW_57);
            	    lv_segments_0_0=ruleStringLiteral();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getStringLiteralExpCSRule());
            	      	        }
            	             		add(
            	             			current,
            	             			"segments",
            	              		lv_segments_0_0,
            	              		"org.eclipse.ocl.xtext.base.Base.StringLiteral");
            	      	        afterParserOrEnumRuleCall();

            	    }

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt92 >= 1 ) break loop92;
            	    if (state.backtracking>0) {state.failed=true; return current;}
                        EarlyExitException eee =
                            new EarlyExitException(92, input);
                        throw eee;
                }
                cnt92++;
            } while (true);


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
    // $ANTLR end "ruleStringLiteralExpCS"


    // $ANTLR start "entryRuleBooleanLiteralExpCS"
    // InternalCompleteOCL.g:4490:1: entryRuleBooleanLiteralExpCS returns [EObject current=null] : iv_ruleBooleanLiteralExpCS= ruleBooleanLiteralExpCS EOF ;
    public final EObject entryRuleBooleanLiteralExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleBooleanLiteralExpCS = null;


        try {
            // InternalCompleteOCL.g:4491:2: (iv_ruleBooleanLiteralExpCS= ruleBooleanLiteralExpCS EOF )
            // InternalCompleteOCL.g:4492:2: iv_ruleBooleanLiteralExpCS= ruleBooleanLiteralExpCS EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getBooleanLiteralExpCSRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleBooleanLiteralExpCS=ruleBooleanLiteralExpCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleBooleanLiteralExpCS;
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
    // $ANTLR end "entryRuleBooleanLiteralExpCS"


    // $ANTLR start "ruleBooleanLiteralExpCS"
    // InternalCompleteOCL.g:4499:1: ruleBooleanLiteralExpCS returns [EObject current=null] : ( ( (lv_symbol_0_0= 'true' ) ) | ( (lv_symbol_1_0= 'false' ) ) ) ;
    public final EObject ruleBooleanLiteralExpCS() throws RecognitionException {
        EObject current = null;

        Token lv_symbol_0_0=null;
        Token lv_symbol_1_0=null;

         enterRule();

        try {
            // InternalCompleteOCL.g:4502:28: ( ( ( (lv_symbol_0_0= 'true' ) ) | ( (lv_symbol_1_0= 'false' ) ) ) )
            // InternalCompleteOCL.g:4503:1: ( ( (lv_symbol_0_0= 'true' ) ) | ( (lv_symbol_1_0= 'false' ) ) )
            {
            // InternalCompleteOCL.g:4503:1: ( ( (lv_symbol_0_0= 'true' ) ) | ( (lv_symbol_1_0= 'false' ) ) )
            int alt93=2;
            int LA93_0 = input.LA(1);

            if ( (LA93_0==88) ) {
                alt93=1;
            }
            else if ( (LA93_0==89) ) {
                alt93=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 93, 0, input);

                throw nvae;
            }
            switch (alt93) {
                case 1 :
                    // InternalCompleteOCL.g:4503:2: ( (lv_symbol_0_0= 'true' ) )
                    {
                    // InternalCompleteOCL.g:4503:2: ( (lv_symbol_0_0= 'true' ) )
                    // InternalCompleteOCL.g:4504:1: (lv_symbol_0_0= 'true' )
                    {
                    // InternalCompleteOCL.g:4504:1: (lv_symbol_0_0= 'true' )
                    // InternalCompleteOCL.g:4505:3: lv_symbol_0_0= 'true'
                    {
                    lv_symbol_0_0=(Token)match(input,88,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_symbol_0_0, grammarAccess.getBooleanLiteralExpCSAccess().getSymbolTrueKeyword_0_0());

                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getBooleanLiteralExpCSRule());
                      	        }
                             		setWithLastConsumed(current, "symbol", lv_symbol_0_0, "true");

                    }

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalCompleteOCL.g:4519:6: ( (lv_symbol_1_0= 'false' ) )
                    {
                    // InternalCompleteOCL.g:4519:6: ( (lv_symbol_1_0= 'false' ) )
                    // InternalCompleteOCL.g:4520:1: (lv_symbol_1_0= 'false' )
                    {
                    // InternalCompleteOCL.g:4520:1: (lv_symbol_1_0= 'false' )
                    // InternalCompleteOCL.g:4521:3: lv_symbol_1_0= 'false'
                    {
                    lv_symbol_1_0=(Token)match(input,89,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_symbol_1_0, grammarAccess.getBooleanLiteralExpCSAccess().getSymbolFalseKeyword_1_0());

                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getBooleanLiteralExpCSRule());
                      	        }
                             		setWithLastConsumed(current, "symbol", lv_symbol_1_0, "false");

                    }

                    }


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
    // $ANTLR end "ruleBooleanLiteralExpCS"


    // $ANTLR start "entryRuleUnlimitedNaturalLiteralExpCS"
    // InternalCompleteOCL.g:4542:1: entryRuleUnlimitedNaturalLiteralExpCS returns [EObject current=null] : iv_ruleUnlimitedNaturalLiteralExpCS= ruleUnlimitedNaturalLiteralExpCS EOF ;
    public final EObject entryRuleUnlimitedNaturalLiteralExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleUnlimitedNaturalLiteralExpCS = null;


        try {
            // InternalCompleteOCL.g:4543:2: (iv_ruleUnlimitedNaturalLiteralExpCS= ruleUnlimitedNaturalLiteralExpCS EOF )
            // InternalCompleteOCL.g:4544:2: iv_ruleUnlimitedNaturalLiteralExpCS= ruleUnlimitedNaturalLiteralExpCS EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getUnlimitedNaturalLiteralExpCSRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleUnlimitedNaturalLiteralExpCS=ruleUnlimitedNaturalLiteralExpCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleUnlimitedNaturalLiteralExpCS;
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
    // $ANTLR end "entryRuleUnlimitedNaturalLiteralExpCS"


    // $ANTLR start "ruleUnlimitedNaturalLiteralExpCS"
    // InternalCompleteOCL.g:4551:1: ruleUnlimitedNaturalLiteralExpCS returns [EObject current=null] : ( () otherlv_1= '*' ) ;
    public final EObject ruleUnlimitedNaturalLiteralExpCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;

         enterRule();

        try {
            // InternalCompleteOCL.g:4554:28: ( ( () otherlv_1= '*' ) )
            // InternalCompleteOCL.g:4555:1: ( () otherlv_1= '*' )
            {
            // InternalCompleteOCL.g:4555:1: ( () otherlv_1= '*' )
            // InternalCompleteOCL.g:4555:2: () otherlv_1= '*'
            {
            // InternalCompleteOCL.g:4555:2: ()
            // InternalCompleteOCL.g:4556:2:
            {
            if ( state.backtracking==0 ) {

              	  /* */

            }
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getUnlimitedNaturalLiteralExpCSAccess().getUnlimitedNaturalLiteralExpCSAction_0(),
                          current);

            }

            }

            otherlv_1=(Token)match(input,56,FollowSets000.FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getUnlimitedNaturalLiteralExpCSAccess().getAsteriskKeyword_1());

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
    // $ANTLR end "ruleUnlimitedNaturalLiteralExpCS"


    // $ANTLR start "entryRuleInvalidLiteralExpCS"
    // InternalCompleteOCL.g:4576:1: entryRuleInvalidLiteralExpCS returns [EObject current=null] : iv_ruleInvalidLiteralExpCS= ruleInvalidLiteralExpCS EOF ;
    public final EObject entryRuleInvalidLiteralExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleInvalidLiteralExpCS = null;


        try {
            // InternalCompleteOCL.g:4577:2: (iv_ruleInvalidLiteralExpCS= ruleInvalidLiteralExpCS EOF )
            // InternalCompleteOCL.g:4578:2: iv_ruleInvalidLiteralExpCS= ruleInvalidLiteralExpCS EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getInvalidLiteralExpCSRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleInvalidLiteralExpCS=ruleInvalidLiteralExpCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleInvalidLiteralExpCS;
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
    // $ANTLR end "entryRuleInvalidLiteralExpCS"


    // $ANTLR start "ruleInvalidLiteralExpCS"
    // InternalCompleteOCL.g:4585:1: ruleInvalidLiteralExpCS returns [EObject current=null] : ( () otherlv_1= 'invalid' ) ;
    public final EObject ruleInvalidLiteralExpCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;

         enterRule();

        try {
            // InternalCompleteOCL.g:4588:28: ( ( () otherlv_1= 'invalid' ) )
            // InternalCompleteOCL.g:4589:1: ( () otherlv_1= 'invalid' )
            {
            // InternalCompleteOCL.g:4589:1: ( () otherlv_1= 'invalid' )
            // InternalCompleteOCL.g:4589:2: () otherlv_1= 'invalid'
            {
            // InternalCompleteOCL.g:4589:2: ()
            // InternalCompleteOCL.g:4590:2:
            {
            if ( state.backtracking==0 ) {

              	  /* */

            }
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getInvalidLiteralExpCSAccess().getInvalidLiteralExpCSAction_0(),
                          current);

            }

            }

            otherlv_1=(Token)match(input,90,FollowSets000.FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getInvalidLiteralExpCSAccess().getInvalidKeyword_1());

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
    // $ANTLR end "ruleInvalidLiteralExpCS"


    // $ANTLR start "entryRuleNullLiteralExpCS"
    // InternalCompleteOCL.g:4610:1: entryRuleNullLiteralExpCS returns [EObject current=null] : iv_ruleNullLiteralExpCS= ruleNullLiteralExpCS EOF ;
    public final EObject entryRuleNullLiteralExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNullLiteralExpCS = null;


        try {
            // InternalCompleteOCL.g:4611:2: (iv_ruleNullLiteralExpCS= ruleNullLiteralExpCS EOF )
            // InternalCompleteOCL.g:4612:2: iv_ruleNullLiteralExpCS= ruleNullLiteralExpCS EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getNullLiteralExpCSRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleNullLiteralExpCS=ruleNullLiteralExpCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleNullLiteralExpCS;
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
    // $ANTLR end "entryRuleNullLiteralExpCS"


    // $ANTLR start "ruleNullLiteralExpCS"
    // InternalCompleteOCL.g:4619:1: ruleNullLiteralExpCS returns [EObject current=null] : ( () otherlv_1= 'null' ) ;
    public final EObject ruleNullLiteralExpCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;

         enterRule();

        try {
            // InternalCompleteOCL.g:4622:28: ( ( () otherlv_1= 'null' ) )
            // InternalCompleteOCL.g:4623:1: ( () otherlv_1= 'null' )
            {
            // InternalCompleteOCL.g:4623:1: ( () otherlv_1= 'null' )
            // InternalCompleteOCL.g:4623:2: () otherlv_1= 'null'
            {
            // InternalCompleteOCL.g:4623:2: ()
            // InternalCompleteOCL.g:4624:2:
            {
            if ( state.backtracking==0 ) {

              	  /* */

            }
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getNullLiteralExpCSAccess().getNullLiteralExpCSAction_0(),
                          current);

            }

            }

            otherlv_1=(Token)match(input,91,FollowSets000.FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getNullLiteralExpCSAccess().getNullKeyword_1());

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
    // $ANTLR end "ruleNullLiteralExpCS"


    // $ANTLR start "entryRuleTypeLiteralCS"
    // InternalCompleteOCL.g:4644:1: entryRuleTypeLiteralCS returns [EObject current=null] : iv_ruleTypeLiteralCS= ruleTypeLiteralCS EOF ;
    public final EObject entryRuleTypeLiteralCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTypeLiteralCS = null;


        try {
            // InternalCompleteOCL.g:4645:2: (iv_ruleTypeLiteralCS= ruleTypeLiteralCS EOF )
            // InternalCompleteOCL.g:4646:2: iv_ruleTypeLiteralCS= ruleTypeLiteralCS EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getTypeLiteralCSRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleTypeLiteralCS=ruleTypeLiteralCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleTypeLiteralCS;
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
    // $ANTLR end "entryRuleTypeLiteralCS"


    // $ANTLR start "ruleTypeLiteralCS"
    // InternalCompleteOCL.g:4653:1: ruleTypeLiteralCS returns [EObject current=null] : (this_PrimitiveTypeCS_0= rulePrimitiveTypeCS | this_CollectionTypeCS_1= ruleCollectionTypeCS | this_MapTypeCS_2= ruleMapTypeCS | this_TupleTypeCS_3= ruleTupleTypeCS ) ;
    public final EObject ruleTypeLiteralCS() throws RecognitionException {
        EObject current = null;

        EObject this_PrimitiveTypeCS_0 = null;

        EObject this_CollectionTypeCS_1 = null;

        EObject this_MapTypeCS_2 = null;

        EObject this_TupleTypeCS_3 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:4656:28: ( (this_PrimitiveTypeCS_0= rulePrimitiveTypeCS | this_CollectionTypeCS_1= ruleCollectionTypeCS | this_MapTypeCS_2= ruleMapTypeCS | this_TupleTypeCS_3= ruleTupleTypeCS ) )
            // InternalCompleteOCL.g:4657:1: (this_PrimitiveTypeCS_0= rulePrimitiveTypeCS | this_CollectionTypeCS_1= ruleCollectionTypeCS | this_MapTypeCS_2= ruleMapTypeCS | this_TupleTypeCS_3= ruleTupleTypeCS )
            {
            // InternalCompleteOCL.g:4657:1: (this_PrimitiveTypeCS_0= rulePrimitiveTypeCS | this_CollectionTypeCS_1= ruleCollectionTypeCS | this_MapTypeCS_2= ruleMapTypeCS | this_TupleTypeCS_3= ruleTupleTypeCS )
            int alt94=4;
            switch ( input.LA(1) ) {
            case 43:
            case 44:
            case 45:
            case 46:
            case 47:
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
                {
                alt94=1;
                }
                break;
            case 77:
            case 78:
            case 79:
            case 80:
            case 81:
                {
                alt94=2;
                }
                break;
            case 74:
                {
                alt94=3;
                }
                break;
            case 75:
                {
                alt94=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 94, 0, input);

                throw nvae;
            }

            switch (alt94) {
                case 1 :
                    // InternalCompleteOCL.g:4658:2: this_PrimitiveTypeCS_0= rulePrimitiveTypeCS
                    {
                    if ( state.backtracking==0 ) {

                      	  /* */

                    }
                    if ( state.backtracking==0 ) {

                              newCompositeNode(grammarAccess.getTypeLiteralCSAccess().getPrimitiveTypeCSParserRuleCall_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_PrimitiveTypeCS_0=rulePrimitiveTypeCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_PrimitiveTypeCS_0;
                              afterParserOrEnumRuleCall();

                    }

                    }
                    break;
                case 2 :
                    // InternalCompleteOCL.g:4671:2: this_CollectionTypeCS_1= ruleCollectionTypeCS
                    {
                    if ( state.backtracking==0 ) {

                      	  /* */

                    }
                    if ( state.backtracking==0 ) {

                              newCompositeNode(grammarAccess.getTypeLiteralCSAccess().getCollectionTypeCSParserRuleCall_1());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_CollectionTypeCS_1=ruleCollectionTypeCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_CollectionTypeCS_1;
                              afterParserOrEnumRuleCall();

                    }

                    }
                    break;
                case 3 :
                    // InternalCompleteOCL.g:4684:2: this_MapTypeCS_2= ruleMapTypeCS
                    {
                    if ( state.backtracking==0 ) {

                      	  /* */

                    }
                    if ( state.backtracking==0 ) {

                              newCompositeNode(grammarAccess.getTypeLiteralCSAccess().getMapTypeCSParserRuleCall_2());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_MapTypeCS_2=ruleMapTypeCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_MapTypeCS_2;
                              afterParserOrEnumRuleCall();

                    }

                    }
                    break;
                case 4 :
                    // InternalCompleteOCL.g:4697:2: this_TupleTypeCS_3= ruleTupleTypeCS
                    {
                    if ( state.backtracking==0 ) {

                      	  /* */

                    }
                    if ( state.backtracking==0 ) {

                              newCompositeNode(grammarAccess.getTypeLiteralCSAccess().getTupleTypeCSParserRuleCall_3());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_TupleTypeCS_3=ruleTupleTypeCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_TupleTypeCS_3;
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
    // $ANTLR end "ruleTypeLiteralCS"


    // $ANTLR start "entryRuleTypeLiteralWithMultiplicityCS"
    // InternalCompleteOCL.g:4716:1: entryRuleTypeLiteralWithMultiplicityCS returns [EObject current=null] : iv_ruleTypeLiteralWithMultiplicityCS= ruleTypeLiteralWithMultiplicityCS EOF ;
    public final EObject entryRuleTypeLiteralWithMultiplicityCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTypeLiteralWithMultiplicityCS = null;


        try {
            // InternalCompleteOCL.g:4717:2: (iv_ruleTypeLiteralWithMultiplicityCS= ruleTypeLiteralWithMultiplicityCS EOF )
            // InternalCompleteOCL.g:4718:2: iv_ruleTypeLiteralWithMultiplicityCS= ruleTypeLiteralWithMultiplicityCS EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getTypeLiteralWithMultiplicityCSRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleTypeLiteralWithMultiplicityCS=ruleTypeLiteralWithMultiplicityCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleTypeLiteralWithMultiplicityCS;
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
    // $ANTLR end "entryRuleTypeLiteralWithMultiplicityCS"


    // $ANTLR start "ruleTypeLiteralWithMultiplicityCS"
    // InternalCompleteOCL.g:4725:1: ruleTypeLiteralWithMultiplicityCS returns [EObject current=null] : (this_TypeLiteralCS_0= ruleTypeLiteralCS ( (lv_ownedMultiplicity_1_0= ruleMultiplicityCS ) )? ) ;
    public final EObject ruleTypeLiteralWithMultiplicityCS() throws RecognitionException {
        EObject current = null;

        EObject this_TypeLiteralCS_0 = null;

        EObject lv_ownedMultiplicity_1_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:4728:28: ( (this_TypeLiteralCS_0= ruleTypeLiteralCS ( (lv_ownedMultiplicity_1_0= ruleMultiplicityCS ) )? ) )
            // InternalCompleteOCL.g:4729:1: (this_TypeLiteralCS_0= ruleTypeLiteralCS ( (lv_ownedMultiplicity_1_0= ruleMultiplicityCS ) )? )
            {
            // InternalCompleteOCL.g:4729:1: (this_TypeLiteralCS_0= ruleTypeLiteralCS ( (lv_ownedMultiplicity_1_0= ruleMultiplicityCS ) )? )
            // InternalCompleteOCL.g:4730:2: this_TypeLiteralCS_0= ruleTypeLiteralCS ( (lv_ownedMultiplicity_1_0= ruleMultiplicityCS ) )?
            {
            if ( state.backtracking==0 ) {

              	  /* */

            }
            if ( state.backtracking==0 ) {

                      newCompositeNode(grammarAccess.getTypeLiteralWithMultiplicityCSAccess().getTypeLiteralCSParserRuleCall_0());

            }
            pushFollow(FollowSets000.FOLLOW_58);
            this_TypeLiteralCS_0=ruleTypeLiteralCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

                      current = this_TypeLiteralCS_0;
                      afterParserOrEnumRuleCall();

            }
            // InternalCompleteOCL.g:4741:1: ( (lv_ownedMultiplicity_1_0= ruleMultiplicityCS ) )?
            int alt95=2;
            int LA95_0 = input.LA(1);

            if ( (LA95_0==93) ) {
                alt95=1;
            }
            switch (alt95) {
                case 1 :
                    // InternalCompleteOCL.g:4742:1: (lv_ownedMultiplicity_1_0= ruleMultiplicityCS )
                    {
                    // InternalCompleteOCL.g:4742:1: (lv_ownedMultiplicity_1_0= ruleMultiplicityCS )
                    // InternalCompleteOCL.g:4743:3: lv_ownedMultiplicity_1_0= ruleMultiplicityCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getTypeLiteralWithMultiplicityCSAccess().getOwnedMultiplicityMultiplicityCSParserRuleCall_1_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    lv_ownedMultiplicity_1_0=ruleMultiplicityCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getTypeLiteralWithMultiplicityCSRule());
                      	        }
                             		set(
                             			current,
                             			"ownedMultiplicity",
                              		lv_ownedMultiplicity_1_0,
                              		"org.eclipse.ocl.xtext.base.Base.MultiplicityCS");
                      	        afterParserOrEnumRuleCall();

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
    // $ANTLR end "ruleTypeLiteralWithMultiplicityCS"


    // $ANTLR start "entryRuleTypeLiteralExpCS"
    // InternalCompleteOCL.g:4767:1: entryRuleTypeLiteralExpCS returns [EObject current=null] : iv_ruleTypeLiteralExpCS= ruleTypeLiteralExpCS EOF ;
    public final EObject entryRuleTypeLiteralExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTypeLiteralExpCS = null;


        try {
            // InternalCompleteOCL.g:4768:2: (iv_ruleTypeLiteralExpCS= ruleTypeLiteralExpCS EOF )
            // InternalCompleteOCL.g:4769:2: iv_ruleTypeLiteralExpCS= ruleTypeLiteralExpCS EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getTypeLiteralExpCSRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleTypeLiteralExpCS=ruleTypeLiteralExpCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleTypeLiteralExpCS;
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
    // $ANTLR end "entryRuleTypeLiteralExpCS"


    // $ANTLR start "ruleTypeLiteralExpCS"
    // InternalCompleteOCL.g:4776:1: ruleTypeLiteralExpCS returns [EObject current=null] : ( (lv_ownedType_0_0= ruleTypeLiteralWithMultiplicityCS ) ) ;
    public final EObject ruleTypeLiteralExpCS() throws RecognitionException {
        EObject current = null;

        EObject lv_ownedType_0_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:4779:28: ( ( (lv_ownedType_0_0= ruleTypeLiteralWithMultiplicityCS ) ) )
            // InternalCompleteOCL.g:4780:1: ( (lv_ownedType_0_0= ruleTypeLiteralWithMultiplicityCS ) )
            {
            // InternalCompleteOCL.g:4780:1: ( (lv_ownedType_0_0= ruleTypeLiteralWithMultiplicityCS ) )
            // InternalCompleteOCL.g:4781:1: (lv_ownedType_0_0= ruleTypeLiteralWithMultiplicityCS )
            {
            // InternalCompleteOCL.g:4781:1: (lv_ownedType_0_0= ruleTypeLiteralWithMultiplicityCS )
            // InternalCompleteOCL.g:4782:3: lv_ownedType_0_0= ruleTypeLiteralWithMultiplicityCS
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getTypeLiteralExpCSAccess().getOwnedTypeTypeLiteralWithMultiplicityCSParserRuleCall_0());

            }
            pushFollow(FollowSets000.FOLLOW_2);
            lv_ownedType_0_0=ruleTypeLiteralWithMultiplicityCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getTypeLiteralExpCSRule());
              	        }
                     		set(
                     			current,
                     			"ownedType",
                      		lv_ownedType_0_0,
                      		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.TypeLiteralWithMultiplicityCS");
              	        afterParserOrEnumRuleCall();

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
    // $ANTLR end "ruleTypeLiteralExpCS"


    // $ANTLR start "entryRuleTypeNameExpCS"
    // InternalCompleteOCL.g:4806:1: entryRuleTypeNameExpCS returns [EObject current=null] : iv_ruleTypeNameExpCS= ruleTypeNameExpCS EOF ;
    public final EObject entryRuleTypeNameExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTypeNameExpCS = null;


        try {
            // InternalCompleteOCL.g:4807:2: (iv_ruleTypeNameExpCS= ruleTypeNameExpCS EOF )
            // InternalCompleteOCL.g:4808:2: iv_ruleTypeNameExpCS= ruleTypeNameExpCS EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getTypeNameExpCSRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleTypeNameExpCS=ruleTypeNameExpCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleTypeNameExpCS;
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
    // $ANTLR end "entryRuleTypeNameExpCS"


    // $ANTLR start "ruleTypeNameExpCS"
    // InternalCompleteOCL.g:4815:1: ruleTypeNameExpCS returns [EObject current=null] : ( ( (lv_ownedPathName_0_0= rulePathNameCS ) ) ( ( (lv_ownedCurlyBracketedClause_1_0= ruleCurlyBracketedClauseCS ) ) (otherlv_2= '{' ( (lv_ownedPatternGuard_3_0= ruleExpCS ) ) otherlv_4= '}' )? )? ) ;
    public final EObject ruleTypeNameExpCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_ownedPathName_0_0 = null;

        EObject lv_ownedCurlyBracketedClause_1_0 = null;

        EObject lv_ownedPatternGuard_3_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:4818:28: ( ( ( (lv_ownedPathName_0_0= rulePathNameCS ) ) ( ( (lv_ownedCurlyBracketedClause_1_0= ruleCurlyBracketedClauseCS ) ) (otherlv_2= '{' ( (lv_ownedPatternGuard_3_0= ruleExpCS ) ) otherlv_4= '}' )? )? ) )
            // InternalCompleteOCL.g:4819:1: ( ( (lv_ownedPathName_0_0= rulePathNameCS ) ) ( ( (lv_ownedCurlyBracketedClause_1_0= ruleCurlyBracketedClauseCS ) ) (otherlv_2= '{' ( (lv_ownedPatternGuard_3_0= ruleExpCS ) ) otherlv_4= '}' )? )? )
            {
            // InternalCompleteOCL.g:4819:1: ( ( (lv_ownedPathName_0_0= rulePathNameCS ) ) ( ( (lv_ownedCurlyBracketedClause_1_0= ruleCurlyBracketedClauseCS ) ) (otherlv_2= '{' ( (lv_ownedPatternGuard_3_0= ruleExpCS ) ) otherlv_4= '}' )? )? )
            // InternalCompleteOCL.g:4819:2: ( (lv_ownedPathName_0_0= rulePathNameCS ) ) ( ( (lv_ownedCurlyBracketedClause_1_0= ruleCurlyBracketedClauseCS ) ) (otherlv_2= '{' ( (lv_ownedPatternGuard_3_0= ruleExpCS ) ) otherlv_4= '}' )? )?
            {
            // InternalCompleteOCL.g:4819:2: ( (lv_ownedPathName_0_0= rulePathNameCS ) )
            // InternalCompleteOCL.g:4820:1: (lv_ownedPathName_0_0= rulePathNameCS )
            {
            // InternalCompleteOCL.g:4820:1: (lv_ownedPathName_0_0= rulePathNameCS )
            // InternalCompleteOCL.g:4821:3: lv_ownedPathName_0_0= rulePathNameCS
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getTypeNameExpCSAccess().getOwnedPathNamePathNameCSParserRuleCall_0_0());

            }
            pushFollow(FollowSets000.FOLLOW_59);
            lv_ownedPathName_0_0=rulePathNameCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getTypeNameExpCSRule());
              	        }
                     		set(
                     			current,
                     			"ownedPathName",
                      		lv_ownedPathName_0_0,
                      		"org.eclipse.ocl.xtext.base.Base.PathNameCS");
              	        afterParserOrEnumRuleCall();

            }

            }


            }

            // InternalCompleteOCL.g:4837:2: ( ( (lv_ownedCurlyBracketedClause_1_0= ruleCurlyBracketedClauseCS ) ) (otherlv_2= '{' ( (lv_ownedPatternGuard_3_0= ruleExpCS ) ) otherlv_4= '}' )? )?
            int alt97=2;
            int LA97_0 = input.LA(1);

            if ( (LA97_0==82) ) {
                alt97=1;
            }
            switch (alt97) {
                case 1 :
                    // InternalCompleteOCL.g:4837:3: ( (lv_ownedCurlyBracketedClause_1_0= ruleCurlyBracketedClauseCS ) ) (otherlv_2= '{' ( (lv_ownedPatternGuard_3_0= ruleExpCS ) ) otherlv_4= '}' )?
                    {
                    // InternalCompleteOCL.g:4837:3: ( (lv_ownedCurlyBracketedClause_1_0= ruleCurlyBracketedClauseCS ) )
                    // InternalCompleteOCL.g:4838:1: (lv_ownedCurlyBracketedClause_1_0= ruleCurlyBracketedClauseCS )
                    {
                    // InternalCompleteOCL.g:4838:1: (lv_ownedCurlyBracketedClause_1_0= ruleCurlyBracketedClauseCS )
                    // InternalCompleteOCL.g:4839:3: lv_ownedCurlyBracketedClause_1_0= ruleCurlyBracketedClauseCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getTypeNameExpCSAccess().getOwnedCurlyBracketedClauseCurlyBracketedClauseCSParserRuleCall_1_0_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_59);
                    lv_ownedCurlyBracketedClause_1_0=ruleCurlyBracketedClauseCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getTypeNameExpCSRule());
                      	        }
                             		set(
                             			current,
                             			"ownedCurlyBracketedClause",
                              		lv_ownedCurlyBracketedClause_1_0,
                              		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.CurlyBracketedClauseCS");
                      	        afterParserOrEnumRuleCall();

                    }

                    }


                    }

                    // InternalCompleteOCL.g:4855:2: (otherlv_2= '{' ( (lv_ownedPatternGuard_3_0= ruleExpCS ) ) otherlv_4= '}' )?
                    int alt96=2;
                    int LA96_0 = input.LA(1);

                    if ( (LA96_0==82) ) {
                        alt96=1;
                    }
                    switch (alt96) {
                        case 1 :
                            // InternalCompleteOCL.g:4855:4: otherlv_2= '{' ( (lv_ownedPatternGuard_3_0= ruleExpCS ) ) otherlv_4= '}'
                            {
                            otherlv_2=(Token)match(input,82,FollowSets000.FOLLOW_50); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                  	newLeafNode(otherlv_2, grammarAccess.getTypeNameExpCSAccess().getLeftCurlyBracketKeyword_1_1_0());

                            }
                            // InternalCompleteOCL.g:4859:1: ( (lv_ownedPatternGuard_3_0= ruleExpCS ) )
                            // InternalCompleteOCL.g:4860:1: (lv_ownedPatternGuard_3_0= ruleExpCS )
                            {
                            // InternalCompleteOCL.g:4860:1: (lv_ownedPatternGuard_3_0= ruleExpCS )
                            // InternalCompleteOCL.g:4861:3: lv_ownedPatternGuard_3_0= ruleExpCS
                            {
                            if ( state.backtracking==0 ) {

                              	        newCompositeNode(grammarAccess.getTypeNameExpCSAccess().getOwnedPatternGuardExpCSParserRuleCall_1_1_1_0());

                            }
                            pushFollow(FollowSets000.FOLLOW_53);
                            lv_ownedPatternGuard_3_0=ruleExpCS();

                            state._fsp--;
                            if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElementForParent(grammarAccess.getTypeNameExpCSRule());
                              	        }
                                     		set(
                                     			current,
                                     			"ownedPatternGuard",
                                      		lv_ownedPatternGuard_3_0,
                                      		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.ExpCS");
                              	        afterParserOrEnumRuleCall();

                            }

                            }


                            }

                            otherlv_4=(Token)match(input,83,FollowSets000.FOLLOW_2); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                  	newLeafNode(otherlv_4, grammarAccess.getTypeNameExpCSAccess().getRightCurlyBracketKeyword_1_1_2());

                            }

                            }
                            break;

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
    // $ANTLR end "ruleTypeNameExpCS"


    // $ANTLR start "entryRuleTypeExpWithoutMultiplicityCS"
    // InternalCompleteOCL.g:4889:1: entryRuleTypeExpWithoutMultiplicityCS returns [EObject current=null] : iv_ruleTypeExpWithoutMultiplicityCS= ruleTypeExpWithoutMultiplicityCS EOF ;
    public final EObject entryRuleTypeExpWithoutMultiplicityCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTypeExpWithoutMultiplicityCS = null;


        try {
            // InternalCompleteOCL.g:4890:2: (iv_ruleTypeExpWithoutMultiplicityCS= ruleTypeExpWithoutMultiplicityCS EOF )
            // InternalCompleteOCL.g:4891:2: iv_ruleTypeExpWithoutMultiplicityCS= ruleTypeExpWithoutMultiplicityCS EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getTypeExpWithoutMultiplicityCSRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleTypeExpWithoutMultiplicityCS=ruleTypeExpWithoutMultiplicityCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleTypeExpWithoutMultiplicityCS;
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
    // $ANTLR end "entryRuleTypeExpWithoutMultiplicityCS"


    // $ANTLR start "ruleTypeExpWithoutMultiplicityCS"
    // InternalCompleteOCL.g:4898:1: ruleTypeExpWithoutMultiplicityCS returns [EObject current=null] : (this_TypeNameExpCS_0= ruleTypeNameExpCS | this_TypeLiteralCS_1= ruleTypeLiteralCS | this_CollectionPatternCS_2= ruleCollectionPatternCS ) ;
    public final EObject ruleTypeExpWithoutMultiplicityCS() throws RecognitionException {
        EObject current = null;

        EObject this_TypeNameExpCS_0 = null;

        EObject this_TypeLiteralCS_1 = null;

        EObject this_CollectionPatternCS_2 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:4901:28: ( (this_TypeNameExpCS_0= ruleTypeNameExpCS | this_TypeLiteralCS_1= ruleTypeLiteralCS | this_CollectionPatternCS_2= ruleCollectionPatternCS ) )
            // InternalCompleteOCL.g:4902:1: (this_TypeNameExpCS_0= ruleTypeNameExpCS | this_TypeLiteralCS_1= ruleTypeLiteralCS | this_CollectionPatternCS_2= ruleCollectionPatternCS )
            {
            // InternalCompleteOCL.g:4902:1: (this_TypeNameExpCS_0= ruleTypeNameExpCS | this_TypeLiteralCS_1= ruleTypeLiteralCS | this_CollectionPatternCS_2= ruleCollectionPatternCS )
            int alt98=3;
            alt98 = dfa98.predict(input);
            switch (alt98) {
                case 1 :
                    // InternalCompleteOCL.g:4903:2: this_TypeNameExpCS_0= ruleTypeNameExpCS
                    {
                    if ( state.backtracking==0 ) {

                      	  /* */

                    }
                    if ( state.backtracking==0 ) {

                              newCompositeNode(grammarAccess.getTypeExpWithoutMultiplicityCSAccess().getTypeNameExpCSParserRuleCall_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_TypeNameExpCS_0=ruleTypeNameExpCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_TypeNameExpCS_0;
                              afterParserOrEnumRuleCall();

                    }

                    }
                    break;
                case 2 :
                    // InternalCompleteOCL.g:4916:2: this_TypeLiteralCS_1= ruleTypeLiteralCS
                    {
                    if ( state.backtracking==0 ) {

                      	  /* */

                    }
                    if ( state.backtracking==0 ) {

                              newCompositeNode(grammarAccess.getTypeExpWithoutMultiplicityCSAccess().getTypeLiteralCSParserRuleCall_1());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_TypeLiteralCS_1=ruleTypeLiteralCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_TypeLiteralCS_1;
                              afterParserOrEnumRuleCall();

                    }

                    }
                    break;
                case 3 :
                    // InternalCompleteOCL.g:4929:2: this_CollectionPatternCS_2= ruleCollectionPatternCS
                    {
                    if ( state.backtracking==0 ) {

                      	  /* */

                    }
                    if ( state.backtracking==0 ) {

                              newCompositeNode(grammarAccess.getTypeExpWithoutMultiplicityCSAccess().getCollectionPatternCSParserRuleCall_2());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_CollectionPatternCS_2=ruleCollectionPatternCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_CollectionPatternCS_2;
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
    // $ANTLR end "ruleTypeExpWithoutMultiplicityCS"


    // $ANTLR start "entryRuleTypeExpCS"
    // InternalCompleteOCL.g:4948:1: entryRuleTypeExpCS returns [EObject current=null] : iv_ruleTypeExpCS= ruleTypeExpCS EOF ;
    public final EObject entryRuleTypeExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTypeExpCS = null;


        try {
            // InternalCompleteOCL.g:4949:2: (iv_ruleTypeExpCS= ruleTypeExpCS EOF )
            // InternalCompleteOCL.g:4950:2: iv_ruleTypeExpCS= ruleTypeExpCS EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getTypeExpCSRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleTypeExpCS=ruleTypeExpCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleTypeExpCS;
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
    // $ANTLR end "entryRuleTypeExpCS"


    // $ANTLR start "ruleTypeExpCS"
    // InternalCompleteOCL.g:4957:1: ruleTypeExpCS returns [EObject current=null] : (this_TypeExpWithoutMultiplicityCS_0= ruleTypeExpWithoutMultiplicityCS ( (lv_ownedMultiplicity_1_0= ruleMultiplicityCS ) )? ) ;
    public final EObject ruleTypeExpCS() throws RecognitionException {
        EObject current = null;

        EObject this_TypeExpWithoutMultiplicityCS_0 = null;

        EObject lv_ownedMultiplicity_1_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:4960:28: ( (this_TypeExpWithoutMultiplicityCS_0= ruleTypeExpWithoutMultiplicityCS ( (lv_ownedMultiplicity_1_0= ruleMultiplicityCS ) )? ) )
            // InternalCompleteOCL.g:4961:1: (this_TypeExpWithoutMultiplicityCS_0= ruleTypeExpWithoutMultiplicityCS ( (lv_ownedMultiplicity_1_0= ruleMultiplicityCS ) )? )
            {
            // InternalCompleteOCL.g:4961:1: (this_TypeExpWithoutMultiplicityCS_0= ruleTypeExpWithoutMultiplicityCS ( (lv_ownedMultiplicity_1_0= ruleMultiplicityCS ) )? )
            // InternalCompleteOCL.g:4962:2: this_TypeExpWithoutMultiplicityCS_0= ruleTypeExpWithoutMultiplicityCS ( (lv_ownedMultiplicity_1_0= ruleMultiplicityCS ) )?
            {
            if ( state.backtracking==0 ) {

              	  /* */

            }
            if ( state.backtracking==0 ) {

                      newCompositeNode(grammarAccess.getTypeExpCSAccess().getTypeExpWithoutMultiplicityCSParserRuleCall_0());

            }
            pushFollow(FollowSets000.FOLLOW_58);
            this_TypeExpWithoutMultiplicityCS_0=ruleTypeExpWithoutMultiplicityCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

                      current = this_TypeExpWithoutMultiplicityCS_0;
                      afterParserOrEnumRuleCall();

            }
            // InternalCompleteOCL.g:4973:1: ( (lv_ownedMultiplicity_1_0= ruleMultiplicityCS ) )?
            int alt99=2;
            int LA99_0 = input.LA(1);

            if ( (LA99_0==93) ) {
                alt99=1;
            }
            switch (alt99) {
                case 1 :
                    // InternalCompleteOCL.g:4974:1: (lv_ownedMultiplicity_1_0= ruleMultiplicityCS )
                    {
                    // InternalCompleteOCL.g:4974:1: (lv_ownedMultiplicity_1_0= ruleMultiplicityCS )
                    // InternalCompleteOCL.g:4975:3: lv_ownedMultiplicity_1_0= ruleMultiplicityCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getTypeExpCSAccess().getOwnedMultiplicityMultiplicityCSParserRuleCall_1_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    lv_ownedMultiplicity_1_0=ruleMultiplicityCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getTypeExpCSRule());
                      	        }
                             		set(
                             			current,
                             			"ownedMultiplicity",
                              		lv_ownedMultiplicity_1_0,
                              		"org.eclipse.ocl.xtext.base.Base.MultiplicityCS");
                      	        afterParserOrEnumRuleCall();

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
    // $ANTLR end "ruleTypeExpCS"


    // $ANTLR start "entryRuleExpCS"
    // InternalCompleteOCL.g:4999:1: entryRuleExpCS returns [EObject current=null] : iv_ruleExpCS= ruleExpCS EOF ;
    public final EObject entryRuleExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpCS = null;


        try {
            // InternalCompleteOCL.g:5000:2: (iv_ruleExpCS= ruleExpCS EOF )
            // InternalCompleteOCL.g:5001:2: iv_ruleExpCS= ruleExpCS EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getExpCSRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleExpCS=ruleExpCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleExpCS;
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
    // $ANTLR end "entryRuleExpCS"


    // $ANTLR start "ruleExpCS"
    // InternalCompleteOCL.g:5008:1: ruleExpCS returns [EObject current=null] : ( (this_PrefixedPrimaryExpCS_0= rulePrefixedPrimaryExpCS ( () ( (lv_name_2_0= ruleBinaryOperatorName ) ) ( (lv_ownedRight_3_0= ruleExpCS ) ) )? ) | this_PrefixedLetExpCS_4= rulePrefixedLetExpCS ) ;
    public final EObject ruleExpCS() throws RecognitionException {
        EObject current = null;

        EObject this_PrefixedPrimaryExpCS_0 = null;

        AntlrDatatypeRuleToken lv_name_2_0 = null;

        EObject lv_ownedRight_3_0 = null;

        EObject this_PrefixedLetExpCS_4 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:5011:28: ( ( (this_PrefixedPrimaryExpCS_0= rulePrefixedPrimaryExpCS ( () ( (lv_name_2_0= ruleBinaryOperatorName ) ) ( (lv_ownedRight_3_0= ruleExpCS ) ) )? ) | this_PrefixedLetExpCS_4= rulePrefixedLetExpCS ) )
            // InternalCompleteOCL.g:5012:1: ( (this_PrefixedPrimaryExpCS_0= rulePrefixedPrimaryExpCS ( () ( (lv_name_2_0= ruleBinaryOperatorName ) ) ( (lv_ownedRight_3_0= ruleExpCS ) ) )? ) | this_PrefixedLetExpCS_4= rulePrefixedLetExpCS )
            {
            // InternalCompleteOCL.g:5012:1: ( (this_PrefixedPrimaryExpCS_0= rulePrefixedPrimaryExpCS ( () ( (lv_name_2_0= ruleBinaryOperatorName ) ) ( (lv_ownedRight_3_0= ruleExpCS ) ) )? ) | this_PrefixedLetExpCS_4= rulePrefixedLetExpCS )
            int alt101=2;
            alt101 = dfa101.predict(input);
            switch (alt101) {
                case 1 :
                    // InternalCompleteOCL.g:5012:2: (this_PrefixedPrimaryExpCS_0= rulePrefixedPrimaryExpCS ( () ( (lv_name_2_0= ruleBinaryOperatorName ) ) ( (lv_ownedRight_3_0= ruleExpCS ) ) )? )
                    {
                    // InternalCompleteOCL.g:5012:2: (this_PrefixedPrimaryExpCS_0= rulePrefixedPrimaryExpCS ( () ( (lv_name_2_0= ruleBinaryOperatorName ) ) ( (lv_ownedRight_3_0= ruleExpCS ) ) )? )
                    // InternalCompleteOCL.g:5013:2: this_PrefixedPrimaryExpCS_0= rulePrefixedPrimaryExpCS ( () ( (lv_name_2_0= ruleBinaryOperatorName ) ) ( (lv_ownedRight_3_0= ruleExpCS ) ) )?
                    {
                    if ( state.backtracking==0 ) {

                      	  /* */

                    }
                    if ( state.backtracking==0 ) {

                              newCompositeNode(grammarAccess.getExpCSAccess().getPrefixedPrimaryExpCSParserRuleCall_0_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_60);
                    this_PrefixedPrimaryExpCS_0=rulePrefixedPrimaryExpCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_PrefixedPrimaryExpCS_0;
                              afterParserOrEnumRuleCall();

                    }
                    // InternalCompleteOCL.g:5024:1: ( () ( (lv_name_2_0= ruleBinaryOperatorName ) ) ( (lv_ownedRight_3_0= ruleExpCS ) ) )?
                    int alt100=2;
                    int LA100_0 = input.LA(1);

                    if ( ((LA100_0>=18 && LA100_0<=19)||LA100_0==30||(LA100_0>=40 && LA100_0<=41)||LA100_0==53||(LA100_0>=56 && LA100_0<=73)) ) {
                        alt100=1;
                    }
                    switch (alt100) {
                        case 1 :
                            // InternalCompleteOCL.g:5024:2: () ( (lv_name_2_0= ruleBinaryOperatorName ) ) ( (lv_ownedRight_3_0= ruleExpCS ) )
                            {
                            // InternalCompleteOCL.g:5024:2: ()
                            // InternalCompleteOCL.g:5025:2:
                            {
                            if ( state.backtracking==0 ) {

                              	  /* */

                            }
                            if ( state.backtracking==0 ) {

                                      current = forceCreateModelElementAndSet(
                                          grammarAccess.getExpCSAccess().getInfixExpCSOwnedLeftAction_0_1_0(),
                                          current);

                            }

                            }

                            // InternalCompleteOCL.g:5033:2: ( (lv_name_2_0= ruleBinaryOperatorName ) )
                            // InternalCompleteOCL.g:5034:1: (lv_name_2_0= ruleBinaryOperatorName )
                            {
                            // InternalCompleteOCL.g:5034:1: (lv_name_2_0= ruleBinaryOperatorName )
                            // InternalCompleteOCL.g:5035:3: lv_name_2_0= ruleBinaryOperatorName
                            {
                            if ( state.backtracking==0 ) {

                              	        newCompositeNode(grammarAccess.getExpCSAccess().getNameBinaryOperatorNameParserRuleCall_0_1_1_0());

                            }
                            pushFollow(FollowSets000.FOLLOW_50);
                            lv_name_2_0=ruleBinaryOperatorName();

                            state._fsp--;
                            if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElementForParent(grammarAccess.getExpCSRule());
                              	        }
                                     		set(
                                     			current,
                                     			"name",
                                      		lv_name_2_0,
                                      		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.BinaryOperatorName");
                              	        afterParserOrEnumRuleCall();

                            }

                            }


                            }

                            // InternalCompleteOCL.g:5051:2: ( (lv_ownedRight_3_0= ruleExpCS ) )
                            // InternalCompleteOCL.g:5052:1: (lv_ownedRight_3_0= ruleExpCS )
                            {
                            // InternalCompleteOCL.g:5052:1: (lv_ownedRight_3_0= ruleExpCS )
                            // InternalCompleteOCL.g:5053:3: lv_ownedRight_3_0= ruleExpCS
                            {
                            if ( state.backtracking==0 ) {

                              	        newCompositeNode(grammarAccess.getExpCSAccess().getOwnedRightExpCSParserRuleCall_0_1_2_0());

                            }
                            pushFollow(FollowSets000.FOLLOW_2);
                            lv_ownedRight_3_0=ruleExpCS();

                            state._fsp--;
                            if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElementForParent(grammarAccess.getExpCSRule());
                              	        }
                                     		set(
                                     			current,
                                     			"ownedRight",
                                      		lv_ownedRight_3_0,
                                      		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.ExpCS");
                              	        afterParserOrEnumRuleCall();

                            }

                            }


                            }


                            }
                            break;

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalCompleteOCL.g:5071:2: this_PrefixedLetExpCS_4= rulePrefixedLetExpCS
                    {
                    if ( state.backtracking==0 ) {

                      	  /* */

                    }
                    if ( state.backtracking==0 ) {

                              newCompositeNode(grammarAccess.getExpCSAccess().getPrefixedLetExpCSParserRuleCall_1());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_PrefixedLetExpCS_4=rulePrefixedLetExpCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_PrefixedLetExpCS_4;
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
    // $ANTLR end "ruleExpCS"


    // $ANTLR start "entryRulePrefixedLetExpCS"
    // InternalCompleteOCL.g:5090:1: entryRulePrefixedLetExpCS returns [EObject current=null] : iv_rulePrefixedLetExpCS= rulePrefixedLetExpCS EOF ;
    public final EObject entryRulePrefixedLetExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePrefixedLetExpCS = null;


        try {
            // InternalCompleteOCL.g:5091:2: (iv_rulePrefixedLetExpCS= rulePrefixedLetExpCS EOF )
            // InternalCompleteOCL.g:5092:2: iv_rulePrefixedLetExpCS= rulePrefixedLetExpCS EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getPrefixedLetExpCSRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_rulePrefixedLetExpCS=rulePrefixedLetExpCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_rulePrefixedLetExpCS;
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
    // $ANTLR end "entryRulePrefixedLetExpCS"


    // $ANTLR start "rulePrefixedLetExpCS"
    // InternalCompleteOCL.g:5099:1: rulePrefixedLetExpCS returns [EObject current=null] : ( ( () ( (lv_name_1_0= ruleUnaryOperatorName ) ) ( (lv_ownedRight_2_0= rulePrefixedLetExpCS ) ) ) | this_LetExpCS_3= ruleLetExpCS ) ;
    public final EObject rulePrefixedLetExpCS() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_name_1_0 = null;

        EObject lv_ownedRight_2_0 = null;

        EObject this_LetExpCS_3 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:5102:28: ( ( ( () ( (lv_name_1_0= ruleUnaryOperatorName ) ) ( (lv_ownedRight_2_0= rulePrefixedLetExpCS ) ) ) | this_LetExpCS_3= ruleLetExpCS ) )
            // InternalCompleteOCL.g:5103:1: ( ( () ( (lv_name_1_0= ruleUnaryOperatorName ) ) ( (lv_ownedRight_2_0= rulePrefixedLetExpCS ) ) ) | this_LetExpCS_3= ruleLetExpCS )
            {
            // InternalCompleteOCL.g:5103:1: ( ( () ( (lv_name_1_0= ruleUnaryOperatorName ) ) ( (lv_ownedRight_2_0= rulePrefixedLetExpCS ) ) ) | this_LetExpCS_3= ruleLetExpCS )
            int alt102=2;
            int LA102_0 = input.LA(1);

            if ( ((LA102_0>=53 && LA102_0<=55)) ) {
                alt102=1;
            }
            else if ( (LA102_0==103) ) {
                alt102=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 102, 0, input);

                throw nvae;
            }
            switch (alt102) {
                case 1 :
                    // InternalCompleteOCL.g:5103:2: ( () ( (lv_name_1_0= ruleUnaryOperatorName ) ) ( (lv_ownedRight_2_0= rulePrefixedLetExpCS ) ) )
                    {
                    // InternalCompleteOCL.g:5103:2: ( () ( (lv_name_1_0= ruleUnaryOperatorName ) ) ( (lv_ownedRight_2_0= rulePrefixedLetExpCS ) ) )
                    // InternalCompleteOCL.g:5103:3: () ( (lv_name_1_0= ruleUnaryOperatorName ) ) ( (lv_ownedRight_2_0= rulePrefixedLetExpCS ) )
                    {
                    // InternalCompleteOCL.g:5103:3: ()
                    // InternalCompleteOCL.g:5104:2:
                    {
                    if ( state.backtracking==0 ) {

                      	  /* */

                    }
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElement(
                                  grammarAccess.getPrefixedLetExpCSAccess().getPrefixExpCSAction_0_0(),
                                  current);

                    }

                    }

                    // InternalCompleteOCL.g:5112:2: ( (lv_name_1_0= ruleUnaryOperatorName ) )
                    // InternalCompleteOCL.g:5113:1: (lv_name_1_0= ruleUnaryOperatorName )
                    {
                    // InternalCompleteOCL.g:5113:1: (lv_name_1_0= ruleUnaryOperatorName )
                    // InternalCompleteOCL.g:5114:3: lv_name_1_0= ruleUnaryOperatorName
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getPrefixedLetExpCSAccess().getNameUnaryOperatorNameParserRuleCall_0_1_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_50);
                    lv_name_1_0=ruleUnaryOperatorName();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getPrefixedLetExpCSRule());
                      	        }
                             		set(
                             			current,
                             			"name",
                              		lv_name_1_0,
                              		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.UnaryOperatorName");
                      	        afterParserOrEnumRuleCall();

                    }

                    }


                    }

                    // InternalCompleteOCL.g:5130:2: ( (lv_ownedRight_2_0= rulePrefixedLetExpCS ) )
                    // InternalCompleteOCL.g:5131:1: (lv_ownedRight_2_0= rulePrefixedLetExpCS )
                    {
                    // InternalCompleteOCL.g:5131:1: (lv_ownedRight_2_0= rulePrefixedLetExpCS )
                    // InternalCompleteOCL.g:5132:3: lv_ownedRight_2_0= rulePrefixedLetExpCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getPrefixedLetExpCSAccess().getOwnedRightPrefixedLetExpCSParserRuleCall_0_2_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    lv_ownedRight_2_0=rulePrefixedLetExpCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getPrefixedLetExpCSRule());
                      	        }
                             		set(
                             			current,
                             			"ownedRight",
                              		lv_ownedRight_2_0,
                              		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.PrefixedLetExpCS");
                      	        afterParserOrEnumRuleCall();

                    }

                    }


                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalCompleteOCL.g:5150:2: this_LetExpCS_3= ruleLetExpCS
                    {
                    if ( state.backtracking==0 ) {

                      	  /* */

                    }
                    if ( state.backtracking==0 ) {

                              newCompositeNode(grammarAccess.getPrefixedLetExpCSAccess().getLetExpCSParserRuleCall_1());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_LetExpCS_3=ruleLetExpCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_LetExpCS_3;
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
    // $ANTLR end "rulePrefixedLetExpCS"


    // $ANTLR start "entryRulePrefixedPrimaryExpCS"
    // InternalCompleteOCL.g:5169:1: entryRulePrefixedPrimaryExpCS returns [EObject current=null] : iv_rulePrefixedPrimaryExpCS= rulePrefixedPrimaryExpCS EOF ;
    public final EObject entryRulePrefixedPrimaryExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePrefixedPrimaryExpCS = null;


        try {
            // InternalCompleteOCL.g:5170:2: (iv_rulePrefixedPrimaryExpCS= rulePrefixedPrimaryExpCS EOF )
            // InternalCompleteOCL.g:5171:2: iv_rulePrefixedPrimaryExpCS= rulePrefixedPrimaryExpCS EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getPrefixedPrimaryExpCSRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_rulePrefixedPrimaryExpCS=rulePrefixedPrimaryExpCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_rulePrefixedPrimaryExpCS;
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
    // $ANTLR end "entryRulePrefixedPrimaryExpCS"


    // $ANTLR start "rulePrefixedPrimaryExpCS"
    // InternalCompleteOCL.g:5178:1: rulePrefixedPrimaryExpCS returns [EObject current=null] : ( ( () ( (lv_name_1_0= ruleUnaryOperatorName ) ) ( (lv_ownedRight_2_0= rulePrefixedPrimaryExpCS ) ) ) | this_PrimaryExpCS_3= rulePrimaryExpCS ) ;
    public final EObject rulePrefixedPrimaryExpCS() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_name_1_0 = null;

        EObject lv_ownedRight_2_0 = null;

        EObject this_PrimaryExpCS_3 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:5181:28: ( ( ( () ( (lv_name_1_0= ruleUnaryOperatorName ) ) ( (lv_ownedRight_2_0= rulePrefixedPrimaryExpCS ) ) ) | this_PrimaryExpCS_3= rulePrimaryExpCS ) )
            // InternalCompleteOCL.g:5182:1: ( ( () ( (lv_name_1_0= ruleUnaryOperatorName ) ) ( (lv_ownedRight_2_0= rulePrefixedPrimaryExpCS ) ) ) | this_PrimaryExpCS_3= rulePrimaryExpCS )
            {
            // InternalCompleteOCL.g:5182:1: ( ( () ( (lv_name_1_0= ruleUnaryOperatorName ) ) ( (lv_ownedRight_2_0= rulePrefixedPrimaryExpCS ) ) ) | this_PrimaryExpCS_3= rulePrimaryExpCS )
            int alt103=2;
            int LA103_0 = input.LA(1);

            if ( ((LA103_0>=53 && LA103_0<=55)) ) {
                alt103=1;
            }
            else if ( ((LA103_0>=RULE_SIMPLE_ID && LA103_0<=RULE_SINGLE_QUOTED_STRING)||LA103_0==22||(LA103_0>=31 && LA103_0<=33)||(LA103_0>=43 && LA103_0<=52)||LA103_0==56||(LA103_0>=74 && LA103_0<=75)||(LA103_0>=77 && LA103_0<=81)||LA103_0==86||(LA103_0>=88 && LA103_0<=91)||LA103_0==98||LA103_0==104) ) {
                alt103=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 103, 0, input);

                throw nvae;
            }
            switch (alt103) {
                case 1 :
                    // InternalCompleteOCL.g:5182:2: ( () ( (lv_name_1_0= ruleUnaryOperatorName ) ) ( (lv_ownedRight_2_0= rulePrefixedPrimaryExpCS ) ) )
                    {
                    // InternalCompleteOCL.g:5182:2: ( () ( (lv_name_1_0= ruleUnaryOperatorName ) ) ( (lv_ownedRight_2_0= rulePrefixedPrimaryExpCS ) ) )
                    // InternalCompleteOCL.g:5182:3: () ( (lv_name_1_0= ruleUnaryOperatorName ) ) ( (lv_ownedRight_2_0= rulePrefixedPrimaryExpCS ) )
                    {
                    // InternalCompleteOCL.g:5182:3: ()
                    // InternalCompleteOCL.g:5183:2:
                    {
                    if ( state.backtracking==0 ) {

                      	  /* */

                    }
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElement(
                                  grammarAccess.getPrefixedPrimaryExpCSAccess().getPrefixExpCSAction_0_0(),
                                  current);

                    }

                    }

                    // InternalCompleteOCL.g:5191:2: ( (lv_name_1_0= ruleUnaryOperatorName ) )
                    // InternalCompleteOCL.g:5192:1: (lv_name_1_0= ruleUnaryOperatorName )
                    {
                    // InternalCompleteOCL.g:5192:1: (lv_name_1_0= ruleUnaryOperatorName )
                    // InternalCompleteOCL.g:5193:3: lv_name_1_0= ruleUnaryOperatorName
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getPrefixedPrimaryExpCSAccess().getNameUnaryOperatorNameParserRuleCall_0_1_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_61);
                    lv_name_1_0=ruleUnaryOperatorName();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getPrefixedPrimaryExpCSRule());
                      	        }
                             		set(
                             			current,
                             			"name",
                              		lv_name_1_0,
                              		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.UnaryOperatorName");
                      	        afterParserOrEnumRuleCall();

                    }

                    }


                    }

                    // InternalCompleteOCL.g:5209:2: ( (lv_ownedRight_2_0= rulePrefixedPrimaryExpCS ) )
                    // InternalCompleteOCL.g:5210:1: (lv_ownedRight_2_0= rulePrefixedPrimaryExpCS )
                    {
                    // InternalCompleteOCL.g:5210:1: (lv_ownedRight_2_0= rulePrefixedPrimaryExpCS )
                    // InternalCompleteOCL.g:5211:3: lv_ownedRight_2_0= rulePrefixedPrimaryExpCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getPrefixedPrimaryExpCSAccess().getOwnedRightPrefixedPrimaryExpCSParserRuleCall_0_2_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    lv_ownedRight_2_0=rulePrefixedPrimaryExpCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getPrefixedPrimaryExpCSRule());
                      	        }
                             		set(
                             			current,
                             			"ownedRight",
                              		lv_ownedRight_2_0,
                              		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.PrefixedPrimaryExpCS");
                      	        afterParserOrEnumRuleCall();

                    }

                    }


                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalCompleteOCL.g:5229:2: this_PrimaryExpCS_3= rulePrimaryExpCS
                    {
                    if ( state.backtracking==0 ) {

                      	  /* */

                    }
                    if ( state.backtracking==0 ) {

                              newCompositeNode(grammarAccess.getPrefixedPrimaryExpCSAccess().getPrimaryExpCSParserRuleCall_1());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_PrimaryExpCS_3=rulePrimaryExpCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_PrimaryExpCS_3;
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
    // $ANTLR end "rulePrefixedPrimaryExpCS"


    // $ANTLR start "entryRulePrimaryExpCS"
    // InternalCompleteOCL.g:5248:1: entryRulePrimaryExpCS returns [EObject current=null] : iv_rulePrimaryExpCS= rulePrimaryExpCS EOF ;
    public final EObject entryRulePrimaryExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePrimaryExpCS = null;


        try {
            // InternalCompleteOCL.g:5249:2: (iv_rulePrimaryExpCS= rulePrimaryExpCS EOF )
            // InternalCompleteOCL.g:5250:2: iv_rulePrimaryExpCS= rulePrimaryExpCS EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getPrimaryExpCSRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_rulePrimaryExpCS=rulePrimaryExpCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_rulePrimaryExpCS;
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
    // $ANTLR end "entryRulePrimaryExpCS"


    // $ANTLR start "rulePrimaryExpCS"
    // InternalCompleteOCL.g:5257:1: rulePrimaryExpCS returns [EObject current=null] : (this_NestedExpCS_0= ruleNestedExpCS | this_IfExpCS_1= ruleIfExpCS | this_SelfExpCS_2= ruleSelfExpCS | this_PrimitiveLiteralExpCS_3= rulePrimitiveLiteralExpCS | this_TupleLiteralExpCS_4= ruleTupleLiteralExpCS | this_MapLiteralExpCS_5= ruleMapLiteralExpCS | this_CollectionLiteralExpCS_6= ruleCollectionLiteralExpCS | this_LambdaLiteralExpCS_7= ruleLambdaLiteralExpCS | this_TypeLiteralExpCS_8= ruleTypeLiteralExpCS | this_NameExpCS_9= ruleNameExpCS ) ;
    public final EObject rulePrimaryExpCS() throws RecognitionException {
        EObject current = null;

        EObject this_NestedExpCS_0 = null;

        EObject this_IfExpCS_1 = null;

        EObject this_SelfExpCS_2 = null;

        EObject this_PrimitiveLiteralExpCS_3 = null;

        EObject this_TupleLiteralExpCS_4 = null;

        EObject this_MapLiteralExpCS_5 = null;

        EObject this_CollectionLiteralExpCS_6 = null;

        EObject this_LambdaLiteralExpCS_7 = null;

        EObject this_TypeLiteralExpCS_8 = null;

        EObject this_NameExpCS_9 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:5260:28: ( (this_NestedExpCS_0= ruleNestedExpCS | this_IfExpCS_1= ruleIfExpCS | this_SelfExpCS_2= ruleSelfExpCS | this_PrimitiveLiteralExpCS_3= rulePrimitiveLiteralExpCS | this_TupleLiteralExpCS_4= ruleTupleLiteralExpCS | this_MapLiteralExpCS_5= ruleMapLiteralExpCS | this_CollectionLiteralExpCS_6= ruleCollectionLiteralExpCS | this_LambdaLiteralExpCS_7= ruleLambdaLiteralExpCS | this_TypeLiteralExpCS_8= ruleTypeLiteralExpCS | this_NameExpCS_9= ruleNameExpCS ) )
            // InternalCompleteOCL.g:5261:1: (this_NestedExpCS_0= ruleNestedExpCS | this_IfExpCS_1= ruleIfExpCS | this_SelfExpCS_2= ruleSelfExpCS | this_PrimitiveLiteralExpCS_3= rulePrimitiveLiteralExpCS | this_TupleLiteralExpCS_4= ruleTupleLiteralExpCS | this_MapLiteralExpCS_5= ruleMapLiteralExpCS | this_CollectionLiteralExpCS_6= ruleCollectionLiteralExpCS | this_LambdaLiteralExpCS_7= ruleLambdaLiteralExpCS | this_TypeLiteralExpCS_8= ruleTypeLiteralExpCS | this_NameExpCS_9= ruleNameExpCS )
            {
            // InternalCompleteOCL.g:5261:1: (this_NestedExpCS_0= ruleNestedExpCS | this_IfExpCS_1= ruleIfExpCS | this_SelfExpCS_2= ruleSelfExpCS | this_PrimitiveLiteralExpCS_3= rulePrimitiveLiteralExpCS | this_TupleLiteralExpCS_4= ruleTupleLiteralExpCS | this_MapLiteralExpCS_5= ruleMapLiteralExpCS | this_CollectionLiteralExpCS_6= ruleCollectionLiteralExpCS | this_LambdaLiteralExpCS_7= ruleLambdaLiteralExpCS | this_TypeLiteralExpCS_8= ruleTypeLiteralExpCS | this_NameExpCS_9= ruleNameExpCS )
            int alt104=10;
            alt104 = dfa104.predict(input);
            switch (alt104) {
                case 1 :
                    // InternalCompleteOCL.g:5262:2: this_NestedExpCS_0= ruleNestedExpCS
                    {
                    if ( state.backtracking==0 ) {

                      	  /* */

                    }
                    if ( state.backtracking==0 ) {

                              newCompositeNode(grammarAccess.getPrimaryExpCSAccess().getNestedExpCSParserRuleCall_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_NestedExpCS_0=ruleNestedExpCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_NestedExpCS_0;
                              afterParserOrEnumRuleCall();

                    }

                    }
                    break;
                case 2 :
                    // InternalCompleteOCL.g:5275:2: this_IfExpCS_1= ruleIfExpCS
                    {
                    if ( state.backtracking==0 ) {

                      	  /* */

                    }
                    if ( state.backtracking==0 ) {

                              newCompositeNode(grammarAccess.getPrimaryExpCSAccess().getIfExpCSParserRuleCall_1());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_IfExpCS_1=ruleIfExpCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_IfExpCS_1;
                              afterParserOrEnumRuleCall();

                    }

                    }
                    break;
                case 3 :
                    // InternalCompleteOCL.g:5288:2: this_SelfExpCS_2= ruleSelfExpCS
                    {
                    if ( state.backtracking==0 ) {

                      	  /* */

                    }
                    if ( state.backtracking==0 ) {

                              newCompositeNode(grammarAccess.getPrimaryExpCSAccess().getSelfExpCSParserRuleCall_2());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_SelfExpCS_2=ruleSelfExpCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_SelfExpCS_2;
                              afterParserOrEnumRuleCall();

                    }

                    }
                    break;
                case 4 :
                    // InternalCompleteOCL.g:5301:2: this_PrimitiveLiteralExpCS_3= rulePrimitiveLiteralExpCS
                    {
                    if ( state.backtracking==0 ) {

                      	  /* */

                    }
                    if ( state.backtracking==0 ) {

                              newCompositeNode(grammarAccess.getPrimaryExpCSAccess().getPrimitiveLiteralExpCSParserRuleCall_3());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_PrimitiveLiteralExpCS_3=rulePrimitiveLiteralExpCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_PrimitiveLiteralExpCS_3;
                              afterParserOrEnumRuleCall();

                    }

                    }
                    break;
                case 5 :
                    // InternalCompleteOCL.g:5314:2: this_TupleLiteralExpCS_4= ruleTupleLiteralExpCS
                    {
                    if ( state.backtracking==0 ) {

                      	  /* */

                    }
                    if ( state.backtracking==0 ) {

                              newCompositeNode(grammarAccess.getPrimaryExpCSAccess().getTupleLiteralExpCSParserRuleCall_4());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_TupleLiteralExpCS_4=ruleTupleLiteralExpCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_TupleLiteralExpCS_4;
                              afterParserOrEnumRuleCall();

                    }

                    }
                    break;
                case 6 :
                    // InternalCompleteOCL.g:5327:2: this_MapLiteralExpCS_5= ruleMapLiteralExpCS
                    {
                    if ( state.backtracking==0 ) {

                      	  /* */

                    }
                    if ( state.backtracking==0 ) {

                              newCompositeNode(grammarAccess.getPrimaryExpCSAccess().getMapLiteralExpCSParserRuleCall_5());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_MapLiteralExpCS_5=ruleMapLiteralExpCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_MapLiteralExpCS_5;
                              afterParserOrEnumRuleCall();

                    }

                    }
                    break;
                case 7 :
                    // InternalCompleteOCL.g:5340:2: this_CollectionLiteralExpCS_6= ruleCollectionLiteralExpCS
                    {
                    if ( state.backtracking==0 ) {

                      	  /* */

                    }
                    if ( state.backtracking==0 ) {

                              newCompositeNode(grammarAccess.getPrimaryExpCSAccess().getCollectionLiteralExpCSParserRuleCall_6());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_CollectionLiteralExpCS_6=ruleCollectionLiteralExpCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_CollectionLiteralExpCS_6;
                              afterParserOrEnumRuleCall();

                    }

                    }
                    break;
                case 8 :
                    // InternalCompleteOCL.g:5353:2: this_LambdaLiteralExpCS_7= ruleLambdaLiteralExpCS
                    {
                    if ( state.backtracking==0 ) {

                      	  /* */

                    }
                    if ( state.backtracking==0 ) {

                              newCompositeNode(grammarAccess.getPrimaryExpCSAccess().getLambdaLiteralExpCSParserRuleCall_7());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_LambdaLiteralExpCS_7=ruleLambdaLiteralExpCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_LambdaLiteralExpCS_7;
                              afterParserOrEnumRuleCall();

                    }

                    }
                    break;
                case 9 :
                    // InternalCompleteOCL.g:5366:2: this_TypeLiteralExpCS_8= ruleTypeLiteralExpCS
                    {
                    if ( state.backtracking==0 ) {

                      	  /* */

                    }
                    if ( state.backtracking==0 ) {

                              newCompositeNode(grammarAccess.getPrimaryExpCSAccess().getTypeLiteralExpCSParserRuleCall_8());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_TypeLiteralExpCS_8=ruleTypeLiteralExpCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_TypeLiteralExpCS_8;
                              afterParserOrEnumRuleCall();

                    }

                    }
                    break;
                case 10 :
                    // InternalCompleteOCL.g:5379:2: this_NameExpCS_9= ruleNameExpCS
                    {
                    if ( state.backtracking==0 ) {

                      	  /* */

                    }
                    if ( state.backtracking==0 ) {

                              newCompositeNode(grammarAccess.getPrimaryExpCSAccess().getNameExpCSParserRuleCall_9());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_NameExpCS_9=ruleNameExpCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_NameExpCS_9;
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
    // $ANTLR end "rulePrimaryExpCS"


    // $ANTLR start "entryRuleNameExpCS"
    // InternalCompleteOCL.g:5398:1: entryRuleNameExpCS returns [EObject current=null] : iv_ruleNameExpCS= ruleNameExpCS EOF ;
    public final EObject entryRuleNameExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNameExpCS = null;


        try {
            // InternalCompleteOCL.g:5399:2: (iv_ruleNameExpCS= ruleNameExpCS EOF )
            // InternalCompleteOCL.g:5400:2: iv_ruleNameExpCS= ruleNameExpCS EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getNameExpCSRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleNameExpCS=ruleNameExpCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleNameExpCS;
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
    // $ANTLR end "entryRuleNameExpCS"


    // $ANTLR start "ruleNameExpCS"
    // InternalCompleteOCL.g:5407:1: ruleNameExpCS returns [EObject current=null] : ( ( (lv_ownedPathName_0_0= rulePathNameCS ) ) ( (lv_ownedSquareBracketedClauses_1_0= ruleSquareBracketedClauseCS ) )* ( (lv_ownedRoundBracketedClause_2_0= ruleRoundBracketedClauseCS ) )? ( (lv_ownedCurlyBracketedClause_3_0= ruleCurlyBracketedClauseCS ) )? ( ( (lv_isPre_4_0= '@' ) ) otherlv_5= 'pre' )? ) ;
    public final EObject ruleNameExpCS() throws RecognitionException {
        EObject current = null;

        Token lv_isPre_4_0=null;
        Token otherlv_5=null;
        EObject lv_ownedPathName_0_0 = null;

        EObject lv_ownedSquareBracketedClauses_1_0 = null;

        EObject lv_ownedRoundBracketedClause_2_0 = null;

        EObject lv_ownedCurlyBracketedClause_3_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:5410:28: ( ( ( (lv_ownedPathName_0_0= rulePathNameCS ) ) ( (lv_ownedSquareBracketedClauses_1_0= ruleSquareBracketedClauseCS ) )* ( (lv_ownedRoundBracketedClause_2_0= ruleRoundBracketedClauseCS ) )? ( (lv_ownedCurlyBracketedClause_3_0= ruleCurlyBracketedClauseCS ) )? ( ( (lv_isPre_4_0= '@' ) ) otherlv_5= 'pre' )? ) )
            // InternalCompleteOCL.g:5411:1: ( ( (lv_ownedPathName_0_0= rulePathNameCS ) ) ( (lv_ownedSquareBracketedClauses_1_0= ruleSquareBracketedClauseCS ) )* ( (lv_ownedRoundBracketedClause_2_0= ruleRoundBracketedClauseCS ) )? ( (lv_ownedCurlyBracketedClause_3_0= ruleCurlyBracketedClauseCS ) )? ( ( (lv_isPre_4_0= '@' ) ) otherlv_5= 'pre' )? )
            {
            // InternalCompleteOCL.g:5411:1: ( ( (lv_ownedPathName_0_0= rulePathNameCS ) ) ( (lv_ownedSquareBracketedClauses_1_0= ruleSquareBracketedClauseCS ) )* ( (lv_ownedRoundBracketedClause_2_0= ruleRoundBracketedClauseCS ) )? ( (lv_ownedCurlyBracketedClause_3_0= ruleCurlyBracketedClauseCS ) )? ( ( (lv_isPre_4_0= '@' ) ) otherlv_5= 'pre' )? )
            // InternalCompleteOCL.g:5411:2: ( (lv_ownedPathName_0_0= rulePathNameCS ) ) ( (lv_ownedSquareBracketedClauses_1_0= ruleSquareBracketedClauseCS ) )* ( (lv_ownedRoundBracketedClause_2_0= ruleRoundBracketedClauseCS ) )? ( (lv_ownedCurlyBracketedClause_3_0= ruleCurlyBracketedClauseCS ) )? ( ( (lv_isPre_4_0= '@' ) ) otherlv_5= 'pre' )?
            {
            // InternalCompleteOCL.g:5411:2: ( (lv_ownedPathName_0_0= rulePathNameCS ) )
            // InternalCompleteOCL.g:5412:1: (lv_ownedPathName_0_0= rulePathNameCS )
            {
            // InternalCompleteOCL.g:5412:1: (lv_ownedPathName_0_0= rulePathNameCS )
            // InternalCompleteOCL.g:5413:3: lv_ownedPathName_0_0= rulePathNameCS
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getNameExpCSAccess().getOwnedPathNamePathNameCSParserRuleCall_0_0());

            }
            pushFollow(FollowSets000.FOLLOW_62);
            lv_ownedPathName_0_0=rulePathNameCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getNameExpCSRule());
              	        }
                     		set(
                     			current,
                     			"ownedPathName",
                      		lv_ownedPathName_0_0,
                      		"org.eclipse.ocl.xtext.base.Base.PathNameCS");
              	        afterParserOrEnumRuleCall();

            }

            }


            }

            // InternalCompleteOCL.g:5429:2: ( (lv_ownedSquareBracketedClauses_1_0= ruleSquareBracketedClauseCS ) )*
            loop105:
            do {
                int alt105=2;
                int LA105_0 = input.LA(1);

                if ( (LA105_0==93) ) {
                    alt105=1;
                }


                switch (alt105) {
            	case 1 :
            	    // InternalCompleteOCL.g:5430:1: (lv_ownedSquareBracketedClauses_1_0= ruleSquareBracketedClauseCS )
            	    {
            	    // InternalCompleteOCL.g:5430:1: (lv_ownedSquareBracketedClauses_1_0= ruleSquareBracketedClauseCS )
            	    // InternalCompleteOCL.g:5431:3: lv_ownedSquareBracketedClauses_1_0= ruleSquareBracketedClauseCS
            	    {
            	    if ( state.backtracking==0 ) {

            	      	        newCompositeNode(grammarAccess.getNameExpCSAccess().getOwnedSquareBracketedClausesSquareBracketedClauseCSParserRuleCall_1_0());

            	    }
            	    pushFollow(FollowSets000.FOLLOW_62);
            	    lv_ownedSquareBracketedClauses_1_0=ruleSquareBracketedClauseCS();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getNameExpCSRule());
            	      	        }
            	             		add(
            	             			current,
            	             			"ownedSquareBracketedClauses",
            	              		lv_ownedSquareBracketedClauses_1_0,
            	              		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.SquareBracketedClauseCS");
            	      	        afterParserOrEnumRuleCall();

            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop105;
                }
            } while (true);

            // InternalCompleteOCL.g:5447:3: ( (lv_ownedRoundBracketedClause_2_0= ruleRoundBracketedClauseCS ) )?
            int alt106=2;
            int LA106_0 = input.LA(1);

            if ( (LA106_0==22) ) {
                alt106=1;
            }
            switch (alt106) {
                case 1 :
                    // InternalCompleteOCL.g:5448:1: (lv_ownedRoundBracketedClause_2_0= ruleRoundBracketedClauseCS )
                    {
                    // InternalCompleteOCL.g:5448:1: (lv_ownedRoundBracketedClause_2_0= ruleRoundBracketedClauseCS )
                    // InternalCompleteOCL.g:5449:3: lv_ownedRoundBracketedClause_2_0= ruleRoundBracketedClauseCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getNameExpCSAccess().getOwnedRoundBracketedClauseRoundBracketedClauseCSParserRuleCall_2_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_63);
                    lv_ownedRoundBracketedClause_2_0=ruleRoundBracketedClauseCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getNameExpCSRule());
                      	        }
                             		set(
                             			current,
                             			"ownedRoundBracketedClause",
                              		lv_ownedRoundBracketedClause_2_0,
                              		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.RoundBracketedClauseCS");
                      	        afterParserOrEnumRuleCall();

                    }

                    }


                    }
                    break;

            }

            // InternalCompleteOCL.g:5465:3: ( (lv_ownedCurlyBracketedClause_3_0= ruleCurlyBracketedClauseCS ) )?
            int alt107=2;
            int LA107_0 = input.LA(1);

            if ( (LA107_0==82) ) {
                alt107=1;
            }
            switch (alt107) {
                case 1 :
                    // InternalCompleteOCL.g:5466:1: (lv_ownedCurlyBracketedClause_3_0= ruleCurlyBracketedClauseCS )
                    {
                    // InternalCompleteOCL.g:5466:1: (lv_ownedCurlyBracketedClause_3_0= ruleCurlyBracketedClauseCS )
                    // InternalCompleteOCL.g:5467:3: lv_ownedCurlyBracketedClause_3_0= ruleCurlyBracketedClauseCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getNameExpCSAccess().getOwnedCurlyBracketedClauseCurlyBracketedClauseCSParserRuleCall_3_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_64);
                    lv_ownedCurlyBracketedClause_3_0=ruleCurlyBracketedClauseCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getNameExpCSRule());
                      	        }
                             		set(
                             			current,
                             			"ownedCurlyBracketedClause",
                              		lv_ownedCurlyBracketedClause_3_0,
                              		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.CurlyBracketedClauseCS");
                      	        afterParserOrEnumRuleCall();

                    }

                    }


                    }
                    break;

            }

            // InternalCompleteOCL.g:5483:3: ( ( (lv_isPre_4_0= '@' ) ) otherlv_5= 'pre' )?
            int alt108=2;
            int LA108_0 = input.LA(1);

            if ( (LA108_0==92) ) {
                alt108=1;
            }
            switch (alt108) {
                case 1 :
                    // InternalCompleteOCL.g:5483:4: ( (lv_isPre_4_0= '@' ) ) otherlv_5= 'pre'
                    {
                    // InternalCompleteOCL.g:5483:4: ( (lv_isPre_4_0= '@' ) )
                    // InternalCompleteOCL.g:5484:1: (lv_isPre_4_0= '@' )
                    {
                    // InternalCompleteOCL.g:5484:1: (lv_isPre_4_0= '@' )
                    // InternalCompleteOCL.g:5485:3: lv_isPre_4_0= '@'
                    {
                    lv_isPre_4_0=(Token)match(input,92,FollowSets000.FOLLOW_16); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_isPre_4_0, grammarAccess.getNameExpCSAccess().getIsPreCommercialAtKeyword_4_0_0());

                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getNameExpCSRule());
                      	        }
                             		setWithLastConsumed(current, "isPre", true, "@");

                    }

                    }


                    }

                    otherlv_5=(Token)match(input,26,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_5, grammarAccess.getNameExpCSAccess().getPreKeyword_4_1());

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
    // $ANTLR end "ruleNameExpCS"


    // $ANTLR start "entryRuleCurlyBracketedClauseCS"
    // InternalCompleteOCL.g:5510:1: entryRuleCurlyBracketedClauseCS returns [EObject current=null] : iv_ruleCurlyBracketedClauseCS= ruleCurlyBracketedClauseCS EOF ;
    public final EObject entryRuleCurlyBracketedClauseCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCurlyBracketedClauseCS = null;


        try {
            // InternalCompleteOCL.g:5511:2: (iv_ruleCurlyBracketedClauseCS= ruleCurlyBracketedClauseCS EOF )
            // InternalCompleteOCL.g:5512:2: iv_ruleCurlyBracketedClauseCS= ruleCurlyBracketedClauseCS EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getCurlyBracketedClauseCSRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleCurlyBracketedClauseCS=ruleCurlyBracketedClauseCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleCurlyBracketedClauseCS;
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
    // $ANTLR end "entryRuleCurlyBracketedClauseCS"


    // $ANTLR start "ruleCurlyBracketedClauseCS"
    // InternalCompleteOCL.g:5519:1: ruleCurlyBracketedClauseCS returns [EObject current=null] : ( () otherlv_1= '{' ( ( (lv_ownedParts_2_0= ruleShadowPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleShadowPartCS ) ) )* )? otherlv_5= '}' ) ;
    public final EObject ruleCurlyBracketedClauseCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        EObject lv_ownedParts_2_0 = null;

        EObject lv_ownedParts_4_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:5522:28: ( ( () otherlv_1= '{' ( ( (lv_ownedParts_2_0= ruleShadowPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleShadowPartCS ) ) )* )? otherlv_5= '}' ) )
            // InternalCompleteOCL.g:5523:1: ( () otherlv_1= '{' ( ( (lv_ownedParts_2_0= ruleShadowPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleShadowPartCS ) ) )* )? otherlv_5= '}' )
            {
            // InternalCompleteOCL.g:5523:1: ( () otherlv_1= '{' ( ( (lv_ownedParts_2_0= ruleShadowPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleShadowPartCS ) ) )* )? otherlv_5= '}' )
            // InternalCompleteOCL.g:5523:2: () otherlv_1= '{' ( ( (lv_ownedParts_2_0= ruleShadowPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleShadowPartCS ) ) )* )? otherlv_5= '}'
            {
            // InternalCompleteOCL.g:5523:2: ()
            // InternalCompleteOCL.g:5524:2:
            {
            if ( state.backtracking==0 ) {

              	  /* */

            }
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getCurlyBracketedClauseCSAccess().getCurlyBracketedClauseCSAction_0(),
                          current);

            }

            }

            otherlv_1=(Token)match(input,82,FollowSets000.FOLLOW_65); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getCurlyBracketedClauseCSAccess().getLeftCurlyBracketKeyword_1());

            }
            // InternalCompleteOCL.g:5536:1: ( ( (lv_ownedParts_2_0= ruleShadowPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleShadowPartCS ) ) )* )?
            int alt110=2;
            int LA110_0 = input.LA(1);

            if ( ((LA110_0>=RULE_SIMPLE_ID && LA110_0<=RULE_ESCAPED_ID)||LA110_0==RULE_SINGLE_QUOTED_STRING||(LA110_0>=31 && LA110_0<=33)) ) {
                alt110=1;
            }
            switch (alt110) {
                case 1 :
                    // InternalCompleteOCL.g:5536:2: ( (lv_ownedParts_2_0= ruleShadowPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleShadowPartCS ) ) )*
                    {
                    // InternalCompleteOCL.g:5536:2: ( (lv_ownedParts_2_0= ruleShadowPartCS ) )
                    // InternalCompleteOCL.g:5537:1: (lv_ownedParts_2_0= ruleShadowPartCS )
                    {
                    // InternalCompleteOCL.g:5537:1: (lv_ownedParts_2_0= ruleShadowPartCS )
                    // InternalCompleteOCL.g:5538:3: lv_ownedParts_2_0= ruleShadowPartCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getCurlyBracketedClauseCSAccess().getOwnedPartsShadowPartCSParserRuleCall_2_0_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_47);
                    lv_ownedParts_2_0=ruleShadowPartCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getCurlyBracketedClauseCSRule());
                      	        }
                             		add(
                             			current,
                             			"ownedParts",
                              		lv_ownedParts_2_0,
                              		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.ShadowPartCS");
                      	        afterParserOrEnumRuleCall();

                    }

                    }


                    }

                    // InternalCompleteOCL.g:5554:2: (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleShadowPartCS ) ) )*
                    loop109:
                    do {
                        int alt109=2;
                        int LA109_0 = input.LA(1);

                        if ( (LA109_0==29) ) {
                            alt109=1;
                        }


                        switch (alt109) {
                    	case 1 :
                    	    // InternalCompleteOCL.g:5554:4: otherlv_3= ',' ( (lv_ownedParts_4_0= ruleShadowPartCS ) )
                    	    {
                    	    otherlv_3=(Token)match(input,29,FollowSets000.FOLLOW_27); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_3, grammarAccess.getCurlyBracketedClauseCSAccess().getCommaKeyword_2_1_0());

                    	    }
                    	    // InternalCompleteOCL.g:5558:1: ( (lv_ownedParts_4_0= ruleShadowPartCS ) )
                    	    // InternalCompleteOCL.g:5559:1: (lv_ownedParts_4_0= ruleShadowPartCS )
                    	    {
                    	    // InternalCompleteOCL.g:5559:1: (lv_ownedParts_4_0= ruleShadowPartCS )
                    	    // InternalCompleteOCL.g:5560:3: lv_ownedParts_4_0= ruleShadowPartCS
                    	    {
                    	    if ( state.backtracking==0 ) {

                    	      	        newCompositeNode(grammarAccess.getCurlyBracketedClauseCSAccess().getOwnedPartsShadowPartCSParserRuleCall_2_1_1_0());

                    	    }
                    	    pushFollow(FollowSets000.FOLLOW_47);
                    	    lv_ownedParts_4_0=ruleShadowPartCS();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = createModelElementForParent(grammarAccess.getCurlyBracketedClauseCSRule());
                    	      	        }
                    	             		add(
                    	             			current,
                    	             			"ownedParts",
                    	              		lv_ownedParts_4_0,
                    	              		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.ShadowPartCS");
                    	      	        afterParserOrEnumRuleCall();

                    	    }

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop109;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_5=(Token)match(input,83,FollowSets000.FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_5, grammarAccess.getCurlyBracketedClauseCSAccess().getRightCurlyBracketKeyword_3());

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
    // $ANTLR end "ruleCurlyBracketedClauseCS"


    // $ANTLR start "entryRuleRoundBracketedClauseCS"
    // InternalCompleteOCL.g:5588:1: entryRuleRoundBracketedClauseCS returns [EObject current=null] : iv_ruleRoundBracketedClauseCS= ruleRoundBracketedClauseCS EOF ;
    public final EObject entryRuleRoundBracketedClauseCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRoundBracketedClauseCS = null;


        try {
            // InternalCompleteOCL.g:5589:2: (iv_ruleRoundBracketedClauseCS= ruleRoundBracketedClauseCS EOF )
            // InternalCompleteOCL.g:5590:2: iv_ruleRoundBracketedClauseCS= ruleRoundBracketedClauseCS EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getRoundBracketedClauseCSRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleRoundBracketedClauseCS=ruleRoundBracketedClauseCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleRoundBracketedClauseCS;
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
    // $ANTLR end "entryRuleRoundBracketedClauseCS"


    // $ANTLR start "ruleRoundBracketedClauseCS"
    // InternalCompleteOCL.g:5597:1: ruleRoundBracketedClauseCS returns [EObject current=null] : ( () otherlv_1= '(' ( ( (lv_ownedArguments_2_0= ruleNavigatingArgCS ) ) ( ( (lv_ownedArguments_3_1= ruleNavigatingCommaArgCS | lv_ownedArguments_3_2= ruleNavigatingSemiArgCS | lv_ownedArguments_3_3= ruleNavigatingBarArgCS ) ) )* )? otherlv_4= ')' ) ;
    public final EObject ruleRoundBracketedClauseCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_4=null;
        EObject lv_ownedArguments_2_0 = null;

        EObject lv_ownedArguments_3_1 = null;

        EObject lv_ownedArguments_3_2 = null;

        EObject lv_ownedArguments_3_3 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:5600:28: ( ( () otherlv_1= '(' ( ( (lv_ownedArguments_2_0= ruleNavigatingArgCS ) ) ( ( (lv_ownedArguments_3_1= ruleNavigatingCommaArgCS | lv_ownedArguments_3_2= ruleNavigatingSemiArgCS | lv_ownedArguments_3_3= ruleNavigatingBarArgCS ) ) )* )? otherlv_4= ')' ) )
            // InternalCompleteOCL.g:5601:1: ( () otherlv_1= '(' ( ( (lv_ownedArguments_2_0= ruleNavigatingArgCS ) ) ( ( (lv_ownedArguments_3_1= ruleNavigatingCommaArgCS | lv_ownedArguments_3_2= ruleNavigatingSemiArgCS | lv_ownedArguments_3_3= ruleNavigatingBarArgCS ) ) )* )? otherlv_4= ')' )
            {
            // InternalCompleteOCL.g:5601:1: ( () otherlv_1= '(' ( ( (lv_ownedArguments_2_0= ruleNavigatingArgCS ) ) ( ( (lv_ownedArguments_3_1= ruleNavigatingCommaArgCS | lv_ownedArguments_3_2= ruleNavigatingSemiArgCS | lv_ownedArguments_3_3= ruleNavigatingBarArgCS ) ) )* )? otherlv_4= ')' )
            // InternalCompleteOCL.g:5601:2: () otherlv_1= '(' ( ( (lv_ownedArguments_2_0= ruleNavigatingArgCS ) ) ( ( (lv_ownedArguments_3_1= ruleNavigatingCommaArgCS | lv_ownedArguments_3_2= ruleNavigatingSemiArgCS | lv_ownedArguments_3_3= ruleNavigatingBarArgCS ) ) )* )? otherlv_4= ')'
            {
            // InternalCompleteOCL.g:5601:2: ()
            // InternalCompleteOCL.g:5602:2:
            {
            if ( state.backtracking==0 ) {

              	  /* */

            }
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getRoundBracketedClauseCSAccess().getRoundBracketedClauseCSAction_0(),
                          current);

            }

            }

            otherlv_1=(Token)match(input,22,FollowSets000.FOLLOW_66); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getRoundBracketedClauseCSAccess().getLeftParenthesisKeyword_1());

            }
            // InternalCompleteOCL.g:5614:1: ( ( (lv_ownedArguments_2_0= ruleNavigatingArgCS ) ) ( ( (lv_ownedArguments_3_1= ruleNavigatingCommaArgCS | lv_ownedArguments_3_2= ruleNavigatingSemiArgCS | lv_ownedArguments_3_3= ruleNavigatingBarArgCS ) ) )* )?
            int alt113=2;
            int LA113_0 = input.LA(1);

            if ( ((LA113_0>=RULE_SIMPLE_ID && LA113_0<=RULE_SINGLE_QUOTED_STRING)||LA113_0==22||LA113_0==24||(LA113_0>=31 && LA113_0<=33)||(LA113_0>=42 && LA113_0<=56)||(LA113_0>=74 && LA113_0<=75)||(LA113_0>=77 && LA113_0<=81)||LA113_0==86||(LA113_0>=88 && LA113_0<=91)||LA113_0==98||(LA113_0>=103 && LA113_0<=104)) ) {
                alt113=1;
            }
            switch (alt113) {
                case 1 :
                    // InternalCompleteOCL.g:5614:2: ( (lv_ownedArguments_2_0= ruleNavigatingArgCS ) ) ( ( (lv_ownedArguments_3_1= ruleNavigatingCommaArgCS | lv_ownedArguments_3_2= ruleNavigatingSemiArgCS | lv_ownedArguments_3_3= ruleNavigatingBarArgCS ) ) )*
                    {
                    // InternalCompleteOCL.g:5614:2: ( (lv_ownedArguments_2_0= ruleNavigatingArgCS ) )
                    // InternalCompleteOCL.g:5615:1: (lv_ownedArguments_2_0= ruleNavigatingArgCS )
                    {
                    // InternalCompleteOCL.g:5615:1: (lv_ownedArguments_2_0= ruleNavigatingArgCS )
                    // InternalCompleteOCL.g:5616:3: lv_ownedArguments_2_0= ruleNavigatingArgCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getRoundBracketedClauseCSAccess().getOwnedArgumentsNavigatingArgCSParserRuleCall_2_0_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_67);
                    lv_ownedArguments_2_0=ruleNavigatingArgCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getRoundBracketedClauseCSRule());
                      	        }
                             		add(
                             			current,
                             			"ownedArguments",
                              		lv_ownedArguments_2_0,
                              		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.NavigatingArgCS");
                      	        afterParserOrEnumRuleCall();

                    }

                    }


                    }

                    // InternalCompleteOCL.g:5632:2: ( ( (lv_ownedArguments_3_1= ruleNavigatingCommaArgCS | lv_ownedArguments_3_2= ruleNavigatingSemiArgCS | lv_ownedArguments_3_3= ruleNavigatingBarArgCS ) ) )*
                    loop112:
                    do {
                        int alt112=2;
                        int LA112_0 = input.LA(1);

                        if ( (LA112_0==29||(LA112_0>=96 && LA112_0<=97)) ) {
                            alt112=1;
                        }


                        switch (alt112) {
                    	case 1 :
                    	    // InternalCompleteOCL.g:5633:1: ( (lv_ownedArguments_3_1= ruleNavigatingCommaArgCS | lv_ownedArguments_3_2= ruleNavigatingSemiArgCS | lv_ownedArguments_3_3= ruleNavigatingBarArgCS ) )
                    	    {
                    	    // InternalCompleteOCL.g:5633:1: ( (lv_ownedArguments_3_1= ruleNavigatingCommaArgCS | lv_ownedArguments_3_2= ruleNavigatingSemiArgCS | lv_ownedArguments_3_3= ruleNavigatingBarArgCS ) )
                    	    // InternalCompleteOCL.g:5634:1: (lv_ownedArguments_3_1= ruleNavigatingCommaArgCS | lv_ownedArguments_3_2= ruleNavigatingSemiArgCS | lv_ownedArguments_3_3= ruleNavigatingBarArgCS )
                    	    {
                    	    // InternalCompleteOCL.g:5634:1: (lv_ownedArguments_3_1= ruleNavigatingCommaArgCS | lv_ownedArguments_3_2= ruleNavigatingSemiArgCS | lv_ownedArguments_3_3= ruleNavigatingBarArgCS )
                    	    int alt111=3;
                    	    switch ( input.LA(1) ) {
                    	    case 29:
                    	        {
                    	        alt111=1;
                    	        }
                    	        break;
                    	    case 97:
                    	        {
                    	        alt111=2;
                    	        }
                    	        break;
                    	    case 96:
                    	        {
                    	        alt111=3;
                    	        }
                    	        break;
                    	    default:
                    	        if (state.backtracking>0) {state.failed=true; return current;}
                    	        NoViableAltException nvae =
                    	            new NoViableAltException("", 111, 0, input);

                    	        throw nvae;
                    	    }

                    	    switch (alt111) {
                    	        case 1 :
                    	            // InternalCompleteOCL.g:5635:3: lv_ownedArguments_3_1= ruleNavigatingCommaArgCS
                    	            {
                    	            if ( state.backtracking==0 ) {

                    	              	        newCompositeNode(grammarAccess.getRoundBracketedClauseCSAccess().getOwnedArgumentsNavigatingCommaArgCSParserRuleCall_2_1_0_0());

                    	            }
                    	            pushFollow(FollowSets000.FOLLOW_67);
                    	            lv_ownedArguments_3_1=ruleNavigatingCommaArgCS();

                    	            state._fsp--;
                    	            if (state.failed) return current;
                    	            if ( state.backtracking==0 ) {

                    	              	        if (current==null) {
                    	              	            current = createModelElementForParent(grammarAccess.getRoundBracketedClauseCSRule());
                    	              	        }
                    	                     		add(
                    	                     			current,
                    	                     			"ownedArguments",
                    	                      		lv_ownedArguments_3_1,
                    	                      		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.NavigatingCommaArgCS");
                    	              	        afterParserOrEnumRuleCall();

                    	            }

                    	            }
                    	            break;
                    	        case 2 :
                    	            // InternalCompleteOCL.g:5650:8: lv_ownedArguments_3_2= ruleNavigatingSemiArgCS
                    	            {
                    	            if ( state.backtracking==0 ) {

                    	              	        newCompositeNode(grammarAccess.getRoundBracketedClauseCSAccess().getOwnedArgumentsNavigatingSemiArgCSParserRuleCall_2_1_0_1());

                    	            }
                    	            pushFollow(FollowSets000.FOLLOW_67);
                    	            lv_ownedArguments_3_2=ruleNavigatingSemiArgCS();

                    	            state._fsp--;
                    	            if (state.failed) return current;
                    	            if ( state.backtracking==0 ) {

                    	              	        if (current==null) {
                    	              	            current = createModelElementForParent(grammarAccess.getRoundBracketedClauseCSRule());
                    	              	        }
                    	                     		add(
                    	                     			current,
                    	                     			"ownedArguments",
                    	                      		lv_ownedArguments_3_2,
                    	                      		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.NavigatingSemiArgCS");
                    	              	        afterParserOrEnumRuleCall();

                    	            }

                    	            }
                    	            break;
                    	        case 3 :
                    	            // InternalCompleteOCL.g:5665:8: lv_ownedArguments_3_3= ruleNavigatingBarArgCS
                    	            {
                    	            if ( state.backtracking==0 ) {

                    	              	        newCompositeNode(grammarAccess.getRoundBracketedClauseCSAccess().getOwnedArgumentsNavigatingBarArgCSParserRuleCall_2_1_0_2());

                    	            }
                    	            pushFollow(FollowSets000.FOLLOW_67);
                    	            lv_ownedArguments_3_3=ruleNavigatingBarArgCS();

                    	            state._fsp--;
                    	            if (state.failed) return current;
                    	            if ( state.backtracking==0 ) {

                    	              	        if (current==null) {
                    	              	            current = createModelElementForParent(grammarAccess.getRoundBracketedClauseCSRule());
                    	              	        }
                    	                     		add(
                    	                     			current,
                    	                     			"ownedArguments",
                    	                      		lv_ownedArguments_3_3,
                    	                      		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.NavigatingBarArgCS");
                    	              	        afterParserOrEnumRuleCall();

                    	            }

                    	            }
                    	            break;

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop112;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_4=(Token)match(input,23,FollowSets000.FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_4, grammarAccess.getRoundBracketedClauseCSAccess().getRightParenthesisKeyword_3());

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
    // $ANTLR end "ruleRoundBracketedClauseCS"


    // $ANTLR start "entryRuleSquareBracketedClauseCS"
    // InternalCompleteOCL.g:5695:1: entryRuleSquareBracketedClauseCS returns [EObject current=null] : iv_ruleSquareBracketedClauseCS= ruleSquareBracketedClauseCS EOF ;
    public final EObject entryRuleSquareBracketedClauseCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSquareBracketedClauseCS = null;


        try {
            // InternalCompleteOCL.g:5696:2: (iv_ruleSquareBracketedClauseCS= ruleSquareBracketedClauseCS EOF )
            // InternalCompleteOCL.g:5697:2: iv_ruleSquareBracketedClauseCS= ruleSquareBracketedClauseCS EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getSquareBracketedClauseCSRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleSquareBracketedClauseCS=ruleSquareBracketedClauseCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleSquareBracketedClauseCS;
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
    // $ANTLR end "entryRuleSquareBracketedClauseCS"


    // $ANTLR start "ruleSquareBracketedClauseCS"
    // InternalCompleteOCL.g:5704:1: ruleSquareBracketedClauseCS returns [EObject current=null] : (otherlv_0= '[' ( (lv_ownedTerms_1_0= ruleExpCS ) ) (otherlv_2= ',' ( (lv_ownedTerms_3_0= ruleExpCS ) ) )* otherlv_4= ']' ) ;
    public final EObject ruleSquareBracketedClauseCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_ownedTerms_1_0 = null;

        EObject lv_ownedTerms_3_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:5707:28: ( (otherlv_0= '[' ( (lv_ownedTerms_1_0= ruleExpCS ) ) (otherlv_2= ',' ( (lv_ownedTerms_3_0= ruleExpCS ) ) )* otherlv_4= ']' ) )
            // InternalCompleteOCL.g:5708:1: (otherlv_0= '[' ( (lv_ownedTerms_1_0= ruleExpCS ) ) (otherlv_2= ',' ( (lv_ownedTerms_3_0= ruleExpCS ) ) )* otherlv_4= ']' )
            {
            // InternalCompleteOCL.g:5708:1: (otherlv_0= '[' ( (lv_ownedTerms_1_0= ruleExpCS ) ) (otherlv_2= ',' ( (lv_ownedTerms_3_0= ruleExpCS ) ) )* otherlv_4= ']' )
            // InternalCompleteOCL.g:5708:3: otherlv_0= '[' ( (lv_ownedTerms_1_0= ruleExpCS ) ) (otherlv_2= ',' ( (lv_ownedTerms_3_0= ruleExpCS ) ) )* otherlv_4= ']'
            {
            otherlv_0=(Token)match(input,93,FollowSets000.FOLLOW_50); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getSquareBracketedClauseCSAccess().getLeftSquareBracketKeyword_0());

            }
            // InternalCompleteOCL.g:5712:1: ( (lv_ownedTerms_1_0= ruleExpCS ) )
            // InternalCompleteOCL.g:5713:1: (lv_ownedTerms_1_0= ruleExpCS )
            {
            // InternalCompleteOCL.g:5713:1: (lv_ownedTerms_1_0= ruleExpCS )
            // InternalCompleteOCL.g:5714:3: lv_ownedTerms_1_0= ruleExpCS
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getSquareBracketedClauseCSAccess().getOwnedTermsExpCSParserRuleCall_1_0());

            }
            pushFollow(FollowSets000.FOLLOW_68);
            lv_ownedTerms_1_0=ruleExpCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getSquareBracketedClauseCSRule());
              	        }
                     		add(
                     			current,
                     			"ownedTerms",
                      		lv_ownedTerms_1_0,
                      		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.ExpCS");
              	        afterParserOrEnumRuleCall();

            }

            }


            }

            // InternalCompleteOCL.g:5730:2: (otherlv_2= ',' ( (lv_ownedTerms_3_0= ruleExpCS ) ) )*
            loop114:
            do {
                int alt114=2;
                int LA114_0 = input.LA(1);

                if ( (LA114_0==29) ) {
                    alt114=1;
                }


                switch (alt114) {
            	case 1 :
            	    // InternalCompleteOCL.g:5730:4: otherlv_2= ',' ( (lv_ownedTerms_3_0= ruleExpCS ) )
            	    {
            	    otherlv_2=(Token)match(input,29,FollowSets000.FOLLOW_50); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_2, grammarAccess.getSquareBracketedClauseCSAccess().getCommaKeyword_2_0());

            	    }
            	    // InternalCompleteOCL.g:5734:1: ( (lv_ownedTerms_3_0= ruleExpCS ) )
            	    // InternalCompleteOCL.g:5735:1: (lv_ownedTerms_3_0= ruleExpCS )
            	    {
            	    // InternalCompleteOCL.g:5735:1: (lv_ownedTerms_3_0= ruleExpCS )
            	    // InternalCompleteOCL.g:5736:3: lv_ownedTerms_3_0= ruleExpCS
            	    {
            	    if ( state.backtracking==0 ) {

            	      	        newCompositeNode(grammarAccess.getSquareBracketedClauseCSAccess().getOwnedTermsExpCSParserRuleCall_2_1_0());

            	    }
            	    pushFollow(FollowSets000.FOLLOW_68);
            	    lv_ownedTerms_3_0=ruleExpCS();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getSquareBracketedClauseCSRule());
            	      	        }
            	             		add(
            	             			current,
            	             			"ownedTerms",
            	              		lv_ownedTerms_3_0,
            	              		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.ExpCS");
            	      	        afterParserOrEnumRuleCall();

            	    }

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop114;
                }
            } while (true);

            otherlv_4=(Token)match(input,94,FollowSets000.FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_4, grammarAccess.getSquareBracketedClauseCSAccess().getRightSquareBracketKeyword_3());

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
    // $ANTLR end "ruleSquareBracketedClauseCS"


    // $ANTLR start "entryRuleNavigatingArgCS"
    // InternalCompleteOCL.g:5764:1: entryRuleNavigatingArgCS returns [EObject current=null] : iv_ruleNavigatingArgCS= ruleNavigatingArgCS EOF ;
    public final EObject entryRuleNavigatingArgCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNavigatingArgCS = null;


        try {
            // InternalCompleteOCL.g:5765:2: (iv_ruleNavigatingArgCS= ruleNavigatingArgCS EOF )
            // InternalCompleteOCL.g:5766:2: iv_ruleNavigatingArgCS= ruleNavigatingArgCS EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getNavigatingArgCSRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleNavigatingArgCS=ruleNavigatingArgCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleNavigatingArgCS;
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
    // $ANTLR end "entryRuleNavigatingArgCS"


    // $ANTLR start "ruleNavigatingArgCS"
    // InternalCompleteOCL.g:5773:1: ruleNavigatingArgCS returns [EObject current=null] : ( ( ( (lv_ownedNameExpression_0_0= ruleNavigatingArgExpCS ) ) ( (otherlv_1= '<-' ( (lv_ownedCoIterator_2_0= ruleCoIteratorVariableCS ) ) (otherlv_3= '=' ( (lv_ownedInitExpression_4_0= ruleExpCS ) ) )? ) | (otherlv_5= ':' ( (lv_ownedType_6_0= ruleTypeExpCS ) ) (otherlv_7= '<-' ( (lv_ownedCoIterator_8_0= ruleCoIteratorVariableCS ) ) )? (otherlv_9= '=' ( (lv_ownedInitExpression_10_0= ruleExpCS ) ) )? ) | ( (otherlv_11= ':' ( (lv_ownedType_12_0= ruleTypeExpCS ) ) )? (otherlv_13= '<-' ( (lv_ownedCoIterator_14_0= ruleCoIteratorVariableCS ) ) )? otherlv_15= 'in' ( (lv_ownedInitExpression_16_0= ruleExpCS ) ) ) )? ) | (otherlv_17= ':' ( (lv_ownedType_18_0= ruleTypeExpCS ) ) ) ) ;
    public final EObject ruleNavigatingArgCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_9=null;
        Token otherlv_11=null;
        Token otherlv_13=null;
        Token otherlv_15=null;
        Token otherlv_17=null;
        EObject lv_ownedNameExpression_0_0 = null;

        EObject lv_ownedCoIterator_2_0 = null;

        EObject lv_ownedInitExpression_4_0 = null;

        EObject lv_ownedType_6_0 = null;

        EObject lv_ownedCoIterator_8_0 = null;

        EObject lv_ownedInitExpression_10_0 = null;

        EObject lv_ownedType_12_0 = null;

        EObject lv_ownedCoIterator_14_0 = null;

        EObject lv_ownedInitExpression_16_0 = null;

        EObject lv_ownedType_18_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:5776:28: ( ( ( ( (lv_ownedNameExpression_0_0= ruleNavigatingArgExpCS ) ) ( (otherlv_1= '<-' ( (lv_ownedCoIterator_2_0= ruleCoIteratorVariableCS ) ) (otherlv_3= '=' ( (lv_ownedInitExpression_4_0= ruleExpCS ) ) )? ) | (otherlv_5= ':' ( (lv_ownedType_6_0= ruleTypeExpCS ) ) (otherlv_7= '<-' ( (lv_ownedCoIterator_8_0= ruleCoIteratorVariableCS ) ) )? (otherlv_9= '=' ( (lv_ownedInitExpression_10_0= ruleExpCS ) ) )? ) | ( (otherlv_11= ':' ( (lv_ownedType_12_0= ruleTypeExpCS ) ) )? (otherlv_13= '<-' ( (lv_ownedCoIterator_14_0= ruleCoIteratorVariableCS ) ) )? otherlv_15= 'in' ( (lv_ownedInitExpression_16_0= ruleExpCS ) ) ) )? ) | (otherlv_17= ':' ( (lv_ownedType_18_0= ruleTypeExpCS ) ) ) ) )
            // InternalCompleteOCL.g:5777:1: ( ( ( (lv_ownedNameExpression_0_0= ruleNavigatingArgExpCS ) ) ( (otherlv_1= '<-' ( (lv_ownedCoIterator_2_0= ruleCoIteratorVariableCS ) ) (otherlv_3= '=' ( (lv_ownedInitExpression_4_0= ruleExpCS ) ) )? ) | (otherlv_5= ':' ( (lv_ownedType_6_0= ruleTypeExpCS ) ) (otherlv_7= '<-' ( (lv_ownedCoIterator_8_0= ruleCoIteratorVariableCS ) ) )? (otherlv_9= '=' ( (lv_ownedInitExpression_10_0= ruleExpCS ) ) )? ) | ( (otherlv_11= ':' ( (lv_ownedType_12_0= ruleTypeExpCS ) ) )? (otherlv_13= '<-' ( (lv_ownedCoIterator_14_0= ruleCoIteratorVariableCS ) ) )? otherlv_15= 'in' ( (lv_ownedInitExpression_16_0= ruleExpCS ) ) ) )? ) | (otherlv_17= ':' ( (lv_ownedType_18_0= ruleTypeExpCS ) ) ) )
            {
            // InternalCompleteOCL.g:5777:1: ( ( ( (lv_ownedNameExpression_0_0= ruleNavigatingArgExpCS ) ) ( (otherlv_1= '<-' ( (lv_ownedCoIterator_2_0= ruleCoIteratorVariableCS ) ) (otherlv_3= '=' ( (lv_ownedInitExpression_4_0= ruleExpCS ) ) )? ) | (otherlv_5= ':' ( (lv_ownedType_6_0= ruleTypeExpCS ) ) (otherlv_7= '<-' ( (lv_ownedCoIterator_8_0= ruleCoIteratorVariableCS ) ) )? (otherlv_9= '=' ( (lv_ownedInitExpression_10_0= ruleExpCS ) ) )? ) | ( (otherlv_11= ':' ( (lv_ownedType_12_0= ruleTypeExpCS ) ) )? (otherlv_13= '<-' ( (lv_ownedCoIterator_14_0= ruleCoIteratorVariableCS ) ) )? otherlv_15= 'in' ( (lv_ownedInitExpression_16_0= ruleExpCS ) ) ) )? ) | (otherlv_17= ':' ( (lv_ownedType_18_0= ruleTypeExpCS ) ) ) )
            int alt121=2;
            int LA121_0 = input.LA(1);

            if ( ((LA121_0>=RULE_SIMPLE_ID && LA121_0<=RULE_SINGLE_QUOTED_STRING)||LA121_0==22||(LA121_0>=31 && LA121_0<=33)||(LA121_0>=42 && LA121_0<=56)||(LA121_0>=74 && LA121_0<=75)||(LA121_0>=77 && LA121_0<=81)||LA121_0==86||(LA121_0>=88 && LA121_0<=91)||LA121_0==98||(LA121_0>=103 && LA121_0<=104)) ) {
                alt121=1;
            }
            else if ( (LA121_0==24) ) {
                alt121=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 121, 0, input);

                throw nvae;
            }
            switch (alt121) {
                case 1 :
                    // InternalCompleteOCL.g:5777:2: ( ( (lv_ownedNameExpression_0_0= ruleNavigatingArgExpCS ) ) ( (otherlv_1= '<-' ( (lv_ownedCoIterator_2_0= ruleCoIteratorVariableCS ) ) (otherlv_3= '=' ( (lv_ownedInitExpression_4_0= ruleExpCS ) ) )? ) | (otherlv_5= ':' ( (lv_ownedType_6_0= ruleTypeExpCS ) ) (otherlv_7= '<-' ( (lv_ownedCoIterator_8_0= ruleCoIteratorVariableCS ) ) )? (otherlv_9= '=' ( (lv_ownedInitExpression_10_0= ruleExpCS ) ) )? ) | ( (otherlv_11= ':' ( (lv_ownedType_12_0= ruleTypeExpCS ) ) )? (otherlv_13= '<-' ( (lv_ownedCoIterator_14_0= ruleCoIteratorVariableCS ) ) )? otherlv_15= 'in' ( (lv_ownedInitExpression_16_0= ruleExpCS ) ) ) )? )
                    {
                    // InternalCompleteOCL.g:5777:2: ( ( (lv_ownedNameExpression_0_0= ruleNavigatingArgExpCS ) ) ( (otherlv_1= '<-' ( (lv_ownedCoIterator_2_0= ruleCoIteratorVariableCS ) ) (otherlv_3= '=' ( (lv_ownedInitExpression_4_0= ruleExpCS ) ) )? ) | (otherlv_5= ':' ( (lv_ownedType_6_0= ruleTypeExpCS ) ) (otherlv_7= '<-' ( (lv_ownedCoIterator_8_0= ruleCoIteratorVariableCS ) ) )? (otherlv_9= '=' ( (lv_ownedInitExpression_10_0= ruleExpCS ) ) )? ) | ( (otherlv_11= ':' ( (lv_ownedType_12_0= ruleTypeExpCS ) ) )? (otherlv_13= '<-' ( (lv_ownedCoIterator_14_0= ruleCoIteratorVariableCS ) ) )? otherlv_15= 'in' ( (lv_ownedInitExpression_16_0= ruleExpCS ) ) ) )? )
                    // InternalCompleteOCL.g:5777:3: ( (lv_ownedNameExpression_0_0= ruleNavigatingArgExpCS ) ) ( (otherlv_1= '<-' ( (lv_ownedCoIterator_2_0= ruleCoIteratorVariableCS ) ) (otherlv_3= '=' ( (lv_ownedInitExpression_4_0= ruleExpCS ) ) )? ) | (otherlv_5= ':' ( (lv_ownedType_6_0= ruleTypeExpCS ) ) (otherlv_7= '<-' ( (lv_ownedCoIterator_8_0= ruleCoIteratorVariableCS ) ) )? (otherlv_9= '=' ( (lv_ownedInitExpression_10_0= ruleExpCS ) ) )? ) | ( (otherlv_11= ':' ( (lv_ownedType_12_0= ruleTypeExpCS ) ) )? (otherlv_13= '<-' ( (lv_ownedCoIterator_14_0= ruleCoIteratorVariableCS ) ) )? otherlv_15= 'in' ( (lv_ownedInitExpression_16_0= ruleExpCS ) ) ) )?
                    {
                    // InternalCompleteOCL.g:5777:3: ( (lv_ownedNameExpression_0_0= ruleNavigatingArgExpCS ) )
                    // InternalCompleteOCL.g:5778:1: (lv_ownedNameExpression_0_0= ruleNavigatingArgExpCS )
                    {
                    // InternalCompleteOCL.g:5778:1: (lv_ownedNameExpression_0_0= ruleNavigatingArgExpCS )
                    // InternalCompleteOCL.g:5779:3: lv_ownedNameExpression_0_0= ruleNavigatingArgExpCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getNavigatingArgCSAccess().getOwnedNameExpressionNavigatingArgExpCSParserRuleCall_0_0_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_69);
                    lv_ownedNameExpression_0_0=ruleNavigatingArgExpCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getNavigatingArgCSRule());
                      	        }
                             		set(
                             			current,
                             			"ownedNameExpression",
                              		lv_ownedNameExpression_0_0,
                              		"org.eclipse.ocl.xtext.completeocl.CompleteOCL.NavigatingArgExpCS");
                      	        afterParserOrEnumRuleCall();

                    }

                    }


                    }

                    // InternalCompleteOCL.g:5795:2: ( (otherlv_1= '<-' ( (lv_ownedCoIterator_2_0= ruleCoIteratorVariableCS ) ) (otherlv_3= '=' ( (lv_ownedInitExpression_4_0= ruleExpCS ) ) )? ) | (otherlv_5= ':' ( (lv_ownedType_6_0= ruleTypeExpCS ) ) (otherlv_7= '<-' ( (lv_ownedCoIterator_8_0= ruleCoIteratorVariableCS ) ) )? (otherlv_9= '=' ( (lv_ownedInitExpression_10_0= ruleExpCS ) ) )? ) | ( (otherlv_11= ':' ( (lv_ownedType_12_0= ruleTypeExpCS ) ) )? (otherlv_13= '<-' ( (lv_ownedCoIterator_14_0= ruleCoIteratorVariableCS ) ) )? otherlv_15= 'in' ( (lv_ownedInitExpression_16_0= ruleExpCS ) ) ) )?
                    int alt120=4;
                    alt120 = dfa120.predict(input);
                    switch (alt120) {
                        case 1 :
                            // InternalCompleteOCL.g:5795:3: (otherlv_1= '<-' ( (lv_ownedCoIterator_2_0= ruleCoIteratorVariableCS ) ) (otherlv_3= '=' ( (lv_ownedInitExpression_4_0= ruleExpCS ) ) )? )
                            {
                            // InternalCompleteOCL.g:5795:3: (otherlv_1= '<-' ( (lv_ownedCoIterator_2_0= ruleCoIteratorVariableCS ) ) (otherlv_3= '=' ( (lv_ownedInitExpression_4_0= ruleExpCS ) ) )? )
                            // InternalCompleteOCL.g:5795:5: otherlv_1= '<-' ( (lv_ownedCoIterator_2_0= ruleCoIteratorVariableCS ) ) (otherlv_3= '=' ( (lv_ownedInitExpression_4_0= ruleExpCS ) ) )?
                            {
                            otherlv_1=(Token)match(input,87,FollowSets000.FOLLOW_19); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                  	newLeafNode(otherlv_1, grammarAccess.getNavigatingArgCSAccess().getLessThanSignHyphenMinusKeyword_0_1_0_0());

                            }
                            // InternalCompleteOCL.g:5799:1: ( (lv_ownedCoIterator_2_0= ruleCoIteratorVariableCS ) )
                            // InternalCompleteOCL.g:5800:1: (lv_ownedCoIterator_2_0= ruleCoIteratorVariableCS )
                            {
                            // InternalCompleteOCL.g:5800:1: (lv_ownedCoIterator_2_0= ruleCoIteratorVariableCS )
                            // InternalCompleteOCL.g:5801:3: lv_ownedCoIterator_2_0= ruleCoIteratorVariableCS
                            {
                            if ( state.backtracking==0 ) {

                              	        newCompositeNode(grammarAccess.getNavigatingArgCSAccess().getOwnedCoIteratorCoIteratorVariableCSParserRuleCall_0_1_0_1_0());

                            }
                            pushFollow(FollowSets000.FOLLOW_70);
                            lv_ownedCoIterator_2_0=ruleCoIteratorVariableCS();

                            state._fsp--;
                            if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElementForParent(grammarAccess.getNavigatingArgCSRule());
                              	        }
                                     		set(
                                     			current,
                                     			"ownedCoIterator",
                                      		lv_ownedCoIterator_2_0,
                                      		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.CoIteratorVariableCS");
                              	        afterParserOrEnumRuleCall();

                            }

                            }


                            }

                            // InternalCompleteOCL.g:5817:2: (otherlv_3= '=' ( (lv_ownedInitExpression_4_0= ruleExpCS ) ) )?
                            int alt115=2;
                            int LA115_0 = input.LA(1);

                            if ( (LA115_0==30) ) {
                                alt115=1;
                            }
                            switch (alt115) {
                                case 1 :
                                    // InternalCompleteOCL.g:5817:4: otherlv_3= '=' ( (lv_ownedInitExpression_4_0= ruleExpCS ) )
                                    {
                                    otherlv_3=(Token)match(input,30,FollowSets000.FOLLOW_50); if (state.failed) return current;
                                    if ( state.backtracking==0 ) {

                                          	newLeafNode(otherlv_3, grammarAccess.getNavigatingArgCSAccess().getEqualsSignKeyword_0_1_0_2_0());

                                    }
                                    // InternalCompleteOCL.g:5821:1: ( (lv_ownedInitExpression_4_0= ruleExpCS ) )
                                    // InternalCompleteOCL.g:5822:1: (lv_ownedInitExpression_4_0= ruleExpCS )
                                    {
                                    // InternalCompleteOCL.g:5822:1: (lv_ownedInitExpression_4_0= ruleExpCS )
                                    // InternalCompleteOCL.g:5823:3: lv_ownedInitExpression_4_0= ruleExpCS
                                    {
                                    if ( state.backtracking==0 ) {

                                      	        newCompositeNode(grammarAccess.getNavigatingArgCSAccess().getOwnedInitExpressionExpCSParserRuleCall_0_1_0_2_1_0());

                                    }
                                    pushFollow(FollowSets000.FOLLOW_2);
                                    lv_ownedInitExpression_4_0=ruleExpCS();

                                    state._fsp--;
                                    if (state.failed) return current;
                                    if ( state.backtracking==0 ) {

                                      	        if (current==null) {
                                      	            current = createModelElementForParent(grammarAccess.getNavigatingArgCSRule());
                                      	        }
                                             		set(
                                             			current,
                                             			"ownedInitExpression",
                                              		lv_ownedInitExpression_4_0,
                                              		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.ExpCS");
                                      	        afterParserOrEnumRuleCall();

                                    }

                                    }


                                    }


                                    }
                                    break;

                            }


                            }


                            }
                            break;
                        case 2 :
                            // InternalCompleteOCL.g:5840:6: (otherlv_5= ':' ( (lv_ownedType_6_0= ruleTypeExpCS ) ) (otherlv_7= '<-' ( (lv_ownedCoIterator_8_0= ruleCoIteratorVariableCS ) ) )? (otherlv_9= '=' ( (lv_ownedInitExpression_10_0= ruleExpCS ) ) )? )
                            {
                            // InternalCompleteOCL.g:5840:6: (otherlv_5= ':' ( (lv_ownedType_6_0= ruleTypeExpCS ) ) (otherlv_7= '<-' ( (lv_ownedCoIterator_8_0= ruleCoIteratorVariableCS ) ) )? (otherlv_9= '=' ( (lv_ownedInitExpression_10_0= ruleExpCS ) ) )? )
                            // InternalCompleteOCL.g:5840:8: otherlv_5= ':' ( (lv_ownedType_6_0= ruleTypeExpCS ) ) (otherlv_7= '<-' ( (lv_ownedCoIterator_8_0= ruleCoIteratorVariableCS ) ) )? (otherlv_9= '=' ( (lv_ownedInitExpression_10_0= ruleExpCS ) ) )?
                            {
                            otherlv_5=(Token)match(input,24,FollowSets000.FOLLOW_25); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                  	newLeafNode(otherlv_5, grammarAccess.getNavigatingArgCSAccess().getColonKeyword_0_1_1_0());

                            }
                            // InternalCompleteOCL.g:5844:1: ( (lv_ownedType_6_0= ruleTypeExpCS ) )
                            // InternalCompleteOCL.g:5845:1: (lv_ownedType_6_0= ruleTypeExpCS )
                            {
                            // InternalCompleteOCL.g:5845:1: (lv_ownedType_6_0= ruleTypeExpCS )
                            // InternalCompleteOCL.g:5846:3: lv_ownedType_6_0= ruleTypeExpCS
                            {
                            if ( state.backtracking==0 ) {

                              	        newCompositeNode(grammarAccess.getNavigatingArgCSAccess().getOwnedTypeTypeExpCSParserRuleCall_0_1_1_1_0());

                            }
                            pushFollow(FollowSets000.FOLLOW_71);
                            lv_ownedType_6_0=ruleTypeExpCS();

                            state._fsp--;
                            if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElementForParent(grammarAccess.getNavigatingArgCSRule());
                              	        }
                                     		set(
                                     			current,
                                     			"ownedType",
                                      		lv_ownedType_6_0,
                                      		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.TypeExpCS");
                              	        afterParserOrEnumRuleCall();

                            }

                            }


                            }

                            // InternalCompleteOCL.g:5862:2: (otherlv_7= '<-' ( (lv_ownedCoIterator_8_0= ruleCoIteratorVariableCS ) ) )?
                            int alt116=2;
                            int LA116_0 = input.LA(1);

                            if ( (LA116_0==87) ) {
                                alt116=1;
                            }
                            switch (alt116) {
                                case 1 :
                                    // InternalCompleteOCL.g:5862:4: otherlv_7= '<-' ( (lv_ownedCoIterator_8_0= ruleCoIteratorVariableCS ) )
                                    {
                                    otherlv_7=(Token)match(input,87,FollowSets000.FOLLOW_19); if (state.failed) return current;
                                    if ( state.backtracking==0 ) {

                                          	newLeafNode(otherlv_7, grammarAccess.getNavigatingArgCSAccess().getLessThanSignHyphenMinusKeyword_0_1_1_2_0());

                                    }
                                    // InternalCompleteOCL.g:5866:1: ( (lv_ownedCoIterator_8_0= ruleCoIteratorVariableCS ) )
                                    // InternalCompleteOCL.g:5867:1: (lv_ownedCoIterator_8_0= ruleCoIteratorVariableCS )
                                    {
                                    // InternalCompleteOCL.g:5867:1: (lv_ownedCoIterator_8_0= ruleCoIteratorVariableCS )
                                    // InternalCompleteOCL.g:5868:3: lv_ownedCoIterator_8_0= ruleCoIteratorVariableCS
                                    {
                                    if ( state.backtracking==0 ) {

                                      	        newCompositeNode(grammarAccess.getNavigatingArgCSAccess().getOwnedCoIteratorCoIteratorVariableCSParserRuleCall_0_1_1_2_1_0());

                                    }
                                    pushFollow(FollowSets000.FOLLOW_70);
                                    lv_ownedCoIterator_8_0=ruleCoIteratorVariableCS();

                                    state._fsp--;
                                    if (state.failed) return current;
                                    if ( state.backtracking==0 ) {

                                      	        if (current==null) {
                                      	            current = createModelElementForParent(grammarAccess.getNavigatingArgCSRule());
                                      	        }
                                             		set(
                                             			current,
                                             			"ownedCoIterator",
                                              		lv_ownedCoIterator_8_0,
                                              		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.CoIteratorVariableCS");
                                      	        afterParserOrEnumRuleCall();

                                    }

                                    }


                                    }


                                    }
                                    break;

                            }

                            // InternalCompleteOCL.g:5884:4: (otherlv_9= '=' ( (lv_ownedInitExpression_10_0= ruleExpCS ) ) )?
                            int alt117=2;
                            int LA117_0 = input.LA(1);

                            if ( (LA117_0==30) ) {
                                alt117=1;
                            }
                            switch (alt117) {
                                case 1 :
                                    // InternalCompleteOCL.g:5884:6: otherlv_9= '=' ( (lv_ownedInitExpression_10_0= ruleExpCS ) )
                                    {
                                    otherlv_9=(Token)match(input,30,FollowSets000.FOLLOW_50); if (state.failed) return current;
                                    if ( state.backtracking==0 ) {

                                          	newLeafNode(otherlv_9, grammarAccess.getNavigatingArgCSAccess().getEqualsSignKeyword_0_1_1_3_0());

                                    }
                                    // InternalCompleteOCL.g:5888:1: ( (lv_ownedInitExpression_10_0= ruleExpCS ) )
                                    // InternalCompleteOCL.g:5889:1: (lv_ownedInitExpression_10_0= ruleExpCS )
                                    {
                                    // InternalCompleteOCL.g:5889:1: (lv_ownedInitExpression_10_0= ruleExpCS )
                                    // InternalCompleteOCL.g:5890:3: lv_ownedInitExpression_10_0= ruleExpCS
                                    {
                                    if ( state.backtracking==0 ) {

                                      	        newCompositeNode(grammarAccess.getNavigatingArgCSAccess().getOwnedInitExpressionExpCSParserRuleCall_0_1_1_3_1_0());

                                    }
                                    pushFollow(FollowSets000.FOLLOW_2);
                                    lv_ownedInitExpression_10_0=ruleExpCS();

                                    state._fsp--;
                                    if (state.failed) return current;
                                    if ( state.backtracking==0 ) {

                                      	        if (current==null) {
                                      	            current = createModelElementForParent(grammarAccess.getNavigatingArgCSRule());
                                      	        }
                                             		set(
                                             			current,
                                             			"ownedInitExpression",
                                              		lv_ownedInitExpression_10_0,
                                              		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.ExpCS");
                                      	        afterParserOrEnumRuleCall();

                                    }

                                    }


                                    }


                                    }
                                    break;

                            }


                            }


                            }
                            break;
                        case 3 :
                            // InternalCompleteOCL.g:5907:6: ( (otherlv_11= ':' ( (lv_ownedType_12_0= ruleTypeExpCS ) ) )? (otherlv_13= '<-' ( (lv_ownedCoIterator_14_0= ruleCoIteratorVariableCS ) ) )? otherlv_15= 'in' ( (lv_ownedInitExpression_16_0= ruleExpCS ) ) )
                            {
                            // InternalCompleteOCL.g:5907:6: ( (otherlv_11= ':' ( (lv_ownedType_12_0= ruleTypeExpCS ) ) )? (otherlv_13= '<-' ( (lv_ownedCoIterator_14_0= ruleCoIteratorVariableCS ) ) )? otherlv_15= 'in' ( (lv_ownedInitExpression_16_0= ruleExpCS ) ) )
                            // InternalCompleteOCL.g:5907:7: (otherlv_11= ':' ( (lv_ownedType_12_0= ruleTypeExpCS ) ) )? (otherlv_13= '<-' ( (lv_ownedCoIterator_14_0= ruleCoIteratorVariableCS ) ) )? otherlv_15= 'in' ( (lv_ownedInitExpression_16_0= ruleExpCS ) )
                            {
                            // InternalCompleteOCL.g:5907:7: (otherlv_11= ':' ( (lv_ownedType_12_0= ruleTypeExpCS ) ) )?
                            int alt118=2;
                            int LA118_0 = input.LA(1);

                            if ( (LA118_0==24) ) {
                                alt118=1;
                            }
                            switch (alt118) {
                                case 1 :
                                    // InternalCompleteOCL.g:5907:9: otherlv_11= ':' ( (lv_ownedType_12_0= ruleTypeExpCS ) )
                                    {
                                    otherlv_11=(Token)match(input,24,FollowSets000.FOLLOW_25); if (state.failed) return current;
                                    if ( state.backtracking==0 ) {

                                          	newLeafNode(otherlv_11, grammarAccess.getNavigatingArgCSAccess().getColonKeyword_0_1_2_0_0());

                                    }
                                    // InternalCompleteOCL.g:5911:1: ( (lv_ownedType_12_0= ruleTypeExpCS ) )
                                    // InternalCompleteOCL.g:5912:1: (lv_ownedType_12_0= ruleTypeExpCS )
                                    {
                                    // InternalCompleteOCL.g:5912:1: (lv_ownedType_12_0= ruleTypeExpCS )
                                    // InternalCompleteOCL.g:5913:3: lv_ownedType_12_0= ruleTypeExpCS
                                    {
                                    if ( state.backtracking==0 ) {

                                      	        newCompositeNode(grammarAccess.getNavigatingArgCSAccess().getOwnedTypeTypeExpCSParserRuleCall_0_1_2_0_1_0());

                                    }
                                    pushFollow(FollowSets000.FOLLOW_72);
                                    lv_ownedType_12_0=ruleTypeExpCS();

                                    state._fsp--;
                                    if (state.failed) return current;
                                    if ( state.backtracking==0 ) {

                                      	        if (current==null) {
                                      	            current = createModelElementForParent(grammarAccess.getNavigatingArgCSRule());
                                      	        }
                                             		set(
                                             			current,
                                             			"ownedType",
                                              		lv_ownedType_12_0,
                                              		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.TypeExpCS");
                                      	        afterParserOrEnumRuleCall();

                                    }

                                    }


                                    }


                                    }
                                    break;

                            }

                            // InternalCompleteOCL.g:5929:4: (otherlv_13= '<-' ( (lv_ownedCoIterator_14_0= ruleCoIteratorVariableCS ) ) )?
                            int alt119=2;
                            int LA119_0 = input.LA(1);

                            if ( (LA119_0==87) ) {
                                alt119=1;
                            }
                            switch (alt119) {
                                case 1 :
                                    // InternalCompleteOCL.g:5929:6: otherlv_13= '<-' ( (lv_ownedCoIterator_14_0= ruleCoIteratorVariableCS ) )
                                    {
                                    otherlv_13=(Token)match(input,87,FollowSets000.FOLLOW_19); if (state.failed) return current;
                                    if ( state.backtracking==0 ) {

                                          	newLeafNode(otherlv_13, grammarAccess.getNavigatingArgCSAccess().getLessThanSignHyphenMinusKeyword_0_1_2_1_0());

                                    }
                                    // InternalCompleteOCL.g:5933:1: ( (lv_ownedCoIterator_14_0= ruleCoIteratorVariableCS ) )
                                    // InternalCompleteOCL.g:5934:1: (lv_ownedCoIterator_14_0= ruleCoIteratorVariableCS )
                                    {
                                    // InternalCompleteOCL.g:5934:1: (lv_ownedCoIterator_14_0= ruleCoIteratorVariableCS )
                                    // InternalCompleteOCL.g:5935:3: lv_ownedCoIterator_14_0= ruleCoIteratorVariableCS
                                    {
                                    if ( state.backtracking==0 ) {

                                      	        newCompositeNode(grammarAccess.getNavigatingArgCSAccess().getOwnedCoIteratorCoIteratorVariableCSParserRuleCall_0_1_2_1_1_0());

                                    }
                                    pushFollow(FollowSets000.FOLLOW_73);
                                    lv_ownedCoIterator_14_0=ruleCoIteratorVariableCS();

                                    state._fsp--;
                                    if (state.failed) return current;
                                    if ( state.backtracking==0 ) {

                                      	        if (current==null) {
                                      	            current = createModelElementForParent(grammarAccess.getNavigatingArgCSRule());
                                      	        }
                                             		set(
                                             			current,
                                             			"ownedCoIterator",
                                              		lv_ownedCoIterator_14_0,
                                              		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.CoIteratorVariableCS");
                                      	        afterParserOrEnumRuleCall();

                                    }

                                    }


                                    }


                                    }
                                    break;

                            }

                            otherlv_15=(Token)match(input,95,FollowSets000.FOLLOW_50); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                  	newLeafNode(otherlv_15, grammarAccess.getNavigatingArgCSAccess().getInKeyword_0_1_2_2());

                            }
                            // InternalCompleteOCL.g:5955:1: ( (lv_ownedInitExpression_16_0= ruleExpCS ) )
                            // InternalCompleteOCL.g:5956:1: (lv_ownedInitExpression_16_0= ruleExpCS )
                            {
                            // InternalCompleteOCL.g:5956:1: (lv_ownedInitExpression_16_0= ruleExpCS )
                            // InternalCompleteOCL.g:5957:3: lv_ownedInitExpression_16_0= ruleExpCS
                            {
                            if ( state.backtracking==0 ) {

                              	        newCompositeNode(grammarAccess.getNavigatingArgCSAccess().getOwnedInitExpressionExpCSParserRuleCall_0_1_2_3_0());

                            }
                            pushFollow(FollowSets000.FOLLOW_2);
                            lv_ownedInitExpression_16_0=ruleExpCS();

                            state._fsp--;
                            if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElementForParent(grammarAccess.getNavigatingArgCSRule());
                              	        }
                                     		set(
                                     			current,
                                     			"ownedInitExpression",
                                      		lv_ownedInitExpression_16_0,
                                      		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.ExpCS");
                              	        afterParserOrEnumRuleCall();

                            }

                            }


                            }


                            }


                            }
                            break;

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalCompleteOCL.g:5974:6: (otherlv_17= ':' ( (lv_ownedType_18_0= ruleTypeExpCS ) ) )
                    {
                    // InternalCompleteOCL.g:5974:6: (otherlv_17= ':' ( (lv_ownedType_18_0= ruleTypeExpCS ) ) )
                    // InternalCompleteOCL.g:5974:8: otherlv_17= ':' ( (lv_ownedType_18_0= ruleTypeExpCS ) )
                    {
                    otherlv_17=(Token)match(input,24,FollowSets000.FOLLOW_25); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_17, grammarAccess.getNavigatingArgCSAccess().getColonKeyword_1_0());

                    }
                    // InternalCompleteOCL.g:5978:1: ( (lv_ownedType_18_0= ruleTypeExpCS ) )
                    // InternalCompleteOCL.g:5979:1: (lv_ownedType_18_0= ruleTypeExpCS )
                    {
                    // InternalCompleteOCL.g:5979:1: (lv_ownedType_18_0= ruleTypeExpCS )
                    // InternalCompleteOCL.g:5980:3: lv_ownedType_18_0= ruleTypeExpCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getNavigatingArgCSAccess().getOwnedTypeTypeExpCSParserRuleCall_1_1_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    lv_ownedType_18_0=ruleTypeExpCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getNavigatingArgCSRule());
                      	        }
                             		set(
                             			current,
                             			"ownedType",
                              		lv_ownedType_18_0,
                              		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.TypeExpCS");
                      	        afterParserOrEnumRuleCall();

                    }

                    }


                    }


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
    // $ANTLR end "ruleNavigatingArgCS"


    // $ANTLR start "entryRuleNavigatingBarArgCS"
    // InternalCompleteOCL.g:6004:1: entryRuleNavigatingBarArgCS returns [EObject current=null] : iv_ruleNavigatingBarArgCS= ruleNavigatingBarArgCS EOF ;
    public final EObject entryRuleNavigatingBarArgCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNavigatingBarArgCS = null;


        try {
            // InternalCompleteOCL.g:6005:2: (iv_ruleNavigatingBarArgCS= ruleNavigatingBarArgCS EOF )
            // InternalCompleteOCL.g:6006:2: iv_ruleNavigatingBarArgCS= ruleNavigatingBarArgCS EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getNavigatingBarArgCSRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleNavigatingBarArgCS=ruleNavigatingBarArgCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleNavigatingBarArgCS;
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
    // $ANTLR end "entryRuleNavigatingBarArgCS"


    // $ANTLR start "ruleNavigatingBarArgCS"
    // InternalCompleteOCL.g:6013:1: ruleNavigatingBarArgCS returns [EObject current=null] : ( ( (lv_prefix_0_0= '|' ) ) ( (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS ) ) (otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? )? ) ;
    public final EObject ruleNavigatingBarArgCS() throws RecognitionException {
        EObject current = null;

        Token lv_prefix_0_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_ownedNameExpression_1_0 = null;

        EObject lv_ownedType_3_0 = null;

        EObject lv_ownedInitExpression_5_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:6016:28: ( ( ( (lv_prefix_0_0= '|' ) ) ( (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS ) ) (otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? )? ) )
            // InternalCompleteOCL.g:6017:1: ( ( (lv_prefix_0_0= '|' ) ) ( (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS ) ) (otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? )? )
            {
            // InternalCompleteOCL.g:6017:1: ( ( (lv_prefix_0_0= '|' ) ) ( (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS ) ) (otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? )? )
            // InternalCompleteOCL.g:6017:2: ( (lv_prefix_0_0= '|' ) ) ( (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS ) ) (otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? )?
            {
            // InternalCompleteOCL.g:6017:2: ( (lv_prefix_0_0= '|' ) )
            // InternalCompleteOCL.g:6018:1: (lv_prefix_0_0= '|' )
            {
            // InternalCompleteOCL.g:6018:1: (lv_prefix_0_0= '|' )
            // InternalCompleteOCL.g:6019:3: lv_prefix_0_0= '|'
            {
            lv_prefix_0_0=(Token)match(input,96,FollowSets000.FOLLOW_74); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                      newLeafNode(lv_prefix_0_0, grammarAccess.getNavigatingBarArgCSAccess().getPrefixVerticalLineKeyword_0_0());

            }
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElement(grammarAccess.getNavigatingBarArgCSRule());
              	        }
                     		setWithLastConsumed(current, "prefix", lv_prefix_0_0, "|");

            }

            }


            }

            // InternalCompleteOCL.g:6032:2: ( (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS ) )
            // InternalCompleteOCL.g:6033:1: (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS )
            {
            // InternalCompleteOCL.g:6033:1: (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS )
            // InternalCompleteOCL.g:6034:3: lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getNavigatingBarArgCSAccess().getOwnedNameExpressionNavigatingArgExpCSParserRuleCall_1_0());

            }
            pushFollow(FollowSets000.FOLLOW_75);
            lv_ownedNameExpression_1_0=ruleNavigatingArgExpCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getNavigatingBarArgCSRule());
              	        }
                     		set(
                     			current,
                     			"ownedNameExpression",
                      		lv_ownedNameExpression_1_0,
                      		"org.eclipse.ocl.xtext.completeocl.CompleteOCL.NavigatingArgExpCS");
              	        afterParserOrEnumRuleCall();

            }

            }


            }

            // InternalCompleteOCL.g:6050:2: (otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? )?
            int alt123=2;
            int LA123_0 = input.LA(1);

            if ( (LA123_0==24) ) {
                alt123=1;
            }
            switch (alt123) {
                case 1 :
                    // InternalCompleteOCL.g:6050:4: otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )?
                    {
                    otherlv_2=(Token)match(input,24,FollowSets000.FOLLOW_25); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_2, grammarAccess.getNavigatingBarArgCSAccess().getColonKeyword_2_0());

                    }
                    // InternalCompleteOCL.g:6054:1: ( (lv_ownedType_3_0= ruleTypeExpCS ) )
                    // InternalCompleteOCL.g:6055:1: (lv_ownedType_3_0= ruleTypeExpCS )
                    {
                    // InternalCompleteOCL.g:6055:1: (lv_ownedType_3_0= ruleTypeExpCS )
                    // InternalCompleteOCL.g:6056:3: lv_ownedType_3_0= ruleTypeExpCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getNavigatingBarArgCSAccess().getOwnedTypeTypeExpCSParserRuleCall_2_1_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_70);
                    lv_ownedType_3_0=ruleTypeExpCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getNavigatingBarArgCSRule());
                      	        }
                             		set(
                             			current,
                             			"ownedType",
                              		lv_ownedType_3_0,
                              		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.TypeExpCS");
                      	        afterParserOrEnumRuleCall();

                    }

                    }


                    }

                    // InternalCompleteOCL.g:6072:2: (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )?
                    int alt122=2;
                    int LA122_0 = input.LA(1);

                    if ( (LA122_0==30) ) {
                        alt122=1;
                    }
                    switch (alt122) {
                        case 1 :
                            // InternalCompleteOCL.g:6072:4: otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) )
                            {
                            otherlv_4=(Token)match(input,30,FollowSets000.FOLLOW_50); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                  	newLeafNode(otherlv_4, grammarAccess.getNavigatingBarArgCSAccess().getEqualsSignKeyword_2_2_0());

                            }
                            // InternalCompleteOCL.g:6076:1: ( (lv_ownedInitExpression_5_0= ruleExpCS ) )
                            // InternalCompleteOCL.g:6077:1: (lv_ownedInitExpression_5_0= ruleExpCS )
                            {
                            // InternalCompleteOCL.g:6077:1: (lv_ownedInitExpression_5_0= ruleExpCS )
                            // InternalCompleteOCL.g:6078:3: lv_ownedInitExpression_5_0= ruleExpCS
                            {
                            if ( state.backtracking==0 ) {

                              	        newCompositeNode(grammarAccess.getNavigatingBarArgCSAccess().getOwnedInitExpressionExpCSParserRuleCall_2_2_1_0());

                            }
                            pushFollow(FollowSets000.FOLLOW_2);
                            lv_ownedInitExpression_5_0=ruleExpCS();

                            state._fsp--;
                            if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElementForParent(grammarAccess.getNavigatingBarArgCSRule());
                              	        }
                                     		set(
                                     			current,
                                     			"ownedInitExpression",
                                      		lv_ownedInitExpression_5_0,
                                      		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.ExpCS");
                              	        afterParserOrEnumRuleCall();

                            }

                            }


                            }


                            }
                            break;

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
    // $ANTLR end "ruleNavigatingBarArgCS"


    // $ANTLR start "entryRuleNavigatingCommaArgCS"
    // InternalCompleteOCL.g:6102:1: entryRuleNavigatingCommaArgCS returns [EObject current=null] : iv_ruleNavigatingCommaArgCS= ruleNavigatingCommaArgCS EOF ;
    public final EObject entryRuleNavigatingCommaArgCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNavigatingCommaArgCS = null;


        try {
            // InternalCompleteOCL.g:6103:2: (iv_ruleNavigatingCommaArgCS= ruleNavigatingCommaArgCS EOF )
            // InternalCompleteOCL.g:6104:2: iv_ruleNavigatingCommaArgCS= ruleNavigatingCommaArgCS EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getNavigatingCommaArgCSRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleNavigatingCommaArgCS=ruleNavigatingCommaArgCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleNavigatingCommaArgCS;
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
    // $ANTLR end "entryRuleNavigatingCommaArgCS"


    // $ANTLR start "ruleNavigatingCommaArgCS"
    // InternalCompleteOCL.g:6111:1: ruleNavigatingCommaArgCS returns [EObject current=null] : ( ( (lv_prefix_0_0= ',' ) ) ( (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS ) ) ( (otherlv_2= '<-' ( (lv_ownedCoIterator_3_0= ruleCoIteratorVariableCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? ) | (otherlv_6= ':' ( (lv_ownedType_7_0= ruleTypeExpCS ) ) (otherlv_8= '<-' ( (lv_ownedCoIterator_9_0= ruleCoIteratorVariableCS ) ) )? (otherlv_10= '=' ( (lv_ownedInitExpression_11_0= ruleExpCS ) ) )? ) | ( (otherlv_12= ':' ( (lv_ownedType_13_0= ruleTypeExpCS ) ) )? (otherlv_14= '<-' ( (lv_ownedCoIterator_15_0= ruleCoIteratorVariableCS ) ) )? otherlv_16= 'in' ( (lv_ownedInitExpression_17_0= ruleExpCS ) ) ) )? ) ;
    public final EObject ruleNavigatingCommaArgCS() throws RecognitionException {
        EObject current = null;

        Token lv_prefix_0_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        Token otherlv_12=null;
        Token otherlv_14=null;
        Token otherlv_16=null;
        EObject lv_ownedNameExpression_1_0 = null;

        EObject lv_ownedCoIterator_3_0 = null;

        EObject lv_ownedInitExpression_5_0 = null;

        EObject lv_ownedType_7_0 = null;

        EObject lv_ownedCoIterator_9_0 = null;

        EObject lv_ownedInitExpression_11_0 = null;

        EObject lv_ownedType_13_0 = null;

        EObject lv_ownedCoIterator_15_0 = null;

        EObject lv_ownedInitExpression_17_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:6114:28: ( ( ( (lv_prefix_0_0= ',' ) ) ( (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS ) ) ( (otherlv_2= '<-' ( (lv_ownedCoIterator_3_0= ruleCoIteratorVariableCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? ) | (otherlv_6= ':' ( (lv_ownedType_7_0= ruleTypeExpCS ) ) (otherlv_8= '<-' ( (lv_ownedCoIterator_9_0= ruleCoIteratorVariableCS ) ) )? (otherlv_10= '=' ( (lv_ownedInitExpression_11_0= ruleExpCS ) ) )? ) | ( (otherlv_12= ':' ( (lv_ownedType_13_0= ruleTypeExpCS ) ) )? (otherlv_14= '<-' ( (lv_ownedCoIterator_15_0= ruleCoIteratorVariableCS ) ) )? otherlv_16= 'in' ( (lv_ownedInitExpression_17_0= ruleExpCS ) ) ) )? ) )
            // InternalCompleteOCL.g:6115:1: ( ( (lv_prefix_0_0= ',' ) ) ( (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS ) ) ( (otherlv_2= '<-' ( (lv_ownedCoIterator_3_0= ruleCoIteratorVariableCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? ) | (otherlv_6= ':' ( (lv_ownedType_7_0= ruleTypeExpCS ) ) (otherlv_8= '<-' ( (lv_ownedCoIterator_9_0= ruleCoIteratorVariableCS ) ) )? (otherlv_10= '=' ( (lv_ownedInitExpression_11_0= ruleExpCS ) ) )? ) | ( (otherlv_12= ':' ( (lv_ownedType_13_0= ruleTypeExpCS ) ) )? (otherlv_14= '<-' ( (lv_ownedCoIterator_15_0= ruleCoIteratorVariableCS ) ) )? otherlv_16= 'in' ( (lv_ownedInitExpression_17_0= ruleExpCS ) ) ) )? )
            {
            // InternalCompleteOCL.g:6115:1: ( ( (lv_prefix_0_0= ',' ) ) ( (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS ) ) ( (otherlv_2= '<-' ( (lv_ownedCoIterator_3_0= ruleCoIteratorVariableCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? ) | (otherlv_6= ':' ( (lv_ownedType_7_0= ruleTypeExpCS ) ) (otherlv_8= '<-' ( (lv_ownedCoIterator_9_0= ruleCoIteratorVariableCS ) ) )? (otherlv_10= '=' ( (lv_ownedInitExpression_11_0= ruleExpCS ) ) )? ) | ( (otherlv_12= ':' ( (lv_ownedType_13_0= ruleTypeExpCS ) ) )? (otherlv_14= '<-' ( (lv_ownedCoIterator_15_0= ruleCoIteratorVariableCS ) ) )? otherlv_16= 'in' ( (lv_ownedInitExpression_17_0= ruleExpCS ) ) ) )? )
            // InternalCompleteOCL.g:6115:2: ( (lv_prefix_0_0= ',' ) ) ( (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS ) ) ( (otherlv_2= '<-' ( (lv_ownedCoIterator_3_0= ruleCoIteratorVariableCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? ) | (otherlv_6= ':' ( (lv_ownedType_7_0= ruleTypeExpCS ) ) (otherlv_8= '<-' ( (lv_ownedCoIterator_9_0= ruleCoIteratorVariableCS ) ) )? (otherlv_10= '=' ( (lv_ownedInitExpression_11_0= ruleExpCS ) ) )? ) | ( (otherlv_12= ':' ( (lv_ownedType_13_0= ruleTypeExpCS ) ) )? (otherlv_14= '<-' ( (lv_ownedCoIterator_15_0= ruleCoIteratorVariableCS ) ) )? otherlv_16= 'in' ( (lv_ownedInitExpression_17_0= ruleExpCS ) ) ) )?
            {
            // InternalCompleteOCL.g:6115:2: ( (lv_prefix_0_0= ',' ) )
            // InternalCompleteOCL.g:6116:1: (lv_prefix_0_0= ',' )
            {
            // InternalCompleteOCL.g:6116:1: (lv_prefix_0_0= ',' )
            // InternalCompleteOCL.g:6117:3: lv_prefix_0_0= ','
            {
            lv_prefix_0_0=(Token)match(input,29,FollowSets000.FOLLOW_74); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                      newLeafNode(lv_prefix_0_0, grammarAccess.getNavigatingCommaArgCSAccess().getPrefixCommaKeyword_0_0());

            }
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElement(grammarAccess.getNavigatingCommaArgCSRule());
              	        }
                     		setWithLastConsumed(current, "prefix", lv_prefix_0_0, ",");

            }

            }


            }

            // InternalCompleteOCL.g:6130:2: ( (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS ) )
            // InternalCompleteOCL.g:6131:1: (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS )
            {
            // InternalCompleteOCL.g:6131:1: (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS )
            // InternalCompleteOCL.g:6132:3: lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getNavigatingCommaArgCSAccess().getOwnedNameExpressionNavigatingArgExpCSParserRuleCall_1_0());

            }
            pushFollow(FollowSets000.FOLLOW_69);
            lv_ownedNameExpression_1_0=ruleNavigatingArgExpCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getNavigatingCommaArgCSRule());
              	        }
                     		set(
                     			current,
                     			"ownedNameExpression",
                      		lv_ownedNameExpression_1_0,
                      		"org.eclipse.ocl.xtext.completeocl.CompleteOCL.NavigatingArgExpCS");
              	        afterParserOrEnumRuleCall();

            }

            }


            }

            // InternalCompleteOCL.g:6148:2: ( (otherlv_2= '<-' ( (lv_ownedCoIterator_3_0= ruleCoIteratorVariableCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? ) | (otherlv_6= ':' ( (lv_ownedType_7_0= ruleTypeExpCS ) ) (otherlv_8= '<-' ( (lv_ownedCoIterator_9_0= ruleCoIteratorVariableCS ) ) )? (otherlv_10= '=' ( (lv_ownedInitExpression_11_0= ruleExpCS ) ) )? ) | ( (otherlv_12= ':' ( (lv_ownedType_13_0= ruleTypeExpCS ) ) )? (otherlv_14= '<-' ( (lv_ownedCoIterator_15_0= ruleCoIteratorVariableCS ) ) )? otherlv_16= 'in' ( (lv_ownedInitExpression_17_0= ruleExpCS ) ) ) )?
            int alt129=4;
            alt129 = dfa129.predict(input);
            switch (alt129) {
                case 1 :
                    // InternalCompleteOCL.g:6148:3: (otherlv_2= '<-' ( (lv_ownedCoIterator_3_0= ruleCoIteratorVariableCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? )
                    {
                    // InternalCompleteOCL.g:6148:3: (otherlv_2= '<-' ( (lv_ownedCoIterator_3_0= ruleCoIteratorVariableCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? )
                    // InternalCompleteOCL.g:6148:5: otherlv_2= '<-' ( (lv_ownedCoIterator_3_0= ruleCoIteratorVariableCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )?
                    {
                    otherlv_2=(Token)match(input,87,FollowSets000.FOLLOW_19); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_2, grammarAccess.getNavigatingCommaArgCSAccess().getLessThanSignHyphenMinusKeyword_2_0_0());

                    }
                    // InternalCompleteOCL.g:6152:1: ( (lv_ownedCoIterator_3_0= ruleCoIteratorVariableCS ) )
                    // InternalCompleteOCL.g:6153:1: (lv_ownedCoIterator_3_0= ruleCoIteratorVariableCS )
                    {
                    // InternalCompleteOCL.g:6153:1: (lv_ownedCoIterator_3_0= ruleCoIteratorVariableCS )
                    // InternalCompleteOCL.g:6154:3: lv_ownedCoIterator_3_0= ruleCoIteratorVariableCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getNavigatingCommaArgCSAccess().getOwnedCoIteratorCoIteratorVariableCSParserRuleCall_2_0_1_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_70);
                    lv_ownedCoIterator_3_0=ruleCoIteratorVariableCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getNavigatingCommaArgCSRule());
                      	        }
                             		set(
                             			current,
                             			"ownedCoIterator",
                              		lv_ownedCoIterator_3_0,
                              		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.CoIteratorVariableCS");
                      	        afterParserOrEnumRuleCall();

                    }

                    }


                    }

                    // InternalCompleteOCL.g:6170:2: (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )?
                    int alt124=2;
                    int LA124_0 = input.LA(1);

                    if ( (LA124_0==30) ) {
                        alt124=1;
                    }
                    switch (alt124) {
                        case 1 :
                            // InternalCompleteOCL.g:6170:4: otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) )
                            {
                            otherlv_4=(Token)match(input,30,FollowSets000.FOLLOW_50); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                  	newLeafNode(otherlv_4, grammarAccess.getNavigatingCommaArgCSAccess().getEqualsSignKeyword_2_0_2_0());

                            }
                            // InternalCompleteOCL.g:6174:1: ( (lv_ownedInitExpression_5_0= ruleExpCS ) )
                            // InternalCompleteOCL.g:6175:1: (lv_ownedInitExpression_5_0= ruleExpCS )
                            {
                            // InternalCompleteOCL.g:6175:1: (lv_ownedInitExpression_5_0= ruleExpCS )
                            // InternalCompleteOCL.g:6176:3: lv_ownedInitExpression_5_0= ruleExpCS
                            {
                            if ( state.backtracking==0 ) {

                              	        newCompositeNode(grammarAccess.getNavigatingCommaArgCSAccess().getOwnedInitExpressionExpCSParserRuleCall_2_0_2_1_0());

                            }
                            pushFollow(FollowSets000.FOLLOW_2);
                            lv_ownedInitExpression_5_0=ruleExpCS();

                            state._fsp--;
                            if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElementForParent(grammarAccess.getNavigatingCommaArgCSRule());
                              	        }
                                     		set(
                                     			current,
                                     			"ownedInitExpression",
                                      		lv_ownedInitExpression_5_0,
                                      		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.ExpCS");
                              	        afterParserOrEnumRuleCall();

                            }

                            }


                            }


                            }
                            break;

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalCompleteOCL.g:6193:6: (otherlv_6= ':' ( (lv_ownedType_7_0= ruleTypeExpCS ) ) (otherlv_8= '<-' ( (lv_ownedCoIterator_9_0= ruleCoIteratorVariableCS ) ) )? (otherlv_10= '=' ( (lv_ownedInitExpression_11_0= ruleExpCS ) ) )? )
                    {
                    // InternalCompleteOCL.g:6193:6: (otherlv_6= ':' ( (lv_ownedType_7_0= ruleTypeExpCS ) ) (otherlv_8= '<-' ( (lv_ownedCoIterator_9_0= ruleCoIteratorVariableCS ) ) )? (otherlv_10= '=' ( (lv_ownedInitExpression_11_0= ruleExpCS ) ) )? )
                    // InternalCompleteOCL.g:6193:8: otherlv_6= ':' ( (lv_ownedType_7_0= ruleTypeExpCS ) ) (otherlv_8= '<-' ( (lv_ownedCoIterator_9_0= ruleCoIteratorVariableCS ) ) )? (otherlv_10= '=' ( (lv_ownedInitExpression_11_0= ruleExpCS ) ) )?
                    {
                    otherlv_6=(Token)match(input,24,FollowSets000.FOLLOW_25); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_6, grammarAccess.getNavigatingCommaArgCSAccess().getColonKeyword_2_1_0());

                    }
                    // InternalCompleteOCL.g:6197:1: ( (lv_ownedType_7_0= ruleTypeExpCS ) )
                    // InternalCompleteOCL.g:6198:1: (lv_ownedType_7_0= ruleTypeExpCS )
                    {
                    // InternalCompleteOCL.g:6198:1: (lv_ownedType_7_0= ruleTypeExpCS )
                    // InternalCompleteOCL.g:6199:3: lv_ownedType_7_0= ruleTypeExpCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getNavigatingCommaArgCSAccess().getOwnedTypeTypeExpCSParserRuleCall_2_1_1_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_71);
                    lv_ownedType_7_0=ruleTypeExpCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getNavigatingCommaArgCSRule());
                      	        }
                             		set(
                             			current,
                             			"ownedType",
                              		lv_ownedType_7_0,
                              		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.TypeExpCS");
                      	        afterParserOrEnumRuleCall();

                    }

                    }


                    }

                    // InternalCompleteOCL.g:6215:2: (otherlv_8= '<-' ( (lv_ownedCoIterator_9_0= ruleCoIteratorVariableCS ) ) )?
                    int alt125=2;
                    int LA125_0 = input.LA(1);

                    if ( (LA125_0==87) ) {
                        alt125=1;
                    }
                    switch (alt125) {
                        case 1 :
                            // InternalCompleteOCL.g:6215:4: otherlv_8= '<-' ( (lv_ownedCoIterator_9_0= ruleCoIteratorVariableCS ) )
                            {
                            otherlv_8=(Token)match(input,87,FollowSets000.FOLLOW_19); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                  	newLeafNode(otherlv_8, grammarAccess.getNavigatingCommaArgCSAccess().getLessThanSignHyphenMinusKeyword_2_1_2_0());

                            }
                            // InternalCompleteOCL.g:6219:1: ( (lv_ownedCoIterator_9_0= ruleCoIteratorVariableCS ) )
                            // InternalCompleteOCL.g:6220:1: (lv_ownedCoIterator_9_0= ruleCoIteratorVariableCS )
                            {
                            // InternalCompleteOCL.g:6220:1: (lv_ownedCoIterator_9_0= ruleCoIteratorVariableCS )
                            // InternalCompleteOCL.g:6221:3: lv_ownedCoIterator_9_0= ruleCoIteratorVariableCS
                            {
                            if ( state.backtracking==0 ) {

                              	        newCompositeNode(grammarAccess.getNavigatingCommaArgCSAccess().getOwnedCoIteratorCoIteratorVariableCSParserRuleCall_2_1_2_1_0());

                            }
                            pushFollow(FollowSets000.FOLLOW_70);
                            lv_ownedCoIterator_9_0=ruleCoIteratorVariableCS();

                            state._fsp--;
                            if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElementForParent(grammarAccess.getNavigatingCommaArgCSRule());
                              	        }
                                     		set(
                                     			current,
                                     			"ownedCoIterator",
                                      		lv_ownedCoIterator_9_0,
                                      		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.CoIteratorVariableCS");
                              	        afterParserOrEnumRuleCall();

                            }

                            }


                            }


                            }
                            break;

                    }

                    // InternalCompleteOCL.g:6237:4: (otherlv_10= '=' ( (lv_ownedInitExpression_11_0= ruleExpCS ) ) )?
                    int alt126=2;
                    int LA126_0 = input.LA(1);

                    if ( (LA126_0==30) ) {
                        alt126=1;
                    }
                    switch (alt126) {
                        case 1 :
                            // InternalCompleteOCL.g:6237:6: otherlv_10= '=' ( (lv_ownedInitExpression_11_0= ruleExpCS ) )
                            {
                            otherlv_10=(Token)match(input,30,FollowSets000.FOLLOW_50); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                  	newLeafNode(otherlv_10, grammarAccess.getNavigatingCommaArgCSAccess().getEqualsSignKeyword_2_1_3_0());

                            }
                            // InternalCompleteOCL.g:6241:1: ( (lv_ownedInitExpression_11_0= ruleExpCS ) )
                            // InternalCompleteOCL.g:6242:1: (lv_ownedInitExpression_11_0= ruleExpCS )
                            {
                            // InternalCompleteOCL.g:6242:1: (lv_ownedInitExpression_11_0= ruleExpCS )
                            // InternalCompleteOCL.g:6243:3: lv_ownedInitExpression_11_0= ruleExpCS
                            {
                            if ( state.backtracking==0 ) {

                              	        newCompositeNode(grammarAccess.getNavigatingCommaArgCSAccess().getOwnedInitExpressionExpCSParserRuleCall_2_1_3_1_0());

                            }
                            pushFollow(FollowSets000.FOLLOW_2);
                            lv_ownedInitExpression_11_0=ruleExpCS();

                            state._fsp--;
                            if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElementForParent(grammarAccess.getNavigatingCommaArgCSRule());
                              	        }
                                     		set(
                                     			current,
                                     			"ownedInitExpression",
                                      		lv_ownedInitExpression_11_0,
                                      		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.ExpCS");
                              	        afterParserOrEnumRuleCall();

                            }

                            }


                            }


                            }
                            break;

                    }


                    }


                    }
                    break;
                case 3 :
                    // InternalCompleteOCL.g:6260:6: ( (otherlv_12= ':' ( (lv_ownedType_13_0= ruleTypeExpCS ) ) )? (otherlv_14= '<-' ( (lv_ownedCoIterator_15_0= ruleCoIteratorVariableCS ) ) )? otherlv_16= 'in' ( (lv_ownedInitExpression_17_0= ruleExpCS ) ) )
                    {
                    // InternalCompleteOCL.g:6260:6: ( (otherlv_12= ':' ( (lv_ownedType_13_0= ruleTypeExpCS ) ) )? (otherlv_14= '<-' ( (lv_ownedCoIterator_15_0= ruleCoIteratorVariableCS ) ) )? otherlv_16= 'in' ( (lv_ownedInitExpression_17_0= ruleExpCS ) ) )
                    // InternalCompleteOCL.g:6260:7: (otherlv_12= ':' ( (lv_ownedType_13_0= ruleTypeExpCS ) ) )? (otherlv_14= '<-' ( (lv_ownedCoIterator_15_0= ruleCoIteratorVariableCS ) ) )? otherlv_16= 'in' ( (lv_ownedInitExpression_17_0= ruleExpCS ) )
                    {
                    // InternalCompleteOCL.g:6260:7: (otherlv_12= ':' ( (lv_ownedType_13_0= ruleTypeExpCS ) ) )?
                    int alt127=2;
                    int LA127_0 = input.LA(1);

                    if ( (LA127_0==24) ) {
                        alt127=1;
                    }
                    switch (alt127) {
                        case 1 :
                            // InternalCompleteOCL.g:6260:9: otherlv_12= ':' ( (lv_ownedType_13_0= ruleTypeExpCS ) )
                            {
                            otherlv_12=(Token)match(input,24,FollowSets000.FOLLOW_25); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                  	newLeafNode(otherlv_12, grammarAccess.getNavigatingCommaArgCSAccess().getColonKeyword_2_2_0_0());

                            }
                            // InternalCompleteOCL.g:6264:1: ( (lv_ownedType_13_0= ruleTypeExpCS ) )
                            // InternalCompleteOCL.g:6265:1: (lv_ownedType_13_0= ruleTypeExpCS )
                            {
                            // InternalCompleteOCL.g:6265:1: (lv_ownedType_13_0= ruleTypeExpCS )
                            // InternalCompleteOCL.g:6266:3: lv_ownedType_13_0= ruleTypeExpCS
                            {
                            if ( state.backtracking==0 ) {

                              	        newCompositeNode(grammarAccess.getNavigatingCommaArgCSAccess().getOwnedTypeTypeExpCSParserRuleCall_2_2_0_1_0());

                            }
                            pushFollow(FollowSets000.FOLLOW_72);
                            lv_ownedType_13_0=ruleTypeExpCS();

                            state._fsp--;
                            if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElementForParent(grammarAccess.getNavigatingCommaArgCSRule());
                              	        }
                                     		set(
                                     			current,
                                     			"ownedType",
                                      		lv_ownedType_13_0,
                                      		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.TypeExpCS");
                              	        afterParserOrEnumRuleCall();

                            }

                            }


                            }


                            }
                            break;

                    }

                    // InternalCompleteOCL.g:6282:4: (otherlv_14= '<-' ( (lv_ownedCoIterator_15_0= ruleCoIteratorVariableCS ) ) )?
                    int alt128=2;
                    int LA128_0 = input.LA(1);

                    if ( (LA128_0==87) ) {
                        alt128=1;
                    }
                    switch (alt128) {
                        case 1 :
                            // InternalCompleteOCL.g:6282:6: otherlv_14= '<-' ( (lv_ownedCoIterator_15_0= ruleCoIteratorVariableCS ) )
                            {
                            otherlv_14=(Token)match(input,87,FollowSets000.FOLLOW_19); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                  	newLeafNode(otherlv_14, grammarAccess.getNavigatingCommaArgCSAccess().getLessThanSignHyphenMinusKeyword_2_2_1_0());

                            }
                            // InternalCompleteOCL.g:6286:1: ( (lv_ownedCoIterator_15_0= ruleCoIteratorVariableCS ) )
                            // InternalCompleteOCL.g:6287:1: (lv_ownedCoIterator_15_0= ruleCoIteratorVariableCS )
                            {
                            // InternalCompleteOCL.g:6287:1: (lv_ownedCoIterator_15_0= ruleCoIteratorVariableCS )
                            // InternalCompleteOCL.g:6288:3: lv_ownedCoIterator_15_0= ruleCoIteratorVariableCS
                            {
                            if ( state.backtracking==0 ) {

                              	        newCompositeNode(grammarAccess.getNavigatingCommaArgCSAccess().getOwnedCoIteratorCoIteratorVariableCSParserRuleCall_2_2_1_1_0());

                            }
                            pushFollow(FollowSets000.FOLLOW_73);
                            lv_ownedCoIterator_15_0=ruleCoIteratorVariableCS();

                            state._fsp--;
                            if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElementForParent(grammarAccess.getNavigatingCommaArgCSRule());
                              	        }
                                     		set(
                                     			current,
                                     			"ownedCoIterator",
                                      		lv_ownedCoIterator_15_0,
                                      		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.CoIteratorVariableCS");
                              	        afterParserOrEnumRuleCall();

                            }

                            }


                            }


                            }
                            break;

                    }

                    otherlv_16=(Token)match(input,95,FollowSets000.FOLLOW_50); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_16, grammarAccess.getNavigatingCommaArgCSAccess().getInKeyword_2_2_2());

                    }
                    // InternalCompleteOCL.g:6308:1: ( (lv_ownedInitExpression_17_0= ruleExpCS ) )
                    // InternalCompleteOCL.g:6309:1: (lv_ownedInitExpression_17_0= ruleExpCS )
                    {
                    // InternalCompleteOCL.g:6309:1: (lv_ownedInitExpression_17_0= ruleExpCS )
                    // InternalCompleteOCL.g:6310:3: lv_ownedInitExpression_17_0= ruleExpCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getNavigatingCommaArgCSAccess().getOwnedInitExpressionExpCSParserRuleCall_2_2_3_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    lv_ownedInitExpression_17_0=ruleExpCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getNavigatingCommaArgCSRule());
                      	        }
                             		set(
                             			current,
                             			"ownedInitExpression",
                              		lv_ownedInitExpression_17_0,
                              		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.ExpCS");
                      	        afterParserOrEnumRuleCall();

                    }

                    }


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
    // $ANTLR end "ruleNavigatingCommaArgCS"


    // $ANTLR start "entryRuleNavigatingSemiArgCS"
    // InternalCompleteOCL.g:6334:1: entryRuleNavigatingSemiArgCS returns [EObject current=null] : iv_ruleNavigatingSemiArgCS= ruleNavigatingSemiArgCS EOF ;
    public final EObject entryRuleNavigatingSemiArgCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNavigatingSemiArgCS = null;


        try {
            // InternalCompleteOCL.g:6335:2: (iv_ruleNavigatingSemiArgCS= ruleNavigatingSemiArgCS EOF )
            // InternalCompleteOCL.g:6336:2: iv_ruleNavigatingSemiArgCS= ruleNavigatingSemiArgCS EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getNavigatingSemiArgCSRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleNavigatingSemiArgCS=ruleNavigatingSemiArgCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleNavigatingSemiArgCS;
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
    // $ANTLR end "entryRuleNavigatingSemiArgCS"


    // $ANTLR start "ruleNavigatingSemiArgCS"
    // InternalCompleteOCL.g:6343:1: ruleNavigatingSemiArgCS returns [EObject current=null] : ( ( (lv_prefix_0_0= ';' ) ) ( (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS ) ) (otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? )? ) ;
    public final EObject ruleNavigatingSemiArgCS() throws RecognitionException {
        EObject current = null;

        Token lv_prefix_0_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_ownedNameExpression_1_0 = null;

        EObject lv_ownedType_3_0 = null;

        EObject lv_ownedInitExpression_5_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:6346:28: ( ( ( (lv_prefix_0_0= ';' ) ) ( (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS ) ) (otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? )? ) )
            // InternalCompleteOCL.g:6347:1: ( ( (lv_prefix_0_0= ';' ) ) ( (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS ) ) (otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? )? )
            {
            // InternalCompleteOCL.g:6347:1: ( ( (lv_prefix_0_0= ';' ) ) ( (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS ) ) (otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? )? )
            // InternalCompleteOCL.g:6347:2: ( (lv_prefix_0_0= ';' ) ) ( (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS ) ) (otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? )?
            {
            // InternalCompleteOCL.g:6347:2: ( (lv_prefix_0_0= ';' ) )
            // InternalCompleteOCL.g:6348:1: (lv_prefix_0_0= ';' )
            {
            // InternalCompleteOCL.g:6348:1: (lv_prefix_0_0= ';' )
            // InternalCompleteOCL.g:6349:3: lv_prefix_0_0= ';'
            {
            lv_prefix_0_0=(Token)match(input,97,FollowSets000.FOLLOW_74); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                      newLeafNode(lv_prefix_0_0, grammarAccess.getNavigatingSemiArgCSAccess().getPrefixSemicolonKeyword_0_0());

            }
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElement(grammarAccess.getNavigatingSemiArgCSRule());
              	        }
                     		setWithLastConsumed(current, "prefix", lv_prefix_0_0, ";");

            }

            }


            }

            // InternalCompleteOCL.g:6362:2: ( (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS ) )
            // InternalCompleteOCL.g:6363:1: (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS )
            {
            // InternalCompleteOCL.g:6363:1: (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS )
            // InternalCompleteOCL.g:6364:3: lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getNavigatingSemiArgCSAccess().getOwnedNameExpressionNavigatingArgExpCSParserRuleCall_1_0());

            }
            pushFollow(FollowSets000.FOLLOW_75);
            lv_ownedNameExpression_1_0=ruleNavigatingArgExpCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getNavigatingSemiArgCSRule());
              	        }
                     		set(
                     			current,
                     			"ownedNameExpression",
                      		lv_ownedNameExpression_1_0,
                      		"org.eclipse.ocl.xtext.completeocl.CompleteOCL.NavigatingArgExpCS");
              	        afterParserOrEnumRuleCall();

            }

            }


            }

            // InternalCompleteOCL.g:6380:2: (otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? )?
            int alt131=2;
            int LA131_0 = input.LA(1);

            if ( (LA131_0==24) ) {
                alt131=1;
            }
            switch (alt131) {
                case 1 :
                    // InternalCompleteOCL.g:6380:4: otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )?
                    {
                    otherlv_2=(Token)match(input,24,FollowSets000.FOLLOW_25); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_2, grammarAccess.getNavigatingSemiArgCSAccess().getColonKeyword_2_0());

                    }
                    // InternalCompleteOCL.g:6384:1: ( (lv_ownedType_3_0= ruleTypeExpCS ) )
                    // InternalCompleteOCL.g:6385:1: (lv_ownedType_3_0= ruleTypeExpCS )
                    {
                    // InternalCompleteOCL.g:6385:1: (lv_ownedType_3_0= ruleTypeExpCS )
                    // InternalCompleteOCL.g:6386:3: lv_ownedType_3_0= ruleTypeExpCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getNavigatingSemiArgCSAccess().getOwnedTypeTypeExpCSParserRuleCall_2_1_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_70);
                    lv_ownedType_3_0=ruleTypeExpCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getNavigatingSemiArgCSRule());
                      	        }
                             		set(
                             			current,
                             			"ownedType",
                              		lv_ownedType_3_0,
                              		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.TypeExpCS");
                      	        afterParserOrEnumRuleCall();

                    }

                    }


                    }

                    // InternalCompleteOCL.g:6402:2: (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )?
                    int alt130=2;
                    int LA130_0 = input.LA(1);

                    if ( (LA130_0==30) ) {
                        alt130=1;
                    }
                    switch (alt130) {
                        case 1 :
                            // InternalCompleteOCL.g:6402:4: otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) )
                            {
                            otherlv_4=(Token)match(input,30,FollowSets000.FOLLOW_50); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                  	newLeafNode(otherlv_4, grammarAccess.getNavigatingSemiArgCSAccess().getEqualsSignKeyword_2_2_0());

                            }
                            // InternalCompleteOCL.g:6406:1: ( (lv_ownedInitExpression_5_0= ruleExpCS ) )
                            // InternalCompleteOCL.g:6407:1: (lv_ownedInitExpression_5_0= ruleExpCS )
                            {
                            // InternalCompleteOCL.g:6407:1: (lv_ownedInitExpression_5_0= ruleExpCS )
                            // InternalCompleteOCL.g:6408:3: lv_ownedInitExpression_5_0= ruleExpCS
                            {
                            if ( state.backtracking==0 ) {

                              	        newCompositeNode(grammarAccess.getNavigatingSemiArgCSAccess().getOwnedInitExpressionExpCSParserRuleCall_2_2_1_0());

                            }
                            pushFollow(FollowSets000.FOLLOW_2);
                            lv_ownedInitExpression_5_0=ruleExpCS();

                            state._fsp--;
                            if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElementForParent(grammarAccess.getNavigatingSemiArgCSRule());
                              	        }
                                     		set(
                                     			current,
                                     			"ownedInitExpression",
                                      		lv_ownedInitExpression_5_0,
                                      		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.ExpCS");
                              	        afterParserOrEnumRuleCall();

                            }

                            }


                            }


                            }
                            break;

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
    // $ANTLR end "ruleNavigatingSemiArgCS"


    // $ANTLR start "entryRuleCoIteratorVariableCS"
    // InternalCompleteOCL.g:6432:1: entryRuleCoIteratorVariableCS returns [EObject current=null] : iv_ruleCoIteratorVariableCS= ruleCoIteratorVariableCS EOF ;
    public final EObject entryRuleCoIteratorVariableCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCoIteratorVariableCS = null;


        try {
            // InternalCompleteOCL.g:6433:2: (iv_ruleCoIteratorVariableCS= ruleCoIteratorVariableCS EOF )
            // InternalCompleteOCL.g:6434:2: iv_ruleCoIteratorVariableCS= ruleCoIteratorVariableCS EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getCoIteratorVariableCSRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleCoIteratorVariableCS=ruleCoIteratorVariableCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleCoIteratorVariableCS;
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
    // $ANTLR end "entryRuleCoIteratorVariableCS"


    // $ANTLR start "ruleCoIteratorVariableCS"
    // InternalCompleteOCL.g:6441:1: ruleCoIteratorVariableCS returns [EObject current=null] : ( ( (lv_name_0_0= ruleUnrestrictedName ) ) (otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) ) )? ) ;
    public final EObject ruleCoIteratorVariableCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_name_0_0 = null;

        EObject lv_ownedType_2_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:6444:28: ( ( ( (lv_name_0_0= ruleUnrestrictedName ) ) (otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) ) )? ) )
            // InternalCompleteOCL.g:6445:1: ( ( (lv_name_0_0= ruleUnrestrictedName ) ) (otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) ) )? )
            {
            // InternalCompleteOCL.g:6445:1: ( ( (lv_name_0_0= ruleUnrestrictedName ) ) (otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) ) )? )
            // InternalCompleteOCL.g:6445:2: ( (lv_name_0_0= ruleUnrestrictedName ) ) (otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) ) )?
            {
            // InternalCompleteOCL.g:6445:2: ( (lv_name_0_0= ruleUnrestrictedName ) )
            // InternalCompleteOCL.g:6446:1: (lv_name_0_0= ruleUnrestrictedName )
            {
            // InternalCompleteOCL.g:6446:1: (lv_name_0_0= ruleUnrestrictedName )
            // InternalCompleteOCL.g:6447:3: lv_name_0_0= ruleUnrestrictedName
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getCoIteratorVariableCSAccess().getNameUnrestrictedNameParserRuleCall_0_0());

            }
            pushFollow(FollowSets000.FOLLOW_75);
            lv_name_0_0=ruleUnrestrictedName();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getCoIteratorVariableCSRule());
              	        }
                     		set(
                     			current,
                     			"name",
                      		lv_name_0_0,
                      		"org.eclipse.ocl.xtext.completeocl.CompleteOCL.UnrestrictedName");
              	        afterParserOrEnumRuleCall();

            }

            }


            }

            // InternalCompleteOCL.g:6463:2: (otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) ) )?
            int alt132=2;
            int LA132_0 = input.LA(1);

            if ( (LA132_0==24) ) {
                alt132=1;
            }
            switch (alt132) {
                case 1 :
                    // InternalCompleteOCL.g:6463:4: otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) )
                    {
                    otherlv_1=(Token)match(input,24,FollowSets000.FOLLOW_25); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_1, grammarAccess.getCoIteratorVariableCSAccess().getColonKeyword_1_0());

                    }
                    // InternalCompleteOCL.g:6467:1: ( (lv_ownedType_2_0= ruleTypeExpCS ) )
                    // InternalCompleteOCL.g:6468:1: (lv_ownedType_2_0= ruleTypeExpCS )
                    {
                    // InternalCompleteOCL.g:6468:1: (lv_ownedType_2_0= ruleTypeExpCS )
                    // InternalCompleteOCL.g:6469:3: lv_ownedType_2_0= ruleTypeExpCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getCoIteratorVariableCSAccess().getOwnedTypeTypeExpCSParserRuleCall_1_1_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    lv_ownedType_2_0=ruleTypeExpCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getCoIteratorVariableCSRule());
                      	        }
                             		set(
                             			current,
                             			"ownedType",
                              		lv_ownedType_2_0,
                              		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.TypeExpCS");
                      	        afterParserOrEnumRuleCall();

                    }

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
    // $ANTLR end "ruleCoIteratorVariableCS"


    // $ANTLR start "entryRuleIfExpCS"
    // InternalCompleteOCL.g:6493:1: entryRuleIfExpCS returns [EObject current=null] : iv_ruleIfExpCS= ruleIfExpCS EOF ;
    public final EObject entryRuleIfExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIfExpCS = null;


        try {
            // InternalCompleteOCL.g:6494:2: (iv_ruleIfExpCS= ruleIfExpCS EOF )
            // InternalCompleteOCL.g:6495:2: iv_ruleIfExpCS= ruleIfExpCS EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getIfExpCSRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleIfExpCS=ruleIfExpCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleIfExpCS;
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
    // $ANTLR end "entryRuleIfExpCS"


    // $ANTLR start "ruleIfExpCS"
    // InternalCompleteOCL.g:6502:1: ruleIfExpCS returns [EObject current=null] : (otherlv_0= 'if' ( ( (lv_ownedCondition_1_1= ruleExpCS | lv_ownedCondition_1_2= rulePatternExpCS ) ) ) otherlv_2= 'then' ( (lv_ownedThenExpression_3_0= ruleExpCS ) ) ( (lv_ownedIfThenExpressions_4_0= ruleElseIfThenExpCS ) )* otherlv_5= 'else' ( (lv_ownedElseExpression_6_0= ruleExpCS ) ) otherlv_7= 'endif' ) ;
    public final EObject ruleIfExpCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        EObject lv_ownedCondition_1_1 = null;

        EObject lv_ownedCondition_1_2 = null;

        EObject lv_ownedThenExpression_3_0 = null;

        EObject lv_ownedIfThenExpressions_4_0 = null;

        EObject lv_ownedElseExpression_6_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:6505:28: ( (otherlv_0= 'if' ( ( (lv_ownedCondition_1_1= ruleExpCS | lv_ownedCondition_1_2= rulePatternExpCS ) ) ) otherlv_2= 'then' ( (lv_ownedThenExpression_3_0= ruleExpCS ) ) ( (lv_ownedIfThenExpressions_4_0= ruleElseIfThenExpCS ) )* otherlv_5= 'else' ( (lv_ownedElseExpression_6_0= ruleExpCS ) ) otherlv_7= 'endif' ) )
            // InternalCompleteOCL.g:6506:1: (otherlv_0= 'if' ( ( (lv_ownedCondition_1_1= ruleExpCS | lv_ownedCondition_1_2= rulePatternExpCS ) ) ) otherlv_2= 'then' ( (lv_ownedThenExpression_3_0= ruleExpCS ) ) ( (lv_ownedIfThenExpressions_4_0= ruleElseIfThenExpCS ) )* otherlv_5= 'else' ( (lv_ownedElseExpression_6_0= ruleExpCS ) ) otherlv_7= 'endif' )
            {
            // InternalCompleteOCL.g:6506:1: (otherlv_0= 'if' ( ( (lv_ownedCondition_1_1= ruleExpCS | lv_ownedCondition_1_2= rulePatternExpCS ) ) ) otherlv_2= 'then' ( (lv_ownedThenExpression_3_0= ruleExpCS ) ) ( (lv_ownedIfThenExpressions_4_0= ruleElseIfThenExpCS ) )* otherlv_5= 'else' ( (lv_ownedElseExpression_6_0= ruleExpCS ) ) otherlv_7= 'endif' )
            // InternalCompleteOCL.g:6506:3: otherlv_0= 'if' ( ( (lv_ownedCondition_1_1= ruleExpCS | lv_ownedCondition_1_2= rulePatternExpCS ) ) ) otherlv_2= 'then' ( (lv_ownedThenExpression_3_0= ruleExpCS ) ) ( (lv_ownedIfThenExpressions_4_0= ruleElseIfThenExpCS ) )* otherlv_5= 'else' ( (lv_ownedElseExpression_6_0= ruleExpCS ) ) otherlv_7= 'endif'
            {
            otherlv_0=(Token)match(input,98,FollowSets000.FOLLOW_48); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getIfExpCSAccess().getIfKeyword_0());

            }
            // InternalCompleteOCL.g:6510:1: ( ( (lv_ownedCondition_1_1= ruleExpCS | lv_ownedCondition_1_2= rulePatternExpCS ) ) )
            // InternalCompleteOCL.g:6511:1: ( (lv_ownedCondition_1_1= ruleExpCS | lv_ownedCondition_1_2= rulePatternExpCS ) )
            {
            // InternalCompleteOCL.g:6511:1: ( (lv_ownedCondition_1_1= ruleExpCS | lv_ownedCondition_1_2= rulePatternExpCS ) )
            // InternalCompleteOCL.g:6512:1: (lv_ownedCondition_1_1= ruleExpCS | lv_ownedCondition_1_2= rulePatternExpCS )
            {
            // InternalCompleteOCL.g:6512:1: (lv_ownedCondition_1_1= ruleExpCS | lv_ownedCondition_1_2= rulePatternExpCS )
            int alt133=2;
            switch ( input.LA(1) ) {
            case RULE_INT:
            case RULE_SINGLE_QUOTED_STRING:
            case 22:
            case 43:
            case 44:
            case 45:
            case 46:
            case 47:
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
            case 56:
            case 74:
            case 75:
            case 77:
            case 78:
            case 79:
            case 80:
            case 81:
            case 86:
            case 88:
            case 89:
            case 90:
            case 91:
            case 98:
            case 103:
            case 104:
                {
                alt133=1;
                }
                break;
            case RULE_SIMPLE_ID:
                {
                int LA133_2 = input.LA(2);

                if ( ((LA133_2>=18 && LA133_2<=19)||LA133_2==22||LA133_2==30||(LA133_2>=40 && LA133_2<=41)||LA133_2==53||(LA133_2>=56 && LA133_2<=73)||LA133_2==76||LA133_2==82||(LA133_2>=92 && LA133_2<=93)||LA133_2==99) ) {
                    alt133=1;
                }
                else if ( (LA133_2==24) ) {
                    alt133=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return current;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 133, 2, input);

                    throw nvae;
                }
                }
                break;
            case RULE_ESCAPED_ID:
                {
                int LA133_3 = input.LA(2);

                if ( ((LA133_3>=18 && LA133_3<=19)||LA133_3==22||LA133_3==30||(LA133_3>=40 && LA133_3<=41)||LA133_3==53||(LA133_3>=56 && LA133_3<=73)||LA133_3==76||LA133_3==82||(LA133_3>=92 && LA133_3<=93)||LA133_3==99) ) {
                    alt133=1;
                }
                else if ( (LA133_3==24) ) {
                    alt133=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return current;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 133, 3, input);

                    throw nvae;
                }
                }
                break;
            case 31:
                {
                int LA133_4 = input.LA(2);

                if ( ((LA133_4>=18 && LA133_4<=19)||LA133_4==22||LA133_4==30||(LA133_4>=40 && LA133_4<=41)||LA133_4==53||(LA133_4>=56 && LA133_4<=73)||LA133_4==76||LA133_4==82||(LA133_4>=92 && LA133_4<=93)||LA133_4==99) ) {
                    alt133=1;
                }
                else if ( (LA133_4==24) ) {
                    alt133=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return current;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 133, 4, input);

                    throw nvae;
                }
                }
                break;
            case 32:
                {
                int LA133_5 = input.LA(2);

                if ( ((LA133_5>=18 && LA133_5<=19)||LA133_5==22||LA133_5==30||(LA133_5>=40 && LA133_5<=41)||LA133_5==53||(LA133_5>=56 && LA133_5<=73)||LA133_5==76||LA133_5==82||(LA133_5>=92 && LA133_5<=93)||LA133_5==99) ) {
                    alt133=1;
                }
                else if ( (LA133_5==24) ) {
                    alt133=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return current;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 133, 5, input);

                    throw nvae;
                }
                }
                break;
            case 33:
                {
                int LA133_6 = input.LA(2);

                if ( (LA133_6==24) ) {
                    alt133=2;
                }
                else if ( ((LA133_6>=18 && LA133_6<=19)||LA133_6==22||LA133_6==30||(LA133_6>=40 && LA133_6<=41)||LA133_6==53||(LA133_6>=56 && LA133_6<=73)||LA133_6==76||LA133_6==82||(LA133_6>=92 && LA133_6<=93)||LA133_6==99) ) {
                    alt133=1;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return current;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 133, 6, input);

                    throw nvae;
                }
                }
                break;
            case 24:
                {
                alt133=2;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 133, 0, input);

                throw nvae;
            }

            switch (alt133) {
                case 1 :
                    // InternalCompleteOCL.g:6513:3: lv_ownedCondition_1_1= ruleExpCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getIfExpCSAccess().getOwnedConditionExpCSParserRuleCall_1_0_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_76);
                    lv_ownedCondition_1_1=ruleExpCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getIfExpCSRule());
                      	        }
                             		set(
                             			current,
                             			"ownedCondition",
                              		lv_ownedCondition_1_1,
                              		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.ExpCS");
                      	        afterParserOrEnumRuleCall();

                    }

                    }
                    break;
                case 2 :
                    // InternalCompleteOCL.g:6528:8: lv_ownedCondition_1_2= rulePatternExpCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getIfExpCSAccess().getOwnedConditionPatternExpCSParserRuleCall_1_0_1());

                    }
                    pushFollow(FollowSets000.FOLLOW_76);
                    lv_ownedCondition_1_2=rulePatternExpCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getIfExpCSRule());
                      	        }
                             		set(
                             			current,
                             			"ownedCondition",
                              		lv_ownedCondition_1_2,
                              		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.PatternExpCS");
                      	        afterParserOrEnumRuleCall();

                    }

                    }
                    break;

            }


            }


            }

            otherlv_2=(Token)match(input,99,FollowSets000.FOLLOW_50); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_2, grammarAccess.getIfExpCSAccess().getThenKeyword_2());

            }
            // InternalCompleteOCL.g:6550:1: ( (lv_ownedThenExpression_3_0= ruleExpCS ) )
            // InternalCompleteOCL.g:6551:1: (lv_ownedThenExpression_3_0= ruleExpCS )
            {
            // InternalCompleteOCL.g:6551:1: (lv_ownedThenExpression_3_0= ruleExpCS )
            // InternalCompleteOCL.g:6552:3: lv_ownedThenExpression_3_0= ruleExpCS
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getIfExpCSAccess().getOwnedThenExpressionExpCSParserRuleCall_3_0());

            }
            pushFollow(FollowSets000.FOLLOW_77);
            lv_ownedThenExpression_3_0=ruleExpCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getIfExpCSRule());
              	        }
                     		set(
                     			current,
                     			"ownedThenExpression",
                      		lv_ownedThenExpression_3_0,
                      		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.ExpCS");
              	        afterParserOrEnumRuleCall();

            }

            }


            }

            // InternalCompleteOCL.g:6568:2: ( (lv_ownedIfThenExpressions_4_0= ruleElseIfThenExpCS ) )*
            loop134:
            do {
                int alt134=2;
                int LA134_0 = input.LA(1);

                if ( (LA134_0==102) ) {
                    alt134=1;
                }


                switch (alt134) {
            	case 1 :
            	    // InternalCompleteOCL.g:6569:1: (lv_ownedIfThenExpressions_4_0= ruleElseIfThenExpCS )
            	    {
            	    // InternalCompleteOCL.g:6569:1: (lv_ownedIfThenExpressions_4_0= ruleElseIfThenExpCS )
            	    // InternalCompleteOCL.g:6570:3: lv_ownedIfThenExpressions_4_0= ruleElseIfThenExpCS
            	    {
            	    if ( state.backtracking==0 ) {

            	      	        newCompositeNode(grammarAccess.getIfExpCSAccess().getOwnedIfThenExpressionsElseIfThenExpCSParserRuleCall_4_0());

            	    }
            	    pushFollow(FollowSets000.FOLLOW_77);
            	    lv_ownedIfThenExpressions_4_0=ruleElseIfThenExpCS();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getIfExpCSRule());
            	      	        }
            	             		add(
            	             			current,
            	             			"ownedIfThenExpressions",
            	              		lv_ownedIfThenExpressions_4_0,
            	              		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.ElseIfThenExpCS");
            	      	        afterParserOrEnumRuleCall();

            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop134;
                }
            } while (true);

            otherlv_5=(Token)match(input,100,FollowSets000.FOLLOW_50); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_5, grammarAccess.getIfExpCSAccess().getElseKeyword_5());

            }
            // InternalCompleteOCL.g:6590:1: ( (lv_ownedElseExpression_6_0= ruleExpCS ) )
            // InternalCompleteOCL.g:6591:1: (lv_ownedElseExpression_6_0= ruleExpCS )
            {
            // InternalCompleteOCL.g:6591:1: (lv_ownedElseExpression_6_0= ruleExpCS )
            // InternalCompleteOCL.g:6592:3: lv_ownedElseExpression_6_0= ruleExpCS
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getIfExpCSAccess().getOwnedElseExpressionExpCSParserRuleCall_6_0());

            }
            pushFollow(FollowSets000.FOLLOW_78);
            lv_ownedElseExpression_6_0=ruleExpCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getIfExpCSRule());
              	        }
                     		set(
                     			current,
                     			"ownedElseExpression",
                      		lv_ownedElseExpression_6_0,
                      		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.ExpCS");
              	        afterParserOrEnumRuleCall();

            }

            }


            }

            otherlv_7=(Token)match(input,101,FollowSets000.FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_7, grammarAccess.getIfExpCSAccess().getEndifKeyword_7());

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
    // $ANTLR end "ruleIfExpCS"


    // $ANTLR start "entryRuleElseIfThenExpCS"
    // InternalCompleteOCL.g:6620:1: entryRuleElseIfThenExpCS returns [EObject current=null] : iv_ruleElseIfThenExpCS= ruleElseIfThenExpCS EOF ;
    public final EObject entryRuleElseIfThenExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleElseIfThenExpCS = null;


        try {
            // InternalCompleteOCL.g:6621:2: (iv_ruleElseIfThenExpCS= ruleElseIfThenExpCS EOF )
            // InternalCompleteOCL.g:6622:2: iv_ruleElseIfThenExpCS= ruleElseIfThenExpCS EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getElseIfThenExpCSRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleElseIfThenExpCS=ruleElseIfThenExpCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleElseIfThenExpCS;
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
    // $ANTLR end "entryRuleElseIfThenExpCS"


    // $ANTLR start "ruleElseIfThenExpCS"
    // InternalCompleteOCL.g:6629:1: ruleElseIfThenExpCS returns [EObject current=null] : (otherlv_0= 'elseif' ( (lv_ownedCondition_1_0= ruleExpCS ) ) otherlv_2= 'then' ( (lv_ownedThenExpression_3_0= ruleExpCS ) ) ) ;
    public final EObject ruleElseIfThenExpCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        EObject lv_ownedCondition_1_0 = null;

        EObject lv_ownedThenExpression_3_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:6632:28: ( (otherlv_0= 'elseif' ( (lv_ownedCondition_1_0= ruleExpCS ) ) otherlv_2= 'then' ( (lv_ownedThenExpression_3_0= ruleExpCS ) ) ) )
            // InternalCompleteOCL.g:6633:1: (otherlv_0= 'elseif' ( (lv_ownedCondition_1_0= ruleExpCS ) ) otherlv_2= 'then' ( (lv_ownedThenExpression_3_0= ruleExpCS ) ) )
            {
            // InternalCompleteOCL.g:6633:1: (otherlv_0= 'elseif' ( (lv_ownedCondition_1_0= ruleExpCS ) ) otherlv_2= 'then' ( (lv_ownedThenExpression_3_0= ruleExpCS ) ) )
            // InternalCompleteOCL.g:6633:3: otherlv_0= 'elseif' ( (lv_ownedCondition_1_0= ruleExpCS ) ) otherlv_2= 'then' ( (lv_ownedThenExpression_3_0= ruleExpCS ) )
            {
            otherlv_0=(Token)match(input,102,FollowSets000.FOLLOW_50); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getElseIfThenExpCSAccess().getElseifKeyword_0());

            }
            // InternalCompleteOCL.g:6637:1: ( (lv_ownedCondition_1_0= ruleExpCS ) )
            // InternalCompleteOCL.g:6638:1: (lv_ownedCondition_1_0= ruleExpCS )
            {
            // InternalCompleteOCL.g:6638:1: (lv_ownedCondition_1_0= ruleExpCS )
            // InternalCompleteOCL.g:6639:3: lv_ownedCondition_1_0= ruleExpCS
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getElseIfThenExpCSAccess().getOwnedConditionExpCSParserRuleCall_1_0());

            }
            pushFollow(FollowSets000.FOLLOW_76);
            lv_ownedCondition_1_0=ruleExpCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getElseIfThenExpCSRule());
              	        }
                     		set(
                     			current,
                     			"ownedCondition",
                      		lv_ownedCondition_1_0,
                      		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.ExpCS");
              	        afterParserOrEnumRuleCall();

            }

            }


            }

            otherlv_2=(Token)match(input,99,FollowSets000.FOLLOW_50); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_2, grammarAccess.getElseIfThenExpCSAccess().getThenKeyword_2());

            }
            // InternalCompleteOCL.g:6659:1: ( (lv_ownedThenExpression_3_0= ruleExpCS ) )
            // InternalCompleteOCL.g:6660:1: (lv_ownedThenExpression_3_0= ruleExpCS )
            {
            // InternalCompleteOCL.g:6660:1: (lv_ownedThenExpression_3_0= ruleExpCS )
            // InternalCompleteOCL.g:6661:3: lv_ownedThenExpression_3_0= ruleExpCS
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getElseIfThenExpCSAccess().getOwnedThenExpressionExpCSParserRuleCall_3_0());

            }
            pushFollow(FollowSets000.FOLLOW_2);
            lv_ownedThenExpression_3_0=ruleExpCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getElseIfThenExpCSRule());
              	        }
                     		set(
                     			current,
                     			"ownedThenExpression",
                      		lv_ownedThenExpression_3_0,
                      		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.ExpCS");
              	        afterParserOrEnumRuleCall();

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
    // $ANTLR end "ruleElseIfThenExpCS"


    // $ANTLR start "entryRuleLetExpCS"
    // InternalCompleteOCL.g:6685:1: entryRuleLetExpCS returns [EObject current=null] : iv_ruleLetExpCS= ruleLetExpCS EOF ;
    public final EObject entryRuleLetExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLetExpCS = null;


        try {
            // InternalCompleteOCL.g:6686:2: (iv_ruleLetExpCS= ruleLetExpCS EOF )
            // InternalCompleteOCL.g:6687:2: iv_ruleLetExpCS= ruleLetExpCS EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getLetExpCSRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleLetExpCS=ruleLetExpCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleLetExpCS;
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
    // $ANTLR end "entryRuleLetExpCS"


    // $ANTLR start "ruleLetExpCS"
    // InternalCompleteOCL.g:6694:1: ruleLetExpCS returns [EObject current=null] : (otherlv_0= 'let' ( (lv_ownedVariables_1_0= ruleLetVariableCS ) ) (otherlv_2= ',' ( (lv_ownedVariables_3_0= ruleLetVariableCS ) ) )* otherlv_4= 'in' ( (lv_ownedInExpression_5_0= ruleExpCS ) ) ) ;
    public final EObject ruleLetExpCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_ownedVariables_1_0 = null;

        EObject lv_ownedVariables_3_0 = null;

        EObject lv_ownedInExpression_5_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:6697:28: ( (otherlv_0= 'let' ( (lv_ownedVariables_1_0= ruleLetVariableCS ) ) (otherlv_2= ',' ( (lv_ownedVariables_3_0= ruleLetVariableCS ) ) )* otherlv_4= 'in' ( (lv_ownedInExpression_5_0= ruleExpCS ) ) ) )
            // InternalCompleteOCL.g:6698:1: (otherlv_0= 'let' ( (lv_ownedVariables_1_0= ruleLetVariableCS ) ) (otherlv_2= ',' ( (lv_ownedVariables_3_0= ruleLetVariableCS ) ) )* otherlv_4= 'in' ( (lv_ownedInExpression_5_0= ruleExpCS ) ) )
            {
            // InternalCompleteOCL.g:6698:1: (otherlv_0= 'let' ( (lv_ownedVariables_1_0= ruleLetVariableCS ) ) (otherlv_2= ',' ( (lv_ownedVariables_3_0= ruleLetVariableCS ) ) )* otherlv_4= 'in' ( (lv_ownedInExpression_5_0= ruleExpCS ) ) )
            // InternalCompleteOCL.g:6698:3: otherlv_0= 'let' ( (lv_ownedVariables_1_0= ruleLetVariableCS ) ) (otherlv_2= ',' ( (lv_ownedVariables_3_0= ruleLetVariableCS ) ) )* otherlv_4= 'in' ( (lv_ownedInExpression_5_0= ruleExpCS ) )
            {
            otherlv_0=(Token)match(input,103,FollowSets000.FOLLOW_19); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getLetExpCSAccess().getLetKeyword_0());

            }
            // InternalCompleteOCL.g:6702:1: ( (lv_ownedVariables_1_0= ruleLetVariableCS ) )
            // InternalCompleteOCL.g:6703:1: (lv_ownedVariables_1_0= ruleLetVariableCS )
            {
            // InternalCompleteOCL.g:6703:1: (lv_ownedVariables_1_0= ruleLetVariableCS )
            // InternalCompleteOCL.g:6704:3: lv_ownedVariables_1_0= ruleLetVariableCS
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getLetExpCSAccess().getOwnedVariablesLetVariableCSParserRuleCall_1_0());

            }
            pushFollow(FollowSets000.FOLLOW_79);
            lv_ownedVariables_1_0=ruleLetVariableCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getLetExpCSRule());
              	        }
                     		add(
                     			current,
                     			"ownedVariables",
                      		lv_ownedVariables_1_0,
                      		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.LetVariableCS");
              	        afterParserOrEnumRuleCall();

            }

            }


            }

            // InternalCompleteOCL.g:6720:2: (otherlv_2= ',' ( (lv_ownedVariables_3_0= ruleLetVariableCS ) ) )*
            loop135:
            do {
                int alt135=2;
                int LA135_0 = input.LA(1);

                if ( (LA135_0==29) ) {
                    alt135=1;
                }


                switch (alt135) {
            	case 1 :
            	    // InternalCompleteOCL.g:6720:4: otherlv_2= ',' ( (lv_ownedVariables_3_0= ruleLetVariableCS ) )
            	    {
            	    otherlv_2=(Token)match(input,29,FollowSets000.FOLLOW_19); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_2, grammarAccess.getLetExpCSAccess().getCommaKeyword_2_0());

            	    }
            	    // InternalCompleteOCL.g:6724:1: ( (lv_ownedVariables_3_0= ruleLetVariableCS ) )
            	    // InternalCompleteOCL.g:6725:1: (lv_ownedVariables_3_0= ruleLetVariableCS )
            	    {
            	    // InternalCompleteOCL.g:6725:1: (lv_ownedVariables_3_0= ruleLetVariableCS )
            	    // InternalCompleteOCL.g:6726:3: lv_ownedVariables_3_0= ruleLetVariableCS
            	    {
            	    if ( state.backtracking==0 ) {

            	      	        newCompositeNode(grammarAccess.getLetExpCSAccess().getOwnedVariablesLetVariableCSParserRuleCall_2_1_0());

            	    }
            	    pushFollow(FollowSets000.FOLLOW_79);
            	    lv_ownedVariables_3_0=ruleLetVariableCS();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getLetExpCSRule());
            	      	        }
            	             		add(
            	             			current,
            	             			"ownedVariables",
            	              		lv_ownedVariables_3_0,
            	              		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.LetVariableCS");
            	      	        afterParserOrEnumRuleCall();

            	    }

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop135;
                }
            } while (true);

            otherlv_4=(Token)match(input,95,FollowSets000.FOLLOW_50); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_4, grammarAccess.getLetExpCSAccess().getInKeyword_3());

            }
            // InternalCompleteOCL.g:6746:1: ( (lv_ownedInExpression_5_0= ruleExpCS ) )
            // InternalCompleteOCL.g:6747:1: (lv_ownedInExpression_5_0= ruleExpCS )
            {
            // InternalCompleteOCL.g:6747:1: (lv_ownedInExpression_5_0= ruleExpCS )
            // InternalCompleteOCL.g:6748:3: lv_ownedInExpression_5_0= ruleExpCS
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getLetExpCSAccess().getOwnedInExpressionExpCSParserRuleCall_4_0());

            }
            pushFollow(FollowSets000.FOLLOW_2);
            lv_ownedInExpression_5_0=ruleExpCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getLetExpCSRule());
              	        }
                     		set(
                     			current,
                     			"ownedInExpression",
                      		lv_ownedInExpression_5_0,
                      		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.ExpCS");
              	        afterParserOrEnumRuleCall();

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
    // $ANTLR end "ruleLetExpCS"


    // $ANTLR start "entryRuleLetVariableCS"
    // InternalCompleteOCL.g:6772:1: entryRuleLetVariableCS returns [EObject current=null] : iv_ruleLetVariableCS= ruleLetVariableCS EOF ;
    public final EObject entryRuleLetVariableCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLetVariableCS = null;


        try {
            // InternalCompleteOCL.g:6773:2: (iv_ruleLetVariableCS= ruleLetVariableCS EOF )
            // InternalCompleteOCL.g:6774:2: iv_ruleLetVariableCS= ruleLetVariableCS EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getLetVariableCSRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleLetVariableCS=ruleLetVariableCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleLetVariableCS;
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
    // $ANTLR end "entryRuleLetVariableCS"


    // $ANTLR start "ruleLetVariableCS"
    // InternalCompleteOCL.g:6781:1: ruleLetVariableCS returns [EObject current=null] : ( ( (lv_name_0_0= ruleUnrestrictedName ) ) ( (lv_ownedRoundBracketedClause_1_0= ruleRoundBracketedClauseCS ) )? (otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) )? otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) ) ;
    public final EObject ruleLetVariableCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        Token otherlv_4=null;
        AntlrDatatypeRuleToken lv_name_0_0 = null;

        EObject lv_ownedRoundBracketedClause_1_0 = null;

        EObject lv_ownedType_3_0 = null;

        EObject lv_ownedInitExpression_5_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:6784:28: ( ( ( (lv_name_0_0= ruleUnrestrictedName ) ) ( (lv_ownedRoundBracketedClause_1_0= ruleRoundBracketedClauseCS ) )? (otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) )? otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) ) )
            // InternalCompleteOCL.g:6785:1: ( ( (lv_name_0_0= ruleUnrestrictedName ) ) ( (lv_ownedRoundBracketedClause_1_0= ruleRoundBracketedClauseCS ) )? (otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) )? otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )
            {
            // InternalCompleteOCL.g:6785:1: ( ( (lv_name_0_0= ruleUnrestrictedName ) ) ( (lv_ownedRoundBracketedClause_1_0= ruleRoundBracketedClauseCS ) )? (otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) )? otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )
            // InternalCompleteOCL.g:6785:2: ( (lv_name_0_0= ruleUnrestrictedName ) ) ( (lv_ownedRoundBracketedClause_1_0= ruleRoundBracketedClauseCS ) )? (otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) )? otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) )
            {
            // InternalCompleteOCL.g:6785:2: ( (lv_name_0_0= ruleUnrestrictedName ) )
            // InternalCompleteOCL.g:6786:1: (lv_name_0_0= ruleUnrestrictedName )
            {
            // InternalCompleteOCL.g:6786:1: (lv_name_0_0= ruleUnrestrictedName )
            // InternalCompleteOCL.g:6787:3: lv_name_0_0= ruleUnrestrictedName
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getLetVariableCSAccess().getNameUnrestrictedNameParserRuleCall_0_0());

            }
            pushFollow(FollowSets000.FOLLOW_80);
            lv_name_0_0=ruleUnrestrictedName();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getLetVariableCSRule());
              	        }
                     		set(
                     			current,
                     			"name",
                      		lv_name_0_0,
                      		"org.eclipse.ocl.xtext.completeocl.CompleteOCL.UnrestrictedName");
              	        afterParserOrEnumRuleCall();

            }

            }


            }

            // InternalCompleteOCL.g:6803:2: ( (lv_ownedRoundBracketedClause_1_0= ruleRoundBracketedClauseCS ) )?
            int alt136=2;
            int LA136_0 = input.LA(1);

            if ( (LA136_0==22) ) {
                alt136=1;
            }
            switch (alt136) {
                case 1 :
                    // InternalCompleteOCL.g:6804:1: (lv_ownedRoundBracketedClause_1_0= ruleRoundBracketedClauseCS )
                    {
                    // InternalCompleteOCL.g:6804:1: (lv_ownedRoundBracketedClause_1_0= ruleRoundBracketedClauseCS )
                    // InternalCompleteOCL.g:6805:3: lv_ownedRoundBracketedClause_1_0= ruleRoundBracketedClauseCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getLetVariableCSAccess().getOwnedRoundBracketedClauseRoundBracketedClauseCSParserRuleCall_1_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_56);
                    lv_ownedRoundBracketedClause_1_0=ruleRoundBracketedClauseCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getLetVariableCSRule());
                      	        }
                             		set(
                             			current,
                             			"ownedRoundBracketedClause",
                              		lv_ownedRoundBracketedClause_1_0,
                              		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.RoundBracketedClauseCS");
                      	        afterParserOrEnumRuleCall();

                    }

                    }


                    }
                    break;

            }

            // InternalCompleteOCL.g:6821:3: (otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) )?
            int alt137=2;
            int LA137_0 = input.LA(1);

            if ( (LA137_0==24) ) {
                alt137=1;
            }
            switch (alt137) {
                case 1 :
                    // InternalCompleteOCL.g:6821:5: otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) )
                    {
                    otherlv_2=(Token)match(input,24,FollowSets000.FOLLOW_25); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_2, grammarAccess.getLetVariableCSAccess().getColonKeyword_2_0());

                    }
                    // InternalCompleteOCL.g:6825:1: ( (lv_ownedType_3_0= ruleTypeExpCS ) )
                    // InternalCompleteOCL.g:6826:1: (lv_ownedType_3_0= ruleTypeExpCS )
                    {
                    // InternalCompleteOCL.g:6826:1: (lv_ownedType_3_0= ruleTypeExpCS )
                    // InternalCompleteOCL.g:6827:3: lv_ownedType_3_0= ruleTypeExpCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getLetVariableCSAccess().getOwnedTypeTypeExpCSParserRuleCall_2_1_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_24);
                    lv_ownedType_3_0=ruleTypeExpCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getLetVariableCSRule());
                      	        }
                             		set(
                             			current,
                             			"ownedType",
                              		lv_ownedType_3_0,
                              		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.TypeExpCS");
                      	        afterParserOrEnumRuleCall();

                    }

                    }


                    }


                    }
                    break;

            }

            otherlv_4=(Token)match(input,30,FollowSets000.FOLLOW_50); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_4, grammarAccess.getLetVariableCSAccess().getEqualsSignKeyword_3());

            }
            // InternalCompleteOCL.g:6847:1: ( (lv_ownedInitExpression_5_0= ruleExpCS ) )
            // InternalCompleteOCL.g:6848:1: (lv_ownedInitExpression_5_0= ruleExpCS )
            {
            // InternalCompleteOCL.g:6848:1: (lv_ownedInitExpression_5_0= ruleExpCS )
            // InternalCompleteOCL.g:6849:3: lv_ownedInitExpression_5_0= ruleExpCS
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getLetVariableCSAccess().getOwnedInitExpressionExpCSParserRuleCall_4_0());

            }
            pushFollow(FollowSets000.FOLLOW_2);
            lv_ownedInitExpression_5_0=ruleExpCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getLetVariableCSRule());
              	        }
                     		set(
                     			current,
                     			"ownedInitExpression",
                      		lv_ownedInitExpression_5_0,
                      		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.ExpCS");
              	        afterParserOrEnumRuleCall();

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
    // $ANTLR end "ruleLetVariableCS"


    // $ANTLR start "entryRuleNestedExpCS"
    // InternalCompleteOCL.g:6873:1: entryRuleNestedExpCS returns [EObject current=null] : iv_ruleNestedExpCS= ruleNestedExpCS EOF ;
    public final EObject entryRuleNestedExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNestedExpCS = null;


        try {
            // InternalCompleteOCL.g:6874:2: (iv_ruleNestedExpCS= ruleNestedExpCS EOF )
            // InternalCompleteOCL.g:6875:2: iv_ruleNestedExpCS= ruleNestedExpCS EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getNestedExpCSRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleNestedExpCS=ruleNestedExpCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleNestedExpCS;
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
    // $ANTLR end "entryRuleNestedExpCS"


    // $ANTLR start "ruleNestedExpCS"
    // InternalCompleteOCL.g:6882:1: ruleNestedExpCS returns [EObject current=null] : (otherlv_0= '(' ( (lv_ownedExpression_1_0= ruleExpCS ) ) otherlv_2= ')' ) ;
    public final EObject ruleNestedExpCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        EObject lv_ownedExpression_1_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:6885:28: ( (otherlv_0= '(' ( (lv_ownedExpression_1_0= ruleExpCS ) ) otherlv_2= ')' ) )
            // InternalCompleteOCL.g:6886:1: (otherlv_0= '(' ( (lv_ownedExpression_1_0= ruleExpCS ) ) otherlv_2= ')' )
            {
            // InternalCompleteOCL.g:6886:1: (otherlv_0= '(' ( (lv_ownedExpression_1_0= ruleExpCS ) ) otherlv_2= ')' )
            // InternalCompleteOCL.g:6886:3: otherlv_0= '(' ( (lv_ownedExpression_1_0= ruleExpCS ) ) otherlv_2= ')'
            {
            otherlv_0=(Token)match(input,22,FollowSets000.FOLLOW_50); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getNestedExpCSAccess().getLeftParenthesisKeyword_0());

            }
            // InternalCompleteOCL.g:6890:1: ( (lv_ownedExpression_1_0= ruleExpCS ) )
            // InternalCompleteOCL.g:6891:1: (lv_ownedExpression_1_0= ruleExpCS )
            {
            // InternalCompleteOCL.g:6891:1: (lv_ownedExpression_1_0= ruleExpCS )
            // InternalCompleteOCL.g:6892:3: lv_ownedExpression_1_0= ruleExpCS
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getNestedExpCSAccess().getOwnedExpressionExpCSParserRuleCall_1_0());

            }
            pushFollow(FollowSets000.FOLLOW_13);
            lv_ownedExpression_1_0=ruleExpCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getNestedExpCSRule());
              	        }
                     		set(
                     			current,
                     			"ownedExpression",
                      		lv_ownedExpression_1_0,
                      		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.ExpCS");
              	        afterParserOrEnumRuleCall();

            }

            }


            }

            otherlv_2=(Token)match(input,23,FollowSets000.FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_2, grammarAccess.getNestedExpCSAccess().getRightParenthesisKeyword_2());

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
    // $ANTLR end "ruleNestedExpCS"


    // $ANTLR start "entryRuleSelfExpCS"
    // InternalCompleteOCL.g:6920:1: entryRuleSelfExpCS returns [EObject current=null] : iv_ruleSelfExpCS= ruleSelfExpCS EOF ;
    public final EObject entryRuleSelfExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSelfExpCS = null;


        try {
            // InternalCompleteOCL.g:6921:2: (iv_ruleSelfExpCS= ruleSelfExpCS EOF )
            // InternalCompleteOCL.g:6922:2: iv_ruleSelfExpCS= ruleSelfExpCS EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getSelfExpCSRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleSelfExpCS=ruleSelfExpCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleSelfExpCS;
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
    // $ANTLR end "entryRuleSelfExpCS"


    // $ANTLR start "ruleSelfExpCS"
    // InternalCompleteOCL.g:6929:1: ruleSelfExpCS returns [EObject current=null] : ( () otherlv_1= 'self' ) ;
    public final EObject ruleSelfExpCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;

         enterRule();

        try {
            // InternalCompleteOCL.g:6932:28: ( ( () otherlv_1= 'self' ) )
            // InternalCompleteOCL.g:6933:1: ( () otherlv_1= 'self' )
            {
            // InternalCompleteOCL.g:6933:1: ( () otherlv_1= 'self' )
            // InternalCompleteOCL.g:6933:2: () otherlv_1= 'self'
            {
            // InternalCompleteOCL.g:6933:2: ()
            // InternalCompleteOCL.g:6934:2:
            {
            if ( state.backtracking==0 ) {

              	  /* */

            }
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getSelfExpCSAccess().getSelfExpCSAction_0(),
                          current);

            }

            }

            otherlv_1=(Token)match(input,104,FollowSets000.FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getSelfExpCSAccess().getSelfKeyword_1());

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
    // $ANTLR end "ruleSelfExpCS"


    // $ANTLR start "entryRuleCommentCS"
    // InternalCompleteOCL.g:6954:1: entryRuleCommentCS returns [EObject current=null] : iv_ruleCommentCS= ruleCommentCS EOF ;
    public final EObject entryRuleCommentCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCommentCS = null;


        try {
            // InternalCompleteOCL.g:6955:2: (iv_ruleCommentCS= ruleCommentCS EOF )
            // InternalCompleteOCL.g:6956:2: iv_ruleCommentCS= ruleCommentCS EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getCommentCSRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleCommentCS=ruleCommentCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleCommentCS;
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
    // $ANTLR end "entryRuleCommentCS"


    // $ANTLR start "ruleCommentCS"
    // InternalCompleteOCL.g:6963:1: ruleCommentCS returns [EObject current=null] : ( (lv_value_0_0= RULE_ML_DOCUMENTATION ) ) ;
    public final EObject ruleCommentCS() throws RecognitionException {
        EObject current = null;

        Token lv_value_0_0=null;

         enterRule();

        try {
            // InternalCompleteOCL.g:6966:28: ( ( (lv_value_0_0= RULE_ML_DOCUMENTATION ) ) )
            // InternalCompleteOCL.g:6967:1: ( (lv_value_0_0= RULE_ML_DOCUMENTATION ) )
            {
            // InternalCompleteOCL.g:6967:1: ( (lv_value_0_0= RULE_ML_DOCUMENTATION ) )
            // InternalCompleteOCL.g:6968:1: (lv_value_0_0= RULE_ML_DOCUMENTATION )
            {
            // InternalCompleteOCL.g:6968:1: (lv_value_0_0= RULE_ML_DOCUMENTATION )
            // InternalCompleteOCL.g:6969:3: lv_value_0_0= RULE_ML_DOCUMENTATION
            {
            lv_value_0_0=(Token)match(input,RULE_ML_DOCUMENTATION,FollowSets000.FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(lv_value_0_0, grammarAccess.getCommentCSAccess().getValueML_DOCUMENTATIONTerminalRuleCall_0());

            }
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElement(grammarAccess.getCommentCSRule());
              	        }
                     		setWithLastConsumed(
                     			current,
                     			"value",
                      		lv_value_0_0,
                      		"org.eclipse.ocl.xtext.base.Base.ML_DOCUMENTATION");

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
    // $ANTLR end "ruleCommentCS"


    // $ANTLR start "entryRuleMultiplicityBoundsCS"
    // InternalCompleteOCL.g:6993:1: entryRuleMultiplicityBoundsCS returns [EObject current=null] : iv_ruleMultiplicityBoundsCS= ruleMultiplicityBoundsCS EOF ;
    public final EObject entryRuleMultiplicityBoundsCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMultiplicityBoundsCS = null;


        try {
            // InternalCompleteOCL.g:6994:2: (iv_ruleMultiplicityBoundsCS= ruleMultiplicityBoundsCS EOF )
            // InternalCompleteOCL.g:6995:2: iv_ruleMultiplicityBoundsCS= ruleMultiplicityBoundsCS EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getMultiplicityBoundsCSRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleMultiplicityBoundsCS=ruleMultiplicityBoundsCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleMultiplicityBoundsCS;
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
    // $ANTLR end "entryRuleMultiplicityBoundsCS"


    // $ANTLR start "ruleMultiplicityBoundsCS"
    // InternalCompleteOCL.g:7002:1: ruleMultiplicityBoundsCS returns [EObject current=null] : ( ( (lv_lowerBound_0_0= ruleLOWER ) ) (otherlv_1= '..' ( (lv_upperBound_2_0= ruleUPPER ) ) )? ) ;
    public final EObject ruleMultiplicityBoundsCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_lowerBound_0_0 = null;

        AntlrDatatypeRuleToken lv_upperBound_2_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:7005:28: ( ( ( (lv_lowerBound_0_0= ruleLOWER ) ) (otherlv_1= '..' ( (lv_upperBound_2_0= ruleUPPER ) ) )? ) )
            // InternalCompleteOCL.g:7006:1: ( ( (lv_lowerBound_0_0= ruleLOWER ) ) (otherlv_1= '..' ( (lv_upperBound_2_0= ruleUPPER ) ) )? )
            {
            // InternalCompleteOCL.g:7006:1: ( ( (lv_lowerBound_0_0= ruleLOWER ) ) (otherlv_1= '..' ( (lv_upperBound_2_0= ruleUPPER ) ) )? )
            // InternalCompleteOCL.g:7006:2: ( (lv_lowerBound_0_0= ruleLOWER ) ) (otherlv_1= '..' ( (lv_upperBound_2_0= ruleUPPER ) ) )?
            {
            // InternalCompleteOCL.g:7006:2: ( (lv_lowerBound_0_0= ruleLOWER ) )
            // InternalCompleteOCL.g:7007:1: (lv_lowerBound_0_0= ruleLOWER )
            {
            // InternalCompleteOCL.g:7007:1: (lv_lowerBound_0_0= ruleLOWER )
            // InternalCompleteOCL.g:7008:3: lv_lowerBound_0_0= ruleLOWER
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getMultiplicityBoundsCSAccess().getLowerBoundLOWERParserRuleCall_0_0());

            }
            pushFollow(FollowSets000.FOLLOW_49);
            lv_lowerBound_0_0=ruleLOWER();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getMultiplicityBoundsCSRule());
              	        }
                     		set(
                     			current,
                     			"lowerBound",
                      		lv_lowerBound_0_0,
                      		"org.eclipse.ocl.xtext.base.Base.LOWER");
              	        afterParserOrEnumRuleCall();

            }

            }


            }

            // InternalCompleteOCL.g:7024:2: (otherlv_1= '..' ( (lv_upperBound_2_0= ruleUPPER ) ) )?
            int alt138=2;
            int LA138_0 = input.LA(1);

            if ( (LA138_0==84) ) {
                alt138=1;
            }
            switch (alt138) {
                case 1 :
                    // InternalCompleteOCL.g:7024:4: otherlv_1= '..' ( (lv_upperBound_2_0= ruleUPPER ) )
                    {
                    otherlv_1=(Token)match(input,84,FollowSets000.FOLLOW_81); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_1, grammarAccess.getMultiplicityBoundsCSAccess().getFullStopFullStopKeyword_1_0());

                    }
                    // InternalCompleteOCL.g:7028:1: ( (lv_upperBound_2_0= ruleUPPER ) )
                    // InternalCompleteOCL.g:7029:1: (lv_upperBound_2_0= ruleUPPER )
                    {
                    // InternalCompleteOCL.g:7029:1: (lv_upperBound_2_0= ruleUPPER )
                    // InternalCompleteOCL.g:7030:3: lv_upperBound_2_0= ruleUPPER
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getMultiplicityBoundsCSAccess().getUpperBoundUPPERParserRuleCall_1_1_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    lv_upperBound_2_0=ruleUPPER();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getMultiplicityBoundsCSRule());
                      	        }
                             		set(
                             			current,
                             			"upperBound",
                              		lv_upperBound_2_0,
                              		"org.eclipse.ocl.xtext.base.Base.UPPER");
                      	        afterParserOrEnumRuleCall();

                    }

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
    // $ANTLR end "ruleMultiplicityBoundsCS"


    // $ANTLR start "entryRuleMultiplicityCS"
    // InternalCompleteOCL.g:7054:1: entryRuleMultiplicityCS returns [EObject current=null] : iv_ruleMultiplicityCS= ruleMultiplicityCS EOF ;
    public final EObject entryRuleMultiplicityCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMultiplicityCS = null;


        try {
            // InternalCompleteOCL.g:7055:2: (iv_ruleMultiplicityCS= ruleMultiplicityCS EOF )
            // InternalCompleteOCL.g:7056:2: iv_ruleMultiplicityCS= ruleMultiplicityCS EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getMultiplicityCSRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleMultiplicityCS=ruleMultiplicityCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleMultiplicityCS;
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
    // $ANTLR end "entryRuleMultiplicityCS"


    // $ANTLR start "ruleMultiplicityCS"
    // InternalCompleteOCL.g:7063:1: ruleMultiplicityCS returns [EObject current=null] : (otherlv_0= '[' (this_MultiplicityBoundsCS_1= ruleMultiplicityBoundsCS | this_MultiplicityStringCS_2= ruleMultiplicityStringCS ) (otherlv_3= '|?' | ( (lv_isNullFree_4_0= '|1' ) ) )? otherlv_5= ']' ) ;
    public final EObject ruleMultiplicityCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_3=null;
        Token lv_isNullFree_4_0=null;
        Token otherlv_5=null;
        EObject this_MultiplicityBoundsCS_1 = null;

        EObject this_MultiplicityStringCS_2 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:7066:28: ( (otherlv_0= '[' (this_MultiplicityBoundsCS_1= ruleMultiplicityBoundsCS | this_MultiplicityStringCS_2= ruleMultiplicityStringCS ) (otherlv_3= '|?' | ( (lv_isNullFree_4_0= '|1' ) ) )? otherlv_5= ']' ) )
            // InternalCompleteOCL.g:7067:1: (otherlv_0= '[' (this_MultiplicityBoundsCS_1= ruleMultiplicityBoundsCS | this_MultiplicityStringCS_2= ruleMultiplicityStringCS ) (otherlv_3= '|?' | ( (lv_isNullFree_4_0= '|1' ) ) )? otherlv_5= ']' )
            {
            // InternalCompleteOCL.g:7067:1: (otherlv_0= '[' (this_MultiplicityBoundsCS_1= ruleMultiplicityBoundsCS | this_MultiplicityStringCS_2= ruleMultiplicityStringCS ) (otherlv_3= '|?' | ( (lv_isNullFree_4_0= '|1' ) ) )? otherlv_5= ']' )
            // InternalCompleteOCL.g:7067:3: otherlv_0= '[' (this_MultiplicityBoundsCS_1= ruleMultiplicityBoundsCS | this_MultiplicityStringCS_2= ruleMultiplicityStringCS ) (otherlv_3= '|?' | ( (lv_isNullFree_4_0= '|1' ) ) )? otherlv_5= ']'
            {
            otherlv_0=(Token)match(input,93,FollowSets000.FOLLOW_82); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getMultiplicityCSAccess().getLeftSquareBracketKeyword_0());

            }
            // InternalCompleteOCL.g:7071:1: (this_MultiplicityBoundsCS_1= ruleMultiplicityBoundsCS | this_MultiplicityStringCS_2= ruleMultiplicityStringCS )
            int alt139=2;
            int LA139_0 = input.LA(1);

            if ( (LA139_0==RULE_INT) ) {
                alt139=1;
            }
            else if ( (LA139_0==42||LA139_0==56||LA139_0==58) ) {
                alt139=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 139, 0, input);

                throw nvae;
            }
            switch (alt139) {
                case 1 :
                    // InternalCompleteOCL.g:7072:2: this_MultiplicityBoundsCS_1= ruleMultiplicityBoundsCS
                    {
                    if ( state.backtracking==0 ) {

                      	  /* */

                    }
                    if ( state.backtracking==0 ) {

                              newCompositeNode(grammarAccess.getMultiplicityCSAccess().getMultiplicityBoundsCSParserRuleCall_1_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_83);
                    this_MultiplicityBoundsCS_1=ruleMultiplicityBoundsCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_MultiplicityBoundsCS_1;
                              afterParserOrEnumRuleCall();

                    }

                    }
                    break;
                case 2 :
                    // InternalCompleteOCL.g:7085:2: this_MultiplicityStringCS_2= ruleMultiplicityStringCS
                    {
                    if ( state.backtracking==0 ) {

                      	  /* */

                    }
                    if ( state.backtracking==0 ) {

                              newCompositeNode(grammarAccess.getMultiplicityCSAccess().getMultiplicityStringCSParserRuleCall_1_1());

                    }
                    pushFollow(FollowSets000.FOLLOW_83);
                    this_MultiplicityStringCS_2=ruleMultiplicityStringCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_MultiplicityStringCS_2;
                              afterParserOrEnumRuleCall();

                    }

                    }
                    break;

            }

            // InternalCompleteOCL.g:7096:2: (otherlv_3= '|?' | ( (lv_isNullFree_4_0= '|1' ) ) )?
            int alt140=3;
            int LA140_0 = input.LA(1);

            if ( (LA140_0==105) ) {
                alt140=1;
            }
            else if ( (LA140_0==106) ) {
                alt140=2;
            }
            switch (alt140) {
                case 1 :
                    // InternalCompleteOCL.g:7096:4: otherlv_3= '|?'
                    {
                    otherlv_3=(Token)match(input,105,FollowSets000.FOLLOW_84); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_3, grammarAccess.getMultiplicityCSAccess().getVerticalLineQuestionMarkKeyword_2_0());

                    }

                    }
                    break;
                case 2 :
                    // InternalCompleteOCL.g:7101:6: ( (lv_isNullFree_4_0= '|1' ) )
                    {
                    // InternalCompleteOCL.g:7101:6: ( (lv_isNullFree_4_0= '|1' ) )
                    // InternalCompleteOCL.g:7102:1: (lv_isNullFree_4_0= '|1' )
                    {
                    // InternalCompleteOCL.g:7102:1: (lv_isNullFree_4_0= '|1' )
                    // InternalCompleteOCL.g:7103:3: lv_isNullFree_4_0= '|1'
                    {
                    lv_isNullFree_4_0=(Token)match(input,106,FollowSets000.FOLLOW_84); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_isNullFree_4_0, grammarAccess.getMultiplicityCSAccess().getIsNullFree1Keyword_2_1_0());

                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getMultiplicityCSRule());
                      	        }
                             		setWithLastConsumed(current, "isNullFree", true, "|1");

                    }

                    }


                    }


                    }
                    break;

            }

            otherlv_5=(Token)match(input,94,FollowSets000.FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_5, grammarAccess.getMultiplicityCSAccess().getRightSquareBracketKeyword_3());

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
    // $ANTLR end "ruleMultiplicityCS"


    // $ANTLR start "entryRuleMultiplicityStringCS"
    // InternalCompleteOCL.g:7128:1: entryRuleMultiplicityStringCS returns [EObject current=null] : iv_ruleMultiplicityStringCS= ruleMultiplicityStringCS EOF ;
    public final EObject entryRuleMultiplicityStringCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMultiplicityStringCS = null;


        try {
            // InternalCompleteOCL.g:7129:2: (iv_ruleMultiplicityStringCS= ruleMultiplicityStringCS EOF )
            // InternalCompleteOCL.g:7130:2: iv_ruleMultiplicityStringCS= ruleMultiplicityStringCS EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getMultiplicityStringCSRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleMultiplicityStringCS=ruleMultiplicityStringCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleMultiplicityStringCS;
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
    // $ANTLR end "entryRuleMultiplicityStringCS"


    // $ANTLR start "ruleMultiplicityStringCS"
    // InternalCompleteOCL.g:7137:1: ruleMultiplicityStringCS returns [EObject current=null] : ( ( (lv_stringBounds_0_1= '*' | lv_stringBounds_0_2= '+' | lv_stringBounds_0_3= '?' ) ) ) ;
    public final EObject ruleMultiplicityStringCS() throws RecognitionException {
        EObject current = null;

        Token lv_stringBounds_0_1=null;
        Token lv_stringBounds_0_2=null;
        Token lv_stringBounds_0_3=null;

         enterRule();

        try {
            // InternalCompleteOCL.g:7140:28: ( ( ( (lv_stringBounds_0_1= '*' | lv_stringBounds_0_2= '+' | lv_stringBounds_0_3= '?' ) ) ) )
            // InternalCompleteOCL.g:7141:1: ( ( (lv_stringBounds_0_1= '*' | lv_stringBounds_0_2= '+' | lv_stringBounds_0_3= '?' ) ) )
            {
            // InternalCompleteOCL.g:7141:1: ( ( (lv_stringBounds_0_1= '*' | lv_stringBounds_0_2= '+' | lv_stringBounds_0_3= '?' ) ) )
            // InternalCompleteOCL.g:7142:1: ( (lv_stringBounds_0_1= '*' | lv_stringBounds_0_2= '+' | lv_stringBounds_0_3= '?' ) )
            {
            // InternalCompleteOCL.g:7142:1: ( (lv_stringBounds_0_1= '*' | lv_stringBounds_0_2= '+' | lv_stringBounds_0_3= '?' ) )
            // InternalCompleteOCL.g:7143:1: (lv_stringBounds_0_1= '*' | lv_stringBounds_0_2= '+' | lv_stringBounds_0_3= '?' )
            {
            // InternalCompleteOCL.g:7143:1: (lv_stringBounds_0_1= '*' | lv_stringBounds_0_2= '+' | lv_stringBounds_0_3= '?' )
            int alt141=3;
            switch ( input.LA(1) ) {
            case 56:
                {
                alt141=1;
                }
                break;
            case 58:
                {
                alt141=2;
                }
                break;
            case 42:
                {
                alt141=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 141, 0, input);

                throw nvae;
            }

            switch (alt141) {
                case 1 :
                    // InternalCompleteOCL.g:7144:3: lv_stringBounds_0_1= '*'
                    {
                    lv_stringBounds_0_1=(Token)match(input,56,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_stringBounds_0_1, grammarAccess.getMultiplicityStringCSAccess().getStringBoundsAsteriskKeyword_0_0());

                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getMultiplicityStringCSRule());
                      	        }
                             		setWithLastConsumed(current, "stringBounds", lv_stringBounds_0_1, null);

                    }

                    }
                    break;
                case 2 :
                    // InternalCompleteOCL.g:7156:8: lv_stringBounds_0_2= '+'
                    {
                    lv_stringBounds_0_2=(Token)match(input,58,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_stringBounds_0_2, grammarAccess.getMultiplicityStringCSAccess().getStringBoundsPlusSignKeyword_0_1());

                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getMultiplicityStringCSRule());
                      	        }
                             		setWithLastConsumed(current, "stringBounds", lv_stringBounds_0_2, null);

                    }

                    }
                    break;
                case 3 :
                    // InternalCompleteOCL.g:7168:8: lv_stringBounds_0_3= '?'
                    {
                    lv_stringBounds_0_3=(Token)match(input,42,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_stringBounds_0_3, grammarAccess.getMultiplicityStringCSAccess().getStringBoundsQuestionMarkKeyword_0_2());

                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getMultiplicityStringCSRule());
                      	        }
                             		setWithLastConsumed(current, "stringBounds", lv_stringBounds_0_3, null);

                    }

                    }
                    break;

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
    // $ANTLR end "ruleMultiplicityStringCS"


    // $ANTLR start "entryRulePathNameCS"
    // InternalCompleteOCL.g:7191:1: entryRulePathNameCS returns [EObject current=null] : iv_rulePathNameCS= rulePathNameCS EOF ;
    public final EObject entryRulePathNameCS() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePathNameCS = null;


        try {
            // InternalCompleteOCL.g:7192:2: (iv_rulePathNameCS= rulePathNameCS EOF )
            // InternalCompleteOCL.g:7193:2: iv_rulePathNameCS= rulePathNameCS EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getPathNameCSRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_rulePathNameCS=rulePathNameCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_rulePathNameCS;
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
    // $ANTLR end "entryRulePathNameCS"


    // $ANTLR start "rulePathNameCS"
    // InternalCompleteOCL.g:7200:1: rulePathNameCS returns [EObject current=null] : ( ( (lv_ownedPathElements_0_0= ruleFirstPathElementCS ) ) (otherlv_1= '::' ( (lv_ownedPathElements_2_0= ruleNextPathElementCS ) ) )* ) ;
    public final EObject rulePathNameCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject lv_ownedPathElements_0_0 = null;

        EObject lv_ownedPathElements_2_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:7203:28: ( ( ( (lv_ownedPathElements_0_0= ruleFirstPathElementCS ) ) (otherlv_1= '::' ( (lv_ownedPathElements_2_0= ruleNextPathElementCS ) ) )* ) )
            // InternalCompleteOCL.g:7204:1: ( ( (lv_ownedPathElements_0_0= ruleFirstPathElementCS ) ) (otherlv_1= '::' ( (lv_ownedPathElements_2_0= ruleNextPathElementCS ) ) )* )
            {
            // InternalCompleteOCL.g:7204:1: ( ( (lv_ownedPathElements_0_0= ruleFirstPathElementCS ) ) (otherlv_1= '::' ( (lv_ownedPathElements_2_0= ruleNextPathElementCS ) ) )* )
            // InternalCompleteOCL.g:7204:2: ( (lv_ownedPathElements_0_0= ruleFirstPathElementCS ) ) (otherlv_1= '::' ( (lv_ownedPathElements_2_0= ruleNextPathElementCS ) ) )*
            {
            // InternalCompleteOCL.g:7204:2: ( (lv_ownedPathElements_0_0= ruleFirstPathElementCS ) )
            // InternalCompleteOCL.g:7205:1: (lv_ownedPathElements_0_0= ruleFirstPathElementCS )
            {
            // InternalCompleteOCL.g:7205:1: (lv_ownedPathElements_0_0= ruleFirstPathElementCS )
            // InternalCompleteOCL.g:7206:3: lv_ownedPathElements_0_0= ruleFirstPathElementCS
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getPathNameCSAccess().getOwnedPathElementsFirstPathElementCSParserRuleCall_0_0());

            }
            pushFollow(FollowSets000.FOLLOW_40);
            lv_ownedPathElements_0_0=ruleFirstPathElementCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getPathNameCSRule());
              	        }
                     		add(
                     			current,
                     			"ownedPathElements",
                      		lv_ownedPathElements_0_0,
                      		"org.eclipse.ocl.xtext.base.Base.FirstPathElementCS");
              	        afterParserOrEnumRuleCall();

            }

            }


            }

            // InternalCompleteOCL.g:7222:2: (otherlv_1= '::' ( (lv_ownedPathElements_2_0= ruleNextPathElementCS ) ) )*
            loop142:
            do {
                int alt142=2;
                int LA142_0 = input.LA(1);

                if ( (LA142_0==76) ) {
                    alt142=1;
                }


                switch (alt142) {
            	case 1 :
            	    // InternalCompleteOCL.g:7222:4: otherlv_1= '::' ( (lv_ownedPathElements_2_0= ruleNextPathElementCS ) )
            	    {
            	    otherlv_1=(Token)match(input,76,FollowSets000.FOLLOW_41); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_1, grammarAccess.getPathNameCSAccess().getColonColonKeyword_1_0());

            	    }
            	    // InternalCompleteOCL.g:7226:1: ( (lv_ownedPathElements_2_0= ruleNextPathElementCS ) )
            	    // InternalCompleteOCL.g:7227:1: (lv_ownedPathElements_2_0= ruleNextPathElementCS )
            	    {
            	    // InternalCompleteOCL.g:7227:1: (lv_ownedPathElements_2_0= ruleNextPathElementCS )
            	    // InternalCompleteOCL.g:7228:3: lv_ownedPathElements_2_0= ruleNextPathElementCS
            	    {
            	    if ( state.backtracking==0 ) {

            	      	        newCompositeNode(grammarAccess.getPathNameCSAccess().getOwnedPathElementsNextPathElementCSParserRuleCall_1_1_0());

            	    }
            	    pushFollow(FollowSets000.FOLLOW_40);
            	    lv_ownedPathElements_2_0=ruleNextPathElementCS();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getPathNameCSRule());
            	      	        }
            	             		add(
            	             			current,
            	             			"ownedPathElements",
            	              		lv_ownedPathElements_2_0,
            	              		"org.eclipse.ocl.xtext.base.Base.NextPathElementCS");
            	      	        afterParserOrEnumRuleCall();

            	    }

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop142;
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
    // $ANTLR end "rulePathNameCS"


    // $ANTLR start "entryRuleFirstPathElementCS"
    // InternalCompleteOCL.g:7252:1: entryRuleFirstPathElementCS returns [EObject current=null] : iv_ruleFirstPathElementCS= ruleFirstPathElementCS EOF ;
    public final EObject entryRuleFirstPathElementCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFirstPathElementCS = null;


        try {
            // InternalCompleteOCL.g:7253:2: (iv_ruleFirstPathElementCS= ruleFirstPathElementCS EOF )
            // InternalCompleteOCL.g:7254:2: iv_ruleFirstPathElementCS= ruleFirstPathElementCS EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getFirstPathElementCSRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleFirstPathElementCS=ruleFirstPathElementCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleFirstPathElementCS;
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
    // $ANTLR end "entryRuleFirstPathElementCS"


    // $ANTLR start "ruleFirstPathElementCS"
    // InternalCompleteOCL.g:7261:1: ruleFirstPathElementCS returns [EObject current=null] : ( ( ruleUnrestrictedName ) ) ;
    public final EObject ruleFirstPathElementCS() throws RecognitionException {
        EObject current = null;

         enterRule();

        try {
            // InternalCompleteOCL.g:7264:28: ( ( ( ruleUnrestrictedName ) ) )
            // InternalCompleteOCL.g:7265:1: ( ( ruleUnrestrictedName ) )
            {
            // InternalCompleteOCL.g:7265:1: ( ( ruleUnrestrictedName ) )
            // InternalCompleteOCL.g:7266:1: ( ruleUnrestrictedName )
            {
            // InternalCompleteOCL.g:7266:1: ( ruleUnrestrictedName )
            // InternalCompleteOCL.g:7267:3: ruleUnrestrictedName
            {
            if ( state.backtracking==0 ) {

              		  /* */

            }
            if ( state.backtracking==0 ) {

              			if (current==null) {
              	            current = createModelElement(grammarAccess.getFirstPathElementCSRule());
              	        }

            }
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getFirstPathElementCSAccess().getReferredElementNamedElementCrossReference_0());

            }
            pushFollow(FollowSets000.FOLLOW_2);
            ruleUnrestrictedName();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        afterParserOrEnumRuleCall();

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
    // $ANTLR end "ruleFirstPathElementCS"


    // $ANTLR start "entryRuleNextPathElementCS"
    // InternalCompleteOCL.g:7291:1: entryRuleNextPathElementCS returns [EObject current=null] : iv_ruleNextPathElementCS= ruleNextPathElementCS EOF ;
    public final EObject entryRuleNextPathElementCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNextPathElementCS = null;


        try {
            // InternalCompleteOCL.g:7292:2: (iv_ruleNextPathElementCS= ruleNextPathElementCS EOF )
            // InternalCompleteOCL.g:7293:2: iv_ruleNextPathElementCS= ruleNextPathElementCS EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getNextPathElementCSRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleNextPathElementCS=ruleNextPathElementCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleNextPathElementCS;
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
    // $ANTLR end "entryRuleNextPathElementCS"


    // $ANTLR start "ruleNextPathElementCS"
    // InternalCompleteOCL.g:7300:1: ruleNextPathElementCS returns [EObject current=null] : ( ( ruleUnreservedName ) ) ;
    public final EObject ruleNextPathElementCS() throws RecognitionException {
        EObject current = null;

         enterRule();

        try {
            // InternalCompleteOCL.g:7303:28: ( ( ( ruleUnreservedName ) ) )
            // InternalCompleteOCL.g:7304:1: ( ( ruleUnreservedName ) )
            {
            // InternalCompleteOCL.g:7304:1: ( ( ruleUnreservedName ) )
            // InternalCompleteOCL.g:7305:1: ( ruleUnreservedName )
            {
            // InternalCompleteOCL.g:7305:1: ( ruleUnreservedName )
            // InternalCompleteOCL.g:7306:3: ruleUnreservedName
            {
            if ( state.backtracking==0 ) {

              		  /* */

            }
            if ( state.backtracking==0 ) {

              			if (current==null) {
              	            current = createModelElement(grammarAccess.getNextPathElementCSRule());
              	        }

            }
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getNextPathElementCSAccess().getReferredElementNamedElementCrossReference_0());

            }
            pushFollow(FollowSets000.FOLLOW_2);
            ruleUnreservedName();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        afterParserOrEnumRuleCall();

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
    // $ANTLR end "ruleNextPathElementCS"


    // $ANTLR start "entryRuleTemplateBindingCS"
    // InternalCompleteOCL.g:7330:1: entryRuleTemplateBindingCS returns [EObject current=null] : iv_ruleTemplateBindingCS= ruleTemplateBindingCS EOF ;
    public final EObject entryRuleTemplateBindingCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTemplateBindingCS = null;


        try {
            // InternalCompleteOCL.g:7331:2: (iv_ruleTemplateBindingCS= ruleTemplateBindingCS EOF )
            // InternalCompleteOCL.g:7332:2: iv_ruleTemplateBindingCS= ruleTemplateBindingCS EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getTemplateBindingCSRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleTemplateBindingCS=ruleTemplateBindingCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleTemplateBindingCS;
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
    // $ANTLR end "entryRuleTemplateBindingCS"


    // $ANTLR start "ruleTemplateBindingCS"
    // InternalCompleteOCL.g:7339:1: ruleTemplateBindingCS returns [EObject current=null] : ( ( (lv_ownedSubstitutions_0_0= ruleTemplateParameterSubstitutionCS ) ) (otherlv_1= ',' ( (lv_ownedSubstitutions_2_0= ruleTemplateParameterSubstitutionCS ) ) )* ( (lv_ownedMultiplicity_3_0= ruleMultiplicityCS ) )? ) ;
    public final EObject ruleTemplateBindingCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject lv_ownedSubstitutions_0_0 = null;

        EObject lv_ownedSubstitutions_2_0 = null;

        EObject lv_ownedMultiplicity_3_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:7342:28: ( ( ( (lv_ownedSubstitutions_0_0= ruleTemplateParameterSubstitutionCS ) ) (otherlv_1= ',' ( (lv_ownedSubstitutions_2_0= ruleTemplateParameterSubstitutionCS ) ) )* ( (lv_ownedMultiplicity_3_0= ruleMultiplicityCS ) )? ) )
            // InternalCompleteOCL.g:7343:1: ( ( (lv_ownedSubstitutions_0_0= ruleTemplateParameterSubstitutionCS ) ) (otherlv_1= ',' ( (lv_ownedSubstitutions_2_0= ruleTemplateParameterSubstitutionCS ) ) )* ( (lv_ownedMultiplicity_3_0= ruleMultiplicityCS ) )? )
            {
            // InternalCompleteOCL.g:7343:1: ( ( (lv_ownedSubstitutions_0_0= ruleTemplateParameterSubstitutionCS ) ) (otherlv_1= ',' ( (lv_ownedSubstitutions_2_0= ruleTemplateParameterSubstitutionCS ) ) )* ( (lv_ownedMultiplicity_3_0= ruleMultiplicityCS ) )? )
            // InternalCompleteOCL.g:7343:2: ( (lv_ownedSubstitutions_0_0= ruleTemplateParameterSubstitutionCS ) ) (otherlv_1= ',' ( (lv_ownedSubstitutions_2_0= ruleTemplateParameterSubstitutionCS ) ) )* ( (lv_ownedMultiplicity_3_0= ruleMultiplicityCS ) )?
            {
            // InternalCompleteOCL.g:7343:2: ( (lv_ownedSubstitutions_0_0= ruleTemplateParameterSubstitutionCS ) )
            // InternalCompleteOCL.g:7344:1: (lv_ownedSubstitutions_0_0= ruleTemplateParameterSubstitutionCS )
            {
            // InternalCompleteOCL.g:7344:1: (lv_ownedSubstitutions_0_0= ruleTemplateParameterSubstitutionCS )
            // InternalCompleteOCL.g:7345:3: lv_ownedSubstitutions_0_0= ruleTemplateParameterSubstitutionCS
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getTemplateBindingCSAccess().getOwnedSubstitutionsTemplateParameterSubstitutionCSParserRuleCall_0_0());

            }
            pushFollow(FollowSets000.FOLLOW_85);
            lv_ownedSubstitutions_0_0=ruleTemplateParameterSubstitutionCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getTemplateBindingCSRule());
              	        }
                     		add(
                     			current,
                     			"ownedSubstitutions",
                      		lv_ownedSubstitutions_0_0,
                      		"org.eclipse.ocl.xtext.base.Base.TemplateParameterSubstitutionCS");
              	        afterParserOrEnumRuleCall();

            }

            }


            }

            // InternalCompleteOCL.g:7361:2: (otherlv_1= ',' ( (lv_ownedSubstitutions_2_0= ruleTemplateParameterSubstitutionCS ) ) )*
            loop143:
            do {
                int alt143=2;
                int LA143_0 = input.LA(1);

                if ( (LA143_0==29) ) {
                    alt143=1;
                }


                switch (alt143) {
            	case 1 :
            	    // InternalCompleteOCL.g:7361:4: otherlv_1= ',' ( (lv_ownedSubstitutions_2_0= ruleTemplateParameterSubstitutionCS ) )
            	    {
            	    otherlv_1=(Token)match(input,29,FollowSets000.FOLLOW_86); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_1, grammarAccess.getTemplateBindingCSAccess().getCommaKeyword_1_0());

            	    }
            	    // InternalCompleteOCL.g:7365:1: ( (lv_ownedSubstitutions_2_0= ruleTemplateParameterSubstitutionCS ) )
            	    // InternalCompleteOCL.g:7366:1: (lv_ownedSubstitutions_2_0= ruleTemplateParameterSubstitutionCS )
            	    {
            	    // InternalCompleteOCL.g:7366:1: (lv_ownedSubstitutions_2_0= ruleTemplateParameterSubstitutionCS )
            	    // InternalCompleteOCL.g:7367:3: lv_ownedSubstitutions_2_0= ruleTemplateParameterSubstitutionCS
            	    {
            	    if ( state.backtracking==0 ) {

            	      	        newCompositeNode(grammarAccess.getTemplateBindingCSAccess().getOwnedSubstitutionsTemplateParameterSubstitutionCSParserRuleCall_1_1_0());

            	    }
            	    pushFollow(FollowSets000.FOLLOW_85);
            	    lv_ownedSubstitutions_2_0=ruleTemplateParameterSubstitutionCS();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getTemplateBindingCSRule());
            	      	        }
            	             		add(
            	             			current,
            	             			"ownedSubstitutions",
            	              		lv_ownedSubstitutions_2_0,
            	              		"org.eclipse.ocl.xtext.base.Base.TemplateParameterSubstitutionCS");
            	      	        afterParserOrEnumRuleCall();

            	    }

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop143;
                }
            } while (true);

            // InternalCompleteOCL.g:7383:4: ( (lv_ownedMultiplicity_3_0= ruleMultiplicityCS ) )?
            int alt144=2;
            int LA144_0 = input.LA(1);

            if ( (LA144_0==93) ) {
                alt144=1;
            }
            switch (alt144) {
                case 1 :
                    // InternalCompleteOCL.g:7384:1: (lv_ownedMultiplicity_3_0= ruleMultiplicityCS )
                    {
                    // InternalCompleteOCL.g:7384:1: (lv_ownedMultiplicity_3_0= ruleMultiplicityCS )
                    // InternalCompleteOCL.g:7385:3: lv_ownedMultiplicity_3_0= ruleMultiplicityCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getTemplateBindingCSAccess().getOwnedMultiplicityMultiplicityCSParserRuleCall_2_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    lv_ownedMultiplicity_3_0=ruleMultiplicityCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getTemplateBindingCSRule());
                      	        }
                             		set(
                             			current,
                             			"ownedMultiplicity",
                              		lv_ownedMultiplicity_3_0,
                              		"org.eclipse.ocl.xtext.base.Base.MultiplicityCS");
                      	        afterParserOrEnumRuleCall();

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
    // $ANTLR end "ruleTemplateBindingCS"


    // $ANTLR start "entryRuleTemplateParameterSubstitutionCS"
    // InternalCompleteOCL.g:7409:1: entryRuleTemplateParameterSubstitutionCS returns [EObject current=null] : iv_ruleTemplateParameterSubstitutionCS= ruleTemplateParameterSubstitutionCS EOF ;
    public final EObject entryRuleTemplateParameterSubstitutionCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTemplateParameterSubstitutionCS = null;


        try {
            // InternalCompleteOCL.g:7410:2: (iv_ruleTemplateParameterSubstitutionCS= ruleTemplateParameterSubstitutionCS EOF )
            // InternalCompleteOCL.g:7411:2: iv_ruleTemplateParameterSubstitutionCS= ruleTemplateParameterSubstitutionCS EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getTemplateParameterSubstitutionCSRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleTemplateParameterSubstitutionCS=ruleTemplateParameterSubstitutionCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleTemplateParameterSubstitutionCS;
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
    // $ANTLR end "entryRuleTemplateParameterSubstitutionCS"


    // $ANTLR start "ruleTemplateParameterSubstitutionCS"
    // InternalCompleteOCL.g:7418:1: ruleTemplateParameterSubstitutionCS returns [EObject current=null] : ( (lv_ownedActualParameter_0_0= ruleTypeRefCS ) ) ;
    public final EObject ruleTemplateParameterSubstitutionCS() throws RecognitionException {
        EObject current = null;

        EObject lv_ownedActualParameter_0_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:7421:28: ( ( (lv_ownedActualParameter_0_0= ruleTypeRefCS ) ) )
            // InternalCompleteOCL.g:7422:1: ( (lv_ownedActualParameter_0_0= ruleTypeRefCS ) )
            {
            // InternalCompleteOCL.g:7422:1: ( (lv_ownedActualParameter_0_0= ruleTypeRefCS ) )
            // InternalCompleteOCL.g:7423:1: (lv_ownedActualParameter_0_0= ruleTypeRefCS )
            {
            // InternalCompleteOCL.g:7423:1: (lv_ownedActualParameter_0_0= ruleTypeRefCS )
            // InternalCompleteOCL.g:7424:3: lv_ownedActualParameter_0_0= ruleTypeRefCS
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getTemplateParameterSubstitutionCSAccess().getOwnedActualParameterTypeRefCSParserRuleCall_0());

            }
            pushFollow(FollowSets000.FOLLOW_2);
            lv_ownedActualParameter_0_0=ruleTypeRefCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getTemplateParameterSubstitutionCSRule());
              	        }
                     		set(
                     			current,
                     			"ownedActualParameter",
                      		lv_ownedActualParameter_0_0,
                      		"org.eclipse.ocl.xtext.base.Base.TypeRefCS");
              	        afterParserOrEnumRuleCall();

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
    // $ANTLR end "ruleTemplateParameterSubstitutionCS"


    // $ANTLR start "entryRuleTypeParameterCS"
    // InternalCompleteOCL.g:7448:1: entryRuleTypeParameterCS returns [EObject current=null] : iv_ruleTypeParameterCS= ruleTypeParameterCS EOF ;
    public final EObject entryRuleTypeParameterCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTypeParameterCS = null;


        try {
            // InternalCompleteOCL.g:7449:2: (iv_ruleTypeParameterCS= ruleTypeParameterCS EOF )
            // InternalCompleteOCL.g:7450:2: iv_ruleTypeParameterCS= ruleTypeParameterCS EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getTypeParameterCSRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleTypeParameterCS=ruleTypeParameterCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleTypeParameterCS;
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
    // $ANTLR end "entryRuleTypeParameterCS"


    // $ANTLR start "ruleTypeParameterCS"
    // InternalCompleteOCL.g:7457:1: ruleTypeParameterCS returns [EObject current=null] : ( ( (lv_name_0_0= ruleUnrestrictedName ) ) (otherlv_1= 'extends' ( (lv_ownedExtends_2_0= ruleTypedRefCS ) ) (otherlv_3= '&&' ( (lv_ownedExtends_4_0= ruleTypedRefCS ) ) )* )? ) ;
    public final EObject ruleTypeParameterCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        AntlrDatatypeRuleToken lv_name_0_0 = null;

        EObject lv_ownedExtends_2_0 = null;

        EObject lv_ownedExtends_4_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:7460:28: ( ( ( (lv_name_0_0= ruleUnrestrictedName ) ) (otherlv_1= 'extends' ( (lv_ownedExtends_2_0= ruleTypedRefCS ) ) (otherlv_3= '&&' ( (lv_ownedExtends_4_0= ruleTypedRefCS ) ) )* )? ) )
            // InternalCompleteOCL.g:7461:1: ( ( (lv_name_0_0= ruleUnrestrictedName ) ) (otherlv_1= 'extends' ( (lv_ownedExtends_2_0= ruleTypedRefCS ) ) (otherlv_3= '&&' ( (lv_ownedExtends_4_0= ruleTypedRefCS ) ) )* )? )
            {
            // InternalCompleteOCL.g:7461:1: ( ( (lv_name_0_0= ruleUnrestrictedName ) ) (otherlv_1= 'extends' ( (lv_ownedExtends_2_0= ruleTypedRefCS ) ) (otherlv_3= '&&' ( (lv_ownedExtends_4_0= ruleTypedRefCS ) ) )* )? )
            // InternalCompleteOCL.g:7461:2: ( (lv_name_0_0= ruleUnrestrictedName ) ) (otherlv_1= 'extends' ( (lv_ownedExtends_2_0= ruleTypedRefCS ) ) (otherlv_3= '&&' ( (lv_ownedExtends_4_0= ruleTypedRefCS ) ) )* )?
            {
            // InternalCompleteOCL.g:7461:2: ( (lv_name_0_0= ruleUnrestrictedName ) )
            // InternalCompleteOCL.g:7462:1: (lv_name_0_0= ruleUnrestrictedName )
            {
            // InternalCompleteOCL.g:7462:1: (lv_name_0_0= ruleUnrestrictedName )
            // InternalCompleteOCL.g:7463:3: lv_name_0_0= ruleUnrestrictedName
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getTypeParameterCSAccess().getNameUnrestrictedNameParserRuleCall_0_0());

            }
            pushFollow(FollowSets000.FOLLOW_87);
            lv_name_0_0=ruleUnrestrictedName();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getTypeParameterCSRule());
              	        }
                     		set(
                     			current,
                     			"name",
                      		lv_name_0_0,
                      		"org.eclipse.ocl.xtext.completeocl.CompleteOCL.UnrestrictedName");
              	        afterParserOrEnumRuleCall();

            }

            }


            }

            // InternalCompleteOCL.g:7479:2: (otherlv_1= 'extends' ( (lv_ownedExtends_2_0= ruleTypedRefCS ) ) (otherlv_3= '&&' ( (lv_ownedExtends_4_0= ruleTypedRefCS ) ) )* )?
            int alt146=2;
            int LA146_0 = input.LA(1);

            if ( (LA146_0==107) ) {
                alt146=1;
            }
            switch (alt146) {
                case 1 :
                    // InternalCompleteOCL.g:7479:4: otherlv_1= 'extends' ( (lv_ownedExtends_2_0= ruleTypedRefCS ) ) (otherlv_3= '&&' ( (lv_ownedExtends_4_0= ruleTypedRefCS ) ) )*
                    {
                    otherlv_1=(Token)match(input,107,FollowSets000.FOLLOW_25); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_1, grammarAccess.getTypeParameterCSAccess().getExtendsKeyword_1_0());

                    }
                    // InternalCompleteOCL.g:7483:1: ( (lv_ownedExtends_2_0= ruleTypedRefCS ) )
                    // InternalCompleteOCL.g:7484:1: (lv_ownedExtends_2_0= ruleTypedRefCS )
                    {
                    // InternalCompleteOCL.g:7484:1: (lv_ownedExtends_2_0= ruleTypedRefCS )
                    // InternalCompleteOCL.g:7485:3: lv_ownedExtends_2_0= ruleTypedRefCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getTypeParameterCSAccess().getOwnedExtendsTypedRefCSParserRuleCall_1_1_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_88);
                    lv_ownedExtends_2_0=ruleTypedRefCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getTypeParameterCSRule());
                      	        }
                             		add(
                             			current,
                             			"ownedExtends",
                              		lv_ownedExtends_2_0,
                              		"org.eclipse.ocl.xtext.completeocl.CompleteOCL.TypedRefCS");
                      	        afterParserOrEnumRuleCall();

                    }

                    }


                    }

                    // InternalCompleteOCL.g:7501:2: (otherlv_3= '&&' ( (lv_ownedExtends_4_0= ruleTypedRefCS ) ) )*
                    loop145:
                    do {
                        int alt145=2;
                        int LA145_0 = input.LA(1);

                        if ( (LA145_0==108) ) {
                            alt145=1;
                        }


                        switch (alt145) {
                    	case 1 :
                    	    // InternalCompleteOCL.g:7501:4: otherlv_3= '&&' ( (lv_ownedExtends_4_0= ruleTypedRefCS ) )
                    	    {
                    	    otherlv_3=(Token)match(input,108,FollowSets000.FOLLOW_25); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_3, grammarAccess.getTypeParameterCSAccess().getAmpersandAmpersandKeyword_1_2_0());

                    	    }
                    	    // InternalCompleteOCL.g:7505:1: ( (lv_ownedExtends_4_0= ruleTypedRefCS ) )
                    	    // InternalCompleteOCL.g:7506:1: (lv_ownedExtends_4_0= ruleTypedRefCS )
                    	    {
                    	    // InternalCompleteOCL.g:7506:1: (lv_ownedExtends_4_0= ruleTypedRefCS )
                    	    // InternalCompleteOCL.g:7507:3: lv_ownedExtends_4_0= ruleTypedRefCS
                    	    {
                    	    if ( state.backtracking==0 ) {

                    	      	        newCompositeNode(grammarAccess.getTypeParameterCSAccess().getOwnedExtendsTypedRefCSParserRuleCall_1_2_1_0());

                    	    }
                    	    pushFollow(FollowSets000.FOLLOW_88);
                    	    lv_ownedExtends_4_0=ruleTypedRefCS();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = createModelElementForParent(grammarAccess.getTypeParameterCSRule());
                    	      	        }
                    	             		add(
                    	             			current,
                    	             			"ownedExtends",
                    	              		lv_ownedExtends_4_0,
                    	              		"org.eclipse.ocl.xtext.completeocl.CompleteOCL.TypedRefCS");
                    	      	        afterParserOrEnumRuleCall();

                    	    }

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop145;
                        }
                    } while (true);


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
    // $ANTLR end "ruleTypeParameterCS"


    // $ANTLR start "entryRuleTypeRefCS"
    // InternalCompleteOCL.g:7531:1: entryRuleTypeRefCS returns [EObject current=null] : iv_ruleTypeRefCS= ruleTypeRefCS EOF ;
    public final EObject entryRuleTypeRefCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTypeRefCS = null;


        try {
            // InternalCompleteOCL.g:7532:2: (iv_ruleTypeRefCS= ruleTypeRefCS EOF )
            // InternalCompleteOCL.g:7533:2: iv_ruleTypeRefCS= ruleTypeRefCS EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getTypeRefCSRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleTypeRefCS=ruleTypeRefCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleTypeRefCS;
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
    // $ANTLR end "entryRuleTypeRefCS"


    // $ANTLR start "ruleTypeRefCS"
    // InternalCompleteOCL.g:7540:1: ruleTypeRefCS returns [EObject current=null] : (this_TypedRefCS_0= ruleTypedRefCS | this_WildcardTypeRefCS_1= ruleWildcardTypeRefCS ) ;
    public final EObject ruleTypeRefCS() throws RecognitionException {
        EObject current = null;

        EObject this_TypedRefCS_0 = null;

        EObject this_WildcardTypeRefCS_1 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:7543:28: ( (this_TypedRefCS_0= ruleTypedRefCS | this_WildcardTypeRefCS_1= ruleWildcardTypeRefCS ) )
            // InternalCompleteOCL.g:7544:1: (this_TypedRefCS_0= ruleTypedRefCS | this_WildcardTypeRefCS_1= ruleWildcardTypeRefCS )
            {
            // InternalCompleteOCL.g:7544:1: (this_TypedRefCS_0= ruleTypedRefCS | this_WildcardTypeRefCS_1= ruleWildcardTypeRefCS )
            int alt147=2;
            int LA147_0 = input.LA(1);

            if ( ((LA147_0>=RULE_SIMPLE_ID && LA147_0<=RULE_ESCAPED_ID)||(LA147_0>=31 && LA147_0<=33)||(LA147_0>=43 && LA147_0<=52)||(LA147_0>=74 && LA147_0<=75)||(LA147_0>=77 && LA147_0<=81)) ) {
                alt147=1;
            }
            else if ( (LA147_0==42) ) {
                alt147=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 147, 0, input);

                throw nvae;
            }
            switch (alt147) {
                case 1 :
                    // InternalCompleteOCL.g:7545:2: this_TypedRefCS_0= ruleTypedRefCS
                    {
                    if ( state.backtracking==0 ) {

                      	  /* */

                    }
                    if ( state.backtracking==0 ) {

                              newCompositeNode(grammarAccess.getTypeRefCSAccess().getTypedRefCSParserRuleCall_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_TypedRefCS_0=ruleTypedRefCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_TypedRefCS_0;
                              afterParserOrEnumRuleCall();

                    }

                    }
                    break;
                case 2 :
                    // InternalCompleteOCL.g:7558:2: this_WildcardTypeRefCS_1= ruleWildcardTypeRefCS
                    {
                    if ( state.backtracking==0 ) {

                      	  /* */

                    }
                    if ( state.backtracking==0 ) {

                              newCompositeNode(grammarAccess.getTypeRefCSAccess().getWildcardTypeRefCSParserRuleCall_1());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    this_WildcardTypeRefCS_1=ruleWildcardTypeRefCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_WildcardTypeRefCS_1;
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
    // $ANTLR end "ruleTypeRefCS"


    // $ANTLR start "entryRuleTypedTypeRefCS"
    // InternalCompleteOCL.g:7577:1: entryRuleTypedTypeRefCS returns [EObject current=null] : iv_ruleTypedTypeRefCS= ruleTypedTypeRefCS EOF ;
    public final EObject entryRuleTypedTypeRefCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTypedTypeRefCS = null;


        try {
            // InternalCompleteOCL.g:7578:2: (iv_ruleTypedTypeRefCS= ruleTypedTypeRefCS EOF )
            // InternalCompleteOCL.g:7579:2: iv_ruleTypedTypeRefCS= ruleTypedTypeRefCS EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getTypedTypeRefCSRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleTypedTypeRefCS=ruleTypedTypeRefCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleTypedTypeRefCS;
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
    // $ANTLR end "entryRuleTypedTypeRefCS"


    // $ANTLR start "ruleTypedTypeRefCS"
    // InternalCompleteOCL.g:7586:1: ruleTypedTypeRefCS returns [EObject current=null] : ( ( (lv_ownedPathName_0_0= rulePathNameCS ) ) (otherlv_1= '(' ( (lv_ownedBinding_2_0= ruleTemplateBindingCS ) ) otherlv_3= ')' )? ) ;
    public final EObject ruleTypedTypeRefCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_ownedPathName_0_0 = null;

        EObject lv_ownedBinding_2_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:7589:28: ( ( ( (lv_ownedPathName_0_0= rulePathNameCS ) ) (otherlv_1= '(' ( (lv_ownedBinding_2_0= ruleTemplateBindingCS ) ) otherlv_3= ')' )? ) )
            // InternalCompleteOCL.g:7590:1: ( ( (lv_ownedPathName_0_0= rulePathNameCS ) ) (otherlv_1= '(' ( (lv_ownedBinding_2_0= ruleTemplateBindingCS ) ) otherlv_3= ')' )? )
            {
            // InternalCompleteOCL.g:7590:1: ( ( (lv_ownedPathName_0_0= rulePathNameCS ) ) (otherlv_1= '(' ( (lv_ownedBinding_2_0= ruleTemplateBindingCS ) ) otherlv_3= ')' )? )
            // InternalCompleteOCL.g:7590:2: ( (lv_ownedPathName_0_0= rulePathNameCS ) ) (otherlv_1= '(' ( (lv_ownedBinding_2_0= ruleTemplateBindingCS ) ) otherlv_3= ')' )?
            {
            // InternalCompleteOCL.g:7590:2: ( (lv_ownedPathName_0_0= rulePathNameCS ) )
            // InternalCompleteOCL.g:7591:1: (lv_ownedPathName_0_0= rulePathNameCS )
            {
            // InternalCompleteOCL.g:7591:1: (lv_ownedPathName_0_0= rulePathNameCS )
            // InternalCompleteOCL.g:7592:3: lv_ownedPathName_0_0= rulePathNameCS
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getTypedTypeRefCSAccess().getOwnedPathNamePathNameCSParserRuleCall_0_0());

            }
            pushFollow(FollowSets000.FOLLOW_42);
            lv_ownedPathName_0_0=rulePathNameCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getTypedTypeRefCSRule());
              	        }
                     		set(
                     			current,
                     			"ownedPathName",
                      		lv_ownedPathName_0_0,
                      		"org.eclipse.ocl.xtext.base.Base.PathNameCS");
              	        afterParserOrEnumRuleCall();

            }

            }


            }

            // InternalCompleteOCL.g:7608:2: (otherlv_1= '(' ( (lv_ownedBinding_2_0= ruleTemplateBindingCS ) ) otherlv_3= ')' )?
            int alt148=2;
            int LA148_0 = input.LA(1);

            if ( (LA148_0==22) ) {
                alt148=1;
            }
            switch (alt148) {
                case 1 :
                    // InternalCompleteOCL.g:7608:4: otherlv_1= '(' ( (lv_ownedBinding_2_0= ruleTemplateBindingCS ) ) otherlv_3= ')'
                    {
                    otherlv_1=(Token)match(input,22,FollowSets000.FOLLOW_86); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_1, grammarAccess.getTypedTypeRefCSAccess().getLeftParenthesisKeyword_1_0());

                    }
                    // InternalCompleteOCL.g:7612:1: ( (lv_ownedBinding_2_0= ruleTemplateBindingCS ) )
                    // InternalCompleteOCL.g:7613:1: (lv_ownedBinding_2_0= ruleTemplateBindingCS )
                    {
                    // InternalCompleteOCL.g:7613:1: (lv_ownedBinding_2_0= ruleTemplateBindingCS )
                    // InternalCompleteOCL.g:7614:3: lv_ownedBinding_2_0= ruleTemplateBindingCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getTypedTypeRefCSAccess().getOwnedBindingTemplateBindingCSParserRuleCall_1_1_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_13);
                    lv_ownedBinding_2_0=ruleTemplateBindingCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getTypedTypeRefCSRule());
                      	        }
                             		set(
                             			current,
                             			"ownedBinding",
                              		lv_ownedBinding_2_0,
                              		"org.eclipse.ocl.xtext.base.Base.TemplateBindingCS");
                      	        afterParserOrEnumRuleCall();

                    }

                    }


                    }

                    otherlv_3=(Token)match(input,23,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_3, grammarAccess.getTypedTypeRefCSAccess().getRightParenthesisKeyword_1_2());

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
    // $ANTLR end "ruleTypedTypeRefCS"


    // $ANTLR start "entryRuleWildcardTypeRefCS"
    // InternalCompleteOCL.g:7642:1: entryRuleWildcardTypeRefCS returns [EObject current=null] : iv_ruleWildcardTypeRefCS= ruleWildcardTypeRefCS EOF ;
    public final EObject entryRuleWildcardTypeRefCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleWildcardTypeRefCS = null;


        try {
            // InternalCompleteOCL.g:7643:2: (iv_ruleWildcardTypeRefCS= ruleWildcardTypeRefCS EOF )
            // InternalCompleteOCL.g:7644:2: iv_ruleWildcardTypeRefCS= ruleWildcardTypeRefCS EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getWildcardTypeRefCSRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleWildcardTypeRefCS=ruleWildcardTypeRefCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleWildcardTypeRefCS;
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
    // $ANTLR end "entryRuleWildcardTypeRefCS"


    // $ANTLR start "ruleWildcardTypeRefCS"
    // InternalCompleteOCL.g:7651:1: ruleWildcardTypeRefCS returns [EObject current=null] : ( () otherlv_1= '?' (otherlv_2= 'extends' ( (lv_ownedExtends_3_0= ruleTypedRefCS ) ) )? ) ;
    public final EObject ruleWildcardTypeRefCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        EObject lv_ownedExtends_3_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:7654:28: ( ( () otherlv_1= '?' (otherlv_2= 'extends' ( (lv_ownedExtends_3_0= ruleTypedRefCS ) ) )? ) )
            // InternalCompleteOCL.g:7655:1: ( () otherlv_1= '?' (otherlv_2= 'extends' ( (lv_ownedExtends_3_0= ruleTypedRefCS ) ) )? )
            {
            // InternalCompleteOCL.g:7655:1: ( () otherlv_1= '?' (otherlv_2= 'extends' ( (lv_ownedExtends_3_0= ruleTypedRefCS ) ) )? )
            // InternalCompleteOCL.g:7655:2: () otherlv_1= '?' (otherlv_2= 'extends' ( (lv_ownedExtends_3_0= ruleTypedRefCS ) ) )?
            {
            // InternalCompleteOCL.g:7655:2: ()
            // InternalCompleteOCL.g:7656:2:
            {
            if ( state.backtracking==0 ) {

              	  /* */

            }
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getWildcardTypeRefCSAccess().getWildcardTypeRefCSAction_0(),
                          current);

            }

            }

            otherlv_1=(Token)match(input,42,FollowSets000.FOLLOW_87); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getWildcardTypeRefCSAccess().getQuestionMarkKeyword_1());

            }
            // InternalCompleteOCL.g:7668:1: (otherlv_2= 'extends' ( (lv_ownedExtends_3_0= ruleTypedRefCS ) ) )?
            int alt149=2;
            int LA149_0 = input.LA(1);

            if ( (LA149_0==107) ) {
                alt149=1;
            }
            switch (alt149) {
                case 1 :
                    // InternalCompleteOCL.g:7668:3: otherlv_2= 'extends' ( (lv_ownedExtends_3_0= ruleTypedRefCS ) )
                    {
                    otherlv_2=(Token)match(input,107,FollowSets000.FOLLOW_25); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_2, grammarAccess.getWildcardTypeRefCSAccess().getExtendsKeyword_2_0());

                    }
                    // InternalCompleteOCL.g:7672:1: ( (lv_ownedExtends_3_0= ruleTypedRefCS ) )
                    // InternalCompleteOCL.g:7673:1: (lv_ownedExtends_3_0= ruleTypedRefCS )
                    {
                    // InternalCompleteOCL.g:7673:1: (lv_ownedExtends_3_0= ruleTypedRefCS )
                    // InternalCompleteOCL.g:7674:3: lv_ownedExtends_3_0= ruleTypedRefCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getWildcardTypeRefCSAccess().getOwnedExtendsTypedRefCSParserRuleCall_2_1_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    lv_ownedExtends_3_0=ruleTypedRefCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getWildcardTypeRefCSRule());
                      	        }
                             		set(
                             			current,
                             			"ownedExtends",
                              		lv_ownedExtends_3_0,
                              		"org.eclipse.ocl.xtext.completeocl.CompleteOCL.TypedRefCS");
                      	        afterParserOrEnumRuleCall();

                    }

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
    // $ANTLR end "ruleWildcardTypeRefCS"


    // $ANTLR start "entryRuleID"
    // InternalCompleteOCL.g:7698:1: entryRuleID returns [String current=null] : iv_ruleID= ruleID EOF ;
    public final String entryRuleID() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleID = null;


        try {
            // InternalCompleteOCL.g:7699:2: (iv_ruleID= ruleID EOF )
            // InternalCompleteOCL.g:7700:2: iv_ruleID= ruleID EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getIDRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleID=ruleID();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleID.getText();
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
    // $ANTLR end "entryRuleID"


    // $ANTLR start "ruleID"
    // InternalCompleteOCL.g:7707:1: ruleID returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_SIMPLE_ID_0= RULE_SIMPLE_ID | this_ESCAPED_ID_1= RULE_ESCAPED_ID ) ;
    public final AntlrDatatypeRuleToken ruleID() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_SIMPLE_ID_0=null;
        Token this_ESCAPED_ID_1=null;

         enterRule();

        try {
            // InternalCompleteOCL.g:7710:28: ( (this_SIMPLE_ID_0= RULE_SIMPLE_ID | this_ESCAPED_ID_1= RULE_ESCAPED_ID ) )
            // InternalCompleteOCL.g:7711:1: (this_SIMPLE_ID_0= RULE_SIMPLE_ID | this_ESCAPED_ID_1= RULE_ESCAPED_ID )
            {
            // InternalCompleteOCL.g:7711:1: (this_SIMPLE_ID_0= RULE_SIMPLE_ID | this_ESCAPED_ID_1= RULE_ESCAPED_ID )
            int alt150=2;
            int LA150_0 = input.LA(1);

            if ( (LA150_0==RULE_SIMPLE_ID) ) {
                alt150=1;
            }
            else if ( (LA150_0==RULE_ESCAPED_ID) ) {
                alt150=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 150, 0, input);

                throw nvae;
            }
            switch (alt150) {
                case 1 :
                    // InternalCompleteOCL.g:7711:6: this_SIMPLE_ID_0= RULE_SIMPLE_ID
                    {
                    this_SIMPLE_ID_0=(Token)match(input,RULE_SIMPLE_ID,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      		current.merge(this_SIMPLE_ID_0);

                    }
                    if ( state.backtracking==0 ) {

                          newLeafNode(this_SIMPLE_ID_0, grammarAccess.getIDAccess().getSIMPLE_IDTerminalRuleCall_0());

                    }

                    }
                    break;
                case 2 :
                    // InternalCompleteOCL.g:7719:10: this_ESCAPED_ID_1= RULE_ESCAPED_ID
                    {
                    this_ESCAPED_ID_1=(Token)match(input,RULE_ESCAPED_ID,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      		current.merge(this_ESCAPED_ID_1);

                    }
                    if ( state.backtracking==0 ) {

                          newLeafNode(this_ESCAPED_ID_1, grammarAccess.getIDAccess().getESCAPED_IDTerminalRuleCall_1());

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
    // $ANTLR end "ruleID"


    // $ANTLR start "entryRuleIdentifier"
    // InternalCompleteOCL.g:7734:1: entryRuleIdentifier returns [String current=null] : iv_ruleIdentifier= ruleIdentifier EOF ;
    public final String entryRuleIdentifier() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleIdentifier = null;


        try {
            // InternalCompleteOCL.g:7735:2: (iv_ruleIdentifier= ruleIdentifier EOF )
            // InternalCompleteOCL.g:7736:2: iv_ruleIdentifier= ruleIdentifier EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getIdentifierRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleIdentifier=ruleIdentifier();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleIdentifier.getText();
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
    // $ANTLR end "entryRuleIdentifier"


    // $ANTLR start "ruleIdentifier"
    // InternalCompleteOCL.g:7743:1: ruleIdentifier returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_ID_0= ruleID ;
    public final AntlrDatatypeRuleToken ruleIdentifier() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        AntlrDatatypeRuleToken this_ID_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:7746:28: (this_ID_0= ruleID )
            // InternalCompleteOCL.g:7748:5: this_ID_0= ruleID
            {
            if ( state.backtracking==0 ) {

                      newCompositeNode(grammarAccess.getIdentifierAccess().getIDParserRuleCall());

            }
            pushFollow(FollowSets000.FOLLOW_2);
            this_ID_0=ruleID();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              		current.merge(this_ID_0);

            }
            if ( state.backtracking==0 ) {

                      afterParserOrEnumRuleCall();

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
    // $ANTLR end "ruleIdentifier"


    // $ANTLR start "entryRuleLOWER"
    // InternalCompleteOCL.g:7766:1: entryRuleLOWER returns [String current=null] : iv_ruleLOWER= ruleLOWER EOF ;
    public final String entryRuleLOWER() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleLOWER = null;


        try {
            // InternalCompleteOCL.g:7767:2: (iv_ruleLOWER= ruleLOWER EOF )
            // InternalCompleteOCL.g:7768:2: iv_ruleLOWER= ruleLOWER EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getLOWERRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleLOWER=ruleLOWER();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleLOWER.getText();
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
    // $ANTLR end "entryRuleLOWER"


    // $ANTLR start "ruleLOWER"
    // InternalCompleteOCL.g:7775:1: ruleLOWER returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_INT_0= RULE_INT ;
    public final AntlrDatatypeRuleToken ruleLOWER() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_INT_0=null;

         enterRule();

        try {
            // InternalCompleteOCL.g:7778:28: (this_INT_0= RULE_INT )
            // InternalCompleteOCL.g:7779:5: this_INT_0= RULE_INT
            {
            this_INT_0=(Token)match(input,RULE_INT,FollowSets000.FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              		current.merge(this_INT_0);

            }
            if ( state.backtracking==0 ) {

                  newLeafNode(this_INT_0, grammarAccess.getLOWERAccess().getINTTerminalRuleCall());

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
    // $ANTLR end "ruleLOWER"


    // $ANTLR start "entryRuleNUMBER_LITERAL"
    // InternalCompleteOCL.g:7794:1: entryRuleNUMBER_LITERAL returns [String current=null] : iv_ruleNUMBER_LITERAL= ruleNUMBER_LITERAL EOF ;
    public final String entryRuleNUMBER_LITERAL() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleNUMBER_LITERAL = null;


        try {
            // InternalCompleteOCL.g:7795:2: (iv_ruleNUMBER_LITERAL= ruleNUMBER_LITERAL EOF )
            // InternalCompleteOCL.g:7796:2: iv_ruleNUMBER_LITERAL= ruleNUMBER_LITERAL EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getNUMBER_LITERALRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleNUMBER_LITERAL=ruleNUMBER_LITERAL();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleNUMBER_LITERAL.getText();
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
    // $ANTLR end "entryRuleNUMBER_LITERAL"


    // $ANTLR start "ruleNUMBER_LITERAL"
    // InternalCompleteOCL.g:7803:1: ruleNUMBER_LITERAL returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_INT_0= RULE_INT ;
    public final AntlrDatatypeRuleToken ruleNUMBER_LITERAL() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_INT_0=null;

         enterRule();

        try {
            // InternalCompleteOCL.g:7806:28: (this_INT_0= RULE_INT )
            // InternalCompleteOCL.g:7807:5: this_INT_0= RULE_INT
            {
            this_INT_0=(Token)match(input,RULE_INT,FollowSets000.FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              		current.merge(this_INT_0);

            }
            if ( state.backtracking==0 ) {

                  newLeafNode(this_INT_0, grammarAccess.getNUMBER_LITERALAccess().getINTTerminalRuleCall());

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
    // $ANTLR end "ruleNUMBER_LITERAL"


    // $ANTLR start "entryRuleStringLiteral"
    // InternalCompleteOCL.g:7822:1: entryRuleStringLiteral returns [String current=null] : iv_ruleStringLiteral= ruleStringLiteral EOF ;
    public final String entryRuleStringLiteral() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleStringLiteral = null;


        try {
            // InternalCompleteOCL.g:7823:2: (iv_ruleStringLiteral= ruleStringLiteral EOF )
            // InternalCompleteOCL.g:7824:2: iv_ruleStringLiteral= ruleStringLiteral EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getStringLiteralRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleStringLiteral=ruleStringLiteral();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleStringLiteral.getText();
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
    // $ANTLR end "entryRuleStringLiteral"


    // $ANTLR start "ruleStringLiteral"
    // InternalCompleteOCL.g:7831:1: ruleStringLiteral returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_SINGLE_QUOTED_STRING_0= RULE_SINGLE_QUOTED_STRING ;
    public final AntlrDatatypeRuleToken ruleStringLiteral() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_SINGLE_QUOTED_STRING_0=null;

         enterRule();

        try {
            // InternalCompleteOCL.g:7834:28: (this_SINGLE_QUOTED_STRING_0= RULE_SINGLE_QUOTED_STRING )
            // InternalCompleteOCL.g:7835:5: this_SINGLE_QUOTED_STRING_0= RULE_SINGLE_QUOTED_STRING
            {
            this_SINGLE_QUOTED_STRING_0=(Token)match(input,RULE_SINGLE_QUOTED_STRING,FollowSets000.FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              		current.merge(this_SINGLE_QUOTED_STRING_0);

            }
            if ( state.backtracking==0 ) {

                  newLeafNode(this_SINGLE_QUOTED_STRING_0, grammarAccess.getStringLiteralAccess().getSINGLE_QUOTED_STRINGTerminalRuleCall());

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
    // $ANTLR end "ruleStringLiteral"


    // $ANTLR start "entryRuleUPPER"
    // InternalCompleteOCL.g:7850:1: entryRuleUPPER returns [String current=null] : iv_ruleUPPER= ruleUPPER EOF ;
    public final String entryRuleUPPER() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleUPPER = null;


        try {
            // InternalCompleteOCL.g:7851:2: (iv_ruleUPPER= ruleUPPER EOF )
            // InternalCompleteOCL.g:7852:2: iv_ruleUPPER= ruleUPPER EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getUPPERRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleUPPER=ruleUPPER();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleUPPER.getText();
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
    // $ANTLR end "entryRuleUPPER"


    // $ANTLR start "ruleUPPER"
    // InternalCompleteOCL.g:7859:1: ruleUPPER returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_INT_0= RULE_INT | kw= '*' ) ;
    public final AntlrDatatypeRuleToken ruleUPPER() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_INT_0=null;
        Token kw=null;

         enterRule();

        try {
            // InternalCompleteOCL.g:7862:28: ( (this_INT_0= RULE_INT | kw= '*' ) )
            // InternalCompleteOCL.g:7863:1: (this_INT_0= RULE_INT | kw= '*' )
            {
            // InternalCompleteOCL.g:7863:1: (this_INT_0= RULE_INT | kw= '*' )
            int alt151=2;
            int LA151_0 = input.LA(1);

            if ( (LA151_0==RULE_INT) ) {
                alt151=1;
            }
            else if ( (LA151_0==56) ) {
                alt151=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 151, 0, input);

                throw nvae;
            }
            switch (alt151) {
                case 1 :
                    // InternalCompleteOCL.g:7863:6: this_INT_0= RULE_INT
                    {
                    this_INT_0=(Token)match(input,RULE_INT,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      		current.merge(this_INT_0);

                    }
                    if ( state.backtracking==0 ) {

                          newLeafNode(this_INT_0, grammarAccess.getUPPERAccess().getINTTerminalRuleCall_0());

                    }

                    }
                    break;
                case 2 :
                    // InternalCompleteOCL.g:7872:2: kw= '*'
                    {
                    kw=(Token)match(input,56,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getUPPERAccess().getAsteriskKeyword_1());

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
    // $ANTLR end "ruleUPPER"


    // $ANTLR start "entryRuleURI"
    // InternalCompleteOCL.g:7885:1: entryRuleURI returns [String current=null] : iv_ruleURI= ruleURI EOF ;
    public final String entryRuleURI() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleURI = null;


        try {
            // InternalCompleteOCL.g:7886:2: (iv_ruleURI= ruleURI EOF )
            // InternalCompleteOCL.g:7887:2: iv_ruleURI= ruleURI EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getURIRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleURI=ruleURI();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleURI.getText();
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
    // $ANTLR end "entryRuleURI"


    // $ANTLR start "ruleURI"
    // InternalCompleteOCL.g:7894:1: ruleURI returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_SINGLE_QUOTED_STRING_0= RULE_SINGLE_QUOTED_STRING ;
    public final AntlrDatatypeRuleToken ruleURI() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_SINGLE_QUOTED_STRING_0=null;

         enterRule();

        try {
            // InternalCompleteOCL.g:7897:28: (this_SINGLE_QUOTED_STRING_0= RULE_SINGLE_QUOTED_STRING )
            // InternalCompleteOCL.g:7898:5: this_SINGLE_QUOTED_STRING_0= RULE_SINGLE_QUOTED_STRING
            {
            this_SINGLE_QUOTED_STRING_0=(Token)match(input,RULE_SINGLE_QUOTED_STRING,FollowSets000.FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              		current.merge(this_SINGLE_QUOTED_STRING_0);

            }
            if ( state.backtracking==0 ) {

                  newLeafNode(this_SINGLE_QUOTED_STRING_0, grammarAccess.getURIAccess().getSINGLE_QUOTED_STRINGTerminalRuleCall());

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
    // $ANTLR end "ruleURI"

    // $ANTLR start synpred1_InternalCompleteOCL
    public final void synpred1_InternalCompleteOCL_fragment() throws RecognitionException {
        EObject lv_ownedAnnotations_0_0 = null;


        // InternalCompleteOCL.g:94:1: ( (lv_ownedAnnotations_0_0= ruleCommentCS ) )
        // InternalCompleteOCL.g:94:1: (lv_ownedAnnotations_0_0= ruleCommentCS )
        {
        // InternalCompleteOCL.g:94:1: (lv_ownedAnnotations_0_0= ruleCommentCS )
        // InternalCompleteOCL.g:95:3: lv_ownedAnnotations_0_0= ruleCommentCS
        {
        if ( state.backtracking==0 ) {

          	        newCompositeNode(grammarAccess.getCompleteOCLDocumentCSAccess().getOwnedAnnotationsCommentCSParserRuleCall_0_0());

        }
        pushFollow(FollowSets000.FOLLOW_2);
        lv_ownedAnnotations_0_0=ruleCommentCS();

        state._fsp--;
        if (state.failed) return ;

        }


        }
    }
    // $ANTLR end synpred1_InternalCompleteOCL

    // $ANTLR start synpred20_InternalCompleteOCL
    public final void synpred20_InternalCompleteOCL_fragment() throws RecognitionException {
        EObject this_PropertyContextDeclCS_0 = null;


        // InternalCompleteOCL.g:683:2: (this_PropertyContextDeclCS_0= rulePropertyContextDeclCS )
        // InternalCompleteOCL.g:683:2: this_PropertyContextDeclCS_0= rulePropertyContextDeclCS
        {
        if ( state.backtracking==0 ) {

          	  /* */

        }
        pushFollow(FollowSets000.FOLLOW_2);
        this_PropertyContextDeclCS_0=rulePropertyContextDeclCS();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred20_InternalCompleteOCL

    // $ANTLR start synpred21_InternalCompleteOCL
    public final void synpred21_InternalCompleteOCL_fragment() throws RecognitionException {
        EObject this_ClassifierContextDeclCS_1 = null;


        // InternalCompleteOCL.g:696:2: (this_ClassifierContextDeclCS_1= ruleClassifierContextDeclCS )
        // InternalCompleteOCL.g:696:2: this_ClassifierContextDeclCS_1= ruleClassifierContextDeclCS
        {
        if ( state.backtracking==0 ) {

          	  /* */

        }
        pushFollow(FollowSets000.FOLLOW_2);
        this_ClassifierContextDeclCS_1=ruleClassifierContextDeclCS();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred21_InternalCompleteOCL

    // $ANTLR start synpred147_InternalCompleteOCL
    public final void synpred147_InternalCompleteOCL_fragment() throws RecognitionException {
        EObject this_TypeLiteralCS_1 = null;


        // InternalCompleteOCL.g:4916:2: (this_TypeLiteralCS_1= ruleTypeLiteralCS )
        // InternalCompleteOCL.g:4916:2: this_TypeLiteralCS_1= ruleTypeLiteralCS
        {
        if ( state.backtracking==0 ) {

          	  /* */

        }
        pushFollow(FollowSets000.FOLLOW_2);
        this_TypeLiteralCS_1=ruleTypeLiteralCS();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred147_InternalCompleteOCL

    // $ANTLR start synpred150_InternalCompleteOCL
    public final void synpred150_InternalCompleteOCL_fragment() throws RecognitionException {
        EObject this_PrefixedPrimaryExpCS_0 = null;

        AntlrDatatypeRuleToken lv_name_2_0 = null;

        EObject lv_ownedRight_3_0 = null;


        // InternalCompleteOCL.g:5012:2: ( (this_PrefixedPrimaryExpCS_0= rulePrefixedPrimaryExpCS ( () ( (lv_name_2_0= ruleBinaryOperatorName ) ) ( (lv_ownedRight_3_0= ruleExpCS ) ) )? ) )
        // InternalCompleteOCL.g:5012:2: (this_PrefixedPrimaryExpCS_0= rulePrefixedPrimaryExpCS ( () ( (lv_name_2_0= ruleBinaryOperatorName ) ) ( (lv_ownedRight_3_0= ruleExpCS ) ) )? )
        {
        // InternalCompleteOCL.g:5012:2: (this_PrefixedPrimaryExpCS_0= rulePrefixedPrimaryExpCS ( () ( (lv_name_2_0= ruleBinaryOperatorName ) ) ( (lv_ownedRight_3_0= ruleExpCS ) ) )? )
        // InternalCompleteOCL.g:5013:2: this_PrefixedPrimaryExpCS_0= rulePrefixedPrimaryExpCS ( () ( (lv_name_2_0= ruleBinaryOperatorName ) ) ( (lv_ownedRight_3_0= ruleExpCS ) ) )?
        {
        if ( state.backtracking==0 ) {

          	  /* */

        }
        pushFollow(FollowSets000.FOLLOW_60);
        this_PrefixedPrimaryExpCS_0=rulePrefixedPrimaryExpCS();

        state._fsp--;
        if (state.failed) return ;
        // InternalCompleteOCL.g:5024:1: ( () ( (lv_name_2_0= ruleBinaryOperatorName ) ) ( (lv_ownedRight_3_0= ruleExpCS ) ) )?
        int alt168=2;
        int LA168_0 = input.LA(1);

        if ( ((LA168_0>=18 && LA168_0<=19)||LA168_0==30||(LA168_0>=40 && LA168_0<=41)||LA168_0==53||(LA168_0>=56 && LA168_0<=73)) ) {
            alt168=1;
        }
        switch (alt168) {
            case 1 :
                // InternalCompleteOCL.g:5024:2: () ( (lv_name_2_0= ruleBinaryOperatorName ) ) ( (lv_ownedRight_3_0= ruleExpCS ) )
                {
                // InternalCompleteOCL.g:5024:2: ()
                // InternalCompleteOCL.g:5025:2:
                {
                if ( state.backtracking==0 ) {

                  	  /* */

                }

                }

                // InternalCompleteOCL.g:5033:2: ( (lv_name_2_0= ruleBinaryOperatorName ) )
                // InternalCompleteOCL.g:5034:1: (lv_name_2_0= ruleBinaryOperatorName )
                {
                // InternalCompleteOCL.g:5034:1: (lv_name_2_0= ruleBinaryOperatorName )
                // InternalCompleteOCL.g:5035:3: lv_name_2_0= ruleBinaryOperatorName
                {
                if ( state.backtracking==0 ) {

                  	        newCompositeNode(grammarAccess.getExpCSAccess().getNameBinaryOperatorNameParserRuleCall_0_1_1_0());

                }
                pushFollow(FollowSets000.FOLLOW_50);
                lv_name_2_0=ruleBinaryOperatorName();

                state._fsp--;
                if (state.failed) return ;

                }


                }

                // InternalCompleteOCL.g:5051:2: ( (lv_ownedRight_3_0= ruleExpCS ) )
                // InternalCompleteOCL.g:5052:1: (lv_ownedRight_3_0= ruleExpCS )
                {
                // InternalCompleteOCL.g:5052:1: (lv_ownedRight_3_0= ruleExpCS )
                // InternalCompleteOCL.g:5053:3: lv_ownedRight_3_0= ruleExpCS
                {
                if ( state.backtracking==0 ) {

                  	        newCompositeNode(grammarAccess.getExpCSAccess().getOwnedRightExpCSParserRuleCall_0_1_2_0());

                }
                pushFollow(FollowSets000.FOLLOW_2);
                lv_ownedRight_3_0=ruleExpCS();

                state._fsp--;
                if (state.failed) return ;

                }


                }


                }
                break;

        }


        }


        }
    }
    // $ANTLR end synpred150_InternalCompleteOCL

    // $ANTLR start synpred157_InternalCompleteOCL
    public final void synpred157_InternalCompleteOCL_fragment() throws RecognitionException {
        EObject this_TupleLiteralExpCS_4 = null;


        // InternalCompleteOCL.g:5314:2: (this_TupleLiteralExpCS_4= ruleTupleLiteralExpCS )
        // InternalCompleteOCL.g:5314:2: this_TupleLiteralExpCS_4= ruleTupleLiteralExpCS
        {
        if ( state.backtracking==0 ) {

          	  /* */

        }
        pushFollow(FollowSets000.FOLLOW_2);
        this_TupleLiteralExpCS_4=ruleTupleLiteralExpCS();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred157_InternalCompleteOCL

    // $ANTLR start synpred158_InternalCompleteOCL
    public final void synpred158_InternalCompleteOCL_fragment() throws RecognitionException {
        EObject this_MapLiteralExpCS_5 = null;


        // InternalCompleteOCL.g:5327:2: (this_MapLiteralExpCS_5= ruleMapLiteralExpCS )
        // InternalCompleteOCL.g:5327:2: this_MapLiteralExpCS_5= ruleMapLiteralExpCS
        {
        if ( state.backtracking==0 ) {

          	  /* */

        }
        pushFollow(FollowSets000.FOLLOW_2);
        this_MapLiteralExpCS_5=ruleMapLiteralExpCS();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred158_InternalCompleteOCL

    // $ANTLR start synpred159_InternalCompleteOCL
    public final void synpred159_InternalCompleteOCL_fragment() throws RecognitionException {
        EObject this_CollectionLiteralExpCS_6 = null;


        // InternalCompleteOCL.g:5340:2: (this_CollectionLiteralExpCS_6= ruleCollectionLiteralExpCS )
        // InternalCompleteOCL.g:5340:2: this_CollectionLiteralExpCS_6= ruleCollectionLiteralExpCS
        {
        if ( state.backtracking==0 ) {

          	  /* */

        }
        pushFollow(FollowSets000.FOLLOW_2);
        this_CollectionLiteralExpCS_6=ruleCollectionLiteralExpCS();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred159_InternalCompleteOCL

    // $ANTLR start synpred161_InternalCompleteOCL
    public final void synpred161_InternalCompleteOCL_fragment() throws RecognitionException {
        EObject this_TypeLiteralExpCS_8 = null;


        // InternalCompleteOCL.g:5366:2: (this_TypeLiteralExpCS_8= ruleTypeLiteralExpCS )
        // InternalCompleteOCL.g:5366:2: this_TypeLiteralExpCS_8= ruleTypeLiteralExpCS
        {
        if ( state.backtracking==0 ) {

          	  /* */

        }
        pushFollow(FollowSets000.FOLLOW_2);
        this_TypeLiteralExpCS_8=ruleTypeLiteralExpCS();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred161_InternalCompleteOCL

    // $ANTLR start synpred174_InternalCompleteOCL
    public final void synpred174_InternalCompleteOCL_fragment() throws RecognitionException {
        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_ownedCoIterator_2_0 = null;

        EObject lv_ownedInitExpression_4_0 = null;


        // InternalCompleteOCL.g:5795:3: ( (otherlv_1= '<-' ( (lv_ownedCoIterator_2_0= ruleCoIteratorVariableCS ) ) (otherlv_3= '=' ( (lv_ownedInitExpression_4_0= ruleExpCS ) ) )? ) )
        // InternalCompleteOCL.g:5795:3: (otherlv_1= '<-' ( (lv_ownedCoIterator_2_0= ruleCoIteratorVariableCS ) ) (otherlv_3= '=' ( (lv_ownedInitExpression_4_0= ruleExpCS ) ) )? )
        {
        // InternalCompleteOCL.g:5795:3: (otherlv_1= '<-' ( (lv_ownedCoIterator_2_0= ruleCoIteratorVariableCS ) ) (otherlv_3= '=' ( (lv_ownedInitExpression_4_0= ruleExpCS ) ) )? )
        // InternalCompleteOCL.g:5795:5: otherlv_1= '<-' ( (lv_ownedCoIterator_2_0= ruleCoIteratorVariableCS ) ) (otherlv_3= '=' ( (lv_ownedInitExpression_4_0= ruleExpCS ) ) )?
        {
        otherlv_1=(Token)match(input,87,FollowSets000.FOLLOW_19); if (state.failed) return ;
        // InternalCompleteOCL.g:5799:1: ( (lv_ownedCoIterator_2_0= ruleCoIteratorVariableCS ) )
        // InternalCompleteOCL.g:5800:1: (lv_ownedCoIterator_2_0= ruleCoIteratorVariableCS )
        {
        // InternalCompleteOCL.g:5800:1: (lv_ownedCoIterator_2_0= ruleCoIteratorVariableCS )
        // InternalCompleteOCL.g:5801:3: lv_ownedCoIterator_2_0= ruleCoIteratorVariableCS
        {
        if ( state.backtracking==0 ) {

          	        newCompositeNode(grammarAccess.getNavigatingArgCSAccess().getOwnedCoIteratorCoIteratorVariableCSParserRuleCall_0_1_0_1_0());

        }
        pushFollow(FollowSets000.FOLLOW_70);
        lv_ownedCoIterator_2_0=ruleCoIteratorVariableCS();

        state._fsp--;
        if (state.failed) return ;

        }


        }

        // InternalCompleteOCL.g:5817:2: (otherlv_3= '=' ( (lv_ownedInitExpression_4_0= ruleExpCS ) ) )?
        int alt173=2;
        int LA173_0 = input.LA(1);

        if ( (LA173_0==30) ) {
            alt173=1;
        }
        switch (alt173) {
            case 1 :
                // InternalCompleteOCL.g:5817:4: otherlv_3= '=' ( (lv_ownedInitExpression_4_0= ruleExpCS ) )
                {
                otherlv_3=(Token)match(input,30,FollowSets000.FOLLOW_50); if (state.failed) return ;
                // InternalCompleteOCL.g:5821:1: ( (lv_ownedInitExpression_4_0= ruleExpCS ) )
                // InternalCompleteOCL.g:5822:1: (lv_ownedInitExpression_4_0= ruleExpCS )
                {
                // InternalCompleteOCL.g:5822:1: (lv_ownedInitExpression_4_0= ruleExpCS )
                // InternalCompleteOCL.g:5823:3: lv_ownedInitExpression_4_0= ruleExpCS
                {
                if ( state.backtracking==0 ) {

                  	        newCompositeNode(grammarAccess.getNavigatingArgCSAccess().getOwnedInitExpressionExpCSParserRuleCall_0_1_0_2_1_0());

                }
                pushFollow(FollowSets000.FOLLOW_2);
                lv_ownedInitExpression_4_0=ruleExpCS();

                state._fsp--;
                if (state.failed) return ;

                }


                }


                }
                break;

        }


        }


        }
    }
    // $ANTLR end synpred174_InternalCompleteOCL

    // $ANTLR start synpred177_InternalCompleteOCL
    public final void synpred177_InternalCompleteOCL_fragment() throws RecognitionException {
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_9=null;
        EObject lv_ownedType_6_0 = null;

        EObject lv_ownedCoIterator_8_0 = null;

        EObject lv_ownedInitExpression_10_0 = null;


        // InternalCompleteOCL.g:5840:6: ( (otherlv_5= ':' ( (lv_ownedType_6_0= ruleTypeExpCS ) ) (otherlv_7= '<-' ( (lv_ownedCoIterator_8_0= ruleCoIteratorVariableCS ) ) )? (otherlv_9= '=' ( (lv_ownedInitExpression_10_0= ruleExpCS ) ) )? ) )
        // InternalCompleteOCL.g:5840:6: (otherlv_5= ':' ( (lv_ownedType_6_0= ruleTypeExpCS ) ) (otherlv_7= '<-' ( (lv_ownedCoIterator_8_0= ruleCoIteratorVariableCS ) ) )? (otherlv_9= '=' ( (lv_ownedInitExpression_10_0= ruleExpCS ) ) )? )
        {
        // InternalCompleteOCL.g:5840:6: (otherlv_5= ':' ( (lv_ownedType_6_0= ruleTypeExpCS ) ) (otherlv_7= '<-' ( (lv_ownedCoIterator_8_0= ruleCoIteratorVariableCS ) ) )? (otherlv_9= '=' ( (lv_ownedInitExpression_10_0= ruleExpCS ) ) )? )
        // InternalCompleteOCL.g:5840:8: otherlv_5= ':' ( (lv_ownedType_6_0= ruleTypeExpCS ) ) (otherlv_7= '<-' ( (lv_ownedCoIterator_8_0= ruleCoIteratorVariableCS ) ) )? (otherlv_9= '=' ( (lv_ownedInitExpression_10_0= ruleExpCS ) ) )?
        {
        otherlv_5=(Token)match(input,24,FollowSets000.FOLLOW_25); if (state.failed) return ;
        // InternalCompleteOCL.g:5844:1: ( (lv_ownedType_6_0= ruleTypeExpCS ) )
        // InternalCompleteOCL.g:5845:1: (lv_ownedType_6_0= ruleTypeExpCS )
        {
        // InternalCompleteOCL.g:5845:1: (lv_ownedType_6_0= ruleTypeExpCS )
        // InternalCompleteOCL.g:5846:3: lv_ownedType_6_0= ruleTypeExpCS
        {
        if ( state.backtracking==0 ) {

          	        newCompositeNode(grammarAccess.getNavigatingArgCSAccess().getOwnedTypeTypeExpCSParserRuleCall_0_1_1_1_0());

        }
        pushFollow(FollowSets000.FOLLOW_71);
        lv_ownedType_6_0=ruleTypeExpCS();

        state._fsp--;
        if (state.failed) return ;

        }


        }

        // InternalCompleteOCL.g:5862:2: (otherlv_7= '<-' ( (lv_ownedCoIterator_8_0= ruleCoIteratorVariableCS ) ) )?
        int alt174=2;
        int LA174_0 = input.LA(1);

        if ( (LA174_0==87) ) {
            alt174=1;
        }
        switch (alt174) {
            case 1 :
                // InternalCompleteOCL.g:5862:4: otherlv_7= '<-' ( (lv_ownedCoIterator_8_0= ruleCoIteratorVariableCS ) )
                {
                otherlv_7=(Token)match(input,87,FollowSets000.FOLLOW_19); if (state.failed) return ;
                // InternalCompleteOCL.g:5866:1: ( (lv_ownedCoIterator_8_0= ruleCoIteratorVariableCS ) )
                // InternalCompleteOCL.g:5867:1: (lv_ownedCoIterator_8_0= ruleCoIteratorVariableCS )
                {
                // InternalCompleteOCL.g:5867:1: (lv_ownedCoIterator_8_0= ruleCoIteratorVariableCS )
                // InternalCompleteOCL.g:5868:3: lv_ownedCoIterator_8_0= ruleCoIteratorVariableCS
                {
                if ( state.backtracking==0 ) {

                  	        newCompositeNode(grammarAccess.getNavigatingArgCSAccess().getOwnedCoIteratorCoIteratorVariableCSParserRuleCall_0_1_1_2_1_0());

                }
                pushFollow(FollowSets000.FOLLOW_70);
                lv_ownedCoIterator_8_0=ruleCoIteratorVariableCS();

                state._fsp--;
                if (state.failed) return ;

                }


                }


                }
                break;

        }

        // InternalCompleteOCL.g:5884:4: (otherlv_9= '=' ( (lv_ownedInitExpression_10_0= ruleExpCS ) ) )?
        int alt175=2;
        int LA175_0 = input.LA(1);

        if ( (LA175_0==30) ) {
            alt175=1;
        }
        switch (alt175) {
            case 1 :
                // InternalCompleteOCL.g:5884:6: otherlv_9= '=' ( (lv_ownedInitExpression_10_0= ruleExpCS ) )
                {
                otherlv_9=(Token)match(input,30,FollowSets000.FOLLOW_50); if (state.failed) return ;
                // InternalCompleteOCL.g:5888:1: ( (lv_ownedInitExpression_10_0= ruleExpCS ) )
                // InternalCompleteOCL.g:5889:1: (lv_ownedInitExpression_10_0= ruleExpCS )
                {
                // InternalCompleteOCL.g:5889:1: (lv_ownedInitExpression_10_0= ruleExpCS )
                // InternalCompleteOCL.g:5890:3: lv_ownedInitExpression_10_0= ruleExpCS
                {
                if ( state.backtracking==0 ) {

                  	        newCompositeNode(grammarAccess.getNavigatingArgCSAccess().getOwnedInitExpressionExpCSParserRuleCall_0_1_1_3_1_0());

                }
                pushFollow(FollowSets000.FOLLOW_2);
                lv_ownedInitExpression_10_0=ruleExpCS();

                state._fsp--;
                if (state.failed) return ;

                }


                }


                }
                break;

        }


        }


        }
    }
    // $ANTLR end synpred177_InternalCompleteOCL

    // $ANTLR start synpred180_InternalCompleteOCL
    public final void synpred180_InternalCompleteOCL_fragment() throws RecognitionException {
        Token otherlv_11=null;
        Token otherlv_13=null;
        Token otherlv_15=null;
        EObject lv_ownedType_12_0 = null;

        EObject lv_ownedCoIterator_14_0 = null;

        EObject lv_ownedInitExpression_16_0 = null;


        // InternalCompleteOCL.g:5907:6: ( ( (otherlv_11= ':' ( (lv_ownedType_12_0= ruleTypeExpCS ) ) )? (otherlv_13= '<-' ( (lv_ownedCoIterator_14_0= ruleCoIteratorVariableCS ) ) )? otherlv_15= 'in' ( (lv_ownedInitExpression_16_0= ruleExpCS ) ) ) )
        // InternalCompleteOCL.g:5907:6: ( (otherlv_11= ':' ( (lv_ownedType_12_0= ruleTypeExpCS ) ) )? (otherlv_13= '<-' ( (lv_ownedCoIterator_14_0= ruleCoIteratorVariableCS ) ) )? otherlv_15= 'in' ( (lv_ownedInitExpression_16_0= ruleExpCS ) ) )
        {
        // InternalCompleteOCL.g:5907:6: ( (otherlv_11= ':' ( (lv_ownedType_12_0= ruleTypeExpCS ) ) )? (otherlv_13= '<-' ( (lv_ownedCoIterator_14_0= ruleCoIteratorVariableCS ) ) )? otherlv_15= 'in' ( (lv_ownedInitExpression_16_0= ruleExpCS ) ) )
        // InternalCompleteOCL.g:5907:7: (otherlv_11= ':' ( (lv_ownedType_12_0= ruleTypeExpCS ) ) )? (otherlv_13= '<-' ( (lv_ownedCoIterator_14_0= ruleCoIteratorVariableCS ) ) )? otherlv_15= 'in' ( (lv_ownedInitExpression_16_0= ruleExpCS ) )
        {
        // InternalCompleteOCL.g:5907:7: (otherlv_11= ':' ( (lv_ownedType_12_0= ruleTypeExpCS ) ) )?
        int alt176=2;
        int LA176_0 = input.LA(1);

        if ( (LA176_0==24) ) {
            alt176=1;
        }
        switch (alt176) {
            case 1 :
                // InternalCompleteOCL.g:5907:9: otherlv_11= ':' ( (lv_ownedType_12_0= ruleTypeExpCS ) )
                {
                otherlv_11=(Token)match(input,24,FollowSets000.FOLLOW_25); if (state.failed) return ;
                // InternalCompleteOCL.g:5911:1: ( (lv_ownedType_12_0= ruleTypeExpCS ) )
                // InternalCompleteOCL.g:5912:1: (lv_ownedType_12_0= ruleTypeExpCS )
                {
                // InternalCompleteOCL.g:5912:1: (lv_ownedType_12_0= ruleTypeExpCS )
                // InternalCompleteOCL.g:5913:3: lv_ownedType_12_0= ruleTypeExpCS
                {
                if ( state.backtracking==0 ) {

                  	        newCompositeNode(grammarAccess.getNavigatingArgCSAccess().getOwnedTypeTypeExpCSParserRuleCall_0_1_2_0_1_0());

                }
                pushFollow(FollowSets000.FOLLOW_72);
                lv_ownedType_12_0=ruleTypeExpCS();

                state._fsp--;
                if (state.failed) return ;

                }


                }


                }
                break;

        }

        // InternalCompleteOCL.g:5929:4: (otherlv_13= '<-' ( (lv_ownedCoIterator_14_0= ruleCoIteratorVariableCS ) ) )?
        int alt177=2;
        int LA177_0 = input.LA(1);

        if ( (LA177_0==87) ) {
            alt177=1;
        }
        switch (alt177) {
            case 1 :
                // InternalCompleteOCL.g:5929:6: otherlv_13= '<-' ( (lv_ownedCoIterator_14_0= ruleCoIteratorVariableCS ) )
                {
                otherlv_13=(Token)match(input,87,FollowSets000.FOLLOW_19); if (state.failed) return ;
                // InternalCompleteOCL.g:5933:1: ( (lv_ownedCoIterator_14_0= ruleCoIteratorVariableCS ) )
                // InternalCompleteOCL.g:5934:1: (lv_ownedCoIterator_14_0= ruleCoIteratorVariableCS )
                {
                // InternalCompleteOCL.g:5934:1: (lv_ownedCoIterator_14_0= ruleCoIteratorVariableCS )
                // InternalCompleteOCL.g:5935:3: lv_ownedCoIterator_14_0= ruleCoIteratorVariableCS
                {
                if ( state.backtracking==0 ) {

                  	        newCompositeNode(grammarAccess.getNavigatingArgCSAccess().getOwnedCoIteratorCoIteratorVariableCSParserRuleCall_0_1_2_1_1_0());

                }
                pushFollow(FollowSets000.FOLLOW_73);
                lv_ownedCoIterator_14_0=ruleCoIteratorVariableCS();

                state._fsp--;
                if (state.failed) return ;

                }


                }


                }
                break;

        }

        otherlv_15=(Token)match(input,95,FollowSets000.FOLLOW_50); if (state.failed) return ;
        // InternalCompleteOCL.g:5955:1: ( (lv_ownedInitExpression_16_0= ruleExpCS ) )
        // InternalCompleteOCL.g:5956:1: (lv_ownedInitExpression_16_0= ruleExpCS )
        {
        // InternalCompleteOCL.g:5956:1: (lv_ownedInitExpression_16_0= ruleExpCS )
        // InternalCompleteOCL.g:5957:3: lv_ownedInitExpression_16_0= ruleExpCS
        {
        if ( state.backtracking==0 ) {

          	        newCompositeNode(grammarAccess.getNavigatingArgCSAccess().getOwnedInitExpressionExpCSParserRuleCall_0_1_2_3_0());

        }
        pushFollow(FollowSets000.FOLLOW_2);
        lv_ownedInitExpression_16_0=ruleExpCS();

        state._fsp--;
        if (state.failed) return ;

        }


        }


        }


        }
    }
    // $ANTLR end synpred180_InternalCompleteOCL

    // $ANTLR start synpred185_InternalCompleteOCL
    public final void synpred185_InternalCompleteOCL_fragment() throws RecognitionException {
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_ownedCoIterator_3_0 = null;

        EObject lv_ownedInitExpression_5_0 = null;


        // InternalCompleteOCL.g:6148:3: ( (otherlv_2= '<-' ( (lv_ownedCoIterator_3_0= ruleCoIteratorVariableCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? ) )
        // InternalCompleteOCL.g:6148:3: (otherlv_2= '<-' ( (lv_ownedCoIterator_3_0= ruleCoIteratorVariableCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? )
        {
        // InternalCompleteOCL.g:6148:3: (otherlv_2= '<-' ( (lv_ownedCoIterator_3_0= ruleCoIteratorVariableCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? )
        // InternalCompleteOCL.g:6148:5: otherlv_2= '<-' ( (lv_ownedCoIterator_3_0= ruleCoIteratorVariableCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )?
        {
        otherlv_2=(Token)match(input,87,FollowSets000.FOLLOW_19); if (state.failed) return ;
        // InternalCompleteOCL.g:6152:1: ( (lv_ownedCoIterator_3_0= ruleCoIteratorVariableCS ) )
        // InternalCompleteOCL.g:6153:1: (lv_ownedCoIterator_3_0= ruleCoIteratorVariableCS )
        {
        // InternalCompleteOCL.g:6153:1: (lv_ownedCoIterator_3_0= ruleCoIteratorVariableCS )
        // InternalCompleteOCL.g:6154:3: lv_ownedCoIterator_3_0= ruleCoIteratorVariableCS
        {
        if ( state.backtracking==0 ) {

          	        newCompositeNode(grammarAccess.getNavigatingCommaArgCSAccess().getOwnedCoIteratorCoIteratorVariableCSParserRuleCall_2_0_1_0());

        }
        pushFollow(FollowSets000.FOLLOW_70);
        lv_ownedCoIterator_3_0=ruleCoIteratorVariableCS();

        state._fsp--;
        if (state.failed) return ;

        }


        }

        // InternalCompleteOCL.g:6170:2: (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )?
        int alt185=2;
        int LA185_0 = input.LA(1);

        if ( (LA185_0==30) ) {
            alt185=1;
        }
        switch (alt185) {
            case 1 :
                // InternalCompleteOCL.g:6170:4: otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) )
                {
                otherlv_4=(Token)match(input,30,FollowSets000.FOLLOW_50); if (state.failed) return ;
                // InternalCompleteOCL.g:6174:1: ( (lv_ownedInitExpression_5_0= ruleExpCS ) )
                // InternalCompleteOCL.g:6175:1: (lv_ownedInitExpression_5_0= ruleExpCS )
                {
                // InternalCompleteOCL.g:6175:1: (lv_ownedInitExpression_5_0= ruleExpCS )
                // InternalCompleteOCL.g:6176:3: lv_ownedInitExpression_5_0= ruleExpCS
                {
                if ( state.backtracking==0 ) {

                  	        newCompositeNode(grammarAccess.getNavigatingCommaArgCSAccess().getOwnedInitExpressionExpCSParserRuleCall_2_0_2_1_0());

                }
                pushFollow(FollowSets000.FOLLOW_2);
                lv_ownedInitExpression_5_0=ruleExpCS();

                state._fsp--;
                if (state.failed) return ;

                }


                }


                }
                break;

        }


        }


        }
    }
    // $ANTLR end synpred185_InternalCompleteOCL

    // $ANTLR start synpred188_InternalCompleteOCL
    public final void synpred188_InternalCompleteOCL_fragment() throws RecognitionException {
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        EObject lv_ownedType_7_0 = null;

        EObject lv_ownedCoIterator_9_0 = null;

        EObject lv_ownedInitExpression_11_0 = null;


        // InternalCompleteOCL.g:6193:6: ( (otherlv_6= ':' ( (lv_ownedType_7_0= ruleTypeExpCS ) ) (otherlv_8= '<-' ( (lv_ownedCoIterator_9_0= ruleCoIteratorVariableCS ) ) )? (otherlv_10= '=' ( (lv_ownedInitExpression_11_0= ruleExpCS ) ) )? ) )
        // InternalCompleteOCL.g:6193:6: (otherlv_6= ':' ( (lv_ownedType_7_0= ruleTypeExpCS ) ) (otherlv_8= '<-' ( (lv_ownedCoIterator_9_0= ruleCoIteratorVariableCS ) ) )? (otherlv_10= '=' ( (lv_ownedInitExpression_11_0= ruleExpCS ) ) )? )
        {
        // InternalCompleteOCL.g:6193:6: (otherlv_6= ':' ( (lv_ownedType_7_0= ruleTypeExpCS ) ) (otherlv_8= '<-' ( (lv_ownedCoIterator_9_0= ruleCoIteratorVariableCS ) ) )? (otherlv_10= '=' ( (lv_ownedInitExpression_11_0= ruleExpCS ) ) )? )
        // InternalCompleteOCL.g:6193:8: otherlv_6= ':' ( (lv_ownedType_7_0= ruleTypeExpCS ) ) (otherlv_8= '<-' ( (lv_ownedCoIterator_9_0= ruleCoIteratorVariableCS ) ) )? (otherlv_10= '=' ( (lv_ownedInitExpression_11_0= ruleExpCS ) ) )?
        {
        otherlv_6=(Token)match(input,24,FollowSets000.FOLLOW_25); if (state.failed) return ;
        // InternalCompleteOCL.g:6197:1: ( (lv_ownedType_7_0= ruleTypeExpCS ) )
        // InternalCompleteOCL.g:6198:1: (lv_ownedType_7_0= ruleTypeExpCS )
        {
        // InternalCompleteOCL.g:6198:1: (lv_ownedType_7_0= ruleTypeExpCS )
        // InternalCompleteOCL.g:6199:3: lv_ownedType_7_0= ruleTypeExpCS
        {
        if ( state.backtracking==0 ) {

          	        newCompositeNode(grammarAccess.getNavigatingCommaArgCSAccess().getOwnedTypeTypeExpCSParserRuleCall_2_1_1_0());

        }
        pushFollow(FollowSets000.FOLLOW_71);
        lv_ownedType_7_0=ruleTypeExpCS();

        state._fsp--;
        if (state.failed) return ;

        }


        }

        // InternalCompleteOCL.g:6215:2: (otherlv_8= '<-' ( (lv_ownedCoIterator_9_0= ruleCoIteratorVariableCS ) ) )?
        int alt186=2;
        int LA186_0 = input.LA(1);

        if ( (LA186_0==87) ) {
            alt186=1;
        }
        switch (alt186) {
            case 1 :
                // InternalCompleteOCL.g:6215:4: otherlv_8= '<-' ( (lv_ownedCoIterator_9_0= ruleCoIteratorVariableCS ) )
                {
                otherlv_8=(Token)match(input,87,FollowSets000.FOLLOW_19); if (state.failed) return ;
                // InternalCompleteOCL.g:6219:1: ( (lv_ownedCoIterator_9_0= ruleCoIteratorVariableCS ) )
                // InternalCompleteOCL.g:6220:1: (lv_ownedCoIterator_9_0= ruleCoIteratorVariableCS )
                {
                // InternalCompleteOCL.g:6220:1: (lv_ownedCoIterator_9_0= ruleCoIteratorVariableCS )
                // InternalCompleteOCL.g:6221:3: lv_ownedCoIterator_9_0= ruleCoIteratorVariableCS
                {
                if ( state.backtracking==0 ) {

                  	        newCompositeNode(grammarAccess.getNavigatingCommaArgCSAccess().getOwnedCoIteratorCoIteratorVariableCSParserRuleCall_2_1_2_1_0());

                }
                pushFollow(FollowSets000.FOLLOW_70);
                lv_ownedCoIterator_9_0=ruleCoIteratorVariableCS();

                state._fsp--;
                if (state.failed) return ;

                }


                }


                }
                break;

        }

        // InternalCompleteOCL.g:6237:4: (otherlv_10= '=' ( (lv_ownedInitExpression_11_0= ruleExpCS ) ) )?
        int alt187=2;
        int LA187_0 = input.LA(1);

        if ( (LA187_0==30) ) {
            alt187=1;
        }
        switch (alt187) {
            case 1 :
                // InternalCompleteOCL.g:6237:6: otherlv_10= '=' ( (lv_ownedInitExpression_11_0= ruleExpCS ) )
                {
                otherlv_10=(Token)match(input,30,FollowSets000.FOLLOW_50); if (state.failed) return ;
                // InternalCompleteOCL.g:6241:1: ( (lv_ownedInitExpression_11_0= ruleExpCS ) )
                // InternalCompleteOCL.g:6242:1: (lv_ownedInitExpression_11_0= ruleExpCS )
                {
                // InternalCompleteOCL.g:6242:1: (lv_ownedInitExpression_11_0= ruleExpCS )
                // InternalCompleteOCL.g:6243:3: lv_ownedInitExpression_11_0= ruleExpCS
                {
                if ( state.backtracking==0 ) {

                  	        newCompositeNode(grammarAccess.getNavigatingCommaArgCSAccess().getOwnedInitExpressionExpCSParserRuleCall_2_1_3_1_0());

                }
                pushFollow(FollowSets000.FOLLOW_2);
                lv_ownedInitExpression_11_0=ruleExpCS();

                state._fsp--;
                if (state.failed) return ;

                }


                }


                }
                break;

        }


        }


        }
    }
    // $ANTLR end synpred188_InternalCompleteOCL

    // $ANTLR start synpred191_InternalCompleteOCL
    public final void synpred191_InternalCompleteOCL_fragment() throws RecognitionException {
        Token otherlv_12=null;
        Token otherlv_14=null;
        Token otherlv_16=null;
        EObject lv_ownedType_13_0 = null;

        EObject lv_ownedCoIterator_15_0 = null;

        EObject lv_ownedInitExpression_17_0 = null;


        // InternalCompleteOCL.g:6260:6: ( ( (otherlv_12= ':' ( (lv_ownedType_13_0= ruleTypeExpCS ) ) )? (otherlv_14= '<-' ( (lv_ownedCoIterator_15_0= ruleCoIteratorVariableCS ) ) )? otherlv_16= 'in' ( (lv_ownedInitExpression_17_0= ruleExpCS ) ) ) )
        // InternalCompleteOCL.g:6260:6: ( (otherlv_12= ':' ( (lv_ownedType_13_0= ruleTypeExpCS ) ) )? (otherlv_14= '<-' ( (lv_ownedCoIterator_15_0= ruleCoIteratorVariableCS ) ) )? otherlv_16= 'in' ( (lv_ownedInitExpression_17_0= ruleExpCS ) ) )
        {
        // InternalCompleteOCL.g:6260:6: ( (otherlv_12= ':' ( (lv_ownedType_13_0= ruleTypeExpCS ) ) )? (otherlv_14= '<-' ( (lv_ownedCoIterator_15_0= ruleCoIteratorVariableCS ) ) )? otherlv_16= 'in' ( (lv_ownedInitExpression_17_0= ruleExpCS ) ) )
        // InternalCompleteOCL.g:6260:7: (otherlv_12= ':' ( (lv_ownedType_13_0= ruleTypeExpCS ) ) )? (otherlv_14= '<-' ( (lv_ownedCoIterator_15_0= ruleCoIteratorVariableCS ) ) )? otherlv_16= 'in' ( (lv_ownedInitExpression_17_0= ruleExpCS ) )
        {
        // InternalCompleteOCL.g:6260:7: (otherlv_12= ':' ( (lv_ownedType_13_0= ruleTypeExpCS ) ) )?
        int alt188=2;
        int LA188_0 = input.LA(1);

        if ( (LA188_0==24) ) {
            alt188=1;
        }
        switch (alt188) {
            case 1 :
                // InternalCompleteOCL.g:6260:9: otherlv_12= ':' ( (lv_ownedType_13_0= ruleTypeExpCS ) )
                {
                otherlv_12=(Token)match(input,24,FollowSets000.FOLLOW_25); if (state.failed) return ;
                // InternalCompleteOCL.g:6264:1: ( (lv_ownedType_13_0= ruleTypeExpCS ) )
                // InternalCompleteOCL.g:6265:1: (lv_ownedType_13_0= ruleTypeExpCS )
                {
                // InternalCompleteOCL.g:6265:1: (lv_ownedType_13_0= ruleTypeExpCS )
                // InternalCompleteOCL.g:6266:3: lv_ownedType_13_0= ruleTypeExpCS
                {
                if ( state.backtracking==0 ) {

                  	        newCompositeNode(grammarAccess.getNavigatingCommaArgCSAccess().getOwnedTypeTypeExpCSParserRuleCall_2_2_0_1_0());

                }
                pushFollow(FollowSets000.FOLLOW_72);
                lv_ownedType_13_0=ruleTypeExpCS();

                state._fsp--;
                if (state.failed) return ;

                }


                }


                }
                break;

        }

        // InternalCompleteOCL.g:6282:4: (otherlv_14= '<-' ( (lv_ownedCoIterator_15_0= ruleCoIteratorVariableCS ) ) )?
        int alt189=2;
        int LA189_0 = input.LA(1);

        if ( (LA189_0==87) ) {
            alt189=1;
        }
        switch (alt189) {
            case 1 :
                // InternalCompleteOCL.g:6282:6: otherlv_14= '<-' ( (lv_ownedCoIterator_15_0= ruleCoIteratorVariableCS ) )
                {
                otherlv_14=(Token)match(input,87,FollowSets000.FOLLOW_19); if (state.failed) return ;
                // InternalCompleteOCL.g:6286:1: ( (lv_ownedCoIterator_15_0= ruleCoIteratorVariableCS ) )
                // InternalCompleteOCL.g:6287:1: (lv_ownedCoIterator_15_0= ruleCoIteratorVariableCS )
                {
                // InternalCompleteOCL.g:6287:1: (lv_ownedCoIterator_15_0= ruleCoIteratorVariableCS )
                // InternalCompleteOCL.g:6288:3: lv_ownedCoIterator_15_0= ruleCoIteratorVariableCS
                {
                if ( state.backtracking==0 ) {

                  	        newCompositeNode(grammarAccess.getNavigatingCommaArgCSAccess().getOwnedCoIteratorCoIteratorVariableCSParserRuleCall_2_2_1_1_0());

                }
                pushFollow(FollowSets000.FOLLOW_73);
                lv_ownedCoIterator_15_0=ruleCoIteratorVariableCS();

                state._fsp--;
                if (state.failed) return ;

                }


                }


                }
                break;

        }

        otherlv_16=(Token)match(input,95,FollowSets000.FOLLOW_50); if (state.failed) return ;
        // InternalCompleteOCL.g:6308:1: ( (lv_ownedInitExpression_17_0= ruleExpCS ) )
        // InternalCompleteOCL.g:6309:1: (lv_ownedInitExpression_17_0= ruleExpCS )
        {
        // InternalCompleteOCL.g:6309:1: (lv_ownedInitExpression_17_0= ruleExpCS )
        // InternalCompleteOCL.g:6310:3: lv_ownedInitExpression_17_0= ruleExpCS
        {
        if ( state.backtracking==0 ) {

          	        newCompositeNode(grammarAccess.getNavigatingCommaArgCSAccess().getOwnedInitExpressionExpCSParserRuleCall_2_2_3_0());

        }
        pushFollow(FollowSets000.FOLLOW_2);
        lv_ownedInitExpression_17_0=ruleExpCS();

        state._fsp--;
        if (state.failed) return ;

        }


        }


        }


        }
    }
    // $ANTLR end synpred191_InternalCompleteOCL

    // Delegated rules

    public final boolean synpred147_InternalCompleteOCL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred147_InternalCompleteOCL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred161_InternalCompleteOCL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred161_InternalCompleteOCL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred150_InternalCompleteOCL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred150_InternalCompleteOCL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred158_InternalCompleteOCL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred158_InternalCompleteOCL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred185_InternalCompleteOCL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred185_InternalCompleteOCL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred159_InternalCompleteOCL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred159_InternalCompleteOCL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred177_InternalCompleteOCL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred177_InternalCompleteOCL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred180_InternalCompleteOCL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred180_InternalCompleteOCL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred188_InternalCompleteOCL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred188_InternalCompleteOCL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred1_InternalCompleteOCL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred1_InternalCompleteOCL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred191_InternalCompleteOCL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred191_InternalCompleteOCL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred20_InternalCompleteOCL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred20_InternalCompleteOCL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred174_InternalCompleteOCL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred174_InternalCompleteOCL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred21_InternalCompleteOCL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred21_InternalCompleteOCL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred157_InternalCompleteOCL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred157_InternalCompleteOCL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }


    protected DFA19 dfa19 = new DFA19(this);
    protected DFA98 dfa98 = new DFA98(this);
    protected DFA101 dfa101 = new DFA101(this);
    protected DFA104 dfa104 = new DFA104(this);
    protected DFA120 dfa120 = new DFA120(this);
    protected DFA129 dfa129 = new DFA129(this);
    static final String dfa_1s = "\21\uffff";
    static final String dfa_2s = "\1\5\1\33\1\34\1\6\5\30\1\6\1\uffff\5\26\1\uffff";
    static final String dfa_3s = "\3\34\1\41\5\30\1\50\1\uffff\5\30\1\uffff";
    static final String dfa_4s = "\12\uffff\1\1\5\uffff\1\2";
    static final String dfa_5s = "\21\uffff}>";
    static final String[] dfa_6s = {
            "\1\1\25\uffff\1\2\1\3",
            "\1\2\1\3",
            "\1\3",
            "\1\4\1\5\20\uffff\1\11\6\uffff\1\6\1\7\1\10",
            "\1\11",
            "\1\11",
            "\1\11",
            "\1\11",
            "\1\11",
            "\1\13\1\14\16\uffff\1\12\10\uffff\1\15\1\16\1\17\6\uffff\1\12",
            "",
            "\1\12\1\uffff\1\20",
            "\1\12\1\uffff\1\20",
            "\1\12\1\uffff\1\20",
            "\1\12\1\uffff\1\20",
            "\1\12\1\uffff\1\20",
            ""
    };

    static final short[] dfa_1 = DFA.unpackEncodedString(dfa_1s);
    static final char[] dfa_2 = DFA.unpackEncodedStringToUnsignedChars(dfa_2s);
    static final char[] dfa_3 = DFA.unpackEncodedStringToUnsignedChars(dfa_3s);
    static final short[] dfa_4 = DFA.unpackEncodedString(dfa_4s);
    static final short[] dfa_5 = DFA.unpackEncodedString(dfa_5s);
    static final short[][] dfa_6 = unpackEncodedStringArray(dfa_6s);

    class DFA19 extends DFA {

        public DFA19(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 19;
            this.eot = dfa_1;
            this.eof = dfa_1;
            this.min = dfa_2;
            this.max = dfa_3;
            this.accept = dfa_4;
            this.special = dfa_5;
            this.transition = dfa_6;
        }
        public String getDescription() {
            return "741:1: (this_DefOperationCS_0= ruleDefOperationCS | this_DefPropertyCS_1= ruleDefPropertyCS )";
        }
    }
    static final String dfa_7s = "\30\uffff";
    static final String dfa_8s = "\1\6\17\uffff\5\0\3\uffff";
    static final String dfa_9s = "\1\121\17\uffff\5\0\3\uffff";
    static final String dfa_10s = "\1\uffff\1\1\4\uffff\1\2\20\uffff\1\3";
    static final String dfa_11s = "\20\uffff\1\0\1\1\1\2\1\3\1\4\3\uffff}>";
    static final String[] dfa_12s = {
            "\2\1\27\uffff\3\1\11\uffff\12\6\25\uffff\2\6\1\uffff\1\20\1\21\1\22\1\23\1\24",
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
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "",
            "",
            ""
    };

    static final short[] dfa_7 = DFA.unpackEncodedString(dfa_7s);
    static final char[] dfa_8 = DFA.unpackEncodedStringToUnsignedChars(dfa_8s);
    static final char[] dfa_9 = DFA.unpackEncodedStringToUnsignedChars(dfa_9s);
    static final short[] dfa_10 = DFA.unpackEncodedString(dfa_10s);
    static final short[] dfa_11 = DFA.unpackEncodedString(dfa_11s);
    static final short[][] dfa_12 = unpackEncodedStringArray(dfa_12s);

    class DFA98 extends DFA {

        public DFA98(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 98;
            this.eot = dfa_7;
            this.eof = dfa_7;
            this.min = dfa_8;
            this.max = dfa_9;
            this.accept = dfa_10;
            this.special = dfa_11;
            this.transition = dfa_12;
        }
        public String getDescription() {
            return "4902:1: (this_TypeNameExpCS_0= ruleTypeNameExpCS | this_TypeLiteralCS_1= ruleTypeLiteralCS | this_CollectionPatternCS_2= ruleCollectionPatternCS )";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 :
                        int LA98_16 = input.LA(1);


                        int index98_16 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred147_InternalCompleteOCL()) ) {s = 6;}

                        else if ( (true) ) {s = 23;}


                        input.seek(index98_16);
                        if ( s>=0 ) return s;
                        break;
                    case 1 :
                        int LA98_17 = input.LA(1);


                        int index98_17 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred147_InternalCompleteOCL()) ) {s = 6;}

                        else if ( (true) ) {s = 23;}


                        input.seek(index98_17);
                        if ( s>=0 ) return s;
                        break;
                    case 2 :
                        int LA98_18 = input.LA(1);


                        int index98_18 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred147_InternalCompleteOCL()) ) {s = 6;}

                        else if ( (true) ) {s = 23;}


                        input.seek(index98_18);
                        if ( s>=0 ) return s;
                        break;
                    case 3 :
                        int LA98_19 = input.LA(1);


                        int index98_19 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred147_InternalCompleteOCL()) ) {s = 6;}

                        else if ( (true) ) {s = 23;}


                        input.seek(index98_19);
                        if ( s>=0 ) return s;
                        break;
                    case 4 :
                        int LA98_20 = input.LA(1);


                        int index98_20 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred147_InternalCompleteOCL()) ) {s = 6;}

                        else if ( (true) ) {s = 23;}


                        input.seek(index98_20);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 98, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String dfa_13s = "\46\uffff";
    static final String dfa_14s = "\1\6\3\0\42\uffff";
    static final String dfa_15s = "\1\150\3\0\42\uffff";
    static final String dfa_16s = "\4\uffff\1\1\40\uffff\1\2";
    static final String dfa_17s = "\1\uffff\1\0\1\1\1\2\42\uffff}>";
    static final String[] dfa_18s = {
            "\4\4\14\uffff\1\4\10\uffff\3\4\11\uffff\12\4\1\1\1\2\1\3\1\4\21\uffff\2\4\1\uffff\5\4\4\uffff\1\4\1\uffff\4\4\6\uffff\1\4\4\uffff\1\45\1\4",
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
            "",
            "",
            "",
            ""
    };

    static final short[] dfa_13 = DFA.unpackEncodedString(dfa_13s);
    static final char[] dfa_14 = DFA.unpackEncodedStringToUnsignedChars(dfa_14s);
    static final char[] dfa_15 = DFA.unpackEncodedStringToUnsignedChars(dfa_15s);
    static final short[] dfa_16 = DFA.unpackEncodedString(dfa_16s);
    static final short[] dfa_17 = DFA.unpackEncodedString(dfa_17s);
    static final short[][] dfa_18 = unpackEncodedStringArray(dfa_18s);

    class DFA101 extends DFA {

        public DFA101(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 101;
            this.eot = dfa_13;
            this.eof = dfa_13;
            this.min = dfa_14;
            this.max = dfa_15;
            this.accept = dfa_16;
            this.special = dfa_17;
            this.transition = dfa_18;
        }
        public String getDescription() {
            return "5012:1: ( (this_PrefixedPrimaryExpCS_0= rulePrefixedPrimaryExpCS ( () ( (lv_name_2_0= ruleBinaryOperatorName ) ) ( (lv_ownedRight_3_0= ruleExpCS ) ) )? ) | this_PrefixedLetExpCS_4= rulePrefixedLetExpCS )";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 :
                        int LA101_1 = input.LA(1);


                        int index101_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred150_InternalCompleteOCL()) ) {s = 4;}

                        else if ( (true) ) {s = 37;}


                        input.seek(index101_1);
                        if ( s>=0 ) return s;
                        break;
                    case 1 :
                        int LA101_2 = input.LA(1);


                        int index101_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred150_InternalCompleteOCL()) ) {s = 4;}

                        else if ( (true) ) {s = 37;}


                        input.seek(index101_2);
                        if ( s>=0 ) return s;
                        break;
                    case 2 :
                        int LA101_3 = input.LA(1);


                        int index101_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred150_InternalCompleteOCL()) ) {s = 4;}

                        else if ( (true) ) {s = 37;}


                        input.seek(index101_3);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 101, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String dfa_19s = "\45\uffff";
    static final String dfa_20s = "\1\6\12\uffff\7\0\23\uffff";
    static final String dfa_21s = "\1\150\12\uffff\7\0\23\uffff";
    static final String dfa_22s = "\1\uffff\1\1\1\2\1\3\1\4\15\uffff\1\10\1\11\11\uffff\1\12\4\uffff\1\5\1\6\1\7";
    static final String dfa_23s = "\13\uffff\1\0\1\1\1\2\1\3\1\4\1\5\1\6\23\uffff}>";
    static final String[] dfa_24s = {
            "\2\35\2\4\14\uffff\1\1\10\uffff\3\35\11\uffff\12\23\3\uffff\1\4\21\uffff\1\14\1\13\1\uffff\1\15\1\16\1\17\1\20\1\21\4\uffff\1\22\1\uffff\4\4\6\uffff\1\2\5\uffff\1\3",
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
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
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
            "",
            "",
            "",
            ""
    };

    static final short[] dfa_19 = DFA.unpackEncodedString(dfa_19s);
    static final char[] dfa_20 = DFA.unpackEncodedStringToUnsignedChars(dfa_20s);
    static final char[] dfa_21 = DFA.unpackEncodedStringToUnsignedChars(dfa_21s);
    static final short[] dfa_22 = DFA.unpackEncodedString(dfa_22s);
    static final short[] dfa_23 = DFA.unpackEncodedString(dfa_23s);
    static final short[][] dfa_24 = unpackEncodedStringArray(dfa_24s);

    class DFA104 extends DFA {

        public DFA104(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 104;
            this.eot = dfa_19;
            this.eof = dfa_19;
            this.min = dfa_20;
            this.max = dfa_21;
            this.accept = dfa_22;
            this.special = dfa_23;
            this.transition = dfa_24;
        }
        public String getDescription() {
            return "5261:1: (this_NestedExpCS_0= ruleNestedExpCS | this_IfExpCS_1= ruleIfExpCS | this_SelfExpCS_2= ruleSelfExpCS | this_PrimitiveLiteralExpCS_3= rulePrimitiveLiteralExpCS | this_TupleLiteralExpCS_4= ruleTupleLiteralExpCS | this_MapLiteralExpCS_5= ruleMapLiteralExpCS | this_CollectionLiteralExpCS_6= ruleCollectionLiteralExpCS | this_LambdaLiteralExpCS_7= ruleLambdaLiteralExpCS | this_TypeLiteralExpCS_8= ruleTypeLiteralExpCS | this_NameExpCS_9= ruleNameExpCS )";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 :
                        int LA104_11 = input.LA(1);


                        int index104_11 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred157_InternalCompleteOCL()) ) {s = 34;}

                        else if ( (synpred161_InternalCompleteOCL()) ) {s = 19;}


                        input.seek(index104_11);
                        if ( s>=0 ) return s;
                        break;
                    case 1 :
                        int LA104_12 = input.LA(1);


                        int index104_12 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred158_InternalCompleteOCL()) ) {s = 35;}

                        else if ( (synpred161_InternalCompleteOCL()) ) {s = 19;}


                        input.seek(index104_12);
                        if ( s>=0 ) return s;
                        break;
                    case 2 :
                        int LA104_13 = input.LA(1);


                        int index104_13 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred159_InternalCompleteOCL()) ) {s = 36;}

                        else if ( (synpred161_InternalCompleteOCL()) ) {s = 19;}


                        input.seek(index104_13);
                        if ( s>=0 ) return s;
                        break;
                    case 3 :
                        int LA104_14 = input.LA(1);


                        int index104_14 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred159_InternalCompleteOCL()) ) {s = 36;}

                        else if ( (synpred161_InternalCompleteOCL()) ) {s = 19;}


                        input.seek(index104_14);
                        if ( s>=0 ) return s;
                        break;
                    case 4 :
                        int LA104_15 = input.LA(1);


                        int index104_15 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred159_InternalCompleteOCL()) ) {s = 36;}

                        else if ( (synpred161_InternalCompleteOCL()) ) {s = 19;}


                        input.seek(index104_15);
                        if ( s>=0 ) return s;
                        break;
                    case 5 :
                        int LA104_16 = input.LA(1);


                        int index104_16 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred159_InternalCompleteOCL()) ) {s = 36;}

                        else if ( (synpred161_InternalCompleteOCL()) ) {s = 19;}


                        input.seek(index104_16);
                        if ( s>=0 ) return s;
                        break;
                    case 6 :
                        int LA104_17 = input.LA(1);


                        int index104_17 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred159_InternalCompleteOCL()) ) {s = 36;}

                        else if ( (synpred161_InternalCompleteOCL()) ) {s = 19;}


                        input.seek(index104_17);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 104, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String dfa_25s = "\13\uffff";
    static final String dfa_26s = "\1\4\12\uffff";
    static final String dfa_27s = "\1\27\2\0\10\uffff";
    static final String dfa_28s = "\1\141\2\0\10\uffff";
    static final String dfa_29s = "\3\uffff\1\3\1\4\4\uffff\1\1\1\2";
    static final String dfa_30s = "\1\uffff\1\0\1\1\10\uffff}>";
    static final String[] dfa_31s = {
            "\1\4\1\2\4\uffff\1\4\71\uffff\1\1\7\uffff\1\3\2\4",
            "\1\uffff",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] dfa_25 = DFA.unpackEncodedString(dfa_25s);
    static final short[] dfa_26 = DFA.unpackEncodedString(dfa_26s);
    static final char[] dfa_27 = DFA.unpackEncodedStringToUnsignedChars(dfa_27s);
    static final char[] dfa_28 = DFA.unpackEncodedStringToUnsignedChars(dfa_28s);
    static final short[] dfa_29 = DFA.unpackEncodedString(dfa_29s);
    static final short[] dfa_30 = DFA.unpackEncodedString(dfa_30s);
    static final short[][] dfa_31 = unpackEncodedStringArray(dfa_31s);

    class DFA120 extends DFA {

        public DFA120(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 120;
            this.eot = dfa_25;
            this.eof = dfa_26;
            this.min = dfa_27;
            this.max = dfa_28;
            this.accept = dfa_29;
            this.special = dfa_30;
            this.transition = dfa_31;
        }
        public String getDescription() {
            return "5795:2: ( (otherlv_1= '<-' ( (lv_ownedCoIterator_2_0= ruleCoIteratorVariableCS ) ) (otherlv_3= '=' ( (lv_ownedInitExpression_4_0= ruleExpCS ) ) )? ) | (otherlv_5= ':' ( (lv_ownedType_6_0= ruleTypeExpCS ) ) (otherlv_7= '<-' ( (lv_ownedCoIterator_8_0= ruleCoIteratorVariableCS ) ) )? (otherlv_9= '=' ( (lv_ownedInitExpression_10_0= ruleExpCS ) ) )? ) | ( (otherlv_11= ':' ( (lv_ownedType_12_0= ruleTypeExpCS ) ) )? (otherlv_13= '<-' ( (lv_ownedCoIterator_14_0= ruleCoIteratorVariableCS ) ) )? otherlv_15= 'in' ( (lv_ownedInitExpression_16_0= ruleExpCS ) ) ) )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 :
                        int LA120_1 = input.LA(1);


                        int index120_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred174_InternalCompleteOCL()) ) {s = 9;}

                        else if ( (synpred180_InternalCompleteOCL()) ) {s = 3;}


                        input.seek(index120_1);
                        if ( s>=0 ) return s;
                        break;
                    case 1 :
                        int LA120_2 = input.LA(1);


                        int index120_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred177_InternalCompleteOCL()) ) {s = 10;}

                        else if ( (synpred180_InternalCompleteOCL()) ) {s = 3;}


                        input.seek(index120_2);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 120, _s, input);
            error(nvae);
            throw nvae;
        }
    }

    class DFA129 extends DFA {

        public DFA129(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 129;
            this.eot = dfa_25;
            this.eof = dfa_26;
            this.min = dfa_27;
            this.max = dfa_28;
            this.accept = dfa_29;
            this.special = dfa_30;
            this.transition = dfa_31;
        }
        public String getDescription() {
            return "6148:2: ( (otherlv_2= '<-' ( (lv_ownedCoIterator_3_0= ruleCoIteratorVariableCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? ) | (otherlv_6= ':' ( (lv_ownedType_7_0= ruleTypeExpCS ) ) (otherlv_8= '<-' ( (lv_ownedCoIterator_9_0= ruleCoIteratorVariableCS ) ) )? (otherlv_10= '=' ( (lv_ownedInitExpression_11_0= ruleExpCS ) ) )? ) | ( (otherlv_12= ':' ( (lv_ownedType_13_0= ruleTypeExpCS ) ) )? (otherlv_14= '<-' ( (lv_ownedCoIterator_15_0= ruleCoIteratorVariableCS ) ) )? otherlv_16= 'in' ( (lv_ownedInitExpression_17_0= ruleExpCS ) ) ) )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 :
                        int LA129_1 = input.LA(1);


                        int index129_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred185_InternalCompleteOCL()) ) {s = 9;}

                        else if ( (synpred191_InternalCompleteOCL()) ) {s = 3;}


                        input.seek(index129_1);
                        if ( s>=0 ) return s;
                        break;
                    case 1 :
                        int LA129_2 = input.LA(1);


                        int index129_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred188_InternalCompleteOCL()) ) {s = 10;}

                        else if ( (synpred191_InternalCompleteOCL()) ) {s = 3;}


                        input.seek(index129_2);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 129, _s, input);
            error(nvae);
            throw nvae;
        }
    }



    private static class FollowSets000 {
        public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000B80100022L});
        public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000800100022L});
        public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000100000L});
        public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x00000103804000C0L});
        public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000018200020L});
        public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000018200022L});
        public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000000200000L});
        public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x00000003810000C0L});
        public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000001400000L});
        public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x01FFF903804003D0L,0x000001840F43EC00L});
        public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000000800000L});
        public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000000001000000L});
        public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000000002000000L});
        public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x0000000004000000L});
        public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x0000000018000000L});
        public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x0000000010000000L});
        public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x00000003800000C0L});
        public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x0000000000400000L});
        public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x00000003808000C0L});
        public static final BitSet FOLLOW_22 = new BitSet(new long[]{0x0000000020800000L});
        public static final BitSet FOLLOW_23 = new BitSet(new long[]{0x001FF903C04000C0L,0x000000000003EC00L});
        public static final BitSet FOLLOW_24 = new BitSet(new long[]{0x0000000040000000L});
        public static final BitSet FOLLOW_25 = new BitSet(new long[]{0x001FF903804000C0L,0x000000000003EC00L});
        public static final BitSet FOLLOW_26 = new BitSet(new long[]{0x0000000380000000L});
        public static final BitSet FOLLOW_27 = new BitSet(new long[]{0x00000003800002C0L});
        public static final BitSet FOLLOW_28 = new BitSet(new long[]{0x0000000400000002L});
        public static final BitSet FOLLOW_29 = new BitSet(new long[]{0x001FF90380C000C0L,0x000000000003EC00L});
        public static final BitSet FOLLOW_30 = new BitSet(new long[]{0x001FF923864000E2L,0x000000000003EC00L});
        public static final BitSet FOLLOW_31 = new BitSet(new long[]{0x0000002006000022L});
        public static final BitSet FOLLOW_32 = new BitSet(new long[]{0x0000000800000000L});
        public static final BitSet FOLLOW_33 = new BitSet(new long[]{0x0000001800300020L});
        public static final BitSet FOLLOW_34 = new BitSet(new long[]{0x0000001800100020L});
        public static final BitSet FOLLOW_35 = new BitSet(new long[]{0x000000C000000022L});
        public static final BitSet FOLLOW_36 = new BitSet(new long[]{0x0000002000000000L});
        public static final BitSet FOLLOW_37 = new BitSet(new long[]{0x0000004000000000L});
        public static final BitSet FOLLOW_38 = new BitSet(new long[]{0x0000008000000000L});
        public static final BitSet FOLLOW_39 = new BitSet(new long[]{0x0000020020000000L});
        public static final BitSet FOLLOW_40 = new BitSet(new long[]{0x0000000000000002L,0x0000000000001000L});
        public static final BitSet FOLLOW_41 = new BitSet(new long[]{0x001FF803800000C0L,0x000000000003EC00L});
        public static final BitSet FOLLOW_42 = new BitSet(new long[]{0x0000000000400002L});
        public static final BitSet FOLLOW_43 = new BitSet(new long[]{0x0000000000800000L,0x0000000020000000L});
        public static final BitSet FOLLOW_44 = new BitSet(new long[]{0x0000000020000000L});
        public static final BitSet FOLLOW_45 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
        public static final BitSet FOLLOW_46 = new BitSet(new long[]{0x01FFF903814003C0L,0x000001840F4BEC00L});
        public static final BitSet FOLLOW_47 = new BitSet(new long[]{0x0000000020000000L,0x0000000000080000L});
        public static final BitSet FOLLOW_48 = new BitSet(new long[]{0x01FFF903814003C0L,0x000001840F43EC00L});
        public static final BitSet FOLLOW_49 = new BitSet(new long[]{0x0000000000000002L,0x0000000000100000L});
        public static final BitSet FOLLOW_50 = new BitSet(new long[]{0x01FFF903804003C0L,0x000001840F43EC00L});
        public static final BitSet FOLLOW_51 = new BitSet(new long[]{0x0000000020000000L,0x0000000000200000L});
        public static final BitSet FOLLOW_52 = new BitSet(new long[]{0x00000000000000C0L});
        public static final BitSet FOLLOW_53 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
        public static final BitSet FOLLOW_54 = new BitSet(new long[]{0x01FFF903804003C0L,0x000001840F4BEC00L});
        public static final BitSet FOLLOW_55 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
        public static final BitSet FOLLOW_56 = new BitSet(new long[]{0x0000000041000000L});
        public static final BitSet FOLLOW_57 = new BitSet(new long[]{0x0000000000000202L});
        public static final BitSet FOLLOW_58 = new BitSet(new long[]{0x0000000000000002L,0x0000000020000000L});
        public static final BitSet FOLLOW_59 = new BitSet(new long[]{0x0000000000000002L,0x0000000000040000L});
        public static final BitSet FOLLOW_60 = new BitSet(new long[]{0xFF200300400C0002L,0x00000000000003FFL});
        public static final BitSet FOLLOW_61 = new BitSet(new long[]{0x01FFF903804003C0L,0x000001040F43EC00L});
        public static final BitSet FOLLOW_62 = new BitSet(new long[]{0x0000000000400002L,0x0000000030040000L});
        public static final BitSet FOLLOW_63 = new BitSet(new long[]{0x0000000000000002L,0x0000000010040000L});
        public static final BitSet FOLLOW_64 = new BitSet(new long[]{0x0000000000000002L,0x0000000010000000L});
        public static final BitSet FOLLOW_65 = new BitSet(new long[]{0x00000003800002C0L,0x0000000000080000L});
        public static final BitSet FOLLOW_66 = new BitSet(new long[]{0x01FFFD0381C003C0L,0x000001840F43EC00L});
        public static final BitSet FOLLOW_67 = new BitSet(new long[]{0x0000000020800000L,0x0000000300000000L});
        public static final BitSet FOLLOW_68 = new BitSet(new long[]{0x0000000020000000L,0x0000000040000000L});
        public static final BitSet FOLLOW_69 = new BitSet(new long[]{0x0000000001000002L,0x0000000080800000L});
        public static final BitSet FOLLOW_70 = new BitSet(new long[]{0x0000000040000002L});
        public static final BitSet FOLLOW_71 = new BitSet(new long[]{0x0000000040000002L,0x0000000000800000L});
        public static final BitSet FOLLOW_72 = new BitSet(new long[]{0x0000000000000000L,0x0000000080800000L});
        public static final BitSet FOLLOW_73 = new BitSet(new long[]{0x0000000000000000L,0x0000000080000000L});
        public static final BitSet FOLLOW_74 = new BitSet(new long[]{0x01FFFD03804003C0L,0x000001840F43EC00L});
        public static final BitSet FOLLOW_75 = new BitSet(new long[]{0x0000000001000002L});
        public static final BitSet FOLLOW_76 = new BitSet(new long[]{0x0000000000000000L,0x0000000800000000L});
        public static final BitSet FOLLOW_77 = new BitSet(new long[]{0x0000000000000000L,0x0000005000000000L});
        public static final BitSet FOLLOW_78 = new BitSet(new long[]{0x0000000000000000L,0x0000002000000000L});
        public static final BitSet FOLLOW_79 = new BitSet(new long[]{0x0000000020000000L,0x0000000080000000L});
        public static final BitSet FOLLOW_80 = new BitSet(new long[]{0x0000000041400000L});
        public static final BitSet FOLLOW_81 = new BitSet(new long[]{0x0100000000000100L});
        public static final BitSet FOLLOW_82 = new BitSet(new long[]{0x0500040000000100L});
        public static final BitSet FOLLOW_83 = new BitSet(new long[]{0x0000000000000000L,0x0000060040000000L});
        public static final BitSet FOLLOW_84 = new BitSet(new long[]{0x0000000000000000L,0x0000000040000000L});
        public static final BitSet FOLLOW_85 = new BitSet(new long[]{0x0000000020000002L,0x0000000020000000L});
        public static final BitSet FOLLOW_86 = new BitSet(new long[]{0x001FFD03804000C0L,0x000000000003EC00L});
        public static final BitSet FOLLOW_87 = new BitSet(new long[]{0x0000000000000002L,0x0000080000000000L});
        public static final BitSet FOLLOW_88 = new BitSet(new long[]{0x0000000000000002L,0x0000100000000000L});
    }


}
