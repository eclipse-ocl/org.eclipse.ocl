/**
* Ecore2Xtext Keyword Lexer
* <copyright>
*******************************************************************************/

package org.eclipse.ocl.examples.ecore2xtext;

import lpg.runtime.*;


public class Ecore2XtextKWLexer extends Ecore2XtextKWLexerprs
{
    private char[] inputChars;
    private final int keywordKind[] = new int[31 + 1];

    public int[] getKeywordKinds() { return keywordKind; }

    public int lexer(int curtok, int lasttok)
    {
        int current_kind = getKind(inputChars[curtok]),
            act;

        for (act = tAction(START_STATE, current_kind);
             act > NUM_RULES && act < ACCEPT_ACTION;
             act = tAction(act, current_kind))
        {
            curtok++;
            current_kind = (curtok > lasttok
                                   ? Char_EOF
                                   : getKind(inputChars[curtok]));
        }

        if (act > ERROR_ACTION)
        {
            curtok++;
            act -= ERROR_ACTION;
        }

        return keywordKind[act == ERROR_ACTION  || curtok <= lasttok ? 0 : act];
    }

    public void setInputChars(char[] inputChars) { this.inputChars = inputChars; }


    final static int tokenKind[] = new int[128];
    static
    {
        tokenKind['$'] = Ecore2XtextKWLexersym.Char_DollarSign;
        tokenKind['%'] = Ecore2XtextKWLexersym.Char_Percent;
        tokenKind['_'] = Ecore2XtextKWLexersym.Char__;

        tokenKind['a'] = Ecore2XtextKWLexersym.Char_a;
        tokenKind['b'] = Ecore2XtextKWLexersym.Char_b;
        tokenKind['c'] = Ecore2XtextKWLexersym.Char_c;
        tokenKind['d'] = Ecore2XtextKWLexersym.Char_d;
        tokenKind['e'] = Ecore2XtextKWLexersym.Char_e;
        tokenKind['f'] = Ecore2XtextKWLexersym.Char_f;
        tokenKind['g'] = Ecore2XtextKWLexersym.Char_g;
        tokenKind['h'] = Ecore2XtextKWLexersym.Char_h;
        tokenKind['i'] = Ecore2XtextKWLexersym.Char_i;
        tokenKind['j'] = Ecore2XtextKWLexersym.Char_j;
        tokenKind['k'] = Ecore2XtextKWLexersym.Char_k;
        tokenKind['l'] = Ecore2XtextKWLexersym.Char_l;
        tokenKind['m'] = Ecore2XtextKWLexersym.Char_m;
        tokenKind['n'] = Ecore2XtextKWLexersym.Char_n;
        tokenKind['o'] = Ecore2XtextKWLexersym.Char_o;
        tokenKind['p'] = Ecore2XtextKWLexersym.Char_p;
        tokenKind['q'] = Ecore2XtextKWLexersym.Char_q;
        tokenKind['r'] = Ecore2XtextKWLexersym.Char_r;
        tokenKind['s'] = Ecore2XtextKWLexersym.Char_s;
        tokenKind['t'] = Ecore2XtextKWLexersym.Char_t;
        tokenKind['u'] = Ecore2XtextKWLexersym.Char_u;
        tokenKind['v'] = Ecore2XtextKWLexersym.Char_v;
        tokenKind['w'] = Ecore2XtextKWLexersym.Char_w;
        tokenKind['x'] = Ecore2XtextKWLexersym.Char_x;
        tokenKind['y'] = Ecore2XtextKWLexersym.Char_y;
        tokenKind['z'] = Ecore2XtextKWLexersym.Char_z;

        tokenKind['A'] = Ecore2XtextKWLexersym.Char_A;
        tokenKind['B'] = Ecore2XtextKWLexersym.Char_B;
        tokenKind['C'] = Ecore2XtextKWLexersym.Char_C;
        tokenKind['D'] = Ecore2XtextKWLexersym.Char_D;
        tokenKind['E'] = Ecore2XtextKWLexersym.Char_E;
        tokenKind['F'] = Ecore2XtextKWLexersym.Char_F;
        tokenKind['G'] = Ecore2XtextKWLexersym.Char_G;
        tokenKind['H'] = Ecore2XtextKWLexersym.Char_H;
        tokenKind['I'] = Ecore2XtextKWLexersym.Char_I;
        tokenKind['J'] = Ecore2XtextKWLexersym.Char_J;
        tokenKind['K'] = Ecore2XtextKWLexersym.Char_K;
        tokenKind['L'] = Ecore2XtextKWLexersym.Char_L;
        tokenKind['M'] = Ecore2XtextKWLexersym.Char_M;
        tokenKind['N'] = Ecore2XtextKWLexersym.Char_N;
        tokenKind['O'] = Ecore2XtextKWLexersym.Char_O;
        tokenKind['P'] = Ecore2XtextKWLexersym.Char_P;
        tokenKind['Q'] = Ecore2XtextKWLexersym.Char_Q;
        tokenKind['R'] = Ecore2XtextKWLexersym.Char_R;
        tokenKind['S'] = Ecore2XtextKWLexersym.Char_S;
        tokenKind['T'] = Ecore2XtextKWLexersym.Char_T;
        tokenKind['U'] = Ecore2XtextKWLexersym.Char_U;
        tokenKind['V'] = Ecore2XtextKWLexersym.Char_V;
        tokenKind['W'] = Ecore2XtextKWLexersym.Char_W;
        tokenKind['X'] = Ecore2XtextKWLexersym.Char_X;
        tokenKind['Y'] = Ecore2XtextKWLexersym.Char_Y;
        tokenKind['Z'] = Ecore2XtextKWLexersym.Char_Z;
    };

    final int getKind(char c)
    {
        return (((c & 0xFFFFFF80) == 0) /* 0 <= c < 128? */ ? tokenKind[c] : 0);
    }


    public Ecore2XtextKWLexer(char[] inputChars, int identifierKind)
    {
        this.inputChars = inputChars;
        keywordKind[0] = identifierKind;

        //
        // Rule 1:  KeyWord ::= a b s t r a c t
        //
        
		keywordKind[1] = (Ecore2XtextParsersym.TK_abstract);
	  
	
        //
        // Rule 2:  KeyWord ::= c h a n g e a b l e
        //
        
		keywordKind[2] = (Ecore2XtextParsersym.TK_changeable);
	  
	
        //
        // Rule 3:  KeyWord ::= c o n t a i n m e n t
        //
        
		keywordKind[3] = (Ecore2XtextParsersym.TK_containment);
	  
	
        //
        // Rule 4:  KeyWord ::= d e f a u l t V a l u e L i t e r a l
        //
        
		keywordKind[4] = (Ecore2XtextParsersym.TK_defaultValueLiteral);
	  
	
        //
        // Rule 5:  KeyWord ::= d e r i v e d
        //
        
		keywordKind[5] = (Ecore2XtextParsersym.TK_derived);
	  
	
        //
        // Rule 6:  KeyWord ::= e C l a s s i f i e r
        //
        
		keywordKind[6] = (Ecore2XtextParsersym.TK_eClassifier);
	  
	
        //
        // Rule 7:  KeyWord ::= e E x c e p t i o n s
        //
        
		keywordKind[7] = (Ecore2XtextParsersym.TK_eExceptions);
	  
	
        //
        // Rule 8:  KeyWord ::= e O p p o s i t e
        //
        
		keywordKind[8] = (Ecore2XtextParsersym.TK_eOpposite);
	  
	
        //
        // Rule 9:  KeyWord ::= e S u p e r T y p e s
        //
        
		keywordKind[9] = (Ecore2XtextParsersym.TK_eSuperTypes);
	  
	
        //
        // Rule 10:  KeyWord ::= e T y p e
        //
        
		keywordKind[10] = (Ecore2XtextParsersym.TK_eType);
	  
	
        //
        // Rule 11:  KeyWord ::= e n c o d i n g
        //
        
		keywordKind[11] = (Ecore2XtextParsersym.TK_encoding);
	  
	
        //
        // Rule 12:  KeyWord ::= i n s t a n c e C l a s s N a m e
        //
        
		keywordKind[12] = (Ecore2XtextParsersym.TK_instanceClassName);
	  
	
        //
        // Rule 13:  KeyWord ::= k e y
        //
        
		keywordKind[13] = (Ecore2XtextParsersym.TK_key);
	  
	
        //
        // Rule 14:  KeyWord ::= l o w e r B o u n d
        //
        
		keywordKind[14] = (Ecore2XtextParsersym.TK_lowerBound);
	  
	
        //
        // Rule 15:  KeyWord ::= n a m e
        //
        
		keywordKind[15] = (Ecore2XtextParsersym.TK_name);
	  
	
        //
        // Rule 16:  KeyWord ::= n s P r e f i x
        //
        
		keywordKind[16] = (Ecore2XtextParsersym.TK_nsPrefix);
	  
	
        //
        // Rule 17:  KeyWord ::= n s U R I
        //
        
		keywordKind[17] = (Ecore2XtextParsersym.TK_nsURI);
	  
	
        //
        // Rule 18:  KeyWord ::= o r d e r e d
        //
        
		keywordKind[18] = (Ecore2XtextParsersym.TK_ordered);
	  
	
        //
        // Rule 19:  KeyWord ::= r e s o l v e P r o x i e s
        //
        
		keywordKind[19] = (Ecore2XtextParsersym.TK_resolveProxies);
	  
	
        //
        // Rule 20:  KeyWord ::= s e r i a l i z a b l e
        //
        
		keywordKind[20] = (Ecore2XtextParsersym.TK_serializable);
	  
	
        //
        // Rule 21:  KeyWord ::= s o u r c e
        //
        
		keywordKind[21] = (Ecore2XtextParsersym.TK_source);
	  
	
        //
        // Rule 22:  KeyWord ::= t r a n s i e n t
        //
        
		keywordKind[22] = (Ecore2XtextParsersym.TK_transient);
	  
	
        //
        // Rule 23:  KeyWord ::= t y p e
        //
        
		keywordKind[23] = (Ecore2XtextParsersym.TK_type);
	  
	
        //
        // Rule 24:  KeyWord ::= u n s e t t a b l e
        //
        
		keywordKind[24] = (Ecore2XtextParsersym.TK_unsettable);
	  
	
        //
        // Rule 25:  KeyWord ::= u p p e r B o u n d
        //
        
		keywordKind[25] = (Ecore2XtextParsersym.TK_upperBound);
	  
	
        //
        // Rule 26:  KeyWord ::= v a l u e
        //
        
		keywordKind[26] = (Ecore2XtextParsersym.TK_value);
	  
	
        //
        // Rule 27:  KeyWord ::= v e r s i o n
        //
        
		keywordKind[27] = (Ecore2XtextParsersym.TK_version);
	  
	
        //
        // Rule 28:  KeyWord ::= v o l a t i l e
        //
        
		keywordKind[28] = (Ecore2XtextParsersym.TK_volatile);
	  
	
        //
        // Rule 29:  KeyWord ::= x m i
        //
        
		keywordKind[29] = (Ecore2XtextParsersym.TK_xmi);
	  
	
        //
        // Rule 30:  KeyWord ::= x m l n s
        //
        
		keywordKind[30] = (Ecore2XtextParsersym.TK_xmlns);
	  
	
        //
        // Rule 31:  KeyWord ::= x s i
        //
        
		keywordKind[31] = (Ecore2XtextParsersym.TK_xsi);
	  
	
        for (int i = 0; i < keywordKind.length; i++)
        {
            if (keywordKind[i] == 0)
                keywordKind[i] = identifierKind;
        }
    }
}

