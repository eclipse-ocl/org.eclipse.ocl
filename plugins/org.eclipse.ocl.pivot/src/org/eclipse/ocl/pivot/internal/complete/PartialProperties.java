/*******************************************************************************
 * Copyright (c) 2011, 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.complete;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.flat.FlatClass;
import org.eclipse.ocl.pivot.flat.FlatFragment;
import org.eclipse.ocl.pivot.internal.scoping.EnvironmentView;
import org.eclipse.ocl.pivot.internal.scoping.EnvironmentView.Disambiguator;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

public class PartialProperties //implements Iterable<@NonNull Property>
{
	//resolution = null, partials = null or empty => empty
	// resolution = X, partials = null or empty or [X} => X
	// resolution = null, partials not empty => lazy unresolved 'ambiguity'
//	private boolean isResolved = false;
	private @Nullable Property resolution = null;
	private @NonNull List<@NonNull Property> partials = new ArrayList<>();
	/**
	 * @since 7.0
	 */
	protected final @NonNull StandardLibrary standardLibrary;

	/**
	 * @param old
	 * @since 7.0
	 */
	public PartialProperties(@NonNull StandardLibrary standardLibrary, @NonNull Property asProperty) {
		this.standardLibrary = standardLibrary;
		partials.add(asProperty);
		resolution = asProperty;
	}

	/**
	 * @since 7.0
	 */
	public void addProperty(@NonNull Property asProperty, @NonNull FlatFragment fragment) {
		assert !partials.contains(asProperty);
		FlatClass baseFlatClass = fragment.getBaseFlatClass();
		String name = PivotUtil.getName(asProperty);
		for (FlatFragment superFlatFragment : baseFlatClass.getDirectSuperFragments()) {
			for (@NonNull Property asSuperProperty : superFlatFragment.getProperties()) {
				if (name.equals(asSuperProperty.getName()) && !asSuperProperty.isIsImplicit()) {
					partials.remove(asSuperProperty);
				//	System.out.println("Occluded " + asSuperProperty);
				}
			}
		//	Iterable<@NonNull Property> localProperties = superFlatFragment.getProperties();
		}
		partials.add(asProperty);
		resolution = null;
//		isResolved = false;
	}

	/**
	 * @since 7.0
	 */
	public @NonNull List<@NonNull Property> getPartials() {
		return partials;
	}

	/**
	 * @since 7.0
	 */
	public @NonNull Property getPrimaryProperty() {
		Property resolution2 = resolution;
		if (resolution2 == null) {
			resolution = resolution2 = selectPrimaryProperty(partials);
		}
		return resolution2;
	}

	private @NonNull Property selectPrimaryProperty(@NonNull Collection<@NonNull Property> partials) {
		assert !partials.isEmpty();


		List<@NonNull  Property> values = new ArrayList<>(partials);
		for (int i = 0; i < values.size()-1;) {
			boolean iRemoved = false;
			@NonNull Property iValue = values.get(i);
			for (int j = i + 1; j < values.size();) {
				Class<? extends Property> iClass = iValue.getClass();
				@NonNull Property jValue = values.get(j);
				Class<? extends Property> jClass = jValue.getClass();
				int verdict = 0;
				for (Class<?> key : EnvironmentView.getDisambiguatorKeys()) {
					if (key.isAssignableFrom(iClass) && key.isAssignableFrom(jClass)) {
						List<@NonNull Disambiguator<@NonNull Object>> disambiguators = EnvironmentView.getDisambiguators(key);
						if (disambiguators != null) {
							for (Disambiguator<@NonNull Object> disambiguator : disambiguators) {
								assert standardLibrary != null;
								verdict = disambiguator.compare(standardLibrary, iValue, jValue);
								if (verdict != 0) {
									break;
								}
							}
						}
						if (verdict != 0) {
							break;
						}
					}
				}
				if (verdict == 0) {
					j++;
				} else if (verdict < 0) {
					values.remove(i);
					iRemoved = true;
					break;
				} else {
					values.remove(j);
				}
			}
			if (!iRemoved) {
				i++;
			}
		}
		if (values.size() == 1) {
			return values.get(0);
		//	isResolved = true;
		//	return;
		}
		throw new IllegalStateException("Ambiguous");
	//	throw new SemanticException("Ambiguous");


	/*	Property asPrimaryProperty = null;
		EObject asPrimaryEObject = null;
		Property asPrimaryOpposite = null;
		FlatClass asPrimaryFlatClass = null;
		for (@NonNull Property asProperty : asProperties) {
		//	assert name.equals(asProperty.getName());
			Property asOpposite = asProperty.getOpposite();
			org.eclipse.ocl.pivot.Class asType = PivotUtil.getClass(asProperty);
			completeModel.getCompleteClass(asType);
			FlatClass flatClass = completeModel.getFlatClass(asType);
			if (asPrimaryProperty == null) {
				asPrimaryProperty = asProperty;
				asPrimaryEObject = asProperty.getESObject();
				asPrimaryOpposite = asOpposite;
				asPrimaryFlatClass = flatClass;
			}
			else {
				assert asPrimaryFlatClass == flatClass;
				assert (asOpposite != null) == (asPrimaryOpposite != null);
				assert (asPrimaryOpposite == null) || (asOpposite == null) || (asOpposite.getName().equals(asPrimaryOpposite.getName()));

				EObject esObject = asProperty.getESObject();
				if (asPrimaryEObject == null) {
					asPrimaryProperty = asProperty;
					asPrimaryEObject = esObject;
					asPrimaryOpposite = asOpposite;
					asPrimaryFlatClass = flatClass;
				}
				else if ((esObject instanceof EStructuralFeature) && !(asPrimaryEObject instanceof EStructuralFeature)) { // UML has a secondary non-EStructuralFeature
					asPrimaryProperty = asProperty;
					asPrimaryEObject = esObject;
					asPrimaryOpposite = asOpposite;
					asPrimaryFlatClass = flatClass;
				}
				else if (esObject != null) {
					assert false;
				}
			/*	Iterable<@NonNull Property> partials = properties; //((PartialProperties)properties).getPartials();
				if (partials != null) {
					for (Property partialProperty : partials) {
						EObject esObject = partialProperty.getESObject();
						if (esObject instanceof EStructuralFeature) {
							eFeature2 = (EStructuralFeature) esObject;
							break;
						}
					}
				}
		//	} * /

				//}
			}
		}

		return asPrimaryProperty; */
	}

//	@Override
/*	public @NonNull Iterator<@NonNull Property> iterator() {
		if (resolution == null) {
			resolve();
		}
		if (resolution != null) {
			return Iterators.singletonIterator(resolution);
		}
		else {
			return partials.iterator();
		}
	} */

/*	private void resolve() {
		assert resolution == null;
		int size = partials.size();
		if (size <= 0) {
			return;
		}
		if (size == 1) {
		//	isResolved = true;
			resolution = partials.get(0);
		}
		List<@NonNull  Property> values = new ArrayList<>(partials);
		for (int i = 0; i < values.size()-1;) {
			boolean iRemoved = false;
			@NonNull Property iValue = values.get(i);
			for (int j = i + 1; j < values.size();) {
				Class<? extends Property> iClass = iValue.getClass();
				@NonNull Property jValue = values.get(j);
				Class<? extends Property> jClass = jValue.getClass();
				int verdict = 0;
				for (Class<?> key : EnvironmentView.getDisambiguatorKeys()) {
					if (key.isAssignableFrom(iClass) && key.isAssignableFrom(jClass)) {
						List<@NonNull Comparator<@NonNull Object>> disambiguators = EnvironmentView.getDisambiguators(key);
						if (disambiguators != null) {
							for (Comparator<@NonNull Object> comparator : disambiguators) {
								if (comparator instanceof Disambiguator<?>) {
									verdict = ((Disambiguator<@NonNull Object>)comparator).compare(standardLibrary, iValue, jValue);
								}
								else {
									verdict = comparator.compare(iValue, jValue);
								}
								if (verdict != 0) {
									break;
								}
							}
						}
						if (verdict != 0) {
							break;
						}
					}
				}
				if (verdict == 0) {
					j++;
				} else if (verdict < 0) {
					values.remove(i);
					iRemoved = true;
					break;
				} else {
					values.remove(j);
				}
			}
			if (!iRemoved) {
				i++;
			}
		}
		if (values.size() == 1) {
			resolution = values.get(0);
		//	isResolved = true;
			return;
		}
	} */

	@Override
	public String toString() {
		if (resolution != null) {
			return resolution.toString();
		}
		StringBuilder s = new StringBuilder();
		for (@NonNull Property asProperty : partials) {
			if (s.length() > 0) {
				s.append(", ");
			}
			s.append(asProperty.toString());
		}
		return s.toString();
	}
}