/*******************************************************************************
 * Copyright (c) 2025 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.ids;

import org.eclipse.jdt.annotation.NonNull;

/**
 * The ExtraParameters enumeration identifies the numbers of template paramters, kiterators, accumulators that
 * may be in use without needing to identify their names.
 *
 * @since 7.0
 */
public enum ExtraParameters
{
	T1_I0_A0(1,0,0),
	T2_I0_A0(2,0,0),
	T3_I0_A0(3,0,0),
	T4_I0_A0(4,0,0),
	T5_I0_A0(5,0,0),
	T6_I0_A0(6,0,0),
	T7_I0_A0(7,0,0),
	T8_I0_A0(8,0,0),
	T9_I0_A0(9,0,0),
	T10_I0_A0(10,0,0),
	T11_I0_A0(11,0,0),
	T12_I0_A0(12,0,0),
	T0_I1_A0(0,1,0),
	T0_I2_A0(0,2,0),
	T0_I3_A0(0,3,0),
	T0_I4_A0(0,4,0),
	T0_I5_A0(0,5,0),
	T0_I6_A0(0,6,0),
	T1_I1_A0(1,1,0),
	T1_I2_A0(1,2,0),
	T1_I3_A0(1,3,0),
	T0_I1_A1(0,1,1),
	T1_I1_A1(1,1,1),
	T2_I1_A1(2,1,1),
	T0_I2_A1(0,2,1),
	T1_I2_A1(1,2,1),
	T2_I2_A1(2,2,1),
	T0_I3_A1(0,3,1),
	T1_I3_A1(1,3,1),
	T2_I3_A1(2,3,1),
	NONE(0, 0, 0);

	int extraParameters;

	private ExtraParameters(int templateParameters, int iterators, int accumulators) {
		this.extraParameters = templateParameters | iterators << 8 | accumulators << 16;
	}

	public int getAccumulators() {
		return (extraParameters >> 16);
	}

	public int getIteratrors() {
		return (extraParameters >> 8) & 0xFF;
	}

	public int getTemplateParameters() {
		return extraParameters & 0xFF;
	}

	public int getValue() {
		return extraParameters;
	}

	public static @NonNull ExtraParameters getTemplateParameters(int templateParameters) {
		switch (templateParameters) {
			case 0: return NONE;
			case 1: return T1_I0_A0;
			case 2: return T2_I0_A0;
			case 3: return T3_I0_A0;
			case 4: return T4_I0_A0;
			case 5: return T5_I0_A0;
			case 6: return T6_I0_A0;
			case 7: return T7_I0_A0;
			case 8: return T8_I0_A0;
			case 9: return T9_I0_A0;
			case 10: return T10_I0_A0;
			case 11: return T11_I0_A0;
			case 12: return T12_I0_A0;
		}
		throw new UnsupportedOperationException();
	}

	public static @NonNull ExtraParameters get(int templateParameters, int iterators, int accumulators) {
		if (accumulators == 1) {						// iterate
			if (iterators == 1) {
				switch (templateParameters) {
					case 0: return T0_I1_A1;
					case 1: return T1_I1_A1;
					case 2: return T2_I1_A1;
				}
			}
			else if (iterators == 2) {
				switch (templateParameters) {
					case 0: return T0_I2_A1;
					case 1: return T1_I2_A1;
					case 2: return T2_I2_A1;
				}
			}
			else if (iterators == 3) {
				switch (templateParameters) {
					case 0: return T0_I3_A1;
					case 1: return T1_I3_A1;
					case 2: return T2_I3_A1;
				}
			}
		}
		else if (accumulators == 0) {						// not iterate
			if (iterators == 0) {
				return getTemplateParameters(templateParameters);
			}
			else if (templateParameters == 0) {
				switch (iterators) {
					case 0: return NONE;
					case 1: return T0_I1_A0;
					case 2: return T0_I2_A0;
					case 3: return T0_I3_A0;
					case 4: return T0_I4_A0;
					case 5: return T0_I5_A0;
					case 6: return T0_I6_A0;
				}
			}
			else if (templateParameters == 1) {
				switch (iterators) {
					case 1: return T1_I1_A0;
					case 2: return T1_I2_A0;
					case 3: return T1_I3_A0;
				//	case 4: return T0_I4_A0;
				//	case 5: return T0_I5_A0;
				//	case 6: return T0_I6_A0;
				}
			}
		}
		throw new UnsupportedOperationException();
	}
}