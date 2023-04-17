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
import org.eclipse.ocl.pivot.CollectionType
import org.eclipse.ocl.pivot.Comment
import org.eclipse.ocl.pivot.EnumerationLiteral
import org.eclipse.ocl.pivot.LambdaType
import org.eclipse.ocl.pivot.MapType
import org.eclipse.ocl.pivot.Model
import org.eclipse.ocl.pivot.Operation
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
import org.eclipse.ocl.pivot.Library
import org.eclipse.ocl.pivot.TupleType
import org.eclipse.ocl.pivot.DataType

abstract class GenerateOCLCommonXtend extends GenerateOCLCommon
{
	protected def String declareAggregateType(/*@NonNull*/ DataType element) {
		switch element {
			CollectionType: return declareCollectionType(element)
			LambdaType: return declareLambdaType(element)
			MapType: return declareMapType(element)
			TupleType: return declareTupleType(element)
			default: return "/* xyzzy " + element.eClass().name + "*/"
		}		
	}

	protected def String declareCollectionType(/*@NonNull*/ CollectionType type) {
		'''
		private «type.eClass.name» «type.getPrefixedSymbolName("_" + type.getName() + "_" + type.getElementType().partialName() + (if (type.isIsNullFree()) "_NullFree" else "") )»;
		'''
	}

	protected def String declareLambdaType(/*@NonNull*/ LambdaType type) {
		'''
		private LambdaType «type.getPrefixedSymbolName("_" + type.partialName())»;
		'''
	}

	protected def String declareMapType(/*@NonNull*/ MapType type) {
		'''
		private «type.eClass.name» «type.getPrefixedSymbolName("_" + type.getName() + "_" + type.getKeyType().partialName() + "_" + type.getValueType().partialName())»;
		'''
	}

	protected def String declareTupleType(/*@NonNull*/ TupleType type) {
		'''
		private TupleType «type.getPrefixedSymbolName("_" + type.partialName())»;
		'''
	}

	protected def String defineAggregateTypes(/*@NonNull*/ Model root) {
		'''
		«var sortedAggregateTypesPerPass = root.getSortedAggregateTypesPerPass()»«FOR aggregateTypes : sortedAggregateTypesPerPass»«var pass = sortedAggregateTypesPerPass.indexOf(aggregateTypes)»

		«FOR aggregateType : aggregateTypes»
			«declareAggregateType(aggregateType)»
		«ENDFOR»

		private void installAggregateTypes«pass»() {
			Class type;
			
			«FOR aggregateType : aggregateTypes»
			«defineAggregateType(aggregateType)»
			«FOR comment : getSortedComments(aggregateType)»
			installComment(type, "«comment.javaString()»");
			«ENDFOR»
			«ENDFOR»
			
			«FOR aggregateType : aggregateTypes»
			«aggregateType.emitSuperClasses(aggregateType.getSymbolName())»
			«ENDFOR»
		}
		«ENDFOR»
		'''
	}

	protected def String defineAggregateType(/*@NonNull*/ DataType element) {
		switch element {
			CollectionType: return defineCollectionType(element)
			LambdaType: return defineLambdaType(element)
			MapType: return defineMapType(element)
			TupleType: return defineTupleType(element)
			default: return "/* xyzzy " + element.eClass().name + "*/"
		}		
	}

	protected def String defineAll(/*@NonNull*/ Model root, /*@NonNull*/ Collection</*@NonNull*/ String> excludedEClassifierNames) {
		'''
		«root.defineExternals()»
		«root.definePackages()»
		«root.definePrecedences()»
		«root.defineTemplateParameters()»
		«root.defineClassTypes(excludedEClassifierNames)»
		«root.definePrimitiveTypes()»
		«root.defineEnumerations()»
		«root.defineAggregateTypes()»
		«root.defineOperations()»
		«root.defineIterations()»
		«root.defineProperties()»
		'''
	}

	protected def String defineClassTypes(/*@NonNull*/ Model root, /*@NonNull*/ Collection</*@NonNull*/ String> excludedEClassifierNames) {
		var pkge2classTypes = root.getSortedClassTypes();
		if (pkge2classTypes.isEmpty()) return "";
		var sortedPackages = root.getSortedPackages(pkge2classTypes.keySet());
		'''
		«FOR pkge : sortedPackages»

		«FOR type : ClassUtil.nullFree(pkge2classTypes.get(pkge))»
		«IF !excludedEClassifierNames.contains(type.name)»
		private «type.eClass().name» «type.getPrefixedSymbolName("_"+type.partialName())»;
		«ELSE»
		private «type.eClass().name» «type.getPrefixedSymbolNameWithoutNormalization("_"+type.partialName())»;
		«ENDIF»
		«ENDFOR»
		«ENDFOR»

		private void installClassTypes() {
			List<Class> ownedClasses;
			Class type;
			«FOR pkge : sortedPackages»

			ownedClasses = «pkge.getSymbolName()».getOwnedClasses();
			«FOR type : ClassUtil.nullFree(pkge2classTypes.get(pkge))»«var templateSignature = type.getOwnedSignature()»
			«IF excludedEClassifierNames.contains(type.name)»
			ownedClasses.add(type = «type.getSymbolName()» = create«type.eClass().name»(«type.getOwningPackage().getSymbolName()», "«type.name»"));
			«ELSE»
			ownedClasses.add(type = «type.getSymbolName()» = create«type.eClass().name»(«type.getOwningPackage().getSymbolName()», «getEcoreLiteral(type)»));
			«ENDIF»
			«IF type.isAbstract»
			type.setIsAbstract(true);
			«ENDIF»
			«IF templateSignature !== null»«IF templateSignature.getOwnedParameters().size() > 0»
				createTemplateSignature(type, «FOR templateParameter : templateSignature.getOwnedParameters() SEPARATOR(", ")»«templateParameter.getSymbolName()»«ENDFOR»);
			«ENDIF»
			«ENDIF»
			«FOR comment : getSortedComments(type)»
				installComment(type, "«comment.javaString()»");
			«ENDFOR»
			«ENDFOR»
			«ENDFOR»
			«FOR pkge : sortedPackages»

			«FOR type : ClassUtil.nullFree(pkge2classTypes.get(pkge))»
			«IF !(type instanceof AnyType)»
				«type.emitSuperClasses(type.getSymbolName())»
			«ENDIF»
			«ENDFOR»
			«ENDFOR»
		}
		'''
	}

	protected def String defineCollectionType(/*@NonNull*/ CollectionType type) {
		'''
		«IF type.getOwnedSignature() !== null»
		type = «type.getSymbolName()» = create«type.eClass.name»(«type.getOwningPackage().getSymbolName()», «getEcoreLiteral(type)», «type.getOwnedSignature().getOwnedParameters().get(0).getSymbolName()», «IF type.isNullFree»true«ELSE»false«ENDIF», «type.lower.intValue()», «IF !(type.upper instanceof Unlimited)»«type.upper.intValue()»«ELSE»-1«ENDIF»);
		«ELSE»
		type = «type.getSymbolName()» = get«type.eClass.name»(«type.getOwningPackage().getSymbolName()», «type.getUnspecializedElement().getSymbolName()», «type.getElementType().getSymbolName()», «IF type.isNullFree»true«ELSE»false«ENDIF», «type.lower.intValue()», «IF !(type.upper instanceof Unlimited)»«type.upper.intValue()»«ELSE»-1«ENDIF»);
		«ENDIF»
		'''
	}

	protected def String defineEnumerations(/*@NonNull*/ Model root) {
		var pkge2enumerations = root.getSortedEnumerations();
		if (pkge2enumerations.isEmpty()) return "";
		var sortedPackages = root.getSortedPackages(pkge2enumerations.keySet());
		'''
		
		«FOR pkge : sortedPackages»
			«FOR enumeration : ClassUtil.nullFree(pkge2enumerations.get(pkge))»
				«var enumerationName = enumeration.getPrefixedSymbolName("_" + enumeration.partialName())»
				private Enumeration «enumerationName»;
			«ENDFOR»
		«ENDFOR»

		private void installEnumerations() {
			List<Class> ownedClasses;
			Enumeration type;
			List<EnumerationLiteral> enumerationLiterals;
			EnumerationLiteral enumerationLiteral;
			«FOR pkge : sortedPackages»
				ownedClasses = «pkge.getSymbolName()».getOwnedClasses();
				«FOR enumeration : ClassUtil.nullFree(pkge2enumerations.get(pkge))»

					ownedClasses.add(type = «enumeration.getSymbolName()» = «emitCreateEnumeration(enumeration)»);
					enumerationLiterals = type.getOwnedLiterals();
					«FOR enumerationLiteral : enumeration.ownedLiterals»
					enumerationLiterals.add(enumerationLiteral = «emitCreateEnumerationLiteral(enumerationLiteral)»);
					«FOR comment : getSortedComments(enumerationLiteral)»
						installComment(enumerationLiteral, "«comment.javaString()»");
					«ENDFOR»
					«ENDFOR»
					«enumeration.emitSuperClasses(enumeration.getSymbolName())»
					«FOR comment : getSortedComments(enumeration)»
						installComment(type, "«comment.javaString()»");
					«ENDFOR»
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
			«ELSEIF element instanceof Library»
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
		var org.eclipse.ocl.pivot.Class oldType  = null;
		'''

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
					ownedIterations.add(iteration = createIteration("«iteration.name»", «iteration.type.getSymbolName()», «IF iteration.implementationClass !== null»"«iteration.
									implementationClass»", «iteration.implementationClass».INSTANCE«ELSE»null, null«ENDIF»«IF iteration.getOwnedSignature() !== null»«FOR templateParameter : iteration.getOwnedSignature().getOwnedParameters()», «templateParameter.getSymbolName()»«ENDFOR»«ENDIF»));
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
					«FOR comment : getSortedComments(iteration)»
						installComment(iteration, "«comment.javaString()»");
					«ENDFOR»
					«ENDFOR»
				«ENDFOR»
			}
		'''
	}

	protected def String defineLambdaType(/*@NonNull*/ LambdaType type) {
		'''
		type = «type.getPrefixedSymbolName("_" + type.partialName())» = createLambdaType(«type.getOwningPackage().getSymbolName()», "«type.name»", «type.contextType.getSymbolName()», «type.resultType.getSymbolName()»«FOR parameterType : type.parameterType», type.getParameterType().add(«parameterType.getSymbolName()»«ENDFOR»);
		'''
	}

	protected def String defineMapType(/*@NonNull*/ MapType type) {
		'''
		«IF type.getOwnedSignature() !== null»
		type = «type.getSymbolName()» = create«type.eClass.name»(«type.getOwningPackage().getSymbolName()», «getEcoreLiteral(type)», «type.getKeyType().getSymbolName()», «IF type.keysAreNullFree»true«ELSE»false«ENDIF», «type.getValueType().getSymbolName()», «IF type.valuesAreNullFree»true«ELSE»false«ENDIF»);
		«ELSE»
		type = «type.getSymbolName()» = get«type.eClass.name»(«type.getOwningPackage().getSymbolName()», «type.getUnspecializedElement().getSymbolName()», «type.getKeyType().getSymbolName()», «IF type.keysAreNullFree»true«ELSE»false«ENDIF», «type.getValueType().getSymbolName()», «IF type.valuesAreNullFree»true«ELSE»false«ENDIF»);
		«ENDIF»
		'''
	}

	protected def String defineOperations(/*@NonNull*/ Model root) {
		var pkge2operations = root.getSortedOperations();
		if (pkge2operations.isEmpty()) return "";
		var sortedPackages = root.getSortedPackages(pkge2operations.keySet());
		var org.eclipse.ocl.pivot.Class oldType  = null;
		'''

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
					ownedOperations.add(operation = createOperation(«operation.
				getNameLiteral()», «operation.type.getSymbolName()», «IF operation.implementationClass !== null»"«operation.
				implementationClass»", «operation.implementationClass».INSTANCE«ELSE»null, null«ENDIF»«IF operation.getOwnedSignature() !== null»«FOR templateParameter : operation.getOwnedSignature().getOwnedParameters()», «templateParameter.getSymbolName()»«ENDFOR»«ENDIF»));
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
					«IF operation.precedence !== null»
						operation.setPrecedence(«operation.precedence.getSymbolName()»);
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
					«IF (newType instanceof PrimitiveType) && ((newType as PrimitiveType).coercions.contains(operation))»
						«newType.getSymbolName()».getCoercions().add(operation);
					«ENDIF»
					«FOR comment : getSortedComments(operation)»
						installComment(operation, "«comment.javaString()»");
					«ENDFOR»
					«ENDFOR»
				«ENDFOR»
			}
		'''
	}

	protected def String definePackages(/*@NonNull*/ Model root) {
		var allPackages = root.getSortedPackages();
		var import2alias = root.getSortedImports();
		var importKeys = new ArrayList<org.eclipse.ocl.pivot.Package>(import2alias.keySet());
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

				«IF allLibraries.size() > 0»
					«FOR lib : allLibraries»
					«var allPrecedences = lib.getSortedPrecedences()»
					«IF (allPrecedences !== null) && (allPrecedences.size() > 0)»
						«FOR precedence : allPrecedences»
							private Precedence «precedence.getPrefixedSymbolName("prec_" + precedence.partialName())»;
						«ENDFOR»
					«ENDIF»
					«ENDFOR»
				«ENDIF»

				private void installPrecedences() {
					«IF allLibraries.size() > 0»
						List<Precedence> ownedPrecedences;

						«FOR lib : allLibraries»
						«var allPrecedences = lib.getSortedPrecedences()»
						«IF (allPrecedences !== null) && (allPrecedences.size() > 0)»
							ownedPrecedences = «lib.getSymbolName()».getOwnedPrecedences();
							«FOR precedence : lib.ownedPrecedences»
								ownedPrecedences.add(«precedence.getSymbolName()» = createPrecedence("«precedence.name»", AssociativityKind.«precedence.associativity.toString().toUpperCase()»));
							«ENDFOR»
						«ENDIF»
						«ENDFOR»
					«ENDIF»
				}
			«ENDIF»
		'''
	}

	protected def String definePrimitiveTypes(/*@NonNull*/ Model root) {
		var pkge2primitiveTypes = root.getSortedPrimitiveTypes();
		if (pkge2primitiveTypes.isEmpty()) return "";
		var sortedPackages = root.getSortedPackages(pkge2primitiveTypes.keySet());
		'''
			«FOR pkge : sortedPackages»

				«FOR type : ClassUtil.nullFree(pkge2primitiveTypes.get(pkge))»
				private PrimitiveType «type.getPrefixedSymbolNameWithoutNormalization("_" + type.partialName())»;
				«ENDFOR»
			«ENDFOR»

			private void installPrimitiveTypes() {
				List<Class> ownedClasses;
				PrimitiveType type;
				«FOR pkge : sortedPackages»

					ownedClasses = «pkge.getSymbolName()».getOwnedClasses();
					«FOR type : ClassUtil.nullFree(pkge2primitiveTypes.get(pkge))»
						ownedClasses.add(type = «type.getSymbolNameWithoutNormalization()» = createPrimitiveType(«getEcoreLiteral(type)»));
						«FOR comment : getSortedComments(type)»
							installComment(type, "«comment.javaString()»");
						«ENDFOR»
					«ENDFOR»
				«ENDFOR»
				«FOR pkge : sortedPackages»

					«FOR type : ClassUtil.nullFree(pkge2primitiveTypes.get(pkge))»
						«FOR superClass : type.getSuperclassesInPackage()»
							addSuperClass(«type.getSymbolName()», «superClass.getSymbolName()»);
						«ENDFOR»
					«ENDFOR»
				«ENDFOR»
			}
		'''
	}

	protected def String defineProperties(/*@NonNull*/ Model root) {
		var properties = root.getSortedProperties();
		if (properties.isEmpty()) return "";
		var org.eclipse.ocl.pivot.Class oldType  = null;
		'''

			private void installProperties() {
				«FOR property : properties»
				«IF property.getOpposite() !== null»
				Property «property.getPrefixedSymbolName("pr_" + property.partialName())»;
				«ENDIF»
				«ENDFOR»
				List<Property> ownedProperties;
				Property property;
				«FOR property : properties»«var newType = property.getOwningClass()»
				«IF newType != oldType»

					ownedProperties = «(oldType = newType).getSymbolName()».getOwnedProperties();
				«ENDIF»
				ownedProperties.add(property = «IF property.getOpposite() !== null»«property.getSymbolName()» = «ENDIF»createProperty(«property.getNameLiteral()», «property.type.getSymbolName()»));
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
				«IF property.implementationClass !== null»
					property.setImplementationClass("«property.implementationClass»");
					property.setImplementation(«property.implementationClass».INSTANCE);
				«ENDIF»
				«FOR comment : getSortedComments(property)»
					installComment(property, "«comment.javaString()»");
				«ENDFOR»
				«ENDFOR»

				«var sortedOppositeProperties = sortedOpposites(properties)»«FOR property : sortedOppositeProperties»
				setOpposites(«property.getSymbolName()», «property.opposite.getSymbolName()»);
				«ENDFOR»
			}
		'''
	}

	protected def String defineTemplateParameters(/*@NonNull*/ Model root) {
		var allTemplateParameters = root.getSortedTemplateParameters();
		if (allTemplateParameters.isEmpty()) return "";
		'''

			«FOR templateParameter : allTemplateParameters»
			private TemplateParameter «templateParameter.getPrefixedSymbolName(
						"tp_" + templateParameter.partialName())»;
			«ENDFOR»

			private void installTemplateParameters() {
				«FOR templateParameter : allTemplateParameters»
				«templateParameter.getSymbolName()» = createTemplateParameter("«templateParameter.getName()»");
				«ENDFOR»
			}
		'''
	}

	protected def String defineTupleType(/*@NonNull*/ TupleType type) {
		'''
		type = «type.getSymbolName()» = createTupleType(«type.getOwningPackage().getSymbolName()», "«type.name»",
		«FOR property : type.getSortedTupleParts() BEFORE ("\t") SEPARATOR (",\n\t")»
		createProperty("«property.name»", «property.type.getSymbolName()»)«ENDFOR»);
		«FOR property : type.getSortedProperties()»
			«IF property.implementationClass !== null»
				«property.getSymbolName()».setImplementationClass("«property.implementationClass»");
				«property.getSymbolName()».setImplementation(«property.implementationClass».INSTANCE);
			«ENDIF»
		«ENDFOR»
		'''
	}

/* 	protected def String emitCreateEnumeration(org.eclipse.ocl.pivot.Enumeration type) {
		return "createEnumeration(\"" + type.name + "\")";
	}
	
	protected def String emitCreateEnumerationLiteral(EnumerationLiteral enumerationLiteral) {
		return "createEnumerationLiteral(\"" + enumerationLiteral.name + "\")";
	} */

	protected def String emitCreateEnumeration(org.eclipse.ocl.pivot.Enumeration type) {
		return "createEnumeration(" + getEcoreLiteral(type) + ")";
	}
	
	protected def String emitCreateEnumerationLiteral(EnumerationLiteral enumerationLiteral) {
		return "createEnumerationLiteral(" + getEcoreLiteral(enumerationLiteral) + ")";
	}

	protected def String emitCreateProperty(Property property) {
		return "createProperty(" + property.name + ", " + property.type.getSymbolName() + ")";
	}

	protected def String emitPackage(org.eclipse.ocl.pivot.Package pkg) {
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

	protected def String emitSuperClasses(org.eclipse.ocl.pivot.Class type, String typeName) {
		var superClasses = type.getSuperclassesInPackage();
		'''
			«IF superClasses.size() > 0»
				«FOR superClass : superClasses»
					addSuperClass(«typeName», «superClass.getSymbolName()»);
				«ENDFOR»
			«ELSEIF (type instanceof MapType)»
				addSuperClass(«typeName», _OclAny);
			«ELSEIF (type instanceof AnyType)»
			«ELSEIF TypeId.OCL_ELEMENT_NAME.equals(type.getName())»
			«ELSEIF PivotConstants.ORPHANAGE_NAME.equals(type.getName())»
			«ELSE»
				addSuperClass(«typeName», _OclElement);
			«ENDIF»
		'''
	}

	protected def String installAll(/*@NonNull*/ Model root) {
		'''
		«thisModel.installPackages()»
		«thisModel.installPrecedences()»
		«thisModel.installTemplateParameters()»
		«thisModel.installClassTypes()»
		«thisModel.installPrimitiveTypes()»
		«thisModel.installEnumerations()»
		«var sortedAggregateTypesPerPass = root.getSortedAggregateTypesPerPass()»«FOR aggregateTypes : sortedAggregateTypesPerPass»«var pass = sortedAggregateTypesPerPass.indexOf(aggregateTypes)»
		installAggregateTypes«pass»();
		«ENDFOR»
		«thisModel.installOperations()»
		«thisModel.installIterations()»
		«thisModel.installProperties()»
		'''
	}

	protected def String installClassTypes(/*@NonNull*/ Model root) {
		var pkge2classTypes = root.getSortedClassTypes();
		if (pkge2classTypes.isEmpty()) return "";
		'''installClassTypes();'''
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

	protected def String installTemplateParameters(/*@NonNull*/ Model root) {
		var allTemplateParameters = root.getSortedTemplateParameters();
		if (allTemplateParameters.size() <= 0) return "";
		'''installTemplateParameters();'''
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
			org.eclipse.ocl.pivot.Class case element.ownedBindings.size() > 0: return '''«element.javaName()»«FOR TemplateParameterSubstitution tps : element.getTemplateParameterSubstitutions()»_«tps.actual.simpleName()»«ENDFOR»'''
			org.eclipse.ocl.pivot.Class: return element.javaName()
			Comment case element.body === null: return "null"
			Comment: return element.javaName(element.body.substring(0, Math.min(11, element.body.length() - 1)))
			EnumerationLiteral case element.owningEnumeration === null: return "null"
			EnumerationLiteral: return element.owningEnumeration.partialName() + "_" + element.javaName()
			Operation case element.owningClass === null: return "null_" + element.javaName()
			Operation: return element.owningClass.partialName() + "_" + element.javaName()
			org.eclipse.ocl.pivot.Package: return element.javaName()
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
			org.eclipse.ocl.pivot.Class: return element.javaName()
			Operation case element.owningClass === null: return "null_" + element.javaName()
			Operation: return element.owningClass.simpleName() + "_" + element.javaName()
			default: return "xyzzy" + element.eClass().name
		}		
	}
}
