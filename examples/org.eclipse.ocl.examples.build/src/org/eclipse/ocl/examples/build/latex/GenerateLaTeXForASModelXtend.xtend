/*******************************************************************************
 * Copyright (c) 2014 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.build.latex

import org.eclipse.jdt.annotation.NonNull
import org.eclipse.ocl.pivot.Class
import org.eclipse.ocl.pivot.Element
import org.eclipse.ocl.pivot.Namespace
import org.eclipse.ocl.pivot.Package

public class GenerateLaTeXForASModelXtend extends GenerateLaTeXForASModel
{
	@NonNull protected override String generateLaTeX(@NonNull Package asPackage) {
		'''
		«emitClasses(asPackage)»
		'''
	}

	protected def emitAssociations(Class asClass) {
		var asAssociations = getSortedAssociations(asClass);
		if (asAssociations.size() > 0) {
		'''

		«emitHeading0a("Associations")»
		«FOR asAssociation : asAssociations»

			«emitHeading0b(prettyPrint(asAssociation, asClass))»
			«emitComment(asAssociation, asClass)»
		«ENDFOR»
		'''
		}
	}

	protected def emitAttributes(Class asClass) {
		var asAttributes = getSortedAttributes(asClass);
		if (asAttributes.size() > 0) {
		'''

		«emitHeading0a("Attributes")»
		«FOR asAttribute : asAttributes»

			«emitHeading0b(prettyPrint(asAttribute, asClass))»
			«IF asAttribute.getDefaultValueString() != null»
			
			«emitBeginDefinition»
			defaultValueString: «encodeDefinitionText(asAttribute.getDefaultValueString())»
			«emitEndDefinition»
			«ENDIF»
			«IF asAttribute.getDefaultValue() != null»
			
			«emitBeginDefinition»
			defaultValue: «encodeDefinitionText(asAttribute.getDefaultValue().toString())»
			«emitEndDefinition»
			«ENDIF»
			«emitComment(asAttribute, asClass)»
		«ENDFOR»
		'''
		}
	}

	protected def emitClasses(@NonNull Package asPackage) {
		var asClasses = getSortedClasses(asPackage);
		'''
		«FOR asClass : asClasses»
			
			«emitHeading3(prettyPrint(asClass, asClass), asClass.name)»
			«emitComment(asClass, asClass)»
			«IF asClass.getSuperClasses().size() > 0»
			
			conformsTo: «FOR asSuperClass : asClass.getSuperClasses() SEPARATOR ', '»«prettyPrint(asSuperClass, asSuperClass)»«ENDFOR»
			«ENDIF»
			«emitAttributes(asClass)»
			«emitAssociations(asClass)»
			«emitOperations(asClass)»
			«emitInvariants(asClass)»
		«ENDFOR»
		'''
	}

	protected def emitComment(Element asElement, Namespace asNamespace) {
		if (asElement.getOwnedComments().size() > 0) {
		'''
			«FOR asComment : asElement.getOwnedComments()»
			
			«prettyPrint(asComment, asNamespace)»
			«ENDFOR»
		'''
		}
	}

	protected def emitInvariants(Class asClass) {
		var asInvariants = getSortedInvariants(ocl, asClass);
		if (asInvariants.size() > 0) {
		'''

		«emitHeading0a("Well-Formedness Rules")»
		«FOR asInvariant : asInvariants»

			«emitHeading0b(asInvariant.getName())»
			«emitComment(asInvariant, asClass)»
			«emitBeginDefinition»
			«encodeDefinitionText(prettyPrint(asInvariant, asClass))»
			«emitEndDefinition»
		«ENDFOR»
		'''
		}
	}

	protected def emitOperations(Class asClass) {
		var asOperations = getSortedOperations(asClass);
		if (asOperations.size() > 0) {
		'''

		«emitHeading0a("Operations")»
		«FOR asOperation : asOperations»

			«emitHeading0b(prettyPrint(asOperation, asClass) /*+ (asOperation.isInvalidating ? " invalidating" : "") + (asOperation.isValidating ? " validating" : "")*/)»
			«IF asOperation.precedence != null»
			
				precedence: «emitHeading0b(asOperation.precedence.name)»
			«ENDIF»
			«emitComment(asOperation, asClass)»
			«FOR asConstraint : getSortedPreconditions(asOperation)»
				«emitBeginDefinition»
				«encodeDefinitionText(prettyPrint(asConstraint, asClass))»
				«emitEndDefinition»
			«ENDFOR»
			«IF asOperation.bodyExpression != null»
				«emitBeginDefinition»
				body: «encodeDefinitionText(asOperation.bodyExpression.getBody())»
				«emitEndDefinition»
			«ENDIF»
			«FOR asConstraint : getSortedPostconditions(asOperation)»
				«emitBeginDefinition»
				«encodeDefinitionText(prettyPrint(asConstraint, asClass))»
				«emitEndDefinition»
			«ENDFOR»
		«ENDFOR»
		'''
		}
	}
}
