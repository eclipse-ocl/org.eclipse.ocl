/*******************************************************************************
 * Copyright (c) 2010, 2025 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - initial API and implementation
 * 	 E.D.Willink (Obeo) - Bug 416287 - tuple-valued constraints
 * 	 E.D.Willink (CEA LIST) - Bug 425799 - validity view
 *******************************************************************************/
package org.eclipse.ocl.pivot.utilities;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.ETypeParameter;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Annotation;
import org.eclipse.ocl.pivot.AnyType;
import org.eclipse.ocl.pivot.AssociativityKind;
import org.eclipse.ocl.pivot.BagType;
import org.eclipse.ocl.pivot.CallExp;
import org.eclipse.ocl.pivot.CollectionItem;
import org.eclipse.ocl.pivot.CollectionKind;
import org.eclipse.ocl.pivot.CollectionLiteralExp;
import org.eclipse.ocl.pivot.CollectionLiteralPart;
import org.eclipse.ocl.pivot.CollectionRange;
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.Comment;
import org.eclipse.ocl.pivot.CompleteClass;
import org.eclipse.ocl.pivot.CompleteModel;
import org.eclipse.ocl.pivot.CompletePackage;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.DataType;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ElementExtension;
import org.eclipse.ocl.pivot.EnumLiteralExp;
import org.eclipse.ocl.pivot.Enumeration;
import org.eclipse.ocl.pivot.EnumerationLiteral;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.Feature;
import org.eclipse.ocl.pivot.IfExp;
import org.eclipse.ocl.pivot.Import;
import org.eclipse.ocl.pivot.InvalidType;
import org.eclipse.ocl.pivot.IterableType;
import org.eclipse.ocl.pivot.IterateExp;
import org.eclipse.ocl.pivot.Iteration;
import org.eclipse.ocl.pivot.IteratorExp;
import org.eclipse.ocl.pivot.IteratorVariable;
import org.eclipse.ocl.pivot.LambdaParameter;
import org.eclipse.ocl.pivot.LambdaType;
import org.eclipse.ocl.pivot.LanguageExpression;
import org.eclipse.ocl.pivot.LetExp;
import org.eclipse.ocl.pivot.LetVariable;
import org.eclipse.ocl.pivot.LoopExp;
import org.eclipse.ocl.pivot.MapLiteralExp;
import org.eclipse.ocl.pivot.MapLiteralPart;
import org.eclipse.ocl.pivot.MapType;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.Namespace;
import org.eclipse.ocl.pivot.NavigationCallExp;
import org.eclipse.ocl.pivot.NormalizedTemplateParameter;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.OppositePropertyCallExp;
import org.eclipse.ocl.pivot.OrderedSetType;
import org.eclipse.ocl.pivot.Package;
import org.eclipse.ocl.pivot.Parameter;
import org.eclipse.ocl.pivot.ParameterVariable;
import org.eclipse.ocl.pivot.PivotFactory;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.PivotTables;
import org.eclipse.ocl.pivot.Precedence;
import org.eclipse.ocl.pivot.PrimitiveType;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.PropertyCallExp;
import org.eclipse.ocl.pivot.ResultVariable;
import org.eclipse.ocl.pivot.SelfType;
import org.eclipse.ocl.pivot.SequenceType;
import org.eclipse.ocl.pivot.SetType;
import org.eclipse.ocl.pivot.ShadowExp;
import org.eclipse.ocl.pivot.ShadowPart;
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.StringLiteralExp;
import org.eclipse.ocl.pivot.TemplateBinding;
import org.eclipse.ocl.pivot.TemplateParameter;
import org.eclipse.ocl.pivot.TemplateParameterSubstitution;
import org.eclipse.ocl.pivot.TemplateSignature;
import org.eclipse.ocl.pivot.TemplateableElement;
import org.eclipse.ocl.pivot.TupleLiteralExp;
import org.eclipse.ocl.pivot.TupleLiteralPart;
import org.eclipse.ocl.pivot.TupleType;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.TypeExp;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.Variable;
import org.eclipse.ocl.pivot.VariableDeclaration;
import org.eclipse.ocl.pivot.VariableExp;
import org.eclipse.ocl.pivot.VoidType;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.ids.OperationId;
import org.eclipse.ocl.pivot.ids.PackageId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.library.ecore.EcoreExecutorManager;
import org.eclipse.ocl.pivot.internal.manager.PivotExecutorManager;
import org.eclipse.ocl.pivot.internal.resource.ASResourceFactoryRegistry;
import org.eclipse.ocl.pivot.internal.resource.ProjectMap;
import org.eclipse.ocl.pivot.internal.scoping.EnvironmentView;
import org.eclipse.ocl.pivot.internal.scoping.EnvironmentView.DiagnosticWrappedException;
import org.eclipse.ocl.pivot.internal.utilities.AS2Moniker;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.internal.utilities.External2AS;
import org.eclipse.ocl.pivot.internal.utilities.PivotConstantsInternal;
import org.eclipse.ocl.pivot.internal.utilities.PivotObjectImpl;
import org.eclipse.ocl.pivot.library.LibraryFeature;
import org.eclipse.ocl.pivot.messages.StatusCodes.Severity;
import org.eclipse.ocl.pivot.options.PivotValidationOptions;
import org.eclipse.ocl.pivot.resource.ProjectManager;
import org.eclipse.ocl.pivot.util.PivotPlugin;
import org.eclipse.ocl.pivot.values.IntegerValue;
import org.eclipse.ocl.pivot.values.InvalidValueException;
import org.eclipse.ocl.pivot.values.MapTypeArguments;
import org.eclipse.ocl.pivot.values.Unlimited;
import org.eclipse.ocl.pivot.values.UnlimitedNaturalValue;

import com.google.common.collect.Iterables;

/**
 * @since 7.0
 */
public class PivotUtil implements PivotConstants
{
	private static final Logger logger = Logger.getLogger(PivotUtil.class);

	/**
	 * If DEBUG_DEPRECATIONS is set active debugDeprecation() returns false causing debugged deprecations
	 * to fail their assertions.
	 *
	 * @since 7.0
	 */
	public static final TracingOption DEBUG_DEPRECATIONS = new TracingOption(PivotPlugin.PLUGIN_ID, "debug/deprecation"); //$NON-NLS-1$

	/**
	 * Prefix to be emitted by errPrintln. Initially null, set non-null at the start of some context such as a test. Set
	 * null again once emitted as a prefix.
	 * @since 7.0
	 */
	public static @Nullable String contextLine = "No-context";	// Typically unused init to suppress first blank line

	/**
	 * Set false to allow debug messages to be emitted to System.err.
	 *
	 * @since 7.0
	 */
	public static boolean noDebug = true;

	/**
	 * The start time of a test as updated by debugReset().
	 */
	private static long startTime = System.currentTimeMillis();

	/**
	 * In TemplateSignature order.
	 */
	public static class TemplateParameterSubstitutionComparator
	implements Comparator<TemplateParameterSubstitution>
	{
		public static Comparator<? super TemplateParameterSubstitution> INSTANCE =
				new TemplateParameterSubstitutionComparator();

		@Override
		public int compare(TemplateParameterSubstitution o1, TemplateParameterSubstitution o2) {
			TemplateParameter f1 = o1.getFormal();
			TemplateParameter f2 = o2.getFormal();
			int i1 = f1.getOwningSignature().getOwnedParameters().indexOf(f1);
			int i2 = f2.getOwningSignature().getOwnedParameters().indexOf(f2);
			return i1 - i2;
		}
	}

	/**
	 * @since 1.3
	 */
	public static void addAllClasses(@NonNull EnvironmentView environmentView, org.eclipse.ocl.pivot.@NonNull Package pPackage) {
		String packageName = pPackage.getName();
		if ((packageName == null) || "".equals(packageName)) {
			environmentView.addNamedElements(pPackage.getOwnedClasses());
		}
		else {
			CompletePackage completePackage = environmentView.getEnvironmentFactory().getCompleteModel().getCompletePackage(pPackage);
			environmentView.addNamedElements(completePackage.getAllClasses());
		}
	}

	/**
	 * @since 1.3
	 */
	public static <T extends NamedElement> void addAllNamedElements(@NonNull EnvironmentView environmentView, @NonNull Iterable<T> namedElements) {
		String name = environmentView.getName();
		if (name != null) {
			for (T namedElement : namedElements) {
				if ((namedElement != null) && name.equals(namedElement.getName())) {
					environmentView.addElement(name, namedElement);
				}
			}
		}
		else {
			for (T namedElement : namedElements) {
				if (namedElement != null) {
					environmentView.addNamedElement(namedElement);
				}
			}
		}
	}

	/**
	 * @since 7.0
	 */
	public static @NonNull URI appendASExtensionSuffix(@NonNull URI uri) {
		String fileExtension = uri.fileExtension();
		assert !fileExtension.endsWith(AS_EXTENSION_SUFFIX);
		return uri.trimFileExtension().appendFileExtension(fileExtension + AS_EXTENSION_SUFFIX);
	}

	/**
	 * @since 7.0
	 */
	public static boolean assertIsNormalizedType(/*@NonNull*/ Type asType) {
		if (!((asType instanceof NormalizedTemplateParameter) || !(asType instanceof TemplateParameter) || !(asType.getESObject() instanceof ETypeParameter) || !((ETypeParameter)asType.getESObject()).getEBounds().isEmpty())) {
			System.out.println("assertIsNormalizedType " + NameUtil.debugSimpleName(asType) + " " + asType);
		}
		assert (asType instanceof NormalizedTemplateParameter) || !(asType instanceof TemplateParameter) || !(asType.getESObject() instanceof ETypeParameter) || !((ETypeParameter)asType.getESObject()).getEBounds().isEmpty();
		return true;
	}

	/**
	 * @since 7.0
	 */
	public static @Nullable Namespace basicGetContainingNamespace(@NonNull EObject element) {
		for (EObject eObject = element; eObject != null; eObject = eObject.eContainer()) {
			if (eObject instanceof Namespace) {
				return (Namespace)eObject;
			}
		}
		return null;
	}

	/**
	 * @since 7.0
	 */
	public static @Nullable TemplateableElement basicGetContainingTemplateableElement(@NonNull EObject element) {
		for (EObject eObject = element; eObject != null; eObject = eObject.eContainer()) {
			if (eObject instanceof TemplateableElement) {
				return (TemplateableElement)eObject;
			}
		}
		return null;
	}

	/**
	 * Return the semantics of EPackage as defined by an EAnnotation, null if none defined.
	 * @since 7.0
	 */
	public static @Nullable URI basicGetEPackageSemantics(@NonNull EPackage ePackage) {
		for (EAnnotation eAnnotation : ePackage.getEAnnotations()) {
			String source = eAnnotation.getSource();
			if (PivotConstants.AS_LIBRARY_ANNOTATION_SOURCE.equals(source)) {
				return PivotConstants.AS_LIBRARY_SEMANTICS;
			}
			else if (PivotConstants.AS_METAMODEL_ANNOTATION_SOURCE.equals(source)) {
				return PivotConstants.AS_METAMODEL_SEMANTICS;
			}
		}
		return null;
	}

	/**
	 * @since 7.0
	 */
	public static @Nullable EnvironmentFactoryInternal basicGetEnvironmentFactory(@Nullable Object object) {
		EnvironmentFactoryInternal environmentFactory = ThreadLocalExecutor.basicGetEnvironmentFactory();
		return environmentFactory;
	}

	/**
	 * @since 1.7
	 */
	public static org.eclipse.ocl.pivot.@Nullable Class basicGetLowerBound(@NonNull TemplateParameter templateParameter) {
		for (int recursions = 0; recursions < 100; recursions++) {
			List<org.eclipse.ocl.pivot.@NonNull Class> asConstrainingClasses = getConstrainingClassesList(templateParameter);
			if (asConstrainingClasses.size() <= 0) {
				return null;
			}
			org.eclipse.ocl.pivot.Class pivotType = asConstrainingClasses.get(0);
			if (!(pivotType instanceof TemplateParameter)) {
				return pivotType;
			}
			templateParameter = (TemplateParameter) pivotType;
		}
		return null;
	}

	/**
	 * Return the semantics of Package as defined by an Annotation, null if none defined.
	 * @since 7.0
	 */
	public static @Nullable URI basicGetPackageSemantics(org.eclipse.ocl.pivot.@NonNull Package asPackage) {
		for (Element asAnnotation : asPackage.getOwnedAnnotations()) {
			if (asAnnotation instanceof Annotation) {
				String source = ((Annotation)asAnnotation).getName();
				if (PivotConstants.AS_LIBRARY_ANNOTATION_SOURCE.equals(source)) {
					return PivotConstants.AS_LIBRARY_SEMANTICS;
				}
				else if (PivotConstants.AS_METAMODEL_ANNOTATION_SOURCE.equals(source)) {
					return PivotConstants.AS_METAMODEL_SEMANTICS;
				}
			}
		}
		return null;
	}

	/**
	 * @since 7.0
	 */
	public static @Nullable Operation basicGetReferredOperation(@NonNull CallExp callExp) {
		if (callExp instanceof LoopExp) {
			return ((LoopExp)callExp).getReferredIteration();
		}
		else if (callExp instanceof OperationCallExp) {
			return ((OperationCallExp)callExp).getReferredOperation();
		}
		else {
			return null;
		}
	}

	/**
	 * @since 7.0
	 */
	public static @Nullable ResourceSet basicGetResourceSet(@Nullable Object object) {
		if (object instanceof EObject) {
			Resource resource = ((EObject)object).eResource();
			return resource != null ? resource.getResourceSet() : null;
		}
		else if (object instanceof Resource) {
			return ((Resource)object).getResourceSet();
		}
		else if (object instanceof ResourceSet) {
			return (ResourceSet)object;
		}
		else {
			return null;
		}
	}

	/**
	 * Check that expressionInOCL was successfully compiled. Throws an InvalidValueException explaining the problem
	 * if expressionInOCL has no contextVariable and has a StringLiteralExp bodyExpression.
	 */
	public static void checkExpression(@NonNull ExpressionInOCL expressionInOCL) {
		VariableDeclaration contextVariable = expressionInOCL.getOwnedContext();
		if (contextVariable == null) {
			OCLExpression bodyExpression = expressionInOCL.getOwnedBody();
			if (bodyExpression instanceof StringLiteralExp) {
				throw new InvalidValueException(((StringLiteralExp)bodyExpression).getStringSymbol());
			}
		}
	}

	public static void checkResourceErrors(@NonNull String message, @NonNull Resource resource) throws ParserException {
		List<Resource.Diagnostic> errors = ClassUtil.requireNonNull(resource.getErrors());
		if (errors.size() > 0) {
			throw new SemanticException(formatResourceDiagnostics(errors, message, "\n"));
		}
	}

	/**
	 * @since 1.4
	 */
	public static void checkResourceWarnings(@NonNull String message, @NonNull Resource resource) throws ParserException {
		List<Resource.Diagnostic> warnings = ClassUtil.requireNonNull(resource.getWarnings());
		if (warnings.size() > 0) {
			throw new SemanticException(formatResourceDiagnostics(warnings, message, "\n"));
		}
	}

	public static boolean conformsTo(@Nullable EClassifier targetType, @NonNull EClassifier contentType) {
		if (targetType == contentType) {
			return true;
		}
		if (!(targetType instanceof EClass)) {
			return false;
		}
		if (!(contentType instanceof EClass)) {
			return false;
		}
		return ((EClass) targetType).isSuperTypeOf((EClass) contentType);
	}

	public static boolean conformsTo(@Nullable EStructuralFeature eStructuralFeature, @NonNull EClassifier contentType) {
		if (eStructuralFeature == null) {			// Wildcard match all
			return true;
		}
		EClassifier targetType = eStructuralFeature.getEType();
		if (targetType == contentType) {
			return true;
		}
		if (!(targetType instanceof EClass)) {
			return false;
		}
		if (!(contentType instanceof EClass)) {
			return false;
		}
		return conformsTo(targetType, contentType);
	}

	public static @NonNull AnyType createAnyType(@NonNull String name) {
		AnyType pivotType = PivotFactory.eINSTANCE.createAnyType();
		pivotType.setName(name);
		return pivotType;
	}

	/**
	 * @since 1.3
	 */
	public static @NonNull AnyType createAnyType(/*@NonNull*/ EClass eClass) {
		AnyType pivotType = PivotFactory.eINSTANCE.createAnyType();
		pivotType.setName(eClass.getName());
		((PivotObjectImpl)pivotType).setESObject(eClass);
		return pivotType;
	}

	public static org.eclipse.ocl.pivot.@NonNull Class createClass(/*@NonNull*/ EClass eClass) {
		org.eclipse.ocl.pivot.Class pivotType = PivotFactory.eINSTANCE.createClass();
		pivotType.setName(eClass.getName());
		((PivotObjectImpl)pivotType).setESObject(eClass);
		return pivotType;
	}

	public static org.eclipse.ocl.pivot.@NonNull Class createClass(@NonNull String name) {
		org.eclipse.ocl.pivot.Class pivotType = PivotFactory.eINSTANCE.createClass();
		pivotType.setName(name);
		return pivotType;
	}

	/**
	 * @since 7.0
	 */
	public static @NonNull CollectionItem createCollectionItem(@NonNull OCLExpression asItem) {
		CollectionItem collectionItem = PivotFactory.eINSTANCE.createCollectionItem();
		collectionItem.setOwnedItem(asItem);
		collectionItem.setType(asItem.getType());
		collectionItem.setIsRequired(asItem.isIsRequired());
		return collectionItem;
	}

	/**
	 * @since 7.0
	 */
	public static @NonNull CollectionLiteralExp createCollectionLiteralExp(@NonNull CollectionType asType, @NonNull Iterable<CollectionLiteralPart> asParts) {
		CollectionLiteralExp collectionLiteralExp = PivotFactory.eINSTANCE.createCollectionLiteralExp();
		Iterables.addAll(collectionLiteralExp.getOwnedParts(), asParts);
		collectionLiteralExp.setType(asType);
		collectionLiteralExp.setKind(getCollectionKind(asType));
		collectionLiteralExp.setIsRequired(true);
		return collectionLiteralExp;
	}

	/**
	 * @since 7.0
	 */
	public static @NonNull CollectionType createCollectionType(@NonNull CollectionType genericCollectionType, @NonNull Type elementType,
			boolean isNullFree, @NonNull IntegerValue lower, @NonNull UnlimitedNaturalValue upper) {
		//
		assert getUnspecializedTemplateableElement(genericCollectionType) == genericCollectionType;
		List<TemplateParameter> templateParameters = genericCollectionType.getOwnedSignature().getOwnedParameters();
		TemplateParameter elementParameter = templateParameters.get(0);
		assert elementParameter != null;
		//
		EClass eClass = genericCollectionType.eClass();
		EFactory eFactoryInstance = eClass.getEPackage().getEFactoryInstance();
		CollectionType specializedCollectionType = (CollectionType) eFactoryInstance.create(eClass);
		specializedCollectionType.setName(genericCollectionType.getName());
		specializedCollectionType.setUnspecializedElement(genericCollectionType);
		TemplateParameterSubstitution templateParameterSubstitution = createTemplateParameterSubstitution(elementParameter, elementType);
		TemplateBinding templateBinding = createTemplateBinding(templateParameterSubstitution);
		specializedCollectionType.getOwnedBindings().add(templateBinding);
		assert specializedCollectionType.getElementType() == elementType;
		specializedCollectionType.setIsNullFree(isNullFree);
		specializedCollectionType.setLowerValue(lower);
		specializedCollectionType.setUpperValue(upper);
		// NB no superClasses
		return specializedCollectionType;
	}

	/**
	 * @since 7.0
	 */
	public static @NonNull Comment createComment(@NonNull String comment) {
		Comment asComment = PivotFactory.eINSTANCE.createComment();
		asComment.setBody(comment);
		return asComment;
	}

	public static @NonNull DataType createDataType(/*@NonNull*/ EDataType eDataType) {
		DataType pivotType = PivotFactory.eINSTANCE.createDataType();
		pivotType.setName(eDataType.getName());
		((PivotObjectImpl)pivotType).setESObject(eDataType);
		return pivotType;
	}

	public static @NonNull DataType createDataType(@NonNull String name) {
		DataType pivotType = PivotFactory.eINSTANCE.createDataType();
		pivotType.setName(name);
		return pivotType;
	}

	/**
	 * @since 7.0
	 */
	public static @NonNull EnumLiteralExp createEnumLiteralExp(@NonNull EnumerationLiteral value) {
		EnumLiteralExp asEnumLiteralExp = PivotFactory.eINSTANCE.createEnumLiteralExp();
		asEnumLiteralExp.setReferredLiteral(value);
		asEnumLiteralExp.setType(value.getOwningEnumeration());
		asEnumLiteralExp.setIsRequired(true);
		return asEnumLiteralExp;
	}

	public static @NonNull Enumeration createEnumeration(/*@NonNull*/ EEnum eEnum) {
		Enumeration pivotType = PivotFactory.eINSTANCE.createEnumeration();
		pivotType.setName(eEnum.getName());
		((PivotObjectImpl)pivotType).setESObject(eEnum);
		return pivotType;
	}

	public static @NonNull Enumeration createEnumeration(@NonNull String name) {
		Enumeration pivotType = PivotFactory.eINSTANCE.createEnumeration();
		pivotType.setName(name);
		return pivotType;
	}

	public static @NonNull EnumerationLiteral createEnumerationLiteral(/*@NonNull*/ EEnumLiteral eEnumLiteral) {
		EnumerationLiteral pivotEnumerationLiteral = PivotFactory.eINSTANCE.createEnumerationLiteral();
		pivotEnumerationLiteral.setName(eEnumLiteral.getName());
		((PivotObjectImpl)pivotEnumerationLiteral).setESObject(eEnumLiteral);
		return pivotEnumerationLiteral;
	}

	public static @NonNull EnumerationLiteral createEnumerationLiteral(@NonNull String name) {
		EnumerationLiteral pivotEnumerationLiteral = PivotFactory.eINSTANCE.createEnumerationLiteral();
		pivotEnumerationLiteral.setName(name);
		return pivotEnumerationLiteral;
	}

	public static @NonNull ExpressionInOCL createExpressionInOCL(@Nullable Variable asContextVariable, @NonNull OCLExpression asExpression, /*@NonNull*/ Variable... asParameterVariables) {
		ExpressionInOCL asExpressionInOCL = PivotFactory.eINSTANCE.createExpressionInOCL();
		asExpressionInOCL.setOwnedContext(asContextVariable);
		if (asParameterVariables != null) {
			for (Variable asParameterVariable : asParameterVariables) {
				asExpressionInOCL.getOwnedParameters().add(asParameterVariable);
			}
		}
		asExpressionInOCL.setOwnedBody(asExpression);
		asExpressionInOCL.setType(asExpression.getType());
		asExpressionInOCL.setIsRequired(asExpression.isIsRequired());
		return asExpressionInOCL;
	}

	/**
	 * @since 7.0
	 */
	public static @NonNull ExpressionInOCL createExpressionInOCLError(@NonNull String string) {
		ExpressionInOCL expressionInOCL = PivotFactory.eINSTANCE.createExpressionInOCL();
		StringLiteralExp stringLiteral = PivotFactory.eINSTANCE.createStringLiteralExp();
		stringLiteral.setStringSymbol(string); //createTupleValuedConstraint("false", null, string));
		expressionInOCL.setOwnedBody(stringLiteral);
		expressionInOCL.setType(stringLiteral.getType());
		return expressionInOCL;
	}

	/**
	 * @since 7.0
	 */
	public static @NonNull Import createImport(@Nullable String name, @NonNull Namespace namespace) {
		Import asImport = PivotFactory.eINSTANCE.createImport();
		asImport.setName(name);
		asImport.setImportedNamespace(namespace);
		asImport.setXmiidVersion(PivotConstants.XMIIDS_CURRENT);
		return asImport;
	}

	public static @NonNull InvalidType createInvalidType(@NonNull String name) {
		InvalidType pivotType = PivotFactory.eINSTANCE.createInvalidType();
		pivotType.setName(name);
		return pivotType;
	}

	/**
	 * @since 1.3
	 */
	public static @NonNull InvalidType createInvalidType(/*@NonNull*/ EClass eClass) {
		InvalidType pivotType = PivotFactory.eINSTANCE.createInvalidType();
		pivotType.setName(eClass.getName());
		((PivotObjectImpl)pivotType).setESObject(eClass);
		return pivotType;
	}

	/**
	 * @since 7.0
	 */
	public static @NonNull Iteration createIteration(@NonNull String name, @NonNull Type type, @Nullable String implementationClass, @NonNull LibraryFeature implementation) {
		Iteration pivotIteration = PivotFactory.eINSTANCE.createIteration();
		pivotIteration.setName(name);
		pivotIteration.setType(type);
		pivotIteration.setImplementationClass(implementationClass);
		pivotIteration.setImplementation(implementation);
		return pivotIteration;
	}

	/**
	 * @since 7.0
	 */
	public static @NonNull IteratorVariable createIteratorVariable(@NonNull String name, @NonNull Type asType, boolean isRequired) {
		IteratorVariable asVariable = PivotFactory.eINSTANCE.createIteratorVariable();
		asVariable.setName(name);
		asVariable.setType(asType);
		asVariable.setIsRequired(isRequired);
		return asVariable;
	}

	public static @NonNull LambdaType createLambdaType(@NonNull String name) {
		LambdaType pivotType = PivotFactory.eINSTANCE.createLambdaType();
		pivotType.setName(name);
		return pivotType;
	}

	/**
	 * @since 7.0
	 */
	public static @NonNull LetExp createLetExp(@NonNull Variable asVariable, @NonNull OCLExpression asIn) {
		LetExp asLetExp = PivotFactory.eINSTANCE.createLetExp();
		asLetExp.setOwnedIn(asIn);
		asLetExp.setType(asIn.getType());
		asLetExp.setIsRequired(asIn.isIsRequired());
		asLetExp.setOwnedVariable(asVariable);
		return asLetExp;
	}

	/**
	 * @since 7.0
	 */
	public static @NonNull LetVariable createLetVariable(@NonNull String name, @NonNull OCLExpression asInitExpression) {
		LetVariable asVariable = PivotFactory.eINSTANCE.createLetVariable();
		asVariable.setName(name);
		asVariable.setType(asInitExpression.getType());
		asVariable.setIsRequired(asInitExpression.isIsRequired());
		asVariable.setOwnedInit(asInitExpression);
		return asVariable;
	}

	/**
	 * @since 7.0
	 */
	public static @NonNull LetVariable createLetVariable(@NonNull String name, @NonNull Type asType, boolean isRequired) {
		LetVariable asVariable = PivotFactory.eINSTANCE.createLetVariable();
		asVariable.setName(name);
		asVariable.setType(asType);
		asVariable.setIsRequired(isRequired);
		return asVariable;
	}

	/**
	 * @since 7.0
	 */
	public static @NonNull LetVariable createLetVariable(@NonNull String name, @NonNull Type asType, boolean isRequired, @NonNull OCLExpression asInitExpression) {
		LetVariable asVariable = PivotFactory.eINSTANCE.createLetVariable();
		asVariable.setName(name);
		asVariable.setType(asType);
		asVariable.setIsRequired(isRequired);
		asVariable.setOwnedInit(asInitExpression);
		return asVariable;
	}

	/**
	 * @since 7.0
	 */
	public static @NonNull OCLExpression createMapLiteralExp(@NonNull MapType asType, @NonNull Iterable<MapLiteralPart> asParts) {
		MapLiteralExp mapLiteralExp = PivotFactory.eINSTANCE.createMapLiteralExp();
		Iterables.addAll(mapLiteralExp.getOwnedParts(), asParts);
		mapLiteralExp.setType(asType);
		mapLiteralExp.setIsRequired(true);
		return mapLiteralExp;
	}

	/**
	 * @since 7.0
	 */
	public static @NonNull MapLiteralPart createMapLiteralPart(@NonNull OCLExpression asKey, @NonNull OCLExpression asValue) {
		MapLiteralPart mapLiteralPart = PivotFactory.eINSTANCE.createMapLiteralPart();
		mapLiteralPart.setOwnedKey(asKey);
		mapLiteralPart.setOwnedValue(asValue);
		//		mapLiteralPart.setType(asItem.getType());
		//		mapLiteralPart.setIsRequired(true);
		return mapLiteralPart;
	}

	/**
	 * @since 7.0
	 */
	public static @NonNull MapType createMapEntryType(@NonNull MapType specializedMapType, org.eclipse.ocl.pivot.@NonNull Class entryClass) {
		assert specializedMapType.getEntryClass() == null;
		MapType genericMapType = getUnspecializedTemplateableElement(specializedMapType);
		assert specializedMapType != genericMapType;
		Type keyType = getKeyType(specializedMapType);
		boolean keysAreNullFree = specializedMapType.isKeysAreNullFree();
		Type valueType = getValueType(specializedMapType);
		boolean valuesAreNullFree = specializedMapType.isValuesAreNullFree();

		MapType mapEntryType = createMapType(genericMapType, keyType, keysAreNullFree, valueType, valuesAreNullFree);
		mapEntryType.setEntryClass(entryClass);
	//	mapEntryType.setBehavioralClass(mapType);		// XXX superClasses
		return mapEntryType;
	}

	/**
	 * @since 7.0
	 */
	public static @NonNull MapType createMapType(@NonNull MapType genericMapType, @NonNull Type keyType, boolean keysAreNullFree, @NonNull Type valueType, boolean valuesAreNullFree) {
		assert genericMapType.getEntryClass() == null;
		//
		assert getUnspecializedTemplateableElement(genericMapType) == genericMapType;
		List<TemplateParameter> templateParameters = genericMapType.getOwnedSignature().getOwnedParameters();
		TemplateParameter keyParameter = templateParameters.get(0);
		TemplateParameter valueParameter = templateParameters.get(1);
		assert keyParameter != null;
		assert valueParameter != null;
		//
		MapType specializedMapType = PivotFactory.eINSTANCE.createMapType();
		specializedMapType.setName(TypeId.MAP_NAME);
		specializedMapType.setUnspecializedElement(genericMapType);
		TemplateParameterSubstitution templateParameterSubstitution1 = createTemplateParameterSubstitution(keyParameter, keyType);
		TemplateParameterSubstitution templateParameterSubstitution2 = createTemplateParameterSubstitution(valueParameter, valueType);
		TemplateBinding templateBinding = createTemplateBinding(templateParameterSubstitution1, templateParameterSubstitution2);
		specializedMapType.getOwnedBindings().add(templateBinding);
		assert specializedMapType.getKeyType() == keyType;
		assert specializedMapType.getValueType() == valueType;
		specializedMapType.setKeysAreNullFree(keysAreNullFree);
		specializedMapType.setValuesAreNullFree(valuesAreNullFree);
		// NB no superClasses
		return specializedMapType;
	}

	/**
	 * @since 7.0
	 */
	public static @NonNull MapTypeArguments createMapTypeArguments(org.eclipse.ocl.pivot.@NonNull Class entryClass) {
		Iterable<@NonNull Property> ownedProperties = PivotUtil.getOwnedProperties(entryClass);
		Property keyProperty = ClassUtil.requireNonNull(NameUtil.getNameable(ownedProperties, "key"));
		Property valueProperty = ClassUtil.requireNonNull(NameUtil.getNameable(ownedProperties, "value"));
		Type keyType = PivotUtil.getType(keyProperty);
		boolean keysAreNullFree = keyProperty.isIsRequired();
		Type valueType = PivotUtil.getType(valueProperty);
		boolean valuesAreNullFree = valueProperty.isIsRequired();
		return new MapTypeArguments(keyType, keysAreNullFree, valueType, valuesAreNullFree);
	}

	public static @NonNull Model createModel(String externalURI) {
		Model pivotModel = PivotFactory.eINSTANCE.createModel();
		pivotModel.setExternalURI(externalURI);
		return pivotModel;
	}

	public static @NonNull <T extends Model> T createModel(@NonNull Class<T> pivotClass, /*@NonNull*/ EClass pivotEClass, String externalURI) {
		assert pivotEClass != null;
		@SuppressWarnings("unchecked")
		T pivotModel = (T) pivotEClass.getEPackage().getEFactoryInstance().create(pivotEClass);
		pivotModel.setExternalURI(externalURI);
		return pivotModel;
	}

	/**
	 * @since 7.0
	 */
	public static <T extends NamedElement> @NonNull T createNamedElement(@NonNull T asNamedElement) {
		@SuppressWarnings("unchecked") T asClone = (T)PivotFactory.eINSTANCE.create(asNamedElement.eClass());
		asClone.setName(asNamedElement.getName());
		return asClone;
	}

	public static @NonNull Operation createOperation(@NonNull String name, @NonNull Type type, @Nullable String implementationClass, @Nullable LibraryFeature implementation) {
		Operation pivotOperation = PivotFactory.eINSTANCE.createOperation();
		pivotOperation.setName(name);
		pivotOperation.setType(type);
		pivotOperation.setImplementationClass(implementationClass);
		pivotOperation.setImplementation(implementation);
		return pivotOperation;
	}

	public static @NonNull Operation createOperation(/*@NonNull*/ EOperation eOperation, @NonNull Type type, @Nullable String implementationClass, @Nullable LibraryFeature implementation) {
		Operation pivotOperation = PivotFactory.eINSTANCE.createOperation();
		pivotOperation.setName(eOperation.getName());
		pivotOperation.setType(type);
		pivotOperation.setImplementationClass(implementationClass);
		pivotOperation.setImplementation(implementation);
		((PivotObjectImpl)pivotOperation).setESObject(eOperation);
		return pivotOperation;
	}

	public static @NonNull Operation createOperation(@NonNull String name, @NonNull ExpressionInOCL asExpressionInOCL) {
		Operation asOperation = PivotFactory.eINSTANCE.createOperation();
		asOperation.setName(name);
		initOperation(asOperation, asExpressionInOCL);
		return asOperation;
	}

	public static org.eclipse.ocl.pivot.@NonNull Package createOwnedPackage(@NonNull Model parentRoot, @NonNull String name) {
		@SuppressWarnings("null")
		org.eclipse.ocl.pivot.Package aPackage = createPackage(org.eclipse.ocl.pivot.Package.class, PivotPackage.Literals.PACKAGE, name, null, null);
		parentRoot.getOwnedPackages().add(aPackage);
		return aPackage;
	}

	/**
	 * @since 7.0
	 */
	public static org.eclipse.ocl.pivot.@NonNull Package createOwnedPackage(org.eclipse.ocl.pivot.@NonNull Package parentPackage, @NonNull String name) {
		@SuppressWarnings("null")
		org.eclipse.ocl.pivot.Package aPackage = createPackage(org.eclipse.ocl.pivot.Package.class, PivotPackage.Literals.PACKAGE, name, null, null);
		parentPackage.getOwnedPackages().add(aPackage);
		return aPackage;
	}

	/**
	 * @since 7.0
	 */
	public static org.eclipse.ocl.pivot.@NonNull Package createPackage(@NonNull String name, @Nullable String nsPrefix, @Nullable String nsURI) {
		Package asPackage = PivotFactory.eINSTANCE.createPackage();
		asPackage.setName(name);
		if (nsPrefix != null) {
			asPackage.setNsPrefix(nsPrefix);
		}
		if (nsURI != null) {
			asPackage.setURI(nsURI);
		}
		return asPackage;
	}

	public static org.eclipse.ocl.pivot.@NonNull Package createPackage(/*@NonNull*/ EPackage ePackage, @Nullable String nsPrefix, @NonNull String nsURI) {
		org.eclipse.ocl.pivot.Package pivotPackage = PivotFactory.eINSTANCE.createPackage();
		pivotPackage.setName(ePackage.getName());
		pivotPackage.setNsPrefix(nsPrefix);
		pivotPackage.setURI(nsURI);
		((PivotObjectImpl)pivotPackage).setESObject(ePackage);
		return pivotPackage;
	}

	public static org.eclipse.ocl.pivot.@NonNull Package createPackage(@NonNull String name, @Nullable String nsPrefix, @NonNull String nsURI, @Nullable PackageId zzpackageId) {
		org.eclipse.ocl.pivot.Package pivotPackage = PivotFactory.eINSTANCE.createPackage();
		pivotPackage.setName(name);
		pivotPackage.setNsPrefix(nsPrefix);
	//	if (packageId != null) {
	//		((PackageImpl)pivotPackage).setPackageId(packageId);  // FIXME Add to API
	//	}
		pivotPackage.setURI(nsURI);
		return pivotPackage;
	}

	public static @NonNull <T extends org.eclipse.ocl.pivot.Package> T createPackage(@NonNull Class<T> pivotClass,
			@NonNull EClass pivotEClass, @NonNull String name, @Nullable String nsURI, @Nullable PackageId packageId) {
		return createPackage(pivotClass, pivotEClass, name, nsURI, null, packageId);
	}

	/**
	 * @since 1.14
	 */
	public static @NonNull <T extends org.eclipse.ocl.pivot.Package> T createPackage(@NonNull Class<T> pivotClass,
			@NonNull EClass pivotEClass, @NonNull String name, @Nullable String nsURI, @Nullable String nsPrefix, @Nullable PackageId zzpackageId) {
		@SuppressWarnings("unchecked")
		T asPackage = (T) pivotEClass.getEPackage().getEFactoryInstance().create(pivotEClass);
		asPackage.setName(name);
	//	if (packageId != null) {
	//		((PackageImpl)asPackage).setPackageId(packageId);
	//	}
		asPackage.setNsPrefix(nsPrefix);		// Before setURI accesses it.
		asPackage.setURI(nsURI);
		return asPackage;
	}

	/**
	 * @since 7.0
	 */
	public static @NonNull Parameter createParameter(@NonNull String name, @NonNull Type asType, boolean isRequired) {
		Parameter asParameter = PivotFactory.eINSTANCE.createParameter();
		asParameter.setName(name);
		asParameter.setType(asType);
		asParameter.setIsRequired(isRequired);
		return asParameter;
	}

	/**
	 * @since 7.0
	 */
	public static @NonNull Parameter createParameter(@NonNull TypedElement typedElement) {
		String name = ClassUtil.requireNonNull(typedElement.getName());
		Type type = ClassUtil.requireNonNull(typedElement.getType());
		Parameter asParameter = createParameter(name, type, typedElement.isIsRequired());
		return asParameter;
	}

	/**
	 * @since 7.0
	 */
	public static @NonNull ParameterVariable createParameterVariable(@NonNull String name, @NonNull Type asType, boolean isRequired) {
		ParameterVariable asVariable = PivotFactory.eINSTANCE.createParameterVariable();
		asVariable.setName(name);
		asVariable.setType(asType);
		asVariable.setIsRequired(isRequired);
		return asVariable;
	}

	/**
	 * @since 7.0
	 */
	public static @NonNull ParameterVariable createParameterVariable(@NonNull Parameter asParameter) {
		ParameterVariable asParameterVariable = PivotFactory.eINSTANCE.createParameterVariable();
		asParameterVariable.setName(asParameter.getName());
		asParameterVariable.setType(asParameter.getType());
		asParameterVariable.setIsRequired(asParameter.isIsRequired());
		asParameterVariable.setRepresentedParameter(asParameter);
		return asParameterVariable;
	}

	public static @NonNull Precedence createPrecedence(@NonNull String name, /*@NonNull*/ AssociativityKind kind) {
		assert kind != null;
		Precedence pivotPrecedence = PivotFactory.eINSTANCE.createPrecedence();
		pivotPrecedence.setName(name);
		pivotPrecedence.setAssociativity(kind);
		return pivotPrecedence;
	}

	/**
	 * @since 1.16
	 */
	public static @NonNull PrimitiveType createPrimitiveType(/*@NonNull*/ EDataType eDataType) {
		boolean isBoolean = eDataType.getInstanceClass() == Boolean.class;
		PrimitiveType pivotType = isBoolean ? PivotFactory.eINSTANCE.createBooleanType() : PivotFactory.eINSTANCE.createPrimitiveType();
		pivotType.setName(eDataType.getName());
		((PivotObjectImpl)pivotType).setESObject(eDataType);
		return pivotType;
	}

	public static @NonNull PrimitiveType createPrimitiveType(@NonNull String name) {
		PrimitiveType pivotType = PivotFactory.eINSTANCE.createPrimitiveType();
		pivotType.setName(name);
		return pivotType;
	}

	public static @NonNull Property createProperty(/*@NonNull*/ EStructuralFeature eFeature, @NonNull Type type) {
		Property pivotProperty = PivotFactory.eINSTANCE.createProperty();
		pivotProperty.setName(eFeature.getName());
		pivotProperty.setType(type);
		((PivotObjectImpl)pivotProperty).setESObject(eFeature);
		return pivotProperty;
	}

	/**
	 * @since 7.0
	 */
	public static @NonNull Property createProperty(@NonNull String name, @NonNull Type type) {
		Property pivotProperty = PivotFactory.eINSTANCE.createProperty();
		pivotProperty.setName(name);
		pivotProperty.setType(type);
		return pivotProperty;
	}

	/**
	 * @since 7.0
	 */
	public static @NonNull PropertyCallExp createPropertyCallExp(@NonNull OCLExpression asSource, @NonNull Property asProperty) {
		PropertyCallExp asChild = PivotFactory.eINSTANCE.createPropertyCallExp();
		asChild.setOwnedSource(asSource);
		asChild.setReferredProperty(asProperty);
		asChild.setType(asProperty.getType());
		asChild.setIsRequired(asProperty.isIsRequired());
		return asChild;
	}

	/**
	 * @since 7.0
	 */
	public static @NonNull ResultVariable createResultVariable(@NonNull String name, @NonNull Type asType, boolean isRequired, @NonNull OCLExpression asInitExpression) {
		ResultVariable asVariable = PivotFactory.eINSTANCE.createResultVariable();
		asVariable.setName(name);
		asVariable.setType(asType);
		asVariable.setIsRequired(isRequired);
		asVariable.setOwnedInit(asInitExpression);
		return asVariable;
	}

	public static @NonNull SelfType createSelfType(@NonNull String name) {
		SelfType pivotType = PivotFactory.eINSTANCE.createSelfType();
		pivotType.setName(name);
		return pivotType;
	}

	/**
	 * @since 1.3
	 */
	public static @NonNull SelfType createSelfType(/*@NonNull*/ EClass eClass) {
		SelfType pivotType = PivotFactory.eINSTANCE.createSelfType();
		pivotType.setName(eClass.getName());
		((PivotObjectImpl)pivotType).setESObject(eClass);
		return pivotType;
	}

	/**
	 * @since 7.0
	 */
	public static @NonNull Annotation createSemanticsAnnotation(@NonNull URI semantics) {
		Annotation asAnnotation = PivotFactory.eINSTANCE.createAnnotation();
		if (PivotConstants.AS_LIBRARY_SEMANTICS.equals(semantics)) {
			asAnnotation.setName(PivotConstants.AS_LIBRARY_ANNOTATION_SOURCE);
		}
		else if (PivotConstants.AS_METAMODEL_SEMANTICS.equals(semantics)) {
			asAnnotation.setName(PivotConstants.AS_METAMODEL_ANNOTATION_SOURCE);
		}
		else {
			asAnnotation.setName(semantics.toString());
		}
		return asAnnotation;
	}

	/**
	 * @since 7.0
	 */
	public static @NonNull OCLExpression createShadowExp(org.eclipse.ocl.pivot.@NonNull Class asClass, @NonNull Iterable<ShadowPart> asParts) {
		ShadowExp shadowExp = PivotFactory.eINSTANCE.createShadowExp();
		Iterables.addAll(shadowExp.getOwnedParts(), asParts);
		shadowExp.setType(asClass);
		shadowExp.setIsRequired(true);
		return shadowExp;
	}

	/**
	 * @since 7.0
	 */
	public static @NonNull ShadowPart createShadowPart(@NonNull Property asProperty, @NonNull OCLExpression asValue) {
		ShadowPart shadowPart = PivotFactory.eINSTANCE.createShadowPart();
		shadowPart.setReferredProperty(asProperty);
		shadowPart.setType(asProperty.getType());
		shadowPart.setIsRequired(asProperty.isIsRequired());
		shadowPart.setOwnedInit(asValue);
		return shadowPart;
	}

	public static @NonNull TemplateBinding createTemplateBinding(TemplateParameterSubstitution... templateParameterSubstitutions) {
		TemplateBinding pivotTemplateBinding = PivotFactory.eINSTANCE.createTemplateBinding();
		List<TemplateParameterSubstitution> parameterSubstitutions = pivotTemplateBinding.getOwnedSubstitutions();
		for (TemplateParameterSubstitution templateParameterSubstitution : templateParameterSubstitutions) {
			parameterSubstitutions.add(templateParameterSubstitution);
		}
		return pivotTemplateBinding;
	}

	public static @NonNull TemplateParameter createTemplateParameter(@NonNull String name, org.eclipse.ocl.pivot.Class... lowerBounds) {
		TemplateParameter pivotTemplateParameter = PivotFactory.eINSTANCE.createTemplateParameter();
		pivotTemplateParameter.setName(name);
		if (lowerBounds != null) {
			List<org.eclipse.ocl.pivot.@NonNull Class> constrainingClasses = getConstrainingClassesList(pivotTemplateParameter);
			for (org.eclipse.ocl.pivot.Class lowerBound : lowerBounds) {
				assert lowerBound != null;
				constrainingClasses.add(lowerBound);
			}
		}
		return pivotTemplateParameter;
	}

	/**
	 * @since 7.0
	 */
	public static @NonNull TemplateParameterSubstitution createTemplateParameterSubstitution(@NonNull TemplateParameter formal, @NonNull Type actual) {
		TemplateParameterSubstitution pivotTemplateParameterSubstitution = PivotFactory.eINSTANCE.createTemplateParameterSubstitution();
		pivotTemplateParameterSubstitution.setFormal(formal);
		pivotTemplateParameterSubstitution.setActual(actual);
		return pivotTemplateParameterSubstitution;
	}

	/**
	 * @since 7.0
	 */
	public static @NonNull TemplateSignature createTemplateSignature(@NonNull TemplateableElement templateableElement, TemplateParameter... templateParameters) {
		TemplateSignature pivotTemplateSignature = PivotFactory.eINSTANCE.createTemplateSignature();
		List<TemplateParameter> parameters = pivotTemplateSignature.getOwnedParameters();
		for (TemplateParameter templateParameter : templateParameters) {
			parameters.add(templateParameter);
		}
		pivotTemplateSignature.setOwningElement(templateableElement);
		return pivotTemplateSignature;
	}

	/**
	 * @since 7.0
	 */
	public static @NonNull TupleLiteralExp createTupleLiteralExp(@NonNull TupleType asType, @NonNull Iterable<TupleLiteralPart> asParts) {
		TupleLiteralExp tupleLiteralExp = PivotFactory.eINSTANCE.createTupleLiteralExp();
		Iterables.addAll(tupleLiteralExp.getOwnedParts(), asParts);
		tupleLiteralExp.setType(asType);
		tupleLiteralExp.setIsRequired(true);
		return tupleLiteralExp;
	}

	/**
	 * @since 7.0
	 */
	public static @NonNull TupleLiteralPart createTupleLiteralPart(@NonNull String name, @NonNull Type asType, boolean isRequired, @NonNull OCLExpression asValue) {
		TupleLiteralPart tupleLiteralPart = PivotFactory.eINSTANCE.createTupleLiteralPart();
		tupleLiteralPart.setName(name);
		tupleLiteralPart.setType(asType);
		tupleLiteralPart.setIsRequired(isRequired);
		tupleLiteralPart.setOwnedInit(asValue);
		return tupleLiteralPart;
	}

	public static @NonNull TupleType createTupleType(@NonNull String name, Property... properties) {
		TupleType pivotType = PivotFactory.eINSTANCE.createTupleType();
		pivotType.setName(name);
		List<Property> ownedProperties = pivotType.getOwnedProperties();
		for (Property property : properties) {
			ownedProperties.add(property);
		}
		return pivotType;
	}

	public static @NonNull String createTupleValuedConstraint(@NonNull String statusText, @Nullable Integer severity, @Nullable String messageText) {
		if ((severity == null) && (messageText == null)) {
			return statusText;
		}
		StringBuilder s = new StringBuilder();
		s.append("Tuple {");
		if (messageText != null) {
			s.append("\n\t" + MESSAGE_PART_NAME + " : String = " + messageText + ",");
		}
		if (severity != null) {
			s.append("\n\t" + SEVERITY_PART_NAME + " : Integer = " + severity + ",");
		}
		s.append("\n\t" + STATUS_PART_NAME + " : Boolean = " + statusText);		// NB parts in alphabetical order
		s.append("\n}."+ STATUS_PART_NAME);
		return s.toString();
	}

	/**
	 * @since 1.3
	 */
	public static @NonNull VariableExp createVariableExp(@NonNull VariableDeclaration asVariable) {
		VariableExp asVariableExp = PivotFactory.eINSTANCE.createVariableExp();
		asVariableExp.setReferredVariable(asVariable);
		asVariableExp.setName(asVariable.getName());
		asVariableExp.setType(asVariable.getType());
		asVariableExp.setIsRequired(asVariable.isIsRequired());
		return asVariableExp;
	}

	public static @NonNull VoidType createVoidType(@NonNull String name) {
		VoidType pivotType = PivotFactory.eINSTANCE.createVoidType();
		pivotType.setName(name);
		return pivotType;
	}

	/**
	 * @since 1.3
	 */
	public static @NonNull VoidType createVoidType(/*@NonNull*/ EClass eClass) {
		VoidType pivotType = PivotFactory.eINSTANCE.createVoidType();
		pivotType.setName(eClass.getName());
		((PivotObjectImpl)pivotType).setESObject(eClass);
		return pivotType;
	}

	/**
	 * Emit string to System.out and return false if DEBUG_DEPRECATIONS active.
	 * This method is typically invoked as assert PivotUtil.debugDeprecation("className.methodName"); so
	 * that with -ea compilation an assertion failure occurs without imposing any costs when -ea not in use.
	 *
	 * @since 7.0
	 */
	public static boolean debugDeprecation(String string) {
		System.out.println("Deprecated method in use: " + string);
		return DEBUG_DEPRECATIONS.isActive() ? false : true; 		// False to crash
	}

	public static void debugObjectUsage(String prefix, EObject element) {
		StringBuilder s = new StringBuilder();
		s.append(prefix);
		if (element != null) {
			s.append(element.eClass().getName());
			s.append("@");
			s.append(Integer.toHexString(element.hashCode()));
			Resource eResource = element.eResource();
			if (eResource != null) {
				if (element instanceof Element) {
					s.append(" ");
					s.append(AS2Moniker.toString((Element) element));
				}
				s.append(" ");
				s.append(eResource.getURI());
			}
			else if (element instanceof NamedElement) {
				s.append(" ");
				s.append(String.valueOf(((NamedElement) element).getName()));
			}
		}
		else {
			s.append("null");
		}
		System.out.println(s.toString());
	}

	/**
	 * @since 7.0
	 */
	public static void debugPrintln(@Nullable Object string) {
		if (!noDebug) {
			System.out.printf("%6.3f [%s] %s\n", 0.001 * (System.currentTimeMillis() - startTime), Thread.currentThread().getName(), String.valueOf(string));
		}
	}

	/**
	 * @since 7.0
	 */
	public static void debugReset() {
		startTime = System.currentTimeMillis();
		if (!noDebug && (contextLine == null)) {
			System.out.println("");
		}
	}

	public static boolean debugWellContainedness(Type type) {
		if (type.eResource() == null) {
			debugObjectUsage("Badly contained ", type);
			return false;
		}
		if (type instanceof CollectionType) {
			Type elementType = ((CollectionType)type).getElementType();
			if ((elementType != null) && !debugWellContainedness(elementType)) {
				debugObjectUsage("Badly contained ", type);
				return false;
			}
		}
		return true;
	}

	/**
	 * @since 7.0
	 */
	public static void errPrintln(@Nullable String string) {
		if (contextLine != null) {
			System.err.println(contextLine);
			contextLine = null;
		}
		System.err.printf("%6.3f [%s] %s\n", 0.001 * (System.currentTimeMillis() - startTime), Thread.currentThread().getName(), String.valueOf(string));
	}

	/**
	 * @since 7.0
	 */
	public static @Nullable MetamodelManager findMetamodelManager(@NonNull Resource resource) {
		EnvironmentFactoryInternal environmentFactory = ThreadLocalExecutor.basicGetEnvironmentFactory();
		if (environmentFactory == null) {
			return null;
		}
		return environmentFactory.getMetamodelManager();
	}

	/**
	 * @since 7.0
	 */
	public static Type findTypeOf(@NonNull MetamodelManager metamodelManager, @NonNull EClassifier eClass) {
		Resource resource = eClass.eResource();
		if (resource != null) {
			External2AS adapter = External2AS.findAdapter(resource, metamodelManager.getEnvironmentFactory());
			if (adapter != null) {
				Type type = adapter.getCreated(Type.class, eClass);
				if (type != null) {
					return type;
				}
			}
		}
		return null;
	}

	public static String formatDiagnostics(@NonNull Diagnostic diagnostic, @NonNull String newLine) {
		StringBuilder s = new StringBuilder();
		formatDiagnostic(s, diagnostic, newLine);
		return s.toString();
	}

	public static void formatDiagnostic(@NonNull StringBuilder s, @NonNull Diagnostic diagnostic, @NonNull String newLine) {
		if (diagnostic.getSeverity() != Diagnostic.OK) {
			s.append(newLine);
			s.append(diagnostic.getSeverity() + " - ");
			String location = diagnostic.getSource();
			if (location != null) {
				s.append(location);
				s.append(": ");
			}
			s.append(diagnostic.getMessage());
		/*	for (Object obj : diagnostic.getData()) {
				s.append(newLine);
				s.append("\t");
				//				if (obj instanceof Throwable) {
				//					s.append(((Throwable)obj).getMessage());
				//				}
				//				else {
				s.append(obj);
				//				}
			} */
			List<?> datas = diagnostic.getData();
			if (datas != null) {
				for (Object data : datas) {
					if (data instanceof Throwable)  {		// FIXME use diagnostic.getException()
						Throwable cause = ((Throwable)data).getCause();
					//	if ((cause != null) && (cause != data)) {
							s.append(newLine + "\t" + (cause != null ? cause : data).toString());
					//	}
					}
				}
			}
			for (Diagnostic childDiagnostic : diagnostic.getChildren()) {
				if (childDiagnostic != null) {
					String childNewLine = newLine + "\t";
					formatDiagnostic(s, childDiagnostic, childNewLine);
				}
			}
		}
	}

	public static String formatResourceDiagnostics(@NonNull List<Resource.Diagnostic> diagnostics, @NonNull String messagePrefix, @NonNull String newLine) {
		if (diagnostics.size() <= 0) {
			return null;
		}
		StringBuilder s = new StringBuilder();
		s.append(messagePrefix);
		for (Resource.Diagnostic diagnostic : diagnostics) {
			if (diagnostic instanceof Diagnostic) {
				formatDiagnostic(s, (Diagnostic)diagnostic, newLine);
			}
			else {
				s.append(newLine);
				String location = diagnostic.getLocation();
				if (location != null) {
					s.append(location);
					s.append(":");
				}
				s.append(diagnostic.getLine());
				try {
					int column = diagnostic.getColumn();
					if (column > 0) {
						s.append(":");
						s.append(column);
					}
				} catch (Exception e) {}	// UnsupportedOperationException was normal for Bug 380232 fixed in Xtext 2.9
				s.append(": ");
				s.append(diagnostic.getMessage());
				if (diagnostic instanceof DiagnosticWrappedException)  {
					Throwable cause = ((DiagnosticWrappedException)diagnostic).getCause();
					if ((cause != null) && (cause != diagnostic)) {
						s.append(" - " + cause.toString());
					}
				}
			}
		}
		return s.toString();
	}

	/**
	 * @since 7.0
	 */
	public static @NonNull URI getASURI(@NonNull URI uri) {
		if (uri.fragment() != null) {
			logger.error("Unexpected fragment ignored for '" + uri.toString() + "'");
			uri = uri.trimFragment();
		}
		URI asURI = uri.appendFileExtension(OCL_AS_FILE_EXTENSION);
		if (!isASURI(asURI)) {
			asURI = uri.appendSegment(DOT_OCL_AS_FILE_EXTENSION);
		}
		assert isASURI(asURI);
		return asURI;
	}

	/**
	 * @since 7.0
	 */
	public static @NonNull Type getActual(@NonNull TemplateParameterSubstitution templateParameterSubstitution) {
		return ClassUtil.requireNonNull(templateParameterSubstitution.getActual());
	}

	/**
	 * @since 1.13
	 */
	public static org.eclipse.ocl.pivot.@Nullable Class getBehavioralClass(@NonNull StandardLibrary standardLibrary, @NonNull Class<?> instanceClass) {
		if (instanceClass == boolean.class) {
			return standardLibrary.getBooleanType();
		}
		if (instanceClass == byte.class) {
			return standardLibrary.getIntegerType();
		}
		if (instanceClass == char.class) {
			return standardLibrary.getIntegerType();
		}
		if (instanceClass == double.class) {
			return standardLibrary.getRealType();
		}
		if (instanceClass == float.class) {
			return standardLibrary.getRealType();
		}
		if (instanceClass == int.class) {
			return standardLibrary.getIntegerType();
		}
		if (instanceClass == long.class) {
			return standardLibrary.getIntegerType();
		}
		if (instanceClass == short.class) {
			return standardLibrary.getIntegerType();
		}
		if (instanceClass == BigDecimal.class) {
			return standardLibrary.getRealType();
		}
		if (instanceClass == BigInteger.class) {
			return standardLibrary.getIntegerType();
		}
		if (instanceClass == Boolean.class) {
			return standardLibrary.getBooleanType();
		}
		if (instanceClass == Byte.class) {
			return standardLibrary.getIntegerType();
		}
		if (instanceClass == Character.class) {
			return standardLibrary.getIntegerType();
		}
		if (instanceClass == Double.class) {
			return standardLibrary.getRealType();
		}
		if (instanceClass == Float.class) {
			return standardLibrary.getRealType();
		}
		if (instanceClass == Integer.class) {
			return standardLibrary.getIntegerType();
		}
		if (instanceClass == Long.class) {
			return standardLibrary.getIntegerType();
		}
		if (instanceClass == Short.class) {
			return standardLibrary.getIntegerType();
		}
		if (instanceClass == String.class) {
			return standardLibrary.getStringType();
		}
		return null;
	}

	/**
	 * @since 1.7
	 */
	@Deprecated /* @deprecated no longer used = behavioralType() now handled within Type::conformsTo */
	public static @NonNull Type getBehavioralType(@NonNull Type type) {
		if (type instanceof CollectionType) {
			CollectionType collectionType = (CollectionType)type;
			Type asElementType = collectionType.getElementType();
			if (asElementType != null) {
				Type behavioralElementType = getBehavioralType(asElementType);
				assert behavioralElementType != null;
				if (behavioralElementType != asElementType) {
					EnvironmentFactoryInternal environmentFactory = ThreadLocalExecutor.basicGetEnvironmentFactory();
					if (environmentFactory != null) {
						CollectionType unspecializedElement = (CollectionType)collectionType.getUnspecializedElement();
						assert unspecializedElement != null;
						boolean isNullFree = collectionType.isIsNullFree();
						IntegerValue lowerValue = collectionType.getLowerValue();
						UnlimitedNaturalValue upperValue = collectionType.getUpperValue();
						return environmentFactory.getStandardLibrary().getCollectionType(unspecializedElement, behavioralElementType, isNullFree, lowerValue, upperValue);
					}
				}
			}
		}
		else if (type instanceof DataType) {
			org.eclipse.ocl.pivot.Class behavioralClass = ((DataType)type).getBehavioralClass();
			return behavioralClass != null ? behavioralClass : type;
		}
		return type;
	}

	/**
	 * Trim a surrounding "result=(...)" to convert a UML BodyCondition to an OCL BodyExpression.
	 * @since 7.0
	 */
	public static @NonNull String getBodyExpression(@NonNull String umlBody) {
		String s = umlBody.trim();
		if (s.startsWith(RESULT_NAME)) {
			s = s.substring(6).trim();
			if (s.startsWith("=")) {
				s = s.substring(1).trim();
				if (s.startsWith("(") && s.endsWith(")")) {
					s = s.substring(1, s.length()-1); //.trim();
				}
				return s;
			}
		}
		return umlBody;
	}

	/**
	 * @since 1.7
	 */
	public static org.eclipse.ocl.pivot.@NonNull Class getClass(@NonNull Type type, @NonNull StandardLibrary standardLibrary) {
		if (type instanceof org.eclipse.ocl.pivot.Class) {
			return (org.eclipse.ocl.pivot.Class)type;
		}
		else if (type instanceof TemplateParameter) {
			return getLowerBound((TemplateParameter)type, standardLibrary.getOclAnyType());
		}
		return standardLibrary.getOclVoidType();			// Never happens
	}

	/**
	 * Return the type of a TypedElement, exploiting the known non-null and non-TypeParameter characteristics.
	 * @throws IllegalStateException for a null type
	 * @throws ClassCastException for a TypeParameter
	 *
	 * @since 1.3
	 */
	public static org.eclipse.ocl.pivot.@NonNull Class getClass(@NonNull TypedElement typedElement) {
		return ClassUtil.requireNonNull((org.eclipse.ocl.pivot.Class)typedElement.getType());
	}

	/**
	 * @since 7.0
	 */
	public static CollectionKind getCollectionKind(@NonNull CollectionType collectionType) {
		if (collectionType instanceof OrderedSetType) {
			return CollectionKind.ORDERED_SET;
		}
		else if (collectionType instanceof SequenceType) {
			return CollectionKind.SEQUENCE;
		}
		else if (collectionType instanceof SetType) {
			return CollectionKind.SET;
		}
		else if (collectionType instanceof BagType) {
			return CollectionKind.BAG;
		}
		else {
			return CollectionKind.COLLECTION;
		}
	}

	/**
	 * @since 7.0
	 */
	public static @NonNull Iterable<org.eclipse.ocl.pivot.@NonNull Class> getConstrainingClasses(@NonNull TemplateParameter asTemplateParameter) {
		return ClassUtil.nullFree(asTemplateParameter.getConstrainingClasses());
	}

	/**
	 * @since 7.0
	 */
	public static @NonNull List<org.eclipse.ocl.pivot.@NonNull Class> getConstrainingClassesList(@NonNull TemplateParameter asTemplateParameter) {
		return ClassUtil.nullFree(asTemplateParameter.getConstrainingClasses());
	}

	public static @Nullable Constraint getContainingConstraint(@Nullable Element element) {
		for (EObject eObject = element; eObject != null; eObject = eObject.eContainer()) {
			if (eObject instanceof Constraint) {
				return (Constraint)eObject;
			}
		}
		return null;
	}

	public static @Nullable ExpressionInOCL getContainingExpressionInOCL(@Nullable Element element) {
		for (EObject eObject = element; eObject != null; eObject = eObject.eContainer()) {
			if (eObject instanceof ExpressionInOCL) {
				return (ExpressionInOCL)eObject;
			}
		}
		return null;
	}

	public static @Nullable Model getContainingModel(@Nullable EObject element) {
		for (EObject eObject = element; eObject != null; eObject = eObject.eContainer()) {
			if (eObject instanceof Model) {
				return (Model)eObject;
			}
		}
		return null;
	}

	public static @NonNull Namespace getContainingNamespace(@NonNull EObject element) {
		return ClassUtil.requireNonNull(basicGetContainingNamespace(element));
	}

	public static @Nullable Operation getContainingOperation(@Nullable EObject element) {
		for (EObject eObject = element; eObject != null; eObject = eObject.eContainer()) {
			if (eObject instanceof Operation) {
				return (Operation)eObject;
			}
		}
		return null;
	}

	public static org.eclipse.ocl.pivot.@Nullable Package getContainingPackage(@Nullable EObject element) {
		for (EObject eObject = element; eObject != null; eObject = eObject.eContainer()) {
			if (eObject instanceof org.eclipse.ocl.pivot.Package) {
				return (org.eclipse.ocl.pivot.Package)eObject;
			}
		}
		return null;
	}

	/**
	 * @since 7.0
	 */
	public static @NonNull TemplateableElement getContainingTemplateableElement(@NonNull EObject element) {
		return ClassUtil.requireNonNull(basicGetContainingTemplateableElement(element));
	}

	public static @Nullable Type getContainingType(@Nullable EObject element) {
		if (element != null) {
			EObject eObject = element;
			while (true) {
				if (eObject instanceof Type) {
					return (Type)eObject;
				}
				EObject eContainer = eObject.eContainer();
				if (eContainer == null) {
					if (eObject instanceof ExpressionInOCL) {
						return ((ExpressionInOCL)eObject).getOwnedContext().getType();
					}
					break;
				}
				eObject = eContainer;
			}
		}
		return null;
	}

	/**
	 * Return the number of containers of eObject, 0 if eObject is a root.
	 */
	public static int getContainmentDepth(EObject eObject) {
		int depth = 0;
		for (EObject eContainer = eObject.eContainer(); eContainer != null; eContainer = eContainer.eContainer()) {
			depth++;
			if (depth > 100000) {
				return depth;
			}
		}
		return depth;
	}

	/**
	 * Return the Java Class used by Ecore for elements of asProperty, or null if not known.
	 */
	public static @Nullable Class<?> getEcoreInstanceClass(@Nullable Property asProperty) {
		Class<?> instanceClass = null;
		if (asProperty != null) {
			EObject eTarget = asProperty.getESObject();
			if (eTarget instanceof EStructuralFeature) {
				EClassifier eType = ((EStructuralFeature)eTarget).getEType();
				if (eType != null) {
					instanceClass = eType.getInstanceClass();
				}
			}
		}
		return instanceClass;
	}

	/**
	 * @since 1.3
	 */
	public static @NonNull Type getElementType(@NonNull CollectionType collectionType) {
		return ClassUtil.requireNonNull(collectionType.getElementType());
	}

	/**
	 * @since 1.3
	 */
	public static @NonNull Type getElementalType(@NonNull Type type) {
		Type elementType = type;
		while (elementType instanceof CollectionType) {
			elementType = ((CollectionType)elementType).getElementType();
			assert elementType != null;
		}
		return elementType;
	}

	/**
	 * @since 7.0
	 */
	public static @NonNull EnvironmentFactoryInternal getEnvironmentFactory(@Nullable Notifier notifier) {
		EnvironmentFactoryInternal environmentFactory = ThreadLocalExecutor.basicGetEnvironmentFactory();
		if (environmentFactory != null) {
			return environmentFactory;
		}
		ProjectManager projectManager = null;
		ResourceSet resourceSet = basicGetResourceSet(notifier);
		if (resourceSet != null) {		// null if working with installed resources
			projectManager = ProjectMap.findAdapter(resourceSet);
		}
		if (projectManager == null) {
			projectManager = ProjectManager.CLASS_PATH;
		}
		environmentFactory = ASResourceFactoryRegistry.INSTANCE.createEnvironmentFactory(projectManager, resourceSet, null);
		ThreadLocalExecutor.setUsesFinalizer();				// auto-created EnvironmentFactory is destroyed by ThreadLocalExecutor.finalize()
		return environmentFactory;
	}

	/**
	 * Locate an OCL Executor from the Resource containing an eObject, else create a default one.
	 *
	 * @since 1.7
	 */
	public static @NonNull Executor getExecutor(@Nullable EObject eObject) {
		Executor executor = ThreadLocalExecutor.basicGetExecutor();
		if (executor != null) {
			return executor;
		}
		if (eObject != null) {
			EnvironmentFactory environmentFactory = ThreadLocalExecutor.basicGetEnvironmentFactory();
			if (environmentFactory != null) {
				executor = new PivotExecutorManager(environmentFactory, eObject);
			}
		}
		if (executor == null) {
			executor = new EcoreExecutorManager(eObject, PivotTables.LIBRARY);
			// This leaks unless caller dispose()s
		}
		ThreadLocalExecutor.setExecutor(executor);
		return executor;
	}

	/**
	 * @since 7.0
	 */
	public static @NonNull TemplateParameter getFormal(@NonNull TemplateParameterSubstitution templateParameterSubstitution) {
		return ClassUtil.requireNonNull(templateParameterSubstitution.getFormal());
	}

	/**
	 * @since 7.0
	 */
	public static @Nullable LibraryFeature getImplementation(@NonNull Operation localOperation) {
		LibraryFeature libraryFeature = localOperation.getImplementation();
		if (libraryFeature != null) {
			return libraryFeature;
		}
		String implementationClassName = localOperation.getImplementationClass();
		if (implementationClassName != null) {
			ClassLoader classLoader = localOperation.getClass().getClassLoader();
			if (classLoader != null) {
				try {
					Class<?> theClass = classLoader.loadClass(implementationClassName);
					if (theClass != null) {
						Field field = theClass.getField("INSTANCE");
						return (LibraryFeature) field.get(null);
					}
				} catch (Exception e) {
				}
			}
		}
		return null;
	}

	/**
	 * @since 1.10
	 */
	public static @NonNull Set<org.eclipse.ocl.pivot.@NonNull Package> getImportedPackageClosure(@NonNull CompleteModel completeModel, org.eclipse.ocl.pivot.@NonNull Package asPackage) {
		Set<org.eclipse.ocl.pivot.@NonNull Package> importedPackageClosure = new HashSet<>();
		getImportedPackageClosure(completeModel, importedPackageClosure, asPackage);
		return importedPackageClosure;
	}

	private static void getImportedPackageClosure(@NonNull CompleteModel completeModel, @NonNull Set<org.eclipse.ocl.pivot.@NonNull Package> importedPackageClosure, org.eclipse.ocl.pivot.@NonNull Package targetPackage) {
		if (importedPackageClosure.add(targetPackage)) {
			CompletePackage completePackage = completeModel.getCompletePackage(targetPackage);
			for (org.eclipse.ocl.pivot.@NonNull Package partialPackage : getPartialPackages(completePackage)) {
				for (org.eclipse.ocl.pivot.@NonNull Package importedPackage : getImportedPackages(partialPackage)) {
					getImportedPackageClosure(completeModel, importedPackageClosure, importedPackage);
				}
			}
		}
	}

	/**
	 * @since 1.10
	 */
	public static @NonNull Iterable<org.eclipse.ocl.pivot.@NonNull Package> getImportedPackages(org.eclipse.ocl.pivot.@NonNull Package asPackage) {
		return ClassUtil.nullFree(asPackage.getImportedPackages());
	}

	/**
	 * @since 1.7
	 */
	public static @NonNull Type getKeyType(@NonNull MapType mapType) {
		return ClassUtil.requireNonNull(mapType.getKeyType());
	}

/*	public static @NonNull Type @NonNull [] getLambdaParameterTypes(@NonNull LambdaType lambdaType) {
		int iParameter = 0;
		List<LambdaParameter> ownedParameters = lambdaType.getOwnedParameters();
		@NonNull Type @NonNull [] parameterTypes = new @NonNull Type[ownedParameters.size() + 2];
		parameterTypes[iParameter++] = getContextType(lambdaType);
		parameterTypes[iParameter++] = getResultType(lambdaType);
		for (LambdaParameter parameter : ownedParameters) {
			parameterTypes[iParameter++] = getType(parameter);
		}
		return parameterTypes;
	} */

	/**
	 * @since 1.7
	 */
	public static org.eclipse.ocl.pivot.@NonNull Class getLowerBound(@NonNull TemplateParameter templateParameter, org.eclipse.ocl.pivot.@NonNull Class oclAnyType) {
		org.eclipse.ocl.pivot.Class lowerBound = basicGetLowerBound(templateParameter);
		return lowerBound != null ? lowerBound : oclAnyType;
	}

	/**
	 * Return the Model at the root of asResource.
	 *
	 * @throws IllegalStateException if none.
	 *
	 * @since 1.3
	 */
	public static @NonNull Model getModel(@NonNull Resource asResource) {
		for (EObject eObject : asResource.getContents()) {
			if (eObject instanceof Model) {
				return (Model)eObject;
			}
		}
		throw new IllegalStateException();
	}

	/**
	 * @since 1.3
	 */
	public static @NonNull String getMultiplicity(@NonNull TypedElement typedElement) {
		StringBuilder s = new StringBuilder();
		Type type = typedElement.getType();
		if (type instanceof CollectionType) {
			CollectionType collectionType = (CollectionType)type;
			Number lower = collectionType.getLower();
			Number upper = collectionType.getUpper();
			StringUtil.appendMultiplicity(s, lower.intValue(), upper instanceof Unlimited ? -1 : upper.intValue(), collectionType.isIsNullFree());
		}
		else {
			s.append(typedElement.isIsRequired() ? "[1]" : "[?]");
		}
		return s.toString();
	}

	/**
	 * @since 1.3
	 */
	public static @NonNull String getName(@NonNull NamedElement namedElement) {
		return ClassUtil.requireNonNull(namedElement.getName());
	}

	public static @Nullable Namespace getNamespace(@Nullable EObject element) {
		for (EObject eObject = element; eObject != null; eObject = eObject.eContainer()) {
			if (eObject instanceof Model) {
				return null;
			}
			if (eObject instanceof Type) {
				return (Namespace) eObject;
			}
			if (eObject instanceof org.eclipse.ocl.pivot.Package) {
				return (Namespace) eObject;
			}
		}
		return null;
	}

	/**
	 * @since 7.0
	 */
	public static @NonNull String getNavigationOperator(boolean isSafe, boolean isAggregate) {
		if (isAggregate) {
			return isSafe ? SAFE_AGGREGATE_NAVIGATION_OPERATOR : AGGREGATE_NAVIGATION_OPERATOR;
		}
		else {
			return isSafe ? SAFE_OBJECT_NAVIGATION_OPERATOR : OBJECT_NAVIGATION_OPERATOR;
		}
	}

	/**
	 * @since 7.0
	 */
	public static @NonNull URI getNonASURI(@NonNull URI uri) {
		assert isASURI(uri);
		return uri.trimFileExtension();
	}

	/**
	 * @since 7.0
	 */
	public static @NonNull Type getNonLambdaType(@NonNull Type type) {
		if (type instanceof LambdaType) {
			LambdaParameter result = getOwnedResult((LambdaType)type);
			type = getType(result);
		}
		return type;
	}

	/**
	 * @since 7.0
	 */
	public static @NonNull <T extends Element> T getNonNullAst(@NonNull Class<T> pivotClass, @NonNull Pivotable pivotableElement) {
		//		if (pivotableElement == null) {
		//			return null;
		//		}
		Element pivotElement = pivotableElement.getPivot();
		if (pivotElement == null) {
			throw new IllegalStateException("Null pivotElementfor a " + pivotClass.getName());
		}
		if (!pivotClass.isAssignableFrom(pivotElement.getClass())) {
			throw new ClassCastException(pivotElement.getClass().getName() + " is not assignable to " + pivotClass.getName());
		}
		@SuppressWarnings("unchecked")
		T castElement = (T) pivotElement;
		return castElement;
	}

	/**
	 * @since 7.0
	 */
	public static @NonNull Type @NonNull [] getOperationParameterTypes(@NonNull Operation anOperation) {
		@NonNull Type @NonNull [] parameterTypes;
		int iParameter = 0;
		List<@NonNull ? extends TypedElement> ownedParameters = getOwnedParametersList(anOperation);
		if (anOperation instanceof Iteration) {
			Iteration anIteration = (Iteration)anOperation;
			List<@NonNull ? extends TypedElement> ownedIterators = getOwnedIteratorsList(anIteration);
			TypedElement ownedAccumulator = anIteration.getOwnedAccumulator();
			parameterTypes = new @NonNull Type[ownedIterators.size() + (ownedAccumulator != null ? 1 : 0) + ownedParameters.size()];
			for (@NonNull TypedElement ownedIterator : ownedIterators) {
				parameterTypes[iParameter++] = getType(ownedIterator);
			}
			if (ownedAccumulator != null) {
				parameterTypes[iParameter++] = getType(ownedAccumulator);
			}
		}
		else {
			parameterTypes = new @NonNull Type[ownedParameters.size()];
		}
		for (@NonNull TypedElement ownedParameter : ownedParameters) {
			parameterTypes[iParameter++] = getType(ownedParameter);
		}
		return parameterTypes;
	}


	/**
	 * @since 1.3
	 */
	public static @NonNull Property getOpposite(@NonNull Property asProperty) {
		return ClassUtil.requireNonNull(asProperty.getOpposite());
	}

	/**
	 * @since 7.0
	 */
	public static @NonNull Parameter getOwnedAccumulator(@NonNull Iteration iteration) {
		return ClassUtil.requireNonNull(iteration.getOwnedAccumulator());
	}

	/**
	 * @since 7.0
	 */
	public static @NonNull Iterable<@NonNull Element> getOwnedAnnotations(org.eclipse.ocl.pivot.@NonNull Package asPackage) {	// XXX Why Element ??
		return ClassUtil.nullFree(asPackage.getOwnedAnnotations());
	}

	/**
	 * @since 1.3
	 */
	public static @NonNull OCLExpression getOwnedArgument(@NonNull OperationCallExp object, int index) {
		return ClassUtil.requireNonNull(object.getOwnedArguments().get(index));
	}

	/**
	 * @since 1.3
	 */
	public static @NonNull Iterable<@NonNull OCLExpression> getOwnedArguments(@NonNull OperationCallExp operationCallExp) {
		return ClassUtil.nullFree(operationCallExp.getOwnedArguments());
	}

	/**
	 * @since 7.0
	 */
	public static @NonNull List<@NonNull OCLExpression> getOwnedArgumentsList(@NonNull OperationCallExp operationCallExp) {
		return ClassUtil.nullFree(operationCallExp.getOwnedArguments());
	}

	/**
	 * @since 1.4
	 */
	public static @NonNull Iterable<@NonNull TemplateBinding> getOwnedBindings(@NonNull TemplateableElement asElement) {
		return ClassUtil.nullFree(asElement.getOwnedBindings());
	}

	/**
	 * @since 7.0
	 */
	public static @NonNull List<@NonNull TemplateBinding> getOwnedBindingsList(@NonNull TemplateableElement templateableElement) {
		return ClassUtil.nullFree(templateableElement.getOwnedBindings());
	}

	/**
	 * @since 7.0
	 */
	public static @NonNull Iterable<@NonNull OCLExpression> getOwnedBodies(@NonNull IterateExp iterateExp) {
		return ClassUtil.nullFree(iterateExp.getOwnedBodies());
	}

	/**
	 * @since 7.0
	 */
	public static @NonNull List<@NonNull OCLExpression> getOwnedBodiesList(@NonNull IterateExp iterateExp) {
		return ClassUtil.nullFree(iterateExp.getOwnedBodies());
	}

	/**
	 * @since 1.3
	 */
	public static @NonNull OCLExpression getOwnedBody(@NonNull ExpressionInOCL asExpression) {
		return ClassUtil.requireNonNull(asExpression.getOwnedBody());
	}

	/**
	 * @since 7.0
	 */
	public static @NonNull OCLExpression getOwnedBody(@NonNull IteratorExp iteratorExp) {
		return ClassUtil.requireNonNull(iteratorExp.getOwnedBody());
	}

	/**
	 * @since 1.3
	 */
	public static @NonNull Iterable<org.eclipse.ocl.pivot.@NonNull Class> getOwnedClasses(org.eclipse.ocl.pivot.@NonNull Package asPackage) {
		return ClassUtil.nullFree(asPackage.getOwnedClasses());
	}

	/**
	 * @since 7.0
	 */
	public static @NonNull List<org.eclipse.ocl.pivot.@NonNull Class> getOwnedClassesList(org.eclipse.ocl.pivot.@NonNull Package asPackage) {
		return ClassUtil.nullFree(asPackage.getOwnedClasses());
	}

	/**
	 * @since 1.6
	 */
	public static @NonNull Iterable<@NonNull IteratorVariable> getOwnedCoIterators(@NonNull LoopExp loopExp) {
		return ClassUtil.nullFree(loopExp.getOwnedCoIterators());
	}

	/**
	 * @since 7.0
	 */
	public static @NonNull List<@NonNull IteratorVariable> getOwnedCoIteratorsList(@NonNull LoopExp loopExp) {
		return ClassUtil.nullFree(loopExp.getOwnedCoIterators());
	}

	/**
	 * @since 1.4
	 */
	public static @NonNull Iterable<@NonNull CompleteClass> getOwnedCompleteClasses(@NonNull CompletePackage completePackage) {
		return ClassUtil.nullFree(completePackage.getOwnedCompleteClasses());
	}

	/**
	 * @since 1.4
	 */
	public static @NonNull Iterable<@NonNull CompletePackage> getOwnedCompletePackages(@NonNull CompletePackage completePackage) {
		return ClassUtil.nullFree(completePackage.getOwnedCompletePackages());
	}

	/**
	 * @since 1.3
	 */
	public static @NonNull Iterable<@NonNull Comment> getOwnedComments(@NonNull Element asElement) {
		return ClassUtil.nullFree(asElement.getOwnedComments());
	}

	/**
	 * @since 1.3
	 */
	public static @NonNull OCLExpression getOwnedCondition(@NonNull IfExp ifExp) {
		return ClassUtil.requireNonNull(ifExp.getOwnedCondition());
	}

	/**
	 * @since 1.4
	 */
	public static @NonNull VariableDeclaration getOwnedContext(@NonNull ExpressionInOCL asExpression) {
		return ClassUtil.requireNonNull(asExpression.getOwnedContext());
	}

	/**
	 * @since 7.0
	 */
	public static @NonNull LambdaParameter getOwnedContext(@NonNull LambdaType asLambdaType) {
		return ClassUtil.requireNonNull(asLambdaType.getOwnedContext());
	}

	/**
	 * @since 1.3
	 */
	public static @NonNull OCLExpression getOwnedElse(@NonNull IfExp ifExp) {
		return ClassUtil.requireNonNull(ifExp.getOwnedElse());
	}

	/**
	 * @since 1.18
	 */
	public static @NonNull Iterable<@NonNull ElementExtension> getOwnedExtensions(Element asElement) {
		return ClassUtil.nullFree(asElement.getOwnedExtensions());
	}

	/**
	 * @since 1.3
	 */
	public static @NonNull OCLExpression getOwnedFirst(@NonNull CollectionRange collectionRange) {
		return ClassUtil.requireNonNull(collectionRange.getOwnedFirst());
	}

	/**
	 * @since 1.3
	 */
	public static @NonNull Iterable<@NonNull Import> getOwnedImports(@NonNull Model asModel) {
		return ClassUtil.nullFree(asModel.getOwnedImports());
	}

	/**
	 * @since 7.0
	 */
	public static @NonNull List<@NonNull Import> getOwnedImportsList(@NonNull Model asModel) {
		return ClassUtil.nullFree(asModel.getOwnedImports());
	}

	/**
	 * @since 1.3
	 */
	public static @NonNull OCLExpression getOwnedIn(@NonNull LetExp letExp) {
		return ClassUtil.requireNonNull(letExp.getOwnedIn());
	}

	/**
	 * @since 1.3
	 */
	public static @NonNull OCLExpression getOwnedInit(@NonNull ShadowPart shadowPart) {
		return ClassUtil.requireNonNull(shadowPart.getOwnedInit());
	}

	/**
	 * @since 1.3
	 */
	public static @NonNull OCLExpression getOwnedInit(@NonNull Variable variable) {
		return ClassUtil.requireNonNull(variable.getOwnedInit());
	}

	/**
	 * @since 1.3
	 */
	public static @NonNull Iterable<@NonNull Constraint> getOwnedInvariants(org.eclipse.ocl.pivot.@NonNull Class asClass) {
		return ClassUtil.nullFree(asClass.getOwnedInvariants());
	}

	/**
	 * @since 1.3
	 */
	public static @NonNull OCLExpression getOwnedItem(@NonNull CollectionItem collectionItem) {
		return ClassUtil.requireNonNull(collectionItem.getOwnedItem());
	}

	/**
	 * @since 1.4
	 */
	public static @NonNull Iterable<@NonNull Parameter> getOwnedIterators(@NonNull Iteration iteration) {
		return ClassUtil.nullFree(iteration.getOwnedIterators());
	}

	/**
	 * @since 1.3
	 */
	public static @NonNull Iterable<@NonNull Variable> getOwnedIterators(@NonNull LoopExp loopExp) {
		return ClassUtil.nullFree(loopExp.getOwnedIterators());
	}

	/**
	 * @since 7.0
	 */
	public static @NonNull List<@NonNull Variable> getOwnedIteratorsList(@NonNull LoopExp loopExp) {
		return ClassUtil.nullFree(loopExp.getOwnedIterators());
	}

	/**
	 * @since 7.0
	 */
	public static @NonNull List<@NonNull Parameter> getOwnedIteratorsList(@NonNull Iteration iteration) {
		return ClassUtil.nullFree(iteration.getOwnedIterators());
	}

	/**
	 * @since 1.3
	 */
	public static @NonNull OCLExpression getOwnedKey(@NonNull MapLiteralPart mapLiteralPart) {
		return ClassUtil.requireNonNull(mapLiteralPart.getOwnedKey());
	}

	/**
	 * @since 1.3
	 */
	public static @NonNull OCLExpression getOwnedLast(@NonNull CollectionRange collectionRange) {
		return ClassUtil.requireNonNull(collectionRange.getOwnedLast());
	}

	/**
	 * @since 1.3
	 */
	public static @NonNull Iterable<@NonNull Operation> getOwnedOperations(org.eclipse.ocl.pivot.@NonNull Class asClass) {
		return ClassUtil.nullFree(asClass.getOwnedOperations());
	}

	/**
	 * @since 7.0
	 */
	public static @NonNull List<@NonNull Operation> getOwnedOperationsList(org.eclipse.ocl.pivot.@NonNull Class asClass) {
		return ClassUtil.nullFree(asClass.getOwnedOperations());
	}

	/**
	 * @since 1.3
	 */
	public static @NonNull Iterable<org.eclipse.ocl.pivot.@NonNull Package> getOwnedPackages(@NonNull Model asModel) {
		return ClassUtil.nullFree(asModel.getOwnedPackages());
	}

	/**
	 * @since 1.3
	 */
	public static @NonNull Iterable<org.eclipse.ocl.pivot.@NonNull Package> getOwnedPackages(org.eclipse.ocl.pivot.@NonNull Package asPackage) {
		return ClassUtil.nullFree(asPackage.getOwnedPackages());
	}

	/**
	 * @since 7.0
	 */
	public static @NonNull List<org.eclipse.ocl.pivot.@NonNull Package> getOwnedPackagesList(@NonNull Model asModel) {
		return ClassUtil.nullFree(asModel.getOwnedPackages());
	}

	/**
	 * @since 7.0
	 */
	public static @NonNull List<org.eclipse.ocl.pivot.@NonNull Package> getOwnedPackagesList(org.eclipse.ocl.pivot.@NonNull Package asPackage) {
		return ClassUtil.nullFree(asPackage.getOwnedPackages());
	}

	/**
	 * @since 1.4
	 */
	public static @NonNull Parameter getOwnedParameter(@NonNull Operation operation, int index) {
		return ClassUtil.requireNonNull(operation.getOwnedParameters().get(index));
	}

	/**
	 * @since 7.0
	 */
	public static @NonNull Iterable<@NonNull LambdaParameter> getOwnedParameters(@NonNull LambdaType lambdaType) {
		return ClassUtil.nullFree(lambdaType.getOwnedParameters());
	}

	/**
	 * @since 1.3
	 */
	public static @NonNull Iterable<@NonNull Parameter> getOwnedParameters(@NonNull Operation operation) {
		return ClassUtil.nullFree(operation.getOwnedParameters());
	}

	/**
	 * @since 1.3
	 */
	public static @NonNull Iterable<@NonNull TemplateParameter> getOwnedParameters(@NonNull TemplateSignature templateSignature) {
		return ClassUtil.nullFree(templateSignature.getOwnedParameters());
	}

	/**
	 * @since 7.0
	 */
	public static @NonNull List<@NonNull Variable> getOwnedParametersList(@NonNull ExpressionInOCL expressionInOCL) {
		return ClassUtil.nullFree(expressionInOCL.getOwnedParameters());
	}

	/**
	 * @since 7.0
	 */
	public static @NonNull List<@NonNull LambdaParameter> getOwnedParametersList(@NonNull LambdaType lambdaType) {
		return ClassUtil.nullFree(lambdaType.getOwnedParameters());
	}

	/**
	 * @since 7.0
	 */
	public static @NonNull List<@NonNull Parameter> getOwnedParametersList(@NonNull Operation operation) {
		return ClassUtil.nullFree(operation.getOwnedParameters());
	}

	/**
	 * @since 7.0
	 */
	public static @NonNull List<@NonNull TemplateParameter> getOwnedParametersList(@NonNull TemplateSignature templateSignature) {
		return ClassUtil.nullFree(templateSignature.getOwnedParameters());
	}

	/**
	 * @since 1.3
	 */
	public static @NonNull Iterable<@NonNull CollectionLiteralPart> getOwnedParts(@NonNull CollectionLiteralExp asCollectionLiteralExp) {
		return ClassUtil.nullFree(asCollectionLiteralExp.getOwnedParts());
	}

	/**
	 * @since 1.3
	 */
	public static @NonNull Iterable<@NonNull TupleLiteralPart> getOwnedParts(@NonNull TupleLiteralExp asTupleLiteralExp) {
		return ClassUtil.nullFree(asTupleLiteralExp.getOwnedParts());
	}

	/**
	 * @since 7.0
	 */
	public static @NonNull List<@NonNull CollectionLiteralPart> getOwnedPartsList(@NonNull CollectionLiteralExp collectionLiteralExp) {
		return ClassUtil.nullFree(collectionLiteralExp.getOwnedParts());
	}

	/**
	 * @since 7.0
	 */
	public static @NonNull List<@NonNull MapLiteralPart> getOwnedPartsList(@NonNull MapLiteralExp mapLiteralExp) {
		return ClassUtil.nullFree(mapLiteralExp.getOwnedParts());
	}

	/**
	 * @since 7.0
	 */
	public static @NonNull List<@NonNull ShadowPart> getOwnedPartsList(@NonNull ShadowExp shadowExp) {
		return ClassUtil.nullFree(shadowExp.getOwnedParts());
	}

	/**
	 * @since 7.0
	 */
	public static @NonNull List<@NonNull TupleLiteralPart> getOwnedPartsList(@NonNull TupleLiteralExp tupleLiteralExp) {
		return ClassUtil.nullFree(tupleLiteralExp.getOwnedParts());
	}

	/**
	 * @since 1.4
	 */
	public static @NonNull Iterable<@NonNull Constraint> getOwnedPostconditions(@NonNull Operation asOperation) {
		return ClassUtil.nullFree(asOperation.getOwnedPostconditions());
	}

	/**
	 * @since 1.4
	 */
	public static @NonNull Iterable<@NonNull Constraint> getOwnedPreconditions(@NonNull Operation asOperation) {
		return ClassUtil.nullFree(asOperation.getOwnedPreconditions());
	}

	/**
	 * @since 1.3
	 */
	public static @NonNull Iterable<@NonNull Property> getOwnedProperties(org.eclipse.ocl.pivot.@NonNull Class asClass) {
		return ClassUtil.nullFree(asClass.getOwnedProperties());
	}

	/**
	 * @since 7.0
	 */
	public static @NonNull List<@NonNull Property> getOwnedPropertiesList(org.eclipse.ocl.pivot.@NonNull Class asClass) {
		return ClassUtil.nullFree(asClass.getOwnedProperties());
	}

	/**
	 * @since 1.3
	 */
	public static @NonNull Variable getOwnedResult(@NonNull IterateExp iterateExp) {
		return ClassUtil.requireNonNull(iterateExp.getOwnedResult());
	}

	/**
	 * @since 7.0
	 */
	public static @NonNull LambdaParameter getOwnedResult(@NonNull LambdaType asLambdaType) {
		return ClassUtil.requireNonNull(asLambdaType.getOwnedResult());
	}

	/**
	 * @since 1.3
	 */
	public static @NonNull OCLExpression getOwnedSource(@NonNull CallExp object) {
		return ClassUtil.requireNonNull(object.getOwnedSource());
	}

	/**
	 * @since 1.4
	 */
	public static @NonNull Iterable<@NonNull TemplateParameterSubstitution> getOwnedSubstitutions(@NonNull TemplateBinding asTemplateBinding) {
		return ClassUtil.nullFree(asTemplateBinding.getOwnedSubstitutions());
	}

	/**
	 * @since 7.0
	 */
	public static @NonNull Iterable<@NonNull TemplateParameterSubstitution> getOwnedSubstitutions(@NonNull TemplateableElement asTemplateableElement) {
		return getOwnedSubstitutions(asTemplateableElement);
	}

	/**
	 * @since 7.0
	 */
	public static @NonNull  List<@NonNull TemplateParameterSubstitution> getOwnedSubstitutionsList(@NonNull TemplateBinding asTemplateBinding) {
		return ClassUtil.nullFree(asTemplateBinding.getOwnedSubstitutions());
	}

	/**
	 * @since 7.0
	 */
	public static @NonNull List<@NonNull TemplateParameterSubstitution> getOwnedSubstitutionsList(@NonNull TemplateableElement asTemplateableElement) {
		List<@NonNull TemplateParameterSubstitution> asTemplateParameterSubstitutions = new ArrayList<>();
		for (@NonNull TemplateBinding asTemplateBinding : getOwnedBindings(asTemplateableElement)) {
			Iterables.addAll(asTemplateParameterSubstitutions, getOwnedSubstitutions(asTemplateBinding));
		}
		return asTemplateParameterSubstitutions;
	}

	/**
	 * @since 1.3
	 */
	public static @NonNull OCLExpression getOwnedThen(@NonNull IfExp ifExp) {
		return ClassUtil.requireNonNull(ifExp.getOwnedThen());
	}

	/**
	 * @since 1.3
	 */
	public static @NonNull OCLExpression getOwnedValue(@NonNull MapLiteralPart mapLiteralPart) {
		return ClassUtil.requireNonNull(mapLiteralPart.getOwnedValue());
	}

	/**
	 * @since 1.3
	 */
	public static @NonNull /*Let*/Variable getOwnedVariable(@NonNull LetExp letExp) {
		return ClassUtil.requireNonNull(letExp.getOwnedVariable());
	}

	/**
	 * @since 7.0
	 */
	public static org.eclipse.ocl.pivot.@NonNull Class getOwningClass(@NonNull Feature feature) {
		return ClassUtil.requireNonNull(feature.getOwningClass());
	}

	/**
	 * @since 1.9
	 */
	public static @NonNull TemplateableElement getOwningElement(@NonNull TemplateSignature templateSignature) {
		return ClassUtil.requireNonNull(templateSignature.getOwningElement());
	}

	/**
	 * @since 1.4
	 */
	public static @NonNull Enumeration getOwningEnumeration(@NonNull EnumerationLiteral enumerationLiteral) {
		return ClassUtil.requireNonNull(enumerationLiteral.getOwningEnumeration());
	}

	/**
	 * @since 1.3
	 */
	public static org.eclipse.ocl.pivot.@NonNull Package getOwningPackage(org.eclipse.ocl.pivot.@NonNull Class asClass) {
		return ClassUtil.requireNonNull(asClass.getOwningPackage());
	}

	/**
	 * @since 1.9
	 */
	public static @NonNull TemplateSignature getOwningSignature(@NonNull TemplateParameter asTemplateParameter) {
		return ClassUtil.requireNonNull(asTemplateParameter.getOwningSignature());
	}

	public static org.eclipse.ocl.pivot.@Nullable Package getPackage(@NonNull EObject object) {
		for (EObject eObject = object; eObject != null; eObject = eObject.eContainer()) {
			if (eObject instanceof org.eclipse.ocl.pivot.Package) {
				return (org.eclipse.ocl.pivot.Package)eObject;
			}
		}
		return null;
	}

	/**
	 * @since 1.3
	 */
	public static @NonNull Iterable<org.eclipse.ocl.pivot.@NonNull Class> getPartialClasses(@NonNull CompleteClass completeClass) {
		return ClassUtil.nullFree(completeClass.getPartialClasses());
	}

	/**
	 * @since 1.10
	 */
	public static @NonNull Iterable<org.eclipse.ocl.pivot.@NonNull Package> getPartialPackages(@NonNull CompletePackage completePackage) {
		return ClassUtil.nullFree(completePackage.getPartialPackages());
	}

	public static @Nullable <T extends Element> T getPivot(@NonNull Class<T> pivotClass, @Nullable Pivotable pivotableElement) {
		if (pivotableElement == null) {
			return null;
		}
		Element pivotElement = pivotableElement.getPivot();
		if (pivotElement == null) {
			return null;
		}
		if (!pivotClass.isAssignableFrom(pivotElement.getClass())) {
			throw new ClassCastException(pivotElement.getClass().getName() + " is not assignable to " + pivotClass.getName());
		}
		@SuppressWarnings("unchecked")
		T castElement = (T) pivotElement;
		return castElement;
	}

	/**
	 * @since 1.4
	 */
	public static @NonNull Iterable<@NonNull Operation> getRedefinedOperations(@NonNull Operation operation) {
		return ClassUtil.nullFree(operation.getRedefinedOperations());
	}

	/**
	 * @since 1.3
	 */
	public static @NonNull Iterable<@NonNull Property> getRedefinedProperties(@NonNull Property property) {
		return ClassUtil.nullFree(property.getRedefinedProperties());
	}

	public static Feature getReferredFeature(CallExp callExp) {
		Feature feature = null;
		if (callExp instanceof LoopExp) {
			feature = ((LoopExp)callExp).getReferredIteration();
		}
		else if (callExp instanceof OperationCallExp) {
			feature = ((OperationCallExp)callExp).getReferredOperation();
		}
		else if (callExp instanceof OppositePropertyCallExp) {
			Property referredOppositeProperty = ((OppositePropertyCallExp)callExp).getReferredProperty();
			feature = referredOppositeProperty != null ? referredOppositeProperty.getOpposite() : null;
		}
		else if (callExp instanceof PropertyCallExp) {
			feature = ((PropertyCallExp)callExp).getReferredProperty();
		}
		return feature;
	}

	/**
	 * @since 1.4
	 */
	public static @NonNull Iteration getReferredIteration(@NonNull LoopExp loopExp) {
		return ClassUtil.requireNonNull(loopExp.getReferredIteration());
	}

	/**
	 * @since 1.4
	 */
	public static @NonNull EnumerationLiteral getReferredLiteral(@NonNull EnumLiteralExp enumLiteralExp) {
		return ClassUtil.requireNonNull(enumLiteralExp.getReferredLiteral());
	}

	public static @NonNull Operation getReferredOperation(@NonNull CallExp callExp) {
		return ClassUtil.requireNonNull(basicGetReferredOperation(callExp));
	}

	/**
	 * @since 1.1
	 */
	public static @NonNull Property getReferredProperty(@NonNull NavigationCallExp navigationCallExp) {
		if (navigationCallExp instanceof PropertyCallExp) {
			return ClassUtil.requireNonNull(((PropertyCallExp)navigationCallExp).getReferredProperty());
		}
		else if (navigationCallExp instanceof OppositePropertyCallExp) {
			Property referredProperty = ClassUtil.requireNonNull(((OppositePropertyCallExp)navigationCallExp).getReferredProperty());
			if (referredProperty.eIsProxy() ) {
				throw new IllegalStateException("Unresolved referred property proxy '" + EcoreUtil.getURI(referredProperty) + "' at '" + EcoreUtil.getURI(navigationCallExp) + "'");
			}
			return ClassUtil.requireNonNull(referredProperty.getOpposite());
		}
		else {
			throw new IllegalStateException();
		}
	}

	/**
	 * @since 1.10
	 */
	public static @NonNull Property getReferredProperty(@NonNull ShadowPart shadowPart) {
		return ClassUtil.requireNonNull(shadowPart.getReferredProperty());
	}

	/**
	 * @since 1.3
	 */
	public static @NonNull Type getReferredType(@NonNull TypeExp typeExp) {
		return ClassUtil.requireNonNull(typeExp.getReferredType());
	}

	/**
	 * @since 1.3
	 */
	public static @NonNull VariableDeclaration getReferredVariable(@NonNull VariableExp variableExp) {
		return ClassUtil.requireNonNull(variableExp.getReferredVariable());
	}

	/**
	 * @since 1.3
	 */
	public static @NonNull Resource getResource(@NonNull EObject eObject) {
		return ClassUtil.requireNonNull(eObject.eResource());
	}

	/**
	 * @since 1.4
	 */
	public static int getSeverity(@NonNull EnvironmentFactory environmentFactory) {
		Severity severity = environmentFactory.getValue(PivotValidationOptions.EcoreValidation);
		if (severity != null) {
			switch (severity) {
				case ERROR: return Diagnostic.ERROR;
				case IGNORE: return Diagnostic.OK;
				case INFO: return Diagnostic.INFO;
				case WARNING: return Diagnostic.WARNING;
			}
		}
		return Diagnostic.ERROR;
	}

	/**
	 * @since 7.0
	 */
	public static @NonNull String getSpecificationRole(@NonNull LanguageExpression asSpecification) {
		Constraint asConstraint = asSpecification.getOwningConstraint();
		if (asConstraint != null) {
			EReference eContainmentFeature = asConstraint.eContainmentFeature();
			if (eContainmentFeature == PivotPackage.Literals.CLASS__OWNED_INVARIANTS) {
				return PivotConstantsInternal.INVARIANT_ROLE;
			}
			else if (eContainmentFeature == PivotPackage.Literals.NAMESPACE__OWNED_CONSTRAINTS) {
				return PivotConstantsInternal.CONSTRAINT_ROLE;
			}
			else if (eContainmentFeature == PivotPackage.Literals.OPERATION__OWNED_PRECONDITIONS) {
				return PivotConstantsInternal.PRECONDITION_ROLE;
			}
			else if (eContainmentFeature == PivotPackage.Literals.OPERATION__OWNED_POSTCONDITIONS) {
				return PivotConstantsInternal.POSTCONDITION_ROLE;
			}
		}
		else {
			EReference eContainmentFeature = asSpecification.eContainmentFeature();
			if (eContainmentFeature == PivotPackage.Literals.PROPERTY__OWNED_EXPRESSION) {
				return PivotConstantsInternal.INITIALIZER_ROLE;
			}
			else if (eContainmentFeature == PivotPackage.Literals.OPERATION__BODY_EXPRESSION) {
				return PivotConstantsInternal.BODY_ROLE;
			}
		}
		return PivotConstantsInternal.UNKNOWN_ROLE;
	}

	/**
	 * @since 7.0
	 */
	public static @Nullable Property getStatusTupleTypeStatusPart(@NonNull TupleType tupleType) {
		Property statusPart = NameUtil.getNameable(tupleType.getOwnedProperties(), STATUS_PART_NAME);
		if (statusPart == null) {
			return null;
		}
		return statusPart.getTypeId() == TypeId.BOOLEAN ? statusPart : null;
	}

	/**
	 * @since 7.0
	 */
	public static String getStereotype(@NonNull Constraint object) {
		EStructuralFeature eContainingFeature = object.eContainingFeature();
		if (eContainingFeature == PivotPackage.Literals.CLASS__OWNED_INVARIANTS) {
			return INVARIANT_NAME;
		}
		else if (eContainingFeature == PivotPackage.Literals.OPERATION__BODY_EXPRESSION) {
			return BODY_NAME;
		}
		else if (eContainingFeature == PivotPackage.Literals.OPERATION__OWNED_POSTCONDITIONS) {
			return POSTCONDITION_NAME;
		}
		else if (eContainingFeature == PivotPackage.Literals.OPERATION__OWNED_PRECONDITIONS) {
			return PRECONDITION_NAME;
		}
		else if (eContainingFeature == PivotPackage.Literals.PROPERTY__OWNED_EXPRESSION) {
			return DERIVATION_NAME;
		}
		return "";
	}

	/**
	 * @since 1.3
	 */
	public static @NonNull Iterable<org.eclipse.ocl.pivot.@NonNull Class> getSuperClasses(org.eclipse.ocl.pivot.@NonNull Class asClass) {
		return ClassUtil.nullFree(asClass.getSuperClasses());
	}

	/**
	 * @since 7.0
	 */
	public static @NonNull List<org.eclipse.ocl.pivot.@NonNull Class> getSuperClassesList(org.eclipse.ocl.pivot.@NonNull Class asClass) {
		return ClassUtil.nullFree(asClass.getSuperClasses());
	}

	/**
	 * Return the type of a TypedElement, exploiting the known non-null characteristics.
	 * @throws IllegalStateException for a null type
	 *
	 * @since 1.3
	 */
	public static @NonNull Type getType(@NonNull TypedElement typedElement) {
		return ClassUtil.requireNonNull(typedElement.getType());
	}

	/**
	 * Return the type of a TupleLiteralExp, exploiting the known non-null characteristics.
	 * @throws IllegalStateException for a null type
	 *
	 * @since 1.3
	 */
	public static @NonNull TupleType getType(@NonNull TupleLiteralExp tupleLiteralExp) {
		return ClassUtil.requireNonNull((TupleType)tupleLiteralExp.getType());
	}

	/**
	 * @since 7.0
	 */		// XXX FIXME resolve getType() conflict.
	public static @NonNull Type getTypeInternal(@NonNull TypedElement typedElement) {
		Type type = getType(typedElement);
		//		type = getType(type);
		type = getNonLambdaType(type);
		if (type instanceof SelfType) {
			if (typedElement instanceof Parameter) {
				Operation operation = ((Parameter)typedElement).getOwningOperation();
				if (operation != null) {
					org.eclipse.ocl.pivot.Class selfType = operation.getOwningClass();
					if (selfType != null) {
						type = selfType;
					}
				}
			}
		}
		return type;
	}

	public static @NonNull <T extends TemplateableElement> T getUnspecializedTemplateableElement(@NonNull T templateableElement) {
		//		if (templateableElement == null) {
		//			return null;
		//		}
		TemplateableElement unspecializedElement = templateableElement.getUnspecializedElement();
		if (unspecializedElement == null) {
			return templateableElement;
		}
		@SuppressWarnings("unchecked")
		T castUnspecializedElement = (T) unspecializedElement;
		return castUnspecializedElement;
	}

	/**
	 * @since 1.7
	 */
	public static @NonNull Type getValueType(@NonNull MapType mapType) {
		return ClassUtil.requireNonNull(mapType.getValueType());
	}

	public static @NonNull Operation initOperation(@NonNull Operation asOperation, @NonNull ExpressionInOCL asExpressionInOCL) {
		for (Variable asParameterVariable : asExpressionInOCL.getOwnedParameters()) {
			String parameterName = ClassUtil.requireNonNull(asParameterVariable.getName());
			Type parameterType = ClassUtil.requireNonNull(asParameterVariable.getType());
			Parameter asParameter = createParameter(parameterName, parameterType, asParameterVariable.isIsRequired());
			asParameterVariable.setRepresentedParameter(asParameter);
			asOperation.getOwnedParameters().add(asParameter);
		}
		asOperation.setBodyExpression(asExpressionInOCL);
		asOperation.setType(asExpressionInOCL.getType());
		asOperation.setIsRequired(asExpressionInOCL.isIsRequired());
		return asOperation;
	}

	/**
	 * The default ResourceSet.getLoadOptions() do not support loading models that reference themselves.
	 * Setting XMLResource.OPTION_DEFER_IDREF_RESOLUTION to true avoids this problem.
	 * See Bug 499442.
	 * @since 1.3
	 */
	public static void initializeLoadOptionsToSupportSelfReferences(@NonNull ResourceSet resourceSet) {
		resourceSet.getLoadOptions().put(XMLResource.OPTION_DEFER_IDREF_RESOLUTION, true);
	}

	/**
	 * @since 7.0
	 */
	public static boolean isASURI(@Nullable URI uri) {
		if (uri == null) {
			return false;
		}
		String fileExtension = uri.fileExtension();
		if (fileExtension == null) {
			return false;
		}
		return fileExtension.endsWith(AS_EXTENSION_SUFFIX);
	}

	/**
	 * Return true if type uses an aggregate (-&gt;) rather than object (.) navigation operator.
	 */
	public static boolean isAggregate(Type type) {
		return type instanceof IterableType;
	}

	public static boolean isAggregateNavigationOperator(/*@NonNull*/ String operatorName) {
		return AGGREGATE_NAVIGATION_OPERATOR.equals(operatorName)
				|| SAFE_AGGREGATE_NAVIGATION_OPERATOR.equals(operatorName);
	}

	/**
	 * @since 1.11
	 */
	public static boolean isDataType(@NonNull CompleteClass completeClass) {
		return completeClass.getPrimaryClass() instanceof DataType;
	}

	/**
	 * Return true if the sole purpose of asPackage is to host implicit opposite properties.
	 * @since 7.0
	 */
	public static boolean isImplicitPackage(org.eclipse.ocl.pivot.@NonNull Package asPackage) {
		boolean hasImplicits = false;
		if (!asPackage.getOwnedAnnotations().isEmpty()) {
			return false;
		}
		if (!asPackage.getOwnedComments().isEmpty()) {
			return false;
		}
		if (!asPackage.getOwnedConstraints().isEmpty()) {
			return false;
		}
		if (!asPackage.getOwnedExtensions().isEmpty()) {
			return false;
		}
		if (!asPackage.getOwnedInstances().isEmpty()) {
			return false;
		}
		if (!asPackage.getOwnedPackages().isEmpty()) {
			return false;
		}
		for (org.eclipse.ocl.pivot.Class asClass : asPackage.getOwnedClasses()) {
			if (!asClass.getOwnedAnnotations().isEmpty()) {
				return false;
			}
			if (!asClass.getOwnedBehaviors().isEmpty()) {
				return false;
			}
			if (!asClass.getOwnedBindings().isEmpty()) {
				return false;
			}
			if (!asClass.getOwnedComments().isEmpty()) {
				return false;
			}
			if (!asClass.getOwnedConstraints().isEmpty()) {
				return false;
			}
			if (!asClass.getOwnedExtensions().isEmpty()) {
				return false;
			}
			if (!asClass.getOwnedInvariants().isEmpty()) {
				return false;
			}
			if (!asClass.getOwnedOperations().isEmpty()) {
				return false;
			}
			for (Property asProperty : asClass.getOwnedProperties()) {
				if (!asProperty.isIsImplicit()) {
					return false;
				}
				hasImplicits = true;
			}
		}
		return hasImplicits;
	}

	/**
	 * Return true if the testNameSuffix system property has been set to indicate tests are
	 * running under the supervision of the maven-surefire-plugin..
	 * @since 7.0
	 */
	public static boolean isMavenSurefire() {
		String testNameSuffix = System.getProperty("testNameSuffix", "");
		return (testNameSuffix != null) && testNameSuffix.startsWith("maven");
	}

	public static boolean isObjectNavigationOperator(/*@NonNull*/ String operatorName) {
		return OBJECT_NAVIGATION_OPERATOR.equals(operatorName)
				|| SAFE_OBJECT_NAVIGATION_OPERATOR.equals(operatorName);
	}

	/**
	 * Return  true if this is a synthetic property whose definition is provided by the Orphanage.
	 * @since 7.0
	 */
	public static boolean isOrphanProperty(@NonNull Property property) {
		if (property.getOwningClass() instanceof TupleType) {
			return true;
		}
		return false;
	}

	/**
	 * Return  true if this is a synthetic type whose definition is provided by the Orphanage.
	 * @since 7.0
	 */
	public static boolean isOrphanType(@NonNull Type type) {	// FIXME org.eclipse.ocl.pivot.Class
		if (type instanceof LambdaType) {
			return true;
		}
		else if (type instanceof TupleType) {
			return true;
		}
		else if (type instanceof TemplateableElement){
			return ((TemplateableElement)type).getOwnedBindings().size() > 0;
		}
		else {
			return false;
		}
	}

	public static boolean isSafeNavigationOperator(/*@NonNull*/ String operatorName) {
		return SAFE_AGGREGATE_NAVIGATION_OPERATOR.equals(operatorName)
				|| SAFE_OBJECT_NAVIGATION_OPERATOR.equals(operatorName);
	}

	/**
	 * @since 1.1
	 */
	public static boolean isSameOperation(@NonNull OperationId operationId1, @NonNull OperationId operationId2) {
		if (operationId1 == operationId2) {
			return true;
		}
		if (!operationId1.getName().equals(operationId2.getName())) {
			return false;
		}
		if (!operationId1.getParametersId().equals(operationId2.getParametersId())) {
			return false;
		}
		return true;
	}

	/**
	 * Return true if the testNameSuffix system property has been set to indicate tests are
	 * running under the supervision of the tycho-surefire-plugin..
	 * @since 7.0
	 */
	public static boolean isTychoSurefire() {
		String testNameSuffix = System.getProperty("testNameSuffix", "");
		return (testNameSuffix != null) && testNameSuffix.startsWith("tycho");
	}

	/**
	 * @since 7.0
	 */
	public static boolean isValidIdentifier(@Nullable String value) {
		if (value == null) {
			return false;
		}
		int iMax = value.length();
		if (iMax <= 0) {
			return false;
		}
		for (int i = 0; i < iMax; i++) {
			char c = value.charAt(i);
			if (('A' <= c) && (c <= 'Z')) {
			}
			else if (('a' <= c) && (c <= 'z')) {
			}
			else if (c == '_') {
			}
			else if (('0' <= c) && (c <= '9') && (i > 0)) {
			}
			else {
				return false;
			}
		}
		return true;
	}

	/**
	 * @since 7.0
	 */
	public static <T extends EObject> void refreshList(@Nullable List<? super T> oldElements, @Nullable List<? extends T> newElements) {
		if (oldElements == null) {
			return;			// Never happens but avoids need for null validation in caller
		}
		if (newElements == null) {
			if (oldElements.size() > 0) {
				oldElements.clear();
			}
			return;
		}
		for (int k = newElements.size(); k-- > 0; ) {
			T newElement = newElements.get(k);
			if ((newElement != null) && newElement.eIsProxy()) {
				oldElements.remove(newElement);			// Lose oldContent before adding possible 'duplicates'
			}
		}
		for (int k = oldElements.size(); k-- > 0; ) {
			Object oldElement = oldElements.get(k);
			if (!newElements.contains(oldElement)) {
				oldElements.remove(k);			// Lose oldContent before adding possible 'duplicates'
			}
		}
		boolean hasDuplicates = false;
		int newMax = newElements.size();
		for (int i = 0; i < newMax; i++) {					// Invariant: lists are equal up to index i
			T newElement = newElements.get(i);
			int oldMax = oldElements.size();
			boolean reused = false;;
			for (int j = i; j < oldMax; j++) {
				Object oldElement = oldElements.get(j);
				if (oldElement == newElement) {
					if (j != i) {
						oldElements.remove(j);
						oldElements.add(i, newElement);
					}
					reused = true;
					break;
				}
			}
			if (!reused) {
				if (i < oldMax) {
					oldElements.add(i, newElement);
				}
				else {
					if (!oldElements.add(newElement)) {
						hasDuplicates = true;
					}
				}
			}
			assert hasDuplicates || (newElements.get(i) == oldElements.get(i));
		}
		for (int k = oldElements.size(); k > newMax; ) {
			oldElements.remove(--k);
		}
		assert hasDuplicates || (newElements.size() == oldElements.size());
	}

	/**
	 * @since 7.0
	 */
	public static void refreshName(@NonNull NamedElement pivotNamedElement, @Nullable String newName) {
		String oldName = pivotNamedElement.getName();
		if ((newName != oldName) && ((newName == null) || !newName.equals(oldName))) {
			pivotNamedElement.setName(newName);
		}
	}

	/**
	 * @since 7.0
	 */
	public static void refreshNsURI(org.eclipse.ocl.pivot.@NonNull Package pivotPackage, String newNsURI) {
		String oldNsURI = pivotPackage.getURI();
		if ((newNsURI != oldNsURI) && ((newNsURI == null) || !newNsURI.equals(oldNsURI))) {
			pivotPackage.setURI(newNsURI);
		}
	}

	/**
	 * @since 7.0
	 */
	public static <T extends EObject> void refreshSet(@Nullable List<? super T> oldElements, @Nullable Collection<? extends T> newElements) {
		if (oldElements == null) {
			return;			// Never happens but avoids need for null validation in caller
		}
		if (newElements == null) {
			oldElements.clear();
			return;
		}
		for (int i = oldElements.size(); i-- > 0;) {	// Remove any oldElements not in newElements
			Object oldElement = oldElements.get(i);
			if (!newElements.contains(oldElement)) {
				oldElements.remove(i);
			}
		}
		for (T newElement : newElements) {				// Add any newElements not in oldElements
			if ((newElement != null) && !newElement.eIsProxy() && !oldElements.contains(newElement)) {
				oldElements.add(newElement);
			}
		}
	}

	/**
	 * Replace oldChild at its eContainer.eContainmentFeature by newChild.
	 * @since 1.3
	 */
	public static void replaceChild(@NonNull EObject oldChild, @NonNull EObject newChild) {
		EObject eContainer = oldChild.eContainer();
		EReference eContainmentFeature = oldChild.eContainmentFeature();
		if (eContainmentFeature.isMany()) {
			@SuppressWarnings("unchecked") EList<EObject> list = (EList<EObject>)eContainer.eGet(eContainmentFeature);
			int index = list.indexOf(oldChild);
			assert index >= 0;
			resetContainer(oldChild);
			list.add(index, newChild);
		}
		else {
			resetContainer(oldChild);
			eContainer.eSet(eContainmentFeature, newChild);
		}
	}


	/**
	 * Detach object from its container so that a child-stealing detection is avoided when attaching to a new container.
	 * @since 7.0
	 */
	public static void resetContainer(@NonNull EObject eObject) {
		EStructuralFeature eContainingFeature = eObject.eContainingFeature();
		if (eContainingFeature != null) {
			EObject eContainer = eObject.eContainer();
			if (eContainer != null) {
				if (!eContainingFeature.isMany()) {
					eContainer.eSet(eContainingFeature, null);
				}
				else {
					Object objects = eContainer.eGet(eContainingFeature);
					if (objects instanceof List<?>) {
						((List<?>)objects).remove(eObject);
					}
				}
			}
		}
	}
	/**
	 * Eliminate the executor for this thread in order to force a new Executor and ModelManager to be
	 * created. This is necessary to lose the caches created prior to a model change. It may also be
	 * invoked to avoid waiting for a stale Exector to be garbage collected.
	 *
	 * @since 1.14
	 */
	public static void resetExecutor() {
		ThreadLocalExecutor.reset();
	}

	/**
	 * Define oclExpression as the bodyExpression of an expressionInOCL, and if non-null
	 * also define stringExpression as the OCL-languaged body.
	 */
	public static void setBody(@NonNull ExpressionInOCL expressionInOCL, @Nullable OCLExpression oclExpression, @Nullable String stringExpression) {
		expressionInOCL.setBody(stringExpression != null ? StringUtil.trimSingleLineText(stringExpression) : stringExpression);
		expressionInOCL.setOwnedBody(oclExpression);
		expressionInOCL.setType(oclExpression != null ? oclExpression.getType() : null);
		expressionInOCL.setIsRequired((oclExpression != null) && oclExpression.isIsRequired());;
	}

	/**
	 * @since 7.0
	 */
	public static @NonNull URI trimASExtensionSuffix(@NonNull URI uri) {
		String fileExtension = uri.fileExtension();
		assert fileExtension.endsWith(AS_EXTENSION_SUFFIX);
		return uri.trimFileExtension().appendFileExtension(fileExtension.substring(0, fileExtension.length() - AS_EXTENSION_SUFFIX.length()));
	}
}
