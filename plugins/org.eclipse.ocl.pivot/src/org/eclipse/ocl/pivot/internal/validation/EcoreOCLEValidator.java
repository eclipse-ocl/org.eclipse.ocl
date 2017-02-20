/*
 * Copyright (c) 2014, 2016 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   E.D.Willink - initial API and implementation
 */
package org.eclipse.ocl.pivot.internal.validation;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.util.EObjectValidator;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.EcoreValidator;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.common.OCLCommon;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.evaluation.AbstractConstraintEvaluator;
import org.eclipse.ocl.pivot.internal.delegate.InvocationBehavior;
import org.eclipse.ocl.pivot.internal.delegate.SettingBehavior;
import org.eclipse.ocl.pivot.internal.delegate.ValidationBehavior;
import org.eclipse.ocl.pivot.internal.manager.MetamodelManagerInternal;
import org.eclipse.ocl.pivot.internal.messages.PivotMessagesInternal;
import org.eclipse.ocl.pivot.internal.utilities.OCLInternal;
import org.eclipse.ocl.pivot.internal.utilities.PivotConstantsInternal;
import org.eclipse.ocl.pivot.util.DerivedConstants;
import org.eclipse.ocl.pivot.util.PivotPlugin;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.ParserContext;
import org.eclipse.ocl.pivot.utilities.ParserException;
import org.eclipse.ocl.pivot.utilities.StringUtil;
import org.eclipse.ocl.pivot.utilities.TracingOption;
import org.eclipse.ocl.pivot.values.InvalidValueException;
import org.eclipse.ocl.pivot.values.TemplateParameterSubstitutions;

/**
 * EcoreOCLEValidator provides the validation support for Ecore elements that exploit OCL.
 * <p>
 * Typically used with a Diagnostician as:
 * <pre>
 *	EValidatorRegistryImpl registry = new EValidatorRegistryImpl();
 *	registry.put(EcorePackage.eINSTANCE, EcoreOCLEValidator.INSTANCE);
 *	Diagnostician diagnostician = new Diagnostician(registry);
 *	Diagnostic diagnostic = dignostician.validate(eObject, validationContext);
 * </pre>
 */
public class EcoreOCLEValidator implements EValidator
{
	public static final String UNKNOWN_DETAIL = "Unknown ''{0}'' detail for ''{1}''";
	public static final String MISSING_DELEGATE = "Missing ''{0}'' delegate for ''{1}''";
	public static final String EXTRA_CONSTRAINTS_ANNOTATION_ENTRY = "Extra ''constraints'' annotation entry for ''{0}'' in ''{1}''";
	public static final String MISSING_CONSTRAINTS_ANNOTATION_ENTRY = "Missing ''constraints'' annotation entry for ''{0}'' in ''{1}''";
	public static final String MISSING_CONSTRAINTS = "Missing constraints annotation for ''{0}''";
	public static final String PARSING_ERROR_2 = "Parsing error ''{0}'' for ''{1}'' ''{2}''";
	public static final String PARSING_ERROR_1 = "Parsing error ''{0}'' for ''{1}''";
	public static final String INCOMPATIBLE_TYPE_2 = "Incompatible type ''{0}'' for ''{1}'' ''{2}''";
	public static final String INCOMPATIBLE_TYPE_1 = "Incompatible type ''{0}'' for ''{1}''";
	public static final String NULL_EXPRESSION = "Null expression for ''{0}''";
	public static final String NULL_PROPERTY_KEY = "Non-null ''derivation'' or ''initial'' detail allowed for ''{0}''";
	public static final String EXTRA_PROPERTY_KEY = "Only ''derivation'' or ''initial'' detail allowed for ''{0}''";
	public static final String DOUBLE_PROPERTY_KEY = "Both ''derivation'' and ''initial'' detail for ''{0}''";
	public static final String MISSING_PROPERTY_KEY = "Missing ''" + SettingBehavior.DERIVATION_CONSTRAINT_KEY + "'' or ''" + SettingBehavior.INITIAL_CONSTRAINT_KEY + "'' detail for ''{0}''";

	/**
	 * ConstraintEvaluatorWithoutDiagnostics provides the minimal ConstraintEvaluator support for
	 * use when no diagnostics are required.
	 */
	public static class ConstraintEvaluatorWithoutDiagnostics extends AbstractConstraintEvaluator<Boolean>
	{
		public ConstraintEvaluatorWithoutDiagnostics(@NonNull ExpressionInOCL expression) {
			super(expression);
		}

		@Override
		protected String getObjectLabel() {
			throw new UnsupportedOperationException(); // Cannot happen;
		}

		@Override
		protected Boolean handleExceptionResult(@NonNull Throwable e) {
			return Boolean.FALSE;
		}

		@Override
		protected Boolean handleFailureResult(@Nullable Object result) {
			return Boolean.FALSE;
		}

		@Override
		protected Boolean handleInvalidExpression(@NonNull String message) {
			return Boolean.FALSE;
		}

		@Override
		protected Boolean handleInvalidResult(@NonNull InvalidValueException e) {
			return Boolean.FALSE;
		}

		@Override
		protected Boolean handleSuccessResult() {
			return Boolean.TRUE;
		}
	}

	/**
	 * ConstraintEvaluatorWithoutDiagnostics provides the richer ConstraintEvaluator support for
	 * use when diagnostics are required.
	 */
	public static class ConstraintEvaluatorWithDiagnostics extends AbstractConstraintEvaluator<Boolean>
	{
		protected final @NonNull EObject eObject;
		protected final @NonNull DiagnosticChain diagnostics;
		protected final @NonNull EObject diagnosticEObject;
		protected final boolean mayUseNewLines;

		public ConstraintEvaluatorWithDiagnostics(@NonNull ExpressionInOCL expression, @NonNull EObject eObject,
				@NonNull DiagnosticChain diagnostics, @NonNull EObject diagnosticEObject, boolean mayUseNewLines) {
			super(expression);
			this.diagnosticEObject = diagnosticEObject;
			this.eObject = eObject;
			this.diagnostics = diagnostics;
			this.mayUseNewLines = mayUseNewLines;
		}

		@Override
		protected String getObjectLabel() {
			return NameUtil.qualifiedNameFor(eObject);
		}

		@Override
		protected Boolean handleExceptionResult(@NonNull Throwable e) {
			String message = StringUtil.bind(PivotMessagesInternal.ValidationResultIsInvalid_ERROR_, getConstraintTypeName(), getConstraintName(), getObjectLabel(), e.toString());
			if (!mayUseNewLines) {
				message = message.replace("\n", "");
			}
			diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR, EcoreValidator.DIAGNOSTIC_SOURCE,
				0, message,  new Object[] { diagnosticEObject }));
			return Boolean.FALSE;
		}

		@Override
		protected Boolean handleFailureResult(@Nullable Object result) {
			int severity = getConstraintResultSeverity(result);
			String message = getConstraintResultMessage(result);
			diagnostics.add(new BasicDiagnostic(severity, EcoreValidator.DIAGNOSTIC_SOURCE,
				0, message,  new Object[] { diagnosticEObject }));
			return Boolean.FALSE;
		}

		@Override
		protected Boolean handleInvalidExpression(@NonNull String message) {
			String message2 = message;
			if (!mayUseNewLines) {
				message2 = message.replace("\n", "");
			}
			diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR, EObjectValidator.DIAGNOSTIC_SOURCE,
				0, message2.replace("\n", " - "), new Object [] { diagnosticEObject }));
			return Boolean.FALSE;
		}

		@Override
		protected Boolean handleInvalidResult(@NonNull InvalidValueException e) {
			String message = StringUtil.bind(PivotMessagesInternal.ValidationResultIsInvalid_ERROR_,
				getConstraintTypeName(), getConstraintName(), getObjectLabel(), e.getLocalizedMessage());
			if (!mayUseNewLines) {
				message = message.replace("\n", "");
			}
			diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR, EcoreValidator.DIAGNOSTIC_SOURCE,
				0, message,  new Object[] { diagnosticEObject }));
			return Boolean.FALSE;
		}

		@Override
		protected Boolean handleSuccessResult() {
			return Boolean.TRUE;
		}
	}

	/**
	 * WeakOCLReference maintains the reference to the OCL context within the Diagnostician context and
	 * disposes of it once the Diagnostician is done.
	 */
	protected static final class WeakOCLReference extends WeakReference<OCLInternal>
	{
		protected final @NonNull OCLInternal ocl;

		protected WeakOCLReference(@NonNull OCLInternal ocl) {
			super(ocl);
			this.ocl = ocl;
		}

		@Override
		public void finalize() {
			new Thread("OCL-Finalizer")
			{
				@Override
				public void run() {
					ocl.dispose();
				}
			}.start();
		}
	}

	public static final @NonNull EcoreOCLEValidator INSTANCE = new EcoreOCLEValidator(true);
	public static final @NonNull EcoreOCLEValidator NO_NEW_LINES = new EcoreOCLEValidator(false);
	public static final @NonNull TracingOption VALIDATE_INSTANCE = new TracingOption(PivotPlugin.PLUGIN_ID, "validate/instance");
	public static final @NonNull TracingOption VALIDATE_OPAQUE_ELEMENT = new TracingOption(PivotPlugin.PLUGIN_ID, "validate/opaqueElement");

	protected static void gatherTypes(@NonNull Set<org.eclipse.ocl.pivot.Class> allTypes, @NonNull Set<Constraint> allConstraints, org.eclipse.ocl.pivot.@NonNull Class newType) {
		if (allTypes.add(newType)) {
			allConstraints.addAll(newType.getOwnedInvariants());
			for (org.eclipse.ocl.pivot.Class superType : newType.getSuperClasses()) {
				if (superType != null) {
					gatherTypes(allTypes, allConstraints, superType);
				}
			}
		}
	}

	protected final boolean mayUseNewLines;

	public EcoreOCLEValidator(boolean mayUseNewLines) {
		this.mayUseNewLines = mayUseNewLines;
	}

	/**
	 * Return the OCL context for the validation, caching the created value in the validation context for re-use by
	 * further validations. The cached reference is weak to ensure that the OCL context is disposed once no longer in use.
	 */
	protected OCLInternal getOCL(@NonNull Map<Object, Object> context) {
		OCLInternal ocl = null;
		Object oclRef = context.get(WeakOCLReference.class);
		if (oclRef instanceof WeakOCLReference) {
			ocl = ((WeakOCLReference)oclRef).get();
		}
		if (ocl == null) {
			ocl = OCLInternal.newInstance();
			context.put(WeakOCLReference.class, new WeakOCLReference(ocl));
		}
		return ocl;
	}

	protected boolean isOCL(List<String> someDelegates) {
		for (String aDelegate : someDelegates) {
			if (OCLCommon.isDelegateURI(aDelegate)) {
				return true;
			}
		}
		return false;
	}

	private boolean isStaleStereotypeContent(EObject eObject) {
		for (; eObject != null; eObject = eObject.eContainer()) {
			if (eObject instanceof EPackage) {
				EObject eContainer = eObject.eContainer();
				if (eContainer instanceof EAnnotation) {
					EAnnotation eAnnotation = (EAnnotation)eContainer;
					if (DerivedConstants.UML2_UML_PACKAGE_2_0_NS_URI.equals(eAnnotation.getSource())) {
						int index = eAnnotation.getContents().indexOf(eObject);
						return index != 0;		// Anything other than the first stereotype is ignored.
					}
				}
				break;
			}
		}
		return false;
	}

	@Override
	public boolean validate(EObject eObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate(eObject.eClass(), eObject, diagnostics, context);
		//		return true;
	}

	@Override
	public boolean validate(EClass eClass, EObject eObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (isStaleStereotypeContent(eObject)) {
			return true;
		}
		assert context != null;
		boolean allOk = true;
		if (eObject instanceof EPackage) {
			EPackage ePackage = (EPackage)eObject;
			allOk = validateEPackage(ePackage, diagnostics, context);
		}
		else if (eObject instanceof EClassifier) {
			EClassifier eClassifier = (EClassifier)eObject;
			allOk = validateEClassifier(eClassifier, diagnostics, context);
		}
		else if (eObject instanceof EOperation) {
			EOperation eOperation = (EOperation)eObject;
			allOk = validateEOperation(eOperation, diagnostics, context);
		}
		else if (eObject instanceof EStructuralFeature) {
			EStructuralFeature eStructuralFeature = (EStructuralFeature)eObject;
			allOk = validateEStructuralFeature(eStructuralFeature, diagnostics, context);
		}
		return allOk;
	}

	@Override
	public boolean validate(EDataType eDataType, Object value, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	protected boolean validateEClassifier(@NonNull EClassifier eClassifier, DiagnosticChain diagnostics, @NonNull Map<Object, Object> context) {
		boolean allOk = true;
		String constraintsAnnotation = EcoreUtil.getAnnotation(eClassifier, EcorePackage.eNS_URI, "constraints");
		EAnnotation eAnnotation = OCLCommon.getDelegateAnnotation(eClassifier);
		if (eAnnotation != null) {
			OCLInternal ocl = getOCL(context);
			MetamodelManagerInternal metamodelManager = ocl.getMetamodelManager();
			StandardLibrary standardLibrary = ocl.getStandardLibrary();
			EMap<String, String> details = eAnnotation.getDetails();
			for (String constraintName : details.keySet()) {
				String value = details.get(constraintName);
				allOk = validateExpression(metamodelManager, eClassifier, value, standardLibrary.getBooleanType(), constraintName, diagnostics, context);
			}
			if (constraintsAnnotation == null) {
				if (diagnostics != null) {
					String objectLabel = EObjectValidator.getObjectLabel(eClassifier, context);
					String message = StringUtil.bind(MISSING_CONSTRAINTS, objectLabel);
					diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR, EcoreValidator.DIAGNOSTIC_SOURCE,
						0, message,  new Object[] { eClassifier }));
				}
				else {
					allOk = false;
				}
			}
			else {
				Set<String> ecoreConstraints = new HashSet<String>(EcoreUtil.getConstraints(eClassifier));
				Set<String> oclConstraints = new HashSet<String>(details.keySet());
				oclConstraints.removeAll(ecoreConstraints);
				ecoreConstraints.removeAll(details.keySet());
				for (String aConstraint : oclConstraints) {
					if (diagnostics != null) {
						String objectLabel = EObjectValidator.getObjectLabel(eClassifier, context);
						String message = StringUtil.bind(MISSING_CONSTRAINTS_ANNOTATION_ENTRY, aConstraint, objectLabel);
						diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR, EcoreValidator.DIAGNOSTIC_SOURCE,
							0, message,  new Object[] { eClassifier }));
					}
					else {
						allOk = false;
					}
				}
				for (String aConstraint : ecoreConstraints) {
					if (diagnostics != null) {
						String objectLabel = EObjectValidator.getObjectLabel(eClassifier, context);
						String message = StringUtil.bind(EXTRA_CONSTRAINTS_ANNOTATION_ENTRY, aConstraint, objectLabel);
						diagnostics.add(new BasicDiagnostic(Diagnostic.WARNING, EcoreValidator.DIAGNOSTIC_SOURCE,
							0, message,  new Object[] { eClassifier }));
					}
					else {
						allOk = false;
					}
				}
			}
		}
		return allOk;
	}

	protected boolean validateEPackage(@NonNull EPackage ePackage, DiagnosticChain diagnostics, @NonNull Map<Object, Object> context) {
		boolean allOk = true;
		boolean needsInvocationDelegates = false;
		boolean needsSettingDelegates = false;
		boolean needsValidationDelegates = false;
		for (EClassifier eClassifier : ePackage.getEClassifiers()) {
			if (OCLCommon.getDelegateAnnotation(eClassifier) != null) {
				needsValidationDelegates = true;
			}
			if (eClassifier instanceof EClass) {
				EClass eClass = (EClass)eClassifier;
				for (EOperation eOperation : eClass.getEOperations()) {
					if (!EcoreUtil.isInvariant(eOperation) && (OCLCommon.getDelegateAnnotation(eOperation) != null)) {
						needsInvocationDelegates = true;
					}
				}
				for (EStructuralFeature eStructuralFeature : eClass.getEStructuralFeatures()) {
					if (OCLCommon.getDelegateAnnotation(eStructuralFeature) != null) {
						needsSettingDelegates = true;
					}
				}
			}
		}
		boolean hasInvocationDelegates = isOCL(EcoreUtil.getInvocationDelegates(ePackage));
		boolean hasSettingDelegates = isOCL(EcoreUtil.getSettingDelegates(ePackage));
		boolean hasValidationDelegates = isOCL(EcoreUtil.getValidationDelegates(ePackage));
		if (needsInvocationDelegates && !hasInvocationDelegates) {
			if (diagnostics != null) {
				String objectLabel = EObjectValidator.getObjectLabel(ePackage, context);
				String message = StringUtil.bind(MISSING_DELEGATE, InvocationBehavior.NAME, objectLabel);
				diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR, EcoreValidator.DIAGNOSTIC_SOURCE,
					0, message,  new Object[] { ePackage }));
			}
			else {
				allOk = false;
			}
		}
		if (needsSettingDelegates && !hasSettingDelegates) {
			if (diagnostics != null) {
				String objectLabel = EObjectValidator.getObjectLabel(ePackage, context);
				String message = StringUtil.bind(MISSING_DELEGATE, SettingBehavior.NAME, objectLabel);
				diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR, EcoreValidator.DIAGNOSTIC_SOURCE,
					0, message,  new Object[] { ePackage }));
			}
			else {
				allOk = false;
			}
		}
		if (needsValidationDelegates && !hasValidationDelegates) {
			if (diagnostics != null) {
				String objectLabel = EObjectValidator.getObjectLabel(ePackage, context);
				String message = StringUtil.bind(MISSING_DELEGATE, ValidationBehavior.NAME, objectLabel);
				diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR, EcoreValidator.DIAGNOSTIC_SOURCE,
					0, message,  new Object[] { ePackage }));
			}
			else {
				allOk = false;
			}
		}
		return allOk;
	}

	protected boolean validateEOperation(@NonNull EOperation eOperation, DiagnosticChain diagnostics, @NonNull Map<Object, Object> context) {
		boolean allOk = true;
		EAnnotation eAnnotation = OCLCommon.getDelegateAnnotation(eOperation);
		if (eAnnotation != null) {
			OCLInternal ocl = getOCL(context);
			MetamodelManagerInternal metamodelManager = ocl.getMetamodelManager();
			StandardLibrary standardLibrary = ocl.getStandardLibrary();
			EMap<String, String> details = eAnnotation.getDetails();
			Set<String> knownKeys = new HashSet<String>();
			if (details.containsKey(InvocationBehavior.BODY_CONSTRAINT_KEY)) {
				knownKeys.add(InvocationBehavior.BODY_CONSTRAINT_KEY);
				String value = details.get(InvocationBehavior.BODY_CONSTRAINT_KEY);
				Type requiredType = EcoreUtil.isInvariant(eOperation) ? standardLibrary.getBooleanType() : null;
				allOk = validateExpression(metamodelManager, eOperation, value, requiredType, InvocationBehavior.BODY_CONSTRAINT_KEY, diagnostics, context);
			}
			if (details.containsKey("pre")) {
				knownKeys.add("pre");
				String value = details.get("pre");
				allOk = validateExpression(metamodelManager, eOperation, value, standardLibrary.getBooleanType(), "pre", diagnostics, context);
			}
			if (details.containsKey("post")) {
				knownKeys.add("post");
				String value = details.get("post");
				allOk = validateExpression(metamodelManager, eOperation, value, standardLibrary.getBooleanType(), "post", diagnostics, context);
			}
			Set<String> actualKeys = details.keySet();
			Set<String> unknownKeys = new HashSet<String>(actualKeys);
			unknownKeys.removeAll(knownKeys);
			if (unknownKeys.size() > 0) {
				if (diagnostics != null) {
					for (String unknownKey : unknownKeys) {
						String objectLabel = EObjectValidator.getObjectLabel(eOperation, context);
						String message = StringUtil.bind(UNKNOWN_DETAIL, unknownKey, objectLabel);
						diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR, EcoreValidator.DIAGNOSTIC_SOURCE,
							0, message,  new Object[] { eOperation }));
					}
				}
				else {
					allOk = false;
				}
			}
		}
		return allOk;
	}

	protected boolean validateEStructuralFeature(@NonNull EStructuralFeature eStructuralFeature, DiagnosticChain diagnostics, @NonNull Map<Object, Object> context) {
		boolean allOk = true;
		EAnnotation eAnnotation = OCLCommon.getDelegateAnnotation(eStructuralFeature);
		if (eAnnotation != null) {
			EMap<String, String> details = eAnnotation.getDetails();
			int entries = 0;
			String value = null;
			if (details.containsKey(SettingBehavior.DERIVATION_CONSTRAINT_KEY)) {
				entries++;
				value = details.get(SettingBehavior.DERIVATION_CONSTRAINT_KEY);
			}
			if (details.containsKey(SettingBehavior.INITIAL_CONSTRAINT_KEY)) {
				entries++;
				value = details.get(SettingBehavior.INITIAL_CONSTRAINT_KEY);
			}
			if (entries == 0) {
				if (diagnostics != null) {
					String objectLabel = EObjectValidator.getObjectLabel(eStructuralFeature, context);
					String message = StringUtil.bind(MISSING_PROPERTY_KEY, objectLabel);
					diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR, EcoreValidator.DIAGNOSTIC_SOURCE,
						0, message,  new Object[] { eStructuralFeature }));
				}
				else {
					allOk = false;
				}
			}
			else if (entries == 2) {
				if (diagnostics != null) {
					String objectLabel = EObjectValidator.getObjectLabel(eStructuralFeature, context);
					String message = StringUtil.bind(DOUBLE_PROPERTY_KEY, objectLabel);
					diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR, EcoreValidator.DIAGNOSTIC_SOURCE,
						0, message,  new Object[] { eStructuralFeature }));
				}
				else {
					allOk = false;
				}
			}
			else if (details.size() != 1) {
				if (diagnostics != null) {
					String objectLabel = EObjectValidator.getObjectLabel(eStructuralFeature, context);
					String message = StringUtil.bind(EXTRA_PROPERTY_KEY, objectLabel);
					diagnostics.add(new BasicDiagnostic(Diagnostic.WARNING, EcoreValidator.DIAGNOSTIC_SOURCE,
						0, message,  new Object[] { eStructuralFeature }));
				}
				else {
					allOk = false;
				}
			}
			else if (value == null) {
				if (diagnostics != null) {
					String objectLabel = EObjectValidator.getObjectLabel(eStructuralFeature, context);
					String message = StringUtil.bind(NULL_PROPERTY_KEY, objectLabel);
					diagnostics.add(new BasicDiagnostic(Diagnostic.WARNING, EcoreValidator.DIAGNOSTIC_SOURCE,
						0, message,  new Object[] { eStructuralFeature }));
				}
				else {
					allOk = false;
				}
			}
			else {
				OCLInternal ocl = getOCL(context);
				MetamodelManagerInternal metamodelManager = ocl.getMetamodelManager();
				allOk = validateExpression(metamodelManager, eStructuralFeature, value, null, null, diagnostics, context);
			}
		}
		return allOk;
	}

	protected boolean validateExpression(@NonNull MetamodelManagerInternal metamodelManager, @NonNull ENamedElement eNamedElement, @Nullable String expression, @Nullable Type requiredType, @Nullable String role, DiagnosticChain diagnostics, @NonNull Map<Object, Object> context) {
		if (expression == null) {
			if (diagnostics != null) {
				String objectLabel = EObjectValidator.getObjectLabel(eNamedElement, context);
				String message = StringUtil.bind(NULL_EXPRESSION, objectLabel);
				diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR, EcoreValidator.DIAGNOSTIC_SOURCE,
					0, message,  new Object[] { eNamedElement }));
				return true;
			}
			else {
				return false;
			}
		}
		try {
			NamedElement asNamedElement = metamodelManager.getASOf(NamedElement.class, eNamedElement);
			if (asNamedElement != null) {
				ParserContext parserContext = metamodelManager.createParserContext(asNamedElement);
				if (parserContext == null) {
					throw new ParserException(PivotMessagesInternal.UnknownContextType_ERROR_, NameUtil.qualifiedNameFor(asNamedElement), PivotConstantsInternal.OWNED_CONSTRAINT_ROLE);
				}
				ExpressionInOCL expressionInOCL = parserContext.parse(asNamedElement, expression);
				Type asExpressionType = expressionInOCL.getType();
				Type asType;
				if (requiredType != null) {
					asType = requiredType;
				}
				else if (asNamedElement instanceof TypedElement) {
					asType = ((TypedElement)asNamedElement).getType();
				}
				else {
					asType = null;
				}
				assert asType != null;
				assert asExpressionType != null;
				if (!metamodelManager.conformsTo(asExpressionType, TemplateParameterSubstitutions.EMPTY, asType, TemplateParameterSubstitutions.EMPTY)) {
					//					metamodelManager.conformsTo(asExpressionType, TemplateParameterSubstitutions.EMPTY, asType, TemplateParameterSubstitutions.EMPTY);			// Debugging
					if (diagnostics != null) {
						String objectLabel = EObjectValidator.getObjectLabel(eNamedElement, context);
						String message = role == null ? StringUtil.bind(INCOMPATIBLE_TYPE_1, asExpressionType, objectLabel)
							: StringUtil.bind(INCOMPATIBLE_TYPE_2, asExpressionType, objectLabel, role);
						diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR, EcoreValidator.DIAGNOSTIC_SOURCE,
							0, message,  new Object[] { eNamedElement }));
					}
					else {
						return false;
					}
				}
				Map<Object,Object> newContext = new HashMap<>(context);
				newContext.remove(EObjectValidator.ROOT_OBJECT);
				return Diagnostician.INSTANCE.validate(expressionInOCL, diagnostics, newContext);
			}
		}
		catch (ParserException e) {
			if (diagnostics != null) {
				String objectLabel = EObjectValidator.getObjectLabel(eNamedElement, context);
				String message = role == null ? StringUtil.bind(PARSING_ERROR_1, e, objectLabel)
					: StringUtil.bind(PARSING_ERROR_2, e, objectLabel, role);
				diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR, EcoreValidator.DIAGNOSTIC_SOURCE,
					0, message,  new Object[] { eNamedElement }));
			}
			else {
				return false;
			}
		}
		return true;
	}

	/**
	 * Return an OCL AST from a string in the context of a NamedElement. If it is necessary
	 * to parse OCL concrete syntax and errors result, a ParserException is thrown.
	 *
	public static @NonNull ExpressionInOCL getValidExpressionInOCL(@NonNull MetamodelManager metamodelManager, @NonNull NamedElement contextElement, @NonNull String expression) throws ParserException {
//			Resource resource = contextElement.eResource();
//			if (resource == null) {
//				throw new ParserException("No containing resource for " + contextElement);
//			}
//			ResourceSet resourceSet = ClassUtil.nonNullState(resource.getResourceSet());
//			MetamodelManager metamodelManager = MetamodelManager.getAdapter(resourceSet);
			ParserContext parserContext = metamodelManager.getParserContext(contextElement);
			if (parserContext == null) {
				throw new ParserException(OCLMessages.UnknownContextType_ERROR_, EcoreUtils.qualifiedNameFor(contextElement), PivotConstants.UNKNOWN_ROLE/*getSpecificationRole(specification)* /);
			}
//			Namespace contextElement2 = PivotUtil.getContainingNamespace(contextElement);
//			if (contextElement == null) {
//				throw new ParserException("No containing namespace for " + contextElement);
//			}
			Type classContext = parserContext.getClassContext();
//			assert classContext == contextElement2;
			ExpressionInOCL expressionInOCL = parserContext.parse(classContext, expression);
			return expressionInOCL;
	} */
}
