/*******************************************************************************
 * Copyright (c) 2010, 2019 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.build.xtend;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.codegen.ecore.genmodel.GenFeature;
import org.eclipse.emf.codegen.ecore.genmodel.GenOperation;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.generator.AbstractGenModelHelper;
import org.eclipse.ocl.examples.codegen.generator.EcoreGenModelHelper;
import org.eclipse.ocl.examples.codegen.generator.GenModelHelper;
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.Enumeration;
import org.eclipse.ocl.pivot.EnumerationLiteral;
import org.eclipse.ocl.pivot.MapType;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.internal.manager.MetamodelManagerInternal;
import org.eclipse.ocl.pivot.internal.manager.PivotMetamodelManager;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

/**
 * NameQueries manges the mapping from a model element to its unique symbol name.
 * For most objects this is a simple one to one mapping. However for types
 * the mapping is for the comlete class to its name so that the name is shared by
 * all partial types. However where the partial type is important a distinct symbol
 * is allocated from partial type and this is used in preferemnce to the shared name.
 */
public class NameQueries
{
	public static final Logger logger = Logger.getLogger(NameQueries.class);

	public static @NonNull String rawEncodeName(@NonNull String name, @NonNull Integer arity) {
		return AbstractGenModelHelper.rawEncodeName(name, arity);
	}

	protected final @NonNull MetamodelManagerInternal metamodelManager;
	protected final @NonNull GenModelHelper genModelHelper;
	private @NonNull Map<String, Integer> counters = new HashMap<String, Integer>();
	private @NonNull Map<Object, String> definedSymbols = new HashMap<Object, String>();

	public NameQueries(@NonNull MetamodelManagerInternal metamodelManager) {
		this.metamodelManager = metamodelManager;
		this.genModelHelper = new EcoreGenModelHelper((PivotMetamodelManager)metamodelManager);
	}

	public @Nullable String basicGetSymbolName(@NonNull Object elem) {
		//		if (elem == null) {
		//			logger.error("getPrefixedSymbolName for '" + prefix + "'and null");
		//		}
				if ((elem instanceof CollectionType) && (((CollectionType)elem).getUnspecializedElement() != null)) {
		}
		else if ((elem instanceof MapType) && (((MapType)elem).getUnspecializedElement() != null)) {
		}
		else if (elem instanceof org.eclipse.ocl.pivot.Class) {
			//	elem = metamodelManager.getCompleteModel().getCompleteClass((Type)elem);
			elem = metamodelManager.getPrimaryClass((org.eclipse.ocl.pivot.Class)elem);
		}
		return definedSymbols.get(elem);
	}

	/*	public static List<Integer> codePoints(String s) {
		List<Integer> results = new ArrayList<Integer>();
		for (int i = 0; i < s.length(); i++) {
			int c = s.charAt(i);
			results.add(c);
		}
		return results;
	}

	public static List<String> convertStrings(String s) {
		List<String> results = new ArrayList<String>();
		for (int i = 0; i < s.length(); i++) {
			String c = s.substring(i, i+1);
			results.add(c);
		}
		return results;
	}

	public static String encodeName(@NonNull NamedElement element) {
		return AbstractGenModelHelper.encodeName(element);
	} */

	public @NonNull String getEcoreLiteral(@NonNull EnumerationLiteral enumerationLiteral) {
		Enumeration enumeration = enumerationLiteral.getOwningEnumeration();
		String nsURI = ClassUtil.nonNullModel(enumeration.getOwningPackage().getURI());
		GenPackage genPackage = ClassUtil.nonNullState(metamodelManager).getGenPackage(nsURI);
		if (genPackage != null) {
			return /*genPackage.getInterfacePackageName() +*/ genPackage.getPackageInterfaceName() + ".Literals." + CodeGenUtil.upperName(enumeration.getName())
			+ ".getEEnumLiteral(\"" + enumerationLiteral.getName() + "\")";
		}
		return enumeration.getName() + "." + CodeGenUtil.upperName(enumerationLiteral.getName());
	}

	public @NonNull String getEcoreLiteral(@NonNull Operation operation) {
		org.eclipse.ocl.pivot.Class type = PivotUtil.getOwningClass(operation);
		String nsURI = ClassUtil.nonNullModel(type.getOwningPackage().getURI());
		GenPackage genPackage = ClassUtil.nonNullState(metamodelManager).getGenPackage(nsURI);
		if (genPackage != null) {
			GenClass genClass = (GenClass)genModelHelper.getGenClassifier(type);
			assert genClass != null;
			GenOperation genOperation = genModelHelper.getGenOperation(operation);
			assert genOperation != null;
			String operationID = genClass.getOperationID(genOperation, false);
			StringBuilder s = new StringBuilder();
			s.append(/*genPackage.getInterfacePackageName() +*/genPackage.getPackageInterfaceName());
			s.append(".Literals.");
			s.append(operationID);
		/*	s.append(CodeGenUtil.upperName(type.getName()));
			s.append("___");
			s.append(CodeGenUtil.upperName(operation.getName()));
			Iterable<@NonNull Parameter> ownedParameters = PivotUtil.getOwnedParameters(operation);
			if (!Iterables.isEmpty(ownedParameters)) {
				s.append("_");
				for (@NonNull Parameter parameter : ownedParameters) {
					s.append("_");
					s.append(parameter.getType().getName().toUpperCase(Locale.getDefault()));
				}
			} */
			return s.toString();
		}
		return "\"" + operation.getName() + "\"";
	}

	public @NonNull String getEcoreLiteral(@NonNull Property property) {
		if (!property.isIsImplicit()) {
			org.eclipse.ocl.pivot.Class type = property.getOwningClass();
			if (type != null) {
				String nsURI = ClassUtil.nonNullModel(type.getOwningPackage().getURI());
				GenPackage genPackage = ClassUtil.nonNullState(metamodelManager).getGenPackage(nsURI);
				if (genPackage != null) {
					GenClass genClass = (GenClass)genModelHelper.getGenClassifier(type);
					assert genClass != null;
					GenFeature genFeature = genModelHelper.getGenFeature(property);
					assert genFeature != null;
					String featureID = genClass.getFeatureID(genFeature);
					return /*genPackage.getInterfacePackageName() +*/genPackage
							.getPackageInterfaceName()
							+ ".Literals."
							+ featureID;
						//	+ CodeGenUtil.upperName(type.getName())
						//	+ "__"
						//	+ CodeGenUtil.upperName(property.getName());
				}
			}
		}
		return "\"" + property.getName() + "\"";
	}

	public @NonNull String getEcoreLiteral(org.eclipse.ocl.pivot.@NonNull Class type) {
		String nsURI = ClassUtil.nonNullModel(type.getOwningPackage().getURI());
		GenPackage genPackage = ClassUtil.nonNullState(metamodelManager).getGenPackage(nsURI);
		if (genPackage != null) {
			return /*genPackage.getInterfacePackageName() +*/ genPackage.getPackageInterfaceName() + ".Literals." + CodeGenUtil.upperName(type.getName());
		}
		return "\"" + type.getName() + "\"";
	}

	public @NonNull String getEcoreLiteral(org.eclipse.ocl.pivot.@NonNull Package pkge) {
		String nsURI = ClassUtil.nonNullModel(pkge.getURI());
		GenPackage genPackage = ClassUtil.nonNullState(metamodelManager).getGenPackage(nsURI);
		if (genPackage != null) {
			return /*genPackage.getInterfacePackageName() +*/ genPackage.getPackageInterfaceName() + ".eINSTANCE";
		}
		return "null";
	}

	public @Nullable String getEcoreQualifiedPackageInterfaceName(org.eclipse.ocl.pivot.@NonNull Package pkge) {
		String nsURI = ClassUtil.nonNullModel(pkge.getURI());
		GenPackage genPackage = ClassUtil.nonNullState(metamodelManager).getGenPackage(nsURI);
		if (genPackage == null) {
			return null;
		}
		return genPackage.getQualifiedPackageInterfaceName();
	}

	/*	public static String getMoniker(@NonNull Element element) {
		return AS2Moniker.toString(element);
	}

	/**
	 * Return a symbol name for an eObject. This method is invoked from an
	 * Acceleo script and so there is only one call per distinct object. Acceleo
	 * maintains the cache that returns the symbol for old objects.
	 *
	 * @param eObject the object in question
	 * @return the symbol name
	 */
	public @NonNull String getSymbolName(@NonNull Object elem) {
		return getPrefixedSymbolName("symbol_", elem);
	}
	public @NonNull String getSymbolNameWithoutNormalization(@NonNull Object elem) {
		return getPrefixedSymbolNameWithoutNormalization("symbol_", elem);
	}

	public @NonNull String getPrefixedSymbolName(@NonNull String prefix, @NonNull Object elem) {
		//		if (elem == null) {
		//			logger.error("getPrefixedSymbolName for '" + prefix + "'and null");
		//		}
		if ((elem instanceof CollectionType) && (((CollectionType)elem).getUnspecializedElement() != null)) {
		}
		else if ((elem instanceof MapType) && (((MapType)elem).getUnspecializedElement() != null)) {
		}
		else if (elem instanceof org.eclipse.ocl.pivot.Class) {
			elem = metamodelManager.getCompleteModel().getCompleteClass((Type)elem);
			//			elem = metamodelManager.getPrimaryClass((org.eclipse.ocl.pivot.Class)elem);
		}
		return getPrefixedSymbolNameWithoutNormalization(prefix, elem);
	}

	public @NonNull String getPrefixedSymbolNameWithoutNormalization(@NonNull String prefix, @NonNull Object elem) {
		String symbol = definedSymbols.get(elem);
		if (symbol == null) {
			Integer count = counters.get(prefix);
			Integer newCount = count != null ? count+1 : 0;
			counters.put(prefix, newCount);
			symbol = count != null ? prefix + "_" + newCount.toString() : prefix;
			putSymbolName(elem, symbol);
		}
		if (symbol.startsWith("symbol_")) {
			getClass();			// FIXME Debugging
		}
		return symbol;
	}

	public void putSymbolName(@NonNull Object elem, @NonNull String symbolName) {
		if (symbolName.startsWith("Model")) {
			getClass();			// FIXME Debugging
		}
		if (symbolName.startsWith("symbol_")) {
			getClass();			// FIXME Debugging
		}
		if (symbolName.startsWith("_OclElement")) {
			getClass();			// FIXME Debugging
		}
		String oldSymbolName = definedSymbols.put(elem, symbolName);
		assert oldSymbolName == null;
	}
}
