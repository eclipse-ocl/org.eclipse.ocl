/**
 * <copyright>
 * 
 * Copyright (c) 2015, 2016 Willink Transformations and others.
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
 * A representation of the model object '<em><b>Company</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link codegen.company.Company#getName <em>Name</em>}</li>
 *   <li>{@link codegen.company.Company#getEmployees <em>Employees</em>}</li>
 *   <li>{@link codegen.company.Company#getSize <em>Size</em>}</li>
 * </ul>
 *
 * @see codegen.company.CodegencompanyPackage#getCompany()
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
	 * @see codegen.company.CodegencompanyPackage#getCompany_Name()
	 * @model required="true"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel get='throw new UnsupportedOperationException();  // FIXME Unimplemented http://www.eclipse.org/ocl/test/Pivot/Company.ecore!Company!name'"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link codegen.company.Company#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Employees</b></em>' containment reference list.
	 * The list contents are of type {@link codegen.company.Employee}.
	 * It is bidirectional and its opposite is '{@link codegen.company.Employee#getCompany <em>Company</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Employees</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Employees</em>' containment reference list.
	 * @see codegen.company.CodegencompanyPackage#getCompany_Employees()
	 * @see codegen.company.Employee#getCompany
	 * @model opposite="company" containment="true"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel get='throw new UnsupportedOperationException();  // FIXME Unimplemented http://www.eclipse.org/ocl/test/Pivot/Company.ecore!Company!employees'"
	 * @generated
	 */
	EList<Employee> getEmployees();

	/**
	 * Returns the value of the '<em><b>Size</b></em>' attribute.
	 * The literals are from the enumeration {@link codegen.company.CompanySizeKind}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Size</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Size</em>' attribute.
	 * @see codegen.company.CompanySizeKind
	 * @see codegen.company.CodegencompanyPackage#getCompany_Size()
	 * @model required="true" transient="true" changeable="false" volatile="true" derived="true"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel get='/**\n * \n * let\n *   table : Set(Tuple(range:Sequence(Integer), size:company::CompanySizeKind[1])) = Set{\n *     Tuple{range = Sequence{0..49}, size = CompanySizeKind::small\n *     }\n *     , Tuple{range = Sequence{50..999}, size = CompanySizeKind::medium\n *     }\n *     , Tuple{range = Sequence{1000..1000000}, size = CompanySizeKind::large\n *     }\n *   }\n * in\n *   table?->any(range->includes(employees->size()))?.size\n \052/\nfinal /*@NonInvalid\052/ org.eclipse.ocl.pivot.evaluation.@<%org.eclipse.jdt.annotation.NonNull%> Executor executor = <%org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal%>.getExecutor(this);\nfinal /*@NonInvalid\052/ org.eclipse.ocl.pivot.ids.@<%org.eclipse.jdt.annotation.NonNull%> IdResolver idResolver = executor.getIdResolver();\nfinal /*@Thrown\052/ org.eclipse.ocl.pivot.values.@<%org.eclipse.jdt.annotation.NonNull%> SetValue safe_null_sources = (<%org.eclipse.ocl.pivot.values.SetValue%>)<%org.eclipse.ocl.pivot.library.collection.CollectionExcludingOperation%>.INSTANCE.evaluate(<%codegen.company.CodegencompanyTables%>.table, null);\n@<%org.eclipse.jdt.annotation.NonNull%> <%java.util.Iterator%><?> ITERATOR__1 = safe_null_sources.iterator();\n/*@Thrown\052/ org.eclipse.ocl.pivot.values.@<%org.eclipse.jdt.annotation.Nullable%> TupleValue any;\nwhile (true) {\n    if (!ITERATOR__1.hasNext()) {\n        throw new <%org.eclipse.ocl.pivot.values.InvalidValueException%>(\"No matching content for \'any\'\");\n    }\n    @SuppressWarnings(\"null\")\n    /*@NonInvalid\052/ org.eclipse.ocl.pivot.values.@<%org.eclipse.jdt.annotation.NonNull%> TupleValue _1 = (<%org.eclipse.ocl.pivot.values.TupleValue%>)ITERATOR__1.next();\n    /**\n     * range->includes(employees->size())\n     \052/\n    @SuppressWarnings(\"null\")\n    final /*@NonInvalid\052/ org.eclipse.ocl.pivot.values.@<%org.eclipse.jdt.annotation.NonNull%> SequenceValue range = (<%org.eclipse.ocl.pivot.values.SequenceValue%>)_1.getValue(0/*range\052/);\n    @SuppressWarnings(\"null\")\n    final /*@Thrown\052/ java.util.@<%org.eclipse.jdt.annotation.NonNull%> List<<%codegen.company.Employee%>> employees = this.getEmployees();\n    final /*@Thrown\052/ org.eclipse.ocl.pivot.values.@<%org.eclipse.jdt.annotation.NonNull%> OrderedSetValue BOXED_employees = idResolver.createOrderedSetOfAll(<%codegen.company.CodegencompanyTables%>.ORD_CLSSid_Employee, employees);\n    final /*@Thrown\052/ org.eclipse.ocl.pivot.values.@<%org.eclipse.jdt.annotation.NonNull%> IntegerValue size = <%org.eclipse.ocl.pivot.library.collection.CollectionSizeOperation%>.INSTANCE.evaluate(BOXED_employees);\n    final /*@NonInvalid\052/ boolean includes = <%org.eclipse.ocl.pivot.library.collection.CollectionIncludesOperation%>.INSTANCE.evaluate(range, size).booleanValue();\n    //\n    if (includes != <%org.eclipse.ocl.pivot.utilities.ValueUtil%>.FALSE_VALUE) {\t\t\t// Carry on till something found\n        any = _1;\n        break;\n    }\n}\n/*@Caught\052/ @<%org.eclipse.jdt.annotation.Nullable%> <%java.lang.Object%> CAUGHT_any;\ntry {\n    CAUGHT_any = any;\n}\ncatch (<%java.lang.Exception%> e) {\n    CAUGHT_any = <%org.eclipse.ocl.pivot.utilities.ValueUtil%>.createInvalidValue(e);\n}\nfinal /*@NonInvalid\052/ @<%org.eclipse.jdt.annotation.NonNull%> <%java.lang.Object%> symbol_0 = CAUGHT_any == null;\n/*@NonInvalid\052/ org.eclipse.ocl.pivot.ids.@<%org.eclipse.jdt.annotation.Nullable%> EnumerationLiteralId safe_size_source;\nif (symbol_0 == Boolean.TRUE) {\n    safe_size_source = null;\n}\nelse {\n    assert any != null;\n    @SuppressWarnings(\"null\")\n    final /*@NonInvalid\052/ org.eclipse.ocl.pivot.ids.@<%org.eclipse.jdt.annotation.NonNull%> EnumerationLiteralId size_0 = (<%org.eclipse.ocl.pivot.ids.EnumerationLiteralId%>)any.getValue(1/*size\052/);\n    safe_size_source = size_0;\n}\nif (safe_size_source == null) {\n    throw new <%org.eclipse.ocl.pivot.values.InvalidValueException%>(\"Null body for \\\'company::Company::size\\\'\");\n}\nfinal /*@NonInvalid\052/ codegen.company.@<%org.eclipse.jdt.annotation.NonNull%> CompanySizeKind ECORE_safe_size_source = (<%codegen.company.CompanySizeKind%>)idResolver.ecoreValueOf(<%org.eclipse.emf.common.util.Enumerator%>.class, safe_size_source);\nreturn ECORE_safe_size_source;'"
	 * @generated
	 */
	CompanySizeKind getSize();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='/**\n * \n * inv dummyInvariant:\n *   let severity : Integer[1] = \'Company::dummyInvariant\'.getSeverity()\n *   in\n *     if severity <= 0\n *     then true\n *     else\n *       let status : OclAny[1] = true\n *       in\n *         \'Company::dummyInvariant\'.logDiagnostic(self, null, diagnostics, context, null, severity, status, 0)\n *     endif\n \052/\nfinal /*@NonInvalid\052/ org.eclipse.ocl.pivot.evaluation.@<%org.eclipse.jdt.annotation.NonNull%> Executor executor = <%org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal%>.getExecutor(this);\nfinal /*@NonInvalid\052/ org.eclipse.ocl.pivot.values.@<%org.eclipse.jdt.annotation.NonNull%> IntegerValue severity_0 = <%org.eclipse.ocl.pivot.library.string.CGStringGetSeverityOperation%>.INSTANCE.evaluate(executor, <%codegen.company.CodegencompanyTables%>.STR_Company_c_c_dummyInvariant);\nfinal /*@NonInvalid\052/ boolean le = <%org.eclipse.ocl.pivot.library.oclany.OclComparableLessThanEqualOperation%>.INSTANCE.evaluate(executor, severity_0, <%codegen.company.CodegencompanyTables%>.INT_0).booleanValue();\n/*@NonInvalid\052/ boolean symbol_0;\nif (le) {\n    symbol_0 = <%org.eclipse.ocl.pivot.utilities.ValueUtil%>.TRUE_VALUE;\n}\nelse {\n    final /*@NonInvalid\052/ boolean logDiagnostic = <%org.eclipse.ocl.pivot.library.string.CGStringLogDiagnosticOperation%>.INSTANCE.evaluate(executor, <%org.eclipse.ocl.pivot.ids.TypeId%>.BOOLEAN, <%codegen.company.CodegencompanyTables%>.STR_Company_c_c_dummyInvariant, this, null, diagnostics, context, null, severity_0, <%org.eclipse.ocl.pivot.utilities.ValueUtil%>.TRUE_VALUE, <%codegen.company.CodegencompanyTables%>.INT_0).booleanValue();\n    symbol_0 = logDiagnostic;\n}\nreturn Boolean.TRUE == symbol_0;'"
	 * @generated
	 */
	boolean dummyInvariant(DiagnosticChain diagnostics, Map<Object, Object> context);

} // Company
