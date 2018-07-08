/* A Bison parser, made by GNU Bison 2.4.2.  */

/* Skeleton interface for Bison's Yacc-like parsers in C
   
      Copyright (C) 1984, 1989-1990, 2000-2006, 2009-2010 Free Software
   Foundation, Inc.
   
   This program is free software: you can redistribute it and/or modify
   it under the terms of the GNU General Public License as published by
   the Free Software Foundation, either version 3 of the License, or
   (at your option) any later version.
   
   This program is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU General Public License for more details.
   
   You should have received a copy of the GNU General Public License
   along with this program.  If not, see <http://www.gnu.org/licenses/>.  */

/* As a special exception, you may create a larger work that contains
   part or all of the Bison parser skeleton and distribute that work
   under terms of your choice, so long as that work isn't itself a
   parser generator using the skeleton or a modified version thereof
   as a parser skeleton.  Alternatively, if you modify or redistribute
   the parser skeleton itself, you may (at your option) remove this
   special exception, which will cause the skeleton and the resulting
   Bison output files to be licensed under the GNU General Public
   License without this special exception.
   
   This special exception was added by the Free Software Foundation in
   version 2.2 of Bison.  */


/* Tokens.  */
#ifndef YYTOKENTYPE
# define YYTOKENTYPE
   /* Put the tokens into the symbol table, so that GDB and other debuggers
      know about them.  */
   enum yytokentype {
     IDENTIFIER = 258,
     STRING = 259,
     KWEAnnotation = 260,
     KWEAttribute = 261,
     KWEClass = 262,
     KWEClassifier = 263,
     KWEDataType = 264,
     KWEEnum = 265,
     KWEEnumLiteral = 266,
     KWEFactory = 267,
     KWEGenericType = 268,
     KWEModelElement = 269,
     KWENamedElement = 270,
     KWEObject = 271,
     KWEOperation = 272,
     KWEPackage = 273,
     KWEParameter = 274,
     KWEReference = 275,
     KWEStringToStringMapEntry = 276,
     KWEStructuralFeature = 277,
     KWETypeParameter = 278,
     KWETypedElement = 279,
     KWXMI = 280,
     KW_CO = 281,
     KW_EQ = 282,
     KW_GT = 283,
     KW_LT_QMxml = 284,
     KW_LT_SL = 285,
     KW_LT_SLcontents_GT = 286,
     KW_LT_SLdetails_GT = 287,
     KW_LT_SLeAnnotations_GT = 288,
     KW_LT_SLeBounds_GT = 289,
     KW_LT_SLeClassifiers_GT = 290,
     KW_LT_SLeGenericExceptions_GT = 291,
     KW_LT_SLeGenericSuperTypes_GT = 292,
     KW_LT_SLeLiterals_GT = 293,
     KW_LT_SLeLowerBound_GT = 294,
     KW_LT_SLeOperations_GT = 295,
     KW_LT_SLeParameters_GT = 296,
     KW_LT_SLeStructuralFeatures_GT = 297,
     KW_LT_SLeSubpackages_GT = 298,
     KW_LT_SLeTypeArguments_GT = 299,
     KW_LT_SLeTypeParameters_GT = 300,
     KW_LT_SLeUpperBound_GT = 301,
     KW_LT_SLxmi_COXMI_GT = 302,
     KW_LTcontents = 303,
     KW_LTdetails = 304,
     KW_LTeAnnotations = 305,
     KW_LTeBounds = 306,
     KW_LTeClassifiers = 307,
     KW_LTeGenericExceptions = 308,
     KW_LTeGenericSuperTypes = 309,
     KW_LTeLiterals = 310,
     KW_LTeLowerBound = 311,
     KW_LTeOperations = 312,
     KW_LTeParameters = 313,
     KW_LTeStructuralFeatures = 314,
     KW_LTeSubpackages = 315,
     KW_LTeTypeArguments = 316,
     KW_LTeTypeParameters = 317,
     KW_LTeUpperBound = 318,
     KW_LTecore = 319,
     KW_LTxmi = 320,
     KW_LTxmi_COXMI = 321,
     KW_QM_GT = 322,
     KW_SL_GT = 323,
     KWabstract = 324,
     KWchangeable = 325,
     KWcontainment = 326,
     KWdefaultValueLiteral = 327,
     KWderived = 328,
     KWeClassifier = 329,
     KWeExceptions = 330,
     KWeKeys = 331,
     KWeOpposite = 332,
     KWeSuperTypes = 333,
     KWeTypeParameter = 334,
     KWencoding = 335,
     KWiD = 336,
     KWinterface = 337,
     KWkey = 338,
     KWliteral = 339,
     KWlowerBound = 340,
     KWname = 341,
     KWnsPrefix = 342,
     KWnsURI = 343,
     KWordered = 344,
     KWreferences = 345,
     KWresolveProxies = 346,
     KWserializable = 347,
     KWsource = 348,
     KWtransient = 349,
     KWtype = 350,
     KWunique = 351,
     KWunsettable = 352,
     KWupperBound = 353,
     KWvalue = 354,
     KWversion = 355,
     KWvolatile = 356,
     KWxmi = 357,
     KWxsi = 358,
     NS_ecore = 359,
     NS_xmi = 360,
     NS_xmlns = 361,
     LT_NS_ecore = 362,
     LT_NS_xmi = 363,
     LT_NS_xmlns = 364
   };
#endif



#if ! defined YYSTYPE && ! defined YYSTYPE_IS_DECLARED
typedef int YYSTYPE;
# define YYSTYPE_IS_TRIVIAL 1
# define yystype YYSTYPE /* obsolescent; will be withdrawn */
# define YYSTYPE_IS_DECLARED 1
#endif

extern YYSTYPE yylval;


