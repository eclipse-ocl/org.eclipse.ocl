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
package org.eclipse.ocl.examples.codegen.cse;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.codegen.analyzer.CodeGenAnalyzer;
import org.eclipse.ocl.examples.codegen.cgmodel.CGConstantExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorType;
import org.eclipse.ocl.examples.codegen.cgmodel.CGLetExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelFactory;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGVariable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGVariableExp;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;

public class CommonAnalysis extends AbstractAnalysis
{
	private /*@LazyNonNull*/ SimpleAnalysis primaryAnalysis = null;
	protected final @NonNull List<SimpleAnalysis> simpleAnalyses = new ArrayList<SimpleAnalysis>();
	private int minDepth = Integer.MAX_VALUE;
	private int maxDepth = Integer.MIN_VALUE;

	public CommonAnalysis(@NonNull SimpleAnalysis firstAnalysis, @NonNull SimpleAnalysis secondAnalysis) {
//		this.primaryAnalysis = firstAnalysis;
		firstAnalysis.setCommonAnalysis(this);
		if (secondAnalysis != firstAnalysis) {
			secondAnalysis.setCommonAnalysis(this);
		}
	}

	@Override
	public @NonNull CommonAnalysis addAnalysis(@NonNull AbstractAnalysis anAnalysis) {
		return anAnalysis.addCommonAnalysis(this);
	}

	@Override
	public @NonNull CommonAnalysis addCommonAnalysis(@NonNull CommonAnalysis commonAnalysis) {
		for (@SuppressWarnings("null")@NonNull SimpleAnalysis simpleAnalysis : new ArrayList<SimpleAnalysis>(commonAnalysis.simpleAnalyses)) {
			addSimpleAnalysis(simpleAnalysis);
		}
		return this;
	}

	@Override
	public @NonNull CommonAnalysis addSimpleAnalysis(@NonNull SimpleAnalysis simpleAnalysis) {
		simpleAnalysis.setCommonAnalysis(this);
		return this;
	}

	public @NonNull CommonAnalysis addedSimpleAnalysis(@NonNull SimpleAnalysis simpleAnalysis) {
//		assert isStructurallyEqualTo(simpleAnalysis);
		simpleAnalyses.add(simpleAnalysis);
		int depth = simpleAnalysis.getDepth();
		if (primaryAnalysis == null) {
			primaryAnalysis = simpleAnalysis;
			this.minDepth = depth;
			this.maxDepth = depth;
		}
		else if (depth < this.minDepth) {
			primaryAnalysis = simpleAnalysis;
			this.minDepth = depth;
		}
		else if (depth > this.maxDepth) {
			this.maxDepth = depth;
		}
		assert primaryAnalysis.isStructurallyEqualTo(simpleAnalysis);
		return this;
	}

	@Override
	public int getMaxDepth() {
		return maxDepth;
	}

	@Override
	public int getMinDepth() {
		return minDepth;
	}

	@SuppressWarnings("null")
	public @NonNull SimpleAnalysis getPrimaryAnalysis() {
		return primaryAnalysis;
	}

	@Override
	public @NonNull CGValuedElement getPrimaryElement() {
		return primaryAnalysis.getElement();
	}

	@Override
	public int getStructuralHashCode() {
		return primaryAnalysis.getStructuralHashCode();
	}
	
	@Override
	public boolean isStructurallyEqualTo(@NonNull AbstractAnalysis thatAnalysis) {
		return primaryAnalysis.isStructurallyEqualTo(thatAnalysis);
	}
	
	@Override
	public boolean isStructurallyEqualTo(@NonNull SimpleAnalysis thatAnalysis) {
		return primaryAnalysis.isStructurallyEqualTo(thatAnalysis);
	}

	public void removedSimpleAnalysis(SimpleAnalysis simpleAnalysis) {
		simpleAnalyses.remove(simpleAnalysis);
	}

	public void rewrite(@NonNull CodeGenAnalyzer analyzer, @NonNull CGValuedElement controlElement) {
		CGValuedElement cgCSE = primaryAnalysis.getElement();
		if ((simpleAnalyses.size() > 1) || !cgCSE.isUncommonable()) {
			CGVariable cgVariable = CGModelFactory.eINSTANCE.createCGLocalVariable();
			cgVariable.setTypeId(cgCSE.getTypeId());
			cgVariable.setRequired(cgCSE.isNonNull());
			cgVariable.setAst(cgCSE.getAst());
			cgVariable.setName(analyzer.getNameManager().getGlobalSymbolName(cgVariable, "_cse"));
			for (SimpleAnalysis simpleAnalysis : simpleAnalyses) {
				CGValuedElement commonElement = simpleAnalysis.getElement();
				CGElement cgParent = commonElement.getParent();
//				assert cgParent != null;		null if already rewritten by another rewrite
				if ((cgParent != null) && !cgParent.rewriteAs(commonElement, cgCSE)) {
					rewriteAsVariableExp(commonElement, cgVariable);
				}
			}
			rewriteAsLet(controlElement, cgVariable);
			if (cgCSE.eResource() == null) {
				PivotUtilInternal.resetContainer(cgCSE);
			}
			else if (cgCSE instanceof CGExecutorType) {
				PivotUtilInternal.resetContainer(cgCSE);			// FIXME Bug 439603 is this reparenting valid?
			}
			else {
				PivotUtilInternal.resetContainer(cgCSE);			// FIXME Bug 439603 is this reparenting valid?
//				CGConstantExp cgConstExp = CGModelFactory.eINSTANCE.createCGConstantExp();
//				cgConstExp.setReferredConstant(cgCSE);
//				cgCSE = cgConstExp;
			}
			cgVariable.setInit(cgCSE);						// After all rewrites complete
		}
	}

	/**
	 * Insert a CGLetExp above cgIn for cgCSE.
	 */
	protected @NonNull CGLetExp rewriteAsLet(@NonNull CGValuedElement cgIn, @NonNull CGVariable cgVariable) {
		CGLetExp cgLetExp = CGModelFactory.eINSTANCE.createCGLetExp();
		cgLetExp.setTypeId(cgIn.getTypeId());
		cgLetExp.setAst(cgIn.getAst());
		CGUtil.replace(cgIn, cgLetExp);
		cgLetExp.setIn(cgIn);
		cgLetExp.setInit(cgVariable);
		return cgLetExp;
	}

	protected void rewriteAsVariableExp(@NonNull CGValuedElement cgElement, @NonNull CGVariable cgVariable) {
		CGVariableExp cgVarExp = CGModelFactory.eINSTANCE.createCGVariableExp();
		cgVarExp.setTypeId(cgVariable.getTypeId());
		cgVarExp.setAst(cgVariable.getAst());
		cgVarExp.setReferredVariable(cgVariable);
		CGUtil.replace(cgElement, cgVarExp);
	}

	public void rewriteGlobal(@NonNull CodeGenAnalyzer analyzer) {
		if (simpleAnalyses.size() > 1) {
			CGConstantExp primaryConstantExp;
			CGValuedElement primaryElement = primaryAnalysis.getElement();
			if (primaryElement instanceof CGConstantExp) {
				primaryConstantExp = (CGConstantExp)primaryElement;
			}
			else {
				CGElement primaryParent = primaryElement.getParent();
				if (primaryParent instanceof CGConstantExp) {
					primaryConstantExp = (CGConstantExp)primaryParent;
				}
				else {
					primaryConstantExp = CGModelFactory.eINSTANCE.createCGConstantExp();
					primaryConstantExp.setReferredConstant(primaryElement);
					primaryConstantExp.setAst(primaryElement.getAst());
					primaryConstantExp.setTypeId(primaryElement.getTypeId());
					primaryConstantExp.setName(primaryElement.getName());
					CGUtil.replace(primaryElement, primaryConstantExp);
				}
			}
			for (SimpleAnalysis secondaryAnalysis : simpleAnalyses) {
				if (secondaryAnalysis != primaryAnalysis) {
					CGConstantExp secondaryConstantExp;
					CGValuedElement secondaryElement = secondaryAnalysis.getElement();
					if (secondaryElement instanceof CGConstantExp) {
						secondaryConstantExp = (CGConstantExp)secondaryElement;
					}
					else {
						CGElement secondaryParent = secondaryElement.getParent();
						if (secondaryParent instanceof CGConstantExp) {
							secondaryConstantExp = (CGConstantExp)secondaryParent;
						}
						else {
							secondaryConstantExp = CGModelFactory.eINSTANCE.createCGConstantExp();
							secondaryConstantExp.setReferredConstant(secondaryElement);
							secondaryConstantExp.setAst(secondaryElement.getAst());
							secondaryConstantExp.setTypeId(secondaryElement.getTypeId());
							secondaryConstantExp.setName(secondaryElement.getName());
							CGUtil.replace(secondaryElement, secondaryConstantExp);
						}
					}
					secondaryConstantExp.setReferredConstant(primaryConstantExp.getReferredConstant());
				}
			}
		}
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(simpleAnalyses.size() + "," + minDepth + ".." + maxDepth + " ");
		s.append(primaryAnalysis);
		for (SimpleAnalysis simpleAnalysis : simpleAnalyses) {
			if (simpleAnalysis != primaryAnalysis) {
				s.append(" ++ " + simpleAnalysis);
			}
		}
		return s.toString();
	}
}