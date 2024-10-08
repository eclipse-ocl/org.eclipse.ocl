/*******************************************************************************
 * Copyright (c) 2015 Willink Transformations, University of York and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     Adolfo Sanchez-Barbudo Herrera (University of York) - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.autogen.xtend

import java.util.ArrayList
import java.util.List
import org.eclipse.emf.codegen.ecore.genmodel.GenClassifier
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.mwe.core.issues.Issues
import org.eclipse.ocl.examples.codegen.generator.AbstractGenModelHelper
import org.eclipse.ocl.examples.codegen.generator.GenModelHelper
import org.eclipse.ocl.pivot.Class
import org.eclipse.ocl.pivot.Operation
import org.eclipse.ocl.pivot.Type
import org.eclipse.ocl.pivot.internal.manager.PivotMetamodelManager
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal
import org.eclipse.ocl.pivot.utilities.ClassUtil
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory
import org.eclipse.ocl.pivot.CollectionType
import org.eclipse.ocl.pivot.utilities.OCL
import org.eclipse.ocl.examples.build.utilities.GenPackageHelper
import org.eclipse.ocl.examples.autogen.lookup.LookupCodeGenerator
import org.eclipse.ocl.xtext.completeocl.CompleteOCLStandaloneSetup
import org.eclipse.ocl.pivot.model.OCLstdlib
import java.util.Set
import org.eclipse.ocl.examples.autogen.lookup.LookupCGUtil
import org.eclipse.ocl.examples.build.xtend.GenerateVisitorsXtend
import org.eclipse.ocl.examples.build.xtend.MergeWriter
import org.eclipse.emf.mwe.core.WorkflowContext
import org.eclipse.emf.mwe.core.monitor.ProgressMonitor

class GenerateAutoLookupInfrastructureXtend extends GenerateVisitorsXtend
{

	protected String lookupFilePath;
	// FIXME The lookupVisitor will be in the normal visitors folder, but all the artifacts generated by this component
	// will be in a different one
	protected String lookupArtifactsJavaPackage;
	protected String lookupArtifactsOutputFolder;
	protected String lookupPackageName;
	protected String superLookupPackageName
	protected String baseLookupPackageName
	protected GenModelHelper genModelHelper;
	
	override checkConfiguration(Issues issues) {
		super.checkConfiguration(issues);
		if (!isDefined(lookupFilePath)) {
			issues.addError(this, "lookupFilePath must be specified");
		}
	}
	
	override invokeInternal(WorkflowContext ctx, ProgressMonitor monitor, Issues issues) {
		super.invokeInternal(ctx, monitor, issues);
		genModelHelper = null;
	}

	/**
	 * The path inside the projectName to the Complete OCL file which contains the 
	 * name resolution description (e.g. "model/NameResolution.ocl"). It 
	 * can't be null
	 */
	def setLookupFilePath(String lookupFilePath) {
		this.lookupFilePath = lookupFilePath;
	}
	
	def setLookupPackageName(String lookupPackageName) {
		this.lookupPackageName = lookupPackageName;
	}
	
	def setSuperLookupPackageName(String superLookupPackageName) {
		this.superLookupPackageName = superLookupPackageName;
	}
	
	def setBaseLookupPackageName(String baseLookupPackageName) {
		this.baseLookupPackageName = baseLookupPackageName;
	}
	
	protected override doSetup() {
		CompleteOCLStandaloneSetup.doSetup();
		OCLstdlib.install();
	}
	

	override protected doPropertiesConfiguration(OCL ocl) {
		super.doPropertiesConfiguration(ocl);
		val genModelURI = getGenModelURI(projectName, genModelFile);
		val genModelResource = getGenModelResource(ocl, genModelURI);
		val genPackage = getGenPackage(genModelResource);
		
		var helper = new GenPackageHelper(genPackage);
		if (!lookupPackageName.isDefined || lookupPackageName.length == 0) {
			lookupPackageName = helper.modelPackageName + ".lookup"	
		}
		 
		if (isDerived()) {
			val superGenModelURI = getGenModelURI(superProjectName, superGenModelFile);
			val superGenModelResource = getGenModelResource(ocl, superGenModelURI);
			val superGenPackage = getGenPackage(superGenModelResource);
			helper = new GenPackageHelper(superGenPackage)
			if (!superLookupPackageName.isDefined || superLookupPackageName.length == 0) {
				superLookupPackageName = helper.modelPackageName + ".lookup"
			}
			
			val baseGenModelURI = getGenModelURI(baseProjectName, baseGenModelFile);
			val baseGenModelResource = getGenModelResource(ocl, baseGenModelURI);
			val baseGenPackage = getGenPackage(baseGenModelResource);
			helper = new GenPackageHelper(baseGenPackage);
			if (!baseLookupPackageName.isDefined || baseLookupPackageName.length == 0) {
				baseLookupPackageName = helper.modelPackageName + ".lookup"
			}
		} else {
			if (!baseLookupPackageName.isDefined || baseLookupPackageName.length == 0) {
				baseLookupPackageName = lookupPackageName;
			}
		}
	}
	
	@SuppressWarnings("null")
	protected def doGenerateVisitors(/*@NonNull*/ GenPackage genPackage) {
		LookupCodeGenerator.generate(genPackage, superGenPackage, baseGenPackage, projectName, lookupFilePath,
				lookupPackageName, lookupPackageName,null); // FIXME
	}
	
	protected def void generateAutoLookupResultItf(/*@NonNull*/ EPackage ePackage) {
		var boolean isDerived = isDerived();
		if (!isDerived) {
			var MergeWriter writer = new MergeWriter(lookupArtifactsOutputFolder + projectPrefix + "LookupResult.java");
			writer.append('''
	«ePackage.generateHeaderWithTemplate(lookupArtifactsJavaPackage)»
	
	import java.util.List;
	
	import org.eclipse.jdt.annotation.NonNull;
	import org.eclipse.jdt.annotation.Nullable;
	
	/**
	 * The lookup result returned by the name lookup solver
	 */
	public interface «projectPrefix»LookupResult<NE> {
	
		@Nullable
		NE getSingleResult();
		
		@NonNull
		List<NE> getAllResults();
		
		int size();
	}
			''');
			writer.close();
		}
	}
	
	protected def void generateAutoLookupResultClass(/*@NonNull*/ EPackage ePackage) {
		var boolean isDerived = isDerived();
		if (!isDerived) {
			var MergeWriter writer = new MergeWriter(lookupArtifactsOutputFolder + projectPrefix + "LookupResultImpl.java");
			writer.append('''
	«ePackage.generateHeaderWithTemplate(lookupArtifactsJavaPackage)»
	
	import java.util.ArrayList;
	import java.util.Collections;
	import java.util.List;
	
	import org.eclipse.jdt.annotation.NonNull;
	import org.eclipse.jdt.annotation.Nullable;
	
	public class «projectPrefix»LookupResultImpl<NE> implements «projectPrefix»LookupResult<NE> {
		
		private @NonNull List<NE> results = new ArrayList<NE>();
		
		public «projectPrefix»LookupResultImpl(List<NE> results){
			this.results.addAll(results);
		}
		
		@Override
		public @NonNull List<NE> getAllResults() {
			return Collections.unmodifiableList(results);
		}
		
		@Override
		public @Nullable NE getSingleResult() {
			return results.size() == 0 ? null : results.get(0);
		}
		
		@Override
		public int size() {
			return results.size();
		}
	}
			''');
			writer.close();
		}
	}
	
	protected def void generateAutoLookupFilterItf(/*@NonNull*/ EPackage ePackage) {
		val boolean isDerived = isDerived();
		if (!isDerived) {
			val itfName =  '''«projectPrefix»LookupFilter'''
			val MergeWriter writer = new MergeWriter(lookupArtifactsOutputFolder + itfName + ".java");
			writer.append('''
	«ePackage.generateHeaderWithTemplate(lookupArtifactsJavaPackage)»
	
	import org.eclipse.jdt.annotation.NonNull;
	import «modelPackageName».NamedElement;

	/**
	 * 
	 */
	public interface «itfName» {
		
		boolean matches(@NonNull NamedElement namedElement);
		
	}
			''');
			writer.close();
		}
	}
	
	protected def void generateAutoLookupFilterClass(/*@NonNull*/ EPackage ePackage) {
		val boolean isDerived = isDerived();
		if (!isDerived) {
			val className =  '''Abstract«projectPrefix»LookupFilter''';
			val itfName =  '''«projectPrefix»LookupFilter'''
			val MergeWriter writer = new MergeWriter(lookupArtifactsOutputFolder + className + ".java");
			writer.append('''
	«ePackage.generateHeaderWithTemplate(lookupArtifactsJavaPackage)»
	
	import org.eclipse.jdt.annotation.NonNull;
	import «modelPackageName».NamedElement;

	/**
	 * 
	 */
	public abstract class «className»<C extends NamedElement> implements «itfName» {
		
		@NonNull private Class<C> _class;
		
		public «className»(@NonNull Class<C> _class) {
			this._class = _class;
		}
		
		@SuppressWarnings("unchecked")
		@Override
		public boolean matches(@NonNull NamedElement namedElement) {
			return _class.isInstance(namedElement) && _matches((C)namedElement);
		}
		
		abstract protected Boolean _matches(@NonNull C element);
	}
			''');
			writer.close();
		}
	}
	protected def void generateSingleResultLookupEnvironment(/*@NonNull*/ EPackage ePackage) { 
		
		var boolean isDerived = isDerived();
		var className = projectPrefix + "SingleResultLookupEnvironment";
		if (!isDerived) {
			var MergeWriter writer = new MergeWriter(lookupArtifactsOutputFolder + className+ ".java");
			writer.append('''
	«ePackage.generateHeaderWithTemplate(lookupArtifactsJavaPackage)»
	
	import java.util.ArrayList;
	import java.util.Collection;
	import java.util.List;
	
	import org.eclipse.emf.ecore.EClass;
	import org.eclipse.jdt.annotation.NonNull;
	import org.eclipse.jdt.annotation.Nullable;
	import org.eclipse.ocl.pivot.evaluation.Executor;
	
	import «modelPackageName».NamedElement;
	import «lookupPackageName».LookupEnvironment;
	import «lookupPackageName».impl.LookupEnvironmentImpl;
	
	public class «className» extends LookupEnvironmentImpl   {
		
		private @NonNull Executor executor;
		private @NonNull String name;
		private @NonNull EClass typeFilter;
		private @Nullable «projectPrefix»LookupFilter expFilter;
		
		public «className»(@NonNull Executor executor, /*@NonNull*/ EClass typeFilter, /*@NonNull*/ String name,  @Nullable «projectPrefix»LookupFilter expFilter) {
			assert typeFilter != null;
			assert name != null;
			this.executor = executor;
			this.name = name;
			this.typeFilter = typeFilter;
			this.expFilter = expFilter;
		}
	
		public «className»(@NonNull Executor executor, /*@NonNull*/ EClass typeFilter, /*@NonNull*/ String name) {
			this(executor, typeFilter, name, null);
		}
		
		@Override
		@NonNull
		public Executor getExecutor() {
			return executor;
		}
		
		@Override
		public boolean hasFinalResult() {
			for (NamedElement element : getNamedElements()) {
				if (name.equals(element.getName())) {
					return true;
				}
			}
			return false;
		}
		
		@Override
		@NonNull
		public LookupEnvironment addElement(@Nullable NamedElement namedElement) {
			if (namedElement != null) {
				if (name.equals(namedElement.getName())) {
					if (typeFilter.isInstance(namedElement)) {
					    «projectPrefix»LookupFilter expFilter2 = expFilter;
						if (expFilter2 == null || expFilter2.matches(namedElement)) {
							List<NamedElement> elements = getNamedElements();
							if (!elements.contains(namedElement)) { 	// FIXME use a set ?
								elements.add(namedElement);
							}
						}
					}
				}
			}
			return this;
		}
		
		@Override
		@NonNull
		public <NE extends NamedElement > LookupEnvironment addElements(
				@Nullable Collection<NE> namedElements) {
			
			if (namedElements != null) {
				for (NamedElement namedElement : namedElements) {
					addElement(namedElement);
				}	
			}
			return this;
		}
		
		@SuppressWarnings("unchecked")
		public <NE extends NamedElement> List<NE> getNamedElementsByKind(Class<NE> class_) {
			List<NE> result = new ArrayList<NE>(); 
			for (NamedElement namedElement : getNamedElements()) {
				if (class_.isAssignableFrom(namedElement.getClass())) {
					result.add((NE)namedElement);
				}
			}
			return result;
		}
	}
			''');
			writer.close();
		}
	}
	
	protected def void generateAutoLookupSolver(GenPackage genPackage, GenPackage basePackage) {
		var EPackage ePackage = genPackage.getEcorePackage;
		var String fqPackageItf = genModelHelper.getQualifiedPackageInterfaceName(ePackage)
		var List<Operation> lookupOps = basePackage.lookupMethods;
		var boolean isDerived = isDerived();
		var String className = projectPrefix + "LookupSolver";
		var String superClassName = superProjectPrefix + "LookupSolver";
		var MergeWriter writer = new MergeWriter(lookupArtifactsOutputFolder + className + ".java");
		writer.append('''
	«ePackage.generateHeaderWithTemplate(lookupArtifactsJavaPackage)»

	import org.eclipse.jdt.annotation.NonNull;
	import org.eclipse.ocl.pivot.evaluation.Executor;
	«FOR typeName : lookupOps.getLookupTypeNames("Unqualified")»
	import «visitorPackageName».«projectPrefix»Unqualified«typeName»LookupVisitor;
	«ENDFOR»
	«FOR typeName : lookupOps.getLookupTypeNames("Exported")»
	import «visitorPackageName».«projectPrefix»Exported«typeName»LookupVisitor;
	«ENDFOR»
	«FOR typeName : lookupOps.getLookupTypeNames("Qualified")»
	import «visitorPackageName».«projectPrefix»Qualified«typeName»LookupVisitor;
	«ENDFOR»
	
	«IF isDerived»
	import «superLookupPackageName».util.«superClassName»;
	import «baseLookupPackageName».util.«basePackage.prefix»LookupResult;
	import «baseLookupPackageName».util.«basePackage.prefix»LookupResultImpl;
	import «baseLookupPackageName».util.«basePackage.prefix»SingleResultLookupEnvironment;
	«ENDIF»
	
	public class «className»«IF isDerived» extends «superClassName»«ENDIF» {
		
		«IF !isDerived»protected final @NonNull Executor executor;
		
		«ENDIF»
		public «className» (@NonNull Executor executor) {
			«IF isDerived»
			super(executor);
			«ELSE»
			this.executor = executor;
			«ENDIF»
		}
		
		«FOR op : lookupOps»
		«val opName = op.name»
		«val isExportedLookup = op.exportedLookupOperation»
		«val lookupVisitorName = op.getLookupVisitorName(op.type.name)»
		«val hasAdditionalFilter = op.hasAdditionalFilterArgs»
		«val lookupVars = op.getLookupArgs»
		«val typeFQName = getTypeFQName(op.type)»
		«val typeLiteral = getTypeLiteral(op.type)»
		
		public «basePackage.prefix»LookupResult<«typeFQName»> «opName»(«getTypeFQName(op.owningClass)» context«FOR param:op.ownedParameters», «getTypeFQName(param.type)» «param.name»«ENDFOR») {
			«IF hasAdditionalFilter»
			«op.type.name»Filter filter = new «op.type.name»Filter(executor, «op.getFilterArgs»);
			«ENDIF»
			«basePackage.prefix»SingleResultLookupEnvironment _lookupEnv = new «basePackage.prefix»SingleResultLookupEnvironment(executor, «fqPackageItf».Literals.«typeLiteral»«lookupVars»);
			«lookupVisitorName» _lookupVisitor = new «lookupVisitorName»(_lookupEnv«IF isExportedLookup», importer«ENDIF»);
			context.accept(_lookupVisitor);
			return new «basePackage.prefix»LookupResultImpl<«typeFQName»>
					(_lookupEnv.getNamedElementsByKind(«typeFQName».class));
		}
		«ENDFOR»
	}
		''');
		writer.close();
	}
		
	protected def void generateAutoCommonLookupVisitor(EPackage ePackage) {
		var String fqPackageItf = genModelHelper.getQualifiedPackageInterfaceName(ePackage)

		var boolean isDerived = isDerived();
		var String visitorName = '''Abstract«projectPrefix»CommonLookupVisitor''';
		var String superVisitorName = '''Abstract«superProjectPrefix»CommonLookupVisitor''';
		var String superClassName = '''AbstractExtending«IF isDerived»«projectPrefix»«ENDIF»Visitor'''
		var MergeWriter writer = new MergeWriter(outputFolder + visitorName + ".java");
		writer.append('''
	«ePackage.generateHeaderWithTemplate(visitorPackageName)»
	import org.eclipse.jdt.annotation.NonNull;
	import org.eclipse.jdt.annotation.Nullable;
	import org.eclipse.ocl.pivot.internal.evaluation.EvaluationCache;
	«IF !isDerived»	
	import org.eclipse.ocl.pivot.internal.evaluation.ExecutorInternal.ExecutorInternalExtension;
	«ENDIF»	
	
	import «baseLookupPackageName».LookupEnvironment;
	import «visitablePackageName».«visitableClassName»;
	«IF isDerived»	
	import «superVisitorPackageName».«superVisitorName»;
	«ENDIF»	
	
	public abstract class «visitorName»
		extends «superClassName»<@Nullable LookupEnvironment, @NonNull LookupEnvironment> {
	
		«IF !isDerived»protected final @NonNull EvaluationCache evaluationCache;«ENDIF»
		«IF isDerived»private «superVisitorName» delegate;«ENDIF»
		
		protected «visitorName»(@NonNull LookupEnvironment context) {
			super(context);
			«IF !isDerived»this.evaluationCache = ((ExecutorInternalExtension)context.getExecutor()).getEvaluationCache();«ENDIF»
			«IF isDerived»this.delegate = createSuperLangVisitor();«ENDIF»
		}
		
		@Override
		public final LookupEnvironment visiting(@NonNull Visitable visitable) {
			«IF isDerived»
			return «fqPackageItf».eINSTANCE == visitable.eClass().getEPackage()
				? doVisiting(visitable)
				: visitable.accept(getSuperLangVisitor());
			«ELSE»
			return doVisiting(visitable);
			«ENDIF»
		}
		
		«IF isDerived»
		protected «superVisitorName» getSuperLangVisitor(){
			if (delegate == null) {
				delegate = createSuperLangVisitor();
			}
			return delegate;
		}
		«ENDIF»
		
		abstract protected LookupEnvironment doVisiting(@NonNull Visitable visitable);
		
		«IF isDerived»abstract protected «superVisitorName»  createSuperLangVisitor();«ENDIF»
	}
		''')
		writer.close();
	}
	
	override generateVisitors(GenPackage genPackage) {		
		genModelHelper = createGenModelHelper(genPackage);
		lookupArtifactsJavaPackage = lookupPackageName + ".util";
		lookupArtifactsOutputFolder = modelFolder + lookupArtifactsJavaPackage.replace('.', '/') + "/";
		
		var EPackage ePackage = genPackage.getEcorePackage();
		
		// Some lookup artefacts
		ePackage.generateAutoLookupResultClass;
		ePackage.generateAutoLookupResultItf;
		ePackage.generateAutoLookupFilterItf;
		ePackage.generateAutoLookupFilterClass;
		ePackage.generateSingleResultLookupEnvironment;
		genPackage.generateAutoLookupSolver(baseGenPackage);
		
		// Visitors related generation
		ePackage.generateAutoCommonLookupVisitor;
		doGenerateVisitors(genPackage);
	}
	
	private def List<Operation> getLookupMethods(GenPackage genPackage) {
		
		var List<Operation> result = new ArrayList<Operation>;
		var EnvironmentFactory envFact = PivotUtilInternal.getEnvironmentFactory(genPackage.getEcorePackage)
		for (oclPackage : LookupCGUtil.getTargetPackages(genPackage, envFact, lookupFilePath, projectName)) {
			for (oclClass : oclPackage.ownedClasses) {
				for (oclOp : oclClass.ownedOperations) {
					if (oclOp.isLookupOperation) {
						result.add(oclOp);
					}
				}
			}
		} 
		return result;
	}
	
	private def Set<String> getLookupTypeNames(List<Operation> lookupOps, String lookupProtocol) {
		
		val result = newHashSet();
		for (op : lookupOps) {
			if (op.name.contains(lookupProtocol)){
				result.add(op.type.name);
			}
		}
		return result;
	}
		
	private def boolean isLookupOperation(Operation op){
		// internal lookup ops...
		if (!op.name.startsWith("_lookup")) {
			return false;
		}
	
		// ...which don't have the env parameter
		// FIXME more robust check of the parameter
		for (param : op.ownedParameters) {
			if ("env".equals(param.name)) {
				return false
			}
		}
		
		return true;
	}
	
	private def boolean isExportedLookupOperation(Operation op) {
		isLookupOperation(op) && op.name.contains("Exported") // FIXME more robust check?
	}
	
	private def GenModelHelper createGenModelHelper(GenPackage genPackage) {
		var PivotMetamodelManager mManager = PivotUtilInternal.getEnvironmentFactory(genPackage.getEcorePackage()).metamodelManager;
		return AbstractGenModelHelper.create(mManager, genPackage.getGenModel());
	}
	
	private def String getTypeLiteral(Type type) {
		var GenClassifier genClassifier = genModelHelper.getGenClassifier(type as Class);
		return ClassUtil.nonNullState(genClassifier).classifierID; 
	}
	
	private def String getTypeFQName(Type type) {
		return if (type instanceof CollectionType) '''java.util.List<«getTypeFQName(type.elementType)»>''' 
			else genModelHelper.getEcoreInterfaceName(type as Class)	
	}
	
	private def String getLookupVisitorName(Operation op, String typeName) {

		if (op.name.contains("Qualified")) '''«projectPrefix»Qualified«typeName»LookupVisitor'''
		else if (op.name.contains("Exported")) '''«projectPrefix»Exported«typeName»LookupVisitor'''
		else '''«projectPrefix»Unqualified«typeName»LookupVisitor''';	
	}

	
	private def boolean hasAdditionalFilterArgs(Operation op) {
		val params = op.ownedParameters;
		if (op.isExportedLookupOperation) params.size() > 2 else params.size() > 1;
	}
	
	private def String getLookupArgs(Operation op) {
		val params = op.ownedParameters
		val nameParam = if (op.isExportedLookupOperation) params.get(1) else params.get(0);
		''',«nameParam.name»«IF op.hasAdditionalFilterArgs»,filter«ENDIF»'''
	}
	
	// We assume we are dealing with the the exported element lookup op
	private def String getFilterArgs(Operation op) {
		val sb = new StringBuffer();
		val filterArgsIndex = if (op.isExportedLookupOperation) 2 else 1;
		val params = op.ownedParameters
		var first=true;
		for (var i = filterArgsIndex; i < params.size; i++) {
			if (first) {
				first = false
			} else {
				sb.append(',');	
			}
			sb.append('''«params.get(i).name»''');
		}
		sb.toString();
	}
	
}
