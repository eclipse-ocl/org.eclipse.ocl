/*******************************************************************************
 * Copyright (c) 2013, 2021 CEA LIST and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink (CEA LIST) - initial API and implementation
 *   E.D.Willink (CEA LIST) - Bug 424034
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.oclinecore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.codegen.util.ImportManager;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.generator.AbstractGenModelHelper;
import org.eclipse.ocl.examples.codegen.genmodel.OCLGenModelUtil;
import org.eclipse.ocl.pivot.AnyType;
import org.eclipse.ocl.pivot.BagType;
import org.eclipse.ocl.pivot.BooleanType;
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.CompleteClass;
import org.eclipse.ocl.pivot.Enumeration;
import org.eclipse.ocl.pivot.EnumerationLiteral;
import org.eclipse.ocl.pivot.InvalidType;
import org.eclipse.ocl.pivot.LambdaType;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.OrderedSetType;
import org.eclipse.ocl.pivot.ParameterTypes;
import org.eclipse.ocl.pivot.PrimitiveType;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.SequenceType;
import org.eclipse.ocl.pivot.SetType;
import org.eclipse.ocl.pivot.TemplateParameter;
import org.eclipse.ocl.pivot.TemplateParameters;
import org.eclipse.ocl.pivot.TemplateSignature;
import org.eclipse.ocl.pivot.TemplateableElement;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.VoidType;
import org.eclipse.ocl.pivot.ids.IdManager;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.library.ecore.EcoreExecutorAnyType;
import org.eclipse.ocl.pivot.internal.library.ecore.EcoreExecutorBagType;
import org.eclipse.ocl.pivot.internal.library.ecore.EcoreExecutorBooleanType;
import org.eclipse.ocl.pivot.internal.library.ecore.EcoreExecutorCollectionType;
import org.eclipse.ocl.pivot.internal.library.ecore.EcoreExecutorEnumeration;
import org.eclipse.ocl.pivot.internal.library.ecore.EcoreExecutorEnumerationLiteral;
import org.eclipse.ocl.pivot.internal.library.ecore.EcoreExecutorInvalidType;
import org.eclipse.ocl.pivot.internal.library.ecore.EcoreExecutorOrderedSetType;
import org.eclipse.ocl.pivot.internal.library.ecore.EcoreExecutorPackage;
import org.eclipse.ocl.pivot.internal.library.ecore.EcoreExecutorPrimitiveType;
import org.eclipse.ocl.pivot.internal.library.ecore.EcoreExecutorProperty;
import org.eclipse.ocl.pivot.internal.library.ecore.EcoreExecutorSequenceType;
import org.eclipse.ocl.pivot.internal.library.ecore.EcoreExecutorSetType;
import org.eclipse.ocl.pivot.internal.library.ecore.EcoreExecutorType;
import org.eclipse.ocl.pivot.internal.library.ecore.EcoreExecutorVoidType;
import org.eclipse.ocl.pivot.internal.library.ecore.EcoreLibraryOppositeProperty;
import org.eclipse.ocl.pivot.internal.library.executor.ExecutorFragment;
import org.eclipse.ocl.pivot.internal.library.executor.ExecutorOperation;
import org.eclipse.ocl.pivot.internal.library.executor.ExecutorProperty;
import org.eclipse.ocl.pivot.internal.library.executor.ExecutorPropertyWithImplementation;
import org.eclipse.ocl.pivot.internal.library.executor.ExecutorStandardLibrary;
import org.eclipse.ocl.pivot.internal.library.executor.ExecutorType;
import org.eclipse.ocl.pivot.internal.library.executor.ExecutorTypeParameter;
import org.eclipse.ocl.pivot.utilities.AbstractTables;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.TypeUtil;

public class OCLinEcoreTables extends OCLinEcoreTablesUtils
{
	/**
	 * Process a nested <%...%> to return its equivalent string and request any necessary imports.
	 */
	private class NestedImport
	{
		private @NonNull StringBuilder result = new StringBuilder();

		public int process(@NonNull String constants, int startIndex) {
			int iMax = constants.length();
			String nestedString = null;
			int i = startIndex;
			int iStart = -1;
			int iEnd = -1;
			while (i < iMax) {
				char c = constants.charAt(i++);
				if ((c == '<') && (i < iMax) && (constants.charAt(i) == '%')) {
					if (nestedString != null) {
						result.append(constants.substring(startIndex, iStart));
						result.append(nestedString);
						result.append(constants.substring(iEnd, i-1));
						i -= 2;
						break;
					}
					iStart = i-1;
					NestedImport nestedImport = new NestedImport();
					i = iEnd = nestedImport.process(constants, ++i);
					nestedString = nestedImport.toString();
				}
				else if ((c == '%') && (i < iMax) && (constants.charAt(i) == '>')) {
					String possibleClassName = iStart >= 0 ? constants.substring(startIndex, iStart) + constants.substring(iEnd, i-1) : constants.substring(startIndex, i-1);
					if (Character.isJavaIdentifierStart(possibleClassName.charAt(0))) {
						String importName = s.addImport(null, possibleClassName);
						if (!importName.equals(possibleClassName)) {
							if (nestedString != null) {
								result.append(nestedString);
							}
							result.append(importName);
							return ++i;
						}
					}
					result.append(constants.substring(startIndex, iStart));
					result.append(nestedString);
					result.append(constants.substring(iEnd, i-1));
					return ++i;
				}
			}
			while (i < iMax) {
				char c = constants.charAt(i++);
				if ((c == '<') && (i < iMax) && (constants.charAt(i) == '%')) {
					NestedImport nestedImport = new NestedImport();
					i = nestedImport.process(constants, ++i);
					result.append(nestedImport.toString());
				}
				else if ((c == '%') && (i < iMax) && (constants.charAt(i) == '>')) {
					return ++i;
				}
				else {
					result.append(c);
				}
			}
			return i;
		}

		@Override
		public @NonNull String toString() {
			return result.toString();
		}
	}
	private final @Nullable String tablesPostamble;
	private @Nullable String precedingPackageName = null;		// Initialization linkage
	private @Nullable String currentPackageName = null;			// Initialization linkage
	protected final @NonNull ImportManager importManager;
	private final @NonNull Set<@NonNull CompleteClass> allInstancesCompleteClasses = new HashSet<>();
	private final @NonNull Set<@NonNull Property> implicitOppositeProperties = new HashSet<>();

	public OCLinEcoreTables(@NonNull GenPackage genPackage) {
		super(genPackage);
		GenModel genModel = ClassUtil.nonNullState(genPackage.getGenModel());
		this.tablesPostamble = OCLinEcoreGenModelGeneratorAdapter.tablesPostamble(genModel);
		this.importManager = new ImportManager(getTablesPackageName());
	}

	public void analyzeExpressions() {
		environmentFactory.analyzeExpressions(asPackage, allInstancesCompleteClasses, implicitOppositeProperties);
	}

	public void appendClassSuperClassName(org.eclipse.ocl.pivot.@NonNull Class asClass, org.eclipse.ocl.pivot.@NonNull Class asSuperClass) {
		asClass.accept(emitLiteralVisitor);
		s.append("__");
		s.appendUnscopedTypeName(asSuperClass);
	}

	protected void appendConstants(@NonNull String constants) {
		s.append("	/**\n");
		s.append("	 *	Constants used by auto-generated code.\n");
		s.append("	 */\n");
		int i = 0;
		int iMax = constants.length();
		if (OCLGenModelUtil.INSTANCE.useNestedImports()) {
			while (i < iMax) {
				char c = constants.charAt(i++);
				if ((c == '<') && (i < iMax) && (constants.charAt(i) == '%')) {
					NestedImport nestedImport = new NestedImport();
					i = nestedImport.process(constants, ++i);
					s.append(nestedImport.toString());
				}
				else {
					s.append(c);
				}
			}
		}
		else {
			while (i < iMax) {
				int j = constants.indexOf("<%", i);
				if (j >= 0) {
					int k = constants.indexOf("%>", j+2);
					if (k >= 0) {
						s.append(constants.substring(i, j));
						Boolean isRequired = null;
						String longClassName;
						int atStart = constants.indexOf("@", j+2);
						if ((0 <= atStart) && (atStart <= k)) {
							int atEnd = constants.indexOf(" ", atStart);
							String longAnnotationName = constants.substring(atStart+1, atEnd);
							if (NonNull.class.getName().equals(longAnnotationName)) {
								isRequired = true;
							}
							else if (Nullable.class.getName().equals(longAnnotationName)) {
								isRequired = false;
							}
							longClassName = constants.substring(j+2, atStart) + constants.substring(atEnd+1, k);
						}
						else {
							longClassName = constants.substring(j+2,  k);
						}
						s.appendClassReference(isRequired, longClassName);
						i = k+2;
					}
					else {
						break;
					}
				}
				else {
					break;
				}
			}
			s.append(constants.substring(i));
		}
	}

	protected void appendInitializationStart(@NonNull String name) {
		currentPackageName = name;
		s.append("\t\tstatic {\n");
		//		s.append("\t\t\tSystem.out.println(\"" + getTablesClassName() + "::" + currentPackageName + " Start\");\n");
		s.append("\t\t\tInit.initStart();\n");
		if (precedingPackageName != null) {
			s.append("\t\t\t" + precedingPackageName + ".init();\n");
		}
		s.append("\t\t}\n");
	}

	protected void appendInitializationEnd(boolean isContinuation) {
		if (!isContinuation) {
			s.append("\n");
			s.append("\t\tstatic {\n");
		}
		s.append("\t\t\tInit.initEnd();\n");
		//		s.append("\t\t\tSystem.out.println(\"" + getTablesClassName() + "::" + currentPackageName + " End\");\n");
		s.append("\t\t}\n");
		s.append("\n");
		s.append("\t\t/**\n");
		s.append("\t\t * Force initialization of the fields of " + getTablesClassName() + "::" + currentPackageName + " and all preceding sub-packages.\n");
		s.append("\t\t */\n");
		s.append("\t\tpublic static void init() {}\n");
		precedingPackageName = currentPackageName;
	}

	protected void appendParameterTypesName(@NonNull ParameterTypes parameterTypes) {	// Workaround deprecated _ name
		if (parameterTypes.size() > 0) {
			s.append("Parameters.");
			s.append(getTemplateBindingsName(parameterTypes));
		}
		else {
			s.appendClassReference(null, TypeUtil.class);
			s.append(".EMPTY_PARAMETER_TYPES");
		}
	}

	protected void appendTypeFlags(@NonNull Type type) {
		if (type instanceof OrderedSetType) {
			s.appendClassReference(null, ExecutorType.class);
			s.append(".ORDERED | ");
			s.appendClassReference(null, ExecutorType.class);
			s.append(".UNIQUE");
		}
		else if (type instanceof SetType) {
			s.appendClassReference(null, ExecutorType.class);
			s.append(".UNIQUE");
		}
		else if (type instanceof SequenceType) {
			s.appendClassReference(null, ExecutorType.class);
			s.append(".ORDERED");
		}
		else {
			s.append("0");
		}
		if ((type instanceof org.eclipse.ocl.pivot.Class) && ((org.eclipse.ocl.pivot.Class)type).isIsAbstract()) {
			s.append(" | ");
			s.appendClassReference(null, ExecutorType.class);
			s.append(".ABSTRACT");
		}
	}

	protected @NonNull String atNonNull() {
		if (useNullAnnotations) {
			//	s.addClassReference("NonNull", "org.eclipse.jdt.annotation.NonNull");
			s.addClassReference(null, NonNull.class);
			return "@NonNull";
		}
		else {
			return "/*@NonNull*/";
		}
	}

	protected @NonNull String atNullable() {
		if (useNullAnnotations) {
			//	s.addClassReference("NonNull", "org.eclipse.jdt.annotation.NonNull");
			s.addClassReference(null, Nullable.class);
			return "@Nullable";
		}
		else {
			return "/*@Nullable*/";
		}
	}

	protected @NonNull LinkedHashMap<org.eclipse.ocl.pivot.@NonNull Class, @NonNull LinkedHashMap<org.eclipse.ocl.pivot.@NonNull Class, @NonNull List<@NonNull Operation>>> computeFragmentOperations() {
		LinkedHashMap<org.eclipse.ocl.pivot.@NonNull Class, @NonNull LinkedHashMap<org.eclipse.ocl.pivot.@NonNull Class, @NonNull List<@NonNull Operation>>> fragmentOperations = new LinkedHashMap<>();
		for (org.eclipse.ocl.pivot.@NonNull Class pClass : activeClassesSortedByName) {
			LinkedHashMap<org.eclipse.ocl.pivot.@NonNull Class, @NonNull List<@NonNull Operation>> classOperations = new LinkedHashMap<>();
			fragmentOperations.put(pClass, classOperations);
			List<@NonNull Operation> sortedOperations = new ArrayList<>(getOperations(pClass));
			Collections.sort(sortedOperations, signatureComparator);
			classOperations.put(pClass, sortedOperations);
			for (org.eclipse.ocl.pivot.@NonNull Class pSuperClass : getAllProperSupertypesSortedByName(pClass)) {
				List<@NonNull Operation> sortedSuperOperations = new ArrayList<>(getOperations(pSuperClass));
				Collections.sort(sortedSuperOperations, signatureComparator);
				classOperations.put(pSuperClass, sortedSuperOperations);
			}
		}
		return fragmentOperations;
	}

	protected @NonNull LinkedHashMap<org.eclipse.ocl.pivot.@NonNull Class, @NonNull List<@NonNull Property>> computeFragmentProperties() {
		LinkedHashMap<org.eclipse.ocl.pivot.@NonNull Class, @NonNull List<@NonNull Property>> fragmentProperties = new LinkedHashMap<>();
		for (org.eclipse.ocl.pivot.@NonNull Class pClass : activeClassesSortedByName) {
			Set<@NonNull Property> allProperties = new HashSet<>();
			for (org.eclipse.ocl.pivot.@NonNull Class pSuperClass : getAllSupertypesSortedByName(pClass)) {
				for (/*@NonNull*/ Property prop : getLocalPropertiesSortedByName(pSuperClass)) {
					assert prop != null;
					if (isProperty(prop) && !prop.isIsImplicit()) {			// FIXME maybe implicits too
						allProperties.add(prop);
					}
				}
			}
			List<@NonNull Property> sortedProperties = new ArrayList<>(allProperties);
			Collections.sort(sortedProperties, propertyComparator);
			fragmentProperties.put(pClass, sortedProperties);
		}
		return fragmentProperties;
	}

	protected void declareAllInstances() {
		if (allInstancesCompleteClasses.size() > 0) {
			List<@NonNull EClass> allInstancesEClasses = new ArrayList<>();
			for (@NonNull CompleteClass completeClass : allInstancesCompleteClasses) {
				EObject esObject = completeClass.getESObject();
				if (esObject == null) {
					esObject = completeClass.getPrimaryClass().getESObject();
				}
				if (esObject instanceof EClass) {
					allInstancesEClasses.add((EClass)esObject);
				}
			}
			Collections.sort(allInstancesEClasses, NameUtil.ENAMED_ELEMENT_COMPARATOR);
			s.append("\n");
			s.append("	/*\n");
			s.append("	 * The EClasses whose instances should be cached to support allInstances().\n");
			s.append("	 */\n");
			s.append("	private static final ");
			s.appendClassReference(true, EClass.class);
			s.append(" allInstancesEClasses " + atNonNull() + " [] = {\n");
			boolean isFirst = true;
			for (@NonNull EClass eClass : allInstancesEClasses) {
				if (!isFirst) {
					s.append(",\n");
				}
				s.append("		");
				s.append(genModelHelper.getQualifiedEcoreLiteralName(eClass));
				isFirst = false;
			}
			s.append("\n");
			s.append("	};\n");
			s.append("\n");
			s.append("	@Override\n");
			s.append("	public ");
			s.appendClassReference(true, EClass.class);
			s.append(" " + atNonNull() + " [] basicGetAllInstancesClasses() {\n");
			s.append("		return allInstancesEClasses;\n");
			s.append("	}\n");
		}
	}

	protected void declareEnumerationLiterals() {
		s.append("	/**\n");
		s.append("	 *	The lists of enumeration literals for each enumeration.\n");
		s.append("	 */\n");
		s.append("	public static class " + AbstractGenModelHelper.ENUMERATION_LITERALS_PACKAGE_NAME + " {\n");
		appendInitializationStart(AbstractGenModelHelper.ENUMERATION_LITERALS_PACKAGE_NAME);
		for (org.eclipse.ocl.pivot.@NonNull Class asClass : activeClassesSortedByName) {
			EClassifier eClassifier = ClassUtil.nonNullState((EClassifier)asClass.getESObject());
			if (asClass instanceof Enumeration) {
				s.append("\n");
				List<EnumerationLiteral> asEnumerationLiterals = ((Enumeration)asClass).getOwnedLiterals();
				for (int i = 0; i < asEnumerationLiterals.size(); i++) {
					EnumerationLiteral asEnumerationLiteral = ClassUtil.nonNullModel(asEnumerationLiterals.get(i));
					s.append("		public static final ");
					s.appendClassReference(true, EcoreExecutorEnumerationLiteral.class);
					s.append(" ");
					asEnumerationLiteral.accept(emitLiteralVisitor);
					s.append(" = new ");
					s.appendClassReference(null, EcoreExecutorEnumerationLiteral.class);
					s.append("(");
					s.append(genModelHelper.getQualifiedEcoreLiteralName(eClassifier));
					s.append(".getEEnumLiteral(");
					s.appendString(PivotUtil.getName(asEnumerationLiteral));
					s.append("), ");
					asClass.accept(emitScopedLiteralVisitor);
					s.append(", " + i + ");\n");
				}
				s.append("		private static final ");
				s.appendClassReference(true, EcoreExecutorEnumerationLiteral.class);
				s.append(" " + atNonNull() + " [] ");
				asClass.accept(emitLiteralVisitor);
				s.append(" = {");
				for (int i = 0; i < asEnumerationLiterals.size(); i++) {
					EnumerationLiteral asEnumerationLiteral = ClassUtil.nonNullModel(asEnumerationLiterals.get(i));
					if (i > 0) {
						s.append(",");
					}
					s.append("\n");
					s.append("			");
					asEnumerationLiteral.accept(emitLiteralVisitor);
				}
				s.append("\n");
				s.append("		};\n");
			}
		}
		s.append("\n");
		s.append("		/**\n");
		s.append("		 *	Install the enumeration literals in the enumerations.\n");
		s.append("		 */\n");
		s.append("		static {\n");
		for (org.eclipse.ocl.pivot.@NonNull Class asClass : activeClassesSortedByName) {
			if (asClass instanceof Enumeration) {
				s.append("			");
				asClass.accept(emitScopedLiteralVisitor);
				s.append(".initLiterals(");
				asClass.accept(emitLiteralVisitor);
				s.append(");\n");
			}
		}
		s.append("\n");
		appendInitializationEnd(true);
		s.append("	}\n");
	}

	protected void declareFragments() {
		s.append("	/**\n");
		s.append("	 *	The fragment descriptors for the local elements of each type and its supertypes.\n");
		s.append("	 */\n");
		s.append("	public static class " + AbstractGenModelHelper.FRAGMENTS_PACKAGE_NAME + " {\n");
		appendInitializationStart(AbstractGenModelHelper.FRAGMENTS_PACKAGE_NAME);
		for (org.eclipse.ocl.pivot.@NonNull Class asClass : activeClassesSortedByName) {
			s.append("\n");
			List<org.eclipse.ocl.pivot.@NonNull Class> allSupertypesSortedByName = getAllSupertypesSortedByName(asClass);
			for (org.eclipse.ocl.pivot.@NonNull Class asSuperClass : allSupertypesSortedByName) {
				s.append("		private static final ");
				s.appendClassReference(true, ExecutorFragment.class);
				s.append(" ");
				appendClassSuperClassName(asClass, asSuperClass);
				s.append(" = new ");
				s.appendClassReference(null, ExecutorFragment.class);
				s.append("(");
				asClass.accept(emitScopedLiteralVisitor);
				s.append(", ");
				asSuperClass.accept(emitQualifiedLiteralVisitor);
				s.append(");\n");
			}
		}
		appendInitializationEnd(false);
		s.append("	}\n");
	}

	protected void declareFragmentOperations(@NonNull List<@NonNull LinkedHashMap<org.eclipse.ocl.pivot.@NonNull Class, @NonNull LinkedHashMap<org.eclipse.ocl.pivot.@NonNull Class, @NonNull List<@NonNull Operation>>>> paginatedFragmentOperations) {
		s.append("	/**\n");
		s.append("	 *	The lists of local operations or local operation overrides for each fragment of each type.\n");
		s.append("	 */\n");
		int page = 1;
		int pageMax = paginatedFragmentOperations.size();
		for (@NonNull LinkedHashMap<org.eclipse.ocl.pivot.@NonNull Class, @NonNull LinkedHashMap<org.eclipse.ocl.pivot.@NonNull Class, @NonNull List<@NonNull Operation>>> fragmentOperations : paginatedFragmentOperations) {
			String pagedName = getPagedName(AbstractGenModelHelper.FRAGMENT_OPERATIONS_PACKAGE_NAME, page, pageMax);
			s.append("	public static class " + pagedName);
			s.append(" {\n");
			appendInitializationStart(pagedName);
			for (org.eclipse.ocl.pivot.@NonNull Class asClass : fragmentOperations.keySet()) {
				s.append("\n");
				LinkedHashMap<org.eclipse.ocl.pivot.@NonNull Class, @NonNull List<@NonNull Operation>> classOperations = fragmentOperations.get(asClass);
				assert classOperations != null;
				for (/*@NonNull*/ org.eclipse.ocl.pivot.Class asSuperClass : classOperations.keySet()) {
					assert asSuperClass != null;
					List<@NonNull Operation> sortedOperations = classOperations.get(asSuperClass);
					assert sortedOperations != null;
					s.append("		private static final ");
					s.appendClassReference(true, ExecutorOperation.class);
					s.append(" " + atNonNull() + " [] ");
					appendClassSuperClassName(asClass, asSuperClass);
					s.append(" = ");
					if (sortedOperations.size() <= 0) {
						s.append("{};\n");
					}
					else {
						s.append("{");
						for (int i = 0; i < sortedOperations.size(); i++) {
							Operation op = ClassUtil.nonNullModel(sortedOperations.get(i));
							Operation overloadOp = getOverloadOp(asClass, op);
							if (i > 0) {
								s.append(",");
							}
							s.append("\n");
							s.append("			");
							overloadOp.accept(emitQualifiedLiteralVisitor);
							s.append(" /* ");
							s.append(getSignature(overloadOp));
							s.append(" */");
						}
						s.append("\n");
						s.append("		};\n");
					}
				}
			}
			s.append("\n");
			s.append("		/*\n");
			s.append("		 *	Install the operation descriptors in the fragment descriptors.\n");
			s.append("		 */\n");
			s.append("		static {\n");
			for (org.eclipse.ocl.pivot.@NonNull Class pClass : fragmentOperations.keySet()) {
				for (org.eclipse.ocl.pivot.@NonNull Class pSuperClass : getAllSupertypesSortedByName(pClass)) {
					s.append("			" + AbstractGenModelHelper.FRAGMENTS_PACKAGE_NAME + ".");
					appendClassSuperClassName(pClass, pSuperClass);
					s.append(".initOperations(");
					appendClassSuperClassName(pClass, pSuperClass);
					s.append(");\n");
				}
				s.append("\n");
			}
			appendInitializationEnd(true);
			s.append("	}\n");
			s.append("\n");
			page++;
		}
	}

	protected void declareFragmentProperties(@NonNull List<@NonNull LinkedHashMap<org.eclipse.ocl.pivot.@NonNull Class, @NonNull List<@NonNull Property>>> paginatedFragmentProperties) {
		s.append("	/**\n");
		s.append("	 *	The lists of local properties for the local fragment of each type.\n");
		s.append("	 */\n");
		int page = 1;
		int pageMax = paginatedFragmentProperties.size();
		for (@NonNull LinkedHashMap<org.eclipse.ocl.pivot.@NonNull Class, @NonNull List<@NonNull Property>> fragmentProperties : paginatedFragmentProperties) {
			String pagedName = getPagedName(AbstractGenModelHelper.FRAGMENT_PROPERTIES_PACKAGE_NAME, page, pageMax);
			s.append("	public static class " + pagedName);
			s.append(" {\n");
			appendInitializationStart(pagedName);
			for (org.eclipse.ocl.pivot.@NonNull Class asClass : fragmentProperties.keySet()) {
				List<@NonNull Property> sortedProperties = fragmentProperties.get(asClass);
				assert sortedProperties != null;
				s.append("\n");
				s.append("		private static final ");
				s.appendClassReference(true, ExecutorProperty.class);
				s.append(" " + atNonNull() + " [] ");
				asClass.accept(emitLiteralVisitor);
				s.append(" = ");
				if (sortedProperties.size() <= 0) {
					s.append("{};\n");
				}
				else {
					s.append("{");
					for (int i = 0; i < sortedProperties.size(); i++) {
						Property prop = sortedProperties.get(i);
						if (i > 0) {
							s.append(",");
						}
						s.append("\n");
						s.append("			");
						prop.accept(emitQualifiedLiteralVisitor);
					}
					s.append("\n");
					s.append("		};\n");
				}
			}
			s.append("\n");
			s.append("		/**\n");
			s.append("		 *	Install the property descriptors in the fragment descriptors.\n");
			s.append("		 */\n");
			//		s.append("		static {\n");
			//		s.append("		}\n");
			//		s.append("\n");
			s.append("		static {\n");
			for (org.eclipse.ocl.pivot.@NonNull Class asClass : fragmentProperties.keySet()) {
				s.append("			" + AbstractGenModelHelper.FRAGMENTS_PACKAGE_NAME + ".");
				appendClassSuperClassName(asClass, asClass);
				s.append(".initProperties(");
				asClass.accept(emitLiteralVisitor);
				s.append(");\n");
			}
			s.append("\n");
			appendInitializationEnd(true);
			s.append("	}\n");
			s.append("\n");
			page++;
		}
	}

	protected void declareImplicitOpposites() {
		if (implicitOppositeProperties.size() > 0) {
			List<@NonNull EReference> implicitOppositeEReferences = new ArrayList<>();
			for (@NonNull Property implicitOppositeProperty : implicitOppositeProperties) {
				EObject esObject = implicitOppositeProperty.getESObject();
				if (esObject instanceof EReference) {
					implicitOppositeEReferences.add((EReference)esObject);
				}
			}
			Collections.sort(implicitOppositeEReferences, NameUtil.ENAMED_ELEMENT_COMPARATOR);	// Qualified
			s.append("\n");
			s.append("	/*\n");
			s.append("	 * The EReferences whose opposites should be cached to support implicit opposite navigation.\n");
			s.append("	 */\n");
			s.append("	private static final ");
			s.appendClassReference(true, EReference.class);
			s.append(" implicitOppositeEReferences " + atNonNull() + " [] = {\n");
			boolean isFirst = true;
			for (@NonNull EReference eReference : implicitOppositeEReferences) {
				if (!isFirst) {
					s.append(",\n");
				}
				s.append("		");
				s.append(genModelHelper.getQualifiedEcoreLiteralName(eReference));
				isFirst = false;
			}
			s.append("\n");
			s.append("	};\n");
			s.append("\n");
			s.append("	@Override\n");
			s.append("	public ");
			s.appendClassReference(true, EReference.class);
			s.append(" " + atNonNull() + " [] basicGetImplicitOpposites() {\n");
			s.append("		return implicitOppositeEReferences;\n");
			s.append("	}\n");
		}
	}

	protected void declareInit() {
		s.append("	/**\n");
		s.append("	 * The multiple packages above avoid problems with the Java 65536 byte limit but introduce a difficulty in ensuring that\n");
		s.append("	 * static construction occurs in the disciplined order of the packages when construction may start in any of the packages.\n");
		s.append("	 * The problem is resolved by ensuring that the static construction of each package first initializes its immediate predecessor.\n");
		s.append("	 * On completion of predecessor initialization, the residual packages are initialized by starting an initialization in the last package.\n");
		s.append("	 * This class maintains a count so that the various predecessors can distinguish whether they are the starting point and so\n");
		s.append("	 * ensure that residual construction occurs just once after all predecessors.\n");
		s.append("	 */\n");
		s.append("	private static class Init {\n");
		s.append("		/**\n");
		s.append("		 * Counter of nested static constructions. On return to zero residual construction starts. -ve once residual construction started.\n");
		s.append("		 */\n");
		s.append("		private static int initCount = 0;\n");
		s.append("\n");
		s.append("		/**\n");
		s.append("		 * Invoked at the start of a static construction to defer residual construction until primary constructions complete.\n");
		s.append("		 */\n");
		s.append("		private static void initStart() {\n");
		s.append("			if (initCount >= 0) {\n");
		s.append("				initCount++;\n");
		//		s.append("				System.out.println(\"" + getTablesClassName() + "::initStart \" + initCount);\n");
		s.append("			}\n");
		s.append("		}\n");
		s.append("\n");
		s.append("		/**\n");
		s.append("		 * Invoked at the end of a static construction to activate residual construction once primary constructions complete.\n");
		s.append("		 */\n");
		s.append("		private static void initEnd() {\n");
		s.append("			if (initCount > 0) {\n");
		//		s.append("				System.out.println(\"" + getTablesClassName() + "::initEnd \" + initCount);\n");
		s.append("				if (--initCount == 0) {\n");
		s.append("					initCount = -1;\n");
		s.append("					" + precedingPackageName + ".init();\n");
		s.append("				}\n");
		s.append("			}\n");
		s.append("		}\n");
		s.append("	}\n");
	}

	protected void declareOperations() {
		s.append("	/**\n");
		s.append("	 *	The operation descriptors for each operation of each type.\n");
		s.append("	 *\n");
		s.append("	 * @noextend This class is not intended to be subclassed by clients.\n");
		s.append("	 * @noinstantiate This class is not intended to be instantiated by clients.\n");
		s.append("	 * @noreference This class is not intended to be referenced by clients.\n");
		s.append("	 */\n");
		s.append("	public static class " + AbstractGenModelHelper.OPERATIONS_PACKAGE_NAME + " {\n");
		appendInitializationStart(AbstractGenModelHelper.OPERATIONS_PACKAGE_NAME);
		for (org.eclipse.ocl.pivot.@NonNull Class pClass : activeClassesSortedByName) {
			List<@NonNull Operation> sortedOperations = new ArrayList<>(getOperations(pClass));
			Collections.sort(sortedOperations, signatureComparator);
			for (int i = 0; i < sortedOperations.size(); i++) {
				if (i == 0) {
					s.append("\n");
				}
				Operation op = sortedOperations.get(i);
				TemplateSignature ownedTemplateSignature = op.getOwnedSignature();
				s.append("		public static final ");
				s.appendClassReference(true, ExecutorOperation.class);
				s.append(" ");
				op.accept(emitLiteralVisitor);
				s.append(" = new ");
				s.appendClassReference(null, ExecutorOperation.class);
				s.append("(");
				s.appendString(ClassUtil.nonNullModel(op.getName()));
				s.append(", ");
				appendParameterTypesName(op.getParameterTypes());
				s.append(", ");
				op.getOwningClass().accept(emitScopedLiteralVisitor);
				s.append(",\n			" + i + ", ");
				if (ownedTemplateSignature == null) {
					s.appendClassReference(null, TemplateParameters.class);
					s.append(".EMPTY_LIST");
				}
				else {
					s.appendClassReference(null, TypeUtil.class);
					s.append(".createTemplateParameters(");
					boolean first = true;
					for (TemplateParameter parameter : ownedTemplateSignature.getOwnedParameters()) {
						if (parameter != null) {
							if (!first) {
								s.append(", ");
							}
							parameter.accept(emitScopedLiteralVisitor);
							first = false;
						}
					}
					s.append(")");
				}
				s.append(", ");
				s.append(getImplementationName(op));
				s.append(");\n");
			}
		}
		appendInitializationEnd(false);
		s.append("	}\n");
	}

	protected void declareParameterLists() {
		Map<@NonNull String, @NonNull ParameterTypes> name2list = new HashMap<>();
		Set<@NonNull ParameterTypes> allLists = new HashSet<>();
		for (org.eclipse.ocl.pivot.@NonNull Class pClass : activeClassesSortedByName) {
			for (Operation operation : getOperations(pClass)) {
				ParameterTypes parameterTypes = operation.getParameterTypes();
				allLists.add(parameterTypes);
				if (parameterTypes.size() > 0) {
					String name = getTemplateBindingsName(parameterTypes);
					name2list.put(name, parameterTypes);
				}
			}
		}
		s.append("	/**\n");
		s.append("	 *	The parameter lists shared by operations.\n");
		s.append("	 *\n");
		s.append("	 * @noextend This class is not intended to be subclassed by clients.\n");
		s.append("	 * @noinstantiate This class is not intended to be instantiated by clients.\n");
		s.append("	 * @noreference This class is not intended to be referenced by clients.\n");
		s.append("	 */\n");
		s.append("	public static class " + AbstractGenModelHelper.PARAMETERS_PACKAGE_NAME + " {\n");
		appendInitializationStart(AbstractGenModelHelper.PARAMETERS_PACKAGE_NAME);
		if (name2list.size() > 0) {
			s.append("\n");
			List<@NonNull String> sortedNames = new ArrayList<>(name2list.keySet());
			Collections.sort(sortedNames);
			for (@NonNull String name : sortedNames) {
				ParameterTypes types = name2list.get(name);
				assert types != null;
				if (types.size() > 0) {				// Bug 471118 avoid deprecated _ identifier
					s.append("		public static final ");
					s.appendClassReference(true, ParameterTypes.class);
					s.append(" ");
					s.append(name);
					s.append(" = ");
					s.appendClassReference(null, TypeUtil.class);
					s.append(".createParameterTypes(");
					for (int i = 0; i < types.size(); i++) {
						if (i > 0) {
							s.append(", ");
						}
						Type type = types.get(i);
						type.accept(declareParameterTypeVisitor);
					}
					s.append(");\n");
				}
			}
		}
		if (allLists.size() > 0) {
		//	s.append("\n");
			List<@NonNull ParameterTypes> sortedLists = new ArrayList<>(allLists);
			Collections.sort(sortedLists, leegacyTemplateBindingNameComparator);
			for (@NonNull ParameterTypes types : sortedLists) {
				if (types.size() > 0) {				// Bug 471118 avoid deprecated _ identifier
					String legacyTemplateBindingsName = getLegacyTemplateBindingsName(types);
					String templateBindingsName = getTemplateBindingsName(types);
					if (!legacyTemplateBindingsName.equals(templateBindingsName) && !(types.get(0) instanceof LambdaType)) {		// legacy never defined LambdaType
						s.append("		@Deprecated /* @deprecated use normalized name */\n");
						s.append("		public static final ");
						s.appendClassReference(true, ParameterTypes.class);
						s.append(" ");
						s.append(legacyTemplateBindingsName);
						s.append(" = ");
						s.append(templateBindingsName);
						s.append(";\n");
					}
				}
			}
		}
		appendInitializationEnd(false);
		s.append("	}\n");
	}

	protected void declareProperties() {
		s.append("	/**\n");
		s.append("	 *	The property descriptors for each property of each type.\n");
		s.append("	 *\n");
		s.append("	 * @noextend This class is not intended to be subclassed by clients.\n");
		s.append("	 * @noinstantiate This class is not intended to be instantiated by clients.\n");
		s.append("	 * @noreference This class is not intended to be referenced by clients.\n");
		s.append("	 */\n");
		s.append("	public static class " + AbstractGenModelHelper.PROPERTIES_PACKAGE_NAME + " {\n");
		appendInitializationStart(AbstractGenModelHelper.PROPERTIES_PACKAGE_NAME);
		boolean isFirst = false;
		for (org.eclipse.ocl.pivot.@NonNull Class pClass : activeClassesSortedByName) {
			List<@NonNull Property> sortedProperties = getLocalPropertiesSortedByName(pClass);
			for (int i = 0; i < sortedProperties.size(); i++) {
				Property prop = ClassUtil.nonNullModel(sortedProperties.get(i));
				if (isProperty(prop)) {
					s.append("\n");
					if (isFirst) {
						s.append("\n");
					}
					isFirst = false;
					s.append("		public static final ");
					s.appendClassReference(true, ExecutorProperty.class);
					s.append(" ");
					prop.accept(emitLiteralVisitor);
					s.append(" = new ");
					String name = ClassUtil.nonNullModel(prop.getName());
					if (prop.getImplementationClass() != null) {
						s.appendClassReference(null, ExecutorPropertyWithImplementation.class);
						s.append("(");
						s.appendString(name);
						s.append(", " );
						pClass.accept(emitScopedLiteralVisitor);
						s.append(", " + i + ", ");
						s.append(prop.getImplementationClass());
						s.append(".INSTANCE)");
					}
					else if (hasEcore(prop)) {
						EStructuralFeature eStructuralFeature = ClassUtil.nonNullState((EStructuralFeature)prop.getESObject());
						s.appendClassReference(null, EcoreExecutorProperty.class);
						s.append("(");
						s.append(genModelHelper.getQualifiedEcoreLiteralName(eStructuralFeature));
						s.append(", " );
						pClass.accept(emitScopedLiteralVisitor);
						s.append(", " + i + ")");
						//						}
					} else {
						Property opposite = prop.getOpposite();
						if ((opposite != null) && hasEcore(opposite)) {
							EStructuralFeature eStructuralFeature = ClassUtil.nonNullState((EStructuralFeature)opposite.getESObject());
							s.appendClassReference(null, ExecutorPropertyWithImplementation.class);
							s.append("(");
							s.appendString(name);
							s.append(", " );
							pClass.accept(emitScopedLiteralVisitor);
							s.append(", " + i + ", new ");
							s.appendClassReference(null, EcoreLibraryOppositeProperty.class);
							s.append("(");
							s.append(genModelHelper.getQualifiedEcoreLiteralName(eStructuralFeature));
							s.append("))");
						}
						else {
							s.appendClassReference(null, ExecutorPropertyWithImplementation.class);
							s.append("(");
							s.appendString(name);
							s.append(", " );
							pClass.accept(emitScopedLiteralVisitor);
							s.append(", " + i + ", null)");
						}
					}
					s.append(";");
				}
			}
			isFirst = true;
		}
		appendInitializationEnd(false);
		s.append("	}\n");
	}

	protected void declareType(org.eclipse.ocl.pivot.@NonNull Class asClass) {
		Class<?> typeClass = getEcoreExecutorClass(asClass);
		EClassifier eClassifier = ClassUtil.nonNullState((EClassifier)asClass.getESObject());
		s.append("		public static final ");
		s.appendClassReference(true, typeClass);
		s.append(" ");
		asClass.accept(emitLiteralVisitor);
		s.append(" = ");
		if (!hasEcore(asClass) || (asClass instanceof AnyType) || (asClass instanceof CollectionType) || (asClass instanceof VoidType) || (asClass instanceof InvalidType)) {
			s.append("new ");
			s.appendClassReference(null, typeClass);
			s.append("(");
			if (isBuiltInType(asClass)) {
				s.appendClassReference(null, TypeId.class);
				s.append(".");
				s.append(genModelHelper.getEcoreLiteralName(eClassifier));
			}
			else {
				s.appendString(ClassUtil.nonNullModel(asClass.getName()));
			}
		}
		else {
			s.append("new ");
			s.appendClassReference(null, typeClass);
			s.append("(");
			s.append(genModelHelper.getQualifiedEcoreLiteralName(eClassifier));
		}
		s.append(", PACKAGE, ");
		appendTypeFlags(asClass);
		if (asClass.getOwnedSignature() != null) {
			for (TemplateParameter asTemplateParameter : asClass.getOwnedSignature().getOwnedParameters()) {
				if (asTemplateParameter != null) {
					s.append(", ");
					asTemplateParameter.accept(emitScopedLiteralVisitor);
				//	s.append(AbstractGenModelHelper.TYPE_PARAMETERS_PACKAGE_NAME);
				//	s.append(".");
				//	s.append(getTemplateParameterName(asTemplateParameter));
				}
			}
		}
		s.append(");\n");
	}

	protected void declareTypes(@NonNull List<@NonNull LinkedHashMap<org.eclipse.ocl.pivot.@NonNull Class, @NonNull LinkedHashMap<org.eclipse.ocl.pivot.@NonNull Class, @NonNull List<@NonNull Operation>>>> paginatedFragmentOperations, @NonNull List<@NonNull LinkedHashMap<org.eclipse.ocl.pivot.@NonNull Class, @NonNull List<@NonNull Property>>> paginatedFragmentProperties) {
		s.append("	/**\n");
		s.append("	 *	The type descriptors for each type.\n");
		s.append("	 */\n");
		s.append("	public static class " + AbstractGenModelHelper.TYPES_PACKAGE_NAME + " {\n");
		appendInitializationStart(AbstractGenModelHelper.TYPES_PACKAGE_NAME);
		s.append("\n");
		for (org.eclipse.ocl.pivot.@NonNull Class pClass : activeClassesSortedByName) {
			declareType(pClass);
		}
		s.append("\n");
		s.append("		private static final ");
		s.appendClassReference(true, EcoreExecutorType.class);
		s.append(" " + atNonNull() + " [] types = {");
		boolean isFirst = true;
		for (org.eclipse.ocl.pivot.@NonNull Class asClass : activeClassesSortedByName) {
			if (!isFirst) {
				s.append(",");
			}
			isFirst = false;
			s.append("\n");
			s.append("			");
			asClass.accept(emitLiteralVisitor);
		}
		s.append("\n");
		s.append("		};\n");
		s.append("\n");
		s.append("		/*\n");
		s.append("		 *	Install the type descriptors in the package descriptor.\n");
		s.append("		 */\n");
		s.append("		static {\n");
		s.append("			PACKAGE.init(LIBRARY, types);\n");
		org.eclipse.ocl.pivot.Package extendedPackage = getExtendedPackage(asPackage);
		if (extendedPackage != null) {
			s.append("			LIBRARY.addExtension(");
			s.appendClassReference(null, getQualifiedTablesClassName(extendedPackage));
			s.append(".PACKAGE, PACKAGE);\n");
		}
		appendInitializationEnd(true);
		s.append("	}\n");
	}

	protected void declareTypeFragments() {
		s.append("	/**\n");
		s.append("	 *	The fragments for all base types in depth order: OclAny first, OclSelf last.\n");
		s.append("	 */\n");
		s.append("	public static class " + AbstractGenModelHelper.TYPE_FRAGMENTS_PACKAGE_NAME + " {\n");
		appendInitializationStart(AbstractGenModelHelper.TYPE_FRAGMENTS_PACKAGE_NAME);
		for (org.eclipse.ocl.pivot.@NonNull Class asClass : activeClassesSortedByName) {
			final Map<org.eclipse.ocl.pivot.@NonNull Class, @NonNull Integer> allSuperTypes = new HashMap<>();
			int myDepth = getAllSuperClasses(allSuperTypes, asClass);
			int[] typesPerDepth = new int[myDepth+1];
			for (int i = 0; i <= myDepth; i++) {
				typesPerDepth[i] = 0;
			}
			for (Integer aDepth : allSuperTypes.values()) {
				typesPerDepth[aDepth]++;
			}
			List<org.eclipse.ocl.pivot.@NonNull Class> superTypes = new ArrayList<>(allSuperTypes.keySet());
			Collections.sort(superTypes, new Comparator<org.eclipse.ocl.pivot.@NonNull Class>()
			{
				@Override
				public int compare(org.eclipse.ocl.pivot.@NonNull Class o1, org.eclipse.ocl.pivot.@NonNull Class o2) {
					Integer d1 = allSuperTypes.get(o1);
					Integer d2 = allSuperTypes.get(o2);
					assert (d1 != null) && (d2 != null);
					if (d1 != d2) {
						return d1.compareTo(d2);
					}
					String n1 = o1.getName();
					String n2 = o2.getName();
					return n1.compareTo(n2);
				}
			});
			s.append("\n");
			s.append("		private static final ");
			s.appendClassReference(true, ExecutorFragment.class);
			s.append(" " + atNonNull() + " [] ");
			asClass.accept(emitLiteralVisitor);
			s.append(" =\n");
			s.append("			{");
			boolean isFirst = true;
			for (/*@NonNull*/ Type asSuperClass : superTypes) {
				assert asSuperClass != null;
				if (!isFirst) {
					s.append(",");
				}
				s.append("\n");
				s.append("				" + AbstractGenModelHelper.FRAGMENTS_PACKAGE_NAME + ".");
				asClass.accept(emitLiteralVisitor);
				s.append("__");
				s.appendUnscopedTypeName(asSuperClass);
				s.append(" /* " + allSuperTypes.get(asSuperClass) + " */");
				isFirst = false;
			}
			s.append("\n");
			s.append("			};\n");
			s.append("		private static final int " + atNonNull() + " [] _");
			asClass.accept(emitLiteralVisitor);
			s.append(" = { ");
			for (int i = 0; i <= myDepth; i++) {
				if (i > 0) {
					s.append(",");
				}
				s.append(Integer.toString(typesPerDepth[i]));
			}
			s.append(" };\n");
		}
		s.append("\n");
		s.append("		/**\n");
		s.append("		 *	Install the fragment descriptors in the class descriptors.\n");
		s.append("		 */\n");
		s.append("		static {\n");
		for (org.eclipse.ocl.pivot.@NonNull Class asClass : activeClassesSortedByName) {
			s.append("			");
			asClass.accept(emitScopedLiteralVisitor);
			s.append(".initFragments(");
			asClass.accept(emitLiteralVisitor);
			s.append(", _");
			asClass.accept(emitLiteralVisitor);
			s.append(");\n");
		}
		s.append("\n");
		appendInitializationEnd(true);
		s.append("	}\n");
	}

	protected void declareTypeParameters() {
		Map<@NonNull String, @NonNull TemplateParameter> name2templateParameter = new HashMap<>();
		for (org.eclipse.ocl.pivot.@NonNull Class asClass : activeClassesSortedByName) {
			TemplateSignature asTemplateSignature = asClass.getOwnedSignature();
			if (asTemplateSignature != null) {
				declareTypeParameters(name2templateParameter, asTemplateSignature);
			}
			for (@NonNull Operation operation : getLocalOperationsSortedBySignature(asClass)) {
				asTemplateSignature = operation.getOwnedSignature();
				if (asTemplateSignature != null) {
					declareTypeParameters(name2templateParameter, asTemplateSignature);
				}
			}
		}
		List<@NonNull String> names = new ArrayList<>(name2templateParameter.keySet());
		Collections.sort(names);
		s.append("	/**\n");
		s.append("	 *	The type parameters for templated types and operations.\n");
		s.append("	 */\n");
		s.append("	public static class " + AbstractGenModelHelper.TYPE_PARAMETERS_PACKAGE_NAME + " {\n");
		appendInitializationStart(AbstractGenModelHelper.TYPE_PARAMETERS_PACKAGE_NAME);
		if (names.size() > 0) {
			s.append("\n");
			for (@NonNull String name : names) {
				TemplateParameter asTemplateParameter = name2templateParameter.get(name);
				assert asTemplateParameter != null;
				s.append("		public static final ");
				s.appendClassReference(true, ExecutorTypeParameter.class);
				s.append(" ");
				s.append(name);
				s.append(" = new ");
				s.appendClassReference(null, ExecutorTypeParameter.class);
				s.append("(");
				s.append(Integer.toString(asTemplateParameter.getTemplateParameterId().getIndex()));
				s.append(", ");
				s.appendString(ClassUtil.nonNullModel(asTemplateParameter.getName()));
				s.append(");\n");
			}
		}
		for (org.eclipse.ocl.pivot.@NonNull Class asClass : activeClassesSortedByName) {
			TemplateSignature templateSignature = asClass.getOwnedSignature();
			if (templateSignature != null) {
				declareTypeParameters(templateSignature);
			}
			for (@NonNull Operation operation : getLocalOperationsSortedBySignature(asClass)) {
				templateSignature = operation.getOwnedSignature();
				if (templateSignature != null) {
					declareTypeParameters(templateSignature);
				}
			}
		}
		appendInitializationEnd(false);
		s.append("	}\n");
	}

	protected void declareTypeParameters(@NonNull Map<@NonNull String, @NonNull TemplateParameter> name2templateParameter, @NonNull TemplateSignature templateSignature) {
		for (@NonNull TemplateParameter asTemplateParameter : PivotUtil.getOwnedParameters(templateSignature)) {
			String name = getTemplateParameterName(asTemplateParameter);
			if (!name2templateParameter.containsKey(name)) {
				name2templateParameter.put(name, asTemplateParameter);
			}
		}
	}

	protected void declareTypeParameters(@NonNull TemplateSignature templateSignature) {
		for (@NonNull TemplateParameter asTemplateParameter : PivotUtil.getOwnedParameters(templateSignature)) {
			s.append("		@Deprecated /* @deprecated use normalized name */\n");
			s.append("		public static final ");
			s.appendClassReference(true, ExecutorTypeParameter.class);
			s.append(" ");
			TemplateSignature asTemplateSignature = asTemplateParameter.getOwningSignature();
			TemplateableElement asTemplateableElement = asTemplateSignature.getOwningElement();
			org.eclipse.ocl.pivot.Class asClass = null;
			if (asTemplateableElement instanceof Operation) {
				Operation asOperation = (Operation)asTemplateableElement;
				asClass = PivotUtil.getOwningClass(asOperation);
			}
			else if (asTemplateableElement instanceof org.eclipse.ocl.pivot.Class) {
				asClass = (org.eclipse.ocl.pivot.Class)asTemplateableElement;
			}
			if (asClass != null) {
				if (asTemplateableElement instanceof Operation) {
					s.append("_");
				}
				asTemplateableElement.accept(emitLiteralVisitor);
				s.append("_");
				s.appendAndEncodeName(asTemplateParameter);
			}
			else {
				s.append("null");
			}
			s.append(" = ");
			asTemplateParameter.accept(emitLiteralVisitor);
			s.append(";\n");
		}
	}

	protected String deresolveFileName(@Nullable String uri) {
		if (uri != null) {
			String modelProjectDirectory = genPackage.getGenModel().getModelProjectDirectory();
			int index = uri.indexOf(modelProjectDirectory);
			if (index >= 0) {
				uri = uri.substring(index);
			}
		}
		return uri;
	}

	public @NonNull String generateTablesClass(@Nullable String constants) {
		String tablesClassName = getTablesClassName();
		LinkedHashMap<org.eclipse.ocl.pivot.@NonNull Class, @NonNull LinkedHashMap<org.eclipse.ocl.pivot.@NonNull Class, @NonNull List<@NonNull Operation>>> fragmentOperations = computeFragmentOperations();
		LinkedHashMap<org.eclipse.ocl.pivot.@NonNull Class, @NonNull List<@NonNull Property>> fragmentProperties = computeFragmentProperties();
		List<@NonNull LinkedHashMap<org.eclipse.ocl.pivot.@NonNull Class, @NonNull LinkedHashMap<org.eclipse.ocl.pivot.@NonNull Class, @NonNull List<@NonNull Operation>>>> paginatedFragmentOperations = paginateFragmentOperations(fragmentOperations);
		List<@NonNull LinkedHashMap<org.eclipse.ocl.pivot.@NonNull Class, @NonNull List<@NonNull Property>>> paginatedFragmentProperties = paginateFragmentProperties(fragmentProperties);
		s.append("/**\n");
		s.append(" * " + tablesClassName + " provides the dispatch tables for the " + asPackage.getName() + " for use by the OCL dispatcher.\n");
		s.append(" *\n");
		s.append(" * In order to ensure correct static initialization, a top level class element must be accessed\n");
		s.append(" * before any nested class element. Therefore an access to PACKAGE.getClass() is recommended.\n");
		s.append(" */\n");
		//		s.append("@SuppressWarnings(\"nls\")\n");
		s.append("public class " + tablesClassName + " extends ");
		s.appendClassReference(null, AbstractTables.class);
		s.append("\n");
		s.append("{\n");
		s.append("	static {\n");
		//		s.append("		System.out.println(\"" + getTablesClassName() + " Start\");\n");
		s.append("		Init.initStart();\n");
		s.append("	}\n");
		s.append("\n");
		s.append("	/**\n");
		s.append("	 *	The package descriptor for the package.\n");
		s.append("	 */\n");
		s.append("	public static final ");
		s.appendClassReference(true, EcoreExecutorPackage.class);
		s.append(" PACKAGE = new ");
		s.appendClassReference(null, EcoreExecutorPackage.class);
		s.append("(" + getGenPackagePrefix() + "Package.eINSTANCE");
		if (asPackage.getPackageId() == IdManager.METAMODEL) {
			s.append(", ");
			s.appendClassReference(null, IdManager.class);
			s.append(".METAMODEL");
		}
		s.append(");\n");

		s.append("\n");
		s.append("	/**\n");
		s.append("	 *	The library of all packages and types.\n");
		s.append("	 */\n");

		s.append("	public static final ");
		s.appendClassReference(true, ExecutorStandardLibrary.class);
		s.append(" LIBRARY = ");
		if (hasSharedLibrary()) {
			s.appendClassReference(null, getSharedLibrary());
			s.append(".LIBRARY");
		}
		else {
			s.append("new ");
			s.appendClassReference(null, ExecutorStandardLibrary.class);
			s.append("()");
		}
		s.append(";\n");

		if (constants != null) {
			s.append("\n");
			appendConstants(constants);
		}

		precedingPackageName = getTablesClassName();
		s.append("\n");
		declareTypeParameters();
		s.append("\n");
		declareTypes(paginatedFragmentOperations, paginatedFragmentProperties);
		s.append("\n");
		declareFragments();
		s.append("\n");
		declareParameterLists();
		s.append("\n");
		declareOperations();
		s.append("\n");
		declareProperties();
		s.append("\n");
		declareTypeFragments();
		s.append("\n");
		declareFragmentOperations(paginatedFragmentOperations);
		//		s.append("\n");
		declareFragmentProperties(paginatedFragmentProperties);
		//		s.append("\n");
		declareEnumerationLiterals();
		s.append("\n");
		declareInit();
		s.append("\n");
		s.append("	static {\n");
		s.append("		Init.initEnd();\n");
		//		s.append("		System.out.println(\"" + getTablesClassName() + " End\");\n");
		s.append("	}\n");
		s.append("\n");
		s.append("	/*\n");
		s.append("	 * Force initialization of outer fields. Inner fields are lazily initialized.\n");
		s.append("	 */\n");
		s.append("	public static void init() {\n");
		s.append("		new " + tablesClassName + "();\n");
		s.append("	}\n");
		s.append("\n");
		s.append("	private " + tablesClassName + "() {\n");
		s.append("		super(" + getGenPackagePrefix() + "Package.eNS_URI);\n");
		s.append("	}\n");
		declareAllInstances();
		declareImplicitOpposites();
		if (tablesPostamble != null) {
			s.append(tablesPostamble);
		}
		s.append("}\n");
		return s.toString();
	}

	protected String getGenPackagePrefix() {
		return genPackage.getPrefix();
	}

	protected @NonNull Class<?> getEcoreExecutorClass(org.eclipse.ocl.pivot.@NonNull Class asClass) {
		if (asClass instanceof AnyType) {
			return EcoreExecutorAnyType.class;
		}
		else if (asClass instanceof CollectionType) {
			if (asClass instanceof BagType) {
				return EcoreExecutorBagType.class;
			}
			else if (asClass instanceof OrderedSetType) {
				return EcoreExecutorOrderedSetType.class;
			}
			else if (asClass instanceof SequenceType) {
				return EcoreExecutorSequenceType.class;
			}
			else if (asClass instanceof SetType) {
				return EcoreExecutorSetType.class;
			}
			else {
				return EcoreExecutorCollectionType.class;
			}
		}
		else if (asClass instanceof Enumeration) {
			return EcoreExecutorEnumeration.class;
		}
		else if (asClass instanceof InvalidType) {
			return EcoreExecutorInvalidType.class;
		}
		else if (asClass instanceof PrimitiveType) {
			if (asClass instanceof BooleanType) {
				return EcoreExecutorBooleanType.class;
			}
			else {
				return EcoreExecutorPrimitiveType.class;
			}
		}
		else if (asClass instanceof VoidType) {
			return EcoreExecutorVoidType.class;
		}
		return EcoreExecutorType.class;
	}

	public @NonNull String getTablesClassName() {
		return getTablesClassName(genPackage);
	}

	protected String getTablesPackageName() {
		return genPackage.getReflectionPackageName();
	}

	protected @NonNull List<@NonNull LinkedHashMap<org.eclipse.ocl.pivot.@NonNull Class, @NonNull LinkedHashMap<org.eclipse.ocl.pivot.@NonNull Class, @NonNull List<@NonNull Operation>>>> paginateFragmentOperations(@NonNull LinkedHashMap<org.eclipse.ocl.pivot.@NonNull Class, @NonNull LinkedHashMap<org.eclipse.ocl.pivot.@NonNull Class, @NonNull List<@NonNull Operation>>> fragmentOperations) {
		List<@NonNull LinkedHashMap<org.eclipse.ocl.pivot.@NonNull Class, @NonNull LinkedHashMap<org.eclipse.ocl.pivot.@NonNull Class, @NonNull List<@NonNull Operation>>>> paginatedFragmentOperations = new ArrayList<>();
		LinkedHashMap<org.eclipse.ocl.pivot.@NonNull Class, @NonNull LinkedHashMap<org.eclipse.ocl.pivot.@NonNull Class, @NonNull List<@NonNull Operation>>> pageOfFragmentOperations = null;
		int size = 0;
		for (org.eclipse.ocl.pivot.@NonNull Class pClass : fragmentOperations.keySet()) {
			LinkedHashMap<org.eclipse.ocl.pivot.@NonNull Class, @NonNull List<@NonNull Operation>> line = fragmentOperations.get(pClass);
			assert line != null;
			int lineSize = 0;
			for (List<@NonNull Operation> operations : line.values()) {
				lineSize += operations.size();
			}
			if ((pageOfFragmentOperations == null) || size+lineSize > 4000) {
				pageOfFragmentOperations = new LinkedHashMap<>();
				size = 0;
				paginatedFragmentOperations.add(pageOfFragmentOperations);
			}
			pageOfFragmentOperations.put(pClass, line);
			size += lineSize;
		}
		return paginatedFragmentOperations;
	}

	protected @NonNull List<@NonNull LinkedHashMap<org.eclipse.ocl.pivot.@NonNull Class, @NonNull List<@NonNull Property>>> paginateFragmentProperties(@NonNull LinkedHashMap<org.eclipse.ocl.pivot.@NonNull Class, @NonNull List<@NonNull Property>> fragmentProperties) {
		List<@NonNull LinkedHashMap<org.eclipse.ocl.pivot.@NonNull Class, @NonNull List<@NonNull Property>>> paginatedFragmentProperties = new ArrayList<>();
		LinkedHashMap<org.eclipse.ocl.pivot.@NonNull Class, @NonNull List<@NonNull Property>> pageOfFragmentProperties = null;
		int size = 0;
		for (org.eclipse.ocl.pivot.@NonNull Class pClass : fragmentProperties.keySet()) {
			List<@NonNull Property> line = fragmentProperties.get(pClass);
			assert line != null;
			int lineSize = line.size();
			if ((pageOfFragmentProperties == null) || size+lineSize > 4000) {
				pageOfFragmentProperties = new LinkedHashMap<>();
				size = 0;
				paginatedFragmentProperties.add(pageOfFragmentProperties);
			}
			pageOfFragmentProperties.put(pClass, line);
			size += lineSize;
		}
		return paginatedFragmentProperties;
	}

	@Override
	public @NonNull String toString() {
		String tablesPackageName = getTablesPackageName();
		int tablesPackageNameLength = tablesPackageName.length();
		String copyright = genPackage.getCopyright(" * ");
		StringBuilder s1 = new StringBuilder();
		s1.append("/*******************************************************************************\n");
		if (copyright != null) {
			String copyrightText = " * " + copyright.replace("\r", "") + "\n";
			s1.append(copyrightText.replaceAll("\\s+\\n", "\n"));
		}
		s1.append(" *************************************************************************\n");
		s1.append(" * This code is 100% auto-generated\n");
		s1.append(" * from:\n");
		for (org.eclipse.ocl.pivot.@NonNull Package dPackage : metamodelManager.getPartialPackages(asPackage, false)) {
			EObject eRoot = ((EObject)dPackage).eContainer();
			if (eRoot instanceof Model) {
				s1.append(" *   " + deresolveFileName(((Model)eRoot).getExternalURI()) + "\n");
			}
		}
		s1.append(" * using:\n");
		s1.append(" *   " + deresolveFileName(genPackage.eResource().getURI().toString()) + "\n");
		s1.append(" *   " + getClass().getName() + "\n");
		s1.append(" *\n");
		s1.append(" * Do not edit it.\n");
		s1.append(" *******************************************************************************/\n");

		s1.append("package ");
		s1.append(tablesPackageName);
		s1.append(";\n");

		s1.append("\n");
		for (@NonNull String classReference : s.getClassReferences()) {
			if (classReference.startsWith(tablesPackageName) && (classReference.indexOf('.', tablesPackageNameLength+1) < 0)) {
				s1.append("// ");
			}
			s1.append("import ");
			s1.append(classReference);
			s1.append(";\n");
		}
		s1.append("\n");
		s1.append(s.toString());
		return s1.toString();
	}
}
