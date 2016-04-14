%options escape=$
%options la=1
%options fp=Ecore2XtextParser,prefix=TK_
%options noserialize
%options package=org.eclipse.ocl.examples.ecore2xtext
%options import_terminals=Ecore2XtextLexer.gi
%options ast_type=CSTNode
%options template=dtParserTemplateF.gi
%options include_directory=".;../lpg"

%Start
XmlRoot
%End

%Notice
	/./**
 *******************************************************************************/
	./
%End

%Globals
	/.
	/* imports */
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
	
	Slash_GreaterThan ::= '/>'
	Colon ::= ':'
	LessThan ::= '<'
	LessThan_Slash ::= '</'
	LessThan_Slash_100_101_116_97_105_108_115_GreaterThan ::= '</details>'
	LessThan_Slash_101_65_110_110_111_116_97_116_105_111_110_115_GreaterThan ::= '</eAnnotations>'
	LessThan_Slash_101_67_108_97_115_115_105_102_105_101_114_115_GreaterThan ::= '</eClassifiers>'
	LessThan_Slash_101_71_101_110_101_114_105_99_84_121_112_101_GreaterThan ::= '</eGenericType>'
	LessThan_Slash_101_79_112_101_114_97_116_105_111_110_115_GreaterThan ::= '</eOperations>'
	LessThan_Slash_101_80_97_99_107_97_103_101_115_GreaterThan ::= '</ePackages>'
	LessThan_Slash_101_80_97_114_97_109_101_116_101_114_115_GreaterThan ::= '</eParameters>'
	LessThan_Slash_101_83_116_114_117_99_116_117_114_97_108_70_101_97_116_117_114_101_115_GreaterThan ::= '</eStructuralFeatures>'
	LessThan_Slash_101_84_121_112_101_65_114_103_117_109_101_110_116_115_GreaterThan ::= '</eTypeArguments>'
	LessThan_Slash_101_99_111_114_101_Colon_69_80_97_99_107_97_103_101_GreaterThan ::= '</ecore:EPackage>'
	LessThan_Slash_120_109_105_Colon_88_77_73_GreaterThan ::= '</xmi:XMI>'
	LessThan_QuestionMark_120_109_108 ::= '<?xml'
	LessThan_100_101_116_97_105_108_115 ::= '<details'
	LessThan_101_65_110_110_111_116_97_116_105_111_110_115 ::= '<eAnnotations'
	LessThan_101_67_108_97_115_115_105_102_105_101_114_115 ::= '<eClassifiers'
	LessThan_101_71_101_110_101_114_105_99_84_121_112_101 ::= '<eGenericType'
	LessThan_101_79_112_101_114_97_116_105_111_110_115 ::= '<eOperations'
	LessThan_101_80_97_99_107_97_103_101_115 ::= '<ePackages'
	LessThan_101_80_97_114_97_109_101_116_101_114_115 ::= '<eParameters'
	LessThan_101_83_116_114_117_99_116_117_114_97_108_70_101_97_116_117_114_101_115 ::= '<eStructuralFeatures'
	LessThan_101_84_121_112_101_65_114_103_117_109_101_110_116_115 ::= '<eTypeArguments'
	LessThan_101_99_111_114_101_Colon_69_80_97_99_107_97_103_101 ::= '<ecore:EPackage'
	LessThan_120_109_105_Colon_88_77_73 ::= '<xmi:XMI'
	Equal ::= '='
	GreaterThan ::= '>'
	QuestionMark_GreaterThan ::= '?>'
%End

%Rules
AbstractAttribute ::= abstract Equal STRING --1

BooleanAttribute ::= AbstractAttribute --2
BooleanAttribute ::= ChangeableAttribute --3
BooleanAttribute ::= ContainmentAttribute --4
BooleanAttribute ::= DefaultValueLiteralAttribute --5
BooleanAttribute ::= DerivedAttribute --6
BooleanAttribute ::= OrderedAttribute --7
BooleanAttribute ::= ResolveProxiesAttribute --8
BooleanAttribute ::= SerializableAttribute --9
BooleanAttribute ::= TransientAttribute --10
BooleanAttribute ::= UnsettableAttribute --11
BooleanAttribute ::= VolatileAttribute --12

ChangeableAttribute ::= changeable Equal STRING --13

ContainmentAttribute ::= containment Equal STRING --14

DefaultValueLiteralAttribute ::= defaultValueLiteral Equal STRING --15

DerivedAttribute ::= derived Equal STRING --16

EAnnotation ::= LessThan_101_65_110_110_111_116_97_116_105_111_110_115 Slash_GreaterThan --17
EAnnotation ::= LessThan_101_65_110_110_111_116_97_116_105_111_110_115 EAnnotation_8 Slash_GreaterThan --18
EAnnotation ::= LessThan_101_65_110_110_111_116_97_116_105_111_110_115 GreaterThan LessThan_Slash_101_65_110_110_111_116_97_116_105_111_110_115_GreaterThan --19
EAnnotation ::= LessThan_101_65_110_110_111_116_97_116_105_111_110_115 EAnnotation_8 GreaterThan LessThan_Slash_101_65_110_110_111_116_97_116_105_111_110_115_GreaterThan --20
EAnnotation ::= LessThan_101_65_110_110_111_116_97_116_105_111_110_115 GreaterThan EAnnotation_3 LessThan_Slash_101_65_110_110_111_116_97_116_105_111_110_115_GreaterThan --21
EAnnotation ::= LessThan_101_65_110_110_111_116_97_116_105_111_110_115 EAnnotation_8 GreaterThan EAnnotation_3 LessThan_Slash_101_65_110_110_111_116_97_116_105_111_110_115_GreaterThan --22
EAnnotation_3 ::= EAnnotation --23
EAnnotation_3 ::= EDetail --24
EAnnotation_3 ::= OtherElement --25
EAnnotation_3 ::= EAnnotation_3 EAnnotation --26
EAnnotation_3 ::= EAnnotation_3 EDetail --27
EAnnotation_3 ::= EAnnotation_3 OtherElement --28
EAnnotation_8 ::= OtherAttribute --29
EAnnotation_8 ::= SourceAttribute --30
EAnnotation_8 ::= XsiTypeAttribute --31
EAnnotation_8 ::= EAnnotation_8 OtherAttribute --32
EAnnotation_8 ::= EAnnotation_8 SourceAttribute --33
EAnnotation_8 ::= EAnnotation_8 XsiTypeAttribute --34

EClassifier ::= LessThan_101_67_108_97_115_115_105_102_105_101_114_115 Slash_GreaterThan --35
EClassifier ::= LessThan_101_67_108_97_115_115_105_102_105_101_114_115 EClassifier_8 Slash_GreaterThan --36
EClassifier ::= LessThan_101_67_108_97_115_115_105_102_105_101_114_115 GreaterThan LessThan_Slash_101_67_108_97_115_115_105_102_105_101_114_115_GreaterThan --37
EClassifier ::= LessThan_101_67_108_97_115_115_105_102_105_101_114_115 EClassifier_8 GreaterThan LessThan_Slash_101_67_108_97_115_115_105_102_105_101_114_115_GreaterThan --38
EClassifier ::= LessThan_101_67_108_97_115_115_105_102_105_101_114_115 GreaterThan EClassifier_3 LessThan_Slash_101_67_108_97_115_115_105_102_105_101_114_115_GreaterThan --39
EClassifier ::= LessThan_101_67_108_97_115_115_105_102_105_101_114_115 EClassifier_8 GreaterThan EClassifier_3 LessThan_Slash_101_67_108_97_115_115_105_102_105_101_114_115_GreaterThan --40
EClassifier_3 ::= EAnnotation --41
EClassifier_3 ::= EOperation --42
EClassifier_3 ::= EStructuralFeature --43
EClassifier_3 ::= OtherElement --44
EClassifier_3 ::= EClassifier_3 EAnnotation --45
EClassifier_3 ::= EClassifier_3 EOperation --46
EClassifier_3 ::= EClassifier_3 EStructuralFeature --47
EClassifier_3 ::= EClassifier_3 OtherElement --48
EClassifier_8 ::= AbstractAttribute --49
EClassifier_8 ::= ESuperTypeAttribute --50
EClassifier_8 ::= InstanceClassNameAttribute --51
EClassifier_8 ::= NameAttribute --52
EClassifier_8 ::= OtherAttribute --53
EClassifier_8 ::= SerializableAttribute --54
EClassifier_8 ::= XsiTypeAttribute --55
EClassifier_8 ::= EClassifier_8 AbstractAttribute --56
EClassifier_8 ::= EClassifier_8 ESuperTypeAttribute --57
EClassifier_8 ::= EClassifier_8 InstanceClassNameAttribute --58
EClassifier_8 ::= EClassifier_8 NameAttribute --59
EClassifier_8 ::= EClassifier_8 OtherAttribute --60
EClassifier_8 ::= EClassifier_8 SerializableAttribute --61
EClassifier_8 ::= EClassifier_8 XsiTypeAttribute --62

EClassifierAttribute ::= eClassifier Equal STRING --63

EDetail ::= LessThan_100_101_116_97_105_108_115 Slash_GreaterThan --64
EDetail ::= LessThan_100_101_116_97_105_108_115 EDetail_8 Slash_GreaterThan --65
EDetail ::= LessThan_100_101_116_97_105_108_115 GreaterThan LessThan_Slash_100_101_116_97_105_108_115_GreaterThan --66
EDetail ::= LessThan_100_101_116_97_105_108_115 EDetail_8 GreaterThan LessThan_Slash_100_101_116_97_105_108_115_GreaterThan --67
EDetail ::= LessThan_100_101_116_97_105_108_115 GreaterThan EDetail_3 LessThan_Slash_100_101_116_97_105_108_115_GreaterThan --68
EDetail ::= LessThan_100_101_116_97_105_108_115 EDetail_8 GreaterThan EDetail_3 LessThan_Slash_100_101_116_97_105_108_115_GreaterThan --69
EDetail_3 ::= EAnnotation --70
EDetail_3 ::= OtherElement --71
EDetail_3 ::= EDetail_3 EAnnotation --72
EDetail_3 ::= EDetail_3 OtherElement --73
EDetail_8 ::= KeyAttribute --74
EDetail_8 ::= OtherAttribute --75
EDetail_8 ::= ValueAttribute --76
EDetail_8 ::= XsiTypeAttribute --77
EDetail_8 ::= EDetail_8 KeyAttribute --78
EDetail_8 ::= EDetail_8 OtherAttribute --79
EDetail_8 ::= EDetail_8 ValueAttribute --80
EDetail_8 ::= EDetail_8 XsiTypeAttribute --81

EExceptionsAttribute ::= eExceptions Equal STRING --82

EGenericType ::= LessThan_101_71_101_110_101_114_105_99_84_121_112_101 Slash_GreaterThan --83
EGenericType ::= LessThan_101_71_101_110_101_114_105_99_84_121_112_101 EGenericType_8 Slash_GreaterThan --84
EGenericType ::= LessThan_101_71_101_110_101_114_105_99_84_121_112_101 GreaterThan LessThan_Slash_101_71_101_110_101_114_105_99_84_121_112_101_GreaterThan --85
EGenericType ::= LessThan_101_71_101_110_101_114_105_99_84_121_112_101 EGenericType_8 GreaterThan LessThan_Slash_101_71_101_110_101_114_105_99_84_121_112_101_GreaterThan --86
EGenericType ::= LessThan_101_71_101_110_101_114_105_99_84_121_112_101 GreaterThan EGenericType_3 LessThan_Slash_101_71_101_110_101_114_105_99_84_121_112_101_GreaterThan --87
EGenericType ::= LessThan_101_71_101_110_101_114_105_99_84_121_112_101 EGenericType_8 GreaterThan EGenericType_3 LessThan_Slash_101_71_101_110_101_114_105_99_84_121_112_101_GreaterThan --88
EGenericType_3 ::= EAnnotation --89
EGenericType_3 ::= ETypeArgument --90
EGenericType_3 ::= OtherElement --91
EGenericType_3 ::= EGenericType_3 EAnnotation --92
EGenericType_3 ::= EGenericType_3 ETypeArgument --93
EGenericType_3 ::= EGenericType_3 OtherElement --94
EGenericType_8 ::= EClassifierAttribute --95
EGenericType_8 ::= OtherAttribute --96
EGenericType_8 ::= XsiTypeAttribute --97
EGenericType_8 ::= EGenericType_8 EClassifierAttribute --98
EGenericType_8 ::= EGenericType_8 OtherAttribute --99
EGenericType_8 ::= EGenericType_8 XsiTypeAttribute --100

EOperation ::= LessThan_101_79_112_101_114_97_116_105_111_110_115 Slash_GreaterThan --101
EOperation ::= LessThan_101_79_112_101_114_97_116_105_111_110_115 EOperation_8 Slash_GreaterThan --102
EOperation ::= LessThan_101_79_112_101_114_97_116_105_111_110_115 GreaterThan LessThan_Slash_101_79_112_101_114_97_116_105_111_110_115_GreaterThan --103
EOperation ::= LessThan_101_79_112_101_114_97_116_105_111_110_115 EOperation_8 GreaterThan LessThan_Slash_101_79_112_101_114_97_116_105_111_110_115_GreaterThan --104
EOperation ::= LessThan_101_79_112_101_114_97_116_105_111_110_115 GreaterThan EOperation_3 LessThan_Slash_101_79_112_101_114_97_116_105_111_110_115_GreaterThan --105
EOperation ::= LessThan_101_79_112_101_114_97_116_105_111_110_115 EOperation_8 GreaterThan EOperation_3 LessThan_Slash_101_79_112_101_114_97_116_105_111_110_115_GreaterThan --106
EOperation_3 ::= EAnnotation --107
EOperation_3 ::= EGenericType --108
EOperation_3 ::= EParameter --109
EOperation_3 ::= OtherElement --110
EOperation_3 ::= EOperation_3 EAnnotation --111
EOperation_3 ::= EOperation_3 EGenericType --112
EOperation_3 ::= EOperation_3 EParameter --113
EOperation_3 ::= EOperation_3 OtherElement --114
EOperation_8 ::= EExceptionsAttribute --115
EOperation_8 ::= ETypeAttribute --116
EOperation_8 ::= NameAttribute --117
EOperation_8 ::= OtherAttribute --118
EOperation_8 ::= XsiTypeAttribute --119
EOperation_8 ::= EOperation_8 EExceptionsAttribute --120
EOperation_8 ::= EOperation_8 ETypeAttribute --121
EOperation_8 ::= EOperation_8 NameAttribute --122
EOperation_8 ::= EOperation_8 OtherAttribute --123
EOperation_8 ::= EOperation_8 XsiTypeAttribute --124

EOppositeAttribute ::= eOpposite Equal STRING --125

EPackage ::= LessThan_101_80_97_99_107_97_103_101_115 Slash_GreaterThan --126
EPackage ::= LessThan_101_80_97_99_107_97_103_101_115 EPackage_8 Slash_GreaterThan --127
EPackage ::= LessThan_101_80_97_99_107_97_103_101_115 GreaterThan LessThan_Slash_101_80_97_99_107_97_103_101_115_GreaterThan --128
EPackage ::= LessThan_101_80_97_99_107_97_103_101_115 EPackage_8 GreaterThan LessThan_Slash_101_80_97_99_107_97_103_101_115_GreaterThan --129
EPackage ::= LessThan_101_80_97_99_107_97_103_101_115 GreaterThan EPackage_3 LessThan_Slash_101_80_97_99_107_97_103_101_115_GreaterThan --130
EPackage ::= LessThan_101_80_97_99_107_97_103_101_115 EPackage_8 GreaterThan EPackage_3 LessThan_Slash_101_80_97_99_107_97_103_101_115_GreaterThan --131
EPackage_3 ::= EAnnotation --132
EPackage_3 ::= EClassifier --133
EPackage_3 ::= EGenericType --134
EPackage_3 ::= OtherElement --135
EPackage_3 ::= EPackage_3 EAnnotation --136
EPackage_3 ::= EPackage_3 EClassifier --137
EPackage_3 ::= EPackage_3 EGenericType --138
EPackage_3 ::= EPackage_3 OtherElement --139
EPackage_8 ::= NameAttribute --140
EPackage_8 ::= NsPrefixAttribute --141
EPackage_8 ::= NsURIAttribute --142
EPackage_8 ::= OtherAttribute --143
EPackage_8 ::= XsiTypeAttribute --144
EPackage_8 ::= EPackage_8 NameAttribute --145
EPackage_8 ::= EPackage_8 NsPrefixAttribute --146
EPackage_8 ::= EPackage_8 NsURIAttribute --147
EPackage_8 ::= EPackage_8 OtherAttribute --148
EPackage_8 ::= EPackage_8 XsiTypeAttribute --149

EParameter ::= LessThan_101_80_97_114_97_109_101_116_101_114_115 Slash_GreaterThan --150
EParameter ::= LessThan_101_80_97_114_97_109_101_116_101_114_115 EParameter_8 Slash_GreaterThan --151
EParameter ::= LessThan_101_80_97_114_97_109_101_116_101_114_115 GreaterThan LessThan_Slash_101_80_97_114_97_109_101_116_101_114_115_GreaterThan --152
EParameter ::= LessThan_101_80_97_114_97_109_101_116_101_114_115 EParameter_8 GreaterThan LessThan_Slash_101_80_97_114_97_109_101_116_101_114_115_GreaterThan --153
EParameter ::= LessThan_101_80_97_114_97_109_101_116_101_114_115 GreaterThan EParameter_3 LessThan_Slash_101_80_97_114_97_109_101_116_101_114_115_GreaterThan --154
EParameter ::= LessThan_101_80_97_114_97_109_101_116_101_114_115 EParameter_8 GreaterThan EParameter_3 LessThan_Slash_101_80_97_114_97_109_101_116_101_114_115_GreaterThan --155
EParameter_3 ::= EAnnotation --156
EParameter_3 ::= EGenericType --157
EParameter_3 ::= OtherElement --158
EParameter_3 ::= EParameter_3 EAnnotation --159
EParameter_3 ::= EParameter_3 EGenericType --160
EParameter_3 ::= EParameter_3 OtherElement --161
EParameter_8 ::= ETypeAttribute --162
EParameter_8 ::= NameAttribute --163
EParameter_8 ::= OtherAttribute --164
EParameter_8 ::= XsiTypeAttribute --165
EParameter_8 ::= EParameter_8 ETypeAttribute --166
EParameter_8 ::= EParameter_8 NameAttribute --167
EParameter_8 ::= EParameter_8 OtherAttribute --168
EParameter_8 ::= EParameter_8 XsiTypeAttribute --169

EStructuralFeature ::= LessThan_101_83_116_114_117_99_116_117_114_97_108_70_101_97_116_117_114_101_115 Slash_GreaterThan --170
EStructuralFeature ::= LessThan_101_83_116_114_117_99_116_117_114_97_108_70_101_97_116_117_114_101_115 EStructuralFeature_8 Slash_GreaterThan --171
EStructuralFeature ::= LessThan_101_83_116_114_117_99_116_117_114_97_108_70_101_97_116_117_114_101_115 GreaterThan LessThan_Slash_101_83_116_114_117_99_116_117_114_97_108_70_101_97_116_117_114_101_115_GreaterThan --172
EStructuralFeature ::= LessThan_101_83_116_114_117_99_116_117_114_97_108_70_101_97_116_117_114_101_115 EStructuralFeature_8 GreaterThan LessThan_Slash_101_83_116_114_117_99_116_117_114_97_108_70_101_97_116_117_114_101_115_GreaterThan --173
EStructuralFeature ::= LessThan_101_83_116_114_117_99_116_117_114_97_108_70_101_97_116_117_114_101_115 GreaterThan EStructuralFeature_3 LessThan_Slash_101_83_116_114_117_99_116_117_114_97_108_70_101_97_116_117_114_101_115_GreaterThan --174
EStructuralFeature ::= LessThan_101_83_116_114_117_99_116_117_114_97_108_70_101_97_116_117_114_101_115 EStructuralFeature_8 GreaterThan EStructuralFeature_3 LessThan_Slash_101_83_116_114_117_99_116_117_114_97_108_70_101_97_116_117_114_101_115_GreaterThan --175
EStructuralFeature_3 ::= EAnnotation --176
EStructuralFeature_3 ::= EGenericType --177
EStructuralFeature_3 ::= OtherElement --178
EStructuralFeature_3 ::= EStructuralFeature_3 EAnnotation --179
EStructuralFeature_3 ::= EStructuralFeature_3 EGenericType --180
EStructuralFeature_3 ::= EStructuralFeature_3 OtherElement --181
EStructuralFeature_8 ::= ChangeableAttribute --182
EStructuralFeature_8 ::= ContainmentAttribute --183
EStructuralFeature_8 ::= DefaultValueLiteralAttribute --184
EStructuralFeature_8 ::= DerivedAttribute --185
EStructuralFeature_8 ::= EOppositeAttribute --186
EStructuralFeature_8 ::= ETypeAttribute --187
EStructuralFeature_8 ::= LowerBoundAttribute --188
EStructuralFeature_8 ::= NameAttribute --189
EStructuralFeature_8 ::= OrderedAttribute --190
EStructuralFeature_8 ::= OtherAttribute --191
EStructuralFeature_8 ::= ResolveProxiesAttribute --192
EStructuralFeature_8 ::= TransientAttribute --193
EStructuralFeature_8 ::= UnsettableAttribute --194
EStructuralFeature_8 ::= UpperBoundAttribute --195
EStructuralFeature_8 ::= VolatileAttribute --196
EStructuralFeature_8 ::= XsiTypeAttribute --197
EStructuralFeature_8 ::= EStructuralFeature_8 ChangeableAttribute --198
EStructuralFeature_8 ::= EStructuralFeature_8 ContainmentAttribute --199
EStructuralFeature_8 ::= EStructuralFeature_8 DefaultValueLiteralAttribute --200
EStructuralFeature_8 ::= EStructuralFeature_8 DerivedAttribute --201
EStructuralFeature_8 ::= EStructuralFeature_8 EOppositeAttribute --202
EStructuralFeature_8 ::= EStructuralFeature_8 ETypeAttribute --203
EStructuralFeature_8 ::= EStructuralFeature_8 LowerBoundAttribute --204
EStructuralFeature_8 ::= EStructuralFeature_8 NameAttribute --205
EStructuralFeature_8 ::= EStructuralFeature_8 OrderedAttribute --206
EStructuralFeature_8 ::= EStructuralFeature_8 OtherAttribute --207
EStructuralFeature_8 ::= EStructuralFeature_8 ResolveProxiesAttribute --208
EStructuralFeature_8 ::= EStructuralFeature_8 TransientAttribute --209
EStructuralFeature_8 ::= EStructuralFeature_8 UnsettableAttribute --210
EStructuralFeature_8 ::= EStructuralFeature_8 UpperBoundAttribute --211
EStructuralFeature_8 ::= EStructuralFeature_8 VolatileAttribute --212
EStructuralFeature_8 ::= EStructuralFeature_8 XsiTypeAttribute --213

ESuperTypeAttribute ::= eSuperTypes Equal STRING --214

ETypeArgument ::= LessThan_101_84_121_112_101_65_114_103_117_109_101_110_116_115 Slash_GreaterThan --215
ETypeArgument ::= LessThan_101_84_121_112_101_65_114_103_117_109_101_110_116_115 ETypeArgument_8 Slash_GreaterThan --216
ETypeArgument ::= LessThan_101_84_121_112_101_65_114_103_117_109_101_110_116_115 GreaterThan LessThan_Slash_101_84_121_112_101_65_114_103_117_109_101_110_116_115_GreaterThan --217
ETypeArgument ::= LessThan_101_84_121_112_101_65_114_103_117_109_101_110_116_115 ETypeArgument_8 GreaterThan LessThan_Slash_101_84_121_112_101_65_114_103_117_109_101_110_116_115_GreaterThan --218
ETypeArgument ::= LessThan_101_84_121_112_101_65_114_103_117_109_101_110_116_115 GreaterThan ETypeArgument_3 LessThan_Slash_101_84_121_112_101_65_114_103_117_109_101_110_116_115_GreaterThan --219
ETypeArgument ::= LessThan_101_84_121_112_101_65_114_103_117_109_101_110_116_115 ETypeArgument_8 GreaterThan ETypeArgument_3 LessThan_Slash_101_84_121_112_101_65_114_103_117_109_101_110_116_115_GreaterThan --220
ETypeArgument_3 ::= EAnnotation --221
ETypeArgument_3 ::= OtherElement --222
ETypeArgument_3 ::= ETypeArgument_3 EAnnotation --223
ETypeArgument_3 ::= ETypeArgument_3 OtherElement --224
ETypeArgument_8 ::= EClassifierAttribute --225
ETypeArgument_8 ::= OtherAttribute --226
ETypeArgument_8 ::= XsiTypeAttribute --227
ETypeArgument_8 ::= ETypeArgument_8 EClassifierAttribute --228
ETypeArgument_8 ::= ETypeArgument_8 OtherAttribute --229
ETypeArgument_8 ::= ETypeArgument_8 XsiTypeAttribute --230

ETypeAttribute ::= eType Equal STRING --231

ID ::= abstract --232
ID ::= changeable --233
ID ::= containment --234
ID ::= defaultValueLiteral --235
ID ::= derived --236
ID ::= eClassifier --237
ID ::= eExceptions --238
ID ::= eOpposite --239
ID ::= eSuperTypes --240
ID ::= eType --241
ID ::= encoding --242
ID ::= instanceClassName --243
ID ::= key --244
ID ::= lowerBound --245
ID ::= name --246
ID ::= nsPrefix --247
ID ::= nsURI --248
ID ::= ordered --249
ID ::= resolveProxies --250
ID ::= serializable --251
ID ::= source --252
ID ::= transient --253
ID ::= unsettable --254
ID ::= upperBound --255
ID ::= value --256
ID ::= version --257
ID ::= volatile --258
ID ::= IDENTIFIER --259

InstanceClassNameAttribute ::= instanceClassName Equal STRING --260

IntegerAttribute ::= LowerBoundAttribute --261
IntegerAttribute ::= UpperBoundAttribute --262

KeyAttribute ::= key Equal STRING --263

LowerBoundAttribute ::= lowerBound Equal STRING --264

NameAttribute ::= name Equal STRING --265

NsPrefixAttribute ::= nsPrefix Equal STRING --266

NsURIAttribute ::= nsURI Equal STRING --267

OrderedAttribute ::= ordered Equal STRING --268

OtherAttribute ::= IDENTIFIER Equal STRING --269

OtherElement ::= LessThan ID Slash_GreaterThan --270
OtherElement ::= LessThan ID OtherElement_6 Slash_GreaterThan --271
OtherElement ::= LessThan ID GreaterThan LessThan_Slash ID GreaterThan --272
OtherElement ::= LessThan ID OtherElement_6 GreaterThan LessThan_Slash ID GreaterThan --273
OtherElement ::= LessThan ID GreaterThan OtherElement_2 LessThan_Slash ID GreaterThan --274
OtherElement ::= LessThan ID OtherElement_6 GreaterThan OtherElement_2 LessThan_Slash ID GreaterThan --275
OtherElement_2 ::= XmlElement --276
OtherElement_2 ::= OtherElement_2 XmlElement --277
OtherElement_6 ::= XmlAttribute --278
OtherElement_6 ::= OtherElement_6 XmlAttribute --279

ResolveProxiesAttribute ::= resolveProxies Equal STRING --280

RootEPackage ::= LessThan_101_99_111_114_101_Colon_69_80_97_99_107_97_103_101 Slash_GreaterThan --281
RootEPackage ::= LessThan_101_99_111_114_101_Colon_69_80_97_99_107_97_103_101 RootEPackage_8 Slash_GreaterThan --282
RootEPackage ::= LessThan_101_99_111_114_101_Colon_69_80_97_99_107_97_103_101 GreaterThan LessThan_Slash_101_99_111_114_101_Colon_69_80_97_99_107_97_103_101_GreaterThan --283
RootEPackage ::= LessThan_101_99_111_114_101_Colon_69_80_97_99_107_97_103_101 RootEPackage_8 GreaterThan LessThan_Slash_101_99_111_114_101_Colon_69_80_97_99_107_97_103_101_GreaterThan --284
RootEPackage ::= LessThan_101_99_111_114_101_Colon_69_80_97_99_107_97_103_101 GreaterThan RootEPackage_3 LessThan_Slash_101_99_111_114_101_Colon_69_80_97_99_107_97_103_101_GreaterThan --285
RootEPackage ::= LessThan_101_99_111_114_101_Colon_69_80_97_99_107_97_103_101 RootEPackage_8 GreaterThan RootEPackage_3 LessThan_Slash_101_99_111_114_101_Colon_69_80_97_99_107_97_103_101_GreaterThan --286
RootEPackage_3 ::= EAnnotation --287
RootEPackage_3 ::= EClassifier --288
RootEPackage_3 ::= OtherElement --289
RootEPackage_3 ::= RootEPackage_3 EAnnotation --290
RootEPackage_3 ::= RootEPackage_3 EClassifier --291
RootEPackage_3 ::= RootEPackage_3 OtherElement --292
RootEPackage_8 ::= NameAttribute --293
RootEPackage_8 ::= NsPrefixAttribute --294
RootEPackage_8 ::= NsURIAttribute --295
RootEPackage_8 ::= OtherAttribute --296
RootEPackage_8 ::= XmiVersionAttribute --297
RootEPackage_8 ::= XmlnsAttribute --298
RootEPackage_8 ::= XsiTypeAttribute --299
RootEPackage_8 ::= RootEPackage_8 NameAttribute --300
RootEPackage_8 ::= RootEPackage_8 NsPrefixAttribute --301
RootEPackage_8 ::= RootEPackage_8 NsURIAttribute --302
RootEPackage_8 ::= RootEPackage_8 OtherAttribute --303
RootEPackage_8 ::= RootEPackage_8 XmiVersionAttribute --304
RootEPackage_8 ::= RootEPackage_8 XmlnsAttribute --305
RootEPackage_8 ::= RootEPackage_8 XsiTypeAttribute --306

SerializableAttribute ::= serializable Equal STRING --307

SourceAttribute ::= source Equal STRING --308

StringAttribute ::= EClassifierAttribute --309
StringAttribute ::= EExceptionsAttribute --310
StringAttribute ::= EOppositeAttribute --311
StringAttribute ::= ESuperTypeAttribute --312
StringAttribute ::= ETypeAttribute --313
StringAttribute ::= InstanceClassNameAttribute --314
StringAttribute ::= KeyAttribute --315
StringAttribute ::= NameAttribute --316
StringAttribute ::= NsPrefixAttribute --317
StringAttribute ::= NsURIAttribute --318
StringAttribute ::= SourceAttribute --319
StringAttribute ::= ValueAttribute --320
StringAttribute ::= XmiVersionAttribute --321
StringAttribute ::= XmlnsAttribute --322
StringAttribute ::= XsiTypeAttribute --323

TransientAttribute ::= transient Equal STRING --324

UnsettableAttribute ::= unsettable Equal STRING --325

UpperBoundAttribute ::= upperBound Equal STRING --326

ValueAttribute ::= value Equal STRING --327

VolatileAttribute ::= volatile Equal STRING --328

XmiVersionAttribute ::= xmi Colon version Equal STRING --329

XmlAttribute ::= BooleanAttribute --330
XmlAttribute ::= IntegerAttribute --331
XmlAttribute ::= OtherAttribute --332
XmlAttribute ::= StringAttribute --333

XmlElement ::= EAnnotation --334
XmlElement ::= EClassifier --335
XmlElement ::= EDetail --336
XmlElement ::= EGenericType --337
XmlElement ::= EOperation --338
XmlElement ::= EPackage --339
XmlElement ::= EParameter --340
XmlElement ::= EStructuralFeature --341
XmlElement ::= ETypeArgument --342
XmlElement ::= OtherElement --343

XmlRoot ::= LessThan_QuestionMark_120_109_108 QuestionMark_GreaterThan RootEPackage --344
XmlRoot ::= LessThan_QuestionMark_120_109_108 QuestionMark_GreaterThan LessThan_120_109_105_Colon_88_77_73 Slash_GreaterThan --345
XmlRoot ::= LessThan_QuestionMark_120_109_108 QuestionMark_GreaterThan LessThan_120_109_105_Colon_88_77_73 XmlRoot_7 Slash_GreaterThan --346
XmlRoot ::= LessThan_QuestionMark_120_109_108 QuestionMark_GreaterThan LessThan_120_109_105_Colon_88_77_73 GreaterThan LessThan_Slash_120_109_105_Colon_88_77_73_GreaterThan --347
XmlRoot ::= LessThan_QuestionMark_120_109_108 QuestionMark_GreaterThan LessThan_120_109_105_Colon_88_77_73 XmlRoot_7 GreaterThan LessThan_Slash_120_109_105_Colon_88_77_73_GreaterThan --348
XmlRoot ::= LessThan_QuestionMark_120_109_108 QuestionMark_GreaterThan LessThan_120_109_105_Colon_88_77_73 GreaterThan XmlRoot_2 LessThan_Slash_120_109_105_Colon_88_77_73_GreaterThan --349
XmlRoot ::= LessThan_QuestionMark_120_109_108 encoding Equal STRING QuestionMark_GreaterThan RootEPackage --350
XmlRoot ::= LessThan_QuestionMark_120_109_108 version Equal STRING QuestionMark_GreaterThan RootEPackage --351
XmlRoot ::= LessThan_QuestionMark_120_109_108 QuestionMark_GreaterThan LessThan_120_109_105_Colon_88_77_73 XmlRoot_7 GreaterThan XmlRoot_2 LessThan_Slash_120_109_105_Colon_88_77_73_GreaterThan --352
XmlRoot ::= LessThan_QuestionMark_120_109_108 encoding Equal STRING QuestionMark_GreaterThan LessThan_120_109_105_Colon_88_77_73 Slash_GreaterThan --353
XmlRoot ::= LessThan_QuestionMark_120_109_108 version Equal STRING QuestionMark_GreaterThan LessThan_120_109_105_Colon_88_77_73 Slash_GreaterThan --354
XmlRoot ::= LessThan_QuestionMark_120_109_108 encoding Equal STRING QuestionMark_GreaterThan LessThan_120_109_105_Colon_88_77_73 XmlRoot_7 Slash_GreaterThan --355
XmlRoot ::= LessThan_QuestionMark_120_109_108 encoding Equal STRING QuestionMark_GreaterThan LessThan_120_109_105_Colon_88_77_73 GreaterThan LessThan_Slash_120_109_105_Colon_88_77_73_GreaterThan --356
XmlRoot ::= LessThan_QuestionMark_120_109_108 version Equal STRING QuestionMark_GreaterThan LessThan_120_109_105_Colon_88_77_73 XmlRoot_7 Slash_GreaterThan --357
XmlRoot ::= LessThan_QuestionMark_120_109_108 version Equal STRING QuestionMark_GreaterThan LessThan_120_109_105_Colon_88_77_73 GreaterThan LessThan_Slash_120_109_105_Colon_88_77_73_GreaterThan --358
XmlRoot ::= LessThan_QuestionMark_120_109_108 encoding Equal STRING QuestionMark_GreaterThan LessThan_120_109_105_Colon_88_77_73 XmlRoot_7 GreaterThan LessThan_Slash_120_109_105_Colon_88_77_73_GreaterThan --359
XmlRoot ::= LessThan_QuestionMark_120_109_108 encoding Equal STRING QuestionMark_GreaterThan LessThan_120_109_105_Colon_88_77_73 GreaterThan XmlRoot_2 LessThan_Slash_120_109_105_Colon_88_77_73_GreaterThan --360
XmlRoot ::= LessThan_QuestionMark_120_109_108 version Equal STRING QuestionMark_GreaterThan LessThan_120_109_105_Colon_88_77_73 XmlRoot_7 GreaterThan LessThan_Slash_120_109_105_Colon_88_77_73_GreaterThan --361
XmlRoot ::= LessThan_QuestionMark_120_109_108 version Equal STRING QuestionMark_GreaterThan LessThan_120_109_105_Colon_88_77_73 GreaterThan XmlRoot_2 LessThan_Slash_120_109_105_Colon_88_77_73_GreaterThan --362
XmlRoot ::= LessThan_QuestionMark_120_109_108 version Equal STRING encoding Equal STRING QuestionMark_GreaterThan RootEPackage --363
XmlRoot ::= LessThan_QuestionMark_120_109_108 encoding Equal STRING QuestionMark_GreaterThan LessThan_120_109_105_Colon_88_77_73 XmlRoot_7 GreaterThan XmlRoot_2 LessThan_Slash_120_109_105_Colon_88_77_73_GreaterThan --364
XmlRoot ::= LessThan_QuestionMark_120_109_108 version Equal STRING QuestionMark_GreaterThan LessThan_120_109_105_Colon_88_77_73 XmlRoot_7 GreaterThan XmlRoot_2 LessThan_Slash_120_109_105_Colon_88_77_73_GreaterThan --365
XmlRoot ::= LessThan_QuestionMark_120_109_108 version Equal STRING encoding Equal STRING QuestionMark_GreaterThan LessThan_120_109_105_Colon_88_77_73 Slash_GreaterThan --366
XmlRoot ::= LessThan_QuestionMark_120_109_108 version Equal STRING encoding Equal STRING QuestionMark_GreaterThan LessThan_120_109_105_Colon_88_77_73 XmlRoot_7 Slash_GreaterThan --367
XmlRoot ::= LessThan_QuestionMark_120_109_108 version Equal STRING encoding Equal STRING QuestionMark_GreaterThan LessThan_120_109_105_Colon_88_77_73 GreaterThan LessThan_Slash_120_109_105_Colon_88_77_73_GreaterThan --368
XmlRoot ::= LessThan_QuestionMark_120_109_108 version Equal STRING encoding Equal STRING QuestionMark_GreaterThan LessThan_120_109_105_Colon_88_77_73 XmlRoot_7 GreaterThan LessThan_Slash_120_109_105_Colon_88_77_73_GreaterThan --369
XmlRoot ::= LessThan_QuestionMark_120_109_108 version Equal STRING encoding Equal STRING QuestionMark_GreaterThan LessThan_120_109_105_Colon_88_77_73 GreaterThan XmlRoot_2 LessThan_Slash_120_109_105_Colon_88_77_73_GreaterThan --370
XmlRoot ::= LessThan_QuestionMark_120_109_108 version Equal STRING encoding Equal STRING QuestionMark_GreaterThan LessThan_120_109_105_Colon_88_77_73 XmlRoot_7 GreaterThan XmlRoot_2 LessThan_Slash_120_109_105_Colon_88_77_73_GreaterThan --371
XmlRoot_2 ::= XmlElement --372
XmlRoot_2 ::= XmlRoot_2 XmlElement --373
XmlRoot_7 ::= XmiVersionAttribute --374
XmlRoot_7 ::= XmlnsAttribute --375
XmlRoot_7 ::= XmlRoot_7 XmiVersionAttribute --376
XmlRoot_7 ::= XmlRoot_7 XmlnsAttribute --377

XmlnsAttribute ::= xmlns Colon ID Equal STRING --378

XsiTypeAttribute ::= xsi Colon type Equal STRING --379
%End
