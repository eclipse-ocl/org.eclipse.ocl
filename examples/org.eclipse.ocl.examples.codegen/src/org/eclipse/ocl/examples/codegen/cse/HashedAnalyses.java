/*******************************************************************************
 * Copyright (c) 2012, 2018 CEA LIST and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink(CEA LIST) - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.cse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

/**
 * HashedAnalyses maintains a Map from Structural Hash Code to Collection of structurally distinct
 * AbstractAnalysis instances; distinct from all others according to an isStructurallyEqualTo comparison.
 * <p>
 * The map is initially populated with SimpleAnalysis instances, which may evolve to SharedAnalysis
 * instances as additional structurally idenmtical AbstractAnalysis instnaces are added.
 */
public class HashedAnalyses implements Iterable<@NonNull AbstractAnalysis>
{
	public static @Nullable HashedAnalyses intersection(@NonNull HashedAnalyses iHash2Analyses, @NonNull HashedAnalyses jHash2Analyses) {
		Multimap<@NonNull Integer, @NonNull AbstractAnalysis> intersection = null;
		for (int hash : iHash2Analyses.map.keySet()) {
			Collection<@NonNull AbstractAnalysis> jAnalyses = jHash2Analyses.map.get(hash);
			if (jAnalyses != null) {
				for (@NonNull AbstractAnalysis iAnalysis : iHash2Analyses.map.get(hash)) {
					for (@NonNull AbstractAnalysis jAnalysis : jAnalyses) {
						if (iAnalysis.isStructurallyEqualTo(jAnalysis)) {
							if (intersection == null) {
								intersection = HashMultimap.create();
							}
							intersection.put(hash, iAnalysis);
							intersection.put(hash, jAnalysis);
						}
					}
				}
			}
		}
		return intersection != null ? new HashedAnalyses(intersection) : null;
	}

	public static <@NonNull V> void printIndented(@NonNull Appendable appendable, @NonNull Multimap<@NonNull Integer,V> map, @NonNull String indentation, @NonNull String title) {
		try {
			List<@NonNull Integer> keys = new ArrayList<>(map.keySet());
			Collections.sort(keys);
			for (@NonNull Integer key : keys) {
				appendable.append(indentation + title + " " + key + "\n");
				for (V analysis : map.get(key)) {
					appendable.append(indentation + "\t" + analysis.toString() + "\n");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private @NonNull Multimap<@NonNull Integer, @NonNull AbstractAnalysis> map;

	@SuppressWarnings("null")
	public HashedAnalyses() {
		this.map = HashMultimap.create();
	}

	private HashedAnalyses(@NonNull Multimap<@NonNull Integer, @NonNull AbstractAnalysis> map) {
		this.map = map;
	}

	public @NonNull AbstractAnalysis add(@NonNull AbstractAnalysis anAnalysis) {
		int structuralHashCode = anAnalysis.getStructuralHashCode();
		AbstractAnalysis oldAnalysis = get(anAnalysis);
		if (oldAnalysis == null) {
			map.put(structuralHashCode, anAnalysis);
			return anAnalysis;
		}
		else {
			AbstractAnalysis newAnalysis = oldAnalysis.addAnalysis(anAnalysis);
			if (newAnalysis != oldAnalysis) {
				map.remove(structuralHashCode, oldAnalysis);
				map.put(structuralHashCode, newAnalysis);
			}
			return newAnalysis;
		}
	}

	public @NonNull AbstractAnalysis addSimpleAnalysis(@NonNull SimpleAnalysis anAnalysis) {
		int structuralHashCode = anAnalysis.getStructuralHashCode();
		AbstractAnalysis oldAnalysis = get(anAnalysis);
		if ((oldAnalysis == null) || !anAnalysis.getElement().isCommonable()) {
			map.put(structuralHashCode, anAnalysis);
			return anAnalysis;
		}
		else {
			AbstractAnalysis newAnalysis = oldAnalysis.addAnalysis(anAnalysis);
			if (newAnalysis != oldAnalysis) {
				map.remove(structuralHashCode, oldAnalysis);
				map.put(structuralHashCode, newAnalysis);
			}
			return newAnalysis;
		}
	}

	public @Nullable AbstractAnalysis get(@NonNull AbstractAnalysis childAnalysis) {
		Collection<AbstractAnalysis> theseAnalyses = map.get(childAnalysis.getStructuralHashCode());
		if (theseAnalyses == null) {
			return null;
		}
		for (AbstractAnalysis thisAnalysis : theseAnalyses) {
			if (thisAnalysis.isStructurallyEqualTo(childAnalysis)) {
				return thisAnalysis;
			}
		}
		return null;
	}

	public boolean isEmpty() {
		return map.size() <= 0;
	}

	@Override
	public @NonNull Iterator<@NonNull AbstractAnalysis> iterator() {
		return map.values().iterator();
	}

	public @Nullable AbstractAnalysis remove(@NonNull AbstractAnalysis thatAnalysis) {
		int hash = thatAnalysis.getStructuralHashCode();
		Collection<@NonNull AbstractAnalysis> theseAnalyses = map.get(hash);
		if (theseAnalyses != null) {
			for (@NonNull AbstractAnalysis thisAnalysis : theseAnalyses) {
				if (thisAnalysis.isStructurallyEqualTo(thatAnalysis)) {
					theseAnalyses.remove(thisAnalysis);
					return thisAnalysis;
				}
			}
		}
		return null;
	}

	@Override
	public String toString() {
		Appendable s = new StringBuilder();
		printIndented(s, map, "", "");
		return s.toString();
	}
}