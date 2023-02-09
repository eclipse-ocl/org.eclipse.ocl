/*******************************************************************************
 * Copyright (c) 2022 Willink Transformation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.naming;

import java.lang.reflect.Field;
import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGClass;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNamedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGParameter;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTypeId;
import org.eclipse.ocl.examples.codegen.cgmodel.CGVariable;
import org.eclipse.ocl.examples.codegen.java.JavaCodeGenerator;
import org.eclipse.ocl.examples.codegen.java.JavaConstants;
import org.eclipse.ocl.examples.codegen.java.types.JavaTypeId;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

/**
 * A ClassNameManager provides suggestions for names and maintains caches of used names so that model elements are consistently
 * named without collisions at some node in the name nesting hierarchy.
 */
public class ClassNameManager extends NestedNameManager implements ClassableNameManager
{
	protected final @NonNull CGClass cgClass;
	protected final org.eclipse.ocl.pivot.@NonNull Class asClass;
	private final /*@LazyNonNull*/ CGParameter rootExecutorParameter;

	public ClassNameManager(@NonNull JavaCodeGenerator codeGenerator, @NonNull ClassableNameManager parent, @NonNull CGClass cgClass) {
		super(codeGenerator, (AbstractNameManager)parent, cgClass);
		this.cgClass = cgClass;
		this.asClass = CGUtil.getAST(cgClass);
		this.rootExecutorParameter = computeRootExecutorParameter();
	}

	public @Nullable CGVariable basicGetRootExecutorVariable() {
		if (parent instanceof ClassNameManager) {
			return ((ClassNameManager)parent).basicGetRootExecutorVariable();
		}
		return rootExecutorParameter;
	}

	private @Nullable CGParameter computeRootExecutorParameter() {
		NameResolution rootExecutorNameResolution = globalNameManager.getRootExecutorName();
		Property asProperty = NameUtil.getNameable(asClass.getOwnedProperties(), rootExecutorNameResolution.getResolvedName());
		if (asProperty != null) {			// XXX redundant
			CGTypeId cgTypeId = analyzer.getCGTypeId(JavaConstants.EXECUTOR_TYPE_ID);
			return analyzer.createCGParameter(rootExecutorNameResolution, cgTypeId, true);
		}
		//
		//	If this class is logically nested, use the rootExecutorVariable of the 'parent' ClassNameManager.
		//
		if ((parent instanceof PackageNameManager) && (((PackageNameManager)parent).parent instanceof PackageNameManager)) {
			PackageNameManager packageNameManager = (PackageNameManager)parent;
			NamedElement asScope1 = packageNameManager.getASScope();
			if (packageNameManager.parent instanceof PackageNameManager) {
				Iterable<@NonNull NestedNameManager> children = ((PackageNameManager)parent).parent.getChildren();
				assert children != null;
				for (@NonNull NestedNameManager nestedNameManager : children) {
					if (nestedNameManager instanceof ClassNameManager) {
						NamedElement asScope2 = nestedNameManager.getASScope();
						if (ClassUtil.safeEquals(asScope1.getName(), asScope2.getName())) {
							return (CGParameter) ((ClassNameManager)nestedNameManager).basicGetRootExecutorVariable();
						}
					}
				}
			}
		}
		//
		//	If this class inherits from a Java class with a rootExecutor field, use it to create the rootExecutorVariable.
		//
		String rootExecutorName = rootExecutorNameResolution.getResolvedName();
		for (@NonNull CGClass cgSuperClass : CGUtil.getSuperTypes(cgClass)) {
			TypeId typeId = CGUtil.getAST(cgSuperClass).getTypeId();
			if (typeId instanceof JavaTypeId) {
				Field rootExecutorField = null;
				for (Class<?> jClass = ((JavaTypeId)typeId).getJavaClass(); jClass != null; jClass = jClass.getSuperclass()) {
					try {
						rootExecutorField = jClass.getDeclaredField(rootExecutorName);
						if (rootExecutorField != null) {
							break;
						}
					} catch (NoSuchFieldException | SecurityException e) {;
					}
				}
				if (rootExecutorField != null) {
					CGTypeId cgTypeId = analyzer.getCGTypeId(JavaConstants.EXECUTOR_TYPE_ID);
					return analyzer.createCGParameter(rootExecutorNameResolution, cgTypeId, true);
				}
			}
		}
		return null;
	}

	public org.eclipse.ocl.pivot.@NonNull Class getASClass() {
		return asClass;
	}

	@Override
	public @NonNull NamedElement getASScope() {
		return asClass;
	}

	public @NonNull CGClass getCGClass() {
		return cgClass;
	}

	@Override
	public @NonNull CGClass getCGScope() {
		return cgClass;
	}

//	@Override
//	public @NonNull ClassNameManager getClassNameManager() {
//		return this;
//	}

	/**
	 * Return the NestedNameManager that can be the parent of another CGClass. Returns null for global.
	 */
	@Override
	public @Nullable ClassNameManager getClassParentNameManager() {
		for (ClassNameManager nameManager = this; nameManager != null; nameManager = nameManager.parent instanceof ClassNameManager ? (ClassNameManager)nameManager.parent : null) {
			CGNamedElement cgClass = nameManager.cgClass;
			if (cgClass instanceof CGClass) {
				return nameManager.parent instanceof ClassNameManager ? (ClassNameManager)nameManager.parent : null;
			}
		}
		return null;
	}

	public @NonNull CGVariable getRootExecutorVariable() {
		if (parent instanceof ClassNameManager) {
			return ((ClassNameManager)parent).getRootExecutorVariable();
		}
		return ClassUtil.nonNullState(rootExecutorParameter);
	}

	public @NonNull String getUniquePropertyName(@NonNull String namePrefix, org.eclipse.ocl.pivot.@NonNull Class asNestedClass) {
		String name = namePrefix + PivotUtil.getName(asNestedClass);
		List<Property> ownedProperties = asClass.getOwnedProperties();
		if (NameUtil.getNameable(ownedProperties, name) == null) {
			return name;
		}
		for (int i = 1; true; i++) {
			String name2 = name + "_" + i;
			if (NameUtil.getNameable(ownedProperties, name2) == null) {
				return name2;
			}
		}
	}
}
