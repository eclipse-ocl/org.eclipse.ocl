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

	SLASH_RANGLE ::= '/>'
	COLON ::= ':'
	LANGLE ::= '<'
	LANGLE_SLASH ::= '</'
	LANGLE_SLASH_100_101_116_97_105_108_115_RANGLE ::= '</details>'
	LANGLE_SLASH_101_65_110_110_111_116_97_116_105_111_110_115_RANGLE ::= '</eAnnotations>'
	LANGLE_SLASH_101_67_108_97_115_115_105_102_105_101_114_115_RANGLE ::= '</eClassifiers>'
	LANGLE_SLASH_101_71_101_110_101_114_105_99_84_121_112_101_RANGLE ::= '</eGenericType>'
	LANGLE_SLASH_101_79_112_101_114_97_116_105_111_110_115_RANGLE ::= '</eOperations>'
	LANGLE_SLASH_101_80_97_99_107_97_103_101_115_RANGLE ::= '</ePackages>'
	LANGLE_SLASH_101_80_97_114_97_109_101_116_101_114_115_RANGLE ::= '</eParameters>'
	LANGLE_SLASH_101_83_116_114_117_99_116_117_114_97_108_70_101_97_116_117_114_101_115_RANGLE ::= '</eStructuralFeatures>'
	LANGLE_SLASH_101_84_121_112_101_65_114_103_117_109_101_110_116_115_RANGLE ::= '</eTypeArguments>'
	LANGLE_SLASH_101_99_111_114_101_COLON_69_80_97_99_107_97_103_101_RANGLE ::= '</ecore:EPackage>'
	LANGLE_SLASH_120_109_105_COLON_88_77_73_RANGLE ::= '</xmi:XMI>'
	LANGLE_QUERY_120_109_108 ::= '<?xml'
	LANGLE_100_101_116_97_105_108_115 ::= '<details'
	LANGLE_101_65_110_110_111_116_97_116_105_111_110_115 ::= '<eAnnotations'
	LANGLE_101_67_108_97_115_115_105_102_105_101_114_115 ::= '<eClassifiers'
	LANGLE_101_71_101_110_101_114_105_99_84_121_112_101 ::= '<eGenericType'
	LANGLE_101_79_112_101_114_97_116_105_111_110_115 ::= '<eOperations'
	LANGLE_101_80_97_99_107_97_103_101_115 ::= '<ePackages'
	LANGLE_101_80_97_114_97_109_101_116_101_114_115 ::= '<eParameters'
	LANGLE_101_83_116_114_117_99_116_117_114_97_108_70_101_97_116_117_114_101_115 ::= '<eStructuralFeatures'
	LANGLE_101_84_121_112_101_65_114_103_117_109_101_110_116_115 ::= '<eTypeArguments'
	LANGLE_101_99_111_114_101_COLON_69_80_97_99_107_97_103_101 ::= '<ecore:EPackage'
	LANGLE_120_109_105_COLON_88_77_73 ::= '<xmi:XMI'
	EQUALS ::= '='
	RANGLE ::= '>'
	QUERY_RANGLE ::= '?>'
%End

%Rules
AbstractAttribute ::= abstract EQUALS STRING --1

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

ChangeableAttribute ::= changeable EQUALS STRING --13

ContainmentAttribute ::= containment EQUALS STRING --14

DefaultValueLiteralAttribute ::= defaultValueLiteral EQUALS STRING --15

DerivedAttribute ::= derived EQUALS STRING --16

EAnnotation ::= LANGLE_101_65_110_110_111_116_97_116_105_111_110_115 SLASH_RANGLE --17
EAnnotation ::= LANGLE_101_65_110_110_111_116_97_116_105_111_110_115 EAnnotation_8 SLASH_RANGLE --18
EAnnotation ::= LANGLE_101_65_110_110_111_116_97_116_105_111_110_115 RANGLE LANGLE_SLASH_101_65_110_110_111_116_97_116_105_111_110_115_RANGLE --19
EAnnotation ::= LANGLE_101_65_110_110_111_116_97_116_105_111_110_115 EAnnotation_8 RANGLE LANGLE_SLASH_101_65_110_110_111_116_97_116_105_111_110_115_RANGLE --20
EAnnotation ::= LANGLE_101_65_110_110_111_116_97_116_105_111_110_115 RANGLE EAnnotation_3 LANGLE_SLASH_101_65_110_110_111_116_97_116_105_111_110_115_RANGLE --21
EAnnotation ::= LANGLE_101_65_110_110_111_116_97_116_105_111_110_115 EAnnotation_8 RANGLE EAnnotation_3 LANGLE_SLASH_101_65_110_110_111_116_97_116_105_111_110_115_RANGLE --22
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

EClassifier ::= LANGLE_101_67_108_97_115_115_105_102_105_101_114_115 SLASH_RANGLE --35
EClassifier ::= LANGLE_101_67_108_97_115_115_105_102_105_101_114_115 EClassifier_8 SLASH_RANGLE --36
EClassifier ::= LANGLE_101_67_108_97_115_115_105_102_105_101_114_115 RANGLE LANGLE_SLASH_101_67_108_97_115_115_105_102_105_101_114_115_RANGLE --37
EClassifier ::= LANGLE_101_67_108_97_115_115_105_102_105_101_114_115 EClassifier_8 RANGLE LANGLE_SLASH_101_67_108_97_115_115_105_102_105_101_114_115_RANGLE --38
EClassifier ::= LANGLE_101_67_108_97_115_115_105_102_105_101_114_115 RANGLE EClassifier_3 LANGLE_SLASH_101_67_108_97_115_115_105_102_105_101_114_115_RANGLE --39
EClassifier ::= LANGLE_101_67_108_97_115_115_105_102_105_101_114_115 EClassifier_8 RANGLE EClassifier_3 LANGLE_SLASH_101_67_108_97_115_115_105_102_105_101_114_115_RANGLE --40
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

EClassifierAttribute ::= eClassifier EQUALS STRING --63

EDetail ::= LANGLE_100_101_116_97_105_108_115 SLASH_RANGLE --64
EDetail ::= LANGLE_100_101_116_97_105_108_115 EDetail_8 SLASH_RANGLE --65
EDetail ::= LANGLE_100_101_116_97_105_108_115 RANGLE LANGLE_SLASH_100_101_116_97_105_108_115_RANGLE --66
EDetail ::= LANGLE_100_101_116_97_105_108_115 EDetail_8 RANGLE LANGLE_SLASH_100_101_116_97_105_108_115_RANGLE --67
EDetail ::= LANGLE_100_101_116_97_105_108_115 RANGLE EDetail_3 LANGLE_SLASH_100_101_116_97_105_108_115_RANGLE --68
EDetail ::= LANGLE_100_101_116_97_105_108_115 EDetail_8 RANGLE EDetail_3 LANGLE_SLASH_100_101_116_97_105_108_115_RANGLE --69
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

EExceptionsAttribute ::= eExceptions EQUALS STRING --82

EGenericType ::= LANGLE_101_71_101_110_101_114_105_99_84_121_112_101 SLASH_RANGLE --83
EGenericType ::= LANGLE_101_71_101_110_101_114_105_99_84_121_112_101 EGenericType_8 SLASH_RANGLE --84
EGenericType ::= LANGLE_101_71_101_110_101_114_105_99_84_121_112_101 RANGLE LANGLE_SLASH_101_71_101_110_101_114_105_99_84_121_112_101_RANGLE --85
EGenericType ::= LANGLE_101_71_101_110_101_114_105_99_84_121_112_101 EGenericType_8 RANGLE LANGLE_SLASH_101_71_101_110_101_114_105_99_84_121_112_101_RANGLE --86
EGenericType ::= LANGLE_101_71_101_110_101_114_105_99_84_121_112_101 RANGLE EGenericType_3 LANGLE_SLASH_101_71_101_110_101_114_105_99_84_121_112_101_RANGLE --87
EGenericType ::= LANGLE_101_71_101_110_101_114_105_99_84_121_112_101 EGenericType_8 RANGLE EGenericType_3 LANGLE_SLASH_101_71_101_110_101_114_105_99_84_121_112_101_RANGLE --88
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

EOperation ::= LANGLE_101_79_112_101_114_97_116_105_111_110_115 SLASH_RANGLE --101
EOperation ::= LANGLE_101_79_112_101_114_97_116_105_111_110_115 EOperation_8 SLASH_RANGLE --102
EOperation ::= LANGLE_101_79_112_101_114_97_116_105_111_110_115 RANGLE LANGLE_SLASH_101_79_112_101_114_97_116_105_111_110_115_RANGLE --103
EOperation ::= LANGLE_101_79_112_101_114_97_116_105_111_110_115 EOperation_8 RANGLE LANGLE_SLASH_101_79_112_101_114_97_116_105_111_110_115_RANGLE --104
EOperation ::= LANGLE_101_79_112_101_114_97_116_105_111_110_115 RANGLE EOperation_3 LANGLE_SLASH_101_79_112_101_114_97_116_105_111_110_115_RANGLE --105
EOperation ::= LANGLE_101_79_112_101_114_97_116_105_111_110_115 EOperation_8 RANGLE EOperation_3 LANGLE_SLASH_101_79_112_101_114_97_116_105_111_110_115_RANGLE --106
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

EOppositeAttribute ::= eOpposite EQUALS STRING --125

EPackage ::= LANGLE_101_80_97_99_107_97_103_101_115 SLASH_RANGLE --126
EPackage ::= LANGLE_101_80_97_99_107_97_103_101_115 EPackage_8 SLASH_RANGLE --127
EPackage ::= LANGLE_101_80_97_99_107_97_103_101_115 RANGLE LANGLE_SLASH_101_80_97_99_107_97_103_101_115_RANGLE --128
EPackage ::= LANGLE_101_80_97_99_107_97_103_101_115 EPackage_8 RANGLE LANGLE_SLASH_101_80_97_99_107_97_103_101_115_RANGLE --129
EPackage ::= LANGLE_101_80_97_99_107_97_103_101_115 RANGLE EPackage_3 LANGLE_SLASH_101_80_97_99_107_97_103_101_115_RANGLE --130
EPackage ::= LANGLE_101_80_97_99_107_97_103_101_115 EPackage_8 RANGLE EPackage_3 LANGLE_SLASH_101_80_97_99_107_97_103_101_115_RANGLE --131
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

EParameter ::= LANGLE_101_80_97_114_97_109_101_116_101_114_115 SLASH_RANGLE --150
EParameter ::= LANGLE_101_80_97_114_97_109_101_116_101_114_115 EParameter_8 SLASH_RANGLE --151
EParameter ::= LANGLE_101_80_97_114_97_109_101_116_101_114_115 RANGLE LANGLE_SLASH_101_80_97_114_97_109_101_116_101_114_115_RANGLE --152
EParameter ::= LANGLE_101_80_97_114_97_109_101_116_101_114_115 EParameter_8 RANGLE LANGLE_SLASH_101_80_97_114_97_109_101_116_101_114_115_RANGLE --153
EParameter ::= LANGLE_101_80_97_114_97_109_101_116_101_114_115 RANGLE EParameter_3 LANGLE_SLASH_101_80_97_114_97_109_101_116_101_114_115_RANGLE --154
EParameter ::= LANGLE_101_80_97_114_97_109_101_116_101_114_115 EParameter_8 RANGLE EParameter_3 LANGLE_SLASH_101_80_97_114_97_109_101_116_101_114_115_RANGLE --155
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

EStructuralFeature ::= LANGLE_101_83_116_114_117_99_116_117_114_97_108_70_101_97_116_117_114_101_115 SLASH_RANGLE --170
EStructuralFeature ::= LANGLE_101_83_116_114_117_99_116_117_114_97_108_70_101_97_116_117_114_101_115 EStructuralFeature_8 SLASH_RANGLE --171
EStructuralFeature ::= LANGLE_101_83_116_114_117_99_116_117_114_97_108_70_101_97_116_117_114_101_115 RANGLE LANGLE_SLASH_101_83_116_114_117_99_116_117_114_97_108_70_101_97_116_117_114_101_115_RANGLE --172
EStructuralFeature ::= LANGLE_101_83_116_114_117_99_116_117_114_97_108_70_101_97_116_117_114_101_115 EStructuralFeature_8 RANGLE LANGLE_SLASH_101_83_116_114_117_99_116_117_114_97_108_70_101_97_116_117_114_101_115_RANGLE --173
EStructuralFeature ::= LANGLE_101_83_116_114_117_99_116_117_114_97_108_70_101_97_116_117_114_101_115 RANGLE EStructuralFeature_3 LANGLE_SLASH_101_83_116_114_117_99_116_117_114_97_108_70_101_97_116_117_114_101_115_RANGLE --174
EStructuralFeature ::= LANGLE_101_83_116_114_117_99_116_117_114_97_108_70_101_97_116_117_114_101_115 EStructuralFeature_8 RANGLE EStructuralFeature_3 LANGLE_SLASH_101_83_116_114_117_99_116_117_114_97_108_70_101_97_116_117_114_101_115_RANGLE --175
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

ESuperTypeAttribute ::= eSuperTypes EQUALS STRING --214

ETypeArgument ::= LANGLE_101_84_121_112_101_65_114_103_117_109_101_110_116_115 SLASH_RANGLE --215
ETypeArgument ::= LANGLE_101_84_121_112_101_65_114_103_117_109_101_110_116_115 ETypeArgument_8 SLASH_RANGLE --216
ETypeArgument ::= LANGLE_101_84_121_112_101_65_114_103_117_109_101_110_116_115 RANGLE LANGLE_SLASH_101_84_121_112_101_65_114_103_117_109_101_110_116_115_RANGLE --217
ETypeArgument ::= LANGLE_101_84_121_112_101_65_114_103_117_109_101_110_116_115 ETypeArgument_8 RANGLE LANGLE_SLASH_101_84_121_112_101_65_114_103_117_109_101_110_116_115_RANGLE --218
ETypeArgument ::= LANGLE_101_84_121_112_101_65_114_103_117_109_101_110_116_115 RANGLE ETypeArgument_3 LANGLE_SLASH_101_84_121_112_101_65_114_103_117_109_101_110_116_115_RANGLE --219
ETypeArgument ::= LANGLE_101_84_121_112_101_65_114_103_117_109_101_110_116_115 ETypeArgument_8 RANGLE ETypeArgument_3 LANGLE_SLASH_101_84_121_112_101_65_114_103_117_109_101_110_116_115_RANGLE --220
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

ETypeAttribute ::= eType EQUALS STRING --231

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

InstanceClassNameAttribute ::= instanceClassName EQUALS STRING --260

IntegerAttribute ::= LowerBoundAttribute --261
IntegerAttribute ::= UpperBoundAttribute --262

KeyAttribute ::= key EQUALS STRING --263

LowerBoundAttribute ::= lowerBound EQUALS STRING --264

NameAttribute ::= name EQUALS STRING --265

NsPrefixAttribute ::= nsPrefix EQUALS STRING --266

NsURIAttribute ::= nsURI EQUALS STRING --267

OrderedAttribute ::= ordered EQUALS STRING --268

OtherAttribute ::= IDENTIFIER EQUALS STRING --269

OtherElement ::= LANGLE ID SLASH_RANGLE --270
OtherElement ::= LANGLE ID OtherElement_6 SLASH_RANGLE --271
OtherElement ::= LANGLE ID RANGLE LANGLE_SLASH ID RANGLE --272
OtherElement ::= LANGLE ID OtherElement_6 RANGLE LANGLE_SLASH ID RANGLE --273
OtherElement ::= LANGLE ID RANGLE OtherElement_2 LANGLE_SLASH ID RANGLE --274
OtherElement ::= LANGLE ID OtherElement_6 RANGLE OtherElement_2 LANGLE_SLASH ID RANGLE --275
OtherElement_2 ::= XmlElement --276
OtherElement_2 ::= OtherElement_2 XmlElement --277
OtherElement_6 ::= XmlAttribute --278
OtherElement_6 ::= OtherElement_6 XmlAttribute --279

ResolveProxiesAttribute ::= resolveProxies EQUALS STRING --280

RootEPackage ::= LANGLE_101_99_111_114_101_COLON_69_80_97_99_107_97_103_101 SLASH_RANGLE --281
RootEPackage ::= LANGLE_101_99_111_114_101_COLON_69_80_97_99_107_97_103_101 RootEPackage_8 SLASH_RANGLE --282
RootEPackage ::= LANGLE_101_99_111_114_101_COLON_69_80_97_99_107_97_103_101 RANGLE LANGLE_SLASH_101_99_111_114_101_COLON_69_80_97_99_107_97_103_101_RANGLE --283
RootEPackage ::= LANGLE_101_99_111_114_101_COLON_69_80_97_99_107_97_103_101 RootEPackage_8 RANGLE LANGLE_SLASH_101_99_111_114_101_COLON_69_80_97_99_107_97_103_101_RANGLE --284
RootEPackage ::= LANGLE_101_99_111_114_101_COLON_69_80_97_99_107_97_103_101 RANGLE RootEPackage_3 LANGLE_SLASH_101_99_111_114_101_COLON_69_80_97_99_107_97_103_101_RANGLE --285
RootEPackage ::= LANGLE_101_99_111_114_101_COLON_69_80_97_99_107_97_103_101 RootEPackage_8 RANGLE RootEPackage_3 LANGLE_SLASH_101_99_111_114_101_COLON_69_80_97_99_107_97_103_101_RANGLE --286
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

SerializableAttribute ::= serializable EQUALS STRING --307

SourceAttribute ::= source EQUALS STRING --308

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

TransientAttribute ::= transient EQUALS STRING --324

UnsettableAttribute ::= unsettable EQUALS STRING --325

UpperBoundAttribute ::= upperBound EQUALS STRING --326

ValueAttribute ::= value EQUALS STRING --327

VolatileAttribute ::= volatile EQUALS STRING --328

XmiVersionAttribute ::= xmi COLON version EQUALS STRING --329

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

XmlRoot ::= LANGLE_QUERY_120_109_108 QUERY_RANGLE RootEPackage --344
XmlRoot ::= LANGLE_QUERY_120_109_108 QUERY_RANGLE LANGLE_120_109_105_COLON_88_77_73 SLASH_RANGLE --345
XmlRoot ::= LANGLE_QUERY_120_109_108 QUERY_RANGLE LANGLE_120_109_105_COLON_88_77_73 XmlRoot_7 SLASH_RANGLE --346
XmlRoot ::= LANGLE_QUERY_120_109_108 QUERY_RANGLE LANGLE_120_109_105_COLON_88_77_73 RANGLE LANGLE_SLASH_120_109_105_COLON_88_77_73_RANGLE --347
XmlRoot ::= LANGLE_QUERY_120_109_108 QUERY_RANGLE LANGLE_120_109_105_COLON_88_77_73 XmlRoot_7 RANGLE LANGLE_SLASH_120_109_105_COLON_88_77_73_RANGLE --348
XmlRoot ::= LANGLE_QUERY_120_109_108 QUERY_RANGLE LANGLE_120_109_105_COLON_88_77_73 RANGLE XmlRoot_2 LANGLE_SLASH_120_109_105_COLON_88_77_73_RANGLE --349
XmlRoot ::= LANGLE_QUERY_120_109_108 encoding EQUALS STRING QUERY_RANGLE RootEPackage --350
XmlRoot ::= LANGLE_QUERY_120_109_108 version EQUALS STRING QUERY_RANGLE RootEPackage --351
XmlRoot ::= LANGLE_QUERY_120_109_108 QUERY_RANGLE LANGLE_120_109_105_COLON_88_77_73 XmlRoot_7 RANGLE XmlRoot_2 LANGLE_SLASH_120_109_105_COLON_88_77_73_RANGLE --352
XmlRoot ::= LANGLE_QUERY_120_109_108 encoding EQUALS STRING QUERY_RANGLE LANGLE_120_109_105_COLON_88_77_73 SLASH_RANGLE --353
XmlRoot ::= LANGLE_QUERY_120_109_108 version EQUALS STRING QUERY_RANGLE LANGLE_120_109_105_COLON_88_77_73 SLASH_RANGLE --354
XmlRoot ::= LANGLE_QUERY_120_109_108 encoding EQUALS STRING QUERY_RANGLE LANGLE_120_109_105_COLON_88_77_73 XmlRoot_7 SLASH_RANGLE --355
XmlRoot ::= LANGLE_QUERY_120_109_108 encoding EQUALS STRING QUERY_RANGLE LANGLE_120_109_105_COLON_88_77_73 RANGLE LANGLE_SLASH_120_109_105_COLON_88_77_73_RANGLE --356
XmlRoot ::= LANGLE_QUERY_120_109_108 version EQUALS STRING QUERY_RANGLE LANGLE_120_109_105_COLON_88_77_73 XmlRoot_7 SLASH_RANGLE --357
XmlRoot ::= LANGLE_QUERY_120_109_108 version EQUALS STRING QUERY_RANGLE LANGLE_120_109_105_COLON_88_77_73 RANGLE LANGLE_SLASH_120_109_105_COLON_88_77_73_RANGLE --358
XmlRoot ::= LANGLE_QUERY_120_109_108 encoding EQUALS STRING QUERY_RANGLE LANGLE_120_109_105_COLON_88_77_73 XmlRoot_7 RANGLE LANGLE_SLASH_120_109_105_COLON_88_77_73_RANGLE --359
XmlRoot ::= LANGLE_QUERY_120_109_108 encoding EQUALS STRING QUERY_RANGLE LANGLE_120_109_105_COLON_88_77_73 RANGLE XmlRoot_2 LANGLE_SLASH_120_109_105_COLON_88_77_73_RANGLE --360
XmlRoot ::= LANGLE_QUERY_120_109_108 version EQUALS STRING QUERY_RANGLE LANGLE_120_109_105_COLON_88_77_73 XmlRoot_7 RANGLE LANGLE_SLASH_120_109_105_COLON_88_77_73_RANGLE --361
XmlRoot ::= LANGLE_QUERY_120_109_108 version EQUALS STRING QUERY_RANGLE LANGLE_120_109_105_COLON_88_77_73 RANGLE XmlRoot_2 LANGLE_SLASH_120_109_105_COLON_88_77_73_RANGLE --362
XmlRoot ::= LANGLE_QUERY_120_109_108 version EQUALS STRING encoding EQUALS STRING QUERY_RANGLE RootEPackage --363
XmlRoot ::= LANGLE_QUERY_120_109_108 encoding EQUALS STRING QUERY_RANGLE LANGLE_120_109_105_COLON_88_77_73 XmlRoot_7 RANGLE XmlRoot_2 LANGLE_SLASH_120_109_105_COLON_88_77_73_RANGLE --364
XmlRoot ::= LANGLE_QUERY_120_109_108 version EQUALS STRING QUERY_RANGLE LANGLE_120_109_105_COLON_88_77_73 XmlRoot_7 RANGLE XmlRoot_2 LANGLE_SLASH_120_109_105_COLON_88_77_73_RANGLE --365
XmlRoot ::= LANGLE_QUERY_120_109_108 version EQUALS STRING encoding EQUALS STRING QUERY_RANGLE LANGLE_120_109_105_COLON_88_77_73 SLASH_RANGLE --366
XmlRoot ::= LANGLE_QUERY_120_109_108 version EQUALS STRING encoding EQUALS STRING QUERY_RANGLE LANGLE_120_109_105_COLON_88_77_73 XmlRoot_7 SLASH_RANGLE --367
XmlRoot ::= LANGLE_QUERY_120_109_108 version EQUALS STRING encoding EQUALS STRING QUERY_RANGLE LANGLE_120_109_105_COLON_88_77_73 RANGLE LANGLE_SLASH_120_109_105_COLON_88_77_73_RANGLE --368
XmlRoot ::= LANGLE_QUERY_120_109_108 version EQUALS STRING encoding EQUALS STRING QUERY_RANGLE LANGLE_120_109_105_COLON_88_77_73 XmlRoot_7 RANGLE LANGLE_SLASH_120_109_105_COLON_88_77_73_RANGLE --369
XmlRoot ::= LANGLE_QUERY_120_109_108 version EQUALS STRING encoding EQUALS STRING QUERY_RANGLE LANGLE_120_109_105_COLON_88_77_73 RANGLE XmlRoot_2 LANGLE_SLASH_120_109_105_COLON_88_77_73_RANGLE --370
XmlRoot ::= LANGLE_QUERY_120_109_108 version EQUALS STRING encoding EQUALS STRING QUERY_RANGLE LANGLE_120_109_105_COLON_88_77_73 XmlRoot_7 RANGLE XmlRoot_2 LANGLE_SLASH_120_109_105_COLON_88_77_73_RANGLE --371
XmlRoot_2 ::= XmlElement --372
XmlRoot_2 ::= XmlRoot_2 XmlElement --373
XmlRoot_7 ::= XmiVersionAttribute --374
XmlRoot_7 ::= XmlnsAttribute --375
XmlRoot_7 ::= XmlRoot_7 XmiVersionAttribute --376
XmlRoot_7 ::= XmlRoot_7 XmlnsAttribute --377

XmlnsAttribute ::= xmlns COLON ID EQUALS STRING --378

XsiTypeAttribute ::= xsi COLON type EQUALS STRING --379
%End
