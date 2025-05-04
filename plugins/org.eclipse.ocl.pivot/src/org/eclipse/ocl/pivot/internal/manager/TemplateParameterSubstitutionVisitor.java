/*******************************************************************************
 * Copyright (c) 2013, 2022 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D. Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.CallExp;
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.Feature;
import org.eclipse.ocl.pivot.IterateExp;
import org.eclipse.ocl.pivot.Iteration;
import org.eclipse.ocl.pivot.IteratorExp;
import org.eclipse.ocl.pivot.LambdaType;
import org.eclipse.ocl.pivot.MapType;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.NavigationCallExp;
import org.eclipse.ocl.pivot.NormalizedTemplateParameter;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.OppositePropertyCallExp;
import org.eclipse.ocl.pivot.Parameter;
import org.eclipse.ocl.pivot.PrimitiveType;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.PropertyCallExp;
import org.eclipse.ocl.pivot.SelfType;
import org.eclipse.ocl.pivot.TemplateParameter;
import org.eclipse.ocl.pivot.TemplateParameterSubstitution;
import org.eclipse.ocl.pivot.TemplateSignature;
import org.eclipse.ocl.pivot.TemplateableElement;
import org.eclipse.ocl.pivot.TupleType;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.ids.IdManager;
import org.eclipse.ocl.pivot.ids.IdResolver;
import org.eclipse.ocl.pivot.ids.TuplePartId;
import org.eclipse.ocl.pivot.ids.TupleTypeId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.pivot.library.LibraryIterationOrOperation;
import org.eclipse.ocl.pivot.util.AbstractExtendingVisitor;
import org.eclipse.ocl.pivot.util.Visitable;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.values.TemplateParameterSubstitutions;

/**
 * A TemplateParameterSubstitutionVisitor supports synthesis of TemplateParameterSubstitutions by visting AS elemenmts that
 * have a partial parameterization and a partial specialization to establish the total specialization.
 *
 * Construction using create() analyzses the passed Callxp.
 *
 * Construction using createVisitor() defers analysis via calls to analyzeType() to the caller.
 *
 * Thereafter specializeType() may be used to import the specialization on a partially/un-specialized type.
 * <p>
 * The visitor should be constructed with an EnvironmentFactory in case any synthetic types need constructing, and the identity
 * of the self type in case one of the substitutions uses OclSelf.
 */
public /*abstract*/ class TemplateParameterSubstitutionVisitor extends AbstractExtendingVisitor<Object, Object> implements TemplateParameterSubstitutions
{
	/**
	 * Create a TemplateParameterSubstitutionVisitor suitable for specializing types with respect to the parameter
	 * substitutions present in the actualExp, with selfType as the optional kn own type of OclSelf.
	 *
	 * @since 1.23
	 */
	public static @NonNull TemplateParameterSubstitutionVisitor create(@NonNull EnvironmentFactoryInternal environmentFactory,
				@NonNull CallExp actualExp, @Nullable Type selfType) {
		Element referredElement;
		 if (actualExp instanceof NavigationCallExp) {
			referredElement = PivotUtil.getReferredProperty((NavigationCallExp)actualExp);
		}
		else {
			referredElement = PivotUtil.getReferredOperation(actualExp);
		}
		TemplateParameterSubstitutionVisitor visitor = createVisitor(referredElement, environmentFactory, selfType, null);
		if (visitor == null) {
			visitor = environmentFactory.createTemplateParameterSubstitutionVisitor(selfType, null);
		}
		else {
			visitor.exclude(actualExp);
			visitor.visit(actualExp);
		}
		return visitor;
	}

	@Deprecated /* @deprecated no longer used */
	public static @NonNull TemplateParameterSubstitutions createBindings(@NonNull EnvironmentFactoryInternal environmentFactory, @NonNull Type formalType, @NonNull Type actualType) {
		TemplateParameterSubstitutionVisitor visitor = createVisitor(actualType, environmentFactory, null, null);
		if (visitor == null) {
			return TemplateParameterSubstitutions.EMPTY;
		}
		visitor.analyzeType(formalType, actualType);
		return visitor;
	}

	@Deprecated /* @deprecated no longer used */
	public static @NonNull TemplateParameterSubstitutions createBindings(@NonNull EnvironmentFactoryInternal environmentFactory, @Nullable Type sourceType, @Nullable Type zzsourceTypeValue, @NonNull Operation candidateOperation) {
		// assert sourceTypeValue == null;			// Bug 580791  Enforcing redundant argument
		if (!hasTemplateParameters(candidateOperation)) {
			return TemplateParameterSubstitutions.EMPTY;
		}
		TemplateParameterSubstitutionVisitor visitor = createVisitor(candidateOperation, environmentFactory, sourceType, null);
		if (visitor == null) {
			return TemplateParameterSubstitutions.EMPTY;
		}
		visitor.analyzeType(candidateOperation.getOwningClass(), sourceType);
		return visitor;
	}

	/**
	 * @since 1.23
	 */
	public static @Nullable TemplateParameterSubstitutionVisitor createVisitor(@NonNull EObject eObject, @NonNull EnvironmentFactoryInternal environmentFactory, @Nullable Type selfType, @Nullable Type selfTypeValue) {
		BasicTemplateSpecialization templateSpecialization = BasicTemplateSpecialization.basicGetTemplateSpecialization((Element)eObject);
		if (templateSpecialization == null) {
			return null;
		}
		TemplateParameterSubstitutionVisitor visitor = environmentFactory.createTemplateParameterSubstitutionVisitor(selfType, null);
		visitor.setTemplateSpecialization(templateSpecialization);
		return visitor;
	}

	/**
	 * Return true if asElement has template parameters.
	 *
	 * @since 1.23
	 */
	public static boolean hasTemplateParameters(@NonNull Element asElement) {
		boolean hasTemplateParameters = false;
		for (EObject eObject = asElement; eObject != null; eObject = eObject.eContainer()) {
			if (eObject instanceof TemplateableElement) {
				TemplateSignature templateSignature = ((TemplateableElement)eObject).getOwnedSignature();
				if (templateSignature != null) {
					List<TemplateParameter> templateParameters = templateSignature.getOwnedParameters();
					int iSize = templateParameters.size();
					if (iSize > 0) {
						hasTemplateParameters = true;
						break;
					}
				}
			}
		}
		return hasTemplateParameters;
	}

	/**
	 * Return true if a referencedType needs specialization to resolve a template parameter.
	 *
	 * @since 1.23
	 */
	public static boolean needsSpecialization(@Nullable Type referencedType)
	{
		if (referencedType == null) {
			return true;
		}
		TemplateParameter templateParameter = referencedType.isTemplateParameter();
		if (templateParameter != null) {
			return true;
		}
		if (referencedType instanceof CollectionType) {
			Type elementType = ((CollectionType)referencedType).getElementType();
			return needsSpecialization(elementType);
		}
		if (referencedType instanceof TupleType) {
			TupleType tupleType = (TupleType)referencedType;
			for (Property tuplePart : tupleType.getOwnedProperties()) {
				Type tuplePartType = tuplePart.getType();
				if (needsSpecialization(tuplePartType)) {
					return true;
				}
			}
			return false;
		}
		if (referencedType instanceof LambdaType) {
			LambdaType lambdaType = (LambdaType)referencedType;
			Type contextType = lambdaType.getContextType();
			if (needsSpecialization(contextType)) {
				return true;
			}
			Type resultType = lambdaType.getResultType();
			if (needsSpecialization(resultType)) {
				return true;
			}
			for (Type parameterType : lambdaType.getParameterTypes()) {
				if (needsSpecialization(parameterType)) {
					return true;
				}
			}
			return false;
		}
		if (referencedType instanceof org.eclipse.ocl.pivot.Class) {
			TemplateSignature templateSignature = ((org.eclipse.ocl.pivot.Class)referencedType).getOwnedSignature();
			if (templateSignature != null) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Return the specialized form of type analyzing actualExp to determine the formal to actual parameter mappings under the
	 * supervision of a metamodelManager and using selfType as the value of OclSelf.
	 *
	 * @since 1.23
	 */
	public static @NonNull Type specializeType(@NonNull Type type, @NonNull CallExp actualExp, @NonNull EnvironmentFactoryInternal environmentFactory, @Nullable Type selfType, @Nullable Type selfTypeValue) {
		// assert selfTypeValue == null;			// Bug 580791 Enforcing redundant argument
		TemplateParameterSubstitutionVisitor visitor = create(environmentFactory, actualExp, selfType);
		return visitor.specializeType(type);
	}

	/**
	 * @since 1.23
	 */
	public static org.eclipse.ocl.pivot.@NonNull Class specializeTypeToLowerBound(org.eclipse.ocl.pivot.@NonNull Class type, @NonNull EnvironmentFactoryInternal environmentFactory) {
		TemplateParameterSubstitutionVisitor visitor = createVisitor(type, environmentFactory, null, null);
		if (visitor == null) {
			return type;
		}
		TemplateParameterization templateParameterization = visitor.getTemplateParameterization();
		for (@NonNull TemplateParameter templateParameter : templateParameterization) {
			org.eclipse.ocl.pivot.Class lowerBound = PivotUtil.basicGetLowerBound(templateParameter);
			if (lowerBound == null) {
				lowerBound = environmentFactory.getStandardLibrary().getOclAnyType();
			}
			visitor.put(templateParameter, lowerBound);
		}
		return (org.eclipse.ocl.pivot.Class) visitor.specializeType(type);
	}

	private final @NonNull EnvironmentFactoryInternal environmentFactory;
	private final @Nullable Type selfType;
	private @Nullable NamedElement excludedTarget = null;

	/**
	 * Internal variable used to pass the actual corresponding to the visited formal.
	 */
	private Type actual;

	private @Nullable BasicTemplateSpecialization templateSpecialization = null;		// XXX move to constructor

	public /*protected*/ TemplateParameterSubstitutionVisitor(@NonNull EnvironmentFactoryInternal environmentFactory, @Nullable Type selfType, @Nullable Type selfTypeValue) {
		super(null);
		this.environmentFactory = environmentFactory;
		this.selfType = selfType;
		// assert selfTypeValue == null;			// Bug 580791 Enforcing redundant argument
	}

	@Deprecated /* @deprecated no longer used */
	protected void analyzeFeature(@Nullable Feature formalFeature, @Nullable TypedElement actualElement) {
		if ((formalFeature != null) && (actualElement != null)) {
			Type formalType = formalFeature.getOwningClass();
			Type actualType = actualElement.getType();
			analyzeType(formalType, actualType);
		}
	}

	@Deprecated /* @deprecated argument should be Type */
	protected void analyzeType(@Nullable Type newFormal, @Nullable Element newActual) {
		analyzeType(newFormal, (Type)newActual);
	}

	/**
	 * @since 1.23
	 */
	public void analyzeType(@Nullable Type newFormal, @Nullable Type newActual) {
		if ((newFormal != null) && (newActual != null)) {
			Type oldActual = actual;
			try {
				actual = newActual;
				newFormal.accept(this);
			} finally {
				actual = oldActual;
			}
		}
	}

	/**
	 * @since 1.23
	 */
	protected void analyzeType(@Nullable Type newFormal, @Nullable TypedElement actualElement) {
		if (actualElement != null) {
			analyzeType(newFormal, actualElement.getType());
		}
	}

	protected void analyzeTypedElement(@Nullable TypedElement newFormal, @Nullable TypedElement newActual) {
		if ((newFormal != null) && (newActual != null) && (newActual != excludedTarget)) {
			Type oldActual = actual;
			try {
				Type actualTypeValue = null;
				if (newActual instanceof OCLExpression) {
					actualTypeValue = ((OCLExpression)newActual).getTypeValue();
				}
				if (actualTypeValue != null) {
					actual = actualTypeValue;
				}
				else {
					actual = newActual.getType();
				}
				newFormal.accept(this);
			} finally {
				actual = oldActual;
			}
		}
	}

	protected void analyzeTypedElements(@NonNull List<? extends TypedElement> formalElements, @Nullable List<? extends TypedElement> actualElements) {
		if (actualElements != null) {
			int iMax = Math.min(formalElements.size(), actualElements.size());
			for (int i = 0; i < iMax; i++) {
				TypedElement formalElement = formalElements.get(i);
				TypedElement actualElement = actualElements.get(i);
				analyzeTypedElement(formalElement, actualElement);
			}
		}
	}

	protected void analyzeTypes(@NonNull List<? extends Type> formalElements, @NonNull List<? extends Type> actualElements) {
		int iMax = Math.min(formalElements.size(), actualElements.size());
		for (int i = 0; i < iMax; i++) {
			analyzeType(formalElements.get(i), actualElements.get(i));
		}
	}

	/**
	 * Exclude the current typed element which may have a stale type.
	 */
	private void exclude(@NonNull NamedElement typedElement) {
		assert excludedTarget == null;
		excludedTarget = typedElement;
	}

	@Override
	public @Nullable Type get(@Nullable TemplateParameter templateParameter) {
		if (templateParameter == null) {
			return null;
		}
		return getTemplateSpecialization().get(templateParameter);
	}

	@Deprecated /* @deprecated all functionality moved to LibraryOperation */
	protected @Nullable TemplateParameterSubstitutionHelper getHelper(@NonNull Operation operation) {
		return  null;
	}

	/**
	 * @since 1.23
	 */
	public @NonNull Type getNormalizedType(@NonNull Type asType) {
		if ((asType instanceof TemplateParameter) && !(asType instanceof NormalizedTemplateParameter)) {
			TemplateParameter asTemplateParameter = (TemplateParameter)asType;
			if (asTemplateParameter.getConstrainingClasses().isEmpty()) {
			//	assert false;
				return Orphanage.getNormalizedTemplateParameter(environmentFactory.getOrphanage(), asTemplateParameter);
			}
		}
		return asType;
	}

	protected @NonNull TupleType getSpecializedTupleType(@NonNull TupleType type) {
		PivotMetamodelManager metamodelManager = environmentFactory.getMetamodelManager();
		TupleType specializedTupleType = type;
		Map<String, Type> resolutions =  null;
		List<Property> parts = specializedTupleType.getOwnedProperties();
		for (Property part : parts) {
			if (part != null) {
				Type propertyType = PivotUtilInternal.getType(part);
				Type resolvedPropertyType = specializeType(propertyType);
				if (resolvedPropertyType != propertyType) {
					if (resolutions == null) {
						resolutions = new HashMap<>();
					}
					resolutions.put(NameUtil.getSafeName(part), resolvedPropertyType);
				}
			}
		}
		if (resolutions != null) {
			List<@NonNull TuplePartId> partIds = new ArrayList<>(parts.size());
			for (int i = 0; i < parts.size(); i++) {
				@SuppressWarnings("null") @NonNull Property part = parts.get(i);
				String partName = NameUtil.getSafeName(part);
				Type resolvedPropertyType = resolutions.get(partName);
				TypeId partTypeId = resolvedPropertyType != null ? resolvedPropertyType.getTypeId() : part.getTypeId();
				TuplePartId tuplePartId = IdManager.getTuplePartId(i, partName, partTypeId);
				partIds.add(tuplePartId);
			}
			TupleTypeId tupleTypeId = IdManager.getTupleTypeId(Objects.requireNonNull(type.getName()), partIds);
			specializedTupleType = metamodelManager.getCompleteModel().getTupleManager().getTupleType(metamodelManager.getEnvironmentFactory().getIdResolver(), tupleTypeId);
			return specializedTupleType;
		}
		else {
			Map<@NonNull String, @NonNull Type> partMap = new HashMap<>();
			for (TypedElement part : type.getOwnedProperties()) {
				Type type1 = part.getType();
				if (type1 != null) {
					Type type2 = metamodelManager.getPrimaryType(type1);
					Type type3 = specializeType(type2);
					partMap.put(PivotUtil.getName(part), type3);
				}
			}
			return metamodelManager.getCompleteModel().getTupleManager().getTupleType(NameUtil.getSafeName(type), partMap);
		}
	}

	/**
	 * @since 1.23
	 */
	public @NonNull TemplateParameterization getTemplateParameterization() {
		return getTemplateSpecialization().getTemplateParameterization();
	}

	private @NonNull BasicTemplateSpecialization getTemplateSpecialization() {
		return Objects.requireNonNull(templateSpecialization);
	}

	@Override
	public boolean isEmpty() {
		return getTemplateSpecialization().isEmpty();
	}

	@Deprecated /* @deprecated no longer used */
	public void put(int templateParameterIndex, @Nullable Type actualType) {
		assert false;
	//	getTemplateSpecialization().put(templateParameterIndex, actualType);
	}

	@Override
	public @NonNull Type put(@NonNull TemplateParameter formalTemplateParameter, @NonNull Type actualType) {
		if (formalTemplateParameter instanceof NormalizedTemplateParameter) {
			formalTemplateParameter = getTemplateParameterization().get(((NormalizedTemplateParameter)formalTemplateParameter).getIndex());
		}
		BasicTemplateSpecialization templateSpecialization2 = getTemplateSpecialization();
		Type oldType = templateSpecialization2.get(formalTemplateParameter);
		if (oldType == actualType) {
			return actualType;
		}
		else if (oldType != null) {
			IdResolver idResolver = environmentFactory.getIdResolver();
			Type commonType = oldType.getCommonType(idResolver, actualType);
			Type bestType = environmentFactory.getMetamodelManager().getPrimaryType(commonType);
			if (bestType != oldType) {
				templateSpecialization2.put(formalTemplateParameter, bestType);
			}
			return bestType;
		}
		else {
			templateSpecialization2.put(formalTemplateParameter, actualType);
			return actualType;
		}
	}

	private void setTemplateSpecialization(@Nullable BasicTemplateSpecialization templateSpecialization) {
		assert this.templateSpecialization == null;
		this.templateSpecialization = templateSpecialization;
	}

	public @NonNull Type specializeType(@NonNull Type type) {
		PivotMetamodelManager metamodelManager = environmentFactory.getMetamodelManager();
		if (type instanceof NormalizedTemplateParameter) {
			Type actualType = getTemplateSpecialization().get(((NormalizedTemplateParameter)type).getIndex());
			if (!(actualType instanceof NormalizedTemplateParameter) && (actualType instanceof TemplateParameter) && ((TemplateParameter)actualType).getConstrainingClasses().isEmpty()) {
				return type;
			}
			return actualType;
		}
		else if (type instanceof TemplateParameter) {
			Type actualType = getTemplateSpecialization().get((TemplateParameter)type);
			if (actualType == null) {
				actualType = type;
			}
			assert !(actualType instanceof NormalizedTemplateParameter);
			if (actualType instanceof TemplateParameter) {
				TemplateParameter asTemplateParameter = (TemplateParameter)actualType;
				if (asTemplateParameter.getConstrainingClasses().isEmpty()) {
					return Orphanage.getNormalizedTemplateParameter(environmentFactory.getOrphanage(), asTemplateParameter);
				}
			}
			return actualType;
		}
		else if (type instanceof SelfType) {
			return selfType != null ? selfType : type;
		}
		else if (type instanceof CollectionType) {
			CollectionType collectionType = (CollectionType)type;
			Type elementType = PivotUtil.getElementType(collectionType);
			Type specializedElementType = specializeType(elementType);
			CollectionType unspecializedCollectionType = PivotUtil.getUnspecializedTemplateableElement(collectionType);
			return metamodelManager.getCompleteEnvironment().getCollectionType(unspecializedCollectionType, specializedElementType, collectionType.isIsNullFree(), collectionType.getLowerValue(), collectionType.getUpperValue());
		}
		else if (type instanceof MapType) {
			MapType mapType = (MapType)type;
			Type keyType = PivotUtil.getKeyType(mapType);
			Type valueType = PivotUtil.getValueType(mapType);
			Type specializedKeyType = specializeType(keyType);
			Type specializedValueType = specializeType(valueType);
			MapType unspecializedMapType = PivotUtil.getUnspecializedTemplateableElement(mapType);
			return metamodelManager.getCompleteEnvironment().getMapType(unspecializedMapType, specializedKeyType, mapType.isKeysAreNullFree(), specializedValueType, mapType.isValuesAreNullFree());
		}
		else if (type instanceof PrimitiveType) {
			return type;
		}
		else if (type instanceof TupleType) {
			return getSpecializedTupleType((TupleType) type);
		}
		else if (type instanceof LambdaType) {
			LambdaType lambdaType = (LambdaType)type;
			String typeName = PivotUtil.getName(lambdaType);
			Type specializedContextType = specializeType(PivotUtil.getContextType(lambdaType));
			List<@NonNull Type> specializedParameterTypes = new ArrayList<>();
			for (Type parameterType : PivotUtil.getParameterType(lambdaType)) {
				specializedParameterTypes.add(specializeType(parameterType));
			}
			Type specializedResultType = specializeType(PivotUtil.getResultType(lambdaType));
			return metamodelManager.getCompleteModel().getLambdaType(typeName, specializedContextType, specializedParameterTypes, specializedResultType);
		}
		else if (templateSpecialization == null) {	// type instanceof Class
			return type;
		}
		else {	// type instanceof Class
			//
			//	Get the bindings of the type.
			//
			BasicTemplateSpecialization templateSpecialization2 = templateSpecialization;
			assert templateSpecialization2 != null;
			org.eclipse.ocl.pivot.Class partiallySpecializedType = (org.eclipse.ocl.pivot.Class)type;
			org.eclipse.ocl.pivot.Class unspecializedType = PivotUtil.getUnspecializedTemplateableElement(partiallySpecializedType);
			if (partiallySpecializedType != templateSpecialization2.getSpecializedElement()) {
				templateSpecialization2 = TemplateSpecialization.basicGetTemplateSpecialization(partiallySpecializedType);
			}
			if (templateSpecialization2 != null) {
				List<@NonNull Type> templateArguments = new ArrayList<>();
				for (int i = 0; i < templateSpecialization2.size(); i++) {
					Type actualType = templateSpecialization2.get(i);
					actualType = specializeType(actualType);
					templateArguments.add(actualType);

				}
				return metamodelManager.getLibraryType(unspecializedType, templateArguments);
			}
			TemplateParameterization templateParameterization = getTemplateParameterization();//TemplateParameterization.basicGetTemplateParameterization(partiallySpecializedType);
			List<@NonNull Type> templateArguments = new ArrayList<>();
			for (@NonNull TemplateParameter templateParameter : templateParameterization) {
				Type actualType = specializeType(templateParameter);
				templateArguments.add(actualType);
			}
			return metamodelManager.getLibraryType(unspecializedType, templateArguments);
		}
	}

	@Override
	public String toString() {
		return templateSpecialization != null ? templateSpecialization.toString() : NameUtil.debugSimpleName(this);
	}

	@Override
	public String visiting(@NonNull Visitable visitable) {
		throw new UnsupportedOperationException("Unsupported " + getClass().getSimpleName() + " " + visitable.getClass().getSimpleName());
	}

	@Override
	public @Nullable Object visitClass(org.eclipse.ocl.pivot.@NonNull Class object) {
		TemplateSignature formalTemplateSignature = object.getOwnedSignature();
		if (formalTemplateSignature != null) {
			assert object.getOwnedBindings().isEmpty();
			List<@NonNull TemplateParameter> formalParameters = PivotUtilInternal.getOwnedParametersList(formalTemplateSignature);
			if (actual instanceof org.eclipse.ocl.pivot.Class) {
				org.eclipse.ocl.pivot.Class actualClass = (org.eclipse.ocl.pivot.Class)actual;
				TemplateSignature actualTemplateSignature = actualClass.getOwnedSignature();
				if (actualTemplateSignature != null) {
					List<@NonNull TemplateParameter> actualParameters = PivotUtilInternal.getOwnedParametersList(actualTemplateSignature);
					int iMax = actualParameters.size();
					assert iMax == formalParameters.size();
					for (int i = 0; i < iMax; i++) {
						@NonNull TemplateParameter formalParameter = formalParameters.get(i);
						@NonNull TemplateParameter actualParameter = actualParameters.get(i);
						analyzeType(formalParameter, actualParameter);				// normalize actual
					}
				}
				else {
					List<@NonNull TemplateParameterSubstitution> actualSubstitutions = PivotUtilInternal.getOwnedSubstitutionsList(actualClass);
					int iMax = actualSubstitutions.size();
					assert iMax == formalParameters.size();
					for (int i = 0; i < iMax; i++) {
						@NonNull TemplateParameter formalParameter = formalParameters.get(i);
						@NonNull TemplateParameterSubstitution actualSubstitution = actualSubstitutions.get(i);
						Type actualActual = actualSubstitution.getActual();
						analyzeType(formalParameter, actualActual);
					}
				}
			}
		}
		else {
			BasicTemplateSpecialization templateSpecialization = TemplateSpecialization.basicGetTemplateSpecialization(object);
			if (templateSpecialization != null) {
				for (@NonNull Type actualType : templateSpecialization) {
					safeVisit(actualType);
				}
			}
		}
		return null;
	}

	@Override
	public @Nullable Object visitCollectionType(@NonNull CollectionType object) {
		if (actual instanceof CollectionType) {
			Type formalElementType = object.getElementType();
			Type actualElementType = ((CollectionType)actual).getElementType();
			analyzeType(formalElementType, actualElementType);
		}
		return null;
	}

	@Override
	public @Nullable Object visitIterateExp(@NonNull IterateExp object) {
		Iteration referredIteration = object.getReferredIteration();
		analyzeTypedElement(referredIteration, object);
		analyzeType(referredIteration.getOwningClass(), object.getOwnedSource());
		analyzeTypedElements(referredIteration.getOwnedIterators(), object.getOwnedIterators());
		//		analyzeTypedElements(referredIteration.getOwnedCoIterators(), object.getOwnedCoIterators());
		analyzeTypedElement(referredIteration.getOwnedAccumulator(), object.getOwnedResult());
		analyzeTypedElements(referredIteration.getOwnedParameters(), object.getOwnedBodies());
		return null;
	}

	@Override
	public @Nullable Object visitIteratorExp(@NonNull IteratorExp object) {
		Iteration referredIteration = object.getReferredIteration();
		analyzeTypedElement(referredIteration, object);
		analyzeType(referredIteration.getOwningClass(), object.getOwnedSource());
		analyzeTypedElements(referredIteration.getOwnedIterators(), object.getOwnedIterators());
		//		analyzeTypedElements(referredIteration.getOwnedCoIterators(), object.getOwnedCoIterators());
		List<Parameter> formalElements = referredIteration.getOwnedParameters();
		if (formalElements.size() > 0) {
			OCLExpression actualElement = object.getOwnedBody();
			Type actualType = actualElement.getType();
			LibraryIterationOrOperation implementation = (LibraryIterationOrOperation) referredIteration.getImplementation();
			if (implementation != null) {		// Library classes have implementations, Complete OCL classes may be recursive
				actualType = implementation.resolveBodyType(environmentFactory, object, actualType);
			}
			analyzeType(formalElements.get(0).getType(), actualType);
		}
		return null;
	}

	@Override
	public @Nullable Object visitLambdaType(@NonNull LambdaType object) {
		if (actual instanceof LambdaType) {
			LambdaType actualLambdaType = (LambdaType)actual;
			analyzeType(object.getContextType(), actualLambdaType.getContextType());
			analyzeType(object.getResultType(), actualLambdaType.getResultType());
			analyzeTypes(object.getParameterType(), actualLambdaType.getParameterType());
		}
		else {
			analyzeType(object.getResultType(), actual);
		}
		return null;
	}

	@Override
	public @Nullable Object visitMapType(@NonNull MapType object) {
		if (actual instanceof MapType) {
			Type formalKeyType = object.getKeyType();
			Type formalValueType = object.getValueType();
			MapType mapType = (MapType)actual;
			Type actualKeyType = mapType.getKeyType();
			Type actualValueType = mapType.getValueType();
			analyzeType(formalKeyType, actualKeyType);
			analyzeType(formalValueType, actualValueType);
		}
		return null;
	}

	@Override
	public @Nullable Object visitNormalizedTemplateParameter(org.eclipse.ocl.pivot.@NonNull NormalizedTemplateParameter object) {
		TemplateParameterization templateParameterization = getTemplateParameterization();
		if (!templateParameterization.isEmpty()) {
			return visitTemplateParameter(templateParameterization.get(object.getIndex()));
		}
		else {
			assert actual != null;
			put(object, actual);
			return null;
		}
	}

	@Override
	public @Nullable Object visitOperationCallExp(@NonNull OperationCallExp object) {
		Operation referredOperation = object.getReferredOperation();
		//		visit(referredOperation, object);
		analyzeTypedElement(referredOperation, object);
		OCLExpression source = object.getOwnedSource();
		analyzeType(referredOperation.getOwningClass(), source);
		analyzeTypedElements(referredOperation.getOwnedParameters(), object.getOwnedArguments());
		//
		//	FIXME More general processing for T2 < T1
		//
		LibraryIterationOrOperation implementation = (LibraryIterationOrOperation)referredOperation.getImplementation();
		if (implementation != null) {		// Library classes have implementations, Complete OCL classes may be recursive
			implementation.resolveUnmodeledTemplateParameterSubstitutions(this, object);
		}
		return null;
	}

	@Override
	public @Nullable Object visitOppositePropertyCallExp(@NonNull OppositePropertyCallExp object) {
		Property referredOppositeProperty = object.getReferredProperty();
		if (referredOppositeProperty != null) {
			Property referredProperty = referredOppositeProperty.getOpposite();
			if (referredProperty != null) {
				analyzeType(referredProperty.getOwningClass(), object.getOwnedSource());
				analyzeTypedElement(referredProperty, object);
			}
		}
		return null;
	}

	@Override
	public @Nullable Object visitParameter(@NonNull Parameter object) {
		if ((object.isIsTypeof()) && actual instanceof OCLExpression) {
			analyzeType(object.getType(), ((OCLExpression)actual).getTypeValue());
		}
		else {
			super.visitParameter(object);
		}
		return null;
	}

	@Override
	public @Nullable Object visitPrimitiveType(@NonNull PrimitiveType object) {
		return null;
	}

//	@Override
//	public @Nullable Object visitProperty(@NonNull Property object) {
//		analyzeType(object.getOwningClass(), actual);
//		analyzeTypedElement(object, object);
//		return null;
//	}

	@Override
	public @Nullable Object visitPropertyCallExp(@NonNull PropertyCallExp object) {
		Property referredProperty = object.getReferredProperty();
		if (referredProperty != null) {
			analyzeType(referredProperty.getOwningClass(), object.getOwnedSource());
			analyzeTypedElement(referredProperty, object);
		}
		return null;
	}

	@Override
	public @Nullable Object visitSelfType(@NonNull SelfType object) {
		analyzeType(/*selfIsTypeof ? metamodelManager.getClassType() :*/ selfType, actual);
		return null;
	}

	@Override
	public @Nullable Object visitTemplateParameter(@NonNull TemplateParameter object) {
		assert actual != null;
		put(object, actual);
		return null;
	}

	@Override
	public @Nullable Object visitTupleType(@NonNull TupleType object) {
		if (actual instanceof TupleType) {
			analyzeTypedElements(object.getOwnedProperties(), ((TupleType)actual).getOwnedProperties());
		}
		return null;
	}

	@Override
	public @Nullable Object visitTypedElement(@NonNull TypedElement object) {
	//	if (actual instanceof TypedElement) {
	//		analyzeType(object.getType(), ((TypedElement)actual).getType());
	//	}
		analyzeType(object.getType(), actual);
		return null;
	}

//	@Override
//	public @Nullable Object visitVariableExp(@NonNull VariableExp object) {
//		if (actual instanceof TypedElement) {
//			analyzeType(object.getType(), ((TypedElement)actual).getType());
//		}
//		return null;
//	}
}
