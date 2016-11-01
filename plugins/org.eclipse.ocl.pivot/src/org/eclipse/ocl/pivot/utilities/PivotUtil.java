/*******************************************************************************
 * Copyright (c) 2010, 2016 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   E.D.Willink - initial API and implementation
 * 	 E.D.Willink (Obeo) - Bug 416287 - tuple-valued constraints
 * 	 E.D.Willink (CEA LIST) - Bug 425799 - validity view
 *******************************************************************************/
package org.eclipse.ocl.pivot.utilities;

import java.util.Comparator;
import java.util.List;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.AnyType;
import org.eclipse.ocl.pivot.AssociativityKind;
import org.eclipse.ocl.pivot.BagType;
import org.eclipse.ocl.pivot.CallExp;
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.CompletePackage;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.DataType;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.Enumeration;
import org.eclipse.ocl.pivot.EnumerationLiteral;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.Feature;
import org.eclipse.ocl.pivot.InvalidType;
import org.eclipse.ocl.pivot.Iteration;
import org.eclipse.ocl.pivot.LambdaType;
import org.eclipse.ocl.pivot.LetExp;
import org.eclipse.ocl.pivot.LoopExp;
import org.eclipse.ocl.pivot.MapType;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.Namespace;
import org.eclipse.ocl.pivot.NavigationCallExp;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.OppositePropertyCallExp;
import org.eclipse.ocl.pivot.OrderedSetType;
import org.eclipse.ocl.pivot.Package;
import org.eclipse.ocl.pivot.Parameter;
import org.eclipse.ocl.pivot.PivotFactory;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.Precedence;
import org.eclipse.ocl.pivot.PrimitiveType;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.PropertyCallExp;
import org.eclipse.ocl.pivot.SelfType;
import org.eclipse.ocl.pivot.SequenceType;
import org.eclipse.ocl.pivot.SetType;
import org.eclipse.ocl.pivot.StringLiteralExp;
import org.eclipse.ocl.pivot.TemplateBinding;
import org.eclipse.ocl.pivot.TemplateParameter;
import org.eclipse.ocl.pivot.TemplateParameterSubstitution;
import org.eclipse.ocl.pivot.TemplateSignature;
import org.eclipse.ocl.pivot.TemplateableElement;
import org.eclipse.ocl.pivot.TupleType;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.Variable;
import org.eclipse.ocl.pivot.VariableDeclaration;
import org.eclipse.ocl.pivot.VariableExp;
import org.eclipse.ocl.pivot.VoidType;
import org.eclipse.ocl.pivot.ids.OperationId;
import org.eclipse.ocl.pivot.ids.PackageId;
import org.eclipse.ocl.pivot.internal.PackageImpl;
import org.eclipse.ocl.pivot.internal.resource.EnvironmentFactoryAdapter;
import org.eclipse.ocl.pivot.internal.scoping.EnvironmentView;
import org.eclipse.ocl.pivot.internal.utilities.AS2Moniker;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.internal.utilities.OCLInternal;
import org.eclipse.ocl.pivot.internal.utilities.PivotObjectImpl;
import org.eclipse.ocl.pivot.library.LibraryFeature;
import org.eclipse.ocl.pivot.resource.CSResource;
import org.eclipse.ocl.pivot.values.InvalidValueException;


public class PivotUtil
{
	/**
	 * 'Highest' precedence first
	 */
	public static class PrecedenceComparator implements Comparator<Precedence>
	{
		public static final PrecedenceComparator INSTANCE = new PrecedenceComparator();

		@Override
		public int compare(Precedence p1, Precedence p2) {
			int o1 = p1 != null ? p1.getOrder().intValue() : -1;
			int o2 = p2 != null ? p2.getOrder().intValue() : -1;
			return o1 - o2; // NB least positive is highest precedence
		}
	}

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
	 * Check that expressionInOCL was successfully compiled. Throws an InvalidValueException explaining the problem
	 * if expressionInOCL has no contextVariable and has a StringLiteralExp bodyExpression.
	 */
	public static void checkExpression(@NonNull ExpressionInOCL expressionInOCL) {
		Variable contextVariable = expressionInOCL.getOwnedContext();
		if (contextVariable == null) {
			OCLExpression bodyExpression = expressionInOCL.getOwnedBody();
			if (bodyExpression instanceof StringLiteralExp) {
				throw new InvalidValueException(((StringLiteralExp)bodyExpression).getStringSymbol());
			}
		}
	}

	public static void checkResourceErrors(@NonNull String message, @NonNull Resource resource) throws ParserException {
		List<Resource.Diagnostic> errors = resource.getErrors();
		if (errors.size() > 0) {
			throw new SemanticException(formatResourceDiagnostics(ClassUtil.nonNullEMF(resource.getErrors()), message, "\n"));
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

	public static @NonNull BagType createBagType(@NonNull BagType unspecializedType, @NonNull Type elementType) {
		return createCollectionType(PivotFactory.eINSTANCE.createBagType(), unspecializedType, elementType);
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

	public static @NonNull CollectionType createCollectionType(@NonNull CollectionType unspecializedType, @NonNull Type elementType) {
		return createCollectionType(PivotFactory.eINSTANCE.createCollectionType(), unspecializedType, elementType);
	}

	protected static @NonNull <@NonNull T extends CollectionType> T createCollectionType(/*@NonNull*/ T specializedType, @NonNull T unspecializedType, @NonNull Type instanceType) {
		specializedType.setName(unspecializedType.getName());
		specializedType.setLower(unspecializedType.getLower());
		specializedType.setUpper(unspecializedType.getUpper());
		specializedType.setUnspecializedElement(unspecializedType);
		specializedType.setElementType(instanceType);
		return specializedType;
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

	public static @NonNull ExpressionInOCL createExpressionInOCL(@Nullable Variable asContextVariable, @NonNull OCLExpression asExpression, /*@NonNUll*/ Variable... asParameterVariables) {
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

	public static @NonNull ExpressionInOCL createExpressionInOCLError(@NonNull String string) {
		ExpressionInOCL expressionInOCL = PivotFactory.eINSTANCE.createExpressionInOCL();
		StringLiteralExp stringLiteral = PivotFactory.eINSTANCE.createStringLiteralExp();
		stringLiteral.setStringSymbol(string); //createTupleValuedConstraint("false", null, string));
		expressionInOCL.setOwnedBody(stringLiteral);
		expressionInOCL.setType(stringLiteral.getType());
		return expressionInOCL;
	}

	public static @NonNull InvalidType createInvalidType(@NonNull String name) {
		InvalidType pivotType = PivotFactory.eINSTANCE.createInvalidType();
		pivotType.setName(name);
		return pivotType;
	}

	public static @NonNull Iteration createIteration(@NonNull String name, @NonNull Type type, @Nullable String implementationClass, @NonNull LibraryFeature implementation) {
		Iteration pivotIteration = PivotFactory.eINSTANCE.createIteration();
		pivotIteration.setName(name);
		pivotIteration.setType(type);
		pivotIteration.setImplementationClass(implementationClass);
		pivotIteration.setImplementation(implementation);
		return pivotIteration;
	}

	public static @NonNull LambdaType createLambdaType(@NonNull String name) {
		LambdaType pivotType = PivotFactory.eINSTANCE.createLambdaType();
		pivotType.setName(name);
		return pivotType;
	}

	public static @NonNull LetExp createLetExp(@NonNull Variable asVariable, @NonNull OCLExpression asIn) {
		LetExp asLetExp = PivotFactory.eINSTANCE.createLetExp();
		asLetExp.setOwnedIn(asIn);
		asLetExp.setType(asIn.getType());
		asLetExp.setIsRequired(asIn.isIsRequired());
		asLetExp.setOwnedVariable(asVariable);
		return asLetExp;
	}

	//	public static @NonNull MapType createMapType(@NonNull String name) {
	//		MapType pivotType = PivotFactory.eINSTANCE.createMapType();
	//		pivotType.setName(name);
	//		((PivotObjectImpl)pivotType).setESObject(eDataType);
	//		return pivotType;
	//	}

	public static @NonNull Model createModel(String externalURI) {
		Model pivotModel = PivotFactory.eINSTANCE.createModel();
		pivotModel.setExternalURI(externalURI);
		return pivotModel;
	}

	public static @NonNull MapType createMapType(@NonNull MapType unspecializedType, @NonNull Type keyType, @NonNull Type valueType) {
		return createMapType(PivotFactory.eINSTANCE.createMapType(), unspecializedType, keyType, valueType);
	}

	protected static @NonNull <@NonNull T extends MapType> T createMapType(T specializedType, T unspecializedType, @NonNull Type keyType, @NonNull Type valueType) {
		specializedType.setName(unspecializedType.getName());
		specializedType.setUnspecializedElement(unspecializedType);
		specializedType.setKeyType(keyType);
		specializedType.setValueType(valueType);
		return specializedType;
	}

	public static @NonNull <T extends Model> T createModel(@NonNull Class<T> pivotClass, /*@NonNull*/ EClass pivotEClass, String externalURI) {
		assert pivotEClass != null;
		@SuppressWarnings("unchecked")
		T pivotModel = (T) pivotEClass.getEPackage().getEFactoryInstance().create(pivotEClass);
		pivotModel.setExternalURI(externalURI);
		return pivotModel;
	}

	/**
	 * @since 1.1
	 */
	public static @NonNull NavigationCallExp createNavigationCallExp(@NonNull OCLExpression asSource, @NonNull Property asProperty) {
		NavigationCallExp asNavigationCallExp;
		if (asProperty.isIsImplicit()) {
			OppositePropertyCallExp asCallExp = PivotFactory.eINSTANCE.createOppositePropertyCallExp();
			asCallExp.setReferredProperty(asProperty.getOpposite());
			asNavigationCallExp = asCallExp;
		}
		else {
			PropertyCallExp asCallExp = PivotFactory.eINSTANCE.createPropertyCallExp();
			asCallExp.setReferredProperty(asProperty);
			asNavigationCallExp = asCallExp;
		}
		asNavigationCallExp.setOwnedSource(asSource);
		asNavigationCallExp.setType(asProperty.getType());
		asNavigationCallExp.setIsRequired(asProperty.isIsRequired());
		return asNavigationCallExp;
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

	public static @NonNull OperationCallExp createOperationCallExp(@NonNull OCLExpression asSource, @NonNull Operation asOperation, /*@NonNull*/ OCLExpression... asArguments) {
		OperationCallExp asCallExp = PivotFactory.eINSTANCE.createOperationCallExp();
		asCallExp.setReferredOperation(asOperation);
		asCallExp.setOwnedSource(asSource);
		if (asArguments != null) {
			List<OCLExpression> asCallArguments = asCallExp.getOwnedArguments();
			for (OCLExpression asArgument : asArguments) {
				asCallArguments.add(ClassUtil.nonNullState(asArgument));
			}
		}
		asCallExp.setType(asOperation.getType());
		asCallExp.setIsRequired(asOperation.isIsRequired());
		return asCallExp;
	}

	public static @NonNull OrderedSetType createOrderedSetType(@NonNull OrderedSetType unspecializedType, @NonNull Type elementType) {
		return createCollectionType(PivotFactory.eINSTANCE.createOrderedSetType(), unspecializedType, elementType);
	}

	public static org.eclipse.ocl.pivot.@NonNull Package createOwnedPackage(@NonNull Model parentRoot, @NonNull String name) {
		@SuppressWarnings("null")
		org.eclipse.ocl.pivot.Package aPackage = PivotUtil.createPackage(org.eclipse.ocl.pivot.Package.class, PivotPackage.Literals.PACKAGE, name, null, null);
		parentRoot.getOwnedPackages().add(aPackage);
		return aPackage;
	}

	public static org.eclipse.ocl.pivot.@NonNull Package createOwnedPackage(org.eclipse.ocl.pivot.@NonNull Package parentPackage, @NonNull String name) {
		@SuppressWarnings("null")
		org.eclipse.ocl.pivot.Package aPackage = PivotUtil.createPackage(org.eclipse.ocl.pivot.Package.class, PivotPackage.Literals.PACKAGE, name, null, null);
		parentPackage.getOwnedPackages().add(aPackage);
		return aPackage;
	}

	public static org.eclipse.ocl.pivot.@NonNull Package createPackage(/*@NonNull*/ EPackage ePackage, @Nullable String nsPrefix, @NonNull String nsURI) {
		Package pivotPackage = PivotFactory.eINSTANCE.createPackage();
		pivotPackage.setName(ePackage.getName());
		pivotPackage.setNsPrefix(nsPrefix);
		pivotPackage.setURI(nsURI);
		((PivotObjectImpl)pivotPackage).setESObject(ePackage);
		return pivotPackage;
	}

	public static org.eclipse.ocl.pivot.@NonNull Package createPackage(@NonNull String name, @Nullable String nsPrefix, @NonNull String nsURI, @Nullable PackageId packageId) {
		Package pivotPackage = PivotFactory.eINSTANCE.createPackage();
		pivotPackage.setName(name);
		pivotPackage.setNsPrefix(nsPrefix);
		if (packageId != null) {
			((PackageImpl)pivotPackage).setPackageId(packageId);  // FIXME Add to API
		}
		pivotPackage.setURI(nsURI);
		return pivotPackage;
	}

	public static @NonNull <T extends org.eclipse.ocl.pivot.Package> T createPackage(@NonNull Class<T> pivotClass,
			@NonNull EClass pivotEClass, @NonNull String name, @Nullable String nsURI, @Nullable PackageId packageId) {
		@SuppressWarnings("unchecked")
		T asPackage = (T) pivotEClass.getEPackage().getEFactoryInstance().create(pivotEClass);
		asPackage.setName(name);
		if (packageId != null) {
			((PackageImpl)asPackage).setPackageId(packageId);
		}
		asPackage.setURI(nsURI);
		return asPackage;
	}

	public static @NonNull Parameter createParameter(@NonNull String name, @NonNull Type asType, boolean isRequired) {
		Parameter asParameter = PivotFactory.eINSTANCE.createParameter();
		asParameter.setName(name);
		asParameter.setType(asType);
		asParameter.setIsRequired(isRequired);
		return asParameter;
	}

	public static @NonNull Precedence createPrecedence(@NonNull String name, /*@NonNull*/ AssociativityKind kind) {
		assert kind != null;
		Precedence pivotPrecedence = PivotFactory.eINSTANCE.createPrecedence();
		pivotPrecedence.setName(name);
		pivotPrecedence.setAssociativity(kind);
		return pivotPrecedence;
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

	public static @NonNull Property createProperty(@NonNull String name, @NonNull Type type) {
		Property pivotProperty = PivotFactory.eINSTANCE.createProperty();
		pivotProperty.setName(name);
		pivotProperty.setType(type);
		return pivotProperty;
	}

	public static @NonNull PropertyCallExp createPropertyCallExp(@NonNull OCLExpression asSource, @NonNull Property asProperty) {
		PropertyCallExp asChild = PivotFactory.eINSTANCE.createPropertyCallExp();
		asChild.setOwnedSource(asSource);
		asChild.setReferredProperty(asProperty);
		asChild.setType(asProperty.getType());
		asChild.setIsRequired(asProperty.isIsRequired());
		return asChild;
	}

	public static @NonNull SelfType createSelfType(@NonNull String name) {
		SelfType pivotType = PivotFactory.eINSTANCE.createSelfType();
		pivotType.setName(name);
		return pivotType;
	}

	public static @NonNull SequenceType createSequenceType(@NonNull SequenceType unspecializedType, @NonNull Type elementType) {
		return createCollectionType(PivotFactory.eINSTANCE.createSequenceType(), unspecializedType, elementType);
	}

	public static @NonNull SetType createSetType(@NonNull SetType unspecializedType, @NonNull Type elementType) {
		return createCollectionType(PivotFactory.eINSTANCE.createSetType(), unspecializedType, elementType);
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
			List<org.eclipse.ocl.pivot.Class> constrainingClasses = pivotTemplateParameter.getConstrainingClasses();
			for (org.eclipse.ocl.pivot.Class lowerBound : lowerBounds) {
				constrainingClasses.add(lowerBound);
			}
		}
		return pivotTemplateParameter;
	}

	public static @NonNull TemplateParameterSubstitution createTemplateParameterSubstitution(@NonNull TemplateParameter formal, @NonNull Type actual) {
		TemplateParameterSubstitution pivotTemplateParameterSubstitution = PivotFactory.eINSTANCE.createTemplateParameterSubstitution();
		pivotTemplateParameterSubstitution.setFormal(formal);
		pivotTemplateParameterSubstitution.setActual(actual);
		return pivotTemplateParameterSubstitution;
	}

	public static @NonNull TemplateSignature createTemplateSignature(@NonNull TemplateableElement templateableElement, TemplateParameter... templateParameters) {
		TemplateSignature pivotTemplateSignature = PivotFactory.eINSTANCE.createTemplateSignature();
		List<TemplateParameter> parameters = pivotTemplateSignature.getOwnedParameters();
		for (TemplateParameter templateParameter : templateParameters) {
			parameters.add(templateParameter);
		}
		pivotTemplateSignature.setOwningElement(templateableElement);
		return pivotTemplateSignature;
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
			s.append("\n\t" + PivotConstants.MESSAGE_PART_NAME + " : String = " + messageText + ",");
		}
		if (severity != null) {
			s.append("\n\t" + PivotConstants.SEVERITY_PART_NAME + " : Integer = " + severity + ",");
		}
		s.append("\n\t" + PivotConstants.STATUS_PART_NAME + " : Boolean = " + statusText);		// NB parts in alphabetical order
		s.append("\n}."+ PivotConstants.STATUS_PART_NAME);
		return s.toString();
	}

	public static @NonNull Variable createVariable(@NonNull String name, @NonNull OCLExpression asInitExpression) {
		Variable asVariable = PivotFactory.eINSTANCE.createVariable();
		asVariable.setName(name);
		asVariable.setType(asInitExpression.getType());
		asVariable.setIsRequired(asInitExpression.isIsRequired());
		asVariable.setOwnedInit(asInitExpression);
		return asVariable;
	}

	public static @NonNull Variable createVariable(@NonNull String name, @NonNull Type asType, boolean isRequired, @Nullable OCLExpression asInitExpression) {
		Variable asVariable = PivotFactory.eINSTANCE.createVariable();
		asVariable.setName(name);
		asVariable.setType(asType);
		asVariable.setIsRequired(isRequired);
		asVariable.setOwnedInit(asInitExpression);
		return asVariable;
	}

	/**
	 * @since 1.3
	 */
	public static @NonNull VariableExp createVariableExp(@NonNull VariableDeclaration asVariable) {
		VariableExp asVariableExp = PivotFactory.eINSTANCE.createVariableExp();
		asVariableExp.setReferredVariable(asVariable);
		asVariableExp.setType(asVariable.getType());
		asVariableExp.setIsRequired(asVariable.isIsRequired());
		return asVariableExp;
	}
	/** @deprecated API preserving redundancy */
	@Deprecated
	public static @NonNull VariableExp createVariableExp(@NonNull Variable asVariable) {
		return createVariableExp((VariableDeclaration)asVariable);
	}

	public static @NonNull VoidType createVoidType(@NonNull String name) {
		VoidType pivotType = PivotFactory.eINSTANCE.createVoidType();
		pivotType.setName(name);
		return pivotType;
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
			for (Object obj : diagnostic.getData()) {
				s.append(newLine);
				s.append("\t");
				//				if (obj instanceof Throwable) {
				//					s.append(((Throwable)obj).getMessage());
				//				}
				//				else {
				s.append(obj);
				//				}
			}
			for (Diagnostic childDiagnostic : diagnostic.getChildren()) {
				if (childDiagnostic != null) {
					formatDiagnostic(s, childDiagnostic, newLine + "\t");
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
			}
		}
		return s.toString();
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

	public static @Nullable Namespace getContainingNamespace(@Nullable EObject element) {
		for (EObject eObject = element; eObject != null; eObject = eObject.eContainer()) {
			if (eObject instanceof Namespace) {
				return (Namespace)eObject;
			}
		}
		return null;
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
	 * @deprecated use getContainingModel
	 */
	@Deprecated
	public static @Nullable Model getContainingRoot(@Nullable EObject element) {
		return getContainingModel(element);
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
	public static @NonNull Type getElementalType(@NonNull Type type) {
		Type elementType = type;
		while (elementType instanceof CollectionType) {
			elementType = ((CollectionType)elementType).getElementType();
			assert elementType != null;
		}
		return elementType;
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

	public static @NonNull String getNavigationOperator(boolean isSafe, boolean isAggregate) {
		if (isAggregate) {
			return isSafe ? PivotConstants.SAFE_AGGREGATE_NAVIGATION_OPERATOR : PivotConstants.AGGREGATE_NAVIGATION_OPERATOR;
		}
		else {
			return isSafe ? PivotConstants.SAFE_OBJECT_NAVIGATION_OPERATOR : PivotConstants.OBJECT_NAVIGATION_OPERATOR;
		}
	}

	/**
	 * @since 1.3
	 */
	public static org.eclipse.ocl.pivot.@NonNull Class getOwningClass(@NonNull Operation subOperation) {
		return ClassUtil.nonNullState(subOperation.getOwningClass());
	}

	public static org.eclipse.ocl.pivot.@Nullable Package getPackage(@NonNull EObject object) {
		for (EObject eObject = object; eObject != null; eObject = eObject.eContainer()) {
			if (eObject instanceof org.eclipse.ocl.pivot.Package) {
				return (org.eclipse.ocl.pivot.Package)eObject;
			}
		}
		return null;
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

	public static @NonNull Operation getReferredOperation(@NonNull CallExp callExp) {
		if (callExp instanceof LoopExp) {
			return ClassUtil.nonNullState(((LoopExp)callExp).getReferredIteration());
		}
		else if (callExp instanceof OperationCallExp) {
			return ClassUtil.nonNullState(((OperationCallExp)callExp).getReferredOperation());
		}
		else {
			throw new IllegalStateException();
		}
	}

	/**
	 * @since 1.1
	 */
	public static @NonNull Property getReferredProperty(@NonNull NavigationCallExp navigationCallExp) {
		if (navigationCallExp instanceof PropertyCallExp) {
			return ClassUtil.nonNullState(((PropertyCallExp)navigationCallExp).getReferredProperty());
		}
		else if (navigationCallExp instanceof OppositePropertyCallExp) {
			return ClassUtil.nonNullState(((OppositePropertyCallExp)navigationCallExp).getReferredProperty().getOpposite());
		}
		else {
			throw new IllegalStateException();
		}
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

	public static @NonNull Operation initOperation(@NonNull Operation asOperation, @NonNull ExpressionInOCL asExpressionInOCL) {
		for (Variable asParameterVariable : asExpressionInOCL.getOwnedParameters()) {
			String parameterName = ClassUtil.nonNullState(asParameterVariable.getName());
			Type parameterType = ClassUtil.nonNullState(asParameterVariable.getType());
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
	 * Return true if type uses an aggregate (-&gt;) rather than object (.) navigation operator.
	 */
	public static boolean isAggregate(Type type) {
		return (type instanceof CollectionType) || (type instanceof MapType);
	}

	public static boolean isAggregateNavigationOperator(/*@NonNull*/ String operatorName) {
		return PivotConstants.AGGREGATE_NAVIGATION_OPERATOR.equals(operatorName)
				|| PivotConstants.SAFE_AGGREGATE_NAVIGATION_OPERATOR.equals(operatorName);
	}

	public static boolean isObjectNavigationOperator(/*@NonNull*/ String operatorName) {
		return PivotConstants.OBJECT_NAVIGATION_OPERATOR.equals(operatorName)
				|| PivotConstants.SAFE_OBJECT_NAVIGATION_OPERATOR.equals(operatorName);
	}

	public static boolean isSafeNavigationOperator(/*@NonNull*/ String operatorName) {
		return PivotConstants.SAFE_AGGREGATE_NAVIGATION_OPERATOR.equals(operatorName)
				|| PivotConstants.SAFE_OBJECT_NAVIGATION_OPERATOR.equals(operatorName);
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
	 * Rewrite asTree and all its descendants to replace all "?." and "?->" navigations by their safe counterparts.
	 * @since 1.3
	 * @deprecated use PivotHelper
	 */
	@Deprecated	// All callers have been a local version for QVTd M3
	public static void rewriteSafeNavigations(@NonNull EnvironmentFactory environmentFactory, @NonNull Element asTree) {
		PivotHelper helper = new PivotHelper(environmentFactory);
		helper.rewriteSafeNavigations(asTree);
	}

	/**
	 * Define oclExpression as the bodyExpression of an expressionInOCL, and if non-null
	 * also define stringExpression as the OCL-languaged body.
	 */
	public static void setBody(@NonNull ExpressionInOCL expressionInOCL, @Nullable OCLExpression oclExpression, @Nullable String stringExpression) {
		expressionInOCL.setBody(stringExpression);
		expressionInOCL.setOwnedBody(oclExpression);
	}

	/**
	 * Configure resource to support parsing in the context of an eObject. Throws a ParserException
	 * if a pivot element cannot be identified for eObject.eClass(). Return false if a pivot element
	 * can be identified, but it is not one that supports constraint parsing.
	 *
	 * @throws ParserException if eObject cannot be converted to a Pivot element
	 */
	public static boolean setParserContext(@NonNull CSResource csResource, @NonNull EObject eObject, Object... todoParameters) throws ParserException {
		EnvironmentFactoryAdapter adapter = OCLInternal.adapt(csResource);
		EnvironmentFactoryInternal environmentFactory = adapter.getEnvironmentFactory();
		Element pivotElement = environmentFactory.getTechnology().getParseableElement(environmentFactory, eObject);
		if (pivotElement == null) {
			return false;
		}
		ParserContext parserContext = environmentFactory.getMetamodelManager().createParserContext(pivotElement, todoParameters);
		if (parserContext == null) {
			return false;
		}
		else {
			csResource.setParserContext(parserContext);
			return true;
		}
	}
}
