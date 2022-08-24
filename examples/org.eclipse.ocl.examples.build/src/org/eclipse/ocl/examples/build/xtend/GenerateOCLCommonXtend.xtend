/*******************************************************************************
 * Copyright (c) 2013 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.build.xtend

import java.util.ArrayList
import java.util.Collection
import java.util.Collections
import org.eclipse.emf.ecore.EObject
import org.eclipse.ocl.pivot.AnyType
import org.eclipse.ocl.pivot.Class
import org.eclipse.ocl.pivot.CollectionType
import org.eclipse.ocl.pivot.Comment
import org.eclipse.ocl.pivot.EnumerationLiteral
import org.eclipse.ocl.pivot.LambdaType
import org.eclipse.ocl.pivot.MapType
import org.eclipse.ocl.pivot.Model
import org.eclipse.ocl.pivot.Operation
import org.eclipse.ocl.pivot.Package
import org.eclipse.ocl.pivot.Parameter
import org.eclipse.ocl.pivot.Precedence
import org.eclipse.ocl.pivot.PrimitiveType
import org.eclipse.ocl.pivot.Property
import org.eclipse.ocl.pivot.TemplateBinding
import org.eclipse.ocl.pivot.TemplateParameter
import org.eclipse.ocl.pivot.TemplateParameterSubstitution
import org.eclipse.ocl.pivot.TemplateSignature
import org.eclipse.ocl.pivot.utilities.ClassUtil
import org.eclipse.ocl.pivot.values.Unlimited
import org.eclipse.ocl.pivot.utilities.PivotConstants
import org.eclipse.ocl.pivot.ids.TypeId
import org.eclipse.ocl.pivot.utilities.NameUtil

abstract class GenerateOCLCommonXtend extends GenerateOCLCommon
{
	protected def String declareClassTypes(/*@NonNull*/ Model root, /*@NonNull*/ Collection</*@NonNull*/ String> excludedEClassifierNames) {
		var pkge2classTypes = root.getSortedClassTypes();
		if (pkge2classTypes.isEmpty()) return "";
		var sortedPackages = root.getSortedPackages(pkge2classTypes.keySet());
		'''
		«FOR pkge : sortedPackages»

			«FOR type : ClassUtil.nullFree(pkge2classTypes.get(pkge))»
				private final @NonNull «type.eClass.name» «type.getPrefixedSymbolNameWithoutNormalization("_" + type.partialName())» = create«type.
				eClass.name»("«type.name»");
			«ENDFOR»
		«ENDFOR»
		'''
	}

	protected def String declareCollectionTypes(Model root) {
		var pkge2collectionTypes = root.getSortedCollectionTypes();
		if (pkge2collectionTypes.isEmpty()) return "";
		var sortedPackages = root.getSortedPackages(pkge2collectionTypes.keySet());
		'''
		«FOR pkge : sortedPackages»

			«FOR type : ClassUtil.nullFree(pkge2collectionTypes.get(pkge))»«var typeName = type.getPrefixedSymbolName("_" + type.getName() + "_" + type.getElementType().partialName() + (if (type.isIsNullFree()) "_NullFree" else "") )»
			«IF type.getOwnedSignature() !== null»
			private final @NonNull «type.eClass.name» «typeName» = create«type.eClass.name»(«getEcoreLiteral(type)»«IF type.getOwnedSignature() !== null»«FOR templateParameter : type.getOwnedSignature().getOwnedParameters()», «templateParameter.getSymbolName()»«ENDFOR»«ENDIF»);
			«ENDIF»
			«ENDFOR»
			«FOR type : ClassUtil.nullFree(pkge2collectionTypes.get(pkge))»«var typeName = type.getPrefixedSymbolName("_" + type.getName() + "_" + type.getElementType().partialName() + (if (type.isIsNullFree()) "_NullFree" else "") )»
			«IF type.getOwnedSignature() === null»
			private final @NonNull «type.eClass.name» «typeName» = create«type.eClass.name»(«type.getUnspecializedElement().getSymbolName()»);
			«ENDIF»
			«ENDFOR»
		«ENDFOR»
		'''
	}

	protected def String declareEnumerations(/*@NonNull*/ Model root) {
		var pkge2enumerations = root.getSortedEnumerations();
		if (pkge2enumerations.isEmpty()) return "";
		var sortedPackages = root.getSortedPackages(pkge2enumerations.keySet());
		'''
		
		«FOR pkge : sortedPackages»
			«FOR enumeration : ClassUtil.nullFree(pkge2enumerations.get(pkge))»
				«var enumerationName = enumeration.getPrefixedSymbolName("_" + enumeration.partialName())»
				private final @NonNull Enumeration «enumerationName» = createEnumeration("«enumeration.name»");
				«FOR enumerationLiteral : enumeration.ownedLiterals»
					private final @NonNull EnumerationLiteral «enumerationLiteral.getPrefixedSymbolName(
				"el_" + enumerationName + "_" + enumerationLiteral.name)» = createEnumerationLiteral("«enumerationLiteral.name»");
				«ENDFOR»
			«ENDFOR»
		«ENDFOR»
		'''
	}

	protected def String declareMapTypes(/*@NonNull*/ Model root) {
		var pkge2mapTypes = root.getSortedMapTypes();
		if (pkge2mapTypes.isEmpty()) return "";
		var sortedPackages = root.getSortedPackages(pkge2mapTypes.keySet());
		'''

		«FOR pkge : sortedPackages»
			«FOR type : ClassUtil.nullFree(pkge2mapTypes.get(pkge))»
				«IF type.getOwnedSignature() !== null»
					private final @NonNull «type.eClass.name» «type.getPrefixedSymbolName("_" + type.getName() + "_" + type.getKeyType().partialName() + "_" + type.getValueType().partialName())» = create«type.
					eClass.name»(«getEcoreLiteral(type)»«IF type.getOwnedSignature() !== null»«FOR templateParameter : type.getOwnedSignature().getOwnedParameters()», «templateParameter.getSymbolName()»«ENDFOR»«ENDIF»);
				«ENDIF»
			«ENDFOR»
			«FOR type : ClassUtil.nullFree(pkge2mapTypes.get(pkge))»
				«IF type.getOwnedSignature() === null»
					private final @NonNull «type.eClass.name» «type.getPrefixedSymbolName("_" + type.getName() + "_" + type.getKeyType().partialName() + "_" + type.getValueType().partialName())» = create«type.
					eClass.name»(«type.getUnspecializedElement().getSymbolName()»);
				«ENDIF»
			«ENDFOR»
		«ENDFOR»
		'''
	}

	protected def String declarePrimitiveTypes(/*@NonNull*/ Model root) {
		var pkge2primitiveTypes = root.getSortedPrimitiveTypes();
		if (pkge2primitiveTypes.isEmpty()) return "";
		var sortedPackages = root.getSortedPackages(pkge2primitiveTypes.keySet());
		'''
			«FOR pkge : sortedPackages»

				«FOR type : ClassUtil.nullFree(pkge2primitiveTypes.get(pkge))»
				private final @NonNull Class «type.getPrefixedSymbolNameWithoutNormalization("_" + type.partialName())» = createPrimitiveType("«type.name»");
				«ENDFOR»
			«ENDFOR»
		'''
	}

	protected def String declareTupleTypes(/*@NonNull*/ Model root) {
		var tupleTypes = root.getSortedTupleTypes();
		if (tupleTypes.isEmpty()) return "";
		'''

			«FOR type : tupleTypes»
				private final @NonNull TupleType «type.getPrefixedSymbolName("_" + type.partialName())» = createTupleType("«type.name»",
				«FOR property : type.getSortedTupleParts() BEFORE ("\t") SEPARATOR (",\n\t")»
				createProperty("«property.name»", «property.type.getSymbolName()»)«ENDFOR»);
			«ENDFOR»
		'''
	}

	protected def String defineCoercions(/*@NonNull*/ Model root) {
		var allCoercions = root.getSortedCoercions();
		if (allCoercions.isEmpty()) return "";
		'''

			private void installCoercions() {
				List<Operation> ownedCoercions;
				Operation coercion;
				«FOR type : allCoercions.getSortedOwningTypes()»
					ownedCoercions = «type.getSymbolName()».getCoercions();
					«FOR coercion : (type as PrimitiveType).getSortedCoercions(allCoercions)»
						ownedCoercions.add(coercion = «coercion.getSymbolName()»);
						«IF coercion.bodyExpression !== null»
							createBodyExpression(operation, «coercion.type.getSymbolName()», "«coercion.bodyExpression.javaString()»");
						«ENDIF»
					«ENDFOR»
				«ENDFOR»
			}
		'''
	}

	protected def String defineClassTypes(/*@NonNull*/ Model root) {
		var pkge2classTypes = root.getSortedClassTypes();
		if (pkge2classTypes.isEmpty()) return "";
		var sortedPackages = root.getSortedPackages(pkge2classTypes.keySet());
		'''

			private void installClassTypes() {
				List<Class> ownedClasses;
				List<Class> superClasses;
				Class type;
				«FOR pkge : sortedPackages»

				ownedClasses = «pkge.getSymbolName()».getOwnedClasses();
				«FOR type : ClassUtil.nullFree(pkge2classTypes.get(pkge))»«var templateSignature = type.getOwnedSignature()»
				type = «type.getSymbolName()»;
				«IF type.isAbstract»
				type.setIsAbstract(true);
				«ENDIF»
				«IF !(type instanceof AnyType)»
					«type.emitSuperClasses("type")»
				«ENDIF»
				«IF templateSignature !== null»«IF templateSignature.getOwnedParameters().size() > 0»
					createTemplateSignature(type, «FOR templateParameter : templateSignature.getOwnedParameters() SEPARATOR(", ")»«templateParameter.getSymbolName()»«ENDFOR»);
				«ENDIF»
				«ENDIF»
				ownedClasses.add(type);
				«ENDFOR»
				«ENDFOR»
			}
		'''
	}

	protected def String defineCollectionTypes(/*@NonNull*/ Model root) {
		// FIXME Probably need to interleave all specialized types in reverse dependency order
		var pkge2collectionTypes = root.getSortedCollectionTypes();
		if (pkge2collectionTypes.isEmpty()) return "";
		var sortedPackages = root.getSortedPackages(pkge2collectionTypes.keySet());
		'''

			private void installCollectionTypes() {
				List<Class> ownedClasses;
				List<Class> superClasses;
				CollectionType type;
				«FOR pkge : sortedPackages»

					ownedClasses = «pkge.getSymbolName()».getOwnedClasses();
					«FOR type : ClassUtil.nullFree(pkge2collectionTypes.get(pkge))»
						type = «type.getSymbolName()»;
						«IF type.isAbstract»
						type.setIsAbstract(true);
						«ENDIF»
						«IF type.lower.intValue() != 0»
						type.setLower(«type.lower.intValue()»);
						«ENDIF»
						«IF !(type.upper instanceof Unlimited)»
						type.setUpper(«type.upper.intValue()»);
						«ENDIF»
						«IF type.isNullFree»
						type.setIsNullFree(true);
						«ENDIF»
						«type.emitSuperClasses("type")»
						ownedClasses.add(type);
					«ENDFOR»
				«ENDFOR»
			}
		'''
	}

	protected def String defineComments(/*@NonNull*/ Model root) {
		'''

			private void installComments() {
				«FOR pElement : root.getSortedCommentedElements()»
				«FOR pComment : pElement.getSortedComments()»
					installComment(«pElement.getSymbolName()», "«pComment.javaString()»");
				«ENDFOR»
				«ENDFOR»
			}
		'''
	}

	protected def String defineEnumerations(/*@NonNull*/ Model root) {
		var pkge2enumerations = root.getSortedEnumerations();
		if (pkge2enumerations.isEmpty()) return "";
		var sortedPackages = root.getSortedPackages(pkge2enumerations.keySet());
		'''

			private void installEnumerations() {
				List<Class> ownedClasses;
				Enumeration type;
				List<EnumerationLiteral> enumerationLiterals;
				«FOR pkge : sortedPackages»

					ownedClasses = «pkge.getSymbolName()».getOwnedClasses();
					«FOR enumeration : ClassUtil.nullFree(pkge2enumerations.get(pkge))»
						type = «enumeration.getSymbolName()»;
						enumerationLiterals = type.getOwnedLiterals();
						«FOR enumerationLiteral : enumeration.ownedLiterals»
							enumerationLiterals.add(«enumerationLiteral.getSymbolName()»);
						«ENDFOR»
						type.getSuperClasses().add(_OclEnumeration);
						ownedClasses.add(type);
					«ENDFOR»
				«ENDFOR»
			}
		'''
	}

	protected def String defineExternals(/*@NonNull*/ Model root) {
		var externals = root.getSortedExternals();
		if (externals.isEmpty()) return "";
		'''

			«FOR name : externals»«var element = ClassUtil.nonNullState(name2external.get(name))»
			«IF element instanceof Package»
			private final @NonNull Package «getPrefixedSymbolName(element, name)» = «element.getExternalReference()»;
			«ELSEIF element instanceof PrimitiveType»
			private final @NonNull Class «getPrefixedSymbolName(element, name)» = «element.getExternalReference()»;
			«ELSE»
			private final @NonNull «element.eClass().getName()» «getPrefixedSymbolName(element, name)» = «element.getExternalReference()»;
			«ENDIF»
			«ENDFOR»
		'''
	}

	protected def String defineIterations(/*@NonNull*/ Model root) {
		var pkge2iterations = root.getSortedIterations();
		if (pkge2iterations.isEmpty()) return "";
		var sortedPackages = root.getSortedPackages(pkge2iterations.keySet());
		var Class oldType  = null;
		'''

			«FOR pkge : sortedPackages»
				«FOR iteration : ClassUtil.nullFree(pkge2iterations.get(pkge))»
				private final @NonNull Iteration «iteration.getPrefixedSymbolName("it_" + iteration.partialName())» = createIteration("«iteration.
				name»", «iteration.type.getSymbolName()», «IF iteration.implementationClass !== null»"«iteration.
				implementationClass»", «iteration.implementationClass».INSTANCE«ELSE»null, null«ENDIF»«IF iteration.getOwnedSignature() !== null»«FOR templateParameter : iteration.getOwnedSignature().getOwnedParameters()», «templateParameter.getSymbolName()»«ENDFOR»«ENDIF»);
				«ENDFOR»
			«ENDFOR»

			private void installIterations() {
				List<Operation> ownedIterations;
				List<Parameter> ownedParameters;
				Iteration iteration;
				Parameter parameter;
				«FOR pkge : sortedPackages»
					«FOR iteration : ClassUtil.nullFree(pkge2iterations.get(pkge))»«var newType = iteration.getOwningClass()»
					«IF newType != oldType»

						ownedIterations = «(oldType = newType).getSymbolName()».getOwnedOperations();
					«ENDIF»
					ownedIterations.add(iteration = «iteration.getSymbolName()»);
					«IF iteration.isInvalidating»
						iteration.setIsInvalidating(true);
					«ENDIF»
					«IF !iteration.isRequired»
						iteration.setIsRequired(false);
					«ENDIF»
					«IF iteration.isStatic»
						iteration.setIsStatic(true);
					«ENDIF»
					«IF iteration.isTypeof»
						iteration.setIsTypeof(true);
					«ENDIF»
					«IF iteration.isValidating»
						iteration.setIsValidating(true);
					«ENDIF»
					«IF iteration.ownedIterators.size() > 0»
						ownedParameters = iteration.getOwnedIterators();
						«FOR parameter : iteration.ownedIterators»
							ownedParameters.add(parameter = createParameter("«parameter.name»", «parameter.type.getSymbolName()», «parameter.isRequired»));
							«IF parameter.isTypeof»
								parameter.setIsTypeof(true);
							«ENDIF»
						«ENDFOR»
					«ENDIF»
					«IF iteration.ownedAccumulators.size() > 0»
						ownedParameters = iteration.getOwnedAccumulators();
						«FOR parameter : iteration.ownedAccumulators»
							ownedParameters.add(parameter = createParameter("«parameter.name»", «parameter.type.getSymbolName()», «parameter.isRequired»));
							«IF parameter.isTypeof»
								parameter.setIsTypeof(true);
							«ENDIF»
						«ENDFOR»
					«ENDIF»
					«IF iteration.ownedParameters.size() > 0»
						ownedParameters = iteration.getOwnedParameters();
						«FOR parameter : iteration.ownedParameters»
							ownedParameters.add(parameter = createParameter("«parameter.name»", «parameter.type.getSymbolName()», «parameter.isRequired»));
							«IF parameter.isTypeof»
								parameter.setIsTypeof(true);
							«ENDIF»
						«ENDFOR»
					«ENDIF»
					«ENDFOR»
				«ENDFOR»
			}
		'''
	}

	protected def String defineLambdaTypes(/*@NonNull*/ Model root) {
		var allLambdaTypes = root.getSortedLambdaTypes();
		if (allLambdaTypes.isEmpty()) return "";
		var orphanPackage = root.getOrphanPackage();
		if (orphanPackage === null) return "";
		'''

			«FOR type : allLambdaTypes»
				private final @NonNull LambdaType «type.getPrefixedSymbolName("_" + type.partialName())» = createLambdaType("«type.
				name»");
			«ENDFOR»
			
			private void installLambdaTypes() {
				final List<Class> orphanTypes = «ClassUtil.nonNullState(orphanPackage).getSymbolName()».getOwnedClasses();
				LambdaType type;
				List<Class> superClasses;
				«FOR type : allLambdaTypes»
					orphanTypes.add(type = «type.getSymbolName()»);
					type.setContextType(«type.contextType.getSymbolName()»);
					«FOR parameterType : type.parameterType»
						type.getParameterType().add(«parameterType.getSymbolName()»);
					«ENDFOR»
					type.setResultType(«type.resultType.getSymbolName()»);
					«type.emitSuperClasses("type")»
				«ENDFOR»
			}
		'''
	}

	protected def String defineMapTypes(/*@NonNull*/ Model root) {
		var pkge2mapTypes = root.getSortedMapTypes();
		if (pkge2mapTypes.isEmpty()) return "";
		var sortedPackages = root.getSortedPackages(pkge2mapTypes.keySet());
		'''

			private void installMapTypes() {
				List<Class> ownedClasses;
				List<Class> superClasses;
				MapType type;
				«FOR pkge : sortedPackages»

					ownedClasses = «pkge.getSymbolName()».getOwnedClasses();
					«FOR type : ClassUtil.nullFree(pkge2mapTypes.get(pkge))»
						type = «type.getSymbolName()»;
						«IF !type.keysAreNullFree»
						type.setKeysAreNullFree(false);
						«ENDIF»
						«IF !type.valuesAreNullFree»
						type.setValuesAreNullFree(false);
						«ENDIF»
						«type.emitSuperClasses("type")»
						ownedClasses.add(type);
					«ENDFOR»
				«ENDFOR»
			}
		'''
	}

	protected def String defineOperations(/*@NonNull*/ Model root) {
		var pkge2operations = root.getSortedOperations();
		if (pkge2operations.isEmpty()) return "";
		var sortedPackages = root.getSortedPackages(pkge2operations.keySet());
		var Class oldType  = null;
		'''

			«FOR pkge : sortedPackages»
				«FOR operation : ClassUtil.nullFree(pkge2operations.get(pkge))»
				private final @NonNull Operation «operation.getPrefixedSymbolName("op_" + operation.partialName())» = createOperation(«operation.
				getNameLiteral()», «operation.type.getSymbolName()», «IF operation.implementationClass !== null»"«operation.
				implementationClass»", «operation.implementationClass».INSTANCE«ELSE»null, null«ENDIF»«IF operation.getOwnedSignature() !== null»«FOR templateParameter : operation.getOwnedSignature().getOwnedParameters()», «templateParameter.getSymbolName()»«ENDFOR»«ENDIF»);
				«ENDFOR»
			«ENDFOR»

			private void installOperations() {
				List<Operation> ownedOperations;
				List<Parameter> ownedParameters;
				Operation operation;
				Parameter parameter;
				«FOR pkge : sortedPackages»
					«FOR operation : ClassUtil.nullFree(pkge2operations.get(pkge))»«var newType = operation.getOwningClass()»
					«IF newType != oldType»

						ownedOperations = «(oldType = newType).getSymbolName()».getOwnedOperations();
					«ENDIF»
					ownedOperations.add(operation = «operation.getSymbolName()»);
					«IF operation.isInvalidating»
						operation.setIsInvalidating(true);
					«ENDIF»
					«IF !operation.isRequired»
						operation.setIsRequired(false);
					«ENDIF»
					«IF operation.isStatic»
						operation.setIsStatic(true);
					«ENDIF»
					«IF operation.isTypeof»
						operation.setIsTypeof(true);
					«ENDIF»
					«IF operation.isValidating»
						operation.setIsValidating(true);
					«ENDIF»
					«IF operation.bodyExpression !== null»
						createBodyExpression(operation, «operation.owningClass.getSymbolName()», "«operation.bodyExpression.javaString()»", «operation.type.getSymbolName()»);
					«ENDIF»
					«IF operation.ownedParameters.size() > 0»
						ownedParameters = operation.getOwnedParameters();
						«FOR parameter : operation.ownedParameters»
							ownedParameters.add(parameter = createParameter("«parameter.name»", «parameter.type.getSymbolName()», «parameter.isRequired»));
							«IF parameter.isTypeof»
								parameter.setIsTypeof(true);
							«ENDIF»
						«ENDFOR»
					«ENDIF»
					«ENDFOR»
				«ENDFOR»
			}
		'''
	}

	protected def String definePackages(/*@NonNull*/ Model root) {
		var allPackages = root.getSortedPackages();
		var import2alias = root.getSortedImports();
		var importKeys = new ArrayList<Package>(import2alias.keySet());
		Collections.sort(importKeys, NameUtil.NAMEABLE_COMPARATOR);
		if (allPackages.isEmpty()) return "";
		'''

			private void installPackages() {
				«emitRoot(root)»
				«IF allPackages.size() > 0»
				«FOR pkg2 : allPackages»
				«emitPackage(pkg2)»
				«ENDFOR»
				«ENDIF»
			«FOR importKey : importKeys»«val importName = import2alias.get(importKey)»
				«root.getSymbolName()».getOwnedImports().add(createImport(«IF importName !== null»"«importName»"«ELSE»null«ENDIF», «importKey.getSymbolName()»));
			«ENDFOR»
			}
		'''
	}

	protected def String definePrecedences(Model root) {
		var allLibraries = root.getSortedLibrariesWithPrecedence();
		var allOperations = root.getSortedOperationsWithPrecedence();
		'''
			«IF (allLibraries.size() > 0) || (allOperations.size() > 0)»

				private void installPrecedences() {
					«IF allLibraries.size() > 0»
						List<Precedence> ownedPrecedences;

						«FOR lib : allLibraries»
						«var allPrecedences = lib.getSortedPrecedences()»
						«IF (allPrecedences !== null) && (allPrecedences.size() > 0)»
							«FOR precedence : allPrecedences»
								final Precedence «precedence.getPrefixedSymbolName("prec_" + precedence.partialName())» = createPrecedence("«precedence.name»", AssociativityKind.«precedence.associativity.toString().toUpperCase()»);
							«ENDFOR»

							ownedPrecedences = «lib.getSymbolName()».getOwnedPrecedences();
							«FOR precedence : lib.ownedPrecedences»
								ownedPrecedences.add(«precedence.getSymbolName()»);
							«ENDFOR»
						«ENDIF»
						«ENDFOR»
					«ENDIF»

					«FOR operation : allOperations»
						«operation.getSymbolName()».setPrecedence(«operation.precedence.getSymbolName()»);
					«ENDFOR»
				}
			«ENDIF»
		'''
	}

	protected def String definePrimitiveTypes(/*@NonNull*/ Model root) {
		var pkge2primitiveTypes = root.getSortedPrimitiveTypes();
		if (pkge2primitiveTypes.isEmpty()) return "";
		var sortedPackages = root.getSortedPackages(pkge2primitiveTypes.keySet());
		'''

			private void installPrimitiveTypes() {
				List<Class> ownedClasses;
				PrimitiveType type;
				«FOR pkge : sortedPackages»

					ownedClasses = «pkge.getSymbolName()».getOwnedClasses();
					«FOR type : ClassUtil.nullFree(pkge2primitiveTypes.get(pkge))»
						«var superClasses = type.getSuperclassesInPackage()»
						type = «type.getSymbolNameWithoutNormalization()»;
						«FOR superClass : superClasses»
							type.getSuperClasses().add(«superClass.getSymbolName()»);
						«ENDFOR»
						ownedClasses.add(type);
					«ENDFOR»
				«ENDFOR»
			}
		'''
	}

	protected def String defineProperties(/*@NonNull*/ Model root) {
		var pkge2properties = root.getSortedProperties();
		if (pkge2properties.isEmpty()) return "";
		var sortedPackages = root.getSortedPackages(pkge2properties.keySet());
		var Class oldType  = null;
		'''

			«FOR pkge : sortedPackages»

				«FOR property : ClassUtil.nullFree(pkge2properties.get(pkge))»
				private final @NonNull Property «property.getPrefixedSymbolName("pr_" + property.partialName())» = createProperty(«property.getNameLiteral()», «property.type.getSymbolName()»);
				«ENDFOR»
			«ENDFOR»

			private void installProperties() {
				List<Property> ownedProperties;
				Property property;
				«FOR pkge : sortedPackages»
					«FOR property : ClassUtil.nullFree(pkge2properties.get(pkge))»«var newType = property.getOwningClass()»
					«IF newType != oldType»

						ownedProperties = «(oldType = newType).getSymbolName()».getOwnedProperties();
					«ENDIF»
					ownedProperties.add(property = «property.getSymbolName()»);
					«IF property.isComposite»
						property.setIsComposite(true);
					«ENDIF»
					«IF property.isDerived»
						property.setIsDerived(true);
					«ENDIF»
					«IF property.isID»
						property.setIsID(true);
					«ENDIF»
					«IF property.isImplicit»
						property.setIsImplicit(true);
					«ENDIF»
					«IF property.isReadOnly»
						property.setIsReadOnly(true);
					«ENDIF»
					«IF !property.isRequired»
						property.setIsRequired(false);
					«ENDIF»
					«IF property.isResolveProxies»
						property.setIsResolveProxies(true);
					«ENDIF»
					«IF property.isStatic»
						property.setIsStatic(true);
					«ENDIF»
					«IF property.isTransient»
						property.setIsTransient(true);
					«ENDIF»
					«IF false /*property.isTypeof*/»
						property.setIsTypeof(true);
					«ENDIF»
					«IF property.isUnsettable»
						property.setIsUnsettable(true);
					«ENDIF»
					«IF property.isVolatile»
						property.setIsVolatile(true);
					«ENDIF»
					«IF property.defaultValueString !== null»
						property.setDefaultValueString("«property.defaultValueString»");
					«ENDIF»
					«IF property.opposite !== null»
						property.setOpposite(«property.opposite.getSymbolName()»);
					«ENDIF»
					«IF property.implementationClass !== null»
						property.setImplementationClass("«property.implementationClass»");
						property.setImplementation(«property.implementationClass».INSTANCE);
					«ENDIF»
					«ENDFOR»
				«ENDFOR»
			}
		'''
	}

	protected def String defineTemplateBindings(/*@NonNull*/ Model root) {
		var allTemplateableElements = root.getSortedTemplateableElements(symbolNameComparator);
		if (allTemplateableElements.isEmpty()) return "";
		'''

			private void installTemplateBindings() {
				«FOR templateableElement : allTemplateableElements»
					«FOR templateBinding : templateableElement.ownedBindings»
						«FOR templateParameterSubstitution : templateBinding.ownedSubstitutions»
							addBinding(«templateableElement.getSymbolName()», «templateParameterSubstitution.actual.getSymbolName()»);
						«ENDFOR»
					«ENDFOR»
				«ENDFOR»
			}
		'''
	}

	protected def String defineTemplateParameters(/*@NonNull*/ Model root) {
		var allTemplateParameters = root.getSortedTemplateParameters();
		if (allTemplateParameters.isEmpty()) return "";
		'''

			«FOR templateParameter : allTemplateParameters»
			private final @NonNull TemplateParameter «templateParameter.getPrefixedSymbolName(
						"tp_" + templateParameter.partialName())» = createTemplateParameter("«templateParameter.getName()»");
			«ENDFOR»
		'''
	}

	protected def String defineTupleTypes(/*@NonNull*/ Model root) {
		var allTupleTypes = root.getSortedTupleTypes();
		if (allTupleTypes.isEmpty()) return "";
		var orphanPackage = root.getOrphanPackage();
		if (orphanPackage === null) return "";
		'''

			private void installTupleTypes() {
				final List<Class> orphanTypes = «ClassUtil.nonNullState(orphanPackage).getSymbolName()».getOwnedClasses();
				TupleType type;
				List<Class> superClasses;
				«FOR type : allTupleTypes»
					orphanTypes.add(type = «type.getSymbolName()»);
					«FOR property : type.getSortedProperties()»
						«IF property.implementationClass !== null»
							«property.getSymbolName()».setImplementationClass("«property.implementationClass»");
							«property.getSymbolName()».setImplementation(«property.implementationClass».INSTANCE);
						«ENDIF»
					«ENDFOR»
					«type.emitSuperClasses("type")»
				«ENDFOR»
			}
		'''
	}

	protected def String emitCreateProperty(Property property) {
		return "createProperty(" + property.name + ", " + property.type.getSymbolName() + ")";
	}

	protected def String emitPackage(Package pkg) {
		'''
			«FOR nestedPackage : pkg.getSortedPackages()»
				«IF nestedPackage.getOwnedPackages().size() > 0»
					«emitPackage(nestedPackage)»
				«ENDIF»
				«pkg.getSymbolName()».getOwnedPackages().add(«nestedPackage.getSymbolName()»);
			«ENDFOR»
		'''
	}

	protected def String emitRoot(Model pkg) {
		'''
			«FOR nestedPackage : pkg.getSortedPackages()»
				«IF nestedPackage.getOwnedPackages().size() > 0»
					«emitPackage(nestedPackage)»
				«ENDIF»
				«pkg.getSymbolName()».getOwnedPackages().add(«nestedPackage.getSymbolName()»);
			«ENDFOR»
		'''
	}

	protected def String emitSuperClasses(Class type, String typeName) {
		var superClasses = type.getSuperclassesInPackage();
		'''
			«IF superClasses.size() > 0»
				superClasses = «typeName».getSuperClasses();
				«FOR superClass : superClasses»
					superClasses.add(«superClass.getSymbolName()»);
				«ENDFOR»
			«ELSEIF (type instanceof MapType)»
				superClasses = «typeName».getSuperClasses();
				superClasses.add(_OclAny);
			«ELSEIF (type instanceof AnyType)»
			«ELSEIF TypeId.OCL_ELEMENT_NAME.equals(type.getName())»
			«ELSEIF PivotConstants.ORPHANAGE_NAME.equals(type.getName())»
			«ELSE»
				superClasses = «typeName».getSuperClasses();
				superClasses.add(_OclElement);
			«ENDIF»
		'''
	}

	protected def String installClassTypes(/*@NonNull*/ Model root) {
		var pkge2classTypes = root.getSortedClassTypes();
		if (pkge2classTypes.isEmpty()) return "";
		'''installClassTypes();'''
	}

	protected def String installCoercions(/*@NonNull*/ Model root) {
		var allCoercions = root.getSortedCoercions();
		if (allCoercions.isEmpty()) return "";
		'''installCoercions();'''
	}

	protected def String installCollectionTypes(/*@NonNull*/ Model root) {
		var pkge2collectionTypes = root.getSortedCollectionTypes();
		if (pkge2collectionTypes.isEmpty()) return "";
		'''installCollectionTypes();'''
	}

	protected def String installComments(/*@NonNull*/ Model root) {
		'''installComments();'''
	}

	protected def String installEnumerations(/*@NonNull*/ Model root) {
		var pkge2enumerations = root.getSortedEnumerations();
		if (pkge2enumerations.isEmpty()) return "";
		'''installEnumerations();'''
	}

	protected def String installIterations(/*@NonNull*/ Model root) {
		var pkge2iterations = root.getSortedIterations();
		if (pkge2iterations.isEmpty()) return "";
		'''installIterations();'''
	}

	protected def String installLambdaTypes(/*@NonNull*/ Model root) {
		var allLambdaTypes = root.getSortedLambdaTypes();
		if (allLambdaTypes.isEmpty()) return "";
		'''installLambdaTypes();'''
	}

	protected def String installMapTypes(/*@NonNull*/ Model root) {
		var pkge2mapTypes = root.getSortedMapTypes();
		if (pkge2mapTypes.isEmpty()) return "";
		'''installMapTypes();'''
	}

	protected def String installOperations(/*@NonNull*/ Model root) {
		var pkge2operations = root.getSortedOperations();
		if (pkge2operations.isEmpty()) return "";
		'''installOperations();'''
	}

	protected def String installPackages(/*@NonNull*/ Model root) {
		var allPackages = root.getSortedPackages();
		if (allPackages.isEmpty()) return "";
		'''installPackages();'''
	}

	protected def String installPrecedences(/*@NonNull*/ Model root) {
		var allLibraries = root.getSortedLibrariesWithPrecedence();
		var allOperations = root.getSortedOperationsWithPrecedence();
		if (allLibraries.isEmpty() && allOperations.isEmpty()) return "";
		'''installPrecedences();'''
	}

	protected def String installPrimitiveTypes(/*@NonNull*/ Model root) {
		var pkge2primitiveTypes = root.getSortedPrimitiveTypes();
		if (pkge2primitiveTypes.isEmpty()) return "";
		'''installPrimitiveTypes();'''
	}

	protected def String installProperties(/*@NonNull*/ Model root) {
		var pkge2properties = root.getSortedProperties();
		if (pkge2properties.isEmpty()) return "";
		'''installProperties();'''
	}
	
	protected def String installTemplateBindings(/*@NonNull*/ Model root) {
		var allTemplateableElements = root.getSortedTemplateableElements();
		if (allTemplateableElements.isEmpty()) return "";
		'''installTemplateBindings();'''
	}

	protected def String installTupleTypes(/*@NonNull*/ Model root) {
		var allTupleTypes = root.getSortedTupleTypes();
		if (allTupleTypes.size() <= 0) return "";
		'''installTupleTypes();'''
	}

	/**
	 * Generate a name for element suitable for embedding in a surrounding punctuation context.
	 */
	protected override String partialName(EObject element) {
		switch element {
			CollectionType case element.elementType === null: return element.javaName()
			CollectionType: return element.javaName()
			LambdaType case element.contextType === null: return "null"
			LambdaType: return element.javaName() + "_" + element.contextType.partialName()
			MapType case element.keyType === null: return element.javaName()
			MapType case element.valueType === null: return element.javaName()
			MapType: return element.javaName()
			Class case element.ownedBindings.size() > 0: return '''«element.javaName()»«FOR TemplateParameterSubstitution tps : element.getTemplateParameterSubstitutions()»_«tps.actual.simpleName()»«ENDFOR»'''
			Class: return element.javaName()
			Comment case element.body === null: return "null"
			Comment: return element.javaName(element.body.substring(0, Math.min(11, element.body.length() - 1)))
			EnumerationLiteral case element.owningEnumeration === null: return "null"
			EnumerationLiteral: return element.owningEnumeration.partialName() + "_" + element.javaName()
			Operation case element.owningClass === null: return "null_" + element.javaName()
			Operation: return element.owningClass.partialName() + "_" + element.javaName()
			Package: return element.javaName()
			Parameter case element.eContainer() === null: return "null_" + element.javaName()
			Parameter: return element.eContainer().partialName() + "_" + element.javaName()
			Precedence: return element.javaName()
			Property: return getPartialName(element)
			TemplateBinding case element.getTemplateSignature().owningElement === null: return "null"
			TemplateBinding: return element.owningElement.partialName()
			TemplateParameter case element.getOwningSignature.owningElement === null: return "[" + element.getOwningSignature.partialName() + "]"
//			TemplateParameter case element.getOwningTemplateSignature.owningTemplateableElement.getUnspecializedElement() == null: return element.javaName()
			TemplateParameter: return element.getOwningSignature.owningElement.partialName() + "_" + element.javaName()
			TemplateParameterSubstitution case element.owningBinding === null: return "null"
			TemplateParameterSubstitution case element.owningBinding.owningElement === null: return "null"
			TemplateParameterSubstitution: return element.owningBinding.owningElement.partialName()
			TemplateSignature case element.owningElement === null: return "null"
			TemplateSignature: return element.owningElement.partialName()
			default: return "xyzzy" + element.eClass().name
		}		
	}

	protected def String simpleName(EObject element) {
		switch element {
			TemplateParameter case element.getOwningSignature.owningElement === null: return "null"
			TemplateParameter: return element.getOwningSignature.owningElement.simpleName() + "_" + element.javaName()
			TemplateParameterSubstitution case element.owningBinding === null: return "null"
			TemplateParameterSubstitution case element.owningBinding.owningElement === null: return "null"
			TemplateParameterSubstitution: return element.owningBinding.owningElement.simpleName()
			Class: return element.javaName()
			Operation case element.owningClass === null: return "null_" + element.javaName()
			Operation: return element.owningClass.simpleName() + "_" + element.javaName()
			default: return "xyzzy" + element.eClass().name
		}		
	}
}
