/*******************************************************************************
 * Copyright (c) 2013, 2014 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.build.textile

import org.eclipse.ocl.pivot.Namespace
import org.eclipse.ocl.pivot.Element
import org.eclipse.ocl.pivot.Model

 class GenerateTextileForModelXtend extends GenerateTextileForModelUtils
{
	/*@NonNull*/ protected override String generateTextile(/*@NonNull*/ Model asModel) {
		'''
		«emitPackages(asModel)»
		'''
	}

	protected def emitAssociations(org.eclipse.ocl.pivot.Class asClass) {
		var asAssociations = getSortedAssociations(asClass);
		if (asAssociations.size() > 0) {
		'''

		*Associations*
		«FOR asAssociation : asAssociations»

			@«prettyPrint(asAssociation, asClass)»@
			«emitComment(asAssociation, asClass)»
		«ENDFOR»
		'''
		}
	}

	protected def emitAttributes(org.eclipse.ocl.pivot.Class asClass) {
		var asAttributes = getSortedAttributes(asClass);
		if ( asAttributes.size() > 0) {
		'''

		*Attributes*
		«FOR asAttribute : asAttributes»

			@«prettyPrint(asAttribute, asClass)»@
			«emitComment(asAttribute, asClass)»
		«ENDFOR»
		'''
		}
	}

	protected def emitClasses(org.eclipse.ocl.pivot.Package asPackage) {
		var asClasses = getSortedClasses(asPackage);
		'''
		«FOR asClass : asClasses»
			
			h2(#«asClass.name»). **@«prettyPrint(asClass, asClass)»@**
			«emitComment(asClass, asClass)»
			«IF asClass.getSuperClasses().size() > 0»
			
			conformsTo «FOR asSuperClass : asClass.getSuperClasses() SEPARATOR ', '»"@«prettyPrint(asSuperClass, asSuperClass)»@":#«asSuperClass.name»«ENDFOR»
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

		*Iterations*
		«FOR asIteration : asIterations»

			@«prettyPrint(asIteration, asClass)»@
			«emitComment(asIteration, asClass)»
			«FOR asConstraint : getSortedConstraints(asIteration)»

				bc.. 
				«prettyPrint(asConstraint, asClass)»
				p. 
			«ENDFOR»
		«ENDFOR»
		'''
		}
	}

	protected def emitOperations(org.eclipse.ocl.pivot.Class asClass) {
		var asOperations = getSortedOperations(asClass);
		if (asOperations.size() > 0) {
		'''

		*Operations*
		«FOR asOperation : asOperations»

			@«prettyPrint(asOperation, asClass)»«IF asOperation.isInvalidating» invalidating«ENDIF»«IF asOperation.isValidating» validating«ENDIF»@
			«IF asOperation.precedence !== null»
				precedence: @«asOperation.precedence.name»@
			«ENDIF»
			«emitComment(asOperation, asClass)»
			«FOR asConstraint : getSortedPreconditions(asOperation)»

				bc.. 
				«prettyPrint(asConstraint, asClass)»
				p. 
			«ENDFOR»
			«IF asOperation.bodyExpression !== null»

				bc.. 
				body: «asOperation.bodyExpression.getBody()»
				p. 
			«ENDIF»
			«FOR asConstraint : getSortedPostconditions(asOperation)»

				bc.. 
				«prettyPrint(asConstraint, asClass)»
				p. 
			«ENDFOR»
		«ENDFOR»
		'''
		}
	}

	protected def emitPackages(Model asModel) {
		var asPackages = getSortedPackages(asModel);
		'''
		«FOR asPackage : asPackages»
			
			h1(#«asPackage.name»). **@«prettyPrint(asPackage, asPackage)»@**
			«emitComment(asPackage, asPackage)»
			
			«emitClasses(asPackage)»
		«ENDFOR»
		'''
	}
	
	protected def emitPrecedences(Model asModel) {
		'''
		h2(#Precedences). *Precedences*

		«FOR asPrecedence : getPrecedences(asModel) SEPARATOR ' > '»@«asPrecedence.name»@«ENDFOR»
		'''
	}
}
