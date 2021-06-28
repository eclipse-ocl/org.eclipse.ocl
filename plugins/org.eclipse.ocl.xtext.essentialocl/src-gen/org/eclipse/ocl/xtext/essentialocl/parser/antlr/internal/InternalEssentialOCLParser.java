package org.eclipse.ocl.xtext.essentialocl.parser.antlr.internal;

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import org.eclipse.ocl.xtext.essentialocl.services.EssentialOCLGrammarAccess;



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
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
@SuppressWarnings("all")
public class InternalEssentialOCLParser extends org.eclipse.ocl.xtext.base.utilities.CompatibilityAbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_SIMPLE_ID", "RULE_ESCAPED_ID", "RULE_INT", "RULE_SINGLE_QUOTED_STRING", "RULE_ESCAPED_CHARACTER", "RULE_LETTER_CHARACTER", "RULE_DOUBLE_QUOTED_STRING", "RULE_ML_SINGLE_QUOTED_STRING", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'-'", "'not'", "'not2'", "'*'", "'/'", "'+'", "'>'", "'<'", "'>='", "'<='", "'='", "'<>'", "'and'", "'and2'", "'implies'", "'implies2'", "'or'", "'or2'", "'xor'", "'xor2'", "'.'", "'->'", "'?.'", "'?->'", "'Map'", "'Tuple'", "'Boolean'", "'Integer'", "'Real'", "'String'", "'UnlimitedNatural'", "'OclAny'", "'OclInvalid'", "'OclVoid'", "'Set'", "'Bag'", "'Sequence'", "'Collection'", "'OrderedSet'", "'('", "')'", "','", "':'", "'{'", "'}'", "'..'", "'++'", "'Lambda'", "'<-'", "'true'", "'false'", "'invalid'", "'null'", "'@'", "'pre'", "'['", "']'", "'in'", "'|'", "';'", "'if'", "'then'", "'else'", "'endif'", "'elseif'", "'let'", "'self'", "'|?'", "'|1'", "'?'", "'::'", "'extends'", "'&&'"
    };
    public static final int T__50=50;
    public static final int RULE_LETTER_CHARACTER=9;
    public static final int T__19=19;
    public static final int T__59=59;
    public static final int T__16=16;
    public static final int T__17=17;
    public static final int T__18=18;
    public static final int T__55=55;
    public static final int RULE_ESCAPED_CHARACTER=8;
    public static final int T__56=56;
    public static final int T__57=57;
    public static final int T__58=58;
    public static final int T__51=51;
    public static final int RULE_ML_SINGLE_QUOTED_STRING=11;
    public static final int T__52=52;
    public static final int T__53=53;
    public static final int T__54=54;
    public static final int T__60=60;
    public static final int T__61=61;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int RULE_INT=6;
    public static final int T__29=29;
    public static final int T__22=22;
    public static final int T__66=66;
    public static final int RULE_ML_COMMENT=12;
    public static final int T__23=23;
    public static final int T__67=67;
    public static final int T__24=24;
    public static final int T__68=68;
    public static final int T__25=25;
    public static final int T__69=69;
    public static final int T__62=62;
    public static final int T__63=63;
    public static final int T__20=20;
    public static final int T__64=64;
    public static final int T__21=21;
    public static final int T__65=65;
    public static final int T__70=70;
    public static final int T__71=71;
    public static final int T__72=72;
    public static final int RULE_SL_COMMENT=13;
    public static final int T__37=37;
    public static final int T__38=38;
    public static final int T__39=39;
    public static final int T__33=33;
    public static final int T__77=77;
    public static final int T__34=34;
    public static final int T__78=78;
    public static final int T__35=35;
    public static final int T__79=79;
    public static final int T__36=36;
    public static final int T__73=73;
    public static final int EOF=-1;
    public static final int T__30=30;
    public static final int T__74=74;
    public static final int T__31=31;
    public static final int T__75=75;
    public static final int T__32=32;
    public static final int T__76=76;
    public static final int T__80=80;
    public static final int T__81=81;
    public static final int T__82=82;
    public static final int RULE_SIMPLE_ID=4;
    public static final int T__83=83;
    public static final int RULE_WS=14;
    public static final int RULE_ANY_OTHER=15;
    public static final int RULE_SINGLE_QUOTED_STRING=7;
    public static final int T__48=48;
    public static final int T__49=49;
    public static final int RULE_DOUBLE_QUOTED_STRING=10;
    public static final int T__44=44;
    public static final int T__88=88;
    public static final int T__45=45;
    public static final int T__46=46;
    public static final int T__47=47;
    public static final int RULE_ESCAPED_ID=5;
    public static final int T__40=40;
    public static final int T__84=84;
    public static final int T__41=41;
    public static final int T__85=85;
    public static final int T__42=42;
    public static final int T__86=86;
    public static final int T__43=43;
    public static final int T__87=87;

    // delegates
    // delegators


        public InternalEssentialOCLParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalEssentialOCLParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);

        }


    public String[] getTokenNames() { return InternalEssentialOCLParser.tokenNames; }
    public String getGrammarFileName() { return "InternalEssentialOCL.g"; }



    /*
      This grammar contains a lot of empty actions to work around a bug in ANTLR.
      Otherwise the ANTLR tool will create synpreds that cannot be compiled in some rare cases.
    */

     	private EssentialOCLGrammarAccess grammarAccess;

        public InternalEssentialOCLParser(TokenStream input, EssentialOCLGrammarAccess grammarAccess) {
            this(input);
            this.grammarAccess = grammarAccess;
            registerRules(grammarAccess.getGrammar());
        }

        @Override
        protected String getFirstRuleName() {
        	return "Model";
       	}

       	@Override
       	protected EssentialOCLGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}



    // $ANTLR start "entryRuleModel"
    // InternalEssentialOCL.g:80:1: entryRuleModel returns [EObject current=null] : iv_ruleModel= ruleModel EOF ;
    public final EObject entryRuleModel() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleModel = null;


        try {
            // InternalEssentialOCL.g:81:2: (iv_ruleModel= ruleModel EOF )
            // InternalEssentialOCL.g:82:2: iv_ruleModel= ruleModel EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getModelRule());
            }
            pushFollow(FollowSets000.FOLLOW_1);
            iv_ruleModel=ruleModel();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleModel;
            }
            match(input,EOF,FollowSets000.FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleModel"


    // $ANTLR start "ruleModel"
    // InternalEssentialOCL.g:89:1: ruleModel returns [EObject current=null] : ( (lv_ownedExpression_0_0= ruleExpCS ) ) ;
    public final EObject ruleModel() throws RecognitionException {
        EObject current = null;

        EObject lv_ownedExpression_0_0 = null;


         enterRule();

        try {
            // InternalEssentialOCL.g:92:28: ( ( (lv_ownedExpression_0_0= ruleExpCS ) ) )
            // InternalEssentialOCL.g:93:1: ( (lv_ownedExpression_0_0= ruleExpCS ) )
            {
            // InternalEssentialOCL.g:93:1: ( (lv_ownedExpression_0_0= ruleExpCS ) )
            // InternalEssentialOCL.g:94:1: (lv_ownedExpression_0_0= ruleExpCS )
            {
            // InternalEssentialOCL.g:94:1: (lv_ownedExpression_0_0= ruleExpCS )
            // InternalEssentialOCL.g:95:3: lv_ownedExpression_0_0= ruleExpCS
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getModelAccess().getOwnedExpressionExpCSParserRuleCall_0());

            }
            pushFollow(FollowSets000.FOLLOW_2);
            lv_ownedExpression_0_0=ruleExpCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getModelRule());
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

            if ( state.backtracking==0 ) {
               leaveRule();
            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleModel"


    // $ANTLR start "entryRuleEssentialOCLUnaryOperatorName"
    // InternalEssentialOCL.g:121:1: entryRuleEssentialOCLUnaryOperatorName returns [String current=null] : iv_ruleEssentialOCLUnaryOperatorName= ruleEssentialOCLUnaryOperatorName EOF ;
    public final String entryRuleEssentialOCLUnaryOperatorName() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleEssentialOCLUnaryOperatorName = null;


        try {
            // InternalEssentialOCL.g:122:2: (iv_ruleEssentialOCLUnaryOperatorName= ruleEssentialOCLUnaryOperatorName EOF )
            // InternalEssentialOCL.g:123:2: iv_ruleEssentialOCLUnaryOperatorName= ruleEssentialOCLUnaryOperatorName EOF
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
    // InternalEssentialOCL.g:130:1: ruleEssentialOCLUnaryOperatorName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= '-' | kw= 'not' | kw= 'not2' ) ;
    public final AntlrDatatypeRuleToken ruleEssentialOCLUnaryOperatorName() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule();

        try {
            // InternalEssentialOCL.g:133:28: ( (kw= '-' | kw= 'not' | kw= 'not2' ) )
            // InternalEssentialOCL.g:134:1: (kw= '-' | kw= 'not' | kw= 'not2' )
            {
            // InternalEssentialOCL.g:134:1: (kw= '-' | kw= 'not' | kw= 'not2' )
            int alt1=3;
            switch ( input.LA(1) ) {
            case 16:
                {
                alt1=1;
                }
                break;
            case 17:
                {
                alt1=2;
                }
                break;
            case 18:
                {
                alt1=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;
            }

            switch (alt1) {
                case 1 :
                    // InternalEssentialOCL.g:135:2: kw= '-'
                    {
                    kw=(Token)match(input,16,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getEssentialOCLUnaryOperatorNameAccess().getHyphenMinusKeyword_0());

                    }

                    }
                    break;
                case 2 :
                    // InternalEssentialOCL.g:142:2: kw= 'not'
                    {
                    kw=(Token)match(input,17,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getEssentialOCLUnaryOperatorNameAccess().getNotKeyword_1());

                    }

                    }
                    break;
                case 3 :
                    // InternalEssentialOCL.g:149:2: kw= 'not2'
                    {
                    kw=(Token)match(input,18,FollowSets000.FOLLOW_2); if (state.failed) return current;
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
    // InternalEssentialOCL.g:162:1: entryRuleEssentialOCLInfixOperatorName returns [String current=null] : iv_ruleEssentialOCLInfixOperatorName= ruleEssentialOCLInfixOperatorName EOF ;
    public final String entryRuleEssentialOCLInfixOperatorName() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleEssentialOCLInfixOperatorName = null;


        try {
            // InternalEssentialOCL.g:163:2: (iv_ruleEssentialOCLInfixOperatorName= ruleEssentialOCLInfixOperatorName EOF )
            // InternalEssentialOCL.g:164:2: iv_ruleEssentialOCLInfixOperatorName= ruleEssentialOCLInfixOperatorName EOF
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
    // InternalEssentialOCL.g:171:1: ruleEssentialOCLInfixOperatorName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= '*' | kw= '/' | kw= '+' | kw= '-' | kw= '>' | kw= '<' | kw= '>=' | kw= '<=' | kw= '=' | kw= '<>' | kw= 'and' | kw= 'and2' | kw= 'implies' | kw= 'implies2' | kw= 'or' | kw= 'or2' | kw= 'xor' | kw= 'xor2' ) ;
    public final AntlrDatatypeRuleToken ruleEssentialOCLInfixOperatorName() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule();

        try {
            // InternalEssentialOCL.g:174:28: ( (kw= '*' | kw= '/' | kw= '+' | kw= '-' | kw= '>' | kw= '<' | kw= '>=' | kw= '<=' | kw= '=' | kw= '<>' | kw= 'and' | kw= 'and2' | kw= 'implies' | kw= 'implies2' | kw= 'or' | kw= 'or2' | kw= 'xor' | kw= 'xor2' ) )
            // InternalEssentialOCL.g:175:1: (kw= '*' | kw= '/' | kw= '+' | kw= '-' | kw= '>' | kw= '<' | kw= '>=' | kw= '<=' | kw= '=' | kw= '<>' | kw= 'and' | kw= 'and2' | kw= 'implies' | kw= 'implies2' | kw= 'or' | kw= 'or2' | kw= 'xor' | kw= 'xor2' )
            {
            // InternalEssentialOCL.g:175:1: (kw= '*' | kw= '/' | kw= '+' | kw= '-' | kw= '>' | kw= '<' | kw= '>=' | kw= '<=' | kw= '=' | kw= '<>' | kw= 'and' | kw= 'and2' | kw= 'implies' | kw= 'implies2' | kw= 'or' | kw= 'or2' | kw= 'xor' | kw= 'xor2' )
            int alt2=18;
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
            case 16:
                {
                alt2=4;
                }
                break;
            case 22:
                {
                alt2=5;
                }
                break;
            case 23:
                {
                alt2=6;
                }
                break;
            case 24:
                {
                alt2=7;
                }
                break;
            case 25:
                {
                alt2=8;
                }
                break;
            case 26:
                {
                alt2=9;
                }
                break;
            case 27:
                {
                alt2=10;
                }
                break;
            case 28:
                {
                alt2=11;
                }
                break;
            case 29:
                {
                alt2=12;
                }
                break;
            case 30:
                {
                alt2=13;
                }
                break;
            case 31:
                {
                alt2=14;
                }
                break;
            case 32:
                {
                alt2=15;
                }
                break;
            case 33:
                {
                alt2=16;
                }
                break;
            case 34:
                {
                alt2=17;
                }
                break;
            case 35:
                {
                alt2=18;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }

            switch (alt2) {
                case 1 :
                    // InternalEssentialOCL.g:176:2: kw= '*'
                    {
                    kw=(Token)match(input,19,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getEssentialOCLInfixOperatorNameAccess().getAsteriskKeyword_0());

                    }

                    }
                    break;
                case 2 :
                    // InternalEssentialOCL.g:183:2: kw= '/'
                    {
                    kw=(Token)match(input,20,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getEssentialOCLInfixOperatorNameAccess().getSolidusKeyword_1());

                    }

                    }
                    break;
                case 3 :
                    // InternalEssentialOCL.g:190:2: kw= '+'
                    {
                    kw=(Token)match(input,21,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getEssentialOCLInfixOperatorNameAccess().getPlusSignKeyword_2());

                    }

                    }
                    break;
                case 4 :
                    // InternalEssentialOCL.g:197:2: kw= '-'
                    {
                    kw=(Token)match(input,16,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getEssentialOCLInfixOperatorNameAccess().getHyphenMinusKeyword_3());

                    }

                    }
                    break;
                case 5 :
                    // InternalEssentialOCL.g:204:2: kw= '>'
                    {
                    kw=(Token)match(input,22,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getEssentialOCLInfixOperatorNameAccess().getGreaterThanSignKeyword_4());

                    }

                    }
                    break;
                case 6 :
                    // InternalEssentialOCL.g:211:2: kw= '<'
                    {
                    kw=(Token)match(input,23,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getEssentialOCLInfixOperatorNameAccess().getLessThanSignKeyword_5());

                    }

                    }
                    break;
                case 7 :
                    // InternalEssentialOCL.g:218:2: kw= '>='
                    {
                    kw=(Token)match(input,24,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getEssentialOCLInfixOperatorNameAccess().getGreaterThanSignEqualsSignKeyword_6());

                    }

                    }
                    break;
                case 8 :
                    // InternalEssentialOCL.g:225:2: kw= '<='
                    {
                    kw=(Token)match(input,25,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getEssentialOCLInfixOperatorNameAccess().getLessThanSignEqualsSignKeyword_7());

                    }

                    }
                    break;
                case 9 :
                    // InternalEssentialOCL.g:232:2: kw= '='
                    {
                    kw=(Token)match(input,26,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getEssentialOCLInfixOperatorNameAccess().getEqualsSignKeyword_8());

                    }

                    }
                    break;
                case 10 :
                    // InternalEssentialOCL.g:239:2: kw= '<>'
                    {
                    kw=(Token)match(input,27,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getEssentialOCLInfixOperatorNameAccess().getLessThanSignGreaterThanSignKeyword_9());

                    }

                    }
                    break;
                case 11 :
                    // InternalEssentialOCL.g:246:2: kw= 'and'
                    {
                    kw=(Token)match(input,28,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getEssentialOCLInfixOperatorNameAccess().getAndKeyword_10());

                    }

                    }
                    break;
                case 12 :
                    // InternalEssentialOCL.g:253:2: kw= 'and2'
                    {
                    kw=(Token)match(input,29,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getEssentialOCLInfixOperatorNameAccess().getAnd2Keyword_11());

                    }

                    }
                    break;
                case 13 :
                    // InternalEssentialOCL.g:260:2: kw= 'implies'
                    {
                    kw=(Token)match(input,30,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getEssentialOCLInfixOperatorNameAccess().getImpliesKeyword_12());

                    }

                    }
                    break;
                case 14 :
                    // InternalEssentialOCL.g:267:2: kw= 'implies2'
                    {
                    kw=(Token)match(input,31,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getEssentialOCLInfixOperatorNameAccess().getImplies2Keyword_13());

                    }

                    }
                    break;
                case 15 :
                    // InternalEssentialOCL.g:274:2: kw= 'or'
                    {
                    kw=(Token)match(input,32,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getEssentialOCLInfixOperatorNameAccess().getOrKeyword_14());

                    }

                    }
                    break;
                case 16 :
                    // InternalEssentialOCL.g:281:2: kw= 'or2'
                    {
                    kw=(Token)match(input,33,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getEssentialOCLInfixOperatorNameAccess().getOr2Keyword_15());

                    }

                    }
                    break;
                case 17 :
                    // InternalEssentialOCL.g:288:2: kw= 'xor'
                    {
                    kw=(Token)match(input,34,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getEssentialOCLInfixOperatorNameAccess().getXorKeyword_16());

                    }

                    }
                    break;
                case 18 :
                    // InternalEssentialOCL.g:295:2: kw= 'xor2'
                    {
                    kw=(Token)match(input,35,FollowSets000.FOLLOW_2); if (state.failed) return current;
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
    // InternalEssentialOCL.g:308:1: entryRuleEssentialOCLNavigationOperatorName returns [String current=null] : iv_ruleEssentialOCLNavigationOperatorName= ruleEssentialOCLNavigationOperatorName EOF ;
    public final String entryRuleEssentialOCLNavigationOperatorName() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleEssentialOCLNavigationOperatorName = null;


        try {
            // InternalEssentialOCL.g:309:2: (iv_ruleEssentialOCLNavigationOperatorName= ruleEssentialOCLNavigationOperatorName EOF )
            // InternalEssentialOCL.g:310:2: iv_ruleEssentialOCLNavigationOperatorName= ruleEssentialOCLNavigationOperatorName EOF
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
    // InternalEssentialOCL.g:317:1: ruleEssentialOCLNavigationOperatorName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= '.' | kw= '->' | kw= '?.' | kw= '?->' ) ;
    public final AntlrDatatypeRuleToken ruleEssentialOCLNavigationOperatorName() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule();

        try {
            // InternalEssentialOCL.g:320:28: ( (kw= '.' | kw= '->' | kw= '?.' | kw= '?->' ) )
            // InternalEssentialOCL.g:321:1: (kw= '.' | kw= '->' | kw= '?.' | kw= '?->' )
            {
            // InternalEssentialOCL.g:321:1: (kw= '.' | kw= '->' | kw= '?.' | kw= '?->' )
            int alt3=4;
            switch ( input.LA(1) ) {
            case 36:
                {
                alt3=1;
                }
                break;
            case 37:
                {
                alt3=2;
                }
                break;
            case 38:
                {
                alt3=3;
                }
                break;
            case 39:
                {
                alt3=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }

            switch (alt3) {
                case 1 :
                    // InternalEssentialOCL.g:322:2: kw= '.'
                    {
                    kw=(Token)match(input,36,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getEssentialOCLNavigationOperatorNameAccess().getFullStopKeyword_0());

                    }

                    }
                    break;
                case 2 :
                    // InternalEssentialOCL.g:329:2: kw= '->'
                    {
                    kw=(Token)match(input,37,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getEssentialOCLNavigationOperatorNameAccess().getHyphenMinusGreaterThanSignKeyword_1());

                    }

                    }
                    break;
                case 3 :
                    // InternalEssentialOCL.g:336:2: kw= '?.'
                    {
                    kw=(Token)match(input,38,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getEssentialOCLNavigationOperatorNameAccess().getQuestionMarkFullStopKeyword_2());

                    }

                    }
                    break;
                case 4 :
                    // InternalEssentialOCL.g:343:2: kw= '?->'
                    {
                    kw=(Token)match(input,39,FollowSets000.FOLLOW_2); if (state.failed) return current;
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
    // InternalEssentialOCL.g:356:1: entryRuleBinaryOperatorName returns [String current=null] : iv_ruleBinaryOperatorName= ruleBinaryOperatorName EOF ;
    public final String entryRuleBinaryOperatorName() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleBinaryOperatorName = null;


        try {
            // InternalEssentialOCL.g:357:2: (iv_ruleBinaryOperatorName= ruleBinaryOperatorName EOF )
            // InternalEssentialOCL.g:358:2: iv_ruleBinaryOperatorName= ruleBinaryOperatorName EOF
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
    // InternalEssentialOCL.g:365:1: ruleBinaryOperatorName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_InfixOperatorName_0= ruleInfixOperatorName | this_NavigationOperatorName_1= ruleNavigationOperatorName ) ;
    public final AntlrDatatypeRuleToken ruleBinaryOperatorName() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        AntlrDatatypeRuleToken this_InfixOperatorName_0 = null;

        AntlrDatatypeRuleToken this_NavigationOperatorName_1 = null;


         enterRule();

        try {
            // InternalEssentialOCL.g:368:28: ( (this_InfixOperatorName_0= ruleInfixOperatorName | this_NavigationOperatorName_1= ruleNavigationOperatorName ) )
            // InternalEssentialOCL.g:369:1: (this_InfixOperatorName_0= ruleInfixOperatorName | this_NavigationOperatorName_1= ruleNavigationOperatorName )
            {
            // InternalEssentialOCL.g:369:1: (this_InfixOperatorName_0= ruleInfixOperatorName | this_NavigationOperatorName_1= ruleNavigationOperatorName )
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==16||(LA4_0>=19 && LA4_0<=35)) ) {
                alt4=1;
            }
            else if ( ((LA4_0>=36 && LA4_0<=39)) ) {
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
                    // InternalEssentialOCL.g:370:5: this_InfixOperatorName_0= ruleInfixOperatorName
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
                    // InternalEssentialOCL.g:382:5: this_NavigationOperatorName_1= ruleNavigationOperatorName
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
    // InternalEssentialOCL.g:400:1: entryRuleInfixOperatorName returns [String current=null] : iv_ruleInfixOperatorName= ruleInfixOperatorName EOF ;
    public final String entryRuleInfixOperatorName() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleInfixOperatorName = null;


        try {
            // InternalEssentialOCL.g:401:2: (iv_ruleInfixOperatorName= ruleInfixOperatorName EOF )
            // InternalEssentialOCL.g:402:2: iv_ruleInfixOperatorName= ruleInfixOperatorName EOF
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
    // InternalEssentialOCL.g:409:1: ruleInfixOperatorName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_EssentialOCLInfixOperatorName_0= ruleEssentialOCLInfixOperatorName ;
    public final AntlrDatatypeRuleToken ruleInfixOperatorName() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        AntlrDatatypeRuleToken this_EssentialOCLInfixOperatorName_0 = null;


         enterRule();

        try {
            // InternalEssentialOCL.g:412:28: (this_EssentialOCLInfixOperatorName_0= ruleEssentialOCLInfixOperatorName )
            // InternalEssentialOCL.g:414:5: this_EssentialOCLInfixOperatorName_0= ruleEssentialOCLInfixOperatorName
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


    // $ANTLR start "entryRuleNavigationOperatorName"
    // InternalEssentialOCL.g:432:1: entryRuleNavigationOperatorName returns [String current=null] : iv_ruleNavigationOperatorName= ruleNavigationOperatorName EOF ;
    public final String entryRuleNavigationOperatorName() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleNavigationOperatorName = null;


        try {
            // InternalEssentialOCL.g:433:2: (iv_ruleNavigationOperatorName= ruleNavigationOperatorName EOF )
            // InternalEssentialOCL.g:434:2: iv_ruleNavigationOperatorName= ruleNavigationOperatorName EOF
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
    // InternalEssentialOCL.g:441:1: ruleNavigationOperatorName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_EssentialOCLNavigationOperatorName_0= ruleEssentialOCLNavigationOperatorName ;
    public final AntlrDatatypeRuleToken ruleNavigationOperatorName() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        AntlrDatatypeRuleToken this_EssentialOCLNavigationOperatorName_0 = null;


         enterRule();

        try {
            // InternalEssentialOCL.g:444:28: (this_EssentialOCLNavigationOperatorName_0= ruleEssentialOCLNavigationOperatorName )
            // InternalEssentialOCL.g:446:5: this_EssentialOCLNavigationOperatorName_0= ruleEssentialOCLNavigationOperatorName
            {
            if ( state.backtracking==0 ) {

                      newCompositeNode(grammarAccess.getNavigationOperatorNameAccess().getEssentialOCLNavigationOperatorNameParserRuleCall());

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

            if ( state.backtracking==0 ) {
               leaveRule();
            }
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


    // $ANTLR start "entryRuleUnaryOperatorName"
    // InternalEssentialOCL.g:464:1: entryRuleUnaryOperatorName returns [String current=null] : iv_ruleUnaryOperatorName= ruleUnaryOperatorName EOF ;
    public final String entryRuleUnaryOperatorName() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleUnaryOperatorName = null;


        try {
            // InternalEssentialOCL.g:465:2: (iv_ruleUnaryOperatorName= ruleUnaryOperatorName EOF )
            // InternalEssentialOCL.g:466:2: iv_ruleUnaryOperatorName= ruleUnaryOperatorName EOF
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
    // InternalEssentialOCL.g:473:1: ruleUnaryOperatorName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_EssentialOCLUnaryOperatorName_0= ruleEssentialOCLUnaryOperatorName ;
    public final AntlrDatatypeRuleToken ruleUnaryOperatorName() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        AntlrDatatypeRuleToken this_EssentialOCLUnaryOperatorName_0 = null;


         enterRule();

        try {
            // InternalEssentialOCL.g:476:28: (this_EssentialOCLUnaryOperatorName_0= ruleEssentialOCLUnaryOperatorName )
            // InternalEssentialOCL.g:478:5: this_EssentialOCLUnaryOperatorName_0= ruleEssentialOCLUnaryOperatorName
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
    // InternalEssentialOCL.g:496:1: entryRuleEssentialOCLUnrestrictedName returns [String current=null] : iv_ruleEssentialOCLUnrestrictedName= ruleEssentialOCLUnrestrictedName EOF ;
    public final String entryRuleEssentialOCLUnrestrictedName() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleEssentialOCLUnrestrictedName = null;


        try {
            // InternalEssentialOCL.g:497:2: (iv_ruleEssentialOCLUnrestrictedName= ruleEssentialOCLUnrestrictedName EOF )
            // InternalEssentialOCL.g:498:2: iv_ruleEssentialOCLUnrestrictedName= ruleEssentialOCLUnrestrictedName EOF
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
    // InternalEssentialOCL.g:505:1: ruleEssentialOCLUnrestrictedName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_Identifier_0= ruleIdentifier ;
    public final AntlrDatatypeRuleToken ruleEssentialOCLUnrestrictedName() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        AntlrDatatypeRuleToken this_Identifier_0 = null;


         enterRule();

        try {
            // InternalEssentialOCL.g:508:28: (this_Identifier_0= ruleIdentifier )
            // InternalEssentialOCL.g:510:5: this_Identifier_0= ruleIdentifier
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


    // $ANTLR start "entryRuleUnrestrictedName"
    // InternalEssentialOCL.g:528:1: entryRuleUnrestrictedName returns [String current=null] : iv_ruleUnrestrictedName= ruleUnrestrictedName EOF ;
    public final String entryRuleUnrestrictedName() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleUnrestrictedName = null;


        try {
            // InternalEssentialOCL.g:529:2: (iv_ruleUnrestrictedName= ruleUnrestrictedName EOF )
            // InternalEssentialOCL.g:530:2: iv_ruleUnrestrictedName= ruleUnrestrictedName EOF
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
    // InternalEssentialOCL.g:537:1: ruleUnrestrictedName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_EssentialOCLUnrestrictedName_0= ruleEssentialOCLUnrestrictedName ;
    public final AntlrDatatypeRuleToken ruleUnrestrictedName() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        AntlrDatatypeRuleToken this_EssentialOCLUnrestrictedName_0 = null;


         enterRule();

        try {
            // InternalEssentialOCL.g:540:28: (this_EssentialOCLUnrestrictedName_0= ruleEssentialOCLUnrestrictedName )
            // InternalEssentialOCL.g:542:5: this_EssentialOCLUnrestrictedName_0= ruleEssentialOCLUnrestrictedName
            {
            if ( state.backtracking==0 ) {

                      newCompositeNode(grammarAccess.getUnrestrictedNameAccess().getEssentialOCLUnrestrictedNameParserRuleCall());

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

            if ( state.backtracking==0 ) {
               leaveRule();
            }
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


    // $ANTLR start "entryRuleEssentialOCLUnreservedName"
    // InternalEssentialOCL.g:560:1: entryRuleEssentialOCLUnreservedName returns [String current=null] : iv_ruleEssentialOCLUnreservedName= ruleEssentialOCLUnreservedName EOF ;
    public final String entryRuleEssentialOCLUnreservedName() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleEssentialOCLUnreservedName = null;


        try {
            // InternalEssentialOCL.g:561:2: (iv_ruleEssentialOCLUnreservedName= ruleEssentialOCLUnreservedName EOF )
            // InternalEssentialOCL.g:562:2: iv_ruleEssentialOCLUnreservedName= ruleEssentialOCLUnreservedName EOF
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
    // InternalEssentialOCL.g:569:1: ruleEssentialOCLUnreservedName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_UnrestrictedName_0= ruleUnrestrictedName | this_CollectionTypeIdentifier_1= ruleCollectionTypeIdentifier | this_PrimitiveTypeIdentifier_2= rulePrimitiveTypeIdentifier | kw= 'Map' | kw= 'Tuple' ) ;
    public final AntlrDatatypeRuleToken ruleEssentialOCLUnreservedName() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        AntlrDatatypeRuleToken this_UnrestrictedName_0 = null;

        AntlrDatatypeRuleToken this_CollectionTypeIdentifier_1 = null;

        AntlrDatatypeRuleToken this_PrimitiveTypeIdentifier_2 = null;


         enterRule();

        try {
            // InternalEssentialOCL.g:572:28: ( (this_UnrestrictedName_0= ruleUnrestrictedName | this_CollectionTypeIdentifier_1= ruleCollectionTypeIdentifier | this_PrimitiveTypeIdentifier_2= rulePrimitiveTypeIdentifier | kw= 'Map' | kw= 'Tuple' ) )
            // InternalEssentialOCL.g:573:1: (this_UnrestrictedName_0= ruleUnrestrictedName | this_CollectionTypeIdentifier_1= ruleCollectionTypeIdentifier | this_PrimitiveTypeIdentifier_2= rulePrimitiveTypeIdentifier | kw= 'Map' | kw= 'Tuple' )
            {
            // InternalEssentialOCL.g:573:1: (this_UnrestrictedName_0= ruleUnrestrictedName | this_CollectionTypeIdentifier_1= ruleCollectionTypeIdentifier | this_PrimitiveTypeIdentifier_2= rulePrimitiveTypeIdentifier | kw= 'Map' | kw= 'Tuple' )
            int alt5=5;
            switch ( input.LA(1) ) {
            case RULE_SIMPLE_ID:
            case RULE_ESCAPED_ID:
                {
                alt5=1;
                }
                break;
            case 50:
            case 51:
            case 52:
            case 53:
            case 54:
                {
                alt5=2;
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
                {
                alt5=3;
                }
                break;
            case 40:
                {
                alt5=4;
                }
                break;
            case 41:
                {
                alt5=5;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;
            }

            switch (alt5) {
                case 1 :
                    // InternalEssentialOCL.g:574:5: this_UnrestrictedName_0= ruleUnrestrictedName
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
                    // InternalEssentialOCL.g:586:5: this_CollectionTypeIdentifier_1= ruleCollectionTypeIdentifier
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
                    // InternalEssentialOCL.g:598:5: this_PrimitiveTypeIdentifier_2= rulePrimitiveTypeIdentifier
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
                    // InternalEssentialOCL.g:610:2: kw= 'Map'
                    {
                    kw=(Token)match(input,40,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getEssentialOCLUnreservedNameAccess().getMapKeyword_3());

                    }

                    }
                    break;
                case 5 :
                    // InternalEssentialOCL.g:617:2: kw= 'Tuple'
                    {
                    kw=(Token)match(input,41,FollowSets000.FOLLOW_2); if (state.failed) return current;
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
    // InternalEssentialOCL.g:630:1: entryRuleUnreservedName returns [String current=null] : iv_ruleUnreservedName= ruleUnreservedName EOF ;
    public final String entryRuleUnreservedName() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleUnreservedName = null;


        try {
            // InternalEssentialOCL.g:631:2: (iv_ruleUnreservedName= ruleUnreservedName EOF )
            // InternalEssentialOCL.g:632:2: iv_ruleUnreservedName= ruleUnreservedName EOF
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
    // InternalEssentialOCL.g:639:1: ruleUnreservedName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_EssentialOCLUnreservedName_0= ruleEssentialOCLUnreservedName ;
    public final AntlrDatatypeRuleToken ruleUnreservedName() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        AntlrDatatypeRuleToken this_EssentialOCLUnreservedName_0 = null;


         enterRule();

        try {
            // InternalEssentialOCL.g:642:28: (this_EssentialOCLUnreservedName_0= ruleEssentialOCLUnreservedName )
            // InternalEssentialOCL.g:644:5: this_EssentialOCLUnreservedName_0= ruleEssentialOCLUnreservedName
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


    // $ANTLR start "entryRuleURIFirstPathElementCS"
    // InternalEssentialOCL.g:664:1: entryRuleURIFirstPathElementCS returns [EObject current=null] : iv_ruleURIFirstPathElementCS= ruleURIFirstPathElementCS EOF ;
    public final EObject entryRuleURIFirstPathElementCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleURIFirstPathElementCS = null;


        try {
            // InternalEssentialOCL.g:665:2: (iv_ruleURIFirstPathElementCS= ruleURIFirstPathElementCS EOF )
            // InternalEssentialOCL.g:666:2: iv_ruleURIFirstPathElementCS= ruleURIFirstPathElementCS EOF
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
    // InternalEssentialOCL.g:673:1: ruleURIFirstPathElementCS returns [EObject current=null] : ( ( ( ruleUnrestrictedName ) ) | ( () ( ( ruleURI ) ) ) ) ;
    public final EObject ruleURIFirstPathElementCS() throws RecognitionException {
        EObject current = null;

         enterRule();

        try {
            // InternalEssentialOCL.g:676:28: ( ( ( ( ruleUnrestrictedName ) ) | ( () ( ( ruleURI ) ) ) ) )
            // InternalEssentialOCL.g:677:1: ( ( ( ruleUnrestrictedName ) ) | ( () ( ( ruleURI ) ) ) )
            {
            // InternalEssentialOCL.g:677:1: ( ( ( ruleUnrestrictedName ) ) | ( () ( ( ruleURI ) ) ) )
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( ((LA6_0>=RULE_SIMPLE_ID && LA6_0<=RULE_ESCAPED_ID)) ) {
                alt6=1;
            }
            else if ( (LA6_0==RULE_SINGLE_QUOTED_STRING) ) {
                alt6=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }
            switch (alt6) {
                case 1 :
                    // InternalEssentialOCL.g:677:2: ( ( ruleUnrestrictedName ) )
                    {
                    // InternalEssentialOCL.g:677:2: ( ( ruleUnrestrictedName ) )
                    // InternalEssentialOCL.g:678:1: ( ruleUnrestrictedName )
                    {
                    // InternalEssentialOCL.g:678:1: ( ruleUnrestrictedName )
                    // InternalEssentialOCL.g:679:3: ruleUnrestrictedName
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
                    // InternalEssentialOCL.g:696:6: ( () ( ( ruleURI ) ) )
                    {
                    // InternalEssentialOCL.g:696:6: ( () ( ( ruleURI ) ) )
                    // InternalEssentialOCL.g:696:7: () ( ( ruleURI ) )
                    {
                    // InternalEssentialOCL.g:696:7: ()
                    // InternalEssentialOCL.g:697:2:
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

                    // InternalEssentialOCL.g:705:2: ( ( ruleURI ) )
                    // InternalEssentialOCL.g:706:1: ( ruleURI )
                    {
                    // InternalEssentialOCL.g:706:1: ( ruleURI )
                    // InternalEssentialOCL.g:707:3: ruleURI
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


    // $ANTLR start "entryRulePrimitiveTypeIdentifier"
    // InternalEssentialOCL.g:733:1: entryRulePrimitiveTypeIdentifier returns [String current=null] : iv_rulePrimitiveTypeIdentifier= rulePrimitiveTypeIdentifier EOF ;
    public final String entryRulePrimitiveTypeIdentifier() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulePrimitiveTypeIdentifier = null;


        try {
            // InternalEssentialOCL.g:734:2: (iv_rulePrimitiveTypeIdentifier= rulePrimitiveTypeIdentifier EOF )
            // InternalEssentialOCL.g:735:2: iv_rulePrimitiveTypeIdentifier= rulePrimitiveTypeIdentifier EOF
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
    // InternalEssentialOCL.g:742:1: rulePrimitiveTypeIdentifier returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= 'Boolean' | kw= 'Integer' | kw= 'Real' | kw= 'String' | kw= 'UnlimitedNatural' | kw= 'OclAny' | kw= 'OclInvalid' | kw= 'OclVoid' ) ;
    public final AntlrDatatypeRuleToken rulePrimitiveTypeIdentifier() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule();

        try {
            // InternalEssentialOCL.g:745:28: ( (kw= 'Boolean' | kw= 'Integer' | kw= 'Real' | kw= 'String' | kw= 'UnlimitedNatural' | kw= 'OclAny' | kw= 'OclInvalid' | kw= 'OclVoid' ) )
            // InternalEssentialOCL.g:746:1: (kw= 'Boolean' | kw= 'Integer' | kw= 'Real' | kw= 'String' | kw= 'UnlimitedNatural' | kw= 'OclAny' | kw= 'OclInvalid' | kw= 'OclVoid' )
            {
            // InternalEssentialOCL.g:746:1: (kw= 'Boolean' | kw= 'Integer' | kw= 'Real' | kw= 'String' | kw= 'UnlimitedNatural' | kw= 'OclAny' | kw= 'OclInvalid' | kw= 'OclVoid' )
            int alt7=8;
            switch ( input.LA(1) ) {
            case 42:
                {
                alt7=1;
                }
                break;
            case 43:
                {
                alt7=2;
                }
                break;
            case 44:
                {
                alt7=3;
                }
                break;
            case 45:
                {
                alt7=4;
                }
                break;
            case 46:
                {
                alt7=5;
                }
                break;
            case 47:
                {
                alt7=6;
                }
                break;
            case 48:
                {
                alt7=7;
                }
                break;
            case 49:
                {
                alt7=8;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;
            }

            switch (alt7) {
                case 1 :
                    // InternalEssentialOCL.g:747:2: kw= 'Boolean'
                    {
                    kw=(Token)match(input,42,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getPrimitiveTypeIdentifierAccess().getBooleanKeyword_0());

                    }

                    }
                    break;
                case 2 :
                    // InternalEssentialOCL.g:754:2: kw= 'Integer'
                    {
                    kw=(Token)match(input,43,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getPrimitiveTypeIdentifierAccess().getIntegerKeyword_1());

                    }

                    }
                    break;
                case 3 :
                    // InternalEssentialOCL.g:761:2: kw= 'Real'
                    {
                    kw=(Token)match(input,44,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getPrimitiveTypeIdentifierAccess().getRealKeyword_2());

                    }

                    }
                    break;
                case 4 :
                    // InternalEssentialOCL.g:768:2: kw= 'String'
                    {
                    kw=(Token)match(input,45,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getPrimitiveTypeIdentifierAccess().getStringKeyword_3());

                    }

                    }
                    break;
                case 5 :
                    // InternalEssentialOCL.g:775:2: kw= 'UnlimitedNatural'
                    {
                    kw=(Token)match(input,46,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getPrimitiveTypeIdentifierAccess().getUnlimitedNaturalKeyword_4());

                    }

                    }
                    break;
                case 6 :
                    // InternalEssentialOCL.g:782:2: kw= 'OclAny'
                    {
                    kw=(Token)match(input,47,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getPrimitiveTypeIdentifierAccess().getOclAnyKeyword_5());

                    }

                    }
                    break;
                case 7 :
                    // InternalEssentialOCL.g:789:2: kw= 'OclInvalid'
                    {
                    kw=(Token)match(input,48,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getPrimitiveTypeIdentifierAccess().getOclInvalidKeyword_6());

                    }

                    }
                    break;
                case 8 :
                    // InternalEssentialOCL.g:796:2: kw= 'OclVoid'
                    {
                    kw=(Token)match(input,49,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getPrimitiveTypeIdentifierAccess().getOclVoidKeyword_7());

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


    // $ANTLR start "entryRulePrimitiveTypeCS"
    // InternalEssentialOCL.g:809:1: entryRulePrimitiveTypeCS returns [EObject current=null] : iv_rulePrimitiveTypeCS= rulePrimitiveTypeCS EOF ;
    public final EObject entryRulePrimitiveTypeCS() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePrimitiveTypeCS = null;


        try {
            // InternalEssentialOCL.g:810:2: (iv_rulePrimitiveTypeCS= rulePrimitiveTypeCS EOF )
            // InternalEssentialOCL.g:811:2: iv_rulePrimitiveTypeCS= rulePrimitiveTypeCS EOF
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
    // InternalEssentialOCL.g:818:1: rulePrimitiveTypeCS returns [EObject current=null] : ( (lv_name_0_0= rulePrimitiveTypeIdentifier ) ) ;
    public final EObject rulePrimitiveTypeCS() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_name_0_0 = null;


         enterRule();

        try {
            // InternalEssentialOCL.g:821:28: ( ( (lv_name_0_0= rulePrimitiveTypeIdentifier ) ) )
            // InternalEssentialOCL.g:822:1: ( (lv_name_0_0= rulePrimitiveTypeIdentifier ) )
            {
            // InternalEssentialOCL.g:822:1: ( (lv_name_0_0= rulePrimitiveTypeIdentifier ) )
            // InternalEssentialOCL.g:823:1: (lv_name_0_0= rulePrimitiveTypeIdentifier )
            {
            // InternalEssentialOCL.g:823:1: (lv_name_0_0= rulePrimitiveTypeIdentifier )
            // InternalEssentialOCL.g:824:3: lv_name_0_0= rulePrimitiveTypeIdentifier
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
                      		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.PrimitiveTypeIdentifier");
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
    // InternalEssentialOCL.g:848:1: entryRuleCollectionTypeIdentifier returns [String current=null] : iv_ruleCollectionTypeIdentifier= ruleCollectionTypeIdentifier EOF ;
    public final String entryRuleCollectionTypeIdentifier() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleCollectionTypeIdentifier = null;


        try {
            // InternalEssentialOCL.g:849:2: (iv_ruleCollectionTypeIdentifier= ruleCollectionTypeIdentifier EOF )
            // InternalEssentialOCL.g:850:2: iv_ruleCollectionTypeIdentifier= ruleCollectionTypeIdentifier EOF
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
    // InternalEssentialOCL.g:857:1: ruleCollectionTypeIdentifier returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= 'Set' | kw= 'Bag' | kw= 'Sequence' | kw= 'Collection' | kw= 'OrderedSet' ) ;
    public final AntlrDatatypeRuleToken ruleCollectionTypeIdentifier() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule();

        try {
            // InternalEssentialOCL.g:860:28: ( (kw= 'Set' | kw= 'Bag' | kw= 'Sequence' | kw= 'Collection' | kw= 'OrderedSet' ) )
            // InternalEssentialOCL.g:861:1: (kw= 'Set' | kw= 'Bag' | kw= 'Sequence' | kw= 'Collection' | kw= 'OrderedSet' )
            {
            // InternalEssentialOCL.g:861:1: (kw= 'Set' | kw= 'Bag' | kw= 'Sequence' | kw= 'Collection' | kw= 'OrderedSet' )
            int alt8=5;
            switch ( input.LA(1) ) {
            case 50:
                {
                alt8=1;
                }
                break;
            case 51:
                {
                alt8=2;
                }
                break;
            case 52:
                {
                alt8=3;
                }
                break;
            case 53:
                {
                alt8=4;
                }
                break;
            case 54:
                {
                alt8=5;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;
            }

            switch (alt8) {
                case 1 :
                    // InternalEssentialOCL.g:862:2: kw= 'Set'
                    {
                    kw=(Token)match(input,50,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getCollectionTypeIdentifierAccess().getSetKeyword_0());

                    }

                    }
                    break;
                case 2 :
                    // InternalEssentialOCL.g:869:2: kw= 'Bag'
                    {
                    kw=(Token)match(input,51,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getCollectionTypeIdentifierAccess().getBagKeyword_1());

                    }

                    }
                    break;
                case 3 :
                    // InternalEssentialOCL.g:876:2: kw= 'Sequence'
                    {
                    kw=(Token)match(input,52,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getCollectionTypeIdentifierAccess().getSequenceKeyword_2());

                    }

                    }
                    break;
                case 4 :
                    // InternalEssentialOCL.g:883:2: kw= 'Collection'
                    {
                    kw=(Token)match(input,53,FollowSets000.FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getCollectionTypeIdentifierAccess().getCollectionKeyword_3());

                    }

                    }
                    break;
                case 5 :
                    // InternalEssentialOCL.g:890:2: kw= 'OrderedSet'
                    {
                    kw=(Token)match(input,54,FollowSets000.FOLLOW_2); if (state.failed) return current;
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
    // InternalEssentialOCL.g:903:1: entryRuleCollectionTypeCS returns [EObject current=null] : iv_ruleCollectionTypeCS= ruleCollectionTypeCS EOF ;
    public final EObject entryRuleCollectionTypeCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCollectionTypeCS = null;


        try {
            // InternalEssentialOCL.g:904:2: (iv_ruleCollectionTypeCS= ruleCollectionTypeCS EOF )
            // InternalEssentialOCL.g:905:2: iv_ruleCollectionTypeCS= ruleCollectionTypeCS EOF
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
    // InternalEssentialOCL.g:912:1: ruleCollectionTypeCS returns [EObject current=null] : ( ( (lv_name_0_0= ruleCollectionTypeIdentifier ) ) (otherlv_1= '(' ( (lv_ownedType_2_0= ruleTypeExpWithoutMultiplicityCS ) ) ( (lv_ownedCollectionMultiplicity_3_0= ruleMultiplicityCS ) )? otherlv_4= ')' )? ) ;
    public final EObject ruleCollectionTypeCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_4=null;
        AntlrDatatypeRuleToken lv_name_0_0 = null;

        EObject lv_ownedType_2_0 = null;

        EObject lv_ownedCollectionMultiplicity_3_0 = null;


         enterRule();

        try {
            // InternalEssentialOCL.g:915:28: ( ( ( (lv_name_0_0= ruleCollectionTypeIdentifier ) ) (otherlv_1= '(' ( (lv_ownedType_2_0= ruleTypeExpWithoutMultiplicityCS ) ) ( (lv_ownedCollectionMultiplicity_3_0= ruleMultiplicityCS ) )? otherlv_4= ')' )? ) )
            // InternalEssentialOCL.g:916:1: ( ( (lv_name_0_0= ruleCollectionTypeIdentifier ) ) (otherlv_1= '(' ( (lv_ownedType_2_0= ruleTypeExpWithoutMultiplicityCS ) ) ( (lv_ownedCollectionMultiplicity_3_0= ruleMultiplicityCS ) )? otherlv_4= ')' )? )
            {
            // InternalEssentialOCL.g:916:1: ( ( (lv_name_0_0= ruleCollectionTypeIdentifier ) ) (otherlv_1= '(' ( (lv_ownedType_2_0= ruleTypeExpWithoutMultiplicityCS ) ) ( (lv_ownedCollectionMultiplicity_3_0= ruleMultiplicityCS ) )? otherlv_4= ')' )? )
            // InternalEssentialOCL.g:916:2: ( (lv_name_0_0= ruleCollectionTypeIdentifier ) ) (otherlv_1= '(' ( (lv_ownedType_2_0= ruleTypeExpWithoutMultiplicityCS ) ) ( (lv_ownedCollectionMultiplicity_3_0= ruleMultiplicityCS ) )? otherlv_4= ')' )?
            {
            // InternalEssentialOCL.g:916:2: ( (lv_name_0_0= ruleCollectionTypeIdentifier ) )
            // InternalEssentialOCL.g:917:1: (lv_name_0_0= ruleCollectionTypeIdentifier )
            {
            // InternalEssentialOCL.g:917:1: (lv_name_0_0= ruleCollectionTypeIdentifier )
            // InternalEssentialOCL.g:918:3: lv_name_0_0= ruleCollectionTypeIdentifier
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getCollectionTypeCSAccess().getNameCollectionTypeIdentifierParserRuleCall_0_0());

            }
            pushFollow(FollowSets000.FOLLOW_3);
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

            // InternalEssentialOCL.g:934:2: (otherlv_1= '(' ( (lv_ownedType_2_0= ruleTypeExpWithoutMultiplicityCS ) ) ( (lv_ownedCollectionMultiplicity_3_0= ruleMultiplicityCS ) )? otherlv_4= ')' )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==55) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // InternalEssentialOCL.g:934:4: otherlv_1= '(' ( (lv_ownedType_2_0= ruleTypeExpWithoutMultiplicityCS ) ) ( (lv_ownedCollectionMultiplicity_3_0= ruleMultiplicityCS ) )? otherlv_4= ')'
                    {
                    otherlv_1=(Token)match(input,55,FollowSets000.FOLLOW_4); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_1, grammarAccess.getCollectionTypeCSAccess().getLeftParenthesisKeyword_1_0());

                    }
                    // InternalEssentialOCL.g:938:1: ( (lv_ownedType_2_0= ruleTypeExpWithoutMultiplicityCS ) )
                    // InternalEssentialOCL.g:939:1: (lv_ownedType_2_0= ruleTypeExpWithoutMultiplicityCS )
                    {
                    // InternalEssentialOCL.g:939:1: (lv_ownedType_2_0= ruleTypeExpWithoutMultiplicityCS )
                    // InternalEssentialOCL.g:940:3: lv_ownedType_2_0= ruleTypeExpWithoutMultiplicityCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getCollectionTypeCSAccess().getOwnedTypeTypeExpWithoutMultiplicityCSParserRuleCall_1_1_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_5);
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

                    // InternalEssentialOCL.g:956:2: ( (lv_ownedCollectionMultiplicity_3_0= ruleMultiplicityCS ) )?
                    int alt9=2;
                    int LA9_0 = input.LA(1);

                    if ( (LA9_0==71) ) {
                        alt9=1;
                    }
                    switch (alt9) {
                        case 1 :
                            // InternalEssentialOCL.g:957:1: (lv_ownedCollectionMultiplicity_3_0= ruleMultiplicityCS )
                            {
                            // InternalEssentialOCL.g:957:1: (lv_ownedCollectionMultiplicity_3_0= ruleMultiplicityCS )
                            // InternalEssentialOCL.g:958:3: lv_ownedCollectionMultiplicity_3_0= ruleMultiplicityCS
                            {
                            if ( state.backtracking==0 ) {

                              	        newCompositeNode(grammarAccess.getCollectionTypeCSAccess().getOwnedCollectionMultiplicityMultiplicityCSParserRuleCall_1_2_0());

                            }
                            pushFollow(FollowSets000.FOLLOW_6);
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

                    otherlv_4=(Token)match(input,56,FollowSets000.FOLLOW_2); if (state.failed) return current;
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
    // InternalEssentialOCL.g:986:1: entryRuleMapTypeCS returns [EObject current=null] : iv_ruleMapTypeCS= ruleMapTypeCS EOF ;
    public final EObject entryRuleMapTypeCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMapTypeCS = null;


        try {
            // InternalEssentialOCL.g:987:2: (iv_ruleMapTypeCS= ruleMapTypeCS EOF )
            // InternalEssentialOCL.g:988:2: iv_ruleMapTypeCS= ruleMapTypeCS EOF
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
    // InternalEssentialOCL.g:995:1: ruleMapTypeCS returns [EObject current=null] : ( ( (lv_name_0_0= 'Map' ) ) (otherlv_1= '(' ( (lv_ownedKeyType_2_0= ruleTypeExpCS ) ) otherlv_3= ',' ( (lv_ownedValueType_4_0= ruleTypeExpCS ) ) otherlv_5= ')' )? ) ;
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
            // InternalEssentialOCL.g:998:28: ( ( ( (lv_name_0_0= 'Map' ) ) (otherlv_1= '(' ( (lv_ownedKeyType_2_0= ruleTypeExpCS ) ) otherlv_3= ',' ( (lv_ownedValueType_4_0= ruleTypeExpCS ) ) otherlv_5= ')' )? ) )
            // InternalEssentialOCL.g:999:1: ( ( (lv_name_0_0= 'Map' ) ) (otherlv_1= '(' ( (lv_ownedKeyType_2_0= ruleTypeExpCS ) ) otherlv_3= ',' ( (lv_ownedValueType_4_0= ruleTypeExpCS ) ) otherlv_5= ')' )? )
            {
            // InternalEssentialOCL.g:999:1: ( ( (lv_name_0_0= 'Map' ) ) (otherlv_1= '(' ( (lv_ownedKeyType_2_0= ruleTypeExpCS ) ) otherlv_3= ',' ( (lv_ownedValueType_4_0= ruleTypeExpCS ) ) otherlv_5= ')' )? )
            // InternalEssentialOCL.g:999:2: ( (lv_name_0_0= 'Map' ) ) (otherlv_1= '(' ( (lv_ownedKeyType_2_0= ruleTypeExpCS ) ) otherlv_3= ',' ( (lv_ownedValueType_4_0= ruleTypeExpCS ) ) otherlv_5= ')' )?
            {
            // InternalEssentialOCL.g:999:2: ( (lv_name_0_0= 'Map' ) )
            // InternalEssentialOCL.g:1000:1: (lv_name_0_0= 'Map' )
            {
            // InternalEssentialOCL.g:1000:1: (lv_name_0_0= 'Map' )
            // InternalEssentialOCL.g:1001:3: lv_name_0_0= 'Map'
            {
            lv_name_0_0=(Token)match(input,40,FollowSets000.FOLLOW_3); if (state.failed) return current;
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

            // InternalEssentialOCL.g:1014:2: (otherlv_1= '(' ( (lv_ownedKeyType_2_0= ruleTypeExpCS ) ) otherlv_3= ',' ( (lv_ownedValueType_4_0= ruleTypeExpCS ) ) otherlv_5= ')' )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==55) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // InternalEssentialOCL.g:1014:4: otherlv_1= '(' ( (lv_ownedKeyType_2_0= ruleTypeExpCS ) ) otherlv_3= ',' ( (lv_ownedValueType_4_0= ruleTypeExpCS ) ) otherlv_5= ')'
                    {
                    otherlv_1=(Token)match(input,55,FollowSets000.FOLLOW_4); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_1, grammarAccess.getMapTypeCSAccess().getLeftParenthesisKeyword_1_0());

                    }
                    // InternalEssentialOCL.g:1018:1: ( (lv_ownedKeyType_2_0= ruleTypeExpCS ) )
                    // InternalEssentialOCL.g:1019:1: (lv_ownedKeyType_2_0= ruleTypeExpCS )
                    {
                    // InternalEssentialOCL.g:1019:1: (lv_ownedKeyType_2_0= ruleTypeExpCS )
                    // InternalEssentialOCL.g:1020:3: lv_ownedKeyType_2_0= ruleTypeExpCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getMapTypeCSAccess().getOwnedKeyTypeTypeExpCSParserRuleCall_1_1_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_7);
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

                    otherlv_3=(Token)match(input,57,FollowSets000.FOLLOW_4); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_3, grammarAccess.getMapTypeCSAccess().getCommaKeyword_1_2());

                    }
                    // InternalEssentialOCL.g:1040:1: ( (lv_ownedValueType_4_0= ruleTypeExpCS ) )
                    // InternalEssentialOCL.g:1041:1: (lv_ownedValueType_4_0= ruleTypeExpCS )
                    {
                    // InternalEssentialOCL.g:1041:1: (lv_ownedValueType_4_0= ruleTypeExpCS )
                    // InternalEssentialOCL.g:1042:3: lv_ownedValueType_4_0= ruleTypeExpCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getMapTypeCSAccess().getOwnedValueTypeTypeExpCSParserRuleCall_1_3_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_6);
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

                    otherlv_5=(Token)match(input,56,FollowSets000.FOLLOW_2); if (state.failed) return current;
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
    // InternalEssentialOCL.g:1070:1: entryRuleTupleTypeCS returns [EObject current=null] : iv_ruleTupleTypeCS= ruleTupleTypeCS EOF ;
    public final EObject entryRuleTupleTypeCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTupleTypeCS = null;


        try {
            // InternalEssentialOCL.g:1071:2: (iv_ruleTupleTypeCS= ruleTupleTypeCS EOF )
            // InternalEssentialOCL.g:1072:2: iv_ruleTupleTypeCS= ruleTupleTypeCS EOF
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
    // InternalEssentialOCL.g:1079:1: ruleTupleTypeCS returns [EObject current=null] : ( ( (lv_name_0_0= 'Tuple' ) ) (otherlv_1= '(' ( ( (lv_ownedParts_2_0= ruleTuplePartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleTuplePartCS ) ) )* )? otherlv_5= ')' )? ) ;
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
            // InternalEssentialOCL.g:1082:28: ( ( ( (lv_name_0_0= 'Tuple' ) ) (otherlv_1= '(' ( ( (lv_ownedParts_2_0= ruleTuplePartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleTuplePartCS ) ) )* )? otherlv_5= ')' )? ) )
            // InternalEssentialOCL.g:1083:1: ( ( (lv_name_0_0= 'Tuple' ) ) (otherlv_1= '(' ( ( (lv_ownedParts_2_0= ruleTuplePartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleTuplePartCS ) ) )* )? otherlv_5= ')' )? )
            {
            // InternalEssentialOCL.g:1083:1: ( ( (lv_name_0_0= 'Tuple' ) ) (otherlv_1= '(' ( ( (lv_ownedParts_2_0= ruleTuplePartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleTuplePartCS ) ) )* )? otherlv_5= ')' )? )
            // InternalEssentialOCL.g:1083:2: ( (lv_name_0_0= 'Tuple' ) ) (otherlv_1= '(' ( ( (lv_ownedParts_2_0= ruleTuplePartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleTuplePartCS ) ) )* )? otherlv_5= ')' )?
            {
            // InternalEssentialOCL.g:1083:2: ( (lv_name_0_0= 'Tuple' ) )
            // InternalEssentialOCL.g:1084:1: (lv_name_0_0= 'Tuple' )
            {
            // InternalEssentialOCL.g:1084:1: (lv_name_0_0= 'Tuple' )
            // InternalEssentialOCL.g:1085:3: lv_name_0_0= 'Tuple'
            {
            lv_name_0_0=(Token)match(input,41,FollowSets000.FOLLOW_3); if (state.failed) return current;
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

            // InternalEssentialOCL.g:1098:2: (otherlv_1= '(' ( ( (lv_ownedParts_2_0= ruleTuplePartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleTuplePartCS ) ) )* )? otherlv_5= ')' )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==55) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // InternalEssentialOCL.g:1098:4: otherlv_1= '(' ( ( (lv_ownedParts_2_0= ruleTuplePartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleTuplePartCS ) ) )* )? otherlv_5= ')'
                    {
                    otherlv_1=(Token)match(input,55,FollowSets000.FOLLOW_8); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_1, grammarAccess.getTupleTypeCSAccess().getLeftParenthesisKeyword_1_0());

                    }
                    // InternalEssentialOCL.g:1102:1: ( ( (lv_ownedParts_2_0= ruleTuplePartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleTuplePartCS ) ) )* )?
                    int alt13=2;
                    int LA13_0 = input.LA(1);

                    if ( ((LA13_0>=RULE_SIMPLE_ID && LA13_0<=RULE_ESCAPED_ID)) ) {
                        alt13=1;
                    }
                    switch (alt13) {
                        case 1 :
                            // InternalEssentialOCL.g:1102:2: ( (lv_ownedParts_2_0= ruleTuplePartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleTuplePartCS ) ) )*
                            {
                            // InternalEssentialOCL.g:1102:2: ( (lv_ownedParts_2_0= ruleTuplePartCS ) )
                            // InternalEssentialOCL.g:1103:1: (lv_ownedParts_2_0= ruleTuplePartCS )
                            {
                            // InternalEssentialOCL.g:1103:1: (lv_ownedParts_2_0= ruleTuplePartCS )
                            // InternalEssentialOCL.g:1104:3: lv_ownedParts_2_0= ruleTuplePartCS
                            {
                            if ( state.backtracking==0 ) {

                              	        newCompositeNode(grammarAccess.getTupleTypeCSAccess().getOwnedPartsTuplePartCSParserRuleCall_1_1_0_0());

                            }
                            pushFollow(FollowSets000.FOLLOW_9);
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

                            // InternalEssentialOCL.g:1120:2: (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleTuplePartCS ) ) )*
                            loop12:
                            do {
                                int alt12=2;
                                int LA12_0 = input.LA(1);

                                if ( (LA12_0==57) ) {
                                    alt12=1;
                                }


                                switch (alt12) {
                            	case 1 :
                            	    // InternalEssentialOCL.g:1120:4: otherlv_3= ',' ( (lv_ownedParts_4_0= ruleTuplePartCS ) )
                            	    {
                            	    otherlv_3=(Token)match(input,57,FollowSets000.FOLLOW_10); if (state.failed) return current;
                            	    if ( state.backtracking==0 ) {

                            	          	newLeafNode(otherlv_3, grammarAccess.getTupleTypeCSAccess().getCommaKeyword_1_1_1_0());

                            	    }
                            	    // InternalEssentialOCL.g:1124:1: ( (lv_ownedParts_4_0= ruleTuplePartCS ) )
                            	    // InternalEssentialOCL.g:1125:1: (lv_ownedParts_4_0= ruleTuplePartCS )
                            	    {
                            	    // InternalEssentialOCL.g:1125:1: (lv_ownedParts_4_0= ruleTuplePartCS )
                            	    // InternalEssentialOCL.g:1126:3: lv_ownedParts_4_0= ruleTuplePartCS
                            	    {
                            	    if ( state.backtracking==0 ) {

                            	      	        newCompositeNode(grammarAccess.getTupleTypeCSAccess().getOwnedPartsTuplePartCSParserRuleCall_1_1_1_1_0());

                            	    }
                            	    pushFollow(FollowSets000.FOLLOW_9);
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
                            	    break loop12;
                                }
                            } while (true);


                            }
                            break;

                    }

                    otherlv_5=(Token)match(input,56,FollowSets000.FOLLOW_2); if (state.failed) return current;
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
    // InternalEssentialOCL.g:1154:1: entryRuleTuplePartCS returns [EObject current=null] : iv_ruleTuplePartCS= ruleTuplePartCS EOF ;
    public final EObject entryRuleTuplePartCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTuplePartCS = null;


        try {
            // InternalEssentialOCL.g:1155:2: (iv_ruleTuplePartCS= ruleTuplePartCS EOF )
            // InternalEssentialOCL.g:1156:2: iv_ruleTuplePartCS= ruleTuplePartCS EOF
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
    // InternalEssentialOCL.g:1163:1: ruleTuplePartCS returns [EObject current=null] : ( ( (lv_name_0_0= ruleUnrestrictedName ) ) otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) ) ) ;
    public final EObject ruleTuplePartCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_name_0_0 = null;

        EObject lv_ownedType_2_0 = null;


         enterRule();

        try {
            // InternalEssentialOCL.g:1166:28: ( ( ( (lv_name_0_0= ruleUnrestrictedName ) ) otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) ) ) )
            // InternalEssentialOCL.g:1167:1: ( ( (lv_name_0_0= ruleUnrestrictedName ) ) otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) ) )
            {
            // InternalEssentialOCL.g:1167:1: ( ( (lv_name_0_0= ruleUnrestrictedName ) ) otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) ) )
            // InternalEssentialOCL.g:1167:2: ( (lv_name_0_0= ruleUnrestrictedName ) ) otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) )
            {
            // InternalEssentialOCL.g:1167:2: ( (lv_name_0_0= ruleUnrestrictedName ) )
            // InternalEssentialOCL.g:1168:1: (lv_name_0_0= ruleUnrestrictedName )
            {
            // InternalEssentialOCL.g:1168:1: (lv_name_0_0= ruleUnrestrictedName )
            // InternalEssentialOCL.g:1169:3: lv_name_0_0= ruleUnrestrictedName
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getTuplePartCSAccess().getNameUnrestrictedNameParserRuleCall_0_0());

            }
            pushFollow(FollowSets000.FOLLOW_11);
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
                      		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.UnrestrictedName");
              	        afterParserOrEnumRuleCall();

            }

            }


            }

            otherlv_1=(Token)match(input,58,FollowSets000.FOLLOW_4); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getTuplePartCSAccess().getColonKeyword_1());

            }
            // InternalEssentialOCL.g:1189:1: ( (lv_ownedType_2_0= ruleTypeExpCS ) )
            // InternalEssentialOCL.g:1190:1: (lv_ownedType_2_0= ruleTypeExpCS )
            {
            // InternalEssentialOCL.g:1190:1: (lv_ownedType_2_0= ruleTypeExpCS )
            // InternalEssentialOCL.g:1191:3: lv_ownedType_2_0= ruleTypeExpCS
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
    // InternalEssentialOCL.g:1215:1: entryRuleCollectionLiteralExpCS returns [EObject current=null] : iv_ruleCollectionLiteralExpCS= ruleCollectionLiteralExpCS EOF ;
    public final EObject entryRuleCollectionLiteralExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCollectionLiteralExpCS = null;


        try {
            // InternalEssentialOCL.g:1216:2: (iv_ruleCollectionLiteralExpCS= ruleCollectionLiteralExpCS EOF )
            // InternalEssentialOCL.g:1217:2: iv_ruleCollectionLiteralExpCS= ruleCollectionLiteralExpCS EOF
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
    // InternalEssentialOCL.g:1224:1: ruleCollectionLiteralExpCS returns [EObject current=null] : ( ( (lv_ownedType_0_0= ruleCollectionTypeCS ) ) otherlv_1= '{' ( ( (lv_ownedParts_2_0= ruleCollectionLiteralPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleCollectionLiteralPartCS ) ) )* )? otherlv_5= '}' ) ;
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
            // InternalEssentialOCL.g:1227:28: ( ( ( (lv_ownedType_0_0= ruleCollectionTypeCS ) ) otherlv_1= '{' ( ( (lv_ownedParts_2_0= ruleCollectionLiteralPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleCollectionLiteralPartCS ) ) )* )? otherlv_5= '}' ) )
            // InternalEssentialOCL.g:1228:1: ( ( (lv_ownedType_0_0= ruleCollectionTypeCS ) ) otherlv_1= '{' ( ( (lv_ownedParts_2_0= ruleCollectionLiteralPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleCollectionLiteralPartCS ) ) )* )? otherlv_5= '}' )
            {
            // InternalEssentialOCL.g:1228:1: ( ( (lv_ownedType_0_0= ruleCollectionTypeCS ) ) otherlv_1= '{' ( ( (lv_ownedParts_2_0= ruleCollectionLiteralPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleCollectionLiteralPartCS ) ) )* )? otherlv_5= '}' )
            // InternalEssentialOCL.g:1228:2: ( (lv_ownedType_0_0= ruleCollectionTypeCS ) ) otherlv_1= '{' ( ( (lv_ownedParts_2_0= ruleCollectionLiteralPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleCollectionLiteralPartCS ) ) )* )? otherlv_5= '}'
            {
            // InternalEssentialOCL.g:1228:2: ( (lv_ownedType_0_0= ruleCollectionTypeCS ) )
            // InternalEssentialOCL.g:1229:1: (lv_ownedType_0_0= ruleCollectionTypeCS )
            {
            // InternalEssentialOCL.g:1229:1: (lv_ownedType_0_0= ruleCollectionTypeCS )
            // InternalEssentialOCL.g:1230:3: lv_ownedType_0_0= ruleCollectionTypeCS
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getCollectionLiteralExpCSAccess().getOwnedTypeCollectionTypeCSParserRuleCall_0_0());

            }
            pushFollow(FollowSets000.FOLLOW_12);
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

            otherlv_1=(Token)match(input,59,FollowSets000.FOLLOW_13); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getCollectionLiteralExpCSAccess().getLeftCurlyBracketKeyword_1());

            }
            // InternalEssentialOCL.g:1250:1: ( ( (lv_ownedParts_2_0= ruleCollectionLiteralPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleCollectionLiteralPartCS ) ) )* )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( ((LA16_0>=RULE_SIMPLE_ID && LA16_0<=RULE_SINGLE_QUOTED_STRING)||(LA16_0>=16 && LA16_0<=19)||(LA16_0>=40 && LA16_0<=55)||LA16_0==58||LA16_0==63||(LA16_0>=65 && LA16_0<=68)||LA16_0==76||(LA16_0>=81 && LA16_0<=82)) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // InternalEssentialOCL.g:1250:2: ( (lv_ownedParts_2_0= ruleCollectionLiteralPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleCollectionLiteralPartCS ) ) )*
                    {
                    // InternalEssentialOCL.g:1250:2: ( (lv_ownedParts_2_0= ruleCollectionLiteralPartCS ) )
                    // InternalEssentialOCL.g:1251:1: (lv_ownedParts_2_0= ruleCollectionLiteralPartCS )
                    {
                    // InternalEssentialOCL.g:1251:1: (lv_ownedParts_2_0= ruleCollectionLiteralPartCS )
                    // InternalEssentialOCL.g:1252:3: lv_ownedParts_2_0= ruleCollectionLiteralPartCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getCollectionLiteralExpCSAccess().getOwnedPartsCollectionLiteralPartCSParserRuleCall_2_0_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_14);
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

                    // InternalEssentialOCL.g:1268:2: (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleCollectionLiteralPartCS ) ) )*
                    loop15:
                    do {
                        int alt15=2;
                        int LA15_0 = input.LA(1);

                        if ( (LA15_0==57) ) {
                            alt15=1;
                        }


                        switch (alt15) {
                    	case 1 :
                    	    // InternalEssentialOCL.g:1268:4: otherlv_3= ',' ( (lv_ownedParts_4_0= ruleCollectionLiteralPartCS ) )
                    	    {
                    	    otherlv_3=(Token)match(input,57,FollowSets000.FOLLOW_15); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_3, grammarAccess.getCollectionLiteralExpCSAccess().getCommaKeyword_2_1_0());

                    	    }
                    	    // InternalEssentialOCL.g:1272:1: ( (lv_ownedParts_4_0= ruleCollectionLiteralPartCS ) )
                    	    // InternalEssentialOCL.g:1273:1: (lv_ownedParts_4_0= ruleCollectionLiteralPartCS )
                    	    {
                    	    // InternalEssentialOCL.g:1273:1: (lv_ownedParts_4_0= ruleCollectionLiteralPartCS )
                    	    // InternalEssentialOCL.g:1274:3: lv_ownedParts_4_0= ruleCollectionLiteralPartCS
                    	    {
                    	    if ( state.backtracking==0 ) {

                    	      	        newCompositeNode(grammarAccess.getCollectionLiteralExpCSAccess().getOwnedPartsCollectionLiteralPartCSParserRuleCall_2_1_1_0());

                    	    }
                    	    pushFollow(FollowSets000.FOLLOW_14);
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
                    	    break loop15;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_5=(Token)match(input,60,FollowSets000.FOLLOW_2); if (state.failed) return current;
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
    // InternalEssentialOCL.g:1302:1: entryRuleCollectionLiteralPartCS returns [EObject current=null] : iv_ruleCollectionLiteralPartCS= ruleCollectionLiteralPartCS EOF ;
    public final EObject entryRuleCollectionLiteralPartCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCollectionLiteralPartCS = null;


        try {
            // InternalEssentialOCL.g:1303:2: (iv_ruleCollectionLiteralPartCS= ruleCollectionLiteralPartCS EOF )
            // InternalEssentialOCL.g:1304:2: iv_ruleCollectionLiteralPartCS= ruleCollectionLiteralPartCS EOF
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
    // InternalEssentialOCL.g:1311:1: ruleCollectionLiteralPartCS returns [EObject current=null] : ( ( ( (lv_ownedExpression_0_0= ruleExpCS ) ) (otherlv_1= '..' ( (lv_ownedLastExpression_2_0= ruleExpCS ) ) )? ) | ( (lv_ownedExpression_3_0= rulePatternExpCS ) ) ) ;
    public final EObject ruleCollectionLiteralPartCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject lv_ownedExpression_0_0 = null;

        EObject lv_ownedLastExpression_2_0 = null;

        EObject lv_ownedExpression_3_0 = null;


         enterRule();

        try {
            // InternalEssentialOCL.g:1314:28: ( ( ( ( (lv_ownedExpression_0_0= ruleExpCS ) ) (otherlv_1= '..' ( (lv_ownedLastExpression_2_0= ruleExpCS ) ) )? ) | ( (lv_ownedExpression_3_0= rulePatternExpCS ) ) ) )
            // InternalEssentialOCL.g:1315:1: ( ( ( (lv_ownedExpression_0_0= ruleExpCS ) ) (otherlv_1= '..' ( (lv_ownedLastExpression_2_0= ruleExpCS ) ) )? ) | ( (lv_ownedExpression_3_0= rulePatternExpCS ) ) )
            {
            // InternalEssentialOCL.g:1315:1: ( ( ( (lv_ownedExpression_0_0= ruleExpCS ) ) (otherlv_1= '..' ( (lv_ownedLastExpression_2_0= ruleExpCS ) ) )? ) | ( (lv_ownedExpression_3_0= rulePatternExpCS ) ) )
            int alt18=2;
            switch ( input.LA(1) ) {
            case RULE_INT:
            case RULE_SINGLE_QUOTED_STRING:
            case 16:
            case 17:
            case 18:
            case 19:
            case 40:
            case 41:
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
            case 63:
            case 65:
            case 66:
            case 67:
            case 68:
            case 76:
            case 81:
            case 82:
                {
                alt18=1;
                }
                break;
            case RULE_SIMPLE_ID:
                {
                int LA18_2 = input.LA(2);

                if ( (LA18_2==EOF||LA18_2==16||(LA18_2>=19 && LA18_2<=39)||LA18_2==55||LA18_2==57||(LA18_2>=59 && LA18_2<=61)||LA18_2==69||LA18_2==71||LA18_2==86) ) {
                    alt18=1;
                }
                else if ( (LA18_2==58) ) {
                    alt18=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return current;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 18, 2, input);

                    throw nvae;
                }
                }
                break;
            case RULE_ESCAPED_ID:
                {
                int LA18_3 = input.LA(2);

                if ( (LA18_3==EOF||LA18_3==16||(LA18_3>=19 && LA18_3<=39)||LA18_3==55||LA18_3==57||(LA18_3>=59 && LA18_3<=61)||LA18_3==69||LA18_3==71||LA18_3==86) ) {
                    alt18=1;
                }
                else if ( (LA18_3==58) ) {
                    alt18=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return current;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 18, 3, input);

                    throw nvae;
                }
                }
                break;
            case 58:
                {
                alt18=2;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 18, 0, input);

                throw nvae;
            }

            switch (alt18) {
                case 1 :
                    // InternalEssentialOCL.g:1315:2: ( ( (lv_ownedExpression_0_0= ruleExpCS ) ) (otherlv_1= '..' ( (lv_ownedLastExpression_2_0= ruleExpCS ) ) )? )
                    {
                    // InternalEssentialOCL.g:1315:2: ( ( (lv_ownedExpression_0_0= ruleExpCS ) ) (otherlv_1= '..' ( (lv_ownedLastExpression_2_0= ruleExpCS ) ) )? )
                    // InternalEssentialOCL.g:1315:3: ( (lv_ownedExpression_0_0= ruleExpCS ) ) (otherlv_1= '..' ( (lv_ownedLastExpression_2_0= ruleExpCS ) ) )?
                    {
                    // InternalEssentialOCL.g:1315:3: ( (lv_ownedExpression_0_0= ruleExpCS ) )
                    // InternalEssentialOCL.g:1316:1: (lv_ownedExpression_0_0= ruleExpCS )
                    {
                    // InternalEssentialOCL.g:1316:1: (lv_ownedExpression_0_0= ruleExpCS )
                    // InternalEssentialOCL.g:1317:3: lv_ownedExpression_0_0= ruleExpCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getCollectionLiteralPartCSAccess().getOwnedExpressionExpCSParserRuleCall_0_0_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_16);
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

                    // InternalEssentialOCL.g:1333:2: (otherlv_1= '..' ( (lv_ownedLastExpression_2_0= ruleExpCS ) ) )?
                    int alt17=2;
                    int LA17_0 = input.LA(1);

                    if ( (LA17_0==61) ) {
                        alt17=1;
                    }
                    switch (alt17) {
                        case 1 :
                            // InternalEssentialOCL.g:1333:4: otherlv_1= '..' ( (lv_ownedLastExpression_2_0= ruleExpCS ) )
                            {
                            otherlv_1=(Token)match(input,61,FollowSets000.FOLLOW_17); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                  	newLeafNode(otherlv_1, grammarAccess.getCollectionLiteralPartCSAccess().getFullStopFullStopKeyword_0_1_0());

                            }
                            // InternalEssentialOCL.g:1337:1: ( (lv_ownedLastExpression_2_0= ruleExpCS ) )
                            // InternalEssentialOCL.g:1338:1: (lv_ownedLastExpression_2_0= ruleExpCS )
                            {
                            // InternalEssentialOCL.g:1338:1: (lv_ownedLastExpression_2_0= ruleExpCS )
                            // InternalEssentialOCL.g:1339:3: lv_ownedLastExpression_2_0= ruleExpCS
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
                    // InternalEssentialOCL.g:1356:6: ( (lv_ownedExpression_3_0= rulePatternExpCS ) )
                    {
                    // InternalEssentialOCL.g:1356:6: ( (lv_ownedExpression_3_0= rulePatternExpCS ) )
                    // InternalEssentialOCL.g:1357:1: (lv_ownedExpression_3_0= rulePatternExpCS )
                    {
                    // InternalEssentialOCL.g:1357:1: (lv_ownedExpression_3_0= rulePatternExpCS )
                    // InternalEssentialOCL.g:1358:3: lv_ownedExpression_3_0= rulePatternExpCS
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
    // InternalEssentialOCL.g:1382:1: entryRuleCollectionPatternCS returns [EObject current=null] : iv_ruleCollectionPatternCS= ruleCollectionPatternCS EOF ;
    public final EObject entryRuleCollectionPatternCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCollectionPatternCS = null;


        try {
            // InternalEssentialOCL.g:1383:2: (iv_ruleCollectionPatternCS= ruleCollectionPatternCS EOF )
            // InternalEssentialOCL.g:1384:2: iv_ruleCollectionPatternCS= ruleCollectionPatternCS EOF
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
    // InternalEssentialOCL.g:1391:1: ruleCollectionPatternCS returns [EObject current=null] : ( ( (lv_ownedType_0_0= ruleCollectionTypeCS ) ) otherlv_1= '{' ( ( (lv_ownedParts_2_0= rulePatternExpCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= rulePatternExpCS ) ) )* (otherlv_5= '++' ( (lv_restVariableName_6_0= ruleIdentifier ) ) ) )? otherlv_7= '}' ) ;
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
            // InternalEssentialOCL.g:1394:28: ( ( ( (lv_ownedType_0_0= ruleCollectionTypeCS ) ) otherlv_1= '{' ( ( (lv_ownedParts_2_0= rulePatternExpCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= rulePatternExpCS ) ) )* (otherlv_5= '++' ( (lv_restVariableName_6_0= ruleIdentifier ) ) ) )? otherlv_7= '}' ) )
            // InternalEssentialOCL.g:1395:1: ( ( (lv_ownedType_0_0= ruleCollectionTypeCS ) ) otherlv_1= '{' ( ( (lv_ownedParts_2_0= rulePatternExpCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= rulePatternExpCS ) ) )* (otherlv_5= '++' ( (lv_restVariableName_6_0= ruleIdentifier ) ) ) )? otherlv_7= '}' )
            {
            // InternalEssentialOCL.g:1395:1: ( ( (lv_ownedType_0_0= ruleCollectionTypeCS ) ) otherlv_1= '{' ( ( (lv_ownedParts_2_0= rulePatternExpCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= rulePatternExpCS ) ) )* (otherlv_5= '++' ( (lv_restVariableName_6_0= ruleIdentifier ) ) ) )? otherlv_7= '}' )
            // InternalEssentialOCL.g:1395:2: ( (lv_ownedType_0_0= ruleCollectionTypeCS ) ) otherlv_1= '{' ( ( (lv_ownedParts_2_0= rulePatternExpCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= rulePatternExpCS ) ) )* (otherlv_5= '++' ( (lv_restVariableName_6_0= ruleIdentifier ) ) ) )? otherlv_7= '}'
            {
            // InternalEssentialOCL.g:1395:2: ( (lv_ownedType_0_0= ruleCollectionTypeCS ) )
            // InternalEssentialOCL.g:1396:1: (lv_ownedType_0_0= ruleCollectionTypeCS )
            {
            // InternalEssentialOCL.g:1396:1: (lv_ownedType_0_0= ruleCollectionTypeCS )
            // InternalEssentialOCL.g:1397:3: lv_ownedType_0_0= ruleCollectionTypeCS
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getCollectionPatternCSAccess().getOwnedTypeCollectionTypeCSParserRuleCall_0_0());

            }
            pushFollow(FollowSets000.FOLLOW_12);
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

            otherlv_1=(Token)match(input,59,FollowSets000.FOLLOW_13); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getCollectionPatternCSAccess().getLeftCurlyBracketKeyword_1());

            }
            // InternalEssentialOCL.g:1417:1: ( ( (lv_ownedParts_2_0= rulePatternExpCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= rulePatternExpCS ) ) )* (otherlv_5= '++' ( (lv_restVariableName_6_0= ruleIdentifier ) ) ) )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( ((LA20_0>=RULE_SIMPLE_ID && LA20_0<=RULE_ESCAPED_ID)||LA20_0==58) ) {
                alt20=1;
            }
            switch (alt20) {
                case 1 :
                    // InternalEssentialOCL.g:1417:2: ( (lv_ownedParts_2_0= rulePatternExpCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= rulePatternExpCS ) ) )* (otherlv_5= '++' ( (lv_restVariableName_6_0= ruleIdentifier ) ) )
                    {
                    // InternalEssentialOCL.g:1417:2: ( (lv_ownedParts_2_0= rulePatternExpCS ) )
                    // InternalEssentialOCL.g:1418:1: (lv_ownedParts_2_0= rulePatternExpCS )
                    {
                    // InternalEssentialOCL.g:1418:1: (lv_ownedParts_2_0= rulePatternExpCS )
                    // InternalEssentialOCL.g:1419:3: lv_ownedParts_2_0= rulePatternExpCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getCollectionPatternCSAccess().getOwnedPartsPatternExpCSParserRuleCall_2_0_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_18);
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

                    // InternalEssentialOCL.g:1435:2: (otherlv_3= ',' ( (lv_ownedParts_4_0= rulePatternExpCS ) ) )*
                    loop19:
                    do {
                        int alt19=2;
                        int LA19_0 = input.LA(1);

                        if ( (LA19_0==57) ) {
                            alt19=1;
                        }


                        switch (alt19) {
                    	case 1 :
                    	    // InternalEssentialOCL.g:1435:4: otherlv_3= ',' ( (lv_ownedParts_4_0= rulePatternExpCS ) )
                    	    {
                    	    otherlv_3=(Token)match(input,57,FollowSets000.FOLLOW_15); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_3, grammarAccess.getCollectionPatternCSAccess().getCommaKeyword_2_1_0());

                    	    }
                    	    // InternalEssentialOCL.g:1439:1: ( (lv_ownedParts_4_0= rulePatternExpCS ) )
                    	    // InternalEssentialOCL.g:1440:1: (lv_ownedParts_4_0= rulePatternExpCS )
                    	    {
                    	    // InternalEssentialOCL.g:1440:1: (lv_ownedParts_4_0= rulePatternExpCS )
                    	    // InternalEssentialOCL.g:1441:3: lv_ownedParts_4_0= rulePatternExpCS
                    	    {
                    	    if ( state.backtracking==0 ) {

                    	      	        newCompositeNode(grammarAccess.getCollectionPatternCSAccess().getOwnedPartsPatternExpCSParserRuleCall_2_1_1_0());

                    	    }
                    	    pushFollow(FollowSets000.FOLLOW_18);
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
                    	    break loop19;
                        }
                    } while (true);

                    // InternalEssentialOCL.g:1457:4: (otherlv_5= '++' ( (lv_restVariableName_6_0= ruleIdentifier ) ) )
                    // InternalEssentialOCL.g:1457:6: otherlv_5= '++' ( (lv_restVariableName_6_0= ruleIdentifier ) )
                    {
                    otherlv_5=(Token)match(input,62,FollowSets000.FOLLOW_10); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_5, grammarAccess.getCollectionPatternCSAccess().getPlusSignPlusSignKeyword_2_2_0());

                    }
                    // InternalEssentialOCL.g:1461:1: ( (lv_restVariableName_6_0= ruleIdentifier ) )
                    // InternalEssentialOCL.g:1462:1: (lv_restVariableName_6_0= ruleIdentifier )
                    {
                    // InternalEssentialOCL.g:1462:1: (lv_restVariableName_6_0= ruleIdentifier )
                    // InternalEssentialOCL.g:1463:3: lv_restVariableName_6_0= ruleIdentifier
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getCollectionPatternCSAccess().getRestVariableNameIdentifierParserRuleCall_2_2_1_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_19);
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

            otherlv_7=(Token)match(input,60,FollowSets000.FOLLOW_2); if (state.failed) return current;
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
    // InternalEssentialOCL.g:1491:1: entryRuleShadowPartCS returns [EObject current=null] : iv_ruleShadowPartCS= ruleShadowPartCS EOF ;
    public final EObject entryRuleShadowPartCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleShadowPartCS = null;


        try {
            // InternalEssentialOCL.g:1492:2: (iv_ruleShadowPartCS= ruleShadowPartCS EOF )
            // InternalEssentialOCL.g:1493:2: iv_ruleShadowPartCS= ruleShadowPartCS EOF
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
    // InternalEssentialOCL.g:1500:1: ruleShadowPartCS returns [EObject current=null] : ( ( ( ( ruleUnrestrictedName ) ) otherlv_1= '=' ( ( (lv_ownedInitExpression_2_1= ruleExpCS | lv_ownedInitExpression_2_2= rulePatternExpCS ) ) ) ) | ( (lv_ownedInitExpression_3_0= ruleStringLiteralExpCS ) ) ) ;
    public final EObject ruleShadowPartCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject lv_ownedInitExpression_2_1 = null;

        EObject lv_ownedInitExpression_2_2 = null;

        EObject lv_ownedInitExpression_3_0 = null;


         enterRule();

        try {
            // InternalEssentialOCL.g:1503:28: ( ( ( ( ( ruleUnrestrictedName ) ) otherlv_1= '=' ( ( (lv_ownedInitExpression_2_1= ruleExpCS | lv_ownedInitExpression_2_2= rulePatternExpCS ) ) ) ) | ( (lv_ownedInitExpression_3_0= ruleStringLiteralExpCS ) ) ) )
            // InternalEssentialOCL.g:1504:1: ( ( ( ( ruleUnrestrictedName ) ) otherlv_1= '=' ( ( (lv_ownedInitExpression_2_1= ruleExpCS | lv_ownedInitExpression_2_2= rulePatternExpCS ) ) ) ) | ( (lv_ownedInitExpression_3_0= ruleStringLiteralExpCS ) ) )
            {
            // InternalEssentialOCL.g:1504:1: ( ( ( ( ruleUnrestrictedName ) ) otherlv_1= '=' ( ( (lv_ownedInitExpression_2_1= ruleExpCS | lv_ownedInitExpression_2_2= rulePatternExpCS ) ) ) ) | ( (lv_ownedInitExpression_3_0= ruleStringLiteralExpCS ) ) )
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( ((LA22_0>=RULE_SIMPLE_ID && LA22_0<=RULE_ESCAPED_ID)) ) {
                alt22=1;
            }
            else if ( (LA22_0==RULE_SINGLE_QUOTED_STRING) ) {
                alt22=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 22, 0, input);

                throw nvae;
            }
            switch (alt22) {
                case 1 :
                    // InternalEssentialOCL.g:1504:2: ( ( ( ruleUnrestrictedName ) ) otherlv_1= '=' ( ( (lv_ownedInitExpression_2_1= ruleExpCS | lv_ownedInitExpression_2_2= rulePatternExpCS ) ) ) )
                    {
                    // InternalEssentialOCL.g:1504:2: ( ( ( ruleUnrestrictedName ) ) otherlv_1= '=' ( ( (lv_ownedInitExpression_2_1= ruleExpCS | lv_ownedInitExpression_2_2= rulePatternExpCS ) ) ) )
                    // InternalEssentialOCL.g:1504:3: ( ( ruleUnrestrictedName ) ) otherlv_1= '=' ( ( (lv_ownedInitExpression_2_1= ruleExpCS | lv_ownedInitExpression_2_2= rulePatternExpCS ) ) )
                    {
                    // InternalEssentialOCL.g:1504:3: ( ( ruleUnrestrictedName ) )
                    // InternalEssentialOCL.g:1505:1: ( ruleUnrestrictedName )
                    {
                    // InternalEssentialOCL.g:1505:1: ( ruleUnrestrictedName )
                    // InternalEssentialOCL.g:1506:3: ruleUnrestrictedName
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

                    otherlv_1=(Token)match(input,26,FollowSets000.FOLLOW_15); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_1, grammarAccess.getShadowPartCSAccess().getEqualsSignKeyword_0_1());

                    }
                    // InternalEssentialOCL.g:1526:1: ( ( (lv_ownedInitExpression_2_1= ruleExpCS | lv_ownedInitExpression_2_2= rulePatternExpCS ) ) )
                    // InternalEssentialOCL.g:1527:1: ( (lv_ownedInitExpression_2_1= ruleExpCS | lv_ownedInitExpression_2_2= rulePatternExpCS ) )
                    {
                    // InternalEssentialOCL.g:1527:1: ( (lv_ownedInitExpression_2_1= ruleExpCS | lv_ownedInitExpression_2_2= rulePatternExpCS ) )
                    // InternalEssentialOCL.g:1528:1: (lv_ownedInitExpression_2_1= ruleExpCS | lv_ownedInitExpression_2_2= rulePatternExpCS )
                    {
                    // InternalEssentialOCL.g:1528:1: (lv_ownedInitExpression_2_1= ruleExpCS | lv_ownedInitExpression_2_2= rulePatternExpCS )
                    int alt21=2;
                    switch ( input.LA(1) ) {
                    case RULE_INT:
                    case RULE_SINGLE_QUOTED_STRING:
                    case 16:
                    case 17:
                    case 18:
                    case 19:
                    case 40:
                    case 41:
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
                    case 63:
                    case 65:
                    case 66:
                    case 67:
                    case 68:
                    case 76:
                    case 81:
                    case 82:
                        {
                        alt21=1;
                        }
                        break;
                    case RULE_SIMPLE_ID:
                        {
                        int LA21_2 = input.LA(2);

                        if ( (LA21_2==58) ) {
                            alt21=2;
                        }
                        else if ( (LA21_2==EOF||LA21_2==16||(LA21_2>=19 && LA21_2<=39)||LA21_2==55||LA21_2==57||(LA21_2>=59 && LA21_2<=60)||LA21_2==69||LA21_2==71||LA21_2==86) ) {
                            alt21=1;
                        }
                        else {
                            if (state.backtracking>0) {state.failed=true; return current;}
                            NoViableAltException nvae =
                                new NoViableAltException("", 21, 2, input);

                            throw nvae;
                        }
                        }
                        break;
                    case RULE_ESCAPED_ID:
                        {
                        int LA21_3 = input.LA(2);

                        if ( (LA21_3==EOF||LA21_3==16||(LA21_3>=19 && LA21_3<=39)||LA21_3==55||LA21_3==57||(LA21_3>=59 && LA21_3<=60)||LA21_3==69||LA21_3==71||LA21_3==86) ) {
                            alt21=1;
                        }
                        else if ( (LA21_3==58) ) {
                            alt21=2;
                        }
                        else {
                            if (state.backtracking>0) {state.failed=true; return current;}
                            NoViableAltException nvae =
                                new NoViableAltException("", 21, 3, input);

                            throw nvae;
                        }
                        }
                        break;
                    case 58:
                        {
                        alt21=2;
                        }
                        break;
                    default:
                        if (state.backtracking>0) {state.failed=true; return current;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 21, 0, input);

                        throw nvae;
                    }

                    switch (alt21) {
                        case 1 :
                            // InternalEssentialOCL.g:1529:3: lv_ownedInitExpression_2_1= ruleExpCS
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
                            // InternalEssentialOCL.g:1544:8: lv_ownedInitExpression_2_2= rulePatternExpCS
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
                    // InternalEssentialOCL.g:1563:6: ( (lv_ownedInitExpression_3_0= ruleStringLiteralExpCS ) )
                    {
                    // InternalEssentialOCL.g:1563:6: ( (lv_ownedInitExpression_3_0= ruleStringLiteralExpCS ) )
                    // InternalEssentialOCL.g:1564:1: (lv_ownedInitExpression_3_0= ruleStringLiteralExpCS )
                    {
                    // InternalEssentialOCL.g:1564:1: (lv_ownedInitExpression_3_0= ruleStringLiteralExpCS )
                    // InternalEssentialOCL.g:1565:3: lv_ownedInitExpression_3_0= ruleStringLiteralExpCS
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
    // InternalEssentialOCL.g:1589:1: entryRulePatternExpCS returns [EObject current=null] : iv_rulePatternExpCS= rulePatternExpCS EOF ;
    public final EObject entryRulePatternExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePatternExpCS = null;


        try {
            // InternalEssentialOCL.g:1590:2: (iv_rulePatternExpCS= rulePatternExpCS EOF )
            // InternalEssentialOCL.g:1591:2: iv_rulePatternExpCS= rulePatternExpCS EOF
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
    // InternalEssentialOCL.g:1598:1: rulePatternExpCS returns [EObject current=null] : ( ( (lv_patternVariableName_0_0= ruleUnrestrictedName ) )? otherlv_1= ':' ( (lv_ownedPatternType_2_0= ruleTypeExpCS ) ) ) ;
    public final EObject rulePatternExpCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_patternVariableName_0_0 = null;

        EObject lv_ownedPatternType_2_0 = null;


         enterRule();

        try {
            // InternalEssentialOCL.g:1601:28: ( ( ( (lv_patternVariableName_0_0= ruleUnrestrictedName ) )? otherlv_1= ':' ( (lv_ownedPatternType_2_0= ruleTypeExpCS ) ) ) )
            // InternalEssentialOCL.g:1602:1: ( ( (lv_patternVariableName_0_0= ruleUnrestrictedName ) )? otherlv_1= ':' ( (lv_ownedPatternType_2_0= ruleTypeExpCS ) ) )
            {
            // InternalEssentialOCL.g:1602:1: ( ( (lv_patternVariableName_0_0= ruleUnrestrictedName ) )? otherlv_1= ':' ( (lv_ownedPatternType_2_0= ruleTypeExpCS ) ) )
            // InternalEssentialOCL.g:1602:2: ( (lv_patternVariableName_0_0= ruleUnrestrictedName ) )? otherlv_1= ':' ( (lv_ownedPatternType_2_0= ruleTypeExpCS ) )
            {
            // InternalEssentialOCL.g:1602:2: ( (lv_patternVariableName_0_0= ruleUnrestrictedName ) )?
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( ((LA23_0>=RULE_SIMPLE_ID && LA23_0<=RULE_ESCAPED_ID)) ) {
                alt23=1;
            }
            switch (alt23) {
                case 1 :
                    // InternalEssentialOCL.g:1603:1: (lv_patternVariableName_0_0= ruleUnrestrictedName )
                    {
                    // InternalEssentialOCL.g:1603:1: (lv_patternVariableName_0_0= ruleUnrestrictedName )
                    // InternalEssentialOCL.g:1604:3: lv_patternVariableName_0_0= ruleUnrestrictedName
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getPatternExpCSAccess().getPatternVariableNameUnrestrictedNameParserRuleCall_0_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_11);
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
                              		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.UnrestrictedName");
                      	        afterParserOrEnumRuleCall();

                    }

                    }


                    }
                    break;

            }

            otherlv_1=(Token)match(input,58,FollowSets000.FOLLOW_4); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getPatternExpCSAccess().getColonKeyword_1());

            }
            // InternalEssentialOCL.g:1624:1: ( (lv_ownedPatternType_2_0= ruleTypeExpCS ) )
            // InternalEssentialOCL.g:1625:1: (lv_ownedPatternType_2_0= ruleTypeExpCS )
            {
            // InternalEssentialOCL.g:1625:1: (lv_ownedPatternType_2_0= ruleTypeExpCS )
            // InternalEssentialOCL.g:1626:3: lv_ownedPatternType_2_0= ruleTypeExpCS
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
    // InternalEssentialOCL.g:1650:1: entryRuleLambdaLiteralExpCS returns [EObject current=null] : iv_ruleLambdaLiteralExpCS= ruleLambdaLiteralExpCS EOF ;
    public final EObject entryRuleLambdaLiteralExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLambdaLiteralExpCS = null;


        try {
            // InternalEssentialOCL.g:1651:2: (iv_ruleLambdaLiteralExpCS= ruleLambdaLiteralExpCS EOF )
            // InternalEssentialOCL.g:1652:2: iv_ruleLambdaLiteralExpCS= ruleLambdaLiteralExpCS EOF
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
    // InternalEssentialOCL.g:1659:1: ruleLambdaLiteralExpCS returns [EObject current=null] : (otherlv_0= 'Lambda' otherlv_1= '{' ( (lv_ownedExpressionCS_2_0= ruleExpCS ) ) otherlv_3= '}' ) ;
    public final EObject ruleLambdaLiteralExpCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_ownedExpressionCS_2_0 = null;


         enterRule();

        try {
            // InternalEssentialOCL.g:1662:28: ( (otherlv_0= 'Lambda' otherlv_1= '{' ( (lv_ownedExpressionCS_2_0= ruleExpCS ) ) otherlv_3= '}' ) )
            // InternalEssentialOCL.g:1663:1: (otherlv_0= 'Lambda' otherlv_1= '{' ( (lv_ownedExpressionCS_2_0= ruleExpCS ) ) otherlv_3= '}' )
            {
            // InternalEssentialOCL.g:1663:1: (otherlv_0= 'Lambda' otherlv_1= '{' ( (lv_ownedExpressionCS_2_0= ruleExpCS ) ) otherlv_3= '}' )
            // InternalEssentialOCL.g:1663:3: otherlv_0= 'Lambda' otherlv_1= '{' ( (lv_ownedExpressionCS_2_0= ruleExpCS ) ) otherlv_3= '}'
            {
            otherlv_0=(Token)match(input,63,FollowSets000.FOLLOW_12); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getLambdaLiteralExpCSAccess().getLambdaKeyword_0());

            }
            otherlv_1=(Token)match(input,59,FollowSets000.FOLLOW_17); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getLambdaLiteralExpCSAccess().getLeftCurlyBracketKeyword_1());

            }
            // InternalEssentialOCL.g:1671:1: ( (lv_ownedExpressionCS_2_0= ruleExpCS ) )
            // InternalEssentialOCL.g:1672:1: (lv_ownedExpressionCS_2_0= ruleExpCS )
            {
            // InternalEssentialOCL.g:1672:1: (lv_ownedExpressionCS_2_0= ruleExpCS )
            // InternalEssentialOCL.g:1673:3: lv_ownedExpressionCS_2_0= ruleExpCS
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getLambdaLiteralExpCSAccess().getOwnedExpressionCSExpCSParserRuleCall_2_0());

            }
            pushFollow(FollowSets000.FOLLOW_19);
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

            otherlv_3=(Token)match(input,60,FollowSets000.FOLLOW_2); if (state.failed) return current;
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
    // InternalEssentialOCL.g:1701:1: entryRuleMapLiteralExpCS returns [EObject current=null] : iv_ruleMapLiteralExpCS= ruleMapLiteralExpCS EOF ;
    public final EObject entryRuleMapLiteralExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMapLiteralExpCS = null;


        try {
            // InternalEssentialOCL.g:1702:2: (iv_ruleMapLiteralExpCS= ruleMapLiteralExpCS EOF )
            // InternalEssentialOCL.g:1703:2: iv_ruleMapLiteralExpCS= ruleMapLiteralExpCS EOF
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
    // InternalEssentialOCL.g:1710:1: ruleMapLiteralExpCS returns [EObject current=null] : ( ( (lv_ownedType_0_0= ruleMapTypeCS ) ) otherlv_1= '{' ( ( (lv_ownedParts_2_0= ruleMapLiteralPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleMapLiteralPartCS ) ) )* )? otherlv_5= '}' ) ;
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
            // InternalEssentialOCL.g:1713:28: ( ( ( (lv_ownedType_0_0= ruleMapTypeCS ) ) otherlv_1= '{' ( ( (lv_ownedParts_2_0= ruleMapLiteralPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleMapLiteralPartCS ) ) )* )? otherlv_5= '}' ) )
            // InternalEssentialOCL.g:1714:1: ( ( (lv_ownedType_0_0= ruleMapTypeCS ) ) otherlv_1= '{' ( ( (lv_ownedParts_2_0= ruleMapLiteralPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleMapLiteralPartCS ) ) )* )? otherlv_5= '}' )
            {
            // InternalEssentialOCL.g:1714:1: ( ( (lv_ownedType_0_0= ruleMapTypeCS ) ) otherlv_1= '{' ( ( (lv_ownedParts_2_0= ruleMapLiteralPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleMapLiteralPartCS ) ) )* )? otherlv_5= '}' )
            // InternalEssentialOCL.g:1714:2: ( (lv_ownedType_0_0= ruleMapTypeCS ) ) otherlv_1= '{' ( ( (lv_ownedParts_2_0= ruleMapLiteralPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleMapLiteralPartCS ) ) )* )? otherlv_5= '}'
            {
            // InternalEssentialOCL.g:1714:2: ( (lv_ownedType_0_0= ruleMapTypeCS ) )
            // InternalEssentialOCL.g:1715:1: (lv_ownedType_0_0= ruleMapTypeCS )
            {
            // InternalEssentialOCL.g:1715:1: (lv_ownedType_0_0= ruleMapTypeCS )
            // InternalEssentialOCL.g:1716:3: lv_ownedType_0_0= ruleMapTypeCS
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getMapLiteralExpCSAccess().getOwnedTypeMapTypeCSParserRuleCall_0_0());

            }
            pushFollow(FollowSets000.FOLLOW_12);
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

            otherlv_1=(Token)match(input,59,FollowSets000.FOLLOW_21); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getMapLiteralExpCSAccess().getLeftCurlyBracketKeyword_1());

            }
            // InternalEssentialOCL.g:1736:1: ( ( (lv_ownedParts_2_0= ruleMapLiteralPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleMapLiteralPartCS ) ) )* )?
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( ((LA25_0>=RULE_SIMPLE_ID && LA25_0<=RULE_SINGLE_QUOTED_STRING)||(LA25_0>=16 && LA25_0<=19)||(LA25_0>=40 && LA25_0<=55)||LA25_0==63||(LA25_0>=65 && LA25_0<=68)||LA25_0==76||(LA25_0>=81 && LA25_0<=82)) ) {
                alt25=1;
            }
            switch (alt25) {
                case 1 :
                    // InternalEssentialOCL.g:1736:2: ( (lv_ownedParts_2_0= ruleMapLiteralPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleMapLiteralPartCS ) ) )*
                    {
                    // InternalEssentialOCL.g:1736:2: ( (lv_ownedParts_2_0= ruleMapLiteralPartCS ) )
                    // InternalEssentialOCL.g:1737:1: (lv_ownedParts_2_0= ruleMapLiteralPartCS )
                    {
                    // InternalEssentialOCL.g:1737:1: (lv_ownedParts_2_0= ruleMapLiteralPartCS )
                    // InternalEssentialOCL.g:1738:3: lv_ownedParts_2_0= ruleMapLiteralPartCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getMapLiteralExpCSAccess().getOwnedPartsMapLiteralPartCSParserRuleCall_2_0_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_14);
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

                    // InternalEssentialOCL.g:1754:2: (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleMapLiteralPartCS ) ) )*
                    loop24:
                    do {
                        int alt24=2;
                        int LA24_0 = input.LA(1);

                        if ( (LA24_0==57) ) {
                            alt24=1;
                        }


                        switch (alt24) {
                    	case 1 :
                    	    // InternalEssentialOCL.g:1754:4: otherlv_3= ',' ( (lv_ownedParts_4_0= ruleMapLiteralPartCS ) )
                    	    {
                    	    otherlv_3=(Token)match(input,57,FollowSets000.FOLLOW_17); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_3, grammarAccess.getMapLiteralExpCSAccess().getCommaKeyword_2_1_0());

                    	    }
                    	    // InternalEssentialOCL.g:1758:1: ( (lv_ownedParts_4_0= ruleMapLiteralPartCS ) )
                    	    // InternalEssentialOCL.g:1759:1: (lv_ownedParts_4_0= ruleMapLiteralPartCS )
                    	    {
                    	    // InternalEssentialOCL.g:1759:1: (lv_ownedParts_4_0= ruleMapLiteralPartCS )
                    	    // InternalEssentialOCL.g:1760:3: lv_ownedParts_4_0= ruleMapLiteralPartCS
                    	    {
                    	    if ( state.backtracking==0 ) {

                    	      	        newCompositeNode(grammarAccess.getMapLiteralExpCSAccess().getOwnedPartsMapLiteralPartCSParserRuleCall_2_1_1_0());

                    	    }
                    	    pushFollow(FollowSets000.FOLLOW_14);
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
                    	    break loop24;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_5=(Token)match(input,60,FollowSets000.FOLLOW_2); if (state.failed) return current;
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
    // InternalEssentialOCL.g:1788:1: entryRuleMapLiteralPartCS returns [EObject current=null] : iv_ruleMapLiteralPartCS= ruleMapLiteralPartCS EOF ;
    public final EObject entryRuleMapLiteralPartCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMapLiteralPartCS = null;


        try {
            // InternalEssentialOCL.g:1789:2: (iv_ruleMapLiteralPartCS= ruleMapLiteralPartCS EOF )
            // InternalEssentialOCL.g:1790:2: iv_ruleMapLiteralPartCS= ruleMapLiteralPartCS EOF
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
    // InternalEssentialOCL.g:1797:1: ruleMapLiteralPartCS returns [EObject current=null] : ( ( (lv_ownedKey_0_0= ruleExpCS ) ) otherlv_1= '<-' ( (lv_ownedValue_2_0= ruleExpCS ) ) ) ;
    public final EObject ruleMapLiteralPartCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject lv_ownedKey_0_0 = null;

        EObject lv_ownedValue_2_0 = null;


         enterRule();

        try {
            // InternalEssentialOCL.g:1800:28: ( ( ( (lv_ownedKey_0_0= ruleExpCS ) ) otherlv_1= '<-' ( (lv_ownedValue_2_0= ruleExpCS ) ) ) )
            // InternalEssentialOCL.g:1801:1: ( ( (lv_ownedKey_0_0= ruleExpCS ) ) otherlv_1= '<-' ( (lv_ownedValue_2_0= ruleExpCS ) ) )
            {
            // InternalEssentialOCL.g:1801:1: ( ( (lv_ownedKey_0_0= ruleExpCS ) ) otherlv_1= '<-' ( (lv_ownedValue_2_0= ruleExpCS ) ) )
            // InternalEssentialOCL.g:1801:2: ( (lv_ownedKey_0_0= ruleExpCS ) ) otherlv_1= '<-' ( (lv_ownedValue_2_0= ruleExpCS ) )
            {
            // InternalEssentialOCL.g:1801:2: ( (lv_ownedKey_0_0= ruleExpCS ) )
            // InternalEssentialOCL.g:1802:1: (lv_ownedKey_0_0= ruleExpCS )
            {
            // InternalEssentialOCL.g:1802:1: (lv_ownedKey_0_0= ruleExpCS )
            // InternalEssentialOCL.g:1803:3: lv_ownedKey_0_0= ruleExpCS
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getMapLiteralPartCSAccess().getOwnedKeyExpCSParserRuleCall_0_0());

            }
            pushFollow(FollowSets000.FOLLOW_22);
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

            otherlv_1=(Token)match(input,64,FollowSets000.FOLLOW_17); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getMapLiteralPartCSAccess().getLessThanSignHyphenMinusKeyword_1());

            }
            // InternalEssentialOCL.g:1823:1: ( (lv_ownedValue_2_0= ruleExpCS ) )
            // InternalEssentialOCL.g:1824:1: (lv_ownedValue_2_0= ruleExpCS )
            {
            // InternalEssentialOCL.g:1824:1: (lv_ownedValue_2_0= ruleExpCS )
            // InternalEssentialOCL.g:1825:3: lv_ownedValue_2_0= ruleExpCS
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
    // InternalEssentialOCL.g:1849:1: entryRulePrimitiveLiteralExpCS returns [EObject current=null] : iv_rulePrimitiveLiteralExpCS= rulePrimitiveLiteralExpCS EOF ;
    public final EObject entryRulePrimitiveLiteralExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePrimitiveLiteralExpCS = null;


        try {
            // InternalEssentialOCL.g:1850:2: (iv_rulePrimitiveLiteralExpCS= rulePrimitiveLiteralExpCS EOF )
            // InternalEssentialOCL.g:1851:2: iv_rulePrimitiveLiteralExpCS= rulePrimitiveLiteralExpCS EOF
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
    // InternalEssentialOCL.g:1858:1: rulePrimitiveLiteralExpCS returns [EObject current=null] : (this_NumberLiteralExpCS_0= ruleNumberLiteralExpCS | this_StringLiteralExpCS_1= ruleStringLiteralExpCS | this_BooleanLiteralExpCS_2= ruleBooleanLiteralExpCS | this_UnlimitedNaturalLiteralExpCS_3= ruleUnlimitedNaturalLiteralExpCS | this_InvalidLiteralExpCS_4= ruleInvalidLiteralExpCS | this_NullLiteralExpCS_5= ruleNullLiteralExpCS ) ;
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
            // InternalEssentialOCL.g:1861:28: ( (this_NumberLiteralExpCS_0= ruleNumberLiteralExpCS | this_StringLiteralExpCS_1= ruleStringLiteralExpCS | this_BooleanLiteralExpCS_2= ruleBooleanLiteralExpCS | this_UnlimitedNaturalLiteralExpCS_3= ruleUnlimitedNaturalLiteralExpCS | this_InvalidLiteralExpCS_4= ruleInvalidLiteralExpCS | this_NullLiteralExpCS_5= ruleNullLiteralExpCS ) )
            // InternalEssentialOCL.g:1862:1: (this_NumberLiteralExpCS_0= ruleNumberLiteralExpCS | this_StringLiteralExpCS_1= ruleStringLiteralExpCS | this_BooleanLiteralExpCS_2= ruleBooleanLiteralExpCS | this_UnlimitedNaturalLiteralExpCS_3= ruleUnlimitedNaturalLiteralExpCS | this_InvalidLiteralExpCS_4= ruleInvalidLiteralExpCS | this_NullLiteralExpCS_5= ruleNullLiteralExpCS )
            {
            // InternalEssentialOCL.g:1862:1: (this_NumberLiteralExpCS_0= ruleNumberLiteralExpCS | this_StringLiteralExpCS_1= ruleStringLiteralExpCS | this_BooleanLiteralExpCS_2= ruleBooleanLiteralExpCS | this_UnlimitedNaturalLiteralExpCS_3= ruleUnlimitedNaturalLiteralExpCS | this_InvalidLiteralExpCS_4= ruleInvalidLiteralExpCS | this_NullLiteralExpCS_5= ruleNullLiteralExpCS )
            int alt26=6;
            switch ( input.LA(1) ) {
            case RULE_INT:
                {
                alt26=1;
                }
                break;
            case RULE_SINGLE_QUOTED_STRING:
                {
                alt26=2;
                }
                break;
            case 65:
            case 66:
                {
                alt26=3;
                }
                break;
            case 19:
                {
                alt26=4;
                }
                break;
            case 67:
                {
                alt26=5;
                }
                break;
            case 68:
                {
                alt26=6;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 26, 0, input);

                throw nvae;
            }

            switch (alt26) {
                case 1 :
                    // InternalEssentialOCL.g:1863:2: this_NumberLiteralExpCS_0= ruleNumberLiteralExpCS
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
                    // InternalEssentialOCL.g:1876:2: this_StringLiteralExpCS_1= ruleStringLiteralExpCS
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
                    // InternalEssentialOCL.g:1889:2: this_BooleanLiteralExpCS_2= ruleBooleanLiteralExpCS
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
                    // InternalEssentialOCL.g:1902:2: this_UnlimitedNaturalLiteralExpCS_3= ruleUnlimitedNaturalLiteralExpCS
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
                    // InternalEssentialOCL.g:1915:2: this_InvalidLiteralExpCS_4= ruleInvalidLiteralExpCS
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
                    // InternalEssentialOCL.g:1928:2: this_NullLiteralExpCS_5= ruleNullLiteralExpCS
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
    // InternalEssentialOCL.g:1947:1: entryRuleTupleLiteralExpCS returns [EObject current=null] : iv_ruleTupleLiteralExpCS= ruleTupleLiteralExpCS EOF ;
    public final EObject entryRuleTupleLiteralExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTupleLiteralExpCS = null;


        try {
            // InternalEssentialOCL.g:1948:2: (iv_ruleTupleLiteralExpCS= ruleTupleLiteralExpCS EOF )
            // InternalEssentialOCL.g:1949:2: iv_ruleTupleLiteralExpCS= ruleTupleLiteralExpCS EOF
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
    // InternalEssentialOCL.g:1956:1: ruleTupleLiteralExpCS returns [EObject current=null] : (otherlv_0= 'Tuple' otherlv_1= '{' ( (lv_ownedParts_2_0= ruleTupleLiteralPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleTupleLiteralPartCS ) ) )* otherlv_5= '}' ) ;
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
            // InternalEssentialOCL.g:1959:28: ( (otherlv_0= 'Tuple' otherlv_1= '{' ( (lv_ownedParts_2_0= ruleTupleLiteralPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleTupleLiteralPartCS ) ) )* otherlv_5= '}' ) )
            // InternalEssentialOCL.g:1960:1: (otherlv_0= 'Tuple' otherlv_1= '{' ( (lv_ownedParts_2_0= ruleTupleLiteralPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleTupleLiteralPartCS ) ) )* otherlv_5= '}' )
            {
            // InternalEssentialOCL.g:1960:1: (otherlv_0= 'Tuple' otherlv_1= '{' ( (lv_ownedParts_2_0= ruleTupleLiteralPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleTupleLiteralPartCS ) ) )* otherlv_5= '}' )
            // InternalEssentialOCL.g:1960:3: otherlv_0= 'Tuple' otherlv_1= '{' ( (lv_ownedParts_2_0= ruleTupleLiteralPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleTupleLiteralPartCS ) ) )* otherlv_5= '}'
            {
            otherlv_0=(Token)match(input,41,FollowSets000.FOLLOW_12); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getTupleLiteralExpCSAccess().getTupleKeyword_0());

            }
            otherlv_1=(Token)match(input,59,FollowSets000.FOLLOW_10); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getTupleLiteralExpCSAccess().getLeftCurlyBracketKeyword_1());

            }
            // InternalEssentialOCL.g:1968:1: ( (lv_ownedParts_2_0= ruleTupleLiteralPartCS ) )
            // InternalEssentialOCL.g:1969:1: (lv_ownedParts_2_0= ruleTupleLiteralPartCS )
            {
            // InternalEssentialOCL.g:1969:1: (lv_ownedParts_2_0= ruleTupleLiteralPartCS )
            // InternalEssentialOCL.g:1970:3: lv_ownedParts_2_0= ruleTupleLiteralPartCS
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getTupleLiteralExpCSAccess().getOwnedPartsTupleLiteralPartCSParserRuleCall_2_0());

            }
            pushFollow(FollowSets000.FOLLOW_14);
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

            // InternalEssentialOCL.g:1986:2: (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleTupleLiteralPartCS ) ) )*
            loop27:
            do {
                int alt27=2;
                int LA27_0 = input.LA(1);

                if ( (LA27_0==57) ) {
                    alt27=1;
                }


                switch (alt27) {
            	case 1 :
            	    // InternalEssentialOCL.g:1986:4: otherlv_3= ',' ( (lv_ownedParts_4_0= ruleTupleLiteralPartCS ) )
            	    {
            	    otherlv_3=(Token)match(input,57,FollowSets000.FOLLOW_10); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_3, grammarAccess.getTupleLiteralExpCSAccess().getCommaKeyword_3_0());

            	    }
            	    // InternalEssentialOCL.g:1990:1: ( (lv_ownedParts_4_0= ruleTupleLiteralPartCS ) )
            	    // InternalEssentialOCL.g:1991:1: (lv_ownedParts_4_0= ruleTupleLiteralPartCS )
            	    {
            	    // InternalEssentialOCL.g:1991:1: (lv_ownedParts_4_0= ruleTupleLiteralPartCS )
            	    // InternalEssentialOCL.g:1992:3: lv_ownedParts_4_0= ruleTupleLiteralPartCS
            	    {
            	    if ( state.backtracking==0 ) {

            	      	        newCompositeNode(grammarAccess.getTupleLiteralExpCSAccess().getOwnedPartsTupleLiteralPartCSParserRuleCall_3_1_0());

            	    }
            	    pushFollow(FollowSets000.FOLLOW_14);
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
            	    break loop27;
                }
            } while (true);

            otherlv_5=(Token)match(input,60,FollowSets000.FOLLOW_2); if (state.failed) return current;
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
    // InternalEssentialOCL.g:2020:1: entryRuleTupleLiteralPartCS returns [EObject current=null] : iv_ruleTupleLiteralPartCS= ruleTupleLiteralPartCS EOF ;
    public final EObject entryRuleTupleLiteralPartCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTupleLiteralPartCS = null;


        try {
            // InternalEssentialOCL.g:2021:2: (iv_ruleTupleLiteralPartCS= ruleTupleLiteralPartCS EOF )
            // InternalEssentialOCL.g:2022:2: iv_ruleTupleLiteralPartCS= ruleTupleLiteralPartCS EOF
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
    // InternalEssentialOCL.g:2029:1: ruleTupleLiteralPartCS returns [EObject current=null] : ( ( (lv_name_0_0= ruleUnrestrictedName ) ) (otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) ) )? otherlv_3= '=' ( (lv_ownedInitExpression_4_0= ruleExpCS ) ) ) ;
    public final EObject ruleTupleLiteralPartCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        AntlrDatatypeRuleToken lv_name_0_0 = null;

        EObject lv_ownedType_2_0 = null;

        EObject lv_ownedInitExpression_4_0 = null;


         enterRule();

        try {
            // InternalEssentialOCL.g:2032:28: ( ( ( (lv_name_0_0= ruleUnrestrictedName ) ) (otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) ) )? otherlv_3= '=' ( (lv_ownedInitExpression_4_0= ruleExpCS ) ) ) )
            // InternalEssentialOCL.g:2033:1: ( ( (lv_name_0_0= ruleUnrestrictedName ) ) (otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) ) )? otherlv_3= '=' ( (lv_ownedInitExpression_4_0= ruleExpCS ) ) )
            {
            // InternalEssentialOCL.g:2033:1: ( ( (lv_name_0_0= ruleUnrestrictedName ) ) (otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) ) )? otherlv_3= '=' ( (lv_ownedInitExpression_4_0= ruleExpCS ) ) )
            // InternalEssentialOCL.g:2033:2: ( (lv_name_0_0= ruleUnrestrictedName ) ) (otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) ) )? otherlv_3= '=' ( (lv_ownedInitExpression_4_0= ruleExpCS ) )
            {
            // InternalEssentialOCL.g:2033:2: ( (lv_name_0_0= ruleUnrestrictedName ) )
            // InternalEssentialOCL.g:2034:1: (lv_name_0_0= ruleUnrestrictedName )
            {
            // InternalEssentialOCL.g:2034:1: (lv_name_0_0= ruleUnrestrictedName )
            // InternalEssentialOCL.g:2035:3: lv_name_0_0= ruleUnrestrictedName
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getTupleLiteralPartCSAccess().getNameUnrestrictedNameParserRuleCall_0_0());

            }
            pushFollow(FollowSets000.FOLLOW_23);
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
                      		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.UnrestrictedName");
              	        afterParserOrEnumRuleCall();

            }

            }


            }

            // InternalEssentialOCL.g:2051:2: (otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) ) )?
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==58) ) {
                alt28=1;
            }
            switch (alt28) {
                case 1 :
                    // InternalEssentialOCL.g:2051:4: otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) )
                    {
                    otherlv_1=(Token)match(input,58,FollowSets000.FOLLOW_4); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_1, grammarAccess.getTupleLiteralPartCSAccess().getColonKeyword_1_0());

                    }
                    // InternalEssentialOCL.g:2055:1: ( (lv_ownedType_2_0= ruleTypeExpCS ) )
                    // InternalEssentialOCL.g:2056:1: (lv_ownedType_2_0= ruleTypeExpCS )
                    {
                    // InternalEssentialOCL.g:2056:1: (lv_ownedType_2_0= ruleTypeExpCS )
                    // InternalEssentialOCL.g:2057:3: lv_ownedType_2_0= ruleTypeExpCS
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

            otherlv_3=(Token)match(input,26,FollowSets000.FOLLOW_17); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_3, grammarAccess.getTupleLiteralPartCSAccess().getEqualsSignKeyword_2());

            }
            // InternalEssentialOCL.g:2077:1: ( (lv_ownedInitExpression_4_0= ruleExpCS ) )
            // InternalEssentialOCL.g:2078:1: (lv_ownedInitExpression_4_0= ruleExpCS )
            {
            // InternalEssentialOCL.g:2078:1: (lv_ownedInitExpression_4_0= ruleExpCS )
            // InternalEssentialOCL.g:2079:3: lv_ownedInitExpression_4_0= ruleExpCS
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
    // InternalEssentialOCL.g:2103:1: entryRuleNumberLiteralExpCS returns [EObject current=null] : iv_ruleNumberLiteralExpCS= ruleNumberLiteralExpCS EOF ;
    public final EObject entryRuleNumberLiteralExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNumberLiteralExpCS = null;


        try {
            // InternalEssentialOCL.g:2104:2: (iv_ruleNumberLiteralExpCS= ruleNumberLiteralExpCS EOF )
            // InternalEssentialOCL.g:2105:2: iv_ruleNumberLiteralExpCS= ruleNumberLiteralExpCS EOF
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
    // InternalEssentialOCL.g:2112:1: ruleNumberLiteralExpCS returns [EObject current=null] : ( (lv_symbol_0_0= ruleNUMBER_LITERAL ) ) ;
    public final EObject ruleNumberLiteralExpCS() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_symbol_0_0 = null;


         enterRule();

        try {
            // InternalEssentialOCL.g:2115:28: ( ( (lv_symbol_0_0= ruleNUMBER_LITERAL ) ) )
            // InternalEssentialOCL.g:2116:1: ( (lv_symbol_0_0= ruleNUMBER_LITERAL ) )
            {
            // InternalEssentialOCL.g:2116:1: ( (lv_symbol_0_0= ruleNUMBER_LITERAL ) )
            // InternalEssentialOCL.g:2117:1: (lv_symbol_0_0= ruleNUMBER_LITERAL )
            {
            // InternalEssentialOCL.g:2117:1: (lv_symbol_0_0= ruleNUMBER_LITERAL )
            // InternalEssentialOCL.g:2118:3: lv_symbol_0_0= ruleNUMBER_LITERAL
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
    // InternalEssentialOCL.g:2142:1: entryRuleStringLiteralExpCS returns [EObject current=null] : iv_ruleStringLiteralExpCS= ruleStringLiteralExpCS EOF ;
    public final EObject entryRuleStringLiteralExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleStringLiteralExpCS = null;


        try {
            // InternalEssentialOCL.g:2143:2: (iv_ruleStringLiteralExpCS= ruleStringLiteralExpCS EOF )
            // InternalEssentialOCL.g:2144:2: iv_ruleStringLiteralExpCS= ruleStringLiteralExpCS EOF
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
    // InternalEssentialOCL.g:2151:1: ruleStringLiteralExpCS returns [EObject current=null] : ( (lv_segments_0_0= ruleStringLiteral ) )+ ;
    public final EObject ruleStringLiteralExpCS() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_segments_0_0 = null;


         enterRule();

        try {
            // InternalEssentialOCL.g:2154:28: ( ( (lv_segments_0_0= ruleStringLiteral ) )+ )
            // InternalEssentialOCL.g:2155:1: ( (lv_segments_0_0= ruleStringLiteral ) )+
            {
            // InternalEssentialOCL.g:2155:1: ( (lv_segments_0_0= ruleStringLiteral ) )+
            int cnt29=0;
            loop29:
            do {
                int alt29=2;
                int LA29_0 = input.LA(1);

                if ( (LA29_0==RULE_SINGLE_QUOTED_STRING) ) {
                    alt29=1;
                }


                switch (alt29) {
            	case 1 :
            	    // InternalEssentialOCL.g:2156:1: (lv_segments_0_0= ruleStringLiteral )
            	    {
            	    // InternalEssentialOCL.g:2156:1: (lv_segments_0_0= ruleStringLiteral )
            	    // InternalEssentialOCL.g:2157:3: lv_segments_0_0= ruleStringLiteral
            	    {
            	    if ( state.backtracking==0 ) {

            	      	        newCompositeNode(grammarAccess.getStringLiteralExpCSAccess().getSegmentsStringLiteralParserRuleCall_0());

            	    }
            	    pushFollow(FollowSets000.FOLLOW_24);
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
            	    if ( cnt29 >= 1 ) break loop29;
            	    if (state.backtracking>0) {state.failed=true; return current;}
                        EarlyExitException eee =
                            new EarlyExitException(29, input);
                        throw eee;
                }
                cnt29++;
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
    // InternalEssentialOCL.g:2181:1: entryRuleBooleanLiteralExpCS returns [EObject current=null] : iv_ruleBooleanLiteralExpCS= ruleBooleanLiteralExpCS EOF ;
    public final EObject entryRuleBooleanLiteralExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleBooleanLiteralExpCS = null;


        try {
            // InternalEssentialOCL.g:2182:2: (iv_ruleBooleanLiteralExpCS= ruleBooleanLiteralExpCS EOF )
            // InternalEssentialOCL.g:2183:2: iv_ruleBooleanLiteralExpCS= ruleBooleanLiteralExpCS EOF
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
    // InternalEssentialOCL.g:2190:1: ruleBooleanLiteralExpCS returns [EObject current=null] : ( ( (lv_symbol_0_0= 'true' ) ) | ( (lv_symbol_1_0= 'false' ) ) ) ;
    public final EObject ruleBooleanLiteralExpCS() throws RecognitionException {
        EObject current = null;

        Token lv_symbol_0_0=null;
        Token lv_symbol_1_0=null;

         enterRule();

        try {
            // InternalEssentialOCL.g:2193:28: ( ( ( (lv_symbol_0_0= 'true' ) ) | ( (lv_symbol_1_0= 'false' ) ) ) )
            // InternalEssentialOCL.g:2194:1: ( ( (lv_symbol_0_0= 'true' ) ) | ( (lv_symbol_1_0= 'false' ) ) )
            {
            // InternalEssentialOCL.g:2194:1: ( ( (lv_symbol_0_0= 'true' ) ) | ( (lv_symbol_1_0= 'false' ) ) )
            int alt30=2;
            int LA30_0 = input.LA(1);

            if ( (LA30_0==65) ) {
                alt30=1;
            }
            else if ( (LA30_0==66) ) {
                alt30=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 30, 0, input);

                throw nvae;
            }
            switch (alt30) {
                case 1 :
                    // InternalEssentialOCL.g:2194:2: ( (lv_symbol_0_0= 'true' ) )
                    {
                    // InternalEssentialOCL.g:2194:2: ( (lv_symbol_0_0= 'true' ) )
                    // InternalEssentialOCL.g:2195:1: (lv_symbol_0_0= 'true' )
                    {
                    // InternalEssentialOCL.g:2195:1: (lv_symbol_0_0= 'true' )
                    // InternalEssentialOCL.g:2196:3: lv_symbol_0_0= 'true'
                    {
                    lv_symbol_0_0=(Token)match(input,65,FollowSets000.FOLLOW_2); if (state.failed) return current;
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
                    // InternalEssentialOCL.g:2210:6: ( (lv_symbol_1_0= 'false' ) )
                    {
                    // InternalEssentialOCL.g:2210:6: ( (lv_symbol_1_0= 'false' ) )
                    // InternalEssentialOCL.g:2211:1: (lv_symbol_1_0= 'false' )
                    {
                    // InternalEssentialOCL.g:2211:1: (lv_symbol_1_0= 'false' )
                    // InternalEssentialOCL.g:2212:3: lv_symbol_1_0= 'false'
                    {
                    lv_symbol_1_0=(Token)match(input,66,FollowSets000.FOLLOW_2); if (state.failed) return current;
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
    // InternalEssentialOCL.g:2233:1: entryRuleUnlimitedNaturalLiteralExpCS returns [EObject current=null] : iv_ruleUnlimitedNaturalLiteralExpCS= ruleUnlimitedNaturalLiteralExpCS EOF ;
    public final EObject entryRuleUnlimitedNaturalLiteralExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleUnlimitedNaturalLiteralExpCS = null;


        try {
            // InternalEssentialOCL.g:2234:2: (iv_ruleUnlimitedNaturalLiteralExpCS= ruleUnlimitedNaturalLiteralExpCS EOF )
            // InternalEssentialOCL.g:2235:2: iv_ruleUnlimitedNaturalLiteralExpCS= ruleUnlimitedNaturalLiteralExpCS EOF
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
    // InternalEssentialOCL.g:2242:1: ruleUnlimitedNaturalLiteralExpCS returns [EObject current=null] : ( () otherlv_1= '*' ) ;
    public final EObject ruleUnlimitedNaturalLiteralExpCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;

         enterRule();

        try {
            // InternalEssentialOCL.g:2245:28: ( ( () otherlv_1= '*' ) )
            // InternalEssentialOCL.g:2246:1: ( () otherlv_1= '*' )
            {
            // InternalEssentialOCL.g:2246:1: ( () otherlv_1= '*' )
            // InternalEssentialOCL.g:2246:2: () otherlv_1= '*'
            {
            // InternalEssentialOCL.g:2246:2: ()
            // InternalEssentialOCL.g:2247:2:
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

            otherlv_1=(Token)match(input,19,FollowSets000.FOLLOW_2); if (state.failed) return current;
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
    // InternalEssentialOCL.g:2267:1: entryRuleInvalidLiteralExpCS returns [EObject current=null] : iv_ruleInvalidLiteralExpCS= ruleInvalidLiteralExpCS EOF ;
    public final EObject entryRuleInvalidLiteralExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleInvalidLiteralExpCS = null;


        try {
            // InternalEssentialOCL.g:2268:2: (iv_ruleInvalidLiteralExpCS= ruleInvalidLiteralExpCS EOF )
            // InternalEssentialOCL.g:2269:2: iv_ruleInvalidLiteralExpCS= ruleInvalidLiteralExpCS EOF
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
    // InternalEssentialOCL.g:2276:1: ruleInvalidLiteralExpCS returns [EObject current=null] : ( () otherlv_1= 'invalid' ) ;
    public final EObject ruleInvalidLiteralExpCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;

         enterRule();

        try {
            // InternalEssentialOCL.g:2279:28: ( ( () otherlv_1= 'invalid' ) )
            // InternalEssentialOCL.g:2280:1: ( () otherlv_1= 'invalid' )
            {
            // InternalEssentialOCL.g:2280:1: ( () otherlv_1= 'invalid' )
            // InternalEssentialOCL.g:2280:2: () otherlv_1= 'invalid'
            {
            // InternalEssentialOCL.g:2280:2: ()
            // InternalEssentialOCL.g:2281:2:
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

            otherlv_1=(Token)match(input,67,FollowSets000.FOLLOW_2); if (state.failed) return current;
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
    // InternalEssentialOCL.g:2301:1: entryRuleNullLiteralExpCS returns [EObject current=null] : iv_ruleNullLiteralExpCS= ruleNullLiteralExpCS EOF ;
    public final EObject entryRuleNullLiteralExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNullLiteralExpCS = null;


        try {
            // InternalEssentialOCL.g:2302:2: (iv_ruleNullLiteralExpCS= ruleNullLiteralExpCS EOF )
            // InternalEssentialOCL.g:2303:2: iv_ruleNullLiteralExpCS= ruleNullLiteralExpCS EOF
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
    // InternalEssentialOCL.g:2310:1: ruleNullLiteralExpCS returns [EObject current=null] : ( () otherlv_1= 'null' ) ;
    public final EObject ruleNullLiteralExpCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;

         enterRule();

        try {
            // InternalEssentialOCL.g:2313:28: ( ( () otherlv_1= 'null' ) )
            // InternalEssentialOCL.g:2314:1: ( () otherlv_1= 'null' )
            {
            // InternalEssentialOCL.g:2314:1: ( () otherlv_1= 'null' )
            // InternalEssentialOCL.g:2314:2: () otherlv_1= 'null'
            {
            // InternalEssentialOCL.g:2314:2: ()
            // InternalEssentialOCL.g:2315:2:
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

            otherlv_1=(Token)match(input,68,FollowSets000.FOLLOW_2); if (state.failed) return current;
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
    // InternalEssentialOCL.g:2335:1: entryRuleTypeLiteralCS returns [EObject current=null] : iv_ruleTypeLiteralCS= ruleTypeLiteralCS EOF ;
    public final EObject entryRuleTypeLiteralCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTypeLiteralCS = null;


        try {
            // InternalEssentialOCL.g:2336:2: (iv_ruleTypeLiteralCS= ruleTypeLiteralCS EOF )
            // InternalEssentialOCL.g:2337:2: iv_ruleTypeLiteralCS= ruleTypeLiteralCS EOF
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
    // InternalEssentialOCL.g:2344:1: ruleTypeLiteralCS returns [EObject current=null] : (this_PrimitiveTypeCS_0= rulePrimitiveTypeCS | this_CollectionTypeCS_1= ruleCollectionTypeCS | this_MapTypeCS_2= ruleMapTypeCS | this_TupleTypeCS_3= ruleTupleTypeCS ) ;
    public final EObject ruleTypeLiteralCS() throws RecognitionException {
        EObject current = null;

        EObject this_PrimitiveTypeCS_0 = null;

        EObject this_CollectionTypeCS_1 = null;

        EObject this_MapTypeCS_2 = null;

        EObject this_TupleTypeCS_3 = null;


         enterRule();

        try {
            // InternalEssentialOCL.g:2347:28: ( (this_PrimitiveTypeCS_0= rulePrimitiveTypeCS | this_CollectionTypeCS_1= ruleCollectionTypeCS | this_MapTypeCS_2= ruleMapTypeCS | this_TupleTypeCS_3= ruleTupleTypeCS ) )
            // InternalEssentialOCL.g:2348:1: (this_PrimitiveTypeCS_0= rulePrimitiveTypeCS | this_CollectionTypeCS_1= ruleCollectionTypeCS | this_MapTypeCS_2= ruleMapTypeCS | this_TupleTypeCS_3= ruleTupleTypeCS )
            {
            // InternalEssentialOCL.g:2348:1: (this_PrimitiveTypeCS_0= rulePrimitiveTypeCS | this_CollectionTypeCS_1= ruleCollectionTypeCS | this_MapTypeCS_2= ruleMapTypeCS | this_TupleTypeCS_3= ruleTupleTypeCS )
            int alt31=4;
            switch ( input.LA(1) ) {
            case 42:
            case 43:
            case 44:
            case 45:
            case 46:
            case 47:
            case 48:
            case 49:
                {
                alt31=1;
                }
                break;
            case 50:
            case 51:
            case 52:
            case 53:
            case 54:
                {
                alt31=2;
                }
                break;
            case 40:
                {
                alt31=3;
                }
                break;
            case 41:
                {
                alt31=4;
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
                    // InternalEssentialOCL.g:2349:2: this_PrimitiveTypeCS_0= rulePrimitiveTypeCS
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
                    // InternalEssentialOCL.g:2362:2: this_CollectionTypeCS_1= ruleCollectionTypeCS
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
                    // InternalEssentialOCL.g:2375:2: this_MapTypeCS_2= ruleMapTypeCS
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
                    // InternalEssentialOCL.g:2388:2: this_TupleTypeCS_3= ruleTupleTypeCS
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
    // InternalEssentialOCL.g:2407:1: entryRuleTypeLiteralWithMultiplicityCS returns [EObject current=null] : iv_ruleTypeLiteralWithMultiplicityCS= ruleTypeLiteralWithMultiplicityCS EOF ;
    public final EObject entryRuleTypeLiteralWithMultiplicityCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTypeLiteralWithMultiplicityCS = null;


        try {
            // InternalEssentialOCL.g:2408:2: (iv_ruleTypeLiteralWithMultiplicityCS= ruleTypeLiteralWithMultiplicityCS EOF )
            // InternalEssentialOCL.g:2409:2: iv_ruleTypeLiteralWithMultiplicityCS= ruleTypeLiteralWithMultiplicityCS EOF
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
    // InternalEssentialOCL.g:2416:1: ruleTypeLiteralWithMultiplicityCS returns [EObject current=null] : (this_TypeLiteralCS_0= ruleTypeLiteralCS ( (lv_ownedMultiplicity_1_0= ruleMultiplicityCS ) )? ) ;
    public final EObject ruleTypeLiteralWithMultiplicityCS() throws RecognitionException {
        EObject current = null;

        EObject this_TypeLiteralCS_0 = null;

        EObject lv_ownedMultiplicity_1_0 = null;


         enterRule();

        try {
            // InternalEssentialOCL.g:2419:28: ( (this_TypeLiteralCS_0= ruleTypeLiteralCS ( (lv_ownedMultiplicity_1_0= ruleMultiplicityCS ) )? ) )
            // InternalEssentialOCL.g:2420:1: (this_TypeLiteralCS_0= ruleTypeLiteralCS ( (lv_ownedMultiplicity_1_0= ruleMultiplicityCS ) )? )
            {
            // InternalEssentialOCL.g:2420:1: (this_TypeLiteralCS_0= ruleTypeLiteralCS ( (lv_ownedMultiplicity_1_0= ruleMultiplicityCS ) )? )
            // InternalEssentialOCL.g:2421:2: this_TypeLiteralCS_0= ruleTypeLiteralCS ( (lv_ownedMultiplicity_1_0= ruleMultiplicityCS ) )?
            {
            if ( state.backtracking==0 ) {

              	  /* */

            }
            if ( state.backtracking==0 ) {

                      newCompositeNode(grammarAccess.getTypeLiteralWithMultiplicityCSAccess().getTypeLiteralCSParserRuleCall_0());

            }
            pushFollow(FollowSets000.FOLLOW_25);
            this_TypeLiteralCS_0=ruleTypeLiteralCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

                      current = this_TypeLiteralCS_0;
                      afterParserOrEnumRuleCall();

            }
            // InternalEssentialOCL.g:2432:1: ( (lv_ownedMultiplicity_1_0= ruleMultiplicityCS ) )?
            int alt32=2;
            int LA32_0 = input.LA(1);

            if ( (LA32_0==71) ) {
                alt32=1;
            }
            switch (alt32) {
                case 1 :
                    // InternalEssentialOCL.g:2433:1: (lv_ownedMultiplicity_1_0= ruleMultiplicityCS )
                    {
                    // InternalEssentialOCL.g:2433:1: (lv_ownedMultiplicity_1_0= ruleMultiplicityCS )
                    // InternalEssentialOCL.g:2434:3: lv_ownedMultiplicity_1_0= ruleMultiplicityCS
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
    // InternalEssentialOCL.g:2458:1: entryRuleTypeLiteralExpCS returns [EObject current=null] : iv_ruleTypeLiteralExpCS= ruleTypeLiteralExpCS EOF ;
    public final EObject entryRuleTypeLiteralExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTypeLiteralExpCS = null;


        try {
            // InternalEssentialOCL.g:2459:2: (iv_ruleTypeLiteralExpCS= ruleTypeLiteralExpCS EOF )
            // InternalEssentialOCL.g:2460:2: iv_ruleTypeLiteralExpCS= ruleTypeLiteralExpCS EOF
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
    // InternalEssentialOCL.g:2467:1: ruleTypeLiteralExpCS returns [EObject current=null] : ( (lv_ownedType_0_0= ruleTypeLiteralWithMultiplicityCS ) ) ;
    public final EObject ruleTypeLiteralExpCS() throws RecognitionException {
        EObject current = null;

        EObject lv_ownedType_0_0 = null;


         enterRule();

        try {
            // InternalEssentialOCL.g:2470:28: ( ( (lv_ownedType_0_0= ruleTypeLiteralWithMultiplicityCS ) ) )
            // InternalEssentialOCL.g:2471:1: ( (lv_ownedType_0_0= ruleTypeLiteralWithMultiplicityCS ) )
            {
            // InternalEssentialOCL.g:2471:1: ( (lv_ownedType_0_0= ruleTypeLiteralWithMultiplicityCS ) )
            // InternalEssentialOCL.g:2472:1: (lv_ownedType_0_0= ruleTypeLiteralWithMultiplicityCS )
            {
            // InternalEssentialOCL.g:2472:1: (lv_ownedType_0_0= ruleTypeLiteralWithMultiplicityCS )
            // InternalEssentialOCL.g:2473:3: lv_ownedType_0_0= ruleTypeLiteralWithMultiplicityCS
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
    // InternalEssentialOCL.g:2497:1: entryRuleTypeNameExpCS returns [EObject current=null] : iv_ruleTypeNameExpCS= ruleTypeNameExpCS EOF ;
    public final EObject entryRuleTypeNameExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTypeNameExpCS = null;


        try {
            // InternalEssentialOCL.g:2498:2: (iv_ruleTypeNameExpCS= ruleTypeNameExpCS EOF )
            // InternalEssentialOCL.g:2499:2: iv_ruleTypeNameExpCS= ruleTypeNameExpCS EOF
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
    // InternalEssentialOCL.g:2506:1: ruleTypeNameExpCS returns [EObject current=null] : ( ( (lv_ownedPathName_0_0= rulePathNameCS ) ) ( ( (lv_ownedCurlyBracketedClause_1_0= ruleCurlyBracketedClauseCS ) ) (otherlv_2= '{' ( (lv_ownedPatternGuard_3_0= ruleExpCS ) ) otherlv_4= '}' )? )? ) ;
    public final EObject ruleTypeNameExpCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_ownedPathName_0_0 = null;

        EObject lv_ownedCurlyBracketedClause_1_0 = null;

        EObject lv_ownedPatternGuard_3_0 = null;


         enterRule();

        try {
            // InternalEssentialOCL.g:2509:28: ( ( ( (lv_ownedPathName_0_0= rulePathNameCS ) ) ( ( (lv_ownedCurlyBracketedClause_1_0= ruleCurlyBracketedClauseCS ) ) (otherlv_2= '{' ( (lv_ownedPatternGuard_3_0= ruleExpCS ) ) otherlv_4= '}' )? )? ) )
            // InternalEssentialOCL.g:2510:1: ( ( (lv_ownedPathName_0_0= rulePathNameCS ) ) ( ( (lv_ownedCurlyBracketedClause_1_0= ruleCurlyBracketedClauseCS ) ) (otherlv_2= '{' ( (lv_ownedPatternGuard_3_0= ruleExpCS ) ) otherlv_4= '}' )? )? )
            {
            // InternalEssentialOCL.g:2510:1: ( ( (lv_ownedPathName_0_0= rulePathNameCS ) ) ( ( (lv_ownedCurlyBracketedClause_1_0= ruleCurlyBracketedClauseCS ) ) (otherlv_2= '{' ( (lv_ownedPatternGuard_3_0= ruleExpCS ) ) otherlv_4= '}' )? )? )
            // InternalEssentialOCL.g:2510:2: ( (lv_ownedPathName_0_0= rulePathNameCS ) ) ( ( (lv_ownedCurlyBracketedClause_1_0= ruleCurlyBracketedClauseCS ) ) (otherlv_2= '{' ( (lv_ownedPatternGuard_3_0= ruleExpCS ) ) otherlv_4= '}' )? )?
            {
            // InternalEssentialOCL.g:2510:2: ( (lv_ownedPathName_0_0= rulePathNameCS ) )
            // InternalEssentialOCL.g:2511:1: (lv_ownedPathName_0_0= rulePathNameCS )
            {
            // InternalEssentialOCL.g:2511:1: (lv_ownedPathName_0_0= rulePathNameCS )
            // InternalEssentialOCL.g:2512:3: lv_ownedPathName_0_0= rulePathNameCS
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getTypeNameExpCSAccess().getOwnedPathNamePathNameCSParserRuleCall_0_0());

            }
            pushFollow(FollowSets000.FOLLOW_26);
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

            // InternalEssentialOCL.g:2528:2: ( ( (lv_ownedCurlyBracketedClause_1_0= ruleCurlyBracketedClauseCS ) ) (otherlv_2= '{' ( (lv_ownedPatternGuard_3_0= ruleExpCS ) ) otherlv_4= '}' )? )?
            int alt34=2;
            int LA34_0 = input.LA(1);

            if ( (LA34_0==59) ) {
                alt34=1;
            }
            switch (alt34) {
                case 1 :
                    // InternalEssentialOCL.g:2528:3: ( (lv_ownedCurlyBracketedClause_1_0= ruleCurlyBracketedClauseCS ) ) (otherlv_2= '{' ( (lv_ownedPatternGuard_3_0= ruleExpCS ) ) otherlv_4= '}' )?
                    {
                    // InternalEssentialOCL.g:2528:3: ( (lv_ownedCurlyBracketedClause_1_0= ruleCurlyBracketedClauseCS ) )
                    // InternalEssentialOCL.g:2529:1: (lv_ownedCurlyBracketedClause_1_0= ruleCurlyBracketedClauseCS )
                    {
                    // InternalEssentialOCL.g:2529:1: (lv_ownedCurlyBracketedClause_1_0= ruleCurlyBracketedClauseCS )
                    // InternalEssentialOCL.g:2530:3: lv_ownedCurlyBracketedClause_1_0= ruleCurlyBracketedClauseCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getTypeNameExpCSAccess().getOwnedCurlyBracketedClauseCurlyBracketedClauseCSParserRuleCall_1_0_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_26);
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

                    // InternalEssentialOCL.g:2546:2: (otherlv_2= '{' ( (lv_ownedPatternGuard_3_0= ruleExpCS ) ) otherlv_4= '}' )?
                    int alt33=2;
                    int LA33_0 = input.LA(1);

                    if ( (LA33_0==59) ) {
                        alt33=1;
                    }
                    switch (alt33) {
                        case 1 :
                            // InternalEssentialOCL.g:2546:4: otherlv_2= '{' ( (lv_ownedPatternGuard_3_0= ruleExpCS ) ) otherlv_4= '}'
                            {
                            otherlv_2=(Token)match(input,59,FollowSets000.FOLLOW_17); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                  	newLeafNode(otherlv_2, grammarAccess.getTypeNameExpCSAccess().getLeftCurlyBracketKeyword_1_1_0());

                            }
                            // InternalEssentialOCL.g:2550:1: ( (lv_ownedPatternGuard_3_0= ruleExpCS ) )
                            // InternalEssentialOCL.g:2551:1: (lv_ownedPatternGuard_3_0= ruleExpCS )
                            {
                            // InternalEssentialOCL.g:2551:1: (lv_ownedPatternGuard_3_0= ruleExpCS )
                            // InternalEssentialOCL.g:2552:3: lv_ownedPatternGuard_3_0= ruleExpCS
                            {
                            if ( state.backtracking==0 ) {

                              	        newCompositeNode(grammarAccess.getTypeNameExpCSAccess().getOwnedPatternGuardExpCSParserRuleCall_1_1_1_0());

                            }
                            pushFollow(FollowSets000.FOLLOW_19);
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

                            otherlv_4=(Token)match(input,60,FollowSets000.FOLLOW_2); if (state.failed) return current;
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
    // InternalEssentialOCL.g:2580:1: entryRuleTypeExpWithoutMultiplicityCS returns [EObject current=null] : iv_ruleTypeExpWithoutMultiplicityCS= ruleTypeExpWithoutMultiplicityCS EOF ;
    public final EObject entryRuleTypeExpWithoutMultiplicityCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTypeExpWithoutMultiplicityCS = null;


        try {
            // InternalEssentialOCL.g:2581:2: (iv_ruleTypeExpWithoutMultiplicityCS= ruleTypeExpWithoutMultiplicityCS EOF )
            // InternalEssentialOCL.g:2582:2: iv_ruleTypeExpWithoutMultiplicityCS= ruleTypeExpWithoutMultiplicityCS EOF
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
    // InternalEssentialOCL.g:2589:1: ruleTypeExpWithoutMultiplicityCS returns [EObject current=null] : (this_TypeNameExpCS_0= ruleTypeNameExpCS | this_TypeLiteralCS_1= ruleTypeLiteralCS | this_CollectionPatternCS_2= ruleCollectionPatternCS ) ;
    public final EObject ruleTypeExpWithoutMultiplicityCS() throws RecognitionException {
        EObject current = null;

        EObject this_TypeNameExpCS_0 = null;

        EObject this_TypeLiteralCS_1 = null;

        EObject this_CollectionPatternCS_2 = null;


         enterRule();

        try {
            // InternalEssentialOCL.g:2592:28: ( (this_TypeNameExpCS_0= ruleTypeNameExpCS | this_TypeLiteralCS_1= ruleTypeLiteralCS | this_CollectionPatternCS_2= ruleCollectionPatternCS ) )
            // InternalEssentialOCL.g:2593:1: (this_TypeNameExpCS_0= ruleTypeNameExpCS | this_TypeLiteralCS_1= ruleTypeLiteralCS | this_CollectionPatternCS_2= ruleCollectionPatternCS )
            {
            // InternalEssentialOCL.g:2593:1: (this_TypeNameExpCS_0= ruleTypeNameExpCS | this_TypeLiteralCS_1= ruleTypeLiteralCS | this_CollectionPatternCS_2= ruleCollectionPatternCS )
            int alt35=3;
            alt35 = dfa35.predict(input);
            switch (alt35) {
                case 1 :
                    // InternalEssentialOCL.g:2594:2: this_TypeNameExpCS_0= ruleTypeNameExpCS
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
                    // InternalEssentialOCL.g:2607:2: this_TypeLiteralCS_1= ruleTypeLiteralCS
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
                    // InternalEssentialOCL.g:2620:2: this_CollectionPatternCS_2= ruleCollectionPatternCS
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
    // InternalEssentialOCL.g:2639:1: entryRuleTypeExpCS returns [EObject current=null] : iv_ruleTypeExpCS= ruleTypeExpCS EOF ;
    public final EObject entryRuleTypeExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTypeExpCS = null;


        try {
            // InternalEssentialOCL.g:2640:2: (iv_ruleTypeExpCS= ruleTypeExpCS EOF )
            // InternalEssentialOCL.g:2641:2: iv_ruleTypeExpCS= ruleTypeExpCS EOF
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
    // InternalEssentialOCL.g:2648:1: ruleTypeExpCS returns [EObject current=null] : (this_TypeExpWithoutMultiplicityCS_0= ruleTypeExpWithoutMultiplicityCS ( (lv_ownedMultiplicity_1_0= ruleMultiplicityCS ) )? ) ;
    public final EObject ruleTypeExpCS() throws RecognitionException {
        EObject current = null;

        EObject this_TypeExpWithoutMultiplicityCS_0 = null;

        EObject lv_ownedMultiplicity_1_0 = null;


         enterRule();

        try {
            // InternalEssentialOCL.g:2651:28: ( (this_TypeExpWithoutMultiplicityCS_0= ruleTypeExpWithoutMultiplicityCS ( (lv_ownedMultiplicity_1_0= ruleMultiplicityCS ) )? ) )
            // InternalEssentialOCL.g:2652:1: (this_TypeExpWithoutMultiplicityCS_0= ruleTypeExpWithoutMultiplicityCS ( (lv_ownedMultiplicity_1_0= ruleMultiplicityCS ) )? )
            {
            // InternalEssentialOCL.g:2652:1: (this_TypeExpWithoutMultiplicityCS_0= ruleTypeExpWithoutMultiplicityCS ( (lv_ownedMultiplicity_1_0= ruleMultiplicityCS ) )? )
            // InternalEssentialOCL.g:2653:2: this_TypeExpWithoutMultiplicityCS_0= ruleTypeExpWithoutMultiplicityCS ( (lv_ownedMultiplicity_1_0= ruleMultiplicityCS ) )?
            {
            if ( state.backtracking==0 ) {

              	  /* */

            }
            if ( state.backtracking==0 ) {

                      newCompositeNode(grammarAccess.getTypeExpCSAccess().getTypeExpWithoutMultiplicityCSParserRuleCall_0());

            }
            pushFollow(FollowSets000.FOLLOW_25);
            this_TypeExpWithoutMultiplicityCS_0=ruleTypeExpWithoutMultiplicityCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

                      current = this_TypeExpWithoutMultiplicityCS_0;
                      afterParserOrEnumRuleCall();

            }
            // InternalEssentialOCL.g:2664:1: ( (lv_ownedMultiplicity_1_0= ruleMultiplicityCS ) )?
            int alt36=2;
            int LA36_0 = input.LA(1);

            if ( (LA36_0==71) ) {
                alt36=1;
            }
            switch (alt36) {
                case 1 :
                    // InternalEssentialOCL.g:2665:1: (lv_ownedMultiplicity_1_0= ruleMultiplicityCS )
                    {
                    // InternalEssentialOCL.g:2665:1: (lv_ownedMultiplicity_1_0= ruleMultiplicityCS )
                    // InternalEssentialOCL.g:2666:3: lv_ownedMultiplicity_1_0= ruleMultiplicityCS
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
    // InternalEssentialOCL.g:2690:1: entryRuleExpCS returns [EObject current=null] : iv_ruleExpCS= ruleExpCS EOF ;
    public final EObject entryRuleExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpCS = null;


        try {
            // InternalEssentialOCL.g:2691:2: (iv_ruleExpCS= ruleExpCS EOF )
            // InternalEssentialOCL.g:2692:2: iv_ruleExpCS= ruleExpCS EOF
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
    // InternalEssentialOCL.g:2699:1: ruleExpCS returns [EObject current=null] : ( (this_PrefixedPrimaryExpCS_0= rulePrefixedPrimaryExpCS ( () ( (lv_name_2_0= ruleBinaryOperatorName ) ) ( (lv_ownedRight_3_0= ruleExpCS ) ) )? ) | this_PrefixedLetExpCS_4= rulePrefixedLetExpCS ) ;
    public final EObject ruleExpCS() throws RecognitionException {
        EObject current = null;

        EObject this_PrefixedPrimaryExpCS_0 = null;

        AntlrDatatypeRuleToken lv_name_2_0 = null;

        EObject lv_ownedRight_3_0 = null;

        EObject this_PrefixedLetExpCS_4 = null;


         enterRule();

        try {
            // InternalEssentialOCL.g:2702:28: ( ( (this_PrefixedPrimaryExpCS_0= rulePrefixedPrimaryExpCS ( () ( (lv_name_2_0= ruleBinaryOperatorName ) ) ( (lv_ownedRight_3_0= ruleExpCS ) ) )? ) | this_PrefixedLetExpCS_4= rulePrefixedLetExpCS ) )
            // InternalEssentialOCL.g:2703:1: ( (this_PrefixedPrimaryExpCS_0= rulePrefixedPrimaryExpCS ( () ( (lv_name_2_0= ruleBinaryOperatorName ) ) ( (lv_ownedRight_3_0= ruleExpCS ) ) )? ) | this_PrefixedLetExpCS_4= rulePrefixedLetExpCS )
            {
            // InternalEssentialOCL.g:2703:1: ( (this_PrefixedPrimaryExpCS_0= rulePrefixedPrimaryExpCS ( () ( (lv_name_2_0= ruleBinaryOperatorName ) ) ( (lv_ownedRight_3_0= ruleExpCS ) ) )? ) | this_PrefixedLetExpCS_4= rulePrefixedLetExpCS )
            int alt38=2;
            alt38 = dfa38.predict(input);
            switch (alt38) {
                case 1 :
                    // InternalEssentialOCL.g:2703:2: (this_PrefixedPrimaryExpCS_0= rulePrefixedPrimaryExpCS ( () ( (lv_name_2_0= ruleBinaryOperatorName ) ) ( (lv_ownedRight_3_0= ruleExpCS ) ) )? )
                    {
                    // InternalEssentialOCL.g:2703:2: (this_PrefixedPrimaryExpCS_0= rulePrefixedPrimaryExpCS ( () ( (lv_name_2_0= ruleBinaryOperatorName ) ) ( (lv_ownedRight_3_0= ruleExpCS ) ) )? )
                    // InternalEssentialOCL.g:2704:2: this_PrefixedPrimaryExpCS_0= rulePrefixedPrimaryExpCS ( () ( (lv_name_2_0= ruleBinaryOperatorName ) ) ( (lv_ownedRight_3_0= ruleExpCS ) ) )?
                    {
                    if ( state.backtracking==0 ) {

                      	  /* */

                    }
                    if ( state.backtracking==0 ) {

                              newCompositeNode(grammarAccess.getExpCSAccess().getPrefixedPrimaryExpCSParserRuleCall_0_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_27);
                    this_PrefixedPrimaryExpCS_0=rulePrefixedPrimaryExpCS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = this_PrefixedPrimaryExpCS_0;
                              afterParserOrEnumRuleCall();

                    }
                    // InternalEssentialOCL.g:2715:1: ( () ( (lv_name_2_0= ruleBinaryOperatorName ) ) ( (lv_ownedRight_3_0= ruleExpCS ) ) )?
                    int alt37=2;
                    int LA37_0 = input.LA(1);

                    if ( (LA37_0==16||(LA37_0>=19 && LA37_0<=39)) ) {
                        alt37=1;
                    }
                    switch (alt37) {
                        case 1 :
                            // InternalEssentialOCL.g:2715:2: () ( (lv_name_2_0= ruleBinaryOperatorName ) ) ( (lv_ownedRight_3_0= ruleExpCS ) )
                            {
                            // InternalEssentialOCL.g:2715:2: ()
                            // InternalEssentialOCL.g:2716:2:
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

                            // InternalEssentialOCL.g:2724:2: ( (lv_name_2_0= ruleBinaryOperatorName ) )
                            // InternalEssentialOCL.g:2725:1: (lv_name_2_0= ruleBinaryOperatorName )
                            {
                            // InternalEssentialOCL.g:2725:1: (lv_name_2_0= ruleBinaryOperatorName )
                            // InternalEssentialOCL.g:2726:3: lv_name_2_0= ruleBinaryOperatorName
                            {
                            if ( state.backtracking==0 ) {

                              	        newCompositeNode(grammarAccess.getExpCSAccess().getNameBinaryOperatorNameParserRuleCall_0_1_1_0());

                            }
                            pushFollow(FollowSets000.FOLLOW_17);
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

                            // InternalEssentialOCL.g:2742:2: ( (lv_ownedRight_3_0= ruleExpCS ) )
                            // InternalEssentialOCL.g:2743:1: (lv_ownedRight_3_0= ruleExpCS )
                            {
                            // InternalEssentialOCL.g:2743:1: (lv_ownedRight_3_0= ruleExpCS )
                            // InternalEssentialOCL.g:2744:3: lv_ownedRight_3_0= ruleExpCS
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
                    // InternalEssentialOCL.g:2762:2: this_PrefixedLetExpCS_4= rulePrefixedLetExpCS
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
    // InternalEssentialOCL.g:2781:1: entryRulePrefixedLetExpCS returns [EObject current=null] : iv_rulePrefixedLetExpCS= rulePrefixedLetExpCS EOF ;
    public final EObject entryRulePrefixedLetExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePrefixedLetExpCS = null;


        try {
            // InternalEssentialOCL.g:2782:2: (iv_rulePrefixedLetExpCS= rulePrefixedLetExpCS EOF )
            // InternalEssentialOCL.g:2783:2: iv_rulePrefixedLetExpCS= rulePrefixedLetExpCS EOF
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
    // InternalEssentialOCL.g:2790:1: rulePrefixedLetExpCS returns [EObject current=null] : ( ( () ( (lv_name_1_0= ruleUnaryOperatorName ) ) ( (lv_ownedRight_2_0= rulePrefixedLetExpCS ) ) ) | this_LetExpCS_3= ruleLetExpCS ) ;
    public final EObject rulePrefixedLetExpCS() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_name_1_0 = null;

        EObject lv_ownedRight_2_0 = null;

        EObject this_LetExpCS_3 = null;


         enterRule();

        try {
            // InternalEssentialOCL.g:2793:28: ( ( ( () ( (lv_name_1_0= ruleUnaryOperatorName ) ) ( (lv_ownedRight_2_0= rulePrefixedLetExpCS ) ) ) | this_LetExpCS_3= ruleLetExpCS ) )
            // InternalEssentialOCL.g:2794:1: ( ( () ( (lv_name_1_0= ruleUnaryOperatorName ) ) ( (lv_ownedRight_2_0= rulePrefixedLetExpCS ) ) ) | this_LetExpCS_3= ruleLetExpCS )
            {
            // InternalEssentialOCL.g:2794:1: ( ( () ( (lv_name_1_0= ruleUnaryOperatorName ) ) ( (lv_ownedRight_2_0= rulePrefixedLetExpCS ) ) ) | this_LetExpCS_3= ruleLetExpCS )
            int alt39=2;
            int LA39_0 = input.LA(1);

            if ( ((LA39_0>=16 && LA39_0<=18)) ) {
                alt39=1;
            }
            else if ( (LA39_0==81) ) {
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
                    // InternalEssentialOCL.g:2794:2: ( () ( (lv_name_1_0= ruleUnaryOperatorName ) ) ( (lv_ownedRight_2_0= rulePrefixedLetExpCS ) ) )
                    {
                    // InternalEssentialOCL.g:2794:2: ( () ( (lv_name_1_0= ruleUnaryOperatorName ) ) ( (lv_ownedRight_2_0= rulePrefixedLetExpCS ) ) )
                    // InternalEssentialOCL.g:2794:3: () ( (lv_name_1_0= ruleUnaryOperatorName ) ) ( (lv_ownedRight_2_0= rulePrefixedLetExpCS ) )
                    {
                    // InternalEssentialOCL.g:2794:3: ()
                    // InternalEssentialOCL.g:2795:2:
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

                    // InternalEssentialOCL.g:2803:2: ( (lv_name_1_0= ruleUnaryOperatorName ) )
                    // InternalEssentialOCL.g:2804:1: (lv_name_1_0= ruleUnaryOperatorName )
                    {
                    // InternalEssentialOCL.g:2804:1: (lv_name_1_0= ruleUnaryOperatorName )
                    // InternalEssentialOCL.g:2805:3: lv_name_1_0= ruleUnaryOperatorName
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getPrefixedLetExpCSAccess().getNameUnaryOperatorNameParserRuleCall_0_1_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_17);
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

                    // InternalEssentialOCL.g:2821:2: ( (lv_ownedRight_2_0= rulePrefixedLetExpCS ) )
                    // InternalEssentialOCL.g:2822:1: (lv_ownedRight_2_0= rulePrefixedLetExpCS )
                    {
                    // InternalEssentialOCL.g:2822:1: (lv_ownedRight_2_0= rulePrefixedLetExpCS )
                    // InternalEssentialOCL.g:2823:3: lv_ownedRight_2_0= rulePrefixedLetExpCS
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
                    // InternalEssentialOCL.g:2841:2: this_LetExpCS_3= ruleLetExpCS
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
    // InternalEssentialOCL.g:2860:1: entryRulePrefixedPrimaryExpCS returns [EObject current=null] : iv_rulePrefixedPrimaryExpCS= rulePrefixedPrimaryExpCS EOF ;
    public final EObject entryRulePrefixedPrimaryExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePrefixedPrimaryExpCS = null;


        try {
            // InternalEssentialOCL.g:2861:2: (iv_rulePrefixedPrimaryExpCS= rulePrefixedPrimaryExpCS EOF )
            // InternalEssentialOCL.g:2862:2: iv_rulePrefixedPrimaryExpCS= rulePrefixedPrimaryExpCS EOF
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
    // InternalEssentialOCL.g:2869:1: rulePrefixedPrimaryExpCS returns [EObject current=null] : ( ( () ( (lv_name_1_0= ruleUnaryOperatorName ) ) ( (lv_ownedRight_2_0= rulePrefixedPrimaryExpCS ) ) ) | this_PrimaryExpCS_3= rulePrimaryExpCS ) ;
    public final EObject rulePrefixedPrimaryExpCS() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_name_1_0 = null;

        EObject lv_ownedRight_2_0 = null;

        EObject this_PrimaryExpCS_3 = null;


         enterRule();

        try {
            // InternalEssentialOCL.g:2872:28: ( ( ( () ( (lv_name_1_0= ruleUnaryOperatorName ) ) ( (lv_ownedRight_2_0= rulePrefixedPrimaryExpCS ) ) ) | this_PrimaryExpCS_3= rulePrimaryExpCS ) )
            // InternalEssentialOCL.g:2873:1: ( ( () ( (lv_name_1_0= ruleUnaryOperatorName ) ) ( (lv_ownedRight_2_0= rulePrefixedPrimaryExpCS ) ) ) | this_PrimaryExpCS_3= rulePrimaryExpCS )
            {
            // InternalEssentialOCL.g:2873:1: ( ( () ( (lv_name_1_0= ruleUnaryOperatorName ) ) ( (lv_ownedRight_2_0= rulePrefixedPrimaryExpCS ) ) ) | this_PrimaryExpCS_3= rulePrimaryExpCS )
            int alt40=2;
            int LA40_0 = input.LA(1);

            if ( ((LA40_0>=16 && LA40_0<=18)) ) {
                alt40=1;
            }
            else if ( ((LA40_0>=RULE_SIMPLE_ID && LA40_0<=RULE_SINGLE_QUOTED_STRING)||LA40_0==19||(LA40_0>=40 && LA40_0<=55)||LA40_0==63||(LA40_0>=65 && LA40_0<=68)||LA40_0==76||LA40_0==82) ) {
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
                    // InternalEssentialOCL.g:2873:2: ( () ( (lv_name_1_0= ruleUnaryOperatorName ) ) ( (lv_ownedRight_2_0= rulePrefixedPrimaryExpCS ) ) )
                    {
                    // InternalEssentialOCL.g:2873:2: ( () ( (lv_name_1_0= ruleUnaryOperatorName ) ) ( (lv_ownedRight_2_0= rulePrefixedPrimaryExpCS ) ) )
                    // InternalEssentialOCL.g:2873:3: () ( (lv_name_1_0= ruleUnaryOperatorName ) ) ( (lv_ownedRight_2_0= rulePrefixedPrimaryExpCS ) )
                    {
                    // InternalEssentialOCL.g:2873:3: ()
                    // InternalEssentialOCL.g:2874:2:
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

                    // InternalEssentialOCL.g:2882:2: ( (lv_name_1_0= ruleUnaryOperatorName ) )
                    // InternalEssentialOCL.g:2883:1: (lv_name_1_0= ruleUnaryOperatorName )
                    {
                    // InternalEssentialOCL.g:2883:1: (lv_name_1_0= ruleUnaryOperatorName )
                    // InternalEssentialOCL.g:2884:3: lv_name_1_0= ruleUnaryOperatorName
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getPrefixedPrimaryExpCSAccess().getNameUnaryOperatorNameParserRuleCall_0_1_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_28);
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

                    // InternalEssentialOCL.g:2900:2: ( (lv_ownedRight_2_0= rulePrefixedPrimaryExpCS ) )
                    // InternalEssentialOCL.g:2901:1: (lv_ownedRight_2_0= rulePrefixedPrimaryExpCS )
                    {
                    // InternalEssentialOCL.g:2901:1: (lv_ownedRight_2_0= rulePrefixedPrimaryExpCS )
                    // InternalEssentialOCL.g:2902:3: lv_ownedRight_2_0= rulePrefixedPrimaryExpCS
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
                    // InternalEssentialOCL.g:2920:2: this_PrimaryExpCS_3= rulePrimaryExpCS
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
    // InternalEssentialOCL.g:2939:1: entryRulePrimaryExpCS returns [EObject current=null] : iv_rulePrimaryExpCS= rulePrimaryExpCS EOF ;
    public final EObject entryRulePrimaryExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePrimaryExpCS = null;


        try {
            // InternalEssentialOCL.g:2940:2: (iv_rulePrimaryExpCS= rulePrimaryExpCS EOF )
            // InternalEssentialOCL.g:2941:2: iv_rulePrimaryExpCS= rulePrimaryExpCS EOF
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
    // InternalEssentialOCL.g:2948:1: rulePrimaryExpCS returns [EObject current=null] : (this_NestedExpCS_0= ruleNestedExpCS | this_IfExpCS_1= ruleIfExpCS | this_SelfExpCS_2= ruleSelfExpCS | this_PrimitiveLiteralExpCS_3= rulePrimitiveLiteralExpCS | this_TupleLiteralExpCS_4= ruleTupleLiteralExpCS | this_MapLiteralExpCS_5= ruleMapLiteralExpCS | this_CollectionLiteralExpCS_6= ruleCollectionLiteralExpCS | this_LambdaLiteralExpCS_7= ruleLambdaLiteralExpCS | this_TypeLiteralExpCS_8= ruleTypeLiteralExpCS | this_NameExpCS_9= ruleNameExpCS ) ;
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
            // InternalEssentialOCL.g:2951:28: ( (this_NestedExpCS_0= ruleNestedExpCS | this_IfExpCS_1= ruleIfExpCS | this_SelfExpCS_2= ruleSelfExpCS | this_PrimitiveLiteralExpCS_3= rulePrimitiveLiteralExpCS | this_TupleLiteralExpCS_4= ruleTupleLiteralExpCS | this_MapLiteralExpCS_5= ruleMapLiteralExpCS | this_CollectionLiteralExpCS_6= ruleCollectionLiteralExpCS | this_LambdaLiteralExpCS_7= ruleLambdaLiteralExpCS | this_TypeLiteralExpCS_8= ruleTypeLiteralExpCS | this_NameExpCS_9= ruleNameExpCS ) )
            // InternalEssentialOCL.g:2952:1: (this_NestedExpCS_0= ruleNestedExpCS | this_IfExpCS_1= ruleIfExpCS | this_SelfExpCS_2= ruleSelfExpCS | this_PrimitiveLiteralExpCS_3= rulePrimitiveLiteralExpCS | this_TupleLiteralExpCS_4= ruleTupleLiteralExpCS | this_MapLiteralExpCS_5= ruleMapLiteralExpCS | this_CollectionLiteralExpCS_6= ruleCollectionLiteralExpCS | this_LambdaLiteralExpCS_7= ruleLambdaLiteralExpCS | this_TypeLiteralExpCS_8= ruleTypeLiteralExpCS | this_NameExpCS_9= ruleNameExpCS )
            {
            // InternalEssentialOCL.g:2952:1: (this_NestedExpCS_0= ruleNestedExpCS | this_IfExpCS_1= ruleIfExpCS | this_SelfExpCS_2= ruleSelfExpCS | this_PrimitiveLiteralExpCS_3= rulePrimitiveLiteralExpCS | this_TupleLiteralExpCS_4= ruleTupleLiteralExpCS | this_MapLiteralExpCS_5= ruleMapLiteralExpCS | this_CollectionLiteralExpCS_6= ruleCollectionLiteralExpCS | this_LambdaLiteralExpCS_7= ruleLambdaLiteralExpCS | this_TypeLiteralExpCS_8= ruleTypeLiteralExpCS | this_NameExpCS_9= ruleNameExpCS )
            int alt41=10;
            alt41 = dfa41.predict(input);
            switch (alt41) {
                case 1 :
                    // InternalEssentialOCL.g:2953:2: this_NestedExpCS_0= ruleNestedExpCS
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
                    // InternalEssentialOCL.g:2966:2: this_IfExpCS_1= ruleIfExpCS
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
                    // InternalEssentialOCL.g:2979:2: this_SelfExpCS_2= ruleSelfExpCS
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
                    // InternalEssentialOCL.g:2992:2: this_PrimitiveLiteralExpCS_3= rulePrimitiveLiteralExpCS
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
                    // InternalEssentialOCL.g:3005:2: this_TupleLiteralExpCS_4= ruleTupleLiteralExpCS
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
                    // InternalEssentialOCL.g:3018:2: this_MapLiteralExpCS_5= ruleMapLiteralExpCS
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
                    // InternalEssentialOCL.g:3031:2: this_CollectionLiteralExpCS_6= ruleCollectionLiteralExpCS
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
                    // InternalEssentialOCL.g:3044:2: this_LambdaLiteralExpCS_7= ruleLambdaLiteralExpCS
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
                    // InternalEssentialOCL.g:3057:2: this_TypeLiteralExpCS_8= ruleTypeLiteralExpCS
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
                    // InternalEssentialOCL.g:3070:2: this_NameExpCS_9= ruleNameExpCS
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
    // InternalEssentialOCL.g:3089:1: entryRuleNameExpCS returns [EObject current=null] : iv_ruleNameExpCS= ruleNameExpCS EOF ;
    public final EObject entryRuleNameExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNameExpCS = null;


        try {
            // InternalEssentialOCL.g:3090:2: (iv_ruleNameExpCS= ruleNameExpCS EOF )
            // InternalEssentialOCL.g:3091:2: iv_ruleNameExpCS= ruleNameExpCS EOF
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
    // InternalEssentialOCL.g:3098:1: ruleNameExpCS returns [EObject current=null] : ( ( (lv_ownedPathName_0_0= rulePathNameCS ) ) ( (lv_ownedSquareBracketedClauses_1_0= ruleSquareBracketedClauseCS ) )* ( (lv_ownedRoundBracketedClause_2_0= ruleRoundBracketedClauseCS ) )? ( (lv_ownedCurlyBracketedClause_3_0= ruleCurlyBracketedClauseCS ) )? ( ( (lv_isPre_4_0= '@' ) ) otherlv_5= 'pre' )? ) ;
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
            // InternalEssentialOCL.g:3101:28: ( ( ( (lv_ownedPathName_0_0= rulePathNameCS ) ) ( (lv_ownedSquareBracketedClauses_1_0= ruleSquareBracketedClauseCS ) )* ( (lv_ownedRoundBracketedClause_2_0= ruleRoundBracketedClauseCS ) )? ( (lv_ownedCurlyBracketedClause_3_0= ruleCurlyBracketedClauseCS ) )? ( ( (lv_isPre_4_0= '@' ) ) otherlv_5= 'pre' )? ) )
            // InternalEssentialOCL.g:3102:1: ( ( (lv_ownedPathName_0_0= rulePathNameCS ) ) ( (lv_ownedSquareBracketedClauses_1_0= ruleSquareBracketedClauseCS ) )* ( (lv_ownedRoundBracketedClause_2_0= ruleRoundBracketedClauseCS ) )? ( (lv_ownedCurlyBracketedClause_3_0= ruleCurlyBracketedClauseCS ) )? ( ( (lv_isPre_4_0= '@' ) ) otherlv_5= 'pre' )? )
            {
            // InternalEssentialOCL.g:3102:1: ( ( (lv_ownedPathName_0_0= rulePathNameCS ) ) ( (lv_ownedSquareBracketedClauses_1_0= ruleSquareBracketedClauseCS ) )* ( (lv_ownedRoundBracketedClause_2_0= ruleRoundBracketedClauseCS ) )? ( (lv_ownedCurlyBracketedClause_3_0= ruleCurlyBracketedClauseCS ) )? ( ( (lv_isPre_4_0= '@' ) ) otherlv_5= 'pre' )? )
            // InternalEssentialOCL.g:3102:2: ( (lv_ownedPathName_0_0= rulePathNameCS ) ) ( (lv_ownedSquareBracketedClauses_1_0= ruleSquareBracketedClauseCS ) )* ( (lv_ownedRoundBracketedClause_2_0= ruleRoundBracketedClauseCS ) )? ( (lv_ownedCurlyBracketedClause_3_0= ruleCurlyBracketedClauseCS ) )? ( ( (lv_isPre_4_0= '@' ) ) otherlv_5= 'pre' )?
            {
            // InternalEssentialOCL.g:3102:2: ( (lv_ownedPathName_0_0= rulePathNameCS ) )
            // InternalEssentialOCL.g:3103:1: (lv_ownedPathName_0_0= rulePathNameCS )
            {
            // InternalEssentialOCL.g:3103:1: (lv_ownedPathName_0_0= rulePathNameCS )
            // InternalEssentialOCL.g:3104:3: lv_ownedPathName_0_0= rulePathNameCS
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getNameExpCSAccess().getOwnedPathNamePathNameCSParserRuleCall_0_0());

            }
            pushFollow(FollowSets000.FOLLOW_29);
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

            // InternalEssentialOCL.g:3120:2: ( (lv_ownedSquareBracketedClauses_1_0= ruleSquareBracketedClauseCS ) )*
            loop42:
            do {
                int alt42=2;
                int LA42_0 = input.LA(1);

                if ( (LA42_0==71) ) {
                    alt42=1;
                }


                switch (alt42) {
            	case 1 :
            	    // InternalEssentialOCL.g:3121:1: (lv_ownedSquareBracketedClauses_1_0= ruleSquareBracketedClauseCS )
            	    {
            	    // InternalEssentialOCL.g:3121:1: (lv_ownedSquareBracketedClauses_1_0= ruleSquareBracketedClauseCS )
            	    // InternalEssentialOCL.g:3122:3: lv_ownedSquareBracketedClauses_1_0= ruleSquareBracketedClauseCS
            	    {
            	    if ( state.backtracking==0 ) {

            	      	        newCompositeNode(grammarAccess.getNameExpCSAccess().getOwnedSquareBracketedClausesSquareBracketedClauseCSParserRuleCall_1_0());

            	    }
            	    pushFollow(FollowSets000.FOLLOW_29);
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
            	    break loop42;
                }
            } while (true);

            // InternalEssentialOCL.g:3138:3: ( (lv_ownedRoundBracketedClause_2_0= ruleRoundBracketedClauseCS ) )?
            int alt43=2;
            int LA43_0 = input.LA(1);

            if ( (LA43_0==55) ) {
                alt43=1;
            }
            switch (alt43) {
                case 1 :
                    // InternalEssentialOCL.g:3139:1: (lv_ownedRoundBracketedClause_2_0= ruleRoundBracketedClauseCS )
                    {
                    // InternalEssentialOCL.g:3139:1: (lv_ownedRoundBracketedClause_2_0= ruleRoundBracketedClauseCS )
                    // InternalEssentialOCL.g:3140:3: lv_ownedRoundBracketedClause_2_0= ruleRoundBracketedClauseCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getNameExpCSAccess().getOwnedRoundBracketedClauseRoundBracketedClauseCSParserRuleCall_2_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_30);
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

            // InternalEssentialOCL.g:3156:3: ( (lv_ownedCurlyBracketedClause_3_0= ruleCurlyBracketedClauseCS ) )?
            int alt44=2;
            int LA44_0 = input.LA(1);

            if ( (LA44_0==59) ) {
                alt44=1;
            }
            switch (alt44) {
                case 1 :
                    // InternalEssentialOCL.g:3157:1: (lv_ownedCurlyBracketedClause_3_0= ruleCurlyBracketedClauseCS )
                    {
                    // InternalEssentialOCL.g:3157:1: (lv_ownedCurlyBracketedClause_3_0= ruleCurlyBracketedClauseCS )
                    // InternalEssentialOCL.g:3158:3: lv_ownedCurlyBracketedClause_3_0= ruleCurlyBracketedClauseCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getNameExpCSAccess().getOwnedCurlyBracketedClauseCurlyBracketedClauseCSParserRuleCall_3_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_31);
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

            // InternalEssentialOCL.g:3174:3: ( ( (lv_isPre_4_0= '@' ) ) otherlv_5= 'pre' )?
            int alt45=2;
            int LA45_0 = input.LA(1);

            if ( (LA45_0==69) ) {
                alt45=1;
            }
            switch (alt45) {
                case 1 :
                    // InternalEssentialOCL.g:3174:4: ( (lv_isPre_4_0= '@' ) ) otherlv_5= 'pre'
                    {
                    // InternalEssentialOCL.g:3174:4: ( (lv_isPre_4_0= '@' ) )
                    // InternalEssentialOCL.g:3175:1: (lv_isPre_4_0= '@' )
                    {
                    // InternalEssentialOCL.g:3175:1: (lv_isPre_4_0= '@' )
                    // InternalEssentialOCL.g:3176:3: lv_isPre_4_0= '@'
                    {
                    lv_isPre_4_0=(Token)match(input,69,FollowSets000.FOLLOW_32); if (state.failed) return current;
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

                    otherlv_5=(Token)match(input,70,FollowSets000.FOLLOW_2); if (state.failed) return current;
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
    // InternalEssentialOCL.g:3201:1: entryRuleCurlyBracketedClauseCS returns [EObject current=null] : iv_ruleCurlyBracketedClauseCS= ruleCurlyBracketedClauseCS EOF ;
    public final EObject entryRuleCurlyBracketedClauseCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCurlyBracketedClauseCS = null;


        try {
            // InternalEssentialOCL.g:3202:2: (iv_ruleCurlyBracketedClauseCS= ruleCurlyBracketedClauseCS EOF )
            // InternalEssentialOCL.g:3203:2: iv_ruleCurlyBracketedClauseCS= ruleCurlyBracketedClauseCS EOF
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
    // InternalEssentialOCL.g:3210:1: ruleCurlyBracketedClauseCS returns [EObject current=null] : ( () otherlv_1= '{' ( ( (lv_ownedParts_2_0= ruleShadowPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleShadowPartCS ) ) )* )? otherlv_5= '}' ) ;
    public final EObject ruleCurlyBracketedClauseCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        EObject lv_ownedParts_2_0 = null;

        EObject lv_ownedParts_4_0 = null;


         enterRule();

        try {
            // InternalEssentialOCL.g:3213:28: ( ( () otherlv_1= '{' ( ( (lv_ownedParts_2_0= ruleShadowPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleShadowPartCS ) ) )* )? otherlv_5= '}' ) )
            // InternalEssentialOCL.g:3214:1: ( () otherlv_1= '{' ( ( (lv_ownedParts_2_0= ruleShadowPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleShadowPartCS ) ) )* )? otherlv_5= '}' )
            {
            // InternalEssentialOCL.g:3214:1: ( () otherlv_1= '{' ( ( (lv_ownedParts_2_0= ruleShadowPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleShadowPartCS ) ) )* )? otherlv_5= '}' )
            // InternalEssentialOCL.g:3214:2: () otherlv_1= '{' ( ( (lv_ownedParts_2_0= ruleShadowPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleShadowPartCS ) ) )* )? otherlv_5= '}'
            {
            // InternalEssentialOCL.g:3214:2: ()
            // InternalEssentialOCL.g:3215:2:
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

            otherlv_1=(Token)match(input,59,FollowSets000.FOLLOW_33); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getCurlyBracketedClauseCSAccess().getLeftCurlyBracketKeyword_1());

            }
            // InternalEssentialOCL.g:3227:1: ( ( (lv_ownedParts_2_0= ruleShadowPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleShadowPartCS ) ) )* )?
            int alt47=2;
            int LA47_0 = input.LA(1);

            if ( ((LA47_0>=RULE_SIMPLE_ID && LA47_0<=RULE_ESCAPED_ID)||LA47_0==RULE_SINGLE_QUOTED_STRING) ) {
                alt47=1;
            }
            switch (alt47) {
                case 1 :
                    // InternalEssentialOCL.g:3227:2: ( (lv_ownedParts_2_0= ruleShadowPartCS ) ) (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleShadowPartCS ) ) )*
                    {
                    // InternalEssentialOCL.g:3227:2: ( (lv_ownedParts_2_0= ruleShadowPartCS ) )
                    // InternalEssentialOCL.g:3228:1: (lv_ownedParts_2_0= ruleShadowPartCS )
                    {
                    // InternalEssentialOCL.g:3228:1: (lv_ownedParts_2_0= ruleShadowPartCS )
                    // InternalEssentialOCL.g:3229:3: lv_ownedParts_2_0= ruleShadowPartCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getCurlyBracketedClauseCSAccess().getOwnedPartsShadowPartCSParserRuleCall_2_0_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_14);
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

                    // InternalEssentialOCL.g:3245:2: (otherlv_3= ',' ( (lv_ownedParts_4_0= ruleShadowPartCS ) ) )*
                    loop46:
                    do {
                        int alt46=2;
                        int LA46_0 = input.LA(1);

                        if ( (LA46_0==57) ) {
                            alt46=1;
                        }


                        switch (alt46) {
                    	case 1 :
                    	    // InternalEssentialOCL.g:3245:4: otherlv_3= ',' ( (lv_ownedParts_4_0= ruleShadowPartCS ) )
                    	    {
                    	    otherlv_3=(Token)match(input,57,FollowSets000.FOLLOW_34); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_3, grammarAccess.getCurlyBracketedClauseCSAccess().getCommaKeyword_2_1_0());

                    	    }
                    	    // InternalEssentialOCL.g:3249:1: ( (lv_ownedParts_4_0= ruleShadowPartCS ) )
                    	    // InternalEssentialOCL.g:3250:1: (lv_ownedParts_4_0= ruleShadowPartCS )
                    	    {
                    	    // InternalEssentialOCL.g:3250:1: (lv_ownedParts_4_0= ruleShadowPartCS )
                    	    // InternalEssentialOCL.g:3251:3: lv_ownedParts_4_0= ruleShadowPartCS
                    	    {
                    	    if ( state.backtracking==0 ) {

                    	      	        newCompositeNode(grammarAccess.getCurlyBracketedClauseCSAccess().getOwnedPartsShadowPartCSParserRuleCall_2_1_1_0());

                    	    }
                    	    pushFollow(FollowSets000.FOLLOW_14);
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
                    	    break loop46;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_5=(Token)match(input,60,FollowSets000.FOLLOW_2); if (state.failed) return current;
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
    // InternalEssentialOCL.g:3279:1: entryRuleRoundBracketedClauseCS returns [EObject current=null] : iv_ruleRoundBracketedClauseCS= ruleRoundBracketedClauseCS EOF ;
    public final EObject entryRuleRoundBracketedClauseCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRoundBracketedClauseCS = null;


        try {
            // InternalEssentialOCL.g:3280:2: (iv_ruleRoundBracketedClauseCS= ruleRoundBracketedClauseCS EOF )
            // InternalEssentialOCL.g:3281:2: iv_ruleRoundBracketedClauseCS= ruleRoundBracketedClauseCS EOF
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
    // InternalEssentialOCL.g:3288:1: ruleRoundBracketedClauseCS returns [EObject current=null] : ( () otherlv_1= '(' ( ( (lv_ownedArguments_2_0= ruleNavigatingArgCS ) ) ( ( (lv_ownedArguments_3_1= ruleNavigatingCommaArgCS | lv_ownedArguments_3_2= ruleNavigatingSemiArgCS | lv_ownedArguments_3_3= ruleNavigatingBarArgCS ) ) )* )? otherlv_4= ')' ) ;
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
            // InternalEssentialOCL.g:3291:28: ( ( () otherlv_1= '(' ( ( (lv_ownedArguments_2_0= ruleNavigatingArgCS ) ) ( ( (lv_ownedArguments_3_1= ruleNavigatingCommaArgCS | lv_ownedArguments_3_2= ruleNavigatingSemiArgCS | lv_ownedArguments_3_3= ruleNavigatingBarArgCS ) ) )* )? otherlv_4= ')' ) )
            // InternalEssentialOCL.g:3292:1: ( () otherlv_1= '(' ( ( (lv_ownedArguments_2_0= ruleNavigatingArgCS ) ) ( ( (lv_ownedArguments_3_1= ruleNavigatingCommaArgCS | lv_ownedArguments_3_2= ruleNavigatingSemiArgCS | lv_ownedArguments_3_3= ruleNavigatingBarArgCS ) ) )* )? otherlv_4= ')' )
            {
            // InternalEssentialOCL.g:3292:1: ( () otherlv_1= '(' ( ( (lv_ownedArguments_2_0= ruleNavigatingArgCS ) ) ( ( (lv_ownedArguments_3_1= ruleNavigatingCommaArgCS | lv_ownedArguments_3_2= ruleNavigatingSemiArgCS | lv_ownedArguments_3_3= ruleNavigatingBarArgCS ) ) )* )? otherlv_4= ')' )
            // InternalEssentialOCL.g:3292:2: () otherlv_1= '(' ( ( (lv_ownedArguments_2_0= ruleNavigatingArgCS ) ) ( ( (lv_ownedArguments_3_1= ruleNavigatingCommaArgCS | lv_ownedArguments_3_2= ruleNavigatingSemiArgCS | lv_ownedArguments_3_3= ruleNavigatingBarArgCS ) ) )* )? otherlv_4= ')'
            {
            // InternalEssentialOCL.g:3292:2: ()
            // InternalEssentialOCL.g:3293:2:
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

            otherlv_1=(Token)match(input,55,FollowSets000.FOLLOW_35); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getRoundBracketedClauseCSAccess().getLeftParenthesisKeyword_1());

            }
            // InternalEssentialOCL.g:3305:1: ( ( (lv_ownedArguments_2_0= ruleNavigatingArgCS ) ) ( ( (lv_ownedArguments_3_1= ruleNavigatingCommaArgCS | lv_ownedArguments_3_2= ruleNavigatingSemiArgCS | lv_ownedArguments_3_3= ruleNavigatingBarArgCS ) ) )* )?
            int alt50=2;
            int LA50_0 = input.LA(1);

            if ( ((LA50_0>=RULE_SIMPLE_ID && LA50_0<=RULE_SINGLE_QUOTED_STRING)||(LA50_0>=16 && LA50_0<=19)||(LA50_0>=40 && LA50_0<=55)||LA50_0==58||LA50_0==63||(LA50_0>=65 && LA50_0<=68)||LA50_0==76||(LA50_0>=81 && LA50_0<=82)) ) {
                alt50=1;
            }
            switch (alt50) {
                case 1 :
                    // InternalEssentialOCL.g:3305:2: ( (lv_ownedArguments_2_0= ruleNavigatingArgCS ) ) ( ( (lv_ownedArguments_3_1= ruleNavigatingCommaArgCS | lv_ownedArguments_3_2= ruleNavigatingSemiArgCS | lv_ownedArguments_3_3= ruleNavigatingBarArgCS ) ) )*
                    {
                    // InternalEssentialOCL.g:3305:2: ( (lv_ownedArguments_2_0= ruleNavigatingArgCS ) )
                    // InternalEssentialOCL.g:3306:1: (lv_ownedArguments_2_0= ruleNavigatingArgCS )
                    {
                    // InternalEssentialOCL.g:3306:1: (lv_ownedArguments_2_0= ruleNavigatingArgCS )
                    // InternalEssentialOCL.g:3307:3: lv_ownedArguments_2_0= ruleNavigatingArgCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getRoundBracketedClauseCSAccess().getOwnedArgumentsNavigatingArgCSParserRuleCall_2_0_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_36);
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

                    // InternalEssentialOCL.g:3323:2: ( ( (lv_ownedArguments_3_1= ruleNavigatingCommaArgCS | lv_ownedArguments_3_2= ruleNavigatingSemiArgCS | lv_ownedArguments_3_3= ruleNavigatingBarArgCS ) ) )*
                    loop49:
                    do {
                        int alt49=2;
                        int LA49_0 = input.LA(1);

                        if ( (LA49_0==57||(LA49_0>=74 && LA49_0<=75)) ) {
                            alt49=1;
                        }


                        switch (alt49) {
                    	case 1 :
                    	    // InternalEssentialOCL.g:3324:1: ( (lv_ownedArguments_3_1= ruleNavigatingCommaArgCS | lv_ownedArguments_3_2= ruleNavigatingSemiArgCS | lv_ownedArguments_3_3= ruleNavigatingBarArgCS ) )
                    	    {
                    	    // InternalEssentialOCL.g:3324:1: ( (lv_ownedArguments_3_1= ruleNavigatingCommaArgCS | lv_ownedArguments_3_2= ruleNavigatingSemiArgCS | lv_ownedArguments_3_3= ruleNavigatingBarArgCS ) )
                    	    // InternalEssentialOCL.g:3325:1: (lv_ownedArguments_3_1= ruleNavigatingCommaArgCS | lv_ownedArguments_3_2= ruleNavigatingSemiArgCS | lv_ownedArguments_3_3= ruleNavigatingBarArgCS )
                    	    {
                    	    // InternalEssentialOCL.g:3325:1: (lv_ownedArguments_3_1= ruleNavigatingCommaArgCS | lv_ownedArguments_3_2= ruleNavigatingSemiArgCS | lv_ownedArguments_3_3= ruleNavigatingBarArgCS )
                    	    int alt48=3;
                    	    switch ( input.LA(1) ) {
                    	    case 57:
                    	        {
                    	        alt48=1;
                    	        }
                    	        break;
                    	    case 75:
                    	        {
                    	        alt48=2;
                    	        }
                    	        break;
                    	    case 74:
                    	        {
                    	        alt48=3;
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
                    	            // InternalEssentialOCL.g:3326:3: lv_ownedArguments_3_1= ruleNavigatingCommaArgCS
                    	            {
                    	            if ( state.backtracking==0 ) {

                    	              	        newCompositeNode(grammarAccess.getRoundBracketedClauseCSAccess().getOwnedArgumentsNavigatingCommaArgCSParserRuleCall_2_1_0_0());

                    	            }
                    	            pushFollow(FollowSets000.FOLLOW_36);
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
                    	            // InternalEssentialOCL.g:3341:8: lv_ownedArguments_3_2= ruleNavigatingSemiArgCS
                    	            {
                    	            if ( state.backtracking==0 ) {

                    	              	        newCompositeNode(grammarAccess.getRoundBracketedClauseCSAccess().getOwnedArgumentsNavigatingSemiArgCSParserRuleCall_2_1_0_1());

                    	            }
                    	            pushFollow(FollowSets000.FOLLOW_36);
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
                    	            // InternalEssentialOCL.g:3356:8: lv_ownedArguments_3_3= ruleNavigatingBarArgCS
                    	            {
                    	            if ( state.backtracking==0 ) {

                    	              	        newCompositeNode(grammarAccess.getRoundBracketedClauseCSAccess().getOwnedArgumentsNavigatingBarArgCSParserRuleCall_2_1_0_2());

                    	            }
                    	            pushFollow(FollowSets000.FOLLOW_36);
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
                    	    break loop49;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_4=(Token)match(input,56,FollowSets000.FOLLOW_2); if (state.failed) return current;
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
    // InternalEssentialOCL.g:3386:1: entryRuleSquareBracketedClauseCS returns [EObject current=null] : iv_ruleSquareBracketedClauseCS= ruleSquareBracketedClauseCS EOF ;
    public final EObject entryRuleSquareBracketedClauseCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSquareBracketedClauseCS = null;


        try {
            // InternalEssentialOCL.g:3387:2: (iv_ruleSquareBracketedClauseCS= ruleSquareBracketedClauseCS EOF )
            // InternalEssentialOCL.g:3388:2: iv_ruleSquareBracketedClauseCS= ruleSquareBracketedClauseCS EOF
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
    // InternalEssentialOCL.g:3395:1: ruleSquareBracketedClauseCS returns [EObject current=null] : (otherlv_0= '[' ( (lv_ownedTerms_1_0= ruleExpCS ) ) (otherlv_2= ',' ( (lv_ownedTerms_3_0= ruleExpCS ) ) )* otherlv_4= ']' ) ;
    public final EObject ruleSquareBracketedClauseCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_ownedTerms_1_0 = null;

        EObject lv_ownedTerms_3_0 = null;


         enterRule();

        try {
            // InternalEssentialOCL.g:3398:28: ( (otherlv_0= '[' ( (lv_ownedTerms_1_0= ruleExpCS ) ) (otherlv_2= ',' ( (lv_ownedTerms_3_0= ruleExpCS ) ) )* otherlv_4= ']' ) )
            // InternalEssentialOCL.g:3399:1: (otherlv_0= '[' ( (lv_ownedTerms_1_0= ruleExpCS ) ) (otherlv_2= ',' ( (lv_ownedTerms_3_0= ruleExpCS ) ) )* otherlv_4= ']' )
            {
            // InternalEssentialOCL.g:3399:1: (otherlv_0= '[' ( (lv_ownedTerms_1_0= ruleExpCS ) ) (otherlv_2= ',' ( (lv_ownedTerms_3_0= ruleExpCS ) ) )* otherlv_4= ']' )
            // InternalEssentialOCL.g:3399:3: otherlv_0= '[' ( (lv_ownedTerms_1_0= ruleExpCS ) ) (otherlv_2= ',' ( (lv_ownedTerms_3_0= ruleExpCS ) ) )* otherlv_4= ']'
            {
            otherlv_0=(Token)match(input,71,FollowSets000.FOLLOW_17); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getSquareBracketedClauseCSAccess().getLeftSquareBracketKeyword_0());

            }
            // InternalEssentialOCL.g:3403:1: ( (lv_ownedTerms_1_0= ruleExpCS ) )
            // InternalEssentialOCL.g:3404:1: (lv_ownedTerms_1_0= ruleExpCS )
            {
            // InternalEssentialOCL.g:3404:1: (lv_ownedTerms_1_0= ruleExpCS )
            // InternalEssentialOCL.g:3405:3: lv_ownedTerms_1_0= ruleExpCS
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getSquareBracketedClauseCSAccess().getOwnedTermsExpCSParserRuleCall_1_0());

            }
            pushFollow(FollowSets000.FOLLOW_37);
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

            // InternalEssentialOCL.g:3421:2: (otherlv_2= ',' ( (lv_ownedTerms_3_0= ruleExpCS ) ) )*
            loop51:
            do {
                int alt51=2;
                int LA51_0 = input.LA(1);

                if ( (LA51_0==57) ) {
                    alt51=1;
                }


                switch (alt51) {
            	case 1 :
            	    // InternalEssentialOCL.g:3421:4: otherlv_2= ',' ( (lv_ownedTerms_3_0= ruleExpCS ) )
            	    {
            	    otherlv_2=(Token)match(input,57,FollowSets000.FOLLOW_17); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_2, grammarAccess.getSquareBracketedClauseCSAccess().getCommaKeyword_2_0());

            	    }
            	    // InternalEssentialOCL.g:3425:1: ( (lv_ownedTerms_3_0= ruleExpCS ) )
            	    // InternalEssentialOCL.g:3426:1: (lv_ownedTerms_3_0= ruleExpCS )
            	    {
            	    // InternalEssentialOCL.g:3426:1: (lv_ownedTerms_3_0= ruleExpCS )
            	    // InternalEssentialOCL.g:3427:3: lv_ownedTerms_3_0= ruleExpCS
            	    {
            	    if ( state.backtracking==0 ) {

            	      	        newCompositeNode(grammarAccess.getSquareBracketedClauseCSAccess().getOwnedTermsExpCSParserRuleCall_2_1_0());

            	    }
            	    pushFollow(FollowSets000.FOLLOW_37);
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
            	    break loop51;
                }
            } while (true);

            otherlv_4=(Token)match(input,72,FollowSets000.FOLLOW_2); if (state.failed) return current;
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
    // InternalEssentialOCL.g:3455:1: entryRuleNavigatingArgCS returns [EObject current=null] : iv_ruleNavigatingArgCS= ruleNavigatingArgCS EOF ;
    public final EObject entryRuleNavigatingArgCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNavigatingArgCS = null;


        try {
            // InternalEssentialOCL.g:3456:2: (iv_ruleNavigatingArgCS= ruleNavigatingArgCS EOF )
            // InternalEssentialOCL.g:3457:2: iv_ruleNavigatingArgCS= ruleNavigatingArgCS EOF
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
    // InternalEssentialOCL.g:3464:1: ruleNavigatingArgCS returns [EObject current=null] : ( ( ( (lv_ownedNameExpression_0_0= ruleNavigatingArgExpCS ) ) ( (otherlv_1= '<-' ( (lv_ownedCoIterator_2_0= ruleCoIteratorVariableCS ) ) (otherlv_3= '=' ( (lv_ownedInitExpression_4_0= ruleExpCS ) ) )? ) | (otherlv_5= ':' ( (lv_ownedType_6_0= ruleTypeExpCS ) ) (otherlv_7= '<-' ( (lv_ownedCoIterator_8_0= ruleCoIteratorVariableCS ) ) )? (otherlv_9= '=' ( (lv_ownedInitExpression_10_0= ruleExpCS ) ) )? ) | ( (otherlv_11= ':' ( (lv_ownedType_12_0= ruleTypeExpCS ) ) )? (otherlv_13= '<-' ( (lv_ownedCoIterator_14_0= ruleCoIteratorVariableCS ) ) )? otherlv_15= 'in' ( (lv_ownedInitExpression_16_0= ruleExpCS ) ) ) )? ) | (otherlv_17= ':' ( (lv_ownedType_18_0= ruleTypeExpCS ) ) ) ) ;
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
            // InternalEssentialOCL.g:3467:28: ( ( ( ( (lv_ownedNameExpression_0_0= ruleNavigatingArgExpCS ) ) ( (otherlv_1= '<-' ( (lv_ownedCoIterator_2_0= ruleCoIteratorVariableCS ) ) (otherlv_3= '=' ( (lv_ownedInitExpression_4_0= ruleExpCS ) ) )? ) | (otherlv_5= ':' ( (lv_ownedType_6_0= ruleTypeExpCS ) ) (otherlv_7= '<-' ( (lv_ownedCoIterator_8_0= ruleCoIteratorVariableCS ) ) )? (otherlv_9= '=' ( (lv_ownedInitExpression_10_0= ruleExpCS ) ) )? ) | ( (otherlv_11= ':' ( (lv_ownedType_12_0= ruleTypeExpCS ) ) )? (otherlv_13= '<-' ( (lv_ownedCoIterator_14_0= ruleCoIteratorVariableCS ) ) )? otherlv_15= 'in' ( (lv_ownedInitExpression_16_0= ruleExpCS ) ) ) )? ) | (otherlv_17= ':' ( (lv_ownedType_18_0= ruleTypeExpCS ) ) ) ) )
            // InternalEssentialOCL.g:3468:1: ( ( ( (lv_ownedNameExpression_0_0= ruleNavigatingArgExpCS ) ) ( (otherlv_1= '<-' ( (lv_ownedCoIterator_2_0= ruleCoIteratorVariableCS ) ) (otherlv_3= '=' ( (lv_ownedInitExpression_4_0= ruleExpCS ) ) )? ) | (otherlv_5= ':' ( (lv_ownedType_6_0= ruleTypeExpCS ) ) (otherlv_7= '<-' ( (lv_ownedCoIterator_8_0= ruleCoIteratorVariableCS ) ) )? (otherlv_9= '=' ( (lv_ownedInitExpression_10_0= ruleExpCS ) ) )? ) | ( (otherlv_11= ':' ( (lv_ownedType_12_0= ruleTypeExpCS ) ) )? (otherlv_13= '<-' ( (lv_ownedCoIterator_14_0= ruleCoIteratorVariableCS ) ) )? otherlv_15= 'in' ( (lv_ownedInitExpression_16_0= ruleExpCS ) ) ) )? ) | (otherlv_17= ':' ( (lv_ownedType_18_0= ruleTypeExpCS ) ) ) )
            {
            // InternalEssentialOCL.g:3468:1: ( ( ( (lv_ownedNameExpression_0_0= ruleNavigatingArgExpCS ) ) ( (otherlv_1= '<-' ( (lv_ownedCoIterator_2_0= ruleCoIteratorVariableCS ) ) (otherlv_3= '=' ( (lv_ownedInitExpression_4_0= ruleExpCS ) ) )? ) | (otherlv_5= ':' ( (lv_ownedType_6_0= ruleTypeExpCS ) ) (otherlv_7= '<-' ( (lv_ownedCoIterator_8_0= ruleCoIteratorVariableCS ) ) )? (otherlv_9= '=' ( (lv_ownedInitExpression_10_0= ruleExpCS ) ) )? ) | ( (otherlv_11= ':' ( (lv_ownedType_12_0= ruleTypeExpCS ) ) )? (otherlv_13= '<-' ( (lv_ownedCoIterator_14_0= ruleCoIteratorVariableCS ) ) )? otherlv_15= 'in' ( (lv_ownedInitExpression_16_0= ruleExpCS ) ) ) )? ) | (otherlv_17= ':' ( (lv_ownedType_18_0= ruleTypeExpCS ) ) ) )
            int alt58=2;
            int LA58_0 = input.LA(1);

            if ( ((LA58_0>=RULE_SIMPLE_ID && LA58_0<=RULE_SINGLE_QUOTED_STRING)||(LA58_0>=16 && LA58_0<=19)||(LA58_0>=40 && LA58_0<=55)||LA58_0==63||(LA58_0>=65 && LA58_0<=68)||LA58_0==76||(LA58_0>=81 && LA58_0<=82)) ) {
                alt58=1;
            }
            else if ( (LA58_0==58) ) {
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
                    // InternalEssentialOCL.g:3468:2: ( ( (lv_ownedNameExpression_0_0= ruleNavigatingArgExpCS ) ) ( (otherlv_1= '<-' ( (lv_ownedCoIterator_2_0= ruleCoIteratorVariableCS ) ) (otherlv_3= '=' ( (lv_ownedInitExpression_4_0= ruleExpCS ) ) )? ) | (otherlv_5= ':' ( (lv_ownedType_6_0= ruleTypeExpCS ) ) (otherlv_7= '<-' ( (lv_ownedCoIterator_8_0= ruleCoIteratorVariableCS ) ) )? (otherlv_9= '=' ( (lv_ownedInitExpression_10_0= ruleExpCS ) ) )? ) | ( (otherlv_11= ':' ( (lv_ownedType_12_0= ruleTypeExpCS ) ) )? (otherlv_13= '<-' ( (lv_ownedCoIterator_14_0= ruleCoIteratorVariableCS ) ) )? otherlv_15= 'in' ( (lv_ownedInitExpression_16_0= ruleExpCS ) ) ) )? )
                    {
                    // InternalEssentialOCL.g:3468:2: ( ( (lv_ownedNameExpression_0_0= ruleNavigatingArgExpCS ) ) ( (otherlv_1= '<-' ( (lv_ownedCoIterator_2_0= ruleCoIteratorVariableCS ) ) (otherlv_3= '=' ( (lv_ownedInitExpression_4_0= ruleExpCS ) ) )? ) | (otherlv_5= ':' ( (lv_ownedType_6_0= ruleTypeExpCS ) ) (otherlv_7= '<-' ( (lv_ownedCoIterator_8_0= ruleCoIteratorVariableCS ) ) )? (otherlv_9= '=' ( (lv_ownedInitExpression_10_0= ruleExpCS ) ) )? ) | ( (otherlv_11= ':' ( (lv_ownedType_12_0= ruleTypeExpCS ) ) )? (otherlv_13= '<-' ( (lv_ownedCoIterator_14_0= ruleCoIteratorVariableCS ) ) )? otherlv_15= 'in' ( (lv_ownedInitExpression_16_0= ruleExpCS ) ) ) )? )
                    // InternalEssentialOCL.g:3468:3: ( (lv_ownedNameExpression_0_0= ruleNavigatingArgExpCS ) ) ( (otherlv_1= '<-' ( (lv_ownedCoIterator_2_0= ruleCoIteratorVariableCS ) ) (otherlv_3= '=' ( (lv_ownedInitExpression_4_0= ruleExpCS ) ) )? ) | (otherlv_5= ':' ( (lv_ownedType_6_0= ruleTypeExpCS ) ) (otherlv_7= '<-' ( (lv_ownedCoIterator_8_0= ruleCoIteratorVariableCS ) ) )? (otherlv_9= '=' ( (lv_ownedInitExpression_10_0= ruleExpCS ) ) )? ) | ( (otherlv_11= ':' ( (lv_ownedType_12_0= ruleTypeExpCS ) ) )? (otherlv_13= '<-' ( (lv_ownedCoIterator_14_0= ruleCoIteratorVariableCS ) ) )? otherlv_15= 'in' ( (lv_ownedInitExpression_16_0= ruleExpCS ) ) ) )?
                    {
                    // InternalEssentialOCL.g:3468:3: ( (lv_ownedNameExpression_0_0= ruleNavigatingArgExpCS ) )
                    // InternalEssentialOCL.g:3469:1: (lv_ownedNameExpression_0_0= ruleNavigatingArgExpCS )
                    {
                    // InternalEssentialOCL.g:3469:1: (lv_ownedNameExpression_0_0= ruleNavigatingArgExpCS )
                    // InternalEssentialOCL.g:3470:3: lv_ownedNameExpression_0_0= ruleNavigatingArgExpCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getNavigatingArgCSAccess().getOwnedNameExpressionNavigatingArgExpCSParserRuleCall_0_0_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_38);
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
                              		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.NavigatingArgExpCS");
                      	        afterParserOrEnumRuleCall();

                    }

                    }


                    }

                    // InternalEssentialOCL.g:3486:2: ( (otherlv_1= '<-' ( (lv_ownedCoIterator_2_0= ruleCoIteratorVariableCS ) ) (otherlv_3= '=' ( (lv_ownedInitExpression_4_0= ruleExpCS ) ) )? ) | (otherlv_5= ':' ( (lv_ownedType_6_0= ruleTypeExpCS ) ) (otherlv_7= '<-' ( (lv_ownedCoIterator_8_0= ruleCoIteratorVariableCS ) ) )? (otherlv_9= '=' ( (lv_ownedInitExpression_10_0= ruleExpCS ) ) )? ) | ( (otherlv_11= ':' ( (lv_ownedType_12_0= ruleTypeExpCS ) ) )? (otherlv_13= '<-' ( (lv_ownedCoIterator_14_0= ruleCoIteratorVariableCS ) ) )? otherlv_15= 'in' ( (lv_ownedInitExpression_16_0= ruleExpCS ) ) ) )?
                    int alt57=4;
                    alt57 = dfa57.predict(input);
                    switch (alt57) {
                        case 1 :
                            // InternalEssentialOCL.g:3486:3: (otherlv_1= '<-' ( (lv_ownedCoIterator_2_0= ruleCoIteratorVariableCS ) ) (otherlv_3= '=' ( (lv_ownedInitExpression_4_0= ruleExpCS ) ) )? )
                            {
                            // InternalEssentialOCL.g:3486:3: (otherlv_1= '<-' ( (lv_ownedCoIterator_2_0= ruleCoIteratorVariableCS ) ) (otherlv_3= '=' ( (lv_ownedInitExpression_4_0= ruleExpCS ) ) )? )
                            // InternalEssentialOCL.g:3486:5: otherlv_1= '<-' ( (lv_ownedCoIterator_2_0= ruleCoIteratorVariableCS ) ) (otherlv_3= '=' ( (lv_ownedInitExpression_4_0= ruleExpCS ) ) )?
                            {
                            otherlv_1=(Token)match(input,64,FollowSets000.FOLLOW_10); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                  	newLeafNode(otherlv_1, grammarAccess.getNavigatingArgCSAccess().getLessThanSignHyphenMinusKeyword_0_1_0_0());

                            }
                            // InternalEssentialOCL.g:3490:1: ( (lv_ownedCoIterator_2_0= ruleCoIteratorVariableCS ) )
                            // InternalEssentialOCL.g:3491:1: (lv_ownedCoIterator_2_0= ruleCoIteratorVariableCS )
                            {
                            // InternalEssentialOCL.g:3491:1: (lv_ownedCoIterator_2_0= ruleCoIteratorVariableCS )
                            // InternalEssentialOCL.g:3492:3: lv_ownedCoIterator_2_0= ruleCoIteratorVariableCS
                            {
                            if ( state.backtracking==0 ) {

                              	        newCompositeNode(grammarAccess.getNavigatingArgCSAccess().getOwnedCoIteratorCoIteratorVariableCSParserRuleCall_0_1_0_1_0());

                            }
                            pushFollow(FollowSets000.FOLLOW_39);
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

                            // InternalEssentialOCL.g:3508:2: (otherlv_3= '=' ( (lv_ownedInitExpression_4_0= ruleExpCS ) ) )?
                            int alt52=2;
                            int LA52_0 = input.LA(1);

                            if ( (LA52_0==26) ) {
                                alt52=1;
                            }
                            switch (alt52) {
                                case 1 :
                                    // InternalEssentialOCL.g:3508:4: otherlv_3= '=' ( (lv_ownedInitExpression_4_0= ruleExpCS ) )
                                    {
                                    otherlv_3=(Token)match(input,26,FollowSets000.FOLLOW_17); if (state.failed) return current;
                                    if ( state.backtracking==0 ) {

                                          	newLeafNode(otherlv_3, grammarAccess.getNavigatingArgCSAccess().getEqualsSignKeyword_0_1_0_2_0());

                                    }
                                    // InternalEssentialOCL.g:3512:1: ( (lv_ownedInitExpression_4_0= ruleExpCS ) )
                                    // InternalEssentialOCL.g:3513:1: (lv_ownedInitExpression_4_0= ruleExpCS )
                                    {
                                    // InternalEssentialOCL.g:3513:1: (lv_ownedInitExpression_4_0= ruleExpCS )
                                    // InternalEssentialOCL.g:3514:3: lv_ownedInitExpression_4_0= ruleExpCS
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
                            // InternalEssentialOCL.g:3531:6: (otherlv_5= ':' ( (lv_ownedType_6_0= ruleTypeExpCS ) ) (otherlv_7= '<-' ( (lv_ownedCoIterator_8_0= ruleCoIteratorVariableCS ) ) )? (otherlv_9= '=' ( (lv_ownedInitExpression_10_0= ruleExpCS ) ) )? )
                            {
                            // InternalEssentialOCL.g:3531:6: (otherlv_5= ':' ( (lv_ownedType_6_0= ruleTypeExpCS ) ) (otherlv_7= '<-' ( (lv_ownedCoIterator_8_0= ruleCoIteratorVariableCS ) ) )? (otherlv_9= '=' ( (lv_ownedInitExpression_10_0= ruleExpCS ) ) )? )
                            // InternalEssentialOCL.g:3531:8: otherlv_5= ':' ( (lv_ownedType_6_0= ruleTypeExpCS ) ) (otherlv_7= '<-' ( (lv_ownedCoIterator_8_0= ruleCoIteratorVariableCS ) ) )? (otherlv_9= '=' ( (lv_ownedInitExpression_10_0= ruleExpCS ) ) )?
                            {
                            otherlv_5=(Token)match(input,58,FollowSets000.FOLLOW_4); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                  	newLeafNode(otherlv_5, grammarAccess.getNavigatingArgCSAccess().getColonKeyword_0_1_1_0());

                            }
                            // InternalEssentialOCL.g:3535:1: ( (lv_ownedType_6_0= ruleTypeExpCS ) )
                            // InternalEssentialOCL.g:3536:1: (lv_ownedType_6_0= ruleTypeExpCS )
                            {
                            // InternalEssentialOCL.g:3536:1: (lv_ownedType_6_0= ruleTypeExpCS )
                            // InternalEssentialOCL.g:3537:3: lv_ownedType_6_0= ruleTypeExpCS
                            {
                            if ( state.backtracking==0 ) {

                              	        newCompositeNode(grammarAccess.getNavigatingArgCSAccess().getOwnedTypeTypeExpCSParserRuleCall_0_1_1_1_0());

                            }
                            pushFollow(FollowSets000.FOLLOW_40);
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

                            // InternalEssentialOCL.g:3553:2: (otherlv_7= '<-' ( (lv_ownedCoIterator_8_0= ruleCoIteratorVariableCS ) ) )?
                            int alt53=2;
                            int LA53_0 = input.LA(1);

                            if ( (LA53_0==64) ) {
                                alt53=1;
                            }
                            switch (alt53) {
                                case 1 :
                                    // InternalEssentialOCL.g:3553:4: otherlv_7= '<-' ( (lv_ownedCoIterator_8_0= ruleCoIteratorVariableCS ) )
                                    {
                                    otherlv_7=(Token)match(input,64,FollowSets000.FOLLOW_10); if (state.failed) return current;
                                    if ( state.backtracking==0 ) {

                                          	newLeafNode(otherlv_7, grammarAccess.getNavigatingArgCSAccess().getLessThanSignHyphenMinusKeyword_0_1_1_2_0());

                                    }
                                    // InternalEssentialOCL.g:3557:1: ( (lv_ownedCoIterator_8_0= ruleCoIteratorVariableCS ) )
                                    // InternalEssentialOCL.g:3558:1: (lv_ownedCoIterator_8_0= ruleCoIteratorVariableCS )
                                    {
                                    // InternalEssentialOCL.g:3558:1: (lv_ownedCoIterator_8_0= ruleCoIteratorVariableCS )
                                    // InternalEssentialOCL.g:3559:3: lv_ownedCoIterator_8_0= ruleCoIteratorVariableCS
                                    {
                                    if ( state.backtracking==0 ) {

                                      	        newCompositeNode(grammarAccess.getNavigatingArgCSAccess().getOwnedCoIteratorCoIteratorVariableCSParserRuleCall_0_1_1_2_1_0());

                                    }
                                    pushFollow(FollowSets000.FOLLOW_39);
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

                            // InternalEssentialOCL.g:3575:4: (otherlv_9= '=' ( (lv_ownedInitExpression_10_0= ruleExpCS ) ) )?
                            int alt54=2;
                            int LA54_0 = input.LA(1);

                            if ( (LA54_0==26) ) {
                                alt54=1;
                            }
                            switch (alt54) {
                                case 1 :
                                    // InternalEssentialOCL.g:3575:6: otherlv_9= '=' ( (lv_ownedInitExpression_10_0= ruleExpCS ) )
                                    {
                                    otherlv_9=(Token)match(input,26,FollowSets000.FOLLOW_17); if (state.failed) return current;
                                    if ( state.backtracking==0 ) {

                                          	newLeafNode(otherlv_9, grammarAccess.getNavigatingArgCSAccess().getEqualsSignKeyword_0_1_1_3_0());

                                    }
                                    // InternalEssentialOCL.g:3579:1: ( (lv_ownedInitExpression_10_0= ruleExpCS ) )
                                    // InternalEssentialOCL.g:3580:1: (lv_ownedInitExpression_10_0= ruleExpCS )
                                    {
                                    // InternalEssentialOCL.g:3580:1: (lv_ownedInitExpression_10_0= ruleExpCS )
                                    // InternalEssentialOCL.g:3581:3: lv_ownedInitExpression_10_0= ruleExpCS
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
                            // InternalEssentialOCL.g:3598:6: ( (otherlv_11= ':' ( (lv_ownedType_12_0= ruleTypeExpCS ) ) )? (otherlv_13= '<-' ( (lv_ownedCoIterator_14_0= ruleCoIteratorVariableCS ) ) )? otherlv_15= 'in' ( (lv_ownedInitExpression_16_0= ruleExpCS ) ) )
                            {
                            // InternalEssentialOCL.g:3598:6: ( (otherlv_11= ':' ( (lv_ownedType_12_0= ruleTypeExpCS ) ) )? (otherlv_13= '<-' ( (lv_ownedCoIterator_14_0= ruleCoIteratorVariableCS ) ) )? otherlv_15= 'in' ( (lv_ownedInitExpression_16_0= ruleExpCS ) ) )
                            // InternalEssentialOCL.g:3598:7: (otherlv_11= ':' ( (lv_ownedType_12_0= ruleTypeExpCS ) ) )? (otherlv_13= '<-' ( (lv_ownedCoIterator_14_0= ruleCoIteratorVariableCS ) ) )? otherlv_15= 'in' ( (lv_ownedInitExpression_16_0= ruleExpCS ) )
                            {
                            // InternalEssentialOCL.g:3598:7: (otherlv_11= ':' ( (lv_ownedType_12_0= ruleTypeExpCS ) ) )?
                            int alt55=2;
                            int LA55_0 = input.LA(1);

                            if ( (LA55_0==58) ) {
                                alt55=1;
                            }
                            switch (alt55) {
                                case 1 :
                                    // InternalEssentialOCL.g:3598:9: otherlv_11= ':' ( (lv_ownedType_12_0= ruleTypeExpCS ) )
                                    {
                                    otherlv_11=(Token)match(input,58,FollowSets000.FOLLOW_4); if (state.failed) return current;
                                    if ( state.backtracking==0 ) {

                                          	newLeafNode(otherlv_11, grammarAccess.getNavigatingArgCSAccess().getColonKeyword_0_1_2_0_0());

                                    }
                                    // InternalEssentialOCL.g:3602:1: ( (lv_ownedType_12_0= ruleTypeExpCS ) )
                                    // InternalEssentialOCL.g:3603:1: (lv_ownedType_12_0= ruleTypeExpCS )
                                    {
                                    // InternalEssentialOCL.g:3603:1: (lv_ownedType_12_0= ruleTypeExpCS )
                                    // InternalEssentialOCL.g:3604:3: lv_ownedType_12_0= ruleTypeExpCS
                                    {
                                    if ( state.backtracking==0 ) {

                                      	        newCompositeNode(grammarAccess.getNavigatingArgCSAccess().getOwnedTypeTypeExpCSParserRuleCall_0_1_2_0_1_0());

                                    }
                                    pushFollow(FollowSets000.FOLLOW_41);
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

                            // InternalEssentialOCL.g:3620:4: (otherlv_13= '<-' ( (lv_ownedCoIterator_14_0= ruleCoIteratorVariableCS ) ) )?
                            int alt56=2;
                            int LA56_0 = input.LA(1);

                            if ( (LA56_0==64) ) {
                                alt56=1;
                            }
                            switch (alt56) {
                                case 1 :
                                    // InternalEssentialOCL.g:3620:6: otherlv_13= '<-' ( (lv_ownedCoIterator_14_0= ruleCoIteratorVariableCS ) )
                                    {
                                    otherlv_13=(Token)match(input,64,FollowSets000.FOLLOW_10); if (state.failed) return current;
                                    if ( state.backtracking==0 ) {

                                          	newLeafNode(otherlv_13, grammarAccess.getNavigatingArgCSAccess().getLessThanSignHyphenMinusKeyword_0_1_2_1_0());

                                    }
                                    // InternalEssentialOCL.g:3624:1: ( (lv_ownedCoIterator_14_0= ruleCoIteratorVariableCS ) )
                                    // InternalEssentialOCL.g:3625:1: (lv_ownedCoIterator_14_0= ruleCoIteratorVariableCS )
                                    {
                                    // InternalEssentialOCL.g:3625:1: (lv_ownedCoIterator_14_0= ruleCoIteratorVariableCS )
                                    // InternalEssentialOCL.g:3626:3: lv_ownedCoIterator_14_0= ruleCoIteratorVariableCS
                                    {
                                    if ( state.backtracking==0 ) {

                                      	        newCompositeNode(grammarAccess.getNavigatingArgCSAccess().getOwnedCoIteratorCoIteratorVariableCSParserRuleCall_0_1_2_1_1_0());

                                    }
                                    pushFollow(FollowSets000.FOLLOW_42);
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

                            otherlv_15=(Token)match(input,73,FollowSets000.FOLLOW_17); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                  	newLeafNode(otherlv_15, grammarAccess.getNavigatingArgCSAccess().getInKeyword_0_1_2_2());

                            }
                            // InternalEssentialOCL.g:3646:1: ( (lv_ownedInitExpression_16_0= ruleExpCS ) )
                            // InternalEssentialOCL.g:3647:1: (lv_ownedInitExpression_16_0= ruleExpCS )
                            {
                            // InternalEssentialOCL.g:3647:1: (lv_ownedInitExpression_16_0= ruleExpCS )
                            // InternalEssentialOCL.g:3648:3: lv_ownedInitExpression_16_0= ruleExpCS
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
                    // InternalEssentialOCL.g:3665:6: (otherlv_17= ':' ( (lv_ownedType_18_0= ruleTypeExpCS ) ) )
                    {
                    // InternalEssentialOCL.g:3665:6: (otherlv_17= ':' ( (lv_ownedType_18_0= ruleTypeExpCS ) ) )
                    // InternalEssentialOCL.g:3665:8: otherlv_17= ':' ( (lv_ownedType_18_0= ruleTypeExpCS ) )
                    {
                    otherlv_17=(Token)match(input,58,FollowSets000.FOLLOW_4); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_17, grammarAccess.getNavigatingArgCSAccess().getColonKeyword_1_0());

                    }
                    // InternalEssentialOCL.g:3669:1: ( (lv_ownedType_18_0= ruleTypeExpCS ) )
                    // InternalEssentialOCL.g:3670:1: (lv_ownedType_18_0= ruleTypeExpCS )
                    {
                    // InternalEssentialOCL.g:3670:1: (lv_ownedType_18_0= ruleTypeExpCS )
                    // InternalEssentialOCL.g:3671:3: lv_ownedType_18_0= ruleTypeExpCS
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
    // InternalEssentialOCL.g:3695:1: entryRuleNavigatingBarArgCS returns [EObject current=null] : iv_ruleNavigatingBarArgCS= ruleNavigatingBarArgCS EOF ;
    public final EObject entryRuleNavigatingBarArgCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNavigatingBarArgCS = null;


        try {
            // InternalEssentialOCL.g:3696:2: (iv_ruleNavigatingBarArgCS= ruleNavigatingBarArgCS EOF )
            // InternalEssentialOCL.g:3697:2: iv_ruleNavigatingBarArgCS= ruleNavigatingBarArgCS EOF
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
    // InternalEssentialOCL.g:3704:1: ruleNavigatingBarArgCS returns [EObject current=null] : ( ( (lv_prefix_0_0= '|' ) ) ( (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS ) ) (otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? )? ) ;
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
            // InternalEssentialOCL.g:3707:28: ( ( ( (lv_prefix_0_0= '|' ) ) ( (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS ) ) (otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? )? ) )
            // InternalEssentialOCL.g:3708:1: ( ( (lv_prefix_0_0= '|' ) ) ( (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS ) ) (otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? )? )
            {
            // InternalEssentialOCL.g:3708:1: ( ( (lv_prefix_0_0= '|' ) ) ( (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS ) ) (otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? )? )
            // InternalEssentialOCL.g:3708:2: ( (lv_prefix_0_0= '|' ) ) ( (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS ) ) (otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? )?
            {
            // InternalEssentialOCL.g:3708:2: ( (lv_prefix_0_0= '|' ) )
            // InternalEssentialOCL.g:3709:1: (lv_prefix_0_0= '|' )
            {
            // InternalEssentialOCL.g:3709:1: (lv_prefix_0_0= '|' )
            // InternalEssentialOCL.g:3710:3: lv_prefix_0_0= '|'
            {
            lv_prefix_0_0=(Token)match(input,74,FollowSets000.FOLLOW_17); if (state.failed) return current;
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

            // InternalEssentialOCL.g:3723:2: ( (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS ) )
            // InternalEssentialOCL.g:3724:1: (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS )
            {
            // InternalEssentialOCL.g:3724:1: (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS )
            // InternalEssentialOCL.g:3725:3: lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getNavigatingBarArgCSAccess().getOwnedNameExpressionNavigatingArgExpCSParserRuleCall_1_0());

            }
            pushFollow(FollowSets000.FOLLOW_43);
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
                      		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.NavigatingArgExpCS");
              	        afterParserOrEnumRuleCall();

            }

            }


            }

            // InternalEssentialOCL.g:3741:2: (otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? )?
            int alt60=2;
            int LA60_0 = input.LA(1);

            if ( (LA60_0==58) ) {
                alt60=1;
            }
            switch (alt60) {
                case 1 :
                    // InternalEssentialOCL.g:3741:4: otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )?
                    {
                    otherlv_2=(Token)match(input,58,FollowSets000.FOLLOW_4); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_2, grammarAccess.getNavigatingBarArgCSAccess().getColonKeyword_2_0());

                    }
                    // InternalEssentialOCL.g:3745:1: ( (lv_ownedType_3_0= ruleTypeExpCS ) )
                    // InternalEssentialOCL.g:3746:1: (lv_ownedType_3_0= ruleTypeExpCS )
                    {
                    // InternalEssentialOCL.g:3746:1: (lv_ownedType_3_0= ruleTypeExpCS )
                    // InternalEssentialOCL.g:3747:3: lv_ownedType_3_0= ruleTypeExpCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getNavigatingBarArgCSAccess().getOwnedTypeTypeExpCSParserRuleCall_2_1_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_39);
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

                    // InternalEssentialOCL.g:3763:2: (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )?
                    int alt59=2;
                    int LA59_0 = input.LA(1);

                    if ( (LA59_0==26) ) {
                        alt59=1;
                    }
                    switch (alt59) {
                        case 1 :
                            // InternalEssentialOCL.g:3763:4: otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) )
                            {
                            otherlv_4=(Token)match(input,26,FollowSets000.FOLLOW_17); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                  	newLeafNode(otherlv_4, grammarAccess.getNavigatingBarArgCSAccess().getEqualsSignKeyword_2_2_0());

                            }
                            // InternalEssentialOCL.g:3767:1: ( (lv_ownedInitExpression_5_0= ruleExpCS ) )
                            // InternalEssentialOCL.g:3768:1: (lv_ownedInitExpression_5_0= ruleExpCS )
                            {
                            // InternalEssentialOCL.g:3768:1: (lv_ownedInitExpression_5_0= ruleExpCS )
                            // InternalEssentialOCL.g:3769:3: lv_ownedInitExpression_5_0= ruleExpCS
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
    // InternalEssentialOCL.g:3793:1: entryRuleNavigatingCommaArgCS returns [EObject current=null] : iv_ruleNavigatingCommaArgCS= ruleNavigatingCommaArgCS EOF ;
    public final EObject entryRuleNavigatingCommaArgCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNavigatingCommaArgCS = null;


        try {
            // InternalEssentialOCL.g:3794:2: (iv_ruleNavigatingCommaArgCS= ruleNavigatingCommaArgCS EOF )
            // InternalEssentialOCL.g:3795:2: iv_ruleNavigatingCommaArgCS= ruleNavigatingCommaArgCS EOF
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
    // InternalEssentialOCL.g:3802:1: ruleNavigatingCommaArgCS returns [EObject current=null] : ( ( (lv_prefix_0_0= ',' ) ) ( (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS ) ) ( (otherlv_2= '<-' ( (lv_ownedCoIterator_3_0= ruleCoIteratorVariableCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? ) | (otherlv_6= ':' ( (lv_ownedType_7_0= ruleTypeExpCS ) ) (otherlv_8= '<-' ( (lv_ownedCoIterator_9_0= ruleCoIteratorVariableCS ) ) )? (otherlv_10= '=' ( (lv_ownedInitExpression_11_0= ruleExpCS ) ) )? ) | ( (otherlv_12= ':' ( (lv_ownedType_13_0= ruleTypeExpCS ) ) )? (otherlv_14= '<-' ( (lv_ownedCoIterator_15_0= ruleCoIteratorVariableCS ) ) )? otherlv_16= 'in' ( (lv_ownedInitExpression_17_0= ruleExpCS ) ) ) )? ) ;
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
            // InternalEssentialOCL.g:3805:28: ( ( ( (lv_prefix_0_0= ',' ) ) ( (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS ) ) ( (otherlv_2= '<-' ( (lv_ownedCoIterator_3_0= ruleCoIteratorVariableCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? ) | (otherlv_6= ':' ( (lv_ownedType_7_0= ruleTypeExpCS ) ) (otherlv_8= '<-' ( (lv_ownedCoIterator_9_0= ruleCoIteratorVariableCS ) ) )? (otherlv_10= '=' ( (lv_ownedInitExpression_11_0= ruleExpCS ) ) )? ) | ( (otherlv_12= ':' ( (lv_ownedType_13_0= ruleTypeExpCS ) ) )? (otherlv_14= '<-' ( (lv_ownedCoIterator_15_0= ruleCoIteratorVariableCS ) ) )? otherlv_16= 'in' ( (lv_ownedInitExpression_17_0= ruleExpCS ) ) ) )? ) )
            // InternalEssentialOCL.g:3806:1: ( ( (lv_prefix_0_0= ',' ) ) ( (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS ) ) ( (otherlv_2= '<-' ( (lv_ownedCoIterator_3_0= ruleCoIteratorVariableCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? ) | (otherlv_6= ':' ( (lv_ownedType_7_0= ruleTypeExpCS ) ) (otherlv_8= '<-' ( (lv_ownedCoIterator_9_0= ruleCoIteratorVariableCS ) ) )? (otherlv_10= '=' ( (lv_ownedInitExpression_11_0= ruleExpCS ) ) )? ) | ( (otherlv_12= ':' ( (lv_ownedType_13_0= ruleTypeExpCS ) ) )? (otherlv_14= '<-' ( (lv_ownedCoIterator_15_0= ruleCoIteratorVariableCS ) ) )? otherlv_16= 'in' ( (lv_ownedInitExpression_17_0= ruleExpCS ) ) ) )? )
            {
            // InternalEssentialOCL.g:3806:1: ( ( (lv_prefix_0_0= ',' ) ) ( (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS ) ) ( (otherlv_2= '<-' ( (lv_ownedCoIterator_3_0= ruleCoIteratorVariableCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? ) | (otherlv_6= ':' ( (lv_ownedType_7_0= ruleTypeExpCS ) ) (otherlv_8= '<-' ( (lv_ownedCoIterator_9_0= ruleCoIteratorVariableCS ) ) )? (otherlv_10= '=' ( (lv_ownedInitExpression_11_0= ruleExpCS ) ) )? ) | ( (otherlv_12= ':' ( (lv_ownedType_13_0= ruleTypeExpCS ) ) )? (otherlv_14= '<-' ( (lv_ownedCoIterator_15_0= ruleCoIteratorVariableCS ) ) )? otherlv_16= 'in' ( (lv_ownedInitExpression_17_0= ruleExpCS ) ) ) )? )
            // InternalEssentialOCL.g:3806:2: ( (lv_prefix_0_0= ',' ) ) ( (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS ) ) ( (otherlv_2= '<-' ( (lv_ownedCoIterator_3_0= ruleCoIteratorVariableCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? ) | (otherlv_6= ':' ( (lv_ownedType_7_0= ruleTypeExpCS ) ) (otherlv_8= '<-' ( (lv_ownedCoIterator_9_0= ruleCoIteratorVariableCS ) ) )? (otherlv_10= '=' ( (lv_ownedInitExpression_11_0= ruleExpCS ) ) )? ) | ( (otherlv_12= ':' ( (lv_ownedType_13_0= ruleTypeExpCS ) ) )? (otherlv_14= '<-' ( (lv_ownedCoIterator_15_0= ruleCoIteratorVariableCS ) ) )? otherlv_16= 'in' ( (lv_ownedInitExpression_17_0= ruleExpCS ) ) ) )?
            {
            // InternalEssentialOCL.g:3806:2: ( (lv_prefix_0_0= ',' ) )
            // InternalEssentialOCL.g:3807:1: (lv_prefix_0_0= ',' )
            {
            // InternalEssentialOCL.g:3807:1: (lv_prefix_0_0= ',' )
            // InternalEssentialOCL.g:3808:3: lv_prefix_0_0= ','
            {
            lv_prefix_0_0=(Token)match(input,57,FollowSets000.FOLLOW_17); if (state.failed) return current;
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

            // InternalEssentialOCL.g:3821:2: ( (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS ) )
            // InternalEssentialOCL.g:3822:1: (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS )
            {
            // InternalEssentialOCL.g:3822:1: (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS )
            // InternalEssentialOCL.g:3823:3: lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getNavigatingCommaArgCSAccess().getOwnedNameExpressionNavigatingArgExpCSParserRuleCall_1_0());

            }
            pushFollow(FollowSets000.FOLLOW_38);
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
                      		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.NavigatingArgExpCS");
              	        afterParserOrEnumRuleCall();

            }

            }


            }

            // InternalEssentialOCL.g:3839:2: ( (otherlv_2= '<-' ( (lv_ownedCoIterator_3_0= ruleCoIteratorVariableCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? ) | (otherlv_6= ':' ( (lv_ownedType_7_0= ruleTypeExpCS ) ) (otherlv_8= '<-' ( (lv_ownedCoIterator_9_0= ruleCoIteratorVariableCS ) ) )? (otherlv_10= '=' ( (lv_ownedInitExpression_11_0= ruleExpCS ) ) )? ) | ( (otherlv_12= ':' ( (lv_ownedType_13_0= ruleTypeExpCS ) ) )? (otherlv_14= '<-' ( (lv_ownedCoIterator_15_0= ruleCoIteratorVariableCS ) ) )? otherlv_16= 'in' ( (lv_ownedInitExpression_17_0= ruleExpCS ) ) ) )?
            int alt66=4;
            alt66 = dfa66.predict(input);
            switch (alt66) {
                case 1 :
                    // InternalEssentialOCL.g:3839:3: (otherlv_2= '<-' ( (lv_ownedCoIterator_3_0= ruleCoIteratorVariableCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? )
                    {
                    // InternalEssentialOCL.g:3839:3: (otherlv_2= '<-' ( (lv_ownedCoIterator_3_0= ruleCoIteratorVariableCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? )
                    // InternalEssentialOCL.g:3839:5: otherlv_2= '<-' ( (lv_ownedCoIterator_3_0= ruleCoIteratorVariableCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )?
                    {
                    otherlv_2=(Token)match(input,64,FollowSets000.FOLLOW_10); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_2, grammarAccess.getNavigatingCommaArgCSAccess().getLessThanSignHyphenMinusKeyword_2_0_0());

                    }
                    // InternalEssentialOCL.g:3843:1: ( (lv_ownedCoIterator_3_0= ruleCoIteratorVariableCS ) )
                    // InternalEssentialOCL.g:3844:1: (lv_ownedCoIterator_3_0= ruleCoIteratorVariableCS )
                    {
                    // InternalEssentialOCL.g:3844:1: (lv_ownedCoIterator_3_0= ruleCoIteratorVariableCS )
                    // InternalEssentialOCL.g:3845:3: lv_ownedCoIterator_3_0= ruleCoIteratorVariableCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getNavigatingCommaArgCSAccess().getOwnedCoIteratorCoIteratorVariableCSParserRuleCall_2_0_1_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_39);
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

                    // InternalEssentialOCL.g:3861:2: (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )?
                    int alt61=2;
                    int LA61_0 = input.LA(1);

                    if ( (LA61_0==26) ) {
                        alt61=1;
                    }
                    switch (alt61) {
                        case 1 :
                            // InternalEssentialOCL.g:3861:4: otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) )
                            {
                            otherlv_4=(Token)match(input,26,FollowSets000.FOLLOW_17); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                  	newLeafNode(otherlv_4, grammarAccess.getNavigatingCommaArgCSAccess().getEqualsSignKeyword_2_0_2_0());

                            }
                            // InternalEssentialOCL.g:3865:1: ( (lv_ownedInitExpression_5_0= ruleExpCS ) )
                            // InternalEssentialOCL.g:3866:1: (lv_ownedInitExpression_5_0= ruleExpCS )
                            {
                            // InternalEssentialOCL.g:3866:1: (lv_ownedInitExpression_5_0= ruleExpCS )
                            // InternalEssentialOCL.g:3867:3: lv_ownedInitExpression_5_0= ruleExpCS
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
                    // InternalEssentialOCL.g:3884:6: (otherlv_6= ':' ( (lv_ownedType_7_0= ruleTypeExpCS ) ) (otherlv_8= '<-' ( (lv_ownedCoIterator_9_0= ruleCoIteratorVariableCS ) ) )? (otherlv_10= '=' ( (lv_ownedInitExpression_11_0= ruleExpCS ) ) )? )
                    {
                    // InternalEssentialOCL.g:3884:6: (otherlv_6= ':' ( (lv_ownedType_7_0= ruleTypeExpCS ) ) (otherlv_8= '<-' ( (lv_ownedCoIterator_9_0= ruleCoIteratorVariableCS ) ) )? (otherlv_10= '=' ( (lv_ownedInitExpression_11_0= ruleExpCS ) ) )? )
                    // InternalEssentialOCL.g:3884:8: otherlv_6= ':' ( (lv_ownedType_7_0= ruleTypeExpCS ) ) (otherlv_8= '<-' ( (lv_ownedCoIterator_9_0= ruleCoIteratorVariableCS ) ) )? (otherlv_10= '=' ( (lv_ownedInitExpression_11_0= ruleExpCS ) ) )?
                    {
                    otherlv_6=(Token)match(input,58,FollowSets000.FOLLOW_4); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_6, grammarAccess.getNavigatingCommaArgCSAccess().getColonKeyword_2_1_0());

                    }
                    // InternalEssentialOCL.g:3888:1: ( (lv_ownedType_7_0= ruleTypeExpCS ) )
                    // InternalEssentialOCL.g:3889:1: (lv_ownedType_7_0= ruleTypeExpCS )
                    {
                    // InternalEssentialOCL.g:3889:1: (lv_ownedType_7_0= ruleTypeExpCS )
                    // InternalEssentialOCL.g:3890:3: lv_ownedType_7_0= ruleTypeExpCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getNavigatingCommaArgCSAccess().getOwnedTypeTypeExpCSParserRuleCall_2_1_1_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_40);
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

                    // InternalEssentialOCL.g:3906:2: (otherlv_8= '<-' ( (lv_ownedCoIterator_9_0= ruleCoIteratorVariableCS ) ) )?
                    int alt62=2;
                    int LA62_0 = input.LA(1);

                    if ( (LA62_0==64) ) {
                        alt62=1;
                    }
                    switch (alt62) {
                        case 1 :
                            // InternalEssentialOCL.g:3906:4: otherlv_8= '<-' ( (lv_ownedCoIterator_9_0= ruleCoIteratorVariableCS ) )
                            {
                            otherlv_8=(Token)match(input,64,FollowSets000.FOLLOW_10); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                  	newLeafNode(otherlv_8, grammarAccess.getNavigatingCommaArgCSAccess().getLessThanSignHyphenMinusKeyword_2_1_2_0());

                            }
                            // InternalEssentialOCL.g:3910:1: ( (lv_ownedCoIterator_9_0= ruleCoIteratorVariableCS ) )
                            // InternalEssentialOCL.g:3911:1: (lv_ownedCoIterator_9_0= ruleCoIteratorVariableCS )
                            {
                            // InternalEssentialOCL.g:3911:1: (lv_ownedCoIterator_9_0= ruleCoIteratorVariableCS )
                            // InternalEssentialOCL.g:3912:3: lv_ownedCoIterator_9_0= ruleCoIteratorVariableCS
                            {
                            if ( state.backtracking==0 ) {

                              	        newCompositeNode(grammarAccess.getNavigatingCommaArgCSAccess().getOwnedCoIteratorCoIteratorVariableCSParserRuleCall_2_1_2_1_0());

                            }
                            pushFollow(FollowSets000.FOLLOW_39);
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

                    // InternalEssentialOCL.g:3928:4: (otherlv_10= '=' ( (lv_ownedInitExpression_11_0= ruleExpCS ) ) )?
                    int alt63=2;
                    int LA63_0 = input.LA(1);

                    if ( (LA63_0==26) ) {
                        alt63=1;
                    }
                    switch (alt63) {
                        case 1 :
                            // InternalEssentialOCL.g:3928:6: otherlv_10= '=' ( (lv_ownedInitExpression_11_0= ruleExpCS ) )
                            {
                            otherlv_10=(Token)match(input,26,FollowSets000.FOLLOW_17); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                  	newLeafNode(otherlv_10, grammarAccess.getNavigatingCommaArgCSAccess().getEqualsSignKeyword_2_1_3_0());

                            }
                            // InternalEssentialOCL.g:3932:1: ( (lv_ownedInitExpression_11_0= ruleExpCS ) )
                            // InternalEssentialOCL.g:3933:1: (lv_ownedInitExpression_11_0= ruleExpCS )
                            {
                            // InternalEssentialOCL.g:3933:1: (lv_ownedInitExpression_11_0= ruleExpCS )
                            // InternalEssentialOCL.g:3934:3: lv_ownedInitExpression_11_0= ruleExpCS
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
                    // InternalEssentialOCL.g:3951:6: ( (otherlv_12= ':' ( (lv_ownedType_13_0= ruleTypeExpCS ) ) )? (otherlv_14= '<-' ( (lv_ownedCoIterator_15_0= ruleCoIteratorVariableCS ) ) )? otherlv_16= 'in' ( (lv_ownedInitExpression_17_0= ruleExpCS ) ) )
                    {
                    // InternalEssentialOCL.g:3951:6: ( (otherlv_12= ':' ( (lv_ownedType_13_0= ruleTypeExpCS ) ) )? (otherlv_14= '<-' ( (lv_ownedCoIterator_15_0= ruleCoIteratorVariableCS ) ) )? otherlv_16= 'in' ( (lv_ownedInitExpression_17_0= ruleExpCS ) ) )
                    // InternalEssentialOCL.g:3951:7: (otherlv_12= ':' ( (lv_ownedType_13_0= ruleTypeExpCS ) ) )? (otherlv_14= '<-' ( (lv_ownedCoIterator_15_0= ruleCoIteratorVariableCS ) ) )? otherlv_16= 'in' ( (lv_ownedInitExpression_17_0= ruleExpCS ) )
                    {
                    // InternalEssentialOCL.g:3951:7: (otherlv_12= ':' ( (lv_ownedType_13_0= ruleTypeExpCS ) ) )?
                    int alt64=2;
                    int LA64_0 = input.LA(1);

                    if ( (LA64_0==58) ) {
                        alt64=1;
                    }
                    switch (alt64) {
                        case 1 :
                            // InternalEssentialOCL.g:3951:9: otherlv_12= ':' ( (lv_ownedType_13_0= ruleTypeExpCS ) )
                            {
                            otherlv_12=(Token)match(input,58,FollowSets000.FOLLOW_4); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                  	newLeafNode(otherlv_12, grammarAccess.getNavigatingCommaArgCSAccess().getColonKeyword_2_2_0_0());

                            }
                            // InternalEssentialOCL.g:3955:1: ( (lv_ownedType_13_0= ruleTypeExpCS ) )
                            // InternalEssentialOCL.g:3956:1: (lv_ownedType_13_0= ruleTypeExpCS )
                            {
                            // InternalEssentialOCL.g:3956:1: (lv_ownedType_13_0= ruleTypeExpCS )
                            // InternalEssentialOCL.g:3957:3: lv_ownedType_13_0= ruleTypeExpCS
                            {
                            if ( state.backtracking==0 ) {

                              	        newCompositeNode(grammarAccess.getNavigatingCommaArgCSAccess().getOwnedTypeTypeExpCSParserRuleCall_2_2_0_1_0());

                            }
                            pushFollow(FollowSets000.FOLLOW_41);
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

                    // InternalEssentialOCL.g:3973:4: (otherlv_14= '<-' ( (lv_ownedCoIterator_15_0= ruleCoIteratorVariableCS ) ) )?
                    int alt65=2;
                    int LA65_0 = input.LA(1);

                    if ( (LA65_0==64) ) {
                        alt65=1;
                    }
                    switch (alt65) {
                        case 1 :
                            // InternalEssentialOCL.g:3973:6: otherlv_14= '<-' ( (lv_ownedCoIterator_15_0= ruleCoIteratorVariableCS ) )
                            {
                            otherlv_14=(Token)match(input,64,FollowSets000.FOLLOW_10); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                  	newLeafNode(otherlv_14, grammarAccess.getNavigatingCommaArgCSAccess().getLessThanSignHyphenMinusKeyword_2_2_1_0());

                            }
                            // InternalEssentialOCL.g:3977:1: ( (lv_ownedCoIterator_15_0= ruleCoIteratorVariableCS ) )
                            // InternalEssentialOCL.g:3978:1: (lv_ownedCoIterator_15_0= ruleCoIteratorVariableCS )
                            {
                            // InternalEssentialOCL.g:3978:1: (lv_ownedCoIterator_15_0= ruleCoIteratorVariableCS )
                            // InternalEssentialOCL.g:3979:3: lv_ownedCoIterator_15_0= ruleCoIteratorVariableCS
                            {
                            if ( state.backtracking==0 ) {

                              	        newCompositeNode(grammarAccess.getNavigatingCommaArgCSAccess().getOwnedCoIteratorCoIteratorVariableCSParserRuleCall_2_2_1_1_0());

                            }
                            pushFollow(FollowSets000.FOLLOW_42);
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

                    otherlv_16=(Token)match(input,73,FollowSets000.FOLLOW_17); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_16, grammarAccess.getNavigatingCommaArgCSAccess().getInKeyword_2_2_2());

                    }
                    // InternalEssentialOCL.g:3999:1: ( (lv_ownedInitExpression_17_0= ruleExpCS ) )
                    // InternalEssentialOCL.g:4000:1: (lv_ownedInitExpression_17_0= ruleExpCS )
                    {
                    // InternalEssentialOCL.g:4000:1: (lv_ownedInitExpression_17_0= ruleExpCS )
                    // InternalEssentialOCL.g:4001:3: lv_ownedInitExpression_17_0= ruleExpCS
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
    // InternalEssentialOCL.g:4025:1: entryRuleNavigatingSemiArgCS returns [EObject current=null] : iv_ruleNavigatingSemiArgCS= ruleNavigatingSemiArgCS EOF ;
    public final EObject entryRuleNavigatingSemiArgCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNavigatingSemiArgCS = null;


        try {
            // InternalEssentialOCL.g:4026:2: (iv_ruleNavigatingSemiArgCS= ruleNavigatingSemiArgCS EOF )
            // InternalEssentialOCL.g:4027:2: iv_ruleNavigatingSemiArgCS= ruleNavigatingSemiArgCS EOF
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
    // InternalEssentialOCL.g:4034:1: ruleNavigatingSemiArgCS returns [EObject current=null] : ( ( (lv_prefix_0_0= ';' ) ) ( (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS ) ) (otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? )? ) ;
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
            // InternalEssentialOCL.g:4037:28: ( ( ( (lv_prefix_0_0= ';' ) ) ( (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS ) ) (otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? )? ) )
            // InternalEssentialOCL.g:4038:1: ( ( (lv_prefix_0_0= ';' ) ) ( (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS ) ) (otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? )? )
            {
            // InternalEssentialOCL.g:4038:1: ( ( (lv_prefix_0_0= ';' ) ) ( (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS ) ) (otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? )? )
            // InternalEssentialOCL.g:4038:2: ( (lv_prefix_0_0= ';' ) ) ( (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS ) ) (otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? )?
            {
            // InternalEssentialOCL.g:4038:2: ( (lv_prefix_0_0= ';' ) )
            // InternalEssentialOCL.g:4039:1: (lv_prefix_0_0= ';' )
            {
            // InternalEssentialOCL.g:4039:1: (lv_prefix_0_0= ';' )
            // InternalEssentialOCL.g:4040:3: lv_prefix_0_0= ';'
            {
            lv_prefix_0_0=(Token)match(input,75,FollowSets000.FOLLOW_17); if (state.failed) return current;
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

            // InternalEssentialOCL.g:4053:2: ( (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS ) )
            // InternalEssentialOCL.g:4054:1: (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS )
            {
            // InternalEssentialOCL.g:4054:1: (lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS )
            // InternalEssentialOCL.g:4055:3: lv_ownedNameExpression_1_0= ruleNavigatingArgExpCS
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getNavigatingSemiArgCSAccess().getOwnedNameExpressionNavigatingArgExpCSParserRuleCall_1_0());

            }
            pushFollow(FollowSets000.FOLLOW_43);
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
                      		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.NavigatingArgExpCS");
              	        afterParserOrEnumRuleCall();

            }

            }


            }

            // InternalEssentialOCL.g:4071:2: (otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? )?
            int alt68=2;
            int LA68_0 = input.LA(1);

            if ( (LA68_0==58) ) {
                alt68=1;
            }
            switch (alt68) {
                case 1 :
                    // InternalEssentialOCL.g:4071:4: otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )?
                    {
                    otherlv_2=(Token)match(input,58,FollowSets000.FOLLOW_4); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_2, grammarAccess.getNavigatingSemiArgCSAccess().getColonKeyword_2_0());

                    }
                    // InternalEssentialOCL.g:4075:1: ( (lv_ownedType_3_0= ruleTypeExpCS ) )
                    // InternalEssentialOCL.g:4076:1: (lv_ownedType_3_0= ruleTypeExpCS )
                    {
                    // InternalEssentialOCL.g:4076:1: (lv_ownedType_3_0= ruleTypeExpCS )
                    // InternalEssentialOCL.g:4077:3: lv_ownedType_3_0= ruleTypeExpCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getNavigatingSemiArgCSAccess().getOwnedTypeTypeExpCSParserRuleCall_2_1_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_39);
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

                    // InternalEssentialOCL.g:4093:2: (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )?
                    int alt67=2;
                    int LA67_0 = input.LA(1);

                    if ( (LA67_0==26) ) {
                        alt67=1;
                    }
                    switch (alt67) {
                        case 1 :
                            // InternalEssentialOCL.g:4093:4: otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) )
                            {
                            otherlv_4=(Token)match(input,26,FollowSets000.FOLLOW_17); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                  	newLeafNode(otherlv_4, grammarAccess.getNavigatingSemiArgCSAccess().getEqualsSignKeyword_2_2_0());

                            }
                            // InternalEssentialOCL.g:4097:1: ( (lv_ownedInitExpression_5_0= ruleExpCS ) )
                            // InternalEssentialOCL.g:4098:1: (lv_ownedInitExpression_5_0= ruleExpCS )
                            {
                            // InternalEssentialOCL.g:4098:1: (lv_ownedInitExpression_5_0= ruleExpCS )
                            // InternalEssentialOCL.g:4099:3: lv_ownedInitExpression_5_0= ruleExpCS
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


    // $ANTLR start "entryRuleNavigatingArgExpCS"
    // InternalEssentialOCL.g:4123:1: entryRuleNavigatingArgExpCS returns [EObject current=null] : iv_ruleNavigatingArgExpCS= ruleNavigatingArgExpCS EOF ;
    public final EObject entryRuleNavigatingArgExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNavigatingArgExpCS = null;


        try {
            // InternalEssentialOCL.g:4124:2: (iv_ruleNavigatingArgExpCS= ruleNavigatingArgExpCS EOF )
            // InternalEssentialOCL.g:4125:2: iv_ruleNavigatingArgExpCS= ruleNavigatingArgExpCS EOF
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
    // InternalEssentialOCL.g:4132:1: ruleNavigatingArgExpCS returns [EObject current=null] : this_ExpCS_0= ruleExpCS ;
    public final EObject ruleNavigatingArgExpCS() throws RecognitionException {
        EObject current = null;

        EObject this_ExpCS_0 = null;


         enterRule();

        try {
            // InternalEssentialOCL.g:4135:28: (this_ExpCS_0= ruleExpCS )
            // InternalEssentialOCL.g:4137:2: this_ExpCS_0= ruleExpCS
            {
            if ( state.backtracking==0 ) {

              	  /* */

            }
            if ( state.backtracking==0 ) {

                      newCompositeNode(grammarAccess.getNavigatingArgExpCSAccess().getExpCSParserRuleCall());

            }
            pushFollow(FollowSets000.FOLLOW_2);
            this_ExpCS_0=ruleExpCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

                      current = this_ExpCS_0;
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
    // $ANTLR end "ruleNavigatingArgExpCS"


    // $ANTLR start "entryRuleCoIteratorVariableCS"
    // InternalEssentialOCL.g:4156:1: entryRuleCoIteratorVariableCS returns [EObject current=null] : iv_ruleCoIteratorVariableCS= ruleCoIteratorVariableCS EOF ;
    public final EObject entryRuleCoIteratorVariableCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCoIteratorVariableCS = null;


        try {
            // InternalEssentialOCL.g:4157:2: (iv_ruleCoIteratorVariableCS= ruleCoIteratorVariableCS EOF )
            // InternalEssentialOCL.g:4158:2: iv_ruleCoIteratorVariableCS= ruleCoIteratorVariableCS EOF
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
    // InternalEssentialOCL.g:4165:1: ruleCoIteratorVariableCS returns [EObject current=null] : ( ( (lv_name_0_0= ruleUnrestrictedName ) ) (otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) ) )? ) ;
    public final EObject ruleCoIteratorVariableCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_name_0_0 = null;

        EObject lv_ownedType_2_0 = null;


         enterRule();

        try {
            // InternalEssentialOCL.g:4168:28: ( ( ( (lv_name_0_0= ruleUnrestrictedName ) ) (otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) ) )? ) )
            // InternalEssentialOCL.g:4169:1: ( ( (lv_name_0_0= ruleUnrestrictedName ) ) (otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) ) )? )
            {
            // InternalEssentialOCL.g:4169:1: ( ( (lv_name_0_0= ruleUnrestrictedName ) ) (otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) ) )? )
            // InternalEssentialOCL.g:4169:2: ( (lv_name_0_0= ruleUnrestrictedName ) ) (otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) ) )?
            {
            // InternalEssentialOCL.g:4169:2: ( (lv_name_0_0= ruleUnrestrictedName ) )
            // InternalEssentialOCL.g:4170:1: (lv_name_0_0= ruleUnrestrictedName )
            {
            // InternalEssentialOCL.g:4170:1: (lv_name_0_0= ruleUnrestrictedName )
            // InternalEssentialOCL.g:4171:3: lv_name_0_0= ruleUnrestrictedName
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getCoIteratorVariableCSAccess().getNameUnrestrictedNameParserRuleCall_0_0());

            }
            pushFollow(FollowSets000.FOLLOW_43);
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
                      		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.UnrestrictedName");
              	        afterParserOrEnumRuleCall();

            }

            }


            }

            // InternalEssentialOCL.g:4187:2: (otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) ) )?
            int alt69=2;
            int LA69_0 = input.LA(1);

            if ( (LA69_0==58) ) {
                alt69=1;
            }
            switch (alt69) {
                case 1 :
                    // InternalEssentialOCL.g:4187:4: otherlv_1= ':' ( (lv_ownedType_2_0= ruleTypeExpCS ) )
                    {
                    otherlv_1=(Token)match(input,58,FollowSets000.FOLLOW_4); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_1, grammarAccess.getCoIteratorVariableCSAccess().getColonKeyword_1_0());

                    }
                    // InternalEssentialOCL.g:4191:1: ( (lv_ownedType_2_0= ruleTypeExpCS ) )
                    // InternalEssentialOCL.g:4192:1: (lv_ownedType_2_0= ruleTypeExpCS )
                    {
                    // InternalEssentialOCL.g:4192:1: (lv_ownedType_2_0= ruleTypeExpCS )
                    // InternalEssentialOCL.g:4193:3: lv_ownedType_2_0= ruleTypeExpCS
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
    // InternalEssentialOCL.g:4217:1: entryRuleIfExpCS returns [EObject current=null] : iv_ruleIfExpCS= ruleIfExpCS EOF ;
    public final EObject entryRuleIfExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIfExpCS = null;


        try {
            // InternalEssentialOCL.g:4218:2: (iv_ruleIfExpCS= ruleIfExpCS EOF )
            // InternalEssentialOCL.g:4219:2: iv_ruleIfExpCS= ruleIfExpCS EOF
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
    // InternalEssentialOCL.g:4226:1: ruleIfExpCS returns [EObject current=null] : (otherlv_0= 'if' ( ( (lv_ownedCondition_1_1= ruleExpCS | lv_ownedCondition_1_2= rulePatternExpCS ) ) ) otherlv_2= 'then' ( (lv_ownedThenExpression_3_0= ruleExpCS ) ) ( (lv_ownedIfThenExpressions_4_0= ruleElseIfThenExpCS ) )* otherlv_5= 'else' ( (lv_ownedElseExpression_6_0= ruleExpCS ) ) otherlv_7= 'endif' ) ;
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
            // InternalEssentialOCL.g:4229:28: ( (otherlv_0= 'if' ( ( (lv_ownedCondition_1_1= ruleExpCS | lv_ownedCondition_1_2= rulePatternExpCS ) ) ) otherlv_2= 'then' ( (lv_ownedThenExpression_3_0= ruleExpCS ) ) ( (lv_ownedIfThenExpressions_4_0= ruleElseIfThenExpCS ) )* otherlv_5= 'else' ( (lv_ownedElseExpression_6_0= ruleExpCS ) ) otherlv_7= 'endif' ) )
            // InternalEssentialOCL.g:4230:1: (otherlv_0= 'if' ( ( (lv_ownedCondition_1_1= ruleExpCS | lv_ownedCondition_1_2= rulePatternExpCS ) ) ) otherlv_2= 'then' ( (lv_ownedThenExpression_3_0= ruleExpCS ) ) ( (lv_ownedIfThenExpressions_4_0= ruleElseIfThenExpCS ) )* otherlv_5= 'else' ( (lv_ownedElseExpression_6_0= ruleExpCS ) ) otherlv_7= 'endif' )
            {
            // InternalEssentialOCL.g:4230:1: (otherlv_0= 'if' ( ( (lv_ownedCondition_1_1= ruleExpCS | lv_ownedCondition_1_2= rulePatternExpCS ) ) ) otherlv_2= 'then' ( (lv_ownedThenExpression_3_0= ruleExpCS ) ) ( (lv_ownedIfThenExpressions_4_0= ruleElseIfThenExpCS ) )* otherlv_5= 'else' ( (lv_ownedElseExpression_6_0= ruleExpCS ) ) otherlv_7= 'endif' )
            // InternalEssentialOCL.g:4230:3: otherlv_0= 'if' ( ( (lv_ownedCondition_1_1= ruleExpCS | lv_ownedCondition_1_2= rulePatternExpCS ) ) ) otherlv_2= 'then' ( (lv_ownedThenExpression_3_0= ruleExpCS ) ) ( (lv_ownedIfThenExpressions_4_0= ruleElseIfThenExpCS ) )* otherlv_5= 'else' ( (lv_ownedElseExpression_6_0= ruleExpCS ) ) otherlv_7= 'endif'
            {
            otherlv_0=(Token)match(input,76,FollowSets000.FOLLOW_15); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getIfExpCSAccess().getIfKeyword_0());

            }
            // InternalEssentialOCL.g:4234:1: ( ( (lv_ownedCondition_1_1= ruleExpCS | lv_ownedCondition_1_2= rulePatternExpCS ) ) )
            // InternalEssentialOCL.g:4235:1: ( (lv_ownedCondition_1_1= ruleExpCS | lv_ownedCondition_1_2= rulePatternExpCS ) )
            {
            // InternalEssentialOCL.g:4235:1: ( (lv_ownedCondition_1_1= ruleExpCS | lv_ownedCondition_1_2= rulePatternExpCS ) )
            // InternalEssentialOCL.g:4236:1: (lv_ownedCondition_1_1= ruleExpCS | lv_ownedCondition_1_2= rulePatternExpCS )
            {
            // InternalEssentialOCL.g:4236:1: (lv_ownedCondition_1_1= ruleExpCS | lv_ownedCondition_1_2= rulePatternExpCS )
            int alt70=2;
            switch ( input.LA(1) ) {
            case RULE_INT:
            case RULE_SINGLE_QUOTED_STRING:
            case 16:
            case 17:
            case 18:
            case 19:
            case 40:
            case 41:
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
            case 63:
            case 65:
            case 66:
            case 67:
            case 68:
            case 76:
            case 81:
            case 82:
                {
                alt70=1;
                }
                break;
            case RULE_SIMPLE_ID:
                {
                int LA70_2 = input.LA(2);

                if ( (LA70_2==16||(LA70_2>=19 && LA70_2<=39)||LA70_2==55||LA70_2==59||LA70_2==69||LA70_2==71||LA70_2==77||LA70_2==86) ) {
                    alt70=1;
                }
                else if ( (LA70_2==58) ) {
                    alt70=2;
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

                if ( (LA70_3==58) ) {
                    alt70=2;
                }
                else if ( (LA70_3==16||(LA70_3>=19 && LA70_3<=39)||LA70_3==55||LA70_3==59||LA70_3==69||LA70_3==71||LA70_3==77||LA70_3==86) ) {
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
            case 58:
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
                    // InternalEssentialOCL.g:4237:3: lv_ownedCondition_1_1= ruleExpCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getIfExpCSAccess().getOwnedConditionExpCSParserRuleCall_1_0_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_44);
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
                    // InternalEssentialOCL.g:4252:8: lv_ownedCondition_1_2= rulePatternExpCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getIfExpCSAccess().getOwnedConditionPatternExpCSParserRuleCall_1_0_1());

                    }
                    pushFollow(FollowSets000.FOLLOW_44);
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

            otherlv_2=(Token)match(input,77,FollowSets000.FOLLOW_17); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_2, grammarAccess.getIfExpCSAccess().getThenKeyword_2());

            }
            // InternalEssentialOCL.g:4274:1: ( (lv_ownedThenExpression_3_0= ruleExpCS ) )
            // InternalEssentialOCL.g:4275:1: (lv_ownedThenExpression_3_0= ruleExpCS )
            {
            // InternalEssentialOCL.g:4275:1: (lv_ownedThenExpression_3_0= ruleExpCS )
            // InternalEssentialOCL.g:4276:3: lv_ownedThenExpression_3_0= ruleExpCS
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getIfExpCSAccess().getOwnedThenExpressionExpCSParserRuleCall_3_0());

            }
            pushFollow(FollowSets000.FOLLOW_45);
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

            // InternalEssentialOCL.g:4292:2: ( (lv_ownedIfThenExpressions_4_0= ruleElseIfThenExpCS ) )*
            loop71:
            do {
                int alt71=2;
                int LA71_0 = input.LA(1);

                if ( (LA71_0==80) ) {
                    alt71=1;
                }


                switch (alt71) {
            	case 1 :
            	    // InternalEssentialOCL.g:4293:1: (lv_ownedIfThenExpressions_4_0= ruleElseIfThenExpCS )
            	    {
            	    // InternalEssentialOCL.g:4293:1: (lv_ownedIfThenExpressions_4_0= ruleElseIfThenExpCS )
            	    // InternalEssentialOCL.g:4294:3: lv_ownedIfThenExpressions_4_0= ruleElseIfThenExpCS
            	    {
            	    if ( state.backtracking==0 ) {

            	      	        newCompositeNode(grammarAccess.getIfExpCSAccess().getOwnedIfThenExpressionsElseIfThenExpCSParserRuleCall_4_0());

            	    }
            	    pushFollow(FollowSets000.FOLLOW_45);
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
            	    break loop71;
                }
            } while (true);

            otherlv_5=(Token)match(input,78,FollowSets000.FOLLOW_17); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_5, grammarAccess.getIfExpCSAccess().getElseKeyword_5());

            }
            // InternalEssentialOCL.g:4314:1: ( (lv_ownedElseExpression_6_0= ruleExpCS ) )
            // InternalEssentialOCL.g:4315:1: (lv_ownedElseExpression_6_0= ruleExpCS )
            {
            // InternalEssentialOCL.g:4315:1: (lv_ownedElseExpression_6_0= ruleExpCS )
            // InternalEssentialOCL.g:4316:3: lv_ownedElseExpression_6_0= ruleExpCS
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getIfExpCSAccess().getOwnedElseExpressionExpCSParserRuleCall_6_0());

            }
            pushFollow(FollowSets000.FOLLOW_46);
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

            otherlv_7=(Token)match(input,79,FollowSets000.FOLLOW_2); if (state.failed) return current;
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
    // InternalEssentialOCL.g:4344:1: entryRuleElseIfThenExpCS returns [EObject current=null] : iv_ruleElseIfThenExpCS= ruleElseIfThenExpCS EOF ;
    public final EObject entryRuleElseIfThenExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleElseIfThenExpCS = null;


        try {
            // InternalEssentialOCL.g:4345:2: (iv_ruleElseIfThenExpCS= ruleElseIfThenExpCS EOF )
            // InternalEssentialOCL.g:4346:2: iv_ruleElseIfThenExpCS= ruleElseIfThenExpCS EOF
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
    // InternalEssentialOCL.g:4353:1: ruleElseIfThenExpCS returns [EObject current=null] : (otherlv_0= 'elseif' ( (lv_ownedCondition_1_0= ruleExpCS ) ) otherlv_2= 'then' ( (lv_ownedThenExpression_3_0= ruleExpCS ) ) ) ;
    public final EObject ruleElseIfThenExpCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        EObject lv_ownedCondition_1_0 = null;

        EObject lv_ownedThenExpression_3_0 = null;


         enterRule();

        try {
            // InternalEssentialOCL.g:4356:28: ( (otherlv_0= 'elseif' ( (lv_ownedCondition_1_0= ruleExpCS ) ) otherlv_2= 'then' ( (lv_ownedThenExpression_3_0= ruleExpCS ) ) ) )
            // InternalEssentialOCL.g:4357:1: (otherlv_0= 'elseif' ( (lv_ownedCondition_1_0= ruleExpCS ) ) otherlv_2= 'then' ( (lv_ownedThenExpression_3_0= ruleExpCS ) ) )
            {
            // InternalEssentialOCL.g:4357:1: (otherlv_0= 'elseif' ( (lv_ownedCondition_1_0= ruleExpCS ) ) otherlv_2= 'then' ( (lv_ownedThenExpression_3_0= ruleExpCS ) ) )
            // InternalEssentialOCL.g:4357:3: otherlv_0= 'elseif' ( (lv_ownedCondition_1_0= ruleExpCS ) ) otherlv_2= 'then' ( (lv_ownedThenExpression_3_0= ruleExpCS ) )
            {
            otherlv_0=(Token)match(input,80,FollowSets000.FOLLOW_17); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getElseIfThenExpCSAccess().getElseifKeyword_0());

            }
            // InternalEssentialOCL.g:4361:1: ( (lv_ownedCondition_1_0= ruleExpCS ) )
            // InternalEssentialOCL.g:4362:1: (lv_ownedCondition_1_0= ruleExpCS )
            {
            // InternalEssentialOCL.g:4362:1: (lv_ownedCondition_1_0= ruleExpCS )
            // InternalEssentialOCL.g:4363:3: lv_ownedCondition_1_0= ruleExpCS
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getElseIfThenExpCSAccess().getOwnedConditionExpCSParserRuleCall_1_0());

            }
            pushFollow(FollowSets000.FOLLOW_44);
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

            otherlv_2=(Token)match(input,77,FollowSets000.FOLLOW_17); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_2, grammarAccess.getElseIfThenExpCSAccess().getThenKeyword_2());

            }
            // InternalEssentialOCL.g:4383:1: ( (lv_ownedThenExpression_3_0= ruleExpCS ) )
            // InternalEssentialOCL.g:4384:1: (lv_ownedThenExpression_3_0= ruleExpCS )
            {
            // InternalEssentialOCL.g:4384:1: (lv_ownedThenExpression_3_0= ruleExpCS )
            // InternalEssentialOCL.g:4385:3: lv_ownedThenExpression_3_0= ruleExpCS
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
    // InternalEssentialOCL.g:4409:1: entryRuleLetExpCS returns [EObject current=null] : iv_ruleLetExpCS= ruleLetExpCS EOF ;
    public final EObject entryRuleLetExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLetExpCS = null;


        try {
            // InternalEssentialOCL.g:4410:2: (iv_ruleLetExpCS= ruleLetExpCS EOF )
            // InternalEssentialOCL.g:4411:2: iv_ruleLetExpCS= ruleLetExpCS EOF
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
    // InternalEssentialOCL.g:4418:1: ruleLetExpCS returns [EObject current=null] : (otherlv_0= 'let' ( (lv_ownedVariables_1_0= ruleLetVariableCS ) ) (otherlv_2= ',' ( (lv_ownedVariables_3_0= ruleLetVariableCS ) ) )* otherlv_4= 'in' ( (lv_ownedInExpression_5_0= ruleExpCS ) ) ) ;
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
            // InternalEssentialOCL.g:4421:28: ( (otherlv_0= 'let' ( (lv_ownedVariables_1_0= ruleLetVariableCS ) ) (otherlv_2= ',' ( (lv_ownedVariables_3_0= ruleLetVariableCS ) ) )* otherlv_4= 'in' ( (lv_ownedInExpression_5_0= ruleExpCS ) ) ) )
            // InternalEssentialOCL.g:4422:1: (otherlv_0= 'let' ( (lv_ownedVariables_1_0= ruleLetVariableCS ) ) (otherlv_2= ',' ( (lv_ownedVariables_3_0= ruleLetVariableCS ) ) )* otherlv_4= 'in' ( (lv_ownedInExpression_5_0= ruleExpCS ) ) )
            {
            // InternalEssentialOCL.g:4422:1: (otherlv_0= 'let' ( (lv_ownedVariables_1_0= ruleLetVariableCS ) ) (otherlv_2= ',' ( (lv_ownedVariables_3_0= ruleLetVariableCS ) ) )* otherlv_4= 'in' ( (lv_ownedInExpression_5_0= ruleExpCS ) ) )
            // InternalEssentialOCL.g:4422:3: otherlv_0= 'let' ( (lv_ownedVariables_1_0= ruleLetVariableCS ) ) (otherlv_2= ',' ( (lv_ownedVariables_3_0= ruleLetVariableCS ) ) )* otherlv_4= 'in' ( (lv_ownedInExpression_5_0= ruleExpCS ) )
            {
            otherlv_0=(Token)match(input,81,FollowSets000.FOLLOW_10); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getLetExpCSAccess().getLetKeyword_0());

            }
            // InternalEssentialOCL.g:4426:1: ( (lv_ownedVariables_1_0= ruleLetVariableCS ) )
            // InternalEssentialOCL.g:4427:1: (lv_ownedVariables_1_0= ruleLetVariableCS )
            {
            // InternalEssentialOCL.g:4427:1: (lv_ownedVariables_1_0= ruleLetVariableCS )
            // InternalEssentialOCL.g:4428:3: lv_ownedVariables_1_0= ruleLetVariableCS
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getLetExpCSAccess().getOwnedVariablesLetVariableCSParserRuleCall_1_0());

            }
            pushFollow(FollowSets000.FOLLOW_47);
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

            // InternalEssentialOCL.g:4444:2: (otherlv_2= ',' ( (lv_ownedVariables_3_0= ruleLetVariableCS ) ) )*
            loop72:
            do {
                int alt72=2;
                int LA72_0 = input.LA(1);

                if ( (LA72_0==57) ) {
                    alt72=1;
                }


                switch (alt72) {
            	case 1 :
            	    // InternalEssentialOCL.g:4444:4: otherlv_2= ',' ( (lv_ownedVariables_3_0= ruleLetVariableCS ) )
            	    {
            	    otherlv_2=(Token)match(input,57,FollowSets000.FOLLOW_10); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_2, grammarAccess.getLetExpCSAccess().getCommaKeyword_2_0());

            	    }
            	    // InternalEssentialOCL.g:4448:1: ( (lv_ownedVariables_3_0= ruleLetVariableCS ) )
            	    // InternalEssentialOCL.g:4449:1: (lv_ownedVariables_3_0= ruleLetVariableCS )
            	    {
            	    // InternalEssentialOCL.g:4449:1: (lv_ownedVariables_3_0= ruleLetVariableCS )
            	    // InternalEssentialOCL.g:4450:3: lv_ownedVariables_3_0= ruleLetVariableCS
            	    {
            	    if ( state.backtracking==0 ) {

            	      	        newCompositeNode(grammarAccess.getLetExpCSAccess().getOwnedVariablesLetVariableCSParserRuleCall_2_1_0());

            	    }
            	    pushFollow(FollowSets000.FOLLOW_47);
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
            	    break loop72;
                }
            } while (true);

            otherlv_4=(Token)match(input,73,FollowSets000.FOLLOW_17); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_4, grammarAccess.getLetExpCSAccess().getInKeyword_3());

            }
            // InternalEssentialOCL.g:4470:1: ( (lv_ownedInExpression_5_0= ruleExpCS ) )
            // InternalEssentialOCL.g:4471:1: (lv_ownedInExpression_5_0= ruleExpCS )
            {
            // InternalEssentialOCL.g:4471:1: (lv_ownedInExpression_5_0= ruleExpCS )
            // InternalEssentialOCL.g:4472:3: lv_ownedInExpression_5_0= ruleExpCS
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
    // InternalEssentialOCL.g:4496:1: entryRuleLetVariableCS returns [EObject current=null] : iv_ruleLetVariableCS= ruleLetVariableCS EOF ;
    public final EObject entryRuleLetVariableCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLetVariableCS = null;


        try {
            // InternalEssentialOCL.g:4497:2: (iv_ruleLetVariableCS= ruleLetVariableCS EOF )
            // InternalEssentialOCL.g:4498:2: iv_ruleLetVariableCS= ruleLetVariableCS EOF
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
    // InternalEssentialOCL.g:4505:1: ruleLetVariableCS returns [EObject current=null] : ( ( (lv_name_0_0= ruleUnrestrictedName ) ) ( (lv_ownedRoundBracketedClause_1_0= ruleRoundBracketedClauseCS ) )? (otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) )? otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) ) ;
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
            // InternalEssentialOCL.g:4508:28: ( ( ( (lv_name_0_0= ruleUnrestrictedName ) ) ( (lv_ownedRoundBracketedClause_1_0= ruleRoundBracketedClauseCS ) )? (otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) )? otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) ) )
            // InternalEssentialOCL.g:4509:1: ( ( (lv_name_0_0= ruleUnrestrictedName ) ) ( (lv_ownedRoundBracketedClause_1_0= ruleRoundBracketedClauseCS ) )? (otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) )? otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )
            {
            // InternalEssentialOCL.g:4509:1: ( ( (lv_name_0_0= ruleUnrestrictedName ) ) ( (lv_ownedRoundBracketedClause_1_0= ruleRoundBracketedClauseCS ) )? (otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) )? otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )
            // InternalEssentialOCL.g:4509:2: ( (lv_name_0_0= ruleUnrestrictedName ) ) ( (lv_ownedRoundBracketedClause_1_0= ruleRoundBracketedClauseCS ) )? (otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) )? otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) )
            {
            // InternalEssentialOCL.g:4509:2: ( (lv_name_0_0= ruleUnrestrictedName ) )
            // InternalEssentialOCL.g:4510:1: (lv_name_0_0= ruleUnrestrictedName )
            {
            // InternalEssentialOCL.g:4510:1: (lv_name_0_0= ruleUnrestrictedName )
            // InternalEssentialOCL.g:4511:3: lv_name_0_0= ruleUnrestrictedName
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getLetVariableCSAccess().getNameUnrestrictedNameParserRuleCall_0_0());

            }
            pushFollow(FollowSets000.FOLLOW_48);
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
                      		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.UnrestrictedName");
              	        afterParserOrEnumRuleCall();

            }

            }


            }

            // InternalEssentialOCL.g:4527:2: ( (lv_ownedRoundBracketedClause_1_0= ruleRoundBracketedClauseCS ) )?
            int alt73=2;
            int LA73_0 = input.LA(1);

            if ( (LA73_0==55) ) {
                alt73=1;
            }
            switch (alt73) {
                case 1 :
                    // InternalEssentialOCL.g:4528:1: (lv_ownedRoundBracketedClause_1_0= ruleRoundBracketedClauseCS )
                    {
                    // InternalEssentialOCL.g:4528:1: (lv_ownedRoundBracketedClause_1_0= ruleRoundBracketedClauseCS )
                    // InternalEssentialOCL.g:4529:3: lv_ownedRoundBracketedClause_1_0= ruleRoundBracketedClauseCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getLetVariableCSAccess().getOwnedRoundBracketedClauseRoundBracketedClauseCSParserRuleCall_1_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_23);
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

            // InternalEssentialOCL.g:4545:3: (otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) ) )?
            int alt74=2;
            int LA74_0 = input.LA(1);

            if ( (LA74_0==58) ) {
                alt74=1;
            }
            switch (alt74) {
                case 1 :
                    // InternalEssentialOCL.g:4545:5: otherlv_2= ':' ( (lv_ownedType_3_0= ruleTypeExpCS ) )
                    {
                    otherlv_2=(Token)match(input,58,FollowSets000.FOLLOW_4); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_2, grammarAccess.getLetVariableCSAccess().getColonKeyword_2_0());

                    }
                    // InternalEssentialOCL.g:4549:1: ( (lv_ownedType_3_0= ruleTypeExpCS ) )
                    // InternalEssentialOCL.g:4550:1: (lv_ownedType_3_0= ruleTypeExpCS )
                    {
                    // InternalEssentialOCL.g:4550:1: (lv_ownedType_3_0= ruleTypeExpCS )
                    // InternalEssentialOCL.g:4551:3: lv_ownedType_3_0= ruleTypeExpCS
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

            otherlv_4=(Token)match(input,26,FollowSets000.FOLLOW_17); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_4, grammarAccess.getLetVariableCSAccess().getEqualsSignKeyword_3());

            }
            // InternalEssentialOCL.g:4571:1: ( (lv_ownedInitExpression_5_0= ruleExpCS ) )
            // InternalEssentialOCL.g:4572:1: (lv_ownedInitExpression_5_0= ruleExpCS )
            {
            // InternalEssentialOCL.g:4572:1: (lv_ownedInitExpression_5_0= ruleExpCS )
            // InternalEssentialOCL.g:4573:3: lv_ownedInitExpression_5_0= ruleExpCS
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
    // InternalEssentialOCL.g:4597:1: entryRuleNestedExpCS returns [EObject current=null] : iv_ruleNestedExpCS= ruleNestedExpCS EOF ;
    public final EObject entryRuleNestedExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNestedExpCS = null;


        try {
            // InternalEssentialOCL.g:4598:2: (iv_ruleNestedExpCS= ruleNestedExpCS EOF )
            // InternalEssentialOCL.g:4599:2: iv_ruleNestedExpCS= ruleNestedExpCS EOF
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
    // InternalEssentialOCL.g:4606:1: ruleNestedExpCS returns [EObject current=null] : (otherlv_0= '(' ( (lv_ownedExpression_1_0= ruleExpCS ) ) otherlv_2= ')' ) ;
    public final EObject ruleNestedExpCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        EObject lv_ownedExpression_1_0 = null;


         enterRule();

        try {
            // InternalEssentialOCL.g:4609:28: ( (otherlv_0= '(' ( (lv_ownedExpression_1_0= ruleExpCS ) ) otherlv_2= ')' ) )
            // InternalEssentialOCL.g:4610:1: (otherlv_0= '(' ( (lv_ownedExpression_1_0= ruleExpCS ) ) otherlv_2= ')' )
            {
            // InternalEssentialOCL.g:4610:1: (otherlv_0= '(' ( (lv_ownedExpression_1_0= ruleExpCS ) ) otherlv_2= ')' )
            // InternalEssentialOCL.g:4610:3: otherlv_0= '(' ( (lv_ownedExpression_1_0= ruleExpCS ) ) otherlv_2= ')'
            {
            otherlv_0=(Token)match(input,55,FollowSets000.FOLLOW_17); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getNestedExpCSAccess().getLeftParenthesisKeyword_0());

            }
            // InternalEssentialOCL.g:4614:1: ( (lv_ownedExpression_1_0= ruleExpCS ) )
            // InternalEssentialOCL.g:4615:1: (lv_ownedExpression_1_0= ruleExpCS )
            {
            // InternalEssentialOCL.g:4615:1: (lv_ownedExpression_1_0= ruleExpCS )
            // InternalEssentialOCL.g:4616:3: lv_ownedExpression_1_0= ruleExpCS
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getNestedExpCSAccess().getOwnedExpressionExpCSParserRuleCall_1_0());

            }
            pushFollow(FollowSets000.FOLLOW_6);
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

            otherlv_2=(Token)match(input,56,FollowSets000.FOLLOW_2); if (state.failed) return current;
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
    // InternalEssentialOCL.g:4644:1: entryRuleSelfExpCS returns [EObject current=null] : iv_ruleSelfExpCS= ruleSelfExpCS EOF ;
    public final EObject entryRuleSelfExpCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSelfExpCS = null;


        try {
            // InternalEssentialOCL.g:4645:2: (iv_ruleSelfExpCS= ruleSelfExpCS EOF )
            // InternalEssentialOCL.g:4646:2: iv_ruleSelfExpCS= ruleSelfExpCS EOF
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
    // InternalEssentialOCL.g:4653:1: ruleSelfExpCS returns [EObject current=null] : ( () otherlv_1= 'self' ) ;
    public final EObject ruleSelfExpCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;

         enterRule();

        try {
            // InternalEssentialOCL.g:4656:28: ( ( () otherlv_1= 'self' ) )
            // InternalEssentialOCL.g:4657:1: ( () otherlv_1= 'self' )
            {
            // InternalEssentialOCL.g:4657:1: ( () otherlv_1= 'self' )
            // InternalEssentialOCL.g:4657:2: () otherlv_1= 'self'
            {
            // InternalEssentialOCL.g:4657:2: ()
            // InternalEssentialOCL.g:4658:2:
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

            otherlv_1=(Token)match(input,82,FollowSets000.FOLLOW_2); if (state.failed) return current;
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
    // InternalEssentialOCL.g:4678:1: entryRuleMultiplicityBoundsCS returns [EObject current=null] : iv_ruleMultiplicityBoundsCS= ruleMultiplicityBoundsCS EOF ;
    public final EObject entryRuleMultiplicityBoundsCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMultiplicityBoundsCS = null;


        try {
            // InternalEssentialOCL.g:4679:2: (iv_ruleMultiplicityBoundsCS= ruleMultiplicityBoundsCS EOF )
            // InternalEssentialOCL.g:4680:2: iv_ruleMultiplicityBoundsCS= ruleMultiplicityBoundsCS EOF
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
    // InternalEssentialOCL.g:4687:1: ruleMultiplicityBoundsCS returns [EObject current=null] : ( ( (lv_lowerBound_0_0= ruleLOWER ) ) (otherlv_1= '..' ( (lv_upperBound_2_0= ruleUPPER ) ) )? ) ;
    public final EObject ruleMultiplicityBoundsCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_lowerBound_0_0 = null;

        AntlrDatatypeRuleToken lv_upperBound_2_0 = null;


         enterRule();

        try {
            // InternalEssentialOCL.g:4690:28: ( ( ( (lv_lowerBound_0_0= ruleLOWER ) ) (otherlv_1= '..' ( (lv_upperBound_2_0= ruleUPPER ) ) )? ) )
            // InternalEssentialOCL.g:4691:1: ( ( (lv_lowerBound_0_0= ruleLOWER ) ) (otherlv_1= '..' ( (lv_upperBound_2_0= ruleUPPER ) ) )? )
            {
            // InternalEssentialOCL.g:4691:1: ( ( (lv_lowerBound_0_0= ruleLOWER ) ) (otherlv_1= '..' ( (lv_upperBound_2_0= ruleUPPER ) ) )? )
            // InternalEssentialOCL.g:4691:2: ( (lv_lowerBound_0_0= ruleLOWER ) ) (otherlv_1= '..' ( (lv_upperBound_2_0= ruleUPPER ) ) )?
            {
            // InternalEssentialOCL.g:4691:2: ( (lv_lowerBound_0_0= ruleLOWER ) )
            // InternalEssentialOCL.g:4692:1: (lv_lowerBound_0_0= ruleLOWER )
            {
            // InternalEssentialOCL.g:4692:1: (lv_lowerBound_0_0= ruleLOWER )
            // InternalEssentialOCL.g:4693:3: lv_lowerBound_0_0= ruleLOWER
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getMultiplicityBoundsCSAccess().getLowerBoundLOWERParserRuleCall_0_0());

            }
            pushFollow(FollowSets000.FOLLOW_16);
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

            // InternalEssentialOCL.g:4709:2: (otherlv_1= '..' ( (lv_upperBound_2_0= ruleUPPER ) ) )?
            int alt75=2;
            int LA75_0 = input.LA(1);

            if ( (LA75_0==61) ) {
                alt75=1;
            }
            switch (alt75) {
                case 1 :
                    // InternalEssentialOCL.g:4709:4: otherlv_1= '..' ( (lv_upperBound_2_0= ruleUPPER ) )
                    {
                    otherlv_1=(Token)match(input,61,FollowSets000.FOLLOW_49); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_1, grammarAccess.getMultiplicityBoundsCSAccess().getFullStopFullStopKeyword_1_0());

                    }
                    // InternalEssentialOCL.g:4713:1: ( (lv_upperBound_2_0= ruleUPPER ) )
                    // InternalEssentialOCL.g:4714:1: (lv_upperBound_2_0= ruleUPPER )
                    {
                    // InternalEssentialOCL.g:4714:1: (lv_upperBound_2_0= ruleUPPER )
                    // InternalEssentialOCL.g:4715:3: lv_upperBound_2_0= ruleUPPER
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
    // InternalEssentialOCL.g:4739:1: entryRuleMultiplicityCS returns [EObject current=null] : iv_ruleMultiplicityCS= ruleMultiplicityCS EOF ;
    public final EObject entryRuleMultiplicityCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMultiplicityCS = null;


        try {
            // InternalEssentialOCL.g:4740:2: (iv_ruleMultiplicityCS= ruleMultiplicityCS EOF )
            // InternalEssentialOCL.g:4741:2: iv_ruleMultiplicityCS= ruleMultiplicityCS EOF
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
    // InternalEssentialOCL.g:4748:1: ruleMultiplicityCS returns [EObject current=null] : (otherlv_0= '[' (this_MultiplicityBoundsCS_1= ruleMultiplicityBoundsCS | this_MultiplicityStringCS_2= ruleMultiplicityStringCS ) (otherlv_3= '|?' | ( (lv_isNullFree_4_0= '|1' ) ) )? otherlv_5= ']' ) ;
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
            // InternalEssentialOCL.g:4751:28: ( (otherlv_0= '[' (this_MultiplicityBoundsCS_1= ruleMultiplicityBoundsCS | this_MultiplicityStringCS_2= ruleMultiplicityStringCS ) (otherlv_3= '|?' | ( (lv_isNullFree_4_0= '|1' ) ) )? otherlv_5= ']' ) )
            // InternalEssentialOCL.g:4752:1: (otherlv_0= '[' (this_MultiplicityBoundsCS_1= ruleMultiplicityBoundsCS | this_MultiplicityStringCS_2= ruleMultiplicityStringCS ) (otherlv_3= '|?' | ( (lv_isNullFree_4_0= '|1' ) ) )? otherlv_5= ']' )
            {
            // InternalEssentialOCL.g:4752:1: (otherlv_0= '[' (this_MultiplicityBoundsCS_1= ruleMultiplicityBoundsCS | this_MultiplicityStringCS_2= ruleMultiplicityStringCS ) (otherlv_3= '|?' | ( (lv_isNullFree_4_0= '|1' ) ) )? otherlv_5= ']' )
            // InternalEssentialOCL.g:4752:3: otherlv_0= '[' (this_MultiplicityBoundsCS_1= ruleMultiplicityBoundsCS | this_MultiplicityStringCS_2= ruleMultiplicityStringCS ) (otherlv_3= '|?' | ( (lv_isNullFree_4_0= '|1' ) ) )? otherlv_5= ']'
            {
            otherlv_0=(Token)match(input,71,FollowSets000.FOLLOW_50); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getMultiplicityCSAccess().getLeftSquareBracketKeyword_0());

            }
            // InternalEssentialOCL.g:4756:1: (this_MultiplicityBoundsCS_1= ruleMultiplicityBoundsCS | this_MultiplicityStringCS_2= ruleMultiplicityStringCS )
            int alt76=2;
            int LA76_0 = input.LA(1);

            if ( (LA76_0==RULE_INT) ) {
                alt76=1;
            }
            else if ( (LA76_0==19||LA76_0==21||LA76_0==85) ) {
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
                    // InternalEssentialOCL.g:4757:2: this_MultiplicityBoundsCS_1= ruleMultiplicityBoundsCS
                    {
                    if ( state.backtracking==0 ) {

                      	  /* */

                    }
                    if ( state.backtracking==0 ) {

                              newCompositeNode(grammarAccess.getMultiplicityCSAccess().getMultiplicityBoundsCSParserRuleCall_1_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_51);
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
                    // InternalEssentialOCL.g:4770:2: this_MultiplicityStringCS_2= ruleMultiplicityStringCS
                    {
                    if ( state.backtracking==0 ) {

                      	  /* */

                    }
                    if ( state.backtracking==0 ) {

                              newCompositeNode(grammarAccess.getMultiplicityCSAccess().getMultiplicityStringCSParserRuleCall_1_1());

                    }
                    pushFollow(FollowSets000.FOLLOW_51);
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

            // InternalEssentialOCL.g:4781:2: (otherlv_3= '|?' | ( (lv_isNullFree_4_0= '|1' ) ) )?
            int alt77=3;
            int LA77_0 = input.LA(1);

            if ( (LA77_0==83) ) {
                alt77=1;
            }
            else if ( (LA77_0==84) ) {
                alt77=2;
            }
            switch (alt77) {
                case 1 :
                    // InternalEssentialOCL.g:4781:4: otherlv_3= '|?'
                    {
                    otherlv_3=(Token)match(input,83,FollowSets000.FOLLOW_52); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_3, grammarAccess.getMultiplicityCSAccess().getVerticalLineQuestionMarkKeyword_2_0());

                    }

                    }
                    break;
                case 2 :
                    // InternalEssentialOCL.g:4786:6: ( (lv_isNullFree_4_0= '|1' ) )
                    {
                    // InternalEssentialOCL.g:4786:6: ( (lv_isNullFree_4_0= '|1' ) )
                    // InternalEssentialOCL.g:4787:1: (lv_isNullFree_4_0= '|1' )
                    {
                    // InternalEssentialOCL.g:4787:1: (lv_isNullFree_4_0= '|1' )
                    // InternalEssentialOCL.g:4788:3: lv_isNullFree_4_0= '|1'
                    {
                    lv_isNullFree_4_0=(Token)match(input,84,FollowSets000.FOLLOW_52); if (state.failed) return current;
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

            otherlv_5=(Token)match(input,72,FollowSets000.FOLLOW_2); if (state.failed) return current;
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
    // InternalEssentialOCL.g:4813:1: entryRuleMultiplicityStringCS returns [EObject current=null] : iv_ruleMultiplicityStringCS= ruleMultiplicityStringCS EOF ;
    public final EObject entryRuleMultiplicityStringCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMultiplicityStringCS = null;


        try {
            // InternalEssentialOCL.g:4814:2: (iv_ruleMultiplicityStringCS= ruleMultiplicityStringCS EOF )
            // InternalEssentialOCL.g:4815:2: iv_ruleMultiplicityStringCS= ruleMultiplicityStringCS EOF
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
    // InternalEssentialOCL.g:4822:1: ruleMultiplicityStringCS returns [EObject current=null] : ( ( (lv_stringBounds_0_1= '*' | lv_stringBounds_0_2= '+' | lv_stringBounds_0_3= '?' ) ) ) ;
    public final EObject ruleMultiplicityStringCS() throws RecognitionException {
        EObject current = null;

        Token lv_stringBounds_0_1=null;
        Token lv_stringBounds_0_2=null;
        Token lv_stringBounds_0_3=null;

         enterRule();

        try {
            // InternalEssentialOCL.g:4825:28: ( ( ( (lv_stringBounds_0_1= '*' | lv_stringBounds_0_2= '+' | lv_stringBounds_0_3= '?' ) ) ) )
            // InternalEssentialOCL.g:4826:1: ( ( (lv_stringBounds_0_1= '*' | lv_stringBounds_0_2= '+' | lv_stringBounds_0_3= '?' ) ) )
            {
            // InternalEssentialOCL.g:4826:1: ( ( (lv_stringBounds_0_1= '*' | lv_stringBounds_0_2= '+' | lv_stringBounds_0_3= '?' ) ) )
            // InternalEssentialOCL.g:4827:1: ( (lv_stringBounds_0_1= '*' | lv_stringBounds_0_2= '+' | lv_stringBounds_0_3= '?' ) )
            {
            // InternalEssentialOCL.g:4827:1: ( (lv_stringBounds_0_1= '*' | lv_stringBounds_0_2= '+' | lv_stringBounds_0_3= '?' ) )
            // InternalEssentialOCL.g:4828:1: (lv_stringBounds_0_1= '*' | lv_stringBounds_0_2= '+' | lv_stringBounds_0_3= '?' )
            {
            // InternalEssentialOCL.g:4828:1: (lv_stringBounds_0_1= '*' | lv_stringBounds_0_2= '+' | lv_stringBounds_0_3= '?' )
            int alt78=3;
            switch ( input.LA(1) ) {
            case 19:
                {
                alt78=1;
                }
                break;
            case 21:
                {
                alt78=2;
                }
                break;
            case 85:
                {
                alt78=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 78, 0, input);

                throw nvae;
            }

            switch (alt78) {
                case 1 :
                    // InternalEssentialOCL.g:4829:3: lv_stringBounds_0_1= '*'
                    {
                    lv_stringBounds_0_1=(Token)match(input,19,FollowSets000.FOLLOW_2); if (state.failed) return current;
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
                    // InternalEssentialOCL.g:4841:8: lv_stringBounds_0_2= '+'
                    {
                    lv_stringBounds_0_2=(Token)match(input,21,FollowSets000.FOLLOW_2); if (state.failed) return current;
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
                    // InternalEssentialOCL.g:4853:8: lv_stringBounds_0_3= '?'
                    {
                    lv_stringBounds_0_3=(Token)match(input,85,FollowSets000.FOLLOW_2); if (state.failed) return current;
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
    // InternalEssentialOCL.g:4876:1: entryRulePathNameCS returns [EObject current=null] : iv_rulePathNameCS= rulePathNameCS EOF ;
    public final EObject entryRulePathNameCS() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePathNameCS = null;


        try {
            // InternalEssentialOCL.g:4877:2: (iv_rulePathNameCS= rulePathNameCS EOF )
            // InternalEssentialOCL.g:4878:2: iv_rulePathNameCS= rulePathNameCS EOF
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
    // InternalEssentialOCL.g:4885:1: rulePathNameCS returns [EObject current=null] : ( ( (lv_ownedPathElements_0_0= ruleFirstPathElementCS ) ) (otherlv_1= '::' ( (lv_ownedPathElements_2_0= ruleNextPathElementCS ) ) )* ) ;
    public final EObject rulePathNameCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject lv_ownedPathElements_0_0 = null;

        EObject lv_ownedPathElements_2_0 = null;


         enterRule();

        try {
            // InternalEssentialOCL.g:4888:28: ( ( ( (lv_ownedPathElements_0_0= ruleFirstPathElementCS ) ) (otherlv_1= '::' ( (lv_ownedPathElements_2_0= ruleNextPathElementCS ) ) )* ) )
            // InternalEssentialOCL.g:4889:1: ( ( (lv_ownedPathElements_0_0= ruleFirstPathElementCS ) ) (otherlv_1= '::' ( (lv_ownedPathElements_2_0= ruleNextPathElementCS ) ) )* )
            {
            // InternalEssentialOCL.g:4889:1: ( ( (lv_ownedPathElements_0_0= ruleFirstPathElementCS ) ) (otherlv_1= '::' ( (lv_ownedPathElements_2_0= ruleNextPathElementCS ) ) )* )
            // InternalEssentialOCL.g:4889:2: ( (lv_ownedPathElements_0_0= ruleFirstPathElementCS ) ) (otherlv_1= '::' ( (lv_ownedPathElements_2_0= ruleNextPathElementCS ) ) )*
            {
            // InternalEssentialOCL.g:4889:2: ( (lv_ownedPathElements_0_0= ruleFirstPathElementCS ) )
            // InternalEssentialOCL.g:4890:1: (lv_ownedPathElements_0_0= ruleFirstPathElementCS )
            {
            // InternalEssentialOCL.g:4890:1: (lv_ownedPathElements_0_0= ruleFirstPathElementCS )
            // InternalEssentialOCL.g:4891:3: lv_ownedPathElements_0_0= ruleFirstPathElementCS
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getPathNameCSAccess().getOwnedPathElementsFirstPathElementCSParserRuleCall_0_0());

            }
            pushFollow(FollowSets000.FOLLOW_53);
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

            // InternalEssentialOCL.g:4907:2: (otherlv_1= '::' ( (lv_ownedPathElements_2_0= ruleNextPathElementCS ) ) )*
            loop79:
            do {
                int alt79=2;
                int LA79_0 = input.LA(1);

                if ( (LA79_0==86) ) {
                    alt79=1;
                }


                switch (alt79) {
            	case 1 :
            	    // InternalEssentialOCL.g:4907:4: otherlv_1= '::' ( (lv_ownedPathElements_2_0= ruleNextPathElementCS ) )
            	    {
            	    otherlv_1=(Token)match(input,86,FollowSets000.FOLLOW_4); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_1, grammarAccess.getPathNameCSAccess().getColonColonKeyword_1_0());

            	    }
            	    // InternalEssentialOCL.g:4911:1: ( (lv_ownedPathElements_2_0= ruleNextPathElementCS ) )
            	    // InternalEssentialOCL.g:4912:1: (lv_ownedPathElements_2_0= ruleNextPathElementCS )
            	    {
            	    // InternalEssentialOCL.g:4912:1: (lv_ownedPathElements_2_0= ruleNextPathElementCS )
            	    // InternalEssentialOCL.g:4913:3: lv_ownedPathElements_2_0= ruleNextPathElementCS
            	    {
            	    if ( state.backtracking==0 ) {

            	      	        newCompositeNode(grammarAccess.getPathNameCSAccess().getOwnedPathElementsNextPathElementCSParserRuleCall_1_1_0());

            	    }
            	    pushFollow(FollowSets000.FOLLOW_53);
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
            	    break loop79;
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
    // InternalEssentialOCL.g:4939:1: entryRuleFirstPathElementCS returns [EObject current=null] : iv_ruleFirstPathElementCS= ruleFirstPathElementCS EOF ;
    public final EObject entryRuleFirstPathElementCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFirstPathElementCS = null;


        try {
            // InternalEssentialOCL.g:4940:2: (iv_ruleFirstPathElementCS= ruleFirstPathElementCS EOF )
            // InternalEssentialOCL.g:4941:2: iv_ruleFirstPathElementCS= ruleFirstPathElementCS EOF
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
    // InternalEssentialOCL.g:4948:1: ruleFirstPathElementCS returns [EObject current=null] : ( ( ruleUnrestrictedName ) ) ;
    public final EObject ruleFirstPathElementCS() throws RecognitionException {
        EObject current = null;

         enterRule();

        try {
            // InternalEssentialOCL.g:4951:28: ( ( ( ruleUnrestrictedName ) ) )
            // InternalEssentialOCL.g:4952:1: ( ( ruleUnrestrictedName ) )
            {
            // InternalEssentialOCL.g:4952:1: ( ( ruleUnrestrictedName ) )
            // InternalEssentialOCL.g:4953:1: ( ruleUnrestrictedName )
            {
            // InternalEssentialOCL.g:4953:1: ( ruleUnrestrictedName )
            // InternalEssentialOCL.g:4954:3: ruleUnrestrictedName
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
    // InternalEssentialOCL.g:4978:1: entryRuleNextPathElementCS returns [EObject current=null] : iv_ruleNextPathElementCS= ruleNextPathElementCS EOF ;
    public final EObject entryRuleNextPathElementCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNextPathElementCS = null;


        try {
            // InternalEssentialOCL.g:4979:2: (iv_ruleNextPathElementCS= ruleNextPathElementCS EOF )
            // InternalEssentialOCL.g:4980:2: iv_ruleNextPathElementCS= ruleNextPathElementCS EOF
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
    // InternalEssentialOCL.g:4987:1: ruleNextPathElementCS returns [EObject current=null] : ( ( ruleUnreservedName ) ) ;
    public final EObject ruleNextPathElementCS() throws RecognitionException {
        EObject current = null;

         enterRule();

        try {
            // InternalEssentialOCL.g:4990:28: ( ( ( ruleUnreservedName ) ) )
            // InternalEssentialOCL.g:4991:1: ( ( ruleUnreservedName ) )
            {
            // InternalEssentialOCL.g:4991:1: ( ( ruleUnreservedName ) )
            // InternalEssentialOCL.g:4992:1: ( ruleUnreservedName )
            {
            // InternalEssentialOCL.g:4992:1: ( ruleUnreservedName )
            // InternalEssentialOCL.g:4993:3: ruleUnreservedName
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
    // InternalEssentialOCL.g:5017:1: entryRuleTemplateBindingCS returns [EObject current=null] : iv_ruleTemplateBindingCS= ruleTemplateBindingCS EOF ;
    public final EObject entryRuleTemplateBindingCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTemplateBindingCS = null;


        try {
            // InternalEssentialOCL.g:5018:2: (iv_ruleTemplateBindingCS= ruleTemplateBindingCS EOF )
            // InternalEssentialOCL.g:5019:2: iv_ruleTemplateBindingCS= ruleTemplateBindingCS EOF
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
    // InternalEssentialOCL.g:5026:1: ruleTemplateBindingCS returns [EObject current=null] : ( ( (lv_ownedSubstitutions_0_0= ruleTemplateParameterSubstitutionCS ) ) (otherlv_1= ',' ( (lv_ownedSubstitutions_2_0= ruleTemplateParameterSubstitutionCS ) ) )* ( (lv_ownedMultiplicity_3_0= ruleMultiplicityCS ) )? ) ;
    public final EObject ruleTemplateBindingCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject lv_ownedSubstitutions_0_0 = null;

        EObject lv_ownedSubstitutions_2_0 = null;

        EObject lv_ownedMultiplicity_3_0 = null;


         enterRule();

        try {
            // InternalEssentialOCL.g:5029:28: ( ( ( (lv_ownedSubstitutions_0_0= ruleTemplateParameterSubstitutionCS ) ) (otherlv_1= ',' ( (lv_ownedSubstitutions_2_0= ruleTemplateParameterSubstitutionCS ) ) )* ( (lv_ownedMultiplicity_3_0= ruleMultiplicityCS ) )? ) )
            // InternalEssentialOCL.g:5030:1: ( ( (lv_ownedSubstitutions_0_0= ruleTemplateParameterSubstitutionCS ) ) (otherlv_1= ',' ( (lv_ownedSubstitutions_2_0= ruleTemplateParameterSubstitutionCS ) ) )* ( (lv_ownedMultiplicity_3_0= ruleMultiplicityCS ) )? )
            {
            // InternalEssentialOCL.g:5030:1: ( ( (lv_ownedSubstitutions_0_0= ruleTemplateParameterSubstitutionCS ) ) (otherlv_1= ',' ( (lv_ownedSubstitutions_2_0= ruleTemplateParameterSubstitutionCS ) ) )* ( (lv_ownedMultiplicity_3_0= ruleMultiplicityCS ) )? )
            // InternalEssentialOCL.g:5030:2: ( (lv_ownedSubstitutions_0_0= ruleTemplateParameterSubstitutionCS ) ) (otherlv_1= ',' ( (lv_ownedSubstitutions_2_0= ruleTemplateParameterSubstitutionCS ) ) )* ( (lv_ownedMultiplicity_3_0= ruleMultiplicityCS ) )?
            {
            // InternalEssentialOCL.g:5030:2: ( (lv_ownedSubstitutions_0_0= ruleTemplateParameterSubstitutionCS ) )
            // InternalEssentialOCL.g:5031:1: (lv_ownedSubstitutions_0_0= ruleTemplateParameterSubstitutionCS )
            {
            // InternalEssentialOCL.g:5031:1: (lv_ownedSubstitutions_0_0= ruleTemplateParameterSubstitutionCS )
            // InternalEssentialOCL.g:5032:3: lv_ownedSubstitutions_0_0= ruleTemplateParameterSubstitutionCS
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getTemplateBindingCSAccess().getOwnedSubstitutionsTemplateParameterSubstitutionCSParserRuleCall_0_0());

            }
            pushFollow(FollowSets000.FOLLOW_54);
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

            // InternalEssentialOCL.g:5048:2: (otherlv_1= ',' ( (lv_ownedSubstitutions_2_0= ruleTemplateParameterSubstitutionCS ) ) )*
            loop80:
            do {
                int alt80=2;
                int LA80_0 = input.LA(1);

                if ( (LA80_0==57) ) {
                    alt80=1;
                }


                switch (alt80) {
            	case 1 :
            	    // InternalEssentialOCL.g:5048:4: otherlv_1= ',' ( (lv_ownedSubstitutions_2_0= ruleTemplateParameterSubstitutionCS ) )
            	    {
            	    otherlv_1=(Token)match(input,57,FollowSets000.FOLLOW_55); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_1, grammarAccess.getTemplateBindingCSAccess().getCommaKeyword_1_0());

            	    }
            	    // InternalEssentialOCL.g:5052:1: ( (lv_ownedSubstitutions_2_0= ruleTemplateParameterSubstitutionCS ) )
            	    // InternalEssentialOCL.g:5053:1: (lv_ownedSubstitutions_2_0= ruleTemplateParameterSubstitutionCS )
            	    {
            	    // InternalEssentialOCL.g:5053:1: (lv_ownedSubstitutions_2_0= ruleTemplateParameterSubstitutionCS )
            	    // InternalEssentialOCL.g:5054:3: lv_ownedSubstitutions_2_0= ruleTemplateParameterSubstitutionCS
            	    {
            	    if ( state.backtracking==0 ) {

            	      	        newCompositeNode(grammarAccess.getTemplateBindingCSAccess().getOwnedSubstitutionsTemplateParameterSubstitutionCSParserRuleCall_1_1_0());

            	    }
            	    pushFollow(FollowSets000.FOLLOW_54);
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
            	    break loop80;
                }
            } while (true);

            // InternalEssentialOCL.g:5070:4: ( (lv_ownedMultiplicity_3_0= ruleMultiplicityCS ) )?
            int alt81=2;
            int LA81_0 = input.LA(1);

            if ( (LA81_0==71) ) {
                alt81=1;
            }
            switch (alt81) {
                case 1 :
                    // InternalEssentialOCL.g:5071:1: (lv_ownedMultiplicity_3_0= ruleMultiplicityCS )
                    {
                    // InternalEssentialOCL.g:5071:1: (lv_ownedMultiplicity_3_0= ruleMultiplicityCS )
                    // InternalEssentialOCL.g:5072:3: lv_ownedMultiplicity_3_0= ruleMultiplicityCS
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
    // InternalEssentialOCL.g:5096:1: entryRuleTemplateParameterSubstitutionCS returns [EObject current=null] : iv_ruleTemplateParameterSubstitutionCS= ruleTemplateParameterSubstitutionCS EOF ;
    public final EObject entryRuleTemplateParameterSubstitutionCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTemplateParameterSubstitutionCS = null;


        try {
            // InternalEssentialOCL.g:5097:2: (iv_ruleTemplateParameterSubstitutionCS= ruleTemplateParameterSubstitutionCS EOF )
            // InternalEssentialOCL.g:5098:2: iv_ruleTemplateParameterSubstitutionCS= ruleTemplateParameterSubstitutionCS EOF
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
    // InternalEssentialOCL.g:5105:1: ruleTemplateParameterSubstitutionCS returns [EObject current=null] : ( (lv_ownedActualParameter_0_0= ruleTypeRefCS ) ) ;
    public final EObject ruleTemplateParameterSubstitutionCS() throws RecognitionException {
        EObject current = null;

        EObject lv_ownedActualParameter_0_0 = null;


         enterRule();

        try {
            // InternalEssentialOCL.g:5108:28: ( ( (lv_ownedActualParameter_0_0= ruleTypeRefCS ) ) )
            // InternalEssentialOCL.g:5109:1: ( (lv_ownedActualParameter_0_0= ruleTypeRefCS ) )
            {
            // InternalEssentialOCL.g:5109:1: ( (lv_ownedActualParameter_0_0= ruleTypeRefCS ) )
            // InternalEssentialOCL.g:5110:1: (lv_ownedActualParameter_0_0= ruleTypeRefCS )
            {
            // InternalEssentialOCL.g:5110:1: (lv_ownedActualParameter_0_0= ruleTypeRefCS )
            // InternalEssentialOCL.g:5111:3: lv_ownedActualParameter_0_0= ruleTypeRefCS
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
    // InternalEssentialOCL.g:5137:1: entryRuleTypeParameterCS returns [EObject current=null] : iv_ruleTypeParameterCS= ruleTypeParameterCS EOF ;
    public final EObject entryRuleTypeParameterCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTypeParameterCS = null;


        try {
            // InternalEssentialOCL.g:5138:2: (iv_ruleTypeParameterCS= ruleTypeParameterCS EOF )
            // InternalEssentialOCL.g:5139:2: iv_ruleTypeParameterCS= ruleTypeParameterCS EOF
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
    // InternalEssentialOCL.g:5146:1: ruleTypeParameterCS returns [EObject current=null] : ( ( (lv_name_0_0= ruleUnrestrictedName ) ) (otherlv_1= 'extends' ( (lv_ownedExtends_2_0= ruleTypedRefCS ) ) (otherlv_3= '&&' ( (lv_ownedExtends_4_0= ruleTypedRefCS ) ) )* )? ) ;
    public final EObject ruleTypeParameterCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        AntlrDatatypeRuleToken lv_name_0_0 = null;

        EObject lv_ownedExtends_2_0 = null;

        EObject lv_ownedExtends_4_0 = null;


         enterRule();

        try {
            // InternalEssentialOCL.g:5149:28: ( ( ( (lv_name_0_0= ruleUnrestrictedName ) ) (otherlv_1= 'extends' ( (lv_ownedExtends_2_0= ruleTypedRefCS ) ) (otherlv_3= '&&' ( (lv_ownedExtends_4_0= ruleTypedRefCS ) ) )* )? ) )
            // InternalEssentialOCL.g:5150:1: ( ( (lv_name_0_0= ruleUnrestrictedName ) ) (otherlv_1= 'extends' ( (lv_ownedExtends_2_0= ruleTypedRefCS ) ) (otherlv_3= '&&' ( (lv_ownedExtends_4_0= ruleTypedRefCS ) ) )* )? )
            {
            // InternalEssentialOCL.g:5150:1: ( ( (lv_name_0_0= ruleUnrestrictedName ) ) (otherlv_1= 'extends' ( (lv_ownedExtends_2_0= ruleTypedRefCS ) ) (otherlv_3= '&&' ( (lv_ownedExtends_4_0= ruleTypedRefCS ) ) )* )? )
            // InternalEssentialOCL.g:5150:2: ( (lv_name_0_0= ruleUnrestrictedName ) ) (otherlv_1= 'extends' ( (lv_ownedExtends_2_0= ruleTypedRefCS ) ) (otherlv_3= '&&' ( (lv_ownedExtends_4_0= ruleTypedRefCS ) ) )* )?
            {
            // InternalEssentialOCL.g:5150:2: ( (lv_name_0_0= ruleUnrestrictedName ) )
            // InternalEssentialOCL.g:5151:1: (lv_name_0_0= ruleUnrestrictedName )
            {
            // InternalEssentialOCL.g:5151:1: (lv_name_0_0= ruleUnrestrictedName )
            // InternalEssentialOCL.g:5152:3: lv_name_0_0= ruleUnrestrictedName
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getTypeParameterCSAccess().getNameUnrestrictedNameParserRuleCall_0_0());

            }
            pushFollow(FollowSets000.FOLLOW_56);
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
                      		"org.eclipse.ocl.xtext.essentialocl.EssentialOCL.UnrestrictedName");
              	        afterParserOrEnumRuleCall();

            }

            }


            }

            // InternalEssentialOCL.g:5168:2: (otherlv_1= 'extends' ( (lv_ownedExtends_2_0= ruleTypedRefCS ) ) (otherlv_3= '&&' ( (lv_ownedExtends_4_0= ruleTypedRefCS ) ) )* )?
            int alt83=2;
            int LA83_0 = input.LA(1);

            if ( (LA83_0==87) ) {
                alt83=1;
            }
            switch (alt83) {
                case 1 :
                    // InternalEssentialOCL.g:5168:4: otherlv_1= 'extends' ( (lv_ownedExtends_2_0= ruleTypedRefCS ) ) (otherlv_3= '&&' ( (lv_ownedExtends_4_0= ruleTypedRefCS ) ) )*
                    {
                    otherlv_1=(Token)match(input,87,FollowSets000.FOLLOW_10); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_1, grammarAccess.getTypeParameterCSAccess().getExtendsKeyword_1_0());

                    }
                    // InternalEssentialOCL.g:5172:1: ( (lv_ownedExtends_2_0= ruleTypedRefCS ) )
                    // InternalEssentialOCL.g:5173:1: (lv_ownedExtends_2_0= ruleTypedRefCS )
                    {
                    // InternalEssentialOCL.g:5173:1: (lv_ownedExtends_2_0= ruleTypedRefCS )
                    // InternalEssentialOCL.g:5174:3: lv_ownedExtends_2_0= ruleTypedRefCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getTypeParameterCSAccess().getOwnedExtendsTypedRefCSParserRuleCall_1_1_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_57);
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
                              		"org.eclipse.ocl.xtext.base.Base.TypedRefCS");
                      	        afterParserOrEnumRuleCall();

                    }

                    }


                    }

                    // InternalEssentialOCL.g:5190:2: (otherlv_3= '&&' ( (lv_ownedExtends_4_0= ruleTypedRefCS ) ) )*
                    loop82:
                    do {
                        int alt82=2;
                        int LA82_0 = input.LA(1);

                        if ( (LA82_0==88) ) {
                            alt82=1;
                        }


                        switch (alt82) {
                    	case 1 :
                    	    // InternalEssentialOCL.g:5190:4: otherlv_3= '&&' ( (lv_ownedExtends_4_0= ruleTypedRefCS ) )
                    	    {
                    	    otherlv_3=(Token)match(input,88,FollowSets000.FOLLOW_10); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_3, grammarAccess.getTypeParameterCSAccess().getAmpersandAmpersandKeyword_1_2_0());

                    	    }
                    	    // InternalEssentialOCL.g:5194:1: ( (lv_ownedExtends_4_0= ruleTypedRefCS ) )
                    	    // InternalEssentialOCL.g:5195:1: (lv_ownedExtends_4_0= ruleTypedRefCS )
                    	    {
                    	    // InternalEssentialOCL.g:5195:1: (lv_ownedExtends_4_0= ruleTypedRefCS )
                    	    // InternalEssentialOCL.g:5196:3: lv_ownedExtends_4_0= ruleTypedRefCS
                    	    {
                    	    if ( state.backtracking==0 ) {

                    	      	        newCompositeNode(grammarAccess.getTypeParameterCSAccess().getOwnedExtendsTypedRefCSParserRuleCall_1_2_1_0());

                    	    }
                    	    pushFollow(FollowSets000.FOLLOW_57);
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
                    	              		"org.eclipse.ocl.xtext.base.Base.TypedRefCS");
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
    // InternalEssentialOCL.g:5220:1: entryRuleTypeRefCS returns [EObject current=null] : iv_ruleTypeRefCS= ruleTypeRefCS EOF ;
    public final EObject entryRuleTypeRefCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTypeRefCS = null;


        try {
            // InternalEssentialOCL.g:5221:2: (iv_ruleTypeRefCS= ruleTypeRefCS EOF )
            // InternalEssentialOCL.g:5222:2: iv_ruleTypeRefCS= ruleTypeRefCS EOF
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
    // InternalEssentialOCL.g:5229:1: ruleTypeRefCS returns [EObject current=null] : (this_TypedRefCS_0= ruleTypedRefCS | this_WildcardTypeRefCS_1= ruleWildcardTypeRefCS ) ;
    public final EObject ruleTypeRefCS() throws RecognitionException {
        EObject current = null;

        EObject this_TypedRefCS_0 = null;

        EObject this_WildcardTypeRefCS_1 = null;


         enterRule();

        try {
            // InternalEssentialOCL.g:5232:28: ( (this_TypedRefCS_0= ruleTypedRefCS | this_WildcardTypeRefCS_1= ruleWildcardTypeRefCS ) )
            // InternalEssentialOCL.g:5233:1: (this_TypedRefCS_0= ruleTypedRefCS | this_WildcardTypeRefCS_1= ruleWildcardTypeRefCS )
            {
            // InternalEssentialOCL.g:5233:1: (this_TypedRefCS_0= ruleTypedRefCS | this_WildcardTypeRefCS_1= ruleWildcardTypeRefCS )
            int alt84=2;
            int LA84_0 = input.LA(1);

            if ( ((LA84_0>=RULE_SIMPLE_ID && LA84_0<=RULE_ESCAPED_ID)) ) {
                alt84=1;
            }
            else if ( (LA84_0==85) ) {
                alt84=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 84, 0, input);

                throw nvae;
            }
            switch (alt84) {
                case 1 :
                    // InternalEssentialOCL.g:5234:2: this_TypedRefCS_0= ruleTypedRefCS
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
                    // InternalEssentialOCL.g:5247:2: this_WildcardTypeRefCS_1= ruleWildcardTypeRefCS
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


    // $ANTLR start "entryRuleTypedRefCS"
    // InternalEssentialOCL.g:5266:1: entryRuleTypedRefCS returns [EObject current=null] : iv_ruleTypedRefCS= ruleTypedRefCS EOF ;
    public final EObject entryRuleTypedRefCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTypedRefCS = null;


        try {
            // InternalEssentialOCL.g:5267:2: (iv_ruleTypedRefCS= ruleTypedRefCS EOF )
            // InternalEssentialOCL.g:5268:2: iv_ruleTypedRefCS= ruleTypedRefCS EOF
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
    // InternalEssentialOCL.g:5275:1: ruleTypedRefCS returns [EObject current=null] : this_TypedTypeRefCS_0= ruleTypedTypeRefCS ;
    public final EObject ruleTypedRefCS() throws RecognitionException {
        EObject current = null;

        EObject this_TypedTypeRefCS_0 = null;


         enterRule();

        try {
            // InternalEssentialOCL.g:5278:28: (this_TypedTypeRefCS_0= ruleTypedTypeRefCS )
            // InternalEssentialOCL.g:5280:2: this_TypedTypeRefCS_0= ruleTypedTypeRefCS
            {
            if ( state.backtracking==0 ) {

              	  /* */

            }
            if ( state.backtracking==0 ) {

                      newCompositeNode(grammarAccess.getTypedRefCSAccess().getTypedTypeRefCSParserRuleCall());

            }
            pushFollow(FollowSets000.FOLLOW_2);
            this_TypedTypeRefCS_0=ruleTypedTypeRefCS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

                      current = this_TypedTypeRefCS_0;
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
    // $ANTLR end "ruleTypedRefCS"


    // $ANTLR start "entryRuleTypedTypeRefCS"
    // InternalEssentialOCL.g:5299:1: entryRuleTypedTypeRefCS returns [EObject current=null] : iv_ruleTypedTypeRefCS= ruleTypedTypeRefCS EOF ;
    public final EObject entryRuleTypedTypeRefCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTypedTypeRefCS = null;


        try {
            // InternalEssentialOCL.g:5300:2: (iv_ruleTypedTypeRefCS= ruleTypedTypeRefCS EOF )
            // InternalEssentialOCL.g:5301:2: iv_ruleTypedTypeRefCS= ruleTypedTypeRefCS EOF
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
    // InternalEssentialOCL.g:5308:1: ruleTypedTypeRefCS returns [EObject current=null] : ( ( (lv_ownedPathName_0_0= rulePathNameCS ) ) (otherlv_1= '(' ( (lv_ownedBinding_2_0= ruleTemplateBindingCS ) ) otherlv_3= ')' )? ) ;
    public final EObject ruleTypedTypeRefCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_ownedPathName_0_0 = null;

        EObject lv_ownedBinding_2_0 = null;


         enterRule();

        try {
            // InternalEssentialOCL.g:5311:28: ( ( ( (lv_ownedPathName_0_0= rulePathNameCS ) ) (otherlv_1= '(' ( (lv_ownedBinding_2_0= ruleTemplateBindingCS ) ) otherlv_3= ')' )? ) )
            // InternalEssentialOCL.g:5312:1: ( ( (lv_ownedPathName_0_0= rulePathNameCS ) ) (otherlv_1= '(' ( (lv_ownedBinding_2_0= ruleTemplateBindingCS ) ) otherlv_3= ')' )? )
            {
            // InternalEssentialOCL.g:5312:1: ( ( (lv_ownedPathName_0_0= rulePathNameCS ) ) (otherlv_1= '(' ( (lv_ownedBinding_2_0= ruleTemplateBindingCS ) ) otherlv_3= ')' )? )
            // InternalEssentialOCL.g:5312:2: ( (lv_ownedPathName_0_0= rulePathNameCS ) ) (otherlv_1= '(' ( (lv_ownedBinding_2_0= ruleTemplateBindingCS ) ) otherlv_3= ')' )?
            {
            // InternalEssentialOCL.g:5312:2: ( (lv_ownedPathName_0_0= rulePathNameCS ) )
            // InternalEssentialOCL.g:5313:1: (lv_ownedPathName_0_0= rulePathNameCS )
            {
            // InternalEssentialOCL.g:5313:1: (lv_ownedPathName_0_0= rulePathNameCS )
            // InternalEssentialOCL.g:5314:3: lv_ownedPathName_0_0= rulePathNameCS
            {
            if ( state.backtracking==0 ) {

              	        newCompositeNode(grammarAccess.getTypedTypeRefCSAccess().getOwnedPathNamePathNameCSParserRuleCall_0_0());

            }
            pushFollow(FollowSets000.FOLLOW_3);
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

            // InternalEssentialOCL.g:5330:2: (otherlv_1= '(' ( (lv_ownedBinding_2_0= ruleTemplateBindingCS ) ) otherlv_3= ')' )?
            int alt85=2;
            int LA85_0 = input.LA(1);

            if ( (LA85_0==55) ) {
                alt85=1;
            }
            switch (alt85) {
                case 1 :
                    // InternalEssentialOCL.g:5330:4: otherlv_1= '(' ( (lv_ownedBinding_2_0= ruleTemplateBindingCS ) ) otherlv_3= ')'
                    {
                    otherlv_1=(Token)match(input,55,FollowSets000.FOLLOW_55); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_1, grammarAccess.getTypedTypeRefCSAccess().getLeftParenthesisKeyword_1_0());

                    }
                    // InternalEssentialOCL.g:5334:1: ( (lv_ownedBinding_2_0= ruleTemplateBindingCS ) )
                    // InternalEssentialOCL.g:5335:1: (lv_ownedBinding_2_0= ruleTemplateBindingCS )
                    {
                    // InternalEssentialOCL.g:5335:1: (lv_ownedBinding_2_0= ruleTemplateBindingCS )
                    // InternalEssentialOCL.g:5336:3: lv_ownedBinding_2_0= ruleTemplateBindingCS
                    {
                    if ( state.backtracking==0 ) {

                      	        newCompositeNode(grammarAccess.getTypedTypeRefCSAccess().getOwnedBindingTemplateBindingCSParserRuleCall_1_1_0());

                    }
                    pushFollow(FollowSets000.FOLLOW_6);
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

                    otherlv_3=(Token)match(input,56,FollowSets000.FOLLOW_2); if (state.failed) return current;
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
    // InternalEssentialOCL.g:5364:1: entryRuleWildcardTypeRefCS returns [EObject current=null] : iv_ruleWildcardTypeRefCS= ruleWildcardTypeRefCS EOF ;
    public final EObject entryRuleWildcardTypeRefCS() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleWildcardTypeRefCS = null;


        try {
            // InternalEssentialOCL.g:5365:2: (iv_ruleWildcardTypeRefCS= ruleWildcardTypeRefCS EOF )
            // InternalEssentialOCL.g:5366:2: iv_ruleWildcardTypeRefCS= ruleWildcardTypeRefCS EOF
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
    // InternalEssentialOCL.g:5373:1: ruleWildcardTypeRefCS returns [EObject current=null] : ( () otherlv_1= '?' (otherlv_2= 'extends' ( (lv_ownedExtends_3_0= ruleTypedRefCS ) ) )? ) ;
    public final EObject ruleWildcardTypeRefCS() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        EObject lv_ownedExtends_3_0 = null;


         enterRule();

        try {
            // InternalEssentialOCL.g:5376:28: ( ( () otherlv_1= '?' (otherlv_2= 'extends' ( (lv_ownedExtends_3_0= ruleTypedRefCS ) ) )? ) )
            // InternalEssentialOCL.g:5377:1: ( () otherlv_1= '?' (otherlv_2= 'extends' ( (lv_ownedExtends_3_0= ruleTypedRefCS ) ) )? )
            {
            // InternalEssentialOCL.g:5377:1: ( () otherlv_1= '?' (otherlv_2= 'extends' ( (lv_ownedExtends_3_0= ruleTypedRefCS ) ) )? )
            // InternalEssentialOCL.g:5377:2: () otherlv_1= '?' (otherlv_2= 'extends' ( (lv_ownedExtends_3_0= ruleTypedRefCS ) ) )?
            {
            // InternalEssentialOCL.g:5377:2: ()
            // InternalEssentialOCL.g:5378:2:
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

            otherlv_1=(Token)match(input,85,FollowSets000.FOLLOW_56); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getWildcardTypeRefCSAccess().getQuestionMarkKeyword_1());

            }
            // InternalEssentialOCL.g:5390:1: (otherlv_2= 'extends' ( (lv_ownedExtends_3_0= ruleTypedRefCS ) ) )?
            int alt86=2;
            int LA86_0 = input.LA(1);

            if ( (LA86_0==87) ) {
                alt86=1;
            }
            switch (alt86) {
                case 1 :
                    // InternalEssentialOCL.g:5390:3: otherlv_2= 'extends' ( (lv_ownedExtends_3_0= ruleTypedRefCS ) )
                    {
                    otherlv_2=(Token)match(input,87,FollowSets000.FOLLOW_10); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_2, grammarAccess.getWildcardTypeRefCSAccess().getExtendsKeyword_2_0());

                    }
                    // InternalEssentialOCL.g:5394:1: ( (lv_ownedExtends_3_0= ruleTypedRefCS ) )
                    // InternalEssentialOCL.g:5395:1: (lv_ownedExtends_3_0= ruleTypedRefCS )
                    {
                    // InternalEssentialOCL.g:5395:1: (lv_ownedExtends_3_0= ruleTypedRefCS )
                    // InternalEssentialOCL.g:5396:3: lv_ownedExtends_3_0= ruleTypedRefCS
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
                              		"org.eclipse.ocl.xtext.base.Base.TypedRefCS");
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
    // InternalEssentialOCL.g:5420:1: entryRuleID returns [String current=null] : iv_ruleID= ruleID EOF ;
    public final String entryRuleID() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleID = null;


        try {
            // InternalEssentialOCL.g:5421:2: (iv_ruleID= ruleID EOF )
            // InternalEssentialOCL.g:5422:2: iv_ruleID= ruleID EOF
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
    // InternalEssentialOCL.g:5429:1: ruleID returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_SIMPLE_ID_0= RULE_SIMPLE_ID | this_ESCAPED_ID_1= RULE_ESCAPED_ID ) ;
    public final AntlrDatatypeRuleToken ruleID() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_SIMPLE_ID_0=null;
        Token this_ESCAPED_ID_1=null;

         enterRule();

        try {
            // InternalEssentialOCL.g:5432:28: ( (this_SIMPLE_ID_0= RULE_SIMPLE_ID | this_ESCAPED_ID_1= RULE_ESCAPED_ID ) )
            // InternalEssentialOCL.g:5433:1: (this_SIMPLE_ID_0= RULE_SIMPLE_ID | this_ESCAPED_ID_1= RULE_ESCAPED_ID )
            {
            // InternalEssentialOCL.g:5433:1: (this_SIMPLE_ID_0= RULE_SIMPLE_ID | this_ESCAPED_ID_1= RULE_ESCAPED_ID )
            int alt87=2;
            int LA87_0 = input.LA(1);

            if ( (LA87_0==RULE_SIMPLE_ID) ) {
                alt87=1;
            }
            else if ( (LA87_0==RULE_ESCAPED_ID) ) {
                alt87=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 87, 0, input);

                throw nvae;
            }
            switch (alt87) {
                case 1 :
                    // InternalEssentialOCL.g:5433:6: this_SIMPLE_ID_0= RULE_SIMPLE_ID
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
                    // InternalEssentialOCL.g:5441:10: this_ESCAPED_ID_1= RULE_ESCAPED_ID
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
    // InternalEssentialOCL.g:5456:1: entryRuleIdentifier returns [String current=null] : iv_ruleIdentifier= ruleIdentifier EOF ;
    public final String entryRuleIdentifier() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleIdentifier = null;


        try {
            // InternalEssentialOCL.g:5457:2: (iv_ruleIdentifier= ruleIdentifier EOF )
            // InternalEssentialOCL.g:5458:2: iv_ruleIdentifier= ruleIdentifier EOF
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
    // InternalEssentialOCL.g:5465:1: ruleIdentifier returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_ID_0= ruleID ;
    public final AntlrDatatypeRuleToken ruleIdentifier() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        AntlrDatatypeRuleToken this_ID_0 = null;


         enterRule();

        try {
            // InternalEssentialOCL.g:5468:28: (this_ID_0= ruleID )
            // InternalEssentialOCL.g:5470:5: this_ID_0= ruleID
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
    // InternalEssentialOCL.g:5488:1: entryRuleLOWER returns [String current=null] : iv_ruleLOWER= ruleLOWER EOF ;
    public final String entryRuleLOWER() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleLOWER = null;


        try {
            // InternalEssentialOCL.g:5489:2: (iv_ruleLOWER= ruleLOWER EOF )
            // InternalEssentialOCL.g:5490:2: iv_ruleLOWER= ruleLOWER EOF
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
    // InternalEssentialOCL.g:5497:1: ruleLOWER returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_INT_0= RULE_INT ;
    public final AntlrDatatypeRuleToken ruleLOWER() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_INT_0=null;

         enterRule();

        try {
            // InternalEssentialOCL.g:5500:28: (this_INT_0= RULE_INT )
            // InternalEssentialOCL.g:5501:5: this_INT_0= RULE_INT
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
    // InternalEssentialOCL.g:5516:1: entryRuleNUMBER_LITERAL returns [String current=null] : iv_ruleNUMBER_LITERAL= ruleNUMBER_LITERAL EOF ;
    public final String entryRuleNUMBER_LITERAL() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleNUMBER_LITERAL = null;


        try {
            // InternalEssentialOCL.g:5517:2: (iv_ruleNUMBER_LITERAL= ruleNUMBER_LITERAL EOF )
            // InternalEssentialOCL.g:5518:2: iv_ruleNUMBER_LITERAL= ruleNUMBER_LITERAL EOF
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
    // InternalEssentialOCL.g:5525:1: ruleNUMBER_LITERAL returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_INT_0= RULE_INT ;
    public final AntlrDatatypeRuleToken ruleNUMBER_LITERAL() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_INT_0=null;

         enterRule();

        try {
            // InternalEssentialOCL.g:5528:28: (this_INT_0= RULE_INT )
            // InternalEssentialOCL.g:5529:5: this_INT_0= RULE_INT
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
    // InternalEssentialOCL.g:5544:1: entryRuleStringLiteral returns [String current=null] : iv_ruleStringLiteral= ruleStringLiteral EOF ;
    public final String entryRuleStringLiteral() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleStringLiteral = null;


        try {
            // InternalEssentialOCL.g:5545:2: (iv_ruleStringLiteral= ruleStringLiteral EOF )
            // InternalEssentialOCL.g:5546:2: iv_ruleStringLiteral= ruleStringLiteral EOF
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
    // InternalEssentialOCL.g:5553:1: ruleStringLiteral returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_SINGLE_QUOTED_STRING_0= RULE_SINGLE_QUOTED_STRING ;
    public final AntlrDatatypeRuleToken ruleStringLiteral() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_SINGLE_QUOTED_STRING_0=null;

         enterRule();

        try {
            // InternalEssentialOCL.g:5556:28: (this_SINGLE_QUOTED_STRING_0= RULE_SINGLE_QUOTED_STRING )
            // InternalEssentialOCL.g:5557:5: this_SINGLE_QUOTED_STRING_0= RULE_SINGLE_QUOTED_STRING
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
    // InternalEssentialOCL.g:5572:1: entryRuleUPPER returns [String current=null] : iv_ruleUPPER= ruleUPPER EOF ;
    public final String entryRuleUPPER() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleUPPER = null;


        try {
            // InternalEssentialOCL.g:5573:2: (iv_ruleUPPER= ruleUPPER EOF )
            // InternalEssentialOCL.g:5574:2: iv_ruleUPPER= ruleUPPER EOF
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
    // InternalEssentialOCL.g:5581:1: ruleUPPER returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_INT_0= RULE_INT | kw= '*' ) ;
    public final AntlrDatatypeRuleToken ruleUPPER() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_INT_0=null;
        Token kw=null;

         enterRule();

        try {
            // InternalEssentialOCL.g:5584:28: ( (this_INT_0= RULE_INT | kw= '*' ) )
            // InternalEssentialOCL.g:5585:1: (this_INT_0= RULE_INT | kw= '*' )
            {
            // InternalEssentialOCL.g:5585:1: (this_INT_0= RULE_INT | kw= '*' )
            int alt88=2;
            int LA88_0 = input.LA(1);

            if ( (LA88_0==RULE_INT) ) {
                alt88=1;
            }
            else if ( (LA88_0==19) ) {
                alt88=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 88, 0, input);

                throw nvae;
            }
            switch (alt88) {
                case 1 :
                    // InternalEssentialOCL.g:5585:6: this_INT_0= RULE_INT
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
                    // InternalEssentialOCL.g:5594:2: kw= '*'
                    {
                    kw=(Token)match(input,19,FollowSets000.FOLLOW_2); if (state.failed) return current;
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
    // InternalEssentialOCL.g:5607:1: entryRuleURI returns [String current=null] : iv_ruleURI= ruleURI EOF ;
    public final String entryRuleURI() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleURI = null;


        try {
            // InternalEssentialOCL.g:5608:2: (iv_ruleURI= ruleURI EOF )
            // InternalEssentialOCL.g:5609:2: iv_ruleURI= ruleURI EOF
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
    // InternalEssentialOCL.g:5616:1: ruleURI returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_SINGLE_QUOTED_STRING_0= RULE_SINGLE_QUOTED_STRING ;
    public final AntlrDatatypeRuleToken ruleURI() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_SINGLE_QUOTED_STRING_0=null;

         enterRule();

        try {
            // InternalEssentialOCL.g:5619:28: (this_SINGLE_QUOTED_STRING_0= RULE_SINGLE_QUOTED_STRING )
            // InternalEssentialOCL.g:5620:5: this_SINGLE_QUOTED_STRING_0= RULE_SINGLE_QUOTED_STRING
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

    // $ANTLR start synpred73_InternalEssentialOCL
    public final void synpred73_InternalEssentialOCL_fragment() throws RecognitionException {
        EObject this_TypeLiteralCS_1 = null;


        // InternalEssentialOCL.g:2607:2: (this_TypeLiteralCS_1= ruleTypeLiteralCS )
        // InternalEssentialOCL.g:2607:2: this_TypeLiteralCS_1= ruleTypeLiteralCS
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
    // $ANTLR end synpred73_InternalEssentialOCL

    // $ANTLR start synpred76_InternalEssentialOCL
    public final void synpred76_InternalEssentialOCL_fragment() throws RecognitionException {
        EObject this_PrefixedPrimaryExpCS_0 = null;

        AntlrDatatypeRuleToken lv_name_2_0 = null;

        EObject lv_ownedRight_3_0 = null;


        // InternalEssentialOCL.g:2703:2: ( (this_PrefixedPrimaryExpCS_0= rulePrefixedPrimaryExpCS ( () ( (lv_name_2_0= ruleBinaryOperatorName ) ) ( (lv_ownedRight_3_0= ruleExpCS ) ) )? ) )
        // InternalEssentialOCL.g:2703:2: (this_PrefixedPrimaryExpCS_0= rulePrefixedPrimaryExpCS ( () ( (lv_name_2_0= ruleBinaryOperatorName ) ) ( (lv_ownedRight_3_0= ruleExpCS ) ) )? )
        {
        // InternalEssentialOCL.g:2703:2: (this_PrefixedPrimaryExpCS_0= rulePrefixedPrimaryExpCS ( () ( (lv_name_2_0= ruleBinaryOperatorName ) ) ( (lv_ownedRight_3_0= ruleExpCS ) ) )? )
        // InternalEssentialOCL.g:2704:2: this_PrefixedPrimaryExpCS_0= rulePrefixedPrimaryExpCS ( () ( (lv_name_2_0= ruleBinaryOperatorName ) ) ( (lv_ownedRight_3_0= ruleExpCS ) ) )?
        {
        if ( state.backtracking==0 ) {

          	  /* */

        }
        pushFollow(FollowSets000.FOLLOW_27);
        this_PrefixedPrimaryExpCS_0=rulePrefixedPrimaryExpCS();

        state._fsp--;
        if (state.failed) return ;
        // InternalEssentialOCL.g:2715:1: ( () ( (lv_name_2_0= ruleBinaryOperatorName ) ) ( (lv_ownedRight_3_0= ruleExpCS ) ) )?
        int alt99=2;
        int LA99_0 = input.LA(1);

        if ( (LA99_0==16||(LA99_0>=19 && LA99_0<=39)) ) {
            alt99=1;
        }
        switch (alt99) {
            case 1 :
                // InternalEssentialOCL.g:2715:2: () ( (lv_name_2_0= ruleBinaryOperatorName ) ) ( (lv_ownedRight_3_0= ruleExpCS ) )
                {
                // InternalEssentialOCL.g:2715:2: ()
                // InternalEssentialOCL.g:2716:2:
                {
                if ( state.backtracking==0 ) {

                  	  /* */

                }

                }

                // InternalEssentialOCL.g:2724:2: ( (lv_name_2_0= ruleBinaryOperatorName ) )
                // InternalEssentialOCL.g:2725:1: (lv_name_2_0= ruleBinaryOperatorName )
                {
                // InternalEssentialOCL.g:2725:1: (lv_name_2_0= ruleBinaryOperatorName )
                // InternalEssentialOCL.g:2726:3: lv_name_2_0= ruleBinaryOperatorName
                {
                if ( state.backtracking==0 ) {

                  	        newCompositeNode(grammarAccess.getExpCSAccess().getNameBinaryOperatorNameParserRuleCall_0_1_1_0());

                }
                pushFollow(FollowSets000.FOLLOW_17);
                lv_name_2_0=ruleBinaryOperatorName();

                state._fsp--;
                if (state.failed) return ;

                }


                }

                // InternalEssentialOCL.g:2742:2: ( (lv_ownedRight_3_0= ruleExpCS ) )
                // InternalEssentialOCL.g:2743:1: (lv_ownedRight_3_0= ruleExpCS )
                {
                // InternalEssentialOCL.g:2743:1: (lv_ownedRight_3_0= ruleExpCS )
                // InternalEssentialOCL.g:2744:3: lv_ownedRight_3_0= ruleExpCS
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
    // $ANTLR end synpred76_InternalEssentialOCL

    // $ANTLR start synpred83_InternalEssentialOCL
    public final void synpred83_InternalEssentialOCL_fragment() throws RecognitionException {
        EObject this_TupleLiteralExpCS_4 = null;


        // InternalEssentialOCL.g:3005:2: (this_TupleLiteralExpCS_4= ruleTupleLiteralExpCS )
        // InternalEssentialOCL.g:3005:2: this_TupleLiteralExpCS_4= ruleTupleLiteralExpCS
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
    // $ANTLR end synpred83_InternalEssentialOCL

    // $ANTLR start synpred84_InternalEssentialOCL
    public final void synpred84_InternalEssentialOCL_fragment() throws RecognitionException {
        EObject this_MapLiteralExpCS_5 = null;


        // InternalEssentialOCL.g:3018:2: (this_MapLiteralExpCS_5= ruleMapLiteralExpCS )
        // InternalEssentialOCL.g:3018:2: this_MapLiteralExpCS_5= ruleMapLiteralExpCS
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
    // $ANTLR end synpred84_InternalEssentialOCL

    // $ANTLR start synpred85_InternalEssentialOCL
    public final void synpred85_InternalEssentialOCL_fragment() throws RecognitionException {
        EObject this_CollectionLiteralExpCS_6 = null;


        // InternalEssentialOCL.g:3031:2: (this_CollectionLiteralExpCS_6= ruleCollectionLiteralExpCS )
        // InternalEssentialOCL.g:3031:2: this_CollectionLiteralExpCS_6= ruleCollectionLiteralExpCS
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
    // $ANTLR end synpred85_InternalEssentialOCL

    // $ANTLR start synpred87_InternalEssentialOCL
    public final void synpred87_InternalEssentialOCL_fragment() throws RecognitionException {
        EObject this_TypeLiteralExpCS_8 = null;


        // InternalEssentialOCL.g:3057:2: (this_TypeLiteralExpCS_8= ruleTypeLiteralExpCS )
        // InternalEssentialOCL.g:3057:2: this_TypeLiteralExpCS_8= ruleTypeLiteralExpCS
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
    // $ANTLR end synpred87_InternalEssentialOCL

    // $ANTLR start synpred100_InternalEssentialOCL
    public final void synpred100_InternalEssentialOCL_fragment() throws RecognitionException {
        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_ownedCoIterator_2_0 = null;

        EObject lv_ownedInitExpression_4_0 = null;


        // InternalEssentialOCL.g:3486:3: ( (otherlv_1= '<-' ( (lv_ownedCoIterator_2_0= ruleCoIteratorVariableCS ) ) (otherlv_3= '=' ( (lv_ownedInitExpression_4_0= ruleExpCS ) ) )? ) )
        // InternalEssentialOCL.g:3486:3: (otherlv_1= '<-' ( (lv_ownedCoIterator_2_0= ruleCoIteratorVariableCS ) ) (otherlv_3= '=' ( (lv_ownedInitExpression_4_0= ruleExpCS ) ) )? )
        {
        // InternalEssentialOCL.g:3486:3: (otherlv_1= '<-' ( (lv_ownedCoIterator_2_0= ruleCoIteratorVariableCS ) ) (otherlv_3= '=' ( (lv_ownedInitExpression_4_0= ruleExpCS ) ) )? )
        // InternalEssentialOCL.g:3486:5: otherlv_1= '<-' ( (lv_ownedCoIterator_2_0= ruleCoIteratorVariableCS ) ) (otherlv_3= '=' ( (lv_ownedInitExpression_4_0= ruleExpCS ) ) )?
        {
        otherlv_1=(Token)match(input,64,FollowSets000.FOLLOW_10); if (state.failed) return ;
        // InternalEssentialOCL.g:3490:1: ( (lv_ownedCoIterator_2_0= ruleCoIteratorVariableCS ) )
        // InternalEssentialOCL.g:3491:1: (lv_ownedCoIterator_2_0= ruleCoIteratorVariableCS )
        {
        // InternalEssentialOCL.g:3491:1: (lv_ownedCoIterator_2_0= ruleCoIteratorVariableCS )
        // InternalEssentialOCL.g:3492:3: lv_ownedCoIterator_2_0= ruleCoIteratorVariableCS
        {
        if ( state.backtracking==0 ) {

          	        newCompositeNode(grammarAccess.getNavigatingArgCSAccess().getOwnedCoIteratorCoIteratorVariableCSParserRuleCall_0_1_0_1_0());

        }
        pushFollow(FollowSets000.FOLLOW_39);
        lv_ownedCoIterator_2_0=ruleCoIteratorVariableCS();

        state._fsp--;
        if (state.failed) return ;

        }


        }

        // InternalEssentialOCL.g:3508:2: (otherlv_3= '=' ( (lv_ownedInitExpression_4_0= ruleExpCS ) ) )?
        int alt104=2;
        int LA104_0 = input.LA(1);

        if ( (LA104_0==26) ) {
            alt104=1;
        }
        switch (alt104) {
            case 1 :
                // InternalEssentialOCL.g:3508:4: otherlv_3= '=' ( (lv_ownedInitExpression_4_0= ruleExpCS ) )
                {
                otherlv_3=(Token)match(input,26,FollowSets000.FOLLOW_17); if (state.failed) return ;
                // InternalEssentialOCL.g:3512:1: ( (lv_ownedInitExpression_4_0= ruleExpCS ) )
                // InternalEssentialOCL.g:3513:1: (lv_ownedInitExpression_4_0= ruleExpCS )
                {
                // InternalEssentialOCL.g:3513:1: (lv_ownedInitExpression_4_0= ruleExpCS )
                // InternalEssentialOCL.g:3514:3: lv_ownedInitExpression_4_0= ruleExpCS
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
    // $ANTLR end synpred100_InternalEssentialOCL

    // $ANTLR start synpred103_InternalEssentialOCL
    public final void synpred103_InternalEssentialOCL_fragment() throws RecognitionException {
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_9=null;
        EObject lv_ownedType_6_0 = null;

        EObject lv_ownedCoIterator_8_0 = null;

        EObject lv_ownedInitExpression_10_0 = null;


        // InternalEssentialOCL.g:3531:6: ( (otherlv_5= ':' ( (lv_ownedType_6_0= ruleTypeExpCS ) ) (otherlv_7= '<-' ( (lv_ownedCoIterator_8_0= ruleCoIteratorVariableCS ) ) )? (otherlv_9= '=' ( (lv_ownedInitExpression_10_0= ruleExpCS ) ) )? ) )
        // InternalEssentialOCL.g:3531:6: (otherlv_5= ':' ( (lv_ownedType_6_0= ruleTypeExpCS ) ) (otherlv_7= '<-' ( (lv_ownedCoIterator_8_0= ruleCoIteratorVariableCS ) ) )? (otherlv_9= '=' ( (lv_ownedInitExpression_10_0= ruleExpCS ) ) )? )
        {
        // InternalEssentialOCL.g:3531:6: (otherlv_5= ':' ( (lv_ownedType_6_0= ruleTypeExpCS ) ) (otherlv_7= '<-' ( (lv_ownedCoIterator_8_0= ruleCoIteratorVariableCS ) ) )? (otherlv_9= '=' ( (lv_ownedInitExpression_10_0= ruleExpCS ) ) )? )
        // InternalEssentialOCL.g:3531:8: otherlv_5= ':' ( (lv_ownedType_6_0= ruleTypeExpCS ) ) (otherlv_7= '<-' ( (lv_ownedCoIterator_8_0= ruleCoIteratorVariableCS ) ) )? (otherlv_9= '=' ( (lv_ownedInitExpression_10_0= ruleExpCS ) ) )?
        {
        otherlv_5=(Token)match(input,58,FollowSets000.FOLLOW_4); if (state.failed) return ;
        // InternalEssentialOCL.g:3535:1: ( (lv_ownedType_6_0= ruleTypeExpCS ) )
        // InternalEssentialOCL.g:3536:1: (lv_ownedType_6_0= ruleTypeExpCS )
        {
        // InternalEssentialOCL.g:3536:1: (lv_ownedType_6_0= ruleTypeExpCS )
        // InternalEssentialOCL.g:3537:3: lv_ownedType_6_0= ruleTypeExpCS
        {
        if ( state.backtracking==0 ) {

          	        newCompositeNode(grammarAccess.getNavigatingArgCSAccess().getOwnedTypeTypeExpCSParserRuleCall_0_1_1_1_0());

        }
        pushFollow(FollowSets000.FOLLOW_40);
        lv_ownedType_6_0=ruleTypeExpCS();

        state._fsp--;
        if (state.failed) return ;

        }


        }

        // InternalEssentialOCL.g:3553:2: (otherlv_7= '<-' ( (lv_ownedCoIterator_8_0= ruleCoIteratorVariableCS ) ) )?
        int alt105=2;
        int LA105_0 = input.LA(1);

        if ( (LA105_0==64) ) {
            alt105=1;
        }
        switch (alt105) {
            case 1 :
                // InternalEssentialOCL.g:3553:4: otherlv_7= '<-' ( (lv_ownedCoIterator_8_0= ruleCoIteratorVariableCS ) )
                {
                otherlv_7=(Token)match(input,64,FollowSets000.FOLLOW_10); if (state.failed) return ;
                // InternalEssentialOCL.g:3557:1: ( (lv_ownedCoIterator_8_0= ruleCoIteratorVariableCS ) )
                // InternalEssentialOCL.g:3558:1: (lv_ownedCoIterator_8_0= ruleCoIteratorVariableCS )
                {
                // InternalEssentialOCL.g:3558:1: (lv_ownedCoIterator_8_0= ruleCoIteratorVariableCS )
                // InternalEssentialOCL.g:3559:3: lv_ownedCoIterator_8_0= ruleCoIteratorVariableCS
                {
                if ( state.backtracking==0 ) {

                  	        newCompositeNode(grammarAccess.getNavigatingArgCSAccess().getOwnedCoIteratorCoIteratorVariableCSParserRuleCall_0_1_1_2_1_0());

                }
                pushFollow(FollowSets000.FOLLOW_39);
                lv_ownedCoIterator_8_0=ruleCoIteratorVariableCS();

                state._fsp--;
                if (state.failed) return ;

                }


                }


                }
                break;

        }

        // InternalEssentialOCL.g:3575:4: (otherlv_9= '=' ( (lv_ownedInitExpression_10_0= ruleExpCS ) ) )?
        int alt106=2;
        int LA106_0 = input.LA(1);

        if ( (LA106_0==26) ) {
            alt106=1;
        }
        switch (alt106) {
            case 1 :
                // InternalEssentialOCL.g:3575:6: otherlv_9= '=' ( (lv_ownedInitExpression_10_0= ruleExpCS ) )
                {
                otherlv_9=(Token)match(input,26,FollowSets000.FOLLOW_17); if (state.failed) return ;
                // InternalEssentialOCL.g:3579:1: ( (lv_ownedInitExpression_10_0= ruleExpCS ) )
                // InternalEssentialOCL.g:3580:1: (lv_ownedInitExpression_10_0= ruleExpCS )
                {
                // InternalEssentialOCL.g:3580:1: (lv_ownedInitExpression_10_0= ruleExpCS )
                // InternalEssentialOCL.g:3581:3: lv_ownedInitExpression_10_0= ruleExpCS
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
    // $ANTLR end synpred103_InternalEssentialOCL

    // $ANTLR start synpred106_InternalEssentialOCL
    public final void synpred106_InternalEssentialOCL_fragment() throws RecognitionException {
        Token otherlv_11=null;
        Token otherlv_13=null;
        Token otherlv_15=null;
        EObject lv_ownedType_12_0 = null;

        EObject lv_ownedCoIterator_14_0 = null;

        EObject lv_ownedInitExpression_16_0 = null;


        // InternalEssentialOCL.g:3598:6: ( ( (otherlv_11= ':' ( (lv_ownedType_12_0= ruleTypeExpCS ) ) )? (otherlv_13= '<-' ( (lv_ownedCoIterator_14_0= ruleCoIteratorVariableCS ) ) )? otherlv_15= 'in' ( (lv_ownedInitExpression_16_0= ruleExpCS ) ) ) )
        // InternalEssentialOCL.g:3598:6: ( (otherlv_11= ':' ( (lv_ownedType_12_0= ruleTypeExpCS ) ) )? (otherlv_13= '<-' ( (lv_ownedCoIterator_14_0= ruleCoIteratorVariableCS ) ) )? otherlv_15= 'in' ( (lv_ownedInitExpression_16_0= ruleExpCS ) ) )
        {
        // InternalEssentialOCL.g:3598:6: ( (otherlv_11= ':' ( (lv_ownedType_12_0= ruleTypeExpCS ) ) )? (otherlv_13= '<-' ( (lv_ownedCoIterator_14_0= ruleCoIteratorVariableCS ) ) )? otherlv_15= 'in' ( (lv_ownedInitExpression_16_0= ruleExpCS ) ) )
        // InternalEssentialOCL.g:3598:7: (otherlv_11= ':' ( (lv_ownedType_12_0= ruleTypeExpCS ) ) )? (otherlv_13= '<-' ( (lv_ownedCoIterator_14_0= ruleCoIteratorVariableCS ) ) )? otherlv_15= 'in' ( (lv_ownedInitExpression_16_0= ruleExpCS ) )
        {
        // InternalEssentialOCL.g:3598:7: (otherlv_11= ':' ( (lv_ownedType_12_0= ruleTypeExpCS ) ) )?
        int alt107=2;
        int LA107_0 = input.LA(1);

        if ( (LA107_0==58) ) {
            alt107=1;
        }
        switch (alt107) {
            case 1 :
                // InternalEssentialOCL.g:3598:9: otherlv_11= ':' ( (lv_ownedType_12_0= ruleTypeExpCS ) )
                {
                otherlv_11=(Token)match(input,58,FollowSets000.FOLLOW_4); if (state.failed) return ;
                // InternalEssentialOCL.g:3602:1: ( (lv_ownedType_12_0= ruleTypeExpCS ) )
                // InternalEssentialOCL.g:3603:1: (lv_ownedType_12_0= ruleTypeExpCS )
                {
                // InternalEssentialOCL.g:3603:1: (lv_ownedType_12_0= ruleTypeExpCS )
                // InternalEssentialOCL.g:3604:3: lv_ownedType_12_0= ruleTypeExpCS
                {
                if ( state.backtracking==0 ) {

                  	        newCompositeNode(grammarAccess.getNavigatingArgCSAccess().getOwnedTypeTypeExpCSParserRuleCall_0_1_2_0_1_0());

                }
                pushFollow(FollowSets000.FOLLOW_41);
                lv_ownedType_12_0=ruleTypeExpCS();

                state._fsp--;
                if (state.failed) return ;

                }


                }


                }
                break;

        }

        // InternalEssentialOCL.g:3620:4: (otherlv_13= '<-' ( (lv_ownedCoIterator_14_0= ruleCoIteratorVariableCS ) ) )?
        int alt108=2;
        int LA108_0 = input.LA(1);

        if ( (LA108_0==64) ) {
            alt108=1;
        }
        switch (alt108) {
            case 1 :
                // InternalEssentialOCL.g:3620:6: otherlv_13= '<-' ( (lv_ownedCoIterator_14_0= ruleCoIteratorVariableCS ) )
                {
                otherlv_13=(Token)match(input,64,FollowSets000.FOLLOW_10); if (state.failed) return ;
                // InternalEssentialOCL.g:3624:1: ( (lv_ownedCoIterator_14_0= ruleCoIteratorVariableCS ) )
                // InternalEssentialOCL.g:3625:1: (lv_ownedCoIterator_14_0= ruleCoIteratorVariableCS )
                {
                // InternalEssentialOCL.g:3625:1: (lv_ownedCoIterator_14_0= ruleCoIteratorVariableCS )
                // InternalEssentialOCL.g:3626:3: lv_ownedCoIterator_14_0= ruleCoIteratorVariableCS
                {
                if ( state.backtracking==0 ) {

                  	        newCompositeNode(grammarAccess.getNavigatingArgCSAccess().getOwnedCoIteratorCoIteratorVariableCSParserRuleCall_0_1_2_1_1_0());

                }
                pushFollow(FollowSets000.FOLLOW_42);
                lv_ownedCoIterator_14_0=ruleCoIteratorVariableCS();

                state._fsp--;
                if (state.failed) return ;

                }


                }


                }
                break;

        }

        otherlv_15=(Token)match(input,73,FollowSets000.FOLLOW_17); if (state.failed) return ;
        // InternalEssentialOCL.g:3646:1: ( (lv_ownedInitExpression_16_0= ruleExpCS ) )
        // InternalEssentialOCL.g:3647:1: (lv_ownedInitExpression_16_0= ruleExpCS )
        {
        // InternalEssentialOCL.g:3647:1: (lv_ownedInitExpression_16_0= ruleExpCS )
        // InternalEssentialOCL.g:3648:3: lv_ownedInitExpression_16_0= ruleExpCS
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
    // $ANTLR end synpred106_InternalEssentialOCL

    // $ANTLR start synpred111_InternalEssentialOCL
    public final void synpred111_InternalEssentialOCL_fragment() throws RecognitionException {
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_ownedCoIterator_3_0 = null;

        EObject lv_ownedInitExpression_5_0 = null;


        // InternalEssentialOCL.g:3839:3: ( (otherlv_2= '<-' ( (lv_ownedCoIterator_3_0= ruleCoIteratorVariableCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? ) )
        // InternalEssentialOCL.g:3839:3: (otherlv_2= '<-' ( (lv_ownedCoIterator_3_0= ruleCoIteratorVariableCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? )
        {
        // InternalEssentialOCL.g:3839:3: (otherlv_2= '<-' ( (lv_ownedCoIterator_3_0= ruleCoIteratorVariableCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? )
        // InternalEssentialOCL.g:3839:5: otherlv_2= '<-' ( (lv_ownedCoIterator_3_0= ruleCoIteratorVariableCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )?
        {
        otherlv_2=(Token)match(input,64,FollowSets000.FOLLOW_10); if (state.failed) return ;
        // InternalEssentialOCL.g:3843:1: ( (lv_ownedCoIterator_3_0= ruleCoIteratorVariableCS ) )
        // InternalEssentialOCL.g:3844:1: (lv_ownedCoIterator_3_0= ruleCoIteratorVariableCS )
        {
        // InternalEssentialOCL.g:3844:1: (lv_ownedCoIterator_3_0= ruleCoIteratorVariableCS )
        // InternalEssentialOCL.g:3845:3: lv_ownedCoIterator_3_0= ruleCoIteratorVariableCS
        {
        if ( state.backtracking==0 ) {

          	        newCompositeNode(grammarAccess.getNavigatingCommaArgCSAccess().getOwnedCoIteratorCoIteratorVariableCSParserRuleCall_2_0_1_0());

        }
        pushFollow(FollowSets000.FOLLOW_39);
        lv_ownedCoIterator_3_0=ruleCoIteratorVariableCS();

        state._fsp--;
        if (state.failed) return ;

        }


        }

        // InternalEssentialOCL.g:3861:2: (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )?
        int alt116=2;
        int LA116_0 = input.LA(1);

        if ( (LA116_0==26) ) {
            alt116=1;
        }
        switch (alt116) {
            case 1 :
                // InternalEssentialOCL.g:3861:4: otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) )
                {
                otherlv_4=(Token)match(input,26,FollowSets000.FOLLOW_17); if (state.failed) return ;
                // InternalEssentialOCL.g:3865:1: ( (lv_ownedInitExpression_5_0= ruleExpCS ) )
                // InternalEssentialOCL.g:3866:1: (lv_ownedInitExpression_5_0= ruleExpCS )
                {
                // InternalEssentialOCL.g:3866:1: (lv_ownedInitExpression_5_0= ruleExpCS )
                // InternalEssentialOCL.g:3867:3: lv_ownedInitExpression_5_0= ruleExpCS
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
    // $ANTLR end synpred111_InternalEssentialOCL

    // $ANTLR start synpred114_InternalEssentialOCL
    public final void synpred114_InternalEssentialOCL_fragment() throws RecognitionException {
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        EObject lv_ownedType_7_0 = null;

        EObject lv_ownedCoIterator_9_0 = null;

        EObject lv_ownedInitExpression_11_0 = null;


        // InternalEssentialOCL.g:3884:6: ( (otherlv_6= ':' ( (lv_ownedType_7_0= ruleTypeExpCS ) ) (otherlv_8= '<-' ( (lv_ownedCoIterator_9_0= ruleCoIteratorVariableCS ) ) )? (otherlv_10= '=' ( (lv_ownedInitExpression_11_0= ruleExpCS ) ) )? ) )
        // InternalEssentialOCL.g:3884:6: (otherlv_6= ':' ( (lv_ownedType_7_0= ruleTypeExpCS ) ) (otherlv_8= '<-' ( (lv_ownedCoIterator_9_0= ruleCoIteratorVariableCS ) ) )? (otherlv_10= '=' ( (lv_ownedInitExpression_11_0= ruleExpCS ) ) )? )
        {
        // InternalEssentialOCL.g:3884:6: (otherlv_6= ':' ( (lv_ownedType_7_0= ruleTypeExpCS ) ) (otherlv_8= '<-' ( (lv_ownedCoIterator_9_0= ruleCoIteratorVariableCS ) ) )? (otherlv_10= '=' ( (lv_ownedInitExpression_11_0= ruleExpCS ) ) )? )
        // InternalEssentialOCL.g:3884:8: otherlv_6= ':' ( (lv_ownedType_7_0= ruleTypeExpCS ) ) (otherlv_8= '<-' ( (lv_ownedCoIterator_9_0= ruleCoIteratorVariableCS ) ) )? (otherlv_10= '=' ( (lv_ownedInitExpression_11_0= ruleExpCS ) ) )?
        {
        otherlv_6=(Token)match(input,58,FollowSets000.FOLLOW_4); if (state.failed) return ;
        // InternalEssentialOCL.g:3888:1: ( (lv_ownedType_7_0= ruleTypeExpCS ) )
        // InternalEssentialOCL.g:3889:1: (lv_ownedType_7_0= ruleTypeExpCS )
        {
        // InternalEssentialOCL.g:3889:1: (lv_ownedType_7_0= ruleTypeExpCS )
        // InternalEssentialOCL.g:3890:3: lv_ownedType_7_0= ruleTypeExpCS
        {
        if ( state.backtracking==0 ) {

          	        newCompositeNode(grammarAccess.getNavigatingCommaArgCSAccess().getOwnedTypeTypeExpCSParserRuleCall_2_1_1_0());

        }
        pushFollow(FollowSets000.FOLLOW_40);
        lv_ownedType_7_0=ruleTypeExpCS();

        state._fsp--;
        if (state.failed) return ;

        }


        }

        // InternalEssentialOCL.g:3906:2: (otherlv_8= '<-' ( (lv_ownedCoIterator_9_0= ruleCoIteratorVariableCS ) ) )?
        int alt117=2;
        int LA117_0 = input.LA(1);

        if ( (LA117_0==64) ) {
            alt117=1;
        }
        switch (alt117) {
            case 1 :
                // InternalEssentialOCL.g:3906:4: otherlv_8= '<-' ( (lv_ownedCoIterator_9_0= ruleCoIteratorVariableCS ) )
                {
                otherlv_8=(Token)match(input,64,FollowSets000.FOLLOW_10); if (state.failed) return ;
                // InternalEssentialOCL.g:3910:1: ( (lv_ownedCoIterator_9_0= ruleCoIteratorVariableCS ) )
                // InternalEssentialOCL.g:3911:1: (lv_ownedCoIterator_9_0= ruleCoIteratorVariableCS )
                {
                // InternalEssentialOCL.g:3911:1: (lv_ownedCoIterator_9_0= ruleCoIteratorVariableCS )
                // InternalEssentialOCL.g:3912:3: lv_ownedCoIterator_9_0= ruleCoIteratorVariableCS
                {
                if ( state.backtracking==0 ) {

                  	        newCompositeNode(grammarAccess.getNavigatingCommaArgCSAccess().getOwnedCoIteratorCoIteratorVariableCSParserRuleCall_2_1_2_1_0());

                }
                pushFollow(FollowSets000.FOLLOW_39);
                lv_ownedCoIterator_9_0=ruleCoIteratorVariableCS();

                state._fsp--;
                if (state.failed) return ;

                }


                }


                }
                break;

        }

        // InternalEssentialOCL.g:3928:4: (otherlv_10= '=' ( (lv_ownedInitExpression_11_0= ruleExpCS ) ) )?
        int alt118=2;
        int LA118_0 = input.LA(1);

        if ( (LA118_0==26) ) {
            alt118=1;
        }
        switch (alt118) {
            case 1 :
                // InternalEssentialOCL.g:3928:6: otherlv_10= '=' ( (lv_ownedInitExpression_11_0= ruleExpCS ) )
                {
                otherlv_10=(Token)match(input,26,FollowSets000.FOLLOW_17); if (state.failed) return ;
                // InternalEssentialOCL.g:3932:1: ( (lv_ownedInitExpression_11_0= ruleExpCS ) )
                // InternalEssentialOCL.g:3933:1: (lv_ownedInitExpression_11_0= ruleExpCS )
                {
                // InternalEssentialOCL.g:3933:1: (lv_ownedInitExpression_11_0= ruleExpCS )
                // InternalEssentialOCL.g:3934:3: lv_ownedInitExpression_11_0= ruleExpCS
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
    // $ANTLR end synpred114_InternalEssentialOCL

    // $ANTLR start synpred117_InternalEssentialOCL
    public final void synpred117_InternalEssentialOCL_fragment() throws RecognitionException {
        Token otherlv_12=null;
        Token otherlv_14=null;
        Token otherlv_16=null;
        EObject lv_ownedType_13_0 = null;

        EObject lv_ownedCoIterator_15_0 = null;

        EObject lv_ownedInitExpression_17_0 = null;


        // InternalEssentialOCL.g:3951:6: ( ( (otherlv_12= ':' ( (lv_ownedType_13_0= ruleTypeExpCS ) ) )? (otherlv_14= '<-' ( (lv_ownedCoIterator_15_0= ruleCoIteratorVariableCS ) ) )? otherlv_16= 'in' ( (lv_ownedInitExpression_17_0= ruleExpCS ) ) ) )
        // InternalEssentialOCL.g:3951:6: ( (otherlv_12= ':' ( (lv_ownedType_13_0= ruleTypeExpCS ) ) )? (otherlv_14= '<-' ( (lv_ownedCoIterator_15_0= ruleCoIteratorVariableCS ) ) )? otherlv_16= 'in' ( (lv_ownedInitExpression_17_0= ruleExpCS ) ) )
        {
        // InternalEssentialOCL.g:3951:6: ( (otherlv_12= ':' ( (lv_ownedType_13_0= ruleTypeExpCS ) ) )? (otherlv_14= '<-' ( (lv_ownedCoIterator_15_0= ruleCoIteratorVariableCS ) ) )? otherlv_16= 'in' ( (lv_ownedInitExpression_17_0= ruleExpCS ) ) )
        // InternalEssentialOCL.g:3951:7: (otherlv_12= ':' ( (lv_ownedType_13_0= ruleTypeExpCS ) ) )? (otherlv_14= '<-' ( (lv_ownedCoIterator_15_0= ruleCoIteratorVariableCS ) ) )? otherlv_16= 'in' ( (lv_ownedInitExpression_17_0= ruleExpCS ) )
        {
        // InternalEssentialOCL.g:3951:7: (otherlv_12= ':' ( (lv_ownedType_13_0= ruleTypeExpCS ) ) )?
        int alt119=2;
        int LA119_0 = input.LA(1);

        if ( (LA119_0==58) ) {
            alt119=1;
        }
        switch (alt119) {
            case 1 :
                // InternalEssentialOCL.g:3951:9: otherlv_12= ':' ( (lv_ownedType_13_0= ruleTypeExpCS ) )
                {
                otherlv_12=(Token)match(input,58,FollowSets000.FOLLOW_4); if (state.failed) return ;
                // InternalEssentialOCL.g:3955:1: ( (lv_ownedType_13_0= ruleTypeExpCS ) )
                // InternalEssentialOCL.g:3956:1: (lv_ownedType_13_0= ruleTypeExpCS )
                {
                // InternalEssentialOCL.g:3956:1: (lv_ownedType_13_0= ruleTypeExpCS )
                // InternalEssentialOCL.g:3957:3: lv_ownedType_13_0= ruleTypeExpCS
                {
                if ( state.backtracking==0 ) {

                  	        newCompositeNode(grammarAccess.getNavigatingCommaArgCSAccess().getOwnedTypeTypeExpCSParserRuleCall_2_2_0_1_0());

                }
                pushFollow(FollowSets000.FOLLOW_41);
                lv_ownedType_13_0=ruleTypeExpCS();

                state._fsp--;
                if (state.failed) return ;

                }


                }


                }
                break;

        }

        // InternalEssentialOCL.g:3973:4: (otherlv_14= '<-' ( (lv_ownedCoIterator_15_0= ruleCoIteratorVariableCS ) ) )?
        int alt120=2;
        int LA120_0 = input.LA(1);

        if ( (LA120_0==64) ) {
            alt120=1;
        }
        switch (alt120) {
            case 1 :
                // InternalEssentialOCL.g:3973:6: otherlv_14= '<-' ( (lv_ownedCoIterator_15_0= ruleCoIteratorVariableCS ) )
                {
                otherlv_14=(Token)match(input,64,FollowSets000.FOLLOW_10); if (state.failed) return ;
                // InternalEssentialOCL.g:3977:1: ( (lv_ownedCoIterator_15_0= ruleCoIteratorVariableCS ) )
                // InternalEssentialOCL.g:3978:1: (lv_ownedCoIterator_15_0= ruleCoIteratorVariableCS )
                {
                // InternalEssentialOCL.g:3978:1: (lv_ownedCoIterator_15_0= ruleCoIteratorVariableCS )
                // InternalEssentialOCL.g:3979:3: lv_ownedCoIterator_15_0= ruleCoIteratorVariableCS
                {
                if ( state.backtracking==0 ) {

                  	        newCompositeNode(grammarAccess.getNavigatingCommaArgCSAccess().getOwnedCoIteratorCoIteratorVariableCSParserRuleCall_2_2_1_1_0());

                }
                pushFollow(FollowSets000.FOLLOW_42);
                lv_ownedCoIterator_15_0=ruleCoIteratorVariableCS();

                state._fsp--;
                if (state.failed) return ;

                }


                }


                }
                break;

        }

        otherlv_16=(Token)match(input,73,FollowSets000.FOLLOW_17); if (state.failed) return ;
        // InternalEssentialOCL.g:3999:1: ( (lv_ownedInitExpression_17_0= ruleExpCS ) )
        // InternalEssentialOCL.g:4000:1: (lv_ownedInitExpression_17_0= ruleExpCS )
        {
        // InternalEssentialOCL.g:4000:1: (lv_ownedInitExpression_17_0= ruleExpCS )
        // InternalEssentialOCL.g:4001:3: lv_ownedInitExpression_17_0= ruleExpCS
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
    // $ANTLR end synpred117_InternalEssentialOCL

    // Delegated rules

    public final boolean synpred84_InternalEssentialOCL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred84_InternalEssentialOCL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred111_InternalEssentialOCL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred111_InternalEssentialOCL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred106_InternalEssentialOCL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred106_InternalEssentialOCL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred103_InternalEssentialOCL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred103_InternalEssentialOCL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred76_InternalEssentialOCL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred76_InternalEssentialOCL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred100_InternalEssentialOCL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred100_InternalEssentialOCL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred85_InternalEssentialOCL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred85_InternalEssentialOCL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred117_InternalEssentialOCL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred117_InternalEssentialOCL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred73_InternalEssentialOCL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred73_InternalEssentialOCL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred114_InternalEssentialOCL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred114_InternalEssentialOCL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred87_InternalEssentialOCL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred87_InternalEssentialOCL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred83_InternalEssentialOCL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred83_InternalEssentialOCL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }


    protected DFA35 dfa35 = new DFA35(this);
    protected DFA38 dfa38 = new DFA38(this);
    protected DFA41 dfa41 = new DFA41(this);
    protected DFA57 dfa57 = new DFA57(this);
    protected DFA66 dfa66 = new DFA66(this);
    static final String dfa_1s = "\23\uffff";
    static final String dfa_2s = "\1\4\12\uffff\5\0\3\uffff";
    static final String dfa_3s = "\1\66\12\uffff\5\0\3\uffff";
    static final String dfa_4s = "\1\uffff\1\1\1\uffff\1\2\16\uffff\1\3";
    static final String dfa_5s = "\13\uffff\1\0\1\1\1\2\1\3\1\4\3\uffff}>";
    static final String[] dfa_6s = {
            "\2\1\42\uffff\12\3\1\13\1\14\1\15\1\16\1\17",
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

    static final short[] dfa_1 = DFA.unpackEncodedString(dfa_1s);
    static final char[] dfa_2 = DFA.unpackEncodedStringToUnsignedChars(dfa_2s);
    static final char[] dfa_3 = DFA.unpackEncodedStringToUnsignedChars(dfa_3s);
    static final short[] dfa_4 = DFA.unpackEncodedString(dfa_4s);
    static final short[] dfa_5 = DFA.unpackEncodedString(dfa_5s);
    static final short[][] dfa_6 = unpackEncodedStringArray(dfa_6s);

    class DFA35 extends DFA {

        public DFA35(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 35;
            this.eot = dfa_1;
            this.eof = dfa_1;
            this.min = dfa_2;
            this.max = dfa_3;
            this.accept = dfa_4;
            this.special = dfa_5;
            this.transition = dfa_6;
        }
        public String getDescription() {
            return "2593:1: (this_TypeNameExpCS_0= ruleTypeNameExpCS | this_TypeLiteralCS_1= ruleTypeLiteralCS | this_CollectionPatternCS_2= ruleCollectionPatternCS )";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 :
                        int LA35_11 = input.LA(1);


                        int index35_11 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred73_InternalEssentialOCL()) ) {s = 3;}

                        else if ( (true) ) {s = 18;}


                        input.seek(index35_11);
                        if ( s>=0 ) return s;
                        break;
                    case 1 :
                        int LA35_12 = input.LA(1);


                        int index35_12 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred73_InternalEssentialOCL()) ) {s = 3;}

                        else if ( (true) ) {s = 18;}


                        input.seek(index35_12);
                        if ( s>=0 ) return s;
                        break;
                    case 2 :
                        int LA35_13 = input.LA(1);


                        int index35_13 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred73_InternalEssentialOCL()) ) {s = 3;}

                        else if ( (true) ) {s = 18;}


                        input.seek(index35_13);
                        if ( s>=0 ) return s;
                        break;
                    case 3 :
                        int LA35_14 = input.LA(1);


                        int index35_14 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred73_InternalEssentialOCL()) ) {s = 3;}

                        else if ( (true) ) {s = 18;}


                        input.seek(index35_14);
                        if ( s>=0 ) return s;
                        break;
                    case 4 :
                        int LA35_15 = input.LA(1);


                        int index35_15 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred73_InternalEssentialOCL()) ) {s = 3;}

                        else if ( (true) ) {s = 18;}


                        input.seek(index35_15);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 35, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String dfa_7s = "\41\uffff";
    static final String dfa_8s = "\1\4\3\0\35\uffff";
    static final String dfa_9s = "\1\122\3\0\35\uffff";
    static final String dfa_10s = "\4\uffff\1\1\33\uffff\1\2";
    static final String dfa_11s = "\1\uffff\1\0\1\1\1\2\35\uffff}>";
    static final String[] dfa_12s = {
            "\4\4\10\uffff\1\1\1\2\1\3\1\4\24\uffff\20\4\7\uffff\1\4\1\uffff\4\4\7\uffff\1\4\4\uffff\1\40\1\4",
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
            ""
    };

    static final short[] dfa_7 = DFA.unpackEncodedString(dfa_7s);
    static final char[] dfa_8 = DFA.unpackEncodedStringToUnsignedChars(dfa_8s);
    static final char[] dfa_9 = DFA.unpackEncodedStringToUnsignedChars(dfa_9s);
    static final short[] dfa_10 = DFA.unpackEncodedString(dfa_10s);
    static final short[] dfa_11 = DFA.unpackEncodedString(dfa_11s);
    static final short[][] dfa_12 = unpackEncodedStringArray(dfa_12s);

    class DFA38 extends DFA {

        public DFA38(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 38;
            this.eot = dfa_7;
            this.eof = dfa_7;
            this.min = dfa_8;
            this.max = dfa_9;
            this.accept = dfa_10;
            this.special = dfa_11;
            this.transition = dfa_12;
        }
        public String getDescription() {
            return "2703:1: ( (this_PrefixedPrimaryExpCS_0= rulePrefixedPrimaryExpCS ( () ( (lv_name_2_0= ruleBinaryOperatorName ) ) ( (lv_ownedRight_3_0= ruleExpCS ) ) )? ) | this_PrefixedLetExpCS_4= rulePrefixedLetExpCS )";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 :
                        int LA38_1 = input.LA(1);


                        int index38_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred76_InternalEssentialOCL()) ) {s = 4;}

                        else if ( (true) ) {s = 32;}


                        input.seek(index38_1);
                        if ( s>=0 ) return s;
                        break;
                    case 1 :
                        int LA38_2 = input.LA(1);


                        int index38_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred76_InternalEssentialOCL()) ) {s = 4;}

                        else if ( (true) ) {s = 32;}


                        input.seek(index38_2);
                        if ( s>=0 ) return s;
                        break;
                    case 2 :
                        int LA38_3 = input.LA(1);


                        int index38_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred76_InternalEssentialOCL()) ) {s = 4;}

                        else if ( (true) ) {s = 32;}


                        input.seek(index38_3);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 38, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String dfa_13s = "\40\uffff";
    static final String dfa_14s = "\1\4\12\uffff\7\0\16\uffff";
    static final String dfa_15s = "\1\122\12\uffff\7\0\16\uffff";
    static final String dfa_16s = "\1\uffff\1\1\1\2\1\3\1\4\15\uffff\1\10\1\11\7\uffff\1\12\1\uffff\1\5\1\6\1\7";
    static final String dfa_17s = "\13\uffff\1\0\1\1\1\2\1\3\1\4\1\5\1\6\16\uffff}>";
    static final String[] dfa_18s = {
            "\2\33\2\4\13\uffff\1\4\24\uffff\1\14\1\13\10\23\1\15\1\16\1\17\1\20\1\21\1\1\7\uffff\1\22\1\uffff\4\4\7\uffff\1\2\5\uffff\1\3",
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
            ""
    };

    static final short[] dfa_13 = DFA.unpackEncodedString(dfa_13s);
    static final char[] dfa_14 = DFA.unpackEncodedStringToUnsignedChars(dfa_14s);
    static final char[] dfa_15 = DFA.unpackEncodedStringToUnsignedChars(dfa_15s);
    static final short[] dfa_16 = DFA.unpackEncodedString(dfa_16s);
    static final short[] dfa_17 = DFA.unpackEncodedString(dfa_17s);
    static final short[][] dfa_18 = unpackEncodedStringArray(dfa_18s);

    class DFA41 extends DFA {

        public DFA41(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 41;
            this.eot = dfa_13;
            this.eof = dfa_13;
            this.min = dfa_14;
            this.max = dfa_15;
            this.accept = dfa_16;
            this.special = dfa_17;
            this.transition = dfa_18;
        }
        public String getDescription() {
            return "2952:1: (this_NestedExpCS_0= ruleNestedExpCS | this_IfExpCS_1= ruleIfExpCS | this_SelfExpCS_2= ruleSelfExpCS | this_PrimitiveLiteralExpCS_3= rulePrimitiveLiteralExpCS | this_TupleLiteralExpCS_4= ruleTupleLiteralExpCS | this_MapLiteralExpCS_5= ruleMapLiteralExpCS | this_CollectionLiteralExpCS_6= ruleCollectionLiteralExpCS | this_LambdaLiteralExpCS_7= ruleLambdaLiteralExpCS | this_TypeLiteralExpCS_8= ruleTypeLiteralExpCS | this_NameExpCS_9= ruleNameExpCS )";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 :
                        int LA41_11 = input.LA(1);


                        int index41_11 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred83_InternalEssentialOCL()) ) {s = 29;}

                        else if ( (synpred87_InternalEssentialOCL()) ) {s = 19;}


                        input.seek(index41_11);
                        if ( s>=0 ) return s;
                        break;
                    case 1 :
                        int LA41_12 = input.LA(1);


                        int index41_12 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred84_InternalEssentialOCL()) ) {s = 30;}

                        else if ( (synpred87_InternalEssentialOCL()) ) {s = 19;}


                        input.seek(index41_12);
                        if ( s>=0 ) return s;
                        break;
                    case 2 :
                        int LA41_13 = input.LA(1);


                        int index41_13 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred85_InternalEssentialOCL()) ) {s = 31;}

                        else if ( (synpred87_InternalEssentialOCL()) ) {s = 19;}


                        input.seek(index41_13);
                        if ( s>=0 ) return s;
                        break;
                    case 3 :
                        int LA41_14 = input.LA(1);


                        int index41_14 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred85_InternalEssentialOCL()) ) {s = 31;}

                        else if ( (synpred87_InternalEssentialOCL()) ) {s = 19;}


                        input.seek(index41_14);
                        if ( s>=0 ) return s;
                        break;
                    case 4 :
                        int LA41_15 = input.LA(1);


                        int index41_15 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred85_InternalEssentialOCL()) ) {s = 31;}

                        else if ( (synpred87_InternalEssentialOCL()) ) {s = 19;}


                        input.seek(index41_15);
                        if ( s>=0 ) return s;
                        break;
                    case 5 :
                        int LA41_16 = input.LA(1);


                        int index41_16 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred85_InternalEssentialOCL()) ) {s = 31;}

                        else if ( (synpred87_InternalEssentialOCL()) ) {s = 19;}


                        input.seek(index41_16);
                        if ( s>=0 ) return s;
                        break;
                    case 6 :
                        int LA41_17 = input.LA(1);


                        int index41_17 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred85_InternalEssentialOCL()) ) {s = 31;}

                        else if ( (synpred87_InternalEssentialOCL()) ) {s = 19;}


                        input.seek(index41_17);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 41, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String dfa_19s = "\13\uffff";
    static final String dfa_20s = "\1\4\12\uffff";
    static final String dfa_21s = "\1\70\2\0\10\uffff";
    static final String dfa_22s = "\1\113\2\0\10\uffff";
    static final String dfa_23s = "\3\uffff\1\3\1\4\4\uffff\1\1\1\2";
    static final String dfa_24s = "\1\uffff\1\0\1\1\10\uffff}>";
    static final String[] dfa_25s = {
            "\2\4\1\2\5\uffff\1\1\10\uffff\1\3\2\4",
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

    static final short[] dfa_19 = DFA.unpackEncodedString(dfa_19s);
    static final short[] dfa_20 = DFA.unpackEncodedString(dfa_20s);
    static final char[] dfa_21 = DFA.unpackEncodedStringToUnsignedChars(dfa_21s);
    static final char[] dfa_22 = DFA.unpackEncodedStringToUnsignedChars(dfa_22s);
    static final short[] dfa_23 = DFA.unpackEncodedString(dfa_23s);
    static final short[] dfa_24 = DFA.unpackEncodedString(dfa_24s);
    static final short[][] dfa_25 = unpackEncodedStringArray(dfa_25s);

    class DFA57 extends DFA {

        public DFA57(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 57;
            this.eot = dfa_19;
            this.eof = dfa_20;
            this.min = dfa_21;
            this.max = dfa_22;
            this.accept = dfa_23;
            this.special = dfa_24;
            this.transition = dfa_25;
        }
        public String getDescription() {
            return "3486:2: ( (otherlv_1= '<-' ( (lv_ownedCoIterator_2_0= ruleCoIteratorVariableCS ) ) (otherlv_3= '=' ( (lv_ownedInitExpression_4_0= ruleExpCS ) ) )? ) | (otherlv_5= ':' ( (lv_ownedType_6_0= ruleTypeExpCS ) ) (otherlv_7= '<-' ( (lv_ownedCoIterator_8_0= ruleCoIteratorVariableCS ) ) )? (otherlv_9= '=' ( (lv_ownedInitExpression_10_0= ruleExpCS ) ) )? ) | ( (otherlv_11= ':' ( (lv_ownedType_12_0= ruleTypeExpCS ) ) )? (otherlv_13= '<-' ( (lv_ownedCoIterator_14_0= ruleCoIteratorVariableCS ) ) )? otherlv_15= 'in' ( (lv_ownedInitExpression_16_0= ruleExpCS ) ) ) )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 :
                        int LA57_1 = input.LA(1);


                        int index57_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred100_InternalEssentialOCL()) ) {s = 9;}

                        else if ( (synpred106_InternalEssentialOCL()) ) {s = 3;}


                        input.seek(index57_1);
                        if ( s>=0 ) return s;
                        break;
                    case 1 :
                        int LA57_2 = input.LA(1);


                        int index57_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred103_InternalEssentialOCL()) ) {s = 10;}

                        else if ( (synpred106_InternalEssentialOCL()) ) {s = 3;}


                        input.seek(index57_2);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 57, _s, input);
            error(nvae);
            throw nvae;
        }
    }

    class DFA66 extends DFA {

        public DFA66(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 66;
            this.eot = dfa_19;
            this.eof = dfa_20;
            this.min = dfa_21;
            this.max = dfa_22;
            this.accept = dfa_23;
            this.special = dfa_24;
            this.transition = dfa_25;
        }
        public String getDescription() {
            return "3839:2: ( (otherlv_2= '<-' ( (lv_ownedCoIterator_3_0= ruleCoIteratorVariableCS ) ) (otherlv_4= '=' ( (lv_ownedInitExpression_5_0= ruleExpCS ) ) )? ) | (otherlv_6= ':' ( (lv_ownedType_7_0= ruleTypeExpCS ) ) (otherlv_8= '<-' ( (lv_ownedCoIterator_9_0= ruleCoIteratorVariableCS ) ) )? (otherlv_10= '=' ( (lv_ownedInitExpression_11_0= ruleExpCS ) ) )? ) | ( (otherlv_12= ':' ( (lv_ownedType_13_0= ruleTypeExpCS ) ) )? (otherlv_14= '<-' ( (lv_ownedCoIterator_15_0= ruleCoIteratorVariableCS ) ) )? otherlv_16= 'in' ( (lv_ownedInitExpression_17_0= ruleExpCS ) ) ) )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 :
                        int LA66_1 = input.LA(1);


                        int index66_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred111_InternalEssentialOCL()) ) {s = 9;}

                        else if ( (synpred117_InternalEssentialOCL()) ) {s = 3;}


                        input.seek(index66_1);
                        if ( s>=0 ) return s;
                        break;
                    case 1 :
                        int LA66_2 = input.LA(1);


                        int index66_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred114_InternalEssentialOCL()) ) {s = 10;}

                        else if ( (synpred117_InternalEssentialOCL()) ) {s = 3;}


                        input.seek(index66_2);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 66, _s, input);
            error(nvae);
            throw nvae;
        }
    }



    private static class FollowSets000 {
        public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0080000000000002L});
        public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x007FFF0000000030L});
        public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0100000000000000L,0x0000000000000080L});
        public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0100000000000000L});
        public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0200000000000000L});
        public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0100000000000030L});
        public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0300000000000000L});
        public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0400000000000000L});
        public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0800000000000000L});
        public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x94FFFF00000F00F0L,0x000000000006101EL});
        public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x1200000000000000L});
        public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x84FFFF00000F00F0L,0x000000000006101EL});
        public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x2000000000000002L});
        public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x80FFFF00000F00F0L,0x000000000006101EL});
        public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x4200000000000000L});
        public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x1000000000000000L});
        public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x0000000004000000L});
        public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x90FFFF00000F00F0L,0x000000000006101EL});
        public static final BitSet FOLLOW_22 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000001L});
        public static final BitSet FOLLOW_23 = new BitSet(new long[]{0x0400000004000000L});
        public static final BitSet FOLLOW_24 = new BitSet(new long[]{0x0000000000000082L});
        public static final BitSet FOLLOW_25 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000080L});
        public static final BitSet FOLLOW_26 = new BitSet(new long[]{0x0800000000000002L});
        public static final BitSet FOLLOW_27 = new BitSet(new long[]{0x000000FFFFF90002L});
        public static final BitSet FOLLOW_28 = new BitSet(new long[]{0x80FFFF00000F00F0L,0x000000000004101EL});
        public static final BitSet FOLLOW_29 = new BitSet(new long[]{0x0880000000000002L,0x00000000000000A0L});
        public static final BitSet FOLLOW_30 = new BitSet(new long[]{0x0800000000000002L,0x0000000000000020L});
        public static final BitSet FOLLOW_31 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000020L});
        public static final BitSet FOLLOW_32 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
        public static final BitSet FOLLOW_33 = new BitSet(new long[]{0x10000000000000B0L});
        public static final BitSet FOLLOW_34 = new BitSet(new long[]{0x00000000000000B0L});
        public static final BitSet FOLLOW_35 = new BitSet(new long[]{0x85FFFF00000F00F0L,0x000000000006101EL});
        public static final BitSet FOLLOW_36 = new BitSet(new long[]{0x0300000000000000L,0x0000000000000C00L});
        public static final BitSet FOLLOW_37 = new BitSet(new long[]{0x0200000000000000L,0x0000000000000100L});
        public static final BitSet FOLLOW_38 = new BitSet(new long[]{0x0400000000000002L,0x0000000000000201L});
        public static final BitSet FOLLOW_39 = new BitSet(new long[]{0x0000000004000002L});
        public static final BitSet FOLLOW_40 = new BitSet(new long[]{0x0000000004000002L,0x0000000000000001L});
        public static final BitSet FOLLOW_41 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000201L});
        public static final BitSet FOLLOW_42 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
        public static final BitSet FOLLOW_43 = new BitSet(new long[]{0x0400000000000002L});
        public static final BitSet FOLLOW_44 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
        public static final BitSet FOLLOW_45 = new BitSet(new long[]{0x0000000000000000L,0x0000000000014000L});
        public static final BitSet FOLLOW_46 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
        public static final BitSet FOLLOW_47 = new BitSet(new long[]{0x0200000000000000L,0x0000000000000200L});
        public static final BitSet FOLLOW_48 = new BitSet(new long[]{0x0480000004000000L});
        public static final BitSet FOLLOW_49 = new BitSet(new long[]{0x0000000000080040L});
        public static final BitSet FOLLOW_50 = new BitSet(new long[]{0x0000000000280040L,0x0000000000200000L});
        public static final BitSet FOLLOW_51 = new BitSet(new long[]{0x0000000000000000L,0x0000000000180100L});
        public static final BitSet FOLLOW_52 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
        public static final BitSet FOLLOW_53 = new BitSet(new long[]{0x0000000000000002L,0x0000000000400000L});
        public static final BitSet FOLLOW_54 = new BitSet(new long[]{0x0200000000000002L,0x0000000000000080L});
        public static final BitSet FOLLOW_55 = new BitSet(new long[]{0x0000000000000030L,0x0000000000200000L});
        public static final BitSet FOLLOW_56 = new BitSet(new long[]{0x0000000000000002L,0x0000000000800000L});
        public static final BitSet FOLLOW_57 = new BitSet(new long[]{0x0000000000000002L,0x0000000001000000L});
    }


}
