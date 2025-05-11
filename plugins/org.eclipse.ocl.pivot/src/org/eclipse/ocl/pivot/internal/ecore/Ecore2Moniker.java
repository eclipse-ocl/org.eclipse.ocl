/*******************************************************************************
 * Copyright (c) 2010, 2025 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.ecore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.ETypeParameter;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreSwitch;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.internal.utilities.PivotConstantsInternal;
import org.eclipse.ocl.pivot.utilities.ClassUtil;

/**
 * Ecore2Moniker supports generation of a hierarchically derived moniker for
 * an EModelElement. A moniker provides a unique repeatable readable id.
 */
public class Ecore2Moniker extends EcoreSwitch<Object> implements PivotConstantsInternal
{
	/**
	 * A MonikerAliasAdapter extends a Resource to provide a set of aliases for elements,
	 * typically packages, contained within that Resource. Use of an alias as the moniker
	 * for a package simplifies the moniker and avoids prefix variation for monikers
	 * computed for alternate domains.
	 *
	 * @since 7.0
	 */
	public static class MonikerAliasAdapter extends AdapterImpl
	{
		public static @Nullable MonikerAliasAdapter findAdapter(@Nullable Resource resource) {
			if (resource == null) {
				return null;
			}
			return ClassUtil.getAdapter(MonikerAliasAdapter.class, resource);
		}

		public static @NonNull MonikerAliasAdapter getAdapter(@NonNull Resource resource) {
			List<Adapter> eAdapters = ClassUtil.nonNullEMF(resource.eAdapters());
			MonikerAliasAdapter adapter = ClassUtil.getAdapter(MonikerAliasAdapter.class, eAdapters);
			if (adapter == null) {
				adapter = new MonikerAliasAdapter();
				eAdapters.add(adapter);
			}
			return adapter;
		}

		public static String getAlias(EObject eElement) {
			String alias = null;
			MonikerAliasAdapter adapter = MonikerAliasAdapter.findAdapter(eElement.eResource());
			if (adapter != null) {
				alias = adapter.getAliasMap().get(eElement);
			}
			return alias;
		}

		private @NonNull Map<@NonNull EObject, @Nullable String> aliasMap = new HashMap<>();

		public @NonNull Map<@NonNull EObject, @Nullable String> getAliasMap() {
			return aliasMap;
		}

		@Override
		public boolean isAdapterForType(Object type) {
			return type == MonikerAliasAdapter.class;
		}
	}

	public static String toString(EModelElement eElement) {
		Ecore2Moniker moniker = new Ecore2Moniker(false);
		moniker.appendElement(eElement);
		String string = moniker.toString();
		return string;
	}

	public static String toString(EGenericType eElement) {
		Ecore2Moniker moniker = new Ecore2Moniker(false);
		moniker.appendType(eElement);
		String string = moniker.toString();
		return string;
	}

	/**
	 * Moiniker detail; false for minimal uniqueness (omit template bounds, template
	 * parameter declarations, parameter names), true to show everything.
	 * <p>
	 * Ecore models (unlike Pivot models) are mutable so the moniker is computed on
	 * demand and may change if a name on the hierarchical path changes.
	 */
	protected boolean fullSignature;

	private StringBuilder s = new StringBuilder();
	private List<ETypeParameter> emittedParameters = null;

	protected Ecore2Moniker(boolean fullSignature) {
		this.fullSignature = fullSignature;
	}

	protected void append(String string) {
		s.append(string != null ? string : "null"); //$NON-NLS-1$
	}

	protected void appendBounds(List<? extends EGenericType> bounds) {
		String prefix = "";
		for (@NonNull EGenericType bound : ClassUtil.nullFree(bounds)) {
			s.append(prefix);
			prefix = "|";							// No example available
			EGenericType eLowerBound = bound.getELowerBound();
			if (eLowerBound != null) {
				s.append(" >= ");					// No example available
				appendType(eLowerBound);
			}
			EGenericType eUpperBound = bound.getEUpperBound();
			if (eUpperBound != null) {
				s.append(" <= ");					// No example available
				appendType(eUpperBound);
			}
			EClassifier eClassifier = bound.getEClassifier();
			if (eClassifier != null) {
				s.append(" extends ");
				appendType(bound);
			}
			else {
				assert bound.getETypeArguments().isEmpty();
				assert bound.getERawType() == null;
			}
			assert bound.getETypeParameter() == null;
		}
	}

	protected void appendElement(EModelElement eElement) {
		if (eElement != null) {
			int classifierID = eElement.eClass().getClassifierID();
			doSwitch(classifierID, eElement);
		}
	}

	protected Object doInPackageSwitch1(EModelElement theEObject) {
		int classifierID = theEObject.eClass().getClassifierID();
		return doSwitch(classifierID, theEObject);
	}

	protected void appendName(ENamedElement eNamedElement) {
		append(eNamedElement.getName());
	}

	protected void appendParameters(List<EParameter> parameters) {
		s.append(PARAMETER_PREFIX);
		String prefix = ""; //$NON-NLS-1$
		for (EParameter eParameter : parameters) {
			s.append(prefix);
			appendType(eParameter.getEGenericType());
			if (fullSignature) {
				s.append(":");
				s.append(eParameter.getName());
			}
			prefix = PARAMETER_SEPARATOR;
		}
		s.append(PARAMETER_SUFFIX);
	}

	protected void appendParent(EObject eElement, String parentSeparator) {
		EObject parent = eElement != null ? eElement.eContainer() : null;
		if (parent instanceof EModelElement) {
			boolean wasFullSignature = fullSignature;
			fullSignature = false;
			appendElement((EModelElement) parent);
			fullSignature = wasFullSignature;
		}
		else if (parent instanceof EGenericType) {
			appendParent(parent, parentSeparator);
		}
		append(parentSeparator);
	}

	protected void appendType(EGenericType eGenericType) {
		ETypeParameter eTypeParameter = eGenericType.getETypeParameter();
		EClassifier eClassifier = eGenericType.getEClassifier();
		if (eClassifier != null) {
			appendParent(eClassifier, MONIKER_SCOPE_SEPARATOR);
			append(eClassifier.getName());
			appendTypeArguments(eGenericType.getETypeArguments(), eClassifier.getETypeParameters());
		}
		else if (eTypeParameter != null){
			appendElement(eTypeParameter);
			assert eGenericType.getETypeArguments().size() == 0;
		}
		else {
			EStructuralFeature eContainingFeature = eGenericType.eContainingFeature();
			if (s.length() == 0) {
				appendParent(eGenericType, MONIKER_SCOPE_SEPARATOR);
				s.append(WILDCARD_INDICATOR);
			}
			else if (eContainingFeature == EcorePackage.Literals.EGENERIC_TYPE__ETYPE_ARGUMENTS) {
				EGenericType eContainer = (EGenericType)eGenericType.eContainer();
				List<?> eTypeArguments = (List<?>) eContainer.eGet(eContainingFeature);
				int index = eTypeArguments.indexOf(eGenericType);
				EClassifier unspecializedClassifier = eContainer.getEClassifier();
				appendElement(unspecializedClassifier);
				s.append(BINDINGS_PREFIX);
				List<ETypeParameter> eTypeParameters = unspecializedClassifier.getETypeParameters();
				eTypeParameter = eTypeParameters.get(index);
				appendName(eTypeParameter);
				s.append(MONIKER_SCOPE_SEPARATOR);
				s.append(WILDCARD_INDICATOR);
				s.append(index);
			}
			else {
				s.append(WILDCARD_INDICATOR);
			}
			assert eGenericType.getETypeArguments().size() == 0;
		}
	}

	protected void appendTypeArguments(List<EGenericType> eTypeArguments, List<ETypeParameter> eTypeParameters) {
		assert eTypeArguments.size() == eTypeParameters.size();
		if (!eTypeArguments.isEmpty()) {
			s.append(TEMPLATE_BINDING_PREFIX);
			String prefix = ""; //$NON-NLS-1$
			for (int i = 0; i < eTypeArguments.size(); i++) {
				s.append(prefix);
				appendType(eTypeArguments.get(i));
				if (fullSignature) {
					s.append(":");
					appendTypeParameter(eTypeParameters.get(i));
				}
				prefix = TEMPLATE_BINDING_SEPARATOR;
			}
			s.append(TEMPLATE_BINDING_SUFFIX);
		}
	}

	protected void appendTypeParameter(ETypeParameter eTypeParameter) {
		if (emittedParameters == null) {
			emittedParameters = new ArrayList<ETypeParameter>();
		}
		emittedParameters.add(eTypeParameter);
		appendName(eTypeParameter);
		if (fullSignature) {
			appendBounds(eTypeParameter.getEBounds());
		}
	}

	protected void appendTypeParameters(List<ETypeParameter> eTypeParameters) {
		if (!eTypeParameters.isEmpty()) {
			s.append(TEMPLATE_SIGNATURE_PREFIX);
			String prefix = ""; //$NON-NLS-1$
			for (ETypeParameter eTypeParameter : eTypeParameters) {
				s.append(prefix);
				appendTypeParameter(eTypeParameter);
				prefix = TEMPLATE_SIGNATURE_SEPARATOR;
			}
			s.append(TEMPLATE_SIGNATURE_SUFFIX);
		}
	}

	protected void appendTypes(List<EGenericType> eTypes) {
		String prefix = ""; //$NON-NLS-1$
		for (EGenericType eType : eTypes) {
			s.append(prefix);
			appendType(eType);
			prefix = TEMPLATE_SIGNATURE_SEPARATOR;
		}
	}

	@Override
	public Object caseEAnnotation(EAnnotation eElement) {
		appendParent(eElement, MONIKER_SCOPE_SEPARATOR);
		s.append("'");
		append(eElement.getSource());
		s.append("'");
		Object container = eElement.eContainer().eGet(eElement.eContainingFeature());
		if (container instanceof List<?>) {
			int index = 0;
			for (Object object : (List<?>)container) {
				if (object == eElement) {
					break;
				}
				if (object instanceof EAnnotation) {
					String source = ((EAnnotation)object).getSource();
					if ((source != null) && source.equals(eElement.getSource())) {
						index++;
					}
				}
			}
			if (index > 0) {
				s.append(index);
			}
		}
		return true;
	}

	@Override
	public Object caseEClassifier(EClassifier eElement) {
		caseENamedElement(eElement);
		appendTypeParameters(eElement.getETypeParameters());
		return true;
	}

	@Override
	public Object caseENamedElement(ENamedElement eElement) {
		appendParent(eElement, MONIKER_SCOPE_SEPARATOR);
		appendName(eElement);
		return true;
	}

	@Override
	public Object caseEOperation(EOperation eElement) {
		caseENamedElement(eElement);
		appendTypeParameters(eElement.getETypeParameters());
		appendParameters(eElement.getEParameters());
		if (fullSignature) {
			List<EGenericType> eGenericExceptions = eElement.getEGenericExceptions();
			if (eGenericExceptions.size() > 0) {
				append(" throws ");
				appendTypes(eGenericExceptions);
			}
		}
		return true;
	}

	@Override
	public Object caseEPackage(EPackage eElement) {
		String alias = MonikerAliasAdapter.getAlias(eElement);
		if (alias != null) {
			append(alias);
			return true;
		}
		return caseENamedElement(eElement);
	}

	@Override
	public Object caseETypeParameter(ETypeParameter eElement) {
		if (!hasEmitted(eElement)) {
			if (toString().length() < MONIKER_OVERFLOW_LIMIT) {
				appendParent(eElement, TEMPLATE_PARAMETER_PREFIX);
			}
			else {
				append(OVERFLOW_MARKER);
			}
		}
		appendName(eElement);
		return true;
	}

	protected boolean hasEmitted(ETypeParameter eTypeParameter) {
		return (emittedParameters != null) && emittedParameters.contains(eTypeParameter);
	}

	@Override
	public String toString() {
		return s.toString();
	}
}
