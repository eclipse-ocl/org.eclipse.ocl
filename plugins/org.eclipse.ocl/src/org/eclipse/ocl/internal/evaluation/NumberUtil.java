/*******************************************************************************
 * Copyright (c) 2007, 2018 IBM Corporation, Zeligsoft Inc., and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   Andreas Werner - Initial API and implementation
 *   Achim Demelt - Bug 245897
 *******************************************************************************/

package org.eclipse.ocl.internal.evaluation;

import java.math.BigDecimal;
import java.math.BigInteger;


/**
 * Utility for converting numeric values to double or integer precision as needed.
 * 
 * @since 1.2
 */
public class NumberUtil {

    /**
     * <p>
     * Tests whether a given number can be safely coerced to <tt>Double</tt> or
     * <tt>Integer</tt> without changing the value of the number.  Safe means 
     * that coercing a number to <tt>Double</tt> or <tt>Integer</tt> and then
     * coercing it back to the original type will result in the same value (no
     * loss of precision).  This is trivial for types, which have a smaller
     * domain then <tt>Integer</tt> or <tt>Double</tt>, but for
     * example a <tt>Long</tt> number may not be safely coerced to
     * <tt>Integer</tt>.
     * </p><p>
     * If the coercion is safe, the number will be returned as either
     * <tt>Double</tt> or <tt>Integer</tt>, as appropriate to the original
     * precision.  Otherwise the original number is returned. 
     * </p>
     * 
     * @param number a number to coerce to <tt>Integer</tt> or <tt>Double</tt>
     * @return the coerced number, or the original number, if coercion was not safe
     */
    public static Number coerceNumber(Number number) { 
        Number result;
        
        if ((number instanceof Integer) || (number instanceof Double)) {
            result = number;
        } else if ((number instanceof Byte) || (number instanceof Short)) {
            result = number.intValue();
        } else if (number instanceof Long) {
            if (canConform((Long) number)) {
                result = number.intValue();
            } else {
                result = number;
            }
        } else if (number instanceof BigInteger) {
        	if (isInteger((BigInteger) number)) {
                result = number.intValue();
            } else if (isLong((BigInteger) number)) {
                result = number.longValue();
            } else {
                // do nothing, NFE will occur
                result = number;
            }
        } else if (number instanceof Float) {
            result = number.doubleValue();
        } else if (number instanceof BigDecimal) {
            if (isDouble((BigDecimal) number)) {
                result = number.doubleValue();
            } else {
                // do nothing, NFE will occur
                result = number;
            }
        } else {
            // some odd-ball number we've never heard of.  NFE will occur
            result = number;
        }
        
        return result;
    }

    private static boolean canConform(Long number) {
        long l = number;
        long i = number.intValue();
        
        return l == i;
    }

    private static boolean isInteger(BigInteger number) {
        int i = number.intValue();
        BigInteger b = new BigInteger(String.valueOf(i));
        
        return number.equals(b);
    }

    private static boolean isLong(BigInteger number) {
        long i = number.longValue();
        BigInteger b = new BigInteger(String.valueOf(i));
        
        return number.equals(b);
    }

    private static boolean isDouble(BigDecimal number) {
    	double doubleValue = number.doubleValue();
		return (doubleValue != Double.NEGATIVE_INFINITY)
			&& (doubleValue != Double.POSITIVE_INFINITY);
    }
    
    /**
     * <p>
     * Coerces the given number to a common or greater precision than <tt>referenceNumber</tt>,
     * <tt>BigDecimal</tt> is greater than <tt>Double</tt> or
     * <tt>BigInteger</tt> which is greater than <tt>Long</tt> precision.
     * </p>
     * 
     * @param number a number to coerce to a common precision
     * @param referenceNumber another number to share the common precision
     * @return the coerced number, or the original number, in case of overflow
     */
	public static Number commonPrecisionNumber(Number number, Number referenceNumber) {
		if ((number instanceof BigDecimal) || (referenceNumber instanceof BigDecimal)) {
			if (number instanceof BigDecimal) {
				return number;
			} else if (number instanceof BigInteger) {
				return new BigDecimal((BigInteger)number);
			} else if (number instanceof Integer || number instanceof Long || number instanceof Short || number instanceof Byte) {
				return new BigDecimal(number.longValue());
			} else {
				return BigDecimal.valueOf(number.doubleValue());
			}
		} else if ((number instanceof BigInteger) && (referenceNumber instanceof Double)) {
			return new BigDecimal((BigInteger)number);
		} else if ((number instanceof Double) && (referenceNumber instanceof BigInteger)) {
			return BigDecimal.valueOf(number.doubleValue());
		} else if ((number instanceof Double) || (referenceNumber instanceof Double)
			|| (number instanceof Float) || (referenceNumber instanceof Float)) {
			if (number instanceof Double) {
				return number;
			} else {
				return Double.valueOf(number.doubleValue());
			}
		} else if ((number instanceof BigInteger) || (referenceNumber instanceof BigInteger)) {
			if (number instanceof BigInteger) {
				return number;
			} else {
				return BigInteger.valueOf(number.longValue());
			}
		} else {
			if (number instanceof Long) {
				return number;
			} else {
				return Long.valueOf(number.longValue());
			}
		}
	}
    
    /**
     * <p>
     * Coerces the given number to <tt>Double</tt> or <tt>Long</tt> precision,
     * if possible.  Note that this is only impossible for <tt>BigDecimal</tt>
     * or <tt>BigInteger</tt> values, respectively, that are out of range of
     * their primitive counterparts.
     * </p>
     * 
     * @param number a number to coerce to <tt>Long</tt> or <tt>Double</tt>
     * @return the coerced number, or the original number, in case of overflow
     */
    public static Number higherPrecisionNumber(Number number) { 
        Number result;
        
        if ((number instanceof Integer) || (number instanceof Byte) ||
                (number instanceof Short)) {
            result = number.longValue();
        } else if (number instanceof Long) {
            result = number;
        } else if (number instanceof BigInteger) {
            if (isLong((BigInteger) number)) {
                result = number.longValue();
            } else {
                // do nothing, NFE will occur
                result = number;
            }
        } else if (number instanceof Float) {
            result = number.doubleValue();
        } else if (number instanceof BigDecimal) {
            if (isDouble((BigDecimal) number)) {
                result = number.doubleValue();
            } else {
                // do nothing, NFE will occur
                result = number;
            }
        } else {
            // some odd-ball number we've never heard of.  NFE will occur
            result = number;
        }
        
        return result;
    }
}
