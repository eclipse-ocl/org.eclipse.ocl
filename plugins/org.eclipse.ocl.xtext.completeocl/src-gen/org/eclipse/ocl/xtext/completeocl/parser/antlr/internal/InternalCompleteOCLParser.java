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
    // InternalCompleteOCL.g:1509:1: rulePropertyContextDeclCS returns [EObject current=null] : (otherlv_0= 'context' ( (lv_ownedPathName_1_0= ruleUnreservedPathNameCS ) ) otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) ( ( (lv_ownedDefaultExpressions_4_1= rulederiveSpecificationCS | lv_ownedDefaultExpressions_4_2= ruleinitSpecificationCS ) ) )* ) ;
    public final EObject rulePropertyContextDeclCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        EObject lv_ownedPathName_1_0 = null;

        EObject lv_ownedType_3_0 = null;

        EObject lv_ownedDefaultExpressions_4_1 = null;

        EObject lv_ownedDefaultExpressions_4_2 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:1515:2: ( (otherlv_0= 'context' ( (lv_ownedPathName_1_0= ruleUnreservedPathNameCS ) ) otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) ( ( (lv_ownedDefaultExpressions_4_1= rulederiveSpecificationCS | lv_ownedDefaultExpressions_4_2= ruleinitSpecificationCS ) ) )* ) )
            // InternalCompleteOCL.g:1516:2: (otherlv_0= 'context' ( (lv_ownedPathName_1_0= ruleUnreservedPathNameCS ) ) otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) ( ( (lv_ownedDefaultExpressions_4_1= rulederiveSpecificationCS | lv_ownedDefaultExpressions_4_2= ruleinitSpecificationCS ) ) )* )
            {
            // InternalCompleteOCL.g:1516:2: (otherlv_0= 'context' ( (lv_ownedPathName_1_0= ruleUnreservedPathNameCS ) ) otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) ( ( (lv_ownedDefaultExpressions_4_1= rulederiveSpecificationCS | lv_ownedDefaultExpressions_4_2= ruleinitSpecificationCS ) ) )* )
            // InternalCompleteOCL.g:1517:3: otherlv_0= 'context' ( (lv_ownedPathName_1_0= ruleUnreservedPathNameCS ) ) otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) ( ( (lv_ownedDefaultExpressions_4_1= rulederiveSpecificationCS | lv_ownedDefaultExpressions_4_2= ruleinitSpecificationCS ) ) )*
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

            // InternalCompleteOCL.g:1563:3: ( ( (lv_ownedDefaultExpressions_4_1= rulederiveSpecificationCS | lv_ownedDefaultExpressions_4_2= ruleinitSpecificationCS ) ) )*
            loop35:
            do {
                int alt35=2;
                int LA35_0 = input.LA(1);

                if ( ((LA35_0>=37 && LA35_0<=38)) ) {
                    alt35=1;
                }


                switch (alt35) {
            	case 1 :
            	    // InternalCompleteOCL.g:1564:4: ( (lv_ownedDefaultExpressions_4_1= rulederiveSpecificationCS | lv_ownedDefaultExpressions_4_2= ruleinitSpecificationCS ) )
            	    {
            	    // InternalCompleteOCL.g:1564:4: ( (lv_ownedDefaultExpressions_4_1= rulederiveSpecificationCS | lv_ownedDefaultExpressions_4_2= ruleinitSpecificationCS ) )
            	    // InternalCompleteOCL.g:1565:5: (lv_ownedDefaultExpressions_4_1= rulederiveSpecificationCS | lv_ownedDefaultExpressions_4_2= ruleinitSpecificationCS )
            	    {
            	    // InternalCompleteOCL.g:1565:5: (lv_ownedDefaultExpressions_4_1= rulederiveSpecificationCS | lv_ownedDefaultExpressions_4_2= ruleinitSpecificationCS )
            	    int alt34=2;
            	    int LA34_0 = input.LA(1);

            	    if ( (LA34_0==37) ) {
            	        alt34=1;
            	    }
            	    else if ( (LA34_0==38) ) {
            	        alt34=2;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return current;}
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 34, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt34) {
            	        case 1 :
            	            // InternalCompleteOCL.g:1566:6: lv_ownedDefaultExpressions_4_1= rulederiveSpecificationCS
            	            {
            	            if ( state.backtracking==0 ) {

            	              						newCompositeNode(grammarAccess.getPropertyContextDeclCSAccess().getOwnedDefaultExpressionsDeriveSpecificationCSParserRuleCall_4_0_0());

            	            }
            	            pushFollow(FollowSets000.FOLLOW_29);
            	            lv_ownedDefaultExpressions_4_1=rulederiveSpecificationCS();

            	            state._fsp--;
            	            if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	              						if (current==null) {
            	              							current = createModelElementForParent(grammarAccess.getPropertyContextDeclCSRule());
            	              						}
            	              						add(
            	              							current,
            	              							"ownedDefaultExpressions",
            	              							lv_ownedDefaultExpressions_4_1,
            	              							"org.eclipse.ocl.xtext.completeocl.CompleteOCL.deriveSpecificationCS");
            	              						afterParserOrEnumRuleCall();

            	            }

            	            }
            	            break;
            	        case 2 :
            	            // InternalCompleteOCL.g:1582:6: lv_ownedDefaultExpressions_4_2= ruleinitSpecificationCS
            	            {
            	            if ( state.backtracking==0 ) {

            	              						newCompositeNode(grammarAccess.getPropertyContextDeclCSAccess().getOwnedDefaultExpressionsInitSpecificationCSParserRuleCall_4_0_1());

            	            }
            	            pushFollow(FollowSets000.FOLLOW_29);
            	            lv_ownedDefaultExpressions_4_2=ruleinitSpecificationCS();

            	            state._fsp--;
            	            if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	              						if (current==null) {
            	              							current = createModelElementForParent(grammarAccess.getPropertyContextDeclCSRule());
            	              						}
            	              						add(
            	              							current,
            	              							"ownedDefaultExpressions",
            	              							lv_ownedDefaultExpressions_4_2,
            	              							"org.eclipse.ocl.xtext.completeocl.CompleteOCL.initSpecificationCS");
            	              						afterParserOrEnumRuleCall();

            	            }

            	            }
            	            break;

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop35;
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
    // InternalCompleteOCL.g:1604:1: entryRuleSpecificationCS returns [EObject current=null] : iv_ruleSpecificationCS= ruleSpecificationCS EOF ;
    public final EObject entryRuleSpecificationCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSpecificationCS = null;


        try {
            // InternalCompleteOCL.g:1604:56: (iv_ruleSpecificationCS= ruleSpecificationCS EOF )
            // InternalCompleteOCL.g:1605:2: iv_ruleSpecificationCS= ruleSpecificationCS EOF
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
    // InternalCompleteOCL.g:1611:1: ruleSpecificationCS returns [EObject current=null] : ( ( (lv_ownedExpression_0_0= ruleExpCS ) ) | ( (lv_exprString_1_0= RULE_UNQUOTED_STRING ) ) ) ;
    public final EObject ruleSpecificationCS() throws RecognitionException {
        EObject current = null;

        Token lv_exprString_1_0=null;
        EObject lv_ownedExpression_0_0 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:1617:2: ( ( ( (lv_ownedExpression_0_0= ruleExpCS ) ) | ( (lv_exprString_1_0= RULE_UNQUOTED_STRING ) ) ) )
            // InternalCompleteOCL.g:1618:2: ( ( (lv_ownedExpression_0_0= ruleExpCS ) ) | ( (lv_exprString_1_0= RULE_UNQUOTED_STRING ) ) )
            {
            // InternalCompleteOCL.g:1618:2: ( ( (lv_ownedExpression_0_0= ruleExpCS ) ) | ( (lv_exprString_1_0= RULE_UNQUOTED_STRING ) ) )
            int alt36=2;
            int LA36_0 = input.LA(1);

            if ( ((LA36_0>=RULE_SIMPLE_ID && LA36_0<=RULE_SINGLE_QUOTED_STRING)||LA36_0==21||(LA36_0>=30 && LA36_0<=32)||(LA36_0>=42 && LA36_0<=55)||(LA36_0>=73 && LA36_0<=74)||(LA36_0>=76 && LA36_0<=80)||LA36_0==85||(LA36_0>=88 && LA36_0<=91)||LA36_0==98||(LA36_0>=103 && LA36_0<=104)) ) {
                alt36=1;
            }
            else if ( (LA36_0==RULE_UNQUOTED_STRING) ) {
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
                    // InternalCompleteOCL.g:1619:3: ( (lv_ownedExpression_0_0= ruleExpCS ) )
                    {
                    // InternalCompleteOCL.g:1619:3: ( (lv_ownedExpression_0_0= ruleExpCS ) )
                    // InternalCompleteOCL.g:1620:4: (lv_ownedExpression_0_0= ruleExpCS )
                    {
                    // InternalCompleteOCL.g:1620:4: (lv_ownedExpression_0_0= ruleExpCS )
                    // InternalCompleteOCL.g:1621:5: lv_ownedExpression_0_0= ruleExpCS
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
                    // InternalCompleteOCL.g:1639:3: ( (lv_exprString_1_0= RULE_UNQUOTED_STRING ) )
                    {
                    // InternalCompleteOCL.g:1639:3: ( (lv_exprString_1_0= RULE_UNQUOTED_STRING ) )
                    // InternalCompleteOCL.g:1640:4: (lv_exprString_1_0= RULE_UNQUOTED_STRING )
                    {
                    // InternalCompleteOCL.g:1640:4: (lv_exprString_1_0= RULE_UNQUOTED_STRING )
                    // InternalCompleteOCL.g:1641:5: lv_exprString_1_0= RULE_UNQUOTED_STRING
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
    // InternalCompleteOCL.g:1661:1: entryRulebodySpecificationCS returns [EObject current=null] : iv_rulebodySpecificationCS= rulebodySpecificationCS EOF ;
    public final EObject entryRulebodySpecificationCS() throws RecognitionException {
        EObject current = null;

        EObject iv_rulebodySpecificationCS = null;


        try {
            // InternalCompleteOCL.g:1661:60: (iv_rulebodySpecificationCS= rulebodySpecificationCS EOF )
            // InternalCompleteOCL.g:1662:2: iv_rulebodySpecificationCS= rulebodySpecificationCS EOF
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
    // InternalCompleteOCL.g:1668:1: rulebodySpecificationCS returns [EObject current=null] : (otherlv_0= 'body' ( ruleUnrestrictedName )? otherlv_2= ':' ( ( (lv_ownedExpression_3_0= ruleExpCS ) ) | ( (lv_exprString_4_0= RULE_UNQUOTED_STRING ) ) ) ) ;
    public final EObject rulebodySpecificationCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token lv_exprString_4_0=null;
        EObject lv_ownedExpression_3_0 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:1674:2: ( (otherlv_0= 'body' ( ruleUnrestrictedName )? otherlv_2= ':' ( ( (lv_ownedExpression_3_0= ruleExpCS ) ) | ( (lv_exprString_4_0= RULE_UNQUOTED_STRING ) ) ) ) )
            // InternalCompleteOCL.g:1675:2: (otherlv_0= 'body' ( ruleUnrestrictedName )? otherlv_2= ':' ( ( (lv_ownedExpression_3_0= ruleExpCS ) ) | ( (lv_exprString_4_0= RULE_UNQUOTED_STRING ) ) ) )
            {
            // InternalCompleteOCL.g:1675:2: (otherlv_0= 'body' ( ruleUnrestrictedName )? otherlv_2= ':' ( ( (lv_ownedExpression_3_0= ruleExpCS ) ) | ( (lv_exprString_4_0= RULE_UNQUOTED_STRING ) ) ) )
            // InternalCompleteOCL.g:1676:3: otherlv_0= 'body' ( ruleUnrestrictedName )? otherlv_2= ':' ( ( (lv_ownedExpression_3_0= ruleExpCS ) ) | ( (lv_exprString_4_0= RULE_UNQUOTED_STRING ) ) )
            {
            otherlv_0=(Token)match(input,36,FollowSets000.FOLLOW_8); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getBodySpecificationCSAccess().getBodyKeyword_0());

            }
            // InternalCompleteOCL.g:1680:3: ( ruleUnrestrictedName )?
            int alt37=2;
            int LA37_0 = input.LA(1);

            if ( ((LA37_0>=RULE_SIMPLE_ID && LA37_0<=RULE_ESCAPED_ID)||(LA37_0>=30 && LA37_0<=32)) ) {
                alt37=1;
            }
            switch (alt37) {
                case 1 :
                    // InternalCompleteOCL.g:1681:4: ruleUnrestrictedName
                    {
                    if ( state.backtracking==0 ) {

                      				/* */

                    }
                    if ( state.backtracking==0 ) {

                      				newCompositeNode(grammarAccess.getBodySpecificationCSAccess().getUnrestrictedNameParserRuleCall_1());

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

            otherlv_2=(Token)match(input,23,FollowSets000.FOLLOW_10); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_2, grammarAccess.getBodySpecificationCSAccess().getColonKeyword_2());

            }
            // InternalCompleteOCL.g:1696:3: ( ( (lv_ownedExpression_3_0= ruleExpCS ) ) | ( (lv_exprString_4_0= RULE_UNQUOTED_STRING ) ) )
            int alt38=2;
            int LA38_0 = input.LA(1);

            if ( ((LA38_0>=RULE_SIMPLE_ID && LA38_0<=RULE_SINGLE_QUOTED_STRING)||LA38_0==21||(LA38_0>=30 && LA38_0<=32)||(LA38_0>=42 && LA38_0<=55)||(LA38_0>=73 && LA38_0<=74)||(LA38_0>=76 && LA38_0<=80)||LA38_0==85||(LA38_0>=88 && LA38_0<=91)||LA38_0==98||(LA38_0>=103 && LA38_0<=104)) ) {
                alt38=1;
            }
            else if ( (LA38_0==RULE_UNQUOTED_STRING) ) {
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
                    // InternalCompleteOCL.g:1697:4: ( (lv_ownedExpression_3_0= ruleExpCS ) )
                    {
                    // InternalCompleteOCL.g:1697:4: ( (lv_ownedExpression_3_0= ruleExpCS ) )
                    // InternalCompleteOCL.g:1698:5: (lv_ownedExpression_3_0= ruleExpCS )
                    {
                    // InternalCompleteOCL.g:1698:5: (lv_ownedExpression_3_0= ruleExpCS )
                    // InternalCompleteOCL.g:1699:6: lv_ownedExpression_3_0= ruleExpCS
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getBodySpecificationCSAccess().getOwnedExpressionExpCSParserRuleCall_3_0_0());

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
                    break;
                case 2 :
                    // InternalCompleteOCL.g:1717:4: ( (lv_exprString_4_0= RULE_UNQUOTED_STRING ) )
                    {
                    // InternalCompleteOCL.g:1717:4: ( (lv_exprString_4_0= RULE_UNQUOTED_STRING ) )
                    // InternalCompleteOCL.g:1718:5: (lv_exprString_4_0= RULE_UNQUOTED_STRING )
                    {
                    // InternalCompleteOCL.g:1718:5: (lv_exprString_4_0= RULE_UNQUOTED_STRING )
                    // InternalCompleteOCL.g:1719:6: lv_exprString_4_0= RULE_UNQUOTED_STRING
                    {
                    lv_exprString_4_0=(Token)match(input,RULE_UNQUOTED_STRING,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						newLeafNode(lv_exprString_4_0, grammarAccess.getBodySpecificationCSAccess().getExprStringUNQUOTED_STRINGTerminalRuleCall_3_1_0());

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


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
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
    // InternalCompleteOCL.g:1740:1: entryRulederiveSpecificationCS returns [EObject current=null] : iv_rulederiveSpecificationCS= rulederiveSpecificationCS EOF ;
    public final EObject entryRulederiveSpecificationCS() throws RecognitionException {
        EObject current = null;

        EObject iv_rulederiveSpecificationCS = null;


        try {
            // InternalCompleteOCL.g:1740:62: (iv_rulederiveSpecificationCS= rulederiveSpecificationCS EOF )
            // InternalCompleteOCL.g:1741:2: iv_rulederiveSpecificationCS= rulederiveSpecificationCS EOF
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
    // InternalCompleteOCL.g:1747:1: rulederiveSpecificationCS returns [EObject current=null] : (otherlv_0= 'derive' ( ruleUnrestrictedName )? otherlv_2= ':' ( ( (lv_ownedExpression_3_0= ruleExpCS ) ) | ( (lv_exprString_4_0= RULE_UNQUOTED_STRING ) ) ) ) ;
    public final EObject rulederiveSpecificationCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token lv_exprString_4_0=null;
        EObject lv_ownedExpression_3_0 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:1753:2: ( (otherlv_0= 'derive' ( ruleUnrestrictedName )? otherlv_2= ':' ( ( (lv_ownedExpression_3_0= ruleExpCS ) ) | ( (lv_exprString_4_0= RULE_UNQUOTED_STRING ) ) ) ) )
            // InternalCompleteOCL.g:1754:2: (otherlv_0= 'derive' ( ruleUnrestrictedName )? otherlv_2= ':' ( ( (lv_ownedExpression_3_0= ruleExpCS ) ) | ( (lv_exprString_4_0= RULE_UNQUOTED_STRING ) ) ) )
            {
            // InternalCompleteOCL.g:1754:2: (otherlv_0= 'derive' ( ruleUnrestrictedName )? otherlv_2= ':' ( ( (lv_ownedExpression_3_0= ruleExpCS ) ) | ( (lv_exprString_4_0= RULE_UNQUOTED_STRING ) ) ) )
            // InternalCompleteOCL.g:1755:3: otherlv_0= 'derive' ( ruleUnrestrictedName )? otherlv_2= ':' ( ( (lv_ownedExpression_3_0= ruleExpCS ) ) | ( (lv_exprString_4_0= RULE_UNQUOTED_STRING ) ) )
            {
            otherlv_0=(Token)match(input,37,FollowSets000.FOLLOW_8); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getDeriveSpecificationCSAccess().getDeriveKeyword_0());

            }
            // InternalCompleteOCL.g:1759:3: ( ruleUnrestrictedName )?
            int alt39=2;
            int LA39_0 = input.LA(1);

            if ( ((LA39_0>=RULE_SIMPLE_ID && LA39_0<=RULE_ESCAPED_ID)||(LA39_0>=30 && LA39_0<=32)) ) {
                alt39=1;
            }
            switch (alt39) {
                case 1 :
                    // InternalCompleteOCL.g:1760:4: ruleUnrestrictedName
                    {
                    if ( state.backtracking==0 ) {

                      				/* */

                    }
                    if ( state.backtracking==0 ) {

                      				newCompositeNode(grammarAccess.getDeriveSpecificationCSAccess().getUnrestrictedNameParserRuleCall_1());

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

            otherlv_2=(Token)match(input,23,FollowSets000.FOLLOW_10); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_2, grammarAccess.getDeriveSpecificationCSAccess().getColonKeyword_2());

            }
            // InternalCompleteOCL.g:1775:3: ( ( (lv_ownedExpression_3_0= ruleExpCS ) ) | ( (lv_exprString_4_0= RULE_UNQUOTED_STRING ) ) )
            int alt40=2;
            int LA40_0 = input.LA(1);

            if ( ((LA40_0>=RULE_SIMPLE_ID && LA40_0<=RULE_SINGLE_QUOTED_STRING)||LA40_0==21||(LA40_0>=30 && LA40_0<=32)||(LA40_0>=42 && LA40_0<=55)||(LA40_0>=73 && LA40_0<=74)||(LA40_0>=76 && LA40_0<=80)||LA40_0==85||(LA40_0>=88 && LA40_0<=91)||LA40_0==98||(LA40_0>=103 && LA40_0<=104)) ) {
                alt40=1;
            }
            else if ( (LA40_0==RULE_UNQUOTED_STRING) ) {
                alt40=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 40, 0, input);

                throw nvae;
            }
            switch (alt40) {
                case 1 :
                    // InternalCompleteOCL.g:1776:4: ( (lv_ownedExpression_3_0= ruleExpCS ) )
                    {
                    // InternalCompleteOCL.g:1776:4: ( (lv_ownedExpression_3_0= ruleExpCS ) )
                    // InternalCompleteOCL.g:1777:5: (lv_ownedExpression_3_0= ruleExpCS )
                    {
                    // InternalCompleteOCL.g:1777:5: (lv_ownedExpression_3_0= ruleExpCS )
                    // InternalCompleteOCL.g:1778:6: lv_ownedExpression_3_0= ruleExpCS
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getDeriveSpecificationCSAccess().getOwnedExpressionExpCSParserRuleCall_3_0_0());

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
                    break;
                case 2 :
                    // InternalCompleteOCL.g:1796:4: ( (lv_exprString_4_0= RULE_UNQUOTED_STRING ) )
                    {
                    // InternalCompleteOCL.g:1796:4: ( (lv_exprString_4_0= RULE_UNQUOTED_STRING ) )
                    // InternalCompleteOCL.g:1797:5: (lv_exprString_4_0= RULE_UNQUOTED_STRING )
                    {
                    // InternalCompleteOCL.g:1797:5: (lv_exprString_4_0= RULE_UNQUOTED_STRING )
                    // InternalCompleteOCL.g:1798:6: lv_exprString_4_0= RULE_UNQUOTED_STRING
                    {
                    lv_exprString_4_0=(Token)match(input,RULE_UNQUOTED_STRING,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						newLeafNode(lv_exprString_4_0, grammarAccess.getDeriveSpecificationCSAccess().getExprStringUNQUOTED_STRINGTerminalRuleCall_3_1_0());

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


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
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
    // InternalCompleteOCL.g:1819:1: entryRuleinitSpecificationCS returns [EObject current=null] : iv_ruleinitSpecificationCS= ruleinitSpecificationCS EOF ;
    public final EObject entryRuleinitSpecificationCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleinitSpecificationCS = null;


        try {
            // InternalCompleteOCL.g:1819:60: (iv_ruleinitSpecificationCS= ruleinitSpecificationCS EOF )
            // InternalCompleteOCL.g:1820:2: iv_ruleinitSpecificationCS= ruleinitSpecificationCS EOF
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
    // InternalCompleteOCL.g:1826:1: ruleinitSpecificationCS returns [EObject current=null] : (otherlv_0= 'init' ( ruleUnrestrictedName )? otherlv_2= ':' ( ( (lv_ownedExpression_3_0= ruleExpCS ) ) | ( (lv_exprString_4_0= RULE_UNQUOTED_STRING ) ) ) ) ;
    public final EObject ruleinitSpecificationCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token lv_exprString_4_0=null;
        EObject lv_ownedExpression_3_0 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:1832:2: ( (otherlv_0= 'init' ( ruleUnrestrictedName )? otherlv_2= ':' ( ( (lv_ownedExpression_3_0= ruleExpCS ) ) | ( (lv_exprString_4_0= RULE_UNQUOTED_STRING ) ) ) ) )
            // InternalCompleteOCL.g:1833:2: (otherlv_0= 'init' ( ruleUnrestrictedName )? otherlv_2= ':' ( ( (lv_ownedExpression_3_0= ruleExpCS ) ) | ( (lv_exprString_4_0= RULE_UNQUOTED_STRING ) ) ) )
            {
            // InternalCompleteOCL.g:1833:2: (otherlv_0= 'init' ( ruleUnrestrictedName )? otherlv_2= ':' ( ( (lv_ownedExpression_3_0= ruleExpCS ) ) | ( (lv_exprString_4_0= RULE_UNQUOTED_STRING ) ) ) )
            // InternalCompleteOCL.g:1834:3: otherlv_0= 'init' ( ruleUnrestrictedName )? otherlv_2= ':' ( ( (lv_ownedExpression_3_0= ruleExpCS ) ) | ( (lv_exprString_4_0= RULE_UNQUOTED_STRING ) ) )
            {
            otherlv_0=(Token)match(input,38,FollowSets000.FOLLOW_8); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getInitSpecificationCSAccess().getInitKeyword_0());

            }
            // InternalCompleteOCL.g:1838:3: ( ruleUnrestrictedName )?
            int alt41=2;
            int LA41_0 = input.LA(1);

            if ( ((LA41_0>=RULE_SIMPLE_ID && LA41_0<=RULE_ESCAPED_ID)||(LA41_0>=30 && LA41_0<=32)) ) {
                alt41=1;
            }
            switch (alt41) {
                case 1 :
                    // InternalCompleteOCL.g:1839:4: ruleUnrestrictedName
                    {
                    if ( state.backtracking==0 ) {

                      				/* */

                    }
                    if ( state.backtracking==0 ) {

                      				newCompositeNode(grammarAccess.getInitSpecificationCSAccess().getUnrestrictedNameParserRuleCall_1());

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

            otherlv_2=(Token)match(input,23,FollowSets000.FOLLOW_10); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_2, grammarAccess.getInitSpecificationCSAccess().getColonKeyword_2());

            }
            // InternalCompleteOCL.g:1854:3: ( ( (lv_ownedExpression_3_0= ruleExpCS ) ) | ( (lv_exprString_4_0= RULE_UNQUOTED_STRING ) ) )
            int alt42=2;
            int LA42_0 = input.LA(1);

            if ( ((LA42_0>=RULE_SIMPLE_ID && LA42_0<=RULE_SINGLE_QUOTED_STRING)||LA42_0==21||(LA42_0>=30 && LA42_0<=32)||(LA42_0>=42 && LA42_0<=55)||(LA42_0>=73 && LA42_0<=74)||(LA42_0>=76 && LA42_0<=80)||LA42_0==85||(LA42_0>=88 && LA42_0<=91)||LA42_0==98||(LA42_0>=103 && LA42_0<=104)) ) {
                alt42=1;
            }
            else if ( (LA42_0==RULE_UNQUOTED_STRING) ) {
                alt42=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 42, 0, input);

                throw nvae;
            }
            switch (alt42) {
                case 1 :
                    // InternalCompleteOCL.g:1855:4: ( (lv_ownedExpression_3_0= ruleExpCS ) )
                    {
                    // InternalCompleteOCL.g:1855:4: ( (lv_ownedExpression_3_0= ruleExpCS ) )
                    // InternalCompleteOCL.g:1856:5: (lv_ownedExpression_3_0= ruleExpCS )
                    {
                    // InternalCompleteOCL.g:1856:5: (lv_ownedExpression_3_0= ruleExpCS )
                    // InternalCompleteOCL.g:1857:6: lv_ownedExpression_3_0= ruleExpCS
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getInitSpecificationCSAccess().getOwnedExpressionExpCSParserRuleCall_3_0_0());

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
                    break;
                case 2 :
                    // InternalCompleteOCL.g:1875:4: ( (lv_exprString_4_0= RULE_UNQUOTED_STRING ) )
                    {
                    // InternalCompleteOCL.g:1875:4: ( (lv_exprString_4_0= RULE_UNQUOTED_STRING ) )
                    // InternalCompleteOCL.g:1876:5: (lv_exprString_4_0= RULE_UNQUOTED_STRING )
                    {
                    // InternalCompleteOCL.g:1876:5: (lv_exprString_4_0= RULE_UNQUOTED_STRING )
                    // InternalCompleteOCL.g:1877:6: lv_exprString_4_0= RULE_UNQUOTED_STRING
                    {
                    lv_exprString_4_0=(Token)match(input,RULE_UNQUOTED_STRING,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						newLeafNode(lv_exprString_4_0, grammarAccess.getInitSpecificationCSAccess().getExprStringUNQUOTED_STRINGTerminalRuleCall_3_1_0());

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


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
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
    // InternalCompleteOCL.g:1898:1: entryRuleTemplateSignatureCS returns [EObject current=null] : iv_ruleTemplateSignatureCS= ruleTemplateSignatureCS EOF ;
    public final EObject entryRuleTemplateSignatureCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTemplateSignatureCS = null;


        try {
            // InternalCompleteOCL.g:1898:60: (iv_ruleTemplateSignatureCS= ruleTemplateSignatureCS EOF )
            // InternalCompleteOCL.g:1899:2: iv_ruleTemplateSignatureCS= ruleTemplateSignatureCS EOF
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
    // InternalCompleteOCL.g:1905:1: ruleTemplateSignatureCS returns [EObject current=null] : ( (otherlv_0= '(' ( (lv_ownedParameters_1_0= ruleTypeParameterCS ) ) (otherlv_2= ',' ( (lv_ownedParameters_3_0= ruleTypeParameterCS ) ) )* otherlv_4= ')' ) | (otherlv_5= '<' ( (lv_ownedParameters_6_0= ruleTypeParameterCS ) ) (otherlv_7= ',' ( (lv_ownedParameters_8_0= ruleTypeParameterCS ) ) )* otherlv_9= '>' ) ) ;
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
            // InternalCompleteOCL.g:1911:2: ( ( (otherlv_0= '(' ( (lv_ownedParameters_1_0= ruleTypeParameterCS ) ) (otherlv_2= ',' ( (lv_ownedParameters_3_0= ruleTypeParameterCS ) ) )* otherlv_4= ')' ) | (otherlv_5= '<' ( (lv_ownedParameters_6_0= ruleTypeParameterCS ) ) (otherlv_7= ',' ( (lv_ownedParameters_8_0= ruleTypeParameterCS ) ) )* otherlv_9= '>' ) ) )
            // InternalCompleteOCL.g:1912:2: ( (otherlv_0= '(' ( (lv_ownedParameters_1_0= ruleTypeParameterCS ) ) (otherlv_2= ',' ( (lv_ownedParameters_3_0= ruleTypeParameterCS ) ) )* otherlv_4= ')' ) | (otherlv_5= '<' ( (lv_ownedParameters_6_0= ruleTypeParameterCS ) ) (otherlv_7= ',' ( (lv_ownedParameters_8_0= ruleTypeParameterCS ) ) )* otherlv_9= '>' ) )
            {
            // InternalCompleteOCL.g:1912:2: ( (otherlv_0= '(' ( (lv_ownedParameters_1_0= ruleTypeParameterCS ) ) (otherlv_2= ',' ( (lv_ownedParameters_3_0= ruleTypeParameterCS ) ) )* otherlv_4= ')' ) | (otherlv_5= '<' ( (lv_ownedParameters_6_0= ruleTypeParameterCS ) ) (otherlv_7= ',' ( (lv_ownedParameters_8_0= ruleTypeParameterCS ) ) )* otherlv_9= '>' ) )
            int alt45=2;
            int LA45_0 = input.LA(1);

            if ( (LA45_0==21) ) {
                alt45=1;
            }
            else if ( (LA45_0==39) ) {
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
                    // InternalCompleteOCL.g:1913:3: (otherlv_0= '(' ( (lv_ownedParameters_1_0= ruleTypeParameterCS ) ) (otherlv_2= ',' ( (lv_ownedParameters_3_0= ruleTypeParameterCS ) ) )* otherlv_4= ')' )
                    {
                    // InternalCompleteOCL.g:1913:3: (otherlv_0= '(' ( (lv_ownedParameters_1_0= ruleTypeParameterCS ) ) (otherlv_2= ',' ( (lv_ownedParameters_3_0= ruleTypeParameterCS ) ) )* otherlv_4= ')' )
                    // InternalCompleteOCL.g:1914:4: otherlv_0= '(' ( (lv_ownedParameters_1_0= ruleTypeParameterCS ) ) (otherlv_2= ',' ( (lv_ownedParameters_3_0= ruleTypeParameterCS ) ) )* otherlv_4= ')'
                    {
                    otherlv_0=(Token)match(input,21,FollowSets000.FOLLOW_15); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_0, grammarAccess.getTemplateSignatureCSAccess().getLeftParenthesisKeyword_0_0());

                    }
                    // InternalCompleteOCL.g:1918:4: ( (lv_ownedParameters_1_0= ruleTypeParameterCS ) )
                    // InternalCompleteOCL.g:1919:5: (lv_ownedParameters_1_0= ruleTypeParameterCS )
                    {
                    // InternalCompleteOCL.g:1919:5: (lv_ownedParameters_1_0= ruleTypeParameterCS )
                    // InternalCompleteOCL.g:1920:6: lv_ownedParameters_1_0= ruleTypeParameterCS
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

                    // InternalCompleteOCL.g:1937:4: (otherlv_2= ',' ( (lv_ownedParameters_3_0= ruleTypeParameterCS ) ) )*
                    loop43:
                    do {
                        int alt43=2;
                        int LA43_0 = input.LA(1);

                        if ( (LA43_0==28) ) {
                            alt43=1;
                        }


                        switch (alt43) {
                    	case 1 :
                    	    // InternalCompleteOCL.g:1938:5: otherlv_2= ',' ( (lv_ownedParameters_3_0= ruleTypeParameterCS ) )
                    	    {
                    	    otherlv_2=(Token)match(input,28,FollowSets000.FOLLOW_15); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      					newLeafNode(otherlv_2, grammarAccess.getTemplateSignatureCSAccess().getCommaKeyword_0_2_0());

                    	    }
                    	    // InternalCompleteOCL.g:1942:5: ( (lv_ownedParameters_3_0= ruleTypeParameterCS ) )
                    	    // InternalCompleteOCL.g:1943:6: (lv_ownedParameters_3_0= ruleTypeParameterCS )
                    	    {
                    	    // InternalCompleteOCL.g:1943:6: (lv_ownedParameters_3_0= ruleTypeParameterCS )
                    	    // InternalCompleteOCL.g:1944:7: lv_ownedParameters_3_0= ruleTypeParameterCS
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
                    	    break loop43;
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
                    // InternalCompleteOCL.g:1968:3: (otherlv_5= '<' ( (lv_ownedParameters_6_0= ruleTypeParameterCS ) ) (otherlv_7= ',' ( (lv_ownedParameters_8_0= ruleTypeParameterCS ) ) )* otherlv_9= '>' )
                    {
                    // InternalCompleteOCL.g:1968:3: (otherlv_5= '<' ( (lv_ownedParameters_6_0= ruleTypeParameterCS ) ) (otherlv_7= ',' ( (lv_ownedParameters_8_0= ruleTypeParameterCS ) ) )* otherlv_9= '>' )
                    // InternalCompleteOCL.g:1969:4: otherlv_5= '<' ( (lv_ownedParameters_6_0= ruleTypeParameterCS ) ) (otherlv_7= ',' ( (lv_ownedParameters_8_0= ruleTypeParameterCS ) ) )* otherlv_9= '>'
                    {
                    otherlv_5=(Token)match(input,39,FollowSets000.FOLLOW_15); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_5, grammarAccess.getTemplateSignatureCSAccess().getLessThanSignKeyword_1_0());

                    }
                    // InternalCompleteOCL.g:1973:4: ( (lv_ownedParameters_6_0= ruleTypeParameterCS ) )
                    // InternalCompleteOCL.g:1974:5: (lv_ownedParameters_6_0= ruleTypeParameterCS )
                    {
                    // InternalCompleteOCL.g:1974:5: (lv_ownedParameters_6_0= ruleTypeParameterCS )
                    // InternalCompleteOCL.g:1975:6: lv_ownedParameters_6_0= ruleTypeParameterCS
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getTemplateSignatureCSAccess().getOwnedParametersTypeParameterCSParserRuleCall_1_1_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_30);
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

                    // InternalCompleteOCL.g:1992:4: (otherlv_7= ',' ( (lv_ownedParameters_8_0= ruleTypeParameterCS ) ) )*
                    loop44:
                    do {
                        int alt44=2;
                        int LA44_0 = input.LA(1);

                        if ( (LA44_0==28) ) {
                            alt44=1;
                        }


                        switch (alt44) {
                    	case 1 :
                    	    // InternalCompleteOCL.g:1993:5: otherlv_7= ',' ( (lv_ownedParameters_8_0= ruleTypeParameterCS ) )
                    	    {
                    	    otherlv_7=(Token)match(input,28,FollowSets000.FOLLOW_15); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      					newLeafNode(otherlv_7, grammarAccess.getTemplateSignatureCSAccess().getCommaKeyword_1_2_0());

                    	    }
                    	    // InternalCompleteOCL.g:1997:5: ( (lv_ownedParameters_8_0= ruleTypeParameterCS ) )
                    	    // InternalCompleteOCL.g:1998:6: (lv_ownedParameters_8_0= ruleTypeParameterCS )
                    	    {
                    	    // InternalCompleteOCL.g:1998:6: (lv_ownedParameters_8_0= ruleTypeParameterCS )
                    	    // InternalCompleteOCL.g:1999:7: lv_ownedParameters_8_0= ruleTypeParameterCS
                    	    {
                    	    if ( state.backtracking==0 ) {

                    	      							newCompositeNode(grammarAccess.getTemplateSignatureCSAccess().getOwnedParametersTypeParameterCSParserRuleCall_1_2_1_0());

                    	    }
                    	    pushFollow(FollowSets000.FOLLOW_30);
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
                    	    break loop44;
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
    // InternalCompleteOCL.g:2026:1: entryRuleTypedRefCS returns [EObject current=null] : iv_ruleTypedRefCS= ruleTypedRefCS EOF ;
    public final EObject entryRuleTypedRefCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTypedRefCS = null;


        try {
            // InternalCompleteOCL.g:2026:51: (iv_ruleTypedRefCS= ruleTypedRefCS EOF )
            // InternalCompleteOCL.g:2027:2: iv_ruleTypedRefCS= ruleTypedRefCS EOF
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
    // InternalCompleteOCL.g:2033:1: ruleTypedRefCS returns [EObject current=null] : (this_TypeLiteralCS_0= ruleTypeLiteralCS | this_TypedTypeRefCS_1= ruleTypedTypeRefCS ) ;
    public final EObject ruleTypedRefCS() throws RecognitionException {
        EObject current = null;

        EObject this_TypeLiteralCS_0 = null;

        EObject this_TypedTypeRefCS_1 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:2039:2: ( (this_TypeLiteralCS_0= ruleTypeLiteralCS | this_TypedTypeRefCS_1= ruleTypedTypeRefCS ) )
            // InternalCompleteOCL.g:2040:2: (this_TypeLiteralCS_0= ruleTypeLiteralCS | this_TypedTypeRefCS_1= ruleTypedTypeRefCS )
            {
            // InternalCompleteOCL.g:2040:2: (this_TypeLiteralCS_0= ruleTypeLiteralCS | this_TypedTypeRefCS_1= ruleTypedTypeRefCS )
            int alt46=2;
            int LA46_0 = input.LA(1);

            if ( ((LA46_0>=42 && LA46_0<=51)||(LA46_0>=73 && LA46_0<=74)||(LA46_0>=76 && LA46_0<=80)) ) {
                alt46=1;
            }
            else if ( ((LA46_0>=RULE_SIMPLE_ID && LA46_0<=RULE_ESCAPED_ID)||(LA46_0>=30 && LA46_0<=32)) ) {
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
                    // InternalCompleteOCL.g:2041:3: this_TypeLiteralCS_0= ruleTypeLiteralCS
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
                    // InternalCompleteOCL.g:2053:3: this_TypedTypeRefCS_1= ruleTypedTypeRefCS
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
    // InternalCompleteOCL.g:2068:1: entryRuleUnrestrictedName returns [String current=null] : iv_ruleUnrestrictedName= ruleUnrestrictedName EOF ;
    public final String entryRuleUnrestrictedName() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleUnrestrictedName = null;


        try {
            // InternalCompleteOCL.g:2068:56: (iv_ruleUnrestrictedName= ruleUnrestrictedName EOF )
            // InternalCompleteOCL.g:2069:2: iv_ruleUnrestrictedName= ruleUnrestrictedName EOF
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
    // InternalCompleteOCL.g:2075:1: ruleUnrestrictedName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_EssentialOCLUnrestrictedName_0= ruleEssentialOCLUnrestrictedName | kw= 'import' | kw= 'include' | kw= 'library' ) ;
    public final AntlrDatatypeRuleToken ruleUnrestrictedName() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        AntlrDatatypeRuleToken this_EssentialOCLUnrestrictedName_0 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:2081:2: ( (this_EssentialOCLUnrestrictedName_0= ruleEssentialOCLUnrestrictedName | kw= 'import' | kw= 'include' | kw= 'library' ) )
            // InternalCompleteOCL.g:2082:2: (this_EssentialOCLUnrestrictedName_0= ruleEssentialOCLUnrestrictedName | kw= 'import' | kw= 'include' | kw= 'library' )
            {
            // InternalCompleteOCL.g:2082:2: (this_EssentialOCLUnrestrictedName_0= ruleEssentialOCLUnrestrictedName | kw= 'import' | kw= 'include' | kw= 'library' )
            int alt47=4;
            switch ( input.LA(1) ) {
            case RULE_SIMPLE_ID:
            case RULE_ESCAPED_ID:
                {
                alt47=1;
                }
                break;
            case 30:
                {
                alt47=2;
                }
                break;
            case 31:
                {
                alt47=3;
                }
                break;
            case 32:
                {
                alt47=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 47, 0, input);

                throw nvae;
            }

            switch (alt47) {
                case 1 :
                    // InternalCompleteOCL.g:2083:3: this_EssentialOCLUnrestrictedName_0= ruleEssentialOCLUnrestrictedName
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
                    // InternalCompleteOCL.g:2094:3: kw= 'import'
                    {
                    kw=(Token)match(input,30,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getUnrestrictedNameAccess().getImportKeyword_1());

                    }

                    }
                    break;
                case 3 :
                    // InternalCompleteOCL.g:2100:3: kw= 'include'
                    {
                    kw=(Token)match(input,31,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getUnrestrictedNameAccess().getIncludeKeyword_2());

                    }

                    }
                    break;
                case 4 :
                    // InternalCompleteOCL.g:2106:3: kw= 'library'
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
    // InternalCompleteOCL.g:2115:1: entryRuleNavigatingArgExpCS returns [EObject current=null] : iv_ruleNavigatingArgExpCS= ruleNavigatingArgExpCS EOF ;
    public final EObject entryRuleNavigatingArgExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNavigatingArgExpCS = null;


        try {
            // InternalCompleteOCL.g:2115:59: (iv_ruleNavigatingArgExpCS= ruleNavigatingArgExpCS EOF )
            // InternalCompleteOCL.g:2116:2: iv_ruleNavigatingArgExpCS= ruleNavigatingArgExpCS EOF
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
    // InternalCompleteOCL.g:2122:1: ruleNavigatingArgExpCS returns [EObject current=null] : ( ( () otherlv_1= '?' ) | this_ExpCS_2= ruleExpCS ) ;
    public final EObject ruleNavigatingArgExpCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject this_ExpCS_2 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:2128:2: ( ( ( () otherlv_1= '?' ) | this_ExpCS_2= ruleExpCS ) )
            // InternalCompleteOCL.g:2129:2: ( ( () otherlv_1= '?' ) | this_ExpCS_2= ruleExpCS )
            {
            // InternalCompleteOCL.g:2129:2: ( ( () otherlv_1= '?' ) | this_ExpCS_2= ruleExpCS )
            int alt48=2;
            int LA48_0 = input.LA(1);

            if ( (LA48_0==41) ) {
                alt48=1;
            }
            else if ( ((LA48_0>=RULE_SIMPLE_ID && LA48_0<=RULE_SINGLE_QUOTED_STRING)||LA48_0==21||(LA48_0>=30 && LA48_0<=32)||(LA48_0>=42 && LA48_0<=55)||(LA48_0>=73 && LA48_0<=74)||(LA48_0>=76 && LA48_0<=80)||LA48_0==85||(LA48_0>=88 && LA48_0<=91)||LA48_0==98||(LA48_0>=103 && LA48_0<=104)) ) {
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
                    // InternalCompleteOCL.g:2130:3: ( () otherlv_1= '?' )
                    {
                    // InternalCompleteOCL.g:2130:3: ( () otherlv_1= '?' )
                    // InternalCompleteOCL.g:2131:4: () otherlv_1= '?'
                    {
                    // InternalCompleteOCL.g:2131:4: ()
                    // InternalCompleteOCL.g:2132:5:
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
                    // InternalCompleteOCL.g:2147:3: this_ExpCS_2= ruleExpCS
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
    // InternalCompleteOCL.g:2162:1: entryRuleNavigationOperatorName returns [String current=null] : iv_ruleNavigationOperatorName= ruleNavigationOperatorName EOF ;
    public final String entryRuleNavigationOperatorName() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleNavigationOperatorName = null;


        try {
            // InternalCompleteOCL.g:2162:62: (iv_ruleNavigationOperatorName= ruleNavigationOperatorName EOF )
            // InternalCompleteOCL.g:2163:2: iv_ruleNavigationOperatorName= ruleNavigationOperatorName EOF
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
    // InternalCompleteOCL.g:2169:1: ruleNavigationOperatorName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_EssentialOCLNavigationOperatorName_0= ruleEssentialOCLNavigationOperatorName | this_CompleteOCLNavigationOperatorName_1= ruleCompleteOCLNavigationOperatorName ) ;
    public final AntlrDatatypeRuleToken ruleNavigationOperatorName() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        AntlrDatatypeRuleToken this_EssentialOCLNavigationOperatorName_0 = null;

        AntlrDatatypeRuleToken this_CompleteOCLNavigationOperatorName_1 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:2175:2: ( (this_EssentialOCLNavigationOperatorName_0= ruleEssentialOCLNavigationOperatorName | this_CompleteOCLNavigationOperatorName_1= ruleCompleteOCLNavigationOperatorName ) )
            // InternalCompleteOCL.g:2176:2: (this_EssentialOCLNavigationOperatorName_0= ruleEssentialOCLNavigationOperatorName | this_CompleteOCLNavigationOperatorName_1= ruleCompleteOCLNavigationOperatorName )
            {
            // InternalCompleteOCL.g:2176:2: (this_EssentialOCLNavigationOperatorName_0= ruleEssentialOCLNavigationOperatorName | this_CompleteOCLNavigationOperatorName_1= ruleCompleteOCLNavigationOperatorName )
            int alt49=2;
            int LA49_0 = input.LA(1);

            if ( ((LA49_0>=69 && LA49_0<=72)) ) {
                alt49=1;
            }
            else if ( ((LA49_0>=17 && LA49_0<=18)) ) {
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
                    // InternalCompleteOCL.g:2177:3: this_EssentialOCLNavigationOperatorName_0= ruleEssentialOCLNavigationOperatorName
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
                    // InternalCompleteOCL.g:2188:3: this_CompleteOCLNavigationOperatorName_1= ruleCompleteOCLNavigationOperatorName
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
    // InternalCompleteOCL.g:2202:1: entryRulePrimitiveTypeIdentifier returns [String current=null] : iv_rulePrimitiveTypeIdentifier= rulePrimitiveTypeIdentifier EOF ;
    public final String entryRulePrimitiveTypeIdentifier() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulePrimitiveTypeIdentifier = null;


        try {
            // InternalCompleteOCL.g:2202:63: (iv_rulePrimitiveTypeIdentifier= rulePrimitiveTypeIdentifier EOF )
            // InternalCompleteOCL.g:2203:2: iv_rulePrimitiveTypeIdentifier= rulePrimitiveTypeIdentifier EOF
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
    // InternalCompleteOCL.g:2209:1: rulePrimitiveTypeIdentifier returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= 'Boolean' | kw= 'Integer' | kw= 'Real' | kw= 'String' | kw= 'UnlimitedNatural' | kw= 'OclAny' | kw= 'OclInvalid' | kw= 'OclMessage' | kw= 'OclState' | kw= 'OclVoid' ) ;
    public final AntlrDatatypeRuleToken rulePrimitiveTypeIdentifier() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;


        	enterRule();

        try {
            // InternalCompleteOCL.g:2215:2: ( (kw= 'Boolean' | kw= 'Integer' | kw= 'Real' | kw= 'String' | kw= 'UnlimitedNatural' | kw= 'OclAny' | kw= 'OclInvalid' | kw= 'OclMessage' | kw= 'OclState' | kw= 'OclVoid' ) )
            // InternalCompleteOCL.g:2216:2: (kw= 'Boolean' | kw= 'Integer' | kw= 'Real' | kw= 'String' | kw= 'UnlimitedNatural' | kw= 'OclAny' | kw= 'OclInvalid' | kw= 'OclMessage' | kw= 'OclState' | kw= 'OclVoid' )
            {
            // InternalCompleteOCL.g:2216:2: (kw= 'Boolean' | kw= 'Integer' | kw= 'Real' | kw= 'String' | kw= 'UnlimitedNatural' | kw= 'OclAny' | kw= 'OclInvalid' | kw= 'OclMessage' | kw= 'OclState' | kw= 'OclVoid' )
            int alt50=10;
            switch ( input.LA(1) ) {
            case 42:
                {
                alt50=1;
                }
                break;
            case 43:
                {
                alt50=2;
                }
                break;
            case 44:
                {
                alt50=3;
                }
                break;
            case 45:
                {
                alt50=4;
                }
                break;
            case 46:
                {
                alt50=5;
                }
                break;
            case 47:
                {
                alt50=6;
                }
                break;
            case 48:
                {
                alt50=7;
                }
                break;
            case 49:
                {
                alt50=8;
                }
                break;
            case 50:
                {
                alt50=9;
                }
                break;
            case 51:
                {
                alt50=10;
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
                    // InternalCompleteOCL.g:2217:3: kw= 'Boolean'
                    {
                    kw=(Token)match(input,42,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getPrimitiveTypeIdentifierAccess().getBooleanKeyword_0());

                    }

                    }
                    break;
                case 2 :
                    // InternalCompleteOCL.g:2223:3: kw= 'Integer'
                    {
                    kw=(Token)match(input,43,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getPrimitiveTypeIdentifierAccess().getIntegerKeyword_1());

                    }

                    }
                    break;
                case 3 :
                    // InternalCompleteOCL.g:2229:3: kw= 'Real'
                    {
                    kw=(Token)match(input,44,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getPrimitiveTypeIdentifierAccess().getRealKeyword_2());

                    }

                    }
                    break;
                case 4 :
                    // InternalCompleteOCL.g:2235:3: kw= 'String'
                    {
                    kw=(Token)match(input,45,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getPrimitiveTypeIdentifierAccess().getStringKeyword_3());

                    }

                    }
                    break;
                case 5 :
                    // InternalCompleteOCL.g:2241:3: kw= 'UnlimitedNatural'
                    {
                    kw=(Token)match(input,46,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getPrimitiveTypeIdentifierAccess().getUnlimitedNaturalKeyword_4());

                    }

                    }
                    break;
                case 6 :
                    // InternalCompleteOCL.g:2247:3: kw= 'OclAny'
                    {
                    kw=(Token)match(input,47,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getPrimitiveTypeIdentifierAccess().getOclAnyKeyword_5());

                    }

                    }
                    break;
                case 7 :
                    // InternalCompleteOCL.g:2253:3: kw= 'OclInvalid'
                    {
                    kw=(Token)match(input,48,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getPrimitiveTypeIdentifierAccess().getOclInvalidKeyword_6());

                    }

                    }
                    break;
                case 8 :
                    // InternalCompleteOCL.g:2259:3: kw= 'OclMessage'
                    {
                    kw=(Token)match(input,49,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getPrimitiveTypeIdentifierAccess().getOclMessageKeyword_7());

                    }

                    }
                    break;
                case 9 :
                    // InternalCompleteOCL.g:2265:3: kw= 'OclState'
                    {
                    kw=(Token)match(input,50,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getPrimitiveTypeIdentifierAccess().getOclStateKeyword_8());

                    }

                    }
                    break;
                case 10 :
                    // InternalCompleteOCL.g:2271:3: kw= 'OclVoid'
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
    // InternalCompleteOCL.g:2280:1: entryRuleEssentialOCLUnaryOperatorName returns [String current=null] : iv_ruleEssentialOCLUnaryOperatorName= ruleEssentialOCLUnaryOperatorName EOF ;
    public final String entryRuleEssentialOCLUnaryOperatorName() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleEssentialOCLUnaryOperatorName = null;


        try {
            // InternalCompleteOCL.g:2280:69: (iv_ruleEssentialOCLUnaryOperatorName= ruleEssentialOCLUnaryOperatorName EOF )
            // InternalCompleteOCL.g:2281:2: iv_ruleEssentialOCLUnaryOperatorName= ruleEssentialOCLUnaryOperatorName EOF
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
    // InternalCompleteOCL.g:2287:1: ruleEssentialOCLUnaryOperatorName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= '-' | kw= 'not' | kw= 'not2' ) ;
    public final AntlrDatatypeRuleToken ruleEssentialOCLUnaryOperatorName() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;


        	enterRule();

        try {
            // InternalCompleteOCL.g:2293:2: ( (kw= '-' | kw= 'not' | kw= 'not2' ) )
            // InternalCompleteOCL.g:2294:2: (kw= '-' | kw= 'not' | kw= 'not2' )
            {
            // InternalCompleteOCL.g:2294:2: (kw= '-' | kw= 'not' | kw= 'not2' )
            int alt51=3;
            switch ( input.LA(1) ) {
            case 52:
                {
                alt51=1;
                }
                break;
            case 53:
                {
                alt51=2;
                }
                break;
            case 54:
                {
                alt51=3;
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
                    // InternalCompleteOCL.g:2295:3: kw= '-'
                    {
                    kw=(Token)match(input,52,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getEssentialOCLUnaryOperatorNameAccess().getHyphenMinusKeyword_0());

                    }

                    }
                    break;
                case 2 :
                    // InternalCompleteOCL.g:2301:3: kw= 'not'
                    {
                    kw=(Token)match(input,53,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getEssentialOCLUnaryOperatorNameAccess().getNotKeyword_1());

                    }

                    }
                    break;
                case 3 :
                    // InternalCompleteOCL.g:2307:3: kw= 'not2'
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
    // InternalCompleteOCL.g:2316:1: entryRuleEssentialOCLInfixOperatorName returns [String current=null] : iv_ruleEssentialOCLInfixOperatorName= ruleEssentialOCLInfixOperatorName EOF ;
    public final String entryRuleEssentialOCLInfixOperatorName() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleEssentialOCLInfixOperatorName = null;


        try {
            // InternalCompleteOCL.g:2316:69: (iv_ruleEssentialOCLInfixOperatorName= ruleEssentialOCLInfixOperatorName EOF )
            // InternalCompleteOCL.g:2317:2: iv_ruleEssentialOCLInfixOperatorName= ruleEssentialOCLInfixOperatorName EOF
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
    // InternalCompleteOCL.g:2323:1: ruleEssentialOCLInfixOperatorName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= '*' | kw= '/' | kw= '+' | kw= '-' | kw= '>' | kw= '<' | kw= '>=' | kw= '<=' | kw= '=' | kw= '<>' | kw= 'and' | kw= 'and2' | kw= 'implies' | kw= 'implies2' | kw= 'or' | kw= 'or2' | kw= 'xor' | kw= 'xor2' ) ;
    public final AntlrDatatypeRuleToken ruleEssentialOCLInfixOperatorName() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;


        	enterRule();

        try {
            // InternalCompleteOCL.g:2329:2: ( (kw= '*' | kw= '/' | kw= '+' | kw= '-' | kw= '>' | kw= '<' | kw= '>=' | kw= '<=' | kw= '=' | kw= '<>' | kw= 'and' | kw= 'and2' | kw= 'implies' | kw= 'implies2' | kw= 'or' | kw= 'or2' | kw= 'xor' | kw= 'xor2' ) )
            // InternalCompleteOCL.g:2330:2: (kw= '*' | kw= '/' | kw= '+' | kw= '-' | kw= '>' | kw= '<' | kw= '>=' | kw= '<=' | kw= '=' | kw= '<>' | kw= 'and' | kw= 'and2' | kw= 'implies' | kw= 'implies2' | kw= 'or' | kw= 'or2' | kw= 'xor' | kw= 'xor2' )
            {
            // InternalCompleteOCL.g:2330:2: (kw= '*' | kw= '/' | kw= '+' | kw= '-' | kw= '>' | kw= '<' | kw= '>=' | kw= '<=' | kw= '=' | kw= '<>' | kw= 'and' | kw= 'and2' | kw= 'implies' | kw= 'implies2' | kw= 'or' | kw= 'or2' | kw= 'xor' | kw= 'xor2' )
            int alt52=18;
            switch ( input.LA(1) ) {
            case 55:
                {
                alt52=1;
                }
                break;
            case 56:
                {
                alt52=2;
                }
                break;
            case 57:
                {
                alt52=3;
                }
                break;
            case 52:
                {
                alt52=4;
                }
                break;
            case 40:
                {
                alt52=5;
                }
                break;
            case 39:
                {
                alt52=6;
                }
                break;
            case 58:
                {
                alt52=7;
                }
                break;
            case 59:
                {
                alt52=8;
                }
                break;
            case 29:
                {
                alt52=9;
                }
                break;
            case 60:
                {
                alt52=10;
                }
                break;
            case 61:
                {
                alt52=11;
                }
                break;
            case 62:
                {
                alt52=12;
                }
                break;
            case 63:
                {
                alt52=13;
                }
                break;
            case 64:
                {
                alt52=14;
                }
                break;
            case 65:
                {
                alt52=15;
                }
                break;
            case 66:
                {
                alt52=16;
                }
                break;
            case 67:
                {
                alt52=17;
                }
                break;
            case 68:
                {
                alt52=18;
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
                    // InternalCompleteOCL.g:2331:3: kw= '*'
                    {
                    kw=(Token)match(input,55,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getEssentialOCLInfixOperatorNameAccess().getAsteriskKeyword_0());

                    }

                    }
                    break;
                case 2 :
                    // InternalCompleteOCL.g:2337:3: kw= '/'
                    {
                    kw=(Token)match(input,56,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getEssentialOCLInfixOperatorNameAccess().getSolidusKeyword_1());

                    }

                    }
                    break;
                case 3 :
                    // InternalCompleteOCL.g:2343:3: kw= '+'
                    {
                    kw=(Token)match(input,57,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getEssentialOCLInfixOperatorNameAccess().getPlusSignKeyword_2());

                    }

                    }
                    break;
                case 4 :
                    // InternalCompleteOCL.g:2349:3: kw= '-'
                    {
                    kw=(Token)match(input,52,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getEssentialOCLInfixOperatorNameAccess().getHyphenMinusKeyword_3());

                    }

                    }
                    break;
                case 5 :
                    // InternalCompleteOCL.g:2355:3: kw= '>'
                    {
                    kw=(Token)match(input,40,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getEssentialOCLInfixOperatorNameAccess().getGreaterThanSignKeyword_4());

                    }

                    }
                    break;
                case 6 :
                    // InternalCompleteOCL.g:2361:3: kw= '<'
                    {
                    kw=(Token)match(input,39,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getEssentialOCLInfixOperatorNameAccess().getLessThanSignKeyword_5());

                    }

                    }
                    break;
                case 7 :
                    // InternalCompleteOCL.g:2367:3: kw= '>='
                    {
                    kw=(Token)match(input,58,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getEssentialOCLInfixOperatorNameAccess().getGreaterThanSignEqualsSignKeyword_6());

                    }

                    }
                    break;
                case 8 :
                    // InternalCompleteOCL.g:2373:3: kw= '<='
                    {
                    kw=(Token)match(input,59,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getEssentialOCLInfixOperatorNameAccess().getLessThanSignEqualsSignKeyword_7());

                    }

                    }
                    break;
                case 9 :
                    // InternalCompleteOCL.g:2379:3: kw= '='
                    {
                    kw=(Token)match(input,29,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getEssentialOCLInfixOperatorNameAccess().getEqualsSignKeyword_8());

                    }

                    }
                    break;
                case 10 :
                    // InternalCompleteOCL.g:2385:3: kw= '<>'
                    {
                    kw=(Token)match(input,60,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getEssentialOCLInfixOperatorNameAccess().getLessThanSignGreaterThanSignKeyword_9());

                    }

                    }
                    break;
                case 11 :
                    // InternalCompleteOCL.g:2391:3: kw= 'and'
                    {
                    kw=(Token)match(input,61,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getEssentialOCLInfixOperatorNameAccess().getAndKeyword_10());

                    }

                    }
                    break;
                case 12 :
                    // InternalCompleteOCL.g:2397:3: kw= 'and2'
                    {
                    kw=(Token)match(input,62,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getEssentialOCLInfixOperatorNameAccess().getAnd2Keyword_11());

                    }

                    }
                    break;
                case 13 :
                    // InternalCompleteOCL.g:2403:3: kw= 'implies'
                    {
                    kw=(Token)match(input,63,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getEssentialOCLInfixOperatorNameAccess().getImpliesKeyword_12());

                    }

                    }
                    break;
                case 14 :
                    // InternalCompleteOCL.g:2409:3: kw= 'implies2'
                    {
                    kw=(Token)match(input,64,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getEssentialOCLInfixOperatorNameAccess().getImplies2Keyword_13());

                    }

                    }
                    break;
                case 15 :
                    // InternalCompleteOCL.g:2415:3: kw= 'or'
                    {
                    kw=(Token)match(input,65,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getEssentialOCLInfixOperatorNameAccess().getOrKeyword_14());

                    }

                    }
                    break;
                case 16 :
                    // InternalCompleteOCL.g:2421:3: kw= 'or2'
                    {
                    kw=(Token)match(input,66,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getEssentialOCLInfixOperatorNameAccess().getOr2Keyword_15());

                    }

                    }
                    break;
                case 17 :
                    // InternalCompleteOCL.g:2427:3: kw= 'xor'
                    {
                    kw=(Token)match(input,67,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getEssentialOCLInfixOperatorNameAccess().getXorKeyword_16());

                    }

                    }
                    break;
                case 18 :
                    // InternalCompleteOCL.g:2433:3: kw= 'xor2'
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
    // InternalCompleteOCL.g:2442:1: entryRuleEssentialOCLNavigationOperatorName returns [String current=null] : iv_ruleEssentialOCLNavigationOperatorName= ruleEssentialOCLNavigationOperatorName EOF ;
    public final String entryRuleEssentialOCLNavigationOperatorName() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleEssentialOCLNavigationOperatorName = null;


        try {
            // InternalCompleteOCL.g:2442:74: (iv_ruleEssentialOCLNavigationOperatorName= ruleEssentialOCLNavigationOperatorName EOF )
            // InternalCompleteOCL.g:2443:2: iv_ruleEssentialOCLNavigationOperatorName= ruleEssentialOCLNavigationOperatorName EOF
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
    // InternalCompleteOCL.g:2449:1: ruleEssentialOCLNavigationOperatorName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= '.' | kw= '->' | kw= '?.' | kw= '?->' ) ;
    public final AntlrDatatypeRuleToken ruleEssentialOCLNavigationOperatorName() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;


        	enterRule();

        try {
            // InternalCompleteOCL.g:2455:2: ( (kw= '.' | kw= '->' | kw= '?.' | kw= '?->' ) )
            // InternalCompleteOCL.g:2456:2: (kw= '.' | kw= '->' | kw= '?.' | kw= '?->' )
            {
            // InternalCompleteOCL.g:2456:2: (kw= '.' | kw= '->' | kw= '?.' | kw= '?->' )
            int alt53=4;
            switch ( input.LA(1) ) {
            case 69:
                {
                alt53=1;
                }
                break;
            case 70:
                {
                alt53=2;
                }
                break;
            case 71:
                {
                alt53=3;
                }
                break;
            case 72:
                {
                alt53=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 53, 0, input);

                throw nvae;
            }

            switch (alt53) {
                case 1 :
                    // InternalCompleteOCL.g:2457:3: kw= '.'
                    {
                    kw=(Token)match(input,69,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getEssentialOCLNavigationOperatorNameAccess().getFullStopKeyword_0());

                    }

                    }
                    break;
                case 2 :
                    // InternalCompleteOCL.g:2463:3: kw= '->'
                    {
                    kw=(Token)match(input,70,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getEssentialOCLNavigationOperatorNameAccess().getHyphenMinusGreaterThanSignKeyword_1());

                    }

                    }
                    break;
                case 3 :
                    // InternalCompleteOCL.g:2469:3: kw= '?.'
                    {
                    kw=(Token)match(input,71,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getEssentialOCLNavigationOperatorNameAccess().getQuestionMarkFullStopKeyword_2());

                    }

                    }
                    break;
                case 4 :
                    // InternalCompleteOCL.g:2475:3: kw= '?->'
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
    // InternalCompleteOCL.g:2484:1: entryRuleBinaryOperatorName returns [String current=null] : iv_ruleBinaryOperatorName= ruleBinaryOperatorName EOF ;
    public final String entryRuleBinaryOperatorName() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleBinaryOperatorName = null;


        try {
            // InternalCompleteOCL.g:2484:58: (iv_ruleBinaryOperatorName= ruleBinaryOperatorName EOF )
            // InternalCompleteOCL.g:2485:2: iv_ruleBinaryOperatorName= ruleBinaryOperatorName EOF
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
    // InternalCompleteOCL.g:2491:1: ruleBinaryOperatorName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_InfixOperatorName_0= ruleInfixOperatorName | this_NavigationOperatorName_1= ruleNavigationOperatorName ) ;
    public final AntlrDatatypeRuleToken ruleBinaryOperatorName() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        AntlrDatatypeRuleToken this_InfixOperatorName_0 = null;

        AntlrDatatypeRuleToken this_NavigationOperatorName_1 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:2497:2: ( (this_InfixOperatorName_0= ruleInfixOperatorName | this_NavigationOperatorName_1= ruleNavigationOperatorName ) )
            // InternalCompleteOCL.g:2498:2: (this_InfixOperatorName_0= ruleInfixOperatorName | this_NavigationOperatorName_1= ruleNavigationOperatorName )
            {
            // InternalCompleteOCL.g:2498:2: (this_InfixOperatorName_0= ruleInfixOperatorName | this_NavigationOperatorName_1= ruleNavigationOperatorName )
            int alt54=2;
            int LA54_0 = input.LA(1);

            if ( (LA54_0==29||(LA54_0>=39 && LA54_0<=40)||LA54_0==52||(LA54_0>=55 && LA54_0<=68)) ) {
                alt54=1;
            }
            else if ( ((LA54_0>=17 && LA54_0<=18)||(LA54_0>=69 && LA54_0<=72)) ) {
                alt54=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 54, 0, input);

                throw nvae;
            }
            switch (alt54) {
                case 1 :
                    // InternalCompleteOCL.g:2499:3: this_InfixOperatorName_0= ruleInfixOperatorName
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
                    // InternalCompleteOCL.g:2510:3: this_NavigationOperatorName_1= ruleNavigationOperatorName
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
    // InternalCompleteOCL.g:2524:1: entryRuleInfixOperatorName returns [String current=null] : iv_ruleInfixOperatorName= ruleInfixOperatorName EOF ;
    public final String entryRuleInfixOperatorName() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleInfixOperatorName = null;


        try {
            // InternalCompleteOCL.g:2524:57: (iv_ruleInfixOperatorName= ruleInfixOperatorName EOF )
            // InternalCompleteOCL.g:2525:2: iv_ruleInfixOperatorName= ruleInfixOperatorName EOF
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
    // InternalCompleteOCL.g:2531:1: ruleInfixOperatorName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_EssentialOCLInfixOperatorName_0= ruleEssentialOCLInfixOperatorName ;
    public final AntlrDatatypeRuleToken ruleInfixOperatorName() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        AntlrDatatypeRuleToken this_EssentialOCLInfixOperatorName_0 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:2537:2: (this_EssentialOCLInfixOperatorName_0= ruleEssentialOCLInfixOperatorName )
            // InternalCompleteOCL.g:2538:2: this_EssentialOCLInfixOperatorName_0= ruleEssentialOCLInfixOperatorName
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
    // InternalCompleteOCL.g:2551:1: entryRuleUnaryOperatorName returns [String current=null] : iv_ruleUnaryOperatorName= ruleUnaryOperatorName EOF ;
    public final String entryRuleUnaryOperatorName() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleUnaryOperatorName = null;


        try {
            // InternalCompleteOCL.g:2551:57: (iv_ruleUnaryOperatorName= ruleUnaryOperatorName EOF )
            // InternalCompleteOCL.g:2552:2: iv_ruleUnaryOperatorName= ruleUnaryOperatorName EOF
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
    // InternalCompleteOCL.g:2558:1: ruleUnaryOperatorName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_EssentialOCLUnaryOperatorName_0= ruleEssentialOCLUnaryOperatorName ;
    public final AntlrDatatypeRuleToken ruleUnaryOperatorName() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        AntlrDatatypeRuleToken this_EssentialOCLUnaryOperatorName_0 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:2564:2: (this_EssentialOCLUnaryOperatorName_0= ruleEssentialOCLUnaryOperatorName )
            // InternalCompleteOCL.g:2565:2: this_EssentialOCLUnaryOperatorName_0= ruleEssentialOCLUnaryOperatorName
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
    // InternalCompleteOCL.g:2578:1: entryRuleEssentialOCLUnrestrictedName returns [String current=null] : iv_ruleEssentialOCLUnrestrictedName= ruleEssentialOCLUnrestrictedName EOF ;
    public final String entryRuleEssentialOCLUnrestrictedName() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleEssentialOCLUnrestrictedName = null;


        try {
            // InternalCompleteOCL.g:2578:68: (iv_ruleEssentialOCLUnrestrictedName= ruleEssentialOCLUnrestrictedName EOF )
            // InternalCompleteOCL.g:2579:2: iv_ruleEssentialOCLUnrestrictedName= ruleEssentialOCLUnrestrictedName EOF
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
    // InternalCompleteOCL.g:2585:1: ruleEssentialOCLUnrestrictedName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_Identifier_0= ruleIdentifier ;
    public final AntlrDatatypeRuleToken ruleEssentialOCLUnrestrictedName() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        AntlrDatatypeRuleToken this_Identifier_0 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:2591:2: (this_Identifier_0= ruleIdentifier )
            // InternalCompleteOCL.g:2592:2: this_Identifier_0= ruleIdentifier
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
    // InternalCompleteOCL.g:2605:1: entryRuleEssentialOCLUnreservedName returns [String current=null] : iv_ruleEssentialOCLUnreservedName= ruleEssentialOCLUnreservedName EOF ;
    public final String entryRuleEssentialOCLUnreservedName() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleEssentialOCLUnreservedName = null;


        try {
            // InternalCompleteOCL.g:2605:66: (iv_ruleEssentialOCLUnreservedName= ruleEssentialOCLUnreservedName EOF )
            // InternalCompleteOCL.g:2606:2: iv_ruleEssentialOCLUnreservedName= ruleEssentialOCLUnreservedName EOF
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
    // InternalCompleteOCL.g:2612:1: ruleEssentialOCLUnreservedName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_UnrestrictedName_0= ruleUnrestrictedName | this_CollectionTypeIdentifier_1= ruleCollectionTypeIdentifier | this_PrimitiveTypeIdentifier_2= rulePrimitiveTypeIdentifier | kw= 'Map' | kw= 'Tuple' ) ;
    public final AntlrDatatypeRuleToken ruleEssentialOCLUnreservedName() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        AntlrDatatypeRuleToken this_UnrestrictedName_0 = null;

        AntlrDatatypeRuleToken this_CollectionTypeIdentifier_1 = null;

        AntlrDatatypeRuleToken this_PrimitiveTypeIdentifier_2 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:2618:2: ( (this_UnrestrictedName_0= ruleUnrestrictedName | this_CollectionTypeIdentifier_1= ruleCollectionTypeIdentifier | this_PrimitiveTypeIdentifier_2= rulePrimitiveTypeIdentifier | kw= 'Map' | kw= 'Tuple' ) )
            // InternalCompleteOCL.g:2619:2: (this_UnrestrictedName_0= ruleUnrestrictedName | this_CollectionTypeIdentifier_1= ruleCollectionTypeIdentifier | this_PrimitiveTypeIdentifier_2= rulePrimitiveTypeIdentifier | kw= 'Map' | kw= 'Tuple' )
            {
            // InternalCompleteOCL.g:2619:2: (this_UnrestrictedName_0= ruleUnrestrictedName | this_CollectionTypeIdentifier_1= ruleCollectionTypeIdentifier | this_PrimitiveTypeIdentifier_2= rulePrimitiveTypeIdentifier | kw= 'Map' | kw= 'Tuple' )
            int alt55=5;
            switch ( input.LA(1) ) {
            case RULE_SIMPLE_ID:
            case RULE_ESCAPED_ID:
            case 30:
            case 31:
            case 32:
                {
                alt55=1;
                }
                break;
            case 76:
            case 77:
            case 78:
            case 79:
            case 80:
                {
                alt55=2;
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
                alt55=3;
                }
                break;
            case 73:
                {
                alt55=4;
                }
                break;
            case 74:
                {
                alt55=5;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 55, 0, input);

                throw nvae;
            }

            switch (alt55) {
                case 1 :
                    // InternalCompleteOCL.g:2620:3: this_UnrestrictedName_0= ruleUnrestrictedName
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
                    // InternalCompleteOCL.g:2631:3: this_CollectionTypeIdentifier_1= ruleCollectionTypeIdentifier
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
                    // InternalCompleteOCL.g:2642:3: this_PrimitiveTypeIdentifier_2= rulePrimitiveTypeIdentifier
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
                    // InternalCompleteOCL.g:2653:3: kw= 'Map'
                    {
                    kw=(Token)match(input,73,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getEssentialOCLUnreservedNameAccess().getMapKeyword_3());

                    }

                    }
                    break;
                case 5 :
                    // InternalCompleteOCL.g:2659:3: kw= 'Tuple'
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
    // InternalCompleteOCL.g:2668:1: entryRuleUnreservedName returns [String current=null] : iv_ruleUnreservedName= ruleUnreservedName EOF ;
    public final String entryRuleUnreservedName() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleUnreservedName = null;


        try {
            // InternalCompleteOCL.g:2668:54: (iv_ruleUnreservedName= ruleUnreservedName EOF )
            // InternalCompleteOCL.g:2669:2: iv_ruleUnreservedName= ruleUnreservedName EOF
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
    // InternalCompleteOCL.g:2675:1: ruleUnreservedName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_EssentialOCLUnreservedName_0= ruleEssentialOCLUnreservedName ;
    public final AntlrDatatypeRuleToken ruleUnreservedName() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        AntlrDatatypeRuleToken this_EssentialOCLUnreservedName_0 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:2681:2: (this_EssentialOCLUnreservedName_0= ruleEssentialOCLUnreservedName )
            // InternalCompleteOCL.g:2682:2: this_EssentialOCLUnreservedName_0= ruleEssentialOCLUnreservedName
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
    // InternalCompleteOCL.g:2695:1: entryRuleURIPathNameCS returns [EObject current=null] : iv_ruleURIPathNameCS= ruleURIPathNameCS EOF ;
    public final EObject entryRuleURIPathNameCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleURIPathNameCS = null;


        try {
            // InternalCompleteOCL.g:2695:54: (iv_ruleURIPathNameCS= ruleURIPathNameCS EOF )
            // InternalCompleteOCL.g:2696:2: iv_ruleURIPathNameCS= ruleURIPathNameCS EOF
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
    // InternalCompleteOCL.g:2702:1: ruleURIPathNameCS returns [EObject current=null] : ( ( (lv_ownedPathElements_0_0= ruleURIFirstPathElementCS ) ) (otherlv_1= '::' ( (lv_ownedPathElements_2_0= ruleNextPathElementCS ) ) )* ) ;
    public final EObject ruleURIPathNameCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject lv_ownedPathElements_0_0 = null;

        EObject lv_ownedPathElements_2_0 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:2708:2: ( ( ( (lv_ownedPathElements_0_0= ruleURIFirstPathElementCS ) ) (otherlv_1= '::' ( (lv_ownedPathElements_2_0= ruleNextPathElementCS ) ) )* ) )
            // InternalCompleteOCL.g:2709:2: ( ( (lv_ownedPathElements_0_0= ruleURIFirstPathElementCS ) ) (otherlv_1= '::' ( (lv_ownedPathElements_2_0= ruleNextPathElementCS ) ) )* )
            {
            // InternalCompleteOCL.g:2709:2: ( ( (lv_ownedPathElements_0_0= ruleURIFirstPathElementCS ) ) (otherlv_1= '::' ( (lv_ownedPathElements_2_0= ruleNextPathElementCS ) ) )* )
            // InternalCompleteOCL.g:2710:3: ( (lv_ownedPathElements_0_0= ruleURIFirstPathElementCS ) ) (otherlv_1= '::' ( (lv_ownedPathElements_2_0= ruleNextPathElementCS ) ) )*
            {
            // InternalCompleteOCL.g:2710:3: ( (lv_ownedPathElements_0_0= ruleURIFirstPathElementCS ) )
            // InternalCompleteOCL.g:2711:4: (lv_ownedPathElements_0_0= ruleURIFirstPathElementCS )
            {
            // InternalCompleteOCL.g:2711:4: (lv_ownedPathElements_0_0= ruleURIFirstPathElementCS )
            // InternalCompleteOCL.g:2712:5: lv_ownedPathElements_0_0= ruleURIFirstPathElementCS
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getURIPathNameCSAccess().getOwnedPathElementsURIFirstPathElementCSParserRuleCall_0_0());

            }
            pushFollow(FollowSets000.FOLLOW_31);
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

            // InternalCompleteOCL.g:2729:3: (otherlv_1= '::' ( (lv_ownedPathElements_2_0= ruleNextPathElementCS ) ) )*
            loop56:
            do {
                int alt56=2;
                int LA56_0 = input.LA(1);

                if ( (LA56_0==75) ) {
                    alt56=1;
                }


                switch (alt56) {
            	case 1 :
            	    // InternalCompleteOCL.g:2730:4: otherlv_1= '::' ( (lv_ownedPathElements_2_0= ruleNextPathElementCS ) )
            	    {
            	    otherlv_1=(Token)match(input,75,FollowSets000.FOLLOW_5); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      				newLeafNode(otherlv_1, grammarAccess.getURIPathNameCSAccess().getColonColonKeyword_1_0());

            	    }
            	    // InternalCompleteOCL.g:2734:4: ( (lv_ownedPathElements_2_0= ruleNextPathElementCS ) )
            	    // InternalCompleteOCL.g:2735:5: (lv_ownedPathElements_2_0= ruleNextPathElementCS )
            	    {
            	    // InternalCompleteOCL.g:2735:5: (lv_ownedPathElements_2_0= ruleNextPathElementCS )
            	    // InternalCompleteOCL.g:2736:6: lv_ownedPathElements_2_0= ruleNextPathElementCS
            	    {
            	    if ( state.backtracking==0 ) {

            	      						newCompositeNode(grammarAccess.getURIPathNameCSAccess().getOwnedPathElementsNextPathElementCSParserRuleCall_1_1_0());

            	    }
            	    pushFollow(FollowSets000.FOLLOW_31);
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
            	    break loop56;
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
    // InternalCompleteOCL.g:2758:1: entryRuleURIFirstPathElementCS returns [EObject current=null] : iv_ruleURIFirstPathElementCS= ruleURIFirstPathElementCS EOF ;
    public final EObject entryRuleURIFirstPathElementCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleURIFirstPathElementCS = null;


        try {
            // InternalCompleteOCL.g:2758:62: (iv_ruleURIFirstPathElementCS= ruleURIFirstPathElementCS EOF )
            // InternalCompleteOCL.g:2759:2: iv_ruleURIFirstPathElementCS= ruleURIFirstPathElementCS EOF
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
    // InternalCompleteOCL.g:2765:1: ruleURIFirstPathElementCS returns [EObject current=null] : ( ( ( ruleUnrestrictedName ) ) | ( () ( ( ruleURI ) ) ) ) ;
    public final EObject ruleURIFirstPathElementCS() throws RecognitionException {
        EObject current = null;


        	enterRule();

        try {
            // InternalCompleteOCL.g:2771:2: ( ( ( ( ruleUnrestrictedName ) ) | ( () ( ( ruleURI ) ) ) ) )
            // InternalCompleteOCL.g:2772:2: ( ( ( ruleUnrestrictedName ) ) | ( () ( ( ruleURI ) ) ) )
            {
            // InternalCompleteOCL.g:2772:2: ( ( ( ruleUnrestrictedName ) ) | ( () ( ( ruleURI ) ) ) )
            int alt57=2;
            int LA57_0 = input.LA(1);

            if ( ((LA57_0>=RULE_SIMPLE_ID && LA57_0<=RULE_ESCAPED_ID)||(LA57_0>=30 && LA57_0<=32)) ) {
                alt57=1;
            }
            else if ( (LA57_0==RULE_SINGLE_QUOTED_STRING) ) {
                alt57=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 57, 0, input);

                throw nvae;
            }
            switch (alt57) {
                case 1 :
                    // InternalCompleteOCL.g:2773:3: ( ( ruleUnrestrictedName ) )
                    {
                    // InternalCompleteOCL.g:2773:3: ( ( ruleUnrestrictedName ) )
                    // InternalCompleteOCL.g:2774:4: ( ruleUnrestrictedName )
                    {
                    // InternalCompleteOCL.g:2774:4: ( ruleUnrestrictedName )
                    // InternalCompleteOCL.g:2775:5: ruleUnrestrictedName
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
                    // InternalCompleteOCL.g:2793:3: ( () ( ( ruleURI ) ) )
                    {
                    // InternalCompleteOCL.g:2793:3: ( () ( ( ruleURI ) ) )
                    // InternalCompleteOCL.g:2794:4: () ( ( ruleURI ) )
                    {
                    // InternalCompleteOCL.g:2794:4: ()
                    // InternalCompleteOCL.g:2795:5:
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

                    // InternalCompleteOCL.g:2804:4: ( ( ruleURI ) )
                    // InternalCompleteOCL.g:2805:5: ( ruleURI )
                    {
                    // InternalCompleteOCL.g:2805:5: ( ruleURI )
                    // InternalCompleteOCL.g:2806:6: ruleURI
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
    // InternalCompleteOCL.g:2828:1: entryRulePrimitiveTypeCS returns [EObject current=null] : iv_rulePrimitiveTypeCS= rulePrimitiveTypeCS EOF ;
    public final EObject entryRulePrimitiveTypeCS() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePrimitiveTypeCS = null;


        try {
            // InternalCompleteOCL.g:2828:56: (iv_rulePrimitiveTypeCS= rulePrimitiveTypeCS EOF )
            // InternalCompleteOCL.g:2829:2: iv_rulePrimitiveTypeCS= rulePrimitiveTypeCS EOF
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
    // InternalCompleteOCL.g:2835:1: rulePrimitiveTypeCS returns [EObject current=null] : ( (lv_name_0_0= rulePrimitiveTypeIdentifier ) ) ;
    public final EObject rulePrimitiveTypeCS() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_name_0_0 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:2841:2: ( ( (lv_name_0_0= rulePrimitiveTypeIdentifier ) ) )
            // InternalCompleteOCL.g:2842:2: ( (lv_name_0_0= rulePrimitiveTypeIdentifier ) )
            {
            // InternalCompleteOCL.g:2842:2: ( (lv_name_0_0= rulePrimitiveTypeIdentifier ) )
            // InternalCompleteOCL.g:2843:3: (lv_name_0_0= rulePrimitiveTypeIdentifier )
            {
            // InternalCompleteOCL.g:2843:3: (lv_name_0_0= rulePrimitiveTypeIdentifier )
            // InternalCompleteOCL.g:2844:4: lv_name_0_0= rulePrimitiveTypeIdentifier
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
    // InternalCompleteOCL.g:2864:1: entryRuleCollectionTypeIdentifier returns [String current=null] : iv_ruleCollectionTypeIdentifier= ruleCollectionTypeIdentifier EOF ;
    public final String entryRuleCollectionTypeIdentifier() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleCollectionTypeIdentifier = null;


        try {
            // InternalCompleteOCL.g:2864:64: (iv_ruleCollectionTypeIdentifier= ruleCollectionTypeIdentifier EOF )
            // InternalCompleteOCL.g:2865:2: iv_ruleCollectionTypeIdentifier= ruleCollectionTypeIdentifier EOF
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
    // InternalCompleteOCL.g:2871:1: ruleCollectionTypeIdentifier returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= 'Set' | kw= 'Bag' | kw= 'Sequence' | kw= 'Collection' | kw= 'OrderedSet' ) ;
    public final AntlrDatatypeRuleToken ruleCollectionTypeIdentifier() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;


        	enterRule();

        try {
            // InternalCompleteOCL.g:2877:2: ( (kw= 'Set' | kw= 'Bag' | kw= 'Sequence' | kw= 'Collection' | kw= 'OrderedSet' ) )
            // InternalCompleteOCL.g:2878:2: (kw= 'Set' | kw= 'Bag' | kw= 'Sequence' | kw= 'Collection' | kw= 'OrderedSet' )
            {
            // InternalCompleteOCL.g:2878:2: (kw= 'Set' | kw= 'Bag' | kw= 'Sequence' | kw= 'Collection' | kw= 'OrderedSet' )
            int alt58=5;
            switch ( input.LA(1) ) {
            case 76:
                {
                alt58=1;
                }
                break;
            case 77:
                {
                alt58=2;
                }
                break;
            case 78:
                {
                alt58=3;
                }
                break;
            case 79:
                {
                alt58=4;
                }
                break;
            case 80:
                {
                alt58=5;
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
                    // InternalCompleteOCL.g:2879:3: kw= 'Set'
                    {
                    kw=(Token)match(input,76,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getCollectionTypeIdentifierAccess().getSetKeyword_0());

                    }

                    }
                    break;
                case 2 :
                    // InternalCompleteOCL.g:2885:3: kw= 'Bag'
                    {
                    kw=(Token)match(input,77,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getCollectionTypeIdentifierAccess().getBagKeyword_1());

                    }

                    }
                    break;
                case 3 :
                    // InternalCompleteOCL.g:2891:3: kw= 'Sequence'
                    {
                    kw=(Token)match(input,78,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getCollectionTypeIdentifierAccess().getSequenceKeyword_2());

                    }

                    }
                    break;
                case 4 :
                    // InternalCompleteOCL.g:2897:3: kw= 'Collection'
                    {
                    kw=(Token)match(input,79,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getCollectionTypeIdentifierAccess().getCollectionKeyword_3());

                    }

                    }
                    break;
                case 5 :
                    // InternalCompleteOCL.g:2903:3: kw= 'OrderedSet'
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
    // InternalCompleteOCL.g:2912:1: entryRuleCollectionTypeCS returns [EObject current=null] : iv_ruleCollectionTypeCS= ruleCollectionTypeCS EOF ;
    public final EObject entryRuleCollectionTypeCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCollectionTypeCS = null;


        try {
            // InternalCompleteOCL.g:2912:57: (iv_ruleCollectionTypeCS= ruleCollectionTypeCS EOF )
            // InternalCompleteOCL.g:2913:2: iv_ruleCollectionTypeCS= ruleCollectionTypeCS EOF
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
    // InternalCompleteOCL.g:2919:1: ruleCollectionTypeCS returns [EObject current=null] : ( ( (lv_name_0_0= ruleCollectionTypeIdentifier ) ) (otherlv_1= '(' ( (lv_ownedType_2_0= ruleTypeExpWithoutMultiplicityCS ) ) ( (lv_ownedCollectionMultiplicity_3_0= ruleMultiplicityCS ) )? otherlv_4= ')' )? ) ;
    public final EObject ruleCollectionTypeCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_4=null;
        AntlrDatatypeRuleToken lv_name_0_0 = null;

        EObject lv_ownedType_2_0 = null;

        EObject lv_ownedCollectionMultiplicity_3_0 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:2925:2: ( ( ( (lv_name_0_0= ruleCollectionTypeIdentifier ) ) (otherlv_1= '(' ( (lv_ownedType_2_0= ruleTypeExpWithoutMultiplicityCS ) ) ( (lv_ownedCollectionMultiplicity_3_0= ruleMultiplicityCS ) )? otherlv_4= ')' )? ) )
            // InternalCompleteOCL.g:2926:2: ( ( (lv_name_0_0= ruleCollectionTypeIdentifier ) ) (otherlv_1= '(' ( (lv_ownedType_2_0= ruleTypeExpWithoutMultiplicityCS ) ) ( (lv_ownedCollectionMultiplicity_3_0= ruleMultiplicityCS ) )? otherlv_4= ')' )? )
            {
            // InternalCompleteOCL.g:2926:2: ( ( (lv_name_0_0= ruleCollectionTypeIdentifier ) ) (otherlv_1= '(' ( (lv_ownedType_2_0= ruleTypeExpWithoutMultiplicityCS ) ) ( (lv_ownedCollectionMultiplicity_3_0= ruleMultiplicityCS ) )? otherlv_4= ')' )? )
            // InternalCompleteOCL.g:2927:3: ( (lv_name_0_0= ruleCollectionTypeIdentifier ) ) (otherlv_1= '(' ( (lv_ownedType_2_0= ruleTypeExpWithoutMultiplicityCS ) ) ( (lv_ownedCollectionMultiplicity_3_0= ruleMultiplicityCS ) )? otherlv_4= ')' )?
            {
            // InternalCompleteOCL.g:2927:3: ( (lv_name_0_0= ruleCollectionTypeIdentifier ) )
            // InternalCompleteOCL.g:2928:4: (lv_name_0_0= ruleCollectionTypeIdentifier )
            {
            // InternalCompleteOCL.g:2928:4: (lv_name_0_0= ruleCollectionTypeIdentifier )
            // InternalCompleteOCL.g:2929:5: lv_name_0_0= ruleCollectionTypeIdentifier
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

            // InternalCompleteOCL.g:2946:3: (otherlv_1= '(' ( (lv_ownedType_2_0= ruleTypeExpWithoutMultiplicityCS ) ) ( (lv_ownedCollectionMultiplicity_3_0= ruleMultiplicityCS ) )? otherlv_4= ')' )?
            int alt60=2;
            int LA60_0 = input.LA(1);

            if ( (LA60_0==21) ) {
                alt60=1;
            }
            switch (alt60) {
                case 1 :
                    // InternalCompleteOCL.g:2947:4: otherlv_1= '(' ( (lv_ownedType_2_0= ruleTypeExpWithoutMultiplicityCS ) ) ( (lv_ownedCollectionMultiplicity_3_0= ruleMultiplicityCS ) )? otherlv_4= ')'
                    {
                    otherlv_1=(Token)match(input,21,FollowSets000.FOLLOW_21); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_1, grammarAccess.getCollectionTypeCSAccess().getLeftParenthesisKeyword_1_0());

                    }
                    // InternalCompleteOCL.g:2951:4: ( (lv_ownedType_2_0= ruleTypeExpWithoutMultiplicityCS ) )
                    // InternalCompleteOCL.g:2952:5: (lv_ownedType_2_0= ruleTypeExpWithoutMultiplicityCS )
                    {
                    // InternalCompleteOCL.g:2952:5: (lv_ownedType_2_0= ruleTypeExpWithoutMultiplicityCS )
                    // InternalCompleteOCL.g:2953:6: lv_ownedType_2_0= ruleTypeExpWithoutMultiplicityCS
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

                    // InternalCompleteOCL.g:2970:4: ( (lv_ownedCollectionMultiplicity_3_0= ruleMultiplicityCS ) )?
                    int alt59=2;
                    int LA59_0 = input.LA(1);

                    if ( (LA59_0==93) ) {
                        alt59=1;
                    }
                    switch (alt59) {
                        case 1 :
                            // InternalCompleteOCL.g:2971:5: (lv_ownedCollectionMultiplicity_3_0= ruleMultiplicityCS )
                            {
                            // InternalCompleteOCL.g:2971:5: (lv_ownedCollectionMultiplicity_3_0= ruleMultiplicityCS )
                            // InternalCompleteOCL.g:2972:6: lv_ownedCollectionMultiplicity_3_0= ruleMultiplicityCS
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
    // InternalCompleteOCL.g:2998:1: entryRuleMapTypeCS returns [EObject current=null] : iv_ruleMapTypeCS= ruleMapTypeCS EOF ;
    public final EObject entryRuleMapTypeCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMapTypeCS = null;


        try {
            // InternalCompleteOCL.g:2998:50: (iv_ruleMapTypeCS= ruleMapTypeCS EOF )
            // InternalCompleteOCL.g:2999:2: iv_ruleMapTypeCS= ruleMapTypeCS EOF
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
    // InternalCompleteOCL.g:3005:1: ruleMapTypeCS returns [EObject current=null] : ( ( (lv_name_0_0= 'Map' ) ) (otherlv_1= '(' ( (lv_ownedKeyType_2_0= ruleTypeExpCS ) ) otherlv_3= ',' ( (lv_ownedValueType_4_0= ruleTypeExpCS ) ) otherlv_5= ')' )? ) ;
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
            // InternalCompleteOCL.g:3011:2: ( ( ( (lv_name_0_0= 'Map' ) ) (otherlv_1= '(' ( (lv_ownedKeyType_2_0= ruleTypeExpCS ) ) otherlv_3= ',' ( (lv_ownedValueType_4_0= ruleTypeExpCS ) ) otherlv_5= ')' )? ) )
            // InternalCompleteOCL.g:3012:2: ( ( (lv_name_0_0= 'Map' ) ) (otherlv_1= '(' ( (lv_ownedKeyType_2_0= ruleTypeExpCS ) ) otherlv_3= ',' ( (lv_ownedValueType_4_0= ruleTypeExpCS ) ) otherlv_5= ')' )? )
            {
            // InternalCompleteOCL.g:3012:2: ( ( (lv_name_0_0= 'Map' ) ) (otherlv_1= '(' ( (lv_ownedKeyType_2_0= ruleTypeExpCS ) ) otherlv_3= ',' ( (lv_ownedValueType_4_0= ruleTypeExpCS ) ) otherlv_5= ')' )? )
            // InternalCompleteOCL.g:3013:3: ( (lv_name_0_0= 'Map' ) ) (otherlv_1= '(' ( (lv_ownedKeyType_2_0= ruleTypeExpCS ) ) otherlv_3= ',' ( (lv_ownedValueType_4_0= ruleTypeExpCS ) ) otherlv_5= ')' )?
            {
            // InternalCompleteOCL.g:3013:3: ( (lv_name_0_0= 'Map' ) )
            // InternalCompleteOCL.g:3014:4: (lv_name_0_0= 'Map' )
            {
            // InternalCompleteOCL.g:3014:4: (lv_name_0_0= 'Map' )
            // InternalCompleteOCL.g:3015:5: lv_name_0_0= 'Map'
            {
            lv_name_0_0=(Token)match(input,73,FollowSets000.FOLLOW_32); if (state.failed) return current;
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

            // InternalCompleteOCL.g:3027:3: (otherlv_1= '(' ( (lv_ownedKeyType_2_0= ruleTypeExpCS ) ) otherlv_3= ',' ( (lv_ownedValueType_4_0= ruleTypeExpCS ) ) otherlv_5= ')' )?
            int alt61=2;
            int LA61_0 = input.LA(1);

            if ( (LA61_0==21) ) {
                alt61=1;
            }
            switch (alt61) {
                case 1 :
                    // InternalCompleteOCL.g:3028:4: otherlv_1= '(' ( (lv_ownedKeyType_2_0= ruleTypeExpCS ) ) otherlv_3= ',' ( (lv_ownedValueType_4_0= ruleTypeExpCS ) ) otherlv_5= ')'
                    {
                    otherlv_1=(Token)match(input,21,FollowSets000.FOLLOW_21); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_1, grammarAccess.getMapTypeCSAccess().getLeftParenthesisKeyword_1_0());

                    }
                    // InternalCompleteOCL.g:3032:4: ( (lv_ownedKeyType_2_0= ruleTypeExpCS ) )
                    // InternalCompleteOCL.g:3033:5: (lv_ownedKeyType_2_0= ruleTypeExpCS )
                    {
                    // InternalCompleteOCL.g:3033:5: (lv_ownedKeyType_2_0= ruleTypeExpCS )
                    // InternalCompleteOCL.g:3034:6: lv_ownedKeyType_2_0= ruleTypeExpCS
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

                    otherlv_3=(Token)match(input,28,FollowSets000.FOLLOW_21); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_3, grammarAccess.getMapTypeCSAccess().getCommaKeyword_1_2());

                    }
                    // InternalCompleteOCL.g:3055:4: ( (lv_ownedValueType_4_0= ruleTypeExpCS ) )
                    // InternalCompleteOCL.g:3056:5: (lv_ownedValueType_4_0= ruleTypeExpCS )
                    {
                    // InternalCompleteOCL.g:3056:5: (lv_ownedValueType_4_0= ruleTypeExpCS )
                    // InternalCompleteOCL.g:3057:6: lv_ownedValueType_4_0= ruleTypeExpCS
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
    // InternalCompleteOCL.g:3083:1: entryRuleTupleTypeCS returns [EObject current=null] : iv_ruleTupleTypeCS= ruleTupleTypeCS EOF ;
    public final EObject entryRuleTupleTypeCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTupleTypeCS = null;


        try {
            // InternalCompleteOCL.g:3083:52: (iv_ruleTupleTypeCS= ruleTupleTypeCS EOF )
            // InternalCompleteOCL.g:3084:2: iv_ruleTupleTypeCS= ruleTupleTypeCS EOF
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
    // InternalCompleteOCL.g:3090:1: ruleTupleTypeCS returns [EObject current=null] : ( ( (lv_name_0_0= 'Tuple' ) ) (otherlv_1= '(' ( ( (lv_ownedParts_2_0= ruleTuplePartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleTuplePartCS ) ) )* )? otherlv_5= ')' )? ) ;
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
            // InternalCompleteOCL.g:3096:2: ( ( ( (lv_name_0_0= 'Tuple' ) ) (otherlv_1= '(' ( ( (lv_ownedParts_2_0= ruleTuplePartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleTuplePartCS ) ) )* )? otherlv_5= ')' )? ) )
            // InternalCompleteOCL.g:3097:2: ( ( (lv_name_0_0= 'Tuple' ) ) (otherlv_1= '(' ( ( (lv_ownedParts_2_0= ruleTuplePartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleTuplePartCS ) ) )* )? otherlv_5= ')' )? )
            {
            // InternalCompleteOCL.g:3097:2: ( ( (lv_name_0_0= 'Tuple' ) ) (otherlv_1= '(' ( ( (lv_ownedParts_2_0= ruleTuplePartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleTuplePartCS ) ) )* )? otherlv_5= ')' )? )
            // InternalCompleteOCL.g:3098:3: ( (lv_name_0_0= 'Tuple' ) ) (otherlv_1= '(' ( ( (lv_ownedParts_2_0= ruleTuplePartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleTuplePartCS ) ) )* )? otherlv_5= ')' )?
            {
            // InternalCompleteOCL.g:3098:3: ( (lv_name_0_0= 'Tuple' ) )
            // InternalCompleteOCL.g:3099:4: (lv_name_0_0= 'Tuple' )
            {
            // InternalCompleteOCL.g:3099:4: (lv_name_0_0= 'Tuple' )
            // InternalCompleteOCL.g:3100:5: lv_name_0_0= 'Tuple'
            {
            lv_name_0_0=(Token)match(input,74,FollowSets000.FOLLOW_32); if (state.failed) return current;
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

            // InternalCompleteOCL.g:3112:3: (otherlv_1= '(' ( ( (lv_ownedParts_2_0= ruleTuplePartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleTuplePartCS ) ) )* )? otherlv_5= ')' )?
            int alt64=2;
            int LA64_0 = input.LA(1);

            if ( (LA64_0==21) ) {
                alt64=1;
            }
            switch (alt64) {
                case 1 :
                    // InternalCompleteOCL.g:3113:4: otherlv_1= '(' ( ( (lv_ownedParts_2_0= ruleTuplePartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleTuplePartCS ) ) )* )? otherlv_5= ')'
                    {
                    otherlv_1=(Token)match(input,21,FollowSets000.FOLLOW_17); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_1, grammarAccess.getTupleTypeCSAccess().getLeftParenthesisKeyword_1_0());

                    }
                    // InternalCompleteOCL.g:3117:4: ( ( (lv_ownedParts_2_0= ruleTuplePartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleTuplePartCS ) ) )* )?
                    int alt63=2;
                    int LA63_0 = input.LA(1);

                    if ( ((LA63_0>=RULE_SIMPLE_ID && LA63_0<=RULE_ESCAPED_ID)||(LA63_0>=30 && LA63_0<=32)) ) {
                        alt63=1;
                    }
                    switch (alt63) {
                        case 1 :
                            // InternalCompleteOCL.g:3118:5: ( (lv_ownedParts_2_0= ruleTuplePartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleTuplePartCS ) ) )*
                            {
                            // InternalCompleteOCL.g:3118:5: ( (lv_ownedParts_2_0= ruleTuplePartCS ) )
                            // InternalCompleteOCL.g:3119:6: (lv_ownedParts_2_0= ruleTuplePartCS )
                            {
                            // InternalCompleteOCL.g:3119:6: (lv_ownedParts_2_0= ruleTuplePartCS )
                            // InternalCompleteOCL.g:3120:7: lv_ownedParts_2_0= ruleTuplePartCS
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

                            // InternalCompleteOCL.g:3137:5: (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleTuplePartCS ) ) )*
                            loop62:
                            do {
                                int alt62=2;
                                int LA62_0 = input.LA(1);

                                if ( (LA62_0==28) ) {
                                    alt62=1;
                                }


                                switch (alt62) {
                            	case 1 :
                            	    // InternalCompleteOCL.g:3138:6: otherlv_3= ',' ( (lv_ownedParts_4_0= ruleTuplePartCS ) )
                            	    {
                            	    otherlv_3=(Token)match(input,28,FollowSets000.FOLLOW_15); if (state.failed) return current;
                            	    if ( state.backtracking==0 ) {

                            	      						newLeafNode(otherlv_3, grammarAccess.getTupleTypeCSAccess().getCommaKeyword_1_1_1_0());

                            	    }
                            	    // InternalCompleteOCL.g:3142:6: ( (lv_ownedParts_4_0= ruleTuplePartCS ) )
                            	    // InternalCompleteOCL.g:3143:7: (lv_ownedParts_4_0= ruleTuplePartCS )
                            	    {
                            	    // InternalCompleteOCL.g:3143:7: (lv_ownedParts_4_0= ruleTuplePartCS )
                            	    // InternalCompleteOCL.g:3144:8: lv_ownedParts_4_0= ruleTuplePartCS
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
                            	    break loop62;
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
    // InternalCompleteOCL.g:3172:1: entryRuleTuplePartCS returns [EObject current=null] : iv_ruleTuplePartCS= ruleTuplePartCS EOF ;
    public final EObject entryRuleTuplePartCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTuplePartCS = null;


        try {
            // InternalCompleteOCL.g:3172:52: (iv_ruleTuplePartCS= ruleTuplePartCS EOF )
            // InternalCompleteOCL.g:3173:2: iv_ruleTuplePartCS= ruleTuplePartCS EOF
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
    // InternalCompleteOCL.g:3179:1: ruleTuplePartCS returns [EObject current=null] : ( ( (lv_name_0_0= ruleUnrestrictedName ) ) otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) ) ) ;
    public final EObject ruleTuplePartCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_name_0_0 = null;

        EObject lv_ownedType_2_0 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:3185:2: ( ( ( (lv_name_0_0= ruleUnrestrictedName ) ) otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) ) ) )
            // InternalCompleteOCL.g:3186:2: ( ( (lv_name_0_0= ruleUnrestrictedName ) ) otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) ) )
            {
            // InternalCompleteOCL.g:3186:2: ( ( (lv_name_0_0= ruleUnrestrictedName ) ) otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) ) )
            // InternalCompleteOCL.g:3187:3: ( (lv_name_0_0= ruleUnrestrictedName ) ) otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) )
            {
            // InternalCompleteOCL.g:3187:3: ( (lv_name_0_0= ruleUnrestrictedName ) )
            // InternalCompleteOCL.g:3188:4: (lv_name_0_0= ruleUnrestrictedName )
            {
            // InternalCompleteOCL.g:3188:4: (lv_name_0_0= ruleUnrestrictedName )
            // InternalCompleteOCL.g:3189:5: lv_name_0_0= ruleUnrestrictedName
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
            // InternalCompleteOCL.g:3210:3: ( (lv_ownedType_2_0= ruleTypeExpCS ) )
            // InternalCompleteOCL.g:3211:4: (lv_ownedType_2_0= ruleTypeExpCS )
            {
            // InternalCompleteOCL.g:3211:4: (lv_ownedType_2_0= ruleTypeExpCS )
            // InternalCompleteOCL.g:3212:5: lv_ownedType_2_0= ruleTypeExpCS
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
    // InternalCompleteOCL.g:3233:1: entryRuleCollectionLiteralExpCS returns [EObject current=null] : iv_ruleCollectionLiteralExpCS= ruleCollectionLiteralExpCS EOF ;
    public final EObject entryRuleCollectionLiteralExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCollectionLiteralExpCS = null;


        try {
            // InternalCompleteOCL.g:3233:63: (iv_ruleCollectionLiteralExpCS= ruleCollectionLiteralExpCS EOF )
            // InternalCompleteOCL.g:3234:2: iv_ruleCollectionLiteralExpCS= ruleCollectionLiteralExpCS EOF
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
    // InternalCompleteOCL.g:3240:1: ruleCollectionLiteralExpCS returns [EObject current=null] : ( ( (lv_ownedType_0_0= ruleCollectionTypeCS ) ) otherlv_1= '{' ( ( (lv_ownedParts_2_0= ruleCollectionLiteralPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleCollectionLiteralPartCS ) ) )* )? otherlv_5= '}' ) ;
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
            // InternalCompleteOCL.g:3246:2: ( ( ( (lv_ownedType_0_0= ruleCollectionTypeCS ) ) otherlv_1= '{' ( ( (lv_ownedParts_2_0= ruleCollectionLiteralPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleCollectionLiteralPartCS ) ) )* )? otherlv_5= '}' ) )
            // InternalCompleteOCL.g:3247:2: ( ( (lv_ownedType_0_0= ruleCollectionTypeCS ) ) otherlv_1= '{' ( ( (lv_ownedParts_2_0= ruleCollectionLiteralPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleCollectionLiteralPartCS ) ) )* )? otherlv_5= '}' )
            {
            // InternalCompleteOCL.g:3247:2: ( ( (lv_ownedType_0_0= ruleCollectionTypeCS ) ) otherlv_1= '{' ( ( (lv_ownedParts_2_0= ruleCollectionLiteralPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleCollectionLiteralPartCS ) ) )* )? otherlv_5= '}' )
            // InternalCompleteOCL.g:3248:3: ( (lv_ownedType_0_0= ruleCollectionTypeCS ) ) otherlv_1= '{' ( ( (lv_ownedParts_2_0= ruleCollectionLiteralPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleCollectionLiteralPartCS ) ) )* )? otherlv_5= '}'
            {
            // InternalCompleteOCL.g:3248:3: ( (lv_ownedType_0_0= ruleCollectionTypeCS ) )
            // InternalCompleteOCL.g:3249:4: (lv_ownedType_0_0= ruleCollectionTypeCS )
            {
            // InternalCompleteOCL.g:3249:4: (lv_ownedType_0_0= ruleCollectionTypeCS )
            // InternalCompleteOCL.g:3250:5: lv_ownedType_0_0= ruleCollectionTypeCS
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

            otherlv_1=(Token)match(input,81,FollowSets000.FOLLOW_36); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getCollectionLiteralExpCSAccess().getLeftCurlyBracketKeyword_1());

            }
            // InternalCompleteOCL.g:3271:3: ( ( (lv_ownedParts_2_0= ruleCollectionLiteralPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleCollectionLiteralPartCS ) ) )* )?
            int alt66=2;
            int LA66_0 = input.LA(1);

            if ( ((LA66_0>=RULE_SIMPLE_ID && LA66_0<=RULE_SINGLE_QUOTED_STRING)||LA66_0==21||LA66_0==23||(LA66_0>=30 && LA66_0<=32)||(LA66_0>=42 && LA66_0<=55)||(LA66_0>=73 && LA66_0<=74)||(LA66_0>=76 && LA66_0<=80)||LA66_0==85||(LA66_0>=88 && LA66_0<=91)||LA66_0==98||(LA66_0>=103 && LA66_0<=104)) ) {
                alt66=1;
            }
            switch (alt66) {
                case 1 :
                    // InternalCompleteOCL.g:3272:4: ( (lv_ownedParts_2_0= ruleCollectionLiteralPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleCollectionLiteralPartCS ) ) )*
                    {
                    // InternalCompleteOCL.g:3272:4: ( (lv_ownedParts_2_0= ruleCollectionLiteralPartCS ) )
                    // InternalCompleteOCL.g:3273:5: (lv_ownedParts_2_0= ruleCollectionLiteralPartCS )
                    {
                    // InternalCompleteOCL.g:3273:5: (lv_ownedParts_2_0= ruleCollectionLiteralPartCS )
                    // InternalCompleteOCL.g:3274:6: lv_ownedParts_2_0= ruleCollectionLiteralPartCS
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

                    // InternalCompleteOCL.g:3291:4: (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleCollectionLiteralPartCS ) ) )*
                    loop65:
                    do {
                        int alt65=2;
                        int LA65_0 = input.LA(1);

                        if ( (LA65_0==28) ) {
                            alt65=1;
                        }


                        switch (alt65) {
                    	case 1 :
                    	    // InternalCompleteOCL.g:3292:5: otherlv_3= ',' ( (lv_ownedParts_4_0= ruleCollectionLiteralPartCS ) )
                    	    {
                    	    otherlv_3=(Token)match(input,28,FollowSets000.FOLLOW_38); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      					newLeafNode(otherlv_3, grammarAccess.getCollectionLiteralExpCSAccess().getCommaKeyword_2_1_0());

                    	    }
                    	    // InternalCompleteOCL.g:3296:5: ( (lv_ownedParts_4_0= ruleCollectionLiteralPartCS ) )
                    	    // InternalCompleteOCL.g:3297:6: (lv_ownedParts_4_0= ruleCollectionLiteralPartCS )
                    	    {
                    	    // InternalCompleteOCL.g:3297:6: (lv_ownedParts_4_0= ruleCollectionLiteralPartCS )
                    	    // InternalCompleteOCL.g:3298:7: lv_ownedParts_4_0= ruleCollectionLiteralPartCS
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
                    	    break loop65;
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
    // InternalCompleteOCL.g:3325:1: entryRuleCollectionLiteralPartCS returns [EObject current=null] : iv_ruleCollectionLiteralPartCS= ruleCollectionLiteralPartCS EOF ;
    public final EObject entryRuleCollectionLiteralPartCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCollectionLiteralPartCS = null;


        try {
            // InternalCompleteOCL.g:3325:64: (iv_ruleCollectionLiteralPartCS= ruleCollectionLiteralPartCS EOF )
            // InternalCompleteOCL.g:3326:2: iv_ruleCollectionLiteralPartCS= ruleCollectionLiteralPartCS EOF
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
    // InternalCompleteOCL.g:3332:1: ruleCollectionLiteralPartCS returns [EObject current=null] : ( ( ( (lv_ownedExpression_0_0= ruleExpCS ) ) (otherlv_1= '..' ( (lv_ownedLastExpression_2_0= ruleExpCS ) ) )? ) | ( (lv_ownedExpression_3_0= rulePatternExpCS ) ) ) ;
    public final EObject ruleCollectionLiteralPartCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject lv_ownedExpression_0_0 = null;

        EObject lv_ownedLastExpression_2_0 = null;

        EObject lv_ownedExpression_3_0 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:3338:2: ( ( ( ( (lv_ownedExpression_0_0= ruleExpCS ) ) (otherlv_1= '..' ( (lv_ownedLastExpression_2_0= ruleExpCS ) ) )? ) | ( (lv_ownedExpression_3_0= rulePatternExpCS ) ) ) )
            // InternalCompleteOCL.g:3339:2: ( ( ( (lv_ownedExpression_0_0= ruleExpCS ) ) (otherlv_1= '..' ( (lv_ownedLastExpression_2_0= ruleExpCS ) ) )? ) | ( (lv_ownedExpression_3_0= rulePatternExpCS ) ) )
            {
            // InternalCompleteOCL.g:3339:2: ( ( ( (lv_ownedExpression_0_0= ruleExpCS ) ) (otherlv_1= '..' ( (lv_ownedLastExpression_2_0= ruleExpCS ) ) )? ) | ( (lv_ownedExpression_3_0= rulePatternExpCS ) ) )
            int alt68=2;
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
                alt68=1;
                }
                break;
            case RULE_SIMPLE_ID:
                {
                int LA68_2 = input.LA(2);

                if ( (LA68_2==23) ) {
                    alt68=2;
                }
                else if ( (LA68_2==EOF||(LA68_2>=17 && LA68_2<=18)||LA68_2==21||(LA68_2>=28 && LA68_2<=29)||(LA68_2>=39 && LA68_2<=40)||LA68_2==52||(LA68_2>=55 && LA68_2<=72)||LA68_2==75||(LA68_2>=81 && LA68_2<=83)||(LA68_2>=92 && LA68_2<=93)) ) {
                    alt68=1;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return current;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 68, 2, input);

                    throw nvae;
                }
                }
                break;
            case RULE_ESCAPED_ID:
                {
                int LA68_3 = input.LA(2);

                if ( (LA68_3==EOF||(LA68_3>=17 && LA68_3<=18)||LA68_3==21||(LA68_3>=28 && LA68_3<=29)||(LA68_3>=39 && LA68_3<=40)||LA68_3==52||(LA68_3>=55 && LA68_3<=72)||LA68_3==75||(LA68_3>=81 && LA68_3<=83)||(LA68_3>=92 && LA68_3<=93)) ) {
                    alt68=1;
                }
                else if ( (LA68_3==23) ) {
                    alt68=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return current;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 68, 3, input);

                    throw nvae;
                }
                }
                break;
            case 30:
                {
                int LA68_4 = input.LA(2);

                if ( (LA68_4==EOF||(LA68_4>=17 && LA68_4<=18)||LA68_4==21||(LA68_4>=28 && LA68_4<=29)||(LA68_4>=39 && LA68_4<=40)||LA68_4==52||(LA68_4>=55 && LA68_4<=72)||LA68_4==75||(LA68_4>=81 && LA68_4<=83)||(LA68_4>=92 && LA68_4<=93)) ) {
                    alt68=1;
                }
                else if ( (LA68_4==23) ) {
                    alt68=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return current;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 68, 4, input);

                    throw nvae;
                }
                }
                break;
            case 31:
                {
                int LA68_5 = input.LA(2);

                if ( (LA68_5==23) ) {
                    alt68=2;
                }
                else if ( (LA68_5==EOF||(LA68_5>=17 && LA68_5<=18)||LA68_5==21||(LA68_5>=28 && LA68_5<=29)||(LA68_5>=39 && LA68_5<=40)||LA68_5==52||(LA68_5>=55 && LA68_5<=72)||LA68_5==75||(LA68_5>=81 && LA68_5<=83)||(LA68_5>=92 && LA68_5<=93)) ) {
                    alt68=1;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return current;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 68, 5, input);

                    throw nvae;
                }
                }
                break;
            case 32:
                {
                int LA68_6 = input.LA(2);

                if ( (LA68_6==23) ) {
                    alt68=2;
                }
                else if ( (LA68_6==EOF||(LA68_6>=17 && LA68_6<=18)||LA68_6==21||(LA68_6>=28 && LA68_6<=29)||(LA68_6>=39 && LA68_6<=40)||LA68_6==52||(LA68_6>=55 && LA68_6<=72)||LA68_6==75||(LA68_6>=81 && LA68_6<=83)||(LA68_6>=92 && LA68_6<=93)) ) {
                    alt68=1;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return current;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 68, 6, input);

                    throw nvae;
                }
                }
                break;
            case 23:
                {
                alt68=2;
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
                    // InternalCompleteOCL.g:3340:3: ( ( (lv_ownedExpression_0_0= ruleExpCS ) ) (otherlv_1= '..' ( (lv_ownedLastExpression_2_0= ruleExpCS ) ) )? )
                    {
                    // InternalCompleteOCL.g:3340:3: ( ( (lv_ownedExpression_0_0= ruleExpCS ) ) (otherlv_1= '..' ( (lv_ownedLastExpression_2_0= ruleExpCS ) ) )? )
                    // InternalCompleteOCL.g:3341:4: ( (lv_ownedExpression_0_0= ruleExpCS ) ) (otherlv_1= '..' ( (lv_ownedLastExpression_2_0= ruleExpCS ) ) )?
                    {
                    // InternalCompleteOCL.g:3341:4: ( (lv_ownedExpression_0_0= ruleExpCS ) )
                    // InternalCompleteOCL.g:3342:5: (lv_ownedExpression_0_0= ruleExpCS )
                    {
                    // InternalCompleteOCL.g:3342:5: (lv_ownedExpression_0_0= ruleExpCS )
                    // InternalCompleteOCL.g:3343:6: lv_ownedExpression_0_0= ruleExpCS
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

                    // InternalCompleteOCL.g:3360:4: (otherlv_1= '..' ( (lv_ownedLastExpression_2_0= ruleExpCS ) ) )?
                    int alt67=2;
                    int LA67_0 = input.LA(1);

                    if ( (LA67_0==83) ) {
                        alt67=1;
                    }
                    switch (alt67) {
                        case 1 :
                            // InternalCompleteOCL.g:3361:5: otherlv_1= '..' ( (lv_ownedLastExpression_2_0= ruleExpCS ) )
                            {
                            otherlv_1=(Token)match(input,83,FollowSets000.FOLLOW_40); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              					newLeafNode(otherlv_1, grammarAccess.getCollectionLiteralPartCSAccess().getFullStopFullStopKeyword_0_1_0());

                            }
                            // InternalCompleteOCL.g:3365:5: ( (lv_ownedLastExpression_2_0= ruleExpCS ) )
                            // InternalCompleteOCL.g:3366:6: (lv_ownedLastExpression_2_0= ruleExpCS )
                            {
                            // InternalCompleteOCL.g:3366:6: (lv_ownedLastExpression_2_0= ruleExpCS )
                            // InternalCompleteOCL.g:3367:7: lv_ownedLastExpression_2_0= ruleExpCS
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
                    // InternalCompleteOCL.g:3387:3: ( (lv_ownedExpression_3_0= rulePatternExpCS ) )
                    {
                    // InternalCompleteOCL.g:3387:3: ( (lv_ownedExpression_3_0= rulePatternExpCS ) )
                    // InternalCompleteOCL.g:3388:4: (lv_ownedExpression_3_0= rulePatternExpCS )
                    {
                    // InternalCompleteOCL.g:3388:4: (lv_ownedExpression_3_0= rulePatternExpCS )
                    // InternalCompleteOCL.g:3389:5: lv_ownedExpression_3_0= rulePatternExpCS
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
    // InternalCompleteOCL.g:3410:1: entryRuleCollectionPatternCS returns [EObject current=null] : iv_ruleCollectionPatternCS= ruleCollectionPatternCS EOF ;
    public final EObject entryRuleCollectionPatternCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCollectionPatternCS = null;


        try {
            // InternalCompleteOCL.g:3410:60: (iv_ruleCollectionPatternCS= ruleCollectionPatternCS EOF )
            // InternalCompleteOCL.g:3411:2: iv_ruleCollectionPatternCS= ruleCollectionPatternCS EOF
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
    // InternalCompleteOCL.g:3417:1: ruleCollectionPatternCS returns [EObject current=null] : ( ( (lv_ownedType_0_0= ruleCollectionTypeCS ) ) otherlv_1= '{' ( ( (lv_ownedParts_2_0= rulePatternExpCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= rulePatternExpCS ) ) )* (otherlv_5= '++' ( (lv_restVariableName_6_0= ruleIdentifier ) ) ) )? otherlv_7= '}' ) ;
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
            // InternalCompleteOCL.g:3423:2: ( ( ( (lv_ownedType_0_0= ruleCollectionTypeCS ) ) otherlv_1= '{' ( ( (lv_ownedParts_2_0= rulePatternExpCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= rulePatternExpCS ) ) )* (otherlv_5= '++' ( (lv_restVariableName_6_0= ruleIdentifier ) ) ) )? otherlv_7= '}' ) )
            // InternalCompleteOCL.g:3424:2: ( ( (lv_ownedType_0_0= ruleCollectionTypeCS ) ) otherlv_1= '{' ( ( (lv_ownedParts_2_0= rulePatternExpCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= rulePatternExpCS ) ) )* (otherlv_5= '++' ( (lv_restVariableName_6_0= ruleIdentifier ) ) ) )? otherlv_7= '}' )
            {
            // InternalCompleteOCL.g:3424:2: ( ( (lv_ownedType_0_0= ruleCollectionTypeCS ) ) otherlv_1= '{' ( ( (lv_ownedParts_2_0= rulePatternExpCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= rulePatternExpCS ) ) )* (otherlv_5= '++' ( (lv_restVariableName_6_0= ruleIdentifier ) ) ) )? otherlv_7= '}' )
            // InternalCompleteOCL.g:3425:3: ( (lv_ownedType_0_0= ruleCollectionTypeCS ) ) otherlv_1= '{' ( ( (lv_ownedParts_2_0= rulePatternExpCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= rulePatternExpCS ) ) )* (otherlv_5= '++' ( (lv_restVariableName_6_0= ruleIdentifier ) ) ) )? otherlv_7= '}'
            {
            // InternalCompleteOCL.g:3425:3: ( (lv_ownedType_0_0= ruleCollectionTypeCS ) )
            // InternalCompleteOCL.g:3426:4: (lv_ownedType_0_0= ruleCollectionTypeCS )
            {
            // InternalCompleteOCL.g:3426:4: (lv_ownedType_0_0= ruleCollectionTypeCS )
            // InternalCompleteOCL.g:3427:5: lv_ownedType_0_0= ruleCollectionTypeCS
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

            otherlv_1=(Token)match(input,81,FollowSets000.FOLLOW_36); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getCollectionPatternCSAccess().getLeftCurlyBracketKeyword_1());

            }
            // InternalCompleteOCL.g:3448:3: ( ( (lv_ownedParts_2_0= rulePatternExpCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= rulePatternExpCS ) ) )* (otherlv_5= '++' ( (lv_restVariableName_6_0= ruleIdentifier ) ) ) )?
            int alt70=2;
            int LA70_0 = input.LA(1);

            if ( ((LA70_0>=RULE_SIMPLE_ID && LA70_0<=RULE_ESCAPED_ID)||LA70_0==23||(LA70_0>=30 && LA70_0<=32)) ) {
                alt70=1;
            }
            switch (alt70) {
                case 1 :
                    // InternalCompleteOCL.g:3449:4: ( (lv_ownedParts_2_0= rulePatternExpCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= rulePatternExpCS ) ) )* (otherlv_5= '++' ( (lv_restVariableName_6_0= ruleIdentifier ) ) )
                    {
                    // InternalCompleteOCL.g:3449:4: ( (lv_ownedParts_2_0= rulePatternExpCS ) )
                    // InternalCompleteOCL.g:3450:5: (lv_ownedParts_2_0= rulePatternExpCS )
                    {
                    // InternalCompleteOCL.g:3450:5: (lv_ownedParts_2_0= rulePatternExpCS )
                    // InternalCompleteOCL.g:3451:6: lv_ownedParts_2_0= rulePatternExpCS
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

                    // InternalCompleteOCL.g:3468:4: (otherlv_3= ',' ( (lv_ownedParts_4_0= rulePatternExpCS ) ) )*
                    loop69:
                    do {
                        int alt69=2;
                        int LA69_0 = input.LA(1);

                        if ( (LA69_0==28) ) {
                            alt69=1;
                        }


                        switch (alt69) {
                    	case 1 :
                    	    // InternalCompleteOCL.g:3469:5: otherlv_3= ',' ( (lv_ownedParts_4_0= rulePatternExpCS ) )
                    	    {
                    	    otherlv_3=(Token)match(input,28,FollowSets000.FOLLOW_38); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      					newLeafNode(otherlv_3, grammarAccess.getCollectionPatternCSAccess().getCommaKeyword_2_1_0());

                    	    }
                    	    // InternalCompleteOCL.g:3473:5: ( (lv_ownedParts_4_0= rulePatternExpCS ) )
                    	    // InternalCompleteOCL.g:3474:6: (lv_ownedParts_4_0= rulePatternExpCS )
                    	    {
                    	    // InternalCompleteOCL.g:3474:6: (lv_ownedParts_4_0= rulePatternExpCS )
                    	    // InternalCompleteOCL.g:3475:7: lv_ownedParts_4_0= rulePatternExpCS
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
                    	    break loop69;
                        }
                    } while (true);

                    // InternalCompleteOCL.g:3493:4: (otherlv_5= '++' ( (lv_restVariableName_6_0= ruleIdentifier ) ) )
                    // InternalCompleteOCL.g:3494:5: otherlv_5= '++' ( (lv_restVariableName_6_0= ruleIdentifier ) )
                    {
                    otherlv_5=(Token)match(input,84,FollowSets000.FOLLOW_42); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					newLeafNode(otherlv_5, grammarAccess.getCollectionPatternCSAccess().getPlusSignPlusSignKeyword_2_2_0());

                    }
                    // InternalCompleteOCL.g:3498:5: ( (lv_restVariableName_6_0= ruleIdentifier ) )
                    // InternalCompleteOCL.g:3499:6: (lv_restVariableName_6_0= ruleIdentifier )
                    {
                    // InternalCompleteOCL.g:3499:6: (lv_restVariableName_6_0= ruleIdentifier )
                    // InternalCompleteOCL.g:3500:7: lv_restVariableName_6_0= ruleIdentifier
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
    // InternalCompleteOCL.g:3527:1: entryRuleShadowPartCS returns [EObject current=null] : iv_ruleShadowPartCS= ruleShadowPartCS EOF ;
    public final EObject entryRuleShadowPartCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleShadowPartCS = null;


        try {
            // InternalCompleteOCL.g:3527:53: (iv_ruleShadowPartCS= ruleShadowPartCS EOF )
            // InternalCompleteOCL.g:3528:2: iv_ruleShadowPartCS= ruleShadowPartCS EOF
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
    // InternalCompleteOCL.g:3534:1: ruleShadowPartCS returns [EObject current=null] : ( ( ( ( ruleUnrestrictedName ) ) otherlv_1= '=' ( ( (lv_ownedInitExpression_2_1= ruleExpCS | lv_ownedInitExpression_2_2= rulePatternExpCS ) ) ) ) | ( (lv_ownedInitExpression_3_0= ruleStringLiteralExpCS ) ) ) ;
    public final EObject ruleShadowPartCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject lv_ownedInitExpression_2_1 = null;

        EObject lv_ownedInitExpression_2_2 = null;

        EObject lv_ownedInitExpression_3_0 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:3540:2: ( ( ( ( ( ruleUnrestrictedName ) ) otherlv_1= '=' ( ( (lv_ownedInitExpression_2_1= ruleExpCS | lv_ownedInitExpression_2_2= rulePatternExpCS ) ) ) ) | ( (lv_ownedInitExpression_3_0= ruleStringLiteralExpCS ) ) ) )
            // InternalCompleteOCL.g:3541:2: ( ( ( ( ruleUnrestrictedName ) ) otherlv_1= '=' ( ( (lv_ownedInitExpression_2_1= ruleExpCS | lv_ownedInitExpression_2_2= rulePatternExpCS ) ) ) ) | ( (lv_ownedInitExpression_3_0= ruleStringLiteralExpCS ) ) )
            {
            // InternalCompleteOCL.g:3541:2: ( ( ( ( ruleUnrestrictedName ) ) otherlv_1= '=' ( ( (lv_ownedInitExpression_2_1= ruleExpCS | lv_ownedInitExpression_2_2= rulePatternExpCS ) ) ) ) | ( (lv_ownedInitExpression_3_0= ruleStringLiteralExpCS ) ) )
            int alt72=2;
            int LA72_0 = input.LA(1);

            if ( ((LA72_0>=RULE_SIMPLE_ID && LA72_0<=RULE_ESCAPED_ID)||(LA72_0>=30 && LA72_0<=32)) ) {
                alt72=1;
            }
            else if ( (LA72_0==RULE_SINGLE_QUOTED_STRING) ) {
                alt72=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 72, 0, input);

                throw nvae;
            }
            switch (alt72) {
                case 1 :
                    // InternalCompleteOCL.g:3542:3: ( ( ( ruleUnrestrictedName ) ) otherlv_1= '=' ( ( (lv_ownedInitExpression_2_1= ruleExpCS | lv_ownedInitExpression_2_2= rulePatternExpCS ) ) ) )
                    {
                    // InternalCompleteOCL.g:3542:3: ( ( ( ruleUnrestrictedName ) ) otherlv_1= '=' ( ( (lv_ownedInitExpression_2_1= ruleExpCS | lv_ownedInitExpression_2_2= rulePatternExpCS ) ) ) )
                    // InternalCompleteOCL.g:3543:4: ( ( ruleUnrestrictedName ) ) otherlv_1= '=' ( ( (lv_ownedInitExpression_2_1= ruleExpCS | lv_ownedInitExpression_2_2= rulePatternExpCS ) ) )
                    {
                    // InternalCompleteOCL.g:3543:4: ( ( ruleUnrestrictedName ) )
                    // InternalCompleteOCL.g:3544:5: ( ruleUnrestrictedName )
                    {
                    // InternalCompleteOCL.g:3544:5: ( ruleUnrestrictedName )
                    // InternalCompleteOCL.g:3545:6: ruleUnrestrictedName
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

                    otherlv_1=(Token)match(input,29,FollowSets000.FOLLOW_38); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_1, grammarAccess.getShadowPartCSAccess().getEqualsSignKeyword_0_1());

                    }
                    // InternalCompleteOCL.g:3566:4: ( ( (lv_ownedInitExpression_2_1= ruleExpCS | lv_ownedInitExpression_2_2= rulePatternExpCS ) ) )
                    // InternalCompleteOCL.g:3567:5: ( (lv_ownedInitExpression_2_1= ruleExpCS | lv_ownedInitExpression_2_2= rulePatternExpCS ) )
                    {
                    // InternalCompleteOCL.g:3567:5: ( (lv_ownedInitExpression_2_1= ruleExpCS | lv_ownedInitExpression_2_2= rulePatternExpCS ) )
                    // InternalCompleteOCL.g:3568:6: (lv_ownedInitExpression_2_1= ruleExpCS | lv_ownedInitExpression_2_2= rulePatternExpCS )
                    {
                    // InternalCompleteOCL.g:3568:6: (lv_ownedInitExpression_2_1= ruleExpCS | lv_ownedInitExpression_2_2= rulePatternExpCS )
                    int alt71=2;
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
                        alt71=1;
                        }
                        break;
                    case RULE_SIMPLE_ID:
                        {
                        int LA71_2 = input.LA(2);

                        if ( (LA71_2==23) ) {
                            alt71=2;
                        }
                        else if ( (LA71_2==EOF||(LA71_2>=17 && LA71_2<=18)||LA71_2==21||(LA71_2>=28 && LA71_2<=29)||(LA71_2>=39 && LA71_2<=40)||LA71_2==52||(LA71_2>=55 && LA71_2<=72)||LA71_2==75||(LA71_2>=81 && LA71_2<=82)||(LA71_2>=92 && LA71_2<=93)) ) {
                            alt71=1;
                        }
                        else {
                            if (state.backtracking>0) {state.failed=true; return current;}
                            NoViableAltException nvae =
                                new NoViableAltException("", 71, 2, input);

                            throw nvae;
                        }
                        }
                        break;
                    case RULE_ESCAPED_ID:
                        {
                        int LA71_3 = input.LA(2);

                        if ( (LA71_3==EOF||(LA71_3>=17 && LA71_3<=18)||LA71_3==21||(LA71_3>=28 && LA71_3<=29)||(LA71_3>=39 && LA71_3<=40)||LA71_3==52||(LA71_3>=55 && LA71_3<=72)||LA71_3==75||(LA71_3>=81 && LA71_3<=82)||(LA71_3>=92 && LA71_3<=93)) ) {
                            alt71=1;
                        }
                        else if ( (LA71_3==23) ) {
                            alt71=2;
                        }
                        else {
                            if (state.backtracking>0) {state.failed=true; return current;}
                            NoViableAltException nvae =
                                new NoViableAltException("", 71, 3, input);

                            throw nvae;
                        }
                        }
                        break;
                    case 30:
                        {
                        int LA71_4 = input.LA(2);

                        if ( (LA71_4==EOF||(LA71_4>=17 && LA71_4<=18)||LA71_4==21||(LA71_4>=28 && LA71_4<=29)||(LA71_4>=39 && LA71_4<=40)||LA71_4==52||(LA71_4>=55 && LA71_4<=72)||LA71_4==75||(LA71_4>=81 && LA71_4<=82)||(LA71_4>=92 && LA71_4<=93)) ) {
                            alt71=1;
                        }
                        else if ( (LA71_4==23) ) {
                            alt71=2;
                        }
                        else {
                            if (state.backtracking>0) {state.failed=true; return current;}
                            NoViableAltException nvae =
                                new NoViableAltException("", 71, 4, input);

                            throw nvae;
                        }
                        }
                        break;
                    case 31:
                        {
                        int LA71_5 = input.LA(2);

                        if ( (LA71_5==EOF||(LA71_5>=17 && LA71_5<=18)||LA71_5==21||(LA71_5>=28 && LA71_5<=29)||(LA71_5>=39 && LA71_5<=40)||LA71_5==52||(LA71_5>=55 && LA71_5<=72)||LA71_5==75||(LA71_5>=81 && LA71_5<=82)||(LA71_5>=92 && LA71_5<=93)) ) {
                            alt71=1;
                        }
                        else if ( (LA71_5==23) ) {
                            alt71=2;
                        }
                        else {
                            if (state.backtracking>0) {state.failed=true; return current;}
                            NoViableAltException nvae =
                                new NoViableAltException("", 71, 5, input);

                            throw nvae;
                        }
                        }
                        break;
                    case 32:
                        {
                        int LA71_6 = input.LA(2);

                        if ( (LA71_6==23) ) {
                            alt71=2;
                        }
                        else if ( (LA71_6==EOF||(LA71_6>=17 && LA71_6<=18)||LA71_6==21||(LA71_6>=28 && LA71_6<=29)||(LA71_6>=39 && LA71_6<=40)||LA71_6==52||(LA71_6>=55 && LA71_6<=72)||LA71_6==75||(LA71_6>=81 && LA71_6<=82)||(LA71_6>=92 && LA71_6<=93)) ) {
                            alt71=1;
                        }
                        else {
                            if (state.backtracking>0) {state.failed=true; return current;}
                            NoViableAltException nvae =
                                new NoViableAltException("", 71, 6, input);

                            throw nvae;
                        }
                        }
                        break;
                    case 23:
                        {
                        alt71=2;
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
                            // InternalCompleteOCL.g:3569:7: lv_ownedInitExpression_2_1= ruleExpCS
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
                            // InternalCompleteOCL.g:3585:7: lv_ownedInitExpression_2_2= rulePatternExpCS
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
                    // InternalCompleteOCL.g:3605:3: ( (lv_ownedInitExpression_3_0= ruleStringLiteralExpCS ) )
                    {
                    // InternalCompleteOCL.g:3605:3: ( (lv_ownedInitExpression_3_0= ruleStringLiteralExpCS ) )
                    // InternalCompleteOCL.g:3606:4: (lv_ownedInitExpression_3_0= ruleStringLiteralExpCS )
                    {
                    // InternalCompleteOCL.g:3606:4: (lv_ownedInitExpression_3_0= ruleStringLiteralExpCS )
                    // InternalCompleteOCL.g:3607:5: lv_ownedInitExpression_3_0= ruleStringLiteralExpCS
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
    // InternalCompleteOCL.g:3628:1: entryRulePatternExpCS returns [EObject current=null] : iv_rulePatternExpCS= rulePatternExpCS EOF ;
    public final EObject entryRulePatternExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePatternExpCS = null;


        try {
            // InternalCompleteOCL.g:3628:53: (iv_rulePatternExpCS= rulePatternExpCS EOF )
            // InternalCompleteOCL.g:3629:2: iv_rulePatternExpCS= rulePatternExpCS EOF
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
    // InternalCompleteOCL.g:3635:1: rulePatternExpCS returns [EObject current=null] : ( ( (lv_patternVariableName_0_0= ruleUnrestrictedName ) )? otherlv_1= ':' ( (lv_ownedPatternType_2_0= ruleTypeExpCS ) ) ) ;
    public final EObject rulePatternExpCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_patternVariableName_0_0 = null;

        EObject lv_ownedPatternType_2_0 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:3641:2: ( ( ( (lv_patternVariableName_0_0= ruleUnrestrictedName ) )? otherlv_1= ':' ( (lv_ownedPatternType_2_0= ruleTypeExpCS ) ) ) )
            // InternalCompleteOCL.g:3642:2: ( ( (lv_patternVariableName_0_0= ruleUnrestrictedName ) )? otherlv_1= ':' ( (lv_ownedPatternType_2_0= ruleTypeExpCS ) ) )
            {
            // InternalCompleteOCL.g:3642:2: ( ( (lv_patternVariableName_0_0= ruleUnrestrictedName ) )? otherlv_1= ':' ( (lv_ownedPatternType_2_0= ruleTypeExpCS ) ) )
            // InternalCompleteOCL.g:3643:3: ( (lv_patternVariableName_0_0= ruleUnrestrictedName ) )? otherlv_1= ':' ( (lv_ownedPatternType_2_0= ruleTypeExpCS ) )
            {
            // InternalCompleteOCL.g:3643:3: ( (lv_patternVariableName_0_0= ruleUnrestrictedName ) )?
            int alt73=2;
            int LA73_0 = input.LA(1);

            if ( ((LA73_0>=RULE_SIMPLE_ID && LA73_0<=RULE_ESCAPED_ID)||(LA73_0>=30 && LA73_0<=32)) ) {
                alt73=1;
            }
            switch (alt73) {
                case 1 :
                    // InternalCompleteOCL.g:3644:4: (lv_patternVariableName_0_0= ruleUnrestrictedName )
                    {
                    // InternalCompleteOCL.g:3644:4: (lv_patternVariableName_0_0= ruleUnrestrictedName )
                    // InternalCompleteOCL.g:3645:5: lv_patternVariableName_0_0= ruleUnrestrictedName
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
            // InternalCompleteOCL.g:3666:3: ( (lv_ownedPatternType_2_0= ruleTypeExpCS ) )
            // InternalCompleteOCL.g:3667:4: (lv_ownedPatternType_2_0= ruleTypeExpCS )
            {
            // InternalCompleteOCL.g:3667:4: (lv_ownedPatternType_2_0= ruleTypeExpCS )
            // InternalCompleteOCL.g:3668:5: lv_ownedPatternType_2_0= ruleTypeExpCS
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
    // InternalCompleteOCL.g:3689:1: entryRuleLambdaLiteralExpCS returns [EObject current=null] : iv_ruleLambdaLiteralExpCS= ruleLambdaLiteralExpCS EOF ;
    public final EObject entryRuleLambdaLiteralExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLambdaLiteralExpCS = null;


        try {
            // InternalCompleteOCL.g:3689:59: (iv_ruleLambdaLiteralExpCS= ruleLambdaLiteralExpCS EOF )
            // InternalCompleteOCL.g:3690:2: iv_ruleLambdaLiteralExpCS= ruleLambdaLiteralExpCS EOF
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
    // InternalCompleteOCL.g:3696:1: ruleLambdaLiteralExpCS returns [EObject current=null] : (otherlv_0= 'Lambda' otherlv_1= '{' ( (lv_ownedExpressionCS_2_0= ruleExpCS ) ) otherlv_3= '}' ) ;
    public final EObject ruleLambdaLiteralExpCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_ownedExpressionCS_2_0 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:3702:2: ( (otherlv_0= 'Lambda' otherlv_1= '{' ( (lv_ownedExpressionCS_2_0= ruleExpCS ) ) otherlv_3= '}' ) )
            // InternalCompleteOCL.g:3703:2: (otherlv_0= 'Lambda' otherlv_1= '{' ( (lv_ownedExpressionCS_2_0= ruleExpCS ) ) otherlv_3= '}' )
            {
            // InternalCompleteOCL.g:3703:2: (otherlv_0= 'Lambda' otherlv_1= '{' ( (lv_ownedExpressionCS_2_0= ruleExpCS ) ) otherlv_3= '}' )
            // InternalCompleteOCL.g:3704:3: otherlv_0= 'Lambda' otherlv_1= '{' ( (lv_ownedExpressionCS_2_0= ruleExpCS ) ) otherlv_3= '}'
            {
            otherlv_0=(Token)match(input,85,FollowSets000.FOLLOW_35); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getLambdaLiteralExpCSAccess().getLambdaKeyword_0());

            }
            otherlv_1=(Token)match(input,81,FollowSets000.FOLLOW_40); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getLambdaLiteralExpCSAccess().getLeftCurlyBracketKeyword_1());

            }
            // InternalCompleteOCL.g:3712:3: ( (lv_ownedExpressionCS_2_0= ruleExpCS ) )
            // InternalCompleteOCL.g:3713:4: (lv_ownedExpressionCS_2_0= ruleExpCS )
            {
            // InternalCompleteOCL.g:3713:4: (lv_ownedExpressionCS_2_0= ruleExpCS )
            // InternalCompleteOCL.g:3714:5: lv_ownedExpressionCS_2_0= ruleExpCS
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
    // InternalCompleteOCL.g:3739:1: entryRuleMapLiteralExpCS returns [EObject current=null] : iv_ruleMapLiteralExpCS= ruleMapLiteralExpCS EOF ;
    public final EObject entryRuleMapLiteralExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMapLiteralExpCS = null;


        try {
            // InternalCompleteOCL.g:3739:56: (iv_ruleMapLiteralExpCS= ruleMapLiteralExpCS EOF )
            // InternalCompleteOCL.g:3740:2: iv_ruleMapLiteralExpCS= ruleMapLiteralExpCS EOF
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
    // InternalCompleteOCL.g:3746:1: ruleMapLiteralExpCS returns [EObject current=null] : ( ( (lv_ownedType_0_0= ruleMapTypeCS ) ) otherlv_1= '{' ( ( (lv_ownedParts_2_0= ruleMapLiteralPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleMapLiteralPartCS ) ) )* )? otherlv_5= '}' ) ;
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
            // InternalCompleteOCL.g:3752:2: ( ( ( (lv_ownedType_0_0= ruleMapTypeCS ) ) otherlv_1= '{' ( ( (lv_ownedParts_2_0= ruleMapLiteralPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleMapLiteralPartCS ) ) )* )? otherlv_5= '}' ) )
            // InternalCompleteOCL.g:3753:2: ( ( (lv_ownedType_0_0= ruleMapTypeCS ) ) otherlv_1= '{' ( ( (lv_ownedParts_2_0= ruleMapLiteralPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleMapLiteralPartCS ) ) )* )? otherlv_5= '}' )
            {
            // InternalCompleteOCL.g:3753:2: ( ( (lv_ownedType_0_0= ruleMapTypeCS ) ) otherlv_1= '{' ( ( (lv_ownedParts_2_0= ruleMapLiteralPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleMapLiteralPartCS ) ) )* )? otherlv_5= '}' )
            // InternalCompleteOCL.g:3754:3: ( (lv_ownedType_0_0= ruleMapTypeCS ) ) otherlv_1= '{' ( ( (lv_ownedParts_2_0= ruleMapLiteralPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleMapLiteralPartCS ) ) )* )? otherlv_5= '}'
            {
            // InternalCompleteOCL.g:3754:3: ( (lv_ownedType_0_0= ruleMapTypeCS ) )
            // InternalCompleteOCL.g:3755:4: (lv_ownedType_0_0= ruleMapTypeCS )
            {
            // InternalCompleteOCL.g:3755:4: (lv_ownedType_0_0= ruleMapTypeCS )
            // InternalCompleteOCL.g:3756:5: lv_ownedType_0_0= ruleMapTypeCS
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

            otherlv_1=(Token)match(input,81,FollowSets000.FOLLOW_44); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getMapLiteralExpCSAccess().getLeftCurlyBracketKeyword_1());

            }
            // InternalCompleteOCL.g:3777:3: ( ( (lv_ownedParts_2_0= ruleMapLiteralPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleMapLiteralPartCS ) ) )* )?
            int alt75=2;
            int LA75_0 = input.LA(1);

            if ( ((LA75_0>=RULE_SIMPLE_ID && LA75_0<=RULE_SINGLE_QUOTED_STRING)||LA75_0==21||(LA75_0>=30 && LA75_0<=32)||(LA75_0>=42 && LA75_0<=55)||(LA75_0>=73 && LA75_0<=74)||(LA75_0>=76 && LA75_0<=80)||LA75_0==85||(LA75_0>=88 && LA75_0<=91)||LA75_0==98||(LA75_0>=103 && LA75_0<=104)) ) {
                alt75=1;
            }
            switch (alt75) {
                case 1 :
                    // InternalCompleteOCL.g:3778:4: ( (lv_ownedParts_2_0= ruleMapLiteralPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleMapLiteralPartCS ) ) )*
                    {
                    // InternalCompleteOCL.g:3778:4: ( (lv_ownedParts_2_0= ruleMapLiteralPartCS ) )
                    // InternalCompleteOCL.g:3779:5: (lv_ownedParts_2_0= ruleMapLiteralPartCS )
                    {
                    // InternalCompleteOCL.g:3779:5: (lv_ownedParts_2_0= ruleMapLiteralPartCS )
                    // InternalCompleteOCL.g:3780:6: lv_ownedParts_2_0= ruleMapLiteralPartCS
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

                    // InternalCompleteOCL.g:3797:4: (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleMapLiteralPartCS ) ) )*
                    loop74:
                    do {
                        int alt74=2;
                        int LA74_0 = input.LA(1);

                        if ( (LA74_0==28) ) {
                            alt74=1;
                        }


                        switch (alt74) {
                    	case 1 :
                    	    // InternalCompleteOCL.g:3798:5: otherlv_3= ',' ( (lv_ownedParts_4_0= ruleMapLiteralPartCS ) )
                    	    {
                    	    otherlv_3=(Token)match(input,28,FollowSets000.FOLLOW_40); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      					newLeafNode(otherlv_3, grammarAccess.getMapLiteralExpCSAccess().getCommaKeyword_2_1_0());

                    	    }
                    	    // InternalCompleteOCL.g:3802:5: ( (lv_ownedParts_4_0= ruleMapLiteralPartCS ) )
                    	    // InternalCompleteOCL.g:3803:6: (lv_ownedParts_4_0= ruleMapLiteralPartCS )
                    	    {
                    	    // InternalCompleteOCL.g:3803:6: (lv_ownedParts_4_0= ruleMapLiteralPartCS )
                    	    // InternalCompleteOCL.g:3804:7: lv_ownedParts_4_0= ruleMapLiteralPartCS
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
                    	    break loop74;
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
    // InternalCompleteOCL.g:3831:1: entryRuleMapLiteralPartCS returns [EObject current=null] : iv_ruleMapLiteralPartCS= ruleMapLiteralPartCS EOF ;
    public final EObject entryRuleMapLiteralPartCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMapLiteralPartCS = null;


        try {
            // InternalCompleteOCL.g:3831:57: (iv_ruleMapLiteralPartCS= ruleMapLiteralPartCS EOF )
            // InternalCompleteOCL.g:3832:2: iv_ruleMapLiteralPartCS= ruleMapLiteralPartCS EOF
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
    // InternalCompleteOCL.g:3838:1: ruleMapLiteralPartCS returns [EObject current=null] : ( ( (lv_ownedKey_0_0= ruleExpCS ) ) (otherlv_1= 'with' | otherlv_2= '<-' ) ( (lv_ownedValue_3_0= ruleExpCS ) ) ) ;
    public final EObject ruleMapLiteralPartCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        EObject lv_ownedKey_0_0 = null;

        EObject lv_ownedValue_3_0 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:3844:2: ( ( ( (lv_ownedKey_0_0= ruleExpCS ) ) (otherlv_1= 'with' | otherlv_2= '<-' ) ( (lv_ownedValue_3_0= ruleExpCS ) ) ) )
            // InternalCompleteOCL.g:3845:2: ( ( (lv_ownedKey_0_0= ruleExpCS ) ) (otherlv_1= 'with' | otherlv_2= '<-' ) ( (lv_ownedValue_3_0= ruleExpCS ) ) )
            {
            // InternalCompleteOCL.g:3845:2: ( ( (lv_ownedKey_0_0= ruleExpCS ) ) (otherlv_1= 'with' | otherlv_2= '<-' ) ( (lv_ownedValue_3_0= ruleExpCS ) ) )
            // InternalCompleteOCL.g:3846:3: ( (lv_ownedKey_0_0= ruleExpCS ) ) (otherlv_1= 'with' | otherlv_2= '<-' ) ( (lv_ownedValue_3_0= ruleExpCS ) )
            {
            // InternalCompleteOCL.g:3846:3: ( (lv_ownedKey_0_0= ruleExpCS ) )
            // InternalCompleteOCL.g:3847:4: (lv_ownedKey_0_0= ruleExpCS )
            {
            // InternalCompleteOCL.g:3847:4: (lv_ownedKey_0_0= ruleExpCS )
            // InternalCompleteOCL.g:3848:5: lv_ownedKey_0_0= ruleExpCS
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

            // InternalCompleteOCL.g:3865:3: (otherlv_1= 'with' | otherlv_2= '<-' )
            int alt76=2;
            int LA76_0 = input.LA(1);

            if ( (LA76_0==86) ) {
                alt76=1;
            }
            else if ( (LA76_0==87) ) {
                alt76=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 76, 0, input);

                throw nvae;
            }
            switch (alt76) {
                case 1 :
                    // InternalCompleteOCL.g:3866:4: otherlv_1= 'with'
                    {
                    otherlv_1=(Token)match(input,86,FollowSets000.FOLLOW_40); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_1, grammarAccess.getMapLiteralPartCSAccess().getWithKeyword_1_0());

                    }

                    }
                    break;
                case 2 :
                    // InternalCompleteOCL.g:3871:4: otherlv_2= '<-'
                    {
                    otherlv_2=(Token)match(input,87,FollowSets000.FOLLOW_40); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_2, grammarAccess.getMapLiteralPartCSAccess().getLessThanSignHyphenMinusKeyword_1_1());

                    }

                    }
                    break;

            }

            // InternalCompleteOCL.g:3876:3: ( (lv_ownedValue_3_0= ruleExpCS ) )
            // InternalCompleteOCL.g:3877:4: (lv_ownedValue_3_0= ruleExpCS )
            {
            // InternalCompleteOCL.g:3877:4: (lv_ownedValue_3_0= ruleExpCS )
            // InternalCompleteOCL.g:3878:5: lv_ownedValue_3_0= ruleExpCS
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
    // InternalCompleteOCL.g:3899:1: entryRulePrimitiveLiteralExpCS returns [EObject current=null] : iv_rulePrimitiveLiteralExpCS= rulePrimitiveLiteralExpCS EOF ;
    public final EObject entryRulePrimitiveLiteralExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePrimitiveLiteralExpCS = null;


        try {
            // InternalCompleteOCL.g:3899:62: (iv_rulePrimitiveLiteralExpCS= rulePrimitiveLiteralExpCS EOF )
            // InternalCompleteOCL.g:3900:2: iv_rulePrimitiveLiteralExpCS= rulePrimitiveLiteralExpCS EOF
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
    // InternalCompleteOCL.g:3906:1: rulePrimitiveLiteralExpCS returns [EObject current=null] : (this_NumberLiteralExpCS_0= ruleNumberLiteralExpCS | this_StringLiteralExpCS_1= ruleStringLiteralExpCS | this_BooleanLiteralExpCS_2= ruleBooleanLiteralExpCS | this_UnlimitedNaturalLiteralExpCS_3= ruleUnlimitedNaturalLiteralExpCS | this_InvalidLiteralExpCS_4= ruleInvalidLiteralExpCS | this_NullLiteralExpCS_5= ruleNullLiteralExpCS ) ;
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
            // InternalCompleteOCL.g:3912:2: ( (this_NumberLiteralExpCS_0= ruleNumberLiteralExpCS | this_StringLiteralExpCS_1= ruleStringLiteralExpCS | this_BooleanLiteralExpCS_2= ruleBooleanLiteralExpCS | this_UnlimitedNaturalLiteralExpCS_3= ruleUnlimitedNaturalLiteralExpCS | this_InvalidLiteralExpCS_4= ruleInvalidLiteralExpCS | this_NullLiteralExpCS_5= ruleNullLiteralExpCS ) )
            // InternalCompleteOCL.g:3913:2: (this_NumberLiteralExpCS_0= ruleNumberLiteralExpCS | this_StringLiteralExpCS_1= ruleStringLiteralExpCS | this_BooleanLiteralExpCS_2= ruleBooleanLiteralExpCS | this_UnlimitedNaturalLiteralExpCS_3= ruleUnlimitedNaturalLiteralExpCS | this_InvalidLiteralExpCS_4= ruleInvalidLiteralExpCS | this_NullLiteralExpCS_5= ruleNullLiteralExpCS )
            {
            // InternalCompleteOCL.g:3913:2: (this_NumberLiteralExpCS_0= ruleNumberLiteralExpCS | this_StringLiteralExpCS_1= ruleStringLiteralExpCS | this_BooleanLiteralExpCS_2= ruleBooleanLiteralExpCS | this_UnlimitedNaturalLiteralExpCS_3= ruleUnlimitedNaturalLiteralExpCS | this_InvalidLiteralExpCS_4= ruleInvalidLiteralExpCS | this_NullLiteralExpCS_5= ruleNullLiteralExpCS )
            int alt77=6;
            switch ( input.LA(1) ) {
            case RULE_INT:
                {
                alt77=1;
                }
                break;
            case RULE_SINGLE_QUOTED_STRING:
                {
                alt77=2;
                }
                break;
            case 88:
            case 89:
                {
                alt77=3;
                }
                break;
            case 55:
                {
                alt77=4;
                }
                break;
            case 90:
                {
                alt77=5;
                }
                break;
            case 91:
                {
                alt77=6;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 77, 0, input);

                throw nvae;
            }

            switch (alt77) {
                case 1 :
                    // InternalCompleteOCL.g:3914:3: this_NumberLiteralExpCS_0= ruleNumberLiteralExpCS
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
                    // InternalCompleteOCL.g:3926:3: this_StringLiteralExpCS_1= ruleStringLiteralExpCS
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
                    // InternalCompleteOCL.g:3938:3: this_BooleanLiteralExpCS_2= ruleBooleanLiteralExpCS
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
                    // InternalCompleteOCL.g:3950:3: this_UnlimitedNaturalLiteralExpCS_3= ruleUnlimitedNaturalLiteralExpCS
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
                    // InternalCompleteOCL.g:3962:3: this_InvalidLiteralExpCS_4= ruleInvalidLiteralExpCS
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
                    // InternalCompleteOCL.g:3974:3: this_NullLiteralExpCS_5= ruleNullLiteralExpCS
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
    // InternalCompleteOCL.g:3989:1: entryRuleTupleLiteralExpCS returns [EObject current=null] : iv_ruleTupleLiteralExpCS= ruleTupleLiteralExpCS EOF ;
    public final EObject entryRuleTupleLiteralExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTupleLiteralExpCS = null;


        try {
            // InternalCompleteOCL.g:3989:58: (iv_ruleTupleLiteralExpCS= ruleTupleLiteralExpCS EOF )
            // InternalCompleteOCL.g:3990:2: iv_ruleTupleLiteralExpCS= ruleTupleLiteralExpCS EOF
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
    // InternalCompleteOCL.g:3996:1: ruleTupleLiteralExpCS returns [EObject current=null] : (otherlv_0= 'Tuple' otherlv_1= '{' ( (lv_ownedParts_2_0= ruleTupleLiteralPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleTupleLiteralPartCS ) ) )* otherlv_5= '}' ) ;
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
            // InternalCompleteOCL.g:4002:2: ( (otherlv_0= 'Tuple' otherlv_1= '{' ( (lv_ownedParts_2_0= ruleTupleLiteralPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleTupleLiteralPartCS ) ) )* otherlv_5= '}' ) )
            // InternalCompleteOCL.g:4003:2: (otherlv_0= 'Tuple' otherlv_1= '{' ( (lv_ownedParts_2_0= ruleTupleLiteralPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleTupleLiteralPartCS ) ) )* otherlv_5= '}' )
            {
            // InternalCompleteOCL.g:4003:2: (otherlv_0= 'Tuple' otherlv_1= '{' ( (lv_ownedParts_2_0= ruleTupleLiteralPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleTupleLiteralPartCS ) ) )* otherlv_5= '}' )
            // InternalCompleteOCL.g:4004:3: otherlv_0= 'Tuple' otherlv_1= '{' ( (lv_ownedParts_2_0= ruleTupleLiteralPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleTupleLiteralPartCS ) ) )* otherlv_5= '}'
            {
            otherlv_0=(Token)match(input,74,FollowSets000.FOLLOW_35); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getTupleLiteralExpCSAccess().getTupleKeyword_0());

            }
            otherlv_1=(Token)match(input,81,FollowSets000.FOLLOW_15); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getTupleLiteralExpCSAccess().getLeftCurlyBracketKeyword_1());

            }
            // InternalCompleteOCL.g:4012:3: ( (lv_ownedParts_2_0= ruleTupleLiteralPartCS ) )
            // InternalCompleteOCL.g:4013:4: (lv_ownedParts_2_0= ruleTupleLiteralPartCS )
            {
            // InternalCompleteOCL.g:4013:4: (lv_ownedParts_2_0= ruleTupleLiteralPartCS )
            // InternalCompleteOCL.g:4014:5: lv_ownedParts_2_0= ruleTupleLiteralPartCS
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

            // InternalCompleteOCL.g:4031:3: (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleTupleLiteralPartCS ) ) )*
            loop78:
            do {
                int alt78=2;
                int LA78_0 = input.LA(1);

                if ( (LA78_0==28) ) {
                    alt78=1;
                }


                switch (alt78) {
            	case 1 :
            	    // InternalCompleteOCL.g:4032:4: otherlv_3= ',' ( (lv_ownedParts_4_0= ruleTupleLiteralPartCS ) )
            	    {
            	    otherlv_3=(Token)match(input,28,FollowSets000.FOLLOW_15); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      				newLeafNode(otherlv_3, grammarAccess.getTupleLiteralExpCSAccess().getCommaKeyword_3_0());

            	    }
            	    // InternalCompleteOCL.g:4036:4: ( (lv_ownedParts_4_0= ruleTupleLiteralPartCS ) )
            	    // InternalCompleteOCL.g:4037:5: (lv_ownedParts_4_0= ruleTupleLiteralPartCS )
            	    {
            	    // InternalCompleteOCL.g:4037:5: (lv_ownedParts_4_0= ruleTupleLiteralPartCS )
            	    // InternalCompleteOCL.g:4038:6: lv_ownedParts_4_0= ruleTupleLiteralPartCS
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
            	    break loop78;
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
    // InternalCompleteOCL.g:4064:1: entryRuleTupleLiteralPartCS returns [EObject current=null] : iv_ruleTupleLiteralPartCS= ruleTupleLiteralPartCS EOF ;
    public final EObject entryRuleTupleLiteralPartCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTupleLiteralPartCS = null;


        try {
            // InternalCompleteOCL.g:4064:59: (iv_ruleTupleLiteralPartCS= ruleTupleLiteralPartCS EOF )
            // InternalCompleteOCL.g:4065:2: iv_ruleTupleLiteralPartCS= ruleTupleLiteralPartCS EOF
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
    // InternalCompleteOCL.g:4071:1: ruleTupleLiteralPartCS returns [EObject current=null] : ( ( (lv_name_0_0= ruleUnrestrictedName ) ) (otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) ) )? otherlv_3= '=' ( (lv_ownedInitExpression_4_0= ruleExpCS ) ) ) ;
    public final EObject ruleTupleLiteralPartCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        AntlrDatatypeRuleToken lv_name_0_0 = null;

        EObject lv_ownedType_2_0 = null;

        EObject lv_ownedInitExpression_4_0 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:4077:2: ( ( ( (lv_name_0_0= ruleUnrestrictedName ) ) (otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) ) )? otherlv_3= '=' ( (lv_ownedInitExpression_4_0= ruleExpCS ) ) ) )
            // InternalCompleteOCL.g:4078:2: ( ( (lv_name_0_0= ruleUnrestrictedName ) ) (otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) ) )? otherlv_3= '=' ( (lv_ownedInitExpression_4_0= ruleExpCS ) ) )
            {
            // InternalCompleteOCL.g:4078:2: ( ( (lv_name_0_0= ruleUnrestrictedName ) ) (otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) ) )? otherlv_3= '=' ( (lv_ownedInitExpression_4_0= ruleExpCS ) ) )
            // InternalCompleteOCL.g:4079:3: ( (lv_name_0_0= ruleUnrestrictedName ) ) (otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) ) )? otherlv_3= '=' ( (lv_ownedInitExpression_4_0= ruleExpCS ) )
            {
            // InternalCompleteOCL.g:4079:3: ( (lv_name_0_0= ruleUnrestrictedName ) )
            // InternalCompleteOCL.g:4080:4: (lv_name_0_0= ruleUnrestrictedName )
            {
            // InternalCompleteOCL.g:4080:4: (lv_name_0_0= ruleUnrestrictedName )
            // InternalCompleteOCL.g:4081:5: lv_name_0_0= ruleUnrestrictedName
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

            // InternalCompleteOCL.g:4098:3: (otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) ) )?
            int alt79=2;
            int LA79_0 = input.LA(1);

            if ( (LA79_0==23) ) {
                alt79=1;
            }
            switch (alt79) {
                case 1 :
                    // InternalCompleteOCL.g:4099:4: otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) )
                    {
                    otherlv_1=(Token)match(input,23,FollowSets000.FOLLOW_21); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_1, grammarAccess.getTupleLiteralPartCSAccess().getColonKeyword_1_0());

                    }
                    // InternalCompleteOCL.g:4103:4: ( (lv_ownedType_2_0= ruleTypeExpCS ) )
                    // InternalCompleteOCL.g:4104:5: (lv_ownedType_2_0= ruleTypeExpCS )
                    {
                    // InternalCompleteOCL.g:4104:5: (lv_ownedType_2_0= ruleTypeExpCS )
                    // InternalCompleteOCL.g:4105:6: lv_ownedType_2_0= ruleTypeExpCS
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

            otherlv_3=(Token)match(input,29,FollowSets000.FOLLOW_40); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_3, grammarAccess.getTupleLiteralPartCSAccess().getEqualsSignKeyword_2());

            }
            // InternalCompleteOCL.g:4127:3: ( (lv_ownedInitExpression_4_0= ruleExpCS ) )
            // InternalCompleteOCL.g:4128:4: (lv_ownedInitExpression_4_0= ruleExpCS )
            {
            // InternalCompleteOCL.g:4128:4: (lv_ownedInitExpression_4_0= ruleExpCS )
            // InternalCompleteOCL.g:4129:5: lv_ownedInitExpression_4_0= ruleExpCS
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
    // InternalCompleteOCL.g:4150:1: entryRuleNumberLiteralExpCS returns [EObject current=null] : iv_ruleNumberLiteralExpCS= ruleNumberLiteralExpCS EOF ;
    public final EObject entryRuleNumberLiteralExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNumberLiteralExpCS = null;


        try {
            // InternalCompleteOCL.g:4150:59: (iv_ruleNumberLiteralExpCS= ruleNumberLiteralExpCS EOF )
            // InternalCompleteOCL.g:4151:2: iv_ruleNumberLiteralExpCS= ruleNumberLiteralExpCS EOF
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
    // InternalCompleteOCL.g:4157:1: ruleNumberLiteralExpCS returns [EObject current=null] : ( (lv_symbol_0_0= ruleNUMBER_LITERAL ) ) ;
    public final EObject ruleNumberLiteralExpCS() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_symbol_0_0 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:4163:2: ( ( (lv_symbol_0_0= ruleNUMBER_LITERAL ) ) )
            // InternalCompleteOCL.g:4164:2: ( (lv_symbol_0_0= ruleNUMBER_LITERAL ) )
            {
            // InternalCompleteOCL.g:4164:2: ( (lv_symbol_0_0= ruleNUMBER_LITERAL ) )
            // InternalCompleteOCL.g:4165:3: (lv_symbol_0_0= ruleNUMBER_LITERAL )
            {
            // InternalCompleteOCL.g:4165:3: (lv_symbol_0_0= ruleNUMBER_LITERAL )
            // InternalCompleteOCL.g:4166:4: lv_symbol_0_0= ruleNUMBER_LITERAL
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
    // InternalCompleteOCL.g:4186:1: entryRuleStringLiteralExpCS returns [EObject current=null] : iv_ruleStringLiteralExpCS= ruleStringLiteralExpCS EOF ;
    public final EObject entryRuleStringLiteralExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleStringLiteralExpCS = null;


        try {
            // InternalCompleteOCL.g:4186:59: (iv_ruleStringLiteralExpCS= ruleStringLiteralExpCS EOF )
            // InternalCompleteOCL.g:4187:2: iv_ruleStringLiteralExpCS= ruleStringLiteralExpCS EOF
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
    // InternalCompleteOCL.g:4193:1: ruleStringLiteralExpCS returns [EObject current=null] : ( (lv_segments_0_0= ruleStringLiteral ) )+ ;
    public final EObject ruleStringLiteralExpCS() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_segments_0_0 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:4199:2: ( ( (lv_segments_0_0= ruleStringLiteral ) )+ )
            // InternalCompleteOCL.g:4200:2: ( (lv_segments_0_0= ruleStringLiteral ) )+
            {
            // InternalCompleteOCL.g:4200:2: ( (lv_segments_0_0= ruleStringLiteral ) )+
            int cnt80=0;
            loop80:
            do {
                int alt80=2;
                int LA80_0 = input.LA(1);

                if ( (LA80_0==RULE_SINGLE_QUOTED_STRING) ) {
                    alt80=1;
                }


                switch (alt80) {
            	case 1 :
            	    // InternalCompleteOCL.g:4201:3: (lv_segments_0_0= ruleStringLiteral )
            	    {
            	    // InternalCompleteOCL.g:4201:3: (lv_segments_0_0= ruleStringLiteral )
            	    // InternalCompleteOCL.g:4202:4: lv_segments_0_0= ruleStringLiteral
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
            	    if ( cnt80 >= 1 ) break loop80;
            	    if (state.backtracking>0) {state.failed=true; return current;}
                        EarlyExitException eee =
                            new EarlyExitException(80, input);
                        throw eee;
                }
                cnt80++;
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
    // InternalCompleteOCL.g:4222:1: entryRuleBooleanLiteralExpCS returns [EObject current=null] : iv_ruleBooleanLiteralExpCS= ruleBooleanLiteralExpCS EOF ;
    public final EObject entryRuleBooleanLiteralExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleBooleanLiteralExpCS = null;


        try {
            // InternalCompleteOCL.g:4222:60: (iv_ruleBooleanLiteralExpCS= ruleBooleanLiteralExpCS EOF )
            // InternalCompleteOCL.g:4223:2: iv_ruleBooleanLiteralExpCS= ruleBooleanLiteralExpCS EOF
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
    // InternalCompleteOCL.g:4229:1: ruleBooleanLiteralExpCS returns [EObject current=null] : ( ( (lv_symbol_0_0= 'true' ) ) | ( (lv_symbol_1_0= 'false' ) ) ) ;
    public final EObject ruleBooleanLiteralExpCS() throws RecognitionException {
        EObject current = null;

        Token lv_symbol_0_0=null;
        Token lv_symbol_1_0=null;


        	enterRule();

        try {
            // InternalCompleteOCL.g:4235:2: ( ( ( (lv_symbol_0_0= 'true' ) ) | ( (lv_symbol_1_0= 'false' ) ) ) )
            // InternalCompleteOCL.g:4236:2: ( ( (lv_symbol_0_0= 'true' ) ) | ( (lv_symbol_1_0= 'false' ) ) )
            {
            // InternalCompleteOCL.g:4236:2: ( ( (lv_symbol_0_0= 'true' ) ) | ( (lv_symbol_1_0= 'false' ) ) )
            int alt81=2;
            int LA81_0 = input.LA(1);

            if ( (LA81_0==88) ) {
                alt81=1;
            }
            else if ( (LA81_0==89) ) {
                alt81=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 81, 0, input);

                throw nvae;
            }
            switch (alt81) {
                case 1 :
                    // InternalCompleteOCL.g:4237:3: ( (lv_symbol_0_0= 'true' ) )
                    {
                    // InternalCompleteOCL.g:4237:3: ( (lv_symbol_0_0= 'true' ) )
                    // InternalCompleteOCL.g:4238:4: (lv_symbol_0_0= 'true' )
                    {
                    // InternalCompleteOCL.g:4238:4: (lv_symbol_0_0= 'true' )
                    // InternalCompleteOCL.g:4239:5: lv_symbol_0_0= 'true'
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
                    // InternalCompleteOCL.g:4252:3: ( (lv_symbol_1_0= 'false' ) )
                    {
                    // InternalCompleteOCL.g:4252:3: ( (lv_symbol_1_0= 'false' ) )
                    // InternalCompleteOCL.g:4253:4: (lv_symbol_1_0= 'false' )
                    {
                    // InternalCompleteOCL.g:4253:4: (lv_symbol_1_0= 'false' )
                    // InternalCompleteOCL.g:4254:5: lv_symbol_1_0= 'false'
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
    // InternalCompleteOCL.g:4270:1: entryRuleUnlimitedNaturalLiteralExpCS returns [EObject current=null] : iv_ruleUnlimitedNaturalLiteralExpCS= ruleUnlimitedNaturalLiteralExpCS EOF ;
    public final EObject entryRuleUnlimitedNaturalLiteralExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleUnlimitedNaturalLiteralExpCS = null;


        try {
            // InternalCompleteOCL.g:4270:69: (iv_ruleUnlimitedNaturalLiteralExpCS= ruleUnlimitedNaturalLiteralExpCS EOF )
            // InternalCompleteOCL.g:4271:2: iv_ruleUnlimitedNaturalLiteralExpCS= ruleUnlimitedNaturalLiteralExpCS EOF
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
    // InternalCompleteOCL.g:4277:1: ruleUnlimitedNaturalLiteralExpCS returns [EObject current=null] : ( () otherlv_1= '*' ) ;
    public final EObject ruleUnlimitedNaturalLiteralExpCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;


        	enterRule();

        try {
            // InternalCompleteOCL.g:4283:2: ( ( () otherlv_1= '*' ) )
            // InternalCompleteOCL.g:4284:2: ( () otherlv_1= '*' )
            {
            // InternalCompleteOCL.g:4284:2: ( () otherlv_1= '*' )
            // InternalCompleteOCL.g:4285:3: () otherlv_1= '*'
            {
            // InternalCompleteOCL.g:4285:3: ()
            // InternalCompleteOCL.g:4286:4:
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
    // InternalCompleteOCL.g:4303:1: entryRuleInvalidLiteralExpCS returns [EObject current=null] : iv_ruleInvalidLiteralExpCS= ruleInvalidLiteralExpCS EOF ;
    public final EObject entryRuleInvalidLiteralExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleInvalidLiteralExpCS = null;


        try {
            // InternalCompleteOCL.g:4303:60: (iv_ruleInvalidLiteralExpCS= ruleInvalidLiteralExpCS EOF )
            // InternalCompleteOCL.g:4304:2: iv_ruleInvalidLiteralExpCS= ruleInvalidLiteralExpCS EOF
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
    // InternalCompleteOCL.g:4310:1: ruleInvalidLiteralExpCS returns [EObject current=null] : ( () otherlv_1= 'invalid' ) ;
    public final EObject ruleInvalidLiteralExpCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;


        	enterRule();

        try {
            // InternalCompleteOCL.g:4316:2: ( ( () otherlv_1= 'invalid' ) )
            // InternalCompleteOCL.g:4317:2: ( () otherlv_1= 'invalid' )
            {
            // InternalCompleteOCL.g:4317:2: ( () otherlv_1= 'invalid' )
            // InternalCompleteOCL.g:4318:3: () otherlv_1= 'invalid'
            {
            // InternalCompleteOCL.g:4318:3: ()
            // InternalCompleteOCL.g:4319:4:
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
    // InternalCompleteOCL.g:4336:1: entryRuleNullLiteralExpCS returns [EObject current=null] : iv_ruleNullLiteralExpCS= ruleNullLiteralExpCS EOF ;
    public final EObject entryRuleNullLiteralExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNullLiteralExpCS = null;


        try {
            // InternalCompleteOCL.g:4336:57: (iv_ruleNullLiteralExpCS= ruleNullLiteralExpCS EOF )
            // InternalCompleteOCL.g:4337:2: iv_ruleNullLiteralExpCS= ruleNullLiteralExpCS EOF
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
    // InternalCompleteOCL.g:4343:1: ruleNullLiteralExpCS returns [EObject current=null] : ( () otherlv_1= 'null' ) ;
    public final EObject ruleNullLiteralExpCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;


        	enterRule();

        try {
            // InternalCompleteOCL.g:4349:2: ( ( () otherlv_1= 'null' ) )
            // InternalCompleteOCL.g:4350:2: ( () otherlv_1= 'null' )
            {
            // InternalCompleteOCL.g:4350:2: ( () otherlv_1= 'null' )
            // InternalCompleteOCL.g:4351:3: () otherlv_1= 'null'
            {
            // InternalCompleteOCL.g:4351:3: ()
            // InternalCompleteOCL.g:4352:4:
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
    // InternalCompleteOCL.g:4369:1: entryRuleTypeLiteralCS returns [EObject current=null] : iv_ruleTypeLiteralCS= ruleTypeLiteralCS EOF ;
    public final EObject entryRuleTypeLiteralCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTypeLiteralCS = null;


        try {
            // InternalCompleteOCL.g:4369:54: (iv_ruleTypeLiteralCS= ruleTypeLiteralCS EOF )
            // InternalCompleteOCL.g:4370:2: iv_ruleTypeLiteralCS= ruleTypeLiteralCS EOF
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
    // InternalCompleteOCL.g:4376:1: ruleTypeLiteralCS returns [EObject current=null] : (this_PrimitiveTypeCS_0= rulePrimitiveTypeCS | this_CollectionTypeCS_1= ruleCollectionTypeCS | this_MapTypeCS_2= ruleMapTypeCS | this_TupleTypeCS_3= ruleTupleTypeCS ) ;
    public final EObject ruleTypeLiteralCS() throws RecognitionException {
        EObject current = null;

        EObject this_PrimitiveTypeCS_0 = null;

        EObject this_CollectionTypeCS_1 = null;

        EObject this_MapTypeCS_2 = null;

        EObject this_TupleTypeCS_3 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:4382:2: ( (this_PrimitiveTypeCS_0= rulePrimitiveTypeCS | this_CollectionTypeCS_1= ruleCollectionTypeCS | this_MapTypeCS_2= ruleMapTypeCS | this_TupleTypeCS_3= ruleTupleTypeCS ) )
            // InternalCompleteOCL.g:4383:2: (this_PrimitiveTypeCS_0= rulePrimitiveTypeCS | this_CollectionTypeCS_1= ruleCollectionTypeCS | this_MapTypeCS_2= ruleMapTypeCS | this_TupleTypeCS_3= ruleTupleTypeCS )
            {
            // InternalCompleteOCL.g:4383:2: (this_PrimitiveTypeCS_0= rulePrimitiveTypeCS | this_CollectionTypeCS_1= ruleCollectionTypeCS | this_MapTypeCS_2= ruleMapTypeCS | this_TupleTypeCS_3= ruleTupleTypeCS )
            int alt82=4;
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
                alt82=1;
                }
                break;
            case 76:
            case 77:
            case 78:
            case 79:
            case 80:
                {
                alt82=2;
                }
                break;
            case 73:
                {
                alt82=3;
                }
                break;
            case 74:
                {
                alt82=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 82, 0, input);

                throw nvae;
            }

            switch (alt82) {
                case 1 :
                    // InternalCompleteOCL.g:4384:3: this_PrimitiveTypeCS_0= rulePrimitiveTypeCS
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
                    // InternalCompleteOCL.g:4396:3: this_CollectionTypeCS_1= ruleCollectionTypeCS
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
                    // InternalCompleteOCL.g:4408:3: this_MapTypeCS_2= ruleMapTypeCS
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
                    // InternalCompleteOCL.g:4420:3: this_TupleTypeCS_3= ruleTupleTypeCS
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
    // InternalCompleteOCL.g:4435:1: entryRuleTypeLiteralWithMultiplicityCS returns [EObject current=null] : iv_ruleTypeLiteralWithMultiplicityCS= ruleTypeLiteralWithMultiplicityCS EOF ;
    public final EObject entryRuleTypeLiteralWithMultiplicityCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTypeLiteralWithMultiplicityCS = null;


        try {
            // InternalCompleteOCL.g:4435:70: (iv_ruleTypeLiteralWithMultiplicityCS= ruleTypeLiteralWithMultiplicityCS EOF )
            // InternalCompleteOCL.g:4436:2: iv_ruleTypeLiteralWithMultiplicityCS= ruleTypeLiteralWithMultiplicityCS EOF
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
    // InternalCompleteOCL.g:4442:1: ruleTypeLiteralWithMultiplicityCS returns [EObject current=null] : (this_TypeLiteralCS_0= ruleTypeLiteralCS ( (lv_ownedMultiplicity_1_0= ruleMultiplicityCS ) )? ) ;
    public final EObject ruleTypeLiteralWithMultiplicityCS() throws RecognitionException {
        EObject current = null;

        EObject this_TypeLiteralCS_0 = null;

        EObject lv_ownedMultiplicity_1_0 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:4448:2: ( (this_TypeLiteralCS_0= ruleTypeLiteralCS ( (lv_ownedMultiplicity_1_0= ruleMultiplicityCS ) )? ) )
            // InternalCompleteOCL.g:4449:2: (this_TypeLiteralCS_0= ruleTypeLiteralCS ( (lv_ownedMultiplicity_1_0= ruleMultiplicityCS ) )? )
            {
            // InternalCompleteOCL.g:4449:2: (this_TypeLiteralCS_0= ruleTypeLiteralCS ( (lv_ownedMultiplicity_1_0= ruleMultiplicityCS ) )? )
            // InternalCompleteOCL.g:4450:3: this_TypeLiteralCS_0= ruleTypeLiteralCS ( (lv_ownedMultiplicity_1_0= ruleMultiplicityCS ) )?
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
            // InternalCompleteOCL.g:4461:3: ( (lv_ownedMultiplicity_1_0= ruleMultiplicityCS ) )?
            int alt83=2;
            int LA83_0 = input.LA(1);

            if ( (LA83_0==93) ) {
                alt83=1;
            }
            switch (alt83) {
                case 1 :
                    // InternalCompleteOCL.g:4462:4: (lv_ownedMultiplicity_1_0= ruleMultiplicityCS )
                    {
                    // InternalCompleteOCL.g:4462:4: (lv_ownedMultiplicity_1_0= ruleMultiplicityCS )
                    // InternalCompleteOCL.g:4463:5: lv_ownedMultiplicity_1_0= ruleMultiplicityCS
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
    // InternalCompleteOCL.g:4484:1: entryRuleTypeLiteralExpCS returns [EObject current=null] : iv_ruleTypeLiteralExpCS= ruleTypeLiteralExpCS EOF ;
    public final EObject entryRuleTypeLiteralExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTypeLiteralExpCS = null;


        try {
            // InternalCompleteOCL.g:4484:57: (iv_ruleTypeLiteralExpCS= ruleTypeLiteralExpCS EOF )
            // InternalCompleteOCL.g:4485:2: iv_ruleTypeLiteralExpCS= ruleTypeLiteralExpCS EOF
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
    // InternalCompleteOCL.g:4491:1: ruleTypeLiteralExpCS returns [EObject current=null] : ( (lv_ownedType_0_0= ruleTypeLiteralWithMultiplicityCS ) ) ;
    public final EObject ruleTypeLiteralExpCS() throws RecognitionException {
        EObject current = null;

        EObject lv_ownedType_0_0 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:4497:2: ( ( (lv_ownedType_0_0= ruleTypeLiteralWithMultiplicityCS ) ) )
            // InternalCompleteOCL.g:4498:2: ( (lv_ownedType_0_0= ruleTypeLiteralWithMultiplicityCS ) )
            {
            // InternalCompleteOCL.g:4498:2: ( (lv_ownedType_0_0= ruleTypeLiteralWithMultiplicityCS ) )
            // InternalCompleteOCL.g:4499:3: (lv_ownedType_0_0= ruleTypeLiteralWithMultiplicityCS )
            {
            // InternalCompleteOCL.g:4499:3: (lv_ownedType_0_0= ruleTypeLiteralWithMultiplicityCS )
            // InternalCompleteOCL.g:4500:4: lv_ownedType_0_0= ruleTypeLiteralWithMultiplicityCS
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
    // InternalCompleteOCL.g:4520:1: entryRuleTypeNameExpCS returns [EObject current=null] : iv_ruleTypeNameExpCS= ruleTypeNameExpCS EOF ;
    public final EObject entryRuleTypeNameExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTypeNameExpCS = null;


        try {
            // InternalCompleteOCL.g:4520:54: (iv_ruleTypeNameExpCS= ruleTypeNameExpCS EOF )
            // InternalCompleteOCL.g:4521:2: iv_ruleTypeNameExpCS= ruleTypeNameExpCS EOF
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
    // InternalCompleteOCL.g:4527:1: ruleTypeNameExpCS returns [EObject current=null] : ( ( (lv_ownedPathName_0_0= rulePathNameCS ) ) ( ( (lv_ownedCurlyBracketedClause_1_0= ruleCurlyBracketedClauseCS ) ) (otherlv_2= '{' ( (lv_ownedPatternGuard_3_0= ruleExpCS ) ) otherlv_4= '}' )? )? ) ;
    public final EObject ruleTypeNameExpCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_ownedPathName_0_0 = null;

        EObject lv_ownedCurlyBracketedClause_1_0 = null;

        EObject lv_ownedPatternGuard_3_0 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:4533:2: ( ( ( (lv_ownedPathName_0_0= rulePathNameCS ) ) ( ( (lv_ownedCurlyBracketedClause_1_0= ruleCurlyBracketedClauseCS ) ) (otherlv_2= '{' ( (lv_ownedPatternGuard_3_0= ruleExpCS ) ) otherlv_4= '}' )? )? ) )
            // InternalCompleteOCL.g:4534:2: ( ( (lv_ownedPathName_0_0= rulePathNameCS ) ) ( ( (lv_ownedCurlyBracketedClause_1_0= ruleCurlyBracketedClauseCS ) ) (otherlv_2= '{' ( (lv_ownedPatternGuard_3_0= ruleExpCS ) ) otherlv_4= '}' )? )? )
            {
            // InternalCompleteOCL.g:4534:2: ( ( (lv_ownedPathName_0_0= rulePathNameCS ) ) ( ( (lv_ownedCurlyBracketedClause_1_0= ruleCurlyBracketedClauseCS ) ) (otherlv_2= '{' ( (lv_ownedPatternGuard_3_0= ruleExpCS ) ) otherlv_4= '}' )? )? )
            // InternalCompleteOCL.g:4535:3: ( (lv_ownedPathName_0_0= rulePathNameCS ) ) ( ( (lv_ownedCurlyBracketedClause_1_0= ruleCurlyBracketedClauseCS ) ) (otherlv_2= '{' ( (lv_ownedPatternGuard_3_0= ruleExpCS ) ) otherlv_4= '}' )? )?
            {
            // InternalCompleteOCL.g:4535:3: ( (lv_ownedPathName_0_0= rulePathNameCS ) )
            // InternalCompleteOCL.g:4536:4: (lv_ownedPathName_0_0= rulePathNameCS )
            {
            // InternalCompleteOCL.g:4536:4: (lv_ownedPathName_0_0= rulePathNameCS )
            // InternalCompleteOCL.g:4537:5: lv_ownedPathName_0_0= rulePathNameCS
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

            // InternalCompleteOCL.g:4554:3: ( ( (lv_ownedCurlyBracketedClause_1_0= ruleCurlyBracketedClauseCS ) ) (otherlv_2= '{' ( (lv_ownedPatternGuard_3_0= ruleExpCS ) ) otherlv_4= '}' )? )?
            int alt85=2;
            int LA85_0 = input.LA(1);

            if ( (LA85_0==81) ) {
                alt85=1;
            }
            switch (alt85) {
                case 1 :
                    // InternalCompleteOCL.g:4555:4: ( (lv_ownedCurlyBracketedClause_1_0= ruleCurlyBracketedClauseCS ) ) (otherlv_2= '{' ( (lv_ownedPatternGuard_3_0= ruleExpCS ) ) otherlv_4= '}' )?
                    {
                    // InternalCompleteOCL.g:4555:4: ( (lv_ownedCurlyBracketedClause_1_0= ruleCurlyBracketedClauseCS ) )
                    // InternalCompleteOCL.g:4556:5: (lv_ownedCurlyBracketedClause_1_0= ruleCurlyBracketedClauseCS )
                    {
                    // InternalCompleteOCL.g:4556:5: (lv_ownedCurlyBracketedClause_1_0= ruleCurlyBracketedClauseCS )
                    // InternalCompleteOCL.g:4557:6: lv_ownedCurlyBracketedClause_1_0= ruleCurlyBracketedClauseCS
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

                    // InternalCompleteOCL.g:4574:4: (otherlv_2= '{' ( (lv_ownedPatternGuard_3_0= ruleExpCS ) ) otherlv_4= '}' )?
                    int alt84=2;
                    int LA84_0 = input.LA(1);

                    if ( (LA84_0==81) ) {
                        alt84=1;
                    }
                    switch (alt84) {
                        case 1 :
                            // InternalCompleteOCL.g:4575:5: otherlv_2= '{' ( (lv_ownedPatternGuard_3_0= ruleExpCS ) ) otherlv_4= '}'
                            {
                            otherlv_2=(Token)match(input,81,FollowSets000.FOLLOW_40); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              					newLeafNode(otherlv_2, grammarAccess.getTypeNameExpCSAccess().getLeftCurlyBracketKeyword_1_1_0());

                            }
                            // InternalCompleteOCL.g:4579:5: ( (lv_ownedPatternGuard_3_0= ruleExpCS ) )
                            // InternalCompleteOCL.g:4580:6: (lv_ownedPatternGuard_3_0= ruleExpCS )
                            {
                            // InternalCompleteOCL.g:4580:6: (lv_ownedPatternGuard_3_0= ruleExpCS )
                            // InternalCompleteOCL.g:4581:7: lv_ownedPatternGuard_3_0= ruleExpCS
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
    // InternalCompleteOCL.g:4608:1: entryRuleTypeExpWithoutMultiplicityCS returns [EObject current=null] : iv_ruleTypeExpWithoutMultiplicityCS= ruleTypeExpWithoutMultiplicityCS EOF ;
    public final EObject entryRuleTypeExpWithoutMultiplicityCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTypeExpWithoutMultiplicityCS = null;


        try {
            // InternalCompleteOCL.g:4608:69: (iv_ruleTypeExpWithoutMultiplicityCS= ruleTypeExpWithoutMultiplicityCS EOF )
            // InternalCompleteOCL.g:4609:2: iv_ruleTypeExpWithoutMultiplicityCS= ruleTypeExpWithoutMultiplicityCS EOF
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
    // InternalCompleteOCL.g:4615:1: ruleTypeExpWithoutMultiplicityCS returns [EObject current=null] : (this_TypeNameExpCS_0= ruleTypeNameExpCS | this_TypeLiteralCS_1= ruleTypeLiteralCS | this_CollectionPatternCS_2= ruleCollectionPatternCS ) ;
    public final EObject ruleTypeExpWithoutMultiplicityCS() throws RecognitionException {
        EObject current = null;

        EObject this_TypeNameExpCS_0 = null;

        EObject this_TypeLiteralCS_1 = null;

        EObject this_CollectionPatternCS_2 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:4621:2: ( (this_TypeNameExpCS_0= ruleTypeNameExpCS | this_TypeLiteralCS_1= ruleTypeLiteralCS | this_CollectionPatternCS_2= ruleCollectionPatternCS ) )
            // InternalCompleteOCL.g:4622:2: (this_TypeNameExpCS_0= ruleTypeNameExpCS | this_TypeLiteralCS_1= ruleTypeLiteralCS | this_CollectionPatternCS_2= ruleCollectionPatternCS )
            {
            // InternalCompleteOCL.g:4622:2: (this_TypeNameExpCS_0= ruleTypeNameExpCS | this_TypeLiteralCS_1= ruleTypeLiteralCS | this_CollectionPatternCS_2= ruleCollectionPatternCS )
            int alt86=3;
            alt86 = dfa86.predict(input);
            switch (alt86) {
                case 1 :
                    // InternalCompleteOCL.g:4623:3: this_TypeNameExpCS_0= ruleTypeNameExpCS
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
                    // InternalCompleteOCL.g:4635:3: this_TypeLiteralCS_1= ruleTypeLiteralCS
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
                    // InternalCompleteOCL.g:4647:3: this_CollectionPatternCS_2= ruleCollectionPatternCS
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
    // InternalCompleteOCL.g:4662:1: entryRuleTypeExpCS returns [EObject current=null] : iv_ruleTypeExpCS= ruleTypeExpCS EOF ;
    public final EObject entryRuleTypeExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTypeExpCS = null;


        try {
            // InternalCompleteOCL.g:4662:50: (iv_ruleTypeExpCS= ruleTypeExpCS EOF )
            // InternalCompleteOCL.g:4663:2: iv_ruleTypeExpCS= ruleTypeExpCS EOF
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
    // InternalCompleteOCL.g:4669:1: ruleTypeExpCS returns [EObject current=null] : (this_TypeExpWithoutMultiplicityCS_0= ruleTypeExpWithoutMultiplicityCS ( (lv_ownedMultiplicity_1_0= ruleMultiplicityCS ) )? ) ;
    public final EObject ruleTypeExpCS() throws RecognitionException {
        EObject current = null;

        EObject this_TypeExpWithoutMultiplicityCS_0 = null;

        EObject lv_ownedMultiplicity_1_0 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:4675:2: ( (this_TypeExpWithoutMultiplicityCS_0= ruleTypeExpWithoutMultiplicityCS ( (lv_ownedMultiplicity_1_0= ruleMultiplicityCS ) )? ) )
            // InternalCompleteOCL.g:4676:2: (this_TypeExpWithoutMultiplicityCS_0= ruleTypeExpWithoutMultiplicityCS ( (lv_ownedMultiplicity_1_0= ruleMultiplicityCS ) )? )
            {
            // InternalCompleteOCL.g:4676:2: (this_TypeExpWithoutMultiplicityCS_0= ruleTypeExpWithoutMultiplicityCS ( (lv_ownedMultiplicity_1_0= ruleMultiplicityCS ) )? )
            // InternalCompleteOCL.g:4677:3: this_TypeExpWithoutMultiplicityCS_0= ruleTypeExpWithoutMultiplicityCS ( (lv_ownedMultiplicity_1_0= ruleMultiplicityCS ) )?
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
            // InternalCompleteOCL.g:4688:3: ( (lv_ownedMultiplicity_1_0= ruleMultiplicityCS ) )?
            int alt87=2;
            int LA87_0 = input.LA(1);

            if ( (LA87_0==93) ) {
                alt87=1;
            }
            switch (alt87) {
                case 1 :
                    // InternalCompleteOCL.g:4689:4: (lv_ownedMultiplicity_1_0= ruleMultiplicityCS )
                    {
                    // InternalCompleteOCL.g:4689:4: (lv_ownedMultiplicity_1_0= ruleMultiplicityCS )
                    // InternalCompleteOCL.g:4690:5: lv_ownedMultiplicity_1_0= ruleMultiplicityCS
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
    // InternalCompleteOCL.g:4711:1: entryRuleExpCS returns [EObject current=null] : iv_ruleExpCS= ruleExpCS EOF ;
    public final EObject entryRuleExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpCS = null;


        try {
            // InternalCompleteOCL.g:4711:46: (iv_ruleExpCS= ruleExpCS EOF )
            // InternalCompleteOCL.g:4712:2: iv_ruleExpCS= ruleExpCS EOF
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
    // InternalCompleteOCL.g:4718:1: ruleExpCS returns [EObject current=null] : ( (this_PrefixedPrimaryExpCS_0= rulePrefixedPrimaryExpCS ( () ( (lv_name_2_0= ruleBinaryOperatorName ) ) ( (lv_ownedRight_3_0= ruleExpCS ) ) )? ) | this_PrefixedLetExpCS_4= rulePrefixedLetExpCS ) ;
    public final EObject ruleExpCS() throws RecognitionException {
        EObject current = null;

        EObject this_PrefixedPrimaryExpCS_0 = null;

        AntlrDatatypeRuleToken lv_name_2_0 = null;

        EObject lv_ownedRight_3_0 = null;

        EObject this_PrefixedLetExpCS_4 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:4724:2: ( ( (this_PrefixedPrimaryExpCS_0= rulePrefixedPrimaryExpCS ( () ( (lv_name_2_0= ruleBinaryOperatorName ) ) ( (lv_ownedRight_3_0= ruleExpCS ) ) )? ) | this_PrefixedLetExpCS_4= rulePrefixedLetExpCS ) )
            // InternalCompleteOCL.g:4725:2: ( (this_PrefixedPrimaryExpCS_0= rulePrefixedPrimaryExpCS ( () ( (lv_name_2_0= ruleBinaryOperatorName ) ) ( (lv_ownedRight_3_0= ruleExpCS ) ) )? ) | this_PrefixedLetExpCS_4= rulePrefixedLetExpCS )
            {
            // InternalCompleteOCL.g:4725:2: ( (this_PrefixedPrimaryExpCS_0= rulePrefixedPrimaryExpCS ( () ( (lv_name_2_0= ruleBinaryOperatorName ) ) ( (lv_ownedRight_3_0= ruleExpCS ) ) )? ) | this_PrefixedLetExpCS_4= rulePrefixedLetExpCS )
            int alt89=2;
            alt89 = dfa89.predict(input);
            switch (alt89) {
                case 1 :
                    // InternalCompleteOCL.g:4726:3: (this_PrefixedPrimaryExpCS_0= rulePrefixedPrimaryExpCS ( () ( (lv_name_2_0= ruleBinaryOperatorName ) ) ( (lv_ownedRight_3_0= ruleExpCS ) ) )? )
                    {
                    // InternalCompleteOCL.g:4726:3: (this_PrefixedPrimaryExpCS_0= rulePrefixedPrimaryExpCS ( () ( (lv_name_2_0= ruleBinaryOperatorName ) ) ( (lv_ownedRight_3_0= ruleExpCS ) ) )? )
                    // InternalCompleteOCL.g:4727:4: this_PrefixedPrimaryExpCS_0= rulePrefixedPrimaryExpCS ( () ( (lv_name_2_0= ruleBinaryOperatorName ) ) ( (lv_ownedRight_3_0= ruleExpCS ) ) )?
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
                    // InternalCompleteOCL.g:4738:4: ( () ( (lv_name_2_0= ruleBinaryOperatorName ) ) ( (lv_ownedRight_3_0= ruleExpCS ) ) )?
                    int alt88=2;
                    int LA88_0 = input.LA(1);

                    if ( ((LA88_0>=17 && LA88_0<=18)||LA88_0==29||(LA88_0>=39 && LA88_0<=40)||LA88_0==52||(LA88_0>=55 && LA88_0<=72)) ) {
                        alt88=1;
                    }
                    switch (alt88) {
                        case 1 :
                            // InternalCompleteOCL.g:4739:5: () ( (lv_name_2_0= ruleBinaryOperatorName ) ) ( (lv_ownedRight_3_0= ruleExpCS ) )
                            {
                            // InternalCompleteOCL.g:4739:5: ()
                            // InternalCompleteOCL.g:4740:6:
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

                            // InternalCompleteOCL.g:4749:5: ( (lv_name_2_0= ruleBinaryOperatorName ) )
                            // InternalCompleteOCL.g:4750:6: (lv_name_2_0= ruleBinaryOperatorName )
                            {
                            // InternalCompleteOCL.g:4750:6: (lv_name_2_0= ruleBinaryOperatorName )
                            // InternalCompleteOCL.g:4751:7: lv_name_2_0= ruleBinaryOperatorName
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

                            // InternalCompleteOCL.g:4768:5: ( (lv_ownedRight_3_0= ruleExpCS ) )
                            // InternalCompleteOCL.g:4769:6: (lv_ownedRight_3_0= ruleExpCS )
                            {
                            // InternalCompleteOCL.g:4769:6: (lv_ownedRight_3_0= ruleExpCS )
                            // InternalCompleteOCL.g:4770:7: lv_ownedRight_3_0= ruleExpCS
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
                    // InternalCompleteOCL.g:4790:3: this_PrefixedLetExpCS_4= rulePrefixedLetExpCS
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
    // InternalCompleteOCL.g:4805:1: entryRulePrefixedLetExpCS returns [EObject current=null] : iv_rulePrefixedLetExpCS= rulePrefixedLetExpCS EOF ;
    public final EObject entryRulePrefixedLetExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePrefixedLetExpCS = null;


        try {
            // InternalCompleteOCL.g:4805:57: (iv_rulePrefixedLetExpCS= rulePrefixedLetExpCS EOF )
            // InternalCompleteOCL.g:4806:2: iv_rulePrefixedLetExpCS= rulePrefixedLetExpCS EOF
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
    // InternalCompleteOCL.g:4812:1: rulePrefixedLetExpCS returns [EObject current=null] : ( ( () ( (lv_name_1_0= ruleUnaryOperatorName ) ) ( (lv_ownedRight_2_0= rulePrefixedLetExpCS ) ) ) | this_LetExpCS_3= ruleLetExpCS ) ;
    public final EObject rulePrefixedLetExpCS() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_name_1_0 = null;

        EObject lv_ownedRight_2_0 = null;

        EObject this_LetExpCS_3 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:4818:2: ( ( ( () ( (lv_name_1_0= ruleUnaryOperatorName ) ) ( (lv_ownedRight_2_0= rulePrefixedLetExpCS ) ) ) | this_LetExpCS_3= ruleLetExpCS ) )
            // InternalCompleteOCL.g:4819:2: ( ( () ( (lv_name_1_0= ruleUnaryOperatorName ) ) ( (lv_ownedRight_2_0= rulePrefixedLetExpCS ) ) ) | this_LetExpCS_3= ruleLetExpCS )
            {
            // InternalCompleteOCL.g:4819:2: ( ( () ( (lv_name_1_0= ruleUnaryOperatorName ) ) ( (lv_ownedRight_2_0= rulePrefixedLetExpCS ) ) ) | this_LetExpCS_3= ruleLetExpCS )
            int alt90=2;
            int LA90_0 = input.LA(1);

            if ( ((LA90_0>=52 && LA90_0<=54)) ) {
                alt90=1;
            }
            else if ( (LA90_0==103) ) {
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
                    // InternalCompleteOCL.g:4820:3: ( () ( (lv_name_1_0= ruleUnaryOperatorName ) ) ( (lv_ownedRight_2_0= rulePrefixedLetExpCS ) ) )
                    {
                    // InternalCompleteOCL.g:4820:3: ( () ( (lv_name_1_0= ruleUnaryOperatorName ) ) ( (lv_ownedRight_2_0= rulePrefixedLetExpCS ) ) )
                    // InternalCompleteOCL.g:4821:4: () ( (lv_name_1_0= ruleUnaryOperatorName ) ) ( (lv_ownedRight_2_0= rulePrefixedLetExpCS ) )
                    {
                    // InternalCompleteOCL.g:4821:4: ()
                    // InternalCompleteOCL.g:4822:5:
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

                    // InternalCompleteOCL.g:4831:4: ( (lv_name_1_0= ruleUnaryOperatorName ) )
                    // InternalCompleteOCL.g:4832:5: (lv_name_1_0= ruleUnaryOperatorName )
                    {
                    // InternalCompleteOCL.g:4832:5: (lv_name_1_0= ruleUnaryOperatorName )
                    // InternalCompleteOCL.g:4833:6: lv_name_1_0= ruleUnaryOperatorName
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

                    // InternalCompleteOCL.g:4850:4: ( (lv_ownedRight_2_0= rulePrefixedLetExpCS ) )
                    // InternalCompleteOCL.g:4851:5: (lv_ownedRight_2_0= rulePrefixedLetExpCS )
                    {
                    // InternalCompleteOCL.g:4851:5: (lv_ownedRight_2_0= rulePrefixedLetExpCS )
                    // InternalCompleteOCL.g:4852:6: lv_ownedRight_2_0= rulePrefixedLetExpCS
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
                    // InternalCompleteOCL.g:4871:3: this_LetExpCS_3= ruleLetExpCS
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
    // InternalCompleteOCL.g:4886:1: entryRulePrefixedPrimaryExpCS returns [EObject current=null] : iv_rulePrefixedPrimaryExpCS= rulePrefixedPrimaryExpCS EOF ;
    public final EObject entryRulePrefixedPrimaryExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePrefixedPrimaryExpCS = null;


        try {
            // InternalCompleteOCL.g:4886:61: (iv_rulePrefixedPrimaryExpCS= rulePrefixedPrimaryExpCS EOF )
            // InternalCompleteOCL.g:4887:2: iv_rulePrefixedPrimaryExpCS= rulePrefixedPrimaryExpCS EOF
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
    // InternalCompleteOCL.g:4893:1: rulePrefixedPrimaryExpCS returns [EObject current=null] : ( ( () ( (lv_name_1_0= ruleUnaryOperatorName ) ) ( (lv_ownedRight_2_0= rulePrefixedPrimaryExpCS ) ) ) | this_PrimaryExpCS_3= rulePrimaryExpCS ) ;
    public final EObject rulePrefixedPrimaryExpCS() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_name_1_0 = null;

        EObject lv_ownedRight_2_0 = null;

        EObject this_PrimaryExpCS_3 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:4899:2: ( ( ( () ( (lv_name_1_0= ruleUnaryOperatorName ) ) ( (lv_ownedRight_2_0= rulePrefixedPrimaryExpCS ) ) ) | this_PrimaryExpCS_3= rulePrimaryExpCS ) )
            // InternalCompleteOCL.g:4900:2: ( ( () ( (lv_name_1_0= ruleUnaryOperatorName ) ) ( (lv_ownedRight_2_0= rulePrefixedPrimaryExpCS ) ) ) | this_PrimaryExpCS_3= rulePrimaryExpCS )
            {
            // InternalCompleteOCL.g:4900:2: ( ( () ( (lv_name_1_0= ruleUnaryOperatorName ) ) ( (lv_ownedRight_2_0= rulePrefixedPrimaryExpCS ) ) ) | this_PrimaryExpCS_3= rulePrimaryExpCS )
            int alt91=2;
            int LA91_0 = input.LA(1);

            if ( ((LA91_0>=52 && LA91_0<=54)) ) {
                alt91=1;
            }
            else if ( ((LA91_0>=RULE_SIMPLE_ID && LA91_0<=RULE_SINGLE_QUOTED_STRING)||LA91_0==21||(LA91_0>=30 && LA91_0<=32)||(LA91_0>=42 && LA91_0<=51)||LA91_0==55||(LA91_0>=73 && LA91_0<=74)||(LA91_0>=76 && LA91_0<=80)||LA91_0==85||(LA91_0>=88 && LA91_0<=91)||LA91_0==98||LA91_0==104) ) {
                alt91=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 91, 0, input);

                throw nvae;
            }
            switch (alt91) {
                case 1 :
                    // InternalCompleteOCL.g:4901:3: ( () ( (lv_name_1_0= ruleUnaryOperatorName ) ) ( (lv_ownedRight_2_0= rulePrefixedPrimaryExpCS ) ) )
                    {
                    // InternalCompleteOCL.g:4901:3: ( () ( (lv_name_1_0= ruleUnaryOperatorName ) ) ( (lv_ownedRight_2_0= rulePrefixedPrimaryExpCS ) ) )
                    // InternalCompleteOCL.g:4902:4: () ( (lv_name_1_0= ruleUnaryOperatorName ) ) ( (lv_ownedRight_2_0= rulePrefixedPrimaryExpCS ) )
                    {
                    // InternalCompleteOCL.g:4902:4: ()
                    // InternalCompleteOCL.g:4903:5:
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

                    // InternalCompleteOCL.g:4912:4: ( (lv_name_1_0= ruleUnaryOperatorName ) )
                    // InternalCompleteOCL.g:4913:5: (lv_name_1_0= ruleUnaryOperatorName )
                    {
                    // InternalCompleteOCL.g:4913:5: (lv_name_1_0= ruleUnaryOperatorName )
                    // InternalCompleteOCL.g:4914:6: lv_name_1_0= ruleUnaryOperatorName
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

                    // InternalCompleteOCL.g:4931:4: ( (lv_ownedRight_2_0= rulePrefixedPrimaryExpCS ) )
                    // InternalCompleteOCL.g:4932:5: (lv_ownedRight_2_0= rulePrefixedPrimaryExpCS )
                    {
                    // InternalCompleteOCL.g:4932:5: (lv_ownedRight_2_0= rulePrefixedPrimaryExpCS )
                    // InternalCompleteOCL.g:4933:6: lv_ownedRight_2_0= rulePrefixedPrimaryExpCS
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
                    // InternalCompleteOCL.g:4952:3: this_PrimaryExpCS_3= rulePrimaryExpCS
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
    // InternalCompleteOCL.g:4967:1: entryRulePrimaryExpCS returns [EObject current=null] : iv_rulePrimaryExpCS= rulePrimaryExpCS EOF ;
    public final EObject entryRulePrimaryExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePrimaryExpCS = null;


        try {
            // InternalCompleteOCL.g:4967:53: (iv_rulePrimaryExpCS= rulePrimaryExpCS EOF )
            // InternalCompleteOCL.g:4968:2: iv_rulePrimaryExpCS= rulePrimaryExpCS EOF
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
    // InternalCompleteOCL.g:4974:1: rulePrimaryExpCS returns [EObject current=null] : (this_NestedExpCS_0= ruleNestedExpCS | this_IfExpCS_1= ruleIfExpCS | this_SelfExpCS_2= ruleSelfExpCS | this_PrimitiveLiteralExpCS_3= rulePrimitiveLiteralExpCS | this_TupleLiteralExpCS_4= ruleTupleLiteralExpCS | this_MapLiteralExpCS_5= ruleMapLiteralExpCS | this_CollectionLiteralExpCS_6= ruleCollectionLiteralExpCS | this_LambdaLiteralExpCS_7= ruleLambdaLiteralExpCS | this_TypeLiteralExpCS_8= ruleTypeLiteralExpCS | this_NameExpCS_9= ruleNameExpCS ) ;
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
            // InternalCompleteOCL.g:4980:2: ( (this_NestedExpCS_0= ruleNestedExpCS | this_IfExpCS_1= ruleIfExpCS | this_SelfExpCS_2= ruleSelfExpCS | this_PrimitiveLiteralExpCS_3= rulePrimitiveLiteralExpCS | this_TupleLiteralExpCS_4= ruleTupleLiteralExpCS | this_MapLiteralExpCS_5= ruleMapLiteralExpCS | this_CollectionLiteralExpCS_6= ruleCollectionLiteralExpCS | this_LambdaLiteralExpCS_7= ruleLambdaLiteralExpCS | this_TypeLiteralExpCS_8= ruleTypeLiteralExpCS | this_NameExpCS_9= ruleNameExpCS ) )
            // InternalCompleteOCL.g:4981:2: (this_NestedExpCS_0= ruleNestedExpCS | this_IfExpCS_1= ruleIfExpCS | this_SelfExpCS_2= ruleSelfExpCS | this_PrimitiveLiteralExpCS_3= rulePrimitiveLiteralExpCS | this_TupleLiteralExpCS_4= ruleTupleLiteralExpCS | this_MapLiteralExpCS_5= ruleMapLiteralExpCS | this_CollectionLiteralExpCS_6= ruleCollectionLiteralExpCS | this_LambdaLiteralExpCS_7= ruleLambdaLiteralExpCS | this_TypeLiteralExpCS_8= ruleTypeLiteralExpCS | this_NameExpCS_9= ruleNameExpCS )
            {
            // InternalCompleteOCL.g:4981:2: (this_NestedExpCS_0= ruleNestedExpCS | this_IfExpCS_1= ruleIfExpCS | this_SelfExpCS_2= ruleSelfExpCS | this_PrimitiveLiteralExpCS_3= rulePrimitiveLiteralExpCS | this_TupleLiteralExpCS_4= ruleTupleLiteralExpCS | this_MapLiteralExpCS_5= ruleMapLiteralExpCS | this_CollectionLiteralExpCS_6= ruleCollectionLiteralExpCS | this_LambdaLiteralExpCS_7= ruleLambdaLiteralExpCS | this_TypeLiteralExpCS_8= ruleTypeLiteralExpCS | this_NameExpCS_9= ruleNameExpCS )
            int alt92=10;
            alt92 = dfa92.predict(input);
            switch (alt92) {
                case 1 :
                    // InternalCompleteOCL.g:4982:3: this_NestedExpCS_0= ruleNestedExpCS
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
                    // InternalCompleteOCL.g:4994:3: this_IfExpCS_1= ruleIfExpCS
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
                    // InternalCompleteOCL.g:5006:3: this_SelfExpCS_2= ruleSelfExpCS
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
                    // InternalCompleteOCL.g:5018:3: this_PrimitiveLiteralExpCS_3= rulePrimitiveLiteralExpCS
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
                    // InternalCompleteOCL.g:5030:3: this_TupleLiteralExpCS_4= ruleTupleLiteralExpCS
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
                    // InternalCompleteOCL.g:5042:3: this_MapLiteralExpCS_5= ruleMapLiteralExpCS
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
                    // InternalCompleteOCL.g:5054:3: this_CollectionLiteralExpCS_6= ruleCollectionLiteralExpCS
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
                    // InternalCompleteOCL.g:5066:3: this_LambdaLiteralExpCS_7= ruleLambdaLiteralExpCS
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
                    // InternalCompleteOCL.g:5078:3: this_TypeLiteralExpCS_8= ruleTypeLiteralExpCS
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
                    // InternalCompleteOCL.g:5090:3: this_NameExpCS_9= ruleNameExpCS
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
    // InternalCompleteOCL.g:5105:1: entryRuleNameExpCS returns [EObject current=null] : iv_ruleNameExpCS= ruleNameExpCS EOF ;
    public final EObject entryRuleNameExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNameExpCS = null;


        try {
            // InternalCompleteOCL.g:5105:50: (iv_ruleNameExpCS= ruleNameExpCS EOF )
            // InternalCompleteOCL.g:5106:2: iv_ruleNameExpCS= ruleNameExpCS EOF
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
    // InternalCompleteOCL.g:5112:1: ruleNameExpCS returns [EObject current=null] : ( ( (lv_ownedPathName_0_0= rulePathNameCS ) ) ( (lv_ownedSquareBracketedClauses_1_0= ruleSquareBracketedClauseCS ) )* ( (lv_ownedRoundBracketedClause_2_0= ruleRoundBracketedClauseCS ) )? ( (lv_ownedCurlyBracketedClause_3_0= ruleCurlyBracketedClauseCS ) )? ( ( (lv_isPre_4_0= '@' ) ) otherlv_5= 'pre' )? ) ;
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
            // InternalCompleteOCL.g:5118:2: ( ( ( (lv_ownedPathName_0_0= rulePathNameCS ) ) ( (lv_ownedSquareBracketedClauses_1_0= ruleSquareBracketedClauseCS ) )* ( (lv_ownedRoundBracketedClause_2_0= ruleRoundBracketedClauseCS ) )? ( (lv_ownedCurlyBracketedClause_3_0= ruleCurlyBracketedClauseCS ) )? ( ( (lv_isPre_4_0= '@' ) ) otherlv_5= 'pre' )? ) )
            // InternalCompleteOCL.g:5119:2: ( ( (lv_ownedPathName_0_0= rulePathNameCS ) ) ( (lv_ownedSquareBracketedClauses_1_0= ruleSquareBracketedClauseCS ) )* ( (lv_ownedRoundBracketedClause_2_0= ruleRoundBracketedClauseCS ) )? ( (lv_ownedCurlyBracketedClause_3_0= ruleCurlyBracketedClauseCS ) )? ( ( (lv_isPre_4_0= '@' ) ) otherlv_5= 'pre' )? )
            {
            // InternalCompleteOCL.g:5119:2: ( ( (lv_ownedPathName_0_0= rulePathNameCS ) ) ( (lv_ownedSquareBracketedClauses_1_0= ruleSquareBracketedClauseCS ) )* ( (lv_ownedRoundBracketedClause_2_0= ruleRoundBracketedClauseCS ) )? ( (lv_ownedCurlyBracketedClause_3_0= ruleCurlyBracketedClauseCS ) )? ( ( (lv_isPre_4_0= '@' ) ) otherlv_5= 'pre' )? )
            // InternalCompleteOCL.g:5120:3: ( (lv_ownedPathName_0_0= rulePathNameCS ) ) ( (lv_ownedSquareBracketedClauses_1_0= ruleSquareBracketedClauseCS ) )* ( (lv_ownedRoundBracketedClause_2_0= ruleRoundBracketedClauseCS ) )? ( (lv_ownedCurlyBracketedClause_3_0= ruleCurlyBracketedClauseCS ) )? ( ( (lv_isPre_4_0= '@' ) ) otherlv_5= 'pre' )?
            {
            // InternalCompleteOCL.g:5120:3: ( (lv_ownedPathName_0_0= rulePathNameCS ) )
            // InternalCompleteOCL.g:5121:4: (lv_ownedPathName_0_0= rulePathNameCS )
            {
            // InternalCompleteOCL.g:5121:4: (lv_ownedPathName_0_0= rulePathNameCS )
            // InternalCompleteOCL.g:5122:5: lv_ownedPathName_0_0= rulePathNameCS
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

            // InternalCompleteOCL.g:5139:3: ( (lv_ownedSquareBracketedClauses_1_0= ruleSquareBracketedClauseCS ) )*
            loop93:
            do {
                int alt93=2;
                int LA93_0 = input.LA(1);

                if ( (LA93_0==93) ) {
                    alt93=1;
                }


                switch (alt93) {
            	case 1 :
            	    // InternalCompleteOCL.g:5140:4: (lv_ownedSquareBracketedClauses_1_0= ruleSquareBracketedClauseCS )
            	    {
            	    // InternalCompleteOCL.g:5140:4: (lv_ownedSquareBracketedClauses_1_0= ruleSquareBracketedClauseCS )
            	    // InternalCompleteOCL.g:5141:5: lv_ownedSquareBracketedClauses_1_0= ruleSquareBracketedClauseCS
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
            	    break loop93;
                }
            } while (true);

            // InternalCompleteOCL.g:5158:3: ( (lv_ownedRoundBracketedClause_2_0= ruleRoundBracketedClauseCS ) )?
            int alt94=2;
            int LA94_0 = input.LA(1);

            if ( (LA94_0==21) ) {
                alt94=1;
            }
            switch (alt94) {
                case 1 :
                    // InternalCompleteOCL.g:5159:4: (lv_ownedRoundBracketedClause_2_0= ruleRoundBracketedClauseCS )
                    {
                    // InternalCompleteOCL.g:5159:4: (lv_ownedRoundBracketedClause_2_0= ruleRoundBracketedClauseCS )
                    // InternalCompleteOCL.g:5160:5: lv_ownedRoundBracketedClause_2_0= ruleRoundBracketedClauseCS
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

            // InternalCompleteOCL.g:5177:3: ( (lv_ownedCurlyBracketedClause_3_0= ruleCurlyBracketedClauseCS ) )?
            int alt95=2;
            int LA95_0 = input.LA(1);

            if ( (LA95_0==81) ) {
                alt95=1;
            }
            switch (alt95) {
                case 1 :
                    // InternalCompleteOCL.g:5178:4: (lv_ownedCurlyBracketedClause_3_0= ruleCurlyBracketedClauseCS )
                    {
                    // InternalCompleteOCL.g:5178:4: (lv_ownedCurlyBracketedClause_3_0= ruleCurlyBracketedClauseCS )
                    // InternalCompleteOCL.g:5179:5: lv_ownedCurlyBracketedClause_3_0= ruleCurlyBracketedClauseCS
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

            // InternalCompleteOCL.g:5196:3: ( ( (lv_isPre_4_0= '@' ) ) otherlv_5= 'pre' )?
            int alt96=2;
            int LA96_0 = input.LA(1);

            if ( (LA96_0==92) ) {
                alt96=1;
            }
            switch (alt96) {
                case 1 :
                    // InternalCompleteOCL.g:5197:4: ( (lv_isPre_4_0= '@' ) ) otherlv_5= 'pre'
                    {
                    // InternalCompleteOCL.g:5197:4: ( (lv_isPre_4_0= '@' ) )
                    // InternalCompleteOCL.g:5198:5: (lv_isPre_4_0= '@' )
                    {
                    // InternalCompleteOCL.g:5198:5: (lv_isPre_4_0= '@' )
                    // InternalCompleteOCL.g:5199:6: lv_isPre_4_0= '@'
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
    // InternalCompleteOCL.g:5220:1: entryRuleCurlyBracketedClauseCS returns [EObject current=null] : iv_ruleCurlyBracketedClauseCS= ruleCurlyBracketedClauseCS EOF ;
    public final EObject entryRuleCurlyBracketedClauseCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCurlyBracketedClauseCS = null;


        try {
            // InternalCompleteOCL.g:5220:63: (iv_ruleCurlyBracketedClauseCS= ruleCurlyBracketedClauseCS EOF )
            // InternalCompleteOCL.g:5221:2: iv_ruleCurlyBracketedClauseCS= ruleCurlyBracketedClauseCS EOF
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
    // InternalCompleteOCL.g:5227:1: ruleCurlyBracketedClauseCS returns [EObject current=null] : ( () otherlv_1= '{' ( ( (lv_ownedParts_2_0= ruleShadowPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleShadowPartCS ) ) )* )? otherlv_5= '}' ) ;
    public final EObject ruleCurlyBracketedClauseCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        EObject lv_ownedParts_2_0 = null;

        EObject lv_ownedParts_4_0 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:5233:2: ( ( () otherlv_1= '{' ( ( (lv_ownedParts_2_0= ruleShadowPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleShadowPartCS ) ) )* )? otherlv_5= '}' ) )
            // InternalCompleteOCL.g:5234:2: ( () otherlv_1= '{' ( ( (lv_ownedParts_2_0= ruleShadowPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleShadowPartCS ) ) )* )? otherlv_5= '}' )
            {
            // InternalCompleteOCL.g:5234:2: ( () otherlv_1= '{' ( ( (lv_ownedParts_2_0= ruleShadowPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleShadowPartCS ) ) )* )? otherlv_5= '}' )
            // InternalCompleteOCL.g:5235:3: () otherlv_1= '{' ( ( (lv_ownedParts_2_0= ruleShadowPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleShadowPartCS ) ) )* )? otherlv_5= '}'
            {
            // InternalCompleteOCL.g:5235:3: ()
            // InternalCompleteOCL.g:5236:4:
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
            // InternalCompleteOCL.g:5249:3: ( ( (lv_ownedParts_2_0= ruleShadowPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleShadowPartCS ) ) )* )?
            int alt98=2;
            int LA98_0 = input.LA(1);

            if ( ((LA98_0>=RULE_SIMPLE_ID && LA98_0<=RULE_ESCAPED_ID)||LA98_0==RULE_SINGLE_QUOTED_STRING||(LA98_0>=30 && LA98_0<=32)) ) {
                alt98=1;
            }
            switch (alt98) {
                case 1 :
                    // InternalCompleteOCL.g:5250:4: ( (lv_ownedParts_2_0= ruleShadowPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleShadowPartCS ) ) )*
                    {
                    // InternalCompleteOCL.g:5250:4: ( (lv_ownedParts_2_0= ruleShadowPartCS ) )
                    // InternalCompleteOCL.g:5251:5: (lv_ownedParts_2_0= ruleShadowPartCS )
                    {
                    // InternalCompleteOCL.g:5251:5: (lv_ownedParts_2_0= ruleShadowPartCS )
                    // InternalCompleteOCL.g:5252:6: lv_ownedParts_2_0= ruleShadowPartCS
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

                    // InternalCompleteOCL.g:5269:4: (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleShadowPartCS ) ) )*
                    loop97:
                    do {
                        int alt97=2;
                        int LA97_0 = input.LA(1);

                        if ( (LA97_0==28) ) {
                            alt97=1;
                        }


                        switch (alt97) {
                    	case 1 :
                    	    // InternalCompleteOCL.g:5270:5: otherlv_3= ',' ( (lv_ownedParts_4_0= ruleShadowPartCS ) )
                    	    {
                    	    otherlv_3=(Token)match(input,28,FollowSets000.FOLLOW_22); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      					newLeafNode(otherlv_3, grammarAccess.getCurlyBracketedClauseCSAccess().getCommaKeyword_2_1_0());

                    	    }
                    	    // InternalCompleteOCL.g:5274:5: ( (lv_ownedParts_4_0= ruleShadowPartCS ) )
                    	    // InternalCompleteOCL.g:5275:6: (lv_ownedParts_4_0= ruleShadowPartCS )
                    	    {
                    	    // InternalCompleteOCL.g:5275:6: (lv_ownedParts_4_0= ruleShadowPartCS )
                    	    // InternalCompleteOCL.g:5276:7: lv_ownedParts_4_0= ruleShadowPartCS
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
                    	    break loop97;
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
    // InternalCompleteOCL.g:5303:1: entryRuleRoundBracketedClauseCS returns [EObject current=null] : iv_ruleRoundBracketedClauseCS= ruleRoundBracketedClauseCS EOF ;
    public final EObject entryRuleRoundBracketedClauseCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRoundBracketedClauseCS = null;


        try {
            // InternalCompleteOCL.g:5303:63: (iv_ruleRoundBracketedClauseCS= ruleRoundBracketedClauseCS EOF )
            // InternalCompleteOCL.g:5304:2: iv_ruleRoundBracketedClauseCS= ruleRoundBracketedClauseCS EOF
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
    // InternalCompleteOCL.g:5310:1: ruleRoundBracketedClauseCS returns [EObject current=null] : ( () otherlv_1= '(' ( ( (lv_ownedArguments_2_0= ruleNavigatingArgCS ) ) ( ( (lv_ownedArguments_3_1= ruleNavigatingCommaArgCS | lv_ownedArguments_3_2= ruleNavigatingSemiArgCS | lv_ownedArguments_3_3= ruleNavigatingBarArgCS ) ) )* )? otherlv_4= ')' ) ;
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
            // InternalCompleteOCL.g:5316:2: ( ( () otherlv_1= '(' ( ( (lv_ownedArguments_2_0= ruleNavigatingArgCS ) ) ( ( (lv_ownedArguments_3_1= ruleNavigatingCommaArgCS | lv_ownedArguments_3_2= ruleNavigatingSemiArgCS | lv_ownedArguments_3_3= ruleNavigatingBarArgCS ) ) )* )? otherlv_4= ')' ) )
            // InternalCompleteOCL.g:5317:2: ( () otherlv_1= '(' ( ( (lv_ownedArguments_2_0= ruleNavigatingArgCS ) ) ( ( (lv_ownedArguments_3_1= ruleNavigatingCommaArgCS | lv_ownedArguments_3_2= ruleNavigatingSemiArgCS | lv_ownedArguments_3_3= ruleNavigatingBarArgCS ) ) )* )? otherlv_4= ')' )
            {
            // InternalCompleteOCL.g:5317:2: ( () otherlv_1= '(' ( ( (lv_ownedArguments_2_0= ruleNavigatingArgCS ) ) ( ( (lv_ownedArguments_3_1= ruleNavigatingCommaArgCS | lv_ownedArguments_3_2= ruleNavigatingSemiArgCS | lv_ownedArguments_3_3= ruleNavigatingBarArgCS ) ) )* )? otherlv_4= ')' )
            // InternalCompleteOCL.g:5318:3: () otherlv_1= '(' ( ( (lv_ownedArguments_2_0= ruleNavigatingArgCS ) ) ( ( (lv_ownedArguments_3_1= ruleNavigatingCommaArgCS | lv_ownedArguments_3_2= ruleNavigatingSemiArgCS | lv_ownedArguments_3_3= ruleNavigatingBarArgCS ) ) )* )? otherlv_4= ')'
            {
            // InternalCompleteOCL.g:5318:3: ()
            // InternalCompleteOCL.g:5319:4:
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
            // InternalCompleteOCL.g:5332:3: ( ( (lv_ownedArguments_2_0= ruleNavigatingArgCS ) ) ( ( (lv_ownedArguments_3_1= ruleNavigatingCommaArgCS | lv_ownedArguments_3_2= ruleNavigatingSemiArgCS | lv_ownedArguments_3_3= ruleNavigatingBarArgCS ) ) )* )?
            int alt101=2;
            int LA101_0 = input.LA(1);

            if ( ((LA101_0>=RULE_SIMPLE_ID && LA101_0<=RULE_SINGLE_QUOTED_STRING)||LA101_0==21||LA101_0==23||(LA101_0>=30 && LA101_0<=32)||(LA101_0>=41 && LA101_0<=55)||(LA101_0>=73 && LA101_0<=74)||(LA101_0>=76 && LA101_0<=80)||LA101_0==85||(LA101_0>=88 && LA101_0<=91)||LA101_0==98||(LA101_0>=103 && LA101_0<=104)) ) {
                alt101=1;
            }
            switch (alt101) {
                case 1 :
                    // InternalCompleteOCL.g:5333:4: ( (lv_ownedArguments_2_0= ruleNavigatingArgCS ) ) ( ( (lv_ownedArguments_3_1= ruleNavigatingCommaArgCS | lv_ownedArguments_3_2= ruleNavigatingSemiArgCS | lv_ownedArguments_3_3= ruleNavigatingBarArgCS ) ) )*
                    {
                    // InternalCompleteOCL.g:5333:4: ( (lv_ownedArguments_2_0= ruleNavigatingArgCS ) )
                    // InternalCompleteOCL.g:5334:5: (lv_ownedArguments_2_0= ruleNavigatingArgCS )
                    {
                    // InternalCompleteOCL.g:5334:5: (lv_ownedArguments_2_0= ruleNavigatingArgCS )
                    // InternalCompleteOCL.g:5335:6: lv_ownedArguments_2_0= ruleNavigatingArgCS
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

                    // InternalCompleteOCL.g:5352:4: ( ( (lv_ownedArguments_3_1= ruleNavigatingCommaArgCS | lv_ownedArguments_3_2= ruleNavigatingSemiArgCS | lv_ownedArguments_3_3= ruleNavigatingBarArgCS ) ) )*
                    loop100:
                    do {
                        int alt100=2;
                        int LA100_0 = input.LA(1);

                        if ( (LA100_0==28||(LA100_0>=96 && LA100_0<=97)) ) {
                            alt100=1;
                        }


                        switch (alt100) {
                    	case 1 :
                    	    // InternalCompleteOCL.g:5353:5: ( (lv_ownedArguments_3_1= ruleNavigatingCommaArgCS | lv_ownedArguments_3_2= ruleNavigatingSemiArgCS | lv_ownedArguments_3_3= ruleNavigatingBarArgCS ) )
                    	    {
                    	    // InternalCompleteOCL.g:5353:5: ( (lv_ownedArguments_3_1= ruleNavigatingCommaArgCS | lv_ownedArguments_3_2= ruleNavigatingSemiArgCS | lv_ownedArguments_3_3= ruleNavigatingBarArgCS ) )
                    	    // InternalCompleteOCL.g:5354:6: (lv_ownedArguments_3_1= ruleNavigatingCommaArgCS | lv_ownedArguments_3_2= ruleNavigatingSemiArgCS | lv_ownedArguments_3_3= ruleNavigatingBarArgCS )
                    	    {
                    	    // InternalCompleteOCL.g:5354:6: (lv_ownedArguments_3_1= ruleNavigatingCommaArgCS | lv_ownedArguments_3_2= ruleNavigatingSemiArgCS | lv_ownedArguments_3_3= ruleNavigatingBarArgCS )
                    	    int alt99=3;
                    	    switch ( input.LA(1) ) {
                    	    case 28:
                    	        {
                    	        alt99=1;
                    	        }
                    	        break;
                    	    case 97:
                    	        {
                    	        alt99=2;
                    	        }
                    	        break;
                    	    case 96:
                    	        {
                    	        alt99=3;
                    	        }
                    	        break;
                    	    default:
                    	        if (state.backtracking>0) {state.failed=true; return current;}
                    	        NoViableAltException nvae =
                    	            new NoViableAltException("", 99, 0, input);

                    	        throw nvae;
                    	    }

                    	    switch (alt99) {
                    	        case 1 :
                    	            // InternalCompleteOCL.g:5355:7: lv_ownedArguments_3_1= ruleNavigatingCommaArgCS
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
                    	            // InternalCompleteOCL.g:5371:7: lv_ownedArguments_3_2= ruleNavigatingSemiArgCS
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
                    	            // InternalCompleteOCL.g:5387:7: lv_ownedArguments_3_3= ruleNavigatingBarArgCS
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
                    	    break loop100;
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
    // InternalCompleteOCL.g:5414:1: entryRuleSquareBracketedClauseCS returns [EObject current=null] : iv_ruleSquareBracketedClauseCS= ruleSquareBracketedClauseCS EOF ;
    public final EObject entryRuleSquareBracketedClauseCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSquareBracketedClauseCS = null;


        try {
            // InternalCompleteOCL.g:5414:64: (iv_ruleSquareBracketedClauseCS= ruleSquareBracketedClauseCS EOF )
            // InternalCompleteOCL.g:5415:2: iv_ruleSquareBracketedClauseCS= ruleSquareBracketedClauseCS EOF
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
    // InternalCompleteOCL.g:5421:1: ruleSquareBracketedClauseCS returns [EObject current=null] : (otherlv_0= '[' ( (lv_ownedTerms_1_0= ruleExpCS ) ) (otherlv_2= ',' ( (lv_ownedTerms_3_0= ruleExpCS ) ) )* otherlv_4= ']' ) ;
    public final EObject ruleSquareBracketedClauseCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_ownedTerms_1_0 = null;

        EObject lv_ownedTerms_3_0 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:5427:2: ( (otherlv_0= '[' ( (lv_ownedTerms_1_0= ruleExpCS ) ) (otherlv_2= ',' ( (lv_ownedTerms_3_0= ruleExpCS ) ) )* otherlv_4= ']' ) )
            // InternalCompleteOCL.g:5428:2: (otherlv_0= '[' ( (lv_ownedTerms_1_0= ruleExpCS ) ) (otherlv_2= ',' ( (lv_ownedTerms_3_0= ruleExpCS ) ) )* otherlv_4= ']' )
            {
            // InternalCompleteOCL.g:5428:2: (otherlv_0= '[' ( (lv_ownedTerms_1_0= ruleExpCS ) ) (otherlv_2= ',' ( (lv_ownedTerms_3_0= ruleExpCS ) ) )* otherlv_4= ']' )
            // InternalCompleteOCL.g:5429:3: otherlv_0= '[' ( (lv_ownedTerms_1_0= ruleExpCS ) ) (otherlv_2= ',' ( (lv_ownedTerms_3_0= ruleExpCS ) ) )* otherlv_4= ']'
            {
            otherlv_0=(Token)match(input,93,FollowSets000.FOLLOW_40); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getSquareBracketedClauseCSAccess().getLeftSquareBracketKeyword_0());

            }
            // InternalCompleteOCL.g:5433:3: ( (lv_ownedTerms_1_0= ruleExpCS ) )
            // InternalCompleteOCL.g:5434:4: (lv_ownedTerms_1_0= ruleExpCS )
            {
            // InternalCompleteOCL.g:5434:4: (lv_ownedTerms_1_0= ruleExpCS )
            // InternalCompleteOCL.g:5435:5: lv_ownedTerms_1_0= ruleExpCS
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

            // InternalCompleteOCL.g:5452:3: (otherlv_2= ',' ( (lv_ownedTerms_3_0= ruleExpCS ) ) )*
            loop102:
            do {
                int alt102=2;
                int LA102_0 = input.LA(1);

                if ( (LA102_0==28) ) {
                    alt102=1;
                }


                switch (alt102) {
            	case 1 :
            	    // InternalCompleteOCL.g:5453:4: otherlv_2= ',' ( (lv_ownedTerms_3_0= ruleExpCS ) )
            	    {
            	    otherlv_2=(Token)match(input,28,FollowSets000.FOLLOW_40); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      				newLeafNode(otherlv_2, grammarAccess.getSquareBracketedClauseCSAccess().getCommaKeyword_2_0());

            	    }
            	    // InternalCompleteOCL.g:5457:4: ( (lv_ownedTerms_3_0= ruleExpCS ) )
            	    // InternalCompleteOCL.g:5458:5: (lv_ownedTerms_3_0= ruleExpCS )
            	    {
            	    // InternalCompleteOCL.g:5458:5: (lv_ownedTerms_3_0= ruleExpCS )
            	    // InternalCompleteOCL.g:5459:6: lv_ownedTerms_3_0= ruleExpCS
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
            	    break loop102;
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
    // InternalCompleteOCL.g:5485:1: entryRuleNavigatingArgCS returns [EObject current=null] : iv_ruleNavigatingArgCS= ruleNavigatingArgCS EOF ;
    public final EObject entryRuleNavigatingArgCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNavigatingArgCS = null;


        try {
            // InternalCompleteOCL.g:5485:56: (iv_ruleNavigatingArgCS= ruleNavigatingArgCS EOF )
            // InternalCompleteOCL.g:5486:2: iv_ruleNavigatingArgCS= ruleNavigatingArgCS EOF
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
    // InternalCompleteOCL.g:5492:1: ruleNavigatingArgCS returns [EObject current=null] : ( ( ( (lv_ownedNameExpression_0_0= ruleNavigatingArgExpCS ) ) ( ( (otherlv_1= 'with' | otherlv_2= '<-' ) ( (lv_ownedCoIterator_3_0= ruleCoIteratorVariableCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? ) | (otherlv_6= ':' ( (lv_ownedType_7_0= ruleTypeExpCS ) ) ( (otherlv_8= 'with' | otherlv_9= '<-' ) ( (lv_ownedCoIterator_10_0= ruleCoIteratorVariableCS ) ) )? (otherlv_11= '=' ( (lv_ownedInitExpression_12_0= ruleExpCS ) ) )? ) | ( (otherlv_13= ':' ( (lv_ownedType_14_0= ruleTypeExpCS ) ) )? ( (otherlv_15= 'with' | otherlv_16= '<-' ) ( (lv_ownedCoIterator_17_0= ruleCoIteratorVariableCS ) ) )? otherlv_18= 'in' ( (lv_ownedInitExpression_19_0= ruleExpCS ) ) ) )? ) | (otherlv_20= ':' ( (lv_ownedType_21_0= ruleTypeExpCS ) ) ) ) ;
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
            // InternalCompleteOCL.g:5498:2: ( ( ( ( (lv_ownedNameExpression_0_0= ruleNavigatingArgExpCS ) ) ( ( (otherlv_1= 'with' | otherlv_2= '<-' ) ( (lv_ownedCoIterator_3_0= ruleCoIteratorVariableCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? ) | (otherlv_6= ':' ( (lv_ownedType_7_0= ruleTypeExpCS ) ) ( (otherlv_8= 'with' | otherlv_9= '<-' ) ( (lv_ownedCoIterator_10_0= ruleCoIteratorVariableCS ) ) )? (otherlv_11= '=' ( (lv_ownedInitExpression_12_0= ruleExpCS ) ) )? ) | ( (otherlv_13= ':' ( (lv_ownedType_14_0= ruleTypeExpCS ) ) )? ( (otherlv_15= 'with' | otherlv_16= '<-' ) ( (lv_ownedCoIterator_17_0= ruleCoIteratorVariableCS ) ) )? otherlv_18= 'in' ( (lv_ownedInitExpression_19_0= ruleExpCS ) ) ) )? ) | (otherlv_20= ':' ( (lv_ownedType_21_0= ruleTypeExpCS ) ) ) ) )
            // InternalCompleteOCL.g:5499:2: ( ( ( (lv_ownedNameExpression_0_0= ruleNavigatingArgExpCS ) ) ( ( (otherlv_1= 'with' | otherlv_2= '<-' ) ( (lv_ownedCoIterator_3_0= ruleCoIteratorVariableCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? ) | (otherlv_6= ':' ( (lv_ownedType_7_0= ruleTypeExpCS ) ) ( (otherlv_8= 'with' | otherlv_9= '<-' ) ( (lv_ownedCoIterator_10_0= ruleCoIteratorVariableCS ) ) )? (otherlv_11= '=' ( (lv_ownedInitExpression_12_0= ruleExpCS ) ) )? ) | ( (otherlv_13= ':' ( (lv_ownedType_14_0= ruleTypeExpCS ) ) )? ( (otherlv_15= 'with' | otherlv_16= '<-' ) ( (lv_ownedCoIterator_17_0= ruleCoIteratorVariableCS ) ) )? otherlv_18= 'in' ( (lv_ownedInitExpression_19_0= ruleExpCS ) ) ) )? ) | (otherlv_20= ':' ( (lv_ownedType_21_0= ruleTypeExpCS ) ) ) )
            {
            // InternalCompleteOCL.g:5499:2: ( ( ( (lv_ownedNameExpression_0_0= ruleNavigatingArgExpCS ) ) ( ( (otherlv_1= 'with' | otherlv_2= '<-' ) ( (lv_ownedCoIterator_3_0= ruleCoIteratorVariableCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? ) | (otherlv_6= ':' ( (lv_ownedType_7_0= ruleTypeExpCS ) ) ( (otherlv_8= 'with' | otherlv_9= '<-' ) ( (lv_ownedCoIterator_10_0= ruleCoIteratorVariableCS ) ) )? (otherlv_11= '=' ( (lv_ownedInitExpression_12_0= ruleExpCS ) ) )? ) | ( (otherlv_13= ':' ( (lv_ownedType_14_0= ruleTypeExpCS ) ) )? ( (otherlv_15= 'with' | otherlv_16= '<-' ) ( (lv_ownedCoIterator_17_0= ruleCoIteratorVariableCS ) ) )? otherlv_18= 'in' ( (lv_ownedInitExpression_19_0= ruleExpCS ) ) ) )? ) | (otherlv_20= ':' ( (lv_ownedType_21_0= ruleTypeExpCS ) ) ) )
            int alt112=2;
            int LA112_0 = input.LA(1);

            if ( ((LA112_0>=RULE_SIMPLE_ID && LA112_0<=RULE_SINGLE_QUOTED_STRING)||LA112_0==21||(LA112_0>=30 && LA112_0<=32)||(LA112_0>=41 && LA112_0<=55)||(LA112_0>=73 && LA112_0<=74)||(LA112_0>=76 && LA112_0<=80)||LA112_0==85||(LA112_0>=88 && LA112_0<=91)||LA112_0==98||(LA112_0>=103 && LA112_0<=104)) ) {
                alt112=1;
            }
            else if ( (LA112_0==23) ) {
                alt112=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 112, 0, input);

                throw nvae;
            }
            switch (alt112) {
                case 1 :
                    // InternalCompleteOCL.g:5500:3: ( ( (lv_ownedNameExpression_0_0= ruleNavigatingArgExpCS ) ) ( ( (otherlv_1= 'with' | otherlv_2= '<-' ) ( (lv_ownedCoIterator_3_0= ruleCoIteratorVariableCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? ) | (otherlv_6= ':' ( (lv_ownedType_7_0= ruleTypeExpCS ) ) ( (otherlv_8= 'with' | otherlv_9= '<-' ) ( (lv_ownedCoIterator_10_0= ruleCoIteratorVariableCS ) ) )? (otherlv_11= '=' ( (lv_ownedInitExpression_12_0= ruleExpCS ) ) )? ) | ( (otherlv_13= ':' ( (lv_ownedType_14_0= ruleTypeExpCS ) ) )? ( (otherlv_15= 'with' | otherlv_16= '<-' ) ( (lv_ownedCoIterator_17_0= ruleCoIteratorVariableCS ) ) )? otherlv_18= 'in' ( (lv_ownedInitExpression_19_0= ruleExpCS ) ) ) )? )
                    {
                    // InternalCompleteOCL.g:5500:3: ( ( (lv_ownedNameExpression_0_0= ruleNavigatingArgExpCS ) ) ( ( (otherlv_1= 'with' | otherlv_2= '<-' ) ( (lv_ownedCoIterator_3_0= ruleCoIteratorVariableCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? ) | (otherlv_6= ':' ( (lv_ownedType_7_0= ruleTypeExpCS ) ) ( (otherlv_8= 'with' | otherlv_9= '<-' ) ( (lv_ownedCoIterator_10_0= ruleCoIteratorVariableCS ) ) )? (otherlv_11= '=' ( (lv_ownedInitExpression_12_0= ruleExpCS ) ) )? ) | ( (otherlv_13= ':' ( (lv_ownedType_14_0= ruleTypeExpCS ) ) )? ( (otherlv_15= 'with' | otherlv_16= '<-' ) ( (lv_ownedCoIterator_17_0= ruleCoIteratorVariableCS ) ) )? otherlv_18= 'in' ( (lv_ownedInitExpression_19_0= ruleExpCS ) ) ) )? )
                    // InternalCompleteOCL.g:5501:4: ( (lv_ownedNameExpression_0_0= ruleNavigatingArgExpCS ) ) ( ( (otherlv_1= 'with' | otherlv_2= '<-' ) ( (lv_ownedCoIterator_3_0= ruleCoIteratorVariableCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? ) | (otherlv_6= ':' ( (lv_ownedType_7_0= ruleTypeExpCS ) ) ( (otherlv_8= 'with' | otherlv_9= '<-' ) ( (lv_ownedCoIterator_10_0= ruleCoIteratorVariableCS ) ) )? (otherlv_11= '=' ( (lv_ownedInitExpression_12_0= ruleExpCS ) ) )? ) | ( (otherlv_13= ':' ( (lv_ownedType_14_0= ruleTypeExpCS ) ) )? ( (otherlv_15= 'with' | otherlv_16= '<-' ) ( (lv_ownedCoIterator_17_0= ruleCoIteratorVariableCS ) ) )? otherlv_18= 'in' ( (lv_ownedInitExpression_19_0= ruleExpCS ) ) ) )?
                    {
                    // InternalCompleteOCL.g:5501:4: ( (lv_ownedNameExpression_0_0= ruleNavigatingArgExpCS ) )
                    // InternalCompleteOCL.g:5502:5: (lv_ownedNameExpression_0_0= ruleNavigatingArgExpCS )
                    {
                    // InternalCompleteOCL.g:5502:5: (lv_ownedNameExpression_0_0= ruleNavigatingArgExpCS )
                    // InternalCompleteOCL.g:5503:6: lv_ownedNameExpression_0_0= ruleNavigatingArgExpCS
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

                    // InternalCompleteOCL.g:5520:4: ( ( (otherlv_1= 'with' | otherlv_2= '<-' ) ( (lv_ownedCoIterator_3_0= ruleCoIteratorVariableCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? ) | (otherlv_6= ':' ( (lv_ownedType_7_0= ruleTypeExpCS ) ) ( (otherlv_8= 'with' | otherlv_9= '<-' ) ( (lv_ownedCoIterator_10_0= ruleCoIteratorVariableCS ) ) )? (otherlv_11= '=' ( (lv_ownedInitExpression_12_0= ruleExpCS ) ) )? ) | ( (otherlv_13= ':' ( (lv_ownedType_14_0= ruleTypeExpCS ) ) )? ( (otherlv_15= 'with' | otherlv_16= '<-' ) ( (lv_ownedCoIterator_17_0= ruleCoIteratorVariableCS ) ) )? otherlv_18= 'in' ( (lv_ownedInitExpression_19_0= ruleExpCS ) ) ) )?
                    int alt111=4;
                    alt111 = dfa111.predict(input);
                    switch (alt111) {
                        case 1 :
                            // InternalCompleteOCL.g:5521:5: ( (otherlv_1= 'with' | otherlv_2= '<-' ) ( (lv_ownedCoIterator_3_0= ruleCoIteratorVariableCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? )
                            {
                            // InternalCompleteOCL.g:5521:5: ( (otherlv_1= 'with' | otherlv_2= '<-' ) ( (lv_ownedCoIterator_3_0= ruleCoIteratorVariableCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? )
                            // InternalCompleteOCL.g:5522:6: (otherlv_1= 'with' | otherlv_2= '<-' ) ( (lv_ownedCoIterator_3_0= ruleCoIteratorVariableCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )?
                            {
                            // InternalCompleteOCL.g:5522:6: (otherlv_1= 'with' | otherlv_2= '<-' )
                            int alt103=2;
                            int LA103_0 = input.LA(1);

                            if ( (LA103_0==86) ) {
                                alt103=1;
                            }
                            else if ( (LA103_0==87) ) {
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
                                    // InternalCompleteOCL.g:5523:7: otherlv_1= 'with'
                                    {
                                    otherlv_1=(Token)match(input,86,FollowSets000.FOLLOW_15); if (state.failed) return current;
                                    if ( state.backtracking==0 ) {

                                      							newLeafNode(otherlv_1, grammarAccess.getNavigatingArgCSAccess().getWithKeyword_0_1_0_0_0());

                                    }

                                    }
                                    break;
                                case 2 :
                                    // InternalCompleteOCL.g:5528:7: otherlv_2= '<-'
                                    {
                                    otherlv_2=(Token)match(input,87,FollowSets000.FOLLOW_15); if (state.failed) return current;
                                    if ( state.backtracking==0 ) {

                                      							newLeafNode(otherlv_2, grammarAccess.getNavigatingArgCSAccess().getLessThanSignHyphenMinusKeyword_0_1_0_0_1());

                                    }

                                    }
                                    break;

                            }

                            // InternalCompleteOCL.g:5533:6: ( (lv_ownedCoIterator_3_0= ruleCoIteratorVariableCS ) )
                            // InternalCompleteOCL.g:5534:7: (lv_ownedCoIterator_3_0= ruleCoIteratorVariableCS )
                            {
                            // InternalCompleteOCL.g:5534:7: (lv_ownedCoIterator_3_0= ruleCoIteratorVariableCS )
                            // InternalCompleteOCL.g:5535:8: lv_ownedCoIterator_3_0= ruleCoIteratorVariableCS
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

                            // InternalCompleteOCL.g:5552:6: (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )?
                            int alt104=2;
                            int LA104_0 = input.LA(1);

                            if ( (LA104_0==29) ) {
                                alt104=1;
                            }
                            switch (alt104) {
                                case 1 :
                                    // InternalCompleteOCL.g:5553:7: otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) )
                                    {
                                    otherlv_4=(Token)match(input,29,FollowSets000.FOLLOW_40); if (state.failed) return current;
                                    if ( state.backtracking==0 ) {

                                      							newLeafNode(otherlv_4, grammarAccess.getNavigatingArgCSAccess().getEqualsSignKeyword_0_1_0_2_0());

                                    }
                                    // InternalCompleteOCL.g:5557:7: ( (lv_ownedInitExpression_5_0= ruleExpCS ) )
                                    // InternalCompleteOCL.g:5558:8: (lv_ownedInitExpression_5_0= ruleExpCS )
                                    {
                                    // InternalCompleteOCL.g:5558:8: (lv_ownedInitExpression_5_0= ruleExpCS )
                                    // InternalCompleteOCL.g:5559:9: lv_ownedInitExpression_5_0= ruleExpCS
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
                            // InternalCompleteOCL.g:5579:5: (otherlv_6= ':' ( (lv_ownedType_7_0= ruleTypeExpCS ) ) ( (otherlv_8= 'with' | otherlv_9= '<-' ) ( (lv_ownedCoIterator_10_0= ruleCoIteratorVariableCS ) ) )? (otherlv_11= '=' ( (lv_ownedInitExpression_12_0= ruleExpCS ) ) )? )
                            {
                            // InternalCompleteOCL.g:5579:5: (otherlv_6= ':' ( (lv_ownedType_7_0= ruleTypeExpCS ) ) ( (otherlv_8= 'with' | otherlv_9= '<-' ) ( (lv_ownedCoIterator_10_0= ruleCoIteratorVariableCS ) ) )? (otherlv_11= '=' ( (lv_ownedInitExpression_12_0= ruleExpCS ) ) )? )
                            // InternalCompleteOCL.g:5580:6: otherlv_6= ':' ( (lv_ownedType_7_0= ruleTypeExpCS ) ) ( (otherlv_8= 'with' | otherlv_9= '<-' ) ( (lv_ownedCoIterator_10_0= ruleCoIteratorVariableCS ) ) )? (otherlv_11= '=' ( (lv_ownedInitExpression_12_0= ruleExpCS ) ) )?
                            {
                            otherlv_6=(Token)match(input,23,FollowSets000.FOLLOW_21); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              						newLeafNode(otherlv_6, grammarAccess.getNavigatingArgCSAccess().getColonKeyword_0_1_1_0());

                            }
                            // InternalCompleteOCL.g:5584:6: ( (lv_ownedType_7_0= ruleTypeExpCS ) )
                            // InternalCompleteOCL.g:5585:7: (lv_ownedType_7_0= ruleTypeExpCS )
                            {
                            // InternalCompleteOCL.g:5585:7: (lv_ownedType_7_0= ruleTypeExpCS )
                            // InternalCompleteOCL.g:5586:8: lv_ownedType_7_0= ruleTypeExpCS
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

                            // InternalCompleteOCL.g:5603:6: ( (otherlv_8= 'with' | otherlv_9= '<-' ) ( (lv_ownedCoIterator_10_0= ruleCoIteratorVariableCS ) ) )?
                            int alt106=2;
                            int LA106_0 = input.LA(1);

                            if ( ((LA106_0>=86 && LA106_0<=87)) ) {
                                alt106=1;
                            }
                            switch (alt106) {
                                case 1 :
                                    // InternalCompleteOCL.g:5604:7: (otherlv_8= 'with' | otherlv_9= '<-' ) ( (lv_ownedCoIterator_10_0= ruleCoIteratorVariableCS ) )
                                    {
                                    // InternalCompleteOCL.g:5604:7: (otherlv_8= 'with' | otherlv_9= '<-' )
                                    int alt105=2;
                                    int LA105_0 = input.LA(1);

                                    if ( (LA105_0==86) ) {
                                        alt105=1;
                                    }
                                    else if ( (LA105_0==87) ) {
                                        alt105=2;
                                    }
                                    else {
                                        if (state.backtracking>0) {state.failed=true; return current;}
                                        NoViableAltException nvae =
                                            new NoViableAltException("", 105, 0, input);

                                        throw nvae;
                                    }
                                    switch (alt105) {
                                        case 1 :
                                            // InternalCompleteOCL.g:5605:8: otherlv_8= 'with'
                                            {
                                            otherlv_8=(Token)match(input,86,FollowSets000.FOLLOW_15); if (state.failed) return current;
                                            if ( state.backtracking==0 ) {

                                              								newLeafNode(otherlv_8, grammarAccess.getNavigatingArgCSAccess().getWithKeyword_0_1_1_2_0_0());

                                            }

                                            }
                                            break;
                                        case 2 :
                                            // InternalCompleteOCL.g:5610:8: otherlv_9= '<-'
                                            {
                                            otherlv_9=(Token)match(input,87,FollowSets000.FOLLOW_15); if (state.failed) return current;
                                            if ( state.backtracking==0 ) {

                                              								newLeafNode(otherlv_9, grammarAccess.getNavigatingArgCSAccess().getLessThanSignHyphenMinusKeyword_0_1_1_2_0_1());

                                            }

                                            }
                                            break;

                                    }

                                    // InternalCompleteOCL.g:5615:7: ( (lv_ownedCoIterator_10_0= ruleCoIteratorVariableCS ) )
                                    // InternalCompleteOCL.g:5616:8: (lv_ownedCoIterator_10_0= ruleCoIteratorVariableCS )
                                    {
                                    // InternalCompleteOCL.g:5616:8: (lv_ownedCoIterator_10_0= ruleCoIteratorVariableCS )
                                    // InternalCompleteOCL.g:5617:9: lv_ownedCoIterator_10_0= ruleCoIteratorVariableCS
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

                            // InternalCompleteOCL.g:5635:6: (otherlv_11= '=' ( (lv_ownedInitExpression_12_0= ruleExpCS ) ) )?
                            int alt107=2;
                            int LA107_0 = input.LA(1);

                            if ( (LA107_0==29) ) {
                                alt107=1;
                            }
                            switch (alt107) {
                                case 1 :
                                    // InternalCompleteOCL.g:5636:7: otherlv_11= '=' ( (lv_ownedInitExpression_12_0= ruleExpCS ) )
                                    {
                                    otherlv_11=(Token)match(input,29,FollowSets000.FOLLOW_40); if (state.failed) return current;
                                    if ( state.backtracking==0 ) {

                                      							newLeafNode(otherlv_11, grammarAccess.getNavigatingArgCSAccess().getEqualsSignKeyword_0_1_1_3_0());

                                    }
                                    // InternalCompleteOCL.g:5640:7: ( (lv_ownedInitExpression_12_0= ruleExpCS ) )
                                    // InternalCompleteOCL.g:5641:8: (lv_ownedInitExpression_12_0= ruleExpCS )
                                    {
                                    // InternalCompleteOCL.g:5641:8: (lv_ownedInitExpression_12_0= ruleExpCS )
                                    // InternalCompleteOCL.g:5642:9: lv_ownedInitExpression_12_0= ruleExpCS
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
                            // InternalCompleteOCL.g:5662:5: ( (otherlv_13= ':' ( (lv_ownedType_14_0= ruleTypeExpCS ) ) )? ( (otherlv_15= 'with' | otherlv_16= '<-' ) ( (lv_ownedCoIterator_17_0= ruleCoIteratorVariableCS ) ) )? otherlv_18= 'in' ( (lv_ownedInitExpression_19_0= ruleExpCS ) ) )
                            {
                            // InternalCompleteOCL.g:5662:5: ( (otherlv_13= ':' ( (lv_ownedType_14_0= ruleTypeExpCS ) ) )? ( (otherlv_15= 'with' | otherlv_16= '<-' ) ( (lv_ownedCoIterator_17_0= ruleCoIteratorVariableCS ) ) )? otherlv_18= 'in' ( (lv_ownedInitExpression_19_0= ruleExpCS ) ) )
                            // InternalCompleteOCL.g:5663:6: (otherlv_13= ':' ( (lv_ownedType_14_0= ruleTypeExpCS ) ) )? ( (otherlv_15= 'with' | otherlv_16= '<-' ) ( (lv_ownedCoIterator_17_0= ruleCoIteratorVariableCS ) ) )? otherlv_18= 'in' ( (lv_ownedInitExpression_19_0= ruleExpCS ) )
                            {
                            // InternalCompleteOCL.g:5663:6: (otherlv_13= ':' ( (lv_ownedType_14_0= ruleTypeExpCS ) ) )?
                            int alt108=2;
                            int LA108_0 = input.LA(1);

                            if ( (LA108_0==23) ) {
                                alt108=1;
                            }
                            switch (alt108) {
                                case 1 :
                                    // InternalCompleteOCL.g:5664:7: otherlv_13= ':' ( (lv_ownedType_14_0= ruleTypeExpCS ) )
                                    {
                                    otherlv_13=(Token)match(input,23,FollowSets000.FOLLOW_21); if (state.failed) return current;
                                    if ( state.backtracking==0 ) {

                                      							newLeafNode(otherlv_13, grammarAccess.getNavigatingArgCSAccess().getColonKeyword_0_1_2_0_0());

                                    }
                                    // InternalCompleteOCL.g:5668:7: ( (lv_ownedType_14_0= ruleTypeExpCS ) )
                                    // InternalCompleteOCL.g:5669:8: (lv_ownedType_14_0= ruleTypeExpCS )
                                    {
                                    // InternalCompleteOCL.g:5669:8: (lv_ownedType_14_0= ruleTypeExpCS )
                                    // InternalCompleteOCL.g:5670:9: lv_ownedType_14_0= ruleTypeExpCS
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

                            // InternalCompleteOCL.g:5688:6: ( (otherlv_15= 'with' | otherlv_16= '<-' ) ( (lv_ownedCoIterator_17_0= ruleCoIteratorVariableCS ) ) )?
                            int alt110=2;
                            int LA110_0 = input.LA(1);

                            if ( ((LA110_0>=86 && LA110_0<=87)) ) {
                                alt110=1;
                            }
                            switch (alt110) {
                                case 1 :
                                    // InternalCompleteOCL.g:5689:7: (otherlv_15= 'with' | otherlv_16= '<-' ) ( (lv_ownedCoIterator_17_0= ruleCoIteratorVariableCS ) )
                                    {
                                    // InternalCompleteOCL.g:5689:7: (otherlv_15= 'with' | otherlv_16= '<-' )
                                    int alt109=2;
                                    int LA109_0 = input.LA(1);

                                    if ( (LA109_0==86) ) {
                                        alt109=1;
                                    }
                                    else if ( (LA109_0==87) ) {
                                        alt109=2;
                                    }
                                    else {
                                        if (state.backtracking>0) {state.failed=true; return current;}
                                        NoViableAltException nvae =
                                            new NoViableAltException("", 109, 0, input);

                                        throw nvae;
                                    }
                                    switch (alt109) {
                                        case 1 :
                                            // InternalCompleteOCL.g:5690:8: otherlv_15= 'with'
                                            {
                                            otherlv_15=(Token)match(input,86,FollowSets000.FOLLOW_15); if (state.failed) return current;
                                            if ( state.backtracking==0 ) {

                                              								newLeafNode(otherlv_15, grammarAccess.getNavigatingArgCSAccess().getWithKeyword_0_1_2_1_0_0());

                                            }

                                            }
                                            break;
                                        case 2 :
                                            // InternalCompleteOCL.g:5695:8: otherlv_16= '<-'
                                            {
                                            otherlv_16=(Token)match(input,87,FollowSets000.FOLLOW_15); if (state.failed) return current;
                                            if ( state.backtracking==0 ) {

                                              								newLeafNode(otherlv_16, grammarAccess.getNavigatingArgCSAccess().getLessThanSignHyphenMinusKeyword_0_1_2_1_0_1());

                                            }

                                            }
                                            break;

                                    }

                                    // InternalCompleteOCL.g:5700:7: ( (lv_ownedCoIterator_17_0= ruleCoIteratorVariableCS ) )
                                    // InternalCompleteOCL.g:5701:8: (lv_ownedCoIterator_17_0= ruleCoIteratorVariableCS )
                                    {
                                    // InternalCompleteOCL.g:5701:8: (lv_ownedCoIterator_17_0= ruleCoIteratorVariableCS )
                                    // InternalCompleteOCL.g:5702:9: lv_ownedCoIterator_17_0= ruleCoIteratorVariableCS
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

                            otherlv_18=(Token)match(input,95,FollowSets000.FOLLOW_40); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              						newLeafNode(otherlv_18, grammarAccess.getNavigatingArgCSAccess().getInKeyword_0_1_2_2());

                            }
                            // InternalCompleteOCL.g:5724:6: ( (lv_ownedInitExpression_19_0= ruleExpCS ) )
                            // InternalCompleteOCL.g:5725:7: (lv_ownedInitExpression_19_0= ruleExpCS )
                            {
                            // InternalCompleteOCL.g:5725:7: (lv_ownedInitExpression_19_0= ruleExpCS )
                            // InternalCompleteOCL.g:5726:8: lv_ownedInitExpression_19_0= ruleExpCS
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
                    // InternalCompleteOCL.g:5747:3: (otherlv_20= ':' ( (lv_ownedType_21_0= ruleTypeExpCS ) ) )
                    {
                    // InternalCompleteOCL.g:5747:3: (otherlv_20= ':' ( (lv_ownedType_21_0= ruleTypeExpCS ) ) )
                    // InternalCompleteOCL.g:5748:4: otherlv_20= ':' ( (lv_ownedType_21_0= ruleTypeExpCS ) )
                    {
                    otherlv_20=(Token)match(input,23,FollowSets000.FOLLOW_21); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_20, grammarAccess.getNavigatingArgCSAccess().getColonKeyword_1_0());

                    }
                    // InternalCompleteOCL.g:5752:4: ( (lv_ownedType_21_0= ruleTypeExpCS ) )
                    // InternalCompleteOCL.g:5753:5: (lv_ownedType_21_0= ruleTypeExpCS )
                    {
                    // InternalCompleteOCL.g:5753:5: (lv_ownedType_21_0= ruleTypeExpCS )
                    // InternalCompleteOCL.g:5754:6: lv_ownedType_21_0= ruleTypeExpCS
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
    // InternalCompleteOCL.g:5776:1: entryRuleNavigatingBarArgCS returns [EObject current=null] : iv_ruleNavigatingBarArgCS= ruleNavigatingBarArgCS EOF ;
    public final EObject entryRuleNavigatingBarArgCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNavigatingBarArgCS = null;


        try {
            // InternalCompleteOCL.g:5776:59: (iv_ruleNavigatingBarArgCS= ruleNavigatingBarArgCS EOF )
            // InternalCompleteOCL.g:5777:2: iv_ruleNavigatingBarArgCS= ruleNavigatingBarArgCS EOF
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
    // InternalCompleteOCL.g:5783:1: ruleNavigatingBarArgCS returns [EObject current=null] : ( ( (lv_prefix_0_0= '|' ) ) ( (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS ) ) (otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? )? ) ;
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
            // InternalCompleteOCL.g:5789:2: ( ( ( (lv_prefix_0_0= '|' ) ) ( (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS ) ) (otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? )? ) )
            // InternalCompleteOCL.g:5790:2: ( ( (lv_prefix_0_0= '|' ) ) ( (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS ) ) (otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? )? )
            {
            // InternalCompleteOCL.g:5790:2: ( ( (lv_prefix_0_0= '|' ) ) ( (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS ) ) (otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? )? )
            // InternalCompleteOCL.g:5791:3: ( (lv_prefix_0_0= '|' ) ) ( (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS ) ) (otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? )?
            {
            // InternalCompleteOCL.g:5791:3: ( (lv_prefix_0_0= '|' ) )
            // InternalCompleteOCL.g:5792:4: (lv_prefix_0_0= '|' )
            {
            // InternalCompleteOCL.g:5792:4: (lv_prefix_0_0= '|' )
            // InternalCompleteOCL.g:5793:5: lv_prefix_0_0= '|'
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

            // InternalCompleteOCL.g:5805:3: ( (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS ) )
            // InternalCompleteOCL.g:5806:4: (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS )
            {
            // InternalCompleteOCL.g:5806:4: (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS )
            // InternalCompleteOCL.g:5807:5: lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS
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

            // InternalCompleteOCL.g:5824:3: (otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? )?
            int alt114=2;
            int LA114_0 = input.LA(1);

            if ( (LA114_0==23) ) {
                alt114=1;
            }
            switch (alt114) {
                case 1 :
                    // InternalCompleteOCL.g:5825:4: otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )?
                    {
                    otherlv_2=(Token)match(input,23,FollowSets000.FOLLOW_21); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_2, grammarAccess.getNavigatingBarArgCSAccess().getColonKeyword_2_0());

                    }
                    // InternalCompleteOCL.g:5829:4: ( (lv_ownedType_3_0= ruleTypeExpCS ) )
                    // InternalCompleteOCL.g:5830:5: (lv_ownedType_3_0= ruleTypeExpCS )
                    {
                    // InternalCompleteOCL.g:5830:5: (lv_ownedType_3_0= ruleTypeExpCS )
                    // InternalCompleteOCL.g:5831:6: lv_ownedType_3_0= ruleTypeExpCS
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

                    // InternalCompleteOCL.g:5848:4: (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )?
                    int alt113=2;
                    int LA113_0 = input.LA(1);

                    if ( (LA113_0==29) ) {
                        alt113=1;
                    }
                    switch (alt113) {
                        case 1 :
                            // InternalCompleteOCL.g:5849:5: otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) )
                            {
                            otherlv_4=(Token)match(input,29,FollowSets000.FOLLOW_40); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              					newLeafNode(otherlv_4, grammarAccess.getNavigatingBarArgCSAccess().getEqualsSignKeyword_2_2_0());

                            }
                            // InternalCompleteOCL.g:5853:5: ( (lv_ownedInitExpression_5_0= ruleExpCS ) )
                            // InternalCompleteOCL.g:5854:6: (lv_ownedInitExpression_5_0= ruleExpCS )
                            {
                            // InternalCompleteOCL.g:5854:6: (lv_ownedInitExpression_5_0= ruleExpCS )
                            // InternalCompleteOCL.g:5855:7: lv_ownedInitExpression_5_0= ruleExpCS
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
    // InternalCompleteOCL.g:5878:1: entryRuleNavigatingCommaArgCS returns [EObject current=null] : iv_ruleNavigatingCommaArgCS= ruleNavigatingCommaArgCS EOF ;
    public final EObject entryRuleNavigatingCommaArgCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNavigatingCommaArgCS = null;


        try {
            // InternalCompleteOCL.g:5878:61: (iv_ruleNavigatingCommaArgCS= ruleNavigatingCommaArgCS EOF )
            // InternalCompleteOCL.g:5879:2: iv_ruleNavigatingCommaArgCS= ruleNavigatingCommaArgCS EOF
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
    // InternalCompleteOCL.g:5885:1: ruleNavigatingCommaArgCS returns [EObject current=null] : ( ( (lv_prefix_0_0= ',' ) ) ( (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS ) ) ( ( (otherlv_2= 'with' | otherlv_3= '<-' ) ( (lv_ownedCoIterator_4_0= ruleCoIteratorVariableCS ) ) (otherlv_5= '=' ( (lv_ownedInitExpression_6_0= ruleExpCS ) ) )? ) | (otherlv_7= ':' ( (lv_ownedType_8_0= ruleTypeExpCS ) ) ( (otherlv_9= 'with' | otherlv_10= '<-' ) ( (lv_ownedCoIterator_11_0= ruleCoIteratorVariableCS ) ) )? (otherlv_12= '=' ( (lv_ownedInitExpression_13_0= ruleExpCS ) ) )? ) | ( (otherlv_14= ':' ( (lv_ownedType_15_0= ruleTypeExpCS ) ) )? ( (otherlv_16= 'with' | otherlv_17= '<-' ) ( (lv_ownedCoIterator_18_0= ruleCoIteratorVariableCS ) ) )? otherlv_19= 'in' ( (lv_ownedInitExpression_20_0= ruleExpCS ) ) ) )? ) ;
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
            // InternalCompleteOCL.g:5891:2: ( ( ( (lv_prefix_0_0= ',' ) ) ( (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS ) ) ( ( (otherlv_2= 'with' | otherlv_3= '<-' ) ( (lv_ownedCoIterator_4_0= ruleCoIteratorVariableCS ) ) (otherlv_5= '=' ( (lv_ownedInitExpression_6_0= ruleExpCS ) ) )? ) | (otherlv_7= ':' ( (lv_ownedType_8_0= ruleTypeExpCS ) ) ( (otherlv_9= 'with' | otherlv_10= '<-' ) ( (lv_ownedCoIterator_11_0= ruleCoIteratorVariableCS ) ) )? (otherlv_12= '=' ( (lv_ownedInitExpression_13_0= ruleExpCS ) ) )? ) | ( (otherlv_14= ':' ( (lv_ownedType_15_0= ruleTypeExpCS ) ) )? ( (otherlv_16= 'with' | otherlv_17= '<-' ) ( (lv_ownedCoIterator_18_0= ruleCoIteratorVariableCS ) ) )? otherlv_19= 'in' ( (lv_ownedInitExpression_20_0= ruleExpCS ) ) ) )? ) )
            // InternalCompleteOCL.g:5892:2: ( ( (lv_prefix_0_0= ',' ) ) ( (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS ) ) ( ( (otherlv_2= 'with' | otherlv_3= '<-' ) ( (lv_ownedCoIterator_4_0= ruleCoIteratorVariableCS ) ) (otherlv_5= '=' ( (lv_ownedInitExpression_6_0= ruleExpCS ) ) )? ) | (otherlv_7= ':' ( (lv_ownedType_8_0= ruleTypeExpCS ) ) ( (otherlv_9= 'with' | otherlv_10= '<-' ) ( (lv_ownedCoIterator_11_0= ruleCoIteratorVariableCS ) ) )? (otherlv_12= '=' ( (lv_ownedInitExpression_13_0= ruleExpCS ) ) )? ) | ( (otherlv_14= ':' ( (lv_ownedType_15_0= ruleTypeExpCS ) ) )? ( (otherlv_16= 'with' | otherlv_17= '<-' ) ( (lv_ownedCoIterator_18_0= ruleCoIteratorVariableCS ) ) )? otherlv_19= 'in' ( (lv_ownedInitExpression_20_0= ruleExpCS ) ) ) )? )
            {
            // InternalCompleteOCL.g:5892:2: ( ( (lv_prefix_0_0= ',' ) ) ( (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS ) ) ( ( (otherlv_2= 'with' | otherlv_3= '<-' ) ( (lv_ownedCoIterator_4_0= ruleCoIteratorVariableCS ) ) (otherlv_5= '=' ( (lv_ownedInitExpression_6_0= ruleExpCS ) ) )? ) | (otherlv_7= ':' ( (lv_ownedType_8_0= ruleTypeExpCS ) ) ( (otherlv_9= 'with' | otherlv_10= '<-' ) ( (lv_ownedCoIterator_11_0= ruleCoIteratorVariableCS ) ) )? (otherlv_12= '=' ( (lv_ownedInitExpression_13_0= ruleExpCS ) ) )? ) | ( (otherlv_14= ':' ( (lv_ownedType_15_0= ruleTypeExpCS ) ) )? ( (otherlv_16= 'with' | otherlv_17= '<-' ) ( (lv_ownedCoIterator_18_0= ruleCoIteratorVariableCS ) ) )? otherlv_19= 'in' ( (lv_ownedInitExpression_20_0= ruleExpCS ) ) ) )? )
            // InternalCompleteOCL.g:5893:3: ( (lv_prefix_0_0= ',' ) ) ( (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS ) ) ( ( (otherlv_2= 'with' | otherlv_3= '<-' ) ( (lv_ownedCoIterator_4_0= ruleCoIteratorVariableCS ) ) (otherlv_5= '=' ( (lv_ownedInitExpression_6_0= ruleExpCS ) ) )? ) | (otherlv_7= ':' ( (lv_ownedType_8_0= ruleTypeExpCS ) ) ( (otherlv_9= 'with' | otherlv_10= '<-' ) ( (lv_ownedCoIterator_11_0= ruleCoIteratorVariableCS ) ) )? (otherlv_12= '=' ( (lv_ownedInitExpression_13_0= ruleExpCS ) ) )? ) | ( (otherlv_14= ':' ( (lv_ownedType_15_0= ruleTypeExpCS ) ) )? ( (otherlv_16= 'with' | otherlv_17= '<-' ) ( (lv_ownedCoIterator_18_0= ruleCoIteratorVariableCS ) ) )? otherlv_19= 'in' ( (lv_ownedInitExpression_20_0= ruleExpCS ) ) ) )?
            {
            // InternalCompleteOCL.g:5893:3: ( (lv_prefix_0_0= ',' ) )
            // InternalCompleteOCL.g:5894:4: (lv_prefix_0_0= ',' )
            {
            // InternalCompleteOCL.g:5894:4: (lv_prefix_0_0= ',' )
            // InternalCompleteOCL.g:5895:5: lv_prefix_0_0= ','
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

            // InternalCompleteOCL.g:5907:3: ( (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS ) )
            // InternalCompleteOCL.g:5908:4: (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS )
            {
            // InternalCompleteOCL.g:5908:4: (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS )
            // InternalCompleteOCL.g:5909:5: lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS
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

            // InternalCompleteOCL.g:5926:3: ( ( (otherlv_2= 'with' | otherlv_3= '<-' ) ( (lv_ownedCoIterator_4_0= ruleCoIteratorVariableCS ) ) (otherlv_5= '=' ( (lv_ownedInitExpression_6_0= ruleExpCS ) ) )? ) | (otherlv_7= ':' ( (lv_ownedType_8_0= ruleTypeExpCS ) ) ( (otherlv_9= 'with' | otherlv_10= '<-' ) ( (lv_ownedCoIterator_11_0= ruleCoIteratorVariableCS ) ) )? (otherlv_12= '=' ( (lv_ownedInitExpression_13_0= ruleExpCS ) ) )? ) | ( (otherlv_14= ':' ( (lv_ownedType_15_0= ruleTypeExpCS ) ) )? ( (otherlv_16= 'with' | otherlv_17= '<-' ) ( (lv_ownedCoIterator_18_0= ruleCoIteratorVariableCS ) ) )? otherlv_19= 'in' ( (lv_ownedInitExpression_20_0= ruleExpCS ) ) ) )?
            int alt123=4;
            alt123 = dfa123.predict(input);
            switch (alt123) {
                case 1 :
                    // InternalCompleteOCL.g:5927:4: ( (otherlv_2= 'with' | otherlv_3= '<-' ) ( (lv_ownedCoIterator_4_0= ruleCoIteratorVariableCS ) ) (otherlv_5= '=' ( (lv_ownedInitExpression_6_0= ruleExpCS ) ) )? )
                    {
                    // InternalCompleteOCL.g:5927:4: ( (otherlv_2= 'with' | otherlv_3= '<-' ) ( (lv_ownedCoIterator_4_0= ruleCoIteratorVariableCS ) ) (otherlv_5= '=' ( (lv_ownedInitExpression_6_0= ruleExpCS ) ) )? )
                    // InternalCompleteOCL.g:5928:5: (otherlv_2= 'with' | otherlv_3= '<-' ) ( (lv_ownedCoIterator_4_0= ruleCoIteratorVariableCS ) ) (otherlv_5= '=' ( (lv_ownedInitExpression_6_0= ruleExpCS ) ) )?
                    {
                    // InternalCompleteOCL.g:5928:5: (otherlv_2= 'with' | otherlv_3= '<-' )
                    int alt115=2;
                    int LA115_0 = input.LA(1);

                    if ( (LA115_0==86) ) {
                        alt115=1;
                    }
                    else if ( (LA115_0==87) ) {
                        alt115=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return current;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 115, 0, input);

                        throw nvae;
                    }
                    switch (alt115) {
                        case 1 :
                            // InternalCompleteOCL.g:5929:6: otherlv_2= 'with'
                            {
                            otherlv_2=(Token)match(input,86,FollowSets000.FOLLOW_15); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              						newLeafNode(otherlv_2, grammarAccess.getNavigatingCommaArgCSAccess().getWithKeyword_2_0_0_0());

                            }

                            }
                            break;
                        case 2 :
                            // InternalCompleteOCL.g:5934:6: otherlv_3= '<-'
                            {
                            otherlv_3=(Token)match(input,87,FollowSets000.FOLLOW_15); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              						newLeafNode(otherlv_3, grammarAccess.getNavigatingCommaArgCSAccess().getLessThanSignHyphenMinusKeyword_2_0_0_1());

                            }

                            }
                            break;

                    }

                    // InternalCompleteOCL.g:5939:5: ( (lv_ownedCoIterator_4_0= ruleCoIteratorVariableCS ) )
                    // InternalCompleteOCL.g:5940:6: (lv_ownedCoIterator_4_0= ruleCoIteratorVariableCS )
                    {
                    // InternalCompleteOCL.g:5940:6: (lv_ownedCoIterator_4_0= ruleCoIteratorVariableCS )
                    // InternalCompleteOCL.g:5941:7: lv_ownedCoIterator_4_0= ruleCoIteratorVariableCS
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

                    // InternalCompleteOCL.g:5958:5: (otherlv_5= '=' ( (lv_ownedInitExpression_6_0= ruleExpCS ) ) )?
                    int alt116=2;
                    int LA116_0 = input.LA(1);

                    if ( (LA116_0==29) ) {
                        alt116=1;
                    }
                    switch (alt116) {
                        case 1 :
                            // InternalCompleteOCL.g:5959:6: otherlv_5= '=' ( (lv_ownedInitExpression_6_0= ruleExpCS ) )
                            {
                            otherlv_5=(Token)match(input,29,FollowSets000.FOLLOW_40); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              						newLeafNode(otherlv_5, grammarAccess.getNavigatingCommaArgCSAccess().getEqualsSignKeyword_2_0_2_0());

                            }
                            // InternalCompleteOCL.g:5963:6: ( (lv_ownedInitExpression_6_0= ruleExpCS ) )
                            // InternalCompleteOCL.g:5964:7: (lv_ownedInitExpression_6_0= ruleExpCS )
                            {
                            // InternalCompleteOCL.g:5964:7: (lv_ownedInitExpression_6_0= ruleExpCS )
                            // InternalCompleteOCL.g:5965:8: lv_ownedInitExpression_6_0= ruleExpCS
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
                    // InternalCompleteOCL.g:5985:4: (otherlv_7= ':' ( (lv_ownedType_8_0= ruleTypeExpCS ) ) ( (otherlv_9= 'with' | otherlv_10= '<-' ) ( (lv_ownedCoIterator_11_0= ruleCoIteratorVariableCS ) ) )? (otherlv_12= '=' ( (lv_ownedInitExpression_13_0= ruleExpCS ) ) )? )
                    {
                    // InternalCompleteOCL.g:5985:4: (otherlv_7= ':' ( (lv_ownedType_8_0= ruleTypeExpCS ) ) ( (otherlv_9= 'with' | otherlv_10= '<-' ) ( (lv_ownedCoIterator_11_0= ruleCoIteratorVariableCS ) ) )? (otherlv_12= '=' ( (lv_ownedInitExpression_13_0= ruleExpCS ) ) )? )
                    // InternalCompleteOCL.g:5986:5: otherlv_7= ':' ( (lv_ownedType_8_0= ruleTypeExpCS ) ) ( (otherlv_9= 'with' | otherlv_10= '<-' ) ( (lv_ownedCoIterator_11_0= ruleCoIteratorVariableCS ) ) )? (otherlv_12= '=' ( (lv_ownedInitExpression_13_0= ruleExpCS ) ) )?
                    {
                    otherlv_7=(Token)match(input,23,FollowSets000.FOLLOW_21); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					newLeafNode(otherlv_7, grammarAccess.getNavigatingCommaArgCSAccess().getColonKeyword_2_1_0());

                    }
                    // InternalCompleteOCL.g:5990:5: ( (lv_ownedType_8_0= ruleTypeExpCS ) )
                    // InternalCompleteOCL.g:5991:6: (lv_ownedType_8_0= ruleTypeExpCS )
                    {
                    // InternalCompleteOCL.g:5991:6: (lv_ownedType_8_0= ruleTypeExpCS )
                    // InternalCompleteOCL.g:5992:7: lv_ownedType_8_0= ruleTypeExpCS
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

                    // InternalCompleteOCL.g:6009:5: ( (otherlv_9= 'with' | otherlv_10= '<-' ) ( (lv_ownedCoIterator_11_0= ruleCoIteratorVariableCS ) ) )?
                    int alt118=2;
                    int LA118_0 = input.LA(1);

                    if ( ((LA118_0>=86 && LA118_0<=87)) ) {
                        alt118=1;
                    }
                    switch (alt118) {
                        case 1 :
                            // InternalCompleteOCL.g:6010:6: (otherlv_9= 'with' | otherlv_10= '<-' ) ( (lv_ownedCoIterator_11_0= ruleCoIteratorVariableCS ) )
                            {
                            // InternalCompleteOCL.g:6010:6: (otherlv_9= 'with' | otherlv_10= '<-' )
                            int alt117=2;
                            int LA117_0 = input.LA(1);

                            if ( (LA117_0==86) ) {
                                alt117=1;
                            }
                            else if ( (LA117_0==87) ) {
                                alt117=2;
                            }
                            else {
                                if (state.backtracking>0) {state.failed=true; return current;}
                                NoViableAltException nvae =
                                    new NoViableAltException("", 117, 0, input);

                                throw nvae;
                            }
                            switch (alt117) {
                                case 1 :
                                    // InternalCompleteOCL.g:6011:7: otherlv_9= 'with'
                                    {
                                    otherlv_9=(Token)match(input,86,FollowSets000.FOLLOW_15); if (state.failed) return current;
                                    if ( state.backtracking==0 ) {

                                      							newLeafNode(otherlv_9, grammarAccess.getNavigatingCommaArgCSAccess().getWithKeyword_2_1_2_0_0());

                                    }

                                    }
                                    break;
                                case 2 :
                                    // InternalCompleteOCL.g:6016:7: otherlv_10= '<-'
                                    {
                                    otherlv_10=(Token)match(input,87,FollowSets000.FOLLOW_15); if (state.failed) return current;
                                    if ( state.backtracking==0 ) {

                                      							newLeafNode(otherlv_10, grammarAccess.getNavigatingCommaArgCSAccess().getLessThanSignHyphenMinusKeyword_2_1_2_0_1());

                                    }

                                    }
                                    break;

                            }

                            // InternalCompleteOCL.g:6021:6: ( (lv_ownedCoIterator_11_0= ruleCoIteratorVariableCS ) )
                            // InternalCompleteOCL.g:6022:7: (lv_ownedCoIterator_11_0= ruleCoIteratorVariableCS )
                            {
                            // InternalCompleteOCL.g:6022:7: (lv_ownedCoIterator_11_0= ruleCoIteratorVariableCS )
                            // InternalCompleteOCL.g:6023:8: lv_ownedCoIterator_11_0= ruleCoIteratorVariableCS
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

                    // InternalCompleteOCL.g:6041:5: (otherlv_12= '=' ( (lv_ownedInitExpression_13_0= ruleExpCS ) ) )?
                    int alt119=2;
                    int LA119_0 = input.LA(1);

                    if ( (LA119_0==29) ) {
                        alt119=1;
                    }
                    switch (alt119) {
                        case 1 :
                            // InternalCompleteOCL.g:6042:6: otherlv_12= '=' ( (lv_ownedInitExpression_13_0= ruleExpCS ) )
                            {
                            otherlv_12=(Token)match(input,29,FollowSets000.FOLLOW_40); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              						newLeafNode(otherlv_12, grammarAccess.getNavigatingCommaArgCSAccess().getEqualsSignKeyword_2_1_3_0());

                            }
                            // InternalCompleteOCL.g:6046:6: ( (lv_ownedInitExpression_13_0= ruleExpCS ) )
                            // InternalCompleteOCL.g:6047:7: (lv_ownedInitExpression_13_0= ruleExpCS )
                            {
                            // InternalCompleteOCL.g:6047:7: (lv_ownedInitExpression_13_0= ruleExpCS )
                            // InternalCompleteOCL.g:6048:8: lv_ownedInitExpression_13_0= ruleExpCS
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
                    // InternalCompleteOCL.g:6068:4: ( (otherlv_14= ':' ( (lv_ownedType_15_0= ruleTypeExpCS ) ) )? ( (otherlv_16= 'with' | otherlv_17= '<-' ) ( (lv_ownedCoIterator_18_0= ruleCoIteratorVariableCS ) ) )? otherlv_19= 'in' ( (lv_ownedInitExpression_20_0= ruleExpCS ) ) )
                    {
                    // InternalCompleteOCL.g:6068:4: ( (otherlv_14= ':' ( (lv_ownedType_15_0= ruleTypeExpCS ) ) )? ( (otherlv_16= 'with' | otherlv_17= '<-' ) ( (lv_ownedCoIterator_18_0= ruleCoIteratorVariableCS ) ) )? otherlv_19= 'in' ( (lv_ownedInitExpression_20_0= ruleExpCS ) ) )
                    // InternalCompleteOCL.g:6069:5: (otherlv_14= ':' ( (lv_ownedType_15_0= ruleTypeExpCS ) ) )? ( (otherlv_16= 'with' | otherlv_17= '<-' ) ( (lv_ownedCoIterator_18_0= ruleCoIteratorVariableCS ) ) )? otherlv_19= 'in' ( (lv_ownedInitExpression_20_0= ruleExpCS ) )
                    {
                    // InternalCompleteOCL.g:6069:5: (otherlv_14= ':' ( (lv_ownedType_15_0= ruleTypeExpCS ) ) )?
                    int alt120=2;
                    int LA120_0 = input.LA(1);

                    if ( (LA120_0==23) ) {
                        alt120=1;
                    }
                    switch (alt120) {
                        case 1 :
                            // InternalCompleteOCL.g:6070:6: otherlv_14= ':' ( (lv_ownedType_15_0= ruleTypeExpCS ) )
                            {
                            otherlv_14=(Token)match(input,23,FollowSets000.FOLLOW_21); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              						newLeafNode(otherlv_14, grammarAccess.getNavigatingCommaArgCSAccess().getColonKeyword_2_2_0_0());

                            }
                            // InternalCompleteOCL.g:6074:6: ( (lv_ownedType_15_0= ruleTypeExpCS ) )
                            // InternalCompleteOCL.g:6075:7: (lv_ownedType_15_0= ruleTypeExpCS )
                            {
                            // InternalCompleteOCL.g:6075:7: (lv_ownedType_15_0= ruleTypeExpCS )
                            // InternalCompleteOCL.g:6076:8: lv_ownedType_15_0= ruleTypeExpCS
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

                    // InternalCompleteOCL.g:6094:5: ( (otherlv_16= 'with' | otherlv_17= '<-' ) ( (lv_ownedCoIterator_18_0= ruleCoIteratorVariableCS ) ) )?
                    int alt122=2;
                    int LA122_0 = input.LA(1);

                    if ( ((LA122_0>=86 && LA122_0<=87)) ) {
                        alt122=1;
                    }
                    switch (alt122) {
                        case 1 :
                            // InternalCompleteOCL.g:6095:6: (otherlv_16= 'with' | otherlv_17= '<-' ) ( (lv_ownedCoIterator_18_0= ruleCoIteratorVariableCS ) )
                            {
                            // InternalCompleteOCL.g:6095:6: (otherlv_16= 'with' | otherlv_17= '<-' )
                            int alt121=2;
                            int LA121_0 = input.LA(1);

                            if ( (LA121_0==86) ) {
                                alt121=1;
                            }
                            else if ( (LA121_0==87) ) {
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
                                    // InternalCompleteOCL.g:6096:7: otherlv_16= 'with'
                                    {
                                    otherlv_16=(Token)match(input,86,FollowSets000.FOLLOW_15); if (state.failed) return current;
                                    if ( state.backtracking==0 ) {

                                      							newLeafNode(otherlv_16, grammarAccess.getNavigatingCommaArgCSAccess().getWithKeyword_2_2_1_0_0());

                                    }

                                    }
                                    break;
                                case 2 :
                                    // InternalCompleteOCL.g:6101:7: otherlv_17= '<-'
                                    {
                                    otherlv_17=(Token)match(input,87,FollowSets000.FOLLOW_15); if (state.failed) return current;
                                    if ( state.backtracking==0 ) {

                                      							newLeafNode(otherlv_17, grammarAccess.getNavigatingCommaArgCSAccess().getLessThanSignHyphenMinusKeyword_2_2_1_0_1());

                                    }

                                    }
                                    break;

                            }

                            // InternalCompleteOCL.g:6106:6: ( (lv_ownedCoIterator_18_0= ruleCoIteratorVariableCS ) )
                            // InternalCompleteOCL.g:6107:7: (lv_ownedCoIterator_18_0= ruleCoIteratorVariableCS )
                            {
                            // InternalCompleteOCL.g:6107:7: (lv_ownedCoIterator_18_0= ruleCoIteratorVariableCS )
                            // InternalCompleteOCL.g:6108:8: lv_ownedCoIterator_18_0= ruleCoIteratorVariableCS
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

                    otherlv_19=(Token)match(input,95,FollowSets000.FOLLOW_40); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					newLeafNode(otherlv_19, grammarAccess.getNavigatingCommaArgCSAccess().getInKeyword_2_2_2());

                    }
                    // InternalCompleteOCL.g:6130:5: ( (lv_ownedInitExpression_20_0= ruleExpCS ) )
                    // InternalCompleteOCL.g:6131:6: (lv_ownedInitExpression_20_0= ruleExpCS )
                    {
                    // InternalCompleteOCL.g:6131:6: (lv_ownedInitExpression_20_0= ruleExpCS )
                    // InternalCompleteOCL.g:6132:7: lv_ownedInitExpression_20_0= ruleExpCS
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
    // InternalCompleteOCL.g:6155:1: entryRuleNavigatingSemiArgCS returns [EObject current=null] : iv_ruleNavigatingSemiArgCS= ruleNavigatingSemiArgCS EOF ;
    public final EObject entryRuleNavigatingSemiArgCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNavigatingSemiArgCS = null;


        try {
            // InternalCompleteOCL.g:6155:60: (iv_ruleNavigatingSemiArgCS= ruleNavigatingSemiArgCS EOF )
            // InternalCompleteOCL.g:6156:2: iv_ruleNavigatingSemiArgCS= ruleNavigatingSemiArgCS EOF
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
    // InternalCompleteOCL.g:6162:1: ruleNavigatingSemiArgCS returns [EObject current=null] : ( ( (lv_prefix_0_0= ';' ) ) ( (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS ) ) (otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? )? ) ;
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
            // InternalCompleteOCL.g:6168:2: ( ( ( (lv_prefix_0_0= ';' ) ) ( (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS ) ) (otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? )? ) )
            // InternalCompleteOCL.g:6169:2: ( ( (lv_prefix_0_0= ';' ) ) ( (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS ) ) (otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? )? )
            {
            // InternalCompleteOCL.g:6169:2: ( ( (lv_prefix_0_0= ';' ) ) ( (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS ) ) (otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? )? )
            // InternalCompleteOCL.g:6170:3: ( (lv_prefix_0_0= ';' ) ) ( (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS ) ) (otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? )?
            {
            // InternalCompleteOCL.g:6170:3: ( (lv_prefix_0_0= ';' ) )
            // InternalCompleteOCL.g:6171:4: (lv_prefix_0_0= ';' )
            {
            // InternalCompleteOCL.g:6171:4: (lv_prefix_0_0= ';' )
            // InternalCompleteOCL.g:6172:5: lv_prefix_0_0= ';'
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

            // InternalCompleteOCL.g:6184:3: ( (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS ) )
            // InternalCompleteOCL.g:6185:4: (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS )
            {
            // InternalCompleteOCL.g:6185:4: (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS )
            // InternalCompleteOCL.g:6186:5: lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS
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

            // InternalCompleteOCL.g:6203:3: (otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? )?
            int alt125=2;
            int LA125_0 = input.LA(1);

            if ( (LA125_0==23) ) {
                alt125=1;
            }
            switch (alt125) {
                case 1 :
                    // InternalCompleteOCL.g:6204:4: otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )?
                    {
                    otherlv_2=(Token)match(input,23,FollowSets000.FOLLOW_21); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_2, grammarAccess.getNavigatingSemiArgCSAccess().getColonKeyword_2_0());

                    }
                    // InternalCompleteOCL.g:6208:4: ( (lv_ownedType_3_0= ruleTypeExpCS ) )
                    // InternalCompleteOCL.g:6209:5: (lv_ownedType_3_0= ruleTypeExpCS )
                    {
                    // InternalCompleteOCL.g:6209:5: (lv_ownedType_3_0= ruleTypeExpCS )
                    // InternalCompleteOCL.g:6210:6: lv_ownedType_3_0= ruleTypeExpCS
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

                    // InternalCompleteOCL.g:6227:4: (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )?
                    int alt124=2;
                    int LA124_0 = input.LA(1);

                    if ( (LA124_0==29) ) {
                        alt124=1;
                    }
                    switch (alt124) {
                        case 1 :
                            // InternalCompleteOCL.g:6228:5: otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) )
                            {
                            otherlv_4=(Token)match(input,29,FollowSets000.FOLLOW_40); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              					newLeafNode(otherlv_4, grammarAccess.getNavigatingSemiArgCSAccess().getEqualsSignKeyword_2_2_0());

                            }
                            // InternalCompleteOCL.g:6232:5: ( (lv_ownedInitExpression_5_0= ruleExpCS ) )
                            // InternalCompleteOCL.g:6233:6: (lv_ownedInitExpression_5_0= ruleExpCS )
                            {
                            // InternalCompleteOCL.g:6233:6: (lv_ownedInitExpression_5_0= ruleExpCS )
                            // InternalCompleteOCL.g:6234:7: lv_ownedInitExpression_5_0= ruleExpCS
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
    // InternalCompleteOCL.g:6257:1: entryRuleCoIteratorVariableCS returns [EObject current=null] : iv_ruleCoIteratorVariableCS= ruleCoIteratorVariableCS EOF ;
    public final EObject entryRuleCoIteratorVariableCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCoIteratorVariableCS = null;


        try {
            // InternalCompleteOCL.g:6257:61: (iv_ruleCoIteratorVariableCS= ruleCoIteratorVariableCS EOF )
            // InternalCompleteOCL.g:6258:2: iv_ruleCoIteratorVariableCS= ruleCoIteratorVariableCS EOF
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
    // InternalCompleteOCL.g:6264:1: ruleCoIteratorVariableCS returns [EObject current=null] : ( ( (lv_name_0_0= ruleUnrestrictedName ) ) (otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) ) )? ) ;
    public final EObject ruleCoIteratorVariableCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_name_0_0 = null;

        EObject lv_ownedType_2_0 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:6270:2: ( ( ( (lv_name_0_0= ruleUnrestrictedName ) ) (otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) ) )? ) )
            // InternalCompleteOCL.g:6271:2: ( ( (lv_name_0_0= ruleUnrestrictedName ) ) (otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) ) )? )
            {
            // InternalCompleteOCL.g:6271:2: ( ( (lv_name_0_0= ruleUnrestrictedName ) ) (otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) ) )? )
            // InternalCompleteOCL.g:6272:3: ( (lv_name_0_0= ruleUnrestrictedName ) ) (otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) ) )?
            {
            // InternalCompleteOCL.g:6272:3: ( (lv_name_0_0= ruleUnrestrictedName ) )
            // InternalCompleteOCL.g:6273:4: (lv_name_0_0= ruleUnrestrictedName )
            {
            // InternalCompleteOCL.g:6273:4: (lv_name_0_0= ruleUnrestrictedName )
            // InternalCompleteOCL.g:6274:5: lv_name_0_0= ruleUnrestrictedName
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

            // InternalCompleteOCL.g:6291:3: (otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) ) )?
            int alt126=2;
            int LA126_0 = input.LA(1);

            if ( (LA126_0==23) ) {
                alt126=1;
            }
            switch (alt126) {
                case 1 :
                    // InternalCompleteOCL.g:6292:4: otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) )
                    {
                    otherlv_1=(Token)match(input,23,FollowSets000.FOLLOW_21); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_1, grammarAccess.getCoIteratorVariableCSAccess().getColonKeyword_1_0());

                    }
                    // InternalCompleteOCL.g:6296:4: ( (lv_ownedType_2_0= ruleTypeExpCS ) )
                    // InternalCompleteOCL.g:6297:5: (lv_ownedType_2_0= ruleTypeExpCS )
                    {
                    // InternalCompleteOCL.g:6297:5: (lv_ownedType_2_0= ruleTypeExpCS )
                    // InternalCompleteOCL.g:6298:6: lv_ownedType_2_0= ruleTypeExpCS
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
    // InternalCompleteOCL.g:6320:1: entryRuleIfExpCS returns [EObject current=null] : iv_ruleIfExpCS= ruleIfExpCS EOF ;
    public final EObject entryRuleIfExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIfExpCS = null;


        try {
            // InternalCompleteOCL.g:6320:48: (iv_ruleIfExpCS= ruleIfExpCS EOF )
            // InternalCompleteOCL.g:6321:2: iv_ruleIfExpCS= ruleIfExpCS EOF
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
    // InternalCompleteOCL.g:6327:1: ruleIfExpCS returns [EObject current=null] : (otherlv_0= 'if' ( ( (lv_ownedCondition_1_1= ruleExpCS | lv_ownedCondition_1_2= rulePatternExpCS ) ) ) otherlv_2= 'then' ( (lv_ownedThenExpression_3_0= ruleExpCS ) ) ( (lv_ownedIfThenExpressions_4_0= ruleElseIfThenExpCS ) )* otherlv_5= 'else' ( (lv_ownedElseExpression_6_0= ruleExpCS ) ) otherlv_7= 'endif' ) ;
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
            // InternalCompleteOCL.g:6333:2: ( (otherlv_0= 'if' ( ( (lv_ownedCondition_1_1= ruleExpCS | lv_ownedCondition_1_2= rulePatternExpCS ) ) ) otherlv_2= 'then' ( (lv_ownedThenExpression_3_0= ruleExpCS ) ) ( (lv_ownedIfThenExpressions_4_0= ruleElseIfThenExpCS ) )* otherlv_5= 'else' ( (lv_ownedElseExpression_6_0= ruleExpCS ) ) otherlv_7= 'endif' ) )
            // InternalCompleteOCL.g:6334:2: (otherlv_0= 'if' ( ( (lv_ownedCondition_1_1= ruleExpCS | lv_ownedCondition_1_2= rulePatternExpCS ) ) ) otherlv_2= 'then' ( (lv_ownedThenExpression_3_0= ruleExpCS ) ) ( (lv_ownedIfThenExpressions_4_0= ruleElseIfThenExpCS ) )* otherlv_5= 'else' ( (lv_ownedElseExpression_6_0= ruleExpCS ) ) otherlv_7= 'endif' )
            {
            // InternalCompleteOCL.g:6334:2: (otherlv_0= 'if' ( ( (lv_ownedCondition_1_1= ruleExpCS | lv_ownedCondition_1_2= rulePatternExpCS ) ) ) otherlv_2= 'then' ( (lv_ownedThenExpression_3_0= ruleExpCS ) ) ( (lv_ownedIfThenExpressions_4_0= ruleElseIfThenExpCS ) )* otherlv_5= 'else' ( (lv_ownedElseExpression_6_0= ruleExpCS ) ) otherlv_7= 'endif' )
            // InternalCompleteOCL.g:6335:3: otherlv_0= 'if' ( ( (lv_ownedCondition_1_1= ruleExpCS | lv_ownedCondition_1_2= rulePatternExpCS ) ) ) otherlv_2= 'then' ( (lv_ownedThenExpression_3_0= ruleExpCS ) ) ( (lv_ownedIfThenExpressions_4_0= ruleElseIfThenExpCS ) )* otherlv_5= 'else' ( (lv_ownedElseExpression_6_0= ruleExpCS ) ) otherlv_7= 'endif'
            {
            otherlv_0=(Token)match(input,98,FollowSets000.FOLLOW_38); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getIfExpCSAccess().getIfKeyword_0());

            }
            // InternalCompleteOCL.g:6339:3: ( ( (lv_ownedCondition_1_1= ruleExpCS | lv_ownedCondition_1_2= rulePatternExpCS ) ) )
            // InternalCompleteOCL.g:6340:4: ( (lv_ownedCondition_1_1= ruleExpCS | lv_ownedCondition_1_2= rulePatternExpCS ) )
            {
            // InternalCompleteOCL.g:6340:4: ( (lv_ownedCondition_1_1= ruleExpCS | lv_ownedCondition_1_2= rulePatternExpCS ) )
            // InternalCompleteOCL.g:6341:5: (lv_ownedCondition_1_1= ruleExpCS | lv_ownedCondition_1_2= rulePatternExpCS )
            {
            // InternalCompleteOCL.g:6341:5: (lv_ownedCondition_1_1= ruleExpCS | lv_ownedCondition_1_2= rulePatternExpCS )
            int alt127=2;
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
                alt127=1;
                }
                break;
            case RULE_SIMPLE_ID:
                {
                int LA127_2 = input.LA(2);

                if ( ((LA127_2>=17 && LA127_2<=18)||LA127_2==21||LA127_2==29||(LA127_2>=39 && LA127_2<=40)||LA127_2==52||(LA127_2>=55 && LA127_2<=72)||LA127_2==75||LA127_2==81||(LA127_2>=92 && LA127_2<=93)||LA127_2==99) ) {
                    alt127=1;
                }
                else if ( (LA127_2==23) ) {
                    alt127=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return current;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 127, 2, input);

                    throw nvae;
                }
                }
                break;
            case RULE_ESCAPED_ID:
                {
                int LA127_3 = input.LA(2);

                if ( ((LA127_3>=17 && LA127_3<=18)||LA127_3==21||LA127_3==29||(LA127_3>=39 && LA127_3<=40)||LA127_3==52||(LA127_3>=55 && LA127_3<=72)||LA127_3==75||LA127_3==81||(LA127_3>=92 && LA127_3<=93)||LA127_3==99) ) {
                    alt127=1;
                }
                else if ( (LA127_3==23) ) {
                    alt127=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return current;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 127, 3, input);

                    throw nvae;
                }
                }
                break;
            case 30:
                {
                int LA127_4 = input.LA(2);

                if ( ((LA127_4>=17 && LA127_4<=18)||LA127_4==21||LA127_4==29||(LA127_4>=39 && LA127_4<=40)||LA127_4==52||(LA127_4>=55 && LA127_4<=72)||LA127_4==75||LA127_4==81||(LA127_4>=92 && LA127_4<=93)||LA127_4==99) ) {
                    alt127=1;
                }
                else if ( (LA127_4==23) ) {
                    alt127=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return current;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 127, 4, input);

                    throw nvae;
                }
                }
                break;
            case 31:
                {
                int LA127_5 = input.LA(2);

                if ( (LA127_5==23) ) {
                    alt127=2;
                }
                else if ( ((LA127_5>=17 && LA127_5<=18)||LA127_5==21||LA127_5==29||(LA127_5>=39 && LA127_5<=40)||LA127_5==52||(LA127_5>=55 && LA127_5<=72)||LA127_5==75||LA127_5==81||(LA127_5>=92 && LA127_5<=93)||LA127_5==99) ) {
                    alt127=1;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return current;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 127, 5, input);

                    throw nvae;
                }
                }
                break;
            case 32:
                {
                int LA127_6 = input.LA(2);

                if ( ((LA127_6>=17 && LA127_6<=18)||LA127_6==21||LA127_6==29||(LA127_6>=39 && LA127_6<=40)||LA127_6==52||(LA127_6>=55 && LA127_6<=72)||LA127_6==75||LA127_6==81||(LA127_6>=92 && LA127_6<=93)||LA127_6==99) ) {
                    alt127=1;
                }
                else if ( (LA127_6==23) ) {
                    alt127=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return current;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 127, 6, input);

                    throw nvae;
                }
                }
                break;
            case 23:
                {
                alt127=2;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 127, 0, input);

                throw nvae;
            }

            switch (alt127) {
                case 1 :
                    // InternalCompleteOCL.g:6342:6: lv_ownedCondition_1_1= ruleExpCS
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
                    // InternalCompleteOCL.g:6358:6: lv_ownedCondition_1_2= rulePatternExpCS
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

            otherlv_2=(Token)match(input,99,FollowSets000.FOLLOW_40); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_2, grammarAccess.getIfExpCSAccess().getThenKeyword_2());

            }
            // InternalCompleteOCL.g:6380:3: ( (lv_ownedThenExpression_3_0= ruleExpCS ) )
            // InternalCompleteOCL.g:6381:4: (lv_ownedThenExpression_3_0= ruleExpCS )
            {
            // InternalCompleteOCL.g:6381:4: (lv_ownedThenExpression_3_0= ruleExpCS )
            // InternalCompleteOCL.g:6382:5: lv_ownedThenExpression_3_0= ruleExpCS
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

            // InternalCompleteOCL.g:6399:3: ( (lv_ownedIfThenExpressions_4_0= ruleElseIfThenExpCS ) )*
            loop128:
            do {
                int alt128=2;
                int LA128_0 = input.LA(1);

                if ( (LA128_0==102) ) {
                    alt128=1;
                }


                switch (alt128) {
            	case 1 :
            	    // InternalCompleteOCL.g:6400:4: (lv_ownedIfThenExpressions_4_0= ruleElseIfThenExpCS )
            	    {
            	    // InternalCompleteOCL.g:6400:4: (lv_ownedIfThenExpressions_4_0= ruleElseIfThenExpCS )
            	    // InternalCompleteOCL.g:6401:5: lv_ownedIfThenExpressions_4_0= ruleElseIfThenExpCS
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
            	    break loop128;
                }
            } while (true);

            otherlv_5=(Token)match(input,100,FollowSets000.FOLLOW_40); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_5, grammarAccess.getIfExpCSAccess().getElseKeyword_5());

            }
            // InternalCompleteOCL.g:6422:3: ( (lv_ownedElseExpression_6_0= ruleExpCS ) )
            // InternalCompleteOCL.g:6423:4: (lv_ownedElseExpression_6_0= ruleExpCS )
            {
            // InternalCompleteOCL.g:6423:4: (lv_ownedElseExpression_6_0= ruleExpCS )
            // InternalCompleteOCL.g:6424:5: lv_ownedElseExpression_6_0= ruleExpCS
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
    // InternalCompleteOCL.g:6449:1: entryRuleElseIfThenExpCS returns [EObject current=null] : iv_ruleElseIfThenExpCS= ruleElseIfThenExpCS EOF ;
    public final EObject entryRuleElseIfThenExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleElseIfThenExpCS = null;


        try {
            // InternalCompleteOCL.g:6449:56: (iv_ruleElseIfThenExpCS= ruleElseIfThenExpCS EOF )
            // InternalCompleteOCL.g:6450:2: iv_ruleElseIfThenExpCS= ruleElseIfThenExpCS EOF
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
    // InternalCompleteOCL.g:6456:1: ruleElseIfThenExpCS returns [EObject current=null] : (otherlv_0= 'elseif' ( (lv_ownedCondition_1_0= ruleExpCS ) ) otherlv_2= 'then' ( (lv_ownedThenExpression_3_0= ruleExpCS ) ) ) ;
    public final EObject ruleElseIfThenExpCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        EObject lv_ownedCondition_1_0 = null;

        EObject lv_ownedThenExpression_3_0 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:6462:2: ( (otherlv_0= 'elseif' ( (lv_ownedCondition_1_0= ruleExpCS ) ) otherlv_2= 'then' ( (lv_ownedThenExpression_3_0= ruleExpCS ) ) ) )
            // InternalCompleteOCL.g:6463:2: (otherlv_0= 'elseif' ( (lv_ownedCondition_1_0= ruleExpCS ) ) otherlv_2= 'then' ( (lv_ownedThenExpression_3_0= ruleExpCS ) ) )
            {
            // InternalCompleteOCL.g:6463:2: (otherlv_0= 'elseif' ( (lv_ownedCondition_1_0= ruleExpCS ) ) otherlv_2= 'then' ( (lv_ownedThenExpression_3_0= ruleExpCS ) ) )
            // InternalCompleteOCL.g:6464:3: otherlv_0= 'elseif' ( (lv_ownedCondition_1_0= ruleExpCS ) ) otherlv_2= 'then' ( (lv_ownedThenExpression_3_0= ruleExpCS ) )
            {
            otherlv_0=(Token)match(input,102,FollowSets000.FOLLOW_40); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getElseIfThenExpCSAccess().getElseifKeyword_0());

            }
            // InternalCompleteOCL.g:6468:3: ( (lv_ownedCondition_1_0= ruleExpCS ) )
            // InternalCompleteOCL.g:6469:4: (lv_ownedCondition_1_0= ruleExpCS )
            {
            // InternalCompleteOCL.g:6469:4: (lv_ownedCondition_1_0= ruleExpCS )
            // InternalCompleteOCL.g:6470:5: lv_ownedCondition_1_0= ruleExpCS
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

            otherlv_2=(Token)match(input,99,FollowSets000.FOLLOW_40); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_2, grammarAccess.getElseIfThenExpCSAccess().getThenKeyword_2());

            }
            // InternalCompleteOCL.g:6491:3: ( (lv_ownedThenExpression_3_0= ruleExpCS ) )
            // InternalCompleteOCL.g:6492:4: (lv_ownedThenExpression_3_0= ruleExpCS )
            {
            // InternalCompleteOCL.g:6492:4: (lv_ownedThenExpression_3_0= ruleExpCS )
            // InternalCompleteOCL.g:6493:5: lv_ownedThenExpression_3_0= ruleExpCS
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
    // InternalCompleteOCL.g:6514:1: entryRuleLetExpCS returns [EObject current=null] : iv_ruleLetExpCS= ruleLetExpCS EOF ;
    public final EObject entryRuleLetExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLetExpCS = null;


        try {
            // InternalCompleteOCL.g:6514:49: (iv_ruleLetExpCS= ruleLetExpCS EOF )
            // InternalCompleteOCL.g:6515:2: iv_ruleLetExpCS= ruleLetExpCS EOF
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
    // InternalCompleteOCL.g:6521:1: ruleLetExpCS returns [EObject current=null] : (otherlv_0= 'let' ( (lv_ownedVariables_1_0= ruleLetVariableCS ) ) (otherlv_2= ',' ( (lv_ownedVariables_3_0= ruleLetVariableCS ) ) )* otherlv_4= 'in' ( (lv_ownedInExpression_5_0= ruleExpCS ) ) ) ;
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
            // InternalCompleteOCL.g:6527:2: ( (otherlv_0= 'let' ( (lv_ownedVariables_1_0= ruleLetVariableCS ) ) (otherlv_2= ',' ( (lv_ownedVariables_3_0= ruleLetVariableCS ) ) )* otherlv_4= 'in' ( (lv_ownedInExpression_5_0= ruleExpCS ) ) ) )
            // InternalCompleteOCL.g:6528:2: (otherlv_0= 'let' ( (lv_ownedVariables_1_0= ruleLetVariableCS ) ) (otherlv_2= ',' ( (lv_ownedVariables_3_0= ruleLetVariableCS ) ) )* otherlv_4= 'in' ( (lv_ownedInExpression_5_0= ruleExpCS ) ) )
            {
            // InternalCompleteOCL.g:6528:2: (otherlv_0= 'let' ( (lv_ownedVariables_1_0= ruleLetVariableCS ) ) (otherlv_2= ',' ( (lv_ownedVariables_3_0= ruleLetVariableCS ) ) )* otherlv_4= 'in' ( (lv_ownedInExpression_5_0= ruleExpCS ) ) )
            // InternalCompleteOCL.g:6529:3: otherlv_0= 'let' ( (lv_ownedVariables_1_0= ruleLetVariableCS ) ) (otherlv_2= ',' ( (lv_ownedVariables_3_0= ruleLetVariableCS ) ) )* otherlv_4= 'in' ( (lv_ownedInExpression_5_0= ruleExpCS ) )
            {
            otherlv_0=(Token)match(input,103,FollowSets000.FOLLOW_15); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getLetExpCSAccess().getLetKeyword_0());

            }
            // InternalCompleteOCL.g:6533:3: ( (lv_ownedVariables_1_0= ruleLetVariableCS ) )
            // InternalCompleteOCL.g:6534:4: (lv_ownedVariables_1_0= ruleLetVariableCS )
            {
            // InternalCompleteOCL.g:6534:4: (lv_ownedVariables_1_0= ruleLetVariableCS )
            // InternalCompleteOCL.g:6535:5: lv_ownedVariables_1_0= ruleLetVariableCS
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

            // InternalCompleteOCL.g:6552:3: (otherlv_2= ',' ( (lv_ownedVariables_3_0= ruleLetVariableCS ) ) )*
            loop129:
            do {
                int alt129=2;
                int LA129_0 = input.LA(1);

                if ( (LA129_0==28) ) {
                    alt129=1;
                }


                switch (alt129) {
            	case 1 :
            	    // InternalCompleteOCL.g:6553:4: otherlv_2= ',' ( (lv_ownedVariables_3_0= ruleLetVariableCS ) )
            	    {
            	    otherlv_2=(Token)match(input,28,FollowSets000.FOLLOW_15); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      				newLeafNode(otherlv_2, grammarAccess.getLetExpCSAccess().getCommaKeyword_2_0());

            	    }
            	    // InternalCompleteOCL.g:6557:4: ( (lv_ownedVariables_3_0= ruleLetVariableCS ) )
            	    // InternalCompleteOCL.g:6558:5: (lv_ownedVariables_3_0= ruleLetVariableCS )
            	    {
            	    // InternalCompleteOCL.g:6558:5: (lv_ownedVariables_3_0= ruleLetVariableCS )
            	    // InternalCompleteOCL.g:6559:6: lv_ownedVariables_3_0= ruleLetVariableCS
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
            	    break loop129;
                }
            } while (true);

            otherlv_4=(Token)match(input,95,FollowSets000.FOLLOW_40); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_4, grammarAccess.getLetExpCSAccess().getInKeyword_3());

            }
            // InternalCompleteOCL.g:6581:3: ( (lv_ownedInExpression_5_0= ruleExpCS ) )
            // InternalCompleteOCL.g:6582:4: (lv_ownedInExpression_5_0= ruleExpCS )
            {
            // InternalCompleteOCL.g:6582:4: (lv_ownedInExpression_5_0= ruleExpCS )
            // InternalCompleteOCL.g:6583:5: lv_ownedInExpression_5_0= ruleExpCS
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
    // InternalCompleteOCL.g:6604:1: entryRuleLetVariableCS returns [EObject current=null] : iv_ruleLetVariableCS= ruleLetVariableCS EOF ;
    public final EObject entryRuleLetVariableCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLetVariableCS = null;


        try {
            // InternalCompleteOCL.g:6604:54: (iv_ruleLetVariableCS= ruleLetVariableCS EOF )
            // InternalCompleteOCL.g:6605:2: iv_ruleLetVariableCS= ruleLetVariableCS EOF
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
    // InternalCompleteOCL.g:6611:1: ruleLetVariableCS returns [EObject current=null] : ( ( (lv_name_0_0= ruleUnrestrictedName ) ) ( (lv_ownedRoundBracketedClause_1_0= ruleRoundBracketedClauseCS ) )? (otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) )? otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) ) ;
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
            // InternalCompleteOCL.g:6617:2: ( ( ( (lv_name_0_0= ruleUnrestrictedName ) ) ( (lv_ownedRoundBracketedClause_1_0= ruleRoundBracketedClauseCS ) )? (otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) )? otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) ) )
            // InternalCompleteOCL.g:6618:2: ( ( (lv_name_0_0= ruleUnrestrictedName ) ) ( (lv_ownedRoundBracketedClause_1_0= ruleRoundBracketedClauseCS ) )? (otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) )? otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )
            {
            // InternalCompleteOCL.g:6618:2: ( ( (lv_name_0_0= ruleUnrestrictedName ) ) ( (lv_ownedRoundBracketedClause_1_0= ruleRoundBracketedClauseCS ) )? (otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) )? otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )
            // InternalCompleteOCL.g:6619:3: ( (lv_name_0_0= ruleUnrestrictedName ) ) ( (lv_ownedRoundBracketedClause_1_0= ruleRoundBracketedClauseCS ) )? (otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) )? otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) )
            {
            // InternalCompleteOCL.g:6619:3: ( (lv_name_0_0= ruleUnrestrictedName ) )
            // InternalCompleteOCL.g:6620:4: (lv_name_0_0= ruleUnrestrictedName )
            {
            // InternalCompleteOCL.g:6620:4: (lv_name_0_0= ruleUnrestrictedName )
            // InternalCompleteOCL.g:6621:5: lv_name_0_0= ruleUnrestrictedName
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

            // InternalCompleteOCL.g:6638:3: ( (lv_ownedRoundBracketedClause_1_0= ruleRoundBracketedClauseCS ) )?
            int alt130=2;
            int LA130_0 = input.LA(1);

            if ( (LA130_0==21) ) {
                alt130=1;
            }
            switch (alt130) {
                case 1 :
                    // InternalCompleteOCL.g:6639:4: (lv_ownedRoundBracketedClause_1_0= ruleRoundBracketedClauseCS )
                    {
                    // InternalCompleteOCL.g:6639:4: (lv_ownedRoundBracketedClause_1_0= ruleRoundBracketedClauseCS )
                    // InternalCompleteOCL.g:6640:5: lv_ownedRoundBracketedClause_1_0= ruleRoundBracketedClauseCS
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

            // InternalCompleteOCL.g:6657:3: (otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) )?
            int alt131=2;
            int LA131_0 = input.LA(1);

            if ( (LA131_0==23) ) {
                alt131=1;
            }
            switch (alt131) {
                case 1 :
                    // InternalCompleteOCL.g:6658:4: otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) )
                    {
                    otherlv_2=(Token)match(input,23,FollowSets000.FOLLOW_21); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_2, grammarAccess.getLetVariableCSAccess().getColonKeyword_2_0());

                    }
                    // InternalCompleteOCL.g:6662:4: ( (lv_ownedType_3_0= ruleTypeExpCS ) )
                    // InternalCompleteOCL.g:6663:5: (lv_ownedType_3_0= ruleTypeExpCS )
                    {
                    // InternalCompleteOCL.g:6663:5: (lv_ownedType_3_0= ruleTypeExpCS )
                    // InternalCompleteOCL.g:6664:6: lv_ownedType_3_0= ruleTypeExpCS
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

            otherlv_4=(Token)match(input,29,FollowSets000.FOLLOW_40); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_4, grammarAccess.getLetVariableCSAccess().getEqualsSignKeyword_3());

            }
            // InternalCompleteOCL.g:6686:3: ( (lv_ownedInitExpression_5_0= ruleExpCS ) )
            // InternalCompleteOCL.g:6687:4: (lv_ownedInitExpression_5_0= ruleExpCS )
            {
            // InternalCompleteOCL.g:6687:4: (lv_ownedInitExpression_5_0= ruleExpCS )
            // InternalCompleteOCL.g:6688:5: lv_ownedInitExpression_5_0= ruleExpCS
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
    // InternalCompleteOCL.g:6709:1: entryRuleNestedExpCS returns [EObject current=null] : iv_ruleNestedExpCS= ruleNestedExpCS EOF ;
    public final EObject entryRuleNestedExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNestedExpCS = null;


        try {
            // InternalCompleteOCL.g:6709:52: (iv_ruleNestedExpCS= ruleNestedExpCS EOF )
            // InternalCompleteOCL.g:6710:2: iv_ruleNestedExpCS= ruleNestedExpCS EOF
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
    // InternalCompleteOCL.g:6716:1: ruleNestedExpCS returns [EObject current=null] : (otherlv_0= '(' ( (lv_ownedExpression_1_0= ruleExpCS ) ) otherlv_2= ')' ) ;
    public final EObject ruleNestedExpCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        EObject lv_ownedExpression_1_0 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:6722:2: ( (otherlv_0= '(' ( (lv_ownedExpression_1_0= ruleExpCS ) ) otherlv_2= ')' ) )
            // InternalCompleteOCL.g:6723:2: (otherlv_0= '(' ( (lv_ownedExpression_1_0= ruleExpCS ) ) otherlv_2= ')' )
            {
            // InternalCompleteOCL.g:6723:2: (otherlv_0= '(' ( (lv_ownedExpression_1_0= ruleExpCS ) ) otherlv_2= ')' )
            // InternalCompleteOCL.g:6724:3: otherlv_0= '(' ( (lv_ownedExpression_1_0= ruleExpCS ) ) otherlv_2= ')'
            {
            otherlv_0=(Token)match(input,21,FollowSets000.FOLLOW_40); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getNestedExpCSAccess().getLeftParenthesisKeyword_0());

            }
            // InternalCompleteOCL.g:6728:3: ( (lv_ownedExpression_1_0= ruleExpCS ) )
            // InternalCompleteOCL.g:6729:4: (lv_ownedExpression_1_0= ruleExpCS )
            {
            // InternalCompleteOCL.g:6729:4: (lv_ownedExpression_1_0= ruleExpCS )
            // InternalCompleteOCL.g:6730:5: lv_ownedExpression_1_0= ruleExpCS
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
    // InternalCompleteOCL.g:6755:1: entryRuleSelfExpCS returns [EObject current=null] : iv_ruleSelfExpCS= ruleSelfExpCS EOF ;
    public final EObject entryRuleSelfExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSelfExpCS = null;


        try {
            // InternalCompleteOCL.g:6755:50: (iv_ruleSelfExpCS= ruleSelfExpCS EOF )
            // InternalCompleteOCL.g:6756:2: iv_ruleSelfExpCS= ruleSelfExpCS EOF
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
    // InternalCompleteOCL.g:6762:1: ruleSelfExpCS returns [EObject current=null] : ( () otherlv_1= 'self' ) ;
    public final EObject ruleSelfExpCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;


        	enterRule();

        try {
            // InternalCompleteOCL.g:6768:2: ( ( () otherlv_1= 'self' ) )
            // InternalCompleteOCL.g:6769:2: ( () otherlv_1= 'self' )
            {
            // InternalCompleteOCL.g:6769:2: ( () otherlv_1= 'self' )
            // InternalCompleteOCL.g:6770:3: () otherlv_1= 'self'
            {
            // InternalCompleteOCL.g:6770:3: ()
            // InternalCompleteOCL.g:6771:4:
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
    // InternalCompleteOCL.g:6788:1: entryRuleMultiplicityBoundsCS returns [EObject current=null] : iv_ruleMultiplicityBoundsCS= ruleMultiplicityBoundsCS EOF ;
    public final EObject entryRuleMultiplicityBoundsCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMultiplicityBoundsCS = null;


        try {
            // InternalCompleteOCL.g:6788:61: (iv_ruleMultiplicityBoundsCS= ruleMultiplicityBoundsCS EOF )
            // InternalCompleteOCL.g:6789:2: iv_ruleMultiplicityBoundsCS= ruleMultiplicityBoundsCS EOF
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
    // InternalCompleteOCL.g:6795:1: ruleMultiplicityBoundsCS returns [EObject current=null] : ( ( (lv_lowerBound_0_0= ruleLOWER ) ) (otherlv_1= '..' ( (lv_upperBound_2_0= ruleUPPER ) ) )? ) ;
    public final EObject ruleMultiplicityBoundsCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_lowerBound_0_0 = null;

        AntlrDatatypeRuleToken lv_upperBound_2_0 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:6801:2: ( ( ( (lv_lowerBound_0_0= ruleLOWER ) ) (otherlv_1= '..' ( (lv_upperBound_2_0= ruleUPPER ) ) )? ) )
            // InternalCompleteOCL.g:6802:2: ( ( (lv_lowerBound_0_0= ruleLOWER ) ) (otherlv_1= '..' ( (lv_upperBound_2_0= ruleUPPER ) ) )? )
            {
            // InternalCompleteOCL.g:6802:2: ( ( (lv_lowerBound_0_0= ruleLOWER ) ) (otherlv_1= '..' ( (lv_upperBound_2_0= ruleUPPER ) ) )? )
            // InternalCompleteOCL.g:6803:3: ( (lv_lowerBound_0_0= ruleLOWER ) ) (otherlv_1= '..' ( (lv_upperBound_2_0= ruleUPPER ) ) )?
            {
            // InternalCompleteOCL.g:6803:3: ( (lv_lowerBound_0_0= ruleLOWER ) )
            // InternalCompleteOCL.g:6804:4: (lv_lowerBound_0_0= ruleLOWER )
            {
            // InternalCompleteOCL.g:6804:4: (lv_lowerBound_0_0= ruleLOWER )
            // InternalCompleteOCL.g:6805:5: lv_lowerBound_0_0= ruleLOWER
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

            // InternalCompleteOCL.g:6822:3: (otherlv_1= '..' ( (lv_upperBound_2_0= ruleUPPER ) ) )?
            int alt132=2;
            int LA132_0 = input.LA(1);

            if ( (LA132_0==83) ) {
                alt132=1;
            }
            switch (alt132) {
                case 1 :
                    // InternalCompleteOCL.g:6823:4: otherlv_1= '..' ( (lv_upperBound_2_0= ruleUPPER ) )
                    {
                    otherlv_1=(Token)match(input,83,FollowSets000.FOLLOW_71); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_1, grammarAccess.getMultiplicityBoundsCSAccess().getFullStopFullStopKeyword_1_0());

                    }
                    // InternalCompleteOCL.g:6827:4: ( (lv_upperBound_2_0= ruleUPPER ) )
                    // InternalCompleteOCL.g:6828:5: (lv_upperBound_2_0= ruleUPPER )
                    {
                    // InternalCompleteOCL.g:6828:5: (lv_upperBound_2_0= ruleUPPER )
                    // InternalCompleteOCL.g:6829:6: lv_upperBound_2_0= ruleUPPER
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
    // InternalCompleteOCL.g:6851:1: entryRuleMultiplicityCS returns [EObject current=null] : iv_ruleMultiplicityCS= ruleMultiplicityCS EOF ;
    public final EObject entryRuleMultiplicityCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMultiplicityCS = null;


        try {
            // InternalCompleteOCL.g:6851:55: (iv_ruleMultiplicityCS= ruleMultiplicityCS EOF )
            // InternalCompleteOCL.g:6852:2: iv_ruleMultiplicityCS= ruleMultiplicityCS EOF
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
    // InternalCompleteOCL.g:6858:1: ruleMultiplicityCS returns [EObject current=null] : (otherlv_0= '[' (this_MultiplicityBoundsCS_1= ruleMultiplicityBoundsCS | this_MultiplicityStringCS_2= ruleMultiplicityStringCS ) (otherlv_3= '|?' | ( (lv_isNullFree_4_0= '|1' ) ) )? otherlv_5= ']' ) ;
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
            // InternalCompleteOCL.g:6864:2: ( (otherlv_0= '[' (this_MultiplicityBoundsCS_1= ruleMultiplicityBoundsCS | this_MultiplicityStringCS_2= ruleMultiplicityStringCS ) (otherlv_3= '|?' | ( (lv_isNullFree_4_0= '|1' ) ) )? otherlv_5= ']' ) )
            // InternalCompleteOCL.g:6865:2: (otherlv_0= '[' (this_MultiplicityBoundsCS_1= ruleMultiplicityBoundsCS | this_MultiplicityStringCS_2= ruleMultiplicityStringCS ) (otherlv_3= '|?' | ( (lv_isNullFree_4_0= '|1' ) ) )? otherlv_5= ']' )
            {
            // InternalCompleteOCL.g:6865:2: (otherlv_0= '[' (this_MultiplicityBoundsCS_1= ruleMultiplicityBoundsCS | this_MultiplicityStringCS_2= ruleMultiplicityStringCS ) (otherlv_3= '|?' | ( (lv_isNullFree_4_0= '|1' ) ) )? otherlv_5= ']' )
            // InternalCompleteOCL.g:6866:3: otherlv_0= '[' (this_MultiplicityBoundsCS_1= ruleMultiplicityBoundsCS | this_MultiplicityStringCS_2= ruleMultiplicityStringCS ) (otherlv_3= '|?' | ( (lv_isNullFree_4_0= '|1' ) ) )? otherlv_5= ']'
            {
            otherlv_0=(Token)match(input,93,FollowSets000.FOLLOW_72); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getMultiplicityCSAccess().getLeftSquareBracketKeyword_0());

            }
            // InternalCompleteOCL.g:6870:3: (this_MultiplicityBoundsCS_1= ruleMultiplicityBoundsCS | this_MultiplicityStringCS_2= ruleMultiplicityStringCS )
            int alt133=2;
            int LA133_0 = input.LA(1);

            if ( (LA133_0==RULE_INT) ) {
                alt133=1;
            }
            else if ( (LA133_0==41||LA133_0==55||LA133_0==57) ) {
                alt133=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 133, 0, input);

                throw nvae;
            }
            switch (alt133) {
                case 1 :
                    // InternalCompleteOCL.g:6871:4: this_MultiplicityBoundsCS_1= ruleMultiplicityBoundsCS
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
                    // InternalCompleteOCL.g:6883:4: this_MultiplicityStringCS_2= ruleMultiplicityStringCS
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

            // InternalCompleteOCL.g:6895:3: (otherlv_3= '|?' | ( (lv_isNullFree_4_0= '|1' ) ) )?
            int alt134=3;
            int LA134_0 = input.LA(1);

            if ( (LA134_0==105) ) {
                alt134=1;
            }
            else if ( (LA134_0==106) ) {
                alt134=2;
            }
            switch (alt134) {
                case 1 :
                    // InternalCompleteOCL.g:6896:4: otherlv_3= '|?'
                    {
                    otherlv_3=(Token)match(input,105,FollowSets000.FOLLOW_74); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_3, grammarAccess.getMultiplicityCSAccess().getVerticalLineQuestionMarkKeyword_2_0());

                    }

                    }
                    break;
                case 2 :
                    // InternalCompleteOCL.g:6901:4: ( (lv_isNullFree_4_0= '|1' ) )
                    {
                    // InternalCompleteOCL.g:6901:4: ( (lv_isNullFree_4_0= '|1' ) )
                    // InternalCompleteOCL.g:6902:5: (lv_isNullFree_4_0= '|1' )
                    {
                    // InternalCompleteOCL.g:6902:5: (lv_isNullFree_4_0= '|1' )
                    // InternalCompleteOCL.g:6903:6: lv_isNullFree_4_0= '|1'
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
    // InternalCompleteOCL.g:6924:1: entryRuleMultiplicityStringCS returns [EObject current=null] : iv_ruleMultiplicityStringCS= ruleMultiplicityStringCS EOF ;
    public final EObject entryRuleMultiplicityStringCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMultiplicityStringCS = null;


        try {
            // InternalCompleteOCL.g:6924:61: (iv_ruleMultiplicityStringCS= ruleMultiplicityStringCS EOF )
            // InternalCompleteOCL.g:6925:2: iv_ruleMultiplicityStringCS= ruleMultiplicityStringCS EOF
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
    // InternalCompleteOCL.g:6931:1: ruleMultiplicityStringCS returns [EObject current=null] : ( ( (lv_stringBounds_0_1= '*' | lv_stringBounds_0_2= '+' | lv_stringBounds_0_3= '?' ) ) ) ;
    public final EObject ruleMultiplicityStringCS() throws RecognitionException {
        EObject current = null;

        Token lv_stringBounds_0_1=null;
        Token lv_stringBounds_0_2=null;
        Token lv_stringBounds_0_3=null;


        	enterRule();

        try {
            // InternalCompleteOCL.g:6937:2: ( ( ( (lv_stringBounds_0_1= '*' | lv_stringBounds_0_2= '+' | lv_stringBounds_0_3= '?' ) ) ) )
            // InternalCompleteOCL.g:6938:2: ( ( (lv_stringBounds_0_1= '*' | lv_stringBounds_0_2= '+' | lv_stringBounds_0_3= '?' ) ) )
            {
            // InternalCompleteOCL.g:6938:2: ( ( (lv_stringBounds_0_1= '*' | lv_stringBounds_0_2= '+' | lv_stringBounds_0_3= '?' ) ) )
            // InternalCompleteOCL.g:6939:3: ( (lv_stringBounds_0_1= '*' | lv_stringBounds_0_2= '+' | lv_stringBounds_0_3= '?' ) )
            {
            // InternalCompleteOCL.g:6939:3: ( (lv_stringBounds_0_1= '*' | lv_stringBounds_0_2= '+' | lv_stringBounds_0_3= '?' ) )
            // InternalCompleteOCL.g:6940:4: (lv_stringBounds_0_1= '*' | lv_stringBounds_0_2= '+' | lv_stringBounds_0_3= '?' )
            {
            // InternalCompleteOCL.g:6940:4: (lv_stringBounds_0_1= '*' | lv_stringBounds_0_2= '+' | lv_stringBounds_0_3= '?' )
            int alt135=3;
            switch ( input.LA(1) ) {
            case 55:
                {
                alt135=1;
                }
                break;
            case 57:
                {
                alt135=2;
                }
                break;
            case 41:
                {
                alt135=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 135, 0, input);

                throw nvae;
            }

            switch (alt135) {
                case 1 :
                    // InternalCompleteOCL.g:6941:5: lv_stringBounds_0_1= '*'
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
                    // InternalCompleteOCL.g:6952:5: lv_stringBounds_0_2= '+'
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
                    // InternalCompleteOCL.g:6963:5: lv_stringBounds_0_3= '?'
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
    // InternalCompleteOCL.g:6979:1: entryRulePathNameCS returns [EObject current=null] : iv_rulePathNameCS= rulePathNameCS EOF ;
    public final EObject entryRulePathNameCS() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePathNameCS = null;


        try {
            // InternalCompleteOCL.g:6979:51: (iv_rulePathNameCS= rulePathNameCS EOF )
            // InternalCompleteOCL.g:6980:2: iv_rulePathNameCS= rulePathNameCS EOF
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
    // InternalCompleteOCL.g:6986:1: rulePathNameCS returns [EObject current=null] : ( ( (lv_ownedPathElements_0_0= ruleFirstPathElementCS ) ) (otherlv_1= '::' ( (lv_ownedPathElements_2_0= ruleNextPathElementCS ) ) )* ) ;
    public final EObject rulePathNameCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject lv_ownedPathElements_0_0 = null;

        EObject lv_ownedPathElements_2_0 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:6992:2: ( ( ( (lv_ownedPathElements_0_0= ruleFirstPathElementCS ) ) (otherlv_1= '::' ( (lv_ownedPathElements_2_0= ruleNextPathElementCS ) ) )* ) )
            // InternalCompleteOCL.g:6993:2: ( ( (lv_ownedPathElements_0_0= ruleFirstPathElementCS ) ) (otherlv_1= '::' ( (lv_ownedPathElements_2_0= ruleNextPathElementCS ) ) )* )
            {
            // InternalCompleteOCL.g:6993:2: ( ( (lv_ownedPathElements_0_0= ruleFirstPathElementCS ) ) (otherlv_1= '::' ( (lv_ownedPathElements_2_0= ruleNextPathElementCS ) ) )* )
            // InternalCompleteOCL.g:6994:3: ( (lv_ownedPathElements_0_0= ruleFirstPathElementCS ) ) (otherlv_1= '::' ( (lv_ownedPathElements_2_0= ruleNextPathElementCS ) ) )*
            {
            // InternalCompleteOCL.g:6994:3: ( (lv_ownedPathElements_0_0= ruleFirstPathElementCS ) )
            // InternalCompleteOCL.g:6995:4: (lv_ownedPathElements_0_0= ruleFirstPathElementCS )
            {
            // InternalCompleteOCL.g:6995:4: (lv_ownedPathElements_0_0= ruleFirstPathElementCS )
            // InternalCompleteOCL.g:6996:5: lv_ownedPathElements_0_0= ruleFirstPathElementCS
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getPathNameCSAccess().getOwnedPathElementsFirstPathElementCSParserRuleCall_0_0());

            }
            pushFollow(FollowSets000.FOLLOW_31);
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

            // InternalCompleteOCL.g:7013:3: (otherlv_1= '::' ( (lv_ownedPathElements_2_0= ruleNextPathElementCS ) ) )*
            loop136:
            do {
                int alt136=2;
                int LA136_0 = input.LA(1);

                if ( (LA136_0==75) ) {
                    alt136=1;
                }


                switch (alt136) {
            	case 1 :
            	    // InternalCompleteOCL.g:7014:4: otherlv_1= '::' ( (lv_ownedPathElements_2_0= ruleNextPathElementCS ) )
            	    {
            	    otherlv_1=(Token)match(input,75,FollowSets000.FOLLOW_5); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      				newLeafNode(otherlv_1, grammarAccess.getPathNameCSAccess().getColonColonKeyword_1_0());

            	    }
            	    // InternalCompleteOCL.g:7018:4: ( (lv_ownedPathElements_2_0= ruleNextPathElementCS ) )
            	    // InternalCompleteOCL.g:7019:5: (lv_ownedPathElements_2_0= ruleNextPathElementCS )
            	    {
            	    // InternalCompleteOCL.g:7019:5: (lv_ownedPathElements_2_0= ruleNextPathElementCS )
            	    // InternalCompleteOCL.g:7020:6: lv_ownedPathElements_2_0= ruleNextPathElementCS
            	    {
            	    if ( state.backtracking==0 ) {

            	      						newCompositeNode(grammarAccess.getPathNameCSAccess().getOwnedPathElementsNextPathElementCSParserRuleCall_1_1_0());

            	    }
            	    pushFollow(FollowSets000.FOLLOW_31);
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
    // $ANTLR end "rulePathNameCS"


    // $ANTLR start "entryRuleUnreservedPathNameCS"
    // InternalCompleteOCL.g:7042:1: entryRuleUnreservedPathNameCS returns [EObject current=null] : iv_ruleUnreservedPathNameCS= ruleUnreservedPathNameCS EOF ;
    public final EObject entryRuleUnreservedPathNameCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleUnreservedPathNameCS = null;


        try {
            // InternalCompleteOCL.g:7042:61: (iv_ruleUnreservedPathNameCS= ruleUnreservedPathNameCS EOF )
            // InternalCompleteOCL.g:7043:2: iv_ruleUnreservedPathNameCS= ruleUnreservedPathNameCS EOF
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
    // InternalCompleteOCL.g:7049:1: ruleUnreservedPathNameCS returns [EObject current=null] : ( ( (lv_ownedPathElements_0_0= ruleNextPathElementCS ) ) (otherlv_1= '::' ( (lv_ownedPathElements_2_0= ruleNextPathElementCS ) ) )* ) ;
    public final EObject ruleUnreservedPathNameCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject lv_ownedPathElements_0_0 = null;

        EObject lv_ownedPathElements_2_0 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:7055:2: ( ( ( (lv_ownedPathElements_0_0= ruleNextPathElementCS ) ) (otherlv_1= '::' ( (lv_ownedPathElements_2_0= ruleNextPathElementCS ) ) )* ) )
            // InternalCompleteOCL.g:7056:2: ( ( (lv_ownedPathElements_0_0= ruleNextPathElementCS ) ) (otherlv_1= '::' ( (lv_ownedPathElements_2_0= ruleNextPathElementCS ) ) )* )
            {
            // InternalCompleteOCL.g:7056:2: ( ( (lv_ownedPathElements_0_0= ruleNextPathElementCS ) ) (otherlv_1= '::' ( (lv_ownedPathElements_2_0= ruleNextPathElementCS ) ) )* )
            // InternalCompleteOCL.g:7057:3: ( (lv_ownedPathElements_0_0= ruleNextPathElementCS ) ) (otherlv_1= '::' ( (lv_ownedPathElements_2_0= ruleNextPathElementCS ) ) )*
            {
            // InternalCompleteOCL.g:7057:3: ( (lv_ownedPathElements_0_0= ruleNextPathElementCS ) )
            // InternalCompleteOCL.g:7058:4: (lv_ownedPathElements_0_0= ruleNextPathElementCS )
            {
            // InternalCompleteOCL.g:7058:4: (lv_ownedPathElements_0_0= ruleNextPathElementCS )
            // InternalCompleteOCL.g:7059:5: lv_ownedPathElements_0_0= ruleNextPathElementCS
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getUnreservedPathNameCSAccess().getOwnedPathElementsNextPathElementCSParserRuleCall_0_0());

            }
            pushFollow(FollowSets000.FOLLOW_31);
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

            // InternalCompleteOCL.g:7076:3: (otherlv_1= '::' ( (lv_ownedPathElements_2_0= ruleNextPathElementCS ) ) )*
            loop137:
            do {
                int alt137=2;
                int LA137_0 = input.LA(1);

                if ( (LA137_0==75) ) {
                    alt137=1;
                }


                switch (alt137) {
            	case 1 :
            	    // InternalCompleteOCL.g:7077:4: otherlv_1= '::' ( (lv_ownedPathElements_2_0= ruleNextPathElementCS ) )
            	    {
            	    otherlv_1=(Token)match(input,75,FollowSets000.FOLLOW_5); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      				newLeafNode(otherlv_1, grammarAccess.getUnreservedPathNameCSAccess().getColonColonKeyword_1_0());

            	    }
            	    // InternalCompleteOCL.g:7081:4: ( (lv_ownedPathElements_2_0= ruleNextPathElementCS ) )
            	    // InternalCompleteOCL.g:7082:5: (lv_ownedPathElements_2_0= ruleNextPathElementCS )
            	    {
            	    // InternalCompleteOCL.g:7082:5: (lv_ownedPathElements_2_0= ruleNextPathElementCS )
            	    // InternalCompleteOCL.g:7083:6: lv_ownedPathElements_2_0= ruleNextPathElementCS
            	    {
            	    if ( state.backtracking==0 ) {

            	      						newCompositeNode(grammarAccess.getUnreservedPathNameCSAccess().getOwnedPathElementsNextPathElementCSParserRuleCall_1_1_0());

            	    }
            	    pushFollow(FollowSets000.FOLLOW_31);
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
            	    break loop137;
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
    // InternalCompleteOCL.g:7105:1: entryRuleFirstPathElementCS returns [EObject current=null] : iv_ruleFirstPathElementCS= ruleFirstPathElementCS EOF ;
    public final EObject entryRuleFirstPathElementCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFirstPathElementCS = null;


        try {
            // InternalCompleteOCL.g:7105:59: (iv_ruleFirstPathElementCS= ruleFirstPathElementCS EOF )
            // InternalCompleteOCL.g:7106:2: iv_ruleFirstPathElementCS= ruleFirstPathElementCS EOF
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
    // InternalCompleteOCL.g:7112:1: ruleFirstPathElementCS returns [EObject current=null] : ( ( ruleUnrestrictedName ) ) ;
    public final EObject ruleFirstPathElementCS() throws RecognitionException {
        EObject current = null;


        	enterRule();

        try {
            // InternalCompleteOCL.g:7118:2: ( ( ( ruleUnrestrictedName ) ) )
            // InternalCompleteOCL.g:7119:2: ( ( ruleUnrestrictedName ) )
            {
            // InternalCompleteOCL.g:7119:2: ( ( ruleUnrestrictedName ) )
            // InternalCompleteOCL.g:7120:3: ( ruleUnrestrictedName )
            {
            // InternalCompleteOCL.g:7120:3: ( ruleUnrestrictedName )
            // InternalCompleteOCL.g:7121:4: ruleUnrestrictedName
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
    // InternalCompleteOCL.g:7141:1: entryRuleNextPathElementCS returns [EObject current=null] : iv_ruleNextPathElementCS= ruleNextPathElementCS EOF ;
    public final EObject entryRuleNextPathElementCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNextPathElementCS = null;


        try {
            // InternalCompleteOCL.g:7141:58: (iv_ruleNextPathElementCS= ruleNextPathElementCS EOF )
            // InternalCompleteOCL.g:7142:2: iv_ruleNextPathElementCS= ruleNextPathElementCS EOF
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
    // InternalCompleteOCL.g:7148:1: ruleNextPathElementCS returns [EObject current=null] : ( ( ruleUnreservedName ) ) ;
    public final EObject ruleNextPathElementCS() throws RecognitionException {
        EObject current = null;


        	enterRule();

        try {
            // InternalCompleteOCL.g:7154:2: ( ( ( ruleUnreservedName ) ) )
            // InternalCompleteOCL.g:7155:2: ( ( ruleUnreservedName ) )
            {
            // InternalCompleteOCL.g:7155:2: ( ( ruleUnreservedName ) )
            // InternalCompleteOCL.g:7156:3: ( ruleUnreservedName )
            {
            // InternalCompleteOCL.g:7156:3: ( ruleUnreservedName )
            // InternalCompleteOCL.g:7157:4: ruleUnreservedName
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
    // InternalCompleteOCL.g:7177:1: entryRuleTemplateBindingCS returns [EObject current=null] : iv_ruleTemplateBindingCS= ruleTemplateBindingCS EOF ;
    public final EObject entryRuleTemplateBindingCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTemplateBindingCS = null;


        try {
            // InternalCompleteOCL.g:7177:58: (iv_ruleTemplateBindingCS= ruleTemplateBindingCS EOF )
            // InternalCompleteOCL.g:7178:2: iv_ruleTemplateBindingCS= ruleTemplateBindingCS EOF
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
    // InternalCompleteOCL.g:7184:1: ruleTemplateBindingCS returns [EObject current=null] : ( ( (lv_ownedSubstitutions_0_0= ruleTemplateParameterSubstitutionCS ) ) (otherlv_1= ',' ( (lv_ownedSubstitutions_2_0= ruleTemplateParameterSubstitutionCS ) ) )* ( (lv_ownedMultiplicity_3_0= ruleMultiplicityCS ) )? ) ;
    public final EObject ruleTemplateBindingCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject lv_ownedSubstitutions_0_0 = null;

        EObject lv_ownedSubstitutions_2_0 = null;

        EObject lv_ownedMultiplicity_3_0 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:7190:2: ( ( ( (lv_ownedSubstitutions_0_0= ruleTemplateParameterSubstitutionCS ) ) (otherlv_1= ',' ( (lv_ownedSubstitutions_2_0= ruleTemplateParameterSubstitutionCS ) ) )* ( (lv_ownedMultiplicity_3_0= ruleMultiplicityCS ) )? ) )
            // InternalCompleteOCL.g:7191:2: ( ( (lv_ownedSubstitutions_0_0= ruleTemplateParameterSubstitutionCS ) ) (otherlv_1= ',' ( (lv_ownedSubstitutions_2_0= ruleTemplateParameterSubstitutionCS ) ) )* ( (lv_ownedMultiplicity_3_0= ruleMultiplicityCS ) )? )
            {
            // InternalCompleteOCL.g:7191:2: ( ( (lv_ownedSubstitutions_0_0= ruleTemplateParameterSubstitutionCS ) ) (otherlv_1= ',' ( (lv_ownedSubstitutions_2_0= ruleTemplateParameterSubstitutionCS ) ) )* ( (lv_ownedMultiplicity_3_0= ruleMultiplicityCS ) )? )
            // InternalCompleteOCL.g:7192:3: ( (lv_ownedSubstitutions_0_0= ruleTemplateParameterSubstitutionCS ) ) (otherlv_1= ',' ( (lv_ownedSubstitutions_2_0= ruleTemplateParameterSubstitutionCS ) ) )* ( (lv_ownedMultiplicity_3_0= ruleMultiplicityCS ) )?
            {
            // InternalCompleteOCL.g:7192:3: ( (lv_ownedSubstitutions_0_0= ruleTemplateParameterSubstitutionCS ) )
            // InternalCompleteOCL.g:7193:4: (lv_ownedSubstitutions_0_0= ruleTemplateParameterSubstitutionCS )
            {
            // InternalCompleteOCL.g:7193:4: (lv_ownedSubstitutions_0_0= ruleTemplateParameterSubstitutionCS )
            // InternalCompleteOCL.g:7194:5: lv_ownedSubstitutions_0_0= ruleTemplateParameterSubstitutionCS
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

            // InternalCompleteOCL.g:7211:3: (otherlv_1= ',' ( (lv_ownedSubstitutions_2_0= ruleTemplateParameterSubstitutionCS ) ) )*
            loop138:
            do {
                int alt138=2;
                int LA138_0 = input.LA(1);

                if ( (LA138_0==28) ) {
                    alt138=1;
                }


                switch (alt138) {
            	case 1 :
            	    // InternalCompleteOCL.g:7212:4: otherlv_1= ',' ( (lv_ownedSubstitutions_2_0= ruleTemplateParameterSubstitutionCS ) )
            	    {
            	    otherlv_1=(Token)match(input,28,FollowSets000.FOLLOW_76); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      				newLeafNode(otherlv_1, grammarAccess.getTemplateBindingCSAccess().getCommaKeyword_1_0());

            	    }
            	    // InternalCompleteOCL.g:7216:4: ( (lv_ownedSubstitutions_2_0= ruleTemplateParameterSubstitutionCS ) )
            	    // InternalCompleteOCL.g:7217:5: (lv_ownedSubstitutions_2_0= ruleTemplateParameterSubstitutionCS )
            	    {
            	    // InternalCompleteOCL.g:7217:5: (lv_ownedSubstitutions_2_0= ruleTemplateParameterSubstitutionCS )
            	    // InternalCompleteOCL.g:7218:6: lv_ownedSubstitutions_2_0= ruleTemplateParameterSubstitutionCS
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
            	    break loop138;
                }
            } while (true);

            // InternalCompleteOCL.g:7236:3: ( (lv_ownedMultiplicity_3_0= ruleMultiplicityCS ) )?
            int alt139=2;
            int LA139_0 = input.LA(1);

            if ( (LA139_0==93) ) {
                alt139=1;
            }
            switch (alt139) {
                case 1 :
                    // InternalCompleteOCL.g:7237:4: (lv_ownedMultiplicity_3_0= ruleMultiplicityCS )
                    {
                    // InternalCompleteOCL.g:7237:4: (lv_ownedMultiplicity_3_0= ruleMultiplicityCS )
                    // InternalCompleteOCL.g:7238:5: lv_ownedMultiplicity_3_0= ruleMultiplicityCS
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
    // InternalCompleteOCL.g:7259:1: entryRuleTemplateParameterSubstitutionCS returns [EObject current=null] : iv_ruleTemplateParameterSubstitutionCS= ruleTemplateParameterSubstitutionCS EOF ;
    public final EObject entryRuleTemplateParameterSubstitutionCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTemplateParameterSubstitutionCS = null;


        try {
            // InternalCompleteOCL.g:7259:72: (iv_ruleTemplateParameterSubstitutionCS= ruleTemplateParameterSubstitutionCS EOF )
            // InternalCompleteOCL.g:7260:2: iv_ruleTemplateParameterSubstitutionCS= ruleTemplateParameterSubstitutionCS EOF
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
    // InternalCompleteOCL.g:7266:1: ruleTemplateParameterSubstitutionCS returns [EObject current=null] : ( (lv_ownedActualParameter_0_0= ruleTypeRefCS ) ) ;
    public final EObject ruleTemplateParameterSubstitutionCS() throws RecognitionException {
        EObject current = null;

        EObject lv_ownedActualParameter_0_0 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:7272:2: ( ( (lv_ownedActualParameter_0_0= ruleTypeRefCS ) ) )
            // InternalCompleteOCL.g:7273:2: ( (lv_ownedActualParameter_0_0= ruleTypeRefCS ) )
            {
            // InternalCompleteOCL.g:7273:2: ( (lv_ownedActualParameter_0_0= ruleTypeRefCS ) )
            // InternalCompleteOCL.g:7274:3: (lv_ownedActualParameter_0_0= ruleTypeRefCS )
            {
            // InternalCompleteOCL.g:7274:3: (lv_ownedActualParameter_0_0= ruleTypeRefCS )
            // InternalCompleteOCL.g:7275:4: lv_ownedActualParameter_0_0= ruleTypeRefCS
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
    // InternalCompleteOCL.g:7295:1: entryRuleTypeParameterCS returns [EObject current=null] : iv_ruleTypeParameterCS= ruleTypeParameterCS EOF ;
    public final EObject entryRuleTypeParameterCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTypeParameterCS = null;


        try {
            // InternalCompleteOCL.g:7295:56: (iv_ruleTypeParameterCS= ruleTypeParameterCS EOF )
            // InternalCompleteOCL.g:7296:2: iv_ruleTypeParameterCS= ruleTypeParameterCS EOF
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
    // InternalCompleteOCL.g:7302:1: ruleTypeParameterCS returns [EObject current=null] : ( ( (lv_name_0_0= ruleUnrestrictedName ) ) (otherlv_1= 'extends' ( (lv_ownedExtends_2_0= ruleTypedRefCS ) ) (otherlv_3= '&&' ( (lv_ownedExtends_4_0= ruleTypedRefCS ) ) )* )? ) ;
    public final EObject ruleTypeParameterCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        AntlrDatatypeRuleToken lv_name_0_0 = null;

        EObject lv_ownedExtends_2_0 = null;

        EObject lv_ownedExtends_4_0 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:7308:2: ( ( ( (lv_name_0_0= ruleUnrestrictedName ) ) (otherlv_1= 'extends' ( (lv_ownedExtends_2_0= ruleTypedRefCS ) ) (otherlv_3= '&&' ( (lv_ownedExtends_4_0= ruleTypedRefCS ) ) )* )? ) )
            // InternalCompleteOCL.g:7309:2: ( ( (lv_name_0_0= ruleUnrestrictedName ) ) (otherlv_1= 'extends' ( (lv_ownedExtends_2_0= ruleTypedRefCS ) ) (otherlv_3= '&&' ( (lv_ownedExtends_4_0= ruleTypedRefCS ) ) )* )? )
            {
            // InternalCompleteOCL.g:7309:2: ( ( (lv_name_0_0= ruleUnrestrictedName ) ) (otherlv_1= 'extends' ( (lv_ownedExtends_2_0= ruleTypedRefCS ) ) (otherlv_3= '&&' ( (lv_ownedExtends_4_0= ruleTypedRefCS ) ) )* )? )
            // InternalCompleteOCL.g:7310:3: ( (lv_name_0_0= ruleUnrestrictedName ) ) (otherlv_1= 'extends' ( (lv_ownedExtends_2_0= ruleTypedRefCS ) ) (otherlv_3= '&&' ( (lv_ownedExtends_4_0= ruleTypedRefCS ) ) )* )?
            {
            // InternalCompleteOCL.g:7310:3: ( (lv_name_0_0= ruleUnrestrictedName ) )
            // InternalCompleteOCL.g:7311:4: (lv_name_0_0= ruleUnrestrictedName )
            {
            // InternalCompleteOCL.g:7311:4: (lv_name_0_0= ruleUnrestrictedName )
            // InternalCompleteOCL.g:7312:5: lv_name_0_0= ruleUnrestrictedName
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

            // InternalCompleteOCL.g:7329:3: (otherlv_1= 'extends' ( (lv_ownedExtends_2_0= ruleTypedRefCS ) ) (otherlv_3= '&&' ( (lv_ownedExtends_4_0= ruleTypedRefCS ) ) )* )?
            int alt141=2;
            int LA141_0 = input.LA(1);

            if ( (LA141_0==107) ) {
                alt141=1;
            }
            switch (alt141) {
                case 1 :
                    // InternalCompleteOCL.g:7330:4: otherlv_1= 'extends' ( (lv_ownedExtends_2_0= ruleTypedRefCS ) ) (otherlv_3= '&&' ( (lv_ownedExtends_4_0= ruleTypedRefCS ) ) )*
                    {
                    otherlv_1=(Token)match(input,107,FollowSets000.FOLLOW_21); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_1, grammarAccess.getTypeParameterCSAccess().getExtendsKeyword_1_0());

                    }
                    // InternalCompleteOCL.g:7334:4: ( (lv_ownedExtends_2_0= ruleTypedRefCS ) )
                    // InternalCompleteOCL.g:7335:5: (lv_ownedExtends_2_0= ruleTypedRefCS )
                    {
                    // InternalCompleteOCL.g:7335:5: (lv_ownedExtends_2_0= ruleTypedRefCS )
                    // InternalCompleteOCL.g:7336:6: lv_ownedExtends_2_0= ruleTypedRefCS
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

                    // InternalCompleteOCL.g:7353:4: (otherlv_3= '&&' ( (lv_ownedExtends_4_0= ruleTypedRefCS ) ) )*
                    loop140:
                    do {
                        int alt140=2;
                        int LA140_0 = input.LA(1);

                        if ( (LA140_0==108) ) {
                            alt140=1;
                        }


                        switch (alt140) {
                    	case 1 :
                    	    // InternalCompleteOCL.g:7354:5: otherlv_3= '&&' ( (lv_ownedExtends_4_0= ruleTypedRefCS ) )
                    	    {
                    	    otherlv_3=(Token)match(input,108,FollowSets000.FOLLOW_21); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      					newLeafNode(otherlv_3, grammarAccess.getTypeParameterCSAccess().getAmpersandAmpersandKeyword_1_2_0());

                    	    }
                    	    // InternalCompleteOCL.g:7358:5: ( (lv_ownedExtends_4_0= ruleTypedRefCS ) )
                    	    // InternalCompleteOCL.g:7359:6: (lv_ownedExtends_4_0= ruleTypedRefCS )
                    	    {
                    	    // InternalCompleteOCL.g:7359:6: (lv_ownedExtends_4_0= ruleTypedRefCS )
                    	    // InternalCompleteOCL.g:7360:7: lv_ownedExtends_4_0= ruleTypedRefCS
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
                    	    break loop140;
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
    // InternalCompleteOCL.g:7383:1: entryRuleTypeRefCS returns [EObject current=null] : iv_ruleTypeRefCS= ruleTypeRefCS EOF ;
    public final EObject entryRuleTypeRefCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTypeRefCS = null;


        try {
            // InternalCompleteOCL.g:7383:50: (iv_ruleTypeRefCS= ruleTypeRefCS EOF )
            // InternalCompleteOCL.g:7384:2: iv_ruleTypeRefCS= ruleTypeRefCS EOF
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
    // InternalCompleteOCL.g:7390:1: ruleTypeRefCS returns [EObject current=null] : (this_TypedRefCS_0= ruleTypedRefCS | this_WildcardTypeRefCS_1= ruleWildcardTypeRefCS ) ;
    public final EObject ruleTypeRefCS() throws RecognitionException {
        EObject current = null;

        EObject this_TypedRefCS_0 = null;

        EObject this_WildcardTypeRefCS_1 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:7396:2: ( (this_TypedRefCS_0= ruleTypedRefCS | this_WildcardTypeRefCS_1= ruleWildcardTypeRefCS ) )
            // InternalCompleteOCL.g:7397:2: (this_TypedRefCS_0= ruleTypedRefCS | this_WildcardTypeRefCS_1= ruleWildcardTypeRefCS )
            {
            // InternalCompleteOCL.g:7397:2: (this_TypedRefCS_0= ruleTypedRefCS | this_WildcardTypeRefCS_1= ruleWildcardTypeRefCS )
            int alt142=2;
            int LA142_0 = input.LA(1);

            if ( ((LA142_0>=RULE_SIMPLE_ID && LA142_0<=RULE_ESCAPED_ID)||(LA142_0>=30 && LA142_0<=32)||(LA142_0>=42 && LA142_0<=51)||(LA142_0>=73 && LA142_0<=74)||(LA142_0>=76 && LA142_0<=80)) ) {
                alt142=1;
            }
            else if ( (LA142_0==41) ) {
                alt142=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 142, 0, input);

                throw nvae;
            }
            switch (alt142) {
                case 1 :
                    // InternalCompleteOCL.g:7398:3: this_TypedRefCS_0= ruleTypedRefCS
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
                    // InternalCompleteOCL.g:7410:3: this_WildcardTypeRefCS_1= ruleWildcardTypeRefCS
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
    // InternalCompleteOCL.g:7425:1: entryRuleTypedTypeRefCS returns [EObject current=null] : iv_ruleTypedTypeRefCS= ruleTypedTypeRefCS EOF ;
    public final EObject entryRuleTypedTypeRefCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTypedTypeRefCS = null;


        try {
            // InternalCompleteOCL.g:7425:55: (iv_ruleTypedTypeRefCS= ruleTypedTypeRefCS EOF )
            // InternalCompleteOCL.g:7426:2: iv_ruleTypedTypeRefCS= ruleTypedTypeRefCS EOF
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
    // InternalCompleteOCL.g:7432:1: ruleTypedTypeRefCS returns [EObject current=null] : ( ( (lv_ownedPathName_0_0= rulePathNameCS ) ) (otherlv_1= '(' ( (lv_ownedBinding_2_0= ruleTemplateBindingCS ) ) otherlv_3= ')' )? ) ;
    public final EObject ruleTypedTypeRefCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_ownedPathName_0_0 = null;

        EObject lv_ownedBinding_2_0 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:7438:2: ( ( ( (lv_ownedPathName_0_0= rulePathNameCS ) ) (otherlv_1= '(' ( (lv_ownedBinding_2_0= ruleTemplateBindingCS ) ) otherlv_3= ')' )? ) )
            // InternalCompleteOCL.g:7439:2: ( ( (lv_ownedPathName_0_0= rulePathNameCS ) ) (otherlv_1= '(' ( (lv_ownedBinding_2_0= ruleTemplateBindingCS ) ) otherlv_3= ')' )? )
            {
            // InternalCompleteOCL.g:7439:2: ( ( (lv_ownedPathName_0_0= rulePathNameCS ) ) (otherlv_1= '(' ( (lv_ownedBinding_2_0= ruleTemplateBindingCS ) ) otherlv_3= ')' )? )
            // InternalCompleteOCL.g:7440:3: ( (lv_ownedPathName_0_0= rulePathNameCS ) ) (otherlv_1= '(' ( (lv_ownedBinding_2_0= ruleTemplateBindingCS ) ) otherlv_3= ')' )?
            {
            // InternalCompleteOCL.g:7440:3: ( (lv_ownedPathName_0_0= rulePathNameCS ) )
            // InternalCompleteOCL.g:7441:4: (lv_ownedPathName_0_0= rulePathNameCS )
            {
            // InternalCompleteOCL.g:7441:4: (lv_ownedPathName_0_0= rulePathNameCS )
            // InternalCompleteOCL.g:7442:5: lv_ownedPathName_0_0= rulePathNameCS
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

            // InternalCompleteOCL.g:7459:3: (otherlv_1= '(' ( (lv_ownedBinding_2_0= ruleTemplateBindingCS ) ) otherlv_3= ')' )?
            int alt143=2;
            int LA143_0 = input.LA(1);

            if ( (LA143_0==21) ) {
                alt143=1;
            }
            switch (alt143) {
                case 1 :
                    // InternalCompleteOCL.g:7460:4: otherlv_1= '(' ( (lv_ownedBinding_2_0= ruleTemplateBindingCS ) ) otherlv_3= ')'
                    {
                    otherlv_1=(Token)match(input,21,FollowSets000.FOLLOW_76); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_1, grammarAccess.getTypedTypeRefCSAccess().getLeftParenthesisKeyword_1_0());

                    }
                    // InternalCompleteOCL.g:7464:4: ( (lv_ownedBinding_2_0= ruleTemplateBindingCS ) )
                    // InternalCompleteOCL.g:7465:5: (lv_ownedBinding_2_0= ruleTemplateBindingCS )
                    {
                    // InternalCompleteOCL.g:7465:5: (lv_ownedBinding_2_0= ruleTemplateBindingCS )
                    // InternalCompleteOCL.g:7466:6: lv_ownedBinding_2_0= ruleTemplateBindingCS
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
    // InternalCompleteOCL.g:7492:1: entryRuleWildcardTypeRefCS returns [EObject current=null] : iv_ruleWildcardTypeRefCS= ruleWildcardTypeRefCS EOF ;
    public final EObject entryRuleWildcardTypeRefCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleWildcardTypeRefCS = null;


        try {
            // InternalCompleteOCL.g:7492:58: (iv_ruleWildcardTypeRefCS= ruleWildcardTypeRefCS EOF )
            // InternalCompleteOCL.g:7493:2: iv_ruleWildcardTypeRefCS= ruleWildcardTypeRefCS EOF
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
    // InternalCompleteOCL.g:7499:1: ruleWildcardTypeRefCS returns [EObject current=null] : ( () otherlv_1= '?' (otherlv_2= 'extends' ( (lv_ownedExtends_3_0= ruleTypedRefCS ) ) )? ) ;
    public final EObject ruleWildcardTypeRefCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        EObject lv_ownedExtends_3_0 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:7505:2: ( ( () otherlv_1= '?' (otherlv_2= 'extends' ( (lv_ownedExtends_3_0= ruleTypedRefCS ) ) )? ) )
            // InternalCompleteOCL.g:7506:2: ( () otherlv_1= '?' (otherlv_2= 'extends' ( (lv_ownedExtends_3_0= ruleTypedRefCS ) ) )? )
            {
            // InternalCompleteOCL.g:7506:2: ( () otherlv_1= '?' (otherlv_2= 'extends' ( (lv_ownedExtends_3_0= ruleTypedRefCS ) ) )? )
            // InternalCompleteOCL.g:7507:3: () otherlv_1= '?' (otherlv_2= 'extends' ( (lv_ownedExtends_3_0= ruleTypedRefCS ) ) )?
            {
            // InternalCompleteOCL.g:7507:3: ()
            // InternalCompleteOCL.g:7508:4:
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
            // InternalCompleteOCL.g:7521:3: (otherlv_2= 'extends' ( (lv_ownedExtends_3_0= ruleTypedRefCS ) ) )?
            int alt144=2;
            int LA144_0 = input.LA(1);

            if ( (LA144_0==107) ) {
                alt144=1;
            }
            switch (alt144) {
                case 1 :
                    // InternalCompleteOCL.g:7522:4: otherlv_2= 'extends' ( (lv_ownedExtends_3_0= ruleTypedRefCS ) )
                    {
                    otherlv_2=(Token)match(input,107,FollowSets000.FOLLOW_21); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_2, grammarAccess.getWildcardTypeRefCSAccess().getExtendsKeyword_2_0());

                    }
                    // InternalCompleteOCL.g:7526:4: ( (lv_ownedExtends_3_0= ruleTypedRefCS ) )
                    // InternalCompleteOCL.g:7527:5: (lv_ownedExtends_3_0= ruleTypedRefCS )
                    {
                    // InternalCompleteOCL.g:7527:5: (lv_ownedExtends_3_0= ruleTypedRefCS )
                    // InternalCompleteOCL.g:7528:6: lv_ownedExtends_3_0= ruleTypedRefCS
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
    // InternalCompleteOCL.g:7550:1: entryRuleID returns [String current=null] : iv_ruleID= ruleID EOF ;
    public final String entryRuleID() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleID = null;


        try {
            // InternalCompleteOCL.g:7550:42: (iv_ruleID= ruleID EOF )
            // InternalCompleteOCL.g:7551:2: iv_ruleID= ruleID EOF
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
    // InternalCompleteOCL.g:7557:1: ruleID returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_SIMPLE_ID_0= RULE_SIMPLE_ID | this_ESCAPED_ID_1= RULE_ESCAPED_ID ) ;
    public final AntlrDatatypeRuleToken ruleID() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_SIMPLE_ID_0=null;
        Token this_ESCAPED_ID_1=null;


        	enterRule();

        try {
            // InternalCompleteOCL.g:7563:2: ( (this_SIMPLE_ID_0= RULE_SIMPLE_ID | this_ESCAPED_ID_1= RULE_ESCAPED_ID ) )
            // InternalCompleteOCL.g:7564:2: (this_SIMPLE_ID_0= RULE_SIMPLE_ID | this_ESCAPED_ID_1= RULE_ESCAPED_ID )
            {
            // InternalCompleteOCL.g:7564:2: (this_SIMPLE_ID_0= RULE_SIMPLE_ID | this_ESCAPED_ID_1= RULE_ESCAPED_ID )
            int alt145=2;
            int LA145_0 = input.LA(1);

            if ( (LA145_0==RULE_SIMPLE_ID) ) {
                alt145=1;
            }
            else if ( (LA145_0==RULE_ESCAPED_ID) ) {
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
                    // InternalCompleteOCL.g:7565:3: this_SIMPLE_ID_0= RULE_SIMPLE_ID
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
                    // InternalCompleteOCL.g:7573:3: this_ESCAPED_ID_1= RULE_ESCAPED_ID
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
    // InternalCompleteOCL.g:7584:1: entryRuleIdentifier returns [String current=null] : iv_ruleIdentifier= ruleIdentifier EOF ;
    public final String entryRuleIdentifier() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleIdentifier = null;


        try {
            // InternalCompleteOCL.g:7584:50: (iv_ruleIdentifier= ruleIdentifier EOF )
            // InternalCompleteOCL.g:7585:2: iv_ruleIdentifier= ruleIdentifier EOF
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
    // InternalCompleteOCL.g:7591:1: ruleIdentifier returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_ID_0= ruleID ;
    public final AntlrDatatypeRuleToken ruleIdentifier() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        AntlrDatatypeRuleToken this_ID_0 = null;



        	enterRule();

        try {
            // InternalCompleteOCL.g:7597:2: (this_ID_0= ruleID )
            // InternalCompleteOCL.g:7598:2: this_ID_0= ruleID
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
    // InternalCompleteOCL.g:7611:1: entryRuleLOWER returns [String current=null] : iv_ruleLOWER= ruleLOWER EOF ;
    public final String entryRuleLOWER() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleLOWER = null;


        try {
            // InternalCompleteOCL.g:7611:45: (iv_ruleLOWER= ruleLOWER EOF )
            // InternalCompleteOCL.g:7612:2: iv_ruleLOWER= ruleLOWER EOF
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
    // InternalCompleteOCL.g:7618:1: ruleLOWER returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_INT_0= RULE_INT ;
    public final AntlrDatatypeRuleToken ruleLOWER() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_INT_0=null;


        	enterRule();

        try {
            // InternalCompleteOCL.g:7624:2: (this_INT_0= RULE_INT )
            // InternalCompleteOCL.g:7625:2: this_INT_0= RULE_INT
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
    // InternalCompleteOCL.g:7635:1: entryRuleNUMBER_LITERAL returns [String current=null] : iv_ruleNUMBER_LITERAL= ruleNUMBER_LITERAL EOF ;
    public final String entryRuleNUMBER_LITERAL() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleNUMBER_LITERAL = null;


        try {
            // InternalCompleteOCL.g:7635:54: (iv_ruleNUMBER_LITERAL= ruleNUMBER_LITERAL EOF )
            // InternalCompleteOCL.g:7636:2: iv_ruleNUMBER_LITERAL= ruleNUMBER_LITERAL EOF
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
    // InternalCompleteOCL.g:7642:1: ruleNUMBER_LITERAL returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_INT_0= RULE_INT ;
    public final AntlrDatatypeRuleToken ruleNUMBER_LITERAL() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_INT_0=null;


        	enterRule();

        try {
            // InternalCompleteOCL.g:7648:2: (this_INT_0= RULE_INT )
            // InternalCompleteOCL.g:7649:2: this_INT_0= RULE_INT
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
    // InternalCompleteOCL.g:7659:1: entryRuleStringLiteral returns [String current=null] : iv_ruleStringLiteral= ruleStringLiteral EOF ;
    public final String entryRuleStringLiteral() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleStringLiteral = null;


        try {
            // InternalCompleteOCL.g:7659:53: (iv_ruleStringLiteral= ruleStringLiteral EOF )
            // InternalCompleteOCL.g:7660:2: iv_ruleStringLiteral= ruleStringLiteral EOF
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
    // InternalCompleteOCL.g:7666:1: ruleStringLiteral returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_SINGLE_QUOTED_STRING_0= RULE_SINGLE_QUOTED_STRING ;
    public final AntlrDatatypeRuleToken ruleStringLiteral() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_SINGLE_QUOTED_STRING_0=null;


        	enterRule();

        try {
            // InternalCompleteOCL.g:7672:2: (this_SINGLE_QUOTED_STRING_0= RULE_SINGLE_QUOTED_STRING )
            // InternalCompleteOCL.g:7673:2: this_SINGLE_QUOTED_STRING_0= RULE_SINGLE_QUOTED_STRING
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
    // InternalCompleteOCL.g:7683:1: entryRuleUPPER returns [String current=null] : iv_ruleUPPER= ruleUPPER EOF ;
    public final String entryRuleUPPER() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleUPPER = null;


        try {
            // InternalCompleteOCL.g:7683:45: (iv_ruleUPPER= ruleUPPER EOF )
            // InternalCompleteOCL.g:7684:2: iv_ruleUPPER= ruleUPPER EOF
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
    // InternalCompleteOCL.g:7690:1: ruleUPPER returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_INT_0= RULE_INT | kw= '*' ) ;
    public final AntlrDatatypeRuleToken ruleUPPER() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_INT_0=null;
        Token kw=null;


        	enterRule();

        try {
            // InternalCompleteOCL.g:7696:2: ( (this_INT_0= RULE_INT | kw= '*' ) )
            // InternalCompleteOCL.g:7697:2: (this_INT_0= RULE_INT | kw= '*' )
            {
            // InternalCompleteOCL.g:7697:2: (this_INT_0= RULE_INT | kw= '*' )
            int alt146=2;
            int LA146_0 = input.LA(1);

            if ( (LA146_0==RULE_INT) ) {
                alt146=1;
            }
            else if ( (LA146_0==55) ) {
                alt146=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 146, 0, input);

                throw nvae;
            }
            switch (alt146) {
                case 1 :
                    // InternalCompleteOCL.g:7698:3: this_INT_0= RULE_INT
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
                    // InternalCompleteOCL.g:7706:3: kw= '*'
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
    // InternalCompleteOCL.g:7715:1: entryRuleURI returns [String current=null] : iv_ruleURI= ruleURI EOF ;
    public final String entryRuleURI() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleURI = null;


        try {
            // InternalCompleteOCL.g:7715:43: (iv_ruleURI= ruleURI EOF )
            // InternalCompleteOCL.g:7716:2: iv_ruleURI= ruleURI EOF
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
    // InternalCompleteOCL.g:7722:1: ruleURI returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_SINGLE_QUOTED_STRING_0= RULE_SINGLE_QUOTED_STRING ;
    public final AntlrDatatypeRuleToken ruleURI() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_SINGLE_QUOTED_STRING_0=null;


        	enterRule();

        try {
            // InternalCompleteOCL.g:7728:2: (this_SINGLE_QUOTED_STRING_0= RULE_SINGLE_QUOTED_STRING )
            // InternalCompleteOCL.g:7729:2: this_SINGLE_QUOTED_STRING_0= RULE_SINGLE_QUOTED_STRING
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

    // $ANTLR start synpred134_InternalCompleteOCL
    public final void synpred134_InternalCompleteOCL_fragment() throws RecognitionException {
        EObject this_TypeLiteralCS_1 = null;


        // InternalCompleteOCL.g:4635:3: (this_TypeLiteralCS_1= ruleTypeLiteralCS )
        // InternalCompleteOCL.g:4635:3: this_TypeLiteralCS_1= ruleTypeLiteralCS
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


        // InternalCompleteOCL.g:4726:3: ( (this_PrefixedPrimaryExpCS_0= rulePrefixedPrimaryExpCS ( () ( (lv_name_2_0= ruleBinaryOperatorName ) ) ( (lv_ownedRight_3_0= ruleExpCS ) ) )? ) )
        // InternalCompleteOCL.g:4726:3: (this_PrefixedPrimaryExpCS_0= rulePrefixedPrimaryExpCS ( () ( (lv_name_2_0= ruleBinaryOperatorName ) ) ( (lv_ownedRight_3_0= ruleExpCS ) ) )? )
        {
        // InternalCompleteOCL.g:4726:3: (this_PrefixedPrimaryExpCS_0= rulePrefixedPrimaryExpCS ( () ( (lv_name_2_0= ruleBinaryOperatorName ) ) ( (lv_ownedRight_3_0= ruleExpCS ) ) )? )
        // InternalCompleteOCL.g:4727:4: this_PrefixedPrimaryExpCS_0= rulePrefixedPrimaryExpCS ( () ( (lv_name_2_0= ruleBinaryOperatorName ) ) ( (lv_ownedRight_3_0= ruleExpCS ) ) )?
        {
        if ( state.backtracking==0 ) {

          				/* */

        }
        pushFollow(FollowSets000.FOLLOW_50);
        this_PrefixedPrimaryExpCS_0=rulePrefixedPrimaryExpCS();

        state._fsp--;
        if (state.failed) return ;
        // InternalCompleteOCL.g:4738:4: ( () ( (lv_name_2_0= ruleBinaryOperatorName ) ) ( (lv_ownedRight_3_0= ruleExpCS ) ) )?
        int alt164=2;
        int LA164_0 = input.LA(1);

        if ( ((LA164_0>=17 && LA164_0<=18)||LA164_0==29||(LA164_0>=39 && LA164_0<=40)||LA164_0==52||(LA164_0>=55 && LA164_0<=72)) ) {
            alt164=1;
        }
        switch (alt164) {
            case 1 :
                // InternalCompleteOCL.g:4739:5: () ( (lv_name_2_0= ruleBinaryOperatorName ) ) ( (lv_ownedRight_3_0= ruleExpCS ) )
                {
                // InternalCompleteOCL.g:4739:5: ()
                // InternalCompleteOCL.g:4740:6:
                {
                if ( state.backtracking==0 ) {

                  						/* */

                }

                }

                // InternalCompleteOCL.g:4749:5: ( (lv_name_2_0= ruleBinaryOperatorName ) )
                // InternalCompleteOCL.g:4750:6: (lv_name_2_0= ruleBinaryOperatorName )
                {
                // InternalCompleteOCL.g:4750:6: (lv_name_2_0= ruleBinaryOperatorName )
                // InternalCompleteOCL.g:4751:7: lv_name_2_0= ruleBinaryOperatorName
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

                // InternalCompleteOCL.g:4768:5: ( (lv_ownedRight_3_0= ruleExpCS ) )
                // InternalCompleteOCL.g:4769:6: (lv_ownedRight_3_0= ruleExpCS )
                {
                // InternalCompleteOCL.g:4769:6: (lv_ownedRight_3_0= ruleExpCS )
                // InternalCompleteOCL.g:4770:7: lv_ownedRight_3_0= ruleExpCS
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


        // InternalCompleteOCL.g:5030:3: (this_TupleLiteralExpCS_4= ruleTupleLiteralExpCS )
        // InternalCompleteOCL.g:5030:3: this_TupleLiteralExpCS_4= ruleTupleLiteralExpCS
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


        // InternalCompleteOCL.g:5042:3: (this_MapLiteralExpCS_5= ruleMapLiteralExpCS )
        // InternalCompleteOCL.g:5042:3: this_MapLiteralExpCS_5= ruleMapLiteralExpCS
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


        // InternalCompleteOCL.g:5054:3: (this_CollectionLiteralExpCS_6= ruleCollectionLiteralExpCS )
        // InternalCompleteOCL.g:5054:3: this_CollectionLiteralExpCS_6= ruleCollectionLiteralExpCS
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


        // InternalCompleteOCL.g:5078:3: (this_TypeLiteralExpCS_8= ruleTypeLiteralExpCS )
        // InternalCompleteOCL.g:5078:3: this_TypeLiteralExpCS_8= ruleTypeLiteralExpCS
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


        // InternalCompleteOCL.g:5521:5: ( ( (otherlv_1= 'with' | otherlv_2= '<-' ) ( (lv_ownedCoIterator_3_0= ruleCoIteratorVariableCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? ) )
        // InternalCompleteOCL.g:5521:5: ( (otherlv_1= 'with' | otherlv_2= '<-' ) ( (lv_ownedCoIterator_3_0= ruleCoIteratorVariableCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? )
        {
        // InternalCompleteOCL.g:5521:5: ( (otherlv_1= 'with' | otherlv_2= '<-' ) ( (lv_ownedCoIterator_3_0= ruleCoIteratorVariableCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? )
        // InternalCompleteOCL.g:5522:6: (otherlv_1= 'with' | otherlv_2= '<-' ) ( (lv_ownedCoIterator_3_0= ruleCoIteratorVariableCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )?
        {
        // InternalCompleteOCL.g:5522:6: (otherlv_1= 'with' | otherlv_2= '<-' )
        int alt169=2;
        int LA169_0 = input.LA(1);

        if ( (LA169_0==86) ) {
            alt169=1;
        }
        else if ( (LA169_0==87) ) {
            alt169=2;
        }
        else {
            if (state.backtracking>0) {state.failed=true; return ;}
            NoViableAltException nvae =
                new NoViableAltException("", 169, 0, input);

            throw nvae;
        }
        switch (alt169) {
            case 1 :
                // InternalCompleteOCL.g:5523:7: otherlv_1= 'with'
                {
                otherlv_1=(Token)match(input,86,FollowSets000.FOLLOW_15); if (state.failed) return ;

                }
                break;
            case 2 :
                // InternalCompleteOCL.g:5528:7: otherlv_2= '<-'
                {
                otherlv_2=(Token)match(input,87,FollowSets000.FOLLOW_15); if (state.failed) return ;

                }
                break;

        }

        // InternalCompleteOCL.g:5533:6: ( (lv_ownedCoIterator_3_0= ruleCoIteratorVariableCS ) )
        // InternalCompleteOCL.g:5534:7: (lv_ownedCoIterator_3_0= ruleCoIteratorVariableCS )
        {
        // InternalCompleteOCL.g:5534:7: (lv_ownedCoIterator_3_0= ruleCoIteratorVariableCS )
        // InternalCompleteOCL.g:5535:8: lv_ownedCoIterator_3_0= ruleCoIteratorVariableCS
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

        // InternalCompleteOCL.g:5552:6: (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )?
        int alt170=2;
        int LA170_0 = input.LA(1);

        if ( (LA170_0==29) ) {
            alt170=1;
        }
        switch (alt170) {
            case 1 :
                // InternalCompleteOCL.g:5553:7: otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) )
                {
                otherlv_4=(Token)match(input,29,FollowSets000.FOLLOW_40); if (state.failed) return ;
                // InternalCompleteOCL.g:5557:7: ( (lv_ownedInitExpression_5_0= ruleExpCS ) )
                // InternalCompleteOCL.g:5558:8: (lv_ownedInitExpression_5_0= ruleExpCS )
                {
                // InternalCompleteOCL.g:5558:8: (lv_ownedInitExpression_5_0= ruleExpCS )
                // InternalCompleteOCL.g:5559:9: lv_ownedInitExpression_5_0= ruleExpCS
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


        // InternalCompleteOCL.g:5579:5: ( (otherlv_6= ':' ( (lv_ownedType_7_0= ruleTypeExpCS ) ) ( (otherlv_8= 'with' | otherlv_9= '<-' ) ( (lv_ownedCoIterator_10_0= ruleCoIteratorVariableCS ) ) )? (otherlv_11= '=' ( (lv_ownedInitExpression_12_0= ruleExpCS ) ) )? ) )
        // InternalCompleteOCL.g:5579:5: (otherlv_6= ':' ( (lv_ownedType_7_0= ruleTypeExpCS ) ) ( (otherlv_8= 'with' | otherlv_9= '<-' ) ( (lv_ownedCoIterator_10_0= ruleCoIteratorVariableCS ) ) )? (otherlv_11= '=' ( (lv_ownedInitExpression_12_0= ruleExpCS ) ) )? )
        {
        // InternalCompleteOCL.g:5579:5: (otherlv_6= ':' ( (lv_ownedType_7_0= ruleTypeExpCS ) ) ( (otherlv_8= 'with' | otherlv_9= '<-' ) ( (lv_ownedCoIterator_10_0= ruleCoIteratorVariableCS ) ) )? (otherlv_11= '=' ( (lv_ownedInitExpression_12_0= ruleExpCS ) ) )? )
        // InternalCompleteOCL.g:5580:6: otherlv_6= ':' ( (lv_ownedType_7_0= ruleTypeExpCS ) ) ( (otherlv_8= 'with' | otherlv_9= '<-' ) ( (lv_ownedCoIterator_10_0= ruleCoIteratorVariableCS ) ) )? (otherlv_11= '=' ( (lv_ownedInitExpression_12_0= ruleExpCS ) ) )?
        {
        otherlv_6=(Token)match(input,23,FollowSets000.FOLLOW_21); if (state.failed) return ;
        // InternalCompleteOCL.g:5584:6: ( (lv_ownedType_7_0= ruleTypeExpCS ) )
        // InternalCompleteOCL.g:5585:7: (lv_ownedType_7_0= ruleTypeExpCS )
        {
        // InternalCompleteOCL.g:5585:7: (lv_ownedType_7_0= ruleTypeExpCS )
        // InternalCompleteOCL.g:5586:8: lv_ownedType_7_0= ruleTypeExpCS
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

        // InternalCompleteOCL.g:5603:6: ( (otherlv_8= 'with' | otherlv_9= '<-' ) ( (lv_ownedCoIterator_10_0= ruleCoIteratorVariableCS ) ) )?
        int alt173=2;
        int LA173_0 = input.LA(1);

        if ( ((LA173_0>=86 && LA173_0<=87)) ) {
            alt173=1;
        }
        switch (alt173) {
            case 1 :
                // InternalCompleteOCL.g:5604:7: (otherlv_8= 'with' | otherlv_9= '<-' ) ( (lv_ownedCoIterator_10_0= ruleCoIteratorVariableCS ) )
                {
                // InternalCompleteOCL.g:5604:7: (otherlv_8= 'with' | otherlv_9= '<-' )
                int alt172=2;
                int LA172_0 = input.LA(1);

                if ( (LA172_0==86) ) {
                    alt172=1;
                }
                else if ( (LA172_0==87) ) {
                    alt172=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 172, 0, input);

                    throw nvae;
                }
                switch (alt172) {
                    case 1 :
                        // InternalCompleteOCL.g:5605:8: otherlv_8= 'with'
                        {
                        otherlv_8=(Token)match(input,86,FollowSets000.FOLLOW_15); if (state.failed) return ;

                        }
                        break;
                    case 2 :
                        // InternalCompleteOCL.g:5610:8: otherlv_9= '<-'
                        {
                        otherlv_9=(Token)match(input,87,FollowSets000.FOLLOW_15); if (state.failed) return ;

                        }
                        break;

                }

                // InternalCompleteOCL.g:5615:7: ( (lv_ownedCoIterator_10_0= ruleCoIteratorVariableCS ) )
                // InternalCompleteOCL.g:5616:8: (lv_ownedCoIterator_10_0= ruleCoIteratorVariableCS )
                {
                // InternalCompleteOCL.g:5616:8: (lv_ownedCoIterator_10_0= ruleCoIteratorVariableCS )
                // InternalCompleteOCL.g:5617:9: lv_ownedCoIterator_10_0= ruleCoIteratorVariableCS
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

        // InternalCompleteOCL.g:5635:6: (otherlv_11= '=' ( (lv_ownedInitExpression_12_0= ruleExpCS ) ) )?
        int alt174=2;
        int LA174_0 = input.LA(1);

        if ( (LA174_0==29) ) {
            alt174=1;
        }
        switch (alt174) {
            case 1 :
                // InternalCompleteOCL.g:5636:7: otherlv_11= '=' ( (lv_ownedInitExpression_12_0= ruleExpCS ) )
                {
                otherlv_11=(Token)match(input,29,FollowSets000.FOLLOW_40); if (state.failed) return ;
                // InternalCompleteOCL.g:5640:7: ( (lv_ownedInitExpression_12_0= ruleExpCS ) )
                // InternalCompleteOCL.g:5641:8: (lv_ownedInitExpression_12_0= ruleExpCS )
                {
                // InternalCompleteOCL.g:5641:8: (lv_ownedInitExpression_12_0= ruleExpCS )
                // InternalCompleteOCL.g:5642:9: lv_ownedInitExpression_12_0= ruleExpCS
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


        // InternalCompleteOCL.g:5662:5: ( ( (otherlv_13= ':' ( (lv_ownedType_14_0= ruleTypeExpCS ) ) )? ( (otherlv_15= 'with' | otherlv_16= '<-' ) ( (lv_ownedCoIterator_17_0= ruleCoIteratorVariableCS ) ) )? otherlv_18= 'in' ( (lv_ownedInitExpression_19_0= ruleExpCS ) ) ) )
        // InternalCompleteOCL.g:5662:5: ( (otherlv_13= ':' ( (lv_ownedType_14_0= ruleTypeExpCS ) ) )? ( (otherlv_15= 'with' | otherlv_16= '<-' ) ( (lv_ownedCoIterator_17_0= ruleCoIteratorVariableCS ) ) )? otherlv_18= 'in' ( (lv_ownedInitExpression_19_0= ruleExpCS ) ) )
        {
        // InternalCompleteOCL.g:5662:5: ( (otherlv_13= ':' ( (lv_ownedType_14_0= ruleTypeExpCS ) ) )? ( (otherlv_15= 'with' | otherlv_16= '<-' ) ( (lv_ownedCoIterator_17_0= ruleCoIteratorVariableCS ) ) )? otherlv_18= 'in' ( (lv_ownedInitExpression_19_0= ruleExpCS ) ) )
        // InternalCompleteOCL.g:5663:6: (otherlv_13= ':' ( (lv_ownedType_14_0= ruleTypeExpCS ) ) )? ( (otherlv_15= 'with' | otherlv_16= '<-' ) ( (lv_ownedCoIterator_17_0= ruleCoIteratorVariableCS ) ) )? otherlv_18= 'in' ( (lv_ownedInitExpression_19_0= ruleExpCS ) )
        {
        // InternalCompleteOCL.g:5663:6: (otherlv_13= ':' ( (lv_ownedType_14_0= ruleTypeExpCS ) ) )?
        int alt176=2;
        int LA176_0 = input.LA(1);

        if ( (LA176_0==23) ) {
            alt176=1;
        }
        switch (alt176) {
            case 1 :
                // InternalCompleteOCL.g:5664:7: otherlv_13= ':' ( (lv_ownedType_14_0= ruleTypeExpCS ) )
                {
                otherlv_13=(Token)match(input,23,FollowSets000.FOLLOW_21); if (state.failed) return ;
                // InternalCompleteOCL.g:5668:7: ( (lv_ownedType_14_0= ruleTypeExpCS ) )
                // InternalCompleteOCL.g:5669:8: (lv_ownedType_14_0= ruleTypeExpCS )
                {
                // InternalCompleteOCL.g:5669:8: (lv_ownedType_14_0= ruleTypeExpCS )
                // InternalCompleteOCL.g:5670:9: lv_ownedType_14_0= ruleTypeExpCS
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

        // InternalCompleteOCL.g:5688:6: ( (otherlv_15= 'with' | otherlv_16= '<-' ) ( (lv_ownedCoIterator_17_0= ruleCoIteratorVariableCS ) ) )?
        int alt178=2;
        int LA178_0 = input.LA(1);

        if ( ((LA178_0>=86 && LA178_0<=87)) ) {
            alt178=1;
        }
        switch (alt178) {
            case 1 :
                // InternalCompleteOCL.g:5689:7: (otherlv_15= 'with' | otherlv_16= '<-' ) ( (lv_ownedCoIterator_17_0= ruleCoIteratorVariableCS ) )
                {
                // InternalCompleteOCL.g:5689:7: (otherlv_15= 'with' | otherlv_16= '<-' )
                int alt177=2;
                int LA177_0 = input.LA(1);

                if ( (LA177_0==86) ) {
                    alt177=1;
                }
                else if ( (LA177_0==87) ) {
                    alt177=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 177, 0, input);

                    throw nvae;
                }
                switch (alt177) {
                    case 1 :
                        // InternalCompleteOCL.g:5690:8: otherlv_15= 'with'
                        {
                        otherlv_15=(Token)match(input,86,FollowSets000.FOLLOW_15); if (state.failed) return ;

                        }
                        break;
                    case 2 :
                        // InternalCompleteOCL.g:5695:8: otherlv_16= '<-'
                        {
                        otherlv_16=(Token)match(input,87,FollowSets000.FOLLOW_15); if (state.failed) return ;

                        }
                        break;

                }

                // InternalCompleteOCL.g:5700:7: ( (lv_ownedCoIterator_17_0= ruleCoIteratorVariableCS ) )
                // InternalCompleteOCL.g:5701:8: (lv_ownedCoIterator_17_0= ruleCoIteratorVariableCS )
                {
                // InternalCompleteOCL.g:5701:8: (lv_ownedCoIterator_17_0= ruleCoIteratorVariableCS )
                // InternalCompleteOCL.g:5702:9: lv_ownedCoIterator_17_0= ruleCoIteratorVariableCS
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

        otherlv_18=(Token)match(input,95,FollowSets000.FOLLOW_40); if (state.failed) return ;
        // InternalCompleteOCL.g:5724:6: ( (lv_ownedInitExpression_19_0= ruleExpCS ) )
        // InternalCompleteOCL.g:5725:7: (lv_ownedInitExpression_19_0= ruleExpCS )
        {
        // InternalCompleteOCL.g:5725:7: (lv_ownedInitExpression_19_0= ruleExpCS )
        // InternalCompleteOCL.g:5726:8: lv_ownedInitExpression_19_0= ruleExpCS
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


        // InternalCompleteOCL.g:5927:4: ( ( (otherlv_2= 'with' | otherlv_3= '<-' ) ( (lv_ownedCoIterator_4_0= ruleCoIteratorVariableCS ) ) (otherlv_5= '=' ( (lv_ownedInitExpression_6_0= ruleExpCS ) ) )? ) )
        // InternalCompleteOCL.g:5927:4: ( (otherlv_2= 'with' | otherlv_3= '<-' ) ( (lv_ownedCoIterator_4_0= ruleCoIteratorVariableCS ) ) (otherlv_5= '=' ( (lv_ownedInitExpression_6_0= ruleExpCS ) ) )? )
        {
        // InternalCompleteOCL.g:5927:4: ( (otherlv_2= 'with' | otherlv_3= '<-' ) ( (lv_ownedCoIterator_4_0= ruleCoIteratorVariableCS ) ) (otherlv_5= '=' ( (lv_ownedInitExpression_6_0= ruleExpCS ) ) )? )
        // InternalCompleteOCL.g:5928:5: (otherlv_2= 'with' | otherlv_3= '<-' ) ( (lv_ownedCoIterator_4_0= ruleCoIteratorVariableCS ) ) (otherlv_5= '=' ( (lv_ownedInitExpression_6_0= ruleExpCS ) ) )?
        {
        // InternalCompleteOCL.g:5928:5: (otherlv_2= 'with' | otherlv_3= '<-' )
        int alt189=2;
        int LA189_0 = input.LA(1);

        if ( (LA189_0==86) ) {
            alt189=1;
        }
        else if ( (LA189_0==87) ) {
            alt189=2;
        }
        else {
            if (state.backtracking>0) {state.failed=true; return ;}
            NoViableAltException nvae =
                new NoViableAltException("", 189, 0, input);

            throw nvae;
        }
        switch (alt189) {
            case 1 :
                // InternalCompleteOCL.g:5929:6: otherlv_2= 'with'
                {
                otherlv_2=(Token)match(input,86,FollowSets000.FOLLOW_15); if (state.failed) return ;

                }
                break;
            case 2 :
                // InternalCompleteOCL.g:5934:6: otherlv_3= '<-'
                {
                otherlv_3=(Token)match(input,87,FollowSets000.FOLLOW_15); if (state.failed) return ;

                }
                break;

        }

        // InternalCompleteOCL.g:5939:5: ( (lv_ownedCoIterator_4_0= ruleCoIteratorVariableCS ) )
        // InternalCompleteOCL.g:5940:6: (lv_ownedCoIterator_4_0= ruleCoIteratorVariableCS )
        {
        // InternalCompleteOCL.g:5940:6: (lv_ownedCoIterator_4_0= ruleCoIteratorVariableCS )
        // InternalCompleteOCL.g:5941:7: lv_ownedCoIterator_4_0= ruleCoIteratorVariableCS
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

        // InternalCompleteOCL.g:5958:5: (otherlv_5= '=' ( (lv_ownedInitExpression_6_0= ruleExpCS ) ) )?
        int alt190=2;
        int LA190_0 = input.LA(1);

        if ( (LA190_0==29) ) {
            alt190=1;
        }
        switch (alt190) {
            case 1 :
                // InternalCompleteOCL.g:5959:6: otherlv_5= '=' ( (lv_ownedInitExpression_6_0= ruleExpCS ) )
                {
                otherlv_5=(Token)match(input,29,FollowSets000.FOLLOW_40); if (state.failed) return ;
                // InternalCompleteOCL.g:5963:6: ( (lv_ownedInitExpression_6_0= ruleExpCS ) )
                // InternalCompleteOCL.g:5964:7: (lv_ownedInitExpression_6_0= ruleExpCS )
                {
                // InternalCompleteOCL.g:5964:7: (lv_ownedInitExpression_6_0= ruleExpCS )
                // InternalCompleteOCL.g:5965:8: lv_ownedInitExpression_6_0= ruleExpCS
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


        // InternalCompleteOCL.g:5985:4: ( (otherlv_7= ':' ( (lv_ownedType_8_0= ruleTypeExpCS ) ) ( (otherlv_9= 'with' | otherlv_10= '<-' ) ( (lv_ownedCoIterator_11_0= ruleCoIteratorVariableCS ) ) )? (otherlv_12= '=' ( (lv_ownedInitExpression_13_0= ruleExpCS ) ) )? ) )
        // InternalCompleteOCL.g:5985:4: (otherlv_7= ':' ( (lv_ownedType_8_0= ruleTypeExpCS ) ) ( (otherlv_9= 'with' | otherlv_10= '<-' ) ( (lv_ownedCoIterator_11_0= ruleCoIteratorVariableCS ) ) )? (otherlv_12= '=' ( (lv_ownedInitExpression_13_0= ruleExpCS ) ) )? )
        {
        // InternalCompleteOCL.g:5985:4: (otherlv_7= ':' ( (lv_ownedType_8_0= ruleTypeExpCS ) ) ( (otherlv_9= 'with' | otherlv_10= '<-' ) ( (lv_ownedCoIterator_11_0= ruleCoIteratorVariableCS ) ) )? (otherlv_12= '=' ( (lv_ownedInitExpression_13_0= ruleExpCS ) ) )? )
        // InternalCompleteOCL.g:5986:5: otherlv_7= ':' ( (lv_ownedType_8_0= ruleTypeExpCS ) ) ( (otherlv_9= 'with' | otherlv_10= '<-' ) ( (lv_ownedCoIterator_11_0= ruleCoIteratorVariableCS ) ) )? (otherlv_12= '=' ( (lv_ownedInitExpression_13_0= ruleExpCS ) ) )?
        {
        otherlv_7=(Token)match(input,23,FollowSets000.FOLLOW_21); if (state.failed) return ;
        // InternalCompleteOCL.g:5990:5: ( (lv_ownedType_8_0= ruleTypeExpCS ) )
        // InternalCompleteOCL.g:5991:6: (lv_ownedType_8_0= ruleTypeExpCS )
        {
        // InternalCompleteOCL.g:5991:6: (lv_ownedType_8_0= ruleTypeExpCS )
        // InternalCompleteOCL.g:5992:7: lv_ownedType_8_0= ruleTypeExpCS
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

        // InternalCompleteOCL.g:6009:5: ( (otherlv_9= 'with' | otherlv_10= '<-' ) ( (lv_ownedCoIterator_11_0= ruleCoIteratorVariableCS ) ) )?
        int alt193=2;
        int LA193_0 = input.LA(1);

        if ( ((LA193_0>=86 && LA193_0<=87)) ) {
            alt193=1;
        }
        switch (alt193) {
            case 1 :
                // InternalCompleteOCL.g:6010:6: (otherlv_9= 'with' | otherlv_10= '<-' ) ( (lv_ownedCoIterator_11_0= ruleCoIteratorVariableCS ) )
                {
                // InternalCompleteOCL.g:6010:6: (otherlv_9= 'with' | otherlv_10= '<-' )
                int alt192=2;
                int LA192_0 = input.LA(1);

                if ( (LA192_0==86) ) {
                    alt192=1;
                }
                else if ( (LA192_0==87) ) {
                    alt192=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 192, 0, input);

                    throw nvae;
                }
                switch (alt192) {
                    case 1 :
                        // InternalCompleteOCL.g:6011:7: otherlv_9= 'with'
                        {
                        otherlv_9=(Token)match(input,86,FollowSets000.FOLLOW_15); if (state.failed) return ;

                        }
                        break;
                    case 2 :
                        // InternalCompleteOCL.g:6016:7: otherlv_10= '<-'
                        {
                        otherlv_10=(Token)match(input,87,FollowSets000.FOLLOW_15); if (state.failed) return ;

                        }
                        break;

                }

                // InternalCompleteOCL.g:6021:6: ( (lv_ownedCoIterator_11_0= ruleCoIteratorVariableCS ) )
                // InternalCompleteOCL.g:6022:7: (lv_ownedCoIterator_11_0= ruleCoIteratorVariableCS )
                {
                // InternalCompleteOCL.g:6022:7: (lv_ownedCoIterator_11_0= ruleCoIteratorVariableCS )
                // InternalCompleteOCL.g:6023:8: lv_ownedCoIterator_11_0= ruleCoIteratorVariableCS
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

        // InternalCompleteOCL.g:6041:5: (otherlv_12= '=' ( (lv_ownedInitExpression_13_0= ruleExpCS ) ) )?
        int alt194=2;
        int LA194_0 = input.LA(1);

        if ( (LA194_0==29) ) {
            alt194=1;
        }
        switch (alt194) {
            case 1 :
                // InternalCompleteOCL.g:6042:6: otherlv_12= '=' ( (lv_ownedInitExpression_13_0= ruleExpCS ) )
                {
                otherlv_12=(Token)match(input,29,FollowSets000.FOLLOW_40); if (state.failed) return ;
                // InternalCompleteOCL.g:6046:6: ( (lv_ownedInitExpression_13_0= ruleExpCS ) )
                // InternalCompleteOCL.g:6047:7: (lv_ownedInitExpression_13_0= ruleExpCS )
                {
                // InternalCompleteOCL.g:6047:7: (lv_ownedInitExpression_13_0= ruleExpCS )
                // InternalCompleteOCL.g:6048:8: lv_ownedInitExpression_13_0= ruleExpCS
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


        // InternalCompleteOCL.g:6068:4: ( ( (otherlv_14= ':' ( (lv_ownedType_15_0= ruleTypeExpCS ) ) )? ( (otherlv_16= 'with' | otherlv_17= '<-' ) ( (lv_ownedCoIterator_18_0= ruleCoIteratorVariableCS ) ) )? otherlv_19= 'in' ( (lv_ownedInitExpression_20_0= ruleExpCS ) ) ) )
        // InternalCompleteOCL.g:6068:4: ( (otherlv_14= ':' ( (lv_ownedType_15_0= ruleTypeExpCS ) ) )? ( (otherlv_16= 'with' | otherlv_17= '<-' ) ( (lv_ownedCoIterator_18_0= ruleCoIteratorVariableCS ) ) )? otherlv_19= 'in' ( (lv_ownedInitExpression_20_0= ruleExpCS ) ) )
        {
        // InternalCompleteOCL.g:6068:4: ( (otherlv_14= ':' ( (lv_ownedType_15_0= ruleTypeExpCS ) ) )? ( (otherlv_16= 'with' | otherlv_17= '<-' ) ( (lv_ownedCoIterator_18_0= ruleCoIteratorVariableCS ) ) )? otherlv_19= 'in' ( (lv_ownedInitExpression_20_0= ruleExpCS ) ) )
        // InternalCompleteOCL.g:6069:5: (otherlv_14= ':' ( (lv_ownedType_15_0= ruleTypeExpCS ) ) )? ( (otherlv_16= 'with' | otherlv_17= '<-' ) ( (lv_ownedCoIterator_18_0= ruleCoIteratorVariableCS ) ) )? otherlv_19= 'in' ( (lv_ownedInitExpression_20_0= ruleExpCS ) )
        {
        // InternalCompleteOCL.g:6069:5: (otherlv_14= ':' ( (lv_ownedType_15_0= ruleTypeExpCS ) ) )?
        int alt196=2;
        int LA196_0 = input.LA(1);

        if ( (LA196_0==23) ) {
            alt196=1;
        }
        switch (alt196) {
            case 1 :
                // InternalCompleteOCL.g:6070:6: otherlv_14= ':' ( (lv_ownedType_15_0= ruleTypeExpCS ) )
                {
                otherlv_14=(Token)match(input,23,FollowSets000.FOLLOW_21); if (state.failed) return ;
                // InternalCompleteOCL.g:6074:6: ( (lv_ownedType_15_0= ruleTypeExpCS ) )
                // InternalCompleteOCL.g:6075:7: (lv_ownedType_15_0= ruleTypeExpCS )
                {
                // InternalCompleteOCL.g:6075:7: (lv_ownedType_15_0= ruleTypeExpCS )
                // InternalCompleteOCL.g:6076:8: lv_ownedType_15_0= ruleTypeExpCS
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

        // InternalCompleteOCL.g:6094:5: ( (otherlv_16= 'with' | otherlv_17= '<-' ) ( (lv_ownedCoIterator_18_0= ruleCoIteratorVariableCS ) ) )?
        int alt198=2;
        int LA198_0 = input.LA(1);

        if ( ((LA198_0>=86 && LA198_0<=87)) ) {
            alt198=1;
        }
        switch (alt198) {
            case 1 :
                // InternalCompleteOCL.g:6095:6: (otherlv_16= 'with' | otherlv_17= '<-' ) ( (lv_ownedCoIterator_18_0= ruleCoIteratorVariableCS ) )
                {
                // InternalCompleteOCL.g:6095:6: (otherlv_16= 'with' | otherlv_17= '<-' )
                int alt197=2;
                int LA197_0 = input.LA(1);

                if ( (LA197_0==86) ) {
                    alt197=1;
                }
                else if ( (LA197_0==87) ) {
                    alt197=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 197, 0, input);

                    throw nvae;
                }
                switch (alt197) {
                    case 1 :
                        // InternalCompleteOCL.g:6096:7: otherlv_16= 'with'
                        {
                        otherlv_16=(Token)match(input,86,FollowSets000.FOLLOW_15); if (state.failed) return ;

                        }
                        break;
                    case 2 :
                        // InternalCompleteOCL.g:6101:7: otherlv_17= '<-'
                        {
                        otherlv_17=(Token)match(input,87,FollowSets000.FOLLOW_15); if (state.failed) return ;

                        }
                        break;

                }

                // InternalCompleteOCL.g:6106:6: ( (lv_ownedCoIterator_18_0= ruleCoIteratorVariableCS ) )
                // InternalCompleteOCL.g:6107:7: (lv_ownedCoIterator_18_0= ruleCoIteratorVariableCS )
                {
                // InternalCompleteOCL.g:6107:7: (lv_ownedCoIterator_18_0= ruleCoIteratorVariableCS )
                // InternalCompleteOCL.g:6108:8: lv_ownedCoIterator_18_0= ruleCoIteratorVariableCS
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

        otherlv_19=(Token)match(input,95,FollowSets000.FOLLOW_40); if (state.failed) return ;
        // InternalCompleteOCL.g:6130:5: ( (lv_ownedInitExpression_20_0= ruleExpCS ) )
        // InternalCompleteOCL.g:6131:6: (lv_ownedInitExpression_20_0= ruleExpCS )
        {
        // InternalCompleteOCL.g:6131:6: (lv_ownedInitExpression_20_0= ruleExpCS )
        // InternalCompleteOCL.g:6132:7: lv_ownedInitExpression_20_0= ruleExpCS
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
    protected DFA86 dfa86 = new DFA86(this);
    protected DFA89 dfa89 = new DFA89(this);
    protected DFA92 dfa92 = new DFA92(this);
    protected DFA111 dfa111 = new DFA111(this);
    protected DFA123 dfa123 = new DFA123(this);
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

    class DFA86 extends DFA {

        public DFA86(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 86;
            this.eot = dfa_7;
            this.eof = dfa_7;
            this.min = dfa_8;
            this.max = dfa_9;
            this.accept = dfa_10;
            this.special = dfa_11;
            this.transition = dfa_12;
        }
        public String getDescription() {
            return "4622:2: (this_TypeNameExpCS_0= ruleTypeNameExpCS | this_TypeLiteralCS_1= ruleTypeLiteralCS | this_CollectionPatternCS_2= ruleCollectionPatternCS )";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 :
                        int LA86_16 = input.LA(1);


                        int index86_16 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred134_InternalCompleteOCL()) ) {s = 6;}

                        else if ( (true) ) {s = 23;}


                        input.seek(index86_16);
                        if ( s>=0 ) return s;
                        break;
                    case 1 :
                        int LA86_17 = input.LA(1);


                        int index86_17 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred134_InternalCompleteOCL()) ) {s = 6;}

                        else if ( (true) ) {s = 23;}


                        input.seek(index86_17);
                        if ( s>=0 ) return s;
                        break;
                    case 2 :
                        int LA86_18 = input.LA(1);


                        int index86_18 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred134_InternalCompleteOCL()) ) {s = 6;}

                        else if ( (true) ) {s = 23;}


                        input.seek(index86_18);
                        if ( s>=0 ) return s;
                        break;
                    case 3 :
                        int LA86_19 = input.LA(1);


                        int index86_19 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred134_InternalCompleteOCL()) ) {s = 6;}

                        else if ( (true) ) {s = 23;}


                        input.seek(index86_19);
                        if ( s>=0 ) return s;
                        break;
                    case 4 :
                        int LA86_20 = input.LA(1);


                        int index86_20 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred134_InternalCompleteOCL()) ) {s = 6;}

                        else if ( (true) ) {s = 23;}


                        input.seek(index86_20);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 86, _s, input);
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

    class DFA89 extends DFA {

        public DFA89(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 89;
            this.eot = dfa_13;
            this.eof = dfa_13;
            this.min = dfa_14;
            this.max = dfa_15;
            this.accept = dfa_16;
            this.special = dfa_17;
            this.transition = dfa_18;
        }
        public String getDescription() {
            return "4725:2: ( (this_PrefixedPrimaryExpCS_0= rulePrefixedPrimaryExpCS ( () ( (lv_name_2_0= ruleBinaryOperatorName ) ) ( (lv_ownedRight_3_0= ruleExpCS ) ) )? ) | this_PrefixedLetExpCS_4= rulePrefixedLetExpCS )";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 :
                        int LA89_1 = input.LA(1);


                        int index89_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred137_InternalCompleteOCL()) ) {s = 4;}

                        else if ( (true) ) {s = 37;}


                        input.seek(index89_1);
                        if ( s>=0 ) return s;
                        break;
                    case 1 :
                        int LA89_2 = input.LA(1);


                        int index89_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred137_InternalCompleteOCL()) ) {s = 4;}

                        else if ( (true) ) {s = 37;}


                        input.seek(index89_2);
                        if ( s>=0 ) return s;
                        break;
                    case 2 :
                        int LA89_3 = input.LA(1);


                        int index89_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred137_InternalCompleteOCL()) ) {s = 4;}

                        else if ( (true) ) {s = 37;}


                        input.seek(index89_3);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 89, _s, input);
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

    class DFA92 extends DFA {

        public DFA92(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 92;
            this.eot = dfa_19;
            this.eof = dfa_19;
            this.min = dfa_20;
            this.max = dfa_21;
            this.accept = dfa_22;
            this.special = dfa_23;
            this.transition = dfa_24;
        }
        public String getDescription() {
            return "4981:2: (this_NestedExpCS_0= ruleNestedExpCS | this_IfExpCS_1= ruleIfExpCS | this_SelfExpCS_2= ruleSelfExpCS | this_PrimitiveLiteralExpCS_3= rulePrimitiveLiteralExpCS | this_TupleLiteralExpCS_4= ruleTupleLiteralExpCS | this_MapLiteralExpCS_5= ruleMapLiteralExpCS | this_CollectionLiteralExpCS_6= ruleCollectionLiteralExpCS | this_LambdaLiteralExpCS_7= ruleLambdaLiteralExpCS | this_TypeLiteralExpCS_8= ruleTypeLiteralExpCS | this_NameExpCS_9= ruleNameExpCS )";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 :
                        int LA92_11 = input.LA(1);


                        int index92_11 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred144_InternalCompleteOCL()) ) {s = 34;}

                        else if ( (synpred148_InternalCompleteOCL()) ) {s = 19;}


                        input.seek(index92_11);
                        if ( s>=0 ) return s;
                        break;
                    case 1 :
                        int LA92_12 = input.LA(1);


                        int index92_12 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred145_InternalCompleteOCL()) ) {s = 35;}

                        else if ( (synpred148_InternalCompleteOCL()) ) {s = 19;}


                        input.seek(index92_12);
                        if ( s>=0 ) return s;
                        break;
                    case 2 :
                        int LA92_13 = input.LA(1);


                        int index92_13 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred146_InternalCompleteOCL()) ) {s = 36;}

                        else if ( (synpred148_InternalCompleteOCL()) ) {s = 19;}


                        input.seek(index92_13);
                        if ( s>=0 ) return s;
                        break;
                    case 3 :
                        int LA92_14 = input.LA(1);


                        int index92_14 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred146_InternalCompleteOCL()) ) {s = 36;}

                        else if ( (synpred148_InternalCompleteOCL()) ) {s = 19;}


                        input.seek(index92_14);
                        if ( s>=0 ) return s;
                        break;
                    case 4 :
                        int LA92_15 = input.LA(1);


                        int index92_15 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred146_InternalCompleteOCL()) ) {s = 36;}

                        else if ( (synpred148_InternalCompleteOCL()) ) {s = 19;}


                        input.seek(index92_15);
                        if ( s>=0 ) return s;
                        break;
                    case 5 :
                        int LA92_16 = input.LA(1);


                        int index92_16 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred146_InternalCompleteOCL()) ) {s = 36;}

                        else if ( (synpred148_InternalCompleteOCL()) ) {s = 19;}


                        input.seek(index92_16);
                        if ( s>=0 ) return s;
                        break;
                    case 6 :
                        int LA92_17 = input.LA(1);


                        int index92_17 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred146_InternalCompleteOCL()) ) {s = 36;}

                        else if ( (synpred148_InternalCompleteOCL()) ) {s = 19;}


                        input.seek(index92_17);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 92, _s, input);
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

    class DFA111 extends DFA {

        public DFA111(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 111;
            this.eot = dfa_25;
            this.eof = dfa_26;
            this.min = dfa_27;
            this.max = dfa_28;
            this.accept = dfa_29;
            this.special = dfa_30;
            this.transition = dfa_31;
        }
        public String getDescription() {
            return "5520:4: ( ( (otherlv_1= 'with' | otherlv_2= '<-' ) ( (lv_ownedCoIterator_3_0= ruleCoIteratorVariableCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? ) | (otherlv_6= ':' ( (lv_ownedType_7_0= ruleTypeExpCS ) ) ( (otherlv_8= 'with' | otherlv_9= '<-' ) ( (lv_ownedCoIterator_10_0= ruleCoIteratorVariableCS ) ) )? (otherlv_11= '=' ( (lv_ownedInitExpression_12_0= ruleExpCS ) ) )? ) | ( (otherlv_13= ':' ( (lv_ownedType_14_0= ruleTypeExpCS ) ) )? ( (otherlv_15= 'with' | otherlv_16= '<-' ) ( (lv_ownedCoIterator_17_0= ruleCoIteratorVariableCS ) ) )? otherlv_18= 'in' ( (lv_ownedInitExpression_19_0= ruleExpCS ) ) ) )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 :
                        int LA111_1 = input.LA(1);


                        int index111_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred162_InternalCompleteOCL()) ) {s = 10;}

                        else if ( (synpred170_InternalCompleteOCL()) ) {s = 4;}


                        input.seek(index111_1);
                        if ( s>=0 ) return s;
                        break;
                    case 1 :
                        int LA111_2 = input.LA(1);


                        int index111_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred162_InternalCompleteOCL()) ) {s = 10;}

                        else if ( (synpred170_InternalCompleteOCL()) ) {s = 4;}


                        input.seek(index111_2);
                        if ( s>=0 ) return s;
                        break;
                    case 2 :
                        int LA111_3 = input.LA(1);


                        int index111_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred166_InternalCompleteOCL()) ) {s = 11;}

                        else if ( (synpred170_InternalCompleteOCL()) ) {s = 4;}


                        input.seek(index111_3);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 111, _s, input);
            error(nvae);
            throw nvae;
        }
    }

    class DFA123 extends DFA {

        public DFA123(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 123;
            this.eot = dfa_25;
            this.eof = dfa_26;
            this.min = dfa_27;
            this.max = dfa_28;
            this.accept = dfa_29;
            this.special = dfa_30;
            this.transition = dfa_31;
        }
        public String getDescription() {
            return "5926:3: ( ( (otherlv_2= 'with' | otherlv_3= '<-' ) ( (lv_ownedCoIterator_4_0= ruleCoIteratorVariableCS ) ) (otherlv_5= '=' ( (lv_ownedInitExpression_6_0= ruleExpCS ) ) )? ) | (otherlv_7= ':' ( (lv_ownedType_8_0= ruleTypeExpCS ) ) ( (otherlv_9= 'with' | otherlv_10= '<-' ) ( (lv_ownedCoIterator_11_0= ruleCoIteratorVariableCS ) ) )? (otherlv_12= '=' ( (lv_ownedInitExpression_13_0= ruleExpCS ) ) )? ) | ( (otherlv_14= ':' ( (lv_ownedType_15_0= ruleTypeExpCS ) ) )? ( (otherlv_16= 'with' | otherlv_17= '<-' ) ( (lv_ownedCoIterator_18_0= ruleCoIteratorVariableCS ) ) )? otherlv_19= 'in' ( (lv_ownedInitExpression_20_0= ruleExpCS ) ) ) )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 :
                        int LA123_1 = input.LA(1);


                        int index123_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred176_InternalCompleteOCL()) ) {s = 10;}

                        else if ( (synpred184_InternalCompleteOCL()) ) {s = 4;}


                        input.seek(index123_1);
                        if ( s>=0 ) return s;
                        break;
                    case 1 :
                        int LA123_2 = input.LA(1);


                        int index123_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred176_InternalCompleteOCL()) ) {s = 10;}

                        else if ( (synpred184_InternalCompleteOCL()) ) {s = 4;}


                        input.seek(index123_2);
                        if ( s>=0 ) return s;
                        break;
                    case 2 :
                        int LA123_3 = input.LA(1);


                        int index123_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred180_InternalCompleteOCL()) ) {s = 11;}

                        else if ( (synpred184_InternalCompleteOCL()) ) {s = 4;}


                        input.seek(index123_3);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 123, _s, input);
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
        public static final BitSet FOLLOW_25 = new BitSet(new long[]{0x00FFFC11C32001E2L,0x000001040F21F600L});
        public static final BitSet FOLLOW_26 = new BitSet(new long[]{0x0000001003000002L});
        public static final BitSet FOLLOW_27 = new BitSet(new long[]{0x0000000C00180000L});
        public static final BitSet FOLLOW_28 = new BitSet(new long[]{0x0000000C00080000L});
        public static final BitSet FOLLOW_29 = new BitSet(new long[]{0x0000006000000002L});
        public static final BitSet FOLLOW_30 = new BitSet(new long[]{0x0000010010000000L});
        public static final BitSet FOLLOW_31 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000800L});
        public static final BitSet FOLLOW_32 = new BitSet(new long[]{0x0000000000200002L});
        public static final BitSet FOLLOW_33 = new BitSet(new long[]{0x0000000000400000L,0x0000000020000000L});
        public static final BitSet FOLLOW_34 = new BitSet(new long[]{0x0000000010000000L});
        public static final BitSet FOLLOW_35 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
        public static final BitSet FOLLOW_36 = new BitSet(new long[]{0x00FFFC01C0A001E0L,0x000001840F25F600L});
        public static final BitSet FOLLOW_37 = new BitSet(new long[]{0x0000000010000000L,0x0000000000040000L});
        public static final BitSet FOLLOW_38 = new BitSet(new long[]{0x00FFFC01C0A001E0L,0x000001840F21F600L});
        public static final BitSet FOLLOW_39 = new BitSet(new long[]{0x0000000000000002L,0x0000000000080000L});
        public static final BitSet FOLLOW_40 = new BitSet(new long[]{0x00FFFC01C02001E0L,0x000001840F21F600L});
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
