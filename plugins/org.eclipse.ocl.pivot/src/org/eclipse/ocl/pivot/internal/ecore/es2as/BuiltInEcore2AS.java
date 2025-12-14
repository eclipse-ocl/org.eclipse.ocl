/*******************************************************************************
 * Copyright (c) 2025 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.ecore.es2as;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.Library;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.PivotTables;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;
import org.eclipse.ocl.pivot.utilities.TreeIterable;

/**
 * Manage mapping of a *.ecore model to its built-in equivalent *.ecore.oclas.
 *
 * @since 7.0
 */
public class BuiltInEcore2AS extends Ecore2AS
{
	protected final @NonNull Model asModel;

	/**
	 * Mapping of source Ecore eModelElements and eGenericTypes to their resulting pivot element in the current conversion.
	 */
	private final @NonNull Map<@NonNull EObject, @NonNull Element> createMap = new HashMap<>();

	public BuiltInEcore2AS(@NonNull Resource resource, @NonNull EnvironmentFactory environmentFactory, @NonNull Model asModel) {
		super(resource, environmentFactory);
		this.asModel = asModel;
		environmentFactory.getCompleteModel().addPartialModel(asModel);
		for (EObject asObject : new TreeIterable(asModel, false)) {		// XXX create eagerly in xxxTables
			if (asObject instanceof Element) {
				if (asObject instanceof Library) {
					environmentFactory.getStandardLibrary().installLibrary((Library)asObject);
				}
				Element asElement = (Element)asObject;
				EObject eObject = asElement.getESObject();
				if (eObject != null) {
					createMap.put(eObject, asElement);
				}
			}
		}
		if (asModel == PivotTables.MODEL) {								// XXX ??? Transfer to ctor
			createMap.put(ClassUtil.requireNonNull(PivotPackage.Literals.BOOLEAN), standardLibrary.getBooleanType());
			createMap.put(ClassUtil.requireNonNull(PivotPackage.Literals.INTEGER), standardLibrary.getIntegerType());
			createMap.put(ClassUtil.requireNonNull(PivotPackage.Literals.REAL), standardLibrary.getRealType());
			createMap.put(ClassUtil.requireNonNull(PivotPackage.Literals.STRING), standardLibrary.getStringType());
			createMap.put(ClassUtil.requireNonNull(PivotPackage.Literals.UNLIMITED_NATURAL), standardLibrary.getUnlimitedNaturalType());
		}
/*		List<EObject> keys = new ArrayList<>(createMap.keySet());
		Collections.sort(keys, NameUtil.TO_STRING_COMPARATOR);
		StringBuilder s = new StringBuilder();
		s.append("BuiltInEcore2AS " + this);
		int i = 0;
		for (EObject key : keys) {		// XXX create eagerly in xxxTables
			Element element = createMap.get(key);
			assert element != null;
			s.append("\n\t" + ++i + " " + element.getClass().getSimpleName() + " " + element + " " + key.getClass().getSimpleName());// + " " + key);
		}
		System.out.println(s.toString()); */
	}

	@Override
	protected Model basicGetPivotModel() {
		return asModel;
	}

	@Override
	public @NonNull Model getASModel() {
		return asModel;
	}

	@Override
	public <T extends Element> @Nullable T getCreated(@NonNull Class<T> requiredClass, @NonNull EObject eObject) {
		return requiredClass.cast(createMap.get(eObject));
	}

	@Override
	public @Nullable Map<@NonNull EObject, @NonNull Element> getCreatedMap() {
		return createMap;
	}
}
