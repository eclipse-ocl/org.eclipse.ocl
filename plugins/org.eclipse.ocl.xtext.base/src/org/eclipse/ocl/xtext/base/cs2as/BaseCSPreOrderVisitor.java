/*******************************************************************************
 * Copyright (c) 2010, 2015 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.xtext.base.cs2as;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.AnyType;
import org.eclipse.ocl.pivot.DataType;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.LambdaType;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.Parameter;
import org.eclipse.ocl.pivot.ParameterType;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.TemplateParameter;
import org.eclipse.ocl.pivot.TemplateableElement;
import org.eclipse.ocl.pivot.TupleType;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.internal.executor.ExecutorTuplePart;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.xtext.base.utilities.ElementUtil;
import org.eclipse.ocl.xtext.basecs.AnnotationCS;
import org.eclipse.ocl.xtext.basecs.ClassCS;
import org.eclipse.ocl.xtext.basecs.ConstraintCS;
import org.eclipse.ocl.xtext.basecs.DataTypeCS;
import org.eclipse.ocl.xtext.basecs.DocumentationCS;
import org.eclipse.ocl.xtext.basecs.EnumerationCS;
import org.eclipse.ocl.xtext.basecs.EnumerationLiteralCS;
import org.eclipse.ocl.xtext.basecs.LambdaTypeCS;
import org.eclipse.ocl.xtext.basecs.ModelElementCS;
import org.eclipse.ocl.xtext.basecs.ModelElementRefCS;
import org.eclipse.ocl.xtext.basecs.MultiplicityBoundsCS;
import org.eclipse.ocl.xtext.basecs.MultiplicityStringCS;
import org.eclipse.ocl.xtext.basecs.NamedElementCS;
import org.eclipse.ocl.xtext.basecs.OperationCS;
import org.eclipse.ocl.xtext.basecs.PackageCS;
import org.eclipse.ocl.xtext.basecs.PackageOwnerCS;
import org.eclipse.ocl.xtext.basecs.ParameterCS;
import org.eclipse.ocl.xtext.basecs.PathElementCS;
import org.eclipse.ocl.xtext.basecs.PathNameCS;
import org.eclipse.ocl.xtext.basecs.PrimitiveTypeRefCS;
import org.eclipse.ocl.xtext.basecs.StructuralFeatureCS;
import org.eclipse.ocl.xtext.basecs.StructuredClassCS;
import org.eclipse.ocl.xtext.basecs.TemplateBindingCS;
import org.eclipse.ocl.xtext.basecs.TemplateParameterSubstitutionCS;
import org.eclipse.ocl.xtext.basecs.TemplateSignatureCS;
import org.eclipse.ocl.xtext.basecs.TuplePartCS;
import org.eclipse.ocl.xtext.basecs.TupleTypeCS;
import org.eclipse.ocl.xtext.basecs.TypeParameterCS;
import org.eclipse.ocl.xtext.basecs.TypeRefCS;
import org.eclipse.ocl.xtext.basecs.TypedRefCS;
import org.eclipse.ocl.xtext.basecs.TypedTypeRefCS;
import org.eclipse.ocl.xtext.basecs.WildcardTypeRefCS;
import org.eclipse.ocl.xtext.basecs.util.AbstractExtendingBaseCSVisitor;
import org.eclipse.ocl.xtext.basecs.util.VisitableCS;

public class BaseCSPreOrderVisitor extends AbstractExtendingBaseCSVisitor<Continuation<?>, CS2ASConversion>
{
	protected static class ClassSupersContinuation extends SingleContinuation<StructuredClassCS>
	{
		private static Dependency[] computeDependencies(@NonNull CS2ASConversion context, @NonNull StructuredClassCS csElement) {
			List<TypedRefCS> csSuperTypes = csElement.getOwnedSuperTypes();
			if (csSuperTypes.isEmpty()) {
				return null;
			}
			List<Dependency> dependencies = new ArrayList<Dependency>();
			for (TypedRefCS csSuperType : csSuperTypes) {
				dependencies.add(new PivotDependency(csSuperType));
			}
			return dependencies.toArray(new Dependency[dependencies.size()]);
		}

		public ClassSupersContinuation(@NonNull CS2ASConversion context, org.eclipse.ocl.pivot.Class pivotParent, @NonNull StructuredClassCS csElement) {
			super(context, pivotParent, null, csElement, computeDependencies(context, csElement));
		}

		@Override
		public BasicContinuation<?> execute() {
			org.eclipse.ocl.pivot.Class pivotElement = PivotUtil.getPivot(org.eclipse.ocl.pivot.Class.class, csElement);
			if (pivotElement != null) {
				List<org.eclipse.ocl.pivot.Class> superClasses = pivotElement.getSuperClasses();
				context.refreshList(org.eclipse.ocl.pivot.Class.class, superClasses, csElement.getOwnedSuperTypes());
				if (superClasses.isEmpty()) {
					org.eclipse.ocl.pivot.Class oclElementType = context.getStandardLibrary().getOclElementType();
					pivotElement.getSuperClasses().add(oclElementType);
				}
			}
			return null;
		}
	}
	
	protected static class LambdaContinuation extends SingleContinuation<LambdaTypeCS>
	{
		private static Dependency[] computeDependencies(@NonNull CS2ASConversion context, @NonNull LambdaTypeCS csElement) {
			TypedRefCS ownedResultType = ClassUtil.nonNullState(csElement.getOwnedResultType());
			List<TypedRefCS> csParameterTypes = csElement.getOwnedParameterTypes();
			int iMax = csParameterTypes.size();
			Dependency[] dependencies = new Dependency[1 + iMax];
			dependencies[0] = context.createTypeIsReferenceableDependency(ownedResultType);
			for (int i = 0; i < iMax; i++) {
				TypedRefCS csParameterType = ClassUtil.nonNullState(csParameterTypes.get(i));
				dependencies[i+1] = context.createTypeIsReferenceableDependency(csParameterType);
			}
			return dependencies;
		}

		public LambdaContinuation(@NonNull CS2ASConversion context, @NonNull LambdaTypeCS csElement) {
			super(context, null, null, csElement, computeDependencies(context, csElement));
		}

		@Override
		public BasicContinuation<?> execute() {
			TypedRefCS csResultType = csElement.getOwnedResultType();
			Type resultType = PivotUtil.getPivot(Type.class, csResultType);
			String name = csElement.getName();
			if ((resultType != null) && (name != null)) {
				List<ParameterType> parameterTypes = new ArrayList<ParameterType>();
				for (TypedRefCS csParameterType : csElement.getOwnedParameterTypes()) {
					Type parameterType = PivotUtil.getPivot(Type.class, csParameterType);
					if (parameterType != null) {
						parameterTypes.add(PivotUtil.createParameterType(parameterType, ElementUtil.isRequired(csParameterType)));
					}
				}
				ParameterType resultParameterType = PivotUtil.createParameterType(resultType, ElementUtil.isRequired(csResultType));
				LambdaType lambdaType = context.getMetamodelManager().getCompleteModel().getLambdaType(name, parameterTypes, resultParameterType, null);
				context.installPivotTypeWithMultiplicity(lambdaType, csElement);
			}
			return null;
		}
	}

	protected static abstract class OperatorExpContinuation<T extends NamedElementCS> extends SingleContinuation<T>
	{
		public OperatorExpContinuation(@NonNull CS2ASConversion context, @NonNull T csElement) {
			super(context, null, null, csElement);
			context.getOperatorsHavePrecedenceInterDependency().addDependency(this);
		}

		@Override
		public BasicContinuation<?> execute() {
			context.getOperatorsHavePrecedenceInterDependency().setSatisfied(this);
			return null;
		}
	}
	
	protected static class ParameterContinuation extends SingleContinuation<ParameterCS>
	{
		public ParameterContinuation(@NonNull CS2ASConversion context, @NonNull ParameterCS csElement) {
			super(context, null, null, csElement);
		}

		@Override
		public boolean canExecute() {
			if (!super.canExecute()) {
				return false;
			}
			TypedRefCS ownedType = csElement.getOwnedType();
			Element pivot = ownedType != null ? ownedType.getPivot() : null;
			if (pivot == null) {
				return false;
			}
			return true;
		}

		@Override
		public BasicContinuation<?> execute() {
			Parameter parameter = PivotUtil.getPivot(Parameter.class, csElement);
			if (parameter != null) {
				context.refreshRequiredType(parameter, csElement);
				TypedRefCS ownedType = csElement.getOwnedType();
				boolean isTypeof = false;
				if (ownedType  instanceof TypedTypeRefCS) {
					isTypeof = ((TypedTypeRefCS)ownedType).isIsTypeof();
				}
				parameter.setIsTypeof(isTypeof);
			}
			return null;
		}
	}

	protected static class PrimitiveTypeRefContinuation extends TypedRefContinuation<PrimitiveTypeRefCS>
	{		
		public PrimitiveTypeRefContinuation(@NonNull CS2ASConversion context, @NonNull PrimitiveTypeRefCS csElement) {
			super(context, csElement);
		}

		@Override
		public BasicContinuation<?> execute() {
			String name = csElement.getName();
			if (name != null) {
				Type pivotType = context.getStandardLibrary().getLibraryType(name);
				context.installPivotTypeWithMultiplicity(pivotType, csElement);
			}
			return null;
		}
	}
	
	protected static class SpecializedTypeRefContinuation1 extends SingleContinuation<TypedTypeRefCS>
	{
		public SpecializedTypeRefContinuation1(@NonNull CS2ASConversion context, @NonNull TypedTypeRefCS csElement) {
			super(context, null, null, csElement, context.getTypesHaveSignaturesInterDependency());
			assert csElement.getOwnedBinding() != null;
		}

		@Override
		public BasicContinuation<?> execute() {
			@SuppressWarnings("unused")
			Element pivotType = csElement.getReferredType();
			return new SpecializedTypeRefContinuation2(context, csElement);
		}
	}

	protected static class SpecializedTypeRefContinuation2 extends TypedRefContinuation<TypedTypeRefCS>
	{
		private static Dependency[] computeDependencies(@NonNull CS2ASConversion context, @NonNull TypedTypeRefCS csElement) {
			List<Dependency> dependencies = new ArrayList<Dependency>();
			TemplateBindingCS csTemplateBinding = csElement.getOwnedBinding();
			if (csTemplateBinding != null) {
				for (TemplateParameterSubstitutionCS csTemplateParameterSubstitution : csTemplateBinding.getOwnedSubstitutions()) {
					TypeRefCS csTemplateParameter = csTemplateParameterSubstitution.getOwnedActualParameter();
					if (csTemplateParameter != null) {
						Dependency dependency = context.createTypeIsReferenceableDependency(csTemplateParameter);
						if (dependency != null) {
							dependencies.add(dependency);
						}
					}
				}
				for (TemplateParameterSubstitutionCS csTemplateParameterSubstitution : csTemplateBinding.getOwnedSubstitutions()) {
					TypeRefCS csActualParameter = csTemplateParameterSubstitution.getOwnedActualParameter();
					dependencies.add(new PivotDependency(csActualParameter));	// FIXME may be a redundant duplicate
				}
			}
			dependencies.add(new PivotHasSuperClassesDependency(csElement));
			return dependencies.toArray(new Dependency[dependencies.size()]);
		}
		
		public SpecializedTypeRefContinuation2(@NonNull CS2ASConversion context, @NonNull TypedTypeRefCS csElement) {
			super(context, csElement, computeDependencies(context, csElement));
			assert csElement.getOwnedBinding() != null;
		}

		@Override
		public boolean canExecute() {
			if (!super.canExecute()) {
				return false;
			}
			if (context.isInReturnTypeWithUnresolvedParameters(csElement)) {
				return false;
			}
			Type pivotType = csElement.getReferredType();
			if (pivotType instanceof org.eclipse.ocl.pivot.Class) {
				if (((org.eclipse.ocl.pivot.Class)pivotType).getSuperClasses().size() <= 0) {
					return false;
				}
				TemplateBindingCS csTemplateBinding = csElement.getOwnedBinding();
				if (csTemplateBinding != null)  {
					for (TemplateParameterSubstitutionCS csTemplateParameterSubstitution : csTemplateBinding.getOwnedSubstitutions()) {
						TypeRefCS ownedActualParameter = csTemplateParameterSubstitution.getOwnedActualParameter();
						if (ownedActualParameter instanceof WildcardTypeRefCS) {
							return true;
						}
						Type actualParameterClass = (Type) ownedActualParameter.getPivot();
						if (actualParameterClass == null) {
							return false;
						}
					}
				}
			}
			return true;
		}

		@Override
		public BasicContinuation<?> execute() {
			Type pivotType = csElement.getReferredType();
			if (pivotType != null) {
				TemplateBindingCS csTemplateBinding = csElement.getOwnedBinding();
				if ((csTemplateBinding != null) && ElementUtil.isSpecialization(csTemplateBinding)) {
					pivotType = (Type) context.specializeTemplates(csElement);
//					TemplateBinding pivotTemplateBinding = PivotUtil.getPivot(TemplateBinding.class, csTemplateBinding);
//					pivotType = pivotTemplateBinding.getBoundElement();
				}
				if (pivotType != null) {
					context.installPivotTypeWithMultiplicity(pivotType, csElement);
				}
			}
			return null;
		}
	}
	
	protected static class TypeParameterContinuation extends SingleContinuation<TypeParameterCS>
	{
		public TypeParameterContinuation(@NonNull CS2ASConversion context, @NonNull TypeParameterCS csElement) {
			super(context, null, null, csElement);
		}

		@Override
		public boolean canExecute() {
			if (!super.canExecute()) {
				return false;
			}
			for (TypedRefCS csExtend : csElement.getOwnedExtends()) {
				org.eclipse.ocl.pivot.Class asExtend = PivotUtil.getPivot(org.eclipse.ocl.pivot.Class.class, csExtend);
				if (asExtend == null) {
					return false;
				}
			}
			return true;
		}

		@Override
		public BasicContinuation<?> execute() {
			TemplateParameter pivotElement = PivotUtil.getPivot(TemplateParameter.class, csElement);
			if (pivotElement != null) {
				List<TypedRefCS> csExtends = csElement.getOwnedExtends();
				List<org.eclipse.ocl.pivot.Class> asExtends = new ArrayList<org.eclipse.ocl.pivot.Class>();
				for (TypedRefCS csExtend : csExtends) {
					org.eclipse.ocl.pivot.Class asExtend = PivotUtil.getPivot(org.eclipse.ocl.pivot.Class.class, csExtend);
					if (asExtend != null) {
						asExtends.add(asExtend);
					}
				}
				PivotUtilInternal.refreshList(pivotElement.getConstrainingClasses(), asExtends);
			}
			return null;
		}
	}

	protected static class TemplateSignatureContinuation extends SingleContinuation<ClassCS>
	{
		public TemplateSignatureContinuation(@NonNull CS2ASConversion context, NamedElement pivotParent, @NonNull ClassCS csElement) {
			super(context, pivotParent, PivotPackage.Literals.TEMPLATEABLE_ELEMENT__OWNED_SIGNATURE, csElement);
			context.getTypesHaveSignaturesInterDependency().addDependency(this);
		}

		@Override
		public BasicContinuation<?> execute() {
			Type pivotElement = PivotUtil.getPivot(Type.class, csElement);
			if (pivotElement != null) {
				if (pivotElement instanceof TemplateableElement) {
					context.refreshTemplateSignature(csElement, (TemplateableElement)pivotElement);
				}
				context.getTypesHaveSignaturesInterDependency().setSatisfied(this);
			}
			return null;
		}
	}
	
	protected static class TupleContinuation extends TypedRefContinuation<TupleTypeCS>
	{
		public TupleContinuation(@NonNull CS2ASConversion context, @NonNull TupleTypeCS csElement) {
			super(context, csElement);
		}

		@Override
		public boolean canExecute() {
			if (!super.canExecute()) {
				return false;
			}
			for (TuplePartCS csTuplePart : csElement.getOwnedParts()) {
				TypedRefCS ownedType = csTuplePart.getOwnedType();
				Element pivot = ownedType.getPivot();
				if (pivot == null) {
					return false;
				}
			}
			if (context.isInReturnTypeWithUnresolvedParameters(csElement)) {
				return false;
			}
			return true;
		}

		@Override
		public BasicContinuation<?> execute() {
			String name = csElement.getName();
			if (name != null) {
				List<TypedElement> parts = new ArrayList<TypedElement>();
				for (@SuppressWarnings("null")@NonNull TuplePartCS csTuplePart : csElement.getOwnedParts()) {
					String partName = csTuplePart.getName();
					if (partName != null) {
						Type partType = PivotUtil.getPivot(Type.class, csTuplePart.getOwnedType());
						if (partType != null) {
							parts.add(new ExecutorTuplePart(partType, partName));
						}
					}
				}
				TupleType tupleType = context.getMetamodelManager().getCompleteModel().getTupleType(name, parts, null);
				context.installPivotTypeWithMultiplicity(tupleType, csElement);
				List<Property> tupleParts = tupleType.getOwnedProperties();
				for (TuplePartCS csTuplePart : csElement.getOwnedParts()) {
					String partName = csTuplePart.getName();
					Property tuplePart = NameUtil.getNameable(tupleParts, partName);
					if (tuplePart != null) {
						context.installPivotUsage(csTuplePart, tuplePart);
					}
				}
			}
			return null;
		}
	}

	protected static abstract class TypedRefContinuation<T extends TypedRefCS> extends SingleContinuation<T>
	{
		public TypedRefContinuation(@NonNull CS2ASConversion context, @NonNull T csElement, Dependency... dependencies) {
			super(context, null, null, csElement);
		}
	}

	protected static class UnspecializedTypeRefContinuation extends TypedRefContinuation<TypedTypeRefCS>
	{
		public UnspecializedTypeRefContinuation(@NonNull CS2ASConversion context, @NonNull TypedTypeRefCS csElement) {
			super(context, csElement, context.getTypesHaveSignaturesInterDependency());
			assert csElement.getOwnedBinding() == null;
		}

		@Override
		public BasicContinuation<?> execute() {
			Type pivotType = csElement.getReferredType();
			context.installPivotTypeWithMultiplicity(pivotType, csElement);
			return null;
		}
	}

	public BaseCSPreOrderVisitor(@NonNull CS2ASConversion context) {
		super(context);
	}

	@Override
	public Continuation<?> visiting(@NonNull VisitableCS visitable) {
		throw new IllegalArgumentException("Unsupported " + visitable.eClass().getName() + " for CS2AS PreOrder pass");
	}

	@Override
	public Continuation<?> visitAnnotationCS(@NonNull AnnotationCS object) {
		return null;
	}

	@Override
	public Continuation<?> visitConstraintCS(@NonNull ConstraintCS csConstraint) {
		return null;
	}

	@Override
	public Continuation<?> visitDataTypeCS(@NonNull DataTypeCS csDataType) {
		DataType pivotElement = PivotUtil.getPivot(DataType.class, csDataType);
		if (pivotElement != null) {
			List<org.eclipse.ocl.pivot.Class> pivotSuperClasses = pivotElement.getSuperClasses();
			pivotSuperClasses.clear();
			org.eclipse.ocl.pivot.Class oclElementType = context.getStandardLibrary().getOclElementType();
			pivotSuperClasses.add(oclElementType);
		}
		return null;
	}

	@Override
	public Continuation<?> visitDocumentationCS(@NonNull DocumentationCS object) {
		return null;
	}

	@Override
	public Continuation<?> visitEnumerationCS(@NonNull EnumerationCS csEnumeration) {
		org.eclipse.ocl.pivot.Enumeration pivotElement = PivotUtil.getPivot(org.eclipse.ocl.pivot.Enumeration.class, csEnumeration);
		if (pivotElement != null) {
			List<org.eclipse.ocl.pivot.Class> pivotSuperClasses = pivotElement.getSuperClasses();
			pivotSuperClasses.clear();
			org.eclipse.ocl.pivot.Class oclElementType = context.getStandardLibrary().getOclElementType();
			pivotSuperClasses.add(oclElementType);
		}
		return null;
	}

	@Override
	public Continuation<?> visitEnumerationLiteralCS(@NonNull EnumerationLiteralCS csEnumerationLiteral) {
		return null;
	}

	@Override
	public Continuation<?> visitLambdaTypeCS(@NonNull LambdaTypeCS csLambdaType) {
		return new LambdaContinuation(context, csLambdaType);
	}

	@Override
	public Continuation<?> visitModelElementCS(@NonNull ModelElementCS csModelElement) {
		return null;
	}

	@Override
	public Continuation<?> visitModelElementRefCS(@NonNull ModelElementRefCS csModelElementRef) {
		return null;
	}

	@Override
	public Continuation<?> visitMultiplicityBoundsCS(@NonNull MultiplicityBoundsCS object) {
		return null;
	}

	@Override
	public Continuation<?> visitMultiplicityStringCS(@NonNull MultiplicityStringCS object) {
		return null;
	}

	@Override
	public Continuation<?> visitOperationCS(@NonNull OperationCS csOperation) {
		return null;
	}
	
	@Override
	public Continuation<?> visitPackageCS(@NonNull PackageCS csPackage) {
		return null;
	}
	
	@Override
	public Continuation<?> visitPackageOwnerCS(@NonNull PackageOwnerCS object) {
		return null;
	};

	@Override
	public Continuation<?> visitParameterCS(@NonNull ParameterCS csParameter) {
		return new ParameterContinuation(context, csParameter);
	}

	@Override
	public Continuation<?> visitPathElementCS(@NonNull PathElementCS csElement) {
		return null;
	}

	@Override
	public Continuation<?> visitPathNameCS(@NonNull PathNameCS csElement) {
		return null;
	}

	@Override
	public Continuation<?> visitPrimitiveTypeRefCS(@NonNull PrimitiveTypeRefCS csPrimitiveTypeRef) {
		return new PrimitiveTypeRefContinuation(context, csPrimitiveTypeRef);
	}

	@Override
	public Continuation<?> visitStructuredClassCS(@NonNull StructuredClassCS csClass) {
		org.eclipse.ocl.pivot.Class pivotElement = PivotUtil.getPivot(org.eclipse.ocl.pivot.Class.class, csClass);
		if (pivotElement == null) {
			return null;
		}
		Continuations continuations = new Continuations();
		if (csClass.getOwnedSignature() != null) {
			continuations.add(new TemplateSignatureContinuation(context, pivotElement, csClass));
		}
		else {
			pivotElement.setOwnedSignature(null);
		}
		if (!(pivotElement instanceof AnyType)) {
			continuations.add(new ClassSupersContinuation(context, pivotElement, csClass));
		}
		return continuations.getContinuation();
	}

	@Override
	public Continuation<?> visitStructuralFeatureCS(@NonNull StructuralFeatureCS csStructuralFeature) {
		return null;
	}

	@Override
	public Continuation<?> visitTemplateBindingCS(@NonNull TemplateBindingCS csTemplateBinding) {
		return null;
	}

	@Override
	public Continuation<?> visitTemplateSignatureCS(@NonNull TemplateSignatureCS csTemplateSignature) {
		return null;
	}

	@Override
	public Continuation<?> visitTupleTypeCS(@NonNull TupleTypeCS csTupleType) {
		return new TupleContinuation(context, csTupleType);
	}

	@Override
	public Continuation<?> visitTypeParameterCS(@NonNull TypeParameterCS csTypeParameter) {
		TemplateParameter pivotElement = PivotUtil.getPivot(TemplateParameter.class, csTypeParameter);
		if (pivotElement == null) {
			return null;
		}
		if (csTypeParameter.getOwnedExtends().size() > 0) {
			return new TypeParameterContinuation(context, csTypeParameter);
		}
		else {
			pivotElement.getConstrainingClasses().clear();
			return null;
		}
	}

	@Override
	public Continuation<?> visitTypedTypeRefCS(@NonNull TypedTypeRefCS csTypedTypeRef) {
		if (csTypedTypeRef.getOwnedBinding() == null) {
			return new UnspecializedTypeRefContinuation(context, csTypedTypeRef);
		}
		else {
			return new SpecializedTypeRefContinuation1(context, csTypedTypeRef);
		}
	}

	@Override
	public Continuation<?> visitWildcardTypeRefCS(@NonNull WildcardTypeRefCS csWildcardTypeRef) {
		return null;
	}
}