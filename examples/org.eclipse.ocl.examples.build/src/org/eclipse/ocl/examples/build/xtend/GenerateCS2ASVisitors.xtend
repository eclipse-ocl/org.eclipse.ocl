/*******************************************************************************
 * Copyright (c) 2013 Willink Transformations Ltd., University of York and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     Adolfo Sanchez-Barbudo Herrera (University of York) - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.build.xtend

import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EPackage
import java.util.List
import java.util.ArrayList
import org.eclipse.ocl.xtext.base.cs2as.Continuation
import org.eclipse.ocl.pivot.Element
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage
import org.eclipse.ocl.xtext.base.cs2as.CS2ASConversion
import java.util.Collections

 class GenerateCS2ASVisitors extends GenerateCSVisitors
{
	override void generateVisitors(/*@NonNull*/ GenPackage genPackage) {
		super.generateVisitors(genPackage);
		if (isDerived()) {
			generateContainmentVisitor(genPackage);
			generatePreOrderVisitor(genPackage);
			generatePostOrderVisitor(genPackage);
			generateLeft2RightVisitor(genPackage);
		}
	}
	
	protected def void generateContainmentVisitor(/*@NonNull*/ GenPackage genPackage) {
		var EPackage csPackage = genPackage.getEcorePackage();
		var String visitorVariant = "Containment";
		var String resultTypeName =  "Continuation<?>";
		var String className = "Abstract" + projectPrefix + visitorVariant + "Visitor";
		var String extendedClass = superProjectPrefix + visitorVariant + "Visitor";
		//		var String extendedClass = if (superVisitorClassName.length() == 0) {
//				"AbstractExtending" + visitableClassName;
//			} else {
//				visitorPrefix + superVisitorClassName;
//			}
		var String interfaceName =  visitorClassName +'<'+resultTypeName+'>';
		var List<Class<?>> additionalImports = new ArrayList();
		additionalImports.add(typeof(Continuation));
		csPackage.generateContextfulAbstractExtendingVisitor(className, extendedClass, 
			interfaceName,  resultTypeName, additionalImports);
	}
	
	protected def void generatePreOrderVisitor(/*@NonNull*/ GenPackage genPackage) {
		var EPackage csPackage = genPackage.getEcorePackage();
		var String visitorVariant = "PreOrder";
		var String resultTypeName =  "Continuation<?>";
		var String className = "Abstract" + projectPrefix + visitorVariant + "Visitor";
		var String extendedClass = superProjectPrefix + visitorVariant + "Visitor";
		var String interfaceName =  visitorClassName +'<'+resultTypeName+'>';
		var List<Class<?>> additionalImports = new ArrayList();
		additionalImports.add(typeof(Continuation));
		csPackage.generateContextfulAbstractExtendingVisitor(className, extendedClass, 
			interfaceName, resultTypeName, additionalImports);
	}
	
	protected def void generatePostOrderVisitor(/*@NonNull*/ GenPackage genPackage) {
		var EPackage csPackage = genPackage.getEcorePackage();
		var String visitorVariant = "PostOrder";
		var String resultTypeName =  "Continuation<?>";
		var String className = "Abstract" + projectPrefix + visitorVariant + "Visitor";
		var String extendedClass = superProjectPrefix + visitorVariant + "Visitor";
		var String interfaceName =  visitorClassName +'<'+resultTypeName+'>';
		var List<Class<?>> additionalImports = new ArrayList();
		additionalImports.add(typeof(Continuation));
		csPackage.generateContextfulAbstractExtendingVisitor(className, extendedClass, 
			interfaceName,  resultTypeName, additionalImports);
	}
	
	protected def void generateLeft2RightVisitor(/*@NonNull*/ GenPackage genPackage) {
		var EPackage csPackage = genPackage.getEcorePackage();
		var String visitorVariant = "Left2Right";
		var String resultTypeName =  "Element";
		var String className = "Abstract" + projectPrefix + visitorVariant + "Visitor";
		var String extendedClass = superProjectPrefix + visitorVariant + "Visitor";
		var String interfaceName =  visitorClassName +'<'+resultTypeName+'>';
		var List<Class<?>> additionalImports = new ArrayList();
		additionalImports.add(typeof(Element));
		csPackage.generateContextfulAbstractExtendingVisitor(className, extendedClass, 
			interfaceName,  resultTypeName, additionalImports);
	}
	
	/**
	 * Assumptions to be considered:
	 * - the package of the extended visitor of generated visitors need to be qualified as follows:
	 *    <code> «superProjectName».cs2as </code>
	 */
	protected def void generateContextfulAbstractExtendingVisitor(/*@NonNull*/ EPackage ePackage, 
		/*@NonNull*/ String className, /*@NonNull*/ String extendedClassName, /*@NonNull*/ String interfaceName,
		/*@NonNull*/ String resultTypeName, /*@NonNull*/ List<Class<?>> additionalImports) {
		var List<String> allImports = new ArrayList<String>();
		allImports.add(typeof(CS2ASConversion).getName());
		allImports.add(superProjectName + ".cs2as." +extendedClassName);
		for (Class<?> anImport : additionalImports) {
			allImports.add(anImport.getName());
		}
		Collections.sort(allImports);
		var boolean needsOverride = needsOverride();
		var MergeWriter writer = new MergeWriter(outputFolder + className + ".java");
		writer.append('''
			«ePackage.generateHeader(visitorPackageName)»
			
			import org.eclipse.jdt.annotation.NonNull;
			import org.eclipse.jdt.annotation.Nullable;
			«FOR anImport : allImports»
			import «anImport»;
			«ENDFOR»
			
			/**
			 * An «className» provides a default implementation for each
			 * visitXxx method that delegates to the visitYyy method of the first
			 * super class, (or transitively its first super class first super class
			 * until a non-interface super-class is found). In the absence of any
			 * suitable first super class, the method delegates to visiting().
			 */
			public abstract class «className»
				extends «extendedClassName»
				implements «interfaceName»
			{
				/**
				 * Initializes me with an initial value for my result.
				 *
				 * @param context my initial result value
				 */
				protected «className»(@NonNull CS2ASConversion context) {
					super(context);
				}
				«FOR eClass : getSortedEClasses(ePackage)»
				«var EClass firstSuperClass = eClass.firstSuperClass(eClass)»
			
				«IF needsOverride»
				@Override
				«ENDIF»
				public «emitNullable(resultTypeName)» visit«eClass.name»(«emitNonNull(modelPackageName + "." + eClass.name)» csElement) {
					«IF firstSuperClass == eClass»
					return visiting(csElement);
					«ELSE»
					return visit«firstSuperClass.name»(csElement);
					«ENDIF»
				}
				«ENDFOR»
			}
		''');
		writer.close();
	}
}
