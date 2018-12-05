/*******************************************************************************
 * Copyright (c) 2013, 2018 CEA LIST and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink(CEA LIST) - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.utilities;

import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCollectionExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCollectionPart;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIfExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIterationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIterator;
import org.eclipse.ocl.examples.codegen.cgmodel.CGLetExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGMapExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGMapPart;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNavigationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNumber;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGShadowExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGShadowPart;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTupleExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTuplePart;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.pivot.CollectionLiteralExp;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.LoopExp;
import org.eclipse.ocl.pivot.MapLiteralExp;
import org.eclipse.ocl.pivot.NavigationCallExp;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.OppositePropertyCallExp;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.PropertyCallExp;
import org.eclipse.ocl.pivot.ShadowExp;
import org.eclipse.ocl.pivot.ShadowPart;
import org.eclipse.ocl.pivot.TupleLiteralExp;
import org.eclipse.ocl.pivot.TupleLiteralPart;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.IntegerValue;
import org.eclipse.ocl.pivot.values.RealValue;

/**
 * EquivalenceUtil provides the bodies for many of the isEquivalentToInternal operations.
 *
 * These return:
 * <br>
 * true if two values are definitely the same.
 * <br>
 * false if two values are definitely different.
 * <br>
 * null if the values could be the same or different.
 */
public class EquivalenceUtil
{
	public static @Nullable Boolean isEquivalent(@NonNull CGCallExp thisValue, @NonNull CGCallExp thatValue) {
		if (thisValue == thatValue) {
			return Boolean.TRUE;
		}
		CGValuedElement thisSource = thisValue.getSource();
		CGValuedElement thatSource = thatValue.getSource();
		if ((thisSource != null) || (thatSource != null)) {
			if ((thisSource == null) || (thatSource == null)) {
				return null;			// Inconsistent sources should never happen
			}
			Boolean equivalence = thisSource.isEquivalentTo(thatSource);
			if (equivalence != Boolean.TRUE) {
				return null;			// Different sources do not guarantee different results
			}
		}
		return Boolean.TRUE;
	}

	public static @Nullable Boolean isEquivalent(@NonNull CGCollectionExp thisValue, @NonNull CGCollectionExp thatValue) {
		if (thisValue == thatValue) {
			return Boolean.TRUE;
		}
		Element thisAST = thisValue.getAst();
		Element thatAST = thatValue.getAst();
		if (!(thisAST instanceof CollectionLiteralExp) || !(thatAST instanceof CollectionLiteralExp)) {
			return null;					// Null ASTs should never happen
		}
		if (((CollectionLiteralExp)thisAST).getKind() != ((CollectionLiteralExp)thatAST).getKind()) {
			return Boolean.FALSE;			// Distinct kinds are not necessarily not equal
		}
		List<CGCollectionPart> theseParts = thisValue.getParts();
		List<CGCollectionPart> thoseParts = thatValue.getParts();
		int iSize = theseParts.size();
		if (iSize != thoseParts.size()) {
			return null; //Boolean.FALSE;				-- FIXME support range/items comparison
		}
		for (int i = 0; i < iSize; i++) {
			CGCollectionPart thisPart = theseParts.get(i);
			CGCollectionPart thatPart = thoseParts.get(i);
			if ((thisPart == null) || (thatPart == null)) {
				return null;				// Null parts should never happen
			}
			Boolean equivalence = thisPart.isEquivalentTo(thatPart);
			if (equivalence != Boolean.TRUE) {
				return null; // equivalence;			-- FIXME support differently ordered comparison
			}
		}
		return Boolean.TRUE;
	}

	public static @Nullable Boolean isEquivalent(@NonNull CGCollectionPart thisValue, @NonNull CGCollectionPart thatValue) {
		if (thisValue == thatValue) {
			return Boolean.TRUE;
		}
		CGValuedElement thisLast = thisValue.getLast();
		CGValuedElement thatLast = thatValue.getLast();
		if ((thisLast != null) || (thatLast != null)) {
			if ((thisLast == null) || (thatLast == null)) {
				return null; 				// FIXME could support range/items comparison
			}
			Boolean equivalence = thisLast.isEquivalentTo(thatLast);
			if (equivalence != Boolean.TRUE) {
				return equivalence;
			}
		}
		CGValuedElement thisFirst = thisValue.getFirst();
		CGValuedElement thatFirst = thatValue.getFirst();
		if ((thisFirst == null) || (thatFirst == null)) {
			return null; 					// FIXME could support range/items comparison
		}
		return thisFirst.isEquivalentTo(thatFirst);
	}

	public static @Nullable Boolean isEquivalent(@NonNull CGIfExp thisValue, @NonNull CGIfExp thatValue) {
		if (thisValue == thatValue) {
			return Boolean.TRUE;
		}
		if (thisValue.isConstant() && thatValue.isConstant()) {				// FIXME Move to caller
			CGValuedElement thisConstant = thisValue.getNamedValue();
			CGValuedElement thatConstant = thatValue.getNamedValue();
			return thisConstant.isEquivalentTo(thatConstant);
		}
		CGValuedElement thisCondition = thisValue.getCondition();
		CGValuedElement thatCondition = thatValue.getCondition();
		if ((thisCondition != null) || (thatCondition != null)) {
			if ((thisCondition == null) || (thatCondition == null)) {
				return null;			// Inconsistent conditions should never happen
			}
			Boolean equivalence = thisCondition.isEquivalentTo(thatCondition);
			if (equivalence != Boolean.TRUE) {
				return null;			// Different conditions do not guarantee different results
			}
		}
		CGValuedElement thisThen = thisValue.getThenExpression();
		CGValuedElement thatThen = thatValue.getThenExpression();
		if ((thisThen != null) || (thatThen != null)) {
			if ((thisThen == null) || (thatThen == null)) {
				return null;			// Inconsistent expressions should never happen
			}
			Boolean equivalence = thisThen.isEquivalentTo(thatThen);
			if (equivalence != Boolean.TRUE) {
				return null;			// Different expressions do not guarantee different results
			}
		}
		CGValuedElement thisElse = thisValue.getElseExpression();
		CGValuedElement thatElse = thatValue.getElseExpression();
		if ((thisElse != null) || (thatElse != null)) {
			if ((thisElse == null) || (thatElse == null)) {
				return null;			// Inconsistent expressions should never happen
			}
			Boolean equivalence = thisElse.isEquivalentTo(thatElse);
			if (equivalence != Boolean.TRUE) {
				return null;			// Different expressions do not guarantee different results
			}
		}
		return Boolean.TRUE;
	}

	public static @Nullable Boolean isEquivalent(@NonNull CGIterationCallExp thisValue, @NonNull CGIterationCallExp thatValue) {
		if (thisValue == thatValue) {
			return Boolean.TRUE;
		}
		Element thisAST = thisValue.getAst();
		Element thatAST = thatValue.getAst();
		if (!(thisAST instanceof LoopExp) || !(thatAST instanceof LoopExp)) {
			return null;			// Null ASTs should never happen
		}
		if (((LoopExp)thisAST).getReferredIteration() != ((LoopExp)thatAST).getReferredIteration()) {
			return null;			// Different iterators do not guarantee different results
		}
		CGValuedElement thisSource = thisValue.getSource();
		CGValuedElement thatSource = thatValue.getSource();
		if ((thisSource != null) || (thatSource != null)) {
			if ((thisSource == null) || (thatSource == null)) {
				return null;			// Inconsistent sources should never happen
			}
			Boolean equivalence = thisSource.isEquivalentTo(thatSource);
			if (equivalence != Boolean.TRUE) {
				return null;			// Different sources do not guarantee different results
			}
		}
		CGValuedElement thisBody = thisValue.getBody();
		CGValuedElement thatBody = thatValue.getBody();
		if ((thisBody != null) || (thatBody != null)) {
			if ((thisBody == null) || (thatBody == null)) {
				return null;			// Null bodies should never happen
			}
			Boolean equivalence = thisBody.isEquivalentTo(thatBody);
			if (equivalence != Boolean.TRUE) {
				return null;			// Different bodies do not guarantee different results
			}
		}
		List<CGIterator> theseIterators = thisValue.getIterators();
		List<CGIterator> thoseIterators = thatValue.getIterators();
		int iSize = theseIterators.size();
		if (iSize != thoseIterators.size()) {
			return null;			// Different iterator lists do not guarantee different results
		}
		for (int i = 0; i < iSize; i++) {
			CGIterator thisIterator = theseIterators.get(i);
			CGIterator thatIterator = thoseIterators.get(i);
			if ((thisIterator != null) || (thatIterator != null)) {
				if ((thisIterator == null) || (thatIterator == null)) {
					return null;			// Null iterators should never happen
				}
				Boolean equivalence = thisIterator.isEquivalentTo(thatIterator);
				if (equivalence != Boolean.TRUE) {
					return null;			// Different iterators do not guarantee different results
				}
			}
		}
		List<CGIterator> theseCoIterators = thisValue.getCoIterators();
		List<CGIterator> thoseCoIterators = thatValue.getCoIterators();
		int iCoSize = theseCoIterators.size();
		if (iCoSize != thoseCoIterators.size()) {
			return null;			// Different iterator lists do not guarantee different results
		}
		for (int i = 0; i < iCoSize; i++) {
			CGIterator thisCoIterator = theseCoIterators.get(i);
			CGIterator thatCoIterator = thoseCoIterators.get(i);
			if ((thisCoIterator != null) || (thatCoIterator != null)) {
				if ((thisCoIterator == null) || (thatCoIterator == null)) {
					return null;			// Null co-iterators should never happen
				}
				Boolean equivalence = thisCoIterator.isEquivalentTo(thatCoIterator);
				if (equivalence != Boolean.TRUE) {
					return null;			// Different iterators do not guarantee different results
				}
			}
		}
		return Boolean.TRUE;
	}

	public static @Nullable Boolean isEquivalent(@NonNull CGLetExp thisValue, @NonNull CGLetExp thatValue) {
		if (thisValue == thatValue) {
			return Boolean.TRUE;
		}
		if (thisValue.isConstant() && thatValue.isConstant()) {				// FIXME Move to caller
			CGValuedElement thisConstant = thisValue.getNamedValue();
			CGValuedElement thatConstant = thatValue.getNamedValue();
			return thisConstant.isEquivalentTo(thatConstant);
		}
		CGValuedElement thisInit = thisValue.getInit();
		CGValuedElement thatInit = thatValue.getInit();
		if ((thisInit != null) || (thatInit != null)) {
			if ((thisInit == null) || (thatInit == null)) {
				return null;			// Inconsistent inits should never happen
			}
			Boolean equivalence = thisInit.isEquivalentTo(thatInit);
			if (equivalence != Boolean.TRUE) {
				return null;			// Different inits do not guarantee different results
			}
		}
		CGValuedElement thisIn = thisValue.getIn();
		CGValuedElement thatIn = thatValue.getIn();
		if ((thisIn != null) || (thatIn != null)) {
			if ((thisIn == null) || (thatIn == null)) {
				return null;			// Inconsistent expressions should never happen
			}
			Boolean equivalence = thisIn.isEquivalentTo(thatIn);
			if (equivalence != Boolean.TRUE) {
				return null;			// Different expressions do not guarantee different results
			}
		}
		return Boolean.TRUE;
	}

	public static @Nullable Boolean isEquivalent(@NonNull CGMapExp thisValue, @NonNull CGMapExp thatValue) {
		if (thisValue == thatValue) {
			return Boolean.TRUE;
		}
		Element thisAST = thisValue.getAst();
		Element thatAST = thatValue.getAst();
		if (!(thisAST instanceof MapLiteralExp) || !(thatAST instanceof MapLiteralExp)) {
			return null;					// Null ASTs should never happen
		}
		List<CGMapPart> theseParts = thisValue.getParts();
		List<CGMapPart> thoseParts = thatValue.getParts();
		int iSize = theseParts.size();
		if (iSize != thoseParts.size()) {
			return null; //Boolean.FALSE;				-- FIXME support range/items comparison
		}
		for (int i = 0; i < iSize; i++) {
			CGMapPart thisPart = theseParts.get(i);
			CGMapPart thatPart = thoseParts.get(i);
			if ((thisPart == null) || (thatPart == null)) {
				return null;				// Null parts should never happen
			}
			Boolean equivalence = thisPart.isEquivalentTo(thatPart);
			if (equivalence != Boolean.TRUE) {
				return null; // equivalence;			-- FIXME support differently ordered comparison
			}
		}
		return Boolean.TRUE;
	}

	public static @Nullable Boolean isEquivalent(@NonNull CGMapPart thisPart, @NonNull CGMapPart thatPart) {
		if (thisPart == thatPart) {
			return Boolean.TRUE;
		}
		CGValuedElement thisKey = thisPart.getKey();
		CGValuedElement thatKey = thatPart.getKey();
		if ((thisKey != null) || (thatKey != null)) {
			if ((thisKey == null) || (thatKey == null)) {
				return null;
			}
			Boolean equivalence = thisKey.isEquivalentTo(thatKey);
			if (equivalence != Boolean.TRUE) {
				return equivalence;
			}
		}
		CGValuedElement thisValue = thisPart.getValue();
		CGValuedElement thatValue = thatPart.getValue();
		if ((thisValue == null) || (thatValue == null)) {
			return null; 					// FIXME could support range/items comparison
		}
		return thisValue.isEquivalentTo(thatValue);
	}

	public static @Nullable Boolean isEquivalent(@NonNull CGNumber thisValue, @NonNull CGNumber thatValue) {
		if (thisValue == thatValue) {
			return Boolean.TRUE;
		}
		Number thisNumber = thisValue.getNumericValue();
		Number thatNumber = thatValue.getNumericValue();
		if ((thisNumber == null) || (thatNumber == null)) {
			return null;			// Null numbers should never happen
		}
		else if (thisNumber.getClass() == thatNumber.getClass()) {
			return thisNumber.equals(thatNumber);
		}
		else if ((thisNumber instanceof RealValue) && (thatNumber instanceof RealValue)) {
			return thisNumber.equals(thatNumber);
		}
		else if (ValueUtil.isRealNumber(thisNumber) || ValueUtil.isRealNumber(thatNumber)) {
			RealValue thisReal = ValueUtil.realValueOf(thisNumber);
			RealValue thatReal = ValueUtil.realValueOf(thatNumber);
			return thisReal.equals(thatReal);
		}
		else if (ValueUtil.isIntegerNumber(thisNumber) && ValueUtil.isIntegerNumber(thatNumber)) {
			IntegerValue thisInteger = ValueUtil.integerValueOf(thisNumber);
			IntegerValue thatInterger = ValueUtil.integerValueOf(thatNumber);
			return thisInteger.equals(thatInterger);
		}
		else {					// This should never happen
			double thisDouble = thisNumber.doubleValue();
			double thatDouble = thatNumber.doubleValue();
			return thisDouble == thatDouble;
		}
	}

	public static @Nullable Boolean isEquivalent(@NonNull CGOperationCallExp thisValue, @NonNull CGOperationCallExp thatValue) {
		if (thisValue == thatValue) {
			return Boolean.TRUE;
		}
		Element thisAST = thisValue.getAst();
		Element thatAST = thatValue.getAst();
		if (!(thisAST instanceof OperationCallExp) || !(thatAST instanceof OperationCallExp)) {
			return null;			// Null ASTs should never happen
		}
		if (((OperationCallExp)thisAST).getReferredOperation() != ((OperationCallExp)thatAST).getReferredOperation()) {
			return null;			// Different operations do not guarantee different results
		}
		// FIXME non-conformant return types can be guaranteed to be different
		CGValuedElement thisSource = thisValue.getSource();
		CGValuedElement thatSource = thatValue.getSource();
		if ((thisSource != null) || (thatSource != null)) {
			if ((thisSource == null) || (thatSource == null)) {
				return null;			// Inconsistently null sources should never happen
			}
			Boolean equivalence = thisSource.isEquivalentTo(thatSource);
			if (equivalence != Boolean.TRUE) {
				return null;			// Different sources do not guarantee different results
			}
		}
		List<CGValuedElement> theseArguments = thisValue.getArguments();
		List<CGValuedElement> thoseArguments = thatValue.getArguments();
		int iSize = theseArguments.size();
		if (iSize != thoseArguments.size()) {
			return null;				// Different argument lists do not guarantee different results
		}
		for (int i = 0; i < iSize; i++) {
			CGValuedElement thisArgument = theseArguments.get(i);
			CGValuedElement thatArgument = thoseArguments.get(i);
			if ((thisArgument != null) || (thatArgument != null)) {
				if ((thisArgument == null) || (thatArgument == null)) {
					return null;			// Null arguments should never happen
				}
				Boolean equivalence = thisArgument.isEquivalentTo(thatArgument);
				if (equivalence != Boolean.TRUE) {
					return null;			// Different arguments do not guarantee different results
				}
			}
		}
		return Boolean.TRUE;
	}

	public static @Nullable Boolean isEquivalent(@NonNull CGNavigationCallExp thisValue, @NonNull CGNavigationCallExp thatValue) {
		if (thisValue == thatValue) {
			return Boolean.TRUE;
		}
		Element thisAST = thisValue.getAst();
		Element thatAST = thatValue.getAst();
		if (!(thisAST instanceof NavigationCallExp) || !(thatAST instanceof NavigationCallExp)) {
			return null;			// Null ASTs should never happen
		}
		if (thisAST.eClass() != thatAST.eClass()) {
			return null;			// Different directions do not guarantee different results
		}
		Property thisProperty = thisAST instanceof PropertyCallExp ? ((PropertyCallExp)thisAST).getReferredProperty() : ((OppositePropertyCallExp)thisAST).getReferredProperty();
		Property thatProperty = thatAST instanceof PropertyCallExp ? ((PropertyCallExp)thatAST).getReferredProperty() : ((OppositePropertyCallExp)thatAST).getReferredProperty();
		if (thisProperty != thatProperty) {
			return null;			// Different properties do not guarantee different results
		}
		// FIXME non-conformant return types can be guaranteed to be different
		CGValuedElement thisSource = thisValue.getSource();
		CGValuedElement thatSource = thatValue.getSource();
		if ((thisSource != null) || (thatSource != null)) {
			if ((thisSource == null) || (thatSource == null)) {
				return null;			// Inconsistently null sources should never happen
			}
			Boolean equivalence = thisSource.isEquivalentTo(thatSource);
			if (equivalence != Boolean.TRUE) {
				return null;			// Different sources do not guarantee different results
			}
		}
		return Boolean.TRUE;
	}

	public static @Nullable Boolean isEquivalent(@NonNull CGShadowExp thisValue, @NonNull CGShadowExp thatValue) {
		if (thisValue == thatValue) {
			return Boolean.TRUE;
		}
		Element thisAST = thisValue.getAst();
		Element thatAST = thatValue.getAst();
		if (!(thisAST instanceof ShadowExp) || !(thatAST instanceof ShadowExp)) {
			return null;					// Null ASTs should never happen
		}
		if (((ShadowExp)thisAST).getTypeId() != ((ShadowExp)thatAST).getTypeId()) {
			return Boolean.FALSE;			// Distinct typeids are necessarily not equal
		}
		List<CGShadowPart> theseParts = thisValue.getParts();
		List<CGShadowPart> thoseParts = thatValue.getParts();
		int iSize = theseParts.size();
		if (iSize != thoseParts.size()) {
			return Boolean.FALSE;			// Distinct part lists are necessarily not equal
		}
		for (int i = 0; i < iSize; i++) {
			CGShadowPart thisPart = theseParts.get(i);
			CGShadowPart thatPart = thoseParts.get(i);
			if ((thisPart == null) || (thatPart == null)) {
				return null;				// Null parts should never happen
			}
			Boolean equivalence = thisPart.isEquivalentTo(thatPart);
			if (equivalence != Boolean.TRUE) {
				return equivalence;			// Distinct parts are necessarily not equal
			}
		}
		return Boolean.TRUE;
	}

	public static @Nullable Boolean isEquivalent(@NonNull CGShadowPart thisValue, @NonNull CGShadowPart thatValue) {
		if (thisValue == thatValue) {
			return Boolean.TRUE;
		}
		Element thisAST = thisValue.getAst();
		Element thatAST = thatValue.getAst();
		if (!(thisAST instanceof ShadowPart) || !(thatAST instanceof ShadowPart)) {
			return null;					// Null ASTs should never happen
		}
		if (((ShadowPart)thisAST).getTypeId() != ((ShadowPart)thatAST).getTypeId()) {
			return Boolean.FALSE;			// Distinct typeids are necessarily not equal
		}
		CGValuedElement thisPartInit = thisValue.getInit();
		CGValuedElement thatPartInit = thatValue.getInit();
		if ((thisPartInit == null) || (thatPartInit == null)) {
			return null;			// Null inits should never happen
		}
		return thisPartInit.isEquivalentTo(thatPartInit);
	}

	public static @Nullable Boolean isEquivalent(@NonNull CGTupleExp thisValue, @NonNull CGTupleExp thatValue) {
		if (thisValue == thatValue) {
			return Boolean.TRUE;
		}
		Element thisAST = thisValue.getAst();
		Element thatAST = thatValue.getAst();
		if (!(thisAST instanceof TupleLiteralExp) || !(thatAST instanceof TupleLiteralExp)) {
			return null;					// Null ASTs should never happen
		}
		if (((TupleLiteralExp)thisAST).getTypeId() != ((TupleLiteralExp)thatAST).getTypeId()) {
			return Boolean.FALSE;			// Distinct typeids are necessarily not equal
		}
		List<CGTuplePart> theseParts = thisValue.getParts();
		List<CGTuplePart> thoseParts = thatValue.getParts();
		int iSize = theseParts.size();
		if (iSize != thoseParts.size()) {
			return Boolean.FALSE;			// Distinct part lists are necessarily not equal
		}
		for (int i = 0; i < iSize; i++) {
			CGTuplePart thisPart = theseParts.get(i);
			CGTuplePart thatPart = thoseParts.get(i);
			if ((thisPart == null) || (thatPart == null)) {
				return null;				// Null parts should never happen
			}
			Boolean equivalence = thisPart.isEquivalentTo(thatPart);
			if (equivalence != Boolean.TRUE) {
				return equivalence;			// Distinct parts are necessarily not equal
			}
		}
		return Boolean.TRUE;
	}

	public static @Nullable Boolean isEquivalent(@NonNull CGTuplePart thisValue, @NonNull CGTuplePart thatValue) {
		if (thisValue == thatValue) {
			return Boolean.TRUE;
		}
		Element thisAST = thisValue.getAst();
		Element thatAST = thatValue.getAst();
		if (!(thisAST instanceof TupleLiteralPart) || !(thatAST instanceof TupleLiteralPart)) {
			return null;					// Null ASTs should never happen
		}
		if (((TupleLiteralPart)thisAST).getTypeId() != ((TupleLiteralPart)thatAST).getTypeId()) {
			return Boolean.FALSE;			// Distinct typeids are necessarily not equal
		}
		CGValuedElement thisPartInit = thisValue.getInit();
		CGValuedElement thatPartInit = thatValue.getInit();
		if ((thisPartInit == null) || (thatPartInit == null)) {
			return null;			// Null inits should never happen
		}
		return thisPartInit.isEquivalentTo(thatPartInit);
	}
}
