--/**
-- * <copyright>
-- *
-- * Copyright (c) 2008, 2009 IBM Corporation and others.
-- * All rights reserved.   This program and the accompanying materials
-- * are made available under the terms of the Eclipse Public License v2.0
-- * which accompanies this distribution, and is available at
-- * http://www.eclipse.org/legal/epl-v20.html
-- *
-- * Contributors:
-- *   IBM - Initial API and implementation
-- *   E.D.Willink - Lexer and Parser refactoring to support extensibility and flexible error handling
-- *   Adolfo Sanchez-Barbudo Herrera (Open Canarias) - LPG v 2.0.17 adoption (242153)
-- *   Adolfo Sanchez-Barbudo Herrera (Open Canarias) - Introducing new LPG templates (299396)
-- *
-- * </copyright>
-- */
--
-- The OCL Backtracking KeyWord Lexer, which is nominally identical to the
-- normal KeyWord Lexer, however the extra ERROR_TOKEN symbol makes it difficult
-- to share reliably.
--

%options slr
%options fp=OCLBacktrackingKWLexer,prefix=Char_
%options noserialize
%options package=org.eclipse.ocl.parser.backtracking
%options template=../../lpg/KeywordTemplateF.gi
%options export_terminals=("OCLBacktrackingParsersym.java", "TK_")
%options include_directory="..;../../lpg"

%Import
	OCLKWLexer.gi
%End
