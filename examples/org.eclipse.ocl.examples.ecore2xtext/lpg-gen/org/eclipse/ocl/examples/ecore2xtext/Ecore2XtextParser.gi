%options escape=$
%options la=1
%options fp=Ecore2XtextParser,prefix=TK_
%options noserialize
%options package=org.eclipse.ocl.examples.ecore2xtext
%options import_terminals=Ecore2XtextLexer.gi
%options ast_type=XMLResource
%options template=dtParserTemplateF.gi
%options include_directory=".;../lpg"

%Start
XMLDocument_ecore_EPackage
%End

%Define
    -- Redefinition of macros used in the parser template
    --
    $default_repair_count /.getDefaultRepairCount()./
	$super_parser_class /.AbstractEcore2XtextParser./
%End

%Notice
	/./**
 *******************************************************************************/
	./
%End

%Globals
	/.
	/* imports */
	import org.eclipse.emf.ecore.*;
	import org.eclipse.emf.ecore.xmi.XMLResource;
	./
%End

--%KeyWords
-- Reserved keywords
--	body context def derive endpackage init inv package post pre static

-- Restricted keywords
--	OclMessage
--%End

%Terminals
	IDENTIFIER
	STRING
	WS

	SLASH_GT ::= '/>'
	COLON ::= ':'
	LT ::= '<'
	LT_SLASH ::= '</'
	LT_SLASH_d_e_t_a_i_l_s_GT ::= '</details>'
	LT_SLASH_e_A_n_n_o_t_a_t_i_o_n_s_GT ::= '</eAnnotations>'
	LT_SLASH_e_C_l_a_s_s_i_f_i_e_r_s_GT ::= '</eClassifiers>'
	LT_SLASH_e_G_e_n_e_r_i_c_T_y_p_e_GT ::= '</eGenericType>'
	LT_SLASH_e_O_p_e_r_a_t_i_o_n_s_GT ::= '</eOperations>'
	LT_SLASH_e_P_a_c_k_a_g_e_s_GT ::= '</ePackages>'
	LT_SLASH_e_P_a_r_a_m_e_t_e_r_s_GT ::= '</eParameters>'
	LT_SLASH_e_S_t_r_u_c_t_u_r_a_l_F_e_a_t_u_r_e_s_GT ::= '</eStructuralFeatures>'
	LT_SLASH_e_T_y_p_e_A_r_g_u_m_e_n_t_s_GT ::= '</eTypeArguments>'
	LT_SLASH_e_c_o_r_e_COLON_E_P_a_c_k_a_g_e_GT ::= '</ecore:EPackage>'
	LT_SLASH_x_m_i_COLON_X_M_I_GT ::= '</xmi:XMI>'
	LT_QUERY_x_m_l ::= '<?xml'
	LT_d_e_t_a_i_l_s ::= '<details'
	LT_e_A_n_n_o_t_a_t_i_o_n_s ::= '<eAnnotations'
	LT_e_C_l_a_s_s_i_f_i_e_r_s ::= '<eClassifiers'
	LT_e_G_e_n_e_r_i_c_T_y_p_e ::= '<eGenericType'
	LT_e_O_p_e_r_a_t_i_o_n_s ::= '<eOperations'
	LT_e_P_a_r_a_m_e_t_e_r_s ::= '<eParameters'
	LT_e_S_t_r_u_c_t_u_r_a_l_F_e_a_t_u_r_e_s ::= '<eStructuralFeatures'
	LT_e_S_u_b_p_a_c_k_a_g_e_s ::= '<eSubpackages'
	LT_e_T_y_p_e_A_r_g_u_m_e_n_t_s ::= '<eTypeArguments'
	LT_e_c_o_r_e_COLON_E_P_a_c_k_a_g_e ::= '<ecore:EPackage'
	LT_x_m_i_COLON_X_M_I ::= '<xmi:XMI'
	EQ ::= '='
	GT ::= '>'
	QUERY_GT ::= '?>'
%End

%Rules
BooleanAttribute ::= EcoreFeature_ecore_EClass_abstract --1
		/.$BeginAction
					setResult(createEObject());
		  $EndAction
		./
BooleanAttribute ::= EcoreFeature_ecore_EDataType_serializable --2
		/.$BeginAction
					setResult(createEObject());
		  $EndAction
		./
BooleanAttribute ::= EcoreFeature_ecore_EReference_containment --3
		/.$BeginAction
					setResult(createEObject());
		  $EndAction
		./
BooleanAttribute ::= EcoreFeature_ecore_EReference_resolveProxies --4
		/.$BeginAction
					setResult(createEObject());
		  $EndAction
		./
BooleanAttribute ::= EcoreFeature_ecore_EStructuralFeature_changeable --5
		/.$BeginAction
					setResult(createEObject());
		  $EndAction
		./
BooleanAttribute ::= EcoreFeature_ecore_EStructuralFeature_defaultValueLiteral --6
		/.$BeginAction
					setResult(createEObject());
		  $EndAction
		./
BooleanAttribute ::= EcoreFeature_ecore_EStructuralFeature_derived --7
		/.$BeginAction
					setResult(createEObject());
		  $EndAction
		./
BooleanAttribute ::= EcoreFeature_ecore_EStructuralFeature_transient --8
		/.$BeginAction
					setResult(createEObject());
		  $EndAction
		./
BooleanAttribute ::= EcoreFeature_ecore_EStructuralFeature_unsettable --9
		/.$BeginAction
					setResult(createEObject());
		  $EndAction
		./
BooleanAttribute ::= EcoreFeature_ecore_EStructuralFeature_volatile --10
		/.$BeginAction
					setResult(createEObject());
		  $EndAction
		./
BooleanAttribute ::= EcoreFeature_ecore_ETypedElement_ordered --11
		/.$BeginAction
					setResult(createEObject());
		  $EndAction
		./

EcoreClass_ecore_EClass_eOperations ::= LT_e_O_p_e_r_a_t_i_o_n_s SLASH_GT --12
		/.$BeginAction
					setResult(createEcoreClass(org.eclipse.emf.ecore.EcorePackage.Literals.ECLASS__EOPERATIONS));
		  $EndAction
		./
EcoreClass_ecore_EClass_eOperations ::= LT_e_O_p_e_r_a_t_i_o_n_s EcoreClass_ecore_EClass_eOperations_8 SLASH_GT --13
		/.$BeginAction
					setResult(createEcoreClass(org.eclipse.emf.ecore.EcorePackage.Literals.ECLASS__EOPERATIONS, getRhsSym(2)));
		  $EndAction
		./
EcoreClass_ecore_EClass_eOperations ::= LT_e_O_p_e_r_a_t_i_o_n_s GT LT_SLASH_e_O_p_e_r_a_t_i_o_n_s_GT --14
		/.$BeginAction
					setResult(createEcoreClass(org.eclipse.emf.ecore.EcorePackage.Literals.ECLASS__EOPERATIONS));
		  $EndAction
		./
EcoreClass_ecore_EClass_eOperations ::= LT_e_O_p_e_r_a_t_i_o_n_s EcoreClass_ecore_EClass_eOperations_8 GT LT_SLASH_e_O_p_e_r_a_t_i_o_n_s_GT --15
		/.$BeginAction
					setResult(createEcoreClass(org.eclipse.emf.ecore.EcorePackage.Literals.ECLASS__EOPERATIONS, getRhsSym(2)));
		  $EndAction
		./
EcoreClass_ecore_EClass_eOperations ::= LT_e_O_p_e_r_a_t_i_o_n_s GT EcoreClass_ecore_EClass_eOperations_3 LT_SLASH_e_O_p_e_r_a_t_i_o_n_s_GT --16
		/.$BeginAction
					setResult(createEcoreClass(org.eclipse.emf.ecore.EcorePackage.Literals.ECLASS__EOPERATIONS, getRhsSym(3)));
		  $EndAction
		./
EcoreClass_ecore_EClass_eOperations ::= LT_e_O_p_e_r_a_t_i_o_n_s EcoreClass_ecore_EClass_eOperations_8 GT EcoreClass_ecore_EClass_eOperations_3 LT_SLASH_e_O_p_e_r_a_t_i_o_n_s_GT --17
		/.$BeginAction
					setResult(createEcoreClass(org.eclipse.emf.ecore.EcorePackage.Literals.ECLASS__EOPERATIONS, getRhsSym(2), getRhsSym(4)));
		  $EndAction
		./
EcoreClass_ecore_EClass_eOperations_3 ::= EcoreClass_ecore_EModelElement_eAnnotations --18
		/.$BeginAction
					setResult(SetAttributes.create(getRhsSym(1)));
		  $EndAction
		./
EcoreClass_ecore_EClass_eOperations_3 ::= EcoreClass_ecore_EOperation_eParameters --19
		/.$BeginAction
					setResult(SetAttributes.create(getRhsSym(1)));
		  $EndAction
		./
EcoreClass_ecore_EClass_eOperations_3 ::= EcoreClass_ecore_ETypedElement_eGenericType --20
		/.$BeginAction
					setResult(SetAttributes.create(getRhsSym(1)));
		  $EndAction
		./
EcoreClass_ecore_EClass_eOperations_3 ::= OtherElement --21
		/.$BeginAction
					setResult(SetAttributes.create(getRhsSym(1)));
		  $EndAction
		./
EcoreClass_ecore_EClass_eOperations_3 ::= EcoreClass_ecore_EClass_eOperations_3 EcoreClass_ecore_EModelElement_eAnnotations --22
		/.$BeginAction
					setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
		  $EndAction
		./
EcoreClass_ecore_EClass_eOperations_3 ::= EcoreClass_ecore_EClass_eOperations_3 EcoreClass_ecore_EOperation_eParameters --23
		/.$BeginAction
					setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
		  $EndAction
		./
EcoreClass_ecore_EClass_eOperations_3 ::= EcoreClass_ecore_EClass_eOperations_3 EcoreClass_ecore_ETypedElement_eGenericType --24
		/.$BeginAction
					setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
		  $EndAction
		./
EcoreClass_ecore_EClass_eOperations_3 ::= EcoreClass_ecore_EClass_eOperations_3 OtherElement --25
		/.$BeginAction
					setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
		  $EndAction
		./
EcoreClass_ecore_EClass_eOperations_8 ::= EcoreFeature_ecore_ENamedElement_name --26
		/.$BeginAction
					setResult(SetAttributes.create(getRhsSym(1)));
		  $EndAction
		./
EcoreClass_ecore_EClass_eOperations_8 ::= EcoreFeature_ecore_EOperation_eExceptions --27
		/.$BeginAction
					setResult(SetAttributes.create(getRhsSym(1)));
		  $EndAction
		./
EcoreClass_ecore_EClass_eOperations_8 ::= EcoreFeature_ecore_ETypedElement_eType --28
		/.$BeginAction
					setResult(SetAttributes.create(getRhsSym(1)));
		  $EndAction
		./
EcoreClass_ecore_EClass_eOperations_8 ::= OtherAttribute --29
		/.$BeginAction
					setResult(SetAttributes.create(getRhsSym(1)));
		  $EndAction
		./
EcoreClass_ecore_EClass_eOperations_8 ::= XMLAttribute_xsi_type --30
		/.$BeginAction
					setResult(SetAttributes.create(getRhsSym(1)));
		  $EndAction
		./
EcoreClass_ecore_EClass_eOperations_8 ::= EcoreClass_ecore_EClass_eOperations_8 EcoreFeature_ecore_ENamedElement_name --31
		/.$BeginAction
					setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
		  $EndAction
		./
EcoreClass_ecore_EClass_eOperations_8 ::= EcoreClass_ecore_EClass_eOperations_8 EcoreFeature_ecore_EOperation_eExceptions --32
		/.$BeginAction
					setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
		  $EndAction
		./
EcoreClass_ecore_EClass_eOperations_8 ::= EcoreClass_ecore_EClass_eOperations_8 EcoreFeature_ecore_ETypedElement_eType --33
		/.$BeginAction
					setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
		  $EndAction
		./
EcoreClass_ecore_EClass_eOperations_8 ::= EcoreClass_ecore_EClass_eOperations_8 OtherAttribute --34
		/.$BeginAction
					setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
		  $EndAction
		./
EcoreClass_ecore_EClass_eOperations_8 ::= EcoreClass_ecore_EClass_eOperations_8 XMLAttribute_xsi_type --35
		/.$BeginAction
					setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
		  $EndAction
		./

EcoreClass_ecore_EClass_eStructuralFeatures ::= LT_e_S_t_r_u_c_t_u_r_a_l_F_e_a_t_u_r_e_s SLASH_GT --36
		/.$BeginAction
					setResult(createEcoreClass(org.eclipse.emf.ecore.EcorePackage.Literals.ECLASS__ESTRUCTURAL_FEATURES));
		  $EndAction
		./
EcoreClass_ecore_EClass_eStructuralFeatures ::= LT_e_S_t_r_u_c_t_u_r_a_l_F_e_a_t_u_r_e_s EcoreClass_ecore_EClass_eStructuralFeatures_8 SLASH_GT --37
		/.$BeginAction
					setResult(createEcoreClass(org.eclipse.emf.ecore.EcorePackage.Literals.ECLASS__ESTRUCTURAL_FEATURES, getRhsSym(2)));
		  $EndAction
		./
EcoreClass_ecore_EClass_eStructuralFeatures ::= LT_e_S_t_r_u_c_t_u_r_a_l_F_e_a_t_u_r_e_s GT LT_SLASH_e_S_t_r_u_c_t_u_r_a_l_F_e_a_t_u_r_e_s_GT --38
		/.$BeginAction
					setResult(createEcoreClass(org.eclipse.emf.ecore.EcorePackage.Literals.ECLASS__ESTRUCTURAL_FEATURES));
		  $EndAction
		./
EcoreClass_ecore_EClass_eStructuralFeatures ::= LT_e_S_t_r_u_c_t_u_r_a_l_F_e_a_t_u_r_e_s EcoreClass_ecore_EClass_eStructuralFeatures_8 GT LT_SLASH_e_S_t_r_u_c_t_u_r_a_l_F_e_a_t_u_r_e_s_GT --39
		/.$BeginAction
					setResult(createEcoreClass(org.eclipse.emf.ecore.EcorePackage.Literals.ECLASS__ESTRUCTURAL_FEATURES, getRhsSym(2)));
		  $EndAction
		./
EcoreClass_ecore_EClass_eStructuralFeatures ::= LT_e_S_t_r_u_c_t_u_r_a_l_F_e_a_t_u_r_e_s GT EcoreClass_ecore_EClass_eStructuralFeatures_3 LT_SLASH_e_S_t_r_u_c_t_u_r_a_l_F_e_a_t_u_r_e_s_GT --40
		/.$BeginAction
					setResult(createEcoreClass(org.eclipse.emf.ecore.EcorePackage.Literals.ECLASS__ESTRUCTURAL_FEATURES, getRhsSym(3)));
		  $EndAction
		./
EcoreClass_ecore_EClass_eStructuralFeatures ::= LT_e_S_t_r_u_c_t_u_r_a_l_F_e_a_t_u_r_e_s EcoreClass_ecore_EClass_eStructuralFeatures_8 GT EcoreClass_ecore_EClass_eStructuralFeatures_3 LT_SLASH_e_S_t_r_u_c_t_u_r_a_l_F_e_a_t_u_r_e_s_GT --41
		/.$BeginAction
					setResult(createEcoreClass(org.eclipse.emf.ecore.EcorePackage.Literals.ECLASS__ESTRUCTURAL_FEATURES, getRhsSym(2), getRhsSym(4)));
		  $EndAction
		./
EcoreClass_ecore_EClass_eStructuralFeatures_3 ::= EcoreClass_ecore_EModelElement_eAnnotations --42
		/.$BeginAction
					setResult(SetAttributes.create(getRhsSym(1)));
		  $EndAction
		./
EcoreClass_ecore_EClass_eStructuralFeatures_3 ::= EcoreClass_ecore_ETypedElement_eGenericType --43
		/.$BeginAction
					setResult(SetAttributes.create(getRhsSym(1)));
		  $EndAction
		./
EcoreClass_ecore_EClass_eStructuralFeatures_3 ::= OtherElement --44
		/.$BeginAction
					setResult(SetAttributes.create(getRhsSym(1)));
		  $EndAction
		./
EcoreClass_ecore_EClass_eStructuralFeatures_3 ::= EcoreClass_ecore_EClass_eStructuralFeatures_3 EcoreClass_ecore_EModelElement_eAnnotations --45
		/.$BeginAction
					setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
		  $EndAction
		./
EcoreClass_ecore_EClass_eStructuralFeatures_3 ::= EcoreClass_ecore_EClass_eStructuralFeatures_3 EcoreClass_ecore_ETypedElement_eGenericType --46
		/.$BeginAction
					setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
		  $EndAction
		./
EcoreClass_ecore_EClass_eStructuralFeatures_3 ::= EcoreClass_ecore_EClass_eStructuralFeatures_3 OtherElement --47
		/.$BeginAction
					setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
		  $EndAction
		./
EcoreClass_ecore_EClass_eStructuralFeatures_8 ::= EcoreFeature_ecore_ENamedElement_name --48
		/.$BeginAction
					setResult(SetAttributes.create(getRhsSym(1)));
		  $EndAction
		./
EcoreClass_ecore_EClass_eStructuralFeatures_8 ::= EcoreFeature_ecore_EReference_containment --49
		/.$BeginAction
					setResult(SetAttributes.create(getRhsSym(1)));
		  $EndAction
		./
EcoreClass_ecore_EClass_eStructuralFeatures_8 ::= EcoreFeature_ecore_EReference_eOpposite --50
		/.$BeginAction
					setResult(SetAttributes.create(getRhsSym(1)));
		  $EndAction
		./
EcoreClass_ecore_EClass_eStructuralFeatures_8 ::= EcoreFeature_ecore_EReference_resolveProxies --51
		/.$BeginAction
					setResult(SetAttributes.create(getRhsSym(1)));
		  $EndAction
		./
EcoreClass_ecore_EClass_eStructuralFeatures_8 ::= EcoreFeature_ecore_EStructuralFeature_changeable --52
		/.$BeginAction
					setResult(SetAttributes.create(getRhsSym(1)));
		  $EndAction
		./
EcoreClass_ecore_EClass_eStructuralFeatures_8 ::= EcoreFeature_ecore_EStructuralFeature_defaultValueLiteral --53
		/.$BeginAction
					setResult(SetAttributes.create(getRhsSym(1)));
		  $EndAction
		./
EcoreClass_ecore_EClass_eStructuralFeatures_8 ::= EcoreFeature_ecore_EStructuralFeature_derived --54
		/.$BeginAction
					setResult(SetAttributes.create(getRhsSym(1)));
		  $EndAction
		./
EcoreClass_ecore_EClass_eStructuralFeatures_8 ::= EcoreFeature_ecore_EStructuralFeature_transient --55
		/.$BeginAction
					setResult(SetAttributes.create(getRhsSym(1)));
		  $EndAction
		./
EcoreClass_ecore_EClass_eStructuralFeatures_8 ::= EcoreFeature_ecore_EStructuralFeature_unsettable --56
		/.$BeginAction
					setResult(SetAttributes.create(getRhsSym(1)));
		  $EndAction
		./
EcoreClass_ecore_EClass_eStructuralFeatures_8 ::= EcoreFeature_ecore_EStructuralFeature_volatile --57
		/.$BeginAction
					setResult(SetAttributes.create(getRhsSym(1)));
		  $EndAction
		./
EcoreClass_ecore_EClass_eStructuralFeatures_8 ::= EcoreFeature_ecore_ETypedElement_eType --58
		/.$BeginAction
					setResult(SetAttributes.create(getRhsSym(1)));
		  $EndAction
		./
EcoreClass_ecore_EClass_eStructuralFeatures_8 ::= EcoreFeature_ecore_ETypedElement_lowerBound --59
		/.$BeginAction
					setResult(SetAttributes.create(getRhsSym(1)));
		  $EndAction
		./
EcoreClass_ecore_EClass_eStructuralFeatures_8 ::= EcoreFeature_ecore_ETypedElement_ordered --60
		/.$BeginAction
					setResult(SetAttributes.create(getRhsSym(1)));
		  $EndAction
		./
EcoreClass_ecore_EClass_eStructuralFeatures_8 ::= EcoreFeature_ecore_ETypedElement_upperBound --61
		/.$BeginAction
					setResult(SetAttributes.create(getRhsSym(1)));
		  $EndAction
		./
EcoreClass_ecore_EClass_eStructuralFeatures_8 ::= OtherAttribute --62
		/.$BeginAction
					setResult(SetAttributes.create(getRhsSym(1)));
		  $EndAction
		./
EcoreClass_ecore_EClass_eStructuralFeatures_8 ::= XMLAttribute_xsi_type --63
		/.$BeginAction
					setResult(SetAttributes.create(getRhsSym(1)));
		  $EndAction
		./
EcoreClass_ecore_EClass_eStructuralFeatures_8 ::= EcoreClass_ecore_EClass_eStructuralFeatures_8 EcoreFeature_ecore_ENamedElement_name --64
		/.$BeginAction
					setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
		  $EndAction
		./
EcoreClass_ecore_EClass_eStructuralFeatures_8 ::= EcoreClass_ecore_EClass_eStructuralFeatures_8 EcoreFeature_ecore_EReference_containment --65
		/.$BeginAction
					setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
		  $EndAction
		./
EcoreClass_ecore_EClass_eStructuralFeatures_8 ::= EcoreClass_ecore_EClass_eStructuralFeatures_8 EcoreFeature_ecore_EReference_eOpposite --66
		/.$BeginAction
					setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
		  $EndAction
		./
EcoreClass_ecore_EClass_eStructuralFeatures_8 ::= EcoreClass_ecore_EClass_eStructuralFeatures_8 EcoreFeature_ecore_EReference_resolveProxies --67
		/.$BeginAction
					setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
		  $EndAction
		./
EcoreClass_ecore_EClass_eStructuralFeatures_8 ::= EcoreClass_ecore_EClass_eStructuralFeatures_8 EcoreFeature_ecore_EStructuralFeature_changeable --68
		/.$BeginAction
					setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
		  $EndAction
		./
EcoreClass_ecore_EClass_eStructuralFeatures_8 ::= EcoreClass_ecore_EClass_eStructuralFeatures_8 EcoreFeature_ecore_EStructuralFeature_defaultValueLiteral --69
		/.$BeginAction
					setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
		  $EndAction
		./
EcoreClass_ecore_EClass_eStructuralFeatures_8 ::= EcoreClass_ecore_EClass_eStructuralFeatures_8 EcoreFeature_ecore_EStructuralFeature_derived --70
		/.$BeginAction
					setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
		  $EndAction
		./
EcoreClass_ecore_EClass_eStructuralFeatures_8 ::= EcoreClass_ecore_EClass_eStructuralFeatures_8 EcoreFeature_ecore_EStructuralFeature_transient --71
		/.$BeginAction
					setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
		  $EndAction
		./
EcoreClass_ecore_EClass_eStructuralFeatures_8 ::= EcoreClass_ecore_EClass_eStructuralFeatures_8 EcoreFeature_ecore_EStructuralFeature_unsettable --72
		/.$BeginAction
					setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
		  $EndAction
		./
EcoreClass_ecore_EClass_eStructuralFeatures_8 ::= EcoreClass_ecore_EClass_eStructuralFeatures_8 EcoreFeature_ecore_EStructuralFeature_volatile --73
		/.$BeginAction
					setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
		  $EndAction
		./
EcoreClass_ecore_EClass_eStructuralFeatures_8 ::= EcoreClass_ecore_EClass_eStructuralFeatures_8 EcoreFeature_ecore_ETypedElement_eType --74
		/.$BeginAction
					setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
		  $EndAction
		./
EcoreClass_ecore_EClass_eStructuralFeatures_8 ::= EcoreClass_ecore_EClass_eStructuralFeatures_8 EcoreFeature_ecore_ETypedElement_lowerBound --75
		/.$BeginAction
					setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
		  $EndAction
		./
EcoreClass_ecore_EClass_eStructuralFeatures_8 ::= EcoreClass_ecore_EClass_eStructuralFeatures_8 EcoreFeature_ecore_ETypedElement_ordered --76
		/.$BeginAction
					setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
		  $EndAction
		./
EcoreClass_ecore_EClass_eStructuralFeatures_8 ::= EcoreClass_ecore_EClass_eStructuralFeatures_8 EcoreFeature_ecore_ETypedElement_upperBound --77
		/.$BeginAction
					setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
		  $EndAction
		./
EcoreClass_ecore_EClass_eStructuralFeatures_8 ::= EcoreClass_ecore_EClass_eStructuralFeatures_8 OtherAttribute --78
		/.$BeginAction
					setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
		  $EndAction
		./
EcoreClass_ecore_EClass_eStructuralFeatures_8 ::= EcoreClass_ecore_EClass_eStructuralFeatures_8 XMLAttribute_xsi_type --79
		/.$BeginAction
					setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
		  $EndAction
		./

EcoreClass_ecore_EGenericType_eTypeArguments ::= LT_e_T_y_p_e_A_r_g_u_m_e_n_t_s SLASH_GT --80
		/.$BeginAction
					setResult(createEcoreClass(org.eclipse.emf.ecore.EcorePackage.Literals.EGENERIC_TYPE__ETYPE_ARGUMENTS));
		  $EndAction
		./
EcoreClass_ecore_EGenericType_eTypeArguments ::= LT_e_T_y_p_e_A_r_g_u_m_e_n_t_s EcoreClass_ecore_EGenericType_eTypeArguments_8 SLASH_GT --81
		/.$BeginAction
					setResult(createEcoreClass(org.eclipse.emf.ecore.EcorePackage.Literals.EGENERIC_TYPE__ETYPE_ARGUMENTS, getRhsSym(2)));
		  $EndAction
		./
EcoreClass_ecore_EGenericType_eTypeArguments ::= LT_e_T_y_p_e_A_r_g_u_m_e_n_t_s GT LT_SLASH_e_T_y_p_e_A_r_g_u_m_e_n_t_s_GT --82
		/.$BeginAction
					setResult(createEcoreClass(org.eclipse.emf.ecore.EcorePackage.Literals.EGENERIC_TYPE__ETYPE_ARGUMENTS));
		  $EndAction
		./
EcoreClass_ecore_EGenericType_eTypeArguments ::= LT_e_T_y_p_e_A_r_g_u_m_e_n_t_s EcoreClass_ecore_EGenericType_eTypeArguments_8 GT LT_SLASH_e_T_y_p_e_A_r_g_u_m_e_n_t_s_GT --83
		/.$BeginAction
					setResult(createEcoreClass(org.eclipse.emf.ecore.EcorePackage.Literals.EGENERIC_TYPE__ETYPE_ARGUMENTS, getRhsSym(2)));
		  $EndAction
		./
EcoreClass_ecore_EGenericType_eTypeArguments ::= LT_e_T_y_p_e_A_r_g_u_m_e_n_t_s GT EcoreClass_ecore_EGenericType_eTypeArguments_3 LT_SLASH_e_T_y_p_e_A_r_g_u_m_e_n_t_s_GT --84
		/.$BeginAction
					setResult(createEcoreClass(org.eclipse.emf.ecore.EcorePackage.Literals.EGENERIC_TYPE__ETYPE_ARGUMENTS, getRhsSym(3)));
		  $EndAction
		./
EcoreClass_ecore_EGenericType_eTypeArguments ::= LT_e_T_y_p_e_A_r_g_u_m_e_n_t_s EcoreClass_ecore_EGenericType_eTypeArguments_8 GT EcoreClass_ecore_EGenericType_eTypeArguments_3 LT_SLASH_e_T_y_p_e_A_r_g_u_m_e_n_t_s_GT --85
		/.$BeginAction
					setResult(createEcoreClass(org.eclipse.emf.ecore.EcorePackage.Literals.EGENERIC_TYPE__ETYPE_ARGUMENTS, getRhsSym(2), getRhsSym(4)));
		  $EndAction
		./
EcoreClass_ecore_EGenericType_eTypeArguments_3 ::= EcoreClass_ecore_EModelElement_eAnnotations --86
		/.$BeginAction
					setResult(SetAttributes.create(getRhsSym(1)));
		  $EndAction
		./
EcoreClass_ecore_EGenericType_eTypeArguments_3 ::= OtherElement --87
		/.$BeginAction
					setResult(SetAttributes.create(getRhsSym(1)));
		  $EndAction
		./
EcoreClass_ecore_EGenericType_eTypeArguments_3 ::= EcoreClass_ecore_EGenericType_eTypeArguments_3 EcoreClass_ecore_EModelElement_eAnnotations --88
		/.$BeginAction
					setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
		  $EndAction
		./
EcoreClass_ecore_EGenericType_eTypeArguments_3 ::= EcoreClass_ecore_EGenericType_eTypeArguments_3 OtherElement --89
		/.$BeginAction
					setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
		  $EndAction
		./
EcoreClass_ecore_EGenericType_eTypeArguments_8 ::= EcoreFeature_ecore_EPackage_eClassifiers --90
		/.$BeginAction
					setResult(SetAttributes.create(getRhsSym(1)));
		  $EndAction
		./
EcoreClass_ecore_EGenericType_eTypeArguments_8 ::= OtherAttribute --91
		/.$BeginAction
					setResult(SetAttributes.create(getRhsSym(1)));
		  $EndAction
		./
EcoreClass_ecore_EGenericType_eTypeArguments_8 ::= XMLAttribute_xsi_type --92
		/.$BeginAction
					setResult(SetAttributes.create(getRhsSym(1)));
		  $EndAction
		./
EcoreClass_ecore_EGenericType_eTypeArguments_8 ::= EcoreClass_ecore_EGenericType_eTypeArguments_8 EcoreFeature_ecore_EPackage_eClassifiers --93
		/.$BeginAction
					setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
		  $EndAction
		./
EcoreClass_ecore_EGenericType_eTypeArguments_8 ::= EcoreClass_ecore_EGenericType_eTypeArguments_8 OtherAttribute --94
		/.$BeginAction
					setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
		  $EndAction
		./
EcoreClass_ecore_EGenericType_eTypeArguments_8 ::= EcoreClass_ecore_EGenericType_eTypeArguments_8 XMLAttribute_xsi_type --95
		/.$BeginAction
					setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
		  $EndAction
		./

EcoreClass_ecore_EModelElement_eAnnotations ::= LT_e_A_n_n_o_t_a_t_i_o_n_s SLASH_GT --96
		/.$BeginAction
					setResult(createEcoreClass(org.eclipse.emf.ecore.EcorePackage.Literals.EMODEL_ELEMENT__EANNOTATIONS));
		  $EndAction
		./
EcoreClass_ecore_EModelElement_eAnnotations ::= LT_e_A_n_n_o_t_a_t_i_o_n_s EcoreClass_ecore_EModelElement_eAnnotations_8 SLASH_GT --97
		/.$BeginAction
					setResult(createEcoreClass(org.eclipse.emf.ecore.EcorePackage.Literals.EMODEL_ELEMENT__EANNOTATIONS, getRhsSym(2)));
		  $EndAction
		./
EcoreClass_ecore_EModelElement_eAnnotations ::= LT_e_A_n_n_o_t_a_t_i_o_n_s GT LT_SLASH_e_A_n_n_o_t_a_t_i_o_n_s_GT --98
		/.$BeginAction
					setResult(createEcoreClass(org.eclipse.emf.ecore.EcorePackage.Literals.EMODEL_ELEMENT__EANNOTATIONS));
		  $EndAction
		./
EcoreClass_ecore_EModelElement_eAnnotations ::= LT_e_A_n_n_o_t_a_t_i_o_n_s EcoreClass_ecore_EModelElement_eAnnotations_8 GT LT_SLASH_e_A_n_n_o_t_a_t_i_o_n_s_GT --99
		/.$BeginAction
					setResult(createEcoreClass(org.eclipse.emf.ecore.EcorePackage.Literals.EMODEL_ELEMENT__EANNOTATIONS, getRhsSym(2)));
		  $EndAction
		./
EcoreClass_ecore_EModelElement_eAnnotations ::= LT_e_A_n_n_o_t_a_t_i_o_n_s GT EcoreClass_ecore_EModelElement_eAnnotations_3 LT_SLASH_e_A_n_n_o_t_a_t_i_o_n_s_GT --100
		/.$BeginAction
					setResult(createEcoreClass(org.eclipse.emf.ecore.EcorePackage.Literals.EMODEL_ELEMENT__EANNOTATIONS, getRhsSym(3)));
		  $EndAction
		./
EcoreClass_ecore_EModelElement_eAnnotations ::= LT_e_A_n_n_o_t_a_t_i_o_n_s EcoreClass_ecore_EModelElement_eAnnotations_8 GT EcoreClass_ecore_EModelElement_eAnnotations_3 LT_SLASH_e_A_n_n_o_t_a_t_i_o_n_s_GT --101
		/.$BeginAction
					setResult(createEcoreClass(org.eclipse.emf.ecore.EcorePackage.Literals.EMODEL_ELEMENT__EANNOTATIONS, getRhsSym(2), getRhsSym(4)));
		  $EndAction
		./
EcoreClass_ecore_EModelElement_eAnnotations_3 ::= EcoreClass_ecore_EModelElement_eAnnotations --102
		/.$BeginAction
					setResult(SetAttributes.create(getRhsSym(1)));
		  $EndAction
		./
EcoreClass_ecore_EModelElement_eAnnotations_3 ::= EcoreFeature_ecore_EAnnotation_details --103
		/.$BeginAction
					setResult(SetAttributes.create(getRhsSym(1)));
		  $EndAction
		./
EcoreClass_ecore_EModelElement_eAnnotations_3 ::= OtherElement --104
		/.$BeginAction
					setResult(SetAttributes.create(getRhsSym(1)));
		  $EndAction
		./
EcoreClass_ecore_EModelElement_eAnnotations_3 ::= EcoreClass_ecore_EModelElement_eAnnotations_3 EcoreClass_ecore_EModelElement_eAnnotations --105
		/.$BeginAction
					setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
		  $EndAction
		./
EcoreClass_ecore_EModelElement_eAnnotations_3 ::= EcoreClass_ecore_EModelElement_eAnnotations_3 EcoreFeature_ecore_EAnnotation_details --106
		/.$BeginAction
					setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
		  $EndAction
		./
EcoreClass_ecore_EModelElement_eAnnotations_3 ::= EcoreClass_ecore_EModelElement_eAnnotations_3 OtherElement --107
		/.$BeginAction
					setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
		  $EndAction
		./
EcoreClass_ecore_EModelElement_eAnnotations_8 ::= EcoreFeature_ecore_EAnnotation_source --108
		/.$BeginAction
					setResult(SetAttributes.create(getRhsSym(1)));
		  $EndAction
		./
EcoreClass_ecore_EModelElement_eAnnotations_8 ::= OtherAttribute --109
		/.$BeginAction
					setResult(SetAttributes.create(getRhsSym(1)));
		  $EndAction
		./
EcoreClass_ecore_EModelElement_eAnnotations_8 ::= XMLAttribute_xsi_type --110
		/.$BeginAction
					setResult(SetAttributes.create(getRhsSym(1)));
		  $EndAction
		./
EcoreClass_ecore_EModelElement_eAnnotations_8 ::= EcoreClass_ecore_EModelElement_eAnnotations_8 EcoreFeature_ecore_EAnnotation_source --111
		/.$BeginAction
					setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
		  $EndAction
		./
EcoreClass_ecore_EModelElement_eAnnotations_8 ::= EcoreClass_ecore_EModelElement_eAnnotations_8 OtherAttribute --112
		/.$BeginAction
					setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
		  $EndAction
		./
EcoreClass_ecore_EModelElement_eAnnotations_8 ::= EcoreClass_ecore_EModelElement_eAnnotations_8 XMLAttribute_xsi_type --113
		/.$BeginAction
					setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
		  $EndAction
		./

EcoreClass_ecore_EOperation_eParameters ::= LT_e_P_a_r_a_m_e_t_e_r_s SLASH_GT --114
		/.$BeginAction
					setResult(createEcoreClass(org.eclipse.emf.ecore.EcorePackage.Literals.EOPERATION__EPARAMETERS));
		  $EndAction
		./
EcoreClass_ecore_EOperation_eParameters ::= LT_e_P_a_r_a_m_e_t_e_r_s EcoreClass_ecore_EOperation_eParameters_8 SLASH_GT --115
		/.$BeginAction
					setResult(createEcoreClass(org.eclipse.emf.ecore.EcorePackage.Literals.EOPERATION__EPARAMETERS, getRhsSym(2)));
		  $EndAction
		./
EcoreClass_ecore_EOperation_eParameters ::= LT_e_P_a_r_a_m_e_t_e_r_s GT LT_SLASH_e_P_a_r_a_m_e_t_e_r_s_GT --116
		/.$BeginAction
					setResult(createEcoreClass(org.eclipse.emf.ecore.EcorePackage.Literals.EOPERATION__EPARAMETERS));
		  $EndAction
		./
EcoreClass_ecore_EOperation_eParameters ::= LT_e_P_a_r_a_m_e_t_e_r_s EcoreClass_ecore_EOperation_eParameters_8 GT LT_SLASH_e_P_a_r_a_m_e_t_e_r_s_GT --117
		/.$BeginAction
					setResult(createEcoreClass(org.eclipse.emf.ecore.EcorePackage.Literals.EOPERATION__EPARAMETERS, getRhsSym(2)));
		  $EndAction
		./
EcoreClass_ecore_EOperation_eParameters ::= LT_e_P_a_r_a_m_e_t_e_r_s GT EcoreClass_ecore_EOperation_eParameters_3 LT_SLASH_e_P_a_r_a_m_e_t_e_r_s_GT --118
		/.$BeginAction
					setResult(createEcoreClass(org.eclipse.emf.ecore.EcorePackage.Literals.EOPERATION__EPARAMETERS, getRhsSym(3)));
		  $EndAction
		./
EcoreClass_ecore_EOperation_eParameters ::= LT_e_P_a_r_a_m_e_t_e_r_s EcoreClass_ecore_EOperation_eParameters_8 GT EcoreClass_ecore_EOperation_eParameters_3 LT_SLASH_e_P_a_r_a_m_e_t_e_r_s_GT --119
		/.$BeginAction
					setResult(createEcoreClass(org.eclipse.emf.ecore.EcorePackage.Literals.EOPERATION__EPARAMETERS, getRhsSym(2), getRhsSym(4)));
		  $EndAction
		./
EcoreClass_ecore_EOperation_eParameters_3 ::= EcoreClass_ecore_EModelElement_eAnnotations --120
		/.$BeginAction
					setResult(SetAttributes.create(getRhsSym(1)));
		  $EndAction
		./
EcoreClass_ecore_EOperation_eParameters_3 ::= EcoreClass_ecore_ETypedElement_eGenericType --121
		/.$BeginAction
					setResult(SetAttributes.create(getRhsSym(1)));
		  $EndAction
		./
EcoreClass_ecore_EOperation_eParameters_3 ::= OtherElement --122
		/.$BeginAction
					setResult(SetAttributes.create(getRhsSym(1)));
		  $EndAction
		./
EcoreClass_ecore_EOperation_eParameters_3 ::= EcoreClass_ecore_EOperation_eParameters_3 EcoreClass_ecore_EModelElement_eAnnotations --123
		/.$BeginAction
					setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
		  $EndAction
		./
EcoreClass_ecore_EOperation_eParameters_3 ::= EcoreClass_ecore_EOperation_eParameters_3 EcoreClass_ecore_ETypedElement_eGenericType --124
		/.$BeginAction
					setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
		  $EndAction
		./
EcoreClass_ecore_EOperation_eParameters_3 ::= EcoreClass_ecore_EOperation_eParameters_3 OtherElement --125
		/.$BeginAction
					setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
		  $EndAction
		./
EcoreClass_ecore_EOperation_eParameters_8 ::= EcoreFeature_ecore_ENamedElement_name --126
		/.$BeginAction
					setResult(SetAttributes.create(getRhsSym(1)));
		  $EndAction
		./
EcoreClass_ecore_EOperation_eParameters_8 ::= EcoreFeature_ecore_ETypedElement_eType --127
		/.$BeginAction
					setResult(SetAttributes.create(getRhsSym(1)));
		  $EndAction
		./
EcoreClass_ecore_EOperation_eParameters_8 ::= OtherAttribute --128
		/.$BeginAction
					setResult(SetAttributes.create(getRhsSym(1)));
		  $EndAction
		./
EcoreClass_ecore_EOperation_eParameters_8 ::= XMLAttribute_xsi_type --129
		/.$BeginAction
					setResult(SetAttributes.create(getRhsSym(1)));
		  $EndAction
		./
EcoreClass_ecore_EOperation_eParameters_8 ::= EcoreClass_ecore_EOperation_eParameters_8 EcoreFeature_ecore_ENamedElement_name --130
		/.$BeginAction
					setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
		  $EndAction
		./
EcoreClass_ecore_EOperation_eParameters_8 ::= EcoreClass_ecore_EOperation_eParameters_8 EcoreFeature_ecore_ETypedElement_eType --131
		/.$BeginAction
					setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
		  $EndAction
		./
EcoreClass_ecore_EOperation_eParameters_8 ::= EcoreClass_ecore_EOperation_eParameters_8 OtherAttribute --132
		/.$BeginAction
					setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
		  $EndAction
		./
EcoreClass_ecore_EOperation_eParameters_8 ::= EcoreClass_ecore_EOperation_eParameters_8 XMLAttribute_xsi_type --133
		/.$BeginAction
					setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
		  $EndAction
		./

EcoreClass_ecore_EPackage_eClassifiers ::= LT_e_C_l_a_s_s_i_f_i_e_r_s SLASH_GT --134
		/.$BeginAction
					setResult(createEcoreClass(org.eclipse.emf.ecore.EcorePackage.Literals.EPACKAGE__ECLASSIFIERS));
		  $EndAction
		./
EcoreClass_ecore_EPackage_eClassifiers ::= LT_e_C_l_a_s_s_i_f_i_e_r_s EcoreClass_ecore_EPackage_eClassifiers_8 SLASH_GT --135
		/.$BeginAction
					setResult(createEcoreClass(org.eclipse.emf.ecore.EcorePackage.Literals.EPACKAGE__ECLASSIFIERS, getRhsSym(2)));
		  $EndAction
		./
EcoreClass_ecore_EPackage_eClassifiers ::= LT_e_C_l_a_s_s_i_f_i_e_r_s GT LT_SLASH_e_C_l_a_s_s_i_f_i_e_r_s_GT --136
		/.$BeginAction
					setResult(createEcoreClass(org.eclipse.emf.ecore.EcorePackage.Literals.EPACKAGE__ECLASSIFIERS));
		  $EndAction
		./
EcoreClass_ecore_EPackage_eClassifiers ::= LT_e_C_l_a_s_s_i_f_i_e_r_s EcoreClass_ecore_EPackage_eClassifiers_8 GT LT_SLASH_e_C_l_a_s_s_i_f_i_e_r_s_GT --137
		/.$BeginAction
					setResult(createEcoreClass(org.eclipse.emf.ecore.EcorePackage.Literals.EPACKAGE__ECLASSIFIERS, getRhsSym(2)));
		  $EndAction
		./
EcoreClass_ecore_EPackage_eClassifiers ::= LT_e_C_l_a_s_s_i_f_i_e_r_s GT EcoreClass_ecore_EPackage_eClassifiers_3 LT_SLASH_e_C_l_a_s_s_i_f_i_e_r_s_GT --138
		/.$BeginAction
					setResult(createEcoreClass(org.eclipse.emf.ecore.EcorePackage.Literals.EPACKAGE__ECLASSIFIERS, getRhsSym(3)));
		  $EndAction
		./
EcoreClass_ecore_EPackage_eClassifiers ::= LT_e_C_l_a_s_s_i_f_i_e_r_s EcoreClass_ecore_EPackage_eClassifiers_8 GT EcoreClass_ecore_EPackage_eClassifiers_3 LT_SLASH_e_C_l_a_s_s_i_f_i_e_r_s_GT --139
		/.$BeginAction
					setResult(createEcoreClass(org.eclipse.emf.ecore.EcorePackage.Literals.EPACKAGE__ECLASSIFIERS, getRhsSym(2), getRhsSym(4)));
		  $EndAction
		./
EcoreClass_ecore_EPackage_eClassifiers_3 ::= EcoreClass_ecore_EClass_eOperations --140
		/.$BeginAction
					setResult(SetAttributes.create(getRhsSym(1)));
		  $EndAction
		./
EcoreClass_ecore_EPackage_eClassifiers_3 ::= EcoreClass_ecore_EClass_eStructuralFeatures --141
		/.$BeginAction
					setResult(SetAttributes.create(getRhsSym(1)));
		  $EndAction
		./
EcoreClass_ecore_EPackage_eClassifiers_3 ::= EcoreClass_ecore_EModelElement_eAnnotations --142
		/.$BeginAction
					setResult(SetAttributes.create(getRhsSym(1)));
		  $EndAction
		./
EcoreClass_ecore_EPackage_eClassifiers_3 ::= OtherElement --143
		/.$BeginAction
					setResult(SetAttributes.create(getRhsSym(1)));
		  $EndAction
		./
EcoreClass_ecore_EPackage_eClassifiers_3 ::= EcoreClass_ecore_EPackage_eClassifiers_3 EcoreClass_ecore_EClass_eOperations --144
		/.$BeginAction
					setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
		  $EndAction
		./
EcoreClass_ecore_EPackage_eClassifiers_3 ::= EcoreClass_ecore_EPackage_eClassifiers_3 EcoreClass_ecore_EClass_eStructuralFeatures --145
		/.$BeginAction
					setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
		  $EndAction
		./
EcoreClass_ecore_EPackage_eClassifiers_3 ::= EcoreClass_ecore_EPackage_eClassifiers_3 EcoreClass_ecore_EModelElement_eAnnotations --146
		/.$BeginAction
					setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
		  $EndAction
		./
EcoreClass_ecore_EPackage_eClassifiers_3 ::= EcoreClass_ecore_EPackage_eClassifiers_3 OtherElement --147
		/.$BeginAction
					setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
		  $EndAction
		./
EcoreClass_ecore_EPackage_eClassifiers_8 ::= EcoreFeature_ecore_EClass_abstract --148
		/.$BeginAction
					setResult(SetAttributes.create(getRhsSym(1)));
		  $EndAction
		./
EcoreClass_ecore_EPackage_eClassifiers_8 ::= EcoreFeature_ecore_EClass_eSuperTypes --149
		/.$BeginAction
					setResult(SetAttributes.create(getRhsSym(1)));
		  $EndAction
		./
EcoreClass_ecore_EPackage_eClassifiers_8 ::= EcoreFeature_ecore_EDataType_serializable --150
		/.$BeginAction
					setResult(SetAttributes.create(getRhsSym(1)));
		  $EndAction
		./
EcoreClass_ecore_EPackage_eClassifiers_8 ::= EcoreFeature_ecore_ENamedElement_name --151
		/.$BeginAction
					setResult(SetAttributes.create(getRhsSym(1)));
		  $EndAction
		./
EcoreClass_ecore_EPackage_eClassifiers_8 ::= InstanceClassNameAttribute --152
		/.$BeginAction
					setResult(SetAttributes.create(getRhsSym(1)));
		  $EndAction
		./
EcoreClass_ecore_EPackage_eClassifiers_8 ::= OtherAttribute --153
		/.$BeginAction
					setResult(SetAttributes.create(getRhsSym(1)));
		  $EndAction
		./
EcoreClass_ecore_EPackage_eClassifiers_8 ::= XMLAttribute_xsi_type --154
		/.$BeginAction
					setResult(SetAttributes.create(getRhsSym(1)));
		  $EndAction
		./
EcoreClass_ecore_EPackage_eClassifiers_8 ::= EcoreClass_ecore_EPackage_eClassifiers_8 EcoreFeature_ecore_EClass_abstract --155
		/.$BeginAction
					setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
		  $EndAction
		./
EcoreClass_ecore_EPackage_eClassifiers_8 ::= EcoreClass_ecore_EPackage_eClassifiers_8 EcoreFeature_ecore_EClass_eSuperTypes --156
		/.$BeginAction
					setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
		  $EndAction
		./
EcoreClass_ecore_EPackage_eClassifiers_8 ::= EcoreClass_ecore_EPackage_eClassifiers_8 EcoreFeature_ecore_EDataType_serializable --157
		/.$BeginAction
					setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
		  $EndAction
		./
EcoreClass_ecore_EPackage_eClassifiers_8 ::= EcoreClass_ecore_EPackage_eClassifiers_8 EcoreFeature_ecore_ENamedElement_name --158
		/.$BeginAction
					setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
		  $EndAction
		./
EcoreClass_ecore_EPackage_eClassifiers_8 ::= EcoreClass_ecore_EPackage_eClassifiers_8 InstanceClassNameAttribute --159
		/.$BeginAction
					setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
		  $EndAction
		./
EcoreClass_ecore_EPackage_eClassifiers_8 ::= EcoreClass_ecore_EPackage_eClassifiers_8 OtherAttribute --160
		/.$BeginAction
					setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
		  $EndAction
		./
EcoreClass_ecore_EPackage_eClassifiers_8 ::= EcoreClass_ecore_EPackage_eClassifiers_8 XMLAttribute_xsi_type --161
		/.$BeginAction
					setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
		  $EndAction
		./

EcoreClass_ecore_EPackage_eSubpackages ::= LT_e_S_u_b_p_a_c_k_a_g_e_s SLASH_GT --162
		/.$BeginAction
					setResult(createEcoreClass(org.eclipse.emf.ecore.EcorePackage.Literals.EPACKAGE__ESUBPACKAGES));
		  $EndAction
		./
EcoreClass_ecore_EPackage_eSubpackages ::= LT_e_S_u_b_p_a_c_k_a_g_e_s EcoreClass_ecore_EPackage_eSubpackages_8 SLASH_GT --163
		/.$BeginAction
					setResult(createEcoreClass(org.eclipse.emf.ecore.EcorePackage.Literals.EPACKAGE__ESUBPACKAGES, getRhsSym(2)));
		  $EndAction
		./
EcoreClass_ecore_EPackage_eSubpackages ::= LT_e_S_u_b_p_a_c_k_a_g_e_s GT LT_SLASH_e_P_a_c_k_a_g_e_s_GT --164
		/.$BeginAction
					setResult(createEcoreClass(org.eclipse.emf.ecore.EcorePackage.Literals.EPACKAGE__ESUBPACKAGES));
		  $EndAction
		./
EcoreClass_ecore_EPackage_eSubpackages ::= LT_e_S_u_b_p_a_c_k_a_g_e_s EcoreClass_ecore_EPackage_eSubpackages_8 GT LT_SLASH_e_P_a_c_k_a_g_e_s_GT --165
		/.$BeginAction
					setResult(createEcoreClass(org.eclipse.emf.ecore.EcorePackage.Literals.EPACKAGE__ESUBPACKAGES, getRhsSym(2)));
		  $EndAction
		./
EcoreClass_ecore_EPackage_eSubpackages ::= LT_e_S_u_b_p_a_c_k_a_g_e_s GT EcoreClass_ecore_EPackage_eSubpackages_3 LT_SLASH_e_P_a_c_k_a_g_e_s_GT --166
		/.$BeginAction
					setResult(createEcoreClass(org.eclipse.emf.ecore.EcorePackage.Literals.EPACKAGE__ESUBPACKAGES, getRhsSym(3)));
		  $EndAction
		./
EcoreClass_ecore_EPackage_eSubpackages ::= LT_e_S_u_b_p_a_c_k_a_g_e_s EcoreClass_ecore_EPackage_eSubpackages_8 GT EcoreClass_ecore_EPackage_eSubpackages_3 LT_SLASH_e_P_a_c_k_a_g_e_s_GT --167
		/.$BeginAction
					setResult(createEcoreClass(org.eclipse.emf.ecore.EcorePackage.Literals.EPACKAGE__ESUBPACKAGES, getRhsSym(2), getRhsSym(4)));
		  $EndAction
		./
EcoreClass_ecore_EPackage_eSubpackages_3 ::= EcoreClass_ecore_EModelElement_eAnnotations --168
		/.$BeginAction
					setResult(SetAttributes.create(getRhsSym(1)));
		  $EndAction
		./
EcoreClass_ecore_EPackage_eSubpackages_3 ::= EcoreClass_ecore_EPackage_eClassifiers --169
		/.$BeginAction
					setResult(SetAttributes.create(getRhsSym(1)));
		  $EndAction
		./
EcoreClass_ecore_EPackage_eSubpackages_3 ::= EcoreClass_ecore_ETypedElement_eGenericType --170
		/.$BeginAction
					setResult(SetAttributes.create(getRhsSym(1)));
		  $EndAction
		./
EcoreClass_ecore_EPackage_eSubpackages_3 ::= OtherElement --171
		/.$BeginAction
					setResult(SetAttributes.create(getRhsSym(1)));
		  $EndAction
		./
EcoreClass_ecore_EPackage_eSubpackages_3 ::= EcoreClass_ecore_EPackage_eSubpackages_3 EcoreClass_ecore_EModelElement_eAnnotations --172
		/.$BeginAction
					setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
		  $EndAction
		./
EcoreClass_ecore_EPackage_eSubpackages_3 ::= EcoreClass_ecore_EPackage_eSubpackages_3 EcoreClass_ecore_EPackage_eClassifiers --173
		/.$BeginAction
					setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
		  $EndAction
		./
EcoreClass_ecore_EPackage_eSubpackages_3 ::= EcoreClass_ecore_EPackage_eSubpackages_3 EcoreClass_ecore_ETypedElement_eGenericType --174
		/.$BeginAction
					setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
		  $EndAction
		./
EcoreClass_ecore_EPackage_eSubpackages_3 ::= EcoreClass_ecore_EPackage_eSubpackages_3 OtherElement --175
		/.$BeginAction
					setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
		  $EndAction
		./
EcoreClass_ecore_EPackage_eSubpackages_8 ::= EcoreFeature_ecore_ENamedElement_name --176
		/.$BeginAction
					setResult(SetAttributes.create(getRhsSym(1)));
		  $EndAction
		./
EcoreClass_ecore_EPackage_eSubpackages_8 ::= EcoreFeature_ecore_EPackage_nsPrefix --177
		/.$BeginAction
					setResult(SetAttributes.create(getRhsSym(1)));
		  $EndAction
		./
EcoreClass_ecore_EPackage_eSubpackages_8 ::= EcoreFeature_ecore_EPackage_nsURI --178
		/.$BeginAction
					setResult(SetAttributes.create(getRhsSym(1)));
		  $EndAction
		./
EcoreClass_ecore_EPackage_eSubpackages_8 ::= OtherAttribute --179
		/.$BeginAction
					setResult(SetAttributes.create(getRhsSym(1)));
		  $EndAction
		./
EcoreClass_ecore_EPackage_eSubpackages_8 ::= XMLAttribute_xsi_type --180
		/.$BeginAction
					setResult(SetAttributes.create(getRhsSym(1)));
		  $EndAction
		./
EcoreClass_ecore_EPackage_eSubpackages_8 ::= EcoreClass_ecore_EPackage_eSubpackages_8 EcoreFeature_ecore_ENamedElement_name --181
		/.$BeginAction
					setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
		  $EndAction
		./
EcoreClass_ecore_EPackage_eSubpackages_8 ::= EcoreClass_ecore_EPackage_eSubpackages_8 EcoreFeature_ecore_EPackage_nsPrefix --182
		/.$BeginAction
					setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
		  $EndAction
		./
EcoreClass_ecore_EPackage_eSubpackages_8 ::= EcoreClass_ecore_EPackage_eSubpackages_8 EcoreFeature_ecore_EPackage_nsURI --183
		/.$BeginAction
					setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
		  $EndAction
		./
EcoreClass_ecore_EPackage_eSubpackages_8 ::= EcoreClass_ecore_EPackage_eSubpackages_8 OtherAttribute --184
		/.$BeginAction
					setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
		  $EndAction
		./
EcoreClass_ecore_EPackage_eSubpackages_8 ::= EcoreClass_ecore_EPackage_eSubpackages_8 XMLAttribute_xsi_type --185
		/.$BeginAction
					setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
		  $EndAction
		./

EcoreClass_ecore_ETypedElement_eGenericType ::= LT_e_G_e_n_e_r_i_c_T_y_p_e SLASH_GT --186
		/.$BeginAction
					setResult(createEcoreClass(org.eclipse.emf.ecore.EcorePackage.Literals.ETYPED_ELEMENT__EGENERIC_TYPE));
		  $EndAction
		./
EcoreClass_ecore_ETypedElement_eGenericType ::= LT_e_G_e_n_e_r_i_c_T_y_p_e EcoreClass_ecore_ETypedElement_eGenericType_8 SLASH_GT --187
		/.$BeginAction
					setResult(createEcoreClass(org.eclipse.emf.ecore.EcorePackage.Literals.ETYPED_ELEMENT__EGENERIC_TYPE, getRhsSym(2)));
		  $EndAction
		./
EcoreClass_ecore_ETypedElement_eGenericType ::= LT_e_G_e_n_e_r_i_c_T_y_p_e GT LT_SLASH_e_G_e_n_e_r_i_c_T_y_p_e_GT --188
		/.$BeginAction
					setResult(createEcoreClass(org.eclipse.emf.ecore.EcorePackage.Literals.ETYPED_ELEMENT__EGENERIC_TYPE));
		  $EndAction
		./
EcoreClass_ecore_ETypedElement_eGenericType ::= LT_e_G_e_n_e_r_i_c_T_y_p_e EcoreClass_ecore_ETypedElement_eGenericType_8 GT LT_SLASH_e_G_e_n_e_r_i_c_T_y_p_e_GT --189
		/.$BeginAction
					setResult(createEcoreClass(org.eclipse.emf.ecore.EcorePackage.Literals.ETYPED_ELEMENT__EGENERIC_TYPE, getRhsSym(2)));
		  $EndAction
		./
EcoreClass_ecore_ETypedElement_eGenericType ::= LT_e_G_e_n_e_r_i_c_T_y_p_e GT EcoreClass_ecore_ETypedElement_eGenericType_3 LT_SLASH_e_G_e_n_e_r_i_c_T_y_p_e_GT --190
		/.$BeginAction
					setResult(createEcoreClass(org.eclipse.emf.ecore.EcorePackage.Literals.ETYPED_ELEMENT__EGENERIC_TYPE, getRhsSym(3)));
		  $EndAction
		./
EcoreClass_ecore_ETypedElement_eGenericType ::= LT_e_G_e_n_e_r_i_c_T_y_p_e EcoreClass_ecore_ETypedElement_eGenericType_8 GT EcoreClass_ecore_ETypedElement_eGenericType_3 LT_SLASH_e_G_e_n_e_r_i_c_T_y_p_e_GT --191
		/.$BeginAction
					setResult(createEcoreClass(org.eclipse.emf.ecore.EcorePackage.Literals.ETYPED_ELEMENT__EGENERIC_TYPE, getRhsSym(2), getRhsSym(4)));
		  $EndAction
		./
EcoreClass_ecore_ETypedElement_eGenericType_3 ::= EcoreClass_ecore_EGenericType_eTypeArguments --192
		/.$BeginAction
					setResult(SetAttributes.create(getRhsSym(1)));
		  $EndAction
		./
EcoreClass_ecore_ETypedElement_eGenericType_3 ::= EcoreClass_ecore_EModelElement_eAnnotations --193
		/.$BeginAction
					setResult(SetAttributes.create(getRhsSym(1)));
		  $EndAction
		./
EcoreClass_ecore_ETypedElement_eGenericType_3 ::= OtherElement --194
		/.$BeginAction
					setResult(SetAttributes.create(getRhsSym(1)));
		  $EndAction
		./
EcoreClass_ecore_ETypedElement_eGenericType_3 ::= EcoreClass_ecore_ETypedElement_eGenericType_3 EcoreClass_ecore_EGenericType_eTypeArguments --195
		/.$BeginAction
					setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
		  $EndAction
		./
EcoreClass_ecore_ETypedElement_eGenericType_3 ::= EcoreClass_ecore_ETypedElement_eGenericType_3 EcoreClass_ecore_EModelElement_eAnnotations --196
		/.$BeginAction
					setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
		  $EndAction
		./
EcoreClass_ecore_ETypedElement_eGenericType_3 ::= EcoreClass_ecore_ETypedElement_eGenericType_3 OtherElement --197
		/.$BeginAction
					setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
		  $EndAction
		./
EcoreClass_ecore_ETypedElement_eGenericType_8 ::= EcoreFeature_ecore_EPackage_eClassifiers --198
		/.$BeginAction
					setResult(SetAttributes.create(getRhsSym(1)));
		  $EndAction
		./
EcoreClass_ecore_ETypedElement_eGenericType_8 ::= OtherAttribute --199
		/.$BeginAction
					setResult(SetAttributes.create(getRhsSym(1)));
		  $EndAction
		./
EcoreClass_ecore_ETypedElement_eGenericType_8 ::= XMLAttribute_xsi_type --200
		/.$BeginAction
					setResult(SetAttributes.create(getRhsSym(1)));
		  $EndAction
		./
EcoreClass_ecore_ETypedElement_eGenericType_8 ::= EcoreClass_ecore_ETypedElement_eGenericType_8 EcoreFeature_ecore_EPackage_eClassifiers --201
		/.$BeginAction
					setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
		  $EndAction
		./
EcoreClass_ecore_ETypedElement_eGenericType_8 ::= EcoreClass_ecore_ETypedElement_eGenericType_8 OtherAttribute --202
		/.$BeginAction
					setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
		  $EndAction
		./
EcoreClass_ecore_ETypedElement_eGenericType_8 ::= EcoreClass_ecore_ETypedElement_eGenericType_8 XMLAttribute_xsi_type --203
		/.$BeginAction
					setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
		  $EndAction
		./

EcoreFeature_ecore_EAnnotation_details ::= LT_d_e_t_a_i_l_s SLASH_GT --204
		/.$BeginAction
					setResult(createEcoreFeature(org.eclipse.emf.ecore.EcorePackage.Literals.EANNOTATION__DETAILS));
		  $EndAction
		./
EcoreFeature_ecore_EAnnotation_details ::= LT_d_e_t_a_i_l_s EcoreFeature_ecore_EAnnotation_details_8 SLASH_GT --205
		/.$BeginAction
					setResult(createEcoreFeature(org.eclipse.emf.ecore.EcorePackage.Literals.EANNOTATION__DETAILS));
		  $EndAction
		./
EcoreFeature_ecore_EAnnotation_details ::= LT_d_e_t_a_i_l_s GT LT_SLASH_d_e_t_a_i_l_s_GT --206
		/.$BeginAction
					setResult(createEcoreFeature(org.eclipse.emf.ecore.EcorePackage.Literals.EANNOTATION__DETAILS));
		  $EndAction
		./
EcoreFeature_ecore_EAnnotation_details ::= LT_d_e_t_a_i_l_s EcoreFeature_ecore_EAnnotation_details_8 GT LT_SLASH_d_e_t_a_i_l_s_GT --207
		/.$BeginAction
					setResult(createEcoreFeature(org.eclipse.emf.ecore.EcorePackage.Literals.EANNOTATION__DETAILS));
		  $EndAction
		./
EcoreFeature_ecore_EAnnotation_details ::= LT_d_e_t_a_i_l_s GT EcoreFeature_ecore_EAnnotation_details_3 LT_SLASH_d_e_t_a_i_l_s_GT --208
		/.$BeginAction
					setResult(createEcoreFeature(org.eclipse.emf.ecore.EcorePackage.Literals.EANNOTATION__DETAILS));
		  $EndAction
		./
EcoreFeature_ecore_EAnnotation_details ::= LT_d_e_t_a_i_l_s EcoreFeature_ecore_EAnnotation_details_8 GT EcoreFeature_ecore_EAnnotation_details_3 LT_SLASH_d_e_t_a_i_l_s_GT --209
		/.$BeginAction
					setResult(createEcoreFeature(org.eclipse.emf.ecore.EcorePackage.Literals.EANNOTATION__DETAILS));
		  $EndAction
		./
EcoreFeature_ecore_EAnnotation_details_3 ::= EcoreClass_ecore_EModelElement_eAnnotations --210
		/.$BeginAction
					setResult(SetMapAttributes.create(getRhsSym(1)));
		  $EndAction
		./
EcoreFeature_ecore_EAnnotation_details_3 ::= OtherElement --211
		/.$BeginAction
					setResult(SetMapAttributes.create(getRhsSym(1)));
		  $EndAction
		./
EcoreFeature_ecore_EAnnotation_details_3 ::= EcoreFeature_ecore_EAnnotation_details_3 EcoreClass_ecore_EModelElement_eAnnotations --212
		/.$BeginAction
					setResult(SetMapAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
		  $EndAction
		./
EcoreFeature_ecore_EAnnotation_details_3 ::= EcoreFeature_ecore_EAnnotation_details_3 OtherElement --213
		/.$BeginAction
					setResult(SetMapAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
		  $EndAction
		./
EcoreFeature_ecore_EAnnotation_details_8 ::= EcoreFeature_ecore_EStringToStringMapEntry_key --214
		/.$BeginAction
					setResult(SetMapAttributes.create(getRhsSym(1)));
		  $EndAction
		./
EcoreFeature_ecore_EAnnotation_details_8 ::= EcoreFeature_ecore_EStringToStringMapEntry_value --215
		/.$BeginAction
					setResult(SetMapAttributes.create(getRhsSym(1)));
		  $EndAction
		./
EcoreFeature_ecore_EAnnotation_details_8 ::= OtherAttribute --216
		/.$BeginAction
					setResult(SetMapAttributes.create(getRhsSym(1)));
		  $EndAction
		./
EcoreFeature_ecore_EAnnotation_details_8 ::= XMLAttribute_xsi_type --217
		/.$BeginAction
					setResult(SetMapAttributes.create(getRhsSym(1)));
		  $EndAction
		./
EcoreFeature_ecore_EAnnotation_details_8 ::= EcoreFeature_ecore_EAnnotation_details_8 EcoreFeature_ecore_EStringToStringMapEntry_key --218
		/.$BeginAction
					setResult(SetMapAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
		  $EndAction
		./
EcoreFeature_ecore_EAnnotation_details_8 ::= EcoreFeature_ecore_EAnnotation_details_8 EcoreFeature_ecore_EStringToStringMapEntry_value --219
		/.$BeginAction
					setResult(SetMapAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
		  $EndAction
		./
EcoreFeature_ecore_EAnnotation_details_8 ::= EcoreFeature_ecore_EAnnotation_details_8 OtherAttribute --220
		/.$BeginAction
					setResult(SetMapAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
		  $EndAction
		./
EcoreFeature_ecore_EAnnotation_details_8 ::= EcoreFeature_ecore_EAnnotation_details_8 XMLAttribute_xsi_type --221
		/.$BeginAction
					setResult(SetMapAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
		  $EndAction
		./

EcoreFeature_ecore_EAnnotation_source ::= source EQ Terminal_String --222
		/.$BeginAction
					setResult(createEcoreFeature(org.eclipse.emf.ecore.EcorePackage.Literals.EANNOTATION__SOURCE));
		  $EndAction
		./

EcoreFeature_ecore_EClass_abstract ::= abstract EQ Terminal_String --223
		/.$BeginAction
					setResult(createEcoreFeature(org.eclipse.emf.ecore.EcorePackage.Literals.ECLASS__ABSTRACT));
		  $EndAction
		./

EcoreFeature_ecore_EClass_eSuperTypes ::= eSuperTypes EQ Terminal_String --224
		/.$BeginAction
					setResult(createEcoreFeature(org.eclipse.emf.ecore.EcorePackage.Literals.ECLASS__ESUPER_TYPES));
		  $EndAction
		./

EcoreFeature_ecore_EDataType_serializable ::= serializable EQ Terminal_String --225
		/.$BeginAction
					setResult(createEcoreFeature(org.eclipse.emf.ecore.EcorePackage.Literals.EDATA_TYPE__SERIALIZABLE));
		  $EndAction
		./

EcoreFeature_ecore_ENamedElement_name ::= name EQ Terminal_String --226
		/.$BeginAction
					setResult(createEcoreFeature(org.eclipse.emf.ecore.EcorePackage.Literals.ENAMED_ELEMENT__NAME));
		  $EndAction
		./

EcoreFeature_ecore_EOperation_eExceptions ::= eExceptions EQ Terminal_String --227
		/.$BeginAction
					setResult(createEcoreFeature(org.eclipse.emf.ecore.EcorePackage.Literals.EOPERATION__EEXCEPTIONS));
		  $EndAction
		./

EcoreFeature_ecore_EPackage_eClassifiers ::= eClassifiers EQ Terminal_String --228
		/.$BeginAction
					setResult(createEcoreFeature(org.eclipse.emf.ecore.EcorePackage.Literals.EPACKAGE__ECLASSIFIERS));
		  $EndAction
		./

EcoreFeature_ecore_EPackage_nsPrefix ::= nsPrefix EQ Terminal_String --229
		/.$BeginAction
					setResult(createEcoreFeature(org.eclipse.emf.ecore.EcorePackage.Literals.EPACKAGE__NS_PREFIX));
		  $EndAction
		./

EcoreFeature_ecore_EPackage_nsURI ::= nsURI EQ Terminal_String --230
		/.$BeginAction
					setResult(createEcoreFeature(org.eclipse.emf.ecore.EcorePackage.Literals.EPACKAGE__NS_URI));
		  $EndAction
		./

EcoreFeature_ecore_EReference_containment ::= containment EQ Terminal_String --231
		/.$BeginAction
					setResult(createEcoreFeature(org.eclipse.emf.ecore.EcorePackage.Literals.EREFERENCE__CONTAINMENT));
		  $EndAction
		./

EcoreFeature_ecore_EReference_eOpposite ::= eOpposite EQ Terminal_String --232
		/.$BeginAction
					setResult(createEcoreFeature(org.eclipse.emf.ecore.EcorePackage.Literals.EREFERENCE__EOPPOSITE));
		  $EndAction
		./

EcoreFeature_ecore_EReference_resolveProxies ::= resolveProxies EQ Terminal_String --233
		/.$BeginAction
					setResult(createEcoreFeature(org.eclipse.emf.ecore.EcorePackage.Literals.EREFERENCE__RESOLVE_PROXIES));
		  $EndAction
		./

EcoreFeature_ecore_EStringToStringMapEntry_key ::= key EQ Terminal_String --234
		/.$BeginAction
					setResult(createEcoreFeature(org.eclipse.emf.ecore.EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY__KEY));
		  $EndAction
		./

EcoreFeature_ecore_EStringToStringMapEntry_value ::= value EQ Terminal_String --235
		/.$BeginAction
					setResult(createEcoreFeature(org.eclipse.emf.ecore.EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY__VALUE));
		  $EndAction
		./

EcoreFeature_ecore_EStructuralFeature_changeable ::= changeable EQ Terminal_String --236
		/.$BeginAction
					setResult(createEcoreFeature(org.eclipse.emf.ecore.EcorePackage.Literals.ESTRUCTURAL_FEATURE__CHANGEABLE));
		  $EndAction
		./

EcoreFeature_ecore_EStructuralFeature_defaultValueLiteral ::= defaultValueLiteral EQ Terminal_String --237
		/.$BeginAction
					setResult(createEcoreFeature(org.eclipse.emf.ecore.EcorePackage.Literals.ESTRUCTURAL_FEATURE__DEFAULT_VALUE_LITERAL));
		  $EndAction
		./

EcoreFeature_ecore_EStructuralFeature_derived ::= derived EQ Terminal_String --238
		/.$BeginAction
					setResult(createEcoreFeature(org.eclipse.emf.ecore.EcorePackage.Literals.ESTRUCTURAL_FEATURE__DERIVED));
		  $EndAction
		./

EcoreFeature_ecore_EStructuralFeature_transient ::= transient EQ Terminal_String --239
		/.$BeginAction
					setResult(createEcoreFeature(org.eclipse.emf.ecore.EcorePackage.Literals.ESTRUCTURAL_FEATURE__TRANSIENT));
		  $EndAction
		./

EcoreFeature_ecore_EStructuralFeature_unsettable ::= unsettable EQ Terminal_String --240
		/.$BeginAction
					setResult(createEcoreFeature(org.eclipse.emf.ecore.EcorePackage.Literals.ESTRUCTURAL_FEATURE__UNSETTABLE));
		  $EndAction
		./

EcoreFeature_ecore_EStructuralFeature_volatile ::= volatile EQ Terminal_String --241
		/.$BeginAction
					setResult(createEcoreFeature(org.eclipse.emf.ecore.EcorePackage.Literals.ESTRUCTURAL_FEATURE__VOLATILE));
		  $EndAction
		./

EcoreFeature_ecore_ETypedElement_eType ::= eType EQ Terminal_String --242
		/.$BeginAction
					setResult(createEcoreFeature(org.eclipse.emf.ecore.EcorePackage.Literals.ETYPED_ELEMENT__ETYPE));
		  $EndAction
		./

EcoreFeature_ecore_ETypedElement_lowerBound ::= lowerBound EQ Terminal_String --243
		/.$BeginAction
					setResult(createEcoreFeature(org.eclipse.emf.ecore.EcorePackage.Literals.ETYPED_ELEMENT__LOWER_BOUND));
		  $EndAction
		./

EcoreFeature_ecore_ETypedElement_ordered ::= ordered EQ Terminal_String --244
		/.$BeginAction
					setResult(createEcoreFeature(org.eclipse.emf.ecore.EcorePackage.Literals.ETYPED_ELEMENT__ORDERED));
		  $EndAction
		./

EcoreFeature_ecore_ETypedElement_upperBound ::= upperBound EQ Terminal_String --245
		/.$BeginAction
					setResult(createEcoreFeature(org.eclipse.emf.ecore.EcorePackage.Literals.ETYPED_ELEMENT__UPPER_BOUND));
		  $EndAction
		./

EcoreRoot_ecore_EPackage ::= LT_e_c_o_r_e_COLON_E_P_a_c_k_a_g_e SLASH_GT --246
		/.$BeginAction
					setResult(createEcoreRoot(org.eclipse.emf.ecore.EcorePackage.Literals.EPACKAGE));
		  $EndAction
		./
EcoreRoot_ecore_EPackage ::= LT_e_c_o_r_e_COLON_E_P_a_c_k_a_g_e EcoreRoot_ecore_EPackage_8 SLASH_GT --247
		/.$BeginAction
					setResult(createEcoreRoot(org.eclipse.emf.ecore.EcorePackage.Literals.EPACKAGE, getRhsSym(2)));
		  $EndAction
		./
EcoreRoot_ecore_EPackage ::= LT_e_c_o_r_e_COLON_E_P_a_c_k_a_g_e GT LT_SLASH_e_c_o_r_e_COLON_E_P_a_c_k_a_g_e_GT --248
		/.$BeginAction
					setResult(createEcoreRoot(org.eclipse.emf.ecore.EcorePackage.Literals.EPACKAGE));
		  $EndAction
		./
EcoreRoot_ecore_EPackage ::= LT_e_c_o_r_e_COLON_E_P_a_c_k_a_g_e EcoreRoot_ecore_EPackage_8 GT LT_SLASH_e_c_o_r_e_COLON_E_P_a_c_k_a_g_e_GT --249
		/.$BeginAction
					setResult(createEcoreRoot(org.eclipse.emf.ecore.EcorePackage.Literals.EPACKAGE, getRhsSym(2)));
		  $EndAction
		./
EcoreRoot_ecore_EPackage ::= LT_e_c_o_r_e_COLON_E_P_a_c_k_a_g_e GT EcoreRoot_ecore_EPackage_3 LT_SLASH_e_c_o_r_e_COLON_E_P_a_c_k_a_g_e_GT --250
		/.$BeginAction
					setResult(createEcoreRoot(org.eclipse.emf.ecore.EcorePackage.Literals.EPACKAGE, getRhsSym(3)));
		  $EndAction
		./
EcoreRoot_ecore_EPackage ::= LT_e_c_o_r_e_COLON_E_P_a_c_k_a_g_e EcoreRoot_ecore_EPackage_8 GT EcoreRoot_ecore_EPackage_3 LT_SLASH_e_c_o_r_e_COLON_E_P_a_c_k_a_g_e_GT --251
		/.$BeginAction
					setResult(createEcoreRoot(org.eclipse.emf.ecore.EcorePackage.Literals.EPACKAGE, getRhsSym(2), getRhsSym(4)));
		  $EndAction
		./
EcoreRoot_ecore_EPackage_3 ::= EcoreClass_ecore_EModelElement_eAnnotations --252
		/.$BeginAction
					setResult(SetAttributes.create(getRhsSym(1)));
		  $EndAction
		./
EcoreRoot_ecore_EPackage_3 ::= EcoreClass_ecore_EPackage_eClassifiers --253
		/.$BeginAction
					setResult(SetAttributes.create(getRhsSym(1)));
		  $EndAction
		./
EcoreRoot_ecore_EPackage_3 ::= OtherElement --254
		/.$BeginAction
					setResult(SetAttributes.create(getRhsSym(1)));
		  $EndAction
		./
EcoreRoot_ecore_EPackage_3 ::= EcoreRoot_ecore_EPackage_3 EcoreClass_ecore_EModelElement_eAnnotations --255
		/.$BeginAction
					setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
		  $EndAction
		./
EcoreRoot_ecore_EPackage_3 ::= EcoreRoot_ecore_EPackage_3 EcoreClass_ecore_EPackage_eClassifiers --256
		/.$BeginAction
					setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
		  $EndAction
		./
EcoreRoot_ecore_EPackage_3 ::= EcoreRoot_ecore_EPackage_3 OtherElement --257
		/.$BeginAction
					setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
		  $EndAction
		./
EcoreRoot_ecore_EPackage_8 ::= EcoreFeature_ecore_ENamedElement_name --258
		/.$BeginAction
					setResult(SetAttributes.create(getRhsSym(1)));
		  $EndAction
		./
EcoreRoot_ecore_EPackage_8 ::= EcoreFeature_ecore_EPackage_nsPrefix --259
		/.$BeginAction
					setResult(SetAttributes.create(getRhsSym(1)));
		  $EndAction
		./
EcoreRoot_ecore_EPackage_8 ::= EcoreFeature_ecore_EPackage_nsURI --260
		/.$BeginAction
					setResult(SetAttributes.create(getRhsSym(1)));
		  $EndAction
		./
EcoreRoot_ecore_EPackage_8 ::= OtherAttribute --261
		/.$BeginAction
					setResult(SetAttributes.create(getRhsSym(1)));
		  $EndAction
		./
EcoreRoot_ecore_EPackage_8 ::= XMLAttribute_xmi_version --262
		/.$BeginAction
					setResult(SetAttributes.create(getRhsSym(1)));
		  $EndAction
		./
EcoreRoot_ecore_EPackage_8 ::= XMLAttribute_xmlns_ --263
		/.$BeginAction
					setResult(SetAttributes.create(getRhsSym(1)));
		  $EndAction
		./
EcoreRoot_ecore_EPackage_8 ::= XMLAttribute_xsi_type --264
		/.$BeginAction
					setResult(SetAttributes.create(getRhsSym(1)));
		  $EndAction
		./
EcoreRoot_ecore_EPackage_8 ::= EcoreRoot_ecore_EPackage_8 EcoreFeature_ecore_ENamedElement_name --265
		/.$BeginAction
					setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
		  $EndAction
		./
EcoreRoot_ecore_EPackage_8 ::= EcoreRoot_ecore_EPackage_8 EcoreFeature_ecore_EPackage_nsPrefix --266
		/.$BeginAction
					setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
		  $EndAction
		./
EcoreRoot_ecore_EPackage_8 ::= EcoreRoot_ecore_EPackage_8 EcoreFeature_ecore_EPackage_nsURI --267
		/.$BeginAction
					setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
		  $EndAction
		./
EcoreRoot_ecore_EPackage_8 ::= EcoreRoot_ecore_EPackage_8 OtherAttribute --268
		/.$BeginAction
					setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
		  $EndAction
		./
EcoreRoot_ecore_EPackage_8 ::= EcoreRoot_ecore_EPackage_8 XMLAttribute_xmi_version --269
		/.$BeginAction
					setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
		  $EndAction
		./
EcoreRoot_ecore_EPackage_8 ::= EcoreRoot_ecore_EPackage_8 XMLAttribute_xmlns_ --270
		/.$BeginAction
					setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
		  $EndAction
		./
EcoreRoot_ecore_EPackage_8 ::= EcoreRoot_ecore_EPackage_8 XMLAttribute_xsi_type --271
		/.$BeginAction
					setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
		  $EndAction
		./

InstanceClassNameAttribute ::= instanceClassName EQ Terminal_String --272
		/.$BeginAction
					setResult(createEObject());
		  $EndAction
		./

IntegerAttribute ::= EcoreFeature_ecore_ETypedElement_lowerBound --273
		/.$BeginAction
					setResult(createEObject());
		  $EndAction
		./
IntegerAttribute ::= EcoreFeature_ecore_ETypedElement_upperBound --274
		/.$BeginAction
					setResult(createEObject());
		  $EndAction
		./

OtherAttribute ::= IDENTIFIER EQ Terminal_String --275
		/.$BeginAction
					setResult(createEObject());
		  $EndAction
		./

OtherElement ::= LT Terminal_Identifier SLASH_GT --276
		/.$BeginAction
					setResult(createEObject());
		  $EndAction
		./
OtherElement ::= LT Terminal_Identifier OtherElement_6 SLASH_GT --277
		/.$BeginAction
					setResult(createEObject());
		  $EndAction
		./
OtherElement ::= LT Terminal_Identifier GT LT_SLASH Terminal_Identifier GT --278
		/.$BeginAction
					setResult(createEObject());
		  $EndAction
		./
OtherElement ::= LT Terminal_Identifier OtherElement_6 GT LT_SLASH Terminal_Identifier GT --279
		/.$BeginAction
					setResult(createEObject());
		  $EndAction
		./
OtherElement ::= LT Terminal_Identifier GT OtherElement_2 LT_SLASH Terminal_Identifier GT --280
		/.$BeginAction
					setResult(createEObject());
		  $EndAction
		./
OtherElement ::= LT Terminal_Identifier OtherElement_6 GT OtherElement_2 LT_SLASH Terminal_Identifier GT --281
		/.$BeginAction
					setResult(createEObject());
		  $EndAction
		./
OtherElement_2 ::= XmlElement --282
		/.$BeginAction
					setResult(SetAttributes.create(getRhsSym(1)));
		  $EndAction
		./
OtherElement_2 ::= OtherElement_2 XmlElement --283
		/.$BeginAction
					setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
		  $EndAction
		./
OtherElement_6 ::= XmlAttribute --284
		/.$BeginAction
					setResult(SetAttributes.create(getRhsSym(1)));
		  $EndAction
		./
OtherElement_6 ::= OtherElement_6 XmlAttribute --285
		/.$BeginAction
					setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
		  $EndAction
		./

StringAttribute ::= EcoreFeature_ecore_EAnnotation_source --286
		/.$BeginAction
					setResult(createEObject());
		  $EndAction
		./
StringAttribute ::= EcoreFeature_ecore_EClass_eSuperTypes --287
		/.$BeginAction
					setResult(createEObject());
		  $EndAction
		./
StringAttribute ::= EcoreFeature_ecore_ENamedElement_name --288
		/.$BeginAction
					setResult(createEObject());
		  $EndAction
		./
StringAttribute ::= EcoreFeature_ecore_EOperation_eExceptions --289
		/.$BeginAction
					setResult(createEObject());
		  $EndAction
		./
StringAttribute ::= EcoreFeature_ecore_EPackage_eClassifiers --290
		/.$BeginAction
					setResult(createEObject());
		  $EndAction
		./
StringAttribute ::= EcoreFeature_ecore_EPackage_nsPrefix --291
		/.$BeginAction
					setResult(createEObject());
		  $EndAction
		./
StringAttribute ::= EcoreFeature_ecore_EPackage_nsURI --292
		/.$BeginAction
					setResult(createEObject());
		  $EndAction
		./
StringAttribute ::= EcoreFeature_ecore_EReference_eOpposite --293
		/.$BeginAction
					setResult(createEObject());
		  $EndAction
		./
StringAttribute ::= EcoreFeature_ecore_EStringToStringMapEntry_key --294
		/.$BeginAction
					setResult(createEObject());
		  $EndAction
		./
StringAttribute ::= EcoreFeature_ecore_EStringToStringMapEntry_value --295
		/.$BeginAction
					setResult(createEObject());
		  $EndAction
		./
StringAttribute ::= EcoreFeature_ecore_ETypedElement_eType --296
		/.$BeginAction
					setResult(createEObject());
		  $EndAction
		./
StringAttribute ::= InstanceClassNameAttribute --297
		/.$BeginAction
					setResult(createEObject());
		  $EndAction
		./
StringAttribute ::= XMLAttribute_xmi_version --298
		/.$BeginAction
					setResult(createEObject());
		  $EndAction
		./
StringAttribute ::= XMLAttribute_xmlns_ --299
		/.$BeginAction
					setResult(createEObject());
		  $EndAction
		./
StringAttribute ::= XMLAttribute_xsi_type --300
		/.$BeginAction
					setResult(createEObject());
		  $EndAction
		./

Terminal_Identifier ::= abstract --301
		/.$BeginAction
					setResult(getRhsTokenText(1));
		  $EndAction
		./
Terminal_Identifier ::= changeable --302
		/.$BeginAction
					setResult(getRhsTokenText(1));
		  $EndAction
		./
Terminal_Identifier ::= containment --303
		/.$BeginAction
					setResult(getRhsTokenText(1));
		  $EndAction
		./
Terminal_Identifier ::= defaultValueLiteral --304
		/.$BeginAction
					setResult(getRhsTokenText(1));
		  $EndAction
		./
Terminal_Identifier ::= derived --305
		/.$BeginAction
					setResult(getRhsTokenText(1));
		  $EndAction
		./
Terminal_Identifier ::= eClassifier --306
		/.$BeginAction
					setResult(getRhsTokenText(1));
		  $EndAction
		./
Terminal_Identifier ::= eExceptions --307
		/.$BeginAction
					setResult(getRhsTokenText(1));
		  $EndAction
		./
Terminal_Identifier ::= eOpposite --308
		/.$BeginAction
					setResult(getRhsTokenText(1));
		  $EndAction
		./
Terminal_Identifier ::= eSuperTypes --309
		/.$BeginAction
					setResult(getRhsTokenText(1));
		  $EndAction
		./
Terminal_Identifier ::= eType --310
		/.$BeginAction
					setResult(getRhsTokenText(1));
		  $EndAction
		./
Terminal_Identifier ::= encoding --311
		/.$BeginAction
					setResult(getRhsTokenText(1));
		  $EndAction
		./
Terminal_Identifier ::= instanceClassName --312
		/.$BeginAction
					setResult(getRhsTokenText(1));
		  $EndAction
		./
Terminal_Identifier ::= key --313
		/.$BeginAction
					setResult(getRhsTokenText(1));
		  $EndAction
		./
Terminal_Identifier ::= lowerBound --314
		/.$BeginAction
					setResult(getRhsTokenText(1));
		  $EndAction
		./
Terminal_Identifier ::= name --315
		/.$BeginAction
					setResult(getRhsTokenText(1));
		  $EndAction
		./
Terminal_Identifier ::= nsPrefix --316
		/.$BeginAction
					setResult(getRhsTokenText(1));
		  $EndAction
		./
Terminal_Identifier ::= nsURI --317
		/.$BeginAction
					setResult(getRhsTokenText(1));
		  $EndAction
		./
Terminal_Identifier ::= ordered --318
		/.$BeginAction
					setResult(getRhsTokenText(1));
		  $EndAction
		./
Terminal_Identifier ::= resolveProxies --319
		/.$BeginAction
					setResult(getRhsTokenText(1));
		  $EndAction
		./
Terminal_Identifier ::= serializable --320
		/.$BeginAction
					setResult(getRhsTokenText(1));
		  $EndAction
		./
Terminal_Identifier ::= source --321
		/.$BeginAction
					setResult(getRhsTokenText(1));
		  $EndAction
		./
Terminal_Identifier ::= transient --322
		/.$BeginAction
					setResult(getRhsTokenText(1));
		  $EndAction
		./
Terminal_Identifier ::= unsettable --323
		/.$BeginAction
					setResult(getRhsTokenText(1));
		  $EndAction
		./
Terminal_Identifier ::= upperBound --324
		/.$BeginAction
					setResult(getRhsTokenText(1));
		  $EndAction
		./
Terminal_Identifier ::= value --325
		/.$BeginAction
					setResult(getRhsTokenText(1));
		  $EndAction
		./
Terminal_Identifier ::= version --326
		/.$BeginAction
					setResult(getRhsTokenText(1));
		  $EndAction
		./
Terminal_Identifier ::= volatile --327
		/.$BeginAction
					setResult(getRhsTokenText(1));
		  $EndAction
		./
Terminal_Identifier ::= xmi --328
		/.$BeginAction
					setResult(getRhsTokenText(1));
		  $EndAction
		./
Terminal_Identifier ::= xsi --329
		/.$BeginAction
					setResult(getRhsTokenText(1));
		  $EndAction
		./
Terminal_Identifier ::= IDENTIFIER --330
		/.$BeginAction
					setResult(getRhsTokenText(1));
		  $EndAction
		./

Terminal_String ::= STRING --331
		/.$BeginAction
					setResult(getRhsTokenText(1));
		  $EndAction
		./

XMLAttribute_xmi_version ::= xmi COLON version EQ Terminal_String --332
		/.$BeginAction
					setResult(createXMIAttribute("version"));
		  $EndAction
		./

XMLAttribute_xmlns_ ::= xmlns COLON Terminal_Identifier EQ Terminal_String --333
		/.$BeginAction
					setResult(createXMLNSAttribute());
		  $EndAction
		./

XMLAttribute_xsi_type ::= xsi COLON type EQ Terminal_String --334
		/.$BeginAction
					setResult(createXSIAttribute("type"));
		  $EndAction
		./

XMLDocument_ecore_EPackage ::= LT_QUERY_x_m_l QUERY_GT EcoreRoot_ecore_EPackage --335
		/.$BeginAction
					setResult(createXMLDocument(getRhsSym(3)));
		  $EndAction
		./
XMLDocument_ecore_EPackage ::= LT_QUERY_x_m_l QUERY_GT LT_x_m_i_COLON_X_M_I SLASH_GT --336
		/.$BeginAction
					setResult(createXMLDocument());
		  $EndAction
		./
XMLDocument_ecore_EPackage ::= LT_QUERY_x_m_l QUERY_GT LT_x_m_i_COLON_X_M_I XMLDocument_ecore_EPackage_7 SLASH_GT --337
		/.$BeginAction
					setResult(createXMLDocument(getRhsSym(4)));
		  $EndAction
		./
XMLDocument_ecore_EPackage ::= LT_QUERY_x_m_l QUERY_GT LT_x_m_i_COLON_X_M_I GT LT_SLASH_x_m_i_COLON_X_M_I_GT --338
		/.$BeginAction
					setResult(createXMLDocument());
		  $EndAction
		./
XMLDocument_ecore_EPackage ::= LT_QUERY_x_m_l QUERY_GT LT_x_m_i_COLON_X_M_I XMLDocument_ecore_EPackage_7 GT LT_SLASH_x_m_i_COLON_X_M_I_GT --339
		/.$BeginAction
					setResult(createXMLDocument(getRhsSym(4)));
		  $EndAction
		./
XMLDocument_ecore_EPackage ::= LT_QUERY_x_m_l QUERY_GT LT_x_m_i_COLON_X_M_I GT XMLDocument_ecore_EPackage_2 LT_SLASH_x_m_i_COLON_X_M_I_GT --340
		/.$BeginAction
					setResult(createXMLDocument(getRhsSym(5)));
		  $EndAction
		./
XMLDocument_ecore_EPackage ::= LT_QUERY_x_m_l encoding EQ Terminal_String QUERY_GT EcoreRoot_ecore_EPackage --341
		/.$BeginAction
					setResult(createXMLDocument(getRhsSym(4), getRhsSym(6)));
		  $EndAction
		./
XMLDocument_ecore_EPackage ::= LT_QUERY_x_m_l version EQ Terminal_String QUERY_GT EcoreRoot_ecore_EPackage --342
		/.$BeginAction
					setResult(createXMLDocument(getRhsSym(4), getRhsSym(6)));
		  $EndAction
		./
XMLDocument_ecore_EPackage ::= LT_QUERY_x_m_l QUERY_GT LT_x_m_i_COLON_X_M_I XMLDocument_ecore_EPackage_7 GT XMLDocument_ecore_EPackage_2 LT_SLASH_x_m_i_COLON_X_M_I_GT --343
		/.$BeginAction
					setResult(createXMLDocument(getRhsSym(4), getRhsSym(6)));
		  $EndAction
		./
XMLDocument_ecore_EPackage ::= LT_QUERY_x_m_l encoding EQ Terminal_String QUERY_GT LT_x_m_i_COLON_X_M_I SLASH_GT --344
		/.$BeginAction
					setResult(createXMLDocument(getRhsSym(4)));
		  $EndAction
		./
XMLDocument_ecore_EPackage ::= LT_QUERY_x_m_l version EQ Terminal_String QUERY_GT LT_x_m_i_COLON_X_M_I SLASH_GT --345
		/.$BeginAction
					setResult(createXMLDocument(getRhsSym(4)));
		  $EndAction
		./
XMLDocument_ecore_EPackage ::= LT_QUERY_x_m_l encoding EQ Terminal_String QUERY_GT LT_x_m_i_COLON_X_M_I XMLDocument_ecore_EPackage_7 SLASH_GT --346
		/.$BeginAction
					setResult(createXMLDocument(getRhsSym(4), getRhsSym(7)));
		  $EndAction
		./
XMLDocument_ecore_EPackage ::= LT_QUERY_x_m_l encoding EQ Terminal_String QUERY_GT LT_x_m_i_COLON_X_M_I GT LT_SLASH_x_m_i_COLON_X_M_I_GT --347
		/.$BeginAction
					setResult(createXMLDocument(getRhsSym(4)));
		  $EndAction
		./
XMLDocument_ecore_EPackage ::= LT_QUERY_x_m_l version EQ Terminal_String QUERY_GT LT_x_m_i_COLON_X_M_I XMLDocument_ecore_EPackage_7 SLASH_GT --348
		/.$BeginAction
					setResult(createXMLDocument(getRhsSym(4), getRhsSym(7)));
		  $EndAction
		./
XMLDocument_ecore_EPackage ::= LT_QUERY_x_m_l version EQ Terminal_String QUERY_GT LT_x_m_i_COLON_X_M_I GT LT_SLASH_x_m_i_COLON_X_M_I_GT --349
		/.$BeginAction
					setResult(createXMLDocument(getRhsSym(4)));
		  $EndAction
		./
XMLDocument_ecore_EPackage ::= LT_QUERY_x_m_l encoding EQ Terminal_String QUERY_GT LT_x_m_i_COLON_X_M_I XMLDocument_ecore_EPackage_7 GT LT_SLASH_x_m_i_COLON_X_M_I_GT --350
		/.$BeginAction
					setResult(createXMLDocument(getRhsSym(4), getRhsSym(7)));
		  $EndAction
		./
XMLDocument_ecore_EPackage ::= LT_QUERY_x_m_l encoding EQ Terminal_String QUERY_GT LT_x_m_i_COLON_X_M_I GT XMLDocument_ecore_EPackage_2 LT_SLASH_x_m_i_COLON_X_M_I_GT --351
		/.$BeginAction
					setResult(createXMLDocument(getRhsSym(4), getRhsSym(8)));
		  $EndAction
		./
XMLDocument_ecore_EPackage ::= LT_QUERY_x_m_l version EQ Terminal_String QUERY_GT LT_x_m_i_COLON_X_M_I XMLDocument_ecore_EPackage_7 GT LT_SLASH_x_m_i_COLON_X_M_I_GT --352
		/.$BeginAction
					setResult(createXMLDocument(getRhsSym(4), getRhsSym(7)));
		  $EndAction
		./
XMLDocument_ecore_EPackage ::= LT_QUERY_x_m_l version EQ Terminal_String QUERY_GT LT_x_m_i_COLON_X_M_I GT XMLDocument_ecore_EPackage_2 LT_SLASH_x_m_i_COLON_X_M_I_GT --353
		/.$BeginAction
					setResult(createXMLDocument(getRhsSym(4), getRhsSym(8)));
		  $EndAction
		./
XMLDocument_ecore_EPackage ::= LT_QUERY_x_m_l version EQ Terminal_String encoding EQ Terminal_String QUERY_GT EcoreRoot_ecore_EPackage --354
		/.$BeginAction
					setResult(createXMLDocument(getRhsSym(4), getRhsSym(7), getRhsSym(9)));
		  $EndAction
		./
XMLDocument_ecore_EPackage ::= LT_QUERY_x_m_l encoding EQ Terminal_String QUERY_GT LT_x_m_i_COLON_X_M_I XMLDocument_ecore_EPackage_7 GT XMLDocument_ecore_EPackage_2 LT_SLASH_x_m_i_COLON_X_M_I_GT --355
		/.$BeginAction
					setResult(createXMLDocument(getRhsSym(4), getRhsSym(7), getRhsSym(9)));
		  $EndAction
		./
XMLDocument_ecore_EPackage ::= LT_QUERY_x_m_l version EQ Terminal_String QUERY_GT LT_x_m_i_COLON_X_M_I XMLDocument_ecore_EPackage_7 GT XMLDocument_ecore_EPackage_2 LT_SLASH_x_m_i_COLON_X_M_I_GT --356
		/.$BeginAction
					setResult(createXMLDocument(getRhsSym(4), getRhsSym(7), getRhsSym(9)));
		  $EndAction
		./
XMLDocument_ecore_EPackage ::= LT_QUERY_x_m_l version EQ Terminal_String encoding EQ Terminal_String QUERY_GT LT_x_m_i_COLON_X_M_I SLASH_GT --357
		/.$BeginAction
					setResult(createXMLDocument(getRhsSym(4), getRhsSym(7)));
		  $EndAction
		./
XMLDocument_ecore_EPackage ::= LT_QUERY_x_m_l version EQ Terminal_String encoding EQ Terminal_String QUERY_GT LT_x_m_i_COLON_X_M_I XMLDocument_ecore_EPackage_7 SLASH_GT --358
		/.$BeginAction
					setResult(createXMLDocument(getRhsSym(4), getRhsSym(7), getRhsSym(10)));
		  $EndAction
		./
XMLDocument_ecore_EPackage ::= LT_QUERY_x_m_l version EQ Terminal_String encoding EQ Terminal_String QUERY_GT LT_x_m_i_COLON_X_M_I GT LT_SLASH_x_m_i_COLON_X_M_I_GT --359
		/.$BeginAction
					setResult(createXMLDocument(getRhsSym(4), getRhsSym(7)));
		  $EndAction
		./
XMLDocument_ecore_EPackage ::= LT_QUERY_x_m_l version EQ Terminal_String encoding EQ Terminal_String QUERY_GT LT_x_m_i_COLON_X_M_I XMLDocument_ecore_EPackage_7 GT LT_SLASH_x_m_i_COLON_X_M_I_GT --360
		/.$BeginAction
					setResult(createXMLDocument(getRhsSym(4), getRhsSym(7), getRhsSym(10)));
		  $EndAction
		./
XMLDocument_ecore_EPackage ::= LT_QUERY_x_m_l version EQ Terminal_String encoding EQ Terminal_String QUERY_GT LT_x_m_i_COLON_X_M_I GT XMLDocument_ecore_EPackage_2 LT_SLASH_x_m_i_COLON_X_M_I_GT --361
		/.$BeginAction
					setResult(createXMLDocument(getRhsSym(4), getRhsSym(7), getRhsSym(11)));
		  $EndAction
		./
XMLDocument_ecore_EPackage ::= LT_QUERY_x_m_l version EQ Terminal_String encoding EQ Terminal_String QUERY_GT LT_x_m_i_COLON_X_M_I XMLDocument_ecore_EPackage_7 GT XMLDocument_ecore_EPackage_2 LT_SLASH_x_m_i_COLON_X_M_I_GT --362
		/.$BeginAction
					setResult(createXMLDocument(getRhsSym(4), getRhsSym(7), getRhsSym(10), getRhsSym(12)));
		  $EndAction
		./
XMLDocument_ecore_EPackage_2 ::= XmlElement --363
		/.$BeginAction
					setResult(SetAttributes.create(getRhsSym(1)));
		  $EndAction
		./
XMLDocument_ecore_EPackage_2 ::= XMLDocument_ecore_EPackage_2 XmlElement --364
		/.$BeginAction
					setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
		  $EndAction
		./
XMLDocument_ecore_EPackage_7 ::= XMLAttribute_xmi_version --365
		/.$BeginAction
					setResult(SetAttributes.create(getRhsSym(1)));
		  $EndAction
		./
XMLDocument_ecore_EPackage_7 ::= XMLAttribute_xmlns_ --366
		/.$BeginAction
					setResult(SetAttributes.create(getRhsSym(1)));
		  $EndAction
		./
XMLDocument_ecore_EPackage_7 ::= XMLDocument_ecore_EPackage_7 XMLAttribute_xmi_version --367
		/.$BeginAction
					setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
		  $EndAction
		./
XMLDocument_ecore_EPackage_7 ::= XMLDocument_ecore_EPackage_7 XMLAttribute_xmlns_ --368
		/.$BeginAction
					setResult(SetAttributes.concatenate(getRhsSym(1), getRhsSym(2)));
		  $EndAction
		./

XmlAttribute ::= BooleanAttribute --369
		/.$BeginAction
					setResult(createEObject());
		  $EndAction
		./
XmlAttribute ::= IntegerAttribute --370
		/.$BeginAction
					setResult(createEObject());
		  $EndAction
		./
XmlAttribute ::= OtherAttribute --371
		/.$BeginAction
					setResult(createEObject());
		  $EndAction
		./
XmlAttribute ::= StringAttribute --372
		/.$BeginAction
					setResult(createEObject());
		  $EndAction
		./

XmlElement ::= EcoreClass_ecore_EClass_eOperations --373
		/.$BeginAction
					setResult(createEObject());
		  $EndAction
		./
XmlElement ::= EcoreClass_ecore_EClass_eStructuralFeatures --374
		/.$BeginAction
					setResult(createEObject());
		  $EndAction
		./
XmlElement ::= EcoreClass_ecore_EGenericType_eTypeArguments --375
		/.$BeginAction
					setResult(createEObject());
		  $EndAction
		./
XmlElement ::= EcoreClass_ecore_EModelElement_eAnnotations --376
		/.$BeginAction
					setResult(createEObject());
		  $EndAction
		./
XmlElement ::= EcoreClass_ecore_EOperation_eParameters --377
		/.$BeginAction
					setResult(createEObject());
		  $EndAction
		./
XmlElement ::= EcoreClass_ecore_EPackage_eClassifiers --378
		/.$BeginAction
					setResult(createEObject());
		  $EndAction
		./
XmlElement ::= EcoreClass_ecore_EPackage_eSubpackages --379
		/.$BeginAction
					setResult(createEObject());
		  $EndAction
		./
XmlElement ::= EcoreClass_ecore_ETypedElement_eGenericType --380
		/.$BeginAction
					setResult(createEObject());
		  $EndAction
		./
XmlElement ::= EcoreFeature_ecore_EAnnotation_details --381
		/.$BeginAction
					setResult(createEObject());
		  $EndAction
		./
XmlElement ::= OtherElement --382
		/.$BeginAction
					setResult(createEObject());
		  $EndAction
		./
%End
