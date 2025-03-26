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
package org.eclipse.ocl.doc.ocl2025.tests;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

public class RandomObjectListGenerator //extends AbstractRandomListGenerator<@NonNull Integer>
{
	public static class Klass {
		int id;
		String name;
		List<@NonNull Klass> parents;
		@NonNull List<@NonNull Feature> features;

		public Klass(int id, @NonNull List<@NonNull Feature> features) {
			this.id = id;
			this.name = null;
			this.features = features;
		}

		public void setParents(@NonNull List<@NonNull Klass> parents) {
			this.parents = parents;
		}

		public @NonNull List<@NonNull Feature> getFeatures() {
			return features;
		}

		public @Nullable String getName() {
			return name;
		}

		public @NonNull List<@NonNull Klass> getParents() {
			return parents;
		}

		public void setName(@Nullable String name) {
			this.name = name;
		}
	}

	public static class Feature
	{
		int name;

		public Feature(int name) {
			this.name = name;
		}
	}


	//	@Override
	//	protected @NonNull Integer createRandomElement(@NonNull Random random) {
	//		return random.nextInt();
	//	}


	public @NonNull Feature @NonNull [] createFeatures(int testSize) {
		@NonNull Feature[] allFeatures = new @NonNull Feature[testSize];
		for (int i = 0; i < testSize; i++) {
			allFeatures[i] = new Feature(i);
		}
		return allFeatures;
	}


	private @NonNull Random random = new Random();

	public @NonNull Klass @NonNull [] createKlasses(int testSize, @NonNull Feature @NonNull [] allFeatures) {
		@NonNull Klass @NonNull [] allKlasses = new @NonNull Klass[testSize];
		for (int i = 0; i < testSize; i++) {
			@NonNull List<@NonNull Feature> features = new ArrayList<>();
			for (int j = 0; j < 10; j++ ) {
				features.add(allFeatures[random.nextInt(allFeatures.length)]);
			}
			allKlasses[i] = new Klass(i, features);
		}
		for (int i = 0; i < testSize; i++) {
			@NonNull List<@NonNull Klass> parents = new ArrayList<>();
			for (int j = 0; j < 10; j++ ) {
				parents.add(allKlasses[random.nextInt(testSize)]);
			}
			allKlasses[i].setParents(parents);
		}
		return allKlasses;
	}
}
