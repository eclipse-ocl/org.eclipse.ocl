/*******************************************************************************
 * Copyright (c) 2022 Willink Transformations and others.
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
import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.CodeGenConstants;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNamedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNativeOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGVariableExp;
import org.eclipse.ocl.examples.codegen.naming.AbstractNameManager.Context;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.TracingOption;

/**
 * A NameResolution represents a future name for the value of an expression and for all unmodified clients of that value.
 *
 * The need for a neme is first declared and an actual name, hierarchically unique within the NameManager unique,
 * is assigned just before the CG2Java generation.
 *  </p>
 *  Declarations occur at various times.
 *  </br>Java reserved words are excluded.
 *  </br>However certain reserved words such as "this" my be assigned for precisely their Java purpose.
 *  </br>Global names may be declared in the GlobalNameManager.
 *  </br>Local names may be declared in the appropriate NestedNameManager.
 *  </p>
 *  Reserved globals are typically declared and assigned during CodeGenerator/GlobalContext construction.
 *  </br>Incidental globals are typically declared during AS2CG and possibly CG3JavaPre
 *  </br>Locals may be pre-emptively declared during AS2CG or CG3JavaPre often to support additional variants.
 *  </br>Residual locals are declared at the start of CodeGenerator resolveNames.
 *  </br>Unassigned names are resolved at the end of CodeGenerator resolveNames.
 *  </br>All names are resolved when needed by CG2Java.
 */
public abstract class NameResolution
{
	/**
	 * An Eager NameResolution specifies an exact symbol name spelling for preferred allocation in some scope.
	 */
	public static abstract class Eager extends NameResolution
	{
		/**
		 * The resolved name based on nameHint after ensuring that it is unique at and below the nameManager. Non-null once resolved.
		 */
		protected final @NonNull String resolvedName;

		protected Eager(@NonNull NameManager nameManager, @Nullable CGNamedElement primaryElement, @NonNull String resolvedName) {
			super(nameManager, primaryElement);
			this.resolvedName = resolvedName;
			if (NAMES_RESOLVE.isActive()) {
				StringBuilder s = new StringBuilder();
				s.append("setResolvedName " + NameUtil.debugSimpleName(this) + " in " + NameUtil.debugSimpleName(nameManager) + " => " + resolvedName);
				if (cgElements != null) {
					for (@NonNull CGNamedElement cgElement2 : cgElements) {
						s.append(" " + NameUtil.debugSimpleName(cgElement2));
					}
				}
				NAMES_RESOLVE.println(s.toString());
			}
			assert debugNameHint(resolvedName);
		}

		@Override
		public @NonNull String basicGetResolvedName() {
			return resolvedName;
		}

		@Override
		public @NonNull String getNameHint() {
			return resolvedName;
		}

		@Override
		public @NonNull String getResolvedName() {
			return resolvedName;
		}

		@Override
		public boolean isUnresolved() {
			return false;
		}

		@Override
		public void resolveNameHint() {}

		@Override
		public @NonNull String toString() {
			return nameManager + " : " + resolvedName;
		}
	}

	/**
	 * An EagerGlobal NameResolution specifies an exact symbol name spelling for immediate allocation to the global scope.
	 */
	public static class EagerGlobal extends Eager
	{
		public EagerGlobal(@NonNull GlobalNameManager nameManager, @Nullable CGNamedElement primaryElement, @NonNull String eagerName) {
			super(nameManager, primaryElement, eagerName);
		}

		@Override
		public @NonNull String resolveIn(@NonNull Context context, @Nullable CGNamedElement cgElement) {
			// context.allocateEagerName(resolvedName, cgElement != null ? cgElement : primaryElement); -- invoked after construction
			return resolvedName;
		}
	}

	/**
	 * A EagerNested NameResolution specifies an exact symbol name spelling for eventual allocation to a nested scope.
	 */
	public static class EagerNested extends Eager
	{
		private boolean allocated = false;

		public EagerNested(@NonNull NestedNameManager nameManager, @NonNull CGNamedElement primaryElement, @NonNull String eagerName) {
			super(nameManager, primaryElement, eagerName);
		}

		@Override
		public @NonNull String resolveIn(@NonNull Context context, @Nullable CGNamedElement cgElement) {
			if (!allocated) {
				context.allocateEagerName(resolvedName, cgElement != null ? cgElement : primaryElement);
				allocated = true;
			}
			return resolvedName;
		}
	}

	/**
	 * A Lazy NameResolution requests a unique name to be lazily allocated in a given scope.
	 */
	public static class Lazy extends NameResolution
	{
		/**
		 * A hint as to what could make a readable resolved name.
		 */
		protected @NonNull String nameHint;

		/**
		 * The resolved name based on nameHint after ensuring that it is unique at and below the nameManager. Non-null once resolved.
		 */
		private @Nullable String resolvedName = null;

		public Lazy(@NonNull NameManager nameManager, @NonNull CGNamedElement primaryElement, @Nullable String nameHint) {
			super(nameManager, primaryElement);
			this.nameHint = nameHint != null ? nameHint : UNRESOLVED;
			assert debugNameHint(this.nameHint);
		//	resolveNameHint();				// Redundant, just for debugging
		}

		@Override
		public @Nullable String basicGetResolvedName() {
			return resolvedName != UNRESOLVED ? resolvedName : null;
		}

		@Override
		public @NonNull String getNameHint() {
			return nameHint;
		}

		@Override
		public @NonNull CGNamedElement getPrimaryElement() {
			assert primaryElement != null;
			return primaryElement;
		}

		@Override
		public @NonNull String getResolvedName() {
			return ClassUtil.nonNullState(basicGetResolvedName());
		}

		@Override
		public boolean isUnresolved() {
			return nameHint == UNRESOLVED;
		}

		/**
		 * Provide the resolution of a name using the constructed hint
		 * @return
		 */
		@Override
		public @NonNull String resolveIn(@NonNull Context context, @Nullable CGNamedElement cgElement) {		// XXX use @Nullable cgElement
			assert nameHint != UNRESOLVED;
			String resolvedName2 = resolvedName;
			if (resolvedName2 == null) {
				if (cgElement == null) {
					cgElement = primaryElement;
				}
				resolvedName2 = context.allocateLazyName(getNameHint(), cgElement);
				setResolvedName(resolvedName2);
			}
			return resolvedName2;
		}

		@Override
		public void resolveNameHint() {
			assert !inhibitNameResolution || (nameManager instanceof GlobalNameManager);
			if (nameHint == UNRESOLVED) {
				CGNamedElement primaryElement2 = primaryElement;
				assert primaryElement2 != null;
				nameHint = nameManager.getNameHint(primaryElement2);
				assert debugNameHint(nameHint);
			}
		}

		protected void setResolvedName(@NonNull String resolvedName) {
			assert !inhibitNameResolution;
			if ("eColor_0".equals(resolvedName)) {
				getClass();		// XXX
			}
			if (resolvedName.contains("eColor")) {
				getClass();		// XXX
			}
			assert !resolvedName.contains("UNRESOLVED");
			this.resolvedName = resolvedName;
		//	if (primaryElement != null) {
		//		System.out.println(nameHint + " => " + resolvedName + " : " + primaryElement.eClass().getName()  + " : " + nameManager.getClass().getSimpleName());
		//	}
		//	else {
		//		System.out.println(nameHint + " => " + resolvedName);
		//	}
			if (NAMES_RESOLVE.isActive()) {
				StringBuilder s = new StringBuilder();
				s.append("setResolvedName " + NameUtil.debugSimpleName(this) + " in " + NameUtil.debugSimpleName(nameManager) + " " + nameHint + " => " + resolvedName);
				if (cgElements != null) {
					for (@NonNull CGNamedElement cgElement2 : cgElements) {
						s.append(" " + NameUtil.debugSimpleName(cgElement2));
					}
				}
				NAMES_RESOLVE.println(s.toString());
			}
		}

		@Override
		public @NonNull String toString() {
			return nameManager + " : " + nameHint + " => " + (resolvedName != null ? resolvedName : "???");
		}
	}

	public static final @NonNull TracingOption NAMES_GATHER = new TracingOption(CodeGenConstants.PLUGIN_ID, "names/gather");
	public static final @NonNull TracingOption NAMES_RESOLVE = new TracingOption(CodeGenConstants.PLUGIN_ID, "names/resolve");

	public static boolean inhibitNameResolution = true;
	/**
	 * A non-null placeholder for a nameHint whose resolution is deferred until the CG containment tree is sound.
	 */
	private static final @NonNull String UNRESOLVED = "«UNRESOLVED»";

	/**
	 * A non-null placeholder for a nameHint whose resolution will not be used.
	 */
	public static final @NonNull String NOT_NEEDED = "«NOT_NEEDED»";

	/**
	 * The namespace at and below whch this resolved name and all its variants must be unique.
	 */
	protected final @NonNull NameManager nameManager;

	/**
	 * The CGElement that computes the value to be accessed by the resolved name. May be null for uncomputed globals.
	 */
	protected final @Nullable CGNamedElement primaryElement;

	/**
	 * Additional CGElements that propagate the unchanged value to be accessed by the resolved name.
	 */
	protected @Nullable List<@NonNull CGNamedElement> cgElements = null;		// XXX obsolete

	protected NameResolution(@NonNull NameManager nameManager, @Nullable CGNamedElement primaryElement) {//, @Nullable String nameHint) {
		this.nameManager = nameManager;
		this.primaryElement = primaryElement;
	//	assert (primaryElement != null) || (getNameHint() != null);
		if (primaryElement instanceof CGNativeOperationCallExp) {
			getClass();		// XXX
		}
		if (nameManager instanceof NestedNameManager) {
			getClass();		// XXX)
		}
//		this.nameHint = nameHint != null ? nameHint : UNRESOLVED;
		assert (primaryElement == null) || nameManager.isGlobal() || !primaryElement.isGlobal();		// XXX derived -- nested CGClass is ?? not global
	//	if (primaryElement == null) {
	//		assert nameHint != null : "Expected NameResolution for null";
	//	}
	/*	else {			-- too fussy
			boolean expectNameHint = nameManager.isGlobal() || (primaryElement instanceof CGCallable) || (primaryElement instanceof CGProperty) || (primaryElement instanceof CGVariable);
			if (expectNameHint) {
				assert nameHint != null : "Expected NameResolution for " + primaryElement.getClass().getName();
			}
			else {
				assert nameHint == null : "Unexpected NameResolution for " + primaryElement.getClass().getName();
			}
		} */
		assert !(primaryElement instanceof CGVariableExp) : "Should have redirected to getNamedValue()";
		if ((primaryElement != null) && "u.oclAsType(SysML_ValueTypes_QUDV::QUDV::ConversionBasedUnit)".equals(String.valueOf(primaryElement.getAst()))) {
			getClass();		// XXX
		}
		if (primaryElement != null) {
			addCGElement(primaryElement);
		}
	//	nameManager.addNameResolution(this);
	//	System.out.println("NameResolution '" + nameHint + "' : " + nameManager.toString() + " : " + primaryElement);
	}

	public void addCGElement(@NonNull CGNamedElement cgElement) {
		//	assert !inhibitNameResolution || (nameManager instanceof GlobalNameManager);
		//	assert !inhibitNameResolution || nameManager.isGlobal();
		if (String.valueOf(primaryElement).contains("oclContainer")) {
			getClass();		// XXX
		}
		List<@NonNull CGNamedElement> cgElements2 = cgElements;
		if (cgElements2 == null) {
			cgElements = cgElements2 = new ArrayList<>();
		}
		else {
		//	assert !cgElements2.contains(cgElement);
		}
		if (!cgElements2.contains(cgElement)) {		// XXX
			cgElements2.add(cgElement);
		}
		if (this != cgElement.basicGetNameResolution()) {			// XXX
			cgElement.setNameResolution(this);
		//	getNameManager().addNameResolution(cgElement);
		}
	//	System.out.println("addCGElement '" + this + "' : " + cgElement.eClass().getName() + ":" + cgElement);
	//	assert (primaryElement == null) || (cgElements2.size() == 1);
	}

	public @Nullable CGNamedElement basicGetPrimaryElement() {
		return primaryElement;
	}

	public abstract @Nullable String basicGetResolvedName();

	protected boolean debugNameHint(@NonNull String newName) {
		if (newName.contains("AbstractComputation")) {
			getClass();		// XXX
		}
		if ("IMPPROPid_d3atlExpression".equals(newName)) {
			getClass();			// XXX
		}
		if ("create".equals(newName)) {
			getClass();			// XXX
		}
		return true;
	}

	public @Nullable Iterable<@NonNull CGNamedElement> getCGElements() {
		return cgElements;
	}

	public abstract @NonNull String getNameHint();

	public @NonNull NameManager getNameManager() {
		return nameManager;
	}

	public @NonNull CGNamedElement getPrimaryElement() {
		return ClassUtil.nonNullState(primaryElement);
	}

	public abstract @NonNull String getResolvedName();

	public boolean isUnresolved() {
		return getNameHint() == UNRESOLVED;
	}

	/**
	 * Provide the resolution of a non-global name using cgElement to provide the nameHint.
	 */
	public abstract @NonNull String resolveIn(@NonNull Context context, @Nullable CGNamedElement cgElement);

	/**
	 * Promote any UNRESOLVED resolution to at least hinted by computing the default nameHint.
	 */
	public abstract void resolveNameHint();
}