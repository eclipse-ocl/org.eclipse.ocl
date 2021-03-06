/*******************************************************************************
 * Copyright (c) 2013, 2021 CEA LIST and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink(CEA LIST) - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.java.types;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGUnboxExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.generator.CodeGenerator;
import org.eclipse.ocl.examples.codegen.generator.GenModelHelper;
import org.eclipse.ocl.examples.codegen.generator.TypeDescriptor;
import org.eclipse.ocl.examples.codegen.java.JavaLocalContext;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.ids.IdResolver;
import org.eclipse.ocl.pivot.ids.TemplateParameterId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.manager.PivotMetamodelManager;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;

/**
 * A BoxedValueDescriptor describes a type whose boxed representation differs from its unboxed representation. It has a pivot ElementId and a Java class.
 * <p>
 * Thus an IntegerValue is a TypeId.INTEGER and an org.eclipse.ocl.domain.values.IntegerValue.
 */
public class BoxedValuesDescriptor extends AbstractValueDescriptor implements BoxedDescriptor
{
	private /*@LazyNonNull*/ UnboxedDescriptor unboxedDescriptor;
	private /*@LazyNonNull*/ Map</*@NonNull*/ Class<?>, @NonNull EcoreDescriptor> javaClass2ecoreDescriptor;

	public BoxedValuesDescriptor(@NonNull CollectionTypeId elementId, @NonNull Class<?> javaClass) {
		super(elementId, javaClass);
	}

	@Override
	public @NonNull Boolean appendUnboxStatements(@NonNull JavaStream js, @NonNull JavaLocalContext<@NonNull ?> localContext,
			@NonNull CGUnboxExp cgUnboxExp, @NonNull CGValuedElement boxedValue) {
//		if (collectionDescriptor != null) {
			js.append("final ");
//			js.appendIsRequired(true);
//			js.append(" ");
			unboxedDescriptor.append(js, true);
//			js.appendClassReference(List.class, false, unboxedTypeDescriptor.getJavaClass());
			js.append(" ");
			js.appendValueName(cgUnboxExp);
			js.append(" = ");
			js.appendValueName(boxedValue);
			js.append(".asEcoreObjects(");
			js.appendReferenceTo(localContext.getIdResolverVariable(cgUnboxExp));
			js.append(", ");
			((CollectionDescriptor)unboxedDescriptor).appendElement(js, true);
			js.append(".class);\n");
			//
			js.append("assert ");
			js.appendValueName(cgUnboxExp);
			js.append(" != null;\n");
//		}
		return true;
	}

	protected EClassifier getEClassifier(@NonNull PivotMetamodelManager metamodelManager, @NonNull Type type) {
		for (@SuppressWarnings("null")org.eclipse.ocl.pivot.@NonNull Class dType : metamodelManager.getPartialClasses(type)) {
			EClassifier eClass = (EClassifier) dType.getESObject();
			if (eClass != null) {
				return eClass;
			}
		}
		return null;
	}

	@Override
	public @NonNull EcoreDescriptor getEcoreDescriptor(@NonNull CodeGenerator codeGenerator, @Nullable Class<?> instanceClass) {
		Map</*@NonNull*/ Class<?>, @NonNull EcoreDescriptor> javaClass2ecoreDescriptor2 = javaClass2ecoreDescriptor;
		if (javaClass2ecoreDescriptor2 == null) {
			javaClass2ecoreDescriptor2 = javaClass2ecoreDescriptor = new HashMap<>();
		}
		EcoreDescriptor ecoreDescriptor = javaClass2ecoreDescriptor2.get(instanceClass);
		if (ecoreDescriptor == null) {
			CollectionTypeId collectionTypeId = (CollectionTypeId)elementId;
			TypeId elementTypeId = collectionTypeId.getElementTypeId();
			TypeId generalizedId = collectionTypeId.getGeneralizedId();
			EnvironmentFactoryInternal environmentFactory = codeGenerator.getEnvironmentFactory();
			IdResolver idResolver = environmentFactory.getIdResolver();
			Type type;
			if (generalizedId == collectionTypeId) {
				type = idResolver.getClass(collectionTypeId, null);
			}
			else if (elementTypeId instanceof TemplateParameterId) {
				CollectionType collectionType = (CollectionType) idResolver.getClass(generalizedId, null);
				type = collectionType.getElementType();
				assert type != null;
			}
			else {
				type = idResolver.getClass(elementTypeId, null);
			}
/*			EClassifier eClassifier = getEClassifier(environmentFactory.getMetamodelManager(), type);
			if (eClassifier != null) {
				GenModelHelper genModelHelper = codeGenerator.getGenModelHelper();
				try {
					Class<?> javaClass = genModelHelper.getEcoreInterfaceClassifier(eClassifier);
					ecoreDescriptor2 = new EObjectsDescriptor(id, eClassifier, javaClass);
				}
				catch (Exception e) {
					String instanceClassName = type.getInstanceClassName();
					if (instanceClassName == null) {
						instanceClassName = genModelHelper.getEcoreInterfaceClassifierName(eClassifier);
					}
					if (instanceClassName != null) {
						ecoreDescriptor2 = new FutureEObjectsDescriptor(id, eClassifier, instanceClassName);
					}
				}
			} */
			EcoreDescriptor rawEcoreElementTypeDescriptor = codeGenerator.getEcoreDescriptor(elementTypeId, instanceClass);
			Class<?> templateElementInstanceJavaClass = rawEcoreElementTypeDescriptor.getNonPrimitiveJavaClass();
			@NonNull EcoreDescriptor nonPrimitiveEcoreElementTypeDescriptor2 = codeGenerator.getEcoreDescriptor(elementTypeId, templateElementInstanceJavaClass);
			ecoreDescriptor = new EcoreListDescriptor(collectionTypeId, environmentFactory.getStandardLibrary(), type, nonPrimitiveEcoreElementTypeDescriptor2);
			javaClass2ecoreDescriptor2.put(instanceClass, ecoreDescriptor);
		}
		return ecoreDescriptor;
	}


	@Override
	public @NonNull UnboxedDescriptor getUnboxedDescriptor(@NonNull CodeGenerator codeGenerator) {
		UnboxedDescriptor unboxedDescriptor2 = unboxedDescriptor;
		if (unboxedDescriptor2 == null) {
			org.eclipse.ocl.pivot.Class type;
			CollectionTypeId id = (CollectionTypeId)elementId;
			TypeId generalizedId = id.getGeneralizedId();
			EnvironmentFactoryInternal environmentFactory = codeGenerator.getEnvironmentFactory();
			IdResolver idResolver = environmentFactory.getIdResolver();
			if (generalizedId == id) {
				type = idResolver.getClass(id, null);
			}
			else {
				TypeId typeId = id.getElementTypeId();
				type = idResolver.getClass(typeId, null);
			}
			EClassifier eClassifier = getEClassifier(environmentFactory.getMetamodelManager(), type);
			if (eClassifier != null) {
				GenModelHelper genModelHelper = codeGenerator.getGenModelHelper();
				try {
					Class<?> javaClass = genModelHelper.getEcoreInterfaceClassifier(eClassifier);
					unboxedDescriptor2 = new EObjectsDescriptor(id, eClassifier, javaClass);
				}
				catch (Exception e) {
					String instanceClassName = type.getInstanceClassName();
					if (instanceClassName == null) {
						instanceClassName = genModelHelper.getEcoreInterfaceClassifierName(eClassifier);
					}
					if (instanceClassName != null) {
						unboxedDescriptor2 = new FutureEObjectsDescriptor(id, eClassifier, instanceClassName);
					}
				}
			}
			if (unboxedDescriptor2 == null) {
				unboxedDescriptor2 = new UnboxedElementsDescriptor(id, environmentFactory.getStandardLibrary(), type);
			}
			unboxedDescriptor = unboxedDescriptor2;
		}
		return unboxedDescriptor2;
	}

	@Override
	public final boolean isAssignableFrom(@NonNull TypeDescriptor typeDescriptor) {
		if (!(typeDescriptor instanceof BoxedDescriptor)) {
			return false;
		}
		return javaClass.isAssignableFrom(typeDescriptor.getJavaClass());
	}
}
