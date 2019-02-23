/*******************************************************************************
 * Copyright (c) 2013 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *     Adolfo Sanchez-Barbudo Herrera (University of York) - bug397429
 *******************************************************************************/
package org.eclipse.ocl.examples.build.xtend

import org.eclipse.emf.codegen.ecore.genmodel.GenPackage
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EPackage
import org.eclipse.ocl.pivot.Type
import org.eclipse.ocl.pivot.internal.manager.TemplateParameterSubstitutionVisitor
import org.eclipse.ocl.pivot.internal.manager.FlowAnalysis

abstract class GenerateVisitorsXtend extends GenerateVisitors
{
	/*
	 * Abstract«projectPrefix»«generic»Visitor
	 */
	protected def void generateAbstractGenericVisitor(/*@NonNull*/ EPackage ePackage, /*@NonNull*/ String generic, /*@NonNull*/ Class<?> returnClass, /*@NonNull*/ Class<?> contextClass) {
		var boolean isDerived = isDerived();
		var boolean needsOverride = needsOverride();
		var MergeWriter writer = new MergeWriter(outputFolder + "Abstract" + projectPrefix + generic + "Visitor.java");
		writer.append('''
			«ePackage.generateHeader(visitorPackageName)»

			import «returnClass.getName()»;
			import org.eclipse.jdt.annotation.NonNull;
			import org.eclipse.jdt.annotation.Nullable;
			import «contextClass.getName()»;
			«IF isDerived»import «superVisitorPackageName»ities.«superProjectPrefix»«generic»Visitor;«ENDIF»

			/**
			 * An Abstract«projectPrefix»«generic»Visitor provides a default implementation for each
			 * visitXxx method that delegates to the visitYyy method of the first
			 * super class, (or transitively its first super class' first super class
			 * until a non-interface super-class is found). In the absence of any
			 * suitable first super class, the method delegates to visiting().
			 */
			public abstract class Abstract«projectPrefix»«generic»Visitor
				«IF isDerived»extends «superProjectPrefix»«generic»Visitor«ENDIF»
				implements «visitorClassName»<«returnClass.getSimpleName()»>
			{
				/**
				 * Initializes me with an initial value for my result.
				 *
				 * @param context my initial result value
				 */
				protected Abstract«projectPrefix»«generic»Visitor(«emitNonNull(contextClass.getSimpleName())» context) {
					super(context);
				}
				«FOR eClass : getSortedEClasses(ePackage)»
				«var EClass firstSuperClass = eClass.firstSuperClass(eClass)»

				«IF needsOverride»
				@Override
				«ENDIF»
				public «emitNullable(returnClass.getSimpleName())» visit«eClass.name»(«emitNonNull(modelPackageName + "." + getTemplatedName(eClass))» object) {
					«IF firstSuperClass == eClass»
					return visiting(object);
					«ELSE»
					return visit«firstSuperClass.name»(object);
					«ENDIF»
				}
				«ENDFOR»
			}
		''');
		writer.close();
	}

	/*
	 * AbstractDelegatingVisitor
	 */
	protected def void generateAbstractDelegatingVisitor(/*@NonNull*/ EPackage ePackage) {
		var boolean isDerived = isDerived();
		var boolean needsOverride = needsOverride();
		var MergeWriter writer = new MergeWriter(outputFolder + "AbstractDelegating" + visitorClassName + ".java");
		writer.append('''
			«ePackage.generateHeader(visitorPackageName)»

			import org.eclipse.jdt.annotation.NonNull;

			/**
			 * An AbstractDelegating«visitorClassName» delegates all visits.
			 */
			public abstract class AbstractDelegating«visitorClassName»<R, C, @NonNull D extends «visitorClassName»<R>>
				extends «IF isDerived»«superVisitorPackageName».AbstractDelegating«superVisitorClassName»<R, C, D>«ELSE»«IF isDerived»«superVisitorClassName»«ELSE»Abstract«visitorClassName»«ENDIF»<R, C>«ENDIF»
				implements «visitorClassName»<R>
			{
				«IF isDerived»
				protected AbstractDelegating«visitorClassName»(@NonNull D delegate, C context) {
					super(delegate, context);
				}
				«ELSE»
				protected final @NonNull D delegate;

				protected AbstractDelegating«visitorClassName»(@NonNull D delegate, C context) {
					super(context);
				//	assert delegate != null : "cannot decorate a null visitor"; //$NON-NLS-1$
					this.delegate = delegate;
				//	delegate.setUndecoratedVisitor(this);
				}

				/**
				 * Delegates to my decorated visitor.
				 */
				//	public @NonNull Decorable«visitorClassName»<R> createNestedVisitor() {
				//		return delegate.createNestedVisitor();
				//	}

				/**
				 * Obtains the visitor that I decorate.
				 *
				 * @return my decorated visitor
				 */
				protected final @NonNull D getDelegate() {
					return delegate;
				}
				«ENDIF»

				«IF isDerived || needsOverride»
				@Override
				«ENDIF»
				public R visiting(«emitNonNull(visitablePackageName + "." + visitableClassName)» visitable) {
					return delegate.visiting(visitable);
				}
				«FOR eClass : getSortedEClasses(ePackage)»

				«IF needsOverride»
				@Override
				«ENDIF»
				public R visit«eClass.name»(«emitNonNull(modelPackageName + "." + getTemplatedName(eClass))» object) {
					return delegate.visit«eClass.name»(object);
				}
				«ENDFOR»
			}
		''');
		writer.close();
	}

	/*
	 * AbstractExtendingDelegatingVisitor
	 */
	protected def void generateAbstractExtendingDelegatingVisitor(/*@NonNull*/ EPackage ePackage) {
		var MergeWriter writer = new MergeWriter(outputFolder + "AbstractExtendingDelegating" + visitorClassName + ".java");
		writer.append('''
			«ePackage.generateHeader(visitorPackageName)»

			import org.eclipse.jdt.annotation.NonNull;
			import org.eclipse.jdt.annotation.Nullable;
			import «superVisitorPackageName».AbstractDelegating«superVisitorClassName»;
			import «superVisitorPackageName».«superVisitorClassName»;

			/**
			 * An AbstractExtendingDelegating«visitorClassName» provides a default implementation for each
			 * visitXxx method that delegates to the supertype if the supertype is in the same package as
			 * the visited type, otherwise it delegates to the delegate.
			 */
			public abstract class AbstractExtendingDelegating«visitorClassName»<R, C, D extends «superVisitorClassName»<R>>
				extends AbstractDelegating«superVisitorClassName»<R, C, D>
				implements «visitorClassName»<R>
			{
				«IF true»
				protected AbstractExtendingDelegating«visitorClassName»(@NonNull D delegate, C context) {
					super(delegate, context);
				}
				«ELSE»
				protected final D delegate;

				protected AbstractExtendingDelegating«visitorClassName»(@NonNull D delegate, C context) {
					super(context);
				//	assert delegate != null : "cannot decorate a null visitor"; //$NON-NLS-1$
					this.delegate = delegate;
				//	delegate.setUndecoratedVisitor(this);
				}

				/**
				 * Delegates to my decorated visitor.
				 */
				//	public Decorable«visitorClassName»<R> createNestedVisitor() {
				//		return delegate.createNestedVisitor();
				//	}

				/**
				 * Obtains the visitor that I decorate.
				 *
				 * @return my decorated visitor
				 */
				protected final D getDelegate() {
					return delegate;
				}
				«ENDIF»

				@Override
				public R visiting(«emitNonNull(visitablePackageName + "." + visitableClassName)» visitable) {
					return delegate.visiting(visitable);
				}
				«FOR eClass : getSortedEClasses(ePackage)»
				«var EClass firstSuperClass = eClass.firstSuperClass(eClass)»

				«IF needsOverride»
				@Override
				«ENDIF»
				public R visit«eClass.name»(«emitNonNull(modelPackageName + "." + getTemplatedName(eClass))» object) {
					«IF firstSuperClass == eClass»
					return visiting(object);
					«ELSEIF firstSuperClass.getEPackage() == eClass.getEPackage()»
					return visit«firstSuperClass.name»(object);
					«ELSE»
					return delegate.visit«firstSuperClass.name»(object);
					«ENDIF»
				}
				«ENDFOR»
			}
		''');
		writer.close();
	}

	/*
	 * AbstractExtendingVisitor
	 */
	protected def void generateAbstractExtendingVisitor(/*@NonNull*/ EPackage ePackage) {
		var boolean isDerived = isDerived();
		var boolean needsOverride = needsOverride();
		var MergeWriter writer = new MergeWriter(outputFolder + "AbstractExtending" + visitorClassName + ".java");
		writer.append('''
			«ePackage.generateHeader(visitorPackageName)»

			import org.eclipse.jdt.annotation.NonNull;

			/**
			 * An AbstractExtending«visitorClassName» provides a default implementation for each
			 * visitXxx method that delegates to the visitYyy method of the first
			 * super class, (or transitively its first super class' first super class
			 * until a non-interface super-class is found). In the absence of any
			 * suitable first super class, the method delegates to visiting().
			 */
			public abstract class AbstractExtending«visitorClassName»<R, C>
				extends «IF isDerived»«superVisitorPackageName».AbstractExtending«superVisitorClassName»«ELSE»Abstract«visitorClassName»«ENDIF»<R, C>
				implements «visitorClassName»<R>
			{
				/**
				 * Initializes me with an initial value for my result.
				 *
				 * @param context my initial result value
				 */
				protected AbstractExtending«visitorClassName»(C context) {
					super(context);
				}
				«FOR eClass : getSortedEClasses(ePackage)»
				«var EClass firstSuperClass = eClass.firstSuperClass(eClass)»

				«IF needsOverride»
				@Override
				«ENDIF»
				public R visit«eClass.name»(«emitNonNull(modelPackageName + "." + getTemplatedName(eClass))» object) {
					«IF firstSuperClass == eClass»
					return visiting(object);
					«ELSE»
					return visit«firstSuperClass.name»(object);
					«ENDIF»
				}
				«ENDFOR»
			}
		''');
		writer.close();
	}

	/*
	 * AbstractMergedVisitor
	 */
	protected def void generateAbstractMergedVisitor(/*@NonNull*/ EPackage ePackage) {
		var boolean isDerived = isDerived();
		var boolean needsOverride = needsOverride();
		var MergeWriter writer = new MergeWriter(outputFolder + "AbstractMerged" + visitorClassName + ".java");
		writer.append('''
			«ePackage.generateHeader(visitorPackageName)»

			import org.eclipse.jdt.annotation.NonNull;

			/**
			 * An AbstractMerged«visitorClassName» merges all visits direct to visiting().
			 * This can be used by a decorating visitor to execute shared code before redispatching to a decorated visitor.
			 */
			public abstract class AbstractMerged«visitorClassName»<R, C>
				extends «IF isDerived»«superVisitorPackageName».AbstractMerged«superVisitorClassName»<R, C>«ELSE»«IF isDerived»«superVisitorClassName»«ELSE»Abstract«visitorClassName»«ENDIF»<R, C>«ENDIF»
				implements «visitorClassName»<R>
			{
				protected AbstractMerged«visitorClassName»(C context) {
					super(context);
				}
				«FOR eClass : getSortedEClasses(ePackage)»

				«IF needsOverride»
				@Override
				«ENDIF»
				public R visit«eClass.name»(«emitNonNull(modelPackageName + "." + getTemplatedName(eClass))» object) {
					return visiting(object);
				}
				«ENDFOR»
			}
		''');
		writer.close();
	}

	/*
	 * AbstractNonNullExtendingVisitor
	 */
	protected def void generateAbstractNonNullExtendingVisitor(/*@NonNull*/ EPackage ePackage) {
		var boolean isDerived = isDerived();
		var boolean needsOverride = needsOverride();
		var MergeWriter writer = new MergeWriter(outputFolder + "AbstractNonNullExtending" + visitorClassName + ".java");
		writer.append('''
			«ePackage.generateHeader(visitorPackageName)»

			import org.eclipse.jdt.annotation.NonNull;

			/**
			 * An AbstractExtendingNonNull«visitorClassName» provides a default implementation for each
			 * visitXxx method that delegates to the visitYyy method of the first
			 * super class, (or transitively its first super class first super class
			 * until a non-interface super-class is found). In the absence of any
			 * suitable first super class, the method delegates to visiting().
			 * The return is annotated as @NonNull.
			 *
			 * @deprecated Explicit 'NonNull' functionality is obsolete with Java 8 @NonNull annotations.
			 */
			 @Deprecated
			public abstract class AbstractNonNullExtending«visitorClassName»<R, C>
				extends «IF isDerived»«superVisitorPackageName».AbstractNonNullExtending«superVisitorClassName»«ELSE»Abstract«visitorClassName»«ENDIF»<R, C>
				implements «visitorClassName»<R>
			{
				/**
				 * Initializes me with an initial value for my result.
				 *
				 * @param context my initial result value
				 */
				protected AbstractNonNullExtending«visitorClassName»(C context) {
					super(context);
				}
				«IF !isDerived»

				/**
				 * Perform a visit to the specified visitable.
				 *
				 * @param visitable a visitable
				 * @return the non-null result of visiting it
				 */
				@Override
				public @NonNull R visit(«emitNonNull(visitablePackageName + "." + visitableClassName)» visitable) {
					R result = visitable.accept(this);
					if (result == null) {
						throw new IllegalStateException("null return from non-null " + getClass().getName());
					}
					return result;
				}
				«ENDIF»
				«FOR eClass : getSortedEClasses(ePackage)»
				«var EClass firstSuperClass = eClass.firstSuperClass(eClass)»

				«IF needsOverride»
				@Override
				«ENDIF»
				public @NonNull R visit«eClass.name»(«emitNonNull(modelPackageName+ "." + getTemplatedName(eClass))» object) {
					«IF firstSuperClass == eClass»
					return visiting(object);
					«ELSE»
					return visit«firstSuperClass.name»(object);
					«ENDIF»
				}
				«ENDFOR»
				«IF !isDerived»

				/**
				 * Return the result of visiting a visitable for which no more specific pivot type method
				 * is available.
				 */
				«IF needsOverride»
				@Override
				«ENDIF»
				public abstract @NonNull R visiting(«emitNonNull(visitablePackageName+ "." + visitableClassName)» visitable);
				«ENDIF»
			}
		''');
		writer.close();
	}

	/*
	 * AbstractNullVisitor
	 */
	protected def void generateAbstractNullVisitor(/*@NonNull*/ EPackage ePackage) {
		var boolean isDerived = isDerived();
		var boolean needsOverride = needsOverride();
		var MergeWriter writer = new MergeWriter(outputFolder + "AbstractNull" + visitorClassName + ".java");
		writer.append('''
			«ePackage.generateHeader(visitorPackageName)»

			import org.eclipse.jdt.annotation.NonNull;
			import org.eclipse.jdt.annotation.Nullable;

			/**
			 * An AbstractNull«visitorClassName» provides a default implementation for each
			 * visitXxx method that returns null.
			 *
			 * @deprecated Explicit 'Null' functionality is obsolete with Java 8 @Nullable annotations.
			 */
			 @Deprecated
			public abstract class AbstractNull«visitorClassName»<@Nullable R, C>
				«IF !isDerived»
				extends Abstract«visitorClassName»<R, C>
				«ELSE»
				extends «superVisitorPackageName».AbstractNull«superVisitorClassName»<R, C> implements «visitorClassName»<R>
				«ENDIF»
			{
				/**
				 * Initializes me with an initial value for my result.
				 *
				 * @param context my initial result value
				 */
				protected AbstractNull«visitorClassName»(C context) {
					super(context);
				}
				«FOR eClass : getSortedEClasses(ePackage)»

				«IF needsOverride»
				@Override
				«ENDIF»
				public R visit«eClass.name»(«emitNonNull(modelPackageName + "." + getTemplatedName(eClass))» object) {
					return null;
				}
				«ENDFOR»
			}
		''');
		writer.close();
	}
	
	/*
	 * Abstract«projectPrefix»«generic»FlowAnalysisDeducerFromTrueVisitor
	 */
	protected def void generateAbstractFlowAnalysisDeducerVisitor(/*@NonNull*/ EPackage ePackage, /*@NonNull*/ String generic, /*@NonNull*/ Class<?> returnClass, /*@NonNull*/ Class<?> contextClass, boolean isNull) {
		var boolean isDerived = isDerived();
		var boolean needsOverride = needsOverride();
		var MergeWriter writer = new MergeWriter(outputFolder + "Abstract" + projectPrefix + generic + "Visitor.java");
		writer.append('''
			«ePackage.generateHeader(visitorPackageName)»

			import «returnClass.getName()»;
			import org.eclipse.jdt.annotation.NonNull;
			import org.eclipse.jdt.annotation.Nullable;
			import «contextClass.getName()»;
			«IF isDerived && !superProjectPrefix.equals("")»import «superVisitorPackageName»ities.«superProjectPrefix»«generic»Visitor;«ENDIF»
			«IF isDerived && superProjectPrefix.equals("")»import «FlowAnalysis.getName()»;«ENDIF»

			/**
			 * An Abstract«projectPrefix»«generic»Visitor provides a default implementation for each
			 * visitXxx method that delegates to the visitYyy method of the first
			 * super class, (or transitively its first super class' first super class
			 * until a non-interface super-class is found). In the absence of any
			 * suitable first super class, the method delegates to visiting().
			 */
			public abstract class Abstract«projectPrefix»«generic»Visitor
				«IF isDerived»extends «superProjectPrefix»«generic»Visitor«ENDIF»
				implements «visitorClassName»<«returnClass.getSimpleName()»>
			{
				/**
				 * Initializes me with an initial value for my result.
				 *
				 * @param context my initial result value
				 */
				protected Abstract«projectPrefix»«generic»Visitor(«emitNonNull(contextClass.getSimpleName())» flowAnalysis«IF isNull», boolean isNull«ENDIF») {
					super(flowAnalysis«IF isNull», isNull«ENDIF»);
				}
				«FOR eClass : getSortedEClasses(ePackage)»
				«var EClass firstSuperClass = eClass.firstSuperClass(eClass)»

				«IF needsOverride»
				@Override
				«ENDIF»
				public «emitNullable(returnClass.getSimpleName())» visit«eClass.name»(«emitNonNull(modelPackageName + "." + getTemplatedName(eClass))» object) {
					«IF firstSuperClass == eClass»
					return visiting(object);
					«ELSE»
					return visit«firstSuperClass.name»(object);
					«ENDIF»
				}
				«ENDFOR»
			}
		''');
		writer.close();
	}
	
	/*
	 * Abstract«projectPrefix»«generic»TemplateParameterSubstitutionVisitor
	 */
	protected def void generateAbstractTemplateParameterSubstitutionVisitor(/*@NonNull*/ EPackage ePackage, /*@NonNull*/ String generic, /*@NonNull*/ Class<?> returnClass, /*@NonNull*/ Class<?> contextClass) {
		var boolean isDerived = isDerived();
		var boolean needsOverride = needsOverride();
		var MergeWriter writer = new MergeWriter(outputFolder + "Abstract" + projectPrefix + generic + "Visitor.java");
		writer.append('''
			«ePackage.generateHeader(visitorPackageName)»

			import «returnClass.getName()»;
			import org.eclipse.jdt.annotation.NonNull;
			import org.eclipse.jdt.annotation.Nullable;
			import «Type.getName()»;
			import «contextClass.getName()»;
			«IF isDerived && !superProjectPrefix.equals("")»import «superVisitorPackageName»ities.«superProjectPrefix»«generic»Visitor;«ENDIF»
			«IF isDerived && superProjectPrefix.equals("")»import «TemplateParameterSubstitutionVisitor.getName()»;«ENDIF»

			/**
			 * An Abstract«projectPrefix»«generic»Visitor provides a default implementation for each
			 * visitXxx method that delegates to the visitYyy method of the first
			 * super class, (or transitively its first super class' first super class
			 * until a non-interface super-class is found). In the absence of any
			 * suitable first super class, the method delegates to visiting().
			 */
			public abstract class Abstract«projectPrefix»«generic»Visitor
				«IF isDerived»extends «superProjectPrefix»«generic»Visitor«ENDIF»
				implements «visitorClassName»<«returnClass.getSimpleName()»>
			{
				/**
				 * Initializes me with an initial value for my result.
				 *
				 * @param context my initial result value
				 */
				protected Abstract«projectPrefix»«generic»Visitor(«emitNonNull(contextClass.getSimpleName())» environmentFactory, @Nullable Type selfType, @Nullable Type selfTypeValue) {
					super(environmentFactory, selfType, selfTypeValue);
				}
				«FOR eClass : getSortedEClasses(ePackage)»
				«var EClass firstSuperClass = eClass.firstSuperClass(eClass)»

				«IF needsOverride»
				@Override
				«ENDIF»
				public «emitNullable(returnClass.getSimpleName())» visit«eClass.name»(«emitNonNull(modelPackageName + "." + getTemplatedName(eClass))» object) {
					«IF firstSuperClass == eClass»
					return visiting(object);
					«ELSE»
					return visit«firstSuperClass.name»(object);
					«ENDIF»
				}
				«ENDFOR»
			}
		''');
		writer.close();
	}

	/*
	 *AbstractVisitor
	 */
	protected def void generateAbstractVisitor(/*@NonNull*/ EPackage ePackage) {
		var boolean isDerived = isDerived();
		var boolean needsOverride = needsOverride();
		var MergeWriter writer = new MergeWriter(outputFolder + "Abstract" + visitorClassName + ".java");
		writer.append('''
			«ePackage.generateHeader(visitorPackageName)»

			«IF !isDerived»
			import org.eclipse.jdt.annotation.NonNull;
			import org.eclipse.jdt.annotation.Nullable;
			«ENDIF»

			/*
			 * An Abstract«visitorClassName» provides a default implementation of the visitor framework
			 * but n implementations of the visitXXX methods..
			 */
			public abstract class Abstract«visitorClassName»<R, C>
				«IF isDerived»
				extends «superVisitorPackageName».Abstract«superVisitorClassName»<R, C>
				«ENDIF»
				implements «visitorClassName»<R>
			{
				«IF !isDerived»
				/**
				 * Context for the AST visitation.
				 */
				protected final C context;

				«ENDIF»
				/**
				 * Initializes me with an initial value for my result.
				 *
				 * @param context my initial result value
				 */
				protected Abstract«visitorClassName»(C context) {
					«IF !isDerived»
					this.context = context;
					«ELSE»
					super(context);
					«ENDIF»
				}
				«IF !isDerived»

				@SuppressWarnings("unchecked")
				«IF needsOverride»
				@Override
				«ENDIF»
				public <A> @Nullable A getAdapter(@NonNull Class<A> adapter) {
					if (adapter.isAssignableFrom(getClass())) {
						return (A) this;
					}
					else {
						return null;
					}
				}

				/**
				 * A null-safe visitation of the specified visitable.
				 *
				 * @param v a visitable, or <code>null</code>
				 * @return <code>null</code> if the visitable is <code>null</code>;
				 *	 otherwise, the result of visiting it
				 */
				public @Nullable R safeVisit(«emitNullable(visitablePackageName + "." + visitableClassName)» v) {
					return (v == null) ? null : v.accept(this);
				}

				/**
				 * Perform a visit to the specified visitable.
				 *
				 * @param v a visitable, or <code>null</code>
				 * @return <code>null</code> if the visitable is <code>null</code>;
				 *	 otherwise, the result of visiting it
				 */
				public R visit(«emitNonNull(visitablePackageName + "." + visitableClassName)» v) {
					return v.accept(this);
				}

				//	public R visiting(«emitNonNull(visitablePackageName + "." + visitableClassName)» visitable) {
				//		return null;
				//	}
				«ENDIF»
			}
		''');
		writer.close();
	}

	/*
	 * AbstractWrappingVisitor
	 */
	protected def void generateAbstractWrappingVisitor(/*@NonNull*/ EPackage ePackage) {
		var boolean isDerived = isDerived();
		var boolean needsOverride = needsOverride();
		var MergeWriter writer = new MergeWriter(outputFolder + "AbstractWrapping" + visitorClassName + ".java");
		writer.append('''
			«ePackage.generateHeader(visitorPackageName)»

			import org.eclipse.jdt.annotation.NonNull;
			import org.eclipse.jdt.annotation.Nullable;

			/**
			 * An AbstractWrapping«visitorClassName» delegates all visits wrapping the delegation in a call to a preVisit function and a postVisit function.
			 */
			public abstract class AbstractWrapping«visitorClassName»<R, C, @NonNull D extends «visitorClassName»<R>, P>
				extends «IF isDerived»«superVisitorPackageName».AbstractWrapping«superVisitorClassName»<R, C, D, P>«ELSE»«IF isDerived»«superVisitorClassName»«ELSE»Abstract«visitorClassName»«ENDIF»<R, C>«ENDIF»
				implements «visitorClassName»<R>
			{
				«IF isDerived»
				protected AbstractWrapping«visitorClassName»(@NonNull D delegate, C context) {
					super(delegate, context);
				}
				«ELSE»
				protected final @NonNull D delegate;

				protected AbstractWrapping«visitorClassName»(@NonNull D delegate, C context) {
					super(context);
					this.delegate = delegate;
				//	delegate.setUndecoratedVisitor(this);
				}

				/**
				 * Intercept an exception thrown by the delegated visit to perform some post-functionality that may use the visitable object,
				 * the result of preVisit and the thrown exception to determine the overall wrapped result.
				 *
				 * @return a rethrown RuntimeException or a RuntimeException-wrapped non-RuntimeException.
				 */
				protected R badVisit(«emitNonNull(visitablePackageName + "." + visitableClassName)» visitable, @Nullable P prologue, @NonNull Throwable e) throws RuntimeException {
					if (e instanceof Exception) {
						throw (RuntimeException)e;
					}
					else {
						throw new RuntimeException(e);
					}
				}

				/**
				 * Obtains the visitor that I wrap.
				 *
				 * @return my wrapped visitor
				 */
				protected @NonNull D getDelegate() {
					return delegate;
				}

				/**
				 * Intercept the result of the delegated visit to perform some post-functionality that may use the visitable object,
				 * the result of preVisit and the result of the delegated visit to determine the overall wrapped result.
				 *
				 * @return the epilogue result, which defaults to the delegated result.
				 */
				protected R postVisit(«emitNonNull(visitablePackageName + "." + visitableClassName)» visitable, @Nullable P prologue, R result) {
					return result;
				}

				/**
				 * Compute and return some value before performing the delegated visit.
				 *
				 * @return the prologue result, which defauilts to null.
				 */
				protected @Nullable P preVisit(«emitNonNull(visitablePackageName + "." + visitableClassName)» visitable) {
					return null;
				}

				«IF needsOverride»
				@Override
				«ENDIF»
				public R visiting(«emitNonNull(visitablePackageName + "." + visitableClassName)» visitable) {
					throw new UnsupportedOperationException();		// Cannot happen since all methods delegate.
				}
				«ENDIF»
				«FOR eClass : getSortedEClasses(ePackage)»

				«IF needsOverride»
				@Override
				«ENDIF»
				public R visit«eClass.name»(«emitNonNull(modelPackageName + "." + getTemplatedName(eClass))» object) {
					@Nullable P prologue = preVisit(object);
					try {
						R result = delegate.visit«eClass.name»(object);
						return postVisit(object, prologue, result);
					}
					catch (Throwable e) {
						return badVisit(object, prologue, e);
					}
				}
				«ENDFOR»
			}
		''');
		writer.close();
	}

	/*
	 * DecorableVisitorInterface
	 */
	protected def void generateDecorableVisitorInterface(/*@NonNull*/ EPackage ePackage, String visitorRootClass) {
		var boolean isDerived = isDerived();
		var boolean needsOverride = needsOverride();
		var MergeWriter writer = new MergeWriter(outputFolder + "Decorable" + visitorClassName + ".java");
		writer.append('''
			«ePackage.generateHeader(visitorPackageName)»

			import org.eclipse.jdt.annotation.NonNull;

			/**
			 */
			public interface Decorable«visitorClassName»<R> extends «visitorClassName»<R>«IF isDerived», «superVisitorPackageName».Decorable«superVisitorClassName»<R>«ENDIF»
			{
				«IF isDerived && needsOverride»
				@Override
				«ENDIF»
				void setUndecoratedVisitor(«emitNonNull(visitorRootClass)»<R> visitor);
			}
		''');
		writer.close();
	}

	protected def String generateHeader(/*@NonNull*/ EPackage ePackage, String javaPackage) {
		generateHeader(ePackage,javaPackage, null);
	}

	protected def String generateHeaderWithTemplate(/*@NonNull*/ EPackage ePackage, String javaPackage) {
		generateHeader(ePackage,javaPackage, class.canonicalName);
	}

	protected def String generateHeader(EPackage ePackage, String javaPackage, String template) {
		'''
		/*******************************************************************************
		 * «MergeWriter.getCopyright(copyright).replace("\n", "\n* ").replace("\n* \n", "\n*\n")»
		 *
		 * This code is auto-generated
		 * from: «projectName»/«sourceFile»
		 «IF template !== null»
		 * template: «template»
		 «ENDIF»
		 *
		 * Only the copyright statement is editable.
		 *******************************************************************************/
		package	«javaPackage»;
		'''
	}

	protected def void generateVisitableInterface(/*@NonNull*/ GenPackage genPackage) {
		var genModel = genPackage.getGenModel();
		var String directoryURI = getInterfaceModelDirectory(genModel);
		var visitableClassName2 = getVisitableClassName(genModel);
		var visitablePackageName2 = getVisitablePackageName(genModel);
		var EPackage ePackage = genPackage.getEcorePackage();
		var MergeWriter writer = new MergeWriter(directoryURI + visitablePackageName2.replace(".", "/") + "/" + visitableClassName2 + ".java");
		writer.append('''
			«ePackage.generateHeader(visitablePackageName2)»

			import org.eclipse.emf.ecore.EClass;
			import org.eclipse.jdt.annotation.NonNull;

			public interface «visitableClassName2»
			{
				/**
				 * Returns the result of accepting a visit from a visitor.
				 * Implementations typically invoke a derived-class-specific
				 * variant of visitXXX() to facilitate derived-class-specific
				 * processing or just visit() when no such method is available.
				 * <p>
				 * Implementations of visit() may use the EcoreSwitch to perform
				 * derived-class-specific processing.
				 * <p>
				 * Derived implementations of accept() may use getAdapter() to obtain
				 * richer visitor interfaces.
				 * @param <R>
				 * @param visitor
				 * @return the result of the visit.
				 */
				<R> R accept(«emitNonNull(visitorPackageName +"." + visitorClassName)»<R> visitor);

				EClass eClass();
			}
		''');
		writer.close();
	}

	protected def void generateVisitorInterface(/*@NonNull*/ GenPackage genPackage) {
		var genModel = genPackage.getGenModel();
		var String directoryURI = getInterfaceModelDirectory(genModel);
		var visitableClassName2 = getVisitableClassName(genModel);
		var visitablePackageName2 = getVisitablePackageName(genModel);
		var visitorClassName2 = visitorClassName; //getVisitableClassName(genModel);
		var visitorPackageName2 = visitorPackageName; //getVisitablePackageName(genModel);
		var boolean isDerived = isDerived();
		var EPackage ePackage = genPackage.getEcorePackage();
		var MergeWriter writer = new MergeWriter(directoryURI + visitorPackageName2.replace(".", "/") + "/" + visitorClassName2 + ".java");
		writer.append('''
			«ePackage.generateHeader(visitorPackageName)»

			import org.eclipse.jdt.annotation.NonNull;
			«IF !isDerived»
			import org.eclipse.jdt.annotation.Nullable;
			«ENDIF»

			/**
			 * @noimplement This interface is not intended to be implemented by clients.
			 */
			public interface «visitorClassName2»<R>«IF isDerived» extends «superVisitorPackageName».«superVisitorClassName»<R>«ENDIF»
			{
				«IF !isDerived»
				/**
				 * Returns an object which is an instance of the given class
				 * associated with this object. Returns <code>null</code> if
				 * no such object can be found.
				 *
				 * @param adapter the adapter class to look up
				 * @return an object of the given class,
				 *    or <code>null</code> if this object does not
				 *    have an adapter for the given class
				 */
				@Nullable <A> A getAdapter(@NonNull Class<A> adapter);

				/**
				 * Return the result of visiting a visitable for which no more specific pivot type method
				 * is available.
				 */
				R visiting(«emitNonNull(visitablePackageName2 + "." + visitableClassName2)» visitable);

				«ENDIF»
				«FOR eClass : getSortedEClasses(ePackage)»
				R visit«eClass.name»(«emitNonNull(modelPackageName + "." + getTemplatedName(eClass))» object);
				«ENDFOR»
			}
		''')
		writer.close();
	}
}
