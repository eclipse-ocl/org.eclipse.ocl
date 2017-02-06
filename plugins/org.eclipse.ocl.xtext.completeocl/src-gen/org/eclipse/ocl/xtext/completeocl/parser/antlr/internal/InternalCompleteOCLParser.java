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
 * Copyright (c) 2011, 2017 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
@SuppressWarnings("all")
public class InternalCompleteOCLParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_UNQUOTED_STRING", "RULE_SIMPLE_ID", "RULE_ESCAPED_ID", "RULE_INT", "RULE_SINGLE_QUOTED_STRING", "RULE_ESCAPED_CHARACTER", "RULE_LETTER_CHARACTER", "RULE_DOUBLE_QUOTED_STRING", "RULE_ML_SINGLE_QUOTED_STRING", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'^'", "'^^'", "'context'", "'inv'", "'('", "')'", "':'", "'static'", "'def'", "','", "'='", "'import'", "'include'", "'library'", "'::*'", "'pre'", "'post'", "'body'", "'package'", "'endpackage'", "'derive'", "'init'", "'<'", "'>'", "'?'", "'Boolean'", "'Integer'", "'Real'", "'String'", "'UnlimitedNatural'", "'OclAny'", "'OclInvalid'", "'OclMessage'", "'OclState'", "'OclVoid'", "'-'", "'not'", "'*'", "'/'", "'+'", "'>='", "'<='", "'<>'", "'and'", "'or'", "'xor'", "'implies'", "'.'", "'->'", "'?.'", "'?->'", "'Map'", "'Tuple'", "'::'", "'Set'", "'Bag'", "'Sequence'", "'Collection'", "'OrderedSet'", "'{'", "'}'", "'..'", "'++'", "'Lambda'", "'<-'", "'true'", "'false'", "'invalid'", "'null'", "'@'", "'['", "']'", "'in'", "'|'", "';'", "'if'", "'then'", "'else'", "'endif'", "'elseif'", "'let'", "'self'", "'|?'", "'|1'", "'extends'", "'&&'"
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
    public static final int RULE_INT=7;
    public static final int T__66=66;
    public static final int RULE_ML_COMMENT=13;
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
    public static final int RULE_SINGLE_QUOTED_STRING=8;
    public static final int T__48=48;
    public static final int T__49=49;
    public static final int RULE_DOUBLE_QUOTED_STRING=11;
    public static final int T__44=44;
    public static final int T__45=45;
    public static final int T__46=46;
    public static final int T__47=47;
    public static final int RULE_ESCAPED_ID=6;
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
    public static final int T__90=90;
    public static final int RULE_LETTER_CHARACTER=10;
    public static final int T__19=19;
    public static final int T__17=17;
    public static final int T__18=18;
    public static final int T__99=99;
    public static final int RULE_ESCAPED_CHARACTER=9;
    public static final int T__95=95;
    public static final int RULE_ML_SINGLE_QUOTED_STRING=12;
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
    public static final int RULE_SL_COMMENT=14;
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
    public static final int RULE_SIMPLE_ID=5;
    public static final int T__83=83;
    public static final int RULE_WS=15;
    public static final int RULE_ANY_OTHER=16;
    public static final int T__88=88;
    public static final int T__89=89;
    public static final int T__84=84;
    public static final int T__85=85;
    public static final int T__86=86;
    public static final int T__87=87;

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
    // InternalCompleteOCL.g:89:1: ruleCompleteOCLDocumentCS returns [EObject current=null] : ( ( (lv_ownedImports_0_0= ruleImportCS ) )* ( ( (lv_ownedPackages_1_0= rulePackageDeclarationCS ) ) | ( (lv_ownedContexts_2_0= ruleContextDeclCS ) ) )* ) ;
    public final EObject ruleCompleteOCLDocumentCS() throws RecognitionException {
        EObject current = null;

        EObject lv_ownedImports_0_0 = null;

        EObject lv_ownedPackages_1_0 = null;

        EObject lv_ownedContexts_2_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:92:28: ( ( ( (lv_ownedImports_0_0= ruleImportCS ) )* ( ( (lv_ownedPackages_1_0= rulePackageDeclarationCS ) ) | ( (lv_ownedContexts_2_0= ruleContextDeclCS ) ) )* ) )
            // InternalCompleteOCL.g:93:1: ( ( (lv_ownedImports_0_0= ruleImportCS ) )* ( ( (lv_ownedPackages_1_0= rulePackageDeclarationCS ) ) | ( (lv_ownedContexts_2_0= ruleContextDeclCS ) ) )* )
            {
            // InternalCompleteOCL.g:93:1: ( ( (lv_ownedImports_0_0= ruleImportCS ) )* ( ( (lv_ownedPackages_1_0= rulePackageDeclarationCS ) ) | ( (lv_ownedContexts_2_0= ruleContextDeclCS ) ) )* )
            // InternalCompleteOCL.g:93:2: ( (lv_ownedImports_0_0= ruleImportCS ) )* ( ( (lv_ownedPackages_1_0= rulePackageDeclarationCS ) ) | ( (lv_ownedContexts_2_0= ruleContextDeclCS ) ) )*
            {
            // InternalCompleteOCL.g:93:2: ( (lv_ownedImports_0_0= ruleImportCS ) )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0>=28 && LA1_0<=30)) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // InternalCompleteOCL.g:94:1: (lv_ownedImports_0_0= ruleImportCS )
            	    {
            	    // InternalCompleteOCL.g:94:1: (lv_ownedImports_0_0= ruleImportCS )
            	    // InternalCompleteOCL.g:95:3: lv_ownedImports_0_0= ruleImportCS
            	    {
            	    if ( state.backtracking==0 ) {

            	      	        newCompositeNode(grammarAccess.getCompleteOCLDocumentCSAccess().getOwnedImportsImportCSParserRuleCall_0_0());

            	    }
            	    pushFollow(FollowSets000.FOLLOW_3);
            	    lv_ownedImports_0_0=ruleImportCS();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getCompleteOCLDocumentCSRule());
            	      	        }
            	             		add(
            	             			current,
            	             			"ownedImports",
            	              		lv_ownedImports_0_0,
            	              		"org.eclipse.ocl.xtext.completeocl.CompleteOCL.ImportCS");
            	      	        afterParserOrEnumRuleCall();

            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);

            // InternalCompleteOCL.g:111:3: ( ( (lv_ownedPackages_1_0= rulePackageDeclarationCS ) ) | ( (lv_ownedContexts_2_0= ruleContextDeclCS ) ) )*
            loop2:
            do {
                int alt2=3;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==35) ) {
                    alt2=1;
                }
                else if ( (LA2_0==19) ) {
                    alt2=2;
                }


                switch (alt2) {
            	case 1 :
            	    // InternalCompleteOCL.g:111:4: ( (lv_ownedPackages_1_0= rulePackageDeclarationCS ) )
            	    {
            	    // InternalCompleteOCL.g:111:4: ( (lv_ownedPackages_1_0= rulePackageDeclarationCS ) )
            	    // InternalCompleteOCL.g:112:1: (lv_ownedPackages_1_0= rulePackageDeclarationCS )
            	    {
            	    // InternalCompleteOCL.g:112:1: (lv_ownedPackages_1_0= rulePackageDeclarationCS )
            	    // InternalCompleteOCL.g:113:3: lv_ownedPackages_1_0= rulePackageDeclarationCS
            	    {
            	    if ( state.backtracking==0 ) {

            	      	        newCompositeNode(grammarAccess.getCompleteOCLDocumentCSAccess().getOwnedPackagesPackageDeclarationCSParserRuleCall_1_0_0());

            	    }
            	    pushFollow(FollowSets000.FOLLOW_4);
            	    lv_ownedPackages_1_0=rulePackageDeclarationCS();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getCompleteOCLDocumentCSRule());
            	      	        }
            	             		add(
            	             			current,
            	             			"ownedPackages",
            	              		lv_ownedPackages_1_0,
            	              		"org.eclipse.ocl.xtext.completeocl.CompleteOCL.PackageDeclarationCS");
            	      	        afterParserOrEnumRuleCall();

            	    }

            	    }


            	    }


            	    }
            	    break;
            	case 2 :
            	    // InternalCompleteOCL.g:130:6: ( (lv_ownedContexts_2_0= ruleContextDeclCS ) )
            	    {
            	    // InternalCompleteOCL.g:130:6: ( (lv_ownedContexts_2_0= ruleContextDeclCS ) )
            	    // InternalCompleteOCL.g:131:1: (lv_ownedContexts_2_0= ruleContextDeclCS )
            	    {
            	    // InternalCompleteOCL.g:131:1: (lv_ownedContexts_2_0= ruleContextDeclCS )
            	    // InternalCompleteOCL.g:132:3: lv_ownedContexts_2_0= ruleContextDeclCS
            	    {
            	    if ( state.backtracking==0 ) {

            	      	        newCompositeNode(grammarAccess.getCompleteOCLDocumentCSAccess().getOwnedContextsContextDeclCSParserRuleCall_1_1_0());

            	    }
            	    pushFollow(FollowSets000.FOLLOW_4);
            	    lv_ownedContexts_2_0=ruleContextDeclCS();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getCompleteOCLDocumentCSRule());
            	      	        }
            	             		add(
            	             			current,
            	             			"ownedContexts",
            	              		lv_ownedContexts_2_0,
            	              		"org.eclipse.ocl.xtext.completeocl.CompleteOCL.ContextDeclCS");
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
    // InternalCompleteOCL.g:156:1: entryRuleCompleteOCLNavigationOperatorName returns [String current=null] : iv_ruleCompleteOCLNavigationOperatorName= ruleCompleteOCLNavigationOperatorName EOF ;
    public final String entryRuleCompleteOCLNavigationOperatorName() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleCompleteOCLNavigationOperatorName = null;


        try {
            // InternalCompleteOCL.g:157:2: (iv_ruleCompleteOCLNavigationOperatorName= ruleCompleteOCLNavigationOperatorName EOF )
            // InternalCompleteOCL.g:158:2: iv_ruleCompleteOCLNavigationOperatorName= ruleCompleteOCLNavigationOperatorName EOF
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
    // InternalCompleteOCL.g:165:1: ruleCompleteOCLNavigationOperatorName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= '^' | kw= '^^' ) ;
    public final AntlrDatatypeRuleToken ruleCompleteOCLNavigationOperatorName() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule();

        try {
            // InternalCompleteOCL.g:168:28: ( (kw= '^' | kw= '^^' ) )
            // InternalCompleteOCL.g:169:1: (kw= '^' | kw= '^^' )
            {
            // InternalCompleteOCL.g:169:1: (kw= '^' | kw= '^^' )
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==17) ) {
                alt3=1;
            }
            else if ( (LA3_0==18) ) {
                alt3=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }
            switch (alt3) {
                case 1 :
                    // InternalCompleteOCL.g:170:2: kw= '^'
                    {
                    kw=(Token)match(input,17,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getCompleteOCLNavigationOperatorNameAccess().getCircumflexAccentKeyword_0());

                    }

                    }
                    break;
                case 2 :
                    // InternalCompleteOCL.g:177:2: kw= '^^'
                    {
                    kw=(Token)match(input,18,FollowSets000.FOLLOW_2); if (state.failed) return current;
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
    // InternalCompleteOCL.g:190:1: entryRuleClassifierContextDeclCS returns [EObject current=null] : iv_ruleClassifierContextDeclCS= ruleClassifierContextDeclCS EOF ;
    public final EObject entryRuleClassifierContextDeclCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleClassifierContextDeclCS = null;


        try {
            // InternalCompleteOCL.g:191:2: (iv_ruleClassifierContextDeclCS= ruleClassifierContextDeclCS EOF )
            // InternalCompleteOCL.g:192:2: iv_ruleClassifierContextDeclCS= ruleClassifierContextDeclCS EOF
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
    // InternalCompleteOCL.g:199:1: ruleClassifierContextDeclCS returns [EObject current=null] : (otherlv_0= 'context' ( (lv_ownedSignature_1_0= ruleTemplateSignatureCS ) )? ( (lv_selfName_2_0= ruleUnrestrictedName ) )? ( (lv_ownedPathName_3_0= rulePathNameCS ) ) ( (otherlv_4= 'inv' ( (lv_ownedInvariants_5_0= ruleConstraintCS ) ) ) | ( (lv_ownedDefinitions_6_0= ruleDefCS ) ) )+ ) ;
    public final EObject ruleClassifierContextDeclCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_4=null;
        EObject lv_ownedSignature_1_0 = null;

        AntlrDatatypeRuleToken lv_selfName_2_0 = null;

        EObject lv_ownedPathName_3_0 = null;

        EObject lv_ownedInvariants_5_0 = null;

        EObject lv_ownedDefinitions_6_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:202:28: ( (otherlv_0= 'context' ( (lv_ownedSignature_1_0= ruleTemplateSignatureCS ) )? ( (lv_selfName_2_0= ruleUnrestrictedName ) )? ( (lv_ownedPathName_3_0= rulePathNameCS ) ) ( (otherlv_4= 'inv' ( (lv_ownedInvariants_5_0= ruleConstraintCS ) ) ) | ( (lv_ownedDefinitions_6_0= ruleDefCS ) ) )+ ) )
            // InternalCompleteOCL.g:203:1: (otherlv_0= 'context' ( (lv_ownedSignature_1_0= ruleTemplateSignatureCS ) )? ( (lv_selfName_2_0= ruleUnrestrictedName ) )? ( (lv_ownedPathName_3_0= rulePathNameCS ) ) ( (otherlv_4= 'inv' ( (lv_ownedInvariants_5_0= ruleConstraintCS ) ) ) | ( (lv_ownedDefinitions_6_0= ruleDefCS ) ) )+ )
            {
            // InternalCompleteOCL.g:203:1: (otherlv_0= 'context' ( (lv_ownedSignature_1_0= ruleTemplateSignatureCS ) )? ( (lv_selfName_2_0= ruleUnrestrictedName ) )? ( (lv_ownedPathName_3_0= rulePathNameCS ) ) ( (otherlv_4= 'inv' ( (lv_ownedInvariants_5_0= ruleConstraintCS ) ) ) | ( (lv_ownedDefinitions_6_0= ruleDefCS ) ) )+ )
            // InternalCompleteOCL.g:203:3: otherlv_0= 'context' ( (lv_ownedSignature_1_0= ruleTemplateSignatureCS ) )? ( (lv_selfName_2_0= ruleUnrestrictedName ) )? ( (lv_ownedPathName_3_0= rulePathNameCS ) ) ( (otherlv_4= 'inv' ( (lv_ownedInvariants_5_0= ruleConstraintCS ) ) ) | ( (lv_ownedDefinitions_6_0= ruleDefCS ) ) )+
            {
            otherlv_0=(Token)match(input,19,FollowSets000.FOLLOW_5); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getClassifierContextDeclCSAccess().getContextKeyword_0());

            }
            // InternalCompleteOCL.g:207:1: ( (lv_ownedSignature_1_0= ruleTemplateSignatureCS ) )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==21||LA4_0==39) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // InternalCompleteOCL.g:208:1: (lv_ownedSignature_1_0= ruleTemplateSignatureCS )
                    {
                    // InternalCompleteOCL.g:208:1: (lv_ownedSignature_1_0= ruleTemplateSignatureCS )
                    // InternalCompleteOCL.g:209:3: lv_ownedSignature_1_0= ruleTemplateSignatureCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getClassifierContextDeclCSAccess().getOwnedSignatureTemplateSignatureCSParserRuleCall_1_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_5);
                    lv_ownedSignature_1_0=ruleTemplateSignatureCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getClassifierContextDeclCSRule());
                      	        }
                             		set(
                             			current,
                             			"ownedSignature",
                              		lv_ownedSignature_1_0,
                              		"org.eclipse.ocl.xtext.completeocl.CompleteOCL.TemplateSignatureCS");
                      	        afterParserOrEnumRuleCall();

                    }

                    }


                    }
                    break;

            }

            // InternalCompleteOCL.g:225:3: ( (lv_selfName_2_0= ruleUnrestrictedName ) )?
            int alt5=2;
            switch ( input.LA(1) ) {
                case RULE_SIMPLE_ID:
                    {
                    int LA5_1 = input.LA(2);

                    if ( ((LA5_1>=RULE_SIMPLE_ID && LA5_1<=RULE_ESCAPED_ID)||(LA5_1>=28 && LA5_1<=30)) ) {
                        alt5=1;
                    }
                    }
                    break;
                case RULE_ESCAPED_ID:
                    {
                    int LA5_2 = input.LA(2);

                    if ( ((LA5_2>=RULE_SIMPLE_ID && LA5_2<=RULE_ESCAPED_ID)||(LA5_2>=28 && LA5_2<=30)) ) {
                        alt5=1;
                    }
                    }
                    break;
                case 28:
                    {
                    int LA5_3 = input.LA(2);

                    if ( ((LA5_3>=RULE_SIMPLE_ID && LA5_3<=RULE_ESCAPED_ID)||(LA5_3>=28 && LA5_3<=30)) ) {
                        alt5=1;
                    }
                    }
                    break;
                case 29:
                    {
                    int LA5_4 = input.LA(2);

                    if ( ((LA5_4>=RULE_SIMPLE_ID && LA5_4<=RULE_ESCAPED_ID)||(LA5_4>=28 && LA5_4<=30)) ) {
                        alt5=1;
                    }
                    }
                    break;
                case 30:
                    {
                    int LA5_5 = input.LA(2);

                    if ( ((LA5_5>=RULE_SIMPLE_ID && LA5_5<=RULE_ESCAPED_ID)||(LA5_5>=28 && LA5_5<=30)) ) {
                        alt5=1;
                    }
                    }
                    break;
            }

            switch (alt5) {
                case 1 :
                    // InternalCompleteOCL.g:226:1: (lv_selfName_2_0= ruleUnrestrictedName )
                    {
                    // InternalCompleteOCL.g:226:1: (lv_selfName_2_0= ruleUnrestrictedName )
                    // InternalCompleteOCL.g:227:3: lv_selfName_2_0= ruleUnrestrictedName
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getClassifierContextDeclCSAccess().getSelfNameUnrestrictedNameParserRuleCall_2_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_5);
                    lv_selfName_2_0=ruleUnrestrictedName();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getClassifierContextDeclCSRule());
                      	        }
                             		set(
                             			current,
                             			"selfName",
                              		lv_selfName_2_0,
                              		"org.eclipse.ocl.xtext.completeocl.CompleteOCL.UnrestrictedName");
                      	        afterParserOrEnumRuleCall();

                    }

                    }


                    }
                    break;

            }

            // InternalCompleteOCL.g:243:3: ( (lv_ownedPathName_3_0= rulePathNameCS ) )
            // InternalCompleteOCL.g:244:1: (lv_ownedPathName_3_0= rulePathNameCS )
            {
            // InternalCompleteOCL.g:244:1: (lv_ownedPathName_3_0= rulePathNameCS )
            // InternalCompleteOCL.g:245:3: lv_ownedPathName_3_0= rulePathNameCS
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getClassifierContextDeclCSAccess().getOwnedPathNamePathNameCSParserRuleCall_3_0());

            }
            pushFollow(FollowSets000.FOLLOW_6);
            lv_ownedPathName_3_0=rulePathNameCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getClassifierContextDeclCSRule());
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

            // InternalCompleteOCL.g:261:2: ( (otherlv_4= 'inv' ( (lv_ownedInvariants_5_0= ruleConstraintCS ) ) ) | ( (lv_ownedDefinitions_6_0= ruleDefCS ) ) )+
            int cnt6=0;
            loop6:
            do {
                int alt6=3;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==20) ) {
                    alt6=1;
                }
                else if ( ((LA6_0>=24 && LA6_0<=25)) ) {
                    alt6=2;
                }


                switch (alt6) {
            	case 1 :
            	    // InternalCompleteOCL.g:261:3: (otherlv_4= 'inv' ( (lv_ownedInvariants_5_0= ruleConstraintCS ) ) )
            	    {
            	    // InternalCompleteOCL.g:261:3: (otherlv_4= 'inv' ( (lv_ownedInvariants_5_0= ruleConstraintCS ) ) )
            	    // InternalCompleteOCL.g:261:5: otherlv_4= 'inv' ( (lv_ownedInvariants_5_0= ruleConstraintCS ) )
            	    {
            	    otherlv_4=(Token)match(input,20,FollowSets000.FOLLOW_7); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_4, grammarAccess.getClassifierContextDeclCSAccess().getInvKeyword_4_0_0());

            	    }
            	    // InternalCompleteOCL.g:265:1: ( (lv_ownedInvariants_5_0= ruleConstraintCS ) )
            	    // InternalCompleteOCL.g:266:1: (lv_ownedInvariants_5_0= ruleConstraintCS )
            	    {
            	    // InternalCompleteOCL.g:266:1: (lv_ownedInvariants_5_0= ruleConstraintCS )
            	    // InternalCompleteOCL.g:267:3: lv_ownedInvariants_5_0= ruleConstraintCS
            	    {
            	    if ( state.backtracking==0 ) {

            	      	        newCompositeNode(grammarAccess.getClassifierContextDeclCSAccess().getOwnedInvariantsConstraintCSParserRuleCall_4_0_1_0());

            	    }
            	    pushFollow(FollowSets000.FOLLOW_8);
            	    lv_ownedInvariants_5_0=ruleConstraintCS();

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
            	              		"org.eclipse.ocl.xtext.completeocl.CompleteOCL.ConstraintCS");
            	      	        afterParserOrEnumRuleCall();

            	    }

            	    }


            	    }


            	    }


            	    }
            	    break;
            	case 2 :
            	    // InternalCompleteOCL.g:284:6: ( (lv_ownedDefinitions_6_0= ruleDefCS ) )
            	    {
            	    // InternalCompleteOCL.g:284:6: ( (lv_ownedDefinitions_6_0= ruleDefCS ) )
            	    // InternalCompleteOCL.g:285:1: (lv_ownedDefinitions_6_0= ruleDefCS )
            	    {
            	    // InternalCompleteOCL.g:285:1: (lv_ownedDefinitions_6_0= ruleDefCS )
            	    // InternalCompleteOCL.g:286:3: lv_ownedDefinitions_6_0= ruleDefCS
            	    {
            	    if ( state.backtracking==0 ) {

            	      	        newCompositeNode(grammarAccess.getClassifierContextDeclCSAccess().getOwnedDefinitionsDefCSParserRuleCall_4_1_0());

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
            	    if ( cnt6 >= 1 ) break loop6;
            	    if (state.backtracking>0) {state.failed=true; return current;}
                        EarlyExitException eee =
                            new EarlyExitException(6, input);
                        throw eee;
                }
                cnt6++;
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


    // $ANTLR start "entryRuleConstraintCS"
    // InternalCompleteOCL.g:310:1: entryRuleConstraintCS returns [EObject current=null] : iv_ruleConstraintCS= ruleConstraintCS EOF ;
    public final EObject entryRuleConstraintCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleConstraintCS = null;


        try {
            // InternalCompleteOCL.g:311:2: (iv_ruleConstraintCS= ruleConstraintCS EOF )
            // InternalCompleteOCL.g:312:2: iv_ruleConstraintCS= ruleConstraintCS EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getConstraintCSRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleConstraintCS=ruleConstraintCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleConstraintCS;
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
    // $ANTLR end "entryRuleConstraintCS"


    // $ANTLR start "ruleConstraintCS"
    // InternalCompleteOCL.g:319:1: ruleConstraintCS returns [EObject current=null] : ( ( ( (lv_name_0_0= ruleUnrestrictedName ) ) (otherlv_1= '(' ( (lv_ownedMessageSpecification_2_0= ruleSpecificationCS ) ) otherlv_3= ')' )? )? otherlv_4= ':' ( (lv_ownedSpecification_5_0= ruleSpecificationCS ) ) ) ;
    public final EObject ruleConstraintCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        AntlrDatatypeRuleToken lv_name_0_0 = null;

        EObject lv_ownedMessageSpecification_2_0 = null;

        EObject lv_ownedSpecification_5_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:322:28: ( ( ( ( (lv_name_0_0= ruleUnrestrictedName ) ) (otherlv_1= '(' ( (lv_ownedMessageSpecification_2_0= ruleSpecificationCS ) ) otherlv_3= ')' )? )? otherlv_4= ':' ( (lv_ownedSpecification_5_0= ruleSpecificationCS ) ) ) )
            // InternalCompleteOCL.g:323:1: ( ( ( (lv_name_0_0= ruleUnrestrictedName ) ) (otherlv_1= '(' ( (lv_ownedMessageSpecification_2_0= ruleSpecificationCS ) ) otherlv_3= ')' )? )? otherlv_4= ':' ( (lv_ownedSpecification_5_0= ruleSpecificationCS ) ) )
            {
            // InternalCompleteOCL.g:323:1: ( ( ( (lv_name_0_0= ruleUnrestrictedName ) ) (otherlv_1= '(' ( (lv_ownedMessageSpecification_2_0= ruleSpecificationCS ) ) otherlv_3= ')' )? )? otherlv_4= ':' ( (lv_ownedSpecification_5_0= ruleSpecificationCS ) ) )
            // InternalCompleteOCL.g:323:2: ( ( (lv_name_0_0= ruleUnrestrictedName ) ) (otherlv_1= '(' ( (lv_ownedMessageSpecification_2_0= ruleSpecificationCS ) ) otherlv_3= ')' )? )? otherlv_4= ':' ( (lv_ownedSpecification_5_0= ruleSpecificationCS ) )
            {
            // InternalCompleteOCL.g:323:2: ( ( (lv_name_0_0= ruleUnrestrictedName ) ) (otherlv_1= '(' ( (lv_ownedMessageSpecification_2_0= ruleSpecificationCS ) ) otherlv_3= ')' )? )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( ((LA8_0>=RULE_SIMPLE_ID && LA8_0<=RULE_ESCAPED_ID)||(LA8_0>=28 && LA8_0<=30)) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // InternalCompleteOCL.g:323:3: ( (lv_name_0_0= ruleUnrestrictedName ) ) (otherlv_1= '(' ( (lv_ownedMessageSpecification_2_0= ruleSpecificationCS ) ) otherlv_3= ')' )?
                    {
                    // InternalCompleteOCL.g:323:3: ( (lv_name_0_0= ruleUnrestrictedName ) )
                    // InternalCompleteOCL.g:324:1: (lv_name_0_0= ruleUnrestrictedName )
                    {
                    // InternalCompleteOCL.g:324:1: (lv_name_0_0= ruleUnrestrictedName )
                    // InternalCompleteOCL.g:325:3: lv_name_0_0= ruleUnrestrictedName
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getConstraintCSAccess().getNameUnrestrictedNameParserRuleCall_0_0_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_9);
                    lv_name_0_0=ruleUnrestrictedName();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getConstraintCSRule());
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

                    // InternalCompleteOCL.g:341:2: (otherlv_1= '(' ( (lv_ownedMessageSpecification_2_0= ruleSpecificationCS ) ) otherlv_3= ')' )?
                    int alt7=2;
                    int LA7_0 = input.LA(1);

                    if ( (LA7_0==21) ) {
                        alt7=1;
                    }
                    switch (alt7) {
                        case 1 :
                            // InternalCompleteOCL.g:341:4: otherlv_1= '(' ( (lv_ownedMessageSpecification_2_0= ruleSpecificationCS ) ) otherlv_3= ')'
                            {
                            otherlv_1=(Token)match(input,21,FollowSets000.FOLLOW_10); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                  	newLeafNode(otherlv_1, grammarAccess.getConstraintCSAccess().getLeftParenthesisKeyword_0_1_0());

                            }
                            // InternalCompleteOCL.g:345:1: ( (lv_ownedMessageSpecification_2_0= ruleSpecificationCS ) )
                            // InternalCompleteOCL.g:346:1: (lv_ownedMessageSpecification_2_0= ruleSpecificationCS )
                            {
                            // InternalCompleteOCL.g:346:1: (lv_ownedMessageSpecification_2_0= ruleSpecificationCS )
                            // InternalCompleteOCL.g:347:3: lv_ownedMessageSpecification_2_0= ruleSpecificationCS
                            {
                            if ( state.backtracking==0 ) {

                              	        newCompositeNode(grammarAccess.getConstraintCSAccess().getOwnedMessageSpecificationSpecificationCSParserRuleCall_0_1_1_0());

                            }
                            pushFollow(FollowSets000.FOLLOW_11);
                            lv_ownedMessageSpecification_2_0=ruleSpecificationCS();

                            state._fsp--;
                            if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElementForParent(grammarAccess.getConstraintCSRule());
                              	        }
                                     		set(
                                     			current,
                                     			"ownedMessageSpecification",
                                      		lv_ownedMessageSpecification_2_0,
                                      		"org.eclipse.ocl.xtext.completeocl.CompleteOCL.SpecificationCS");
                              	        afterParserOrEnumRuleCall();

                            }

                            }


                            }

                            otherlv_3=(Token)match(input,22,FollowSets000.FOLLOW_12); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                  	newLeafNode(otherlv_3, grammarAccess.getConstraintCSAccess().getRightParenthesisKeyword_0_1_2());

                            }

                            }
                            break;

                    }


                    }
                    break;

            }

            otherlv_4=(Token)match(input,23,FollowSets000.FOLLOW_10); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_4, grammarAccess.getConstraintCSAccess().getColonKeyword_1());

            }
            // InternalCompleteOCL.g:371:1: ( (lv_ownedSpecification_5_0= ruleSpecificationCS ) )
            // InternalCompleteOCL.g:372:1: (lv_ownedSpecification_5_0= ruleSpecificationCS )
            {
            // InternalCompleteOCL.g:372:1: (lv_ownedSpecification_5_0= ruleSpecificationCS )
            // InternalCompleteOCL.g:373:3: lv_ownedSpecification_5_0= ruleSpecificationCS
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getConstraintCSAccess().getOwnedSpecificationSpecificationCSParserRuleCall_2_0());

            }
            pushFollow(FollowSets000.FOLLOW_2);
            lv_ownedSpecification_5_0=ruleSpecificationCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getConstraintCSRule());
              	        }
                     		set(
                     			current,
                     			"ownedSpecification",
                      		lv_ownedSpecification_5_0,
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
    // $ANTLR end "ruleConstraintCS"


    // $ANTLR start "entryRuleContextDeclCS"
    // InternalCompleteOCL.g:397:1: entryRuleContextDeclCS returns [EObject current=null] : iv_ruleContextDeclCS= ruleContextDeclCS EOF ;
    public final EObject entryRuleContextDeclCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleContextDeclCS = null;


        try {
            // InternalCompleteOCL.g:398:2: (iv_ruleContextDeclCS= ruleContextDeclCS EOF )
            // InternalCompleteOCL.g:399:2: iv_ruleContextDeclCS= ruleContextDeclCS EOF
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
    // InternalCompleteOCL.g:406:1: ruleContextDeclCS returns [EObject current=null] : (this_PropertyContextDeclCS_0= rulePropertyContextDeclCS | this_ClassifierContextDeclCS_1= ruleClassifierContextDeclCS | this_OperationContextDeclCS_2= ruleOperationContextDeclCS ) ;
    public final EObject ruleContextDeclCS() throws RecognitionException {
        EObject current = null;

        EObject this_PropertyContextDeclCS_0 = null;

        EObject this_ClassifierContextDeclCS_1 = null;

        EObject this_OperationContextDeclCS_2 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:409:28: ( (this_PropertyContextDeclCS_0= rulePropertyContextDeclCS | this_ClassifierContextDeclCS_1= ruleClassifierContextDeclCS | this_OperationContextDeclCS_2= ruleOperationContextDeclCS ) )
            // InternalCompleteOCL.g:410:1: (this_PropertyContextDeclCS_0= rulePropertyContextDeclCS | this_ClassifierContextDeclCS_1= ruleClassifierContextDeclCS | this_OperationContextDeclCS_2= ruleOperationContextDeclCS )
            {
            // InternalCompleteOCL.g:410:1: (this_PropertyContextDeclCS_0= rulePropertyContextDeclCS | this_ClassifierContextDeclCS_1= ruleClassifierContextDeclCS | this_OperationContextDeclCS_2= ruleOperationContextDeclCS )
            int alt9=3;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==19) ) {
                int LA9_1 = input.LA(2);

                if ( (synpred11_InternalCompleteOCL()) ) {
                    alt9=1;
                }
                else if ( (synpred12_InternalCompleteOCL()) ) {
                    alt9=2;
                }
                else if ( (true) ) {
                    alt9=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return current;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 9, 1, input);

                    throw nvae;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 0, input);

                throw nvae;
            }
            switch (alt9) {
                case 1 :
                    // InternalCompleteOCL.g:411:2: this_PropertyContextDeclCS_0= rulePropertyContextDeclCS
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
                    // InternalCompleteOCL.g:424:2: this_ClassifierContextDeclCS_1= ruleClassifierContextDeclCS
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
                    // InternalCompleteOCL.g:437:2: this_OperationContextDeclCS_2= ruleOperationContextDeclCS
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
    // InternalCompleteOCL.g:456:1: entryRuleDefCS returns [EObject current=null] : iv_ruleDefCS= ruleDefCS EOF ;
    public final EObject entryRuleDefCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDefCS = null;


        try {
            // InternalCompleteOCL.g:457:2: (iv_ruleDefCS= ruleDefCS EOF )
            // InternalCompleteOCL.g:458:2: iv_ruleDefCS= ruleDefCS EOF
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
    // InternalCompleteOCL.g:465:1: ruleDefCS returns [EObject current=null] : (this_DefOperationCS_0= ruleDefOperationCS | this_DefPropertyCS_1= ruleDefPropertyCS ) ;
    public final EObject ruleDefCS() throws RecognitionException {
        EObject current = null;

        EObject this_DefOperationCS_0 = null;

        EObject this_DefPropertyCS_1 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:468:28: ( (this_DefOperationCS_0= ruleDefOperationCS | this_DefPropertyCS_1= ruleDefPropertyCS ) )
            // InternalCompleteOCL.g:469:1: (this_DefOperationCS_0= ruleDefOperationCS | this_DefPropertyCS_1= ruleDefPropertyCS )
            {
            // InternalCompleteOCL.g:469:1: (this_DefOperationCS_0= ruleDefOperationCS | this_DefPropertyCS_1= ruleDefPropertyCS )
            int alt10=2;
            alt10 = dfa10.predict(input);
            switch (alt10) {
                case 1 :
                    // InternalCompleteOCL.g:470:2: this_DefOperationCS_0= ruleDefOperationCS
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
                    // InternalCompleteOCL.g:483:2: this_DefPropertyCS_1= ruleDefPropertyCS
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
    // InternalCompleteOCL.g:502:1: entryRuleDefOperationCS returns [EObject current=null] : iv_ruleDefOperationCS= ruleDefOperationCS EOF ;
    public final EObject entryRuleDefOperationCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDefOperationCS = null;


        try {
            // InternalCompleteOCL.g:503:2: (iv_ruleDefOperationCS= ruleDefOperationCS EOF )
            // InternalCompleteOCL.g:504:2: iv_ruleDefOperationCS= ruleDefOperationCS EOF
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
    // InternalCompleteOCL.g:511:1: ruleDefOperationCS returns [EObject current=null] : ( ( (lv_isStatic_0_0= 'static' ) )? otherlv_1= 'def' ( ruleUnrestrictedName )? otherlv_3= ':' ( (lv_ownedSignature_4_0= ruleTemplateSignatureCS ) )? ( (lv_name_5_0= ruleUnrestrictedName ) ) otherlv_6= '(' ( ( (lv_ownedParameters_7_0= ruleDefParameterCS ) ) (otherlv_8= ',' ( (lv_ownedParameters_9_0= ruleDefParameterCS ) ) )* )? otherlv_10= ')' otherlv_11= ':' ( (lv_ownedType_12_0= ruleTypeExpCS ) )? otherlv_13= '=' ( (lv_ownedSpecification_14_0= ruleSpecificationCS ) ) ) ;
    public final EObject ruleDefOperationCS() throws RecognitionException {
        EObject current = null;

        Token lv_isStatic_0_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        Token otherlv_11=null;
        Token otherlv_13=null;
        EObject lv_ownedSignature_4_0 = null;

        AntlrDatatypeRuleToken lv_name_5_0 = null;

        EObject lv_ownedParameters_7_0 = null;

        EObject lv_ownedParameters_9_0 = null;

        EObject lv_ownedType_12_0 = null;

        EObject lv_ownedSpecification_14_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:514:28: ( ( ( (lv_isStatic_0_0= 'static' ) )? otherlv_1= 'def' ( ruleUnrestrictedName )? otherlv_3= ':' ( (lv_ownedSignature_4_0= ruleTemplateSignatureCS ) )? ( (lv_name_5_0= ruleUnrestrictedName ) ) otherlv_6= '(' ( ( (lv_ownedParameters_7_0= ruleDefParameterCS ) ) (otherlv_8= ',' ( (lv_ownedParameters_9_0= ruleDefParameterCS ) ) )* )? otherlv_10= ')' otherlv_11= ':' ( (lv_ownedType_12_0= ruleTypeExpCS ) )? otherlv_13= '=' ( (lv_ownedSpecification_14_0= ruleSpecificationCS ) ) ) )
            // InternalCompleteOCL.g:515:1: ( ( (lv_isStatic_0_0= 'static' ) )? otherlv_1= 'def' ( ruleUnrestrictedName )? otherlv_3= ':' ( (lv_ownedSignature_4_0= ruleTemplateSignatureCS ) )? ( (lv_name_5_0= ruleUnrestrictedName ) ) otherlv_6= '(' ( ( (lv_ownedParameters_7_0= ruleDefParameterCS ) ) (otherlv_8= ',' ( (lv_ownedParameters_9_0= ruleDefParameterCS ) ) )* )? otherlv_10= ')' otherlv_11= ':' ( (lv_ownedType_12_0= ruleTypeExpCS ) )? otherlv_13= '=' ( (lv_ownedSpecification_14_0= ruleSpecificationCS ) ) )
            {
            // InternalCompleteOCL.g:515:1: ( ( (lv_isStatic_0_0= 'static' ) )? otherlv_1= 'def' ( ruleUnrestrictedName )? otherlv_3= ':' ( (lv_ownedSignature_4_0= ruleTemplateSignatureCS ) )? ( (lv_name_5_0= ruleUnrestrictedName ) ) otherlv_6= '(' ( ( (lv_ownedParameters_7_0= ruleDefParameterCS ) ) (otherlv_8= ',' ( (lv_ownedParameters_9_0= ruleDefParameterCS ) ) )* )? otherlv_10= ')' otherlv_11= ':' ( (lv_ownedType_12_0= ruleTypeExpCS ) )? otherlv_13= '=' ( (lv_ownedSpecification_14_0= ruleSpecificationCS ) ) )
            // InternalCompleteOCL.g:515:2: ( (lv_isStatic_0_0= 'static' ) )? otherlv_1= 'def' ( ruleUnrestrictedName )? otherlv_3= ':' ( (lv_ownedSignature_4_0= ruleTemplateSignatureCS ) )? ( (lv_name_5_0= ruleUnrestrictedName ) ) otherlv_6= '(' ( ( (lv_ownedParameters_7_0= ruleDefParameterCS ) ) (otherlv_8= ',' ( (lv_ownedParameters_9_0= ruleDefParameterCS ) ) )* )? otherlv_10= ')' otherlv_11= ':' ( (lv_ownedType_12_0= ruleTypeExpCS ) )? otherlv_13= '=' ( (lv_ownedSpecification_14_0= ruleSpecificationCS ) )
            {
            // InternalCompleteOCL.g:515:2: ( (lv_isStatic_0_0= 'static' ) )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==24) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // InternalCompleteOCL.g:516:1: (lv_isStatic_0_0= 'static' )
                    {
                    // InternalCompleteOCL.g:516:1: (lv_isStatic_0_0= 'static' )
                    // InternalCompleteOCL.g:517:3: lv_isStatic_0_0= 'static'
                    {
                    lv_isStatic_0_0=(Token)match(input,24,FollowSets000.FOLLOW_13); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_isStatic_0_0, grammarAccess.getDefOperationCSAccess().getIsStaticStaticKeyword_0_0());

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

            otherlv_1=(Token)match(input,25,FollowSets000.FOLLOW_7); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getDefOperationCSAccess().getDefKeyword_1());

            }
            // InternalCompleteOCL.g:534:1: ( ruleUnrestrictedName )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( ((LA12_0>=RULE_SIMPLE_ID && LA12_0<=RULE_ESCAPED_ID)||(LA12_0>=28 && LA12_0<=30)) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // InternalCompleteOCL.g:535:2: ruleUnrestrictedName
                    {
                    if ( state.backtracking==0 ) {

                      	  /* */

                    }
                    if ( state.backtracking==0 ) {

                              newCompositeNode(grammarAccess.getDefOperationCSAccess().getUnrestrictedNameParserRuleCall_2());

                    }
                    pushFollow(FollowSets000.FOLLOW_12);
                    ruleUnrestrictedName();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              afterParserOrEnumRuleCall();

                    }

                    }
                    break;

            }

            otherlv_3=(Token)match(input,23,FollowSets000.FOLLOW_5); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_3, grammarAccess.getDefOperationCSAccess().getColonKeyword_3());

            }
            // InternalCompleteOCL.g:549:1: ( (lv_ownedSignature_4_0= ruleTemplateSignatureCS ) )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==21||LA13_0==39) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // InternalCompleteOCL.g:550:1: (lv_ownedSignature_4_0= ruleTemplateSignatureCS )
                    {
                    // InternalCompleteOCL.g:550:1: (lv_ownedSignature_4_0= ruleTemplateSignatureCS )
                    // InternalCompleteOCL.g:551:3: lv_ownedSignature_4_0= ruleTemplateSignatureCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getDefOperationCSAccess().getOwnedSignatureTemplateSignatureCSParserRuleCall_4_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_14);
                    lv_ownedSignature_4_0=ruleTemplateSignatureCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getDefOperationCSRule());
                      	        }
                             		set(
                             			current,
                             			"ownedSignature",
                              		lv_ownedSignature_4_0,
                              		"org.eclipse.ocl.xtext.completeocl.CompleteOCL.TemplateSignatureCS");
                      	        afterParserOrEnumRuleCall();

                    }

                    }


                    }
                    break;

            }

            // InternalCompleteOCL.g:567:3: ( (lv_name_5_0= ruleUnrestrictedName ) )
            // InternalCompleteOCL.g:568:1: (lv_name_5_0= ruleUnrestrictedName )
            {
            // InternalCompleteOCL.g:568:1: (lv_name_5_0= ruleUnrestrictedName )
            // InternalCompleteOCL.g:569:3: lv_name_5_0= ruleUnrestrictedName
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getDefOperationCSAccess().getNameUnrestrictedNameParserRuleCall_5_0());

            }
            pushFollow(FollowSets000.FOLLOW_15);
            lv_name_5_0=ruleUnrestrictedName();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getDefOperationCSRule());
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

            otherlv_6=(Token)match(input,21,FollowSets000.FOLLOW_16); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_6, grammarAccess.getDefOperationCSAccess().getLeftParenthesisKeyword_6());

            }
            // InternalCompleteOCL.g:589:1: ( ( (lv_ownedParameters_7_0= ruleDefParameterCS ) ) (otherlv_8= ',' ( (lv_ownedParameters_9_0= ruleDefParameterCS ) ) )* )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( ((LA15_0>=RULE_SIMPLE_ID && LA15_0<=RULE_ESCAPED_ID)||(LA15_0>=28 && LA15_0<=30)) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // InternalCompleteOCL.g:589:2: ( (lv_ownedParameters_7_0= ruleDefParameterCS ) ) (otherlv_8= ',' ( (lv_ownedParameters_9_0= ruleDefParameterCS ) ) )*
                    {
                    // InternalCompleteOCL.g:589:2: ( (lv_ownedParameters_7_0= ruleDefParameterCS ) )
                    // InternalCompleteOCL.g:590:1: (lv_ownedParameters_7_0= ruleDefParameterCS )
                    {
                    // InternalCompleteOCL.g:590:1: (lv_ownedParameters_7_0= ruleDefParameterCS )
                    // InternalCompleteOCL.g:591:3: lv_ownedParameters_7_0= ruleDefParameterCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getDefOperationCSAccess().getOwnedParametersDefParameterCSParserRuleCall_7_0_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_17);
                    lv_ownedParameters_7_0=ruleDefParameterCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getDefOperationCSRule());
                      	        }
                             		add(
                             			current,
                             			"ownedParameters",
                              		lv_ownedParameters_7_0,
                              		"org.eclipse.ocl.xtext.completeocl.CompleteOCL.DefParameterCS");
                      	        afterParserOrEnumRuleCall();

                    }

                    }


                    }

                    // InternalCompleteOCL.g:607:2: (otherlv_8= ',' ( (lv_ownedParameters_9_0= ruleDefParameterCS ) ) )*
                    loop14:
                    do {
                        int alt14=2;
                        int LA14_0 = input.LA(1);

                        if ( (LA14_0==26) ) {
                            alt14=1;
                        }


                        switch (alt14) {
                    	case 1 :
                    	    // InternalCompleteOCL.g:607:4: otherlv_8= ',' ( (lv_ownedParameters_9_0= ruleDefParameterCS ) )
                    	    {
                    	    otherlv_8=(Token)match(input,26,FollowSets000.FOLLOW_14); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_8, grammarAccess.getDefOperationCSAccess().getCommaKeyword_7_1_0());

                    	    }
                    	    // InternalCompleteOCL.g:611:1: ( (lv_ownedParameters_9_0= ruleDefParameterCS ) )
                    	    // InternalCompleteOCL.g:612:1: (lv_ownedParameters_9_0= ruleDefParameterCS )
                    	    {
                    	    // InternalCompleteOCL.g:612:1: (lv_ownedParameters_9_0= ruleDefParameterCS )
                    	    // InternalCompleteOCL.g:613:3: lv_ownedParameters_9_0= ruleDefParameterCS
                    	    {
                    	    if ( state.backtracking==0 ) {

                    	      	        newCompositeNode(grammarAccess.getDefOperationCSAccess().getOwnedParametersDefParameterCSParserRuleCall_7_1_1_0());

                    	    }
                    	    pushFollow(FollowSets000.FOLLOW_17);
                    	    lv_ownedParameters_9_0=ruleDefParameterCS();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = createModelElementForParent(grammarAccess.getDefOperationCSRule());
                    	      	        }
                    	             		add(
                    	             			current,
                    	             			"ownedParameters",
                    	              		lv_ownedParameters_9_0,
                    	              		"org.eclipse.ocl.xtext.completeocl.CompleteOCL.DefParameterCS");
                    	      	        afterParserOrEnumRuleCall();

                    	    }

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop14;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_10=(Token)match(input,22,FollowSets000.FOLLOW_12); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_10, grammarAccess.getDefOperationCSAccess().getRightParenthesisKeyword_8());

            }
            otherlv_11=(Token)match(input,23,FollowSets000.FOLLOW_18); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_11, grammarAccess.getDefOperationCSAccess().getColonKeyword_9());

            }
            // InternalCompleteOCL.g:637:1: ( (lv_ownedType_12_0= ruleTypeExpCS ) )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( ((LA16_0>=RULE_SIMPLE_ID && LA16_0<=RULE_ESCAPED_ID)||(LA16_0>=28 && LA16_0<=30)||(LA16_0>=42 && LA16_0<=51)||(LA16_0>=68 && LA16_0<=69)||(LA16_0>=71 && LA16_0<=75)) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // InternalCompleteOCL.g:638:1: (lv_ownedType_12_0= ruleTypeExpCS )
                    {
                    // InternalCompleteOCL.g:638:1: (lv_ownedType_12_0= ruleTypeExpCS )
                    // InternalCompleteOCL.g:639:3: lv_ownedType_12_0= ruleTypeExpCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getDefOperationCSAccess().getOwnedTypeTypeExpCSParserRuleCall_10_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_19);
                    lv_ownedType_12_0=ruleTypeExpCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getDefOperationCSRule());
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
                    break;

            }

            otherlv_13=(Token)match(input,27,FollowSets000.FOLLOW_10); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_13, grammarAccess.getDefOperationCSAccess().getEqualsSignKeyword_11());

            }
            // InternalCompleteOCL.g:659:1: ( (lv_ownedSpecification_14_0= ruleSpecificationCS ) )
            // InternalCompleteOCL.g:660:1: (lv_ownedSpecification_14_0= ruleSpecificationCS )
            {
            // InternalCompleteOCL.g:660:1: (lv_ownedSpecification_14_0= ruleSpecificationCS )
            // InternalCompleteOCL.g:661:3: lv_ownedSpecification_14_0= ruleSpecificationCS
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getDefOperationCSAccess().getOwnedSpecificationSpecificationCSParserRuleCall_12_0());

            }
            pushFollow(FollowSets000.FOLLOW_2);
            lv_ownedSpecification_14_0=ruleSpecificationCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getDefOperationCSRule());
              	        }
                     		set(
                     			current,
                     			"ownedSpecification",
                      		lv_ownedSpecification_14_0,
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
    // InternalCompleteOCL.g:685:1: entryRuleDefParameterCS returns [EObject current=null] : iv_ruleDefParameterCS= ruleDefParameterCS EOF ;
    public final EObject entryRuleDefParameterCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDefParameterCS = null;


        try {
            // InternalCompleteOCL.g:686:2: (iv_ruleDefParameterCS= ruleDefParameterCS EOF )
            // InternalCompleteOCL.g:687:2: iv_ruleDefParameterCS= ruleDefParameterCS EOF
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
    // InternalCompleteOCL.g:694:1: ruleDefParameterCS returns [EObject current=null] : ( ( (lv_name_0_0= ruleUnrestrictedName ) ) otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) ) ) ;
    public final EObject ruleDefParameterCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_name_0_0 = null;

        EObject lv_ownedType_2_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:697:28: ( ( ( (lv_name_0_0= ruleUnrestrictedName ) ) otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) ) ) )
            // InternalCompleteOCL.g:698:1: ( ( (lv_name_0_0= ruleUnrestrictedName ) ) otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) ) )
            {
            // InternalCompleteOCL.g:698:1: ( ( (lv_name_0_0= ruleUnrestrictedName ) ) otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) ) )
            // InternalCompleteOCL.g:698:2: ( (lv_name_0_0= ruleUnrestrictedName ) ) otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) )
            {
            // InternalCompleteOCL.g:698:2: ( (lv_name_0_0= ruleUnrestrictedName ) )
            // InternalCompleteOCL.g:699:1: (lv_name_0_0= ruleUnrestrictedName )
            {
            // InternalCompleteOCL.g:699:1: (lv_name_0_0= ruleUnrestrictedName )
            // InternalCompleteOCL.g:700:3: lv_name_0_0= ruleUnrestrictedName
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getDefParameterCSAccess().getNameUnrestrictedNameParserRuleCall_0_0());

            }
            pushFollow(FollowSets000.FOLLOW_12);
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

            otherlv_1=(Token)match(input,23,FollowSets000.FOLLOW_20); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getDefParameterCSAccess().getColonKeyword_1());

            }
            // InternalCompleteOCL.g:720:1: ( (lv_ownedType_2_0= ruleTypeExpCS ) )
            // InternalCompleteOCL.g:721:1: (lv_ownedType_2_0= ruleTypeExpCS )
            {
            // InternalCompleteOCL.g:721:1: (lv_ownedType_2_0= ruleTypeExpCS )
            // InternalCompleteOCL.g:722:3: lv_ownedType_2_0= ruleTypeExpCS
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
    // InternalCompleteOCL.g:746:1: entryRuleDefPropertyCS returns [EObject current=null] : iv_ruleDefPropertyCS= ruleDefPropertyCS EOF ;
    public final EObject entryRuleDefPropertyCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDefPropertyCS = null;


        try {
            // InternalCompleteOCL.g:747:2: (iv_ruleDefPropertyCS= ruleDefPropertyCS EOF )
            // InternalCompleteOCL.g:748:2: iv_ruleDefPropertyCS= ruleDefPropertyCS EOF
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
    // InternalCompleteOCL.g:755:1: ruleDefPropertyCS returns [EObject current=null] : ( ( (lv_isStatic_0_0= 'static' ) )? otherlv_1= 'def' ( ruleUnrestrictedName )? otherlv_3= ':' ( (lv_name_4_0= ruleUnrestrictedName ) ) otherlv_5= ':' ( (lv_ownedType_6_0= ruleTypeExpCS ) ) otherlv_7= '=' ( (lv_ownedSpecification_8_0= ruleSpecificationCS ) ) ) ;
    public final EObject ruleDefPropertyCS() throws RecognitionException {
        EObject current = null;

        Token lv_isStatic_0_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        AntlrDatatypeRuleToken lv_name_4_0 = null;

        EObject lv_ownedType_6_0 = null;

        EObject lv_ownedSpecification_8_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:758:28: ( ( ( (lv_isStatic_0_0= 'static' ) )? otherlv_1= 'def' ( ruleUnrestrictedName )? otherlv_3= ':' ( (lv_name_4_0= ruleUnrestrictedName ) ) otherlv_5= ':' ( (lv_ownedType_6_0= ruleTypeExpCS ) ) otherlv_7= '=' ( (lv_ownedSpecification_8_0= ruleSpecificationCS ) ) ) )
            // InternalCompleteOCL.g:759:1: ( ( (lv_isStatic_0_0= 'static' ) )? otherlv_1= 'def' ( ruleUnrestrictedName )? otherlv_3= ':' ( (lv_name_4_0= ruleUnrestrictedName ) ) otherlv_5= ':' ( (lv_ownedType_6_0= ruleTypeExpCS ) ) otherlv_7= '=' ( (lv_ownedSpecification_8_0= ruleSpecificationCS ) ) )
            {
            // InternalCompleteOCL.g:759:1: ( ( (lv_isStatic_0_0= 'static' ) )? otherlv_1= 'def' ( ruleUnrestrictedName )? otherlv_3= ':' ( (lv_name_4_0= ruleUnrestrictedName ) ) otherlv_5= ':' ( (lv_ownedType_6_0= ruleTypeExpCS ) ) otherlv_7= '=' ( (lv_ownedSpecification_8_0= ruleSpecificationCS ) ) )
            // InternalCompleteOCL.g:759:2: ( (lv_isStatic_0_0= 'static' ) )? otherlv_1= 'def' ( ruleUnrestrictedName )? otherlv_3= ':' ( (lv_name_4_0= ruleUnrestrictedName ) ) otherlv_5= ':' ( (lv_ownedType_6_0= ruleTypeExpCS ) ) otherlv_7= '=' ( (lv_ownedSpecification_8_0= ruleSpecificationCS ) )
            {
            // InternalCompleteOCL.g:759:2: ( (lv_isStatic_0_0= 'static' ) )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==24) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // InternalCompleteOCL.g:760:1: (lv_isStatic_0_0= 'static' )
                    {
                    // InternalCompleteOCL.g:760:1: (lv_isStatic_0_0= 'static' )
                    // InternalCompleteOCL.g:761:3: lv_isStatic_0_0= 'static'
                    {
                    lv_isStatic_0_0=(Token)match(input,24,FollowSets000.FOLLOW_13); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_isStatic_0_0, grammarAccess.getDefPropertyCSAccess().getIsStaticStaticKeyword_0_0());

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

            otherlv_1=(Token)match(input,25,FollowSets000.FOLLOW_7); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getDefPropertyCSAccess().getDefKeyword_1());

            }
            // InternalCompleteOCL.g:778:1: ( ruleUnrestrictedName )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( ((LA18_0>=RULE_SIMPLE_ID && LA18_0<=RULE_ESCAPED_ID)||(LA18_0>=28 && LA18_0<=30)) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // InternalCompleteOCL.g:779:2: ruleUnrestrictedName
                    {
                    if ( state.backtracking==0 ) {

                      	  /* */

                    }
                    if ( state.backtracking==0 ) {

                              newCompositeNode(grammarAccess.getDefPropertyCSAccess().getUnrestrictedNameParserRuleCall_2());

                    }
                    pushFollow(FollowSets000.FOLLOW_12);
                    ruleUnrestrictedName();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              afterParserOrEnumRuleCall();

                    }

                    }
                    break;

            }

            otherlv_3=(Token)match(input,23,FollowSets000.FOLLOW_14); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_3, grammarAccess.getDefPropertyCSAccess().getColonKeyword_3());

            }
            // InternalCompleteOCL.g:793:1: ( (lv_name_4_0= ruleUnrestrictedName ) )
            // InternalCompleteOCL.g:794:1: (lv_name_4_0= ruleUnrestrictedName )
            {
            // InternalCompleteOCL.g:794:1: (lv_name_4_0= ruleUnrestrictedName )
            // InternalCompleteOCL.g:795:3: lv_name_4_0= ruleUnrestrictedName
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getDefPropertyCSAccess().getNameUnrestrictedNameParserRuleCall_4_0());

            }
            pushFollow(FollowSets000.FOLLOW_12);
            lv_name_4_0=ruleUnrestrictedName();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getDefPropertyCSRule());
              	        }
                     		set(
                     			current,
                     			"name",
                      		lv_name_4_0,
                      		"org.eclipse.ocl.xtext.completeocl.CompleteOCL.UnrestrictedName");
              	        afterParserOrEnumRuleCall();

            }

            }


            }

            otherlv_5=(Token)match(input,23,FollowSets000.FOLLOW_20); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_5, grammarAccess.getDefPropertyCSAccess().getColonKeyword_5());

            }
            // InternalCompleteOCL.g:815:1: ( (lv_ownedType_6_0= ruleTypeExpCS ) )
            // InternalCompleteOCL.g:816:1: (lv_ownedType_6_0= ruleTypeExpCS )
            {
            // InternalCompleteOCL.g:816:1: (lv_ownedType_6_0= ruleTypeExpCS )
            // InternalCompleteOCL.g:817:3: lv_ownedType_6_0= ruleTypeExpCS
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getDefPropertyCSAccess().getOwnedTypeTypeExpCSParserRuleCall_6_0());

            }
            pushFollow(FollowSets000.FOLLOW_19);
            lv_ownedType_6_0=ruleTypeExpCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getDefPropertyCSRule());
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

            otherlv_7=(Token)match(input,27,FollowSets000.FOLLOW_10); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_7, grammarAccess.getDefPropertyCSAccess().getEqualsSignKeyword_7());

            }
            // InternalCompleteOCL.g:837:1: ( (lv_ownedSpecification_8_0= ruleSpecificationCS ) )
            // InternalCompleteOCL.g:838:1: (lv_ownedSpecification_8_0= ruleSpecificationCS )
            {
            // InternalCompleteOCL.g:838:1: (lv_ownedSpecification_8_0= ruleSpecificationCS )
            // InternalCompleteOCL.g:839:3: lv_ownedSpecification_8_0= ruleSpecificationCS
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getDefPropertyCSAccess().getOwnedSpecificationSpecificationCSParserRuleCall_8_0());

            }
            pushFollow(FollowSets000.FOLLOW_2);
            lv_ownedSpecification_8_0=ruleSpecificationCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getDefPropertyCSRule());
              	        }
                     		set(
                     			current,
                     			"ownedSpecification",
                      		lv_ownedSpecification_8_0,
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
    // InternalCompleteOCL.g:863:1: entryRuleImportCS returns [EObject current=null] : iv_ruleImportCS= ruleImportCS EOF ;
    public final EObject entryRuleImportCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleImportCS = null;


        try {
            // InternalCompleteOCL.g:864:2: (iv_ruleImportCS= ruleImportCS EOF )
            // InternalCompleteOCL.g:865:2: iv_ruleImportCS= ruleImportCS EOF
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
    // InternalCompleteOCL.g:872:1: ruleImportCS returns [EObject current=null] : ( (otherlv_0= 'import' | otherlv_1= 'include' | otherlv_2= 'library' ) ( ( (lv_name_3_0= ruleIdentifier ) ) otherlv_4= ':' )? ( (lv_ownedPathName_5_0= ruleURIPathNameCS ) ) ( (lv_isAll_6_0= '::*' ) )? ) ;
    public final EObject ruleImportCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token lv_isAll_6_0=null;
        AntlrDatatypeRuleToken lv_name_3_0 = null;

        EObject lv_ownedPathName_5_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:875:28: ( ( (otherlv_0= 'import' | otherlv_1= 'include' | otherlv_2= 'library' ) ( ( (lv_name_3_0= ruleIdentifier ) ) otherlv_4= ':' )? ( (lv_ownedPathName_5_0= ruleURIPathNameCS ) ) ( (lv_isAll_6_0= '::*' ) )? ) )
            // InternalCompleteOCL.g:876:1: ( (otherlv_0= 'import' | otherlv_1= 'include' | otherlv_2= 'library' ) ( ( (lv_name_3_0= ruleIdentifier ) ) otherlv_4= ':' )? ( (lv_ownedPathName_5_0= ruleURIPathNameCS ) ) ( (lv_isAll_6_0= '::*' ) )? )
            {
            // InternalCompleteOCL.g:876:1: ( (otherlv_0= 'import' | otherlv_1= 'include' | otherlv_2= 'library' ) ( ( (lv_name_3_0= ruleIdentifier ) ) otherlv_4= ':' )? ( (lv_ownedPathName_5_0= ruleURIPathNameCS ) ) ( (lv_isAll_6_0= '::*' ) )? )
            // InternalCompleteOCL.g:876:2: (otherlv_0= 'import' | otherlv_1= 'include' | otherlv_2= 'library' ) ( ( (lv_name_3_0= ruleIdentifier ) ) otherlv_4= ':' )? ( (lv_ownedPathName_5_0= ruleURIPathNameCS ) ) ( (lv_isAll_6_0= '::*' ) )?
            {
            // InternalCompleteOCL.g:876:2: (otherlv_0= 'import' | otherlv_1= 'include' | otherlv_2= 'library' )
            int alt19=3;
            switch ( input.LA(1) ) {
            case 28:
                {
                alt19=1;
                }
                break;
            case 29:
                {
                alt19=2;
                }
                break;
            case 30:
                {
                alt19=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 19, 0, input);

                throw nvae;
            }

            switch (alt19) {
                case 1 :
                    // InternalCompleteOCL.g:876:4: otherlv_0= 'import'
                    {
                    otherlv_0=(Token)match(input,28,FollowSets000.FOLLOW_21); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_0, grammarAccess.getImportCSAccess().getImportKeyword_0_0());

                    }

                    }
                    break;
                case 2 :
                    // InternalCompleteOCL.g:881:7: otherlv_1= 'include'
                    {
                    otherlv_1=(Token)match(input,29,FollowSets000.FOLLOW_21); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_1, grammarAccess.getImportCSAccess().getIncludeKeyword_0_1());

                    }

                    }
                    break;
                case 3 :
                    // InternalCompleteOCL.g:886:7: otherlv_2= 'library'
                    {
                    otherlv_2=(Token)match(input,30,FollowSets000.FOLLOW_21); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_2, grammarAccess.getImportCSAccess().getLibraryKeyword_0_2());

                    }

                    }
                    break;

            }

            // InternalCompleteOCL.g:890:2: ( ( (lv_name_3_0= ruleIdentifier ) ) otherlv_4= ':' )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==RULE_SIMPLE_ID) ) {
                int LA20_1 = input.LA(2);

                if ( (LA20_1==23) ) {
                    alt20=1;
                }
            }
            else if ( (LA20_0==RULE_ESCAPED_ID) ) {
                int LA20_2 = input.LA(2);

                if ( (LA20_2==23) ) {
                    alt20=1;
                }
            }
            switch (alt20) {
                case 1 :
                    // InternalCompleteOCL.g:890:3: ( (lv_name_3_0= ruleIdentifier ) ) otherlv_4= ':'
                    {
                    // InternalCompleteOCL.g:890:3: ( (lv_name_3_0= ruleIdentifier ) )
                    // InternalCompleteOCL.g:891:1: (lv_name_3_0= ruleIdentifier )
                    {
                    // InternalCompleteOCL.g:891:1: (lv_name_3_0= ruleIdentifier )
                    // InternalCompleteOCL.g:892:3: lv_name_3_0= ruleIdentifier
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getImportCSAccess().getNameIdentifierParserRuleCall_1_0_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_12);
                    lv_name_3_0=ruleIdentifier();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getImportCSRule());
                      	        }
                             		set(
                             			current,
                             			"name",
                              		lv_name_3_0,
                              		"org.eclipse.ocl.xtext.base.Base.Identifier");
                      	        afterParserOrEnumRuleCall();

                    }

                    }


                    }

                    otherlv_4=(Token)match(input,23,FollowSets000.FOLLOW_21); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_4, grammarAccess.getImportCSAccess().getColonKeyword_1_1());

                    }

                    }
                    break;

            }

            // InternalCompleteOCL.g:912:3: ( (lv_ownedPathName_5_0= ruleURIPathNameCS ) )
            // InternalCompleteOCL.g:913:1: (lv_ownedPathName_5_0= ruleURIPathNameCS )
            {
            // InternalCompleteOCL.g:913:1: (lv_ownedPathName_5_0= ruleURIPathNameCS )
            // InternalCompleteOCL.g:914:3: lv_ownedPathName_5_0= ruleURIPathNameCS
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getImportCSAccess().getOwnedPathNameURIPathNameCSParserRuleCall_2_0());

            }
            pushFollow(FollowSets000.FOLLOW_22);
            lv_ownedPathName_5_0=ruleURIPathNameCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getImportCSRule());
              	        }
                     		set(
                     			current,
                     			"ownedPathName",
                      		lv_ownedPathName_5_0,
                      		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.URIPathNameCS");
              	        afterParserOrEnumRuleCall();

            }

            }


            }

            // InternalCompleteOCL.g:930:2: ( (lv_isAll_6_0= '::*' ) )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==31) ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // InternalCompleteOCL.g:931:1: (lv_isAll_6_0= '::*' )
                    {
                    // InternalCompleteOCL.g:931:1: (lv_isAll_6_0= '::*' )
                    // InternalCompleteOCL.g:932:3: lv_isAll_6_0= '::*'
                    {
                    lv_isAll_6_0=(Token)match(input,31,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_isAll_6_0, grammarAccess.getImportCSAccess().getIsAllColonColonAsteriskKeyword_3_0());

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
    // InternalCompleteOCL.g:953:1: entryRuleOperationContextDeclCS returns [EObject current=null] : iv_ruleOperationContextDeclCS= ruleOperationContextDeclCS EOF ;
    public final EObject entryRuleOperationContextDeclCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOperationContextDeclCS = null;


        try {
            // InternalCompleteOCL.g:954:2: (iv_ruleOperationContextDeclCS= ruleOperationContextDeclCS EOF )
            // InternalCompleteOCL.g:955:2: iv_ruleOperationContextDeclCS= ruleOperationContextDeclCS EOF
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
    // InternalCompleteOCL.g:962:1: ruleOperationContextDeclCS returns [EObject current=null] : (otherlv_0= 'context' ( (lv_ownedSignature_1_0= ruleTemplateSignatureCS ) )? ( (lv_ownedPathName_2_0= rulePathNameCS ) ) otherlv_3= '(' ( ( (lv_ownedParameters_4_0= ruleParameterCS ) ) (otherlv_5= ',' ( (lv_ownedParameters_6_0= ruleParameterCS ) ) )* )? otherlv_7= ')' otherlv_8= ':' ( (lv_ownedType_9_0= ruleTypeExpCS ) )? ( (otherlv_10= 'pre' ( (lv_ownedPreconditions_11_0= ruleConstraintCS ) ) ) | (otherlv_12= 'post' ( (lv_ownedPostconditions_13_0= ruleConstraintCS ) ) ) | (otherlv_14= 'body' ( ruleUnrestrictedName )? otherlv_16= ':' ( (lv_ownedBodies_17_0= ruleSpecificationCS ) ) ) )* ) ;
    public final EObject ruleOperationContextDeclCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        Token otherlv_12=null;
        Token otherlv_14=null;
        Token otherlv_16=null;
        EObject lv_ownedSignature_1_0 = null;

        EObject lv_ownedPathName_2_0 = null;

        EObject lv_ownedParameters_4_0 = null;

        EObject lv_ownedParameters_6_0 = null;

        EObject lv_ownedType_9_0 = null;

        EObject lv_ownedPreconditions_11_0 = null;

        EObject lv_ownedPostconditions_13_0 = null;

        EObject lv_ownedBodies_17_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:965:28: ( (otherlv_0= 'context' ( (lv_ownedSignature_1_0= ruleTemplateSignatureCS ) )? ( (lv_ownedPathName_2_0= rulePathNameCS ) ) otherlv_3= '(' ( ( (lv_ownedParameters_4_0= ruleParameterCS ) ) (otherlv_5= ',' ( (lv_ownedParameters_6_0= ruleParameterCS ) ) )* )? otherlv_7= ')' otherlv_8= ':' ( (lv_ownedType_9_0= ruleTypeExpCS ) )? ( (otherlv_10= 'pre' ( (lv_ownedPreconditions_11_0= ruleConstraintCS ) ) ) | (otherlv_12= 'post' ( (lv_ownedPostconditions_13_0= ruleConstraintCS ) ) ) | (otherlv_14= 'body' ( ruleUnrestrictedName )? otherlv_16= ':' ( (lv_ownedBodies_17_0= ruleSpecificationCS ) ) ) )* ) )
            // InternalCompleteOCL.g:966:1: (otherlv_0= 'context' ( (lv_ownedSignature_1_0= ruleTemplateSignatureCS ) )? ( (lv_ownedPathName_2_0= rulePathNameCS ) ) otherlv_3= '(' ( ( (lv_ownedParameters_4_0= ruleParameterCS ) ) (otherlv_5= ',' ( (lv_ownedParameters_6_0= ruleParameterCS ) ) )* )? otherlv_7= ')' otherlv_8= ':' ( (lv_ownedType_9_0= ruleTypeExpCS ) )? ( (otherlv_10= 'pre' ( (lv_ownedPreconditions_11_0= ruleConstraintCS ) ) ) | (otherlv_12= 'post' ( (lv_ownedPostconditions_13_0= ruleConstraintCS ) ) ) | (otherlv_14= 'body' ( ruleUnrestrictedName )? otherlv_16= ':' ( (lv_ownedBodies_17_0= ruleSpecificationCS ) ) ) )* )
            {
            // InternalCompleteOCL.g:966:1: (otherlv_0= 'context' ( (lv_ownedSignature_1_0= ruleTemplateSignatureCS ) )? ( (lv_ownedPathName_2_0= rulePathNameCS ) ) otherlv_3= '(' ( ( (lv_ownedParameters_4_0= ruleParameterCS ) ) (otherlv_5= ',' ( (lv_ownedParameters_6_0= ruleParameterCS ) ) )* )? otherlv_7= ')' otherlv_8= ':' ( (lv_ownedType_9_0= ruleTypeExpCS ) )? ( (otherlv_10= 'pre' ( (lv_ownedPreconditions_11_0= ruleConstraintCS ) ) ) | (otherlv_12= 'post' ( (lv_ownedPostconditions_13_0= ruleConstraintCS ) ) ) | (otherlv_14= 'body' ( ruleUnrestrictedName )? otherlv_16= ':' ( (lv_ownedBodies_17_0= ruleSpecificationCS ) ) ) )* )
            // InternalCompleteOCL.g:966:3: otherlv_0= 'context' ( (lv_ownedSignature_1_0= ruleTemplateSignatureCS ) )? ( (lv_ownedPathName_2_0= rulePathNameCS ) ) otherlv_3= '(' ( ( (lv_ownedParameters_4_0= ruleParameterCS ) ) (otherlv_5= ',' ( (lv_ownedParameters_6_0= ruleParameterCS ) ) )* )? otherlv_7= ')' otherlv_8= ':' ( (lv_ownedType_9_0= ruleTypeExpCS ) )? ( (otherlv_10= 'pre' ( (lv_ownedPreconditions_11_0= ruleConstraintCS ) ) ) | (otherlv_12= 'post' ( (lv_ownedPostconditions_13_0= ruleConstraintCS ) ) ) | (otherlv_14= 'body' ( ruleUnrestrictedName )? otherlv_16= ':' ( (lv_ownedBodies_17_0= ruleSpecificationCS ) ) ) )*
            {
            otherlv_0=(Token)match(input,19,FollowSets000.FOLLOW_5); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getOperationContextDeclCSAccess().getContextKeyword_0());

            }
            // InternalCompleteOCL.g:970:1: ( (lv_ownedSignature_1_0= ruleTemplateSignatureCS ) )?
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==21||LA22_0==39) ) {
                alt22=1;
            }
            switch (alt22) {
                case 1 :
                    // InternalCompleteOCL.g:971:1: (lv_ownedSignature_1_0= ruleTemplateSignatureCS )
                    {
                    // InternalCompleteOCL.g:971:1: (lv_ownedSignature_1_0= ruleTemplateSignatureCS )
                    // InternalCompleteOCL.g:972:3: lv_ownedSignature_1_0= ruleTemplateSignatureCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getOperationContextDeclCSAccess().getOwnedSignatureTemplateSignatureCSParserRuleCall_1_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_5);
                    lv_ownedSignature_1_0=ruleTemplateSignatureCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getOperationContextDeclCSRule());
                      	        }
                             		set(
                             			current,
                             			"ownedSignature",
                              		lv_ownedSignature_1_0,
                              		"org.eclipse.ocl.xtext.completeocl.CompleteOCL.TemplateSignatureCS");
                      	        afterParserOrEnumRuleCall();

                    }

                    }


                    }
                    break;

            }

            // InternalCompleteOCL.g:988:3: ( (lv_ownedPathName_2_0= rulePathNameCS ) )
            // InternalCompleteOCL.g:989:1: (lv_ownedPathName_2_0= rulePathNameCS )
            {
            // InternalCompleteOCL.g:989:1: (lv_ownedPathName_2_0= rulePathNameCS )
            // InternalCompleteOCL.g:990:3: lv_ownedPathName_2_0= rulePathNameCS
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getOperationContextDeclCSAccess().getOwnedPathNamePathNameCSParserRuleCall_2_0());

            }
            pushFollow(FollowSets000.FOLLOW_15);
            lv_ownedPathName_2_0=rulePathNameCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getOperationContextDeclCSRule());
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

            otherlv_3=(Token)match(input,21,FollowSets000.FOLLOW_23); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_3, grammarAccess.getOperationContextDeclCSAccess().getLeftParenthesisKeyword_3());

            }
            // InternalCompleteOCL.g:1010:1: ( ( (lv_ownedParameters_4_0= ruleParameterCS ) ) (otherlv_5= ',' ( (lv_ownedParameters_6_0= ruleParameterCS ) ) )* )?
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( ((LA24_0>=RULE_SIMPLE_ID && LA24_0<=RULE_ESCAPED_ID)||(LA24_0>=28 && LA24_0<=30)||(LA24_0>=42 && LA24_0<=51)||(LA24_0>=68 && LA24_0<=69)||(LA24_0>=71 && LA24_0<=75)) ) {
                alt24=1;
            }
            switch (alt24) {
                case 1 :
                    // InternalCompleteOCL.g:1010:2: ( (lv_ownedParameters_4_0= ruleParameterCS ) ) (otherlv_5= ',' ( (lv_ownedParameters_6_0= ruleParameterCS ) ) )*
                    {
                    // InternalCompleteOCL.g:1010:2: ( (lv_ownedParameters_4_0= ruleParameterCS ) )
                    // InternalCompleteOCL.g:1011:1: (lv_ownedParameters_4_0= ruleParameterCS )
                    {
                    // InternalCompleteOCL.g:1011:1: (lv_ownedParameters_4_0= ruleParameterCS )
                    // InternalCompleteOCL.g:1012:3: lv_ownedParameters_4_0= ruleParameterCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getOperationContextDeclCSAccess().getOwnedParametersParameterCSParserRuleCall_4_0_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_17);
                    lv_ownedParameters_4_0=ruleParameterCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getOperationContextDeclCSRule());
                      	        }
                             		add(
                             			current,
                             			"ownedParameters",
                              		lv_ownedParameters_4_0,
                              		"org.eclipse.ocl.xtext.completeocl.CompleteOCL.ParameterCS");
                      	        afterParserOrEnumRuleCall();

                    }

                    }


                    }

                    // InternalCompleteOCL.g:1028:2: (otherlv_5= ',' ( (lv_ownedParameters_6_0= ruleParameterCS ) ) )*
                    loop23:
                    do {
                        int alt23=2;
                        int LA23_0 = input.LA(1);

                        if ( (LA23_0==26) ) {
                            alt23=1;
                        }


                        switch (alt23) {
                    	case 1 :
                    	    // InternalCompleteOCL.g:1028:4: otherlv_5= ',' ( (lv_ownedParameters_6_0= ruleParameterCS ) )
                    	    {
                    	    otherlv_5=(Token)match(input,26,FollowSets000.FOLLOW_20); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_5, grammarAccess.getOperationContextDeclCSAccess().getCommaKeyword_4_1_0());

                    	    }
                    	    // InternalCompleteOCL.g:1032:1: ( (lv_ownedParameters_6_0= ruleParameterCS ) )
                    	    // InternalCompleteOCL.g:1033:1: (lv_ownedParameters_6_0= ruleParameterCS )
                    	    {
                    	    // InternalCompleteOCL.g:1033:1: (lv_ownedParameters_6_0= ruleParameterCS )
                    	    // InternalCompleteOCL.g:1034:3: lv_ownedParameters_6_0= ruleParameterCS
                    	    {
                    	    if ( state.backtracking==0 ) {

                    	      	        newCompositeNode(grammarAccess.getOperationContextDeclCSAccess().getOwnedParametersParameterCSParserRuleCall_4_1_1_0());

                    	    }
                    	    pushFollow(FollowSets000.FOLLOW_17);
                    	    lv_ownedParameters_6_0=ruleParameterCS();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = createModelElementForParent(grammarAccess.getOperationContextDeclCSRule());
                    	      	        }
                    	             		add(
                    	             			current,
                    	             			"ownedParameters",
                    	              		lv_ownedParameters_6_0,
                    	              		"org.eclipse.ocl.xtext.completeocl.CompleteOCL.ParameterCS");
                    	      	        afterParserOrEnumRuleCall();

                    	    }

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop23;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_7=(Token)match(input,22,FollowSets000.FOLLOW_12); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_7, grammarAccess.getOperationContextDeclCSAccess().getRightParenthesisKeyword_5());

            }
            otherlv_8=(Token)match(input,23,FollowSets000.FOLLOW_24); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_8, grammarAccess.getOperationContextDeclCSAccess().getColonKeyword_6());

            }
            // InternalCompleteOCL.g:1058:1: ( (lv_ownedType_9_0= ruleTypeExpCS ) )?
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( ((LA25_0>=RULE_SIMPLE_ID && LA25_0<=RULE_ESCAPED_ID)||(LA25_0>=28 && LA25_0<=30)||(LA25_0>=42 && LA25_0<=51)||(LA25_0>=68 && LA25_0<=69)||(LA25_0>=71 && LA25_0<=75)) ) {
                alt25=1;
            }
            switch (alt25) {
                case 1 :
                    // InternalCompleteOCL.g:1059:1: (lv_ownedType_9_0= ruleTypeExpCS )
                    {
                    // InternalCompleteOCL.g:1059:1: (lv_ownedType_9_0= ruleTypeExpCS )
                    // InternalCompleteOCL.g:1060:3: lv_ownedType_9_0= ruleTypeExpCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getOperationContextDeclCSAccess().getOwnedTypeTypeExpCSParserRuleCall_7_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_25);
                    lv_ownedType_9_0=ruleTypeExpCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getOperationContextDeclCSRule());
                      	        }
                             		set(
                             			current,
                             			"ownedType",
                              		lv_ownedType_9_0,
                              		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.TypeExpCS");
                      	        afterParserOrEnumRuleCall();

                    }

                    }


                    }
                    break;

            }

            // InternalCompleteOCL.g:1076:3: ( (otherlv_10= 'pre' ( (lv_ownedPreconditions_11_0= ruleConstraintCS ) ) ) | (otherlv_12= 'post' ( (lv_ownedPostconditions_13_0= ruleConstraintCS ) ) ) | (otherlv_14= 'body' ( ruleUnrestrictedName )? otherlv_16= ':' ( (lv_ownedBodies_17_0= ruleSpecificationCS ) ) ) )*
            loop27:
            do {
                int alt27=4;
                switch ( input.LA(1) ) {
                case 32:
                    {
                    alt27=1;
                    }
                    break;
                case 33:
                    {
                    alt27=2;
                    }
                    break;
                case 34:
                    {
                    alt27=3;
                    }
                    break;

                }

                switch (alt27) {
            	case 1 :
            	    // InternalCompleteOCL.g:1076:4: (otherlv_10= 'pre' ( (lv_ownedPreconditions_11_0= ruleConstraintCS ) ) )
            	    {
            	    // InternalCompleteOCL.g:1076:4: (otherlv_10= 'pre' ( (lv_ownedPreconditions_11_0= ruleConstraintCS ) ) )
            	    // InternalCompleteOCL.g:1076:6: otherlv_10= 'pre' ( (lv_ownedPreconditions_11_0= ruleConstraintCS ) )
            	    {
            	    otherlv_10=(Token)match(input,32,FollowSets000.FOLLOW_7); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_10, grammarAccess.getOperationContextDeclCSAccess().getPreKeyword_8_0_0());

            	    }
            	    // InternalCompleteOCL.g:1080:1: ( (lv_ownedPreconditions_11_0= ruleConstraintCS ) )
            	    // InternalCompleteOCL.g:1081:1: (lv_ownedPreconditions_11_0= ruleConstraintCS )
            	    {
            	    // InternalCompleteOCL.g:1081:1: (lv_ownedPreconditions_11_0= ruleConstraintCS )
            	    // InternalCompleteOCL.g:1082:3: lv_ownedPreconditions_11_0= ruleConstraintCS
            	    {
            	    if ( state.backtracking==0 ) {

            	      	        newCompositeNode(grammarAccess.getOperationContextDeclCSAccess().getOwnedPreconditionsConstraintCSParserRuleCall_8_0_1_0());

            	    }
            	    pushFollow(FollowSets000.FOLLOW_25);
            	    lv_ownedPreconditions_11_0=ruleConstraintCS();

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
            	              		"org.eclipse.ocl.xtext.completeocl.CompleteOCL.ConstraintCS");
            	      	        afterParserOrEnumRuleCall();

            	    }

            	    }


            	    }


            	    }


            	    }
            	    break;
            	case 2 :
            	    // InternalCompleteOCL.g:1099:6: (otherlv_12= 'post' ( (lv_ownedPostconditions_13_0= ruleConstraintCS ) ) )
            	    {
            	    // InternalCompleteOCL.g:1099:6: (otherlv_12= 'post' ( (lv_ownedPostconditions_13_0= ruleConstraintCS ) ) )
            	    // InternalCompleteOCL.g:1099:8: otherlv_12= 'post' ( (lv_ownedPostconditions_13_0= ruleConstraintCS ) )
            	    {
            	    otherlv_12=(Token)match(input,33,FollowSets000.FOLLOW_7); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_12, grammarAccess.getOperationContextDeclCSAccess().getPostKeyword_8_1_0());

            	    }
            	    // InternalCompleteOCL.g:1103:1: ( (lv_ownedPostconditions_13_0= ruleConstraintCS ) )
            	    // InternalCompleteOCL.g:1104:1: (lv_ownedPostconditions_13_0= ruleConstraintCS )
            	    {
            	    // InternalCompleteOCL.g:1104:1: (lv_ownedPostconditions_13_0= ruleConstraintCS )
            	    // InternalCompleteOCL.g:1105:3: lv_ownedPostconditions_13_0= ruleConstraintCS
            	    {
            	    if ( state.backtracking==0 ) {

            	      	        newCompositeNode(grammarAccess.getOperationContextDeclCSAccess().getOwnedPostconditionsConstraintCSParserRuleCall_8_1_1_0());

            	    }
            	    pushFollow(FollowSets000.FOLLOW_25);
            	    lv_ownedPostconditions_13_0=ruleConstraintCS();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getOperationContextDeclCSRule());
            	      	        }
            	             		add(
            	             			current,
            	             			"ownedPostconditions",
            	              		lv_ownedPostconditions_13_0,
            	              		"org.eclipse.ocl.xtext.completeocl.CompleteOCL.ConstraintCS");
            	      	        afterParserOrEnumRuleCall();

            	    }

            	    }


            	    }


            	    }


            	    }
            	    break;
            	case 3 :
            	    // InternalCompleteOCL.g:1122:6: (otherlv_14= 'body' ( ruleUnrestrictedName )? otherlv_16= ':' ( (lv_ownedBodies_17_0= ruleSpecificationCS ) ) )
            	    {
            	    // InternalCompleteOCL.g:1122:6: (otherlv_14= 'body' ( ruleUnrestrictedName )? otherlv_16= ':' ( (lv_ownedBodies_17_0= ruleSpecificationCS ) ) )
            	    // InternalCompleteOCL.g:1122:8: otherlv_14= 'body' ( ruleUnrestrictedName )? otherlv_16= ':' ( (lv_ownedBodies_17_0= ruleSpecificationCS ) )
            	    {
            	    otherlv_14=(Token)match(input,34,FollowSets000.FOLLOW_7); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_14, grammarAccess.getOperationContextDeclCSAccess().getBodyKeyword_8_2_0());

            	    }
            	    // InternalCompleteOCL.g:1126:1: ( ruleUnrestrictedName )?
            	    int alt26=2;
            	    int LA26_0 = input.LA(1);

            	    if ( ((LA26_0>=RULE_SIMPLE_ID && LA26_0<=RULE_ESCAPED_ID)||(LA26_0>=28 && LA26_0<=30)) ) {
            	        alt26=1;
            	    }
            	    switch (alt26) {
            	        case 1 :
            	            // InternalCompleteOCL.g:1127:2: ruleUnrestrictedName
            	            {
            	            if ( state.backtracking==0 ) {

            	              	  /* */

            	            }
            	            if ( state.backtracking==0 ) {

            	                      newCompositeNode(grammarAccess.getOperationContextDeclCSAccess().getUnrestrictedNameParserRuleCall_8_2_1());

            	            }
            	            pushFollow(FollowSets000.FOLLOW_12);
            	            ruleUnrestrictedName();

            	            state._fsp--;
            	            if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	                      afterParserOrEnumRuleCall();

            	            }

            	            }
            	            break;

            	    }

            	    otherlv_16=(Token)match(input,23,FollowSets000.FOLLOW_10); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_16, grammarAccess.getOperationContextDeclCSAccess().getColonKeyword_8_2_2());

            	    }
            	    // InternalCompleteOCL.g:1141:1: ( (lv_ownedBodies_17_0= ruleSpecificationCS ) )
            	    // InternalCompleteOCL.g:1142:1: (lv_ownedBodies_17_0= ruleSpecificationCS )
            	    {
            	    // InternalCompleteOCL.g:1142:1: (lv_ownedBodies_17_0= ruleSpecificationCS )
            	    // InternalCompleteOCL.g:1143:3: lv_ownedBodies_17_0= ruleSpecificationCS
            	    {
            	    if ( state.backtracking==0 ) {

            	      	        newCompositeNode(grammarAccess.getOperationContextDeclCSAccess().getOwnedBodiesSpecificationCSParserRuleCall_8_2_3_0());

            	    }
            	    pushFollow(FollowSets000.FOLLOW_25);
            	    lv_ownedBodies_17_0=ruleSpecificationCS();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getOperationContextDeclCSRule());
            	      	        }
            	             		add(
            	             			current,
            	             			"ownedBodies",
            	              		lv_ownedBodies_17_0,
            	              		"org.eclipse.ocl.xtext.completeocl.CompleteOCL.SpecificationCS");
            	      	        afterParserOrEnumRuleCall();

            	    }

            	    }


            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop27;
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
    // InternalCompleteOCL.g:1167:1: entryRulePackageDeclarationCS returns [EObject current=null] : iv_rulePackageDeclarationCS= rulePackageDeclarationCS EOF ;
    public final EObject entryRulePackageDeclarationCS() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePackageDeclarationCS = null;


        try {
            // InternalCompleteOCL.g:1168:2: (iv_rulePackageDeclarationCS= rulePackageDeclarationCS EOF )
            // InternalCompleteOCL.g:1169:2: iv_rulePackageDeclarationCS= rulePackageDeclarationCS EOF
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
    // InternalCompleteOCL.g:1176:1: rulePackageDeclarationCS returns [EObject current=null] : (otherlv_0= 'package' ( (lv_ownedPathName_1_0= rulePathNameCS ) ) (otherlv_2= 'inv' ( (lv_ownedInvariants_3_0= ruleConstraintCS ) ) )* ( (lv_ownedContexts_4_0= ruleContextDeclCS ) )* otherlv_5= 'endpackage' ) ;
    public final EObject rulePackageDeclarationCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_5=null;
        EObject lv_ownedPathName_1_0 = null;

        EObject lv_ownedInvariants_3_0 = null;

        EObject lv_ownedContexts_4_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:1179:28: ( (otherlv_0= 'package' ( (lv_ownedPathName_1_0= rulePathNameCS ) ) (otherlv_2= 'inv' ( (lv_ownedInvariants_3_0= ruleConstraintCS ) ) )* ( (lv_ownedContexts_4_0= ruleContextDeclCS ) )* otherlv_5= 'endpackage' ) )
            // InternalCompleteOCL.g:1180:1: (otherlv_0= 'package' ( (lv_ownedPathName_1_0= rulePathNameCS ) ) (otherlv_2= 'inv' ( (lv_ownedInvariants_3_0= ruleConstraintCS ) ) )* ( (lv_ownedContexts_4_0= ruleContextDeclCS ) )* otherlv_5= 'endpackage' )
            {
            // InternalCompleteOCL.g:1180:1: (otherlv_0= 'package' ( (lv_ownedPathName_1_0= rulePathNameCS ) ) (otherlv_2= 'inv' ( (lv_ownedInvariants_3_0= ruleConstraintCS ) ) )* ( (lv_ownedContexts_4_0= ruleContextDeclCS ) )* otherlv_5= 'endpackage' )
            // InternalCompleteOCL.g:1180:3: otherlv_0= 'package' ( (lv_ownedPathName_1_0= rulePathNameCS ) ) (otherlv_2= 'inv' ( (lv_ownedInvariants_3_0= ruleConstraintCS ) ) )* ( (lv_ownedContexts_4_0= ruleContextDeclCS ) )* otherlv_5= 'endpackage'
            {
            otherlv_0=(Token)match(input,35,FollowSets000.FOLLOW_5); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getPackageDeclarationCSAccess().getPackageKeyword_0());

            }
            // InternalCompleteOCL.g:1184:1: ( (lv_ownedPathName_1_0= rulePathNameCS ) )
            // InternalCompleteOCL.g:1185:1: (lv_ownedPathName_1_0= rulePathNameCS )
            {
            // InternalCompleteOCL.g:1185:1: (lv_ownedPathName_1_0= rulePathNameCS )
            // InternalCompleteOCL.g:1186:3: lv_ownedPathName_1_0= rulePathNameCS
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getPackageDeclarationCSAccess().getOwnedPathNamePathNameCSParserRuleCall_1_0());

            }
            pushFollow(FollowSets000.FOLLOW_26);
            lv_ownedPathName_1_0=rulePathNameCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getPackageDeclarationCSRule());
              	        }
                     		set(
                     			current,
                     			"ownedPathName",
                      		lv_ownedPathName_1_0,
                      		"org.eclipse.ocl.xtext.base.Base.PathNameCS");
              	        afterParserOrEnumRuleCall();

            }

            }


            }

            // InternalCompleteOCL.g:1202:2: (otherlv_2= 'inv' ( (lv_ownedInvariants_3_0= ruleConstraintCS ) ) )*
            loop28:
            do {
                int alt28=2;
                int LA28_0 = input.LA(1);

                if ( (LA28_0==20) ) {
                    alt28=1;
                }


                switch (alt28) {
            	case 1 :
            	    // InternalCompleteOCL.g:1202:4: otherlv_2= 'inv' ( (lv_ownedInvariants_3_0= ruleConstraintCS ) )
            	    {
            	    otherlv_2=(Token)match(input,20,FollowSets000.FOLLOW_7); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_2, grammarAccess.getPackageDeclarationCSAccess().getInvKeyword_2_0());

            	    }
            	    // InternalCompleteOCL.g:1206:1: ( (lv_ownedInvariants_3_0= ruleConstraintCS ) )
            	    // InternalCompleteOCL.g:1207:1: (lv_ownedInvariants_3_0= ruleConstraintCS )
            	    {
            	    // InternalCompleteOCL.g:1207:1: (lv_ownedInvariants_3_0= ruleConstraintCS )
            	    // InternalCompleteOCL.g:1208:3: lv_ownedInvariants_3_0= ruleConstraintCS
            	    {
            	    if ( state.backtracking==0 ) {

            	      	        newCompositeNode(grammarAccess.getPackageDeclarationCSAccess().getOwnedInvariantsConstraintCSParserRuleCall_2_1_0());

            	    }
            	    pushFollow(FollowSets000.FOLLOW_26);
            	    lv_ownedInvariants_3_0=ruleConstraintCS();

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
            	              		"org.eclipse.ocl.xtext.completeocl.CompleteOCL.ConstraintCS");
            	      	        afterParserOrEnumRuleCall();

            	    }

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop28;
                }
            } while (true);

            // InternalCompleteOCL.g:1224:4: ( (lv_ownedContexts_4_0= ruleContextDeclCS ) )*
            loop29:
            do {
                int alt29=2;
                int LA29_0 = input.LA(1);

                if ( (LA29_0==19) ) {
                    alt29=1;
                }


                switch (alt29) {
            	case 1 :
            	    // InternalCompleteOCL.g:1225:1: (lv_ownedContexts_4_0= ruleContextDeclCS )
            	    {
            	    // InternalCompleteOCL.g:1225:1: (lv_ownedContexts_4_0= ruleContextDeclCS )
            	    // InternalCompleteOCL.g:1226:3: lv_ownedContexts_4_0= ruleContextDeclCS
            	    {
            	    if ( state.backtracking==0 ) {

            	      	        newCompositeNode(grammarAccess.getPackageDeclarationCSAccess().getOwnedContextsContextDeclCSParserRuleCall_3_0());

            	    }
            	    pushFollow(FollowSets000.FOLLOW_27);
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
            	    break loop29;
                }
            } while (true);

            otherlv_5=(Token)match(input,36,FollowSets000.FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_5, grammarAccess.getPackageDeclarationCSAccess().getEndpackageKeyword_4());

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
    // InternalCompleteOCL.g:1254:1: entryRuleParameterCS returns [EObject current=null] : iv_ruleParameterCS= ruleParameterCS EOF ;
    public final EObject entryRuleParameterCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleParameterCS = null;


        try {
            // InternalCompleteOCL.g:1255:2: (iv_ruleParameterCS= ruleParameterCS EOF )
            // InternalCompleteOCL.g:1256:2: iv_ruleParameterCS= ruleParameterCS EOF
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
    // InternalCompleteOCL.g:1263:1: ruleParameterCS returns [EObject current=null] : ( ( ( (lv_name_0_0= ruleUnrestrictedName ) ) otherlv_1= ':' )? ( (lv_ownedType_2_0= ruleTypeExpCS ) ) ) ;
    public final EObject ruleParameterCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_name_0_0 = null;

        EObject lv_ownedType_2_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:1266:28: ( ( ( ( (lv_name_0_0= ruleUnrestrictedName ) ) otherlv_1= ':' )? ( (lv_ownedType_2_0= ruleTypeExpCS ) ) ) )
            // InternalCompleteOCL.g:1267:1: ( ( ( (lv_name_0_0= ruleUnrestrictedName ) ) otherlv_1= ':' )? ( (lv_ownedType_2_0= ruleTypeExpCS ) ) )
            {
            // InternalCompleteOCL.g:1267:1: ( ( ( (lv_name_0_0= ruleUnrestrictedName ) ) otherlv_1= ':' )? ( (lv_ownedType_2_0= ruleTypeExpCS ) ) )
            // InternalCompleteOCL.g:1267:2: ( ( (lv_name_0_0= ruleUnrestrictedName ) ) otherlv_1= ':' )? ( (lv_ownedType_2_0= ruleTypeExpCS ) )
            {
            // InternalCompleteOCL.g:1267:2: ( ( (lv_name_0_0= ruleUnrestrictedName ) ) otherlv_1= ':' )?
            int alt30=2;
            switch ( input.LA(1) ) {
                case RULE_SIMPLE_ID:
                    {
                    int LA30_1 = input.LA(2);

                    if ( (LA30_1==23) ) {
                        alt30=1;
                    }
                    }
                    break;
                case RULE_ESCAPED_ID:
                    {
                    int LA30_2 = input.LA(2);

                    if ( (LA30_2==23) ) {
                        alt30=1;
                    }
                    }
                    break;
                case 28:
                    {
                    int LA30_3 = input.LA(2);

                    if ( (LA30_3==23) ) {
                        alt30=1;
                    }
                    }
                    break;
                case 29:
                    {
                    int LA30_4 = input.LA(2);

                    if ( (LA30_4==23) ) {
                        alt30=1;
                    }
                    }
                    break;
                case 30:
                    {
                    int LA30_5 = input.LA(2);

                    if ( (LA30_5==23) ) {
                        alt30=1;
                    }
                    }
                    break;
            }

            switch (alt30) {
                case 1 :
                    // InternalCompleteOCL.g:1267:3: ( (lv_name_0_0= ruleUnrestrictedName ) ) otherlv_1= ':'
                    {
                    // InternalCompleteOCL.g:1267:3: ( (lv_name_0_0= ruleUnrestrictedName ) )
                    // InternalCompleteOCL.g:1268:1: (lv_name_0_0= ruleUnrestrictedName )
                    {
                    // InternalCompleteOCL.g:1268:1: (lv_name_0_0= ruleUnrestrictedName )
                    // InternalCompleteOCL.g:1269:3: lv_name_0_0= ruleUnrestrictedName
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getParameterCSAccess().getNameUnrestrictedNameParserRuleCall_0_0_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_12);
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

                    otherlv_1=(Token)match(input,23,FollowSets000.FOLLOW_20); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_1, grammarAccess.getParameterCSAccess().getColonKeyword_0_1());

                    }

                    }
                    break;

            }

            // InternalCompleteOCL.g:1289:3: ( (lv_ownedType_2_0= ruleTypeExpCS ) )
            // InternalCompleteOCL.g:1290:1: (lv_ownedType_2_0= ruleTypeExpCS )
            {
            // InternalCompleteOCL.g:1290:1: (lv_ownedType_2_0= ruleTypeExpCS )
            // InternalCompleteOCL.g:1291:3: lv_ownedType_2_0= ruleTypeExpCS
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
    // InternalCompleteOCL.g:1315:1: entryRulePropertyContextDeclCS returns [EObject current=null] : iv_rulePropertyContextDeclCS= rulePropertyContextDeclCS EOF ;
    public final EObject entryRulePropertyContextDeclCS() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePropertyContextDeclCS = null;


        try {
            // InternalCompleteOCL.g:1316:2: (iv_rulePropertyContextDeclCS= rulePropertyContextDeclCS EOF )
            // InternalCompleteOCL.g:1317:2: iv_rulePropertyContextDeclCS= rulePropertyContextDeclCS EOF
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
    // InternalCompleteOCL.g:1324:1: rulePropertyContextDeclCS returns [EObject current=null] : (otherlv_0= 'context' ( (lv_ownedPathName_1_0= rulePathNameCS ) ) otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) ( (otherlv_4= 'derive' ( (lv_ownedDerivedInvariants_5_0= ruleConstraintCS ) ) ) | (otherlv_6= 'init' otherlv_7= ':' ( (lv_ownedDefaultExpressions_8_0= ruleSpecificationCS ) ) ) )* ) ;
    public final EObject rulePropertyContextDeclCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_7=null;
        EObject lv_ownedPathName_1_0 = null;

        EObject lv_ownedType_3_0 = null;

        EObject lv_ownedDerivedInvariants_5_0 = null;

        EObject lv_ownedDefaultExpressions_8_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:1327:28: ( (otherlv_0= 'context' ( (lv_ownedPathName_1_0= rulePathNameCS ) ) otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) ( (otherlv_4= 'derive' ( (lv_ownedDerivedInvariants_5_0= ruleConstraintCS ) ) ) | (otherlv_6= 'init' otherlv_7= ':' ( (lv_ownedDefaultExpressions_8_0= ruleSpecificationCS ) ) ) )* ) )
            // InternalCompleteOCL.g:1328:1: (otherlv_0= 'context' ( (lv_ownedPathName_1_0= rulePathNameCS ) ) otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) ( (otherlv_4= 'derive' ( (lv_ownedDerivedInvariants_5_0= ruleConstraintCS ) ) ) | (otherlv_6= 'init' otherlv_7= ':' ( (lv_ownedDefaultExpressions_8_0= ruleSpecificationCS ) ) ) )* )
            {
            // InternalCompleteOCL.g:1328:1: (otherlv_0= 'context' ( (lv_ownedPathName_1_0= rulePathNameCS ) ) otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) ( (otherlv_4= 'derive' ( (lv_ownedDerivedInvariants_5_0= ruleConstraintCS ) ) ) | (otherlv_6= 'init' otherlv_7= ':' ( (lv_ownedDefaultExpressions_8_0= ruleSpecificationCS ) ) ) )* )
            // InternalCompleteOCL.g:1328:3: otherlv_0= 'context' ( (lv_ownedPathName_1_0= rulePathNameCS ) ) otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) ( (otherlv_4= 'derive' ( (lv_ownedDerivedInvariants_5_0= ruleConstraintCS ) ) ) | (otherlv_6= 'init' otherlv_7= ':' ( (lv_ownedDefaultExpressions_8_0= ruleSpecificationCS ) ) ) )*
            {
            otherlv_0=(Token)match(input,19,FollowSets000.FOLLOW_5); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getPropertyContextDeclCSAccess().getContextKeyword_0());

            }
            // InternalCompleteOCL.g:1332:1: ( (lv_ownedPathName_1_0= rulePathNameCS ) )
            // InternalCompleteOCL.g:1333:1: (lv_ownedPathName_1_0= rulePathNameCS )
            {
            // InternalCompleteOCL.g:1333:1: (lv_ownedPathName_1_0= rulePathNameCS )
            // InternalCompleteOCL.g:1334:3: lv_ownedPathName_1_0= rulePathNameCS
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getPropertyContextDeclCSAccess().getOwnedPathNamePathNameCSParserRuleCall_1_0());

            }
            pushFollow(FollowSets000.FOLLOW_12);
            lv_ownedPathName_1_0=rulePathNameCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getPropertyContextDeclCSRule());
              	        }
                     		set(
                     			current,
                     			"ownedPathName",
                      		lv_ownedPathName_1_0,
                      		"org.eclipse.ocl.xtext.base.Base.PathNameCS");
              	        afterParserOrEnumRuleCall();

            }

            }


            }

            otherlv_2=(Token)match(input,23,FollowSets000.FOLLOW_20); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_2, grammarAccess.getPropertyContextDeclCSAccess().getColonKeyword_2());

            }
            // InternalCompleteOCL.g:1354:1: ( (lv_ownedType_3_0= ruleTypeExpCS ) )
            // InternalCompleteOCL.g:1355:1: (lv_ownedType_3_0= ruleTypeExpCS )
            {
            // InternalCompleteOCL.g:1355:1: (lv_ownedType_3_0= ruleTypeExpCS )
            // InternalCompleteOCL.g:1356:3: lv_ownedType_3_0= ruleTypeExpCS
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getPropertyContextDeclCSAccess().getOwnedTypeTypeExpCSParserRuleCall_3_0());

            }
            pushFollow(FollowSets000.FOLLOW_28);
            lv_ownedType_3_0=ruleTypeExpCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getPropertyContextDeclCSRule());
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

            // InternalCompleteOCL.g:1372:2: ( (otherlv_4= 'derive' ( (lv_ownedDerivedInvariants_5_0= ruleConstraintCS ) ) ) | (otherlv_6= 'init' otherlv_7= ':' ( (lv_ownedDefaultExpressions_8_0= ruleSpecificationCS ) ) ) )*
            loop31:
            do {
                int alt31=3;
                int LA31_0 = input.LA(1);

                if ( (LA31_0==37) ) {
                    alt31=1;
                }
                else if ( (LA31_0==38) ) {
                    alt31=2;
                }


                switch (alt31) {
            	case 1 :
            	    // InternalCompleteOCL.g:1372:3: (otherlv_4= 'derive' ( (lv_ownedDerivedInvariants_5_0= ruleConstraintCS ) ) )
            	    {
            	    // InternalCompleteOCL.g:1372:3: (otherlv_4= 'derive' ( (lv_ownedDerivedInvariants_5_0= ruleConstraintCS ) ) )
            	    // InternalCompleteOCL.g:1372:5: otherlv_4= 'derive' ( (lv_ownedDerivedInvariants_5_0= ruleConstraintCS ) )
            	    {
            	    otherlv_4=(Token)match(input,37,FollowSets000.FOLLOW_7); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_4, grammarAccess.getPropertyContextDeclCSAccess().getDeriveKeyword_4_0_0());

            	    }
            	    // InternalCompleteOCL.g:1376:1: ( (lv_ownedDerivedInvariants_5_0= ruleConstraintCS ) )
            	    // InternalCompleteOCL.g:1377:1: (lv_ownedDerivedInvariants_5_0= ruleConstraintCS )
            	    {
            	    // InternalCompleteOCL.g:1377:1: (lv_ownedDerivedInvariants_5_0= ruleConstraintCS )
            	    // InternalCompleteOCL.g:1378:3: lv_ownedDerivedInvariants_5_0= ruleConstraintCS
            	    {
            	    if ( state.backtracking==0 ) {

            	      	        newCompositeNode(grammarAccess.getPropertyContextDeclCSAccess().getOwnedDerivedInvariantsConstraintCSParserRuleCall_4_0_1_0());

            	    }
            	    pushFollow(FollowSets000.FOLLOW_28);
            	    lv_ownedDerivedInvariants_5_0=ruleConstraintCS();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getPropertyContextDeclCSRule());
            	      	        }
            	             		add(
            	             			current,
            	             			"ownedDerivedInvariants",
            	              		lv_ownedDerivedInvariants_5_0,
            	              		"org.eclipse.ocl.xtext.completeocl.CompleteOCL.ConstraintCS");
            	      	        afterParserOrEnumRuleCall();

            	    }

            	    }


            	    }


            	    }


            	    }
            	    break;
            	case 2 :
            	    // InternalCompleteOCL.g:1395:6: (otherlv_6= 'init' otherlv_7= ':' ( (lv_ownedDefaultExpressions_8_0= ruleSpecificationCS ) ) )
            	    {
            	    // InternalCompleteOCL.g:1395:6: (otherlv_6= 'init' otherlv_7= ':' ( (lv_ownedDefaultExpressions_8_0= ruleSpecificationCS ) ) )
            	    // InternalCompleteOCL.g:1395:8: otherlv_6= 'init' otherlv_7= ':' ( (lv_ownedDefaultExpressions_8_0= ruleSpecificationCS ) )
            	    {
            	    otherlv_6=(Token)match(input,38,FollowSets000.FOLLOW_12); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_6, grammarAccess.getPropertyContextDeclCSAccess().getInitKeyword_4_1_0());

            	    }
            	    otherlv_7=(Token)match(input,23,FollowSets000.FOLLOW_10); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_7, grammarAccess.getPropertyContextDeclCSAccess().getColonKeyword_4_1_1());

            	    }
            	    // InternalCompleteOCL.g:1403:1: ( (lv_ownedDefaultExpressions_8_0= ruleSpecificationCS ) )
            	    // InternalCompleteOCL.g:1404:1: (lv_ownedDefaultExpressions_8_0= ruleSpecificationCS )
            	    {
            	    // InternalCompleteOCL.g:1404:1: (lv_ownedDefaultExpressions_8_0= ruleSpecificationCS )
            	    // InternalCompleteOCL.g:1405:3: lv_ownedDefaultExpressions_8_0= ruleSpecificationCS
            	    {
            	    if ( state.backtracking==0 ) {

            	      	        newCompositeNode(grammarAccess.getPropertyContextDeclCSAccess().getOwnedDefaultExpressionsSpecificationCSParserRuleCall_4_1_2_0());

            	    }
            	    pushFollow(FollowSets000.FOLLOW_28);
            	    lv_ownedDefaultExpressions_8_0=ruleSpecificationCS();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getPropertyContextDeclCSRule());
            	      	        }
            	             		add(
            	             			current,
            	             			"ownedDefaultExpressions",
            	              		lv_ownedDefaultExpressions_8_0,
            	              		"org.eclipse.ocl.xtext.completeocl.CompleteOCL.SpecificationCS");
            	      	        afterParserOrEnumRuleCall();

            	    }

            	    }


            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop31;
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
    // InternalCompleteOCL.g:1429:1: entryRuleSpecificationCS returns [EObject current=null] : iv_ruleSpecificationCS= ruleSpecificationCS EOF ;
    public final EObject entryRuleSpecificationCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSpecificationCS = null;


        try {
            // InternalCompleteOCL.g:1430:2: (iv_ruleSpecificationCS= ruleSpecificationCS EOF )
            // InternalCompleteOCL.g:1431:2: iv_ruleSpecificationCS= ruleSpecificationCS EOF
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
    // InternalCompleteOCL.g:1438:1: ruleSpecificationCS returns [EObject current=null] : ( ( (lv_ownedExpression_0_0= ruleExpCS ) ) | ( (lv_exprString_1_0= RULE_UNQUOTED_STRING ) ) ) ;
    public final EObject ruleSpecificationCS() throws RecognitionException {
        EObject current = null;

        Token lv_exprString_1_0=null;
        EObject lv_ownedExpression_0_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:1441:28: ( ( ( (lv_ownedExpression_0_0= ruleExpCS ) ) | ( (lv_exprString_1_0= RULE_UNQUOTED_STRING ) ) ) )
            // InternalCompleteOCL.g:1442:1: ( ( (lv_ownedExpression_0_0= ruleExpCS ) ) | ( (lv_exprString_1_0= RULE_UNQUOTED_STRING ) ) )
            {
            // InternalCompleteOCL.g:1442:1: ( ( (lv_ownedExpression_0_0= ruleExpCS ) ) | ( (lv_exprString_1_0= RULE_UNQUOTED_STRING ) ) )
            int alt32=2;
            int LA32_0 = input.LA(1);

            if ( ((LA32_0>=RULE_SIMPLE_ID && LA32_0<=RULE_SINGLE_QUOTED_STRING)||LA32_0==21||(LA32_0>=28 && LA32_0<=30)||(LA32_0>=42 && LA32_0<=54)||(LA32_0>=68 && LA32_0<=69)||(LA32_0>=71 && LA32_0<=75)||LA32_0==80||(LA32_0>=82 && LA32_0<=85)||LA32_0==92||(LA32_0>=97 && LA32_0<=98)) ) {
                alt32=1;
            }
            else if ( (LA32_0==RULE_UNQUOTED_STRING) ) {
                alt32=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 32, 0, input);

                throw nvae;
            }
            switch (alt32) {
                case 1 :
                    // InternalCompleteOCL.g:1442:2: ( (lv_ownedExpression_0_0= ruleExpCS ) )
                    {
                    // InternalCompleteOCL.g:1442:2: ( (lv_ownedExpression_0_0= ruleExpCS ) )
                    // InternalCompleteOCL.g:1443:1: (lv_ownedExpression_0_0= ruleExpCS )
                    {
                    // InternalCompleteOCL.g:1443:1: (lv_ownedExpression_0_0= ruleExpCS )
                    // InternalCompleteOCL.g:1444:3: lv_ownedExpression_0_0= ruleExpCS
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
                    // InternalCompleteOCL.g:1461:6: ( (lv_exprString_1_0= RULE_UNQUOTED_STRING ) )
                    {
                    // InternalCompleteOCL.g:1461:6: ( (lv_exprString_1_0= RULE_UNQUOTED_STRING ) )
                    // InternalCompleteOCL.g:1462:1: (lv_exprString_1_0= RULE_UNQUOTED_STRING )
                    {
                    // InternalCompleteOCL.g:1462:1: (lv_exprString_1_0= RULE_UNQUOTED_STRING )
                    // InternalCompleteOCL.g:1463:3: lv_exprString_1_0= RULE_UNQUOTED_STRING
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


    // $ANTLR start "entryRuleTemplateSignatureCS"
    // InternalCompleteOCL.g:1487:1: entryRuleTemplateSignatureCS returns [EObject current=null] : iv_ruleTemplateSignatureCS= ruleTemplateSignatureCS EOF ;
    public final EObject entryRuleTemplateSignatureCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTemplateSignatureCS = null;


        try {
            // InternalCompleteOCL.g:1488:2: (iv_ruleTemplateSignatureCS= ruleTemplateSignatureCS EOF )
            // InternalCompleteOCL.g:1489:2: iv_ruleTemplateSignatureCS= ruleTemplateSignatureCS EOF
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
    // InternalCompleteOCL.g:1496:1: ruleTemplateSignatureCS returns [EObject current=null] : ( (otherlv_0= '(' ( (lv_ownedParameters_1_0= ruleTypeParameterCS ) ) (otherlv_2= ',' ( (lv_ownedParameters_3_0= ruleTypeParameterCS ) ) )* otherlv_4= ')' ) | (otherlv_5= '<' ( (lv_ownedParameters_6_0= ruleTypeParameterCS ) ) (otherlv_7= ',' ( (lv_ownedParameters_8_0= ruleTypeParameterCS ) ) )* otherlv_9= '>' ) ) ;
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
            // InternalCompleteOCL.g:1499:28: ( ( (otherlv_0= '(' ( (lv_ownedParameters_1_0= ruleTypeParameterCS ) ) (otherlv_2= ',' ( (lv_ownedParameters_3_0= ruleTypeParameterCS ) ) )* otherlv_4= ')' ) | (otherlv_5= '<' ( (lv_ownedParameters_6_0= ruleTypeParameterCS ) ) (otherlv_7= ',' ( (lv_ownedParameters_8_0= ruleTypeParameterCS ) ) )* otherlv_9= '>' ) ) )
            // InternalCompleteOCL.g:1500:1: ( (otherlv_0= '(' ( (lv_ownedParameters_1_0= ruleTypeParameterCS ) ) (otherlv_2= ',' ( (lv_ownedParameters_3_0= ruleTypeParameterCS ) ) )* otherlv_4= ')' ) | (otherlv_5= '<' ( (lv_ownedParameters_6_0= ruleTypeParameterCS ) ) (otherlv_7= ',' ( (lv_ownedParameters_8_0= ruleTypeParameterCS ) ) )* otherlv_9= '>' ) )
            {
            // InternalCompleteOCL.g:1500:1: ( (otherlv_0= '(' ( (lv_ownedParameters_1_0= ruleTypeParameterCS ) ) (otherlv_2= ',' ( (lv_ownedParameters_3_0= ruleTypeParameterCS ) ) )* otherlv_4= ')' ) | (otherlv_5= '<' ( (lv_ownedParameters_6_0= ruleTypeParameterCS ) ) (otherlv_7= ',' ( (lv_ownedParameters_8_0= ruleTypeParameterCS ) ) )* otherlv_9= '>' ) )
            int alt35=2;
            int LA35_0 = input.LA(1);

            if ( (LA35_0==21) ) {
                alt35=1;
            }
            else if ( (LA35_0==39) ) {
                alt35=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 35, 0, input);

                throw nvae;
            }
            switch (alt35) {
                case 1 :
                    // InternalCompleteOCL.g:1500:2: (otherlv_0= '(' ( (lv_ownedParameters_1_0= ruleTypeParameterCS ) ) (otherlv_2= ',' ( (lv_ownedParameters_3_0= ruleTypeParameterCS ) ) )* otherlv_4= ')' )
                    {
                    // InternalCompleteOCL.g:1500:2: (otherlv_0= '(' ( (lv_ownedParameters_1_0= ruleTypeParameterCS ) ) (otherlv_2= ',' ( (lv_ownedParameters_3_0= ruleTypeParameterCS ) ) )* otherlv_4= ')' )
                    // InternalCompleteOCL.g:1500:4: otherlv_0= '(' ( (lv_ownedParameters_1_0= ruleTypeParameterCS ) ) (otherlv_2= ',' ( (lv_ownedParameters_3_0= ruleTypeParameterCS ) ) )* otherlv_4= ')'
                    {
                    otherlv_0=(Token)match(input,21,FollowSets000.FOLLOW_14); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_0, grammarAccess.getTemplateSignatureCSAccess().getLeftParenthesisKeyword_0_0());

                    }
                    // InternalCompleteOCL.g:1504:1: ( (lv_ownedParameters_1_0= ruleTypeParameterCS ) )
                    // InternalCompleteOCL.g:1505:1: (lv_ownedParameters_1_0= ruleTypeParameterCS )
                    {
                    // InternalCompleteOCL.g:1505:1: (lv_ownedParameters_1_0= ruleTypeParameterCS )
                    // InternalCompleteOCL.g:1506:3: lv_ownedParameters_1_0= ruleTypeParameterCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getTemplateSignatureCSAccess().getOwnedParametersTypeParameterCSParserRuleCall_0_1_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_17);
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

                    // InternalCompleteOCL.g:1522:2: (otherlv_2= ',' ( (lv_ownedParameters_3_0= ruleTypeParameterCS ) ) )*
                    loop33:
                    do {
                        int alt33=2;
                        int LA33_0 = input.LA(1);

                        if ( (LA33_0==26) ) {
                            alt33=1;
                        }


                        switch (alt33) {
                    	case 1 :
                    	    // InternalCompleteOCL.g:1522:4: otherlv_2= ',' ( (lv_ownedParameters_3_0= ruleTypeParameterCS ) )
                    	    {
                    	    otherlv_2=(Token)match(input,26,FollowSets000.FOLLOW_14); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_2, grammarAccess.getTemplateSignatureCSAccess().getCommaKeyword_0_2_0());

                    	    }
                    	    // InternalCompleteOCL.g:1526:1: ( (lv_ownedParameters_3_0= ruleTypeParameterCS ) )
                    	    // InternalCompleteOCL.g:1527:1: (lv_ownedParameters_3_0= ruleTypeParameterCS )
                    	    {
                    	    // InternalCompleteOCL.g:1527:1: (lv_ownedParameters_3_0= ruleTypeParameterCS )
                    	    // InternalCompleteOCL.g:1528:3: lv_ownedParameters_3_0= ruleTypeParameterCS
                    	    {
                    	    if ( state.backtracking==0 ) {

                    	      	        newCompositeNode(grammarAccess.getTemplateSignatureCSAccess().getOwnedParametersTypeParameterCSParserRuleCall_0_2_1_0());

                    	    }
                    	    pushFollow(FollowSets000.FOLLOW_17);
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
                    	    break loop33;
                        }
                    } while (true);

                    otherlv_4=(Token)match(input,22,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_4, grammarAccess.getTemplateSignatureCSAccess().getRightParenthesisKeyword_0_3());

                    }

                    }


                    }
                    break;
                case 2 :
                    // InternalCompleteOCL.g:1549:6: (otherlv_5= '<' ( (lv_ownedParameters_6_0= ruleTypeParameterCS ) ) (otherlv_7= ',' ( (lv_ownedParameters_8_0= ruleTypeParameterCS ) ) )* otherlv_9= '>' )
                    {
                    // InternalCompleteOCL.g:1549:6: (otherlv_5= '<' ( (lv_ownedParameters_6_0= ruleTypeParameterCS ) ) (otherlv_7= ',' ( (lv_ownedParameters_8_0= ruleTypeParameterCS ) ) )* otherlv_9= '>' )
                    // InternalCompleteOCL.g:1549:8: otherlv_5= '<' ( (lv_ownedParameters_6_0= ruleTypeParameterCS ) ) (otherlv_7= ',' ( (lv_ownedParameters_8_0= ruleTypeParameterCS ) ) )* otherlv_9= '>'
                    {
                    otherlv_5=(Token)match(input,39,FollowSets000.FOLLOW_14); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_5, grammarAccess.getTemplateSignatureCSAccess().getLessThanSignKeyword_1_0());

                    }
                    // InternalCompleteOCL.g:1553:1: ( (lv_ownedParameters_6_0= ruleTypeParameterCS ) )
                    // InternalCompleteOCL.g:1554:1: (lv_ownedParameters_6_0= ruleTypeParameterCS )
                    {
                    // InternalCompleteOCL.g:1554:1: (lv_ownedParameters_6_0= ruleTypeParameterCS )
                    // InternalCompleteOCL.g:1555:3: lv_ownedParameters_6_0= ruleTypeParameterCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getTemplateSignatureCSAccess().getOwnedParametersTypeParameterCSParserRuleCall_1_1_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_29);
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

                    // InternalCompleteOCL.g:1571:2: (otherlv_7= ',' ( (lv_ownedParameters_8_0= ruleTypeParameterCS ) ) )*
                    loop34:
                    do {
                        int alt34=2;
                        int LA34_0 = input.LA(1);

                        if ( (LA34_0==26) ) {
                            alt34=1;
                        }


                        switch (alt34) {
                    	case 1 :
                    	    // InternalCompleteOCL.g:1571:4: otherlv_7= ',' ( (lv_ownedParameters_8_0= ruleTypeParameterCS ) )
                    	    {
                    	    otherlv_7=(Token)match(input,26,FollowSets000.FOLLOW_14); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_7, grammarAccess.getTemplateSignatureCSAccess().getCommaKeyword_1_2_0());

                    	    }
                    	    // InternalCompleteOCL.g:1575:1: ( (lv_ownedParameters_8_0= ruleTypeParameterCS ) )
                    	    // InternalCompleteOCL.g:1576:1: (lv_ownedParameters_8_0= ruleTypeParameterCS )
                    	    {
                    	    // InternalCompleteOCL.g:1576:1: (lv_ownedParameters_8_0= ruleTypeParameterCS )
                    	    // InternalCompleteOCL.g:1577:3: lv_ownedParameters_8_0= ruleTypeParameterCS
                    	    {
                    	    if ( state.backtracking==0 ) {

                    	      	        newCompositeNode(grammarAccess.getTemplateSignatureCSAccess().getOwnedParametersTypeParameterCSParserRuleCall_1_2_1_0());

                    	    }
                    	    pushFollow(FollowSets000.FOLLOW_29);
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
                    	    break loop34;
                        }
                    } while (true);

                    otherlv_9=(Token)match(input,40,FollowSets000.FOLLOW_2); if (state.failed) return current;
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
    // InternalCompleteOCL.g:1605:1: entryRuleTypedRefCS returns [EObject current=null] : iv_ruleTypedRefCS= ruleTypedRefCS EOF ;
    public final EObject entryRuleTypedRefCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTypedRefCS = null;


        try {
            // InternalCompleteOCL.g:1606:2: (iv_ruleTypedRefCS= ruleTypedRefCS EOF )
            // InternalCompleteOCL.g:1607:2: iv_ruleTypedRefCS= ruleTypedRefCS EOF
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
    // InternalCompleteOCL.g:1614:1: ruleTypedRefCS returns [EObject current=null] : (this_TypeLiteralCS_0= ruleTypeLiteralCS | this_TypedTypeRefCS_1= ruleTypedTypeRefCS ) ;
    public final EObject ruleTypedRefCS() throws RecognitionException {
        EObject current = null;

        EObject this_TypeLiteralCS_0 = null;

        EObject this_TypedTypeRefCS_1 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:1617:28: ( (this_TypeLiteralCS_0= ruleTypeLiteralCS | this_TypedTypeRefCS_1= ruleTypedTypeRefCS ) )
            // InternalCompleteOCL.g:1618:1: (this_TypeLiteralCS_0= ruleTypeLiteralCS | this_TypedTypeRefCS_1= ruleTypedTypeRefCS )
            {
            // InternalCompleteOCL.g:1618:1: (this_TypeLiteralCS_0= ruleTypeLiteralCS | this_TypedTypeRefCS_1= ruleTypedTypeRefCS )
            int alt36=2;
            int LA36_0 = input.LA(1);

            if ( ((LA36_0>=42 && LA36_0<=51)||(LA36_0>=68 && LA36_0<=69)||(LA36_0>=71 && LA36_0<=75)) ) {
                alt36=1;
            }
            else if ( ((LA36_0>=RULE_SIMPLE_ID && LA36_0<=RULE_ESCAPED_ID)||(LA36_0>=28 && LA36_0<=30)) ) {
                alt36=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 36, 0, input);

                throw nvae;
            }
            switch (alt36) {
                case 1 :
                    // InternalCompleteOCL.g:1619:2: this_TypeLiteralCS_0= ruleTypeLiteralCS
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
                    // InternalCompleteOCL.g:1632:2: this_TypedTypeRefCS_1= ruleTypedTypeRefCS
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
    // InternalCompleteOCL.g:1651:1: entryRuleUnrestrictedName returns [String current=null] : iv_ruleUnrestrictedName= ruleUnrestrictedName EOF ;
    public final String entryRuleUnrestrictedName() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleUnrestrictedName = null;


        try {
            // InternalCompleteOCL.g:1652:2: (iv_ruleUnrestrictedName= ruleUnrestrictedName EOF )
            // InternalCompleteOCL.g:1653:2: iv_ruleUnrestrictedName= ruleUnrestrictedName EOF
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
    // InternalCompleteOCL.g:1660:1: ruleUnrestrictedName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_EssentialOCLUnrestrictedName_0= ruleEssentialOCLUnrestrictedName | kw= 'import' | kw= 'include' | kw= 'library' ) ;
    public final AntlrDatatypeRuleToken ruleUnrestrictedName() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        AntlrDatatypeRuleToken this_EssentialOCLUnrestrictedName_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:1663:28: ( (this_EssentialOCLUnrestrictedName_0= ruleEssentialOCLUnrestrictedName | kw= 'import' | kw= 'include' | kw= 'library' ) )
            // InternalCompleteOCL.g:1664:1: (this_EssentialOCLUnrestrictedName_0= ruleEssentialOCLUnrestrictedName | kw= 'import' | kw= 'include' | kw= 'library' )
            {
            // InternalCompleteOCL.g:1664:1: (this_EssentialOCLUnrestrictedName_0= ruleEssentialOCLUnrestrictedName | kw= 'import' | kw= 'include' | kw= 'library' )
            int alt37=4;
            switch ( input.LA(1) ) {
            case RULE_SIMPLE_ID:
            case RULE_ESCAPED_ID:
                {
                alt37=1;
                }
                break;
            case 28:
                {
                alt37=2;
                }
                break;
            case 29:
                {
                alt37=3;
                }
                break;
            case 30:
                {
                alt37=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 37, 0, input);

                throw nvae;
            }

            switch (alt37) {
                case 1 :
                    // InternalCompleteOCL.g:1665:5: this_EssentialOCLUnrestrictedName_0= ruleEssentialOCLUnrestrictedName
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
                    // InternalCompleteOCL.g:1677:2: kw= 'import'
                    {
                    kw=(Token)match(input,28,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getUnrestrictedNameAccess().getImportKeyword_1());

                    }

                    }
                    break;
                case 3 :
                    // InternalCompleteOCL.g:1684:2: kw= 'include'
                    {
                    kw=(Token)match(input,29,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getUnrestrictedNameAccess().getIncludeKeyword_2());

                    }

                    }
                    break;
                case 4 :
                    // InternalCompleteOCL.g:1691:2: kw= 'library'
                    {
                    kw=(Token)match(input,30,FollowSets000.FOLLOW_2); if (state.failed) return current;
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
    // InternalCompleteOCL.g:1704:1: entryRuleNavigatingArgExpCS returns [EObject current=null] : iv_ruleNavigatingArgExpCS= ruleNavigatingArgExpCS EOF ;
    public final EObject entryRuleNavigatingArgExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNavigatingArgExpCS = null;


        try {
            // InternalCompleteOCL.g:1705:2: (iv_ruleNavigatingArgExpCS= ruleNavigatingArgExpCS EOF )
            // InternalCompleteOCL.g:1706:2: iv_ruleNavigatingArgExpCS= ruleNavigatingArgExpCS EOF
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
    // InternalCompleteOCL.g:1713:1: ruleNavigatingArgExpCS returns [EObject current=null] : ( ( () otherlv_1= '?' ) | this_ExpCS_2= ruleExpCS ) ;
    public final EObject ruleNavigatingArgExpCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject this_ExpCS_2 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:1716:28: ( ( ( () otherlv_1= '?' ) | this_ExpCS_2= ruleExpCS ) )
            // InternalCompleteOCL.g:1717:1: ( ( () otherlv_1= '?' ) | this_ExpCS_2= ruleExpCS )
            {
            // InternalCompleteOCL.g:1717:1: ( ( () otherlv_1= '?' ) | this_ExpCS_2= ruleExpCS )
            int alt38=2;
            int LA38_0 = input.LA(1);

            if ( (LA38_0==41) ) {
                alt38=1;
            }
            else if ( ((LA38_0>=RULE_SIMPLE_ID && LA38_0<=RULE_SINGLE_QUOTED_STRING)||LA38_0==21||(LA38_0>=28 && LA38_0<=30)||(LA38_0>=42 && LA38_0<=54)||(LA38_0>=68 && LA38_0<=69)||(LA38_0>=71 && LA38_0<=75)||LA38_0==80||(LA38_0>=82 && LA38_0<=85)||LA38_0==92||(LA38_0>=97 && LA38_0<=98)) ) {
                alt38=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 38, 0, input);

                throw nvae;
            }
            switch (alt38) {
                case 1 :
                    // InternalCompleteOCL.g:1717:2: ( () otherlv_1= '?' )
                    {
                    // InternalCompleteOCL.g:1717:2: ( () otherlv_1= '?' )
                    // InternalCompleteOCL.g:1717:3: () otherlv_1= '?'
                    {
                    // InternalCompleteOCL.g:1717:3: ()
                    // InternalCompleteOCL.g:1718:2:
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

                    otherlv_1=(Token)match(input,41,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_1, grammarAccess.getNavigatingArgExpCSAccess().getQuestionMarkKeyword_0_1());

                    }

                    }


                    }
                    break;
                case 2 :
                    // InternalCompleteOCL.g:1732:2: this_ExpCS_2= ruleExpCS
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
    // InternalCompleteOCL.g:1751:1: entryRuleNavigationOperatorName returns [String current=null] : iv_ruleNavigationOperatorName= ruleNavigationOperatorName EOF ;
    public final String entryRuleNavigationOperatorName() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleNavigationOperatorName = null;


        try {
            // InternalCompleteOCL.g:1752:2: (iv_ruleNavigationOperatorName= ruleNavigationOperatorName EOF )
            // InternalCompleteOCL.g:1753:2: iv_ruleNavigationOperatorName= ruleNavigationOperatorName EOF
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
    // InternalCompleteOCL.g:1760:1: ruleNavigationOperatorName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_EssentialOCLNavigationOperatorName_0= ruleEssentialOCLNavigationOperatorName | this_CompleteOCLNavigationOperatorName_1= ruleCompleteOCLNavigationOperatorName ) ;
    public final AntlrDatatypeRuleToken ruleNavigationOperatorName() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        AntlrDatatypeRuleToken this_EssentialOCLNavigationOperatorName_0 = null;

        AntlrDatatypeRuleToken this_CompleteOCLNavigationOperatorName_1 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:1763:28: ( (this_EssentialOCLNavigationOperatorName_0= ruleEssentialOCLNavigationOperatorName | this_CompleteOCLNavigationOperatorName_1= ruleCompleteOCLNavigationOperatorName ) )
            // InternalCompleteOCL.g:1764:1: (this_EssentialOCLNavigationOperatorName_0= ruleEssentialOCLNavigationOperatorName | this_CompleteOCLNavigationOperatorName_1= ruleCompleteOCLNavigationOperatorName )
            {
            // InternalCompleteOCL.g:1764:1: (this_EssentialOCLNavigationOperatorName_0= ruleEssentialOCLNavigationOperatorName | this_CompleteOCLNavigationOperatorName_1= ruleCompleteOCLNavigationOperatorName )
            int alt39=2;
            int LA39_0 = input.LA(1);

            if ( ((LA39_0>=64 && LA39_0<=67)) ) {
                alt39=1;
            }
            else if ( ((LA39_0>=17 && LA39_0<=18)) ) {
                alt39=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 39, 0, input);

                throw nvae;
            }
            switch (alt39) {
                case 1 :
                    // InternalCompleteOCL.g:1765:5: this_EssentialOCLNavigationOperatorName_0= ruleEssentialOCLNavigationOperatorName
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
                    // InternalCompleteOCL.g:1777:5: this_CompleteOCLNavigationOperatorName_1= ruleCompleteOCLNavigationOperatorName
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
    // InternalCompleteOCL.g:1795:1: entryRulePrimitiveTypeIdentifier returns [String current=null] : iv_rulePrimitiveTypeIdentifier= rulePrimitiveTypeIdentifier EOF ;
    public final String entryRulePrimitiveTypeIdentifier() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulePrimitiveTypeIdentifier = null;


        try {
            // InternalCompleteOCL.g:1796:2: (iv_rulePrimitiveTypeIdentifier= rulePrimitiveTypeIdentifier EOF )
            // InternalCompleteOCL.g:1797:2: iv_rulePrimitiveTypeIdentifier= rulePrimitiveTypeIdentifier EOF
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
    // InternalCompleteOCL.g:1804:1: rulePrimitiveTypeIdentifier returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= 'Boolean' | kw= 'Integer' | kw= 'Real' | kw= 'String' | kw= 'UnlimitedNatural' | kw= 'OclAny' | kw= 'OclInvalid' | kw= 'OclMessage' | kw= 'OclState' | kw= 'OclVoid' ) ;
    public final AntlrDatatypeRuleToken rulePrimitiveTypeIdentifier() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule();

        try {
            // InternalCompleteOCL.g:1807:28: ( (kw= 'Boolean' | kw= 'Integer' | kw= 'Real' | kw= 'String' | kw= 'UnlimitedNatural' | kw= 'OclAny' | kw= 'OclInvalid' | kw= 'OclMessage' | kw= 'OclState' | kw= 'OclVoid' ) )
            // InternalCompleteOCL.g:1808:1: (kw= 'Boolean' | kw= 'Integer' | kw= 'Real' | kw= 'String' | kw= 'UnlimitedNatural' | kw= 'OclAny' | kw= 'OclInvalid' | kw= 'OclMessage' | kw= 'OclState' | kw= 'OclVoid' )
            {
            // InternalCompleteOCL.g:1808:1: (kw= 'Boolean' | kw= 'Integer' | kw= 'Real' | kw= 'String' | kw= 'UnlimitedNatural' | kw= 'OclAny' | kw= 'OclInvalid' | kw= 'OclMessage' | kw= 'OclState' | kw= 'OclVoid' )
            int alt40=10;
            switch ( input.LA(1) ) {
            case 42:
                {
                alt40=1;
                }
                break;
            case 43:
                {
                alt40=2;
                }
                break;
            case 44:
                {
                alt40=3;
                }
                break;
            case 45:
                {
                alt40=4;
                }
                break;
            case 46:
                {
                alt40=5;
                }
                break;
            case 47:
                {
                alt40=6;
                }
                break;
            case 48:
                {
                alt40=7;
                }
                break;
            case 49:
                {
                alt40=8;
                }
                break;
            case 50:
                {
                alt40=9;
                }
                break;
            case 51:
                {
                alt40=10;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 40, 0, input);

                throw nvae;
            }

            switch (alt40) {
                case 1 :
                    // InternalCompleteOCL.g:1809:2: kw= 'Boolean'
                    {
                    kw=(Token)match(input,42,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getPrimitiveTypeIdentifierAccess().getBooleanKeyword_0());

                    }

                    }
                    break;
                case 2 :
                    // InternalCompleteOCL.g:1816:2: kw= 'Integer'
                    {
                    kw=(Token)match(input,43,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getPrimitiveTypeIdentifierAccess().getIntegerKeyword_1());

                    }

                    }
                    break;
                case 3 :
                    // InternalCompleteOCL.g:1823:2: kw= 'Real'
                    {
                    kw=(Token)match(input,44,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getPrimitiveTypeIdentifierAccess().getRealKeyword_2());

                    }

                    }
                    break;
                case 4 :
                    // InternalCompleteOCL.g:1830:2: kw= 'String'
                    {
                    kw=(Token)match(input,45,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getPrimitiveTypeIdentifierAccess().getStringKeyword_3());

                    }

                    }
                    break;
                case 5 :
                    // InternalCompleteOCL.g:1837:2: kw= 'UnlimitedNatural'
                    {
                    kw=(Token)match(input,46,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getPrimitiveTypeIdentifierAccess().getUnlimitedNaturalKeyword_4());

                    }

                    }
                    break;
                case 6 :
                    // InternalCompleteOCL.g:1844:2: kw= 'OclAny'
                    {
                    kw=(Token)match(input,47,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getPrimitiveTypeIdentifierAccess().getOclAnyKeyword_5());

                    }

                    }
                    break;
                case 7 :
                    // InternalCompleteOCL.g:1851:2: kw= 'OclInvalid'
                    {
                    kw=(Token)match(input,48,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getPrimitiveTypeIdentifierAccess().getOclInvalidKeyword_6());

                    }

                    }
                    break;
                case 8 :
                    // InternalCompleteOCL.g:1858:2: kw= 'OclMessage'
                    {
                    kw=(Token)match(input,49,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getPrimitiveTypeIdentifierAccess().getOclMessageKeyword_7());

                    }

                    }
                    break;
                case 9 :
                    // InternalCompleteOCL.g:1865:2: kw= 'OclState'
                    {
                    kw=(Token)match(input,50,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getPrimitiveTypeIdentifierAccess().getOclStateKeyword_8());

                    }

                    }
                    break;
                case 10 :
                    // InternalCompleteOCL.g:1872:2: kw= 'OclVoid'
                    {
                    kw=(Token)match(input,51,FollowSets000.FOLLOW_2); if (state.failed) return current;
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
    // InternalCompleteOCL.g:1889:1: entryRuleEssentialOCLUnaryOperatorName returns [String current=null] : iv_ruleEssentialOCLUnaryOperatorName= ruleEssentialOCLUnaryOperatorName EOF ;
    public final String entryRuleEssentialOCLUnaryOperatorName() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleEssentialOCLUnaryOperatorName = null;


        try {
            // InternalCompleteOCL.g:1890:2: (iv_ruleEssentialOCLUnaryOperatorName= ruleEssentialOCLUnaryOperatorName EOF )
            // InternalCompleteOCL.g:1891:2: iv_ruleEssentialOCLUnaryOperatorName= ruleEssentialOCLUnaryOperatorName EOF
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
    // InternalCompleteOCL.g:1898:1: ruleEssentialOCLUnaryOperatorName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= '-' | kw= 'not' ) ;
    public final AntlrDatatypeRuleToken ruleEssentialOCLUnaryOperatorName() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule();

        try {
            // InternalCompleteOCL.g:1901:28: ( (kw= '-' | kw= 'not' ) )
            // InternalCompleteOCL.g:1902:1: (kw= '-' | kw= 'not' )
            {
            // InternalCompleteOCL.g:1902:1: (kw= '-' | kw= 'not' )
            int alt41=2;
            int LA41_0 = input.LA(1);

            if ( (LA41_0==52) ) {
                alt41=1;
            }
            else if ( (LA41_0==53) ) {
                alt41=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 41, 0, input);

                throw nvae;
            }
            switch (alt41) {
                case 1 :
                    // InternalCompleteOCL.g:1903:2: kw= '-'
                    {
                    kw=(Token)match(input,52,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getEssentialOCLUnaryOperatorNameAccess().getHyphenMinusKeyword_0());

                    }

                    }
                    break;
                case 2 :
                    // InternalCompleteOCL.g:1910:2: kw= 'not'
                    {
                    kw=(Token)match(input,53,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getEssentialOCLUnaryOperatorNameAccess().getNotKeyword_1());

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
    // InternalCompleteOCL.g:1923:1: entryRuleEssentialOCLInfixOperatorName returns [String current=null] : iv_ruleEssentialOCLInfixOperatorName= ruleEssentialOCLInfixOperatorName EOF ;
    public final String entryRuleEssentialOCLInfixOperatorName() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleEssentialOCLInfixOperatorName = null;


        try {
            // InternalCompleteOCL.g:1924:2: (iv_ruleEssentialOCLInfixOperatorName= ruleEssentialOCLInfixOperatorName EOF )
            // InternalCompleteOCL.g:1925:2: iv_ruleEssentialOCLInfixOperatorName= ruleEssentialOCLInfixOperatorName EOF
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
    // InternalCompleteOCL.g:1932:1: ruleEssentialOCLInfixOperatorName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= '*' | kw= '/' | kw= '+' | kw= '-' | kw= '>' | kw= '<' | kw= '>=' | kw= '<=' | kw= '=' | kw= '<>' | kw= 'and' | kw= 'or' | kw= 'xor' | kw= 'implies' ) ;
    public final AntlrDatatypeRuleToken ruleEssentialOCLInfixOperatorName() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule();

        try {
            // InternalCompleteOCL.g:1935:28: ( (kw= '*' | kw= '/' | kw= '+' | kw= '-' | kw= '>' | kw= '<' | kw= '>=' | kw= '<=' | kw= '=' | kw= '<>' | kw= 'and' | kw= 'or' | kw= 'xor' | kw= 'implies' ) )
            // InternalCompleteOCL.g:1936:1: (kw= '*' | kw= '/' | kw= '+' | kw= '-' | kw= '>' | kw= '<' | kw= '>=' | kw= '<=' | kw= '=' | kw= '<>' | kw= 'and' | kw= 'or' | kw= 'xor' | kw= 'implies' )
            {
            // InternalCompleteOCL.g:1936:1: (kw= '*' | kw= '/' | kw= '+' | kw= '-' | kw= '>' | kw= '<' | kw= '>=' | kw= '<=' | kw= '=' | kw= '<>' | kw= 'and' | kw= 'or' | kw= 'xor' | kw= 'implies' )
            int alt42=14;
            switch ( input.LA(1) ) {
            case 54:
                {
                alt42=1;
                }
                break;
            case 55:
                {
                alt42=2;
                }
                break;
            case 56:
                {
                alt42=3;
                }
                break;
            case 52:
                {
                alt42=4;
                }
                break;
            case 40:
                {
                alt42=5;
                }
                break;
            case 39:
                {
                alt42=6;
                }
                break;
            case 57:
                {
                alt42=7;
                }
                break;
            case 58:
                {
                alt42=8;
                }
                break;
            case 27:
                {
                alt42=9;
                }
                break;
            case 59:
                {
                alt42=10;
                }
                break;
            case 60:
                {
                alt42=11;
                }
                break;
            case 61:
                {
                alt42=12;
                }
                break;
            case 62:
                {
                alt42=13;
                }
                break;
            case 63:
                {
                alt42=14;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 42, 0, input);

                throw nvae;
            }

            switch (alt42) {
                case 1 :
                    // InternalCompleteOCL.g:1937:2: kw= '*'
                    {
                    kw=(Token)match(input,54,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getEssentialOCLInfixOperatorNameAccess().getAsteriskKeyword_0());

                    }

                    }
                    break;
                case 2 :
                    // InternalCompleteOCL.g:1944:2: kw= '/'
                    {
                    kw=(Token)match(input,55,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getEssentialOCLInfixOperatorNameAccess().getSolidusKeyword_1());

                    }

                    }
                    break;
                case 3 :
                    // InternalCompleteOCL.g:1951:2: kw= '+'
                    {
                    kw=(Token)match(input,56,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getEssentialOCLInfixOperatorNameAccess().getPlusSignKeyword_2());

                    }

                    }
                    break;
                case 4 :
                    // InternalCompleteOCL.g:1958:2: kw= '-'
                    {
                    kw=(Token)match(input,52,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getEssentialOCLInfixOperatorNameAccess().getHyphenMinusKeyword_3());

                    }

                    }
                    break;
                case 5 :
                    // InternalCompleteOCL.g:1965:2: kw= '>'
                    {
                    kw=(Token)match(input,40,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getEssentialOCLInfixOperatorNameAccess().getGreaterThanSignKeyword_4());

                    }

                    }
                    break;
                case 6 :
                    // InternalCompleteOCL.g:1972:2: kw= '<'
                    {
                    kw=(Token)match(input,39,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getEssentialOCLInfixOperatorNameAccess().getLessThanSignKeyword_5());

                    }

                    }
                    break;
                case 7 :
                    // InternalCompleteOCL.g:1979:2: kw= '>='
                    {
                    kw=(Token)match(input,57,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getEssentialOCLInfixOperatorNameAccess().getGreaterThanSignEqualsSignKeyword_6());

                    }

                    }
                    break;
                case 8 :
                    // InternalCompleteOCL.g:1986:2: kw= '<='
                    {
                    kw=(Token)match(input,58,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getEssentialOCLInfixOperatorNameAccess().getLessThanSignEqualsSignKeyword_7());

                    }

                    }
                    break;
                case 9 :
                    // InternalCompleteOCL.g:1993:2: kw= '='
                    {
                    kw=(Token)match(input,27,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getEssentialOCLInfixOperatorNameAccess().getEqualsSignKeyword_8());

                    }

                    }
                    break;
                case 10 :
                    // InternalCompleteOCL.g:2000:2: kw= '<>'
                    {
                    kw=(Token)match(input,59,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getEssentialOCLInfixOperatorNameAccess().getLessThanSignGreaterThanSignKeyword_9());

                    }

                    }
                    break;
                case 11 :
                    // InternalCompleteOCL.g:2007:2: kw= 'and'
                    {
                    kw=(Token)match(input,60,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getEssentialOCLInfixOperatorNameAccess().getAndKeyword_10());

                    }

                    }
                    break;
                case 12 :
                    // InternalCompleteOCL.g:2014:2: kw= 'or'
                    {
                    kw=(Token)match(input,61,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getEssentialOCLInfixOperatorNameAccess().getOrKeyword_11());

                    }

                    }
                    break;
                case 13 :
                    // InternalCompleteOCL.g:2021:2: kw= 'xor'
                    {
                    kw=(Token)match(input,62,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getEssentialOCLInfixOperatorNameAccess().getXorKeyword_12());

                    }

                    }
                    break;
                case 14 :
                    // InternalCompleteOCL.g:2028:2: kw= 'implies'
                    {
                    kw=(Token)match(input,63,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getEssentialOCLInfixOperatorNameAccess().getImpliesKeyword_13());

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
    // InternalCompleteOCL.g:2041:1: entryRuleEssentialOCLNavigationOperatorName returns [String current=null] : iv_ruleEssentialOCLNavigationOperatorName= ruleEssentialOCLNavigationOperatorName EOF ;
    public final String entryRuleEssentialOCLNavigationOperatorName() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleEssentialOCLNavigationOperatorName = null;


        try {
            // InternalCompleteOCL.g:2042:2: (iv_ruleEssentialOCLNavigationOperatorName= ruleEssentialOCLNavigationOperatorName EOF )
            // InternalCompleteOCL.g:2043:2: iv_ruleEssentialOCLNavigationOperatorName= ruleEssentialOCLNavigationOperatorName EOF
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
    // InternalCompleteOCL.g:2050:1: ruleEssentialOCLNavigationOperatorName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= '.' | kw= '->' | kw= '?.' | kw= '?->' ) ;
    public final AntlrDatatypeRuleToken ruleEssentialOCLNavigationOperatorName() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule();

        try {
            // InternalCompleteOCL.g:2053:28: ( (kw= '.' | kw= '->' | kw= '?.' | kw= '?->' ) )
            // InternalCompleteOCL.g:2054:1: (kw= '.' | kw= '->' | kw= '?.' | kw= '?->' )
            {
            // InternalCompleteOCL.g:2054:1: (kw= '.' | kw= '->' | kw= '?.' | kw= '?->' )
            int alt43=4;
            switch ( input.LA(1) ) {
            case 64:
                {
                alt43=1;
                }
                break;
            case 65:
                {
                alt43=2;
                }
                break;
            case 66:
                {
                alt43=3;
                }
                break;
            case 67:
                {
                alt43=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 43, 0, input);

                throw nvae;
            }

            switch (alt43) {
                case 1 :
                    // InternalCompleteOCL.g:2055:2: kw= '.'
                    {
                    kw=(Token)match(input,64,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getEssentialOCLNavigationOperatorNameAccess().getFullStopKeyword_0());

                    }

                    }
                    break;
                case 2 :
                    // InternalCompleteOCL.g:2062:2: kw= '->'
                    {
                    kw=(Token)match(input,65,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getEssentialOCLNavigationOperatorNameAccess().getHyphenMinusGreaterThanSignKeyword_1());

                    }

                    }
                    break;
                case 3 :
                    // InternalCompleteOCL.g:2069:2: kw= '?.'
                    {
                    kw=(Token)match(input,66,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getEssentialOCLNavigationOperatorNameAccess().getQuestionMarkFullStopKeyword_2());

                    }

                    }
                    break;
                case 4 :
                    // InternalCompleteOCL.g:2076:2: kw= '?->'
                    {
                    kw=(Token)match(input,67,FollowSets000.FOLLOW_2); if (state.failed) return current;
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
    // InternalCompleteOCL.g:2089:1: entryRuleBinaryOperatorName returns [String current=null] : iv_ruleBinaryOperatorName= ruleBinaryOperatorName EOF ;
    public final String entryRuleBinaryOperatorName() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleBinaryOperatorName = null;


        try {
            // InternalCompleteOCL.g:2090:2: (iv_ruleBinaryOperatorName= ruleBinaryOperatorName EOF )
            // InternalCompleteOCL.g:2091:2: iv_ruleBinaryOperatorName= ruleBinaryOperatorName EOF
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
    // InternalCompleteOCL.g:2098:1: ruleBinaryOperatorName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_InfixOperatorName_0= ruleInfixOperatorName | this_NavigationOperatorName_1= ruleNavigationOperatorName ) ;
    public final AntlrDatatypeRuleToken ruleBinaryOperatorName() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        AntlrDatatypeRuleToken this_InfixOperatorName_0 = null;

        AntlrDatatypeRuleToken this_NavigationOperatorName_1 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:2101:28: ( (this_InfixOperatorName_0= ruleInfixOperatorName | this_NavigationOperatorName_1= ruleNavigationOperatorName ) )
            // InternalCompleteOCL.g:2102:1: (this_InfixOperatorName_0= ruleInfixOperatorName | this_NavigationOperatorName_1= ruleNavigationOperatorName )
            {
            // InternalCompleteOCL.g:2102:1: (this_InfixOperatorName_0= ruleInfixOperatorName | this_NavigationOperatorName_1= ruleNavigationOperatorName )
            int alt44=2;
            int LA44_0 = input.LA(1);

            if ( (LA44_0==27||(LA44_0>=39 && LA44_0<=40)||LA44_0==52||(LA44_0>=54 && LA44_0<=63)) ) {
                alt44=1;
            }
            else if ( ((LA44_0>=17 && LA44_0<=18)||(LA44_0>=64 && LA44_0<=67)) ) {
                alt44=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 44, 0, input);

                throw nvae;
            }
            switch (alt44) {
                case 1 :
                    // InternalCompleteOCL.g:2103:5: this_InfixOperatorName_0= ruleInfixOperatorName
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
                    // InternalCompleteOCL.g:2115:5: this_NavigationOperatorName_1= ruleNavigationOperatorName
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
    // InternalCompleteOCL.g:2133:1: entryRuleInfixOperatorName returns [String current=null] : iv_ruleInfixOperatorName= ruleInfixOperatorName EOF ;
    public final String entryRuleInfixOperatorName() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleInfixOperatorName = null;


        try {
            // InternalCompleteOCL.g:2134:2: (iv_ruleInfixOperatorName= ruleInfixOperatorName EOF )
            // InternalCompleteOCL.g:2135:2: iv_ruleInfixOperatorName= ruleInfixOperatorName EOF
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
    // InternalCompleteOCL.g:2142:1: ruleInfixOperatorName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_EssentialOCLInfixOperatorName_0= ruleEssentialOCLInfixOperatorName ;
    public final AntlrDatatypeRuleToken ruleInfixOperatorName() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        AntlrDatatypeRuleToken this_EssentialOCLInfixOperatorName_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:2145:28: (this_EssentialOCLInfixOperatorName_0= ruleEssentialOCLInfixOperatorName )
            // InternalCompleteOCL.g:2147:5: this_EssentialOCLInfixOperatorName_0= ruleEssentialOCLInfixOperatorName
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
    // InternalCompleteOCL.g:2165:1: entryRuleUnaryOperatorName returns [String current=null] : iv_ruleUnaryOperatorName= ruleUnaryOperatorName EOF ;
    public final String entryRuleUnaryOperatorName() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleUnaryOperatorName = null;


        try {
            // InternalCompleteOCL.g:2166:2: (iv_ruleUnaryOperatorName= ruleUnaryOperatorName EOF )
            // InternalCompleteOCL.g:2167:2: iv_ruleUnaryOperatorName= ruleUnaryOperatorName EOF
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
    // InternalCompleteOCL.g:2174:1: ruleUnaryOperatorName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_EssentialOCLUnaryOperatorName_0= ruleEssentialOCLUnaryOperatorName ;
    public final AntlrDatatypeRuleToken ruleUnaryOperatorName() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        AntlrDatatypeRuleToken this_EssentialOCLUnaryOperatorName_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:2177:28: (this_EssentialOCLUnaryOperatorName_0= ruleEssentialOCLUnaryOperatorName )
            // InternalCompleteOCL.g:2179:5: this_EssentialOCLUnaryOperatorName_0= ruleEssentialOCLUnaryOperatorName
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
    // InternalCompleteOCL.g:2197:1: entryRuleEssentialOCLUnrestrictedName returns [String current=null] : iv_ruleEssentialOCLUnrestrictedName= ruleEssentialOCLUnrestrictedName EOF ;
    public final String entryRuleEssentialOCLUnrestrictedName() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleEssentialOCLUnrestrictedName = null;


        try {
            // InternalCompleteOCL.g:2198:2: (iv_ruleEssentialOCLUnrestrictedName= ruleEssentialOCLUnrestrictedName EOF )
            // InternalCompleteOCL.g:2199:2: iv_ruleEssentialOCLUnrestrictedName= ruleEssentialOCLUnrestrictedName EOF
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
    // InternalCompleteOCL.g:2206:1: ruleEssentialOCLUnrestrictedName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_Identifier_0= ruleIdentifier ;
    public final AntlrDatatypeRuleToken ruleEssentialOCLUnrestrictedName() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        AntlrDatatypeRuleToken this_Identifier_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:2209:28: (this_Identifier_0= ruleIdentifier )
            // InternalCompleteOCL.g:2211:5: this_Identifier_0= ruleIdentifier
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
    // InternalCompleteOCL.g:2229:1: entryRuleEssentialOCLUnreservedName returns [String current=null] : iv_ruleEssentialOCLUnreservedName= ruleEssentialOCLUnreservedName EOF ;
    public final String entryRuleEssentialOCLUnreservedName() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleEssentialOCLUnreservedName = null;


        try {
            // InternalCompleteOCL.g:2230:2: (iv_ruleEssentialOCLUnreservedName= ruleEssentialOCLUnreservedName EOF )
            // InternalCompleteOCL.g:2231:2: iv_ruleEssentialOCLUnreservedName= ruleEssentialOCLUnreservedName EOF
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
    // InternalCompleteOCL.g:2238:1: ruleEssentialOCLUnreservedName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_UnrestrictedName_0= ruleUnrestrictedName | this_CollectionTypeIdentifier_1= ruleCollectionTypeIdentifier | this_PrimitiveTypeIdentifier_2= rulePrimitiveTypeIdentifier | kw= 'Map' | kw= 'Tuple' ) ;
    public final AntlrDatatypeRuleToken ruleEssentialOCLUnreservedName() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        AntlrDatatypeRuleToken this_UnrestrictedName_0 = null;

        AntlrDatatypeRuleToken this_CollectionTypeIdentifier_1 = null;

        AntlrDatatypeRuleToken this_PrimitiveTypeIdentifier_2 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:2241:28: ( (this_UnrestrictedName_0= ruleUnrestrictedName | this_CollectionTypeIdentifier_1= ruleCollectionTypeIdentifier | this_PrimitiveTypeIdentifier_2= rulePrimitiveTypeIdentifier | kw= 'Map' | kw= 'Tuple' ) )
            // InternalCompleteOCL.g:2242:1: (this_UnrestrictedName_0= ruleUnrestrictedName | this_CollectionTypeIdentifier_1= ruleCollectionTypeIdentifier | this_PrimitiveTypeIdentifier_2= rulePrimitiveTypeIdentifier | kw= 'Map' | kw= 'Tuple' )
            {
            // InternalCompleteOCL.g:2242:1: (this_UnrestrictedName_0= ruleUnrestrictedName | this_CollectionTypeIdentifier_1= ruleCollectionTypeIdentifier | this_PrimitiveTypeIdentifier_2= rulePrimitiveTypeIdentifier | kw= 'Map' | kw= 'Tuple' )
            int alt45=5;
            switch ( input.LA(1) ) {
            case RULE_SIMPLE_ID:
            case RULE_ESCAPED_ID:
            case 28:
            case 29:
            case 30:
                {
                alt45=1;
                }
                break;
            case 71:
            case 72:
            case 73:
            case 74:
            case 75:
                {
                alt45=2;
                }
                break;
            case 42:
            case 43:
            case 44:
            case 45:
            case 46:
            case 47:
            case 48:
            case 49:
            case 50:
            case 51:
                {
                alt45=3;
                }
                break;
            case 68:
                {
                alt45=4;
                }
                break;
            case 69:
                {
                alt45=5;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 45, 0, input);

                throw nvae;
            }

            switch (alt45) {
                case 1 :
                    // InternalCompleteOCL.g:2243:5: this_UnrestrictedName_0= ruleUnrestrictedName
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
                    // InternalCompleteOCL.g:2255:5: this_CollectionTypeIdentifier_1= ruleCollectionTypeIdentifier
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
                    // InternalCompleteOCL.g:2267:5: this_PrimitiveTypeIdentifier_2= rulePrimitiveTypeIdentifier
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
                    // InternalCompleteOCL.g:2279:2: kw= 'Map'
                    {
                    kw=(Token)match(input,68,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getEssentialOCLUnreservedNameAccess().getMapKeyword_3());

                    }

                    }
                    break;
                case 5 :
                    // InternalCompleteOCL.g:2286:2: kw= 'Tuple'
                    {
                    kw=(Token)match(input,69,FollowSets000.FOLLOW_2); if (state.failed) return current;
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
    // InternalCompleteOCL.g:2299:1: entryRuleUnreservedName returns [String current=null] : iv_ruleUnreservedName= ruleUnreservedName EOF ;
    public final String entryRuleUnreservedName() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleUnreservedName = null;


        try {
            // InternalCompleteOCL.g:2300:2: (iv_ruleUnreservedName= ruleUnreservedName EOF )
            // InternalCompleteOCL.g:2301:2: iv_ruleUnreservedName= ruleUnreservedName EOF
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
    // InternalCompleteOCL.g:2308:1: ruleUnreservedName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_EssentialOCLUnreservedName_0= ruleEssentialOCLUnreservedName ;
    public final AntlrDatatypeRuleToken ruleUnreservedName() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        AntlrDatatypeRuleToken this_EssentialOCLUnreservedName_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:2311:28: (this_EssentialOCLUnreservedName_0= ruleEssentialOCLUnreservedName )
            // InternalCompleteOCL.g:2313:5: this_EssentialOCLUnreservedName_0= ruleEssentialOCLUnreservedName
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
    // InternalCompleteOCL.g:2331:1: entryRuleURIPathNameCS returns [EObject current=null] : iv_ruleURIPathNameCS= ruleURIPathNameCS EOF ;
    public final EObject entryRuleURIPathNameCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleURIPathNameCS = null;


        try {
            // InternalCompleteOCL.g:2332:2: (iv_ruleURIPathNameCS= ruleURIPathNameCS EOF )
            // InternalCompleteOCL.g:2333:2: iv_ruleURIPathNameCS= ruleURIPathNameCS EOF
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
    // InternalCompleteOCL.g:2340:1: ruleURIPathNameCS returns [EObject current=null] : ( ( (lv_ownedPathElements_0_0= ruleURIFirstPathElementCS ) ) (otherlv_1= '::' ( (lv_ownedPathElements_2_0= ruleNextPathElementCS ) ) )* ) ;
    public final EObject ruleURIPathNameCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject lv_ownedPathElements_0_0 = null;

        EObject lv_ownedPathElements_2_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:2343:28: ( ( ( (lv_ownedPathElements_0_0= ruleURIFirstPathElementCS ) ) (otherlv_1= '::' ( (lv_ownedPathElements_2_0= ruleNextPathElementCS ) ) )* ) )
            // InternalCompleteOCL.g:2344:1: ( ( (lv_ownedPathElements_0_0= ruleURIFirstPathElementCS ) ) (otherlv_1= '::' ( (lv_ownedPathElements_2_0= ruleNextPathElementCS ) ) )* )
            {
            // InternalCompleteOCL.g:2344:1: ( ( (lv_ownedPathElements_0_0= ruleURIFirstPathElementCS ) ) (otherlv_1= '::' ( (lv_ownedPathElements_2_0= ruleNextPathElementCS ) ) )* )
            // InternalCompleteOCL.g:2344:2: ( (lv_ownedPathElements_0_0= ruleURIFirstPathElementCS ) ) (otherlv_1= '::' ( (lv_ownedPathElements_2_0= ruleNextPathElementCS ) ) )*
            {
            // InternalCompleteOCL.g:2344:2: ( (lv_ownedPathElements_0_0= ruleURIFirstPathElementCS ) )
            // InternalCompleteOCL.g:2345:1: (lv_ownedPathElements_0_0= ruleURIFirstPathElementCS )
            {
            // InternalCompleteOCL.g:2345:1: (lv_ownedPathElements_0_0= ruleURIFirstPathElementCS )
            // InternalCompleteOCL.g:2346:3: lv_ownedPathElements_0_0= ruleURIFirstPathElementCS
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getURIPathNameCSAccess().getOwnedPathElementsURIFirstPathElementCSParserRuleCall_0_0());

            }
            pushFollow(FollowSets000.FOLLOW_30);
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

            // InternalCompleteOCL.g:2362:2: (otherlv_1= '::' ( (lv_ownedPathElements_2_0= ruleNextPathElementCS ) ) )*
            loop46:
            do {
                int alt46=2;
                int LA46_0 = input.LA(1);

                if ( (LA46_0==70) ) {
                    alt46=1;
                }


                switch (alt46) {
            	case 1 :
            	    // InternalCompleteOCL.g:2362:4: otherlv_1= '::' ( (lv_ownedPathElements_2_0= ruleNextPathElementCS ) )
            	    {
            	    otherlv_1=(Token)match(input,70,FollowSets000.FOLLOW_31); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_1, grammarAccess.getURIPathNameCSAccess().getColonColonKeyword_1_0());

            	    }
            	    // InternalCompleteOCL.g:2366:1: ( (lv_ownedPathElements_2_0= ruleNextPathElementCS ) )
            	    // InternalCompleteOCL.g:2367:1: (lv_ownedPathElements_2_0= ruleNextPathElementCS )
            	    {
            	    // InternalCompleteOCL.g:2367:1: (lv_ownedPathElements_2_0= ruleNextPathElementCS )
            	    // InternalCompleteOCL.g:2368:3: lv_ownedPathElements_2_0= ruleNextPathElementCS
            	    {
            	    if ( state.backtracking==0 ) {

            	      	        newCompositeNode(grammarAccess.getURIPathNameCSAccess().getOwnedPathElementsNextPathElementCSParserRuleCall_1_1_0());

            	    }
            	    pushFollow(FollowSets000.FOLLOW_30);
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
            	    break loop46;
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
    // InternalCompleteOCL.g:2392:1: entryRuleURIFirstPathElementCS returns [EObject current=null] : iv_ruleURIFirstPathElementCS= ruleURIFirstPathElementCS EOF ;
    public final EObject entryRuleURIFirstPathElementCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleURIFirstPathElementCS = null;


        try {
            // InternalCompleteOCL.g:2393:2: (iv_ruleURIFirstPathElementCS= ruleURIFirstPathElementCS EOF )
            // InternalCompleteOCL.g:2394:2: iv_ruleURIFirstPathElementCS= ruleURIFirstPathElementCS EOF
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
    // InternalCompleteOCL.g:2401:1: ruleURIFirstPathElementCS returns [EObject current=null] : ( ( ( ruleUnrestrictedName ) ) | ( () ( ( ruleURI ) ) ) ) ;
    public final EObject ruleURIFirstPathElementCS() throws RecognitionException {
        EObject current = null;

         enterRule();

        try {
            // InternalCompleteOCL.g:2404:28: ( ( ( ( ruleUnrestrictedName ) ) | ( () ( ( ruleURI ) ) ) ) )
            // InternalCompleteOCL.g:2405:1: ( ( ( ruleUnrestrictedName ) ) | ( () ( ( ruleURI ) ) ) )
            {
            // InternalCompleteOCL.g:2405:1: ( ( ( ruleUnrestrictedName ) ) | ( () ( ( ruleURI ) ) ) )
            int alt47=2;
            int LA47_0 = input.LA(1);

            if ( ((LA47_0>=RULE_SIMPLE_ID && LA47_0<=RULE_ESCAPED_ID)||(LA47_0>=28 && LA47_0<=30)) ) {
                alt47=1;
            }
            else if ( (LA47_0==RULE_SINGLE_QUOTED_STRING) ) {
                alt47=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 47, 0, input);

                throw nvae;
            }
            switch (alt47) {
                case 1 :
                    // InternalCompleteOCL.g:2405:2: ( ( ruleUnrestrictedName ) )
                    {
                    // InternalCompleteOCL.g:2405:2: ( ( ruleUnrestrictedName ) )
                    // InternalCompleteOCL.g:2406:1: ( ruleUnrestrictedName )
                    {
                    // InternalCompleteOCL.g:2406:1: ( ruleUnrestrictedName )
                    // InternalCompleteOCL.g:2407:3: ruleUnrestrictedName
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
                    // InternalCompleteOCL.g:2424:6: ( () ( ( ruleURI ) ) )
                    {
                    // InternalCompleteOCL.g:2424:6: ( () ( ( ruleURI ) ) )
                    // InternalCompleteOCL.g:2424:7: () ( ( ruleURI ) )
                    {
                    // InternalCompleteOCL.g:2424:7: ()
                    // InternalCompleteOCL.g:2425:2:
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

                    // InternalCompleteOCL.g:2433:2: ( ( ruleURI ) )
                    // InternalCompleteOCL.g:2434:1: ( ruleURI )
                    {
                    // InternalCompleteOCL.g:2434:1: ( ruleURI )
                    // InternalCompleteOCL.g:2435:3: ruleURI
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
    // InternalCompleteOCL.g:2459:1: entryRulePrimitiveTypeCS returns [EObject current=null] : iv_rulePrimitiveTypeCS= rulePrimitiveTypeCS EOF ;
    public final EObject entryRulePrimitiveTypeCS() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePrimitiveTypeCS = null;


        try {
            // InternalCompleteOCL.g:2460:2: (iv_rulePrimitiveTypeCS= rulePrimitiveTypeCS EOF )
            // InternalCompleteOCL.g:2461:2: iv_rulePrimitiveTypeCS= rulePrimitiveTypeCS EOF
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
    // InternalCompleteOCL.g:2468:1: rulePrimitiveTypeCS returns [EObject current=null] : ( (lv_name_0_0= rulePrimitiveTypeIdentifier ) ) ;
    public final EObject rulePrimitiveTypeCS() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_name_0_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:2471:28: ( ( (lv_name_0_0= rulePrimitiveTypeIdentifier ) ) )
            // InternalCompleteOCL.g:2472:1: ( (lv_name_0_0= rulePrimitiveTypeIdentifier ) )
            {
            // InternalCompleteOCL.g:2472:1: ( (lv_name_0_0= rulePrimitiveTypeIdentifier ) )
            // InternalCompleteOCL.g:2473:1: (lv_name_0_0= rulePrimitiveTypeIdentifier )
            {
            // InternalCompleteOCL.g:2473:1: (lv_name_0_0= rulePrimitiveTypeIdentifier )
            // InternalCompleteOCL.g:2474:3: lv_name_0_0= rulePrimitiveTypeIdentifier
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
    // InternalCompleteOCL.g:2498:1: entryRuleCollectionTypeIdentifier returns [String current=null] : iv_ruleCollectionTypeIdentifier= ruleCollectionTypeIdentifier EOF ;
    public final String entryRuleCollectionTypeIdentifier() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleCollectionTypeIdentifier = null;


        try {
            // InternalCompleteOCL.g:2499:2: (iv_ruleCollectionTypeIdentifier= ruleCollectionTypeIdentifier EOF )
            // InternalCompleteOCL.g:2500:2: iv_ruleCollectionTypeIdentifier= ruleCollectionTypeIdentifier EOF
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
    // InternalCompleteOCL.g:2507:1: ruleCollectionTypeIdentifier returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= 'Set' | kw= 'Bag' | kw= 'Sequence' | kw= 'Collection' | kw= 'OrderedSet' ) ;
    public final AntlrDatatypeRuleToken ruleCollectionTypeIdentifier() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule();

        try {
            // InternalCompleteOCL.g:2510:28: ( (kw= 'Set' | kw= 'Bag' | kw= 'Sequence' | kw= 'Collection' | kw= 'OrderedSet' ) )
            // InternalCompleteOCL.g:2511:1: (kw= 'Set' | kw= 'Bag' | kw= 'Sequence' | kw= 'Collection' | kw= 'OrderedSet' )
            {
            // InternalCompleteOCL.g:2511:1: (kw= 'Set' | kw= 'Bag' | kw= 'Sequence' | kw= 'Collection' | kw= 'OrderedSet' )
            int alt48=5;
            switch ( input.LA(1) ) {
            case 71:
                {
                alt48=1;
                }
                break;
            case 72:
                {
                alt48=2;
                }
                break;
            case 73:
                {
                alt48=3;
                }
                break;
            case 74:
                {
                alt48=4;
                }
                break;
            case 75:
                {
                alt48=5;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 48, 0, input);

                throw nvae;
            }

            switch (alt48) {
                case 1 :
                    // InternalCompleteOCL.g:2512:2: kw= 'Set'
                    {
                    kw=(Token)match(input,71,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getCollectionTypeIdentifierAccess().getSetKeyword_0());

                    }

                    }
                    break;
                case 2 :
                    // InternalCompleteOCL.g:2519:2: kw= 'Bag'
                    {
                    kw=(Token)match(input,72,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getCollectionTypeIdentifierAccess().getBagKeyword_1());

                    }

                    }
                    break;
                case 3 :
                    // InternalCompleteOCL.g:2526:2: kw= 'Sequence'
                    {
                    kw=(Token)match(input,73,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getCollectionTypeIdentifierAccess().getSequenceKeyword_2());

                    }

                    }
                    break;
                case 4 :
                    // InternalCompleteOCL.g:2533:2: kw= 'Collection'
                    {
                    kw=(Token)match(input,74,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getCollectionTypeIdentifierAccess().getCollectionKeyword_3());

                    }

                    }
                    break;
                case 5 :
                    // InternalCompleteOCL.g:2540:2: kw= 'OrderedSet'
                    {
                    kw=(Token)match(input,75,FollowSets000.FOLLOW_2); if (state.failed) return current;
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
    // InternalCompleteOCL.g:2553:1: entryRuleCollectionTypeCS returns [EObject current=null] : iv_ruleCollectionTypeCS= ruleCollectionTypeCS EOF ;
    public final EObject entryRuleCollectionTypeCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCollectionTypeCS = null;


        try {
            // InternalCompleteOCL.g:2554:2: (iv_ruleCollectionTypeCS= ruleCollectionTypeCS EOF )
            // InternalCompleteOCL.g:2555:2: iv_ruleCollectionTypeCS= ruleCollectionTypeCS EOF
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
    // InternalCompleteOCL.g:2562:1: ruleCollectionTypeCS returns [EObject current=null] : ( ( (lv_name_0_0= ruleCollectionTypeIdentifier ) ) (otherlv_1= '(' ( (lv_ownedType_2_0= ruleTypeExpWithoutMultiplicityCS ) ) ( (lv_ownedCollectionMultiplicity_3_0= ruleMultiplicityCS ) )? otherlv_4= ')' )? ) ;
    public final EObject ruleCollectionTypeCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_4=null;
        AntlrDatatypeRuleToken lv_name_0_0 = null;

        EObject lv_ownedType_2_0 = null;

        EObject lv_ownedCollectionMultiplicity_3_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:2565:28: ( ( ( (lv_name_0_0= ruleCollectionTypeIdentifier ) ) (otherlv_1= '(' ( (lv_ownedType_2_0= ruleTypeExpWithoutMultiplicityCS ) ) ( (lv_ownedCollectionMultiplicity_3_0= ruleMultiplicityCS ) )? otherlv_4= ')' )? ) )
            // InternalCompleteOCL.g:2566:1: ( ( (lv_name_0_0= ruleCollectionTypeIdentifier ) ) (otherlv_1= '(' ( (lv_ownedType_2_0= ruleTypeExpWithoutMultiplicityCS ) ) ( (lv_ownedCollectionMultiplicity_3_0= ruleMultiplicityCS ) )? otherlv_4= ')' )? )
            {
            // InternalCompleteOCL.g:2566:1: ( ( (lv_name_0_0= ruleCollectionTypeIdentifier ) ) (otherlv_1= '(' ( (lv_ownedType_2_0= ruleTypeExpWithoutMultiplicityCS ) ) ( (lv_ownedCollectionMultiplicity_3_0= ruleMultiplicityCS ) )? otherlv_4= ')' )? )
            // InternalCompleteOCL.g:2566:2: ( (lv_name_0_0= ruleCollectionTypeIdentifier ) ) (otherlv_1= '(' ( (lv_ownedType_2_0= ruleTypeExpWithoutMultiplicityCS ) ) ( (lv_ownedCollectionMultiplicity_3_0= ruleMultiplicityCS ) )? otherlv_4= ')' )?
            {
            // InternalCompleteOCL.g:2566:2: ( (lv_name_0_0= ruleCollectionTypeIdentifier ) )
            // InternalCompleteOCL.g:2567:1: (lv_name_0_0= ruleCollectionTypeIdentifier )
            {
            // InternalCompleteOCL.g:2567:1: (lv_name_0_0= ruleCollectionTypeIdentifier )
            // InternalCompleteOCL.g:2568:3: lv_name_0_0= ruleCollectionTypeIdentifier
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getCollectionTypeCSAccess().getNameCollectionTypeIdentifierParserRuleCall_0_0());

            }
            pushFollow(FollowSets000.FOLLOW_32);
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

            // InternalCompleteOCL.g:2584:2: (otherlv_1= '(' ( (lv_ownedType_2_0= ruleTypeExpWithoutMultiplicityCS ) ) ( (lv_ownedCollectionMultiplicity_3_0= ruleMultiplicityCS ) )? otherlv_4= ')' )?
            int alt50=2;
            int LA50_0 = input.LA(1);

            if ( (LA50_0==21) ) {
                alt50=1;
            }
            switch (alt50) {
                case 1 :
                    // InternalCompleteOCL.g:2584:4: otherlv_1= '(' ( (lv_ownedType_2_0= ruleTypeExpWithoutMultiplicityCS ) ) ( (lv_ownedCollectionMultiplicity_3_0= ruleMultiplicityCS ) )? otherlv_4= ')'
                    {
                    otherlv_1=(Token)match(input,21,FollowSets000.FOLLOW_20); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_1, grammarAccess.getCollectionTypeCSAccess().getLeftParenthesisKeyword_1_0());

                    }
                    // InternalCompleteOCL.g:2588:1: ( (lv_ownedType_2_0= ruleTypeExpWithoutMultiplicityCS ) )
                    // InternalCompleteOCL.g:2589:1: (lv_ownedType_2_0= ruleTypeExpWithoutMultiplicityCS )
                    {
                    // InternalCompleteOCL.g:2589:1: (lv_ownedType_2_0= ruleTypeExpWithoutMultiplicityCS )
                    // InternalCompleteOCL.g:2590:3: lv_ownedType_2_0= ruleTypeExpWithoutMultiplicityCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getCollectionTypeCSAccess().getOwnedTypeTypeExpWithoutMultiplicityCSParserRuleCall_1_1_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_33);
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

                    // InternalCompleteOCL.g:2606:2: ( (lv_ownedCollectionMultiplicity_3_0= ruleMultiplicityCS ) )?
                    int alt49=2;
                    int LA49_0 = input.LA(1);

                    if ( (LA49_0==87) ) {
                        alt49=1;
                    }
                    switch (alt49) {
                        case 1 :
                            // InternalCompleteOCL.g:2607:1: (lv_ownedCollectionMultiplicity_3_0= ruleMultiplicityCS )
                            {
                            // InternalCompleteOCL.g:2607:1: (lv_ownedCollectionMultiplicity_3_0= ruleMultiplicityCS )
                            // InternalCompleteOCL.g:2608:3: lv_ownedCollectionMultiplicity_3_0= ruleMultiplicityCS
                            {
                            if ( state.backtracking==0 ) {

                              	        newCompositeNode(grammarAccess.getCollectionTypeCSAccess().getOwnedCollectionMultiplicityMultiplicityCSParserRuleCall_1_2_0());

                            }
                            pushFollow(FollowSets000.FOLLOW_11);
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

                    otherlv_4=(Token)match(input,22,FollowSets000.FOLLOW_2); if (state.failed) return current;
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
    // InternalCompleteOCL.g:2636:1: entryRuleMapTypeCS returns [EObject current=null] : iv_ruleMapTypeCS= ruleMapTypeCS EOF ;
    public final EObject entryRuleMapTypeCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMapTypeCS = null;


        try {
            // InternalCompleteOCL.g:2637:2: (iv_ruleMapTypeCS= ruleMapTypeCS EOF )
            // InternalCompleteOCL.g:2638:2: iv_ruleMapTypeCS= ruleMapTypeCS EOF
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
    // InternalCompleteOCL.g:2645:1: ruleMapTypeCS returns [EObject current=null] : ( ( (lv_name_0_0= 'Map' ) ) (otherlv_1= '(' ( (lv_ownedKeyType_2_0= ruleTypeExpCS ) ) otherlv_3= ',' ( (lv_ownedValueType_4_0= ruleTypeExpCS ) ) otherlv_5= ')' )? ) ;
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
            // InternalCompleteOCL.g:2648:28: ( ( ( (lv_name_0_0= 'Map' ) ) (otherlv_1= '(' ( (lv_ownedKeyType_2_0= ruleTypeExpCS ) ) otherlv_3= ',' ( (lv_ownedValueType_4_0= ruleTypeExpCS ) ) otherlv_5= ')' )? ) )
            // InternalCompleteOCL.g:2649:1: ( ( (lv_name_0_0= 'Map' ) ) (otherlv_1= '(' ( (lv_ownedKeyType_2_0= ruleTypeExpCS ) ) otherlv_3= ',' ( (lv_ownedValueType_4_0= ruleTypeExpCS ) ) otherlv_5= ')' )? )
            {
            // InternalCompleteOCL.g:2649:1: ( ( (lv_name_0_0= 'Map' ) ) (otherlv_1= '(' ( (lv_ownedKeyType_2_0= ruleTypeExpCS ) ) otherlv_3= ',' ( (lv_ownedValueType_4_0= ruleTypeExpCS ) ) otherlv_5= ')' )? )
            // InternalCompleteOCL.g:2649:2: ( (lv_name_0_0= 'Map' ) ) (otherlv_1= '(' ( (lv_ownedKeyType_2_0= ruleTypeExpCS ) ) otherlv_3= ',' ( (lv_ownedValueType_4_0= ruleTypeExpCS ) ) otherlv_5= ')' )?
            {
            // InternalCompleteOCL.g:2649:2: ( (lv_name_0_0= 'Map' ) )
            // InternalCompleteOCL.g:2650:1: (lv_name_0_0= 'Map' )
            {
            // InternalCompleteOCL.g:2650:1: (lv_name_0_0= 'Map' )
            // InternalCompleteOCL.g:2651:3: lv_name_0_0= 'Map'
            {
            lv_name_0_0=(Token)match(input,68,FollowSets000.FOLLOW_32); if (state.failed) return current;
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

            // InternalCompleteOCL.g:2664:2: (otherlv_1= '(' ( (lv_ownedKeyType_2_0= ruleTypeExpCS ) ) otherlv_3= ',' ( (lv_ownedValueType_4_0= ruleTypeExpCS ) ) otherlv_5= ')' )?
            int alt51=2;
            int LA51_0 = input.LA(1);

            if ( (LA51_0==21) ) {
                alt51=1;
            }
            switch (alt51) {
                case 1 :
                    // InternalCompleteOCL.g:2664:4: otherlv_1= '(' ( (lv_ownedKeyType_2_0= ruleTypeExpCS ) ) otherlv_3= ',' ( (lv_ownedValueType_4_0= ruleTypeExpCS ) ) otherlv_5= ')'
                    {
                    otherlv_1=(Token)match(input,21,FollowSets000.FOLLOW_20); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_1, grammarAccess.getMapTypeCSAccess().getLeftParenthesisKeyword_1_0());

                    }
                    // InternalCompleteOCL.g:2668:1: ( (lv_ownedKeyType_2_0= ruleTypeExpCS ) )
                    // InternalCompleteOCL.g:2669:1: (lv_ownedKeyType_2_0= ruleTypeExpCS )
                    {
                    // InternalCompleteOCL.g:2669:1: (lv_ownedKeyType_2_0= ruleTypeExpCS )
                    // InternalCompleteOCL.g:2670:3: lv_ownedKeyType_2_0= ruleTypeExpCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getMapTypeCSAccess().getOwnedKeyTypeTypeExpCSParserRuleCall_1_1_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_34);
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

                    otherlv_3=(Token)match(input,26,FollowSets000.FOLLOW_20); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_3, grammarAccess.getMapTypeCSAccess().getCommaKeyword_1_2());

                    }
                    // InternalCompleteOCL.g:2690:1: ( (lv_ownedValueType_4_0= ruleTypeExpCS ) )
                    // InternalCompleteOCL.g:2691:1: (lv_ownedValueType_4_0= ruleTypeExpCS )
                    {
                    // InternalCompleteOCL.g:2691:1: (lv_ownedValueType_4_0= ruleTypeExpCS )
                    // InternalCompleteOCL.g:2692:3: lv_ownedValueType_4_0= ruleTypeExpCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getMapTypeCSAccess().getOwnedValueTypeTypeExpCSParserRuleCall_1_3_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_11);
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

                    otherlv_5=(Token)match(input,22,FollowSets000.FOLLOW_2); if (state.failed) return current;
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
    // InternalCompleteOCL.g:2720:1: entryRuleTupleTypeCS returns [EObject current=null] : iv_ruleTupleTypeCS= ruleTupleTypeCS EOF ;
    public final EObject entryRuleTupleTypeCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTupleTypeCS = null;


        try {
            // InternalCompleteOCL.g:2721:2: (iv_ruleTupleTypeCS= ruleTupleTypeCS EOF )
            // InternalCompleteOCL.g:2722:2: iv_ruleTupleTypeCS= ruleTupleTypeCS EOF
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
    // InternalCompleteOCL.g:2729:1: ruleTupleTypeCS returns [EObject current=null] : ( ( (lv_name_0_0= 'Tuple' ) ) (otherlv_1= '(' ( ( (lv_ownedParts_2_0= ruleTuplePartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleTuplePartCS ) ) )* )? otherlv_5= ')' )? ) ;
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
            // InternalCompleteOCL.g:2732:28: ( ( ( (lv_name_0_0= 'Tuple' ) ) (otherlv_1= '(' ( ( (lv_ownedParts_2_0= ruleTuplePartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleTuplePartCS ) ) )* )? otherlv_5= ')' )? ) )
            // InternalCompleteOCL.g:2733:1: ( ( (lv_name_0_0= 'Tuple' ) ) (otherlv_1= '(' ( ( (lv_ownedParts_2_0= ruleTuplePartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleTuplePartCS ) ) )* )? otherlv_5= ')' )? )
            {
            // InternalCompleteOCL.g:2733:1: ( ( (lv_name_0_0= 'Tuple' ) ) (otherlv_1= '(' ( ( (lv_ownedParts_2_0= ruleTuplePartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleTuplePartCS ) ) )* )? otherlv_5= ')' )? )
            // InternalCompleteOCL.g:2733:2: ( (lv_name_0_0= 'Tuple' ) ) (otherlv_1= '(' ( ( (lv_ownedParts_2_0= ruleTuplePartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleTuplePartCS ) ) )* )? otherlv_5= ')' )?
            {
            // InternalCompleteOCL.g:2733:2: ( (lv_name_0_0= 'Tuple' ) )
            // InternalCompleteOCL.g:2734:1: (lv_name_0_0= 'Tuple' )
            {
            // InternalCompleteOCL.g:2734:1: (lv_name_0_0= 'Tuple' )
            // InternalCompleteOCL.g:2735:3: lv_name_0_0= 'Tuple'
            {
            lv_name_0_0=(Token)match(input,69,FollowSets000.FOLLOW_32); if (state.failed) return current;
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

            // InternalCompleteOCL.g:2748:2: (otherlv_1= '(' ( ( (lv_ownedParts_2_0= ruleTuplePartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleTuplePartCS ) ) )* )? otherlv_5= ')' )?
            int alt54=2;
            int LA54_0 = input.LA(1);

            if ( (LA54_0==21) ) {
                alt54=1;
            }
            switch (alt54) {
                case 1 :
                    // InternalCompleteOCL.g:2748:4: otherlv_1= '(' ( ( (lv_ownedParts_2_0= ruleTuplePartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleTuplePartCS ) ) )* )? otherlv_5= ')'
                    {
                    otherlv_1=(Token)match(input,21,FollowSets000.FOLLOW_16); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_1, grammarAccess.getTupleTypeCSAccess().getLeftParenthesisKeyword_1_0());

                    }
                    // InternalCompleteOCL.g:2752:1: ( ( (lv_ownedParts_2_0= ruleTuplePartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleTuplePartCS ) ) )* )?
                    int alt53=2;
                    int LA53_0 = input.LA(1);

                    if ( ((LA53_0>=RULE_SIMPLE_ID && LA53_0<=RULE_ESCAPED_ID)||(LA53_0>=28 && LA53_0<=30)) ) {
                        alt53=1;
                    }
                    switch (alt53) {
                        case 1 :
                            // InternalCompleteOCL.g:2752:2: ( (lv_ownedParts_2_0= ruleTuplePartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleTuplePartCS ) ) )*
                            {
                            // InternalCompleteOCL.g:2752:2: ( (lv_ownedParts_2_0= ruleTuplePartCS ) )
                            // InternalCompleteOCL.g:2753:1: (lv_ownedParts_2_0= ruleTuplePartCS )
                            {
                            // InternalCompleteOCL.g:2753:1: (lv_ownedParts_2_0= ruleTuplePartCS )
                            // InternalCompleteOCL.g:2754:3: lv_ownedParts_2_0= ruleTuplePartCS
                            {
                            if ( state.backtracking==0 ) {

                              	        newCompositeNode(grammarAccess.getTupleTypeCSAccess().getOwnedPartsTuplePartCSParserRuleCall_1_1_0_0());

                            }
                            pushFollow(FollowSets000.FOLLOW_17);
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

                            // InternalCompleteOCL.g:2770:2: (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleTuplePartCS ) ) )*
                            loop52:
                            do {
                                int alt52=2;
                                int LA52_0 = input.LA(1);

                                if ( (LA52_0==26) ) {
                                    alt52=1;
                                }


                                switch (alt52) {
                            	case 1 :
                            	    // InternalCompleteOCL.g:2770:4: otherlv_3= ',' ( (lv_ownedParts_4_0= ruleTuplePartCS ) )
                            	    {
                            	    otherlv_3=(Token)match(input,26,FollowSets000.FOLLOW_14); if (state.failed) return current;
                            	    if ( state.backtracking==0 ) {

                            	          	newLeafNode(otherlv_3, grammarAccess.getTupleTypeCSAccess().getCommaKeyword_1_1_1_0());

                            	    }
                            	    // InternalCompleteOCL.g:2774:1: ( (lv_ownedParts_4_0= ruleTuplePartCS ) )
                            	    // InternalCompleteOCL.g:2775:1: (lv_ownedParts_4_0= ruleTuplePartCS )
                            	    {
                            	    // InternalCompleteOCL.g:2775:1: (lv_ownedParts_4_0= ruleTuplePartCS )
                            	    // InternalCompleteOCL.g:2776:3: lv_ownedParts_4_0= ruleTuplePartCS
                            	    {
                            	    if ( state.backtracking==0 ) {

                            	      	        newCompositeNode(grammarAccess.getTupleTypeCSAccess().getOwnedPartsTuplePartCSParserRuleCall_1_1_1_1_0());

                            	    }
                            	    pushFollow(FollowSets000.FOLLOW_17);
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
                            	    break loop52;
                                }
                            } while (true);


                            }
                            break;

                    }

                    otherlv_5=(Token)match(input,22,FollowSets000.FOLLOW_2); if (state.failed) return current;
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
    // InternalCompleteOCL.g:2804:1: entryRuleTuplePartCS returns [EObject current=null] : iv_ruleTuplePartCS= ruleTuplePartCS EOF ;
    public final EObject entryRuleTuplePartCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTuplePartCS = null;


        try {
            // InternalCompleteOCL.g:2805:2: (iv_ruleTuplePartCS= ruleTuplePartCS EOF )
            // InternalCompleteOCL.g:2806:2: iv_ruleTuplePartCS= ruleTuplePartCS EOF
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
    // InternalCompleteOCL.g:2813:1: ruleTuplePartCS returns [EObject current=null] : ( ( (lv_name_0_0= ruleUnrestrictedName ) ) otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) ) ) ;
    public final EObject ruleTuplePartCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_name_0_0 = null;

        EObject lv_ownedType_2_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:2816:28: ( ( ( (lv_name_0_0= ruleUnrestrictedName ) ) otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) ) ) )
            // InternalCompleteOCL.g:2817:1: ( ( (lv_name_0_0= ruleUnrestrictedName ) ) otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) ) )
            {
            // InternalCompleteOCL.g:2817:1: ( ( (lv_name_0_0= ruleUnrestrictedName ) ) otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) ) )
            // InternalCompleteOCL.g:2817:2: ( (lv_name_0_0= ruleUnrestrictedName ) ) otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) )
            {
            // InternalCompleteOCL.g:2817:2: ( (lv_name_0_0= ruleUnrestrictedName ) )
            // InternalCompleteOCL.g:2818:1: (lv_name_0_0= ruleUnrestrictedName )
            {
            // InternalCompleteOCL.g:2818:1: (lv_name_0_0= ruleUnrestrictedName )
            // InternalCompleteOCL.g:2819:3: lv_name_0_0= ruleUnrestrictedName
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getTuplePartCSAccess().getNameUnrestrictedNameParserRuleCall_0_0());

            }
            pushFollow(FollowSets000.FOLLOW_12);
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

            otherlv_1=(Token)match(input,23,FollowSets000.FOLLOW_20); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getTuplePartCSAccess().getColonKeyword_1());

            }
            // InternalCompleteOCL.g:2839:1: ( (lv_ownedType_2_0= ruleTypeExpCS ) )
            // InternalCompleteOCL.g:2840:1: (lv_ownedType_2_0= ruleTypeExpCS )
            {
            // InternalCompleteOCL.g:2840:1: (lv_ownedType_2_0= ruleTypeExpCS )
            // InternalCompleteOCL.g:2841:3: lv_ownedType_2_0= ruleTypeExpCS
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
    // InternalCompleteOCL.g:2865:1: entryRuleCollectionLiteralExpCS returns [EObject current=null] : iv_ruleCollectionLiteralExpCS= ruleCollectionLiteralExpCS EOF ;
    public final EObject entryRuleCollectionLiteralExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCollectionLiteralExpCS = null;


        try {
            // InternalCompleteOCL.g:2866:2: (iv_ruleCollectionLiteralExpCS= ruleCollectionLiteralExpCS EOF )
            // InternalCompleteOCL.g:2867:2: iv_ruleCollectionLiteralExpCS= ruleCollectionLiteralExpCS EOF
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
    // InternalCompleteOCL.g:2874:1: ruleCollectionLiteralExpCS returns [EObject current=null] : ( ( (lv_ownedType_0_0= ruleCollectionTypeCS ) ) otherlv_1= '{' ( ( (lv_ownedParts_2_0= ruleCollectionLiteralPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleCollectionLiteralPartCS ) ) )* )? otherlv_5= '}' ) ;
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
            // InternalCompleteOCL.g:2877:28: ( ( ( (lv_ownedType_0_0= ruleCollectionTypeCS ) ) otherlv_1= '{' ( ( (lv_ownedParts_2_0= ruleCollectionLiteralPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleCollectionLiteralPartCS ) ) )* )? otherlv_5= '}' ) )
            // InternalCompleteOCL.g:2878:1: ( ( (lv_ownedType_0_0= ruleCollectionTypeCS ) ) otherlv_1= '{' ( ( (lv_ownedParts_2_0= ruleCollectionLiteralPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleCollectionLiteralPartCS ) ) )* )? otherlv_5= '}' )
            {
            // InternalCompleteOCL.g:2878:1: ( ( (lv_ownedType_0_0= ruleCollectionTypeCS ) ) otherlv_1= '{' ( ( (lv_ownedParts_2_0= ruleCollectionLiteralPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleCollectionLiteralPartCS ) ) )* )? otherlv_5= '}' )
            // InternalCompleteOCL.g:2878:2: ( (lv_ownedType_0_0= ruleCollectionTypeCS ) ) otherlv_1= '{' ( ( (lv_ownedParts_2_0= ruleCollectionLiteralPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleCollectionLiteralPartCS ) ) )* )? otherlv_5= '}'
            {
            // InternalCompleteOCL.g:2878:2: ( (lv_ownedType_0_0= ruleCollectionTypeCS ) )
            // InternalCompleteOCL.g:2879:1: (lv_ownedType_0_0= ruleCollectionTypeCS )
            {
            // InternalCompleteOCL.g:2879:1: (lv_ownedType_0_0= ruleCollectionTypeCS )
            // InternalCompleteOCL.g:2880:3: lv_ownedType_0_0= ruleCollectionTypeCS
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getCollectionLiteralExpCSAccess().getOwnedTypeCollectionTypeCSParserRuleCall_0_0());

            }
            pushFollow(FollowSets000.FOLLOW_35);
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

            otherlv_1=(Token)match(input,76,FollowSets000.FOLLOW_36); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getCollectionLiteralExpCSAccess().getLeftCurlyBracketKeyword_1());

            }
            // InternalCompleteOCL.g:2900:1: ( ( (lv_ownedParts_2_0= ruleCollectionLiteralPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleCollectionLiteralPartCS ) ) )* )?
            int alt56=2;
            int LA56_0 = input.LA(1);

            if ( ((LA56_0>=RULE_SIMPLE_ID && LA56_0<=RULE_SINGLE_QUOTED_STRING)||LA56_0==21||LA56_0==23||(LA56_0>=28 && LA56_0<=30)||(LA56_0>=42 && LA56_0<=54)||(LA56_0>=68 && LA56_0<=69)||(LA56_0>=71 && LA56_0<=75)||LA56_0==80||(LA56_0>=82 && LA56_0<=85)||LA56_0==92||(LA56_0>=97 && LA56_0<=98)) ) {
                alt56=1;
            }
            switch (alt56) {
                case 1 :
                    // InternalCompleteOCL.g:2900:2: ( (lv_ownedParts_2_0= ruleCollectionLiteralPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleCollectionLiteralPartCS ) ) )*
                    {
                    // InternalCompleteOCL.g:2900:2: ( (lv_ownedParts_2_0= ruleCollectionLiteralPartCS ) )
                    // InternalCompleteOCL.g:2901:1: (lv_ownedParts_2_0= ruleCollectionLiteralPartCS )
                    {
                    // InternalCompleteOCL.g:2901:1: (lv_ownedParts_2_0= ruleCollectionLiteralPartCS )
                    // InternalCompleteOCL.g:2902:3: lv_ownedParts_2_0= ruleCollectionLiteralPartCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getCollectionLiteralExpCSAccess().getOwnedPartsCollectionLiteralPartCSParserRuleCall_2_0_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_37);
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

                    // InternalCompleteOCL.g:2918:2: (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleCollectionLiteralPartCS ) ) )*
                    loop55:
                    do {
                        int alt55=2;
                        int LA55_0 = input.LA(1);

                        if ( (LA55_0==26) ) {
                            alt55=1;
                        }


                        switch (alt55) {
                    	case 1 :
                    	    // InternalCompleteOCL.g:2918:4: otherlv_3= ',' ( (lv_ownedParts_4_0= ruleCollectionLiteralPartCS ) )
                    	    {
                    	    otherlv_3=(Token)match(input,26,FollowSets000.FOLLOW_38); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_3, grammarAccess.getCollectionLiteralExpCSAccess().getCommaKeyword_2_1_0());

                    	    }
                    	    // InternalCompleteOCL.g:2922:1: ( (lv_ownedParts_4_0= ruleCollectionLiteralPartCS ) )
                    	    // InternalCompleteOCL.g:2923:1: (lv_ownedParts_4_0= ruleCollectionLiteralPartCS )
                    	    {
                    	    // InternalCompleteOCL.g:2923:1: (lv_ownedParts_4_0= ruleCollectionLiteralPartCS )
                    	    // InternalCompleteOCL.g:2924:3: lv_ownedParts_4_0= ruleCollectionLiteralPartCS
                    	    {
                    	    if ( state.backtracking==0 ) {

                    	      	        newCompositeNode(grammarAccess.getCollectionLiteralExpCSAccess().getOwnedPartsCollectionLiteralPartCSParserRuleCall_2_1_1_0());

                    	    }
                    	    pushFollow(FollowSets000.FOLLOW_37);
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
                    	    break loop55;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_5=(Token)match(input,77,FollowSets000.FOLLOW_2); if (state.failed) return current;
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
    // InternalCompleteOCL.g:2952:1: entryRuleCollectionLiteralPartCS returns [EObject current=null] : iv_ruleCollectionLiteralPartCS= ruleCollectionLiteralPartCS EOF ;
    public final EObject entryRuleCollectionLiteralPartCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCollectionLiteralPartCS = null;


        try {
            // InternalCompleteOCL.g:2953:2: (iv_ruleCollectionLiteralPartCS= ruleCollectionLiteralPartCS EOF )
            // InternalCompleteOCL.g:2954:2: iv_ruleCollectionLiteralPartCS= ruleCollectionLiteralPartCS EOF
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
    // InternalCompleteOCL.g:2961:1: ruleCollectionLiteralPartCS returns [EObject current=null] : ( ( ( (lv_ownedExpression_0_0= ruleExpCS ) ) (otherlv_1= '..' ( (lv_ownedLastExpression_2_0= ruleExpCS ) ) )? ) | ( (lv_ownedExpression_3_0= rulePatternExpCS ) ) ) ;
    public final EObject ruleCollectionLiteralPartCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject lv_ownedExpression_0_0 = null;

        EObject lv_ownedLastExpression_2_0 = null;

        EObject lv_ownedExpression_3_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:2964:28: ( ( ( ( (lv_ownedExpression_0_0= ruleExpCS ) ) (otherlv_1= '..' ( (lv_ownedLastExpression_2_0= ruleExpCS ) ) )? ) | ( (lv_ownedExpression_3_0= rulePatternExpCS ) ) ) )
            // InternalCompleteOCL.g:2965:1: ( ( ( (lv_ownedExpression_0_0= ruleExpCS ) ) (otherlv_1= '..' ( (lv_ownedLastExpression_2_0= ruleExpCS ) ) )? ) | ( (lv_ownedExpression_3_0= rulePatternExpCS ) ) )
            {
            // InternalCompleteOCL.g:2965:1: ( ( ( (lv_ownedExpression_0_0= ruleExpCS ) ) (otherlv_1= '..' ( (lv_ownedLastExpression_2_0= ruleExpCS ) ) )? ) | ( (lv_ownedExpression_3_0= rulePatternExpCS ) ) )
            int alt58=2;
            switch ( input.LA(1) ) {
            case RULE_INT:
            case RULE_SINGLE_QUOTED_STRING:
            case 21:
            case 42:
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
            case 68:
            case 69:
            case 71:
            case 72:
            case 73:
            case 74:
            case 75:
            case 80:
            case 82:
            case 83:
            case 84:
            case 85:
            case 92:
            case 97:
            case 98:
                {
                alt58=1;
                }
                break;
            case RULE_SIMPLE_ID:
                {
                int LA58_2 = input.LA(2);

                if ( (LA58_2==23) ) {
                    alt58=2;
                }
                else if ( (LA58_2==EOF||(LA58_2>=17 && LA58_2<=18)||LA58_2==21||(LA58_2>=26 && LA58_2<=27)||(LA58_2>=39 && LA58_2<=40)||LA58_2==52||(LA58_2>=54 && LA58_2<=67)||LA58_2==70||(LA58_2>=76 && LA58_2<=78)||(LA58_2>=86 && LA58_2<=87)) ) {
                    alt58=1;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return current;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 58, 2, input);

                    throw nvae;
                }
                }
                break;
            case RULE_ESCAPED_ID:
                {
                int LA58_3 = input.LA(2);

                if ( (LA58_3==23) ) {
                    alt58=2;
                }
                else if ( (LA58_3==EOF||(LA58_3>=17 && LA58_3<=18)||LA58_3==21||(LA58_3>=26 && LA58_3<=27)||(LA58_3>=39 && LA58_3<=40)||LA58_3==52||(LA58_3>=54 && LA58_3<=67)||LA58_3==70||(LA58_3>=76 && LA58_3<=78)||(LA58_3>=86 && LA58_3<=87)) ) {
                    alt58=1;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return current;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 58, 3, input);

                    throw nvae;
                }
                }
                break;
            case 28:
                {
                int LA58_4 = input.LA(2);

                if ( (LA58_4==EOF||(LA58_4>=17 && LA58_4<=18)||LA58_4==21||(LA58_4>=26 && LA58_4<=27)||(LA58_4>=39 && LA58_4<=40)||LA58_4==52||(LA58_4>=54 && LA58_4<=67)||LA58_4==70||(LA58_4>=76 && LA58_4<=78)||(LA58_4>=86 && LA58_4<=87)) ) {
                    alt58=1;
                }
                else if ( (LA58_4==23) ) {
                    alt58=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return current;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 58, 4, input);

                    throw nvae;
                }
                }
                break;
            case 29:
                {
                int LA58_5 = input.LA(2);

                if ( (LA58_5==23) ) {
                    alt58=2;
                }
                else if ( (LA58_5==EOF||(LA58_5>=17 && LA58_5<=18)||LA58_5==21||(LA58_5>=26 && LA58_5<=27)||(LA58_5>=39 && LA58_5<=40)||LA58_5==52||(LA58_5>=54 && LA58_5<=67)||LA58_5==70||(LA58_5>=76 && LA58_5<=78)||(LA58_5>=86 && LA58_5<=87)) ) {
                    alt58=1;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return current;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 58, 5, input);

                    throw nvae;
                }
                }
                break;
            case 30:
                {
                int LA58_6 = input.LA(2);

                if ( (LA58_6==23) ) {
                    alt58=2;
                }
                else if ( (LA58_6==EOF||(LA58_6>=17 && LA58_6<=18)||LA58_6==21||(LA58_6>=26 && LA58_6<=27)||(LA58_6>=39 && LA58_6<=40)||LA58_6==52||(LA58_6>=54 && LA58_6<=67)||LA58_6==70||(LA58_6>=76 && LA58_6<=78)||(LA58_6>=86 && LA58_6<=87)) ) {
                    alt58=1;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return current;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 58, 6, input);

                    throw nvae;
                }
                }
                break;
            case 23:
                {
                alt58=2;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 58, 0, input);

                throw nvae;
            }

            switch (alt58) {
                case 1 :
                    // InternalCompleteOCL.g:2965:2: ( ( (lv_ownedExpression_0_0= ruleExpCS ) ) (otherlv_1= '..' ( (lv_ownedLastExpression_2_0= ruleExpCS ) ) )? )
                    {
                    // InternalCompleteOCL.g:2965:2: ( ( (lv_ownedExpression_0_0= ruleExpCS ) ) (otherlv_1= '..' ( (lv_ownedLastExpression_2_0= ruleExpCS ) ) )? )
                    // InternalCompleteOCL.g:2965:3: ( (lv_ownedExpression_0_0= ruleExpCS ) ) (otherlv_1= '..' ( (lv_ownedLastExpression_2_0= ruleExpCS ) ) )?
                    {
                    // InternalCompleteOCL.g:2965:3: ( (lv_ownedExpression_0_0= ruleExpCS ) )
                    // InternalCompleteOCL.g:2966:1: (lv_ownedExpression_0_0= ruleExpCS )
                    {
                    // InternalCompleteOCL.g:2966:1: (lv_ownedExpression_0_0= ruleExpCS )
                    // InternalCompleteOCL.g:2967:3: lv_ownedExpression_0_0= ruleExpCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getCollectionLiteralPartCSAccess().getOwnedExpressionExpCSParserRuleCall_0_0_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_39);
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

                    // InternalCompleteOCL.g:2983:2: (otherlv_1= '..' ( (lv_ownedLastExpression_2_0= ruleExpCS ) ) )?
                    int alt57=2;
                    int LA57_0 = input.LA(1);

                    if ( (LA57_0==78) ) {
                        alt57=1;
                    }
                    switch (alt57) {
                        case 1 :
                            // InternalCompleteOCL.g:2983:4: otherlv_1= '..' ( (lv_ownedLastExpression_2_0= ruleExpCS ) )
                            {
                            otherlv_1=(Token)match(input,78,FollowSets000.FOLLOW_40); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                  	newLeafNode(otherlv_1, grammarAccess.getCollectionLiteralPartCSAccess().getFullStopFullStopKeyword_0_1_0());

                            }
                            // InternalCompleteOCL.g:2987:1: ( (lv_ownedLastExpression_2_0= ruleExpCS ) )
                            // InternalCompleteOCL.g:2988:1: (lv_ownedLastExpression_2_0= ruleExpCS )
                            {
                            // InternalCompleteOCL.g:2988:1: (lv_ownedLastExpression_2_0= ruleExpCS )
                            // InternalCompleteOCL.g:2989:3: lv_ownedLastExpression_2_0= ruleExpCS
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
                    // InternalCompleteOCL.g:3006:6: ( (lv_ownedExpression_3_0= rulePatternExpCS ) )
                    {
                    // InternalCompleteOCL.g:3006:6: ( (lv_ownedExpression_3_0= rulePatternExpCS ) )
                    // InternalCompleteOCL.g:3007:1: (lv_ownedExpression_3_0= rulePatternExpCS )
                    {
                    // InternalCompleteOCL.g:3007:1: (lv_ownedExpression_3_0= rulePatternExpCS )
                    // InternalCompleteOCL.g:3008:3: lv_ownedExpression_3_0= rulePatternExpCS
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
    // InternalCompleteOCL.g:3032:1: entryRuleCollectionPatternCS returns [EObject current=null] : iv_ruleCollectionPatternCS= ruleCollectionPatternCS EOF ;
    public final EObject entryRuleCollectionPatternCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCollectionPatternCS = null;


        try {
            // InternalCompleteOCL.g:3033:2: (iv_ruleCollectionPatternCS= ruleCollectionPatternCS EOF )
            // InternalCompleteOCL.g:3034:2: iv_ruleCollectionPatternCS= ruleCollectionPatternCS EOF
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
    // InternalCompleteOCL.g:3041:1: ruleCollectionPatternCS returns [EObject current=null] : ( ( (lv_ownedType_0_0= ruleCollectionTypeCS ) ) otherlv_1= '{' ( ( (lv_ownedParts_2_0= rulePatternExpCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= rulePatternExpCS ) ) )* (otherlv_5= '++' ( (lv_restVariableName_6_0= ruleIdentifier ) ) ) )? otherlv_7= '}' ) ;
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
            // InternalCompleteOCL.g:3044:28: ( ( ( (lv_ownedType_0_0= ruleCollectionTypeCS ) ) otherlv_1= '{' ( ( (lv_ownedParts_2_0= rulePatternExpCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= rulePatternExpCS ) ) )* (otherlv_5= '++' ( (lv_restVariableName_6_0= ruleIdentifier ) ) ) )? otherlv_7= '}' ) )
            // InternalCompleteOCL.g:3045:1: ( ( (lv_ownedType_0_0= ruleCollectionTypeCS ) ) otherlv_1= '{' ( ( (lv_ownedParts_2_0= rulePatternExpCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= rulePatternExpCS ) ) )* (otherlv_5= '++' ( (lv_restVariableName_6_0= ruleIdentifier ) ) ) )? otherlv_7= '}' )
            {
            // InternalCompleteOCL.g:3045:1: ( ( (lv_ownedType_0_0= ruleCollectionTypeCS ) ) otherlv_1= '{' ( ( (lv_ownedParts_2_0= rulePatternExpCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= rulePatternExpCS ) ) )* (otherlv_5= '++' ( (lv_restVariableName_6_0= ruleIdentifier ) ) ) )? otherlv_7= '}' )
            // InternalCompleteOCL.g:3045:2: ( (lv_ownedType_0_0= ruleCollectionTypeCS ) ) otherlv_1= '{' ( ( (lv_ownedParts_2_0= rulePatternExpCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= rulePatternExpCS ) ) )* (otherlv_5= '++' ( (lv_restVariableName_6_0= ruleIdentifier ) ) ) )? otherlv_7= '}'
            {
            // InternalCompleteOCL.g:3045:2: ( (lv_ownedType_0_0= ruleCollectionTypeCS ) )
            // InternalCompleteOCL.g:3046:1: (lv_ownedType_0_0= ruleCollectionTypeCS )
            {
            // InternalCompleteOCL.g:3046:1: (lv_ownedType_0_0= ruleCollectionTypeCS )
            // InternalCompleteOCL.g:3047:3: lv_ownedType_0_0= ruleCollectionTypeCS
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getCollectionPatternCSAccess().getOwnedTypeCollectionTypeCSParserRuleCall_0_0());

            }
            pushFollow(FollowSets000.FOLLOW_35);
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

            otherlv_1=(Token)match(input,76,FollowSets000.FOLLOW_36); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getCollectionPatternCSAccess().getLeftCurlyBracketKeyword_1());

            }
            // InternalCompleteOCL.g:3067:1: ( ( (lv_ownedParts_2_0= rulePatternExpCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= rulePatternExpCS ) ) )* (otherlv_5= '++' ( (lv_restVariableName_6_0= ruleIdentifier ) ) ) )?
            int alt60=2;
            int LA60_0 = input.LA(1);

            if ( ((LA60_0>=RULE_SIMPLE_ID && LA60_0<=RULE_ESCAPED_ID)||LA60_0==23||(LA60_0>=28 && LA60_0<=30)) ) {
                alt60=1;
            }
            switch (alt60) {
                case 1 :
                    // InternalCompleteOCL.g:3067:2: ( (lv_ownedParts_2_0= rulePatternExpCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= rulePatternExpCS ) ) )* (otherlv_5= '++' ( (lv_restVariableName_6_0= ruleIdentifier ) ) )
                    {
                    // InternalCompleteOCL.g:3067:2: ( (lv_ownedParts_2_0= rulePatternExpCS ) )
                    // InternalCompleteOCL.g:3068:1: (lv_ownedParts_2_0= rulePatternExpCS )
                    {
                    // InternalCompleteOCL.g:3068:1: (lv_ownedParts_2_0= rulePatternExpCS )
                    // InternalCompleteOCL.g:3069:3: lv_ownedParts_2_0= rulePatternExpCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getCollectionPatternCSAccess().getOwnedPartsPatternExpCSParserRuleCall_2_0_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_41);
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

                    // InternalCompleteOCL.g:3085:2: (otherlv_3= ',' ( (lv_ownedParts_4_0= rulePatternExpCS ) ) )*
                    loop59:
                    do {
                        int alt59=2;
                        int LA59_0 = input.LA(1);

                        if ( (LA59_0==26) ) {
                            alt59=1;
                        }


                        switch (alt59) {
                    	case 1 :
                    	    // InternalCompleteOCL.g:3085:4: otherlv_3= ',' ( (lv_ownedParts_4_0= rulePatternExpCS ) )
                    	    {
                    	    otherlv_3=(Token)match(input,26,FollowSets000.FOLLOW_38); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_3, grammarAccess.getCollectionPatternCSAccess().getCommaKeyword_2_1_0());

                    	    }
                    	    // InternalCompleteOCL.g:3089:1: ( (lv_ownedParts_4_0= rulePatternExpCS ) )
                    	    // InternalCompleteOCL.g:3090:1: (lv_ownedParts_4_0= rulePatternExpCS )
                    	    {
                    	    // InternalCompleteOCL.g:3090:1: (lv_ownedParts_4_0= rulePatternExpCS )
                    	    // InternalCompleteOCL.g:3091:3: lv_ownedParts_4_0= rulePatternExpCS
                    	    {
                    	    if ( state.backtracking==0 ) {

                    	      	        newCompositeNode(grammarAccess.getCollectionPatternCSAccess().getOwnedPartsPatternExpCSParserRuleCall_2_1_1_0());

                    	    }
                    	    pushFollow(FollowSets000.FOLLOW_41);
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
                    	    break loop59;
                        }
                    } while (true);

                    // InternalCompleteOCL.g:3107:4: (otherlv_5= '++' ( (lv_restVariableName_6_0= ruleIdentifier ) ) )
                    // InternalCompleteOCL.g:3107:6: otherlv_5= '++' ( (lv_restVariableName_6_0= ruleIdentifier ) )
                    {
                    otherlv_5=(Token)match(input,79,FollowSets000.FOLLOW_42); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_5, grammarAccess.getCollectionPatternCSAccess().getPlusSignPlusSignKeyword_2_2_0());

                    }
                    // InternalCompleteOCL.g:3111:1: ( (lv_restVariableName_6_0= ruleIdentifier ) )
                    // InternalCompleteOCL.g:3112:1: (lv_restVariableName_6_0= ruleIdentifier )
                    {
                    // InternalCompleteOCL.g:3112:1: (lv_restVariableName_6_0= ruleIdentifier )
                    // InternalCompleteOCL.g:3113:3: lv_restVariableName_6_0= ruleIdentifier
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getCollectionPatternCSAccess().getRestVariableNameIdentifierParserRuleCall_2_2_1_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_43);
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

            otherlv_7=(Token)match(input,77,FollowSets000.FOLLOW_2); if (state.failed) return current;
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
    // InternalCompleteOCL.g:3141:1: entryRuleShadowPartCS returns [EObject current=null] : iv_ruleShadowPartCS= ruleShadowPartCS EOF ;
    public final EObject entryRuleShadowPartCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleShadowPartCS = null;


        try {
            // InternalCompleteOCL.g:3142:2: (iv_ruleShadowPartCS= ruleShadowPartCS EOF )
            // InternalCompleteOCL.g:3143:2: iv_ruleShadowPartCS= ruleShadowPartCS EOF
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
    // InternalCompleteOCL.g:3150:1: ruleShadowPartCS returns [EObject current=null] : ( ( ( ( ruleUnrestrictedName ) ) otherlv_1= '=' ( ( (lv_ownedInitExpression_2_1= ruleExpCS | lv_ownedInitExpression_2_2= rulePatternExpCS ) ) ) ) | ( (lv_ownedInitExpression_3_0= ruleStringLiteralExpCS ) ) ) ;
    public final EObject ruleShadowPartCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject lv_ownedInitExpression_2_1 = null;

        EObject lv_ownedInitExpression_2_2 = null;

        EObject lv_ownedInitExpression_3_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:3153:28: ( ( ( ( ( ruleUnrestrictedName ) ) otherlv_1= '=' ( ( (lv_ownedInitExpression_2_1= ruleExpCS | lv_ownedInitExpression_2_2= rulePatternExpCS ) ) ) ) | ( (lv_ownedInitExpression_3_0= ruleStringLiteralExpCS ) ) ) )
            // InternalCompleteOCL.g:3154:1: ( ( ( ( ruleUnrestrictedName ) ) otherlv_1= '=' ( ( (lv_ownedInitExpression_2_1= ruleExpCS | lv_ownedInitExpression_2_2= rulePatternExpCS ) ) ) ) | ( (lv_ownedInitExpression_3_0= ruleStringLiteralExpCS ) ) )
            {
            // InternalCompleteOCL.g:3154:1: ( ( ( ( ruleUnrestrictedName ) ) otherlv_1= '=' ( ( (lv_ownedInitExpression_2_1= ruleExpCS | lv_ownedInitExpression_2_2= rulePatternExpCS ) ) ) ) | ( (lv_ownedInitExpression_3_0= ruleStringLiteralExpCS ) ) )
            int alt62=2;
            int LA62_0 = input.LA(1);

            if ( ((LA62_0>=RULE_SIMPLE_ID && LA62_0<=RULE_ESCAPED_ID)||(LA62_0>=28 && LA62_0<=30)) ) {
                alt62=1;
            }
            else if ( (LA62_0==RULE_SINGLE_QUOTED_STRING) ) {
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
                    // InternalCompleteOCL.g:3154:2: ( ( ( ruleUnrestrictedName ) ) otherlv_1= '=' ( ( (lv_ownedInitExpression_2_1= ruleExpCS | lv_ownedInitExpression_2_2= rulePatternExpCS ) ) ) )
                    {
                    // InternalCompleteOCL.g:3154:2: ( ( ( ruleUnrestrictedName ) ) otherlv_1= '=' ( ( (lv_ownedInitExpression_2_1= ruleExpCS | lv_ownedInitExpression_2_2= rulePatternExpCS ) ) ) )
                    // InternalCompleteOCL.g:3154:3: ( ( ruleUnrestrictedName ) ) otherlv_1= '=' ( ( (lv_ownedInitExpression_2_1= ruleExpCS | lv_ownedInitExpression_2_2= rulePatternExpCS ) ) )
                    {
                    // InternalCompleteOCL.g:3154:3: ( ( ruleUnrestrictedName ) )
                    // InternalCompleteOCL.g:3155:1: ( ruleUnrestrictedName )
                    {
                    // InternalCompleteOCL.g:3155:1: ( ruleUnrestrictedName )
                    // InternalCompleteOCL.g:3156:3: ruleUnrestrictedName
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
                    pushFollow(FollowSets000.FOLLOW_19);
                    ruleUnrestrictedName();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        afterParserOrEnumRuleCall();

                    }

                    }


                    }

                    otherlv_1=(Token)match(input,27,FollowSets000.FOLLOW_38); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_1, grammarAccess.getShadowPartCSAccess().getEqualsSignKeyword_0_1());

                    }
                    // InternalCompleteOCL.g:3176:1: ( ( (lv_ownedInitExpression_2_1= ruleExpCS | lv_ownedInitExpression_2_2= rulePatternExpCS ) ) )
                    // InternalCompleteOCL.g:3177:1: ( (lv_ownedInitExpression_2_1= ruleExpCS | lv_ownedInitExpression_2_2= rulePatternExpCS ) )
                    {
                    // InternalCompleteOCL.g:3177:1: ( (lv_ownedInitExpression_2_1= ruleExpCS | lv_ownedInitExpression_2_2= rulePatternExpCS ) )
                    // InternalCompleteOCL.g:3178:1: (lv_ownedInitExpression_2_1= ruleExpCS | lv_ownedInitExpression_2_2= rulePatternExpCS )
                    {
                    // InternalCompleteOCL.g:3178:1: (lv_ownedInitExpression_2_1= ruleExpCS | lv_ownedInitExpression_2_2= rulePatternExpCS )
                    int alt61=2;
                    switch ( input.LA(1) ) {
                    case RULE_INT:
                    case RULE_SINGLE_QUOTED_STRING:
                    case 21:
                    case 42:
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
                    case 68:
                    case 69:
                    case 71:
                    case 72:
                    case 73:
                    case 74:
                    case 75:
                    case 80:
                    case 82:
                    case 83:
                    case 84:
                    case 85:
                    case 92:
                    case 97:
                    case 98:
                        {
                        alt61=1;
                        }
                        break;
                    case RULE_SIMPLE_ID:
                        {
                        int LA61_2 = input.LA(2);

                        if ( (LA61_2==23) ) {
                            alt61=2;
                        }
                        else if ( (LA61_2==EOF||(LA61_2>=17 && LA61_2<=18)||LA61_2==21||(LA61_2>=26 && LA61_2<=27)||(LA61_2>=39 && LA61_2<=40)||LA61_2==52||(LA61_2>=54 && LA61_2<=67)||LA61_2==70||(LA61_2>=76 && LA61_2<=77)||(LA61_2>=86 && LA61_2<=87)) ) {
                            alt61=1;
                        }
                        else {
                            if (state.backtracking>0) {state.failed=true; return current;}
                            NoViableAltException nvae =
                                new NoViableAltException("", 61, 2, input);

                            throw nvae;
                        }
                        }
                        break;
                    case RULE_ESCAPED_ID:
                        {
                        int LA61_3 = input.LA(2);

                        if ( (LA61_3==23) ) {
                            alt61=2;
                        }
                        else if ( (LA61_3==EOF||(LA61_3>=17 && LA61_3<=18)||LA61_3==21||(LA61_3>=26 && LA61_3<=27)||(LA61_3>=39 && LA61_3<=40)||LA61_3==52||(LA61_3>=54 && LA61_3<=67)||LA61_3==70||(LA61_3>=76 && LA61_3<=77)||(LA61_3>=86 && LA61_3<=87)) ) {
                            alt61=1;
                        }
                        else {
                            if (state.backtracking>0) {state.failed=true; return current;}
                            NoViableAltException nvae =
                                new NoViableAltException("", 61, 3, input);

                            throw nvae;
                        }
                        }
                        break;
                    case 28:
                        {
                        int LA61_4 = input.LA(2);

                        if ( (LA61_4==EOF||(LA61_4>=17 && LA61_4<=18)||LA61_4==21||(LA61_4>=26 && LA61_4<=27)||(LA61_4>=39 && LA61_4<=40)||LA61_4==52||(LA61_4>=54 && LA61_4<=67)||LA61_4==70||(LA61_4>=76 && LA61_4<=77)||(LA61_4>=86 && LA61_4<=87)) ) {
                            alt61=1;
                        }
                        else if ( (LA61_4==23) ) {
                            alt61=2;
                        }
                        else {
                            if (state.backtracking>0) {state.failed=true; return current;}
                            NoViableAltException nvae =
                                new NoViableAltException("", 61, 4, input);

                            throw nvae;
                        }
                        }
                        break;
                    case 29:
                        {
                        int LA61_5 = input.LA(2);

                        if ( (LA61_5==23) ) {
                            alt61=2;
                        }
                        else if ( (LA61_5==EOF||(LA61_5>=17 && LA61_5<=18)||LA61_5==21||(LA61_5>=26 && LA61_5<=27)||(LA61_5>=39 && LA61_5<=40)||LA61_5==52||(LA61_5>=54 && LA61_5<=67)||LA61_5==70||(LA61_5>=76 && LA61_5<=77)||(LA61_5>=86 && LA61_5<=87)) ) {
                            alt61=1;
                        }
                        else {
                            if (state.backtracking>0) {state.failed=true; return current;}
                            NoViableAltException nvae =
                                new NoViableAltException("", 61, 5, input);

                            throw nvae;
                        }
                        }
                        break;
                    case 30:
                        {
                        int LA61_6 = input.LA(2);

                        if ( (LA61_6==23) ) {
                            alt61=2;
                        }
                        else if ( (LA61_6==EOF||(LA61_6>=17 && LA61_6<=18)||LA61_6==21||(LA61_6>=26 && LA61_6<=27)||(LA61_6>=39 && LA61_6<=40)||LA61_6==52||(LA61_6>=54 && LA61_6<=67)||LA61_6==70||(LA61_6>=76 && LA61_6<=77)||(LA61_6>=86 && LA61_6<=87)) ) {
                            alt61=1;
                        }
                        else {
                            if (state.backtracking>0) {state.failed=true; return current;}
                            NoViableAltException nvae =
                                new NoViableAltException("", 61, 6, input);

                            throw nvae;
                        }
                        }
                        break;
                    case 23:
                        {
                        alt61=2;
                        }
                        break;
                    default:
                        if (state.backtracking>0) {state.failed=true; return current;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 61, 0, input);

                        throw nvae;
                    }

                    switch (alt61) {
                        case 1 :
                            // InternalCompleteOCL.g:3179:3: lv_ownedInitExpression_2_1= ruleExpCS
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
                            // InternalCompleteOCL.g:3194:8: lv_ownedInitExpression_2_2= rulePatternExpCS
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
                    // InternalCompleteOCL.g:3213:6: ( (lv_ownedInitExpression_3_0= ruleStringLiteralExpCS ) )
                    {
                    // InternalCompleteOCL.g:3213:6: ( (lv_ownedInitExpression_3_0= ruleStringLiteralExpCS ) )
                    // InternalCompleteOCL.g:3214:1: (lv_ownedInitExpression_3_0= ruleStringLiteralExpCS )
                    {
                    // InternalCompleteOCL.g:3214:1: (lv_ownedInitExpression_3_0= ruleStringLiteralExpCS )
                    // InternalCompleteOCL.g:3215:3: lv_ownedInitExpression_3_0= ruleStringLiteralExpCS
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
    // InternalCompleteOCL.g:3239:1: entryRulePatternExpCS returns [EObject current=null] : iv_rulePatternExpCS= rulePatternExpCS EOF ;
    public final EObject entryRulePatternExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePatternExpCS = null;


        try {
            // InternalCompleteOCL.g:3240:2: (iv_rulePatternExpCS= rulePatternExpCS EOF )
            // InternalCompleteOCL.g:3241:2: iv_rulePatternExpCS= rulePatternExpCS EOF
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
    // InternalCompleteOCL.g:3248:1: rulePatternExpCS returns [EObject current=null] : ( ( (lv_patternVariableName_0_0= ruleUnrestrictedName ) )? otherlv_1= ':' ( (lv_ownedPatternType_2_0= ruleTypeExpCS ) ) ) ;
    public final EObject rulePatternExpCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_patternVariableName_0_0 = null;

        EObject lv_ownedPatternType_2_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:3251:28: ( ( ( (lv_patternVariableName_0_0= ruleUnrestrictedName ) )? otherlv_1= ':' ( (lv_ownedPatternType_2_0= ruleTypeExpCS ) ) ) )
            // InternalCompleteOCL.g:3252:1: ( ( (lv_patternVariableName_0_0= ruleUnrestrictedName ) )? otherlv_1= ':' ( (lv_ownedPatternType_2_0= ruleTypeExpCS ) ) )
            {
            // InternalCompleteOCL.g:3252:1: ( ( (lv_patternVariableName_0_0= ruleUnrestrictedName ) )? otherlv_1= ':' ( (lv_ownedPatternType_2_0= ruleTypeExpCS ) ) )
            // InternalCompleteOCL.g:3252:2: ( (lv_patternVariableName_0_0= ruleUnrestrictedName ) )? otherlv_1= ':' ( (lv_ownedPatternType_2_0= ruleTypeExpCS ) )
            {
            // InternalCompleteOCL.g:3252:2: ( (lv_patternVariableName_0_0= ruleUnrestrictedName ) )?
            int alt63=2;
            int LA63_0 = input.LA(1);

            if ( ((LA63_0>=RULE_SIMPLE_ID && LA63_0<=RULE_ESCAPED_ID)||(LA63_0>=28 && LA63_0<=30)) ) {
                alt63=1;
            }
            switch (alt63) {
                case 1 :
                    // InternalCompleteOCL.g:3253:1: (lv_patternVariableName_0_0= ruleUnrestrictedName )
                    {
                    // InternalCompleteOCL.g:3253:1: (lv_patternVariableName_0_0= ruleUnrestrictedName )
                    // InternalCompleteOCL.g:3254:3: lv_patternVariableName_0_0= ruleUnrestrictedName
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getPatternExpCSAccess().getPatternVariableNameUnrestrictedNameParserRuleCall_0_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_12);
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

            otherlv_1=(Token)match(input,23,FollowSets000.FOLLOW_20); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getPatternExpCSAccess().getColonKeyword_1());

            }
            // InternalCompleteOCL.g:3274:1: ( (lv_ownedPatternType_2_0= ruleTypeExpCS ) )
            // InternalCompleteOCL.g:3275:1: (lv_ownedPatternType_2_0= ruleTypeExpCS )
            {
            // InternalCompleteOCL.g:3275:1: (lv_ownedPatternType_2_0= ruleTypeExpCS )
            // InternalCompleteOCL.g:3276:3: lv_ownedPatternType_2_0= ruleTypeExpCS
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
    // InternalCompleteOCL.g:3300:1: entryRuleLambdaLiteralExpCS returns [EObject current=null] : iv_ruleLambdaLiteralExpCS= ruleLambdaLiteralExpCS EOF ;
    public final EObject entryRuleLambdaLiteralExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLambdaLiteralExpCS = null;


        try {
            // InternalCompleteOCL.g:3301:2: (iv_ruleLambdaLiteralExpCS= ruleLambdaLiteralExpCS EOF )
            // InternalCompleteOCL.g:3302:2: iv_ruleLambdaLiteralExpCS= ruleLambdaLiteralExpCS EOF
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
    // InternalCompleteOCL.g:3309:1: ruleLambdaLiteralExpCS returns [EObject current=null] : (otherlv_0= 'Lambda' otherlv_1= '{' ( (lv_ownedExpressionCS_2_0= ruleExpCS ) ) otherlv_3= '}' ) ;
    public final EObject ruleLambdaLiteralExpCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_ownedExpressionCS_2_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:3312:28: ( (otherlv_0= 'Lambda' otherlv_1= '{' ( (lv_ownedExpressionCS_2_0= ruleExpCS ) ) otherlv_3= '}' ) )
            // InternalCompleteOCL.g:3313:1: (otherlv_0= 'Lambda' otherlv_1= '{' ( (lv_ownedExpressionCS_2_0= ruleExpCS ) ) otherlv_3= '}' )
            {
            // InternalCompleteOCL.g:3313:1: (otherlv_0= 'Lambda' otherlv_1= '{' ( (lv_ownedExpressionCS_2_0= ruleExpCS ) ) otherlv_3= '}' )
            // InternalCompleteOCL.g:3313:3: otherlv_0= 'Lambda' otherlv_1= '{' ( (lv_ownedExpressionCS_2_0= ruleExpCS ) ) otherlv_3= '}'
            {
            otherlv_0=(Token)match(input,80,FollowSets000.FOLLOW_35); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getLambdaLiteralExpCSAccess().getLambdaKeyword_0());

            }
            otherlv_1=(Token)match(input,76,FollowSets000.FOLLOW_40); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getLambdaLiteralExpCSAccess().getLeftCurlyBracketKeyword_1());

            }
            // InternalCompleteOCL.g:3321:1: ( (lv_ownedExpressionCS_2_0= ruleExpCS ) )
            // InternalCompleteOCL.g:3322:1: (lv_ownedExpressionCS_2_0= ruleExpCS )
            {
            // InternalCompleteOCL.g:3322:1: (lv_ownedExpressionCS_2_0= ruleExpCS )
            // InternalCompleteOCL.g:3323:3: lv_ownedExpressionCS_2_0= ruleExpCS
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getLambdaLiteralExpCSAccess().getOwnedExpressionCSExpCSParserRuleCall_2_0());

            }
            pushFollow(FollowSets000.FOLLOW_43);
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

            otherlv_3=(Token)match(input,77,FollowSets000.FOLLOW_2); if (state.failed) return current;
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
    // InternalCompleteOCL.g:3351:1: entryRuleMapLiteralExpCS returns [EObject current=null] : iv_ruleMapLiteralExpCS= ruleMapLiteralExpCS EOF ;
    public final EObject entryRuleMapLiteralExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMapLiteralExpCS = null;


        try {
            // InternalCompleteOCL.g:3352:2: (iv_ruleMapLiteralExpCS= ruleMapLiteralExpCS EOF )
            // InternalCompleteOCL.g:3353:2: iv_ruleMapLiteralExpCS= ruleMapLiteralExpCS EOF
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
    // InternalCompleteOCL.g:3360:1: ruleMapLiteralExpCS returns [EObject current=null] : ( ( (lv_ownedType_0_0= ruleMapTypeCS ) ) otherlv_1= '{' ( ( (lv_ownedParts_2_0= ruleMapLiteralPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleMapLiteralPartCS ) ) )* )? otherlv_5= '}' ) ;
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
            // InternalCompleteOCL.g:3363:28: ( ( ( (lv_ownedType_0_0= ruleMapTypeCS ) ) otherlv_1= '{' ( ( (lv_ownedParts_2_0= ruleMapLiteralPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleMapLiteralPartCS ) ) )* )? otherlv_5= '}' ) )
            // InternalCompleteOCL.g:3364:1: ( ( (lv_ownedType_0_0= ruleMapTypeCS ) ) otherlv_1= '{' ( ( (lv_ownedParts_2_0= ruleMapLiteralPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleMapLiteralPartCS ) ) )* )? otherlv_5= '}' )
            {
            // InternalCompleteOCL.g:3364:1: ( ( (lv_ownedType_0_0= ruleMapTypeCS ) ) otherlv_1= '{' ( ( (lv_ownedParts_2_0= ruleMapLiteralPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleMapLiteralPartCS ) ) )* )? otherlv_5= '}' )
            // InternalCompleteOCL.g:3364:2: ( (lv_ownedType_0_0= ruleMapTypeCS ) ) otherlv_1= '{' ( ( (lv_ownedParts_2_0= ruleMapLiteralPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleMapLiteralPartCS ) ) )* )? otherlv_5= '}'
            {
            // InternalCompleteOCL.g:3364:2: ( (lv_ownedType_0_0= ruleMapTypeCS ) )
            // InternalCompleteOCL.g:3365:1: (lv_ownedType_0_0= ruleMapTypeCS )
            {
            // InternalCompleteOCL.g:3365:1: (lv_ownedType_0_0= ruleMapTypeCS )
            // InternalCompleteOCL.g:3366:3: lv_ownedType_0_0= ruleMapTypeCS
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getMapLiteralExpCSAccess().getOwnedTypeMapTypeCSParserRuleCall_0_0());

            }
            pushFollow(FollowSets000.FOLLOW_35);
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

            otherlv_1=(Token)match(input,76,FollowSets000.FOLLOW_44); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getMapLiteralExpCSAccess().getLeftCurlyBracketKeyword_1());

            }
            // InternalCompleteOCL.g:3386:1: ( ( (lv_ownedParts_2_0= ruleMapLiteralPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleMapLiteralPartCS ) ) )* )?
            int alt65=2;
            int LA65_0 = input.LA(1);

            if ( ((LA65_0>=RULE_SIMPLE_ID && LA65_0<=RULE_SINGLE_QUOTED_STRING)||LA65_0==21||(LA65_0>=28 && LA65_0<=30)||(LA65_0>=42 && LA65_0<=54)||(LA65_0>=68 && LA65_0<=69)||(LA65_0>=71 && LA65_0<=75)||LA65_0==80||(LA65_0>=82 && LA65_0<=85)||LA65_0==92||(LA65_0>=97 && LA65_0<=98)) ) {
                alt65=1;
            }
            switch (alt65) {
                case 1 :
                    // InternalCompleteOCL.g:3386:2: ( (lv_ownedParts_2_0= ruleMapLiteralPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleMapLiteralPartCS ) ) )*
                    {
                    // InternalCompleteOCL.g:3386:2: ( (lv_ownedParts_2_0= ruleMapLiteralPartCS ) )
                    // InternalCompleteOCL.g:3387:1: (lv_ownedParts_2_0= ruleMapLiteralPartCS )
                    {
                    // InternalCompleteOCL.g:3387:1: (lv_ownedParts_2_0= ruleMapLiteralPartCS )
                    // InternalCompleteOCL.g:3388:3: lv_ownedParts_2_0= ruleMapLiteralPartCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getMapLiteralExpCSAccess().getOwnedPartsMapLiteralPartCSParserRuleCall_2_0_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_37);
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

                    // InternalCompleteOCL.g:3404:2: (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleMapLiteralPartCS ) ) )*
                    loop64:
                    do {
                        int alt64=2;
                        int LA64_0 = input.LA(1);

                        if ( (LA64_0==26) ) {
                            alt64=1;
                        }


                        switch (alt64) {
                    	case 1 :
                    	    // InternalCompleteOCL.g:3404:4: otherlv_3= ',' ( (lv_ownedParts_4_0= ruleMapLiteralPartCS ) )
                    	    {
                    	    otherlv_3=(Token)match(input,26,FollowSets000.FOLLOW_40); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_3, grammarAccess.getMapLiteralExpCSAccess().getCommaKeyword_2_1_0());

                    	    }
                    	    // InternalCompleteOCL.g:3408:1: ( (lv_ownedParts_4_0= ruleMapLiteralPartCS ) )
                    	    // InternalCompleteOCL.g:3409:1: (lv_ownedParts_4_0= ruleMapLiteralPartCS )
                    	    {
                    	    // InternalCompleteOCL.g:3409:1: (lv_ownedParts_4_0= ruleMapLiteralPartCS )
                    	    // InternalCompleteOCL.g:3410:3: lv_ownedParts_4_0= ruleMapLiteralPartCS
                    	    {
                    	    if ( state.backtracking==0 ) {

                    	      	        newCompositeNode(grammarAccess.getMapLiteralExpCSAccess().getOwnedPartsMapLiteralPartCSParserRuleCall_2_1_1_0());

                    	    }
                    	    pushFollow(FollowSets000.FOLLOW_37);
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
                    	    break loop64;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_5=(Token)match(input,77,FollowSets000.FOLLOW_2); if (state.failed) return current;
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
    // InternalCompleteOCL.g:3438:1: entryRuleMapLiteralPartCS returns [EObject current=null] : iv_ruleMapLiteralPartCS= ruleMapLiteralPartCS EOF ;
    public final EObject entryRuleMapLiteralPartCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMapLiteralPartCS = null;


        try {
            // InternalCompleteOCL.g:3439:2: (iv_ruleMapLiteralPartCS= ruleMapLiteralPartCS EOF )
            // InternalCompleteOCL.g:3440:2: iv_ruleMapLiteralPartCS= ruleMapLiteralPartCS EOF
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
    // InternalCompleteOCL.g:3447:1: ruleMapLiteralPartCS returns [EObject current=null] : ( ( (lv_ownedKey_0_0= ruleExpCS ) ) otherlv_1= '<-' ( (lv_ownedValue_2_0= ruleExpCS ) ) ) ;
    public final EObject ruleMapLiteralPartCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject lv_ownedKey_0_0 = null;

        EObject lv_ownedValue_2_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:3450:28: ( ( ( (lv_ownedKey_0_0= ruleExpCS ) ) otherlv_1= '<-' ( (lv_ownedValue_2_0= ruleExpCS ) ) ) )
            // InternalCompleteOCL.g:3451:1: ( ( (lv_ownedKey_0_0= ruleExpCS ) ) otherlv_1= '<-' ( (lv_ownedValue_2_0= ruleExpCS ) ) )
            {
            // InternalCompleteOCL.g:3451:1: ( ( (lv_ownedKey_0_0= ruleExpCS ) ) otherlv_1= '<-' ( (lv_ownedValue_2_0= ruleExpCS ) ) )
            // InternalCompleteOCL.g:3451:2: ( (lv_ownedKey_0_0= ruleExpCS ) ) otherlv_1= '<-' ( (lv_ownedValue_2_0= ruleExpCS ) )
            {
            // InternalCompleteOCL.g:3451:2: ( (lv_ownedKey_0_0= ruleExpCS ) )
            // InternalCompleteOCL.g:3452:1: (lv_ownedKey_0_0= ruleExpCS )
            {
            // InternalCompleteOCL.g:3452:1: (lv_ownedKey_0_0= ruleExpCS )
            // InternalCompleteOCL.g:3453:3: lv_ownedKey_0_0= ruleExpCS
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getMapLiteralPartCSAccess().getOwnedKeyExpCSParserRuleCall_0_0());

            }
            pushFollow(FollowSets000.FOLLOW_45);
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

            otherlv_1=(Token)match(input,81,FollowSets000.FOLLOW_40); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getMapLiteralPartCSAccess().getLessThanSignHyphenMinusKeyword_1());

            }
            // InternalCompleteOCL.g:3473:1: ( (lv_ownedValue_2_0= ruleExpCS ) )
            // InternalCompleteOCL.g:3474:1: (lv_ownedValue_2_0= ruleExpCS )
            {
            // InternalCompleteOCL.g:3474:1: (lv_ownedValue_2_0= ruleExpCS )
            // InternalCompleteOCL.g:3475:3: lv_ownedValue_2_0= ruleExpCS
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
    // InternalCompleteOCL.g:3499:1: entryRulePrimitiveLiteralExpCS returns [EObject current=null] : iv_rulePrimitiveLiteralExpCS= rulePrimitiveLiteralExpCS EOF ;
    public final EObject entryRulePrimitiveLiteralExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePrimitiveLiteralExpCS = null;


        try {
            // InternalCompleteOCL.g:3500:2: (iv_rulePrimitiveLiteralExpCS= rulePrimitiveLiteralExpCS EOF )
            // InternalCompleteOCL.g:3501:2: iv_rulePrimitiveLiteralExpCS= rulePrimitiveLiteralExpCS EOF
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
    // InternalCompleteOCL.g:3508:1: rulePrimitiveLiteralExpCS returns [EObject current=null] : (this_NumberLiteralExpCS_0= ruleNumberLiteralExpCS | this_StringLiteralExpCS_1= ruleStringLiteralExpCS | this_BooleanLiteralExpCS_2= ruleBooleanLiteralExpCS | this_UnlimitedNaturalLiteralExpCS_3= ruleUnlimitedNaturalLiteralExpCS | this_InvalidLiteralExpCS_4= ruleInvalidLiteralExpCS | this_NullLiteralExpCS_5= ruleNullLiteralExpCS ) ;
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
            // InternalCompleteOCL.g:3511:28: ( (this_NumberLiteralExpCS_0= ruleNumberLiteralExpCS | this_StringLiteralExpCS_1= ruleStringLiteralExpCS | this_BooleanLiteralExpCS_2= ruleBooleanLiteralExpCS | this_UnlimitedNaturalLiteralExpCS_3= ruleUnlimitedNaturalLiteralExpCS | this_InvalidLiteralExpCS_4= ruleInvalidLiteralExpCS | this_NullLiteralExpCS_5= ruleNullLiteralExpCS ) )
            // InternalCompleteOCL.g:3512:1: (this_NumberLiteralExpCS_0= ruleNumberLiteralExpCS | this_StringLiteralExpCS_1= ruleStringLiteralExpCS | this_BooleanLiteralExpCS_2= ruleBooleanLiteralExpCS | this_UnlimitedNaturalLiteralExpCS_3= ruleUnlimitedNaturalLiteralExpCS | this_InvalidLiteralExpCS_4= ruleInvalidLiteralExpCS | this_NullLiteralExpCS_5= ruleNullLiteralExpCS )
            {
            // InternalCompleteOCL.g:3512:1: (this_NumberLiteralExpCS_0= ruleNumberLiteralExpCS | this_StringLiteralExpCS_1= ruleStringLiteralExpCS | this_BooleanLiteralExpCS_2= ruleBooleanLiteralExpCS | this_UnlimitedNaturalLiteralExpCS_3= ruleUnlimitedNaturalLiteralExpCS | this_InvalidLiteralExpCS_4= ruleInvalidLiteralExpCS | this_NullLiteralExpCS_5= ruleNullLiteralExpCS )
            int alt66=6;
            switch ( input.LA(1) ) {
            case RULE_INT:
                {
                alt66=1;
                }
                break;
            case RULE_SINGLE_QUOTED_STRING:
                {
                alt66=2;
                }
                break;
            case 82:
            case 83:
                {
                alt66=3;
                }
                break;
            case 54:
                {
                alt66=4;
                }
                break;
            case 84:
                {
                alt66=5;
                }
                break;
            case 85:
                {
                alt66=6;
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
                    // InternalCompleteOCL.g:3513:2: this_NumberLiteralExpCS_0= ruleNumberLiteralExpCS
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
                    // InternalCompleteOCL.g:3526:2: this_StringLiteralExpCS_1= ruleStringLiteralExpCS
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
                    // InternalCompleteOCL.g:3539:2: this_BooleanLiteralExpCS_2= ruleBooleanLiteralExpCS
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
                    // InternalCompleteOCL.g:3552:2: this_UnlimitedNaturalLiteralExpCS_3= ruleUnlimitedNaturalLiteralExpCS
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
                    // InternalCompleteOCL.g:3565:2: this_InvalidLiteralExpCS_4= ruleInvalidLiteralExpCS
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
                    // InternalCompleteOCL.g:3578:2: this_NullLiteralExpCS_5= ruleNullLiteralExpCS
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
    // InternalCompleteOCL.g:3597:1: entryRuleTupleLiteralExpCS returns [EObject current=null] : iv_ruleTupleLiteralExpCS= ruleTupleLiteralExpCS EOF ;
    public final EObject entryRuleTupleLiteralExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTupleLiteralExpCS = null;


        try {
            // InternalCompleteOCL.g:3598:2: (iv_ruleTupleLiteralExpCS= ruleTupleLiteralExpCS EOF )
            // InternalCompleteOCL.g:3599:2: iv_ruleTupleLiteralExpCS= ruleTupleLiteralExpCS EOF
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
    // InternalCompleteOCL.g:3606:1: ruleTupleLiteralExpCS returns [EObject current=null] : (otherlv_0= 'Tuple' otherlv_1= '{' ( (lv_ownedParts_2_0= ruleTupleLiteralPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleTupleLiteralPartCS ) ) )* otherlv_5= '}' ) ;
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
            // InternalCompleteOCL.g:3609:28: ( (otherlv_0= 'Tuple' otherlv_1= '{' ( (lv_ownedParts_2_0= ruleTupleLiteralPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleTupleLiteralPartCS ) ) )* otherlv_5= '}' ) )
            // InternalCompleteOCL.g:3610:1: (otherlv_0= 'Tuple' otherlv_1= '{' ( (lv_ownedParts_2_0= ruleTupleLiteralPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleTupleLiteralPartCS ) ) )* otherlv_5= '}' )
            {
            // InternalCompleteOCL.g:3610:1: (otherlv_0= 'Tuple' otherlv_1= '{' ( (lv_ownedParts_2_0= ruleTupleLiteralPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleTupleLiteralPartCS ) ) )* otherlv_5= '}' )
            // InternalCompleteOCL.g:3610:3: otherlv_0= 'Tuple' otherlv_1= '{' ( (lv_ownedParts_2_0= ruleTupleLiteralPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleTupleLiteralPartCS ) ) )* otherlv_5= '}'
            {
            otherlv_0=(Token)match(input,69,FollowSets000.FOLLOW_35); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getTupleLiteralExpCSAccess().getTupleKeyword_0());

            }
            otherlv_1=(Token)match(input,76,FollowSets000.FOLLOW_14); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getTupleLiteralExpCSAccess().getLeftCurlyBracketKeyword_1());

            }
            // InternalCompleteOCL.g:3618:1: ( (lv_ownedParts_2_0= ruleTupleLiteralPartCS ) )
            // InternalCompleteOCL.g:3619:1: (lv_ownedParts_2_0= ruleTupleLiteralPartCS )
            {
            // InternalCompleteOCL.g:3619:1: (lv_ownedParts_2_0= ruleTupleLiteralPartCS )
            // InternalCompleteOCL.g:3620:3: lv_ownedParts_2_0= ruleTupleLiteralPartCS
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getTupleLiteralExpCSAccess().getOwnedPartsTupleLiteralPartCSParserRuleCall_2_0());

            }
            pushFollow(FollowSets000.FOLLOW_37);
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

            // InternalCompleteOCL.g:3636:2: (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleTupleLiteralPartCS ) ) )*
            loop67:
            do {
                int alt67=2;
                int LA67_0 = input.LA(1);

                if ( (LA67_0==26) ) {
                    alt67=1;
                }


                switch (alt67) {
            	case 1 :
            	    // InternalCompleteOCL.g:3636:4: otherlv_3= ',' ( (lv_ownedParts_4_0= ruleTupleLiteralPartCS ) )
            	    {
            	    otherlv_3=(Token)match(input,26,FollowSets000.FOLLOW_14); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_3, grammarAccess.getTupleLiteralExpCSAccess().getCommaKeyword_3_0());

            	    }
            	    // InternalCompleteOCL.g:3640:1: ( (lv_ownedParts_4_0= ruleTupleLiteralPartCS ) )
            	    // InternalCompleteOCL.g:3641:1: (lv_ownedParts_4_0= ruleTupleLiteralPartCS )
            	    {
            	    // InternalCompleteOCL.g:3641:1: (lv_ownedParts_4_0= ruleTupleLiteralPartCS )
            	    // InternalCompleteOCL.g:3642:3: lv_ownedParts_4_0= ruleTupleLiteralPartCS
            	    {
            	    if ( state.backtracking==0 ) {

            	      	        newCompositeNode(grammarAccess.getTupleLiteralExpCSAccess().getOwnedPartsTupleLiteralPartCSParserRuleCall_3_1_0());

            	    }
            	    pushFollow(FollowSets000.FOLLOW_37);
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
            	    break loop67;
                }
            } while (true);

            otherlv_5=(Token)match(input,77,FollowSets000.FOLLOW_2); if (state.failed) return current;
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
    // InternalCompleteOCL.g:3670:1: entryRuleTupleLiteralPartCS returns [EObject current=null] : iv_ruleTupleLiteralPartCS= ruleTupleLiteralPartCS EOF ;
    public final EObject entryRuleTupleLiteralPartCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTupleLiteralPartCS = null;


        try {
            // InternalCompleteOCL.g:3671:2: (iv_ruleTupleLiteralPartCS= ruleTupleLiteralPartCS EOF )
            // InternalCompleteOCL.g:3672:2: iv_ruleTupleLiteralPartCS= ruleTupleLiteralPartCS EOF
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
    // InternalCompleteOCL.g:3679:1: ruleTupleLiteralPartCS returns [EObject current=null] : ( ( (lv_name_0_0= ruleUnrestrictedName ) ) (otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) ) )? otherlv_3= '=' ( (lv_ownedInitExpression_4_0= ruleExpCS ) ) ) ;
    public final EObject ruleTupleLiteralPartCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        AntlrDatatypeRuleToken lv_name_0_0 = null;

        EObject lv_ownedType_2_0 = null;

        EObject lv_ownedInitExpression_4_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:3682:28: ( ( ( (lv_name_0_0= ruleUnrestrictedName ) ) (otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) ) )? otherlv_3= '=' ( (lv_ownedInitExpression_4_0= ruleExpCS ) ) ) )
            // InternalCompleteOCL.g:3683:1: ( ( (lv_name_0_0= ruleUnrestrictedName ) ) (otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) ) )? otherlv_3= '=' ( (lv_ownedInitExpression_4_0= ruleExpCS ) ) )
            {
            // InternalCompleteOCL.g:3683:1: ( ( (lv_name_0_0= ruleUnrestrictedName ) ) (otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) ) )? otherlv_3= '=' ( (lv_ownedInitExpression_4_0= ruleExpCS ) ) )
            // InternalCompleteOCL.g:3683:2: ( (lv_name_0_0= ruleUnrestrictedName ) ) (otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) ) )? otherlv_3= '=' ( (lv_ownedInitExpression_4_0= ruleExpCS ) )
            {
            // InternalCompleteOCL.g:3683:2: ( (lv_name_0_0= ruleUnrestrictedName ) )
            // InternalCompleteOCL.g:3684:1: (lv_name_0_0= ruleUnrestrictedName )
            {
            // InternalCompleteOCL.g:3684:1: (lv_name_0_0= ruleUnrestrictedName )
            // InternalCompleteOCL.g:3685:3: lv_name_0_0= ruleUnrestrictedName
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getTupleLiteralPartCSAccess().getNameUnrestrictedNameParserRuleCall_0_0());

            }
            pushFollow(FollowSets000.FOLLOW_46);
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

            // InternalCompleteOCL.g:3701:2: (otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) ) )?
            int alt68=2;
            int LA68_0 = input.LA(1);

            if ( (LA68_0==23) ) {
                alt68=1;
            }
            switch (alt68) {
                case 1 :
                    // InternalCompleteOCL.g:3701:4: otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) )
                    {
                    otherlv_1=(Token)match(input,23,FollowSets000.FOLLOW_20); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_1, grammarAccess.getTupleLiteralPartCSAccess().getColonKeyword_1_0());

                    }
                    // InternalCompleteOCL.g:3705:1: ( (lv_ownedType_2_0= ruleTypeExpCS ) )
                    // InternalCompleteOCL.g:3706:1: (lv_ownedType_2_0= ruleTypeExpCS )
                    {
                    // InternalCompleteOCL.g:3706:1: (lv_ownedType_2_0= ruleTypeExpCS )
                    // InternalCompleteOCL.g:3707:3: lv_ownedType_2_0= ruleTypeExpCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getTupleLiteralPartCSAccess().getOwnedTypeTypeExpCSParserRuleCall_1_1_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_19);
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

            otherlv_3=(Token)match(input,27,FollowSets000.FOLLOW_40); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_3, grammarAccess.getTupleLiteralPartCSAccess().getEqualsSignKeyword_2());

            }
            // InternalCompleteOCL.g:3727:1: ( (lv_ownedInitExpression_4_0= ruleExpCS ) )
            // InternalCompleteOCL.g:3728:1: (lv_ownedInitExpression_4_0= ruleExpCS )
            {
            // InternalCompleteOCL.g:3728:1: (lv_ownedInitExpression_4_0= ruleExpCS )
            // InternalCompleteOCL.g:3729:3: lv_ownedInitExpression_4_0= ruleExpCS
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
    // InternalCompleteOCL.g:3753:1: entryRuleNumberLiteralExpCS returns [EObject current=null] : iv_ruleNumberLiteralExpCS= ruleNumberLiteralExpCS EOF ;
    public final EObject entryRuleNumberLiteralExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNumberLiteralExpCS = null;


        try {
            // InternalCompleteOCL.g:3754:2: (iv_ruleNumberLiteralExpCS= ruleNumberLiteralExpCS EOF )
            // InternalCompleteOCL.g:3755:2: iv_ruleNumberLiteralExpCS= ruleNumberLiteralExpCS EOF
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
    // InternalCompleteOCL.g:3762:1: ruleNumberLiteralExpCS returns [EObject current=null] : ( (lv_symbol_0_0= ruleNUMBER_LITERAL ) ) ;
    public final EObject ruleNumberLiteralExpCS() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_symbol_0_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:3765:28: ( ( (lv_symbol_0_0= ruleNUMBER_LITERAL ) ) )
            // InternalCompleteOCL.g:3766:1: ( (lv_symbol_0_0= ruleNUMBER_LITERAL ) )
            {
            // InternalCompleteOCL.g:3766:1: ( (lv_symbol_0_0= ruleNUMBER_LITERAL ) )
            // InternalCompleteOCL.g:3767:1: (lv_symbol_0_0= ruleNUMBER_LITERAL )
            {
            // InternalCompleteOCL.g:3767:1: (lv_symbol_0_0= ruleNUMBER_LITERAL )
            // InternalCompleteOCL.g:3768:3: lv_symbol_0_0= ruleNUMBER_LITERAL
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
    // InternalCompleteOCL.g:3792:1: entryRuleStringLiteralExpCS returns [EObject current=null] : iv_ruleStringLiteralExpCS= ruleStringLiteralExpCS EOF ;
    public final EObject entryRuleStringLiteralExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleStringLiteralExpCS = null;


        try {
            // InternalCompleteOCL.g:3793:2: (iv_ruleStringLiteralExpCS= ruleStringLiteralExpCS EOF )
            // InternalCompleteOCL.g:3794:2: iv_ruleStringLiteralExpCS= ruleStringLiteralExpCS EOF
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
    // InternalCompleteOCL.g:3801:1: ruleStringLiteralExpCS returns [EObject current=null] : ( (lv_segments_0_0= ruleStringLiteral ) )+ ;
    public final EObject ruleStringLiteralExpCS() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_segments_0_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:3804:28: ( ( (lv_segments_0_0= ruleStringLiteral ) )+ )
            // InternalCompleteOCL.g:3805:1: ( (lv_segments_0_0= ruleStringLiteral ) )+
            {
            // InternalCompleteOCL.g:3805:1: ( (lv_segments_0_0= ruleStringLiteral ) )+
            int cnt69=0;
            loop69:
            do {
                int alt69=2;
                int LA69_0 = input.LA(1);

                if ( (LA69_0==RULE_SINGLE_QUOTED_STRING) ) {
                    alt69=1;
                }


                switch (alt69) {
            	case 1 :
            	    // InternalCompleteOCL.g:3806:1: (lv_segments_0_0= ruleStringLiteral )
            	    {
            	    // InternalCompleteOCL.g:3806:1: (lv_segments_0_0= ruleStringLiteral )
            	    // InternalCompleteOCL.g:3807:3: lv_segments_0_0= ruleStringLiteral
            	    {
            	    if ( state.backtracking==0 ) {

            	      	        newCompositeNode(grammarAccess.getStringLiteralExpCSAccess().getSegmentsStringLiteralParserRuleCall_0());

            	    }
            	    pushFollow(FollowSets000.FOLLOW_47);
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
            	    if ( cnt69 >= 1 ) break loop69;
            	    if (state.backtracking>0) {state.failed=true; return current;}
                        EarlyExitException eee =
                            new EarlyExitException(69, input);
                        throw eee;
                }
                cnt69++;
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
    // InternalCompleteOCL.g:3831:1: entryRuleBooleanLiteralExpCS returns [EObject current=null] : iv_ruleBooleanLiteralExpCS= ruleBooleanLiteralExpCS EOF ;
    public final EObject entryRuleBooleanLiteralExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleBooleanLiteralExpCS = null;


        try {
            // InternalCompleteOCL.g:3832:2: (iv_ruleBooleanLiteralExpCS= ruleBooleanLiteralExpCS EOF )
            // InternalCompleteOCL.g:3833:2: iv_ruleBooleanLiteralExpCS= ruleBooleanLiteralExpCS EOF
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
    // InternalCompleteOCL.g:3840:1: ruleBooleanLiteralExpCS returns [EObject current=null] : ( ( (lv_symbol_0_0= 'true' ) ) | ( (lv_symbol_1_0= 'false' ) ) ) ;
    public final EObject ruleBooleanLiteralExpCS() throws RecognitionException {
        EObject current = null;

        Token lv_symbol_0_0=null;
        Token lv_symbol_1_0=null;

         enterRule();

        try {
            // InternalCompleteOCL.g:3843:28: ( ( ( (lv_symbol_0_0= 'true' ) ) | ( (lv_symbol_1_0= 'false' ) ) ) )
            // InternalCompleteOCL.g:3844:1: ( ( (lv_symbol_0_0= 'true' ) ) | ( (lv_symbol_1_0= 'false' ) ) )
            {
            // InternalCompleteOCL.g:3844:1: ( ( (lv_symbol_0_0= 'true' ) ) | ( (lv_symbol_1_0= 'false' ) ) )
            int alt70=2;
            int LA70_0 = input.LA(1);

            if ( (LA70_0==82) ) {
                alt70=1;
            }
            else if ( (LA70_0==83) ) {
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
                    // InternalCompleteOCL.g:3844:2: ( (lv_symbol_0_0= 'true' ) )
                    {
                    // InternalCompleteOCL.g:3844:2: ( (lv_symbol_0_0= 'true' ) )
                    // InternalCompleteOCL.g:3845:1: (lv_symbol_0_0= 'true' )
                    {
                    // InternalCompleteOCL.g:3845:1: (lv_symbol_0_0= 'true' )
                    // InternalCompleteOCL.g:3846:3: lv_symbol_0_0= 'true'
                    {
                    lv_symbol_0_0=(Token)match(input,82,FollowSets000.FOLLOW_2); if (state.failed) return current;
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
                    // InternalCompleteOCL.g:3860:6: ( (lv_symbol_1_0= 'false' ) )
                    {
                    // InternalCompleteOCL.g:3860:6: ( (lv_symbol_1_0= 'false' ) )
                    // InternalCompleteOCL.g:3861:1: (lv_symbol_1_0= 'false' )
                    {
                    // InternalCompleteOCL.g:3861:1: (lv_symbol_1_0= 'false' )
                    // InternalCompleteOCL.g:3862:3: lv_symbol_1_0= 'false'
                    {
                    lv_symbol_1_0=(Token)match(input,83,FollowSets000.FOLLOW_2); if (state.failed) return current;
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
    // InternalCompleteOCL.g:3883:1: entryRuleUnlimitedNaturalLiteralExpCS returns [EObject current=null] : iv_ruleUnlimitedNaturalLiteralExpCS= ruleUnlimitedNaturalLiteralExpCS EOF ;
    public final EObject entryRuleUnlimitedNaturalLiteralExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleUnlimitedNaturalLiteralExpCS = null;


        try {
            // InternalCompleteOCL.g:3884:2: (iv_ruleUnlimitedNaturalLiteralExpCS= ruleUnlimitedNaturalLiteralExpCS EOF )
            // InternalCompleteOCL.g:3885:2: iv_ruleUnlimitedNaturalLiteralExpCS= ruleUnlimitedNaturalLiteralExpCS EOF
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
    // InternalCompleteOCL.g:3892:1: ruleUnlimitedNaturalLiteralExpCS returns [EObject current=null] : ( () otherlv_1= '*' ) ;
    public final EObject ruleUnlimitedNaturalLiteralExpCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;

         enterRule();

        try {
            // InternalCompleteOCL.g:3895:28: ( ( () otherlv_1= '*' ) )
            // InternalCompleteOCL.g:3896:1: ( () otherlv_1= '*' )
            {
            // InternalCompleteOCL.g:3896:1: ( () otherlv_1= '*' )
            // InternalCompleteOCL.g:3896:2: () otherlv_1= '*'
            {
            // InternalCompleteOCL.g:3896:2: ()
            // InternalCompleteOCL.g:3897:2:
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

            otherlv_1=(Token)match(input,54,FollowSets000.FOLLOW_2); if (state.failed) return current;
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
    // InternalCompleteOCL.g:3917:1: entryRuleInvalidLiteralExpCS returns [EObject current=null] : iv_ruleInvalidLiteralExpCS= ruleInvalidLiteralExpCS EOF ;
    public final EObject entryRuleInvalidLiteralExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleInvalidLiteralExpCS = null;


        try {
            // InternalCompleteOCL.g:3918:2: (iv_ruleInvalidLiteralExpCS= ruleInvalidLiteralExpCS EOF )
            // InternalCompleteOCL.g:3919:2: iv_ruleInvalidLiteralExpCS= ruleInvalidLiteralExpCS EOF
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
    // InternalCompleteOCL.g:3926:1: ruleInvalidLiteralExpCS returns [EObject current=null] : ( () otherlv_1= 'invalid' ) ;
    public final EObject ruleInvalidLiteralExpCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;

         enterRule();

        try {
            // InternalCompleteOCL.g:3929:28: ( ( () otherlv_1= 'invalid' ) )
            // InternalCompleteOCL.g:3930:1: ( () otherlv_1= 'invalid' )
            {
            // InternalCompleteOCL.g:3930:1: ( () otherlv_1= 'invalid' )
            // InternalCompleteOCL.g:3930:2: () otherlv_1= 'invalid'
            {
            // InternalCompleteOCL.g:3930:2: ()
            // InternalCompleteOCL.g:3931:2:
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

            otherlv_1=(Token)match(input,84,FollowSets000.FOLLOW_2); if (state.failed) return current;
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
    // InternalCompleteOCL.g:3951:1: entryRuleNullLiteralExpCS returns [EObject current=null] : iv_ruleNullLiteralExpCS= ruleNullLiteralExpCS EOF ;
    public final EObject entryRuleNullLiteralExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNullLiteralExpCS = null;


        try {
            // InternalCompleteOCL.g:3952:2: (iv_ruleNullLiteralExpCS= ruleNullLiteralExpCS EOF )
            // InternalCompleteOCL.g:3953:2: iv_ruleNullLiteralExpCS= ruleNullLiteralExpCS EOF
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
    // InternalCompleteOCL.g:3960:1: ruleNullLiteralExpCS returns [EObject current=null] : ( () otherlv_1= 'null' ) ;
    public final EObject ruleNullLiteralExpCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;

         enterRule();

        try {
            // InternalCompleteOCL.g:3963:28: ( ( () otherlv_1= 'null' ) )
            // InternalCompleteOCL.g:3964:1: ( () otherlv_1= 'null' )
            {
            // InternalCompleteOCL.g:3964:1: ( () otherlv_1= 'null' )
            // InternalCompleteOCL.g:3964:2: () otherlv_1= 'null'
            {
            // InternalCompleteOCL.g:3964:2: ()
            // InternalCompleteOCL.g:3965:2:
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

            otherlv_1=(Token)match(input,85,FollowSets000.FOLLOW_2); if (state.failed) return current;
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
    // InternalCompleteOCL.g:3985:1: entryRuleTypeLiteralCS returns [EObject current=null] : iv_ruleTypeLiteralCS= ruleTypeLiteralCS EOF ;
    public final EObject entryRuleTypeLiteralCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTypeLiteralCS = null;


        try {
            // InternalCompleteOCL.g:3986:2: (iv_ruleTypeLiteralCS= ruleTypeLiteralCS EOF )
            // InternalCompleteOCL.g:3987:2: iv_ruleTypeLiteralCS= ruleTypeLiteralCS EOF
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
    // InternalCompleteOCL.g:3994:1: ruleTypeLiteralCS returns [EObject current=null] : (this_PrimitiveTypeCS_0= rulePrimitiveTypeCS | this_CollectionTypeCS_1= ruleCollectionTypeCS | this_MapTypeCS_2= ruleMapTypeCS | this_TupleTypeCS_3= ruleTupleTypeCS ) ;
    public final EObject ruleTypeLiteralCS() throws RecognitionException {
        EObject current = null;

        EObject this_PrimitiveTypeCS_0 = null;

        EObject this_CollectionTypeCS_1 = null;

        EObject this_MapTypeCS_2 = null;

        EObject this_TupleTypeCS_3 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:3997:28: ( (this_PrimitiveTypeCS_0= rulePrimitiveTypeCS | this_CollectionTypeCS_1= ruleCollectionTypeCS | this_MapTypeCS_2= ruleMapTypeCS | this_TupleTypeCS_3= ruleTupleTypeCS ) )
            // InternalCompleteOCL.g:3998:1: (this_PrimitiveTypeCS_0= rulePrimitiveTypeCS | this_CollectionTypeCS_1= ruleCollectionTypeCS | this_MapTypeCS_2= ruleMapTypeCS | this_TupleTypeCS_3= ruleTupleTypeCS )
            {
            // InternalCompleteOCL.g:3998:1: (this_PrimitiveTypeCS_0= rulePrimitiveTypeCS | this_CollectionTypeCS_1= ruleCollectionTypeCS | this_MapTypeCS_2= ruleMapTypeCS | this_TupleTypeCS_3= ruleTupleTypeCS )
            int alt71=4;
            switch ( input.LA(1) ) {
            case 42:
            case 43:
            case 44:
            case 45:
            case 46:
            case 47:
            case 48:
            case 49:
            case 50:
            case 51:
                {
                alt71=1;
                }
                break;
            case 71:
            case 72:
            case 73:
            case 74:
            case 75:
                {
                alt71=2;
                }
                break;
            case 68:
                {
                alt71=3;
                }
                break;
            case 69:
                {
                alt71=4;
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
                    // InternalCompleteOCL.g:3999:2: this_PrimitiveTypeCS_0= rulePrimitiveTypeCS
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
                    // InternalCompleteOCL.g:4012:2: this_CollectionTypeCS_1= ruleCollectionTypeCS
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
                    // InternalCompleteOCL.g:4025:2: this_MapTypeCS_2= ruleMapTypeCS
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
                    // InternalCompleteOCL.g:4038:2: this_TupleTypeCS_3= ruleTupleTypeCS
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
    // InternalCompleteOCL.g:4057:1: entryRuleTypeLiteralWithMultiplicityCS returns [EObject current=null] : iv_ruleTypeLiteralWithMultiplicityCS= ruleTypeLiteralWithMultiplicityCS EOF ;
    public final EObject entryRuleTypeLiteralWithMultiplicityCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTypeLiteralWithMultiplicityCS = null;


        try {
            // InternalCompleteOCL.g:4058:2: (iv_ruleTypeLiteralWithMultiplicityCS= ruleTypeLiteralWithMultiplicityCS EOF )
            // InternalCompleteOCL.g:4059:2: iv_ruleTypeLiteralWithMultiplicityCS= ruleTypeLiteralWithMultiplicityCS EOF
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
    // InternalCompleteOCL.g:4066:1: ruleTypeLiteralWithMultiplicityCS returns [EObject current=null] : (this_TypeLiteralCS_0= ruleTypeLiteralCS ( (lv_ownedMultiplicity_1_0= ruleMultiplicityCS ) )? ) ;
    public final EObject ruleTypeLiteralWithMultiplicityCS() throws RecognitionException {
        EObject current = null;

        EObject this_TypeLiteralCS_0 = null;

        EObject lv_ownedMultiplicity_1_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:4069:28: ( (this_TypeLiteralCS_0= ruleTypeLiteralCS ( (lv_ownedMultiplicity_1_0= ruleMultiplicityCS ) )? ) )
            // InternalCompleteOCL.g:4070:1: (this_TypeLiteralCS_0= ruleTypeLiteralCS ( (lv_ownedMultiplicity_1_0= ruleMultiplicityCS ) )? )
            {
            // InternalCompleteOCL.g:4070:1: (this_TypeLiteralCS_0= ruleTypeLiteralCS ( (lv_ownedMultiplicity_1_0= ruleMultiplicityCS ) )? )
            // InternalCompleteOCL.g:4071:2: this_TypeLiteralCS_0= ruleTypeLiteralCS ( (lv_ownedMultiplicity_1_0= ruleMultiplicityCS ) )?
            {
            if ( state.backtracking==0 ) {

              	  /* */

            }
            if ( state.backtracking==0 ) {

                      newCompositeNode(grammarAccess.getTypeLiteralWithMultiplicityCSAccess().getTypeLiteralCSParserRuleCall_0());

            }
            pushFollow(FollowSets000.FOLLOW_48);
            this_TypeLiteralCS_0=ruleTypeLiteralCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

                      current = this_TypeLiteralCS_0;
                      afterParserOrEnumRuleCall();

            }
            // InternalCompleteOCL.g:4082:1: ( (lv_ownedMultiplicity_1_0= ruleMultiplicityCS ) )?
            int alt72=2;
            int LA72_0 = input.LA(1);

            if ( (LA72_0==87) ) {
                alt72=1;
            }
            switch (alt72) {
                case 1 :
                    // InternalCompleteOCL.g:4083:1: (lv_ownedMultiplicity_1_0= ruleMultiplicityCS )
                    {
                    // InternalCompleteOCL.g:4083:1: (lv_ownedMultiplicity_1_0= ruleMultiplicityCS )
                    // InternalCompleteOCL.g:4084:3: lv_ownedMultiplicity_1_0= ruleMultiplicityCS
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
    // InternalCompleteOCL.g:4108:1: entryRuleTypeLiteralExpCS returns [EObject current=null] : iv_ruleTypeLiteralExpCS= ruleTypeLiteralExpCS EOF ;
    public final EObject entryRuleTypeLiteralExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTypeLiteralExpCS = null;


        try {
            // InternalCompleteOCL.g:4109:2: (iv_ruleTypeLiteralExpCS= ruleTypeLiteralExpCS EOF )
            // InternalCompleteOCL.g:4110:2: iv_ruleTypeLiteralExpCS= ruleTypeLiteralExpCS EOF
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
    // InternalCompleteOCL.g:4117:1: ruleTypeLiteralExpCS returns [EObject current=null] : ( (lv_ownedType_0_0= ruleTypeLiteralWithMultiplicityCS ) ) ;
    public final EObject ruleTypeLiteralExpCS() throws RecognitionException {
        EObject current = null;

        EObject lv_ownedType_0_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:4120:28: ( ( (lv_ownedType_0_0= ruleTypeLiteralWithMultiplicityCS ) ) )
            // InternalCompleteOCL.g:4121:1: ( (lv_ownedType_0_0= ruleTypeLiteralWithMultiplicityCS ) )
            {
            // InternalCompleteOCL.g:4121:1: ( (lv_ownedType_0_0= ruleTypeLiteralWithMultiplicityCS ) )
            // InternalCompleteOCL.g:4122:1: (lv_ownedType_0_0= ruleTypeLiteralWithMultiplicityCS )
            {
            // InternalCompleteOCL.g:4122:1: (lv_ownedType_0_0= ruleTypeLiteralWithMultiplicityCS )
            // InternalCompleteOCL.g:4123:3: lv_ownedType_0_0= ruleTypeLiteralWithMultiplicityCS
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
    // InternalCompleteOCL.g:4147:1: entryRuleTypeNameExpCS returns [EObject current=null] : iv_ruleTypeNameExpCS= ruleTypeNameExpCS EOF ;
    public final EObject entryRuleTypeNameExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTypeNameExpCS = null;


        try {
            // InternalCompleteOCL.g:4148:2: (iv_ruleTypeNameExpCS= ruleTypeNameExpCS EOF )
            // InternalCompleteOCL.g:4149:2: iv_ruleTypeNameExpCS= ruleTypeNameExpCS EOF
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
    // InternalCompleteOCL.g:4156:1: ruleTypeNameExpCS returns [EObject current=null] : ( ( (lv_ownedPathName_0_0= rulePathNameCS ) ) ( ( (lv_ownedCurlyBracketedClause_1_0= ruleCurlyBracketedClauseCS ) ) (otherlv_2= '{' ( (lv_ownedPatternGuard_3_0= ruleExpCS ) ) otherlv_4= '}' )? )? ) ;
    public final EObject ruleTypeNameExpCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_ownedPathName_0_0 = null;

        EObject lv_ownedCurlyBracketedClause_1_0 = null;

        EObject lv_ownedPatternGuard_3_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:4159:28: ( ( ( (lv_ownedPathName_0_0= rulePathNameCS ) ) ( ( (lv_ownedCurlyBracketedClause_1_0= ruleCurlyBracketedClauseCS ) ) (otherlv_2= '{' ( (lv_ownedPatternGuard_3_0= ruleExpCS ) ) otherlv_4= '}' )? )? ) )
            // InternalCompleteOCL.g:4160:1: ( ( (lv_ownedPathName_0_0= rulePathNameCS ) ) ( ( (lv_ownedCurlyBracketedClause_1_0= ruleCurlyBracketedClauseCS ) ) (otherlv_2= '{' ( (lv_ownedPatternGuard_3_0= ruleExpCS ) ) otherlv_4= '}' )? )? )
            {
            // InternalCompleteOCL.g:4160:1: ( ( (lv_ownedPathName_0_0= rulePathNameCS ) ) ( ( (lv_ownedCurlyBracketedClause_1_0= ruleCurlyBracketedClauseCS ) ) (otherlv_2= '{' ( (lv_ownedPatternGuard_3_0= ruleExpCS ) ) otherlv_4= '}' )? )? )
            // InternalCompleteOCL.g:4160:2: ( (lv_ownedPathName_0_0= rulePathNameCS ) ) ( ( (lv_ownedCurlyBracketedClause_1_0= ruleCurlyBracketedClauseCS ) ) (otherlv_2= '{' ( (lv_ownedPatternGuard_3_0= ruleExpCS ) ) otherlv_4= '}' )? )?
            {
            // InternalCompleteOCL.g:4160:2: ( (lv_ownedPathName_0_0= rulePathNameCS ) )
            // InternalCompleteOCL.g:4161:1: (lv_ownedPathName_0_0= rulePathNameCS )
            {
            // InternalCompleteOCL.g:4161:1: (lv_ownedPathName_0_0= rulePathNameCS )
            // InternalCompleteOCL.g:4162:3: lv_ownedPathName_0_0= rulePathNameCS
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getTypeNameExpCSAccess().getOwnedPathNamePathNameCSParserRuleCall_0_0());

            }
            pushFollow(FollowSets000.FOLLOW_49);
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

            // InternalCompleteOCL.g:4178:2: ( ( (lv_ownedCurlyBracketedClause_1_0= ruleCurlyBracketedClauseCS ) ) (otherlv_2= '{' ( (lv_ownedPatternGuard_3_0= ruleExpCS ) ) otherlv_4= '}' )? )?
            int alt74=2;
            int LA74_0 = input.LA(1);

            if ( (LA74_0==76) ) {
                alt74=1;
            }
            switch (alt74) {
                case 1 :
                    // InternalCompleteOCL.g:4178:3: ( (lv_ownedCurlyBracketedClause_1_0= ruleCurlyBracketedClauseCS ) ) (otherlv_2= '{' ( (lv_ownedPatternGuard_3_0= ruleExpCS ) ) otherlv_4= '}' )?
                    {
                    // InternalCompleteOCL.g:4178:3: ( (lv_ownedCurlyBracketedClause_1_0= ruleCurlyBracketedClauseCS ) )
                    // InternalCompleteOCL.g:4179:1: (lv_ownedCurlyBracketedClause_1_0= ruleCurlyBracketedClauseCS )
                    {
                    // InternalCompleteOCL.g:4179:1: (lv_ownedCurlyBracketedClause_1_0= ruleCurlyBracketedClauseCS )
                    // InternalCompleteOCL.g:4180:3: lv_ownedCurlyBracketedClause_1_0= ruleCurlyBracketedClauseCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getTypeNameExpCSAccess().getOwnedCurlyBracketedClauseCurlyBracketedClauseCSParserRuleCall_1_0_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_49);
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

                    // InternalCompleteOCL.g:4196:2: (otherlv_2= '{' ( (lv_ownedPatternGuard_3_0= ruleExpCS ) ) otherlv_4= '}' )?
                    int alt73=2;
                    int LA73_0 = input.LA(1);

                    if ( (LA73_0==76) ) {
                        alt73=1;
                    }
                    switch (alt73) {
                        case 1 :
                            // InternalCompleteOCL.g:4196:4: otherlv_2= '{' ( (lv_ownedPatternGuard_3_0= ruleExpCS ) ) otherlv_4= '}'
                            {
                            otherlv_2=(Token)match(input,76,FollowSets000.FOLLOW_40); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                  	newLeafNode(otherlv_2, grammarAccess.getTypeNameExpCSAccess().getLeftCurlyBracketKeyword_1_1_0());

                            }
                            // InternalCompleteOCL.g:4200:1: ( (lv_ownedPatternGuard_3_0= ruleExpCS ) )
                            // InternalCompleteOCL.g:4201:1: (lv_ownedPatternGuard_3_0= ruleExpCS )
                            {
                            // InternalCompleteOCL.g:4201:1: (lv_ownedPatternGuard_3_0= ruleExpCS )
                            // InternalCompleteOCL.g:4202:3: lv_ownedPatternGuard_3_0= ruleExpCS
                            {
                            if ( state.backtracking==0 ) {

                              	        newCompositeNode(grammarAccess.getTypeNameExpCSAccess().getOwnedPatternGuardExpCSParserRuleCall_1_1_1_0());

                            }
                            pushFollow(FollowSets000.FOLLOW_43);
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

                            otherlv_4=(Token)match(input,77,FollowSets000.FOLLOW_2); if (state.failed) return current;
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
    // InternalCompleteOCL.g:4230:1: entryRuleTypeExpWithoutMultiplicityCS returns [EObject current=null] : iv_ruleTypeExpWithoutMultiplicityCS= ruleTypeExpWithoutMultiplicityCS EOF ;
    public final EObject entryRuleTypeExpWithoutMultiplicityCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTypeExpWithoutMultiplicityCS = null;


        try {
            // InternalCompleteOCL.g:4231:2: (iv_ruleTypeExpWithoutMultiplicityCS= ruleTypeExpWithoutMultiplicityCS EOF )
            // InternalCompleteOCL.g:4232:2: iv_ruleTypeExpWithoutMultiplicityCS= ruleTypeExpWithoutMultiplicityCS EOF
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
    // InternalCompleteOCL.g:4239:1: ruleTypeExpWithoutMultiplicityCS returns [EObject current=null] : (this_TypeNameExpCS_0= ruleTypeNameExpCS | this_TypeLiteralCS_1= ruleTypeLiteralCS | this_CollectionPatternCS_2= ruleCollectionPatternCS ) ;
    public final EObject ruleTypeExpWithoutMultiplicityCS() throws RecognitionException {
        EObject current = null;

        EObject this_TypeNameExpCS_0 = null;

        EObject this_TypeLiteralCS_1 = null;

        EObject this_CollectionPatternCS_2 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:4242:28: ( (this_TypeNameExpCS_0= ruleTypeNameExpCS | this_TypeLiteralCS_1= ruleTypeLiteralCS | this_CollectionPatternCS_2= ruleCollectionPatternCS ) )
            // InternalCompleteOCL.g:4243:1: (this_TypeNameExpCS_0= ruleTypeNameExpCS | this_TypeLiteralCS_1= ruleTypeLiteralCS | this_CollectionPatternCS_2= ruleCollectionPatternCS )
            {
            // InternalCompleteOCL.g:4243:1: (this_TypeNameExpCS_0= ruleTypeNameExpCS | this_TypeLiteralCS_1= ruleTypeLiteralCS | this_CollectionPatternCS_2= ruleCollectionPatternCS )
            int alt75=3;
            alt75 = dfa75.predict(input);
            switch (alt75) {
                case 1 :
                    // InternalCompleteOCL.g:4244:2: this_TypeNameExpCS_0= ruleTypeNameExpCS
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
                    // InternalCompleteOCL.g:4257:2: this_TypeLiteralCS_1= ruleTypeLiteralCS
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
                    // InternalCompleteOCL.g:4270:2: this_CollectionPatternCS_2= ruleCollectionPatternCS
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
    // InternalCompleteOCL.g:4289:1: entryRuleTypeExpCS returns [EObject current=null] : iv_ruleTypeExpCS= ruleTypeExpCS EOF ;
    public final EObject entryRuleTypeExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTypeExpCS = null;


        try {
            // InternalCompleteOCL.g:4290:2: (iv_ruleTypeExpCS= ruleTypeExpCS EOF )
            // InternalCompleteOCL.g:4291:2: iv_ruleTypeExpCS= ruleTypeExpCS EOF
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
    // InternalCompleteOCL.g:4298:1: ruleTypeExpCS returns [EObject current=null] : (this_TypeExpWithoutMultiplicityCS_0= ruleTypeExpWithoutMultiplicityCS ( (lv_ownedMultiplicity_1_0= ruleMultiplicityCS ) )? ) ;
    public final EObject ruleTypeExpCS() throws RecognitionException {
        EObject current = null;

        EObject this_TypeExpWithoutMultiplicityCS_0 = null;

        EObject lv_ownedMultiplicity_1_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:4301:28: ( (this_TypeExpWithoutMultiplicityCS_0= ruleTypeExpWithoutMultiplicityCS ( (lv_ownedMultiplicity_1_0= ruleMultiplicityCS ) )? ) )
            // InternalCompleteOCL.g:4302:1: (this_TypeExpWithoutMultiplicityCS_0= ruleTypeExpWithoutMultiplicityCS ( (lv_ownedMultiplicity_1_0= ruleMultiplicityCS ) )? )
            {
            // InternalCompleteOCL.g:4302:1: (this_TypeExpWithoutMultiplicityCS_0= ruleTypeExpWithoutMultiplicityCS ( (lv_ownedMultiplicity_1_0= ruleMultiplicityCS ) )? )
            // InternalCompleteOCL.g:4303:2: this_TypeExpWithoutMultiplicityCS_0= ruleTypeExpWithoutMultiplicityCS ( (lv_ownedMultiplicity_1_0= ruleMultiplicityCS ) )?
            {
            if ( state.backtracking==0 ) {

              	  /* */

            }
            if ( state.backtracking==0 ) {

                      newCompositeNode(grammarAccess.getTypeExpCSAccess().getTypeExpWithoutMultiplicityCSParserRuleCall_0());

            }
            pushFollow(FollowSets000.FOLLOW_48);
            this_TypeExpWithoutMultiplicityCS_0=ruleTypeExpWithoutMultiplicityCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

                      current = this_TypeExpWithoutMultiplicityCS_0;
                      afterParserOrEnumRuleCall();

            }
            // InternalCompleteOCL.g:4314:1: ( (lv_ownedMultiplicity_1_0= ruleMultiplicityCS ) )?
            int alt76=2;
            int LA76_0 = input.LA(1);

            if ( (LA76_0==87) ) {
                alt76=1;
            }
            switch (alt76) {
                case 1 :
                    // InternalCompleteOCL.g:4315:1: (lv_ownedMultiplicity_1_0= ruleMultiplicityCS )
                    {
                    // InternalCompleteOCL.g:4315:1: (lv_ownedMultiplicity_1_0= ruleMultiplicityCS )
                    // InternalCompleteOCL.g:4316:3: lv_ownedMultiplicity_1_0= ruleMultiplicityCS
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
    // InternalCompleteOCL.g:4340:1: entryRuleExpCS returns [EObject current=null] : iv_ruleExpCS= ruleExpCS EOF ;
    public final EObject entryRuleExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpCS = null;


        try {
            // InternalCompleteOCL.g:4341:2: (iv_ruleExpCS= ruleExpCS EOF )
            // InternalCompleteOCL.g:4342:2: iv_ruleExpCS= ruleExpCS EOF
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
    // InternalCompleteOCL.g:4349:1: ruleExpCS returns [EObject current=null] : ( (this_PrefixedPrimaryExpCS_0= rulePrefixedPrimaryExpCS ( () ( (lv_name_2_0= ruleBinaryOperatorName ) ) ( (lv_ownedRight_3_0= ruleExpCS ) ) )? ) | this_PrefixedLetExpCS_4= rulePrefixedLetExpCS ) ;
    public final EObject ruleExpCS() throws RecognitionException {
        EObject current = null;

        EObject this_PrefixedPrimaryExpCS_0 = null;

        AntlrDatatypeRuleToken lv_name_2_0 = null;

        EObject lv_ownedRight_3_0 = null;

        EObject this_PrefixedLetExpCS_4 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:4352:28: ( ( (this_PrefixedPrimaryExpCS_0= rulePrefixedPrimaryExpCS ( () ( (lv_name_2_0= ruleBinaryOperatorName ) ) ( (lv_ownedRight_3_0= ruleExpCS ) ) )? ) | this_PrefixedLetExpCS_4= rulePrefixedLetExpCS ) )
            // InternalCompleteOCL.g:4353:1: ( (this_PrefixedPrimaryExpCS_0= rulePrefixedPrimaryExpCS ( () ( (lv_name_2_0= ruleBinaryOperatorName ) ) ( (lv_ownedRight_3_0= ruleExpCS ) ) )? ) | this_PrefixedLetExpCS_4= rulePrefixedLetExpCS )
            {
            // InternalCompleteOCL.g:4353:1: ( (this_PrefixedPrimaryExpCS_0= rulePrefixedPrimaryExpCS ( () ( (lv_name_2_0= ruleBinaryOperatorName ) ) ( (lv_ownedRight_3_0= ruleExpCS ) ) )? ) | this_PrefixedLetExpCS_4= rulePrefixedLetExpCS )
            int alt78=2;
            alt78 = dfa78.predict(input);
            switch (alt78) {
                case 1 :
                    // InternalCompleteOCL.g:4353:2: (this_PrefixedPrimaryExpCS_0= rulePrefixedPrimaryExpCS ( () ( (lv_name_2_0= ruleBinaryOperatorName ) ) ( (lv_ownedRight_3_0= ruleExpCS ) ) )? )
                    {
                    // InternalCompleteOCL.g:4353:2: (this_PrefixedPrimaryExpCS_0= rulePrefixedPrimaryExpCS ( () ( (lv_name_2_0= ruleBinaryOperatorName ) ) ( (lv_ownedRight_3_0= ruleExpCS ) ) )? )
                    // InternalCompleteOCL.g:4354:2: this_PrefixedPrimaryExpCS_0= rulePrefixedPrimaryExpCS ( () ( (lv_name_2_0= ruleBinaryOperatorName ) ) ( (lv_ownedRight_3_0= ruleExpCS ) ) )?
                    {
                    if ( state.backtracking==0 ) {

                      	  /* */

                    }
                    if ( state.backtracking==0 ) {

                              newCompositeNode(grammarAccess.getExpCSAccess().getPrefixedPrimaryExpCSParserRuleCall_0_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_50);
                    this_PrefixedPrimaryExpCS_0=rulePrefixedPrimaryExpCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_PrefixedPrimaryExpCS_0;
                              afterParserOrEnumRuleCall();

                    }
                    // InternalCompleteOCL.g:4365:1: ( () ( (lv_name_2_0= ruleBinaryOperatorName ) ) ( (lv_ownedRight_3_0= ruleExpCS ) ) )?
                    int alt77=2;
                    int LA77_0 = input.LA(1);

                    if ( ((LA77_0>=17 && LA77_0<=18)||LA77_0==27||(LA77_0>=39 && LA77_0<=40)||LA77_0==52||(LA77_0>=54 && LA77_0<=67)) ) {
                        alt77=1;
                    }
                    switch (alt77) {
                        case 1 :
                            // InternalCompleteOCL.g:4365:2: () ( (lv_name_2_0= ruleBinaryOperatorName ) ) ( (lv_ownedRight_3_0= ruleExpCS ) )
                            {
                            // InternalCompleteOCL.g:4365:2: ()
                            // InternalCompleteOCL.g:4366:2:
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

                            // InternalCompleteOCL.g:4374:2: ( (lv_name_2_0= ruleBinaryOperatorName ) )
                            // InternalCompleteOCL.g:4375:1: (lv_name_2_0= ruleBinaryOperatorName )
                            {
                            // InternalCompleteOCL.g:4375:1: (lv_name_2_0= ruleBinaryOperatorName )
                            // InternalCompleteOCL.g:4376:3: lv_name_2_0= ruleBinaryOperatorName
                            {
                            if ( state.backtracking==0 ) {

                              	        newCompositeNode(grammarAccess.getExpCSAccess().getNameBinaryOperatorNameParserRuleCall_0_1_1_0());

                            }
                            pushFollow(FollowSets000.FOLLOW_40);
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

                            // InternalCompleteOCL.g:4392:2: ( (lv_ownedRight_3_0= ruleExpCS ) )
                            // InternalCompleteOCL.g:4393:1: (lv_ownedRight_3_0= ruleExpCS )
                            {
                            // InternalCompleteOCL.g:4393:1: (lv_ownedRight_3_0= ruleExpCS )
                            // InternalCompleteOCL.g:4394:3: lv_ownedRight_3_0= ruleExpCS
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
                    // InternalCompleteOCL.g:4412:2: this_PrefixedLetExpCS_4= rulePrefixedLetExpCS
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
    // InternalCompleteOCL.g:4431:1: entryRulePrefixedLetExpCS returns [EObject current=null] : iv_rulePrefixedLetExpCS= rulePrefixedLetExpCS EOF ;
    public final EObject entryRulePrefixedLetExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePrefixedLetExpCS = null;


        try {
            // InternalCompleteOCL.g:4432:2: (iv_rulePrefixedLetExpCS= rulePrefixedLetExpCS EOF )
            // InternalCompleteOCL.g:4433:2: iv_rulePrefixedLetExpCS= rulePrefixedLetExpCS EOF
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
    // InternalCompleteOCL.g:4440:1: rulePrefixedLetExpCS returns [EObject current=null] : ( ( () ( (lv_name_1_0= ruleUnaryOperatorName ) ) ( (lv_ownedRight_2_0= rulePrefixedLetExpCS ) ) ) | this_LetExpCS_3= ruleLetExpCS ) ;
    public final EObject rulePrefixedLetExpCS() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_name_1_0 = null;

        EObject lv_ownedRight_2_0 = null;

        EObject this_LetExpCS_3 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:4443:28: ( ( ( () ( (lv_name_1_0= ruleUnaryOperatorName ) ) ( (lv_ownedRight_2_0= rulePrefixedLetExpCS ) ) ) | this_LetExpCS_3= ruleLetExpCS ) )
            // InternalCompleteOCL.g:4444:1: ( ( () ( (lv_name_1_0= ruleUnaryOperatorName ) ) ( (lv_ownedRight_2_0= rulePrefixedLetExpCS ) ) ) | this_LetExpCS_3= ruleLetExpCS )
            {
            // InternalCompleteOCL.g:4444:1: ( ( () ( (lv_name_1_0= ruleUnaryOperatorName ) ) ( (lv_ownedRight_2_0= rulePrefixedLetExpCS ) ) ) | this_LetExpCS_3= ruleLetExpCS )
            int alt79=2;
            int LA79_0 = input.LA(1);

            if ( ((LA79_0>=52 && LA79_0<=53)) ) {
                alt79=1;
            }
            else if ( (LA79_0==97) ) {
                alt79=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 79, 0, input);

                throw nvae;
            }
            switch (alt79) {
                case 1 :
                    // InternalCompleteOCL.g:4444:2: ( () ( (lv_name_1_0= ruleUnaryOperatorName ) ) ( (lv_ownedRight_2_0= rulePrefixedLetExpCS ) ) )
                    {
                    // InternalCompleteOCL.g:4444:2: ( () ( (lv_name_1_0= ruleUnaryOperatorName ) ) ( (lv_ownedRight_2_0= rulePrefixedLetExpCS ) ) )
                    // InternalCompleteOCL.g:4444:3: () ( (lv_name_1_0= ruleUnaryOperatorName ) ) ( (lv_ownedRight_2_0= rulePrefixedLetExpCS ) )
                    {
                    // InternalCompleteOCL.g:4444:3: ()
                    // InternalCompleteOCL.g:4445:2:
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

                    // InternalCompleteOCL.g:4453:2: ( (lv_name_1_0= ruleUnaryOperatorName ) )
                    // InternalCompleteOCL.g:4454:1: (lv_name_1_0= ruleUnaryOperatorName )
                    {
                    // InternalCompleteOCL.g:4454:1: (lv_name_1_0= ruleUnaryOperatorName )
                    // InternalCompleteOCL.g:4455:3: lv_name_1_0= ruleUnaryOperatorName
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getPrefixedLetExpCSAccess().getNameUnaryOperatorNameParserRuleCall_0_1_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_40);
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

                    // InternalCompleteOCL.g:4471:2: ( (lv_ownedRight_2_0= rulePrefixedLetExpCS ) )
                    // InternalCompleteOCL.g:4472:1: (lv_ownedRight_2_0= rulePrefixedLetExpCS )
                    {
                    // InternalCompleteOCL.g:4472:1: (lv_ownedRight_2_0= rulePrefixedLetExpCS )
                    // InternalCompleteOCL.g:4473:3: lv_ownedRight_2_0= rulePrefixedLetExpCS
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
                    // InternalCompleteOCL.g:4491:2: this_LetExpCS_3= ruleLetExpCS
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
    // InternalCompleteOCL.g:4510:1: entryRulePrefixedPrimaryExpCS returns [EObject current=null] : iv_rulePrefixedPrimaryExpCS= rulePrefixedPrimaryExpCS EOF ;
    public final EObject entryRulePrefixedPrimaryExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePrefixedPrimaryExpCS = null;


        try {
            // InternalCompleteOCL.g:4511:2: (iv_rulePrefixedPrimaryExpCS= rulePrefixedPrimaryExpCS EOF )
            // InternalCompleteOCL.g:4512:2: iv_rulePrefixedPrimaryExpCS= rulePrefixedPrimaryExpCS EOF
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
    // InternalCompleteOCL.g:4519:1: rulePrefixedPrimaryExpCS returns [EObject current=null] : ( ( () ( (lv_name_1_0= ruleUnaryOperatorName ) ) ( (lv_ownedRight_2_0= rulePrefixedPrimaryExpCS ) ) ) | this_PrimaryExpCS_3= rulePrimaryExpCS ) ;
    public final EObject rulePrefixedPrimaryExpCS() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_name_1_0 = null;

        EObject lv_ownedRight_2_0 = null;

        EObject this_PrimaryExpCS_3 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:4522:28: ( ( ( () ( (lv_name_1_0= ruleUnaryOperatorName ) ) ( (lv_ownedRight_2_0= rulePrefixedPrimaryExpCS ) ) ) | this_PrimaryExpCS_3= rulePrimaryExpCS ) )
            // InternalCompleteOCL.g:4523:1: ( ( () ( (lv_name_1_0= ruleUnaryOperatorName ) ) ( (lv_ownedRight_2_0= rulePrefixedPrimaryExpCS ) ) ) | this_PrimaryExpCS_3= rulePrimaryExpCS )
            {
            // InternalCompleteOCL.g:4523:1: ( ( () ( (lv_name_1_0= ruleUnaryOperatorName ) ) ( (lv_ownedRight_2_0= rulePrefixedPrimaryExpCS ) ) ) | this_PrimaryExpCS_3= rulePrimaryExpCS )
            int alt80=2;
            int LA80_0 = input.LA(1);

            if ( ((LA80_0>=52 && LA80_0<=53)) ) {
                alt80=1;
            }
            else if ( ((LA80_0>=RULE_SIMPLE_ID && LA80_0<=RULE_SINGLE_QUOTED_STRING)||LA80_0==21||(LA80_0>=28 && LA80_0<=30)||(LA80_0>=42 && LA80_0<=51)||LA80_0==54||(LA80_0>=68 && LA80_0<=69)||(LA80_0>=71 && LA80_0<=75)||LA80_0==80||(LA80_0>=82 && LA80_0<=85)||LA80_0==92||LA80_0==98) ) {
                alt80=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 80, 0, input);

                throw nvae;
            }
            switch (alt80) {
                case 1 :
                    // InternalCompleteOCL.g:4523:2: ( () ( (lv_name_1_0= ruleUnaryOperatorName ) ) ( (lv_ownedRight_2_0= rulePrefixedPrimaryExpCS ) ) )
                    {
                    // InternalCompleteOCL.g:4523:2: ( () ( (lv_name_1_0= ruleUnaryOperatorName ) ) ( (lv_ownedRight_2_0= rulePrefixedPrimaryExpCS ) ) )
                    // InternalCompleteOCL.g:4523:3: () ( (lv_name_1_0= ruleUnaryOperatorName ) ) ( (lv_ownedRight_2_0= rulePrefixedPrimaryExpCS ) )
                    {
                    // InternalCompleteOCL.g:4523:3: ()
                    // InternalCompleteOCL.g:4524:2:
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

                    // InternalCompleteOCL.g:4532:2: ( (lv_name_1_0= ruleUnaryOperatorName ) )
                    // InternalCompleteOCL.g:4533:1: (lv_name_1_0= ruleUnaryOperatorName )
                    {
                    // InternalCompleteOCL.g:4533:1: (lv_name_1_0= ruleUnaryOperatorName )
                    // InternalCompleteOCL.g:4534:3: lv_name_1_0= ruleUnaryOperatorName
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getPrefixedPrimaryExpCSAccess().getNameUnaryOperatorNameParserRuleCall_0_1_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_51);
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

                    // InternalCompleteOCL.g:4550:2: ( (lv_ownedRight_2_0= rulePrefixedPrimaryExpCS ) )
                    // InternalCompleteOCL.g:4551:1: (lv_ownedRight_2_0= rulePrefixedPrimaryExpCS )
                    {
                    // InternalCompleteOCL.g:4551:1: (lv_ownedRight_2_0= rulePrefixedPrimaryExpCS )
                    // InternalCompleteOCL.g:4552:3: lv_ownedRight_2_0= rulePrefixedPrimaryExpCS
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
                    // InternalCompleteOCL.g:4570:2: this_PrimaryExpCS_3= rulePrimaryExpCS
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
    // InternalCompleteOCL.g:4589:1: entryRulePrimaryExpCS returns [EObject current=null] : iv_rulePrimaryExpCS= rulePrimaryExpCS EOF ;
    public final EObject entryRulePrimaryExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePrimaryExpCS = null;


        try {
            // InternalCompleteOCL.g:4590:2: (iv_rulePrimaryExpCS= rulePrimaryExpCS EOF )
            // InternalCompleteOCL.g:4591:2: iv_rulePrimaryExpCS= rulePrimaryExpCS EOF
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
    // InternalCompleteOCL.g:4598:1: rulePrimaryExpCS returns [EObject current=null] : (this_NestedExpCS_0= ruleNestedExpCS | this_IfExpCS_1= ruleIfExpCS | this_SelfExpCS_2= ruleSelfExpCS | this_PrimitiveLiteralExpCS_3= rulePrimitiveLiteralExpCS | this_TupleLiteralExpCS_4= ruleTupleLiteralExpCS | this_MapLiteralExpCS_5= ruleMapLiteralExpCS | this_CollectionLiteralExpCS_6= ruleCollectionLiteralExpCS | this_LambdaLiteralExpCS_7= ruleLambdaLiteralExpCS | this_TypeLiteralExpCS_8= ruleTypeLiteralExpCS | this_NameExpCS_9= ruleNameExpCS ) ;
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
            // InternalCompleteOCL.g:4601:28: ( (this_NestedExpCS_0= ruleNestedExpCS | this_IfExpCS_1= ruleIfExpCS | this_SelfExpCS_2= ruleSelfExpCS | this_PrimitiveLiteralExpCS_3= rulePrimitiveLiteralExpCS | this_TupleLiteralExpCS_4= ruleTupleLiteralExpCS | this_MapLiteralExpCS_5= ruleMapLiteralExpCS | this_CollectionLiteralExpCS_6= ruleCollectionLiteralExpCS | this_LambdaLiteralExpCS_7= ruleLambdaLiteralExpCS | this_TypeLiteralExpCS_8= ruleTypeLiteralExpCS | this_NameExpCS_9= ruleNameExpCS ) )
            // InternalCompleteOCL.g:4602:1: (this_NestedExpCS_0= ruleNestedExpCS | this_IfExpCS_1= ruleIfExpCS | this_SelfExpCS_2= ruleSelfExpCS | this_PrimitiveLiteralExpCS_3= rulePrimitiveLiteralExpCS | this_TupleLiteralExpCS_4= ruleTupleLiteralExpCS | this_MapLiteralExpCS_5= ruleMapLiteralExpCS | this_CollectionLiteralExpCS_6= ruleCollectionLiteralExpCS | this_LambdaLiteralExpCS_7= ruleLambdaLiteralExpCS | this_TypeLiteralExpCS_8= ruleTypeLiteralExpCS | this_NameExpCS_9= ruleNameExpCS )
            {
            // InternalCompleteOCL.g:4602:1: (this_NestedExpCS_0= ruleNestedExpCS | this_IfExpCS_1= ruleIfExpCS | this_SelfExpCS_2= ruleSelfExpCS | this_PrimitiveLiteralExpCS_3= rulePrimitiveLiteralExpCS | this_TupleLiteralExpCS_4= ruleTupleLiteralExpCS | this_MapLiteralExpCS_5= ruleMapLiteralExpCS | this_CollectionLiteralExpCS_6= ruleCollectionLiteralExpCS | this_LambdaLiteralExpCS_7= ruleLambdaLiteralExpCS | this_TypeLiteralExpCS_8= ruleTypeLiteralExpCS | this_NameExpCS_9= ruleNameExpCS )
            int alt81=10;
            alt81 = dfa81.predict(input);
            switch (alt81) {
                case 1 :
                    // InternalCompleteOCL.g:4603:2: this_NestedExpCS_0= ruleNestedExpCS
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
                    // InternalCompleteOCL.g:4616:2: this_IfExpCS_1= ruleIfExpCS
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
                    // InternalCompleteOCL.g:4629:2: this_SelfExpCS_2= ruleSelfExpCS
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
                    // InternalCompleteOCL.g:4642:2: this_PrimitiveLiteralExpCS_3= rulePrimitiveLiteralExpCS
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
                    // InternalCompleteOCL.g:4655:2: this_TupleLiteralExpCS_4= ruleTupleLiteralExpCS
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
                    // InternalCompleteOCL.g:4668:2: this_MapLiteralExpCS_5= ruleMapLiteralExpCS
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
                    // InternalCompleteOCL.g:4681:2: this_CollectionLiteralExpCS_6= ruleCollectionLiteralExpCS
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
                    // InternalCompleteOCL.g:4694:2: this_LambdaLiteralExpCS_7= ruleLambdaLiteralExpCS
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
                    // InternalCompleteOCL.g:4707:2: this_TypeLiteralExpCS_8= ruleTypeLiteralExpCS
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
                    // InternalCompleteOCL.g:4720:2: this_NameExpCS_9= ruleNameExpCS
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
    // InternalCompleteOCL.g:4739:1: entryRuleNameExpCS returns [EObject current=null] : iv_ruleNameExpCS= ruleNameExpCS EOF ;
    public final EObject entryRuleNameExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNameExpCS = null;


        try {
            // InternalCompleteOCL.g:4740:2: (iv_ruleNameExpCS= ruleNameExpCS EOF )
            // InternalCompleteOCL.g:4741:2: iv_ruleNameExpCS= ruleNameExpCS EOF
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
    // InternalCompleteOCL.g:4748:1: ruleNameExpCS returns [EObject current=null] : ( ( (lv_ownedPathName_0_0= rulePathNameCS ) ) ( (lv_ownedSquareBracketedClauses_1_0= ruleSquareBracketedClauseCS ) )* ( (lv_ownedRoundBracketedClause_2_0= ruleRoundBracketedClauseCS ) )? ( (lv_ownedCurlyBracketedClause_3_0= ruleCurlyBracketedClauseCS ) )? ( ( (lv_isPre_4_0= '@' ) ) otherlv_5= 'pre' )? ) ;
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
            // InternalCompleteOCL.g:4751:28: ( ( ( (lv_ownedPathName_0_0= rulePathNameCS ) ) ( (lv_ownedSquareBracketedClauses_1_0= ruleSquareBracketedClauseCS ) )* ( (lv_ownedRoundBracketedClause_2_0= ruleRoundBracketedClauseCS ) )? ( (lv_ownedCurlyBracketedClause_3_0= ruleCurlyBracketedClauseCS ) )? ( ( (lv_isPre_4_0= '@' ) ) otherlv_5= 'pre' )? ) )
            // InternalCompleteOCL.g:4752:1: ( ( (lv_ownedPathName_0_0= rulePathNameCS ) ) ( (lv_ownedSquareBracketedClauses_1_0= ruleSquareBracketedClauseCS ) )* ( (lv_ownedRoundBracketedClause_2_0= ruleRoundBracketedClauseCS ) )? ( (lv_ownedCurlyBracketedClause_3_0= ruleCurlyBracketedClauseCS ) )? ( ( (lv_isPre_4_0= '@' ) ) otherlv_5= 'pre' )? )
            {
            // InternalCompleteOCL.g:4752:1: ( ( (lv_ownedPathName_0_0= rulePathNameCS ) ) ( (lv_ownedSquareBracketedClauses_1_0= ruleSquareBracketedClauseCS ) )* ( (lv_ownedRoundBracketedClause_2_0= ruleRoundBracketedClauseCS ) )? ( (lv_ownedCurlyBracketedClause_3_0= ruleCurlyBracketedClauseCS ) )? ( ( (lv_isPre_4_0= '@' ) ) otherlv_5= 'pre' )? )
            // InternalCompleteOCL.g:4752:2: ( (lv_ownedPathName_0_0= rulePathNameCS ) ) ( (lv_ownedSquareBracketedClauses_1_0= ruleSquareBracketedClauseCS ) )* ( (lv_ownedRoundBracketedClause_2_0= ruleRoundBracketedClauseCS ) )? ( (lv_ownedCurlyBracketedClause_3_0= ruleCurlyBracketedClauseCS ) )? ( ( (lv_isPre_4_0= '@' ) ) otherlv_5= 'pre' )?
            {
            // InternalCompleteOCL.g:4752:2: ( (lv_ownedPathName_0_0= rulePathNameCS ) )
            // InternalCompleteOCL.g:4753:1: (lv_ownedPathName_0_0= rulePathNameCS )
            {
            // InternalCompleteOCL.g:4753:1: (lv_ownedPathName_0_0= rulePathNameCS )
            // InternalCompleteOCL.g:4754:3: lv_ownedPathName_0_0= rulePathNameCS
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getNameExpCSAccess().getOwnedPathNamePathNameCSParserRuleCall_0_0());

            }
            pushFollow(FollowSets000.FOLLOW_52);
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

            // InternalCompleteOCL.g:4770:2: ( (lv_ownedSquareBracketedClauses_1_0= ruleSquareBracketedClauseCS ) )*
            loop82:
            do {
                int alt82=2;
                int LA82_0 = input.LA(1);

                if ( (LA82_0==87) ) {
                    alt82=1;
                }


                switch (alt82) {
            	case 1 :
            	    // InternalCompleteOCL.g:4771:1: (lv_ownedSquareBracketedClauses_1_0= ruleSquareBracketedClauseCS )
            	    {
            	    // InternalCompleteOCL.g:4771:1: (lv_ownedSquareBracketedClauses_1_0= ruleSquareBracketedClauseCS )
            	    // InternalCompleteOCL.g:4772:3: lv_ownedSquareBracketedClauses_1_0= ruleSquareBracketedClauseCS
            	    {
            	    if ( state.backtracking==0 ) {

            	      	        newCompositeNode(grammarAccess.getNameExpCSAccess().getOwnedSquareBracketedClausesSquareBracketedClauseCSParserRuleCall_1_0());

            	    }
            	    pushFollow(FollowSets000.FOLLOW_52);
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
            	    break loop82;
                }
            } while (true);

            // InternalCompleteOCL.g:4788:3: ( (lv_ownedRoundBracketedClause_2_0= ruleRoundBracketedClauseCS ) )?
            int alt83=2;
            int LA83_0 = input.LA(1);

            if ( (LA83_0==21) ) {
                alt83=1;
            }
            switch (alt83) {
                case 1 :
                    // InternalCompleteOCL.g:4789:1: (lv_ownedRoundBracketedClause_2_0= ruleRoundBracketedClauseCS )
                    {
                    // InternalCompleteOCL.g:4789:1: (lv_ownedRoundBracketedClause_2_0= ruleRoundBracketedClauseCS )
                    // InternalCompleteOCL.g:4790:3: lv_ownedRoundBracketedClause_2_0= ruleRoundBracketedClauseCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getNameExpCSAccess().getOwnedRoundBracketedClauseRoundBracketedClauseCSParserRuleCall_2_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_53);
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

            // InternalCompleteOCL.g:4806:3: ( (lv_ownedCurlyBracketedClause_3_0= ruleCurlyBracketedClauseCS ) )?
            int alt84=2;
            int LA84_0 = input.LA(1);

            if ( (LA84_0==76) ) {
                alt84=1;
            }
            switch (alt84) {
                case 1 :
                    // InternalCompleteOCL.g:4807:1: (lv_ownedCurlyBracketedClause_3_0= ruleCurlyBracketedClauseCS )
                    {
                    // InternalCompleteOCL.g:4807:1: (lv_ownedCurlyBracketedClause_3_0= ruleCurlyBracketedClauseCS )
                    // InternalCompleteOCL.g:4808:3: lv_ownedCurlyBracketedClause_3_0= ruleCurlyBracketedClauseCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getNameExpCSAccess().getOwnedCurlyBracketedClauseCurlyBracketedClauseCSParserRuleCall_3_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_54);
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

            // InternalCompleteOCL.g:4824:3: ( ( (lv_isPre_4_0= '@' ) ) otherlv_5= 'pre' )?
            int alt85=2;
            int LA85_0 = input.LA(1);

            if ( (LA85_0==86) ) {
                alt85=1;
            }
            switch (alt85) {
                case 1 :
                    // InternalCompleteOCL.g:4824:4: ( (lv_isPre_4_0= '@' ) ) otherlv_5= 'pre'
                    {
                    // InternalCompleteOCL.g:4824:4: ( (lv_isPre_4_0= '@' ) )
                    // InternalCompleteOCL.g:4825:1: (lv_isPre_4_0= '@' )
                    {
                    // InternalCompleteOCL.g:4825:1: (lv_isPre_4_0= '@' )
                    // InternalCompleteOCL.g:4826:3: lv_isPre_4_0= '@'
                    {
                    lv_isPre_4_0=(Token)match(input,86,FollowSets000.FOLLOW_55); if (state.failed) return current;
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

                    otherlv_5=(Token)match(input,32,FollowSets000.FOLLOW_2); if (state.failed) return current;
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
    // InternalCompleteOCL.g:4851:1: entryRuleCurlyBracketedClauseCS returns [EObject current=null] : iv_ruleCurlyBracketedClauseCS= ruleCurlyBracketedClauseCS EOF ;
    public final EObject entryRuleCurlyBracketedClauseCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCurlyBracketedClauseCS = null;


        try {
            // InternalCompleteOCL.g:4852:2: (iv_ruleCurlyBracketedClauseCS= ruleCurlyBracketedClauseCS EOF )
            // InternalCompleteOCL.g:4853:2: iv_ruleCurlyBracketedClauseCS= ruleCurlyBracketedClauseCS EOF
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
    // InternalCompleteOCL.g:4860:1: ruleCurlyBracketedClauseCS returns [EObject current=null] : ( () otherlv_1= '{' ( ( (lv_ownedParts_2_0= ruleShadowPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleShadowPartCS ) ) )* )? otherlv_5= '}' ) ;
    public final EObject ruleCurlyBracketedClauseCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        EObject lv_ownedParts_2_0 = null;

        EObject lv_ownedParts_4_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:4863:28: ( ( () otherlv_1= '{' ( ( (lv_ownedParts_2_0= ruleShadowPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleShadowPartCS ) ) )* )? otherlv_5= '}' ) )
            // InternalCompleteOCL.g:4864:1: ( () otherlv_1= '{' ( ( (lv_ownedParts_2_0= ruleShadowPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleShadowPartCS ) ) )* )? otherlv_5= '}' )
            {
            // InternalCompleteOCL.g:4864:1: ( () otherlv_1= '{' ( ( (lv_ownedParts_2_0= ruleShadowPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleShadowPartCS ) ) )* )? otherlv_5= '}' )
            // InternalCompleteOCL.g:4864:2: () otherlv_1= '{' ( ( (lv_ownedParts_2_0= ruleShadowPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleShadowPartCS ) ) )* )? otherlv_5= '}'
            {
            // InternalCompleteOCL.g:4864:2: ()
            // InternalCompleteOCL.g:4865:2:
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

            otherlv_1=(Token)match(input,76,FollowSets000.FOLLOW_56); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getCurlyBracketedClauseCSAccess().getLeftCurlyBracketKeyword_1());

            }
            // InternalCompleteOCL.g:4877:1: ( ( (lv_ownedParts_2_0= ruleShadowPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleShadowPartCS ) ) )* )?
            int alt87=2;
            int LA87_0 = input.LA(1);

            if ( ((LA87_0>=RULE_SIMPLE_ID && LA87_0<=RULE_ESCAPED_ID)||LA87_0==RULE_SINGLE_QUOTED_STRING||(LA87_0>=28 && LA87_0<=30)) ) {
                alt87=1;
            }
            switch (alt87) {
                case 1 :
                    // InternalCompleteOCL.g:4877:2: ( (lv_ownedParts_2_0= ruleShadowPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleShadowPartCS ) ) )*
                    {
                    // InternalCompleteOCL.g:4877:2: ( (lv_ownedParts_2_0= ruleShadowPartCS ) )
                    // InternalCompleteOCL.g:4878:1: (lv_ownedParts_2_0= ruleShadowPartCS )
                    {
                    // InternalCompleteOCL.g:4878:1: (lv_ownedParts_2_0= ruleShadowPartCS )
                    // InternalCompleteOCL.g:4879:3: lv_ownedParts_2_0= ruleShadowPartCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getCurlyBracketedClauseCSAccess().getOwnedPartsShadowPartCSParserRuleCall_2_0_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_37);
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

                    // InternalCompleteOCL.g:4895:2: (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleShadowPartCS ) ) )*
                    loop86:
                    do {
                        int alt86=2;
                        int LA86_0 = input.LA(1);

                        if ( (LA86_0==26) ) {
                            alt86=1;
                        }


                        switch (alt86) {
                    	case 1 :
                    	    // InternalCompleteOCL.g:4895:4: otherlv_3= ',' ( (lv_ownedParts_4_0= ruleShadowPartCS ) )
                    	    {
                    	    otherlv_3=(Token)match(input,26,FollowSets000.FOLLOW_21); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_3, grammarAccess.getCurlyBracketedClauseCSAccess().getCommaKeyword_2_1_0());

                    	    }
                    	    // InternalCompleteOCL.g:4899:1: ( (lv_ownedParts_4_0= ruleShadowPartCS ) )
                    	    // InternalCompleteOCL.g:4900:1: (lv_ownedParts_4_0= ruleShadowPartCS )
                    	    {
                    	    // InternalCompleteOCL.g:4900:1: (lv_ownedParts_4_0= ruleShadowPartCS )
                    	    // InternalCompleteOCL.g:4901:3: lv_ownedParts_4_0= ruleShadowPartCS
                    	    {
                    	    if ( state.backtracking==0 ) {

                    	      	        newCompositeNode(grammarAccess.getCurlyBracketedClauseCSAccess().getOwnedPartsShadowPartCSParserRuleCall_2_1_1_0());

                    	    }
                    	    pushFollow(FollowSets000.FOLLOW_37);
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
                    	    break loop86;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_5=(Token)match(input,77,FollowSets000.FOLLOW_2); if (state.failed) return current;
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
    // InternalCompleteOCL.g:4929:1: entryRuleRoundBracketedClauseCS returns [EObject current=null] : iv_ruleRoundBracketedClauseCS= ruleRoundBracketedClauseCS EOF ;
    public final EObject entryRuleRoundBracketedClauseCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRoundBracketedClauseCS = null;


        try {
            // InternalCompleteOCL.g:4930:2: (iv_ruleRoundBracketedClauseCS= ruleRoundBracketedClauseCS EOF )
            // InternalCompleteOCL.g:4931:2: iv_ruleRoundBracketedClauseCS= ruleRoundBracketedClauseCS EOF
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
    // InternalCompleteOCL.g:4938:1: ruleRoundBracketedClauseCS returns [EObject current=null] : ( () otherlv_1= '(' ( ( (lv_ownedArguments_2_0= ruleNavigatingArgCS ) ) ( (lv_ownedArguments_3_0= ruleNavigatingCommaArgCS ) )* ( ( (lv_ownedArguments_4_0= ruleNavigatingSemiArgCS ) ) ( (lv_ownedArguments_5_0= ruleNavigatingCommaArgCS ) )* )? ( ( (lv_ownedArguments_6_0= ruleNavigatingBarArgCS ) ) ( (lv_ownedArguments_7_0= ruleNavigatingCommaArgCS ) )* )* )? otherlv_8= ')' ) ;
    public final EObject ruleRoundBracketedClauseCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_8=null;
        EObject lv_ownedArguments_2_0 = null;

        EObject lv_ownedArguments_3_0 = null;

        EObject lv_ownedArguments_4_0 = null;

        EObject lv_ownedArguments_5_0 = null;

        EObject lv_ownedArguments_6_0 = null;

        EObject lv_ownedArguments_7_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:4941:28: ( ( () otherlv_1= '(' ( ( (lv_ownedArguments_2_0= ruleNavigatingArgCS ) ) ( (lv_ownedArguments_3_0= ruleNavigatingCommaArgCS ) )* ( ( (lv_ownedArguments_4_0= ruleNavigatingSemiArgCS ) ) ( (lv_ownedArguments_5_0= ruleNavigatingCommaArgCS ) )* )? ( ( (lv_ownedArguments_6_0= ruleNavigatingBarArgCS ) ) ( (lv_ownedArguments_7_0= ruleNavigatingCommaArgCS ) )* )* )? otherlv_8= ')' ) )
            // InternalCompleteOCL.g:4942:1: ( () otherlv_1= '(' ( ( (lv_ownedArguments_2_0= ruleNavigatingArgCS ) ) ( (lv_ownedArguments_3_0= ruleNavigatingCommaArgCS ) )* ( ( (lv_ownedArguments_4_0= ruleNavigatingSemiArgCS ) ) ( (lv_ownedArguments_5_0= ruleNavigatingCommaArgCS ) )* )? ( ( (lv_ownedArguments_6_0= ruleNavigatingBarArgCS ) ) ( (lv_ownedArguments_7_0= ruleNavigatingCommaArgCS ) )* )* )? otherlv_8= ')' )
            {
            // InternalCompleteOCL.g:4942:1: ( () otherlv_1= '(' ( ( (lv_ownedArguments_2_0= ruleNavigatingArgCS ) ) ( (lv_ownedArguments_3_0= ruleNavigatingCommaArgCS ) )* ( ( (lv_ownedArguments_4_0= ruleNavigatingSemiArgCS ) ) ( (lv_ownedArguments_5_0= ruleNavigatingCommaArgCS ) )* )? ( ( (lv_ownedArguments_6_0= ruleNavigatingBarArgCS ) ) ( (lv_ownedArguments_7_0= ruleNavigatingCommaArgCS ) )* )* )? otherlv_8= ')' )
            // InternalCompleteOCL.g:4942:2: () otherlv_1= '(' ( ( (lv_ownedArguments_2_0= ruleNavigatingArgCS ) ) ( (lv_ownedArguments_3_0= ruleNavigatingCommaArgCS ) )* ( ( (lv_ownedArguments_4_0= ruleNavigatingSemiArgCS ) ) ( (lv_ownedArguments_5_0= ruleNavigatingCommaArgCS ) )* )? ( ( (lv_ownedArguments_6_0= ruleNavigatingBarArgCS ) ) ( (lv_ownedArguments_7_0= ruleNavigatingCommaArgCS ) )* )* )? otherlv_8= ')'
            {
            // InternalCompleteOCL.g:4942:2: ()
            // InternalCompleteOCL.g:4943:2:
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

            otherlv_1=(Token)match(input,21,FollowSets000.FOLLOW_57); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getRoundBracketedClauseCSAccess().getLeftParenthesisKeyword_1());

            }
            // InternalCompleteOCL.g:4955:1: ( ( (lv_ownedArguments_2_0= ruleNavigatingArgCS ) ) ( (lv_ownedArguments_3_0= ruleNavigatingCommaArgCS ) )* ( ( (lv_ownedArguments_4_0= ruleNavigatingSemiArgCS ) ) ( (lv_ownedArguments_5_0= ruleNavigatingCommaArgCS ) )* )? ( ( (lv_ownedArguments_6_0= ruleNavigatingBarArgCS ) ) ( (lv_ownedArguments_7_0= ruleNavigatingCommaArgCS ) )* )* )?
            int alt93=2;
            int LA93_0 = input.LA(1);

            if ( ((LA93_0>=RULE_SIMPLE_ID && LA93_0<=RULE_SINGLE_QUOTED_STRING)||LA93_0==21||LA93_0==23||(LA93_0>=28 && LA93_0<=30)||(LA93_0>=41 && LA93_0<=54)||(LA93_0>=68 && LA93_0<=69)||(LA93_0>=71 && LA93_0<=75)||LA93_0==80||(LA93_0>=82 && LA93_0<=85)||LA93_0==92||(LA93_0>=97 && LA93_0<=98)) ) {
                alt93=1;
            }
            switch (alt93) {
                case 1 :
                    // InternalCompleteOCL.g:4955:2: ( (lv_ownedArguments_2_0= ruleNavigatingArgCS ) ) ( (lv_ownedArguments_3_0= ruleNavigatingCommaArgCS ) )* ( ( (lv_ownedArguments_4_0= ruleNavigatingSemiArgCS ) ) ( (lv_ownedArguments_5_0= ruleNavigatingCommaArgCS ) )* )? ( ( (lv_ownedArguments_6_0= ruleNavigatingBarArgCS ) ) ( (lv_ownedArguments_7_0= ruleNavigatingCommaArgCS ) )* )*
                    {
                    // InternalCompleteOCL.g:4955:2: ( (lv_ownedArguments_2_0= ruleNavigatingArgCS ) )
                    // InternalCompleteOCL.g:4956:1: (lv_ownedArguments_2_0= ruleNavigatingArgCS )
                    {
                    // InternalCompleteOCL.g:4956:1: (lv_ownedArguments_2_0= ruleNavigatingArgCS )
                    // InternalCompleteOCL.g:4957:3: lv_ownedArguments_2_0= ruleNavigatingArgCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getRoundBracketedClauseCSAccess().getOwnedArgumentsNavigatingArgCSParserRuleCall_2_0_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_58);
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

                    // InternalCompleteOCL.g:4973:2: ( (lv_ownedArguments_3_0= ruleNavigatingCommaArgCS ) )*
                    loop88:
                    do {
                        int alt88=2;
                        int LA88_0 = input.LA(1);

                        if ( (LA88_0==26) ) {
                            alt88=1;
                        }


                        switch (alt88) {
                    	case 1 :
                    	    // InternalCompleteOCL.g:4974:1: (lv_ownedArguments_3_0= ruleNavigatingCommaArgCS )
                    	    {
                    	    // InternalCompleteOCL.g:4974:1: (lv_ownedArguments_3_0= ruleNavigatingCommaArgCS )
                    	    // InternalCompleteOCL.g:4975:3: lv_ownedArguments_3_0= ruleNavigatingCommaArgCS
                    	    {
                    	    if ( state.backtracking==0 ) {

                    	      	        newCompositeNode(grammarAccess.getRoundBracketedClauseCSAccess().getOwnedArgumentsNavigatingCommaArgCSParserRuleCall_2_1_0());

                    	    }
                    	    pushFollow(FollowSets000.FOLLOW_58);
                    	    lv_ownedArguments_3_0=ruleNavigatingCommaArgCS();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = createModelElementForParent(grammarAccess.getRoundBracketedClauseCSRule());
                    	      	        }
                    	             		add(
                    	             			current,
                    	             			"ownedArguments",
                    	              		lv_ownedArguments_3_0,
                    	              		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.NavigatingCommaArgCS");
                    	      	        afterParserOrEnumRuleCall();

                    	    }

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop88;
                        }
                    } while (true);

                    // InternalCompleteOCL.g:4991:3: ( ( (lv_ownedArguments_4_0= ruleNavigatingSemiArgCS ) ) ( (lv_ownedArguments_5_0= ruleNavigatingCommaArgCS ) )* )?
                    int alt90=2;
                    int LA90_0 = input.LA(1);

                    if ( (LA90_0==91) ) {
                        alt90=1;
                    }
                    switch (alt90) {
                        case 1 :
                            // InternalCompleteOCL.g:4991:4: ( (lv_ownedArguments_4_0= ruleNavigatingSemiArgCS ) ) ( (lv_ownedArguments_5_0= ruleNavigatingCommaArgCS ) )*
                            {
                            // InternalCompleteOCL.g:4991:4: ( (lv_ownedArguments_4_0= ruleNavigatingSemiArgCS ) )
                            // InternalCompleteOCL.g:4992:1: (lv_ownedArguments_4_0= ruleNavigatingSemiArgCS )
                            {
                            // InternalCompleteOCL.g:4992:1: (lv_ownedArguments_4_0= ruleNavigatingSemiArgCS )
                            // InternalCompleteOCL.g:4993:3: lv_ownedArguments_4_0= ruleNavigatingSemiArgCS
                            {
                            if ( state.backtracking==0 ) {

                              	        newCompositeNode(grammarAccess.getRoundBracketedClauseCSAccess().getOwnedArgumentsNavigatingSemiArgCSParserRuleCall_2_2_0_0());

                            }
                            pushFollow(FollowSets000.FOLLOW_59);
                            lv_ownedArguments_4_0=ruleNavigatingSemiArgCS();

                            state._fsp--;
                            if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElementForParent(grammarAccess.getRoundBracketedClauseCSRule());
                              	        }
                                     		add(
                                     			current,
                                     			"ownedArguments",
                                      		lv_ownedArguments_4_0,
                                      		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.NavigatingSemiArgCS");
                              	        afterParserOrEnumRuleCall();

                            }

                            }


                            }

                            // InternalCompleteOCL.g:5009:2: ( (lv_ownedArguments_5_0= ruleNavigatingCommaArgCS ) )*
                            loop89:
                            do {
                                int alt89=2;
                                int LA89_0 = input.LA(1);

                                if ( (LA89_0==26) ) {
                                    alt89=1;
                                }


                                switch (alt89) {
                            	case 1 :
                            	    // InternalCompleteOCL.g:5010:1: (lv_ownedArguments_5_0= ruleNavigatingCommaArgCS )
                            	    {
                            	    // InternalCompleteOCL.g:5010:1: (lv_ownedArguments_5_0= ruleNavigatingCommaArgCS )
                            	    // InternalCompleteOCL.g:5011:3: lv_ownedArguments_5_0= ruleNavigatingCommaArgCS
                            	    {
                            	    if ( state.backtracking==0 ) {

                            	      	        newCompositeNode(grammarAccess.getRoundBracketedClauseCSAccess().getOwnedArgumentsNavigatingCommaArgCSParserRuleCall_2_2_1_0());

                            	    }
                            	    pushFollow(FollowSets000.FOLLOW_59);
                            	    lv_ownedArguments_5_0=ruleNavigatingCommaArgCS();

                            	    state._fsp--;
                            	    if (state.failed) return current;
                            	    if ( state.backtracking==0 ) {

                            	      	        if (current==null) {
                            	      	            current = createModelElementForParent(grammarAccess.getRoundBracketedClauseCSRule());
                            	      	        }
                            	             		add(
                            	             			current,
                            	             			"ownedArguments",
                            	              		lv_ownedArguments_5_0,
                            	              		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.NavigatingCommaArgCS");
                            	      	        afterParserOrEnumRuleCall();

                            	    }

                            	    }


                            	    }
                            	    break;

                            	default :
                            	    break loop89;
                                }
                            } while (true);


                            }
                            break;

                    }

                    // InternalCompleteOCL.g:5027:5: ( ( (lv_ownedArguments_6_0= ruleNavigatingBarArgCS ) ) ( (lv_ownedArguments_7_0= ruleNavigatingCommaArgCS ) )* )*
                    loop92:
                    do {
                        int alt92=2;
                        int LA92_0 = input.LA(1);

                        if ( (LA92_0==90) ) {
                            alt92=1;
                        }


                        switch (alt92) {
                    	case 1 :
                    	    // InternalCompleteOCL.g:5027:6: ( (lv_ownedArguments_6_0= ruleNavigatingBarArgCS ) ) ( (lv_ownedArguments_7_0= ruleNavigatingCommaArgCS ) )*
                    	    {
                    	    // InternalCompleteOCL.g:5027:6: ( (lv_ownedArguments_6_0= ruleNavigatingBarArgCS ) )
                    	    // InternalCompleteOCL.g:5028:1: (lv_ownedArguments_6_0= ruleNavigatingBarArgCS )
                    	    {
                    	    // InternalCompleteOCL.g:5028:1: (lv_ownedArguments_6_0= ruleNavigatingBarArgCS )
                    	    // InternalCompleteOCL.g:5029:3: lv_ownedArguments_6_0= ruleNavigatingBarArgCS
                    	    {
                    	    if ( state.backtracking==0 ) {

                    	      	        newCompositeNode(grammarAccess.getRoundBracketedClauseCSAccess().getOwnedArgumentsNavigatingBarArgCSParserRuleCall_2_3_0_0());

                    	    }
                    	    pushFollow(FollowSets000.FOLLOW_59);
                    	    lv_ownedArguments_6_0=ruleNavigatingBarArgCS();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = createModelElementForParent(grammarAccess.getRoundBracketedClauseCSRule());
                    	      	        }
                    	             		add(
                    	             			current,
                    	             			"ownedArguments",
                    	              		lv_ownedArguments_6_0,
                    	              		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.NavigatingBarArgCS");
                    	      	        afterParserOrEnumRuleCall();

                    	    }

                    	    }


                    	    }

                    	    // InternalCompleteOCL.g:5045:2: ( (lv_ownedArguments_7_0= ruleNavigatingCommaArgCS ) )*
                    	    loop91:
                    	    do {
                    	        int alt91=2;
                    	        int LA91_0 = input.LA(1);

                    	        if ( (LA91_0==26) ) {
                    	            alt91=1;
                    	        }


                    	        switch (alt91) {
                    	    	case 1 :
                    	    	    // InternalCompleteOCL.g:5046:1: (lv_ownedArguments_7_0= ruleNavigatingCommaArgCS )
                    	    	    {
                    	    	    // InternalCompleteOCL.g:5046:1: (lv_ownedArguments_7_0= ruleNavigatingCommaArgCS )
                    	    	    // InternalCompleteOCL.g:5047:3: lv_ownedArguments_7_0= ruleNavigatingCommaArgCS
                    	    	    {
                    	    	    if ( state.backtracking==0 ) {

                    	    	      	        newCompositeNode(grammarAccess.getRoundBracketedClauseCSAccess().getOwnedArgumentsNavigatingCommaArgCSParserRuleCall_2_3_1_0());

                    	    	    }
                    	    	    pushFollow(FollowSets000.FOLLOW_59);
                    	    	    lv_ownedArguments_7_0=ruleNavigatingCommaArgCS();

                    	    	    state._fsp--;
                    	    	    if (state.failed) return current;
                    	    	    if ( state.backtracking==0 ) {

                    	    	      	        if (current==null) {
                    	    	      	            current = createModelElementForParent(grammarAccess.getRoundBracketedClauseCSRule());
                    	    	      	        }
                    	    	             		add(
                    	    	             			current,
                    	    	             			"ownedArguments",
                    	    	              		lv_ownedArguments_7_0,
                    	    	              		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.NavigatingCommaArgCS");
                    	    	      	        afterParserOrEnumRuleCall();

                    	    	    }

                    	    	    }


                    	    	    }
                    	    	    break;

                    	    	default :
                    	    	    break loop91;
                    	        }
                    	    } while (true);


                    	    }
                    	    break;

                    	default :
                    	    break loop92;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_8=(Token)match(input,22,FollowSets000.FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_8, grammarAccess.getRoundBracketedClauseCSAccess().getRightParenthesisKeyword_3());

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
    // InternalCompleteOCL.g:5075:1: entryRuleSquareBracketedClauseCS returns [EObject current=null] : iv_ruleSquareBracketedClauseCS= ruleSquareBracketedClauseCS EOF ;
    public final EObject entryRuleSquareBracketedClauseCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSquareBracketedClauseCS = null;


        try {
            // InternalCompleteOCL.g:5076:2: (iv_ruleSquareBracketedClauseCS= ruleSquareBracketedClauseCS EOF )
            // InternalCompleteOCL.g:5077:2: iv_ruleSquareBracketedClauseCS= ruleSquareBracketedClauseCS EOF
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
    // InternalCompleteOCL.g:5084:1: ruleSquareBracketedClauseCS returns [EObject current=null] : (otherlv_0= '[' ( (lv_ownedTerms_1_0= ruleExpCS ) ) (otherlv_2= ',' ( (lv_ownedTerms_3_0= ruleExpCS ) ) )* otherlv_4= ']' ) ;
    public final EObject ruleSquareBracketedClauseCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_ownedTerms_1_0 = null;

        EObject lv_ownedTerms_3_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:5087:28: ( (otherlv_0= '[' ( (lv_ownedTerms_1_0= ruleExpCS ) ) (otherlv_2= ',' ( (lv_ownedTerms_3_0= ruleExpCS ) ) )* otherlv_4= ']' ) )
            // InternalCompleteOCL.g:5088:1: (otherlv_0= '[' ( (lv_ownedTerms_1_0= ruleExpCS ) ) (otherlv_2= ',' ( (lv_ownedTerms_3_0= ruleExpCS ) ) )* otherlv_4= ']' )
            {
            // InternalCompleteOCL.g:5088:1: (otherlv_0= '[' ( (lv_ownedTerms_1_0= ruleExpCS ) ) (otherlv_2= ',' ( (lv_ownedTerms_3_0= ruleExpCS ) ) )* otherlv_4= ']' )
            // InternalCompleteOCL.g:5088:3: otherlv_0= '[' ( (lv_ownedTerms_1_0= ruleExpCS ) ) (otherlv_2= ',' ( (lv_ownedTerms_3_0= ruleExpCS ) ) )* otherlv_4= ']'
            {
            otherlv_0=(Token)match(input,87,FollowSets000.FOLLOW_40); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getSquareBracketedClauseCSAccess().getLeftSquareBracketKeyword_0());

            }
            // InternalCompleteOCL.g:5092:1: ( (lv_ownedTerms_1_0= ruleExpCS ) )
            // InternalCompleteOCL.g:5093:1: (lv_ownedTerms_1_0= ruleExpCS )
            {
            // InternalCompleteOCL.g:5093:1: (lv_ownedTerms_1_0= ruleExpCS )
            // InternalCompleteOCL.g:5094:3: lv_ownedTerms_1_0= ruleExpCS
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getSquareBracketedClauseCSAccess().getOwnedTermsExpCSParserRuleCall_1_0());

            }
            pushFollow(FollowSets000.FOLLOW_60);
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

            // InternalCompleteOCL.g:5110:2: (otherlv_2= ',' ( (lv_ownedTerms_3_0= ruleExpCS ) ) )*
            loop94:
            do {
                int alt94=2;
                int LA94_0 = input.LA(1);

                if ( (LA94_0==26) ) {
                    alt94=1;
                }


                switch (alt94) {
            	case 1 :
            	    // InternalCompleteOCL.g:5110:4: otherlv_2= ',' ( (lv_ownedTerms_3_0= ruleExpCS ) )
            	    {
            	    otherlv_2=(Token)match(input,26,FollowSets000.FOLLOW_40); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_2, grammarAccess.getSquareBracketedClauseCSAccess().getCommaKeyword_2_0());

            	    }
            	    // InternalCompleteOCL.g:5114:1: ( (lv_ownedTerms_3_0= ruleExpCS ) )
            	    // InternalCompleteOCL.g:5115:1: (lv_ownedTerms_3_0= ruleExpCS )
            	    {
            	    // InternalCompleteOCL.g:5115:1: (lv_ownedTerms_3_0= ruleExpCS )
            	    // InternalCompleteOCL.g:5116:3: lv_ownedTerms_3_0= ruleExpCS
            	    {
            	    if ( state.backtracking==0 ) {

            	      	        newCompositeNode(grammarAccess.getSquareBracketedClauseCSAccess().getOwnedTermsExpCSParserRuleCall_2_1_0());

            	    }
            	    pushFollow(FollowSets000.FOLLOW_60);
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
            	    break loop94;
                }
            } while (true);

            otherlv_4=(Token)match(input,88,FollowSets000.FOLLOW_2); if (state.failed) return current;
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
    // InternalCompleteOCL.g:5144:1: entryRuleNavigatingArgCS returns [EObject current=null] : iv_ruleNavigatingArgCS= ruleNavigatingArgCS EOF ;
    public final EObject entryRuleNavigatingArgCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNavigatingArgCS = null;


        try {
            // InternalCompleteOCL.g:5145:2: (iv_ruleNavigatingArgCS= ruleNavigatingArgCS EOF )
            // InternalCompleteOCL.g:5146:2: iv_ruleNavigatingArgCS= ruleNavigatingArgCS EOF
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
    // InternalCompleteOCL.g:5153:1: ruleNavigatingArgCS returns [EObject current=null] : ( ( ( (lv_ownedNameExpression_0_0= ruleNavigatingArgExpCS ) ) ( (otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) ) (otherlv_3= '=' ( (lv_ownedInitExpression_4_0= ruleExpCS ) ) )? ) | (otherlv_5= 'in' ( (lv_ownedInitExpression_6_0= ruleExpCS ) ) ) )? ) | (otherlv_7= ':' ( (lv_ownedType_8_0= ruleTypeExpCS ) ) ) ) ;
    public final EObject ruleNavigatingArgCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        EObject lv_ownedNameExpression_0_0 = null;

        EObject lv_ownedType_2_0 = null;

        EObject lv_ownedInitExpression_4_0 = null;

        EObject lv_ownedInitExpression_6_0 = null;

        EObject lv_ownedType_8_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:5156:28: ( ( ( ( (lv_ownedNameExpression_0_0= ruleNavigatingArgExpCS ) ) ( (otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) ) (otherlv_3= '=' ( (lv_ownedInitExpression_4_0= ruleExpCS ) ) )? ) | (otherlv_5= 'in' ( (lv_ownedInitExpression_6_0= ruleExpCS ) ) ) )? ) | (otherlv_7= ':' ( (lv_ownedType_8_0= ruleTypeExpCS ) ) ) ) )
            // InternalCompleteOCL.g:5157:1: ( ( ( (lv_ownedNameExpression_0_0= ruleNavigatingArgExpCS ) ) ( (otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) ) (otherlv_3= '=' ( (lv_ownedInitExpression_4_0= ruleExpCS ) ) )? ) | (otherlv_5= 'in' ( (lv_ownedInitExpression_6_0= ruleExpCS ) ) ) )? ) | (otherlv_7= ':' ( (lv_ownedType_8_0= ruleTypeExpCS ) ) ) )
            {
            // InternalCompleteOCL.g:5157:1: ( ( ( (lv_ownedNameExpression_0_0= ruleNavigatingArgExpCS ) ) ( (otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) ) (otherlv_3= '=' ( (lv_ownedInitExpression_4_0= ruleExpCS ) ) )? ) | (otherlv_5= 'in' ( (lv_ownedInitExpression_6_0= ruleExpCS ) ) ) )? ) | (otherlv_7= ':' ( (lv_ownedType_8_0= ruleTypeExpCS ) ) ) )
            int alt97=2;
            int LA97_0 = input.LA(1);

            if ( ((LA97_0>=RULE_SIMPLE_ID && LA97_0<=RULE_SINGLE_QUOTED_STRING)||LA97_0==21||(LA97_0>=28 && LA97_0<=30)||(LA97_0>=41 && LA97_0<=54)||(LA97_0>=68 && LA97_0<=69)||(LA97_0>=71 && LA97_0<=75)||LA97_0==80||(LA97_0>=82 && LA97_0<=85)||LA97_0==92||(LA97_0>=97 && LA97_0<=98)) ) {
                alt97=1;
            }
            else if ( (LA97_0==23) ) {
                alt97=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 97, 0, input);

                throw nvae;
            }
            switch (alt97) {
                case 1 :
                    // InternalCompleteOCL.g:5157:2: ( ( (lv_ownedNameExpression_0_0= ruleNavigatingArgExpCS ) ) ( (otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) ) (otherlv_3= '=' ( (lv_ownedInitExpression_4_0= ruleExpCS ) ) )? ) | (otherlv_5= 'in' ( (lv_ownedInitExpression_6_0= ruleExpCS ) ) ) )? )
                    {
                    // InternalCompleteOCL.g:5157:2: ( ( (lv_ownedNameExpression_0_0= ruleNavigatingArgExpCS ) ) ( (otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) ) (otherlv_3= '=' ( (lv_ownedInitExpression_4_0= ruleExpCS ) ) )? ) | (otherlv_5= 'in' ( (lv_ownedInitExpression_6_0= ruleExpCS ) ) ) )? )
                    // InternalCompleteOCL.g:5157:3: ( (lv_ownedNameExpression_0_0= ruleNavigatingArgExpCS ) ) ( (otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) ) (otherlv_3= '=' ( (lv_ownedInitExpression_4_0= ruleExpCS ) ) )? ) | (otherlv_5= 'in' ( (lv_ownedInitExpression_6_0= ruleExpCS ) ) ) )?
                    {
                    // InternalCompleteOCL.g:5157:3: ( (lv_ownedNameExpression_0_0= ruleNavigatingArgExpCS ) )
                    // InternalCompleteOCL.g:5158:1: (lv_ownedNameExpression_0_0= ruleNavigatingArgExpCS )
                    {
                    // InternalCompleteOCL.g:5158:1: (lv_ownedNameExpression_0_0= ruleNavigatingArgExpCS )
                    // InternalCompleteOCL.g:5159:3: lv_ownedNameExpression_0_0= ruleNavigatingArgExpCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getNavigatingArgCSAccess().getOwnedNameExpressionNavigatingArgExpCSParserRuleCall_0_0_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_61);
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

                    // InternalCompleteOCL.g:5175:2: ( (otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) ) (otherlv_3= '=' ( (lv_ownedInitExpression_4_0= ruleExpCS ) ) )? ) | (otherlv_5= 'in' ( (lv_ownedInitExpression_6_0= ruleExpCS ) ) ) )?
                    int alt96=3;
                    int LA96_0 = input.LA(1);

                    if ( (LA96_0==23) ) {
                        alt96=1;
                    }
                    else if ( (LA96_0==89) ) {
                        alt96=2;
                    }
                    switch (alt96) {
                        case 1 :
                            // InternalCompleteOCL.g:5175:3: (otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) ) (otherlv_3= '=' ( (lv_ownedInitExpression_4_0= ruleExpCS ) ) )? )
                            {
                            // InternalCompleteOCL.g:5175:3: (otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) ) (otherlv_3= '=' ( (lv_ownedInitExpression_4_0= ruleExpCS ) ) )? )
                            // InternalCompleteOCL.g:5175:5: otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) ) (otherlv_3= '=' ( (lv_ownedInitExpression_4_0= ruleExpCS ) ) )?
                            {
                            otherlv_1=(Token)match(input,23,FollowSets000.FOLLOW_20); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                  	newLeafNode(otherlv_1, grammarAccess.getNavigatingArgCSAccess().getColonKeyword_0_1_0_0());

                            }
                            // InternalCompleteOCL.g:5179:1: ( (lv_ownedType_2_0= ruleTypeExpCS ) )
                            // InternalCompleteOCL.g:5180:1: (lv_ownedType_2_0= ruleTypeExpCS )
                            {
                            // InternalCompleteOCL.g:5180:1: (lv_ownedType_2_0= ruleTypeExpCS )
                            // InternalCompleteOCL.g:5181:3: lv_ownedType_2_0= ruleTypeExpCS
                            {
                            if ( state.backtracking==0 ) {

                              	        newCompositeNode(grammarAccess.getNavigatingArgCSAccess().getOwnedTypeTypeExpCSParserRuleCall_0_1_0_1_0());

                            }
                            pushFollow(FollowSets000.FOLLOW_62);
                            lv_ownedType_2_0=ruleTypeExpCS();

                            state._fsp--;
                            if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElementForParent(grammarAccess.getNavigatingArgCSRule());
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

                            // InternalCompleteOCL.g:5197:2: (otherlv_3= '=' ( (lv_ownedInitExpression_4_0= ruleExpCS ) ) )?
                            int alt95=2;
                            int LA95_0 = input.LA(1);

                            if ( (LA95_0==27) ) {
                                alt95=1;
                            }
                            switch (alt95) {
                                case 1 :
                                    // InternalCompleteOCL.g:5197:4: otherlv_3= '=' ( (lv_ownedInitExpression_4_0= ruleExpCS ) )
                                    {
                                    otherlv_3=(Token)match(input,27,FollowSets000.FOLLOW_40); if (state.failed) return current;
                                    if ( state.backtracking==0 ) {

                                          	newLeafNode(otherlv_3, grammarAccess.getNavigatingArgCSAccess().getEqualsSignKeyword_0_1_0_2_0());

                                    }
                                    // InternalCompleteOCL.g:5201:1: ( (lv_ownedInitExpression_4_0= ruleExpCS ) )
                                    // InternalCompleteOCL.g:5202:1: (lv_ownedInitExpression_4_0= ruleExpCS )
                                    {
                                    // InternalCompleteOCL.g:5202:1: (lv_ownedInitExpression_4_0= ruleExpCS )
                                    // InternalCompleteOCL.g:5203:3: lv_ownedInitExpression_4_0= ruleExpCS
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
                            // InternalCompleteOCL.g:5220:6: (otherlv_5= 'in' ( (lv_ownedInitExpression_6_0= ruleExpCS ) ) )
                            {
                            // InternalCompleteOCL.g:5220:6: (otherlv_5= 'in' ( (lv_ownedInitExpression_6_0= ruleExpCS ) ) )
                            // InternalCompleteOCL.g:5220:8: otherlv_5= 'in' ( (lv_ownedInitExpression_6_0= ruleExpCS ) )
                            {
                            otherlv_5=(Token)match(input,89,FollowSets000.FOLLOW_40); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                  	newLeafNode(otherlv_5, grammarAccess.getNavigatingArgCSAccess().getInKeyword_0_1_1_0());

                            }
                            // InternalCompleteOCL.g:5224:1: ( (lv_ownedInitExpression_6_0= ruleExpCS ) )
                            // InternalCompleteOCL.g:5225:1: (lv_ownedInitExpression_6_0= ruleExpCS )
                            {
                            // InternalCompleteOCL.g:5225:1: (lv_ownedInitExpression_6_0= ruleExpCS )
                            // InternalCompleteOCL.g:5226:3: lv_ownedInitExpression_6_0= ruleExpCS
                            {
                            if ( state.backtracking==0 ) {

                              	        newCompositeNode(grammarAccess.getNavigatingArgCSAccess().getOwnedInitExpressionExpCSParserRuleCall_0_1_1_1_0());

                            }
                            pushFollow(FollowSets000.FOLLOW_2);
                            lv_ownedInitExpression_6_0=ruleExpCS();

                            state._fsp--;
                            if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElementForParent(grammarAccess.getNavigatingArgCSRule());
                              	        }
                                     		set(
                                     			current,
                                     			"ownedInitExpression",
                                      		lv_ownedInitExpression_6_0,
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
                    // InternalCompleteOCL.g:5243:6: (otherlv_7= ':' ( (lv_ownedType_8_0= ruleTypeExpCS ) ) )
                    {
                    // InternalCompleteOCL.g:5243:6: (otherlv_7= ':' ( (lv_ownedType_8_0= ruleTypeExpCS ) ) )
                    // InternalCompleteOCL.g:5243:8: otherlv_7= ':' ( (lv_ownedType_8_0= ruleTypeExpCS ) )
                    {
                    otherlv_7=(Token)match(input,23,FollowSets000.FOLLOW_20); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_7, grammarAccess.getNavigatingArgCSAccess().getColonKeyword_1_0());

                    }
                    // InternalCompleteOCL.g:5247:1: ( (lv_ownedType_8_0= ruleTypeExpCS ) )
                    // InternalCompleteOCL.g:5248:1: (lv_ownedType_8_0= ruleTypeExpCS )
                    {
                    // InternalCompleteOCL.g:5248:1: (lv_ownedType_8_0= ruleTypeExpCS )
                    // InternalCompleteOCL.g:5249:3: lv_ownedType_8_0= ruleTypeExpCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getNavigatingArgCSAccess().getOwnedTypeTypeExpCSParserRuleCall_1_1_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    lv_ownedType_8_0=ruleTypeExpCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getNavigatingArgCSRule());
                      	        }
                             		set(
                             			current,
                             			"ownedType",
                              		lv_ownedType_8_0,
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
    // InternalCompleteOCL.g:5273:1: entryRuleNavigatingBarArgCS returns [EObject current=null] : iv_ruleNavigatingBarArgCS= ruleNavigatingBarArgCS EOF ;
    public final EObject entryRuleNavigatingBarArgCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNavigatingBarArgCS = null;


        try {
            // InternalCompleteOCL.g:5274:2: (iv_ruleNavigatingBarArgCS= ruleNavigatingBarArgCS EOF )
            // InternalCompleteOCL.g:5275:2: iv_ruleNavigatingBarArgCS= ruleNavigatingBarArgCS EOF
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
    // InternalCompleteOCL.g:5282:1: ruleNavigatingBarArgCS returns [EObject current=null] : ( ( (lv_prefix_0_0= '|' ) ) ( (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS ) ) (otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? )? ) ;
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
            // InternalCompleteOCL.g:5285:28: ( ( ( (lv_prefix_0_0= '|' ) ) ( (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS ) ) (otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? )? ) )
            // InternalCompleteOCL.g:5286:1: ( ( (lv_prefix_0_0= '|' ) ) ( (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS ) ) (otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? )? )
            {
            // InternalCompleteOCL.g:5286:1: ( ( (lv_prefix_0_0= '|' ) ) ( (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS ) ) (otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? )? )
            // InternalCompleteOCL.g:5286:2: ( (lv_prefix_0_0= '|' ) ) ( (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS ) ) (otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? )?
            {
            // InternalCompleteOCL.g:5286:2: ( (lv_prefix_0_0= '|' ) )
            // InternalCompleteOCL.g:5287:1: (lv_prefix_0_0= '|' )
            {
            // InternalCompleteOCL.g:5287:1: (lv_prefix_0_0= '|' )
            // InternalCompleteOCL.g:5288:3: lv_prefix_0_0= '|'
            {
            lv_prefix_0_0=(Token)match(input,90,FollowSets000.FOLLOW_63); if (state.failed) return current;
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

            // InternalCompleteOCL.g:5301:2: ( (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS ) )
            // InternalCompleteOCL.g:5302:1: (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS )
            {
            // InternalCompleteOCL.g:5302:1: (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS )
            // InternalCompleteOCL.g:5303:3: lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getNavigatingBarArgCSAccess().getOwnedNameExpressionNavigatingArgExpCSParserRuleCall_1_0());

            }
            pushFollow(FollowSets000.FOLLOW_64);
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

            // InternalCompleteOCL.g:5319:2: (otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? )?
            int alt99=2;
            int LA99_0 = input.LA(1);

            if ( (LA99_0==23) ) {
                alt99=1;
            }
            switch (alt99) {
                case 1 :
                    // InternalCompleteOCL.g:5319:4: otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )?
                    {
                    otherlv_2=(Token)match(input,23,FollowSets000.FOLLOW_20); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_2, grammarAccess.getNavigatingBarArgCSAccess().getColonKeyword_2_0());

                    }
                    // InternalCompleteOCL.g:5323:1: ( (lv_ownedType_3_0= ruleTypeExpCS ) )
                    // InternalCompleteOCL.g:5324:1: (lv_ownedType_3_0= ruleTypeExpCS )
                    {
                    // InternalCompleteOCL.g:5324:1: (lv_ownedType_3_0= ruleTypeExpCS )
                    // InternalCompleteOCL.g:5325:3: lv_ownedType_3_0= ruleTypeExpCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getNavigatingBarArgCSAccess().getOwnedTypeTypeExpCSParserRuleCall_2_1_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_62);
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

                    // InternalCompleteOCL.g:5341:2: (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )?
                    int alt98=2;
                    int LA98_0 = input.LA(1);

                    if ( (LA98_0==27) ) {
                        alt98=1;
                    }
                    switch (alt98) {
                        case 1 :
                            // InternalCompleteOCL.g:5341:4: otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) )
                            {
                            otherlv_4=(Token)match(input,27,FollowSets000.FOLLOW_40); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                  	newLeafNode(otherlv_4, grammarAccess.getNavigatingBarArgCSAccess().getEqualsSignKeyword_2_2_0());

                            }
                            // InternalCompleteOCL.g:5345:1: ( (lv_ownedInitExpression_5_0= ruleExpCS ) )
                            // InternalCompleteOCL.g:5346:1: (lv_ownedInitExpression_5_0= ruleExpCS )
                            {
                            // InternalCompleteOCL.g:5346:1: (lv_ownedInitExpression_5_0= ruleExpCS )
                            // InternalCompleteOCL.g:5347:3: lv_ownedInitExpression_5_0= ruleExpCS
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
    // InternalCompleteOCL.g:5371:1: entryRuleNavigatingCommaArgCS returns [EObject current=null] : iv_ruleNavigatingCommaArgCS= ruleNavigatingCommaArgCS EOF ;
    public final EObject entryRuleNavigatingCommaArgCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNavigatingCommaArgCS = null;


        try {
            // InternalCompleteOCL.g:5372:2: (iv_ruleNavigatingCommaArgCS= ruleNavigatingCommaArgCS EOF )
            // InternalCompleteOCL.g:5373:2: iv_ruleNavigatingCommaArgCS= ruleNavigatingCommaArgCS EOF
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
    // InternalCompleteOCL.g:5380:1: ruleNavigatingCommaArgCS returns [EObject current=null] : ( ( (lv_prefix_0_0= ',' ) ) ( (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS ) ) ( (otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? ) | (otherlv_6= 'in' ( (lv_ownedInitExpression_7_0= ruleExpCS ) ) ) )? ) ;
    public final EObject ruleNavigatingCommaArgCS() throws RecognitionException {
        EObject current = null;

        Token lv_prefix_0_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        EObject lv_ownedNameExpression_1_0 = null;

        EObject lv_ownedType_3_0 = null;

        EObject lv_ownedInitExpression_5_0 = null;

        EObject lv_ownedInitExpression_7_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:5383:28: ( ( ( (lv_prefix_0_0= ',' ) ) ( (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS ) ) ( (otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? ) | (otherlv_6= 'in' ( (lv_ownedInitExpression_7_0= ruleExpCS ) ) ) )? ) )
            // InternalCompleteOCL.g:5384:1: ( ( (lv_prefix_0_0= ',' ) ) ( (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS ) ) ( (otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? ) | (otherlv_6= 'in' ( (lv_ownedInitExpression_7_0= ruleExpCS ) ) ) )? )
            {
            // InternalCompleteOCL.g:5384:1: ( ( (lv_prefix_0_0= ',' ) ) ( (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS ) ) ( (otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? ) | (otherlv_6= 'in' ( (lv_ownedInitExpression_7_0= ruleExpCS ) ) ) )? )
            // InternalCompleteOCL.g:5384:2: ( (lv_prefix_0_0= ',' ) ) ( (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS ) ) ( (otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? ) | (otherlv_6= 'in' ( (lv_ownedInitExpression_7_0= ruleExpCS ) ) ) )?
            {
            // InternalCompleteOCL.g:5384:2: ( (lv_prefix_0_0= ',' ) )
            // InternalCompleteOCL.g:5385:1: (lv_prefix_0_0= ',' )
            {
            // InternalCompleteOCL.g:5385:1: (lv_prefix_0_0= ',' )
            // InternalCompleteOCL.g:5386:3: lv_prefix_0_0= ','
            {
            lv_prefix_0_0=(Token)match(input,26,FollowSets000.FOLLOW_63); if (state.failed) return current;
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

            // InternalCompleteOCL.g:5399:2: ( (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS ) )
            // InternalCompleteOCL.g:5400:1: (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS )
            {
            // InternalCompleteOCL.g:5400:1: (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS )
            // InternalCompleteOCL.g:5401:3: lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getNavigatingCommaArgCSAccess().getOwnedNameExpressionNavigatingArgExpCSParserRuleCall_1_0());

            }
            pushFollow(FollowSets000.FOLLOW_61);
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

            // InternalCompleteOCL.g:5417:2: ( (otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? ) | (otherlv_6= 'in' ( (lv_ownedInitExpression_7_0= ruleExpCS ) ) ) )?
            int alt101=3;
            int LA101_0 = input.LA(1);

            if ( (LA101_0==23) ) {
                alt101=1;
            }
            else if ( (LA101_0==89) ) {
                alt101=2;
            }
            switch (alt101) {
                case 1 :
                    // InternalCompleteOCL.g:5417:3: (otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? )
                    {
                    // InternalCompleteOCL.g:5417:3: (otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? )
                    // InternalCompleteOCL.g:5417:5: otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )?
                    {
                    otherlv_2=(Token)match(input,23,FollowSets000.FOLLOW_20); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_2, grammarAccess.getNavigatingCommaArgCSAccess().getColonKeyword_2_0_0());

                    }
                    // InternalCompleteOCL.g:5421:1: ( (lv_ownedType_3_0= ruleTypeExpCS ) )
                    // InternalCompleteOCL.g:5422:1: (lv_ownedType_3_0= ruleTypeExpCS )
                    {
                    // InternalCompleteOCL.g:5422:1: (lv_ownedType_3_0= ruleTypeExpCS )
                    // InternalCompleteOCL.g:5423:3: lv_ownedType_3_0= ruleTypeExpCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getNavigatingCommaArgCSAccess().getOwnedTypeTypeExpCSParserRuleCall_2_0_1_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_62);
                    lv_ownedType_3_0=ruleTypeExpCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getNavigatingCommaArgCSRule());
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

                    // InternalCompleteOCL.g:5439:2: (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )?
                    int alt100=2;
                    int LA100_0 = input.LA(1);

                    if ( (LA100_0==27) ) {
                        alt100=1;
                    }
                    switch (alt100) {
                        case 1 :
                            // InternalCompleteOCL.g:5439:4: otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) )
                            {
                            otherlv_4=(Token)match(input,27,FollowSets000.FOLLOW_40); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                  	newLeafNode(otherlv_4, grammarAccess.getNavigatingCommaArgCSAccess().getEqualsSignKeyword_2_0_2_0());

                            }
                            // InternalCompleteOCL.g:5443:1: ( (lv_ownedInitExpression_5_0= ruleExpCS ) )
                            // InternalCompleteOCL.g:5444:1: (lv_ownedInitExpression_5_0= ruleExpCS )
                            {
                            // InternalCompleteOCL.g:5444:1: (lv_ownedInitExpression_5_0= ruleExpCS )
                            // InternalCompleteOCL.g:5445:3: lv_ownedInitExpression_5_0= ruleExpCS
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
                    // InternalCompleteOCL.g:5462:6: (otherlv_6= 'in' ( (lv_ownedInitExpression_7_0= ruleExpCS ) ) )
                    {
                    // InternalCompleteOCL.g:5462:6: (otherlv_6= 'in' ( (lv_ownedInitExpression_7_0= ruleExpCS ) ) )
                    // InternalCompleteOCL.g:5462:8: otherlv_6= 'in' ( (lv_ownedInitExpression_7_0= ruleExpCS ) )
                    {
                    otherlv_6=(Token)match(input,89,FollowSets000.FOLLOW_40); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_6, grammarAccess.getNavigatingCommaArgCSAccess().getInKeyword_2_1_0());

                    }
                    // InternalCompleteOCL.g:5466:1: ( (lv_ownedInitExpression_7_0= ruleExpCS ) )
                    // InternalCompleteOCL.g:5467:1: (lv_ownedInitExpression_7_0= ruleExpCS )
                    {
                    // InternalCompleteOCL.g:5467:1: (lv_ownedInitExpression_7_0= ruleExpCS )
                    // InternalCompleteOCL.g:5468:3: lv_ownedInitExpression_7_0= ruleExpCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getNavigatingCommaArgCSAccess().getOwnedInitExpressionExpCSParserRuleCall_2_1_1_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    lv_ownedInitExpression_7_0=ruleExpCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getNavigatingCommaArgCSRule());
                      	        }
                             		set(
                             			current,
                             			"ownedInitExpression",
                              		lv_ownedInitExpression_7_0,
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
    // InternalCompleteOCL.g:5492:1: entryRuleNavigatingSemiArgCS returns [EObject current=null] : iv_ruleNavigatingSemiArgCS= ruleNavigatingSemiArgCS EOF ;
    public final EObject entryRuleNavigatingSemiArgCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNavigatingSemiArgCS = null;


        try {
            // InternalCompleteOCL.g:5493:2: (iv_ruleNavigatingSemiArgCS= ruleNavigatingSemiArgCS EOF )
            // InternalCompleteOCL.g:5494:2: iv_ruleNavigatingSemiArgCS= ruleNavigatingSemiArgCS EOF
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
    // InternalCompleteOCL.g:5501:1: ruleNavigatingSemiArgCS returns [EObject current=null] : ( ( (lv_prefix_0_0= ';' ) ) ( (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS ) ) (otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? )? ) ;
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
            // InternalCompleteOCL.g:5504:28: ( ( ( (lv_prefix_0_0= ';' ) ) ( (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS ) ) (otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? )? ) )
            // InternalCompleteOCL.g:5505:1: ( ( (lv_prefix_0_0= ';' ) ) ( (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS ) ) (otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? )? )
            {
            // InternalCompleteOCL.g:5505:1: ( ( (lv_prefix_0_0= ';' ) ) ( (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS ) ) (otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? )? )
            // InternalCompleteOCL.g:5505:2: ( (lv_prefix_0_0= ';' ) ) ( (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS ) ) (otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? )?
            {
            // InternalCompleteOCL.g:5505:2: ( (lv_prefix_0_0= ';' ) )
            // InternalCompleteOCL.g:5506:1: (lv_prefix_0_0= ';' )
            {
            // InternalCompleteOCL.g:5506:1: (lv_prefix_0_0= ';' )
            // InternalCompleteOCL.g:5507:3: lv_prefix_0_0= ';'
            {
            lv_prefix_0_0=(Token)match(input,91,FollowSets000.FOLLOW_63); if (state.failed) return current;
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

            // InternalCompleteOCL.g:5520:2: ( (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS ) )
            // InternalCompleteOCL.g:5521:1: (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS )
            {
            // InternalCompleteOCL.g:5521:1: (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS )
            // InternalCompleteOCL.g:5522:3: lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getNavigatingSemiArgCSAccess().getOwnedNameExpressionNavigatingArgExpCSParserRuleCall_1_0());

            }
            pushFollow(FollowSets000.FOLLOW_64);
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

            // InternalCompleteOCL.g:5538:2: (otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? )?
            int alt103=2;
            int LA103_0 = input.LA(1);

            if ( (LA103_0==23) ) {
                alt103=1;
            }
            switch (alt103) {
                case 1 :
                    // InternalCompleteOCL.g:5538:4: otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )?
                    {
                    otherlv_2=(Token)match(input,23,FollowSets000.FOLLOW_20); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_2, grammarAccess.getNavigatingSemiArgCSAccess().getColonKeyword_2_0());

                    }
                    // InternalCompleteOCL.g:5542:1: ( (lv_ownedType_3_0= ruleTypeExpCS ) )
                    // InternalCompleteOCL.g:5543:1: (lv_ownedType_3_0= ruleTypeExpCS )
                    {
                    // InternalCompleteOCL.g:5543:1: (lv_ownedType_3_0= ruleTypeExpCS )
                    // InternalCompleteOCL.g:5544:3: lv_ownedType_3_0= ruleTypeExpCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getNavigatingSemiArgCSAccess().getOwnedTypeTypeExpCSParserRuleCall_2_1_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_62);
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

                    // InternalCompleteOCL.g:5560:2: (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )?
                    int alt102=2;
                    int LA102_0 = input.LA(1);

                    if ( (LA102_0==27) ) {
                        alt102=1;
                    }
                    switch (alt102) {
                        case 1 :
                            // InternalCompleteOCL.g:5560:4: otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) )
                            {
                            otherlv_4=(Token)match(input,27,FollowSets000.FOLLOW_40); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                  	newLeafNode(otherlv_4, grammarAccess.getNavigatingSemiArgCSAccess().getEqualsSignKeyword_2_2_0());

                            }
                            // InternalCompleteOCL.g:5564:1: ( (lv_ownedInitExpression_5_0= ruleExpCS ) )
                            // InternalCompleteOCL.g:5565:1: (lv_ownedInitExpression_5_0= ruleExpCS )
                            {
                            // InternalCompleteOCL.g:5565:1: (lv_ownedInitExpression_5_0= ruleExpCS )
                            // InternalCompleteOCL.g:5566:3: lv_ownedInitExpression_5_0= ruleExpCS
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


    // $ANTLR start "entryRuleIfExpCS"
    // InternalCompleteOCL.g:5590:1: entryRuleIfExpCS returns [EObject current=null] : iv_ruleIfExpCS= ruleIfExpCS EOF ;
    public final EObject entryRuleIfExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIfExpCS = null;


        try {
            // InternalCompleteOCL.g:5591:2: (iv_ruleIfExpCS= ruleIfExpCS EOF )
            // InternalCompleteOCL.g:5592:2: iv_ruleIfExpCS= ruleIfExpCS EOF
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
    // InternalCompleteOCL.g:5599:1: ruleIfExpCS returns [EObject current=null] : (otherlv_0= 'if' ( ( (lv_ownedCondition_1_1= ruleExpCS | lv_ownedCondition_1_2= rulePatternExpCS ) ) ) otherlv_2= 'then' ( (lv_ownedThenExpression_3_0= ruleExpCS ) ) ( (lv_ownedIfThenExpressions_4_0= ruleElseIfThenExpCS ) )* otherlv_5= 'else' ( (lv_ownedElseExpression_6_0= ruleExpCS ) ) otherlv_7= 'endif' ) ;
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
            // InternalCompleteOCL.g:5602:28: ( (otherlv_0= 'if' ( ( (lv_ownedCondition_1_1= ruleExpCS | lv_ownedCondition_1_2= rulePatternExpCS ) ) ) otherlv_2= 'then' ( (lv_ownedThenExpression_3_0= ruleExpCS ) ) ( (lv_ownedIfThenExpressions_4_0= ruleElseIfThenExpCS ) )* otherlv_5= 'else' ( (lv_ownedElseExpression_6_0= ruleExpCS ) ) otherlv_7= 'endif' ) )
            // InternalCompleteOCL.g:5603:1: (otherlv_0= 'if' ( ( (lv_ownedCondition_1_1= ruleExpCS | lv_ownedCondition_1_2= rulePatternExpCS ) ) ) otherlv_2= 'then' ( (lv_ownedThenExpression_3_0= ruleExpCS ) ) ( (lv_ownedIfThenExpressions_4_0= ruleElseIfThenExpCS ) )* otherlv_5= 'else' ( (lv_ownedElseExpression_6_0= ruleExpCS ) ) otherlv_7= 'endif' )
            {
            // InternalCompleteOCL.g:5603:1: (otherlv_0= 'if' ( ( (lv_ownedCondition_1_1= ruleExpCS | lv_ownedCondition_1_2= rulePatternExpCS ) ) ) otherlv_2= 'then' ( (lv_ownedThenExpression_3_0= ruleExpCS ) ) ( (lv_ownedIfThenExpressions_4_0= ruleElseIfThenExpCS ) )* otherlv_5= 'else' ( (lv_ownedElseExpression_6_0= ruleExpCS ) ) otherlv_7= 'endif' )
            // InternalCompleteOCL.g:5603:3: otherlv_0= 'if' ( ( (lv_ownedCondition_1_1= ruleExpCS | lv_ownedCondition_1_2= rulePatternExpCS ) ) ) otherlv_2= 'then' ( (lv_ownedThenExpression_3_0= ruleExpCS ) ) ( (lv_ownedIfThenExpressions_4_0= ruleElseIfThenExpCS ) )* otherlv_5= 'else' ( (lv_ownedElseExpression_6_0= ruleExpCS ) ) otherlv_7= 'endif'
            {
            otherlv_0=(Token)match(input,92,FollowSets000.FOLLOW_38); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getIfExpCSAccess().getIfKeyword_0());

            }
            // InternalCompleteOCL.g:5607:1: ( ( (lv_ownedCondition_1_1= ruleExpCS | lv_ownedCondition_1_2= rulePatternExpCS ) ) )
            // InternalCompleteOCL.g:5608:1: ( (lv_ownedCondition_1_1= ruleExpCS | lv_ownedCondition_1_2= rulePatternExpCS ) )
            {
            // InternalCompleteOCL.g:5608:1: ( (lv_ownedCondition_1_1= ruleExpCS | lv_ownedCondition_1_2= rulePatternExpCS ) )
            // InternalCompleteOCL.g:5609:1: (lv_ownedCondition_1_1= ruleExpCS | lv_ownedCondition_1_2= rulePatternExpCS )
            {
            // InternalCompleteOCL.g:5609:1: (lv_ownedCondition_1_1= ruleExpCS | lv_ownedCondition_1_2= rulePatternExpCS )
            int alt104=2;
            switch ( input.LA(1) ) {
            case RULE_INT:
            case RULE_SINGLE_QUOTED_STRING:
            case 21:
            case 42:
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
            case 68:
            case 69:
            case 71:
            case 72:
            case 73:
            case 74:
            case 75:
            case 80:
            case 82:
            case 83:
            case 84:
            case 85:
            case 92:
            case 97:
            case 98:
                {
                alt104=1;
                }
                break;
            case RULE_SIMPLE_ID:
                {
                int LA104_2 = input.LA(2);

                if ( (LA104_2==23) ) {
                    alt104=2;
                }
                else if ( ((LA104_2>=17 && LA104_2<=18)||LA104_2==21||LA104_2==27||(LA104_2>=39 && LA104_2<=40)||LA104_2==52||(LA104_2>=54 && LA104_2<=67)||LA104_2==70||LA104_2==76||(LA104_2>=86 && LA104_2<=87)||LA104_2==93) ) {
                    alt104=1;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return current;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 104, 2, input);

                    throw nvae;
                }
                }
                break;
            case RULE_ESCAPED_ID:
                {
                int LA104_3 = input.LA(2);

                if ( ((LA104_3>=17 && LA104_3<=18)||LA104_3==21||LA104_3==27||(LA104_3>=39 && LA104_3<=40)||LA104_3==52||(LA104_3>=54 && LA104_3<=67)||LA104_3==70||LA104_3==76||(LA104_3>=86 && LA104_3<=87)||LA104_3==93) ) {
                    alt104=1;
                }
                else if ( (LA104_3==23) ) {
                    alt104=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return current;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 104, 3, input);

                    throw nvae;
                }
                }
                break;
            case 28:
                {
                int LA104_4 = input.LA(2);

                if ( ((LA104_4>=17 && LA104_4<=18)||LA104_4==21||LA104_4==27||(LA104_4>=39 && LA104_4<=40)||LA104_4==52||(LA104_4>=54 && LA104_4<=67)||LA104_4==70||LA104_4==76||(LA104_4>=86 && LA104_4<=87)||LA104_4==93) ) {
                    alt104=1;
                }
                else if ( (LA104_4==23) ) {
                    alt104=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return current;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 104, 4, input);

                    throw nvae;
                }
                }
                break;
            case 29:
                {
                int LA104_5 = input.LA(2);

                if ( (LA104_5==23) ) {
                    alt104=2;
                }
                else if ( ((LA104_5>=17 && LA104_5<=18)||LA104_5==21||LA104_5==27||(LA104_5>=39 && LA104_5<=40)||LA104_5==52||(LA104_5>=54 && LA104_5<=67)||LA104_5==70||LA104_5==76||(LA104_5>=86 && LA104_5<=87)||LA104_5==93) ) {
                    alt104=1;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return current;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 104, 5, input);

                    throw nvae;
                }
                }
                break;
            case 30:
                {
                int LA104_6 = input.LA(2);

                if ( (LA104_6==23) ) {
                    alt104=2;
                }
                else if ( ((LA104_6>=17 && LA104_6<=18)||LA104_6==21||LA104_6==27||(LA104_6>=39 && LA104_6<=40)||LA104_6==52||(LA104_6>=54 && LA104_6<=67)||LA104_6==70||LA104_6==76||(LA104_6>=86 && LA104_6<=87)||LA104_6==93) ) {
                    alt104=1;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return current;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 104, 6, input);

                    throw nvae;
                }
                }
                break;
            case 23:
                {
                alt104=2;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 104, 0, input);

                throw nvae;
            }

            switch (alt104) {
                case 1 :
                    // InternalCompleteOCL.g:5610:3: lv_ownedCondition_1_1= ruleExpCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getIfExpCSAccess().getOwnedConditionExpCSParserRuleCall_1_0_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_65);
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
                    // InternalCompleteOCL.g:5625:8: lv_ownedCondition_1_2= rulePatternExpCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getIfExpCSAccess().getOwnedConditionPatternExpCSParserRuleCall_1_0_1());

                    }
                    pushFollow(FollowSets000.FOLLOW_65);
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

            otherlv_2=(Token)match(input,93,FollowSets000.FOLLOW_40); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_2, grammarAccess.getIfExpCSAccess().getThenKeyword_2());

            }
            // InternalCompleteOCL.g:5647:1: ( (lv_ownedThenExpression_3_0= ruleExpCS ) )
            // InternalCompleteOCL.g:5648:1: (lv_ownedThenExpression_3_0= ruleExpCS )
            {
            // InternalCompleteOCL.g:5648:1: (lv_ownedThenExpression_3_0= ruleExpCS )
            // InternalCompleteOCL.g:5649:3: lv_ownedThenExpression_3_0= ruleExpCS
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getIfExpCSAccess().getOwnedThenExpressionExpCSParserRuleCall_3_0());

            }
            pushFollow(FollowSets000.FOLLOW_66);
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

            // InternalCompleteOCL.g:5665:2: ( (lv_ownedIfThenExpressions_4_0= ruleElseIfThenExpCS ) )*
            loop105:
            do {
                int alt105=2;
                int LA105_0 = input.LA(1);

                if ( (LA105_0==96) ) {
                    alt105=1;
                }


                switch (alt105) {
            	case 1 :
            	    // InternalCompleteOCL.g:5666:1: (lv_ownedIfThenExpressions_4_0= ruleElseIfThenExpCS )
            	    {
            	    // InternalCompleteOCL.g:5666:1: (lv_ownedIfThenExpressions_4_0= ruleElseIfThenExpCS )
            	    // InternalCompleteOCL.g:5667:3: lv_ownedIfThenExpressions_4_0= ruleElseIfThenExpCS
            	    {
            	    if ( state.backtracking==0 ) {

            	      	        newCompositeNode(grammarAccess.getIfExpCSAccess().getOwnedIfThenExpressionsElseIfThenExpCSParserRuleCall_4_0());

            	    }
            	    pushFollow(FollowSets000.FOLLOW_66);
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
            	    break loop105;
                }
            } while (true);

            otherlv_5=(Token)match(input,94,FollowSets000.FOLLOW_40); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_5, grammarAccess.getIfExpCSAccess().getElseKeyword_5());

            }
            // InternalCompleteOCL.g:5687:1: ( (lv_ownedElseExpression_6_0= ruleExpCS ) )
            // InternalCompleteOCL.g:5688:1: (lv_ownedElseExpression_6_0= ruleExpCS )
            {
            // InternalCompleteOCL.g:5688:1: (lv_ownedElseExpression_6_0= ruleExpCS )
            // InternalCompleteOCL.g:5689:3: lv_ownedElseExpression_6_0= ruleExpCS
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getIfExpCSAccess().getOwnedElseExpressionExpCSParserRuleCall_6_0());

            }
            pushFollow(FollowSets000.FOLLOW_67);
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

            otherlv_7=(Token)match(input,95,FollowSets000.FOLLOW_2); if (state.failed) return current;
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
    // InternalCompleteOCL.g:5717:1: entryRuleElseIfThenExpCS returns [EObject current=null] : iv_ruleElseIfThenExpCS= ruleElseIfThenExpCS EOF ;
    public final EObject entryRuleElseIfThenExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleElseIfThenExpCS = null;


        try {
            // InternalCompleteOCL.g:5718:2: (iv_ruleElseIfThenExpCS= ruleElseIfThenExpCS EOF )
            // InternalCompleteOCL.g:5719:2: iv_ruleElseIfThenExpCS= ruleElseIfThenExpCS EOF
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
    // InternalCompleteOCL.g:5726:1: ruleElseIfThenExpCS returns [EObject current=null] : (otherlv_0= 'elseif' ( (lv_ownedCondition_1_0= ruleExpCS ) ) otherlv_2= 'then' ( (lv_ownedThenExpression_3_0= ruleExpCS ) ) ) ;
    public final EObject ruleElseIfThenExpCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        EObject lv_ownedCondition_1_0 = null;

        EObject lv_ownedThenExpression_3_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:5729:28: ( (otherlv_0= 'elseif' ( (lv_ownedCondition_1_0= ruleExpCS ) ) otherlv_2= 'then' ( (lv_ownedThenExpression_3_0= ruleExpCS ) ) ) )
            // InternalCompleteOCL.g:5730:1: (otherlv_0= 'elseif' ( (lv_ownedCondition_1_0= ruleExpCS ) ) otherlv_2= 'then' ( (lv_ownedThenExpression_3_0= ruleExpCS ) ) )
            {
            // InternalCompleteOCL.g:5730:1: (otherlv_0= 'elseif' ( (lv_ownedCondition_1_0= ruleExpCS ) ) otherlv_2= 'then' ( (lv_ownedThenExpression_3_0= ruleExpCS ) ) )
            // InternalCompleteOCL.g:5730:3: otherlv_0= 'elseif' ( (lv_ownedCondition_1_0= ruleExpCS ) ) otherlv_2= 'then' ( (lv_ownedThenExpression_3_0= ruleExpCS ) )
            {
            otherlv_0=(Token)match(input,96,FollowSets000.FOLLOW_40); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getElseIfThenExpCSAccess().getElseifKeyword_0());

            }
            // InternalCompleteOCL.g:5734:1: ( (lv_ownedCondition_1_0= ruleExpCS ) )
            // InternalCompleteOCL.g:5735:1: (lv_ownedCondition_1_0= ruleExpCS )
            {
            // InternalCompleteOCL.g:5735:1: (lv_ownedCondition_1_0= ruleExpCS )
            // InternalCompleteOCL.g:5736:3: lv_ownedCondition_1_0= ruleExpCS
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getElseIfThenExpCSAccess().getOwnedConditionExpCSParserRuleCall_1_0());

            }
            pushFollow(FollowSets000.FOLLOW_65);
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

            otherlv_2=(Token)match(input,93,FollowSets000.FOLLOW_40); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_2, grammarAccess.getElseIfThenExpCSAccess().getThenKeyword_2());

            }
            // InternalCompleteOCL.g:5756:1: ( (lv_ownedThenExpression_3_0= ruleExpCS ) )
            // InternalCompleteOCL.g:5757:1: (lv_ownedThenExpression_3_0= ruleExpCS )
            {
            // InternalCompleteOCL.g:5757:1: (lv_ownedThenExpression_3_0= ruleExpCS )
            // InternalCompleteOCL.g:5758:3: lv_ownedThenExpression_3_0= ruleExpCS
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
    // InternalCompleteOCL.g:5782:1: entryRuleLetExpCS returns [EObject current=null] : iv_ruleLetExpCS= ruleLetExpCS EOF ;
    public final EObject entryRuleLetExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLetExpCS = null;


        try {
            // InternalCompleteOCL.g:5783:2: (iv_ruleLetExpCS= ruleLetExpCS EOF )
            // InternalCompleteOCL.g:5784:2: iv_ruleLetExpCS= ruleLetExpCS EOF
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
    // InternalCompleteOCL.g:5791:1: ruleLetExpCS returns [EObject current=null] : (otherlv_0= 'let' ( (lv_ownedVariables_1_0= ruleLetVariableCS ) ) (otherlv_2= ',' ( (lv_ownedVariables_3_0= ruleLetVariableCS ) ) )* otherlv_4= 'in' ( (lv_ownedInExpression_5_0= ruleExpCS ) ) ) ;
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
            // InternalCompleteOCL.g:5794:28: ( (otherlv_0= 'let' ( (lv_ownedVariables_1_0= ruleLetVariableCS ) ) (otherlv_2= ',' ( (lv_ownedVariables_3_0= ruleLetVariableCS ) ) )* otherlv_4= 'in' ( (lv_ownedInExpression_5_0= ruleExpCS ) ) ) )
            // InternalCompleteOCL.g:5795:1: (otherlv_0= 'let' ( (lv_ownedVariables_1_0= ruleLetVariableCS ) ) (otherlv_2= ',' ( (lv_ownedVariables_3_0= ruleLetVariableCS ) ) )* otherlv_4= 'in' ( (lv_ownedInExpression_5_0= ruleExpCS ) ) )
            {
            // InternalCompleteOCL.g:5795:1: (otherlv_0= 'let' ( (lv_ownedVariables_1_0= ruleLetVariableCS ) ) (otherlv_2= ',' ( (lv_ownedVariables_3_0= ruleLetVariableCS ) ) )* otherlv_4= 'in' ( (lv_ownedInExpression_5_0= ruleExpCS ) ) )
            // InternalCompleteOCL.g:5795:3: otherlv_0= 'let' ( (lv_ownedVariables_1_0= ruleLetVariableCS ) ) (otherlv_2= ',' ( (lv_ownedVariables_3_0= ruleLetVariableCS ) ) )* otherlv_4= 'in' ( (lv_ownedInExpression_5_0= ruleExpCS ) )
            {
            otherlv_0=(Token)match(input,97,FollowSets000.FOLLOW_14); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getLetExpCSAccess().getLetKeyword_0());

            }
            // InternalCompleteOCL.g:5799:1: ( (lv_ownedVariables_1_0= ruleLetVariableCS ) )
            // InternalCompleteOCL.g:5800:1: (lv_ownedVariables_1_0= ruleLetVariableCS )
            {
            // InternalCompleteOCL.g:5800:1: (lv_ownedVariables_1_0= ruleLetVariableCS )
            // InternalCompleteOCL.g:5801:3: lv_ownedVariables_1_0= ruleLetVariableCS
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getLetExpCSAccess().getOwnedVariablesLetVariableCSParserRuleCall_1_0());

            }
            pushFollow(FollowSets000.FOLLOW_68);
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

            // InternalCompleteOCL.g:5817:2: (otherlv_2= ',' ( (lv_ownedVariables_3_0= ruleLetVariableCS ) ) )*
            loop106:
            do {
                int alt106=2;
                int LA106_0 = input.LA(1);

                if ( (LA106_0==26) ) {
                    alt106=1;
                }


                switch (alt106) {
            	case 1 :
            	    // InternalCompleteOCL.g:5817:4: otherlv_2= ',' ( (lv_ownedVariables_3_0= ruleLetVariableCS ) )
            	    {
            	    otherlv_2=(Token)match(input,26,FollowSets000.FOLLOW_14); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_2, grammarAccess.getLetExpCSAccess().getCommaKeyword_2_0());

            	    }
            	    // InternalCompleteOCL.g:5821:1: ( (lv_ownedVariables_3_0= ruleLetVariableCS ) )
            	    // InternalCompleteOCL.g:5822:1: (lv_ownedVariables_3_0= ruleLetVariableCS )
            	    {
            	    // InternalCompleteOCL.g:5822:1: (lv_ownedVariables_3_0= ruleLetVariableCS )
            	    // InternalCompleteOCL.g:5823:3: lv_ownedVariables_3_0= ruleLetVariableCS
            	    {
            	    if ( state.backtracking==0 ) {

            	      	        newCompositeNode(grammarAccess.getLetExpCSAccess().getOwnedVariablesLetVariableCSParserRuleCall_2_1_0());

            	    }
            	    pushFollow(FollowSets000.FOLLOW_68);
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
            	    break loop106;
                }
            } while (true);

            otherlv_4=(Token)match(input,89,FollowSets000.FOLLOW_40); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_4, grammarAccess.getLetExpCSAccess().getInKeyword_3());

            }
            // InternalCompleteOCL.g:5843:1: ( (lv_ownedInExpression_5_0= ruleExpCS ) )
            // InternalCompleteOCL.g:5844:1: (lv_ownedInExpression_5_0= ruleExpCS )
            {
            // InternalCompleteOCL.g:5844:1: (lv_ownedInExpression_5_0= ruleExpCS )
            // InternalCompleteOCL.g:5845:3: lv_ownedInExpression_5_0= ruleExpCS
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
    // InternalCompleteOCL.g:5869:1: entryRuleLetVariableCS returns [EObject current=null] : iv_ruleLetVariableCS= ruleLetVariableCS EOF ;
    public final EObject entryRuleLetVariableCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLetVariableCS = null;


        try {
            // InternalCompleteOCL.g:5870:2: (iv_ruleLetVariableCS= ruleLetVariableCS EOF )
            // InternalCompleteOCL.g:5871:2: iv_ruleLetVariableCS= ruleLetVariableCS EOF
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
    // InternalCompleteOCL.g:5878:1: ruleLetVariableCS returns [EObject current=null] : ( ( (lv_name_0_0= ruleUnrestrictedName ) ) ( (lv_ownedRoundBracketedClause_1_0= ruleRoundBracketedClauseCS ) )? (otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) )? otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) ) ;
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
            // InternalCompleteOCL.g:5881:28: ( ( ( (lv_name_0_0= ruleUnrestrictedName ) ) ( (lv_ownedRoundBracketedClause_1_0= ruleRoundBracketedClauseCS ) )? (otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) )? otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) ) )
            // InternalCompleteOCL.g:5882:1: ( ( (lv_name_0_0= ruleUnrestrictedName ) ) ( (lv_ownedRoundBracketedClause_1_0= ruleRoundBracketedClauseCS ) )? (otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) )? otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )
            {
            // InternalCompleteOCL.g:5882:1: ( ( (lv_name_0_0= ruleUnrestrictedName ) ) ( (lv_ownedRoundBracketedClause_1_0= ruleRoundBracketedClauseCS ) )? (otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) )? otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )
            // InternalCompleteOCL.g:5882:2: ( (lv_name_0_0= ruleUnrestrictedName ) ) ( (lv_ownedRoundBracketedClause_1_0= ruleRoundBracketedClauseCS ) )? (otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) )? otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) )
            {
            // InternalCompleteOCL.g:5882:2: ( (lv_name_0_0= ruleUnrestrictedName ) )
            // InternalCompleteOCL.g:5883:1: (lv_name_0_0= ruleUnrestrictedName )
            {
            // InternalCompleteOCL.g:5883:1: (lv_name_0_0= ruleUnrestrictedName )
            // InternalCompleteOCL.g:5884:3: lv_name_0_0= ruleUnrestrictedName
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getLetVariableCSAccess().getNameUnrestrictedNameParserRuleCall_0_0());

            }
            pushFollow(FollowSets000.FOLLOW_69);
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

            // InternalCompleteOCL.g:5900:2: ( (lv_ownedRoundBracketedClause_1_0= ruleRoundBracketedClauseCS ) )?
            int alt107=2;
            int LA107_0 = input.LA(1);

            if ( (LA107_0==21) ) {
                alt107=1;
            }
            switch (alt107) {
                case 1 :
                    // InternalCompleteOCL.g:5901:1: (lv_ownedRoundBracketedClause_1_0= ruleRoundBracketedClauseCS )
                    {
                    // InternalCompleteOCL.g:5901:1: (lv_ownedRoundBracketedClause_1_0= ruleRoundBracketedClauseCS )
                    // InternalCompleteOCL.g:5902:3: lv_ownedRoundBracketedClause_1_0= ruleRoundBracketedClauseCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getLetVariableCSAccess().getOwnedRoundBracketedClauseRoundBracketedClauseCSParserRuleCall_1_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_46);
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

            // InternalCompleteOCL.g:5918:3: (otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) )?
            int alt108=2;
            int LA108_0 = input.LA(1);

            if ( (LA108_0==23) ) {
                alt108=1;
            }
            switch (alt108) {
                case 1 :
                    // InternalCompleteOCL.g:5918:5: otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) )
                    {
                    otherlv_2=(Token)match(input,23,FollowSets000.FOLLOW_20); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_2, grammarAccess.getLetVariableCSAccess().getColonKeyword_2_0());

                    }
                    // InternalCompleteOCL.g:5922:1: ( (lv_ownedType_3_0= ruleTypeExpCS ) )
                    // InternalCompleteOCL.g:5923:1: (lv_ownedType_3_0= ruleTypeExpCS )
                    {
                    // InternalCompleteOCL.g:5923:1: (lv_ownedType_3_0= ruleTypeExpCS )
                    // InternalCompleteOCL.g:5924:3: lv_ownedType_3_0= ruleTypeExpCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getLetVariableCSAccess().getOwnedTypeTypeExpCSParserRuleCall_2_1_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_19);
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

            otherlv_4=(Token)match(input,27,FollowSets000.FOLLOW_40); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_4, grammarAccess.getLetVariableCSAccess().getEqualsSignKeyword_3());

            }
            // InternalCompleteOCL.g:5944:1: ( (lv_ownedInitExpression_5_0= ruleExpCS ) )
            // InternalCompleteOCL.g:5945:1: (lv_ownedInitExpression_5_0= ruleExpCS )
            {
            // InternalCompleteOCL.g:5945:1: (lv_ownedInitExpression_5_0= ruleExpCS )
            // InternalCompleteOCL.g:5946:3: lv_ownedInitExpression_5_0= ruleExpCS
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
    // InternalCompleteOCL.g:5970:1: entryRuleNestedExpCS returns [EObject current=null] : iv_ruleNestedExpCS= ruleNestedExpCS EOF ;
    public final EObject entryRuleNestedExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNestedExpCS = null;


        try {
            // InternalCompleteOCL.g:5971:2: (iv_ruleNestedExpCS= ruleNestedExpCS EOF )
            // InternalCompleteOCL.g:5972:2: iv_ruleNestedExpCS= ruleNestedExpCS EOF
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
    // InternalCompleteOCL.g:5979:1: ruleNestedExpCS returns [EObject current=null] : (otherlv_0= '(' ( (lv_ownedExpression_1_0= ruleExpCS ) ) otherlv_2= ')' ) ;
    public final EObject ruleNestedExpCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        EObject lv_ownedExpression_1_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:5982:28: ( (otherlv_0= '(' ( (lv_ownedExpression_1_0= ruleExpCS ) ) otherlv_2= ')' ) )
            // InternalCompleteOCL.g:5983:1: (otherlv_0= '(' ( (lv_ownedExpression_1_0= ruleExpCS ) ) otherlv_2= ')' )
            {
            // InternalCompleteOCL.g:5983:1: (otherlv_0= '(' ( (lv_ownedExpression_1_0= ruleExpCS ) ) otherlv_2= ')' )
            // InternalCompleteOCL.g:5983:3: otherlv_0= '(' ( (lv_ownedExpression_1_0= ruleExpCS ) ) otherlv_2= ')'
            {
            otherlv_0=(Token)match(input,21,FollowSets000.FOLLOW_40); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getNestedExpCSAccess().getLeftParenthesisKeyword_0());

            }
            // InternalCompleteOCL.g:5987:1: ( (lv_ownedExpression_1_0= ruleExpCS ) )
            // InternalCompleteOCL.g:5988:1: (lv_ownedExpression_1_0= ruleExpCS )
            {
            // InternalCompleteOCL.g:5988:1: (lv_ownedExpression_1_0= ruleExpCS )
            // InternalCompleteOCL.g:5989:3: lv_ownedExpression_1_0= ruleExpCS
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getNestedExpCSAccess().getOwnedExpressionExpCSParserRuleCall_1_0());

            }
            pushFollow(FollowSets000.FOLLOW_11);
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

            otherlv_2=(Token)match(input,22,FollowSets000.FOLLOW_2); if (state.failed) return current;
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
    // InternalCompleteOCL.g:6017:1: entryRuleSelfExpCS returns [EObject current=null] : iv_ruleSelfExpCS= ruleSelfExpCS EOF ;
    public final EObject entryRuleSelfExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSelfExpCS = null;


        try {
            // InternalCompleteOCL.g:6018:2: (iv_ruleSelfExpCS= ruleSelfExpCS EOF )
            // InternalCompleteOCL.g:6019:2: iv_ruleSelfExpCS= ruleSelfExpCS EOF
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
    // InternalCompleteOCL.g:6026:1: ruleSelfExpCS returns [EObject current=null] : ( () otherlv_1= 'self' ) ;
    public final EObject ruleSelfExpCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;

         enterRule();

        try {
            // InternalCompleteOCL.g:6029:28: ( ( () otherlv_1= 'self' ) )
            // InternalCompleteOCL.g:6030:1: ( () otherlv_1= 'self' )
            {
            // InternalCompleteOCL.g:6030:1: ( () otherlv_1= 'self' )
            // InternalCompleteOCL.g:6030:2: () otherlv_1= 'self'
            {
            // InternalCompleteOCL.g:6030:2: ()
            // InternalCompleteOCL.g:6031:2:
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

            otherlv_1=(Token)match(input,98,FollowSets000.FOLLOW_2); if (state.failed) return current;
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


    // $ANTLR start "entryRuleMultiplicityBoundsCS"
    // InternalCompleteOCL.g:6051:1: entryRuleMultiplicityBoundsCS returns [EObject current=null] : iv_ruleMultiplicityBoundsCS= ruleMultiplicityBoundsCS EOF ;
    public final EObject entryRuleMultiplicityBoundsCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMultiplicityBoundsCS = null;


        try {
            // InternalCompleteOCL.g:6052:2: (iv_ruleMultiplicityBoundsCS= ruleMultiplicityBoundsCS EOF )
            // InternalCompleteOCL.g:6053:2: iv_ruleMultiplicityBoundsCS= ruleMultiplicityBoundsCS EOF
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
    // InternalCompleteOCL.g:6060:1: ruleMultiplicityBoundsCS returns [EObject current=null] : ( ( (lv_lowerBound_0_0= ruleLOWER ) ) (otherlv_1= '..' ( (lv_upperBound_2_0= ruleUPPER ) ) )? ) ;
    public final EObject ruleMultiplicityBoundsCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_lowerBound_0_0 = null;

        AntlrDatatypeRuleToken lv_upperBound_2_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:6063:28: ( ( ( (lv_lowerBound_0_0= ruleLOWER ) ) (otherlv_1= '..' ( (lv_upperBound_2_0= ruleUPPER ) ) )? ) )
            // InternalCompleteOCL.g:6064:1: ( ( (lv_lowerBound_0_0= ruleLOWER ) ) (otherlv_1= '..' ( (lv_upperBound_2_0= ruleUPPER ) ) )? )
            {
            // InternalCompleteOCL.g:6064:1: ( ( (lv_lowerBound_0_0= ruleLOWER ) ) (otherlv_1= '..' ( (lv_upperBound_2_0= ruleUPPER ) ) )? )
            // InternalCompleteOCL.g:6064:2: ( (lv_lowerBound_0_0= ruleLOWER ) ) (otherlv_1= '..' ( (lv_upperBound_2_0= ruleUPPER ) ) )?
            {
            // InternalCompleteOCL.g:6064:2: ( (lv_lowerBound_0_0= ruleLOWER ) )
            // InternalCompleteOCL.g:6065:1: (lv_lowerBound_0_0= ruleLOWER )
            {
            // InternalCompleteOCL.g:6065:1: (lv_lowerBound_0_0= ruleLOWER )
            // InternalCompleteOCL.g:6066:3: lv_lowerBound_0_0= ruleLOWER
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getMultiplicityBoundsCSAccess().getLowerBoundLOWERParserRuleCall_0_0());

            }
            pushFollow(FollowSets000.FOLLOW_39);
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

            // InternalCompleteOCL.g:6082:2: (otherlv_1= '..' ( (lv_upperBound_2_0= ruleUPPER ) ) )?
            int alt109=2;
            int LA109_0 = input.LA(1);

            if ( (LA109_0==78) ) {
                alt109=1;
            }
            switch (alt109) {
                case 1 :
                    // InternalCompleteOCL.g:6082:4: otherlv_1= '..' ( (lv_upperBound_2_0= ruleUPPER ) )
                    {
                    otherlv_1=(Token)match(input,78,FollowSets000.FOLLOW_70); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_1, grammarAccess.getMultiplicityBoundsCSAccess().getFullStopFullStopKeyword_1_0());

                    }
                    // InternalCompleteOCL.g:6086:1: ( (lv_upperBound_2_0= ruleUPPER ) )
                    // InternalCompleteOCL.g:6087:1: (lv_upperBound_2_0= ruleUPPER )
                    {
                    // InternalCompleteOCL.g:6087:1: (lv_upperBound_2_0= ruleUPPER )
                    // InternalCompleteOCL.g:6088:3: lv_upperBound_2_0= ruleUPPER
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
    // InternalCompleteOCL.g:6112:1: entryRuleMultiplicityCS returns [EObject current=null] : iv_ruleMultiplicityCS= ruleMultiplicityCS EOF ;
    public final EObject entryRuleMultiplicityCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMultiplicityCS = null;


        try {
            // InternalCompleteOCL.g:6113:2: (iv_ruleMultiplicityCS= ruleMultiplicityCS EOF )
            // InternalCompleteOCL.g:6114:2: iv_ruleMultiplicityCS= ruleMultiplicityCS EOF
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
    // InternalCompleteOCL.g:6121:1: ruleMultiplicityCS returns [EObject current=null] : (otherlv_0= '[' (this_MultiplicityBoundsCS_1= ruleMultiplicityBoundsCS | this_MultiplicityStringCS_2= ruleMultiplicityStringCS ) (otherlv_3= '|?' | ( (lv_isNullFree_4_0= '|1' ) ) )? otherlv_5= ']' ) ;
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
            // InternalCompleteOCL.g:6124:28: ( (otherlv_0= '[' (this_MultiplicityBoundsCS_1= ruleMultiplicityBoundsCS | this_MultiplicityStringCS_2= ruleMultiplicityStringCS ) (otherlv_3= '|?' | ( (lv_isNullFree_4_0= '|1' ) ) )? otherlv_5= ']' ) )
            // InternalCompleteOCL.g:6125:1: (otherlv_0= '[' (this_MultiplicityBoundsCS_1= ruleMultiplicityBoundsCS | this_MultiplicityStringCS_2= ruleMultiplicityStringCS ) (otherlv_3= '|?' | ( (lv_isNullFree_4_0= '|1' ) ) )? otherlv_5= ']' )
            {
            // InternalCompleteOCL.g:6125:1: (otherlv_0= '[' (this_MultiplicityBoundsCS_1= ruleMultiplicityBoundsCS | this_MultiplicityStringCS_2= ruleMultiplicityStringCS ) (otherlv_3= '|?' | ( (lv_isNullFree_4_0= '|1' ) ) )? otherlv_5= ']' )
            // InternalCompleteOCL.g:6125:3: otherlv_0= '[' (this_MultiplicityBoundsCS_1= ruleMultiplicityBoundsCS | this_MultiplicityStringCS_2= ruleMultiplicityStringCS ) (otherlv_3= '|?' | ( (lv_isNullFree_4_0= '|1' ) ) )? otherlv_5= ']'
            {
            otherlv_0=(Token)match(input,87,FollowSets000.FOLLOW_71); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getMultiplicityCSAccess().getLeftSquareBracketKeyword_0());

            }
            // InternalCompleteOCL.g:6129:1: (this_MultiplicityBoundsCS_1= ruleMultiplicityBoundsCS | this_MultiplicityStringCS_2= ruleMultiplicityStringCS )
            int alt110=2;
            int LA110_0 = input.LA(1);

            if ( (LA110_0==RULE_INT) ) {
                alt110=1;
            }
            else if ( (LA110_0==41||LA110_0==54||LA110_0==56) ) {
                alt110=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 110, 0, input);

                throw nvae;
            }
            switch (alt110) {
                case 1 :
                    // InternalCompleteOCL.g:6130:2: this_MultiplicityBoundsCS_1= ruleMultiplicityBoundsCS
                    {
                    if ( state.backtracking==0 ) {

                      	  /* */

                    }
                    if ( state.backtracking==0 ) {

                              newCompositeNode(grammarAccess.getMultiplicityCSAccess().getMultiplicityBoundsCSParserRuleCall_1_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_72);
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
                    // InternalCompleteOCL.g:6143:2: this_MultiplicityStringCS_2= ruleMultiplicityStringCS
                    {
                    if ( state.backtracking==0 ) {

                      	  /* */

                    }
                    if ( state.backtracking==0 ) {

                              newCompositeNode(grammarAccess.getMultiplicityCSAccess().getMultiplicityStringCSParserRuleCall_1_1());

                    }
                    pushFollow(FollowSets000.FOLLOW_72);
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

            // InternalCompleteOCL.g:6154:2: (otherlv_3= '|?' | ( (lv_isNullFree_4_0= '|1' ) ) )?
            int alt111=3;
            int LA111_0 = input.LA(1);

            if ( (LA111_0==99) ) {
                alt111=1;
            }
            else if ( (LA111_0==100) ) {
                alt111=2;
            }
            switch (alt111) {
                case 1 :
                    // InternalCompleteOCL.g:6154:4: otherlv_3= '|?'
                    {
                    otherlv_3=(Token)match(input,99,FollowSets000.FOLLOW_73); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_3, grammarAccess.getMultiplicityCSAccess().getVerticalLineQuestionMarkKeyword_2_0());

                    }

                    }
                    break;
                case 2 :
                    // InternalCompleteOCL.g:6159:6: ( (lv_isNullFree_4_0= '|1' ) )
                    {
                    // InternalCompleteOCL.g:6159:6: ( (lv_isNullFree_4_0= '|1' ) )
                    // InternalCompleteOCL.g:6160:1: (lv_isNullFree_4_0= '|1' )
                    {
                    // InternalCompleteOCL.g:6160:1: (lv_isNullFree_4_0= '|1' )
                    // InternalCompleteOCL.g:6161:3: lv_isNullFree_4_0= '|1'
                    {
                    lv_isNullFree_4_0=(Token)match(input,100,FollowSets000.FOLLOW_73); if (state.failed) return current;
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

            otherlv_5=(Token)match(input,88,FollowSets000.FOLLOW_2); if (state.failed) return current;
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
    // InternalCompleteOCL.g:6186:1: entryRuleMultiplicityStringCS returns [EObject current=null] : iv_ruleMultiplicityStringCS= ruleMultiplicityStringCS EOF ;
    public final EObject entryRuleMultiplicityStringCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMultiplicityStringCS = null;


        try {
            // InternalCompleteOCL.g:6187:2: (iv_ruleMultiplicityStringCS= ruleMultiplicityStringCS EOF )
            // InternalCompleteOCL.g:6188:2: iv_ruleMultiplicityStringCS= ruleMultiplicityStringCS EOF
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
    // InternalCompleteOCL.g:6195:1: ruleMultiplicityStringCS returns [EObject current=null] : ( ( (lv_stringBounds_0_1= '*' | lv_stringBounds_0_2= '+' | lv_stringBounds_0_3= '?' ) ) ) ;
    public final EObject ruleMultiplicityStringCS() throws RecognitionException {
        EObject current = null;

        Token lv_stringBounds_0_1=null;
        Token lv_stringBounds_0_2=null;
        Token lv_stringBounds_0_3=null;

         enterRule();

        try {
            // InternalCompleteOCL.g:6198:28: ( ( ( (lv_stringBounds_0_1= '*' | lv_stringBounds_0_2= '+' | lv_stringBounds_0_3= '?' ) ) ) )
            // InternalCompleteOCL.g:6199:1: ( ( (lv_stringBounds_0_1= '*' | lv_stringBounds_0_2= '+' | lv_stringBounds_0_3= '?' ) ) )
            {
            // InternalCompleteOCL.g:6199:1: ( ( (lv_stringBounds_0_1= '*' | lv_stringBounds_0_2= '+' | lv_stringBounds_0_3= '?' ) ) )
            // InternalCompleteOCL.g:6200:1: ( (lv_stringBounds_0_1= '*' | lv_stringBounds_0_2= '+' | lv_stringBounds_0_3= '?' ) )
            {
            // InternalCompleteOCL.g:6200:1: ( (lv_stringBounds_0_1= '*' | lv_stringBounds_0_2= '+' | lv_stringBounds_0_3= '?' ) )
            // InternalCompleteOCL.g:6201:1: (lv_stringBounds_0_1= '*' | lv_stringBounds_0_2= '+' | lv_stringBounds_0_3= '?' )
            {
            // InternalCompleteOCL.g:6201:1: (lv_stringBounds_0_1= '*' | lv_stringBounds_0_2= '+' | lv_stringBounds_0_3= '?' )
            int alt112=3;
            switch ( input.LA(1) ) {
            case 54:
                {
                alt112=1;
                }
                break;
            case 56:
                {
                alt112=2;
                }
                break;
            case 41:
                {
                alt112=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 112, 0, input);

                throw nvae;
            }

            switch (alt112) {
                case 1 :
                    // InternalCompleteOCL.g:6202:3: lv_stringBounds_0_1= '*'
                    {
                    lv_stringBounds_0_1=(Token)match(input,54,FollowSets000.FOLLOW_2); if (state.failed) return current;
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
                    // InternalCompleteOCL.g:6214:8: lv_stringBounds_0_2= '+'
                    {
                    lv_stringBounds_0_2=(Token)match(input,56,FollowSets000.FOLLOW_2); if (state.failed) return current;
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
                    // InternalCompleteOCL.g:6226:8: lv_stringBounds_0_3= '?'
                    {
                    lv_stringBounds_0_3=(Token)match(input,41,FollowSets000.FOLLOW_2); if (state.failed) return current;
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
    // InternalCompleteOCL.g:6249:1: entryRulePathNameCS returns [EObject current=null] : iv_rulePathNameCS= rulePathNameCS EOF ;
    public final EObject entryRulePathNameCS() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePathNameCS = null;


        try {
            // InternalCompleteOCL.g:6250:2: (iv_rulePathNameCS= rulePathNameCS EOF )
            // InternalCompleteOCL.g:6251:2: iv_rulePathNameCS= rulePathNameCS EOF
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
    // InternalCompleteOCL.g:6258:1: rulePathNameCS returns [EObject current=null] : ( ( (lv_ownedPathElements_0_0= ruleFirstPathElementCS ) ) (otherlv_1= '::' ( (lv_ownedPathElements_2_0= ruleNextPathElementCS ) ) )* ) ;
    public final EObject rulePathNameCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject lv_ownedPathElements_0_0 = null;

        EObject lv_ownedPathElements_2_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:6261:28: ( ( ( (lv_ownedPathElements_0_0= ruleFirstPathElementCS ) ) (otherlv_1= '::' ( (lv_ownedPathElements_2_0= ruleNextPathElementCS ) ) )* ) )
            // InternalCompleteOCL.g:6262:1: ( ( (lv_ownedPathElements_0_0= ruleFirstPathElementCS ) ) (otherlv_1= '::' ( (lv_ownedPathElements_2_0= ruleNextPathElementCS ) ) )* )
            {
            // InternalCompleteOCL.g:6262:1: ( ( (lv_ownedPathElements_0_0= ruleFirstPathElementCS ) ) (otherlv_1= '::' ( (lv_ownedPathElements_2_0= ruleNextPathElementCS ) ) )* )
            // InternalCompleteOCL.g:6262:2: ( (lv_ownedPathElements_0_0= ruleFirstPathElementCS ) ) (otherlv_1= '::' ( (lv_ownedPathElements_2_0= ruleNextPathElementCS ) ) )*
            {
            // InternalCompleteOCL.g:6262:2: ( (lv_ownedPathElements_0_0= ruleFirstPathElementCS ) )
            // InternalCompleteOCL.g:6263:1: (lv_ownedPathElements_0_0= ruleFirstPathElementCS )
            {
            // InternalCompleteOCL.g:6263:1: (lv_ownedPathElements_0_0= ruleFirstPathElementCS )
            // InternalCompleteOCL.g:6264:3: lv_ownedPathElements_0_0= ruleFirstPathElementCS
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getPathNameCSAccess().getOwnedPathElementsFirstPathElementCSParserRuleCall_0_0());

            }
            pushFollow(FollowSets000.FOLLOW_30);
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

            // InternalCompleteOCL.g:6280:2: (otherlv_1= '::' ( (lv_ownedPathElements_2_0= ruleNextPathElementCS ) ) )*
            loop113:
            do {
                int alt113=2;
                int LA113_0 = input.LA(1);

                if ( (LA113_0==70) ) {
                    alt113=1;
                }


                switch (alt113) {
            	case 1 :
            	    // InternalCompleteOCL.g:6280:4: otherlv_1= '::' ( (lv_ownedPathElements_2_0= ruleNextPathElementCS ) )
            	    {
            	    otherlv_1=(Token)match(input,70,FollowSets000.FOLLOW_31); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_1, grammarAccess.getPathNameCSAccess().getColonColonKeyword_1_0());

            	    }
            	    // InternalCompleteOCL.g:6284:1: ( (lv_ownedPathElements_2_0= ruleNextPathElementCS ) )
            	    // InternalCompleteOCL.g:6285:1: (lv_ownedPathElements_2_0= ruleNextPathElementCS )
            	    {
            	    // InternalCompleteOCL.g:6285:1: (lv_ownedPathElements_2_0= ruleNextPathElementCS )
            	    // InternalCompleteOCL.g:6286:3: lv_ownedPathElements_2_0= ruleNextPathElementCS
            	    {
            	    if ( state.backtracking==0 ) {

            	      	        newCompositeNode(grammarAccess.getPathNameCSAccess().getOwnedPathElementsNextPathElementCSParserRuleCall_1_1_0());

            	    }
            	    pushFollow(FollowSets000.FOLLOW_30);
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
            	    break loop113;
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
    // InternalCompleteOCL.g:6310:1: entryRuleFirstPathElementCS returns [EObject current=null] : iv_ruleFirstPathElementCS= ruleFirstPathElementCS EOF ;
    public final EObject entryRuleFirstPathElementCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFirstPathElementCS = null;


        try {
            // InternalCompleteOCL.g:6311:2: (iv_ruleFirstPathElementCS= ruleFirstPathElementCS EOF )
            // InternalCompleteOCL.g:6312:2: iv_ruleFirstPathElementCS= ruleFirstPathElementCS EOF
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
    // InternalCompleteOCL.g:6319:1: ruleFirstPathElementCS returns [EObject current=null] : ( ( ruleUnrestrictedName ) ) ;
    public final EObject ruleFirstPathElementCS() throws RecognitionException {
        EObject current = null;

         enterRule();

        try {
            // InternalCompleteOCL.g:6322:28: ( ( ( ruleUnrestrictedName ) ) )
            // InternalCompleteOCL.g:6323:1: ( ( ruleUnrestrictedName ) )
            {
            // InternalCompleteOCL.g:6323:1: ( ( ruleUnrestrictedName ) )
            // InternalCompleteOCL.g:6324:1: ( ruleUnrestrictedName )
            {
            // InternalCompleteOCL.g:6324:1: ( ruleUnrestrictedName )
            // InternalCompleteOCL.g:6325:3: ruleUnrestrictedName
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
    // InternalCompleteOCL.g:6349:1: entryRuleNextPathElementCS returns [EObject current=null] : iv_ruleNextPathElementCS= ruleNextPathElementCS EOF ;
    public final EObject entryRuleNextPathElementCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNextPathElementCS = null;


        try {
            // InternalCompleteOCL.g:6350:2: (iv_ruleNextPathElementCS= ruleNextPathElementCS EOF )
            // InternalCompleteOCL.g:6351:2: iv_ruleNextPathElementCS= ruleNextPathElementCS EOF
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
    // InternalCompleteOCL.g:6358:1: ruleNextPathElementCS returns [EObject current=null] : ( ( ruleUnreservedName ) ) ;
    public final EObject ruleNextPathElementCS() throws RecognitionException {
        EObject current = null;

         enterRule();

        try {
            // InternalCompleteOCL.g:6361:28: ( ( ( ruleUnreservedName ) ) )
            // InternalCompleteOCL.g:6362:1: ( ( ruleUnreservedName ) )
            {
            // InternalCompleteOCL.g:6362:1: ( ( ruleUnreservedName ) )
            // InternalCompleteOCL.g:6363:1: ( ruleUnreservedName )
            {
            // InternalCompleteOCL.g:6363:1: ( ruleUnreservedName )
            // InternalCompleteOCL.g:6364:3: ruleUnreservedName
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
    // InternalCompleteOCL.g:6388:1: entryRuleTemplateBindingCS returns [EObject current=null] : iv_ruleTemplateBindingCS= ruleTemplateBindingCS EOF ;
    public final EObject entryRuleTemplateBindingCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTemplateBindingCS = null;


        try {
            // InternalCompleteOCL.g:6389:2: (iv_ruleTemplateBindingCS= ruleTemplateBindingCS EOF )
            // InternalCompleteOCL.g:6390:2: iv_ruleTemplateBindingCS= ruleTemplateBindingCS EOF
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
    // InternalCompleteOCL.g:6397:1: ruleTemplateBindingCS returns [EObject current=null] : ( ( (lv_ownedSubstitutions_0_0= ruleTemplateParameterSubstitutionCS ) ) (otherlv_1= ',' ( (lv_ownedSubstitutions_2_0= ruleTemplateParameterSubstitutionCS ) ) )* ( (lv_ownedMultiplicity_3_0= ruleMultiplicityCS ) )? ) ;
    public final EObject ruleTemplateBindingCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject lv_ownedSubstitutions_0_0 = null;

        EObject lv_ownedSubstitutions_2_0 = null;

        EObject lv_ownedMultiplicity_3_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:6400:28: ( ( ( (lv_ownedSubstitutions_0_0= ruleTemplateParameterSubstitutionCS ) ) (otherlv_1= ',' ( (lv_ownedSubstitutions_2_0= ruleTemplateParameterSubstitutionCS ) ) )* ( (lv_ownedMultiplicity_3_0= ruleMultiplicityCS ) )? ) )
            // InternalCompleteOCL.g:6401:1: ( ( (lv_ownedSubstitutions_0_0= ruleTemplateParameterSubstitutionCS ) ) (otherlv_1= ',' ( (lv_ownedSubstitutions_2_0= ruleTemplateParameterSubstitutionCS ) ) )* ( (lv_ownedMultiplicity_3_0= ruleMultiplicityCS ) )? )
            {
            // InternalCompleteOCL.g:6401:1: ( ( (lv_ownedSubstitutions_0_0= ruleTemplateParameterSubstitutionCS ) ) (otherlv_1= ',' ( (lv_ownedSubstitutions_2_0= ruleTemplateParameterSubstitutionCS ) ) )* ( (lv_ownedMultiplicity_3_0= ruleMultiplicityCS ) )? )
            // InternalCompleteOCL.g:6401:2: ( (lv_ownedSubstitutions_0_0= ruleTemplateParameterSubstitutionCS ) ) (otherlv_1= ',' ( (lv_ownedSubstitutions_2_0= ruleTemplateParameterSubstitutionCS ) ) )* ( (lv_ownedMultiplicity_3_0= ruleMultiplicityCS ) )?
            {
            // InternalCompleteOCL.g:6401:2: ( (lv_ownedSubstitutions_0_0= ruleTemplateParameterSubstitutionCS ) )
            // InternalCompleteOCL.g:6402:1: (lv_ownedSubstitutions_0_0= ruleTemplateParameterSubstitutionCS )
            {
            // InternalCompleteOCL.g:6402:1: (lv_ownedSubstitutions_0_0= ruleTemplateParameterSubstitutionCS )
            // InternalCompleteOCL.g:6403:3: lv_ownedSubstitutions_0_0= ruleTemplateParameterSubstitutionCS
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getTemplateBindingCSAccess().getOwnedSubstitutionsTemplateParameterSubstitutionCSParserRuleCall_0_0());

            }
            pushFollow(FollowSets000.FOLLOW_74);
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

            // InternalCompleteOCL.g:6419:2: (otherlv_1= ',' ( (lv_ownedSubstitutions_2_0= ruleTemplateParameterSubstitutionCS ) ) )*
            loop114:
            do {
                int alt114=2;
                int LA114_0 = input.LA(1);

                if ( (LA114_0==26) ) {
                    alt114=1;
                }


                switch (alt114) {
            	case 1 :
            	    // InternalCompleteOCL.g:6419:4: otherlv_1= ',' ( (lv_ownedSubstitutions_2_0= ruleTemplateParameterSubstitutionCS ) )
            	    {
            	    otherlv_1=(Token)match(input,26,FollowSets000.FOLLOW_75); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_1, grammarAccess.getTemplateBindingCSAccess().getCommaKeyword_1_0());

            	    }
            	    // InternalCompleteOCL.g:6423:1: ( (lv_ownedSubstitutions_2_0= ruleTemplateParameterSubstitutionCS ) )
            	    // InternalCompleteOCL.g:6424:1: (lv_ownedSubstitutions_2_0= ruleTemplateParameterSubstitutionCS )
            	    {
            	    // InternalCompleteOCL.g:6424:1: (lv_ownedSubstitutions_2_0= ruleTemplateParameterSubstitutionCS )
            	    // InternalCompleteOCL.g:6425:3: lv_ownedSubstitutions_2_0= ruleTemplateParameterSubstitutionCS
            	    {
            	    if ( state.backtracking==0 ) {

            	      	        newCompositeNode(grammarAccess.getTemplateBindingCSAccess().getOwnedSubstitutionsTemplateParameterSubstitutionCSParserRuleCall_1_1_0());

            	    }
            	    pushFollow(FollowSets000.FOLLOW_74);
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
            	    break loop114;
                }
            } while (true);

            // InternalCompleteOCL.g:6441:4: ( (lv_ownedMultiplicity_3_0= ruleMultiplicityCS ) )?
            int alt115=2;
            int LA115_0 = input.LA(1);

            if ( (LA115_0==87) ) {
                alt115=1;
            }
            switch (alt115) {
                case 1 :
                    // InternalCompleteOCL.g:6442:1: (lv_ownedMultiplicity_3_0= ruleMultiplicityCS )
                    {
                    // InternalCompleteOCL.g:6442:1: (lv_ownedMultiplicity_3_0= ruleMultiplicityCS )
                    // InternalCompleteOCL.g:6443:3: lv_ownedMultiplicity_3_0= ruleMultiplicityCS
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
    // InternalCompleteOCL.g:6467:1: entryRuleTemplateParameterSubstitutionCS returns [EObject current=null] : iv_ruleTemplateParameterSubstitutionCS= ruleTemplateParameterSubstitutionCS EOF ;
    public final EObject entryRuleTemplateParameterSubstitutionCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTemplateParameterSubstitutionCS = null;


        try {
            // InternalCompleteOCL.g:6468:2: (iv_ruleTemplateParameterSubstitutionCS= ruleTemplateParameterSubstitutionCS EOF )
            // InternalCompleteOCL.g:6469:2: iv_ruleTemplateParameterSubstitutionCS= ruleTemplateParameterSubstitutionCS EOF
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
    // InternalCompleteOCL.g:6476:1: ruleTemplateParameterSubstitutionCS returns [EObject current=null] : ( (lv_ownedActualParameter_0_0= ruleTypeRefCS ) ) ;
    public final EObject ruleTemplateParameterSubstitutionCS() throws RecognitionException {
        EObject current = null;

        EObject lv_ownedActualParameter_0_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:6479:28: ( ( (lv_ownedActualParameter_0_0= ruleTypeRefCS ) ) )
            // InternalCompleteOCL.g:6480:1: ( (lv_ownedActualParameter_0_0= ruleTypeRefCS ) )
            {
            // InternalCompleteOCL.g:6480:1: ( (lv_ownedActualParameter_0_0= ruleTypeRefCS ) )
            // InternalCompleteOCL.g:6481:1: (lv_ownedActualParameter_0_0= ruleTypeRefCS )
            {
            // InternalCompleteOCL.g:6481:1: (lv_ownedActualParameter_0_0= ruleTypeRefCS )
            // InternalCompleteOCL.g:6482:3: lv_ownedActualParameter_0_0= ruleTypeRefCS
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
    // InternalCompleteOCL.g:6506:1: entryRuleTypeParameterCS returns [EObject current=null] : iv_ruleTypeParameterCS= ruleTypeParameterCS EOF ;
    public final EObject entryRuleTypeParameterCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTypeParameterCS = null;


        try {
            // InternalCompleteOCL.g:6507:2: (iv_ruleTypeParameterCS= ruleTypeParameterCS EOF )
            // InternalCompleteOCL.g:6508:2: iv_ruleTypeParameterCS= ruleTypeParameterCS EOF
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
    // InternalCompleteOCL.g:6515:1: ruleTypeParameterCS returns [EObject current=null] : ( ( (lv_name_0_0= ruleUnrestrictedName ) ) (otherlv_1= 'extends' ( (lv_ownedExtends_2_0= ruleTypedRefCS ) ) (otherlv_3= '&&' ( (lv_ownedExtends_4_0= ruleTypedRefCS ) ) )* )? ) ;
    public final EObject ruleTypeParameterCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        AntlrDatatypeRuleToken lv_name_0_0 = null;

        EObject lv_ownedExtends_2_0 = null;

        EObject lv_ownedExtends_4_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:6518:28: ( ( ( (lv_name_0_0= ruleUnrestrictedName ) ) (otherlv_1= 'extends' ( (lv_ownedExtends_2_0= ruleTypedRefCS ) ) (otherlv_3= '&&' ( (lv_ownedExtends_4_0= ruleTypedRefCS ) ) )* )? ) )
            // InternalCompleteOCL.g:6519:1: ( ( (lv_name_0_0= ruleUnrestrictedName ) ) (otherlv_1= 'extends' ( (lv_ownedExtends_2_0= ruleTypedRefCS ) ) (otherlv_3= '&&' ( (lv_ownedExtends_4_0= ruleTypedRefCS ) ) )* )? )
            {
            // InternalCompleteOCL.g:6519:1: ( ( (lv_name_0_0= ruleUnrestrictedName ) ) (otherlv_1= 'extends' ( (lv_ownedExtends_2_0= ruleTypedRefCS ) ) (otherlv_3= '&&' ( (lv_ownedExtends_4_0= ruleTypedRefCS ) ) )* )? )
            // InternalCompleteOCL.g:6519:2: ( (lv_name_0_0= ruleUnrestrictedName ) ) (otherlv_1= 'extends' ( (lv_ownedExtends_2_0= ruleTypedRefCS ) ) (otherlv_3= '&&' ( (lv_ownedExtends_4_0= ruleTypedRefCS ) ) )* )?
            {
            // InternalCompleteOCL.g:6519:2: ( (lv_name_0_0= ruleUnrestrictedName ) )
            // InternalCompleteOCL.g:6520:1: (lv_name_0_0= ruleUnrestrictedName )
            {
            // InternalCompleteOCL.g:6520:1: (lv_name_0_0= ruleUnrestrictedName )
            // InternalCompleteOCL.g:6521:3: lv_name_0_0= ruleUnrestrictedName
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getTypeParameterCSAccess().getNameUnrestrictedNameParserRuleCall_0_0());

            }
            pushFollow(FollowSets000.FOLLOW_76);
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

            // InternalCompleteOCL.g:6537:2: (otherlv_1= 'extends' ( (lv_ownedExtends_2_0= ruleTypedRefCS ) ) (otherlv_3= '&&' ( (lv_ownedExtends_4_0= ruleTypedRefCS ) ) )* )?
            int alt117=2;
            int LA117_0 = input.LA(1);

            if ( (LA117_0==101) ) {
                alt117=1;
            }
            switch (alt117) {
                case 1 :
                    // InternalCompleteOCL.g:6537:4: otherlv_1= 'extends' ( (lv_ownedExtends_2_0= ruleTypedRefCS ) ) (otherlv_3= '&&' ( (lv_ownedExtends_4_0= ruleTypedRefCS ) ) )*
                    {
                    otherlv_1=(Token)match(input,101,FollowSets000.FOLLOW_20); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_1, grammarAccess.getTypeParameterCSAccess().getExtendsKeyword_1_0());

                    }
                    // InternalCompleteOCL.g:6541:1: ( (lv_ownedExtends_2_0= ruleTypedRefCS ) )
                    // InternalCompleteOCL.g:6542:1: (lv_ownedExtends_2_0= ruleTypedRefCS )
                    {
                    // InternalCompleteOCL.g:6542:1: (lv_ownedExtends_2_0= ruleTypedRefCS )
                    // InternalCompleteOCL.g:6543:3: lv_ownedExtends_2_0= ruleTypedRefCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getTypeParameterCSAccess().getOwnedExtendsTypedRefCSParserRuleCall_1_1_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_77);
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

                    // InternalCompleteOCL.g:6559:2: (otherlv_3= '&&' ( (lv_ownedExtends_4_0= ruleTypedRefCS ) ) )*
                    loop116:
                    do {
                        int alt116=2;
                        int LA116_0 = input.LA(1);

                        if ( (LA116_0==102) ) {
                            alt116=1;
                        }


                        switch (alt116) {
                    	case 1 :
                    	    // InternalCompleteOCL.g:6559:4: otherlv_3= '&&' ( (lv_ownedExtends_4_0= ruleTypedRefCS ) )
                    	    {
                    	    otherlv_3=(Token)match(input,102,FollowSets000.FOLLOW_20); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_3, grammarAccess.getTypeParameterCSAccess().getAmpersandAmpersandKeyword_1_2_0());

                    	    }
                    	    // InternalCompleteOCL.g:6563:1: ( (lv_ownedExtends_4_0= ruleTypedRefCS ) )
                    	    // InternalCompleteOCL.g:6564:1: (lv_ownedExtends_4_0= ruleTypedRefCS )
                    	    {
                    	    // InternalCompleteOCL.g:6564:1: (lv_ownedExtends_4_0= ruleTypedRefCS )
                    	    // InternalCompleteOCL.g:6565:3: lv_ownedExtends_4_0= ruleTypedRefCS
                    	    {
                    	    if ( state.backtracking==0 ) {

                    	      	        newCompositeNode(grammarAccess.getTypeParameterCSAccess().getOwnedExtendsTypedRefCSParserRuleCall_1_2_1_0());

                    	    }
                    	    pushFollow(FollowSets000.FOLLOW_77);
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
                    	    break loop116;
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
    // InternalCompleteOCL.g:6589:1: entryRuleTypeRefCS returns [EObject current=null] : iv_ruleTypeRefCS= ruleTypeRefCS EOF ;
    public final EObject entryRuleTypeRefCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTypeRefCS = null;


        try {
            // InternalCompleteOCL.g:6590:2: (iv_ruleTypeRefCS= ruleTypeRefCS EOF )
            // InternalCompleteOCL.g:6591:2: iv_ruleTypeRefCS= ruleTypeRefCS EOF
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
    // InternalCompleteOCL.g:6598:1: ruleTypeRefCS returns [EObject current=null] : (this_TypedRefCS_0= ruleTypedRefCS | this_WildcardTypeRefCS_1= ruleWildcardTypeRefCS ) ;
    public final EObject ruleTypeRefCS() throws RecognitionException {
        EObject current = null;

        EObject this_TypedRefCS_0 = null;

        EObject this_WildcardTypeRefCS_1 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:6601:28: ( (this_TypedRefCS_0= ruleTypedRefCS | this_WildcardTypeRefCS_1= ruleWildcardTypeRefCS ) )
            // InternalCompleteOCL.g:6602:1: (this_TypedRefCS_0= ruleTypedRefCS | this_WildcardTypeRefCS_1= ruleWildcardTypeRefCS )
            {
            // InternalCompleteOCL.g:6602:1: (this_TypedRefCS_0= ruleTypedRefCS | this_WildcardTypeRefCS_1= ruleWildcardTypeRefCS )
            int alt118=2;
            int LA118_0 = input.LA(1);

            if ( ((LA118_0>=RULE_SIMPLE_ID && LA118_0<=RULE_ESCAPED_ID)||(LA118_0>=28 && LA118_0<=30)||(LA118_0>=42 && LA118_0<=51)||(LA118_0>=68 && LA118_0<=69)||(LA118_0>=71 && LA118_0<=75)) ) {
                alt118=1;
            }
            else if ( (LA118_0==41) ) {
                alt118=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 118, 0, input);

                throw nvae;
            }
            switch (alt118) {
                case 1 :
                    // InternalCompleteOCL.g:6603:2: this_TypedRefCS_0= ruleTypedRefCS
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
                    // InternalCompleteOCL.g:6616:2: this_WildcardTypeRefCS_1= ruleWildcardTypeRefCS
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
    // InternalCompleteOCL.g:6635:1: entryRuleTypedTypeRefCS returns [EObject current=null] : iv_ruleTypedTypeRefCS= ruleTypedTypeRefCS EOF ;
    public final EObject entryRuleTypedTypeRefCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTypedTypeRefCS = null;


        try {
            // InternalCompleteOCL.g:6636:2: (iv_ruleTypedTypeRefCS= ruleTypedTypeRefCS EOF )
            // InternalCompleteOCL.g:6637:2: iv_ruleTypedTypeRefCS= ruleTypedTypeRefCS EOF
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
    // InternalCompleteOCL.g:6644:1: ruleTypedTypeRefCS returns [EObject current=null] : ( ( (lv_ownedPathName_0_0= rulePathNameCS ) ) (otherlv_1= '(' ( (lv_ownedBinding_2_0= ruleTemplateBindingCS ) ) otherlv_3= ')' )? ) ;
    public final EObject ruleTypedTypeRefCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_ownedPathName_0_0 = null;

        EObject lv_ownedBinding_2_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:6647:28: ( ( ( (lv_ownedPathName_0_0= rulePathNameCS ) ) (otherlv_1= '(' ( (lv_ownedBinding_2_0= ruleTemplateBindingCS ) ) otherlv_3= ')' )? ) )
            // InternalCompleteOCL.g:6648:1: ( ( (lv_ownedPathName_0_0= rulePathNameCS ) ) (otherlv_1= '(' ( (lv_ownedBinding_2_0= ruleTemplateBindingCS ) ) otherlv_3= ')' )? )
            {
            // InternalCompleteOCL.g:6648:1: ( ( (lv_ownedPathName_0_0= rulePathNameCS ) ) (otherlv_1= '(' ( (lv_ownedBinding_2_0= ruleTemplateBindingCS ) ) otherlv_3= ')' )? )
            // InternalCompleteOCL.g:6648:2: ( (lv_ownedPathName_0_0= rulePathNameCS ) ) (otherlv_1= '(' ( (lv_ownedBinding_2_0= ruleTemplateBindingCS ) ) otherlv_3= ')' )?
            {
            // InternalCompleteOCL.g:6648:2: ( (lv_ownedPathName_0_0= rulePathNameCS ) )
            // InternalCompleteOCL.g:6649:1: (lv_ownedPathName_0_0= rulePathNameCS )
            {
            // InternalCompleteOCL.g:6649:1: (lv_ownedPathName_0_0= rulePathNameCS )
            // InternalCompleteOCL.g:6650:3: lv_ownedPathName_0_0= rulePathNameCS
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getTypedTypeRefCSAccess().getOwnedPathNamePathNameCSParserRuleCall_0_0());

            }
            pushFollow(FollowSets000.FOLLOW_32);
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

            // InternalCompleteOCL.g:6666:2: (otherlv_1= '(' ( (lv_ownedBinding_2_0= ruleTemplateBindingCS ) ) otherlv_3= ')' )?
            int alt119=2;
            int LA119_0 = input.LA(1);

            if ( (LA119_0==21) ) {
                alt119=1;
            }
            switch (alt119) {
                case 1 :
                    // InternalCompleteOCL.g:6666:4: otherlv_1= '(' ( (lv_ownedBinding_2_0= ruleTemplateBindingCS ) ) otherlv_3= ')'
                    {
                    otherlv_1=(Token)match(input,21,FollowSets000.FOLLOW_75); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_1, grammarAccess.getTypedTypeRefCSAccess().getLeftParenthesisKeyword_1_0());

                    }
                    // InternalCompleteOCL.g:6670:1: ( (lv_ownedBinding_2_0= ruleTemplateBindingCS ) )
                    // InternalCompleteOCL.g:6671:1: (lv_ownedBinding_2_0= ruleTemplateBindingCS )
                    {
                    // InternalCompleteOCL.g:6671:1: (lv_ownedBinding_2_0= ruleTemplateBindingCS )
                    // InternalCompleteOCL.g:6672:3: lv_ownedBinding_2_0= ruleTemplateBindingCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getTypedTypeRefCSAccess().getOwnedBindingTemplateBindingCSParserRuleCall_1_1_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_11);
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

                    otherlv_3=(Token)match(input,22,FollowSets000.FOLLOW_2); if (state.failed) return current;
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
    // InternalCompleteOCL.g:6700:1: entryRuleWildcardTypeRefCS returns [EObject current=null] : iv_ruleWildcardTypeRefCS= ruleWildcardTypeRefCS EOF ;
    public final EObject entryRuleWildcardTypeRefCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleWildcardTypeRefCS = null;


        try {
            // InternalCompleteOCL.g:6701:2: (iv_ruleWildcardTypeRefCS= ruleWildcardTypeRefCS EOF )
            // InternalCompleteOCL.g:6702:2: iv_ruleWildcardTypeRefCS= ruleWildcardTypeRefCS EOF
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
    // InternalCompleteOCL.g:6709:1: ruleWildcardTypeRefCS returns [EObject current=null] : ( () otherlv_1= '?' (otherlv_2= 'extends' ( (lv_ownedExtends_3_0= ruleTypedRefCS ) ) )? ) ;
    public final EObject ruleWildcardTypeRefCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        EObject lv_ownedExtends_3_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:6712:28: ( ( () otherlv_1= '?' (otherlv_2= 'extends' ( (lv_ownedExtends_3_0= ruleTypedRefCS ) ) )? ) )
            // InternalCompleteOCL.g:6713:1: ( () otherlv_1= '?' (otherlv_2= 'extends' ( (lv_ownedExtends_3_0= ruleTypedRefCS ) ) )? )
            {
            // InternalCompleteOCL.g:6713:1: ( () otherlv_1= '?' (otherlv_2= 'extends' ( (lv_ownedExtends_3_0= ruleTypedRefCS ) ) )? )
            // InternalCompleteOCL.g:6713:2: () otherlv_1= '?' (otherlv_2= 'extends' ( (lv_ownedExtends_3_0= ruleTypedRefCS ) ) )?
            {
            // InternalCompleteOCL.g:6713:2: ()
            // InternalCompleteOCL.g:6714:2:
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

            otherlv_1=(Token)match(input,41,FollowSets000.FOLLOW_76); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getWildcardTypeRefCSAccess().getQuestionMarkKeyword_1());

            }
            // InternalCompleteOCL.g:6726:1: (otherlv_2= 'extends' ( (lv_ownedExtends_3_0= ruleTypedRefCS ) ) )?
            int alt120=2;
            int LA120_0 = input.LA(1);

            if ( (LA120_0==101) ) {
                alt120=1;
            }
            switch (alt120) {
                case 1 :
                    // InternalCompleteOCL.g:6726:3: otherlv_2= 'extends' ( (lv_ownedExtends_3_0= ruleTypedRefCS ) )
                    {
                    otherlv_2=(Token)match(input,101,FollowSets000.FOLLOW_20); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_2, grammarAccess.getWildcardTypeRefCSAccess().getExtendsKeyword_2_0());

                    }
                    // InternalCompleteOCL.g:6730:1: ( (lv_ownedExtends_3_0= ruleTypedRefCS ) )
                    // InternalCompleteOCL.g:6731:1: (lv_ownedExtends_3_0= ruleTypedRefCS )
                    {
                    // InternalCompleteOCL.g:6731:1: (lv_ownedExtends_3_0= ruleTypedRefCS )
                    // InternalCompleteOCL.g:6732:3: lv_ownedExtends_3_0= ruleTypedRefCS
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
    // InternalCompleteOCL.g:6756:1: entryRuleID returns [String current=null] : iv_ruleID= ruleID EOF ;
    public final String entryRuleID() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleID = null;


        try {
            // InternalCompleteOCL.g:6757:2: (iv_ruleID= ruleID EOF )
            // InternalCompleteOCL.g:6758:2: iv_ruleID= ruleID EOF
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
    // InternalCompleteOCL.g:6765:1: ruleID returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_SIMPLE_ID_0= RULE_SIMPLE_ID | this_ESCAPED_ID_1= RULE_ESCAPED_ID ) ;
    public final AntlrDatatypeRuleToken ruleID() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_SIMPLE_ID_0=null;
        Token this_ESCAPED_ID_1=null;

         enterRule();

        try {
            // InternalCompleteOCL.g:6768:28: ( (this_SIMPLE_ID_0= RULE_SIMPLE_ID | this_ESCAPED_ID_1= RULE_ESCAPED_ID ) )
            // InternalCompleteOCL.g:6769:1: (this_SIMPLE_ID_0= RULE_SIMPLE_ID | this_ESCAPED_ID_1= RULE_ESCAPED_ID )
            {
            // InternalCompleteOCL.g:6769:1: (this_SIMPLE_ID_0= RULE_SIMPLE_ID | this_ESCAPED_ID_1= RULE_ESCAPED_ID )
            int alt121=2;
            int LA121_0 = input.LA(1);

            if ( (LA121_0==RULE_SIMPLE_ID) ) {
                alt121=1;
            }
            else if ( (LA121_0==RULE_ESCAPED_ID) ) {
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
                    // InternalCompleteOCL.g:6769:6: this_SIMPLE_ID_0= RULE_SIMPLE_ID
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
                    // InternalCompleteOCL.g:6777:10: this_ESCAPED_ID_1= RULE_ESCAPED_ID
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
    // InternalCompleteOCL.g:6792:1: entryRuleIdentifier returns [String current=null] : iv_ruleIdentifier= ruleIdentifier EOF ;
    public final String entryRuleIdentifier() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleIdentifier = null;


        try {
            // InternalCompleteOCL.g:6793:2: (iv_ruleIdentifier= ruleIdentifier EOF )
            // InternalCompleteOCL.g:6794:2: iv_ruleIdentifier= ruleIdentifier EOF
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
    // InternalCompleteOCL.g:6801:1: ruleIdentifier returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_ID_0= ruleID ;
    public final AntlrDatatypeRuleToken ruleIdentifier() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        AntlrDatatypeRuleToken this_ID_0 = null;


         enterRule();

        try {
            // InternalCompleteOCL.g:6804:28: (this_ID_0= ruleID )
            // InternalCompleteOCL.g:6806:5: this_ID_0= ruleID
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
    // InternalCompleteOCL.g:6824:1: entryRuleLOWER returns [String current=null] : iv_ruleLOWER= ruleLOWER EOF ;
    public final String entryRuleLOWER() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleLOWER = null;


        try {
            // InternalCompleteOCL.g:6825:2: (iv_ruleLOWER= ruleLOWER EOF )
            // InternalCompleteOCL.g:6826:2: iv_ruleLOWER= ruleLOWER EOF
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
    // InternalCompleteOCL.g:6833:1: ruleLOWER returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_INT_0= RULE_INT ;
    public final AntlrDatatypeRuleToken ruleLOWER() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_INT_0=null;

         enterRule();

        try {
            // InternalCompleteOCL.g:6836:28: (this_INT_0= RULE_INT )
            // InternalCompleteOCL.g:6837:5: this_INT_0= RULE_INT
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
    // InternalCompleteOCL.g:6852:1: entryRuleNUMBER_LITERAL returns [String current=null] : iv_ruleNUMBER_LITERAL= ruleNUMBER_LITERAL EOF ;
    public final String entryRuleNUMBER_LITERAL() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleNUMBER_LITERAL = null;


        try {
            // InternalCompleteOCL.g:6853:2: (iv_ruleNUMBER_LITERAL= ruleNUMBER_LITERAL EOF )
            // InternalCompleteOCL.g:6854:2: iv_ruleNUMBER_LITERAL= ruleNUMBER_LITERAL EOF
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
    // InternalCompleteOCL.g:6861:1: ruleNUMBER_LITERAL returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_INT_0= RULE_INT ;
    public final AntlrDatatypeRuleToken ruleNUMBER_LITERAL() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_INT_0=null;

         enterRule();

        try {
            // InternalCompleteOCL.g:6864:28: (this_INT_0= RULE_INT )
            // InternalCompleteOCL.g:6865:5: this_INT_0= RULE_INT
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
    // InternalCompleteOCL.g:6880:1: entryRuleStringLiteral returns [String current=null] : iv_ruleStringLiteral= ruleStringLiteral EOF ;
    public final String entryRuleStringLiteral() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleStringLiteral = null;


        try {
            // InternalCompleteOCL.g:6881:2: (iv_ruleStringLiteral= ruleStringLiteral EOF )
            // InternalCompleteOCL.g:6882:2: iv_ruleStringLiteral= ruleStringLiteral EOF
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
    // InternalCompleteOCL.g:6889:1: ruleStringLiteral returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_SINGLE_QUOTED_STRING_0= RULE_SINGLE_QUOTED_STRING ;
    public final AntlrDatatypeRuleToken ruleStringLiteral() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_SINGLE_QUOTED_STRING_0=null;

         enterRule();

        try {
            // InternalCompleteOCL.g:6892:28: (this_SINGLE_QUOTED_STRING_0= RULE_SINGLE_QUOTED_STRING )
            // InternalCompleteOCL.g:6893:5: this_SINGLE_QUOTED_STRING_0= RULE_SINGLE_QUOTED_STRING
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
    // InternalCompleteOCL.g:6908:1: entryRuleUPPER returns [String current=null] : iv_ruleUPPER= ruleUPPER EOF ;
    public final String entryRuleUPPER() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleUPPER = null;


        try {
            // InternalCompleteOCL.g:6909:2: (iv_ruleUPPER= ruleUPPER EOF )
            // InternalCompleteOCL.g:6910:2: iv_ruleUPPER= ruleUPPER EOF
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
    // InternalCompleteOCL.g:6917:1: ruleUPPER returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_INT_0= RULE_INT | kw= '*' ) ;
    public final AntlrDatatypeRuleToken ruleUPPER() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_INT_0=null;
        Token kw=null;

         enterRule();

        try {
            // InternalCompleteOCL.g:6920:28: ( (this_INT_0= RULE_INT | kw= '*' ) )
            // InternalCompleteOCL.g:6921:1: (this_INT_0= RULE_INT | kw= '*' )
            {
            // InternalCompleteOCL.g:6921:1: (this_INT_0= RULE_INT | kw= '*' )
            int alt122=2;
            int LA122_0 = input.LA(1);

            if ( (LA122_0==RULE_INT) ) {
                alt122=1;
            }
            else if ( (LA122_0==54) ) {
                alt122=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 122, 0, input);

                throw nvae;
            }
            switch (alt122) {
                case 1 :
                    // InternalCompleteOCL.g:6921:6: this_INT_0= RULE_INT
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
                    // InternalCompleteOCL.g:6930:2: kw= '*'
                    {
                    kw=(Token)match(input,54,FollowSets000.FOLLOW_2); if (state.failed) return current;
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
    // InternalCompleteOCL.g:6943:1: entryRuleURI returns [String current=null] : iv_ruleURI= ruleURI EOF ;
    public final String entryRuleURI() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleURI = null;


        try {
            // InternalCompleteOCL.g:6944:2: (iv_ruleURI= ruleURI EOF )
            // InternalCompleteOCL.g:6945:2: iv_ruleURI= ruleURI EOF
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
    // InternalCompleteOCL.g:6952:1: ruleURI returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_SINGLE_QUOTED_STRING_0= RULE_SINGLE_QUOTED_STRING ;
    public final AntlrDatatypeRuleToken ruleURI() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_SINGLE_QUOTED_STRING_0=null;

         enterRule();

        try {
            // InternalCompleteOCL.g:6955:28: (this_SINGLE_QUOTED_STRING_0= RULE_SINGLE_QUOTED_STRING )
            // InternalCompleteOCL.g:6956:5: this_SINGLE_QUOTED_STRING_0= RULE_SINGLE_QUOTED_STRING
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

    // $ANTLR start synpred11_InternalCompleteOCL
    public final void synpred11_InternalCompleteOCL_fragment() throws RecognitionException {
        EObject this_PropertyContextDeclCS_0 = null;


        // InternalCompleteOCL.g:411:2: (this_PropertyContextDeclCS_0= rulePropertyContextDeclCS )
        // InternalCompleteOCL.g:411:2: this_PropertyContextDeclCS_0= rulePropertyContextDeclCS
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
    // $ANTLR end synpred11_InternalCompleteOCL

    // $ANTLR start synpred12_InternalCompleteOCL
    public final void synpred12_InternalCompleteOCL_fragment() throws RecognitionException {
        EObject this_ClassifierContextDeclCS_1 = null;


        // InternalCompleteOCL.g:424:2: (this_ClassifierContextDeclCS_1= ruleClassifierContextDeclCS )
        // InternalCompleteOCL.g:424:2: this_ClassifierContextDeclCS_1= ruleClassifierContextDeclCS
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
    // $ANTLR end synpred12_InternalCompleteOCL

    // $ANTLR start synpred119_InternalCompleteOCL
    public final void synpred119_InternalCompleteOCL_fragment() throws RecognitionException {
        EObject this_TypeLiteralCS_1 = null;


        // InternalCompleteOCL.g:4257:2: (this_TypeLiteralCS_1= ruleTypeLiteralCS )
        // InternalCompleteOCL.g:4257:2: this_TypeLiteralCS_1= ruleTypeLiteralCS
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
    // $ANTLR end synpred119_InternalCompleteOCL

    // $ANTLR start synpred122_InternalCompleteOCL
    public final void synpred122_InternalCompleteOCL_fragment() throws RecognitionException {
        EObject this_PrefixedPrimaryExpCS_0 = null;

        AntlrDatatypeRuleToken lv_name_2_0 = null;

        EObject lv_ownedRight_3_0 = null;


        // InternalCompleteOCL.g:4353:2: ( (this_PrefixedPrimaryExpCS_0= rulePrefixedPrimaryExpCS ( () ( (lv_name_2_0= ruleBinaryOperatorName ) ) ( (lv_ownedRight_3_0= ruleExpCS ) ) )? ) )
        // InternalCompleteOCL.g:4353:2: (this_PrefixedPrimaryExpCS_0= rulePrefixedPrimaryExpCS ( () ( (lv_name_2_0= ruleBinaryOperatorName ) ) ( (lv_ownedRight_3_0= ruleExpCS ) ) )? )
        {
        // InternalCompleteOCL.g:4353:2: (this_PrefixedPrimaryExpCS_0= rulePrefixedPrimaryExpCS ( () ( (lv_name_2_0= ruleBinaryOperatorName ) ) ( (lv_ownedRight_3_0= ruleExpCS ) ) )? )
        // InternalCompleteOCL.g:4354:2: this_PrefixedPrimaryExpCS_0= rulePrefixedPrimaryExpCS ( () ( (lv_name_2_0= ruleBinaryOperatorName ) ) ( (lv_ownedRight_3_0= ruleExpCS ) ) )?
        {
        if ( state.backtracking==0 ) {

          	  /* */

        }
        pushFollow(FollowSets000.FOLLOW_50);
        this_PrefixedPrimaryExpCS_0=rulePrefixedPrimaryExpCS();

        state._fsp--;
        if (state.failed) return ;
        // InternalCompleteOCL.g:4365:1: ( () ( (lv_name_2_0= ruleBinaryOperatorName ) ) ( (lv_ownedRight_3_0= ruleExpCS ) ) )?
        int alt138=2;
        int LA138_0 = input.LA(1);

        if ( ((LA138_0>=17 && LA138_0<=18)||LA138_0==27||(LA138_0>=39 && LA138_0<=40)||LA138_0==52||(LA138_0>=54 && LA138_0<=67)) ) {
            alt138=1;
        }
        switch (alt138) {
            case 1 :
                // InternalCompleteOCL.g:4365:2: () ( (lv_name_2_0= ruleBinaryOperatorName ) ) ( (lv_ownedRight_3_0= ruleExpCS ) )
                {
                // InternalCompleteOCL.g:4365:2: ()
                // InternalCompleteOCL.g:4366:2:
                {
                if ( state.backtracking==0 ) {

                  	  /* */

                }

                }

                // InternalCompleteOCL.g:4374:2: ( (lv_name_2_0= ruleBinaryOperatorName ) )
                // InternalCompleteOCL.g:4375:1: (lv_name_2_0= ruleBinaryOperatorName )
                {
                // InternalCompleteOCL.g:4375:1: (lv_name_2_0= ruleBinaryOperatorName )
                // InternalCompleteOCL.g:4376:3: lv_name_2_0= ruleBinaryOperatorName
                {
                if ( state.backtracking==0 ) {

                  	        newCompositeNode(grammarAccess.getExpCSAccess().getNameBinaryOperatorNameParserRuleCall_0_1_1_0());

                }
                pushFollow(FollowSets000.FOLLOW_40);
                lv_name_2_0=ruleBinaryOperatorName();

                state._fsp--;
                if (state.failed) return ;

                }


                }

                // InternalCompleteOCL.g:4392:2: ( (lv_ownedRight_3_0= ruleExpCS ) )
                // InternalCompleteOCL.g:4393:1: (lv_ownedRight_3_0= ruleExpCS )
                {
                // InternalCompleteOCL.g:4393:1: (lv_ownedRight_3_0= ruleExpCS )
                // InternalCompleteOCL.g:4394:3: lv_ownedRight_3_0= ruleExpCS
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
    // $ANTLR end synpred122_InternalCompleteOCL

    // $ANTLR start synpred129_InternalCompleteOCL
    public final void synpred129_InternalCompleteOCL_fragment() throws RecognitionException {
        EObject this_TupleLiteralExpCS_4 = null;


        // InternalCompleteOCL.g:4655:2: (this_TupleLiteralExpCS_4= ruleTupleLiteralExpCS )
        // InternalCompleteOCL.g:4655:2: this_TupleLiteralExpCS_4= ruleTupleLiteralExpCS
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
    // $ANTLR end synpred129_InternalCompleteOCL

    // $ANTLR start synpred130_InternalCompleteOCL
    public final void synpred130_InternalCompleteOCL_fragment() throws RecognitionException {
        EObject this_MapLiteralExpCS_5 = null;


        // InternalCompleteOCL.g:4668:2: (this_MapLiteralExpCS_5= ruleMapLiteralExpCS )
        // InternalCompleteOCL.g:4668:2: this_MapLiteralExpCS_5= ruleMapLiteralExpCS
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
    // $ANTLR end synpred130_InternalCompleteOCL

    // $ANTLR start synpred131_InternalCompleteOCL
    public final void synpred131_InternalCompleteOCL_fragment() throws RecognitionException {
        EObject this_CollectionLiteralExpCS_6 = null;


        // InternalCompleteOCL.g:4681:2: (this_CollectionLiteralExpCS_6= ruleCollectionLiteralExpCS )
        // InternalCompleteOCL.g:4681:2: this_CollectionLiteralExpCS_6= ruleCollectionLiteralExpCS
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
    // $ANTLR end synpred131_InternalCompleteOCL

    // $ANTLR start synpred133_InternalCompleteOCL
    public final void synpred133_InternalCompleteOCL_fragment() throws RecognitionException {
        EObject this_TypeLiteralExpCS_8 = null;


        // InternalCompleteOCL.g:4707:2: (this_TypeLiteralExpCS_8= ruleTypeLiteralExpCS )
        // InternalCompleteOCL.g:4707:2: this_TypeLiteralExpCS_8= ruleTypeLiteralExpCS
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
    // $ANTLR end synpred133_InternalCompleteOCL

    // Delegated rules

    public final boolean synpred129_InternalCompleteOCL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred129_InternalCompleteOCL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred12_InternalCompleteOCL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred12_InternalCompleteOCL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred131_InternalCompleteOCL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred131_InternalCompleteOCL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred119_InternalCompleteOCL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred119_InternalCompleteOCL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred122_InternalCompleteOCL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred122_InternalCompleteOCL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred133_InternalCompleteOCL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred133_InternalCompleteOCL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred11_InternalCompleteOCL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred11_InternalCompleteOCL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred130_InternalCompleteOCL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred130_InternalCompleteOCL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }


    protected DFA10 dfa10 = new DFA10(this);
    protected DFA75 dfa75 = new DFA75(this);
    protected DFA78 dfa78 = new DFA78(this);
    protected DFA81 dfa81 = new DFA81(this);
    static final String dfa_1s = "\20\uffff";
    static final String dfa_2s = "\1\30\1\31\1\5\5\27\1\5\5\25\2\uffff";
    static final String dfa_3s = "\2\31\1\36\5\27\1\47\5\27\2\uffff";
    static final String dfa_4s = "\16\uffff\1\1\1\2";
    static final String dfa_5s = "\20\uffff}>";
    static final String[] dfa_6s = {
            "\1\1\1\2",
            "\1\2",
            "\1\3\1\4\20\uffff\1\10\4\uffff\1\5\1\6\1\7",
            "\1\10",
            "\1\10",
            "\1\10",
            "\1\10",
            "\1\10",
            "\1\11\1\12\16\uffff\1\16\6\uffff\1\13\1\14\1\15\10\uffff\1\16",
            "\1\16\1\uffff\1\17",
            "\1\16\1\uffff\1\17",
            "\1\16\1\uffff\1\17",
            "\1\16\1\uffff\1\17",
            "\1\16\1\uffff\1\17",
            "",
            ""
    };

    static final short[] dfa_1 = DFA.unpackEncodedString(dfa_1s);
    static final char[] dfa_2 = DFA.unpackEncodedStringToUnsignedChars(dfa_2s);
    static final char[] dfa_3 = DFA.unpackEncodedStringToUnsignedChars(dfa_3s);
    static final short[] dfa_4 = DFA.unpackEncodedString(dfa_4s);
    static final short[] dfa_5 = DFA.unpackEncodedString(dfa_5s);
    static final short[][] dfa_6 = unpackEncodedStringArray(dfa_6s);

    class DFA10 extends DFA {

        public DFA10(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 10;
            this.eot = dfa_1;
            this.eof = dfa_1;
            this.min = dfa_2;
            this.max = dfa_3;
            this.accept = dfa_4;
            this.special = dfa_5;
            this.transition = dfa_6;
        }
        public String getDescription() {
            return "469:1: (this_DefOperationCS_0= ruleDefOperationCS | this_DefPropertyCS_1= ruleDefPropertyCS )";
        }
    }
    static final String dfa_7s = "\30\uffff";
    static final String dfa_8s = "\1\5\17\uffff\5\0\3\uffff";
    static final String dfa_9s = "\1\113\17\uffff\5\0\3\uffff";
    static final String dfa_10s = "\1\uffff\1\1\4\uffff\1\2\20\uffff\1\3";
    static final String dfa_11s = "\20\uffff\1\0\1\1\1\2\1\3\1\4\3\uffff}>";
    static final String[] dfa_12s = {
            "\2\1\25\uffff\3\1\13\uffff\12\6\20\uffff\2\6\1\uffff\1\20\1\21\1\22\1\23\1\24",
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

    class DFA75 extends DFA {

        public DFA75(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 75;
            this.eot = dfa_7;
            this.eof = dfa_7;
            this.min = dfa_8;
            this.max = dfa_9;
            this.accept = dfa_10;
            this.special = dfa_11;
            this.transition = dfa_12;
        }
        public String getDescription() {
            return "4243:1: (this_TypeNameExpCS_0= ruleTypeNameExpCS | this_TypeLiteralCS_1= ruleTypeLiteralCS | this_CollectionPatternCS_2= ruleCollectionPatternCS )";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 :
                        int LA75_16 = input.LA(1);


                        int index75_16 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred119_InternalCompleteOCL()) ) {s = 6;}

                        else if ( (true) ) {s = 23;}


                        input.seek(index75_16);
                        if ( s>=0 ) return s;
                        break;
                    case 1 :
                        int LA75_17 = input.LA(1);


                        int index75_17 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred119_InternalCompleteOCL()) ) {s = 6;}

                        else if ( (true) ) {s = 23;}


                        input.seek(index75_17);
                        if ( s>=0 ) return s;
                        break;
                    case 2 :
                        int LA75_18 = input.LA(1);


                        int index75_18 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred119_InternalCompleteOCL()) ) {s = 6;}

                        else if ( (true) ) {s = 23;}


                        input.seek(index75_18);
                        if ( s>=0 ) return s;
                        break;
                    case 3 :
                        int LA75_19 = input.LA(1);


                        int index75_19 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred119_InternalCompleteOCL()) ) {s = 6;}

                        else if ( (true) ) {s = 23;}


                        input.seek(index75_19);
                        if ( s>=0 ) return s;
                        break;
                    case 4 :
                        int LA75_20 = input.LA(1);


                        int index75_20 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred119_InternalCompleteOCL()) ) {s = 6;}

                        else if ( (true) ) {s = 23;}


                        input.seek(index75_20);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 75, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String dfa_13s = "\45\uffff";
    static final String dfa_14s = "\1\5\2\0\42\uffff";
    static final String dfa_15s = "\1\142\2\0\42\uffff";
    static final String dfa_16s = "\3\uffff\1\1\40\uffff\1\2";
    static final String dfa_17s = "\1\uffff\1\0\1\1\42\uffff}>";
    static final String[] dfa_18s = {
            "\4\3\14\uffff\1\3\6\uffff\3\3\13\uffff\12\3\1\1\1\2\1\3\15\uffff\2\3\1\uffff\5\3\4\uffff\1\3\1\uffff\4\3\6\uffff\1\3\4\uffff\1\44\1\3",
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

    class DFA78 extends DFA {

        public DFA78(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 78;
            this.eot = dfa_13;
            this.eof = dfa_13;
            this.min = dfa_14;
            this.max = dfa_15;
            this.accept = dfa_16;
            this.special = dfa_17;
            this.transition = dfa_18;
        }
        public String getDescription() {
            return "4353:1: ( (this_PrefixedPrimaryExpCS_0= rulePrefixedPrimaryExpCS ( () ( (lv_name_2_0= ruleBinaryOperatorName ) ) ( (lv_ownedRight_3_0= ruleExpCS ) ) )? ) | this_PrefixedLetExpCS_4= rulePrefixedLetExpCS )";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 :
                        int LA78_1 = input.LA(1);


                        int index78_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred122_InternalCompleteOCL()) ) {s = 3;}

                        else if ( (true) ) {s = 36;}


                        input.seek(index78_1);
                        if ( s>=0 ) return s;
                        break;
                    case 1 :
                        int LA78_2 = input.LA(1);


                        int index78_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred122_InternalCompleteOCL()) ) {s = 3;}

                        else if ( (true) ) {s = 36;}


                        input.seek(index78_2);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 78, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String dfa_19s = "\1\5\12\uffff\7\0\23\uffff";
    static final String dfa_20s = "\1\142\12\uffff\7\0\23\uffff";
    static final String dfa_21s = "\1\uffff\1\1\1\2\1\3\1\4\15\uffff\1\10\1\11\11\uffff\1\12\4\uffff\1\5\1\6\1\7";
    static final String dfa_22s = "\13\uffff\1\0\1\1\1\2\1\3\1\4\1\5\1\6\23\uffff}>";
    static final String[] dfa_23s = {
            "\2\35\2\4\14\uffff\1\1\6\uffff\3\35\13\uffff\12\23\2\uffff\1\4\15\uffff\1\14\1\13\1\uffff\1\15\1\16\1\17\1\20\1\21\4\uffff\1\22\1\uffff\4\4\6\uffff\1\2\5\uffff\1\3",
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
    static final char[] dfa_19 = DFA.unpackEncodedStringToUnsignedChars(dfa_19s);
    static final char[] dfa_20 = DFA.unpackEncodedStringToUnsignedChars(dfa_20s);
    static final short[] dfa_21 = DFA.unpackEncodedString(dfa_21s);
    static final short[] dfa_22 = DFA.unpackEncodedString(dfa_22s);
    static final short[][] dfa_23 = unpackEncodedStringArray(dfa_23s);

    class DFA81 extends DFA {

        public DFA81(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 81;
            this.eot = dfa_13;
            this.eof = dfa_13;
            this.min = dfa_19;
            this.max = dfa_20;
            this.accept = dfa_21;
            this.special = dfa_22;
            this.transition = dfa_23;
        }
        public String getDescription() {
            return "4602:1: (this_NestedExpCS_0= ruleNestedExpCS | this_IfExpCS_1= ruleIfExpCS | this_SelfExpCS_2= ruleSelfExpCS | this_PrimitiveLiteralExpCS_3= rulePrimitiveLiteralExpCS | this_TupleLiteralExpCS_4= ruleTupleLiteralExpCS | this_MapLiteralExpCS_5= ruleMapLiteralExpCS | this_CollectionLiteralExpCS_6= ruleCollectionLiteralExpCS | this_LambdaLiteralExpCS_7= ruleLambdaLiteralExpCS | this_TypeLiteralExpCS_8= ruleTypeLiteralExpCS | this_NameExpCS_9= ruleNameExpCS )";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 :
                        int LA81_11 = input.LA(1);


                        int index81_11 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred129_InternalCompleteOCL()) ) {s = 34;}

                        else if ( (synpred133_InternalCompleteOCL()) ) {s = 19;}


                        input.seek(index81_11);
                        if ( s>=0 ) return s;
                        break;
                    case 1 :
                        int LA81_12 = input.LA(1);


                        int index81_12 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred130_InternalCompleteOCL()) ) {s = 35;}

                        else if ( (synpred133_InternalCompleteOCL()) ) {s = 19;}


                        input.seek(index81_12);
                        if ( s>=0 ) return s;
                        break;
                    case 2 :
                        int LA81_13 = input.LA(1);


                        int index81_13 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred131_InternalCompleteOCL()) ) {s = 36;}

                        else if ( (synpred133_InternalCompleteOCL()) ) {s = 19;}


                        input.seek(index81_13);
                        if ( s>=0 ) return s;
                        break;
                    case 3 :
                        int LA81_14 = input.LA(1);


                        int index81_14 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred131_InternalCompleteOCL()) ) {s = 36;}

                        else if ( (synpred133_InternalCompleteOCL()) ) {s = 19;}


                        input.seek(index81_14);
                        if ( s>=0 ) return s;
                        break;
                    case 4 :
                        int LA81_15 = input.LA(1);


                        int index81_15 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred131_InternalCompleteOCL()) ) {s = 36;}

                        else if ( (synpred133_InternalCompleteOCL()) ) {s = 19;}


                        input.seek(index81_15);
                        if ( s>=0 ) return s;
                        break;
                    case 5 :
                        int LA81_16 = input.LA(1);


                        int index81_16 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred131_InternalCompleteOCL()) ) {s = 36;}

                        else if ( (synpred133_InternalCompleteOCL()) ) {s = 19;}


                        input.seek(index81_16);
                        if ( s>=0 ) return s;
                        break;
                    case 6 :
                        int LA81_17 = input.LA(1);


                        int index81_17 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred131_InternalCompleteOCL()) ) {s = 36;}

                        else if ( (synpred133_InternalCompleteOCL()) ) {s = 19;}


                        input.seek(index81_17);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 81, _s, input);
            error(nvae);
            throw nvae;
        }
    }



    private static class FollowSets000 {
        public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000870080002L});
        public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000800080002L});
        public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000008070200060L});
        public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000003100000L});
        public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000070800060L});
        public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000003100002L});
        public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000000A00000L});
        public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x007FFC80702001F0L,0x00000006103D0FB0L});
        public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000000400000L});
        public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000000800000L});
        public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000002000000L});
        public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000000070000060L});
        public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000000000200000L});
        public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x0000000070400060L});
        public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x0000000004400000L});
        public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x000FFC8078200060L,0x0000000000000FB0L});
        public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x0000000008000000L});
        public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x000FFC8070200060L,0x0000000000000FB0L});
        public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x0000000070000160L});
        public static final BitSet FOLLOW_22 = new BitSet(new long[]{0x0000000080000002L});
        public static final BitSet FOLLOW_23 = new BitSet(new long[]{0x000FFC8070600060L,0x0000000000000FB0L});
        public static final BitSet FOLLOW_24 = new BitSet(new long[]{0x000FFC8770200062L,0x0000000000000FB0L});
        public static final BitSet FOLLOW_25 = new BitSet(new long[]{0x0000000700000002L});
        public static final BitSet FOLLOW_26 = new BitSet(new long[]{0x0000001800180000L});
        public static final BitSet FOLLOW_27 = new BitSet(new long[]{0x0000001800080000L});
        public static final BitSet FOLLOW_28 = new BitSet(new long[]{0x0000006000000002L});
        public static final BitSet FOLLOW_29 = new BitSet(new long[]{0x0000010004000000L});
        public static final BitSet FOLLOW_30 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000040L});
        public static final BitSet FOLLOW_31 = new BitSet(new long[]{0x000FFC0070000060L,0x0000000000000FB0L});
        public static final BitSet FOLLOW_32 = new BitSet(new long[]{0x0000000000200002L});
        public static final BitSet FOLLOW_33 = new BitSet(new long[]{0x0000000000400000L,0x0000000000800000L});
        public static final BitSet FOLLOW_34 = new BitSet(new long[]{0x0000000004000000L});
        public static final BitSet FOLLOW_35 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
        public static final BitSet FOLLOW_36 = new BitSet(new long[]{0x007FFC8070A001E0L,0x00000006103D2FB0L});
        public static final BitSet FOLLOW_37 = new BitSet(new long[]{0x0000000004000000L,0x0000000000002000L});
        public static final BitSet FOLLOW_38 = new BitSet(new long[]{0x007FFC8070A001E0L,0x00000006103D0FB0L});
        public static final BitSet FOLLOW_39 = new BitSet(new long[]{0x0000000000000002L,0x0000000000004000L});
        public static final BitSet FOLLOW_40 = new BitSet(new long[]{0x007FFC80702001E0L,0x00000006103D0FB0L});
        public static final BitSet FOLLOW_41 = new BitSet(new long[]{0x0000000004000000L,0x0000000000008000L});
        public static final BitSet FOLLOW_42 = new BitSet(new long[]{0x0000000000000060L});
        public static final BitSet FOLLOW_43 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
        public static final BitSet FOLLOW_44 = new BitSet(new long[]{0x007FFC80702001E0L,0x00000006103D2FB0L});
        public static final BitSet FOLLOW_45 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
        public static final BitSet FOLLOW_46 = new BitSet(new long[]{0x0000000008800000L});
        public static final BitSet FOLLOW_47 = new BitSet(new long[]{0x0000000000000102L});
        public static final BitSet FOLLOW_48 = new BitSet(new long[]{0x0000000000000002L,0x0000000000800000L});
        public static final BitSet FOLLOW_49 = new BitSet(new long[]{0x0000000000000002L,0x0000000000001000L});
        public static final BitSet FOLLOW_50 = new BitSet(new long[]{0xFFD0018008060002L,0x000000000000000FL});
        public static final BitSet FOLLOW_51 = new BitSet(new long[]{0x007FFC80702001E0L,0x00000004103D0FB0L});
        public static final BitSet FOLLOW_52 = new BitSet(new long[]{0x0000000000200002L,0x0000000000C01000L});
        public static final BitSet FOLLOW_53 = new BitSet(new long[]{0x0000000000000002L,0x0000000000401000L});
        public static final BitSet FOLLOW_54 = new BitSet(new long[]{0x0000000000000002L,0x0000000000400000L});
        public static final BitSet FOLLOW_55 = new BitSet(new long[]{0x0000000100000000L});
        public static final BitSet FOLLOW_56 = new BitSet(new long[]{0x0000000070000160L,0x0000000000002000L});
        public static final BitSet FOLLOW_57 = new BitSet(new long[]{0x007FFE8070E001E0L,0x00000006103D0FB0L});
        public static final BitSet FOLLOW_58 = new BitSet(new long[]{0x0000000004400000L,0x000000000C000000L});
        public static final BitSet FOLLOW_59 = new BitSet(new long[]{0x0000000004400000L,0x0000000004000000L});
        public static final BitSet FOLLOW_60 = new BitSet(new long[]{0x0000000004000000L,0x0000000001000000L});
        public static final BitSet FOLLOW_61 = new BitSet(new long[]{0x0000000000800002L,0x0000000002000000L});
        public static final BitSet FOLLOW_62 = new BitSet(new long[]{0x0000000008000002L});
        public static final BitSet FOLLOW_63 = new BitSet(new long[]{0x007FFE80702001E0L,0x00000006103D0FB0L});
        public static final BitSet FOLLOW_64 = new BitSet(new long[]{0x0000000000800002L});
        public static final BitSet FOLLOW_65 = new BitSet(new long[]{0x0000000000000000L,0x0000000020000000L});
        public static final BitSet FOLLOW_66 = new BitSet(new long[]{0x0000000000000000L,0x0000000140000000L});
        public static final BitSet FOLLOW_67 = new BitSet(new long[]{0x0000000000000000L,0x0000000080000000L});
        public static final BitSet FOLLOW_68 = new BitSet(new long[]{0x0000000004000000L,0x0000000002000000L});
        public static final BitSet FOLLOW_69 = new BitSet(new long[]{0x0000000008A00000L});
        public static final BitSet FOLLOW_70 = new BitSet(new long[]{0x0040000000000080L});
        public static final BitSet FOLLOW_71 = new BitSet(new long[]{0x0140020000000080L});
        public static final BitSet FOLLOW_72 = new BitSet(new long[]{0x0000000000000000L,0x0000001801000000L});
        public static final BitSet FOLLOW_73 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
        public static final BitSet FOLLOW_74 = new BitSet(new long[]{0x0000000004000002L,0x0000000000800000L});
        public static final BitSet FOLLOW_75 = new BitSet(new long[]{0x000FFE8070200060L,0x0000000000000FB0L});
        public static final BitSet FOLLOW_76 = new BitSet(new long[]{0x0000000000000002L,0x0000002000000000L});
        public static final BitSet FOLLOW_77 = new BitSet(new long[]{0x0000000000000002L,0x0000004000000000L});
    }


}