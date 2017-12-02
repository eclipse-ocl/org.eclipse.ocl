/**
*******************************************************************************/

package org.eclipse.ocl.examples.ecore2xtext;

import lpg.runtime.*;

/* imports */
import org.eclipse.emf.ecore.*;
import org.eclipse.emf.ecore.xmi.XMLResource;

public class Ecore2XtextParser extends AbstractEcore2XtextParser implements RuleAction
{
    private static ParseTable prsTable = new Ecore2XtextParserprs();

    private PrsStream prsStream = null;        
    private boolean unimplementedSymbolsWarning = false;
    private DeterministicParser dtParser = null;

    public ParseTable getParseTable() { return prsTable; }

    public DeterministicParser getParser() { return dtParser; }

    private void setResult(Object object) {
    	dtParser.setSym1(object);
    }
    public Object getRhsSym(int i) { return dtParser.getSym(i); }

    public int getRhsTokenIndex(int i) { return dtParser.getToken(i); }
    public IToken getRhsIToken(int i) { return prsStream.getIToken(getRhsTokenIndex(i)); }
    
    public int getRhsFirstTokenIndex(int i) { return dtParser.getFirstToken(i); }
    public IToken getRhsFirstIToken(int i) { return prsStream.getIToken(getRhsFirstTokenIndex(i)); }

    public int getRhsLastTokenIndex(int i) { return dtParser.getLastToken(i); }
    public IToken getRhsLastIToken(int i) { return prsStream.getIToken(getRhsLastTokenIndex(i)); }

    public int getLeftSpan() { return dtParser.getFirstToken(); }
    public IToken getLeftIToken()  { return prsStream.getIToken(getLeftSpan()); }

    public int getRightSpan() { return dtParser.getLastToken(); }
    public IToken getRightIToken() { return prsStream.getIToken(getRightSpan()); }

    public int getRhsErrorTokenIndex(int i)
    {
        int index = dtParser.getToken(i);
        IToken err = prsStream.getIToken(index);
        return (err instanceof ErrorToken ? index : 0);
    }
    public ErrorToken getRhsErrorIToken(int i)
    {
        int index = dtParser.getToken(i);
        IToken err = prsStream.getIToken(index);
        return (ErrorToken) (err instanceof ErrorToken ? err : null);
    }

	protected String getRhsTokenText(int i) { 
		return prsStream.getTokenText(getRhsTokenIndex(i));
	}

    public void reset(ILexStream lexStream)
    {
        prsStream = new PrsStream(lexStream);
        dtParser.reset(prsStream);

        try
        {
            prsStream.remapTerminalSymbols(orderedTerminalSymbols(), prsTable.getEoftSymbol());
        }
        catch(NullExportedSymbolsException e) {
        }
        catch(NullTerminalSymbolsException e) {
        }
        catch(UnimplementedTerminalsException e)
        {
            if (unimplementedSymbolsWarning) {
                java.util.ArrayList<?> unimplemented_symbols = e.getSymbols();
                System.out.println("The Lexer will not scan the following token(s):");
                for (int i = 0; i < unimplemented_symbols.size(); i++)
                {
                    Integer id = (Integer) unimplemented_symbols.get(i);
                    System.out.println("    " + Ecore2XtextParsersym.orderedTerminalSymbols[id.intValue()]);               
                }
                System.out.println();
            }
        }
        catch(UndefinedEofSymbolException e)
        {
            throw new Error(new UndefinedEofSymbolException
                                ("The Lexer does not implement the Eof symbol " +
                                 Ecore2XtextParsersym.orderedTerminalSymbols[prsTable.getEoftSymbol()]));
        }
    }
    
    public Ecore2XtextParser(org.eclipse.emf.ecore.xmi.XMLResource xmlResource)
    {
		super(xmlResource);
        try
        {
            dtParser = new DeterministicParser(prsStream, prsTable, (RuleAction) this);
        }
        catch (NotDeterministicParseTableException e)
        {
            throw new Error(new NotDeterministicParseTableException
                                ("Regenerate Ecore2XtextParserprs.java with -NOBACKTRACK option"));
        }
        catch (BadParseSymFileException e)
        {
            throw new Error(new BadParseSymFileException("Bad Parser Symbol File -- Ecore2XtextParsersym.java. Regenerate Ecore2XtextParserprs.java"));
        }
    }

    public Ecore2XtextParser(org.eclipse.emf.ecore.xmi.XMLResource xmlResource, ILexStream lexStream)
    {
        this(xmlResource);
        reset(lexStream);
    }

    public int numTokenKinds() { return Ecore2XtextParsersym.numTokenKinds; }
    public String[] orderedTerminalSymbols() { return Ecore2XtextParsersym.orderedTerminalSymbols; }
    public String getTokenKindName(int kind) { return Ecore2XtextParsersym.orderedTerminalSymbols[kind]; }            
    public int getEOFTokenKind() { return prsTable.getEoftSymbol(); }
    public IPrsStream getIPrsStream() { return prsStream; }

    /**
     * @deprecated replaced by {@link #getIPrsStream()}
     *
     */
    public PrsStream getPrsStream() { return prsStream; }

    /**
     * @deprecated replaced by {@link #getIPrsStream()}
     *
     */
    public PrsStream getParseStream() { return prsStream; }

    public XMLResource parser()
    {
        return parser(null, 0);
    }
        
    public XMLResource parser(Monitor monitor)
    {
        return parser(monitor, 0);
    }
        
    public XMLResource parser(int error_repair_count)
    {
        return parser(null, error_repair_count);
    }
        
    public XMLResource parser(Monitor monitor, int error_repair_count)
    {
        dtParser.setMonitor(monitor);

        try
        {
            return (XMLResource) dtParser.parse();
        }
        catch (BadParseException e)
        {
            prsStream.reset(e.error_token); // point to error token

            DiagnoseParser diagnoseParser = new DiagnoseParser(prsStream, prsTable);
            diagnoseParser.diagnose(e.error_token);
        }

        return null;
    }

    //
    // Additional entry points, if any
    //
    

    public void ruleAction(int ruleNumber)
    {
        switch (ruleNumber)
        {

            //
            // Rule 1:  BooleanAttribute ::= EcoreFeature_ecore_EClass_abstract
            //
            case 1: {
				setResult(createEObject());
	                  break;
            }
	
            //
            // Rule 2:  BooleanAttribute ::= EcoreFeature_ecore_EDataType_serializable
            //
            case 2: {
				setResult(createEObject());
	                  break;
            }
	
            //
            // Rule 3:  BooleanAttribute ::= EcoreFeature_ecore_EReference_containment
            //
            case 3: {
				setResult(createEObject());
	                  break;
            }
	
            //
            // Rule 4:  BooleanAttribute ::= EcoreFeature_ecore_EReference_resolveProxies
            //
            case 4: {
				setResult(createEObject());
	                  break;
            }
	
            //
            // Rule 5:  BooleanAttribute ::= EcoreFeature_ecore_EStructuralFeature_changeable
            //
            case 5: {
				setResult(createEObject());
	                  break;
            }
	
            //
            // Rule 6:  BooleanAttribute ::= EcoreFeature_ecore_EStructuralFeature_defaultValueLiteral
            //
            case 6: {
				setResult(createEObject());
	                  break;
            }
	
            //
            // Rule 7:  BooleanAttribute ::= EcoreFeature_ecore_EStructuralFeature_derived
            //
            case 7: {
				setResult(createEObject());
	                  break;
            }
	
            //
            // Rule 8:  BooleanAttribute ::= EcoreFeature_ecore_EStructuralFeature_transient
            //
            case 8: {
				setResult(createEObject());
	                  break;
            }
	
            //
            // Rule 9:  BooleanAttribute ::= EcoreFeature_ecore_EStructuralFeature_unsettable
            //
            case 9: {
				setResult(createEObject());
	                  break;
            }
	
            //
            // Rule 10:  BooleanAttribute ::= EcoreFeature_ecore_EStructuralFeature_volatile
            //
            case 10: {
				setResult(createEObject());
	                  break;
            }
	
            //
            // Rule 11:  BooleanAttribute ::= EcoreFeature_ecore_ETypedElement_ordered
            //
            case 11: {
				setResult(createEObject());
	                  break;
            }
	
            //
            // Rule 12:  EcoreClass_ecore_EClass_eOperations ::= LT_e_O_p_e_r_a_t_i_o_n_s SLASH_GT
            //
            case 12: {
				setResult(createEcoreClass(org.eclipse.emf.ecore.EcorePackage.Literals.ECLASS__EOPERATIONS));
	                  break;
            }
	
            //
            // Rule 13:  EcoreClass_ecore_EClass_eOperations ::= LT_e_O_p_e_r_a_t_i_o_n_s EcoreClass_ecore_EClass_eOperations_8 SLASH_GT
            //
            case 13: {
				setResult(createEcoreClass(org.eclipse.emf.ecore.EcorePackage.Literals.ECLASS__EOPERATIONS, getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 14:  EcoreClass_ecore_EClass_eOperations ::= LT_e_O_p_e_r_a_t_i_o_n_s GT LT_SLASH_e_O_p_e_r_a_t_i_o_n_s_GT
            //
            case 14: {
				setResult(createEcoreClass(org.eclipse.emf.ecore.EcorePackage.Literals.ECLASS__EOPERATIONS));
	                  break;
            }
	
            //
            // Rule 15:  EcoreClass_ecore_EClass_eOperations ::= LT_e_O_p_e_r_a_t_i_o_n_s EcoreClass_ecore_EClass_eOperations_8 GT LT_SLASH_e_O_p_e_r_a_t_i_o_n_s_GT
            //
            case 15: {
				setResult(createEcoreClass(org.eclipse.emf.ecore.EcorePackage.Literals.ECLASS__EOPERATIONS, getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 16:  EcoreClass_ecore_EClass_eOperations ::= LT_e_O_p_e_r_a_t_i_o_n_s GT EcoreClass_ecore_EClass_eOperations_3 LT_SLASH_e_O_p_e_r_a_t_i_o_n_s_GT
            //
            case 16: {
				setResult(createEcoreClass(org.eclipse.emf.ecore.EcorePackage.Literals.ECLASS__EOPERATIONS, getRhsSym(3)));
	                  break;
            }
	
            //
            // Rule 17:  EcoreClass_ecore_EClass_eOperations ::= LT_e_O_p_e_r_a_t_i_o_n_s EcoreClass_ecore_EClass_eOperations_8 GT EcoreClass_ecore_EClass_eOperations_3 LT_SLASH_e_O_p_e_r_a_t_i_o_n_s_GT
            //
            case 17: {
				setResult(createEcoreClass(org.eclipse.emf.ecore.EcorePackage.Literals.ECLASS__EOPERATIONS, getRhsSym(2), getRhsSym(4)));
	                  break;
            }
	
            //
            // Rule 18:  EcoreClass_ecore_EClass_eOperations_3 ::= EcoreClass_ecore_EModelElement_eAnnotations
            //
            case 18: {
				setResult(SetAttributes.create(getRhsSym(1)));
	                  break;
            }
	
            //
            // Rule 19:  EcoreClass_ecore_EClass_eOperations_3 ::= EcoreClass_ecore_EOperation_eParameters
            //
            case 19: {
				setResult(SetAttributes.create(getRhsSym(1)));
	                  break;
            }
	
            //
            // Rule 20:  EcoreClass_ecore_EClass_eOperations_3 ::= EcoreClass_ecore_ETypedElement_eGenericType
            //
            case 20: {
				setResult(SetAttributes.create(getRhsSym(1)));
	                  break;
            }
	
            //
            // Rule 21:  EcoreClass_ecore_EClass_eOperations_3 ::= OtherElement
            //
            case 21: {
				setResult(SetAttributes.create(getRhsSym(1)));
	                  break;
            }
	
            //
            // Rule 22:  EcoreClass_ecore_EClass_eOperations_3 ::= EcoreClass_ecore_EClass_eOperations_3 EcoreClass_ecore_EModelElement_eAnnotations
            //
            case 22: {
				setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 23:  EcoreClass_ecore_EClass_eOperations_3 ::= EcoreClass_ecore_EClass_eOperations_3 EcoreClass_ecore_EOperation_eParameters
            //
            case 23: {
				setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 24:  EcoreClass_ecore_EClass_eOperations_3 ::= EcoreClass_ecore_EClass_eOperations_3 EcoreClass_ecore_ETypedElement_eGenericType
            //
            case 24: {
				setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 25:  EcoreClass_ecore_EClass_eOperations_3 ::= EcoreClass_ecore_EClass_eOperations_3 OtherElement
            //
            case 25: {
				setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 26:  EcoreClass_ecore_EClass_eOperations_8 ::= EcoreFeature_ecore_ENamedElement_name
            //
            case 26: {
				setResult(SetAttributes.create(getRhsSym(1)));
	                  break;
            }
	
            //
            // Rule 27:  EcoreClass_ecore_EClass_eOperations_8 ::= EcoreFeature_ecore_EOperation_eExceptions
            //
            case 27: {
				setResult(SetAttributes.create(getRhsSym(1)));
	                  break;
            }
	
            //
            // Rule 28:  EcoreClass_ecore_EClass_eOperations_8 ::= EcoreFeature_ecore_ETypedElement_eType
            //
            case 28: {
				setResult(SetAttributes.create(getRhsSym(1)));
	                  break;
            }
	
            //
            // Rule 29:  EcoreClass_ecore_EClass_eOperations_8 ::= OtherAttribute
            //
            case 29: {
				setResult(SetAttributes.create(getRhsSym(1)));
	                  break;
            }
	
            //
            // Rule 30:  EcoreClass_ecore_EClass_eOperations_8 ::= XMLAttribute_xsi_type
            //
            case 30: {
				setResult(SetAttributes.create(getRhsSym(1)));
	                  break;
            }
	
            //
            // Rule 31:  EcoreClass_ecore_EClass_eOperations_8 ::= EcoreClass_ecore_EClass_eOperations_8 EcoreFeature_ecore_ENamedElement_name
            //
            case 31: {
				setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 32:  EcoreClass_ecore_EClass_eOperations_8 ::= EcoreClass_ecore_EClass_eOperations_8 EcoreFeature_ecore_EOperation_eExceptions
            //
            case 32: {
				setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 33:  EcoreClass_ecore_EClass_eOperations_8 ::= EcoreClass_ecore_EClass_eOperations_8 EcoreFeature_ecore_ETypedElement_eType
            //
            case 33: {
				setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 34:  EcoreClass_ecore_EClass_eOperations_8 ::= EcoreClass_ecore_EClass_eOperations_8 OtherAttribute
            //
            case 34: {
				setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 35:  EcoreClass_ecore_EClass_eOperations_8 ::= EcoreClass_ecore_EClass_eOperations_8 XMLAttribute_xsi_type
            //
            case 35: {
				setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 36:  EcoreClass_ecore_EClass_eStructuralFeatures ::= LT_e_S_t_r_u_c_t_u_r_a_l_F_e_a_t_u_r_e_s SLASH_GT
            //
            case 36: {
				setResult(createEcoreClass(org.eclipse.emf.ecore.EcorePackage.Literals.ECLASS__ESTRUCTURAL_FEATURES));
	                  break;
            }
	
            //
            // Rule 37:  EcoreClass_ecore_EClass_eStructuralFeatures ::= LT_e_S_t_r_u_c_t_u_r_a_l_F_e_a_t_u_r_e_s EcoreClass_ecore_EClass_eStructuralFeatures_8 SLASH_GT
            //
            case 37: {
				setResult(createEcoreClass(org.eclipse.emf.ecore.EcorePackage.Literals.ECLASS__ESTRUCTURAL_FEATURES, getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 38:  EcoreClass_ecore_EClass_eStructuralFeatures ::= LT_e_S_t_r_u_c_t_u_r_a_l_F_e_a_t_u_r_e_s GT LT_SLASH_e_S_t_r_u_c_t_u_r_a_l_F_e_a_t_u_r_e_s_GT
            //
            case 38: {
				setResult(createEcoreClass(org.eclipse.emf.ecore.EcorePackage.Literals.ECLASS__ESTRUCTURAL_FEATURES));
	                  break;
            }
	
            //
            // Rule 39:  EcoreClass_ecore_EClass_eStructuralFeatures ::= LT_e_S_t_r_u_c_t_u_r_a_l_F_e_a_t_u_r_e_s EcoreClass_ecore_EClass_eStructuralFeatures_8 GT LT_SLASH_e_S_t_r_u_c_t_u_r_a_l_F_e_a_t_u_r_e_s_GT
            //
            case 39: {
				setResult(createEcoreClass(org.eclipse.emf.ecore.EcorePackage.Literals.ECLASS__ESTRUCTURAL_FEATURES, getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 40:  EcoreClass_ecore_EClass_eStructuralFeatures ::= LT_e_S_t_r_u_c_t_u_r_a_l_F_e_a_t_u_r_e_s GT EcoreClass_ecore_EClass_eStructuralFeatures_3 LT_SLASH_e_S_t_r_u_c_t_u_r_a_l_F_e_a_t_u_r_e_s_GT
            //
            case 40: {
				setResult(createEcoreClass(org.eclipse.emf.ecore.EcorePackage.Literals.ECLASS__ESTRUCTURAL_FEATURES, getRhsSym(3)));
	                  break;
            }
	
            //
            // Rule 41:  EcoreClass_ecore_EClass_eStructuralFeatures ::= LT_e_S_t_r_u_c_t_u_r_a_l_F_e_a_t_u_r_e_s EcoreClass_ecore_EClass_eStructuralFeatures_8 GT EcoreClass_ecore_EClass_eStructuralFeatures_3 LT_SLASH_e_S_t_r_u_c_t_u_r_a_l_F_e_a_t_u_r_e_s_GT
            //
            case 41: {
				setResult(createEcoreClass(org.eclipse.emf.ecore.EcorePackage.Literals.ECLASS__ESTRUCTURAL_FEATURES, getRhsSym(2), getRhsSym(4)));
	                  break;
            }
	
            //
            // Rule 42:  EcoreClass_ecore_EClass_eStructuralFeatures_3 ::= EcoreClass_ecore_EModelElement_eAnnotations
            //
            case 42: {
				setResult(SetAttributes.create(getRhsSym(1)));
	                  break;
            }
	
            //
            // Rule 43:  EcoreClass_ecore_EClass_eStructuralFeatures_3 ::= EcoreClass_ecore_ETypedElement_eGenericType
            //
            case 43: {
				setResult(SetAttributes.create(getRhsSym(1)));
	                  break;
            }
	
            //
            // Rule 44:  EcoreClass_ecore_EClass_eStructuralFeatures_3 ::= OtherElement
            //
            case 44: {
				setResult(SetAttributes.create(getRhsSym(1)));
	                  break;
            }
	
            //
            // Rule 45:  EcoreClass_ecore_EClass_eStructuralFeatures_3 ::= EcoreClass_ecore_EClass_eStructuralFeatures_3 EcoreClass_ecore_EModelElement_eAnnotations
            //
            case 45: {
				setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 46:  EcoreClass_ecore_EClass_eStructuralFeatures_3 ::= EcoreClass_ecore_EClass_eStructuralFeatures_3 EcoreClass_ecore_ETypedElement_eGenericType
            //
            case 46: {
				setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 47:  EcoreClass_ecore_EClass_eStructuralFeatures_3 ::= EcoreClass_ecore_EClass_eStructuralFeatures_3 OtherElement
            //
            case 47: {
				setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 48:  EcoreClass_ecore_EClass_eStructuralFeatures_8 ::= EcoreFeature_ecore_ENamedElement_name
            //
            case 48: {
				setResult(SetAttributes.create(getRhsSym(1)));
	                  break;
            }
	
            //
            // Rule 49:  EcoreClass_ecore_EClass_eStructuralFeatures_8 ::= EcoreFeature_ecore_EReference_containment
            //
            case 49: {
				setResult(SetAttributes.create(getRhsSym(1)));
	                  break;
            }
	
            //
            // Rule 50:  EcoreClass_ecore_EClass_eStructuralFeatures_8 ::= EcoreFeature_ecore_EReference_eOpposite
            //
            case 50: {
				setResult(SetAttributes.create(getRhsSym(1)));
	                  break;
            }
	
            //
            // Rule 51:  EcoreClass_ecore_EClass_eStructuralFeatures_8 ::= EcoreFeature_ecore_EReference_resolveProxies
            //
            case 51: {
				setResult(SetAttributes.create(getRhsSym(1)));
	                  break;
            }
	
            //
            // Rule 52:  EcoreClass_ecore_EClass_eStructuralFeatures_8 ::= EcoreFeature_ecore_EStructuralFeature_changeable
            //
            case 52: {
				setResult(SetAttributes.create(getRhsSym(1)));
	                  break;
            }
	
            //
            // Rule 53:  EcoreClass_ecore_EClass_eStructuralFeatures_8 ::= EcoreFeature_ecore_EStructuralFeature_defaultValueLiteral
            //
            case 53: {
				setResult(SetAttributes.create(getRhsSym(1)));
	                  break;
            }
	
            //
            // Rule 54:  EcoreClass_ecore_EClass_eStructuralFeatures_8 ::= EcoreFeature_ecore_EStructuralFeature_derived
            //
            case 54: {
				setResult(SetAttributes.create(getRhsSym(1)));
	                  break;
            }
	
            //
            // Rule 55:  EcoreClass_ecore_EClass_eStructuralFeatures_8 ::= EcoreFeature_ecore_EStructuralFeature_transient
            //
            case 55: {
				setResult(SetAttributes.create(getRhsSym(1)));
	                  break;
            }
	
            //
            // Rule 56:  EcoreClass_ecore_EClass_eStructuralFeatures_8 ::= EcoreFeature_ecore_EStructuralFeature_unsettable
            //
            case 56: {
				setResult(SetAttributes.create(getRhsSym(1)));
	                  break;
            }
	
            //
            // Rule 57:  EcoreClass_ecore_EClass_eStructuralFeatures_8 ::= EcoreFeature_ecore_EStructuralFeature_volatile
            //
            case 57: {
				setResult(SetAttributes.create(getRhsSym(1)));
	                  break;
            }
	
            //
            // Rule 58:  EcoreClass_ecore_EClass_eStructuralFeatures_8 ::= EcoreFeature_ecore_ETypedElement_eType
            //
            case 58: {
				setResult(SetAttributes.create(getRhsSym(1)));
	                  break;
            }
	
            //
            // Rule 59:  EcoreClass_ecore_EClass_eStructuralFeatures_8 ::= EcoreFeature_ecore_ETypedElement_lowerBound
            //
            case 59: {
				setResult(SetAttributes.create(getRhsSym(1)));
	                  break;
            }
	
            //
            // Rule 60:  EcoreClass_ecore_EClass_eStructuralFeatures_8 ::= EcoreFeature_ecore_ETypedElement_ordered
            //
            case 60: {
				setResult(SetAttributes.create(getRhsSym(1)));
	                  break;
            }
	
            //
            // Rule 61:  EcoreClass_ecore_EClass_eStructuralFeatures_8 ::= EcoreFeature_ecore_ETypedElement_upperBound
            //
            case 61: {
				setResult(SetAttributes.create(getRhsSym(1)));
	                  break;
            }
	
            //
            // Rule 62:  EcoreClass_ecore_EClass_eStructuralFeatures_8 ::= OtherAttribute
            //
            case 62: {
				setResult(SetAttributes.create(getRhsSym(1)));
	                  break;
            }
	
            //
            // Rule 63:  EcoreClass_ecore_EClass_eStructuralFeatures_8 ::= XMLAttribute_xsi_type
            //
            case 63: {
				setResult(SetAttributes.create(getRhsSym(1)));
	                  break;
            }
	
            //
            // Rule 64:  EcoreClass_ecore_EClass_eStructuralFeatures_8 ::= EcoreClass_ecore_EClass_eStructuralFeatures_8 EcoreFeature_ecore_ENamedElement_name
            //
            case 64: {
				setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 65:  EcoreClass_ecore_EClass_eStructuralFeatures_8 ::= EcoreClass_ecore_EClass_eStructuralFeatures_8 EcoreFeature_ecore_EReference_containment
            //
            case 65: {
				setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 66:  EcoreClass_ecore_EClass_eStructuralFeatures_8 ::= EcoreClass_ecore_EClass_eStructuralFeatures_8 EcoreFeature_ecore_EReference_eOpposite
            //
            case 66: {
				setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 67:  EcoreClass_ecore_EClass_eStructuralFeatures_8 ::= EcoreClass_ecore_EClass_eStructuralFeatures_8 EcoreFeature_ecore_EReference_resolveProxies
            //
            case 67: {
				setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 68:  EcoreClass_ecore_EClass_eStructuralFeatures_8 ::= EcoreClass_ecore_EClass_eStructuralFeatures_8 EcoreFeature_ecore_EStructuralFeature_changeable
            //
            case 68: {
				setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 69:  EcoreClass_ecore_EClass_eStructuralFeatures_8 ::= EcoreClass_ecore_EClass_eStructuralFeatures_8 EcoreFeature_ecore_EStructuralFeature_defaultValueLiteral
            //
            case 69: {
				setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 70:  EcoreClass_ecore_EClass_eStructuralFeatures_8 ::= EcoreClass_ecore_EClass_eStructuralFeatures_8 EcoreFeature_ecore_EStructuralFeature_derived
            //
            case 70: {
				setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 71:  EcoreClass_ecore_EClass_eStructuralFeatures_8 ::= EcoreClass_ecore_EClass_eStructuralFeatures_8 EcoreFeature_ecore_EStructuralFeature_transient
            //
            case 71: {
				setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 72:  EcoreClass_ecore_EClass_eStructuralFeatures_8 ::= EcoreClass_ecore_EClass_eStructuralFeatures_8 EcoreFeature_ecore_EStructuralFeature_unsettable
            //
            case 72: {
				setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 73:  EcoreClass_ecore_EClass_eStructuralFeatures_8 ::= EcoreClass_ecore_EClass_eStructuralFeatures_8 EcoreFeature_ecore_EStructuralFeature_volatile
            //
            case 73: {
				setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 74:  EcoreClass_ecore_EClass_eStructuralFeatures_8 ::= EcoreClass_ecore_EClass_eStructuralFeatures_8 EcoreFeature_ecore_ETypedElement_eType
            //
            case 74: {
				setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 75:  EcoreClass_ecore_EClass_eStructuralFeatures_8 ::= EcoreClass_ecore_EClass_eStructuralFeatures_8 EcoreFeature_ecore_ETypedElement_lowerBound
            //
            case 75: {
				setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 76:  EcoreClass_ecore_EClass_eStructuralFeatures_8 ::= EcoreClass_ecore_EClass_eStructuralFeatures_8 EcoreFeature_ecore_ETypedElement_ordered
            //
            case 76: {
				setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 77:  EcoreClass_ecore_EClass_eStructuralFeatures_8 ::= EcoreClass_ecore_EClass_eStructuralFeatures_8 EcoreFeature_ecore_ETypedElement_upperBound
            //
            case 77: {
				setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 78:  EcoreClass_ecore_EClass_eStructuralFeatures_8 ::= EcoreClass_ecore_EClass_eStructuralFeatures_8 OtherAttribute
            //
            case 78: {
				setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 79:  EcoreClass_ecore_EClass_eStructuralFeatures_8 ::= EcoreClass_ecore_EClass_eStructuralFeatures_8 XMLAttribute_xsi_type
            //
            case 79: {
				setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 80:  EcoreClass_ecore_EGenericType_eTypeArguments ::= LT_e_T_y_p_e_A_r_g_u_m_e_n_t_s SLASH_GT
            //
            case 80: {
				setResult(createEcoreClass(org.eclipse.emf.ecore.EcorePackage.Literals.EGENERIC_TYPE__ETYPE_ARGUMENTS));
	                  break;
            }
	
            //
            // Rule 81:  EcoreClass_ecore_EGenericType_eTypeArguments ::= LT_e_T_y_p_e_A_r_g_u_m_e_n_t_s EcoreClass_ecore_EGenericType_eTypeArguments_8 SLASH_GT
            //
            case 81: {
				setResult(createEcoreClass(org.eclipse.emf.ecore.EcorePackage.Literals.EGENERIC_TYPE__ETYPE_ARGUMENTS, getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 82:  EcoreClass_ecore_EGenericType_eTypeArguments ::= LT_e_T_y_p_e_A_r_g_u_m_e_n_t_s GT LT_SLASH_e_T_y_p_e_A_r_g_u_m_e_n_t_s_GT
            //
            case 82: {
				setResult(createEcoreClass(org.eclipse.emf.ecore.EcorePackage.Literals.EGENERIC_TYPE__ETYPE_ARGUMENTS));
	                  break;
            }
	
            //
            // Rule 83:  EcoreClass_ecore_EGenericType_eTypeArguments ::= LT_e_T_y_p_e_A_r_g_u_m_e_n_t_s EcoreClass_ecore_EGenericType_eTypeArguments_8 GT LT_SLASH_e_T_y_p_e_A_r_g_u_m_e_n_t_s_GT
            //
            case 83: {
				setResult(createEcoreClass(org.eclipse.emf.ecore.EcorePackage.Literals.EGENERIC_TYPE__ETYPE_ARGUMENTS, getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 84:  EcoreClass_ecore_EGenericType_eTypeArguments ::= LT_e_T_y_p_e_A_r_g_u_m_e_n_t_s GT EcoreClass_ecore_EGenericType_eTypeArguments_3 LT_SLASH_e_T_y_p_e_A_r_g_u_m_e_n_t_s_GT
            //
            case 84: {
				setResult(createEcoreClass(org.eclipse.emf.ecore.EcorePackage.Literals.EGENERIC_TYPE__ETYPE_ARGUMENTS, getRhsSym(3)));
	                  break;
            }
	
            //
            // Rule 85:  EcoreClass_ecore_EGenericType_eTypeArguments ::= LT_e_T_y_p_e_A_r_g_u_m_e_n_t_s EcoreClass_ecore_EGenericType_eTypeArguments_8 GT EcoreClass_ecore_EGenericType_eTypeArguments_3 LT_SLASH_e_T_y_p_e_A_r_g_u_m_e_n_t_s_GT
            //
            case 85: {
				setResult(createEcoreClass(org.eclipse.emf.ecore.EcorePackage.Literals.EGENERIC_TYPE__ETYPE_ARGUMENTS, getRhsSym(2), getRhsSym(4)));
	                  break;
            }
	
            //
            // Rule 86:  EcoreClass_ecore_EGenericType_eTypeArguments_3 ::= EcoreClass_ecore_EModelElement_eAnnotations
            //
            case 86: {
				setResult(SetAttributes.create(getRhsSym(1)));
	                  break;
            }
	
            //
            // Rule 87:  EcoreClass_ecore_EGenericType_eTypeArguments_3 ::= OtherElement
            //
            case 87: {
				setResult(SetAttributes.create(getRhsSym(1)));
	                  break;
            }
	
            //
            // Rule 88:  EcoreClass_ecore_EGenericType_eTypeArguments_3 ::= EcoreClass_ecore_EGenericType_eTypeArguments_3 EcoreClass_ecore_EModelElement_eAnnotations
            //
            case 88: {
				setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 89:  EcoreClass_ecore_EGenericType_eTypeArguments_3 ::= EcoreClass_ecore_EGenericType_eTypeArguments_3 OtherElement
            //
            case 89: {
				setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 90:  EcoreClass_ecore_EGenericType_eTypeArguments_8 ::= EcoreFeature_ecore_EPackage_eClassifiers
            //
            case 90: {
				setResult(SetAttributes.create(getRhsSym(1)));
	                  break;
            }
	
            //
            // Rule 91:  EcoreClass_ecore_EGenericType_eTypeArguments_8 ::= OtherAttribute
            //
            case 91: {
				setResult(SetAttributes.create(getRhsSym(1)));
	                  break;
            }
	
            //
            // Rule 92:  EcoreClass_ecore_EGenericType_eTypeArguments_8 ::= XMLAttribute_xsi_type
            //
            case 92: {
				setResult(SetAttributes.create(getRhsSym(1)));
	                  break;
            }
	
            //
            // Rule 93:  EcoreClass_ecore_EGenericType_eTypeArguments_8 ::= EcoreClass_ecore_EGenericType_eTypeArguments_8 EcoreFeature_ecore_EPackage_eClassifiers
            //
            case 93: {
				setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 94:  EcoreClass_ecore_EGenericType_eTypeArguments_8 ::= EcoreClass_ecore_EGenericType_eTypeArguments_8 OtherAttribute
            //
            case 94: {
				setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 95:  EcoreClass_ecore_EGenericType_eTypeArguments_8 ::= EcoreClass_ecore_EGenericType_eTypeArguments_8 XMLAttribute_xsi_type
            //
            case 95: {
				setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 96:  EcoreClass_ecore_EModelElement_eAnnotations ::= LT_e_A_n_n_o_t_a_t_i_o_n_s SLASH_GT
            //
            case 96: {
				setResult(createEcoreClass(org.eclipse.emf.ecore.EcorePackage.Literals.EMODEL_ELEMENT__EANNOTATIONS));
	                  break;
            }
	
            //
            // Rule 97:  EcoreClass_ecore_EModelElement_eAnnotations ::= LT_e_A_n_n_o_t_a_t_i_o_n_s EcoreClass_ecore_EModelElement_eAnnotations_8 SLASH_GT
            //
            case 97: {
				setResult(createEcoreClass(org.eclipse.emf.ecore.EcorePackage.Literals.EMODEL_ELEMENT__EANNOTATIONS, getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 98:  EcoreClass_ecore_EModelElement_eAnnotations ::= LT_e_A_n_n_o_t_a_t_i_o_n_s GT LT_SLASH_e_A_n_n_o_t_a_t_i_o_n_s_GT
            //
            case 98: {
				setResult(createEcoreClass(org.eclipse.emf.ecore.EcorePackage.Literals.EMODEL_ELEMENT__EANNOTATIONS));
	                  break;
            }
	
            //
            // Rule 99:  EcoreClass_ecore_EModelElement_eAnnotations ::= LT_e_A_n_n_o_t_a_t_i_o_n_s EcoreClass_ecore_EModelElement_eAnnotations_8 GT LT_SLASH_e_A_n_n_o_t_a_t_i_o_n_s_GT
            //
            case 99: {
				setResult(createEcoreClass(org.eclipse.emf.ecore.EcorePackage.Literals.EMODEL_ELEMENT__EANNOTATIONS, getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 100:  EcoreClass_ecore_EModelElement_eAnnotations ::= LT_e_A_n_n_o_t_a_t_i_o_n_s GT EcoreClass_ecore_EModelElement_eAnnotations_3 LT_SLASH_e_A_n_n_o_t_a_t_i_o_n_s_GT
            //
            case 100: {
				setResult(createEcoreClass(org.eclipse.emf.ecore.EcorePackage.Literals.EMODEL_ELEMENT__EANNOTATIONS, getRhsSym(3)));
	                  break;
            }
	
            //
            // Rule 101:  EcoreClass_ecore_EModelElement_eAnnotations ::= LT_e_A_n_n_o_t_a_t_i_o_n_s EcoreClass_ecore_EModelElement_eAnnotations_8 GT EcoreClass_ecore_EModelElement_eAnnotations_3 LT_SLASH_e_A_n_n_o_t_a_t_i_o_n_s_GT
            //
            case 101: {
				setResult(createEcoreClass(org.eclipse.emf.ecore.EcorePackage.Literals.EMODEL_ELEMENT__EANNOTATIONS, getRhsSym(2), getRhsSym(4)));
	                  break;
            }
	
            //
            // Rule 102:  EcoreClass_ecore_EModelElement_eAnnotations_3 ::= EcoreClass_ecore_EModelElement_eAnnotations
            //
            case 102: {
				setResult(SetAttributes.create(getRhsSym(1)));
	                  break;
            }
	
            //
            // Rule 103:  EcoreClass_ecore_EModelElement_eAnnotations_3 ::= EcoreFeature_ecore_EAnnotation_details
            //
            case 103: {
				setResult(SetAttributes.create(getRhsSym(1)));
	                  break;
            }
	
            //
            // Rule 104:  EcoreClass_ecore_EModelElement_eAnnotations_3 ::= OtherElement
            //
            case 104: {
				setResult(SetAttributes.create(getRhsSym(1)));
	                  break;
            }
	
            //
            // Rule 105:  EcoreClass_ecore_EModelElement_eAnnotations_3 ::= EcoreClass_ecore_EModelElement_eAnnotations_3 EcoreClass_ecore_EModelElement_eAnnotations
            //
            case 105: {
				setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 106:  EcoreClass_ecore_EModelElement_eAnnotations_3 ::= EcoreClass_ecore_EModelElement_eAnnotations_3 EcoreFeature_ecore_EAnnotation_details
            //
            case 106: {
				setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 107:  EcoreClass_ecore_EModelElement_eAnnotations_3 ::= EcoreClass_ecore_EModelElement_eAnnotations_3 OtherElement
            //
            case 107: {
				setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 108:  EcoreClass_ecore_EModelElement_eAnnotations_8 ::= EcoreFeature_ecore_EAnnotation_source
            //
            case 108: {
				setResult(SetAttributes.create(getRhsSym(1)));
	                  break;
            }
	
            //
            // Rule 109:  EcoreClass_ecore_EModelElement_eAnnotations_8 ::= OtherAttribute
            //
            case 109: {
				setResult(SetAttributes.create(getRhsSym(1)));
	                  break;
            }
	
            //
            // Rule 110:  EcoreClass_ecore_EModelElement_eAnnotations_8 ::= XMLAttribute_xsi_type
            //
            case 110: {
				setResult(SetAttributes.create(getRhsSym(1)));
	                  break;
            }
	
            //
            // Rule 111:  EcoreClass_ecore_EModelElement_eAnnotations_8 ::= EcoreClass_ecore_EModelElement_eAnnotations_8 EcoreFeature_ecore_EAnnotation_source
            //
            case 111: {
				setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 112:  EcoreClass_ecore_EModelElement_eAnnotations_8 ::= EcoreClass_ecore_EModelElement_eAnnotations_8 OtherAttribute
            //
            case 112: {
				setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 113:  EcoreClass_ecore_EModelElement_eAnnotations_8 ::= EcoreClass_ecore_EModelElement_eAnnotations_8 XMLAttribute_xsi_type
            //
            case 113: {
				setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 114:  EcoreClass_ecore_EOperation_eParameters ::= LT_e_P_a_r_a_m_e_t_e_r_s SLASH_GT
            //
            case 114: {
				setResult(createEcoreClass(org.eclipse.emf.ecore.EcorePackage.Literals.EOPERATION__EPARAMETERS));
	                  break;
            }
	
            //
            // Rule 115:  EcoreClass_ecore_EOperation_eParameters ::= LT_e_P_a_r_a_m_e_t_e_r_s EcoreClass_ecore_EOperation_eParameters_8 SLASH_GT
            //
            case 115: {
				setResult(createEcoreClass(org.eclipse.emf.ecore.EcorePackage.Literals.EOPERATION__EPARAMETERS, getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 116:  EcoreClass_ecore_EOperation_eParameters ::= LT_e_P_a_r_a_m_e_t_e_r_s GT LT_SLASH_e_P_a_r_a_m_e_t_e_r_s_GT
            //
            case 116: {
				setResult(createEcoreClass(org.eclipse.emf.ecore.EcorePackage.Literals.EOPERATION__EPARAMETERS));
	                  break;
            }
	
            //
            // Rule 117:  EcoreClass_ecore_EOperation_eParameters ::= LT_e_P_a_r_a_m_e_t_e_r_s EcoreClass_ecore_EOperation_eParameters_8 GT LT_SLASH_e_P_a_r_a_m_e_t_e_r_s_GT
            //
            case 117: {
				setResult(createEcoreClass(org.eclipse.emf.ecore.EcorePackage.Literals.EOPERATION__EPARAMETERS, getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 118:  EcoreClass_ecore_EOperation_eParameters ::= LT_e_P_a_r_a_m_e_t_e_r_s GT EcoreClass_ecore_EOperation_eParameters_3 LT_SLASH_e_P_a_r_a_m_e_t_e_r_s_GT
            //
            case 118: {
				setResult(createEcoreClass(org.eclipse.emf.ecore.EcorePackage.Literals.EOPERATION__EPARAMETERS, getRhsSym(3)));
	                  break;
            }
	
            //
            // Rule 119:  EcoreClass_ecore_EOperation_eParameters ::= LT_e_P_a_r_a_m_e_t_e_r_s EcoreClass_ecore_EOperation_eParameters_8 GT EcoreClass_ecore_EOperation_eParameters_3 LT_SLASH_e_P_a_r_a_m_e_t_e_r_s_GT
            //
            case 119: {
				setResult(createEcoreClass(org.eclipse.emf.ecore.EcorePackage.Literals.EOPERATION__EPARAMETERS, getRhsSym(2), getRhsSym(4)));
	                  break;
            }
	
            //
            // Rule 120:  EcoreClass_ecore_EOperation_eParameters_3 ::= EcoreClass_ecore_EModelElement_eAnnotations
            //
            case 120: {
				setResult(SetAttributes.create(getRhsSym(1)));
	                  break;
            }
	
            //
            // Rule 121:  EcoreClass_ecore_EOperation_eParameters_3 ::= EcoreClass_ecore_ETypedElement_eGenericType
            //
            case 121: {
				setResult(SetAttributes.create(getRhsSym(1)));
	                  break;
            }
	
            //
            // Rule 122:  EcoreClass_ecore_EOperation_eParameters_3 ::= OtherElement
            //
            case 122: {
				setResult(SetAttributes.create(getRhsSym(1)));
	                  break;
            }
	
            //
            // Rule 123:  EcoreClass_ecore_EOperation_eParameters_3 ::= EcoreClass_ecore_EOperation_eParameters_3 EcoreClass_ecore_EModelElement_eAnnotations
            //
            case 123: {
				setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 124:  EcoreClass_ecore_EOperation_eParameters_3 ::= EcoreClass_ecore_EOperation_eParameters_3 EcoreClass_ecore_ETypedElement_eGenericType
            //
            case 124: {
				setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 125:  EcoreClass_ecore_EOperation_eParameters_3 ::= EcoreClass_ecore_EOperation_eParameters_3 OtherElement
            //
            case 125: {
				setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 126:  EcoreClass_ecore_EOperation_eParameters_8 ::= EcoreFeature_ecore_ENamedElement_name
            //
            case 126: {
				setResult(SetAttributes.create(getRhsSym(1)));
	                  break;
            }
	
            //
            // Rule 127:  EcoreClass_ecore_EOperation_eParameters_8 ::= EcoreFeature_ecore_ETypedElement_eType
            //
            case 127: {
				setResult(SetAttributes.create(getRhsSym(1)));
	                  break;
            }
	
            //
            // Rule 128:  EcoreClass_ecore_EOperation_eParameters_8 ::= OtherAttribute
            //
            case 128: {
				setResult(SetAttributes.create(getRhsSym(1)));
	                  break;
            }
	
            //
            // Rule 129:  EcoreClass_ecore_EOperation_eParameters_8 ::= XMLAttribute_xsi_type
            //
            case 129: {
				setResult(SetAttributes.create(getRhsSym(1)));
	                  break;
            }
	
            //
            // Rule 130:  EcoreClass_ecore_EOperation_eParameters_8 ::= EcoreClass_ecore_EOperation_eParameters_8 EcoreFeature_ecore_ENamedElement_name
            //
            case 130: {
				setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 131:  EcoreClass_ecore_EOperation_eParameters_8 ::= EcoreClass_ecore_EOperation_eParameters_8 EcoreFeature_ecore_ETypedElement_eType
            //
            case 131: {
				setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 132:  EcoreClass_ecore_EOperation_eParameters_8 ::= EcoreClass_ecore_EOperation_eParameters_8 OtherAttribute
            //
            case 132: {
				setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 133:  EcoreClass_ecore_EOperation_eParameters_8 ::= EcoreClass_ecore_EOperation_eParameters_8 XMLAttribute_xsi_type
            //
            case 133: {
				setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 134:  EcoreClass_ecore_EPackage_eClassifiers ::= LT_e_C_l_a_s_s_i_f_i_e_r_s SLASH_GT
            //
            case 134: {
				setResult(createEcoreClass(org.eclipse.emf.ecore.EcorePackage.Literals.EPACKAGE__ECLASSIFIERS));
	                  break;
            }
	
            //
            // Rule 135:  EcoreClass_ecore_EPackage_eClassifiers ::= LT_e_C_l_a_s_s_i_f_i_e_r_s EcoreClass_ecore_EPackage_eClassifiers_8 SLASH_GT
            //
            case 135: {
				setResult(createEcoreClass(org.eclipse.emf.ecore.EcorePackage.Literals.EPACKAGE__ECLASSIFIERS, getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 136:  EcoreClass_ecore_EPackage_eClassifiers ::= LT_e_C_l_a_s_s_i_f_i_e_r_s GT LT_SLASH_e_C_l_a_s_s_i_f_i_e_r_s_GT
            //
            case 136: {
				setResult(createEcoreClass(org.eclipse.emf.ecore.EcorePackage.Literals.EPACKAGE__ECLASSIFIERS));
	                  break;
            }
	
            //
            // Rule 137:  EcoreClass_ecore_EPackage_eClassifiers ::= LT_e_C_l_a_s_s_i_f_i_e_r_s EcoreClass_ecore_EPackage_eClassifiers_8 GT LT_SLASH_e_C_l_a_s_s_i_f_i_e_r_s_GT
            //
            case 137: {
				setResult(createEcoreClass(org.eclipse.emf.ecore.EcorePackage.Literals.EPACKAGE__ECLASSIFIERS, getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 138:  EcoreClass_ecore_EPackage_eClassifiers ::= LT_e_C_l_a_s_s_i_f_i_e_r_s GT EcoreClass_ecore_EPackage_eClassifiers_3 LT_SLASH_e_C_l_a_s_s_i_f_i_e_r_s_GT
            //
            case 138: {
				setResult(createEcoreClass(org.eclipse.emf.ecore.EcorePackage.Literals.EPACKAGE__ECLASSIFIERS, getRhsSym(3)));
	                  break;
            }
	
            //
            // Rule 139:  EcoreClass_ecore_EPackage_eClassifiers ::= LT_e_C_l_a_s_s_i_f_i_e_r_s EcoreClass_ecore_EPackage_eClassifiers_8 GT EcoreClass_ecore_EPackage_eClassifiers_3 LT_SLASH_e_C_l_a_s_s_i_f_i_e_r_s_GT
            //
            case 139: {
				setResult(createEcoreClass(org.eclipse.emf.ecore.EcorePackage.Literals.EPACKAGE__ECLASSIFIERS, getRhsSym(2), getRhsSym(4)));
	                  break;
            }
	
            //
            // Rule 140:  EcoreClass_ecore_EPackage_eClassifiers_3 ::= EcoreClass_ecore_EClass_eOperations
            //
            case 140: {
				setResult(SetAttributes.create(getRhsSym(1)));
	                  break;
            }
	
            //
            // Rule 141:  EcoreClass_ecore_EPackage_eClassifiers_3 ::= EcoreClass_ecore_EClass_eStructuralFeatures
            //
            case 141: {
				setResult(SetAttributes.create(getRhsSym(1)));
	                  break;
            }
	
            //
            // Rule 142:  EcoreClass_ecore_EPackage_eClassifiers_3 ::= EcoreClass_ecore_EModelElement_eAnnotations
            //
            case 142: {
				setResult(SetAttributes.create(getRhsSym(1)));
	                  break;
            }
	
            //
            // Rule 143:  EcoreClass_ecore_EPackage_eClassifiers_3 ::= OtherElement
            //
            case 143: {
				setResult(SetAttributes.create(getRhsSym(1)));
	                  break;
            }
	
            //
            // Rule 144:  EcoreClass_ecore_EPackage_eClassifiers_3 ::= EcoreClass_ecore_EPackage_eClassifiers_3 EcoreClass_ecore_EClass_eOperations
            //
            case 144: {
				setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 145:  EcoreClass_ecore_EPackage_eClassifiers_3 ::= EcoreClass_ecore_EPackage_eClassifiers_3 EcoreClass_ecore_EClass_eStructuralFeatures
            //
            case 145: {
				setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 146:  EcoreClass_ecore_EPackage_eClassifiers_3 ::= EcoreClass_ecore_EPackage_eClassifiers_3 EcoreClass_ecore_EModelElement_eAnnotations
            //
            case 146: {
				setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 147:  EcoreClass_ecore_EPackage_eClassifiers_3 ::= EcoreClass_ecore_EPackage_eClassifiers_3 OtherElement
            //
            case 147: {
				setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 148:  EcoreClass_ecore_EPackage_eClassifiers_8 ::= EcoreFeature_ecore_EClass_abstract
            //
            case 148: {
				setResult(SetAttributes.create(getRhsSym(1)));
	                  break;
            }
	
            //
            // Rule 149:  EcoreClass_ecore_EPackage_eClassifiers_8 ::= EcoreFeature_ecore_EClass_eSuperTypes
            //
            case 149: {
				setResult(SetAttributes.create(getRhsSym(1)));
	                  break;
            }
	
            //
            // Rule 150:  EcoreClass_ecore_EPackage_eClassifiers_8 ::= EcoreFeature_ecore_EDataType_serializable
            //
            case 150: {
				setResult(SetAttributes.create(getRhsSym(1)));
	                  break;
            }
	
            //
            // Rule 151:  EcoreClass_ecore_EPackage_eClassifiers_8 ::= EcoreFeature_ecore_ENamedElement_name
            //
            case 151: {
				setResult(SetAttributes.create(getRhsSym(1)));
	                  break;
            }
	
            //
            // Rule 152:  EcoreClass_ecore_EPackage_eClassifiers_8 ::= InstanceClassNameAttribute
            //
            case 152: {
				setResult(SetAttributes.create(getRhsSym(1)));
	                  break;
            }
	
            //
            // Rule 153:  EcoreClass_ecore_EPackage_eClassifiers_8 ::= OtherAttribute
            //
            case 153: {
				setResult(SetAttributes.create(getRhsSym(1)));
	                  break;
            }
	
            //
            // Rule 154:  EcoreClass_ecore_EPackage_eClassifiers_8 ::= XMLAttribute_xsi_type
            //
            case 154: {
				setResult(SetAttributes.create(getRhsSym(1)));
	                  break;
            }
	
            //
            // Rule 155:  EcoreClass_ecore_EPackage_eClassifiers_8 ::= EcoreClass_ecore_EPackage_eClassifiers_8 EcoreFeature_ecore_EClass_abstract
            //
            case 155: {
				setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 156:  EcoreClass_ecore_EPackage_eClassifiers_8 ::= EcoreClass_ecore_EPackage_eClassifiers_8 EcoreFeature_ecore_EClass_eSuperTypes
            //
            case 156: {
				setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 157:  EcoreClass_ecore_EPackage_eClassifiers_8 ::= EcoreClass_ecore_EPackage_eClassifiers_8 EcoreFeature_ecore_EDataType_serializable
            //
            case 157: {
				setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 158:  EcoreClass_ecore_EPackage_eClassifiers_8 ::= EcoreClass_ecore_EPackage_eClassifiers_8 EcoreFeature_ecore_ENamedElement_name
            //
            case 158: {
				setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 159:  EcoreClass_ecore_EPackage_eClassifiers_8 ::= EcoreClass_ecore_EPackage_eClassifiers_8 InstanceClassNameAttribute
            //
            case 159: {
				setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 160:  EcoreClass_ecore_EPackage_eClassifiers_8 ::= EcoreClass_ecore_EPackage_eClassifiers_8 OtherAttribute
            //
            case 160: {
				setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 161:  EcoreClass_ecore_EPackage_eClassifiers_8 ::= EcoreClass_ecore_EPackage_eClassifiers_8 XMLAttribute_xsi_type
            //
            case 161: {
				setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 162:  EcoreClass_ecore_EPackage_eSubpackages ::= LT_e_S_u_b_p_a_c_k_a_g_e_s SLASH_GT
            //
            case 162: {
				setResult(createEcoreClass(org.eclipse.emf.ecore.EcorePackage.Literals.EPACKAGE__ESUBPACKAGES));
	                  break;
            }
	
            //
            // Rule 163:  EcoreClass_ecore_EPackage_eSubpackages ::= LT_e_S_u_b_p_a_c_k_a_g_e_s EcoreClass_ecore_EPackage_eSubpackages_8 SLASH_GT
            //
            case 163: {
				setResult(createEcoreClass(org.eclipse.emf.ecore.EcorePackage.Literals.EPACKAGE__ESUBPACKAGES, getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 164:  EcoreClass_ecore_EPackage_eSubpackages ::= LT_e_S_u_b_p_a_c_k_a_g_e_s GT LT_SLASH_e_P_a_c_k_a_g_e_s_GT
            //
            case 164: {
				setResult(createEcoreClass(org.eclipse.emf.ecore.EcorePackage.Literals.EPACKAGE__ESUBPACKAGES));
	                  break;
            }
	
            //
            // Rule 165:  EcoreClass_ecore_EPackage_eSubpackages ::= LT_e_S_u_b_p_a_c_k_a_g_e_s EcoreClass_ecore_EPackage_eSubpackages_8 GT LT_SLASH_e_P_a_c_k_a_g_e_s_GT
            //
            case 165: {
				setResult(createEcoreClass(org.eclipse.emf.ecore.EcorePackage.Literals.EPACKAGE__ESUBPACKAGES, getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 166:  EcoreClass_ecore_EPackage_eSubpackages ::= LT_e_S_u_b_p_a_c_k_a_g_e_s GT EcoreClass_ecore_EPackage_eSubpackages_3 LT_SLASH_e_P_a_c_k_a_g_e_s_GT
            //
            case 166: {
				setResult(createEcoreClass(org.eclipse.emf.ecore.EcorePackage.Literals.EPACKAGE__ESUBPACKAGES, getRhsSym(3)));
	                  break;
            }
	
            //
            // Rule 167:  EcoreClass_ecore_EPackage_eSubpackages ::= LT_e_S_u_b_p_a_c_k_a_g_e_s EcoreClass_ecore_EPackage_eSubpackages_8 GT EcoreClass_ecore_EPackage_eSubpackages_3 LT_SLASH_e_P_a_c_k_a_g_e_s_GT
            //
            case 167: {
				setResult(createEcoreClass(org.eclipse.emf.ecore.EcorePackage.Literals.EPACKAGE__ESUBPACKAGES, getRhsSym(2), getRhsSym(4)));
	                  break;
            }
	
            //
            // Rule 168:  EcoreClass_ecore_EPackage_eSubpackages_3 ::= EcoreClass_ecore_EModelElement_eAnnotations
            //
            case 168: {
				setResult(SetAttributes.create(getRhsSym(1)));
	                  break;
            }
	
            //
            // Rule 169:  EcoreClass_ecore_EPackage_eSubpackages_3 ::= EcoreClass_ecore_EPackage_eClassifiers
            //
            case 169: {
				setResult(SetAttributes.create(getRhsSym(1)));
	                  break;
            }
	
            //
            // Rule 170:  EcoreClass_ecore_EPackage_eSubpackages_3 ::= EcoreClass_ecore_ETypedElement_eGenericType
            //
            case 170: {
				setResult(SetAttributes.create(getRhsSym(1)));
	                  break;
            }
	
            //
            // Rule 171:  EcoreClass_ecore_EPackage_eSubpackages_3 ::= OtherElement
            //
            case 171: {
				setResult(SetAttributes.create(getRhsSym(1)));
	                  break;
            }
	
            //
            // Rule 172:  EcoreClass_ecore_EPackage_eSubpackages_3 ::= EcoreClass_ecore_EPackage_eSubpackages_3 EcoreClass_ecore_EModelElement_eAnnotations
            //
            case 172: {
				setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 173:  EcoreClass_ecore_EPackage_eSubpackages_3 ::= EcoreClass_ecore_EPackage_eSubpackages_3 EcoreClass_ecore_EPackage_eClassifiers
            //
            case 173: {
				setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 174:  EcoreClass_ecore_EPackage_eSubpackages_3 ::= EcoreClass_ecore_EPackage_eSubpackages_3 EcoreClass_ecore_ETypedElement_eGenericType
            //
            case 174: {
				setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 175:  EcoreClass_ecore_EPackage_eSubpackages_3 ::= EcoreClass_ecore_EPackage_eSubpackages_3 OtherElement
            //
            case 175: {
				setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 176:  EcoreClass_ecore_EPackage_eSubpackages_8 ::= EcoreFeature_ecore_ENamedElement_name
            //
            case 176: {
				setResult(SetAttributes.create(getRhsSym(1)));
	                  break;
            }
	
            //
            // Rule 177:  EcoreClass_ecore_EPackage_eSubpackages_8 ::= EcoreFeature_ecore_EPackage_nsPrefix
            //
            case 177: {
				setResult(SetAttributes.create(getRhsSym(1)));
	                  break;
            }
	
            //
            // Rule 178:  EcoreClass_ecore_EPackage_eSubpackages_8 ::= EcoreFeature_ecore_EPackage_nsURI
            //
            case 178: {
				setResult(SetAttributes.create(getRhsSym(1)));
	                  break;
            }
	
            //
            // Rule 179:  EcoreClass_ecore_EPackage_eSubpackages_8 ::= OtherAttribute
            //
            case 179: {
				setResult(SetAttributes.create(getRhsSym(1)));
	                  break;
            }
	
            //
            // Rule 180:  EcoreClass_ecore_EPackage_eSubpackages_8 ::= XMLAttribute_xsi_type
            //
            case 180: {
				setResult(SetAttributes.create(getRhsSym(1)));
	                  break;
            }
	
            //
            // Rule 181:  EcoreClass_ecore_EPackage_eSubpackages_8 ::= EcoreClass_ecore_EPackage_eSubpackages_8 EcoreFeature_ecore_ENamedElement_name
            //
            case 181: {
				setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 182:  EcoreClass_ecore_EPackage_eSubpackages_8 ::= EcoreClass_ecore_EPackage_eSubpackages_8 EcoreFeature_ecore_EPackage_nsPrefix
            //
            case 182: {
				setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 183:  EcoreClass_ecore_EPackage_eSubpackages_8 ::= EcoreClass_ecore_EPackage_eSubpackages_8 EcoreFeature_ecore_EPackage_nsURI
            //
            case 183: {
				setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 184:  EcoreClass_ecore_EPackage_eSubpackages_8 ::= EcoreClass_ecore_EPackage_eSubpackages_8 OtherAttribute
            //
            case 184: {
				setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 185:  EcoreClass_ecore_EPackage_eSubpackages_8 ::= EcoreClass_ecore_EPackage_eSubpackages_8 XMLAttribute_xsi_type
            //
            case 185: {
				setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 186:  EcoreClass_ecore_ETypedElement_eGenericType ::= LT_e_G_e_n_e_r_i_c_T_y_p_e SLASH_GT
            //
            case 186: {
				setResult(createEcoreClass(org.eclipse.emf.ecore.EcorePackage.Literals.ETYPED_ELEMENT__EGENERIC_TYPE));
	                  break;
            }
	
            //
            // Rule 187:  EcoreClass_ecore_ETypedElement_eGenericType ::= LT_e_G_e_n_e_r_i_c_T_y_p_e EcoreClass_ecore_ETypedElement_eGenericType_8 SLASH_GT
            //
            case 187: {
				setResult(createEcoreClass(org.eclipse.emf.ecore.EcorePackage.Literals.ETYPED_ELEMENT__EGENERIC_TYPE, getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 188:  EcoreClass_ecore_ETypedElement_eGenericType ::= LT_e_G_e_n_e_r_i_c_T_y_p_e GT LT_SLASH_e_G_e_n_e_r_i_c_T_y_p_e_GT
            //
            case 188: {
				setResult(createEcoreClass(org.eclipse.emf.ecore.EcorePackage.Literals.ETYPED_ELEMENT__EGENERIC_TYPE));
	                  break;
            }
	
            //
            // Rule 189:  EcoreClass_ecore_ETypedElement_eGenericType ::= LT_e_G_e_n_e_r_i_c_T_y_p_e EcoreClass_ecore_ETypedElement_eGenericType_8 GT LT_SLASH_e_G_e_n_e_r_i_c_T_y_p_e_GT
            //
            case 189: {
				setResult(createEcoreClass(org.eclipse.emf.ecore.EcorePackage.Literals.ETYPED_ELEMENT__EGENERIC_TYPE, getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 190:  EcoreClass_ecore_ETypedElement_eGenericType ::= LT_e_G_e_n_e_r_i_c_T_y_p_e GT EcoreClass_ecore_ETypedElement_eGenericType_3 LT_SLASH_e_G_e_n_e_r_i_c_T_y_p_e_GT
            //
            case 190: {
				setResult(createEcoreClass(org.eclipse.emf.ecore.EcorePackage.Literals.ETYPED_ELEMENT__EGENERIC_TYPE, getRhsSym(3)));
	                  break;
            }
	
            //
            // Rule 191:  EcoreClass_ecore_ETypedElement_eGenericType ::= LT_e_G_e_n_e_r_i_c_T_y_p_e EcoreClass_ecore_ETypedElement_eGenericType_8 GT EcoreClass_ecore_ETypedElement_eGenericType_3 LT_SLASH_e_G_e_n_e_r_i_c_T_y_p_e_GT
            //
            case 191: {
				setResult(createEcoreClass(org.eclipse.emf.ecore.EcorePackage.Literals.ETYPED_ELEMENT__EGENERIC_TYPE, getRhsSym(2), getRhsSym(4)));
	                  break;
            }
	
            //
            // Rule 192:  EcoreClass_ecore_ETypedElement_eGenericType_3 ::= EcoreClass_ecore_EGenericType_eTypeArguments
            //
            case 192: {
				setResult(SetAttributes.create(getRhsSym(1)));
	                  break;
            }
	
            //
            // Rule 193:  EcoreClass_ecore_ETypedElement_eGenericType_3 ::= EcoreClass_ecore_EModelElement_eAnnotations
            //
            case 193: {
				setResult(SetAttributes.create(getRhsSym(1)));
	                  break;
            }
	
            //
            // Rule 194:  EcoreClass_ecore_ETypedElement_eGenericType_3 ::= OtherElement
            //
            case 194: {
				setResult(SetAttributes.create(getRhsSym(1)));
	                  break;
            }
	
            //
            // Rule 195:  EcoreClass_ecore_ETypedElement_eGenericType_3 ::= EcoreClass_ecore_ETypedElement_eGenericType_3 EcoreClass_ecore_EGenericType_eTypeArguments
            //
            case 195: {
				setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 196:  EcoreClass_ecore_ETypedElement_eGenericType_3 ::= EcoreClass_ecore_ETypedElement_eGenericType_3 EcoreClass_ecore_EModelElement_eAnnotations
            //
            case 196: {
				setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 197:  EcoreClass_ecore_ETypedElement_eGenericType_3 ::= EcoreClass_ecore_ETypedElement_eGenericType_3 OtherElement
            //
            case 197: {
				setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 198:  EcoreClass_ecore_ETypedElement_eGenericType_8 ::= EcoreFeature_ecore_EPackage_eClassifiers
            //
            case 198: {
				setResult(SetAttributes.create(getRhsSym(1)));
	                  break;
            }
	
            //
            // Rule 199:  EcoreClass_ecore_ETypedElement_eGenericType_8 ::= OtherAttribute
            //
            case 199: {
				setResult(SetAttributes.create(getRhsSym(1)));
	                  break;
            }
	
            //
            // Rule 200:  EcoreClass_ecore_ETypedElement_eGenericType_8 ::= XMLAttribute_xsi_type
            //
            case 200: {
				setResult(SetAttributes.create(getRhsSym(1)));
	                  break;
            }
	
            //
            // Rule 201:  EcoreClass_ecore_ETypedElement_eGenericType_8 ::= EcoreClass_ecore_ETypedElement_eGenericType_8 EcoreFeature_ecore_EPackage_eClassifiers
            //
            case 201: {
				setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 202:  EcoreClass_ecore_ETypedElement_eGenericType_8 ::= EcoreClass_ecore_ETypedElement_eGenericType_8 OtherAttribute
            //
            case 202: {
				setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 203:  EcoreClass_ecore_ETypedElement_eGenericType_8 ::= EcoreClass_ecore_ETypedElement_eGenericType_8 XMLAttribute_xsi_type
            //
            case 203: {
				setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 204:  EcoreFeature_ecore_EAnnotation_details ::= LT_d_e_t_a_i_l_s SLASH_GT
            //
            case 204: {
				setResult(createEcoreFeature(org.eclipse.emf.ecore.EcorePackage.Literals.EANNOTATION__DETAILS));
	                  break;
            }
	
            //
            // Rule 205:  EcoreFeature_ecore_EAnnotation_details ::= LT_d_e_t_a_i_l_s EcoreFeature_ecore_EAnnotation_details_8 SLASH_GT
            //
            case 205: {
				setResult(createEcoreFeature(org.eclipse.emf.ecore.EcorePackage.Literals.EANNOTATION__DETAILS));
	                  break;
            }
	
            //
            // Rule 206:  EcoreFeature_ecore_EAnnotation_details ::= LT_d_e_t_a_i_l_s GT LT_SLASH_d_e_t_a_i_l_s_GT
            //
            case 206: {
				setResult(createEcoreFeature(org.eclipse.emf.ecore.EcorePackage.Literals.EANNOTATION__DETAILS));
	                  break;
            }
	
            //
            // Rule 207:  EcoreFeature_ecore_EAnnotation_details ::= LT_d_e_t_a_i_l_s EcoreFeature_ecore_EAnnotation_details_8 GT LT_SLASH_d_e_t_a_i_l_s_GT
            //
            case 207: {
				setResult(createEcoreFeature(org.eclipse.emf.ecore.EcorePackage.Literals.EANNOTATION__DETAILS));
	                  break;
            }
	
            //
            // Rule 208:  EcoreFeature_ecore_EAnnotation_details ::= LT_d_e_t_a_i_l_s GT EcoreFeature_ecore_EAnnotation_details_3 LT_SLASH_d_e_t_a_i_l_s_GT
            //
            case 208: {
				setResult(createEcoreFeature(org.eclipse.emf.ecore.EcorePackage.Literals.EANNOTATION__DETAILS));
	                  break;
            }
	
            //
            // Rule 209:  EcoreFeature_ecore_EAnnotation_details ::= LT_d_e_t_a_i_l_s EcoreFeature_ecore_EAnnotation_details_8 GT EcoreFeature_ecore_EAnnotation_details_3 LT_SLASH_d_e_t_a_i_l_s_GT
            //
            case 209: {
				setResult(createEcoreFeature(org.eclipse.emf.ecore.EcorePackage.Literals.EANNOTATION__DETAILS));
	                  break;
            }
	
            //
            // Rule 210:  EcoreFeature_ecore_EAnnotation_details_3 ::= EcoreClass_ecore_EModelElement_eAnnotations
            //
            case 210: {
				setResult(SetMapAttributes.create(getRhsSym(1)));
	                  break;
            }
	
            //
            // Rule 211:  EcoreFeature_ecore_EAnnotation_details_3 ::= OtherElement
            //
            case 211: {
				setResult(SetMapAttributes.create(getRhsSym(1)));
	                  break;
            }
	
            //
            // Rule 212:  EcoreFeature_ecore_EAnnotation_details_3 ::= EcoreFeature_ecore_EAnnotation_details_3 EcoreClass_ecore_EModelElement_eAnnotations
            //
            case 212: {
				setResult(SetMapAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 213:  EcoreFeature_ecore_EAnnotation_details_3 ::= EcoreFeature_ecore_EAnnotation_details_3 OtherElement
            //
            case 213: {
				setResult(SetMapAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 214:  EcoreFeature_ecore_EAnnotation_details_8 ::= EcoreFeature_ecore_EStringToStringMapEntry_key
            //
            case 214: {
				setResult(SetMapAttributes.create(getRhsSym(1)));
	                  break;
            }
	
            //
            // Rule 215:  EcoreFeature_ecore_EAnnotation_details_8 ::= EcoreFeature_ecore_EStringToStringMapEntry_value
            //
            case 215: {
				setResult(SetMapAttributes.create(getRhsSym(1)));
	                  break;
            }
	
            //
            // Rule 216:  EcoreFeature_ecore_EAnnotation_details_8 ::= OtherAttribute
            //
            case 216: {
				setResult(SetMapAttributes.create(getRhsSym(1)));
	                  break;
            }
	
            //
            // Rule 217:  EcoreFeature_ecore_EAnnotation_details_8 ::= XMLAttribute_xsi_type
            //
            case 217: {
				setResult(SetMapAttributes.create(getRhsSym(1)));
	                  break;
            }
	
            //
            // Rule 218:  EcoreFeature_ecore_EAnnotation_details_8 ::= EcoreFeature_ecore_EAnnotation_details_8 EcoreFeature_ecore_EStringToStringMapEntry_key
            //
            case 218: {
				setResult(SetMapAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 219:  EcoreFeature_ecore_EAnnotation_details_8 ::= EcoreFeature_ecore_EAnnotation_details_8 EcoreFeature_ecore_EStringToStringMapEntry_value
            //
            case 219: {
				setResult(SetMapAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 220:  EcoreFeature_ecore_EAnnotation_details_8 ::= EcoreFeature_ecore_EAnnotation_details_8 OtherAttribute
            //
            case 220: {
				setResult(SetMapAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 221:  EcoreFeature_ecore_EAnnotation_details_8 ::= EcoreFeature_ecore_EAnnotation_details_8 XMLAttribute_xsi_type
            //
            case 221: {
				setResult(SetMapAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 222:  EcoreFeature_ecore_EAnnotation_source ::= source EQ Terminal_String
            //
            case 222: {
				setResult(createEcoreFeature(org.eclipse.emf.ecore.EcorePackage.Literals.EANNOTATION__SOURCE));
	                  break;
            }
	
            //
            // Rule 223:  EcoreFeature_ecore_EClass_abstract ::= abstract EQ Terminal_String
            //
            case 223: {
				setResult(createEcoreFeature(org.eclipse.emf.ecore.EcorePackage.Literals.ECLASS__ABSTRACT));
	                  break;
            }
	
            //
            // Rule 224:  EcoreFeature_ecore_EClass_eSuperTypes ::= eSuperTypes EQ Terminal_String
            //
            case 224: {
				setResult(createEcoreFeature(org.eclipse.emf.ecore.EcorePackage.Literals.ECLASS__ESUPER_TYPES));
	                  break;
            }
	
            //
            // Rule 225:  EcoreFeature_ecore_EDataType_serializable ::= serializable EQ Terminal_String
            //
            case 225: {
				setResult(createEcoreFeature(org.eclipse.emf.ecore.EcorePackage.Literals.EDATA_TYPE__SERIALIZABLE));
	                  break;
            }
	
            //
            // Rule 226:  EcoreFeature_ecore_ENamedElement_name ::= name EQ Terminal_String
            //
            case 226: {
				setResult(createEcoreFeature(org.eclipse.emf.ecore.EcorePackage.Literals.ENAMED_ELEMENT__NAME));
	                  break;
            }
	
            //
            // Rule 227:  EcoreFeature_ecore_EOperation_eExceptions ::= eExceptions EQ Terminal_String
            //
            case 227: {
				setResult(createEcoreFeature(org.eclipse.emf.ecore.EcorePackage.Literals.EOPERATION__EEXCEPTIONS));
	                  break;
            }
	
            //
            // Rule 228:  EcoreFeature_ecore_EPackage_eClassifiers ::= eClassifiers EQ Terminal_String
            //
            case 228: {
				setResult(createEcoreFeature(org.eclipse.emf.ecore.EcorePackage.Literals.EPACKAGE__ECLASSIFIERS));
	                  break;
            }
	
            //
            // Rule 229:  EcoreFeature_ecore_EPackage_nsPrefix ::= nsPrefix EQ Terminal_String
            //
            case 229: {
				setResult(createEcoreFeature(org.eclipse.emf.ecore.EcorePackage.Literals.EPACKAGE__NS_PREFIX));
	                  break;
            }
	
            //
            // Rule 230:  EcoreFeature_ecore_EPackage_nsURI ::= nsURI EQ Terminal_String
            //
            case 230: {
				setResult(createEcoreFeature(org.eclipse.emf.ecore.EcorePackage.Literals.EPACKAGE__NS_URI));
	                  break;
            }
	
            //
            // Rule 231:  EcoreFeature_ecore_EReference_containment ::= containment EQ Terminal_String
            //
            case 231: {
				setResult(createEcoreFeature(org.eclipse.emf.ecore.EcorePackage.Literals.EREFERENCE__CONTAINMENT));
	                  break;
            }
	
            //
            // Rule 232:  EcoreFeature_ecore_EReference_eOpposite ::= eOpposite EQ Terminal_String
            //
            case 232: {
				setResult(createEcoreFeature(org.eclipse.emf.ecore.EcorePackage.Literals.EREFERENCE__EOPPOSITE));
	                  break;
            }
	
            //
            // Rule 233:  EcoreFeature_ecore_EReference_resolveProxies ::= resolveProxies EQ Terminal_String
            //
            case 233: {
				setResult(createEcoreFeature(org.eclipse.emf.ecore.EcorePackage.Literals.EREFERENCE__RESOLVE_PROXIES));
	                  break;
            }
	
            //
            // Rule 234:  EcoreFeature_ecore_EStringToStringMapEntry_key ::= key EQ Terminal_String
            //
            case 234: {
				setResult(createEcoreFeature(org.eclipse.emf.ecore.EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY__KEY));
	                  break;
            }
	
            //
            // Rule 235:  EcoreFeature_ecore_EStringToStringMapEntry_value ::= value EQ Terminal_String
            //
            case 235: {
				setResult(createEcoreFeature(org.eclipse.emf.ecore.EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY__VALUE));
	                  break;
            }
	
            //
            // Rule 236:  EcoreFeature_ecore_EStructuralFeature_changeable ::= changeable EQ Terminal_String
            //
            case 236: {
				setResult(createEcoreFeature(org.eclipse.emf.ecore.EcorePackage.Literals.ESTRUCTURAL_FEATURE__CHANGEABLE));
	                  break;
            }
	
            //
            // Rule 237:  EcoreFeature_ecore_EStructuralFeature_defaultValueLiteral ::= defaultValueLiteral EQ Terminal_String
            //
            case 237: {
				setResult(createEcoreFeature(org.eclipse.emf.ecore.EcorePackage.Literals.ESTRUCTURAL_FEATURE__DEFAULT_VALUE_LITERAL));
	                  break;
            }
	
            //
            // Rule 238:  EcoreFeature_ecore_EStructuralFeature_derived ::= derived EQ Terminal_String
            //
            case 238: {
				setResult(createEcoreFeature(org.eclipse.emf.ecore.EcorePackage.Literals.ESTRUCTURAL_FEATURE__DERIVED));
	                  break;
            }
	
            //
            // Rule 239:  EcoreFeature_ecore_EStructuralFeature_transient ::= transient EQ Terminal_String
            //
            case 239: {
				setResult(createEcoreFeature(org.eclipse.emf.ecore.EcorePackage.Literals.ESTRUCTURAL_FEATURE__TRANSIENT));
	                  break;
            }
	
            //
            // Rule 240:  EcoreFeature_ecore_EStructuralFeature_unsettable ::= unsettable EQ Terminal_String
            //
            case 240: {
				setResult(createEcoreFeature(org.eclipse.emf.ecore.EcorePackage.Literals.ESTRUCTURAL_FEATURE__UNSETTABLE));
	                  break;
            }
	
            //
            // Rule 241:  EcoreFeature_ecore_EStructuralFeature_volatile ::= volatile EQ Terminal_String
            //
            case 241: {
				setResult(createEcoreFeature(org.eclipse.emf.ecore.EcorePackage.Literals.ESTRUCTURAL_FEATURE__VOLATILE));
	                  break;
            }
	
            //
            // Rule 242:  EcoreFeature_ecore_ETypedElement_eType ::= eType EQ Terminal_String
            //
            case 242: {
				setResult(createEcoreFeature(org.eclipse.emf.ecore.EcorePackage.Literals.ETYPED_ELEMENT__ETYPE));
	                  break;
            }
	
            //
            // Rule 243:  EcoreFeature_ecore_ETypedElement_lowerBound ::= lowerBound EQ Terminal_String
            //
            case 243: {
				setResult(createEcoreFeature(org.eclipse.emf.ecore.EcorePackage.Literals.ETYPED_ELEMENT__LOWER_BOUND));
	                  break;
            }
	
            //
            // Rule 244:  EcoreFeature_ecore_ETypedElement_ordered ::= ordered EQ Terminal_String
            //
            case 244: {
				setResult(createEcoreFeature(org.eclipse.emf.ecore.EcorePackage.Literals.ETYPED_ELEMENT__ORDERED));
	                  break;
            }
	
            //
            // Rule 245:  EcoreFeature_ecore_ETypedElement_upperBound ::= upperBound EQ Terminal_String
            //
            case 245: {
				setResult(createEcoreFeature(org.eclipse.emf.ecore.EcorePackage.Literals.ETYPED_ELEMENT__UPPER_BOUND));
	                  break;
            }
	
            //
            // Rule 246:  EcoreRoot_ecore_EPackage ::= LT_e_c_o_r_e_COLON_E_P_a_c_k_a_g_e SLASH_GT
            //
            case 246: {
				setResult(createEcoreRoot(org.eclipse.emf.ecore.EcorePackage.Literals.EPACKAGE));
	                  break;
            }
	
            //
            // Rule 247:  EcoreRoot_ecore_EPackage ::= LT_e_c_o_r_e_COLON_E_P_a_c_k_a_g_e EcoreRoot_ecore_EPackage_8 SLASH_GT
            //
            case 247: {
				setResult(createEcoreRoot(org.eclipse.emf.ecore.EcorePackage.Literals.EPACKAGE, getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 248:  EcoreRoot_ecore_EPackage ::= LT_e_c_o_r_e_COLON_E_P_a_c_k_a_g_e GT LT_SLASH_e_c_o_r_e_COLON_E_P_a_c_k_a_g_e_GT
            //
            case 248: {
				setResult(createEcoreRoot(org.eclipse.emf.ecore.EcorePackage.Literals.EPACKAGE));
	                  break;
            }
	
            //
            // Rule 249:  EcoreRoot_ecore_EPackage ::= LT_e_c_o_r_e_COLON_E_P_a_c_k_a_g_e EcoreRoot_ecore_EPackage_8 GT LT_SLASH_e_c_o_r_e_COLON_E_P_a_c_k_a_g_e_GT
            //
            case 249: {
				setResult(createEcoreRoot(org.eclipse.emf.ecore.EcorePackage.Literals.EPACKAGE, getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 250:  EcoreRoot_ecore_EPackage ::= LT_e_c_o_r_e_COLON_E_P_a_c_k_a_g_e GT EcoreRoot_ecore_EPackage_3 LT_SLASH_e_c_o_r_e_COLON_E_P_a_c_k_a_g_e_GT
            //
            case 250: {
				setResult(createEcoreRoot(org.eclipse.emf.ecore.EcorePackage.Literals.EPACKAGE, getRhsSym(3)));
	                  break;
            }
	
            //
            // Rule 251:  EcoreRoot_ecore_EPackage ::= LT_e_c_o_r_e_COLON_E_P_a_c_k_a_g_e EcoreRoot_ecore_EPackage_8 GT EcoreRoot_ecore_EPackage_3 LT_SLASH_e_c_o_r_e_COLON_E_P_a_c_k_a_g_e_GT
            //
            case 251: {
				setResult(createEcoreRoot(org.eclipse.emf.ecore.EcorePackage.Literals.EPACKAGE, getRhsSym(2), getRhsSym(4)));
	                  break;
            }
	
            //
            // Rule 252:  EcoreRoot_ecore_EPackage_3 ::= EcoreClass_ecore_EModelElement_eAnnotations
            //
            case 252: {
				setResult(SetAttributes.create(getRhsSym(1)));
	                  break;
            }
	
            //
            // Rule 253:  EcoreRoot_ecore_EPackage_3 ::= EcoreClass_ecore_EPackage_eClassifiers
            //
            case 253: {
				setResult(SetAttributes.create(getRhsSym(1)));
	                  break;
            }
	
            //
            // Rule 254:  EcoreRoot_ecore_EPackage_3 ::= OtherElement
            //
            case 254: {
				setResult(SetAttributes.create(getRhsSym(1)));
	                  break;
            }
	
            //
            // Rule 255:  EcoreRoot_ecore_EPackage_3 ::= EcoreRoot_ecore_EPackage_3 EcoreClass_ecore_EModelElement_eAnnotations
            //
            case 255: {
				setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 256:  EcoreRoot_ecore_EPackage_3 ::= EcoreRoot_ecore_EPackage_3 EcoreClass_ecore_EPackage_eClassifiers
            //
            case 256: {
				setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 257:  EcoreRoot_ecore_EPackage_3 ::= EcoreRoot_ecore_EPackage_3 OtherElement
            //
            case 257: {
				setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 258:  EcoreRoot_ecore_EPackage_8 ::= EcoreFeature_ecore_ENamedElement_name
            //
            case 258: {
				setResult(SetAttributes.create(getRhsSym(1)));
	                  break;
            }
	
            //
            // Rule 259:  EcoreRoot_ecore_EPackage_8 ::= EcoreFeature_ecore_EPackage_nsPrefix
            //
            case 259: {
				setResult(SetAttributes.create(getRhsSym(1)));
	                  break;
            }
	
            //
            // Rule 260:  EcoreRoot_ecore_EPackage_8 ::= EcoreFeature_ecore_EPackage_nsURI
            //
            case 260: {
				setResult(SetAttributes.create(getRhsSym(1)));
	                  break;
            }
	
            //
            // Rule 261:  EcoreRoot_ecore_EPackage_8 ::= OtherAttribute
            //
            case 261: {
				setResult(SetAttributes.create(getRhsSym(1)));
	                  break;
            }
	
            //
            // Rule 262:  EcoreRoot_ecore_EPackage_8 ::= XMLAttribute_xmi_version
            //
            case 262: {
				setResult(SetAttributes.create(getRhsSym(1)));
	                  break;
            }
	
            //
            // Rule 263:  EcoreRoot_ecore_EPackage_8 ::= XMLAttribute_xmlns_
            //
            case 263: {
				setResult(SetAttributes.create(getRhsSym(1)));
	                  break;
            }
	
            //
            // Rule 264:  EcoreRoot_ecore_EPackage_8 ::= XMLAttribute_xsi_type
            //
            case 264: {
				setResult(SetAttributes.create(getRhsSym(1)));
	                  break;
            }
	
            //
            // Rule 265:  EcoreRoot_ecore_EPackage_8 ::= EcoreRoot_ecore_EPackage_8 EcoreFeature_ecore_ENamedElement_name
            //
            case 265: {
				setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 266:  EcoreRoot_ecore_EPackage_8 ::= EcoreRoot_ecore_EPackage_8 EcoreFeature_ecore_EPackage_nsPrefix
            //
            case 266: {
				setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 267:  EcoreRoot_ecore_EPackage_8 ::= EcoreRoot_ecore_EPackage_8 EcoreFeature_ecore_EPackage_nsURI
            //
            case 267: {
				setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 268:  EcoreRoot_ecore_EPackage_8 ::= EcoreRoot_ecore_EPackage_8 OtherAttribute
            //
            case 268: {
				setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 269:  EcoreRoot_ecore_EPackage_8 ::= EcoreRoot_ecore_EPackage_8 XMLAttribute_xmi_version
            //
            case 269: {
				setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 270:  EcoreRoot_ecore_EPackage_8 ::= EcoreRoot_ecore_EPackage_8 XMLAttribute_xmlns_
            //
            case 270: {
				setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 271:  EcoreRoot_ecore_EPackage_8 ::= EcoreRoot_ecore_EPackage_8 XMLAttribute_xsi_type
            //
            case 271: {
				setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 272:  InstanceClassNameAttribute ::= instanceClassName EQ Terminal_String
            //
            case 272: {
				setResult(createEObject());
	                  break;
            }
	
            //
            // Rule 273:  IntegerAttribute ::= EcoreFeature_ecore_ETypedElement_lowerBound
            //
            case 273: {
				setResult(createEObject());
	                  break;
            }
	
            //
            // Rule 274:  IntegerAttribute ::= EcoreFeature_ecore_ETypedElement_upperBound
            //
            case 274: {
				setResult(createEObject());
	                  break;
            }
	
            //
            // Rule 275:  OtherAttribute ::= IDENTIFIER EQ Terminal_String
            //
            case 275: {
				setResult(createEObject());
	                  break;
            }
	
            //
            // Rule 276:  OtherElement ::= LT Terminal_Identifier SLASH_GT
            //
            case 276: {
				setResult(createEObject());
	                  break;
            }
	
            //
            // Rule 277:  OtherElement ::= LT Terminal_Identifier OtherElement_6 SLASH_GT
            //
            case 277: {
				setResult(createEObject());
	                  break;
            }
	
            //
            // Rule 278:  OtherElement ::= LT Terminal_Identifier GT LT_SLASH Terminal_Identifier GT
            //
            case 278: {
				setResult(createEObject());
	                  break;
            }
	
            //
            // Rule 279:  OtherElement ::= LT Terminal_Identifier OtherElement_6 GT LT_SLASH Terminal_Identifier GT
            //
            case 279: {
				setResult(createEObject());
	                  break;
            }
	
            //
            // Rule 280:  OtherElement ::= LT Terminal_Identifier GT OtherElement_2 LT_SLASH Terminal_Identifier GT
            //
            case 280: {
				setResult(createEObject());
	                  break;
            }
	
            //
            // Rule 281:  OtherElement ::= LT Terminal_Identifier OtherElement_6 GT OtherElement_2 LT_SLASH Terminal_Identifier GT
            //
            case 281: {
				setResult(createEObject());
	                  break;
            }
	
            //
            // Rule 282:  OtherElement_2 ::= XmlElement
            //
            case 282: {
				setResult(SetAttributes.create(getRhsSym(1)));
	                  break;
            }
	
            //
            // Rule 283:  OtherElement_2 ::= OtherElement_2 XmlElement
            //
            case 283: {
				setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 284:  OtherElement_6 ::= XmlAttribute
            //
            case 284: {
				setResult(SetAttributes.create(getRhsSym(1)));
	                  break;
            }
	
            //
            // Rule 285:  OtherElement_6 ::= OtherElement_6 XmlAttribute
            //
            case 285: {
				setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 286:  StringAttribute ::= EcoreFeature_ecore_EAnnotation_source
            //
            case 286: {
				setResult(createEObject());
	                  break;
            }
	
            //
            // Rule 287:  StringAttribute ::= EcoreFeature_ecore_EClass_eSuperTypes
            //
            case 287: {
				setResult(createEObject());
	                  break;
            }
	
            //
            // Rule 288:  StringAttribute ::= EcoreFeature_ecore_ENamedElement_name
            //
            case 288: {
				setResult(createEObject());
	                  break;
            }
	
            //
            // Rule 289:  StringAttribute ::= EcoreFeature_ecore_EOperation_eExceptions
            //
            case 289: {
				setResult(createEObject());
	                  break;
            }
	
            //
            // Rule 290:  StringAttribute ::= EcoreFeature_ecore_EPackage_eClassifiers
            //
            case 290: {
				setResult(createEObject());
	                  break;
            }
	
            //
            // Rule 291:  StringAttribute ::= EcoreFeature_ecore_EPackage_nsPrefix
            //
            case 291: {
				setResult(createEObject());
	                  break;
            }
	
            //
            // Rule 292:  StringAttribute ::= EcoreFeature_ecore_EPackage_nsURI
            //
            case 292: {
				setResult(createEObject());
	                  break;
            }
	
            //
            // Rule 293:  StringAttribute ::= EcoreFeature_ecore_EReference_eOpposite
            //
            case 293: {
				setResult(createEObject());
	                  break;
            }
	
            //
            // Rule 294:  StringAttribute ::= EcoreFeature_ecore_EStringToStringMapEntry_key
            //
            case 294: {
				setResult(createEObject());
	                  break;
            }
	
            //
            // Rule 295:  StringAttribute ::= EcoreFeature_ecore_EStringToStringMapEntry_value
            //
            case 295: {
				setResult(createEObject());
	                  break;
            }
	
            //
            // Rule 296:  StringAttribute ::= EcoreFeature_ecore_ETypedElement_eType
            //
            case 296: {
				setResult(createEObject());
	                  break;
            }
	
            //
            // Rule 297:  StringAttribute ::= InstanceClassNameAttribute
            //
            case 297: {
				setResult(createEObject());
	                  break;
            }
	
            //
            // Rule 298:  StringAttribute ::= XMLAttribute_xmi_version
            //
            case 298: {
				setResult(createEObject());
	                  break;
            }
	
            //
            // Rule 299:  StringAttribute ::= XMLAttribute_xmlns_
            //
            case 299: {
				setResult(createEObject());
	                  break;
            }
	
            //
            // Rule 300:  StringAttribute ::= XMLAttribute_xsi_type
            //
            case 300: {
				setResult(createEObject());
	                  break;
            }
	
            //
            // Rule 301:  Terminal_Identifier ::= abstract
            //
            case 301: {
				setResult(getRhsTokenText(1));
	                  break;
            }
	
            //
            // Rule 302:  Terminal_Identifier ::= changeable
            //
            case 302: {
				setResult(getRhsTokenText(1));
	                  break;
            }
	
            //
            // Rule 303:  Terminal_Identifier ::= containment
            //
            case 303: {
				setResult(getRhsTokenText(1));
	                  break;
            }
	
            //
            // Rule 304:  Terminal_Identifier ::= defaultValueLiteral
            //
            case 304: {
				setResult(getRhsTokenText(1));
	                  break;
            }
	
            //
            // Rule 305:  Terminal_Identifier ::= derived
            //
            case 305: {
				setResult(getRhsTokenText(1));
	                  break;
            }
	
            //
            // Rule 306:  Terminal_Identifier ::= eClassifier
            //
            case 306: {
				setResult(getRhsTokenText(1));
	                  break;
            }
	
            //
            // Rule 307:  Terminal_Identifier ::= eExceptions
            //
            case 307: {
				setResult(getRhsTokenText(1));
	                  break;
            }
	
            //
            // Rule 308:  Terminal_Identifier ::= eOpposite
            //
            case 308: {
				setResult(getRhsTokenText(1));
	                  break;
            }
	
            //
            // Rule 309:  Terminal_Identifier ::= eSuperTypes
            //
            case 309: {
				setResult(getRhsTokenText(1));
	                  break;
            }
	
            //
            // Rule 310:  Terminal_Identifier ::= eType
            //
            case 310: {
				setResult(getRhsTokenText(1));
	                  break;
            }
	
            //
            // Rule 311:  Terminal_Identifier ::= encoding
            //
            case 311: {
				setResult(getRhsTokenText(1));
	                  break;
            }
	
            //
            // Rule 312:  Terminal_Identifier ::= instanceClassName
            //
            case 312: {
				setResult(getRhsTokenText(1));
	                  break;
            }
	
            //
            // Rule 313:  Terminal_Identifier ::= key
            //
            case 313: {
				setResult(getRhsTokenText(1));
	                  break;
            }
	
            //
            // Rule 314:  Terminal_Identifier ::= lowerBound
            //
            case 314: {
				setResult(getRhsTokenText(1));
	                  break;
            }
	
            //
            // Rule 315:  Terminal_Identifier ::= name
            //
            case 315: {
				setResult(getRhsTokenText(1));
	                  break;
            }
	
            //
            // Rule 316:  Terminal_Identifier ::= nsPrefix
            //
            case 316: {
				setResult(getRhsTokenText(1));
	                  break;
            }
	
            //
            // Rule 317:  Terminal_Identifier ::= nsURI
            //
            case 317: {
				setResult(getRhsTokenText(1));
	                  break;
            }
	
            //
            // Rule 318:  Terminal_Identifier ::= ordered
            //
            case 318: {
				setResult(getRhsTokenText(1));
	                  break;
            }
	
            //
            // Rule 319:  Terminal_Identifier ::= resolveProxies
            //
            case 319: {
				setResult(getRhsTokenText(1));
	                  break;
            }
	
            //
            // Rule 320:  Terminal_Identifier ::= serializable
            //
            case 320: {
				setResult(getRhsTokenText(1));
	                  break;
            }
	
            //
            // Rule 321:  Terminal_Identifier ::= source
            //
            case 321: {
				setResult(getRhsTokenText(1));
	                  break;
            }
	
            //
            // Rule 322:  Terminal_Identifier ::= transient
            //
            case 322: {
				setResult(getRhsTokenText(1));
	                  break;
            }
	
            //
            // Rule 323:  Terminal_Identifier ::= unsettable
            //
            case 323: {
				setResult(getRhsTokenText(1));
	                  break;
            }
	
            //
            // Rule 324:  Terminal_Identifier ::= upperBound
            //
            case 324: {
				setResult(getRhsTokenText(1));
	                  break;
            }
	
            //
            // Rule 325:  Terminal_Identifier ::= value
            //
            case 325: {
				setResult(getRhsTokenText(1));
	                  break;
            }
	
            //
            // Rule 326:  Terminal_Identifier ::= version
            //
            case 326: {
				setResult(getRhsTokenText(1));
	                  break;
            }
	
            //
            // Rule 327:  Terminal_Identifier ::= volatile
            //
            case 327: {
				setResult(getRhsTokenText(1));
	                  break;
            }
	
            //
            // Rule 328:  Terminal_Identifier ::= xmi
            //
            case 328: {
				setResult(getRhsTokenText(1));
	                  break;
            }
	
            //
            // Rule 329:  Terminal_Identifier ::= xsi
            //
            case 329: {
				setResult(getRhsTokenText(1));
	                  break;
            }
	
            //
            // Rule 330:  Terminal_Identifier ::= IDENTIFIER
            //
            case 330: {
				setResult(getRhsTokenText(1));
	                  break;
            }
	
            //
            // Rule 331:  Terminal_String ::= STRING
            //
            case 331: {
				setResult(getRhsTokenText(1));
	                  break;
            }
	
            //
            // Rule 332:  XMLAttribute_xmi_version ::= xmi COLON version EQ Terminal_String
            //
            case 332: {
				setResult(createXMIAttribute("version"));
	                  break;
            }
	
            //
            // Rule 333:  XMLAttribute_xmlns_ ::= xmlns COLON Terminal_Identifier EQ Terminal_String
            //
            case 333: {
				setResult(createXMLNSAttribute());
	                  break;
            }
	
            //
            // Rule 334:  XMLAttribute_xsi_type ::= xsi COLON type EQ Terminal_String
            //
            case 334: {
				setResult(createXSIAttribute("type"));
	                  break;
            }
	
            //
            // Rule 335:  XMLDocument_ecore_EPackage ::= LT_QUERY_x_m_l QUERY_GT EcoreRoot_ecore_EPackage
            //
            case 335: {
				setResult(createXMLDocument(getRhsSym(3)));
	                  break;
            }
	
            //
            // Rule 336:  XMLDocument_ecore_EPackage ::= LT_QUERY_x_m_l QUERY_GT LT_x_m_i_COLON_X_M_I SLASH_GT
            //
            case 336: {
				setResult(createXMLDocument());
	                  break;
            }
	
            //
            // Rule 337:  XMLDocument_ecore_EPackage ::= LT_QUERY_x_m_l QUERY_GT LT_x_m_i_COLON_X_M_I XMLDocument_ecore_EPackage_7 SLASH_GT
            //
            case 337: {
				setResult(createXMLDocument(getRhsSym(4)));
	                  break;
            }
	
            //
            // Rule 338:  XMLDocument_ecore_EPackage ::= LT_QUERY_x_m_l QUERY_GT LT_x_m_i_COLON_X_M_I GT LT_SLASH_x_m_i_COLON_X_M_I_GT
            //
            case 338: {
				setResult(createXMLDocument());
	                  break;
            }
	
            //
            // Rule 339:  XMLDocument_ecore_EPackage ::= LT_QUERY_x_m_l QUERY_GT LT_x_m_i_COLON_X_M_I XMLDocument_ecore_EPackage_7 GT LT_SLASH_x_m_i_COLON_X_M_I_GT
            //
            case 339: {
				setResult(createXMLDocument(getRhsSym(4)));
	                  break;
            }
	
            //
            // Rule 340:  XMLDocument_ecore_EPackage ::= LT_QUERY_x_m_l QUERY_GT LT_x_m_i_COLON_X_M_I GT XMLDocument_ecore_EPackage_2 LT_SLASH_x_m_i_COLON_X_M_I_GT
            //
            case 340: {
				setResult(createXMLDocument(getRhsSym(5)));
	                  break;
            }
	
            //
            // Rule 341:  XMLDocument_ecore_EPackage ::= LT_QUERY_x_m_l encoding EQ Terminal_String QUERY_GT EcoreRoot_ecore_EPackage
            //
            case 341: {
				setResult(createXMLDocument(getRhsSym(4), getRhsSym(6)));
	                  break;
            }
	
            //
            // Rule 342:  XMLDocument_ecore_EPackage ::= LT_QUERY_x_m_l version EQ Terminal_String QUERY_GT EcoreRoot_ecore_EPackage
            //
            case 342: {
				setResult(createXMLDocument(getRhsSym(4), getRhsSym(6)));
	                  break;
            }
	
            //
            // Rule 343:  XMLDocument_ecore_EPackage ::= LT_QUERY_x_m_l QUERY_GT LT_x_m_i_COLON_X_M_I XMLDocument_ecore_EPackage_7 GT XMLDocument_ecore_EPackage_2 LT_SLASH_x_m_i_COLON_X_M_I_GT
            //
            case 343: {
				setResult(createXMLDocument(getRhsSym(4), getRhsSym(6)));
	                  break;
            }
	
            //
            // Rule 344:  XMLDocument_ecore_EPackage ::= LT_QUERY_x_m_l encoding EQ Terminal_String QUERY_GT LT_x_m_i_COLON_X_M_I SLASH_GT
            //
            case 344: {
				setResult(createXMLDocument(getRhsSym(4)));
	                  break;
            }
	
            //
            // Rule 345:  XMLDocument_ecore_EPackage ::= LT_QUERY_x_m_l version EQ Terminal_String QUERY_GT LT_x_m_i_COLON_X_M_I SLASH_GT
            //
            case 345: {
				setResult(createXMLDocument(getRhsSym(4)));
	                  break;
            }
	
            //
            // Rule 346:  XMLDocument_ecore_EPackage ::= LT_QUERY_x_m_l encoding EQ Terminal_String QUERY_GT LT_x_m_i_COLON_X_M_I XMLDocument_ecore_EPackage_7 SLASH_GT
            //
            case 346: {
				setResult(createXMLDocument(getRhsSym(4), getRhsSym(7)));
	                  break;
            }
	
            //
            // Rule 347:  XMLDocument_ecore_EPackage ::= LT_QUERY_x_m_l encoding EQ Terminal_String QUERY_GT LT_x_m_i_COLON_X_M_I GT LT_SLASH_x_m_i_COLON_X_M_I_GT
            //
            case 347: {
				setResult(createXMLDocument(getRhsSym(4)));
	                  break;
            }
	
            //
            // Rule 348:  XMLDocument_ecore_EPackage ::= LT_QUERY_x_m_l version EQ Terminal_String QUERY_GT LT_x_m_i_COLON_X_M_I XMLDocument_ecore_EPackage_7 SLASH_GT
            //
            case 348: {
				setResult(createXMLDocument(getRhsSym(4), getRhsSym(7)));
	                  break;
            }
	
            //
            // Rule 349:  XMLDocument_ecore_EPackage ::= LT_QUERY_x_m_l version EQ Terminal_String QUERY_GT LT_x_m_i_COLON_X_M_I GT LT_SLASH_x_m_i_COLON_X_M_I_GT
            //
            case 349: {
				setResult(createXMLDocument(getRhsSym(4)));
	                  break;
            }
	
            //
            // Rule 350:  XMLDocument_ecore_EPackage ::= LT_QUERY_x_m_l encoding EQ Terminal_String QUERY_GT LT_x_m_i_COLON_X_M_I XMLDocument_ecore_EPackage_7 GT LT_SLASH_x_m_i_COLON_X_M_I_GT
            //
            case 350: {
				setResult(createXMLDocument(getRhsSym(4), getRhsSym(7)));
	                  break;
            }
	
            //
            // Rule 351:  XMLDocument_ecore_EPackage ::= LT_QUERY_x_m_l encoding EQ Terminal_String QUERY_GT LT_x_m_i_COLON_X_M_I GT XMLDocument_ecore_EPackage_2 LT_SLASH_x_m_i_COLON_X_M_I_GT
            //
            case 351: {
				setResult(createXMLDocument(getRhsSym(4), getRhsSym(8)));
	                  break;
            }
	
            //
            // Rule 352:  XMLDocument_ecore_EPackage ::= LT_QUERY_x_m_l version EQ Terminal_String QUERY_GT LT_x_m_i_COLON_X_M_I XMLDocument_ecore_EPackage_7 GT LT_SLASH_x_m_i_COLON_X_M_I_GT
            //
            case 352: {
				setResult(createXMLDocument(getRhsSym(4), getRhsSym(7)));
	                  break;
            }
	
            //
            // Rule 353:  XMLDocument_ecore_EPackage ::= LT_QUERY_x_m_l version EQ Terminal_String QUERY_GT LT_x_m_i_COLON_X_M_I GT XMLDocument_ecore_EPackage_2 LT_SLASH_x_m_i_COLON_X_M_I_GT
            //
            case 353: {
				setResult(createXMLDocument(getRhsSym(4), getRhsSym(8)));
	                  break;
            }
	
            //
            // Rule 354:  XMLDocument_ecore_EPackage ::= LT_QUERY_x_m_l version EQ Terminal_String encoding EQ Terminal_String QUERY_GT EcoreRoot_ecore_EPackage
            //
            case 354: {
				setResult(createXMLDocument(getRhsSym(4), getRhsSym(7), getRhsSym(9)));
	                  break;
            }
	
            //
            // Rule 355:  XMLDocument_ecore_EPackage ::= LT_QUERY_x_m_l encoding EQ Terminal_String QUERY_GT LT_x_m_i_COLON_X_M_I XMLDocument_ecore_EPackage_7 GT XMLDocument_ecore_EPackage_2 LT_SLASH_x_m_i_COLON_X_M_I_GT
            //
            case 355: {
				setResult(createXMLDocument(getRhsSym(4), getRhsSym(7), getRhsSym(9)));
	                  break;
            }
	
            //
            // Rule 356:  XMLDocument_ecore_EPackage ::= LT_QUERY_x_m_l version EQ Terminal_String QUERY_GT LT_x_m_i_COLON_X_M_I XMLDocument_ecore_EPackage_7 GT XMLDocument_ecore_EPackage_2 LT_SLASH_x_m_i_COLON_X_M_I_GT
            //
            case 356: {
				setResult(createXMLDocument(getRhsSym(4), getRhsSym(7), getRhsSym(9)));
	                  break;
            }
	
            //
            // Rule 357:  XMLDocument_ecore_EPackage ::= LT_QUERY_x_m_l version EQ Terminal_String encoding EQ Terminal_String QUERY_GT LT_x_m_i_COLON_X_M_I SLASH_GT
            //
            case 357: {
				setResult(createXMLDocument(getRhsSym(4), getRhsSym(7)));
	                  break;
            }
	
            //
            // Rule 358:  XMLDocument_ecore_EPackage ::= LT_QUERY_x_m_l version EQ Terminal_String encoding EQ Terminal_String QUERY_GT LT_x_m_i_COLON_X_M_I XMLDocument_ecore_EPackage_7 SLASH_GT
            //
            case 358: {
				setResult(createXMLDocument(getRhsSym(4), getRhsSym(7), getRhsSym(10)));
	                  break;
            }
	
            //
            // Rule 359:  XMLDocument_ecore_EPackage ::= LT_QUERY_x_m_l version EQ Terminal_String encoding EQ Terminal_String QUERY_GT LT_x_m_i_COLON_X_M_I GT LT_SLASH_x_m_i_COLON_X_M_I_GT
            //
            case 359: {
				setResult(createXMLDocument(getRhsSym(4), getRhsSym(7)));
	                  break;
            }
	
            //
            // Rule 360:  XMLDocument_ecore_EPackage ::= LT_QUERY_x_m_l version EQ Terminal_String encoding EQ Terminal_String QUERY_GT LT_x_m_i_COLON_X_M_I XMLDocument_ecore_EPackage_7 GT LT_SLASH_x_m_i_COLON_X_M_I_GT
            //
            case 360: {
				setResult(createXMLDocument(getRhsSym(4), getRhsSym(7), getRhsSym(10)));
	                  break;
            }
	
            //
            // Rule 361:  XMLDocument_ecore_EPackage ::= LT_QUERY_x_m_l version EQ Terminal_String encoding EQ Terminal_String QUERY_GT LT_x_m_i_COLON_X_M_I GT XMLDocument_ecore_EPackage_2 LT_SLASH_x_m_i_COLON_X_M_I_GT
            //
            case 361: {
				setResult(createXMLDocument(getRhsSym(4), getRhsSym(7), getRhsSym(11)));
	                  break;
            }
	
            //
            // Rule 362:  XMLDocument_ecore_EPackage ::= LT_QUERY_x_m_l version EQ Terminal_String encoding EQ Terminal_String QUERY_GT LT_x_m_i_COLON_X_M_I XMLDocument_ecore_EPackage_7 GT XMLDocument_ecore_EPackage_2 LT_SLASH_x_m_i_COLON_X_M_I_GT
            //
            case 362: {
				setResult(createXMLDocument(getRhsSym(4), getRhsSym(7), getRhsSym(10), getRhsSym(12)));
	                  break;
            }
	
            //
            // Rule 363:  XMLDocument_ecore_EPackage_2 ::= XmlElement
            //
            case 363: {
				setResult(SetAttributes.create(getRhsSym(1)));
	                  break;
            }
	
            //
            // Rule 364:  XMLDocument_ecore_EPackage_2 ::= XMLDocument_ecore_EPackage_2 XmlElement
            //
            case 364: {
				setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 365:  XMLDocument_ecore_EPackage_7 ::= XMLAttribute_xmi_version
            //
            case 365: {
				setResult(SetAttributes.create(getRhsSym(1)));
	                  break;
            }
	
            //
            // Rule 366:  XMLDocument_ecore_EPackage_7 ::= XMLAttribute_xmlns_
            //
            case 366: {
				setResult(SetAttributes.create(getRhsSym(1)));
	                  break;
            }
	
            //
            // Rule 367:  XMLDocument_ecore_EPackage_7 ::= XMLDocument_ecore_EPackage_7 XMLAttribute_xmi_version
            //
            case 367: {
				setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 368:  XMLDocument_ecore_EPackage_7 ::= XMLDocument_ecore_EPackage_7 XMLAttribute_xmlns_
            //
            case 368: {
				setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
	                  break;
            }
	
            //
            // Rule 369:  XmlAttribute ::= BooleanAttribute
            //
            case 369: {
				setResult(createEObject());
	                  break;
            }
	
            //
            // Rule 370:  XmlAttribute ::= IntegerAttribute
            //
            case 370: {
				setResult(createEObject());
	                  break;
            }
	
            //
            // Rule 371:  XmlAttribute ::= OtherAttribute
            //
            case 371: {
				setResult(createEObject());
	                  break;
            }
	
            //
            // Rule 372:  XmlAttribute ::= StringAttribute
            //
            case 372: {
				setResult(createEObject());
	                  break;
            }
	
            //
            // Rule 373:  XmlElement ::= EcoreClass_ecore_EClass_eOperations
            //
            case 373: {
				setResult(createEObject());
	                  break;
            }
	
            //
            // Rule 374:  XmlElement ::= EcoreClass_ecore_EClass_eStructuralFeatures
            //
            case 374: {
				setResult(createEObject());
	                  break;
            }
	
            //
            // Rule 375:  XmlElement ::= EcoreClass_ecore_EGenericType_eTypeArguments
            //
            case 375: {
				setResult(createEObject());
	                  break;
            }
	
            //
            // Rule 376:  XmlElement ::= EcoreClass_ecore_EModelElement_eAnnotations
            //
            case 376: {
				setResult(createEObject());
	                  break;
            }
	
            //
            // Rule 377:  XmlElement ::= EcoreClass_ecore_EOperation_eParameters
            //
            case 377: {
				setResult(createEObject());
	                  break;
            }
	
            //
            // Rule 378:  XmlElement ::= EcoreClass_ecore_EPackage_eClassifiers
            //
            case 378: {
				setResult(createEObject());
	                  break;
            }
	
            //
            // Rule 379:  XmlElement ::= EcoreClass_ecore_EPackage_eSubpackages
            //
            case 379: {
				setResult(createEObject());
	                  break;
            }
	
            //
            // Rule 380:  XmlElement ::= EcoreClass_ecore_ETypedElement_eGenericType
            //
            case 380: {
				setResult(createEObject());
	                  break;
            }
	
            //
            // Rule 381:  XmlElement ::= EcoreFeature_ecore_EAnnotation_details
            //
            case 381: {
				setResult(createEObject());
	                  break;
            }
	
            //
            // Rule 382:  XmlElement ::= OtherElement
            //
            case 382: {
				setResult(createEObject());
	                  break;
            }
	
    
            default:
                break;
        }
        return;
    }
}

