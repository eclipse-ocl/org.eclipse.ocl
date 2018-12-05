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
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.analyzer.CodeGenAnalyzer;
import org.eclipse.ocl.examples.codegen.analyzer.DependencyVisitor;
import org.eclipse.ocl.examples.codegen.analyzer.ReferencesVisitor;
import org.eclipse.ocl.examples.codegen.cgmodel.CGElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.StringUtil;
import org.eclipse.ocl.pivot.utilities.TracingOption;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

/**
 * The GlobalPlace describes a forest of CG trees that can be resolved as global constants.
 */
public class GlobalPlace extends AbstractPlace
{
	public static @NonNull AbstractPlace createGlobalPlace(@NonNull Map<CGElement, AbstractPlace> element2place, @NonNull CGElement cgElement) {
		AbstractPlace abstractPlace = element2place.get(null);
		assert abstractPlace != null;
		return abstractPlace;
	}

	protected final @NonNull CodeGenAnalyzer analyzer;
	protected final @NonNull ReferencesVisitor referencesVisitor;
	private final @NonNull Map<@Nullable CGElement, @NonNull AbstractPlace> element2place = new HashMap<>();
	private final @NonNull Set<@NonNull OuterStackPlace> stackPlaces = new HashSet<>();
	private final @NonNull Map<@NonNull CGElement, @NonNull SimpleAnalysis> element2simpleAnalysis = new HashMap<>();
	protected final @NonNull HashedAnalyses globalAnalyses = new HashedAnalyses();

	public GlobalPlace(@NonNull CodeGenAnalyzer analyzer) {
		this.analyzer = analyzer;
		this.referencesVisitor = analyzer.getCodeGenerator().createReferencesVisitor();
	}

	public void addSimpleAnalysis(@NonNull SimpleAnalysis simpleAnalysis) {
		//		System.out.println(ClassUtil.debugSimpleName(simpleAnalysis.getCGElement()) + " => " + ClassUtil.debugSimpleName(simpleAnalysis));
		CGValuedElement cgElement = simpleAnalysis.getElement();
		element2simpleAnalysis.put(cgElement, simpleAnalysis);
		if (cgElement.isGlobal() && !cgElement.isInlined()) {
			assert cgElement.isConstant();							// FIXME debugging
			globalAnalyses.addSimpleAnalysis(simpleAnalysis);
		}
	}

	void addStackPlace(@NonNull OuterStackPlace stackPlace) {
		stackPlaces.add(stackPlace);
	}

	/**
	 * Populate the map from structural hash code to same-hashed analysis in the analysis tree rooted at thisAnalysis.
	 * <p>
	 * Returns the SimpleAnalysis of cgElement.
	 */
	protected @Nullable SimpleAnalysis buildSimpleAnalysisTree(@NonNull CGElement cgElement, int depth) {
		if (CommonSubexpressionEliminator.CSE_BUILD.isActive()) {
			CommonSubexpressionEliminator.CSE_BUILD.println(StringUtil.getIndentation(depth, "  ") + "Build " + NameUtil.debugSimpleName(cgElement) + " " + StringUtil.convertToOCLString(cgElement.toString()));
		}
		//
		//	Create the Place hierarchy in Preorder
		//
		AbstractPlace abstractPlace = cgElement.getPlace(element2place);
		if (abstractPlace == null) {
			return null;
		}
		if (element2place.get(cgElement) == null) {
			element2place.put(cgElement, abstractPlace);
		}
		if (CommonSubexpressionEliminator.CSE_BUILD.isActive()) {
			CommonSubexpressionEliminator.CSE_BUILD.println(StringUtil.getIndentation(depth, "  ") + " ==> " + NameUtil.debugSimpleName(abstractPlace));
		}
		//
		//	Determine the local part of the structural hash code from referenced objects
		//
		int structuralHashCode = getStructuralHashCode(cgElement, "");
		//
		//	Accumulate the contained part of the structural hash code in a post order traversal
		//
		Iterable<? extends CGElement> childElements = cgElement.getChildren();
		List<@NonNull SimpleAnalysis> childAnalyses = null;
		for (CGElement cgChild : childElements) {
			if ((cgChild != null) /*&& (cgChild.eContainmentFeature() != CGModelPackage.Literals.CG_VALUED_ELEMENT__OWNS)*/) {
				SimpleAnalysis childAnalysis = buildSimpleAnalysisTree(cgChild, depth+1);
				if (childAnalysis != null) {
					structuralHashCode = 3 * structuralHashCode + childAnalysis.getStructuralHashCode();
					if (childAnalyses == null) {
						childAnalyses = new ArrayList<>();
					}
					childAnalyses.add(childAnalysis);
				}
			}
		}
		if (!(cgElement instanceof CGValuedElement)) {
			return null;
		}
		CGValuedElement cgValuedElement = (CGValuedElement)cgElement;
		//		System.out.println("Build2 " + ClassUtil.debugSimpleName(cgElement));
		if (CommonSubexpressionEliminator.CSE_BUILD.isActive()) {
			CommonSubexpressionEliminator.CSE_BUILD.println(StringUtil.getIndentation(depth, "  ") + "=> " + NameUtil.debugSimpleName(abstractPlace) + " " + structuralHashCode);
		}
		if (abstractPlace != this) {			// FIXME Ugh!
			StackPlace stackPlace = abstractPlace.getStackPlace();
			if (stackPlace == null) {
				return null;
			}
			if ((cgValuedElement == stackPlace.getStackElement()) && (stackPlace instanceof OuterStackPlace)) {
				return null;
			}
		}
		//		System.out.println("Build3 " + ClassUtil.debugSimpleName(cgElement));
		//		System.out.println("new " + structuralHashCode + " " + ClassUtil.debugSimpleName(cgValuedElement) + " " + cgValuedElement.toString());
		SimpleAnalysis structuralAnalysis;
		if (childAnalyses != null) {
			@SuppressWarnings("null")@NonNull SimpleAnalysis @NonNull [] childArray = childAnalyses.toArray(new SimpleAnalysis[childAnalyses.size()]);
			structuralAnalysis = new SimpleAnalysis(this, cgValuedElement, depth, structuralHashCode, childArray);
		}
		else {
			structuralAnalysis = new SimpleAnalysis(this, cgValuedElement, depth, structuralHashCode, SimpleAnalysis.EMPTY_LIST);
		}
		if (CommonSubexpressionEliminator.CSE_BUILD.isActive()) {
			CommonSubexpressionEliminator.CSE_BUILD.println(StringUtil.getIndentation(depth, "  ") + "=> " + NameUtil.debugSimpleName(structuralAnalysis) + " " + structuralAnalysis.toString());
		}
		return structuralAnalysis;
	}

	public @NonNull CodeGenAnalyzer getAnalyzer() {
		return analyzer;
	}

	public @NonNull ControlPlace getControlPlace(@NonNull CGValuedElement cgElement) {
		AbstractPlace abstractPlace = element2place.get(cgElement);
		if (abstractPlace instanceof ControlPlace) {
			return (ControlPlace) abstractPlace;
		}
		else {
			throw new IllegalStateException("Not ControlPlace " + abstractPlace);
		}
	}

	@Override
	public @NonNull GlobalPlace getGlobalPlace() {
		return this;
	}

	@Override
	public @NonNull GlobalPlace getParentPlace() {
		return this;
	}

	public @Nullable AbstractPlace getPlace(@NonNull CGElement cgElement) {
		return element2place.get(cgElement);
	}

	public @NonNull ReferencesVisitor getReferencesVisitor() {
		return referencesVisitor;
	}

	public @Nullable SimpleAnalysis getSimpleAnalysis(@NonNull Object anObject) {
		return element2simpleAnalysis.get(anObject);
	}

	public @Nullable List<@NonNull CGValuedElement> getSortedGlobals(@NonNull DependencyVisitor dependencyVisitor) {
		if (globalAnalyses.isEmpty()) {
			return null;
		}
		Set<@NonNull CGValuedElement> sortedGlobals = new HashSet<>();
		for (AbstractAnalysis analysis : globalAnalyses) {
			CGValuedElement value = analysis.getPrimaryElement().getNamedValue();
			assert value.isGlobal();
			sortedGlobals.add(value);
		}
		dependencyVisitor.visitAll(sortedGlobals);
		return dependencyVisitor.getSortedDependencies(true);
	}

	@Override
	public @Nullable StackPlace getStackPlace() {
		return null;
	}

	protected int getStructuralHashCode(@NonNull CGElement cgElement, @NonNull String prefix) {
		//		System.out.println(prefix + "getStructuralHashCode " + ClassUtil.debugSimpleName(cgElement) + " " + cgElement.toString());
		int structuralHashCode = cgElement.getClass().getName().hashCode();
		//		System.out.println(prefix + "  class " + structuralHashCode);
		List<@Nullable Object> referencedObjects = cgElement.accept(referencesVisitor);
		for (@Nullable Object referencedObject : referencedObjects) {
			int referenceHashCode = 1;
			if (referencedObject != null) {
				if (referencedObject instanceof CGElement) {
					referenceHashCode = getStructuralHashCode((CGElement) referencedObject, prefix + "  ");
				}
				else {
					referenceHashCode = referencedObject.hashCode();
				}
				//				System.out.println(prefix + "  ref " + referenceHashCode + " " + ClassUtil.debugSimpleName(referencedObject) + " " + referencedObject.toString());
			}
			structuralHashCode = 3 * structuralHashCode + referenceHashCode;
		}
		//		System.out.println(prefix + "  = " + structuralHashCode + " " + ClassUtil.debugSimpleName(cgElement) + " " + cgElement.toString());
		return structuralHashCode;
	}

	/**
	 * Optimize the cgRoot tree by eliminating common subexpressions.
	 */
	public void optimize(@NonNull CGElement cgRoot) {
		element2place.put(null, this);
		element2place.put(cgRoot, this);
		//
		//	Analyze the tree to create an analysis per node and a partitioning into Places
		//	with a Map from structural hash code to analysis in each Place.
		//
		buildSimpleAnalysisTree(cgRoot, 0);
		//
		if (CommonSubexpressionEliminator.CSE_PLACES.isActive()) {
			CommonSubexpressionEliminator.CSE_PLACES.println("Places");
			printHierarchy(CommonSubexpressionEliminator.CSE_PLACES, "");
		}
		//
		//	Optimize each outer stack place.
		//
		for (OuterStackPlace stackPlace : stackPlaces) {
			stackPlace.optimize();
		}
		optimizeGlobals(cgRoot);
	}

	public void optimizeGlobals(@NonNull CGElement cgRoot) {
		//
		//	Rewrite shared analyses as LetExps, VarExps.
		//
		if (!globalAnalyses.isEmpty()) {
			Multimap<@NonNull Integer, @NonNull CommonAnalysis> depth2commonAnalyses = HashMultimap.create();
			for (AbstractAnalysis analysis : globalAnalyses) {
				if (analysis instanceof CommonAnalysis) {
					CommonAnalysis commonAnalysis = (CommonAnalysis)analysis;
					int maxDepth = commonAnalysis.getMaxDepth();
					depth2commonAnalyses.put(maxDepth, commonAnalysis);
				}
			}
			List<@NonNull Integer> sortedMaxDepths = new ArrayList<>(depth2commonAnalyses.keySet());
			Collections.sort(sortedMaxDepths);
			Collections.reverse(sortedMaxDepths);
			for (int maxDepth : sortedMaxDepths) {
				List<@NonNull CommonAnalysis> commonAnalyses = new ArrayList<>(depth2commonAnalyses.get(maxDepth));
				Collections.sort(commonAnalyses);
				for (@NonNull CommonAnalysis commonAnalysis : commonAnalyses) {
					commonAnalysis.rewriteGlobal(analyzer);
				}
			}
		}
		if (CommonSubexpressionEliminator.CSE_REWRITE.isActive()) {
			CommonSubexpressionEliminator.CSE_REWRITE.println("Places after rewrite");
			printHierarchy(CommonSubexpressionEliminator.CSE_REWRITE, "");
			@NonNull String string = String.valueOf(cgRoot);
			TracingOption.println(CommonSubexpressionEliminator.CSE_REWRITE, string);
		}
	}

	@Override
	public void printHierarchy(@NonNull Appendable appendable, @NonNull String indentation) {
		TracingOption.println(appendable, indentation + "GlobalPlace");
		if (!globalAnalyses.isEmpty()) {
			for (AbstractAnalysis analysis : globalAnalyses) {
				TracingOption.println(appendable, indentation + "    " + analysis.getStructuralHashCode() + "," + analysis);
			}
		}
		else {
			TracingOption.println(appendable, indentation + "    <empty>");
		}
		for (OuterStackPlace stackPlace : stackPlaces) {
			stackPlace.printHierarchy(appendable, indentation + "  ");
		}
	}

	@Override
	public String toString() {
		return getClass().getSimpleName();
	}
}