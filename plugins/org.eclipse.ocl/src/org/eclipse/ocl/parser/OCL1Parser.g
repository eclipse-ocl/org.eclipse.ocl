--/**
-- * <copyright>
-- *
-- * Copyright (c) 2001, 2020 OMG and others.
-- * All rights reserved.   This program and the accompanying materials
-- * are made available under the terms of the Eclipse Public License v2.0
-- * which accompanies this distribution, and is available at
-- * http://www.eclipse.org/legal/epl-v20.html
-- *
-- * Contributors:
-- *   See (or edit) Notice Declaration below
-- *
-- * </copyright>
-- */
--
-- The Complete OCL Parser
--

%options escape=$
%options la=1
%options fp=OCLParser,prefix=TK_
%options noserialize
%options package=org.eclipse.ocl.parser
%options import_terminals=OCLLexer.gi
%options ast_type=CSTNode
%options template=dtParserTemplateF.gi
%options include_directory=".;../lpg"

%Start
	goal
%End

%Import
	EssentialOCL.gi
%End

%Notice
	/./**
 * Complete OCL Grammar
 * <copyright>
 *
 * Copyright (c) 2001, 2020 OMG and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   OMG - Initial API and implementation
 *   E.D.Willink - Make OMG UML 1.4 grammar yaccable
 *******************************************************************************/
	./
%End

%Define
    $environment_class /.Environment<?,?,?,?,?,?,?,?,?,?,?,?>./
	$super_lexer_class /.OCLLexer./
%End

%Globals
	/.
	import org.eclipse.ocl.Environment;
	import org.eclipse.ocl.cst.ClassifierContextDeclCS;
	import org.eclipse.ocl.cst.ContextDeclCS;
	import org.eclipse.ocl.cst.DefCS;
	import org.eclipse.ocl.cst.DefExpressionCS;
	import org.eclipse.ocl.cst.DerValueCS;
	import org.eclipse.ocl.cst.InitValueCS;
	import org.eclipse.ocl.cst.InitOrDerValueCS;
	import org.eclipse.ocl.cst.InvCS;
	import org.eclipse.ocl.cst.InvOrDefCS;
	import org.eclipse.ocl.cst.MessageExpCS;
	import org.eclipse.ocl.cst.OCLMessageArgCS;
	import org.eclipse.ocl.cst.OperationCS;
	import org.eclipse.ocl.cst.OperationContextDeclCS;
	import org.eclipse.ocl.cst.PackageDeclarationCS;
	import org.eclipse.ocl.cst.PrePostOrBodyDeclCS;
	import org.eclipse.ocl.cst.PrePostOrBodyEnum;
	import org.eclipse.ocl.cst.PropertyContextCS;
	./
%End

%KeyWords
-- Reserved keywords
	body context def derive endpackage init inv package post pre static

-- Restricted keywords
	OclMessage import
%End

%Terminals
	AT           ::= '@'
	CARET        ::= '^'
	CARETCARET   ::= '^^'
	QUESTIONMARK ::= '?'
%End

%Rules
--
-- This yaccable copy of the UML 1.4 OCL grammar fixes minor problems
--
-- EBNF *,+,? are flattened
--
-- A bad letExpression* in oclExpression is corrected
-- Litertal grammar re-use the Eclipse OCL literals
-- Gratutous oathnames are suppressed
--
-- The catastrophic shift-reduce iterator declaratpr conflicy is suppressed by a '|' prefix.
--
goal -> oclFile
oclFile -> oclPackage
oclFile ::= oclFile oclPackage

oclPackage ::= 'package' packageName oclExpressions 'endpackage'

packageName ::= pathName

oclExpressions -> constraint
oclExpressions ::= oclExpressions constraint

constraint1 ::= 'def' ':' letExpression
constraint1 ::= 'def' name ':' letExpression
constraint1 ::= constraint1 letExpression
constraint2 ::= stereotype ':' oclExpression
constraint2 ::= stereotype name ':' oclExpression
constraint ::= contextDeclaration constraint1
constraint ::= contextDeclaration constraint2
constraint ::= constraint constraint1
constraint ::= constraint constraint2

contextDeclaration ::= 'context' operationContext
contextDeclaration ::= 'context' classifierContext

classifierContext ::= name
classifierContext ::= name ':' name

operationContext -> operationContext2
operationContext ::= operationContext2 ':' returnType
operationContext2 ::= name '::' operationName '(' ')'
operationContext2 ::= name '::' operationName '(' formalParameterList ')'

stereotype ::= 'pre'
stereotype ::= 'post'
stereotype ::= 'inv'

operationName ::= name | '=' | '+' | '-' | '<' | '<=' | '>=' | '>' | '/' | '*' | '<>' | 'implies' | 'not' | 'or' | 'xor' | 'and'

formalParameterList -> name ':' typeSpecifier
formalParameterList ::= formalParameterList ',' name ':' typeSpecifier

typeSpecifier ::= simpleTypeSpecifier | collectionType

collectionType ::= collectionKind '(' simpleTypeSpecifier ')'

oclExpression1 -> letExpression
oclExpression1 ::= oclExpression1 letExpression
oclExpression -> expression
oclExpression ::= oclExpression1 'in' expression

returnType ::= typeSpecifier
expression ::= logicalExpression

letExpression1 ::= 'let' name
letExpression2 -> letExpression1
letExpression2 ::= letExpression1 '(' ')'
letExpression2 ::= letExpression1 '(' formalParameterList ')'
letExpression3 -> letExpression2
letExpression3 ::= letExpression2 ':' typeSpecifier
letExpression ::= letExpression3 '=' expression

ifExpression ::= 'if' expression 'then' expression 'else' expression 'endif'

logicalExpression -> relationalExpression
logicalExpression ::= logicalExpression logicalOperator relationalExpression

relationalExpression -> additiveExpression
relationalExpression ::= relationalExpression relationalOperator additiveExpression

additiveExpression -> multiplicativeExpression
additiveExpression ::= additiveExpression addOperator multiplicativeExpression

multiplicativeExpression -> unaryExpression
multiplicativeExpression ::= multiplicativeExpression multiplyOperator unaryExpression

unaryExpression -> postfixExpression
unaryExpression ::= unaryOperator postfixExpression

postfixExpression -> primaryExpression
postfixExpression ::= postfixExpression '.' propertyCall
postfixExpression ::= postfixExpression '->' propertyCall

primaryExpression -> literalCollection
primaryExpression -> literal
primaryExpression -> propertyCall
primaryExpression ::= '(' expression ')'
primaryExpression -> ifExpression

propertyCallParameters2 -> '('
propertyCallParameters2 ::= '(' declarator
propertyCallParameters3 -> propertyCallParameters2
propertyCallParameters3 ::= propertyCallParameters2 actualParameterList
propertyCallParameters ::= propertyCallParameters3 ')'

literal ::= string
literal ::= number
-- literal ::= enumLiteral

-- enumLiteral ::= name '::' name

simpleTypeSpecifier ::= pathName

literalCollection0 ::= collectionKind '{'
literalCollection1 ::= literalCollection0 collectionItem
literalCollection2 -> literalCollection1
literalCollection2 ::= literalCollection1 ',' collectionItem
literalCollection ::= literalCollection0 '}'
literalCollection ::= literalCollection2 '}'

collectionItem -> expression
collectionItem ::= collectionItem '..' expression

propertyCall1 -> pathName
propertyCall2 -> propertyCall1
propertyCall2 ::= propertyCall1 timeExpression
propertyCall3 -> propertyCall2
propertyCall3 ::= propertyCall2 qualifiers
propertyCall -> propertyCall3
propertyCall ::= propertyCall3 propertyCallParameters

qualifiers ::= '[' actualParameterList ']'

declarator1 ::= '|' name
declarator1 ::= declarator1 ',' name
declarator2 -> declarator1
declarator2 ::= declarator1 ':' simpleTypeSpecifier
declarator3 -> declarator2
declarator3 ::= declarator2 ';' name ':' typeSpecifier '=' expression
declarator ::= declarator3 '|'

pathName -> name
pathName ::= pathName '::' name

timeExpression ::= '@' 'pre'

actualParameterList -> expression
actualParameterList ::= actualParameterList ',' expression

logicalOperator ::= 'and' | 'or' | 'xor' | 'implies'

collectionKind ::= 'Set' | 'Bag' | 'Sequence' | 'Collection'

relationalOperator ::= '=' | '>' | '<' | '>=' | '<=' | '<>'

addOperator ::= '+' | '-'

multiplyOperator ::= '*' | '/'

unaryOperator ::= '-' | 'not'

-- typeName ::= IDENTIFIER
name ::= IDENTIFIER
-- charForNameTop ::= IDENTIFIER
-- charForName ::= IDENTIFIER
number ::= INTEGER_LITERAL
string ::= STRING_LITERAL
%End
