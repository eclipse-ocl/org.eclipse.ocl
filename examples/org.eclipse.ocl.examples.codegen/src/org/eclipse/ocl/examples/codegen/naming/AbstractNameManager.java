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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNamedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGParameter;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.ids.NestedTypeId;
import org.eclipse.ocl.pivot.ids.PackageId;
import org.eclipse.ocl.pivot.ids.RootPackageId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.utilities.AbstractLanguageSupport;
import org.eclipse.ocl.pivot.utilities.ClassUtil;

/**
 * A NameManager provides suggestions for names and maintains caches of used names so that model elements are consistently
 * named without collisions.
 */
public abstract class AbstractNameManager implements NameManager
{
	/**
	 * name2object place holders for names with no real object.
	 */
	protected static final @NonNull Object NOT_AN_OBJECT = new Object()
	{
		@Override
		public String toString() {
			return "NOT_AN_OBJECT";
		}
	};

	protected static void appendJavaCharacters(StringBuilder s, String string) {
		for (int i = 0; i < string.length(); i++) {
			char c = string.charAt(i);
			if (Character.isJavaIdentifierPart(c)) {
				s.append(c);
			}
			else {
				s.append('_');
			}
		}
	}

	protected static void appendJavaCharacters(StringBuilder s, String string, int iMax) {
		for (int i = 0; i < Math.min(iMax, string.length()); i++) {
			char c = string.charAt(i);
			if (Character.isJavaIdentifierPart(c)) {
				s.append(c);
			}
			else {
				s.append('_');
			}
		}
	}

	protected static class Context
	{
		private final @NonNull AbstractNameManager nameManager;
		private @NonNull Map<@NonNull String, @NonNull Object> name2object;		// User of each name, null if name ambiguous
		private @Nullable Map<@NonNull String, @NonNull Integer> name2counter;	// Auto-generation counter for each colliding name
		private boolean hasChildren = false;									// Set tru once a child context has copied state

		protected Context(@NonNull AbstractNameManager nameManager) {
			this.nameManager = nameManager;
			if (nameManager instanceof GlobalNameManager) {
				this.name2object = new HashMap<>();
				this.name2counter = null;
			}
			else {
				AbstractNameManager parent = ((NestedNameManager)nameManager).parent;
				Context parentContext = parent.getContext();
				parentContext.hasChildren = true;
				this.name2object = new HashMap<>(parentContext.name2object);
				this.name2counter = parentContext.name2counter != null ? new HashMap<>(parentContext.name2counter) : null;
			}
		}

		protected @NonNull String allocateEagerName(@NonNull String eagerName, @Nullable CGNamedElement cgElement) {
			Object anObject = cgElement != null ? cgElement : NOT_AN_OBJECT;
			assert eagerName != NameResolution.NOT_NEEDED;
		//	assert reservedName.equals(NameManagerHelper.getValidJavaIdentifier(eagerName, false, null));
		//	assert !reservedJavaNames.contains(reservedName);
		//	assert anObject != NOT_AN_OBJECT;
			Object oldElement = name2object.get(eagerName);
			if (oldElement == null) {									// New allocation
				name2object.put(eagerName, anObject);
				assert debugAllocatedName(eagerName);
				return eagerName;
			}
			Element occludedElement = (oldElement instanceof CGNamedElement) ? ((CGNamedElement)oldElement).getAst() : null;
			Element occludingElement = cgElement != null ? cgElement.getAst() : null;
			String occludedName = occludedElement instanceof NamedElement ? (((NamedElement)occludedElement).eClass().getName() + " \"" + AbstractLanguageSupport.getQualifiedName((NamedElement)occludedElement) + "\"") : String.valueOf(oldElement);
			String occludingName = occludingElement instanceof NamedElement ? (((NamedElement)occludingElement).eClass().getName() + " \"" + AbstractLanguageSupport.getQualifiedName((NamedElement)occludingElement) + "\"") : String.valueOf(cgElement);
			System.out.println("NameManager: " + occludedName + " is occluded by " + occludingName + ".");
			return allocateFallBackName(eagerName);		// testQVTcCompiler_Forward2Reverse_CG has list2list Property occluding  a Package
		}

		private @NonNull String allocateFallBackName(@NonNull String validHint) {
			Map<@NonNull String, @NonNull Integer> name2counter2 = name2counter;
			if (name2counter2 == null) {
				name2counter = name2counter2 = new HashMap<>();
			}
			Integer counter = name2counter2.get(validHint);
			for (int count = counter != null ? counter : 0; true; count++) {
				String attempt = validHint + "_" + Integer.toString(count);
				if (!name2object.containsKey(attempt)) {		// Assumes that reserved names do not end in _ count
					name2counter2.put(validHint, ++count);
					if ("context_0".equals(attempt)) {
						@SuppressWarnings("unused") Object firstUsage = name2object.get(validHint);
						getClass();			// XXX
					}
					assert debugAllocatedName(attempt);
					return attempt;
				}
			}
		}

		protected @NonNull String allocateLazyName(@NonNull String nameHint, @Nullable CGNamedElement cgElement) {
			if (nameHint.equals("context") && (cgElement instanceof CGParameter)) {
				getClass();			// XXX
			}
			Object anObject = cgElement != null ? cgElement : NOT_AN_OBJECT;
			if (nameHint == NameResolution.NOT_NEEDED) {
				assert debugAllocatedName(nameHint);
				return nameHint;
			}
			String validHint = NameManagerHelper.getValidJavaIdentifier(nameHint, false, anObject);
			boolean isJavaReservedName = NameManagerHelper.reservedJavaNames.contains(validHint);
			boolean isNative = (anObject instanceof CGValuedElement) && isNative((CGValuedElement)anObject);
			if (!isJavaReservedName || isNative) {
				if (anObject != NOT_AN_OBJECT) {
					Object oldElement = name2object.get(validHint);
					if (oldElement == null) {									// New allocation
						name2object.put(validHint, anObject);
						assert debugAllocatedName(validHint);
						return validHint;
					}
					else if (oldElement == anObject) {							// Re-allocation of object
						assert debugAllocatedName(validHint);
						return validHint;
					}
					else {
						nameHint.toString();
					}
				}
				else {
					if (!name2object.containsKey(validHint)) {
						assert debugAllocatedName(validHint);
						return validHint;
					}
				}
			}
			return allocateFallBackName(validHint);
		}

		private boolean debugAllocatedName(@NonNull String name) {
			if (name.contains("context")) {
				getClass();			// XXX
			}
			return true;
		}

		public boolean hasChildren() {
			return hasChildren;
		}

		public boolean hasKey(@NonNull String name) {
			return name2object.containsKey(name);
		}

		private boolean isNative(@NonNull CGValuedElement cgElement) {
			TypeId asTypeId = cgElement.getASTypeId();
			if (asTypeId instanceof NestedTypeId) {
				PackageId packageId = ((NestedTypeId)asTypeId).getParent();
				if ((packageId instanceof RootPackageId) && ((RootPackageId)packageId).getName().contains("://")) {	// e.g. java://
					return true;
				}
			}
			return false;
		}

		public @NonNull String reserveName(@NonNull String name, @NonNull Object object) {
			if ("diagnostics".equals(name)) {
				getClass();			// XXX
			}
			Object old = name2object.put(name, object);
		//	assert old == null;
		//	if (old != null) {
		//		System.out.println(object + " occludes " + old);	// Parameter can hide Property
		//	}
			return name;
		}

		@Override
		public @NonNull String toString() {
			StringBuilder s = new StringBuilder();
			List<@NonNull String> names = new ArrayList<>(name2object.keySet());
			Collections.sort(names);
			for (String name : names) {
				if (s.length() > 0) {
					s.append("\n");
				}
				s.append(name);
				s.append(" = ");
				s.append(name2object.get(name));
			}
			return s.toString();
		}
	}

	protected final @NonNull GlobalNameManager globalNameManager;
	protected final @NonNull NameManagerHelper helper;

	private @Nullable List<@NonNull NestedNameManager> children = null;

	/**
	 * The NameResolution for each element declared in this NameManager.
	 */
//	private final @NonNull Map<@NonNull CGValuedElement, @NonNull NameResolution> element2nameResolution = new HashMap<>();

	/**
	 * All the NameResolutions declared in this NameManager. This avoid repeats from re-used names, or omissions from globl names.
	 */
//	private final @NonNull List<@NonNull NameResolution> nameResolutions = new ArrayList<>();

	protected AbstractNameManager(@Nullable AbstractNameManager parent, @NonNull NameManagerHelper helper) {
		this.globalNameManager = parent != null ? parent.getGlobalNameManager() : (GlobalNameManager)this;
		this.helper = helper;
	}

	protected void addChild(@NonNull NestedNameManager sibling) {
		List<@NonNull NestedNameManager> children2 = children;
		if (children2 == null) {
			this.children = children2 = new ArrayList<>();
		}
		assert helper == sibling.helper;
		children2.add(sibling);
	}

//	public void addNameResolution(@NonNull NameResolution nameResolution) {
//		nameResolutions.add(nameResolution);
//	}

//	public void addNameResolution(@NonNull CGValuedElement cgElement) {
//		NameResolution nameResolution = cgElement.getNameResolution();
//		assert nameResolution.getNameManager() == this;
//		NameResolution old = element2nameResolution.put(cgElement, nameResolution);
//		assert old == null;
//	}

	protected void assignLocalNames(@NonNull Context context, @NonNull Map<@NonNull NameManager, @NonNull List<@NonNull CGValuedElement>> nameManager2namedElements) {
		Iterable<@NonNull CGValuedElement> namedElements = nameManager2namedElements.get(this);
		// XXX		Collections.sort(nameResolutions);
		if (namedElements != null) {
			for (@NonNull CGValuedElement namedElement : namedElements) {
				NameResolution nameResolution = namedElement.getNameResolution();
			//	if (nameResolution.basicGetResolvedName() == null) {
				nameResolution.resolveIn(context, namedElement);
			//	}
			}
		}
	}

	protected void assignNestedNames(@NonNull Map<@NonNull NameManager, @NonNull List<@NonNull CGValuedElement>> nameManager2namedElements) {
		if (children != null) {
			for (@NonNull NestedNameManager child : children) {
				child.assignNames(nameManager2namedElements);
			}
		}
	}

	public @Nullable List<@NonNull NestedNameManager> getChildren() {
		return children;
	}

	@Deprecated // not needed
	protected abstract @NonNull Context getContext();

	@Override
	public @NonNull GlobalNameManager getGlobalNameManager() {
		return globalNameManager;
	}

//	@Override
//	public @NonNull NameManagerHelper getHelper() {
//		return helper;
//	}

	@Override
	public @NonNull String getNameHint(@NonNull Object anObject) {
		return ClassUtil.nonNullState(helper.getNameHint(anObject));
	}
}
