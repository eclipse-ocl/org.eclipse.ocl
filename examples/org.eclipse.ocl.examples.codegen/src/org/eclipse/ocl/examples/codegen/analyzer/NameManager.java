/*******************************************************************************
 * Copyright (c) 2013, 2022 CEA LIST and others.
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
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNamedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.java.JavaConstants;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.ids.NestedTypeId;
import org.eclipse.ocl.pivot.ids.PackageId;
import org.eclipse.ocl.pivot.ids.RootPackageId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.utilities.ClassUtil;

/**
 * A NameManager provides suggestions for names and maintains caches of used names so that model elements are consistently
 * named without collisions.
 */
public abstract class NameManager
{
	/**
	 * Names that will not be allocated to temporary variables.
	 * <p>
	 * This Set is public and unsynchronized. Clients may change it in arbitrary ways at their own risk.
	 * <p>
	 * It is strongly recommended that clients do no more than add additional names.
	 */
	public static final Set<@NonNull String> reservedJavaNames = new HashSet<>();
	{
		reservedJavaNames.add("Boolean");
		reservedJavaNames.add("Character");
		reservedJavaNames.add("Class");
		reservedJavaNames.add("Double");
		reservedJavaNames.add("Float");
		reservedJavaNames.add("Integer");
		reservedJavaNames.add("List");
		reservedJavaNames.add("Long");
		reservedJavaNames.add("Map");
		reservedJavaNames.add("Package");
		reservedJavaNames.add("String");

		reservedJavaNames.add("boolean");
		reservedJavaNames.add("byte");
		reservedJavaNames.add("char");
		reservedJavaNames.add("double");
		reservedJavaNames.add("float");
		reservedJavaNames.add("int");
		reservedJavaNames.add("long");
		reservedJavaNames.add("short");
		reservedJavaNames.add("void");

		reservedJavaNames.add("abstract");		// FIXME Exploit CodeGenUtil.getJavaReservedWords()
		reservedJavaNames.add("assert");
		reservedJavaNames.add("break");
		reservedJavaNames.add("case");
		reservedJavaNames.add("catch");
		reservedJavaNames.add("class");
		reservedJavaNames.add("const");
		reservedJavaNames.add("continue");
		reservedJavaNames.add("default");
		reservedJavaNames.add("do");
		reservedJavaNames.add("else");
		reservedJavaNames.add("enum");
		reservedJavaNames.add("extends");
		reservedJavaNames.add("final");
		reservedJavaNames.add("finally");
		reservedJavaNames.add("for");
		reservedJavaNames.add("goto");
		reservedJavaNames.add("if");
		reservedJavaNames.add("implements");
		reservedJavaNames.add("import");
		reservedJavaNames.add("instanceof");
		reservedJavaNames.add("interface");
		reservedJavaNames.add("native");
		reservedJavaNames.add("new");
		reservedJavaNames.add("package");
		reservedJavaNames.add("private");
		reservedJavaNames.add("protected");
		reservedJavaNames.add("public");
		reservedJavaNames.add("return");
		reservedJavaNames.add("static");
		reservedJavaNames.add("strictfp");
		reservedJavaNames.add("switch");
		reservedJavaNames.add("synchronized");
		reservedJavaNames.add("throw");
		reservedJavaNames.add("throws");
		reservedJavaNames.add("transient");
		reservedJavaNames.add("try");
		reservedJavaNames.add("volatile");
		reservedJavaNames.add("while");

		reservedJavaNames.add("false");
		reservedJavaNames.add("null");
		reservedJavaNames.add("super");
		reservedJavaNames.add(JavaConstants.THIS_NAME);
		reservedJavaNames.add("true");
	}

	/**
	 * Return a valid Java identifier based on nameHint. hasPrefix may be true to indicate that the
	 * caller will supply an additional valid prefix relieving this routine of the need to avoid
	 * leading numeric characters.
	 * <p>
	 * This is not intended to be a reversible algorithm; just to provide something reasonably readable.
	 */
	protected static @NonNull String getValidJavaIdentifier(@NonNull String nameHint, boolean hasPrefix, @Nullable Object anObject) {
		if (nameHint.equals("<")) {
			return("lt");
		}
		else if (nameHint.equals("<=")) {
			return("le");
		}
		else if (nameHint.equals("=")) {
			return("eq");
		}
		else if (nameHint.equals("<>")) {
			return("ne");
		}
		else if (nameHint.equals(">=")) {
			return("ge");
		}
		else if (nameHint.equals(">")) {
			return("gt");
		}
		else if (nameHint.equals("+")) {
			return("sum");
		}
		else if (nameHint.equals("-")) {
			return((anObject instanceof Operation) && ((Operation)anObject).getOwnedParameters().size() <= 0 ? "neg" : "diff");
		}
		else if (nameHint.equals("*")) {
			return("prod");
		}
		else if (nameHint.equals("/")) {
			return("quot");
		}
		else if (nameHint.equals("1_")) {
			return("_1");
		}
		else if (nameHint.equals("2_")) {
			return("_2");
		}
		StringBuilder s = new StringBuilder();
		Character prefix = null;
		int length = nameHint.length();
		for (int i = 0; i < length; i++) {
			char c = nameHint.charAt(i);
			if (((i == 0) && !hasPrefix) ? Character.isJavaIdentifierStart(c) : Character.isJavaIdentifierPart(c)) {
				if (prefix != null) {
					s.append(prefix);
					prefix = null;
				}
				s.append(c);
			}
			else {
				if (c == '*') {
					s.append("_a");
				}
				else if (c == ':') {
					s.append("_c");
				}
				else if (c == '.') {
					if (prefix != null) {
						s.append(prefix);
						prefix = null;
					}
				}
				else if (c == ')') {
					s.append("_e");
				}
				else if (c == '>') {
					s.append("_g");
				}
				else if (c == '<') {
					s.append("_l");
				}
				else if (c == '-') {
					s.append("_m");
				}
				else if (c == '(') {
					s.append("_o");
				}
				else if (c == '+') {
					s.append("_p");
				}
				else if (c == '=') {
					s.append("_q");
				}
				else if (c == '/') {
					s.append("_s");
				}
				else {
					s.append('_' + Integer.toString(c));
				}
				prefix = '_';
			}
		}
		return s.toString();
	}

	/**
	 * name2object place holders for names with no real object.
	 */
	/*private*/ static final @NonNull Class<?> NOT_AN_OBJECT = NameManager.class;

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
		private final @NonNull NameManager nameManager;
		private @NonNull Map<@NonNull String, @NonNull Object> name2object;		// User of each name, null if name ambiguous
		private @Nullable Map<@NonNull String, @NonNull Integer> name2counter;	// Auto-generation counter for each colliding name
		private boolean hasChildren = false;									// Set tru once a child context has copied state

		protected Context(@NonNull NameManager nameManager) {
			this.nameManager = nameManager;
			if (nameManager.isGlobal()) {
				this.name2object = new HashMap<>();
				this.name2counter = null;
			}
			else {
				NameManager parent = ((NestedNameManager)nameManager).parent;
				Context parentContext = parent.getContext();
				parentContext.hasChildren = true;
				this.name2object = new HashMap<>(parentContext.name2object);
				this.name2counter = parentContext.name2counter != null ? new HashMap<>(parentContext.name2counter) : null;
			}
		}

		protected @NonNull String allocateUniqueName(@NonNull String nameHint, @NonNull Object anObject) {
			String validHint = getValidJavaIdentifier(nameHint, false, anObject);
			boolean isJavaReservedName = reservedJavaNames.contains(validHint);
			boolean isNative = (anObject instanceof CGValuedElement) && isNative((CGValuedElement)anObject);
			if (!isJavaReservedName || isNative) {
				if (anObject != NOT_AN_OBJECT) {
					Object oldElement = name2object.get(validHint);
					if (oldElement == null) {									// New allocation
						if ("diagnostics".equals(validHint)) {
							getClass();			// XXX
						}
						name2object.put(validHint, anObject);
						return validHint;
					}
					else if (oldElement == anObject) {							// Re-allocation of object
						return validHint;
					}
					else {
						nameHint.toString();
					}
				}
				else {
					if (!name2object.containsKey(validHint)) {
						return validHint;
					}
				}
				/*<<<<<<< Upstream, based on origin/master
			}
			String lastResort = null;
			if (nameHints != null) {
				boolean isNative = (anObject instanceof CGValuedElement) && isNative((CGValuedElement)anObject);
				boolean hasRawNameHint = false;
				for (int hintSuffix = 0; lastResort == null; hintSuffix++) {
					for (String rawNameHint : nameHints) {
						if (rawNameHint != null)  {
							hasRawNameHint = true;
							String nameHint = hintSuffix > 0 ? rawNameHint + hintSuffix : rawNameHint;
							String validHint = getValidJavaIdentifier(nameHint, false, anObject);
							if (!reservedJavaNames.contains(validHint) || isNative) {
								if (anObject != null) {
									Object oldElement = name2object.get(validHint);
									if (oldElement == anObject) {
										return validHint;
									}
									if ((oldElement == null) && !name2object.containsKey(validHint)) {
										install(validHint, anObject);
										return validHint;
									}
									else {
										nameHint.toString();
									}
								}
								else {
									if (!name2object.containsKey(validHint)) {
										install(validHint, anObject);
										return validHint;
									}
								}
								if (lastResort == null) {
									lastResort = validHint;
								}
							}
						}
					}
					if (!hasRawNameHint) {
						break;
					}
				}
>>>>>>> 59e88ef [578443] Use disciplined NameResolution*/
			}
			Map<@NonNull String, @NonNull Integer> name2counter2 = name2counter;
			if (name2counter2 == null) {
				name2counter = name2counter2 = new HashMap<>();
			}
			Integer counter = name2counter2.get(validHint);
			int count = counter != null ? counter : 0;
			for ( ; true; count++) {
				String attempt = validHint + "_" + Integer.toString(count);
				if (!name2object.containsKey(attempt)) {		// Assumes that reserved names do not end in _ count
					name2counter2.put(validHint, ++count);
					return attempt;
				}
			}
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

		public void reserveName(@NonNull String name, @NonNull Object object) {
			if ("diagnostics".equals(name)) {
				getClass();			// XXX
			}
			Object old = name2object.put(name, object);
		//	assert old == null;
		//	if (old != null) {
		//		System.out.println(object + " occludes " + old);	// Parameter can hide Property
		//	}
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
	private final @NonNull Map<@NonNull CGValuedElement, @NonNull BaseNameResolution> element2baseNameResolution = new HashMap<>();

	/**
	 * All the NameResolutions declared in this NameManager. This avoid repeats from re-used names, or omissions from globl names.
	 */
	private final @NonNull List<@NonNull NameResolution> nameResolutions = new ArrayList<>();

	protected NameManager(@Nullable NameManager parent, @NonNull NameManagerHelper helper) {
		this.globalNameManager = parent != null ? parent.globalNameManager : (GlobalNameManager)this;
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

	public void addNameResolution(@NonNull NameResolution nameResolution) {
		nameResolutions.add(nameResolution);
	}

	public void addNameResolution(@NonNull CGValuedElement cgElement) {
		NameResolution nameResolution = cgElement.getNameResolution();
		assert nameResolution.getNameManager() == this;
		if (nameResolution instanceof BaseNameResolution) {
			NameResolution old = element2baseNameResolution.put(cgElement, (BaseNameResolution)nameResolution);
			assert old == null;
		}
	}

	protected void assignNames(@NonNull Context context) {
		List<@NonNull BaseNameResolution> baseNameResolutions = new ArrayList<>(element2baseNameResolution.values());
// XXX		Collections.sort(nameResolutions);
		for (@NonNull BaseNameResolution baseNameResolution : baseNameResolutions) {
		//	if (baseNameResolution.basicGetResolvedName() == null) {
				baseNameResolution.resolveIn(context);
		//	}
		}
		if (children != null) {
			for (@NonNull NestedNameManager child : children) {
				child.assignNames();
			}
		}
	}

	public @NonNull NestedNameManager createNestedNameManager(@NonNull CGNamedElement cgScope) {
		return new NestedNameManager(this, cgScope);
	}

	/**
	 * Declare that cgElement must eventually have a distinct name that can default to its natural value once all other
	 * name preferences have been satisfied. This is the normal form of name resolution.
	 */
	public @NonNull NameResolution declareLazyName(@NonNull CGValuedElement cgElement) {
		NameResolution nameResolution = cgElement.basicGetNameResolution();
		if (nameResolution != null) {
			return nameResolution;
		}
		CGValuedElement cgNamedValue = cgElement.getNamedValue();
		nameResolution = cgNamedValue.basicGetNameResolution();
		if (nameResolution == null) {
			String nameHint = getLazyNameHint(cgNamedValue);		// globals must have a name soon, nested resolve later
			nameResolution = new BaseNameResolution(this, cgNamedValue, nameHint);
		}
		if (cgElement != cgNamedValue) {
			nameResolution.addCGElement(cgElement);
		}
		return nameResolution;
	}

	/**
	 * Declare that cgElement has a name which should immediately default to nameHint.
	 * This is typically used to provide an eager name reservation for an Ecore operation parameter.
	 */
	public abstract @NonNull BaseNameResolution declareReservedName(@NonNull CGValuedElement cgElement, @NonNull String nameHint);

	@Deprecated // not needed
	protected abstract @NonNull Context getContext();

	public @NonNull GlobalNameManager getGlobalNameManager() {
		return globalNameManager;
	}

	public @NonNull String getNameHint(@NonNull Object anObject) {
		return ClassUtil.nonNullState(helper.getNameHint(anObject));
	}

	protected abstract @Nullable String getLazyNameHint(@NonNull CGValuedElement cgNamedValue);

	public abstract boolean isGlobal();

	public void removeNameResolution(@NonNull CGValuedElement cgElement) {
		NameResolution old = element2baseNameResolution.remove(cgElement);
		assert old != null;
	}
}
