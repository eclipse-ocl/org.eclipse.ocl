/*******************************************************************************
 * Copyright (c) 2013, 2025 CEA LIST and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink (CEA LIST) - initial API and implementation
 *   E.D.Willink (CEA LIST) - Bug 424034
 *******************************************************************************/
package org.eclipse.ocl.codegen.oclinecore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.codegen.ecore.genmodel.GenAnnotation;
import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.codegen.ecore.genmodel.GenClassifier;
import org.eclipse.emf.codegen.ecore.genmodel.GenFeature;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.codegen.common.NameQueries;
import org.eclipse.ocl.codegen.generator.AbstractGenModelHelper;
import org.eclipse.ocl.codegen.generator.GenModelHelper;
import org.eclipse.ocl.codegen.java.JavaImportNameManager;
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.CompleteClass;
import org.eclipse.ocl.pivot.CompleteModel;
import org.eclipse.ocl.pivot.CompleteStandardLibrary;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.DataType;
import org.eclipse.ocl.pivot.Enumeration;
import org.eclipse.ocl.pivot.EnumerationLiteral;
import org.eclipse.ocl.pivot.LambdaParameter;
import org.eclipse.ocl.pivot.LambdaType;
import org.eclipse.ocl.pivot.MapType;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.Namespace;
import org.eclipse.ocl.pivot.NormalizedTemplateParameter;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.ParameterTypes;
import org.eclipse.ocl.pivot.PrimitiveType;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.TemplateParameter;
import org.eclipse.ocl.pivot.TemplateableElement;
import org.eclipse.ocl.pivot.TupleType;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.VoidType;
import org.eclipse.ocl.pivot.ids.BuiltInTypeId;
import org.eclipse.ocl.pivot.ids.LambdaTypeId;
import org.eclipse.ocl.pivot.ids.ParametersId;
import org.eclipse.ocl.pivot.ids.TemplateParameterId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.manager.BasicTemplateSpecialization;
import org.eclipse.ocl.pivot.internal.manager.Orphanage;
import org.eclipse.ocl.pivot.internal.manager.TemplateSpecialization;
import org.eclipse.ocl.pivot.internal.prettyprint.PrettyPrinter;
import org.eclipse.ocl.pivot.internal.utilities.External2AS;
import org.eclipse.ocl.pivot.util.AbstractExtendingVisitor;
import org.eclipse.ocl.pivot.util.Visitable;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;
import org.eclipse.ocl.pivot.utilities.Nameable;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.xtext.util.Strings;

public class OCLinEcoreTablesUtils
{
//	private static int SHOW_TABLES_PACKAGE = 1;
//	private static int SHOW_TABLES_SUBPACKAGE = 2;

	public Comparator<@NonNull ParameterTypes> templateBindingNameComparator = new Comparator<@NonNull ParameterTypes>()
	{
		@Override
		public int compare(@NonNull ParameterTypes o1, @NonNull ParameterTypes o2) {
			String n1 = getTemplateBindingsName(o1);
			String n2 = getTemplateBindingsName(o2);
			return n1.compareTo(n2);
		}
	};

	public static Comparator<@NonNull Nameable> nameComparator = new Comparator<@NonNull Nameable>()
	{
		@Override
		public int compare(@NonNull Nameable o1, @NonNull Nameable o2) {
			String n1 = String.valueOf(o1.getName());
			String n2 = String.valueOf(o2.getName());
			return n1.compareTo(n2);
		}
	};

	public static final @NonNull Comparator<@NonNull Property> propertyComparator = new Comparator<@NonNull Property>()
	{
		@Override
		public int compare(@NonNull Property p1, @NonNull Property p2) {
			boolean b1 = p1.isIsImplicit();
			boolean b2 = p2.isIsImplicit();
			if (b1 != b2) {
				return b1 ? 1 : -1;
			}
			String n1 = String.valueOf(p1.getName());
			String n2 = String.valueOf(p2.getName());
			int diff = n1.compareTo(n2);
			if (diff != 0) {
				return diff;
			}
			Property o1 = p1.getOpposite();
			Property o2 = p2.getOpposite();
			if (o1 == null) {
				if (o2 == null) {
					return 0;
				}
				else {
					return 1;
				}
			}
			else {
				if (o2 == null) {
					return -1;
				}
				else {
					n1 = String.valueOf(o1.getName());
					n2 = String.valueOf(o2.getName());
					return n1.compareTo(n2);
				}
			}
		}
	};

	public static Comparator<@NonNull Operation> signatureComparator = new Comparator<@NonNull Operation>()
	{
		@Override
		public int compare(@NonNull Operation o1, @NonNull Operation o2) {
			String n1 = String.valueOf(getSignature(o1));
			String n2 = String.valueOf(getSignature(o2));
			return n1.compareTo(n2);
		}
	};

/*	private static @Nullable <T extends GenFeature> T getGenFeature(@Nullable List<T> genFeatures, @NonNull String name) {
		if (genFeatures != null) {
			for (T genFeature : genFeatures) {
				assert genFeature != null;
				EStructuralFeature eFeature = genFeature.getEcoreFeature();
				if (genModelHelper.getName(eFeature).equals(name)) {
					return genFeature;
				}
			}
		}
		return null;
	} */

/*	private static @Nullable <T extends GenPackage> T getGenPackage(@Nullable List<T> genPackages, @NonNull String name) {
		if (genPackages != null) {
			for (T genPackage : genPackages) {
				assert genPackage != null;
				EPackage ePackage = genPackage.getEcorePackage();
				if (getName(ePackage).equals(name)) {
					return genPackage;
				}
			}
		}
		return null;
	} */

/*	private static <@NonNull T extends GenPackage> @Nullable T getLibraryGenPackage(List<T> genPackages) {
		for (T genPackage : genPackages) {
			EPackage ecorePackage = genPackage.getEcorePackage();
			GenClassifier genClassifier = getGenClassifier(genPackage.getGenClassifiers(), "_Dummy");
			EClassifier eClassifier = ecorePackage.getEClassifier("_Dummy");		/ / F I X M E
			if (eClassifier != null) {
				assert genClassifier != null;
				assert eClassifier == genClassifier.getEcoreClassifier();
				return genPackage;
			}
			assert genClassifier == null;
		}
		return null;
	} */

/*	private static <@NonNull T extends GenPackage> @Nullable T getMetamodelGenPackage(@NonNull List<T> genPackages) {
		for (T genPackage : genPackages) {
			EPackage ecorePackage = genPackage.getEcorePackage();
			GenClassifier genClassifier = getGenClassifier(genPackage.getGenClassifiers(), "Element");
			EClassifier eClassifier = ecorePackage.getEClassifier("Element");
			if (eClassifier != null) {
				assert genClassifier != null;
				assert eClassifier == genClassifier.getEcoreClassifier();
				return genPackage;
			}
			assert genClassifier == null;
		}
		return null;
	} */

	public @NonNull String getPagedName(@NonNull String name, int i, int iMax) {
		if (i < iMax) {
			return name + i;
		}
		else {
			return name;
		}
	}

	public static @NonNull Boolean isBuiltInType(@NonNull Type type) {
		//		System.out.println(ClassUtil.debugSimpleName(type) + " + " + ClassUtil.debugSimpleName(type.getTypeId()) + " + " + type.getTypeId());
		return type.getTypeId() instanceof BuiltInTypeId;
	}

/*	private static @NonNull GenPackage loadGenPackage(@NonNull ResourceSet resourceSet, @NonNull URI genModelURI) {
		Resource resource = resourceSet.getResource(genModelURI, true);
		GenModel genModel = (GenModel) resource.getContents().get(0);
		GenPackage genPackage = genModel.getGenPackages().get(0);
		assert genPackage != null;
		return genPackage;
	} */

	/**
	 * CodeGenString elaborates a StringBuilder with helpers for Java generation.
	 * In particular addImport and addClassReference facilitate management of the imports,.
	 */
	public static class CodeGenString
	{
		protected final @NonNull CompleteModel completeModel;
		protected final boolean useNullAnnotations;
		protected final @NonNull StringBuilder s = new StringBuilder();
		protected final @NonNull JavaImportNameManager importNameManager = new JavaImportNameManager();
		protected final @NonNull Map<@NonNull Type, @NonNull String> typeNameMap = new HashMap<>();
		protected final @NonNull Set<@NonNull String> typeNameUse = new HashSet<>();

		public CodeGenString(@NonNull CompleteModel completeModel, boolean useNullAnnotations) {
			this.completeModel = completeModel;
			this.useNullAnnotations = useNullAnnotations;
		}

		public @NonNull String addClassReference(@Nullable Boolean isRequired, @NonNull Class<?> referencedClass) {
			@NonNull String fullName = referencedClass.getName();
			return importNameManager.addImport(useNullAnnotations ? isRequired : null, fullName);
		}

		public @NonNull String addImport(@Nullable Boolean isRequired, @NonNull String referencedClass) {
			return importNameManager.addImport(isRequired, referencedClass);
		}

		public void append(char c) {
			s.append(c);
		}

		public void append(@Nullable String string) {
			if (string != null) {
				s.append(string);
			}
		}

		public void appendAndEncodeName(@NonNull NamedElement namedElement) {
			s.append(AbstractGenModelHelper.encodeName(namedElement));
		}

		public void appendClassReference(@Nullable Boolean isRequired, @NonNull Class<?> referencedClass) {
			String classReferenceText = addClassReference(isRequired, referencedClass);
			s.append(classReferenceText);
		}

		public void appendClassReference(@Nullable Boolean isRequired, @NonNull String referencedClass) {
			s.append(addImport(isRequired, referencedClass));
		}

		protected void appendString(@NonNull String string) {
			@SuppressWarnings("null")@NonNull String javaString = Strings.convertToJavaString(string);
			s.append("\"");
			s.append(javaString);
			s.append("\"");
		}

		/**
		 * Append the encoded name of a type with a suffix if disambiguation across packages is required.
		 */
		public void appendUnscopedTypeName(@NonNull Type theType) {
			s.append(getTypeName(completeModel.getPrimaryType(theType)));
		}

		public @NonNull List<@NonNull String> getClassReferences() {
			List<@NonNull String> names = new ArrayList<>(importNameManager.getLong2ShortImportNames().keySet());
			Collections.sort(names);
			return names;
		}

		private @NonNull String getTypeName(@NonNull Type theType) {
			String name = typeNameMap.get(theType);
			if (name != null) {
				return name;
			}
			name = AbstractGenModelHelper.encodeName(theType);
			if (typeNameUse.contains(name)) {
				int index = 1;
				String candidateName = name + '_' + index;
				while (typeNameUse.contains(name + '_' + index)) {
					index++;
				}
				name = candidateName;
			}
			typeNameMap.put(theType, name);
			typeNameUse.add(name);
			return name;
		}

		@Override
		public @NonNull String toString() {
			return s.toString();
		}
	}

	/**
	 * EmitDeclaredNameVisitor.accept(T) emits the name of T.
	 */
	public static class EmitDeclaredNameVisitor extends AbstractExtendingVisitor<Object, Object>
	{
		protected final @NonNull CodeGenString s;

		protected EmitDeclaredNameVisitor(@NonNull CodeGenString s) {
			super(null);
			this.s = s;
		}

		protected void appendClassReference(@NonNull String nestedClassName, org.eclipse.ocl.pivot.@NonNull Class asClass) {
			appendDeclaredClassName(asClass);
		}

		protected void appendDeclaredClassName(@NonNull NamedElement asNamedElement) {
			s.append("_");
			s.appendAndEncodeName(asNamedElement);
		}

		@Override
		public @Nullable Object visiting(@NonNull Visitable visitable) {
			throw new UnsupportedOperationException("Unsupported EmitDeclaredNameVisitor for " + visitable.eClass().getName());
		}

		@Override
		public @Nullable Object visitClass(org.eclipse.ocl.pivot.@NonNull Class asClass) {
			appendClassReference(AbstractGenModelHelper.TYPES_PACKAGE_NAME, asClass);
			return null;
		}

		@Override
		public @Nullable Object visitCollectionType(@NonNull CollectionType type) {
			CollectionType unspecializedType = PivotUtil.getUnspecializedTemplateableElement(type);
			appendClassReference(AbstractGenModelHelper.TYPES_PACKAGE_NAME, unspecializedType);
			return null;
		}

		@Override
		public @Nullable Object visitConstraint(@NonNull Constraint constraint) {
			Type type = ClassUtil.requireNonNull((Type) constraint.eContainer());
			appendDeclaredClassName(type);
			s.append("__");
			s.append(NameQueries.getUniqueText(type, constraint));
			return null;
		}

		@Override
		public @Nullable Object visitEnumerationLiteral(@NonNull EnumerationLiteral asEnumerationLiteral) {
			Enumeration asEnumeration = PivotUtil.getOwningEnumeration(asEnumerationLiteral);
			appendClassReference(AbstractGenModelHelper.ENUMERATION_LITERALS_PACKAGE_NAME, asEnumeration);
			s.append("__");
			s.appendAndEncodeName(asEnumerationLiteral);
			return null;
		}

		@Override
		public @Nullable Object visitMapType(@NonNull MapType asMapType) {
			MapType unspecializedType = PivotUtil.getUnspecializedTemplateableElement(asMapType);
			appendClassReference(AbstractGenModelHelper.TYPES_PACKAGE_NAME, unspecializedType);
			return null;
		}

		@Override
		public @Nullable Object visitOperation(@NonNull Operation asOperation) {
			org.eclipse.ocl.pivot.Class asClass = PivotUtil.getOwningClass(asOperation);
			appendClassReference(AbstractGenModelHelper.OPERATIONS_PACKAGE_NAME, asClass);
			s.append("__");
			s.appendAndEncodeName(asOperation);
			return null;
		}

		@Override
		public @Nullable Object visitPackage(org.eclipse.ocl.pivot.@NonNull Package asPackage) {
			appendDeclaredClassName(asPackage);
			return null;
		}

		@Override
		public @Nullable Object visitProperty(@NonNull Property asProperty) {
			org.eclipse.ocl.pivot.Class asClass = PivotUtil.getOwningClass(asProperty);
			appendClassReference(AbstractGenModelHelper.PROPERTIES_PACKAGE_NAME, asClass);
			s.append("__");
			s.appendAndEncodeName(asProperty);
			if (asProperty.isIsImplicit()) {
				Property asOpposite = asProperty.getOpposite();
				if (asOpposite != null) {
					s.append("__");
					s.appendAndEncodeName(asOpposite);
				}
			}
			return null;
		}

		/*	@Override
			public @Nullable Object visitTupleType(@NonNull TupleType asTupleType) {
				GenPackage genPackage = genModelHelper.getGenPackage(asTupleType);
				s.appendClassReference(null, getQualifiedTablesClassName(genPackage));
				s.append(".");
				s.append("tuple_type_");			//
				s.appendUnscopedTypeName(asTupleType);
				return null;
			} */
	}

	/**
	 * EmitReferencedElementVisitor.accept(T) emits the name of T with the minimal necessary qualification for the configured
	 * tablesClassPath and nestedClassName resolving any TEmplateParameter references via the configured namespace's TemplateParameterization.
	 */
	public class EmitReferencedElementVisitor extends EmitDeclaredNameVisitor
	{
		/**
		 * Prevailing namespace of synthesized declaration - the provider of the TemplateParameterization.
		 */
		private @Nullable Namespace namespace = null;
		/**
		 * Prevailing class scope when synthesizing Java code.
		 */
		private @Nullable String tablesClassPath = null;
		/**
		 * Prevailing nested class scope when synthesizing Java code.
		 */
		private @Nullable String nestedClassName = null;

		protected EmitReferencedElementVisitor(@NonNull CodeGenString s) {
			super(s);
		}

		@Override
		protected void appendClassReference(@NonNull String nestedClassName, org.eclipse.ocl.pivot.@NonNull Class asClass) {
			GenPackage genPackage = genModelHelper.getGenPackage(asClass);
			String tablesClassPath = getQualifiedTablesClassName(genPackage);
			if (!tablesClassPath.equals(this.tablesClassPath)) {
				if (tablesClassPath.contains("UMLTables")) {
					getClass();		// XXX
				}
				s.appendClassReference(null, tablesClassPath);
				s.append(".");
				s.append(nestedClassName);
				s.append(".");
			}
			else if (!nestedClassName.equals(this.nestedClassName)) {
				s.append(nestedClassName);
				s.append(".");
			}
			appendDeclaredClassName(asClass);
		}

		public @Nullable Namespace basicGetNamespace() {
			return namespace;
		}

		public void setNamespace(Namespace namespace) {
			this.namespace = namespace;
		}

		public void setNestedClassName(@Nullable String nestedClassName) {
			this.nestedClassName = nestedClassName;
		}

		public void setTablesClassPath(@Nullable String tablesClassPath) {
			this.tablesClassPath = tablesClassPath;
		}

		@Override
		public @Nullable Object visiting(@NonNull Visitable visitable) {
			throw new UnsupportedOperationException("Unsupported EmitReferencedElementVisitor for " + visitable.eClass().getName());
		}

		@Override
		public @Nullable Object visitProperty(@NonNull Property asProperty) {
			org.eclipse.ocl.pivot.Class asClass = PivotUtil.getOwningClass(asProperty);
			GenClassifier genClassifier = genModelHelper.getGenClassifier(asClass);
			if (genClassifier == null) {
				return null;
			}
			GenModel genModel = genClassifier.getGenModel();
			if (genModel == null) {
				return null;
			}
			boolean isImplicit = asProperty.isIsImplicit();				// XXX
			if (!isImplicit) {
				return super.visitProperty(asProperty);
			}
			if (!Orphanage.isOrphan(asProperty)) {
				return super.visitProperty(asProperty);
			}
		//	GenFeature genFeature = genModelHelper.basicGetGenFeature(asProperty);
		//	if (genFeature != null) {
		//		GenPackage genFeaturePackage = genFeature.getGenPackage();
		//		if (genFeaturePackage == genPackage) {
		//			return super.visitProperty(asProperty);
		//		}
		//	}
		//	GenFeature genFeature2 = genModelHelper.basicGetGenFeature(asProperty.getOpposite());
		//	GenAnnotation genAnnotation = null; //genModel.getGenAnnotation(OCLinEcoreGenModelGeneratorAdapter.OCL_GENMODEL_URI);
		//	if ((genFeature == null) || (genAnnotation == null)) {
				s.append("createOpposite(");
				emitASClass(genClassifier);
				s.append(", ");
				s.appendString(PivotUtil.getName(asProperty));
				s.append(", ");
				asProperty.getOpposite().accept(this);
				s.append(")");
				return null;
		//	}
		//	else {
		//		return super.visitProperty(asProperty);
		//	}
		}

		@Override
		public @Nullable Object visitTemplateParameter(@NonNull TemplateParameter asTemplateParameter) {
			Namespace namespace2 = namespace;
			if (namespace2 != null) {
				s.append("LIBRARY.getTemplateParameter(");
				namespace2.accept(this);
				s.append(", ");
			}
			int index = asTemplateParameter.getTemplateParameterId().getIndex();
			Orphanage orphanage = environmentFactory.getOrphanage();
			NormalizedTemplateParameter normalizedTemplateParameter = Orphanage.getNormalizedTemplateParameter(orphanage, index);
			s.append(AbstractGenModelHelper.TYPE_PARAMETERS_PACKAGE_NAME);
			s.append(".");
			s.append(normalizedTemplateParameter.getName());
			if (namespace2 != null) {
				s.append(")");
			}
			return null;
		}
	}

	/**
	 * EmitTypeExpressionVisitor.accept(T) emits the an expression that computes T.
	 */
	public class EmitTypeExpressionVisitor extends AbstractExtendingVisitor<Object, Object>
	{
		protected final @NonNull CodeGenString s;

		protected EmitTypeExpressionVisitor(@NonNull CodeGenString s) {
			super(null);
			this.s = s;
		}

		@Override
		public @Nullable Object visiting(@NonNull Visitable visitable) {
			throw new UnsupportedOperationException("Unsupported DeclareParameterTypeVisitor for " + visitable.eClass().getName());
		}

		@Override
		public @Nullable Object visitClass(org.eclipse.ocl.pivot.@NonNull Class type) {
			type = PivotUtil.getUnspecializedTemplateableElement(type);
			GenClassifier genClassifier = genModelHelper.getGenClassifier(type);
			if (genClassifier == null) {
				return null;
			}
			GenModel genModel = genClassifier.getGenModel();
			if (genModel == null) {
				return null;
			}
			GenAnnotation genAnnotation = genModel.getGenAnnotation(OCLinEcoreGenModelGeneratorAdapter.OCL_GENMODEL_URI);
			if (genAnnotation != null) {					// has xxxTables
				type.accept(emitReferencedElement);
			}
			else {
				s.append("getASClass(");
				emitASClass(genClassifier);
				s.append(")");
			}
			return null;
		}

		@Override
		public @Nullable Object visitCollectionType(@NonNull CollectionType type) {
			s.append("LIBRARY.getCollectionType(");
			type.accept(emitReferencedElement);
			s.append(", ");
			type.getElementType().accept(this);
			s.append(")");
			return null;
		}

		@Override
		public Object visitDataType(@NonNull DataType type) {
			org.eclipse.ocl.pivot.Package dataTypePackage = PivotUtil.getContainingPackage(type);
			if (dataTypePackage != asPackage) {
				Type behavioralClass = type.getBehavioralClass();
				if (behavioralClass != null) {
					return behavioralClass.accept(this);				// Bug 577563 workaround to Avoid referencing potentially non-existing EcoreTables
				}
			}
			return super.visitDataType(type);
		}

		@Override
		public Object visitLambdaParameter(@NonNull LambdaParameter asLambdaParameter) {
			s.append(getLambdaParameterName(asLambdaParameter));
			return null;
		}

		@Override
		public @Nullable Object visitLambdaType(@NonNull LambdaType lambdaType) {
			s.append("LIBRARY.getLambdaType(");
			lambdaType.getOwnedContext().accept(this);
			for (LambdaParameter parameter : PivotUtil.getOwnedParameters(lambdaType)) {
				s.append(", ");
				parameter.accept(this);
			}
			s.append(", ");
			lambdaType.getOwnedResult().accept(this);
			s.append(")");
			return null;
		}

		@Override
		public @Nullable Object visitMapType(@NonNull MapType type) {
			s.append("LIBRARY.getMapType(");
			type.accept(emitReferencedElement);
			s.append(", ");
			type.getKeyType().accept(this);
			s.append(", ");
			type.getValueType().accept(this);
			s.append(")");
			return null;
		}

		@Override
		public Object visitNormalizedTemplateParameter(@NonNull NormalizedTemplateParameter asNormalizedTemplateParameter) {
			s.append(AbstractGenModelHelper.TYPE_PARAMETERS_PACKAGE_NAME);
			s.append(".");
			s.append(PivotUtil.getName(asNormalizedTemplateParameter));
			return null;
		}

		@Override
		public @Nullable Object visitTemplateParameter(@NonNull TemplateParameter asTemplateParameter) {
			asTemplateParameter.accept(emitReferencedElement);
			return null;
		}

		@Override
		public @Nullable Object visitTupleType(@NonNull TupleType tupleType) {
			s.append("LIBRARY.getTupleType(");
			boolean isFirst = true;
			for (@NonNull Property part : PivotUtil.getOwnedProperties(tupleType)) {
				if (!isFirst) {
					s.append(", ");
				}
				s.append("LIBRARY.getTuplePart(");
				s.appendString(PivotUtil.getName(part));
				s.append(", ");
				part.getType().accept(this);
				s.append(", ");
				s.append(part.isIsRequired() ? "true" : "false");
				s.append(")");
				isFirst = false;
			}
			s.append(")");
			return null;
		}
	}

	protected final boolean useNullAnnotations;
	protected final @NonNull GenPackage genPackage;
	protected final @NonNull EnvironmentFactory environmentFactory;
	protected final @NonNull CompleteModel completeModel;
	protected final @NonNull CompleteStandardLibrary standardLibrary;
	protected final @NonNull CodeGenString s;
	protected final org.eclipse.ocl.pivot.@NonNull Package asPackage;
	protected final @NonNull EmitTypeExpressionVisitor emitTypeExpression;			// emit LIBRARY.getSomething
	protected final @NonNull EmitDeclaredNameVisitor emitDeclaredName;				// emit _ZZZ
	protected final @NonNull EmitReferencedElementVisitor emitReferencedElement;	// emit XXXTables.YYY._ZZZ
	protected final @NonNull Iterable<org.eclipse.ocl.pivot.@NonNull Class> activeClassesSortedByName;
	protected final @NonNull Map<@NonNull ParameterTypes, String> legacyTemplateBindingsNames = new HashMap<>();
	protected final @NonNull Map<@NonNull ParameterTypes, String> templateBindingsNames = new HashMap<>();
	protected final @NonNull GenModelHelper genModelHelper;

	protected OCLinEcoreTablesUtils(@NonNull GenPackage genPackage) {
		GenModel genModel = ClassUtil.requireNonNull(genPackage.getGenModel());
		this.useNullAnnotations = OCLinEcoreGenModelGeneratorAdapter.useNullAnnotations(genModel);
		Resource genModelResource = genPackage.eResource();
		ResourceSet genModelResourceSet = genModelResource.getResourceSet();
		assert genModelResourceSet != null;
		this.environmentFactory = PivotUtil.getEnvironmentFactory(genModelResourceSet);
		this.completeModel = environmentFactory.getCompleteModel();
		this.standardLibrary = environmentFactory.getStandardLibrary();
		this.s = new CodeGenString(completeModel, useNullAnnotations);
		this.genPackage = genPackage;
		this.asPackage = ClassUtil.requireNonNull(getPivotPackage(genPackage));
		this.emitTypeExpression = new EmitTypeExpressionVisitor(s);
		this.emitDeclaredName = new EmitDeclaredNameVisitor(s);
		this.emitReferencedElement = new EmitReferencedElementVisitor(s);
		this.genModelHelper = AbstractGenModelHelper.create(environmentFactory, genPackage.getGenModel());
		this.activeClassesSortedByName = getActiveClassesSortedByName(asPackage);
	}

	protected void emitASClass(@NonNull GenClassifier genClassifier) {
		GenPackage genPackage = genClassifier.getGenPackage();
		//s.append(genClassifier.getQualifiedClassifierAccessor());			-- expanded below to expose import
		s.appendClassReference(null, genPackage.getQualifiedPackageInterfaceName());
		if (genPackage.isLiteralsInterface()) {
			s.append(".Literals.");
			s.append(genClassifier.getClassifierID());
		}
		else {
			s.append(".eINSTANCE.get");
			s.append(genClassifier.getClassifierAccessorName());
			s.append("()");
		}
	}

	protected @NonNull Iterable<org.eclipse.ocl.pivot.@NonNull Class> getActiveClassesSortedByName(org.eclipse.ocl.pivot.@NonNull Package asPackage) {
		List<org.eclipse.ocl.pivot.@NonNull Class> sortedClasses = new ArrayList<>(getActiveTypes(asPackage));
		Collections.sort(sortedClasses, nameComparator);
		return sortedClasses;
	}

	protected @NonNull Set<? extends org.eclipse.ocl.pivot.@NonNull Class> getActiveTypes(org.eclipse.ocl.pivot.@NonNull Package asPackage) {
		org.eclipse.ocl.pivot.Package oclstdlibPackage = standardLibrary.getBooleanType().getOwningPackage();
		org.eclipse.ocl.pivot.Package pivotMetamodel = completeModel.getASmetamodel();
		Type elementType = completeModel.getASClass("Element");
		if (oclstdlibPackage == asPackage) {
			VoidType oclVoidType = standardLibrary.getOclVoidType();
			Set<org.eclipse.ocl.pivot.@NonNull Class> types = new HashSet<>();
			for (org.eclipse.ocl.pivot.Class type : oclstdlibPackage.getOwnedClasses()) {
				assert type != null;
				CompleteClass completeClass = completeModel.getCompleteClass(type);
				if ((elementType == null) || !completeClass.isElementType(standardLibrary, elementType, oclVoidType)) {
					types.add(type);
				}
			}
			return types;
		}
		else if (pivotMetamodel == asPackage) {
			Set<org.eclipse.ocl.pivot.@NonNull Class> types = new HashSet<>();
			for (org.eclipse.ocl.pivot.Class type : pivotMetamodel.getOwnedClasses()) {
				assert type != null;
				boolean pruned = false;
				Type myType = null;
				CompleteClass completeClass = completeModel.getCompleteClass(type);
				for (org.eclipse.ocl.pivot.Class partialClass : completeClass.getPartialClasses()) {
					org.eclipse.ocl.pivot.Package partialPackage = partialClass.getOwningPackage();
					if (partialPackage == oclstdlibPackage) {
						if ((elementType != null) && !completeClass.conformsTo(standardLibrary, elementType)) {
							//							System.out.println("Prune " + type.getName());
							pruned = true;
						}
					}
					else if (partialPackage == asPackage) {
						myType = type;
					}
				}
				if (!pruned && (myType instanceof org.eclipse.ocl.pivot.Class)) {
					types.add((org.eclipse.ocl.pivot.Class)myType);
				}
			}
			//			if (oclstdlibPackage != null) {
			//				for (DomainType type : oclstdlibPackage.getOwnedType()) {
			//					types.remove(type.getName());
			//				}
			//			}
			return types;
		}
		else {
			return new HashSet<>(ClassUtil.nullFree(asPackage.getOwnedClasses()));
		}
	}

	protected @NonNull Iterable<org.eclipse.ocl.pivot.@NonNull Class> getAllProperSupertypesSortedByName(org.eclipse.ocl.pivot.@NonNull Class pClass) {
		org.eclipse.ocl.pivot.Class theClass = completeModel.getPrimaryClass(pClass);
		Map<org.eclipse.ocl.pivot.@NonNull Class, @NonNull Integer> results = new HashMap<>();
		getAllSuperClasses(results, theClass);
		List<org.eclipse.ocl.pivot.@NonNull Class> sortedClasses = new ArrayList<>(results.keySet());
		sortedClasses.remove(theClass);
		Collections.sort(sortedClasses, nameComparator);
		return sortedClasses;
	}

	protected @NonNull List<org.eclipse.ocl.pivot.@NonNull Class> getAllSupertypesSortedByName(org.eclipse.ocl.pivot.@NonNull Class pClass) {
		Map<org.eclipse.ocl.pivot.@NonNull Class, @NonNull Integer> results = new HashMap<>();
		getAllSuperClasses(results, pClass);
		List<org.eclipse.ocl.pivot.@NonNull Class> sortedClasses = new ArrayList<>(results.keySet());
		Collections.sort(sortedClasses, nameComparator);
		return sortedClasses;
	}

	protected int getAllSuperClasses(@NonNull Map<org.eclipse.ocl.pivot.@NonNull Class, @NonNull Integer> results, org.eclipse.ocl.pivot.@NonNull Class aClass) {
		org.eclipse.ocl.pivot.Class theClass = completeModel.getPrimaryClass(aClass);
		Integer depth = results.get(theClass);
		if (depth != null) {
			return depth;
		}
		int myDepth = 0;
		for (@NonNull CompleteClass superCompleteClass : completeModel.getAllSuperCompleteClasses(theClass)) {
			org.eclipse.ocl.pivot.Class superClass = superCompleteClass.getPrimaryClass();
			if (superClass != theClass) {
				superClass = PivotUtil.getUnspecializedTemplateableElement(superClass);
				int superDepth = getAllSuperClasses(results, superClass);
				if (superDepth >= myDepth) {
					myDepth = superDepth+1;
				}
			}
		}
		results.put(theClass, myDepth);
		return myDepth;
	}

	protected org.eclipse.ocl.pivot.@Nullable Package getExtendedPackage(org.eclipse.ocl.pivot.@NonNull Package asPackage) {
		org.eclipse.ocl.pivot.Package oclstdlibPackage = standardLibrary.getBooleanType().getOwningPackage();
		org.eclipse.ocl.pivot.Package pivotMetamodel = completeModel.getASmetamodel();
		if (oclstdlibPackage == asPackage) {
			return null;
		}
		else if (pivotMetamodel == asPackage) {
			return oclstdlibPackage;
		}
		else {
			return null;
		}
	}

	public @Nullable GenPackage getGenPackage() {
		return genPackage;
	}

/*	protected @Nullable GenPackage getGenPackage(org.eclipse.ocl.pivot.@NonNull Class asClass) {
	//	GenPackage genPackage1 = getGenPackage1(asClass);
		GenPackage genPackage2 = genModelHelper.getGenPackage(asClass);
	//	assert genPackage1 == genPackage2;
		return genPackage2;
	} */

/*	protected @Nullable GenPackage getGenPackage1(org.eclipse.ocl.pivot.@NonNull Class asClass) {
		org.eclipse.ocl.pivot.Package asPackage = asClass.getOwningPackage();
		assert asPackage != null;
		Package oclstdlibPackage = standardLibrary.getBooleanType().getOwningPackage();
		org.eclipse.ocl.pivot.Class elementType = metamodelManager.getASClass("Element");
		if ((elementType != null) && (oclstdlibPackage != null)) {
			VoidType oclVoidType = metamodelManager.getStandardLibrary().getOclVoidType();
			org.eclipse.ocl.pivot.Package pivotMetamodel = elementType.getOwningPackage();
			assert pivotMetamodel != null;
			if (oclstdlibPackage == asPackage) {
				CompleteClass completeClass = metamodelManager.getCompleteClass(asClass);
				if (isElementType(completeClass, elementType, oclVoidType)) {
					return getGenPackage(pivotMetamodel);
				}
				else {
					return getGenPackage(oclstdlibPackage);
				}
			}
			else if (pivotMetamodel == asPackage) {
				CompleteClass completeClass = metamodelManager.getCompleteClass(asClass);
				for (org.eclipse.ocl.pivot.Class partialClass : completeClass.getPartialClasses()) {
					org.eclipse.ocl.pivot.Package partialPackage = partialClass.getOwningPackage();
					if (partialPackage == oclstdlibPackage) {
						if (!isElementType(completeClass, elementType, oclVoidType)) {
							return getGenPackage(oclstdlibPackage);
						}
					}
				}
				return getGenPackage(pivotMetamodel);
			}
		}
		return getGenPackage(asPackage);
	} */

/*	protected @Nullable GenPackage getGenPackage(org.eclipse.ocl.pivot.@NonNull Package asPackage) {
	//	GenPackage genPackage1 = getGenPackage1(asPackage);
		GenPackage genPackage2 = genModelHelper.getGenPackage(asPackage);
	//	assert genPackage1 == genPackage2;
		return genPackage2;
	} */

/*	protected @Nullable GenPackage getGenPackage1(org.eclipse.ocl.pivot.@NonNull Package asPackage) {
		List<@NonNull GenPackage> usedGenPackages;
		ResourceSet genModelResourceSet;
		GenPackage genPackage2 = genPackage;
		EPackage firstEPackage = genPackage2.getEcorePackage();
		if (getName(firstEPackage).equals(asPackage.getName())) {
			return genPackage2;
		}
		usedGenPackages = ClassUtil.nullFree(genPackage2.getGenModel().getUsedGenPackages());
		assert usedGenPackages != null;
		//		String nsURI = asPackage.getNsURI();
		//		String name = asType.getName();
		//		GenPackage usedGenPackage = getNsURIGenPackage(usedGenPackages, nsURI, name);
		//		if (usedGenPackage != null) {
		//			return usedGenPackage;
		//		}
		Resource genModelResource = genPackage2.eResource();
		genModelResourceSet = genModelResource.getResourceSet();
		assert genModelResourceSet != null;
		org.eclipse.ocl.pivot.Package metamodelPackage = metamodelManager.getASmetamodel();
		org.eclipse.ocl.pivot.Package libraryPackage = metamodelManager.getLibraries().get(0);
		if (asPackage == libraryPackage) {
			GenPackage libraryGenPackage = getLibraryGenPackage(usedGenPackages);
			if (libraryGenPackage == null) {
				libraryGenPackage = loadGenPackage(genModelResourceSet, LibraryConstants.GEN_MODEL_URI);
			}
			return libraryGenPackage;
		}
		if (asPackage == metamodelPackage) {
			GenPackage metamodelGenPackage = getMetamodelGenPackage(usedGenPackages);
			if (metamodelGenPackage == null) {
				metamodelGenPackage = loadGenPackage(genModelResourceSet, PivotConstantsInternal.GEN_MODEL_URI);
			}
			return metamodelGenPackage;
		}
		String nsURI = asPackage.getURI();
		if (nsURI != null) {
			GenPackage genPackage3 = metamodelManager.getGenPackage(nsURI);
			if (genPackage3 != null) {
				return genPackage3;
			}
			for (@NonNull GenPackage usedGenPackage : usedGenPackages) {
				metamodelManager.addGenPackage(usedGenPackage);
			}
			genPackage3 = metamodelManager.getGenPackage(nsURI);
			if (genPackage3 != null) {
				return genPackage3;
			}
		}
		throw new IllegalStateException("No GenPackage for '" + nsURI + "'");
	} */

	protected @NonNull String getImplementationName(@NonNull Operation operation) {
		if (operation.getImplementationClass() != null) {
			return operation.getImplementationClass() + ".INSTANCE";
		}
		else {
			//		    List<Constraint> constraints = operation.getOwnedRule();
			//			if (constraints.size() > 0) {
			//				return getQualifiedBodiesClassName(ClassUtil.requireNonNull(operation.getOwningType())) + "._" + operation.getName() + "_" + constraints.get(0).getStereotype() + "_.INSTANCE";
			//			}
			//			else {
			return "null";
			//			}
		}
	}

	protected @NonNull String getLegacyTemplateBindingsName(@NonNull ParameterTypes templateBindings) {
		String name2 = legacyTemplateBindingsNames.get(templateBindings);
		if (name2 == null) {
			StringBuilder s = new StringBuilder();
			s.append("_");
			if (templateBindings.size() > 0 ) {
				for (int i = 0; i < templateBindings.size(); i++) {
					if (i > 0) {
						s.append("___");
					}
					Type element = templateBindings.get(i);
					getLegacyTemplateBindingsName(s, element);
				}
			}
			name2 = s.toString();
			legacyTemplateBindingsNames.put(templateBindings, name2);
		}
		return name2;
	}
	private void getLegacyTemplateBindingsName(@NonNull StringBuilder s, @NonNull Type element) {
		TemplateParameter templateParameter = element.isTemplateParameter();
		if (templateParameter != null) {
			TemplateableElement template = templateParameter.getOwningSignature().getOwningElement();
			if (template instanceof Operation) {
				s.append(AbstractGenModelHelper.encodeName(ClassUtil.requireNonNull(((Operation) template).getOwningClass())));
				s.append("_");
			}
			s.append(AbstractGenModelHelper.encodeName(ClassUtil.requireNonNull((NamedElement) template)));
			s.append("_");
		}
		s.append(AbstractGenModelHelper.encodeName(element));
		if (element instanceof TemplateableElement) {
			BasicTemplateSpecialization templateSpecialization = TemplateSpecialization.basicGetTemplateSpecialization(element);
			if (templateSpecialization != null) {
				s.append("_");
				for (@NonNull Type actual : templateSpecialization) {
					s.append("_");
					getLegacyTemplateBindingsName(s, actual);
				}
				s.append("__");
			}
		}
		if (element instanceof LambdaType) {
			LambdaType lambdaType = (LambdaType)element;
			s.append("_");
			getLegacyTemplateBindingsName(s, PivotUtil.getType(PivotUtil.getOwnedContext(lambdaType)));
			for (@NonNull LambdaParameter parameter : PivotUtil.getOwnedParameters(lambdaType)) {
				s.append("_");
				getLegacyTemplateBindingsName(s, PivotUtil.getType(parameter));
			}
			s.append("_");
			getLegacyTemplateBindingsName(s, PivotUtil.getType(PivotUtil.getOwnedResult(lambdaType)));
		}
	}

	protected @NonNull String getLambdaParameterName(LambdaParameter lambdaParameter) {
		StringBuilder s = new StringBuilder();
		s.append("_");
		s.append(lambdaParameter.getName());
		s.append("_");
		getTemplateBindingsName(s, PivotUtil.getType(lambdaParameter));
		s.append("_");
		s.append(lambdaParameter.isIsRequired() ? "T" : "F");
		return s.toString();
	}

	protected @NonNull Iterable<@NonNull Operation> getLocalOperationsSortedBySignature(org.eclipse.ocl.pivot.@NonNull Class pClass) {
		// cls.getOperations()->sortedBy(op2 : Operation | op2.getSignature())
		List<@NonNull Operation> sortedOperations = new ArrayList<>(getOperations(pClass));
		Collections.sort(sortedOperations, signatureComparator);
		return sortedOperations;
	}

	protected @NonNull List<@NonNull Property> getLocalPropertiesSortedByName(org.eclipse.ocl.pivot.@NonNull Class pClass) {
		List<@NonNull Property> sortedProperties = new ArrayList<>();
		for (/*@NonNull*/ Property property : getProperties(pClass)) {
			assert property != null;
			if (isProperty(property)) {
				sortedProperties.add(property);
			}
		}
		Collections.sort(sortedProperties, propertyComparator);
		return sortedProperties;
	}

	protected @NonNull LinkedHashSet<@NonNull Operation> getOperations(org.eclipse.ocl.pivot.@NonNull Class type) {
		LinkedHashSet<@NonNull Operation> operations = new LinkedHashSet<>();
		for (@NonNull Operation operation : completeModel.getMemberOperations(type, false)) {
			operations.add(operation);
		}
		for (@NonNull Operation operation : completeModel.getMemberOperations(type, true)) {
			operations.add(operation);
		}
		return operations;
	}

	protected @NonNull Operation getOverloadOp(org.eclipse.ocl.pivot.@NonNull Class pClass, @NonNull Operation baseOp) {
		String baseSignature = getSignature(baseOp);
		Map<org.eclipse.ocl.pivot.@NonNull Class, @NonNull Integer> results = new HashMap<>();
		getAllSuperClasses(results, pClass);
		int bestDepth = -1;
		Operation best = null;
		for (org.eclipse.ocl.pivot.Class aClass : results.keySet()) {
			Integer aDepth = results.get(aClass);
			assert aDepth != null;
			for (Operation op : getOperations(ClassUtil.requireNonNull(aClass))) {
				if (baseSignature.equals(getSignature(ClassUtil.requireNonNull(op))) && (aDepth > bestDepth)) {
					bestDepth = aDepth;
					best = op;
				}
			}
		}
		assert best != null;
		return best;
	}

	protected org.eclipse.ocl.pivot.Package getPivotPackage(@NonNull GenPackage genPackage) {
		EPackage ePackage = genPackage.getEcorePackage();
		Resource ecoreResource = ePackage.eResource();
		if (ecoreResource == null) {
			return null;
		}
		External2AS ecore2as = External2AS.getAdapter(ecoreResource, environmentFactory);
		return ecore2as.getCreated(org.eclipse.ocl.pivot.Package.class, ePackage);
	}

	protected @NonNull LinkedHashSet<@NonNull Property> getProperties(org.eclipse.ocl.pivot.@NonNull Class type) {
		Set<String> names = new HashSet<>();
		LinkedHashSet<@NonNull Property> properties = new LinkedHashSet<>();
		for (@NonNull Property property : completeModel.getMemberProperties(type, true)) {
			names.add(property.getName());
			properties.add(completeModel.getPrimaryProperty(property));
		}
		for (@NonNull Property property : completeModel.getMemberProperties(type, false)) {
			if (!names.contains(property.getName())) {
				properties.add(completeModel.getPrimaryProperty(property));
			}
		}
		return properties;
	}

	protected @NonNull String getQualifiedTablesClassName(@Nullable GenPackage genPackage) {
		if (genPackage != null) {
			return genPackage.getReflectionPackageName() + "." + getTablesClassName(genPackage);
		}
		else {
			return "UnknownMetamodelTables";
		}
	}

	protected @NonNull String getSharedLibrary() {
		org.eclipse.ocl.pivot.Package thisPackage = getPivotPackage(genPackage);
		if (thisPackage != null) {
			PrimitiveType booleanType = standardLibrary.getBooleanType();
			org.eclipse.ocl.pivot.Package libraryPackage = booleanType.getOwningPackage();
			if (libraryPackage != null) {
				GenPackage gPackage = genModelHelper.getGenPackage(libraryPackage);
				if (gPackage != null) {
					return gPackage.getReflectionPackageName() + "." + gPackage.getPrefix() + AbstractGenModelHelper.TABLES_CLASS_SUFFIX;
				}
			}
		}
		/*		TypeServer typeServer = metamodelManager.getTypeServer(booleanType);
			for (DomainType type : typeServer.getPartialTypes()) {
				org.eclipse.ocl.pivot.Package asPackage = type.getPackage();
				if ((asPackage != null) && (asPackage != thisPackage)) {
					GenPackage gPackage = getGenPackage(genPackage, asPackage);
					if (gPackage != null) {
						return getInterfacePackageName(gPackage) + "." + gPackage.getPrefix() + AbstractGenModelHelper.TABLES_CLASS_SUFFIX;
					}
				}
			} */
		return "";
	}

	public static @NonNull String getSignature(@NonNull Operation anOperation) {
		org.eclipse.ocl.pivot.Class owningType = anOperation.getOwningClass();
		if (owningType == null) {
			return "null";
		}
		String qualifiedSignature = PrettyPrinter.printType(anOperation, owningType);
		int colonColonIndex = qualifiedSignature.indexOf("::");
		int parenthesisIndex = qualifiedSignature.indexOf("(");
		if ((parenthesisIndex < 0) ? (colonColonIndex > 0) : (colonColonIndex < parenthesisIndex)) {	// FIXME use a decent inherently right algorithm
			@NonNull String substring = qualifiedSignature.substring(colonColonIndex+1);
			return substring;
		}
		else {
			return qualifiedSignature;	// FIXME with PrettyPrintOptions
		}
	}

	protected @NonNull String getTablesClassName(@NonNull GenPackage genPackage) {
		return genPackage.getPrefix() + AbstractGenModelHelper.TABLES_CLASS_SUFFIX;
	}

	private void getTemplateBindingsName(@NonNull StringBuilder s, @NonNull LambdaParameter lambdaParameter) {
		s.append(AbstractGenModelHelper.encodeName(lambdaParameter));
		s.append("_");
		getTemplateBindingsName(s, PivotUtil.getType(lambdaParameter));
		s.append("_");
		s.append(lambdaParameter.isIsRequired() ? "T" : "F");
	}

	protected @NonNull String getTemplateBindingsName(@NonNull ParameterTypes templateBindings) {
		String name2 = templateBindingsNames.get(templateBindings);
		if (name2 == null) {
			StringBuilder s = new StringBuilder();
			s.append("_");
			if (templateBindings.size() > 0 ) {
				for (int i = 0; i < templateBindings.size(); i++) {
					if (i > 0) {
						s.append("___");
					}
					Type element = templateBindings.get(i);
					getTemplateBindingsName(s, element);
				}
			}
			name2 = s.toString();
			templateBindingsNames.put(templateBindings, name2);
		}
		return name2;
	}
	private void getTemplateBindingsName(@NonNull StringBuilder s, @NonNull Type element) {
	//	TemplateParameter templateParameter = element.isTemplateParameter();
	//	if (templateParameter != null) {
	//		s.append(Integer.toString(templateParameter.getTemplateParameterId().getIndex()));
	//		s.append("_");
	//	}
		s.append(AbstractGenModelHelper.encodeName(element));
		if (element instanceof TemplateableElement) {
			BasicTemplateSpecialization templateSpecialization = TemplateSpecialization.basicGetTemplateSpecialization(element);
			if (templateSpecialization != null) {
				s.append("_");
				for (@NonNull Type actual : templateSpecialization) {
					s.append("_");
					getTemplateBindingsName(s, actual);
				}
				s.append("__");
			}
		}
		if (element instanceof LambdaType) {
			LambdaType lambdaType = (LambdaType)element;
			s.append("_");
			getTemplateBindingsName(s, PivotUtil.getOwnedContext(lambdaType));
			for (/*@NonNull*/ LambdaParameter parameter : lambdaType.getOwnedParameters()) {
				assert parameter != null;
				s.append("_");
				getTemplateBindingsName(s, parameter);
			}
			s.append("_");
			getTemplateBindingsName(s, PivotUtil.getOwnedResult(lambdaType));
		}
	}

	protected @NonNull String getTemplateParameterName(@NonNull TemplateParameter asTemplateParameter) {
		TemplateParameterId asTemplateParameterId = asTemplateParameter.getTemplateParameterId();
		return "_" + asTemplateParameterId.getIndex() + "_" + asTemplateParameter.getName();
	}

	/**
	 * Return true if property has an Ecore counterpart. Non-navigable opposites may have a Property
	 * but no Ecore EReference.
	 */
	protected @NonNull Boolean hasEcore(@NonNull Property property) {
		org.eclipse.ocl.pivot.Class owningType = property.getOwningClass();
		if (owningType == null) {
			return false;
		}
	//	String typeName = owningType.getName();
	//	if (typeName == null) {
	//		return false;
	//	}
	//	List<@NonNull GenClass> genClasses = ClassUtil.nullFree(genPackage.getGenClasses());
		GenClassifier genClassifier = genModelHelper.getGenClassifier(owningType);
		if (genClassifier == null) {
			return false;
		}
		String propertyName = property.getName();
		if (propertyName == null) {
			return false;
		}
		List<@NonNull GenFeature> genFeatures = ClassUtil.nullFree(((GenClass)genClassifier).getAllGenFeatures());
		for (@NonNull GenFeature genFeature : genFeatures) {
			EStructuralFeature eFeature = genFeature.getEcoreFeature();
			if (genModelHelper.getName(eFeature).equals(propertyName)) {
				return true;
			}
		}
		return false;
	}

	protected boolean hasSharedLibrary() {
		org.eclipse.ocl.pivot.Package thisPackage = getPivotPackage(genPackage);
		PrimitiveType booleanType = standardLibrary.getBooleanType();
		org.eclipse.ocl.pivot.Package libraryPackage = booleanType.getOwningPackage();
		return thisPackage != libraryPackage;
	}

	protected boolean isLambdaParameterList(@NonNull ParametersId parametersId) {
		for (TypeId typeId : parametersId) {
			if (typeId instanceof LambdaTypeId) {
				return true;
			}
		}
		return false;
	}

	protected boolean isProperty(@NonNull Property prop) {
		if (hasEcore(prop)) {
			return true;
		}
		Property opposite = prop.getOpposite();
		return (opposite != null) && hasEcore(opposite);
	}

	/**
	 * Return true if type has an Ecore counterpart. The Standard Library genmodel has
	 * no Ecore types, unless the Pivot model is also in use.
	 */
	protected @NonNull Boolean hasEcore(@NonNull Type type) {
		String typeName = type.getName();
		if (typeName != null) {
			List<@NonNull GenClassifier> genClassifiers = ClassUtil.nullFree(genPackage.getGenClassifiers());
			GenClassifier genClassifier = getGenClassifier(genClassifiers, typeName);
			if (genClassifier != null) {
				return true;
			}
		}
		return false;
	}

	private @Nullable <T extends GenClassifier> T getGenClassifier(@Nullable List<T> genClassifiers, @NonNull String name) {
		if (genClassifiers != null) {
			for (T genClassifier : genClassifiers) {
				assert genClassifier != null;
				EClassifier ecoreClassifier = genClassifier.getEcoreClassifier();
				String ecoreName = genModelHelper.getName(ecoreClassifier);
				if (ecoreName.equals(name)) {
					return genClassifier;
				}
			}
		}
		return null;
	}
}

