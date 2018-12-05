/*******************************************************************************
 * Copyright (c) 2012, 2018 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.internal.evaluation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.ocl.Environment;
import org.eclipse.ocl.TypeChecker;
import org.eclipse.ocl.utilities.TypedElement;
import org.eclipse.ocl.utilities.UMLReflection;

public class CachedTypeChecker<C, O, P, PM> extends BasicTypeChecker<C, O, P, PM> implements TypeChecker.Cached<C, O, P>
{
	private boolean bypass = false;
	
	private final UMLReflection<?, C, O, P, ?, PM, ?, ?, ?, ?> uml;

	/**
	 * Lazily created maps of operation name to an operation or a list of operations for each type.
	 */
	private final Map<C, Map<String, Object>> type2name2operationOrOperations = new HashMap<C, Map<String, Object>>();

	/**
	 * Lazily created maps of static operation to dynamic operation for each dispatch type.
	 */
	private final Map<C, Map<O, O>> type2static2dynamic = new HashMap<C, Map<O, O>>();

	/**
	 * Lazily created maps of property name to a property for each type.
	 */
	private final Map<C, Map<String, P>> type2name2property = new HashMap<C, Map<String, P>>();

	public CachedTypeChecker(Environment<?, C, O, P, ?, PM, ?, ?, ?, ?, ?, ?> environment) {
		super(environment);
		this.uml = environment.getUMLReflection();
	}

	/**
	 * Return true if requiredParameters is an exact match for candidateParameters.
	 */
	protected boolean exactlyMatches(List<? extends PM> requiredParameters, List<? extends PM> candidateParameters) {
    	int iMax = requiredParameters.size();
    	if (iMax != candidateParameters.size()) {
    		return false;
    	}
		for (int i = 0; i < iMax; i++) {
			PM requiredParameter = requiredParameters.get(i);
			PM candidateParameter = candidateParameters.get(i);
			C requiredType = uml.getOCLType(requiredParameter);
			C candidateType = uml.getOCLType(candidateParameter);
			if (requiredType != candidateType) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public P findAttribute(C owner, String name) {
		if (bypass) {
			return super.findAttribute(owner, name);
		}
    	Map<String, P> name2property = getName2Property(owner);
       	return name2property.get(name);
	}

	@Override
	protected List<O> getBestMatchingOperations(C owner, String name, List<? extends TypedElement<C>> args) {
		if (bypass) {
			return super.getBestMatchingOperations(owner, name, args);
		}
		Map<String, Object> name2operationOrOperations = getName2OperationOrOperations(owner);
		Object candidateOperationOrOperations = name2operationOrOperations.get(name);
		if (candidateOperationOrOperations instanceof List<?>) {
			List<O> matches = null;
        	@SuppressWarnings("unchecked")
			List<O> candidateOperations = (List<O>) candidateOperationOrOperations;
			int bestExactitude = 0;
			for (O oper : candidateOperations) {
				int exactitude = matchArgsWithExactitude(owner, uml.getParameters(oper), args);
				if (exactitude >= bestExactitude) {
					if (exactitude > bestExactitude) {
						if (matches != null) {
							matches.clear();
						}
						bestExactitude = exactitude;
					}
					if (matches == null) {
						// assume a small number of redefinitions
						matches = new java.util.ArrayList<O>(3);
					}
	
					matches.add(oper);
				}
			}
			return matches;
		}
		else if (candidateOperationOrOperations != null) {
    		@SuppressWarnings("unchecked")
			O candidateOperation = (O) candidateOperationOrOperations;
			int exactitude = matchArgsWithExactitude(owner, uml.getParameters(candidateOperation), args);
			if (exactitude >= 0) {
				return Collections.singletonList(candidateOperation);
			}
			else {
				return null;
			}
		}
		else {
			return null;
		}
	}
	
	/**
	 * Return the operation of dynamicType that exactly match the staticOperation, or null
	 * if there is none or more than one.
	 */
    public O getDynamicOperation(C dynamicType, O staticOperation) {
    	//
    	//	Use class2dynamic2static to return known dynamic operation for static operation on a source type. 
    	//
		Map<O, O> typeOperations = type2static2dynamic.get(dynamicType);
		if (typeOperations == null) {
			typeOperations = new HashMap<O, O>();
			type2static2dynamic.put(dynamicType, typeOperations);
		}
		else {
			O dynamicOperation = typeOperations.get(staticOperation);
			if (dynamicOperation != null) {
				return dynamicOperation;
			}
			if (typeOperations.containsKey(staticOperation)) {
				return null;		// Cached miss or ambiguity
			}
		}
		//
		//	Prepare the type-specific name2operationOrOperations lazily.
		//
    	Map<String, Object> name2operationOrOperations = getName2OperationOrOperations(dynamicType);
    	//
    	//	Pick and cache the matching unambiguous overload.
    	//
       	O dynamicOperation = getExactMatchingOperation(name2operationOrOperations, staticOperation);
		typeOperations.put(staticOperation, dynamicOperation);
		return dynamicOperation;
    }

    /**
     * Return the operation from the name2operationOrOperations cache that matches operation.
     * Returns null if there is no operation or more than one.
     * 
     * @param name2operationOrOperations
     * @param operation
     */
	protected O getExactMatchingOperation(Map<String, Object> name2operationOrOperations, O operation) {
		String requiredName = uml.getName(operation);
    	List<PM> requiredParameters = uml.getParameters(operation);
    	Object candidateOperationOrOperations = name2operationOrOperations.get(requiredName);
    	if (candidateOperationOrOperations instanceof List<?>) {
    		O matchingOperation = null;
        	@SuppressWarnings("unchecked")
			List<O> candidateOperations = (List<O>) candidateOperationOrOperations;
			for (O candidateOperation : candidateOperations) {
	        	List<PM> candidateParameters = uml.getParameters(candidateOperation);
	        	if (exactlyMatches(requiredParameters, candidateParameters)) {
	        		if (matchingOperation == null) {
	        			matchingOperation = candidateOperation;
	        		}
	        		else {
	        			return null;	// Ambiguity detected
	        		}
	        	}
	    	}
			return matchingOperation;
    	}
    	else if (candidateOperationOrOperations != null) {
    		@SuppressWarnings("unchecked")
			O candidateOperation = (O) candidateOperationOrOperations;
        	List<PM> candidateParameters = uml.getParameters(candidateOperation);
        	if (exactlyMatches(requiredParameters, candidateParameters)) {
        		return candidateOperation;
        	}
        	else {
        		return null;
        	}
    	}
    	else {
    		return null;
    	}
	}

    /**
     * Return the map of name to operator or list of operations for a type.
     */
	protected Map<String, Object> getName2OperationOrOperations(C type) {
		Map<String, Object> name2operationOrOperations = type2name2operationOrOperations.get(type);
    	if (name2operationOrOperations == null) {
    		name2operationOrOperations = new HashMap<String, Object>();
    		type2name2operationOrOperations.put(type, name2operationOrOperations);
        	List<O> allOperations = getOperations(type);
        	for (O candidateOperation : allOperations) {
        		String name = uml.getName(candidateOperation);
        		Object overloadOrOverloads = name2operationOrOperations.get(name);
               	if (overloadOrOverloads == null) {
               		name2operationOrOperations.put(name, candidateOperation);
               	}
               	else {
               		List<O> overloads;
                   	if (overloadOrOverloads instanceof List<?>) {
                   		@SuppressWarnings("unchecked")
						List<O> castOperations = (List<O>) overloadOrOverloads;
						overloads = castOperations;
                   	}
                   	else {
                   		overloads = new ArrayList<O>();
                   		name2operationOrOperations.put(name, overloads);
						@SuppressWarnings("unchecked")
						O castOperation = (O)overloadOrOverloads;
						overloads.add(castOperation);
                   	}
	        		C candidateOwner = uml.getOwningClassifier(candidateOperation);
	        		Collection<? extends C> candidateSupertypes = uml.getAllSupertypes(candidateOwner);
    	        	List<PM> candidateParameters = uml.getParameters(candidateOperation);
    		    	int iMax = candidateParameters.size();
    		    	int j = overloads.size();
                   	while (--j >= 0) {								// Reverse count to allow remove()
                   		O oldOperation = overloads.get(j);
        	        	List<PM> oldParameters = uml.getParameters(oldOperation);
        	        	if (iMax == oldParameters.size()) {
        	        		int i = 0;
        	        		for ( ; i < iMax; i++) {
        	        			PM candidateParameter = candidateParameters.get(i);
        	        			PM oldParameter = oldParameters.get(i);
        						C oldType = uml.getOCLType(oldParameter);
        						C candidateType = uml.getOCLType(candidateParameter);
        	        			if (oldType != candidateType) {
        	        				break;
        	        			}
        	        		}
        	        		if (i >= iMax) {
           	        			C oldOwner = uml.getOwningClassifier(oldOperation);
           	        			if (candidateSupertypes.contains(oldOwner)) {
           	        				overloads.remove(j);
           	        			}
           	        			else {
           	        				Collection<? extends C> oldSupertypes = uml.getAllSupertypes(oldOwner);
               	        			if (oldSupertypes.contains(candidateOwner)) {
               	        				break;
               	        			}
           	        			}
        	        		}
        	        	}
                   	}
                   	if (j < 0) {
                   		overloads.add(candidateOperation);
                   	}
               	}
        	}
    	}
		return name2operationOrOperations;
	}

    /**
     * Return the map of name to property for a type.
     */
	protected Map<String, P> getName2Property(C type) {
		Map<String, P> name2property = type2name2property.get(type);
    	if (name2property == null) {
    		name2property = new HashMap<String, P>();
    		type2name2property.put(type, name2property);
        	List<P> allProperties = getAttributes(type);
        	for (P candidateProperty : allProperties) {
        		String name = uml.getName(candidateProperty);
        		P oldProperty = name2property.get(name);
               	if (oldProperty == null) {
               		name2property.put(name, candidateProperty);
               	}
               	else {
	        		C candidateOwner = uml.getOwningClassifier(candidateProperty);
	        		Collection<? extends C> candidateSupertypes = uml.getAllSupertypes(candidateOwner);
   	        		C oldOwner = uml.getOwningClassifier(oldProperty);
   	        		if (candidateSupertypes.contains(oldOwner)) {
   	               		name2property.put(name, candidateProperty);
   	        		}
   	        		else {
   	        			Collection<? extends C> oldSupertypes = uml.getAllSupertypes(oldOwner);
       	        		if (!oldSupertypes.contains(candidateOwner)) {
       	        			; // This should not happen
   	        			}
                   	}
               	}
        	}
    	}
		return name2property;
	}

	public void reset() {
		type2name2operationOrOperations.clear();
		type2static2dynamic.clear();
		type2name2property.clear();
	}

	public void setBypass(boolean bypass) {
		reset();
		this.bypass = bypass;
	}
}
