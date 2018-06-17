/*******************************************************************************
 * Copyright (c) 2014 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.build.latex

import org.eclipse.ocl.pivot.Namespace
import org.eclipse.ocl.pivot.Element
import org.eclipse.ocl.pivot.Library

 class GenerateLaTeXForLibraryXtend extends GenerateLaTeXForLibrary
{
	/*@NonNull*/ protected override String generateLaTeX(/*@NonNull*/ Library asLibrary) {
		'''
		«emitPrecedences(asLibrary)»
		
		«emitClasses(asLibrary)»
		'''
	}

	protected def emitAssociations(org.eclipse.ocl.pivot.Class asClass) {
		var asAssociations = getSortedAssociations(asClass);
		if (asAssociations.size() > 0) {
		'''

		«emitHeading0a("Associations")»
		«FOR asAssociation : asAssociations»

			«emitEmphasis(prettyPrint(asAssociation, asClass))»
			«emitComment(asAssociation, asClass)»
		«ENDFOR»
		'''
		}
	}

	protected def emitAttributes(org.eclipse.ocl.pivot.Class asClass) {
		var asAttributes = getSortedAttributes(asClass);
		if ( asAttributes.size() > 0) {
		'''

		«emitHeading0a("Attributes")»
		«FOR asAttribute : asAttributes»

			«emitEmphasis(prettyPrint(asAttribute, asClass))»
			«emitComment(asAttribute, asClass)»
		«ENDFOR»
		'''
		}
	}

	protected def emitClasses(/*@NonNull*/ org.eclipse.ocl.pivot.Package asPackage) {
		var asClasses = getSortedClasses(asPackage);
		'''
		«FOR asClass : asClasses»
			
			«emitHeading3(prettyPrint(asClass, asClass), asClass.name)»
			«emitComment(asClass, asClass)»
			«IF asClass.getSuperClasses().size() > 0»
			
			conformsTo «FOR asSuperClass : asClass.getSuperClasses() SEPARATOR ', '»"«prettyPrint(asSuperClass, asSuperClass)»":«asSuperClass.name»«ENDFOR»
			«ENDIF»
			«emitAttributes(asClass)»
			«emitAssociations(asClass)»
			«emitOperations(asClass)»
			«emitIterations(asClass)»
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

	protected def emitIterations(org.eclipse.ocl.pivot.Class asClass) {
		var asIterations = getSortedIterations(asClass);
		if (asIterations.size() > 0) {
		'''

		«emitHeading0a("Iterations")»
		«FOR asIteration : asIterations»

			«emitHeading0b(prettyPrint(asIteration, asClass))»
			«emitComment(asIteration, asClass)»
			«FOR asConstraint : getSortedConstraints(asIteration)»
				«emitBeginDefinition()»
				«prettyPrint(asConstraint, asClass)»
				«emitEndDefinition()»
			«ENDFOR»
		«ENDFOR»
		'''
		}
	}

	protected def emitOperations(org.eclipse.ocl.pivot.Class asClass) {
		var asOperations = getSortedOperations(asClass);
		if (asOperations.size() > 0) {
		'''

		«emitHeading0a("Operations")»
		«FOR asOperation : asOperations»

			«emitHeading0b(prettyPrint(asOperation, asClass) /*+ (asOperation.isInvalidating ? " invalidating" : "") + (asOperation.isValidating ? " validating" : "")*/)»
			«IF asOperation.precedence !== null»
			
				precedence: «emitEmphasis(asOperation.precedence.name)»
			«ENDIF»
			«emitComment(asOperation, asClass)»
			«FOR asConstraint : getSortedPreconditions(asOperation)»
				«emitBeginDefinition()»
				«prettyPrint(asConstraint, asClass)»
				«emitEndDefinition()»
			«ENDFOR»
			«IF asOperation.bodyExpression !== null»
				«emitBeginDefinition()»
				body: «asOperation.bodyExpression.getBody()»
				«emitEndDefinition()»
			«ENDIF»
			«FOR asConstraint : getSortedPostconditions(asOperation)»
				«emitBeginDefinition()»
				«prettyPrint(asConstraint, asClass)»
				«emitEndDefinition()»
			«ENDFOR»
		«ENDFOR»
		'''
		}
	}
	
	protected def emitPrecedences(/*@NonNull*/ Library asLibrary) {
		'''
		«emitHeading3("Precedences", "Precedences")»

		«FOR asPrecedence : getPrecedences(asLibrary) SEPARATOR ' < '»«emitEmphasis(asPrecedence.name)»«ENDFOR»
		'''
	}
}
