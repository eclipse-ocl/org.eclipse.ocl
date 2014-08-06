package org.eclipse.ocl.examples.pivot.lookup;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.domain.elements.DomainNamedElement;
import org.eclipse.ocl.examples.domain.elements.DomainPackage;
import org.eclipse.ocl.examples.domain.elements.FeatureFilter;
import org.eclipse.ocl.examples.pivot.DataType;
import org.eclipse.ocl.examples.pivot.Element;
import org.eclipse.ocl.examples.pivot.Enumeration;
import org.eclipse.ocl.examples.pivot.ExpressionInOCL;
import org.eclipse.ocl.examples.pivot.IterateExp;
import org.eclipse.ocl.examples.pivot.IteratorExp;
import org.eclipse.ocl.examples.pivot.LetExp;
import org.eclipse.ocl.examples.pivot.Library;
import org.eclipse.ocl.examples.pivot.LoopExp;
import org.eclipse.ocl.examples.pivot.Metaclass;
import org.eclipse.ocl.examples.pivot.OCLExpression;
import org.eclipse.ocl.examples.pivot.Operation;
import org.eclipse.ocl.examples.pivot.Package;
import org.eclipse.ocl.examples.pivot.PivotPackage;
import org.eclipse.ocl.examples.pivot.Root;
import org.eclipse.ocl.examples.pivot.TemplateableElement;
import org.eclipse.ocl.examples.pivot.Type;
import org.eclipse.ocl.examples.pivot.UnspecifiedType;
import org.eclipse.ocl.examples.pivot.Variable;
import org.eclipse.ocl.examples.pivot.manager.MetaModelManager;
import org.eclipse.ocl.examples.pivot.manager.PackageManager;
import org.eclipse.ocl.examples.pivot.manager.PackageServer;
import org.eclipse.ocl.examples.pivot.manager.TypeServer;
import org.eclipse.ocl.examples.pivot.util.Pivotable;
import org.eclipse.ocl.examples.pivot.utilities.PivotUtil;

/**
 * @since 3.5
 */
public class NewPivotLookupVisitor extends AutoPivotLookupVisitor{
		
	protected final  MetaModelManager mmManager;
	public NewPivotLookupVisitor(@NonNull MetaModelManager mmManager, @NonNull Environment env) {
		super(env);
		this.mmManager = mmManager;
	}
	
//	@Override
//	public @NonNull
//	Environment visitOperation(@NonNull Operation object) {		
//		if (object.getOwnedParameter().contains(child)) {
//			
//		}
//		else {
//			addOperation0_ParameterElements(object);
//		}
//		addTypeTemplateParameterables(object);		
//		return lookupInParentIfEnvNoComplete(object);
//	}
//
//	@Override
//	public @NonNull
//	Environment visitClass(@NonNull org.eclipse.ocl.examples.pivot.Class object) {
//		
//		assert !(object instanceof Metaclass<?>);
//		if (object.getOwningTemplateParameter() != null) {
//			org.eclipse.ocl.examples.pivot.Class type = mmManager.getOclAnyType(); // WIP use lowerbound
//			addClass1_OperationElements(type);
//			addClass2_PropertyElements(type);
//			addClass0_BehaviorElements(type);
//			return context;
//		}
//		if (object.getTemplateBinding().size() == 0) {
//			EObject scopeTarget = child;
//			if (scopeTarget instanceof Pivotable) {
//				Element pivot = ((Pivotable)scopeTarget).getPivot();
//				if (pivot == object) {		// Inherited template parameters are invisible.
//					context.addElements(PivotUtil.getTypeTemplateParameterables(object));
//				}
//			}
//		}
//		addClass1_OperationElements(object);
//		addClass2_PropertyElements(object);
//		addClass0_BehaviorElements(object);
//		return lookupInParentIfEnvNoComplete(object);
//	}
//	
//	@Override
//	public @NonNull
//	Environment visitDataType(@NonNull DataType object) {
//		context.addElements(PivotUtil.getTypeTemplateParameterables(object));
//		Type behavioralType = object.getBehavioralType();
//		if (behavioralType == null) {
//			return visitClass(object);
//		} else {
//			if (! (behavioralType instanceof DataType)) { // FIXME
//				Environment env = behavioralType.accept(this);
//				assert(env != null);
//				return env;
//			} else {
//				return lookupInParentIfEnvNoComplete(object);
//			}
//		}
//	};
//
//	@Override
//	public @NonNull
//	Environment visitEnumeration(@NonNull Enumeration object) {
//		addEnumeration3_EnumerationLiteralElements(object);
//		addEnumeration2_PropertyElements(object);
//		addEnumeration1_OperationElements(object);
//		addEnumeration0_BehaviorElements(object);
//		addTypeTemplateParameterables(object);
//		return lookupInParentIfEnvNoComplete(object);
//	}
//	
//	@Override
//	public @NonNull
//	Environment visitPackage(@NonNull Package object) {
//		Set<Package> allPackages = new HashSet<Package>();
//		gatherAllPackages(mmManager, allPackages, object);
//		for (@SuppressWarnings("null")@NonNull Package aPackage : allPackages) {
//			addPackage0_TypeElements(aPackage);
//			addPackage1_PackageElements(aPackage);
//		}
//		return lookupInParentIfEnvNoComplete(object);
//	}
//
//	private void gatherAllPackages(@NonNull MetaModelManager metaModelManager, @NonNull Set<org.eclipse.ocl.examples.pivot.Package> allPackages,
//				@NonNull org.eclipse.ocl.examples.pivot.Package targetPackage) {
//			org.eclipse.ocl.examples.pivot.Package primaryPackage = metaModelManager.getPrimaryElement(targetPackage);
//		if (allPackages.add(primaryPackage)) {
//			for (@SuppressWarnings("null")@NonNull DomainPackage partialPackage : metaModelManager.getPartialPackages(primaryPackage, false)) {
//				if (partialPackage instanceof org.eclipse.ocl.examples.pivot.Package) {
//					for (@SuppressWarnings("null")@NonNull org.eclipse.ocl.examples.pivot.Package importedPackage : ((org.eclipse.ocl.examples.pivot.Package)partialPackage).getImportedPackage()) {
//						gatherAllPackages(metaModelManager, allPackages, importedPackage);
//					}
//				}
//			}
//		}
//	}
//	
//	@Override
//	public @NonNull
//	Environment visitUnspecifiedType(@NonNull UnspecifiedType object) {
//		return lookupFromNewElement(object.getLowerBound());
//	}
//	
//
//
//	@Override
//	public @NonNull
//	Environment visitLibrary(@NonNull Library object) {
//		addLibrary0_PrecedenceElements(object);
//		Environment env =  super.visitLibrary(object);
//		assert(env != null);
//		return env;
//	}
//	
//	@Override
//	public @NonNull
//	Environment visitMetaclass(@NonNull Metaclass object) {
//		addMetaclass0_NamedElementElements(object);
//		addMetaclass1_NamedElementElements(object);		
//		return lookupInParentIfEnvNoComplete(object);
//	}
//	
//	@Override
//	public @NonNull
//	Environment visitIterateExp(@NonNull IterateExp object) {
//
//		if (child != null) {
//			@NonNull Element child2 = child;
//			if (child2.equals(object.getBody())) {
//				OCLExpression source = object.getSource();
//				lookupFromNewElement(source.getType());
//				addIterateExp0_VariableElements(object);
//				addIterateExp1_VariableElement(object);
//				
//			}
//			else if (child2.equals(object.getResult())) {
//				OCLExpression source = object.getSource();
//				addIterateExp2_VariableElements(object);
//				lookupFromNewElement(source.getType());
//			}
//			else if (object.getIterator().contains(child2)) {
//				OCLExpression source = object.getSource();
//				lookupFromNewElement(source.getType());
//				int childIndex = object.getIterator().indexOf(child2);
//				addIterateExp3_VariableElements(object, childIndex);
//			}
//		}
//		
//		return lookupInParentIfEnvNoComplete(object);
//	}
//
//	@Override
//	public @NonNull
//	Environment visitIteratorExp(@NonNull IteratorExp object) {
//		
//		if (child != null) {
//			@NonNull Element child2 = child;
//			if (child2.equals(object.getBody())) {
//				OCLExpression source = object.getSource();
//				lookupFromNewElement(source.getType());
//				addIteratorExp0_VariableElements(object);
//			}
//			else if (object.getIterator().contains(child2)) {
//				OCLExpression source = object.getSource();
//				lookupFromNewElement(source.getType());
//				
//				int childIndex = object.getIterator().indexOf(child2);
//				addIteratorExp1_VariableElements(object, childIndex);
//			}
//		}
//		return lookupInParentIfEnvNoComplete(object);
//	}
//	
////  This is not used by test cases
////	@Override
////	public @NonNull
////	AutoIPivotLookupenv<C> visitLetExp(@NonNull LetExp object) {
////		EStructuralFeature containmentFeature = context.getToChildReference();
////		if (containmentFeature == PivotPackage.Literals.LET_EXP__IN) {
////			env.addVariable(object);
////		}
////		return lookupInParentIfEnvNoComplete();
////	}
//	
//	@Override
//	public @NonNull
//	Environment visitExpressionInOCL(@NonNull ExpressionInOCL object) {
//		Variable contextVariable = object.getContextVariable();
//		if (contextVariable != null) {
//			lookupFromNewElement(contextVariable.getType());
//		}
//		addExpressionInOCL0_VariableElement(object);
//		addExpressionInOCL1_VariableElement(object);
//		return lookupInParentIfEnvNoComplete(object);
//	};
//	
//	
//	/* TEMPORAL METHODS TO VERIFY CORRECT LOOKUPO VISITOR. THIS WILL BE REMOVED*/
//	
//	public void addOperation0_ParameterElements(@NonNull Operation object) {
//		addOwnedParameter(object);
//	}
//	
//	// FIXME remove when Auto-generation is finished
//	private  void addOwnedParameter(@NonNull Operation object) {
//		context.addElements(object.getOwnedParameter());
//	}
//			
//	public void addTypeTemplateParameterables(@NonNull TemplateableElement object) {
//		context.addElements(PivotUtil.getTypeTemplateParameterables(object));
//	}
//
//	public void addEnumeration3_EnumerationLiteralElements(
//			@NonNull Enumeration object) {
//		addOwnedLiteral(object);
//	}
//	
//	// FIXME remove when Auto-generation is finished
//	private void addOwnedLiteral(@NonNull Enumeration object) {
//		context.addElements(object.getOwnedLiteral());
//	}
//	
//	public void addClass1_OperationElements(
//			@NonNull org.eclipse.ocl.examples.pivot.Class object) {
//		addOwnedOperation(object, FeatureFilter.SELECT_NON_STATIC);
//	}
//	
//	public void addMetaclass0_NamedElementElements(@NonNull Metaclass object) {
//		Type instanceType = object.getInstanceType();
//		if (instanceType != null) {
//			addOwnedOperation(instanceType, null);
//		}
//	}
//	
//	public void addEnumeration1_OperationElements(@NonNull Enumeration object) {
//		addOwnedOperation(object, FeatureFilter.SELECT_NON_STATIC);
//	}
//	
//	// FIXME remove when Auto-generation is finished
//	private void addOwnedOperation(@NonNull Type type, @Nullable FeatureFilter featureFilter) {
//			assert mmManager.isTypeServeable(type);
//			type = PivotUtil.getUnspecializedTemplateableElement(type);
//			TypeServer typeServer = mmManager.getTypeServer(type);
//			addElements(typeServer.getAllOperations(featureFilter, getName()));
//			//: typeServer.getAllOperations(featureFilter));
//	}
//	
//	public void addClass2_PropertyElements(
//			@NonNull org.eclipse.ocl.examples.pivot.Class object) {
//		addOwnedProperty(object, FeatureFilter.SELECT_NON_STATIC);
//	}
//	
//	public void addMetaclass1_NamedElementElements(@NonNull Metaclass object) {
//		Type instanceType = object.getInstanceType();
//		if (instanceType != null) {
//			addOwnedProperty(object, null);
//		}
//	}
//	
//	public void addEnumeration2_PropertyElements(@NonNull Enumeration object) {
//		addOwnedProperty(object, FeatureFilter.SELECT_NON_STATIC);
//	}
//	
//	// FIXME remove when Auto-generation is finished
//	private void addOwnedProperty(@NonNull Type type, @Nullable FeatureFilter featureFilter) {
//			assert mmManager.isTypeServeable(type);
//			TypeServer typeServer = mmManager.getTypeServer(type);
//			addElements(typeServer.getAllProperties(featureFilter, getName()));
//			// : typeServer.getAllProperties(featureFilter));
//	}
//	
//	public void addClass0_BehaviorElements(
//			@NonNull org.eclipse.ocl.examples.pivot.Class object) {
//		addOwnedBehavior(object);
//	}
//		
//	public void addEnumeration0_BehaviorElements(@NonNull Enumeration object) {
//		addOwnedBehavior(object);
//	}
//	
//	// FIXME remove when Auto-generation is finished
//	private void addOwnedBehavior(@NonNull org.eclipse.ocl.examples.pivot.Class aClass) {
//			assert mmManager.isTypeServeable(aClass);
//			TypeServer typeServer = mmManager.getTypeServer(aClass);
//			addElements(typeServer.getAllStates(getName()));
//			//: typeServer.getAllStates());
//	}
//
//	public void addPackage1_PackageElements(@NonNull Package object) {
//		addNestedPackage(object);
//	}
//
//	// FIXME remove when Auto-generation is finished
//	private void addNestedPackage(@NonNull Package pkge) {
//			PackageServer parentPackageServer = mmManager.getPackageServer(pkge);
//			PackageServer packageServer = parentPackageServer.getMemberPackage(getName());
//			if (packageServer != null) {
//				context.addElement(packageServer);
//			}
//	}
//	
//	public void addPackage0_TypeElements(@NonNull Package object) {
//		addOwnedType(object);
//	}
//	
//	// FIXME remove when Auto-generation is finished
//	private void addOwnedType(@NonNull Package pkge) {
//		PackageServer packageServer = mmManager.getPackageServer(pkge);
//		Type type = packageServer.getMemberType(getName());
//		if (type != null) {
//			context.addElement(type);
//		}
//	}
//	
//	public void addRoot0_PackageElements(@NonNull Root object) {
//		addNestedPackage(object);
//		
//	}
//	
//	// FIXME remove when Auto-generation is finished
//	private void addNestedPackage(@NonNull Root root) {
//			addElements(root.getNestedPackage());
//	
//	}
//	
//	public void addRoot1_ImportElements(@NonNull Root object) {
//		addRootPackages();
//	}
//	
//	// FIXME remove when Auto-generation is finished
//	private void addRootPackages() {
//		PackageManager packageManager = mmManager.getPackageManager();
//		context.addElement(packageManager.getMemberPackage(getName()));
//		context.addElement(packageManager.getPackageByURI(getName()));
//	}
//	
//	public void addLibrary0_PrecedenceElements(@NonNull Library object) {
//		addOwnedPrecedence(object);
//	}
//	
//	// FIXME remove when Auto-generation is finished
//	private void addOwnedPrecedence(@NonNull Library library) {
//			addElements(library.getOwnedPrecedence());
//	}
//	
//	public void addLibrary1_TypeElements(@NonNull Library object) {
//		addOwnedType(object);
//	}
//	
//		public void addLibrary2_PackageElements(@NonNull Library object) {
//		addNestedPackage(object);
//	}
//		
//	public void addIterateExp0_VariableElements(@NonNull IterateExp object) {
//		addIterator(object);
//	}
//	
//	public void addIterateExp2_VariableElements(@NonNull IterateExp object) {
//		addIterator(object);
//	}
//	
//	public void addIteratorExp0_VariableElements(@NonNull IteratorExp object) {
//		addIterator(object);		
//	}
//	
//	// FIXME remove when Auto-generation is finished
//	private void addIterator(@NonNull LoopExp  aLoopExp) {
//		addElements(aLoopExp.getIterator());
//	}
//	
//	public void addIterateExp3_VariableElements(@NonNull IterateExp object,
//			int childIndex) {
//		addIterator(object, childIndex);
//	}
//	
//	public void addIteratorExp1_VariableElements(@NonNull IteratorExp object,
//			int childIndex) {
//		addIterator(object, childIndex);
//	}
//	
//	// FIXME remove when Auto-generation is finished
//	private void addIterator(@NonNull LoopExp  aLoopExp, int index) {
//		
//		// self.iterator->select(x| self.iterator->indexOf(x) < index)		
//		for (int i = 0; i <= index -1; i++) { 
//			context.addElement(aLoopExp.getIterator().get(i));
//		}
//	}
//	
//	public void addIterateExp1_VariableElement(@NonNull IterateExp object) {
//		addResult(object);
//	}
//	
//	// FIXME remove when Auto-generation is finished
//	private void addResult(@NonNull IterateExp iterateExp) {
//		context.addElement(iterateExp.getResult());
//	}
//		
//	public void addLetExp0_VariableElement(@NonNull LetExp object) {
//		addVariable(object);		
//	}
//	
//	private void addVariable(@NonNull LetExp  letExp) {
//		context.addElement(letExp.getVariable());
//	}
//	
//	public void addExpressionInOCL0_VariableElement(
//			@NonNull ExpressionInOCL object) {
//		addContextVariable(object);		
//	}
//	
//	// FIXME remove when Auto-generation is finished
//	private void addContextVariable(@NonNull ExpressionInOCL expressionInOCL) {
//		context.addElement(expressionInOCL.getContextVariable());
//	}
//
//	public void addExpressionInOCL1_VariableElement(
//			@NonNull ExpressionInOCL object) {
//		addResultVariable(object);
//		
//	}
//	
//	// FIXME remove when Auto-generation is finished
//	private void addResultVariable(@NonNull ExpressionInOCL expressionInOCL) {
//		context.addElement(expressionInOCL.getResultVariable());
//	}
//	
//	
//	
//	private String getName() {
//		return ((SingleResultEnvironment)context).getName();
//	}
//	
//	protected void addElements(@Nullable Iterable<? extends DomainNamedElement> elements) {
//		if (elements != null) {
//			for (DomainNamedElement element : elements) {
//				context.addElement(element);
//			}
//		}
//	}
//	
//	@NonNull
//	protected Environment lookupInParentIfEnvNoComplete(@NonNull Element object) {
//		if (context.hasFinalResult()) {
//			return context;
//		} else {
//			Environment env = this.parentEnv(object);
//			assert(env != null);
//			return env;
//		}
//	}
//	
//	@NonNull
//	protected Environment lookupFromNewElement(Element object) {
//		Environment env =  object.accept(this);
//		assert(env != null);
//		return env;
//	}
}
