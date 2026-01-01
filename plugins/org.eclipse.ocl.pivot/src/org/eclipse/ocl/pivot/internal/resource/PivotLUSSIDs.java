/*******************************************************************************
 * Copyright (c) 2017, 2025 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.resource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.CollectionLiteralPart;
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.DataType;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.EnumerationLiteral;
import org.eclipse.ocl.pivot.Feature;
import org.eclipse.ocl.pivot.IterableType;
import org.eclipse.ocl.pivot.Iteration;
import org.eclipse.ocl.pivot.LambdaParameter;
import org.eclipse.ocl.pivot.LambdaType;
import org.eclipse.ocl.pivot.Library;
import org.eclipse.ocl.pivot.MapLiteralPart;
import org.eclipse.ocl.pivot.MapType;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.NormalizedTemplateParameter;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Parameter;
import org.eclipse.ocl.pivot.Precedence;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.ShadowPart;
import org.eclipse.ocl.pivot.TemplateArgument;
import org.eclipse.ocl.pivot.TemplateParameter;
import org.eclipse.ocl.pivot.TemplateableElement;
import org.eclipse.ocl.pivot.TupleLiteralPart;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.WildcardType;
import org.eclipse.ocl.pivot.internal.manager.Orphanage;
import org.eclipse.ocl.pivot.oclstdlib.OCLstdlibTables;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.values.Unlimited;

import com.google.common.collect.Iterables;

/**
 * The LUSSIDs class maintains the element to LUSSID and LUSSID to element mapping for the elements
 * of an ASResource. It also privides the ability to return predictable xmi:id values.
 *
 * An xmi:id is provided for every explicitly referenced, and every potentially externally referenced element,
 * so that the fall-back the @x/@y.1 style id referemces is never required.
 *
 * The xmi:id typically comprises a 5 Base64-like letter encoding of the bottom 30 bits of the LUSSID of the element.
 * Additional Base64 letters are occasionally needed to avoid duplicates.
 *
 * The LUSSID (Locally Unique Semantically Sentsitive ID) is the hashcode of the hierarchical path of the element.
 * The resource location, model name and external URI are ignored avoiding dependence on location and URI.
 * Elements within ordered collections use the index, but elements within unordered collections use a further
 * local LUSSID that captures the name / template bindings / parameter names / collection bounds so that LUSSID
 * have substantial tolerance to insignaicant reordering of elements.

 * @since 1.4
 */
public class PivotLUSSIDs extends LUSSIDs
{
	private org.eclipse.ocl.pivot.Package typeOrphanage = null;

	public PivotLUSSIDs(@NonNull ASResource asResource, @NonNull Map<@NonNull Object, @Nullable Object> options) {
		super(asResource, options);
		for (EObject eRoot : asResource.getContents()) {
			if (eRoot instanceof Model) {
				for (org.eclipse.ocl.pivot.@NonNull Package asPackage : PivotUtil.getOwnedPackages((Model)eRoot)) {
					if (Orphanage.isOrphanage(asPackage)) {
						typeOrphanage = asPackage;
						break;
					}
				}
				break;
			}
		}
	}

	/**
	 * Return the hash of the aspects of element that distinguish it from its siblings.
	 * Return null if there are no distinguishing aspects.
	 */
	@Override
	protected @Nullable Integer computeLocalLUSSID(@NonNull AS2ID as2id, @NonNull EObject element, @Nullable TemplateableElement wildcardContext) {
		assert asResource == element.eResource();
		int localId = 0;
		if (!(element instanceof NamedElement)) {
			return null;
		}
		if ((element instanceof DataType) && !(element instanceof IterableType) && (((DataType)element).getOwnedTemplateArguments().size() > 0)) {
			String s = element.toString();
			getClass();				// XXX
		}
		if (element instanceof WildcardType) {		// XXX Oops bad TemplateableElement inheritance
			WildcardType wildcard = (WildcardType)element;
		//	Integer contextLUSSID = computeLocalLUSSID(as2id, wildcard.getOwningTemplateableElement(), true);
		//	assert contextLUSSID != null;
			localId = /*contextLUSSID.intValue() +*/ WILDCARD_TYPE_MULTIPLIER * (1 + wildcard.getIndex());
			return localId;
		}
		String name = ((NamedElement)element).getName();
		if (name == null) {
			return null;
		}
		if ((element instanceof Library) && name.equals(OCLstdlibTables.PACKAGE.getName())) {
			name = "ocl";					// XXX transitional fudge while migrating to just xxxTables
		}
		localId += name.hashCode();
		if (element instanceof TemplateableElement) {
			int templateIndexMultiplier = TEMPLATE_BINDING_MULTIPLIER;
			List<@NonNull TemplateArgument> asTemplateArguments = ((TemplateableElement)element).basicGetOwnedTemplateArguments();
			if (asTemplateArguments != null) {
				for (@NonNull TemplateArgument templateArgument :  asTemplateArguments) {
					Element actual = templateArgument.getActual();
					/*if (actual instanceof WildcardType) {
						localId += templateIndexMultiplier;
					}
					else*/ if (actual instanceof Type) {
						localId += templateIndexMultiplier * computeReferenceLUSSID(as2id, (Type) actual, wildcardContext);
					}
					else if (actual != null) {
						localId += templateIndexMultiplier * as2id.assignLUSSID(actual, false, wildcardContext);
					}
					templateIndexMultiplier += 2 * TEMPLATE_BINDING_MULTIPLIER;
				}
			}
			if (element instanceof CollectionType) {
				CollectionType collectionType = (CollectionType)element;
				if (!collectionType.isIsNullFree()) {
					localId += COLLECTION_IS_NULL_FREE_MULTIPLIER;
				}
				int lowerValue = collectionType.getLower().intValue();
				if (lowerValue != 0) {
					localId += COLLECTION_LOWER_BOUND_MULTIPLIER * lowerValue;
				}
				Number upper = collectionType.getUpper();
				if (!(upper instanceof Unlimited)) {
					localId += COLLECTION_UPPER_BOUND_MULTIPLIER * (upper.intValue() + 1);
				}
			}
			else if (element instanceof MapType) {
				MapType mapType = (MapType)element;
				if (!mapType.isKeysAreNullFree()) {
					localId += MAP_KEYS_ARE_NULL_FREE_MULTIPLIER;
				}
				if (!mapType.isValuesAreNullFree()) {
					localId += MAP_VALUES_ARE_NULL_FREE_MULTIPLIER;
				}
				Type entryClass = mapType.getEntryClass();
				if (entryClass != null) {
					localId += templateIndexMultiplier * computeReferenceLUSSID(as2id, entryClass, wildcardContext);
				}
			}
			else if (element instanceof LambdaType) {
				LambdaType lambdaType = (LambdaType)element;
				List<@NonNull Parameter> lambdaParameters = new ArrayList<>();
				lambdaParameters.add(PivotUtil.getOwnedContext(lambdaType));
				lambdaParameters.add(PivotUtil.getOwnedResult(lambdaType));
				lambdaParameters.addAll(PivotUtil.getOwnedParametersList(lambdaType));
				localId += computeParametersLUSSID(as2id, lambdaParameters, wildcardContext);
			}
			else if (element instanceof Iteration) {
				Iteration iteration = (Iteration)element;
				Iterable<@NonNull Parameter> asIterators = PivotUtil.getOwnedIterators(iteration);
				Iterable<@NonNull Parameter> parameters;
				Parameter asAccumulator = iteration.getOwnedAccumulator();
				if (asAccumulator == null) {
					parameters = asIterators;
				}
				else {
					parameters = Iterables.concat(asIterators, Collections.singletonList(asAccumulator));
					assert parameters != null;
				}
				localId += computeParametersLUSSID(as2id, parameters, iteration);
			}
			else if (element instanceof Operation) {
				Operation operation = (Operation)element;
				localId += computeParametersLUSSID(as2id, PivotUtil.getOwnedParameters(operation), operation);
			}
		}
		else if (element instanceof Property) {
			Property property = (Property)element;
			if (property.isIsImplicit()) {
				Property oppositeProperty = property.getOpposite();
				if (oppositeProperty != null) {
					String oppositeName = oppositeProperty.getName();
					if (oppositeName != null) {
						localId += OPPOSITE_PROPERTY_NAME_MULTIPLIER * oppositeName.hashCode();
					}
				}
				else {				// Never happens
					System.out.println("No opposite for " + element);
				}
			}
		}
		else if (element instanceof Parameter) {
			Parameter parameter = (Parameter)element;
			Type type = parameter.getType();
			if (type != null) {
				TemplateableElement nestedWildcardContext = getNestedWildcardContext(parameter, wildcardContext);
				localId += PARAMETER_TYPE_MULTIPLIER * computeReferenceLUSSID(as2id, type, nestedWildcardContext);
			}
		}
		return Integer.valueOf(localId);
	}

	/**
	 * @since 7.0
	 */
	protected int computeParametersLUSSID(@NonNull AS2ID as2id, @NonNull Iterable<@NonNull ? extends Parameter> parameters, @Nullable TemplateableElement wildcardContext) {
		int parametersLUSSID = 0;
		int parameterIndex = 1;
		for (@NonNull Parameter parameter :  parameters) {
			if (parameter instanceof WildcardType) {
				getClass();		// XXX
			}
			int index = -1;
			Type parameterType = parameter.getType();
			assert (parameterType == null) || (parameterType instanceof NormalizedTemplateParameter) || (parameterType.eResource() != null);
			if (parameterType instanceof TemplateParameter) {
				TemplateableElement templateableElement = ((TemplateParameter)parameterType).getOwningTemplateableElement();
				List<@NonNull TemplateParameter> templateParameters = PivotUtil.getOwnedTemplateParametersList(templateableElement);
				index = templateParameters.indexOf(parameterType);
			}
			if (parameter instanceof LambdaParameter) {
				parametersLUSSID += parameterIndex * LAMBDA_PARAMETER_NAME_MULTIPLIER * parameter.getName().hashCode();
			}
			if (!parameter.isIsRequired()) {
				parametersLUSSID += parameterIndex * PARAMETER_IS_OPTIONAL_MULTIPLIER;
			}
			if (index >= 0) {
				parametersLUSSID += parameterIndex * TEMPLATE_PARAMETER_INDEX_MULTIPLIER * (index + 1);
			}
			else if (parameterType != null) {
				TemplateableElement nestedWildcardContext = getNestedWildcardContext(parameter, wildcardContext);
				parametersLUSSID += parameterIndex * OPERATION_PARAMETER_TYPE_MULTIPLIER * computeReferenceLUSSID(as2id, parameterType, nestedWildcardContext);
			}
			parameterIndex++;
		}
		return parametersLUSSID;
	}

	/**
	 * @since 7.0
	 */
	protected int computeReferenceLUSSID(@NonNull AS2ID as2id, @NonNull Type type, @Nullable TemplateableElement wildcardContext) {
		if (wildcardContext != null) {
			boolean gotIt = false;
			int index = 0;
			if (type instanceof TemplateParameter) {
				for (EObject eContainer = type.eContainer(); eContainer != null; eContainer = eContainer.eContainer()) {
					if (eContainer instanceof TemplateableElement) {
						List<@NonNull TemplateParameter> asTemplateParameters = ((TemplateableElement)eContainer).basicGetOwnedTemplateParameters();
						if (asTemplateParameters != null) {
							int localIndex = asTemplateParameters.indexOf(type);
							if (localIndex >= 0) {
								index += localIndex;
								gotIt = true;
							}
							else {
								index += asTemplateParameters.size();
							}
						}
					}
				}
			}
			else if (type instanceof WildcardType) {
				WildcardType wildcard = (WildcardType)type;
				if (wildcard.eContainer() == wildcardContext) {
					index += -(1 + wildcard.getIndex());
					gotIt = true;
				}
			}
			if (gotIt) {
				return TEMPLATE_PARAMETER_INDEX_MULTIPLIER * index;
			}
		}
	//	TemplateableElement nestedWildcardContext = getNestedWildcardContext(parameter, wildcardContext);
		return as2id.assignLUSSID(type, false, wildcardContext);
	}

	/**
	 * @since 7.0
	 */
	protected @Nullable TemplateableElement getNestedWildcardContext(@NonNull Parameter parameter, @Nullable TemplateableElement wildcardContext) {
		EObject eContainer = parameter.eContainer();
		if (eContainer instanceof TemplateableElement) {			// Operation / Iteration
			return (TemplateableElement) eContainer;
		}
		else {
			return wildcardContext;
		}
	}

	@Override
	protected boolean isExternallyReferenceable(@NonNull EObject eObject) {
//		if (eObject instanceof WildcardType) {
//			return false;
//		}
		if (eObject instanceof Type) {				// Class, TemplateParameter
		//	if ((typeOrphanage == null) || (eObject.eContainer() != typeOrphanage)) {
				return true;
		//	}
		}
		else if (eObject instanceof org.eclipse.ocl.pivot.Package) {		// Profile
			if (eObject != typeOrphanage) {
				return true;
			}
		}
		else if (eObject instanceof Feature) {		// Iteration, Operation, Property
		//	if ((featureOrphanage == null) || (eObject.eContainer() != featureOrphanage)) {
				return true;
		//	}
		}
		else if (eObject instanceof CollectionLiteralPart) {
			return true;
		}
		else if (eObject instanceof Constraint) {
			return true;
		}
		else if (eObject instanceof EnumerationLiteral) {
			return true;
		}
		else if (eObject instanceof MapLiteralPart) {
			return true;
		}
		else if (eObject instanceof Model) {
			return true;
		}
		else if ((eObject instanceof Parameter) && !(eObject instanceof LambdaParameter)) {
			return true;
		}
		else if (eObject instanceof Precedence) {
			return true;
		}
		else if (eObject instanceof ShadowPart) {
			return true;
		}
		else if (eObject instanceof TupleLiteralPart) {
			return true;
		}
		return false;
	}
}