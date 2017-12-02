/*******************************************************************************
 *************************************************************************
 * This code is 100% auto-generated
 * from:
 *   /org.eclipse.ocl.examples.xtext2lpg/model/XBNF.ecore
 *   /org.eclipse.ocl.examples.xtext2lpg/model/XBNFwithCardinality.ecore
 * using:
 *   /org.eclipse.ocl.examples.xtext2lpg/model/XBNFwithCardinality.genmodel
 *   org.eclipse.ocl.examples.codegen.oclinecore.OCLinEcoreTables
 *
 * Do not edit it.
 *******************************************************************************/
package org.eclipse.ocl.examples.xtext2lpg.XBNF;

import org.eclipse.ocl.examples.xtext2lpg.XBNF.XBNFTables;
import org.eclipse.ocl.pivot.internal.library.ecore.EcoreExecutorPackage;
import org.eclipse.ocl.pivot.internal.library.ecore.EcoreExecutorProperty;
import org.eclipse.ocl.pivot.internal.library.ecore.EcoreExecutorType;
import org.eclipse.ocl.pivot.internal.library.ecore.EcoreLibraryOppositeProperty;
import org.eclipse.ocl.pivot.internal.library.executor.ExecutorFragment;
import org.eclipse.ocl.pivot.internal.library.executor.ExecutorOperation;
import org.eclipse.ocl.pivot.internal.library.executor.ExecutorProperty;
import org.eclipse.ocl.pivot.internal.library.executor.ExecutorPropertyWithImplementation;
import org.eclipse.ocl.pivot.internal.library.executor.ExecutorStandardLibrary;
import org.eclipse.ocl.pivot.internal.library.executor.ExecutorType;
import org.eclipse.ocl.pivot.oclstdlib.OCLstdlibTables;

/**
 * XBNFTables provides the dispatch tables for the XBNF for use by the OCL dispatcher.
 *
 * In order to ensure correct static initialization, a top level class element must be accessed
 * before any nested class element. Therefore an access to PACKAGE.getClass() is recommended.
 */
@SuppressWarnings("nls")
public class XBNFTables
{
	static {
		Init.initStart();
	}

	/**
	 *	The package descriptor for the package.
	 */
	public static final /*@NonNull*/ EcoreExecutorPackage PACKAGE = new EcoreExecutorPackage(XBNFPackage.eINSTANCE);

	/**
	 *	The library of all packages and types.
	 */
	public static final /*@NonNull*/ ExecutorStandardLibrary LIBRARY = OCLstdlibTables.LIBRARY;

	/**
	 *	The type parameters for templated types and operations.
	 */
	public static class TypeParameters {
		static {
			Init.initStart();
			XBNFTables.init();
		}

		static {
			Init.initEnd();
		}

		/**
		 * Force initialization of the fields of XBNFTables::TypeParameters and all preceding sub-packages.
		 */
		public static void init() {}
	}

	/**
	 *	The type descriptors for each type.
	 */
	public static class Types {
		static {
			Init.initStart();
			TypeParameters.init();
		}

		public static final /*@NonNull*/ EcoreExecutorType _AbstractElement = new EcoreExecutorType(XBNFPackage.Literals.ABSTRACT_ELEMENT, PACKAGE, 0 | ExecutorType.ABSTRACT);
		public static final /*@NonNull*/ EcoreExecutorType _AbstractRule = new EcoreExecutorType(XBNFPackage.Literals.ABSTRACT_RULE, PACKAGE, 0);
		public static final /*@NonNull*/ EcoreExecutorType _ActionAssignment = new EcoreExecutorType(XBNFPackage.Literals.ACTION_ASSIGNMENT, PACKAGE, 0);
		public static final /*@NonNull*/ EcoreExecutorType _Assignment = new EcoreExecutorType(XBNFPackage.Literals.ASSIGNMENT, PACKAGE, 0 | ExecutorType.ABSTRACT);
		public static final /*@NonNull*/ EcoreExecutorType _CharacterRange = new EcoreExecutorType(XBNFPackage.Literals.CHARACTER_RANGE, PACKAGE, 0);
		public static final /*@NonNull*/ EcoreExecutorType _Conjunction = new EcoreExecutorType(XBNFPackage.Literals.CONJUNCTION, PACKAGE, 0);
		public static final /*@NonNull*/ EcoreExecutorType _Disjunction = new EcoreExecutorType(XBNFPackage.Literals.DISJUNCTION, PACKAGE, 0);
		public static final /*@NonNull*/ EcoreExecutorType _EOF = new EcoreExecutorType(XBNFPackage.Literals.EOF, PACKAGE, 0);
		public static final /*@NonNull*/ EcoreExecutorType _Epsilon = new EcoreExecutorType(XBNFPackage.Literals.EPSILON, PACKAGE, 0);
		public static final /*@NonNull*/ EcoreExecutorType _Grammar = new EcoreExecutorType(XBNFPackage.Literals.GRAMMAR, PACKAGE, 0);
		public static final /*@NonNull*/ EcoreExecutorType _Keyword = new EcoreExecutorType(XBNFPackage.Literals.KEYWORD, PACKAGE, 0);
		public static final /*@NonNull*/ EcoreExecutorType _KeywordAssignment = new EcoreExecutorType(XBNFPackage.Literals.KEYWORD_ASSIGNMENT, PACKAGE, 0);
		public static final /*@NonNull*/ EcoreExecutorType _LexerGrammar = new EcoreExecutorType(XBNFPackage.Literals.LEXER_GRAMMAR, PACKAGE, 0);
		public static final /*@NonNull*/ EcoreExecutorType _MetamodelDeclaration = new EcoreExecutorType(XBNFPackage.Literals.METAMODEL_DECLARATION, PACKAGE, 0);
		public static final /*@NonNull*/ EcoreExecutorType _NegatedToken = new EcoreExecutorType(XBNFPackage.Literals.NEGATED_TOKEN, PACKAGE, 0);
		public static final /*@NonNull*/ EcoreExecutorType _ParserGrammar = new EcoreExecutorType(XBNFPackage.Literals.PARSER_GRAMMAR, PACKAGE, 0);
		public static final /*@NonNull*/ EcoreExecutorType _ParserRule = new EcoreExecutorType(XBNFPackage.Literals.PARSER_RULE, PACKAGE, 0);
		public static final /*@NonNull*/ EcoreExecutorType _RuleCall = new EcoreExecutorType(XBNFPackage.Literals.RULE_CALL, PACKAGE, 0);
		public static final /*@NonNull*/ EcoreExecutorType _RuleCallAssignment = new EcoreExecutorType(XBNFPackage.Literals.RULE_CALL_ASSIGNMENT, PACKAGE, 0);
		public static final /*@NonNull*/ EcoreExecutorType _Syntax = new EcoreExecutorType(XBNFPackage.Literals.SYNTAX, PACKAGE, 0);
		public static final /*@NonNull*/ EcoreExecutorType _TerminalRule = new EcoreExecutorType(XBNFPackage.Literals.TERMINAL_RULE, PACKAGE, 0);
		public static final /*@NonNull*/ EcoreExecutorType _TypedRule = new EcoreExecutorType(XBNFPackage.Literals.TYPED_RULE, PACKAGE, 0 | ExecutorType.ABSTRACT);
		public static final /*@NonNull*/ EcoreExecutorType _UntilToken = new EcoreExecutorType(XBNFPackage.Literals.UNTIL_TOKEN, PACKAGE, 0);
		public static final /*@NonNull*/ EcoreExecutorType _UntypedRule = new EcoreExecutorType(XBNFPackage.Literals.UNTYPED_RULE, PACKAGE, 0);
		public static final /*@NonNull*/ EcoreExecutorType _Wildcard = new EcoreExecutorType(XBNFPackage.Literals.WILDCARD, PACKAGE, 0);

		private static final /*@NonNull*/ EcoreExecutorType /*@NonNull*/ [] types = {
			_AbstractElement,
			_AbstractRule,
			_ActionAssignment,
			_Assignment,
			_CharacterRange,
			_Conjunction,
			_Disjunction,
			_EOF,
			_Epsilon,
			_Grammar,
			_Keyword,
			_KeywordAssignment,
			_LexerGrammar,
			_MetamodelDeclaration,
			_NegatedToken,
			_ParserGrammar,
			_ParserRule,
			_RuleCall,
			_RuleCallAssignment,
			_Syntax,
			_TerminalRule,
			_TypedRule,
			_UntilToken,
			_UntypedRule,
			_Wildcard
		};

		/*
		 *	Install the type descriptors in the package descriptor.
		 */
		static {
			PACKAGE.init(LIBRARY, types);
			Init.initEnd();
		}

		/**
		 * Force initialization of the fields of XBNFTables::Types and all preceding sub-packages.
		 */
		public static void init() {}
	}

	/**
	 *	The fragment descriptors for the local elements of each type and its supertypes.
	 */
	public static class Fragments {
		static {
			Init.initStart();
			Types.init();
		}

		private static final /*@NonNull*/ ExecutorFragment _AbstractElement__AbstractElement = new ExecutorFragment(Types._AbstractElement, XBNFTables.Types._AbstractElement);
		private static final /*@NonNull*/ ExecutorFragment _AbstractElement__OclAny = new ExecutorFragment(Types._AbstractElement, OCLstdlibTables.Types._OclAny);
		private static final /*@NonNull*/ ExecutorFragment _AbstractElement__OclElement = new ExecutorFragment(Types._AbstractElement, OCLstdlibTables.Types._OclElement);

		private static final /*@NonNull*/ ExecutorFragment _AbstractRule__AbstractRule = new ExecutorFragment(Types._AbstractRule, XBNFTables.Types._AbstractRule);
		private static final /*@NonNull*/ ExecutorFragment _AbstractRule__OclAny = new ExecutorFragment(Types._AbstractRule, OCLstdlibTables.Types._OclAny);
		private static final /*@NonNull*/ ExecutorFragment _AbstractRule__OclElement = new ExecutorFragment(Types._AbstractRule, OCLstdlibTables.Types._OclElement);

		private static final /*@NonNull*/ ExecutorFragment _ActionAssignment__AbstractElement = new ExecutorFragment(Types._ActionAssignment, XBNFTables.Types._AbstractElement);
		private static final /*@NonNull*/ ExecutorFragment _ActionAssignment__ActionAssignment = new ExecutorFragment(Types._ActionAssignment, XBNFTables.Types._ActionAssignment);
		private static final /*@NonNull*/ ExecutorFragment _ActionAssignment__Assignment = new ExecutorFragment(Types._ActionAssignment, XBNFTables.Types._Assignment);
		private static final /*@NonNull*/ ExecutorFragment _ActionAssignment__OclAny = new ExecutorFragment(Types._ActionAssignment, OCLstdlibTables.Types._OclAny);
		private static final /*@NonNull*/ ExecutorFragment _ActionAssignment__OclElement = new ExecutorFragment(Types._ActionAssignment, OCLstdlibTables.Types._OclElement);

		private static final /*@NonNull*/ ExecutorFragment _Assignment__AbstractElement = new ExecutorFragment(Types._Assignment, XBNFTables.Types._AbstractElement);
		private static final /*@NonNull*/ ExecutorFragment _Assignment__Assignment = new ExecutorFragment(Types._Assignment, XBNFTables.Types._Assignment);
		private static final /*@NonNull*/ ExecutorFragment _Assignment__OclAny = new ExecutorFragment(Types._Assignment, OCLstdlibTables.Types._OclAny);
		private static final /*@NonNull*/ ExecutorFragment _Assignment__OclElement = new ExecutorFragment(Types._Assignment, OCLstdlibTables.Types._OclElement);

		private static final /*@NonNull*/ ExecutorFragment _CharacterRange__AbstractElement = new ExecutorFragment(Types._CharacterRange, XBNFTables.Types._AbstractElement);
		private static final /*@NonNull*/ ExecutorFragment _CharacterRange__CharacterRange = new ExecutorFragment(Types._CharacterRange, XBNFTables.Types._CharacterRange);
		private static final /*@NonNull*/ ExecutorFragment _CharacterRange__OclAny = new ExecutorFragment(Types._CharacterRange, OCLstdlibTables.Types._OclAny);
		private static final /*@NonNull*/ ExecutorFragment _CharacterRange__OclElement = new ExecutorFragment(Types._CharacterRange, OCLstdlibTables.Types._OclElement);

		private static final /*@NonNull*/ ExecutorFragment _Conjunction__AbstractElement = new ExecutorFragment(Types._Conjunction, XBNFTables.Types._AbstractElement);
		private static final /*@NonNull*/ ExecutorFragment _Conjunction__Conjunction = new ExecutorFragment(Types._Conjunction, XBNFTables.Types._Conjunction);
		private static final /*@NonNull*/ ExecutorFragment _Conjunction__OclAny = new ExecutorFragment(Types._Conjunction, OCLstdlibTables.Types._OclAny);
		private static final /*@NonNull*/ ExecutorFragment _Conjunction__OclElement = new ExecutorFragment(Types._Conjunction, OCLstdlibTables.Types._OclElement);

		private static final /*@NonNull*/ ExecutorFragment _Disjunction__AbstractElement = new ExecutorFragment(Types._Disjunction, XBNFTables.Types._AbstractElement);
		private static final /*@NonNull*/ ExecutorFragment _Disjunction__Disjunction = new ExecutorFragment(Types._Disjunction, XBNFTables.Types._Disjunction);
		private static final /*@NonNull*/ ExecutorFragment _Disjunction__OclAny = new ExecutorFragment(Types._Disjunction, OCLstdlibTables.Types._OclAny);
		private static final /*@NonNull*/ ExecutorFragment _Disjunction__OclElement = new ExecutorFragment(Types._Disjunction, OCLstdlibTables.Types._OclElement);

		private static final /*@NonNull*/ ExecutorFragment _EOF__AbstractElement = new ExecutorFragment(Types._EOF, XBNFTables.Types._AbstractElement);
		private static final /*@NonNull*/ ExecutorFragment _EOF__EOF = new ExecutorFragment(Types._EOF, XBNFTables.Types._EOF);
		private static final /*@NonNull*/ ExecutorFragment _EOF__OclAny = new ExecutorFragment(Types._EOF, OCLstdlibTables.Types._OclAny);
		private static final /*@NonNull*/ ExecutorFragment _EOF__OclElement = new ExecutorFragment(Types._EOF, OCLstdlibTables.Types._OclElement);

		private static final /*@NonNull*/ ExecutorFragment _Epsilon__AbstractElement = new ExecutorFragment(Types._Epsilon, XBNFTables.Types._AbstractElement);
		private static final /*@NonNull*/ ExecutorFragment _Epsilon__Epsilon = new ExecutorFragment(Types._Epsilon, XBNFTables.Types._Epsilon);
		private static final /*@NonNull*/ ExecutorFragment _Epsilon__OclAny = new ExecutorFragment(Types._Epsilon, OCLstdlibTables.Types._OclAny);
		private static final /*@NonNull*/ ExecutorFragment _Epsilon__OclElement = new ExecutorFragment(Types._Epsilon, OCLstdlibTables.Types._OclElement);

		private static final /*@NonNull*/ ExecutorFragment _Grammar__Grammar = new ExecutorFragment(Types._Grammar, XBNFTables.Types._Grammar);
		private static final /*@NonNull*/ ExecutorFragment _Grammar__OclAny = new ExecutorFragment(Types._Grammar, OCLstdlibTables.Types._OclAny);
		private static final /*@NonNull*/ ExecutorFragment _Grammar__OclElement = new ExecutorFragment(Types._Grammar, OCLstdlibTables.Types._OclElement);

		private static final /*@NonNull*/ ExecutorFragment _Keyword__AbstractElement = new ExecutorFragment(Types._Keyword, XBNFTables.Types._AbstractElement);
		private static final /*@NonNull*/ ExecutorFragment _Keyword__Keyword = new ExecutorFragment(Types._Keyword, XBNFTables.Types._Keyword);
		private static final /*@NonNull*/ ExecutorFragment _Keyword__OclAny = new ExecutorFragment(Types._Keyword, OCLstdlibTables.Types._OclAny);
		private static final /*@NonNull*/ ExecutorFragment _Keyword__OclElement = new ExecutorFragment(Types._Keyword, OCLstdlibTables.Types._OclElement);

		private static final /*@NonNull*/ ExecutorFragment _KeywordAssignment__AbstractElement = new ExecutorFragment(Types._KeywordAssignment, XBNFTables.Types._AbstractElement);
		private static final /*@NonNull*/ ExecutorFragment _KeywordAssignment__Assignment = new ExecutorFragment(Types._KeywordAssignment, XBNFTables.Types._Assignment);
		private static final /*@NonNull*/ ExecutorFragment _KeywordAssignment__Keyword = new ExecutorFragment(Types._KeywordAssignment, XBNFTables.Types._Keyword);
		private static final /*@NonNull*/ ExecutorFragment _KeywordAssignment__KeywordAssignment = new ExecutorFragment(Types._KeywordAssignment, XBNFTables.Types._KeywordAssignment);
		private static final /*@NonNull*/ ExecutorFragment _KeywordAssignment__OclAny = new ExecutorFragment(Types._KeywordAssignment, OCLstdlibTables.Types._OclAny);
		private static final /*@NonNull*/ ExecutorFragment _KeywordAssignment__OclElement = new ExecutorFragment(Types._KeywordAssignment, OCLstdlibTables.Types._OclElement);

		private static final /*@NonNull*/ ExecutorFragment _LexerGrammar__Grammar = new ExecutorFragment(Types._LexerGrammar, XBNFTables.Types._Grammar);
		private static final /*@NonNull*/ ExecutorFragment _LexerGrammar__LexerGrammar = new ExecutorFragment(Types._LexerGrammar, XBNFTables.Types._LexerGrammar);
		private static final /*@NonNull*/ ExecutorFragment _LexerGrammar__OclAny = new ExecutorFragment(Types._LexerGrammar, OCLstdlibTables.Types._OclAny);
		private static final /*@NonNull*/ ExecutorFragment _LexerGrammar__OclElement = new ExecutorFragment(Types._LexerGrammar, OCLstdlibTables.Types._OclElement);

		private static final /*@NonNull*/ ExecutorFragment _MetamodelDeclaration__MetamodelDeclaration = new ExecutorFragment(Types._MetamodelDeclaration, XBNFTables.Types._MetamodelDeclaration);
		private static final /*@NonNull*/ ExecutorFragment _MetamodelDeclaration__OclAny = new ExecutorFragment(Types._MetamodelDeclaration, OCLstdlibTables.Types._OclAny);
		private static final /*@NonNull*/ ExecutorFragment _MetamodelDeclaration__OclElement = new ExecutorFragment(Types._MetamodelDeclaration, OCLstdlibTables.Types._OclElement);

		private static final /*@NonNull*/ ExecutorFragment _NegatedToken__AbstractElement = new ExecutorFragment(Types._NegatedToken, XBNFTables.Types._AbstractElement);
		private static final /*@NonNull*/ ExecutorFragment _NegatedToken__NegatedToken = new ExecutorFragment(Types._NegatedToken, XBNFTables.Types._NegatedToken);
		private static final /*@NonNull*/ ExecutorFragment _NegatedToken__OclAny = new ExecutorFragment(Types._NegatedToken, OCLstdlibTables.Types._OclAny);
		private static final /*@NonNull*/ ExecutorFragment _NegatedToken__OclElement = new ExecutorFragment(Types._NegatedToken, OCLstdlibTables.Types._OclElement);

		private static final /*@NonNull*/ ExecutorFragment _ParserGrammar__Grammar = new ExecutorFragment(Types._ParserGrammar, XBNFTables.Types._Grammar);
		private static final /*@NonNull*/ ExecutorFragment _ParserGrammar__OclAny = new ExecutorFragment(Types._ParserGrammar, OCLstdlibTables.Types._OclAny);
		private static final /*@NonNull*/ ExecutorFragment _ParserGrammar__OclElement = new ExecutorFragment(Types._ParserGrammar, OCLstdlibTables.Types._OclElement);
		private static final /*@NonNull*/ ExecutorFragment _ParserGrammar__ParserGrammar = new ExecutorFragment(Types._ParserGrammar, XBNFTables.Types._ParserGrammar);

		private static final /*@NonNull*/ ExecutorFragment _ParserRule__AbstractRule = new ExecutorFragment(Types._ParserRule, XBNFTables.Types._AbstractRule);
		private static final /*@NonNull*/ ExecutorFragment _ParserRule__OclAny = new ExecutorFragment(Types._ParserRule, OCLstdlibTables.Types._OclAny);
		private static final /*@NonNull*/ ExecutorFragment _ParserRule__OclElement = new ExecutorFragment(Types._ParserRule, OCLstdlibTables.Types._OclElement);
		private static final /*@NonNull*/ ExecutorFragment _ParserRule__ParserRule = new ExecutorFragment(Types._ParserRule, XBNFTables.Types._ParserRule);
		private static final /*@NonNull*/ ExecutorFragment _ParserRule__TypedRule = new ExecutorFragment(Types._ParserRule, XBNFTables.Types._TypedRule);

		private static final /*@NonNull*/ ExecutorFragment _RuleCall__AbstractElement = new ExecutorFragment(Types._RuleCall, XBNFTables.Types._AbstractElement);
		private static final /*@NonNull*/ ExecutorFragment _RuleCall__OclAny = new ExecutorFragment(Types._RuleCall, OCLstdlibTables.Types._OclAny);
		private static final /*@NonNull*/ ExecutorFragment _RuleCall__OclElement = new ExecutorFragment(Types._RuleCall, OCLstdlibTables.Types._OclElement);
		private static final /*@NonNull*/ ExecutorFragment _RuleCall__RuleCall = new ExecutorFragment(Types._RuleCall, XBNFTables.Types._RuleCall);

		private static final /*@NonNull*/ ExecutorFragment _RuleCallAssignment__AbstractElement = new ExecutorFragment(Types._RuleCallAssignment, XBNFTables.Types._AbstractElement);
		private static final /*@NonNull*/ ExecutorFragment _RuleCallAssignment__Assignment = new ExecutorFragment(Types._RuleCallAssignment, XBNFTables.Types._Assignment);
		private static final /*@NonNull*/ ExecutorFragment _RuleCallAssignment__OclAny = new ExecutorFragment(Types._RuleCallAssignment, OCLstdlibTables.Types._OclAny);
		private static final /*@NonNull*/ ExecutorFragment _RuleCallAssignment__OclElement = new ExecutorFragment(Types._RuleCallAssignment, OCLstdlibTables.Types._OclElement);
		private static final /*@NonNull*/ ExecutorFragment _RuleCallAssignment__RuleCall = new ExecutorFragment(Types._RuleCallAssignment, XBNFTables.Types._RuleCall);
		private static final /*@NonNull*/ ExecutorFragment _RuleCallAssignment__RuleCallAssignment = new ExecutorFragment(Types._RuleCallAssignment, XBNFTables.Types._RuleCallAssignment);

		private static final /*@NonNull*/ ExecutorFragment _Syntax__OclAny = new ExecutorFragment(Types._Syntax, OCLstdlibTables.Types._OclAny);
		private static final /*@NonNull*/ ExecutorFragment _Syntax__OclElement = new ExecutorFragment(Types._Syntax, OCLstdlibTables.Types._OclElement);
		private static final /*@NonNull*/ ExecutorFragment _Syntax__Syntax = new ExecutorFragment(Types._Syntax, XBNFTables.Types._Syntax);

		private static final /*@NonNull*/ ExecutorFragment _TerminalRule__AbstractRule = new ExecutorFragment(Types._TerminalRule, XBNFTables.Types._AbstractRule);
		private static final /*@NonNull*/ ExecutorFragment _TerminalRule__OclAny = new ExecutorFragment(Types._TerminalRule, OCLstdlibTables.Types._OclAny);
		private static final /*@NonNull*/ ExecutorFragment _TerminalRule__OclElement = new ExecutorFragment(Types._TerminalRule, OCLstdlibTables.Types._OclElement);
		private static final /*@NonNull*/ ExecutorFragment _TerminalRule__TerminalRule = new ExecutorFragment(Types._TerminalRule, XBNFTables.Types._TerminalRule);
		private static final /*@NonNull*/ ExecutorFragment _TerminalRule__TypedRule = new ExecutorFragment(Types._TerminalRule, XBNFTables.Types._TypedRule);

		private static final /*@NonNull*/ ExecutorFragment _TypedRule__AbstractRule = new ExecutorFragment(Types._TypedRule, XBNFTables.Types._AbstractRule);
		private static final /*@NonNull*/ ExecutorFragment _TypedRule__OclAny = new ExecutorFragment(Types._TypedRule, OCLstdlibTables.Types._OclAny);
		private static final /*@NonNull*/ ExecutorFragment _TypedRule__OclElement = new ExecutorFragment(Types._TypedRule, OCLstdlibTables.Types._OclElement);
		private static final /*@NonNull*/ ExecutorFragment _TypedRule__TypedRule = new ExecutorFragment(Types._TypedRule, XBNFTables.Types._TypedRule);

		private static final /*@NonNull*/ ExecutorFragment _UntilToken__AbstractElement = new ExecutorFragment(Types._UntilToken, XBNFTables.Types._AbstractElement);
		private static final /*@NonNull*/ ExecutorFragment _UntilToken__OclAny = new ExecutorFragment(Types._UntilToken, OCLstdlibTables.Types._OclAny);
		private static final /*@NonNull*/ ExecutorFragment _UntilToken__OclElement = new ExecutorFragment(Types._UntilToken, OCLstdlibTables.Types._OclElement);
		private static final /*@NonNull*/ ExecutorFragment _UntilToken__UntilToken = new ExecutorFragment(Types._UntilToken, XBNFTables.Types._UntilToken);

		private static final /*@NonNull*/ ExecutorFragment _UntypedRule__AbstractRule = new ExecutorFragment(Types._UntypedRule, XBNFTables.Types._AbstractRule);
		private static final /*@NonNull*/ ExecutorFragment _UntypedRule__OclAny = new ExecutorFragment(Types._UntypedRule, OCLstdlibTables.Types._OclAny);
		private static final /*@NonNull*/ ExecutorFragment _UntypedRule__OclElement = new ExecutorFragment(Types._UntypedRule, OCLstdlibTables.Types._OclElement);
		private static final /*@NonNull*/ ExecutorFragment _UntypedRule__UntypedRule = new ExecutorFragment(Types._UntypedRule, XBNFTables.Types._UntypedRule);

		private static final /*@NonNull*/ ExecutorFragment _Wildcard__AbstractElement = new ExecutorFragment(Types._Wildcard, XBNFTables.Types._AbstractElement);
		private static final /*@NonNull*/ ExecutorFragment _Wildcard__OclAny = new ExecutorFragment(Types._Wildcard, OCLstdlibTables.Types._OclAny);
		private static final /*@NonNull*/ ExecutorFragment _Wildcard__OclElement = new ExecutorFragment(Types._Wildcard, OCLstdlibTables.Types._OclElement);
		private static final /*@NonNull*/ ExecutorFragment _Wildcard__Wildcard = new ExecutorFragment(Types._Wildcard, XBNFTables.Types._Wildcard);

		static {
			Init.initEnd();
		}

		/**
		 * Force initialization of the fields of XBNFTables::Fragments and all preceding sub-packages.
		 */
		public static void init() {}
	}

	/**
	 *	The parameter lists shared by operations.
	 *
	 * @noextend This class is not intended to be subclassed by clients.
	 * @noinstantiate This class is not intended to be instantiated by clients.
	 * @noreference This class is not intended to be referenced by clients.
	 */
	public static class Parameters {
		static {
			Init.initStart();
			Fragments.init();
		}


		static {
			Init.initEnd();
		}

		/**
		 * Force initialization of the fields of XBNFTables::Parameters and all preceding sub-packages.
		 */
		public static void init() {}
	}

	/**
	 *	The operation descriptors for each operation of each type.
	 *
	 * @noextend This class is not intended to be subclassed by clients.
	 * @noinstantiate This class is not intended to be instantiated by clients.
	 * @noreference This class is not intended to be referenced by clients.
	 */
	public static class Operations {
		static {
			Init.initStart();
			Parameters.init();
		}

		static {
			Init.initEnd();
		}

		/**
		 * Force initialization of the fields of XBNFTables::Operations and all preceding sub-packages.
		 */
		public static void init() {}
	}

	/**
	 *	The property descriptors for each property of each type.
	 *
	 * @noextend This class is not intended to be subclassed by clients.
	 * @noinstantiate This class is not intended to be instantiated by clients.
	 * @noreference This class is not intended to be referenced by clients.
	 */
	public static class Properties {
		static {
			Init.initStart();
			Operations.init();
		}

		public static final /*@NonNull*/ ExecutorProperty _AbstractElement__debug = new EcoreExecutorProperty(XBNFPackage.Literals.ABSTRACT_ELEMENT__DEBUG, Types._AbstractElement, 0);
		public static final /*@NonNull*/ ExecutorProperty _AbstractElement__parentRule = new EcoreExecutorProperty(XBNFPackage.Literals.ABSTRACT_ELEMENT__PARENT_RULE, Types._AbstractElement, 1);
		public static final /*@NonNull*/ ExecutorProperty _AbstractElement__Conjunction__elements = new ExecutorPropertyWithImplementation("Conjunction", Types._AbstractElement, 2, new EcoreLibraryOppositeProperty(XBNFPackage.Literals.CONJUNCTION__ELEMENTS));
		public static final /*@NonNull*/ ExecutorProperty _AbstractElement__NegatedToken__terminal = new ExecutorPropertyWithImplementation("NegatedToken", Types._AbstractElement, 3, new EcoreLibraryOppositeProperty(XBNFPackage.Literals.NEGATED_TOKEN__TERMINAL));
		public static final /*@NonNull*/ ExecutorProperty _AbstractElement__UntilToken__terminal = new ExecutorPropertyWithImplementation("UntilToken", Types._AbstractElement, 4, new EcoreLibraryOppositeProperty(XBNFPackage.Literals.UNTIL_TOKEN__TERMINAL));

		public static final /*@NonNull*/ ExecutorProperty _AbstractRule__debug = new EcoreExecutorProperty(XBNFPackage.Literals.ABSTRACT_RULE__DEBUG, Types._AbstractRule, 0);
		public static final /*@NonNull*/ ExecutorProperty _AbstractRule__element = new EcoreExecutorProperty(XBNFPackage.Literals.ABSTRACT_RULE__ELEMENT, Types._AbstractRule, 1);
		public static final /*@NonNull*/ ExecutorProperty _AbstractRule__kind = new EcoreExecutorProperty(XBNFPackage.Literals.ABSTRACT_RULE__KIND, Types._AbstractRule, 2);
		public static final /*@NonNull*/ ExecutorProperty _AbstractRule__name = new EcoreExecutorProperty(XBNFPackage.Literals.ABSTRACT_RULE__NAME, Types._AbstractRule, 3);
		public static final /*@NonNull*/ ExecutorProperty _AbstractRule__RuleCall__referredRule = new ExecutorPropertyWithImplementation("RuleCall", Types._AbstractRule, 4, new EcoreLibraryOppositeProperty(XBNFPackage.Literals.RULE_CALL__REFERRED_RULE));

		public static final /*@NonNull*/ ExecutorProperty _ActionAssignment__type = new EcoreExecutorProperty(XBNFPackage.Literals.ACTION_ASSIGNMENT__TYPE, Types._ActionAssignment, 0);

		public static final /*@NonNull*/ ExecutorProperty _Assignment__feature = new EcoreExecutorProperty(XBNFPackage.Literals.ASSIGNMENT__FEATURE, Types._Assignment, 0);
		public static final /*@NonNull*/ ExecutorProperty _Assignment__operator = new EcoreExecutorProperty(XBNFPackage.Literals.ASSIGNMENT__OPERATOR, Types._Assignment, 1);

		public static final /*@NonNull*/ ExecutorProperty _CharacterRange__left = new EcoreExecutorProperty(XBNFPackage.Literals.CHARACTER_RANGE__LEFT, Types._CharacterRange, 0);
		public static final /*@NonNull*/ ExecutorProperty _CharacterRange__right = new EcoreExecutorProperty(XBNFPackage.Literals.CHARACTER_RANGE__RIGHT, Types._CharacterRange, 1);

		public static final /*@NonNull*/ ExecutorProperty _Conjunction__elements = new EcoreExecutorProperty(XBNFPackage.Literals.CONJUNCTION__ELEMENTS, Types._Conjunction, 0);
		public static final /*@NonNull*/ ExecutorProperty _Conjunction__Disjunction__conjunctions = new ExecutorPropertyWithImplementation("Disjunction", Types._Conjunction, 1, new EcoreLibraryOppositeProperty(XBNFPackage.Literals.DISJUNCTION__CONJUNCTIONS));

		public static final /*@NonNull*/ ExecutorProperty _Disjunction__conjunctions = new EcoreExecutorProperty(XBNFPackage.Literals.DISJUNCTION__CONJUNCTIONS, Types._Disjunction, 0);

		public static final /*@NonNull*/ ExecutorProperty _Grammar__debug = new EcoreExecutorProperty(XBNFPackage.Literals.GRAMMAR__DEBUG, Types._Grammar, 0);
		public static final /*@NonNull*/ ExecutorProperty _Grammar__goals = new EcoreExecutorProperty(XBNFPackage.Literals.GRAMMAR__GOALS, Types._Grammar, 1);
		public static final /*@NonNull*/ ExecutorProperty _Grammar__name = new EcoreExecutorProperty(XBNFPackage.Literals.GRAMMAR__NAME, Types._Grammar, 2);
		public static final /*@NonNull*/ ExecutorProperty _Grammar__rules = new EcoreExecutorProperty(XBNFPackage.Literals.GRAMMAR__RULES, Types._Grammar, 3);
		public static final /*@NonNull*/ ExecutorProperty _Grammar__syntax = new EcoreExecutorProperty(XBNFPackage.Literals.GRAMMAR__SYNTAX, Types._Grammar, 4);

		public static final /*@NonNull*/ ExecutorProperty _Keyword__value = new EcoreExecutorProperty(XBNFPackage.Literals.KEYWORD__VALUE, Types._Keyword, 0);

		public static final /*@NonNull*/ ExecutorProperty _MetamodelDeclaration__name = new EcoreExecutorProperty(XBNFPackage.Literals.METAMODEL_DECLARATION__NAME, Types._MetamodelDeclaration, 0);
		public static final /*@NonNull*/ ExecutorProperty _MetamodelDeclaration__referredPackage = new EcoreExecutorProperty(XBNFPackage.Literals.METAMODEL_DECLARATION__REFERRED_PACKAGE, Types._MetamodelDeclaration, 1);
		public static final /*@NonNull*/ ExecutorProperty _MetamodelDeclaration__Syntax__metamodelDeclarations = new ExecutorPropertyWithImplementation("Syntax", Types._MetamodelDeclaration, 2, new EcoreLibraryOppositeProperty(XBNFPackage.Literals.SYNTAX__METAMODEL_DECLARATIONS));

		public static final /*@NonNull*/ ExecutorProperty _NegatedToken__terminal = new EcoreExecutorProperty(XBNFPackage.Literals.NEGATED_TOKEN__TERMINAL, Types._NegatedToken, 0);

		public static final /*@NonNull*/ ExecutorProperty _RuleCall__name = new EcoreExecutorProperty(XBNFPackage.Literals.RULE_CALL__NAME, Types._RuleCall, 0);
		public static final /*@NonNull*/ ExecutorProperty _RuleCall__referredRule = new EcoreExecutorProperty(XBNFPackage.Literals.RULE_CALL__REFERRED_RULE, Types._RuleCall, 1);

		public static final /*@NonNull*/ ExecutorProperty _Syntax__debug = new EcoreExecutorProperty(XBNFPackage.Literals.SYNTAX__DEBUG, Types._Syntax, 0);
		public static final /*@NonNull*/ ExecutorProperty _Syntax__grammars = new EcoreExecutorProperty(XBNFPackage.Literals.SYNTAX__GRAMMARS, Types._Syntax, 1);
		public static final /*@NonNull*/ ExecutorProperty _Syntax__metamodelDeclarations = new EcoreExecutorProperty(XBNFPackage.Literals.SYNTAX__METAMODEL_DECLARATIONS, Types._Syntax, 2);
		public static final /*@NonNull*/ ExecutorProperty _Syntax__name = new EcoreExecutorProperty(XBNFPackage.Literals.SYNTAX__NAME, Types._Syntax, 3);

		public static final /*@NonNull*/ ExecutorProperty _TypedRule__grammar = new EcoreExecutorProperty(XBNFPackage.Literals.TYPED_RULE__GRAMMAR, Types._TypedRule, 0);
		public static final /*@NonNull*/ ExecutorProperty _TypedRule__subrules = new EcoreExecutorProperty(XBNFPackage.Literals.TYPED_RULE__SUBRULES, Types._TypedRule, 1);
		public static final /*@NonNull*/ ExecutorProperty _TypedRule__type = new EcoreExecutorProperty(XBNFPackage.Literals.TYPED_RULE__TYPE, Types._TypedRule, 2);
		public static final /*@NonNull*/ ExecutorProperty _TypedRule__Grammar__goals = new ExecutorPropertyWithImplementation("Grammar", Types._TypedRule, 3, new EcoreLibraryOppositeProperty(XBNFPackage.Literals.GRAMMAR__GOALS));

		public static final /*@NonNull*/ ExecutorProperty _UntilToken__terminal = new EcoreExecutorProperty(XBNFPackage.Literals.UNTIL_TOKEN__TERMINAL, Types._UntilToken, 0);

		public static final /*@NonNull*/ ExecutorProperty _UntypedRule__typedRule = new EcoreExecutorProperty(XBNFPackage.Literals.UNTYPED_RULE__TYPED_RULE, Types._UntypedRule, 0);
		static {
			Init.initEnd();
		}

		/**
		 * Force initialization of the fields of XBNFTables::Properties and all preceding sub-packages.
		 */
		public static void init() {}
	}

	/**
	 *	The fragments for all base types in depth order: OclAny first, OclSelf last.
	 */
	public static class TypeFragments {
		static {
			Init.initStart();
			Properties.init();
		}

		private static final /*@NonNull*/ ExecutorFragment /*@NonNull*/ [] _AbstractElement =
		{
			Fragments._AbstractElement__OclAny /* 0 */,
			Fragments._AbstractElement__OclElement /* 1 */,
			Fragments._AbstractElement__AbstractElement /* 2 */
		};
		private static final int /*@NonNull*/ [] __AbstractElement = { 1,1,1 };

		private static final /*@NonNull*/ ExecutorFragment /*@NonNull*/ [] _AbstractRule =
		{
			Fragments._AbstractRule__OclAny /* 0 */,
			Fragments._AbstractRule__OclElement /* 1 */,
			Fragments._AbstractRule__AbstractRule /* 2 */
		};
		private static final int /*@NonNull*/ [] __AbstractRule = { 1,1,1 };

		private static final /*@NonNull*/ ExecutorFragment /*@NonNull*/ [] _ActionAssignment =
		{
			Fragments._ActionAssignment__OclAny /* 0 */,
			Fragments._ActionAssignment__OclElement /* 1 */,
			Fragments._ActionAssignment__AbstractElement /* 2 */,
			Fragments._ActionAssignment__Assignment /* 3 */,
			Fragments._ActionAssignment__ActionAssignment /* 4 */
		};
		private static final int /*@NonNull*/ [] __ActionAssignment = { 1,1,1,1,1 };

		private static final /*@NonNull*/ ExecutorFragment /*@NonNull*/ [] _Assignment =
		{
			Fragments._Assignment__OclAny /* 0 */,
			Fragments._Assignment__OclElement /* 1 */,
			Fragments._Assignment__AbstractElement /* 2 */,
			Fragments._Assignment__Assignment /* 3 */
		};
		private static final int /*@NonNull*/ [] __Assignment = { 1,1,1,1 };

		private static final /*@NonNull*/ ExecutorFragment /*@NonNull*/ [] _CharacterRange =
		{
			Fragments._CharacterRange__OclAny /* 0 */,
			Fragments._CharacterRange__OclElement /* 1 */,
			Fragments._CharacterRange__AbstractElement /* 2 */,
			Fragments._CharacterRange__CharacterRange /* 3 */
		};
		private static final int /*@NonNull*/ [] __CharacterRange = { 1,1,1,1 };

		private static final /*@NonNull*/ ExecutorFragment /*@NonNull*/ [] _Conjunction =
		{
			Fragments._Conjunction__OclAny /* 0 */,
			Fragments._Conjunction__OclElement /* 1 */,
			Fragments._Conjunction__AbstractElement /* 2 */,
			Fragments._Conjunction__Conjunction /* 3 */
		};
		private static final int /*@NonNull*/ [] __Conjunction = { 1,1,1,1 };

		private static final /*@NonNull*/ ExecutorFragment /*@NonNull*/ [] _Disjunction =
		{
			Fragments._Disjunction__OclAny /* 0 */,
			Fragments._Disjunction__OclElement /* 1 */,
			Fragments._Disjunction__AbstractElement /* 2 */,
			Fragments._Disjunction__Disjunction /* 3 */
		};
		private static final int /*@NonNull*/ [] __Disjunction = { 1,1,1,1 };

		private static final /*@NonNull*/ ExecutorFragment /*@NonNull*/ [] _EOF =
		{
			Fragments._EOF__OclAny /* 0 */,
			Fragments._EOF__OclElement /* 1 */,
			Fragments._EOF__AbstractElement /* 2 */,
			Fragments._EOF__EOF /* 3 */
		};
		private static final int /*@NonNull*/ [] __EOF = { 1,1,1,1 };

		private static final /*@NonNull*/ ExecutorFragment /*@NonNull*/ [] _Epsilon =
		{
			Fragments._Epsilon__OclAny /* 0 */,
			Fragments._Epsilon__OclElement /* 1 */,
			Fragments._Epsilon__AbstractElement /* 2 */,
			Fragments._Epsilon__Epsilon /* 3 */
		};
		private static final int /*@NonNull*/ [] __Epsilon = { 1,1,1,1 };

		private static final /*@NonNull*/ ExecutorFragment /*@NonNull*/ [] _Grammar =
		{
			Fragments._Grammar__OclAny /* 0 */,
			Fragments._Grammar__OclElement /* 1 */,
			Fragments._Grammar__Grammar /* 2 */
		};
		private static final int /*@NonNull*/ [] __Grammar = { 1,1,1 };

		private static final /*@NonNull*/ ExecutorFragment /*@NonNull*/ [] _Keyword =
		{
			Fragments._Keyword__OclAny /* 0 */,
			Fragments._Keyword__OclElement /* 1 */,
			Fragments._Keyword__AbstractElement /* 2 */,
			Fragments._Keyword__Keyword /* 3 */
		};
		private static final int /*@NonNull*/ [] __Keyword = { 1,1,1,1 };

		private static final /*@NonNull*/ ExecutorFragment /*@NonNull*/ [] _KeywordAssignment =
		{
			Fragments._KeywordAssignment__OclAny /* 0 */,
			Fragments._KeywordAssignment__OclElement /* 1 */,
			Fragments._KeywordAssignment__AbstractElement /* 2 */,
			Fragments._KeywordAssignment__Assignment /* 3 */,
			Fragments._KeywordAssignment__Keyword /* 3 */,
			Fragments._KeywordAssignment__KeywordAssignment /* 4 */
		};
		private static final int /*@NonNull*/ [] __KeywordAssignment = { 1,1,1,2,1 };

		private static final /*@NonNull*/ ExecutorFragment /*@NonNull*/ [] _LexerGrammar =
		{
			Fragments._LexerGrammar__OclAny /* 0 */,
			Fragments._LexerGrammar__OclElement /* 1 */,
			Fragments._LexerGrammar__Grammar /* 2 */,
			Fragments._LexerGrammar__LexerGrammar /* 3 */
		};
		private static final int /*@NonNull*/ [] __LexerGrammar = { 1,1,1,1 };

		private static final /*@NonNull*/ ExecutorFragment /*@NonNull*/ [] _MetamodelDeclaration =
		{
			Fragments._MetamodelDeclaration__OclAny /* 0 */,
			Fragments._MetamodelDeclaration__OclElement /* 1 */,
			Fragments._MetamodelDeclaration__MetamodelDeclaration /* 2 */
		};
		private static final int /*@NonNull*/ [] __MetamodelDeclaration = { 1,1,1 };

		private static final /*@NonNull*/ ExecutorFragment /*@NonNull*/ [] _NegatedToken =
		{
			Fragments._NegatedToken__OclAny /* 0 */,
			Fragments._NegatedToken__OclElement /* 1 */,
			Fragments._NegatedToken__AbstractElement /* 2 */,
			Fragments._NegatedToken__NegatedToken /* 3 */
		};
		private static final int /*@NonNull*/ [] __NegatedToken = { 1,1,1,1 };

		private static final /*@NonNull*/ ExecutorFragment /*@NonNull*/ [] _ParserGrammar =
		{
			Fragments._ParserGrammar__OclAny /* 0 */,
			Fragments._ParserGrammar__OclElement /* 1 */,
			Fragments._ParserGrammar__Grammar /* 2 */,
			Fragments._ParserGrammar__ParserGrammar /* 3 */
		};
		private static final int /*@NonNull*/ [] __ParserGrammar = { 1,1,1,1 };

		private static final /*@NonNull*/ ExecutorFragment /*@NonNull*/ [] _ParserRule =
		{
			Fragments._ParserRule__OclAny /* 0 */,
			Fragments._ParserRule__OclElement /* 1 */,
			Fragments._ParserRule__AbstractRule /* 2 */,
			Fragments._ParserRule__TypedRule /* 3 */,
			Fragments._ParserRule__ParserRule /* 4 */
		};
		private static final int /*@NonNull*/ [] __ParserRule = { 1,1,1,1,1 };

		private static final /*@NonNull*/ ExecutorFragment /*@NonNull*/ [] _RuleCall =
		{
			Fragments._RuleCall__OclAny /* 0 */,
			Fragments._RuleCall__OclElement /* 1 */,
			Fragments._RuleCall__AbstractElement /* 2 */,
			Fragments._RuleCall__RuleCall /* 3 */
		};
		private static final int /*@NonNull*/ [] __RuleCall = { 1,1,1,1 };

		private static final /*@NonNull*/ ExecutorFragment /*@NonNull*/ [] _RuleCallAssignment =
		{
			Fragments._RuleCallAssignment__OclAny /* 0 */,
			Fragments._RuleCallAssignment__OclElement /* 1 */,
			Fragments._RuleCallAssignment__AbstractElement /* 2 */,
			Fragments._RuleCallAssignment__Assignment /* 3 */,
			Fragments._RuleCallAssignment__RuleCall /* 3 */,
			Fragments._RuleCallAssignment__RuleCallAssignment /* 4 */
		};
		private static final int /*@NonNull*/ [] __RuleCallAssignment = { 1,1,1,2,1 };

		private static final /*@NonNull*/ ExecutorFragment /*@NonNull*/ [] _Syntax =
		{
			Fragments._Syntax__OclAny /* 0 */,
			Fragments._Syntax__OclElement /* 1 */,
			Fragments._Syntax__Syntax /* 2 */
		};
		private static final int /*@NonNull*/ [] __Syntax = { 1,1,1 };

		private static final /*@NonNull*/ ExecutorFragment /*@NonNull*/ [] _TerminalRule =
		{
			Fragments._TerminalRule__OclAny /* 0 */,
			Fragments._TerminalRule__OclElement /* 1 */,
			Fragments._TerminalRule__AbstractRule /* 2 */,
			Fragments._TerminalRule__TypedRule /* 3 */,
			Fragments._TerminalRule__TerminalRule /* 4 */
		};
		private static final int /*@NonNull*/ [] __TerminalRule = { 1,1,1,1,1 };

		private static final /*@NonNull*/ ExecutorFragment /*@NonNull*/ [] _TypedRule =
		{
			Fragments._TypedRule__OclAny /* 0 */,
			Fragments._TypedRule__OclElement /* 1 */,
			Fragments._TypedRule__AbstractRule /* 2 */,
			Fragments._TypedRule__TypedRule /* 3 */
		};
		private static final int /*@NonNull*/ [] __TypedRule = { 1,1,1,1 };

		private static final /*@NonNull*/ ExecutorFragment /*@NonNull*/ [] _UntilToken =
		{
			Fragments._UntilToken__OclAny /* 0 */,
			Fragments._UntilToken__OclElement /* 1 */,
			Fragments._UntilToken__AbstractElement /* 2 */,
			Fragments._UntilToken__UntilToken /* 3 */
		};
		private static final int /*@NonNull*/ [] __UntilToken = { 1,1,1,1 };

		private static final /*@NonNull*/ ExecutorFragment /*@NonNull*/ [] _UntypedRule =
		{
			Fragments._UntypedRule__OclAny /* 0 */,
			Fragments._UntypedRule__OclElement /* 1 */,
			Fragments._UntypedRule__AbstractRule /* 2 */,
			Fragments._UntypedRule__UntypedRule /* 3 */
		};
		private static final int /*@NonNull*/ [] __UntypedRule = { 1,1,1,1 };

		private static final /*@NonNull*/ ExecutorFragment /*@NonNull*/ [] _Wildcard =
		{
			Fragments._Wildcard__OclAny /* 0 */,
			Fragments._Wildcard__OclElement /* 1 */,
			Fragments._Wildcard__AbstractElement /* 2 */,
			Fragments._Wildcard__Wildcard /* 3 */
		};
		private static final int /*@NonNull*/ [] __Wildcard = { 1,1,1,1 };

		/**
		 *	Install the fragment descriptors in the class descriptors.
		 */
		static {
			Types._AbstractElement.initFragments(_AbstractElement, __AbstractElement);
			Types._AbstractRule.initFragments(_AbstractRule, __AbstractRule);
			Types._ActionAssignment.initFragments(_ActionAssignment, __ActionAssignment);
			Types._Assignment.initFragments(_Assignment, __Assignment);
			Types._CharacterRange.initFragments(_CharacterRange, __CharacterRange);
			Types._Conjunction.initFragments(_Conjunction, __Conjunction);
			Types._Disjunction.initFragments(_Disjunction, __Disjunction);
			Types._EOF.initFragments(_EOF, __EOF);
			Types._Epsilon.initFragments(_Epsilon, __Epsilon);
			Types._Grammar.initFragments(_Grammar, __Grammar);
			Types._Keyword.initFragments(_Keyword, __Keyword);
			Types._KeywordAssignment.initFragments(_KeywordAssignment, __KeywordAssignment);
			Types._LexerGrammar.initFragments(_LexerGrammar, __LexerGrammar);
			Types._MetamodelDeclaration.initFragments(_MetamodelDeclaration, __MetamodelDeclaration);
			Types._NegatedToken.initFragments(_NegatedToken, __NegatedToken);
			Types._ParserGrammar.initFragments(_ParserGrammar, __ParserGrammar);
			Types._ParserRule.initFragments(_ParserRule, __ParserRule);
			Types._RuleCall.initFragments(_RuleCall, __RuleCall);
			Types._RuleCallAssignment.initFragments(_RuleCallAssignment, __RuleCallAssignment);
			Types._Syntax.initFragments(_Syntax, __Syntax);
			Types._TerminalRule.initFragments(_TerminalRule, __TerminalRule);
			Types._TypedRule.initFragments(_TypedRule, __TypedRule);
			Types._UntilToken.initFragments(_UntilToken, __UntilToken);
			Types._UntypedRule.initFragments(_UntypedRule, __UntypedRule);
			Types._Wildcard.initFragments(_Wildcard, __Wildcard);

			Init.initEnd();
		}

		/**
		 * Force initialization of the fields of XBNFTables::TypeFragments and all preceding sub-packages.
		 */
		public static void init() {}
	}

	/**
	 *	The lists of local operations or local operation overrides for each fragment of each type.
	 */
	public static class FragmentOperations {
		static {
			Init.initStart();
			TypeFragments.init();
		}

		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _AbstractElement__AbstractElement = {};
		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _AbstractElement__OclAny = {
			OCLstdlibTables.Operations._OclAny___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclAny__oclAsType /* oclAsType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclAny__oclIsKindOf /* oclIsKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclAny__oclIsTypeOf /* oclIsTypeOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[?]) */,
			OCLstdlibTables.Operations._OclAny__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclAny__oclTypes /* oclTypes() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};
		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _AbstractElement__OclElement = {
			OCLstdlibTables.Operations._OclElement__allInstances /* allInstances() */,
			OCLstdlibTables.Operations._OclElement__oclAsModelType /* oclAsModelType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */,
			OCLstdlibTables.Operations._OclElement__oclIsModelKindOf /* oclIsModelKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclElement__oclModelType /* oclModelType() */,
			OCLstdlibTables.Operations._OclElement__oclModelTypes /* oclModelTypes() */
		};

		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _AbstractRule__AbstractRule = {};
		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _AbstractRule__OclAny = {
			OCLstdlibTables.Operations._OclAny___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclAny__oclAsType /* oclAsType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclAny__oclIsKindOf /* oclIsKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclAny__oclIsTypeOf /* oclIsTypeOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[?]) */,
			OCLstdlibTables.Operations._OclAny__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclAny__oclTypes /* oclTypes() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};
		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _AbstractRule__OclElement = {
			OCLstdlibTables.Operations._OclElement__allInstances /* allInstances() */,
			OCLstdlibTables.Operations._OclElement__oclAsModelType /* oclAsModelType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */,
			OCLstdlibTables.Operations._OclElement__oclIsModelKindOf /* oclIsModelKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclElement__oclModelType /* oclModelType() */,
			OCLstdlibTables.Operations._OclElement__oclModelTypes /* oclModelTypes() */
		};

		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _ActionAssignment__ActionAssignment = {};
		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _ActionAssignment__AbstractElement = {};
		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _ActionAssignment__Assignment = {};
		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _ActionAssignment__OclAny = {
			OCLstdlibTables.Operations._OclAny___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclAny__oclAsType /* oclAsType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclAny__oclIsKindOf /* oclIsKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclAny__oclIsTypeOf /* oclIsTypeOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[?]) */,
			OCLstdlibTables.Operations._OclAny__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclAny__oclTypes /* oclTypes() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};
		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _ActionAssignment__OclElement = {
			OCLstdlibTables.Operations._OclElement__allInstances /* allInstances() */,
			OCLstdlibTables.Operations._OclElement__oclAsModelType /* oclAsModelType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */,
			OCLstdlibTables.Operations._OclElement__oclIsModelKindOf /* oclIsModelKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclElement__oclModelType /* oclModelType() */,
			OCLstdlibTables.Operations._OclElement__oclModelTypes /* oclModelTypes() */
		};

		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _Assignment__Assignment = {};
		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _Assignment__AbstractElement = {};
		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _Assignment__OclAny = {
			OCLstdlibTables.Operations._OclAny___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclAny__oclAsType /* oclAsType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclAny__oclIsKindOf /* oclIsKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclAny__oclIsTypeOf /* oclIsTypeOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[?]) */,
			OCLstdlibTables.Operations._OclAny__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclAny__oclTypes /* oclTypes() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};
		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _Assignment__OclElement = {
			OCLstdlibTables.Operations._OclElement__allInstances /* allInstances() */,
			OCLstdlibTables.Operations._OclElement__oclAsModelType /* oclAsModelType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */,
			OCLstdlibTables.Operations._OclElement__oclIsModelKindOf /* oclIsModelKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclElement__oclModelType /* oclModelType() */,
			OCLstdlibTables.Operations._OclElement__oclModelTypes /* oclModelTypes() */
		};

		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _CharacterRange__CharacterRange = {};
		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _CharacterRange__AbstractElement = {};
		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _CharacterRange__OclAny = {
			OCLstdlibTables.Operations._OclAny___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclAny__oclAsType /* oclAsType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclAny__oclIsKindOf /* oclIsKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclAny__oclIsTypeOf /* oclIsTypeOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[?]) */,
			OCLstdlibTables.Operations._OclAny__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclAny__oclTypes /* oclTypes() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};
		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _CharacterRange__OclElement = {
			OCLstdlibTables.Operations._OclElement__allInstances /* allInstances() */,
			OCLstdlibTables.Operations._OclElement__oclAsModelType /* oclAsModelType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */,
			OCLstdlibTables.Operations._OclElement__oclIsModelKindOf /* oclIsModelKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclElement__oclModelType /* oclModelType() */,
			OCLstdlibTables.Operations._OclElement__oclModelTypes /* oclModelTypes() */
		};

		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _Conjunction__Conjunction = {};
		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _Conjunction__AbstractElement = {};
		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _Conjunction__OclAny = {
			OCLstdlibTables.Operations._OclAny___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclAny__oclAsType /* oclAsType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclAny__oclIsKindOf /* oclIsKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclAny__oclIsTypeOf /* oclIsTypeOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[?]) */,
			OCLstdlibTables.Operations._OclAny__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclAny__oclTypes /* oclTypes() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};
		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _Conjunction__OclElement = {
			OCLstdlibTables.Operations._OclElement__allInstances /* allInstances() */,
			OCLstdlibTables.Operations._OclElement__oclAsModelType /* oclAsModelType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */,
			OCLstdlibTables.Operations._OclElement__oclIsModelKindOf /* oclIsModelKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclElement__oclModelType /* oclModelType() */,
			OCLstdlibTables.Operations._OclElement__oclModelTypes /* oclModelTypes() */
		};

		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _Disjunction__Disjunction = {};
		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _Disjunction__AbstractElement = {};
		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _Disjunction__OclAny = {
			OCLstdlibTables.Operations._OclAny___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclAny__oclAsType /* oclAsType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclAny__oclIsKindOf /* oclIsKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclAny__oclIsTypeOf /* oclIsTypeOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[?]) */,
			OCLstdlibTables.Operations._OclAny__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclAny__oclTypes /* oclTypes() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};
		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _Disjunction__OclElement = {
			OCLstdlibTables.Operations._OclElement__allInstances /* allInstances() */,
			OCLstdlibTables.Operations._OclElement__oclAsModelType /* oclAsModelType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */,
			OCLstdlibTables.Operations._OclElement__oclIsModelKindOf /* oclIsModelKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclElement__oclModelType /* oclModelType() */,
			OCLstdlibTables.Operations._OclElement__oclModelTypes /* oclModelTypes() */
		};

		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _EOF__EOF = {};
		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _EOF__AbstractElement = {};
		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _EOF__OclAny = {
			OCLstdlibTables.Operations._OclAny___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclAny__oclAsType /* oclAsType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclAny__oclIsKindOf /* oclIsKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclAny__oclIsTypeOf /* oclIsTypeOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[?]) */,
			OCLstdlibTables.Operations._OclAny__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclAny__oclTypes /* oclTypes() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};
		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _EOF__OclElement = {
			OCLstdlibTables.Operations._OclElement__allInstances /* allInstances() */,
			OCLstdlibTables.Operations._OclElement__oclAsModelType /* oclAsModelType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */,
			OCLstdlibTables.Operations._OclElement__oclIsModelKindOf /* oclIsModelKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclElement__oclModelType /* oclModelType() */,
			OCLstdlibTables.Operations._OclElement__oclModelTypes /* oclModelTypes() */
		};

		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _Epsilon__Epsilon = {};
		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _Epsilon__AbstractElement = {};
		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _Epsilon__OclAny = {
			OCLstdlibTables.Operations._OclAny___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclAny__oclAsType /* oclAsType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclAny__oclIsKindOf /* oclIsKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclAny__oclIsTypeOf /* oclIsTypeOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[?]) */,
			OCLstdlibTables.Operations._OclAny__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclAny__oclTypes /* oclTypes() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};
		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _Epsilon__OclElement = {
			OCLstdlibTables.Operations._OclElement__allInstances /* allInstances() */,
			OCLstdlibTables.Operations._OclElement__oclAsModelType /* oclAsModelType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */,
			OCLstdlibTables.Operations._OclElement__oclIsModelKindOf /* oclIsModelKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclElement__oclModelType /* oclModelType() */,
			OCLstdlibTables.Operations._OclElement__oclModelTypes /* oclModelTypes() */
		};

		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _Grammar__Grammar = {};
		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _Grammar__OclAny = {
			OCLstdlibTables.Operations._OclAny___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclAny__oclAsType /* oclAsType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclAny__oclIsKindOf /* oclIsKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclAny__oclIsTypeOf /* oclIsTypeOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[?]) */,
			OCLstdlibTables.Operations._OclAny__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclAny__oclTypes /* oclTypes() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};
		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _Grammar__OclElement = {
			OCLstdlibTables.Operations._OclElement__allInstances /* allInstances() */,
			OCLstdlibTables.Operations._OclElement__oclAsModelType /* oclAsModelType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */,
			OCLstdlibTables.Operations._OclElement__oclIsModelKindOf /* oclIsModelKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclElement__oclModelType /* oclModelType() */,
			OCLstdlibTables.Operations._OclElement__oclModelTypes /* oclModelTypes() */
		};

		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _Keyword__Keyword = {};
		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _Keyword__AbstractElement = {};
		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _Keyword__OclAny = {
			OCLstdlibTables.Operations._OclAny___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclAny__oclAsType /* oclAsType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclAny__oclIsKindOf /* oclIsKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclAny__oclIsTypeOf /* oclIsTypeOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[?]) */,
			OCLstdlibTables.Operations._OclAny__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclAny__oclTypes /* oclTypes() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};
		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _Keyword__OclElement = {
			OCLstdlibTables.Operations._OclElement__allInstances /* allInstances() */,
			OCLstdlibTables.Operations._OclElement__oclAsModelType /* oclAsModelType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */,
			OCLstdlibTables.Operations._OclElement__oclIsModelKindOf /* oclIsModelKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclElement__oclModelType /* oclModelType() */,
			OCLstdlibTables.Operations._OclElement__oclModelTypes /* oclModelTypes() */
		};

		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _KeywordAssignment__KeywordAssignment = {};
		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _KeywordAssignment__AbstractElement = {};
		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _KeywordAssignment__Assignment = {};
		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _KeywordAssignment__Keyword = {};
		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _KeywordAssignment__OclAny = {
			OCLstdlibTables.Operations._OclAny___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclAny__oclAsType /* oclAsType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclAny__oclIsKindOf /* oclIsKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclAny__oclIsTypeOf /* oclIsTypeOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[?]) */,
			OCLstdlibTables.Operations._OclAny__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclAny__oclTypes /* oclTypes() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};
		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _KeywordAssignment__OclElement = {
			OCLstdlibTables.Operations._OclElement__allInstances /* allInstances() */,
			OCLstdlibTables.Operations._OclElement__oclAsModelType /* oclAsModelType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */,
			OCLstdlibTables.Operations._OclElement__oclIsModelKindOf /* oclIsModelKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclElement__oclModelType /* oclModelType() */,
			OCLstdlibTables.Operations._OclElement__oclModelTypes /* oclModelTypes() */
		};

		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _LexerGrammar__LexerGrammar = {};
		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _LexerGrammar__Grammar = {};
		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _LexerGrammar__OclAny = {
			OCLstdlibTables.Operations._OclAny___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclAny__oclAsType /* oclAsType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclAny__oclIsKindOf /* oclIsKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclAny__oclIsTypeOf /* oclIsTypeOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[?]) */,
			OCLstdlibTables.Operations._OclAny__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclAny__oclTypes /* oclTypes() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};
		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _LexerGrammar__OclElement = {
			OCLstdlibTables.Operations._OclElement__allInstances /* allInstances() */,
			OCLstdlibTables.Operations._OclElement__oclAsModelType /* oclAsModelType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */,
			OCLstdlibTables.Operations._OclElement__oclIsModelKindOf /* oclIsModelKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclElement__oclModelType /* oclModelType() */,
			OCLstdlibTables.Operations._OclElement__oclModelTypes /* oclModelTypes() */
		};

		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _MetamodelDeclaration__MetamodelDeclaration = {};
		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _MetamodelDeclaration__OclAny = {
			OCLstdlibTables.Operations._OclAny___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclAny__oclAsType /* oclAsType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclAny__oclIsKindOf /* oclIsKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclAny__oclIsTypeOf /* oclIsTypeOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[?]) */,
			OCLstdlibTables.Operations._OclAny__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclAny__oclTypes /* oclTypes() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};
		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _MetamodelDeclaration__OclElement = {
			OCLstdlibTables.Operations._OclElement__allInstances /* allInstances() */,
			OCLstdlibTables.Operations._OclElement__oclAsModelType /* oclAsModelType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */,
			OCLstdlibTables.Operations._OclElement__oclIsModelKindOf /* oclIsModelKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclElement__oclModelType /* oclModelType() */,
			OCLstdlibTables.Operations._OclElement__oclModelTypes /* oclModelTypes() */
		};

		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _NegatedToken__NegatedToken = {};
		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _NegatedToken__AbstractElement = {};
		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _NegatedToken__OclAny = {
			OCLstdlibTables.Operations._OclAny___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclAny__oclAsType /* oclAsType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclAny__oclIsKindOf /* oclIsKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclAny__oclIsTypeOf /* oclIsTypeOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[?]) */,
			OCLstdlibTables.Operations._OclAny__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclAny__oclTypes /* oclTypes() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};
		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _NegatedToken__OclElement = {
			OCLstdlibTables.Operations._OclElement__allInstances /* allInstances() */,
			OCLstdlibTables.Operations._OclElement__oclAsModelType /* oclAsModelType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */,
			OCLstdlibTables.Operations._OclElement__oclIsModelKindOf /* oclIsModelKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclElement__oclModelType /* oclModelType() */,
			OCLstdlibTables.Operations._OclElement__oclModelTypes /* oclModelTypes() */
		};

		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _ParserGrammar__ParserGrammar = {};
		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _ParserGrammar__Grammar = {};
		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _ParserGrammar__OclAny = {
			OCLstdlibTables.Operations._OclAny___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclAny__oclAsType /* oclAsType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclAny__oclIsKindOf /* oclIsKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclAny__oclIsTypeOf /* oclIsTypeOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[?]) */,
			OCLstdlibTables.Operations._OclAny__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclAny__oclTypes /* oclTypes() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};
		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _ParserGrammar__OclElement = {
			OCLstdlibTables.Operations._OclElement__allInstances /* allInstances() */,
			OCLstdlibTables.Operations._OclElement__oclAsModelType /* oclAsModelType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */,
			OCLstdlibTables.Operations._OclElement__oclIsModelKindOf /* oclIsModelKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclElement__oclModelType /* oclModelType() */,
			OCLstdlibTables.Operations._OclElement__oclModelTypes /* oclModelTypes() */
		};

		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _ParserRule__ParserRule = {};
		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _ParserRule__AbstractRule = {};
		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _ParserRule__OclAny = {
			OCLstdlibTables.Operations._OclAny___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclAny__oclAsType /* oclAsType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclAny__oclIsKindOf /* oclIsKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclAny__oclIsTypeOf /* oclIsTypeOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[?]) */,
			OCLstdlibTables.Operations._OclAny__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclAny__oclTypes /* oclTypes() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};
		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _ParserRule__OclElement = {
			OCLstdlibTables.Operations._OclElement__allInstances /* allInstances() */,
			OCLstdlibTables.Operations._OclElement__oclAsModelType /* oclAsModelType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */,
			OCLstdlibTables.Operations._OclElement__oclIsModelKindOf /* oclIsModelKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclElement__oclModelType /* oclModelType() */,
			OCLstdlibTables.Operations._OclElement__oclModelTypes /* oclModelTypes() */
		};
		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _ParserRule__TypedRule = {};

		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _RuleCall__RuleCall = {};
		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _RuleCall__AbstractElement = {};
		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _RuleCall__OclAny = {
			OCLstdlibTables.Operations._OclAny___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclAny__oclAsType /* oclAsType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclAny__oclIsKindOf /* oclIsKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclAny__oclIsTypeOf /* oclIsTypeOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[?]) */,
			OCLstdlibTables.Operations._OclAny__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclAny__oclTypes /* oclTypes() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};
		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _RuleCall__OclElement = {
			OCLstdlibTables.Operations._OclElement__allInstances /* allInstances() */,
			OCLstdlibTables.Operations._OclElement__oclAsModelType /* oclAsModelType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */,
			OCLstdlibTables.Operations._OclElement__oclIsModelKindOf /* oclIsModelKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclElement__oclModelType /* oclModelType() */,
			OCLstdlibTables.Operations._OclElement__oclModelTypes /* oclModelTypes() */
		};

		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _RuleCallAssignment__RuleCallAssignment = {};
		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _RuleCallAssignment__AbstractElement = {};
		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _RuleCallAssignment__Assignment = {};
		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _RuleCallAssignment__OclAny = {
			OCLstdlibTables.Operations._OclAny___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclAny__oclAsType /* oclAsType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclAny__oclIsKindOf /* oclIsKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclAny__oclIsTypeOf /* oclIsTypeOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[?]) */,
			OCLstdlibTables.Operations._OclAny__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclAny__oclTypes /* oclTypes() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};
		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _RuleCallAssignment__OclElement = {
			OCLstdlibTables.Operations._OclElement__allInstances /* allInstances() */,
			OCLstdlibTables.Operations._OclElement__oclAsModelType /* oclAsModelType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */,
			OCLstdlibTables.Operations._OclElement__oclIsModelKindOf /* oclIsModelKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclElement__oclModelType /* oclModelType() */,
			OCLstdlibTables.Operations._OclElement__oclModelTypes /* oclModelTypes() */
		};
		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _RuleCallAssignment__RuleCall = {};

		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _Syntax__Syntax = {};
		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _Syntax__OclAny = {
			OCLstdlibTables.Operations._OclAny___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclAny__oclAsType /* oclAsType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclAny__oclIsKindOf /* oclIsKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclAny__oclIsTypeOf /* oclIsTypeOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[?]) */,
			OCLstdlibTables.Operations._OclAny__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclAny__oclTypes /* oclTypes() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};
		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _Syntax__OclElement = {
			OCLstdlibTables.Operations._OclElement__allInstances /* allInstances() */,
			OCLstdlibTables.Operations._OclElement__oclAsModelType /* oclAsModelType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */,
			OCLstdlibTables.Operations._OclElement__oclIsModelKindOf /* oclIsModelKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclElement__oclModelType /* oclModelType() */,
			OCLstdlibTables.Operations._OclElement__oclModelTypes /* oclModelTypes() */
		};

		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _TerminalRule__TerminalRule = {};
		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _TerminalRule__AbstractRule = {};
		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _TerminalRule__OclAny = {
			OCLstdlibTables.Operations._OclAny___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclAny__oclAsType /* oclAsType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclAny__oclIsKindOf /* oclIsKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclAny__oclIsTypeOf /* oclIsTypeOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[?]) */,
			OCLstdlibTables.Operations._OclAny__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclAny__oclTypes /* oclTypes() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};
		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _TerminalRule__OclElement = {
			OCLstdlibTables.Operations._OclElement__allInstances /* allInstances() */,
			OCLstdlibTables.Operations._OclElement__oclAsModelType /* oclAsModelType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */,
			OCLstdlibTables.Operations._OclElement__oclIsModelKindOf /* oclIsModelKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclElement__oclModelType /* oclModelType() */,
			OCLstdlibTables.Operations._OclElement__oclModelTypes /* oclModelTypes() */
		};
		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _TerminalRule__TypedRule = {};

		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _TypedRule__TypedRule = {};
		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _TypedRule__AbstractRule = {};
		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _TypedRule__OclAny = {
			OCLstdlibTables.Operations._OclAny___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclAny__oclAsType /* oclAsType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclAny__oclIsKindOf /* oclIsKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclAny__oclIsTypeOf /* oclIsTypeOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[?]) */,
			OCLstdlibTables.Operations._OclAny__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclAny__oclTypes /* oclTypes() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};
		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _TypedRule__OclElement = {
			OCLstdlibTables.Operations._OclElement__allInstances /* allInstances() */,
			OCLstdlibTables.Operations._OclElement__oclAsModelType /* oclAsModelType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */,
			OCLstdlibTables.Operations._OclElement__oclIsModelKindOf /* oclIsModelKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclElement__oclModelType /* oclModelType() */,
			OCLstdlibTables.Operations._OclElement__oclModelTypes /* oclModelTypes() */
		};

		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _UntilToken__UntilToken = {};
		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _UntilToken__AbstractElement = {};
		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _UntilToken__OclAny = {
			OCLstdlibTables.Operations._OclAny___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclAny__oclAsType /* oclAsType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclAny__oclIsKindOf /* oclIsKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclAny__oclIsTypeOf /* oclIsTypeOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[?]) */,
			OCLstdlibTables.Operations._OclAny__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclAny__oclTypes /* oclTypes() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};
		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _UntilToken__OclElement = {
			OCLstdlibTables.Operations._OclElement__allInstances /* allInstances() */,
			OCLstdlibTables.Operations._OclElement__oclAsModelType /* oclAsModelType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */,
			OCLstdlibTables.Operations._OclElement__oclIsModelKindOf /* oclIsModelKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclElement__oclModelType /* oclModelType() */,
			OCLstdlibTables.Operations._OclElement__oclModelTypes /* oclModelTypes() */
		};

		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _UntypedRule__UntypedRule = {};
		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _UntypedRule__AbstractRule = {};
		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _UntypedRule__OclAny = {
			OCLstdlibTables.Operations._OclAny___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclAny__oclAsType /* oclAsType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclAny__oclIsKindOf /* oclIsKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclAny__oclIsTypeOf /* oclIsTypeOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[?]) */,
			OCLstdlibTables.Operations._OclAny__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclAny__oclTypes /* oclTypes() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};
		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _UntypedRule__OclElement = {
			OCLstdlibTables.Operations._OclElement__allInstances /* allInstances() */,
			OCLstdlibTables.Operations._OclElement__oclAsModelType /* oclAsModelType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */,
			OCLstdlibTables.Operations._OclElement__oclIsModelKindOf /* oclIsModelKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclElement__oclModelType /* oclModelType() */,
			OCLstdlibTables.Operations._OclElement__oclModelTypes /* oclModelTypes() */
		};

		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _Wildcard__Wildcard = {};
		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _Wildcard__AbstractElement = {};
		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _Wildcard__OclAny = {
			OCLstdlibTables.Operations._OclAny___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclAny__oclAsType /* oclAsType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclAny__oclIsKindOf /* oclIsKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclAny__oclIsTypeOf /* oclIsTypeOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[?]) */,
			OCLstdlibTables.Operations._OclAny__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclAny__oclTypes /* oclTypes() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};
		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _Wildcard__OclElement = {
			OCLstdlibTables.Operations._OclElement__allInstances /* allInstances() */,
			OCLstdlibTables.Operations._OclElement__oclAsModelType /* oclAsModelType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */,
			OCLstdlibTables.Operations._OclElement__oclIsModelKindOf /* oclIsModelKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclElement__oclModelType /* oclModelType() */,
			OCLstdlibTables.Operations._OclElement__oclModelTypes /* oclModelTypes() */
		};

		/*
		 *	Install the operation descriptors in the fragment descriptors.
		 */
		static {
			Fragments._AbstractElement__AbstractElement.initOperations(_AbstractElement__AbstractElement);
			Fragments._AbstractElement__OclAny.initOperations(_AbstractElement__OclAny);
			Fragments._AbstractElement__OclElement.initOperations(_AbstractElement__OclElement);

			Fragments._AbstractRule__AbstractRule.initOperations(_AbstractRule__AbstractRule);
			Fragments._AbstractRule__OclAny.initOperations(_AbstractRule__OclAny);
			Fragments._AbstractRule__OclElement.initOperations(_AbstractRule__OclElement);

			Fragments._ActionAssignment__AbstractElement.initOperations(_ActionAssignment__AbstractElement);
			Fragments._ActionAssignment__ActionAssignment.initOperations(_ActionAssignment__ActionAssignment);
			Fragments._ActionAssignment__Assignment.initOperations(_ActionAssignment__Assignment);
			Fragments._ActionAssignment__OclAny.initOperations(_ActionAssignment__OclAny);
			Fragments._ActionAssignment__OclElement.initOperations(_ActionAssignment__OclElement);

			Fragments._Assignment__AbstractElement.initOperations(_Assignment__AbstractElement);
			Fragments._Assignment__Assignment.initOperations(_Assignment__Assignment);
			Fragments._Assignment__OclAny.initOperations(_Assignment__OclAny);
			Fragments._Assignment__OclElement.initOperations(_Assignment__OclElement);

			Fragments._CharacterRange__AbstractElement.initOperations(_CharacterRange__AbstractElement);
			Fragments._CharacterRange__CharacterRange.initOperations(_CharacterRange__CharacterRange);
			Fragments._CharacterRange__OclAny.initOperations(_CharacterRange__OclAny);
			Fragments._CharacterRange__OclElement.initOperations(_CharacterRange__OclElement);

			Fragments._Conjunction__AbstractElement.initOperations(_Conjunction__AbstractElement);
			Fragments._Conjunction__Conjunction.initOperations(_Conjunction__Conjunction);
			Fragments._Conjunction__OclAny.initOperations(_Conjunction__OclAny);
			Fragments._Conjunction__OclElement.initOperations(_Conjunction__OclElement);

			Fragments._Disjunction__AbstractElement.initOperations(_Disjunction__AbstractElement);
			Fragments._Disjunction__Disjunction.initOperations(_Disjunction__Disjunction);
			Fragments._Disjunction__OclAny.initOperations(_Disjunction__OclAny);
			Fragments._Disjunction__OclElement.initOperations(_Disjunction__OclElement);

			Fragments._EOF__AbstractElement.initOperations(_EOF__AbstractElement);
			Fragments._EOF__EOF.initOperations(_EOF__EOF);
			Fragments._EOF__OclAny.initOperations(_EOF__OclAny);
			Fragments._EOF__OclElement.initOperations(_EOF__OclElement);

			Fragments._Epsilon__AbstractElement.initOperations(_Epsilon__AbstractElement);
			Fragments._Epsilon__Epsilon.initOperations(_Epsilon__Epsilon);
			Fragments._Epsilon__OclAny.initOperations(_Epsilon__OclAny);
			Fragments._Epsilon__OclElement.initOperations(_Epsilon__OclElement);

			Fragments._Grammar__Grammar.initOperations(_Grammar__Grammar);
			Fragments._Grammar__OclAny.initOperations(_Grammar__OclAny);
			Fragments._Grammar__OclElement.initOperations(_Grammar__OclElement);

			Fragments._Keyword__AbstractElement.initOperations(_Keyword__AbstractElement);
			Fragments._Keyword__Keyword.initOperations(_Keyword__Keyword);
			Fragments._Keyword__OclAny.initOperations(_Keyword__OclAny);
			Fragments._Keyword__OclElement.initOperations(_Keyword__OclElement);

			Fragments._KeywordAssignment__AbstractElement.initOperations(_KeywordAssignment__AbstractElement);
			Fragments._KeywordAssignment__Assignment.initOperations(_KeywordAssignment__Assignment);
			Fragments._KeywordAssignment__Keyword.initOperations(_KeywordAssignment__Keyword);
			Fragments._KeywordAssignment__KeywordAssignment.initOperations(_KeywordAssignment__KeywordAssignment);
			Fragments._KeywordAssignment__OclAny.initOperations(_KeywordAssignment__OclAny);
			Fragments._KeywordAssignment__OclElement.initOperations(_KeywordAssignment__OclElement);

			Fragments._LexerGrammar__Grammar.initOperations(_LexerGrammar__Grammar);
			Fragments._LexerGrammar__LexerGrammar.initOperations(_LexerGrammar__LexerGrammar);
			Fragments._LexerGrammar__OclAny.initOperations(_LexerGrammar__OclAny);
			Fragments._LexerGrammar__OclElement.initOperations(_LexerGrammar__OclElement);

			Fragments._MetamodelDeclaration__MetamodelDeclaration.initOperations(_MetamodelDeclaration__MetamodelDeclaration);
			Fragments._MetamodelDeclaration__OclAny.initOperations(_MetamodelDeclaration__OclAny);
			Fragments._MetamodelDeclaration__OclElement.initOperations(_MetamodelDeclaration__OclElement);

			Fragments._NegatedToken__AbstractElement.initOperations(_NegatedToken__AbstractElement);
			Fragments._NegatedToken__NegatedToken.initOperations(_NegatedToken__NegatedToken);
			Fragments._NegatedToken__OclAny.initOperations(_NegatedToken__OclAny);
			Fragments._NegatedToken__OclElement.initOperations(_NegatedToken__OclElement);

			Fragments._ParserGrammar__Grammar.initOperations(_ParserGrammar__Grammar);
			Fragments._ParserGrammar__OclAny.initOperations(_ParserGrammar__OclAny);
			Fragments._ParserGrammar__OclElement.initOperations(_ParserGrammar__OclElement);
			Fragments._ParserGrammar__ParserGrammar.initOperations(_ParserGrammar__ParserGrammar);

			Fragments._ParserRule__AbstractRule.initOperations(_ParserRule__AbstractRule);
			Fragments._ParserRule__OclAny.initOperations(_ParserRule__OclAny);
			Fragments._ParserRule__OclElement.initOperations(_ParserRule__OclElement);
			Fragments._ParserRule__ParserRule.initOperations(_ParserRule__ParserRule);
			Fragments._ParserRule__TypedRule.initOperations(_ParserRule__TypedRule);

			Fragments._RuleCall__AbstractElement.initOperations(_RuleCall__AbstractElement);
			Fragments._RuleCall__OclAny.initOperations(_RuleCall__OclAny);
			Fragments._RuleCall__OclElement.initOperations(_RuleCall__OclElement);
			Fragments._RuleCall__RuleCall.initOperations(_RuleCall__RuleCall);

			Fragments._RuleCallAssignment__AbstractElement.initOperations(_RuleCallAssignment__AbstractElement);
			Fragments._RuleCallAssignment__Assignment.initOperations(_RuleCallAssignment__Assignment);
			Fragments._RuleCallAssignment__OclAny.initOperations(_RuleCallAssignment__OclAny);
			Fragments._RuleCallAssignment__OclElement.initOperations(_RuleCallAssignment__OclElement);
			Fragments._RuleCallAssignment__RuleCall.initOperations(_RuleCallAssignment__RuleCall);
			Fragments._RuleCallAssignment__RuleCallAssignment.initOperations(_RuleCallAssignment__RuleCallAssignment);

			Fragments._Syntax__OclAny.initOperations(_Syntax__OclAny);
			Fragments._Syntax__OclElement.initOperations(_Syntax__OclElement);
			Fragments._Syntax__Syntax.initOperations(_Syntax__Syntax);

			Fragments._TerminalRule__AbstractRule.initOperations(_TerminalRule__AbstractRule);
			Fragments._TerminalRule__OclAny.initOperations(_TerminalRule__OclAny);
			Fragments._TerminalRule__OclElement.initOperations(_TerminalRule__OclElement);
			Fragments._TerminalRule__TerminalRule.initOperations(_TerminalRule__TerminalRule);
			Fragments._TerminalRule__TypedRule.initOperations(_TerminalRule__TypedRule);

			Fragments._TypedRule__AbstractRule.initOperations(_TypedRule__AbstractRule);
			Fragments._TypedRule__OclAny.initOperations(_TypedRule__OclAny);
			Fragments._TypedRule__OclElement.initOperations(_TypedRule__OclElement);
			Fragments._TypedRule__TypedRule.initOperations(_TypedRule__TypedRule);

			Fragments._UntilToken__AbstractElement.initOperations(_UntilToken__AbstractElement);
			Fragments._UntilToken__OclAny.initOperations(_UntilToken__OclAny);
			Fragments._UntilToken__OclElement.initOperations(_UntilToken__OclElement);
			Fragments._UntilToken__UntilToken.initOperations(_UntilToken__UntilToken);

			Fragments._UntypedRule__AbstractRule.initOperations(_UntypedRule__AbstractRule);
			Fragments._UntypedRule__OclAny.initOperations(_UntypedRule__OclAny);
			Fragments._UntypedRule__OclElement.initOperations(_UntypedRule__OclElement);
			Fragments._UntypedRule__UntypedRule.initOperations(_UntypedRule__UntypedRule);

			Fragments._Wildcard__AbstractElement.initOperations(_Wildcard__AbstractElement);
			Fragments._Wildcard__OclAny.initOperations(_Wildcard__OclAny);
			Fragments._Wildcard__OclElement.initOperations(_Wildcard__OclElement);
			Fragments._Wildcard__Wildcard.initOperations(_Wildcard__Wildcard);

			Init.initEnd();
		}

		/**
		 * Force initialization of the fields of XBNFTables::FragmentOperations and all preceding sub-packages.
		 */
		public static void init() {}
	}

	/**
	 *	The lists of local properties for the local fragment of each type.
	 */
	public static class FragmentProperties {
		static {
			Init.initStart();
			FragmentOperations.init();
		}

		private static final /*@NonNull*/ ExecutorProperty /*@NonNull*/ [] _AbstractElement = {
			XBNFTables.Properties._AbstractElement__debug,
			XBNFTables.Properties._AbstractElement__parentRule
		};

		private static final /*@NonNull*/ ExecutorProperty /*@NonNull*/ [] _AbstractRule = {
			XBNFTables.Properties._AbstractRule__debug,
			XBNFTables.Properties._AbstractRule__element,
			XBNFTables.Properties._AbstractRule__kind,
			XBNFTables.Properties._AbstractRule__name
		};

		private static final /*@NonNull*/ ExecutorProperty /*@NonNull*/ [] _ActionAssignment = {
			XBNFTables.Properties._AbstractElement__debug,
			XBNFTables.Properties._Assignment__feature,
			XBNFTables.Properties._Assignment__operator,
			XBNFTables.Properties._AbstractElement__parentRule,
			XBNFTables.Properties._ActionAssignment__type
		};

		private static final /*@NonNull*/ ExecutorProperty /*@NonNull*/ [] _Assignment = {
			XBNFTables.Properties._AbstractElement__debug,
			XBNFTables.Properties._Assignment__feature,
			XBNFTables.Properties._Assignment__operator,
			XBNFTables.Properties._AbstractElement__parentRule
		};

		private static final /*@NonNull*/ ExecutorProperty /*@NonNull*/ [] _CharacterRange = {
			XBNFTables.Properties._AbstractElement__debug,
			XBNFTables.Properties._CharacterRange__left,
			XBNFTables.Properties._AbstractElement__parentRule,
			XBNFTables.Properties._CharacterRange__right
		};

		private static final /*@NonNull*/ ExecutorProperty /*@NonNull*/ [] _Conjunction = {
			XBNFTables.Properties._AbstractElement__debug,
			XBNFTables.Properties._Conjunction__elements,
			XBNFTables.Properties._AbstractElement__parentRule
		};

		private static final /*@NonNull*/ ExecutorProperty /*@NonNull*/ [] _Disjunction = {
			XBNFTables.Properties._Disjunction__conjunctions,
			XBNFTables.Properties._AbstractElement__debug,
			XBNFTables.Properties._AbstractElement__parentRule
		};

		private static final /*@NonNull*/ ExecutorProperty /*@NonNull*/ [] _EOF = {
			XBNFTables.Properties._AbstractElement__debug,
			XBNFTables.Properties._AbstractElement__parentRule
		};

		private static final /*@NonNull*/ ExecutorProperty /*@NonNull*/ [] _Epsilon = {
			XBNFTables.Properties._AbstractElement__debug,
			XBNFTables.Properties._AbstractElement__parentRule
		};

		private static final /*@NonNull*/ ExecutorProperty /*@NonNull*/ [] _Grammar = {
			XBNFTables.Properties._Grammar__debug,
			XBNFTables.Properties._Grammar__goals,
			XBNFTables.Properties._Grammar__name,
			XBNFTables.Properties._Grammar__rules,
			XBNFTables.Properties._Grammar__syntax
		};

		private static final /*@NonNull*/ ExecutorProperty /*@NonNull*/ [] _Keyword = {
			XBNFTables.Properties._AbstractElement__debug,
			XBNFTables.Properties._AbstractElement__parentRule,
			XBNFTables.Properties._Keyword__value
		};

		private static final /*@NonNull*/ ExecutorProperty /*@NonNull*/ [] _KeywordAssignment = {
			XBNFTables.Properties._AbstractElement__debug,
			XBNFTables.Properties._Assignment__feature,
			XBNFTables.Properties._Assignment__operator,
			XBNFTables.Properties._AbstractElement__parentRule,
			XBNFTables.Properties._Keyword__value
		};

		private static final /*@NonNull*/ ExecutorProperty /*@NonNull*/ [] _LexerGrammar = {
			XBNFTables.Properties._Grammar__debug,
			XBNFTables.Properties._Grammar__goals,
			XBNFTables.Properties._Grammar__name,
			XBNFTables.Properties._Grammar__rules,
			XBNFTables.Properties._Grammar__syntax
		};

		private static final /*@NonNull*/ ExecutorProperty /*@NonNull*/ [] _MetamodelDeclaration = {
			XBNFTables.Properties._MetamodelDeclaration__name,
			XBNFTables.Properties._MetamodelDeclaration__referredPackage
		};

		private static final /*@NonNull*/ ExecutorProperty /*@NonNull*/ [] _NegatedToken = {
			XBNFTables.Properties._AbstractElement__debug,
			XBNFTables.Properties._AbstractElement__parentRule,
			XBNFTables.Properties._NegatedToken__terminal
		};

		private static final /*@NonNull*/ ExecutorProperty /*@NonNull*/ [] _ParserGrammar = {
			XBNFTables.Properties._Grammar__debug,
			XBNFTables.Properties._Grammar__goals,
			XBNFTables.Properties._Grammar__name,
			XBNFTables.Properties._Grammar__rules,
			XBNFTables.Properties._Grammar__syntax
		};

		private static final /*@NonNull*/ ExecutorProperty /*@NonNull*/ [] _ParserRule = {
			XBNFTables.Properties._AbstractRule__debug,
			XBNFTables.Properties._AbstractRule__element,
			XBNFTables.Properties._TypedRule__grammar,
			XBNFTables.Properties._AbstractRule__kind,
			XBNFTables.Properties._AbstractRule__name,
			XBNFTables.Properties._TypedRule__subrules,
			XBNFTables.Properties._TypedRule__type
		};

		private static final /*@NonNull*/ ExecutorProperty /*@NonNull*/ [] _RuleCall = {
			XBNFTables.Properties._AbstractElement__debug,
			XBNFTables.Properties._RuleCall__name,
			XBNFTables.Properties._AbstractElement__parentRule,
			XBNFTables.Properties._RuleCall__referredRule
		};

		private static final /*@NonNull*/ ExecutorProperty /*@NonNull*/ [] _RuleCallAssignment = {
			XBNFTables.Properties._AbstractElement__debug,
			XBNFTables.Properties._Assignment__feature,
			XBNFTables.Properties._RuleCall__name,
			XBNFTables.Properties._Assignment__operator,
			XBNFTables.Properties._AbstractElement__parentRule,
			XBNFTables.Properties._RuleCall__referredRule
		};

		private static final /*@NonNull*/ ExecutorProperty /*@NonNull*/ [] _Syntax = {
			XBNFTables.Properties._Syntax__debug,
			XBNFTables.Properties._Syntax__grammars,
			XBNFTables.Properties._Syntax__metamodelDeclarations,
			XBNFTables.Properties._Syntax__name
		};

		private static final /*@NonNull*/ ExecutorProperty /*@NonNull*/ [] _TerminalRule = {
			XBNFTables.Properties._AbstractRule__debug,
			XBNFTables.Properties._AbstractRule__element,
			XBNFTables.Properties._TypedRule__grammar,
			XBNFTables.Properties._AbstractRule__kind,
			XBNFTables.Properties._AbstractRule__name,
			XBNFTables.Properties._TypedRule__subrules,
			XBNFTables.Properties._TypedRule__type
		};

		private static final /*@NonNull*/ ExecutorProperty /*@NonNull*/ [] _TypedRule = {
			XBNFTables.Properties._AbstractRule__debug,
			XBNFTables.Properties._AbstractRule__element,
			XBNFTables.Properties._TypedRule__grammar,
			XBNFTables.Properties._AbstractRule__kind,
			XBNFTables.Properties._AbstractRule__name,
			XBNFTables.Properties._TypedRule__subrules,
			XBNFTables.Properties._TypedRule__type
		};

		private static final /*@NonNull*/ ExecutorProperty /*@NonNull*/ [] _UntilToken = {
			XBNFTables.Properties._AbstractElement__debug,
			XBNFTables.Properties._AbstractElement__parentRule,
			XBNFTables.Properties._UntilToken__terminal
		};

		private static final /*@NonNull*/ ExecutorProperty /*@NonNull*/ [] _UntypedRule = {
			XBNFTables.Properties._AbstractRule__debug,
			XBNFTables.Properties._AbstractRule__element,
			XBNFTables.Properties._AbstractRule__kind,
			XBNFTables.Properties._AbstractRule__name,
			XBNFTables.Properties._UntypedRule__typedRule
		};

		private static final /*@NonNull*/ ExecutorProperty /*@NonNull*/ [] _Wildcard = {
			XBNFTables.Properties._AbstractElement__debug,
			XBNFTables.Properties._AbstractElement__parentRule
		};

		/**
		 *	Install the property descriptors in the fragment descriptors.
		 */
		static {
			Fragments._AbstractElement__AbstractElement.initProperties(_AbstractElement);
			Fragments._AbstractRule__AbstractRule.initProperties(_AbstractRule);
			Fragments._ActionAssignment__ActionAssignment.initProperties(_ActionAssignment);
			Fragments._Assignment__Assignment.initProperties(_Assignment);
			Fragments._CharacterRange__CharacterRange.initProperties(_CharacterRange);
			Fragments._Conjunction__Conjunction.initProperties(_Conjunction);
			Fragments._Disjunction__Disjunction.initProperties(_Disjunction);
			Fragments._EOF__EOF.initProperties(_EOF);
			Fragments._Epsilon__Epsilon.initProperties(_Epsilon);
			Fragments._Grammar__Grammar.initProperties(_Grammar);
			Fragments._Keyword__Keyword.initProperties(_Keyword);
			Fragments._KeywordAssignment__KeywordAssignment.initProperties(_KeywordAssignment);
			Fragments._LexerGrammar__LexerGrammar.initProperties(_LexerGrammar);
			Fragments._MetamodelDeclaration__MetamodelDeclaration.initProperties(_MetamodelDeclaration);
			Fragments._NegatedToken__NegatedToken.initProperties(_NegatedToken);
			Fragments._ParserGrammar__ParserGrammar.initProperties(_ParserGrammar);
			Fragments._ParserRule__ParserRule.initProperties(_ParserRule);
			Fragments._RuleCall__RuleCall.initProperties(_RuleCall);
			Fragments._RuleCallAssignment__RuleCallAssignment.initProperties(_RuleCallAssignment);
			Fragments._Syntax__Syntax.initProperties(_Syntax);
			Fragments._TerminalRule__TerminalRule.initProperties(_TerminalRule);
			Fragments._TypedRule__TypedRule.initProperties(_TypedRule);
			Fragments._UntilToken__UntilToken.initProperties(_UntilToken);
			Fragments._UntypedRule__UntypedRule.initProperties(_UntypedRule);
			Fragments._Wildcard__Wildcard.initProperties(_Wildcard);

			Init.initEnd();
		}

		/**
		 * Force initialization of the fields of XBNFTables::FragmentProperties and all preceding sub-packages.
		 */
		public static void init() {}
	}

	/**
	 *	The lists of enumeration literals for each enumeration.
	 */
	public static class EnumerationLiterals {
		static {
			Init.initStart();
			FragmentProperties.init();
		}

		/**
		 *	Install the enumeration literals in the enumerations.
		 */
		static {

			Init.initEnd();
		}

		/**
		 * Force initialization of the fields of XBNFTables::EnumerationLiterals and all preceding sub-packages.
		 */
		public static void init() {}
	}

	/**
	 * The multiple packages above avoid problems with the Java 65536 byte limit but introduce a difficulty in ensuring that
	 * static construction occurs in the disciplined order of the packages when construction may start in any of the packages.
	 * The problem is resolved by ensuring that the static construction of each package first initializes its immediate predecessor.
	 * On completion of predecessor initialization, the residual packages are initialized by starting an initialization in the last package.
	 * This class maintains a count so that the various predecessors can distinguish whether they are the starting point and so
	 * ensure that residual construction occurs just once after all predecessors.
	 */
	private static class Init {
		/**
		 * Counter of nested static constructions. On return to zero residual construction starts. -ve once residual construction started.
		 */
		private static int initCount = 0;

		/**
		 * Invoked at the start of a static construction to defer residual cobstruction until primary constructions complete.
		 */
		private static void initStart() {
			if (initCount >= 0) {
				initCount++;
			}
		}

		/**
		 * Invoked at the end of a static construction to activate residual cobstruction once primary constructions complete.
		 */
		private static void initEnd() {
			if (initCount > 0) {
				if (--initCount == 0) {
					initCount = -1;
					EnumerationLiterals.init();
				}
			}
		}
	}

	static {
		Init.initEnd();
	}

	/*
	 * Force initialization of outer fields. Inner fields are lazily initialized.
	 */
	public static void init() {}
}
