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
import org.eclipse.ocl.pivot.InvalidType
import org.eclipse.ocl.pivot.SelfType
import org.eclipse.ocl.pivot.VoidType
import org.eclipse.ocl.pivot.PivotPackage
import org.eclipse.ocl.pivot.Iteration
import org.eclipse.ocl.pivot.Element
import org.eclipse.ocl.pivot.Type
import org.eclipse.ocl.pivot.Enumeration
import java.util.List
import org.eclipse.ocl.examples.codegen.oclinecore.SynthesisSchedule.Slot
import org.eclipse.ocl.pivot.Class
import org.eclipse.jdt.annotation.NonNull
import org.eclipse.ocl.pivot.NamedElement

abstract class GenerateOCLCommonXtend extends GenerateOCLCommon
{
	protected def String declareClass_name(org.eclipse.ocl.pivot./*@NonNull*/ Class type) {
		'''
		«IF !excludedEClassifierNames.contains(type.name)»
		private Class «type.getPrefixedSymbolName("_"+type.partialName())»;
		«ELSE»
		private Class «type.getPrefixedSymbolNameWithoutNormalization("_"+type.partialName())»;
		«ENDIF»
		'''
	}

	protected def String declareCollectionType_name(/*@NonNull*/ CollectionType type) {
		'''
		private CollectionType «type.getPrefixedSymbolName("_"+type.partialName())»;
		'''
	}

	protected def String declareDataType_name(/*@NonNull*/ DataType type) {
		'''
		private DataType «type.getPrefixedSymbolName("_"+type.partialName())»;
		'''
	}

	protected def String declareEnumeration_name(/*@NonNull*/ Enumeration enumeration) {
		'''
		private Enumeration «enumeration.getPrefixedSymbolName("_" + enumeration.partialName())»;
		'''
	}

	protected def String declareEnumerationLiteral_name(/*@NonNull*/ EnumerationLiteral enumerationLiteral) {
		'''
		private EnumerationLiteral «enumerationLiteral.getPrefixedSymbolName("_" + enumerationLiteral.partialName())»;
		'''
	}

	protected def String declareIteration_name(/*@NonNull*/ Iteration iteration) {
		'''
		private Iteration «iteration.getPrefixedSymbolName("it_" + iteration.partialName())»;
		'''
	}

	protected def String declareIteration_type(/*@NonNull*/ Iteration iteration) {
		'''
		'''
	}

	protected def String declareLambdaType_name(/*@NonNull*/ LambdaType type) {
		'''
		private LambdaType «type.getPrefixedSymbolName("_" + type.partialName())»;
		'''
	}

	protected def String declareMapType_name(/*@NonNull*/ MapType type) {
		'''
		private MapType «type.getPrefixedSymbolName("_" + type.getName() + "_" + type.getKeyType().partialName() + "_" + type.getValueType().partialName())»;
		'''
	}

	protected def String declareOperation_name(/*@NonNull*/ Operation operation) {
		'''
		private Operation «operation.getPrefixedSymbolName("op_" + operation.partialName())»;
		'''
	}

	protected def String declareOperation_type(/*@NonNull*/ Operation operation) {
		'''
		'''
	}

	protected def String declarePrimitiveType_external(/*@NonNull*/ PrimitiveType primitiveType) {
		'''
		private PrimitiveType «primitiveType.getPrefixedSymbolNameWithoutNormalization("_" + primitiveType.partialName())»;
		'''
	}

	protected def String declarePrimitiveType_name(/*@NonNull*/ PrimitiveType primitiveType) {
		'''
		private PrimitiveType «primitiveType.getPrefixedSymbolNameWithoutNormalization("_" + primitiveType.partialName())»;
		'''
	}

	protected def String declareSlot(/*@NonNull*/ Slot slot) {
		var element = slot.getElement();
		var role = slot.getRole();
		if (role == Slot.ROLE_EXTERNAL) {
			switch element {
				CollectionType: return declareCollectionType_name(element)
				org.eclipse.ocl.pivot.Package: return ""
				PrimitiveType: return declarePrimitiveType_external(element)
				TemplateParameter: return declareTemplateParameter_name(element)
				org.eclipse.ocl.pivot.Class: return declareClass_name(element)
			}
		}			
		else if (role == Slot.ROLE_CTOR) {
			switch element {
				CollectionType: return declareCollectionType_name(element)
				Enumeration: return declareEnumeration_name(element)
				EnumerationLiteral: return declareEnumerationLiteral_name(element)
				Iteration: return declareIteration_name(element)
				LambdaType: return declareLambdaType_name(element)
				MapType: return declareMapType_name(element)
				Model: return ""
				org.eclipse.ocl.pivot.Package: return ""
				PrimitiveType: return declarePrimitiveType_name(element)
				TemplateParameter: return declareTemplateParameter_name(element)
				TupleType: return declareTupleType_name(element)
				DataType: return declareDataType_name(element)
				org.eclipse.ocl.pivot.Class: return declareClass_name(element)
				Operation: return declareOperation_name(element)
				Parameter: return ""
				Property: return ""
			}
		}			
		else if (role == Slot.ROLE_TYPE) {
			switch element {
				Iteration: return declareIteration_type(element)
				Operation: return declareOperation_type(element)
				Parameter: return ""
				Property: return ""
			}
		}
		else if (role == Slot.ROLE_ALL_FRAGMENTS) {
			return ""
		}
		else if (role == Slot.ROLE_ALL_OPERATIONS) {
			return ""
		}
		else if (role == Slot.ROLE_ALL_PROPERTIES) {
			return ""
		}
		else if (role == Slot.ROLE_ALL_TYPES) {
			return ""
		}
		else if (role == Slot.ROLE_COMMENTS) {
			return ""
		}
		else if (role == Slot.ROLE_ENUMERATION_LITERALS) {
			return ""
		}
		else if (role == Slot.ROLE_FRAGMENT_OPERATIONS) {
			return ""
		}
		else if (role == Slot.ROLE_FRAGMENT_PROPERTIES) {
			return ""
		}
		else if (role == Slot.ROLE_OPERATIONS) {
			return ""
		}
		else if (role == Slot.ROLE_PARAMETER_LISTS) {
			return ""
		}
		else if (role == Slot.ROLE_PROPERTIES) {
			return ""
		}
		else if (role == Slot.ROLE_SUPER_CLASSES) {
			return ""
		}
		else if (role == Slot.ROLE_TYPE_FRAGMENTS) {
			return ""
		}
		else if (role == Slot.ROLE_TYPE_PARAMETERS) {
			return ""
		}
		return "// declare " + role + " " + element.eClass().getName() + " " + element
	}

	protected def String declareTemplateParameter_name(/*@NonNull*/ TemplateParameter templateParameter) {
		'''
		private TemplateParameter «templateParameter.getPrefixedSymbolName("_" + templateParameter.partialName())»;
		'''
	}

	protected def String declareTupleType_name(/*@NonNull*/ TupleType type) {
		'''
		private TupleType «type.getPrefixedSymbolName("_" + type.partialName())»;
		'''
	}

	protected def String defineAll(/*@NonNull*/ Model root, /*@NonNull*/ Collection</*@NonNull*/ String> excludedEClassifierNames) {
		this.excludedEClassifierNames = excludedEClassifierNames;
		'''
		«root.defineExternals()»
		«root.definePackages()»
		«root.definePrecedences()»
		«root.defineSlots()»
		«root.defineProperties()»
		'''
	}
	
	protected def String defineClass_name(org.eclipse.ocl.pivot./*@NonNull*/ Class type) {
		'''
		«IF excludedEClassifierNames.contains(type.name)»
		«type.getSymbolName()» = createClass(«type.getOwningPackage().getSymbolName()», "«type.name»");
		«ELSE»
		«type.getSymbolName()» = createClass(«type.getOwningPackage().getSymbolName()», «getEcoreLiteral(type)»);
		«ENDIF»
		«IF type.isAbstract»
		«type.getSymbolName()».setIsAbstract(true);
		«ENDIF»
		'''
	}

	protected def String defineClass_superClasses(List<Slot> superClassSlots, org.eclipse.ocl.pivot.Class asClass) {
		'''
		«IF asClass.getOwnedBindings().size() > 0»
		«FOR slot : superClassSlots»
		checkSuperClass(«asClass.getSymbolName()», «slot.getElement().getSymbolName()»);
		«ENDFOR»
		«ELSE»
		«FOR slot : superClassSlots»
		addSuperClass(«asClass.getSymbolName()», «slot.getElement().getSymbolName()»);
		«ENDFOR»
		«ENDIF»
		'''
	}

	protected def String defineCollectionType_name(/*@NonNull*/ CollectionType type) {
		'''
		«IF type.getOwnedSignature() !== null»
		«type.getSymbolName()» = createCollectionType(«type.getOwningPackage().getSymbolName()», «getEcoreLiteral(type)»);
		«ELSE»
		«type.getSymbolName()» = getCollectionType(«type.getGeneric().getSymbolName()», «type.getElementType().getSymbolName()», «IF type.isNullFree»true«ELSE»false«ENDIF», «type.lower.toString()», «IF type.upper.isUnlimited()»-1«ELSE»«type.upper.intValue()»«ENDIF»);
		«ENDIF»
		'''
	}
	protected def String defineDataType_name(org.eclipse.ocl.pivot./*@NonNull*/ DataType type) {
		'''
		«IF excludedEClassifierNames.contains(type.name)»
		«type.getSymbolName()» = createDataType(«type.getOwningPackage().getSymbolName()», "«type.name»");
		«ELSE»
		«type.getSymbolName()» = createDataType(«type.getOwningPackage().getSymbolName()», «getEcoreLiteral(type)»);
		«ENDIF»
		«IF type.getBehavioralClass() !== null»
		«type.getSymbolName()».setBehavioralClass(«type.getBehavioralClass().getSymbolName()»);
		«ENDIF»
		'''
	}


	protected def String defineElement_comments(/*@NonNull*/ Element element) {
		'''
		«FOR comment : getSortedComments(element)»
		installComment(«element.getSymbolName()», "«comment.javaString()»");
		«ENDFOR»
		'''
	}

	protected def String defineEnumeration_name(/*@NonNull*/ Enumeration enumeration) {
		'''
		«enumeration.getSymbolName()» = createEnumeration(«enumeration.getOwningPackage().getSymbolName()», «getEcoreLiteral(enumeration)»);
		'''
	}

	protected def String defineEnumerationLiteral_name(/*@NonNull*/ EnumerationLiteral enumerationLiteral) {
		'''
		«enumerationLiteral.getSymbolName()» = createEnumerationLiteral(«enumerationLiteral.owningEnumeration.getSymbolName()», «getEcoreLiteral(enumerationLiteral)»);
		'''
	}

	protected def String defineExternals(/*@NonNull*/ Model root) {
		return "";
	}

	protected def String defineIteration_name(/*@NonNull*/ Iteration iteration) {
		'''
		«iteration.symbolName» = createIteration(«iteration.getOwningClass().getSymbolName()», "«iteration.name»", «IF iteration.implementationClass !== null»"«iteration.
			implementationClass»", «iteration.implementationClass».INSTANCE«ELSE»null, null«ENDIF»);
		'''
	}

	protected def String defineIteration_type(/*@NonNull*/ Iteration iteration) {
		'''
		«iteration.symbolName».setType(«iteration.type.getSymbolName()»);
		«IF iteration.isInvalidating»
			«iteration.symbolName».setIsInvalidating(true);
		«ENDIF»
		«IF !iteration.isRequired»
			«iteration.symbolName».setIsRequired(false);
		«ENDIF»
		«IF iteration.isStatic»
			«iteration.symbolName».setIsStatic(true);
		«ENDIF»
		«IF iteration.isTypeof»
			«iteration.symbolName».setIsTypeof(true);
		«ENDIF»
		«IF iteration.isValidating»
			«iteration.symbolName».setIsValidating(true);
		«ENDIF»
		«FOR parameter : iteration.ownedIterators»
		createIterator(«iteration.symbolName», "«parameter.name»", «parameter.type.getSymbolName()», «parameter.isRequired»)«IF parameter.isTypeof».setIsTypeof(true)«ENDIF»;
		«ENDFOR»
		«FOR parameter : iteration.ownedAccumulators»
		createAccumulator(«iteration.symbolName», "«parameter.name»", «parameter.type.getSymbolName()», «parameter.isRequired»)«IF parameter.isTypeof»parameter.setIsTypeof(true)«ENDIF»;
		«ENDFOR»
		«FOR parameter : iteration.ownedParameters»
		createParameter(«iteration.symbolName», "«parameter.name»", «parameter.type.getSymbolName()», «parameter.isRequired»)«IF parameter.isTypeof»parameter.setIsTypeof(true)«ENDIF»;
		«ENDFOR»
		'''
	}

	protected def String defineLambdaType_name(/*@NonNull*/ LambdaType type) {
		'''
		«type.getPrefixedSymbolName("_" + type.partialName())» = getLambdaType(_OclLambda, «type.contextType.getSymbolName()», «type.resultType.getSymbolName()»«FOR parameterType : type.parameterType», type.getParameterType().add(«parameterType.getSymbolName()»«ENDFOR»);
		'''
	}

	protected def String defineMapType_name(/*@NonNull*/ MapType type) {
		'''
		«IF type.getOwnedSignature() !== null»
		«type.getSymbolName()» = createMapType(«type.getOwningPackage().getSymbolName()», «getEcoreLiteral(type)»);
		«ELSE»
		«type.getSymbolName()» = getMapType(«type.getGeneric().getSymbolName()», «type.getKeyType().getSymbolName()», «IF type.keysAreNullFree»true«ELSE»false«ENDIF», «type.getValueType().getSymbolName()», «IF type.valuesAreNullFree»true«ELSE»false«ENDIF»);
		«ENDIF»
		'''
	}

	protected def String defineNamedElement_external(/*@NonNull*/ NamedElement asNamedElement) {
		'''
		«asNamedElement.getSymbolName()» = «asNamedElement.getExternalReference()»;
		'''
	}

	protected def String defineOperation_name(/*@NonNull*/ Operation operation) {
		'''
		«operation.getSymbolName()» = createOperation(«operation.getOwningClass().getSymbolName()», «operation.
			getNameLiteral()», «IF operation.implementationClass !== null»"«operation.
			implementationClass»", «operation.implementationClass».INSTANCE«ELSE»null, null«ENDIF»);
		'''
	}

	protected def String defineOperation_type(/*@NonNull*/ Operation operation) {
		var org.eclipse.ocl.pivot.Class asClass = operation.getOwningClass();
		'''
		«operation.getSymbolName()».setType(«operation.type.getSymbolName()»);
		«IF operation.isInvalidating»
			«operation.getSymbolName()».setIsInvalidating(true);
		«ENDIF»
		«IF !operation.isRequired»
			«operation.getSymbolName()».setIsRequired(false);
		«ENDIF»
		«IF operation.isStatic»
			«operation.getSymbolName()».setIsStatic(true);
		«ENDIF»
		«IF operation.isTypeof»
			«operation.getSymbolName()».setIsTypeof(true);
		«ENDIF»
		«IF operation.isValidating»
			«operation.getSymbolName()».setIsValidating(true);
		«ENDIF»
		«IF operation.precedence !== null»
			«operation.getSymbolName()».setPrecedence(«operation.precedence.getSymbolName()»);
		«ENDIF»
		«IF operation.bodyExpression !== null»
			createBodyExpression(«operation.getSymbolName()», «operation.owningClass.getSymbolName()», "«operation.bodyExpression.javaString()»", «operation.type.getSymbolName()»);
		«ENDIF»
		«FOR parameter : operation.ownedParameters»
		createParameter(«operation.getSymbolName()», "«parameter.name»", «parameter.type.getSymbolName()», «parameter.isRequired»)«IF parameter.isTypeof».setIsTypeof(true)«ENDIF»;
		«ENDFOR»
		«IF (asClass instanceof PrimitiveType) && ((asClass as PrimitiveType).coercions.contains(operation))»
			«asClass.getSymbolName()».getCoercions().add(«operation.getSymbolName()»);
		«ENDIF»
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
				«IF importKeys.size() > 0»
				«FOR importKey : importKeys»«val importName = import2alias.get(importKey)»
				«root.getSymbolName()».getOwnedImports().add(createImport(«IF importName !== null»"«importName»"«ELSE»null«ENDIF», «importKey.getSymbolName()»));
				«ENDFOR»
				«ENDIF»
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

	protected def String definePrimitiveType_name(/*@NonNull*/ PrimitiveType primitiveType) {
		'''
		«primitiveType.getSymbolName()» = createPrimitiveType(«primitiveType.getOwningPackage().getSymbolName()», «getEcoreLiteral(primitiveType)»);
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

	protected def String defineSlot(/*@NonNull*/ Slot slot) {
		var element = slot.getElement();
		var role = slot.getRole();
		if (role == Slot.ROLE_EXTERNAL) {
			switch element {
				NamedElement: return defineNamedElement_external(element)
			}
		}			
		else if (role == Slot.ROLE_CTOR) {
			switch element {
				CollectionType: return defineCollectionType_name(element)
				Enumeration: return defineEnumeration_name(element)
				EnumerationLiteral: return defineEnumerationLiteral_name(element)
				Iteration: return defineIteration_name(element)
				LambdaType: return defineLambdaType_name(element)
				MapType: return defineMapType_name(element)
				Model: return ""
				org.eclipse.ocl.pivot.Package: return ""
				PrimitiveType: return definePrimitiveType_name(element)
				Property: return ""
				TemplateParameter: return defineTemplateParameter_name(element)
				TupleType: return defineTupleType_name(element)
				DataType: return defineDataType_name(element)
				org.eclipse.ocl.pivot.Class: return defineClass_name(element)
				Operation: return defineOperation_name(element)
			}
		}			
		else if (role == Slot.ROLE_TYPE) {
			switch element {
				Iteration: return defineIteration_type(element)
				Operation: return defineOperation_type(element)
				Parameter: return ""
				Property: return ""
			}
		}
		else if (role == Slot.ROLE_SUPER_CLASSES) {
			switch element {
				org.eclipse.ocl.pivot.Class: return defineClass_superClasses(slot.getPredecessors().subList(1, slot.getPredecessors().size()), element)
			}
		}
		else if (role == Slot.ROLE_COMMENTS) {
			switch element {
				Parameter: return ""
				Property: return ""
				default: return defineElement_comments(element)
			}		
		}
		else if (role == Slot.ROLE_ALL_FRAGMENTS) {
			return ""
		}
		else if (role == Slot.ROLE_ALL_OPERATIONS) {
			return ""
		}
		else if (role == Slot.ROLE_ALL_PROPERTIES) {
			return ""
		}
		else if (role == Slot.ROLE_ALL_TYPES) {
			return ""
		}
		else if (role == Slot.ROLE_ENUMERATION_LITERALS) {
			return ""
		}
		else if (role == Slot.ROLE_FRAGMENT_OPERATIONS) {
			return ""
		}
		else if (role == Slot.ROLE_FRAGMENT_PROPERTIES) {
			return ""
		}
		else if (role == Slot.ROLE_OPERATIONS) {
			return ""
		}
		else if (role == Slot.ROLE_PARAMETER_LISTS) {
			return ""
		}
		else if (role == Slot.ROLE_PROPERTIES) {
			return ""
		}
		else if (role == Slot.ROLE_TYPE_FRAGMENTS) {
			return ""
		}
		else if (role == Slot.ROLE_TYPE_PARAMETERS) {
			return ""
		}
		return "// define " + role + " " + element.eClass().getName() + " " + element
	}

	protected def String defineSlots(/*@NonNull*/ Model root) {
		'''

		«FOR slot : contentAnalysis.getSynthesisSchedule.getSlots()»
		«declareSlot(slot)»
		«ENDFOR»
		«FOR stage : contentAnalysis.getSynthesisSchedule.getStages()»
		«IF stage.getSlots().size() > 0»
			
		private void installSlots«stage.getName()»() {
			«FOR slot : stage.getSlots()»
			«defineSlot(slot)»
			«ENDFOR»
		}
		«ENDIF»
		«ENDFOR»
		'''
	}

	protected def String defineTemplateParameter_name(/*@NonNull*/ TemplateParameter templateParameter) {
		'''
		«templateParameter.getSymbolName()» = createTemplateParameter(«templateParameter.getOwningSignature().getOwningElement().getSymbolName()», "«templateParameter.getName()»");
		'''
	}

	protected def String defineTupleType_name(/*@NonNull*/ TupleType type) {
		'''
		«type.getSymbolName()» = getTupleType(_OclTuple,
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

/* 	protected def String emitCreateEnumeration(org.eclipse.ocl.pivot.Enumeration type) {
		return "createEnumeration(" + getEcoreLiteral(type) + ")";
	} */
	
/*	protected def String emitCreateEnumerationLiteral(EnumerationLiteral enumerationLiteral) {
		return "createEnumerationLiteral(" + getEcoreLiteral(enumerationLiteral) + ")";
	} */

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

	protected def String installAll(/*@NonNull*/ Model root) {
		'''
		«thisModel.installPackages()»
		«thisModel.installPrecedences()»
		«thisModel.installSlots()»
		«thisModel.installProperties()»
		'''
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

	protected def String installProperties(/*@NonNull*/ Model root) {
		var pkge2properties = root.getSortedProperties();
		if (pkge2properties.isEmpty()) return "";
		'''installProperties();'''
	}

	protected def String installSlots(/*@NonNull*/ Model root) {
		var stages = contentAnalysis.getSynthesisSchedule.getStages();
		if (stages.isEmpty()) return "";
		'''
		«FOR stage : stages»
		«IF stage.getSlots().size() > 0»
		installSlots«stage.getName()»();
		«ENDIF»
		«ENDFOR»
		'''
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
