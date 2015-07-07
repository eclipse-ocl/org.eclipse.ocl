/**
 * <copyright>
 * 
 * Copyright (c) 2015 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 * 
 * </copyright>
 */
package codegen.company;

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
 *   <li>{@link codegen.company.Employee#getName <em>Name</em>}</li>
 *   <li>{@link codegen.company.Employee#getManager <em>Manager</em>}</li>
 *   <li>{@link codegen.company.Employee#getCompany <em>Company</em>}</li>
 *   <li>{@link codegen.company.Employee#getDirectReports <em>Direct Reports</em>}</li>
 *   <li>{@link codegen.company.Employee#getAllReports <em>All Reports</em>}</li>
 *   <li>{@link codegen.company.Employee#getReportingChain <em>Reporting Chain</em>}</li>
 *   <li>{@link codegen.company.Employee#isHasNameAsAttribute <em>Has Name As Attribute</em>}</li>
 * </ul>
 *
 * @see codegen.company.CodegencompanyPackage#getEmployee()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='mustHaveName mustHaveNonEmptyName'"
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
	 * @see codegen.company.CodegencompanyPackage#getEmployee_Name()
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel get='throw new UnsupportedOperationException();  // FIXME Unimplemented http://www.eclipse.org/ocl/test/Pivot/Company.ecore!Employee!name'"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link codegen.company.Employee#getName <em>Name</em>}' attribute.
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
	 * @see codegen.company.CodegencompanyPackage#getEmployee_Manager()
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel get='throw new UnsupportedOperationException();  // FIXME Unimplemented http://www.eclipse.org/ocl/test/Pivot/Company.ecore!Employee!manager'"
	 * @generated
	 */
	Employee getManager();

	/**
	 * Sets the value of the '{@link codegen.company.Employee#getManager <em>Manager</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Manager</em>' reference.
	 * @see #getManager()
	 * @generated
	 */
	void setManager(Employee value);

	/**
	 * Returns the value of the '<em><b>Company</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link codegen.company.Company#getEmployees <em>Employees</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Company</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Company</em>' container reference.
	 * @see #setCompany(Company)
	 * @see codegen.company.CodegencompanyPackage#getEmployee_Company()
	 * @see codegen.company.Company#getEmployees
	 * @model opposite="employees" required="true" transient="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel get='throw new UnsupportedOperationException();  // FIXME Unimplemented http://www.eclipse.org/ocl/test/Pivot/Company.ecore!Employee!company'"
	 * @generated
	 */
	Company getCompany();

	/**
	 * Sets the value of the '{@link codegen.company.Employee#getCompany <em>Company</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Company</em>' container reference.
	 * @see #getCompany()
	 * @generated
	 */
	void setCompany(Company value);

	/**
	 * Returns the value of the '<em><b>Direct Reports</b></em>' reference list.
	 * The list contents are of type {@link codegen.company.Employee}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Direct Reports</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Direct Reports</em>' reference list.
	 * @see codegen.company.CodegencompanyPackage#getEmployee_DirectReports()
	 * @model transient="true" changeable="false" volatile="true" derived="true"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel get='/**\n * company.employees?->select(manager = self)\n \052/\nfinal @<%org.eclipse.jdt.annotation.NonNull%> /*@NonInvalid\052/ <%org.eclipse.ocl.pivot.evaluation.Evaluator%> evaluator = <%org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal%>.getEvaluator(this);\nfinal @<%org.eclipse.jdt.annotation.NonNull%> /*@NonInvalid\052/ <%org.eclipse.ocl.pivot.ids.IdResolver%> idResolver = evaluator.getIdResolver();\n@SuppressWarnings(\"null\")\nfinal @<%org.eclipse.jdt.annotation.NonNull%> /*@Thrown\052/ <%codegen.company.Company%> company = this.getCompany();\n@SuppressWarnings(\"null\")\nfinal @<%org.eclipse.jdt.annotation.NonNull%> /*@Thrown\052/ <%java.util.List%><<%codegen.company.Employee%>> employees = company.getEmployees();\nfinal @<%org.eclipse.jdt.annotation.NonNull%> /*@Thrown\052/ <%org.eclipse.ocl.pivot.values.OrderedSetValue%> BOXED_employees = idResolver.createOrderedSetOfAll(<%codegen.company.CodegencompanyTables%>.ORD_CLSSid_Employee, employees);\nfinal @<%org.eclipse.jdt.annotation.NonNull%> /*@Thrown\052/ <%org.eclipse.ocl.pivot.values.OrderedSetValue%> safe_null_sources = (<%org.eclipse.ocl.pivot.values.OrderedSetValue%>)<%org.eclipse.ocl.pivot.library.collection.CollectionExcludingOperation%>.INSTANCE.evaluate(BOXED_employees, null);\n@<%org.eclipse.jdt.annotation.NonNull%> /*@Thrown\052/ <%org.eclipse.ocl.pivot.values.OrderedSetValue%>.Accumulator accumulator = <%org.eclipse.ocl.pivot.utilities.ValueUtil%>.createOrderedSetAccumulatorValue(<%codegen.company.CodegencompanyTables%>.ORD_CLSSid_Employee);\n@<%org.eclipse.jdt.annotation.NonNull%> <%java.util.Iterator%><?> ITERATOR__1 = safe_null_sources.iterator();\n@<%org.eclipse.jdt.annotation.NonNull%> /*@Thrown\052/ <%org.eclipse.ocl.pivot.values.OrderedSetValue%> select;\nwhile (true) {\n    if (!ITERATOR__1.hasNext()) {\n        select = accumulator;\n        break;\n    }\n    @<%org.eclipse.jdt.annotation.NonNull%> /*@NonInvalid\052/ <%codegen.company.Employee%> _1 = (<%codegen.company.Employee%>)ITERATOR__1.next();\n    /**\n     * manager = self\n     \052/\n    final @<%org.eclipse.jdt.annotation.Nullable%> /*@Thrown\052/ <%codegen.company.Employee%> manager_0 = _1.getManager();\n    final /*@Thrown\052/ boolean eq = this.equals(manager_0);\n    //\n    if (eq == <%org.eclipse.ocl.pivot.utilities.ValueUtil%>.TRUE_VALUE) {\n        accumulator.add(_1);\n    }\n}\nfinal <%java.util.List%><<%codegen.company.Employee%>> UNBOXED_select = select.asEcoreObjects(idResolver, <%codegen.company.Employee%>.class);\nassert UNBOXED_select != null;\nreturn (<%org.eclipse.emf.common.util.EList<codegen.company.Employee>%>)UNBOXED_select;'"
	 * @generated
	 */
	EList<Employee> getDirectReports();

	/**
	 * Returns the value of the '<em><b>All Reports</b></em>' reference list.
	 * The list contents are of type {@link codegen.company.Employee}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>All Reports</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>All Reports</em>' reference list.
	 * @see codegen.company.CodegencompanyPackage#getEmployee_AllReports()
	 * @model transient="true" changeable="false" volatile="true" derived="true" ordered="false"
	 *        annotation="http://www.eclipse.org/OCL/Collection nullFree='true'"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel get='/**\n * Employee.allInstances()->select(reportsTo(self))\n \052/\nfinal @<%org.eclipse.jdt.annotation.NonNull%> /*@NonInvalid\052/ <%org.eclipse.ocl.pivot.evaluation.Evaluator%> evaluator = <%org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal%>.getEvaluator(this);\nfinal @<%org.eclipse.jdt.annotation.NonNull%> /*@NonInvalid\052/ <%org.eclipse.ocl.pivot.ids.IdResolver%> idResolver = evaluator.getIdResolver();\nfinal @<%org.eclipse.jdt.annotation.NonNull%> /*@NonInvalid\052/ <%org.eclipse.ocl.pivot.Class%> TYP_company_c_c_Employee_0 = idResolver.getClass(<%codegen.company.CodegencompanyTables%>.CLSSid_Employee, null);\nfinal @<%org.eclipse.jdt.annotation.NonNull%> /*@NonInvalid\052/ <%org.eclipse.ocl.pivot.values.SetValue%> allInstances = <%org.eclipse.ocl.pivot.library.classifier.ClassifierAllInstancesOperation%>.INSTANCE.evaluate(evaluator, <%codegen.company.CodegencompanyTables%>.SET_CLSSid_Employee, TYP_company_c_c_Employee_0);\n@<%org.eclipse.jdt.annotation.NonNull%> /*@Thrown\052/ <%org.eclipse.ocl.pivot.values.SetValue%>.Accumulator accumulator = <%org.eclipse.ocl.pivot.utilities.ValueUtil%>.createSetAccumulatorValue(<%codegen.company.CodegencompanyTables%>.SET_CLSSid_Employee);\n@<%org.eclipse.jdt.annotation.NonNull%> <%java.util.Iterator%><?> ITERATOR__1 = allInstances.iterator();\n@<%org.eclipse.jdt.annotation.NonNull%> /*@Thrown\052/ <%org.eclipse.ocl.pivot.values.SetValue%> select;\nwhile (true) {\n    if (!ITERATOR__1.hasNext()) {\n        select = accumulator;\n        break;\n    }\n    @<%org.eclipse.jdt.annotation.NonNull%> /*@NonInvalid\052/ <%codegen.company.Employee%> _1 = (<%codegen.company.Employee%>)ITERATOR__1.next();\n    /**\n     * reportsTo(self)\n     \052/\n    final /*@Thrown\052/ boolean reportsTo = _1.reportsTo(this);\n    //\n    if (reportsTo == <%org.eclipse.ocl.pivot.utilities.ValueUtil%>.TRUE_VALUE) {\n        accumulator.add(_1);\n    }\n}\nfinal <%java.util.List%><<%codegen.company.Employee%>> UNBOXED_select = select.asEcoreObjects(idResolver, <%codegen.company.Employee%>.class);\nassert UNBOXED_select != null;\nreturn (<%org.eclipse.emf.common.util.EList<codegen.company.Employee>%>)UNBOXED_select;'"
	 * @generated
	 */
	EList<Employee> getAllReports();

	/**
	 * Returns the value of the '<em><b>Reporting Chain</b></em>' reference list.
	 * The list contents are of type {@link codegen.company.Employee}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Reporting Chain</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Reporting Chain</em>' reference list.
	 * @see codegen.company.CodegencompanyPackage#getEmployee_ReportingChain()
	 * @model transient="true" changeable="false" volatile="true" derived="true"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel get='/**\n * \n * if manager.oclIsUndefined()\n * then OrderedSet{}\n * else manager?.reportingChain->prepend(manager)\n * endif\n \052/\nfinal @<%org.eclipse.jdt.annotation.NonNull%> /*@NonInvalid\052/ <%org.eclipse.ocl.pivot.evaluation.Evaluator%> evaluator = <%org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal%>.getEvaluator(this);\nfinal @<%org.eclipse.jdt.annotation.NonNull%> /*@NonInvalid\052/ <%org.eclipse.ocl.pivot.ids.IdResolver%> idResolver = evaluator.getIdResolver();\n@<%org.eclipse.jdt.annotation.Nullable%> /*@Caught\052/ <%java.lang.Object%> CAUGHT_manager_0;\ntry {\n    final @<%org.eclipse.jdt.annotation.Nullable%> /*@Thrown\052/ <%codegen.company.Employee%> manager_0 = this.getManager();\n    CAUGHT_manager_0 = manager_0;\n}\ncatch (<%java.lang.Exception%> e) {\n    CAUGHT_manager_0 = <%org.eclipse.ocl.pivot.utilities.ValueUtil%>.createInvalidValue(e);\n}\nfinal /*@NonInvalid\052/ boolean symbol_6 = (CAUGHT_manager_0 == null) || (CAUGHT_manager_0 instanceof <%org.eclipse.ocl.pivot.values.InvalidValueException%>);\n@<%org.eclipse.jdt.annotation.NonNull%> /*@Thrown\052/ <%org.eclipse.ocl.pivot.values.OrderedSetValue%> symbol_8;\nif (symbol_6) {\n    symbol_8 = <%codegen.company.CodegencompanyTables%>.OrderedSet;\n}\nelse {\n    final @<%org.eclipse.jdt.annotation.Nullable%> /*@Thrown\052/ <%codegen.company.Employee%> manager_2 = this.getManager();\n    @<%org.eclipse.jdt.annotation.Nullable%> /*@Caught\052/ <%java.lang.Object%> CAUGHT_manager_1;\n    try {\n        CAUGHT_manager_1 = manager_2;\n    }\n    catch (<%java.lang.Exception%> e) {\n        CAUGHT_manager_1 = <%org.eclipse.ocl.pivot.utilities.ValueUtil%>.createInvalidValue(e);\n    }\n    final @<%org.eclipse.jdt.annotation.NonNull%> /*@NonInvalid\052/ <%java.lang.Object%> symbol_7 = CAUGHT_manager_1 == null;\n    @<%org.eclipse.jdt.annotation.Nullable%> /*@Thrown\052/ <%org.eclipse.ocl.pivot.values.OrderedSetValue%> safe_reportingChain_source;\n    if (symbol_7 == Boolean.TRUE) {\n        safe_reportingChain_source = null;\n    }\n    else {\n        assert manager_2 != null;\n        @SuppressWarnings(\"null\")\n        final @<%org.eclipse.jdt.annotation.NonNull%> /*@Thrown\052/ <%java.util.List%><<%codegen.company.Employee%>> reportingChain = manager_2.getReportingChain();\n        final @<%org.eclipse.jdt.annotation.NonNull%> /*@Thrown\052/ <%org.eclipse.ocl.pivot.values.OrderedSetValue%> BOXED_reportingChain = idResolver.createOrderedSetOfAll(<%codegen.company.CodegencompanyTables%>.ORD_CLSSid_Employee, reportingChain);\n        safe_reportingChain_source = BOXED_reportingChain;\n    }\n    final @<%org.eclipse.jdt.annotation.NonNull%> /*@Thrown\052/ <%org.eclipse.ocl.pivot.values.OrderedSetValue%> prepend = (<%org.eclipse.ocl.pivot.values.OrderedSetValue%>)<%org.eclipse.ocl.pivot.library.collection.OrderedCollectionPrependOperation%>.INSTANCE.evaluate(safe_reportingChain_source, manager_2);\n    symbol_8 = prepend;\n}\nfinal <%java.util.List%><<%codegen.company.Employee%>> UNBOXED_symbol_8 = symbol_8.asEcoreObjects(idResolver, <%codegen.company.Employee%>.class);\nassert UNBOXED_symbol_8 != null;\nreturn (<%org.eclipse.emf.common.util.EList<codegen.company.Employee>%>)UNBOXED_symbol_8;'"
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
	 * @see codegen.company.CodegencompanyPackage#getEmployee_HasNameAsAttribute()
	 * @model transient="true" changeable="false" volatile="true" derived="true"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel get='/**\n * name <> null\n \052/\nfinal @<%org.eclipse.jdt.annotation.Nullable%> /*@Thrown\052/ <%java.lang.String%> name = this.getName();\nfinal /*@Thrown\052/ boolean ne = name != null;\nreturn (<%java.lang.Boolean%>)ne;'"
	 * @generated
	 */
	boolean isHasNameAsAttribute();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model required="true"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='/**\n * self.reportingChain->includes(manager)\n \052/\nfinal @<%org.eclipse.jdt.annotation.NonNull%> /*@NonInvalid\052/ <%org.eclipse.ocl.pivot.evaluation.Evaluator%> evaluator = <%org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal%>.getEvaluator(this);\nfinal @<%org.eclipse.jdt.annotation.NonNull%> /*@NonInvalid\052/ <%org.eclipse.ocl.pivot.ids.IdResolver%> idResolver = evaluator.getIdResolver();\n@SuppressWarnings(\"null\")\nfinal @<%org.eclipse.jdt.annotation.NonNull%> /*@Thrown\052/ <%java.util.List%><<%codegen.company.Employee%>> reportingChain = this.getReportingChain();\nfinal @<%org.eclipse.jdt.annotation.NonNull%> /*@Thrown\052/ <%org.eclipse.ocl.pivot.values.OrderedSetValue%> BOXED_reportingChain = idResolver.createOrderedSetOfAll(<%codegen.company.CodegencompanyTables%>.ORD_CLSSid_Employee, reportingChain);\nfinal /*@Thrown\052/ boolean includes = <%org.eclipse.ocl.pivot.library.collection.CollectionIncludesOperation%>.INSTANCE.evaluate(BOXED_reportingChain, manager).booleanValue();\nreturn includes;'"
	 * @generated
	 */
	boolean reportsTo(Employee manager);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model required="true"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='/**\n * name <> null\n \052/\nfinal @<%org.eclipse.jdt.annotation.Nullable%> /*@Thrown\052/ <%java.lang.String%> name = this.getName();\nfinal /*@Thrown\052/ boolean ne = name != null;\nreturn ne;'"
	 * @generated
	 */
	boolean hasNameAsOperation();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='/**\n * \n * inv noManagerImpliesDirectReports:\n *   let\n *     severity : Integer[1] = \'Employee::noManagerImpliesDirectReports\'.getSeverity()\n *   in\n *     if severity <= 0\n *     then true\n *     else\n *       let\n *         status : Boolean[?] = manager.oclIsUndefined() implies\n *         directReports->size() > 0\n *       in\n *         \'Employee::noManagerImpliesDirectReports\'.logDiagnostic(self, diagnostics, context, severity, status, 0)\n *     endif\n \052/\nfinal @<%org.eclipse.jdt.annotation.NonNull%> /*@NonInvalid\052/ <%org.eclipse.ocl.pivot.evaluation.Evaluator%> evaluator = <%org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal%>.getEvaluator(this);\nfinal @<%org.eclipse.jdt.annotation.NonNull%> /*@NonInvalid\052/ <%org.eclipse.ocl.pivot.ids.IdResolver%> idResolver = evaluator.getIdResolver();\nfinal @<%org.eclipse.jdt.annotation.NonNull%> /*@NonInvalid\052/ <%org.eclipse.ocl.pivot.values.IntegerValue%> severity_0 = <%org.eclipse.ocl.pivot.library.string.CGStringGetSeverityOperation%>.INSTANCE.evaluate(evaluator, <%codegen.company.CodegencompanyTables%>.STR_Employee_c_c_noManagerImpliesDirectReports);\nfinal /*@NonInvalid\052/ boolean le = <%org.eclipse.ocl.pivot.library.oclany.OclComparableLessThanEqualOperation%>.INSTANCE.evaluate(evaluator, severity_0, <%codegen.company.CodegencompanyTables%>.INT_0).booleanValue();\n/*@NonInvalid\052/ boolean symbol_1;\nif (le) {\n    symbol_1 = <%org.eclipse.ocl.pivot.utilities.ValueUtil%>.TRUE_VALUE;\n}\nelse {\n    @<%org.eclipse.jdt.annotation.Nullable%> /*@Caught\052/ <%java.lang.Object%> CAUGHT_status;\n    try {\n        @<%org.eclipse.jdt.annotation.Nullable%> /*@Caught\052/ <%java.lang.Object%> CAUGHT_manager;\n        try {\n            final @<%org.eclipse.jdt.annotation.Nullable%> /*@Thrown\052/ <%codegen.company.Employee%> manager = this.getManager();\n            CAUGHT_manager = manager;\n        }\n        catch (<%java.lang.Exception%> e) {\n            CAUGHT_manager = <%org.eclipse.ocl.pivot.utilities.ValueUtil%>.createInvalidValue(e);\n        }\n        final /*@NonInvalid\052/ boolean symbol_0 = (CAUGHT_manager == null) || (CAUGHT_manager instanceof <%org.eclipse.ocl.pivot.values.InvalidValueException%>);\n        @<%org.eclipse.jdt.annotation.NonNull%> /*@Caught\052/ <%java.lang.Object%> CAUGHT_gt;\n        try {\n            @SuppressWarnings(\"null\")\n            final @<%org.eclipse.jdt.annotation.NonNull%> /*@Thrown\052/ <%java.util.List%><<%codegen.company.Employee%>> directReports = this.getDirectReports();\n            final @<%org.eclipse.jdt.annotation.NonNull%> /*@Thrown\052/ <%org.eclipse.ocl.pivot.values.OrderedSetValue%> BOXED_directReports = idResolver.createOrderedSetOfAll(<%codegen.company.CodegencompanyTables%>.ORD_CLSSid_Employee, directReports);\n            final @<%org.eclipse.jdt.annotation.NonNull%> /*@Thrown\052/ <%org.eclipse.ocl.pivot.values.IntegerValue%> size = <%org.eclipse.ocl.pivot.library.collection.CollectionSizeOperation%>.INSTANCE.evaluate(BOXED_directReports);\n            final /*@Thrown\052/ boolean gt = <%org.eclipse.ocl.pivot.library.oclany.OclComparableGreaterThanOperation%>.INSTANCE.evaluate(evaluator, size, <%codegen.company.CodegencompanyTables%>.INT_0).booleanValue();\n            CAUGHT_gt = gt;\n        }\n        catch (<%java.lang.Exception%> e) {\n            CAUGHT_gt = <%org.eclipse.ocl.pivot.utilities.ValueUtil%>.createInvalidValue(e);\n        }\n        final @<%org.eclipse.jdt.annotation.Nullable%> /*@Thrown\052/ <%java.lang.Boolean%> status = <%org.eclipse.ocl.pivot.library.logical.BooleanImpliesOperation%>.INSTANCE.evaluate(symbol_0, CAUGHT_gt);\n        CAUGHT_status = status;\n    }\n    catch (<%java.lang.Exception%> e) {\n        CAUGHT_status = <%org.eclipse.ocl.pivot.utilities.ValueUtil%>.createInvalidValue(e);\n    }\n    final /*@NonInvalid\052/ boolean logDiagnostic = <%org.eclipse.ocl.pivot.library.string.CGStringLogDiagnosticOperation%>.INSTANCE.evaluate(evaluator, <%org.eclipse.ocl.pivot.ids.TypeId%>.BOOLEAN, <%codegen.company.CodegencompanyTables%>.STR_Employee_c_c_noManagerImpliesDirectReports, this, diagnostics, context, severity_0, CAUGHT_status, <%codegen.company.CodegencompanyTables%>.INT_0).booleanValue();\n    symbol_1 = logDiagnostic;\n}\nreturn Boolean.TRUE == symbol_1;'"
	 * @generated
	 */
	boolean noManagerImpliesDirectReports(DiagnosticChain diagnostics, Map<Object, Object> context);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='/**\n * \n * inv mustHaveName:\n *   let severity : Integer[1] = \'Employee::mustHaveName\'.getSeverity()\n *   in\n *     if severity <= 0\n *     then true\n *     else\n *       let\n *         status : Boolean[?] = not name.oclIsUndefined() and hasNameAsAttribute and\n *         hasNameAsOperation()\n *       in\n *         \'Employee::mustHaveName\'.logDiagnostic(self, diagnostics, context, severity, status, 0)\n *     endif\n \052/\nfinal @<%org.eclipse.jdt.annotation.NonNull%> /*@NonInvalid\052/ <%org.eclipse.ocl.pivot.evaluation.Evaluator%> evaluator = <%org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal%>.getEvaluator(this);\nfinal @<%org.eclipse.jdt.annotation.NonNull%> /*@NonInvalid\052/ <%org.eclipse.ocl.pivot.values.IntegerValue%> severity_0 = <%org.eclipse.ocl.pivot.library.string.CGStringGetSeverityOperation%>.INSTANCE.evaluate(evaluator, <%codegen.company.CodegencompanyTables%>.STR_Employee_c_c_mustHaveName);\nfinal /*@NonInvalid\052/ boolean le = <%org.eclipse.ocl.pivot.library.oclany.OclComparableLessThanEqualOperation%>.INSTANCE.evaluate(evaluator, severity_0, <%codegen.company.CodegencompanyTables%>.INT_0).booleanValue();\n/*@NonInvalid\052/ boolean symbol_1;\nif (le) {\n    symbol_1 = <%org.eclipse.ocl.pivot.utilities.ValueUtil%>.TRUE_VALUE;\n}\nelse {\n    @<%org.eclipse.jdt.annotation.Nullable%> /*@Caught\052/ <%java.lang.Object%> CAUGHT_status;\n    try {\n        @<%org.eclipse.jdt.annotation.Nullable%> /*@Caught\052/ <%java.lang.Object%> CAUGHT_and;\n        try {\n            @<%org.eclipse.jdt.annotation.Nullable%> /*@Caught\052/ <%java.lang.Object%> CAUGHT_name;\n            try {\n                final @<%org.eclipse.jdt.annotation.Nullable%> /*@Thrown\052/ <%java.lang.String%> name = this.getName();\n                CAUGHT_name = name;\n            }\n            catch (<%java.lang.Exception%> e) {\n                CAUGHT_name = <%org.eclipse.ocl.pivot.utilities.ValueUtil%>.createInvalidValue(e);\n            }\n            final /*@NonInvalid\052/ boolean symbol_0 = (CAUGHT_name == null) || (CAUGHT_name instanceof <%org.eclipse.ocl.pivot.values.InvalidValueException%>);\n            final @<%org.eclipse.jdt.annotation.Nullable%> /*@NonInvalid\052/ <%java.lang.Boolean%> not = <%org.eclipse.ocl.pivot.library.logical.BooleanNotOperation%>.INSTANCE.evaluate(symbol_0);\n            @<%org.eclipse.jdt.annotation.Nullable%> /*@Caught\052/ <%java.lang.Object%> CAUGHT_hasNameAsAttribute;\n            try {\n                final @<%org.eclipse.jdt.annotation.Nullable%> /*@Thrown\052/ <%java.lang.Boolean%> hasNameAsAttribute = this.isHasNameAsAttribute();\n                CAUGHT_hasNameAsAttribute = hasNameAsAttribute;\n            }\n            catch (<%java.lang.Exception%> e) {\n                CAUGHT_hasNameAsAttribute = <%org.eclipse.ocl.pivot.utilities.ValueUtil%>.createInvalidValue(e);\n            }\n            final @<%org.eclipse.jdt.annotation.Nullable%> /*@Thrown\052/ <%java.lang.Boolean%> and = <%org.eclipse.ocl.pivot.library.logical.BooleanAndOperation%>.INSTANCE.evaluate(not, CAUGHT_hasNameAsAttribute);\n            CAUGHT_and = and;\n        }\n        catch (<%java.lang.Exception%> e) {\n            CAUGHT_and = <%org.eclipse.ocl.pivot.utilities.ValueUtil%>.createInvalidValue(e);\n        }\n        @<%org.eclipse.jdt.annotation.NonNull%> /*@Caught\052/ <%java.lang.Object%> CAUGHT_hasNameAsOperation;\n        try {\n            final /*@Thrown\052/ boolean hasNameAsOperation = this.hasNameAsOperation();\n            CAUGHT_hasNameAsOperation = hasNameAsOperation;\n        }\n        catch (<%java.lang.Exception%> e) {\n            CAUGHT_hasNameAsOperation = <%org.eclipse.ocl.pivot.utilities.ValueUtil%>.createInvalidValue(e);\n        }\n        final @<%org.eclipse.jdt.annotation.Nullable%> /*@Thrown\052/ <%java.lang.Boolean%> status = <%org.eclipse.ocl.pivot.library.logical.BooleanAndOperation%>.INSTANCE.evaluate(CAUGHT_and, CAUGHT_hasNameAsOperation);\n        CAUGHT_status = status;\n    }\n    catch (<%java.lang.Exception%> e) {\n        CAUGHT_status = <%org.eclipse.ocl.pivot.utilities.ValueUtil%>.createInvalidValue(e);\n    }\n    final /*@NonInvalid\052/ boolean logDiagnostic = <%org.eclipse.ocl.pivot.library.string.CGStringLogDiagnosticOperation%>.INSTANCE.evaluate(evaluator, <%org.eclipse.ocl.pivot.ids.TypeId%>.BOOLEAN, <%codegen.company.CodegencompanyTables%>.STR_Employee_c_c_mustHaveName, this, diagnostics, context, severity_0, CAUGHT_status, <%codegen.company.CodegencompanyTables%>.INT_0).booleanValue();\n    symbol_1 = logDiagnostic;\n}\nreturn Boolean.TRUE == symbol_1;'"
	 * @generated
	 */
	boolean mustHaveName(DiagnosticChain diagnostics, Map<Object, Object> context);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='/**\n * \n * inv mustHaveNonEmptyName:\n *   let severity : Integer[1] = \'Employee::mustHaveNonEmptyName\'.getSeverity()\n *   in\n *     if severity <= 0\n *     then true\n *     else\n *       let status : Boolean[?] = name->notEmpty() implies name.size() > 0\n *       in\n *         \'Employee::mustHaveNonEmptyName\'.logDiagnostic(self, diagnostics, context, severity, status, 0)\n *     endif\n \052/\nfinal @<%org.eclipse.jdt.annotation.NonNull%> /*@NonInvalid\052/ <%org.eclipse.ocl.pivot.evaluation.Evaluator%> evaluator = <%org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal%>.getEvaluator(this);\nfinal @<%org.eclipse.jdt.annotation.NonNull%> /*@NonInvalid\052/ <%org.eclipse.ocl.pivot.values.IntegerValue%> severity_0 = <%org.eclipse.ocl.pivot.library.string.CGStringGetSeverityOperation%>.INSTANCE.evaluate(evaluator, <%codegen.company.CodegencompanyTables%>.STR_Employee_c_c_mustHaveNonEmptyName);\nfinal /*@NonInvalid\052/ boolean le = <%org.eclipse.ocl.pivot.library.oclany.OclComparableLessThanEqualOperation%>.INSTANCE.evaluate(evaluator, severity_0, <%codegen.company.CodegencompanyTables%>.INT_0).booleanValue();\n/*@NonInvalid\052/ boolean symbol_0;\nif (le) {\n    symbol_0 = <%org.eclipse.ocl.pivot.utilities.ValueUtil%>.TRUE_VALUE;\n}\nelse {\n    @<%org.eclipse.jdt.annotation.Nullable%> /*@Caught\052/ <%java.lang.Object%> CAUGHT_status;\n    try {\n        @<%org.eclipse.jdt.annotation.NonNull%> /*@Caught\052/ <%java.lang.Object%> CAUGHT_notEmpty;\n        try {\n            final @<%org.eclipse.jdt.annotation.Nullable%> /*@Thrown\052/ <%java.lang.String%> name = this.getName();\n            final @<%org.eclipse.jdt.annotation.NonNull%> /*@Thrown\052/ <%org.eclipse.ocl.pivot.values.SetValue%> oclAsSet = <%org.eclipse.ocl.pivot.library.oclany.OclAnyOclAsSetOperation%>.INSTANCE.evaluate(evaluator, <%codegen.company.CodegencompanyTables%>.SET_PRIMid_String, name);\n            final /*@Thrown\052/ boolean notEmpty = <%org.eclipse.ocl.pivot.library.collection.CollectionNotEmptyOperation%>.INSTANCE.evaluate(oclAsSet).booleanValue();\n            CAUGHT_notEmpty = notEmpty;\n        }\n        catch (<%java.lang.Exception%> e) {\n            CAUGHT_notEmpty = <%org.eclipse.ocl.pivot.utilities.ValueUtil%>.createInvalidValue(e);\n        }\n        @<%org.eclipse.jdt.annotation.NonNull%> /*@Caught\052/ <%java.lang.Object%> CAUGHT_gt;\n        try {\n            final @<%org.eclipse.jdt.annotation.Nullable%> /*@Thrown\052/ <%java.lang.String%> name_0 = this.getName();\n            final @<%org.eclipse.jdt.annotation.NonNull%> /*@Thrown\052/ <%org.eclipse.ocl.pivot.values.IntegerValue%> size = <%org.eclipse.ocl.pivot.library.string.StringSizeOperation%>.INSTANCE.evaluate(name_0);\n            final /*@Thrown\052/ boolean gt = <%org.eclipse.ocl.pivot.library.oclany.OclComparableGreaterThanOperation%>.INSTANCE.evaluate(evaluator, size, <%codegen.company.CodegencompanyTables%>.INT_0).booleanValue();\n            CAUGHT_gt = gt;\n        }\n        catch (<%java.lang.Exception%> e) {\n            CAUGHT_gt = <%org.eclipse.ocl.pivot.utilities.ValueUtil%>.createInvalidValue(e);\n        }\n        final @<%org.eclipse.jdt.annotation.Nullable%> /*@Thrown\052/ <%java.lang.Boolean%> status = <%org.eclipse.ocl.pivot.library.logical.BooleanImpliesOperation%>.INSTANCE.evaluate(CAUGHT_notEmpty, CAUGHT_gt);\n        CAUGHT_status = status;\n    }\n    catch (<%java.lang.Exception%> e) {\n        CAUGHT_status = <%org.eclipse.ocl.pivot.utilities.ValueUtil%>.createInvalidValue(e);\n    }\n    final /*@NonInvalid\052/ boolean logDiagnostic = <%org.eclipse.ocl.pivot.library.string.CGStringLogDiagnosticOperation%>.INSTANCE.evaluate(evaluator, <%org.eclipse.ocl.pivot.ids.TypeId%>.BOOLEAN, <%codegen.company.CodegencompanyTables%>.STR_Employee_c_c_mustHaveNonEmptyName, this, diagnostics, context, severity_0, CAUGHT_status, <%codegen.company.CodegencompanyTables%>.INT_0).booleanValue();\n    symbol_0 = logDiagnostic;\n}\nreturn Boolean.TRUE == symbol_0;'"
	 * @generated
	 */
	boolean mustHaveNonEmptyName(DiagnosticChain diagnostics, Map<Object, Object> context);

} // Employee
