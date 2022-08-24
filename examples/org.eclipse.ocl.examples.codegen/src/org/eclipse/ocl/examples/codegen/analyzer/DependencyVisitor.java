/*******************************************************************************
 * Copyright (c) 2013, 2018 CEA LIST and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink(CEA LIST) - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.analyzer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCollectionExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCollectionPart;
import org.eclipse.ocl.examples.codegen.cgmodel.CGConstantExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGElementId;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIterator;
import org.eclipse.ocl.examples.codegen.cgmodel.CGMapExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGMapPart;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNamedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGShadowPart;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTupleExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTuplePart;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTypeId;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGVariable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGVariableExp;
import org.eclipse.ocl.examples.codegen.cgmodel.util.AbstractExtendingCGModelVisitor;
import org.eclipse.ocl.examples.codegen.cse.GlobalPlace;
import org.eclipse.ocl.examples.codegen.cse.SimpleAnalysis;
import org.eclipse.ocl.pivot.ids.BindingsId;
import org.eclipse.ocl.pivot.ids.ClassId;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.ids.DataTypeId;
import org.eclipse.ocl.pivot.ids.ElementId;
import org.eclipse.ocl.pivot.ids.EnumerationId;
import org.eclipse.ocl.pivot.ids.EnumerationLiteralId;
import org.eclipse.ocl.pivot.ids.IdVisitor;
import org.eclipse.ocl.pivot.ids.LambdaTypeId;
import org.eclipse.ocl.pivot.ids.MapTypeId;
import org.eclipse.ocl.pivot.ids.NestedPackageId;
import org.eclipse.ocl.pivot.ids.NsURIPackageId;
import org.eclipse.ocl.pivot.ids.OclInvalidTypeId;
import org.eclipse.ocl.pivot.ids.OclVoidTypeId;
import org.eclipse.ocl.pivot.ids.OperationId;
import org.eclipse.ocl.pivot.ids.PrimitiveTypeId;
import org.eclipse.ocl.pivot.ids.PropertyId;
import org.eclipse.ocl.pivot.ids.RootPackageId;
import org.eclipse.ocl.pivot.ids.SpecializedId;
import org.eclipse.ocl.pivot.ids.TemplateBinding;
import org.eclipse.ocl.pivot.ids.TemplateParameterId;
import org.eclipse.ocl.pivot.ids.TemplateableTypeId;
import org.eclipse.ocl.pivot.ids.TuplePartId;
import org.eclipse.ocl.pivot.ids.TupleTypeId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.ids.UnspecifiedId;
import org.eclipse.ocl.pivot.ids.WildcardId;
import org.eclipse.ocl.pivot.utilities.ClassUtil;

/**
 * Traverses the AST adding any internode dependencies to ensure correct declaration ordering.
 */
public class DependencyVisitor extends AbstractExtendingCGModelVisitor<@Nullable Object, @NonNull CodeGenAnalyzer>
{
	private static final int TOUCHED = -1;
	protected static final int NOT_AVAILABLE = -2;

	private @NonNull Map<@NonNull CGValuedElement, @NonNull Set<@NonNull CGValuedElement>> directDependencies = new HashMap<>();
	protected @NonNull Id2DependencyVisitor id2DependencyVisitor = new Id2DependencyVisitor();
	protected final @NonNull GlobalPlace globalPlace;

	public DependencyVisitor(@NonNull CodeGenAnalyzer analyzer, @NonNull GlobalPlace globalPlace) {
		super(analyzer);
		this.globalPlace = globalPlace;
	}

	protected void addDependency(@Nullable CGValuedElement cgElement, @Nullable CGValuedElement dependsOn) {
		cgElement = cgElement != null ? cgElement.getThisValue() : null;
		dependsOn = dependsOn != null ? dependsOn.getThisValue() : null;
		if ((cgElement != null) && (cgElement != dependsOn)) {
			if (!cgElement.isGlobal() || (dependsOn == null) || dependsOn.isGlobal()) {
				CGValuedElement cgPrimaryElement = getPrimaryElement(cgElement);
				Set<@NonNull CGValuedElement> dependencies = directDependencies.get(cgPrimaryElement);
				List<@NonNull CGValuedElement> dependsOns = ClassUtil.nullFree(cgPrimaryElement.getDependsOn());
				if (dependencies == null) {
					dependencies = new HashSet<>();
					directDependencies.put(cgPrimaryElement, dependencies);
					for (@NonNull CGValuedElement cgDependent : new ArrayList<>(dependsOns)) {
						addDependency(cgPrimaryElement, cgDependent);
					}
					for (@NonNull EStructuralFeature eFeature : ClassUtil.nullFree(cgPrimaryElement.eClass().getEAllStructuralFeatures())) {
						if (eFeature instanceof EReference) {
							EReference eReference = (EReference)eFeature;
							if (!eReference.isDerived() && !eReference.isTransient() && !eReference.isVolatile()) {
								Object childOrChildren = cgPrimaryElement.eGet(eReference);
								if (eReference.isMany()) {
									for (@NonNull Object child : new ArrayList<>((List<@NonNull ?>)childOrChildren)) {
										if (child instanceof CGValuedElement) {
											addDependency(cgPrimaryElement, (CGValuedElement)child);
										}
									}
								}
								else {
									if ((childOrChildren instanceof CGValuedElement) && (childOrChildren != cgPrimaryElement.eContainer())) {
										addDependency(cgPrimaryElement, (CGValuedElement)childOrChildren);
									}
								}
							}
						}
					}
				}
				if (dependsOn != null) {
					CGValuedElement cgPrimaryDependsOn = getPrimaryElement(dependsOn);
					if (cgPrimaryDependsOn != cgPrimaryElement) {
						dependencies.add(cgPrimaryDependsOn);
						if (!dependsOns.contains(cgPrimaryDependsOn)) {
							dependsOns.add(cgPrimaryDependsOn);
						}
					}
				}
			}
		}
	}

	private void addElementIdDependency(@NonNull CGValuedElement cgValuedElement) {
		CGTypeId cgTypeId = cgValuedElement.getTypeId();
		addDependency(cgValuedElement, cgTypeId);
	}

	private void addElementIdDependency(@NonNull ElementId elementId, @NonNull ElementId dependsOn) {
		CGElementId cgElementId = context.getElementId(elementId);
		CGElementId cgDependsOn = context.getElementId(dependsOn);
		dependsOn.accept(id2DependencyVisitor);
		addDependency(cgElementId, cgDependsOn);
	}

	private int computeDepths(@NonNull CGValuedElement cgElement, @NonNull Map<CGValuedElement, Integer> dependencyDepths, boolean isGlobal) {
		if (isGlobal) {
			assert cgElement.isGlobal();
		}
		@NonNull CGValuedElement cgPrimaryElement = getPrimaryElement(cgElement);
		if (isGlobal) {
			assert cgPrimaryElement.isGlobal();
		}
		Set<@NonNull CGValuedElement> dependencies = directDependencies.get(cgPrimaryElement);
		if (dependencies == null) {
			int depth = getRootDepth(cgPrimaryElement);
			dependencyDepths.put(cgPrimaryElement, depth);
			return depth;
		}
		Integer knownDepth = dependencyDepths.get(cgPrimaryElement);
		if (knownDepth != null) {
			if (knownDepth != TOUCHED) {
				return knownDepth;
			}
			throw new IllegalStateException("Cyclic dependency for " + cgPrimaryElement);
		}
		dependencyDepths.put(cgPrimaryElement, TOUCHED);			// Mark already here
		int maxDepth = 0;
		for (@NonNull CGValuedElement dependency : dependencies) {
			int depth = computeDepths(dependency, dependencyDepths, isGlobal);
			if (depth > maxDepth) {
				maxDepth = depth;
			}
		}
		int myDepth = maxDepth+1;
		dependencyDepths.put(cgPrimaryElement, myDepth);
		return myDepth;
	}

	public @NonNull CGValuedElement getPrimaryElement(@NonNull CGValuedElement cgElement) {
		SimpleAnalysis simpleAnalysis = globalPlace.getSimpleAnalysis(cgElement);
		if (simpleAnalysis != null) {
			return simpleAnalysis.getPrimaryElement();
		}
		return cgElement;
	}

	public int getRootDepth(@NonNull CGValuedElement cgElement) {
		if (cgElement instanceof CGIterator) {
			return NOT_AVAILABLE;
		}
		return 0;
	}

	public @NonNull List<@NonNull CGValuedElement> getSortedDependencies(boolean isGlobal) {
		final Map<@NonNull CGValuedElement, @NonNull Integer> dependencyDepths = new HashMap<>();
		for (@NonNull CGValuedElement cgElement : directDependencies.keySet()) {
			computeDepths(cgElement, dependencyDepths, isGlobal);
		}
		List<@NonNull CGValuedElement> sortedList = new ArrayList<>();
		for (@NonNull CGValuedElement cgElement : dependencyDepths.keySet()) {
			if (!cgElement.isInlined() && (cgElement.getThisValue() == cgElement)) {
				if (isGlobal) {
					assert cgElement.isGlobal();
				}
				sortedList.add(cgElement);
			}
		}
		Collections.sort(sortedList, new Comparator<@NonNull CGValuedElement>()
		{
			@Override
			public int compare(@NonNull CGValuedElement o1, @NonNull CGValuedElement o2) {
				Integer d1 = dependencyDepths.get(o1);
				Integer d2 = dependencyDepths.get(o2);
				assert (d1 != null) && (d2 != null);
				if (d1 != d2) {
					return d1 - d2;
				}
				String n1 = String.valueOf(o1.getName());
				String n2 = String.valueOf(o2.getName());
				int diff = n1.compareTo(n2);
				if (diff != 0) {
					return diff;
				}
				String t1 = String.valueOf(o1.getTypeId());
				String t2 = String.valueOf(o2.getTypeId());
				diff = t1.compareTo(t2);
				if (diff != 0) {
					return diff;
				}
				String s1 = String.valueOf(o1);
				String s2 = String.valueOf(o2);
				return s1.compareTo(s2);
			}
		});
		return sortedList;
	}

	public void visit(@NonNull CGNamedElement cgElement) {
		if (cgElement instanceof CGValuedElement) {
			CGValuedElement cgPrimaryElement = getPrimaryElement(((CGValuedElement) cgElement).getThisValue());
			addDependency(cgPrimaryElement, null);
			cgPrimaryElement.accept(this);
		}
		else {
			cgElement.accept(this);
		}
	}

	public void visitAll(@Nullable Iterable<@NonNull ? extends CGNamedElement> cgElements) {
		if (cgElements != null) {
			for (@NonNull CGNamedElement cgElement : cgElements) {
				visit(cgElement);
			}
		}
	}

	@Override
	public @Nullable Object visitCGCollectionExp(@NonNull CGCollectionExp cgCollectionExp) {
		addElementIdDependency(cgCollectionExp);
		for (CGCollectionPart cgCollectionPart : cgCollectionExp.getParts()) {
			addDependency(cgCollectionExp, cgCollectionPart);
		}
		return super.visitCGCollectionExp(cgCollectionExp);
	}

	@Override
	public @Nullable Object visitCGCollectionPart(@NonNull CGCollectionPart cgCollectionPart) {
		CGCollectionExp cgCollectionExp = cgCollectionPart.getCollectionExp();
		addDependency(cgCollectionExp, cgCollectionPart.getFirst());
		addDependency(cgCollectionExp, cgCollectionPart.getLast());
		return super.visitCGCollectionPart(cgCollectionPart);
	}

	@Override
	public @Nullable Object visitCGConstantExp(@NonNull CGConstantExp visitCGConstantExp) {
		addDependency(visitCGConstantExp, visitCGConstantExp.getNamedValue());
		return super.visitCGConstantExp(visitCGConstantExp);
	}

	@Override
	public @Nullable Object visitCGElement(@NonNull CGElement cgElement) {
		for (CGElement cgChild : cgElement.getChildren()) {
			if ((cgElement instanceof CGValuedElement) && (cgChild instanceof CGValuedElement)) {
				addDependency((CGValuedElement)cgElement, (CGValuedElement) cgChild);
			}
			cgChild.accept(this);
		}
		return null;
	}

	@Override
	public @Nullable Object visitCGElementId(@NonNull CGElementId cgElementId) {
		cgElementId.getElementId().accept(id2DependencyVisitor);
		return super.visitCGElementId(cgElementId);
	}

	@Override
	public @Nullable Object visitCGMapExp(@NonNull CGMapExp cgMapExp) {
		addElementIdDependency(cgMapExp);
		for (CGMapPart cgMapPart : cgMapExp.getParts()) {
			addDependency(cgMapExp, cgMapPart);
		}
		return super.visitCGMapExp(cgMapExp);
	}

	@Override
	public @Nullable Object visitCGMapPart(@NonNull CGMapPart cgMapPart) {
		CGMapExp cgMapExp = cgMapPart.getMapExp();
		addDependency(cgMapExp, cgMapPart.getKey());
		addDependency(cgMapExp, cgMapPart.getValue());
		return super.visitCGMapPart(cgMapPart);
	}

	@Override
	public @Nullable Object visitCGTuplePart(@NonNull CGTuplePart cgTuplePart) {
		addDependency(cgTuplePart, cgTuplePart.getInit());
		return super.visitCGTuplePart(cgTuplePart);
	}

	@Override
	public @Nullable Object visitCGShadowPart(@NonNull CGShadowPart cgShadowPart) {
		//		CGTupleExp cgTupleExp = cgShadowPart.getTupleExp();
		//		addDependency(cgTupleExp, cgTuplePart);
		addDependency(cgShadowPart, cgShadowPart.getInit());
		return super.visitCGShadowPart(cgShadowPart);
	}

	@Override
	public @Nullable Object visitCGTupleExp(@NonNull CGTupleExp cgTupleExp) {
		addElementIdDependency(cgTupleExp);
		return super.visitCGTupleExp(cgTupleExp);
	}

	@Override
	public @Nullable Object visitCGVariable(@NonNull CGVariable cgVariable) {
		CGValuedElement init = cgVariable.getInit();
		addDependency(cgVariable, init);
		return super.visitCGVariable(cgVariable);
	}

	@Override
	public @Nullable Object visitCGVariableExp(@NonNull CGVariableExp cgVariableExp) {
		addDependency(cgVariableExp, cgVariableExp.getReferredVariable());
		return super.visitCGVariableExp(cgVariableExp);
	}

	@Override
	public @Nullable Object visiting(@NonNull CGElement visitable) {
		throw new UnsupportedOperationException(getClass().getSimpleName() + ": " + visitable.getClass().getSimpleName());
	}

	public class Id2DependencyVisitor implements IdVisitor<@Nullable Object>
	{
		@Override
		public @Nullable Object visitClassId(final @NonNull ClassId id) {
			addElementIdDependency(id, id.getParent());
			return null;
		}

		@Override
		public @Nullable Object visitCollectionTypeId(final @NonNull CollectionTypeId id) {
			if (id instanceof SpecializedId) {
				BindingsId templateBindings = ((SpecializedId)id).getTemplateBindings();
				for (int i = 0; i < templateBindings.elementIdSize(); i++) {
					ElementId elementId = ClassUtil.nonNullModel(templateBindings.getElementId(i));
					addElementIdDependency(id, elementId);
				}
			}
			return null;
		}

		@Override
		public @Nullable Object visitDataTypeId(final @NonNull DataTypeId id) {
			addElementIdDependency(id, id.getParent());
			return null;
		}

		@Override
		public @Nullable Object visitEnumerationId(final @NonNull EnumerationId id) {
			addElementIdDependency(id, id.getParent());
			return null;
		}

		@Override
		public @Nullable Object visitEnumerationLiteralId(final @NonNull EnumerationLiteralId id) {
			addElementIdDependency(id, id.getParentId());
			return null;
		}

		@Override
		public @Nullable Object visitInvalidId(@NonNull OclInvalidTypeId id) {
			return null;
		}

		@Override
		public @Nullable Object visitLambdaTypeId(@NonNull LambdaTypeId id) {
			// TODO Auto-generated method stub
			return visiting(id);
		}

		@Override
		public @Nullable Object visitMapTypeId(final @NonNull MapTypeId id) {
			if (id instanceof SpecializedId) {
				BindingsId templateBindings = ((SpecializedId)id).getTemplateBindings();
				for (int i = 0; i < templateBindings.elementIdSize(); i++) {
					ElementId elementId = ClassUtil.nonNullModel(templateBindings.getElementId(i));
					addElementIdDependency(id, elementId);
				}
			}
			return null;
		}

		@Override
		public @Nullable Object visitNestedPackageId(final @NonNull NestedPackageId id) {
			addElementIdDependency(id, id.getParent());
			return null;
		}

		@Override
		public @Nullable Object visitNsURIPackageId(final @NonNull NsURIPackageId id) {
			return null;
		}

		@Override
		public @Nullable Object visitNullId(@NonNull OclVoidTypeId id) {
			return null;
		}

		@Override
		public @Nullable Object visitOperationId(final @NonNull OperationId id) {
			addElementIdDependency(id, id.getParent());
			for (@NonNull TypeId parameterId : id.getParametersId()) {
				addElementIdDependency(id, parameterId);
			}
			return null;
		}

		@Override
		public @Nullable Object visitPrimitiveTypeId(@NonNull PrimitiveTypeId id) {
			return null;
		}

		@Override
		public @Nullable Object visitPropertyId(final @NonNull PropertyId id) {
			addElementIdDependency(id, id.getParent());
			return null;
		}

		@Override
		public @Nullable Object visitRootPackageId(final @NonNull RootPackageId id) {
			return null;
		}

		@Override
		public @Nullable Object visitTemplateBinding(@NonNull TemplateBinding id) {
			// TODO Auto-generated method stub
			return visiting(id);
		}

		@Override
		public @Nullable Object visitTemplateParameterId(@NonNull TemplateParameterId id) {
			return null;
		}

		@Override
		public @Nullable Object visitTemplateableTypeId(@NonNull TemplateableTypeId id) {
			// TODO Auto-generated method stub
			return visiting(id);
		}

		@Override
		public @Nullable Object visitTuplePartId(final @NonNull TuplePartId id) {
			addElementIdDependency(id, id.getTypeId());
			return null;
		}

		@Override
		public @Nullable Object visitTupleTypeId(final @NonNull TupleTypeId id) {
			for (TuplePartId partId : id.getPartIds()) {
				addElementIdDependency(id, partId);
			}
			return null;
		}

		@Override
		public @Nullable Object visitUnspecifiedId(@NonNull UnspecifiedId id) {
			return visiting(id);
		}

		@Override
		public @Nullable Object visitWildcardId(@NonNull WildcardId id) {
	//		return visiting(id);
			return null;
		}

		public @Nullable Object visiting(@NonNull ElementId id) {
			throw new UnsupportedOperationException(getClass().getSimpleName() + ": " + id.getClass().getName());
		}
	}
}
