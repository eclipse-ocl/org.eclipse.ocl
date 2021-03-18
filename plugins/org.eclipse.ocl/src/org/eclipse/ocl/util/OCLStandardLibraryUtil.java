/*******************************************************************************
 * Copyright (c) 2006, 2014, 2018 IBM Corporation, Zeligsoft Inc., and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *   E.D.Willink - Refactoring to support extensibility and flexible error handling
 *       - Bug 259819
 *   Zeligsoft - Bug 244948
 *   Axel Uhl (SAP AG) - Bug 342644
 *******************************************************************************/
package org.eclipse.ocl.util;

import static org.eclipse.ocl.utilities.PredefinedType.ABS;
import static org.eclipse.ocl.utilities.PredefinedType.ABS_NAME;
import static org.eclipse.ocl.utilities.PredefinedType.ALL_INSTANCES;
import static org.eclipse.ocl.utilities.PredefinedType.ALL_INSTANCES_NAME;
import static org.eclipse.ocl.utilities.PredefinedType.AND;
import static org.eclipse.ocl.utilities.PredefinedType.AND_NAME;
import static org.eclipse.ocl.utilities.PredefinedType.ANY;
import static org.eclipse.ocl.utilities.PredefinedType.ANY_NAME;
import static org.eclipse.ocl.utilities.PredefinedType.APPEND;
import static org.eclipse.ocl.utilities.PredefinedType.APPEND_NAME;
import static org.eclipse.ocl.utilities.PredefinedType.AS_BAG;
import static org.eclipse.ocl.utilities.PredefinedType.AS_BAG_NAME;
import static org.eclipse.ocl.utilities.PredefinedType.AS_ORDERED_SET;
import static org.eclipse.ocl.utilities.PredefinedType.AS_ORDERED_SET_NAME;
import static org.eclipse.ocl.utilities.PredefinedType.AS_SEQUENCE;
import static org.eclipse.ocl.utilities.PredefinedType.AS_SEQUENCE_NAME;
import static org.eclipse.ocl.utilities.PredefinedType.AS_SET;
import static org.eclipse.ocl.utilities.PredefinedType.AS_SET_NAME;
import static org.eclipse.ocl.utilities.PredefinedType.AT;
import static org.eclipse.ocl.utilities.PredefinedType.AT_NAME;
import static org.eclipse.ocl.utilities.PredefinedType.CHARACTERS;
import static org.eclipse.ocl.utilities.PredefinedType.CHARACTERS_NAME;
import static org.eclipse.ocl.utilities.PredefinedType.CLOSURE;
import static org.eclipse.ocl.utilities.PredefinedType.CLOSURE_NAME;
import static org.eclipse.ocl.utilities.PredefinedType.COLLECT;
import static org.eclipse.ocl.utilities.PredefinedType.COLLECT_NAME;
import static org.eclipse.ocl.utilities.PredefinedType.COLLECT_NESTED;
import static org.eclipse.ocl.utilities.PredefinedType.COLLECT_NESTED_NAME;
import static org.eclipse.ocl.utilities.PredefinedType.CONCAT;
import static org.eclipse.ocl.utilities.PredefinedType.CONCAT_NAME;
import static org.eclipse.ocl.utilities.PredefinedType.COUNT;
import static org.eclipse.ocl.utilities.PredefinedType.COUNT_NAME;
import static org.eclipse.ocl.utilities.PredefinedType.DIV;
import static org.eclipse.ocl.utilities.PredefinedType.DIVIDE;
import static org.eclipse.ocl.utilities.PredefinedType.DIVIDE_NAME;
import static org.eclipse.ocl.utilities.PredefinedType.DIV_NAME;
import static org.eclipse.ocl.utilities.PredefinedType.ENDS_WITH;
import static org.eclipse.ocl.utilities.PredefinedType.ENDS_WITH_NAME;
import static org.eclipse.ocl.utilities.PredefinedType.EQUAL;
import static org.eclipse.ocl.utilities.PredefinedType.EQUALS_IGNORE_CASE;
import static org.eclipse.ocl.utilities.PredefinedType.EQUALS_IGNORE_CASE_NAME;
import static org.eclipse.ocl.utilities.PredefinedType.EQUAL_NAME;
import static org.eclipse.ocl.utilities.PredefinedType.EXCLUDES;
import static org.eclipse.ocl.utilities.PredefinedType.EXCLUDES_ALL;
import static org.eclipse.ocl.utilities.PredefinedType.EXCLUDES_ALL_NAME;
import static org.eclipse.ocl.utilities.PredefinedType.EXCLUDES_NAME;
import static org.eclipse.ocl.utilities.PredefinedType.EXCLUDING;
import static org.eclipse.ocl.utilities.PredefinedType.EXCLUDING_NAME;
import static org.eclipse.ocl.utilities.PredefinedType.EXISTS;
import static org.eclipse.ocl.utilities.PredefinedType.EXISTS_NAME;
import static org.eclipse.ocl.utilities.PredefinedType.FIRST;
import static org.eclipse.ocl.utilities.PredefinedType.FIRST_NAME;
import static org.eclipse.ocl.utilities.PredefinedType.FLATTEN;
import static org.eclipse.ocl.utilities.PredefinedType.FLATTEN_NAME;
import static org.eclipse.ocl.utilities.PredefinedType.FLOOR;
import static org.eclipse.ocl.utilities.PredefinedType.FLOOR_NAME;
import static org.eclipse.ocl.utilities.PredefinedType.FOR_ALL;
import static org.eclipse.ocl.utilities.PredefinedType.FOR_ALL_NAME;
import static org.eclipse.ocl.utilities.PredefinedType.GREATER_THAN;
import static org.eclipse.ocl.utilities.PredefinedType.GREATER_THAN_EQUAL;
import static org.eclipse.ocl.utilities.PredefinedType.GREATER_THAN_EQUAL_NAME;
import static org.eclipse.ocl.utilities.PredefinedType.GREATER_THAN_NAME;
import static org.eclipse.ocl.utilities.PredefinedType.HAS_RETURNED;
import static org.eclipse.ocl.utilities.PredefinedType.HAS_RETURNED_NAME;
import static org.eclipse.ocl.utilities.PredefinedType.IMPLIES;
import static org.eclipse.ocl.utilities.PredefinedType.IMPLIES_NAME;
import static org.eclipse.ocl.utilities.PredefinedType.INCLUDES;
import static org.eclipse.ocl.utilities.PredefinedType.INCLUDES_ALL;
import static org.eclipse.ocl.utilities.PredefinedType.INCLUDES_ALL_NAME;
import static org.eclipse.ocl.utilities.PredefinedType.INCLUDES_NAME;
import static org.eclipse.ocl.utilities.PredefinedType.INCLUDING;
import static org.eclipse.ocl.utilities.PredefinedType.INCLUDING_NAME;
import static org.eclipse.ocl.utilities.PredefinedType.INDEX_OF;
import static org.eclipse.ocl.utilities.PredefinedType.INDEX_OF_NAME;
import static org.eclipse.ocl.utilities.PredefinedType.INSERT_AT;
import static org.eclipse.ocl.utilities.PredefinedType.INSERT_AT_NAME;
import static org.eclipse.ocl.utilities.PredefinedType.INTERSECTION;
import static org.eclipse.ocl.utilities.PredefinedType.INTERSECTION_NAME;
import static org.eclipse.ocl.utilities.PredefinedType.IS_EMPTY;
import static org.eclipse.ocl.utilities.PredefinedType.IS_EMPTY_NAME;
import static org.eclipse.ocl.utilities.PredefinedType.IS_OPERATION_CALL;
import static org.eclipse.ocl.utilities.PredefinedType.IS_OPERATION_CALL_NAME;
import static org.eclipse.ocl.utilities.PredefinedType.IS_SIGNAL_SENT;
import static org.eclipse.ocl.utilities.PredefinedType.IS_SIGNAL_SENT_NAME;
import static org.eclipse.ocl.utilities.PredefinedType.IS_UNIQUE;
import static org.eclipse.ocl.utilities.PredefinedType.IS_UNIQUE_NAME;
import static org.eclipse.ocl.utilities.PredefinedType.LAST;
import static org.eclipse.ocl.utilities.PredefinedType.LAST_INDEX_OF;
import static org.eclipse.ocl.utilities.PredefinedType.LAST_INDEX_OF_NAME;
import static org.eclipse.ocl.utilities.PredefinedType.LAST_NAME;
import static org.eclipse.ocl.utilities.PredefinedType.LESS_THAN;
import static org.eclipse.ocl.utilities.PredefinedType.LESS_THAN_EQUAL;
import static org.eclipse.ocl.utilities.PredefinedType.LESS_THAN_EQUAL_NAME;
import static org.eclipse.ocl.utilities.PredefinedType.LESS_THAN_NAME;
import static org.eclipse.ocl.utilities.PredefinedType.MATCHES;
import static org.eclipse.ocl.utilities.PredefinedType.MATCHES_NAME;
import static org.eclipse.ocl.utilities.PredefinedType.MAX;
import static org.eclipse.ocl.utilities.PredefinedType.MAX_NAME;
import static org.eclipse.ocl.utilities.PredefinedType.MIN;
import static org.eclipse.ocl.utilities.PredefinedType.MINUS;
import static org.eclipse.ocl.utilities.PredefinedType.MINUS_NAME;
import static org.eclipse.ocl.utilities.PredefinedType.MIN_NAME;
import static org.eclipse.ocl.utilities.PredefinedType.MOD;
import static org.eclipse.ocl.utilities.PredefinedType.MOD_NAME;
import static org.eclipse.ocl.utilities.PredefinedType.NOT;
import static org.eclipse.ocl.utilities.PredefinedType.NOT_EMPTY;
import static org.eclipse.ocl.utilities.PredefinedType.NOT_EMPTY_NAME;
import static org.eclipse.ocl.utilities.PredefinedType.NOT_EQUAL;
import static org.eclipse.ocl.utilities.PredefinedType.NOT_EQUAL_NAME;
import static org.eclipse.ocl.utilities.PredefinedType.NOT_NAME;
import static org.eclipse.ocl.utilities.PredefinedType.OCL_AS_SET;
import static org.eclipse.ocl.utilities.PredefinedType.OCL_AS_SET_NAME;
import static org.eclipse.ocl.utilities.PredefinedType.OCL_AS_TYPE;
import static org.eclipse.ocl.utilities.PredefinedType.OCL_AS_TYPE_NAME;
import static org.eclipse.ocl.utilities.PredefinedType.OCL_IS_INVALID;
import static org.eclipse.ocl.utilities.PredefinedType.OCL_IS_INVALID_NAME;
import static org.eclipse.ocl.utilities.PredefinedType.OCL_IS_IN_STATE;
import static org.eclipse.ocl.utilities.PredefinedType.OCL_IS_IN_STATE_NAME;
import static org.eclipse.ocl.utilities.PredefinedType.OCL_IS_KIND_OF;
import static org.eclipse.ocl.utilities.PredefinedType.OCL_IS_KIND_OF_NAME;
import static org.eclipse.ocl.utilities.PredefinedType.OCL_IS_NEW;
import static org.eclipse.ocl.utilities.PredefinedType.OCL_IS_NEW_NAME;
import static org.eclipse.ocl.utilities.PredefinedType.OCL_IS_TYPE_OF;
import static org.eclipse.ocl.utilities.PredefinedType.OCL_IS_TYPE_OF_NAME;
import static org.eclipse.ocl.utilities.PredefinedType.OCL_IS_UNDEFINED;
import static org.eclipse.ocl.utilities.PredefinedType.OCL_IS_UNDEFINED_NAME;
import static org.eclipse.ocl.utilities.PredefinedType.ONE;
import static org.eclipse.ocl.utilities.PredefinedType.ONE_NAME;
import static org.eclipse.ocl.utilities.PredefinedType.OR;
import static org.eclipse.ocl.utilities.PredefinedType.OR_NAME;
import static org.eclipse.ocl.utilities.PredefinedType.PLUS;
import static org.eclipse.ocl.utilities.PredefinedType.PLUS_NAME;
import static org.eclipse.ocl.utilities.PredefinedType.PREPEND;
import static org.eclipse.ocl.utilities.PredefinedType.PREPEND_NAME;
import static org.eclipse.ocl.utilities.PredefinedType.PRODUCT;
import static org.eclipse.ocl.utilities.PredefinedType.PRODUCT_NAME;
import static org.eclipse.ocl.utilities.PredefinedType.REJECT;
import static org.eclipse.ocl.utilities.PredefinedType.REJECT_NAME;
import static org.eclipse.ocl.utilities.PredefinedType.REPLACE_ALL;
import static org.eclipse.ocl.utilities.PredefinedType.REPLACE_ALL_NAME;
import static org.eclipse.ocl.utilities.PredefinedType.REPLACE_FIRST;
import static org.eclipse.ocl.utilities.PredefinedType.REPLACE_FIRST_NAME;
import static org.eclipse.ocl.utilities.PredefinedType.RESULT;
import static org.eclipse.ocl.utilities.PredefinedType.RESULT_NAME;
import static org.eclipse.ocl.utilities.PredefinedType.ROUND;
import static org.eclipse.ocl.utilities.PredefinedType.ROUND_NAME;
import static org.eclipse.ocl.utilities.PredefinedType.SELECT;
import static org.eclipse.ocl.utilities.PredefinedType.SELECT_BY_KIND;
import static org.eclipse.ocl.utilities.PredefinedType.SELECT_BY_KIND_NAME;
import static org.eclipse.ocl.utilities.PredefinedType.SELECT_BY_TYPE;
import static org.eclipse.ocl.utilities.PredefinedType.SELECT_BY_TYPE_NAME;
import static org.eclipse.ocl.utilities.PredefinedType.SELECT_NAME;
import static org.eclipse.ocl.utilities.PredefinedType.SIZE;
import static org.eclipse.ocl.utilities.PredefinedType.SIZE_NAME;
import static org.eclipse.ocl.utilities.PredefinedType.SORTED_BY;
import static org.eclipse.ocl.utilities.PredefinedType.SORTED_BY_NAME;
import static org.eclipse.ocl.utilities.PredefinedType.STARTS_WITH;
import static org.eclipse.ocl.utilities.PredefinedType.STARTS_WITH_NAME;
import static org.eclipse.ocl.utilities.PredefinedType.SUBSTITUTE_ALL;
import static org.eclipse.ocl.utilities.PredefinedType.SUBSTITUTE_ALL_NAME;
import static org.eclipse.ocl.utilities.PredefinedType.SUBSTITUTE_FIRST;
import static org.eclipse.ocl.utilities.PredefinedType.SUBSTITUTE_FIRST_NAME;
import static org.eclipse.ocl.utilities.PredefinedType.SUBSTRING;
import static org.eclipse.ocl.utilities.PredefinedType.SUBSTRING_NAME;
import static org.eclipse.ocl.utilities.PredefinedType.SUB_ORDERED_SET;
import static org.eclipse.ocl.utilities.PredefinedType.SUB_ORDERED_SET_NAME;
import static org.eclipse.ocl.utilities.PredefinedType.SUB_SEQUENCE;
import static org.eclipse.ocl.utilities.PredefinedType.SUB_SEQUENCE_NAME;
import static org.eclipse.ocl.utilities.PredefinedType.SUM;
import static org.eclipse.ocl.utilities.PredefinedType.SUM_NAME;
import static org.eclipse.ocl.utilities.PredefinedType.SYMMETRIC_DIFFERENCE;
import static org.eclipse.ocl.utilities.PredefinedType.SYMMETRIC_DIFFERENCE_NAME;
import static org.eclipse.ocl.utilities.PredefinedType.TIMES;
import static org.eclipse.ocl.utilities.PredefinedType.TIMES_NAME;
import static org.eclipse.ocl.utilities.PredefinedType.TOKENIZE;
import static org.eclipse.ocl.utilities.PredefinedType.TOKENIZE_NAME;
import static org.eclipse.ocl.utilities.PredefinedType.TO_BOOLEAN;
import static org.eclipse.ocl.utilities.PredefinedType.TO_BOOLEAN_NAME;
import static org.eclipse.ocl.utilities.PredefinedType.TO_INTEGER;
import static org.eclipse.ocl.utilities.PredefinedType.TO_INTEGER_NAME;
import static org.eclipse.ocl.utilities.PredefinedType.TO_LOWER;
import static org.eclipse.ocl.utilities.PredefinedType.TO_LOWER_CASE;
import static org.eclipse.ocl.utilities.PredefinedType.TO_LOWER_CASE_NAME;
import static org.eclipse.ocl.utilities.PredefinedType.TO_LOWER_NAME;
import static org.eclipse.ocl.utilities.PredefinedType.TO_REAL;
import static org.eclipse.ocl.utilities.PredefinedType.TO_REAL_NAME;
import static org.eclipse.ocl.utilities.PredefinedType.TO_STRING;
import static org.eclipse.ocl.utilities.PredefinedType.TO_STRING_NAME;
import static org.eclipse.ocl.utilities.PredefinedType.TO_UPPER;
import static org.eclipse.ocl.utilities.PredefinedType.TO_UPPER_CASE;
import static org.eclipse.ocl.utilities.PredefinedType.TO_UPPER_CASE_NAME;
import static org.eclipse.ocl.utilities.PredefinedType.TO_UPPER_NAME;
import static org.eclipse.ocl.utilities.PredefinedType.TRIM;
import static org.eclipse.ocl.utilities.PredefinedType.TRIM_NAME;
import static org.eclipse.ocl.utilities.PredefinedType.UNION;
import static org.eclipse.ocl.utilities.PredefinedType.UNION_NAME;
import static org.eclipse.ocl.utilities.PredefinedType.XOR;
import static org.eclipse.ocl.utilities.PredefinedType.XOR_NAME;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.ocl.Environment;
import org.eclipse.ocl.SemanticException;
import org.eclipse.ocl.cst.CSTNode;
import org.eclipse.ocl.expressions.TypeExp;
import org.eclipse.ocl.expressions.Variable;
import org.eclipse.ocl.internal.l10n.OCLMessages;
import org.eclipse.ocl.lpg.AbstractParser;
import org.eclipse.ocl.lpg.BasicEnvironment;
import org.eclipse.ocl.lpg.ProblemHandler;
import org.eclipse.ocl.lpg.StringProblemHandler;
import org.eclipse.ocl.options.ParsingOptions;
import org.eclipse.ocl.types.AnyType;
import org.eclipse.ocl.types.BagType;
import org.eclipse.ocl.types.CollectionType;
import org.eclipse.ocl.types.MessageType;
import org.eclipse.ocl.types.OCLStandardLibrary;
import org.eclipse.ocl.types.OrderedSetType;
import org.eclipse.ocl.types.PrimitiveType;
import org.eclipse.ocl.types.SequenceType;
import org.eclipse.ocl.types.SetType;
import org.eclipse.ocl.types.TypeType;
import org.eclipse.ocl.utilities.OCLFactory;
import org.eclipse.ocl.utilities.PredefinedType;
import org.eclipse.ocl.utilities.TypedElement;
import org.eclipse.ocl.utilities.UMLReflection;

/**
 * Convenience utilities for working with the types defined by the
 * {@linkplain OCLStandardLibrary OCL Standard Library}.
 * <p>
 * See the {@link Environment} class for a description of the generic type
 * parameters of this class.
 * </p>
 *
 * @author Christian W. Damus (cdamus)
 */
public final class OCLStandardLibraryUtil {

	/**
	 * The name of the tuple part carrying elements of the source collection in
	 * a <tt>product</tt> operation.
	 */
	public static final String PRODUCT_FIRST = "first"; //$NON-NLS-1$

	/**
	 * The name of the tuple part carrying elements of the argument collection
	 * in a <tt>product</tt> operation.
	 */
	public static final String PRODUCT_SECOND = "second"; //$NON-NLS-1$

	private static final Map<String, Integer> operationCodes = new java.util.HashMap<String, Integer>();

	private static final Map<String, Integer> oclAnyOperationCodes = new java.util.HashMap<String, Integer>();

	static {
		operationCodes.put(PLUS_NAME, PLUS);
		operationCodes.put(MINUS_NAME, MINUS);
		operationCodes.put(TIMES_NAME, TIMES);
		operationCodes.put(DIVIDE_NAME, DIVIDE);
		operationCodes.put(AND_NAME, AND);
		operationCodes.put(NOT_NAME, NOT);
		operationCodes.put(OR_NAME, OR);
		operationCodes.put(IMPLIES_NAME, IMPLIES);
		operationCodes.put(ABS_NAME, ABS);
		operationCodes.put(DIV_NAME, DIV);
		operationCodes.put(MOD_NAME, MOD);
		operationCodes.put(MAX_NAME, MAX);
		operationCodes.put(MIN_NAME, MIN);
		operationCodes.put(SIZE_NAME, SIZE);
		operationCodes.put(CONCAT_NAME, CONCAT);
		operationCodes.put(SUBSTRING_NAME, SUBSTRING);
		operationCodes.put(TO_INTEGER_NAME, TO_INTEGER);
		operationCodes.put(TO_REAL_NAME, TO_REAL);
		operationCodes.put(XOR_NAME, XOR);
		operationCodes.put(FLOOR_NAME, FLOOR);
		operationCodes.put(ROUND_NAME, ROUND);
		operationCodes.put(TO_LOWER_NAME, TO_LOWER);
		operationCodes.put(TO_UPPER_NAME, TO_UPPER);
		operationCodes.put(ALL_INSTANCES_NAME, ALL_INSTANCES);
		operationCodes.put(EQUAL_NAME, EQUAL);
		operationCodes.put(NOT_EQUAL_NAME, NOT_EQUAL);
		operationCodes.put(OCL_AS_TYPE_NAME, OCL_AS_TYPE);
		operationCodes.put(OCL_IS_KIND_OF_NAME, OCL_IS_KIND_OF);
		operationCodes.put(OCL_IS_TYPE_OF_NAME, OCL_IS_TYPE_OF);
		operationCodes.put(OCL_IS_UNDEFINED_NAME, OCL_IS_UNDEFINED);
		operationCodes.put(OCL_IS_INVALID_NAME, OCL_IS_INVALID);
		operationCodes.put(LESS_THAN_NAME, LESS_THAN);
		operationCodes.put(GREATER_THAN_NAME, GREATER_THAN);
		operationCodes.put(LESS_THAN_EQUAL_NAME, LESS_THAN_EQUAL);
		operationCodes.put(GREATER_THAN_EQUAL_NAME, GREATER_THAN_EQUAL);
		operationCodes.put(OCL_IS_NEW_NAME, OCL_IS_NEW);
		operationCodes.put(OCL_IS_IN_STATE_NAME, OCL_IS_IN_STATE);
		operationCodes.put(HAS_RETURNED_NAME, HAS_RETURNED);
		operationCodes.put(RESULT_NAME, RESULT);
		operationCodes.put(IS_SIGNAL_SENT_NAME, IS_SIGNAL_SENT);
		operationCodes.put(IS_OPERATION_CALL_NAME, IS_OPERATION_CALL);
		operationCodes.put(COUNT_NAME, COUNT);
		operationCodes.put(EXCLUDES_NAME, EXCLUDES);
		operationCodes.put(EXCLUDES_ALL_NAME, EXCLUDES_ALL);
		operationCodes.put(INCLUDES_NAME, INCLUDES);
		operationCodes.put(INCLUDES_ALL_NAME, INCLUDES_ALL);
		operationCodes.put(IS_EMPTY_NAME, IS_EMPTY);
		operationCodes.put(NOT_EMPTY_NAME, NOT_EMPTY);
		operationCodes.put(PRODUCT_NAME, PRODUCT);
		operationCodes.put(SUM_NAME, SUM);
		operationCodes.put(AS_BAG_NAME, AS_BAG);
		operationCodes.put(AS_ORDERED_SET_NAME, AS_ORDERED_SET);
		operationCodes.put(AS_SEQUENCE_NAME, AS_SEQUENCE);
		operationCodes.put(AS_SET_NAME, AS_SET);
		operationCodes.put(EXCLUDING_NAME, EXCLUDING);
		operationCodes.put(FLATTEN_NAME, FLATTEN);
		operationCodes.put(INCLUDING_NAME, INCLUDING);
		operationCodes.put(INTERSECTION_NAME, INTERSECTION);
		operationCodes.put(UNION_NAME, UNION);
		operationCodes.put(AT_NAME, AT);
		operationCodes.put(FIRST_NAME, FIRST);
		operationCodes.put(INDEX_OF_NAME, INDEX_OF);
		operationCodes.put(INSERT_AT_NAME, INSERT_AT);
		operationCodes.put(LAST_NAME, LAST);
		operationCodes.put(PREPEND_NAME, PREPEND);
		operationCodes.put(SUB_SEQUENCE_NAME, SUB_SEQUENCE);
		operationCodes.put(APPEND_NAME, APPEND);
		operationCodes.put(SUB_ORDERED_SET_NAME, SUB_ORDERED_SET);
		operationCodes.put(SYMMETRIC_DIFFERENCE_NAME, SYMMETRIC_DIFFERENCE);
		operationCodes.put(EXISTS_NAME, EXISTS);
		operationCodes.put(FOR_ALL_NAME, FOR_ALL);
		operationCodes.put(IS_UNIQUE_NAME, IS_UNIQUE);
		operationCodes.put(ONE_NAME, ONE);
		operationCodes.put(ANY_NAME, ANY);
		operationCodes.put(COLLECT_NAME, COLLECT);
		operationCodes.put(COLLECT_NESTED_NAME, COLLECT_NESTED);
		operationCodes.put(CLOSURE_NAME, CLOSURE);
		operationCodes.put(SELECT_NAME, SELECT);
		operationCodes.put(REJECT_NAME, REJECT);
		operationCodes.put(SORTED_BY_NAME, SORTED_BY);
		operationCodes.put(TO_BOOLEAN_NAME, TO_BOOLEAN);
		operationCodes.put(TO_STRING_NAME, TO_STRING);
		operationCodes.put(CHARACTERS_NAME, CHARACTERS);
		operationCodes.put(ENDS_WITH_NAME, ENDS_WITH);
		operationCodes.put(EQUALS_IGNORE_CASE_NAME, EQUALS_IGNORE_CASE);
		operationCodes.put(LAST_INDEX_OF_NAME, LAST_INDEX_OF);
		operationCodes.put(MATCHES_NAME, MATCHES);
		operationCodes.put(REPLACE_ALL_NAME, REPLACE_ALL);
		operationCodes.put(REPLACE_FIRST_NAME, REPLACE_FIRST);
		operationCodes.put(STARTS_WITH_NAME, STARTS_WITH);
		operationCodes.put(SUBSTITUTE_ALL_NAME, SUBSTITUTE_ALL);
		operationCodes.put(SUBSTITUTE_FIRST_NAME, SUBSTITUTE_FIRST);
		operationCodes.put(TOKENIZE_NAME, TOKENIZE);
		operationCodes.put(TRIM_NAME, TRIM);
		operationCodes.put(TO_LOWER_CASE_NAME, TO_LOWER_CASE);
		operationCodes.put(TO_UPPER_CASE_NAME, TO_UPPER_CASE);
		operationCodes.put(SELECT_BY_KIND_NAME, SELECT_BY_KIND);
		operationCodes.put(SELECT_BY_TYPE_NAME, SELECT_BY_TYPE);
		operationCodes.put(OCL_AS_SET_NAME, OCL_AS_SET);

		oclAnyOperationCodes.put(EQUAL_NAME, EQUAL);
		oclAnyOperationCodes.put(NOT_EQUAL_NAME, NOT_EQUAL);
		oclAnyOperationCodes.put(OCL_AS_TYPE_NAME, OCL_AS_TYPE);
		oclAnyOperationCodes.put(OCL_IS_KIND_OF_NAME, OCL_IS_KIND_OF);
		oclAnyOperationCodes.put(OCL_IS_TYPE_OF_NAME, OCL_IS_TYPE_OF);
		oclAnyOperationCodes.put(OCL_IS_UNDEFINED_NAME, OCL_IS_UNDEFINED);
		oclAnyOperationCodes.put(OCL_IS_INVALID_NAME, OCL_IS_INVALID);
		oclAnyOperationCodes.put(LESS_THAN_NAME, LESS_THAN);
		oclAnyOperationCodes.put(GREATER_THAN_NAME, GREATER_THAN);
		oclAnyOperationCodes.put(LESS_THAN_EQUAL_NAME, LESS_THAN_EQUAL);
		oclAnyOperationCodes.put(GREATER_THAN_EQUAL_NAME, GREATER_THAN_EQUAL);
		oclAnyOperationCodes.put(OCL_IS_NEW_NAME, OCL_IS_NEW);
		oclAnyOperationCodes.put(OCL_IS_IN_STATE_NAME, OCL_IS_IN_STATE);
		oclAnyOperationCodes.put(TO_STRING_NAME, TO_STRING);
		oclAnyOperationCodes.put(OCL_AS_SET_NAME, OCL_AS_SET);
	}

	// not instantiable by clients
	private OCLStandardLibraryUtil() {
		super();
	}

	/**
	 * Obtains the numeric code of the specified pre-defined (by OCL) operaiton.
	 *
	 * @param operName
	 *            the operation name
	 * @return the corresponding code (as defined by the {@link PredefinedType}
	 *         interface), or <code>0</code> if the operation name is not a
	 *         pre-defined operation
	 *
	 * @see #getOperationName(int)
	 */
	public static int getOperationCode(String operName) {
		Integer code = operationCodes.get(operName);

		return code == null
				? 0
					: code;
	}

	/**
	 * Obtains the numeric code of the specified <tt>OclAny</tt> operaiton.
	 *
	 * @param operName
	 *            the operation name
	 * @return the corresponding code (as defined by the {@link PredefinedType}
	 *         interface), or <code>0</code> if the operation name is not an
	 *         operation of the <tt>OclAny</tt> type
	 */
	public static int getOclAnyOperationCode(String operName) {
		Integer code = oclAnyOperationCodes.get(operName);

		return code == null
				? 0
					: code;
	}

	/**
	 * Returns the operation name corresponding to the opcode.
	 *
	 * @param opcode
	 *            an operation code
	 * @return the name corresponding to the opcode, or <code>null</code> if the
	 *         code is not one defined by the {@link PredefinedType} interface
	 *
	 * @see #getOperationCode(String)
	 */
	public static String getOperationName(int opcode) {
		switch (opcode) {
			case PLUS :
				return PLUS_NAME;
			case MINUS :
				return MINUS_NAME;
			case TIMES :
				return TIMES_NAME;
			case DIVIDE :
				return DIVIDE_NAME;
			case AND :
				return AND_NAME;
			case NOT :
				return NOT_NAME;
			case OR :
				return OR_NAME;
			case IMPLIES :
				return IMPLIES_NAME;
			case ABS :
				return ABS_NAME;
			case DIV :
				return DIV_NAME;
			case MOD :
				return MOD_NAME;
			case MAX :
				return MAX_NAME;
			case MIN :
				return MIN_NAME;
			case SIZE :
				return SIZE_NAME;
			case CONCAT :
				return CONCAT_NAME;
			case SUBSTRING :
				return SUBSTRING_NAME;
			case TO_INTEGER :
				return TO_INTEGER_NAME;
			case TO_REAL :
				return TO_REAL_NAME;
			case XOR :
				return XOR_NAME;
			case FLOOR :
				return FLOOR_NAME;
			case ROUND :
				return ROUND_NAME;
			case TO_LOWER :
				return TO_LOWER_NAME;
			case TO_UPPER :
				return TO_UPPER_NAME;
			case ALL_INSTANCES :
				return ALL_INSTANCES_NAME;
			case EQUAL :
				return EQUAL_NAME;
			case NOT_EQUAL :
				return NOT_EQUAL_NAME;
			case OCL_AS_TYPE :
				return OCL_AS_TYPE_NAME;
			case OCL_IS_KIND_OF :
				return OCL_IS_KIND_OF_NAME;
			case OCL_IS_TYPE_OF :
				return OCL_IS_TYPE_OF_NAME;
			case OCL_IS_UNDEFINED :
				return OCL_IS_UNDEFINED_NAME;
			case OCL_IS_INVALID :
				return OCL_IS_INVALID_NAME;
			case LESS_THAN :
				return LESS_THAN_NAME;
			case GREATER_THAN :
				return GREATER_THAN_NAME;
			case LESS_THAN_EQUAL :
				return LESS_THAN_EQUAL_NAME;
			case GREATER_THAN_EQUAL :
				return GREATER_THAN_EQUAL_NAME;
			case OCL_IS_NEW :
				return OCL_IS_NEW_NAME;
			case OCL_IS_IN_STATE :
				return OCL_IS_IN_STATE_NAME;
			case HAS_RETURNED :
				return HAS_RETURNED_NAME;
			case RESULT :
				return RESULT_NAME;
			case IS_SIGNAL_SENT :
				return IS_SIGNAL_SENT_NAME;
			case IS_OPERATION_CALL :
				return IS_OPERATION_CALL_NAME;
			case COUNT :
				return COUNT_NAME;
			case EXCLUDES :
				return EXCLUDES_NAME;
			case EXCLUDES_ALL :
				return EXCLUDES_ALL_NAME;
			case INCLUDES :
				return INCLUDES_NAME;
			case INCLUDES_ALL :
				return INCLUDES_ALL_NAME;
			case IS_EMPTY :
				return IS_EMPTY_NAME;
			case NOT_EMPTY :
				return NOT_EMPTY_NAME;
			case PRODUCT :
				return PRODUCT_NAME;
			case SUM :
				return SUM_NAME;
			case AS_BAG :
				return AS_BAG_NAME;
			case AS_ORDERED_SET :
				return AS_ORDERED_SET_NAME;
			case AS_SEQUENCE :
				return AS_SEQUENCE_NAME;
			case AS_SET :
				return AS_SET_NAME;
			case EXCLUDING :
				return EXCLUDING_NAME;
			case FLATTEN :
				return FLATTEN_NAME;
			case INCLUDING :
				return INCLUDING_NAME;
			case INTERSECTION :
				return INTERSECTION_NAME;
			case UNION :
				return UNION_NAME;
			case AT :
				return AT_NAME;
			case FIRST :
				return FIRST_NAME;
			case INDEX_OF :
				return INDEX_OF_NAME;
			case INSERT_AT :
				return INSERT_AT_NAME;
			case LAST :
				return LAST_NAME;
			case PREPEND :
				return PREPEND_NAME;
			case SUB_SEQUENCE :
				return SUB_SEQUENCE_NAME;
			case APPEND :
				return APPEND_NAME;
			case SUB_ORDERED_SET :
				return SUB_ORDERED_SET_NAME;
			case SYMMETRIC_DIFFERENCE :
				return SYMMETRIC_DIFFERENCE_NAME;
			case EXISTS :
				return EXISTS_NAME;
			case FOR_ALL :
				return FOR_ALL_NAME;
			case IS_UNIQUE :
				return IS_UNIQUE_NAME;
			case ONE :
				return ONE_NAME;
			case ANY :
				return ANY_NAME;
			case COLLECT :
				return COLLECT_NAME;
			case COLLECT_NESTED :
				return COLLECT_NESTED_NAME;
			case CLOSURE :
				return CLOSURE_NAME;
			case SELECT :
				return SELECT_NAME;
			case REJECT :
				return REJECT_NAME;
			case SORTED_BY :
				return SORTED_BY_NAME;
			case TO_BOOLEAN :
				return TO_BOOLEAN_NAME;
			case TO_STRING :
				return TO_STRING_NAME;
			case CHARACTERS :
				return CHARACTERS_NAME;
			case ENDS_WITH :
				return ENDS_WITH_NAME;
			case EQUALS_IGNORE_CASE :
				return EQUALS_IGNORE_CASE_NAME;
			case LAST_INDEX_OF :
				return LAST_INDEX_OF_NAME;
			case MATCHES :
				return MATCHES_NAME;
			case REPLACE_ALL :
				return REPLACE_ALL_NAME;
			case REPLACE_FIRST :
				return REPLACE_FIRST_NAME;
			case STARTS_WITH :
				return STARTS_WITH_NAME;
			case SUBSTITUTE_ALL :
				return SUBSTITUTE_ALL_NAME;
			case SUBSTITUTE_FIRST :
				return SUBSTITUTE_FIRST_NAME;
			case TOKENIZE :
				return TOKENIZE_NAME;
			case TRIM :
				return TRIM_NAME;
			case TO_LOWER_CASE :
				return TO_LOWER_CASE_NAME;
			case TO_UPPER_CASE :
				return TO_UPPER_CASE_NAME;
			case SELECT_BY_KIND :
				return SELECT_BY_KIND_NAME;
			case SELECT_BY_TYPE :
				return SELECT_BY_TYPE_NAME;
			case OCL_AS_SET :
				return OCL_AS_SET_NAME;
			default :
				return ""; //$NON-NLS-1$
		}
	}

	/**
	 * Obtains the result type of the specified operation from the OCL Standard
	 * Library. Many of the OCL Standard Library operations are either generic
	 * themselves or defined by generic types, so the return results depend on
	 * the argument and source types.
	 *
	 * @param env
	 *            an OCL environment (indicating the metamodel binding)
	 * @param sourceType
	 *            the type of the operation source (object on which the
	 *            operation is called)
	 * @param opcode
	 *            the operation's code
	 * @param args
	 *            the arguments of the operation call, as expressions or
	 *            variables
	 * @return the result type of the corresponding operation
	 *
	 * @throws SemanticException
	 *             if any of the argument types does not correspond to the
	 *             source type and/or expected parameter types of the operation
	 *
	 * @see #getOperationCode(String)
	 *
	 * @deprecated Use the
	 *             {@link #getResultTypeOf(Object, Environment, Object, int, List)}
	 *             method, instead, which doesn't fail on the first problem
	 */
	@Deprecated
	public static <PK, C, O, P, EL, PM, ST, COA, SSA, CT, CLS, E> C getResultTypeOf(
			Environment<PK, C, O, P, EL, PM, ST, COA, SSA, CT, CLS, E> env,
			C sourceType, int opcode, List<? extends TypedElement<C>> args)
					throws SemanticException {

		StringProblemHandler handler = null;
		ProblemHandler oldHandler = null;
		BasicEnvironment benv = OCLUtil.getAdapter(env, BasicEnvironment.class);

		if (benv != null) {
			AbstractParser parser = benv.getParser();
			oldHandler = benv.getProblemHandler();
			handler = new StringProblemHandler(parser);

			benv.setProblemHandler(new ProblemHandlerWrapper.Tee(oldHandler,
				handler));
		}

		try {
			C result = getResultTypeOf(null, env, sourceType, opcode, args);

			if (result == null) {
				String message = handler != null
						? handler.getProblemString()
							: "No handler"; //$NON-NLS-1$
						throw new SemanticException(message);
			}

			return result;
		} finally {
			if (benv != null) {
				benv.setProblemHandler(oldHandler);
			}
		}
	}

	/**
	 * Obtains the result type of the specified operation from the OCL Standard
	 * Library. Many of the OCL Standard Library operations are either generic
	 * themselves or defined by generic types, so the return results depend on
	 * the argument and source types.
	 *
	 * @param env
	 *            an OCL environment (indicating the metamodel binding)
	 * @param sourceType
	 *            the type of the operation source (object on which the
	 *            operation is called)
	 * @param opcode
	 *            the operation's code
	 * @param args
	 *            the arguments of the operation call, as expressions or
	 *            variables
	 * @return the result type of the corresponding operation, or null after
	 *         reporting a problem to env if any of the argument types do not
	 *         correspond to the source type and/or expected parameter types of
	 *         the operation
	 *
	 * @see #getOperationCode(String)
	 */
	public static <PK, C, O, P, EL, PM, ST, COA, SSA, CT, CLS, E> C getResultTypeOf(
			Object problemObject,
			Environment<PK, C, O, P, EL, PM, ST, COA, SSA, CT, CLS, E> env,
			C sourceType, int opcode, List<? extends TypedElement<C>> args) {

		if (sourceType instanceof PrimitiveType<?>) {
			return getPrimitiveTypeResultTypeOf(problemObject, env, sourceType,
				opcode, args);
		} else if (sourceType instanceof CollectionType<?, ?>) {
			if (sourceType instanceof BagType<?, ?>) {
				@SuppressWarnings("unchecked")
				BagType<C, O> bagType = (BagType<C, O>) sourceType;
				return getBagTypeResultTypeOf(problemObject, env, bagType,
					opcode, args);
			} else if (sourceType instanceof SetType<?, ?>) {
				@SuppressWarnings("unchecked")
				SetType<C, O> setType = (SetType<C, O>) sourceType;
				return getSetTypeResultTypeOf(problemObject, env, setType,
					opcode, args);
			} else if (sourceType instanceof OrderedSetType<?, ?>) {
				@SuppressWarnings("unchecked")
				OrderedSetType<C, O> orderedSetType = (OrderedSetType<C, O>) sourceType;
				return getOrderedSetTypeResultTypeOf(problemObject, env,
					orderedSetType, opcode, args);
			} else if (sourceType instanceof SequenceType<?, ?>) {
				@SuppressWarnings("unchecked")
				SequenceType<C, O> seqType = (SequenceType<C, O>) sourceType;
				return getSequenceTypeResultTypeOf(problemObject, env, seqType,
					opcode, args);
			}
			@SuppressWarnings("unchecked")
			CollectionType<C, O> collType = (CollectionType<C, O>) sourceType;
			return getCollectionTypeResultTypeOf(problemObject, env, collType,
				opcode, args);
		} else if (sourceType instanceof TypeType<?, ?>) {
			@SuppressWarnings("unchecked")
			TypeType<C, O> typeType = (TypeType<C, O>) sourceType;
			return getTypeTypeResultTypeOf(problemObject, env, typeType,
				opcode, args);
		} else if (sourceType instanceof MessageType<?, ?, ?>) {
			@SuppressWarnings("unchecked")
			MessageType<C, O, P> messageType = (MessageType<C, O, P>) sourceType;
			return getMessageTypeResultTypeOf(problemObject, env, messageType,
				opcode, args);
		}

		return getAnyTypeResultTypeOf(problemObject, env, sourceType, opcode,
			args);
	}

	/**
	 * Helper for the {@link #getResultTypeOf(Environment, Object, int, List)}
	 * dealing with the {@link AnyType}s.
	 */
	private static <PK, C, O, P, EL, PM, ST, COA, SSA, CT, CLS, E> C getAnyTypeResultTypeOf(
			Object problemObject,
			Environment<PK, C, O, P, EL, PM, ST, COA, SSA, CT, CLS, E> env,
			C sourceType, int opcode, List<? extends TypedElement<C>> args) {

		OCLStandardLibrary<C> stdlib = env.getOCLStandardLibrary();
		UMLReflection<PK, C, O, P, EL, PM, ?, COA, SSA, CT> uml = env
				.getUMLReflection();

		TypedElement<C> arg;
		C argType;

		switch (opcode) {
			case NOT_EQUAL :
			case EQUAL :
				/*
				 * Performs a conformance test for primitives, EClass, EEnum,
				 * TupleType
				 */
				arg = args.get(0);
				argType = arg.getType();

				return stdlib.getBoolean();
			case LESS_THAN :
			case GREATER_THAN :
			case LESS_THAN_EQUAL :
			case GREATER_THAN_EQUAL :
				arg = args.get(0);
				argType = arg.getType();

				O oper = null;
				try {
					oper = TypeUtil.findOperationMatching(env, sourceType,
						getOperationName(opcode), args);

					if ((oper == null)
							&& ParsingOptions.getValue(env,
								ParsingOptions.USE_COMPARE_TO_OPERATION)) {
						// source must either be a DataType that is Comparable,
						// or
						// else be a Elass, with an operation:
						// int compareTo(object)
						if (uml.isDataType(sourceType)) {
							if (uml.isComparable(sourceType)) {
								TypeUtil.checkMutuallyComparable(problemObject,
									env, sourceType, argType, opcode);

								// warn about non-standard Java-ism
								warning(env, OCLMessages.NonStd_CompareTo_,
									"getAnyTypeResultOf", problemObject); //$NON-NLS-1$

								return stdlib.getBoolean();
							}

							String message = OCLMessages.bind(
								OCLMessages.SourceEClass_ERROR_,
								getOperationName(opcode));
							error(env, message,
								"anyTypeResultTypeOf", problemObject); //$NON-NLS-1$
							return null;
						}

						// Check that the type has a method named "compareTo"
						oper = TypeUtil.findOperationMatching(env, sourceType,
							"compareTo", //$NON-NLS-1$
							args);

						if (oper != null) {
							// warn about non-standard Java-ism
							warning(env, OCLMessages.NonStd_CompareTo_,
								"getAnyTypeResultOf", problemObject); //$NON-NLS-1$
						}
					}
				} catch (Exception e) {
					String message = OCLMessages.bind(
						OCLMessages.SourceOperationCompareTo_ERROR_,
						getOperationName(opcode));
					error(env, message, "anyTypeResultTypeOf", problemObject); //$NON-NLS-1$
					return null;
				}

				if ((oper != null)
						&& "compareTo".equals(uml.getName(oper)) //$NON-NLS-1$
						&& (TypeUtil.resolveType(env, uml.getOCLType(oper)) != stdlib
						.getInteger())) {
					String message = OCLMessages.ResultCompareToInt_ERROR_;
					error(env, message, "anyTypeResultTypeOf", problemObject); //$NON-NLS-1$
					return null;
				}

				return stdlib.getBoolean();

			case OCL_IS_KIND_OF :
			case OCL_IS_TYPE_OF :
			case OCL_IS_NEW :
			case OCL_IS_IN_STATE :
				return stdlib.getBoolean();
			case OCL_AS_TYPE :
				arg = args.get(0);
				if (arg instanceof TypeExp<?>) {
					TypeExp<C> typeExp = (TypeExp<C>) arg;
					argType = typeExp.getReferredType();
				} else {
					argType = arg.getType();
				}

				if (sourceType instanceof CollectionType<?, ?>) {
					String message = OCLMessages.bind(
						OCLMessages.Noncomforming_ERROR_, uml
						.getName(sourceType), getOperationName(opcode));
					error(env, message, "anyTypeResultTypeOf", problemObject); //$NON-NLS-1$
					return null;
				}
				// we can require neither a common supertype nor that type2
				// and type1 have any conformance relationship whatsoever
				// because the run-time 'type' may conform to 'arg'
				// commonSuperType(argEType, type);
				// type1AsType2(type, argEType);
				return argType;
			case OCL_IS_UNDEFINED :
			case OCL_IS_INVALID :
				return stdlib.getBoolean();
			case OCL_AS_SET :
				return getSetType(env, env.getOCLFactory(), sourceType);
		}

		// unknown operation (shouldn't get here)
		return null;
	}

	/**
	 * Helper for the {@link #getResultTypeOf(Environment, Object, int, List)}
	 * dealing with the {@link PrimitiveType}s.
	 */
	private static <PK, C, O, P, EL, PM, ST, COA, SSA, CT, CLS, E> C getPrimitiveTypeResultTypeOf(
			Object problemObject,
			Environment<PK, C, O, P, EL, PM, ST, COA, SSA, CT, CLS, E> env,
			C sourceType, int opcode, List<? extends TypedElement<C>> args) {

		OCLStandardLibrary<C> stdlib = env.getOCLStandardLibrary();

		C argType;

		switch (opcode) {

			case PLUS :
			case TIMES :
				argType = args.get(0).getType();

				return TypeUtil.commonSuperType(problemObject, env, argType,
					sourceType);
			case DIVIDE :
				argType = args.get(0).getType();

				// assert the relationship between the types
				TypeUtil.commonSuperType(problemObject, env, argType,
					sourceType);
				return stdlib.getReal();
			case MINUS :
				// unary minus
				if (args == null || args.size() == 0) {
					return sourceType;
				}

				argType = args.get(0).getType();
				return TypeUtil.commonSuperType(problemObject, env, argType,
					sourceType);
			case GREATER_THAN :
			case LESS_THAN :
			case GREATER_THAN_EQUAL :
			case LESS_THAN_EQUAL :
			case IMPLIES :
			case XOR :
			case NOT :
			case AND :
			case OR :
			case ENDS_WITH :
			case EQUALS_IGNORE_CASE :
			case MATCHES :
			case STARTS_WITH :
			case TO_BOOLEAN :
				return stdlib.getBoolean();
			case MIN :
			case MAX :
			case ABS :
			case DIV :
			case MOD :
			case SUBSTRING :
			case CONCAT :
				return sourceType;
			case FLOOR :
			case TO_INTEGER :
			case SIZE :
			case ROUND :
			case INDEX_OF :
			case LAST_INDEX_OF :
				return stdlib.getInteger();
			case TO_REAL :
				return stdlib.getReal();
			case AT :
			case REPLACE_ALL :
			case REPLACE_FIRST :
			case SUBSTITUTE_ALL :
			case SUBSTITUTE_FIRST :
			case TO_LOWER :
			case TO_LOWER_CASE :
			case TO_STRING :
			case TO_UPPER :
			case TO_UPPER_CASE :
			case TRIM :
				return stdlib.getString();
			case CHARACTERS :
			case TOKENIZE :
				return getSequenceType(env, env.getOCLFactory(), stdlib.getString());
		}

		// must be an operation defined for all types, then
		return getAnyTypeResultTypeOf(problemObject, env, sourceType, opcode,
			args);
	}

	/**
	 * Helper for the {@link #getResultTypeOf(Environment, Object, int, List)}
	 * dealing with the {@link BagType}s.
	 */
	private static <PK, C, O, P, EL, PM, ST, COA, SSA, CT, CLS, E> C getBagTypeResultTypeOf(
			Object problemObject,
			Environment<PK, C, O, P, EL, PM, ST, COA, SSA, CT, CLS, E> env,
			BagType<C, O> bagType, int opcode,
			List<? extends TypedElement<C>> args) {

		OCLStandardLibrary<C> stdlib = env.getOCLStandardLibrary();
		OCLFactory oclFactory = env.getOCLFactory();

		@SuppressWarnings("unchecked")
		C sourceType = (C) bagType;
		C argType;
		C argElementType;

		C elemType = bagType.getElementType();

		switch (opcode) {

			case EQUAL :
			case NOT_EQUAL :
				return stdlib.getBoolean();
			case UNION :
				argType = args.get(0).getType();
				argElementType = getElementType(argType);
				return getBagType(env, oclFactory, TypeUtil.commonSuperType(
					problemObject, env, elemType, argElementType));
			case INCLUDING :
				argType = args.get(0).getType();
				return getBagType(env, oclFactory, TypeUtil.commonSuperType(
					problemObject, env, elemType, argType));
			case INTERSECTION :
				argType = args.get(0).getType();
				argElementType = getElementType(argType);

				if (argType instanceof SetType<?, ?>) {
					return getSetType(env, oclFactory, TypeUtil
						.commonSuperType(problemObject, env, elemType,
							argElementType));
				} else {
					return getBagType(env, oclFactory, TypeUtil
						.commonSuperType(problemObject, env, elemType,
							argElementType));
				}
			case EXCLUDING :
				return sourceType;
			case COUNT :
				return stdlib.getInteger();
			case FLATTEN :
				if (!(elemType instanceof CollectionType<?, ?>)) {
					return sourceType;
				}
				return getBagType(env, oclFactory, CollectionUtil
					.getFlattenedElementType(bagType));
			case AS_BAG :
				return sourceType;
			case AS_SEQUENCE :
				return getSequenceType(env, oclFactory, elemType);
			case AS_SET :
				return getSetType(env, oclFactory, elemType);
			case AS_ORDERED_SET :
				return getOrderedSetType(env, oclFactory, elemType);
			case SELECT :
			case REJECT :
				return sourceType;
			case SELECT_BY_KIND :
			case SELECT_BY_TYPE :
				TypedElement<C> arg = args.get(0);
				if (arg instanceof TypeExp<?>) {
					TypeExp<C> typeExp = (TypeExp<C>) arg;
					argType = typeExp.getReferredType();
				} else {
					argType = arg.getType();
				}
				return getBagType(env, env.getOCLFactory(), argType);
			case SORTED_BY :
				return getSequenceType(env, oclFactory, elemType);
			case COLLECT_NESTED :
				return getBagType(env, oclFactory, stdlib.getT2());
		}

		return getCollectionTypeResultTypeOf(problemObject, env, bagType,
			opcode, args);
	}

	/**
	 * Helper for the {@link #getResultTypeOf(Environment, Object, int, List)}
	 * dealing with the {@link SetType}s.
	 */
	private static <PK, C, O, P, EL, PM, ST, COA, SSA, CT, CLS, E> C getSetTypeResultTypeOf(
			Object problemObject,
			Environment<PK, C, O, P, EL, PM, ST, COA, SSA, CT, CLS, E> env,
			SetType<C, O> setType, int opcode,
			List<? extends TypedElement<C>> args) {

		OCLStandardLibrary<C> stdlib = env.getOCLStandardLibrary();
		OCLFactory oclFactory = env.getOCLFactory();

		@SuppressWarnings("unchecked")
		C sourceType = (C) setType;
		C argType;
		C argElementType;

		C elemType = setType.getElementType();
		C resultType;

		switch (opcode) {

			case EQUAL :
			case NOT_EQUAL :
				return stdlib.getBoolean();

			case UNION :
				argType = args.get(0).getType();
				argElementType = getElementType(argType);

				C newElementType = TypeUtil.commonSuperType(problemObject, env,
					elemType, argElementType);

				if (argType instanceof BagType<?, ?>) {
					resultType = getBagType(env, oclFactory, newElementType);
				} else {
					resultType = getSetType(env, oclFactory, newElementType);
				}

				return resultType;

			case MINUS :
			case SYMMETRIC_DIFFERENCE :
				argType = args.get(0).getType();
				argElementType = getElementType(argType);

				resultType = getSetType(env, oclFactory, TypeUtil
					.commonSuperType(problemObject, env, elemType,
						argElementType));
				return resultType;

			case INCLUDING :
				argType = args.get(0).getType();
				resultType = getSetType(env, oclFactory, TypeUtil
					.commonSuperType(problemObject, env, elemType, argType));
				return resultType;

			case INTERSECTION :
				argType = args.get(0).getType();
				argElementType = getElementType(argType);

				resultType = getSetType(env, oclFactory, TypeUtil
					.commonSuperType(problemObject, env, elemType,
						argElementType));

				// both variants in both set and bag return the source type
				return sourceType;

			case EXCLUDING :
				return sourceType;
			case COUNT :
				return stdlib.getInteger();
			case FLATTEN :
				if (!(elemType instanceof CollectionType<?, ?>)) {
					return sourceType;
				}

				resultType = getSetType(env, oclFactory, CollectionUtil
					.getFlattenedElementType(setType));

				return resultType;
			case AS_BAG :
				return getBagType(env, oclFactory, elemType);
			case AS_SEQUENCE :
				return getSequenceType(env, oclFactory, elemType);
			case AS_SET :
				return sourceType;
			case AS_ORDERED_SET :
				return getOrderedSetType(env, oclFactory, elemType);
			case SELECT :
			case REJECT :
				return sourceType;
			case SELECT_BY_KIND :
			case SELECT_BY_TYPE :
				TypedElement<C> arg = args.get(0);
				if (arg instanceof TypeExp<?>) {
					TypeExp<C> typeExp = (TypeExp<C>) arg;
					argType = typeExp.getReferredType();
				} else {
					argType = arg.getType();
				}
				return getSetType(env, env.getOCLFactory(), argType);
			case SORTED_BY :
				return getOrderedSetType(env, oclFactory, elemType);
			case COLLECT_NESTED :
				return getBagType(env, oclFactory, stdlib.getT2());
		}

		return getCollectionTypeResultTypeOf(problemObject, env, setType,
			opcode, args);
	}

	private static <C, O> C getElementType(C type) {
		if (type instanceof CollectionType<?, ?>) {
			@SuppressWarnings("unchecked")
			CollectionType<C, ?> castType = (CollectionType<C, ?>) type;
			return castType.getElementType();
		} else {
			return null;
		}
	}

	/**
	 * Helper for the {@link #getResultTypeOf(Environment, Object, int, List)}
	 * dealing with the {@link OrderedSetType}s.
	 */
	private static <PK, C, O, P, EL, PM, ST, COA, SSA, CT, CLS, E> C getOrderedSetTypeResultTypeOf(
			Object problemObject,
			Environment<PK, C, O, P, EL, PM, ST, COA, SSA, CT, CLS, E> env,
			OrderedSetType<C, O> orderedSetType, int opcode,
			List<? extends TypedElement<C>> args) {

		OCLStandardLibrary<C> stdlib = env.getOCLStandardLibrary();
		OCLFactory oclFactory = env.getOCLFactory();

		@SuppressWarnings("unchecked")
		C sourceType = (C) orderedSetType;
		C argType;

		C elemType = orderedSetType.getElementType();

		switch (opcode) {

			case EQUAL :
			case NOT_EQUAL :
				return stdlib.getBoolean();

			case INDEX_OF :
				return stdlib.getInteger();

			case APPEND :
			case PREPEND :
				argType = args.get(0).getType();

				return getOrderedSetType(env, oclFactory, TypeUtil
					.commonSuperType(problemObject, env, elemType, argType));

			case INSERT_AT :
				argType = args.get(1).getType(); // arg 0 is the index

				return getOrderedSetType(env, oclFactory, TypeUtil
					.commonSuperType(problemObject, env, elemType, argType));

			case SUB_ORDERED_SET :
				return sourceType;
			case SELECT_BY_KIND :
			case SELECT_BY_TYPE :
				TypedElement<C> arg = args.get(0);
				if (arg instanceof TypeExp<?>) {
					TypeExp<C> typeExp = (TypeExp<C>) arg;
					argType = typeExp.getReferredType();
				} else {
					argType = arg.getType();
				}
				return getOrderedSetType(env, env.getOCLFactory(), argType);

			case AT :
			case FIRST :
			case LAST :
				return elemType;

			case AS_SET :
				return getSetType(env, oclFactory, elemType);
			case AS_BAG :
				return getBagType(env, oclFactory, elemType);
			case AS_SEQUENCE :
				return getSequenceType(env, oclFactory, elemType);
		}

		@SuppressWarnings("unchecked")
		SetType<C, O> setType = (SetType<C, O>) getSetType(env, oclFactory,
			elemType);
		return getSetTypeResultTypeOf(problemObject, env, setType, opcode, args);
	}

	/**
	 * Helper for the {@link #getResultTypeOf(Environment, Object, int, List)}
	 * dealing with the {@link SequenceType}s.
	 */
	private static <PK, C, O, P, EL, PM, ST, COA, SSA, CT, CLS, E> C getSequenceTypeResultTypeOf(
			Object problemObject,
			Environment<PK, C, O, P, EL, PM, ST, COA, SSA, CT, CLS, E> env,
			SequenceType<C, O> seqType, int opcode,
			List<? extends TypedElement<C>> args) {

		OCLStandardLibrary<C> stdlib = env.getOCLStandardLibrary();
		OCLFactory oclFactory = env.getOCLFactory();

		@SuppressWarnings("unchecked")
		C sourceType = (C) seqType;
		C argType;
		C argElementType;
		C elemType = seqType.getElementType();

		switch (opcode) {

			case COUNT :
			case INDEX_OF :
				return stdlib.getInteger();
			case EQUAL :
			case NOT_EQUAL :
				return stdlib.getBoolean();
			case UNION :
				argType = args.get(0).getType();
				argElementType = getElementType(argType);

				return getSequenceType(env, oclFactory, TypeUtil
					.commonSuperType(problemObject, env, elemType,
						argElementType));
			case INCLUDING :
			case APPEND :
			case PREPEND :
				argType = args.get(0).getType();

				return getSequenceType(env, oclFactory, TypeUtil
					.commonSuperType(problemObject, env, elemType, argType));
			case INSERT_AT :
				argType = args.get(1).getType(); // arg 0 is the index

				return getSequenceType(env, oclFactory, TypeUtil
					.commonSuperType(problemObject, env, elemType, argType));
			case EXCLUDING :
				return sourceType;
			case FLATTEN :
				if (!(elemType instanceof CollectionType<?, ?>)) {
					return sourceType;
				}

				return getSequenceType(env, oclFactory, CollectionUtil
					.getFlattenedElementType(seqType));
			case AT :
			case FIRST :
			case LAST :
				return elemType;
			case AS_BAG :
				return getBagType(env, oclFactory, elemType);
			case AS_SEQUENCE :
			case SUB_SEQUENCE :
				return sourceType;
			case AS_SET :
				return getSetType(env, oclFactory, elemType);
			case AS_ORDERED_SET :
				return getOrderedSetType(env, oclFactory, elemType);
			case SELECT :
			case REJECT :
			case SELECT_BY_KIND :
			case SELECT_BY_TYPE :
				TypedElement<C> arg = args.get(0);
				if (arg instanceof TypeExp<?>) {
					TypeExp<C> typeExp = (TypeExp<C>) arg;
					argType = typeExp.getReferredType();
				} else {
					argType = arg.getType();
				}
				return getSequenceType(env, env.getOCLFactory(), argType);
			case SORTED_BY :
				return sourceType;
			case COLLECT_NESTED :
				return getSequenceType(env, oclFactory, stdlib.getT2());
		}

		return getCollectionTypeResultTypeOf(problemObject, env, seqType,
			opcode, args);
	}

	/**
	 * Helper for the {@link #getResultTypeOf(Environment, Object, int, List)}
	 * dealing with the general {@link CollectionType}s.
	 */
	private static <PK, C, O, P, EL, PM, ST, COA, SSA, CT, CLS, E> C getCollectionTypeResultTypeOf(
			Object problemObject,
			Environment<PK, C, O, P, EL, PM, ST, COA, SSA, CT, CLS, E> env,
			CollectionType<C, O> collType, int opcode,
			List<? extends TypedElement<C>> args) {

		OCLStandardLibrary<C> stdlib = env.getOCLStandardLibrary();

		C argType;

		switch (opcode) {
			case SIZE :
			case COUNT :
				return stdlib.getInteger();
			case INCLUDES :
			case EXCLUDES :
			case INCLUDES_ALL :
			case EXCLUDES_ALL :
			case IS_EMPTY :
			case NOT_EMPTY :
			case EQUAL :
			case NOT_EQUAL :
			case OCL_IS_UNDEFINED :
			case OCL_IS_INVALID :
				return stdlib.getBoolean();
			case MAX : {
				C type = collType.getElementType();
				if (type != stdlib.getReal() && type != stdlib.getInteger()) {
					String message = OCLMessages.MaxOperator_ERROR_;
					error(env, message,
						"collectionTypeResultTypeOf", problemObject); //$NON-NLS-1$
					return null;
				}
				return type;
			}
			case MIN : {
				C type = collType.getElementType();
				if (type != stdlib.getReal() && type != stdlib.getInteger()) {
					String message = OCLMessages.MinOperator_ERROR_;
					error(env, message,
						"collectionTypeResultTypeOf", problemObject); //$NON-NLS-1$
					return null;
				}
				return type;
			}
			case SUM : {
				C type = collType.getElementType();
				if (type != stdlib.getReal() && type != stdlib.getInteger() && type != stdlib.getUnlimitedNatural()) {
					String message = OCLMessages.SumOperator_ERROR_;
					error(env, message,
						"collectionTypeResultTypeOf", problemObject); //$NON-NLS-1$
					return null;
				}
				return type;
			}
			case PRODUCT :
				/*
				 * The result type is: Set(Tuple(first:T, second:T2) where T is
				 * the elementType of the source, and T2 is the elementType of
				 * the argument.
				 */
				C t = collType.getElementType();
				argType = args.get(0).getType();
				C t2 = getElementType(argType);

				OCLFactory oclFactory = env.getOCLFactory();

				return getSetType(env, oclFactory, getTupleType(env,
					oclFactory, createTupleParts(env, t, t2)));
			case EXISTS :
			case FOR_ALL :
			case IS_UNIQUE :
			case ONE :
				return stdlib.getBoolean();
			case ANY :
				return collType.getElementType();
			case COLLECT :
				return getCollectionType(env, env.getOCLFactory(), stdlib
					.getT2());
			case CLOSURE :
				return getSetType(env, env.getOCLFactory(), stdlib.getT2());
			case SELECT_BY_KIND :
			case SELECT_BY_TYPE :
				TypedElement<C> arg = args.get(0);
				if (arg instanceof TypeExp<?>) {
					TypeExp<C> typeExp = (TypeExp<C>) arg;
					argType = typeExp.getReferredType();
				} else {
					argType = arg.getType();
				}
				return getCollectionType(env, env.getOCLFactory(), argType);
			case OCL_AS_SET :
				@SuppressWarnings("unchecked")
				C collType2 = (C)collType;
				return getSetType(env, env.getOCLFactory(), collType2);
		}

		String message = OCLMessages.bind(OCLMessages.CollectionType_ERROR_,
			collType.getName(), getOperationName(opcode));
		error(env, message, "collectionTypeResultTypeOf", problemObject); //$NON-NLS-1$
		return null;
	}

	private static <PK, C, O, P, EL, PM, ST, COA, SSA, CT, CLS, E> EList<Variable<C, PM>> createTupleParts(
			Environment<PK, C, O, P, EL, PM, ST, COA, SSA, CT, CLS, E> env,
			C firstType, C secondType) {

		EList<Variable<C, PM>> result = new BasicEList<Variable<C, PM>>();

		UMLReflection<PK, C, O, P, EL, PM, ?, COA, SSA, CT> uml = env
				.getUMLReflection();
		OCLFactory oclFactory = env.getOCLFactory();

		Variable<C, PM> var = oclFactory.createVariable();
		uml.setName(var, PRODUCT_FIRST);
		uml.setType(var, firstType);
		result.add(var);

		var = oclFactory.createVariable();
		uml.setName(var, PRODUCT_SECOND);
		uml.setType(var, secondType);
		result.add(var);

		return result;
	}

	/**
	 * Helper for the {@link #getResultTypeOf(Environment, Object, int, List)}
	 * dealing with the {@link TypeType}s.
	 */
	private static <PK, C, O, P, EL, PM, ST, COA, SSA, CT, CLS, E> C getTypeTypeResultTypeOf(
			Object problemObject,
			Environment<PK, C, O, P, EL, PM, ST, COA, SSA, CT, CLS, E> env,
			TypeType<C, O> typeType, int opcode,
			List<? extends TypedElement<C>> args) {

		switch (opcode) {
			case ALL_INSTANCES :
				return getSetType(env, env.getOCLFactory(), typeType
					.getReferredType());
		}
		@SuppressWarnings("unchecked")
		C sourceType = (C) typeType;
		return getAnyTypeResultTypeOf(problemObject, env, sourceType, opcode,
			args);
	}

	/**
	 * Helper for the {@link #getResultTypeOf(Environment, Object, int, List)}
	 * dealing with the {@link MessageType}s.
	 */
	private static <PK, C, O, P, EL, PM, ST, COA, SSA, CT, CLS, E> C getMessageTypeResultTypeOf(
			Object problemObject,
			Environment<PK, C, O, P, EL, PM, ST, COA, SSA, CT, CLS, E> env,
			MessageType<C, O, P> messageType, int opcode,
			List<? extends TypedElement<C>> args) {

		OCLStandardLibrary<C> stdlib = env.getOCLStandardLibrary();
		UMLReflection<PK, C, O, P, EL, PM, ?, COA, SSA, CT> uml = env
				.getUMLReflection();

		switch (opcode) {
			case HAS_RETURNED :
			case IS_SIGNAL_SENT :
			case IS_OPERATION_CALL :
				return stdlib.getBoolean();
			case RESULT :
				return (messageType.getReferredOperation() == null)
						? stdlib.getOclInvalid()
							: TypeUtil.resolveType(env, uml.getOCLType(messageType
								.getReferredOperation()));
		}
		@SuppressWarnings("unchecked")
		C sourceType = (C) messageType;
		return getAnyTypeResultTypeOf(problemObject, env, sourceType, opcode,
			args);
	}

	//
	// Factories of standard library operations
	//

	private static final int ANY_OPERATION_COUNT = 9;

	private static <PK, C, O, P, EL, PM, ST, COA, SSA, CT, CLS, E> List<O> createAnyOperations(
			Environment<PK, C, O, P, EL, PM, ST, COA, SSA, CT, CLS, E> env) {
		List<O> result = new java.util.ArrayList<O>(ANY_OPERATION_COUNT);

		OCLStandardLibrary<C> stdlib = env.getOCLStandardLibrary();
		UMLReflection<PK, C, O, P, EL, PM, ?, COA, SSA, CT> uml = env
				.getUMLReflection();

		result.add(createBinaryOperation(uml, stdlib.getBoolean(), EQUAL_NAME,
			stdlib.getOclAny(), "object")); //$NON-NLS-1$
		result.add(createBinaryOperation(uml, stdlib.getBoolean(),
			NOT_EQUAL_NAME, stdlib.getOclAny(), "object")); //$NON-NLS-1$
		result.add(createBinaryOperation(uml, stdlib.getT(), OCL_AS_TYPE_NAME,
			stdlib.getOclType(), "typespec")); //$NON-NLS-1$
		result.add(createBinaryOperation(uml, stdlib.getBoolean(),
			OCL_IS_KIND_OF_NAME, stdlib.getOclType(), "typespec"));//$NON-NLS-1$
		result.add(createBinaryOperation(uml, stdlib.getBoolean(),
			OCL_IS_TYPE_OF_NAME, stdlib.getOclType(), "typespec"));//$NON-NLS-1$
		result.add(createUnaryOperation(uml, stdlib.getBoolean(),
			OCL_IS_UNDEFINED_NAME));
		result.add(createUnaryOperation(uml, stdlib.getBoolean(),
			OCL_IS_INVALID_NAME));
		result.add(createUnaryOperation(uml, stdlib.getBoolean(),
			OCL_IS_NEW_NAME));
		result.add(createBinaryOperation(uml, stdlib.getBoolean(),
			OCL_IS_IN_STATE_NAME, stdlib.getState(), "statespec")); //$NON-NLS-1$
		result.add(createUnaryOperation(uml, stdlib.getString(), TO_STRING_NAME));
		OCLFactory oclFactory = env.getOCLFactory();
		result.add(createUnaryOperation(uml, getSetType(env, oclFactory, stdlib.getT()), OCL_AS_SET_NAME));

		return result;
	}

	/**
	 * Utility method creating the operations of the <code>OclAny</code> type of
	 * the OCL Standard library. This is useful for implementors of metamodel
	 * bindings ({@link Environment}s) to initialize their implementations of
	 * the <code>OclAny</code>.
	 *
	 * @param env
	 *            an OCL environment
	 * @return a list of new operations representing the standard
	 *         <code>OclAny</code> operations
	 */
	public static <PK, C, O, P, EL, PM, ST, COA, SSA, CT, CLS, E> List<O> createAnyTypeOperations(
			Environment<PK, C, O, P, EL, PM, ST, COA, SSA, CT, CLS, E> env) {
		List<O> result = new java.util.ArrayList<O>(ANY_OPERATION_COUNT + 4);

		result.addAll(createAnyOperations(env));

		OCLStandardLibrary<C> stdlib = env.getOCLStandardLibrary();
		UMLReflection<PK, C, O, P, EL, PM, ?, COA, SSA, CT> uml = env
				.getUMLReflection();

		result.add(createBinaryOperation(uml, stdlib.getBoolean(),
			LESS_THAN_NAME, stdlib.getT(), "object"));//$NON-NLS-1$
		result.add(createBinaryOperation(uml, stdlib.getBoolean(),
			GREATER_THAN_NAME, stdlib.getT(), "object"));//$NON-NLS-1$
		result.add(createBinaryOperation(uml, stdlib.getBoolean(),
			LESS_THAN_EQUAL_NAME, stdlib.getT(), "object"));//$NON-NLS-1$
		result.add(createBinaryOperation(uml, stdlib.getBoolean(),
			GREATER_THAN_EQUAL_NAME, stdlib.getT(), "object"));//$NON-NLS-1$

		return result;
	}

	/**
	 * Utility method creating the operations of the <code>OclType</code> type
	 * of the OCL Standard library. This is useful for implementors of metamodel
	 * bindings ({@link Environment}s) to initialize their implementations of
	 * the <code>OclType</code>.
	 *
	 * @param env
	 *            an OCL environment
	 * @return a list of new operations representing the standard
	 *         <code>OclType</code> operations
	 */
	public static <PK, C, O, P, EL, PM, ST, COA, SSA, CT, CLS, E> List<O> createTypeTypeOperations(
			Environment<PK, C, O, P, EL, PM, ST, COA, SSA, CT, CLS, E> env) {
		List<O> result = new java.util.ArrayList<O>(ANY_OPERATION_COUNT + 1);

		result.addAll(createAnyOperations(env));

		OCLStandardLibrary<C> stdlib = env.getOCLStandardLibrary();

		result.add(createUnaryOperation(env.getUMLReflection(),
			stdlib.getSet(), ALL_INSTANCES_NAME));

		return result;
	}

	/**
	 * Utility method creating the operations of the <code>OclMessage</code>
	 * type of the OCL Standard library. This is useful for implementors of
	 * metamodel bindings ({@link Environment}s) to initialize their
	 * implementations of the <code>OclMessage</code>.
	 *
	 * @param env
	 *            an OCL environment
	 * @return a list of new operations representing the standard
	 *         <code>OclMessage</code> operations
	 */
	public static <PK, C, O, P, EL, PM, ST, COA, SSA, CT, CLS, E> List<O> createMessageTypeOperations(
			Environment<PK, C, O, P, EL, PM, ST, COA, SSA, CT, CLS, E> env) {
		List<O> result = new java.util.ArrayList<O>(ANY_OPERATION_COUNT + 4);

		result.addAll(createAnyOperations(env));

		OCLStandardLibrary<C> stdlib = env.getOCLStandardLibrary();
		UMLReflection<PK, C, O, P, EL, PM, ?, COA, SSA, CT> uml = env
				.getUMLReflection();

		result.add(createUnaryOperation(uml, stdlib.getBoolean(),
			HAS_RETURNED_NAME));
		result.add(createUnaryOperation(uml, stdlib.getT(), RESULT_NAME));
		result.add(createUnaryOperation(uml, stdlib.getBoolean(),
			IS_SIGNAL_SENT_NAME));
		result.add(createUnaryOperation(uml, stdlib.getBoolean(),
			IS_OPERATION_CALL_NAME));

		return result;
	}

	/**
	 * Utility method creating the operations of the <code>String</code> type of
	 * the OCL Standard library. This is useful for implementors of metamodel
	 * bindings ({@link Environment}s) to initialize their implementations of
	 * the <code>String</code>.
	 *
	 * @param env
	 *            an OCL environment
	 * @return a list of new operations representing the standard
	 *         <code>String</code> operations
	 */
	public static <PK, C, O, P, EL, PM, ST, COA, SSA, CT, CLS, E> List<O> createStringOperations(
			Environment<PK, C, O, P, EL, PM, ST, COA, SSA, CT, CLS, E> env) {
		List<O> result = new java.util.ArrayList<O>(ANY_OPERATION_COUNT + 11);

		result.addAll(createAnyOperations(env));

		OCLStandardLibrary<C> stdlib = env.getOCLStandardLibrary();
		UMLReflection<PK, C, O, P, EL, PM, ?, COA, SSA, CT> uml = env
				.getUMLReflection();

		result.add(createBinaryOperation(uml, stdlib.getBoolean(),
			LESS_THAN_NAME, stdlib.getString(), "s"));//$NON-NLS-1$
		result.add(createBinaryOperation(uml, stdlib.getBoolean(),
			GREATER_THAN_NAME, stdlib.getString(), "s"));//$NON-NLS-1$
		result.add(createBinaryOperation(uml, stdlib.getBoolean(),
			LESS_THAN_EQUAL_NAME, stdlib.getString(), "s"));//$NON-NLS-1$
		result.add(createBinaryOperation(uml, stdlib.getBoolean(),
			GREATER_THAN_EQUAL_NAME, stdlib.getString(), "s"));//$NON-NLS-1$
		result.add(createUnaryOperation(uml, stdlib.getInteger(), SIZE_NAME));
		result.add(createBinaryOperation(uml, stdlib.getString(), CONCAT_NAME,
			stdlib.getString(), "s"));//$NON-NLS-1$
		result.add(createTernaryOperation(uml, stdlib.getString(),
			SUBSTRING_NAME, stdlib.getInteger(),
			"lower", stdlib.getInteger(), "upper"));//$NON-NLS-1$ //$NON-NLS-2$
		result.add(createUnaryOperation(uml, stdlib.getInteger(),
			TO_INTEGER_NAME));
		result.add(createUnaryOperation(uml, stdlib.getReal(), TO_REAL_NAME));
		result
		.add(createUnaryOperation(uml, stdlib.getString(), TO_LOWER_NAME));
		result
		.add(createUnaryOperation(uml, stdlib.getString(), TO_UPPER_NAME));
		result.add(createBinaryOperation(uml, stdlib.getString(), PLUS_NAME,
			stdlib.getString(), "s"));//$NON-NLS-1$
		result
		.add(createUnaryOperation(uml, stdlib.getString(), TO_LOWER_CASE_NAME));
		result
		.add(createUnaryOperation(uml, stdlib.getString(), TO_UPPER_CASE_NAME));
		result.add(createBinaryOperation(uml, stdlib.getString(),
			AT_NAME, stdlib.getInteger(), "index"));//$NON-NLS-1$
		result
		.add(createUnaryOperation(uml, stdlib.getSequence(), CHARACTERS_NAME));
		result.add(createBinaryOperation(uml, stdlib.getBoolean(),
			ENDS_WITH_NAME, stdlib.getString(), "s"));//$NON-NLS-1$
		result.add(createBinaryOperation(uml, stdlib.getBoolean(),
			EQUALS_IGNORE_CASE_NAME, stdlib.getString(), "s"));//$NON-NLS-1$
		result.add(createBinaryOperation(uml, stdlib.getInteger(),
			INDEX_OF_NAME, stdlib.getString(), "s"));//$NON-NLS-1$
		result.add(createBinaryOperation(uml, stdlib.getInteger(),
			LAST_INDEX_OF_NAME, stdlib.getString(), "s"));//$NON-NLS-1$
		result.add(createBinaryOperation(uml, stdlib.getBoolean(),
			MATCHES_NAME, stdlib.getString(), "s"));//$NON-NLS-1$
		result.add(createTernaryOperation(uml, stdlib.getString(),
			REPLACE_ALL_NAME, stdlib.getString(), "regex", stdlib.getString(), "replacement"));//$NON-NLS-1$ //$NON-NLS-2$
		result.add(createTernaryOperation(uml, stdlib.getString(),
			REPLACE_FIRST_NAME, stdlib.getString(), "regex", stdlib.getString(), "replacement"));//$NON-NLS-1$ //$NON-NLS-2$
		result.add(createBinaryOperation(uml, stdlib.getBoolean(),
			STARTS_WITH_NAME, stdlib.getString(), "s"));//$NON-NLS-1$
		result.add(createTernaryOperation(uml, stdlib.getString(),
			SUBSTITUTE_ALL_NAME, stdlib.getString(), "regex", stdlib.getString(), "replacement"));//$NON-NLS-1$ //$NON-NLS-2$
		result.add(createTernaryOperation(uml, stdlib.getString(),
			SUBSTITUTE_FIRST_NAME, stdlib.getString(), "regex", stdlib.getString(), "replacement"));//$NON-NLS-1$ //$NON-NLS-2$
		result
		.add(createUnaryOperation(uml, stdlib.getBoolean(), TO_BOOLEAN_NAME));
		result
		.add(createUnaryOperation(uml, stdlib.getSequence(), TOKENIZE_NAME));
		result.add(createBinaryOperation(uml, stdlib.getSequence(),
			TOKENIZE_NAME, stdlib.getString(), "delimiters"));//$NON-NLS-1$
		result.add(createTernaryOperation(uml, stdlib.getSequence(),
			TOKENIZE_NAME, stdlib.getString(), "delimiters", stdlib.getBoolean(), "returnDelimiters"));//$NON-NLS-1$ //$NON-NLS-2$
		result
		.add(createUnaryOperation(uml, stdlib.getString(), TRIM_NAME));

		return result;
	}

	/**
	 * Utility method creating the operations of the <code>Real</code> type of
	 * the OCL Standard library. This is useful for implementors of metamodel
	 * bindings ({@link Environment}s) to initialize their implementations of
	 * the <code>Real</code>.
	 *
	 * @param env
	 *            an OCL environment
	 * @return a list of new operations representing the standard
	 *         <code>Real</code> operations
	 */
	public static <PK, C, O, P, EL, PM, ST, COA, SSA, CT, CLS, E> List<O> createRealOperations(
			Environment<PK, C, O, P, EL, PM, ST, COA, SSA, CT, CLS, E> env) {
		List<O> result = new java.util.ArrayList<O>(ANY_OPERATION_COUNT + 14);

		result.addAll(createAnyOperations(env));

		OCLStandardLibrary<C> stdlib = env.getOCLStandardLibrary();
		UMLReflection<PK, C, O, P, EL, PM, ?, COA, SSA, CT> uml = env
				.getUMLReflection();

		result.add(createBinaryOperation(uml, stdlib.getBoolean(),
			LESS_THAN_NAME, stdlib.getReal(), "r"));//$NON-NLS-1$
		result.add(createBinaryOperation(uml, stdlib.getBoolean(),
			GREATER_THAN_NAME, stdlib.getReal(), "r"));//$NON-NLS-1$
		result.add(createBinaryOperation(uml, stdlib.getBoolean(),
			LESS_THAN_EQUAL_NAME, stdlib.getReal(), "r"));//$NON-NLS-1$
		result.add(createBinaryOperation(uml, stdlib.getBoolean(),
			GREATER_THAN_EQUAL_NAME, stdlib.getReal(), "r"));//$NON-NLS-1$
		result.add(createBinaryOperation(uml, stdlib.getReal(), PLUS_NAME,
			stdlib.getReal(), "r")); //$NON-NLS-1$
		result.add(createBinaryOperation(uml, stdlib.getReal(), MINUS_NAME,
			stdlib.getReal(), "r"));//$NON-NLS-1$
		result.add(createUnaryOperation(uml, stdlib.getReal(), MINUS_NAME));
		result.add(createBinaryOperation(uml, stdlib.getReal(), TIMES_NAME,
			stdlib.getReal(), "r"));//$NON-NLS-1$
		result.add(createBinaryOperation(uml, stdlib.getReal(), DIVIDE_NAME,
			stdlib.getReal(), "r")); //$NON-NLS-1$
		result.add(createBinaryOperation(uml, stdlib.getReal(), MIN_NAME,
			stdlib.getReal(), "r"));//$NON-NLS-1$
		result.add(createBinaryOperation(uml, stdlib.getReal(), MAX_NAME,
			stdlib.getReal(), "r")); //$NON-NLS-1$
		result.add(createUnaryOperation(uml, stdlib.getReal(), ABS_NAME));
		result.add(createUnaryOperation(uml, stdlib.getInteger(), FLOOR_NAME));
		result.add(createUnaryOperation(uml, stdlib.getInteger(), ROUND_NAME));

		return result;
	}

	/**
	 * Utility method creating the operations of the <code>Integer</code> type
	 * of the OCL Standard library. This is useful for implementors of metamodel
	 * bindings ({@link Environment}s) to initialize their implementations of
	 * the <code>Integer</code>.
	 *
	 * @param env
	 *            an OCL environment
	 * @return a list of new operations representing the standard
	 *         <code>Integer</code> operations
	 */
	public static <PK, C, O, P, EL, PM, ST, COA, SSA, CT, CLS, E> List<O> createIntegerOperations(
			Environment<PK, C, O, P, EL, PM, ST, COA, SSA, CT, CLS, E> env) {
		List<O> result = new java.util.ArrayList<O>(ANY_OPERATION_COUNT + 20);

		result.addAll(createRealOperations(env));

		OCLStandardLibrary<C> stdlib = env.getOCLStandardLibrary();
		UMLReflection<PK, C, O, P, EL, PM, ?, COA, SSA, CT> uml = env
				.getUMLReflection();

		result.add(createBinaryOperation(uml, stdlib.getBoolean(),
			LESS_THAN_NAME, stdlib.getInteger(), "i"));//$NON-NLS-1$
		result.add(createBinaryOperation(uml, stdlib.getBoolean(),
			GREATER_THAN_NAME, stdlib.getInteger(), "i"));//$NON-NLS-1$
		result.add(createBinaryOperation(uml, stdlib.getBoolean(),
			LESS_THAN_EQUAL_NAME, stdlib.getInteger(), "i"));//$NON-NLS-1$
		result.add(createBinaryOperation(uml, stdlib.getBoolean(),
			GREATER_THAN_EQUAL_NAME, stdlib.getInteger(), "i"));//$NON-NLS-1$
		result.add(createBinaryOperation(uml, stdlib.getInteger(), DIV_NAME,
			stdlib.getInteger(), "i")); //$NON-NLS-1$
		result.add(createBinaryOperation(uml, stdlib.getInteger(), MOD_NAME,
			stdlib.getInteger(), "i")); //$NON-NLS-1$

		return result;
	}

	/**
	 * Utility method creating the operations of the
	 * <code>UnlimitedNatural</code> type of the OCL Standard library. This is
	 * useful for implementors of metamodel bindings ({@link Environment}s) to
	 * initialize their implementations of the <code>Integer</code>.
	 *
	 * @param env
	 *            an OCL environment
	 * @return a list of new operations representing the standard
	 *         <code>UnlimitedNatural</code> operations
	 */
	public static <PK, C, O, P, EL, PM, ST, COA, SSA, CT, CLS, E> List<O> createUnlimitedNaturalOperations(
			Environment<PK, C, O, P, EL, PM, ST, COA, SSA, CT, CLS, E> env) {
		List<O> result = new java.util.ArrayList<O>(ANY_OPERATION_COUNT + 20);

		result.addAll(createRealOperations(env));

		OCLStandardLibrary<C> stdlib = env.getOCLStandardLibrary();
		UMLReflection<PK, C, O, P, EL, PM, ?, COA, SSA, CT> uml = env
				.getUMLReflection();

		result.add(createBinaryOperation(uml, stdlib.getBoolean(),
			LESS_THAN_NAME, stdlib.getUnlimitedNatural(), "n"));//$NON-NLS-1$
		result.add(createBinaryOperation(uml, stdlib.getBoolean(),
			GREATER_THAN_NAME, stdlib.getUnlimitedNatural(), "n"));//$NON-NLS-1$
		result.add(createBinaryOperation(uml, stdlib.getBoolean(),
			LESS_THAN_EQUAL_NAME, stdlib.getUnlimitedNatural(), "n"));//$NON-NLS-1$
		result.add(createBinaryOperation(uml, stdlib.getBoolean(),
			GREATER_THAN_EQUAL_NAME, stdlib.getUnlimitedNatural(), "n"));//$NON-NLS-1$
		result.add(createUnaryOperation(uml, stdlib.getInteger(),
			TO_INTEGER_NAME));
		result.add(createBinaryOperation(uml, stdlib.getInteger(), DIV_NAME,
			stdlib.getUnlimitedNatural(), "n")); //$NON-NLS-1$
		result.add(createBinaryOperation(uml, stdlib.getInteger(), MOD_NAME,
			stdlib.getUnlimitedNatural(), "n")); //$NON-NLS-1$

		return result;
	}

	/**
	 * Utility method creating the operations of the <code>Boolean</code> type
	 * of the OCL Standard library. This is useful for implementors of metamodel
	 * bindings ({@link Environment}s) to initialize their implementations of
	 * the <code>Boolean</code>.
	 *
	 * @param env
	 *            an OCL environment
	 * @return a list of new operations representing the standard
	 *         <code>Boolean</code> operations
	 */
	public static <PK, C, O, P, EL, PM, ST, COA, SSA, CT, CLS, E> List<O> createBooleanOperations(
			Environment<PK, C, O, P, EL, PM, ST, COA, SSA, CT, CLS, E> env) {
		List<O> result = new java.util.ArrayList<O>(ANY_OPERATION_COUNT + 5);

		result.addAll(createAnyOperations(env));

		OCLStandardLibrary<C> stdlib = env.getOCLStandardLibrary();
		UMLReflection<PK, C, O, P, EL, PM, ?, COA, SSA, CT> uml = env
				.getUMLReflection();

		result.add(createUnaryOperation(uml, stdlib.getBoolean(), NOT_NAME));
		result.add(createBinaryOperation(uml, stdlib.getBoolean(), AND_NAME,
			stdlib.getBoolean(), "b")); //$NON-NLS-1$
		result.add(createBinaryOperation(uml, stdlib.getBoolean(), OR_NAME,
			stdlib.getBoolean(), "b")); //$NON-NLS-1$
		result.add(createBinaryOperation(uml, stdlib.getBoolean(),
			IMPLIES_NAME, stdlib.getBoolean(), "b"));//$NON-NLS-1$
		result.add(createBinaryOperation(uml, stdlib.getBoolean(), XOR_NAME,
			stdlib.getBoolean(), "b"));//$NON-NLS-1$

		return result;
	}

	private static final int COLLECTION_OPERATION_COUNT = 10;

	/**
	 * Utility method creating the operations of the <code>Collection(T)</code>
	 * type of the OCL Standard library. This is useful for implementors of
	 * metamodel bindings ({@link Environment}s) to initialize their
	 * implementations of the <code>Collection(T)</code>.
	 *
	 * @param env
	 *            an OCL environment
	 * @return a list of new operations representing the standard
	 *         <code>Collection(T)</code> operations
	 */
	public static <PK, C, O, P, EL, PM, ST, COA, SSA, CT, CLS, E> List<O> createCollectionOperations(
			Environment<PK, C, O, P, EL, PM, ST, COA, SSA, CT, CLS, E> env) {
		List<O> result = new java.util.ArrayList<O>(COLLECTION_OPERATION_COUNT);

		OCLStandardLibrary<C> stdlib = env.getOCLStandardLibrary();
		UMLReflection<PK, C, O, P, EL, PM, ?, COA, SSA, CT> uml = env
				.getUMLReflection();

		result.add(createBinaryOperation(uml, stdlib.getBoolean(),
			EQUAL_NAME, stdlib.getCollection(), "c"));//$NON-NLS-1$
		result.add(createBinaryOperation(uml, stdlib.getBoolean(),
			NOT_EQUAL_NAME, stdlib.getCollection(), "c"));//$NON-NLS-1$

		result.add(createBinaryOperation(uml, stdlib.getInteger(), COUNT_NAME,
			stdlib.getT(), "object")); //$NON-NLS-1$
		result.add(createBinaryOperation(uml, stdlib.getBoolean(),
			EXCLUDES_NAME, stdlib.getT(), "object")); //$NON-NLS-1$
		result.add(createBinaryOperation(uml, stdlib.getBoolean(),
			EXCLUDES_ALL_NAME, stdlib.getCollection(), "c2"));//$NON-NLS-1$
		result.add(createBinaryOperation(uml, stdlib.getBoolean(),
			INCLUDES_NAME, stdlib.getT(), "object")); //$NON-NLS-1$
		result.add(createBinaryOperation(uml, stdlib.getBoolean(),
			INCLUDES_ALL_NAME, stdlib.getCollection(), "c2"));//$NON-NLS-1$
		result
		.add(createUnaryOperation(uml, stdlib.getBoolean(), IS_EMPTY_NAME));
		result.add(createUnaryOperation(uml, stdlib.getBoolean(),
			NOT_EMPTY_NAME));

		OCLFactory oclFactory = env.getOCLFactory();
		C resultType = getSetType(env, oclFactory, getTupleType(env,
			oclFactory, createTupleParts(env, stdlib.getT(), stdlib.getT2())));
		result.add(createBinaryOperation(uml, resultType, PRODUCT_NAME,
			getCollectionType(env, oclFactory, stdlib.getT2()), "c2"));//$NON-NLS-1$
		result.add(createUnaryOperation(uml, stdlib.getReal(), SUM_NAME));
		result.add(createUnaryOperation(uml, stdlib.getInteger(), SIZE_NAME));
		result.add(createUnaryOperation(uml, stdlib.getReal(), MAX_NAME));
		result.add(createUnaryOperation(uml, stdlib.getReal(), MIN_NAME));
		result.add(createBinaryOperation(uml, getCollectionType(env, oclFactory, stdlib.getT2()), SELECT_BY_KIND_NAME,
			stdlib.getOclType(), "typespec")); //$NON-NLS-1$
		result.add(createBinaryOperation(uml, getCollectionType(env, oclFactory, stdlib.getT2()), SELECT_BY_TYPE_NAME,
			stdlib.getOclType(), "typespec")); //$NON-NLS-1$
		result.add(createUnaryOperation(uml, getSetType(env, oclFactory, stdlib.getT()), OCL_AS_SET_NAME));
		result.add(createUnaryOperation(uml, stdlib.getBoolean(),
			OCL_IS_UNDEFINED_NAME));
		result.add(createUnaryOperation(uml, stdlib.getBoolean(),
			OCL_IS_INVALID_NAME));

		return result;
	}

	private static final int SET_OPERATION_COUNT = COLLECTION_OPERATION_COUNT + 15;

	/**
	 * Utility method creating the operations of the <code>Set(T)</code> type of
	 * the OCL Standard library. This is useful for implementors of metamodel
	 * bindings ({@link Environment}s) to initialize their implementations of
	 * the <code>Set(T)</code>.
	 *
	 * @param env
	 *            an OCL environment
	 * @return a list of new operations representing the standard
	 *         <code>Set(T)</code> operations
	 */
	public static <PK, C, O, P, EL, PM, ST, COA, SSA, CT, CLS, E> List<O> createSetOperations(
			Environment<PK, C, O, P, EL, PM, ST, COA, SSA, CT, CLS, E> env) {
		List<O> result = new java.util.ArrayList<O>(SET_OPERATION_COUNT);

		result.addAll(createCollectionOperations(env));

		OCLStandardLibrary<C> stdlib = env.getOCLStandardLibrary();
		UMLReflection<PK, C, O, P, EL, PM, ?, COA, SSA, CT> uml = env
				.getUMLReflection();

		result.add(createBinaryOperation(uml, stdlib.getBoolean(), EQUAL_NAME,
			stdlib.getSet(), "set"));//$NON-NLS-1$
		result.add(createBinaryOperation(uml, stdlib.getBoolean(),
			NOT_EQUAL_NAME, stdlib.getSet(), "set")); //$NON-NLS-1$
		result.add(createBinaryOperation(uml, stdlib.getBag(), UNION_NAME,
			stdlib.getBag(), "bag")); //$NON-NLS-1$
		result.add(createBinaryOperation(uml, stdlib.getSet(), UNION_NAME,
			stdlib.getSet(), "set"));//$NON-NLS-1$
		result.add(createBinaryOperation(uml, stdlib.getSet(), MINUS_NAME,
			stdlib.getSet(), "set"));//$NON-NLS-1$
		result.add(createBinaryOperation(uml, stdlib.getSet(),
			INTERSECTION_NAME, stdlib.getBag(), "bag"));//$NON-NLS-1$
		result.add(createBinaryOperation(uml, stdlib.getSet(),
			INTERSECTION_NAME, stdlib.getSet(), "set"));//$NON-NLS-1$
		result.add(createBinaryOperation(uml, stdlib.getSet(), INCLUDING_NAME,
			stdlib.getT(), "object"));//$NON-NLS-1$
		result.add(createBinaryOperation(uml, stdlib.getSet(), EXCLUDING_NAME,
			stdlib.getT(), "object"));//$NON-NLS-1$
		result.add(createBinaryOperation(uml, stdlib.getSet(),
			SYMMETRIC_DIFFERENCE_NAME, stdlib.getSet(), "s"));//$NON-NLS-1$
		result.add(createUnaryOperation(uml, getSetType(env, env
			.getOCLFactory(), stdlib.getT2()), FLATTEN_NAME));
		result.add(createUnaryOperation(uml, stdlib.getBag(), AS_BAG_NAME));
		result.add(createUnaryOperation(uml, stdlib.getSet(), AS_SET_NAME));
		result.add(createUnaryOperation(uml, stdlib.getSequence(),
			AS_SEQUENCE_NAME));
		result.add(createUnaryOperation(uml, stdlib.getOrderedSet(),
			AS_ORDERED_SET_NAME));

		return result;
	}

	/**
	 * Utility method creating the operations of the <code>OrderedSet(T)</code>
	 * type of the OCL Standard library. This is useful for implementors of
	 * metamodel bindings ({@link Environment}s) to initialize their
	 * implementations of the <code>OrderedSet(T)</code>.
	 *
	 * @param env
	 *            an OCL environment
	 * @return a list of new operations representing the standard
	 *         <code>OrderedSet(T)</code> operations
	 */
	public static <PK, C, O, P, EL, PM, ST, COA, SSA, CT, CLS, E> List<O> createOrderedSetOperations(
			Environment<PK, C, O, P, EL, PM, ST, COA, SSA, CT, CLS, E> env) {
		List<O> result = new java.util.ArrayList<O>(SET_OPERATION_COUNT + 10);

		result.addAll(createSetOperations(env));

		OCLStandardLibrary<C> stdlib = env.getOCLStandardLibrary();
		UMLReflection<PK, C, O, P, EL, PM, ?, COA, SSA, CT> uml = env
				.getUMLReflection();

		result.add(createBinaryOperation(uml, stdlib.getBoolean(), EQUAL_NAME,
			stdlib.getOrderedSet(), "s"));//$NON-NLS-1$
		result.add(createBinaryOperation(uml, stdlib.getBoolean(),
			NOT_EQUAL_NAME, stdlib.getOrderedSet(), "s"));//$NON-NLS-1$
		result.add(createBinaryOperation(uml, stdlib.getOrderedSet(),
			APPEND_NAME, stdlib.getT(), "object"));//$NON-NLS-1$
		result.add(createBinaryOperation(uml, stdlib.getT(), AT_NAME, stdlib
			.getInteger(), "index"));//$NON-NLS-1$
		result.add(createBinaryOperation(uml, stdlib.getInteger(),
			INDEX_OF_NAME, stdlib.getT(), "object"));//$NON-NLS-1$
		result.add(createTernaryOperation(uml, stdlib.getOrderedSet(),
			INSERT_AT_NAME, stdlib.getInteger(), "index", //$NON-NLS-1$
			stdlib.getT(), "object")); //$NON-NLS-1$
		result.add(createBinaryOperation(uml, stdlib.getOrderedSet(),
			PREPEND_NAME, stdlib.getT(), "object"));//$NON-NLS-1$
		result.add(createTernaryOperation(uml, stdlib.getOrderedSet(),
			SUB_ORDERED_SET_NAME, stdlib.getInteger(), "lower", //$NON-NLS-1$
			stdlib.getInteger(), "upper"));//$NON-NLS-1$
		result.add(createUnaryOperation(uml, stdlib.getT(), FIRST_NAME));
		result.add(createUnaryOperation(uml, stdlib.getT(), LAST_NAME));

		return result;
	}

	/**
	 * Utility method creating the operations of the <code>Bag(T)</code> type of
	 * the OCL Standard library. This is useful for implementors of metamodel
	 * bindings ({@link Environment}s) to initialize their implementations of
	 * the <code>Bag(T)</code>.
	 *
	 * @param env
	 *            an OCL environment
	 * @return a list of new operations representing the standard
	 *         <code>Bag(T)</code> operations
	 */
	public static <PK, C, O, P, EL, PM, ST, COA, SSA, CT, CLS, E> List<O> createBagOperations(
			Environment<PK, C, O, P, EL, PM, ST, COA, SSA, CT, CLS, E> env) {
		List<O> result = new java.util.ArrayList<O>(
				COLLECTION_OPERATION_COUNT + 13);

		result.addAll(createCollectionOperations(env));

		OCLStandardLibrary<C> stdlib = env.getOCLStandardLibrary();
		UMLReflection<PK, C, O, P, EL, PM, ?, COA, SSA, CT> uml = env
				.getUMLReflection();

		result.add(createBinaryOperation(uml, stdlib.getBoolean(), EQUAL_NAME,
			stdlib.getBag(), "bag"));//$NON-NLS-1$
		result.add(createBinaryOperation(uml, stdlib.getBoolean(),
			NOT_EQUAL_NAME, stdlib.getBag(), "bag")); //$NON-NLS-1$
		result.add(createBinaryOperation(uml, stdlib.getBag(), UNION_NAME,
			stdlib.getBag(), "bag")); //$NON-NLS-1$
		result.add(createBinaryOperation(uml, stdlib.getBag(), UNION_NAME,
			stdlib.getSet(), "set"));//$NON-NLS-1$
		result.add(createBinaryOperation(uml, stdlib.getBag(),
			INTERSECTION_NAME, stdlib.getBag(), "bag"));//$NON-NLS-1$
		result.add(createBinaryOperation(uml, stdlib.getBag(),
			INTERSECTION_NAME, stdlib.getSet(), "set"));//$NON-NLS-1$
		result.add(createBinaryOperation(uml, stdlib.getBag(), INCLUDING_NAME,
			stdlib.getT(), "object"));//$NON-NLS-1$
		result.add(createBinaryOperation(uml, stdlib.getBag(), EXCLUDING_NAME,
			stdlib.getT(), "object"));//$NON-NLS-1$
		result.add(createUnaryOperation(uml, getBagType(env, env
			.getOCLFactory(), stdlib.getT2()), FLATTEN_NAME));
		result.add(createUnaryOperation(uml, stdlib.getBag(), AS_BAG_NAME));
		result.add(createUnaryOperation(uml, stdlib.getSet(), AS_SET_NAME));
		result.add(createUnaryOperation(uml, stdlib.getSequence(),
			AS_SEQUENCE_NAME));
		result.add(createUnaryOperation(uml, stdlib.getOrderedSet(),
			AS_ORDERED_SET_NAME));

		return result;
	}

	/**
	 * Utility method creating the operations of the <code>Sequence(T)</code>
	 * type of the OCL Standard library. This is useful for implementors of
	 * metamodel bindings ({@link Environment}s) to initialize their
	 * implementations of the <code>Sequence(T)</code>.
	 *
	 * @param env
	 *            an OCL environment
	 * @return a list of new operations representing the standard
	 *         <code>Sequence(T)</code> operations
	 */
	public static <PK, C, O, P, EL, PM, ST, COA, SSA, CT, CLS, E> List<O> createSequenceOperations(
			Environment<PK, C, O, P, EL, PM, ST, COA, SSA, CT, CLS, E> env) {
		List<O> result = new java.util.ArrayList<O>(
				COLLECTION_OPERATION_COUNT + 18);

		result.addAll(createCollectionOperations(env));

		OCLStandardLibrary<C> stdlib = env.getOCLStandardLibrary();
		UMLReflection<PK, C, O, P, EL, PM, ?, COA, SSA, CT> uml = env
				.getUMLReflection();

		result.add(createBinaryOperation(uml, stdlib.getBoolean(), EQUAL_NAME,
			stdlib.getSequence(), "s"));//$NON-NLS-1$
		result.add(createBinaryOperation(uml, stdlib.getBoolean(),
			NOT_EQUAL_NAME, stdlib.getSequence(), "s")); //$NON-NLS-1$
		result.add(createBinaryOperation(uml, stdlib.getSequence(), UNION_NAME,
			stdlib.getSequence(), "s")); //$NON-NLS-1$
		result.add(createBinaryOperation(uml, stdlib.getSequence(), APPEND_NAME,
			stdlib.getT(), "object"));//$NON-NLS-1$
		result.add(createBinaryOperation(uml, stdlib.getSequence(),
			PREPEND_NAME, stdlib.getT(), "object"));//$NON-NLS-1$
		result.add(createTernaryOperation(uml, stdlib.getSequence(),
			INSERT_AT_NAME, stdlib.getInteger(), "index", //$NON-NLS-1$
			stdlib.getT(), "object"));//$NON-NLS-1$
		result.add(createTernaryOperation(uml, stdlib.getSequence(),
			SUB_SEQUENCE_NAME, stdlib.getInteger(), "lower", //$NON-NLS-1$
			stdlib.getInteger(), "upper"));//$NON-NLS-1$
		result.add(createBinaryOperation(uml, stdlib.getT(), AT_NAME, stdlib
			.getInteger(), "index")); //$NON-NLS-1$
		result.add(createBinaryOperation(uml, stdlib.getInteger(),
			INDEX_OF_NAME, stdlib.getT(), "object"));//$NON-NLS-1$
		result.add(createBinaryOperation(uml, stdlib.getSequence(),
			INCLUDING_NAME, stdlib.getT(), "object"));//$NON-NLS-1$
		result.add(createBinaryOperation(uml, stdlib.getSequence(),
			EXCLUDING_NAME, stdlib.getT(), "object"));//$NON-NLS-1$
		result.add(createUnaryOperation(uml, stdlib.getT(), FIRST_NAME));
		result.add(createUnaryOperation(uml, stdlib.getT(), LAST_NAME));
		result.add(createUnaryOperation(uml, getSequenceType(env, env
			.getOCLFactory(), stdlib.getT2()), FLATTEN_NAME));
		result.add(createUnaryOperation(uml, stdlib.getBag(), AS_BAG_NAME));
		result.add(createUnaryOperation(uml, stdlib.getSet(), AS_SET_NAME));
		result.add(createUnaryOperation(uml, stdlib.getBoolean(),
			AS_SEQUENCE_NAME));
		result.add(createUnaryOperation(uml, stdlib.getOrderedSet(),
			AS_ORDERED_SET_NAME));

		return result;
	}

	private static final int COLLECTION_ITERATOR_COUNT = 7;

	/**
	 * Utility method creating the pre-defined iterators of the
	 * <code>Collection(T)</code> type of the OCL Standard library. This is
	 * useful for implementors of metamodel bindings ({@link Environment}s) to
	 * initialize their implementations of the <code>Collection(T)</code>.
	 *
	 * @param env
	 *            an OCL environment
	 * @return a list of new operations representing the pre-defined iterators
	 *         of the <code>Collection(T)</code> type
	 */
	public static <PK, C, O, P, EL, PM, ST, COA, SSA, CT, CLS, E> List<O> createCollectionIterators(
			Environment<PK, C, O, P, EL, PM, ST, COA, SSA, CT, CLS, E> env) {
		List<O> result = new java.util.ArrayList<O>(COLLECTION_ITERATOR_COUNT);

		OCLStandardLibrary<C> stdlib = env.getOCLStandardLibrary();
		UMLReflection<PK, C, O, P, EL, PM, ?, COA, SSA, CT> uml = env
				.getUMLReflection();

		result.add(createBinaryOperation(uml, stdlib.getBoolean(), EXISTS_NAME,
			stdlib.getOclExpression(), "expr")); //$NON-NLS-1$

		result.add(createBinaryOperation(uml, stdlib.getBoolean(),
			FOR_ALL_NAME, stdlib.getOclExpression(), "expr")); //$NON-NLS-1$

		result.add(createBinaryOperation(uml, stdlib.getBoolean(),
			IS_UNIQUE_NAME, stdlib.getOclExpression(), "expr")); //$NON-NLS-1$

		result.add(createBinaryOperation(uml, stdlib.getBoolean(), ONE_NAME,
			stdlib.getOclExpression(), "expr")); //$NON-NLS-1$

		result.add(createBinaryOperation(uml, stdlib.getT(), ANY_NAME, stdlib
			.getOclExpression(), "expr")); //$NON-NLS-1$

		result.add(createBinaryOperation(uml, getCollectionType(env, env
			.getOCLFactory(), stdlib.getT2()), COLLECT_NAME, stdlib
			.getOclExpression(), "expr")); //$NON-NLS-1$

		result.add(createBinaryOperation(uml, getSetType(env, env
			.getOCLFactory(), stdlib.getT2()), CLOSURE_NAME, stdlib
			.getOclExpression(), "expr")); //$NON-NLS-1$

		return result;
	}

	private static final int SET_ITERATOR_COUNT = COLLECTION_ITERATOR_COUNT + 4;

	/**
	 * Utility method creating the pre-defined iterators of the
	 * <code>Set(T)</code> type of the OCL Standard library. This is useful for
	 * implementors of metamodel bindings ({@link Environment}s) to initialize
	 * their implementations of the <code>Set(T)</code>.
	 *
	 * @param env
	 *            an OCL environment
	 * @return a list of new operations representing the pre-defined iterators
	 *         of the <code>Set(T)</code> type
	 */
	public static <PK, C, O, P, EL, PM, ST, COA, SSA, CT, CLS, E> List<O> createSetIterators(
			Environment<PK, C, O, P, EL, PM, ST, COA, SSA, CT, CLS, E> env) {
		List<O> result = new java.util.ArrayList<O>(SET_ITERATOR_COUNT);

		OCLStandardLibrary<C> stdlib = env.getOCLStandardLibrary();

		result.addAll(createCollectionIterators(env));
		UMLReflection<PK, C, O, P, EL, PM, ?, COA, SSA, CT> uml = env
				.getUMLReflection();

		result.add(createBinaryOperation(uml, stdlib.getSet(), SELECT_NAME,
			stdlib.getOclExpression(), "expr")); //$NON-NLS-1$

		result.add(createBinaryOperation(uml, stdlib.getSet(), REJECT_NAME,
			stdlib.getOclExpression(), "expr")); //$NON-NLS-1$

		result.add(createBinaryOperation(uml, stdlib.getOrderedSet(),
			SORTED_BY_NAME, stdlib.getOclExpression(), "expr")); //$NON-NLS-1$

		result.add(createBinaryOperation(uml, getBagType(env, env
			.getOCLFactory(), stdlib.getT2()), COLLECT_NESTED_NAME, stdlib
			.getOclExpression(), "expr")); //$NON-NLS-1$

		return result;
	}

	/**
	 * Utility method creating the pre-defined iterators of the
	 * <code>OrderedSet(T)</code> type of the OCL Standard library. This is
	 * useful for implementors of metamodel bindings ({@link Environment}s) to
	 * initialize their implementations of the <code>OrderedSet(T)</code>.
	 *
	 * @param env
	 *            an OCL environment
	 * @return a list of new operations representing the pre-defined iterators
	 *         of the <code>OrderedSet(T)</code> type
	 */
	public static <PK, C, O, P, EL, PM, ST, COA, SSA, CT, CLS, E> List<O> createOrderedSetIterators(
			Environment<PK, C, O, P, EL, PM, ST, COA, SSA, CT, CLS, E> env) {
		List<O> result = new java.util.ArrayList<O>(SET_ITERATOR_COUNT);

		result.addAll(createSetIterators(env));

		return result;
	}

	/**
	 * Utility method creating the pre-defined iterators of the
	 * <code>Bag(T)</code> type of the OCL Standard library. This is useful for
	 * implementors of metamodel bindings ({@link Environment}s) to initialize
	 * their implementations of the <code>Bag(T)</code>.
	 *
	 * @param env
	 *            an OCL environment
	 * @return a list of new operations representing the pre-defined iterators
	 *         of the <code>Bag(T)</code> type
	 */
	public static <PK, C, O, P, EL, PM, ST, COA, SSA, CT, CLS, E> List<O> createBagIterators(
			Environment<PK, C, O, P, EL, PM, ST, COA, SSA, CT, CLS, E> env) {
		List<O> result = new java.util.ArrayList<O>(
				COLLECTION_ITERATOR_COUNT + 4);

		OCLStandardLibrary<C> stdlib = env.getOCLStandardLibrary();

		result.addAll(createCollectionIterators(env));
		UMLReflection<PK, C, O, P, EL, PM, ?, COA, SSA, CT> uml = env
				.getUMLReflection();

		result.add(createBinaryOperation(uml, stdlib.getBag(), SELECT_NAME,
			stdlib.getOclExpression(), "expr")); //$NON-NLS-1$

		result.add(createBinaryOperation(uml, stdlib.getBag(), REJECT_NAME,
			stdlib.getOclExpression(), "expr")); //$NON-NLS-1$

		result.add(createBinaryOperation(uml, stdlib.getSequence(),
			SORTED_BY_NAME, stdlib.getOclExpression(), "expr")); //$NON-NLS-1$

		result.add(createBinaryOperation(uml, getBagType(env, env
			.getOCLFactory(), stdlib.getT2()), COLLECT_NESTED_NAME, stdlib
			.getOclExpression(), "expr")); //$NON-NLS-1$

		return result;
	}

	/**
	 * Utility method creating the pre-defined iterators of the
	 * <code>Sequence(T)</code> type of the OCL Standard library. This is useful
	 * for implementors of metamodel bindings ({@link Environment}s) to
	 * initialize their implementations of the <code>Sequence(T)</code>.
	 *
	 * @param env
	 *            an OCL environment
	 * @return a list of new operations representing the pre-defined iterators
	 *         of the <code>Sequence(T)</code> type
	 */
	public static <PK, C, O, P, EL, PM, ST, COA, SSA, CT, CLS, E> List<O> createSequenceIterators(
			Environment<PK, C, O, P, EL, PM, ST, COA, SSA, CT, CLS, E> env) {
		List<O> result = new java.util.ArrayList<O>(
				COLLECTION_ITERATOR_COUNT + 4);

		OCLStandardLibrary<C> stdlib = env.getOCLStandardLibrary();
		UMLReflection<PK, C, O, P, EL, PM, ?, COA, SSA, CT> uml = env
				.getUMLReflection();

		result.addAll(createCollectionIterators(env));

		result.add(createBinaryOperation(uml, stdlib.getSequence(),
			SELECT_NAME, stdlib.getOclExpression(), "expr")); //$NON-NLS-1$

		result.add(createBinaryOperation(uml, stdlib.getSequence(),
			REJECT_NAME, stdlib.getOclExpression(), "expr")); //$NON-NLS-1$

		result.add(createBinaryOperation(uml, stdlib.getSequence(),
			SORTED_BY_NAME, stdlib.getOclExpression(), "expr")); //$NON-NLS-1$

		result.add(createBinaryOperation(uml, getSequenceType(env, env
			.getOCLFactory(), stdlib.getT2()), COLLECT_NESTED_NAME, stdlib
			.getOclExpression(), "expr")); //$NON-NLS-1$

		return result;
	}

	private static <PK, C, O, P, EL, PM, ST, COA, SSA, CT, CLS, E> O createUnaryOperation(
			UMLReflection<PK, C, O, P, EL, PM, ST, COA, SSA, CT> uml,
			C resultType, String name) {

		List<String> paramNames = Collections.emptyList();
		List<C> paramTypes = Collections.emptyList();

		return uml.createOperation(name, resultType, paramNames, paramTypes);
	}

	private static <PK, C, O, P, EL, PM, ST, COA, SSA, CT, CLS, E> O createBinaryOperation(
			UMLReflection<PK, C, O, P, EL, PM, ST, COA, SSA, CT> uml,
			C resultType, String name, C paramType, String paramName) {

		List<String> paramNames = Collections.singletonList(paramName);
		List<C> paramTypes = Collections.singletonList(paramType);

		return uml.createOperation(name, resultType, paramNames, paramTypes);
	}

	private static <PK, C, O, P, EL, PM, ST, COA, SSA, CT, CLS, E> O createTernaryOperation(
			UMLReflection<PK, C, O, P, EL, PM, ST, COA, SSA, CT> uml,
			C resultType, String name, C param1Type, String param1Name,
			C param2Type, String param2Name) {

		List<String> paramNames = new java.util.ArrayList<String>(2);
		List<C> paramTypes = new java.util.ArrayList<C>(2);

		paramNames.add(param1Name);
		paramTypes.add(param1Type);
		paramNames.add(param2Name);
		paramTypes.add(param2Type);

		return uml.createOperation(name, resultType, paramNames, paramTypes);
	}

	@SuppressWarnings("unchecked")
	private static <PK, C, O, P, EL, PM, ST, COA, SSA, CT, CLS, E> C getBagType(
			Environment<PK, C, O, P, EL, PM, ST, COA, SSA, CT, CLS, E> env,
			OCLFactory factory, C elementType) {
		return TypeUtil
				.resolveType(env, (C) factory.createBagType(elementType));
	}

	@SuppressWarnings("unchecked")
	private static <PK, C, O, P, EL, PM, ST, COA, SSA, CT, CLS, E> C getSetType(
			Environment<PK, C, O, P, EL, PM, ST, COA, SSA, CT, CLS, E> env,
			OCLFactory factory, C elementType) {
		return TypeUtil
				.resolveType(env, (C) factory.createSetType(elementType));
	}

	@SuppressWarnings("unchecked")
	private static <PK, C, O, P, EL, PM, ST, COA, SSA, CT, CLS, E> C getOrderedSetType(
			Environment<PK, C, O, P, EL, PM, ST, COA, SSA, CT, CLS, E> env,
			OCLFactory factory, C elementType) {
		return TypeUtil.resolveType(env, (C) factory
			.createOrderedSetType(elementType));
	}

	@SuppressWarnings("unchecked")
	private static <PK, C, O, P, EL, PM, ST, COA, SSA, CT, CLS, E> C getSequenceType(
			Environment<PK, C, O, P, EL, PM, ST, COA, SSA, CT, CLS, E> env,
			OCLFactory factory, C elementType) {
		return TypeUtil.resolveType(env, (C) factory
			.createSequenceType(elementType));
	}

	@SuppressWarnings("unchecked")
	private static <PK, C, O, P, EL, PM, ST, COA, SSA, CT, CLS, E> C getCollectionType(
			Environment<PK, C, O, P, EL, PM, ST, COA, SSA, CT, CLS, E> env,
			OCLFactory factory, C elementType) {
		return TypeUtil.resolveType(env, (C) factory
			.createCollectionType(elementType));
	}

	@SuppressWarnings("unchecked")
	private static <PK, C, O, P, EL, PM, ST, COA, SSA, CT, CLS, E> C getTupleType(
			Environment<PK, C, O, P, EL, PM, ST, COA, SSA, CT, CLS, E> env,
			OCLFactory factory, List<? extends TypedElement<C>> parts) {
		return TypeUtil.resolveType(env, (C) factory.createTupleType(parts));
	}

	/**
	 * Convenience method invoking
	 * <code>getProblemHandler().utilityProblem</code> with an error severity.
	 *
	 * @param problemMessage
	 *            message describing the problem
	 * @param problemContext
	 *            optional message describing the reporting context
	 * @param problemObject
	 *            optional object associated with the problem
	 */
	private static void error(
			Environment<?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?> env,
			String problemMessage, String problemContext, Object problemObject) {
		OCLUtil.getAdapter(env, BasicEnvironment.class).utilityError(
			problemMessage, problemContext, problemObject);
	}

	/**
	 * Convenience method invoking
	 * <code>getProblemHandler().utilityProblem</code> with a warning severity.
	 *
	 * @param problemMessage
	 *            message describing the problem
	 * @param problemContext
	 *            optional message describing the reporting context
	 * @param problemObject
	 *            optional object associated with the problem
	 */
	private static void warning(
			Environment<?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?> env,
			String problemMessage, String problemContext, Object problemObject) {
		BasicEnvironment benv = OCLUtil.getAdapter(env, BasicEnvironment.class);
		if (benv != null) {
			CSTNode cstNode = benv.getASTMapping(problemObject);
			int startOffset = (cstNode != null)
					? cstNode.getStartOffset()
						: -1;
					int endOffset = (cstNode != null)
							? cstNode.getEndOffset()
								: -1;
							benv.getProblemHandler().utilityProblem(
								ProblemHandler.Severity.WARNING, problemMessage,
								problemContext, startOffset, endOffset);
		}
	}

	/**
	 * Queries all of the supertypes of a pre-defined type. Note that this is
	 * only useful for operations on the OCL standard library types themselves,
	 * especially in the case of generic types such as the collection types,
	 * <tt>OclMessage</tt>, and the like. Thus, it helps to find inherited
	 * operations and attributes, etc., but not actual type conformance. For
	 * example, <tt>{@literal Set<Fruit>}</tt> is not returned as a supertype of
	 * <tt>{@literal Set<Apple>}</tt>. Rather, only
	 * <tt>{@literal Collection<T>}</tt> is. This method also does not handle
	 * the void and invalid types.
	 *
	 * @param env
	 *            the contextual environment
	 * @param type
	 *            an OCL re-defined type
	 * @return an unmodifiable collection of the supertypes, which may be empty
	 *         in some cases
	 *
	 * @since 1.3
	 */
	@SuppressWarnings("unchecked")
	public static <C> Collection<C> getAllSupertypes(
			Environment<?, C, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?> env,
			PredefinedType<?> type) {

		Collection<C> result;
		OCLStandardLibrary<C> stdlib = env.getOCLStandardLibrary();

		if (type instanceof CollectionType<?, ?>) {
			CollectionType<?, ?> collType = (CollectionType<?, ?>) type;

			switch (collType.getKind()) {
				case ORDERED_SET_LITERAL :
					result = Arrays.asList(stdlib.getSet(), stdlib
						.getCollection());
					break;
				case COLLECTION_LITERAL :
					result = Collections.emptySet();
					break;
				default :
					result = Collections.singleton(stdlib.getCollection());
					break;
			}
		} else if (type == stdlib.getInteger()) {
			result = Arrays.asList(stdlib.getReal(), stdlib.getOclAny());
		} else if (type instanceof AnyType<?>) {
			result = Collections.emptySet();
		} else {
			result = Collections.singleton(stdlib.getOclAny());
		}

		return result;
	}
}
