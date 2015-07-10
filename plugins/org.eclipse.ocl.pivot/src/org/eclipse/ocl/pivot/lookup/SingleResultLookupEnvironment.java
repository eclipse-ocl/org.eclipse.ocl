/*******************************************************************************
 * Copyright (c) 2014, 2015 Willink Transformations Ltd., University of York and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Adolfo Sanchez-Barbudo Herrera (University of York) - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.lookup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.CompleteModel;
import org.eclipse.ocl.pivot.CompletePackage;
import org.eclipse.ocl.pivot.Feature;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.internal.lookup.LookupEnvironment;
import org.eclipse.ocl.pivot.internal.lookup.impl.LookupEnvironmentImpl;
import org.eclipse.ocl.pivot.internal.scoping.ScopeFilter;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;
import org.eclipse.ocl.pivot.utilities.PivotUtil;


/**
 * @since 1.1
 */
public class SingleResultLookupEnvironment extends LookupEnvironmentImpl implements SingleResultEnvironment {
	
	
	private static final Logger logger = Logger.getLogger(SingleResultLookupEnvironment.class);
	private @NonNull List<NamedElement> elements = new ArrayList<NamedElement>();
	private @NonNull String name; 
	private @NonNull Executor executor;
	
	
	public SingleResultLookupEnvironment(@NonNull Executor executor, @NonNull String name) {
		this.executor = executor;
		this.name = name;
		//this.envFactory = executor.getEnvironmentFactory();
	}
	
	public SingleResultLookupEnvironment(@NonNull Executor executor, @NonNull String name, Boolean isLocal) {
		this.executor = executor;
		this.name = name;
		//this.envFactory = executor.getEnvironmentFactory();
	}
	
	@Override
	@NonNull
	public LookupEnvironment addElement(@Nullable NamedElement namedElement) {
		if (namedElement != null) {
			if (name.equals(namedElement.getName())) {
				if (!elements.contains(namedElement)) { 	// FIXME use a set ?
					elements.add(namedElement);
				}
			}
		}
		return this;
	}
	
	
	@Override
	@NonNull
	public <NE extends NamedElement> LookupEnvironment addElements(
			@Nullable Collection<NE> elements) {
	
		if (elements != null) {
			for (NamedElement namedElement : elements) {
				addElement(namedElement);
			}	
		}
		return this;
	}


	@Override
	@Nullable
	public NamedElement getSingleResult() {
		int contentsSize = elements.size();
		if (contentsSize > 1) {
			logger.warn("Unhandled ambiguous content for '" + name + "'");
		}
		return  contentsSize == 0 ? null : elements.get(0);
	}

	@Override
	@NonNull
	public List<NamedElement> getAllResults() {
		return elements;
	}
	
	
	@Override
	public boolean hasFinalResult() {
		// Not thing found is not a final result
		return elements.size() == 0 ? false : true;
	}
	
	@Override
	@NonNull
	public Executor getExecutor() {
		return executor;
	}
	
/*	
	//
	// ADDITIONAL STUFF TO INTEGRATE WITH
	//
	
	public static abstract class Disambiguator<T> implements Comparator<T>
	{
	    @Override
		public int compare(T o1, T o2) {
		    throw new UnsupportedOperationException();
	    }
	    
	    public abstract int compare(@NonNull EnvironmentFactory environmentFactory, @NonNull T o1, @NonNull T o2);
	}
	
	private static final class ImplicitDisambiguator extends Disambiguator<Object>
	{
		@Override
		public int compare(@NonNull EnvironmentFactory environmentFactory, @NonNull Object match1, @NonNull Object match2) {
			boolean match1IsImplicit = (match1 instanceof Property) && ((Property)match1).isIsImplicit();
			boolean match2IsImplicit = (match2 instanceof Property) && ((Property)match2).isIsImplicit();
			if (!match1IsImplicit) {
				return match2IsImplicit ? 1 : 0;				// match2 inferior			
			}
			else {
				return match2IsImplicit ? 0 : -1;				// match1 inferior			
			}
		}
	}
	
	private static final class MetamodelMergeDisambiguator extends Disambiguator<Feature>
	{
		@Override
		public int compare(@NonNull EnvironmentFactory environmentFactory, @NonNull Feature match1, @NonNull Feature match2) {
			org.eclipse.ocl.pivot.Package p1 = PivotUtil.getContainingPackage(match1);
			org.eclipse.ocl.pivot.Package p2 = PivotUtil.getContainingPackage(match2);
			if (p1 == null) {
				return 0;
			}
			if (p2 == null) {
				return 0;
			}
			CompleteModel completeModel = environmentFactory.getCompleteModel();
			CompletePackage s1 = completeModel.getCompletePackage(p1);
			CompletePackage s2 = completeModel.getCompletePackage(p2);
			if (s1 != s2) {
				return 0;
			}
			int i1 = s1.getIndex(p1);
			int i2 = s2.getIndex(p2);
			return i2 - i1;
		}
	}

	private static final class OperationDisambiguator extends Disambiguator<Operation>
	{
		@Override
		public int compare(@NonNull EnvironmentFactory environmentFactory, @NonNull Operation match1, @NonNull Operation match2) {
			if (isRedefinitionOf(match1, match2)) {
				return 1;				// match2 inferior			
			}
			if (isRedefinitionOf(match2, match1)) {
				return -1;				// match1 inferior			
			}
			return 0;
		}

		protected boolean isRedefinitionOf(@NonNull Operation operation1, @NonNull Operation operation2) {
			List<Operation> redefinedOperations = operation1.getRedefinedOperations();
			for (Operation redefinedOperation : redefinedOperations) {
				if (redefinedOperation != null) {
					if (redefinedOperation == operation2) {
						return true;
					}
					if (isRedefinitionOf(redefinedOperation, operation2)) {
						return true;
					}
				}
			}
			return false;
		}
	}

	private static final class MergedPackageDisambiguator extends Disambiguator<org.eclipse.ocl.pivot.Package>
	{
		@Override
		public int compare(@NonNull EnvironmentFactory environmentFactory, @NonNull org.eclipse.ocl.pivot.Package match1, @NonNull org.eclipse.ocl.pivot.Package match2) {
			CompleteModel completeModel = environmentFactory.getCompleteModel();
			CompletePackage completePackage1 = completeModel.getCompletePackage(match1);
			CompletePackage completePackage2 = completeModel.getCompletePackage(match2);
			if (completePackage1 == completePackage2) {
				return 1;				// match2 inferior			
			}
			return 0;
		}
	}

	private static final class PropertyDisambiguator extends Disambiguator<Property>
	{
		@Override
		public int compare(@NonNull EnvironmentFactory environmentFactory, @NonNull Property match1, @NonNull Property match2) {
			if (isRedefinitionOf(match1, match2)) {
				return 1;				// match2 inferior			
			}
			if (isRedefinitionOf(match2, match1)) {
				return -1;				// match1 inferior			
			}
			return 0;
		}

		protected boolean isRedefinitionOf(@NonNull Property property1, @NonNull Property property2) {
			List<Property> redefinedProperties = property1.getRedefinedProperties();
			for (Property redefinedProperty : redefinedProperties) {
				if (redefinedProperty != null) {
					if (redefinedProperty == property2) {
						return true;
					}
					if (isRedefinitionOf(redefinedProperty, property2)) {
						return true;
					}
				}
			}
			return false;
		}
	}
			
	private static @NonNull LinkedHashMap<Class<?>, List<Comparator<Object>>> disambiguatorMap =	// FIXME narrow API to Disambiguator
			new LinkedHashMap<Class<?>, List<Comparator<Object>>>();

	static {
		addDisambiguator(Object.class, new ImplicitDisambiguator());
		addDisambiguator(Feature.class, new MetamodelMergeDisambiguator());
		addDisambiguator(Operation.class, new OperationDisambiguator());
		addDisambiguator(org.eclipse.ocl.pivot.Package.class, new MergedPackageDisambiguator());
		addDisambiguator(Property.class, new PropertyDisambiguator());
	}
	
	public static synchronized <T> void addDisambiguator(@NonNull Class<T> targetClass, @NonNull Comparator<T> disambiguator) {
		List<Comparator<Object>> disambiguators = disambiguatorMap.get(targetClass);
		if (disambiguators == null) {
			disambiguators = new ArrayList<Comparator<Object>>();
			disambiguatorMap.put(targetClass, disambiguators);
		}
		@SuppressWarnings("unchecked")
		Comparator<Object> castDisambiguator = (Comparator<Object>) disambiguator;
		disambiguators.add(castDisambiguator);
	}

	@SuppressWarnings("null")
	public static @NonNull Iterable<Class<?>> getDisambiguatorKeys() {
		return disambiguatorMap.keySet();
	}

	public static @Nullable List<Comparator<Object>> getDisambiguators(@NonNull Class<?> key) {
		return disambiguatorMap.get(key);
	}
	
	
	protected final @NonNull EnvironmentFactory envFactory;
	private List<ScopeFilter> matchers = null;	// Prevailing filters for matching
	private Set<ScopeFilter> resolvers = null;	// Successful filters for resolving
	
	public void addFilter(@NonNull ScopeFilter filter) {
		if (matchers == null) {
			matchers = new ArrayList<ScopeFilter>();
		}
		matchers.add(filter);
	}
	
	public void removeFilter(@NonNull ScopeFilter filter) {
		if (matchers != null) {
			matchers.remove(filter);
		}
	}
	
	@NonNull
	public SingleResultLookupEnvironment resolveDuplicates() {
		if (elements.size() > 1)  {
			// FIXME this WAS done while "adding" elements. Adding here where they are supposed
			// to be used. Talk with ED about this.
			if (matchers != null) {
				if (resolvers == null) {
					resolvers = new HashSet<ScopeFilter>();
				}
				resolvers.addAll(matchers);
			}
			for (int i = 0; i < elements.size()-1;) {
				boolean iRemoved = false;
				@SuppressWarnings("null") @NonNull Object iValue = elements.get(i);
				for (int j = i + 1; j < elements.size();) {
					Class<?> iClass = iValue.getClass();
					@SuppressWarnings("null") @NonNull Object jValue = elements.get(j);
					Class<?> jClass = jValue.getClass();
					int verdict = 0;
					for (Class<?> key : disambiguatorMap.keySet()) {
						if (key.isAssignableFrom(iClass) && key.isAssignableFrom(jClass)) {
							for (Comparator<Object> comparator : disambiguatorMap.get(key)) {
								if (comparator instanceof Disambiguator<?>) {
									verdict = ((Disambiguator<Object>)comparator).compare(envFactory, iValue, jValue);
								} else {
									verdict = comparator.compare(iValue, jValue);
								}
								if (verdict != 0) {
									break;
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
						elements.remove(i);
						iRemoved = true;
						break;
					} else {
						elements.remove(j);
					}
				}
				if (!iRemoved) {
					i++;
				}				
			}
		}
		return this;
	}
*/
}
