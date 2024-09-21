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
public class InternalCompleteOCLParser extends org.eclipse.ocl.xtext.base.utilities.CompatibilityAbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_UNQUOTED_STRING", "RULE_SIMPLE_ID", "RULE_ESCAPED_ID", "RULE_INT", "RULE_SINGLE_QUOTED_STRING", "RULE_ESCAPED_CHARACTER", "RULE_LETTER_CHARACTER", "RULE_DOUBLE_QUOTED_STRING", "RULE_ML_SINGLE_QUOTED_STRING", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'^'", "'^^'", "'context'", "'inv'", "'('", "')'", "':'", "'pre'", "'post'", "'static'", "'def'", "','", "'='", "'import'", "'include'", "'library'", "'::*'", "'package'", "'endpackage'", "'body'", "'derive'", "'init'", "'<'", "'>'", "'?'", "'Boolean'", "'Integer'", "'Real'", "'String'", "'UnlimitedNatural'", "'OclAny'", "'OclInvalid'", "'OclMessage'", "'OclState'", "'OclVoid'", "'-'", "'not'", "'not2'", "'*'", "'/'", "'+'", "'>='", "'<='", "'<>'", "'and'", "'and2'", "'implies'", "'implies2'", "'or'", "'or2'", "'xor'", "'xor2'", "'.'", "'->'", "'?.'", "'?->'", "'Map'", "'Tuple'", "'::'", "'Set'", "'Bag'", "'Sequence'", "'Collection'", "'OrderedSet'", "'{'", "'}'", "'..'", "'++'", "'Lambda'", "'with'", "'<-'", "'true'", "'false'", "'invalid'", "'null'", "'@'", "'['", "']'", "'in'", "'|'", "';'", "'if'", "'then'", "'else'", "'endif'", "'elseif'", "'let'", "'self'", "'|?'", "'|1'", "'extends'", "'&&'"
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
    // InternalCompleteOCL.g:79:1: entryRuleCompleteOCLDocumentCS returns [EObject current=null] : iv_ruleCompleteOCLDocumentCS= ruleCompleteOCLDocumentCS EOF ;
    public final EObject entryRuleCompleteOCLDocumentCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCompleteOCLDocumentCS = null;


        try {
            // InternalCompleteOCL.g:79:62: (iv_ruleCompleteOCLDocumentCS= ruleCompleteOCLDocumentCS EOF )
            // InternalCompleteOCL.g:80:2: iv_ruleCompleteOCLDocumentCS= ruleCompleteOCLDocumentCS EOF
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
    // InternalCompleteOCL.g:86:1: ruleCompleteOCLDocumentCS returns [EObject current=null] : ( ( (lv_ownedImports_0_0= ruleImportCS ) )* ( ( (lv_ownedPackages_1_0= rulePackageDeclarationCS ) ) | ( (lv_ownedContexts_2_0= ruleContextDeclCS ) ) )* ) ;
    public final EObject ruleCompleteOCLDocumentCS() throws RecognitionException {
        EObject current = null;

        EObject lv_ownedImports_0_0 = null;

        EObject lv_ownedPackages_1_0 = null;

        EObject lv_ownedContexts_2_0 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:92:2: ( ( ( (lv_ownedImports_0_0= ruleImportCS ) )* ( ( (lv_ownedPackages_1_0= rulePackageDeclarationCS ) ) | ( (lv_ownedContexts_2_0= ruleContextDeclCS ) ) )* ) )
            // InternalCompleteOCL.g:93:2: ( ( (lv_ownedImports_0_0= ruleImportCS ) )* ( ( (lv_ownedPackages_1_0= rulePackageDeclarationCS ) ) | ( (lv_ownedContexts_2_0= ruleContextDeclCS ) ) )* )
            {
            // InternalCompleteOCL.g:93:2: ( ( (lv_ownedImports_0_0= ruleImportCS ) )* ( ( (lv_ownedPackages_1_0= rulePackageDeclarationCS ) ) | ( (lv_ownedContexts_2_0= ruleContextDeclCS ) ) )* )
            // InternalCompleteOCL.g:94:3: ( (lv_ownedImports_0_0= ruleImportCS ) )* ( ( (lv_ownedPackages_1_0= rulePackageDeclarationCS ) ) | ( (lv_ownedContexts_2_0= ruleContextDeclCS ) ) )*
            {
            // InternalCompleteOCL.g:94:3: ( (lv_ownedImports_0_0= ruleImportCS ) )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0>=30 && LA1_0<=32)) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // InternalCompleteOCL.g:95:4: (lv_ownedImports_0_0= ruleImportCS )
            	    {
            	    // InternalCompleteOCL.g:95:4: (lv_ownedImports_0_0= ruleImportCS )
            	    // InternalCompleteOCL.g:96:5: lv_ownedImports_0_0= ruleImportCS
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

            // InternalCompleteOCL.g:113:3: ( ( (lv_ownedPackages_1_0= rulePackageDeclarationCS ) ) | ( (lv_ownedContexts_2_0= ruleContextDeclCS ) ) )*
            loop2:
            do {
                int alt2=3;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==34) ) {
                    alt2=1;
                }
                else if ( (LA2_0==19) ) {
                    alt2=2;
                }


                switch (alt2) {
            	case 1 :
            	    // InternalCompleteOCL.g:114:4: ( (lv_ownedPackages_1_0= rulePackageDeclarationCS ) )
            	    {
            	    // InternalCompleteOCL.g:114:4: ( (lv_ownedPackages_1_0= rulePackageDeclarationCS ) )
            	    // InternalCompleteOCL.g:115:5: (lv_ownedPackages_1_0= rulePackageDeclarationCS )
            	    {
            	    // InternalCompleteOCL.g:115:5: (lv_ownedPackages_1_0= rulePackageDeclarationCS )
            	    // InternalCompleteOCL.g:116:6: lv_ownedPackages_1_0= rulePackageDeclarationCS
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
            	    // InternalCompleteOCL.g:134:4: ( (lv_ownedContexts_2_0= ruleContextDeclCS ) )
            	    {
            	    // InternalCompleteOCL.g:134:4: ( (lv_ownedContexts_2_0= ruleContextDeclCS ) )
            	    // InternalCompleteOCL.g:135:5: (lv_ownedContexts_2_0= ruleContextDeclCS )
            	    {
            	    // InternalCompleteOCL.g:135:5: (lv_ownedContexts_2_0= ruleContextDeclCS )
            	    // InternalCompleteOCL.g:136:6: lv_ownedContexts_2_0= ruleContextDeclCS
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
    // InternalCompleteOCL.g:158:1: entryRuleCompleteOCLNavigationOperatorName returns [String current=null] : iv_ruleCompleteOCLNavigationOperatorName= ruleCompleteOCLNavigationOperatorName EOF ;
    public final String entryRuleCompleteOCLNavigationOperatorName() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleCompleteOCLNavigationOperatorName = null;


        try {
            // InternalCompleteOCL.g:158:73: (iv_ruleCompleteOCLNavigationOperatorName= ruleCompleteOCLNavigationOperatorName EOF )
            // InternalCompleteOCL.g:159:2: iv_ruleCompleteOCLNavigationOperatorName= ruleCompleteOCLNavigationOperatorName EOF
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
            // InternalCompleteOCL.g:171:2: ( (kw= '^' | kw= '^^' ) )
            // InternalCompleteOCL.g:172:2: (kw= '^' | kw= '^^' )
            {
            // InternalCompleteOCL.g:172:2: (kw= '^' | kw= '^^' )
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
                    // InternalCompleteOCL.g:173:3: kw= '^'
                    {
                    kw=(Token)match(input,17,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getCompleteOCLNavigationOperatorNameAccess().getCircumflexAccentKeyword_0());

                    }

                    }
                    break;
                case 2 :
                    // InternalCompleteOCL.g:179:3: kw= '^^'
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
    // InternalCompleteOCL.g:188:1: entryRuleClassifierContextDeclCS returns [EObject current=null] : iv_ruleClassifierContextDeclCS= ruleClassifierContextDeclCS EOF ;
    public final EObject entryRuleClassifierContextDeclCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleClassifierContextDeclCS = null;


        try {
            // InternalCompleteOCL.g:188:64: (iv_ruleClassifierContextDeclCS= ruleClassifierContextDeclCS EOF )
            // InternalCompleteOCL.g:189:2: iv_ruleClassifierContextDeclCS= ruleClassifierContextDeclCS EOF
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
    // InternalCompleteOCL.g:195:1: ruleClassifierContextDeclCS returns [EObject current=null] : (otherlv_0= 'context' ( (lv_ownedSignature_1_0= ruleTemplateSignatureCS ) )? ( (lv_selfName_2_0= ruleUnrestrictedName ) )? ( (lv_ownedPathName_3_0= ruleUnreservedPathNameCS ) ) ( ( (lv_ownedInvariants_4_0= ruleinvConstraintCS ) ) | ( (lv_ownedDefinitions_5_0= ruleDefCS ) ) )+ ) ;
    public final EObject ruleClassifierContextDeclCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        EObject lv_ownedSignature_1_0 = null;

        AntlrDatatypeRuleToken lv_selfName_2_0 = null;

        EObject lv_ownedPathName_3_0 = null;

        EObject lv_ownedInvariants_4_0 = null;

        EObject lv_ownedDefinitions_5_0 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:201:2: ( (otherlv_0= 'context' ( (lv_ownedSignature_1_0= ruleTemplateSignatureCS ) )? ( (lv_selfName_2_0= ruleUnrestrictedName ) )? ( (lv_ownedPathName_3_0= ruleUnreservedPathNameCS ) ) ( ( (lv_ownedInvariants_4_0= ruleinvConstraintCS ) ) | ( (lv_ownedDefinitions_5_0= ruleDefCS ) ) )+ ) )
            // InternalCompleteOCL.g:202:2: (otherlv_0= 'context' ( (lv_ownedSignature_1_0= ruleTemplateSignatureCS ) )? ( (lv_selfName_2_0= ruleUnrestrictedName ) )? ( (lv_ownedPathName_3_0= ruleUnreservedPathNameCS ) ) ( ( (lv_ownedInvariants_4_0= ruleinvConstraintCS ) ) | ( (lv_ownedDefinitions_5_0= ruleDefCS ) ) )+ )
            {
            // InternalCompleteOCL.g:202:2: (otherlv_0= 'context' ( (lv_ownedSignature_1_0= ruleTemplateSignatureCS ) )? ( (lv_selfName_2_0= ruleUnrestrictedName ) )? ( (lv_ownedPathName_3_0= ruleUnreservedPathNameCS ) ) ( ( (lv_ownedInvariants_4_0= ruleinvConstraintCS ) ) | ( (lv_ownedDefinitions_5_0= ruleDefCS ) ) )+ )
            // InternalCompleteOCL.g:203:3: otherlv_0= 'context' ( (lv_ownedSignature_1_0= ruleTemplateSignatureCS ) )? ( (lv_selfName_2_0= ruleUnrestrictedName ) )? ( (lv_ownedPathName_3_0= ruleUnreservedPathNameCS ) ) ( ( (lv_ownedInvariants_4_0= ruleinvConstraintCS ) ) | ( (lv_ownedDefinitions_5_0= ruleDefCS ) ) )+
            {
            otherlv_0=(Token)match(input,19,FollowSets000.FOLLOW_5); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getClassifierContextDeclCSAccess().getContextKeyword_0());

            }
            // InternalCompleteOCL.g:207:3: ( (lv_ownedSignature_1_0= ruleTemplateSignatureCS ) )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==21||LA4_0==39) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // InternalCompleteOCL.g:208:4: (lv_ownedSignature_1_0= ruleTemplateSignatureCS )
                    {
                    // InternalCompleteOCL.g:208:4: (lv_ownedSignature_1_0= ruleTemplateSignatureCS )
                    // InternalCompleteOCL.g:209:5: lv_ownedSignature_1_0= ruleTemplateSignatureCS
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

            // InternalCompleteOCL.g:226:3: ( (lv_selfName_2_0= ruleUnrestrictedName ) )?
            int alt5=2;
            switch ( input.LA(1) ) {
                case RULE_SIMPLE_ID:
                    {
                    int LA5_1 = input.LA(2);

                    if ( ((LA5_1>=RULE_SIMPLE_ID && LA5_1<=RULE_ESCAPED_ID)||(LA5_1>=30 && LA5_1<=32)||(LA5_1>=42 && LA5_1<=51)||(LA5_1>=73 && LA5_1<=74)||(LA5_1>=76 && LA5_1<=80)) ) {
                        alt5=1;
                    }
                    }
                    break;
                case RULE_ESCAPED_ID:
                    {
                    int LA5_2 = input.LA(2);

                    if ( ((LA5_2>=RULE_SIMPLE_ID && LA5_2<=RULE_ESCAPED_ID)||(LA5_2>=30 && LA5_2<=32)||(LA5_2>=42 && LA5_2<=51)||(LA5_2>=73 && LA5_2<=74)||(LA5_2>=76 && LA5_2<=80)) ) {
                        alt5=1;
                    }
                    }
                    break;
                case 30:
                    {
                    int LA5_3 = input.LA(2);

                    if ( ((LA5_3>=RULE_SIMPLE_ID && LA5_3<=RULE_ESCAPED_ID)||(LA5_3>=30 && LA5_3<=32)||(LA5_3>=42 && LA5_3<=51)||(LA5_3>=73 && LA5_3<=74)||(LA5_3>=76 && LA5_3<=80)) ) {
                        alt5=1;
                    }
                    }
                    break;
                case 31:
                    {
                    int LA5_4 = input.LA(2);

                    if ( ((LA5_4>=RULE_SIMPLE_ID && LA5_4<=RULE_ESCAPED_ID)||(LA5_4>=30 && LA5_4<=32)||(LA5_4>=42 && LA5_4<=51)||(LA5_4>=73 && LA5_4<=74)||(LA5_4>=76 && LA5_4<=80)) ) {
                        alt5=1;
                    }
                    }
                    break;
                case 32:
                    {
                    int LA5_5 = input.LA(2);

                    if ( ((LA5_5>=RULE_SIMPLE_ID && LA5_5<=RULE_ESCAPED_ID)||(LA5_5>=30 && LA5_5<=32)||(LA5_5>=42 && LA5_5<=51)||(LA5_5>=73 && LA5_5<=74)||(LA5_5>=76 && LA5_5<=80)) ) {
                        alt5=1;
                    }
                    }
                    break;
            }

            switch (alt5) {
                case 1 :
                    // InternalCompleteOCL.g:227:4: (lv_selfName_2_0= ruleUnrestrictedName )
                    {
                    // InternalCompleteOCL.g:227:4: (lv_selfName_2_0= ruleUnrestrictedName )
                    // InternalCompleteOCL.g:228:5: lv_selfName_2_0= ruleUnrestrictedName
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

            // InternalCompleteOCL.g:245:3: ( (lv_ownedPathName_3_0= ruleUnreservedPathNameCS ) )
            // InternalCompleteOCL.g:246:4: (lv_ownedPathName_3_0= ruleUnreservedPathNameCS )
            {
            // InternalCompleteOCL.g:246:4: (lv_ownedPathName_3_0= ruleUnreservedPathNameCS )
            // InternalCompleteOCL.g:247:5: lv_ownedPathName_3_0= ruleUnreservedPathNameCS
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getClassifierContextDeclCSAccess().getOwnedPathNameUnreservedPathNameCSParserRuleCall_3_0());

            }
            pushFollow(FollowSets000.FOLLOW_6);
            lv_ownedPathName_3_0=ruleUnreservedPathNameCS();

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
              						"org.eclipse.ocl.xtext.base.Base.UnreservedPathNameCS");
              					afterParserOrEnumRuleCall();

            }

            }


            }

            // InternalCompleteOCL.g:264:3: ( ( (lv_ownedInvariants_4_0= ruleinvConstraintCS ) ) | ( (lv_ownedDefinitions_5_0= ruleDefCS ) ) )+
            int cnt6=0;
            loop6:
            do {
                int alt6=3;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==20) ) {
                    alt6=1;
                }
                else if ( ((LA6_0>=26 && LA6_0<=27)) ) {
                    alt6=2;
                }


                switch (alt6) {
            	case 1 :
            	    // InternalCompleteOCL.g:265:4: ( (lv_ownedInvariants_4_0= ruleinvConstraintCS ) )
            	    {
            	    // InternalCompleteOCL.g:265:4: ( (lv_ownedInvariants_4_0= ruleinvConstraintCS ) )
            	    // InternalCompleteOCL.g:266:5: (lv_ownedInvariants_4_0= ruleinvConstraintCS )
            	    {
            	    // InternalCompleteOCL.g:266:5: (lv_ownedInvariants_4_0= ruleinvConstraintCS )
            	    // InternalCompleteOCL.g:267:6: lv_ownedInvariants_4_0= ruleinvConstraintCS
            	    {
            	    if ( state.backtracking==0 ) {

            	      						newCompositeNode(grammarAccess.getClassifierContextDeclCSAccess().getOwnedInvariantsInvConstraintCSParserRuleCall_4_0_0());

            	    }
            	    pushFollow(FollowSets000.FOLLOW_7);
            	    lv_ownedInvariants_4_0=ruleinvConstraintCS();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      						if (current==null) {
            	      							current = createModelElementForParent(grammarAccess.getClassifierContextDeclCSRule());
            	      						}
            	      						add(
            	      							current,
            	      							"ownedInvariants",
            	      							lv_ownedInvariants_4_0,
            	      							"org.eclipse.ocl.xtext.completeocl.CompleteOCL.invConstraintCS");
            	      						afterParserOrEnumRuleCall();

            	    }

            	    }


            	    }


            	    }
            	    break;
            	case 2 :
            	    // InternalCompleteOCL.g:285:4: ( (lv_ownedDefinitions_5_0= ruleDefCS ) )
            	    {
            	    // InternalCompleteOCL.g:285:4: ( (lv_ownedDefinitions_5_0= ruleDefCS ) )
            	    // InternalCompleteOCL.g:286:5: (lv_ownedDefinitions_5_0= ruleDefCS )
            	    {
            	    // InternalCompleteOCL.g:286:5: (lv_ownedDefinitions_5_0= ruleDefCS )
            	    // InternalCompleteOCL.g:287:6: lv_ownedDefinitions_5_0= ruleDefCS
            	    {
            	    if ( state.backtracking==0 ) {

            	      						newCompositeNode(grammarAccess.getClassifierContextDeclCSAccess().getOwnedDefinitionsDefCSParserRuleCall_4_1_0());

            	    }
            	    pushFollow(FollowSets000.FOLLOW_7);
            	    lv_ownedDefinitions_5_0=ruleDefCS();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      						if (current==null) {
            	      							current = createModelElementForParent(grammarAccess.getClassifierContextDeclCSRule());
            	      						}
            	      						add(
            	      							current,
            	      							"ownedDefinitions",
            	      							lv_ownedDefinitions_5_0,
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


    // $ANTLR start "entryRuleinvConstraintCS"
    // InternalCompleteOCL.g:309:1: entryRuleinvConstraintCS returns [EObject current=null] : iv_ruleinvConstraintCS= ruleinvConstraintCS EOF ;
    public final EObject entryRuleinvConstraintCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleinvConstraintCS = null;


        try {
            // InternalCompleteOCL.g:309:56: (iv_ruleinvConstraintCS= ruleinvConstraintCS EOF )
            // InternalCompleteOCL.g:310:2: iv_ruleinvConstraintCS= ruleinvConstraintCS EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getInvConstraintCSRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleinvConstraintCS=ruleinvConstraintCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleinvConstraintCS;
            }
            match(input,EOF,FollowSets000.FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleinvConstraintCS"


    // $ANTLR start "ruleinvConstraintCS"
    // InternalCompleteOCL.g:316:1: ruleinvConstraintCS returns [EObject current=null] : (otherlv_0= 'inv' ( ( (lv_name_1_0= ruleUnrestrictedName ) ) (otherlv_2= '(' ( (lv_ownedMessageSpecification_3_0= ruleSpecificationCS ) ) otherlv_4= ')' )? )? otherlv_5= ':' ( (lv_ownedSpecification_6_0= ruleSpecificationCS ) ) ) ;
    public final EObject ruleinvConstraintCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        AntlrDatatypeRuleToken lv_name_1_0 = null;

        EObject lv_ownedMessageSpecification_3_0 = null;

        EObject lv_ownedSpecification_6_0 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:322:2: ( (otherlv_0= 'inv' ( ( (lv_name_1_0= ruleUnrestrictedName ) ) (otherlv_2= '(' ( (lv_ownedMessageSpecification_3_0= ruleSpecificationCS ) ) otherlv_4= ')' )? )? otherlv_5= ':' ( (lv_ownedSpecification_6_0= ruleSpecificationCS ) ) ) )
            // InternalCompleteOCL.g:323:2: (otherlv_0= 'inv' ( ( (lv_name_1_0= ruleUnrestrictedName ) ) (otherlv_2= '(' ( (lv_ownedMessageSpecification_3_0= ruleSpecificationCS ) ) otherlv_4= ')' )? )? otherlv_5= ':' ( (lv_ownedSpecification_6_0= ruleSpecificationCS ) ) )
            {
            // InternalCompleteOCL.g:323:2: (otherlv_0= 'inv' ( ( (lv_name_1_0= ruleUnrestrictedName ) ) (otherlv_2= '(' ( (lv_ownedMessageSpecification_3_0= ruleSpecificationCS ) ) otherlv_4= ')' )? )? otherlv_5= ':' ( (lv_ownedSpecification_6_0= ruleSpecificationCS ) ) )
            // InternalCompleteOCL.g:324:3: otherlv_0= 'inv' ( ( (lv_name_1_0= ruleUnrestrictedName ) ) (otherlv_2= '(' ( (lv_ownedMessageSpecification_3_0= ruleSpecificationCS ) ) otherlv_4= ')' )? )? otherlv_5= ':' ( (lv_ownedSpecification_6_0= ruleSpecificationCS ) )
            {
            otherlv_0=(Token)match(input,20,FollowSets000.FOLLOW_8); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getInvConstraintCSAccess().getInvKeyword_0());

            }
            // InternalCompleteOCL.g:328:3: ( ( (lv_name_1_0= ruleUnrestrictedName ) ) (otherlv_2= '(' ( (lv_ownedMessageSpecification_3_0= ruleSpecificationCS ) ) otherlv_4= ')' )? )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( ((LA8_0>=RULE_SIMPLE_ID && LA8_0<=RULE_ESCAPED_ID)||(LA8_0>=30 && LA8_0<=32)) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // InternalCompleteOCL.g:329:4: ( (lv_name_1_0= ruleUnrestrictedName ) ) (otherlv_2= '(' ( (lv_ownedMessageSpecification_3_0= ruleSpecificationCS ) ) otherlv_4= ')' )?
                    {
                    // InternalCompleteOCL.g:329:4: ( (lv_name_1_0= ruleUnrestrictedName ) )
                    // InternalCompleteOCL.g:330:5: (lv_name_1_0= ruleUnrestrictedName )
                    {
                    // InternalCompleteOCL.g:330:5: (lv_name_1_0= ruleUnrestrictedName )
                    // InternalCompleteOCL.g:331:6: lv_name_1_0= ruleUnrestrictedName
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getInvConstraintCSAccess().getNameUnrestrictedNameParserRuleCall_1_0_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_9);
                    lv_name_1_0=ruleUnrestrictedName();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElementForParent(grammarAccess.getInvConstraintCSRule());
                      						}
                      						set(
                      							current,
                      							"name",
                      							lv_name_1_0,
                      							"org.eclipse.ocl.xtext.completeocl.CompleteOCL.UnrestrictedName");
                      						afterParserOrEnumRuleCall();

                    }

                    }


                    }

                    // InternalCompleteOCL.g:348:4: (otherlv_2= '(' ( (lv_ownedMessageSpecification_3_0= ruleSpecificationCS ) ) otherlv_4= ')' )?
                    int alt7=2;
                    int LA7_0 = input.LA(1);

                    if ( (LA7_0==21) ) {
                        alt7=1;
                    }
                    switch (alt7) {
                        case 1 :
                            // InternalCompleteOCL.g:349:5: otherlv_2= '(' ( (lv_ownedMessageSpecification_3_0= ruleSpecificationCS ) ) otherlv_4= ')'
                            {
                            otherlv_2=(Token)match(input,21,FollowSets000.FOLLOW_10); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              					newLeafNode(otherlv_2, grammarAccess.getInvConstraintCSAccess().getLeftParenthesisKeyword_1_1_0());

                            }
                            // InternalCompleteOCL.g:353:5: ( (lv_ownedMessageSpecification_3_0= ruleSpecificationCS ) )
                            // InternalCompleteOCL.g:354:6: (lv_ownedMessageSpecification_3_0= ruleSpecificationCS )
                            {
                            // InternalCompleteOCL.g:354:6: (lv_ownedMessageSpecification_3_0= ruleSpecificationCS )
                            // InternalCompleteOCL.g:355:7: lv_ownedMessageSpecification_3_0= ruleSpecificationCS
                            {
                            if ( state.backtracking==0 ) {

                              							newCompositeNode(grammarAccess.getInvConstraintCSAccess().getOwnedMessageSpecificationSpecificationCSParserRuleCall_1_1_1_0());

                            }
                            pushFollow(FollowSets000.FOLLOW_11);
                            lv_ownedMessageSpecification_3_0=ruleSpecificationCS();

                            state._fsp--;
                            if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              							if (current==null) {
                              								current = createModelElementForParent(grammarAccess.getInvConstraintCSRule());
                              							}
                              							set(
                              								current,
                              								"ownedMessageSpecification",
                              								lv_ownedMessageSpecification_3_0,
                              								"org.eclipse.ocl.xtext.completeocl.CompleteOCL.SpecificationCS");
                              							afterParserOrEnumRuleCall();

                            }

                            }


                            }

                            otherlv_4=(Token)match(input,22,FollowSets000.FOLLOW_12); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              					newLeafNode(otherlv_4, grammarAccess.getInvConstraintCSAccess().getRightParenthesisKeyword_1_1_2());

                            }

                            }
                            break;

                    }


                    }
                    break;

            }

            otherlv_5=(Token)match(input,23,FollowSets000.FOLLOW_10); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_5, grammarAccess.getInvConstraintCSAccess().getColonKeyword_2());

            }
            // InternalCompleteOCL.g:382:3: ( (lv_ownedSpecification_6_0= ruleSpecificationCS ) )
            // InternalCompleteOCL.g:383:4: (lv_ownedSpecification_6_0= ruleSpecificationCS )
            {
            // InternalCompleteOCL.g:383:4: (lv_ownedSpecification_6_0= ruleSpecificationCS )
            // InternalCompleteOCL.g:384:5: lv_ownedSpecification_6_0= ruleSpecificationCS
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getInvConstraintCSAccess().getOwnedSpecificationSpecificationCSParserRuleCall_3_0());

            }
            pushFollow(FollowSets000.FOLLOW_2);
            lv_ownedSpecification_6_0=ruleSpecificationCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getInvConstraintCSRule());
              					}
              					set(
              						current,
              						"ownedSpecification",
              						lv_ownedSpecification_6_0,
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
    // $ANTLR end "ruleinvConstraintCS"


    // $ANTLR start "entryRulepreConstraintCS"
    // InternalCompleteOCL.g:405:1: entryRulepreConstraintCS returns [EObject current=null] : iv_rulepreConstraintCS= rulepreConstraintCS EOF ;
    public final EObject entryRulepreConstraintCS() throws RecognitionException {
        EObject current = null;

        EObject iv_rulepreConstraintCS = null;


        try {
            // InternalCompleteOCL.g:405:56: (iv_rulepreConstraintCS= rulepreConstraintCS EOF )
            // InternalCompleteOCL.g:406:2: iv_rulepreConstraintCS= rulepreConstraintCS EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getPreConstraintCSRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_rulepreConstraintCS=rulepreConstraintCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_rulepreConstraintCS;
            }
            match(input,EOF,FollowSets000.FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulepreConstraintCS"


    // $ANTLR start "rulepreConstraintCS"
    // InternalCompleteOCL.g:412:1: rulepreConstraintCS returns [EObject current=null] : (otherlv_0= 'pre' ( ( (lv_name_1_0= ruleUnrestrictedName ) ) (otherlv_2= '(' ( (lv_ownedMessageSpecification_3_0= ruleSpecificationCS ) ) otherlv_4= ')' )? )? otherlv_5= ':' ( (lv_ownedSpecification_6_0= ruleSpecificationCS ) ) ) ;
    public final EObject rulepreConstraintCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        AntlrDatatypeRuleToken lv_name_1_0 = null;

        EObject lv_ownedMessageSpecification_3_0 = null;

        EObject lv_ownedSpecification_6_0 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:418:2: ( (otherlv_0= 'pre' ( ( (lv_name_1_0= ruleUnrestrictedName ) ) (otherlv_2= '(' ( (lv_ownedMessageSpecification_3_0= ruleSpecificationCS ) ) otherlv_4= ')' )? )? otherlv_5= ':' ( (lv_ownedSpecification_6_0= ruleSpecificationCS ) ) ) )
            // InternalCompleteOCL.g:419:2: (otherlv_0= 'pre' ( ( (lv_name_1_0= ruleUnrestrictedName ) ) (otherlv_2= '(' ( (lv_ownedMessageSpecification_3_0= ruleSpecificationCS ) ) otherlv_4= ')' )? )? otherlv_5= ':' ( (lv_ownedSpecification_6_0= ruleSpecificationCS ) ) )
            {
            // InternalCompleteOCL.g:419:2: (otherlv_0= 'pre' ( ( (lv_name_1_0= ruleUnrestrictedName ) ) (otherlv_2= '(' ( (lv_ownedMessageSpecification_3_0= ruleSpecificationCS ) ) otherlv_4= ')' )? )? otherlv_5= ':' ( (lv_ownedSpecification_6_0= ruleSpecificationCS ) ) )
            // InternalCompleteOCL.g:420:3: otherlv_0= 'pre' ( ( (lv_name_1_0= ruleUnrestrictedName ) ) (otherlv_2= '(' ( (lv_ownedMessageSpecification_3_0= ruleSpecificationCS ) ) otherlv_4= ')' )? )? otherlv_5= ':' ( (lv_ownedSpecification_6_0= ruleSpecificationCS ) )
            {
            otherlv_0=(Token)match(input,24,FollowSets000.FOLLOW_8); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getPreConstraintCSAccess().getPreKeyword_0());

            }
            // InternalCompleteOCL.g:424:3: ( ( (lv_name_1_0= ruleUnrestrictedName ) ) (otherlv_2= '(' ( (lv_ownedMessageSpecification_3_0= ruleSpecificationCS ) ) otherlv_4= ')' )? )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( ((LA10_0>=RULE_SIMPLE_ID && LA10_0<=RULE_ESCAPED_ID)||(LA10_0>=30 && LA10_0<=32)) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // InternalCompleteOCL.g:425:4: ( (lv_name_1_0= ruleUnrestrictedName ) ) (otherlv_2= '(' ( (lv_ownedMessageSpecification_3_0= ruleSpecificationCS ) ) otherlv_4= ')' )?
                    {
                    // InternalCompleteOCL.g:425:4: ( (lv_name_1_0= ruleUnrestrictedName ) )
                    // InternalCompleteOCL.g:426:5: (lv_name_1_0= ruleUnrestrictedName )
                    {
                    // InternalCompleteOCL.g:426:5: (lv_name_1_0= ruleUnrestrictedName )
                    // InternalCompleteOCL.g:427:6: lv_name_1_0= ruleUnrestrictedName
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getPreConstraintCSAccess().getNameUnrestrictedNameParserRuleCall_1_0_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_9);
                    lv_name_1_0=ruleUnrestrictedName();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElementForParent(grammarAccess.getPreConstraintCSRule());
                      						}
                      						set(
                      							current,
                      							"name",
                      							lv_name_1_0,
                      							"org.eclipse.ocl.xtext.completeocl.CompleteOCL.UnrestrictedName");
                      						afterParserOrEnumRuleCall();

                    }

                    }


                    }

                    // InternalCompleteOCL.g:444:4: (otherlv_2= '(' ( (lv_ownedMessageSpecification_3_0= ruleSpecificationCS ) ) otherlv_4= ')' )?
                    int alt9=2;
                    int LA9_0 = input.LA(1);

                    if ( (LA9_0==21) ) {
                        alt9=1;
                    }
                    switch (alt9) {
                        case 1 :
                            // InternalCompleteOCL.g:445:5: otherlv_2= '(' ( (lv_ownedMessageSpecification_3_0= ruleSpecificationCS ) ) otherlv_4= ')'
                            {
                            otherlv_2=(Token)match(input,21,FollowSets000.FOLLOW_10); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              					newLeafNode(otherlv_2, grammarAccess.getPreConstraintCSAccess().getLeftParenthesisKeyword_1_1_0());

                            }
                            // InternalCompleteOCL.g:449:5: ( (lv_ownedMessageSpecification_3_0= ruleSpecificationCS ) )
                            // InternalCompleteOCL.g:450:6: (lv_ownedMessageSpecification_3_0= ruleSpecificationCS )
                            {
                            // InternalCompleteOCL.g:450:6: (lv_ownedMessageSpecification_3_0= ruleSpecificationCS )
                            // InternalCompleteOCL.g:451:7: lv_ownedMessageSpecification_3_0= ruleSpecificationCS
                            {
                            if ( state.backtracking==0 ) {

                              							newCompositeNode(grammarAccess.getPreConstraintCSAccess().getOwnedMessageSpecificationSpecificationCSParserRuleCall_1_1_1_0());

                            }
                            pushFollow(FollowSets000.FOLLOW_11);
                            lv_ownedMessageSpecification_3_0=ruleSpecificationCS();

                            state._fsp--;
                            if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              							if (current==null) {
                              								current = createModelElementForParent(grammarAccess.getPreConstraintCSRule());
                              							}
                              							set(
                              								current,
                              								"ownedMessageSpecification",
                              								lv_ownedMessageSpecification_3_0,
                              								"org.eclipse.ocl.xtext.completeocl.CompleteOCL.SpecificationCS");
                              							afterParserOrEnumRuleCall();

                            }

                            }


                            }

                            otherlv_4=(Token)match(input,22,FollowSets000.FOLLOW_12); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              					newLeafNode(otherlv_4, grammarAccess.getPreConstraintCSAccess().getRightParenthesisKeyword_1_1_2());

                            }

                            }
                            break;

                    }


                    }
                    break;

            }

            otherlv_5=(Token)match(input,23,FollowSets000.FOLLOW_10); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_5, grammarAccess.getPreConstraintCSAccess().getColonKeyword_2());

            }
            // InternalCompleteOCL.g:478:3: ( (lv_ownedSpecification_6_0= ruleSpecificationCS ) )
            // InternalCompleteOCL.g:479:4: (lv_ownedSpecification_6_0= ruleSpecificationCS )
            {
            // InternalCompleteOCL.g:479:4: (lv_ownedSpecification_6_0= ruleSpecificationCS )
            // InternalCompleteOCL.g:480:5: lv_ownedSpecification_6_0= ruleSpecificationCS
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getPreConstraintCSAccess().getOwnedSpecificationSpecificationCSParserRuleCall_3_0());

            }
            pushFollow(FollowSets000.FOLLOW_2);
            lv_ownedSpecification_6_0=ruleSpecificationCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getPreConstraintCSRule());
              					}
              					set(
              						current,
              						"ownedSpecification",
              						lv_ownedSpecification_6_0,
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
    // $ANTLR end "rulepreConstraintCS"


    // $ANTLR start "entryRulepostConstraintCS"
    // InternalCompleteOCL.g:501:1: entryRulepostConstraintCS returns [EObject current=null] : iv_rulepostConstraintCS= rulepostConstraintCS EOF ;
    public final EObject entryRulepostConstraintCS() throws RecognitionException {
        EObject current = null;

        EObject iv_rulepostConstraintCS = null;


        try {
            // InternalCompleteOCL.g:501:57: (iv_rulepostConstraintCS= rulepostConstraintCS EOF )
            // InternalCompleteOCL.g:502:2: iv_rulepostConstraintCS= rulepostConstraintCS EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getPostConstraintCSRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_rulepostConstraintCS=rulepostConstraintCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_rulepostConstraintCS;
            }
            match(input,EOF,FollowSets000.FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulepostConstraintCS"


    // $ANTLR start "rulepostConstraintCS"
    // InternalCompleteOCL.g:508:1: rulepostConstraintCS returns [EObject current=null] : (otherlv_0= 'post' ( ( (lv_name_1_0= ruleUnrestrictedName ) ) (otherlv_2= '(' ( (lv_ownedMessageSpecification_3_0= ruleSpecificationCS ) ) otherlv_4= ')' )? )? otherlv_5= ':' ( (lv_ownedSpecification_6_0= ruleSpecificationCS ) ) ) ;
    public final EObject rulepostConstraintCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        AntlrDatatypeRuleToken lv_name_1_0 = null;

        EObject lv_ownedMessageSpecification_3_0 = null;

        EObject lv_ownedSpecification_6_0 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:514:2: ( (otherlv_0= 'post' ( ( (lv_name_1_0= ruleUnrestrictedName ) ) (otherlv_2= '(' ( (lv_ownedMessageSpecification_3_0= ruleSpecificationCS ) ) otherlv_4= ')' )? )? otherlv_5= ':' ( (lv_ownedSpecification_6_0= ruleSpecificationCS ) ) ) )
            // InternalCompleteOCL.g:515:2: (otherlv_0= 'post' ( ( (lv_name_1_0= ruleUnrestrictedName ) ) (otherlv_2= '(' ( (lv_ownedMessageSpecification_3_0= ruleSpecificationCS ) ) otherlv_4= ')' )? )? otherlv_5= ':' ( (lv_ownedSpecification_6_0= ruleSpecificationCS ) ) )
            {
            // InternalCompleteOCL.g:515:2: (otherlv_0= 'post' ( ( (lv_name_1_0= ruleUnrestrictedName ) ) (otherlv_2= '(' ( (lv_ownedMessageSpecification_3_0= ruleSpecificationCS ) ) otherlv_4= ')' )? )? otherlv_5= ':' ( (lv_ownedSpecification_6_0= ruleSpecificationCS ) ) )
            // InternalCompleteOCL.g:516:3: otherlv_0= 'post' ( ( (lv_name_1_0= ruleUnrestrictedName ) ) (otherlv_2= '(' ( (lv_ownedMessageSpecification_3_0= ruleSpecificationCS ) ) otherlv_4= ')' )? )? otherlv_5= ':' ( (lv_ownedSpecification_6_0= ruleSpecificationCS ) )
            {
            otherlv_0=(Token)match(input,25,FollowSets000.FOLLOW_8); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getPostConstraintCSAccess().getPostKeyword_0());

            }
            // InternalCompleteOCL.g:520:3: ( ( (lv_name_1_0= ruleUnrestrictedName ) ) (otherlv_2= '(' ( (lv_ownedMessageSpecification_3_0= ruleSpecificationCS ) ) otherlv_4= ')' )? )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( ((LA12_0>=RULE_SIMPLE_ID && LA12_0<=RULE_ESCAPED_ID)||(LA12_0>=30 && LA12_0<=32)) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // InternalCompleteOCL.g:521:4: ( (lv_name_1_0= ruleUnrestrictedName ) ) (otherlv_2= '(' ( (lv_ownedMessageSpecification_3_0= ruleSpecificationCS ) ) otherlv_4= ')' )?
                    {
                    // InternalCompleteOCL.g:521:4: ( (lv_name_1_0= ruleUnrestrictedName ) )
                    // InternalCompleteOCL.g:522:5: (lv_name_1_0= ruleUnrestrictedName )
                    {
                    // InternalCompleteOCL.g:522:5: (lv_name_1_0= ruleUnrestrictedName )
                    // InternalCompleteOCL.g:523:6: lv_name_1_0= ruleUnrestrictedName
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getPostConstraintCSAccess().getNameUnrestrictedNameParserRuleCall_1_0_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_9);
                    lv_name_1_0=ruleUnrestrictedName();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElementForParent(grammarAccess.getPostConstraintCSRule());
                      						}
                      						set(
                      							current,
                      							"name",
                      							lv_name_1_0,
                      							"org.eclipse.ocl.xtext.completeocl.CompleteOCL.UnrestrictedName");
                      						afterParserOrEnumRuleCall();

                    }

                    }


                    }

                    // InternalCompleteOCL.g:540:4: (otherlv_2= '(' ( (lv_ownedMessageSpecification_3_0= ruleSpecificationCS ) ) otherlv_4= ')' )?
                    int alt11=2;
                    int LA11_0 = input.LA(1);

                    if ( (LA11_0==21) ) {
                        alt11=1;
                    }
                    switch (alt11) {
                        case 1 :
                            // InternalCompleteOCL.g:541:5: otherlv_2= '(' ( (lv_ownedMessageSpecification_3_0= ruleSpecificationCS ) ) otherlv_4= ')'
                            {
                            otherlv_2=(Token)match(input,21,FollowSets000.FOLLOW_10); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              					newLeafNode(otherlv_2, grammarAccess.getPostConstraintCSAccess().getLeftParenthesisKeyword_1_1_0());

                            }
                            // InternalCompleteOCL.g:545:5: ( (lv_ownedMessageSpecification_3_0= ruleSpecificationCS ) )
                            // InternalCompleteOCL.g:546:6: (lv_ownedMessageSpecification_3_0= ruleSpecificationCS )
                            {
                            // InternalCompleteOCL.g:546:6: (lv_ownedMessageSpecification_3_0= ruleSpecificationCS )
                            // InternalCompleteOCL.g:547:7: lv_ownedMessageSpecification_3_0= ruleSpecificationCS
                            {
                            if ( state.backtracking==0 ) {

                              							newCompositeNode(grammarAccess.getPostConstraintCSAccess().getOwnedMessageSpecificationSpecificationCSParserRuleCall_1_1_1_0());

                            }
                            pushFollow(FollowSets000.FOLLOW_11);
                            lv_ownedMessageSpecification_3_0=ruleSpecificationCS();

                            state._fsp--;
                            if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              							if (current==null) {
                              								current = createModelElementForParent(grammarAccess.getPostConstraintCSRule());
                              							}
                              							set(
                              								current,
                              								"ownedMessageSpecification",
                              								lv_ownedMessageSpecification_3_0,
                              								"org.eclipse.ocl.xtext.completeocl.CompleteOCL.SpecificationCS");
                              							afterParserOrEnumRuleCall();

                            }

                            }


                            }

                            otherlv_4=(Token)match(input,22,FollowSets000.FOLLOW_12); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              					newLeafNode(otherlv_4, grammarAccess.getPostConstraintCSAccess().getRightParenthesisKeyword_1_1_2());

                            }

                            }
                            break;

                    }


                    }
                    break;

            }

            otherlv_5=(Token)match(input,23,FollowSets000.FOLLOW_10); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_5, grammarAccess.getPostConstraintCSAccess().getColonKeyword_2());

            }
            // InternalCompleteOCL.g:574:3: ( (lv_ownedSpecification_6_0= ruleSpecificationCS ) )
            // InternalCompleteOCL.g:575:4: (lv_ownedSpecification_6_0= ruleSpecificationCS )
            {
            // InternalCompleteOCL.g:575:4: (lv_ownedSpecification_6_0= ruleSpecificationCS )
            // InternalCompleteOCL.g:576:5: lv_ownedSpecification_6_0= ruleSpecificationCS
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getPostConstraintCSAccess().getOwnedSpecificationSpecificationCSParserRuleCall_3_0());

            }
            pushFollow(FollowSets000.FOLLOW_2);
            lv_ownedSpecification_6_0=ruleSpecificationCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getPostConstraintCSRule());
              					}
              					set(
              						current,
              						"ownedSpecification",
              						lv_ownedSpecification_6_0,
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
    // $ANTLR end "rulepostConstraintCS"


    // $ANTLR start "entryRuleContextDeclCS"
    // InternalCompleteOCL.g:597:1: entryRuleContextDeclCS returns [EObject current=null] : iv_ruleContextDeclCS= ruleContextDeclCS EOF ;
    public final EObject entryRuleContextDeclCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleContextDeclCS = null;


        try {
            // InternalCompleteOCL.g:597:54: (iv_ruleContextDeclCS= ruleContextDeclCS EOF )
            // InternalCompleteOCL.g:598:2: iv_ruleContextDeclCS= ruleContextDeclCS EOF
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
    // InternalCompleteOCL.g:604:1: ruleContextDeclCS returns [EObject current=null] : (this_PropertyContextDeclCS_0= rulePropertyContextDeclCS | this_ClassifierContextDeclCS_1= ruleClassifierContextDeclCS | this_OperationContextDeclCS_2= ruleOperationContextDeclCS ) ;
    public final EObject ruleContextDeclCS() throws RecognitionException {
        EObject current = null;

        EObject this_PropertyContextDeclCS_0 = null;

        EObject this_ClassifierContextDeclCS_1 = null;

        EObject this_OperationContextDeclCS_2 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:610:2: ( (this_PropertyContextDeclCS_0= rulePropertyContextDeclCS | this_ClassifierContextDeclCS_1= ruleClassifierContextDeclCS | this_OperationContextDeclCS_2= ruleOperationContextDeclCS ) )
            // InternalCompleteOCL.g:611:2: (this_PropertyContextDeclCS_0= rulePropertyContextDeclCS | this_ClassifierContextDeclCS_1= ruleClassifierContextDeclCS | this_OperationContextDeclCS_2= ruleOperationContextDeclCS )
            {
            // InternalCompleteOCL.g:611:2: (this_PropertyContextDeclCS_0= rulePropertyContextDeclCS | this_ClassifierContextDeclCS_1= ruleClassifierContextDeclCS | this_OperationContextDeclCS_2= ruleOperationContextDeclCS )
            int alt13=3;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==19) ) {
                int LA13_1 = input.LA(2);

                if ( (synpred15_InternalCompleteOCL()) ) {
                    alt13=1;
                }
                else if ( (synpred16_InternalCompleteOCL()) ) {
                    alt13=2;
                }
                else if ( (true) ) {
                    alt13=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return current;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 13, 1, input);

                    throw nvae;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 13, 0, input);

                throw nvae;
            }
            switch (alt13) {
                case 1 :
                    // InternalCompleteOCL.g:612:3: this_PropertyContextDeclCS_0= rulePropertyContextDeclCS
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
                    // InternalCompleteOCL.g:624:3: this_ClassifierContextDeclCS_1= ruleClassifierContextDeclCS
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
                    // InternalCompleteOCL.g:636:3: this_OperationContextDeclCS_2= ruleOperationContextDeclCS
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
    // InternalCompleteOCL.g:651:1: entryRuleDefCS returns [EObject current=null] : iv_ruleDefCS= ruleDefCS EOF ;
    public final EObject entryRuleDefCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDefCS = null;


        try {
            // InternalCompleteOCL.g:651:46: (iv_ruleDefCS= ruleDefCS EOF )
            // InternalCompleteOCL.g:652:2: iv_ruleDefCS= ruleDefCS EOF
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
    // InternalCompleteOCL.g:658:1: ruleDefCS returns [EObject current=null] : (this_DefOperationCS_0= ruleDefOperationCS | this_DefPropertyCS_1= ruleDefPropertyCS ) ;
    public final EObject ruleDefCS() throws RecognitionException {
        EObject current = null;

        EObject this_DefOperationCS_0 = null;

        EObject this_DefPropertyCS_1 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:664:2: ( (this_DefOperationCS_0= ruleDefOperationCS | this_DefPropertyCS_1= ruleDefPropertyCS ) )
            // InternalCompleteOCL.g:665:2: (this_DefOperationCS_0= ruleDefOperationCS | this_DefPropertyCS_1= ruleDefPropertyCS )
            {
            // InternalCompleteOCL.g:665:2: (this_DefOperationCS_0= ruleDefOperationCS | this_DefPropertyCS_1= ruleDefPropertyCS )
            int alt14=2;
            alt14 = dfa14.predict(input);
            switch (alt14) {
                case 1 :
                    // InternalCompleteOCL.g:666:3: this_DefOperationCS_0= ruleDefOperationCS
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
                    // InternalCompleteOCL.g:678:3: this_DefPropertyCS_1= ruleDefPropertyCS
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
    // InternalCompleteOCL.g:693:1: entryRuleDefOperationCS returns [EObject current=null] : iv_ruleDefOperationCS= ruleDefOperationCS EOF ;
    public final EObject entryRuleDefOperationCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDefOperationCS = null;


        try {
            // InternalCompleteOCL.g:693:55: (iv_ruleDefOperationCS= ruleDefOperationCS EOF )
            // InternalCompleteOCL.g:694:2: iv_ruleDefOperationCS= ruleDefOperationCS EOF
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
    // InternalCompleteOCL.g:700:1: ruleDefOperationCS returns [EObject current=null] : ( ( (lv_isStatic_0_0= 'static' ) )? otherlv_1= 'def' ( ruleUnrestrictedName )? otherlv_3= ':' ( (lv_ownedSignature_4_0= ruleTemplateSignatureCS ) )? ( (lv_name_5_0= ruleUnrestrictedName ) ) otherlv_6= '(' ( ( (lv_ownedParameters_7_0= ruleDefParameterCS ) ) (otherlv_8= ',' ( (lv_ownedParameters_9_0= ruleDefParameterCS ) ) )* )? otherlv_10= ')' otherlv_11= ':' ( (lv_ownedType_12_0= ruleTypeExpCS ) )? otherlv_13= '=' ( (lv_ownedSpecification_14_0= ruleSpecificationCS ) ) ) ;
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
            // InternalCompleteOCL.g:706:2: ( ( ( (lv_isStatic_0_0= 'static' ) )? otherlv_1= 'def' ( ruleUnrestrictedName )? otherlv_3= ':' ( (lv_ownedSignature_4_0= ruleTemplateSignatureCS ) )? ( (lv_name_5_0= ruleUnrestrictedName ) ) otherlv_6= '(' ( ( (lv_ownedParameters_7_0= ruleDefParameterCS ) ) (otherlv_8= ',' ( (lv_ownedParameters_9_0= ruleDefParameterCS ) ) )* )? otherlv_10= ')' otherlv_11= ':' ( (lv_ownedType_12_0= ruleTypeExpCS ) )? otherlv_13= '=' ( (lv_ownedSpecification_14_0= ruleSpecificationCS ) ) ) )
            // InternalCompleteOCL.g:707:2: ( ( (lv_isStatic_0_0= 'static' ) )? otherlv_1= 'def' ( ruleUnrestrictedName )? otherlv_3= ':' ( (lv_ownedSignature_4_0= ruleTemplateSignatureCS ) )? ( (lv_name_5_0= ruleUnrestrictedName ) ) otherlv_6= '(' ( ( (lv_ownedParameters_7_0= ruleDefParameterCS ) ) (otherlv_8= ',' ( (lv_ownedParameters_9_0= ruleDefParameterCS ) ) )* )? otherlv_10= ')' otherlv_11= ':' ( (lv_ownedType_12_0= ruleTypeExpCS ) )? otherlv_13= '=' ( (lv_ownedSpecification_14_0= ruleSpecificationCS ) ) )
            {
            // InternalCompleteOCL.g:707:2: ( ( (lv_isStatic_0_0= 'static' ) )? otherlv_1= 'def' ( ruleUnrestrictedName )? otherlv_3= ':' ( (lv_ownedSignature_4_0= ruleTemplateSignatureCS ) )? ( (lv_name_5_0= ruleUnrestrictedName ) ) otherlv_6= '(' ( ( (lv_ownedParameters_7_0= ruleDefParameterCS ) ) (otherlv_8= ',' ( (lv_ownedParameters_9_0= ruleDefParameterCS ) ) )* )? otherlv_10= ')' otherlv_11= ':' ( (lv_ownedType_12_0= ruleTypeExpCS ) )? otherlv_13= '=' ( (lv_ownedSpecification_14_0= ruleSpecificationCS ) ) )
            // InternalCompleteOCL.g:708:3: ( (lv_isStatic_0_0= 'static' ) )? otherlv_1= 'def' ( ruleUnrestrictedName )? otherlv_3= ':' ( (lv_ownedSignature_4_0= ruleTemplateSignatureCS ) )? ( (lv_name_5_0= ruleUnrestrictedName ) ) otherlv_6= '(' ( ( (lv_ownedParameters_7_0= ruleDefParameterCS ) ) (otherlv_8= ',' ( (lv_ownedParameters_9_0= ruleDefParameterCS ) ) )* )? otherlv_10= ')' otherlv_11= ':' ( (lv_ownedType_12_0= ruleTypeExpCS ) )? otherlv_13= '=' ( (lv_ownedSpecification_14_0= ruleSpecificationCS ) )
            {
            // InternalCompleteOCL.g:708:3: ( (lv_isStatic_0_0= 'static' ) )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==26) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // InternalCompleteOCL.g:709:4: (lv_isStatic_0_0= 'static' )
                    {
                    // InternalCompleteOCL.g:709:4: (lv_isStatic_0_0= 'static' )
                    // InternalCompleteOCL.g:710:5: lv_isStatic_0_0= 'static'
                    {
                    lv_isStatic_0_0=(Token)match(input,26,FollowSets000.FOLLOW_13); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					newLeafNode(lv_isStatic_0_0, grammarAccess.getDefOperationCSAccess().getIsStaticStaticKeyword_0_0());

                    }
                    if ( state.backtracking==0 ) {

                      					if (current==null) {
                      						current = createModelElement(grammarAccess.getDefOperationCSRule());
                      					}
                      					setWithLastConsumed(current, "isStatic", lv_isStatic_0_0 != null, "static");

                    }

                    }


                    }
                    break;

            }

            otherlv_1=(Token)match(input,27,FollowSets000.FOLLOW_8); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getDefOperationCSAccess().getDefKeyword_1());

            }
            // InternalCompleteOCL.g:726:3: ( ruleUnrestrictedName )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( ((LA16_0>=RULE_SIMPLE_ID && LA16_0<=RULE_ESCAPED_ID)||(LA16_0>=30 && LA16_0<=32)) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // InternalCompleteOCL.g:727:4: ruleUnrestrictedName
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

            otherlv_3=(Token)match(input,23,FollowSets000.FOLLOW_14); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_3, grammarAccess.getDefOperationCSAccess().getColonKeyword_3());

            }
            // InternalCompleteOCL.g:742:3: ( (lv_ownedSignature_4_0= ruleTemplateSignatureCS ) )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==21||LA17_0==39) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // InternalCompleteOCL.g:743:4: (lv_ownedSignature_4_0= ruleTemplateSignatureCS )
                    {
                    // InternalCompleteOCL.g:743:4: (lv_ownedSignature_4_0= ruleTemplateSignatureCS )
                    // InternalCompleteOCL.g:744:5: lv_ownedSignature_4_0= ruleTemplateSignatureCS
                    {
                    if ( state.backtracking==0 ) {

                      					newCompositeNode(grammarAccess.getDefOperationCSAccess().getOwnedSignatureTemplateSignatureCSParserRuleCall_4_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_15);
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

            // InternalCompleteOCL.g:761:3: ( (lv_name_5_0= ruleUnrestrictedName ) )
            // InternalCompleteOCL.g:762:4: (lv_name_5_0= ruleUnrestrictedName )
            {
            // InternalCompleteOCL.g:762:4: (lv_name_5_0= ruleUnrestrictedName )
            // InternalCompleteOCL.g:763:5: lv_name_5_0= ruleUnrestrictedName
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getDefOperationCSAccess().getNameUnrestrictedNameParserRuleCall_5_0());

            }
            pushFollow(FollowSets000.FOLLOW_16);
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

            otherlv_6=(Token)match(input,21,FollowSets000.FOLLOW_17); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_6, grammarAccess.getDefOperationCSAccess().getLeftParenthesisKeyword_6());

            }
            // InternalCompleteOCL.g:784:3: ( ( (lv_ownedParameters_7_0= ruleDefParameterCS ) ) (otherlv_8= ',' ( (lv_ownedParameters_9_0= ruleDefParameterCS ) ) )* )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( ((LA19_0>=RULE_SIMPLE_ID && LA19_0<=RULE_ESCAPED_ID)||(LA19_0>=30 && LA19_0<=32)) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // InternalCompleteOCL.g:785:4: ( (lv_ownedParameters_7_0= ruleDefParameterCS ) ) (otherlv_8= ',' ( (lv_ownedParameters_9_0= ruleDefParameterCS ) ) )*
                    {
                    // InternalCompleteOCL.g:785:4: ( (lv_ownedParameters_7_0= ruleDefParameterCS ) )
                    // InternalCompleteOCL.g:786:5: (lv_ownedParameters_7_0= ruleDefParameterCS )
                    {
                    // InternalCompleteOCL.g:786:5: (lv_ownedParameters_7_0= ruleDefParameterCS )
                    // InternalCompleteOCL.g:787:6: lv_ownedParameters_7_0= ruleDefParameterCS
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getDefOperationCSAccess().getOwnedParametersDefParameterCSParserRuleCall_7_0_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_18);
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

                    // InternalCompleteOCL.g:804:4: (otherlv_8= ',' ( (lv_ownedParameters_9_0= ruleDefParameterCS ) ) )*
                    loop18:
                    do {
                        int alt18=2;
                        int LA18_0 = input.LA(1);

                        if ( (LA18_0==28) ) {
                            alt18=1;
                        }


                        switch (alt18) {
                    	case 1 :
                    	    // InternalCompleteOCL.g:805:5: otherlv_8= ',' ( (lv_ownedParameters_9_0= ruleDefParameterCS ) )
                    	    {
                    	    otherlv_8=(Token)match(input,28,FollowSets000.FOLLOW_15); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      					newLeafNode(otherlv_8, grammarAccess.getDefOperationCSAccess().getCommaKeyword_7_1_0());

                    	    }
                    	    // InternalCompleteOCL.g:809:5: ( (lv_ownedParameters_9_0= ruleDefParameterCS ) )
                    	    // InternalCompleteOCL.g:810:6: (lv_ownedParameters_9_0= ruleDefParameterCS )
                    	    {
                    	    // InternalCompleteOCL.g:810:6: (lv_ownedParameters_9_0= ruleDefParameterCS )
                    	    // InternalCompleteOCL.g:811:7: lv_ownedParameters_9_0= ruleDefParameterCS
                    	    {
                    	    if ( state.backtracking==0 ) {

                    	      							newCompositeNode(grammarAccess.getDefOperationCSAccess().getOwnedParametersDefParameterCSParserRuleCall_7_1_1_0());

                    	    }
                    	    pushFollow(FollowSets000.FOLLOW_18);
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
                    	    break loop18;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_10=(Token)match(input,22,FollowSets000.FOLLOW_12); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_10, grammarAccess.getDefOperationCSAccess().getRightParenthesisKeyword_8());

            }
            otherlv_11=(Token)match(input,23,FollowSets000.FOLLOW_19); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_11, grammarAccess.getDefOperationCSAccess().getColonKeyword_9());

            }
            // InternalCompleteOCL.g:838:3: ( (lv_ownedType_12_0= ruleTypeExpCS ) )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( ((LA20_0>=RULE_SIMPLE_ID && LA20_0<=RULE_ESCAPED_ID)||(LA20_0>=30 && LA20_0<=32)||(LA20_0>=42 && LA20_0<=51)||(LA20_0>=73 && LA20_0<=74)||(LA20_0>=76 && LA20_0<=80)) ) {
                alt20=1;
            }
            switch (alt20) {
                case 1 :
                    // InternalCompleteOCL.g:839:4: (lv_ownedType_12_0= ruleTypeExpCS )
                    {
                    // InternalCompleteOCL.g:839:4: (lv_ownedType_12_0= ruleTypeExpCS )
                    // InternalCompleteOCL.g:840:5: lv_ownedType_12_0= ruleTypeExpCS
                    {
                    if ( state.backtracking==0 ) {

                      					newCompositeNode(grammarAccess.getDefOperationCSAccess().getOwnedTypeTypeExpCSParserRuleCall_10_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_20);
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

            otherlv_13=(Token)match(input,29,FollowSets000.FOLLOW_10); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_13, grammarAccess.getDefOperationCSAccess().getEqualsSignKeyword_11());

            }
            // InternalCompleteOCL.g:861:3: ( (lv_ownedSpecification_14_0= ruleSpecificationCS ) )
            // InternalCompleteOCL.g:862:4: (lv_ownedSpecification_14_0= ruleSpecificationCS )
            {
            // InternalCompleteOCL.g:862:4: (lv_ownedSpecification_14_0= ruleSpecificationCS )
            // InternalCompleteOCL.g:863:5: lv_ownedSpecification_14_0= ruleSpecificationCS
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
    // InternalCompleteOCL.g:884:1: entryRuleDefParameterCS returns [EObject current=null] : iv_ruleDefParameterCS= ruleDefParameterCS EOF ;
    public final EObject entryRuleDefParameterCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDefParameterCS = null;


        try {
            // InternalCompleteOCL.g:884:55: (iv_ruleDefParameterCS= ruleDefParameterCS EOF )
            // InternalCompleteOCL.g:885:2: iv_ruleDefParameterCS= ruleDefParameterCS EOF
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
    // InternalCompleteOCL.g:891:1: ruleDefParameterCS returns [EObject current=null] : ( ( (lv_name_0_0= ruleUnrestrictedName ) ) otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) ) ) ;
    public final EObject ruleDefParameterCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_name_0_0 = null;

        EObject lv_ownedType_2_0 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:897:2: ( ( ( (lv_name_0_0= ruleUnrestrictedName ) ) otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) ) ) )
            // InternalCompleteOCL.g:898:2: ( ( (lv_name_0_0= ruleUnrestrictedName ) ) otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) ) )
            {
            // InternalCompleteOCL.g:898:2: ( ( (lv_name_0_0= ruleUnrestrictedName ) ) otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) ) )
            // InternalCompleteOCL.g:899:3: ( (lv_name_0_0= ruleUnrestrictedName ) ) otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) )
            {
            // InternalCompleteOCL.g:899:3: ( (lv_name_0_0= ruleUnrestrictedName ) )
            // InternalCompleteOCL.g:900:4: (lv_name_0_0= ruleUnrestrictedName )
            {
            // InternalCompleteOCL.g:900:4: (lv_name_0_0= ruleUnrestrictedName )
            // InternalCompleteOCL.g:901:5: lv_name_0_0= ruleUnrestrictedName
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

            otherlv_1=(Token)match(input,23,FollowSets000.FOLLOW_21); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getDefParameterCSAccess().getColonKeyword_1());

            }
            // InternalCompleteOCL.g:922:3: ( (lv_ownedType_2_0= ruleTypeExpCS ) )
            // InternalCompleteOCL.g:923:4: (lv_ownedType_2_0= ruleTypeExpCS )
            {
            // InternalCompleteOCL.g:923:4: (lv_ownedType_2_0= ruleTypeExpCS )
            // InternalCompleteOCL.g:924:5: lv_ownedType_2_0= ruleTypeExpCS
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
    // InternalCompleteOCL.g:945:1: entryRuleDefPropertyCS returns [EObject current=null] : iv_ruleDefPropertyCS= ruleDefPropertyCS EOF ;
    public final EObject entryRuleDefPropertyCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDefPropertyCS = null;


        try {
            // InternalCompleteOCL.g:945:54: (iv_ruleDefPropertyCS= ruleDefPropertyCS EOF )
            // InternalCompleteOCL.g:946:2: iv_ruleDefPropertyCS= ruleDefPropertyCS EOF
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
    // InternalCompleteOCL.g:952:1: ruleDefPropertyCS returns [EObject current=null] : ( ( (lv_isStatic_0_0= 'static' ) )? otherlv_1= 'def' ( ruleUnrestrictedName )? otherlv_3= ':' ( (lv_name_4_0= ruleUnrestrictedName ) ) otherlv_5= ':' ( (lv_ownedType_6_0= ruleTypeExpCS ) ) otherlv_7= '=' ( (lv_ownedSpecification_8_0= ruleSpecificationCS ) ) ) ;
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
            // InternalCompleteOCL.g:958:2: ( ( ( (lv_isStatic_0_0= 'static' ) )? otherlv_1= 'def' ( ruleUnrestrictedName )? otherlv_3= ':' ( (lv_name_4_0= ruleUnrestrictedName ) ) otherlv_5= ':' ( (lv_ownedType_6_0= ruleTypeExpCS ) ) otherlv_7= '=' ( (lv_ownedSpecification_8_0= ruleSpecificationCS ) ) ) )
            // InternalCompleteOCL.g:959:2: ( ( (lv_isStatic_0_0= 'static' ) )? otherlv_1= 'def' ( ruleUnrestrictedName )? otherlv_3= ':' ( (lv_name_4_0= ruleUnrestrictedName ) ) otherlv_5= ':' ( (lv_ownedType_6_0= ruleTypeExpCS ) ) otherlv_7= '=' ( (lv_ownedSpecification_8_0= ruleSpecificationCS ) ) )
            {
            // InternalCompleteOCL.g:959:2: ( ( (lv_isStatic_0_0= 'static' ) )? otherlv_1= 'def' ( ruleUnrestrictedName )? otherlv_3= ':' ( (lv_name_4_0= ruleUnrestrictedName ) ) otherlv_5= ':' ( (lv_ownedType_6_0= ruleTypeExpCS ) ) otherlv_7= '=' ( (lv_ownedSpecification_8_0= ruleSpecificationCS ) ) )
            // InternalCompleteOCL.g:960:3: ( (lv_isStatic_0_0= 'static' ) )? otherlv_1= 'def' ( ruleUnrestrictedName )? otherlv_3= ':' ( (lv_name_4_0= ruleUnrestrictedName ) ) otherlv_5= ':' ( (lv_ownedType_6_0= ruleTypeExpCS ) ) otherlv_7= '=' ( (lv_ownedSpecification_8_0= ruleSpecificationCS ) )
            {
            // InternalCompleteOCL.g:960:3: ( (lv_isStatic_0_0= 'static' ) )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==26) ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // InternalCompleteOCL.g:961:4: (lv_isStatic_0_0= 'static' )
                    {
                    // InternalCompleteOCL.g:961:4: (lv_isStatic_0_0= 'static' )
                    // InternalCompleteOCL.g:962:5: lv_isStatic_0_0= 'static'
                    {
                    lv_isStatic_0_0=(Token)match(input,26,FollowSets000.FOLLOW_13); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					newLeafNode(lv_isStatic_0_0, grammarAccess.getDefPropertyCSAccess().getIsStaticStaticKeyword_0_0());

                    }
                    if ( state.backtracking==0 ) {

                      					if (current==null) {
                      						current = createModelElement(grammarAccess.getDefPropertyCSRule());
                      					}
                      					setWithLastConsumed(current, "isStatic", lv_isStatic_0_0 != null, "static");

                    }

                    }


                    }
                    break;

            }

            otherlv_1=(Token)match(input,27,FollowSets000.FOLLOW_8); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getDefPropertyCSAccess().getDefKeyword_1());

            }
            // InternalCompleteOCL.g:978:3: ( ruleUnrestrictedName )?
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( ((LA22_0>=RULE_SIMPLE_ID && LA22_0<=RULE_ESCAPED_ID)||(LA22_0>=30 && LA22_0<=32)) ) {
                alt22=1;
            }
            switch (alt22) {
                case 1 :
                    // InternalCompleteOCL.g:979:4: ruleUnrestrictedName
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

            otherlv_3=(Token)match(input,23,FollowSets000.FOLLOW_15); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_3, grammarAccess.getDefPropertyCSAccess().getColonKeyword_3());

            }
            // InternalCompleteOCL.g:994:3: ( (lv_name_4_0= ruleUnrestrictedName ) )
            // InternalCompleteOCL.g:995:4: (lv_name_4_0= ruleUnrestrictedName )
            {
            // InternalCompleteOCL.g:995:4: (lv_name_4_0= ruleUnrestrictedName )
            // InternalCompleteOCL.g:996:5: lv_name_4_0= ruleUnrestrictedName
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

            otherlv_5=(Token)match(input,23,FollowSets000.FOLLOW_21); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_5, grammarAccess.getDefPropertyCSAccess().getColonKeyword_5());

            }
            // InternalCompleteOCL.g:1017:3: ( (lv_ownedType_6_0= ruleTypeExpCS ) )
            // InternalCompleteOCL.g:1018:4: (lv_ownedType_6_0= ruleTypeExpCS )
            {
            // InternalCompleteOCL.g:1018:4: (lv_ownedType_6_0= ruleTypeExpCS )
            // InternalCompleteOCL.g:1019:5: lv_ownedType_6_0= ruleTypeExpCS
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getDefPropertyCSAccess().getOwnedTypeTypeExpCSParserRuleCall_6_0());

            }
            pushFollow(FollowSets000.FOLLOW_20);
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

            otherlv_7=(Token)match(input,29,FollowSets000.FOLLOW_10); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_7, grammarAccess.getDefPropertyCSAccess().getEqualsSignKeyword_7());

            }
            // InternalCompleteOCL.g:1040:3: ( (lv_ownedSpecification_8_0= ruleSpecificationCS ) )
            // InternalCompleteOCL.g:1041:4: (lv_ownedSpecification_8_0= ruleSpecificationCS )
            {
            // InternalCompleteOCL.g:1041:4: (lv_ownedSpecification_8_0= ruleSpecificationCS )
            // InternalCompleteOCL.g:1042:5: lv_ownedSpecification_8_0= ruleSpecificationCS
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
    // InternalCompleteOCL.g:1063:1: entryRuleImportCS returns [EObject current=null] : iv_ruleImportCS= ruleImportCS EOF ;
    public final EObject entryRuleImportCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleImportCS = null;


        try {
            // InternalCompleteOCL.g:1063:49: (iv_ruleImportCS= ruleImportCS EOF )
            // InternalCompleteOCL.g:1064:2: iv_ruleImportCS= ruleImportCS EOF
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
    // InternalCompleteOCL.g:1070:1: ruleImportCS returns [EObject current=null] : ( (otherlv_0= 'import' | otherlv_1= 'include' | otherlv_2= 'library' ) ( ( (lv_name_3_0= ruleIdentifier ) ) otherlv_4= ':' )? ( (lv_ownedPathName_5_0= ruleURIPathNameCS ) ) ( (lv_isAll_6_0= '::*' ) )? ) ;
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
            // InternalCompleteOCL.g:1076:2: ( ( (otherlv_0= 'import' | otherlv_1= 'include' | otherlv_2= 'library' ) ( ( (lv_name_3_0= ruleIdentifier ) ) otherlv_4= ':' )? ( (lv_ownedPathName_5_0= ruleURIPathNameCS ) ) ( (lv_isAll_6_0= '::*' ) )? ) )
            // InternalCompleteOCL.g:1077:2: ( (otherlv_0= 'import' | otherlv_1= 'include' | otherlv_2= 'library' ) ( ( (lv_name_3_0= ruleIdentifier ) ) otherlv_4= ':' )? ( (lv_ownedPathName_5_0= ruleURIPathNameCS ) ) ( (lv_isAll_6_0= '::*' ) )? )
            {
            // InternalCompleteOCL.g:1077:2: ( (otherlv_0= 'import' | otherlv_1= 'include' | otherlv_2= 'library' ) ( ( (lv_name_3_0= ruleIdentifier ) ) otherlv_4= ':' )? ( (lv_ownedPathName_5_0= ruleURIPathNameCS ) ) ( (lv_isAll_6_0= '::*' ) )? )
            // InternalCompleteOCL.g:1078:3: (otherlv_0= 'import' | otherlv_1= 'include' | otherlv_2= 'library' ) ( ( (lv_name_3_0= ruleIdentifier ) ) otherlv_4= ':' )? ( (lv_ownedPathName_5_0= ruleURIPathNameCS ) ) ( (lv_isAll_6_0= '::*' ) )?
            {
            // InternalCompleteOCL.g:1078:3: (otherlv_0= 'import' | otherlv_1= 'include' | otherlv_2= 'library' )
            int alt23=3;
            switch ( input.LA(1) ) {
            case 30:
                {
                alt23=1;
                }
                break;
            case 31:
                {
                alt23=2;
                }
                break;
            case 32:
                {
                alt23=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 23, 0, input);

                throw nvae;
            }

            switch (alt23) {
                case 1 :
                    // InternalCompleteOCL.g:1079:4: otherlv_0= 'import'
                    {
                    otherlv_0=(Token)match(input,30,FollowSets000.FOLLOW_22); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_0, grammarAccess.getImportCSAccess().getImportKeyword_0_0());

                    }

                    }
                    break;
                case 2 :
                    // InternalCompleteOCL.g:1084:4: otherlv_1= 'include'
                    {
                    otherlv_1=(Token)match(input,31,FollowSets000.FOLLOW_22); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_1, grammarAccess.getImportCSAccess().getIncludeKeyword_0_1());

                    }

                    }
                    break;
                case 3 :
                    // InternalCompleteOCL.g:1089:4: otherlv_2= 'library'
                    {
                    otherlv_2=(Token)match(input,32,FollowSets000.FOLLOW_22); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_2, grammarAccess.getImportCSAccess().getLibraryKeyword_0_2());

                    }

                    }
                    break;

            }

            // InternalCompleteOCL.g:1094:3: ( ( (lv_name_3_0= ruleIdentifier ) ) otherlv_4= ':' )?
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( (LA24_0==RULE_SIMPLE_ID) ) {
                int LA24_1 = input.LA(2);

                if ( (LA24_1==23) ) {
                    alt24=1;
                }
            }
            else if ( (LA24_0==RULE_ESCAPED_ID) ) {
                int LA24_2 = input.LA(2);

                if ( (LA24_2==23) ) {
                    alt24=1;
                }
            }
            switch (alt24) {
                case 1 :
                    // InternalCompleteOCL.g:1095:4: ( (lv_name_3_0= ruleIdentifier ) ) otherlv_4= ':'
                    {
                    // InternalCompleteOCL.g:1095:4: ( (lv_name_3_0= ruleIdentifier ) )
                    // InternalCompleteOCL.g:1096:5: (lv_name_3_0= ruleIdentifier )
                    {
                    // InternalCompleteOCL.g:1096:5: (lv_name_3_0= ruleIdentifier )
                    // InternalCompleteOCL.g:1097:6: lv_name_3_0= ruleIdentifier
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

                    otherlv_4=(Token)match(input,23,FollowSets000.FOLLOW_22); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_4, grammarAccess.getImportCSAccess().getColonKeyword_1_1());

                    }

                    }
                    break;

            }

            // InternalCompleteOCL.g:1119:3: ( (lv_ownedPathName_5_0= ruleURIPathNameCS ) )
            // InternalCompleteOCL.g:1120:4: (lv_ownedPathName_5_0= ruleURIPathNameCS )
            {
            // InternalCompleteOCL.g:1120:4: (lv_ownedPathName_5_0= ruleURIPathNameCS )
            // InternalCompleteOCL.g:1121:5: lv_ownedPathName_5_0= ruleURIPathNameCS
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getImportCSAccess().getOwnedPathNameURIPathNameCSParserRuleCall_2_0());

            }
            pushFollow(FollowSets000.FOLLOW_23);
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

            // InternalCompleteOCL.g:1138:3: ( (lv_isAll_6_0= '::*' ) )?
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==33) ) {
                alt25=1;
            }
            switch (alt25) {
                case 1 :
                    // InternalCompleteOCL.g:1139:4: (lv_isAll_6_0= '::*' )
                    {
                    // InternalCompleteOCL.g:1139:4: (lv_isAll_6_0= '::*' )
                    // InternalCompleteOCL.g:1140:5: lv_isAll_6_0= '::*'
                    {
                    lv_isAll_6_0=(Token)match(input,33,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					newLeafNode(lv_isAll_6_0, grammarAccess.getImportCSAccess().getIsAllColonColonAsteriskKeyword_3_0());

                    }
                    if ( state.backtracking==0 ) {

                      					if (current==null) {
                      						current = createModelElement(grammarAccess.getImportCSRule());
                      					}
                      					setWithLastConsumed(current, "isAll", lv_isAll_6_0 != null, "::*");

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
    // InternalCompleteOCL.g:1156:1: entryRuleOperationContextDeclCS returns [EObject current=null] : iv_ruleOperationContextDeclCS= ruleOperationContextDeclCS EOF ;
    public final EObject entryRuleOperationContextDeclCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOperationContextDeclCS = null;


        try {
            // InternalCompleteOCL.g:1156:63: (iv_ruleOperationContextDeclCS= ruleOperationContextDeclCS EOF )
            // InternalCompleteOCL.g:1157:2: iv_ruleOperationContextDeclCS= ruleOperationContextDeclCS EOF
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
    // InternalCompleteOCL.g:1163:1: ruleOperationContextDeclCS returns [EObject current=null] : (otherlv_0= 'context' ( (lv_ownedSignature_1_0= ruleTemplateSignatureCS ) )? ( (lv_ownedPathName_2_0= ruleUnreservedPathNameCS ) ) otherlv_3= '(' ( ( (lv_ownedParameters_4_0= ruleParameterCS ) ) (otherlv_5= ',' ( (lv_ownedParameters_6_0= ruleParameterCS ) ) )* )? otherlv_7= ')' otherlv_8= ':' ( (lv_ownedType_9_0= ruleTypeExpCS ) )? ( ( (lv_ownedPreconditions_10_0= rulepreConstraintCS ) ) | ( (lv_ownedPostconditions_11_0= rulepostConstraintCS ) ) | ( (lv_ownedBodies_12_0= rulebodySpecificationCS ) ) )* ) ;
    public final EObject ruleOperationContextDeclCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_8=null;
        EObject lv_ownedSignature_1_0 = null;

        EObject lv_ownedPathName_2_0 = null;

        EObject lv_ownedParameters_4_0 = null;

        EObject lv_ownedParameters_6_0 = null;

        EObject lv_ownedType_9_0 = null;

        EObject lv_ownedPreconditions_10_0 = null;

        EObject lv_ownedPostconditions_11_0 = null;

        EObject lv_ownedBodies_12_0 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:1169:2: ( (otherlv_0= 'context' ( (lv_ownedSignature_1_0= ruleTemplateSignatureCS ) )? ( (lv_ownedPathName_2_0= ruleUnreservedPathNameCS ) ) otherlv_3= '(' ( ( (lv_ownedParameters_4_0= ruleParameterCS ) ) (otherlv_5= ',' ( (lv_ownedParameters_6_0= ruleParameterCS ) ) )* )? otherlv_7= ')' otherlv_8= ':' ( (lv_ownedType_9_0= ruleTypeExpCS ) )? ( ( (lv_ownedPreconditions_10_0= rulepreConstraintCS ) ) | ( (lv_ownedPostconditions_11_0= rulepostConstraintCS ) ) | ( (lv_ownedBodies_12_0= rulebodySpecificationCS ) ) )* ) )
            // InternalCompleteOCL.g:1170:2: (otherlv_0= 'context' ( (lv_ownedSignature_1_0= ruleTemplateSignatureCS ) )? ( (lv_ownedPathName_2_0= ruleUnreservedPathNameCS ) ) otherlv_3= '(' ( ( (lv_ownedParameters_4_0= ruleParameterCS ) ) (otherlv_5= ',' ( (lv_ownedParameters_6_0= ruleParameterCS ) ) )* )? otherlv_7= ')' otherlv_8= ':' ( (lv_ownedType_9_0= ruleTypeExpCS ) )? ( ( (lv_ownedPreconditions_10_0= rulepreConstraintCS ) ) | ( (lv_ownedPostconditions_11_0= rulepostConstraintCS ) ) | ( (lv_ownedBodies_12_0= rulebodySpecificationCS ) ) )* )
            {
            // InternalCompleteOCL.g:1170:2: (otherlv_0= 'context' ( (lv_ownedSignature_1_0= ruleTemplateSignatureCS ) )? ( (lv_ownedPathName_2_0= ruleUnreservedPathNameCS ) ) otherlv_3= '(' ( ( (lv_ownedParameters_4_0= ruleParameterCS ) ) (otherlv_5= ',' ( (lv_ownedParameters_6_0= ruleParameterCS ) ) )* )? otherlv_7= ')' otherlv_8= ':' ( (lv_ownedType_9_0= ruleTypeExpCS ) )? ( ( (lv_ownedPreconditions_10_0= rulepreConstraintCS ) ) | ( (lv_ownedPostconditions_11_0= rulepostConstraintCS ) ) | ( (lv_ownedBodies_12_0= rulebodySpecificationCS ) ) )* )
            // InternalCompleteOCL.g:1171:3: otherlv_0= 'context' ( (lv_ownedSignature_1_0= ruleTemplateSignatureCS ) )? ( (lv_ownedPathName_2_0= ruleUnreservedPathNameCS ) ) otherlv_3= '(' ( ( (lv_ownedParameters_4_0= ruleParameterCS ) ) (otherlv_5= ',' ( (lv_ownedParameters_6_0= ruleParameterCS ) ) )* )? otherlv_7= ')' otherlv_8= ':' ( (lv_ownedType_9_0= ruleTypeExpCS ) )? ( ( (lv_ownedPreconditions_10_0= rulepreConstraintCS ) ) | ( (lv_ownedPostconditions_11_0= rulepostConstraintCS ) ) | ( (lv_ownedBodies_12_0= rulebodySpecificationCS ) ) )*
            {
            otherlv_0=(Token)match(input,19,FollowSets000.FOLLOW_5); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getOperationContextDeclCSAccess().getContextKeyword_0());

            }
            // InternalCompleteOCL.g:1175:3: ( (lv_ownedSignature_1_0= ruleTemplateSignatureCS ) )?
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( (LA26_0==21||LA26_0==39) ) {
                alt26=1;
            }
            switch (alt26) {
                case 1 :
                    // InternalCompleteOCL.g:1176:4: (lv_ownedSignature_1_0= ruleTemplateSignatureCS )
                    {
                    // InternalCompleteOCL.g:1176:4: (lv_ownedSignature_1_0= ruleTemplateSignatureCS )
                    // InternalCompleteOCL.g:1177:5: lv_ownedSignature_1_0= ruleTemplateSignatureCS
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

            // InternalCompleteOCL.g:1194:3: ( (lv_ownedPathName_2_0= ruleUnreservedPathNameCS ) )
            // InternalCompleteOCL.g:1195:4: (lv_ownedPathName_2_0= ruleUnreservedPathNameCS )
            {
            // InternalCompleteOCL.g:1195:4: (lv_ownedPathName_2_0= ruleUnreservedPathNameCS )
            // InternalCompleteOCL.g:1196:5: lv_ownedPathName_2_0= ruleUnreservedPathNameCS
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getOperationContextDeclCSAccess().getOwnedPathNameUnreservedPathNameCSParserRuleCall_2_0());

            }
            pushFollow(FollowSets000.FOLLOW_16);
            lv_ownedPathName_2_0=ruleUnreservedPathNameCS();

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
              						"org.eclipse.ocl.xtext.base.Base.UnreservedPathNameCS");
              					afterParserOrEnumRuleCall();

            }

            }


            }

            otherlv_3=(Token)match(input,21,FollowSets000.FOLLOW_24); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_3, grammarAccess.getOperationContextDeclCSAccess().getLeftParenthesisKeyword_3());

            }
            // InternalCompleteOCL.g:1217:3: ( ( (lv_ownedParameters_4_0= ruleParameterCS ) ) (otherlv_5= ',' ( (lv_ownedParameters_6_0= ruleParameterCS ) ) )* )?
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( ((LA28_0>=RULE_SIMPLE_ID && LA28_0<=RULE_ESCAPED_ID)||(LA28_0>=30 && LA28_0<=32)||(LA28_0>=42 && LA28_0<=51)||(LA28_0>=73 && LA28_0<=74)||(LA28_0>=76 && LA28_0<=80)) ) {
                alt28=1;
            }
            switch (alt28) {
                case 1 :
                    // InternalCompleteOCL.g:1218:4: ( (lv_ownedParameters_4_0= ruleParameterCS ) ) (otherlv_5= ',' ( (lv_ownedParameters_6_0= ruleParameterCS ) ) )*
                    {
                    // InternalCompleteOCL.g:1218:4: ( (lv_ownedParameters_4_0= ruleParameterCS ) )
                    // InternalCompleteOCL.g:1219:5: (lv_ownedParameters_4_0= ruleParameterCS )
                    {
                    // InternalCompleteOCL.g:1219:5: (lv_ownedParameters_4_0= ruleParameterCS )
                    // InternalCompleteOCL.g:1220:6: lv_ownedParameters_4_0= ruleParameterCS
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getOperationContextDeclCSAccess().getOwnedParametersParameterCSParserRuleCall_4_0_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_18);
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

                    // InternalCompleteOCL.g:1237:4: (otherlv_5= ',' ( (lv_ownedParameters_6_0= ruleParameterCS ) ) )*
                    loop27:
                    do {
                        int alt27=2;
                        int LA27_0 = input.LA(1);

                        if ( (LA27_0==28) ) {
                            alt27=1;
                        }


                        switch (alt27) {
                    	case 1 :
                    	    // InternalCompleteOCL.g:1238:5: otherlv_5= ',' ( (lv_ownedParameters_6_0= ruleParameterCS ) )
                    	    {
                    	    otherlv_5=(Token)match(input,28,FollowSets000.FOLLOW_21); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      					newLeafNode(otherlv_5, grammarAccess.getOperationContextDeclCSAccess().getCommaKeyword_4_1_0());

                    	    }
                    	    // InternalCompleteOCL.g:1242:5: ( (lv_ownedParameters_6_0= ruleParameterCS ) )
                    	    // InternalCompleteOCL.g:1243:6: (lv_ownedParameters_6_0= ruleParameterCS )
                    	    {
                    	    // InternalCompleteOCL.g:1243:6: (lv_ownedParameters_6_0= ruleParameterCS )
                    	    // InternalCompleteOCL.g:1244:7: lv_ownedParameters_6_0= ruleParameterCS
                    	    {
                    	    if ( state.backtracking==0 ) {

                    	      							newCompositeNode(grammarAccess.getOperationContextDeclCSAccess().getOwnedParametersParameterCSParserRuleCall_4_1_1_0());

                    	    }
                    	    pushFollow(FollowSets000.FOLLOW_18);
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
                    	    break loop27;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_7=(Token)match(input,22,FollowSets000.FOLLOW_12); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_7, grammarAccess.getOperationContextDeclCSAccess().getRightParenthesisKeyword_5());

            }
            otherlv_8=(Token)match(input,23,FollowSets000.FOLLOW_25); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_8, grammarAccess.getOperationContextDeclCSAccess().getColonKeyword_6());

            }
            // InternalCompleteOCL.g:1271:3: ( (lv_ownedType_9_0= ruleTypeExpCS ) )?
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( ((LA29_0>=RULE_SIMPLE_ID && LA29_0<=RULE_ESCAPED_ID)||(LA29_0>=30 && LA29_0<=32)||(LA29_0>=42 && LA29_0<=51)||(LA29_0>=73 && LA29_0<=74)||(LA29_0>=76 && LA29_0<=80)) ) {
                alt29=1;
            }
            switch (alt29) {
                case 1 :
                    // InternalCompleteOCL.g:1272:4: (lv_ownedType_9_0= ruleTypeExpCS )
                    {
                    // InternalCompleteOCL.g:1272:4: (lv_ownedType_9_0= ruleTypeExpCS )
                    // InternalCompleteOCL.g:1273:5: lv_ownedType_9_0= ruleTypeExpCS
                    {
                    if ( state.backtracking==0 ) {

                      					newCompositeNode(grammarAccess.getOperationContextDeclCSAccess().getOwnedTypeTypeExpCSParserRuleCall_7_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_26);
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

            // InternalCompleteOCL.g:1290:3: ( ( (lv_ownedPreconditions_10_0= rulepreConstraintCS ) ) | ( (lv_ownedPostconditions_11_0= rulepostConstraintCS ) ) | ( (lv_ownedBodies_12_0= rulebodySpecificationCS ) ) )*
            loop30:
            do {
                int alt30=4;
                switch ( input.LA(1) ) {
                case 24:
                    {
                    alt30=1;
                    }
                    break;
                case 25:
                    {
                    alt30=2;
                    }
                    break;
                case RULE_UNQUOTED_STRING:
                case 36:
                    {
                    alt30=3;
                    }
                    break;

                }

                switch (alt30) {
            	case 1 :
            	    // InternalCompleteOCL.g:1291:4: ( (lv_ownedPreconditions_10_0= rulepreConstraintCS ) )
            	    {
            	    // InternalCompleteOCL.g:1291:4: ( (lv_ownedPreconditions_10_0= rulepreConstraintCS ) )
            	    // InternalCompleteOCL.g:1292:5: (lv_ownedPreconditions_10_0= rulepreConstraintCS )
            	    {
            	    // InternalCompleteOCL.g:1292:5: (lv_ownedPreconditions_10_0= rulepreConstraintCS )
            	    // InternalCompleteOCL.g:1293:6: lv_ownedPreconditions_10_0= rulepreConstraintCS
            	    {
            	    if ( state.backtracking==0 ) {

            	      						newCompositeNode(grammarAccess.getOperationContextDeclCSAccess().getOwnedPreconditionsPreConstraintCSParserRuleCall_8_0_0());

            	    }
            	    pushFollow(FollowSets000.FOLLOW_26);
            	    lv_ownedPreconditions_10_0=rulepreConstraintCS();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      						if (current==null) {
            	      							current = createModelElementForParent(grammarAccess.getOperationContextDeclCSRule());
            	      						}
            	      						add(
            	      							current,
            	      							"ownedPreconditions",
            	      							lv_ownedPreconditions_10_0,
            	      							"org.eclipse.ocl.xtext.completeocl.CompleteOCL.preConstraintCS");
            	      						afterParserOrEnumRuleCall();

            	    }

            	    }


            	    }


            	    }
            	    break;
            	case 2 :
            	    // InternalCompleteOCL.g:1311:4: ( (lv_ownedPostconditions_11_0= rulepostConstraintCS ) )
            	    {
            	    // InternalCompleteOCL.g:1311:4: ( (lv_ownedPostconditions_11_0= rulepostConstraintCS ) )
            	    // InternalCompleteOCL.g:1312:5: (lv_ownedPostconditions_11_0= rulepostConstraintCS )
            	    {
            	    // InternalCompleteOCL.g:1312:5: (lv_ownedPostconditions_11_0= rulepostConstraintCS )
            	    // InternalCompleteOCL.g:1313:6: lv_ownedPostconditions_11_0= rulepostConstraintCS
            	    {
            	    if ( state.backtracking==0 ) {

            	      						newCompositeNode(grammarAccess.getOperationContextDeclCSAccess().getOwnedPostconditionsPostConstraintCSParserRuleCall_8_1_0());

            	    }
            	    pushFollow(FollowSets000.FOLLOW_26);
            	    lv_ownedPostconditions_11_0=rulepostConstraintCS();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      						if (current==null) {
            	      							current = createModelElementForParent(grammarAccess.getOperationContextDeclCSRule());
            	      						}
            	      						add(
            	      							current,
            	      							"ownedPostconditions",
            	      							lv_ownedPostconditions_11_0,
            	      							"org.eclipse.ocl.xtext.completeocl.CompleteOCL.postConstraintCS");
            	      						afterParserOrEnumRuleCall();

            	    }

            	    }


            	    }


            	    }
            	    break;
            	case 3 :
            	    // InternalCompleteOCL.g:1331:4: ( (lv_ownedBodies_12_0= rulebodySpecificationCS ) )
            	    {
            	    // InternalCompleteOCL.g:1331:4: ( (lv_ownedBodies_12_0= rulebodySpecificationCS ) )
            	    // InternalCompleteOCL.g:1332:5: (lv_ownedBodies_12_0= rulebodySpecificationCS )
            	    {
            	    // InternalCompleteOCL.g:1332:5: (lv_ownedBodies_12_0= rulebodySpecificationCS )
            	    // InternalCompleteOCL.g:1333:6: lv_ownedBodies_12_0= rulebodySpecificationCS
            	    {
            	    if ( state.backtracking==0 ) {

            	      						newCompositeNode(grammarAccess.getOperationContextDeclCSAccess().getOwnedBodiesBodySpecificationCSParserRuleCall_8_2_0());

            	    }
            	    pushFollow(FollowSets000.FOLLOW_26);
            	    lv_ownedBodies_12_0=rulebodySpecificationCS();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      						if (current==null) {
            	      							current = createModelElementForParent(grammarAccess.getOperationContextDeclCSRule());
            	      						}
            	      						add(
            	      							current,
            	      							"ownedBodies",
            	      							lv_ownedBodies_12_0,
            	      							"org.eclipse.ocl.xtext.completeocl.CompleteOCL.bodySpecificationCS");
            	      						afterParserOrEnumRuleCall();

            	    }

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop30;
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
    // InternalCompleteOCL.g:1355:1: entryRulePackageDeclarationCS returns [EObject current=null] : iv_rulePackageDeclarationCS= rulePackageDeclarationCS EOF ;
    public final EObject entryRulePackageDeclarationCS() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePackageDeclarationCS = null;


        try {
            // InternalCompleteOCL.g:1355:61: (iv_rulePackageDeclarationCS= rulePackageDeclarationCS EOF )
            // InternalCompleteOCL.g:1356:2: iv_rulePackageDeclarationCS= rulePackageDeclarationCS EOF
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
    // InternalCompleteOCL.g:1362:1: rulePackageDeclarationCS returns [EObject current=null] : (otherlv_0= 'package' ( (lv_ownedPathName_1_0= ruleUnreservedPathNameCS ) ) ( (lv_ownedInvariants_2_0= ruleinvConstraintCS ) )* ( (lv_ownedContexts_3_0= ruleContextDeclCS ) )* otherlv_4= 'endpackage' ) ;
    public final EObject rulePackageDeclarationCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_4=null;
        EObject lv_ownedPathName_1_0 = null;

        EObject lv_ownedInvariants_2_0 = null;

        EObject lv_ownedContexts_3_0 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:1368:2: ( (otherlv_0= 'package' ( (lv_ownedPathName_1_0= ruleUnreservedPathNameCS ) ) ( (lv_ownedInvariants_2_0= ruleinvConstraintCS ) )* ( (lv_ownedContexts_3_0= ruleContextDeclCS ) )* otherlv_4= 'endpackage' ) )
            // InternalCompleteOCL.g:1369:2: (otherlv_0= 'package' ( (lv_ownedPathName_1_0= ruleUnreservedPathNameCS ) ) ( (lv_ownedInvariants_2_0= ruleinvConstraintCS ) )* ( (lv_ownedContexts_3_0= ruleContextDeclCS ) )* otherlv_4= 'endpackage' )
            {
            // InternalCompleteOCL.g:1369:2: (otherlv_0= 'package' ( (lv_ownedPathName_1_0= ruleUnreservedPathNameCS ) ) ( (lv_ownedInvariants_2_0= ruleinvConstraintCS ) )* ( (lv_ownedContexts_3_0= ruleContextDeclCS ) )* otherlv_4= 'endpackage' )
            // InternalCompleteOCL.g:1370:3: otherlv_0= 'package' ( (lv_ownedPathName_1_0= ruleUnreservedPathNameCS ) ) ( (lv_ownedInvariants_2_0= ruleinvConstraintCS ) )* ( (lv_ownedContexts_3_0= ruleContextDeclCS ) )* otherlv_4= 'endpackage'
            {
            otherlv_0=(Token)match(input,34,FollowSets000.FOLLOW_5); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getPackageDeclarationCSAccess().getPackageKeyword_0());

            }
            // InternalCompleteOCL.g:1374:3: ( (lv_ownedPathName_1_0= ruleUnreservedPathNameCS ) )
            // InternalCompleteOCL.g:1375:4: (lv_ownedPathName_1_0= ruleUnreservedPathNameCS )
            {
            // InternalCompleteOCL.g:1375:4: (lv_ownedPathName_1_0= ruleUnreservedPathNameCS )
            // InternalCompleteOCL.g:1376:5: lv_ownedPathName_1_0= ruleUnreservedPathNameCS
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getPackageDeclarationCSAccess().getOwnedPathNameUnreservedPathNameCSParserRuleCall_1_0());

            }
            pushFollow(FollowSets000.FOLLOW_27);
            lv_ownedPathName_1_0=ruleUnreservedPathNameCS();

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
              						"org.eclipse.ocl.xtext.base.Base.UnreservedPathNameCS");
              					afterParserOrEnumRuleCall();

            }

            }


            }

            // InternalCompleteOCL.g:1393:3: ( (lv_ownedInvariants_2_0= ruleinvConstraintCS ) )*
            loop31:
            do {
                int alt31=2;
                int LA31_0 = input.LA(1);

                if ( (LA31_0==20) ) {
                    alt31=1;
                }


                switch (alt31) {
            	case 1 :
            	    // InternalCompleteOCL.g:1394:4: (lv_ownedInvariants_2_0= ruleinvConstraintCS )
            	    {
            	    // InternalCompleteOCL.g:1394:4: (lv_ownedInvariants_2_0= ruleinvConstraintCS )
            	    // InternalCompleteOCL.g:1395:5: lv_ownedInvariants_2_0= ruleinvConstraintCS
            	    {
            	    if ( state.backtracking==0 ) {

            	      					newCompositeNode(grammarAccess.getPackageDeclarationCSAccess().getOwnedInvariantsInvConstraintCSParserRuleCall_2_0());

            	    }
            	    pushFollow(FollowSets000.FOLLOW_27);
            	    lv_ownedInvariants_2_0=ruleinvConstraintCS();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      					if (current==null) {
            	      						current = createModelElementForParent(grammarAccess.getPackageDeclarationCSRule());
            	      					}
            	      					add(
            	      						current,
            	      						"ownedInvariants",
            	      						lv_ownedInvariants_2_0,
            	      						"org.eclipse.ocl.xtext.completeocl.CompleteOCL.invConstraintCS");
            	      					afterParserOrEnumRuleCall();

            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop31;
                }
            } while (true);

            // InternalCompleteOCL.g:1412:3: ( (lv_ownedContexts_3_0= ruleContextDeclCS ) )*
            loop32:
            do {
                int alt32=2;
                int LA32_0 = input.LA(1);

                if ( (LA32_0==19) ) {
                    alt32=1;
                }


                switch (alt32) {
            	case 1 :
            	    // InternalCompleteOCL.g:1413:4: (lv_ownedContexts_3_0= ruleContextDeclCS )
            	    {
            	    // InternalCompleteOCL.g:1413:4: (lv_ownedContexts_3_0= ruleContextDeclCS )
            	    // InternalCompleteOCL.g:1414:5: lv_ownedContexts_3_0= ruleContextDeclCS
            	    {
            	    if ( state.backtracking==0 ) {

            	      					newCompositeNode(grammarAccess.getPackageDeclarationCSAccess().getOwnedContextsContextDeclCSParserRuleCall_3_0());

            	    }
            	    pushFollow(FollowSets000.FOLLOW_28);
            	    lv_ownedContexts_3_0=ruleContextDeclCS();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      					if (current==null) {
            	      						current = createModelElementForParent(grammarAccess.getPackageDeclarationCSRule());
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
            	    break;

            	default :
            	    break loop32;
                }
            } while (true);

            otherlv_4=(Token)match(input,35,FollowSets000.FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_4, grammarAccess.getPackageDeclarationCSAccess().getEndpackageKeyword_4());

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
    // InternalCompleteOCL.g:1439:1: entryRuleParameterCS returns [EObject current=null] : iv_ruleParameterCS= ruleParameterCS EOF ;
    public final EObject entryRuleParameterCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleParameterCS = null;


        try {
            // InternalCompleteOCL.g:1439:52: (iv_ruleParameterCS= ruleParameterCS EOF )
            // InternalCompleteOCL.g:1440:2: iv_ruleParameterCS= ruleParameterCS EOF
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
    // InternalCompleteOCL.g:1446:1: ruleParameterCS returns [EObject current=null] : ( ( ( (lv_name_0_0= ruleUnrestrictedName ) ) otherlv_1= ':' )? ( (lv_ownedType_2_0= ruleTypeExpCS ) ) ) ;
    public final EObject ruleParameterCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_name_0_0 = null;

        EObject lv_ownedType_2_0 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:1452:2: ( ( ( ( (lv_name_0_0= ruleUnrestrictedName ) ) otherlv_1= ':' )? ( (lv_ownedType_2_0= ruleTypeExpCS ) ) ) )
            // InternalCompleteOCL.g:1453:2: ( ( ( (lv_name_0_0= ruleUnrestrictedName ) ) otherlv_1= ':' )? ( (lv_ownedType_2_0= ruleTypeExpCS ) ) )
            {
            // InternalCompleteOCL.g:1453:2: ( ( ( (lv_name_0_0= ruleUnrestrictedName ) ) otherlv_1= ':' )? ( (lv_ownedType_2_0= ruleTypeExpCS ) ) )
            // InternalCompleteOCL.g:1454:3: ( ( (lv_name_0_0= ruleUnrestrictedName ) ) otherlv_1= ':' )? ( (lv_ownedType_2_0= ruleTypeExpCS ) )
            {
            // InternalCompleteOCL.g:1454:3: ( ( (lv_name_0_0= ruleUnrestrictedName ) ) otherlv_1= ':' )?
            int alt33=2;
            switch ( input.LA(1) ) {
                case RULE_SIMPLE_ID:
                    {
                    int LA33_1 = input.LA(2);

                    if ( (LA33_1==23) ) {
                        alt33=1;
                    }
                    }
                    break;
                case RULE_ESCAPED_ID:
                    {
                    int LA33_2 = input.LA(2);

                    if ( (LA33_2==23) ) {
                        alt33=1;
                    }
                    }
                    break;
                case 30:
                    {
                    int LA33_3 = input.LA(2);

                    if ( (LA33_3==23) ) {
                        alt33=1;
                    }
                    }
                    break;
                case 31:
                    {
                    int LA33_4 = input.LA(2);

                    if ( (LA33_4==23) ) {
                        alt33=1;
                    }
                    }
                    break;
                case 32:
                    {
                    int LA33_5 = input.LA(2);

                    if ( (LA33_5==23) ) {
                        alt33=1;
                    }
                    }
                    break;
            }

            switch (alt33) {
                case 1 :
                    // InternalCompleteOCL.g:1455:4: ( (lv_name_0_0= ruleUnrestrictedName ) ) otherlv_1= ':'
                    {
                    // InternalCompleteOCL.g:1455:4: ( (lv_name_0_0= ruleUnrestrictedName ) )
                    // InternalCompleteOCL.g:1456:5: (lv_name_0_0= ruleUnrestrictedName )
                    {
                    // InternalCompleteOCL.g:1456:5: (lv_name_0_0= ruleUnrestrictedName )
                    // InternalCompleteOCL.g:1457:6: lv_name_0_0= ruleUnrestrictedName
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

                    otherlv_1=(Token)match(input,23,FollowSets000.FOLLOW_21); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_1, grammarAccess.getParameterCSAccess().getColonKeyword_0_1());

                    }

                    }
                    break;

            }

            // InternalCompleteOCL.g:1479:3: ( (lv_ownedType_2_0= ruleTypeExpCS ) )
            // InternalCompleteOCL.g:1480:4: (lv_ownedType_2_0= ruleTypeExpCS )
            {
            // InternalCompleteOCL.g:1480:4: (lv_ownedType_2_0= ruleTypeExpCS )
            // InternalCompleteOCL.g:1481:5: lv_ownedType_2_0= ruleTypeExpCS
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
    // InternalCompleteOCL.g:1502:1: entryRulePropertyContextDeclCS returns [EObject current=null] : iv_rulePropertyContextDeclCS= rulePropertyContextDeclCS EOF ;
    public final EObject entryRulePropertyContextDeclCS() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePropertyContextDeclCS = null;


        try {
            // InternalCompleteOCL.g:1502:62: (iv_rulePropertyContextDeclCS= rulePropertyContextDeclCS EOF )
            // InternalCompleteOCL.g:1503:2: iv_rulePropertyContextDeclCS= rulePropertyContextDeclCS EOF
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
    // InternalCompleteOCL.g:1509:1: rulePropertyContextDeclCS returns [EObject current=null] : (otherlv_0= 'context' ( (lv_ownedPathName_1_0= ruleUnreservedPathNameCS ) ) otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) ( ( (lv_ownedDefaultExpressions_4_0= rulederiveSpecificationCS ) ) | ( (lv_ownedDefaultExpressions_5_0= ruleinitSpecificationCS ) ) )* ) ;
    public final EObject rulePropertyContextDeclCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        EObject lv_ownedPathName_1_0 = null;

        EObject lv_ownedType_3_0 = null;

        EObject lv_ownedDefaultExpressions_4_0 = null;

        EObject lv_ownedDefaultExpressions_5_0 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:1515:2: ( (otherlv_0= 'context' ( (lv_ownedPathName_1_0= ruleUnreservedPathNameCS ) ) otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) ( ( (lv_ownedDefaultExpressions_4_0= rulederiveSpecificationCS ) ) | ( (lv_ownedDefaultExpressions_5_0= ruleinitSpecificationCS ) ) )* ) )
            // InternalCompleteOCL.g:1516:2: (otherlv_0= 'context' ( (lv_ownedPathName_1_0= ruleUnreservedPathNameCS ) ) otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) ( ( (lv_ownedDefaultExpressions_4_0= rulederiveSpecificationCS ) ) | ( (lv_ownedDefaultExpressions_5_0= ruleinitSpecificationCS ) ) )* )
            {
            // InternalCompleteOCL.g:1516:2: (otherlv_0= 'context' ( (lv_ownedPathName_1_0= ruleUnreservedPathNameCS ) ) otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) ( ( (lv_ownedDefaultExpressions_4_0= rulederiveSpecificationCS ) ) | ( (lv_ownedDefaultExpressions_5_0= ruleinitSpecificationCS ) ) )* )
            // InternalCompleteOCL.g:1517:3: otherlv_0= 'context' ( (lv_ownedPathName_1_0= ruleUnreservedPathNameCS ) ) otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) ( ( (lv_ownedDefaultExpressions_4_0= rulederiveSpecificationCS ) ) | ( (lv_ownedDefaultExpressions_5_0= ruleinitSpecificationCS ) ) )*
            {
            otherlv_0=(Token)match(input,19,FollowSets000.FOLLOW_5); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getPropertyContextDeclCSAccess().getContextKeyword_0());

            }
            // InternalCompleteOCL.g:1521:3: ( (lv_ownedPathName_1_0= ruleUnreservedPathNameCS ) )
            // InternalCompleteOCL.g:1522:4: (lv_ownedPathName_1_0= ruleUnreservedPathNameCS )
            {
            // InternalCompleteOCL.g:1522:4: (lv_ownedPathName_1_0= ruleUnreservedPathNameCS )
            // InternalCompleteOCL.g:1523:5: lv_ownedPathName_1_0= ruleUnreservedPathNameCS
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getPropertyContextDeclCSAccess().getOwnedPathNameUnreservedPathNameCSParserRuleCall_1_0());

            }
            pushFollow(FollowSets000.FOLLOW_12);
            lv_ownedPathName_1_0=ruleUnreservedPathNameCS();

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
              						"org.eclipse.ocl.xtext.base.Base.UnreservedPathNameCS");
              					afterParserOrEnumRuleCall();

            }

            }


            }

            otherlv_2=(Token)match(input,23,FollowSets000.FOLLOW_21); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_2, grammarAccess.getPropertyContextDeclCSAccess().getColonKeyword_2());

            }
            // InternalCompleteOCL.g:1544:3: ( (lv_ownedType_3_0= ruleTypeExpCS ) )
            // InternalCompleteOCL.g:1545:4: (lv_ownedType_3_0= ruleTypeExpCS )
            {
            // InternalCompleteOCL.g:1545:4: (lv_ownedType_3_0= ruleTypeExpCS )
            // InternalCompleteOCL.g:1546:5: lv_ownedType_3_0= ruleTypeExpCS
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getPropertyContextDeclCSAccess().getOwnedTypeTypeExpCSParserRuleCall_3_0());

            }
            pushFollow(FollowSets000.FOLLOW_29);
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

            // InternalCompleteOCL.g:1563:3: ( ( (lv_ownedDefaultExpressions_4_0= rulederiveSpecificationCS ) ) | ( (lv_ownedDefaultExpressions_5_0= ruleinitSpecificationCS ) ) )*
            loop34:
            do {
                int alt34=3;
                switch ( input.LA(1) ) {
                case 37:
                    {
                    alt34=1;
                    }
                    break;
                case RULE_UNQUOTED_STRING:
                    {
                    int LA34_3 = input.LA(2);

                    if ( (synpred40_InternalCompleteOCL()) ) {
                        alt34=1;
                    }
                    else if ( (synpred41_InternalCompleteOCL()) ) {
                        alt34=2;
                    }


                    }
                    break;
                case 38:
                    {
                    alt34=2;
                    }
                    break;

                }

                switch (alt34) {
            	case 1 :
            	    // InternalCompleteOCL.g:1564:4: ( (lv_ownedDefaultExpressions_4_0= rulederiveSpecificationCS ) )
            	    {
            	    // InternalCompleteOCL.g:1564:4: ( (lv_ownedDefaultExpressions_4_0= rulederiveSpecificationCS ) )
            	    // InternalCompleteOCL.g:1565:5: (lv_ownedDefaultExpressions_4_0= rulederiveSpecificationCS )
            	    {
            	    // InternalCompleteOCL.g:1565:5: (lv_ownedDefaultExpressions_4_0= rulederiveSpecificationCS )
            	    // InternalCompleteOCL.g:1566:6: lv_ownedDefaultExpressions_4_0= rulederiveSpecificationCS
            	    {
            	    if ( state.backtracking==0 ) {

            	      						newCompositeNode(grammarAccess.getPropertyContextDeclCSAccess().getOwnedDefaultExpressionsDeriveSpecificationCSParserRuleCall_4_0_0());

            	    }
            	    pushFollow(FollowSets000.FOLLOW_29);
            	    lv_ownedDefaultExpressions_4_0=rulederiveSpecificationCS();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      						if (current==null) {
            	      							current = createModelElementForParent(grammarAccess.getPropertyContextDeclCSRule());
            	      						}
            	      						add(
            	      							current,
            	      							"ownedDefaultExpressions",
            	      							lv_ownedDefaultExpressions_4_0,
            	      							"org.eclipse.ocl.xtext.completeocl.CompleteOCL.deriveSpecificationCS");
            	      						afterParserOrEnumRuleCall();

            	    }

            	    }


            	    }


            	    }
            	    break;
            	case 2 :
            	    // InternalCompleteOCL.g:1584:4: ( (lv_ownedDefaultExpressions_5_0= ruleinitSpecificationCS ) )
            	    {
            	    // InternalCompleteOCL.g:1584:4: ( (lv_ownedDefaultExpressions_5_0= ruleinitSpecificationCS ) )
            	    // InternalCompleteOCL.g:1585:5: (lv_ownedDefaultExpressions_5_0= ruleinitSpecificationCS )
            	    {
            	    // InternalCompleteOCL.g:1585:5: (lv_ownedDefaultExpressions_5_0= ruleinitSpecificationCS )
            	    // InternalCompleteOCL.g:1586:6: lv_ownedDefaultExpressions_5_0= ruleinitSpecificationCS
            	    {
            	    if ( state.backtracking==0 ) {

            	      						newCompositeNode(grammarAccess.getPropertyContextDeclCSAccess().getOwnedDefaultExpressionsInitSpecificationCSParserRuleCall_4_1_0());

            	    }
            	    pushFollow(FollowSets000.FOLLOW_29);
            	    lv_ownedDefaultExpressions_5_0=ruleinitSpecificationCS();

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
            	      							"org.eclipse.ocl.xtext.completeocl.CompleteOCL.initSpecificationCS");
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


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
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
    // InternalCompleteOCL.g:1608:1: entryRuleSpecificationCS returns [EObject current=null] : iv_ruleSpecificationCS= ruleSpecificationCS EOF ;
    public final EObject entryRuleSpecificationCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSpecificationCS = null;


        try {
            // InternalCompleteOCL.g:1608:56: (iv_ruleSpecificationCS= ruleSpecificationCS EOF )
            // InternalCompleteOCL.g:1609:2: iv_ruleSpecificationCS= ruleSpecificationCS EOF
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
    // InternalCompleteOCL.g:1615:1: ruleSpecificationCS returns [EObject current=null] : ( ( (lv_ownedExpression_0_0= ruleExpCS ) ) | ( (lv_exprString_1_0= RULE_UNQUOTED_STRING ) ) ) ;
    public final EObject ruleSpecificationCS() throws RecognitionException {
        EObject current = null;

        Token lv_exprString_1_0=null;
        EObject lv_ownedExpression_0_0 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:1621:2: ( ( ( (lv_ownedExpression_0_0= ruleExpCS ) ) | ( (lv_exprString_1_0= RULE_UNQUOTED_STRING ) ) ) )
            // InternalCompleteOCL.g:1622:2: ( ( (lv_ownedExpression_0_0= ruleExpCS ) ) | ( (lv_exprString_1_0= RULE_UNQUOTED_STRING ) ) )
            {
            // InternalCompleteOCL.g:1622:2: ( ( (lv_ownedExpression_0_0= ruleExpCS ) ) | ( (lv_exprString_1_0= RULE_UNQUOTED_STRING ) ) )
            int alt35=2;
            int LA35_0 = input.LA(1);

            if ( ((LA35_0>=RULE_SIMPLE_ID && LA35_0<=RULE_SINGLE_QUOTED_STRING)||LA35_0==21||(LA35_0>=30 && LA35_0<=32)||(LA35_0>=42 && LA35_0<=55)||(LA35_0>=73 && LA35_0<=74)||(LA35_0>=76 && LA35_0<=80)||LA35_0==85||(LA35_0>=88 && LA35_0<=91)||LA35_0==98||(LA35_0>=103 && LA35_0<=104)) ) {
                alt35=1;
            }
            else if ( (LA35_0==RULE_UNQUOTED_STRING) ) {
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
                    // InternalCompleteOCL.g:1623:3: ( (lv_ownedExpression_0_0= ruleExpCS ) )
                    {
                    // InternalCompleteOCL.g:1623:3: ( (lv_ownedExpression_0_0= ruleExpCS ) )
                    // InternalCompleteOCL.g:1624:4: (lv_ownedExpression_0_0= ruleExpCS )
                    {
                    // InternalCompleteOCL.g:1624:4: (lv_ownedExpression_0_0= ruleExpCS )
                    // InternalCompleteOCL.g:1625:5: lv_ownedExpression_0_0= ruleExpCS
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
                    // InternalCompleteOCL.g:1643:3: ( (lv_exprString_1_0= RULE_UNQUOTED_STRING ) )
                    {
                    // InternalCompleteOCL.g:1643:3: ( (lv_exprString_1_0= RULE_UNQUOTED_STRING ) )
                    // InternalCompleteOCL.g:1644:4: (lv_exprString_1_0= RULE_UNQUOTED_STRING )
                    {
                    // InternalCompleteOCL.g:1644:4: (lv_exprString_1_0= RULE_UNQUOTED_STRING )
                    // InternalCompleteOCL.g:1645:5: lv_exprString_1_0= RULE_UNQUOTED_STRING
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


    // $ANTLR start "entryRulebodySpecificationCS"
    // InternalCompleteOCL.g:1665:1: entryRulebodySpecificationCS returns [EObject current=null] : iv_rulebodySpecificationCS= rulebodySpecificationCS EOF ;
    public final EObject entryRulebodySpecificationCS() throws RecognitionException {
        EObject current = null;

        EObject iv_rulebodySpecificationCS = null;


        try {
            // InternalCompleteOCL.g:1665:60: (iv_rulebodySpecificationCS= rulebodySpecificationCS EOF )
            // InternalCompleteOCL.g:1666:2: iv_rulebodySpecificationCS= rulebodySpecificationCS EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getBodySpecificationCSRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_rulebodySpecificationCS=rulebodySpecificationCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_rulebodySpecificationCS;
            }
            match(input,EOF,FollowSets000.FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulebodySpecificationCS"


    // $ANTLR start "rulebodySpecificationCS"
    // InternalCompleteOCL.g:1672:1: rulebodySpecificationCS returns [EObject current=null] : ( (otherlv_0= 'body' ( ruleUnrestrictedName )? otherlv_2= ':' ( (lv_ownedExpression_3_0= ruleExpCS ) ) ) | ( (lv_exprString_4_0= RULE_UNQUOTED_STRING ) ) ) ;
    public final EObject rulebodySpecificationCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token lv_exprString_4_0=null;
        EObject lv_ownedExpression_3_0 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:1678:2: ( ( (otherlv_0= 'body' ( ruleUnrestrictedName )? otherlv_2= ':' ( (lv_ownedExpression_3_0= ruleExpCS ) ) ) | ( (lv_exprString_4_0= RULE_UNQUOTED_STRING ) ) ) )
            // InternalCompleteOCL.g:1679:2: ( (otherlv_0= 'body' ( ruleUnrestrictedName )? otherlv_2= ':' ( (lv_ownedExpression_3_0= ruleExpCS ) ) ) | ( (lv_exprString_4_0= RULE_UNQUOTED_STRING ) ) )
            {
            // InternalCompleteOCL.g:1679:2: ( (otherlv_0= 'body' ( ruleUnrestrictedName )? otherlv_2= ':' ( (lv_ownedExpression_3_0= ruleExpCS ) ) ) | ( (lv_exprString_4_0= RULE_UNQUOTED_STRING ) ) )
            int alt37=2;
            int LA37_0 = input.LA(1);

            if ( (LA37_0==36) ) {
                alt37=1;
            }
            else if ( (LA37_0==RULE_UNQUOTED_STRING) ) {
                alt37=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 37, 0, input);

                throw nvae;
            }
            switch (alt37) {
                case 1 :
                    // InternalCompleteOCL.g:1680:3: (otherlv_0= 'body' ( ruleUnrestrictedName )? otherlv_2= ':' ( (lv_ownedExpression_3_0= ruleExpCS ) ) )
                    {
                    // InternalCompleteOCL.g:1680:3: (otherlv_0= 'body' ( ruleUnrestrictedName )? otherlv_2= ':' ( (lv_ownedExpression_3_0= ruleExpCS ) ) )
                    // InternalCompleteOCL.g:1681:4: otherlv_0= 'body' ( ruleUnrestrictedName )? otherlv_2= ':' ( (lv_ownedExpression_3_0= ruleExpCS ) )
                    {
                    otherlv_0=(Token)match(input,36,FollowSets000.FOLLOW_8); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_0, grammarAccess.getBodySpecificationCSAccess().getBodyKeyword_0_0());

                    }
                    // InternalCompleteOCL.g:1685:4: ( ruleUnrestrictedName )?
                    int alt36=2;
                    int LA36_0 = input.LA(1);

                    if ( ((LA36_0>=RULE_SIMPLE_ID && LA36_0<=RULE_ESCAPED_ID)||(LA36_0>=30 && LA36_0<=32)) ) {
                        alt36=1;
                    }
                    switch (alt36) {
                        case 1 :
                            // InternalCompleteOCL.g:1686:5: ruleUnrestrictedName
                            {
                            if ( state.backtracking==0 ) {

                              					/* */

                            }
                            if ( state.backtracking==0 ) {

                              					newCompositeNode(grammarAccess.getBodySpecificationCSAccess().getUnrestrictedNameParserRuleCall_0_1());

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

                    otherlv_2=(Token)match(input,23,FollowSets000.FOLLOW_30); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_2, grammarAccess.getBodySpecificationCSAccess().getColonKeyword_0_2());

                    }
                    // InternalCompleteOCL.g:1701:4: ( (lv_ownedExpression_3_0= ruleExpCS ) )
                    // InternalCompleteOCL.g:1702:5: (lv_ownedExpression_3_0= ruleExpCS )
                    {
                    // InternalCompleteOCL.g:1702:5: (lv_ownedExpression_3_0= ruleExpCS )
                    // InternalCompleteOCL.g:1703:6: lv_ownedExpression_3_0= ruleExpCS
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getBodySpecificationCSAccess().getOwnedExpressionExpCSParserRuleCall_0_3_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    lv_ownedExpression_3_0=ruleExpCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElementForParent(grammarAccess.getBodySpecificationCSRule());
                      						}
                      						set(
                      							current,
                      							"ownedExpression",
                      							lv_ownedExpression_3_0,
                      							"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.ExpCS");
                      						afterParserOrEnumRuleCall();

                    }

                    }


                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalCompleteOCL.g:1722:3: ( (lv_exprString_4_0= RULE_UNQUOTED_STRING ) )
                    {
                    // InternalCompleteOCL.g:1722:3: ( (lv_exprString_4_0= RULE_UNQUOTED_STRING ) )
                    // InternalCompleteOCL.g:1723:4: (lv_exprString_4_0= RULE_UNQUOTED_STRING )
                    {
                    // InternalCompleteOCL.g:1723:4: (lv_exprString_4_0= RULE_UNQUOTED_STRING )
                    // InternalCompleteOCL.g:1724:5: lv_exprString_4_0= RULE_UNQUOTED_STRING
                    {
                    lv_exprString_4_0=(Token)match(input,RULE_UNQUOTED_STRING,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					newLeafNode(lv_exprString_4_0, grammarAccess.getBodySpecificationCSAccess().getExprStringUNQUOTED_STRINGTerminalRuleCall_1_0());

                    }
                    if ( state.backtracking==0 ) {

                      					if (current==null) {
                      						current = createModelElement(grammarAccess.getBodySpecificationCSRule());
                      					}
                      					setWithLastConsumed(
                      						current,
                      						"exprString",
                      						lv_exprString_4_0,
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
    // $ANTLR end "rulebodySpecificationCS"


    // $ANTLR start "entryRulederiveSpecificationCS"
    // InternalCompleteOCL.g:1744:1: entryRulederiveSpecificationCS returns [EObject current=null] : iv_rulederiveSpecificationCS= rulederiveSpecificationCS EOF ;
    public final EObject entryRulederiveSpecificationCS() throws RecognitionException {
        EObject current = null;

        EObject iv_rulederiveSpecificationCS = null;


        try {
            // InternalCompleteOCL.g:1744:62: (iv_rulederiveSpecificationCS= rulederiveSpecificationCS EOF )
            // InternalCompleteOCL.g:1745:2: iv_rulederiveSpecificationCS= rulederiveSpecificationCS EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getDeriveSpecificationCSRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_rulederiveSpecificationCS=rulederiveSpecificationCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_rulederiveSpecificationCS;
            }
            match(input,EOF,FollowSets000.FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulederiveSpecificationCS"


    // $ANTLR start "rulederiveSpecificationCS"
    // InternalCompleteOCL.g:1751:1: rulederiveSpecificationCS returns [EObject current=null] : ( (otherlv_0= 'derive' ( ruleUnrestrictedName )? otherlv_2= ':' ( (lv_ownedExpression_3_0= ruleExpCS ) ) ) | ( (lv_exprString_4_0= RULE_UNQUOTED_STRING ) ) ) ;
    public final EObject rulederiveSpecificationCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token lv_exprString_4_0=null;
        EObject lv_ownedExpression_3_0 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:1757:2: ( ( (otherlv_0= 'derive' ( ruleUnrestrictedName )? otherlv_2= ':' ( (lv_ownedExpression_3_0= ruleExpCS ) ) ) | ( (lv_exprString_4_0= RULE_UNQUOTED_STRING ) ) ) )
            // InternalCompleteOCL.g:1758:2: ( (otherlv_0= 'derive' ( ruleUnrestrictedName )? otherlv_2= ':' ( (lv_ownedExpression_3_0= ruleExpCS ) ) ) | ( (lv_exprString_4_0= RULE_UNQUOTED_STRING ) ) )
            {
            // InternalCompleteOCL.g:1758:2: ( (otherlv_0= 'derive' ( ruleUnrestrictedName )? otherlv_2= ':' ( (lv_ownedExpression_3_0= ruleExpCS ) ) ) | ( (lv_exprString_4_0= RULE_UNQUOTED_STRING ) ) )
            int alt39=2;
            int LA39_0 = input.LA(1);

            if ( (LA39_0==37) ) {
                alt39=1;
            }
            else if ( (LA39_0==RULE_UNQUOTED_STRING) ) {
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
                    // InternalCompleteOCL.g:1759:3: (otherlv_0= 'derive' ( ruleUnrestrictedName )? otherlv_2= ':' ( (lv_ownedExpression_3_0= ruleExpCS ) ) )
                    {
                    // InternalCompleteOCL.g:1759:3: (otherlv_0= 'derive' ( ruleUnrestrictedName )? otherlv_2= ':' ( (lv_ownedExpression_3_0= ruleExpCS ) ) )
                    // InternalCompleteOCL.g:1760:4: otherlv_0= 'derive' ( ruleUnrestrictedName )? otherlv_2= ':' ( (lv_ownedExpression_3_0= ruleExpCS ) )
                    {
                    otherlv_0=(Token)match(input,37,FollowSets000.FOLLOW_8); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_0, grammarAccess.getDeriveSpecificationCSAccess().getDeriveKeyword_0_0());

                    }
                    // InternalCompleteOCL.g:1764:4: ( ruleUnrestrictedName )?
                    int alt38=2;
                    int LA38_0 = input.LA(1);

                    if ( ((LA38_0>=RULE_SIMPLE_ID && LA38_0<=RULE_ESCAPED_ID)||(LA38_0>=30 && LA38_0<=32)) ) {
                        alt38=1;
                    }
                    switch (alt38) {
                        case 1 :
                            // InternalCompleteOCL.g:1765:5: ruleUnrestrictedName
                            {
                            if ( state.backtracking==0 ) {

                              					/* */

                            }
                            if ( state.backtracking==0 ) {

                              					newCompositeNode(grammarAccess.getDeriveSpecificationCSAccess().getUnrestrictedNameParserRuleCall_0_1());

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

                    otherlv_2=(Token)match(input,23,FollowSets000.FOLLOW_30); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_2, grammarAccess.getDeriveSpecificationCSAccess().getColonKeyword_0_2());

                    }
                    // InternalCompleteOCL.g:1780:4: ( (lv_ownedExpression_3_0= ruleExpCS ) )
                    // InternalCompleteOCL.g:1781:5: (lv_ownedExpression_3_0= ruleExpCS )
                    {
                    // InternalCompleteOCL.g:1781:5: (lv_ownedExpression_3_0= ruleExpCS )
                    // InternalCompleteOCL.g:1782:6: lv_ownedExpression_3_0= ruleExpCS
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getDeriveSpecificationCSAccess().getOwnedExpressionExpCSParserRuleCall_0_3_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    lv_ownedExpression_3_0=ruleExpCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElementForParent(grammarAccess.getDeriveSpecificationCSRule());
                      						}
                      						set(
                      							current,
                      							"ownedExpression",
                      							lv_ownedExpression_3_0,
                      							"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.ExpCS");
                      						afterParserOrEnumRuleCall();

                    }

                    }


                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalCompleteOCL.g:1801:3: ( (lv_exprString_4_0= RULE_UNQUOTED_STRING ) )
                    {
                    // InternalCompleteOCL.g:1801:3: ( (lv_exprString_4_0= RULE_UNQUOTED_STRING ) )
                    // InternalCompleteOCL.g:1802:4: (lv_exprString_4_0= RULE_UNQUOTED_STRING )
                    {
                    // InternalCompleteOCL.g:1802:4: (lv_exprString_4_0= RULE_UNQUOTED_STRING )
                    // InternalCompleteOCL.g:1803:5: lv_exprString_4_0= RULE_UNQUOTED_STRING
                    {
                    lv_exprString_4_0=(Token)match(input,RULE_UNQUOTED_STRING,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					newLeafNode(lv_exprString_4_0, grammarAccess.getDeriveSpecificationCSAccess().getExprStringUNQUOTED_STRINGTerminalRuleCall_1_0());

                    }
                    if ( state.backtracking==0 ) {

                      					if (current==null) {
                      						current = createModelElement(grammarAccess.getDeriveSpecificationCSRule());
                      					}
                      					setWithLastConsumed(
                      						current,
                      						"exprString",
                      						lv_exprString_4_0,
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
    // $ANTLR end "rulederiveSpecificationCS"


    // $ANTLR start "entryRuleinitSpecificationCS"
    // InternalCompleteOCL.g:1823:1: entryRuleinitSpecificationCS returns [EObject current=null] : iv_ruleinitSpecificationCS= ruleinitSpecificationCS EOF ;
    public final EObject entryRuleinitSpecificationCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleinitSpecificationCS = null;


        try {
            // InternalCompleteOCL.g:1823:60: (iv_ruleinitSpecificationCS= ruleinitSpecificationCS EOF )
            // InternalCompleteOCL.g:1824:2: iv_ruleinitSpecificationCS= ruleinitSpecificationCS EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getInitSpecificationCSRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleinitSpecificationCS=ruleinitSpecificationCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleinitSpecificationCS;
            }
            match(input,EOF,FollowSets000.FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleinitSpecificationCS"


    // $ANTLR start "ruleinitSpecificationCS"
    // InternalCompleteOCL.g:1830:1: ruleinitSpecificationCS returns [EObject current=null] : ( (otherlv_0= 'init' ( ruleUnrestrictedName )? otherlv_2= ':' ( (lv_ownedExpression_3_0= ruleExpCS ) ) ) | ( (lv_exprString_4_0= RULE_UNQUOTED_STRING ) ) ) ;
    public final EObject ruleinitSpecificationCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token lv_exprString_4_0=null;
        EObject lv_ownedExpression_3_0 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:1836:2: ( ( (otherlv_0= 'init' ( ruleUnrestrictedName )? otherlv_2= ':' ( (lv_ownedExpression_3_0= ruleExpCS ) ) ) | ( (lv_exprString_4_0= RULE_UNQUOTED_STRING ) ) ) )
            // InternalCompleteOCL.g:1837:2: ( (otherlv_0= 'init' ( ruleUnrestrictedName )? otherlv_2= ':' ( (lv_ownedExpression_3_0= ruleExpCS ) ) ) | ( (lv_exprString_4_0= RULE_UNQUOTED_STRING ) ) )
            {
            // InternalCompleteOCL.g:1837:2: ( (otherlv_0= 'init' ( ruleUnrestrictedName )? otherlv_2= ':' ( (lv_ownedExpression_3_0= ruleExpCS ) ) ) | ( (lv_exprString_4_0= RULE_UNQUOTED_STRING ) ) )
            int alt41=2;
            int LA41_0 = input.LA(1);

            if ( (LA41_0==38) ) {
                alt41=1;
            }
            else if ( (LA41_0==RULE_UNQUOTED_STRING) ) {
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
                    // InternalCompleteOCL.g:1838:3: (otherlv_0= 'init' ( ruleUnrestrictedName )? otherlv_2= ':' ( (lv_ownedExpression_3_0= ruleExpCS ) ) )
                    {
                    // InternalCompleteOCL.g:1838:3: (otherlv_0= 'init' ( ruleUnrestrictedName )? otherlv_2= ':' ( (lv_ownedExpression_3_0= ruleExpCS ) ) )
                    // InternalCompleteOCL.g:1839:4: otherlv_0= 'init' ( ruleUnrestrictedName )? otherlv_2= ':' ( (lv_ownedExpression_3_0= ruleExpCS ) )
                    {
                    otherlv_0=(Token)match(input,38,FollowSets000.FOLLOW_8); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_0, grammarAccess.getInitSpecificationCSAccess().getInitKeyword_0_0());

                    }
                    // InternalCompleteOCL.g:1843:4: ( ruleUnrestrictedName )?
                    int alt40=2;
                    int LA40_0 = input.LA(1);

                    if ( ((LA40_0>=RULE_SIMPLE_ID && LA40_0<=RULE_ESCAPED_ID)||(LA40_0>=30 && LA40_0<=32)) ) {
                        alt40=1;
                    }
                    switch (alt40) {
                        case 1 :
                            // InternalCompleteOCL.g:1844:5: ruleUnrestrictedName
                            {
                            if ( state.backtracking==0 ) {

                              					/* */

                            }
                            if ( state.backtracking==0 ) {

                              					newCompositeNode(grammarAccess.getInitSpecificationCSAccess().getUnrestrictedNameParserRuleCall_0_1());

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

                    otherlv_2=(Token)match(input,23,FollowSets000.FOLLOW_30); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_2, grammarAccess.getInitSpecificationCSAccess().getColonKeyword_0_2());

                    }
                    // InternalCompleteOCL.g:1859:4: ( (lv_ownedExpression_3_0= ruleExpCS ) )
                    // InternalCompleteOCL.g:1860:5: (lv_ownedExpression_3_0= ruleExpCS )
                    {
                    // InternalCompleteOCL.g:1860:5: (lv_ownedExpression_3_0= ruleExpCS )
                    // InternalCompleteOCL.g:1861:6: lv_ownedExpression_3_0= ruleExpCS
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getInitSpecificationCSAccess().getOwnedExpressionExpCSParserRuleCall_0_3_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    lv_ownedExpression_3_0=ruleExpCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElementForParent(grammarAccess.getInitSpecificationCSRule());
                      						}
                      						set(
                      							current,
                      							"ownedExpression",
                      							lv_ownedExpression_3_0,
                      							"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.ExpCS");
                      						afterParserOrEnumRuleCall();

                    }

                    }


                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalCompleteOCL.g:1880:3: ( (lv_exprString_4_0= RULE_UNQUOTED_STRING ) )
                    {
                    // InternalCompleteOCL.g:1880:3: ( (lv_exprString_4_0= RULE_UNQUOTED_STRING ) )
                    // InternalCompleteOCL.g:1881:4: (lv_exprString_4_0= RULE_UNQUOTED_STRING )
                    {
                    // InternalCompleteOCL.g:1881:4: (lv_exprString_4_0= RULE_UNQUOTED_STRING )
                    // InternalCompleteOCL.g:1882:5: lv_exprString_4_0= RULE_UNQUOTED_STRING
                    {
                    lv_exprString_4_0=(Token)match(input,RULE_UNQUOTED_STRING,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					newLeafNode(lv_exprString_4_0, grammarAccess.getInitSpecificationCSAccess().getExprStringUNQUOTED_STRINGTerminalRuleCall_1_0());

                    }
                    if ( state.backtracking==0 ) {

                      					if (current==null) {
                      						current = createModelElement(grammarAccess.getInitSpecificationCSRule());
                      					}
                      					setWithLastConsumed(
                      						current,
                      						"exprString",
                      						lv_exprString_4_0,
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
    // $ANTLR end "ruleinitSpecificationCS"


    // $ANTLR start "entryRuleTemplateSignatureCS"
    // InternalCompleteOCL.g:1902:1: entryRuleTemplateSignatureCS returns [EObject current=null] : iv_ruleTemplateSignatureCS= ruleTemplateSignatureCS EOF ;
    public final EObject entryRuleTemplateSignatureCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTemplateSignatureCS = null;


        try {
            // InternalCompleteOCL.g:1902:60: (iv_ruleTemplateSignatureCS= ruleTemplateSignatureCS EOF )
            // InternalCompleteOCL.g:1903:2: iv_ruleTemplateSignatureCS= ruleTemplateSignatureCS EOF
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
    // InternalCompleteOCL.g:1909:1: ruleTemplateSignatureCS returns [EObject current=null] : ( (otherlv_0= '(' ( (lv_ownedParameters_1_0= ruleTypeParameterCS ) ) (otherlv_2= ',' ( (lv_ownedParameters_3_0= ruleTypeParameterCS ) ) )* otherlv_4= ')' ) | (otherlv_5= '<' ( (lv_ownedParameters_6_0= ruleTypeParameterCS ) ) (otherlv_7= ',' ( (lv_ownedParameters_8_0= ruleTypeParameterCS ) ) )* otherlv_9= '>' ) ) ;
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
            // InternalCompleteOCL.g:1915:2: ( ( (otherlv_0= '(' ( (lv_ownedParameters_1_0= ruleTypeParameterCS ) ) (otherlv_2= ',' ( (lv_ownedParameters_3_0= ruleTypeParameterCS ) ) )* otherlv_4= ')' ) | (otherlv_5= '<' ( (lv_ownedParameters_6_0= ruleTypeParameterCS ) ) (otherlv_7= ',' ( (lv_ownedParameters_8_0= ruleTypeParameterCS ) ) )* otherlv_9= '>' ) ) )
            // InternalCompleteOCL.g:1916:2: ( (otherlv_0= '(' ( (lv_ownedParameters_1_0= ruleTypeParameterCS ) ) (otherlv_2= ',' ( (lv_ownedParameters_3_0= ruleTypeParameterCS ) ) )* otherlv_4= ')' ) | (otherlv_5= '<' ( (lv_ownedParameters_6_0= ruleTypeParameterCS ) ) (otherlv_7= ',' ( (lv_ownedParameters_8_0= ruleTypeParameterCS ) ) )* otherlv_9= '>' ) )
            {
            // InternalCompleteOCL.g:1916:2: ( (otherlv_0= '(' ( (lv_ownedParameters_1_0= ruleTypeParameterCS ) ) (otherlv_2= ',' ( (lv_ownedParameters_3_0= ruleTypeParameterCS ) ) )* otherlv_4= ')' ) | (otherlv_5= '<' ( (lv_ownedParameters_6_0= ruleTypeParameterCS ) ) (otherlv_7= ',' ( (lv_ownedParameters_8_0= ruleTypeParameterCS ) ) )* otherlv_9= '>' ) )
            int alt44=2;
            int LA44_0 = input.LA(1);

            if ( (LA44_0==21) ) {
                alt44=1;
            }
            else if ( (LA44_0==39) ) {
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
                    // InternalCompleteOCL.g:1917:3: (otherlv_0= '(' ( (lv_ownedParameters_1_0= ruleTypeParameterCS ) ) (otherlv_2= ',' ( (lv_ownedParameters_3_0= ruleTypeParameterCS ) ) )* otherlv_4= ')' )
                    {
                    // InternalCompleteOCL.g:1917:3: (otherlv_0= '(' ( (lv_ownedParameters_1_0= ruleTypeParameterCS ) ) (otherlv_2= ',' ( (lv_ownedParameters_3_0= ruleTypeParameterCS ) ) )* otherlv_4= ')' )
                    // InternalCompleteOCL.g:1918:4: otherlv_0= '(' ( (lv_ownedParameters_1_0= ruleTypeParameterCS ) ) (otherlv_2= ',' ( (lv_ownedParameters_3_0= ruleTypeParameterCS ) ) )* otherlv_4= ')'
                    {
                    otherlv_0=(Token)match(input,21,FollowSets000.FOLLOW_15); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_0, grammarAccess.getTemplateSignatureCSAccess().getLeftParenthesisKeyword_0_0());

                    }
                    // InternalCompleteOCL.g:1922:4: ( (lv_ownedParameters_1_0= ruleTypeParameterCS ) )
                    // InternalCompleteOCL.g:1923:5: (lv_ownedParameters_1_0= ruleTypeParameterCS )
                    {
                    // InternalCompleteOCL.g:1923:5: (lv_ownedParameters_1_0= ruleTypeParameterCS )
                    // InternalCompleteOCL.g:1924:6: lv_ownedParameters_1_0= ruleTypeParameterCS
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getTemplateSignatureCSAccess().getOwnedParametersTypeParameterCSParserRuleCall_0_1_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_18);
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

                    // InternalCompleteOCL.g:1941:4: (otherlv_2= ',' ( (lv_ownedParameters_3_0= ruleTypeParameterCS ) ) )*
                    loop42:
                    do {
                        int alt42=2;
                        int LA42_0 = input.LA(1);

                        if ( (LA42_0==28) ) {
                            alt42=1;
                        }


                        switch (alt42) {
                    	case 1 :
                    	    // InternalCompleteOCL.g:1942:5: otherlv_2= ',' ( (lv_ownedParameters_3_0= ruleTypeParameterCS ) )
                    	    {
                    	    otherlv_2=(Token)match(input,28,FollowSets000.FOLLOW_15); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      					newLeafNode(otherlv_2, grammarAccess.getTemplateSignatureCSAccess().getCommaKeyword_0_2_0());

                    	    }
                    	    // InternalCompleteOCL.g:1946:5: ( (lv_ownedParameters_3_0= ruleTypeParameterCS ) )
                    	    // InternalCompleteOCL.g:1947:6: (lv_ownedParameters_3_0= ruleTypeParameterCS )
                    	    {
                    	    // InternalCompleteOCL.g:1947:6: (lv_ownedParameters_3_0= ruleTypeParameterCS )
                    	    // InternalCompleteOCL.g:1948:7: lv_ownedParameters_3_0= ruleTypeParameterCS
                    	    {
                    	    if ( state.backtracking==0 ) {

                    	      							newCompositeNode(grammarAccess.getTemplateSignatureCSAccess().getOwnedParametersTypeParameterCSParserRuleCall_0_2_1_0());

                    	    }
                    	    pushFollow(FollowSets000.FOLLOW_18);
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
                    	    break loop42;
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
                    // InternalCompleteOCL.g:1972:3: (otherlv_5= '<' ( (lv_ownedParameters_6_0= ruleTypeParameterCS ) ) (otherlv_7= ',' ( (lv_ownedParameters_8_0= ruleTypeParameterCS ) ) )* otherlv_9= '>' )
                    {
                    // InternalCompleteOCL.g:1972:3: (otherlv_5= '<' ( (lv_ownedParameters_6_0= ruleTypeParameterCS ) ) (otherlv_7= ',' ( (lv_ownedParameters_8_0= ruleTypeParameterCS ) ) )* otherlv_9= '>' )
                    // InternalCompleteOCL.g:1973:4: otherlv_5= '<' ( (lv_ownedParameters_6_0= ruleTypeParameterCS ) ) (otherlv_7= ',' ( (lv_ownedParameters_8_0= ruleTypeParameterCS ) ) )* otherlv_9= '>'
                    {
                    otherlv_5=(Token)match(input,39,FollowSets000.FOLLOW_15); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_5, grammarAccess.getTemplateSignatureCSAccess().getLessThanSignKeyword_1_0());

                    }
                    // InternalCompleteOCL.g:1977:4: ( (lv_ownedParameters_6_0= ruleTypeParameterCS ) )
                    // InternalCompleteOCL.g:1978:5: (lv_ownedParameters_6_0= ruleTypeParameterCS )
                    {
                    // InternalCompleteOCL.g:1978:5: (lv_ownedParameters_6_0= ruleTypeParameterCS )
                    // InternalCompleteOCL.g:1979:6: lv_ownedParameters_6_0= ruleTypeParameterCS
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getTemplateSignatureCSAccess().getOwnedParametersTypeParameterCSParserRuleCall_1_1_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_31);
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

                    // InternalCompleteOCL.g:1996:4: (otherlv_7= ',' ( (lv_ownedParameters_8_0= ruleTypeParameterCS ) ) )*
                    loop43:
                    do {
                        int alt43=2;
                        int LA43_0 = input.LA(1);

                        if ( (LA43_0==28) ) {
                            alt43=1;
                        }


                        switch (alt43) {
                    	case 1 :
                    	    // InternalCompleteOCL.g:1997:5: otherlv_7= ',' ( (lv_ownedParameters_8_0= ruleTypeParameterCS ) )
                    	    {
                    	    otherlv_7=(Token)match(input,28,FollowSets000.FOLLOW_15); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      					newLeafNode(otherlv_7, grammarAccess.getTemplateSignatureCSAccess().getCommaKeyword_1_2_0());

                    	    }
                    	    // InternalCompleteOCL.g:2001:5: ( (lv_ownedParameters_8_0= ruleTypeParameterCS ) )
                    	    // InternalCompleteOCL.g:2002:6: (lv_ownedParameters_8_0= ruleTypeParameterCS )
                    	    {
                    	    // InternalCompleteOCL.g:2002:6: (lv_ownedParameters_8_0= ruleTypeParameterCS )
                    	    // InternalCompleteOCL.g:2003:7: lv_ownedParameters_8_0= ruleTypeParameterCS
                    	    {
                    	    if ( state.backtracking==0 ) {

                    	      							newCompositeNode(grammarAccess.getTemplateSignatureCSAccess().getOwnedParametersTypeParameterCSParserRuleCall_1_2_1_0());

                    	    }
                    	    pushFollow(FollowSets000.FOLLOW_31);
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
                    	    break loop43;
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
    // InternalCompleteOCL.g:2030:1: entryRuleTypedRefCS returns [EObject current=null] : iv_ruleTypedRefCS= ruleTypedRefCS EOF ;
    public final EObject entryRuleTypedRefCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTypedRefCS = null;


        try {
            // InternalCompleteOCL.g:2030:51: (iv_ruleTypedRefCS= ruleTypedRefCS EOF )
            // InternalCompleteOCL.g:2031:2: iv_ruleTypedRefCS= ruleTypedRefCS EOF
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
    // InternalCompleteOCL.g:2037:1: ruleTypedRefCS returns [EObject current=null] : (this_TypeLiteralCS_0= ruleTypeLiteralCS | this_TypedTypeRefCS_1= ruleTypedTypeRefCS ) ;
    public final EObject ruleTypedRefCS() throws RecognitionException {
        EObject current = null;

        EObject this_TypeLiteralCS_0 = null;

        EObject this_TypedTypeRefCS_1 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:2043:2: ( (this_TypeLiteralCS_0= ruleTypeLiteralCS | this_TypedTypeRefCS_1= ruleTypedTypeRefCS ) )
            // InternalCompleteOCL.g:2044:2: (this_TypeLiteralCS_0= ruleTypeLiteralCS | this_TypedTypeRefCS_1= ruleTypedTypeRefCS )
            {
            // InternalCompleteOCL.g:2044:2: (this_TypeLiteralCS_0= ruleTypeLiteralCS | this_TypedTypeRefCS_1= ruleTypedTypeRefCS )
            int alt45=2;
            int LA45_0 = input.LA(1);

            if ( ((LA45_0>=42 && LA45_0<=51)||(LA45_0>=73 && LA45_0<=74)||(LA45_0>=76 && LA45_0<=80)) ) {
                alt45=1;
            }
            else if ( ((LA45_0>=RULE_SIMPLE_ID && LA45_0<=RULE_ESCAPED_ID)||(LA45_0>=30 && LA45_0<=32)) ) {
                alt45=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 45, 0, input);

                throw nvae;
            }
            switch (alt45) {
                case 1 :
                    // InternalCompleteOCL.g:2045:3: this_TypeLiteralCS_0= ruleTypeLiteralCS
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
                    // InternalCompleteOCL.g:2057:3: this_TypedTypeRefCS_1= ruleTypedTypeRefCS
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
    // InternalCompleteOCL.g:2072:1: entryRuleUnrestrictedName returns [String current=null] : iv_ruleUnrestrictedName= ruleUnrestrictedName EOF ;
    public final String entryRuleUnrestrictedName() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleUnrestrictedName = null;


        try {
            // InternalCompleteOCL.g:2072:56: (iv_ruleUnrestrictedName= ruleUnrestrictedName EOF )
            // InternalCompleteOCL.g:2073:2: iv_ruleUnrestrictedName= ruleUnrestrictedName EOF
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
    // InternalCompleteOCL.g:2079:1: ruleUnrestrictedName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_EssentialOCLUnrestrictedName_0= ruleEssentialOCLUnrestrictedName | kw= 'import' | kw= 'include' | kw= 'library' ) ;
    public final AntlrDatatypeRuleToken ruleUnrestrictedName() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        AntlrDatatypeRuleToken this_EssentialOCLUnrestrictedName_0 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:2085:2: ( (this_EssentialOCLUnrestrictedName_0= ruleEssentialOCLUnrestrictedName | kw= 'import' | kw= 'include' | kw= 'library' ) )
            // InternalCompleteOCL.g:2086:2: (this_EssentialOCLUnrestrictedName_0= ruleEssentialOCLUnrestrictedName | kw= 'import' | kw= 'include' | kw= 'library' )
            {
            // InternalCompleteOCL.g:2086:2: (this_EssentialOCLUnrestrictedName_0= ruleEssentialOCLUnrestrictedName | kw= 'import' | kw= 'include' | kw= 'library' )
            int alt46=4;
            switch ( input.LA(1) ) {
            case RULE_SIMPLE_ID:
            case RULE_ESCAPED_ID:
                {
                alt46=1;
                }
                break;
            case 30:
                {
                alt46=2;
                }
                break;
            case 31:
                {
                alt46=3;
                }
                break;
            case 32:
                {
                alt46=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 46, 0, input);

                throw nvae;
            }

            switch (alt46) {
                case 1 :
                    // InternalCompleteOCL.g:2087:3: this_EssentialOCLUnrestrictedName_0= ruleEssentialOCLUnrestrictedName
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
                    // InternalCompleteOCL.g:2098:3: kw= 'import'
                    {
                    kw=(Token)match(input,30,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getUnrestrictedNameAccess().getImportKeyword_1());

                    }

                    }
                    break;
                case 3 :
                    // InternalCompleteOCL.g:2104:3: kw= 'include'
                    {
                    kw=(Token)match(input,31,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getUnrestrictedNameAccess().getIncludeKeyword_2());

                    }

                    }
                    break;
                case 4 :
                    // InternalCompleteOCL.g:2110:3: kw= 'library'
                    {
                    kw=(Token)match(input,32,FollowSets000.FOLLOW_2); if (state.failed) return current;
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
    // InternalCompleteOCL.g:2119:1: entryRuleNavigatingArgExpCS returns [EObject current=null] : iv_ruleNavigatingArgExpCS= ruleNavigatingArgExpCS EOF ;
    public final EObject entryRuleNavigatingArgExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNavigatingArgExpCS = null;


        try {
            // InternalCompleteOCL.g:2119:59: (iv_ruleNavigatingArgExpCS= ruleNavigatingArgExpCS EOF )
            // InternalCompleteOCL.g:2120:2: iv_ruleNavigatingArgExpCS= ruleNavigatingArgExpCS EOF
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
    // InternalCompleteOCL.g:2126:1: ruleNavigatingArgExpCS returns [EObject current=null] : ( ( () otherlv_1= '?' ) | this_ExpCS_2= ruleExpCS ) ;
    public final EObject ruleNavigatingArgExpCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject this_ExpCS_2 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:2132:2: ( ( ( () otherlv_1= '?' ) | this_ExpCS_2= ruleExpCS ) )
            // InternalCompleteOCL.g:2133:2: ( ( () otherlv_1= '?' ) | this_ExpCS_2= ruleExpCS )
            {
            // InternalCompleteOCL.g:2133:2: ( ( () otherlv_1= '?' ) | this_ExpCS_2= ruleExpCS )
            int alt47=2;
            int LA47_0 = input.LA(1);

            if ( (LA47_0==41) ) {
                alt47=1;
            }
            else if ( ((LA47_0>=RULE_SIMPLE_ID && LA47_0<=RULE_SINGLE_QUOTED_STRING)||LA47_0==21||(LA47_0>=30 && LA47_0<=32)||(LA47_0>=42 && LA47_0<=55)||(LA47_0>=73 && LA47_0<=74)||(LA47_0>=76 && LA47_0<=80)||LA47_0==85||(LA47_0>=88 && LA47_0<=91)||LA47_0==98||(LA47_0>=103 && LA47_0<=104)) ) {
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
                    // InternalCompleteOCL.g:2134:3: ( () otherlv_1= '?' )
                    {
                    // InternalCompleteOCL.g:2134:3: ( () otherlv_1= '?' )
                    // InternalCompleteOCL.g:2135:4: () otherlv_1= '?'
                    {
                    // InternalCompleteOCL.g:2135:4: ()
                    // InternalCompleteOCL.g:2136:5:
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
                    // InternalCompleteOCL.g:2151:3: this_ExpCS_2= ruleExpCS
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
    // InternalCompleteOCL.g:2166:1: entryRuleNavigationOperatorName returns [String current=null] : iv_ruleNavigationOperatorName= ruleNavigationOperatorName EOF ;
    public final String entryRuleNavigationOperatorName() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleNavigationOperatorName = null;


        try {
            // InternalCompleteOCL.g:2166:62: (iv_ruleNavigationOperatorName= ruleNavigationOperatorName EOF )
            // InternalCompleteOCL.g:2167:2: iv_ruleNavigationOperatorName= ruleNavigationOperatorName EOF
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
    // InternalCompleteOCL.g:2173:1: ruleNavigationOperatorName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_EssentialOCLNavigationOperatorName_0= ruleEssentialOCLNavigationOperatorName | this_CompleteOCLNavigationOperatorName_1= ruleCompleteOCLNavigationOperatorName ) ;
    public final AntlrDatatypeRuleToken ruleNavigationOperatorName() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        AntlrDatatypeRuleToken this_EssentialOCLNavigationOperatorName_0 = null;

        AntlrDatatypeRuleToken this_CompleteOCLNavigationOperatorName_1 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:2179:2: ( (this_EssentialOCLNavigationOperatorName_0= ruleEssentialOCLNavigationOperatorName | this_CompleteOCLNavigationOperatorName_1= ruleCompleteOCLNavigationOperatorName ) )
            // InternalCompleteOCL.g:2180:2: (this_EssentialOCLNavigationOperatorName_0= ruleEssentialOCLNavigationOperatorName | this_CompleteOCLNavigationOperatorName_1= ruleCompleteOCLNavigationOperatorName )
            {
            // InternalCompleteOCL.g:2180:2: (this_EssentialOCLNavigationOperatorName_0= ruleEssentialOCLNavigationOperatorName | this_CompleteOCLNavigationOperatorName_1= ruleCompleteOCLNavigationOperatorName )
            int alt48=2;
            int LA48_0 = input.LA(1);

            if ( ((LA48_0>=69 && LA48_0<=72)) ) {
                alt48=1;
            }
            else if ( ((LA48_0>=17 && LA48_0<=18)) ) {
                alt48=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 48, 0, input);

                throw nvae;
            }
            switch (alt48) {
                case 1 :
                    // InternalCompleteOCL.g:2181:3: this_EssentialOCLNavigationOperatorName_0= ruleEssentialOCLNavigationOperatorName
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
                    // InternalCompleteOCL.g:2192:3: this_CompleteOCLNavigationOperatorName_1= ruleCompleteOCLNavigationOperatorName
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
    // InternalCompleteOCL.g:2206:1: entryRulePrimitiveTypeIdentifier returns [String current=null] : iv_rulePrimitiveTypeIdentifier= rulePrimitiveTypeIdentifier EOF ;
    public final String entryRulePrimitiveTypeIdentifier() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulePrimitiveTypeIdentifier = null;


        try {
            // InternalCompleteOCL.g:2206:63: (iv_rulePrimitiveTypeIdentifier= rulePrimitiveTypeIdentifier EOF )
            // InternalCompleteOCL.g:2207:2: iv_rulePrimitiveTypeIdentifier= rulePrimitiveTypeIdentifier EOF
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
    // InternalCompleteOCL.g:2213:1: rulePrimitiveTypeIdentifier returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= 'Boolean' | kw= 'Integer' | kw= 'Real' | kw= 'String' | kw= 'UnlimitedNatural' | kw= 'OclAny' | kw= 'OclInvalid' | kw= 'OclMessage' | kw= 'OclState' | kw= 'OclVoid' ) ;
    public final AntlrDatatypeRuleToken rulePrimitiveTypeIdentifier() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;


        	enterRule();

        try {
            // InternalCompleteOCL.g:2219:2: ( (kw= 'Boolean' | kw= 'Integer' | kw= 'Real' | kw= 'String' | kw= 'UnlimitedNatural' | kw= 'OclAny' | kw= 'OclInvalid' | kw= 'OclMessage' | kw= 'OclState' | kw= 'OclVoid' ) )
            // InternalCompleteOCL.g:2220:2: (kw= 'Boolean' | kw= 'Integer' | kw= 'Real' | kw= 'String' | kw= 'UnlimitedNatural' | kw= 'OclAny' | kw= 'OclInvalid' | kw= 'OclMessage' | kw= 'OclState' | kw= 'OclVoid' )
            {
            // InternalCompleteOCL.g:2220:2: (kw= 'Boolean' | kw= 'Integer' | kw= 'Real' | kw= 'String' | kw= 'UnlimitedNatural' | kw= 'OclAny' | kw= 'OclInvalid' | kw= 'OclMessage' | kw= 'OclState' | kw= 'OclVoid' )
            int alt49=10;
            switch ( input.LA(1) ) {
            case 42:
                {
                alt49=1;
                }
                break;
            case 43:
                {
                alt49=2;
                }
                break;
            case 44:
                {
                alt49=3;
                }
                break;
            case 45:
                {
                alt49=4;
                }
                break;
            case 46:
                {
                alt49=5;
                }
                break;
            case 47:
                {
                alt49=6;
                }
                break;
            case 48:
                {
                alt49=7;
                }
                break;
            case 49:
                {
                alt49=8;
                }
                break;
            case 50:
                {
                alt49=9;
                }
                break;
            case 51:
                {
                alt49=10;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 49, 0, input);

                throw nvae;
            }

            switch (alt49) {
                case 1 :
                    // InternalCompleteOCL.g:2221:3: kw= 'Boolean'
                    {
                    kw=(Token)match(input,42,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getPrimitiveTypeIdentifierAccess().getBooleanKeyword_0());

                    }

                    }
                    break;
                case 2 :
                    // InternalCompleteOCL.g:2227:3: kw= 'Integer'
                    {
                    kw=(Token)match(input,43,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getPrimitiveTypeIdentifierAccess().getIntegerKeyword_1());

                    }

                    }
                    break;
                case 3 :
                    // InternalCompleteOCL.g:2233:3: kw= 'Real'
                    {
                    kw=(Token)match(input,44,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getPrimitiveTypeIdentifierAccess().getRealKeyword_2());

                    }

                    }
                    break;
                case 4 :
                    // InternalCompleteOCL.g:2239:3: kw= 'String'
                    {
                    kw=(Token)match(input,45,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getPrimitiveTypeIdentifierAccess().getStringKeyword_3());

                    }

                    }
                    break;
                case 5 :
                    // InternalCompleteOCL.g:2245:3: kw= 'UnlimitedNatural'
                    {
                    kw=(Token)match(input,46,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getPrimitiveTypeIdentifierAccess().getUnlimitedNaturalKeyword_4());

                    }

                    }
                    break;
                case 6 :
                    // InternalCompleteOCL.g:2251:3: kw= 'OclAny'
                    {
                    kw=(Token)match(input,47,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getPrimitiveTypeIdentifierAccess().getOclAnyKeyword_5());

                    }

                    }
                    break;
                case 7 :
                    // InternalCompleteOCL.g:2257:3: kw= 'OclInvalid'
                    {
                    kw=(Token)match(input,48,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getPrimitiveTypeIdentifierAccess().getOclInvalidKeyword_6());

                    }

                    }
                    break;
                case 8 :
                    // InternalCompleteOCL.g:2263:3: kw= 'OclMessage'
                    {
                    kw=(Token)match(input,49,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getPrimitiveTypeIdentifierAccess().getOclMessageKeyword_7());

                    }

                    }
                    break;
                case 9 :
                    // InternalCompleteOCL.g:2269:3: kw= 'OclState'
                    {
                    kw=(Token)match(input,50,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getPrimitiveTypeIdentifierAccess().getOclStateKeyword_8());

                    }

                    }
                    break;
                case 10 :
                    // InternalCompleteOCL.g:2275:3: kw= 'OclVoid'
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
    // InternalCompleteOCL.g:2284:1: entryRuleEssentialOCLUnaryOperatorName returns [String current=null] : iv_ruleEssentialOCLUnaryOperatorName= ruleEssentialOCLUnaryOperatorName EOF ;
    public final String entryRuleEssentialOCLUnaryOperatorName() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleEssentialOCLUnaryOperatorName = null;


        try {
            // InternalCompleteOCL.g:2284:69: (iv_ruleEssentialOCLUnaryOperatorName= ruleEssentialOCLUnaryOperatorName EOF )
            // InternalCompleteOCL.g:2285:2: iv_ruleEssentialOCLUnaryOperatorName= ruleEssentialOCLUnaryOperatorName EOF
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
    // InternalCompleteOCL.g:2291:1: ruleEssentialOCLUnaryOperatorName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= '-' | kw= 'not' | kw= 'not2' ) ;
    public final AntlrDatatypeRuleToken ruleEssentialOCLUnaryOperatorName() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;


        	enterRule();

        try {
            // InternalCompleteOCL.g:2297:2: ( (kw= '-' | kw= 'not' | kw= 'not2' ) )
            // InternalCompleteOCL.g:2298:2: (kw= '-' | kw= 'not' | kw= 'not2' )
            {
            // InternalCompleteOCL.g:2298:2: (kw= '-' | kw= 'not' | kw= 'not2' )
            int alt50=3;
            switch ( input.LA(1) ) {
            case 52:
                {
                alt50=1;
                }
                break;
            case 53:
                {
                alt50=2;
                }
                break;
            case 54:
                {
                alt50=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 50, 0, input);

                throw nvae;
            }

            switch (alt50) {
                case 1 :
                    // InternalCompleteOCL.g:2299:3: kw= '-'
                    {
                    kw=(Token)match(input,52,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getEssentialOCLUnaryOperatorNameAccess().getHyphenMinusKeyword_0());

                    }

                    }
                    break;
                case 2 :
                    // InternalCompleteOCL.g:2305:3: kw= 'not'
                    {
                    kw=(Token)match(input,53,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getEssentialOCLUnaryOperatorNameAccess().getNotKeyword_1());

                    }

                    }
                    break;
                case 3 :
                    // InternalCompleteOCL.g:2311:3: kw= 'not2'
                    {
                    kw=(Token)match(input,54,FollowSets000.FOLLOW_2); if (state.failed) return current;
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
    // InternalCompleteOCL.g:2320:1: entryRuleEssentialOCLInfixOperatorName returns [String current=null] : iv_ruleEssentialOCLInfixOperatorName= ruleEssentialOCLInfixOperatorName EOF ;
    public final String entryRuleEssentialOCLInfixOperatorName() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleEssentialOCLInfixOperatorName = null;


        try {
            // InternalCompleteOCL.g:2320:69: (iv_ruleEssentialOCLInfixOperatorName= ruleEssentialOCLInfixOperatorName EOF )
            // InternalCompleteOCL.g:2321:2: iv_ruleEssentialOCLInfixOperatorName= ruleEssentialOCLInfixOperatorName EOF
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
    // InternalCompleteOCL.g:2327:1: ruleEssentialOCLInfixOperatorName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= '*' | kw= '/' | kw= '+' | kw= '-' | kw= '>' | kw= '<' | kw= '>=' | kw= '<=' | kw= '=' | kw= '<>' | kw= 'and' | kw= 'and2' | kw= 'implies' | kw= 'implies2' | kw= 'or' | kw= 'or2' | kw= 'xor' | kw= 'xor2' ) ;
    public final AntlrDatatypeRuleToken ruleEssentialOCLInfixOperatorName() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;


        	enterRule();

        try {
            // InternalCompleteOCL.g:2333:2: ( (kw= '*' | kw= '/' | kw= '+' | kw= '-' | kw= '>' | kw= '<' | kw= '>=' | kw= '<=' | kw= '=' | kw= '<>' | kw= 'and' | kw= 'and2' | kw= 'implies' | kw= 'implies2' | kw= 'or' | kw= 'or2' | kw= 'xor' | kw= 'xor2' ) )
            // InternalCompleteOCL.g:2334:2: (kw= '*' | kw= '/' | kw= '+' | kw= '-' | kw= '>' | kw= '<' | kw= '>=' | kw= '<=' | kw= '=' | kw= '<>' | kw= 'and' | kw= 'and2' | kw= 'implies' | kw= 'implies2' | kw= 'or' | kw= 'or2' | kw= 'xor' | kw= 'xor2' )
            {
            // InternalCompleteOCL.g:2334:2: (kw= '*' | kw= '/' | kw= '+' | kw= '-' | kw= '>' | kw= '<' | kw= '>=' | kw= '<=' | kw= '=' | kw= '<>' | kw= 'and' | kw= 'and2' | kw= 'implies' | kw= 'implies2' | kw= 'or' | kw= 'or2' | kw= 'xor' | kw= 'xor2' )
            int alt51=18;
            switch ( input.LA(1) ) {
            case 55:
                {
                alt51=1;
                }
                break;
            case 56:
                {
                alt51=2;
                }
                break;
            case 57:
                {
                alt51=3;
                }
                break;
            case 52:
                {
                alt51=4;
                }
                break;
            case 40:
                {
                alt51=5;
                }
                break;
            case 39:
                {
                alt51=6;
                }
                break;
            case 58:
                {
                alt51=7;
                }
                break;
            case 59:
                {
                alt51=8;
                }
                break;
            case 29:
                {
                alt51=9;
                }
                break;
            case 60:
                {
                alt51=10;
                }
                break;
            case 61:
                {
                alt51=11;
                }
                break;
            case 62:
                {
                alt51=12;
                }
                break;
            case 63:
                {
                alt51=13;
                }
                break;
            case 64:
                {
                alt51=14;
                }
                break;
            case 65:
                {
                alt51=15;
                }
                break;
            case 66:
                {
                alt51=16;
                }
                break;
            case 67:
                {
                alt51=17;
                }
                break;
            case 68:
                {
                alt51=18;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 51, 0, input);

                throw nvae;
            }

            switch (alt51) {
                case 1 :
                    // InternalCompleteOCL.g:2335:3: kw= '*'
                    {
                    kw=(Token)match(input,55,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getEssentialOCLInfixOperatorNameAccess().getAsteriskKeyword_0());

                    }

                    }
                    break;
                case 2 :
                    // InternalCompleteOCL.g:2341:3: kw= '/'
                    {
                    kw=(Token)match(input,56,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getEssentialOCLInfixOperatorNameAccess().getSolidusKeyword_1());

                    }

                    }
                    break;
                case 3 :
                    // InternalCompleteOCL.g:2347:3: kw= '+'
                    {
                    kw=(Token)match(input,57,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getEssentialOCLInfixOperatorNameAccess().getPlusSignKeyword_2());

                    }

                    }
                    break;
                case 4 :
                    // InternalCompleteOCL.g:2353:3: kw= '-'
                    {
                    kw=(Token)match(input,52,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getEssentialOCLInfixOperatorNameAccess().getHyphenMinusKeyword_3());

                    }

                    }
                    break;
                case 5 :
                    // InternalCompleteOCL.g:2359:3: kw= '>'
                    {
                    kw=(Token)match(input,40,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getEssentialOCLInfixOperatorNameAccess().getGreaterThanSignKeyword_4());

                    }

                    }
                    break;
                case 6 :
                    // InternalCompleteOCL.g:2365:3: kw= '<'
                    {
                    kw=(Token)match(input,39,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getEssentialOCLInfixOperatorNameAccess().getLessThanSignKeyword_5());

                    }

                    }
                    break;
                case 7 :
                    // InternalCompleteOCL.g:2371:3: kw= '>='
                    {
                    kw=(Token)match(input,58,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getEssentialOCLInfixOperatorNameAccess().getGreaterThanSignEqualsSignKeyword_6());

                    }

                    }
                    break;
                case 8 :
                    // InternalCompleteOCL.g:2377:3: kw= '<='
                    {
                    kw=(Token)match(input,59,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getEssentialOCLInfixOperatorNameAccess().getLessThanSignEqualsSignKeyword_7());

                    }

                    }
                    break;
                case 9 :
                    // InternalCompleteOCL.g:2383:3: kw= '='
                    {
                    kw=(Token)match(input,29,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getEssentialOCLInfixOperatorNameAccess().getEqualsSignKeyword_8());

                    }

                    }
                    break;
                case 10 :
                    // InternalCompleteOCL.g:2389:3: kw= '<>'
                    {
                    kw=(Token)match(input,60,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getEssentialOCLInfixOperatorNameAccess().getLessThanSignGreaterThanSignKeyword_9());

                    }

                    }
                    break;
                case 11 :
                    // InternalCompleteOCL.g:2395:3: kw= 'and'
                    {
                    kw=(Token)match(input,61,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getEssentialOCLInfixOperatorNameAccess().getAndKeyword_10());

                    }

                    }
                    break;
                case 12 :
                    // InternalCompleteOCL.g:2401:3: kw= 'and2'
                    {
                    kw=(Token)match(input,62,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getEssentialOCLInfixOperatorNameAccess().getAnd2Keyword_11());

                    }

                    }
                    break;
                case 13 :
                    // InternalCompleteOCL.g:2407:3: kw= 'implies'
                    {
                    kw=(Token)match(input,63,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getEssentialOCLInfixOperatorNameAccess().getImpliesKeyword_12());

                    }

                    }
                    break;
                case 14 :
                    // InternalCompleteOCL.g:2413:3: kw= 'implies2'
                    {
                    kw=(Token)match(input,64,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getEssentialOCLInfixOperatorNameAccess().getImplies2Keyword_13());

                    }

                    }
                    break;
                case 15 :
                    // InternalCompleteOCL.g:2419:3: kw= 'or'
                    {
                    kw=(Token)match(input,65,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getEssentialOCLInfixOperatorNameAccess().getOrKeyword_14());

                    }

                    }
                    break;
                case 16 :
                    // InternalCompleteOCL.g:2425:3: kw= 'or2'
                    {
                    kw=(Token)match(input,66,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getEssentialOCLInfixOperatorNameAccess().getOr2Keyword_15());

                    }

                    }
                    break;
                case 17 :
                    // InternalCompleteOCL.g:2431:3: kw= 'xor'
                    {
                    kw=(Token)match(input,67,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getEssentialOCLInfixOperatorNameAccess().getXorKeyword_16());

                    }

                    }
                    break;
                case 18 :
                    // InternalCompleteOCL.g:2437:3: kw= 'xor2'
                    {
                    kw=(Token)match(input,68,FollowSets000.FOLLOW_2); if (state.failed) return current;
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
    // InternalCompleteOCL.g:2446:1: entryRuleEssentialOCLNavigationOperatorName returns [String current=null] : iv_ruleEssentialOCLNavigationOperatorName= ruleEssentialOCLNavigationOperatorName EOF ;
    public final String entryRuleEssentialOCLNavigationOperatorName() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleEssentialOCLNavigationOperatorName = null;


        try {
            // InternalCompleteOCL.g:2446:74: (iv_ruleEssentialOCLNavigationOperatorName= ruleEssentialOCLNavigationOperatorName EOF )
            // InternalCompleteOCL.g:2447:2: iv_ruleEssentialOCLNavigationOperatorName= ruleEssentialOCLNavigationOperatorName EOF
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
    // InternalCompleteOCL.g:2453:1: ruleEssentialOCLNavigationOperatorName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= '.' | kw= '->' | kw= '?.' | kw= '?->' ) ;
    public final AntlrDatatypeRuleToken ruleEssentialOCLNavigationOperatorName() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;


        	enterRule();

        try {
            // InternalCompleteOCL.g:2459:2: ( (kw= '.' | kw= '->' | kw= '?.' | kw= '?->' ) )
            // InternalCompleteOCL.g:2460:2: (kw= '.' | kw= '->' | kw= '?.' | kw= '?->' )
            {
            // InternalCompleteOCL.g:2460:2: (kw= '.' | kw= '->' | kw= '?.' | kw= '?->' )
            int alt52=4;
            switch ( input.LA(1) ) {
            case 69:
                {
                alt52=1;
                }
                break;
            case 70:
                {
                alt52=2;
                }
                break;
            case 71:
                {
                alt52=3;
                }
                break;
            case 72:
                {
                alt52=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 52, 0, input);

                throw nvae;
            }

            switch (alt52) {
                case 1 :
                    // InternalCompleteOCL.g:2461:3: kw= '.'
                    {
                    kw=(Token)match(input,69,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getEssentialOCLNavigationOperatorNameAccess().getFullStopKeyword_0());

                    }

                    }
                    break;
                case 2 :
                    // InternalCompleteOCL.g:2467:3: kw= '->'
                    {
                    kw=(Token)match(input,70,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getEssentialOCLNavigationOperatorNameAccess().getHyphenMinusGreaterThanSignKeyword_1());

                    }

                    }
                    break;
                case 3 :
                    // InternalCompleteOCL.g:2473:3: kw= '?.'
                    {
                    kw=(Token)match(input,71,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getEssentialOCLNavigationOperatorNameAccess().getQuestionMarkFullStopKeyword_2());

                    }

                    }
                    break;
                case 4 :
                    // InternalCompleteOCL.g:2479:3: kw= '?->'
                    {
                    kw=(Token)match(input,72,FollowSets000.FOLLOW_2); if (state.failed) return current;
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
    // InternalCompleteOCL.g:2488:1: entryRuleBinaryOperatorName returns [String current=null] : iv_ruleBinaryOperatorName= ruleBinaryOperatorName EOF ;
    public final String entryRuleBinaryOperatorName() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleBinaryOperatorName = null;


        try {
            // InternalCompleteOCL.g:2488:58: (iv_ruleBinaryOperatorName= ruleBinaryOperatorName EOF )
            // InternalCompleteOCL.g:2489:2: iv_ruleBinaryOperatorName= ruleBinaryOperatorName EOF
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
    // InternalCompleteOCL.g:2495:1: ruleBinaryOperatorName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_InfixOperatorName_0= ruleInfixOperatorName | this_NavigationOperatorName_1= ruleNavigationOperatorName ) ;
    public final AntlrDatatypeRuleToken ruleBinaryOperatorName() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        AntlrDatatypeRuleToken this_InfixOperatorName_0 = null;

        AntlrDatatypeRuleToken this_NavigationOperatorName_1 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:2501:2: ( (this_InfixOperatorName_0= ruleInfixOperatorName | this_NavigationOperatorName_1= ruleNavigationOperatorName ) )
            // InternalCompleteOCL.g:2502:2: (this_InfixOperatorName_0= ruleInfixOperatorName | this_NavigationOperatorName_1= ruleNavigationOperatorName )
            {
            // InternalCompleteOCL.g:2502:2: (this_InfixOperatorName_0= ruleInfixOperatorName | this_NavigationOperatorName_1= ruleNavigationOperatorName )
            int alt53=2;
            int LA53_0 = input.LA(1);

            if ( (LA53_0==29||(LA53_0>=39 && LA53_0<=40)||LA53_0==52||(LA53_0>=55 && LA53_0<=68)) ) {
                alt53=1;
            }
            else if ( ((LA53_0>=17 && LA53_0<=18)||(LA53_0>=69 && LA53_0<=72)) ) {
                alt53=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 53, 0, input);

                throw nvae;
            }
            switch (alt53) {
                case 1 :
                    // InternalCompleteOCL.g:2503:3: this_InfixOperatorName_0= ruleInfixOperatorName
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
                    // InternalCompleteOCL.g:2514:3: this_NavigationOperatorName_1= ruleNavigationOperatorName
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
    // InternalCompleteOCL.g:2528:1: entryRuleInfixOperatorName returns [String current=null] : iv_ruleInfixOperatorName= ruleInfixOperatorName EOF ;
    public final String entryRuleInfixOperatorName() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleInfixOperatorName = null;


        try {
            // InternalCompleteOCL.g:2528:57: (iv_ruleInfixOperatorName= ruleInfixOperatorName EOF )
            // InternalCompleteOCL.g:2529:2: iv_ruleInfixOperatorName= ruleInfixOperatorName EOF
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
    // InternalCompleteOCL.g:2535:1: ruleInfixOperatorName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_EssentialOCLInfixOperatorName_0= ruleEssentialOCLInfixOperatorName ;
    public final AntlrDatatypeRuleToken ruleInfixOperatorName() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        AntlrDatatypeRuleToken this_EssentialOCLInfixOperatorName_0 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:2541:2: (this_EssentialOCLInfixOperatorName_0= ruleEssentialOCLInfixOperatorName )
            // InternalCompleteOCL.g:2542:2: this_EssentialOCLInfixOperatorName_0= ruleEssentialOCLInfixOperatorName
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
    // InternalCompleteOCL.g:2555:1: entryRuleUnaryOperatorName returns [String current=null] : iv_ruleUnaryOperatorName= ruleUnaryOperatorName EOF ;
    public final String entryRuleUnaryOperatorName() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleUnaryOperatorName = null;


        try {
            // InternalCompleteOCL.g:2555:57: (iv_ruleUnaryOperatorName= ruleUnaryOperatorName EOF )
            // InternalCompleteOCL.g:2556:2: iv_ruleUnaryOperatorName= ruleUnaryOperatorName EOF
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
    // InternalCompleteOCL.g:2562:1: ruleUnaryOperatorName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_EssentialOCLUnaryOperatorName_0= ruleEssentialOCLUnaryOperatorName ;
    public final AntlrDatatypeRuleToken ruleUnaryOperatorName() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        AntlrDatatypeRuleToken this_EssentialOCLUnaryOperatorName_0 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:2568:2: (this_EssentialOCLUnaryOperatorName_0= ruleEssentialOCLUnaryOperatorName )
            // InternalCompleteOCL.g:2569:2: this_EssentialOCLUnaryOperatorName_0= ruleEssentialOCLUnaryOperatorName
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
    // InternalCompleteOCL.g:2582:1: entryRuleEssentialOCLUnrestrictedName returns [String current=null] : iv_ruleEssentialOCLUnrestrictedName= ruleEssentialOCLUnrestrictedName EOF ;
    public final String entryRuleEssentialOCLUnrestrictedName() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleEssentialOCLUnrestrictedName = null;


        try {
            // InternalCompleteOCL.g:2582:68: (iv_ruleEssentialOCLUnrestrictedName= ruleEssentialOCLUnrestrictedName EOF )
            // InternalCompleteOCL.g:2583:2: iv_ruleEssentialOCLUnrestrictedName= ruleEssentialOCLUnrestrictedName EOF
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
    // InternalCompleteOCL.g:2589:1: ruleEssentialOCLUnrestrictedName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_Identifier_0= ruleIdentifier ;
    public final AntlrDatatypeRuleToken ruleEssentialOCLUnrestrictedName() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        AntlrDatatypeRuleToken this_Identifier_0 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:2595:2: (this_Identifier_0= ruleIdentifier )
            // InternalCompleteOCL.g:2596:2: this_Identifier_0= ruleIdentifier
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
    // InternalCompleteOCL.g:2609:1: entryRuleEssentialOCLUnreservedName returns [String current=null] : iv_ruleEssentialOCLUnreservedName= ruleEssentialOCLUnreservedName EOF ;
    public final String entryRuleEssentialOCLUnreservedName() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleEssentialOCLUnreservedName = null;


        try {
            // InternalCompleteOCL.g:2609:66: (iv_ruleEssentialOCLUnreservedName= ruleEssentialOCLUnreservedName EOF )
            // InternalCompleteOCL.g:2610:2: iv_ruleEssentialOCLUnreservedName= ruleEssentialOCLUnreservedName EOF
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
    // InternalCompleteOCL.g:2616:1: ruleEssentialOCLUnreservedName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_UnrestrictedName_0= ruleUnrestrictedName | this_CollectionTypeIdentifier_1= ruleCollectionTypeIdentifier | this_PrimitiveTypeIdentifier_2= rulePrimitiveTypeIdentifier | kw= 'Map' | kw= 'Tuple' ) ;
    public final AntlrDatatypeRuleToken ruleEssentialOCLUnreservedName() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        AntlrDatatypeRuleToken this_UnrestrictedName_0 = null;

        AntlrDatatypeRuleToken this_CollectionTypeIdentifier_1 = null;

        AntlrDatatypeRuleToken this_PrimitiveTypeIdentifier_2 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:2622:2: ( (this_UnrestrictedName_0= ruleUnrestrictedName | this_CollectionTypeIdentifier_1= ruleCollectionTypeIdentifier | this_PrimitiveTypeIdentifier_2= rulePrimitiveTypeIdentifier | kw= 'Map' | kw= 'Tuple' ) )
            // InternalCompleteOCL.g:2623:2: (this_UnrestrictedName_0= ruleUnrestrictedName | this_CollectionTypeIdentifier_1= ruleCollectionTypeIdentifier | this_PrimitiveTypeIdentifier_2= rulePrimitiveTypeIdentifier | kw= 'Map' | kw= 'Tuple' )
            {
            // InternalCompleteOCL.g:2623:2: (this_UnrestrictedName_0= ruleUnrestrictedName | this_CollectionTypeIdentifier_1= ruleCollectionTypeIdentifier | this_PrimitiveTypeIdentifier_2= rulePrimitiveTypeIdentifier | kw= 'Map' | kw= 'Tuple' )
            int alt54=5;
            switch ( input.LA(1) ) {
            case RULE_SIMPLE_ID:
            case RULE_ESCAPED_ID:
            case 30:
            case 31:
            case 32:
                {
                alt54=1;
                }
                break;
            case 76:
            case 77:
            case 78:
            case 79:
            case 80:
                {
                alt54=2;
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
                alt54=3;
                }
                break;
            case 73:
                {
                alt54=4;
                }
                break;
            case 74:
                {
                alt54=5;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 54, 0, input);

                throw nvae;
            }

            switch (alt54) {
                case 1 :
                    // InternalCompleteOCL.g:2624:3: this_UnrestrictedName_0= ruleUnrestrictedName
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
                    // InternalCompleteOCL.g:2635:3: this_CollectionTypeIdentifier_1= ruleCollectionTypeIdentifier
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
                    // InternalCompleteOCL.g:2646:3: this_PrimitiveTypeIdentifier_2= rulePrimitiveTypeIdentifier
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
                    // InternalCompleteOCL.g:2657:3: kw= 'Map'
                    {
                    kw=(Token)match(input,73,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getEssentialOCLUnreservedNameAccess().getMapKeyword_3());

                    }

                    }
                    break;
                case 5 :
                    // InternalCompleteOCL.g:2663:3: kw= 'Tuple'
                    {
                    kw=(Token)match(input,74,FollowSets000.FOLLOW_2); if (state.failed) return current;
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
    // InternalCompleteOCL.g:2672:1: entryRuleUnreservedName returns [String current=null] : iv_ruleUnreservedName= ruleUnreservedName EOF ;
    public final String entryRuleUnreservedName() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleUnreservedName = null;


        try {
            // InternalCompleteOCL.g:2672:54: (iv_ruleUnreservedName= ruleUnreservedName EOF )
            // InternalCompleteOCL.g:2673:2: iv_ruleUnreservedName= ruleUnreservedName EOF
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
    // InternalCompleteOCL.g:2679:1: ruleUnreservedName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_EssentialOCLUnreservedName_0= ruleEssentialOCLUnreservedName ;
    public final AntlrDatatypeRuleToken ruleUnreservedName() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        AntlrDatatypeRuleToken this_EssentialOCLUnreservedName_0 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:2685:2: (this_EssentialOCLUnreservedName_0= ruleEssentialOCLUnreservedName )
            // InternalCompleteOCL.g:2686:2: this_EssentialOCLUnreservedName_0= ruleEssentialOCLUnreservedName
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
    // InternalCompleteOCL.g:2699:1: entryRuleURIPathNameCS returns [EObject current=null] : iv_ruleURIPathNameCS= ruleURIPathNameCS EOF ;
    public final EObject entryRuleURIPathNameCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleURIPathNameCS = null;


        try {
            // InternalCompleteOCL.g:2699:54: (iv_ruleURIPathNameCS= ruleURIPathNameCS EOF )
            // InternalCompleteOCL.g:2700:2: iv_ruleURIPathNameCS= ruleURIPathNameCS EOF
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
    // InternalCompleteOCL.g:2706:1: ruleURIPathNameCS returns [EObject current=null] : ( ( (lv_ownedPathElements_0_0= ruleURIFirstPathElementCS ) ) (otherlv_1= '::' ( (lv_ownedPathElements_2_0= ruleNextPathElementCS ) ) )* ) ;
    public final EObject ruleURIPathNameCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject lv_ownedPathElements_0_0 = null;

        EObject lv_ownedPathElements_2_0 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:2712:2: ( ( ( (lv_ownedPathElements_0_0= ruleURIFirstPathElementCS ) ) (otherlv_1= '::' ( (lv_ownedPathElements_2_0= ruleNextPathElementCS ) ) )* ) )
            // InternalCompleteOCL.g:2713:2: ( ( (lv_ownedPathElements_0_0= ruleURIFirstPathElementCS ) ) (otherlv_1= '::' ( (lv_ownedPathElements_2_0= ruleNextPathElementCS ) ) )* )
            {
            // InternalCompleteOCL.g:2713:2: ( ( (lv_ownedPathElements_0_0= ruleURIFirstPathElementCS ) ) (otherlv_1= '::' ( (lv_ownedPathElements_2_0= ruleNextPathElementCS ) ) )* )
            // InternalCompleteOCL.g:2714:3: ( (lv_ownedPathElements_0_0= ruleURIFirstPathElementCS ) ) (otherlv_1= '::' ( (lv_ownedPathElements_2_0= ruleNextPathElementCS ) ) )*
            {
            // InternalCompleteOCL.g:2714:3: ( (lv_ownedPathElements_0_0= ruleURIFirstPathElementCS ) )
            // InternalCompleteOCL.g:2715:4: (lv_ownedPathElements_0_0= ruleURIFirstPathElementCS )
            {
            // InternalCompleteOCL.g:2715:4: (lv_ownedPathElements_0_0= ruleURIFirstPathElementCS )
            // InternalCompleteOCL.g:2716:5: lv_ownedPathElements_0_0= ruleURIFirstPathElementCS
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getURIPathNameCSAccess().getOwnedPathElementsURIFirstPathElementCSParserRuleCall_0_0());

            }
            pushFollow(FollowSets000.FOLLOW_32);
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

            // InternalCompleteOCL.g:2733:3: (otherlv_1= '::' ( (lv_ownedPathElements_2_0= ruleNextPathElementCS ) ) )*
            loop55:
            do {
                int alt55=2;
                int LA55_0 = input.LA(1);

                if ( (LA55_0==75) ) {
                    alt55=1;
                }


                switch (alt55) {
            	case 1 :
            	    // InternalCompleteOCL.g:2734:4: otherlv_1= '::' ( (lv_ownedPathElements_2_0= ruleNextPathElementCS ) )
            	    {
            	    otherlv_1=(Token)match(input,75,FollowSets000.FOLLOW_5); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      				newLeafNode(otherlv_1, grammarAccess.getURIPathNameCSAccess().getColonColonKeyword_1_0());

            	    }
            	    // InternalCompleteOCL.g:2738:4: ( (lv_ownedPathElements_2_0= ruleNextPathElementCS ) )
            	    // InternalCompleteOCL.g:2739:5: (lv_ownedPathElements_2_0= ruleNextPathElementCS )
            	    {
            	    // InternalCompleteOCL.g:2739:5: (lv_ownedPathElements_2_0= ruleNextPathElementCS )
            	    // InternalCompleteOCL.g:2740:6: lv_ownedPathElements_2_0= ruleNextPathElementCS
            	    {
            	    if ( state.backtracking==0 ) {

            	      						newCompositeNode(grammarAccess.getURIPathNameCSAccess().getOwnedPathElementsNextPathElementCSParserRuleCall_1_1_0());

            	    }
            	    pushFollow(FollowSets000.FOLLOW_32);
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
            	    break loop55;
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
    // InternalCompleteOCL.g:2762:1: entryRuleURIFirstPathElementCS returns [EObject current=null] : iv_ruleURIFirstPathElementCS= ruleURIFirstPathElementCS EOF ;
    public final EObject entryRuleURIFirstPathElementCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleURIFirstPathElementCS = null;


        try {
            // InternalCompleteOCL.g:2762:62: (iv_ruleURIFirstPathElementCS= ruleURIFirstPathElementCS EOF )
            // InternalCompleteOCL.g:2763:2: iv_ruleURIFirstPathElementCS= ruleURIFirstPathElementCS EOF
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
    // InternalCompleteOCL.g:2769:1: ruleURIFirstPathElementCS returns [EObject current=null] : ( ( ( ruleUnrestrictedName ) ) | ( () ( ( ruleURI ) ) ) ) ;
    public final EObject ruleURIFirstPathElementCS() throws RecognitionException {
        EObject current = null;


        	enterRule();

        try {
            // InternalCompleteOCL.g:2775:2: ( ( ( ( ruleUnrestrictedName ) ) | ( () ( ( ruleURI ) ) ) ) )
            // InternalCompleteOCL.g:2776:2: ( ( ( ruleUnrestrictedName ) ) | ( () ( ( ruleURI ) ) ) )
            {
            // InternalCompleteOCL.g:2776:2: ( ( ( ruleUnrestrictedName ) ) | ( () ( ( ruleURI ) ) ) )
            int alt56=2;
            int LA56_0 = input.LA(1);

            if ( ((LA56_0>=RULE_SIMPLE_ID && LA56_0<=RULE_ESCAPED_ID)||(LA56_0>=30 && LA56_0<=32)) ) {
                alt56=1;
            }
            else if ( (LA56_0==RULE_SINGLE_QUOTED_STRING) ) {
                alt56=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 56, 0, input);

                throw nvae;
            }
            switch (alt56) {
                case 1 :
                    // InternalCompleteOCL.g:2777:3: ( ( ruleUnrestrictedName ) )
                    {
                    // InternalCompleteOCL.g:2777:3: ( ( ruleUnrestrictedName ) )
                    // InternalCompleteOCL.g:2778:4: ( ruleUnrestrictedName )
                    {
                    // InternalCompleteOCL.g:2778:4: ( ruleUnrestrictedName )
                    // InternalCompleteOCL.g:2779:5: ruleUnrestrictedName
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
                    // InternalCompleteOCL.g:2797:3: ( () ( ( ruleURI ) ) )
                    {
                    // InternalCompleteOCL.g:2797:3: ( () ( ( ruleURI ) ) )
                    // InternalCompleteOCL.g:2798:4: () ( ( ruleURI ) )
                    {
                    // InternalCompleteOCL.g:2798:4: ()
                    // InternalCompleteOCL.g:2799:5:
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

                    // InternalCompleteOCL.g:2808:4: ( ( ruleURI ) )
                    // InternalCompleteOCL.g:2809:5: ( ruleURI )
                    {
                    // InternalCompleteOCL.g:2809:5: ( ruleURI )
                    // InternalCompleteOCL.g:2810:6: ruleURI
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
    // InternalCompleteOCL.g:2832:1: entryRulePrimitiveTypeCS returns [EObject current=null] : iv_rulePrimitiveTypeCS= rulePrimitiveTypeCS EOF ;
    public final EObject entryRulePrimitiveTypeCS() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePrimitiveTypeCS = null;


        try {
            // InternalCompleteOCL.g:2832:56: (iv_rulePrimitiveTypeCS= rulePrimitiveTypeCS EOF )
            // InternalCompleteOCL.g:2833:2: iv_rulePrimitiveTypeCS= rulePrimitiveTypeCS EOF
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
    // InternalCompleteOCL.g:2839:1: rulePrimitiveTypeCS returns [EObject current=null] : ( (lv_name_0_0= rulePrimitiveTypeIdentifier ) ) ;
    public final EObject rulePrimitiveTypeCS() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_name_0_0 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:2845:2: ( ( (lv_name_0_0= rulePrimitiveTypeIdentifier ) ) )
            // InternalCompleteOCL.g:2846:2: ( (lv_name_0_0= rulePrimitiveTypeIdentifier ) )
            {
            // InternalCompleteOCL.g:2846:2: ( (lv_name_0_0= rulePrimitiveTypeIdentifier ) )
            // InternalCompleteOCL.g:2847:3: (lv_name_0_0= rulePrimitiveTypeIdentifier )
            {
            // InternalCompleteOCL.g:2847:3: (lv_name_0_0= rulePrimitiveTypeIdentifier )
            // InternalCompleteOCL.g:2848:4: lv_name_0_0= rulePrimitiveTypeIdentifier
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
    // InternalCompleteOCL.g:2868:1: entryRuleCollectionTypeIdentifier returns [String current=null] : iv_ruleCollectionTypeIdentifier= ruleCollectionTypeIdentifier EOF ;
    public final String entryRuleCollectionTypeIdentifier() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleCollectionTypeIdentifier = null;


        try {
            // InternalCompleteOCL.g:2868:64: (iv_ruleCollectionTypeIdentifier= ruleCollectionTypeIdentifier EOF )
            // InternalCompleteOCL.g:2869:2: iv_ruleCollectionTypeIdentifier= ruleCollectionTypeIdentifier EOF
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
    // InternalCompleteOCL.g:2875:1: ruleCollectionTypeIdentifier returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= 'Set' | kw= 'Bag' | kw= 'Sequence' | kw= 'Collection' | kw= 'OrderedSet' ) ;
    public final AntlrDatatypeRuleToken ruleCollectionTypeIdentifier() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;


        	enterRule();

        try {
            // InternalCompleteOCL.g:2881:2: ( (kw= 'Set' | kw= 'Bag' | kw= 'Sequence' | kw= 'Collection' | kw= 'OrderedSet' ) )
            // InternalCompleteOCL.g:2882:2: (kw= 'Set' | kw= 'Bag' | kw= 'Sequence' | kw= 'Collection' | kw= 'OrderedSet' )
            {
            // InternalCompleteOCL.g:2882:2: (kw= 'Set' | kw= 'Bag' | kw= 'Sequence' | kw= 'Collection' | kw= 'OrderedSet' )
            int alt57=5;
            switch ( input.LA(1) ) {
            case 76:
                {
                alt57=1;
                }
                break;
            case 77:
                {
                alt57=2;
                }
                break;
            case 78:
                {
                alt57=3;
                }
                break;
            case 79:
                {
                alt57=4;
                }
                break;
            case 80:
                {
                alt57=5;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 57, 0, input);

                throw nvae;
            }

            switch (alt57) {
                case 1 :
                    // InternalCompleteOCL.g:2883:3: kw= 'Set'
                    {
                    kw=(Token)match(input,76,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getCollectionTypeIdentifierAccess().getSetKeyword_0());

                    }

                    }
                    break;
                case 2 :
                    // InternalCompleteOCL.g:2889:3: kw= 'Bag'
                    {
                    kw=(Token)match(input,77,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getCollectionTypeIdentifierAccess().getBagKeyword_1());

                    }

                    }
                    break;
                case 3 :
                    // InternalCompleteOCL.g:2895:3: kw= 'Sequence'
                    {
                    kw=(Token)match(input,78,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getCollectionTypeIdentifierAccess().getSequenceKeyword_2());

                    }

                    }
                    break;
                case 4 :
                    // InternalCompleteOCL.g:2901:3: kw= 'Collection'
                    {
                    kw=(Token)match(input,79,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getCollectionTypeIdentifierAccess().getCollectionKeyword_3());

                    }

                    }
                    break;
                case 5 :
                    // InternalCompleteOCL.g:2907:3: kw= 'OrderedSet'
                    {
                    kw=(Token)match(input,80,FollowSets000.FOLLOW_2); if (state.failed) return current;
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
    // InternalCompleteOCL.g:2916:1: entryRuleCollectionTypeCS returns [EObject current=null] : iv_ruleCollectionTypeCS= ruleCollectionTypeCS EOF ;
    public final EObject entryRuleCollectionTypeCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCollectionTypeCS = null;


        try {
            // InternalCompleteOCL.g:2916:57: (iv_ruleCollectionTypeCS= ruleCollectionTypeCS EOF )
            // InternalCompleteOCL.g:2917:2: iv_ruleCollectionTypeCS= ruleCollectionTypeCS EOF
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
    // InternalCompleteOCL.g:2923:1: ruleCollectionTypeCS returns [EObject current=null] : ( ( (lv_name_0_0= ruleCollectionTypeIdentifier ) ) (otherlv_1= '(' ( (lv_ownedType_2_0= ruleTypeExpWithoutMultiplicityCS ) ) ( (lv_ownedCollectionMultiplicity_3_0= ruleMultiplicityCS ) )? otherlv_4= ')' )? ) ;
    public final EObject ruleCollectionTypeCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_4=null;
        AntlrDatatypeRuleToken lv_name_0_0 = null;

        EObject lv_ownedType_2_0 = null;

        EObject lv_ownedCollectionMultiplicity_3_0 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:2929:2: ( ( ( (lv_name_0_0= ruleCollectionTypeIdentifier ) ) (otherlv_1= '(' ( (lv_ownedType_2_0= ruleTypeExpWithoutMultiplicityCS ) ) ( (lv_ownedCollectionMultiplicity_3_0= ruleMultiplicityCS ) )? otherlv_4= ')' )? ) )
            // InternalCompleteOCL.g:2930:2: ( ( (lv_name_0_0= ruleCollectionTypeIdentifier ) ) (otherlv_1= '(' ( (lv_ownedType_2_0= ruleTypeExpWithoutMultiplicityCS ) ) ( (lv_ownedCollectionMultiplicity_3_0= ruleMultiplicityCS ) )? otherlv_4= ')' )? )
            {
            // InternalCompleteOCL.g:2930:2: ( ( (lv_name_0_0= ruleCollectionTypeIdentifier ) ) (otherlv_1= '(' ( (lv_ownedType_2_0= ruleTypeExpWithoutMultiplicityCS ) ) ( (lv_ownedCollectionMultiplicity_3_0= ruleMultiplicityCS ) )? otherlv_4= ')' )? )
            // InternalCompleteOCL.g:2931:3: ( (lv_name_0_0= ruleCollectionTypeIdentifier ) ) (otherlv_1= '(' ( (lv_ownedType_2_0= ruleTypeExpWithoutMultiplicityCS ) ) ( (lv_ownedCollectionMultiplicity_3_0= ruleMultiplicityCS ) )? otherlv_4= ')' )?
            {
            // InternalCompleteOCL.g:2931:3: ( (lv_name_0_0= ruleCollectionTypeIdentifier ) )
            // InternalCompleteOCL.g:2932:4: (lv_name_0_0= ruleCollectionTypeIdentifier )
            {
            // InternalCompleteOCL.g:2932:4: (lv_name_0_0= ruleCollectionTypeIdentifier )
            // InternalCompleteOCL.g:2933:5: lv_name_0_0= ruleCollectionTypeIdentifier
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getCollectionTypeCSAccess().getNameCollectionTypeIdentifierParserRuleCall_0_0());

            }
            pushFollow(FollowSets000.FOLLOW_33);
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

            // InternalCompleteOCL.g:2950:3: (otherlv_1= '(' ( (lv_ownedType_2_0= ruleTypeExpWithoutMultiplicityCS ) ) ( (lv_ownedCollectionMultiplicity_3_0= ruleMultiplicityCS ) )? otherlv_4= ')' )?
            int alt59=2;
            int LA59_0 = input.LA(1);

            if ( (LA59_0==21) ) {
                alt59=1;
            }
            switch (alt59) {
                case 1 :
                    // InternalCompleteOCL.g:2951:4: otherlv_1= '(' ( (lv_ownedType_2_0= ruleTypeExpWithoutMultiplicityCS ) ) ( (lv_ownedCollectionMultiplicity_3_0= ruleMultiplicityCS ) )? otherlv_4= ')'
                    {
                    otherlv_1=(Token)match(input,21,FollowSets000.FOLLOW_21); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_1, grammarAccess.getCollectionTypeCSAccess().getLeftParenthesisKeyword_1_0());

                    }
                    // InternalCompleteOCL.g:2955:4: ( (lv_ownedType_2_0= ruleTypeExpWithoutMultiplicityCS ) )
                    // InternalCompleteOCL.g:2956:5: (lv_ownedType_2_0= ruleTypeExpWithoutMultiplicityCS )
                    {
                    // InternalCompleteOCL.g:2956:5: (lv_ownedType_2_0= ruleTypeExpWithoutMultiplicityCS )
                    // InternalCompleteOCL.g:2957:6: lv_ownedType_2_0= ruleTypeExpWithoutMultiplicityCS
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getCollectionTypeCSAccess().getOwnedTypeTypeExpWithoutMultiplicityCSParserRuleCall_1_1_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_34);
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

                    // InternalCompleteOCL.g:2974:4: ( (lv_ownedCollectionMultiplicity_3_0= ruleMultiplicityCS ) )?
                    int alt58=2;
                    int LA58_0 = input.LA(1);

                    if ( (LA58_0==93) ) {
                        alt58=1;
                    }
                    switch (alt58) {
                        case 1 :
                            // InternalCompleteOCL.g:2975:5: (lv_ownedCollectionMultiplicity_3_0= ruleMultiplicityCS )
                            {
                            // InternalCompleteOCL.g:2975:5: (lv_ownedCollectionMultiplicity_3_0= ruleMultiplicityCS )
                            // InternalCompleteOCL.g:2976:6: lv_ownedCollectionMultiplicity_3_0= ruleMultiplicityCS
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
    // InternalCompleteOCL.g:3002:1: entryRuleMapTypeCS returns [EObject current=null] : iv_ruleMapTypeCS= ruleMapTypeCS EOF ;
    public final EObject entryRuleMapTypeCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMapTypeCS = null;


        try {
            // InternalCompleteOCL.g:3002:50: (iv_ruleMapTypeCS= ruleMapTypeCS EOF )
            // InternalCompleteOCL.g:3003:2: iv_ruleMapTypeCS= ruleMapTypeCS EOF
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
    // InternalCompleteOCL.g:3009:1: ruleMapTypeCS returns [EObject current=null] : ( ( (lv_name_0_0= 'Map' ) ) (otherlv_1= '(' ( (lv_ownedKeyType_2_0= ruleTypeExpCS ) ) otherlv_3= ',' ( (lv_ownedValueType_4_0= ruleTypeExpCS ) ) otherlv_5= ')' )? ) ;
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
            // InternalCompleteOCL.g:3015:2: ( ( ( (lv_name_0_0= 'Map' ) ) (otherlv_1= '(' ( (lv_ownedKeyType_2_0= ruleTypeExpCS ) ) otherlv_3= ',' ( (lv_ownedValueType_4_0= ruleTypeExpCS ) ) otherlv_5= ')' )? ) )
            // InternalCompleteOCL.g:3016:2: ( ( (lv_name_0_0= 'Map' ) ) (otherlv_1= '(' ( (lv_ownedKeyType_2_0= ruleTypeExpCS ) ) otherlv_3= ',' ( (lv_ownedValueType_4_0= ruleTypeExpCS ) ) otherlv_5= ')' )? )
            {
            // InternalCompleteOCL.g:3016:2: ( ( (lv_name_0_0= 'Map' ) ) (otherlv_1= '(' ( (lv_ownedKeyType_2_0= ruleTypeExpCS ) ) otherlv_3= ',' ( (lv_ownedValueType_4_0= ruleTypeExpCS ) ) otherlv_5= ')' )? )
            // InternalCompleteOCL.g:3017:3: ( (lv_name_0_0= 'Map' ) ) (otherlv_1= '(' ( (lv_ownedKeyType_2_0= ruleTypeExpCS ) ) otherlv_3= ',' ( (lv_ownedValueType_4_0= ruleTypeExpCS ) ) otherlv_5= ')' )?
            {
            // InternalCompleteOCL.g:3017:3: ( (lv_name_0_0= 'Map' ) )
            // InternalCompleteOCL.g:3018:4: (lv_name_0_0= 'Map' )
            {
            // InternalCompleteOCL.g:3018:4: (lv_name_0_0= 'Map' )
            // InternalCompleteOCL.g:3019:5: lv_name_0_0= 'Map'
            {
            lv_name_0_0=(Token)match(input,73,FollowSets000.FOLLOW_33); if (state.failed) return current;
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

            // InternalCompleteOCL.g:3031:3: (otherlv_1= '(' ( (lv_ownedKeyType_2_0= ruleTypeExpCS ) ) otherlv_3= ',' ( (lv_ownedValueType_4_0= ruleTypeExpCS ) ) otherlv_5= ')' )?
            int alt60=2;
            int LA60_0 = input.LA(1);

            if ( (LA60_0==21) ) {
                alt60=1;
            }
            switch (alt60) {
                case 1 :
                    // InternalCompleteOCL.g:3032:4: otherlv_1= '(' ( (lv_ownedKeyType_2_0= ruleTypeExpCS ) ) otherlv_3= ',' ( (lv_ownedValueType_4_0= ruleTypeExpCS ) ) otherlv_5= ')'
                    {
                    otherlv_1=(Token)match(input,21,FollowSets000.FOLLOW_21); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_1, grammarAccess.getMapTypeCSAccess().getLeftParenthesisKeyword_1_0());

                    }
                    // InternalCompleteOCL.g:3036:4: ( (lv_ownedKeyType_2_0= ruleTypeExpCS ) )
                    // InternalCompleteOCL.g:3037:5: (lv_ownedKeyType_2_0= ruleTypeExpCS )
                    {
                    // InternalCompleteOCL.g:3037:5: (lv_ownedKeyType_2_0= ruleTypeExpCS )
                    // InternalCompleteOCL.g:3038:6: lv_ownedKeyType_2_0= ruleTypeExpCS
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getMapTypeCSAccess().getOwnedKeyTypeTypeExpCSParserRuleCall_1_1_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_35);
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

                    otherlv_3=(Token)match(input,28,FollowSets000.FOLLOW_21); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_3, grammarAccess.getMapTypeCSAccess().getCommaKeyword_1_2());

                    }
                    // InternalCompleteOCL.g:3059:4: ( (lv_ownedValueType_4_0= ruleTypeExpCS ) )
                    // InternalCompleteOCL.g:3060:5: (lv_ownedValueType_4_0= ruleTypeExpCS )
                    {
                    // InternalCompleteOCL.g:3060:5: (lv_ownedValueType_4_0= ruleTypeExpCS )
                    // InternalCompleteOCL.g:3061:6: lv_ownedValueType_4_0= ruleTypeExpCS
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
    // InternalCompleteOCL.g:3087:1: entryRuleTupleTypeCS returns [EObject current=null] : iv_ruleTupleTypeCS= ruleTupleTypeCS EOF ;
    public final EObject entryRuleTupleTypeCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTupleTypeCS = null;


        try {
            // InternalCompleteOCL.g:3087:52: (iv_ruleTupleTypeCS= ruleTupleTypeCS EOF )
            // InternalCompleteOCL.g:3088:2: iv_ruleTupleTypeCS= ruleTupleTypeCS EOF
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
    // InternalCompleteOCL.g:3094:1: ruleTupleTypeCS returns [EObject current=null] : ( ( (lv_name_0_0= 'Tuple' ) ) (otherlv_1= '(' ( ( (lv_ownedParts_2_0= ruleTuplePartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleTuplePartCS ) ) )* )? otherlv_5= ')' )? ) ;
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
            // InternalCompleteOCL.g:3100:2: ( ( ( (lv_name_0_0= 'Tuple' ) ) (otherlv_1= '(' ( ( (lv_ownedParts_2_0= ruleTuplePartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleTuplePartCS ) ) )* )? otherlv_5= ')' )? ) )
            // InternalCompleteOCL.g:3101:2: ( ( (lv_name_0_0= 'Tuple' ) ) (otherlv_1= '(' ( ( (lv_ownedParts_2_0= ruleTuplePartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleTuplePartCS ) ) )* )? otherlv_5= ')' )? )
            {
            // InternalCompleteOCL.g:3101:2: ( ( (lv_name_0_0= 'Tuple' ) ) (otherlv_1= '(' ( ( (lv_ownedParts_2_0= ruleTuplePartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleTuplePartCS ) ) )* )? otherlv_5= ')' )? )
            // InternalCompleteOCL.g:3102:3: ( (lv_name_0_0= 'Tuple' ) ) (otherlv_1= '(' ( ( (lv_ownedParts_2_0= ruleTuplePartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleTuplePartCS ) ) )* )? otherlv_5= ')' )?
            {
            // InternalCompleteOCL.g:3102:3: ( (lv_name_0_0= 'Tuple' ) )
            // InternalCompleteOCL.g:3103:4: (lv_name_0_0= 'Tuple' )
            {
            // InternalCompleteOCL.g:3103:4: (lv_name_0_0= 'Tuple' )
            // InternalCompleteOCL.g:3104:5: lv_name_0_0= 'Tuple'
            {
            lv_name_0_0=(Token)match(input,74,FollowSets000.FOLLOW_33); if (state.failed) return current;
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

            // InternalCompleteOCL.g:3116:3: (otherlv_1= '(' ( ( (lv_ownedParts_2_0= ruleTuplePartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleTuplePartCS ) ) )* )? otherlv_5= ')' )?
            int alt63=2;
            int LA63_0 = input.LA(1);

            if ( (LA63_0==21) ) {
                alt63=1;
            }
            switch (alt63) {
                case 1 :
                    // InternalCompleteOCL.g:3117:4: otherlv_1= '(' ( ( (lv_ownedParts_2_0= ruleTuplePartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleTuplePartCS ) ) )* )? otherlv_5= ')'
                    {
                    otherlv_1=(Token)match(input,21,FollowSets000.FOLLOW_17); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_1, grammarAccess.getTupleTypeCSAccess().getLeftParenthesisKeyword_1_0());

                    }
                    // InternalCompleteOCL.g:3121:4: ( ( (lv_ownedParts_2_0= ruleTuplePartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleTuplePartCS ) ) )* )?
                    int alt62=2;
                    int LA62_0 = input.LA(1);

                    if ( ((LA62_0>=RULE_SIMPLE_ID && LA62_0<=RULE_ESCAPED_ID)||(LA62_0>=30 && LA62_0<=32)) ) {
                        alt62=1;
                    }
                    switch (alt62) {
                        case 1 :
                            // InternalCompleteOCL.g:3122:5: ( (lv_ownedParts_2_0= ruleTuplePartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleTuplePartCS ) ) )*
                            {
                            // InternalCompleteOCL.g:3122:5: ( (lv_ownedParts_2_0= ruleTuplePartCS ) )
                            // InternalCompleteOCL.g:3123:6: (lv_ownedParts_2_0= ruleTuplePartCS )
                            {
                            // InternalCompleteOCL.g:3123:6: (lv_ownedParts_2_0= ruleTuplePartCS )
                            // InternalCompleteOCL.g:3124:7: lv_ownedParts_2_0= ruleTuplePartCS
                            {
                            if ( state.backtracking==0 ) {

                              							newCompositeNode(grammarAccess.getTupleTypeCSAccess().getOwnedPartsTuplePartCSParserRuleCall_1_1_0_0());

                            }
                            pushFollow(FollowSets000.FOLLOW_18);
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

                            // InternalCompleteOCL.g:3141:5: (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleTuplePartCS ) ) )*
                            loop61:
                            do {
                                int alt61=2;
                                int LA61_0 = input.LA(1);

                                if ( (LA61_0==28) ) {
                                    alt61=1;
                                }


                                switch (alt61) {
                            	case 1 :
                            	    // InternalCompleteOCL.g:3142:6: otherlv_3= ',' ( (lv_ownedParts_4_0= ruleTuplePartCS ) )
                            	    {
                            	    otherlv_3=(Token)match(input,28,FollowSets000.FOLLOW_15); if (state.failed) return current;
                            	    if ( state.backtracking==0 ) {

                            	      						newLeafNode(otherlv_3, grammarAccess.getTupleTypeCSAccess().getCommaKeyword_1_1_1_0());

                            	    }
                            	    // InternalCompleteOCL.g:3146:6: ( (lv_ownedParts_4_0= ruleTuplePartCS ) )
                            	    // InternalCompleteOCL.g:3147:7: (lv_ownedParts_4_0= ruleTuplePartCS )
                            	    {
                            	    // InternalCompleteOCL.g:3147:7: (lv_ownedParts_4_0= ruleTuplePartCS )
                            	    // InternalCompleteOCL.g:3148:8: lv_ownedParts_4_0= ruleTuplePartCS
                            	    {
                            	    if ( state.backtracking==0 ) {

                            	      								newCompositeNode(grammarAccess.getTupleTypeCSAccess().getOwnedPartsTuplePartCSParserRuleCall_1_1_1_1_0());

                            	    }
                            	    pushFollow(FollowSets000.FOLLOW_18);
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
                            	    break loop61;
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
    // InternalCompleteOCL.g:3176:1: entryRuleTuplePartCS returns [EObject current=null] : iv_ruleTuplePartCS= ruleTuplePartCS EOF ;
    public final EObject entryRuleTuplePartCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTuplePartCS = null;


        try {
            // InternalCompleteOCL.g:3176:52: (iv_ruleTuplePartCS= ruleTuplePartCS EOF )
            // InternalCompleteOCL.g:3177:2: iv_ruleTuplePartCS= ruleTuplePartCS EOF
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
    // InternalCompleteOCL.g:3183:1: ruleTuplePartCS returns [EObject current=null] : ( ( (lv_name_0_0= ruleUnrestrictedName ) ) otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) ) ) ;
    public final EObject ruleTuplePartCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_name_0_0 = null;

        EObject lv_ownedType_2_0 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:3189:2: ( ( ( (lv_name_0_0= ruleUnrestrictedName ) ) otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) ) ) )
            // InternalCompleteOCL.g:3190:2: ( ( (lv_name_0_0= ruleUnrestrictedName ) ) otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) ) )
            {
            // InternalCompleteOCL.g:3190:2: ( ( (lv_name_0_0= ruleUnrestrictedName ) ) otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) ) )
            // InternalCompleteOCL.g:3191:3: ( (lv_name_0_0= ruleUnrestrictedName ) ) otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) )
            {
            // InternalCompleteOCL.g:3191:3: ( (lv_name_0_0= ruleUnrestrictedName ) )
            // InternalCompleteOCL.g:3192:4: (lv_name_0_0= ruleUnrestrictedName )
            {
            // InternalCompleteOCL.g:3192:4: (lv_name_0_0= ruleUnrestrictedName )
            // InternalCompleteOCL.g:3193:5: lv_name_0_0= ruleUnrestrictedName
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

            otherlv_1=(Token)match(input,23,FollowSets000.FOLLOW_21); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getTuplePartCSAccess().getColonKeyword_1());

            }
            // InternalCompleteOCL.g:3214:3: ( (lv_ownedType_2_0= ruleTypeExpCS ) )
            // InternalCompleteOCL.g:3215:4: (lv_ownedType_2_0= ruleTypeExpCS )
            {
            // InternalCompleteOCL.g:3215:4: (lv_ownedType_2_0= ruleTypeExpCS )
            // InternalCompleteOCL.g:3216:5: lv_ownedType_2_0= ruleTypeExpCS
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
    // InternalCompleteOCL.g:3237:1: entryRuleCollectionLiteralExpCS returns [EObject current=null] : iv_ruleCollectionLiteralExpCS= ruleCollectionLiteralExpCS EOF ;
    public final EObject entryRuleCollectionLiteralExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCollectionLiteralExpCS = null;


        try {
            // InternalCompleteOCL.g:3237:63: (iv_ruleCollectionLiteralExpCS= ruleCollectionLiteralExpCS EOF )
            // InternalCompleteOCL.g:3238:2: iv_ruleCollectionLiteralExpCS= ruleCollectionLiteralExpCS EOF
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
    // InternalCompleteOCL.g:3244:1: ruleCollectionLiteralExpCS returns [EObject current=null] : ( ( (lv_ownedType_0_0= ruleCollectionTypeCS ) ) otherlv_1= '{' ( ( (lv_ownedParts_2_0= ruleCollectionLiteralPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleCollectionLiteralPartCS ) ) )* )? otherlv_5= '}' ) ;
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
            // InternalCompleteOCL.g:3250:2: ( ( ( (lv_ownedType_0_0= ruleCollectionTypeCS ) ) otherlv_1= '{' ( ( (lv_ownedParts_2_0= ruleCollectionLiteralPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleCollectionLiteralPartCS ) ) )* )? otherlv_5= '}' ) )
            // InternalCompleteOCL.g:3251:2: ( ( (lv_ownedType_0_0= ruleCollectionTypeCS ) ) otherlv_1= '{' ( ( (lv_ownedParts_2_0= ruleCollectionLiteralPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleCollectionLiteralPartCS ) ) )* )? otherlv_5= '}' )
            {
            // InternalCompleteOCL.g:3251:2: ( ( (lv_ownedType_0_0= ruleCollectionTypeCS ) ) otherlv_1= '{' ( ( (lv_ownedParts_2_0= ruleCollectionLiteralPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleCollectionLiteralPartCS ) ) )* )? otherlv_5= '}' )
            // InternalCompleteOCL.g:3252:3: ( (lv_ownedType_0_0= ruleCollectionTypeCS ) ) otherlv_1= '{' ( ( (lv_ownedParts_2_0= ruleCollectionLiteralPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleCollectionLiteralPartCS ) ) )* )? otherlv_5= '}'
            {
            // InternalCompleteOCL.g:3252:3: ( (lv_ownedType_0_0= ruleCollectionTypeCS ) )
            // InternalCompleteOCL.g:3253:4: (lv_ownedType_0_0= ruleCollectionTypeCS )
            {
            // InternalCompleteOCL.g:3253:4: (lv_ownedType_0_0= ruleCollectionTypeCS )
            // InternalCompleteOCL.g:3254:5: lv_ownedType_0_0= ruleCollectionTypeCS
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getCollectionLiteralExpCSAccess().getOwnedTypeCollectionTypeCSParserRuleCall_0_0());

            }
            pushFollow(FollowSets000.FOLLOW_36);
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

            otherlv_1=(Token)match(input,81,FollowSets000.FOLLOW_37); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getCollectionLiteralExpCSAccess().getLeftCurlyBracketKeyword_1());

            }
            // InternalCompleteOCL.g:3275:3: ( ( (lv_ownedParts_2_0= ruleCollectionLiteralPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleCollectionLiteralPartCS ) ) )* )?
            int alt65=2;
            int LA65_0 = input.LA(1);

            if ( ((LA65_0>=RULE_SIMPLE_ID && LA65_0<=RULE_SINGLE_QUOTED_STRING)||LA65_0==21||LA65_0==23||(LA65_0>=30 && LA65_0<=32)||(LA65_0>=42 && LA65_0<=55)||(LA65_0>=73 && LA65_0<=74)||(LA65_0>=76 && LA65_0<=80)||LA65_0==85||(LA65_0>=88 && LA65_0<=91)||LA65_0==98||(LA65_0>=103 && LA65_0<=104)) ) {
                alt65=1;
            }
            switch (alt65) {
                case 1 :
                    // InternalCompleteOCL.g:3276:4: ( (lv_ownedParts_2_0= ruleCollectionLiteralPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleCollectionLiteralPartCS ) ) )*
                    {
                    // InternalCompleteOCL.g:3276:4: ( (lv_ownedParts_2_0= ruleCollectionLiteralPartCS ) )
                    // InternalCompleteOCL.g:3277:5: (lv_ownedParts_2_0= ruleCollectionLiteralPartCS )
                    {
                    // InternalCompleteOCL.g:3277:5: (lv_ownedParts_2_0= ruleCollectionLiteralPartCS )
                    // InternalCompleteOCL.g:3278:6: lv_ownedParts_2_0= ruleCollectionLiteralPartCS
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getCollectionLiteralExpCSAccess().getOwnedPartsCollectionLiteralPartCSParserRuleCall_2_0_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_38);
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

                    // InternalCompleteOCL.g:3295:4: (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleCollectionLiteralPartCS ) ) )*
                    loop64:
                    do {
                        int alt64=2;
                        int LA64_0 = input.LA(1);

                        if ( (LA64_0==28) ) {
                            alt64=1;
                        }


                        switch (alt64) {
                    	case 1 :
                    	    // InternalCompleteOCL.g:3296:5: otherlv_3= ',' ( (lv_ownedParts_4_0= ruleCollectionLiteralPartCS ) )
                    	    {
                    	    otherlv_3=(Token)match(input,28,FollowSets000.FOLLOW_39); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      					newLeafNode(otherlv_3, grammarAccess.getCollectionLiteralExpCSAccess().getCommaKeyword_2_1_0());

                    	    }
                    	    // InternalCompleteOCL.g:3300:5: ( (lv_ownedParts_4_0= ruleCollectionLiteralPartCS ) )
                    	    // InternalCompleteOCL.g:3301:6: (lv_ownedParts_4_0= ruleCollectionLiteralPartCS )
                    	    {
                    	    // InternalCompleteOCL.g:3301:6: (lv_ownedParts_4_0= ruleCollectionLiteralPartCS )
                    	    // InternalCompleteOCL.g:3302:7: lv_ownedParts_4_0= ruleCollectionLiteralPartCS
                    	    {
                    	    if ( state.backtracking==0 ) {

                    	      							newCompositeNode(grammarAccess.getCollectionLiteralExpCSAccess().getOwnedPartsCollectionLiteralPartCSParserRuleCall_2_1_1_0());

                    	    }
                    	    pushFollow(FollowSets000.FOLLOW_38);
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
                    	    break loop64;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_5=(Token)match(input,82,FollowSets000.FOLLOW_2); if (state.failed) return current;
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
    // InternalCompleteOCL.g:3329:1: entryRuleCollectionLiteralPartCS returns [EObject current=null] : iv_ruleCollectionLiteralPartCS= ruleCollectionLiteralPartCS EOF ;
    public final EObject entryRuleCollectionLiteralPartCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCollectionLiteralPartCS = null;


        try {
            // InternalCompleteOCL.g:3329:64: (iv_ruleCollectionLiteralPartCS= ruleCollectionLiteralPartCS EOF )
            // InternalCompleteOCL.g:3330:2: iv_ruleCollectionLiteralPartCS= ruleCollectionLiteralPartCS EOF
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
    // InternalCompleteOCL.g:3336:1: ruleCollectionLiteralPartCS returns [EObject current=null] : ( ( ( (lv_ownedExpression_0_0= ruleExpCS ) ) (otherlv_1= '..' ( (lv_ownedLastExpression_2_0= ruleExpCS ) ) )? ) | ( (lv_ownedExpression_3_0= rulePatternExpCS ) ) ) ;
    public final EObject ruleCollectionLiteralPartCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject lv_ownedExpression_0_0 = null;

        EObject lv_ownedLastExpression_2_0 = null;

        EObject lv_ownedExpression_3_0 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:3342:2: ( ( ( ( (lv_ownedExpression_0_0= ruleExpCS ) ) (otherlv_1= '..' ( (lv_ownedLastExpression_2_0= ruleExpCS ) ) )? ) | ( (lv_ownedExpression_3_0= rulePatternExpCS ) ) ) )
            // InternalCompleteOCL.g:3343:2: ( ( ( (lv_ownedExpression_0_0= ruleExpCS ) ) (otherlv_1= '..' ( (lv_ownedLastExpression_2_0= ruleExpCS ) ) )? ) | ( (lv_ownedExpression_3_0= rulePatternExpCS ) ) )
            {
            // InternalCompleteOCL.g:3343:2: ( ( ( (lv_ownedExpression_0_0= ruleExpCS ) ) (otherlv_1= '..' ( (lv_ownedLastExpression_2_0= ruleExpCS ) ) )? ) | ( (lv_ownedExpression_3_0= rulePatternExpCS ) ) )
            int alt67=2;
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
            case 55:
            case 73:
            case 74:
            case 76:
            case 77:
            case 78:
            case 79:
            case 80:
            case 85:
            case 88:
            case 89:
            case 90:
            case 91:
            case 98:
            case 103:
            case 104:
                {
                alt67=1;
                }
                break;
            case RULE_SIMPLE_ID:
                {
                int LA67_2 = input.LA(2);

                if ( (LA67_2==EOF||(LA67_2>=17 && LA67_2<=18)||LA67_2==21||(LA67_2>=28 && LA67_2<=29)||(LA67_2>=39 && LA67_2<=40)||LA67_2==52||(LA67_2>=55 && LA67_2<=72)||LA67_2==75||(LA67_2>=81 && LA67_2<=83)||(LA67_2>=92 && LA67_2<=93)) ) {
                    alt67=1;
                }
                else if ( (LA67_2==23) ) {
                    alt67=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return current;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 67, 2, input);

                    throw nvae;
                }
                }
                break;
            case RULE_ESCAPED_ID:
                {
                int LA67_3 = input.LA(2);

                if ( (LA67_3==EOF||(LA67_3>=17 && LA67_3<=18)||LA67_3==21||(LA67_3>=28 && LA67_3<=29)||(LA67_3>=39 && LA67_3<=40)||LA67_3==52||(LA67_3>=55 && LA67_3<=72)||LA67_3==75||(LA67_3>=81 && LA67_3<=83)||(LA67_3>=92 && LA67_3<=93)) ) {
                    alt67=1;
                }
                else if ( (LA67_3==23) ) {
                    alt67=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return current;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 67, 3, input);

                    throw nvae;
                }
                }
                break;
            case 30:
                {
                int LA67_4 = input.LA(2);

                if ( (LA67_4==EOF||(LA67_4>=17 && LA67_4<=18)||LA67_4==21||(LA67_4>=28 && LA67_4<=29)||(LA67_4>=39 && LA67_4<=40)||LA67_4==52||(LA67_4>=55 && LA67_4<=72)||LA67_4==75||(LA67_4>=81 && LA67_4<=83)||(LA67_4>=92 && LA67_4<=93)) ) {
                    alt67=1;
                }
                else if ( (LA67_4==23) ) {
                    alt67=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return current;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 67, 4, input);

                    throw nvae;
                }
                }
                break;
            case 31:
                {
                int LA67_5 = input.LA(2);

                if ( (LA67_5==EOF||(LA67_5>=17 && LA67_5<=18)||LA67_5==21||(LA67_5>=28 && LA67_5<=29)||(LA67_5>=39 && LA67_5<=40)||LA67_5==52||(LA67_5>=55 && LA67_5<=72)||LA67_5==75||(LA67_5>=81 && LA67_5<=83)||(LA67_5>=92 && LA67_5<=93)) ) {
                    alt67=1;
                }
                else if ( (LA67_5==23) ) {
                    alt67=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return current;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 67, 5, input);

                    throw nvae;
                }
                }
                break;
            case 32:
                {
                int LA67_6 = input.LA(2);

                if ( (LA67_6==EOF||(LA67_6>=17 && LA67_6<=18)||LA67_6==21||(LA67_6>=28 && LA67_6<=29)||(LA67_6>=39 && LA67_6<=40)||LA67_6==52||(LA67_6>=55 && LA67_6<=72)||LA67_6==75||(LA67_6>=81 && LA67_6<=83)||(LA67_6>=92 && LA67_6<=93)) ) {
                    alt67=1;
                }
                else if ( (LA67_6==23) ) {
                    alt67=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return current;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 67, 6, input);

                    throw nvae;
                }
                }
                break;
            case 23:
                {
                alt67=2;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 67, 0, input);

                throw nvae;
            }

            switch (alt67) {
                case 1 :
                    // InternalCompleteOCL.g:3344:3: ( ( (lv_ownedExpression_0_0= ruleExpCS ) ) (otherlv_1= '..' ( (lv_ownedLastExpression_2_0= ruleExpCS ) ) )? )
                    {
                    // InternalCompleteOCL.g:3344:3: ( ( (lv_ownedExpression_0_0= ruleExpCS ) ) (otherlv_1= '..' ( (lv_ownedLastExpression_2_0= ruleExpCS ) ) )? )
                    // InternalCompleteOCL.g:3345:4: ( (lv_ownedExpression_0_0= ruleExpCS ) ) (otherlv_1= '..' ( (lv_ownedLastExpression_2_0= ruleExpCS ) ) )?
                    {
                    // InternalCompleteOCL.g:3345:4: ( (lv_ownedExpression_0_0= ruleExpCS ) )
                    // InternalCompleteOCL.g:3346:5: (lv_ownedExpression_0_0= ruleExpCS )
                    {
                    // InternalCompleteOCL.g:3346:5: (lv_ownedExpression_0_0= ruleExpCS )
                    // InternalCompleteOCL.g:3347:6: lv_ownedExpression_0_0= ruleExpCS
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getCollectionLiteralPartCSAccess().getOwnedExpressionExpCSParserRuleCall_0_0_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_40);
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

                    // InternalCompleteOCL.g:3364:4: (otherlv_1= '..' ( (lv_ownedLastExpression_2_0= ruleExpCS ) ) )?
                    int alt66=2;
                    int LA66_0 = input.LA(1);

                    if ( (LA66_0==83) ) {
                        alt66=1;
                    }
                    switch (alt66) {
                        case 1 :
                            // InternalCompleteOCL.g:3365:5: otherlv_1= '..' ( (lv_ownedLastExpression_2_0= ruleExpCS ) )
                            {
                            otherlv_1=(Token)match(input,83,FollowSets000.FOLLOW_30); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              					newLeafNode(otherlv_1, grammarAccess.getCollectionLiteralPartCSAccess().getFullStopFullStopKeyword_0_1_0());

                            }
                            // InternalCompleteOCL.g:3369:5: ( (lv_ownedLastExpression_2_0= ruleExpCS ) )
                            // InternalCompleteOCL.g:3370:6: (lv_ownedLastExpression_2_0= ruleExpCS )
                            {
                            // InternalCompleteOCL.g:3370:6: (lv_ownedLastExpression_2_0= ruleExpCS )
                            // InternalCompleteOCL.g:3371:7: lv_ownedLastExpression_2_0= ruleExpCS
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
                    // InternalCompleteOCL.g:3391:3: ( (lv_ownedExpression_3_0= rulePatternExpCS ) )
                    {
                    // InternalCompleteOCL.g:3391:3: ( (lv_ownedExpression_3_0= rulePatternExpCS ) )
                    // InternalCompleteOCL.g:3392:4: (lv_ownedExpression_3_0= rulePatternExpCS )
                    {
                    // InternalCompleteOCL.g:3392:4: (lv_ownedExpression_3_0= rulePatternExpCS )
                    // InternalCompleteOCL.g:3393:5: lv_ownedExpression_3_0= rulePatternExpCS
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
    // InternalCompleteOCL.g:3414:1: entryRuleCollectionPatternCS returns [EObject current=null] : iv_ruleCollectionPatternCS= ruleCollectionPatternCS EOF ;
    public final EObject entryRuleCollectionPatternCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCollectionPatternCS = null;


        try {
            // InternalCompleteOCL.g:3414:60: (iv_ruleCollectionPatternCS= ruleCollectionPatternCS EOF )
            // InternalCompleteOCL.g:3415:2: iv_ruleCollectionPatternCS= ruleCollectionPatternCS EOF
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
    // InternalCompleteOCL.g:3421:1: ruleCollectionPatternCS returns [EObject current=null] : ( ( (lv_ownedType_0_0= ruleCollectionTypeCS ) ) otherlv_1= '{' ( ( (lv_ownedParts_2_0= rulePatternExpCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= rulePatternExpCS ) ) )* (otherlv_5= '++' ( (lv_restVariableName_6_0= ruleIdentifier ) ) ) )? otherlv_7= '}' ) ;
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
            // InternalCompleteOCL.g:3427:2: ( ( ( (lv_ownedType_0_0= ruleCollectionTypeCS ) ) otherlv_1= '{' ( ( (lv_ownedParts_2_0= rulePatternExpCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= rulePatternExpCS ) ) )* (otherlv_5= '++' ( (lv_restVariableName_6_0= ruleIdentifier ) ) ) )? otherlv_7= '}' ) )
            // InternalCompleteOCL.g:3428:2: ( ( (lv_ownedType_0_0= ruleCollectionTypeCS ) ) otherlv_1= '{' ( ( (lv_ownedParts_2_0= rulePatternExpCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= rulePatternExpCS ) ) )* (otherlv_5= '++' ( (lv_restVariableName_6_0= ruleIdentifier ) ) ) )? otherlv_7= '}' )
            {
            // InternalCompleteOCL.g:3428:2: ( ( (lv_ownedType_0_0= ruleCollectionTypeCS ) ) otherlv_1= '{' ( ( (lv_ownedParts_2_0= rulePatternExpCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= rulePatternExpCS ) ) )* (otherlv_5= '++' ( (lv_restVariableName_6_0= ruleIdentifier ) ) ) )? otherlv_7= '}' )
            // InternalCompleteOCL.g:3429:3: ( (lv_ownedType_0_0= ruleCollectionTypeCS ) ) otherlv_1= '{' ( ( (lv_ownedParts_2_0= rulePatternExpCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= rulePatternExpCS ) ) )* (otherlv_5= '++' ( (lv_restVariableName_6_0= ruleIdentifier ) ) ) )? otherlv_7= '}'
            {
            // InternalCompleteOCL.g:3429:3: ( (lv_ownedType_0_0= ruleCollectionTypeCS ) )
            // InternalCompleteOCL.g:3430:4: (lv_ownedType_0_0= ruleCollectionTypeCS )
            {
            // InternalCompleteOCL.g:3430:4: (lv_ownedType_0_0= ruleCollectionTypeCS )
            // InternalCompleteOCL.g:3431:5: lv_ownedType_0_0= ruleCollectionTypeCS
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getCollectionPatternCSAccess().getOwnedTypeCollectionTypeCSParserRuleCall_0_0());

            }
            pushFollow(FollowSets000.FOLLOW_36);
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

            otherlv_1=(Token)match(input,81,FollowSets000.FOLLOW_37); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getCollectionPatternCSAccess().getLeftCurlyBracketKeyword_1());

            }
            // InternalCompleteOCL.g:3452:3: ( ( (lv_ownedParts_2_0= rulePatternExpCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= rulePatternExpCS ) ) )* (otherlv_5= '++' ( (lv_restVariableName_6_0= ruleIdentifier ) ) ) )?
            int alt69=2;
            int LA69_0 = input.LA(1);

            if ( ((LA69_0>=RULE_SIMPLE_ID && LA69_0<=RULE_ESCAPED_ID)||LA69_0==23||(LA69_0>=30 && LA69_0<=32)) ) {
                alt69=1;
            }
            switch (alt69) {
                case 1 :
                    // InternalCompleteOCL.g:3453:4: ( (lv_ownedParts_2_0= rulePatternExpCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= rulePatternExpCS ) ) )* (otherlv_5= '++' ( (lv_restVariableName_6_0= ruleIdentifier ) ) )
                    {
                    // InternalCompleteOCL.g:3453:4: ( (lv_ownedParts_2_0= rulePatternExpCS ) )
                    // InternalCompleteOCL.g:3454:5: (lv_ownedParts_2_0= rulePatternExpCS )
                    {
                    // InternalCompleteOCL.g:3454:5: (lv_ownedParts_2_0= rulePatternExpCS )
                    // InternalCompleteOCL.g:3455:6: lv_ownedParts_2_0= rulePatternExpCS
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

                    // InternalCompleteOCL.g:3472:4: (otherlv_3= ',' ( (lv_ownedParts_4_0= rulePatternExpCS ) ) )*
                    loop68:
                    do {
                        int alt68=2;
                        int LA68_0 = input.LA(1);

                        if ( (LA68_0==28) ) {
                            alt68=1;
                        }


                        switch (alt68) {
                    	case 1 :
                    	    // InternalCompleteOCL.g:3473:5: otherlv_3= ',' ( (lv_ownedParts_4_0= rulePatternExpCS ) )
                    	    {
                    	    otherlv_3=(Token)match(input,28,FollowSets000.FOLLOW_39); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      					newLeafNode(otherlv_3, grammarAccess.getCollectionPatternCSAccess().getCommaKeyword_2_1_0());

                    	    }
                    	    // InternalCompleteOCL.g:3477:5: ( (lv_ownedParts_4_0= rulePatternExpCS ) )
                    	    // InternalCompleteOCL.g:3478:6: (lv_ownedParts_4_0= rulePatternExpCS )
                    	    {
                    	    // InternalCompleteOCL.g:3478:6: (lv_ownedParts_4_0= rulePatternExpCS )
                    	    // InternalCompleteOCL.g:3479:7: lv_ownedParts_4_0= rulePatternExpCS
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
                    	    break loop68;
                        }
                    } while (true);

                    // InternalCompleteOCL.g:3497:4: (otherlv_5= '++' ( (lv_restVariableName_6_0= ruleIdentifier ) ) )
                    // InternalCompleteOCL.g:3498:5: otherlv_5= '++' ( (lv_restVariableName_6_0= ruleIdentifier ) )
                    {
                    otherlv_5=(Token)match(input,84,FollowSets000.FOLLOW_42); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					newLeafNode(otherlv_5, grammarAccess.getCollectionPatternCSAccess().getPlusSignPlusSignKeyword_2_2_0());

                    }
                    // InternalCompleteOCL.g:3502:5: ( (lv_restVariableName_6_0= ruleIdentifier ) )
                    // InternalCompleteOCL.g:3503:6: (lv_restVariableName_6_0= ruleIdentifier )
                    {
                    // InternalCompleteOCL.g:3503:6: (lv_restVariableName_6_0= ruleIdentifier )
                    // InternalCompleteOCL.g:3504:7: lv_restVariableName_6_0= ruleIdentifier
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

            otherlv_7=(Token)match(input,82,FollowSets000.FOLLOW_2); if (state.failed) return current;
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
    // InternalCompleteOCL.g:3531:1: entryRuleShadowPartCS returns [EObject current=null] : iv_ruleShadowPartCS= ruleShadowPartCS EOF ;
    public final EObject entryRuleShadowPartCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleShadowPartCS = null;


        try {
            // InternalCompleteOCL.g:3531:53: (iv_ruleShadowPartCS= ruleShadowPartCS EOF )
            // InternalCompleteOCL.g:3532:2: iv_ruleShadowPartCS= ruleShadowPartCS EOF
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
    // InternalCompleteOCL.g:3538:1: ruleShadowPartCS returns [EObject current=null] : ( ( ( ( ruleUnrestrictedName ) ) otherlv_1= '=' ( ( (lv_ownedInitExpression_2_1= ruleExpCS | lv_ownedInitExpression_2_2= rulePatternExpCS ) ) ) ) | ( (lv_ownedInitExpression_3_0= ruleStringLiteralExpCS ) ) ) ;
    public final EObject ruleShadowPartCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject lv_ownedInitExpression_2_1 = null;

        EObject lv_ownedInitExpression_2_2 = null;

        EObject lv_ownedInitExpression_3_0 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:3544:2: ( ( ( ( ( ruleUnrestrictedName ) ) otherlv_1= '=' ( ( (lv_ownedInitExpression_2_1= ruleExpCS | lv_ownedInitExpression_2_2= rulePatternExpCS ) ) ) ) | ( (lv_ownedInitExpression_3_0= ruleStringLiteralExpCS ) ) ) )
            // InternalCompleteOCL.g:3545:2: ( ( ( ( ruleUnrestrictedName ) ) otherlv_1= '=' ( ( (lv_ownedInitExpression_2_1= ruleExpCS | lv_ownedInitExpression_2_2= rulePatternExpCS ) ) ) ) | ( (lv_ownedInitExpression_3_0= ruleStringLiteralExpCS ) ) )
            {
            // InternalCompleteOCL.g:3545:2: ( ( ( ( ruleUnrestrictedName ) ) otherlv_1= '=' ( ( (lv_ownedInitExpression_2_1= ruleExpCS | lv_ownedInitExpression_2_2= rulePatternExpCS ) ) ) ) | ( (lv_ownedInitExpression_3_0= ruleStringLiteralExpCS ) ) )
            int alt71=2;
            int LA71_0 = input.LA(1);

            if ( ((LA71_0>=RULE_SIMPLE_ID && LA71_0<=RULE_ESCAPED_ID)||(LA71_0>=30 && LA71_0<=32)) ) {
                alt71=1;
            }
            else if ( (LA71_0==RULE_SINGLE_QUOTED_STRING) ) {
                alt71=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 71, 0, input);

                throw nvae;
            }
            switch (alt71) {
                case 1 :
                    // InternalCompleteOCL.g:3546:3: ( ( ( ruleUnrestrictedName ) ) otherlv_1= '=' ( ( (lv_ownedInitExpression_2_1= ruleExpCS | lv_ownedInitExpression_2_2= rulePatternExpCS ) ) ) )
                    {
                    // InternalCompleteOCL.g:3546:3: ( ( ( ruleUnrestrictedName ) ) otherlv_1= '=' ( ( (lv_ownedInitExpression_2_1= ruleExpCS | lv_ownedInitExpression_2_2= rulePatternExpCS ) ) ) )
                    // InternalCompleteOCL.g:3547:4: ( ( ruleUnrestrictedName ) ) otherlv_1= '=' ( ( (lv_ownedInitExpression_2_1= ruleExpCS | lv_ownedInitExpression_2_2= rulePatternExpCS ) ) )
                    {
                    // InternalCompleteOCL.g:3547:4: ( ( ruleUnrestrictedName ) )
                    // InternalCompleteOCL.g:3548:5: ( ruleUnrestrictedName )
                    {
                    // InternalCompleteOCL.g:3548:5: ( ruleUnrestrictedName )
                    // InternalCompleteOCL.g:3549:6: ruleUnrestrictedName
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
                    pushFollow(FollowSets000.FOLLOW_20);
                    ruleUnrestrictedName();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						afterParserOrEnumRuleCall();

                    }

                    }


                    }

                    otherlv_1=(Token)match(input,29,FollowSets000.FOLLOW_39); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_1, grammarAccess.getShadowPartCSAccess().getEqualsSignKeyword_0_1());

                    }
                    // InternalCompleteOCL.g:3570:4: ( ( (lv_ownedInitExpression_2_1= ruleExpCS | lv_ownedInitExpression_2_2= rulePatternExpCS ) ) )
                    // InternalCompleteOCL.g:3571:5: ( (lv_ownedInitExpression_2_1= ruleExpCS | lv_ownedInitExpression_2_2= rulePatternExpCS ) )
                    {
                    // InternalCompleteOCL.g:3571:5: ( (lv_ownedInitExpression_2_1= ruleExpCS | lv_ownedInitExpression_2_2= rulePatternExpCS ) )
                    // InternalCompleteOCL.g:3572:6: (lv_ownedInitExpression_2_1= ruleExpCS | lv_ownedInitExpression_2_2= rulePatternExpCS )
                    {
                    // InternalCompleteOCL.g:3572:6: (lv_ownedInitExpression_2_1= ruleExpCS | lv_ownedInitExpression_2_2= rulePatternExpCS )
                    int alt70=2;
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
                    case 55:
                    case 73:
                    case 74:
                    case 76:
                    case 77:
                    case 78:
                    case 79:
                    case 80:
                    case 85:
                    case 88:
                    case 89:
                    case 90:
                    case 91:
                    case 98:
                    case 103:
                    case 104:
                        {
                        alt70=1;
                        }
                        break;
                    case RULE_SIMPLE_ID:
                        {
                        int LA70_2 = input.LA(2);

                        if ( (LA70_2==23) ) {
                            alt70=2;
                        }
                        else if ( (LA70_2==EOF||(LA70_2>=17 && LA70_2<=18)||LA70_2==21||(LA70_2>=28 && LA70_2<=29)||(LA70_2>=39 && LA70_2<=40)||LA70_2==52||(LA70_2>=55 && LA70_2<=72)||LA70_2==75||(LA70_2>=81 && LA70_2<=82)||(LA70_2>=92 && LA70_2<=93)) ) {
                            alt70=1;
                        }
                        else {
                            if (state.backtracking>0) {state.failed=true; return current;}
                            NoViableAltException nvae =
                                new NoViableAltException("", 70, 2, input);

                            throw nvae;
                        }
                        }
                        break;
                    case RULE_ESCAPED_ID:
                        {
                        int LA70_3 = input.LA(2);

                        if ( (LA70_3==23) ) {
                            alt70=2;
                        }
                        else if ( (LA70_3==EOF||(LA70_3>=17 && LA70_3<=18)||LA70_3==21||(LA70_3>=28 && LA70_3<=29)||(LA70_3>=39 && LA70_3<=40)||LA70_3==52||(LA70_3>=55 && LA70_3<=72)||LA70_3==75||(LA70_3>=81 && LA70_3<=82)||(LA70_3>=92 && LA70_3<=93)) ) {
                            alt70=1;
                        }
                        else {
                            if (state.backtracking>0) {state.failed=true; return current;}
                            NoViableAltException nvae =
                                new NoViableAltException("", 70, 3, input);

                            throw nvae;
                        }
                        }
                        break;
                    case 30:
                        {
                        int LA70_4 = input.LA(2);

                        if ( (LA70_4==EOF||(LA70_4>=17 && LA70_4<=18)||LA70_4==21||(LA70_4>=28 && LA70_4<=29)||(LA70_4>=39 && LA70_4<=40)||LA70_4==52||(LA70_4>=55 && LA70_4<=72)||LA70_4==75||(LA70_4>=81 && LA70_4<=82)||(LA70_4>=92 && LA70_4<=93)) ) {
                            alt70=1;
                        }
                        else if ( (LA70_4==23) ) {
                            alt70=2;
                        }
                        else {
                            if (state.backtracking>0) {state.failed=true; return current;}
                            NoViableAltException nvae =
                                new NoViableAltException("", 70, 4, input);

                            throw nvae;
                        }
                        }
                        break;
                    case 31:
                        {
                        int LA70_5 = input.LA(2);

                        if ( (LA70_5==23) ) {
                            alt70=2;
                        }
                        else if ( (LA70_5==EOF||(LA70_5>=17 && LA70_5<=18)||LA70_5==21||(LA70_5>=28 && LA70_5<=29)||(LA70_5>=39 && LA70_5<=40)||LA70_5==52||(LA70_5>=55 && LA70_5<=72)||LA70_5==75||(LA70_5>=81 && LA70_5<=82)||(LA70_5>=92 && LA70_5<=93)) ) {
                            alt70=1;
                        }
                        else {
                            if (state.backtracking>0) {state.failed=true; return current;}
                            NoViableAltException nvae =
                                new NoViableAltException("", 70, 5, input);

                            throw nvae;
                        }
                        }
                        break;
                    case 32:
                        {
                        int LA70_6 = input.LA(2);

                        if ( (LA70_6==23) ) {
                            alt70=2;
                        }
                        else if ( (LA70_6==EOF||(LA70_6>=17 && LA70_6<=18)||LA70_6==21||(LA70_6>=28 && LA70_6<=29)||(LA70_6>=39 && LA70_6<=40)||LA70_6==52||(LA70_6>=55 && LA70_6<=72)||LA70_6==75||(LA70_6>=81 && LA70_6<=82)||(LA70_6>=92 && LA70_6<=93)) ) {
                            alt70=1;
                        }
                        else {
                            if (state.backtracking>0) {state.failed=true; return current;}
                            NoViableAltException nvae =
                                new NoViableAltException("", 70, 6, input);

                            throw nvae;
                        }
                        }
                        break;
                    case 23:
                        {
                        alt70=2;
                        }
                        break;
                    default:
                        if (state.backtracking>0) {state.failed=true; return current;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 70, 0, input);

                        throw nvae;
                    }

                    switch (alt70) {
                        case 1 :
                            // InternalCompleteOCL.g:3573:7: lv_ownedInitExpression_2_1= ruleExpCS
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
                            // InternalCompleteOCL.g:3589:7: lv_ownedInitExpression_2_2= rulePatternExpCS
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
                    // InternalCompleteOCL.g:3609:3: ( (lv_ownedInitExpression_3_0= ruleStringLiteralExpCS ) )
                    {
                    // InternalCompleteOCL.g:3609:3: ( (lv_ownedInitExpression_3_0= ruleStringLiteralExpCS ) )
                    // InternalCompleteOCL.g:3610:4: (lv_ownedInitExpression_3_0= ruleStringLiteralExpCS )
                    {
                    // InternalCompleteOCL.g:3610:4: (lv_ownedInitExpression_3_0= ruleStringLiteralExpCS )
                    // InternalCompleteOCL.g:3611:5: lv_ownedInitExpression_3_0= ruleStringLiteralExpCS
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
    // InternalCompleteOCL.g:3632:1: entryRulePatternExpCS returns [EObject current=null] : iv_rulePatternExpCS= rulePatternExpCS EOF ;
    public final EObject entryRulePatternExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePatternExpCS = null;


        try {
            // InternalCompleteOCL.g:3632:53: (iv_rulePatternExpCS= rulePatternExpCS EOF )
            // InternalCompleteOCL.g:3633:2: iv_rulePatternExpCS= rulePatternExpCS EOF
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
    // InternalCompleteOCL.g:3639:1: rulePatternExpCS returns [EObject current=null] : ( ( (lv_patternVariableName_0_0= ruleUnrestrictedName ) )? otherlv_1= ':' ( (lv_ownedPatternType_2_0= ruleTypeExpCS ) ) ) ;
    public final EObject rulePatternExpCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_patternVariableName_0_0 = null;

        EObject lv_ownedPatternType_2_0 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:3645:2: ( ( ( (lv_patternVariableName_0_0= ruleUnrestrictedName ) )? otherlv_1= ':' ( (lv_ownedPatternType_2_0= ruleTypeExpCS ) ) ) )
            // InternalCompleteOCL.g:3646:2: ( ( (lv_patternVariableName_0_0= ruleUnrestrictedName ) )? otherlv_1= ':' ( (lv_ownedPatternType_2_0= ruleTypeExpCS ) ) )
            {
            // InternalCompleteOCL.g:3646:2: ( ( (lv_patternVariableName_0_0= ruleUnrestrictedName ) )? otherlv_1= ':' ( (lv_ownedPatternType_2_0= ruleTypeExpCS ) ) )
            // InternalCompleteOCL.g:3647:3: ( (lv_patternVariableName_0_0= ruleUnrestrictedName ) )? otherlv_1= ':' ( (lv_ownedPatternType_2_0= ruleTypeExpCS ) )
            {
            // InternalCompleteOCL.g:3647:3: ( (lv_patternVariableName_0_0= ruleUnrestrictedName ) )?
            int alt72=2;
            int LA72_0 = input.LA(1);

            if ( ((LA72_0>=RULE_SIMPLE_ID && LA72_0<=RULE_ESCAPED_ID)||(LA72_0>=30 && LA72_0<=32)) ) {
                alt72=1;
            }
            switch (alt72) {
                case 1 :
                    // InternalCompleteOCL.g:3648:4: (lv_patternVariableName_0_0= ruleUnrestrictedName )
                    {
                    // InternalCompleteOCL.g:3648:4: (lv_patternVariableName_0_0= ruleUnrestrictedName )
                    // InternalCompleteOCL.g:3649:5: lv_patternVariableName_0_0= ruleUnrestrictedName
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

            otherlv_1=(Token)match(input,23,FollowSets000.FOLLOW_21); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getPatternExpCSAccess().getColonKeyword_1());

            }
            // InternalCompleteOCL.g:3670:3: ( (lv_ownedPatternType_2_0= ruleTypeExpCS ) )
            // InternalCompleteOCL.g:3671:4: (lv_ownedPatternType_2_0= ruleTypeExpCS )
            {
            // InternalCompleteOCL.g:3671:4: (lv_ownedPatternType_2_0= ruleTypeExpCS )
            // InternalCompleteOCL.g:3672:5: lv_ownedPatternType_2_0= ruleTypeExpCS
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
    // InternalCompleteOCL.g:3693:1: entryRuleLambdaLiteralExpCS returns [EObject current=null] : iv_ruleLambdaLiteralExpCS= ruleLambdaLiteralExpCS EOF ;
    public final EObject entryRuleLambdaLiteralExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLambdaLiteralExpCS = null;


        try {
            // InternalCompleteOCL.g:3693:59: (iv_ruleLambdaLiteralExpCS= ruleLambdaLiteralExpCS EOF )
            // InternalCompleteOCL.g:3694:2: iv_ruleLambdaLiteralExpCS= ruleLambdaLiteralExpCS EOF
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
    // InternalCompleteOCL.g:3700:1: ruleLambdaLiteralExpCS returns [EObject current=null] : (otherlv_0= 'Lambda' otherlv_1= '{' ( (lv_ownedExpressionCS_2_0= ruleExpCS ) ) otherlv_3= '}' ) ;
    public final EObject ruleLambdaLiteralExpCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_ownedExpressionCS_2_0 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:3706:2: ( (otherlv_0= 'Lambda' otherlv_1= '{' ( (lv_ownedExpressionCS_2_0= ruleExpCS ) ) otherlv_3= '}' ) )
            // InternalCompleteOCL.g:3707:2: (otherlv_0= 'Lambda' otherlv_1= '{' ( (lv_ownedExpressionCS_2_0= ruleExpCS ) ) otherlv_3= '}' )
            {
            // InternalCompleteOCL.g:3707:2: (otherlv_0= 'Lambda' otherlv_1= '{' ( (lv_ownedExpressionCS_2_0= ruleExpCS ) ) otherlv_3= '}' )
            // InternalCompleteOCL.g:3708:3: otherlv_0= 'Lambda' otherlv_1= '{' ( (lv_ownedExpressionCS_2_0= ruleExpCS ) ) otherlv_3= '}'
            {
            otherlv_0=(Token)match(input,85,FollowSets000.FOLLOW_36); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getLambdaLiteralExpCSAccess().getLambdaKeyword_0());

            }
            otherlv_1=(Token)match(input,81,FollowSets000.FOLLOW_30); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getLambdaLiteralExpCSAccess().getLeftCurlyBracketKeyword_1());

            }
            // InternalCompleteOCL.g:3716:3: ( (lv_ownedExpressionCS_2_0= ruleExpCS ) )
            // InternalCompleteOCL.g:3717:4: (lv_ownedExpressionCS_2_0= ruleExpCS )
            {
            // InternalCompleteOCL.g:3717:4: (lv_ownedExpressionCS_2_0= ruleExpCS )
            // InternalCompleteOCL.g:3718:5: lv_ownedExpressionCS_2_0= ruleExpCS
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

            otherlv_3=(Token)match(input,82,FollowSets000.FOLLOW_2); if (state.failed) return current;
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
    // InternalCompleteOCL.g:3743:1: entryRuleMapLiteralExpCS returns [EObject current=null] : iv_ruleMapLiteralExpCS= ruleMapLiteralExpCS EOF ;
    public final EObject entryRuleMapLiteralExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMapLiteralExpCS = null;


        try {
            // InternalCompleteOCL.g:3743:56: (iv_ruleMapLiteralExpCS= ruleMapLiteralExpCS EOF )
            // InternalCompleteOCL.g:3744:2: iv_ruleMapLiteralExpCS= ruleMapLiteralExpCS EOF
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
    // InternalCompleteOCL.g:3750:1: ruleMapLiteralExpCS returns [EObject current=null] : ( ( (lv_ownedType_0_0= ruleMapTypeCS ) ) otherlv_1= '{' ( ( (lv_ownedParts_2_0= ruleMapLiteralPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleMapLiteralPartCS ) ) )* )? otherlv_5= '}' ) ;
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
            // InternalCompleteOCL.g:3756:2: ( ( ( (lv_ownedType_0_0= ruleMapTypeCS ) ) otherlv_1= '{' ( ( (lv_ownedParts_2_0= ruleMapLiteralPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleMapLiteralPartCS ) ) )* )? otherlv_5= '}' ) )
            // InternalCompleteOCL.g:3757:2: ( ( (lv_ownedType_0_0= ruleMapTypeCS ) ) otherlv_1= '{' ( ( (lv_ownedParts_2_0= ruleMapLiteralPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleMapLiteralPartCS ) ) )* )? otherlv_5= '}' )
            {
            // InternalCompleteOCL.g:3757:2: ( ( (lv_ownedType_0_0= ruleMapTypeCS ) ) otherlv_1= '{' ( ( (lv_ownedParts_2_0= ruleMapLiteralPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleMapLiteralPartCS ) ) )* )? otherlv_5= '}' )
            // InternalCompleteOCL.g:3758:3: ( (lv_ownedType_0_0= ruleMapTypeCS ) ) otherlv_1= '{' ( ( (lv_ownedParts_2_0= ruleMapLiteralPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleMapLiteralPartCS ) ) )* )? otherlv_5= '}'
            {
            // InternalCompleteOCL.g:3758:3: ( (lv_ownedType_0_0= ruleMapTypeCS ) )
            // InternalCompleteOCL.g:3759:4: (lv_ownedType_0_0= ruleMapTypeCS )
            {
            // InternalCompleteOCL.g:3759:4: (lv_ownedType_0_0= ruleMapTypeCS )
            // InternalCompleteOCL.g:3760:5: lv_ownedType_0_0= ruleMapTypeCS
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getMapLiteralExpCSAccess().getOwnedTypeMapTypeCSParserRuleCall_0_0());

            }
            pushFollow(FollowSets000.FOLLOW_36);
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

            otherlv_1=(Token)match(input,81,FollowSets000.FOLLOW_44); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getMapLiteralExpCSAccess().getLeftCurlyBracketKeyword_1());

            }
            // InternalCompleteOCL.g:3781:3: ( ( (lv_ownedParts_2_0= ruleMapLiteralPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleMapLiteralPartCS ) ) )* )?
            int alt74=2;
            int LA74_0 = input.LA(1);

            if ( ((LA74_0>=RULE_SIMPLE_ID && LA74_0<=RULE_SINGLE_QUOTED_STRING)||LA74_0==21||(LA74_0>=30 && LA74_0<=32)||(LA74_0>=42 && LA74_0<=55)||(LA74_0>=73 && LA74_0<=74)||(LA74_0>=76 && LA74_0<=80)||LA74_0==85||(LA74_0>=88 && LA74_0<=91)||LA74_0==98||(LA74_0>=103 && LA74_0<=104)) ) {
                alt74=1;
            }
            switch (alt74) {
                case 1 :
                    // InternalCompleteOCL.g:3782:4: ( (lv_ownedParts_2_0= ruleMapLiteralPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleMapLiteralPartCS ) ) )*
                    {
                    // InternalCompleteOCL.g:3782:4: ( (lv_ownedParts_2_0= ruleMapLiteralPartCS ) )
                    // InternalCompleteOCL.g:3783:5: (lv_ownedParts_2_0= ruleMapLiteralPartCS )
                    {
                    // InternalCompleteOCL.g:3783:5: (lv_ownedParts_2_0= ruleMapLiteralPartCS )
                    // InternalCompleteOCL.g:3784:6: lv_ownedParts_2_0= ruleMapLiteralPartCS
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getMapLiteralExpCSAccess().getOwnedPartsMapLiteralPartCSParserRuleCall_2_0_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_38);
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

                    // InternalCompleteOCL.g:3801:4: (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleMapLiteralPartCS ) ) )*
                    loop73:
                    do {
                        int alt73=2;
                        int LA73_0 = input.LA(1);

                        if ( (LA73_0==28) ) {
                            alt73=1;
                        }


                        switch (alt73) {
                    	case 1 :
                    	    // InternalCompleteOCL.g:3802:5: otherlv_3= ',' ( (lv_ownedParts_4_0= ruleMapLiteralPartCS ) )
                    	    {
                    	    otherlv_3=(Token)match(input,28,FollowSets000.FOLLOW_30); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      					newLeafNode(otherlv_3, grammarAccess.getMapLiteralExpCSAccess().getCommaKeyword_2_1_0());

                    	    }
                    	    // InternalCompleteOCL.g:3806:5: ( (lv_ownedParts_4_0= ruleMapLiteralPartCS ) )
                    	    // InternalCompleteOCL.g:3807:6: (lv_ownedParts_4_0= ruleMapLiteralPartCS )
                    	    {
                    	    // InternalCompleteOCL.g:3807:6: (lv_ownedParts_4_0= ruleMapLiteralPartCS )
                    	    // InternalCompleteOCL.g:3808:7: lv_ownedParts_4_0= ruleMapLiteralPartCS
                    	    {
                    	    if ( state.backtracking==0 ) {

                    	      							newCompositeNode(grammarAccess.getMapLiteralExpCSAccess().getOwnedPartsMapLiteralPartCSParserRuleCall_2_1_1_0());

                    	    }
                    	    pushFollow(FollowSets000.FOLLOW_38);
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
                    	    break loop73;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_5=(Token)match(input,82,FollowSets000.FOLLOW_2); if (state.failed) return current;
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
    // InternalCompleteOCL.g:3835:1: entryRuleMapLiteralPartCS returns [EObject current=null] : iv_ruleMapLiteralPartCS= ruleMapLiteralPartCS EOF ;
    public final EObject entryRuleMapLiteralPartCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMapLiteralPartCS = null;


        try {
            // InternalCompleteOCL.g:3835:57: (iv_ruleMapLiteralPartCS= ruleMapLiteralPartCS EOF )
            // InternalCompleteOCL.g:3836:2: iv_ruleMapLiteralPartCS= ruleMapLiteralPartCS EOF
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
    // InternalCompleteOCL.g:3842:1: ruleMapLiteralPartCS returns [EObject current=null] : ( ( (lv_ownedKey_0_0= ruleExpCS ) ) (otherlv_1= 'with' | otherlv_2= '<-' ) ( (lv_ownedValue_3_0= ruleExpCS ) ) ) ;
    public final EObject ruleMapLiteralPartCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        EObject lv_ownedKey_0_0 = null;

        EObject lv_ownedValue_3_0 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:3848:2: ( ( ( (lv_ownedKey_0_0= ruleExpCS ) ) (otherlv_1= 'with' | otherlv_2= '<-' ) ( (lv_ownedValue_3_0= ruleExpCS ) ) ) )
            // InternalCompleteOCL.g:3849:2: ( ( (lv_ownedKey_0_0= ruleExpCS ) ) (otherlv_1= 'with' | otherlv_2= '<-' ) ( (lv_ownedValue_3_0= ruleExpCS ) ) )
            {
            // InternalCompleteOCL.g:3849:2: ( ( (lv_ownedKey_0_0= ruleExpCS ) ) (otherlv_1= 'with' | otherlv_2= '<-' ) ( (lv_ownedValue_3_0= ruleExpCS ) ) )
            // InternalCompleteOCL.g:3850:3: ( (lv_ownedKey_0_0= ruleExpCS ) ) (otherlv_1= 'with' | otherlv_2= '<-' ) ( (lv_ownedValue_3_0= ruleExpCS ) )
            {
            // InternalCompleteOCL.g:3850:3: ( (lv_ownedKey_0_0= ruleExpCS ) )
            // InternalCompleteOCL.g:3851:4: (lv_ownedKey_0_0= ruleExpCS )
            {
            // InternalCompleteOCL.g:3851:4: (lv_ownedKey_0_0= ruleExpCS )
            // InternalCompleteOCL.g:3852:5: lv_ownedKey_0_0= ruleExpCS
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

            // InternalCompleteOCL.g:3869:3: (otherlv_1= 'with' | otherlv_2= '<-' )
            int alt75=2;
            int LA75_0 = input.LA(1);

            if ( (LA75_0==86) ) {
                alt75=1;
            }
            else if ( (LA75_0==87) ) {
                alt75=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 75, 0, input);

                throw nvae;
            }
            switch (alt75) {
                case 1 :
                    // InternalCompleteOCL.g:3870:4: otherlv_1= 'with'
                    {
                    otherlv_1=(Token)match(input,86,FollowSets000.FOLLOW_30); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_1, grammarAccess.getMapLiteralPartCSAccess().getWithKeyword_1_0());

                    }

                    }
                    break;
                case 2 :
                    // InternalCompleteOCL.g:3875:4: otherlv_2= '<-'
                    {
                    otherlv_2=(Token)match(input,87,FollowSets000.FOLLOW_30); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_2, grammarAccess.getMapLiteralPartCSAccess().getLessThanSignHyphenMinusKeyword_1_1());

                    }

                    }
                    break;

            }

            // InternalCompleteOCL.g:3880:3: ( (lv_ownedValue_3_0= ruleExpCS ) )
            // InternalCompleteOCL.g:3881:4: (lv_ownedValue_3_0= ruleExpCS )
            {
            // InternalCompleteOCL.g:3881:4: (lv_ownedValue_3_0= ruleExpCS )
            // InternalCompleteOCL.g:3882:5: lv_ownedValue_3_0= ruleExpCS
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getMapLiteralPartCSAccess().getOwnedValueExpCSParserRuleCall_2_0());

            }
            pushFollow(FollowSets000.FOLLOW_2);
            lv_ownedValue_3_0=ruleExpCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getMapLiteralPartCSRule());
              					}
              					set(
              						current,
              						"ownedValue",
              						lv_ownedValue_3_0,
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
    // InternalCompleteOCL.g:3903:1: entryRulePrimitiveLiteralExpCS returns [EObject current=null] : iv_rulePrimitiveLiteralExpCS= rulePrimitiveLiteralExpCS EOF ;
    public final EObject entryRulePrimitiveLiteralExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePrimitiveLiteralExpCS = null;


        try {
            // InternalCompleteOCL.g:3903:62: (iv_rulePrimitiveLiteralExpCS= rulePrimitiveLiteralExpCS EOF )
            // InternalCompleteOCL.g:3904:2: iv_rulePrimitiveLiteralExpCS= rulePrimitiveLiteralExpCS EOF
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
    // InternalCompleteOCL.g:3910:1: rulePrimitiveLiteralExpCS returns [EObject current=null] : (this_NumberLiteralExpCS_0= ruleNumberLiteralExpCS | this_StringLiteralExpCS_1= ruleStringLiteralExpCS | this_BooleanLiteralExpCS_2= ruleBooleanLiteralExpCS | this_UnlimitedNaturalLiteralExpCS_3= ruleUnlimitedNaturalLiteralExpCS | this_InvalidLiteralExpCS_4= ruleInvalidLiteralExpCS | this_NullLiteralExpCS_5= ruleNullLiteralExpCS ) ;
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
            // InternalCompleteOCL.g:3916:2: ( (this_NumberLiteralExpCS_0= ruleNumberLiteralExpCS | this_StringLiteralExpCS_1= ruleStringLiteralExpCS | this_BooleanLiteralExpCS_2= ruleBooleanLiteralExpCS | this_UnlimitedNaturalLiteralExpCS_3= ruleUnlimitedNaturalLiteralExpCS | this_InvalidLiteralExpCS_4= ruleInvalidLiteralExpCS | this_NullLiteralExpCS_5= ruleNullLiteralExpCS ) )
            // InternalCompleteOCL.g:3917:2: (this_NumberLiteralExpCS_0= ruleNumberLiteralExpCS | this_StringLiteralExpCS_1= ruleStringLiteralExpCS | this_BooleanLiteralExpCS_2= ruleBooleanLiteralExpCS | this_UnlimitedNaturalLiteralExpCS_3= ruleUnlimitedNaturalLiteralExpCS | this_InvalidLiteralExpCS_4= ruleInvalidLiteralExpCS | this_NullLiteralExpCS_5= ruleNullLiteralExpCS )
            {
            // InternalCompleteOCL.g:3917:2: (this_NumberLiteralExpCS_0= ruleNumberLiteralExpCS | this_StringLiteralExpCS_1= ruleStringLiteralExpCS | this_BooleanLiteralExpCS_2= ruleBooleanLiteralExpCS | this_UnlimitedNaturalLiteralExpCS_3= ruleUnlimitedNaturalLiteralExpCS | this_InvalidLiteralExpCS_4= ruleInvalidLiteralExpCS | this_NullLiteralExpCS_5= ruleNullLiteralExpCS )
            int alt76=6;
            switch ( input.LA(1) ) {
            case RULE_INT:
                {
                alt76=1;
                }
                break;
            case RULE_SINGLE_QUOTED_STRING:
                {
                alt76=2;
                }
                break;
            case 88:
            case 89:
                {
                alt76=3;
                }
                break;
            case 55:
                {
                alt76=4;
                }
                break;
            case 90:
                {
                alt76=5;
                }
                break;
            case 91:
                {
                alt76=6;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 76, 0, input);

                throw nvae;
            }

            switch (alt76) {
                case 1 :
                    // InternalCompleteOCL.g:3918:3: this_NumberLiteralExpCS_0= ruleNumberLiteralExpCS
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
                    // InternalCompleteOCL.g:3930:3: this_StringLiteralExpCS_1= ruleStringLiteralExpCS
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
                    // InternalCompleteOCL.g:3942:3: this_BooleanLiteralExpCS_2= ruleBooleanLiteralExpCS
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
                    // InternalCompleteOCL.g:3954:3: this_UnlimitedNaturalLiteralExpCS_3= ruleUnlimitedNaturalLiteralExpCS
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
                    // InternalCompleteOCL.g:3966:3: this_InvalidLiteralExpCS_4= ruleInvalidLiteralExpCS
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
                    // InternalCompleteOCL.g:3978:3: this_NullLiteralExpCS_5= ruleNullLiteralExpCS
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
    // InternalCompleteOCL.g:3993:1: entryRuleTupleLiteralExpCS returns [EObject current=null] : iv_ruleTupleLiteralExpCS= ruleTupleLiteralExpCS EOF ;
    public final EObject entryRuleTupleLiteralExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTupleLiteralExpCS = null;


        try {
            // InternalCompleteOCL.g:3993:58: (iv_ruleTupleLiteralExpCS= ruleTupleLiteralExpCS EOF )
            // InternalCompleteOCL.g:3994:2: iv_ruleTupleLiteralExpCS= ruleTupleLiteralExpCS EOF
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
    // InternalCompleteOCL.g:4000:1: ruleTupleLiteralExpCS returns [EObject current=null] : (otherlv_0= 'Tuple' otherlv_1= '{' ( (lv_ownedParts_2_0= ruleTupleLiteralPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleTupleLiteralPartCS ) ) )* otherlv_5= '}' ) ;
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
            // InternalCompleteOCL.g:4006:2: ( (otherlv_0= 'Tuple' otherlv_1= '{' ( (lv_ownedParts_2_0= ruleTupleLiteralPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleTupleLiteralPartCS ) ) )* otherlv_5= '}' ) )
            // InternalCompleteOCL.g:4007:2: (otherlv_0= 'Tuple' otherlv_1= '{' ( (lv_ownedParts_2_0= ruleTupleLiteralPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleTupleLiteralPartCS ) ) )* otherlv_5= '}' )
            {
            // InternalCompleteOCL.g:4007:2: (otherlv_0= 'Tuple' otherlv_1= '{' ( (lv_ownedParts_2_0= ruleTupleLiteralPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleTupleLiteralPartCS ) ) )* otherlv_5= '}' )
            // InternalCompleteOCL.g:4008:3: otherlv_0= 'Tuple' otherlv_1= '{' ( (lv_ownedParts_2_0= ruleTupleLiteralPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleTupleLiteralPartCS ) ) )* otherlv_5= '}'
            {
            otherlv_0=(Token)match(input,74,FollowSets000.FOLLOW_36); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getTupleLiteralExpCSAccess().getTupleKeyword_0());

            }
            otherlv_1=(Token)match(input,81,FollowSets000.FOLLOW_15); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getTupleLiteralExpCSAccess().getLeftCurlyBracketKeyword_1());

            }
            // InternalCompleteOCL.g:4016:3: ( (lv_ownedParts_2_0= ruleTupleLiteralPartCS ) )
            // InternalCompleteOCL.g:4017:4: (lv_ownedParts_2_0= ruleTupleLiteralPartCS )
            {
            // InternalCompleteOCL.g:4017:4: (lv_ownedParts_2_0= ruleTupleLiteralPartCS )
            // InternalCompleteOCL.g:4018:5: lv_ownedParts_2_0= ruleTupleLiteralPartCS
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getTupleLiteralExpCSAccess().getOwnedPartsTupleLiteralPartCSParserRuleCall_2_0());

            }
            pushFollow(FollowSets000.FOLLOW_38);
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

            // InternalCompleteOCL.g:4035:3: (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleTupleLiteralPartCS ) ) )*
            loop77:
            do {
                int alt77=2;
                int LA77_0 = input.LA(1);

                if ( (LA77_0==28) ) {
                    alt77=1;
                }


                switch (alt77) {
            	case 1 :
            	    // InternalCompleteOCL.g:4036:4: otherlv_3= ',' ( (lv_ownedParts_4_0= ruleTupleLiteralPartCS ) )
            	    {
            	    otherlv_3=(Token)match(input,28,FollowSets000.FOLLOW_15); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      				newLeafNode(otherlv_3, grammarAccess.getTupleLiteralExpCSAccess().getCommaKeyword_3_0());

            	    }
            	    // InternalCompleteOCL.g:4040:4: ( (lv_ownedParts_4_0= ruleTupleLiteralPartCS ) )
            	    // InternalCompleteOCL.g:4041:5: (lv_ownedParts_4_0= ruleTupleLiteralPartCS )
            	    {
            	    // InternalCompleteOCL.g:4041:5: (lv_ownedParts_4_0= ruleTupleLiteralPartCS )
            	    // InternalCompleteOCL.g:4042:6: lv_ownedParts_4_0= ruleTupleLiteralPartCS
            	    {
            	    if ( state.backtracking==0 ) {

            	      						newCompositeNode(grammarAccess.getTupleLiteralExpCSAccess().getOwnedPartsTupleLiteralPartCSParserRuleCall_3_1_0());

            	    }
            	    pushFollow(FollowSets000.FOLLOW_38);
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
            	    break loop77;
                }
            } while (true);

            otherlv_5=(Token)match(input,82,FollowSets000.FOLLOW_2); if (state.failed) return current;
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
    // InternalCompleteOCL.g:4068:1: entryRuleTupleLiteralPartCS returns [EObject current=null] : iv_ruleTupleLiteralPartCS= ruleTupleLiteralPartCS EOF ;
    public final EObject entryRuleTupleLiteralPartCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTupleLiteralPartCS = null;


        try {
            // InternalCompleteOCL.g:4068:59: (iv_ruleTupleLiteralPartCS= ruleTupleLiteralPartCS EOF )
            // InternalCompleteOCL.g:4069:2: iv_ruleTupleLiteralPartCS= ruleTupleLiteralPartCS EOF
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
    // InternalCompleteOCL.g:4075:1: ruleTupleLiteralPartCS returns [EObject current=null] : ( ( (lv_name_0_0= ruleUnrestrictedName ) ) (otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) ) )? otherlv_3= '=' ( (lv_ownedInitExpression_4_0= ruleExpCS ) ) ) ;
    public final EObject ruleTupleLiteralPartCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        AntlrDatatypeRuleToken lv_name_0_0 = null;

        EObject lv_ownedType_2_0 = null;

        EObject lv_ownedInitExpression_4_0 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:4081:2: ( ( ( (lv_name_0_0= ruleUnrestrictedName ) ) (otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) ) )? otherlv_3= '=' ( (lv_ownedInitExpression_4_0= ruleExpCS ) ) ) )
            // InternalCompleteOCL.g:4082:2: ( ( (lv_name_0_0= ruleUnrestrictedName ) ) (otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) ) )? otherlv_3= '=' ( (lv_ownedInitExpression_4_0= ruleExpCS ) ) )
            {
            // InternalCompleteOCL.g:4082:2: ( ( (lv_name_0_0= ruleUnrestrictedName ) ) (otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) ) )? otherlv_3= '=' ( (lv_ownedInitExpression_4_0= ruleExpCS ) ) )
            // InternalCompleteOCL.g:4083:3: ( (lv_name_0_0= ruleUnrestrictedName ) ) (otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) ) )? otherlv_3= '=' ( (lv_ownedInitExpression_4_0= ruleExpCS ) )
            {
            // InternalCompleteOCL.g:4083:3: ( (lv_name_0_0= ruleUnrestrictedName ) )
            // InternalCompleteOCL.g:4084:4: (lv_name_0_0= ruleUnrestrictedName )
            {
            // InternalCompleteOCL.g:4084:4: (lv_name_0_0= ruleUnrestrictedName )
            // InternalCompleteOCL.g:4085:5: lv_name_0_0= ruleUnrestrictedName
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

            // InternalCompleteOCL.g:4102:3: (otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) ) )?
            int alt78=2;
            int LA78_0 = input.LA(1);

            if ( (LA78_0==23) ) {
                alt78=1;
            }
            switch (alt78) {
                case 1 :
                    // InternalCompleteOCL.g:4103:4: otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) )
                    {
                    otherlv_1=(Token)match(input,23,FollowSets000.FOLLOW_21); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_1, grammarAccess.getTupleLiteralPartCSAccess().getColonKeyword_1_0());

                    }
                    // InternalCompleteOCL.g:4107:4: ( (lv_ownedType_2_0= ruleTypeExpCS ) )
                    // InternalCompleteOCL.g:4108:5: (lv_ownedType_2_0= ruleTypeExpCS )
                    {
                    // InternalCompleteOCL.g:4108:5: (lv_ownedType_2_0= ruleTypeExpCS )
                    // InternalCompleteOCL.g:4109:6: lv_ownedType_2_0= ruleTypeExpCS
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getTupleLiteralPartCSAccess().getOwnedTypeTypeExpCSParserRuleCall_1_1_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_20);
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

            otherlv_3=(Token)match(input,29,FollowSets000.FOLLOW_30); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_3, grammarAccess.getTupleLiteralPartCSAccess().getEqualsSignKeyword_2());

            }
            // InternalCompleteOCL.g:4131:3: ( (lv_ownedInitExpression_4_0= ruleExpCS ) )
            // InternalCompleteOCL.g:4132:4: (lv_ownedInitExpression_4_0= ruleExpCS )
            {
            // InternalCompleteOCL.g:4132:4: (lv_ownedInitExpression_4_0= ruleExpCS )
            // InternalCompleteOCL.g:4133:5: lv_ownedInitExpression_4_0= ruleExpCS
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
    // InternalCompleteOCL.g:4154:1: entryRuleNumberLiteralExpCS returns [EObject current=null] : iv_ruleNumberLiteralExpCS= ruleNumberLiteralExpCS EOF ;
    public final EObject entryRuleNumberLiteralExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNumberLiteralExpCS = null;


        try {
            // InternalCompleteOCL.g:4154:59: (iv_ruleNumberLiteralExpCS= ruleNumberLiteralExpCS EOF )
            // InternalCompleteOCL.g:4155:2: iv_ruleNumberLiteralExpCS= ruleNumberLiteralExpCS EOF
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
    // InternalCompleteOCL.g:4161:1: ruleNumberLiteralExpCS returns [EObject current=null] : ( (lv_symbol_0_0= ruleNUMBER_LITERAL ) ) ;
    public final EObject ruleNumberLiteralExpCS() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_symbol_0_0 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:4167:2: ( ( (lv_symbol_0_0= ruleNUMBER_LITERAL ) ) )
            // InternalCompleteOCL.g:4168:2: ( (lv_symbol_0_0= ruleNUMBER_LITERAL ) )
            {
            // InternalCompleteOCL.g:4168:2: ( (lv_symbol_0_0= ruleNUMBER_LITERAL ) )
            // InternalCompleteOCL.g:4169:3: (lv_symbol_0_0= ruleNUMBER_LITERAL )
            {
            // InternalCompleteOCL.g:4169:3: (lv_symbol_0_0= ruleNUMBER_LITERAL )
            // InternalCompleteOCL.g:4170:4: lv_symbol_0_0= ruleNUMBER_LITERAL
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
    // InternalCompleteOCL.g:4190:1: entryRuleStringLiteralExpCS returns [EObject current=null] : iv_ruleStringLiteralExpCS= ruleStringLiteralExpCS EOF ;
    public final EObject entryRuleStringLiteralExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleStringLiteralExpCS = null;


        try {
            // InternalCompleteOCL.g:4190:59: (iv_ruleStringLiteralExpCS= ruleStringLiteralExpCS EOF )
            // InternalCompleteOCL.g:4191:2: iv_ruleStringLiteralExpCS= ruleStringLiteralExpCS EOF
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
    // InternalCompleteOCL.g:4197:1: ruleStringLiteralExpCS returns [EObject current=null] : ( (lv_segments_0_0= ruleStringLiteral ) )+ ;
    public final EObject ruleStringLiteralExpCS() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_segments_0_0 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:4203:2: ( ( (lv_segments_0_0= ruleStringLiteral ) )+ )
            // InternalCompleteOCL.g:4204:2: ( (lv_segments_0_0= ruleStringLiteral ) )+
            {
            // InternalCompleteOCL.g:4204:2: ( (lv_segments_0_0= ruleStringLiteral ) )+
            int cnt79=0;
            loop79:
            do {
                int alt79=2;
                int LA79_0 = input.LA(1);

                if ( (LA79_0==RULE_SINGLE_QUOTED_STRING) ) {
                    alt79=1;
                }


                switch (alt79) {
            	case 1 :
            	    // InternalCompleteOCL.g:4205:3: (lv_segments_0_0= ruleStringLiteral )
            	    {
            	    // InternalCompleteOCL.g:4205:3: (lv_segments_0_0= ruleStringLiteral )
            	    // InternalCompleteOCL.g:4206:4: lv_segments_0_0= ruleStringLiteral
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
            	    if ( cnt79 >= 1 ) break loop79;
            	    if (state.backtracking>0) {state.failed=true; return current;}
                        EarlyExitException eee =
                            new EarlyExitException(79, input);
                        throw eee;
                }
                cnt79++;
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
    // InternalCompleteOCL.g:4226:1: entryRuleBooleanLiteralExpCS returns [EObject current=null] : iv_ruleBooleanLiteralExpCS= ruleBooleanLiteralExpCS EOF ;
    public final EObject entryRuleBooleanLiteralExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleBooleanLiteralExpCS = null;


        try {
            // InternalCompleteOCL.g:4226:60: (iv_ruleBooleanLiteralExpCS= ruleBooleanLiteralExpCS EOF )
            // InternalCompleteOCL.g:4227:2: iv_ruleBooleanLiteralExpCS= ruleBooleanLiteralExpCS EOF
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
    // InternalCompleteOCL.g:4233:1: ruleBooleanLiteralExpCS returns [EObject current=null] : ( ( (lv_symbol_0_0= 'true' ) ) | ( (lv_symbol_1_0= 'false' ) ) ) ;
    public final EObject ruleBooleanLiteralExpCS() throws RecognitionException {
        EObject current = null;

        Token lv_symbol_0_0=null;
        Token lv_symbol_1_0=null;


        	enterRule();

        try {
            // InternalCompleteOCL.g:4239:2: ( ( ( (lv_symbol_0_0= 'true' ) ) | ( (lv_symbol_1_0= 'false' ) ) ) )
            // InternalCompleteOCL.g:4240:2: ( ( (lv_symbol_0_0= 'true' ) ) | ( (lv_symbol_1_0= 'false' ) ) )
            {
            // InternalCompleteOCL.g:4240:2: ( ( (lv_symbol_0_0= 'true' ) ) | ( (lv_symbol_1_0= 'false' ) ) )
            int alt80=2;
            int LA80_0 = input.LA(1);

            if ( (LA80_0==88) ) {
                alt80=1;
            }
            else if ( (LA80_0==89) ) {
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
                    // InternalCompleteOCL.g:4241:3: ( (lv_symbol_0_0= 'true' ) )
                    {
                    // InternalCompleteOCL.g:4241:3: ( (lv_symbol_0_0= 'true' ) )
                    // InternalCompleteOCL.g:4242:4: (lv_symbol_0_0= 'true' )
                    {
                    // InternalCompleteOCL.g:4242:4: (lv_symbol_0_0= 'true' )
                    // InternalCompleteOCL.g:4243:5: lv_symbol_0_0= 'true'
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
                    // InternalCompleteOCL.g:4256:3: ( (lv_symbol_1_0= 'false' ) )
                    {
                    // InternalCompleteOCL.g:4256:3: ( (lv_symbol_1_0= 'false' ) )
                    // InternalCompleteOCL.g:4257:4: (lv_symbol_1_0= 'false' )
                    {
                    // InternalCompleteOCL.g:4257:4: (lv_symbol_1_0= 'false' )
                    // InternalCompleteOCL.g:4258:5: lv_symbol_1_0= 'false'
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
    // InternalCompleteOCL.g:4274:1: entryRuleUnlimitedNaturalLiteralExpCS returns [EObject current=null] : iv_ruleUnlimitedNaturalLiteralExpCS= ruleUnlimitedNaturalLiteralExpCS EOF ;
    public final EObject entryRuleUnlimitedNaturalLiteralExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleUnlimitedNaturalLiteralExpCS = null;


        try {
            // InternalCompleteOCL.g:4274:69: (iv_ruleUnlimitedNaturalLiteralExpCS= ruleUnlimitedNaturalLiteralExpCS EOF )
            // InternalCompleteOCL.g:4275:2: iv_ruleUnlimitedNaturalLiteralExpCS= ruleUnlimitedNaturalLiteralExpCS EOF
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
    // InternalCompleteOCL.g:4281:1: ruleUnlimitedNaturalLiteralExpCS returns [EObject current=null] : ( () otherlv_1= '*' ) ;
    public final EObject ruleUnlimitedNaturalLiteralExpCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;


        	enterRule();

        try {
            // InternalCompleteOCL.g:4287:2: ( ( () otherlv_1= '*' ) )
            // InternalCompleteOCL.g:4288:2: ( () otherlv_1= '*' )
            {
            // InternalCompleteOCL.g:4288:2: ( () otherlv_1= '*' )
            // InternalCompleteOCL.g:4289:3: () otherlv_1= '*'
            {
            // InternalCompleteOCL.g:4289:3: ()
            // InternalCompleteOCL.g:4290:4:
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

            otherlv_1=(Token)match(input,55,FollowSets000.FOLLOW_2); if (state.failed) return current;
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
    // InternalCompleteOCL.g:4307:1: entryRuleInvalidLiteralExpCS returns [EObject current=null] : iv_ruleInvalidLiteralExpCS= ruleInvalidLiteralExpCS EOF ;
    public final EObject entryRuleInvalidLiteralExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleInvalidLiteralExpCS = null;


        try {
            // InternalCompleteOCL.g:4307:60: (iv_ruleInvalidLiteralExpCS= ruleInvalidLiteralExpCS EOF )
            // InternalCompleteOCL.g:4308:2: iv_ruleInvalidLiteralExpCS= ruleInvalidLiteralExpCS EOF
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
    // InternalCompleteOCL.g:4314:1: ruleInvalidLiteralExpCS returns [EObject current=null] : ( () otherlv_1= 'invalid' ) ;
    public final EObject ruleInvalidLiteralExpCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;


        	enterRule();

        try {
            // InternalCompleteOCL.g:4320:2: ( ( () otherlv_1= 'invalid' ) )
            // InternalCompleteOCL.g:4321:2: ( () otherlv_1= 'invalid' )
            {
            // InternalCompleteOCL.g:4321:2: ( () otherlv_1= 'invalid' )
            // InternalCompleteOCL.g:4322:3: () otherlv_1= 'invalid'
            {
            // InternalCompleteOCL.g:4322:3: ()
            // InternalCompleteOCL.g:4323:4:
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
    // InternalCompleteOCL.g:4340:1: entryRuleNullLiteralExpCS returns [EObject current=null] : iv_ruleNullLiteralExpCS= ruleNullLiteralExpCS EOF ;
    public final EObject entryRuleNullLiteralExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNullLiteralExpCS = null;


        try {
            // InternalCompleteOCL.g:4340:57: (iv_ruleNullLiteralExpCS= ruleNullLiteralExpCS EOF )
            // InternalCompleteOCL.g:4341:2: iv_ruleNullLiteralExpCS= ruleNullLiteralExpCS EOF
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
    // InternalCompleteOCL.g:4347:1: ruleNullLiteralExpCS returns [EObject current=null] : ( () otherlv_1= 'null' ) ;
    public final EObject ruleNullLiteralExpCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;


        	enterRule();

        try {
            // InternalCompleteOCL.g:4353:2: ( ( () otherlv_1= 'null' ) )
            // InternalCompleteOCL.g:4354:2: ( () otherlv_1= 'null' )
            {
            // InternalCompleteOCL.g:4354:2: ( () otherlv_1= 'null' )
            // InternalCompleteOCL.g:4355:3: () otherlv_1= 'null'
            {
            // InternalCompleteOCL.g:4355:3: ()
            // InternalCompleteOCL.g:4356:4:
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
    // InternalCompleteOCL.g:4373:1: entryRuleTypeLiteralCS returns [EObject current=null] : iv_ruleTypeLiteralCS= ruleTypeLiteralCS EOF ;
    public final EObject entryRuleTypeLiteralCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTypeLiteralCS = null;


        try {
            // InternalCompleteOCL.g:4373:54: (iv_ruleTypeLiteralCS= ruleTypeLiteralCS EOF )
            // InternalCompleteOCL.g:4374:2: iv_ruleTypeLiteralCS= ruleTypeLiteralCS EOF
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
    // InternalCompleteOCL.g:4380:1: ruleTypeLiteralCS returns [EObject current=null] : (this_PrimitiveTypeCS_0= rulePrimitiveTypeCS | this_CollectionTypeCS_1= ruleCollectionTypeCS | this_MapTypeCS_2= ruleMapTypeCS | this_TupleTypeCS_3= ruleTupleTypeCS ) ;
    public final EObject ruleTypeLiteralCS() throws RecognitionException {
        EObject current = null;

        EObject this_PrimitiveTypeCS_0 = null;

        EObject this_CollectionTypeCS_1 = null;

        EObject this_MapTypeCS_2 = null;

        EObject this_TupleTypeCS_3 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:4386:2: ( (this_PrimitiveTypeCS_0= rulePrimitiveTypeCS | this_CollectionTypeCS_1= ruleCollectionTypeCS | this_MapTypeCS_2= ruleMapTypeCS | this_TupleTypeCS_3= ruleTupleTypeCS ) )
            // InternalCompleteOCL.g:4387:2: (this_PrimitiveTypeCS_0= rulePrimitiveTypeCS | this_CollectionTypeCS_1= ruleCollectionTypeCS | this_MapTypeCS_2= ruleMapTypeCS | this_TupleTypeCS_3= ruleTupleTypeCS )
            {
            // InternalCompleteOCL.g:4387:2: (this_PrimitiveTypeCS_0= rulePrimitiveTypeCS | this_CollectionTypeCS_1= ruleCollectionTypeCS | this_MapTypeCS_2= ruleMapTypeCS | this_TupleTypeCS_3= ruleTupleTypeCS )
            int alt81=4;
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
                alt81=1;
                }
                break;
            case 76:
            case 77:
            case 78:
            case 79:
            case 80:
                {
                alt81=2;
                }
                break;
            case 73:
                {
                alt81=3;
                }
                break;
            case 74:
                {
                alt81=4;
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
                    // InternalCompleteOCL.g:4388:3: this_PrimitiveTypeCS_0= rulePrimitiveTypeCS
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
                    // InternalCompleteOCL.g:4400:3: this_CollectionTypeCS_1= ruleCollectionTypeCS
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
                    // InternalCompleteOCL.g:4412:3: this_MapTypeCS_2= ruleMapTypeCS
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
                    // InternalCompleteOCL.g:4424:3: this_TupleTypeCS_3= ruleTupleTypeCS
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
    // InternalCompleteOCL.g:4439:1: entryRuleTypeLiteralWithMultiplicityCS returns [EObject current=null] : iv_ruleTypeLiteralWithMultiplicityCS= ruleTypeLiteralWithMultiplicityCS EOF ;
    public final EObject entryRuleTypeLiteralWithMultiplicityCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTypeLiteralWithMultiplicityCS = null;


        try {
            // InternalCompleteOCL.g:4439:70: (iv_ruleTypeLiteralWithMultiplicityCS= ruleTypeLiteralWithMultiplicityCS EOF )
            // InternalCompleteOCL.g:4440:2: iv_ruleTypeLiteralWithMultiplicityCS= ruleTypeLiteralWithMultiplicityCS EOF
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
    // InternalCompleteOCL.g:4446:1: ruleTypeLiteralWithMultiplicityCS returns [EObject current=null] : (this_TypeLiteralCS_0= ruleTypeLiteralCS ( (lv_ownedMultiplicity_1_0= ruleMultiplicityCS ) )? ) ;
    public final EObject ruleTypeLiteralWithMultiplicityCS() throws RecognitionException {
        EObject current = null;

        EObject this_TypeLiteralCS_0 = null;

        EObject lv_ownedMultiplicity_1_0 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:4452:2: ( (this_TypeLiteralCS_0= ruleTypeLiteralCS ( (lv_ownedMultiplicity_1_0= ruleMultiplicityCS ) )? ) )
            // InternalCompleteOCL.g:4453:2: (this_TypeLiteralCS_0= ruleTypeLiteralCS ( (lv_ownedMultiplicity_1_0= ruleMultiplicityCS ) )? )
            {
            // InternalCompleteOCL.g:4453:2: (this_TypeLiteralCS_0= ruleTypeLiteralCS ( (lv_ownedMultiplicity_1_0= ruleMultiplicityCS ) )? )
            // InternalCompleteOCL.g:4454:3: this_TypeLiteralCS_0= ruleTypeLiteralCS ( (lv_ownedMultiplicity_1_0= ruleMultiplicityCS ) )?
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
            // InternalCompleteOCL.g:4465:3: ( (lv_ownedMultiplicity_1_0= ruleMultiplicityCS ) )?
            int alt82=2;
            int LA82_0 = input.LA(1);

            if ( (LA82_0==93) ) {
                alt82=1;
            }
            switch (alt82) {
                case 1 :
                    // InternalCompleteOCL.g:4466:4: (lv_ownedMultiplicity_1_0= ruleMultiplicityCS )
                    {
                    // InternalCompleteOCL.g:4466:4: (lv_ownedMultiplicity_1_0= ruleMultiplicityCS )
                    // InternalCompleteOCL.g:4467:5: lv_ownedMultiplicity_1_0= ruleMultiplicityCS
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
    // InternalCompleteOCL.g:4488:1: entryRuleTypeLiteralExpCS returns [EObject current=null] : iv_ruleTypeLiteralExpCS= ruleTypeLiteralExpCS EOF ;
    public final EObject entryRuleTypeLiteralExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTypeLiteralExpCS = null;


        try {
            // InternalCompleteOCL.g:4488:57: (iv_ruleTypeLiteralExpCS= ruleTypeLiteralExpCS EOF )
            // InternalCompleteOCL.g:4489:2: iv_ruleTypeLiteralExpCS= ruleTypeLiteralExpCS EOF
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
    // InternalCompleteOCL.g:4495:1: ruleTypeLiteralExpCS returns [EObject current=null] : ( (lv_ownedType_0_0= ruleTypeLiteralWithMultiplicityCS ) ) ;
    public final EObject ruleTypeLiteralExpCS() throws RecognitionException {
        EObject current = null;

        EObject lv_ownedType_0_0 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:4501:2: ( ( (lv_ownedType_0_0= ruleTypeLiteralWithMultiplicityCS ) ) )
            // InternalCompleteOCL.g:4502:2: ( (lv_ownedType_0_0= ruleTypeLiteralWithMultiplicityCS ) )
            {
            // InternalCompleteOCL.g:4502:2: ( (lv_ownedType_0_0= ruleTypeLiteralWithMultiplicityCS ) )
            // InternalCompleteOCL.g:4503:3: (lv_ownedType_0_0= ruleTypeLiteralWithMultiplicityCS )
            {
            // InternalCompleteOCL.g:4503:3: (lv_ownedType_0_0= ruleTypeLiteralWithMultiplicityCS )
            // InternalCompleteOCL.g:4504:4: lv_ownedType_0_0= ruleTypeLiteralWithMultiplicityCS
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
    // InternalCompleteOCL.g:4524:1: entryRuleTypeNameExpCS returns [EObject current=null] : iv_ruleTypeNameExpCS= ruleTypeNameExpCS EOF ;
    public final EObject entryRuleTypeNameExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTypeNameExpCS = null;


        try {
            // InternalCompleteOCL.g:4524:54: (iv_ruleTypeNameExpCS= ruleTypeNameExpCS EOF )
            // InternalCompleteOCL.g:4525:2: iv_ruleTypeNameExpCS= ruleTypeNameExpCS EOF
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
    // InternalCompleteOCL.g:4531:1: ruleTypeNameExpCS returns [EObject current=null] : ( ( (lv_ownedPathName_0_0= rulePathNameCS ) ) ( ( (lv_ownedCurlyBracketedClause_1_0= ruleCurlyBracketedClauseCS ) ) (otherlv_2= '{' ( (lv_ownedPatternGuard_3_0= ruleExpCS ) ) otherlv_4= '}' )? )? ) ;
    public final EObject ruleTypeNameExpCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_ownedPathName_0_0 = null;

        EObject lv_ownedCurlyBracketedClause_1_0 = null;

        EObject lv_ownedPatternGuard_3_0 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:4537:2: ( ( ( (lv_ownedPathName_0_0= rulePathNameCS ) ) ( ( (lv_ownedCurlyBracketedClause_1_0= ruleCurlyBracketedClauseCS ) ) (otherlv_2= '{' ( (lv_ownedPatternGuard_3_0= ruleExpCS ) ) otherlv_4= '}' )? )? ) )
            // InternalCompleteOCL.g:4538:2: ( ( (lv_ownedPathName_0_0= rulePathNameCS ) ) ( ( (lv_ownedCurlyBracketedClause_1_0= ruleCurlyBracketedClauseCS ) ) (otherlv_2= '{' ( (lv_ownedPatternGuard_3_0= ruleExpCS ) ) otherlv_4= '}' )? )? )
            {
            // InternalCompleteOCL.g:4538:2: ( ( (lv_ownedPathName_0_0= rulePathNameCS ) ) ( ( (lv_ownedCurlyBracketedClause_1_0= ruleCurlyBracketedClauseCS ) ) (otherlv_2= '{' ( (lv_ownedPatternGuard_3_0= ruleExpCS ) ) otherlv_4= '}' )? )? )
            // InternalCompleteOCL.g:4539:3: ( (lv_ownedPathName_0_0= rulePathNameCS ) ) ( ( (lv_ownedCurlyBracketedClause_1_0= ruleCurlyBracketedClauseCS ) ) (otherlv_2= '{' ( (lv_ownedPatternGuard_3_0= ruleExpCS ) ) otherlv_4= '}' )? )?
            {
            // InternalCompleteOCL.g:4539:3: ( (lv_ownedPathName_0_0= rulePathNameCS ) )
            // InternalCompleteOCL.g:4540:4: (lv_ownedPathName_0_0= rulePathNameCS )
            {
            // InternalCompleteOCL.g:4540:4: (lv_ownedPathName_0_0= rulePathNameCS )
            // InternalCompleteOCL.g:4541:5: lv_ownedPathName_0_0= rulePathNameCS
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

            // InternalCompleteOCL.g:4558:3: ( ( (lv_ownedCurlyBracketedClause_1_0= ruleCurlyBracketedClauseCS ) ) (otherlv_2= '{' ( (lv_ownedPatternGuard_3_0= ruleExpCS ) ) otherlv_4= '}' )? )?
            int alt84=2;
            int LA84_0 = input.LA(1);

            if ( (LA84_0==81) ) {
                alt84=1;
            }
            switch (alt84) {
                case 1 :
                    // InternalCompleteOCL.g:4559:4: ( (lv_ownedCurlyBracketedClause_1_0= ruleCurlyBracketedClauseCS ) ) (otherlv_2= '{' ( (lv_ownedPatternGuard_3_0= ruleExpCS ) ) otherlv_4= '}' )?
                    {
                    // InternalCompleteOCL.g:4559:4: ( (lv_ownedCurlyBracketedClause_1_0= ruleCurlyBracketedClauseCS ) )
                    // InternalCompleteOCL.g:4560:5: (lv_ownedCurlyBracketedClause_1_0= ruleCurlyBracketedClauseCS )
                    {
                    // InternalCompleteOCL.g:4560:5: (lv_ownedCurlyBracketedClause_1_0= ruleCurlyBracketedClauseCS )
                    // InternalCompleteOCL.g:4561:6: lv_ownedCurlyBracketedClause_1_0= ruleCurlyBracketedClauseCS
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

                    // InternalCompleteOCL.g:4578:4: (otherlv_2= '{' ( (lv_ownedPatternGuard_3_0= ruleExpCS ) ) otherlv_4= '}' )?
                    int alt83=2;
                    int LA83_0 = input.LA(1);

                    if ( (LA83_0==81) ) {
                        alt83=1;
                    }
                    switch (alt83) {
                        case 1 :
                            // InternalCompleteOCL.g:4579:5: otherlv_2= '{' ( (lv_ownedPatternGuard_3_0= ruleExpCS ) ) otherlv_4= '}'
                            {
                            otherlv_2=(Token)match(input,81,FollowSets000.FOLLOW_30); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              					newLeafNode(otherlv_2, grammarAccess.getTypeNameExpCSAccess().getLeftCurlyBracketKeyword_1_1_0());

                            }
                            // InternalCompleteOCL.g:4583:5: ( (lv_ownedPatternGuard_3_0= ruleExpCS ) )
                            // InternalCompleteOCL.g:4584:6: (lv_ownedPatternGuard_3_0= ruleExpCS )
                            {
                            // InternalCompleteOCL.g:4584:6: (lv_ownedPatternGuard_3_0= ruleExpCS )
                            // InternalCompleteOCL.g:4585:7: lv_ownedPatternGuard_3_0= ruleExpCS
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

                            otherlv_4=(Token)match(input,82,FollowSets000.FOLLOW_2); if (state.failed) return current;
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
    // InternalCompleteOCL.g:4612:1: entryRuleTypeExpWithoutMultiplicityCS returns [EObject current=null] : iv_ruleTypeExpWithoutMultiplicityCS= ruleTypeExpWithoutMultiplicityCS EOF ;
    public final EObject entryRuleTypeExpWithoutMultiplicityCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTypeExpWithoutMultiplicityCS = null;


        try {
            // InternalCompleteOCL.g:4612:69: (iv_ruleTypeExpWithoutMultiplicityCS= ruleTypeExpWithoutMultiplicityCS EOF )
            // InternalCompleteOCL.g:4613:2: iv_ruleTypeExpWithoutMultiplicityCS= ruleTypeExpWithoutMultiplicityCS EOF
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
    // InternalCompleteOCL.g:4619:1: ruleTypeExpWithoutMultiplicityCS returns [EObject current=null] : (this_TypeNameExpCS_0= ruleTypeNameExpCS | this_TypeLiteralCS_1= ruleTypeLiteralCS | this_CollectionPatternCS_2= ruleCollectionPatternCS ) ;
    public final EObject ruleTypeExpWithoutMultiplicityCS() throws RecognitionException {
        EObject current = null;

        EObject this_TypeNameExpCS_0 = null;

        EObject this_TypeLiteralCS_1 = null;

        EObject this_CollectionPatternCS_2 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:4625:2: ( (this_TypeNameExpCS_0= ruleTypeNameExpCS | this_TypeLiteralCS_1= ruleTypeLiteralCS | this_CollectionPatternCS_2= ruleCollectionPatternCS ) )
            // InternalCompleteOCL.g:4626:2: (this_TypeNameExpCS_0= ruleTypeNameExpCS | this_TypeLiteralCS_1= ruleTypeLiteralCS | this_CollectionPatternCS_2= ruleCollectionPatternCS )
            {
            // InternalCompleteOCL.g:4626:2: (this_TypeNameExpCS_0= ruleTypeNameExpCS | this_TypeLiteralCS_1= ruleTypeLiteralCS | this_CollectionPatternCS_2= ruleCollectionPatternCS )
            int alt85=3;
            alt85 = dfa85.predict(input);
            switch (alt85) {
                case 1 :
                    // InternalCompleteOCL.g:4627:3: this_TypeNameExpCS_0= ruleTypeNameExpCS
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
                    // InternalCompleteOCL.g:4639:3: this_TypeLiteralCS_1= ruleTypeLiteralCS
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
                    // InternalCompleteOCL.g:4651:3: this_CollectionPatternCS_2= ruleCollectionPatternCS
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
    // InternalCompleteOCL.g:4666:1: entryRuleTypeExpCS returns [EObject current=null] : iv_ruleTypeExpCS= ruleTypeExpCS EOF ;
    public final EObject entryRuleTypeExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTypeExpCS = null;


        try {
            // InternalCompleteOCL.g:4666:50: (iv_ruleTypeExpCS= ruleTypeExpCS EOF )
            // InternalCompleteOCL.g:4667:2: iv_ruleTypeExpCS= ruleTypeExpCS EOF
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
    // InternalCompleteOCL.g:4673:1: ruleTypeExpCS returns [EObject current=null] : (this_TypeExpWithoutMultiplicityCS_0= ruleTypeExpWithoutMultiplicityCS ( (lv_ownedMultiplicity_1_0= ruleMultiplicityCS ) )? ) ;
    public final EObject ruleTypeExpCS() throws RecognitionException {
        EObject current = null;

        EObject this_TypeExpWithoutMultiplicityCS_0 = null;

        EObject lv_ownedMultiplicity_1_0 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:4679:2: ( (this_TypeExpWithoutMultiplicityCS_0= ruleTypeExpWithoutMultiplicityCS ( (lv_ownedMultiplicity_1_0= ruleMultiplicityCS ) )? ) )
            // InternalCompleteOCL.g:4680:2: (this_TypeExpWithoutMultiplicityCS_0= ruleTypeExpWithoutMultiplicityCS ( (lv_ownedMultiplicity_1_0= ruleMultiplicityCS ) )? )
            {
            // InternalCompleteOCL.g:4680:2: (this_TypeExpWithoutMultiplicityCS_0= ruleTypeExpWithoutMultiplicityCS ( (lv_ownedMultiplicity_1_0= ruleMultiplicityCS ) )? )
            // InternalCompleteOCL.g:4681:3: this_TypeExpWithoutMultiplicityCS_0= ruleTypeExpWithoutMultiplicityCS ( (lv_ownedMultiplicity_1_0= ruleMultiplicityCS ) )?
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
            // InternalCompleteOCL.g:4692:3: ( (lv_ownedMultiplicity_1_0= ruleMultiplicityCS ) )?
            int alt86=2;
            int LA86_0 = input.LA(1);

            if ( (LA86_0==93) ) {
                alt86=1;
            }
            switch (alt86) {
                case 1 :
                    // InternalCompleteOCL.g:4693:4: (lv_ownedMultiplicity_1_0= ruleMultiplicityCS )
                    {
                    // InternalCompleteOCL.g:4693:4: (lv_ownedMultiplicity_1_0= ruleMultiplicityCS )
                    // InternalCompleteOCL.g:4694:5: lv_ownedMultiplicity_1_0= ruleMultiplicityCS
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
    // InternalCompleteOCL.g:4715:1: entryRuleExpCS returns [EObject current=null] : iv_ruleExpCS= ruleExpCS EOF ;
    public final EObject entryRuleExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpCS = null;


        try {
            // InternalCompleteOCL.g:4715:46: (iv_ruleExpCS= ruleExpCS EOF )
            // InternalCompleteOCL.g:4716:2: iv_ruleExpCS= ruleExpCS EOF
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
    // InternalCompleteOCL.g:4722:1: ruleExpCS returns [EObject current=null] : ( (this_PrefixedPrimaryExpCS_0= rulePrefixedPrimaryExpCS ( () ( (lv_name_2_0= ruleBinaryOperatorName ) ) ( (lv_ownedRight_3_0= ruleExpCS ) ) )? ) | this_PrefixedLetExpCS_4= rulePrefixedLetExpCS ) ;
    public final EObject ruleExpCS() throws RecognitionException {
        EObject current = null;

        EObject this_PrefixedPrimaryExpCS_0 = null;

        AntlrDatatypeRuleToken lv_name_2_0 = null;

        EObject lv_ownedRight_3_0 = null;

        EObject this_PrefixedLetExpCS_4 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:4728:2: ( ( (this_PrefixedPrimaryExpCS_0= rulePrefixedPrimaryExpCS ( () ( (lv_name_2_0= ruleBinaryOperatorName ) ) ( (lv_ownedRight_3_0= ruleExpCS ) ) )? ) | this_PrefixedLetExpCS_4= rulePrefixedLetExpCS ) )
            // InternalCompleteOCL.g:4729:2: ( (this_PrefixedPrimaryExpCS_0= rulePrefixedPrimaryExpCS ( () ( (lv_name_2_0= ruleBinaryOperatorName ) ) ( (lv_ownedRight_3_0= ruleExpCS ) ) )? ) | this_PrefixedLetExpCS_4= rulePrefixedLetExpCS )
            {
            // InternalCompleteOCL.g:4729:2: ( (this_PrefixedPrimaryExpCS_0= rulePrefixedPrimaryExpCS ( () ( (lv_name_2_0= ruleBinaryOperatorName ) ) ( (lv_ownedRight_3_0= ruleExpCS ) ) )? ) | this_PrefixedLetExpCS_4= rulePrefixedLetExpCS )
            int alt88=2;
            alt88 = dfa88.predict(input);
            switch (alt88) {
                case 1 :
                    // InternalCompleteOCL.g:4730:3: (this_PrefixedPrimaryExpCS_0= rulePrefixedPrimaryExpCS ( () ( (lv_name_2_0= ruleBinaryOperatorName ) ) ( (lv_ownedRight_3_0= ruleExpCS ) ) )? )
                    {
                    // InternalCompleteOCL.g:4730:3: (this_PrefixedPrimaryExpCS_0= rulePrefixedPrimaryExpCS ( () ( (lv_name_2_0= ruleBinaryOperatorName ) ) ( (lv_ownedRight_3_0= ruleExpCS ) ) )? )
                    // InternalCompleteOCL.g:4731:4: this_PrefixedPrimaryExpCS_0= rulePrefixedPrimaryExpCS ( () ( (lv_name_2_0= ruleBinaryOperatorName ) ) ( (lv_ownedRight_3_0= ruleExpCS ) ) )?
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
                    // InternalCompleteOCL.g:4742:4: ( () ( (lv_name_2_0= ruleBinaryOperatorName ) ) ( (lv_ownedRight_3_0= ruleExpCS ) ) )?
                    int alt87=2;
                    int LA87_0 = input.LA(1);

                    if ( ((LA87_0>=17 && LA87_0<=18)||LA87_0==29||(LA87_0>=39 && LA87_0<=40)||LA87_0==52||(LA87_0>=55 && LA87_0<=72)) ) {
                        alt87=1;
                    }
                    switch (alt87) {
                        case 1 :
                            // InternalCompleteOCL.g:4743:5: () ( (lv_name_2_0= ruleBinaryOperatorName ) ) ( (lv_ownedRight_3_0= ruleExpCS ) )
                            {
                            // InternalCompleteOCL.g:4743:5: ()
                            // InternalCompleteOCL.g:4744:6:
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

                            // InternalCompleteOCL.g:4753:5: ( (lv_name_2_0= ruleBinaryOperatorName ) )
                            // InternalCompleteOCL.g:4754:6: (lv_name_2_0= ruleBinaryOperatorName )
                            {
                            // InternalCompleteOCL.g:4754:6: (lv_name_2_0= ruleBinaryOperatorName )
                            // InternalCompleteOCL.g:4755:7: lv_name_2_0= ruleBinaryOperatorName
                            {
                            if ( state.backtracking==0 ) {

                              							newCompositeNode(grammarAccess.getExpCSAccess().getNameBinaryOperatorNameParserRuleCall_0_1_1_0());

                            }
                            pushFollow(FollowSets000.FOLLOW_30);
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

                            // InternalCompleteOCL.g:4772:5: ( (lv_ownedRight_3_0= ruleExpCS ) )
                            // InternalCompleteOCL.g:4773:6: (lv_ownedRight_3_0= ruleExpCS )
                            {
                            // InternalCompleteOCL.g:4773:6: (lv_ownedRight_3_0= ruleExpCS )
                            // InternalCompleteOCL.g:4774:7: lv_ownedRight_3_0= ruleExpCS
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
                    // InternalCompleteOCL.g:4794:3: this_PrefixedLetExpCS_4= rulePrefixedLetExpCS
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
    // InternalCompleteOCL.g:4809:1: entryRulePrefixedLetExpCS returns [EObject current=null] : iv_rulePrefixedLetExpCS= rulePrefixedLetExpCS EOF ;
    public final EObject entryRulePrefixedLetExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePrefixedLetExpCS = null;


        try {
            // InternalCompleteOCL.g:4809:57: (iv_rulePrefixedLetExpCS= rulePrefixedLetExpCS EOF )
            // InternalCompleteOCL.g:4810:2: iv_rulePrefixedLetExpCS= rulePrefixedLetExpCS EOF
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
    // InternalCompleteOCL.g:4816:1: rulePrefixedLetExpCS returns [EObject current=null] : ( ( () ( (lv_name_1_0= ruleUnaryOperatorName ) ) ( (lv_ownedRight_2_0= rulePrefixedLetExpCS ) ) ) | this_LetExpCS_3= ruleLetExpCS ) ;
    public final EObject rulePrefixedLetExpCS() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_name_1_0 = null;

        EObject lv_ownedRight_2_0 = null;

        EObject this_LetExpCS_3 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:4822:2: ( ( ( () ( (lv_name_1_0= ruleUnaryOperatorName ) ) ( (lv_ownedRight_2_0= rulePrefixedLetExpCS ) ) ) | this_LetExpCS_3= ruleLetExpCS ) )
            // InternalCompleteOCL.g:4823:2: ( ( () ( (lv_name_1_0= ruleUnaryOperatorName ) ) ( (lv_ownedRight_2_0= rulePrefixedLetExpCS ) ) ) | this_LetExpCS_3= ruleLetExpCS )
            {
            // InternalCompleteOCL.g:4823:2: ( ( () ( (lv_name_1_0= ruleUnaryOperatorName ) ) ( (lv_ownedRight_2_0= rulePrefixedLetExpCS ) ) ) | this_LetExpCS_3= ruleLetExpCS )
            int alt89=2;
            int LA89_0 = input.LA(1);

            if ( ((LA89_0>=52 && LA89_0<=54)) ) {
                alt89=1;
            }
            else if ( (LA89_0==103) ) {
                alt89=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 89, 0, input);

                throw nvae;
            }
            switch (alt89) {
                case 1 :
                    // InternalCompleteOCL.g:4824:3: ( () ( (lv_name_1_0= ruleUnaryOperatorName ) ) ( (lv_ownedRight_2_0= rulePrefixedLetExpCS ) ) )
                    {
                    // InternalCompleteOCL.g:4824:3: ( () ( (lv_name_1_0= ruleUnaryOperatorName ) ) ( (lv_ownedRight_2_0= rulePrefixedLetExpCS ) ) )
                    // InternalCompleteOCL.g:4825:4: () ( (lv_name_1_0= ruleUnaryOperatorName ) ) ( (lv_ownedRight_2_0= rulePrefixedLetExpCS ) )
                    {
                    // InternalCompleteOCL.g:4825:4: ()
                    // InternalCompleteOCL.g:4826:5:
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

                    // InternalCompleteOCL.g:4835:4: ( (lv_name_1_0= ruleUnaryOperatorName ) )
                    // InternalCompleteOCL.g:4836:5: (lv_name_1_0= ruleUnaryOperatorName )
                    {
                    // InternalCompleteOCL.g:4836:5: (lv_name_1_0= ruleUnaryOperatorName )
                    // InternalCompleteOCL.g:4837:6: lv_name_1_0= ruleUnaryOperatorName
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getPrefixedLetExpCSAccess().getNameUnaryOperatorNameParserRuleCall_0_1_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_30);
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

                    // InternalCompleteOCL.g:4854:4: ( (lv_ownedRight_2_0= rulePrefixedLetExpCS ) )
                    // InternalCompleteOCL.g:4855:5: (lv_ownedRight_2_0= rulePrefixedLetExpCS )
                    {
                    // InternalCompleteOCL.g:4855:5: (lv_ownedRight_2_0= rulePrefixedLetExpCS )
                    // InternalCompleteOCL.g:4856:6: lv_ownedRight_2_0= rulePrefixedLetExpCS
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
                    // InternalCompleteOCL.g:4875:3: this_LetExpCS_3= ruleLetExpCS
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
    // InternalCompleteOCL.g:4890:1: entryRulePrefixedPrimaryExpCS returns [EObject current=null] : iv_rulePrefixedPrimaryExpCS= rulePrefixedPrimaryExpCS EOF ;
    public final EObject entryRulePrefixedPrimaryExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePrefixedPrimaryExpCS = null;


        try {
            // InternalCompleteOCL.g:4890:61: (iv_rulePrefixedPrimaryExpCS= rulePrefixedPrimaryExpCS EOF )
            // InternalCompleteOCL.g:4891:2: iv_rulePrefixedPrimaryExpCS= rulePrefixedPrimaryExpCS EOF
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
    // InternalCompleteOCL.g:4897:1: rulePrefixedPrimaryExpCS returns [EObject current=null] : ( ( () ( (lv_name_1_0= ruleUnaryOperatorName ) ) ( (lv_ownedRight_2_0= rulePrefixedPrimaryExpCS ) ) ) | this_PrimaryExpCS_3= rulePrimaryExpCS ) ;
    public final EObject rulePrefixedPrimaryExpCS() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_name_1_0 = null;

        EObject lv_ownedRight_2_0 = null;

        EObject this_PrimaryExpCS_3 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:4903:2: ( ( ( () ( (lv_name_1_0= ruleUnaryOperatorName ) ) ( (lv_ownedRight_2_0= rulePrefixedPrimaryExpCS ) ) ) | this_PrimaryExpCS_3= rulePrimaryExpCS ) )
            // InternalCompleteOCL.g:4904:2: ( ( () ( (lv_name_1_0= ruleUnaryOperatorName ) ) ( (lv_ownedRight_2_0= rulePrefixedPrimaryExpCS ) ) ) | this_PrimaryExpCS_3= rulePrimaryExpCS )
            {
            // InternalCompleteOCL.g:4904:2: ( ( () ( (lv_name_1_0= ruleUnaryOperatorName ) ) ( (lv_ownedRight_2_0= rulePrefixedPrimaryExpCS ) ) ) | this_PrimaryExpCS_3= rulePrimaryExpCS )
            int alt90=2;
            int LA90_0 = input.LA(1);

            if ( ((LA90_0>=52 && LA90_0<=54)) ) {
                alt90=1;
            }
            else if ( ((LA90_0>=RULE_SIMPLE_ID && LA90_0<=RULE_SINGLE_QUOTED_STRING)||LA90_0==21||(LA90_0>=30 && LA90_0<=32)||(LA90_0>=42 && LA90_0<=51)||LA90_0==55||(LA90_0>=73 && LA90_0<=74)||(LA90_0>=76 && LA90_0<=80)||LA90_0==85||(LA90_0>=88 && LA90_0<=91)||LA90_0==98||LA90_0==104) ) {
                alt90=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 90, 0, input);

                throw nvae;
            }
            switch (alt90) {
                case 1 :
                    // InternalCompleteOCL.g:4905:3: ( () ( (lv_name_1_0= ruleUnaryOperatorName ) ) ( (lv_ownedRight_2_0= rulePrefixedPrimaryExpCS ) ) )
                    {
                    // InternalCompleteOCL.g:4905:3: ( () ( (lv_name_1_0= ruleUnaryOperatorName ) ) ( (lv_ownedRight_2_0= rulePrefixedPrimaryExpCS ) ) )
                    // InternalCompleteOCL.g:4906:4: () ( (lv_name_1_0= ruleUnaryOperatorName ) ) ( (lv_ownedRight_2_0= rulePrefixedPrimaryExpCS ) )
                    {
                    // InternalCompleteOCL.g:4906:4: ()
                    // InternalCompleteOCL.g:4907:5:
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

                    // InternalCompleteOCL.g:4916:4: ( (lv_name_1_0= ruleUnaryOperatorName ) )
                    // InternalCompleteOCL.g:4917:5: (lv_name_1_0= ruleUnaryOperatorName )
                    {
                    // InternalCompleteOCL.g:4917:5: (lv_name_1_0= ruleUnaryOperatorName )
                    // InternalCompleteOCL.g:4918:6: lv_name_1_0= ruleUnaryOperatorName
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getPrefixedPrimaryExpCSAccess().getNameUnaryOperatorNameParserRuleCall_0_1_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_21);
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

                    // InternalCompleteOCL.g:4935:4: ( (lv_ownedRight_2_0= rulePrefixedPrimaryExpCS ) )
                    // InternalCompleteOCL.g:4936:5: (lv_ownedRight_2_0= rulePrefixedPrimaryExpCS )
                    {
                    // InternalCompleteOCL.g:4936:5: (lv_ownedRight_2_0= rulePrefixedPrimaryExpCS )
                    // InternalCompleteOCL.g:4937:6: lv_ownedRight_2_0= rulePrefixedPrimaryExpCS
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
                    // InternalCompleteOCL.g:4956:3: this_PrimaryExpCS_3= rulePrimaryExpCS
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
    // InternalCompleteOCL.g:4971:1: entryRulePrimaryExpCS returns [EObject current=null] : iv_rulePrimaryExpCS= rulePrimaryExpCS EOF ;
    public final EObject entryRulePrimaryExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePrimaryExpCS = null;


        try {
            // InternalCompleteOCL.g:4971:53: (iv_rulePrimaryExpCS= rulePrimaryExpCS EOF )
            // InternalCompleteOCL.g:4972:2: iv_rulePrimaryExpCS= rulePrimaryExpCS EOF
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
    // InternalCompleteOCL.g:4978:1: rulePrimaryExpCS returns [EObject current=null] : (this_NestedExpCS_0= ruleNestedExpCS | this_IfExpCS_1= ruleIfExpCS | this_SelfExpCS_2= ruleSelfExpCS | this_PrimitiveLiteralExpCS_3= rulePrimitiveLiteralExpCS | this_TupleLiteralExpCS_4= ruleTupleLiteralExpCS | this_MapLiteralExpCS_5= ruleMapLiteralExpCS | this_CollectionLiteralExpCS_6= ruleCollectionLiteralExpCS | this_LambdaLiteralExpCS_7= ruleLambdaLiteralExpCS | this_TypeLiteralExpCS_8= ruleTypeLiteralExpCS | this_NameExpCS_9= ruleNameExpCS ) ;
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
            // InternalCompleteOCL.g:4984:2: ( (this_NestedExpCS_0= ruleNestedExpCS | this_IfExpCS_1= ruleIfExpCS | this_SelfExpCS_2= ruleSelfExpCS | this_PrimitiveLiteralExpCS_3= rulePrimitiveLiteralExpCS | this_TupleLiteralExpCS_4= ruleTupleLiteralExpCS | this_MapLiteralExpCS_5= ruleMapLiteralExpCS | this_CollectionLiteralExpCS_6= ruleCollectionLiteralExpCS | this_LambdaLiteralExpCS_7= ruleLambdaLiteralExpCS | this_TypeLiteralExpCS_8= ruleTypeLiteralExpCS | this_NameExpCS_9= ruleNameExpCS ) )
            // InternalCompleteOCL.g:4985:2: (this_NestedExpCS_0= ruleNestedExpCS | this_IfExpCS_1= ruleIfExpCS | this_SelfExpCS_2= ruleSelfExpCS | this_PrimitiveLiteralExpCS_3= rulePrimitiveLiteralExpCS | this_TupleLiteralExpCS_4= ruleTupleLiteralExpCS | this_MapLiteralExpCS_5= ruleMapLiteralExpCS | this_CollectionLiteralExpCS_6= ruleCollectionLiteralExpCS | this_LambdaLiteralExpCS_7= ruleLambdaLiteralExpCS | this_TypeLiteralExpCS_8= ruleTypeLiteralExpCS | this_NameExpCS_9= ruleNameExpCS )
            {
            // InternalCompleteOCL.g:4985:2: (this_NestedExpCS_0= ruleNestedExpCS | this_IfExpCS_1= ruleIfExpCS | this_SelfExpCS_2= ruleSelfExpCS | this_PrimitiveLiteralExpCS_3= rulePrimitiveLiteralExpCS | this_TupleLiteralExpCS_4= ruleTupleLiteralExpCS | this_MapLiteralExpCS_5= ruleMapLiteralExpCS | this_CollectionLiteralExpCS_6= ruleCollectionLiteralExpCS | this_LambdaLiteralExpCS_7= ruleLambdaLiteralExpCS | this_TypeLiteralExpCS_8= ruleTypeLiteralExpCS | this_NameExpCS_9= ruleNameExpCS )
            int alt91=10;
            alt91 = dfa91.predict(input);
            switch (alt91) {
                case 1 :
                    // InternalCompleteOCL.g:4986:3: this_NestedExpCS_0= ruleNestedExpCS
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
                    // InternalCompleteOCL.g:4998:3: this_IfExpCS_1= ruleIfExpCS
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
                    // InternalCompleteOCL.g:5010:3: this_SelfExpCS_2= ruleSelfExpCS
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
                    // InternalCompleteOCL.g:5022:3: this_PrimitiveLiteralExpCS_3= rulePrimitiveLiteralExpCS
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
                    // InternalCompleteOCL.g:5034:3: this_TupleLiteralExpCS_4= ruleTupleLiteralExpCS
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
                    // InternalCompleteOCL.g:5046:3: this_MapLiteralExpCS_5= ruleMapLiteralExpCS
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
                    // InternalCompleteOCL.g:5058:3: this_CollectionLiteralExpCS_6= ruleCollectionLiteralExpCS
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
                    // InternalCompleteOCL.g:5070:3: this_LambdaLiteralExpCS_7= ruleLambdaLiteralExpCS
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
                    // InternalCompleteOCL.g:5082:3: this_TypeLiteralExpCS_8= ruleTypeLiteralExpCS
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
                    // InternalCompleteOCL.g:5094:3: this_NameExpCS_9= ruleNameExpCS
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
    // InternalCompleteOCL.g:5109:1: entryRuleNameExpCS returns [EObject current=null] : iv_ruleNameExpCS= ruleNameExpCS EOF ;
    public final EObject entryRuleNameExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNameExpCS = null;


        try {
            // InternalCompleteOCL.g:5109:50: (iv_ruleNameExpCS= ruleNameExpCS EOF )
            // InternalCompleteOCL.g:5110:2: iv_ruleNameExpCS= ruleNameExpCS EOF
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
    // InternalCompleteOCL.g:5116:1: ruleNameExpCS returns [EObject current=null] : ( ( (lv_ownedPathName_0_0= rulePathNameCS ) ) ( (lv_ownedSquareBracketedClauses_1_0= ruleSquareBracketedClauseCS ) )* ( (lv_ownedRoundBracketedClause_2_0= ruleRoundBracketedClauseCS ) )? ( (lv_ownedCurlyBracketedClause_3_0= ruleCurlyBracketedClauseCS ) )? ( ( (lv_isPre_4_0= '@' ) ) otherlv_5= 'pre' )? ) ;
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
            // InternalCompleteOCL.g:5122:2: ( ( ( (lv_ownedPathName_0_0= rulePathNameCS ) ) ( (lv_ownedSquareBracketedClauses_1_0= ruleSquareBracketedClauseCS ) )* ( (lv_ownedRoundBracketedClause_2_0= ruleRoundBracketedClauseCS ) )? ( (lv_ownedCurlyBracketedClause_3_0= ruleCurlyBracketedClauseCS ) )? ( ( (lv_isPre_4_0= '@' ) ) otherlv_5= 'pre' )? ) )
            // InternalCompleteOCL.g:5123:2: ( ( (lv_ownedPathName_0_0= rulePathNameCS ) ) ( (lv_ownedSquareBracketedClauses_1_0= ruleSquareBracketedClauseCS ) )* ( (lv_ownedRoundBracketedClause_2_0= ruleRoundBracketedClauseCS ) )? ( (lv_ownedCurlyBracketedClause_3_0= ruleCurlyBracketedClauseCS ) )? ( ( (lv_isPre_4_0= '@' ) ) otherlv_5= 'pre' )? )
            {
            // InternalCompleteOCL.g:5123:2: ( ( (lv_ownedPathName_0_0= rulePathNameCS ) ) ( (lv_ownedSquareBracketedClauses_1_0= ruleSquareBracketedClauseCS ) )* ( (lv_ownedRoundBracketedClause_2_0= ruleRoundBracketedClauseCS ) )? ( (lv_ownedCurlyBracketedClause_3_0= ruleCurlyBracketedClauseCS ) )? ( ( (lv_isPre_4_0= '@' ) ) otherlv_5= 'pre' )? )
            // InternalCompleteOCL.g:5124:3: ( (lv_ownedPathName_0_0= rulePathNameCS ) ) ( (lv_ownedSquareBracketedClauses_1_0= ruleSquareBracketedClauseCS ) )* ( (lv_ownedRoundBracketedClause_2_0= ruleRoundBracketedClauseCS ) )? ( (lv_ownedCurlyBracketedClause_3_0= ruleCurlyBracketedClauseCS ) )? ( ( (lv_isPre_4_0= '@' ) ) otherlv_5= 'pre' )?
            {
            // InternalCompleteOCL.g:5124:3: ( (lv_ownedPathName_0_0= rulePathNameCS ) )
            // InternalCompleteOCL.g:5125:4: (lv_ownedPathName_0_0= rulePathNameCS )
            {
            // InternalCompleteOCL.g:5125:4: (lv_ownedPathName_0_0= rulePathNameCS )
            // InternalCompleteOCL.g:5126:5: lv_ownedPathName_0_0= rulePathNameCS
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getNameExpCSAccess().getOwnedPathNamePathNameCSParserRuleCall_0_0());

            }
            pushFollow(FollowSets000.FOLLOW_51);
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

            // InternalCompleteOCL.g:5143:3: ( (lv_ownedSquareBracketedClauses_1_0= ruleSquareBracketedClauseCS ) )*
            loop92:
            do {
                int alt92=2;
                int LA92_0 = input.LA(1);

                if ( (LA92_0==93) ) {
                    alt92=1;
                }


                switch (alt92) {
            	case 1 :
            	    // InternalCompleteOCL.g:5144:4: (lv_ownedSquareBracketedClauses_1_0= ruleSquareBracketedClauseCS )
            	    {
            	    // InternalCompleteOCL.g:5144:4: (lv_ownedSquareBracketedClauses_1_0= ruleSquareBracketedClauseCS )
            	    // InternalCompleteOCL.g:5145:5: lv_ownedSquareBracketedClauses_1_0= ruleSquareBracketedClauseCS
            	    {
            	    if ( state.backtracking==0 ) {

            	      					newCompositeNode(grammarAccess.getNameExpCSAccess().getOwnedSquareBracketedClausesSquareBracketedClauseCSParserRuleCall_1_0());

            	    }
            	    pushFollow(FollowSets000.FOLLOW_51);
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
            	    break loop92;
                }
            } while (true);

            // InternalCompleteOCL.g:5162:3: ( (lv_ownedRoundBracketedClause_2_0= ruleRoundBracketedClauseCS ) )?
            int alt93=2;
            int LA93_0 = input.LA(1);

            if ( (LA93_0==21) ) {
                alt93=1;
            }
            switch (alt93) {
                case 1 :
                    // InternalCompleteOCL.g:5163:4: (lv_ownedRoundBracketedClause_2_0= ruleRoundBracketedClauseCS )
                    {
                    // InternalCompleteOCL.g:5163:4: (lv_ownedRoundBracketedClause_2_0= ruleRoundBracketedClauseCS )
                    // InternalCompleteOCL.g:5164:5: lv_ownedRoundBracketedClause_2_0= ruleRoundBracketedClauseCS
                    {
                    if ( state.backtracking==0 ) {

                      					newCompositeNode(grammarAccess.getNameExpCSAccess().getOwnedRoundBracketedClauseRoundBracketedClauseCSParserRuleCall_2_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_52);
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

            // InternalCompleteOCL.g:5181:3: ( (lv_ownedCurlyBracketedClause_3_0= ruleCurlyBracketedClauseCS ) )?
            int alt94=2;
            int LA94_0 = input.LA(1);

            if ( (LA94_0==81) ) {
                alt94=1;
            }
            switch (alt94) {
                case 1 :
                    // InternalCompleteOCL.g:5182:4: (lv_ownedCurlyBracketedClause_3_0= ruleCurlyBracketedClauseCS )
                    {
                    // InternalCompleteOCL.g:5182:4: (lv_ownedCurlyBracketedClause_3_0= ruleCurlyBracketedClauseCS )
                    // InternalCompleteOCL.g:5183:5: lv_ownedCurlyBracketedClause_3_0= ruleCurlyBracketedClauseCS
                    {
                    if ( state.backtracking==0 ) {

                      					newCompositeNode(grammarAccess.getNameExpCSAccess().getOwnedCurlyBracketedClauseCurlyBracketedClauseCSParserRuleCall_3_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_53);
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

            // InternalCompleteOCL.g:5200:3: ( ( (lv_isPre_4_0= '@' ) ) otherlv_5= 'pre' )?
            int alt95=2;
            int LA95_0 = input.LA(1);

            if ( (LA95_0==92) ) {
                alt95=1;
            }
            switch (alt95) {
                case 1 :
                    // InternalCompleteOCL.g:5201:4: ( (lv_isPre_4_0= '@' ) ) otherlv_5= 'pre'
                    {
                    // InternalCompleteOCL.g:5201:4: ( (lv_isPre_4_0= '@' ) )
                    // InternalCompleteOCL.g:5202:5: (lv_isPre_4_0= '@' )
                    {
                    // InternalCompleteOCL.g:5202:5: (lv_isPre_4_0= '@' )
                    // InternalCompleteOCL.g:5203:6: lv_isPre_4_0= '@'
                    {
                    lv_isPre_4_0=(Token)match(input,92,FollowSets000.FOLLOW_54); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						newLeafNode(lv_isPre_4_0, grammarAccess.getNameExpCSAccess().getIsPreCommercialAtKeyword_4_0_0());

                    }
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElement(grammarAccess.getNameExpCSRule());
                      						}
                      						setWithLastConsumed(current, "isPre", lv_isPre_4_0 != null, "@");

                    }

                    }


                    }

                    otherlv_5=(Token)match(input,24,FollowSets000.FOLLOW_2); if (state.failed) return current;
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
    // InternalCompleteOCL.g:5224:1: entryRuleCurlyBracketedClauseCS returns [EObject current=null] : iv_ruleCurlyBracketedClauseCS= ruleCurlyBracketedClauseCS EOF ;
    public final EObject entryRuleCurlyBracketedClauseCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCurlyBracketedClauseCS = null;


        try {
            // InternalCompleteOCL.g:5224:63: (iv_ruleCurlyBracketedClauseCS= ruleCurlyBracketedClauseCS EOF )
            // InternalCompleteOCL.g:5225:2: iv_ruleCurlyBracketedClauseCS= ruleCurlyBracketedClauseCS EOF
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
    // InternalCompleteOCL.g:5231:1: ruleCurlyBracketedClauseCS returns [EObject current=null] : ( () otherlv_1= '{' ( ( (lv_ownedParts_2_0= ruleShadowPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleShadowPartCS ) ) )* )? otherlv_5= '}' ) ;
    public final EObject ruleCurlyBracketedClauseCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        EObject lv_ownedParts_2_0 = null;

        EObject lv_ownedParts_4_0 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:5237:2: ( ( () otherlv_1= '{' ( ( (lv_ownedParts_2_0= ruleShadowPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleShadowPartCS ) ) )* )? otherlv_5= '}' ) )
            // InternalCompleteOCL.g:5238:2: ( () otherlv_1= '{' ( ( (lv_ownedParts_2_0= ruleShadowPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleShadowPartCS ) ) )* )? otherlv_5= '}' )
            {
            // InternalCompleteOCL.g:5238:2: ( () otherlv_1= '{' ( ( (lv_ownedParts_2_0= ruleShadowPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleShadowPartCS ) ) )* )? otherlv_5= '}' )
            // InternalCompleteOCL.g:5239:3: () otherlv_1= '{' ( ( (lv_ownedParts_2_0= ruleShadowPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleShadowPartCS ) ) )* )? otherlv_5= '}'
            {
            // InternalCompleteOCL.g:5239:3: ()
            // InternalCompleteOCL.g:5240:4:
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

            otherlv_1=(Token)match(input,81,FollowSets000.FOLLOW_55); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getCurlyBracketedClauseCSAccess().getLeftCurlyBracketKeyword_1());

            }
            // InternalCompleteOCL.g:5253:3: ( ( (lv_ownedParts_2_0= ruleShadowPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleShadowPartCS ) ) )* )?
            int alt97=2;
            int LA97_0 = input.LA(1);

            if ( ((LA97_0>=RULE_SIMPLE_ID && LA97_0<=RULE_ESCAPED_ID)||LA97_0==RULE_SINGLE_QUOTED_STRING||(LA97_0>=30 && LA97_0<=32)) ) {
                alt97=1;
            }
            switch (alt97) {
                case 1 :
                    // InternalCompleteOCL.g:5254:4: ( (lv_ownedParts_2_0= ruleShadowPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleShadowPartCS ) ) )*
                    {
                    // InternalCompleteOCL.g:5254:4: ( (lv_ownedParts_2_0= ruleShadowPartCS ) )
                    // InternalCompleteOCL.g:5255:5: (lv_ownedParts_2_0= ruleShadowPartCS )
                    {
                    // InternalCompleteOCL.g:5255:5: (lv_ownedParts_2_0= ruleShadowPartCS )
                    // InternalCompleteOCL.g:5256:6: lv_ownedParts_2_0= ruleShadowPartCS
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getCurlyBracketedClauseCSAccess().getOwnedPartsShadowPartCSParserRuleCall_2_0_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_38);
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

                    // InternalCompleteOCL.g:5273:4: (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleShadowPartCS ) ) )*
                    loop96:
                    do {
                        int alt96=2;
                        int LA96_0 = input.LA(1);

                        if ( (LA96_0==28) ) {
                            alt96=1;
                        }


                        switch (alt96) {
                    	case 1 :
                    	    // InternalCompleteOCL.g:5274:5: otherlv_3= ',' ( (lv_ownedParts_4_0= ruleShadowPartCS ) )
                    	    {
                    	    otherlv_3=(Token)match(input,28,FollowSets000.FOLLOW_22); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      					newLeafNode(otherlv_3, grammarAccess.getCurlyBracketedClauseCSAccess().getCommaKeyword_2_1_0());

                    	    }
                    	    // InternalCompleteOCL.g:5278:5: ( (lv_ownedParts_4_0= ruleShadowPartCS ) )
                    	    // InternalCompleteOCL.g:5279:6: (lv_ownedParts_4_0= ruleShadowPartCS )
                    	    {
                    	    // InternalCompleteOCL.g:5279:6: (lv_ownedParts_4_0= ruleShadowPartCS )
                    	    // InternalCompleteOCL.g:5280:7: lv_ownedParts_4_0= ruleShadowPartCS
                    	    {
                    	    if ( state.backtracking==0 ) {

                    	      							newCompositeNode(grammarAccess.getCurlyBracketedClauseCSAccess().getOwnedPartsShadowPartCSParserRuleCall_2_1_1_0());

                    	    }
                    	    pushFollow(FollowSets000.FOLLOW_38);
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
                    	    break loop96;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_5=(Token)match(input,82,FollowSets000.FOLLOW_2); if (state.failed) return current;
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
    // InternalCompleteOCL.g:5307:1: entryRuleRoundBracketedClauseCS returns [EObject current=null] : iv_ruleRoundBracketedClauseCS= ruleRoundBracketedClauseCS EOF ;
    public final EObject entryRuleRoundBracketedClauseCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRoundBracketedClauseCS = null;


        try {
            // InternalCompleteOCL.g:5307:63: (iv_ruleRoundBracketedClauseCS= ruleRoundBracketedClauseCS EOF )
            // InternalCompleteOCL.g:5308:2: iv_ruleRoundBracketedClauseCS= ruleRoundBracketedClauseCS EOF
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
    // InternalCompleteOCL.g:5314:1: ruleRoundBracketedClauseCS returns [EObject current=null] : ( () otherlv_1= '(' ( ( (lv_ownedArguments_2_0= ruleNavigatingArgCS ) ) ( ( (lv_ownedArguments_3_1= ruleNavigatingCommaArgCS | lv_ownedArguments_3_2= ruleNavigatingSemiArgCS | lv_ownedArguments_3_3= ruleNavigatingBarArgCS ) ) )* )? otherlv_4= ')' ) ;
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
            // InternalCompleteOCL.g:5320:2: ( ( () otherlv_1= '(' ( ( (lv_ownedArguments_2_0= ruleNavigatingArgCS ) ) ( ( (lv_ownedArguments_3_1= ruleNavigatingCommaArgCS | lv_ownedArguments_3_2= ruleNavigatingSemiArgCS | lv_ownedArguments_3_3= ruleNavigatingBarArgCS ) ) )* )? otherlv_4= ')' ) )
            // InternalCompleteOCL.g:5321:2: ( () otherlv_1= '(' ( ( (lv_ownedArguments_2_0= ruleNavigatingArgCS ) ) ( ( (lv_ownedArguments_3_1= ruleNavigatingCommaArgCS | lv_ownedArguments_3_2= ruleNavigatingSemiArgCS | lv_ownedArguments_3_3= ruleNavigatingBarArgCS ) ) )* )? otherlv_4= ')' )
            {
            // InternalCompleteOCL.g:5321:2: ( () otherlv_1= '(' ( ( (lv_ownedArguments_2_0= ruleNavigatingArgCS ) ) ( ( (lv_ownedArguments_3_1= ruleNavigatingCommaArgCS | lv_ownedArguments_3_2= ruleNavigatingSemiArgCS | lv_ownedArguments_3_3= ruleNavigatingBarArgCS ) ) )* )? otherlv_4= ')' )
            // InternalCompleteOCL.g:5322:3: () otherlv_1= '(' ( ( (lv_ownedArguments_2_0= ruleNavigatingArgCS ) ) ( ( (lv_ownedArguments_3_1= ruleNavigatingCommaArgCS | lv_ownedArguments_3_2= ruleNavigatingSemiArgCS | lv_ownedArguments_3_3= ruleNavigatingBarArgCS ) ) )* )? otherlv_4= ')'
            {
            // InternalCompleteOCL.g:5322:3: ()
            // InternalCompleteOCL.g:5323:4:
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

            otherlv_1=(Token)match(input,21,FollowSets000.FOLLOW_56); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getRoundBracketedClauseCSAccess().getLeftParenthesisKeyword_1());

            }
            // InternalCompleteOCL.g:5336:3: ( ( (lv_ownedArguments_2_0= ruleNavigatingArgCS ) ) ( ( (lv_ownedArguments_3_1= ruleNavigatingCommaArgCS | lv_ownedArguments_3_2= ruleNavigatingSemiArgCS | lv_ownedArguments_3_3= ruleNavigatingBarArgCS ) ) )* )?
            int alt100=2;
            int LA100_0 = input.LA(1);

            if ( ((LA100_0>=RULE_SIMPLE_ID && LA100_0<=RULE_SINGLE_QUOTED_STRING)||LA100_0==21||LA100_0==23||(LA100_0>=30 && LA100_0<=32)||(LA100_0>=41 && LA100_0<=55)||(LA100_0>=73 && LA100_0<=74)||(LA100_0>=76 && LA100_0<=80)||LA100_0==85||(LA100_0>=88 && LA100_0<=91)||LA100_0==98||(LA100_0>=103 && LA100_0<=104)) ) {
                alt100=1;
            }
            switch (alt100) {
                case 1 :
                    // InternalCompleteOCL.g:5337:4: ( (lv_ownedArguments_2_0= ruleNavigatingArgCS ) ) ( ( (lv_ownedArguments_3_1= ruleNavigatingCommaArgCS | lv_ownedArguments_3_2= ruleNavigatingSemiArgCS | lv_ownedArguments_3_3= ruleNavigatingBarArgCS ) ) )*
                    {
                    // InternalCompleteOCL.g:5337:4: ( (lv_ownedArguments_2_0= ruleNavigatingArgCS ) )
                    // InternalCompleteOCL.g:5338:5: (lv_ownedArguments_2_0= ruleNavigatingArgCS )
                    {
                    // InternalCompleteOCL.g:5338:5: (lv_ownedArguments_2_0= ruleNavigatingArgCS )
                    // InternalCompleteOCL.g:5339:6: lv_ownedArguments_2_0= ruleNavigatingArgCS
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getRoundBracketedClauseCSAccess().getOwnedArgumentsNavigatingArgCSParserRuleCall_2_0_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_57);
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

                    // InternalCompleteOCL.g:5356:4: ( ( (lv_ownedArguments_3_1= ruleNavigatingCommaArgCS | lv_ownedArguments_3_2= ruleNavigatingSemiArgCS | lv_ownedArguments_3_3= ruleNavigatingBarArgCS ) ) )*
                    loop99:
                    do {
                        int alt99=2;
                        int LA99_0 = input.LA(1);

                        if ( (LA99_0==28||(LA99_0>=96 && LA99_0<=97)) ) {
                            alt99=1;
                        }


                        switch (alt99) {
                    	case 1 :
                    	    // InternalCompleteOCL.g:5357:5: ( (lv_ownedArguments_3_1= ruleNavigatingCommaArgCS | lv_ownedArguments_3_2= ruleNavigatingSemiArgCS | lv_ownedArguments_3_3= ruleNavigatingBarArgCS ) )
                    	    {
                    	    // InternalCompleteOCL.g:5357:5: ( (lv_ownedArguments_3_1= ruleNavigatingCommaArgCS | lv_ownedArguments_3_2= ruleNavigatingSemiArgCS | lv_ownedArguments_3_3= ruleNavigatingBarArgCS ) )
                    	    // InternalCompleteOCL.g:5358:6: (lv_ownedArguments_3_1= ruleNavigatingCommaArgCS | lv_ownedArguments_3_2= ruleNavigatingSemiArgCS | lv_ownedArguments_3_3= ruleNavigatingBarArgCS )
                    	    {
                    	    // InternalCompleteOCL.g:5358:6: (lv_ownedArguments_3_1= ruleNavigatingCommaArgCS | lv_ownedArguments_3_2= ruleNavigatingSemiArgCS | lv_ownedArguments_3_3= ruleNavigatingBarArgCS )
                    	    int alt98=3;
                    	    switch ( input.LA(1) ) {
                    	    case 28:
                    	        {
                    	        alt98=1;
                    	        }
                    	        break;
                    	    case 97:
                    	        {
                    	        alt98=2;
                    	        }
                    	        break;
                    	    case 96:
                    	        {
                    	        alt98=3;
                    	        }
                    	        break;
                    	    default:
                    	        if (state.backtracking>0) {state.failed=true; return current;}
                    	        NoViableAltException nvae =
                    	            new NoViableAltException("", 98, 0, input);

                    	        throw nvae;
                    	    }

                    	    switch (alt98) {
                    	        case 1 :
                    	            // InternalCompleteOCL.g:5359:7: lv_ownedArguments_3_1= ruleNavigatingCommaArgCS
                    	            {
                    	            if ( state.backtracking==0 ) {

                    	              							newCompositeNode(grammarAccess.getRoundBracketedClauseCSAccess().getOwnedArgumentsNavigatingCommaArgCSParserRuleCall_2_1_0_0());

                    	            }
                    	            pushFollow(FollowSets000.FOLLOW_57);
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
                    	            // InternalCompleteOCL.g:5375:7: lv_ownedArguments_3_2= ruleNavigatingSemiArgCS
                    	            {
                    	            if ( state.backtracking==0 ) {

                    	              							newCompositeNode(grammarAccess.getRoundBracketedClauseCSAccess().getOwnedArgumentsNavigatingSemiArgCSParserRuleCall_2_1_0_1());

                    	            }
                    	            pushFollow(FollowSets000.FOLLOW_57);
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
                    	            // InternalCompleteOCL.g:5391:7: lv_ownedArguments_3_3= ruleNavigatingBarArgCS
                    	            {
                    	            if ( state.backtracking==0 ) {

                    	              							newCompositeNode(grammarAccess.getRoundBracketedClauseCSAccess().getOwnedArgumentsNavigatingBarArgCSParserRuleCall_2_1_0_2());

                    	            }
                    	            pushFollow(FollowSets000.FOLLOW_57);
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
                    	    break loop99;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_4=(Token)match(input,22,FollowSets000.FOLLOW_2); if (state.failed) return current;
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
    // InternalCompleteOCL.g:5418:1: entryRuleSquareBracketedClauseCS returns [EObject current=null] : iv_ruleSquareBracketedClauseCS= ruleSquareBracketedClauseCS EOF ;
    public final EObject entryRuleSquareBracketedClauseCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSquareBracketedClauseCS = null;


        try {
            // InternalCompleteOCL.g:5418:64: (iv_ruleSquareBracketedClauseCS= ruleSquareBracketedClauseCS EOF )
            // InternalCompleteOCL.g:5419:2: iv_ruleSquareBracketedClauseCS= ruleSquareBracketedClauseCS EOF
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
    // InternalCompleteOCL.g:5425:1: ruleSquareBracketedClauseCS returns [EObject current=null] : (otherlv_0= '[' ( (lv_ownedTerms_1_0= ruleExpCS ) ) (otherlv_2= ',' ( (lv_ownedTerms_3_0= ruleExpCS ) ) )* otherlv_4= ']' ) ;
    public final EObject ruleSquareBracketedClauseCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_ownedTerms_1_0 = null;

        EObject lv_ownedTerms_3_0 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:5431:2: ( (otherlv_0= '[' ( (lv_ownedTerms_1_0= ruleExpCS ) ) (otherlv_2= ',' ( (lv_ownedTerms_3_0= ruleExpCS ) ) )* otherlv_4= ']' ) )
            // InternalCompleteOCL.g:5432:2: (otherlv_0= '[' ( (lv_ownedTerms_1_0= ruleExpCS ) ) (otherlv_2= ',' ( (lv_ownedTerms_3_0= ruleExpCS ) ) )* otherlv_4= ']' )
            {
            // InternalCompleteOCL.g:5432:2: (otherlv_0= '[' ( (lv_ownedTerms_1_0= ruleExpCS ) ) (otherlv_2= ',' ( (lv_ownedTerms_3_0= ruleExpCS ) ) )* otherlv_4= ']' )
            // InternalCompleteOCL.g:5433:3: otherlv_0= '[' ( (lv_ownedTerms_1_0= ruleExpCS ) ) (otherlv_2= ',' ( (lv_ownedTerms_3_0= ruleExpCS ) ) )* otherlv_4= ']'
            {
            otherlv_0=(Token)match(input,93,FollowSets000.FOLLOW_30); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getSquareBracketedClauseCSAccess().getLeftSquareBracketKeyword_0());

            }
            // InternalCompleteOCL.g:5437:3: ( (lv_ownedTerms_1_0= ruleExpCS ) )
            // InternalCompleteOCL.g:5438:4: (lv_ownedTerms_1_0= ruleExpCS )
            {
            // InternalCompleteOCL.g:5438:4: (lv_ownedTerms_1_0= ruleExpCS )
            // InternalCompleteOCL.g:5439:5: lv_ownedTerms_1_0= ruleExpCS
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getSquareBracketedClauseCSAccess().getOwnedTermsExpCSParserRuleCall_1_0());

            }
            pushFollow(FollowSets000.FOLLOW_58);
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

            // InternalCompleteOCL.g:5456:3: (otherlv_2= ',' ( (lv_ownedTerms_3_0= ruleExpCS ) ) )*
            loop101:
            do {
                int alt101=2;
                int LA101_0 = input.LA(1);

                if ( (LA101_0==28) ) {
                    alt101=1;
                }


                switch (alt101) {
            	case 1 :
            	    // InternalCompleteOCL.g:5457:4: otherlv_2= ',' ( (lv_ownedTerms_3_0= ruleExpCS ) )
            	    {
            	    otherlv_2=(Token)match(input,28,FollowSets000.FOLLOW_30); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      				newLeafNode(otherlv_2, grammarAccess.getSquareBracketedClauseCSAccess().getCommaKeyword_2_0());

            	    }
            	    // InternalCompleteOCL.g:5461:4: ( (lv_ownedTerms_3_0= ruleExpCS ) )
            	    // InternalCompleteOCL.g:5462:5: (lv_ownedTerms_3_0= ruleExpCS )
            	    {
            	    // InternalCompleteOCL.g:5462:5: (lv_ownedTerms_3_0= ruleExpCS )
            	    // InternalCompleteOCL.g:5463:6: lv_ownedTerms_3_0= ruleExpCS
            	    {
            	    if ( state.backtracking==0 ) {

            	      						newCompositeNode(grammarAccess.getSquareBracketedClauseCSAccess().getOwnedTermsExpCSParserRuleCall_2_1_0());

            	    }
            	    pushFollow(FollowSets000.FOLLOW_58);
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
            	    break loop101;
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
    // InternalCompleteOCL.g:5489:1: entryRuleNavigatingArgCS returns [EObject current=null] : iv_ruleNavigatingArgCS= ruleNavigatingArgCS EOF ;
    public final EObject entryRuleNavigatingArgCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNavigatingArgCS = null;


        try {
            // InternalCompleteOCL.g:5489:56: (iv_ruleNavigatingArgCS= ruleNavigatingArgCS EOF )
            // InternalCompleteOCL.g:5490:2: iv_ruleNavigatingArgCS= ruleNavigatingArgCS EOF
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
    // InternalCompleteOCL.g:5496:1: ruleNavigatingArgCS returns [EObject current=null] : ( ( ( (lv_ownedNameExpression_0_0= ruleNavigatingArgExpCS ) ) ( ( (otherlv_1= 'with' | otherlv_2= '<-' ) ( (lv_ownedCoIterator_3_0= ruleCoIteratorVariableCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? ) | (otherlv_6= ':' ( (lv_ownedType_7_0= ruleTypeExpCS ) ) ( (otherlv_8= 'with' | otherlv_9= '<-' ) ( (lv_ownedCoIterator_10_0= ruleCoIteratorVariableCS ) ) )? (otherlv_11= '=' ( (lv_ownedInitExpression_12_0= ruleExpCS ) ) )? ) | ( (otherlv_13= ':' ( (lv_ownedType_14_0= ruleTypeExpCS ) ) )? ( (otherlv_15= 'with' | otherlv_16= '<-' ) ( (lv_ownedCoIterator_17_0= ruleCoIteratorVariableCS ) ) )? otherlv_18= 'in' ( (lv_ownedInitExpression_19_0= ruleExpCS ) ) ) )? ) | (otherlv_20= ':' ( (lv_ownedType_21_0= ruleTypeExpCS ) ) ) ) ;
    public final EObject ruleNavigatingArgCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token otherlv_9=null;
        Token otherlv_11=null;
        Token otherlv_13=null;
        Token otherlv_15=null;
        Token otherlv_16=null;
        Token otherlv_18=null;
        Token otherlv_20=null;
        EObject lv_ownedNameExpression_0_0 = null;

        EObject lv_ownedCoIterator_3_0 = null;

        EObject lv_ownedInitExpression_5_0 = null;

        EObject lv_ownedType_7_0 = null;

        EObject lv_ownedCoIterator_10_0 = null;

        EObject lv_ownedInitExpression_12_0 = null;

        EObject lv_ownedType_14_0 = null;

        EObject lv_ownedCoIterator_17_0 = null;

        EObject lv_ownedInitExpression_19_0 = null;

        EObject lv_ownedType_21_0 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:5502:2: ( ( ( ( (lv_ownedNameExpression_0_0= ruleNavigatingArgExpCS ) ) ( ( (otherlv_1= 'with' | otherlv_2= '<-' ) ( (lv_ownedCoIterator_3_0= ruleCoIteratorVariableCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? ) | (otherlv_6= ':' ( (lv_ownedType_7_0= ruleTypeExpCS ) ) ( (otherlv_8= 'with' | otherlv_9= '<-' ) ( (lv_ownedCoIterator_10_0= ruleCoIteratorVariableCS ) ) )? (otherlv_11= '=' ( (lv_ownedInitExpression_12_0= ruleExpCS ) ) )? ) | ( (otherlv_13= ':' ( (lv_ownedType_14_0= ruleTypeExpCS ) ) )? ( (otherlv_15= 'with' | otherlv_16= '<-' ) ( (lv_ownedCoIterator_17_0= ruleCoIteratorVariableCS ) ) )? otherlv_18= 'in' ( (lv_ownedInitExpression_19_0= ruleExpCS ) ) ) )? ) | (otherlv_20= ':' ( (lv_ownedType_21_0= ruleTypeExpCS ) ) ) ) )
            // InternalCompleteOCL.g:5503:2: ( ( ( (lv_ownedNameExpression_0_0= ruleNavigatingArgExpCS ) ) ( ( (otherlv_1= 'with' | otherlv_2= '<-' ) ( (lv_ownedCoIterator_3_0= ruleCoIteratorVariableCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? ) | (otherlv_6= ':' ( (lv_ownedType_7_0= ruleTypeExpCS ) ) ( (otherlv_8= 'with' | otherlv_9= '<-' ) ( (lv_ownedCoIterator_10_0= ruleCoIteratorVariableCS ) ) )? (otherlv_11= '=' ( (lv_ownedInitExpression_12_0= ruleExpCS ) ) )? ) | ( (otherlv_13= ':' ( (lv_ownedType_14_0= ruleTypeExpCS ) ) )? ( (otherlv_15= 'with' | otherlv_16= '<-' ) ( (lv_ownedCoIterator_17_0= ruleCoIteratorVariableCS ) ) )? otherlv_18= 'in' ( (lv_ownedInitExpression_19_0= ruleExpCS ) ) ) )? ) | (otherlv_20= ':' ( (lv_ownedType_21_0= ruleTypeExpCS ) ) ) )
            {
            // InternalCompleteOCL.g:5503:2: ( ( ( (lv_ownedNameExpression_0_0= ruleNavigatingArgExpCS ) ) ( ( (otherlv_1= 'with' | otherlv_2= '<-' ) ( (lv_ownedCoIterator_3_0= ruleCoIteratorVariableCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? ) | (otherlv_6= ':' ( (lv_ownedType_7_0= ruleTypeExpCS ) ) ( (otherlv_8= 'with' | otherlv_9= '<-' ) ( (lv_ownedCoIterator_10_0= ruleCoIteratorVariableCS ) ) )? (otherlv_11= '=' ( (lv_ownedInitExpression_12_0= ruleExpCS ) ) )? ) | ( (otherlv_13= ':' ( (lv_ownedType_14_0= ruleTypeExpCS ) ) )? ( (otherlv_15= 'with' | otherlv_16= '<-' ) ( (lv_ownedCoIterator_17_0= ruleCoIteratorVariableCS ) ) )? otherlv_18= 'in' ( (lv_ownedInitExpression_19_0= ruleExpCS ) ) ) )? ) | (otherlv_20= ':' ( (lv_ownedType_21_0= ruleTypeExpCS ) ) ) )
            int alt111=2;
            int LA111_0 = input.LA(1);

            if ( ((LA111_0>=RULE_SIMPLE_ID && LA111_0<=RULE_SINGLE_QUOTED_STRING)||LA111_0==21||(LA111_0>=30 && LA111_0<=32)||(LA111_0>=41 && LA111_0<=55)||(LA111_0>=73 && LA111_0<=74)||(LA111_0>=76 && LA111_0<=80)||LA111_0==85||(LA111_0>=88 && LA111_0<=91)||LA111_0==98||(LA111_0>=103 && LA111_0<=104)) ) {
                alt111=1;
            }
            else if ( (LA111_0==23) ) {
                alt111=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 111, 0, input);

                throw nvae;
            }
            switch (alt111) {
                case 1 :
                    // InternalCompleteOCL.g:5504:3: ( ( (lv_ownedNameExpression_0_0= ruleNavigatingArgExpCS ) ) ( ( (otherlv_1= 'with' | otherlv_2= '<-' ) ( (lv_ownedCoIterator_3_0= ruleCoIteratorVariableCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? ) | (otherlv_6= ':' ( (lv_ownedType_7_0= ruleTypeExpCS ) ) ( (otherlv_8= 'with' | otherlv_9= '<-' ) ( (lv_ownedCoIterator_10_0= ruleCoIteratorVariableCS ) ) )? (otherlv_11= '=' ( (lv_ownedInitExpression_12_0= ruleExpCS ) ) )? ) | ( (otherlv_13= ':' ( (lv_ownedType_14_0= ruleTypeExpCS ) ) )? ( (otherlv_15= 'with' | otherlv_16= '<-' ) ( (lv_ownedCoIterator_17_0= ruleCoIteratorVariableCS ) ) )? otherlv_18= 'in' ( (lv_ownedInitExpression_19_0= ruleExpCS ) ) ) )? )
                    {
                    // InternalCompleteOCL.g:5504:3: ( ( (lv_ownedNameExpression_0_0= ruleNavigatingArgExpCS ) ) ( ( (otherlv_1= 'with' | otherlv_2= '<-' ) ( (lv_ownedCoIterator_3_0= ruleCoIteratorVariableCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? ) | (otherlv_6= ':' ( (lv_ownedType_7_0= ruleTypeExpCS ) ) ( (otherlv_8= 'with' | otherlv_9= '<-' ) ( (lv_ownedCoIterator_10_0= ruleCoIteratorVariableCS ) ) )? (otherlv_11= '=' ( (lv_ownedInitExpression_12_0= ruleExpCS ) ) )? ) | ( (otherlv_13= ':' ( (lv_ownedType_14_0= ruleTypeExpCS ) ) )? ( (otherlv_15= 'with' | otherlv_16= '<-' ) ( (lv_ownedCoIterator_17_0= ruleCoIteratorVariableCS ) ) )? otherlv_18= 'in' ( (lv_ownedInitExpression_19_0= ruleExpCS ) ) ) )? )
                    // InternalCompleteOCL.g:5505:4: ( (lv_ownedNameExpression_0_0= ruleNavigatingArgExpCS ) ) ( ( (otherlv_1= 'with' | otherlv_2= '<-' ) ( (lv_ownedCoIterator_3_0= ruleCoIteratorVariableCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? ) | (otherlv_6= ':' ( (lv_ownedType_7_0= ruleTypeExpCS ) ) ( (otherlv_8= 'with' | otherlv_9= '<-' ) ( (lv_ownedCoIterator_10_0= ruleCoIteratorVariableCS ) ) )? (otherlv_11= '=' ( (lv_ownedInitExpression_12_0= ruleExpCS ) ) )? ) | ( (otherlv_13= ':' ( (lv_ownedType_14_0= ruleTypeExpCS ) ) )? ( (otherlv_15= 'with' | otherlv_16= '<-' ) ( (lv_ownedCoIterator_17_0= ruleCoIteratorVariableCS ) ) )? otherlv_18= 'in' ( (lv_ownedInitExpression_19_0= ruleExpCS ) ) ) )?
                    {
                    // InternalCompleteOCL.g:5505:4: ( (lv_ownedNameExpression_0_0= ruleNavigatingArgExpCS ) )
                    // InternalCompleteOCL.g:5506:5: (lv_ownedNameExpression_0_0= ruleNavigatingArgExpCS )
                    {
                    // InternalCompleteOCL.g:5506:5: (lv_ownedNameExpression_0_0= ruleNavigatingArgExpCS )
                    // InternalCompleteOCL.g:5507:6: lv_ownedNameExpression_0_0= ruleNavigatingArgExpCS
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getNavigatingArgCSAccess().getOwnedNameExpressionNavigatingArgExpCSParserRuleCall_0_0_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_59);
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

                    // InternalCompleteOCL.g:5524:4: ( ( (otherlv_1= 'with' | otherlv_2= '<-' ) ( (lv_ownedCoIterator_3_0= ruleCoIteratorVariableCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? ) | (otherlv_6= ':' ( (lv_ownedType_7_0= ruleTypeExpCS ) ) ( (otherlv_8= 'with' | otherlv_9= '<-' ) ( (lv_ownedCoIterator_10_0= ruleCoIteratorVariableCS ) ) )? (otherlv_11= '=' ( (lv_ownedInitExpression_12_0= ruleExpCS ) ) )? ) | ( (otherlv_13= ':' ( (lv_ownedType_14_0= ruleTypeExpCS ) ) )? ( (otherlv_15= 'with' | otherlv_16= '<-' ) ( (lv_ownedCoIterator_17_0= ruleCoIteratorVariableCS ) ) )? otherlv_18= 'in' ( (lv_ownedInitExpression_19_0= ruleExpCS ) ) ) )?
                    int alt110=4;
                    alt110 = dfa110.predict(input);
                    switch (alt110) {
                        case 1 :
                            // InternalCompleteOCL.g:5525:5: ( (otherlv_1= 'with' | otherlv_2= '<-' ) ( (lv_ownedCoIterator_3_0= ruleCoIteratorVariableCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? )
                            {
                            // InternalCompleteOCL.g:5525:5: ( (otherlv_1= 'with' | otherlv_2= '<-' ) ( (lv_ownedCoIterator_3_0= ruleCoIteratorVariableCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? )
                            // InternalCompleteOCL.g:5526:6: (otherlv_1= 'with' | otherlv_2= '<-' ) ( (lv_ownedCoIterator_3_0= ruleCoIteratorVariableCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )?
                            {
                            // InternalCompleteOCL.g:5526:6: (otherlv_1= 'with' | otherlv_2= '<-' )
                            int alt102=2;
                            int LA102_0 = input.LA(1);

                            if ( (LA102_0==86) ) {
                                alt102=1;
                            }
                            else if ( (LA102_0==87) ) {
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
                                    // InternalCompleteOCL.g:5527:7: otherlv_1= 'with'
                                    {
                                    otherlv_1=(Token)match(input,86,FollowSets000.FOLLOW_15); if (state.failed) return current;
                                    if ( state.backtracking==0 ) {

                                      							newLeafNode(otherlv_1, grammarAccess.getNavigatingArgCSAccess().getWithKeyword_0_1_0_0_0());

                                    }

                                    }
                                    break;
                                case 2 :
                                    // InternalCompleteOCL.g:5532:7: otherlv_2= '<-'
                                    {
                                    otherlv_2=(Token)match(input,87,FollowSets000.FOLLOW_15); if (state.failed) return current;
                                    if ( state.backtracking==0 ) {

                                      							newLeafNode(otherlv_2, grammarAccess.getNavigatingArgCSAccess().getLessThanSignHyphenMinusKeyword_0_1_0_0_1());

                                    }

                                    }
                                    break;

                            }

                            // InternalCompleteOCL.g:5537:6: ( (lv_ownedCoIterator_3_0= ruleCoIteratorVariableCS ) )
                            // InternalCompleteOCL.g:5538:7: (lv_ownedCoIterator_3_0= ruleCoIteratorVariableCS )
                            {
                            // InternalCompleteOCL.g:5538:7: (lv_ownedCoIterator_3_0= ruleCoIteratorVariableCS )
                            // InternalCompleteOCL.g:5539:8: lv_ownedCoIterator_3_0= ruleCoIteratorVariableCS
                            {
                            if ( state.backtracking==0 ) {

                              								newCompositeNode(grammarAccess.getNavigatingArgCSAccess().getOwnedCoIteratorCoIteratorVariableCSParserRuleCall_0_1_0_1_0());

                            }
                            pushFollow(FollowSets000.FOLLOW_60);
                            lv_ownedCoIterator_3_0=ruleCoIteratorVariableCS();

                            state._fsp--;
                            if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              								if (current==null) {
                              									current = createModelElementForParent(grammarAccess.getNavigatingArgCSRule());
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

                            // InternalCompleteOCL.g:5556:6: (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )?
                            int alt103=2;
                            int LA103_0 = input.LA(1);

                            if ( (LA103_0==29) ) {
                                alt103=1;
                            }
                            switch (alt103) {
                                case 1 :
                                    // InternalCompleteOCL.g:5557:7: otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) )
                                    {
                                    otherlv_4=(Token)match(input,29,FollowSets000.FOLLOW_30); if (state.failed) return current;
                                    if ( state.backtracking==0 ) {

                                      							newLeafNode(otherlv_4, grammarAccess.getNavigatingArgCSAccess().getEqualsSignKeyword_0_1_0_2_0());

                                    }
                                    // InternalCompleteOCL.g:5561:7: ( (lv_ownedInitExpression_5_0= ruleExpCS ) )
                                    // InternalCompleteOCL.g:5562:8: (lv_ownedInitExpression_5_0= ruleExpCS )
                                    {
                                    // InternalCompleteOCL.g:5562:8: (lv_ownedInitExpression_5_0= ruleExpCS )
                                    // InternalCompleteOCL.g:5563:9: lv_ownedInitExpression_5_0= ruleExpCS
                                    {
                                    if ( state.backtracking==0 ) {

                                      									newCompositeNode(grammarAccess.getNavigatingArgCSAccess().getOwnedInitExpressionExpCSParserRuleCall_0_1_0_2_1_0());

                                    }
                                    pushFollow(FollowSets000.FOLLOW_2);
                                    lv_ownedInitExpression_5_0=ruleExpCS();

                                    state._fsp--;
                                    if (state.failed) return current;
                                    if ( state.backtracking==0 ) {

                                      									if (current==null) {
                                      										current = createModelElementForParent(grammarAccess.getNavigatingArgCSRule());
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
                            // InternalCompleteOCL.g:5583:5: (otherlv_6= ':' ( (lv_ownedType_7_0= ruleTypeExpCS ) ) ( (otherlv_8= 'with' | otherlv_9= '<-' ) ( (lv_ownedCoIterator_10_0= ruleCoIteratorVariableCS ) ) )? (otherlv_11= '=' ( (lv_ownedInitExpression_12_0= ruleExpCS ) ) )? )
                            {
                            // InternalCompleteOCL.g:5583:5: (otherlv_6= ':' ( (lv_ownedType_7_0= ruleTypeExpCS ) ) ( (otherlv_8= 'with' | otherlv_9= '<-' ) ( (lv_ownedCoIterator_10_0= ruleCoIteratorVariableCS ) ) )? (otherlv_11= '=' ( (lv_ownedInitExpression_12_0= ruleExpCS ) ) )? )
                            // InternalCompleteOCL.g:5584:6: otherlv_6= ':' ( (lv_ownedType_7_0= ruleTypeExpCS ) ) ( (otherlv_8= 'with' | otherlv_9= '<-' ) ( (lv_ownedCoIterator_10_0= ruleCoIteratorVariableCS ) ) )? (otherlv_11= '=' ( (lv_ownedInitExpression_12_0= ruleExpCS ) ) )?
                            {
                            otherlv_6=(Token)match(input,23,FollowSets000.FOLLOW_21); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              						newLeafNode(otherlv_6, grammarAccess.getNavigatingArgCSAccess().getColonKeyword_0_1_1_0());

                            }
                            // InternalCompleteOCL.g:5588:6: ( (lv_ownedType_7_0= ruleTypeExpCS ) )
                            // InternalCompleteOCL.g:5589:7: (lv_ownedType_7_0= ruleTypeExpCS )
                            {
                            // InternalCompleteOCL.g:5589:7: (lv_ownedType_7_0= ruleTypeExpCS )
                            // InternalCompleteOCL.g:5590:8: lv_ownedType_7_0= ruleTypeExpCS
                            {
                            if ( state.backtracking==0 ) {

                              								newCompositeNode(grammarAccess.getNavigatingArgCSAccess().getOwnedTypeTypeExpCSParserRuleCall_0_1_1_1_0());

                            }
                            pushFollow(FollowSets000.FOLLOW_61);
                            lv_ownedType_7_0=ruleTypeExpCS();

                            state._fsp--;
                            if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              								if (current==null) {
                              									current = createModelElementForParent(grammarAccess.getNavigatingArgCSRule());
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

                            // InternalCompleteOCL.g:5607:6: ( (otherlv_8= 'with' | otherlv_9= '<-' ) ( (lv_ownedCoIterator_10_0= ruleCoIteratorVariableCS ) ) )?
                            int alt105=2;
                            int LA105_0 = input.LA(1);

                            if ( ((LA105_0>=86 && LA105_0<=87)) ) {
                                alt105=1;
                            }
                            switch (alt105) {
                                case 1 :
                                    // InternalCompleteOCL.g:5608:7: (otherlv_8= 'with' | otherlv_9= '<-' ) ( (lv_ownedCoIterator_10_0= ruleCoIteratorVariableCS ) )
                                    {
                                    // InternalCompleteOCL.g:5608:7: (otherlv_8= 'with' | otherlv_9= '<-' )
                                    int alt104=2;
                                    int LA104_0 = input.LA(1);

                                    if ( (LA104_0==86) ) {
                                        alt104=1;
                                    }
                                    else if ( (LA104_0==87) ) {
                                        alt104=2;
                                    }
                                    else {
                                        if (state.backtracking>0) {state.failed=true; return current;}
                                        NoViableAltException nvae =
                                            new NoViableAltException("", 104, 0, input);

                                        throw nvae;
                                    }
                                    switch (alt104) {
                                        case 1 :
                                            // InternalCompleteOCL.g:5609:8: otherlv_8= 'with'
                                            {
                                            otherlv_8=(Token)match(input,86,FollowSets000.FOLLOW_15); if (state.failed) return current;
                                            if ( state.backtracking==0 ) {

                                              								newLeafNode(otherlv_8, grammarAccess.getNavigatingArgCSAccess().getWithKeyword_0_1_1_2_0_0());

                                            }

                                            }
                                            break;
                                        case 2 :
                                            // InternalCompleteOCL.g:5614:8: otherlv_9= '<-'
                                            {
                                            otherlv_9=(Token)match(input,87,FollowSets000.FOLLOW_15); if (state.failed) return current;
                                            if ( state.backtracking==0 ) {

                                              								newLeafNode(otherlv_9, grammarAccess.getNavigatingArgCSAccess().getLessThanSignHyphenMinusKeyword_0_1_1_2_0_1());

                                            }

                                            }
                                            break;

                                    }

                                    // InternalCompleteOCL.g:5619:7: ( (lv_ownedCoIterator_10_0= ruleCoIteratorVariableCS ) )
                                    // InternalCompleteOCL.g:5620:8: (lv_ownedCoIterator_10_0= ruleCoIteratorVariableCS )
                                    {
                                    // InternalCompleteOCL.g:5620:8: (lv_ownedCoIterator_10_0= ruleCoIteratorVariableCS )
                                    // InternalCompleteOCL.g:5621:9: lv_ownedCoIterator_10_0= ruleCoIteratorVariableCS
                                    {
                                    if ( state.backtracking==0 ) {

                                      									newCompositeNode(grammarAccess.getNavigatingArgCSAccess().getOwnedCoIteratorCoIteratorVariableCSParserRuleCall_0_1_1_2_1_0());

                                    }
                                    pushFollow(FollowSets000.FOLLOW_60);
                                    lv_ownedCoIterator_10_0=ruleCoIteratorVariableCS();

                                    state._fsp--;
                                    if (state.failed) return current;
                                    if ( state.backtracking==0 ) {

                                      									if (current==null) {
                                      										current = createModelElementForParent(grammarAccess.getNavigatingArgCSRule());
                                      									}
                                      									set(
                                      										current,
                                      										"ownedCoIterator",
                                      										lv_ownedCoIterator_10_0,
                                      										"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.CoIteratorVariableCS");
                                      									afterParserOrEnumRuleCall();

                                    }

                                    }


                                    }


                                    }
                                    break;

                            }

                            // InternalCompleteOCL.g:5639:6: (otherlv_11= '=' ( (lv_ownedInitExpression_12_0= ruleExpCS ) ) )?
                            int alt106=2;
                            int LA106_0 = input.LA(1);

                            if ( (LA106_0==29) ) {
                                alt106=1;
                            }
                            switch (alt106) {
                                case 1 :
                                    // InternalCompleteOCL.g:5640:7: otherlv_11= '=' ( (lv_ownedInitExpression_12_0= ruleExpCS ) )
                                    {
                                    otherlv_11=(Token)match(input,29,FollowSets000.FOLLOW_30); if (state.failed) return current;
                                    if ( state.backtracking==0 ) {

                                      							newLeafNode(otherlv_11, grammarAccess.getNavigatingArgCSAccess().getEqualsSignKeyword_0_1_1_3_0());

                                    }
                                    // InternalCompleteOCL.g:5644:7: ( (lv_ownedInitExpression_12_0= ruleExpCS ) )
                                    // InternalCompleteOCL.g:5645:8: (lv_ownedInitExpression_12_0= ruleExpCS )
                                    {
                                    // InternalCompleteOCL.g:5645:8: (lv_ownedInitExpression_12_0= ruleExpCS )
                                    // InternalCompleteOCL.g:5646:9: lv_ownedInitExpression_12_0= ruleExpCS
                                    {
                                    if ( state.backtracking==0 ) {

                                      									newCompositeNode(grammarAccess.getNavigatingArgCSAccess().getOwnedInitExpressionExpCSParserRuleCall_0_1_1_3_1_0());

                                    }
                                    pushFollow(FollowSets000.FOLLOW_2);
                                    lv_ownedInitExpression_12_0=ruleExpCS();

                                    state._fsp--;
                                    if (state.failed) return current;
                                    if ( state.backtracking==0 ) {

                                      									if (current==null) {
                                      										current = createModelElementForParent(grammarAccess.getNavigatingArgCSRule());
                                      									}
                                      									set(
                                      										current,
                                      										"ownedInitExpression",
                                      										lv_ownedInitExpression_12_0,
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
                            // InternalCompleteOCL.g:5666:5: ( (otherlv_13= ':' ( (lv_ownedType_14_0= ruleTypeExpCS ) ) )? ( (otherlv_15= 'with' | otherlv_16= '<-' ) ( (lv_ownedCoIterator_17_0= ruleCoIteratorVariableCS ) ) )? otherlv_18= 'in' ( (lv_ownedInitExpression_19_0= ruleExpCS ) ) )
                            {
                            // InternalCompleteOCL.g:5666:5: ( (otherlv_13= ':' ( (lv_ownedType_14_0= ruleTypeExpCS ) ) )? ( (otherlv_15= 'with' | otherlv_16= '<-' ) ( (lv_ownedCoIterator_17_0= ruleCoIteratorVariableCS ) ) )? otherlv_18= 'in' ( (lv_ownedInitExpression_19_0= ruleExpCS ) ) )
                            // InternalCompleteOCL.g:5667:6: (otherlv_13= ':' ( (lv_ownedType_14_0= ruleTypeExpCS ) ) )? ( (otherlv_15= 'with' | otherlv_16= '<-' ) ( (lv_ownedCoIterator_17_0= ruleCoIteratorVariableCS ) ) )? otherlv_18= 'in' ( (lv_ownedInitExpression_19_0= ruleExpCS ) )
                            {
                            // InternalCompleteOCL.g:5667:6: (otherlv_13= ':' ( (lv_ownedType_14_0= ruleTypeExpCS ) ) )?
                            int alt107=2;
                            int LA107_0 = input.LA(1);

                            if ( (LA107_0==23) ) {
                                alt107=1;
                            }
                            switch (alt107) {
                                case 1 :
                                    // InternalCompleteOCL.g:5668:7: otherlv_13= ':' ( (lv_ownedType_14_0= ruleTypeExpCS ) )
                                    {
                                    otherlv_13=(Token)match(input,23,FollowSets000.FOLLOW_21); if (state.failed) return current;
                                    if ( state.backtracking==0 ) {

                                      							newLeafNode(otherlv_13, grammarAccess.getNavigatingArgCSAccess().getColonKeyword_0_1_2_0_0());

                                    }
                                    // InternalCompleteOCL.g:5672:7: ( (lv_ownedType_14_0= ruleTypeExpCS ) )
                                    // InternalCompleteOCL.g:5673:8: (lv_ownedType_14_0= ruleTypeExpCS )
                                    {
                                    // InternalCompleteOCL.g:5673:8: (lv_ownedType_14_0= ruleTypeExpCS )
                                    // InternalCompleteOCL.g:5674:9: lv_ownedType_14_0= ruleTypeExpCS
                                    {
                                    if ( state.backtracking==0 ) {

                                      									newCompositeNode(grammarAccess.getNavigatingArgCSAccess().getOwnedTypeTypeExpCSParserRuleCall_0_1_2_0_1_0());

                                    }
                                    pushFollow(FollowSets000.FOLLOW_62);
                                    lv_ownedType_14_0=ruleTypeExpCS();

                                    state._fsp--;
                                    if (state.failed) return current;
                                    if ( state.backtracking==0 ) {

                                      									if (current==null) {
                                      										current = createModelElementForParent(grammarAccess.getNavigatingArgCSRule());
                                      									}
                                      									set(
                                      										current,
                                      										"ownedType",
                                      										lv_ownedType_14_0,
                                      										"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.TypeExpCS");
                                      									afterParserOrEnumRuleCall();

                                    }

                                    }


                                    }


                                    }
                                    break;

                            }

                            // InternalCompleteOCL.g:5692:6: ( (otherlv_15= 'with' | otherlv_16= '<-' ) ( (lv_ownedCoIterator_17_0= ruleCoIteratorVariableCS ) ) )?
                            int alt109=2;
                            int LA109_0 = input.LA(1);

                            if ( ((LA109_0>=86 && LA109_0<=87)) ) {
                                alt109=1;
                            }
                            switch (alt109) {
                                case 1 :
                                    // InternalCompleteOCL.g:5693:7: (otherlv_15= 'with' | otherlv_16= '<-' ) ( (lv_ownedCoIterator_17_0= ruleCoIteratorVariableCS ) )
                                    {
                                    // InternalCompleteOCL.g:5693:7: (otherlv_15= 'with' | otherlv_16= '<-' )
                                    int alt108=2;
                                    int LA108_0 = input.LA(1);

                                    if ( (LA108_0==86) ) {
                                        alt108=1;
                                    }
                                    else if ( (LA108_0==87) ) {
                                        alt108=2;
                                    }
                                    else {
                                        if (state.backtracking>0) {state.failed=true; return current;}
                                        NoViableAltException nvae =
                                            new NoViableAltException("", 108, 0, input);

                                        throw nvae;
                                    }
                                    switch (alt108) {
                                        case 1 :
                                            // InternalCompleteOCL.g:5694:8: otherlv_15= 'with'
                                            {
                                            otherlv_15=(Token)match(input,86,FollowSets000.FOLLOW_15); if (state.failed) return current;
                                            if ( state.backtracking==0 ) {

                                              								newLeafNode(otherlv_15, grammarAccess.getNavigatingArgCSAccess().getWithKeyword_0_1_2_1_0_0());

                                            }

                                            }
                                            break;
                                        case 2 :
                                            // InternalCompleteOCL.g:5699:8: otherlv_16= '<-'
                                            {
                                            otherlv_16=(Token)match(input,87,FollowSets000.FOLLOW_15); if (state.failed) return current;
                                            if ( state.backtracking==0 ) {

                                              								newLeafNode(otherlv_16, grammarAccess.getNavigatingArgCSAccess().getLessThanSignHyphenMinusKeyword_0_1_2_1_0_1());

                                            }

                                            }
                                            break;

                                    }

                                    // InternalCompleteOCL.g:5704:7: ( (lv_ownedCoIterator_17_0= ruleCoIteratorVariableCS ) )
                                    // InternalCompleteOCL.g:5705:8: (lv_ownedCoIterator_17_0= ruleCoIteratorVariableCS )
                                    {
                                    // InternalCompleteOCL.g:5705:8: (lv_ownedCoIterator_17_0= ruleCoIteratorVariableCS )
                                    // InternalCompleteOCL.g:5706:9: lv_ownedCoIterator_17_0= ruleCoIteratorVariableCS
                                    {
                                    if ( state.backtracking==0 ) {

                                      									newCompositeNode(grammarAccess.getNavigatingArgCSAccess().getOwnedCoIteratorCoIteratorVariableCSParserRuleCall_0_1_2_1_1_0());

                                    }
                                    pushFollow(FollowSets000.FOLLOW_63);
                                    lv_ownedCoIterator_17_0=ruleCoIteratorVariableCS();

                                    state._fsp--;
                                    if (state.failed) return current;
                                    if ( state.backtracking==0 ) {

                                      									if (current==null) {
                                      										current = createModelElementForParent(grammarAccess.getNavigatingArgCSRule());
                                      									}
                                      									set(
                                      										current,
                                      										"ownedCoIterator",
                                      										lv_ownedCoIterator_17_0,
                                      										"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.CoIteratorVariableCS");
                                      									afterParserOrEnumRuleCall();

                                    }

                                    }


                                    }


                                    }
                                    break;

                            }

                            otherlv_18=(Token)match(input,95,FollowSets000.FOLLOW_30); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              						newLeafNode(otherlv_18, grammarAccess.getNavigatingArgCSAccess().getInKeyword_0_1_2_2());

                            }
                            // InternalCompleteOCL.g:5728:6: ( (lv_ownedInitExpression_19_0= ruleExpCS ) )
                            // InternalCompleteOCL.g:5729:7: (lv_ownedInitExpression_19_0= ruleExpCS )
                            {
                            // InternalCompleteOCL.g:5729:7: (lv_ownedInitExpression_19_0= ruleExpCS )
                            // InternalCompleteOCL.g:5730:8: lv_ownedInitExpression_19_0= ruleExpCS
                            {
                            if ( state.backtracking==0 ) {

                              								newCompositeNode(grammarAccess.getNavigatingArgCSAccess().getOwnedInitExpressionExpCSParserRuleCall_0_1_2_3_0());

                            }
                            pushFollow(FollowSets000.FOLLOW_2);
                            lv_ownedInitExpression_19_0=ruleExpCS();

                            state._fsp--;
                            if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              								if (current==null) {
                              									current = createModelElementForParent(grammarAccess.getNavigatingArgCSRule());
                              								}
                              								set(
                              									current,
                              									"ownedInitExpression",
                              									lv_ownedInitExpression_19_0,
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
                    // InternalCompleteOCL.g:5751:3: (otherlv_20= ':' ( (lv_ownedType_21_0= ruleTypeExpCS ) ) )
                    {
                    // InternalCompleteOCL.g:5751:3: (otherlv_20= ':' ( (lv_ownedType_21_0= ruleTypeExpCS ) ) )
                    // InternalCompleteOCL.g:5752:4: otherlv_20= ':' ( (lv_ownedType_21_0= ruleTypeExpCS ) )
                    {
                    otherlv_20=(Token)match(input,23,FollowSets000.FOLLOW_21); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_20, grammarAccess.getNavigatingArgCSAccess().getColonKeyword_1_0());

                    }
                    // InternalCompleteOCL.g:5756:4: ( (lv_ownedType_21_0= ruleTypeExpCS ) )
                    // InternalCompleteOCL.g:5757:5: (lv_ownedType_21_0= ruleTypeExpCS )
                    {
                    // InternalCompleteOCL.g:5757:5: (lv_ownedType_21_0= ruleTypeExpCS )
                    // InternalCompleteOCL.g:5758:6: lv_ownedType_21_0= ruleTypeExpCS
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getNavigatingArgCSAccess().getOwnedTypeTypeExpCSParserRuleCall_1_1_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    lv_ownedType_21_0=ruleTypeExpCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElementForParent(grammarAccess.getNavigatingArgCSRule());
                      						}
                      						set(
                      							current,
                      							"ownedType",
                      							lv_ownedType_21_0,
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
    // InternalCompleteOCL.g:5780:1: entryRuleNavigatingBarArgCS returns [EObject current=null] : iv_ruleNavigatingBarArgCS= ruleNavigatingBarArgCS EOF ;
    public final EObject entryRuleNavigatingBarArgCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNavigatingBarArgCS = null;


        try {
            // InternalCompleteOCL.g:5780:59: (iv_ruleNavigatingBarArgCS= ruleNavigatingBarArgCS EOF )
            // InternalCompleteOCL.g:5781:2: iv_ruleNavigatingBarArgCS= ruleNavigatingBarArgCS EOF
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
    // InternalCompleteOCL.g:5787:1: ruleNavigatingBarArgCS returns [EObject current=null] : ( ( (lv_prefix_0_0= '|' ) ) ( (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS ) ) (otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? )? ) ;
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
            // InternalCompleteOCL.g:5793:2: ( ( ( (lv_prefix_0_0= '|' ) ) ( (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS ) ) (otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? )? ) )
            // InternalCompleteOCL.g:5794:2: ( ( (lv_prefix_0_0= '|' ) ) ( (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS ) ) (otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? )? )
            {
            // InternalCompleteOCL.g:5794:2: ( ( (lv_prefix_0_0= '|' ) ) ( (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS ) ) (otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? )? )
            // InternalCompleteOCL.g:5795:3: ( (lv_prefix_0_0= '|' ) ) ( (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS ) ) (otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? )?
            {
            // InternalCompleteOCL.g:5795:3: ( (lv_prefix_0_0= '|' ) )
            // InternalCompleteOCL.g:5796:4: (lv_prefix_0_0= '|' )
            {
            // InternalCompleteOCL.g:5796:4: (lv_prefix_0_0= '|' )
            // InternalCompleteOCL.g:5797:5: lv_prefix_0_0= '|'
            {
            lv_prefix_0_0=(Token)match(input,96,FollowSets000.FOLLOW_64); if (state.failed) return current;
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

            // InternalCompleteOCL.g:5809:3: ( (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS ) )
            // InternalCompleteOCL.g:5810:4: (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS )
            {
            // InternalCompleteOCL.g:5810:4: (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS )
            // InternalCompleteOCL.g:5811:5: lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getNavigatingBarArgCSAccess().getOwnedNameExpressionNavigatingArgExpCSParserRuleCall_1_0());

            }
            pushFollow(FollowSets000.FOLLOW_65);
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

            // InternalCompleteOCL.g:5828:3: (otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? )?
            int alt113=2;
            int LA113_0 = input.LA(1);

            if ( (LA113_0==23) ) {
                alt113=1;
            }
            switch (alt113) {
                case 1 :
                    // InternalCompleteOCL.g:5829:4: otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )?
                    {
                    otherlv_2=(Token)match(input,23,FollowSets000.FOLLOW_21); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_2, grammarAccess.getNavigatingBarArgCSAccess().getColonKeyword_2_0());

                    }
                    // InternalCompleteOCL.g:5833:4: ( (lv_ownedType_3_0= ruleTypeExpCS ) )
                    // InternalCompleteOCL.g:5834:5: (lv_ownedType_3_0= ruleTypeExpCS )
                    {
                    // InternalCompleteOCL.g:5834:5: (lv_ownedType_3_0= ruleTypeExpCS )
                    // InternalCompleteOCL.g:5835:6: lv_ownedType_3_0= ruleTypeExpCS
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getNavigatingBarArgCSAccess().getOwnedTypeTypeExpCSParserRuleCall_2_1_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_60);
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

                    // InternalCompleteOCL.g:5852:4: (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )?
                    int alt112=2;
                    int LA112_0 = input.LA(1);

                    if ( (LA112_0==29) ) {
                        alt112=1;
                    }
                    switch (alt112) {
                        case 1 :
                            // InternalCompleteOCL.g:5853:5: otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) )
                            {
                            otherlv_4=(Token)match(input,29,FollowSets000.FOLLOW_30); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              					newLeafNode(otherlv_4, grammarAccess.getNavigatingBarArgCSAccess().getEqualsSignKeyword_2_2_0());

                            }
                            // InternalCompleteOCL.g:5857:5: ( (lv_ownedInitExpression_5_0= ruleExpCS ) )
                            // InternalCompleteOCL.g:5858:6: (lv_ownedInitExpression_5_0= ruleExpCS )
                            {
                            // InternalCompleteOCL.g:5858:6: (lv_ownedInitExpression_5_0= ruleExpCS )
                            // InternalCompleteOCL.g:5859:7: lv_ownedInitExpression_5_0= ruleExpCS
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
    // InternalCompleteOCL.g:5882:1: entryRuleNavigatingCommaArgCS returns [EObject current=null] : iv_ruleNavigatingCommaArgCS= ruleNavigatingCommaArgCS EOF ;
    public final EObject entryRuleNavigatingCommaArgCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNavigatingCommaArgCS = null;


        try {
            // InternalCompleteOCL.g:5882:61: (iv_ruleNavigatingCommaArgCS= ruleNavigatingCommaArgCS EOF )
            // InternalCompleteOCL.g:5883:2: iv_ruleNavigatingCommaArgCS= ruleNavigatingCommaArgCS EOF
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
    // InternalCompleteOCL.g:5889:1: ruleNavigatingCommaArgCS returns [EObject current=null] : ( ( (lv_prefix_0_0= ',' ) ) ( (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS ) ) ( ( (otherlv_2= 'with' | otherlv_3= '<-' ) ( (lv_ownedCoIterator_4_0= ruleCoIteratorVariableCS ) ) (otherlv_5= '=' ( (lv_ownedInitExpression_6_0= ruleExpCS ) ) )? ) | (otherlv_7= ':' ( (lv_ownedType_8_0= ruleTypeExpCS ) ) ( (otherlv_9= 'with' | otherlv_10= '<-' ) ( (lv_ownedCoIterator_11_0= ruleCoIteratorVariableCS ) ) )? (otherlv_12= '=' ( (lv_ownedInitExpression_13_0= ruleExpCS ) ) )? ) | ( (otherlv_14= ':' ( (lv_ownedType_15_0= ruleTypeExpCS ) ) )? ( (otherlv_16= 'with' | otherlv_17= '<-' ) ( (lv_ownedCoIterator_18_0= ruleCoIteratorVariableCS ) ) )? otherlv_19= 'in' ( (lv_ownedInitExpression_20_0= ruleExpCS ) ) ) )? ) ;
    public final EObject ruleNavigatingCommaArgCS() throws RecognitionException {
        EObject current = null;

        Token lv_prefix_0_0=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_9=null;
        Token otherlv_10=null;
        Token otherlv_12=null;
        Token otherlv_14=null;
        Token otherlv_16=null;
        Token otherlv_17=null;
        Token otherlv_19=null;
        EObject lv_ownedNameExpression_1_0 = null;

        EObject lv_ownedCoIterator_4_0 = null;

        EObject lv_ownedInitExpression_6_0 = null;

        EObject lv_ownedType_8_0 = null;

        EObject lv_ownedCoIterator_11_0 = null;

        EObject lv_ownedInitExpression_13_0 = null;

        EObject lv_ownedType_15_0 = null;

        EObject lv_ownedCoIterator_18_0 = null;

        EObject lv_ownedInitExpression_20_0 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:5895:2: ( ( ( (lv_prefix_0_0= ',' ) ) ( (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS ) ) ( ( (otherlv_2= 'with' | otherlv_3= '<-' ) ( (lv_ownedCoIterator_4_0= ruleCoIteratorVariableCS ) ) (otherlv_5= '=' ( (lv_ownedInitExpression_6_0= ruleExpCS ) ) )? ) | (otherlv_7= ':' ( (lv_ownedType_8_0= ruleTypeExpCS ) ) ( (otherlv_9= 'with' | otherlv_10= '<-' ) ( (lv_ownedCoIterator_11_0= ruleCoIteratorVariableCS ) ) )? (otherlv_12= '=' ( (lv_ownedInitExpression_13_0= ruleExpCS ) ) )? ) | ( (otherlv_14= ':' ( (lv_ownedType_15_0= ruleTypeExpCS ) ) )? ( (otherlv_16= 'with' | otherlv_17= '<-' ) ( (lv_ownedCoIterator_18_0= ruleCoIteratorVariableCS ) ) )? otherlv_19= 'in' ( (lv_ownedInitExpression_20_0= ruleExpCS ) ) ) )? ) )
            // InternalCompleteOCL.g:5896:2: ( ( (lv_prefix_0_0= ',' ) ) ( (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS ) ) ( ( (otherlv_2= 'with' | otherlv_3= '<-' ) ( (lv_ownedCoIterator_4_0= ruleCoIteratorVariableCS ) ) (otherlv_5= '=' ( (lv_ownedInitExpression_6_0= ruleExpCS ) ) )? ) | (otherlv_7= ':' ( (lv_ownedType_8_0= ruleTypeExpCS ) ) ( (otherlv_9= 'with' | otherlv_10= '<-' ) ( (lv_ownedCoIterator_11_0= ruleCoIteratorVariableCS ) ) )? (otherlv_12= '=' ( (lv_ownedInitExpression_13_0= ruleExpCS ) ) )? ) | ( (otherlv_14= ':' ( (lv_ownedType_15_0= ruleTypeExpCS ) ) )? ( (otherlv_16= 'with' | otherlv_17= '<-' ) ( (lv_ownedCoIterator_18_0= ruleCoIteratorVariableCS ) ) )? otherlv_19= 'in' ( (lv_ownedInitExpression_20_0= ruleExpCS ) ) ) )? )
            {
            // InternalCompleteOCL.g:5896:2: ( ( (lv_prefix_0_0= ',' ) ) ( (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS ) ) ( ( (otherlv_2= 'with' | otherlv_3= '<-' ) ( (lv_ownedCoIterator_4_0= ruleCoIteratorVariableCS ) ) (otherlv_5= '=' ( (lv_ownedInitExpression_6_0= ruleExpCS ) ) )? ) | (otherlv_7= ':' ( (lv_ownedType_8_0= ruleTypeExpCS ) ) ( (otherlv_9= 'with' | otherlv_10= '<-' ) ( (lv_ownedCoIterator_11_0= ruleCoIteratorVariableCS ) ) )? (otherlv_12= '=' ( (lv_ownedInitExpression_13_0= ruleExpCS ) ) )? ) | ( (otherlv_14= ':' ( (lv_ownedType_15_0= ruleTypeExpCS ) ) )? ( (otherlv_16= 'with' | otherlv_17= '<-' ) ( (lv_ownedCoIterator_18_0= ruleCoIteratorVariableCS ) ) )? otherlv_19= 'in' ( (lv_ownedInitExpression_20_0= ruleExpCS ) ) ) )? )
            // InternalCompleteOCL.g:5897:3: ( (lv_prefix_0_0= ',' ) ) ( (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS ) ) ( ( (otherlv_2= 'with' | otherlv_3= '<-' ) ( (lv_ownedCoIterator_4_0= ruleCoIteratorVariableCS ) ) (otherlv_5= '=' ( (lv_ownedInitExpression_6_0= ruleExpCS ) ) )? ) | (otherlv_7= ':' ( (lv_ownedType_8_0= ruleTypeExpCS ) ) ( (otherlv_9= 'with' | otherlv_10= '<-' ) ( (lv_ownedCoIterator_11_0= ruleCoIteratorVariableCS ) ) )? (otherlv_12= '=' ( (lv_ownedInitExpression_13_0= ruleExpCS ) ) )? ) | ( (otherlv_14= ':' ( (lv_ownedType_15_0= ruleTypeExpCS ) ) )? ( (otherlv_16= 'with' | otherlv_17= '<-' ) ( (lv_ownedCoIterator_18_0= ruleCoIteratorVariableCS ) ) )? otherlv_19= 'in' ( (lv_ownedInitExpression_20_0= ruleExpCS ) ) ) )?
            {
            // InternalCompleteOCL.g:5897:3: ( (lv_prefix_0_0= ',' ) )
            // InternalCompleteOCL.g:5898:4: (lv_prefix_0_0= ',' )
            {
            // InternalCompleteOCL.g:5898:4: (lv_prefix_0_0= ',' )
            // InternalCompleteOCL.g:5899:5: lv_prefix_0_0= ','
            {
            lv_prefix_0_0=(Token)match(input,28,FollowSets000.FOLLOW_64); if (state.failed) return current;
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

            // InternalCompleteOCL.g:5911:3: ( (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS ) )
            // InternalCompleteOCL.g:5912:4: (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS )
            {
            // InternalCompleteOCL.g:5912:4: (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS )
            // InternalCompleteOCL.g:5913:5: lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getNavigatingCommaArgCSAccess().getOwnedNameExpressionNavigatingArgExpCSParserRuleCall_1_0());

            }
            pushFollow(FollowSets000.FOLLOW_59);
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

            // InternalCompleteOCL.g:5930:3: ( ( (otherlv_2= 'with' | otherlv_3= '<-' ) ( (lv_ownedCoIterator_4_0= ruleCoIteratorVariableCS ) ) (otherlv_5= '=' ( (lv_ownedInitExpression_6_0= ruleExpCS ) ) )? ) | (otherlv_7= ':' ( (lv_ownedType_8_0= ruleTypeExpCS ) ) ( (otherlv_9= 'with' | otherlv_10= '<-' ) ( (lv_ownedCoIterator_11_0= ruleCoIteratorVariableCS ) ) )? (otherlv_12= '=' ( (lv_ownedInitExpression_13_0= ruleExpCS ) ) )? ) | ( (otherlv_14= ':' ( (lv_ownedType_15_0= ruleTypeExpCS ) ) )? ( (otherlv_16= 'with' | otherlv_17= '<-' ) ( (lv_ownedCoIterator_18_0= ruleCoIteratorVariableCS ) ) )? otherlv_19= 'in' ( (lv_ownedInitExpression_20_0= ruleExpCS ) ) ) )?
            int alt122=4;
            alt122 = dfa122.predict(input);
            switch (alt122) {
                case 1 :
                    // InternalCompleteOCL.g:5931:4: ( (otherlv_2= 'with' | otherlv_3= '<-' ) ( (lv_ownedCoIterator_4_0= ruleCoIteratorVariableCS ) ) (otherlv_5= '=' ( (lv_ownedInitExpression_6_0= ruleExpCS ) ) )? )
                    {
                    // InternalCompleteOCL.g:5931:4: ( (otherlv_2= 'with' | otherlv_3= '<-' ) ( (lv_ownedCoIterator_4_0= ruleCoIteratorVariableCS ) ) (otherlv_5= '=' ( (lv_ownedInitExpression_6_0= ruleExpCS ) ) )? )
                    // InternalCompleteOCL.g:5932:5: (otherlv_2= 'with' | otherlv_3= '<-' ) ( (lv_ownedCoIterator_4_0= ruleCoIteratorVariableCS ) ) (otherlv_5= '=' ( (lv_ownedInitExpression_6_0= ruleExpCS ) ) )?
                    {
                    // InternalCompleteOCL.g:5932:5: (otherlv_2= 'with' | otherlv_3= '<-' )
                    int alt114=2;
                    int LA114_0 = input.LA(1);

                    if ( (LA114_0==86) ) {
                        alt114=1;
                    }
                    else if ( (LA114_0==87) ) {
                        alt114=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return current;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 114, 0, input);

                        throw nvae;
                    }
                    switch (alt114) {
                        case 1 :
                            // InternalCompleteOCL.g:5933:6: otherlv_2= 'with'
                            {
                            otherlv_2=(Token)match(input,86,FollowSets000.FOLLOW_15); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              						newLeafNode(otherlv_2, grammarAccess.getNavigatingCommaArgCSAccess().getWithKeyword_2_0_0_0());

                            }

                            }
                            break;
                        case 2 :
                            // InternalCompleteOCL.g:5938:6: otherlv_3= '<-'
                            {
                            otherlv_3=(Token)match(input,87,FollowSets000.FOLLOW_15); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              						newLeafNode(otherlv_3, grammarAccess.getNavigatingCommaArgCSAccess().getLessThanSignHyphenMinusKeyword_2_0_0_1());

                            }

                            }
                            break;

                    }

                    // InternalCompleteOCL.g:5943:5: ( (lv_ownedCoIterator_4_0= ruleCoIteratorVariableCS ) )
                    // InternalCompleteOCL.g:5944:6: (lv_ownedCoIterator_4_0= ruleCoIteratorVariableCS )
                    {
                    // InternalCompleteOCL.g:5944:6: (lv_ownedCoIterator_4_0= ruleCoIteratorVariableCS )
                    // InternalCompleteOCL.g:5945:7: lv_ownedCoIterator_4_0= ruleCoIteratorVariableCS
                    {
                    if ( state.backtracking==0 ) {

                      							newCompositeNode(grammarAccess.getNavigatingCommaArgCSAccess().getOwnedCoIteratorCoIteratorVariableCSParserRuleCall_2_0_1_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_60);
                    lv_ownedCoIterator_4_0=ruleCoIteratorVariableCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      							if (current==null) {
                      								current = createModelElementForParent(grammarAccess.getNavigatingCommaArgCSRule());
                      							}
                      							set(
                      								current,
                      								"ownedCoIterator",
                      								lv_ownedCoIterator_4_0,
                      								"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.CoIteratorVariableCS");
                      							afterParserOrEnumRuleCall();

                    }

                    }


                    }

                    // InternalCompleteOCL.g:5962:5: (otherlv_5= '=' ( (lv_ownedInitExpression_6_0= ruleExpCS ) ) )?
                    int alt115=2;
                    int LA115_0 = input.LA(1);

                    if ( (LA115_0==29) ) {
                        alt115=1;
                    }
                    switch (alt115) {
                        case 1 :
                            // InternalCompleteOCL.g:5963:6: otherlv_5= '=' ( (lv_ownedInitExpression_6_0= ruleExpCS ) )
                            {
                            otherlv_5=(Token)match(input,29,FollowSets000.FOLLOW_30); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              						newLeafNode(otherlv_5, grammarAccess.getNavigatingCommaArgCSAccess().getEqualsSignKeyword_2_0_2_0());

                            }
                            // InternalCompleteOCL.g:5967:6: ( (lv_ownedInitExpression_6_0= ruleExpCS ) )
                            // InternalCompleteOCL.g:5968:7: (lv_ownedInitExpression_6_0= ruleExpCS )
                            {
                            // InternalCompleteOCL.g:5968:7: (lv_ownedInitExpression_6_0= ruleExpCS )
                            // InternalCompleteOCL.g:5969:8: lv_ownedInitExpression_6_0= ruleExpCS
                            {
                            if ( state.backtracking==0 ) {

                              								newCompositeNode(grammarAccess.getNavigatingCommaArgCSAccess().getOwnedInitExpressionExpCSParserRuleCall_2_0_2_1_0());

                            }
                            pushFollow(FollowSets000.FOLLOW_2);
                            lv_ownedInitExpression_6_0=ruleExpCS();

                            state._fsp--;
                            if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              								if (current==null) {
                              									current = createModelElementForParent(grammarAccess.getNavigatingCommaArgCSRule());
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
                            break;

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalCompleteOCL.g:5989:4: (otherlv_7= ':' ( (lv_ownedType_8_0= ruleTypeExpCS ) ) ( (otherlv_9= 'with' | otherlv_10= '<-' ) ( (lv_ownedCoIterator_11_0= ruleCoIteratorVariableCS ) ) )? (otherlv_12= '=' ( (lv_ownedInitExpression_13_0= ruleExpCS ) ) )? )
                    {
                    // InternalCompleteOCL.g:5989:4: (otherlv_7= ':' ( (lv_ownedType_8_0= ruleTypeExpCS ) ) ( (otherlv_9= 'with' | otherlv_10= '<-' ) ( (lv_ownedCoIterator_11_0= ruleCoIteratorVariableCS ) ) )? (otherlv_12= '=' ( (lv_ownedInitExpression_13_0= ruleExpCS ) ) )? )
                    // InternalCompleteOCL.g:5990:5: otherlv_7= ':' ( (lv_ownedType_8_0= ruleTypeExpCS ) ) ( (otherlv_9= 'with' | otherlv_10= '<-' ) ( (lv_ownedCoIterator_11_0= ruleCoIteratorVariableCS ) ) )? (otherlv_12= '=' ( (lv_ownedInitExpression_13_0= ruleExpCS ) ) )?
                    {
                    otherlv_7=(Token)match(input,23,FollowSets000.FOLLOW_21); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					newLeafNode(otherlv_7, grammarAccess.getNavigatingCommaArgCSAccess().getColonKeyword_2_1_0());

                    }
                    // InternalCompleteOCL.g:5994:5: ( (lv_ownedType_8_0= ruleTypeExpCS ) )
                    // InternalCompleteOCL.g:5995:6: (lv_ownedType_8_0= ruleTypeExpCS )
                    {
                    // InternalCompleteOCL.g:5995:6: (lv_ownedType_8_0= ruleTypeExpCS )
                    // InternalCompleteOCL.g:5996:7: lv_ownedType_8_0= ruleTypeExpCS
                    {
                    if ( state.backtracking==0 ) {

                      							newCompositeNode(grammarAccess.getNavigatingCommaArgCSAccess().getOwnedTypeTypeExpCSParserRuleCall_2_1_1_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_61);
                    lv_ownedType_8_0=ruleTypeExpCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      							if (current==null) {
                      								current = createModelElementForParent(grammarAccess.getNavigatingCommaArgCSRule());
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

                    // InternalCompleteOCL.g:6013:5: ( (otherlv_9= 'with' | otherlv_10= '<-' ) ( (lv_ownedCoIterator_11_0= ruleCoIteratorVariableCS ) ) )?
                    int alt117=2;
                    int LA117_0 = input.LA(1);

                    if ( ((LA117_0>=86 && LA117_0<=87)) ) {
                        alt117=1;
                    }
                    switch (alt117) {
                        case 1 :
                            // InternalCompleteOCL.g:6014:6: (otherlv_9= 'with' | otherlv_10= '<-' ) ( (lv_ownedCoIterator_11_0= ruleCoIteratorVariableCS ) )
                            {
                            // InternalCompleteOCL.g:6014:6: (otherlv_9= 'with' | otherlv_10= '<-' )
                            int alt116=2;
                            int LA116_0 = input.LA(1);

                            if ( (LA116_0==86) ) {
                                alt116=1;
                            }
                            else if ( (LA116_0==87) ) {
                                alt116=2;
                            }
                            else {
                                if (state.backtracking>0) {state.failed=true; return current;}
                                NoViableAltException nvae =
                                    new NoViableAltException("", 116, 0, input);

                                throw nvae;
                            }
                            switch (alt116) {
                                case 1 :
                                    // InternalCompleteOCL.g:6015:7: otherlv_9= 'with'
                                    {
                                    otherlv_9=(Token)match(input,86,FollowSets000.FOLLOW_15); if (state.failed) return current;
                                    if ( state.backtracking==0 ) {

                                      							newLeafNode(otherlv_9, grammarAccess.getNavigatingCommaArgCSAccess().getWithKeyword_2_1_2_0_0());

                                    }

                                    }
                                    break;
                                case 2 :
                                    // InternalCompleteOCL.g:6020:7: otherlv_10= '<-'
                                    {
                                    otherlv_10=(Token)match(input,87,FollowSets000.FOLLOW_15); if (state.failed) return current;
                                    if ( state.backtracking==0 ) {

                                      							newLeafNode(otherlv_10, grammarAccess.getNavigatingCommaArgCSAccess().getLessThanSignHyphenMinusKeyword_2_1_2_0_1());

                                    }

                                    }
                                    break;

                            }

                            // InternalCompleteOCL.g:6025:6: ( (lv_ownedCoIterator_11_0= ruleCoIteratorVariableCS ) )
                            // InternalCompleteOCL.g:6026:7: (lv_ownedCoIterator_11_0= ruleCoIteratorVariableCS )
                            {
                            // InternalCompleteOCL.g:6026:7: (lv_ownedCoIterator_11_0= ruleCoIteratorVariableCS )
                            // InternalCompleteOCL.g:6027:8: lv_ownedCoIterator_11_0= ruleCoIteratorVariableCS
                            {
                            if ( state.backtracking==0 ) {

                              								newCompositeNode(grammarAccess.getNavigatingCommaArgCSAccess().getOwnedCoIteratorCoIteratorVariableCSParserRuleCall_2_1_2_1_0());

                            }
                            pushFollow(FollowSets000.FOLLOW_60);
                            lv_ownedCoIterator_11_0=ruleCoIteratorVariableCS();

                            state._fsp--;
                            if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              								if (current==null) {
                              									current = createModelElementForParent(grammarAccess.getNavigatingCommaArgCSRule());
                              								}
                              								set(
                              									current,
                              									"ownedCoIterator",
                              									lv_ownedCoIterator_11_0,
                              									"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.CoIteratorVariableCS");
                              								afterParserOrEnumRuleCall();

                            }

                            }


                            }


                            }
                            break;

                    }

                    // InternalCompleteOCL.g:6045:5: (otherlv_12= '=' ( (lv_ownedInitExpression_13_0= ruleExpCS ) ) )?
                    int alt118=2;
                    int LA118_0 = input.LA(1);

                    if ( (LA118_0==29) ) {
                        alt118=1;
                    }
                    switch (alt118) {
                        case 1 :
                            // InternalCompleteOCL.g:6046:6: otherlv_12= '=' ( (lv_ownedInitExpression_13_0= ruleExpCS ) )
                            {
                            otherlv_12=(Token)match(input,29,FollowSets000.FOLLOW_30); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              						newLeafNode(otherlv_12, grammarAccess.getNavigatingCommaArgCSAccess().getEqualsSignKeyword_2_1_3_0());

                            }
                            // InternalCompleteOCL.g:6050:6: ( (lv_ownedInitExpression_13_0= ruleExpCS ) )
                            // InternalCompleteOCL.g:6051:7: (lv_ownedInitExpression_13_0= ruleExpCS )
                            {
                            // InternalCompleteOCL.g:6051:7: (lv_ownedInitExpression_13_0= ruleExpCS )
                            // InternalCompleteOCL.g:6052:8: lv_ownedInitExpression_13_0= ruleExpCS
                            {
                            if ( state.backtracking==0 ) {

                              								newCompositeNode(grammarAccess.getNavigatingCommaArgCSAccess().getOwnedInitExpressionExpCSParserRuleCall_2_1_3_1_0());

                            }
                            pushFollow(FollowSets000.FOLLOW_2);
                            lv_ownedInitExpression_13_0=ruleExpCS();

                            state._fsp--;
                            if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              								if (current==null) {
                              									current = createModelElementForParent(grammarAccess.getNavigatingCommaArgCSRule());
                              								}
                              								set(
                              									current,
                              									"ownedInitExpression",
                              									lv_ownedInitExpression_13_0,
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
                    // InternalCompleteOCL.g:6072:4: ( (otherlv_14= ':' ( (lv_ownedType_15_0= ruleTypeExpCS ) ) )? ( (otherlv_16= 'with' | otherlv_17= '<-' ) ( (lv_ownedCoIterator_18_0= ruleCoIteratorVariableCS ) ) )? otherlv_19= 'in' ( (lv_ownedInitExpression_20_0= ruleExpCS ) ) )
                    {
                    // InternalCompleteOCL.g:6072:4: ( (otherlv_14= ':' ( (lv_ownedType_15_0= ruleTypeExpCS ) ) )? ( (otherlv_16= 'with' | otherlv_17= '<-' ) ( (lv_ownedCoIterator_18_0= ruleCoIteratorVariableCS ) ) )? otherlv_19= 'in' ( (lv_ownedInitExpression_20_0= ruleExpCS ) ) )
                    // InternalCompleteOCL.g:6073:5: (otherlv_14= ':' ( (lv_ownedType_15_0= ruleTypeExpCS ) ) )? ( (otherlv_16= 'with' | otherlv_17= '<-' ) ( (lv_ownedCoIterator_18_0= ruleCoIteratorVariableCS ) ) )? otherlv_19= 'in' ( (lv_ownedInitExpression_20_0= ruleExpCS ) )
                    {
                    // InternalCompleteOCL.g:6073:5: (otherlv_14= ':' ( (lv_ownedType_15_0= ruleTypeExpCS ) ) )?
                    int alt119=2;
                    int LA119_0 = input.LA(1);

                    if ( (LA119_0==23) ) {
                        alt119=1;
                    }
                    switch (alt119) {
                        case 1 :
                            // InternalCompleteOCL.g:6074:6: otherlv_14= ':' ( (lv_ownedType_15_0= ruleTypeExpCS ) )
                            {
                            otherlv_14=(Token)match(input,23,FollowSets000.FOLLOW_21); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              						newLeafNode(otherlv_14, grammarAccess.getNavigatingCommaArgCSAccess().getColonKeyword_2_2_0_0());

                            }
                            // InternalCompleteOCL.g:6078:6: ( (lv_ownedType_15_0= ruleTypeExpCS ) )
                            // InternalCompleteOCL.g:6079:7: (lv_ownedType_15_0= ruleTypeExpCS )
                            {
                            // InternalCompleteOCL.g:6079:7: (lv_ownedType_15_0= ruleTypeExpCS )
                            // InternalCompleteOCL.g:6080:8: lv_ownedType_15_0= ruleTypeExpCS
                            {
                            if ( state.backtracking==0 ) {

                              								newCompositeNode(grammarAccess.getNavigatingCommaArgCSAccess().getOwnedTypeTypeExpCSParserRuleCall_2_2_0_1_0());

                            }
                            pushFollow(FollowSets000.FOLLOW_62);
                            lv_ownedType_15_0=ruleTypeExpCS();

                            state._fsp--;
                            if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              								if (current==null) {
                              									current = createModelElementForParent(grammarAccess.getNavigatingCommaArgCSRule());
                              								}
                              								set(
                              									current,
                              									"ownedType",
                              									lv_ownedType_15_0,
                              									"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.TypeExpCS");
                              								afterParserOrEnumRuleCall();

                            }

                            }


                            }


                            }
                            break;

                    }

                    // InternalCompleteOCL.g:6098:5: ( (otherlv_16= 'with' | otherlv_17= '<-' ) ( (lv_ownedCoIterator_18_0= ruleCoIteratorVariableCS ) ) )?
                    int alt121=2;
                    int LA121_0 = input.LA(1);

                    if ( ((LA121_0>=86 && LA121_0<=87)) ) {
                        alt121=1;
                    }
                    switch (alt121) {
                        case 1 :
                            // InternalCompleteOCL.g:6099:6: (otherlv_16= 'with' | otherlv_17= '<-' ) ( (lv_ownedCoIterator_18_0= ruleCoIteratorVariableCS ) )
                            {
                            // InternalCompleteOCL.g:6099:6: (otherlv_16= 'with' | otherlv_17= '<-' )
                            int alt120=2;
                            int LA120_0 = input.LA(1);

                            if ( (LA120_0==86) ) {
                                alt120=1;
                            }
                            else if ( (LA120_0==87) ) {
                                alt120=2;
                            }
                            else {
                                if (state.backtracking>0) {state.failed=true; return current;}
                                NoViableAltException nvae =
                                    new NoViableAltException("", 120, 0, input);

                                throw nvae;
                            }
                            switch (alt120) {
                                case 1 :
                                    // InternalCompleteOCL.g:6100:7: otherlv_16= 'with'
                                    {
                                    otherlv_16=(Token)match(input,86,FollowSets000.FOLLOW_15); if (state.failed) return current;
                                    if ( state.backtracking==0 ) {

                                      							newLeafNode(otherlv_16, grammarAccess.getNavigatingCommaArgCSAccess().getWithKeyword_2_2_1_0_0());

                                    }

                                    }
                                    break;
                                case 2 :
                                    // InternalCompleteOCL.g:6105:7: otherlv_17= '<-'
                                    {
                                    otherlv_17=(Token)match(input,87,FollowSets000.FOLLOW_15); if (state.failed) return current;
                                    if ( state.backtracking==0 ) {

                                      							newLeafNode(otherlv_17, grammarAccess.getNavigatingCommaArgCSAccess().getLessThanSignHyphenMinusKeyword_2_2_1_0_1());

                                    }

                                    }
                                    break;

                            }

                            // InternalCompleteOCL.g:6110:6: ( (lv_ownedCoIterator_18_0= ruleCoIteratorVariableCS ) )
                            // InternalCompleteOCL.g:6111:7: (lv_ownedCoIterator_18_0= ruleCoIteratorVariableCS )
                            {
                            // InternalCompleteOCL.g:6111:7: (lv_ownedCoIterator_18_0= ruleCoIteratorVariableCS )
                            // InternalCompleteOCL.g:6112:8: lv_ownedCoIterator_18_0= ruleCoIteratorVariableCS
                            {
                            if ( state.backtracking==0 ) {

                              								newCompositeNode(grammarAccess.getNavigatingCommaArgCSAccess().getOwnedCoIteratorCoIteratorVariableCSParserRuleCall_2_2_1_1_0());

                            }
                            pushFollow(FollowSets000.FOLLOW_63);
                            lv_ownedCoIterator_18_0=ruleCoIteratorVariableCS();

                            state._fsp--;
                            if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              								if (current==null) {
                              									current = createModelElementForParent(grammarAccess.getNavigatingCommaArgCSRule());
                              								}
                              								set(
                              									current,
                              									"ownedCoIterator",
                              									lv_ownedCoIterator_18_0,
                              									"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.CoIteratorVariableCS");
                              								afterParserOrEnumRuleCall();

                            }

                            }


                            }


                            }
                            break;

                    }

                    otherlv_19=(Token)match(input,95,FollowSets000.FOLLOW_30); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					newLeafNode(otherlv_19, grammarAccess.getNavigatingCommaArgCSAccess().getInKeyword_2_2_2());

                    }
                    // InternalCompleteOCL.g:6134:5: ( (lv_ownedInitExpression_20_0= ruleExpCS ) )
                    // InternalCompleteOCL.g:6135:6: (lv_ownedInitExpression_20_0= ruleExpCS )
                    {
                    // InternalCompleteOCL.g:6135:6: (lv_ownedInitExpression_20_0= ruleExpCS )
                    // InternalCompleteOCL.g:6136:7: lv_ownedInitExpression_20_0= ruleExpCS
                    {
                    if ( state.backtracking==0 ) {

                      							newCompositeNode(grammarAccess.getNavigatingCommaArgCSAccess().getOwnedInitExpressionExpCSParserRuleCall_2_2_3_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_2);
                    lv_ownedInitExpression_20_0=ruleExpCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      							if (current==null) {
                      								current = createModelElementForParent(grammarAccess.getNavigatingCommaArgCSRule());
                      							}
                      							set(
                      								current,
                      								"ownedInitExpression",
                      								lv_ownedInitExpression_20_0,
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
    // InternalCompleteOCL.g:6159:1: entryRuleNavigatingSemiArgCS returns [EObject current=null] : iv_ruleNavigatingSemiArgCS= ruleNavigatingSemiArgCS EOF ;
    public final EObject entryRuleNavigatingSemiArgCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNavigatingSemiArgCS = null;


        try {
            // InternalCompleteOCL.g:6159:60: (iv_ruleNavigatingSemiArgCS= ruleNavigatingSemiArgCS EOF )
            // InternalCompleteOCL.g:6160:2: iv_ruleNavigatingSemiArgCS= ruleNavigatingSemiArgCS EOF
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
    // InternalCompleteOCL.g:6166:1: ruleNavigatingSemiArgCS returns [EObject current=null] : ( ( (lv_prefix_0_0= ';' ) ) ( (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS ) ) (otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? )? ) ;
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
            // InternalCompleteOCL.g:6172:2: ( ( ( (lv_prefix_0_0= ';' ) ) ( (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS ) ) (otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? )? ) )
            // InternalCompleteOCL.g:6173:2: ( ( (lv_prefix_0_0= ';' ) ) ( (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS ) ) (otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? )? )
            {
            // InternalCompleteOCL.g:6173:2: ( ( (lv_prefix_0_0= ';' ) ) ( (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS ) ) (otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? )? )
            // InternalCompleteOCL.g:6174:3: ( (lv_prefix_0_0= ';' ) ) ( (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS ) ) (otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? )?
            {
            // InternalCompleteOCL.g:6174:3: ( (lv_prefix_0_0= ';' ) )
            // InternalCompleteOCL.g:6175:4: (lv_prefix_0_0= ';' )
            {
            // InternalCompleteOCL.g:6175:4: (lv_prefix_0_0= ';' )
            // InternalCompleteOCL.g:6176:5: lv_prefix_0_0= ';'
            {
            lv_prefix_0_0=(Token)match(input,97,FollowSets000.FOLLOW_64); if (state.failed) return current;
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

            // InternalCompleteOCL.g:6188:3: ( (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS ) )
            // InternalCompleteOCL.g:6189:4: (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS )
            {
            // InternalCompleteOCL.g:6189:4: (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS )
            // InternalCompleteOCL.g:6190:5: lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getNavigatingSemiArgCSAccess().getOwnedNameExpressionNavigatingArgExpCSParserRuleCall_1_0());

            }
            pushFollow(FollowSets000.FOLLOW_65);
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

            // InternalCompleteOCL.g:6207:3: (otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? )?
            int alt124=2;
            int LA124_0 = input.LA(1);

            if ( (LA124_0==23) ) {
                alt124=1;
            }
            switch (alt124) {
                case 1 :
                    // InternalCompleteOCL.g:6208:4: otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )?
                    {
                    otherlv_2=(Token)match(input,23,FollowSets000.FOLLOW_21); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_2, grammarAccess.getNavigatingSemiArgCSAccess().getColonKeyword_2_0());

                    }
                    // InternalCompleteOCL.g:6212:4: ( (lv_ownedType_3_0= ruleTypeExpCS ) )
                    // InternalCompleteOCL.g:6213:5: (lv_ownedType_3_0= ruleTypeExpCS )
                    {
                    // InternalCompleteOCL.g:6213:5: (lv_ownedType_3_0= ruleTypeExpCS )
                    // InternalCompleteOCL.g:6214:6: lv_ownedType_3_0= ruleTypeExpCS
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getNavigatingSemiArgCSAccess().getOwnedTypeTypeExpCSParserRuleCall_2_1_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_60);
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

                    // InternalCompleteOCL.g:6231:4: (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )?
                    int alt123=2;
                    int LA123_0 = input.LA(1);

                    if ( (LA123_0==29) ) {
                        alt123=1;
                    }
                    switch (alt123) {
                        case 1 :
                            // InternalCompleteOCL.g:6232:5: otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) )
                            {
                            otherlv_4=(Token)match(input,29,FollowSets000.FOLLOW_30); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              					newLeafNode(otherlv_4, grammarAccess.getNavigatingSemiArgCSAccess().getEqualsSignKeyword_2_2_0());

                            }
                            // InternalCompleteOCL.g:6236:5: ( (lv_ownedInitExpression_5_0= ruleExpCS ) )
                            // InternalCompleteOCL.g:6237:6: (lv_ownedInitExpression_5_0= ruleExpCS )
                            {
                            // InternalCompleteOCL.g:6237:6: (lv_ownedInitExpression_5_0= ruleExpCS )
                            // InternalCompleteOCL.g:6238:7: lv_ownedInitExpression_5_0= ruleExpCS
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
    // InternalCompleteOCL.g:6261:1: entryRuleCoIteratorVariableCS returns [EObject current=null] : iv_ruleCoIteratorVariableCS= ruleCoIteratorVariableCS EOF ;
    public final EObject entryRuleCoIteratorVariableCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCoIteratorVariableCS = null;


        try {
            // InternalCompleteOCL.g:6261:61: (iv_ruleCoIteratorVariableCS= ruleCoIteratorVariableCS EOF )
            // InternalCompleteOCL.g:6262:2: iv_ruleCoIteratorVariableCS= ruleCoIteratorVariableCS EOF
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
    // InternalCompleteOCL.g:6268:1: ruleCoIteratorVariableCS returns [EObject current=null] : ( ( (lv_name_0_0= ruleUnrestrictedName ) ) (otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) ) )? ) ;
    public final EObject ruleCoIteratorVariableCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_name_0_0 = null;

        EObject lv_ownedType_2_0 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:6274:2: ( ( ( (lv_name_0_0= ruleUnrestrictedName ) ) (otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) ) )? ) )
            // InternalCompleteOCL.g:6275:2: ( ( (lv_name_0_0= ruleUnrestrictedName ) ) (otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) ) )? )
            {
            // InternalCompleteOCL.g:6275:2: ( ( (lv_name_0_0= ruleUnrestrictedName ) ) (otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) ) )? )
            // InternalCompleteOCL.g:6276:3: ( (lv_name_0_0= ruleUnrestrictedName ) ) (otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) ) )?
            {
            // InternalCompleteOCL.g:6276:3: ( (lv_name_0_0= ruleUnrestrictedName ) )
            // InternalCompleteOCL.g:6277:4: (lv_name_0_0= ruleUnrestrictedName )
            {
            // InternalCompleteOCL.g:6277:4: (lv_name_0_0= ruleUnrestrictedName )
            // InternalCompleteOCL.g:6278:5: lv_name_0_0= ruleUnrestrictedName
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getCoIteratorVariableCSAccess().getNameUnrestrictedNameParserRuleCall_0_0());

            }
            pushFollow(FollowSets000.FOLLOW_65);
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

            // InternalCompleteOCL.g:6295:3: (otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) ) )?
            int alt125=2;
            int LA125_0 = input.LA(1);

            if ( (LA125_0==23) ) {
                alt125=1;
            }
            switch (alt125) {
                case 1 :
                    // InternalCompleteOCL.g:6296:4: otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) )
                    {
                    otherlv_1=(Token)match(input,23,FollowSets000.FOLLOW_21); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_1, grammarAccess.getCoIteratorVariableCSAccess().getColonKeyword_1_0());

                    }
                    // InternalCompleteOCL.g:6300:4: ( (lv_ownedType_2_0= ruleTypeExpCS ) )
                    // InternalCompleteOCL.g:6301:5: (lv_ownedType_2_0= ruleTypeExpCS )
                    {
                    // InternalCompleteOCL.g:6301:5: (lv_ownedType_2_0= ruleTypeExpCS )
                    // InternalCompleteOCL.g:6302:6: lv_ownedType_2_0= ruleTypeExpCS
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
    // InternalCompleteOCL.g:6324:1: entryRuleIfExpCS returns [EObject current=null] : iv_ruleIfExpCS= ruleIfExpCS EOF ;
    public final EObject entryRuleIfExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIfExpCS = null;


        try {
            // InternalCompleteOCL.g:6324:48: (iv_ruleIfExpCS= ruleIfExpCS EOF )
            // InternalCompleteOCL.g:6325:2: iv_ruleIfExpCS= ruleIfExpCS EOF
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
    // InternalCompleteOCL.g:6331:1: ruleIfExpCS returns [EObject current=null] : (otherlv_0= 'if' ( ( (lv_ownedCondition_1_1= ruleExpCS | lv_ownedCondition_1_2= rulePatternExpCS ) ) ) otherlv_2= 'then' ( (lv_ownedThenExpression_3_0= ruleExpCS ) ) ( (lv_ownedIfThenExpressions_4_0= ruleElseIfThenExpCS ) )* otherlv_5= 'else' ( (lv_ownedElseExpression_6_0= ruleExpCS ) ) otherlv_7= 'endif' ) ;
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
            // InternalCompleteOCL.g:6337:2: ( (otherlv_0= 'if' ( ( (lv_ownedCondition_1_1= ruleExpCS | lv_ownedCondition_1_2= rulePatternExpCS ) ) ) otherlv_2= 'then' ( (lv_ownedThenExpression_3_0= ruleExpCS ) ) ( (lv_ownedIfThenExpressions_4_0= ruleElseIfThenExpCS ) )* otherlv_5= 'else' ( (lv_ownedElseExpression_6_0= ruleExpCS ) ) otherlv_7= 'endif' ) )
            // InternalCompleteOCL.g:6338:2: (otherlv_0= 'if' ( ( (lv_ownedCondition_1_1= ruleExpCS | lv_ownedCondition_1_2= rulePatternExpCS ) ) ) otherlv_2= 'then' ( (lv_ownedThenExpression_3_0= ruleExpCS ) ) ( (lv_ownedIfThenExpressions_4_0= ruleElseIfThenExpCS ) )* otherlv_5= 'else' ( (lv_ownedElseExpression_6_0= ruleExpCS ) ) otherlv_7= 'endif' )
            {
            // InternalCompleteOCL.g:6338:2: (otherlv_0= 'if' ( ( (lv_ownedCondition_1_1= ruleExpCS | lv_ownedCondition_1_2= rulePatternExpCS ) ) ) otherlv_2= 'then' ( (lv_ownedThenExpression_3_0= ruleExpCS ) ) ( (lv_ownedIfThenExpressions_4_0= ruleElseIfThenExpCS ) )* otherlv_5= 'else' ( (lv_ownedElseExpression_6_0= ruleExpCS ) ) otherlv_7= 'endif' )
            // InternalCompleteOCL.g:6339:3: otherlv_0= 'if' ( ( (lv_ownedCondition_1_1= ruleExpCS | lv_ownedCondition_1_2= rulePatternExpCS ) ) ) otherlv_2= 'then' ( (lv_ownedThenExpression_3_0= ruleExpCS ) ) ( (lv_ownedIfThenExpressions_4_0= ruleElseIfThenExpCS ) )* otherlv_5= 'else' ( (lv_ownedElseExpression_6_0= ruleExpCS ) ) otherlv_7= 'endif'
            {
            otherlv_0=(Token)match(input,98,FollowSets000.FOLLOW_39); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getIfExpCSAccess().getIfKeyword_0());

            }
            // InternalCompleteOCL.g:6343:3: ( ( (lv_ownedCondition_1_1= ruleExpCS | lv_ownedCondition_1_2= rulePatternExpCS ) ) )
            // InternalCompleteOCL.g:6344:4: ( (lv_ownedCondition_1_1= ruleExpCS | lv_ownedCondition_1_2= rulePatternExpCS ) )
            {
            // InternalCompleteOCL.g:6344:4: ( (lv_ownedCondition_1_1= ruleExpCS | lv_ownedCondition_1_2= rulePatternExpCS ) )
            // InternalCompleteOCL.g:6345:5: (lv_ownedCondition_1_1= ruleExpCS | lv_ownedCondition_1_2= rulePatternExpCS )
            {
            // InternalCompleteOCL.g:6345:5: (lv_ownedCondition_1_1= ruleExpCS | lv_ownedCondition_1_2= rulePatternExpCS )
            int alt126=2;
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
            case 55:
            case 73:
            case 74:
            case 76:
            case 77:
            case 78:
            case 79:
            case 80:
            case 85:
            case 88:
            case 89:
            case 90:
            case 91:
            case 98:
            case 103:
            case 104:
                {
                alt126=1;
                }
                break;
            case RULE_SIMPLE_ID:
                {
                int LA126_2 = input.LA(2);

                if ( (LA126_2==23) ) {
                    alt126=2;
                }
                else if ( ((LA126_2>=17 && LA126_2<=18)||LA126_2==21||LA126_2==29||(LA126_2>=39 && LA126_2<=40)||LA126_2==52||(LA126_2>=55 && LA126_2<=72)||LA126_2==75||LA126_2==81||(LA126_2>=92 && LA126_2<=93)||LA126_2==99) ) {
                    alt126=1;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return current;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 126, 2, input);

                    throw nvae;
                }
                }
                break;
            case RULE_ESCAPED_ID:
                {
                int LA126_3 = input.LA(2);

                if ( (LA126_3==23) ) {
                    alt126=2;
                }
                else if ( ((LA126_3>=17 && LA126_3<=18)||LA126_3==21||LA126_3==29||(LA126_3>=39 && LA126_3<=40)||LA126_3==52||(LA126_3>=55 && LA126_3<=72)||LA126_3==75||LA126_3==81||(LA126_3>=92 && LA126_3<=93)||LA126_3==99) ) {
                    alt126=1;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return current;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 126, 3, input);

                    throw nvae;
                }
                }
                break;
            case 30:
                {
                int LA126_4 = input.LA(2);

                if ( (LA126_4==23) ) {
                    alt126=2;
                }
                else if ( ((LA126_4>=17 && LA126_4<=18)||LA126_4==21||LA126_4==29||(LA126_4>=39 && LA126_4<=40)||LA126_4==52||(LA126_4>=55 && LA126_4<=72)||LA126_4==75||LA126_4==81||(LA126_4>=92 && LA126_4<=93)||LA126_4==99) ) {
                    alt126=1;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return current;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 126, 4, input);

                    throw nvae;
                }
                }
                break;
            case 31:
                {
                int LA126_5 = input.LA(2);

                if ( (LA126_5==23) ) {
                    alt126=2;
                }
                else if ( ((LA126_5>=17 && LA126_5<=18)||LA126_5==21||LA126_5==29||(LA126_5>=39 && LA126_5<=40)||LA126_5==52||(LA126_5>=55 && LA126_5<=72)||LA126_5==75||LA126_5==81||(LA126_5>=92 && LA126_5<=93)||LA126_5==99) ) {
                    alt126=1;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return current;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 126, 5, input);

                    throw nvae;
                }
                }
                break;
            case 32:
                {
                int LA126_6 = input.LA(2);

                if ( ((LA126_6>=17 && LA126_6<=18)||LA126_6==21||LA126_6==29||(LA126_6>=39 && LA126_6<=40)||LA126_6==52||(LA126_6>=55 && LA126_6<=72)||LA126_6==75||LA126_6==81||(LA126_6>=92 && LA126_6<=93)||LA126_6==99) ) {
                    alt126=1;
                }
                else if ( (LA126_6==23) ) {
                    alt126=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return current;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 126, 6, input);

                    throw nvae;
                }
                }
                break;
            case 23:
                {
                alt126=2;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 126, 0, input);

                throw nvae;
            }

            switch (alt126) {
                case 1 :
                    // InternalCompleteOCL.g:6346:6: lv_ownedCondition_1_1= ruleExpCS
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getIfExpCSAccess().getOwnedConditionExpCSParserRuleCall_1_0_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_66);
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
                    // InternalCompleteOCL.g:6362:6: lv_ownedCondition_1_2= rulePatternExpCS
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getIfExpCSAccess().getOwnedConditionPatternExpCSParserRuleCall_1_0_1());

                    }
                    pushFollow(FollowSets000.FOLLOW_66);
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

            otherlv_2=(Token)match(input,99,FollowSets000.FOLLOW_30); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_2, grammarAccess.getIfExpCSAccess().getThenKeyword_2());

            }
            // InternalCompleteOCL.g:6384:3: ( (lv_ownedThenExpression_3_0= ruleExpCS ) )
            // InternalCompleteOCL.g:6385:4: (lv_ownedThenExpression_3_0= ruleExpCS )
            {
            // InternalCompleteOCL.g:6385:4: (lv_ownedThenExpression_3_0= ruleExpCS )
            // InternalCompleteOCL.g:6386:5: lv_ownedThenExpression_3_0= ruleExpCS
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getIfExpCSAccess().getOwnedThenExpressionExpCSParserRuleCall_3_0());

            }
            pushFollow(FollowSets000.FOLLOW_67);
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

            // InternalCompleteOCL.g:6403:3: ( (lv_ownedIfThenExpressions_4_0= ruleElseIfThenExpCS ) )*
            loop127:
            do {
                int alt127=2;
                int LA127_0 = input.LA(1);

                if ( (LA127_0==102) ) {
                    alt127=1;
                }


                switch (alt127) {
            	case 1 :
            	    // InternalCompleteOCL.g:6404:4: (lv_ownedIfThenExpressions_4_0= ruleElseIfThenExpCS )
            	    {
            	    // InternalCompleteOCL.g:6404:4: (lv_ownedIfThenExpressions_4_0= ruleElseIfThenExpCS )
            	    // InternalCompleteOCL.g:6405:5: lv_ownedIfThenExpressions_4_0= ruleElseIfThenExpCS
            	    {
            	    if ( state.backtracking==0 ) {

            	      					newCompositeNode(grammarAccess.getIfExpCSAccess().getOwnedIfThenExpressionsElseIfThenExpCSParserRuleCall_4_0());

            	    }
            	    pushFollow(FollowSets000.FOLLOW_67);
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
            	    break loop127;
                }
            } while (true);

            otherlv_5=(Token)match(input,100,FollowSets000.FOLLOW_30); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_5, grammarAccess.getIfExpCSAccess().getElseKeyword_5());

            }
            // InternalCompleteOCL.g:6426:3: ( (lv_ownedElseExpression_6_0= ruleExpCS ) )
            // InternalCompleteOCL.g:6427:4: (lv_ownedElseExpression_6_0= ruleExpCS )
            {
            // InternalCompleteOCL.g:6427:4: (lv_ownedElseExpression_6_0= ruleExpCS )
            // InternalCompleteOCL.g:6428:5: lv_ownedElseExpression_6_0= ruleExpCS
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getIfExpCSAccess().getOwnedElseExpressionExpCSParserRuleCall_6_0());

            }
            pushFollow(FollowSets000.FOLLOW_68);
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
    // InternalCompleteOCL.g:6453:1: entryRuleElseIfThenExpCS returns [EObject current=null] : iv_ruleElseIfThenExpCS= ruleElseIfThenExpCS EOF ;
    public final EObject entryRuleElseIfThenExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleElseIfThenExpCS = null;


        try {
            // InternalCompleteOCL.g:6453:56: (iv_ruleElseIfThenExpCS= ruleElseIfThenExpCS EOF )
            // InternalCompleteOCL.g:6454:2: iv_ruleElseIfThenExpCS= ruleElseIfThenExpCS EOF
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
    // InternalCompleteOCL.g:6460:1: ruleElseIfThenExpCS returns [EObject current=null] : (otherlv_0= 'elseif' ( (lv_ownedCondition_1_0= ruleExpCS ) ) otherlv_2= 'then' ( (lv_ownedThenExpression_3_0= ruleExpCS ) ) ) ;
    public final EObject ruleElseIfThenExpCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        EObject lv_ownedCondition_1_0 = null;

        EObject lv_ownedThenExpression_3_0 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:6466:2: ( (otherlv_0= 'elseif' ( (lv_ownedCondition_1_0= ruleExpCS ) ) otherlv_2= 'then' ( (lv_ownedThenExpression_3_0= ruleExpCS ) ) ) )
            // InternalCompleteOCL.g:6467:2: (otherlv_0= 'elseif' ( (lv_ownedCondition_1_0= ruleExpCS ) ) otherlv_2= 'then' ( (lv_ownedThenExpression_3_0= ruleExpCS ) ) )
            {
            // InternalCompleteOCL.g:6467:2: (otherlv_0= 'elseif' ( (lv_ownedCondition_1_0= ruleExpCS ) ) otherlv_2= 'then' ( (lv_ownedThenExpression_3_0= ruleExpCS ) ) )
            // InternalCompleteOCL.g:6468:3: otherlv_0= 'elseif' ( (lv_ownedCondition_1_0= ruleExpCS ) ) otherlv_2= 'then' ( (lv_ownedThenExpression_3_0= ruleExpCS ) )
            {
            otherlv_0=(Token)match(input,102,FollowSets000.FOLLOW_30); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getElseIfThenExpCSAccess().getElseifKeyword_0());

            }
            // InternalCompleteOCL.g:6472:3: ( (lv_ownedCondition_1_0= ruleExpCS ) )
            // InternalCompleteOCL.g:6473:4: (lv_ownedCondition_1_0= ruleExpCS )
            {
            // InternalCompleteOCL.g:6473:4: (lv_ownedCondition_1_0= ruleExpCS )
            // InternalCompleteOCL.g:6474:5: lv_ownedCondition_1_0= ruleExpCS
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getElseIfThenExpCSAccess().getOwnedConditionExpCSParserRuleCall_1_0());

            }
            pushFollow(FollowSets000.FOLLOW_66);
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

            otherlv_2=(Token)match(input,99,FollowSets000.FOLLOW_30); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_2, grammarAccess.getElseIfThenExpCSAccess().getThenKeyword_2());

            }
            // InternalCompleteOCL.g:6495:3: ( (lv_ownedThenExpression_3_0= ruleExpCS ) )
            // InternalCompleteOCL.g:6496:4: (lv_ownedThenExpression_3_0= ruleExpCS )
            {
            // InternalCompleteOCL.g:6496:4: (lv_ownedThenExpression_3_0= ruleExpCS )
            // InternalCompleteOCL.g:6497:5: lv_ownedThenExpression_3_0= ruleExpCS
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
    // InternalCompleteOCL.g:6518:1: entryRuleLetExpCS returns [EObject current=null] : iv_ruleLetExpCS= ruleLetExpCS EOF ;
    public final EObject entryRuleLetExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLetExpCS = null;


        try {
            // InternalCompleteOCL.g:6518:49: (iv_ruleLetExpCS= ruleLetExpCS EOF )
            // InternalCompleteOCL.g:6519:2: iv_ruleLetExpCS= ruleLetExpCS EOF
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
    // InternalCompleteOCL.g:6525:1: ruleLetExpCS returns [EObject current=null] : (otherlv_0= 'let' ( (lv_ownedVariables_1_0= ruleLetVariableCS ) ) (otherlv_2= ',' ( (lv_ownedVariables_3_0= ruleLetVariableCS ) ) )* otherlv_4= 'in' ( (lv_ownedInExpression_5_0= ruleExpCS ) ) ) ;
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
            // InternalCompleteOCL.g:6531:2: ( (otherlv_0= 'let' ( (lv_ownedVariables_1_0= ruleLetVariableCS ) ) (otherlv_2= ',' ( (lv_ownedVariables_3_0= ruleLetVariableCS ) ) )* otherlv_4= 'in' ( (lv_ownedInExpression_5_0= ruleExpCS ) ) ) )
            // InternalCompleteOCL.g:6532:2: (otherlv_0= 'let' ( (lv_ownedVariables_1_0= ruleLetVariableCS ) ) (otherlv_2= ',' ( (lv_ownedVariables_3_0= ruleLetVariableCS ) ) )* otherlv_4= 'in' ( (lv_ownedInExpression_5_0= ruleExpCS ) ) )
            {
            // InternalCompleteOCL.g:6532:2: (otherlv_0= 'let' ( (lv_ownedVariables_1_0= ruleLetVariableCS ) ) (otherlv_2= ',' ( (lv_ownedVariables_3_0= ruleLetVariableCS ) ) )* otherlv_4= 'in' ( (lv_ownedInExpression_5_0= ruleExpCS ) ) )
            // InternalCompleteOCL.g:6533:3: otherlv_0= 'let' ( (lv_ownedVariables_1_0= ruleLetVariableCS ) ) (otherlv_2= ',' ( (lv_ownedVariables_3_0= ruleLetVariableCS ) ) )* otherlv_4= 'in' ( (lv_ownedInExpression_5_0= ruleExpCS ) )
            {
            otherlv_0=(Token)match(input,103,FollowSets000.FOLLOW_15); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getLetExpCSAccess().getLetKeyword_0());

            }
            // InternalCompleteOCL.g:6537:3: ( (lv_ownedVariables_1_0= ruleLetVariableCS ) )
            // InternalCompleteOCL.g:6538:4: (lv_ownedVariables_1_0= ruleLetVariableCS )
            {
            // InternalCompleteOCL.g:6538:4: (lv_ownedVariables_1_0= ruleLetVariableCS )
            // InternalCompleteOCL.g:6539:5: lv_ownedVariables_1_0= ruleLetVariableCS
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getLetExpCSAccess().getOwnedVariablesLetVariableCSParserRuleCall_1_0());

            }
            pushFollow(FollowSets000.FOLLOW_69);
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

            // InternalCompleteOCL.g:6556:3: (otherlv_2= ',' ( (lv_ownedVariables_3_0= ruleLetVariableCS ) ) )*
            loop128:
            do {
                int alt128=2;
                int LA128_0 = input.LA(1);

                if ( (LA128_0==28) ) {
                    alt128=1;
                }


                switch (alt128) {
            	case 1 :
            	    // InternalCompleteOCL.g:6557:4: otherlv_2= ',' ( (lv_ownedVariables_3_0= ruleLetVariableCS ) )
            	    {
            	    otherlv_2=(Token)match(input,28,FollowSets000.FOLLOW_15); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      				newLeafNode(otherlv_2, grammarAccess.getLetExpCSAccess().getCommaKeyword_2_0());

            	    }
            	    // InternalCompleteOCL.g:6561:4: ( (lv_ownedVariables_3_0= ruleLetVariableCS ) )
            	    // InternalCompleteOCL.g:6562:5: (lv_ownedVariables_3_0= ruleLetVariableCS )
            	    {
            	    // InternalCompleteOCL.g:6562:5: (lv_ownedVariables_3_0= ruleLetVariableCS )
            	    // InternalCompleteOCL.g:6563:6: lv_ownedVariables_3_0= ruleLetVariableCS
            	    {
            	    if ( state.backtracking==0 ) {

            	      						newCompositeNode(grammarAccess.getLetExpCSAccess().getOwnedVariablesLetVariableCSParserRuleCall_2_1_0());

            	    }
            	    pushFollow(FollowSets000.FOLLOW_69);
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
            	    break loop128;
                }
            } while (true);

            otherlv_4=(Token)match(input,95,FollowSets000.FOLLOW_30); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_4, grammarAccess.getLetExpCSAccess().getInKeyword_3());

            }
            // InternalCompleteOCL.g:6585:3: ( (lv_ownedInExpression_5_0= ruleExpCS ) )
            // InternalCompleteOCL.g:6586:4: (lv_ownedInExpression_5_0= ruleExpCS )
            {
            // InternalCompleteOCL.g:6586:4: (lv_ownedInExpression_5_0= ruleExpCS )
            // InternalCompleteOCL.g:6587:5: lv_ownedInExpression_5_0= ruleExpCS
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
    // InternalCompleteOCL.g:6608:1: entryRuleLetVariableCS returns [EObject current=null] : iv_ruleLetVariableCS= ruleLetVariableCS EOF ;
    public final EObject entryRuleLetVariableCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLetVariableCS = null;


        try {
            // InternalCompleteOCL.g:6608:54: (iv_ruleLetVariableCS= ruleLetVariableCS EOF )
            // InternalCompleteOCL.g:6609:2: iv_ruleLetVariableCS= ruleLetVariableCS EOF
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
    // InternalCompleteOCL.g:6615:1: ruleLetVariableCS returns [EObject current=null] : ( ( (lv_name_0_0= ruleUnrestrictedName ) ) ( (lv_ownedRoundBracketedClause_1_0= ruleRoundBracketedClauseCS ) )? (otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) )? otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) ) ;
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
            // InternalCompleteOCL.g:6621:2: ( ( ( (lv_name_0_0= ruleUnrestrictedName ) ) ( (lv_ownedRoundBracketedClause_1_0= ruleRoundBracketedClauseCS ) )? (otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) )? otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) ) )
            // InternalCompleteOCL.g:6622:2: ( ( (lv_name_0_0= ruleUnrestrictedName ) ) ( (lv_ownedRoundBracketedClause_1_0= ruleRoundBracketedClauseCS ) )? (otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) )? otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )
            {
            // InternalCompleteOCL.g:6622:2: ( ( (lv_name_0_0= ruleUnrestrictedName ) ) ( (lv_ownedRoundBracketedClause_1_0= ruleRoundBracketedClauseCS ) )? (otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) )? otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )
            // InternalCompleteOCL.g:6623:3: ( (lv_name_0_0= ruleUnrestrictedName ) ) ( (lv_ownedRoundBracketedClause_1_0= ruleRoundBracketedClauseCS ) )? (otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) )? otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) )
            {
            // InternalCompleteOCL.g:6623:3: ( (lv_name_0_0= ruleUnrestrictedName ) )
            // InternalCompleteOCL.g:6624:4: (lv_name_0_0= ruleUnrestrictedName )
            {
            // InternalCompleteOCL.g:6624:4: (lv_name_0_0= ruleUnrestrictedName )
            // InternalCompleteOCL.g:6625:5: lv_name_0_0= ruleUnrestrictedName
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getLetVariableCSAccess().getNameUnrestrictedNameParserRuleCall_0_0());

            }
            pushFollow(FollowSets000.FOLLOW_70);
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

            // InternalCompleteOCL.g:6642:3: ( (lv_ownedRoundBracketedClause_1_0= ruleRoundBracketedClauseCS ) )?
            int alt129=2;
            int LA129_0 = input.LA(1);

            if ( (LA129_0==21) ) {
                alt129=1;
            }
            switch (alt129) {
                case 1 :
                    // InternalCompleteOCL.g:6643:4: (lv_ownedRoundBracketedClause_1_0= ruleRoundBracketedClauseCS )
                    {
                    // InternalCompleteOCL.g:6643:4: (lv_ownedRoundBracketedClause_1_0= ruleRoundBracketedClauseCS )
                    // InternalCompleteOCL.g:6644:5: lv_ownedRoundBracketedClause_1_0= ruleRoundBracketedClauseCS
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

            // InternalCompleteOCL.g:6661:3: (otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) )?
            int alt130=2;
            int LA130_0 = input.LA(1);

            if ( (LA130_0==23) ) {
                alt130=1;
            }
            switch (alt130) {
                case 1 :
                    // InternalCompleteOCL.g:6662:4: otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) )
                    {
                    otherlv_2=(Token)match(input,23,FollowSets000.FOLLOW_21); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_2, grammarAccess.getLetVariableCSAccess().getColonKeyword_2_0());

                    }
                    // InternalCompleteOCL.g:6666:4: ( (lv_ownedType_3_0= ruleTypeExpCS ) )
                    // InternalCompleteOCL.g:6667:5: (lv_ownedType_3_0= ruleTypeExpCS )
                    {
                    // InternalCompleteOCL.g:6667:5: (lv_ownedType_3_0= ruleTypeExpCS )
                    // InternalCompleteOCL.g:6668:6: lv_ownedType_3_0= ruleTypeExpCS
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getLetVariableCSAccess().getOwnedTypeTypeExpCSParserRuleCall_2_1_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_20);
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

            otherlv_4=(Token)match(input,29,FollowSets000.FOLLOW_30); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_4, grammarAccess.getLetVariableCSAccess().getEqualsSignKeyword_3());

            }
            // InternalCompleteOCL.g:6690:3: ( (lv_ownedInitExpression_5_0= ruleExpCS ) )
            // InternalCompleteOCL.g:6691:4: (lv_ownedInitExpression_5_0= ruleExpCS )
            {
            // InternalCompleteOCL.g:6691:4: (lv_ownedInitExpression_5_0= ruleExpCS )
            // InternalCompleteOCL.g:6692:5: lv_ownedInitExpression_5_0= ruleExpCS
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
    // InternalCompleteOCL.g:6713:1: entryRuleNestedExpCS returns [EObject current=null] : iv_ruleNestedExpCS= ruleNestedExpCS EOF ;
    public final EObject entryRuleNestedExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNestedExpCS = null;


        try {
            // InternalCompleteOCL.g:6713:52: (iv_ruleNestedExpCS= ruleNestedExpCS EOF )
            // InternalCompleteOCL.g:6714:2: iv_ruleNestedExpCS= ruleNestedExpCS EOF
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
    // InternalCompleteOCL.g:6720:1: ruleNestedExpCS returns [EObject current=null] : (otherlv_0= '(' ( (lv_ownedExpression_1_0= ruleExpCS ) ) otherlv_2= ')' ) ;
    public final EObject ruleNestedExpCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        EObject lv_ownedExpression_1_0 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:6726:2: ( (otherlv_0= '(' ( (lv_ownedExpression_1_0= ruleExpCS ) ) otherlv_2= ')' ) )
            // InternalCompleteOCL.g:6727:2: (otherlv_0= '(' ( (lv_ownedExpression_1_0= ruleExpCS ) ) otherlv_2= ')' )
            {
            // InternalCompleteOCL.g:6727:2: (otherlv_0= '(' ( (lv_ownedExpression_1_0= ruleExpCS ) ) otherlv_2= ')' )
            // InternalCompleteOCL.g:6728:3: otherlv_0= '(' ( (lv_ownedExpression_1_0= ruleExpCS ) ) otherlv_2= ')'
            {
            otherlv_0=(Token)match(input,21,FollowSets000.FOLLOW_30); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getNestedExpCSAccess().getLeftParenthesisKeyword_0());

            }
            // InternalCompleteOCL.g:6732:3: ( (lv_ownedExpression_1_0= ruleExpCS ) )
            // InternalCompleteOCL.g:6733:4: (lv_ownedExpression_1_0= ruleExpCS )
            {
            // InternalCompleteOCL.g:6733:4: (lv_ownedExpression_1_0= ruleExpCS )
            // InternalCompleteOCL.g:6734:5: lv_ownedExpression_1_0= ruleExpCS
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
    // InternalCompleteOCL.g:6759:1: entryRuleSelfExpCS returns [EObject current=null] : iv_ruleSelfExpCS= ruleSelfExpCS EOF ;
    public final EObject entryRuleSelfExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSelfExpCS = null;


        try {
            // InternalCompleteOCL.g:6759:50: (iv_ruleSelfExpCS= ruleSelfExpCS EOF )
            // InternalCompleteOCL.g:6760:2: iv_ruleSelfExpCS= ruleSelfExpCS EOF
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
    // InternalCompleteOCL.g:6766:1: ruleSelfExpCS returns [EObject current=null] : ( () otherlv_1= 'self' ) ;
    public final EObject ruleSelfExpCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;


        	enterRule();

        try {
            // InternalCompleteOCL.g:6772:2: ( ( () otherlv_1= 'self' ) )
            // InternalCompleteOCL.g:6773:2: ( () otherlv_1= 'self' )
            {
            // InternalCompleteOCL.g:6773:2: ( () otherlv_1= 'self' )
            // InternalCompleteOCL.g:6774:3: () otherlv_1= 'self'
            {
            // InternalCompleteOCL.g:6774:3: ()
            // InternalCompleteOCL.g:6775:4:
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


    // $ANTLR start "entryRuleMultiplicityBoundsCS"
    // InternalCompleteOCL.g:6792:1: entryRuleMultiplicityBoundsCS returns [EObject current=null] : iv_ruleMultiplicityBoundsCS= ruleMultiplicityBoundsCS EOF ;
    public final EObject entryRuleMultiplicityBoundsCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMultiplicityBoundsCS = null;


        try {
            // InternalCompleteOCL.g:6792:61: (iv_ruleMultiplicityBoundsCS= ruleMultiplicityBoundsCS EOF )
            // InternalCompleteOCL.g:6793:2: iv_ruleMultiplicityBoundsCS= ruleMultiplicityBoundsCS EOF
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
    // InternalCompleteOCL.g:6799:1: ruleMultiplicityBoundsCS returns [EObject current=null] : ( ( (lv_lowerBound_0_0= ruleLOWER ) ) (otherlv_1= '..' ( (lv_upperBound_2_0= ruleUPPER ) ) )? ) ;
    public final EObject ruleMultiplicityBoundsCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_lowerBound_0_0 = null;

        AntlrDatatypeRuleToken lv_upperBound_2_0 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:6805:2: ( ( ( (lv_lowerBound_0_0= ruleLOWER ) ) (otherlv_1= '..' ( (lv_upperBound_2_0= ruleUPPER ) ) )? ) )
            // InternalCompleteOCL.g:6806:2: ( ( (lv_lowerBound_0_0= ruleLOWER ) ) (otherlv_1= '..' ( (lv_upperBound_2_0= ruleUPPER ) ) )? )
            {
            // InternalCompleteOCL.g:6806:2: ( ( (lv_lowerBound_0_0= ruleLOWER ) ) (otherlv_1= '..' ( (lv_upperBound_2_0= ruleUPPER ) ) )? )
            // InternalCompleteOCL.g:6807:3: ( (lv_lowerBound_0_0= ruleLOWER ) ) (otherlv_1= '..' ( (lv_upperBound_2_0= ruleUPPER ) ) )?
            {
            // InternalCompleteOCL.g:6807:3: ( (lv_lowerBound_0_0= ruleLOWER ) )
            // InternalCompleteOCL.g:6808:4: (lv_lowerBound_0_0= ruleLOWER )
            {
            // InternalCompleteOCL.g:6808:4: (lv_lowerBound_0_0= ruleLOWER )
            // InternalCompleteOCL.g:6809:5: lv_lowerBound_0_0= ruleLOWER
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getMultiplicityBoundsCSAccess().getLowerBoundLOWERParserRuleCall_0_0());

            }
            pushFollow(FollowSets000.FOLLOW_40);
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

            // InternalCompleteOCL.g:6826:3: (otherlv_1= '..' ( (lv_upperBound_2_0= ruleUPPER ) ) )?
            int alt131=2;
            int LA131_0 = input.LA(1);

            if ( (LA131_0==83) ) {
                alt131=1;
            }
            switch (alt131) {
                case 1 :
                    // InternalCompleteOCL.g:6827:4: otherlv_1= '..' ( (lv_upperBound_2_0= ruleUPPER ) )
                    {
                    otherlv_1=(Token)match(input,83,FollowSets000.FOLLOW_71); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_1, grammarAccess.getMultiplicityBoundsCSAccess().getFullStopFullStopKeyword_1_0());

                    }
                    // InternalCompleteOCL.g:6831:4: ( (lv_upperBound_2_0= ruleUPPER ) )
                    // InternalCompleteOCL.g:6832:5: (lv_upperBound_2_0= ruleUPPER )
                    {
                    // InternalCompleteOCL.g:6832:5: (lv_upperBound_2_0= ruleUPPER )
                    // InternalCompleteOCL.g:6833:6: lv_upperBound_2_0= ruleUPPER
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
    // InternalCompleteOCL.g:6855:1: entryRuleMultiplicityCS returns [EObject current=null] : iv_ruleMultiplicityCS= ruleMultiplicityCS EOF ;
    public final EObject entryRuleMultiplicityCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMultiplicityCS = null;


        try {
            // InternalCompleteOCL.g:6855:55: (iv_ruleMultiplicityCS= ruleMultiplicityCS EOF )
            // InternalCompleteOCL.g:6856:2: iv_ruleMultiplicityCS= ruleMultiplicityCS EOF
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
    // InternalCompleteOCL.g:6862:1: ruleMultiplicityCS returns [EObject current=null] : (otherlv_0= '[' (this_MultiplicityBoundsCS_1= ruleMultiplicityBoundsCS | this_MultiplicityStringCS_2= ruleMultiplicityStringCS ) (otherlv_3= '|?' | ( (lv_isNullFree_4_0= '|1' ) ) )? otherlv_5= ']' ) ;
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
            // InternalCompleteOCL.g:6868:2: ( (otherlv_0= '[' (this_MultiplicityBoundsCS_1= ruleMultiplicityBoundsCS | this_MultiplicityStringCS_2= ruleMultiplicityStringCS ) (otherlv_3= '|?' | ( (lv_isNullFree_4_0= '|1' ) ) )? otherlv_5= ']' ) )
            // InternalCompleteOCL.g:6869:2: (otherlv_0= '[' (this_MultiplicityBoundsCS_1= ruleMultiplicityBoundsCS | this_MultiplicityStringCS_2= ruleMultiplicityStringCS ) (otherlv_3= '|?' | ( (lv_isNullFree_4_0= '|1' ) ) )? otherlv_5= ']' )
            {
            // InternalCompleteOCL.g:6869:2: (otherlv_0= '[' (this_MultiplicityBoundsCS_1= ruleMultiplicityBoundsCS | this_MultiplicityStringCS_2= ruleMultiplicityStringCS ) (otherlv_3= '|?' | ( (lv_isNullFree_4_0= '|1' ) ) )? otherlv_5= ']' )
            // InternalCompleteOCL.g:6870:3: otherlv_0= '[' (this_MultiplicityBoundsCS_1= ruleMultiplicityBoundsCS | this_MultiplicityStringCS_2= ruleMultiplicityStringCS ) (otherlv_3= '|?' | ( (lv_isNullFree_4_0= '|1' ) ) )? otherlv_5= ']'
            {
            otherlv_0=(Token)match(input,93,FollowSets000.FOLLOW_72); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getMultiplicityCSAccess().getLeftSquareBracketKeyword_0());

            }
            // InternalCompleteOCL.g:6874:3: (this_MultiplicityBoundsCS_1= ruleMultiplicityBoundsCS | this_MultiplicityStringCS_2= ruleMultiplicityStringCS )
            int alt132=2;
            int LA132_0 = input.LA(1);

            if ( (LA132_0==RULE_INT) ) {
                alt132=1;
            }
            else if ( (LA132_0==41||LA132_0==55||LA132_0==57) ) {
                alt132=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 132, 0, input);

                throw nvae;
            }
            switch (alt132) {
                case 1 :
                    // InternalCompleteOCL.g:6875:4: this_MultiplicityBoundsCS_1= ruleMultiplicityBoundsCS
                    {
                    if ( state.backtracking==0 ) {

                      				/* */

                    }
                    if ( state.backtracking==0 ) {

                      				newCompositeNode(grammarAccess.getMultiplicityCSAccess().getMultiplicityBoundsCSParserRuleCall_1_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_73);
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
                    // InternalCompleteOCL.g:6887:4: this_MultiplicityStringCS_2= ruleMultiplicityStringCS
                    {
                    if ( state.backtracking==0 ) {

                      				/* */

                    }
                    if ( state.backtracking==0 ) {

                      				newCompositeNode(grammarAccess.getMultiplicityCSAccess().getMultiplicityStringCSParserRuleCall_1_1());

                    }
                    pushFollow(FollowSets000.FOLLOW_73);
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

            // InternalCompleteOCL.g:6899:3: (otherlv_3= '|?' | ( (lv_isNullFree_4_0= '|1' ) ) )?
            int alt133=3;
            int LA133_0 = input.LA(1);

            if ( (LA133_0==105) ) {
                alt133=1;
            }
            else if ( (LA133_0==106) ) {
                alt133=2;
            }
            switch (alt133) {
                case 1 :
                    // InternalCompleteOCL.g:6900:4: otherlv_3= '|?'
                    {
                    otherlv_3=(Token)match(input,105,FollowSets000.FOLLOW_74); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_3, grammarAccess.getMultiplicityCSAccess().getVerticalLineQuestionMarkKeyword_2_0());

                    }

                    }
                    break;
                case 2 :
                    // InternalCompleteOCL.g:6905:4: ( (lv_isNullFree_4_0= '|1' ) )
                    {
                    // InternalCompleteOCL.g:6905:4: ( (lv_isNullFree_4_0= '|1' ) )
                    // InternalCompleteOCL.g:6906:5: (lv_isNullFree_4_0= '|1' )
                    {
                    // InternalCompleteOCL.g:6906:5: (lv_isNullFree_4_0= '|1' )
                    // InternalCompleteOCL.g:6907:6: lv_isNullFree_4_0= '|1'
                    {
                    lv_isNullFree_4_0=(Token)match(input,106,FollowSets000.FOLLOW_74); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						newLeafNode(lv_isNullFree_4_0, grammarAccess.getMultiplicityCSAccess().getIsNullFree1Keyword_2_1_0());

                    }
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElement(grammarAccess.getMultiplicityCSRule());
                      						}
                      						setWithLastConsumed(current, "isNullFree", lv_isNullFree_4_0 != null, "|1");

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
    // InternalCompleteOCL.g:6928:1: entryRuleMultiplicityStringCS returns [EObject current=null] : iv_ruleMultiplicityStringCS= ruleMultiplicityStringCS EOF ;
    public final EObject entryRuleMultiplicityStringCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMultiplicityStringCS = null;


        try {
            // InternalCompleteOCL.g:6928:61: (iv_ruleMultiplicityStringCS= ruleMultiplicityStringCS EOF )
            // InternalCompleteOCL.g:6929:2: iv_ruleMultiplicityStringCS= ruleMultiplicityStringCS EOF
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
    // InternalCompleteOCL.g:6935:1: ruleMultiplicityStringCS returns [EObject current=null] : ( ( (lv_stringBounds_0_1= '*' | lv_stringBounds_0_2= '+' | lv_stringBounds_0_3= '?' ) ) ) ;
    public final EObject ruleMultiplicityStringCS() throws RecognitionException {
        EObject current = null;

        Token lv_stringBounds_0_1=null;
        Token lv_stringBounds_0_2=null;
        Token lv_stringBounds_0_3=null;


        	enterRule();

        try {
            // InternalCompleteOCL.g:6941:2: ( ( ( (lv_stringBounds_0_1= '*' | lv_stringBounds_0_2= '+' | lv_stringBounds_0_3= '?' ) ) ) )
            // InternalCompleteOCL.g:6942:2: ( ( (lv_stringBounds_0_1= '*' | lv_stringBounds_0_2= '+' | lv_stringBounds_0_3= '?' ) ) )
            {
            // InternalCompleteOCL.g:6942:2: ( ( (lv_stringBounds_0_1= '*' | lv_stringBounds_0_2= '+' | lv_stringBounds_0_3= '?' ) ) )
            // InternalCompleteOCL.g:6943:3: ( (lv_stringBounds_0_1= '*' | lv_stringBounds_0_2= '+' | lv_stringBounds_0_3= '?' ) )
            {
            // InternalCompleteOCL.g:6943:3: ( (lv_stringBounds_0_1= '*' | lv_stringBounds_0_2= '+' | lv_stringBounds_0_3= '?' ) )
            // InternalCompleteOCL.g:6944:4: (lv_stringBounds_0_1= '*' | lv_stringBounds_0_2= '+' | lv_stringBounds_0_3= '?' )
            {
            // InternalCompleteOCL.g:6944:4: (lv_stringBounds_0_1= '*' | lv_stringBounds_0_2= '+' | lv_stringBounds_0_3= '?' )
            int alt134=3;
            switch ( input.LA(1) ) {
            case 55:
                {
                alt134=1;
                }
                break;
            case 57:
                {
                alt134=2;
                }
                break;
            case 41:
                {
                alt134=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 134, 0, input);

                throw nvae;
            }

            switch (alt134) {
                case 1 :
                    // InternalCompleteOCL.g:6945:5: lv_stringBounds_0_1= '*'
                    {
                    lv_stringBounds_0_1=(Token)match(input,55,FollowSets000.FOLLOW_2); if (state.failed) return current;
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
                    // InternalCompleteOCL.g:6956:5: lv_stringBounds_0_2= '+'
                    {
                    lv_stringBounds_0_2=(Token)match(input,57,FollowSets000.FOLLOW_2); if (state.failed) return current;
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
                    // InternalCompleteOCL.g:6967:5: lv_stringBounds_0_3= '?'
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
    // InternalCompleteOCL.g:6983:1: entryRulePathNameCS returns [EObject current=null] : iv_rulePathNameCS= rulePathNameCS EOF ;
    public final EObject entryRulePathNameCS() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePathNameCS = null;


        try {
            // InternalCompleteOCL.g:6983:51: (iv_rulePathNameCS= rulePathNameCS EOF )
            // InternalCompleteOCL.g:6984:2: iv_rulePathNameCS= rulePathNameCS EOF
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
    // InternalCompleteOCL.g:6990:1: rulePathNameCS returns [EObject current=null] : ( ( (lv_ownedPathElements_0_0= ruleFirstPathElementCS ) ) (otherlv_1= '::' ( (lv_ownedPathElements_2_0= ruleNextPathElementCS ) ) )* ) ;
    public final EObject rulePathNameCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject lv_ownedPathElements_0_0 = null;

        EObject lv_ownedPathElements_2_0 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:6996:2: ( ( ( (lv_ownedPathElements_0_0= ruleFirstPathElementCS ) ) (otherlv_1= '::' ( (lv_ownedPathElements_2_0= ruleNextPathElementCS ) ) )* ) )
            // InternalCompleteOCL.g:6997:2: ( ( (lv_ownedPathElements_0_0= ruleFirstPathElementCS ) ) (otherlv_1= '::' ( (lv_ownedPathElements_2_0= ruleNextPathElementCS ) ) )* )
            {
            // InternalCompleteOCL.g:6997:2: ( ( (lv_ownedPathElements_0_0= ruleFirstPathElementCS ) ) (otherlv_1= '::' ( (lv_ownedPathElements_2_0= ruleNextPathElementCS ) ) )* )
            // InternalCompleteOCL.g:6998:3: ( (lv_ownedPathElements_0_0= ruleFirstPathElementCS ) ) (otherlv_1= '::' ( (lv_ownedPathElements_2_0= ruleNextPathElementCS ) ) )*
            {
            // InternalCompleteOCL.g:6998:3: ( (lv_ownedPathElements_0_0= ruleFirstPathElementCS ) )
            // InternalCompleteOCL.g:6999:4: (lv_ownedPathElements_0_0= ruleFirstPathElementCS )
            {
            // InternalCompleteOCL.g:6999:4: (lv_ownedPathElements_0_0= ruleFirstPathElementCS )
            // InternalCompleteOCL.g:7000:5: lv_ownedPathElements_0_0= ruleFirstPathElementCS
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getPathNameCSAccess().getOwnedPathElementsFirstPathElementCSParserRuleCall_0_0());

            }
            pushFollow(FollowSets000.FOLLOW_32);
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

            // InternalCompleteOCL.g:7017:3: (otherlv_1= '::' ( (lv_ownedPathElements_2_0= ruleNextPathElementCS ) ) )*
            loop135:
            do {
                int alt135=2;
                int LA135_0 = input.LA(1);

                if ( (LA135_0==75) ) {
                    alt135=1;
                }


                switch (alt135) {
            	case 1 :
            	    // InternalCompleteOCL.g:7018:4: otherlv_1= '::' ( (lv_ownedPathElements_2_0= ruleNextPathElementCS ) )
            	    {
            	    otherlv_1=(Token)match(input,75,FollowSets000.FOLLOW_5); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      				newLeafNode(otherlv_1, grammarAccess.getPathNameCSAccess().getColonColonKeyword_1_0());

            	    }
            	    // InternalCompleteOCL.g:7022:4: ( (lv_ownedPathElements_2_0= ruleNextPathElementCS ) )
            	    // InternalCompleteOCL.g:7023:5: (lv_ownedPathElements_2_0= ruleNextPathElementCS )
            	    {
            	    // InternalCompleteOCL.g:7023:5: (lv_ownedPathElements_2_0= ruleNextPathElementCS )
            	    // InternalCompleteOCL.g:7024:6: lv_ownedPathElements_2_0= ruleNextPathElementCS
            	    {
            	    if ( state.backtracking==0 ) {

            	      						newCompositeNode(grammarAccess.getPathNameCSAccess().getOwnedPathElementsNextPathElementCSParserRuleCall_1_1_0());

            	    }
            	    pushFollow(FollowSets000.FOLLOW_32);
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
            	    break loop135;
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


    // $ANTLR start "entryRuleUnreservedPathNameCS"
    // InternalCompleteOCL.g:7046:1: entryRuleUnreservedPathNameCS returns [EObject current=null] : iv_ruleUnreservedPathNameCS= ruleUnreservedPathNameCS EOF ;
    public final EObject entryRuleUnreservedPathNameCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleUnreservedPathNameCS = null;


        try {
            // InternalCompleteOCL.g:7046:61: (iv_ruleUnreservedPathNameCS= ruleUnreservedPathNameCS EOF )
            // InternalCompleteOCL.g:7047:2: iv_ruleUnreservedPathNameCS= ruleUnreservedPathNameCS EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getUnreservedPathNameCSRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleUnreservedPathNameCS=ruleUnreservedPathNameCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleUnreservedPathNameCS;
            }
            match(input,EOF,FollowSets000.FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleUnreservedPathNameCS"


    // $ANTLR start "ruleUnreservedPathNameCS"
    // InternalCompleteOCL.g:7053:1: ruleUnreservedPathNameCS returns [EObject current=null] : ( ( (lv_ownedPathElements_0_0= ruleNextPathElementCS ) ) (otherlv_1= '::' ( (lv_ownedPathElements_2_0= ruleNextPathElementCS ) ) )* ) ;
    public final EObject ruleUnreservedPathNameCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject lv_ownedPathElements_0_0 = null;

        EObject lv_ownedPathElements_2_0 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:7059:2: ( ( ( (lv_ownedPathElements_0_0= ruleNextPathElementCS ) ) (otherlv_1= '::' ( (lv_ownedPathElements_2_0= ruleNextPathElementCS ) ) )* ) )
            // InternalCompleteOCL.g:7060:2: ( ( (lv_ownedPathElements_0_0= ruleNextPathElementCS ) ) (otherlv_1= '::' ( (lv_ownedPathElements_2_0= ruleNextPathElementCS ) ) )* )
            {
            // InternalCompleteOCL.g:7060:2: ( ( (lv_ownedPathElements_0_0= ruleNextPathElementCS ) ) (otherlv_1= '::' ( (lv_ownedPathElements_2_0= ruleNextPathElementCS ) ) )* )
            // InternalCompleteOCL.g:7061:3: ( (lv_ownedPathElements_0_0= ruleNextPathElementCS ) ) (otherlv_1= '::' ( (lv_ownedPathElements_2_0= ruleNextPathElementCS ) ) )*
            {
            // InternalCompleteOCL.g:7061:3: ( (lv_ownedPathElements_0_0= ruleNextPathElementCS ) )
            // InternalCompleteOCL.g:7062:4: (lv_ownedPathElements_0_0= ruleNextPathElementCS )
            {
            // InternalCompleteOCL.g:7062:4: (lv_ownedPathElements_0_0= ruleNextPathElementCS )
            // InternalCompleteOCL.g:7063:5: lv_ownedPathElements_0_0= ruleNextPathElementCS
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getUnreservedPathNameCSAccess().getOwnedPathElementsNextPathElementCSParserRuleCall_0_0());

            }
            pushFollow(FollowSets000.FOLLOW_32);
            lv_ownedPathElements_0_0=ruleNextPathElementCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getUnreservedPathNameCSRule());
              					}
              					add(
              						current,
              						"ownedPathElements",
              						lv_ownedPathElements_0_0,
              						"org.eclipse.ocl.xtext.base.Base.NextPathElementCS");
              					afterParserOrEnumRuleCall();

            }

            }


            }

            // InternalCompleteOCL.g:7080:3: (otherlv_1= '::' ( (lv_ownedPathElements_2_0= ruleNextPathElementCS ) ) )*
            loop136:
            do {
                int alt136=2;
                int LA136_0 = input.LA(1);

                if ( (LA136_0==75) ) {
                    alt136=1;
                }


                switch (alt136) {
            	case 1 :
            	    // InternalCompleteOCL.g:7081:4: otherlv_1= '::' ( (lv_ownedPathElements_2_0= ruleNextPathElementCS ) )
            	    {
            	    otherlv_1=(Token)match(input,75,FollowSets000.FOLLOW_5); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      				newLeafNode(otherlv_1, grammarAccess.getUnreservedPathNameCSAccess().getColonColonKeyword_1_0());

            	    }
            	    // InternalCompleteOCL.g:7085:4: ( (lv_ownedPathElements_2_0= ruleNextPathElementCS ) )
            	    // InternalCompleteOCL.g:7086:5: (lv_ownedPathElements_2_0= ruleNextPathElementCS )
            	    {
            	    // InternalCompleteOCL.g:7086:5: (lv_ownedPathElements_2_0= ruleNextPathElementCS )
            	    // InternalCompleteOCL.g:7087:6: lv_ownedPathElements_2_0= ruleNextPathElementCS
            	    {
            	    if ( state.backtracking==0 ) {

            	      						newCompositeNode(grammarAccess.getUnreservedPathNameCSAccess().getOwnedPathElementsNextPathElementCSParserRuleCall_1_1_0());

            	    }
            	    pushFollow(FollowSets000.FOLLOW_32);
            	    lv_ownedPathElements_2_0=ruleNextPathElementCS();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      						if (current==null) {
            	      							current = createModelElementForParent(grammarAccess.getUnreservedPathNameCSRule());
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
            	    break loop136;
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
    // $ANTLR end "ruleUnreservedPathNameCS"


    // $ANTLR start "entryRuleFirstPathElementCS"
    // InternalCompleteOCL.g:7109:1: entryRuleFirstPathElementCS returns [EObject current=null] : iv_ruleFirstPathElementCS= ruleFirstPathElementCS EOF ;
    public final EObject entryRuleFirstPathElementCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFirstPathElementCS = null;


        try {
            // InternalCompleteOCL.g:7109:59: (iv_ruleFirstPathElementCS= ruleFirstPathElementCS EOF )
            // InternalCompleteOCL.g:7110:2: iv_ruleFirstPathElementCS= ruleFirstPathElementCS EOF
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
    // InternalCompleteOCL.g:7116:1: ruleFirstPathElementCS returns [EObject current=null] : ( ( ruleUnrestrictedName ) ) ;
    public final EObject ruleFirstPathElementCS() throws RecognitionException {
        EObject current = null;


        	enterRule();

        try {
            // InternalCompleteOCL.g:7122:2: ( ( ( ruleUnrestrictedName ) ) )
            // InternalCompleteOCL.g:7123:2: ( ( ruleUnrestrictedName ) )
            {
            // InternalCompleteOCL.g:7123:2: ( ( ruleUnrestrictedName ) )
            // InternalCompleteOCL.g:7124:3: ( ruleUnrestrictedName )
            {
            // InternalCompleteOCL.g:7124:3: ( ruleUnrestrictedName )
            // InternalCompleteOCL.g:7125:4: ruleUnrestrictedName
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
    // InternalCompleteOCL.g:7145:1: entryRuleNextPathElementCS returns [EObject current=null] : iv_ruleNextPathElementCS= ruleNextPathElementCS EOF ;
    public final EObject entryRuleNextPathElementCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNextPathElementCS = null;


        try {
            // InternalCompleteOCL.g:7145:58: (iv_ruleNextPathElementCS= ruleNextPathElementCS EOF )
            // InternalCompleteOCL.g:7146:2: iv_ruleNextPathElementCS= ruleNextPathElementCS EOF
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
    // InternalCompleteOCL.g:7152:1: ruleNextPathElementCS returns [EObject current=null] : ( ( ruleUnreservedName ) ) ;
    public final EObject ruleNextPathElementCS() throws RecognitionException {
        EObject current = null;


        	enterRule();

        try {
            // InternalCompleteOCL.g:7158:2: ( ( ( ruleUnreservedName ) ) )
            // InternalCompleteOCL.g:7159:2: ( ( ruleUnreservedName ) )
            {
            // InternalCompleteOCL.g:7159:2: ( ( ruleUnreservedName ) )
            // InternalCompleteOCL.g:7160:3: ( ruleUnreservedName )
            {
            // InternalCompleteOCL.g:7160:3: ( ruleUnreservedName )
            // InternalCompleteOCL.g:7161:4: ruleUnreservedName
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
    // InternalCompleteOCL.g:7181:1: entryRuleTemplateBindingCS returns [EObject current=null] : iv_ruleTemplateBindingCS= ruleTemplateBindingCS EOF ;
    public final EObject entryRuleTemplateBindingCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTemplateBindingCS = null;


        try {
            // InternalCompleteOCL.g:7181:58: (iv_ruleTemplateBindingCS= ruleTemplateBindingCS EOF )
            // InternalCompleteOCL.g:7182:2: iv_ruleTemplateBindingCS= ruleTemplateBindingCS EOF
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
    // InternalCompleteOCL.g:7188:1: ruleTemplateBindingCS returns [EObject current=null] : ( ( (lv_ownedSubstitutions_0_0= ruleTemplateParameterSubstitutionCS ) ) (otherlv_1= ',' ( (lv_ownedSubstitutions_2_0= ruleTemplateParameterSubstitutionCS ) ) )* ( (lv_ownedMultiplicity_3_0= ruleMultiplicityCS ) )? ) ;
    public final EObject ruleTemplateBindingCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject lv_ownedSubstitutions_0_0 = null;

        EObject lv_ownedSubstitutions_2_0 = null;

        EObject lv_ownedMultiplicity_3_0 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:7194:2: ( ( ( (lv_ownedSubstitutions_0_0= ruleTemplateParameterSubstitutionCS ) ) (otherlv_1= ',' ( (lv_ownedSubstitutions_2_0= ruleTemplateParameterSubstitutionCS ) ) )* ( (lv_ownedMultiplicity_3_0= ruleMultiplicityCS ) )? ) )
            // InternalCompleteOCL.g:7195:2: ( ( (lv_ownedSubstitutions_0_0= ruleTemplateParameterSubstitutionCS ) ) (otherlv_1= ',' ( (lv_ownedSubstitutions_2_0= ruleTemplateParameterSubstitutionCS ) ) )* ( (lv_ownedMultiplicity_3_0= ruleMultiplicityCS ) )? )
            {
            // InternalCompleteOCL.g:7195:2: ( ( (lv_ownedSubstitutions_0_0= ruleTemplateParameterSubstitutionCS ) ) (otherlv_1= ',' ( (lv_ownedSubstitutions_2_0= ruleTemplateParameterSubstitutionCS ) ) )* ( (lv_ownedMultiplicity_3_0= ruleMultiplicityCS ) )? )
            // InternalCompleteOCL.g:7196:3: ( (lv_ownedSubstitutions_0_0= ruleTemplateParameterSubstitutionCS ) ) (otherlv_1= ',' ( (lv_ownedSubstitutions_2_0= ruleTemplateParameterSubstitutionCS ) ) )* ( (lv_ownedMultiplicity_3_0= ruleMultiplicityCS ) )?
            {
            // InternalCompleteOCL.g:7196:3: ( (lv_ownedSubstitutions_0_0= ruleTemplateParameterSubstitutionCS ) )
            // InternalCompleteOCL.g:7197:4: (lv_ownedSubstitutions_0_0= ruleTemplateParameterSubstitutionCS )
            {
            // InternalCompleteOCL.g:7197:4: (lv_ownedSubstitutions_0_0= ruleTemplateParameterSubstitutionCS )
            // InternalCompleteOCL.g:7198:5: lv_ownedSubstitutions_0_0= ruleTemplateParameterSubstitutionCS
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getTemplateBindingCSAccess().getOwnedSubstitutionsTemplateParameterSubstitutionCSParserRuleCall_0_0());

            }
            pushFollow(FollowSets000.FOLLOW_75);
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

            // InternalCompleteOCL.g:7215:3: (otherlv_1= ',' ( (lv_ownedSubstitutions_2_0= ruleTemplateParameterSubstitutionCS ) ) )*
            loop137:
            do {
                int alt137=2;
                int LA137_0 = input.LA(1);

                if ( (LA137_0==28) ) {
                    alt137=1;
                }


                switch (alt137) {
            	case 1 :
            	    // InternalCompleteOCL.g:7216:4: otherlv_1= ',' ( (lv_ownedSubstitutions_2_0= ruleTemplateParameterSubstitutionCS ) )
            	    {
            	    otherlv_1=(Token)match(input,28,FollowSets000.FOLLOW_76); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      				newLeafNode(otherlv_1, grammarAccess.getTemplateBindingCSAccess().getCommaKeyword_1_0());

            	    }
            	    // InternalCompleteOCL.g:7220:4: ( (lv_ownedSubstitutions_2_0= ruleTemplateParameterSubstitutionCS ) )
            	    // InternalCompleteOCL.g:7221:5: (lv_ownedSubstitutions_2_0= ruleTemplateParameterSubstitutionCS )
            	    {
            	    // InternalCompleteOCL.g:7221:5: (lv_ownedSubstitutions_2_0= ruleTemplateParameterSubstitutionCS )
            	    // InternalCompleteOCL.g:7222:6: lv_ownedSubstitutions_2_0= ruleTemplateParameterSubstitutionCS
            	    {
            	    if ( state.backtracking==0 ) {

            	      						newCompositeNode(grammarAccess.getTemplateBindingCSAccess().getOwnedSubstitutionsTemplateParameterSubstitutionCSParserRuleCall_1_1_0());

            	    }
            	    pushFollow(FollowSets000.FOLLOW_75);
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
            	    break loop137;
                }
            } while (true);

            // InternalCompleteOCL.g:7240:3: ( (lv_ownedMultiplicity_3_0= ruleMultiplicityCS ) )?
            int alt138=2;
            int LA138_0 = input.LA(1);

            if ( (LA138_0==93) ) {
                alt138=1;
            }
            switch (alt138) {
                case 1 :
                    // InternalCompleteOCL.g:7241:4: (lv_ownedMultiplicity_3_0= ruleMultiplicityCS )
                    {
                    // InternalCompleteOCL.g:7241:4: (lv_ownedMultiplicity_3_0= ruleMultiplicityCS )
                    // InternalCompleteOCL.g:7242:5: lv_ownedMultiplicity_3_0= ruleMultiplicityCS
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
    // InternalCompleteOCL.g:7263:1: entryRuleTemplateParameterSubstitutionCS returns [EObject current=null] : iv_ruleTemplateParameterSubstitutionCS= ruleTemplateParameterSubstitutionCS EOF ;
    public final EObject entryRuleTemplateParameterSubstitutionCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTemplateParameterSubstitutionCS = null;


        try {
            // InternalCompleteOCL.g:7263:72: (iv_ruleTemplateParameterSubstitutionCS= ruleTemplateParameterSubstitutionCS EOF )
            // InternalCompleteOCL.g:7264:2: iv_ruleTemplateParameterSubstitutionCS= ruleTemplateParameterSubstitutionCS EOF
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
    // InternalCompleteOCL.g:7270:1: ruleTemplateParameterSubstitutionCS returns [EObject current=null] : ( (lv_ownedActualParameter_0_0= ruleTypeRefCS ) ) ;
    public final EObject ruleTemplateParameterSubstitutionCS() throws RecognitionException {
        EObject current = null;

        EObject lv_ownedActualParameter_0_0 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:7276:2: ( ( (lv_ownedActualParameter_0_0= ruleTypeRefCS ) ) )
            // InternalCompleteOCL.g:7277:2: ( (lv_ownedActualParameter_0_0= ruleTypeRefCS ) )
            {
            // InternalCompleteOCL.g:7277:2: ( (lv_ownedActualParameter_0_0= ruleTypeRefCS ) )
            // InternalCompleteOCL.g:7278:3: (lv_ownedActualParameter_0_0= ruleTypeRefCS )
            {
            // InternalCompleteOCL.g:7278:3: (lv_ownedActualParameter_0_0= ruleTypeRefCS )
            // InternalCompleteOCL.g:7279:4: lv_ownedActualParameter_0_0= ruleTypeRefCS
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
    // InternalCompleteOCL.g:7299:1: entryRuleTypeParameterCS returns [EObject current=null] : iv_ruleTypeParameterCS= ruleTypeParameterCS EOF ;
    public final EObject entryRuleTypeParameterCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTypeParameterCS = null;


        try {
            // InternalCompleteOCL.g:7299:56: (iv_ruleTypeParameterCS= ruleTypeParameterCS EOF )
            // InternalCompleteOCL.g:7300:2: iv_ruleTypeParameterCS= ruleTypeParameterCS EOF
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
    // InternalCompleteOCL.g:7306:1: ruleTypeParameterCS returns [EObject current=null] : ( ( (lv_name_0_0= ruleUnrestrictedName ) ) (otherlv_1= 'extends' ( (lv_ownedExtends_2_0= ruleTypedRefCS ) ) (otherlv_3= '&&' ( (lv_ownedExtends_4_0= ruleTypedRefCS ) ) )* )? ) ;
    public final EObject ruleTypeParameterCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        AntlrDatatypeRuleToken lv_name_0_0 = null;

        EObject lv_ownedExtends_2_0 = null;

        EObject lv_ownedExtends_4_0 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:7312:2: ( ( ( (lv_name_0_0= ruleUnrestrictedName ) ) (otherlv_1= 'extends' ( (lv_ownedExtends_2_0= ruleTypedRefCS ) ) (otherlv_3= '&&' ( (lv_ownedExtends_4_0= ruleTypedRefCS ) ) )* )? ) )
            // InternalCompleteOCL.g:7313:2: ( ( (lv_name_0_0= ruleUnrestrictedName ) ) (otherlv_1= 'extends' ( (lv_ownedExtends_2_0= ruleTypedRefCS ) ) (otherlv_3= '&&' ( (lv_ownedExtends_4_0= ruleTypedRefCS ) ) )* )? )
            {
            // InternalCompleteOCL.g:7313:2: ( ( (lv_name_0_0= ruleUnrestrictedName ) ) (otherlv_1= 'extends' ( (lv_ownedExtends_2_0= ruleTypedRefCS ) ) (otherlv_3= '&&' ( (lv_ownedExtends_4_0= ruleTypedRefCS ) ) )* )? )
            // InternalCompleteOCL.g:7314:3: ( (lv_name_0_0= ruleUnrestrictedName ) ) (otherlv_1= 'extends' ( (lv_ownedExtends_2_0= ruleTypedRefCS ) ) (otherlv_3= '&&' ( (lv_ownedExtends_4_0= ruleTypedRefCS ) ) )* )?
            {
            // InternalCompleteOCL.g:7314:3: ( (lv_name_0_0= ruleUnrestrictedName ) )
            // InternalCompleteOCL.g:7315:4: (lv_name_0_0= ruleUnrestrictedName )
            {
            // InternalCompleteOCL.g:7315:4: (lv_name_0_0= ruleUnrestrictedName )
            // InternalCompleteOCL.g:7316:5: lv_name_0_0= ruleUnrestrictedName
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getTypeParameterCSAccess().getNameUnrestrictedNameParserRuleCall_0_0());

            }
            pushFollow(FollowSets000.FOLLOW_77);
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

            // InternalCompleteOCL.g:7333:3: (otherlv_1= 'extends' ( (lv_ownedExtends_2_0= ruleTypedRefCS ) ) (otherlv_3= '&&' ( (lv_ownedExtends_4_0= ruleTypedRefCS ) ) )* )?
            int alt140=2;
            int LA140_0 = input.LA(1);

            if ( (LA140_0==107) ) {
                alt140=1;
            }
            switch (alt140) {
                case 1 :
                    // InternalCompleteOCL.g:7334:4: otherlv_1= 'extends' ( (lv_ownedExtends_2_0= ruleTypedRefCS ) ) (otherlv_3= '&&' ( (lv_ownedExtends_4_0= ruleTypedRefCS ) ) )*
                    {
                    otherlv_1=(Token)match(input,107,FollowSets000.FOLLOW_21); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_1, grammarAccess.getTypeParameterCSAccess().getExtendsKeyword_1_0());

                    }
                    // InternalCompleteOCL.g:7338:4: ( (lv_ownedExtends_2_0= ruleTypedRefCS ) )
                    // InternalCompleteOCL.g:7339:5: (lv_ownedExtends_2_0= ruleTypedRefCS )
                    {
                    // InternalCompleteOCL.g:7339:5: (lv_ownedExtends_2_0= ruleTypedRefCS )
                    // InternalCompleteOCL.g:7340:6: lv_ownedExtends_2_0= ruleTypedRefCS
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getTypeParameterCSAccess().getOwnedExtendsTypedRefCSParserRuleCall_1_1_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_78);
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

                    // InternalCompleteOCL.g:7357:4: (otherlv_3= '&&' ( (lv_ownedExtends_4_0= ruleTypedRefCS ) ) )*
                    loop139:
                    do {
                        int alt139=2;
                        int LA139_0 = input.LA(1);

                        if ( (LA139_0==108) ) {
                            alt139=1;
                        }


                        switch (alt139) {
                    	case 1 :
                    	    // InternalCompleteOCL.g:7358:5: otherlv_3= '&&' ( (lv_ownedExtends_4_0= ruleTypedRefCS ) )
                    	    {
                    	    otherlv_3=(Token)match(input,108,FollowSets000.FOLLOW_21); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      					newLeafNode(otherlv_3, grammarAccess.getTypeParameterCSAccess().getAmpersandAmpersandKeyword_1_2_0());

                    	    }
                    	    // InternalCompleteOCL.g:7362:5: ( (lv_ownedExtends_4_0= ruleTypedRefCS ) )
                    	    // InternalCompleteOCL.g:7363:6: (lv_ownedExtends_4_0= ruleTypedRefCS )
                    	    {
                    	    // InternalCompleteOCL.g:7363:6: (lv_ownedExtends_4_0= ruleTypedRefCS )
                    	    // InternalCompleteOCL.g:7364:7: lv_ownedExtends_4_0= ruleTypedRefCS
                    	    {
                    	    if ( state.backtracking==0 ) {

                    	      							newCompositeNode(grammarAccess.getTypeParameterCSAccess().getOwnedExtendsTypedRefCSParserRuleCall_1_2_1_0());

                    	    }
                    	    pushFollow(FollowSets000.FOLLOW_78);
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
                    	    break loop139;
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
    // InternalCompleteOCL.g:7387:1: entryRuleTypeRefCS returns [EObject current=null] : iv_ruleTypeRefCS= ruleTypeRefCS EOF ;
    public final EObject entryRuleTypeRefCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTypeRefCS = null;


        try {
            // InternalCompleteOCL.g:7387:50: (iv_ruleTypeRefCS= ruleTypeRefCS EOF )
            // InternalCompleteOCL.g:7388:2: iv_ruleTypeRefCS= ruleTypeRefCS EOF
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
    // InternalCompleteOCL.g:7394:1: ruleTypeRefCS returns [EObject current=null] : (this_TypedRefCS_0= ruleTypedRefCS | this_WildcardTypeRefCS_1= ruleWildcardTypeRefCS ) ;
    public final EObject ruleTypeRefCS() throws RecognitionException {
        EObject current = null;

        EObject this_TypedRefCS_0 = null;

        EObject this_WildcardTypeRefCS_1 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:7400:2: ( (this_TypedRefCS_0= ruleTypedRefCS | this_WildcardTypeRefCS_1= ruleWildcardTypeRefCS ) )
            // InternalCompleteOCL.g:7401:2: (this_TypedRefCS_0= ruleTypedRefCS | this_WildcardTypeRefCS_1= ruleWildcardTypeRefCS )
            {
            // InternalCompleteOCL.g:7401:2: (this_TypedRefCS_0= ruleTypedRefCS | this_WildcardTypeRefCS_1= ruleWildcardTypeRefCS )
            int alt141=2;
            int LA141_0 = input.LA(1);

            if ( ((LA141_0>=RULE_SIMPLE_ID && LA141_0<=RULE_ESCAPED_ID)||(LA141_0>=30 && LA141_0<=32)||(LA141_0>=42 && LA141_0<=51)||(LA141_0>=73 && LA141_0<=74)||(LA141_0>=76 && LA141_0<=80)) ) {
                alt141=1;
            }
            else if ( (LA141_0==41) ) {
                alt141=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 141, 0, input);

                throw nvae;
            }
            switch (alt141) {
                case 1 :
                    // InternalCompleteOCL.g:7402:3: this_TypedRefCS_0= ruleTypedRefCS
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
                    // InternalCompleteOCL.g:7414:3: this_WildcardTypeRefCS_1= ruleWildcardTypeRefCS
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
    // InternalCompleteOCL.g:7429:1: entryRuleTypedTypeRefCS returns [EObject current=null] : iv_ruleTypedTypeRefCS= ruleTypedTypeRefCS EOF ;
    public final EObject entryRuleTypedTypeRefCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTypedTypeRefCS = null;


        try {
            // InternalCompleteOCL.g:7429:55: (iv_ruleTypedTypeRefCS= ruleTypedTypeRefCS EOF )
            // InternalCompleteOCL.g:7430:2: iv_ruleTypedTypeRefCS= ruleTypedTypeRefCS EOF
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
    // InternalCompleteOCL.g:7436:1: ruleTypedTypeRefCS returns [EObject current=null] : ( ( (lv_ownedPathName_0_0= rulePathNameCS ) ) (otherlv_1= '(' ( (lv_ownedBinding_2_0= ruleTemplateBindingCS ) ) otherlv_3= ')' )? ) ;
    public final EObject ruleTypedTypeRefCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_ownedPathName_0_0 = null;

        EObject lv_ownedBinding_2_0 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:7442:2: ( ( ( (lv_ownedPathName_0_0= rulePathNameCS ) ) (otherlv_1= '(' ( (lv_ownedBinding_2_0= ruleTemplateBindingCS ) ) otherlv_3= ')' )? ) )
            // InternalCompleteOCL.g:7443:2: ( ( (lv_ownedPathName_0_0= rulePathNameCS ) ) (otherlv_1= '(' ( (lv_ownedBinding_2_0= ruleTemplateBindingCS ) ) otherlv_3= ')' )? )
            {
            // InternalCompleteOCL.g:7443:2: ( ( (lv_ownedPathName_0_0= rulePathNameCS ) ) (otherlv_1= '(' ( (lv_ownedBinding_2_0= ruleTemplateBindingCS ) ) otherlv_3= ')' )? )
            // InternalCompleteOCL.g:7444:3: ( (lv_ownedPathName_0_0= rulePathNameCS ) ) (otherlv_1= '(' ( (lv_ownedBinding_2_0= ruleTemplateBindingCS ) ) otherlv_3= ')' )?
            {
            // InternalCompleteOCL.g:7444:3: ( (lv_ownedPathName_0_0= rulePathNameCS ) )
            // InternalCompleteOCL.g:7445:4: (lv_ownedPathName_0_0= rulePathNameCS )
            {
            // InternalCompleteOCL.g:7445:4: (lv_ownedPathName_0_0= rulePathNameCS )
            // InternalCompleteOCL.g:7446:5: lv_ownedPathName_0_0= rulePathNameCS
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getTypedTypeRefCSAccess().getOwnedPathNamePathNameCSParserRuleCall_0_0());

            }
            pushFollow(FollowSets000.FOLLOW_33);
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

            // InternalCompleteOCL.g:7463:3: (otherlv_1= '(' ( (lv_ownedBinding_2_0= ruleTemplateBindingCS ) ) otherlv_3= ')' )?
            int alt142=2;
            int LA142_0 = input.LA(1);

            if ( (LA142_0==21) ) {
                alt142=1;
            }
            switch (alt142) {
                case 1 :
                    // InternalCompleteOCL.g:7464:4: otherlv_1= '(' ( (lv_ownedBinding_2_0= ruleTemplateBindingCS ) ) otherlv_3= ')'
                    {
                    otherlv_1=(Token)match(input,21,FollowSets000.FOLLOW_76); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_1, grammarAccess.getTypedTypeRefCSAccess().getLeftParenthesisKeyword_1_0());

                    }
                    // InternalCompleteOCL.g:7468:4: ( (lv_ownedBinding_2_0= ruleTemplateBindingCS ) )
                    // InternalCompleteOCL.g:7469:5: (lv_ownedBinding_2_0= ruleTemplateBindingCS )
                    {
                    // InternalCompleteOCL.g:7469:5: (lv_ownedBinding_2_0= ruleTemplateBindingCS )
                    // InternalCompleteOCL.g:7470:6: lv_ownedBinding_2_0= ruleTemplateBindingCS
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
    // InternalCompleteOCL.g:7496:1: entryRuleWildcardTypeRefCS returns [EObject current=null] : iv_ruleWildcardTypeRefCS= ruleWildcardTypeRefCS EOF ;
    public final EObject entryRuleWildcardTypeRefCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleWildcardTypeRefCS = null;


        try {
            // InternalCompleteOCL.g:7496:58: (iv_ruleWildcardTypeRefCS= ruleWildcardTypeRefCS EOF )
            // InternalCompleteOCL.g:7497:2: iv_ruleWildcardTypeRefCS= ruleWildcardTypeRefCS EOF
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
    // InternalCompleteOCL.g:7503:1: ruleWildcardTypeRefCS returns [EObject current=null] : ( () otherlv_1= '?' (otherlv_2= 'extends' ( (lv_ownedExtends_3_0= ruleTypedRefCS ) ) )? ) ;
    public final EObject ruleWildcardTypeRefCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        EObject lv_ownedExtends_3_0 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:7509:2: ( ( () otherlv_1= '?' (otherlv_2= 'extends' ( (lv_ownedExtends_3_0= ruleTypedRefCS ) ) )? ) )
            // InternalCompleteOCL.g:7510:2: ( () otherlv_1= '?' (otherlv_2= 'extends' ( (lv_ownedExtends_3_0= ruleTypedRefCS ) ) )? )
            {
            // InternalCompleteOCL.g:7510:2: ( () otherlv_1= '?' (otherlv_2= 'extends' ( (lv_ownedExtends_3_0= ruleTypedRefCS ) ) )? )
            // InternalCompleteOCL.g:7511:3: () otherlv_1= '?' (otherlv_2= 'extends' ( (lv_ownedExtends_3_0= ruleTypedRefCS ) ) )?
            {
            // InternalCompleteOCL.g:7511:3: ()
            // InternalCompleteOCL.g:7512:4:
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

            otherlv_1=(Token)match(input,41,FollowSets000.FOLLOW_77); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getWildcardTypeRefCSAccess().getQuestionMarkKeyword_1());

            }
            // InternalCompleteOCL.g:7525:3: (otherlv_2= 'extends' ( (lv_ownedExtends_3_0= ruleTypedRefCS ) ) )?
            int alt143=2;
            int LA143_0 = input.LA(1);

            if ( (LA143_0==107) ) {
                alt143=1;
            }
            switch (alt143) {
                case 1 :
                    // InternalCompleteOCL.g:7526:4: otherlv_2= 'extends' ( (lv_ownedExtends_3_0= ruleTypedRefCS ) )
                    {
                    otherlv_2=(Token)match(input,107,FollowSets000.FOLLOW_21); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_2, grammarAccess.getWildcardTypeRefCSAccess().getExtendsKeyword_2_0());

                    }
                    // InternalCompleteOCL.g:7530:4: ( (lv_ownedExtends_3_0= ruleTypedRefCS ) )
                    // InternalCompleteOCL.g:7531:5: (lv_ownedExtends_3_0= ruleTypedRefCS )
                    {
                    // InternalCompleteOCL.g:7531:5: (lv_ownedExtends_3_0= ruleTypedRefCS )
                    // InternalCompleteOCL.g:7532:6: lv_ownedExtends_3_0= ruleTypedRefCS
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
    // InternalCompleteOCL.g:7554:1: entryRuleID returns [String current=null] : iv_ruleID= ruleID EOF ;
    public final String entryRuleID() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleID = null;


        try {
            // InternalCompleteOCL.g:7554:42: (iv_ruleID= ruleID EOF )
            // InternalCompleteOCL.g:7555:2: iv_ruleID= ruleID EOF
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
    // InternalCompleteOCL.g:7561:1: ruleID returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_SIMPLE_ID_0= RULE_SIMPLE_ID | this_ESCAPED_ID_1= RULE_ESCAPED_ID ) ;
    public final AntlrDatatypeRuleToken ruleID() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_SIMPLE_ID_0=null;
        Token this_ESCAPED_ID_1=null;


        	enterRule();

        try {
            // InternalCompleteOCL.g:7567:2: ( (this_SIMPLE_ID_0= RULE_SIMPLE_ID | this_ESCAPED_ID_1= RULE_ESCAPED_ID ) )
            // InternalCompleteOCL.g:7568:2: (this_SIMPLE_ID_0= RULE_SIMPLE_ID | this_ESCAPED_ID_1= RULE_ESCAPED_ID )
            {
            // InternalCompleteOCL.g:7568:2: (this_SIMPLE_ID_0= RULE_SIMPLE_ID | this_ESCAPED_ID_1= RULE_ESCAPED_ID )
            int alt144=2;
            int LA144_0 = input.LA(1);

            if ( (LA144_0==RULE_SIMPLE_ID) ) {
                alt144=1;
            }
            else if ( (LA144_0==RULE_ESCAPED_ID) ) {
                alt144=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 144, 0, input);

                throw nvae;
            }
            switch (alt144) {
                case 1 :
                    // InternalCompleteOCL.g:7569:3: this_SIMPLE_ID_0= RULE_SIMPLE_ID
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
                    // InternalCompleteOCL.g:7577:3: this_ESCAPED_ID_1= RULE_ESCAPED_ID
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
    // InternalCompleteOCL.g:7588:1: entryRuleIdentifier returns [String current=null] : iv_ruleIdentifier= ruleIdentifier EOF ;
    public final String entryRuleIdentifier() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleIdentifier = null;


        try {
            // InternalCompleteOCL.g:7588:50: (iv_ruleIdentifier= ruleIdentifier EOF )
            // InternalCompleteOCL.g:7589:2: iv_ruleIdentifier= ruleIdentifier EOF
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
    // InternalCompleteOCL.g:7595:1: ruleIdentifier returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_ID_0= ruleID ;
    public final AntlrDatatypeRuleToken ruleIdentifier() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        AntlrDatatypeRuleToken this_ID_0 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:7601:2: (this_ID_0= ruleID )
            // InternalCompleteOCL.g:7602:2: this_ID_0= ruleID
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
    // InternalCompleteOCL.g:7615:1: entryRuleLOWER returns [String current=null] : iv_ruleLOWER= ruleLOWER EOF ;
    public final String entryRuleLOWER() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleLOWER = null;


        try {
            // InternalCompleteOCL.g:7615:45: (iv_ruleLOWER= ruleLOWER EOF )
            // InternalCompleteOCL.g:7616:2: iv_ruleLOWER= ruleLOWER EOF
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
    // InternalCompleteOCL.g:7622:1: ruleLOWER returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_INT_0= RULE_INT ;
    public final AntlrDatatypeRuleToken ruleLOWER() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_INT_0=null;


        	enterRule();

        try {
            // InternalCompleteOCL.g:7628:2: (this_INT_0= RULE_INT )
            // InternalCompleteOCL.g:7629:2: this_INT_0= RULE_INT
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
    // InternalCompleteOCL.g:7639:1: entryRuleNUMBER_LITERAL returns [String current=null] : iv_ruleNUMBER_LITERAL= ruleNUMBER_LITERAL EOF ;
    public final String entryRuleNUMBER_LITERAL() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleNUMBER_LITERAL = null;


        try {
            // InternalCompleteOCL.g:7639:54: (iv_ruleNUMBER_LITERAL= ruleNUMBER_LITERAL EOF )
            // InternalCompleteOCL.g:7640:2: iv_ruleNUMBER_LITERAL= ruleNUMBER_LITERAL EOF
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
    // InternalCompleteOCL.g:7646:1: ruleNUMBER_LITERAL returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_INT_0= RULE_INT ;
    public final AntlrDatatypeRuleToken ruleNUMBER_LITERAL() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_INT_0=null;


        	enterRule();

        try {
            // InternalCompleteOCL.g:7652:2: (this_INT_0= RULE_INT )
            // InternalCompleteOCL.g:7653:2: this_INT_0= RULE_INT
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
    // InternalCompleteOCL.g:7663:1: entryRuleStringLiteral returns [String current=null] : iv_ruleStringLiteral= ruleStringLiteral EOF ;
    public final String entryRuleStringLiteral() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleStringLiteral = null;


        try {
            // InternalCompleteOCL.g:7663:53: (iv_ruleStringLiteral= ruleStringLiteral EOF )
            // InternalCompleteOCL.g:7664:2: iv_ruleStringLiteral= ruleStringLiteral EOF
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
    // InternalCompleteOCL.g:7670:1: ruleStringLiteral returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_SINGLE_QUOTED_STRING_0= RULE_SINGLE_QUOTED_STRING ;
    public final AntlrDatatypeRuleToken ruleStringLiteral() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_SINGLE_QUOTED_STRING_0=null;


        	enterRule();

        try {
            // InternalCompleteOCL.g:7676:2: (this_SINGLE_QUOTED_STRING_0= RULE_SINGLE_QUOTED_STRING )
            // InternalCompleteOCL.g:7677:2: this_SINGLE_QUOTED_STRING_0= RULE_SINGLE_QUOTED_STRING
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
    // InternalCompleteOCL.g:7687:1: entryRuleUPPER returns [String current=null] : iv_ruleUPPER= ruleUPPER EOF ;
    public final String entryRuleUPPER() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleUPPER = null;


        try {
            // InternalCompleteOCL.g:7687:45: (iv_ruleUPPER= ruleUPPER EOF )
            // InternalCompleteOCL.g:7688:2: iv_ruleUPPER= ruleUPPER EOF
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
    // InternalCompleteOCL.g:7694:1: ruleUPPER returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_INT_0= RULE_INT | kw= '*' ) ;
    public final AntlrDatatypeRuleToken ruleUPPER() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_INT_0=null;
        Token kw=null;


        	enterRule();

        try {
            // InternalCompleteOCL.g:7700:2: ( (this_INT_0= RULE_INT | kw= '*' ) )
            // InternalCompleteOCL.g:7701:2: (this_INT_0= RULE_INT | kw= '*' )
            {
            // InternalCompleteOCL.g:7701:2: (this_INT_0= RULE_INT | kw= '*' )
            int alt145=2;
            int LA145_0 = input.LA(1);

            if ( (LA145_0==RULE_INT) ) {
                alt145=1;
            }
            else if ( (LA145_0==55) ) {
                alt145=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 145, 0, input);

                throw nvae;
            }
            switch (alt145) {
                case 1 :
                    // InternalCompleteOCL.g:7702:3: this_INT_0= RULE_INT
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
                    // InternalCompleteOCL.g:7710:3: kw= '*'
                    {
                    kw=(Token)match(input,55,FollowSets000.FOLLOW_2); if (state.failed) return current;
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
    // InternalCompleteOCL.g:7719:1: entryRuleURI returns [String current=null] : iv_ruleURI= ruleURI EOF ;
    public final String entryRuleURI() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleURI = null;


        try {
            // InternalCompleteOCL.g:7719:43: (iv_ruleURI= ruleURI EOF )
            // InternalCompleteOCL.g:7720:2: iv_ruleURI= ruleURI EOF
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
    // InternalCompleteOCL.g:7726:1: ruleURI returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_SINGLE_QUOTED_STRING_0= RULE_SINGLE_QUOTED_STRING ;
    public final AntlrDatatypeRuleToken ruleURI() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_SINGLE_QUOTED_STRING_0=null;


        	enterRule();

        try {
            // InternalCompleteOCL.g:7732:2: (this_SINGLE_QUOTED_STRING_0= RULE_SINGLE_QUOTED_STRING )
            // InternalCompleteOCL.g:7733:2: this_SINGLE_QUOTED_STRING_0= RULE_SINGLE_QUOTED_STRING
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

    // $ANTLR start synpred15_InternalCompleteOCL
    public final void synpred15_InternalCompleteOCL_fragment() throws RecognitionException {
        EObject this_PropertyContextDeclCS_0 = null;


        // InternalCompleteOCL.g:612:3: (this_PropertyContextDeclCS_0= rulePropertyContextDeclCS )
        // InternalCompleteOCL.g:612:3: this_PropertyContextDeclCS_0= rulePropertyContextDeclCS
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
    // $ANTLR end synpred15_InternalCompleteOCL

    // $ANTLR start synpred16_InternalCompleteOCL
    public final void synpred16_InternalCompleteOCL_fragment() throws RecognitionException {
        EObject this_ClassifierContextDeclCS_1 = null;


        // InternalCompleteOCL.g:624:3: (this_ClassifierContextDeclCS_1= ruleClassifierContextDeclCS )
        // InternalCompleteOCL.g:624:3: this_ClassifierContextDeclCS_1= ruleClassifierContextDeclCS
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
    // $ANTLR end synpred16_InternalCompleteOCL

    // $ANTLR start synpred40_InternalCompleteOCL
    public final void synpred40_InternalCompleteOCL_fragment() throws RecognitionException {
        EObject lv_ownedDefaultExpressions_4_0 = null;


        // InternalCompleteOCL.g:1564:4: ( ( (lv_ownedDefaultExpressions_4_0= rulederiveSpecificationCS ) ) )
        // InternalCompleteOCL.g:1564:4: ( (lv_ownedDefaultExpressions_4_0= rulederiveSpecificationCS ) )
        {
        // InternalCompleteOCL.g:1564:4: ( (lv_ownedDefaultExpressions_4_0= rulederiveSpecificationCS ) )
        // InternalCompleteOCL.g:1565:5: (lv_ownedDefaultExpressions_4_0= rulederiveSpecificationCS )
        {
        // InternalCompleteOCL.g:1565:5: (lv_ownedDefaultExpressions_4_0= rulederiveSpecificationCS )
        // InternalCompleteOCL.g:1566:6: lv_ownedDefaultExpressions_4_0= rulederiveSpecificationCS
        {
        if ( state.backtracking==0 ) {

          						newCompositeNode(grammarAccess.getPropertyContextDeclCSAccess().getOwnedDefaultExpressionsDeriveSpecificationCSParserRuleCall_4_0_0());

        }
        pushFollow(FollowSets000.FOLLOW_2);
        lv_ownedDefaultExpressions_4_0=rulederiveSpecificationCS();

        state._fsp--;
        if (state.failed) return ;

        }


        }


        }
    }
    // $ANTLR end synpred40_InternalCompleteOCL

    // $ANTLR start synpred41_InternalCompleteOCL
    public final void synpred41_InternalCompleteOCL_fragment() throws RecognitionException {
        EObject lv_ownedDefaultExpressions_5_0 = null;


        // InternalCompleteOCL.g:1584:4: ( ( (lv_ownedDefaultExpressions_5_0= ruleinitSpecificationCS ) ) )
        // InternalCompleteOCL.g:1584:4: ( (lv_ownedDefaultExpressions_5_0= ruleinitSpecificationCS ) )
        {
        // InternalCompleteOCL.g:1584:4: ( (lv_ownedDefaultExpressions_5_0= ruleinitSpecificationCS ) )
        // InternalCompleteOCL.g:1585:5: (lv_ownedDefaultExpressions_5_0= ruleinitSpecificationCS )
        {
        // InternalCompleteOCL.g:1585:5: (lv_ownedDefaultExpressions_5_0= ruleinitSpecificationCS )
        // InternalCompleteOCL.g:1586:6: lv_ownedDefaultExpressions_5_0= ruleinitSpecificationCS
        {
        if ( state.backtracking==0 ) {

          						newCompositeNode(grammarAccess.getPropertyContextDeclCSAccess().getOwnedDefaultExpressionsInitSpecificationCSParserRuleCall_4_1_0());

        }
        pushFollow(FollowSets000.FOLLOW_2);
        lv_ownedDefaultExpressions_5_0=ruleinitSpecificationCS();

        state._fsp--;
        if (state.failed) return ;

        }


        }


        }
    }
    // $ANTLR end synpred41_InternalCompleteOCL

    // $ANTLR start synpred134_InternalCompleteOCL
    public final void synpred134_InternalCompleteOCL_fragment() throws RecognitionException {
        EObject this_TypeLiteralCS_1 = null;


        // InternalCompleteOCL.g:4639:3: (this_TypeLiteralCS_1= ruleTypeLiteralCS )
        // InternalCompleteOCL.g:4639:3: this_TypeLiteralCS_1= ruleTypeLiteralCS
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
    // $ANTLR end synpred134_InternalCompleteOCL

    // $ANTLR start synpred137_InternalCompleteOCL
    public final void synpred137_InternalCompleteOCL_fragment() throws RecognitionException {
        EObject this_PrefixedPrimaryExpCS_0 = null;

        AntlrDatatypeRuleToken lv_name_2_0 = null;

        EObject lv_ownedRight_3_0 = null;


        // InternalCompleteOCL.g:4730:3: ( (this_PrefixedPrimaryExpCS_0= rulePrefixedPrimaryExpCS ( () ( (lv_name_2_0= ruleBinaryOperatorName ) ) ( (lv_ownedRight_3_0= ruleExpCS ) ) )? ) )
        // InternalCompleteOCL.g:4730:3: (this_PrefixedPrimaryExpCS_0= rulePrefixedPrimaryExpCS ( () ( (lv_name_2_0= ruleBinaryOperatorName ) ) ( (lv_ownedRight_3_0= ruleExpCS ) ) )? )
        {
        // InternalCompleteOCL.g:4730:3: (this_PrefixedPrimaryExpCS_0= rulePrefixedPrimaryExpCS ( () ( (lv_name_2_0= ruleBinaryOperatorName ) ) ( (lv_ownedRight_3_0= ruleExpCS ) ) )? )
        // InternalCompleteOCL.g:4731:4: this_PrefixedPrimaryExpCS_0= rulePrefixedPrimaryExpCS ( () ( (lv_name_2_0= ruleBinaryOperatorName ) ) ( (lv_ownedRight_3_0= ruleExpCS ) ) )?
        {
        if ( state.backtracking==0 ) {

          				/* */

        }
        pushFollow(FollowSets000.FOLLOW_50);
        this_PrefixedPrimaryExpCS_0=rulePrefixedPrimaryExpCS();

        state._fsp--;
        if (state.failed) return ;
        // InternalCompleteOCL.g:4742:4: ( () ( (lv_name_2_0= ruleBinaryOperatorName ) ) ( (lv_ownedRight_3_0= ruleExpCS ) ) )?
        int alt165=2;
        int LA165_0 = input.LA(1);

        if ( ((LA165_0>=17 && LA165_0<=18)||LA165_0==29||(LA165_0>=39 && LA165_0<=40)||LA165_0==52||(LA165_0>=55 && LA165_0<=72)) ) {
            alt165=1;
        }
        switch (alt165) {
            case 1 :
                // InternalCompleteOCL.g:4743:5: () ( (lv_name_2_0= ruleBinaryOperatorName ) ) ( (lv_ownedRight_3_0= ruleExpCS ) )
                {
                // InternalCompleteOCL.g:4743:5: ()
                // InternalCompleteOCL.g:4744:6:
                {
                if ( state.backtracking==0 ) {

                  						/* */

                }

                }

                // InternalCompleteOCL.g:4753:5: ( (lv_name_2_0= ruleBinaryOperatorName ) )
                // InternalCompleteOCL.g:4754:6: (lv_name_2_0= ruleBinaryOperatorName )
                {
                // InternalCompleteOCL.g:4754:6: (lv_name_2_0= ruleBinaryOperatorName )
                // InternalCompleteOCL.g:4755:7: lv_name_2_0= ruleBinaryOperatorName
                {
                if ( state.backtracking==0 ) {

                  							newCompositeNode(grammarAccess.getExpCSAccess().getNameBinaryOperatorNameParserRuleCall_0_1_1_0());

                }
                pushFollow(FollowSets000.FOLLOW_30);
                lv_name_2_0=ruleBinaryOperatorName();

                state._fsp--;
                if (state.failed) return ;

                }


                }

                // InternalCompleteOCL.g:4772:5: ( (lv_ownedRight_3_0= ruleExpCS ) )
                // InternalCompleteOCL.g:4773:6: (lv_ownedRight_3_0= ruleExpCS )
                {
                // InternalCompleteOCL.g:4773:6: (lv_ownedRight_3_0= ruleExpCS )
                // InternalCompleteOCL.g:4774:7: lv_ownedRight_3_0= ruleExpCS
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
    // $ANTLR end synpred137_InternalCompleteOCL

    // $ANTLR start synpred144_InternalCompleteOCL
    public final void synpred144_InternalCompleteOCL_fragment() throws RecognitionException {
        EObject this_TupleLiteralExpCS_4 = null;


        // InternalCompleteOCL.g:5034:3: (this_TupleLiteralExpCS_4= ruleTupleLiteralExpCS )
        // InternalCompleteOCL.g:5034:3: this_TupleLiteralExpCS_4= ruleTupleLiteralExpCS
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
    // $ANTLR end synpred144_InternalCompleteOCL

    // $ANTLR start synpred145_InternalCompleteOCL
    public final void synpred145_InternalCompleteOCL_fragment() throws RecognitionException {
        EObject this_MapLiteralExpCS_5 = null;


        // InternalCompleteOCL.g:5046:3: (this_MapLiteralExpCS_5= ruleMapLiteralExpCS )
        // InternalCompleteOCL.g:5046:3: this_MapLiteralExpCS_5= ruleMapLiteralExpCS
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
    // $ANTLR end synpred145_InternalCompleteOCL

    // $ANTLR start synpred146_InternalCompleteOCL
    public final void synpred146_InternalCompleteOCL_fragment() throws RecognitionException {
        EObject this_CollectionLiteralExpCS_6 = null;


        // InternalCompleteOCL.g:5058:3: (this_CollectionLiteralExpCS_6= ruleCollectionLiteralExpCS )
        // InternalCompleteOCL.g:5058:3: this_CollectionLiteralExpCS_6= ruleCollectionLiteralExpCS
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
    // $ANTLR end synpred146_InternalCompleteOCL

    // $ANTLR start synpred148_InternalCompleteOCL
    public final void synpred148_InternalCompleteOCL_fragment() throws RecognitionException {
        EObject this_TypeLiteralExpCS_8 = null;


        // InternalCompleteOCL.g:5082:3: (this_TypeLiteralExpCS_8= ruleTypeLiteralExpCS )
        // InternalCompleteOCL.g:5082:3: this_TypeLiteralExpCS_8= ruleTypeLiteralExpCS
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
    // $ANTLR end synpred148_InternalCompleteOCL

    // $ANTLR start synpred162_InternalCompleteOCL
    public final void synpred162_InternalCompleteOCL_fragment() throws RecognitionException {
        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_ownedCoIterator_3_0 = null;

        EObject lv_ownedInitExpression_5_0 = null;


        // InternalCompleteOCL.g:5525:5: ( ( (otherlv_1= 'with' | otherlv_2= '<-' ) ( (lv_ownedCoIterator_3_0= ruleCoIteratorVariableCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? ) )
        // InternalCompleteOCL.g:5525:5: ( (otherlv_1= 'with' | otherlv_2= '<-' ) ( (lv_ownedCoIterator_3_0= ruleCoIteratorVariableCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? )
        {
        // InternalCompleteOCL.g:5525:5: ( (otherlv_1= 'with' | otherlv_2= '<-' ) ( (lv_ownedCoIterator_3_0= ruleCoIteratorVariableCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? )
        // InternalCompleteOCL.g:5526:6: (otherlv_1= 'with' | otherlv_2= '<-' ) ( (lv_ownedCoIterator_3_0= ruleCoIteratorVariableCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )?
        {
        // InternalCompleteOCL.g:5526:6: (otherlv_1= 'with' | otherlv_2= '<-' )
        int alt170=2;
        int LA170_0 = input.LA(1);

        if ( (LA170_0==86) ) {
            alt170=1;
        }
        else if ( (LA170_0==87) ) {
            alt170=2;
        }
        else {
            if (state.backtracking>0) {state.failed=true; return ;}
            NoViableAltException nvae =
                new NoViableAltException("", 170, 0, input);

            throw nvae;
        }
        switch (alt170) {
            case 1 :
                // InternalCompleteOCL.g:5527:7: otherlv_1= 'with'
                {
                otherlv_1=(Token)match(input,86,FollowSets000.FOLLOW_15); if (state.failed) return ;

                }
                break;
            case 2 :
                // InternalCompleteOCL.g:5532:7: otherlv_2= '<-'
                {
                otherlv_2=(Token)match(input,87,FollowSets000.FOLLOW_15); if (state.failed) return ;

                }
                break;

        }

        // InternalCompleteOCL.g:5537:6: ( (lv_ownedCoIterator_3_0= ruleCoIteratorVariableCS ) )
        // InternalCompleteOCL.g:5538:7: (lv_ownedCoIterator_3_0= ruleCoIteratorVariableCS )
        {
        // InternalCompleteOCL.g:5538:7: (lv_ownedCoIterator_3_0= ruleCoIteratorVariableCS )
        // InternalCompleteOCL.g:5539:8: lv_ownedCoIterator_3_0= ruleCoIteratorVariableCS
        {
        if ( state.backtracking==0 ) {

          								newCompositeNode(grammarAccess.getNavigatingArgCSAccess().getOwnedCoIteratorCoIteratorVariableCSParserRuleCall_0_1_0_1_0());

        }
        pushFollow(FollowSets000.FOLLOW_60);
        lv_ownedCoIterator_3_0=ruleCoIteratorVariableCS();

        state._fsp--;
        if (state.failed) return ;

        }


        }

        // InternalCompleteOCL.g:5556:6: (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )?
        int alt171=2;
        int LA171_0 = input.LA(1);

        if ( (LA171_0==29) ) {
            alt171=1;
        }
        switch (alt171) {
            case 1 :
                // InternalCompleteOCL.g:5557:7: otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) )
                {
                otherlv_4=(Token)match(input,29,FollowSets000.FOLLOW_30); if (state.failed) return ;
                // InternalCompleteOCL.g:5561:7: ( (lv_ownedInitExpression_5_0= ruleExpCS ) )
                // InternalCompleteOCL.g:5562:8: (lv_ownedInitExpression_5_0= ruleExpCS )
                {
                // InternalCompleteOCL.g:5562:8: (lv_ownedInitExpression_5_0= ruleExpCS )
                // InternalCompleteOCL.g:5563:9: lv_ownedInitExpression_5_0= ruleExpCS
                {
                if ( state.backtracking==0 ) {

                  									newCompositeNode(grammarAccess.getNavigatingArgCSAccess().getOwnedInitExpressionExpCSParserRuleCall_0_1_0_2_1_0());

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
    // $ANTLR end synpred162_InternalCompleteOCL

    // $ANTLR start synpred166_InternalCompleteOCL
    public final void synpred166_InternalCompleteOCL_fragment() throws RecognitionException {
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token otherlv_9=null;
        Token otherlv_11=null;
        EObject lv_ownedType_7_0 = null;

        EObject lv_ownedCoIterator_10_0 = null;

        EObject lv_ownedInitExpression_12_0 = null;


        // InternalCompleteOCL.g:5583:5: ( (otherlv_6= ':' ( (lv_ownedType_7_0= ruleTypeExpCS ) ) ( (otherlv_8= 'with' | otherlv_9= '<-' ) ( (lv_ownedCoIterator_10_0= ruleCoIteratorVariableCS ) ) )? (otherlv_11= '=' ( (lv_ownedInitExpression_12_0= ruleExpCS ) ) )? ) )
        // InternalCompleteOCL.g:5583:5: (otherlv_6= ':' ( (lv_ownedType_7_0= ruleTypeExpCS ) ) ( (otherlv_8= 'with' | otherlv_9= '<-' ) ( (lv_ownedCoIterator_10_0= ruleCoIteratorVariableCS ) ) )? (otherlv_11= '=' ( (lv_ownedInitExpression_12_0= ruleExpCS ) ) )? )
        {
        // InternalCompleteOCL.g:5583:5: (otherlv_6= ':' ( (lv_ownedType_7_0= ruleTypeExpCS ) ) ( (otherlv_8= 'with' | otherlv_9= '<-' ) ( (lv_ownedCoIterator_10_0= ruleCoIteratorVariableCS ) ) )? (otherlv_11= '=' ( (lv_ownedInitExpression_12_0= ruleExpCS ) ) )? )
        // InternalCompleteOCL.g:5584:6: otherlv_6= ':' ( (lv_ownedType_7_0= ruleTypeExpCS ) ) ( (otherlv_8= 'with' | otherlv_9= '<-' ) ( (lv_ownedCoIterator_10_0= ruleCoIteratorVariableCS ) ) )? (otherlv_11= '=' ( (lv_ownedInitExpression_12_0= ruleExpCS ) ) )?
        {
        otherlv_6=(Token)match(input,23,FollowSets000.FOLLOW_21); if (state.failed) return ;
        // InternalCompleteOCL.g:5588:6: ( (lv_ownedType_7_0= ruleTypeExpCS ) )
        // InternalCompleteOCL.g:5589:7: (lv_ownedType_7_0= ruleTypeExpCS )
        {
        // InternalCompleteOCL.g:5589:7: (lv_ownedType_7_0= ruleTypeExpCS )
        // InternalCompleteOCL.g:5590:8: lv_ownedType_7_0= ruleTypeExpCS
        {
        if ( state.backtracking==0 ) {

          								newCompositeNode(grammarAccess.getNavigatingArgCSAccess().getOwnedTypeTypeExpCSParserRuleCall_0_1_1_1_0());

        }
        pushFollow(FollowSets000.FOLLOW_61);
        lv_ownedType_7_0=ruleTypeExpCS();

        state._fsp--;
        if (state.failed) return ;

        }


        }

        // InternalCompleteOCL.g:5607:6: ( (otherlv_8= 'with' | otherlv_9= '<-' ) ( (lv_ownedCoIterator_10_0= ruleCoIteratorVariableCS ) ) )?
        int alt174=2;
        int LA174_0 = input.LA(1);

        if ( ((LA174_0>=86 && LA174_0<=87)) ) {
            alt174=1;
        }
        switch (alt174) {
            case 1 :
                // InternalCompleteOCL.g:5608:7: (otherlv_8= 'with' | otherlv_9= '<-' ) ( (lv_ownedCoIterator_10_0= ruleCoIteratorVariableCS ) )
                {
                // InternalCompleteOCL.g:5608:7: (otherlv_8= 'with' | otherlv_9= '<-' )
                int alt173=2;
                int LA173_0 = input.LA(1);

                if ( (LA173_0==86) ) {
                    alt173=1;
                }
                else if ( (LA173_0==87) ) {
                    alt173=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 173, 0, input);

                    throw nvae;
                }
                switch (alt173) {
                    case 1 :
                        // InternalCompleteOCL.g:5609:8: otherlv_8= 'with'
                        {
                        otherlv_8=(Token)match(input,86,FollowSets000.FOLLOW_15); if (state.failed) return ;

                        }
                        break;
                    case 2 :
                        // InternalCompleteOCL.g:5614:8: otherlv_9= '<-'
                        {
                        otherlv_9=(Token)match(input,87,FollowSets000.FOLLOW_15); if (state.failed) return ;

                        }
                        break;

                }

                // InternalCompleteOCL.g:5619:7: ( (lv_ownedCoIterator_10_0= ruleCoIteratorVariableCS ) )
                // InternalCompleteOCL.g:5620:8: (lv_ownedCoIterator_10_0= ruleCoIteratorVariableCS )
                {
                // InternalCompleteOCL.g:5620:8: (lv_ownedCoIterator_10_0= ruleCoIteratorVariableCS )
                // InternalCompleteOCL.g:5621:9: lv_ownedCoIterator_10_0= ruleCoIteratorVariableCS
                {
                if ( state.backtracking==0 ) {

                  									newCompositeNode(grammarAccess.getNavigatingArgCSAccess().getOwnedCoIteratorCoIteratorVariableCSParserRuleCall_0_1_1_2_1_0());

                }
                pushFollow(FollowSets000.FOLLOW_60);
                lv_ownedCoIterator_10_0=ruleCoIteratorVariableCS();

                state._fsp--;
                if (state.failed) return ;

                }


                }


                }
                break;

        }

        // InternalCompleteOCL.g:5639:6: (otherlv_11= '=' ( (lv_ownedInitExpression_12_0= ruleExpCS ) ) )?
        int alt175=2;
        int LA175_0 = input.LA(1);

        if ( (LA175_0==29) ) {
            alt175=1;
        }
        switch (alt175) {
            case 1 :
                // InternalCompleteOCL.g:5640:7: otherlv_11= '=' ( (lv_ownedInitExpression_12_0= ruleExpCS ) )
                {
                otherlv_11=(Token)match(input,29,FollowSets000.FOLLOW_30); if (state.failed) return ;
                // InternalCompleteOCL.g:5644:7: ( (lv_ownedInitExpression_12_0= ruleExpCS ) )
                // InternalCompleteOCL.g:5645:8: (lv_ownedInitExpression_12_0= ruleExpCS )
                {
                // InternalCompleteOCL.g:5645:8: (lv_ownedInitExpression_12_0= ruleExpCS )
                // InternalCompleteOCL.g:5646:9: lv_ownedInitExpression_12_0= ruleExpCS
                {
                if ( state.backtracking==0 ) {

                  									newCompositeNode(grammarAccess.getNavigatingArgCSAccess().getOwnedInitExpressionExpCSParserRuleCall_0_1_1_3_1_0());

                }
                pushFollow(FollowSets000.FOLLOW_2);
                lv_ownedInitExpression_12_0=ruleExpCS();

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
    // $ANTLR end synpred166_InternalCompleteOCL

    // $ANTLR start synpred170_InternalCompleteOCL
    public final void synpred170_InternalCompleteOCL_fragment() throws RecognitionException {
        Token otherlv_13=null;
        Token otherlv_15=null;
        Token otherlv_16=null;
        Token otherlv_18=null;
        EObject lv_ownedType_14_0 = null;

        EObject lv_ownedCoIterator_17_0 = null;

        EObject lv_ownedInitExpression_19_0 = null;


        // InternalCompleteOCL.g:5666:5: ( ( (otherlv_13= ':' ( (lv_ownedType_14_0= ruleTypeExpCS ) ) )? ( (otherlv_15= 'with' | otherlv_16= '<-' ) ( (lv_ownedCoIterator_17_0= ruleCoIteratorVariableCS ) ) )? otherlv_18= 'in' ( (lv_ownedInitExpression_19_0= ruleExpCS ) ) ) )
        // InternalCompleteOCL.g:5666:5: ( (otherlv_13= ':' ( (lv_ownedType_14_0= ruleTypeExpCS ) ) )? ( (otherlv_15= 'with' | otherlv_16= '<-' ) ( (lv_ownedCoIterator_17_0= ruleCoIteratorVariableCS ) ) )? otherlv_18= 'in' ( (lv_ownedInitExpression_19_0= ruleExpCS ) ) )
        {
        // InternalCompleteOCL.g:5666:5: ( (otherlv_13= ':' ( (lv_ownedType_14_0= ruleTypeExpCS ) ) )? ( (otherlv_15= 'with' | otherlv_16= '<-' ) ( (lv_ownedCoIterator_17_0= ruleCoIteratorVariableCS ) ) )? otherlv_18= 'in' ( (lv_ownedInitExpression_19_0= ruleExpCS ) ) )
        // InternalCompleteOCL.g:5667:6: (otherlv_13= ':' ( (lv_ownedType_14_0= ruleTypeExpCS ) ) )? ( (otherlv_15= 'with' | otherlv_16= '<-' ) ( (lv_ownedCoIterator_17_0= ruleCoIteratorVariableCS ) ) )? otherlv_18= 'in' ( (lv_ownedInitExpression_19_0= ruleExpCS ) )
        {
        // InternalCompleteOCL.g:5667:6: (otherlv_13= ':' ( (lv_ownedType_14_0= ruleTypeExpCS ) ) )?
        int alt177=2;
        int LA177_0 = input.LA(1);

        if ( (LA177_0==23) ) {
            alt177=1;
        }
        switch (alt177) {
            case 1 :
                // InternalCompleteOCL.g:5668:7: otherlv_13= ':' ( (lv_ownedType_14_0= ruleTypeExpCS ) )
                {
                otherlv_13=(Token)match(input,23,FollowSets000.FOLLOW_21); if (state.failed) return ;
                // InternalCompleteOCL.g:5672:7: ( (lv_ownedType_14_0= ruleTypeExpCS ) )
                // InternalCompleteOCL.g:5673:8: (lv_ownedType_14_0= ruleTypeExpCS )
                {
                // InternalCompleteOCL.g:5673:8: (lv_ownedType_14_0= ruleTypeExpCS )
                // InternalCompleteOCL.g:5674:9: lv_ownedType_14_0= ruleTypeExpCS
                {
                if ( state.backtracking==0 ) {

                  									newCompositeNode(grammarAccess.getNavigatingArgCSAccess().getOwnedTypeTypeExpCSParserRuleCall_0_1_2_0_1_0());

                }
                pushFollow(FollowSets000.FOLLOW_62);
                lv_ownedType_14_0=ruleTypeExpCS();

                state._fsp--;
                if (state.failed) return ;

                }


                }


                }
                break;

        }

        // InternalCompleteOCL.g:5692:6: ( (otherlv_15= 'with' | otherlv_16= '<-' ) ( (lv_ownedCoIterator_17_0= ruleCoIteratorVariableCS ) ) )?
        int alt179=2;
        int LA179_0 = input.LA(1);

        if ( ((LA179_0>=86 && LA179_0<=87)) ) {
            alt179=1;
        }
        switch (alt179) {
            case 1 :
                // InternalCompleteOCL.g:5693:7: (otherlv_15= 'with' | otherlv_16= '<-' ) ( (lv_ownedCoIterator_17_0= ruleCoIteratorVariableCS ) )
                {
                // InternalCompleteOCL.g:5693:7: (otherlv_15= 'with' | otherlv_16= '<-' )
                int alt178=2;
                int LA178_0 = input.LA(1);

                if ( (LA178_0==86) ) {
                    alt178=1;
                }
                else if ( (LA178_0==87) ) {
                    alt178=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 178, 0, input);

                    throw nvae;
                }
                switch (alt178) {
                    case 1 :
                        // InternalCompleteOCL.g:5694:8: otherlv_15= 'with'
                        {
                        otherlv_15=(Token)match(input,86,FollowSets000.FOLLOW_15); if (state.failed) return ;

                        }
                        break;
                    case 2 :
                        // InternalCompleteOCL.g:5699:8: otherlv_16= '<-'
                        {
                        otherlv_16=(Token)match(input,87,FollowSets000.FOLLOW_15); if (state.failed) return ;

                        }
                        break;

                }

                // InternalCompleteOCL.g:5704:7: ( (lv_ownedCoIterator_17_0= ruleCoIteratorVariableCS ) )
                // InternalCompleteOCL.g:5705:8: (lv_ownedCoIterator_17_0= ruleCoIteratorVariableCS )
                {
                // InternalCompleteOCL.g:5705:8: (lv_ownedCoIterator_17_0= ruleCoIteratorVariableCS )
                // InternalCompleteOCL.g:5706:9: lv_ownedCoIterator_17_0= ruleCoIteratorVariableCS
                {
                if ( state.backtracking==0 ) {

                  									newCompositeNode(grammarAccess.getNavigatingArgCSAccess().getOwnedCoIteratorCoIteratorVariableCSParserRuleCall_0_1_2_1_1_0());

                }
                pushFollow(FollowSets000.FOLLOW_63);
                lv_ownedCoIterator_17_0=ruleCoIteratorVariableCS();

                state._fsp--;
                if (state.failed) return ;

                }


                }


                }
                break;

        }

        otherlv_18=(Token)match(input,95,FollowSets000.FOLLOW_30); if (state.failed) return ;
        // InternalCompleteOCL.g:5728:6: ( (lv_ownedInitExpression_19_0= ruleExpCS ) )
        // InternalCompleteOCL.g:5729:7: (lv_ownedInitExpression_19_0= ruleExpCS )
        {
        // InternalCompleteOCL.g:5729:7: (lv_ownedInitExpression_19_0= ruleExpCS )
        // InternalCompleteOCL.g:5730:8: lv_ownedInitExpression_19_0= ruleExpCS
        {
        if ( state.backtracking==0 ) {

          								newCompositeNode(grammarAccess.getNavigatingArgCSAccess().getOwnedInitExpressionExpCSParserRuleCall_0_1_2_3_0());

        }
        pushFollow(FollowSets000.FOLLOW_2);
        lv_ownedInitExpression_19_0=ruleExpCS();

        state._fsp--;
        if (state.failed) return ;

        }


        }


        }


        }
    }
    // $ANTLR end synpred170_InternalCompleteOCL

    // $ANTLR start synpred176_InternalCompleteOCL
    public final void synpred176_InternalCompleteOCL_fragment() throws RecognitionException {
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        EObject lv_ownedCoIterator_4_0 = null;

        EObject lv_ownedInitExpression_6_0 = null;


        // InternalCompleteOCL.g:5931:4: ( ( (otherlv_2= 'with' | otherlv_3= '<-' ) ( (lv_ownedCoIterator_4_0= ruleCoIteratorVariableCS ) ) (otherlv_5= '=' ( (lv_ownedInitExpression_6_0= ruleExpCS ) ) )? ) )
        // InternalCompleteOCL.g:5931:4: ( (otherlv_2= 'with' | otherlv_3= '<-' ) ( (lv_ownedCoIterator_4_0= ruleCoIteratorVariableCS ) ) (otherlv_5= '=' ( (lv_ownedInitExpression_6_0= ruleExpCS ) ) )? )
        {
        // InternalCompleteOCL.g:5931:4: ( (otherlv_2= 'with' | otherlv_3= '<-' ) ( (lv_ownedCoIterator_4_0= ruleCoIteratorVariableCS ) ) (otherlv_5= '=' ( (lv_ownedInitExpression_6_0= ruleExpCS ) ) )? )
        // InternalCompleteOCL.g:5932:5: (otherlv_2= 'with' | otherlv_3= '<-' ) ( (lv_ownedCoIterator_4_0= ruleCoIteratorVariableCS ) ) (otherlv_5= '=' ( (lv_ownedInitExpression_6_0= ruleExpCS ) ) )?
        {
        // InternalCompleteOCL.g:5932:5: (otherlv_2= 'with' | otherlv_3= '<-' )
        int alt190=2;
        int LA190_0 = input.LA(1);

        if ( (LA190_0==86) ) {
            alt190=1;
        }
        else if ( (LA190_0==87) ) {
            alt190=2;
        }
        else {
            if (state.backtracking>0) {state.failed=true; return ;}
            NoViableAltException nvae =
                new NoViableAltException("", 190, 0, input);

            throw nvae;
        }
        switch (alt190) {
            case 1 :
                // InternalCompleteOCL.g:5933:6: otherlv_2= 'with'
                {
                otherlv_2=(Token)match(input,86,FollowSets000.FOLLOW_15); if (state.failed) return ;

                }
                break;
            case 2 :
                // InternalCompleteOCL.g:5938:6: otherlv_3= '<-'
                {
                otherlv_3=(Token)match(input,87,FollowSets000.FOLLOW_15); if (state.failed) return ;

                }
                break;

        }

        // InternalCompleteOCL.g:5943:5: ( (lv_ownedCoIterator_4_0= ruleCoIteratorVariableCS ) )
        // InternalCompleteOCL.g:5944:6: (lv_ownedCoIterator_4_0= ruleCoIteratorVariableCS )
        {
        // InternalCompleteOCL.g:5944:6: (lv_ownedCoIterator_4_0= ruleCoIteratorVariableCS )
        // InternalCompleteOCL.g:5945:7: lv_ownedCoIterator_4_0= ruleCoIteratorVariableCS
        {
        if ( state.backtracking==0 ) {

          							newCompositeNode(grammarAccess.getNavigatingCommaArgCSAccess().getOwnedCoIteratorCoIteratorVariableCSParserRuleCall_2_0_1_0());

        }
        pushFollow(FollowSets000.FOLLOW_60);
        lv_ownedCoIterator_4_0=ruleCoIteratorVariableCS();

        state._fsp--;
        if (state.failed) return ;

        }


        }

        // InternalCompleteOCL.g:5962:5: (otherlv_5= '=' ( (lv_ownedInitExpression_6_0= ruleExpCS ) ) )?
        int alt191=2;
        int LA191_0 = input.LA(1);

        if ( (LA191_0==29) ) {
            alt191=1;
        }
        switch (alt191) {
            case 1 :
                // InternalCompleteOCL.g:5963:6: otherlv_5= '=' ( (lv_ownedInitExpression_6_0= ruleExpCS ) )
                {
                otherlv_5=(Token)match(input,29,FollowSets000.FOLLOW_30); if (state.failed) return ;
                // InternalCompleteOCL.g:5967:6: ( (lv_ownedInitExpression_6_0= ruleExpCS ) )
                // InternalCompleteOCL.g:5968:7: (lv_ownedInitExpression_6_0= ruleExpCS )
                {
                // InternalCompleteOCL.g:5968:7: (lv_ownedInitExpression_6_0= ruleExpCS )
                // InternalCompleteOCL.g:5969:8: lv_ownedInitExpression_6_0= ruleExpCS
                {
                if ( state.backtracking==0 ) {

                  								newCompositeNode(grammarAccess.getNavigatingCommaArgCSAccess().getOwnedInitExpressionExpCSParserRuleCall_2_0_2_1_0());

                }
                pushFollow(FollowSets000.FOLLOW_2);
                lv_ownedInitExpression_6_0=ruleExpCS();

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
    // $ANTLR end synpred176_InternalCompleteOCL

    // $ANTLR start synpred180_InternalCompleteOCL
    public final void synpred180_InternalCompleteOCL_fragment() throws RecognitionException {
        Token otherlv_7=null;
        Token otherlv_9=null;
        Token otherlv_10=null;
        Token otherlv_12=null;
        EObject lv_ownedType_8_0 = null;

        EObject lv_ownedCoIterator_11_0 = null;

        EObject lv_ownedInitExpression_13_0 = null;


        // InternalCompleteOCL.g:5989:4: ( (otherlv_7= ':' ( (lv_ownedType_8_0= ruleTypeExpCS ) ) ( (otherlv_9= 'with' | otherlv_10= '<-' ) ( (lv_ownedCoIterator_11_0= ruleCoIteratorVariableCS ) ) )? (otherlv_12= '=' ( (lv_ownedInitExpression_13_0= ruleExpCS ) ) )? ) )
        // InternalCompleteOCL.g:5989:4: (otherlv_7= ':' ( (lv_ownedType_8_0= ruleTypeExpCS ) ) ( (otherlv_9= 'with' | otherlv_10= '<-' ) ( (lv_ownedCoIterator_11_0= ruleCoIteratorVariableCS ) ) )? (otherlv_12= '=' ( (lv_ownedInitExpression_13_0= ruleExpCS ) ) )? )
        {
        // InternalCompleteOCL.g:5989:4: (otherlv_7= ':' ( (lv_ownedType_8_0= ruleTypeExpCS ) ) ( (otherlv_9= 'with' | otherlv_10= '<-' ) ( (lv_ownedCoIterator_11_0= ruleCoIteratorVariableCS ) ) )? (otherlv_12= '=' ( (lv_ownedInitExpression_13_0= ruleExpCS ) ) )? )
        // InternalCompleteOCL.g:5990:5: otherlv_7= ':' ( (lv_ownedType_8_0= ruleTypeExpCS ) ) ( (otherlv_9= 'with' | otherlv_10= '<-' ) ( (lv_ownedCoIterator_11_0= ruleCoIteratorVariableCS ) ) )? (otherlv_12= '=' ( (lv_ownedInitExpression_13_0= ruleExpCS ) ) )?
        {
        otherlv_7=(Token)match(input,23,FollowSets000.FOLLOW_21); if (state.failed) return ;
        // InternalCompleteOCL.g:5994:5: ( (lv_ownedType_8_0= ruleTypeExpCS ) )
        // InternalCompleteOCL.g:5995:6: (lv_ownedType_8_0= ruleTypeExpCS )
        {
        // InternalCompleteOCL.g:5995:6: (lv_ownedType_8_0= ruleTypeExpCS )
        // InternalCompleteOCL.g:5996:7: lv_ownedType_8_0= ruleTypeExpCS
        {
        if ( state.backtracking==0 ) {

          							newCompositeNode(grammarAccess.getNavigatingCommaArgCSAccess().getOwnedTypeTypeExpCSParserRuleCall_2_1_1_0());

        }
        pushFollow(FollowSets000.FOLLOW_61);
        lv_ownedType_8_0=ruleTypeExpCS();

        state._fsp--;
        if (state.failed) return ;

        }


        }

        // InternalCompleteOCL.g:6013:5: ( (otherlv_9= 'with' | otherlv_10= '<-' ) ( (lv_ownedCoIterator_11_0= ruleCoIteratorVariableCS ) ) )?
        int alt194=2;
        int LA194_0 = input.LA(1);

        if ( ((LA194_0>=86 && LA194_0<=87)) ) {
            alt194=1;
        }
        switch (alt194) {
            case 1 :
                // InternalCompleteOCL.g:6014:6: (otherlv_9= 'with' | otherlv_10= '<-' ) ( (lv_ownedCoIterator_11_0= ruleCoIteratorVariableCS ) )
                {
                // InternalCompleteOCL.g:6014:6: (otherlv_9= 'with' | otherlv_10= '<-' )
                int alt193=2;
                int LA193_0 = input.LA(1);

                if ( (LA193_0==86) ) {
                    alt193=1;
                }
                else if ( (LA193_0==87) ) {
                    alt193=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 193, 0, input);

                    throw nvae;
                }
                switch (alt193) {
                    case 1 :
                        // InternalCompleteOCL.g:6015:7: otherlv_9= 'with'
                        {
                        otherlv_9=(Token)match(input,86,FollowSets000.FOLLOW_15); if (state.failed) return ;

                        }
                        break;
                    case 2 :
                        // InternalCompleteOCL.g:6020:7: otherlv_10= '<-'
                        {
                        otherlv_10=(Token)match(input,87,FollowSets000.FOLLOW_15); if (state.failed) return ;

                        }
                        break;

                }

                // InternalCompleteOCL.g:6025:6: ( (lv_ownedCoIterator_11_0= ruleCoIteratorVariableCS ) )
                // InternalCompleteOCL.g:6026:7: (lv_ownedCoIterator_11_0= ruleCoIteratorVariableCS )
                {
                // InternalCompleteOCL.g:6026:7: (lv_ownedCoIterator_11_0= ruleCoIteratorVariableCS )
                // InternalCompleteOCL.g:6027:8: lv_ownedCoIterator_11_0= ruleCoIteratorVariableCS
                {
                if ( state.backtracking==0 ) {

                  								newCompositeNode(grammarAccess.getNavigatingCommaArgCSAccess().getOwnedCoIteratorCoIteratorVariableCSParserRuleCall_2_1_2_1_0());

                }
                pushFollow(FollowSets000.FOLLOW_60);
                lv_ownedCoIterator_11_0=ruleCoIteratorVariableCS();

                state._fsp--;
                if (state.failed) return ;

                }


                }


                }
                break;

        }

        // InternalCompleteOCL.g:6045:5: (otherlv_12= '=' ( (lv_ownedInitExpression_13_0= ruleExpCS ) ) )?
        int alt195=2;
        int LA195_0 = input.LA(1);

        if ( (LA195_0==29) ) {
            alt195=1;
        }
        switch (alt195) {
            case 1 :
                // InternalCompleteOCL.g:6046:6: otherlv_12= '=' ( (lv_ownedInitExpression_13_0= ruleExpCS ) )
                {
                otherlv_12=(Token)match(input,29,FollowSets000.FOLLOW_30); if (state.failed) return ;
                // InternalCompleteOCL.g:6050:6: ( (lv_ownedInitExpression_13_0= ruleExpCS ) )
                // InternalCompleteOCL.g:6051:7: (lv_ownedInitExpression_13_0= ruleExpCS )
                {
                // InternalCompleteOCL.g:6051:7: (lv_ownedInitExpression_13_0= ruleExpCS )
                // InternalCompleteOCL.g:6052:8: lv_ownedInitExpression_13_0= ruleExpCS
                {
                if ( state.backtracking==0 ) {

                  								newCompositeNode(grammarAccess.getNavigatingCommaArgCSAccess().getOwnedInitExpressionExpCSParserRuleCall_2_1_3_1_0());

                }
                pushFollow(FollowSets000.FOLLOW_2);
                lv_ownedInitExpression_13_0=ruleExpCS();

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
    // $ANTLR end synpred180_InternalCompleteOCL

    // $ANTLR start synpred184_InternalCompleteOCL
    public final void synpred184_InternalCompleteOCL_fragment() throws RecognitionException {
        Token otherlv_14=null;
        Token otherlv_16=null;
        Token otherlv_17=null;
        Token otherlv_19=null;
        EObject lv_ownedType_15_0 = null;

        EObject lv_ownedCoIterator_18_0 = null;

        EObject lv_ownedInitExpression_20_0 = null;


        // InternalCompleteOCL.g:6072:4: ( ( (otherlv_14= ':' ( (lv_ownedType_15_0= ruleTypeExpCS ) ) )? ( (otherlv_16= 'with' | otherlv_17= '<-' ) ( (lv_ownedCoIterator_18_0= ruleCoIteratorVariableCS ) ) )? otherlv_19= 'in' ( (lv_ownedInitExpression_20_0= ruleExpCS ) ) ) )
        // InternalCompleteOCL.g:6072:4: ( (otherlv_14= ':' ( (lv_ownedType_15_0= ruleTypeExpCS ) ) )? ( (otherlv_16= 'with' | otherlv_17= '<-' ) ( (lv_ownedCoIterator_18_0= ruleCoIteratorVariableCS ) ) )? otherlv_19= 'in' ( (lv_ownedInitExpression_20_0= ruleExpCS ) ) )
        {
        // InternalCompleteOCL.g:6072:4: ( (otherlv_14= ':' ( (lv_ownedType_15_0= ruleTypeExpCS ) ) )? ( (otherlv_16= 'with' | otherlv_17= '<-' ) ( (lv_ownedCoIterator_18_0= ruleCoIteratorVariableCS ) ) )? otherlv_19= 'in' ( (lv_ownedInitExpression_20_0= ruleExpCS ) ) )
        // InternalCompleteOCL.g:6073:5: (otherlv_14= ':' ( (lv_ownedType_15_0= ruleTypeExpCS ) ) )? ( (otherlv_16= 'with' | otherlv_17= '<-' ) ( (lv_ownedCoIterator_18_0= ruleCoIteratorVariableCS ) ) )? otherlv_19= 'in' ( (lv_ownedInitExpression_20_0= ruleExpCS ) )
        {
        // InternalCompleteOCL.g:6073:5: (otherlv_14= ':' ( (lv_ownedType_15_0= ruleTypeExpCS ) ) )?
        int alt197=2;
        int LA197_0 = input.LA(1);

        if ( (LA197_0==23) ) {
            alt197=1;
        }
        switch (alt197) {
            case 1 :
                // InternalCompleteOCL.g:6074:6: otherlv_14= ':' ( (lv_ownedType_15_0= ruleTypeExpCS ) )
                {
                otherlv_14=(Token)match(input,23,FollowSets000.FOLLOW_21); if (state.failed) return ;
                // InternalCompleteOCL.g:6078:6: ( (lv_ownedType_15_0= ruleTypeExpCS ) )
                // InternalCompleteOCL.g:6079:7: (lv_ownedType_15_0= ruleTypeExpCS )
                {
                // InternalCompleteOCL.g:6079:7: (lv_ownedType_15_0= ruleTypeExpCS )
                // InternalCompleteOCL.g:6080:8: lv_ownedType_15_0= ruleTypeExpCS
                {
                if ( state.backtracking==0 ) {

                  								newCompositeNode(grammarAccess.getNavigatingCommaArgCSAccess().getOwnedTypeTypeExpCSParserRuleCall_2_2_0_1_0());

                }
                pushFollow(FollowSets000.FOLLOW_62);
                lv_ownedType_15_0=ruleTypeExpCS();

                state._fsp--;
                if (state.failed) return ;

                }


                }


                }
                break;

        }

        // InternalCompleteOCL.g:6098:5: ( (otherlv_16= 'with' | otherlv_17= '<-' ) ( (lv_ownedCoIterator_18_0= ruleCoIteratorVariableCS ) ) )?
        int alt199=2;
        int LA199_0 = input.LA(1);

        if ( ((LA199_0>=86 && LA199_0<=87)) ) {
            alt199=1;
        }
        switch (alt199) {
            case 1 :
                // InternalCompleteOCL.g:6099:6: (otherlv_16= 'with' | otherlv_17= '<-' ) ( (lv_ownedCoIterator_18_0= ruleCoIteratorVariableCS ) )
                {
                // InternalCompleteOCL.g:6099:6: (otherlv_16= 'with' | otherlv_17= '<-' )
                int alt198=2;
                int LA198_0 = input.LA(1);

                if ( (LA198_0==86) ) {
                    alt198=1;
                }
                else if ( (LA198_0==87) ) {
                    alt198=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 198, 0, input);

                    throw nvae;
                }
                switch (alt198) {
                    case 1 :
                        // InternalCompleteOCL.g:6100:7: otherlv_16= 'with'
                        {
                        otherlv_16=(Token)match(input,86,FollowSets000.FOLLOW_15); if (state.failed) return ;

                        }
                        break;
                    case 2 :
                        // InternalCompleteOCL.g:6105:7: otherlv_17= '<-'
                        {
                        otherlv_17=(Token)match(input,87,FollowSets000.FOLLOW_15); if (state.failed) return ;

                        }
                        break;

                }

                // InternalCompleteOCL.g:6110:6: ( (lv_ownedCoIterator_18_0= ruleCoIteratorVariableCS ) )
                // InternalCompleteOCL.g:6111:7: (lv_ownedCoIterator_18_0= ruleCoIteratorVariableCS )
                {
                // InternalCompleteOCL.g:6111:7: (lv_ownedCoIterator_18_0= ruleCoIteratorVariableCS )
                // InternalCompleteOCL.g:6112:8: lv_ownedCoIterator_18_0= ruleCoIteratorVariableCS
                {
                if ( state.backtracking==0 ) {

                  								newCompositeNode(grammarAccess.getNavigatingCommaArgCSAccess().getOwnedCoIteratorCoIteratorVariableCSParserRuleCall_2_2_1_1_0());

                }
                pushFollow(FollowSets000.FOLLOW_63);
                lv_ownedCoIterator_18_0=ruleCoIteratorVariableCS();

                state._fsp--;
                if (state.failed) return ;

                }


                }


                }
                break;

        }

        otherlv_19=(Token)match(input,95,FollowSets000.FOLLOW_30); if (state.failed) return ;
        // InternalCompleteOCL.g:6134:5: ( (lv_ownedInitExpression_20_0= ruleExpCS ) )
        // InternalCompleteOCL.g:6135:6: (lv_ownedInitExpression_20_0= ruleExpCS )
        {
        // InternalCompleteOCL.g:6135:6: (lv_ownedInitExpression_20_0= ruleExpCS )
        // InternalCompleteOCL.g:6136:7: lv_ownedInitExpression_20_0= ruleExpCS
        {
        if ( state.backtracking==0 ) {

          							newCompositeNode(grammarAccess.getNavigatingCommaArgCSAccess().getOwnedInitExpressionExpCSParserRuleCall_2_2_3_0());

        }
        pushFollow(FollowSets000.FOLLOW_2);
        lv_ownedInitExpression_20_0=ruleExpCS();

        state._fsp--;
        if (state.failed) return ;

        }


        }


        }


        }
    }
    // $ANTLR end synpred184_InternalCompleteOCL

    // Delegated rules

    public final boolean synpred40_InternalCompleteOCL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred40_InternalCompleteOCL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred15_InternalCompleteOCL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred15_InternalCompleteOCL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred41_InternalCompleteOCL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred41_InternalCompleteOCL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred176_InternalCompleteOCL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred176_InternalCompleteOCL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred170_InternalCompleteOCL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred170_InternalCompleteOCL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred137_InternalCompleteOCL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred137_InternalCompleteOCL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred148_InternalCompleteOCL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred148_InternalCompleteOCL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred184_InternalCompleteOCL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred184_InternalCompleteOCL_fragment(); // can never throw exception
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
    public final boolean synpred144_InternalCompleteOCL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred144_InternalCompleteOCL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred145_InternalCompleteOCL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred145_InternalCompleteOCL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred134_InternalCompleteOCL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred134_InternalCompleteOCL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred166_InternalCompleteOCL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred166_InternalCompleteOCL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred16_InternalCompleteOCL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred16_InternalCompleteOCL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred146_InternalCompleteOCL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred146_InternalCompleteOCL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred162_InternalCompleteOCL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred162_InternalCompleteOCL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }


    protected DFA14 dfa14 = new DFA14(this);
    protected DFA85 dfa85 = new DFA85(this);
    protected DFA88 dfa88 = new DFA88(this);
    protected DFA91 dfa91 = new DFA91(this);
    protected DFA110 dfa110 = new DFA110(this);
    protected DFA122 dfa122 = new DFA122(this);
    static final String dfa_1s = "\20\uffff";
    static final String dfa_2s = "\1\32\1\33\1\5\5\27\1\5\5\25\2\uffff";
    static final String dfa_3s = "\2\33\1\40\5\27\1\47\5\27\2\uffff";
    static final String dfa_4s = "\16\uffff\1\1\1\2";
    static final String dfa_5s = "\20\uffff}>";
    static final String[] dfa_6s = {
            "\1\1\1\2",
            "\1\2",
            "\1\3\1\4\20\uffff\1\10\6\uffff\1\5\1\6\1\7",
            "\1\10",
            "\1\10",
            "\1\10",
            "\1\10",
            "\1\10",
            "\1\11\1\12\16\uffff\1\16\10\uffff\1\13\1\14\1\15\6\uffff\1\16",
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

    class DFA14 extends DFA {

        public DFA14(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 14;
            this.eot = dfa_1;
            this.eof = dfa_1;
            this.min = dfa_2;
            this.max = dfa_3;
            this.accept = dfa_4;
            this.special = dfa_5;
            this.transition = dfa_6;
        }
        public String getDescription() {
            return "665:2: (this_DefOperationCS_0= ruleDefOperationCS | this_DefPropertyCS_1= ruleDefPropertyCS )";
        }
    }
    static final String dfa_7s = "\30\uffff";
    static final String dfa_8s = "\1\5\17\uffff\5\0\3\uffff";
    static final String dfa_9s = "\1\120\17\uffff\5\0\3\uffff";
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

    class DFA85 extends DFA {

        public DFA85(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 85;
            this.eot = dfa_7;
            this.eof = dfa_7;
            this.min = dfa_8;
            this.max = dfa_9;
            this.accept = dfa_10;
            this.special = dfa_11;
            this.transition = dfa_12;
        }
        public String getDescription() {
            return "4626:2: (this_TypeNameExpCS_0= ruleTypeNameExpCS | this_TypeLiteralCS_1= ruleTypeLiteralCS | this_CollectionPatternCS_2= ruleCollectionPatternCS )";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 :
                        int LA85_16 = input.LA(1);


                        int index85_16 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred134_InternalCompleteOCL()) ) {s = 6;}

                        else if ( (true) ) {s = 23;}


                        input.seek(index85_16);
                        if ( s>=0 ) return s;
                        break;
                    case 1 :
                        int LA85_17 = input.LA(1);


                        int index85_17 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred134_InternalCompleteOCL()) ) {s = 6;}

                        else if ( (true) ) {s = 23;}


                        input.seek(index85_17);
                        if ( s>=0 ) return s;
                        break;
                    case 2 :
                        int LA85_18 = input.LA(1);


                        int index85_18 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred134_InternalCompleteOCL()) ) {s = 6;}

                        else if ( (true) ) {s = 23;}


                        input.seek(index85_18);
                        if ( s>=0 ) return s;
                        break;
                    case 3 :
                        int LA85_19 = input.LA(1);


                        int index85_19 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred134_InternalCompleteOCL()) ) {s = 6;}

                        else if ( (true) ) {s = 23;}


                        input.seek(index85_19);
                        if ( s>=0 ) return s;
                        break;
                    case 4 :
                        int LA85_20 = input.LA(1);


                        int index85_20 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred134_InternalCompleteOCL()) ) {s = 6;}

                        else if ( (true) ) {s = 23;}


                        input.seek(index85_20);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 85, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String dfa_13s = "\46\uffff";
    static final String dfa_14s = "\1\5\3\0\42\uffff";
    static final String dfa_15s = "\1\150\3\0\42\uffff";
    static final String dfa_16s = "\4\uffff\1\1\40\uffff\1\2";
    static final String dfa_17s = "\1\uffff\1\0\1\1\1\2\42\uffff}>";
    static final String[] dfa_18s = {
            "\4\4\14\uffff\1\4\10\uffff\3\4\11\uffff\12\4\1\1\1\2\1\3\1\4\21\uffff\2\4\1\uffff\5\4\4\uffff\1\4\2\uffff\4\4\6\uffff\1\4\4\uffff\1\45\1\4",
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

    class DFA88 extends DFA {

        public DFA88(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 88;
            this.eot = dfa_13;
            this.eof = dfa_13;
            this.min = dfa_14;
            this.max = dfa_15;
            this.accept = dfa_16;
            this.special = dfa_17;
            this.transition = dfa_18;
        }
        public String getDescription() {
            return "4729:2: ( (this_PrefixedPrimaryExpCS_0= rulePrefixedPrimaryExpCS ( () ( (lv_name_2_0= ruleBinaryOperatorName ) ) ( (lv_ownedRight_3_0= ruleExpCS ) ) )? ) | this_PrefixedLetExpCS_4= rulePrefixedLetExpCS )";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 :
                        int LA88_1 = input.LA(1);


                        int index88_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred137_InternalCompleteOCL()) ) {s = 4;}

                        else if ( (true) ) {s = 37;}


                        input.seek(index88_1);
                        if ( s>=0 ) return s;
                        break;
                    case 1 :
                        int LA88_2 = input.LA(1);


                        int index88_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred137_InternalCompleteOCL()) ) {s = 4;}

                        else if ( (true) ) {s = 37;}


                        input.seek(index88_2);
                        if ( s>=0 ) return s;
                        break;
                    case 2 :
                        int LA88_3 = input.LA(1);


                        int index88_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred137_InternalCompleteOCL()) ) {s = 4;}

                        else if ( (true) ) {s = 37;}


                        input.seek(index88_3);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 88, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String dfa_19s = "\45\uffff";
    static final String dfa_20s = "\1\5\12\uffff\7\0\23\uffff";
    static final String dfa_21s = "\1\150\12\uffff\7\0\23\uffff";
    static final String dfa_22s = "\1\uffff\1\1\1\2\1\3\1\4\15\uffff\1\10\1\11\11\uffff\1\12\4\uffff\1\5\1\6\1\7";
    static final String dfa_23s = "\13\uffff\1\0\1\1\1\2\1\3\1\4\1\5\1\6\23\uffff}>";
    static final String[] dfa_24s = {
            "\2\35\2\4\14\uffff\1\1\10\uffff\3\35\11\uffff\12\23\3\uffff\1\4\21\uffff\1\14\1\13\1\uffff\1\15\1\16\1\17\1\20\1\21\4\uffff\1\22\2\uffff\4\4\6\uffff\1\2\5\uffff\1\3",
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

    class DFA91 extends DFA {

        public DFA91(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 91;
            this.eot = dfa_19;
            this.eof = dfa_19;
            this.min = dfa_20;
            this.max = dfa_21;
            this.accept = dfa_22;
            this.special = dfa_23;
            this.transition = dfa_24;
        }
        public String getDescription() {
            return "4985:2: (this_NestedExpCS_0= ruleNestedExpCS | this_IfExpCS_1= ruleIfExpCS | this_SelfExpCS_2= ruleSelfExpCS | this_PrimitiveLiteralExpCS_3= rulePrimitiveLiteralExpCS | this_TupleLiteralExpCS_4= ruleTupleLiteralExpCS | this_MapLiteralExpCS_5= ruleMapLiteralExpCS | this_CollectionLiteralExpCS_6= ruleCollectionLiteralExpCS | this_LambdaLiteralExpCS_7= ruleLambdaLiteralExpCS | this_TypeLiteralExpCS_8= ruleTypeLiteralExpCS | this_NameExpCS_9= ruleNameExpCS )";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 :
                        int LA91_11 = input.LA(1);


                        int index91_11 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred144_InternalCompleteOCL()) ) {s = 34;}

                        else if ( (synpred148_InternalCompleteOCL()) ) {s = 19;}


                        input.seek(index91_11);
                        if ( s>=0 ) return s;
                        break;
                    case 1 :
                        int LA91_12 = input.LA(1);


                        int index91_12 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred145_InternalCompleteOCL()) ) {s = 35;}

                        else if ( (synpred148_InternalCompleteOCL()) ) {s = 19;}


                        input.seek(index91_12);
                        if ( s>=0 ) return s;
                        break;
                    case 2 :
                        int LA91_13 = input.LA(1);


                        int index91_13 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred146_InternalCompleteOCL()) ) {s = 36;}

                        else if ( (synpred148_InternalCompleteOCL()) ) {s = 19;}


                        input.seek(index91_13);
                        if ( s>=0 ) return s;
                        break;
                    case 3 :
                        int LA91_14 = input.LA(1);


                        int index91_14 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred146_InternalCompleteOCL()) ) {s = 36;}

                        else if ( (synpred148_InternalCompleteOCL()) ) {s = 19;}


                        input.seek(index91_14);
                        if ( s>=0 ) return s;
                        break;
                    case 4 :
                        int LA91_15 = input.LA(1);


                        int index91_15 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred146_InternalCompleteOCL()) ) {s = 36;}

                        else if ( (synpred148_InternalCompleteOCL()) ) {s = 19;}


                        input.seek(index91_15);
                        if ( s>=0 ) return s;
                        break;
                    case 5 :
                        int LA91_16 = input.LA(1);


                        int index91_16 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred146_InternalCompleteOCL()) ) {s = 36;}

                        else if ( (synpred148_InternalCompleteOCL()) ) {s = 19;}


                        input.seek(index91_16);
                        if ( s>=0 ) return s;
                        break;
                    case 6 :
                        int LA91_17 = input.LA(1);


                        int index91_17 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred146_InternalCompleteOCL()) ) {s = 36;}

                        else if ( (synpred148_InternalCompleteOCL()) ) {s = 19;}


                        input.seek(index91_17);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 91, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String dfa_25s = "\14\uffff";
    static final String dfa_26s = "\1\5\13\uffff";
    static final String dfa_27s = "\1\26\3\0\10\uffff";
    static final String dfa_28s = "\1\141\3\0\10\uffff";
    static final String dfa_29s = "\4\uffff\1\3\1\4\4\uffff\1\1\1\2";
    static final String dfa_30s = "\1\uffff\1\0\1\1\1\2\10\uffff}>";
    static final String[] dfa_31s = {
            "\1\5\1\3\4\uffff\1\5\71\uffff\1\1\1\2\7\uffff\1\4\2\5",
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
            ""
    };

    static final short[] dfa_25 = DFA.unpackEncodedString(dfa_25s);
    static final short[] dfa_26 = DFA.unpackEncodedString(dfa_26s);
    static final char[] dfa_27 = DFA.unpackEncodedStringToUnsignedChars(dfa_27s);
    static final char[] dfa_28 = DFA.unpackEncodedStringToUnsignedChars(dfa_28s);
    static final short[] dfa_29 = DFA.unpackEncodedString(dfa_29s);
    static final short[] dfa_30 = DFA.unpackEncodedString(dfa_30s);
    static final short[][] dfa_31 = unpackEncodedStringArray(dfa_31s);

    class DFA110 extends DFA {

        public DFA110(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 110;
            this.eot = dfa_25;
            this.eof = dfa_26;
            this.min = dfa_27;
            this.max = dfa_28;
            this.accept = dfa_29;
            this.special = dfa_30;
            this.transition = dfa_31;
        }
        public String getDescription() {
            return "5524:4: ( ( (otherlv_1= 'with' | otherlv_2= '<-' ) ( (lv_ownedCoIterator_3_0= ruleCoIteratorVariableCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? ) | (otherlv_6= ':' ( (lv_ownedType_7_0= ruleTypeExpCS ) ) ( (otherlv_8= 'with' | otherlv_9= '<-' ) ( (lv_ownedCoIterator_10_0= ruleCoIteratorVariableCS ) ) )? (otherlv_11= '=' ( (lv_ownedInitExpression_12_0= ruleExpCS ) ) )? ) | ( (otherlv_13= ':' ( (lv_ownedType_14_0= ruleTypeExpCS ) ) )? ( (otherlv_15= 'with' | otherlv_16= '<-' ) ( (lv_ownedCoIterator_17_0= ruleCoIteratorVariableCS ) ) )? otherlv_18= 'in' ( (lv_ownedInitExpression_19_0= ruleExpCS ) ) ) )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 :
                        int LA110_1 = input.LA(1);


                        int index110_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred162_InternalCompleteOCL()) ) {s = 10;}

                        else if ( (synpred170_InternalCompleteOCL()) ) {s = 4;}


                        input.seek(index110_1);
                        if ( s>=0 ) return s;
                        break;
                    case 1 :
                        int LA110_2 = input.LA(1);


                        int index110_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred162_InternalCompleteOCL()) ) {s = 10;}

                        else if ( (synpred170_InternalCompleteOCL()) ) {s = 4;}


                        input.seek(index110_2);
                        if ( s>=0 ) return s;
                        break;
                    case 2 :
                        int LA110_3 = input.LA(1);


                        int index110_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred166_InternalCompleteOCL()) ) {s = 11;}

                        else if ( (synpred170_InternalCompleteOCL()) ) {s = 4;}


                        input.seek(index110_3);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 110, _s, input);
            error(nvae);
            throw nvae;
        }
    }

    class DFA122 extends DFA {

        public DFA122(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 122;
            this.eot = dfa_25;
            this.eof = dfa_26;
            this.min = dfa_27;
            this.max = dfa_28;
            this.accept = dfa_29;
            this.special = dfa_30;
            this.transition = dfa_31;
        }
        public String getDescription() {
            return "5930:3: ( ( (otherlv_2= 'with' | otherlv_3= '<-' ) ( (lv_ownedCoIterator_4_0= ruleCoIteratorVariableCS ) ) (otherlv_5= '=' ( (lv_ownedInitExpression_6_0= ruleExpCS ) ) )? ) | (otherlv_7= ':' ( (lv_ownedType_8_0= ruleTypeExpCS ) ) ( (otherlv_9= 'with' | otherlv_10= '<-' ) ( (lv_ownedCoIterator_11_0= ruleCoIteratorVariableCS ) ) )? (otherlv_12= '=' ( (lv_ownedInitExpression_13_0= ruleExpCS ) ) )? ) | ( (otherlv_14= ':' ( (lv_ownedType_15_0= ruleTypeExpCS ) ) )? ( (otherlv_16= 'with' | otherlv_17= '<-' ) ( (lv_ownedCoIterator_18_0= ruleCoIteratorVariableCS ) ) )? otherlv_19= 'in' ( (lv_ownedInitExpression_20_0= ruleExpCS ) ) ) )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 :
                        int LA122_1 = input.LA(1);


                        int index122_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred176_InternalCompleteOCL()) ) {s = 10;}

                        else if ( (synpred184_InternalCompleteOCL()) ) {s = 4;}


                        input.seek(index122_1);
                        if ( s>=0 ) return s;
                        break;
                    case 1 :
                        int LA122_2 = input.LA(1);


                        int index122_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred176_InternalCompleteOCL()) ) {s = 10;}

                        else if ( (synpred184_InternalCompleteOCL()) ) {s = 4;}


                        input.seek(index122_2);
                        if ( s>=0 ) return s;
                        break;
                    case 2 :
                        int LA122_3 = input.LA(1);


                        int index122_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred180_InternalCompleteOCL()) ) {s = 11;}

                        else if ( (synpred184_InternalCompleteOCL()) ) {s = 4;}


                        input.seek(index122_3);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 122, _s, input);
            error(nvae);
            throw nvae;
        }
    }



    private static class FollowSets000 {
        public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x00000005C0080002L});
        public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000400080002L});
        public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x000FFC81C0200060L,0x000000000001F600L});
        public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x000000000C100000L});
        public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x000000000C100002L});
        public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x00000001C0800060L});
        public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000000A00000L});
        public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x00FFFC01C02001F0L,0x000001840F21F600L});
        public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000000400000L});
        public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000000800000L});
        public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000008000000L});
        public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x00000081C0200060L});
        public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x00000001C0000060L});
        public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x0000000000200000L});
        public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x00000001C0400060L});
        public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x0000000010400000L});
        public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x00FFFC01E02001E0L,0x000001040F21F600L});
        public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x0000000020000000L});
        public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x00FFFC01C02001E0L,0x000001040F21F600L});
        public static final BitSet FOLLOW_22 = new BitSet(new long[]{0x00000001C0000160L});
        public static final BitSet FOLLOW_23 = new BitSet(new long[]{0x0000000200000002L});
        public static final BitSet FOLLOW_24 = new BitSet(new long[]{0x00FFFC01C06001E0L,0x000001040F21F600L});
        public static final BitSet FOLLOW_25 = new BitSet(new long[]{0x00FFFC11C32001F2L,0x000001040F21F600L});
        public static final BitSet FOLLOW_26 = new BitSet(new long[]{0x0000001003000012L});
        public static final BitSet FOLLOW_27 = new BitSet(new long[]{0x0000000C00180000L});
        public static final BitSet FOLLOW_28 = new BitSet(new long[]{0x0000000C00080000L});
        public static final BitSet FOLLOW_29 = new BitSet(new long[]{0x0000006000000012L});
        public static final BitSet FOLLOW_30 = new BitSet(new long[]{0x00FFFC01C02001E0L,0x000001840F21F600L});
        public static final BitSet FOLLOW_31 = new BitSet(new long[]{0x0000010010000000L});
        public static final BitSet FOLLOW_32 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000800L});
        public static final BitSet FOLLOW_33 = new BitSet(new long[]{0x0000000000200002L});
        public static final BitSet FOLLOW_34 = new BitSet(new long[]{0x0000000000400000L,0x0000000020000000L});
        public static final BitSet FOLLOW_35 = new BitSet(new long[]{0x0000000010000000L});
        public static final BitSet FOLLOW_36 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
        public static final BitSet FOLLOW_37 = new BitSet(new long[]{0x00FFFC01C0A001E0L,0x000001840F25F600L});
        public static final BitSet FOLLOW_38 = new BitSet(new long[]{0x0000000010000000L,0x0000000000040000L});
        public static final BitSet FOLLOW_39 = new BitSet(new long[]{0x00FFFC01C0A001E0L,0x000001840F21F600L});
        public static final BitSet FOLLOW_40 = new BitSet(new long[]{0x0000000000000002L,0x0000000000080000L});
        public static final BitSet FOLLOW_41 = new BitSet(new long[]{0x0000000010000000L,0x0000000000100000L});
        public static final BitSet FOLLOW_42 = new BitSet(new long[]{0x0000000000000060L});
        public static final BitSet FOLLOW_43 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
        public static final BitSet FOLLOW_44 = new BitSet(new long[]{0x00FFFC01C02001E0L,0x000001840F25F600L});
        public static final BitSet FOLLOW_45 = new BitSet(new long[]{0x0000000000000000L,0x0000000000C00000L});
        public static final BitSet FOLLOW_46 = new BitSet(new long[]{0x0000000020800000L});
        public static final BitSet FOLLOW_47 = new BitSet(new long[]{0x0000000000000102L});
        public static final BitSet FOLLOW_48 = new BitSet(new long[]{0x0000000000000002L,0x0000000020000000L});
        public static final BitSet FOLLOW_49 = new BitSet(new long[]{0x0000000000000002L,0x0000000000020000L});
        public static final BitSet FOLLOW_50 = new BitSet(new long[]{0xFF90018020060002L,0x00000000000001FFL});
        public static final BitSet FOLLOW_51 = new BitSet(new long[]{0x0000000000200002L,0x0000000030020000L});
        public static final BitSet FOLLOW_52 = new BitSet(new long[]{0x0000000000000002L,0x0000000010020000L});
        public static final BitSet FOLLOW_53 = new BitSet(new long[]{0x0000000000000002L,0x0000000010000000L});
        public static final BitSet FOLLOW_54 = new BitSet(new long[]{0x0000000001000000L});
        public static final BitSet FOLLOW_55 = new BitSet(new long[]{0x00000001C0000160L,0x0000000000040000L});
        public static final BitSet FOLLOW_56 = new BitSet(new long[]{0x00FFFE01C0E001E0L,0x000001840F21F600L});
        public static final BitSet FOLLOW_57 = new BitSet(new long[]{0x0000000010400000L,0x0000000300000000L});
        public static final BitSet FOLLOW_58 = new BitSet(new long[]{0x0000000010000000L,0x0000000040000000L});
        public static final BitSet FOLLOW_59 = new BitSet(new long[]{0x0000000000800002L,0x0000000080C00000L});
        public static final BitSet FOLLOW_60 = new BitSet(new long[]{0x0000000020000002L});
        public static final BitSet FOLLOW_61 = new BitSet(new long[]{0x0000000020000002L,0x0000000000C00000L});
        public static final BitSet FOLLOW_62 = new BitSet(new long[]{0x0000000000000000L,0x0000000080C00000L});
        public static final BitSet FOLLOW_63 = new BitSet(new long[]{0x0000000000000000L,0x0000000080000000L});
        public static final BitSet FOLLOW_64 = new BitSet(new long[]{0x00FFFE01C02001E0L,0x000001840F21F600L});
        public static final BitSet FOLLOW_65 = new BitSet(new long[]{0x0000000000800002L});
        public static final BitSet FOLLOW_66 = new BitSet(new long[]{0x0000000000000000L,0x0000000800000000L});
        public static final BitSet FOLLOW_67 = new BitSet(new long[]{0x0000000000000000L,0x0000005000000000L});
        public static final BitSet FOLLOW_68 = new BitSet(new long[]{0x0000000000000000L,0x0000002000000000L});
        public static final BitSet FOLLOW_69 = new BitSet(new long[]{0x0000000010000000L,0x0000000080000000L});
        public static final BitSet FOLLOW_70 = new BitSet(new long[]{0x0000000020A00000L});
        public static final BitSet FOLLOW_71 = new BitSet(new long[]{0x0080000000000080L});
        public static final BitSet FOLLOW_72 = new BitSet(new long[]{0x0280020000000080L});
        public static final BitSet FOLLOW_73 = new BitSet(new long[]{0x0000000000000000L,0x0000060040000000L});
        public static final BitSet FOLLOW_74 = new BitSet(new long[]{0x0000000000000000L,0x0000000040000000L});
        public static final BitSet FOLLOW_75 = new BitSet(new long[]{0x0000000010000002L,0x0000000020000000L});
        public static final BitSet FOLLOW_76 = new BitSet(new long[]{0x00FFFE01C02001E0L,0x000001040F21F600L});
        public static final BitSet FOLLOW_77 = new BitSet(new long[]{0x0000000000000002L,0x0000080000000000L});
        public static final BitSet FOLLOW_78 = new BitSet(new long[]{0x0000000000000002L,0x0000100000000000L});
    }


}
