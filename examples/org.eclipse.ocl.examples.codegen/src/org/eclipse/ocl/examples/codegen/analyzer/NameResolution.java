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
package org.eclipse.ocl.examples.codegen.analyzer;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.CodeGenConstants;
import org.eclipse.ocl.examples.codegen.analyzer.NameManager.Context;
import org.eclipse.ocl.examples.codegen.cgmodel.CGBuiltInIterationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGVariableExp;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.TracingOption;

/**
 * A NameResolution represents a future name for the value of an expression and for all unmodified clients of that value.
 *
 * The need for a neme is first declared and an actual name, hierarchically unique within the NameManager unique,
 * is assigned just before the CG2Java generation.
 *  </p>
 *  Declarations occur at various timres.
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
public class NameResolution
{
	public static final @NonNull TracingOption NAMES_GATHER = new TracingOption(CodeGenConstants.PLUGIN_ID, "names/gather");
	public static final @NonNull TracingOption NAMES_RESOLVE = new TracingOption(CodeGenConstants.PLUGIN_ID, "names/resolve");

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
	 * A hint as to what could make a readable resolved name.
	 */
	private @NonNull String nameHint;

	/**
	 * The CGElement that computes the value to be accessed by the resolved name. May be null for uncomputed globals.
	 */
	protected final @Nullable CGValuedElement primaryElement;

	/**
	 * The resolved name based on nameHint after ensuring that it is unique at and below the nameManager. Non-null once resolved.
	 */
	private @Nullable String resolvedName = null;

	/**
	 * Additional CGElements that propagate the unchanged value to be accessed by the resolved name.
	 */
	private @Nullable List<@NonNull CGValuedElement> cgElements = null;

	public NameResolution(@NonNull NameManager nameManager, @Nullable CGValuedElement primaryElement, @Nullable String nameHint) {
		this.nameManager = nameManager;
		this.primaryElement = primaryElement;
		assert (primaryElement != null) || (nameHint != null);
		if (nameManager instanceof NestedNameManager) {
			getClass();		// XXX)
		}
		if (primaryElement instanceof CGBuiltInIterationCallExp) {
			getClass();		// XXX)
		}
		this.nameHint = nameHint != null ? nameHint : UNRESOLVED;
		assert debugNameHint();
		assert (primaryElement == null) || nameManager.isGlobal() || !primaryElement.isGlobal();
		if (primaryElement == null) {
			assert nameHint != null : "Expected NameResolution for null";
		}
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

	public void addCGElement(@NonNull CGValuedElement cgElement) {
		List<@NonNull CGValuedElement> cgElements2 = cgElements;
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

	public @Nullable CGValuedElement basicGetPrimaryElement() {
		return primaryElement;
	}

	public @Nullable String basicGetResolvedName() {
		return resolvedName != UNRESOLVED ? resolvedName : null;
	}

	protected boolean debugNameHint() {
		if (nameHint.contains("manyDates")) {
			getClass();		// XXX
		}
		if ("IMPPROPid_t3forwardList".equals(nameHint)) {
			getClass();			// XXX
		}
		if ("ast".equals(nameHint)) {
			getClass();			// XXX
		}
		return true;
	}

	public @Nullable Iterable<@NonNull CGValuedElement> getCGElements() {
		return cgElements;
	}

	public @NonNull String getNameHint() {
		return nameHint;
	}

	public @NonNull NameManager getNameManager() {
		return nameManager;
	}

	public @NonNull CGValuedElement getPrimaryElement() {
		return ClassUtil.nonNullState(primaryElement);
	}

	public @NonNull String getResolvedName() {
	/*	StringBuilder s = new StringBuilder();
		s.append("getResolvedName " + NameUtil.debugSimpleName(this) + " in " + NameUtil.debugSimpleName(nameManager) + " " + nameHint + " => " + resolvedName);
		if (cgElements != null) {
			for (@NonNull CGValuedElement cgElement : cgElements) {
				s.append(" " + NameUtil.debugSimpleName(cgElement));
			}
		}
		System.out.println(s.toString());
	*/	// XXX assert !isUnresolved();	-- maybe unresolved if containerless as a result of a CSE rewrite
		return ClassUtil.nonNullState(basicGetResolvedName());
	}

	public boolean isUnresolved() {
		return nameHint == UNRESOLVED;
	}

	/**
	 * Provide the resolution of a global name using the constructed hint
	 */
	public void resolveIn(@NonNull Context context) {
		assert !isUnresolved();
		assert resolvedName == null;
		String resolvedName = context.allocateUniqueName(getNameHint(), NameManager.NOT_AN_OBJECT);
		setResolvedName(resolvedName);
		if (NAMES_RESOLVE.isActive()) {
			StringBuilder s = new StringBuilder();
			s.append(NameUtil.debugSimpleName(this) + " in " + NameUtil.debugSimpleName(nameManager) + " " + nameHint + " => " + resolvedName);
			if (cgElements != null) {
				for (@NonNull CGValuedElement cgElement2 : cgElements) {
					s.append(" " + NameUtil.debugSimpleName(cgElement2));
				}
			}
			NAMES_RESOLVE.println(s.toString());
		}
		assert resolvedName != null;
	}

	/**
	 * Provide the resolution of a non-global name using cgElement to provide the nameHint.
	 */
	public void resolveIn(@NonNull Context context, @NonNull CGValuedElement cgElement) {
		assert !isUnresolved();
		if (resolvedName == null) {
		//	CGPackage cgPackage = CGUtil.basicGetContainingPackage(cgElement);
		//	assert (cgPackage != null) || cgElement.isGlobal() || (cgElement instanceof CGLibraryOperation) || (cgElement instanceof CGNativeOperation);
			String resolvedName = context.allocateUniqueName(getNameHint(), cgElement);
			setResolvedName(resolvedName);
		}
		if (NAMES_RESOLVE.isActive()) {
			StringBuilder s = new StringBuilder();
			s.append("resolveIn " + NameUtil.debugSimpleName(this) + " in " + NameUtil.debugSimpleName(nameManager) + " " + nameHint + " => " + resolvedName);
			if (cgElements != null) {
				for (@NonNull CGValuedElement cgElement2 : cgElements) {
					s.append(" " + NameUtil.debugSimpleName(cgElement2));
				}
			}
			NAMES_RESOLVE.println(s.toString());
		}
		assert resolvedName != null;
	}

	public void resolveNameHint() {
		if (nameHint == UNRESOLVED) {
			CGValuedElement primaryElement2 = primaryElement;
			assert primaryElement2 != null;
			nameHint = nameManager.getNameHint(primaryElement2);
			if ("_171_UNRESOLVED_187".equals(nameHint)) {			// XXX
				nameManager.getNameHint(primaryElement2);
			}
			if ("XXX_171_UNRESOLVED_187".equals(nameHint)) {			// XXX
				nameManager.getNameHint(primaryElement2);
			}
			assert !"_171_UNRESOLVED_187".equals(nameHint);
			assert debugNameHint();
		}
	}

	protected void setResolvedName(@NonNull String resolvedName) {
		if ("allInstances".equals(resolvedName)) {
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
	}

	@Override
	public @NonNull String toString() {
		return nameManager + " : " + nameHint + " => " + (resolvedName != null ? resolvedName : "???");
	}
}