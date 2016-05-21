/**
 * Copyright (c) 2014, 2016 Willink Transformations Ltd., University of York and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at 
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Adolfo Sanchez-Barbudo Herrera (University of York) - initial API and implementation
 */
package org.eclipse.ocl.pivot.internal.lookup;

import java.util.Collection;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.evaluation.Executor;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Environment</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.pivot.internal.lookup.LookupEnvironment#getNamedElements <em>Named Elements</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.lookup.LookupEnvironment#getParentEnv <em>Parent Env</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.pivot.internal.lookup.LookupPackage#getLookupEnvironment()
 * @model
 * @generated
 */
public interface LookupEnvironment extends EObject {
	/**
	 * Returns the value of the '<em><b>Named Elements</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.ocl.pivot.NamedElement}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Named Elements</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * @since 1.1
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Named Elements</em>' reference list.
	 * @see org.eclipse.ocl.pivot.internal.lookup.LookupPackage#getLookupEnvironment_NamedElements()
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel get='throw new UnsupportedOperationException();  // FIXME Unimplemented http://www.eclipse.org/ocl/2015/Lookup!LookupEnvironment!namedElements'"
	 * @generated
	 */
	EList<NamedElement> getNamedElements();

	/**
	 * Returns the value of the '<em><b>Parent Env</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parent Env</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * @since 1.1
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parent Env</em>' reference.
	 * @see #setParentEnv(LookupEnvironment)
	 * @see org.eclipse.ocl.pivot.internal.lookup.LookupPackage#getLookupEnvironment_ParentEnv()
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel get='throw new UnsupportedOperationException();  // FIXME Unimplemented http://www.eclipse.org/ocl/2015/Lookup!LookupEnvironment!parentEnv'"
	 * @generated
	 */
	LookupEnvironment getParentEnv();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.pivot.internal.lookup.LookupEnvironment#getParentEnv <em>Parent Env</em>}' reference.
	 * <!-- begin-user-doc -->
	 * @since 1.1
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parent Env</em>' reference.
	 * @see #getParentEnv()
	 * @generated
	 */
	void setParentEnv(LookupEnvironment value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model required="true" elementsType="org.eclipse.ocl.pivot.oclstdlib.Collection<NE>" elementsRequired="true"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='/**\n * LookupEnvironment{namedElements = namedElements->includingAll(elements)}\n \052/\nfinal /*@NonInvalid\052/ org.eclipse.ocl.pivot.evaluation.@<%org.eclipse.jdt.annotation.NonNull%> Executor executor = <%org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal%>.getExecutor(this);\nfinal /*@NonInvalid\052/ org.eclipse.ocl.pivot.ids.@<%org.eclipse.jdt.annotation.NonNull%> IdResolver idResolver = executor.getIdResolver();\nfinal /*@NonInvalid\052/ org.eclipse.ocl.pivot.@<%org.eclipse.jdt.annotation.NonNull%> Property CTORid_namedElements = idResolver.getProperty(<%org.eclipse.ocl.pivot.internal.lookup.LookupTables%>.PROPid_namedElements);\nfinal /*@NonInvalid\052/ org.eclipse.ocl.pivot.@<%org.eclipse.jdt.annotation.NonNull%> Class TYP_lookup_c_c_LookupEnvironment_0 = idResolver.getClass(<%org.eclipse.ocl.pivot.internal.lookup.LookupTables%>.CLSSid_LookupEnvironment, null);\nfinal /*@Thrown\052/ org.eclipse.ocl.pivot.internal.lookup.@<%org.eclipse.jdt.annotation.NonNull%> LookupEnvironment symbol_0 = (<%org.eclipse.ocl.pivot.internal.lookup.LookupEnvironment%>)TYP_lookup_c_c_LookupEnvironment_0.createInstance();\n@SuppressWarnings(\"null\")\nfinal /*@Thrown\052/ java.util.@<%org.eclipse.jdt.annotation.NonNull%> List<<%org.eclipse.ocl.pivot.NamedElement%>> namedElements = this.getNamedElements();\nfinal /*@Thrown\052/ org.eclipse.ocl.pivot.values.@<%org.eclipse.jdt.annotation.NonNull%> OrderedSetValue BOXED_namedElements = idResolver.createOrderedSetOfAll(<%org.eclipse.ocl.pivot.internal.lookup.LookupTables%>.ORD_CLSSid_NamedElement, namedElements);\nfinal /*@NonInvalid\052/ org.eclipse.ocl.pivot.values.@<%org.eclipse.jdt.annotation.NonNull%> CollectionValue BOXED_elements = idResolver.createCollectionOfAll(<%org.eclipse.ocl.pivot.internal.lookup.LookupTables%>.COL_TMPLid_, elements);\nfinal /*@Thrown\052/ org.eclipse.ocl.pivot.values.@<%org.eclipse.jdt.annotation.NonNull%> CollectionValue includingAll = <%org.eclipse.ocl.pivot.library.collection.CollectionIncludingAllOperation%>.INSTANCE.evaluate(BOXED_namedElements, BOXED_elements);\nfinal <%java.util.List%><? extends <%java.lang.Object%>> UNBOXED_includingAll = includingAll.asEcoreObjects(idResolver, <%java.lang.Object%>.class);\nassert UNBOXED_includingAll != null;\nCTORid_namedElements.initValue(symbol_0, UNBOXED_includingAll);\nreturn symbol_0;'"
	 * @generated
	 */
	<NE extends NamedElement> LookupEnvironment addElements(@NonNull Collection<NE> elements);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model required="true"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='/**\n * LookupEnvironment{namedElements = namedElements->including(element)}\n \052/\nfinal /*@NonInvalid\052/ org.eclipse.ocl.pivot.evaluation.@<%org.eclipse.jdt.annotation.NonNull%> Executor executor = <%org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal%>.getExecutor(this);\nfinal /*@NonInvalid\052/ org.eclipse.ocl.pivot.ids.@<%org.eclipse.jdt.annotation.NonNull%> IdResolver idResolver = executor.getIdResolver();\nfinal /*@NonInvalid\052/ org.eclipse.ocl.pivot.@<%org.eclipse.jdt.annotation.NonNull%> Property CTORid_namedElements = idResolver.getProperty(<%org.eclipse.ocl.pivot.internal.lookup.LookupTables%>.PROPid_namedElements);\nfinal /*@NonInvalid\052/ org.eclipse.ocl.pivot.@<%org.eclipse.jdt.annotation.NonNull%> Class TYP_lookup_c_c_LookupEnvironment_0 = idResolver.getClass(<%org.eclipse.ocl.pivot.internal.lookup.LookupTables%>.CLSSid_LookupEnvironment, null);\nfinal /*@Thrown\052/ org.eclipse.ocl.pivot.internal.lookup.@<%org.eclipse.jdt.annotation.NonNull%> LookupEnvironment symbol_0 = (<%org.eclipse.ocl.pivot.internal.lookup.LookupEnvironment%>)TYP_lookup_c_c_LookupEnvironment_0.createInstance();\n@SuppressWarnings(\"null\")\nfinal /*@Thrown\052/ java.util.@<%org.eclipse.jdt.annotation.NonNull%> List<<%org.eclipse.ocl.pivot.NamedElement%>> namedElements = this.getNamedElements();\nfinal /*@Thrown\052/ org.eclipse.ocl.pivot.values.@<%org.eclipse.jdt.annotation.NonNull%> OrderedSetValue BOXED_namedElements = idResolver.createOrderedSetOfAll(<%org.eclipse.ocl.pivot.internal.lookup.LookupTables%>.ORD_CLSSid_NamedElement, namedElements);\nfinal /*@Thrown\052/ org.eclipse.ocl.pivot.values.@<%org.eclipse.jdt.annotation.NonNull%> OrderedSetValue including = (<%org.eclipse.ocl.pivot.values.OrderedSetValue%>)<%org.eclipse.ocl.pivot.library.collection.CollectionIncludingOperation%>.INSTANCE.evaluate(BOXED_namedElements, element);\nfinal <%java.util.List%><<%org.eclipse.ocl.pivot.NamedElement%>> UNBOXED_including = including.asEcoreObjects(idResolver, <%org.eclipse.ocl.pivot.NamedElement%>.class);\nassert UNBOXED_including != null;\nCTORid_namedElements.initValue(symbol_0, UNBOXED_including);\nreturn symbol_0;'"
	 * @generated
	 */
	LookupEnvironment addElement(NamedElement element);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='throw new <%java.lang.UnsupportedOperationException%>(\"Enviroment::hasFinalResult() has been created for CG purposes. Don\'t call this method\");'"
	 * @generated
	 */
	boolean hasFinalResult();

	/**
	 * <!-- begin-user-doc -->
	 * @since 1.1
	 * <!-- end-user-doc -->
	 * @model kind="operation" type="org.eclipse.ocl.pivot.internal.lookup.Executor"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='throw new <%java.lang.UnsupportedOperationException%>(\"Enviroment::getEvaluator() has been created for CG purposes. Don\'t call this method\");'"
	 * @generated
	 */
	Executor getExecutor();

} // LookupEnvironment
