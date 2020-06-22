/*******************************************************************************
 * Copyright (c) 2020 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.xtext.base.cs2text;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

public abstract class AbstractSerializationNode implements SerializationNode
	{
		/**
		 * The overall (multi-)grammar analysis.
		 */
		protected final @NonNull XtextGrammarAnalysis grammarAnalysis;
	//	protected final @NonNull String cardinality;
		private int lowerBound;
		private int upperBound;

		public AbstractSerializationNode(@NonNull XtextGrammarAnalysis grammarAnalysis, @Nullable String cardinality) {
			this.grammarAnalysis = grammarAnalysis;
			if (cardinality == null) {
				this.lowerBound = 1;
				this.upperBound = 1;
			}
			else if (cardinality.equals("?")) {
				this.lowerBound = 0;
				this.upperBound = 1;
			}
			else if (cardinality.equals("*")) {
				this.lowerBound = 0;
				this.upperBound = -1;
			}
			else if (cardinality.equals("+")) {
				this.lowerBound = 1;
				this.upperBound = -1;
			}
			else {
				throw new UnsupportedOperationException("Unsupported cardinality '" + cardinality + "'");
			}
		}

//		public boolean addAlternative(@NonNull SerializationNode nestedContent) {
//			return false;
//		}

//		public boolean addAlternative(@NonNull AbstractElement newContent) {
//			return false;
//		}

		protected void appendCardinality(@NonNull StringBuilder s) {
			if ((lowerBound != 1) || (upperBound != 1)) {
				s.append(getCardinality());
			}
		}

		@Override
		public @NonNull String getCardinality() {
			if (upperBound < 0) {
				return lowerBound != 0 ? "+" : "*";
			}
			else if (upperBound == 1) {
				return lowerBound != 0 ? "1" : "?";
			}
			else if (upperBound == lowerBound) {
				return Integer.toString(lowerBound);
			}
			else {
				return lowerBound + ".." + upperBound;
			}
		}

		@Override
		public @Nullable SerializationBuilder isCompatible(@NonNull UserModelAnalysis modelAnalysis, @NonNull StringBuilder s, @NonNull EObject element) {
			return new SerializationBuilder(modelAnalysis, s);
		}

		@Override
		public boolean isNull() {
			return false;
		}

		@Override
		public void serialize(@NonNull SerializationBuilder serializationBuilder, @NonNull EObject element) {
			serializationBuilder.append("<<<Unsupported serialize '" + getClass().getSimpleName() + "'>>>");
		}

		@Override
		public void setCardinality(@NonNull String cardinality) {
			if ("?".equals(cardinality)) {
				lowerBound = 0;
			}
			else if ("*".equals(cardinality)) {
				lowerBound = 0;
				upperBound = -1;
			}
			else if ("+".equals(cardinality)) {
			//??	lowerBound = 1;
				upperBound = -1;
			}
			else {
				throw new UnsupportedOperationException("Unsupported cardinality '" + cardinality + "'");
			}
		}

		@Override
		public @NonNull String toString() {
			StringBuilder s = new StringBuilder();
			toString(s, 0);
			return s.toString();
		}
	}