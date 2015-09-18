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
@SuppressWarnings("all")
public class InternalBaseParser extends AbstractInternalContentAssistParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_INT", "RULE_SIMPLE_ID", "RULE_ESCAPED_ID", "RULE_ESCAPED_CHARACTER", "RULE_LETTER_CHARACTER", "RULE_DOUBLE_QUOTED_STRING", "RULE_SINGLE_QUOTED_STRING", "RULE_ML_SINGLE_QUOTED_STRING", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'|?'", "'*'", "'+'", "'?'", "'..'", "'['", "']'", "'::'", "','", "'extends'", "'&&'", "'('", "')'", "'|1'"
    };
    public static final int RULE_ML_SINGLE_QUOTED_STRING=11;
    public static final int T__29=29;
    public static final int T__28=28;
    public static final int T__27=27;
    public static final int T__26=26;
    public static final int RULE_ESCAPED_CHARACTER=7;
    public static final int T__25=25;
    public static final int T__24=24;
    public static final int T__23=23;
    public static final int T__22=22;
    public static final int RULE_ANY_OTHER=15;
    public static final int T__21=21;
    public static final int RULE_SINGLE_QUOTED_STRING=10;
    public static final int T__20=20;
    public static final int RULE_ESCAPED_ID=6;
    public static final int RULE_DOUBLE_QUOTED_STRING=9;
    public static final int RULE_SL_COMMENT=13;
    public static final int EOF=-1;
    public static final int RULE_ML_COMMENT=12;
    public static final int RULE_LETTER_CHARACTER=8;
    public static final int T__19=19;
    public static final int T__16=16;
    public static final int T__18=18;
    public static final int T__17=17;
    public static final int RULE_SIMPLE_ID=5;
    public static final int RULE_INT=4;
    public static final int RULE_WS=14;

    // delegates
    // delegators


        public InternalBaseParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalBaseParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalBaseParser.tokenNames; }
    public String getGrammarFileName() { return "../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g"; }


     
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




    // $ANTLR start "entryRuleMultiplicityBoundsCS"
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:61:1: entryRuleMultiplicityBoundsCS : ruleMultiplicityBoundsCS EOF ;
    public final void entryRuleMultiplicityBoundsCS() throws RecognitionException {
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:62:1: ( ruleMultiplicityBoundsCS EOF )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:63:1: ruleMultiplicityBoundsCS EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getMultiplicityBoundsCSRule()); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleMultiplicityBoundsCS_in_entryRuleMultiplicityBoundsCS67);
            ruleMultiplicityBoundsCS();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getMultiplicityBoundsCSRule()); 
            }
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleMultiplicityBoundsCS74); if (state.failed) return ;

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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:70:1: ruleMultiplicityBoundsCS : ( ( rule__MultiplicityBoundsCS__Group__0 ) ) ;
    public final void ruleMultiplicityBoundsCS() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:74:2: ( ( ( rule__MultiplicityBoundsCS__Group__0 ) ) )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:75:1: ( ( rule__MultiplicityBoundsCS__Group__0 ) )
            {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:75:1: ( ( rule__MultiplicityBoundsCS__Group__0 ) )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:76:1: ( rule__MultiplicityBoundsCS__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getMultiplicityBoundsCSAccess().getGroup()); 
            }
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:77:1: ( rule__MultiplicityBoundsCS__Group__0 )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:77:2: rule__MultiplicityBoundsCS__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_rule__MultiplicityBoundsCS__Group__0_in_ruleMultiplicityBoundsCS100);
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:89:1: entryRuleMultiplicityCS : ruleMultiplicityCS EOF ;
    public final void entryRuleMultiplicityCS() throws RecognitionException {
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:90:1: ( ruleMultiplicityCS EOF )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:91:1: ruleMultiplicityCS EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getMultiplicityCSRule()); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleMultiplicityCS_in_entryRuleMultiplicityCS127);
            ruleMultiplicityCS();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getMultiplicityCSRule()); 
            }
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleMultiplicityCS134); if (state.failed) return ;

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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:98:1: ruleMultiplicityCS : ( ( rule__MultiplicityCS__Group__0 ) ) ;
    public final void ruleMultiplicityCS() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:102:2: ( ( ( rule__MultiplicityCS__Group__0 ) ) )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:103:1: ( ( rule__MultiplicityCS__Group__0 ) )
            {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:103:1: ( ( rule__MultiplicityCS__Group__0 ) )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:104:1: ( rule__MultiplicityCS__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getMultiplicityCSAccess().getGroup()); 
            }
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:105:1: ( rule__MultiplicityCS__Group__0 )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:105:2: rule__MultiplicityCS__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_rule__MultiplicityCS__Group__0_in_ruleMultiplicityCS160);
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:117:1: entryRuleMultiplicityStringCS : ruleMultiplicityStringCS EOF ;
    public final void entryRuleMultiplicityStringCS() throws RecognitionException {
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:118:1: ( ruleMultiplicityStringCS EOF )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:119:1: ruleMultiplicityStringCS EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getMultiplicityStringCSRule()); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleMultiplicityStringCS_in_entryRuleMultiplicityStringCS187);
            ruleMultiplicityStringCS();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getMultiplicityStringCSRule()); 
            }
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleMultiplicityStringCS194); if (state.failed) return ;

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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:126:1: ruleMultiplicityStringCS : ( ( rule__MultiplicityStringCS__StringBoundsAssignment ) ) ;
    public final void ruleMultiplicityStringCS() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:130:2: ( ( ( rule__MultiplicityStringCS__StringBoundsAssignment ) ) )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:131:1: ( ( rule__MultiplicityStringCS__StringBoundsAssignment ) )
            {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:131:1: ( ( rule__MultiplicityStringCS__StringBoundsAssignment ) )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:132:1: ( rule__MultiplicityStringCS__StringBoundsAssignment )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getMultiplicityStringCSAccess().getStringBoundsAssignment()); 
            }
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:133:1: ( rule__MultiplicityStringCS__StringBoundsAssignment )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:133:2: rule__MultiplicityStringCS__StringBoundsAssignment
            {
            pushFollow(FollowSets000.FOLLOW_rule__MultiplicityStringCS__StringBoundsAssignment_in_ruleMultiplicityStringCS220);
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:145:1: entryRulePathNameCS : rulePathNameCS EOF ;
    public final void entryRulePathNameCS() throws RecognitionException {
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:146:1: ( rulePathNameCS EOF )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:147:1: rulePathNameCS EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getPathNameCSRule()); 
            }
            pushFollow(FollowSets000.FOLLOW_rulePathNameCS_in_entryRulePathNameCS247);
            rulePathNameCS();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getPathNameCSRule()); 
            }
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRulePathNameCS254); if (state.failed) return ;

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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:154:1: rulePathNameCS : ( ( rule__PathNameCS__Group__0 ) ) ;
    public final void rulePathNameCS() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:158:2: ( ( ( rule__PathNameCS__Group__0 ) ) )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:159:1: ( ( rule__PathNameCS__Group__0 ) )
            {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:159:1: ( ( rule__PathNameCS__Group__0 ) )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:160:1: ( rule__PathNameCS__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getPathNameCSAccess().getGroup()); 
            }
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:161:1: ( rule__PathNameCS__Group__0 )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:161:2: rule__PathNameCS__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_rule__PathNameCS__Group__0_in_rulePathNameCS280);
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:173:1: entryRuleFirstPathElementCS : ruleFirstPathElementCS EOF ;
    public final void entryRuleFirstPathElementCS() throws RecognitionException {
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:174:1: ( ruleFirstPathElementCS EOF )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:175:1: ruleFirstPathElementCS EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFirstPathElementCSRule()); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleFirstPathElementCS_in_entryRuleFirstPathElementCS307);
            ruleFirstPathElementCS();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getFirstPathElementCSRule()); 
            }
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleFirstPathElementCS314); if (state.failed) return ;

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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:182:1: ruleFirstPathElementCS : ( ( rule__FirstPathElementCS__ReferredElementAssignment ) ) ;
    public final void ruleFirstPathElementCS() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:186:2: ( ( ( rule__FirstPathElementCS__ReferredElementAssignment ) ) )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:187:1: ( ( rule__FirstPathElementCS__ReferredElementAssignment ) )
            {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:187:1: ( ( rule__FirstPathElementCS__ReferredElementAssignment ) )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:188:1: ( rule__FirstPathElementCS__ReferredElementAssignment )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFirstPathElementCSAccess().getReferredElementAssignment()); 
            }
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:189:1: ( rule__FirstPathElementCS__ReferredElementAssignment )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:189:2: rule__FirstPathElementCS__ReferredElementAssignment
            {
            pushFollow(FollowSets000.FOLLOW_rule__FirstPathElementCS__ReferredElementAssignment_in_ruleFirstPathElementCS340);
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:201:1: entryRuleNextPathElementCS : ruleNextPathElementCS EOF ;
    public final void entryRuleNextPathElementCS() throws RecognitionException {
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:202:1: ( ruleNextPathElementCS EOF )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:203:1: ruleNextPathElementCS EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getNextPathElementCSRule()); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleNextPathElementCS_in_entryRuleNextPathElementCS367);
            ruleNextPathElementCS();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getNextPathElementCSRule()); 
            }
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleNextPathElementCS374); if (state.failed) return ;

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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:210:1: ruleNextPathElementCS : ( ( rule__NextPathElementCS__ReferredElementAssignment ) ) ;
    public final void ruleNextPathElementCS() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:214:2: ( ( ( rule__NextPathElementCS__ReferredElementAssignment ) ) )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:215:1: ( ( rule__NextPathElementCS__ReferredElementAssignment ) )
            {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:215:1: ( ( rule__NextPathElementCS__ReferredElementAssignment ) )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:216:1: ( rule__NextPathElementCS__ReferredElementAssignment )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getNextPathElementCSAccess().getReferredElementAssignment()); 
            }
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:217:1: ( rule__NextPathElementCS__ReferredElementAssignment )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:217:2: rule__NextPathElementCS__ReferredElementAssignment
            {
            pushFollow(FollowSets000.FOLLOW_rule__NextPathElementCS__ReferredElementAssignment_in_ruleNextPathElementCS400);
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:229:1: entryRuleTemplateBindingCS : ruleTemplateBindingCS EOF ;
    public final void entryRuleTemplateBindingCS() throws RecognitionException {
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:230:1: ( ruleTemplateBindingCS EOF )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:231:1: ruleTemplateBindingCS EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTemplateBindingCSRule()); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleTemplateBindingCS_in_entryRuleTemplateBindingCS427);
            ruleTemplateBindingCS();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getTemplateBindingCSRule()); 
            }
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleTemplateBindingCS434); if (state.failed) return ;

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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:238:1: ruleTemplateBindingCS : ( ( rule__TemplateBindingCS__Group__0 ) ) ;
    public final void ruleTemplateBindingCS() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:242:2: ( ( ( rule__TemplateBindingCS__Group__0 ) ) )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:243:1: ( ( rule__TemplateBindingCS__Group__0 ) )
            {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:243:1: ( ( rule__TemplateBindingCS__Group__0 ) )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:244:1: ( rule__TemplateBindingCS__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTemplateBindingCSAccess().getGroup()); 
            }
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:245:1: ( rule__TemplateBindingCS__Group__0 )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:245:2: rule__TemplateBindingCS__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_rule__TemplateBindingCS__Group__0_in_ruleTemplateBindingCS460);
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:257:1: entryRuleTemplateParameterSubstitutionCS : ruleTemplateParameterSubstitutionCS EOF ;
    public final void entryRuleTemplateParameterSubstitutionCS() throws RecognitionException {
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:258:1: ( ruleTemplateParameterSubstitutionCS EOF )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:259:1: ruleTemplateParameterSubstitutionCS EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTemplateParameterSubstitutionCSRule()); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleTemplateParameterSubstitutionCS_in_entryRuleTemplateParameterSubstitutionCS487);
            ruleTemplateParameterSubstitutionCS();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getTemplateParameterSubstitutionCSRule()); 
            }
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleTemplateParameterSubstitutionCS494); if (state.failed) return ;

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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:266:1: ruleTemplateParameterSubstitutionCS : ( ( rule__TemplateParameterSubstitutionCS__OwnedActualParameterAssignment ) ) ;
    public final void ruleTemplateParameterSubstitutionCS() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:270:2: ( ( ( rule__TemplateParameterSubstitutionCS__OwnedActualParameterAssignment ) ) )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:271:1: ( ( rule__TemplateParameterSubstitutionCS__OwnedActualParameterAssignment ) )
            {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:271:1: ( ( rule__TemplateParameterSubstitutionCS__OwnedActualParameterAssignment ) )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:272:1: ( rule__TemplateParameterSubstitutionCS__OwnedActualParameterAssignment )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTemplateParameterSubstitutionCSAccess().getOwnedActualParameterAssignment()); 
            }
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:273:1: ( rule__TemplateParameterSubstitutionCS__OwnedActualParameterAssignment )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:273:2: rule__TemplateParameterSubstitutionCS__OwnedActualParameterAssignment
            {
            pushFollow(FollowSets000.FOLLOW_rule__TemplateParameterSubstitutionCS__OwnedActualParameterAssignment_in_ruleTemplateParameterSubstitutionCS520);
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:287:1: entryRuleTypeParameterCS : ruleTypeParameterCS EOF ;
    public final void entryRuleTypeParameterCS() throws RecognitionException {
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:288:1: ( ruleTypeParameterCS EOF )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:289:1: ruleTypeParameterCS EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTypeParameterCSRule()); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleTypeParameterCS_in_entryRuleTypeParameterCS549);
            ruleTypeParameterCS();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getTypeParameterCSRule()); 
            }
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleTypeParameterCS556); if (state.failed) return ;

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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:296:1: ruleTypeParameterCS : ( ( rule__TypeParameterCS__Group__0 ) ) ;
    public final void ruleTypeParameterCS() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:300:2: ( ( ( rule__TypeParameterCS__Group__0 ) ) )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:301:1: ( ( rule__TypeParameterCS__Group__0 ) )
            {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:301:1: ( ( rule__TypeParameterCS__Group__0 ) )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:302:1: ( rule__TypeParameterCS__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTypeParameterCSAccess().getGroup()); 
            }
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:303:1: ( rule__TypeParameterCS__Group__0 )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:303:2: rule__TypeParameterCS__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_rule__TypeParameterCS__Group__0_in_ruleTypeParameterCS582);
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:315:1: entryRuleTypeRefCS : ruleTypeRefCS EOF ;
    public final void entryRuleTypeRefCS() throws RecognitionException {
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:316:1: ( ruleTypeRefCS EOF )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:317:1: ruleTypeRefCS EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTypeRefCSRule()); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleTypeRefCS_in_entryRuleTypeRefCS609);
            ruleTypeRefCS();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getTypeRefCSRule()); 
            }
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleTypeRefCS616); if (state.failed) return ;

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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:324:1: ruleTypeRefCS : ( ( rule__TypeRefCS__Alternatives ) ) ;
    public final void ruleTypeRefCS() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:328:2: ( ( ( rule__TypeRefCS__Alternatives ) ) )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:329:1: ( ( rule__TypeRefCS__Alternatives ) )
            {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:329:1: ( ( rule__TypeRefCS__Alternatives ) )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:330:1: ( rule__TypeRefCS__Alternatives )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTypeRefCSAccess().getAlternatives()); 
            }
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:331:1: ( rule__TypeRefCS__Alternatives )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:331:2: rule__TypeRefCS__Alternatives
            {
            pushFollow(FollowSets000.FOLLOW_rule__TypeRefCS__Alternatives_in_ruleTypeRefCS642);
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:343:1: entryRuleTypedRefCS : ruleTypedRefCS EOF ;
    public final void entryRuleTypedRefCS() throws RecognitionException {
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:344:1: ( ruleTypedRefCS EOF )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:345:1: ruleTypedRefCS EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTypedRefCSRule()); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleTypedRefCS_in_entryRuleTypedRefCS669);
            ruleTypedRefCS();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getTypedRefCSRule()); 
            }
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleTypedRefCS676); if (state.failed) return ;

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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:352:1: ruleTypedRefCS : ( ruleTypedTypeRefCS ) ;
    public final void ruleTypedRefCS() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:356:2: ( ( ruleTypedTypeRefCS ) )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:357:1: ( ruleTypedTypeRefCS )
            {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:357:1: ( ruleTypedTypeRefCS )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:358:1: ruleTypedTypeRefCS
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTypedRefCSAccess().getTypedTypeRefCSParserRuleCall()); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleTypedTypeRefCS_in_ruleTypedRefCS702);
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:371:1: entryRuleTypedTypeRefCS : ruleTypedTypeRefCS EOF ;
    public final void entryRuleTypedTypeRefCS() throws RecognitionException {
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:372:1: ( ruleTypedTypeRefCS EOF )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:373:1: ruleTypedTypeRefCS EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTypedTypeRefCSRule()); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleTypedTypeRefCS_in_entryRuleTypedTypeRefCS728);
            ruleTypedTypeRefCS();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getTypedTypeRefCSRule()); 
            }
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleTypedTypeRefCS735); if (state.failed) return ;

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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:380:1: ruleTypedTypeRefCS : ( ( rule__TypedTypeRefCS__Group__0 ) ) ;
    public final void ruleTypedTypeRefCS() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:384:2: ( ( ( rule__TypedTypeRefCS__Group__0 ) ) )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:385:1: ( ( rule__TypedTypeRefCS__Group__0 ) )
            {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:385:1: ( ( rule__TypedTypeRefCS__Group__0 ) )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:386:1: ( rule__TypedTypeRefCS__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTypedTypeRefCSAccess().getGroup()); 
            }
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:387:1: ( rule__TypedTypeRefCS__Group__0 )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:387:2: rule__TypedTypeRefCS__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_rule__TypedTypeRefCS__Group__0_in_ruleTypedTypeRefCS761);
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:399:1: entryRuleUnreservedName : ruleUnreservedName EOF ;
    public final void entryRuleUnreservedName() throws RecognitionException {
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:400:1: ( ruleUnreservedName EOF )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:401:1: ruleUnreservedName EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getUnreservedNameRule()); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleUnreservedName_in_entryRuleUnreservedName788);
            ruleUnreservedName();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getUnreservedNameRule()); 
            }
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleUnreservedName795); if (state.failed) return ;

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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:408:1: ruleUnreservedName : ( ruleUnrestrictedName ) ;
    public final void ruleUnreservedName() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:412:2: ( ( ruleUnrestrictedName ) )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:413:1: ( ruleUnrestrictedName )
            {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:413:1: ( ruleUnrestrictedName )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:414:1: ruleUnrestrictedName
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getUnreservedNameAccess().getUnrestrictedNameParserRuleCall()); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleUnrestrictedName_in_ruleUnreservedName821);
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:427:1: entryRuleUnrestrictedName : ruleUnrestrictedName EOF ;
    public final void entryRuleUnrestrictedName() throws RecognitionException {
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:428:1: ( ruleUnrestrictedName EOF )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:429:1: ruleUnrestrictedName EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getUnrestrictedNameRule()); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleUnrestrictedName_in_entryRuleUnrestrictedName847);
            ruleUnrestrictedName();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getUnrestrictedNameRule()); 
            }
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleUnrestrictedName854); if (state.failed) return ;

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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:436:1: ruleUnrestrictedName : ( ruleIdentifier ) ;
    public final void ruleUnrestrictedName() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:440:2: ( ( ruleIdentifier ) )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:441:1: ( ruleIdentifier )
            {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:441:1: ( ruleIdentifier )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:442:1: ruleIdentifier
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getUnrestrictedNameAccess().getIdentifierParserRuleCall()); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleIdentifier_in_ruleUnrestrictedName880);
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:455:1: entryRuleWildcardTypeRefCS : ruleWildcardTypeRefCS EOF ;
    public final void entryRuleWildcardTypeRefCS() throws RecognitionException {
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:456:1: ( ruleWildcardTypeRefCS EOF )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:457:1: ruleWildcardTypeRefCS EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getWildcardTypeRefCSRule()); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleWildcardTypeRefCS_in_entryRuleWildcardTypeRefCS906);
            ruleWildcardTypeRefCS();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getWildcardTypeRefCSRule()); 
            }
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleWildcardTypeRefCS913); if (state.failed) return ;

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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:464:1: ruleWildcardTypeRefCS : ( ( rule__WildcardTypeRefCS__Group__0 ) ) ;
    public final void ruleWildcardTypeRefCS() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:468:2: ( ( ( rule__WildcardTypeRefCS__Group__0 ) ) )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:469:1: ( ( rule__WildcardTypeRefCS__Group__0 ) )
            {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:469:1: ( ( rule__WildcardTypeRefCS__Group__0 ) )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:470:1: ( rule__WildcardTypeRefCS__Group__0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getWildcardTypeRefCSAccess().getGroup()); 
            }
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:471:1: ( rule__WildcardTypeRefCS__Group__0 )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:471:2: rule__WildcardTypeRefCS__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_rule__WildcardTypeRefCS__Group__0_in_ruleWildcardTypeRefCS939);
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:483:1: entryRuleID : ruleID EOF ;
    public final void entryRuleID() throws RecognitionException {
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:484:1: ( ruleID EOF )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:485:1: ruleID EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIDRule()); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleID_in_entryRuleID966);
            ruleID();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getIDRule()); 
            }
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleID973); if (state.failed) return ;

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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:492:1: ruleID : ( ( rule__ID__Alternatives ) ) ;
    public final void ruleID() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:496:2: ( ( ( rule__ID__Alternatives ) ) )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:497:1: ( ( rule__ID__Alternatives ) )
            {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:497:1: ( ( rule__ID__Alternatives ) )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:498:1: ( rule__ID__Alternatives )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIDAccess().getAlternatives()); 
            }
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:499:1: ( rule__ID__Alternatives )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:499:2: rule__ID__Alternatives
            {
            pushFollow(FollowSets000.FOLLOW_rule__ID__Alternatives_in_ruleID999);
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:511:1: entryRuleIdentifier : ruleIdentifier EOF ;
    public final void entryRuleIdentifier() throws RecognitionException {
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:512:1: ( ruleIdentifier EOF )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:513:1: ruleIdentifier EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdentifierRule()); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleIdentifier_in_entryRuleIdentifier1026);
            ruleIdentifier();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getIdentifierRule()); 
            }
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleIdentifier1033); if (state.failed) return ;

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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:520:1: ruleIdentifier : ( ruleID ) ;
    public final void ruleIdentifier() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:524:2: ( ( ruleID ) )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:525:1: ( ruleID )
            {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:525:1: ( ruleID )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:526:1: ruleID
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getIdentifierAccess().getIDParserRuleCall()); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleID_in_ruleIdentifier1059);
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:539:1: entryRuleLOWER : ruleLOWER EOF ;
    public final void entryRuleLOWER() throws RecognitionException {
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:540:1: ( ruleLOWER EOF )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:541:1: ruleLOWER EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getLOWERRule()); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleLOWER_in_entryRuleLOWER1085);
            ruleLOWER();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getLOWERRule()); 
            }
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleLOWER1092); if (state.failed) return ;

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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:548:1: ruleLOWER : ( RULE_INT ) ;
    public final void ruleLOWER() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:552:2: ( ( RULE_INT ) )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:553:1: ( RULE_INT )
            {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:553:1: ( RULE_INT )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:554:1: RULE_INT
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getLOWERAccess().getINTTerminalRuleCall()); 
            }
            match(input,RULE_INT,FollowSets000.FOLLOW_RULE_INT_in_ruleLOWER1118); if (state.failed) return ;
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:571:1: entryRuleUPPER : ruleUPPER EOF ;
    public final void entryRuleUPPER() throws RecognitionException {
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:572:1: ( ruleUPPER EOF )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:573:1: ruleUPPER EOF
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getUPPERRule()); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleUPPER_in_entryRuleUPPER1148);
            ruleUPPER();

            state._fsp--;
            if (state.failed) return ;
            if ( state.backtracking==0 ) {
               after(grammarAccess.getUPPERRule()); 
            }
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleUPPER1155); if (state.failed) return ;

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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:580:1: ruleUPPER : ( ( rule__UPPER__Alternatives ) ) ;
    public final void ruleUPPER() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:584:2: ( ( ( rule__UPPER__Alternatives ) ) )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:585:1: ( ( rule__UPPER__Alternatives ) )
            {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:585:1: ( ( rule__UPPER__Alternatives ) )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:586:1: ( rule__UPPER__Alternatives )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getUPPERAccess().getAlternatives()); 
            }
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:587:1: ( rule__UPPER__Alternatives )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:587:2: rule__UPPER__Alternatives
            {
            pushFollow(FollowSets000.FOLLOW_rule__UPPER__Alternatives_in_ruleUPPER1181);
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:601:1: rule__MultiplicityCS__Alternatives_1 : ( ( ruleMultiplicityBoundsCS ) | ( ruleMultiplicityStringCS ) );
    public final void rule__MultiplicityCS__Alternatives_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:605:1: ( ( ruleMultiplicityBoundsCS ) | ( ruleMultiplicityStringCS ) )
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==RULE_INT) ) {
                alt1=1;
            }
            else if ( ((LA1_0>=17 && LA1_0<=19)) ) {
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
                    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:606:1: ( ruleMultiplicityBoundsCS )
                    {
                    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:606:1: ( ruleMultiplicityBoundsCS )
                    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:607:1: ruleMultiplicityBoundsCS
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getMultiplicityCSAccess().getMultiplicityBoundsCSParserRuleCall_1_0()); 
                    }
                    pushFollow(FollowSets000.FOLLOW_ruleMultiplicityBoundsCS_in_rule__MultiplicityCS__Alternatives_11219);
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
                    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:612:6: ( ruleMultiplicityStringCS )
                    {
                    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:612:6: ( ruleMultiplicityStringCS )
                    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:613:1: ruleMultiplicityStringCS
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getMultiplicityCSAccess().getMultiplicityStringCSParserRuleCall_1_1()); 
                    }
                    pushFollow(FollowSets000.FOLLOW_ruleMultiplicityStringCS_in_rule__MultiplicityCS__Alternatives_11236);
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:623:1: rule__MultiplicityCS__Alternatives_2 : ( ( '|?' ) | ( ( rule__MultiplicityCS__IsNullFreeAssignment_2_1 ) ) );
    public final void rule__MultiplicityCS__Alternatives_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:627:1: ( ( '|?' ) | ( ( rule__MultiplicityCS__IsNullFreeAssignment_2_1 ) ) )
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==16) ) {
                alt2=1;
            }
            else if ( (LA2_0==29) ) {
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
                    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:628:1: ( '|?' )
                    {
                    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:628:1: ( '|?' )
                    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:629:1: '|?'
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getMultiplicityCSAccess().getVerticalLineQuestionMarkKeyword_2_0()); 
                    }
                    match(input,16,FollowSets000.FOLLOW_16_in_rule__MultiplicityCS__Alternatives_21269); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getMultiplicityCSAccess().getVerticalLineQuestionMarkKeyword_2_0()); 
                    }

                    }


                    }
                    break;
                case 2 :
                    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:636:6: ( ( rule__MultiplicityCS__IsNullFreeAssignment_2_1 ) )
                    {
                    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:636:6: ( ( rule__MultiplicityCS__IsNullFreeAssignment_2_1 ) )
                    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:637:1: ( rule__MultiplicityCS__IsNullFreeAssignment_2_1 )
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getMultiplicityCSAccess().getIsNullFreeAssignment_2_1()); 
                    }
                    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:638:1: ( rule__MultiplicityCS__IsNullFreeAssignment_2_1 )
                    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:638:2: rule__MultiplicityCS__IsNullFreeAssignment_2_1
                    {
                    pushFollow(FollowSets000.FOLLOW_rule__MultiplicityCS__IsNullFreeAssignment_2_1_in_rule__MultiplicityCS__Alternatives_21288);
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:647:1: rule__MultiplicityStringCS__StringBoundsAlternatives_0 : ( ( '*' ) | ( '+' ) | ( '?' ) );
    public final void rule__MultiplicityStringCS__StringBoundsAlternatives_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:651:1: ( ( '*' ) | ( '+' ) | ( '?' ) )
            int alt3=3;
            switch ( input.LA(1) ) {
            case 17:
                {
                alt3=1;
                }
                break;
            case 18:
                {
                alt3=2;
                }
                break;
            case 19:
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
                    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:652:1: ( '*' )
                    {
                    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:652:1: ( '*' )
                    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:653:1: '*'
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getMultiplicityStringCSAccess().getStringBoundsAsteriskKeyword_0_0()); 
                    }
                    match(input,17,FollowSets000.FOLLOW_17_in_rule__MultiplicityStringCS__StringBoundsAlternatives_01322); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getMultiplicityStringCSAccess().getStringBoundsAsteriskKeyword_0_0()); 
                    }

                    }


                    }
                    break;
                case 2 :
                    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:660:6: ( '+' )
                    {
                    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:660:6: ( '+' )
                    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:661:1: '+'
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getMultiplicityStringCSAccess().getStringBoundsPlusSignKeyword_0_1()); 
                    }
                    match(input,18,FollowSets000.FOLLOW_18_in_rule__MultiplicityStringCS__StringBoundsAlternatives_01342); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getMultiplicityStringCSAccess().getStringBoundsPlusSignKeyword_0_1()); 
                    }

                    }


                    }
                    break;
                case 3 :
                    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:668:6: ( '?' )
                    {
                    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:668:6: ( '?' )
                    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:669:1: '?'
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getMultiplicityStringCSAccess().getStringBoundsQuestionMarkKeyword_0_2()); 
                    }
                    match(input,19,FollowSets000.FOLLOW_19_in_rule__MultiplicityStringCS__StringBoundsAlternatives_01362); if (state.failed) return ;
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:681:1: rule__TypeRefCS__Alternatives : ( ( ruleTypedRefCS ) | ( ruleWildcardTypeRefCS ) );
    public final void rule__TypeRefCS__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:685:1: ( ( ruleTypedRefCS ) | ( ruleWildcardTypeRefCS ) )
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( ((LA4_0>=RULE_SIMPLE_ID && LA4_0<=RULE_ESCAPED_ID)) ) {
                alt4=1;
            }
            else if ( (LA4_0==19) ) {
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
                    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:686:1: ( ruleTypedRefCS )
                    {
                    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:686:1: ( ruleTypedRefCS )
                    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:687:1: ruleTypedRefCS
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getTypeRefCSAccess().getTypedRefCSParserRuleCall_0()); 
                    }
                    pushFollow(FollowSets000.FOLLOW_ruleTypedRefCS_in_rule__TypeRefCS__Alternatives1396);
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
                    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:692:6: ( ruleWildcardTypeRefCS )
                    {
                    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:692:6: ( ruleWildcardTypeRefCS )
                    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:693:1: ruleWildcardTypeRefCS
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getTypeRefCSAccess().getWildcardTypeRefCSParserRuleCall_1()); 
                    }
                    pushFollow(FollowSets000.FOLLOW_ruleWildcardTypeRefCS_in_rule__TypeRefCS__Alternatives1413);
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:703:1: rule__ID__Alternatives : ( ( RULE_SIMPLE_ID ) | ( RULE_ESCAPED_ID ) );
    public final void rule__ID__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:707:1: ( ( RULE_SIMPLE_ID ) | ( RULE_ESCAPED_ID ) )
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
                    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:708:1: ( RULE_SIMPLE_ID )
                    {
                    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:708:1: ( RULE_SIMPLE_ID )
                    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:709:1: RULE_SIMPLE_ID
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getIDAccess().getSIMPLE_IDTerminalRuleCall_0()); 
                    }
                    match(input,RULE_SIMPLE_ID,FollowSets000.FOLLOW_RULE_SIMPLE_ID_in_rule__ID__Alternatives1445); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getIDAccess().getSIMPLE_IDTerminalRuleCall_0()); 
                    }

                    }


                    }
                    break;
                case 2 :
                    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:714:6: ( RULE_ESCAPED_ID )
                    {
                    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:714:6: ( RULE_ESCAPED_ID )
                    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:715:1: RULE_ESCAPED_ID
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getIDAccess().getESCAPED_IDTerminalRuleCall_1()); 
                    }
                    match(input,RULE_ESCAPED_ID,FollowSets000.FOLLOW_RULE_ESCAPED_ID_in_rule__ID__Alternatives1462); if (state.failed) return ;
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:725:1: rule__UPPER__Alternatives : ( ( RULE_INT ) | ( '*' ) );
    public final void rule__UPPER__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:729:1: ( ( RULE_INT ) | ( '*' ) )
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==RULE_INT) ) {
                alt6=1;
            }
            else if ( (LA6_0==17) ) {
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
                    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:730:1: ( RULE_INT )
                    {
                    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:730:1: ( RULE_INT )
                    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:731:1: RULE_INT
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getUPPERAccess().getINTTerminalRuleCall_0()); 
                    }
                    match(input,RULE_INT,FollowSets000.FOLLOW_RULE_INT_in_rule__UPPER__Alternatives1494); if (state.failed) return ;
                    if ( state.backtracking==0 ) {
                       after(grammarAccess.getUPPERAccess().getINTTerminalRuleCall_0()); 
                    }

                    }


                    }
                    break;
                case 2 :
                    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:736:6: ( '*' )
                    {
                    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:736:6: ( '*' )
                    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:737:1: '*'
                    {
                    if ( state.backtracking==0 ) {
                       before(grammarAccess.getUPPERAccess().getAsteriskKeyword_1()); 
                    }
                    match(input,17,FollowSets000.FOLLOW_17_in_rule__UPPER__Alternatives1512); if (state.failed) return ;
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:751:1: rule__MultiplicityBoundsCS__Group__0 : rule__MultiplicityBoundsCS__Group__0__Impl rule__MultiplicityBoundsCS__Group__1 ;
    public final void rule__MultiplicityBoundsCS__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:755:1: ( rule__MultiplicityBoundsCS__Group__0__Impl rule__MultiplicityBoundsCS__Group__1 )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:756:2: rule__MultiplicityBoundsCS__Group__0__Impl rule__MultiplicityBoundsCS__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_rule__MultiplicityBoundsCS__Group__0__Impl_in_rule__MultiplicityBoundsCS__Group__01544);
            rule__MultiplicityBoundsCS__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_rule__MultiplicityBoundsCS__Group__1_in_rule__MultiplicityBoundsCS__Group__01547);
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:763:1: rule__MultiplicityBoundsCS__Group__0__Impl : ( ( rule__MultiplicityBoundsCS__LowerBoundAssignment_0 ) ) ;
    public final void rule__MultiplicityBoundsCS__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:767:1: ( ( ( rule__MultiplicityBoundsCS__LowerBoundAssignment_0 ) ) )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:768:1: ( ( rule__MultiplicityBoundsCS__LowerBoundAssignment_0 ) )
            {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:768:1: ( ( rule__MultiplicityBoundsCS__LowerBoundAssignment_0 ) )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:769:1: ( rule__MultiplicityBoundsCS__LowerBoundAssignment_0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getMultiplicityBoundsCSAccess().getLowerBoundAssignment_0()); 
            }
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:770:1: ( rule__MultiplicityBoundsCS__LowerBoundAssignment_0 )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:770:2: rule__MultiplicityBoundsCS__LowerBoundAssignment_0
            {
            pushFollow(FollowSets000.FOLLOW_rule__MultiplicityBoundsCS__LowerBoundAssignment_0_in_rule__MultiplicityBoundsCS__Group__0__Impl1574);
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:780:1: rule__MultiplicityBoundsCS__Group__1 : rule__MultiplicityBoundsCS__Group__1__Impl ;
    public final void rule__MultiplicityBoundsCS__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:784:1: ( rule__MultiplicityBoundsCS__Group__1__Impl )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:785:2: rule__MultiplicityBoundsCS__Group__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_rule__MultiplicityBoundsCS__Group__1__Impl_in_rule__MultiplicityBoundsCS__Group__11604);
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:791:1: rule__MultiplicityBoundsCS__Group__1__Impl : ( ( rule__MultiplicityBoundsCS__Group_1__0 )? ) ;
    public final void rule__MultiplicityBoundsCS__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:795:1: ( ( ( rule__MultiplicityBoundsCS__Group_1__0 )? ) )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:796:1: ( ( rule__MultiplicityBoundsCS__Group_1__0 )? )
            {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:796:1: ( ( rule__MultiplicityBoundsCS__Group_1__0 )? )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:797:1: ( rule__MultiplicityBoundsCS__Group_1__0 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getMultiplicityBoundsCSAccess().getGroup_1()); 
            }
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:798:1: ( rule__MultiplicityBoundsCS__Group_1__0 )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==20) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:798:2: rule__MultiplicityBoundsCS__Group_1__0
                    {
                    pushFollow(FollowSets000.FOLLOW_rule__MultiplicityBoundsCS__Group_1__0_in_rule__MultiplicityBoundsCS__Group__1__Impl1631);
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:812:1: rule__MultiplicityBoundsCS__Group_1__0 : rule__MultiplicityBoundsCS__Group_1__0__Impl rule__MultiplicityBoundsCS__Group_1__1 ;
    public final void rule__MultiplicityBoundsCS__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:816:1: ( rule__MultiplicityBoundsCS__Group_1__0__Impl rule__MultiplicityBoundsCS__Group_1__1 )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:817:2: rule__MultiplicityBoundsCS__Group_1__0__Impl rule__MultiplicityBoundsCS__Group_1__1
            {
            pushFollow(FollowSets000.FOLLOW_rule__MultiplicityBoundsCS__Group_1__0__Impl_in_rule__MultiplicityBoundsCS__Group_1__01666);
            rule__MultiplicityBoundsCS__Group_1__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_rule__MultiplicityBoundsCS__Group_1__1_in_rule__MultiplicityBoundsCS__Group_1__01669);
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:824:1: rule__MultiplicityBoundsCS__Group_1__0__Impl : ( '..' ) ;
    public final void rule__MultiplicityBoundsCS__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:828:1: ( ( '..' ) )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:829:1: ( '..' )
            {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:829:1: ( '..' )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:830:1: '..'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getMultiplicityBoundsCSAccess().getFullStopFullStopKeyword_1_0()); 
            }
            match(input,20,FollowSets000.FOLLOW_20_in_rule__MultiplicityBoundsCS__Group_1__0__Impl1697); if (state.failed) return ;
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:843:1: rule__MultiplicityBoundsCS__Group_1__1 : rule__MultiplicityBoundsCS__Group_1__1__Impl ;
    public final void rule__MultiplicityBoundsCS__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:847:1: ( rule__MultiplicityBoundsCS__Group_1__1__Impl )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:848:2: rule__MultiplicityBoundsCS__Group_1__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_rule__MultiplicityBoundsCS__Group_1__1__Impl_in_rule__MultiplicityBoundsCS__Group_1__11728);
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:854:1: rule__MultiplicityBoundsCS__Group_1__1__Impl : ( ( rule__MultiplicityBoundsCS__UpperBoundAssignment_1_1 ) ) ;
    public final void rule__MultiplicityBoundsCS__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:858:1: ( ( ( rule__MultiplicityBoundsCS__UpperBoundAssignment_1_1 ) ) )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:859:1: ( ( rule__MultiplicityBoundsCS__UpperBoundAssignment_1_1 ) )
            {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:859:1: ( ( rule__MultiplicityBoundsCS__UpperBoundAssignment_1_1 ) )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:860:1: ( rule__MultiplicityBoundsCS__UpperBoundAssignment_1_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getMultiplicityBoundsCSAccess().getUpperBoundAssignment_1_1()); 
            }
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:861:1: ( rule__MultiplicityBoundsCS__UpperBoundAssignment_1_1 )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:861:2: rule__MultiplicityBoundsCS__UpperBoundAssignment_1_1
            {
            pushFollow(FollowSets000.FOLLOW_rule__MultiplicityBoundsCS__UpperBoundAssignment_1_1_in_rule__MultiplicityBoundsCS__Group_1__1__Impl1755);
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:875:1: rule__MultiplicityCS__Group__0 : rule__MultiplicityCS__Group__0__Impl rule__MultiplicityCS__Group__1 ;
    public final void rule__MultiplicityCS__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:879:1: ( rule__MultiplicityCS__Group__0__Impl rule__MultiplicityCS__Group__1 )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:880:2: rule__MultiplicityCS__Group__0__Impl rule__MultiplicityCS__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_rule__MultiplicityCS__Group__0__Impl_in_rule__MultiplicityCS__Group__01789);
            rule__MultiplicityCS__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_rule__MultiplicityCS__Group__1_in_rule__MultiplicityCS__Group__01792);
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:887:1: rule__MultiplicityCS__Group__0__Impl : ( '[' ) ;
    public final void rule__MultiplicityCS__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:891:1: ( ( '[' ) )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:892:1: ( '[' )
            {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:892:1: ( '[' )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:893:1: '['
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getMultiplicityCSAccess().getLeftSquareBracketKeyword_0()); 
            }
            match(input,21,FollowSets000.FOLLOW_21_in_rule__MultiplicityCS__Group__0__Impl1820); if (state.failed) return ;
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:906:1: rule__MultiplicityCS__Group__1 : rule__MultiplicityCS__Group__1__Impl rule__MultiplicityCS__Group__2 ;
    public final void rule__MultiplicityCS__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:910:1: ( rule__MultiplicityCS__Group__1__Impl rule__MultiplicityCS__Group__2 )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:911:2: rule__MultiplicityCS__Group__1__Impl rule__MultiplicityCS__Group__2
            {
            pushFollow(FollowSets000.FOLLOW_rule__MultiplicityCS__Group__1__Impl_in_rule__MultiplicityCS__Group__11851);
            rule__MultiplicityCS__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_rule__MultiplicityCS__Group__2_in_rule__MultiplicityCS__Group__11854);
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:918:1: rule__MultiplicityCS__Group__1__Impl : ( ( rule__MultiplicityCS__Alternatives_1 ) ) ;
    public final void rule__MultiplicityCS__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:922:1: ( ( ( rule__MultiplicityCS__Alternatives_1 ) ) )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:923:1: ( ( rule__MultiplicityCS__Alternatives_1 ) )
            {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:923:1: ( ( rule__MultiplicityCS__Alternatives_1 ) )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:924:1: ( rule__MultiplicityCS__Alternatives_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getMultiplicityCSAccess().getAlternatives_1()); 
            }
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:925:1: ( rule__MultiplicityCS__Alternatives_1 )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:925:2: rule__MultiplicityCS__Alternatives_1
            {
            pushFollow(FollowSets000.FOLLOW_rule__MultiplicityCS__Alternatives_1_in_rule__MultiplicityCS__Group__1__Impl1881);
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:935:1: rule__MultiplicityCS__Group__2 : rule__MultiplicityCS__Group__2__Impl rule__MultiplicityCS__Group__3 ;
    public final void rule__MultiplicityCS__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:939:1: ( rule__MultiplicityCS__Group__2__Impl rule__MultiplicityCS__Group__3 )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:940:2: rule__MultiplicityCS__Group__2__Impl rule__MultiplicityCS__Group__3
            {
            pushFollow(FollowSets000.FOLLOW_rule__MultiplicityCS__Group__2__Impl_in_rule__MultiplicityCS__Group__21911);
            rule__MultiplicityCS__Group__2__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_rule__MultiplicityCS__Group__3_in_rule__MultiplicityCS__Group__21914);
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:947:1: rule__MultiplicityCS__Group__2__Impl : ( ( rule__MultiplicityCS__Alternatives_2 )? ) ;
    public final void rule__MultiplicityCS__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:951:1: ( ( ( rule__MultiplicityCS__Alternatives_2 )? ) )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:952:1: ( ( rule__MultiplicityCS__Alternatives_2 )? )
            {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:952:1: ( ( rule__MultiplicityCS__Alternatives_2 )? )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:953:1: ( rule__MultiplicityCS__Alternatives_2 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getMultiplicityCSAccess().getAlternatives_2()); 
            }
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:954:1: ( rule__MultiplicityCS__Alternatives_2 )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==16||LA8_0==29) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:954:2: rule__MultiplicityCS__Alternatives_2
                    {
                    pushFollow(FollowSets000.FOLLOW_rule__MultiplicityCS__Alternatives_2_in_rule__MultiplicityCS__Group__2__Impl1941);
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:964:1: rule__MultiplicityCS__Group__3 : rule__MultiplicityCS__Group__3__Impl ;
    public final void rule__MultiplicityCS__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:968:1: ( rule__MultiplicityCS__Group__3__Impl )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:969:2: rule__MultiplicityCS__Group__3__Impl
            {
            pushFollow(FollowSets000.FOLLOW_rule__MultiplicityCS__Group__3__Impl_in_rule__MultiplicityCS__Group__31972);
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:975:1: rule__MultiplicityCS__Group__3__Impl : ( ']' ) ;
    public final void rule__MultiplicityCS__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:979:1: ( ( ']' ) )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:980:1: ( ']' )
            {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:980:1: ( ']' )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:981:1: ']'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getMultiplicityCSAccess().getRightSquareBracketKeyword_3()); 
            }
            match(input,22,FollowSets000.FOLLOW_22_in_rule__MultiplicityCS__Group__3__Impl2000); if (state.failed) return ;
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1002:1: rule__PathNameCS__Group__0 : rule__PathNameCS__Group__0__Impl rule__PathNameCS__Group__1 ;
    public final void rule__PathNameCS__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1006:1: ( rule__PathNameCS__Group__0__Impl rule__PathNameCS__Group__1 )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1007:2: rule__PathNameCS__Group__0__Impl rule__PathNameCS__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_rule__PathNameCS__Group__0__Impl_in_rule__PathNameCS__Group__02039);
            rule__PathNameCS__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_rule__PathNameCS__Group__1_in_rule__PathNameCS__Group__02042);
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1014:1: rule__PathNameCS__Group__0__Impl : ( ( rule__PathNameCS__OwnedPathElementsAssignment_0 ) ) ;
    public final void rule__PathNameCS__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1018:1: ( ( ( rule__PathNameCS__OwnedPathElementsAssignment_0 ) ) )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1019:1: ( ( rule__PathNameCS__OwnedPathElementsAssignment_0 ) )
            {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1019:1: ( ( rule__PathNameCS__OwnedPathElementsAssignment_0 ) )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1020:1: ( rule__PathNameCS__OwnedPathElementsAssignment_0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getPathNameCSAccess().getOwnedPathElementsAssignment_0()); 
            }
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1021:1: ( rule__PathNameCS__OwnedPathElementsAssignment_0 )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1021:2: rule__PathNameCS__OwnedPathElementsAssignment_0
            {
            pushFollow(FollowSets000.FOLLOW_rule__PathNameCS__OwnedPathElementsAssignment_0_in_rule__PathNameCS__Group__0__Impl2069);
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1031:1: rule__PathNameCS__Group__1 : rule__PathNameCS__Group__1__Impl ;
    public final void rule__PathNameCS__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1035:1: ( rule__PathNameCS__Group__1__Impl )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1036:2: rule__PathNameCS__Group__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_rule__PathNameCS__Group__1__Impl_in_rule__PathNameCS__Group__12099);
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1042:1: rule__PathNameCS__Group__1__Impl : ( ( rule__PathNameCS__Group_1__0 )* ) ;
    public final void rule__PathNameCS__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1046:1: ( ( ( rule__PathNameCS__Group_1__0 )* ) )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1047:1: ( ( rule__PathNameCS__Group_1__0 )* )
            {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1047:1: ( ( rule__PathNameCS__Group_1__0 )* )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1048:1: ( rule__PathNameCS__Group_1__0 )*
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getPathNameCSAccess().getGroup_1()); 
            }
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1049:1: ( rule__PathNameCS__Group_1__0 )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==23) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1049:2: rule__PathNameCS__Group_1__0
            	    {
            	    pushFollow(FollowSets000.FOLLOW_rule__PathNameCS__Group_1__0_in_rule__PathNameCS__Group__1__Impl2126);
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1063:1: rule__PathNameCS__Group_1__0 : rule__PathNameCS__Group_1__0__Impl rule__PathNameCS__Group_1__1 ;
    public final void rule__PathNameCS__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1067:1: ( rule__PathNameCS__Group_1__0__Impl rule__PathNameCS__Group_1__1 )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1068:2: rule__PathNameCS__Group_1__0__Impl rule__PathNameCS__Group_1__1
            {
            pushFollow(FollowSets000.FOLLOW_rule__PathNameCS__Group_1__0__Impl_in_rule__PathNameCS__Group_1__02161);
            rule__PathNameCS__Group_1__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_rule__PathNameCS__Group_1__1_in_rule__PathNameCS__Group_1__02164);
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1075:1: rule__PathNameCS__Group_1__0__Impl : ( '::' ) ;
    public final void rule__PathNameCS__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1079:1: ( ( '::' ) )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1080:1: ( '::' )
            {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1080:1: ( '::' )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1081:1: '::'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getPathNameCSAccess().getColonColonKeyword_1_0()); 
            }
            match(input,23,FollowSets000.FOLLOW_23_in_rule__PathNameCS__Group_1__0__Impl2192); if (state.failed) return ;
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1094:1: rule__PathNameCS__Group_1__1 : rule__PathNameCS__Group_1__1__Impl ;
    public final void rule__PathNameCS__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1098:1: ( rule__PathNameCS__Group_1__1__Impl )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1099:2: rule__PathNameCS__Group_1__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_rule__PathNameCS__Group_1__1__Impl_in_rule__PathNameCS__Group_1__12223);
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1105:1: rule__PathNameCS__Group_1__1__Impl : ( ( rule__PathNameCS__OwnedPathElementsAssignment_1_1 ) ) ;
    public final void rule__PathNameCS__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1109:1: ( ( ( rule__PathNameCS__OwnedPathElementsAssignment_1_1 ) ) )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1110:1: ( ( rule__PathNameCS__OwnedPathElementsAssignment_1_1 ) )
            {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1110:1: ( ( rule__PathNameCS__OwnedPathElementsAssignment_1_1 ) )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1111:1: ( rule__PathNameCS__OwnedPathElementsAssignment_1_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getPathNameCSAccess().getOwnedPathElementsAssignment_1_1()); 
            }
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1112:1: ( rule__PathNameCS__OwnedPathElementsAssignment_1_1 )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1112:2: rule__PathNameCS__OwnedPathElementsAssignment_1_1
            {
            pushFollow(FollowSets000.FOLLOW_rule__PathNameCS__OwnedPathElementsAssignment_1_1_in_rule__PathNameCS__Group_1__1__Impl2250);
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1126:1: rule__TemplateBindingCS__Group__0 : rule__TemplateBindingCS__Group__0__Impl rule__TemplateBindingCS__Group__1 ;
    public final void rule__TemplateBindingCS__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1130:1: ( rule__TemplateBindingCS__Group__0__Impl rule__TemplateBindingCS__Group__1 )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1131:2: rule__TemplateBindingCS__Group__0__Impl rule__TemplateBindingCS__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_rule__TemplateBindingCS__Group__0__Impl_in_rule__TemplateBindingCS__Group__02284);
            rule__TemplateBindingCS__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_rule__TemplateBindingCS__Group__1_in_rule__TemplateBindingCS__Group__02287);
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1138:1: rule__TemplateBindingCS__Group__0__Impl : ( ( rule__TemplateBindingCS__OwnedSubstitutionsAssignment_0 ) ) ;
    public final void rule__TemplateBindingCS__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1142:1: ( ( ( rule__TemplateBindingCS__OwnedSubstitutionsAssignment_0 ) ) )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1143:1: ( ( rule__TemplateBindingCS__OwnedSubstitutionsAssignment_0 ) )
            {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1143:1: ( ( rule__TemplateBindingCS__OwnedSubstitutionsAssignment_0 ) )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1144:1: ( rule__TemplateBindingCS__OwnedSubstitutionsAssignment_0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTemplateBindingCSAccess().getOwnedSubstitutionsAssignment_0()); 
            }
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1145:1: ( rule__TemplateBindingCS__OwnedSubstitutionsAssignment_0 )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1145:2: rule__TemplateBindingCS__OwnedSubstitutionsAssignment_0
            {
            pushFollow(FollowSets000.FOLLOW_rule__TemplateBindingCS__OwnedSubstitutionsAssignment_0_in_rule__TemplateBindingCS__Group__0__Impl2314);
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1155:1: rule__TemplateBindingCS__Group__1 : rule__TemplateBindingCS__Group__1__Impl rule__TemplateBindingCS__Group__2 ;
    public final void rule__TemplateBindingCS__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1159:1: ( rule__TemplateBindingCS__Group__1__Impl rule__TemplateBindingCS__Group__2 )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1160:2: rule__TemplateBindingCS__Group__1__Impl rule__TemplateBindingCS__Group__2
            {
            pushFollow(FollowSets000.FOLLOW_rule__TemplateBindingCS__Group__1__Impl_in_rule__TemplateBindingCS__Group__12344);
            rule__TemplateBindingCS__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_rule__TemplateBindingCS__Group__2_in_rule__TemplateBindingCS__Group__12347);
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1167:1: rule__TemplateBindingCS__Group__1__Impl : ( ( rule__TemplateBindingCS__Group_1__0 )* ) ;
    public final void rule__TemplateBindingCS__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1171:1: ( ( ( rule__TemplateBindingCS__Group_1__0 )* ) )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1172:1: ( ( rule__TemplateBindingCS__Group_1__0 )* )
            {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1172:1: ( ( rule__TemplateBindingCS__Group_1__0 )* )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1173:1: ( rule__TemplateBindingCS__Group_1__0 )*
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTemplateBindingCSAccess().getGroup_1()); 
            }
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1174:1: ( rule__TemplateBindingCS__Group_1__0 )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( (LA10_0==24) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1174:2: rule__TemplateBindingCS__Group_1__0
            	    {
            	    pushFollow(FollowSets000.FOLLOW_rule__TemplateBindingCS__Group_1__0_in_rule__TemplateBindingCS__Group__1__Impl2374);
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1184:1: rule__TemplateBindingCS__Group__2 : rule__TemplateBindingCS__Group__2__Impl ;
    public final void rule__TemplateBindingCS__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1188:1: ( rule__TemplateBindingCS__Group__2__Impl )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1189:2: rule__TemplateBindingCS__Group__2__Impl
            {
            pushFollow(FollowSets000.FOLLOW_rule__TemplateBindingCS__Group__2__Impl_in_rule__TemplateBindingCS__Group__22405);
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1195:1: rule__TemplateBindingCS__Group__2__Impl : ( ( rule__TemplateBindingCS__OwnedMultiplicityAssignment_2 )? ) ;
    public final void rule__TemplateBindingCS__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1199:1: ( ( ( rule__TemplateBindingCS__OwnedMultiplicityAssignment_2 )? ) )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1200:1: ( ( rule__TemplateBindingCS__OwnedMultiplicityAssignment_2 )? )
            {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1200:1: ( ( rule__TemplateBindingCS__OwnedMultiplicityAssignment_2 )? )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1201:1: ( rule__TemplateBindingCS__OwnedMultiplicityAssignment_2 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTemplateBindingCSAccess().getOwnedMultiplicityAssignment_2()); 
            }
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1202:1: ( rule__TemplateBindingCS__OwnedMultiplicityAssignment_2 )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==21) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1202:2: rule__TemplateBindingCS__OwnedMultiplicityAssignment_2
                    {
                    pushFollow(FollowSets000.FOLLOW_rule__TemplateBindingCS__OwnedMultiplicityAssignment_2_in_rule__TemplateBindingCS__Group__2__Impl2432);
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1218:1: rule__TemplateBindingCS__Group_1__0 : rule__TemplateBindingCS__Group_1__0__Impl rule__TemplateBindingCS__Group_1__1 ;
    public final void rule__TemplateBindingCS__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1222:1: ( rule__TemplateBindingCS__Group_1__0__Impl rule__TemplateBindingCS__Group_1__1 )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1223:2: rule__TemplateBindingCS__Group_1__0__Impl rule__TemplateBindingCS__Group_1__1
            {
            pushFollow(FollowSets000.FOLLOW_rule__TemplateBindingCS__Group_1__0__Impl_in_rule__TemplateBindingCS__Group_1__02469);
            rule__TemplateBindingCS__Group_1__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_rule__TemplateBindingCS__Group_1__1_in_rule__TemplateBindingCS__Group_1__02472);
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1230:1: rule__TemplateBindingCS__Group_1__0__Impl : ( ',' ) ;
    public final void rule__TemplateBindingCS__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1234:1: ( ( ',' ) )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1235:1: ( ',' )
            {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1235:1: ( ',' )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1236:1: ','
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTemplateBindingCSAccess().getCommaKeyword_1_0()); 
            }
            match(input,24,FollowSets000.FOLLOW_24_in_rule__TemplateBindingCS__Group_1__0__Impl2500); if (state.failed) return ;
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1249:1: rule__TemplateBindingCS__Group_1__1 : rule__TemplateBindingCS__Group_1__1__Impl ;
    public final void rule__TemplateBindingCS__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1253:1: ( rule__TemplateBindingCS__Group_1__1__Impl )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1254:2: rule__TemplateBindingCS__Group_1__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_rule__TemplateBindingCS__Group_1__1__Impl_in_rule__TemplateBindingCS__Group_1__12531);
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1260:1: rule__TemplateBindingCS__Group_1__1__Impl : ( ( rule__TemplateBindingCS__OwnedSubstitutionsAssignment_1_1 ) ) ;
    public final void rule__TemplateBindingCS__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1264:1: ( ( ( rule__TemplateBindingCS__OwnedSubstitutionsAssignment_1_1 ) ) )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1265:1: ( ( rule__TemplateBindingCS__OwnedSubstitutionsAssignment_1_1 ) )
            {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1265:1: ( ( rule__TemplateBindingCS__OwnedSubstitutionsAssignment_1_1 ) )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1266:1: ( rule__TemplateBindingCS__OwnedSubstitutionsAssignment_1_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTemplateBindingCSAccess().getOwnedSubstitutionsAssignment_1_1()); 
            }
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1267:1: ( rule__TemplateBindingCS__OwnedSubstitutionsAssignment_1_1 )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1267:2: rule__TemplateBindingCS__OwnedSubstitutionsAssignment_1_1
            {
            pushFollow(FollowSets000.FOLLOW_rule__TemplateBindingCS__OwnedSubstitutionsAssignment_1_1_in_rule__TemplateBindingCS__Group_1__1__Impl2558);
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1283:1: rule__TypeParameterCS__Group__0 : rule__TypeParameterCS__Group__0__Impl rule__TypeParameterCS__Group__1 ;
    public final void rule__TypeParameterCS__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1287:1: ( rule__TypeParameterCS__Group__0__Impl rule__TypeParameterCS__Group__1 )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1288:2: rule__TypeParameterCS__Group__0__Impl rule__TypeParameterCS__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_rule__TypeParameterCS__Group__0__Impl_in_rule__TypeParameterCS__Group__02594);
            rule__TypeParameterCS__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_rule__TypeParameterCS__Group__1_in_rule__TypeParameterCS__Group__02597);
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1295:1: rule__TypeParameterCS__Group__0__Impl : ( ( rule__TypeParameterCS__NameAssignment_0 ) ) ;
    public final void rule__TypeParameterCS__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1299:1: ( ( ( rule__TypeParameterCS__NameAssignment_0 ) ) )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1300:1: ( ( rule__TypeParameterCS__NameAssignment_0 ) )
            {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1300:1: ( ( rule__TypeParameterCS__NameAssignment_0 ) )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1301:1: ( rule__TypeParameterCS__NameAssignment_0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTypeParameterCSAccess().getNameAssignment_0()); 
            }
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1302:1: ( rule__TypeParameterCS__NameAssignment_0 )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1302:2: rule__TypeParameterCS__NameAssignment_0
            {
            pushFollow(FollowSets000.FOLLOW_rule__TypeParameterCS__NameAssignment_0_in_rule__TypeParameterCS__Group__0__Impl2624);
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1312:1: rule__TypeParameterCS__Group__1 : rule__TypeParameterCS__Group__1__Impl ;
    public final void rule__TypeParameterCS__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1316:1: ( rule__TypeParameterCS__Group__1__Impl )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1317:2: rule__TypeParameterCS__Group__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_rule__TypeParameterCS__Group__1__Impl_in_rule__TypeParameterCS__Group__12654);
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1323:1: rule__TypeParameterCS__Group__1__Impl : ( ( rule__TypeParameterCS__Group_1__0 )? ) ;
    public final void rule__TypeParameterCS__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1327:1: ( ( ( rule__TypeParameterCS__Group_1__0 )? ) )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1328:1: ( ( rule__TypeParameterCS__Group_1__0 )? )
            {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1328:1: ( ( rule__TypeParameterCS__Group_1__0 )? )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1329:1: ( rule__TypeParameterCS__Group_1__0 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTypeParameterCSAccess().getGroup_1()); 
            }
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1330:1: ( rule__TypeParameterCS__Group_1__0 )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==25) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1330:2: rule__TypeParameterCS__Group_1__0
                    {
                    pushFollow(FollowSets000.FOLLOW_rule__TypeParameterCS__Group_1__0_in_rule__TypeParameterCS__Group__1__Impl2681);
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1344:1: rule__TypeParameterCS__Group_1__0 : rule__TypeParameterCS__Group_1__0__Impl rule__TypeParameterCS__Group_1__1 ;
    public final void rule__TypeParameterCS__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1348:1: ( rule__TypeParameterCS__Group_1__0__Impl rule__TypeParameterCS__Group_1__1 )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1349:2: rule__TypeParameterCS__Group_1__0__Impl rule__TypeParameterCS__Group_1__1
            {
            pushFollow(FollowSets000.FOLLOW_rule__TypeParameterCS__Group_1__0__Impl_in_rule__TypeParameterCS__Group_1__02716);
            rule__TypeParameterCS__Group_1__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_rule__TypeParameterCS__Group_1__1_in_rule__TypeParameterCS__Group_1__02719);
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1356:1: rule__TypeParameterCS__Group_1__0__Impl : ( 'extends' ) ;
    public final void rule__TypeParameterCS__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1360:1: ( ( 'extends' ) )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1361:1: ( 'extends' )
            {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1361:1: ( 'extends' )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1362:1: 'extends'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTypeParameterCSAccess().getExtendsKeyword_1_0()); 
            }
            match(input,25,FollowSets000.FOLLOW_25_in_rule__TypeParameterCS__Group_1__0__Impl2747); if (state.failed) return ;
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1375:1: rule__TypeParameterCS__Group_1__1 : rule__TypeParameterCS__Group_1__1__Impl rule__TypeParameterCS__Group_1__2 ;
    public final void rule__TypeParameterCS__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1379:1: ( rule__TypeParameterCS__Group_1__1__Impl rule__TypeParameterCS__Group_1__2 )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1380:2: rule__TypeParameterCS__Group_1__1__Impl rule__TypeParameterCS__Group_1__2
            {
            pushFollow(FollowSets000.FOLLOW_rule__TypeParameterCS__Group_1__1__Impl_in_rule__TypeParameterCS__Group_1__12778);
            rule__TypeParameterCS__Group_1__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_rule__TypeParameterCS__Group_1__2_in_rule__TypeParameterCS__Group_1__12781);
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1387:1: rule__TypeParameterCS__Group_1__1__Impl : ( ( rule__TypeParameterCS__OwnedExtendsAssignment_1_1 ) ) ;
    public final void rule__TypeParameterCS__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1391:1: ( ( ( rule__TypeParameterCS__OwnedExtendsAssignment_1_1 ) ) )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1392:1: ( ( rule__TypeParameterCS__OwnedExtendsAssignment_1_1 ) )
            {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1392:1: ( ( rule__TypeParameterCS__OwnedExtendsAssignment_1_1 ) )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1393:1: ( rule__TypeParameterCS__OwnedExtendsAssignment_1_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTypeParameterCSAccess().getOwnedExtendsAssignment_1_1()); 
            }
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1394:1: ( rule__TypeParameterCS__OwnedExtendsAssignment_1_1 )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1394:2: rule__TypeParameterCS__OwnedExtendsAssignment_1_1
            {
            pushFollow(FollowSets000.FOLLOW_rule__TypeParameterCS__OwnedExtendsAssignment_1_1_in_rule__TypeParameterCS__Group_1__1__Impl2808);
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1404:1: rule__TypeParameterCS__Group_1__2 : rule__TypeParameterCS__Group_1__2__Impl ;
    public final void rule__TypeParameterCS__Group_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1408:1: ( rule__TypeParameterCS__Group_1__2__Impl )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1409:2: rule__TypeParameterCS__Group_1__2__Impl
            {
            pushFollow(FollowSets000.FOLLOW_rule__TypeParameterCS__Group_1__2__Impl_in_rule__TypeParameterCS__Group_1__22838);
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1415:1: rule__TypeParameterCS__Group_1__2__Impl : ( ( rule__TypeParameterCS__Group_1_2__0 )* ) ;
    public final void rule__TypeParameterCS__Group_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1419:1: ( ( ( rule__TypeParameterCS__Group_1_2__0 )* ) )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1420:1: ( ( rule__TypeParameterCS__Group_1_2__0 )* )
            {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1420:1: ( ( rule__TypeParameterCS__Group_1_2__0 )* )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1421:1: ( rule__TypeParameterCS__Group_1_2__0 )*
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTypeParameterCSAccess().getGroup_1_2()); 
            }
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1422:1: ( rule__TypeParameterCS__Group_1_2__0 )*
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( (LA13_0==26) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1422:2: rule__TypeParameterCS__Group_1_2__0
            	    {
            	    pushFollow(FollowSets000.FOLLOW_rule__TypeParameterCS__Group_1_2__0_in_rule__TypeParameterCS__Group_1__2__Impl2865);
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1438:1: rule__TypeParameterCS__Group_1_2__0 : rule__TypeParameterCS__Group_1_2__0__Impl rule__TypeParameterCS__Group_1_2__1 ;
    public final void rule__TypeParameterCS__Group_1_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1442:1: ( rule__TypeParameterCS__Group_1_2__0__Impl rule__TypeParameterCS__Group_1_2__1 )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1443:2: rule__TypeParameterCS__Group_1_2__0__Impl rule__TypeParameterCS__Group_1_2__1
            {
            pushFollow(FollowSets000.FOLLOW_rule__TypeParameterCS__Group_1_2__0__Impl_in_rule__TypeParameterCS__Group_1_2__02902);
            rule__TypeParameterCS__Group_1_2__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_rule__TypeParameterCS__Group_1_2__1_in_rule__TypeParameterCS__Group_1_2__02905);
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1450:1: rule__TypeParameterCS__Group_1_2__0__Impl : ( '&&' ) ;
    public final void rule__TypeParameterCS__Group_1_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1454:1: ( ( '&&' ) )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1455:1: ( '&&' )
            {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1455:1: ( '&&' )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1456:1: '&&'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTypeParameterCSAccess().getAmpersandAmpersandKeyword_1_2_0()); 
            }
            match(input,26,FollowSets000.FOLLOW_26_in_rule__TypeParameterCS__Group_1_2__0__Impl2933); if (state.failed) return ;
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1469:1: rule__TypeParameterCS__Group_1_2__1 : rule__TypeParameterCS__Group_1_2__1__Impl ;
    public final void rule__TypeParameterCS__Group_1_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1473:1: ( rule__TypeParameterCS__Group_1_2__1__Impl )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1474:2: rule__TypeParameterCS__Group_1_2__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_rule__TypeParameterCS__Group_1_2__1__Impl_in_rule__TypeParameterCS__Group_1_2__12964);
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1480:1: rule__TypeParameterCS__Group_1_2__1__Impl : ( ( rule__TypeParameterCS__OwnedExtendsAssignment_1_2_1 ) ) ;
    public final void rule__TypeParameterCS__Group_1_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1484:1: ( ( ( rule__TypeParameterCS__OwnedExtendsAssignment_1_2_1 ) ) )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1485:1: ( ( rule__TypeParameterCS__OwnedExtendsAssignment_1_2_1 ) )
            {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1485:1: ( ( rule__TypeParameterCS__OwnedExtendsAssignment_1_2_1 ) )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1486:1: ( rule__TypeParameterCS__OwnedExtendsAssignment_1_2_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTypeParameterCSAccess().getOwnedExtendsAssignment_1_2_1()); 
            }
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1487:1: ( rule__TypeParameterCS__OwnedExtendsAssignment_1_2_1 )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1487:2: rule__TypeParameterCS__OwnedExtendsAssignment_1_2_1
            {
            pushFollow(FollowSets000.FOLLOW_rule__TypeParameterCS__OwnedExtendsAssignment_1_2_1_in_rule__TypeParameterCS__Group_1_2__1__Impl2991);
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1501:1: rule__TypedTypeRefCS__Group__0 : rule__TypedTypeRefCS__Group__0__Impl rule__TypedTypeRefCS__Group__1 ;
    public final void rule__TypedTypeRefCS__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1505:1: ( rule__TypedTypeRefCS__Group__0__Impl rule__TypedTypeRefCS__Group__1 )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1506:2: rule__TypedTypeRefCS__Group__0__Impl rule__TypedTypeRefCS__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_rule__TypedTypeRefCS__Group__0__Impl_in_rule__TypedTypeRefCS__Group__03025);
            rule__TypedTypeRefCS__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_rule__TypedTypeRefCS__Group__1_in_rule__TypedTypeRefCS__Group__03028);
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1513:1: rule__TypedTypeRefCS__Group__0__Impl : ( ( rule__TypedTypeRefCS__OwnedPathNameAssignment_0 ) ) ;
    public final void rule__TypedTypeRefCS__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1517:1: ( ( ( rule__TypedTypeRefCS__OwnedPathNameAssignment_0 ) ) )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1518:1: ( ( rule__TypedTypeRefCS__OwnedPathNameAssignment_0 ) )
            {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1518:1: ( ( rule__TypedTypeRefCS__OwnedPathNameAssignment_0 ) )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1519:1: ( rule__TypedTypeRefCS__OwnedPathNameAssignment_0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTypedTypeRefCSAccess().getOwnedPathNameAssignment_0()); 
            }
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1520:1: ( rule__TypedTypeRefCS__OwnedPathNameAssignment_0 )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1520:2: rule__TypedTypeRefCS__OwnedPathNameAssignment_0
            {
            pushFollow(FollowSets000.FOLLOW_rule__TypedTypeRefCS__OwnedPathNameAssignment_0_in_rule__TypedTypeRefCS__Group__0__Impl3055);
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1530:1: rule__TypedTypeRefCS__Group__1 : rule__TypedTypeRefCS__Group__1__Impl ;
    public final void rule__TypedTypeRefCS__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1534:1: ( rule__TypedTypeRefCS__Group__1__Impl )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1535:2: rule__TypedTypeRefCS__Group__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_rule__TypedTypeRefCS__Group__1__Impl_in_rule__TypedTypeRefCS__Group__13085);
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1541:1: rule__TypedTypeRefCS__Group__1__Impl : ( ( rule__TypedTypeRefCS__Group_1__0 )? ) ;
    public final void rule__TypedTypeRefCS__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1545:1: ( ( ( rule__TypedTypeRefCS__Group_1__0 )? ) )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1546:1: ( ( rule__TypedTypeRefCS__Group_1__0 )? )
            {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1546:1: ( ( rule__TypedTypeRefCS__Group_1__0 )? )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1547:1: ( rule__TypedTypeRefCS__Group_1__0 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTypedTypeRefCSAccess().getGroup_1()); 
            }
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1548:1: ( rule__TypedTypeRefCS__Group_1__0 )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==27) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1548:2: rule__TypedTypeRefCS__Group_1__0
                    {
                    pushFollow(FollowSets000.FOLLOW_rule__TypedTypeRefCS__Group_1__0_in_rule__TypedTypeRefCS__Group__1__Impl3112);
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1562:1: rule__TypedTypeRefCS__Group_1__0 : rule__TypedTypeRefCS__Group_1__0__Impl rule__TypedTypeRefCS__Group_1__1 ;
    public final void rule__TypedTypeRefCS__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1566:1: ( rule__TypedTypeRefCS__Group_1__0__Impl rule__TypedTypeRefCS__Group_1__1 )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1567:2: rule__TypedTypeRefCS__Group_1__0__Impl rule__TypedTypeRefCS__Group_1__1
            {
            pushFollow(FollowSets000.FOLLOW_rule__TypedTypeRefCS__Group_1__0__Impl_in_rule__TypedTypeRefCS__Group_1__03147);
            rule__TypedTypeRefCS__Group_1__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_rule__TypedTypeRefCS__Group_1__1_in_rule__TypedTypeRefCS__Group_1__03150);
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1574:1: rule__TypedTypeRefCS__Group_1__0__Impl : ( '(' ) ;
    public final void rule__TypedTypeRefCS__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1578:1: ( ( '(' ) )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1579:1: ( '(' )
            {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1579:1: ( '(' )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1580:1: '('
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTypedTypeRefCSAccess().getLeftParenthesisKeyword_1_0()); 
            }
            match(input,27,FollowSets000.FOLLOW_27_in_rule__TypedTypeRefCS__Group_1__0__Impl3178); if (state.failed) return ;
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1593:1: rule__TypedTypeRefCS__Group_1__1 : rule__TypedTypeRefCS__Group_1__1__Impl rule__TypedTypeRefCS__Group_1__2 ;
    public final void rule__TypedTypeRefCS__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1597:1: ( rule__TypedTypeRefCS__Group_1__1__Impl rule__TypedTypeRefCS__Group_1__2 )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1598:2: rule__TypedTypeRefCS__Group_1__1__Impl rule__TypedTypeRefCS__Group_1__2
            {
            pushFollow(FollowSets000.FOLLOW_rule__TypedTypeRefCS__Group_1__1__Impl_in_rule__TypedTypeRefCS__Group_1__13209);
            rule__TypedTypeRefCS__Group_1__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_rule__TypedTypeRefCS__Group_1__2_in_rule__TypedTypeRefCS__Group_1__13212);
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1605:1: rule__TypedTypeRefCS__Group_1__1__Impl : ( ( rule__TypedTypeRefCS__OwnedBindingAssignment_1_1 ) ) ;
    public final void rule__TypedTypeRefCS__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1609:1: ( ( ( rule__TypedTypeRefCS__OwnedBindingAssignment_1_1 ) ) )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1610:1: ( ( rule__TypedTypeRefCS__OwnedBindingAssignment_1_1 ) )
            {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1610:1: ( ( rule__TypedTypeRefCS__OwnedBindingAssignment_1_1 ) )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1611:1: ( rule__TypedTypeRefCS__OwnedBindingAssignment_1_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTypedTypeRefCSAccess().getOwnedBindingAssignment_1_1()); 
            }
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1612:1: ( rule__TypedTypeRefCS__OwnedBindingAssignment_1_1 )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1612:2: rule__TypedTypeRefCS__OwnedBindingAssignment_1_1
            {
            pushFollow(FollowSets000.FOLLOW_rule__TypedTypeRefCS__OwnedBindingAssignment_1_1_in_rule__TypedTypeRefCS__Group_1__1__Impl3239);
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1622:1: rule__TypedTypeRefCS__Group_1__2 : rule__TypedTypeRefCS__Group_1__2__Impl ;
    public final void rule__TypedTypeRefCS__Group_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1626:1: ( rule__TypedTypeRefCS__Group_1__2__Impl )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1627:2: rule__TypedTypeRefCS__Group_1__2__Impl
            {
            pushFollow(FollowSets000.FOLLOW_rule__TypedTypeRefCS__Group_1__2__Impl_in_rule__TypedTypeRefCS__Group_1__23269);
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1633:1: rule__TypedTypeRefCS__Group_1__2__Impl : ( ')' ) ;
    public final void rule__TypedTypeRefCS__Group_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1637:1: ( ( ')' ) )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1638:1: ( ')' )
            {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1638:1: ( ')' )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1639:1: ')'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTypedTypeRefCSAccess().getRightParenthesisKeyword_1_2()); 
            }
            match(input,28,FollowSets000.FOLLOW_28_in_rule__TypedTypeRefCS__Group_1__2__Impl3297); if (state.failed) return ;
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1658:1: rule__WildcardTypeRefCS__Group__0 : rule__WildcardTypeRefCS__Group__0__Impl rule__WildcardTypeRefCS__Group__1 ;
    public final void rule__WildcardTypeRefCS__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1662:1: ( rule__WildcardTypeRefCS__Group__0__Impl rule__WildcardTypeRefCS__Group__1 )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1663:2: rule__WildcardTypeRefCS__Group__0__Impl rule__WildcardTypeRefCS__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_rule__WildcardTypeRefCS__Group__0__Impl_in_rule__WildcardTypeRefCS__Group__03334);
            rule__WildcardTypeRefCS__Group__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_rule__WildcardTypeRefCS__Group__1_in_rule__WildcardTypeRefCS__Group__03337);
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1670:1: rule__WildcardTypeRefCS__Group__0__Impl : ( () ) ;
    public final void rule__WildcardTypeRefCS__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1674:1: ( ( () ) )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1675:1: ( () )
            {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1675:1: ( () )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1676:1: ()
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getWildcardTypeRefCSAccess().getWildcardTypeRefCSAction_0()); 
            }
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1677:1: ()
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1679:1: 
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1689:1: rule__WildcardTypeRefCS__Group__1 : rule__WildcardTypeRefCS__Group__1__Impl rule__WildcardTypeRefCS__Group__2 ;
    public final void rule__WildcardTypeRefCS__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1693:1: ( rule__WildcardTypeRefCS__Group__1__Impl rule__WildcardTypeRefCS__Group__2 )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1694:2: rule__WildcardTypeRefCS__Group__1__Impl rule__WildcardTypeRefCS__Group__2
            {
            pushFollow(FollowSets000.FOLLOW_rule__WildcardTypeRefCS__Group__1__Impl_in_rule__WildcardTypeRefCS__Group__13395);
            rule__WildcardTypeRefCS__Group__1__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_rule__WildcardTypeRefCS__Group__2_in_rule__WildcardTypeRefCS__Group__13398);
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1701:1: rule__WildcardTypeRefCS__Group__1__Impl : ( '?' ) ;
    public final void rule__WildcardTypeRefCS__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1705:1: ( ( '?' ) )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1706:1: ( '?' )
            {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1706:1: ( '?' )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1707:1: '?'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getWildcardTypeRefCSAccess().getQuestionMarkKeyword_1()); 
            }
            match(input,19,FollowSets000.FOLLOW_19_in_rule__WildcardTypeRefCS__Group__1__Impl3426); if (state.failed) return ;
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1720:1: rule__WildcardTypeRefCS__Group__2 : rule__WildcardTypeRefCS__Group__2__Impl ;
    public final void rule__WildcardTypeRefCS__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1724:1: ( rule__WildcardTypeRefCS__Group__2__Impl )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1725:2: rule__WildcardTypeRefCS__Group__2__Impl
            {
            pushFollow(FollowSets000.FOLLOW_rule__WildcardTypeRefCS__Group__2__Impl_in_rule__WildcardTypeRefCS__Group__23457);
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1731:1: rule__WildcardTypeRefCS__Group__2__Impl : ( ( rule__WildcardTypeRefCS__Group_2__0 )? ) ;
    public final void rule__WildcardTypeRefCS__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1735:1: ( ( ( rule__WildcardTypeRefCS__Group_2__0 )? ) )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1736:1: ( ( rule__WildcardTypeRefCS__Group_2__0 )? )
            {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1736:1: ( ( rule__WildcardTypeRefCS__Group_2__0 )? )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1737:1: ( rule__WildcardTypeRefCS__Group_2__0 )?
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getWildcardTypeRefCSAccess().getGroup_2()); 
            }
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1738:1: ( rule__WildcardTypeRefCS__Group_2__0 )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==25) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1738:2: rule__WildcardTypeRefCS__Group_2__0
                    {
                    pushFollow(FollowSets000.FOLLOW_rule__WildcardTypeRefCS__Group_2__0_in_rule__WildcardTypeRefCS__Group__2__Impl3484);
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1754:1: rule__WildcardTypeRefCS__Group_2__0 : rule__WildcardTypeRefCS__Group_2__0__Impl rule__WildcardTypeRefCS__Group_2__1 ;
    public final void rule__WildcardTypeRefCS__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1758:1: ( rule__WildcardTypeRefCS__Group_2__0__Impl rule__WildcardTypeRefCS__Group_2__1 )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1759:2: rule__WildcardTypeRefCS__Group_2__0__Impl rule__WildcardTypeRefCS__Group_2__1
            {
            pushFollow(FollowSets000.FOLLOW_rule__WildcardTypeRefCS__Group_2__0__Impl_in_rule__WildcardTypeRefCS__Group_2__03521);
            rule__WildcardTypeRefCS__Group_2__0__Impl();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FollowSets000.FOLLOW_rule__WildcardTypeRefCS__Group_2__1_in_rule__WildcardTypeRefCS__Group_2__03524);
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1766:1: rule__WildcardTypeRefCS__Group_2__0__Impl : ( 'extends' ) ;
    public final void rule__WildcardTypeRefCS__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1770:1: ( ( 'extends' ) )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1771:1: ( 'extends' )
            {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1771:1: ( 'extends' )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1772:1: 'extends'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getWildcardTypeRefCSAccess().getExtendsKeyword_2_0()); 
            }
            match(input,25,FollowSets000.FOLLOW_25_in_rule__WildcardTypeRefCS__Group_2__0__Impl3552); if (state.failed) return ;
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1785:1: rule__WildcardTypeRefCS__Group_2__1 : rule__WildcardTypeRefCS__Group_2__1__Impl ;
    public final void rule__WildcardTypeRefCS__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1789:1: ( rule__WildcardTypeRefCS__Group_2__1__Impl )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1790:2: rule__WildcardTypeRefCS__Group_2__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_rule__WildcardTypeRefCS__Group_2__1__Impl_in_rule__WildcardTypeRefCS__Group_2__13583);
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1796:1: rule__WildcardTypeRefCS__Group_2__1__Impl : ( ( rule__WildcardTypeRefCS__OwnedExtendsAssignment_2_1 ) ) ;
    public final void rule__WildcardTypeRefCS__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1800:1: ( ( ( rule__WildcardTypeRefCS__OwnedExtendsAssignment_2_1 ) ) )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1801:1: ( ( rule__WildcardTypeRefCS__OwnedExtendsAssignment_2_1 ) )
            {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1801:1: ( ( rule__WildcardTypeRefCS__OwnedExtendsAssignment_2_1 ) )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1802:1: ( rule__WildcardTypeRefCS__OwnedExtendsAssignment_2_1 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getWildcardTypeRefCSAccess().getOwnedExtendsAssignment_2_1()); 
            }
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1803:1: ( rule__WildcardTypeRefCS__OwnedExtendsAssignment_2_1 )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1803:2: rule__WildcardTypeRefCS__OwnedExtendsAssignment_2_1
            {
            pushFollow(FollowSets000.FOLLOW_rule__WildcardTypeRefCS__OwnedExtendsAssignment_2_1_in_rule__WildcardTypeRefCS__Group_2__1__Impl3610);
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


    // $ANTLR start "rule__MultiplicityBoundsCS__LowerBoundAssignment_0"
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1818:1: rule__MultiplicityBoundsCS__LowerBoundAssignment_0 : ( ruleLOWER ) ;
    public final void rule__MultiplicityBoundsCS__LowerBoundAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1822:1: ( ( ruleLOWER ) )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1823:1: ( ruleLOWER )
            {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1823:1: ( ruleLOWER )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1824:1: ruleLOWER
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getMultiplicityBoundsCSAccess().getLowerBoundLOWERParserRuleCall_0_0()); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleLOWER_in_rule__MultiplicityBoundsCS__LowerBoundAssignment_03649);
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1833:1: rule__MultiplicityBoundsCS__UpperBoundAssignment_1_1 : ( ruleUPPER ) ;
    public final void rule__MultiplicityBoundsCS__UpperBoundAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1837:1: ( ( ruleUPPER ) )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1838:1: ( ruleUPPER )
            {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1838:1: ( ruleUPPER )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1839:1: ruleUPPER
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getMultiplicityBoundsCSAccess().getUpperBoundUPPERParserRuleCall_1_1_0()); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleUPPER_in_rule__MultiplicityBoundsCS__UpperBoundAssignment_1_13680);
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1848:1: rule__MultiplicityCS__IsNullFreeAssignment_2_1 : ( ( '|1' ) ) ;
    public final void rule__MultiplicityCS__IsNullFreeAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1852:1: ( ( ( '|1' ) ) )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1853:1: ( ( '|1' ) )
            {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1853:1: ( ( '|1' ) )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1854:1: ( '|1' )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getMultiplicityCSAccess().getIsNullFree1Keyword_2_1_0()); 
            }
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1855:1: ( '|1' )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1856:1: '|1'
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getMultiplicityCSAccess().getIsNullFree1Keyword_2_1_0()); 
            }
            match(input,29,FollowSets000.FOLLOW_29_in_rule__MultiplicityCS__IsNullFreeAssignment_2_13716); if (state.failed) return ;
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1871:1: rule__MultiplicityStringCS__StringBoundsAssignment : ( ( rule__MultiplicityStringCS__StringBoundsAlternatives_0 ) ) ;
    public final void rule__MultiplicityStringCS__StringBoundsAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1875:1: ( ( ( rule__MultiplicityStringCS__StringBoundsAlternatives_0 ) ) )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1876:1: ( ( rule__MultiplicityStringCS__StringBoundsAlternatives_0 ) )
            {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1876:1: ( ( rule__MultiplicityStringCS__StringBoundsAlternatives_0 ) )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1877:1: ( rule__MultiplicityStringCS__StringBoundsAlternatives_0 )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getMultiplicityStringCSAccess().getStringBoundsAlternatives_0()); 
            }
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1878:1: ( rule__MultiplicityStringCS__StringBoundsAlternatives_0 )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1878:2: rule__MultiplicityStringCS__StringBoundsAlternatives_0
            {
            pushFollow(FollowSets000.FOLLOW_rule__MultiplicityStringCS__StringBoundsAlternatives_0_in_rule__MultiplicityStringCS__StringBoundsAssignment3755);
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1887:1: rule__PathNameCS__OwnedPathElementsAssignment_0 : ( ruleFirstPathElementCS ) ;
    public final void rule__PathNameCS__OwnedPathElementsAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1891:1: ( ( ruleFirstPathElementCS ) )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1892:1: ( ruleFirstPathElementCS )
            {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1892:1: ( ruleFirstPathElementCS )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1893:1: ruleFirstPathElementCS
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getPathNameCSAccess().getOwnedPathElementsFirstPathElementCSParserRuleCall_0_0()); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleFirstPathElementCS_in_rule__PathNameCS__OwnedPathElementsAssignment_03788);
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1902:1: rule__PathNameCS__OwnedPathElementsAssignment_1_1 : ( ruleNextPathElementCS ) ;
    public final void rule__PathNameCS__OwnedPathElementsAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1906:1: ( ( ruleNextPathElementCS ) )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1907:1: ( ruleNextPathElementCS )
            {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1907:1: ( ruleNextPathElementCS )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1908:1: ruleNextPathElementCS
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getPathNameCSAccess().getOwnedPathElementsNextPathElementCSParserRuleCall_1_1_0()); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleNextPathElementCS_in_rule__PathNameCS__OwnedPathElementsAssignment_1_13819);
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1917:1: rule__FirstPathElementCS__ReferredElementAssignment : ( ( ruleUnrestrictedName ) ) ;
    public final void rule__FirstPathElementCS__ReferredElementAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1921:1: ( ( ( ruleUnrestrictedName ) ) )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1922:1: ( ( ruleUnrestrictedName ) )
            {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1922:1: ( ( ruleUnrestrictedName ) )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1923:1: ( ruleUnrestrictedName )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFirstPathElementCSAccess().getReferredElementNamedElementCrossReference_0()); 
            }
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1924:1: ( ruleUnrestrictedName )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1925:1: ruleUnrestrictedName
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getFirstPathElementCSAccess().getReferredElementNamedElementUnrestrictedNameParserRuleCall_0_1()); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleUnrestrictedName_in_rule__FirstPathElementCS__ReferredElementAssignment3854);
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1936:1: rule__NextPathElementCS__ReferredElementAssignment : ( ( ruleUnreservedName ) ) ;
    public final void rule__NextPathElementCS__ReferredElementAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1940:1: ( ( ( ruleUnreservedName ) ) )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1941:1: ( ( ruleUnreservedName ) )
            {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1941:1: ( ( ruleUnreservedName ) )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1942:1: ( ruleUnreservedName )
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getNextPathElementCSAccess().getReferredElementNamedElementCrossReference_0()); 
            }
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1943:1: ( ruleUnreservedName )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1944:1: ruleUnreservedName
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getNextPathElementCSAccess().getReferredElementNamedElementUnreservedNameParserRuleCall_0_1()); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleUnreservedName_in_rule__NextPathElementCS__ReferredElementAssignment3893);
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1955:1: rule__TemplateBindingCS__OwnedSubstitutionsAssignment_0 : ( ruleTemplateParameterSubstitutionCS ) ;
    public final void rule__TemplateBindingCS__OwnedSubstitutionsAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1959:1: ( ( ruleTemplateParameterSubstitutionCS ) )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1960:1: ( ruleTemplateParameterSubstitutionCS )
            {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1960:1: ( ruleTemplateParameterSubstitutionCS )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1961:1: ruleTemplateParameterSubstitutionCS
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTemplateBindingCSAccess().getOwnedSubstitutionsTemplateParameterSubstitutionCSParserRuleCall_0_0()); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleTemplateParameterSubstitutionCS_in_rule__TemplateBindingCS__OwnedSubstitutionsAssignment_03928);
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1970:1: rule__TemplateBindingCS__OwnedSubstitutionsAssignment_1_1 : ( ruleTemplateParameterSubstitutionCS ) ;
    public final void rule__TemplateBindingCS__OwnedSubstitutionsAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1974:1: ( ( ruleTemplateParameterSubstitutionCS ) )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1975:1: ( ruleTemplateParameterSubstitutionCS )
            {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1975:1: ( ruleTemplateParameterSubstitutionCS )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1976:1: ruleTemplateParameterSubstitutionCS
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTemplateBindingCSAccess().getOwnedSubstitutionsTemplateParameterSubstitutionCSParserRuleCall_1_1_0()); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleTemplateParameterSubstitutionCS_in_rule__TemplateBindingCS__OwnedSubstitutionsAssignment_1_13959);
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1985:1: rule__TemplateBindingCS__OwnedMultiplicityAssignment_2 : ( ruleMultiplicityCS ) ;
    public final void rule__TemplateBindingCS__OwnedMultiplicityAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1989:1: ( ( ruleMultiplicityCS ) )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1990:1: ( ruleMultiplicityCS )
            {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1990:1: ( ruleMultiplicityCS )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:1991:1: ruleMultiplicityCS
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTemplateBindingCSAccess().getOwnedMultiplicityMultiplicityCSParserRuleCall_2_0()); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleMultiplicityCS_in_rule__TemplateBindingCS__OwnedMultiplicityAssignment_23990);
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:2000:1: rule__TemplateParameterSubstitutionCS__OwnedActualParameterAssignment : ( ruleTypeRefCS ) ;
    public final void rule__TemplateParameterSubstitutionCS__OwnedActualParameterAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:2004:1: ( ( ruleTypeRefCS ) )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:2005:1: ( ruleTypeRefCS )
            {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:2005:1: ( ruleTypeRefCS )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:2006:1: ruleTypeRefCS
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTemplateParameterSubstitutionCSAccess().getOwnedActualParameterTypeRefCSParserRuleCall_0()); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleTypeRefCS_in_rule__TemplateParameterSubstitutionCS__OwnedActualParameterAssignment4021);
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:2017:1: rule__TypeParameterCS__NameAssignment_0 : ( ruleUnrestrictedName ) ;
    public final void rule__TypeParameterCS__NameAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:2021:1: ( ( ruleUnrestrictedName ) )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:2022:1: ( ruleUnrestrictedName )
            {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:2022:1: ( ruleUnrestrictedName )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:2023:1: ruleUnrestrictedName
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTypeParameterCSAccess().getNameUnrestrictedNameParserRuleCall_0_0()); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleUnrestrictedName_in_rule__TypeParameterCS__NameAssignment_04054);
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:2032:1: rule__TypeParameterCS__OwnedExtendsAssignment_1_1 : ( ruleTypedRefCS ) ;
    public final void rule__TypeParameterCS__OwnedExtendsAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:2036:1: ( ( ruleTypedRefCS ) )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:2037:1: ( ruleTypedRefCS )
            {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:2037:1: ( ruleTypedRefCS )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:2038:1: ruleTypedRefCS
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTypeParameterCSAccess().getOwnedExtendsTypedRefCSParserRuleCall_1_1_0()); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleTypedRefCS_in_rule__TypeParameterCS__OwnedExtendsAssignment_1_14085);
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:2047:1: rule__TypeParameterCS__OwnedExtendsAssignment_1_2_1 : ( ruleTypedRefCS ) ;
    public final void rule__TypeParameterCS__OwnedExtendsAssignment_1_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:2051:1: ( ( ruleTypedRefCS ) )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:2052:1: ( ruleTypedRefCS )
            {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:2052:1: ( ruleTypedRefCS )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:2053:1: ruleTypedRefCS
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTypeParameterCSAccess().getOwnedExtendsTypedRefCSParserRuleCall_1_2_1_0()); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleTypedRefCS_in_rule__TypeParameterCS__OwnedExtendsAssignment_1_2_14116);
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:2062:1: rule__TypedTypeRefCS__OwnedPathNameAssignment_0 : ( rulePathNameCS ) ;
    public final void rule__TypedTypeRefCS__OwnedPathNameAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:2066:1: ( ( rulePathNameCS ) )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:2067:1: ( rulePathNameCS )
            {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:2067:1: ( rulePathNameCS )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:2068:1: rulePathNameCS
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTypedTypeRefCSAccess().getOwnedPathNamePathNameCSParserRuleCall_0_0()); 
            }
            pushFollow(FollowSets000.FOLLOW_rulePathNameCS_in_rule__TypedTypeRefCS__OwnedPathNameAssignment_04147);
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:2077:1: rule__TypedTypeRefCS__OwnedBindingAssignment_1_1 : ( ruleTemplateBindingCS ) ;
    public final void rule__TypedTypeRefCS__OwnedBindingAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:2081:1: ( ( ruleTemplateBindingCS ) )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:2082:1: ( ruleTemplateBindingCS )
            {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:2082:1: ( ruleTemplateBindingCS )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:2083:1: ruleTemplateBindingCS
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getTypedTypeRefCSAccess().getOwnedBindingTemplateBindingCSParserRuleCall_1_1_0()); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleTemplateBindingCS_in_rule__TypedTypeRefCS__OwnedBindingAssignment_1_14178);
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
    // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:2092:1: rule__WildcardTypeRefCS__OwnedExtendsAssignment_2_1 : ( ruleTypedRefCS ) ;
    public final void rule__WildcardTypeRefCS__OwnedExtendsAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:2096:1: ( ( ruleTypedRefCS ) )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:2097:1: ( ruleTypedRefCS )
            {
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:2097:1: ( ruleTypedRefCS )
            // ../../plugins/org.eclipse.ocl.xtext.base.ui/src-gen/org/eclipse/ocl/xtext/base/ui/contentassist/antlr/internal/InternalBase.g:2098:1: ruleTypedRefCS
            {
            if ( state.backtracking==0 ) {
               before(grammarAccess.getWildcardTypeRefCSAccess().getOwnedExtendsTypedRefCSParserRuleCall_2_1_0()); 
            }
            pushFollow(FollowSets000.FOLLOW_ruleTypedRefCS_in_rule__WildcardTypeRefCS__OwnedExtendsAssignment_2_14209);
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
        public static final BitSet FOLLOW_ruleMultiplicityBoundsCS_in_entryRuleMultiplicityBoundsCS67 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleMultiplicityBoundsCS74 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__MultiplicityBoundsCS__Group__0_in_ruleMultiplicityBoundsCS100 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleMultiplicityCS_in_entryRuleMultiplicityCS127 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleMultiplicityCS134 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__MultiplicityCS__Group__0_in_ruleMultiplicityCS160 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleMultiplicityStringCS_in_entryRuleMultiplicityStringCS187 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleMultiplicityStringCS194 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__MultiplicityStringCS__StringBoundsAssignment_in_ruleMultiplicityStringCS220 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rulePathNameCS_in_entryRulePathNameCS247 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRulePathNameCS254 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__PathNameCS__Group__0_in_rulePathNameCS280 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleFirstPathElementCS_in_entryRuleFirstPathElementCS307 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleFirstPathElementCS314 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__FirstPathElementCS__ReferredElementAssignment_in_ruleFirstPathElementCS340 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleNextPathElementCS_in_entryRuleNextPathElementCS367 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleNextPathElementCS374 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__NextPathElementCS__ReferredElementAssignment_in_ruleNextPathElementCS400 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleTemplateBindingCS_in_entryRuleTemplateBindingCS427 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleTemplateBindingCS434 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__TemplateBindingCS__Group__0_in_ruleTemplateBindingCS460 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleTemplateParameterSubstitutionCS_in_entryRuleTemplateParameterSubstitutionCS487 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleTemplateParameterSubstitutionCS494 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__TemplateParameterSubstitutionCS__OwnedActualParameterAssignment_in_ruleTemplateParameterSubstitutionCS520 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleTypeParameterCS_in_entryRuleTypeParameterCS549 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleTypeParameterCS556 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__TypeParameterCS__Group__0_in_ruleTypeParameterCS582 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleTypeRefCS_in_entryRuleTypeRefCS609 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleTypeRefCS616 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__TypeRefCS__Alternatives_in_ruleTypeRefCS642 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleTypedRefCS_in_entryRuleTypedRefCS669 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleTypedRefCS676 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleTypedTypeRefCS_in_ruleTypedRefCS702 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleTypedTypeRefCS_in_entryRuleTypedTypeRefCS728 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleTypedTypeRefCS735 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__TypedTypeRefCS__Group__0_in_ruleTypedTypeRefCS761 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleUnreservedName_in_entryRuleUnreservedName788 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleUnreservedName795 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleUnrestrictedName_in_ruleUnreservedName821 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleUnrestrictedName_in_entryRuleUnrestrictedName847 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleUnrestrictedName854 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleIdentifier_in_ruleUnrestrictedName880 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleWildcardTypeRefCS_in_entryRuleWildcardTypeRefCS906 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleWildcardTypeRefCS913 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__WildcardTypeRefCS__Group__0_in_ruleWildcardTypeRefCS939 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleID_in_entryRuleID966 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleID973 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__ID__Alternatives_in_ruleID999 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleIdentifier_in_entryRuleIdentifier1026 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleIdentifier1033 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleID_in_ruleIdentifier1059 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleLOWER_in_entryRuleLOWER1085 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleLOWER1092 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_INT_in_ruleLOWER1118 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleUPPER_in_entryRuleUPPER1148 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleUPPER1155 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__UPPER__Alternatives_in_ruleUPPER1181 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleMultiplicityBoundsCS_in_rule__MultiplicityCS__Alternatives_11219 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleMultiplicityStringCS_in_rule__MultiplicityCS__Alternatives_11236 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_16_in_rule__MultiplicityCS__Alternatives_21269 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__MultiplicityCS__IsNullFreeAssignment_2_1_in_rule__MultiplicityCS__Alternatives_21288 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_17_in_rule__MultiplicityStringCS__StringBoundsAlternatives_01322 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_18_in_rule__MultiplicityStringCS__StringBoundsAlternatives_01342 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_19_in_rule__MultiplicityStringCS__StringBoundsAlternatives_01362 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleTypedRefCS_in_rule__TypeRefCS__Alternatives1396 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleWildcardTypeRefCS_in_rule__TypeRefCS__Alternatives1413 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_SIMPLE_ID_in_rule__ID__Alternatives1445 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_ESCAPED_ID_in_rule__ID__Alternatives1462 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_INT_in_rule__UPPER__Alternatives1494 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_17_in_rule__UPPER__Alternatives1512 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__MultiplicityBoundsCS__Group__0__Impl_in_rule__MultiplicityBoundsCS__Group__01544 = new BitSet(new long[]{0x0000000000100000L});
        public static final BitSet FOLLOW_rule__MultiplicityBoundsCS__Group__1_in_rule__MultiplicityBoundsCS__Group__01547 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__MultiplicityBoundsCS__LowerBoundAssignment_0_in_rule__MultiplicityBoundsCS__Group__0__Impl1574 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__MultiplicityBoundsCS__Group__1__Impl_in_rule__MultiplicityBoundsCS__Group__11604 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__MultiplicityBoundsCS__Group_1__0_in_rule__MultiplicityBoundsCS__Group__1__Impl1631 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__MultiplicityBoundsCS__Group_1__0__Impl_in_rule__MultiplicityBoundsCS__Group_1__01666 = new BitSet(new long[]{0x0000000000020010L});
        public static final BitSet FOLLOW_rule__MultiplicityBoundsCS__Group_1__1_in_rule__MultiplicityBoundsCS__Group_1__01669 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_20_in_rule__MultiplicityBoundsCS__Group_1__0__Impl1697 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__MultiplicityBoundsCS__Group_1__1__Impl_in_rule__MultiplicityBoundsCS__Group_1__11728 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__MultiplicityBoundsCS__UpperBoundAssignment_1_1_in_rule__MultiplicityBoundsCS__Group_1__1__Impl1755 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__MultiplicityCS__Group__0__Impl_in_rule__MultiplicityCS__Group__01789 = new BitSet(new long[]{0x00000000000E0010L});
        public static final BitSet FOLLOW_rule__MultiplicityCS__Group__1_in_rule__MultiplicityCS__Group__01792 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_21_in_rule__MultiplicityCS__Group__0__Impl1820 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__MultiplicityCS__Group__1__Impl_in_rule__MultiplicityCS__Group__11851 = new BitSet(new long[]{0x0000000020410000L});
        public static final BitSet FOLLOW_rule__MultiplicityCS__Group__2_in_rule__MultiplicityCS__Group__11854 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__MultiplicityCS__Alternatives_1_in_rule__MultiplicityCS__Group__1__Impl1881 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__MultiplicityCS__Group__2__Impl_in_rule__MultiplicityCS__Group__21911 = new BitSet(new long[]{0x0000000020410000L});
        public static final BitSet FOLLOW_rule__MultiplicityCS__Group__3_in_rule__MultiplicityCS__Group__21914 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__MultiplicityCS__Alternatives_2_in_rule__MultiplicityCS__Group__2__Impl1941 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__MultiplicityCS__Group__3__Impl_in_rule__MultiplicityCS__Group__31972 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_22_in_rule__MultiplicityCS__Group__3__Impl2000 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__PathNameCS__Group__0__Impl_in_rule__PathNameCS__Group__02039 = new BitSet(new long[]{0x0000000000800000L});
        public static final BitSet FOLLOW_rule__PathNameCS__Group__1_in_rule__PathNameCS__Group__02042 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__PathNameCS__OwnedPathElementsAssignment_0_in_rule__PathNameCS__Group__0__Impl2069 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__PathNameCS__Group__1__Impl_in_rule__PathNameCS__Group__12099 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__PathNameCS__Group_1__0_in_rule__PathNameCS__Group__1__Impl2126 = new BitSet(new long[]{0x0000000000800002L});
        public static final BitSet FOLLOW_rule__PathNameCS__Group_1__0__Impl_in_rule__PathNameCS__Group_1__02161 = new BitSet(new long[]{0x0000000000000060L});
        public static final BitSet FOLLOW_rule__PathNameCS__Group_1__1_in_rule__PathNameCS__Group_1__02164 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_23_in_rule__PathNameCS__Group_1__0__Impl2192 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__PathNameCS__Group_1__1__Impl_in_rule__PathNameCS__Group_1__12223 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__PathNameCS__OwnedPathElementsAssignment_1_1_in_rule__PathNameCS__Group_1__1__Impl2250 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__TemplateBindingCS__Group__0__Impl_in_rule__TemplateBindingCS__Group__02284 = new BitSet(new long[]{0x0000000001200000L});
        public static final BitSet FOLLOW_rule__TemplateBindingCS__Group__1_in_rule__TemplateBindingCS__Group__02287 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__TemplateBindingCS__OwnedSubstitutionsAssignment_0_in_rule__TemplateBindingCS__Group__0__Impl2314 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__TemplateBindingCS__Group__1__Impl_in_rule__TemplateBindingCS__Group__12344 = new BitSet(new long[]{0x0000000001200000L});
        public static final BitSet FOLLOW_rule__TemplateBindingCS__Group__2_in_rule__TemplateBindingCS__Group__12347 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__TemplateBindingCS__Group_1__0_in_rule__TemplateBindingCS__Group__1__Impl2374 = new BitSet(new long[]{0x0000000001000002L});
        public static final BitSet FOLLOW_rule__TemplateBindingCS__Group__2__Impl_in_rule__TemplateBindingCS__Group__22405 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__TemplateBindingCS__OwnedMultiplicityAssignment_2_in_rule__TemplateBindingCS__Group__2__Impl2432 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__TemplateBindingCS__Group_1__0__Impl_in_rule__TemplateBindingCS__Group_1__02469 = new BitSet(new long[]{0x0000000000080060L});
        public static final BitSet FOLLOW_rule__TemplateBindingCS__Group_1__1_in_rule__TemplateBindingCS__Group_1__02472 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_24_in_rule__TemplateBindingCS__Group_1__0__Impl2500 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__TemplateBindingCS__Group_1__1__Impl_in_rule__TemplateBindingCS__Group_1__12531 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__TemplateBindingCS__OwnedSubstitutionsAssignment_1_1_in_rule__TemplateBindingCS__Group_1__1__Impl2558 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__TypeParameterCS__Group__0__Impl_in_rule__TypeParameterCS__Group__02594 = new BitSet(new long[]{0x0000000002000000L});
        public static final BitSet FOLLOW_rule__TypeParameterCS__Group__1_in_rule__TypeParameterCS__Group__02597 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__TypeParameterCS__NameAssignment_0_in_rule__TypeParameterCS__Group__0__Impl2624 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__TypeParameterCS__Group__1__Impl_in_rule__TypeParameterCS__Group__12654 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__TypeParameterCS__Group_1__0_in_rule__TypeParameterCS__Group__1__Impl2681 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__TypeParameterCS__Group_1__0__Impl_in_rule__TypeParameterCS__Group_1__02716 = new BitSet(new long[]{0x0000000000000060L});
        public static final BitSet FOLLOW_rule__TypeParameterCS__Group_1__1_in_rule__TypeParameterCS__Group_1__02719 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_25_in_rule__TypeParameterCS__Group_1__0__Impl2747 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__TypeParameterCS__Group_1__1__Impl_in_rule__TypeParameterCS__Group_1__12778 = new BitSet(new long[]{0x0000000004000000L});
        public static final BitSet FOLLOW_rule__TypeParameterCS__Group_1__2_in_rule__TypeParameterCS__Group_1__12781 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__TypeParameterCS__OwnedExtendsAssignment_1_1_in_rule__TypeParameterCS__Group_1__1__Impl2808 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__TypeParameterCS__Group_1__2__Impl_in_rule__TypeParameterCS__Group_1__22838 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__TypeParameterCS__Group_1_2__0_in_rule__TypeParameterCS__Group_1__2__Impl2865 = new BitSet(new long[]{0x0000000004000002L});
        public static final BitSet FOLLOW_rule__TypeParameterCS__Group_1_2__0__Impl_in_rule__TypeParameterCS__Group_1_2__02902 = new BitSet(new long[]{0x0000000000000060L});
        public static final BitSet FOLLOW_rule__TypeParameterCS__Group_1_2__1_in_rule__TypeParameterCS__Group_1_2__02905 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_26_in_rule__TypeParameterCS__Group_1_2__0__Impl2933 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__TypeParameterCS__Group_1_2__1__Impl_in_rule__TypeParameterCS__Group_1_2__12964 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__TypeParameterCS__OwnedExtendsAssignment_1_2_1_in_rule__TypeParameterCS__Group_1_2__1__Impl2991 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__TypedTypeRefCS__Group__0__Impl_in_rule__TypedTypeRefCS__Group__03025 = new BitSet(new long[]{0x0000000008000000L});
        public static final BitSet FOLLOW_rule__TypedTypeRefCS__Group__1_in_rule__TypedTypeRefCS__Group__03028 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__TypedTypeRefCS__OwnedPathNameAssignment_0_in_rule__TypedTypeRefCS__Group__0__Impl3055 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__TypedTypeRefCS__Group__1__Impl_in_rule__TypedTypeRefCS__Group__13085 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__TypedTypeRefCS__Group_1__0_in_rule__TypedTypeRefCS__Group__1__Impl3112 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__TypedTypeRefCS__Group_1__0__Impl_in_rule__TypedTypeRefCS__Group_1__03147 = new BitSet(new long[]{0x0000000000080060L});
        public static final BitSet FOLLOW_rule__TypedTypeRefCS__Group_1__1_in_rule__TypedTypeRefCS__Group_1__03150 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_27_in_rule__TypedTypeRefCS__Group_1__0__Impl3178 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__TypedTypeRefCS__Group_1__1__Impl_in_rule__TypedTypeRefCS__Group_1__13209 = new BitSet(new long[]{0x0000000010000000L});
        public static final BitSet FOLLOW_rule__TypedTypeRefCS__Group_1__2_in_rule__TypedTypeRefCS__Group_1__13212 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__TypedTypeRefCS__OwnedBindingAssignment_1_1_in_rule__TypedTypeRefCS__Group_1__1__Impl3239 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__TypedTypeRefCS__Group_1__2__Impl_in_rule__TypedTypeRefCS__Group_1__23269 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_28_in_rule__TypedTypeRefCS__Group_1__2__Impl3297 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__WildcardTypeRefCS__Group__0__Impl_in_rule__WildcardTypeRefCS__Group__03334 = new BitSet(new long[]{0x0000000000080060L});
        public static final BitSet FOLLOW_rule__WildcardTypeRefCS__Group__1_in_rule__WildcardTypeRefCS__Group__03337 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__WildcardTypeRefCS__Group__1__Impl_in_rule__WildcardTypeRefCS__Group__13395 = new BitSet(new long[]{0x0000000002000000L});
        public static final BitSet FOLLOW_rule__WildcardTypeRefCS__Group__2_in_rule__WildcardTypeRefCS__Group__13398 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_19_in_rule__WildcardTypeRefCS__Group__1__Impl3426 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__WildcardTypeRefCS__Group__2__Impl_in_rule__WildcardTypeRefCS__Group__23457 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__WildcardTypeRefCS__Group_2__0_in_rule__WildcardTypeRefCS__Group__2__Impl3484 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__WildcardTypeRefCS__Group_2__0__Impl_in_rule__WildcardTypeRefCS__Group_2__03521 = new BitSet(new long[]{0x0000000000000060L});
        public static final BitSet FOLLOW_rule__WildcardTypeRefCS__Group_2__1_in_rule__WildcardTypeRefCS__Group_2__03524 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_25_in_rule__WildcardTypeRefCS__Group_2__0__Impl3552 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__WildcardTypeRefCS__Group_2__1__Impl_in_rule__WildcardTypeRefCS__Group_2__13583 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__WildcardTypeRefCS__OwnedExtendsAssignment_2_1_in_rule__WildcardTypeRefCS__Group_2__1__Impl3610 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleLOWER_in_rule__MultiplicityBoundsCS__LowerBoundAssignment_03649 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleUPPER_in_rule__MultiplicityBoundsCS__UpperBoundAssignment_1_13680 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_29_in_rule__MultiplicityCS__IsNullFreeAssignment_2_13716 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__MultiplicityStringCS__StringBoundsAlternatives_0_in_rule__MultiplicityStringCS__StringBoundsAssignment3755 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleFirstPathElementCS_in_rule__PathNameCS__OwnedPathElementsAssignment_03788 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleNextPathElementCS_in_rule__PathNameCS__OwnedPathElementsAssignment_1_13819 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleUnrestrictedName_in_rule__FirstPathElementCS__ReferredElementAssignment3854 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleUnreservedName_in_rule__NextPathElementCS__ReferredElementAssignment3893 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleTemplateParameterSubstitutionCS_in_rule__TemplateBindingCS__OwnedSubstitutionsAssignment_03928 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleTemplateParameterSubstitutionCS_in_rule__TemplateBindingCS__OwnedSubstitutionsAssignment_1_13959 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleMultiplicityCS_in_rule__TemplateBindingCS__OwnedMultiplicityAssignment_23990 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleTypeRefCS_in_rule__TemplateParameterSubstitutionCS__OwnedActualParameterAssignment4021 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleUnrestrictedName_in_rule__TypeParameterCS__NameAssignment_04054 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleTypedRefCS_in_rule__TypeParameterCS__OwnedExtendsAssignment_1_14085 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleTypedRefCS_in_rule__TypeParameterCS__OwnedExtendsAssignment_1_2_14116 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rulePathNameCS_in_rule__TypedTypeRefCS__OwnedPathNameAssignment_04147 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleTemplateBindingCS_in_rule__TypedTypeRefCS__OwnedBindingAssignment_1_14178 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleTypedRefCS_in_rule__WildcardTypeRefCS__OwnedExtendsAssignment_2_14209 = new BitSet(new long[]{0x0000000000000002L});
    }


}