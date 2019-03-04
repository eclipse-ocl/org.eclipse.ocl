/*******************************************************************************
 * Copyright (c) 2013, 2019 CEA LIST and others.
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
import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.eclipse.emf.codegen.util.ImportManager;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.genmodel.OCLGenModelUtil;
import org.eclipse.ocl.pivot.AnyType;
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.Enumeration;
import org.eclipse.ocl.pivot.EnumerationLiteral;
import org.eclipse.ocl.pivot.InvalidType;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.OrderedSetType;
import org.eclipse.ocl.pivot.ParameterTypes;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.SequenceType;
import org.eclipse.ocl.pivot.SetType;
import org.eclipse.ocl.pivot.TemplateParameter;
import org.eclipse.ocl.pivot.TemplateParameters;
import org.eclipse.ocl.pivot.TemplateSignature;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.VoidType;
import org.eclipse.ocl.pivot.ids.IdManager;
import org.eclipse.ocl.pivot.ids.TemplateParameterId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.library.ecore.EcoreExecutorEnumeration;
import org.eclipse.ocl.pivot.internal.library.ecore.EcoreExecutorEnumerationLiteral;
import org.eclipse.ocl.pivot.internal.library.ecore.EcoreExecutorInvalidType;
import org.eclipse.ocl.pivot.internal.library.ecore.EcoreExecutorPackage;
import org.eclipse.ocl.pivot.internal.library.ecore.EcoreExecutorProperty;
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
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.TypeUtil;

public class OCLinEcoreTables extends OCLinEcoreTablesUtils
{
	/**
	 * Proces a nested <%...%> to return its equivalent string and request any necessary imports.
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

	public OCLinEcoreTables(@NonNull GenPackage genPackage) {
		super(genPackage);
		GenModel genModel = ClassUtil.nonNullState(genPackage.getGenModel());
		this.tablesPostamble = OCLinEcoreGenModelGeneratorAdapter.tablesPostamble(genModel);
		this.importManager = new ImportManager(getTablesPackageName());
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

	protected void appendUpperName(@NonNull NamedElement namedElement) {
		s.append(ClassUtil.nonNullModel(CodeGenUtil.upperName(namedElement.getName())));
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
					if (isProperty(prop) && !prop.isIsImplicit()) {			// FIXME need implicits too
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

	protected void declareEnumerationLiterals() {
		s.append("	/**\n");
		s.append("	 *	The lists of enumeration literals for each enumeration.\n");
		s.append("	 */\n");
		s.append("	public static class EnumerationLiterals {\n");
		appendInitializationStart("EnumerationLiterals");
		for (org.eclipse.ocl.pivot.@NonNull Class pClass : activeClassesSortedByName) {
			if (pClass instanceof Enumeration) {
				s.append("\n");
				List<EnumerationLiteral> enumerationLiterals = ((Enumeration)pClass).getOwnedLiterals();
				for (int i = 0; i < enumerationLiterals.size(); i++) {
					EnumerationLiteral enumerationLiteral = ClassUtil.nonNullModel(enumerationLiterals.get(i));
					s.append("		public static final ");
					s.appendClassReference(true, EcoreExecutorEnumerationLiteral.class);
					s.append(" ");
					s.appendScopedTypeName(pClass);
					s.append("__");
					s.appendName(enumerationLiteral);
					s.append(" = new ");
					s.appendClassReference(null, EcoreExecutorEnumerationLiteral.class);
					s.append("(");
					s.append(getGenPackagePrefix() + "Package.Literals.");
					appendUpperName(pClass);
					s.append(".getEEnumLiteral(");
					s.appendString(ClassUtil.nonNullModel(enumerationLiteral.getName()));
					s.append("), Types.");
					s.appendScopedTypeName(pClass);
					s.append(", " + i + ");\n");
				}
				s.append("		private static final ");
				s.appendClassReference(true, EcoreExecutorEnumerationLiteral.class);
				s.append(" " + atNonNull() + " [] ");
				s.appendScopedTypeName(pClass);
				s.append(" = {");
				for (int i = 0; i < enumerationLiterals.size(); i++) {
					EnumerationLiteral enumerationLiteral = ClassUtil.nonNullModel(enumerationLiterals.get(i));
					if (i > 0) {
						s.append(",");
					}
					s.append("\n");
					s.append("			");
					s.appendScopedTypeName(pClass);
					s.append("__");
					s.appendName(enumerationLiteral);
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
		for (org.eclipse.ocl.pivot.@NonNull Class pClass : activeClassesSortedByName) {
			if (pClass instanceof Enumeration) {
				s.append("			Types.");
				s.appendScopedTypeName(pClass);
				s.append(".initLiterals(");
				s.appendScopedTypeName(pClass);
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
		s.append("	public static class Fragments {\n");
		appendInitializationStart("Fragments");
		for (org.eclipse.ocl.pivot.@NonNull Class pClass : activeClassesSortedByName) {
			s.append("\n");
			List<org.eclipse.ocl.pivot.@NonNull Class> allSupertypesSortedByName = getAllSupertypesSortedByName(pClass);
			for (org.eclipse.ocl.pivot.@NonNull Class pSuperClass : allSupertypesSortedByName) {
				s.append("		private static final ");
				s.appendClassReference(true, ExecutorFragment.class);
				s.append(" ");
				s.appendScopedTypeName(pClass);
				s.append("__");
				s.appendUnscopedTypeName(metamodelManager, pSuperClass);
				s.append(" = new ");
				s.appendClassReference(null, ExecutorFragment.class);
				s.append("(");
				pClass.accept(emitLiteralVisitor);
				s.append(", ");
				pSuperClass.accept(emitQualifiedLiteralVisitor);
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
			String pagedName = getPagedName("FragmentOperations", page, pageMax);
			s.append("	public static class " + pagedName);
			s.append(" {\n");
			appendInitializationStart(pagedName);
			for (org.eclipse.ocl.pivot.@NonNull Class pClass : fragmentOperations.keySet()) {
				s.append("\n");
				LinkedHashMap<org.eclipse.ocl.pivot.@NonNull Class, @NonNull List<@NonNull Operation>> classOperations = fragmentOperations.get(pClass);
				assert classOperations != null;
				for (/*@NonNull*/ org.eclipse.ocl.pivot.Class pSuperClass : classOperations.keySet()) {
					assert pSuperClass != null;
					List<@NonNull Operation> sortedOperations = classOperations.get(pSuperClass);
					assert sortedOperations != null;
					s.append("		private static final ");
					s.appendClassReference(true, ExecutorOperation.class);
					s.append(" " + atNonNull() + " [] ");
					s.appendScopedTypeName(pClass);
					s.append("__");
					s.appendUnscopedTypeName(metamodelManager, pSuperClass);
					s.append(" = ");
					if (sortedOperations.size() <= 0) {
						s.append("{};\n");
					}
					else {
						s.append("{");
						for (int i = 0; i < sortedOperations.size(); i++) {
							Operation op = ClassUtil.nonNullModel(sortedOperations.get(i));
							Operation overloadOp = getOverloadOp(pClass, op);
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
					s.append("			Fragments.");
					s.appendScopedTypeName(pClass);
					s.append("__");
					s.appendUnscopedTypeName(metamodelManager, pSuperClass);
					s.append(".initOperations(");
					s.appendScopedTypeName(pClass);
					s.append("__");
					s.appendUnscopedTypeName(metamodelManager, pSuperClass);
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
			String pagedName = getPagedName("FragmentProperties", page, pageMax);
			s.append("	public static class " + pagedName);
			s.append(" {\n");
			appendInitializationStart(pagedName);
			for (org.eclipse.ocl.pivot.@NonNull Class pClass : fragmentProperties.keySet()) {
				List<@NonNull Property> sortedProperties = fragmentProperties.get(pClass);
				assert sortedProperties != null;
				s.append("\n");
				s.append("		private static final ");
				s.appendClassReference(true, ExecutorProperty.class);
				s.append(" " + atNonNull() + " [] ");
				s.appendScopedTypeName(pClass);
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
			for (org.eclipse.ocl.pivot.@NonNull Class pClass : fragmentProperties.keySet()) {
				s.append("			Fragments.");
				s.appendScopedTypeName(pClass);
				s.append("__");
				s.appendUnscopedTypeName(metamodelManager, pClass);
				s.append(".initProperties(");
				s.appendScopedTypeName(pClass);
				s.append(");\n");
			}
			s.append("\n");
			appendInitializationEnd(true);
			s.append("	}\n");
			s.append("\n");
			page++;
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
		s.append("		 * Invoked at the start of a static construction to defer residual cobstruction until primary constructions complete.\n");
		s.append("		 */\n");
		s.append("		private static void initStart() {\n");
		s.append("			if (initCount >= 0) {\n");
		s.append("				initCount++;\n");
		//		s.append("				System.out.println(\"" + getTablesClassName() + "::initStart \" + initCount);\n");
		s.append("			}\n");
		s.append("		}\n");
		s.append("\n");
		s.append("		/**\n");
		s.append("		 * Invoked at the end of a static construction to activate residual cobstruction once primary constructions complete.\n");
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
		s.append("	public static class Operations {\n");
		appendInitializationStart("Operations");
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
				op.getOwningClass().accept(emitLiteralVisitor);
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
							s.append("TypeParameters._");
							op.accept(emitLiteralVisitor);
							s.append("_");
							s.appendParameterName(parameter);
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
		Set<@NonNull ParameterTypes> allLists = new HashSet<>();
		for (org.eclipse.ocl.pivot.@NonNull Class pClass : activeClassesSortedByName) {
			for (Operation operation : getOperations(pClass)) {
				allLists.add(operation.getParameterTypes());
			}
		}
		s.append("	/**\n");
		s.append("	 *	The parameter lists shared by operations.\n");
		s.append("	 *\n");
		s.append("	 * @noextend This class is not intended to be subclassed by clients.\n");
		s.append("	 * @noinstantiate This class is not intended to be instantiated by clients.\n");
		s.append("	 * @noreference This class is not intended to be referenced by clients.\n");
		s.append("	 */\n");
		s.append("	public static class Parameters {\n");
		appendInitializationStart("Parameters");
		s.append("\n");
		List<@NonNull ParameterTypes> sortedLists = new ArrayList<>(allLists);
		Collections.sort(sortedLists, templateBindingNameComparator);
		for (@NonNull ParameterTypes types : sortedLists) {
			if (types.size() > 0) {				// Bug 471118 avoid deprecated _ identifier
				s.append("		public static final ");
				s.appendClassReference(true, ParameterTypes.class);
				s.append(" ");
				s.append(getTemplateBindingsName(types));
				s.append(" = ");
				s.appendClassReference(null, TypeUtil.class);
				s.append(".createParameterTypes(");
				for (int i = 0; i < types.size(); i++) {
					if (i > 0) {
						s.append(", ");
					}
					Type type = PivotUtil.getBehavioralReturnType(types.get(i));
					type.accept(declareParameterTypeVisitor);
				}
				s.append(");\n");
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
		s.append("	public static class Properties {\n");
		appendInitializationStart("Properties");
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
						pClass.accept(emitLiteralVisitor);
						s.append(", " + i + ", ");
						s.append(prop.getImplementationClass());
						s.append(".INSTANCE)");
					}
					else if (hasEcore(prop)) {
						//					    List<Constraint> constraints = prop.getOwnedRule();
						org.eclipse.ocl.pivot.Class owningType = ClassUtil.nonNullModel(prop.getOwningClass());
						/*						if (constraints.size() > 0) {
							s.appendClassReference(null, ExecutorPropertyWithImplementation.class);
							s.append("(");
							s.appendString(name);
							s.append(", " );
							pClass.accept(emitLiteralVisitor);
							s.append(", " + i + ", ");
							s.append(getQualifiedBodiesClassName(owningType));
							s.append("._");
							s.appendName(prop);
							s.append("_");
							s.append(constraints.get(0).getStereotype());
							s.append("_.INSTANCE)");
						}
						else { */
						s.appendClassReference(null, EcoreExecutorProperty.class);
						s.append("(");
						s.append(getGenPackagePrefix());
						s.append("Package.Literals." );
						appendUpperName(owningType);
						s.append("__" );
						appendUpperName(prop);
						s.append(", " );
						pClass.accept(emitLiteralVisitor);
						s.append(", " + i + ")");
						//						}
					} else {
						Property opposite = prop.getOpposite();
						if ((opposite != null) && hasEcore(opposite)) {
							s.appendClassReference(null, ExecutorPropertyWithImplementation.class);
							s.append("(");
							s.appendString(name);
							s.append(", " );
							pClass.accept(emitLiteralVisitor);
							s.append(", " + i + ", new ");
							s.appendClassReference(null, EcoreLibraryOppositeProperty.class);
							s.append("(");
							s.append(getGenPackagePrefix());
							s.append("Package.Literals." );
							appendUpperName(ClassUtil.nonNullModel(opposite.getOwningClass()));
							s.append("__" );
							appendUpperName(opposite);
							s.append("))");
						}
						else {
							s.appendClassReference(null, ExecutorPropertyWithImplementation.class);
							s.append("(");
							s.appendString(name);
							s.append(", " );
							pClass.accept(emitLiteralVisitor);
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

	protected void declareType(org.eclipse.ocl.pivot.@NonNull Class pClass) {
		Class<?> typeClass =
				pClass instanceof Enumeration ? EcoreExecutorEnumeration.class :
					pClass instanceof InvalidType ? EcoreExecutorInvalidType.class :
						pClass instanceof VoidType ? EcoreExecutorVoidType.class :
							EcoreExecutorType.class;
		s.append("		public static final ");
		s.appendClassReference(true, typeClass);
		s.append(" ");
		s.appendScopedTypeName(pClass);
		s.append(" = ");
		if (!hasEcore(pClass) || (pClass instanceof AnyType) || (pClass instanceof CollectionType) || (pClass instanceof VoidType) || (pClass instanceof InvalidType)) {
			s.append("new ");
			s.appendClassReference(null, typeClass);
			s.append("(");
			if (isBuiltInType(pClass)) {
				s.appendClassReference(null, TypeId.class);
				s.append(".");
				appendUpperName(pClass);
			}
			else {
				s.appendString(ClassUtil.nonNullModel(pClass.getName()));
			}
		}
		else {
			s.append("new ");
			s.appendClassReference(null, typeClass);
			s.append("(" + getGenPackagePrefix() + "Package.Literals.");
			appendUpperName(pClass);
		}
		s.append(", PACKAGE, ");
		appendTypeFlags(pClass);
		if (pClass.getOwnedSignature() != null) {
			for (TemplateParameter parameter : pClass.getOwnedSignature().getOwnedParameters()) {
				if (parameter != null) {
					s.append(", TypeParameters.");
					s.appendScopedTypeName(pClass);
					s.append("_");
					s.appendParameterName(parameter);
				}
			}
		}
		s.append(");\n");
	}

	protected void declareTypes(@NonNull List<LinkedHashMap<org.eclipse.ocl.pivot.@NonNull Class, @NonNull LinkedHashMap<org.eclipse.ocl.pivot.@NonNull Class, @NonNull List<@NonNull Operation>>>> paginatedFragmentOperations, @NonNull List<LinkedHashMap<org.eclipse.ocl.pivot.@NonNull Class, List<@NonNull Property>>> paginatedFragmentProperties) {
		s.append("	/**\n");
		s.append("	 *	The type descriptors for each type.\n");
		s.append("	 */\n");
		s.append("	public static class Types {\n");
		appendInitializationStart("Types");
		s.append("\n");
		for (org.eclipse.ocl.pivot.@NonNull Class pClass : activeClassesSortedByName) {
			declareType(pClass);
		}
		s.append("\n");
		s.append("		private static final ");
		s.appendClassReference(true, EcoreExecutorType.class);
		s.append(" " + atNonNull() + " [] types = {");
		boolean isFirst = true;
		for (org.eclipse.ocl.pivot.@NonNull Class pClass : activeClassesSortedByName) {
			if (!isFirst) {
				s.append(",");
			}
			isFirst = false;
			s.append("\n");
			s.append("			");
			s.appendScopedTypeName(pClass);
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
		s.append("	public static class TypeFragments {\n");
		appendInitializationStart("TypeFragments");
		for (org.eclipse.ocl.pivot.@NonNull Class pClass : activeClassesSortedByName) {
			final Map<org.eclipse.ocl.pivot.@NonNull Class, @NonNull Integer> allSuperTypes = new HashMap<>();
			int myDepth = getAllSuperClasses(allSuperTypes, pClass);
			int[] typesPerDepth = new int[myDepth+1];
			for (int i = 0; i <= myDepth; i++) {
				typesPerDepth[i] = 0;
			}
			for (Integer aDepth : allSuperTypes.values()) {
				typesPerDepth[aDepth]++;
			}
			List<Type> superTypes = new ArrayList<>(allSuperTypes.keySet());
			Collections.sort(superTypes, new Comparator<@NonNull Type>()
			{
				@Override
				public int compare(@NonNull Type o1, @NonNull Type o2) {
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
			s.appendScopedTypeName(pClass);
			s.append(" =\n");
			s.append("			{");
			boolean isFirst = true;
			for (/*@NonNull*/ Type superClass : superTypes) {
				assert superClass != null;
				if (!isFirst) {
					s.append(",");
				}
				s.append("\n");
				s.append("				Fragments.");
				s.appendScopedTypeName(pClass);
				s.append("__");
				s.appendUnscopedTypeName(metamodelManager, superClass);
				s.append(" /* " + allSuperTypes.get(superClass) + " */");
				isFirst = false;
			}
			s.append("\n");
			s.append("			};\n");
			s.append("		private static final int " + atNonNull() + " [] _");
			s.appendScopedTypeName(pClass);
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
		for (org.eclipse.ocl.pivot.@NonNull Class pClass : activeClassesSortedByName) {
			s.append("			");
			pClass.accept(emitLiteralVisitor);
			s.append(".initFragments(");
			s.appendScopedTypeName(pClass);
			s.append(", _");
			s.appendScopedTypeName(pClass);
			//			if (hasAnotherType(pClass)) {
			//				s.append(", " + genPackage.getPrefix() + "Package.Literals.");
			//				appendUpperName(pClass);
			//			}
			s.append(");\n");
		}
		s.append("\n");
		appendInitializationEnd(true);
		s.append("	}\n");
	}

	protected void declareTypeParameters() {
		s.append("	/**\n");
		s.append("	 *	The type parameters for templated types and operations.\n");
		s.append("	 */\n");
		s.append("	public static class TypeParameters {\n");
		appendInitializationStart("TypeParameters");
		for (org.eclipse.ocl.pivot.@NonNull Class pClass : activeClassesSortedByName) {
			TemplateSignature templateSignature = pClass.getOwnedSignature();
			if (templateSignature != null) {
				s.append("\n");
				for (TemplateParameter parameter : templateSignature.getOwnedParameters()) {
					if (parameter != null) {
						s.append("		public static final ");
						s.appendClassReference(true, ExecutorTypeParameter.class);
						s.append(" ");
						s.appendScopedTypeName(pClass);
						s.append("_");
						s.appendParameterName(parameter);
						s.append(" = new ");
						s.appendClassReference(null, ExecutorTypeParameter.class);
						s.append("(");
						TemplateParameterId elementId = parameter.getTemplateParameterId();
						String idName = elementId.getLiteralName();
						if (idName != null) {
							s.appendClassReference(null, TypeId.class);
							s.append(".");
							s.append(idName);
						}
						else {
							s.appendClassReference(null, IdManager.class);
							s.append(".getTemplateParameterId(" + elementId.getIndex() + ")");
						}
						s.append(", ");
						s.appendString(ClassUtil.nonNullModel(parameter.getName()));
						s.append(");\n");
					}
				}
			}
			for (@NonNull Operation operation : getLocalOperationsSortedBySignature(pClass)) {
				templateSignature = operation.getOwnedSignature();
				if (templateSignature != null) {
					for (/*@NonNull*/ TemplateParameter parameter : templateSignature.getOwnedParameters()) {
						if (parameter != null) {
							s.append("		public static final ");
							s.appendClassReference(true, ExecutorTypeParameter.class);
							s.append(" _");
							operation.accept(emitLiteralVisitor);
							s.append("_");
							s.appendParameterName(parameter);
							s.append(" = new ");
							s.appendClassReference(null, ExecutorTypeParameter.class);
							s.append("(");
							TemplateParameterId elementId = parameter.getTemplateParameterId();
							String idName = elementId.getLiteralName();
							if (idName != null) {
								s.appendClassReference(null, TypeId.class);
								s.append(".");
								s.append(idName);
							}
							else {
								s.appendClassReference(null, IdManager.class);
								s.append(".getTemplateParameterId(" + elementId.getIndex() + ")");
							}
							s.append(", ");
							s.appendString(ClassUtil.nonNullModel(parameter.getName()));
							s.append(");\n");
						}
					}
				}
			}
		}
		appendInitializationEnd(false);
		s.append("	}\n");
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
		//		if (constants != null) {
		//			constants = s.rewriteManagedImports(constants);
		//		}
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
		s.append("public class " + tablesClassName + "\n");
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
		s.append("	public static void init() {}\n");
		if (tablesPostamble != null) {
			s.append(tablesPostamble);
		}
		s.append("}\n");
		return s.toString();
	}

	protected String getGenPackagePrefix() {
		return genPackage.getPrefix();
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
		s1.append(getTablesPackageName());
		s1.append(";\n");

		s1.append("\n");
		for (String classReference : s.getClassReferences()) {
			s1.append("import ");
			s1.append(classReference);
			s1.append(";\n");
		}
		s1.append("\n");
		s1.append(s.toString());
		return s1.toString();
	}
}
