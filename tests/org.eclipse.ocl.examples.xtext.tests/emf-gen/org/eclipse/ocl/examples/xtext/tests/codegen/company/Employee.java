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
 * A representation of the model object '<em><b>Employee</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.examples.xtext.tests.codegen.company.Employee#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.xtext.tests.codegen.company.Employee#getManager <em>Manager</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.xtext.tests.codegen.company.Employee#getCompany <em>Company</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.xtext.tests.codegen.company.Employee#getDirectReports <em>Direct Reports</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.xtext.tests.codegen.company.Employee#getAllReports <em>All Reports</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.xtext.tests.codegen.company.Employee#getReportingChain <em>Reporting Chain</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.xtext.tests.codegen.company.Employee#isHasNameAsAttribute <em>Has Name As Attribute</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.examples.xtext.tests.codegen.company.CodegencompanyPackage#getEmployee()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='mustHaveNonEmptyName'"
 * @generated
 */
public interface Employee extends EObject {
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
	 * @see org.eclipse.ocl.examples.xtext.tests.codegen.company.CodegencompanyPackage#getEmployee_Name()
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel get='throw new UnsupportedOperationException();  // FIXME Unimplemented http://www.eclipse.org/ocl/test/Pivot/Company.ecore!Employee!name'"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.examples.xtext.tests.codegen.company.Employee#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Manager</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Manager</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Manager</em>' reference.
	 * @see #setManager(Employee)
	 * @see org.eclipse.ocl.examples.xtext.tests.codegen.company.CodegencompanyPackage#getEmployee_Manager()
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel get='throw new UnsupportedOperationException();  // FIXME Unimplemented http://www.eclipse.org/ocl/test/Pivot/Company.ecore!Employee!manager'"
	 * @generated
	 */
	Employee getManager();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.examples.xtext.tests.codegen.company.Employee#getManager <em>Manager</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Manager</em>' reference.
	 * @see #getManager()
	 * @generated
	 */
	void setManager(Employee value);

	/**
	 * Returns the value of the '<em><b>Company</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.ocl.examples.xtext.tests.codegen.company.Company#getEmployees <em>Employees</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Company</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Company</em>' container reference.
	 * @see #setCompany(Company)
	 * @see org.eclipse.ocl.examples.xtext.tests.codegen.company.CodegencompanyPackage#getEmployee_Company()
	 * @see org.eclipse.ocl.examples.xtext.tests.codegen.company.Company#getEmployees
	 * @model opposite="employees" required="true" transient="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel get='throw new UnsupportedOperationException();  // FIXME Unimplemented http://www.eclipse.org/ocl/test/Pivot/Company.ecore!Employee!company'"
	 * @generated
	 */
	Company getCompany();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.examples.xtext.tests.codegen.company.Employee#getCompany <em>Company</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Company</em>' container reference.
	 * @see #getCompany()
	 * @generated
	 */
	void setCompany(Company value);

	/**
	 * Returns the value of the '<em><b>Direct Reports</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.ocl.examples.xtext.tests.codegen.company.Employee}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Direct Reports</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Direct Reports</em>' reference list.
	 * @see org.eclipse.ocl.examples.xtext.tests.codegen.company.CodegencompanyPackage#getEmployee_DirectReports()
	 * @model transient="true" changeable="false" volatile="true" derived="true"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel get='/**\n * company.employees-&gt;select(manager = self)\n \052/\nfinal /*@NonInvalid\052/ org.eclipse.ocl.pivot.evaluation.@&lt;%org.eclipse.jdt.annotation.NonNull%&gt; Executor executor = &lt;%org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal%&gt;.getExecutor(this);\nfinal /*@NonInvalid\052/ org.eclipse.ocl.pivot.ids.@&lt;%org.eclipse.jdt.annotation.NonNull%&gt; IdResolver idResolver = executor.getIdResolver();\n@SuppressWarnings(\"null\")\nfinal /*@NonInvalid\052/ org.eclipse.ocl.examples.xtext.tests.codegen.company.@&lt;%org.eclipse.jdt.annotation.NonNull%&gt; Company company = this.getCompany();\n@SuppressWarnings(\"null\")\nfinal /*@NonInvalid\052/ java.util.@&lt;%org.eclipse.jdt.annotation.NonNull%&gt; List&lt;&lt;%org.eclipse.ocl.examples.xtext.tests.codegen.company.Employee%&gt;&gt; employees = company.getEmployees();\nfinal /*@NonInvalid\052/ org.eclipse.ocl.pivot.values.@&lt;%org.eclipse.jdt.annotation.NonNull%&gt; OrderedSetValue BOXED_employees = idResolver.createOrderedSetOfAll(&lt;%org.eclipse.ocl.examples.xtext.tests.codegen.company.CodegencompanyTables%&gt;.ORD_CLSSid_Employee, employees);\n/*@Thrown\052/ &lt;%org.eclipse.ocl.pivot.values.OrderedSetValue%&gt;.@org.eclipse.jdt.annotation.NonNull Accumulator accumulator = &lt;%org.eclipse.ocl.pivot.utilities.ValueUtil%&gt;.createOrderedSetAccumulatorValue(&lt;%org.eclipse.ocl.examples.xtext.tests.codegen.company.CodegencompanyTables%&gt;.ORD_CLSSid_Employee);\n@&lt;%org.eclipse.jdt.annotation.NonNull%&gt; &lt;%java.util.Iterator%&gt;&lt;&lt;%java.lang.Object%&gt;&gt; ITERATOR__1 = BOXED_employees.iterator();\n/*@NonInvalid\052/ org.eclipse.ocl.pivot.values.@&lt;%org.eclipse.jdt.annotation.NonNull%&gt; OrderedSetValue select;\nwhile (true) {\n\tif (!ITERATOR__1.hasNext()) {\n\t\tselect = accumulator;\n\t\tbreak;\n\t}\n\t@SuppressWarnings(\"null\")\n\t/*@NonInvalid\052/ org.eclipse.ocl.examples.xtext.tests.codegen.company.@&lt;%org.eclipse.jdt.annotation.NonNull%&gt; Employee _1 = (&lt;%org.eclipse.ocl.examples.xtext.tests.codegen.company.Employee%&gt;)ITERATOR__1.next();\n\t/**\n\t * manager = self\n\t \052/\n\tfinal /*@NonInvalid\052/ org.eclipse.ocl.examples.xtext.tests.codegen.company.@&lt;%org.eclipse.jdt.annotation.Nullable%&gt; Employee manager_0 = _1.getManager();\n\tfinal /*@NonInvalid\052/ boolean eq = this.equals(manager_0);\n\t//\n\tif (eq == &lt;%org.eclipse.ocl.pivot.utilities.ValueUtil%&gt;.TRUE_VALUE) {\n\t\taccumulator.add(_1);\n\t}\n}\nfinal /*@NonInvalid\052/ java.util.@&lt;%org.eclipse.jdt.annotation.NonNull%&gt; List&lt;&lt;%org.eclipse.ocl.examples.xtext.tests.codegen.company.Employee%&gt;&gt; ECORE_select = ((&lt;%org.eclipse.ocl.pivot.ids.IdResolver%&gt;.IdResolverExtension)idResolver).ecoreValueOfAll(&lt;%org.eclipse.ocl.examples.xtext.tests.codegen.company.Employee%&gt;.class, select);\nreturn (&lt;%org.eclipse.emf.common.util.EList%&gt;&lt;&lt;%org.eclipse.ocl.examples.xtext.tests.codegen.company.Employee%&gt;&gt;)ECORE_select;'"
	 * @generated
	 */
	EList<Employee> getDirectReports();

	/**
	 * Returns the value of the '<em><b>All Reports</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.ocl.examples.xtext.tests.codegen.company.Employee}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>All Reports</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>All Reports</em>' reference list.
	 * @see org.eclipse.ocl.examples.xtext.tests.codegen.company.CodegencompanyPackage#getEmployee_AllReports()
	 * @model transient="true" changeable="false" volatile="true" derived="true" ordered="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel get='/**\n * Employee.allInstances()-&gt;select(reportsTo(self))\n \052/\nfinal /*@NonInvalid\052/ org.eclipse.ocl.pivot.evaluation.@&lt;%org.eclipse.jdt.annotation.NonNull%&gt; Executor executor = &lt;%org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal%&gt;.getExecutor(this);\nfinal /*@NonInvalid\052/ org.eclipse.ocl.pivot.ids.@&lt;%org.eclipse.jdt.annotation.NonNull%&gt; IdResolver idResolver = executor.getIdResolver();\nfinal /*@NonInvalid\052/ org.eclipse.ocl.pivot.@&lt;%org.eclipse.jdt.annotation.NonNull%&gt; Class TYP_company_c_c_Employee_0 = idResolver.getClass(&lt;%org.eclipse.ocl.examples.xtext.tests.codegen.company.CodegencompanyTables%&gt;.CLSSid_Employee, null);\nfinal /*@NonInvalid\052/ org.eclipse.ocl.pivot.values.@&lt;%org.eclipse.jdt.annotation.NonNull%&gt; SetValue allInstances = &lt;%org.eclipse.ocl.pivot.library.classifier.ClassifierAllInstancesOperation%&gt;.INSTANCE.evaluate(executor, &lt;%org.eclipse.ocl.examples.xtext.tests.codegen.company.CodegencompanyTables%&gt;.SET_CLSSid_Employee, TYP_company_c_c_Employee_0);\n/*@Thrown\052/ &lt;%org.eclipse.ocl.pivot.values.SetValue%&gt;.@org.eclipse.jdt.annotation.NonNull Accumulator accumulator = &lt;%org.eclipse.ocl.pivot.utilities.ValueUtil%&gt;.createSetAccumulatorValue(&lt;%org.eclipse.ocl.examples.xtext.tests.codegen.company.CodegencompanyTables%&gt;.SET_CLSSid_Employee);\n@&lt;%org.eclipse.jdt.annotation.NonNull%&gt; &lt;%java.util.Iterator%&gt;&lt;&lt;%java.lang.Object%&gt;&gt; ITERATOR__1 = allInstances.iterator();\n/*@NonInvalid\052/ org.eclipse.ocl.pivot.values.@&lt;%org.eclipse.jdt.annotation.NonNull%&gt; SetValue select;\nwhile (true) {\n\tif (!ITERATOR__1.hasNext()) {\n\t\tselect = accumulator;\n\t\tbreak;\n\t}\n\t@SuppressWarnings(\"null\")\n\t/*@NonInvalid\052/ org.eclipse.ocl.examples.xtext.tests.codegen.company.@&lt;%org.eclipse.jdt.annotation.NonNull%&gt; Employee _1 = (&lt;%org.eclipse.ocl.examples.xtext.tests.codegen.company.Employee%&gt;)ITERATOR__1.next();\n\t/**\n\t * reportsTo(self)\n\t \052/\n\tfinal /*@NonInvalid\052/ boolean reportsTo = _1.reportsTo(this);\n\t//\n\tif (reportsTo == &lt;%org.eclipse.ocl.pivot.utilities.ValueUtil%&gt;.TRUE_VALUE) {\n\t\taccumulator.add(_1);\n\t}\n}\nfinal /*@NonInvalid\052/ java.util.@&lt;%org.eclipse.jdt.annotation.NonNull%&gt; List&lt;&lt;%org.eclipse.ocl.examples.xtext.tests.codegen.company.Employee%&gt;&gt; ECORE_select = ((&lt;%org.eclipse.ocl.pivot.ids.IdResolver%&gt;.IdResolverExtension)idResolver).ecoreValueOfAll(&lt;%org.eclipse.ocl.examples.xtext.tests.codegen.company.Employee%&gt;.class, select);\nreturn (&lt;%org.eclipse.emf.common.util.EList%&gt;&lt;&lt;%org.eclipse.ocl.examples.xtext.tests.codegen.company.Employee%&gt;&gt;)ECORE_select;'"
	 * @generated
	 */
	EList<Employee> getAllReports();

	/**
	 * Returns the value of the '<em><b>Reporting Chain</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.ocl.examples.xtext.tests.codegen.company.Employee}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Reporting Chain</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Reporting Chain</em>' reference list.
	 * @see org.eclipse.ocl.examples.xtext.tests.codegen.company.CodegencompanyPackage#getEmployee_ReportingChain()
	 * @model transient="true" changeable="false" volatile="true" derived="true"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel get='/**\n *\n * if manager.oclIsUndefined()\n * then OrderedSet{}\n * else manager?.reportingChain-&gt;prepend(manager)\n * endif\n \052/\nfinal /*@NonInvalid\052/ org.eclipse.ocl.pivot.evaluation.@&lt;%org.eclipse.jdt.annotation.NonNull%&gt; Executor executor = &lt;%org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal%&gt;.getExecutor(this);\nfinal /*@NonInvalid\052/ org.eclipse.ocl.pivot.ids.@&lt;%org.eclipse.jdt.annotation.NonNull%&gt; IdResolver idResolver = executor.getIdResolver();\nfinal /*@NonInvalid\052/ org.eclipse.ocl.examples.xtext.tests.codegen.company.@&lt;%org.eclipse.jdt.annotation.Nullable%&gt; Employee manager_2 = this.getManager();\nfinal /*@NonInvalid\052/ boolean oclIsUndefined = manager_2 == null;\n/*@Thrown\052/ org.eclipse.ocl.pivot.values.@&lt;%org.eclipse.jdt.annotation.NonNull%&gt; OrderedSetValue symbol_6;\nif (oclIsUndefined) {\n\tsymbol_6 = &lt;%org.eclipse.ocl.examples.xtext.tests.codegen.company.CodegencompanyTables%&gt;.OrderedSet;\n}\nelse {\n\tfinal /*@NonInvalid\052/ @&lt;%org.eclipse.jdt.annotation.NonNull%&gt; &lt;%java.lang.Object%&gt; reportingChain = manager_2 == null;\n\t/*@Thrown\052/ org.eclipse.ocl.pivot.values.@&lt;%org.eclipse.jdt.annotation.Nullable%&gt; OrderedSetValue safe_reportingChain_source;\n\tif (reportingChain == Boolean.TRUE) {\n\t\tsafe_reportingChain_source = null;\n\t}\n\telse {\n\t\tassert manager_2 != null;\n\t\t@SuppressWarnings(\"null\")\n\t\tfinal /*@Thrown\052/ java.util.@&lt;%org.eclipse.jdt.annotation.NonNull%&gt; List&lt;&lt;%org.eclipse.ocl.examples.xtext.tests.codegen.company.Employee%&gt;&gt; reportingChain_0 = manager_2.getReportingChain();\n\t\tfinal /*@Thrown\052/ org.eclipse.ocl.pivot.values.@&lt;%org.eclipse.jdt.annotation.NonNull%&gt; OrderedSetValue BOXED_reportingChain_0 = idResolver.createOrderedSetOfAll(&lt;%org.eclipse.ocl.examples.xtext.tests.codegen.company.CodegencompanyTables%&gt;.ORD_CLSSid_Employee, reportingChain_0);\n\t\tsafe_reportingChain_source = BOXED_reportingChain_0;\n\t}\n\tfinal /*@Thrown\052/ org.eclipse.ocl.pivot.values.@&lt;%org.eclipse.jdt.annotation.NonNull%&gt; OrderedSetValue prepend = (&lt;%org.eclipse.ocl.pivot.values.OrderedSetValue%&gt;)&lt;%org.eclipse.ocl.pivot.library.collection.OrderedCollectionPrependOperation%&gt;.INSTANCE.evaluate(safe_reportingChain_source, manager_2);\n\tsymbol_6 = prepend;\n}\nfinal /*@Thrown\052/ java.util.@&lt;%org.eclipse.jdt.annotation.NonNull%&gt; List&lt;&lt;%org.eclipse.ocl.examples.xtext.tests.codegen.company.Employee%&gt;&gt; ECORE_symbol_6 = ((&lt;%org.eclipse.ocl.pivot.ids.IdResolver%&gt;.IdResolverExtension)idResolver).ecoreValueOfAll(&lt;%org.eclipse.ocl.examples.xtext.tests.codegen.company.Employee%&gt;.class, symbol_6);\nreturn (&lt;%org.eclipse.emf.common.util.EList%&gt;&lt;&lt;%org.eclipse.ocl.examples.xtext.tests.codegen.company.Employee%&gt;&gt;)ECORE_symbol_6;'"
	 * @generated
	 */
	EList<Employee> getReportingChain();

	/**
	 * Returns the value of the '<em><b>Has Name As Attribute</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Has Name As Attribute</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Has Name As Attribute</em>' attribute.
	 * @see org.eclipse.ocl.examples.xtext.tests.codegen.company.CodegencompanyPackage#getEmployee_HasNameAsAttribute()
	 * @model required="true" transient="true" changeable="false" volatile="true" derived="true"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel get='/**\n * name &lt;&gt; null\n \052/\nfinal /*@NonInvalid\052/ java.lang.@&lt;%org.eclipse.jdt.annotation.Nullable%&gt; String name = this.getName();\nfinal /*@NonInvalid\052/ boolean ne = name != null;\nreturn ne;'"
	 * @generated
	 */
	boolean isHasNameAsAttribute();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model required="true"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='/**\n * self.reportingChain-&gt;includes(manager)\n \052/\nfinal /*@NonInvalid\052/ org.eclipse.ocl.pivot.evaluation.@&lt;%org.eclipse.jdt.annotation.NonNull%&gt; Executor executor = &lt;%org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal%&gt;.getExecutor(this);\nfinal /*@NonInvalid\052/ org.eclipse.ocl.pivot.ids.@&lt;%org.eclipse.jdt.annotation.NonNull%&gt; IdResolver idResolver = executor.getIdResolver();\n@SuppressWarnings(\"null\")\nfinal /*@NonInvalid\052/ java.util.@&lt;%org.eclipse.jdt.annotation.NonNull%&gt; List&lt;&lt;%org.eclipse.ocl.examples.xtext.tests.codegen.company.Employee%&gt;&gt; reportingChain = this.getReportingChain();\nfinal /*@NonInvalid\052/ org.eclipse.ocl.pivot.values.@&lt;%org.eclipse.jdt.annotation.NonNull%&gt; OrderedSetValue BOXED_reportingChain = idResolver.createOrderedSetOfAll(&lt;%org.eclipse.ocl.examples.xtext.tests.codegen.company.CodegencompanyTables%&gt;.ORD_CLSSid_Employee, reportingChain);\nfinal /*@Thrown\052/ boolean includes = &lt;%org.eclipse.ocl.pivot.library.collection.CollectionIncludesOperation%&gt;.INSTANCE.evaluate(BOXED_reportingChain, manager).booleanValue();\nreturn includes;'"
	 * @generated
	 */
	boolean reportsTo(Employee manager);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model required="true"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='/**\n * name &lt;&gt; null\n \052/\nfinal /*@NonInvalid\052/ java.lang.@&lt;%org.eclipse.jdt.annotation.Nullable%&gt; String name = this.getName();\nfinal /*@NonInvalid\052/ boolean ne = name != null;\nreturn ne;'"
	 * @generated
	 */
	boolean hasNameAsOperation();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='/**\n *\n * inv noManagerImpliesDirectReports:\n *   let\n *     severity : Integer[1] = \'Employee::noManagerImpliesDirectReports\'.getSeverity()\n *   in\n *     if severity &lt;= 0\n *     then true\n *     else\n *       let\n *         result : Boolean[?] = manager.oclIsUndefined() implies\n *         directReports-&gt;size() &gt; 0\n *       in\n *         \'Employee::noManagerImpliesDirectReports\'.logDiagnostic(self, null, diagnostics, context, null, severity, result, 0)\n *     endif\n \052/\nfinal /*@NonInvalid\052/ org.eclipse.ocl.pivot.evaluation.@&lt;%org.eclipse.jdt.annotation.NonNull%&gt; Executor executor = &lt;%org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal%&gt;.getExecutor(this);\nfinal /*@NonInvalid\052/ org.eclipse.ocl.pivot.ids.@&lt;%org.eclipse.jdt.annotation.NonNull%&gt; IdResolver idResolver = executor.getIdResolver();\nfinal /*@NonInvalid\052/ org.eclipse.ocl.pivot.values.@&lt;%org.eclipse.jdt.annotation.NonNull%&gt; IntegerValue severity_0 = &lt;%org.eclipse.ocl.pivot.library.string.CGStringGetSeverityOperation%&gt;.INSTANCE.evaluate(executor, &lt;%org.eclipse.ocl.examples.xtext.tests.codegen.company.CodegencompanyTables%&gt;.STR_Employee_c_c_noManagerImpliesDirectReports);\nfinal /*@NonInvalid\052/ boolean le = &lt;%org.eclipse.ocl.pivot.library.oclany.OclComparableLessThanEqualOperation%&gt;.INSTANCE.evaluate(executor, severity_0, &lt;%org.eclipse.ocl.examples.xtext.tests.codegen.company.CodegencompanyTables%&gt;.INT_0).booleanValue();\n/*@NonInvalid\052/ boolean symbol_0;\nif (le) {\n\tsymbol_0 = &lt;%org.eclipse.ocl.pivot.utilities.ValueUtil%&gt;.TRUE_VALUE;\n}\nelse {\n\tfinal /*@NonInvalid\052/ org.eclipse.ocl.examples.xtext.tests.codegen.company.@&lt;%org.eclipse.jdt.annotation.Nullable%&gt; Employee manager = this.getManager();\n\tfinal /*@NonInvalid\052/ boolean oclIsUndefined = manager == null;\n\t/*@NonInvalid\052/ boolean result;\n\tif (oclIsUndefined) {\n\t\t@SuppressWarnings(\"null\")\n\t\tfinal /*@NonInvalid\052/ java.util.@&lt;%org.eclipse.jdt.annotation.NonNull%&gt; List&lt;&lt;%org.eclipse.ocl.examples.xtext.tests.codegen.company.Employee%&gt;&gt; directReports = this.getDirectReports();\n\t\tfinal /*@NonInvalid\052/ org.eclipse.ocl.pivot.values.@&lt;%org.eclipse.jdt.annotation.NonNull%&gt; OrderedSetValue BOXED_directReports = idResolver.createOrderedSetOfAll(&lt;%org.eclipse.ocl.examples.xtext.tests.codegen.company.CodegencompanyTables%&gt;.ORD_CLSSid_Employee, directReports);\n\t\tfinal /*@NonInvalid\052/ org.eclipse.ocl.pivot.values.@&lt;%org.eclipse.jdt.annotation.NonNull%&gt; IntegerValue size = &lt;%org.eclipse.ocl.pivot.library.collection.CollectionSizeOperation%&gt;.INSTANCE.evaluate(BOXED_directReports);\n\t\tfinal /*@NonInvalid\052/ boolean gt = &lt;%org.eclipse.ocl.pivot.library.oclany.OclComparableGreaterThanOperation%&gt;.INSTANCE.evaluate(executor, size, &lt;%org.eclipse.ocl.examples.xtext.tests.codegen.company.CodegencompanyTables%&gt;.INT_0).booleanValue();\n\t\tresult = gt;\n\t}\n\telse {\n\t\tresult = &lt;%org.eclipse.ocl.pivot.utilities.ValueUtil%&gt;.TRUE_VALUE;\n\t}\n\tfinal /*@NonInvalid\052/ boolean logDiagnostic = &lt;%org.eclipse.ocl.pivot.library.string.CGStringLogDiagnosticOperation%&gt;.INSTANCE.evaluate(executor, &lt;%org.eclipse.ocl.pivot.ids.TypeId%&gt;.BOOLEAN, &lt;%org.eclipse.ocl.examples.xtext.tests.codegen.company.CodegencompanyTables%&gt;.STR_Employee_c_c_noManagerImpliesDirectReports, this, (Object)null, diagnostics, context, (Object)null, severity_0, result, &lt;%org.eclipse.ocl.examples.xtext.tests.codegen.company.CodegencompanyTables%&gt;.INT_0).booleanValue();\n\tsymbol_0 = logDiagnostic;\n}\nreturn Boolean.TRUE == symbol_0;'"
	 * @generated
	 */
	boolean noManagerImpliesDirectReports(DiagnosticChain diagnostics, Map<Object, Object> context);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='/**\n *\n * inv mustHaveName:\n *   let severity : Integer[1] = \'Employee::mustHaveName\'.getSeverity()\n *   in\n *     if severity &lt;= 0\n *     then true\n *     else\n *       let\n *         result : OclAny[1] = let\n *           status : Boolean[?] = not name.oclIsUndefined() and hasNameAsAttribute and\n *           hasNameAsOperation()\n *         in\n *           if status = true\n *           then true\n *           else Tuple{message = \'Employee must have a name\', status = status}\n *           endif\n *       in\n *         \'Employee::mustHaveName\'.logDiagnostic(self, null, diagnostics, context, null, severity, result, 0)\n *     endif\n \052/\nfinal /*@NonInvalid\052/ org.eclipse.ocl.pivot.evaluation.@&lt;%org.eclipse.jdt.annotation.NonNull%&gt; Executor executor = &lt;%org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal%&gt;.getExecutor(this);\nfinal /*@NonInvalid\052/ org.eclipse.ocl.pivot.values.@&lt;%org.eclipse.jdt.annotation.NonNull%&gt; IntegerValue severity_0 = &lt;%org.eclipse.ocl.pivot.library.string.CGStringGetSeverityOperation%&gt;.INSTANCE.evaluate(executor, &lt;%org.eclipse.ocl.examples.xtext.tests.codegen.company.CodegencompanyTables%&gt;.STR_Employee_c_c_mustHaveName);\nfinal /*@NonInvalid\052/ boolean le = &lt;%org.eclipse.ocl.pivot.library.oclany.OclComparableLessThanEqualOperation%&gt;.INSTANCE.evaluate(executor, severity_0, &lt;%org.eclipse.ocl.examples.xtext.tests.codegen.company.CodegencompanyTables%&gt;.INT_0).booleanValue();\n/*@NonInvalid\052/ java.lang.@&lt;%org.eclipse.jdt.annotation.NonNull%&gt; Object symbol_2;\nif (le) {\n\tsymbol_2 = &lt;%org.eclipse.ocl.pivot.utilities.ValueUtil%&gt;.TRUE_VALUE;\n}\nelse {\n\t/*@Caught\052/ @&lt;%org.eclipse.jdt.annotation.NonNull%&gt; &lt;%java.lang.Object%&gt; CAUGHT_symbol_1;\n\ttry {\n\t\t/*@Caught\052/ @&lt;%org.eclipse.jdt.annotation.Nullable%&gt; &lt;%java.lang.Object%&gt; CAUGHT_and;\n\t\ttry {\n\t\t\tfinal /*@NonInvalid\052/ java.lang.@&lt;%org.eclipse.jdt.annotation.Nullable%&gt; String name = this.getName();\n\t\t\tfinal /*@NonInvalid\052/ boolean oclIsUndefined = name == null;\n\t\t\tfinal /*@NonInvalid\052/ java.lang.@&lt;%org.eclipse.jdt.annotation.Nullable%&gt; Boolean not = &lt;%org.eclipse.ocl.pivot.library.logical.BooleanNotOperation%&gt;.INSTANCE.evaluate(oclIsUndefined);\n\t\t\tfinal /*@NonInvalid\052/ boolean hasNameAsAttribute = this.isHasNameAsAttribute();\n\t\t\tfinal /*@Thrown\052/ java.lang.@&lt;%org.eclipse.jdt.annotation.Nullable%&gt; Boolean and = &lt;%org.eclipse.ocl.pivot.library.logical.BooleanAndOperation%&gt;.INSTANCE.evaluate(not, hasNameAsAttribute);\n\t\t\tCAUGHT_and = and;\n\t\t}\n\t\tcatch (&lt;%java.lang.Exception%&gt; e) {\n\t\t\tCAUGHT_and = &lt;%org.eclipse.ocl.pivot.utilities.ValueUtil%&gt;.createInvalidValue(e);\n\t\t}\n\t\tfinal /*@NonInvalid\052/ boolean hasNameAsOperation = this.hasNameAsOperation();\n\t\tfinal /*@Thrown\052/ java.lang.@&lt;%org.eclipse.jdt.annotation.Nullable%&gt; Boolean status = &lt;%org.eclipse.ocl.pivot.library.logical.BooleanAndOperation%&gt;.INSTANCE.evaluate(CAUGHT_and, hasNameAsOperation);\n\t\tfinal /*@Thrown\052/ boolean eq = status == Boolean.TRUE;\n\t\t/*@Thrown\052/ java.lang.@&lt;%org.eclipse.jdt.annotation.NonNull%&gt; Object symbol_1;\n\t\tif (eq) {\n\t\t\tsymbol_1 = &lt;%org.eclipse.ocl.pivot.utilities.ValueUtil%&gt;.TRUE_VALUE;\n\t\t}\n\t\telse {\n\t\t\tfinal /*@Thrown\052/ org.eclipse.ocl.pivot.values.@&lt;%org.eclipse.jdt.annotation.NonNull%&gt; TupleValue symbol_0 = &lt;%org.eclipse.ocl.pivot.utilities.ValueUtil%&gt;.createTupleOfEach(&lt;%org.eclipse.ocl.examples.xtext.tests.codegen.company.CodegencompanyTables%&gt;.TUPLid__0, &lt;%org.eclipse.ocl.examples.xtext.tests.codegen.company.CodegencompanyTables%&gt;.STR_Employee_32_must_32_have_32_a_32_name, status);\n\t\t\tsymbol_1 = symbol_0;\n\t\t}\n\t\tCAUGHT_symbol_1 = symbol_1;\n\t}\n\tcatch (&lt;%java.lang.Exception%&gt; e) {\n\t\tCAUGHT_symbol_1 = &lt;%org.eclipse.ocl.pivot.utilities.ValueUtil%&gt;.createInvalidValue(e);\n\t}\n\tfinal /*@NonInvalid\052/ boolean logDiagnostic = &lt;%org.eclipse.ocl.pivot.library.string.CGStringLogDiagnosticOperation%&gt;.INSTANCE.evaluate(executor, &lt;%org.eclipse.ocl.pivot.ids.TypeId%&gt;.BOOLEAN, &lt;%org.eclipse.ocl.examples.xtext.tests.codegen.company.CodegencompanyTables%&gt;.STR_Employee_c_c_mustHaveName, this, (Object)null, diagnostics, context, (Object)null, severity_0, CAUGHT_symbol_1, &lt;%org.eclipse.ocl.examples.xtext.tests.codegen.company.CodegencompanyTables%&gt;.INT_0).booleanValue();\n\tsymbol_2 = logDiagnostic;\n}\nreturn Boolean.TRUE == symbol_2;'"
	 * @generated
	 */
	boolean mustHaveName(DiagnosticChain diagnostics, Map<Object, Object> context);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='/**\n *\n * inv mustHaveNonEmptyName:\n *   let severity : Integer[1] = \'Employee::mustHaveNonEmptyName\'.getSeverity()\n *   in\n *     if severity &lt;= 0\n *     then true\n *     else\n *       let result : Boolean[?] = name-&gt;notEmpty() implies name.size() &gt; 0\n *       in\n *         \'Employee::mustHaveNonEmptyName\'.logDiagnostic(self, null, diagnostics, context, null, severity, result, 0)\n *     endif\n \052/\nfinal /*@NonInvalid\052/ org.eclipse.ocl.pivot.evaluation.@&lt;%org.eclipse.jdt.annotation.NonNull%&gt; Executor executor = &lt;%org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal%&gt;.getExecutor(this);\nfinal /*@NonInvalid\052/ org.eclipse.ocl.pivot.values.@&lt;%org.eclipse.jdt.annotation.NonNull%&gt; IntegerValue severity_0 = &lt;%org.eclipse.ocl.pivot.library.string.CGStringGetSeverityOperation%&gt;.INSTANCE.evaluate(executor, &lt;%org.eclipse.ocl.examples.xtext.tests.codegen.company.CodegencompanyTables%&gt;.STR_Employee_c_c_mustHaveNonEmptyName);\nfinal /*@NonInvalid\052/ boolean le = &lt;%org.eclipse.ocl.pivot.library.oclany.OclComparableLessThanEqualOperation%&gt;.INSTANCE.evaluate(executor, severity_0, &lt;%org.eclipse.ocl.examples.xtext.tests.codegen.company.CodegencompanyTables%&gt;.INT_0).booleanValue();\n/*@NonInvalid\052/ boolean symbol_0;\nif (le) {\n\tsymbol_0 = &lt;%org.eclipse.ocl.pivot.utilities.ValueUtil%&gt;.TRUE_VALUE;\n}\nelse {\n\t/*@Caught\052/ @&lt;%org.eclipse.jdt.annotation.Nullable%&gt; &lt;%java.lang.Object%&gt; CAUGHT_result;\n\ttry {\n\t\t/*@Caught\052/ @&lt;%org.eclipse.jdt.annotation.NonNull%&gt; &lt;%java.lang.Object%&gt; CAUGHT_notEmpty;\n\t\ttry {\n\t\t\tfinal /*@NonInvalid\052/ java.lang.@&lt;%org.eclipse.jdt.annotation.Nullable%&gt; String name = this.getName();\n\t\t\tfinal /*@Thrown\052/ org.eclipse.ocl.pivot.values.@&lt;%org.eclipse.jdt.annotation.NonNull%&gt; SetValue oclAsSet = &lt;%org.eclipse.ocl.pivot.library.oclany.OclAnyOclAsSetOperation%&gt;.INSTANCE.evaluate(executor, &lt;%org.eclipse.ocl.examples.xtext.tests.codegen.company.CodegencompanyTables%&gt;.SET_PRIMid_String, name);\n\t\t\tfinal /*@Thrown\052/ boolean notEmpty = &lt;%org.eclipse.ocl.pivot.library.collection.CollectionNotEmptyOperation%&gt;.INSTANCE.evaluate(oclAsSet).booleanValue();\n\t\t\tCAUGHT_notEmpty = notEmpty;\n\t\t}\n\t\tcatch (&lt;%java.lang.Exception%&gt; e) {\n\t\t\tCAUGHT_notEmpty = &lt;%org.eclipse.ocl.pivot.utilities.ValueUtil%&gt;.createInvalidValue(e);\n\t\t}\n\t\t/*@Caught\052/ @&lt;%org.eclipse.jdt.annotation.NonNull%&gt; &lt;%java.lang.Object%&gt; CAUGHT_gt;\n\t\ttry {\n\t\t\tfinal /*@NonInvalid\052/ java.lang.@&lt;%org.eclipse.jdt.annotation.Nullable%&gt; String name_0 = this.getName();\n\t\t\tfinal /*@Thrown\052/ org.eclipse.ocl.pivot.values.@&lt;%org.eclipse.jdt.annotation.NonNull%&gt; IntegerValue size = &lt;%org.eclipse.ocl.pivot.library.string.StringSizeOperation%&gt;.INSTANCE.evaluate(name_0);\n\t\t\tfinal /*@Thrown\052/ boolean gt = &lt;%org.eclipse.ocl.pivot.library.oclany.OclComparableGreaterThanOperation%&gt;.INSTANCE.evaluate(executor, size, &lt;%org.eclipse.ocl.examples.xtext.tests.codegen.company.CodegencompanyTables%&gt;.INT_0).booleanValue();\n\t\t\tCAUGHT_gt = gt;\n\t\t}\n\t\tcatch (&lt;%java.lang.Exception%&gt; e) {\n\t\t\tCAUGHT_gt = &lt;%org.eclipse.ocl.pivot.utilities.ValueUtil%&gt;.createInvalidValue(e);\n\t\t}\n\t\tfinal /*@Thrown\052/ java.lang.@&lt;%org.eclipse.jdt.annotation.Nullable%&gt; Boolean result = &lt;%org.eclipse.ocl.pivot.library.logical.BooleanImpliesOperation%&gt;.INSTANCE.evaluate(CAUGHT_notEmpty, CAUGHT_gt);\n\t\tCAUGHT_result = result;\n\t}\n\tcatch (&lt;%java.lang.Exception%&gt; e) {\n\t\tCAUGHT_result = &lt;%org.eclipse.ocl.pivot.utilities.ValueUtil%&gt;.createInvalidValue(e);\n\t}\n\tfinal /*@NonInvalid\052/ boolean logDiagnostic = &lt;%org.eclipse.ocl.pivot.library.string.CGStringLogDiagnosticOperation%&gt;.INSTANCE.evaluate(executor, &lt;%org.eclipse.ocl.pivot.ids.TypeId%&gt;.BOOLEAN, &lt;%org.eclipse.ocl.examples.xtext.tests.codegen.company.CodegencompanyTables%&gt;.STR_Employee_c_c_mustHaveNonEmptyName, this, (Object)null, diagnostics, context, (Object)null, severity_0, CAUGHT_result, &lt;%org.eclipse.ocl.examples.xtext.tests.codegen.company.CodegencompanyTables%&gt;.INT_0).booleanValue();\n\tsymbol_0 = logDiagnostic;\n}\nreturn Boolean.TRUE == symbol_0;'"
	 * @generated
	 */
	boolean mustHaveNonEmptyName(DiagnosticChain diagnostics, Map<Object, Object> context);

} // Employee
