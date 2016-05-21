/*******************************************************************************
 * Copyright (c) 2013, 2016 CEA LIST and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.TypeUtil;

public class OCLinEcoreTables extends OCLinEcoreTablesUtils
{
	protected final boolean useNullAnnotations;
	private @Nullable String precedingPackageName = null;		// Initialization linkage
	private @Nullable String currentPackageName = null;			// Initialization linkage
	protected final @NonNull ImportManager importManager;
	
	public OCLinEcoreTables(@NonNull GenPackage genPackage) {
		super(genPackage);
		GenModel genModel = ClassUtil.nonNullState(genPackage.getGenModel());
		this.useNullAnnotations = OCLinEcoreGenModelGeneratorAdapter.useNullAnnotations(genModel);
		this.importManager = new ImportManager(genPackage.getReflectionPackageName());
	}

	protected void appendConstants(@NonNull String constants) {
		s.append("	/**\n");
		s.append("	 *	Constants used by auto-generated code.\n");
		s.append("	 */\n");
		int i = 0;
		while (i < constants.length()) {
			int j = constants.indexOf("<%", i);
			if (j >= 0) {
				int k = constants.indexOf("%>", j+2);
				if (k >= 0) {
					s.append(constants.substring(i, j));
					@NonNull String referencedClass = constants.substring(j+2, k);
					s.appendClassReference(referencedClass);
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
			s.appendClassReference(TypeUtil.class);
			s.append(".EMPTY_PARAMETER_TYPES");
		}
	}

	protected void appendTypeFlags(@NonNull Type type) {
		if (type instanceof OrderedSetType) {
			s.appendClassReference(ExecutorType.class);
			s.append(".ORDERED | ");
			s.appendClassReference(ExecutorType.class);
			s.append(".UNIQUE");
		}
		else if (type instanceof SetType) {
			s.appendClassReference(ExecutorType.class);
			s.append(".UNIQUE");
		}
		else if (type instanceof SequenceType) {
			s.appendClassReference(ExecutorType.class);
			s.append(".ORDERED");
		}
		else {
			s.append("0");
		}
		if ((type instanceof org.eclipse.ocl.pivot.Class) && ((org.eclipse.ocl.pivot.Class)type).isIsAbstract()) {
			s.append(" | ");
			s.appendClassReference(ExecutorType.class);
			s.append(".ABSTRACT");
		}
	}

	protected void appendUpperName(@NonNull NamedElement namedElement) {
		s.append(ClassUtil.nonNullModel(CodeGenUtil.upperName(namedElement.getName())));
	}
	
	protected @NonNull String atNonNull() {
		if (useNullAnnotations) {
			s.addClassReference("NonNull", "org.eclipse.jdt.annotation.NonNull");
			return "@NonNull";
		}
		else {
			return "/*@NonNull*/";
		}
	}

	protected @NonNull LinkedHashMap<org.eclipse.ocl.pivot.Class, LinkedHashMap<org.eclipse.ocl.pivot.Class, List<Operation>>> computeFragmentOperations() {
		LinkedHashMap<org.eclipse.ocl.pivot.Class, LinkedHashMap<org.eclipse.ocl.pivot.Class, List<Operation>>> fragmentOperations = new LinkedHashMap<org.eclipse.ocl.pivot.Class, LinkedHashMap<org.eclipse.ocl.pivot.Class, List<Operation>>>();
		for (/*@NonNull*/ org.eclipse.ocl.pivot.Class pClass : activeClassesSortedByName) {
			assert pClass != null;
			LinkedHashMap<org.eclipse.ocl.pivot.Class, List<Operation>> classOperations = new LinkedHashMap<org.eclipse.ocl.pivot.Class, List<Operation>>();
			fragmentOperations.put(pClass, classOperations);
			List<Operation> sortedOperations = new ArrayList<Operation>(getOperations(pClass));
			Collections.sort(sortedOperations, signatureComparator);
			classOperations.put(pClass, sortedOperations);
			for (/*@NonNull*/ org.eclipse.ocl.pivot.Class pSuperClass : getAllProperSupertypesSortedByName(pClass)) {
				assert pSuperClass != null;
				List<Operation> sortedSuperOperations = new ArrayList<Operation>(getOperations(pSuperClass));
				Collections.sort(sortedSuperOperations, signatureComparator);
				classOperations.put(pSuperClass, sortedSuperOperations);
			}
		}
		return fragmentOperations;
	}	
	
	protected @NonNull LinkedHashMap<org.eclipse.ocl.pivot.Class,List<Property>> computeFragmentProperties() {
		LinkedHashMap<org.eclipse.ocl.pivot.Class,List<Property>> fragmentProperties = new LinkedHashMap<org.eclipse.ocl.pivot.Class,List<Property>>();
		for (/*@NonNull*/ org.eclipse.ocl.pivot.Class pClass : activeClassesSortedByName) {
			assert pClass != null;
			Set<Property> allProperties = new HashSet<Property>();
			for (/*@NonNull*/ org.eclipse.ocl.pivot.Class pSuperClass : getAllSupertypesSortedByName(pClass)) {
				assert pSuperClass != null;
				for (/*@NonNull*/ Property prop : getLocalPropertiesSortedByName(pSuperClass)) {
					assert prop != null;
					if (isProperty(prop)) {
						allProperties.add(prop);
					}
				}
			}
			List<Property> sortedProperties = new ArrayList<Property>(allProperties);
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
		for (org.eclipse.ocl.pivot.Class pClass : activeClassesSortedByName) {
			if (pClass instanceof Enumeration) {
				s.append("\n");
				List<EnumerationLiteral> enumerationLiterals = ((Enumeration)pClass).getOwnedLiterals();
				for (int i = 0; i < enumerationLiterals.size(); i++) {
					EnumerationLiteral enumerationLiteral = ClassUtil.nonNullModel(enumerationLiterals.get(i));
					s.append("		public static final " + atNonNull() + " ");
					s.appendClassReference(EcoreExecutorEnumerationLiteral.class);
					s.append(" ");
					s.appendScopedTypeName(pClass);
					s.append("__");
					s.appendName(enumerationLiteral);
					s.append(" = new ");
					s.appendClassReference(EcoreExecutorEnumerationLiteral.class);
					s.append("(");
					s.append(genPackage.getPrefix() + "Package.Literals.");
					appendUpperName(pClass);
					s.append(".getEEnumLiteral(");
					s.appendString(ClassUtil.nonNullModel(enumerationLiteral.getName()));
					s.append("), Types.");
					s.appendScopedTypeName(pClass);
					s.append(", " + i + ");\n");
				}
				s.append("		private static final " + atNonNull() + " ");
				s.appendClassReference(EcoreExecutorEnumerationLiteral.class);
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
		for (org.eclipse.ocl.pivot.Class pClass : activeClassesSortedByName) {
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
		for (/*@NonNull*/ org.eclipse.ocl.pivot.Class pClass : activeClassesSortedByName) {
			assert pClass != null;
			s.append("\n");
			List<org.eclipse.ocl.pivot.Class> allSupertypesSortedByName = getAllSupertypesSortedByName(pClass);
			for (/*@NonNull*/ org.eclipse.ocl.pivot.Class pSuperClass : allSupertypesSortedByName) {
				assert pSuperClass != null;
				s.append("		private static final " + atNonNull() + " ");
				s.appendClassReference(ExecutorFragment.class);
				s.append(" ");
				s.appendScopedTypeName(pClass);
				s.append("__");
				s.appendUnscopedTypeName(metamodelManager, pSuperClass);
				s.append(" = new ");
				s.appendClassReference(ExecutorFragment.class);
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

	protected void declareFragmentOperations(@NonNull List<LinkedHashMap<org.eclipse.ocl.pivot.Class, LinkedHashMap<org.eclipse.ocl.pivot.Class, List<Operation>>>> paginatedFragmentOperations) {
		s.append("	/**\n");
		s.append("	 *	The lists of local operations or local operation overrides for each fragment of each type.\n");
		s.append("	 */\n");
		int page = 1;
		int pageMax = paginatedFragmentOperations.size();
		for (LinkedHashMap<org.eclipse.ocl.pivot.Class, LinkedHashMap<org.eclipse.ocl.pivot.Class, List<Operation>>> fragmentOperations : paginatedFragmentOperations) {
			String pagedName = getPagedName("FragmentOperations", page, pageMax);
			s.append("	public static class " + pagedName);
			s.append(" {\n");
			appendInitializationStart(pagedName);	
			for (/*@NonNull*/ org.eclipse.ocl.pivot.Class pClass : fragmentOperations.keySet()) {
				assert pClass != null;
				s.append("\n");
				LinkedHashMap<org.eclipse.ocl.pivot.Class, List<Operation>> classOperations = fragmentOperations.get(pClass);
				for (/*@NonNull*/ org.eclipse.ocl.pivot.Class pSuperClass : classOperations.keySet()) {
					assert pSuperClass != null;
					List<Operation> sortedOperations = classOperations.get(pSuperClass);
					s.append("		private static final " + atNonNull() + " ");
					s.appendClassReference(ExecutorOperation.class);
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
			for (/*@NonNull*/ org.eclipse.ocl.pivot.Class pClass : fragmentOperations.keySet()) {
				assert pClass != null;
				for (org.eclipse.ocl.pivot.Class pSuperClass : getAllSupertypesSortedByName(pClass)) {
					assert pSuperClass != null;
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
	
	protected void declareFragmentProperties(@NonNull List<LinkedHashMap<org.eclipse.ocl.pivot.Class, List<Property>>> paginatedFragmentProperties) {
		s.append("	/**\n");
		s.append("	 *	The lists of local properties for the local fragment of each type.\n");
		s.append("	 */\n");
		int page = 1;
		int pageMax = paginatedFragmentProperties.size();
		for (LinkedHashMap<org.eclipse.ocl.pivot.Class, List<Property>> fragmentProperties : paginatedFragmentProperties) {
			String pagedName = getPagedName("FragmentProperties", page, pageMax);
			s.append("	public static class " + pagedName);
			s.append(" {\n");
			appendInitializationStart(pagedName);	
			for (@SuppressWarnings("null")org.eclipse.ocl.pivot.@NonNull Class pClass : fragmentProperties.keySet()) {
				List<Property> sortedProperties = fragmentProperties.get(pClass);
				s.append("\n");
				s.append("		private static final " + atNonNull() + " ");
				s.appendClassReference(ExecutorProperty.class);
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
			for (/*@NonNull*/ org.eclipse.ocl.pivot.Class pClass : fragmentProperties.keySet()) {
				assert pClass != null;
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
		s.append("	 */\n");
		s.append("	public static class Operations {\n");
		appendInitializationStart("Operations");	
		for (/*@NonNull*/ org.eclipse.ocl.pivot.Class pClass : activeClassesSortedByName) {
			assert pClass != null;
			List<Operation> sortedOperations = new ArrayList<Operation>(getOperations(pClass));
			Collections.sort(sortedOperations, signatureComparator);
			for (int i = 0; i < sortedOperations.size(); i++) {
				if (i == 0) {
					s.append("\n");
				}
				Operation op = sortedOperations.get(i);
				TemplateSignature ownedTemplateSignature = op.getOwnedSignature();
				s.append("		public static final " + atNonNull() + " ");
				s.appendClassReference(ExecutorOperation.class);
				s.append(" ");
				op.accept(emitLiteralVisitor);
				s.append(" = new ");
				s.appendClassReference(ExecutorOperation.class);
				s.append("(");
				s.appendString(ClassUtil.nonNullModel(op.getName()));
				s.append(", ");
				appendParameterTypesName(op.getParameterTypes());
				s.append(", ");
				op.getOwningClass().accept(emitLiteralVisitor);
				s.append(",\n			" + i + ", ");
				if (ownedTemplateSignature == null) {
					s.appendClassReference(TemplateParameters.class);
					s.append(".EMPTY_LIST");
				}
				else {
					s.appendClassReference(TypeUtil.class);
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
		Set<ParameterTypes> allLists = new HashSet<ParameterTypes>();
		for (/*@NonNull*/ org.eclipse.ocl.pivot.Class pClass : activeClassesSortedByName) {
			assert pClass != null;
			for (Operation operation : getOperations(pClass)) {
				allLists.add(operation.getParameterTypes());
			}
		}
		s.append("	/**\n");
		s.append("	 *	The parameter lists shared by operations.\n");
		s.append("	 */\n");
		s.append("	public static class Parameters {\n");
		appendInitializationStart("Parameters");	
		s.append("\n");
		List<ParameterTypes> sortedLists = new ArrayList<ParameterTypes>(allLists);
		Collections.sort(sortedLists, templateBindingNameComparator);
		for (/*@NonNull*/ ParameterTypes types : sortedLists) {
			assert types != null;
			if (types.size() > 0) {				// Bug 471118 avoid deprecated _ identifier
				s.append("		public static final " + atNonNull() + " ");
				s.appendClassReference(ParameterTypes.class);
				s.append(" ");
				s.append(getTemplateBindingsName(types));
				s.append(" = ");
				s.appendClassReference(TypeUtil.class);
				s.append(".createParameterTypes(");
				for (int i = 0; i < types.size(); i++) {
					if (i > 0) {
						s.append(", ");
					}
					Type type = PivotUtilInternal.getType(types.get(i));
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
		s.append("	 */\n");
		s.append("	public static class Properties {\n");
		appendInitializationStart("Properties");	
		boolean isFirst = false;
		for (/*@NonNull*/ org.eclipse.ocl.pivot.Class pClass : activeClassesSortedByName) {
			assert pClass != null;
			List<Property> sortedProperties = getLocalPropertiesSortedByName(pClass);
			assert pClass != null;
			for (int i = 0; i < sortedProperties.size(); i++) {
				Property prop = ClassUtil.nonNullModel(sortedProperties.get(i));
				if (isProperty(prop)) {
					s.append("\n");
					if (isFirst) {
						s.append("\n");
					}
					isFirst = false;
					s.append("		public static final " + atNonNull() + " ");
					s.appendClassReference(ExecutorProperty.class);
					s.append(" ");
					prop.accept(emitLiteralVisitor);
					s.append(" = new ");
					String name = ClassUtil.nonNullModel(prop.getName());
					if (prop.getImplementationClass() != null) {
						s.appendClassReference(ExecutorPropertyWithImplementation.class);
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
							s.appendClassReference(ExecutorPropertyWithImplementation.class);
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
							s.appendClassReference(EcoreExecutorProperty.class);
							s.append("(");
							s.append(genPackage.getPrefix());
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
							s.appendClassReference(ExecutorPropertyWithImplementation.class);
							s.append("(");
							s.appendString(name);
							s.append(", " );
							pClass.accept(emitLiteralVisitor);
							s.append(", " + i + ", new ");
							s.appendClassReference(EcoreLibraryOppositeProperty.class);
							s.append("(");
							s.append(genPackage.getPrefix());
							s.append("Package.Literals." );
							appendUpperName(ClassUtil.nonNullModel(opposite.getOwningClass()));
							s.append("__" );
							appendUpperName(opposite);
							s.append("))");
						}
						else {
							s.appendClassReference(ExecutorPropertyWithImplementation.class);
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
		s.append("		public static final " + atNonNull() + " ");
		s.appendClassReference(typeClass);
		s.append(" ");
		s.appendScopedTypeName(pClass);
		s.append(" = ");
		if (!hasEcore(pClass) || (pClass instanceof AnyType) || (pClass instanceof CollectionType) || (pClass instanceof VoidType) || (pClass instanceof InvalidType)) {
			s.append("new ");
			s.appendClassReference(typeClass);
			s.append("(");
			if (isBuiltInType(pClass)) {
				s.appendClassReference(TypeId.class);
				s.append(".");
				appendUpperName(pClass);
			}
			else {
				s.appendString(ClassUtil.nonNullModel(pClass.getName()));
			}
		}
		else {
			s.append("new ");
			s.appendClassReference(typeClass);
			s.append("(" + genPackage.getPrefix() + "Package.Literals.");
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

	protected void declareTypes(@NonNull List<LinkedHashMap<org.eclipse.ocl.pivot.Class, LinkedHashMap<org.eclipse.ocl.pivot.Class, List<Operation>>>> paginatedFragmentOperations, @NonNull List<LinkedHashMap<org.eclipse.ocl.pivot.Class, List<Property>>> paginatedFragmentProperties) {
		s.append("	/**\n");
		s.append("	 *	The type descriptors for each type.\n");
		s.append("	 */\n");
		s.append("	public static class Types {\n");
		appendInitializationStart("Types");	
		s.append("\n");
		for (/*@NonNull*/ org.eclipse.ocl.pivot.Class pClass : activeClassesSortedByName) {
			assert pClass != null;
			declareType(pClass);
		}
		s.append("\n");
		s.append("		private static final " + atNonNull() + " ");
		s.appendClassReference(EcoreExecutorType.class);
		s.append(" " + atNonNull() + " [] types = {");
		boolean isFirst = true;
		for (/*@NonNull*/ org.eclipse.ocl.pivot.Class pClass : activeClassesSortedByName) {
			assert pClass != null;
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
		org.eclipse.ocl.pivot.Package extendedPackage = getExtendedPackage(pPackage);
		if (extendedPackage != null) {
			s.append("			LIBRARY.addExtension(");
			s.appendClassReference(getQualifiedTablesClassName(extendedPackage));
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
		for (/*@NonNull*/ org.eclipse.ocl.pivot.Class pClass : activeClassesSortedByName) {
			assert pClass != null;
			final Map<org.eclipse.ocl.pivot.Class, @NonNull Integer> allSuperTypes = new HashMap<org.eclipse.ocl.pivot.Class, @NonNull Integer>();
			int myDepth = getAllSuperClasses(allSuperTypes, pClass);
			int[] typesPerDepth = new int[myDepth+1];
			for (int i = 0; i <= myDepth; i++) {
				typesPerDepth[i] = 0;
			}
			for (Integer aDepth : allSuperTypes.values()) {
				typesPerDepth[aDepth]++;
			}
			List<Type> superTypes = new ArrayList<Type>(allSuperTypes.keySet());
			Collections.sort(superTypes, new Comparator<Type>()
			{
				@Override
				public int compare(Type o1, Type o2) {
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
			s.append("		private static final " + atNonNull() + " ");
			s.appendClassReference(ExecutorFragment.class);
			s.append(" " + atNonNull() + " [] ");
			s.appendScopedTypeName(pClass);
			s.append(" =\n");
			s.append("		{");
			boolean isFirst = true;
			for (/*@NonNull*/ Type superClass : superTypes) {
				assert superClass != null;
				if (!isFirst) {
					s.append(",");
				}
				s.append("\n");
				s.append("			Fragments.");
				s.appendScopedTypeName(pClass);
				s.append("__");
				s.appendUnscopedTypeName(metamodelManager, superClass);
				s.append(" /* " + allSuperTypes.get(superClass) + " */");
				isFirst = false;
			}
			s.append("\n");
			s.append("		};\n");
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
		for (/*@NonNull*/ org.eclipse.ocl.pivot.Class pClass : activeClassesSortedByName) {
			assert pClass != null;
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
		for (/*@NonNull*/ org.eclipse.ocl.pivot.Class pClass : activeClassesSortedByName) {
			TemplateSignature templateSignature = pClass.getOwnedSignature();
			if (templateSignature != null) {
				s.append("\n");
				for (TemplateParameter parameter : templateSignature.getOwnedParameters()) {
					if (parameter != null) {
						s.append("		public static final " + atNonNull() + " ");
						s.appendClassReference(ExecutorTypeParameter.class);
						s.append(" ");
						s.appendScopedTypeName(pClass);
						s.append("_");
						s.appendParameterName(parameter);
						s.append(" = new ");
						s.appendClassReference(ExecutorTypeParameter.class);
						s.append("(");
						TemplateParameterId elementId = parameter.getTemplateParameterId();
						String idName = elementId.getLiteralName();
						if (idName != null) {
							s.appendClassReference(TypeId.class);
							s.append(".");
							s.append(idName);
						}
						else {
							s.appendClassReference(IdManager.class);
							s.append(".getTemplateParameterId(" + elementId.getIndex() + ")");
						}
 						s.append(", ");
						s.appendString(ClassUtil.nonNullModel(parameter.getName()));
						s.append(");\n");
					}
				}
			}
			for (/*@NonNull*/ Operation operation : getLocalOperationsSortedBySignature(pClass)) {
				assert operation != null;
				templateSignature = operation.getOwnedSignature();
				if (templateSignature != null) {
					for (/*@NonNull*/ TemplateParameter parameter : templateSignature.getOwnedParameters()) {
						if (parameter != null) {
							s.append("		public static final " + atNonNull() + " ");
							s.appendClassReference(ExecutorTypeParameter.class);
							s.append(" _");
							operation.accept(emitLiteralVisitor);
							s.append("_");
							s.appendParameterName(parameter);
							s.append(" = new ");
							s.appendClassReference(ExecutorTypeParameter.class);
							s.append("(");
							TemplateParameterId elementId = parameter.getTemplateParameterId();
							String idName = elementId.getLiteralName();
							if (idName != null) {
								s.appendClassReference(TypeId.class);
								s.append(".");
								s.append(idName);
							}
							else {
								s.appendClassReference(IdManager.class);
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
		if (constants != null) {
			constants = s.rewriteManagedImports(constants);
		}
		String tablesClassName = getTablesClassName(genPackage);
		LinkedHashMap<org.eclipse.ocl.pivot.Class, LinkedHashMap<org.eclipse.ocl.pivot.Class, List<Operation>>> fragmentOperations = computeFragmentOperations();
		LinkedHashMap<org.eclipse.ocl.pivot.Class, List<Property>> fragmentProperties = computeFragmentProperties();
		List<LinkedHashMap<org.eclipse.ocl.pivot.Class, LinkedHashMap<org.eclipse.ocl.pivot.Class, List<Operation>>>> paginatedFragmentOperations = paginateFragmentOperations(fragmentOperations);
		List<LinkedHashMap<org.eclipse.ocl.pivot.Class, List<Property>>> paginatedFragmentProperties = paginateFragmentProperties(fragmentProperties);
		s.append("/**\n");
		s.append(" * " + tablesClassName + " provides the dispatch tables for the " + pPackage.getName() + " for use by the OCL dispatcher.\n");
		s.append(" *\n");
		s.append(" * In order to ensure correct static initialization, a top level class element must be accessed\n");
		s.append(" * before any nested class element. Therefore an access to PACKAGE.getClass() is recommended.\n");
		s.append(" */\n");
		s.append("@SuppressWarnings(\"nls\")\n");
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
		s.append(atNonNull());
		s.append(" ");
		s.appendClassReference(EcoreExecutorPackage.class);
		s.append(" PACKAGE = new ");
		s.appendClassReference(EcoreExecutorPackage.class);
		s.append("(" + genPackage.getPrefix() + "Package.eINSTANCE");
		if (pPackage.getPackageId() == IdManager.METAMODEL) {
			s.append(", ");
			s.appendClassReference(IdManager.class);
			s.append(".METAMODEL");
		}
		s.append(");\n");
		
		s.append("\n");
		s.append("	/**\n");
		s.append("	 *	The library of all packages and types.\n");
		s.append("	 */\n");
		
		s.append("	public static final " + atNonNull() + " ");
		s.appendClassReference(ExecutorStandardLibrary.class);
		s.append(" LIBRARY = ");
		if (hasSharedLibrary()) {
			s.appendClassReference(getSharedLibrary());
			s.append(".LIBRARY");
		}
		else {
			s.append("new ");
			s.appendClassReference(ExecutorStandardLibrary.class);
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
		s.append("}\n");
		return s.toString();
	}
	
	protected @NonNull List<LinkedHashMap<org.eclipse.ocl.pivot.Class, LinkedHashMap<org.eclipse.ocl.pivot.Class, List<Operation>>>> paginateFragmentOperations(@NonNull LinkedHashMap<org.eclipse.ocl.pivot.Class, LinkedHashMap<org.eclipse.ocl.pivot.Class, List<Operation>>> fragmentOperations) {
		List<LinkedHashMap<org.eclipse.ocl.pivot.Class, LinkedHashMap<org.eclipse.ocl.pivot.Class, List<Operation>>>> paginatedFragmentOperations = new ArrayList<LinkedHashMap<org.eclipse.ocl.pivot.Class, LinkedHashMap<org.eclipse.ocl.pivot.Class, List<Operation>>>>();
		LinkedHashMap<org.eclipse.ocl.pivot.Class, LinkedHashMap<org.eclipse.ocl.pivot.Class, List<Operation>>> pageOfFragmentOperations = null;
		int size = 0;
		for (org.eclipse.ocl.pivot.Class pClass : fragmentOperations.keySet()) {
			LinkedHashMap<org.eclipse.ocl.pivot.Class, List<Operation>> line = fragmentOperations.get(pClass);
			int lineSize = 0;
			for (List<Operation> operations : line.values()) {
				lineSize += operations.size();
			}
			if ((pageOfFragmentOperations == null) || size+lineSize > 4000) {
				pageOfFragmentOperations = new LinkedHashMap<org.eclipse.ocl.pivot.Class, LinkedHashMap<org.eclipse.ocl.pivot.Class, List<Operation>>>();
				size = 0;
				paginatedFragmentOperations.add(pageOfFragmentOperations);
			}
			pageOfFragmentOperations.put(pClass, line);
			size += lineSize;
		}
		return paginatedFragmentOperations;
	}
	
	protected @NonNull List<LinkedHashMap<org.eclipse.ocl.pivot.Class,List<Property>>> paginateFragmentProperties(@NonNull LinkedHashMap<org.eclipse.ocl.pivot.Class, List<Property>> fragmentProperties) {
		List<LinkedHashMap<org.eclipse.ocl.pivot.Class,List<Property>>> paginatedFragmentProperties = new ArrayList<LinkedHashMap<org.eclipse.ocl.pivot.Class,List<Property>>>();
		LinkedHashMap<org.eclipse.ocl.pivot.Class,List<Property>> pageOfFragmentProperties = null;
		int size = 0;
		for (org.eclipse.ocl.pivot.Class pClass : fragmentProperties.keySet()) {
			List<Property> line = fragmentProperties.get(pClass);
			int lineSize = line.size();
			if ((pageOfFragmentProperties == null) || size+lineSize > 4000) {
				pageOfFragmentProperties = new LinkedHashMap<org.eclipse.ocl.pivot.Class,List<Property>>();
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
			s1.append(" * ");
			s1.append(copyright.replace("\r", ""));
			s1.append("\n");
		}
		s1.append(" *************************************************************************\n");
		s1.append(" * This code is 100% auto-generated\n");
		s1.append(" * from:\n");
		for (@SuppressWarnings("null")org.eclipse.ocl.pivot.@NonNull Package dPackage : metamodelManager.getPartialPackages(pPackage, false)) {
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
		s1.append(genPackage.getReflectionPackageName());
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
