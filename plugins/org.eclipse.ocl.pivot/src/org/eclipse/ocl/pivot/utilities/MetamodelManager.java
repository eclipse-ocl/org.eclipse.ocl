/*******************************************************************************
 * Copyright (c) 2010, 2022 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *	E.D.Willink - initial API and implementation
 *	E.D.Willink (CEA LIST) - Bug 399252
 *******************************************************************************/
package org.eclipse.ocl.pivot.utilities;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.CallExp;
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.CompleteClass;
import org.eclipse.ocl.pivot.CompleteInheritance;
import org.eclipse.ocl.pivot.CompletePackage;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ElementExtension;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.IfExp;
import org.eclipse.ocl.pivot.InvalidLiteralExp;
import org.eclipse.ocl.pivot.Library;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.Namespace;
import org.eclipse.ocl.pivot.NullLiteralExp;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.Stereotype;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.WildcardType;
import org.eclipse.ocl.pivot.internal.complete.CompleteClassInternal;
import org.eclipse.ocl.pivot.internal.complete.CompleteEnvironmentInternal;
import org.eclipse.ocl.pivot.internal.complete.CompleteModelInternal;
import org.eclipse.ocl.pivot.internal.complete.CompletePackageInternal;
import org.eclipse.ocl.pivot.internal.complete.StandardLibraryInternal;
import org.eclipse.ocl.pivot.internal.library.ImplementationManager;
import org.eclipse.ocl.pivot.internal.manager.FinalAnalysis;
import org.eclipse.ocl.pivot.internal.manager.FlowAnalysis;
import org.eclipse.ocl.pivot.internal.manager.Orphanage;
import org.eclipse.ocl.pivot.internal.manager.PrecedenceManager;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.internal.utilities.External2AS;
import org.eclipse.ocl.pivot.library.LibraryFeature;
import org.eclipse.ocl.pivot.library.LibraryProperty;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.eclipse.ocl.pivot.values.IntegerValue;
import org.eclipse.ocl.pivot.values.TemplateParameterSubstitutions;
import org.eclipse.ocl.pivot.values.UnlimitedNaturalValue;

/**
 * @since 7.0
 */
public interface MetamodelManager
{
	/**
	 * @since 7.0
	 */
	void addClassLoader(@NonNull ClassLoader classLoader);

	/**
	 * @since 7.0
	 */
	void addExternal2AS(@NonNull External2AS external2as);

	/**
	 * @since 7.0
	 */
	void addGenModel(@NonNull GenModel genModel);

	/**
	 * @since 7.0
	 */
	void addGenPackage(@NonNull GenPackage genPackage);

	/**
	 * @since 7.0
	 */
	@Nullable Namespace addGlobalNamespace(@NonNull String name, @NonNull Namespace namespace);

	/**
	 * @since 7.0
	 */
	void addLockedElement(@NonNull Object lockedElement);

	/**
	 * @since 7.0
	 */
	@Deprecated /* @deprecated only used when AS2XMIID generates legacy Model.xmiidVersion 0 xmiids */
	void assignLibraryIds(org.eclipse.ocl.pivot.internal.utilities.@NonNull AS2XMIid as2xmIid, @Nullable Map<@NonNull String, @Nullable Object> options);

	/**
	 * @since 7.0
	 */
	int compareOperationMatches(@NonNull Operation reference, @NonNull TemplateParameterSubstitutions referenceBindings,
			@NonNull Operation candidate, @NonNull TemplateParameterSubstitutions candidateBindings);

	/**
	 * @since 7.0
	 */
	boolean conformsTo(@NonNull Type firstType, @NonNull TemplateParameterSubstitutions firstSubstitutions,
			@NonNull Type secondType, @NonNull TemplateParameterSubstitutions secondSubstitutions);

	/**
	 * @since 7.0
	 */		// XXX move to PivotHelper
	@NonNull IfExp createIfExp(@NonNull OCLExpression asCondition, @NonNull OCLExpression asThen, @NonNull OCLExpression asElse);

	/**
	 * @since 7.0
	 */
	void createImplicitOppositeProperty(@NonNull Property asProperty, @NonNull String oppositeName,
			boolean isOrdered, boolean isUnique, @NonNull IntegerValue lower, @NonNull UnlimitedNaturalValue upper);

	/**
	 * @since 7.0
	 */
	@NonNull InvalidLiteralExp createInvalidExpression(/*Object object, String boundMessage, Throwable e*/);		// FIXME move to PivotHelper

	/**
	 * @since 7.0
	 */
	@NonNull NullLiteralExp createNullLiteralExp();		// FIXME move to PivotHelper

	/**
	 * @since 7.0
	 */
	@NonNull Orphanage createOrphanage();

	/**
	 * @since 7.0
	 */
	@NonNull WildcardType createWildcardType(org.eclipse.ocl.pivot.@Nullable Class lowerBound, org.eclipse.ocl.pivot.@Nullable Class upperBound);		// FIXME move to PivotHelper

	/**
	 * @since 7.0
	 */
	void dispose();

	/**
	 * @since 7.0
	 */
	org.eclipse.ocl.pivot.@Nullable Class getASClass(@NonNull String className);

	/**
	 * @since 7.0
	 */
	org.eclipse.ocl.pivot.@Nullable Package getASmetamodel();

	@Nullable <T extends Element> T getASOfEcore(@NonNull Class<T> pivotClass, @Nullable EObject eObject);

	@NonNull ResourceSet getASResourceSet();

	/**
	 * @since 7.0
	 */
	@NonNull Iterable<@NonNull CompletePackageInternal> getAllCompletePackages();

	/**
	 * @since 7.0
	 */
	@NonNull Iterable<Constraint> getAllInvariants(@NonNull Type pivotType);

	/**
	 * @since 7.0
	 */
	@NonNull Iterable<@NonNull Operation> getAllOperations(@NonNull Type type, @Nullable FeatureFilter featureFilter);

	/**
	 * @since 7.0
	 */
	@NonNull Iterable<@NonNull Operation> getAllOperations(@NonNull Type type, @Nullable FeatureFilter featureFilter, @NonNull String name);

	/**
	 * @since 7.0
	 */
	@NonNull Iterable<@NonNull CompleteClass> getAllSuperCompleteClasses(@NonNull Type type);

	/**
	 * @since 7.0
	 */
	@NonNull CollectionType getCollectionType(boolean isOrdered, boolean isUnique, @NonNull Type elementType, boolean isNullFree, @Nullable IntegerValue lower, @Nullable UnlimitedNaturalValue upper);

	/**
	 * @since 7.0
	 */
	org.eclipse.ocl.pivot.@NonNull Class getCollectionType(@NonNull String collectionTypeName, @NonNull Type elementType, boolean isNullFree, @Nullable IntegerValue lower, @Nullable UnlimitedNaturalValue upper);

	/**
	 * @since 7.0
	 */
	@NonNull Type getCommonType(@NonNull Type leftType, @NonNull TemplateParameterSubstitutions leftSubstitutions,
			@NonNull Type rightType, @NonNull TemplateParameterSubstitutions rightSubstitutions);

	/**
	 * @since 7.0
	 */
	@NonNull CompleteClassInternal getCompleteClass(@NonNull Type pivotType);

	/**
	 * @since 7.0
	 */
	@NonNull CompleteEnvironmentInternal getCompleteEnvironment();

	/**
	 * @since 7.0
	 */
	@NonNull CompleteModelInternal getCompleteModel();

	/**
	 * @since 7.0
	 */
	@NonNull CompletePackage getCompletePackage(org.eclipse.ocl.pivot.@NonNull Package asPackage);

	/**
	 * @since 7.0
	 */
	@Nullable ExpressionInOCL getDefaultExpression(@NonNull Property property);

	/**
	 * @since 7.0
	 */
	@Nullable External2AS getES2AS(@NonNull Resource esResource);

	@Nullable <T extends EObject> T getEcoreOfPivot(@NonNull Class<T> ecoreClass, @NonNull Element element);

	/**
	 * @since 7.0
	 */
	@NonNull ElementExtension getElementExtension(@NonNull Element asStereotypedElement, @NonNull Stereotype asStereotype);

	/**
	 * @since 7.0
	 */
	@NonNull EnvironmentFactoryInternal getEnvironmentFactory();

	/**
	 * @since 7.0
	 */
	org.eclipse.ocl.pivot.@NonNull Class getEquivalentClass(@NonNull Model thisModel, org.eclipse.ocl.pivot.@NonNull Class thatClass);

	/**
	 * @since 7.0
	 */
	@NonNull FinalAnalysis getFinalAnalysis();

	/**
	 * @since 7.0
	 */
	@NonNull FlowAnalysis getFlowAnalysis(@NonNull OCLExpression oclExpression);

	/**
	 * @since 7.0
	 */
	@Nullable GenPackage getGenPackage(@NonNull String nsURI);

	/**
	 * @since 7.0
	 */
	@NonNull Set<Map.@NonNull Entry<String, Namespace>> getGlobalNamespaces();

	/**
	 * @since 7.0
	 */
	@NonNull Iterable<Type> getGlobalTypes();

	/**
	 * @since 7.0
	 */
	@NonNull LibraryProperty getImplementation(@Nullable Element asNavigationExp, @Nullable Object sourceValue, @NonNull Property property);

	/**
	 * @since 7.0
	 */
	@NonNull LibraryFeature getImplementation(@NonNull Operation operation);

	/**
	 * @since 7.0
	 */
	@NonNull ImplementationManager getImplementationManager();

	/**
	 * @since 7.0
	 */
	@NonNull CompleteInheritance getInheritance(org.eclipse.ocl.pivot.@NonNull Class type);

	/**
	 * @since 7.0
	 */
	@NonNull List<@NonNull Library> getLibraries();

	/**
	 * @since 7.0
	 */
	@Nullable Resource getLibraryResource();

	/**
	 * @since 7.0
	 */
	@NonNull <T extends org.eclipse.ocl.pivot.Class> T getLibraryType(@NonNull T libraryType, @NonNull List<@NonNull ? extends Type> templateArguments);

	/**
	 * @since 7.0
	 */
	@NonNull Iterable<Constraint> getLocalInvariants(org.eclipse.ocl.pivot.@NonNull Class type);

	/**
	 * @since 7.0
	 */
	@Nullable EObject getLockingObject();

	/**
	 * @since 7.0
	 */
	org.eclipse.ocl.pivot.@NonNull Class getMapType(@NonNull String mapTypeName, @NonNull Type keyType, boolean keysAreNullFree, @NonNull Type valueType, boolean valuesAreNullFree);

	/**
	 * @since 7.0
	 */
	org.eclipse.ocl.pivot.@NonNull Class getMapType(org.eclipse.ocl.pivot.@NonNull Class entryClass);

	/**
	 * @since 7.0
	 */
	@NonNull Iterable<@NonNull Operation> getMemberOperations(org.eclipse.ocl.pivot.@NonNull Class type, boolean selectStatic);

	/**
	 * @since 7.0
	 */
	@NonNull Iterable<@NonNull Property> getMemberProperties(org.eclipse.ocl.pivot.@NonNull Class type, boolean selectStatic);

	/**
	 * @since 7.0
	 */
	org.eclipse.ocl.pivot.@NonNull Class getMetaclass(@NonNull Type asInstanceType);

	/**
	 * @since 7.0
	 */
	@Nullable Type getOclType(@NonNull String typeName);

	/**
	 * @since 7.0
	 */
	@NonNull Iterable<? extends Operation> getOperationOverloads(@NonNull Operation pivotOperation);

	/**
	 * @since 7.0
	 */
	@NonNull Iterable<org.eclipse.ocl.pivot.Class> getPartialClasses(@NonNull Type pivotType);

	/**
	 * @since 7.0
	 */
	@NonNull Iterable<? extends org.eclipse.ocl.pivot.@NonNull Package> getPartialPackages(org.eclipse.ocl.pivot.@NonNull Package pkg, boolean loadASmetamodelFirst);

	/**
	 * @since 7.0
	 */
	@NonNull PrecedenceManager getPrecedenceManager();

	org.eclipse.ocl.pivot.@NonNull Class getPrimaryClass(org.eclipse.ocl.pivot.@NonNull Class pivotClass);

	/**
	 * @since 7.0
	 */
	@NonNull <T extends EObject> T getPrimaryElement(@NonNull T element);

	@NonNull Operation getPrimaryOperation(@NonNull Operation pivotOperation);

	org.eclipse.ocl.pivot.@NonNull Package getPrimaryPackage(org.eclipse.ocl.pivot.@NonNull Package eObject);

	/**
	 * @since 7.0
	 */
	org.eclipse.ocl.pivot.@Nullable Package getPrimaryPackage(@NonNull String nsURI, String... subPackagePath);

	@NonNull Property getPrimaryProperty(@NonNull Property pivotProperty);

	/**
	 * @since 7.0
	 */
	org.eclipse.ocl.pivot.@Nullable Class getPrimaryType(@NonNull String nsURI, @NonNull String path, String... extraPath);

	// FIXME ASBH This should probably disappear
	/**
	 * @since 7.0
	 */
	@NonNull Type getPrimaryType(@NonNull Type type);

	/**
	 * @since 7.0
	 */
	@NonNull ASResource getResource(@NonNull URI uri, @Nullable String contentType);

	/**
	 * @since 7.0
	 */
	@NonNull StandardLibraryInternal getStandardLibrary();

	/**
	 * @since 7.0
	 */
	void installPropertyDeclaration(@NonNull Property thisProperty);

	/**
	 * @since 7.0
	 */
	void installResource(@NonNull Resource asResource);

	/**
	 * @since 7.0
	 */
	void installRoot(@NonNull Model pivotModel);

	/**
	 * @since 7.0
	 */
	boolean isSuperClassOf(org.eclipse.ocl.pivot.@NonNull Class unspecializedFirstType, org.eclipse.ocl.pivot.@NonNull Class secondType);

	/**
	 * @since 7.0
	 */
	boolean isTypeServeable(@NonNull Type type);

	/**
	 * @since 7.0
	 */
	@Nullable Resource loadDefaultLibrary(@Nullable String uri);

	/**
	 * @since 7.0
	 */
	@Nullable Element loadResource(@NonNull URI uri, String alias, @Nullable ResourceSet resourceSet) throws ParserException;

	/**
	 * @since 7.0
	 */
	void removeExternalResource(@NonNull External2AS external2as);

	/**
	 * @since 7.0
	 */
	void removeExternalResource(@NonNull Resource esResource);

	/**
	 * @since 7.0
	 */
	void resetFinalAnalysis();

	/**
	 * @since 7.0
	 */
	void resetFlowAnalysis();

	/**
	 * @since 7.0
	 */
	void setAutoLoadASmetamodel(boolean autoLoadASmetamodel);

	/**
	 * @since 7.0
	 */
	void setLibraryLoadInProgress(boolean libraryLoadInProgress);

	/**
	 * @since 7.0
	 */
	@NonNull Type specializeType(@NonNull Type type, @NonNull CallExp actualExp, @NonNull Type selfType, @Nullable Type selfTypeValue);
}
