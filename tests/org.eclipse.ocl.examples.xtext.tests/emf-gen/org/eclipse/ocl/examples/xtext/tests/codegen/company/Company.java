/**
 * <copyright>
 * 
 * Copyright (c) 2015, 2018 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 * 
 * </copyright>
 */
package org.eclipse.ocl.examples.xtext.tests.codegen.company;

import java.util.Map;

import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Company</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.examples.xtext.tests.codegen.company.Company#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.xtext.tests.codegen.company.Company#getEmployees <em>Employees</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.xtext.tests.codegen.company.Company#getSize <em>Size</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.examples.xtext.tests.codegen.company.CodegencompanyPackage#getCompany()
 * @model
 * @generated
 */
public interface Company extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.eclipse.ocl.examples.xtext.tests.codegen.company.CodegencompanyPackage#getCompany_Name()
	 * @model required="true"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel get='throw new UnsupportedOperationException();  // FIXME Unimplemented http://www.eclipse.org/ocl/test/Pivot/Company.ecore!Company!name'"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.examples.xtext.tests.codegen.company.Company#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Employees</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.ocl.examples.xtext.tests.codegen.company.Employee}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.ocl.examples.xtext.tests.codegen.company.Employee#getCompany <em>Company</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Employees</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Employees</em>' containment reference list.
	 * @see org.eclipse.ocl.examples.xtext.tests.codegen.company.CodegencompanyPackage#getCompany_Employees()
	 * @see org.eclipse.ocl.examples.xtext.tests.codegen.company.Employee#getCompany
	 * @model opposite="company" containment="true"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel get='throw new UnsupportedOperationException();  // FIXME Unimplemented http://www.eclipse.org/ocl/test/Pivot/Company.ecore!Company!employees'"
	 * @generated
	 */
	EList<Employee> getEmployees();

	/**
	 * Returns the value of the '<em><b>Size</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.ocl.examples.xtext.tests.codegen.company.CompanySizeKind}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Size</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Size</em>' attribute.
	 * @see org.eclipse.ocl.examples.xtext.tests.codegen.company.CompanySizeKind
	 * @see org.eclipse.ocl.examples.xtext.tests.codegen.company.CodegencompanyPackage#getCompany_Size()
	 * @model required="true" transient="true" changeable="false" volatile="true" derived="true"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel get='/**\n *\n * let\n *   table : Set(Tuple(range:Sequence(Integer[*|?]), size:company::CompanySizeKind[1])) = Set{\n *     Tuple{range = Sequence{0..49}, size = CompanySizeKind::small\n *     }\n *     , Tuple{range = Sequence{50..999}, size = CompanySizeKind::medium\n *     }\n *     , Tuple{range = Sequence{1000..1000000}, size = CompanySizeKind::large\n *     }\n *   }\n * in\n *   table-&gt;any(range-&gt;includes(employees-&gt;size()))?.size\n \052/\nfinal /*@NonInvalid\052/ org.eclipse.ocl.pivot.evaluation.@&lt;%org.eclipse.jdt.annotation.NonNull%&gt; Executor executor = &lt;%org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal%&gt;.getExecutor(this);\nfinal /*@NonInvalid\052/ org.eclipse.ocl.pivot.ids.@&lt;%org.eclipse.jdt.annotation.NonNull%&gt; IdResolver idResolver = executor.getIdResolver();\n@&lt;%org.eclipse.jdt.annotation.NonNull%&gt; &lt;%java.util.Iterator%&gt;&lt;&lt;%java.lang.Object%&gt;&gt; ITERATOR__1 = &lt;%org.eclipse.ocl.examples.xtext.tests.codegen.company.CodegencompanyTables%&gt;.table.iterator();\n/*@Thrown\052/ org.eclipse.ocl.pivot.values.@&lt;%org.eclipse.jdt.annotation.Nullable%&gt; TupleValue any;\nwhile (true) {\n\tif (!ITERATOR__1.hasNext()) {\n\t\tthrow new &lt;%org.eclipse.ocl.pivot.values.InvalidValueException%&gt;(\"Nothing to return for \'\'any\'\'\");\n\t}\n\t@SuppressWarnings(\"null\")\n\t/*@NonInvalid\052/ org.eclipse.ocl.pivot.values.@&lt;%org.eclipse.jdt.annotation.NonNull%&gt; TupleValue _1 = (&lt;%org.eclipse.ocl.pivot.values.TupleValue%&gt;)ITERATOR__1.next();\n\t/**\n\t * range-&gt;includes(employees-&gt;size())\n\t \052/\n\t@SuppressWarnings(\"null\")\n\tfinal /*@NonInvalid\052/ org.eclipse.ocl.pivot.values.@&lt;%org.eclipse.jdt.annotation.NonNull%&gt; SequenceValue range = &lt;%org.eclipse.ocl.pivot.utilities.ClassUtil%&gt;.nonNullState((&lt;%org.eclipse.ocl.pivot.values.SequenceValue%&gt;)_1.getValue(0/*range\052/));\n\t@SuppressWarnings(\"null\")\n\tfinal /*@NonInvalid\052/ java.util.@&lt;%org.eclipse.jdt.annotation.NonNull%&gt; List&lt;&lt;%org.eclipse.ocl.examples.xtext.tests.codegen.company.Employee%&gt;&gt; employees = this.getEmployees();\n\tfinal /*@NonInvalid\052/ org.eclipse.ocl.pivot.values.@&lt;%org.eclipse.jdt.annotation.NonNull%&gt; OrderedSetValue BOXED_employees = idResolver.createOrderedSetOfAll(&lt;%org.eclipse.ocl.examples.xtext.tests.codegen.company.CodegencompanyTables%&gt;.ORD_CLSSid_Employee, employees);\n\tfinal /*@NonInvalid\052/ org.eclipse.ocl.pivot.values.@&lt;%org.eclipse.jdt.annotation.NonNull%&gt; IntegerValue size = &lt;%org.eclipse.ocl.pivot.library.collection.CollectionSizeOperation%&gt;.INSTANCE.evaluate(BOXED_employees);\n\tfinal /*@NonInvalid\052/ boolean includes = &lt;%org.eclipse.ocl.pivot.library.collection.CollectionIncludesOperation%&gt;.INSTANCE.evaluate(range, size).booleanValue();\n\t//\n\tif (includes != &lt;%org.eclipse.ocl.pivot.utilities.ValueUtil%&gt;.FALSE_VALUE) {\t\t\t// Carry on till something found\n\t\tany = _1;\n\t\tbreak;\n\t}\n}\n/*@Caught\052/ @&lt;%org.eclipse.jdt.annotation.Nullable%&gt; &lt;%java.lang.Object%&gt; CAUGHT_any;\ntry {\n\tCAUGHT_any = any;\n}\ncatch (&lt;%java.lang.Exception%&gt; e) {\n\tCAUGHT_any = &lt;%org.eclipse.ocl.pivot.utilities.ValueUtil%&gt;.createInvalidValue(e);\n}\nfinal /*@NonInvalid\052/ @&lt;%org.eclipse.jdt.annotation.NonNull%&gt; &lt;%java.lang.Object%&gt; size_0 = CAUGHT_any == null;\n/*@Thrown\052/ org.eclipse.ocl.pivot.ids.@&lt;%org.eclipse.jdt.annotation.Nullable%&gt; EnumerationLiteralId safe_size_source;\nif (size_0 == Boolean.TRUE) {\n\tsafe_size_source = null;\n}\nelse {\n\tassert any != null;\n\t@SuppressWarnings(\"null\")\n\tfinal /*@Thrown\052/ org.eclipse.ocl.pivot.ids.@&lt;%org.eclipse.jdt.annotation.NonNull%&gt; EnumerationLiteralId size_1 = &lt;%org.eclipse.ocl.pivot.utilities.ClassUtil%&gt;.nonNullState((&lt;%org.eclipse.ocl.pivot.ids.EnumerationLiteralId%&gt;)any.getValue(1/*size\052/));\n\tsafe_size_source = size_1;\n}\nif (safe_size_source == null) {\n\tthrow new &lt;%org.eclipse.ocl.pivot.values.InvalidValueException%&gt;(\"Null body for \\\'company::Company::size\\\'\");\n}\nfinal /*@Thrown\052/ org.eclipse.ocl.examples.xtext.tests.codegen.company.@&lt;%org.eclipse.jdt.annotation.NonNull%&gt; CompanySizeKind ECORE_safe_size_source = (&lt;%org.eclipse.ocl.examples.xtext.tests.codegen.company.CompanySizeKind%&gt;)idResolver.ecoreValueOf(&lt;%org.eclipse.emf.common.util.Enumerator%&gt;.class, safe_size_source);\nreturn ECORE_safe_size_source;'"
	 * @generated
	 */
	CompanySizeKind getSize();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='/**\n * inv dummyInvariant: true\n \052/\nreturn &lt;%org.eclipse.ocl.pivot.utilities.ValueUtil%&gt;.TRUE_VALUE;'"
	 * @generated
	 */
	boolean dummyInvariant(DiagnosticChain diagnostics, Map<Object, Object> context);

} // Company
