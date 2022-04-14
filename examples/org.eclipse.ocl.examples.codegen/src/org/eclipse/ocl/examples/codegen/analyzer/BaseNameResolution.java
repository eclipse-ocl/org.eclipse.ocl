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

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.analyzer.NameManager.Context;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCallable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTuplePart;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGVariable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGVariableExp;
import org.eclipse.ocl.pivot.utilities.ClassUtil;

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
public class BaseNameResolution extends AbstractNameResolution
{
	/**
	 * A non-null placeholder for a nameHint whose resolution is deferred until the CG containment tree is sound.
	 */
	private static final @NonNull String UNRESOLVED = "«UNRESOLVED»";

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

	public BaseNameResolution(@NonNull NameManager nameManager, @Nullable CGValuedElement primaryElement, @Nullable String nameHint) {
		this.nameManager = nameManager;
		this.primaryElement = primaryElement;
		assert (primaryElement != null) || (nameHint != null);
		if (nameManager instanceof NestedNameManager) {
			getClass();		// XXX)
		}
		this.nameHint = nameHint != null ? nameHint : UNRESOLVED;
		assert debugNameHint();
		if (primaryElement instanceof CGTuplePart) {
			primaryElement.isGlobal();
			getClass();		// XXX
		}
		assert (primaryElement == null) || nameManager.isGlobal() || !primaryElement.isGlobal();
		if (primaryElement == null) {
			assert nameHint != null : "Expected BaseNameResolution for null";
		}
		else {
			boolean expectNameHint = nameManager.isGlobal() || (primaryElement instanceof CGCallable) || (primaryElement instanceof CGProperty) || (primaryElement instanceof CGVariable);
			if (expectNameHint) {
				assert nameHint != null  : "Expected BaseNameResolution for " + primaryElement.getClass().getName();
			}
			else {
				assert nameHint == null : "Unexpected BaseNameResolution for " + primaryElement.getClass().getName();
			}
		}
		assert !(primaryElement instanceof CGVariableExp) : "Should have redirected to getNamedValue()";
		if (primaryElement != null) {
			addCGElement(primaryElement);
		}
		nameManager.addNameResolution(this);
	//	System.out.println("BaseNameResolution '" + nameHint + "' : " + nameManager.toString() + " : " + primaryElement);
	}

	public @Nullable CGValuedElement basicGetPrimaryElement() {
		return primaryElement;
	}

	@Override
	public @Nullable String basicGetResolvedName() {
		return resolvedName;
	}

	protected boolean debugNameHint() {
		if (nameHint.contains("manyDates")) {
			getClass();		// XXX
		}
		if ("TUP__1".equals(nameHint)) {
			getClass();			// XXX
		}
		if ("diagnostics".equals(nameHint)) {
			getClass();		// XXX
		}
		if ("getSeverity".equals(nameHint)) {
			getClass();		// XXX
		}
		return true;
	}

	@Override
	public @NonNull BaseNameResolution getBaseNameResolution() {
		return this;
	}

	@Override
	public @NonNull String getNameHint() {
		return nameHint;
	}

	@Override
	public @NonNull NameManager getNameManager() {
		return nameManager;
	}

	public @NonNull CGValuedElement getPrimaryElement() {
		return ClassUtil.nonNullState(primaryElement);
	}

	@Override
	public @NonNull String getResolvedName() {
		assert !isUnresolved();
		return ClassUtil.nonNullState(resolvedName);
	}

	@Override
	public boolean isUnresolved() {
		return nameHint == UNRESOLVED;
	}

	public void resolveIn(@NonNull Context context) {
	//	if (nameHint == UNRESOLVED) {
	//		CGValuedElement primaryElement2 = primaryElement;
	//		assert primaryElement2 != null;
	//		nameHint = nameManager.getNameHint(primaryElement2);
	//	}
		assert !isUnresolved();
		Object cgElement = primaryElement != null ? primaryElement : NameManager.NOT_AN_OBJECT;
		if (resolvedName == null) {
			String resolvedName = context.allocateUniqueName(getNameHint(), cgElement);
			setResolvedName(resolvedName);
		}
		resolveVariants(context, cgElement);
	}

	@Override
	public void resolveNameHint() {
		if (nameHint == UNRESOLVED) {
			CGValuedElement primaryElement2 = primaryElement;
			assert primaryElement2 != null;
			nameHint = nameManager.getNameHint(primaryElement2);
			assert debugNameHint();
		}
	}

	protected void setResolvedName(@NonNull String resolvedName) {
		if ("a".equals(resolvedName)) {
			getClass();		// XXX
		}
		this.resolvedName = resolvedName;
		if (primaryElement != null) {
			System.out.println(nameHint + " => " + resolvedName + " : " + primaryElement.eClass().getName()  + " : " + nameManager.getClass().getSimpleName());
		}
		else {
			System.out.println(nameHint + " => " + resolvedName);
		}

	}

	@Override
	public @NonNull String toString() {
		return nameManager + " : " + nameHint + " => " + (resolvedName != null ? resolvedName : "???");
	}
}