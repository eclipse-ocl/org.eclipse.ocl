/*******************************************************************************
 * Copyright (c) 2010, 2015 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *************************************************************************
 * This code is 100% auto-generated
 * from:
 *   /org.eclipse.ocl.xtext.base/model/BaseCS.ecore
 * using:
 *   /org.eclipse.ocl.xtext.base/model/BaseCS.genmodel
 *   org.eclipse.ocl.examples.codegen.oclinecore.OCLinEcoreTables
 *
 * Do not edit it.
 *******************************************************************************/
package org.eclipse.ocl.xtext.basecs;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.PivotTables;
import org.eclipse.ocl.pivot.TemplateParameters;
import org.eclipse.ocl.pivot.internal.library.ecore.EcoreExecutorPackage;
import org.eclipse.ocl.pivot.internal.library.ecore.EcoreExecutorProperty;
import org.eclipse.ocl.pivot.internal.library.ecore.EcoreExecutorType;
import org.eclipse.ocl.pivot.internal.library.ecore.EcoreLibraryOppositeProperty;
import org.eclipse.ocl.pivot.internal.library.executor.ExecutorFragment;
import org.eclipse.ocl.pivot.internal.library.executor.ExecutorOperation;
import org.eclipse.ocl.pivot.internal.library.executor.ExecutorProperty;
import org.eclipse.ocl.pivot.internal.library.executor.ExecutorPropertyWithImplementation;
import org.eclipse.ocl.pivot.internal.library.executor.ExecutorStandardLibrary;
import org.eclipse.ocl.pivot.oclstdlib.OCLstdlibTables;
import org.eclipse.ocl.pivot.utilities.TypeUtil;
import org.eclipse.ocl.xtext.basecs.BaseCSTables;

/**
 * BaseCSTables provides the dispatch tables for the basecs for use by the OCL dispatcher.
 *
 * In order to ensure correct static initialization, a top level class element must be accessed
 * before any nested class element. Therefore an access to PACKAGE.getClass() is recommended.
 */
@SuppressWarnings("nls")
public class BaseCSTables
{
	static {
		Init.initStart();
	}

	/**
	 *	The package descriptor for the package.
	 */
	public static final @NonNull EcoreExecutorPackage PACKAGE = new EcoreExecutorPackage(BaseCSPackage.eINSTANCE);

	/**
	 *	The library of all packages and types.
	 */
	public static final @NonNull ExecutorStandardLibrary LIBRARY = OCLstdlibTables.LIBRARY;

	/**
	 *	Constants used by auto-generated code.
	 */
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull RootPackageId PACKid_$metamodel$ = org.eclipse.ocl.pivot.ids.IdManager.getRootPackageId("$metamodel$");
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull NsURIPackageId PACKid_http_c_s_s_www_eclipse_org_s_emf_s_2002_s_Ecore = org.eclipse.ocl.pivot.ids.IdManager.getNsURIPackageId("http://www.eclipse.org/emf/2002/Ecore", null, org.eclipse.emf.ecore.EcorePackage.eINSTANCE);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull NsURIPackageId PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_2015_s_BaseCS = org.eclipse.ocl.pivot.ids.IdManager.getNsURIPackageId("http://www.eclipse.org/ocl/2015/BaseCS", null, org.eclipse.ocl.xtext.basecs.BaseCSPackage.eINSTANCE);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_AnnotationCS = org.eclipse.ocl.xtext.basecs.BaseCSTables.PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_2015_s_BaseCS.getClassId("AnnotationCS", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_AnnotationElementCS = org.eclipse.ocl.xtext.basecs.BaseCSTables.PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_2015_s_BaseCS.getClassId("AnnotationElementCS", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_Class = org.eclipse.ocl.xtext.basecs.BaseCSTables.PACKid_$metamodel$.getClassId("Class", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_ClassCS = org.eclipse.ocl.xtext.basecs.BaseCSTables.PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_2015_s_BaseCS.getClassId("ClassCS", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_ConstraintCS = org.eclipse.ocl.xtext.basecs.BaseCSTables.PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_2015_s_BaseCS.getClassId("ConstraintCS", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_DetailCS = org.eclipse.ocl.xtext.basecs.BaseCSTables.PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_2015_s_BaseCS.getClassId("DetailCS", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_EClassifier = org.eclipse.ocl.xtext.basecs.BaseCSTables.PACKid_http_c_s_s_www_eclipse_org_s_emf_s_2002_s_Ecore.getClassId("EClassifier", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_Element = org.eclipse.ocl.xtext.basecs.BaseCSTables.PACKid_$metamodel$.getClassId("Element", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_ElementCS = org.eclipse.ocl.xtext.basecs.BaseCSTables.PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_2015_s_BaseCS.getClassId("ElementCS", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_EnumerationCS = org.eclipse.ocl.xtext.basecs.BaseCSTables.PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_2015_s_BaseCS.getClassId("EnumerationCS", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_EnumerationLiteralCS = org.eclipse.ocl.xtext.basecs.BaseCSTables.PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_2015_s_BaseCS.getClassId("EnumerationLiteralCS", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_ImportCS = org.eclipse.ocl.xtext.basecs.BaseCSTables.PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_2015_s_BaseCS.getClassId("ImportCS", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_LambdaTypeCS = org.eclipse.ocl.xtext.basecs.BaseCSTables.PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_2015_s_BaseCS.getClassId("LambdaTypeCS", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_ModelElementCS = org.eclipse.ocl.xtext.basecs.BaseCSTables.PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_2015_s_BaseCS.getClassId("ModelElementCS", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_ModelElementRefCS = org.eclipse.ocl.xtext.basecs.BaseCSTables.PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_2015_s_BaseCS.getClassId("ModelElementRefCS", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_MultiplicityCS = org.eclipse.ocl.xtext.basecs.BaseCSTables.PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_2015_s_BaseCS.getClassId("MultiplicityCS", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_NamedElement = org.eclipse.ocl.xtext.basecs.BaseCSTables.PACKid_$metamodel$.getClassId("NamedElement", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_Namespace = org.eclipse.ocl.xtext.basecs.BaseCSTables.PACKid_$metamodel$.getClassId("Namespace", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_OperationCS = org.eclipse.ocl.xtext.basecs.BaseCSTables.PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_2015_s_BaseCS.getClassId("OperationCS", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_PackageCS = org.eclipse.ocl.xtext.basecs.BaseCSTables.PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_2015_s_BaseCS.getClassId("PackageCS", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_PackageOwnerCS = org.eclipse.ocl.xtext.basecs.BaseCSTables.PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_2015_s_BaseCS.getClassId("PackageOwnerCS", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_ParameterCS = org.eclipse.ocl.xtext.basecs.BaseCSTables.PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_2015_s_BaseCS.getClassId("ParameterCS", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_PathElementCS = org.eclipse.ocl.xtext.basecs.BaseCSTables.PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_2015_s_BaseCS.getClassId("PathElementCS", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_PathNameCS = org.eclipse.ocl.xtext.basecs.BaseCSTables.PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_2015_s_BaseCS.getClassId("PathNameCS", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_Property = org.eclipse.ocl.xtext.basecs.BaseCSTables.PACKid_$metamodel$.getClassId("Property", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_RootCS = org.eclipse.ocl.xtext.basecs.BaseCSTables.PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_2015_s_BaseCS.getClassId("RootCS", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_SpecificationCS = org.eclipse.ocl.xtext.basecs.BaseCSTables.PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_2015_s_BaseCS.getClassId("SpecificationCS", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_StructuralFeatureCS = org.eclipse.ocl.xtext.basecs.BaseCSTables.PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_2015_s_BaseCS.getClassId("StructuralFeatureCS", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_StructuredClassCS = org.eclipse.ocl.xtext.basecs.BaseCSTables.PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_2015_s_BaseCS.getClassId("StructuredClassCS", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_TemplateBindingCS = org.eclipse.ocl.xtext.basecs.BaseCSTables.PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_2015_s_BaseCS.getClassId("TemplateBindingCS", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_TemplateParameterCS = org.eclipse.ocl.xtext.basecs.BaseCSTables.PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_2015_s_BaseCS.getClassId("TemplateParameterCS", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_TemplateParameterSubstitutionCS = org.eclipse.ocl.xtext.basecs.BaseCSTables.PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_2015_s_BaseCS.getClassId("TemplateParameterSubstitutionCS", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_TemplateSignatureCS = org.eclipse.ocl.xtext.basecs.BaseCSTables.PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_2015_s_BaseCS.getClassId("TemplateSignatureCS", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_TemplateableElementCS = org.eclipse.ocl.xtext.basecs.BaseCSTables.PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_2015_s_BaseCS.getClassId("TemplateableElementCS", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_TuplePartCS = org.eclipse.ocl.xtext.basecs.BaseCSTables.PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_2015_s_BaseCS.getClassId("TuplePartCS", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_TupleTypeCS = org.eclipse.ocl.xtext.basecs.BaseCSTables.PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_2015_s_BaseCS.getClassId("TupleTypeCS", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_Type = org.eclipse.ocl.xtext.basecs.BaseCSTables.PACKid_$metamodel$.getClassId("Type", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_TypeParameterCS = org.eclipse.ocl.xtext.basecs.BaseCSTables.PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_2015_s_BaseCS.getClassId("TypeParameterCS", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_TypeRefCS = org.eclipse.ocl.xtext.basecs.BaseCSTables.PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_2015_s_BaseCS.getClassId("TypeRefCS", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_TypedElementCS = org.eclipse.ocl.xtext.basecs.BaseCSTables.PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_2015_s_BaseCS.getClassId("TypedElementCS", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_TypedRefCS = org.eclipse.ocl.xtext.basecs.BaseCSTables.PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_2015_s_BaseCS.getClassId("TypedRefCS", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_TypedTypeRefCS = org.eclipse.ocl.xtext.basecs.BaseCSTables.PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_2015_s_BaseCS.getClassId("TypedTypeRefCS", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_WildcardTypeRefCS = org.eclipse.ocl.xtext.basecs.BaseCSTables.PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_2015_s_BaseCS.getClassId("WildcardTypeRefCS", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull DataTypeId DATAid_CSI = org.eclipse.ocl.xtext.basecs.BaseCSTables.PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_2015_s_BaseCS.getDataTypeId("CSI", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull DataTypeId DATAid_EInt = org.eclipse.ocl.xtext.basecs.BaseCSTables.PACKid_http_c_s_s_www_eclipse_org_s_emf_s_2002_s_Ecore.getDataTypeId("EInt", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull DataTypeId DATAid_EIntegerObject = org.eclipse.ocl.xtext.basecs.BaseCSTables.PACKid_http_c_s_s_www_eclipse_org_s_emf_s_2002_s_Ecore.getDataTypeId("EIntegerObject", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull DataTypeId DATAid_ScopeFilter = org.eclipse.ocl.xtext.basecs.BaseCSTables.PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_2015_s_BaseCS.getDataTypeId("ScopeFilter", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull CollectionTypeId ORD_PRIMid_String = org.eclipse.ocl.pivot.ids.TypeId.ORDERED_SET.getSpecializedId(org.eclipse.ocl.pivot.ids.TypeId.STRING);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull CollectionTypeId BAG_CLSSid_ElementCS = org.eclipse.ocl.pivot.ids.TypeId.BAG.getSpecializedId(org.eclipse.ocl.xtext.basecs.BaseCSTables.CLSSid_ElementCS);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull CollectionTypeId ORD_CLSSid_AnnotationElementCS = org.eclipse.ocl.pivot.ids.TypeId.ORDERED_SET.getSpecializedId(org.eclipse.ocl.xtext.basecs.BaseCSTables.CLSSid_AnnotationElementCS);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull CollectionTypeId ORD_CLSSid_ClassCS = org.eclipse.ocl.pivot.ids.TypeId.ORDERED_SET.getSpecializedId(org.eclipse.ocl.xtext.basecs.BaseCSTables.CLSSid_ClassCS);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull CollectionTypeId ORD_CLSSid_ConstraintCS = org.eclipse.ocl.pivot.ids.TypeId.ORDERED_SET.getSpecializedId(org.eclipse.ocl.xtext.basecs.BaseCSTables.CLSSid_ConstraintCS);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull CollectionTypeId ORD_CLSSid_DetailCS = org.eclipse.ocl.pivot.ids.TypeId.ORDERED_SET.getSpecializedId(org.eclipse.ocl.xtext.basecs.BaseCSTables.CLSSid_DetailCS);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull CollectionTypeId ORD_CLSSid_EnumerationLiteralCS = org.eclipse.ocl.pivot.ids.TypeId.ORDERED_SET.getSpecializedId(org.eclipse.ocl.xtext.basecs.BaseCSTables.CLSSid_EnumerationLiteralCS);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull CollectionTypeId ORD_CLSSid_ImportCS = org.eclipse.ocl.pivot.ids.TypeId.ORDERED_SET.getSpecializedId(org.eclipse.ocl.xtext.basecs.BaseCSTables.CLSSid_ImportCS);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull CollectionTypeId ORD_CLSSid_ModelElementCS = org.eclipse.ocl.pivot.ids.TypeId.ORDERED_SET.getSpecializedId(org.eclipse.ocl.xtext.basecs.BaseCSTables.CLSSid_ModelElementCS);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull CollectionTypeId ORD_CLSSid_ModelElementRefCS = org.eclipse.ocl.pivot.ids.TypeId.ORDERED_SET.getSpecializedId(org.eclipse.ocl.xtext.basecs.BaseCSTables.CLSSid_ModelElementRefCS);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull CollectionTypeId ORD_CLSSid_OperationCS = org.eclipse.ocl.pivot.ids.TypeId.ORDERED_SET.getSpecializedId(org.eclipse.ocl.xtext.basecs.BaseCSTables.CLSSid_OperationCS);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull CollectionTypeId ORD_CLSSid_PackageCS = org.eclipse.ocl.pivot.ids.TypeId.ORDERED_SET.getSpecializedId(org.eclipse.ocl.xtext.basecs.BaseCSTables.CLSSid_PackageCS);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull CollectionTypeId ORD_CLSSid_ParameterCS = org.eclipse.ocl.pivot.ids.TypeId.ORDERED_SET.getSpecializedId(org.eclipse.ocl.xtext.basecs.BaseCSTables.CLSSid_ParameterCS);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull CollectionTypeId ORD_CLSSid_PathElementCS = org.eclipse.ocl.pivot.ids.TypeId.ORDERED_SET.getSpecializedId(org.eclipse.ocl.xtext.basecs.BaseCSTables.CLSSid_PathElementCS);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull CollectionTypeId ORD_CLSSid_Property = org.eclipse.ocl.pivot.ids.TypeId.ORDERED_SET.getSpecializedId(org.eclipse.ocl.xtext.basecs.BaseCSTables.CLSSid_Property);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull CollectionTypeId ORD_CLSSid_SpecificationCS = org.eclipse.ocl.pivot.ids.TypeId.ORDERED_SET.getSpecializedId(org.eclipse.ocl.xtext.basecs.BaseCSTables.CLSSid_SpecificationCS);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull CollectionTypeId ORD_CLSSid_StructuralFeatureCS = org.eclipse.ocl.pivot.ids.TypeId.ORDERED_SET.getSpecializedId(org.eclipse.ocl.xtext.basecs.BaseCSTables.CLSSid_StructuralFeatureCS);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull CollectionTypeId ORD_CLSSid_TemplateParameterCS = org.eclipse.ocl.pivot.ids.TypeId.ORDERED_SET.getSpecializedId(org.eclipse.ocl.xtext.basecs.BaseCSTables.CLSSid_TemplateParameterCS);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull CollectionTypeId ORD_CLSSid_TemplateParameterSubstitutionCS = org.eclipse.ocl.pivot.ids.TypeId.ORDERED_SET.getSpecializedId(org.eclipse.ocl.xtext.basecs.BaseCSTables.CLSSid_TemplateParameterSubstitutionCS);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull CollectionTypeId ORD_CLSSid_TuplePartCS = org.eclipse.ocl.pivot.ids.TypeId.ORDERED_SET.getSpecializedId(org.eclipse.ocl.xtext.basecs.BaseCSTables.CLSSid_TuplePartCS);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull CollectionTypeId ORD_CLSSid_TypedRefCS = org.eclipse.ocl.pivot.ids.TypeId.ORDERED_SET.getSpecializedId(org.eclipse.ocl.xtext.basecs.BaseCSTables.CLSSid_TypedRefCS);

	/**
	 *	The type parameters for templated types and operations.
	 */
	public static class TypeParameters {
		static {
			Init.initStart();
			BaseCSTables.init();
		}

		static {
			Init.initEnd();
		}

		/**
		 * Force initialization of the fields of BaseCSTables::TypeParameters and all preceding sub-packages.
		 */
		public static void init() {}
	}

	/**
	 *	The type descriptors for each type.
	 */
	public static class Types {
		static {
			Init.initStart();
			TypeParameters.init();
		}

		public static final @NonNull EcoreExecutorType _AnnotationCS = new EcoreExecutorType(BaseCSPackage.Literals.ANNOTATION_CS, PACKAGE, 0);
		public static final @NonNull EcoreExecutorType _AnnotationElementCS = new EcoreExecutorType(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS, PACKAGE, 0);
		public static final @NonNull EcoreExecutorType _AttributeCS = new EcoreExecutorType(BaseCSPackage.Literals.ATTRIBUTE_CS, PACKAGE, 0);
		public static final @NonNull EcoreExecutorType _BigNumber = new EcoreExecutorType("BigNumber", PACKAGE, 0);
		public static final @NonNull EcoreExecutorType _CSI = new EcoreExecutorType("CSI", PACKAGE, 0);
		public static final @NonNull EcoreExecutorType _ClassCS = new EcoreExecutorType(BaseCSPackage.Literals.CLASS_CS, PACKAGE, 0);
		public static final @NonNull EcoreExecutorType _ConstraintCS = new EcoreExecutorType(BaseCSPackage.Literals.CONSTRAINT_CS, PACKAGE, 0);
		public static final @NonNull EcoreExecutorType _ContextLessElementCS = new EcoreExecutorType(BaseCSPackage.Literals.CONTEXT_LESS_ELEMENT_CS, PACKAGE, 0);
		public static final @NonNull EcoreExecutorType _DataTypeCS = new EcoreExecutorType(BaseCSPackage.Literals.DATA_TYPE_CS, PACKAGE, 0);
		public static final @NonNull EcoreExecutorType _DetailCS = new EcoreExecutorType(BaseCSPackage.Literals.DETAIL_CS, PACKAGE, 0);
		public static final @NonNull EcoreExecutorType _DocumentationCS = new EcoreExecutorType(BaseCSPackage.Literals.DOCUMENTATION_CS, PACKAGE, 0);
		public static final @NonNull EcoreExecutorType _ElementCS = new EcoreExecutorType(BaseCSPackage.Literals.ELEMENT_CS, PACKAGE, 0);
		public static final @NonNull EcoreExecutorType _ElementRefCS = new EcoreExecutorType(BaseCSPackage.Literals.ELEMENT_REF_CS, PACKAGE, 0);
		public static final @NonNull EcoreExecutorType _EnumerationCS = new EcoreExecutorType(BaseCSPackage.Literals.ENUMERATION_CS, PACKAGE, 0);
		public static final @NonNull EcoreExecutorType _EnumerationLiteralCS = new EcoreExecutorType(BaseCSPackage.Literals.ENUMERATION_LITERAL_CS, PACKAGE, 0);
		public static final @NonNull EcoreExecutorType _FeatureCS = new EcoreExecutorType(BaseCSPackage.Literals.FEATURE_CS, PACKAGE, 0);
		public static final @NonNull EcoreExecutorType _ImportCS = new EcoreExecutorType(BaseCSPackage.Literals.IMPORT_CS, PACKAGE, 0);
		public static final @NonNull EcoreExecutorType _LambdaTypeCS = new EcoreExecutorType(BaseCSPackage.Literals.LAMBDA_TYPE_CS, PACKAGE, 0);
		public static final @NonNull EcoreExecutorType _ModelElementCS = new EcoreExecutorType(BaseCSPackage.Literals.MODEL_ELEMENT_CS, PACKAGE, 0);
		public static final @NonNull EcoreExecutorType _ModelElementRefCS = new EcoreExecutorType(BaseCSPackage.Literals.MODEL_ELEMENT_REF_CS, PACKAGE, 0);
		public static final @NonNull EcoreExecutorType _MultiplicityBoundsCS = new EcoreExecutorType(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS, PACKAGE, 0);
		public static final @NonNull EcoreExecutorType _MultiplicityCS = new EcoreExecutorType(BaseCSPackage.Literals.MULTIPLICITY_CS, PACKAGE, 0);
		public static final @NonNull EcoreExecutorType _MultiplicityStringCS = new EcoreExecutorType(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS, PACKAGE, 0);
		public static final @NonNull EcoreExecutorType _NamedElementCS = new EcoreExecutorType(BaseCSPackage.Literals.NAMED_ELEMENT_CS, PACKAGE, 0);
		public static final @NonNull EcoreExecutorType _NamespaceCS = new EcoreExecutorType(BaseCSPackage.Literals.NAMESPACE_CS, PACKAGE, 0);
		public static final @NonNull EcoreExecutorType _OperationCS = new EcoreExecutorType(BaseCSPackage.Literals.OPERATION_CS, PACKAGE, 0);
		public static final @NonNull EcoreExecutorType _PackageCS = new EcoreExecutorType(BaseCSPackage.Literals.PACKAGE_CS, PACKAGE, 0);
		public static final @NonNull EcoreExecutorType _PackageOwnerCS = new EcoreExecutorType(BaseCSPackage.Literals.PACKAGE_OWNER_CS, PACKAGE, 0);
		public static final @NonNull EcoreExecutorType _ParameterCS = new EcoreExecutorType(BaseCSPackage.Literals.PARAMETER_CS, PACKAGE, 0);
		public static final @NonNull EcoreExecutorType _PathElementCS = new EcoreExecutorType(BaseCSPackage.Literals.PATH_ELEMENT_CS, PACKAGE, 0);
		public static final @NonNull EcoreExecutorType _PathElementWithURICS = new EcoreExecutorType(BaseCSPackage.Literals.PATH_ELEMENT_WITH_URICS, PACKAGE, 0);
		public static final @NonNull EcoreExecutorType _PathNameCS = new EcoreExecutorType(BaseCSPackage.Literals.PATH_NAME_CS, PACKAGE, 0);
		public static final @NonNull EcoreExecutorType _PivotableElementCS = new EcoreExecutorType(BaseCSPackage.Literals.PIVOTABLE_ELEMENT_CS, PACKAGE, 0);
		public static final @NonNull EcoreExecutorType _PrimitiveTypeRefCS = new EcoreExecutorType(BaseCSPackage.Literals.PRIMITIVE_TYPE_REF_CS, PACKAGE, 0);
		public static final @NonNull EcoreExecutorType _ReferenceCS = new EcoreExecutorType(BaseCSPackage.Literals.REFERENCE_CS, PACKAGE, 0);
		public static final @NonNull EcoreExecutorType _RootCS = new EcoreExecutorType(BaseCSPackage.Literals.ROOT_CS, PACKAGE, 0);
		public static final @NonNull EcoreExecutorType _RootPackageCS = new EcoreExecutorType(BaseCSPackage.Literals.ROOT_PACKAGE_CS, PACKAGE, 0);
		public static final @NonNull EcoreExecutorType _ScopeFilter = new EcoreExecutorType("ScopeFilter", PACKAGE, 0);
		public static final @NonNull EcoreExecutorType _SpecificationCS = new EcoreExecutorType(BaseCSPackage.Literals.SPECIFICATION_CS, PACKAGE, 0);
		public static final @NonNull EcoreExecutorType _StructuralFeatureCS = new EcoreExecutorType(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS, PACKAGE, 0);
		public static final @NonNull EcoreExecutorType _StructuredClassCS = new EcoreExecutorType(BaseCSPackage.Literals.STRUCTURED_CLASS_CS, PACKAGE, 0);
		public static final @NonNull EcoreExecutorType _TemplateBindingCS = new EcoreExecutorType(BaseCSPackage.Literals.TEMPLATE_BINDING_CS, PACKAGE, 0);
		public static final @NonNull EcoreExecutorType _TemplateParameterCS = new EcoreExecutorType(BaseCSPackage.Literals.TEMPLATE_PARAMETER_CS, PACKAGE, 0);
		public static final @NonNull EcoreExecutorType _TemplateParameterSubstitutionCS = new EcoreExecutorType(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS, PACKAGE, 0);
		public static final @NonNull EcoreExecutorType _TemplateSignatureCS = new EcoreExecutorType(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS, PACKAGE, 0);
		public static final @NonNull EcoreExecutorType _TemplateableElementCS = new EcoreExecutorType(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS, PACKAGE, 0);
		public static final @NonNull EcoreExecutorType _TuplePartCS = new EcoreExecutorType(BaseCSPackage.Literals.TUPLE_PART_CS, PACKAGE, 0);
		public static final @NonNull EcoreExecutorType _TupleTypeCS = new EcoreExecutorType(BaseCSPackage.Literals.TUPLE_TYPE_CS, PACKAGE, 0);
		public static final @NonNull EcoreExecutorType _TypeCS = new EcoreExecutorType(BaseCSPackage.Literals.TYPE_CS, PACKAGE, 0);
		public static final @NonNull EcoreExecutorType _TypeParameterCS = new EcoreExecutorType(BaseCSPackage.Literals.TYPE_PARAMETER_CS, PACKAGE, 0);
		public static final @NonNull EcoreExecutorType _TypeRefCS = new EcoreExecutorType(BaseCSPackage.Literals.TYPE_REF_CS, PACKAGE, 0);
		public static final @NonNull EcoreExecutorType _TypedElementCS = new EcoreExecutorType(BaseCSPackage.Literals.TYPED_ELEMENT_CS, PACKAGE, 0);
		public static final @NonNull EcoreExecutorType _TypedRefCS = new EcoreExecutorType(BaseCSPackage.Literals.TYPED_REF_CS, PACKAGE, 0);
		public static final @NonNull EcoreExecutorType _TypedTypeRefCS = new EcoreExecutorType(BaseCSPackage.Literals.TYPED_TYPE_REF_CS, PACKAGE, 0);
		public static final @NonNull EcoreExecutorType _VisitableCS = new EcoreExecutorType(BaseCSPackage.Literals.VISITABLE_CS, PACKAGE, 0);
		public static final @NonNull EcoreExecutorType _WildcardTypeRefCS = new EcoreExecutorType(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS, PACKAGE, 0);

		private static final @NonNull EcoreExecutorType @NonNull [] types = {
			_AnnotationCS,
			_AnnotationElementCS,
			_AttributeCS,
			_BigNumber,
			_CSI,
			_ClassCS,
			_ConstraintCS,
			_ContextLessElementCS,
			_DataTypeCS,
			_DetailCS,
			_DocumentationCS,
			_ElementCS,
			_ElementRefCS,
			_EnumerationCS,
			_EnumerationLiteralCS,
			_FeatureCS,
			_ImportCS,
			_LambdaTypeCS,
			_ModelElementCS,
			_ModelElementRefCS,
			_MultiplicityBoundsCS,
			_MultiplicityCS,
			_MultiplicityStringCS,
			_NamedElementCS,
			_NamespaceCS,
			_OperationCS,
			_PackageCS,
			_PackageOwnerCS,
			_ParameterCS,
			_PathElementCS,
			_PathElementWithURICS,
			_PathNameCS,
			_PivotableElementCS,
			_PrimitiveTypeRefCS,
			_ReferenceCS,
			_RootCS,
			_RootPackageCS,
			_ScopeFilter,
			_SpecificationCS,
			_StructuralFeatureCS,
			_StructuredClassCS,
			_TemplateBindingCS,
			_TemplateParameterCS,
			_TemplateParameterSubstitutionCS,
			_TemplateSignatureCS,
			_TemplateableElementCS,
			_TuplePartCS,
			_TupleTypeCS,
			_TypeCS,
			_TypeParameterCS,
			_TypeRefCS,
			_TypedElementCS,
			_TypedRefCS,
			_TypedTypeRefCS,
			_VisitableCS,
			_WildcardTypeRefCS
		};

		/*
		 *	Install the type descriptors in the package descriptor.
		 */
		static {
			PACKAGE.init(LIBRARY, types);
			Init.initEnd();
		}

		/**
		 * Force initialization of the fields of BaseCSTables::Types and all preceding sub-packages.
		 */
		public static void init() {}
	}

	/**
	 *	The fragment descriptors for the local elements of each type and its supertypes.
	 */
	public static class Fragments {
		static {
			Init.initStart();
			Types.init();
		}

		private static final @NonNull ExecutorFragment _AnnotationCS__AnnotationCS = new ExecutorFragment(Types._AnnotationCS, BaseCSTables.Types._AnnotationCS);
		private static final @NonNull ExecutorFragment _AnnotationCS__AnnotationElementCS = new ExecutorFragment(Types._AnnotationCS, BaseCSTables.Types._AnnotationElementCS);
		private static final @NonNull ExecutorFragment _AnnotationCS__ElementCS = new ExecutorFragment(Types._AnnotationCS, BaseCSTables.Types._ElementCS);
		private static final @NonNull ExecutorFragment _AnnotationCS__ModelElementCS = new ExecutorFragment(Types._AnnotationCS, BaseCSTables.Types._ModelElementCS);
		private static final @NonNull ExecutorFragment _AnnotationCS__Nameable = new ExecutorFragment(Types._AnnotationCS, PivotTables.Types._Nameable);
		private static final @NonNull ExecutorFragment _AnnotationCS__NamedElementCS = new ExecutorFragment(Types._AnnotationCS, BaseCSTables.Types._NamedElementCS);
		private static final @NonNull ExecutorFragment _AnnotationCS__OclAny = new ExecutorFragment(Types._AnnotationCS, OCLstdlibTables.Types._OclAny);
		private static final @NonNull ExecutorFragment _AnnotationCS__OclElement = new ExecutorFragment(Types._AnnotationCS, OCLstdlibTables.Types._OclElement);
		private static final @NonNull ExecutorFragment _AnnotationCS__Pivotable = new ExecutorFragment(Types._AnnotationCS, PivotTables.Types._Pivotable);
		private static final @NonNull ExecutorFragment _AnnotationCS__PivotableElementCS = new ExecutorFragment(Types._AnnotationCS, BaseCSTables.Types._PivotableElementCS);
		private static final @NonNull ExecutorFragment _AnnotationCS__VisitableCS = new ExecutorFragment(Types._AnnotationCS, BaseCSTables.Types._VisitableCS);

		private static final @NonNull ExecutorFragment _AnnotationElementCS__AnnotationElementCS = new ExecutorFragment(Types._AnnotationElementCS, BaseCSTables.Types._AnnotationElementCS);
		private static final @NonNull ExecutorFragment _AnnotationElementCS__ElementCS = new ExecutorFragment(Types._AnnotationElementCS, BaseCSTables.Types._ElementCS);
		private static final @NonNull ExecutorFragment _AnnotationElementCS__ModelElementCS = new ExecutorFragment(Types._AnnotationElementCS, BaseCSTables.Types._ModelElementCS);
		private static final @NonNull ExecutorFragment _AnnotationElementCS__Nameable = new ExecutorFragment(Types._AnnotationElementCS, PivotTables.Types._Nameable);
		private static final @NonNull ExecutorFragment _AnnotationElementCS__NamedElementCS = new ExecutorFragment(Types._AnnotationElementCS, BaseCSTables.Types._NamedElementCS);
		private static final @NonNull ExecutorFragment _AnnotationElementCS__OclAny = new ExecutorFragment(Types._AnnotationElementCS, OCLstdlibTables.Types._OclAny);
		private static final @NonNull ExecutorFragment _AnnotationElementCS__OclElement = new ExecutorFragment(Types._AnnotationElementCS, OCLstdlibTables.Types._OclElement);
		private static final @NonNull ExecutorFragment _AnnotationElementCS__Pivotable = new ExecutorFragment(Types._AnnotationElementCS, PivotTables.Types._Pivotable);
		private static final @NonNull ExecutorFragment _AnnotationElementCS__PivotableElementCS = new ExecutorFragment(Types._AnnotationElementCS, BaseCSTables.Types._PivotableElementCS);
		private static final @NonNull ExecutorFragment _AnnotationElementCS__VisitableCS = new ExecutorFragment(Types._AnnotationElementCS, BaseCSTables.Types._VisitableCS);

		private static final @NonNull ExecutorFragment _AttributeCS__AttributeCS = new ExecutorFragment(Types._AttributeCS, BaseCSTables.Types._AttributeCS);
		private static final @NonNull ExecutorFragment _AttributeCS__ElementCS = new ExecutorFragment(Types._AttributeCS, BaseCSTables.Types._ElementCS);
		private static final @NonNull ExecutorFragment _AttributeCS__FeatureCS = new ExecutorFragment(Types._AttributeCS, BaseCSTables.Types._FeatureCS);
		private static final @NonNull ExecutorFragment _AttributeCS__ModelElementCS = new ExecutorFragment(Types._AttributeCS, BaseCSTables.Types._ModelElementCS);
		private static final @NonNull ExecutorFragment _AttributeCS__Nameable = new ExecutorFragment(Types._AttributeCS, PivotTables.Types._Nameable);
		private static final @NonNull ExecutorFragment _AttributeCS__NamedElementCS = new ExecutorFragment(Types._AttributeCS, BaseCSTables.Types._NamedElementCS);
		private static final @NonNull ExecutorFragment _AttributeCS__OclAny = new ExecutorFragment(Types._AttributeCS, OCLstdlibTables.Types._OclAny);
		private static final @NonNull ExecutorFragment _AttributeCS__OclElement = new ExecutorFragment(Types._AttributeCS, OCLstdlibTables.Types._OclElement);
		private static final @NonNull ExecutorFragment _AttributeCS__Pivotable = new ExecutorFragment(Types._AttributeCS, PivotTables.Types._Pivotable);
		private static final @NonNull ExecutorFragment _AttributeCS__PivotableElementCS = new ExecutorFragment(Types._AttributeCS, BaseCSTables.Types._PivotableElementCS);
		private static final @NonNull ExecutorFragment _AttributeCS__StructuralFeatureCS = new ExecutorFragment(Types._AttributeCS, BaseCSTables.Types._StructuralFeatureCS);
		private static final @NonNull ExecutorFragment _AttributeCS__TypedElementCS = new ExecutorFragment(Types._AttributeCS, BaseCSTables.Types._TypedElementCS);
		private static final @NonNull ExecutorFragment _AttributeCS__VisitableCS = new ExecutorFragment(Types._AttributeCS, BaseCSTables.Types._VisitableCS);

		private static final @NonNull ExecutorFragment _BigNumber__BigNumber = new ExecutorFragment(Types._BigNumber, BaseCSTables.Types._BigNumber);
		private static final @NonNull ExecutorFragment _BigNumber__OclAny = new ExecutorFragment(Types._BigNumber, OCLstdlibTables.Types._OclAny);

		private static final @NonNull ExecutorFragment _CSI__CSI = new ExecutorFragment(Types._CSI, BaseCSTables.Types._CSI);
		private static final @NonNull ExecutorFragment _CSI__OclAny = new ExecutorFragment(Types._CSI, OCLstdlibTables.Types._OclAny);

		private static final @NonNull ExecutorFragment _ClassCS__ClassCS = new ExecutorFragment(Types._ClassCS, BaseCSTables.Types._ClassCS);
		private static final @NonNull ExecutorFragment _ClassCS__ElementCS = new ExecutorFragment(Types._ClassCS, BaseCSTables.Types._ElementCS);
		private static final @NonNull ExecutorFragment _ClassCS__ModelElementCS = new ExecutorFragment(Types._ClassCS, BaseCSTables.Types._ModelElementCS);
		private static final @NonNull ExecutorFragment _ClassCS__Nameable = new ExecutorFragment(Types._ClassCS, PivotTables.Types._Nameable);
		private static final @NonNull ExecutorFragment _ClassCS__NamedElementCS = new ExecutorFragment(Types._ClassCS, BaseCSTables.Types._NamedElementCS);
		private static final @NonNull ExecutorFragment _ClassCS__OclAny = new ExecutorFragment(Types._ClassCS, OCLstdlibTables.Types._OclAny);
		private static final @NonNull ExecutorFragment _ClassCS__OclElement = new ExecutorFragment(Types._ClassCS, OCLstdlibTables.Types._OclElement);
		private static final @NonNull ExecutorFragment _ClassCS__Pivotable = new ExecutorFragment(Types._ClassCS, PivotTables.Types._Pivotable);
		private static final @NonNull ExecutorFragment _ClassCS__PivotableElementCS = new ExecutorFragment(Types._ClassCS, BaseCSTables.Types._PivotableElementCS);
		private static final @NonNull ExecutorFragment _ClassCS__TemplateableElementCS = new ExecutorFragment(Types._ClassCS, BaseCSTables.Types._TemplateableElementCS);
		private static final @NonNull ExecutorFragment _ClassCS__TypeCS = new ExecutorFragment(Types._ClassCS, BaseCSTables.Types._TypeCS);
		private static final @NonNull ExecutorFragment _ClassCS__VisitableCS = new ExecutorFragment(Types._ClassCS, BaseCSTables.Types._VisitableCS);

		private static final @NonNull ExecutorFragment _ConstraintCS__ConstraintCS = new ExecutorFragment(Types._ConstraintCS, BaseCSTables.Types._ConstraintCS);
		private static final @NonNull ExecutorFragment _ConstraintCS__ElementCS = new ExecutorFragment(Types._ConstraintCS, BaseCSTables.Types._ElementCS);
		private static final @NonNull ExecutorFragment _ConstraintCS__ModelElementCS = new ExecutorFragment(Types._ConstraintCS, BaseCSTables.Types._ModelElementCS);
		private static final @NonNull ExecutorFragment _ConstraintCS__Nameable = new ExecutorFragment(Types._ConstraintCS, PivotTables.Types._Nameable);
		private static final @NonNull ExecutorFragment _ConstraintCS__NamedElementCS = new ExecutorFragment(Types._ConstraintCS, BaseCSTables.Types._NamedElementCS);
		private static final @NonNull ExecutorFragment _ConstraintCS__OclAny = new ExecutorFragment(Types._ConstraintCS, OCLstdlibTables.Types._OclAny);
		private static final @NonNull ExecutorFragment _ConstraintCS__OclElement = new ExecutorFragment(Types._ConstraintCS, OCLstdlibTables.Types._OclElement);
		private static final @NonNull ExecutorFragment _ConstraintCS__Pivotable = new ExecutorFragment(Types._ConstraintCS, PivotTables.Types._Pivotable);
		private static final @NonNull ExecutorFragment _ConstraintCS__PivotableElementCS = new ExecutorFragment(Types._ConstraintCS, BaseCSTables.Types._PivotableElementCS);
		private static final @NonNull ExecutorFragment _ConstraintCS__VisitableCS = new ExecutorFragment(Types._ConstraintCS, BaseCSTables.Types._VisitableCS);

		private static final @NonNull ExecutorFragment _ContextLessElementCS__ContextLessElementCS = new ExecutorFragment(Types._ContextLessElementCS, BaseCSTables.Types._ContextLessElementCS);
		private static final @NonNull ExecutorFragment _ContextLessElementCS__ElementCS = new ExecutorFragment(Types._ContextLessElementCS, BaseCSTables.Types._ElementCS);
		private static final @NonNull ExecutorFragment _ContextLessElementCS__OclAny = new ExecutorFragment(Types._ContextLessElementCS, OCLstdlibTables.Types._OclAny);
		private static final @NonNull ExecutorFragment _ContextLessElementCS__OclElement = new ExecutorFragment(Types._ContextLessElementCS, OCLstdlibTables.Types._OclElement);
		private static final @NonNull ExecutorFragment _ContextLessElementCS__VisitableCS = new ExecutorFragment(Types._ContextLessElementCS, BaseCSTables.Types._VisitableCS);

		private static final @NonNull ExecutorFragment _DataTypeCS__ClassCS = new ExecutorFragment(Types._DataTypeCS, BaseCSTables.Types._ClassCS);
		private static final @NonNull ExecutorFragment _DataTypeCS__DataTypeCS = new ExecutorFragment(Types._DataTypeCS, BaseCSTables.Types._DataTypeCS);
		private static final @NonNull ExecutorFragment _DataTypeCS__ElementCS = new ExecutorFragment(Types._DataTypeCS, BaseCSTables.Types._ElementCS);
		private static final @NonNull ExecutorFragment _DataTypeCS__ModelElementCS = new ExecutorFragment(Types._DataTypeCS, BaseCSTables.Types._ModelElementCS);
		private static final @NonNull ExecutorFragment _DataTypeCS__Nameable = new ExecutorFragment(Types._DataTypeCS, PivotTables.Types._Nameable);
		private static final @NonNull ExecutorFragment _DataTypeCS__NamedElementCS = new ExecutorFragment(Types._DataTypeCS, BaseCSTables.Types._NamedElementCS);
		private static final @NonNull ExecutorFragment _DataTypeCS__NamespaceCS = new ExecutorFragment(Types._DataTypeCS, BaseCSTables.Types._NamespaceCS);
		private static final @NonNull ExecutorFragment _DataTypeCS__OclAny = new ExecutorFragment(Types._DataTypeCS, OCLstdlibTables.Types._OclAny);
		private static final @NonNull ExecutorFragment _DataTypeCS__OclElement = new ExecutorFragment(Types._DataTypeCS, OCLstdlibTables.Types._OclElement);
		private static final @NonNull ExecutorFragment _DataTypeCS__Pivotable = new ExecutorFragment(Types._DataTypeCS, PivotTables.Types._Pivotable);
		private static final @NonNull ExecutorFragment _DataTypeCS__PivotableElementCS = new ExecutorFragment(Types._DataTypeCS, BaseCSTables.Types._PivotableElementCS);
		private static final @NonNull ExecutorFragment _DataTypeCS__TemplateableElementCS = new ExecutorFragment(Types._DataTypeCS, BaseCSTables.Types._TemplateableElementCS);
		private static final @NonNull ExecutorFragment _DataTypeCS__TypeCS = new ExecutorFragment(Types._DataTypeCS, BaseCSTables.Types._TypeCS);
		private static final @NonNull ExecutorFragment _DataTypeCS__VisitableCS = new ExecutorFragment(Types._DataTypeCS, BaseCSTables.Types._VisitableCS);

		private static final @NonNull ExecutorFragment _DetailCS__DetailCS = new ExecutorFragment(Types._DetailCS, BaseCSTables.Types._DetailCS);
		private static final @NonNull ExecutorFragment _DetailCS__ElementCS = new ExecutorFragment(Types._DetailCS, BaseCSTables.Types._ElementCS);
		private static final @NonNull ExecutorFragment _DetailCS__ModelElementCS = new ExecutorFragment(Types._DetailCS, BaseCSTables.Types._ModelElementCS);
		private static final @NonNull ExecutorFragment _DetailCS__Nameable = new ExecutorFragment(Types._DetailCS, PivotTables.Types._Nameable);
		private static final @NonNull ExecutorFragment _DetailCS__NamedElementCS = new ExecutorFragment(Types._DetailCS, BaseCSTables.Types._NamedElementCS);
		private static final @NonNull ExecutorFragment _DetailCS__OclAny = new ExecutorFragment(Types._DetailCS, OCLstdlibTables.Types._OclAny);
		private static final @NonNull ExecutorFragment _DetailCS__OclElement = new ExecutorFragment(Types._DetailCS, OCLstdlibTables.Types._OclElement);
		private static final @NonNull ExecutorFragment _DetailCS__Pivotable = new ExecutorFragment(Types._DetailCS, PivotTables.Types._Pivotable);
		private static final @NonNull ExecutorFragment _DetailCS__PivotableElementCS = new ExecutorFragment(Types._DetailCS, BaseCSTables.Types._PivotableElementCS);
		private static final @NonNull ExecutorFragment _DetailCS__VisitableCS = new ExecutorFragment(Types._DetailCS, BaseCSTables.Types._VisitableCS);

		private static final @NonNull ExecutorFragment _DocumentationCS__AnnotationElementCS = new ExecutorFragment(Types._DocumentationCS, BaseCSTables.Types._AnnotationElementCS);
		private static final @NonNull ExecutorFragment _DocumentationCS__DocumentationCS = new ExecutorFragment(Types._DocumentationCS, BaseCSTables.Types._DocumentationCS);
		private static final @NonNull ExecutorFragment _DocumentationCS__ElementCS = new ExecutorFragment(Types._DocumentationCS, BaseCSTables.Types._ElementCS);
		private static final @NonNull ExecutorFragment _DocumentationCS__ModelElementCS = new ExecutorFragment(Types._DocumentationCS, BaseCSTables.Types._ModelElementCS);
		private static final @NonNull ExecutorFragment _DocumentationCS__Nameable = new ExecutorFragment(Types._DocumentationCS, PivotTables.Types._Nameable);
		private static final @NonNull ExecutorFragment _DocumentationCS__NamedElementCS = new ExecutorFragment(Types._DocumentationCS, BaseCSTables.Types._NamedElementCS);
		private static final @NonNull ExecutorFragment _DocumentationCS__OclAny = new ExecutorFragment(Types._DocumentationCS, OCLstdlibTables.Types._OclAny);
		private static final @NonNull ExecutorFragment _DocumentationCS__OclElement = new ExecutorFragment(Types._DocumentationCS, OCLstdlibTables.Types._OclElement);
		private static final @NonNull ExecutorFragment _DocumentationCS__Pivotable = new ExecutorFragment(Types._DocumentationCS, PivotTables.Types._Pivotable);
		private static final @NonNull ExecutorFragment _DocumentationCS__PivotableElementCS = new ExecutorFragment(Types._DocumentationCS, BaseCSTables.Types._PivotableElementCS);
		private static final @NonNull ExecutorFragment _DocumentationCS__VisitableCS = new ExecutorFragment(Types._DocumentationCS, BaseCSTables.Types._VisitableCS);

		private static final @NonNull ExecutorFragment _ElementCS__ElementCS = new ExecutorFragment(Types._ElementCS, BaseCSTables.Types._ElementCS);
		private static final @NonNull ExecutorFragment _ElementCS__OclAny = new ExecutorFragment(Types._ElementCS, OCLstdlibTables.Types._OclAny);
		private static final @NonNull ExecutorFragment _ElementCS__OclElement = new ExecutorFragment(Types._ElementCS, OCLstdlibTables.Types._OclElement);
		private static final @NonNull ExecutorFragment _ElementCS__VisitableCS = new ExecutorFragment(Types._ElementCS, BaseCSTables.Types._VisitableCS);

		private static final @NonNull ExecutorFragment _ElementRefCS__ElementCS = new ExecutorFragment(Types._ElementRefCS, BaseCSTables.Types._ElementCS);
		private static final @NonNull ExecutorFragment _ElementRefCS__ElementRefCS = new ExecutorFragment(Types._ElementRefCS, BaseCSTables.Types._ElementRefCS);
		private static final @NonNull ExecutorFragment _ElementRefCS__OclAny = new ExecutorFragment(Types._ElementRefCS, OCLstdlibTables.Types._OclAny);
		private static final @NonNull ExecutorFragment _ElementRefCS__OclElement = new ExecutorFragment(Types._ElementRefCS, OCLstdlibTables.Types._OclElement);
		private static final @NonNull ExecutorFragment _ElementRefCS__Pivotable = new ExecutorFragment(Types._ElementRefCS, PivotTables.Types._Pivotable);
		private static final @NonNull ExecutorFragment _ElementRefCS__PivotableElementCS = new ExecutorFragment(Types._ElementRefCS, BaseCSTables.Types._PivotableElementCS);
		private static final @NonNull ExecutorFragment _ElementRefCS__VisitableCS = new ExecutorFragment(Types._ElementRefCS, BaseCSTables.Types._VisitableCS);

		private static final @NonNull ExecutorFragment _EnumerationCS__ClassCS = new ExecutorFragment(Types._EnumerationCS, BaseCSTables.Types._ClassCS);
		private static final @NonNull ExecutorFragment _EnumerationCS__ElementCS = new ExecutorFragment(Types._EnumerationCS, BaseCSTables.Types._ElementCS);
		private static final @NonNull ExecutorFragment _EnumerationCS__EnumerationCS = new ExecutorFragment(Types._EnumerationCS, BaseCSTables.Types._EnumerationCS);
		private static final @NonNull ExecutorFragment _EnumerationCS__ModelElementCS = new ExecutorFragment(Types._EnumerationCS, BaseCSTables.Types._ModelElementCS);
		private static final @NonNull ExecutorFragment _EnumerationCS__Nameable = new ExecutorFragment(Types._EnumerationCS, PivotTables.Types._Nameable);
		private static final @NonNull ExecutorFragment _EnumerationCS__NamedElementCS = new ExecutorFragment(Types._EnumerationCS, BaseCSTables.Types._NamedElementCS);
		private static final @NonNull ExecutorFragment _EnumerationCS__NamespaceCS = new ExecutorFragment(Types._EnumerationCS, BaseCSTables.Types._NamespaceCS);
		private static final @NonNull ExecutorFragment _EnumerationCS__OclAny = new ExecutorFragment(Types._EnumerationCS, OCLstdlibTables.Types._OclAny);
		private static final @NonNull ExecutorFragment _EnumerationCS__OclElement = new ExecutorFragment(Types._EnumerationCS, OCLstdlibTables.Types._OclElement);
		private static final @NonNull ExecutorFragment _EnumerationCS__Pivotable = new ExecutorFragment(Types._EnumerationCS, PivotTables.Types._Pivotable);
		private static final @NonNull ExecutorFragment _EnumerationCS__PivotableElementCS = new ExecutorFragment(Types._EnumerationCS, BaseCSTables.Types._PivotableElementCS);
		private static final @NonNull ExecutorFragment _EnumerationCS__TemplateableElementCS = new ExecutorFragment(Types._EnumerationCS, BaseCSTables.Types._TemplateableElementCS);
		private static final @NonNull ExecutorFragment _EnumerationCS__TypeCS = new ExecutorFragment(Types._EnumerationCS, BaseCSTables.Types._TypeCS);
		private static final @NonNull ExecutorFragment _EnumerationCS__VisitableCS = new ExecutorFragment(Types._EnumerationCS, BaseCSTables.Types._VisitableCS);

		private static final @NonNull ExecutorFragment _EnumerationLiteralCS__ElementCS = new ExecutorFragment(Types._EnumerationLiteralCS, BaseCSTables.Types._ElementCS);
		private static final @NonNull ExecutorFragment _EnumerationLiteralCS__EnumerationLiteralCS = new ExecutorFragment(Types._EnumerationLiteralCS, BaseCSTables.Types._EnumerationLiteralCS);
		private static final @NonNull ExecutorFragment _EnumerationLiteralCS__ModelElementCS = new ExecutorFragment(Types._EnumerationLiteralCS, BaseCSTables.Types._ModelElementCS);
		private static final @NonNull ExecutorFragment _EnumerationLiteralCS__Nameable = new ExecutorFragment(Types._EnumerationLiteralCS, PivotTables.Types._Nameable);
		private static final @NonNull ExecutorFragment _EnumerationLiteralCS__NamedElementCS = new ExecutorFragment(Types._EnumerationLiteralCS, BaseCSTables.Types._NamedElementCS);
		private static final @NonNull ExecutorFragment _EnumerationLiteralCS__OclAny = new ExecutorFragment(Types._EnumerationLiteralCS, OCLstdlibTables.Types._OclAny);
		private static final @NonNull ExecutorFragment _EnumerationLiteralCS__OclElement = new ExecutorFragment(Types._EnumerationLiteralCS, OCLstdlibTables.Types._OclElement);
		private static final @NonNull ExecutorFragment _EnumerationLiteralCS__Pivotable = new ExecutorFragment(Types._EnumerationLiteralCS, PivotTables.Types._Pivotable);
		private static final @NonNull ExecutorFragment _EnumerationLiteralCS__PivotableElementCS = new ExecutorFragment(Types._EnumerationLiteralCS, BaseCSTables.Types._PivotableElementCS);
		private static final @NonNull ExecutorFragment _EnumerationLiteralCS__VisitableCS = new ExecutorFragment(Types._EnumerationLiteralCS, BaseCSTables.Types._VisitableCS);

		private static final @NonNull ExecutorFragment _FeatureCS__ElementCS = new ExecutorFragment(Types._FeatureCS, BaseCSTables.Types._ElementCS);
		private static final @NonNull ExecutorFragment _FeatureCS__FeatureCS = new ExecutorFragment(Types._FeatureCS, BaseCSTables.Types._FeatureCS);
		private static final @NonNull ExecutorFragment _FeatureCS__ModelElementCS = new ExecutorFragment(Types._FeatureCS, BaseCSTables.Types._ModelElementCS);
		private static final @NonNull ExecutorFragment _FeatureCS__Nameable = new ExecutorFragment(Types._FeatureCS, PivotTables.Types._Nameable);
		private static final @NonNull ExecutorFragment _FeatureCS__NamedElementCS = new ExecutorFragment(Types._FeatureCS, BaseCSTables.Types._NamedElementCS);
		private static final @NonNull ExecutorFragment _FeatureCS__OclAny = new ExecutorFragment(Types._FeatureCS, OCLstdlibTables.Types._OclAny);
		private static final @NonNull ExecutorFragment _FeatureCS__OclElement = new ExecutorFragment(Types._FeatureCS, OCLstdlibTables.Types._OclElement);
		private static final @NonNull ExecutorFragment _FeatureCS__Pivotable = new ExecutorFragment(Types._FeatureCS, PivotTables.Types._Pivotable);
		private static final @NonNull ExecutorFragment _FeatureCS__PivotableElementCS = new ExecutorFragment(Types._FeatureCS, BaseCSTables.Types._PivotableElementCS);
		private static final @NonNull ExecutorFragment _FeatureCS__TypedElementCS = new ExecutorFragment(Types._FeatureCS, BaseCSTables.Types._TypedElementCS);
		private static final @NonNull ExecutorFragment _FeatureCS__VisitableCS = new ExecutorFragment(Types._FeatureCS, BaseCSTables.Types._VisitableCS);

		private static final @NonNull ExecutorFragment _ImportCS__ElementCS = new ExecutorFragment(Types._ImportCS, BaseCSTables.Types._ElementCS);
		private static final @NonNull ExecutorFragment _ImportCS__ImportCS = new ExecutorFragment(Types._ImportCS, BaseCSTables.Types._ImportCS);
		private static final @NonNull ExecutorFragment _ImportCS__ModelElementCS = new ExecutorFragment(Types._ImportCS, BaseCSTables.Types._ModelElementCS);
		private static final @NonNull ExecutorFragment _ImportCS__Nameable = new ExecutorFragment(Types._ImportCS, PivotTables.Types._Nameable);
		private static final @NonNull ExecutorFragment _ImportCS__NamedElementCS = new ExecutorFragment(Types._ImportCS, BaseCSTables.Types._NamedElementCS);
		private static final @NonNull ExecutorFragment _ImportCS__NamespaceCS = new ExecutorFragment(Types._ImportCS, BaseCSTables.Types._NamespaceCS);
		private static final @NonNull ExecutorFragment _ImportCS__OclAny = new ExecutorFragment(Types._ImportCS, OCLstdlibTables.Types._OclAny);
		private static final @NonNull ExecutorFragment _ImportCS__OclElement = new ExecutorFragment(Types._ImportCS, OCLstdlibTables.Types._OclElement);
		private static final @NonNull ExecutorFragment _ImportCS__Pivotable = new ExecutorFragment(Types._ImportCS, PivotTables.Types._Pivotable);
		private static final @NonNull ExecutorFragment _ImportCS__PivotableElementCS = new ExecutorFragment(Types._ImportCS, BaseCSTables.Types._PivotableElementCS);
		private static final @NonNull ExecutorFragment _ImportCS__VisitableCS = new ExecutorFragment(Types._ImportCS, BaseCSTables.Types._VisitableCS);

		private static final @NonNull ExecutorFragment _LambdaTypeCS__ElementCS = new ExecutorFragment(Types._LambdaTypeCS, BaseCSTables.Types._ElementCS);
		private static final @NonNull ExecutorFragment _LambdaTypeCS__ElementRefCS = new ExecutorFragment(Types._LambdaTypeCS, BaseCSTables.Types._ElementRefCS);
		private static final @NonNull ExecutorFragment _LambdaTypeCS__LambdaTypeCS = new ExecutorFragment(Types._LambdaTypeCS, BaseCSTables.Types._LambdaTypeCS);
		private static final @NonNull ExecutorFragment _LambdaTypeCS__Nameable = new ExecutorFragment(Types._LambdaTypeCS, PivotTables.Types._Nameable);
		private static final @NonNull ExecutorFragment _LambdaTypeCS__OclAny = new ExecutorFragment(Types._LambdaTypeCS, OCLstdlibTables.Types._OclAny);
		private static final @NonNull ExecutorFragment _LambdaTypeCS__OclElement = new ExecutorFragment(Types._LambdaTypeCS, OCLstdlibTables.Types._OclElement);
		private static final @NonNull ExecutorFragment _LambdaTypeCS__Pivotable = new ExecutorFragment(Types._LambdaTypeCS, PivotTables.Types._Pivotable);
		private static final @NonNull ExecutorFragment _LambdaTypeCS__PivotableElementCS = new ExecutorFragment(Types._LambdaTypeCS, BaseCSTables.Types._PivotableElementCS);
		private static final @NonNull ExecutorFragment _LambdaTypeCS__TemplateableElementCS = new ExecutorFragment(Types._LambdaTypeCS, BaseCSTables.Types._TemplateableElementCS);
		private static final @NonNull ExecutorFragment _LambdaTypeCS__TypeRefCS = new ExecutorFragment(Types._LambdaTypeCS, BaseCSTables.Types._TypeRefCS);
		private static final @NonNull ExecutorFragment _LambdaTypeCS__TypedRefCS = new ExecutorFragment(Types._LambdaTypeCS, BaseCSTables.Types._TypedRefCS);
		private static final @NonNull ExecutorFragment _LambdaTypeCS__VisitableCS = new ExecutorFragment(Types._LambdaTypeCS, BaseCSTables.Types._VisitableCS);

		private static final @NonNull ExecutorFragment _ModelElementCS__ElementCS = new ExecutorFragment(Types._ModelElementCS, BaseCSTables.Types._ElementCS);
		private static final @NonNull ExecutorFragment _ModelElementCS__ModelElementCS = new ExecutorFragment(Types._ModelElementCS, BaseCSTables.Types._ModelElementCS);
		private static final @NonNull ExecutorFragment _ModelElementCS__OclAny = new ExecutorFragment(Types._ModelElementCS, OCLstdlibTables.Types._OclAny);
		private static final @NonNull ExecutorFragment _ModelElementCS__OclElement = new ExecutorFragment(Types._ModelElementCS, OCLstdlibTables.Types._OclElement);
		private static final @NonNull ExecutorFragment _ModelElementCS__Pivotable = new ExecutorFragment(Types._ModelElementCS, PivotTables.Types._Pivotable);
		private static final @NonNull ExecutorFragment _ModelElementCS__PivotableElementCS = new ExecutorFragment(Types._ModelElementCS, BaseCSTables.Types._PivotableElementCS);
		private static final @NonNull ExecutorFragment _ModelElementCS__VisitableCS = new ExecutorFragment(Types._ModelElementCS, BaseCSTables.Types._VisitableCS);

		private static final @NonNull ExecutorFragment _ModelElementRefCS__ElementCS = new ExecutorFragment(Types._ModelElementRefCS, BaseCSTables.Types._ElementCS);
		private static final @NonNull ExecutorFragment _ModelElementRefCS__ElementRefCS = new ExecutorFragment(Types._ModelElementRefCS, BaseCSTables.Types._ElementRefCS);
		private static final @NonNull ExecutorFragment _ModelElementRefCS__ModelElementRefCS = new ExecutorFragment(Types._ModelElementRefCS, BaseCSTables.Types._ModelElementRefCS);
		private static final @NonNull ExecutorFragment _ModelElementRefCS__OclAny = new ExecutorFragment(Types._ModelElementRefCS, OCLstdlibTables.Types._OclAny);
		private static final @NonNull ExecutorFragment _ModelElementRefCS__OclElement = new ExecutorFragment(Types._ModelElementRefCS, OCLstdlibTables.Types._OclElement);
		private static final @NonNull ExecutorFragment _ModelElementRefCS__Pivotable = new ExecutorFragment(Types._ModelElementRefCS, PivotTables.Types._Pivotable);
		private static final @NonNull ExecutorFragment _ModelElementRefCS__PivotableElementCS = new ExecutorFragment(Types._ModelElementRefCS, BaseCSTables.Types._PivotableElementCS);
		private static final @NonNull ExecutorFragment _ModelElementRefCS__VisitableCS = new ExecutorFragment(Types._ModelElementRefCS, BaseCSTables.Types._VisitableCS);

		private static final @NonNull ExecutorFragment _MultiplicityBoundsCS__ElementCS = new ExecutorFragment(Types._MultiplicityBoundsCS, BaseCSTables.Types._ElementCS);
		private static final @NonNull ExecutorFragment _MultiplicityBoundsCS__MultiplicityBoundsCS = new ExecutorFragment(Types._MultiplicityBoundsCS, BaseCSTables.Types._MultiplicityBoundsCS);
		private static final @NonNull ExecutorFragment _MultiplicityBoundsCS__MultiplicityCS = new ExecutorFragment(Types._MultiplicityBoundsCS, BaseCSTables.Types._MultiplicityCS);
		private static final @NonNull ExecutorFragment _MultiplicityBoundsCS__OclAny = new ExecutorFragment(Types._MultiplicityBoundsCS, OCLstdlibTables.Types._OclAny);
		private static final @NonNull ExecutorFragment _MultiplicityBoundsCS__OclElement = new ExecutorFragment(Types._MultiplicityBoundsCS, OCLstdlibTables.Types._OclElement);
		private static final @NonNull ExecutorFragment _MultiplicityBoundsCS__VisitableCS = new ExecutorFragment(Types._MultiplicityBoundsCS, BaseCSTables.Types._VisitableCS);

		private static final @NonNull ExecutorFragment _MultiplicityCS__ElementCS = new ExecutorFragment(Types._MultiplicityCS, BaseCSTables.Types._ElementCS);
		private static final @NonNull ExecutorFragment _MultiplicityCS__MultiplicityCS = new ExecutorFragment(Types._MultiplicityCS, BaseCSTables.Types._MultiplicityCS);
		private static final @NonNull ExecutorFragment _MultiplicityCS__OclAny = new ExecutorFragment(Types._MultiplicityCS, OCLstdlibTables.Types._OclAny);
		private static final @NonNull ExecutorFragment _MultiplicityCS__OclElement = new ExecutorFragment(Types._MultiplicityCS, OCLstdlibTables.Types._OclElement);
		private static final @NonNull ExecutorFragment _MultiplicityCS__VisitableCS = new ExecutorFragment(Types._MultiplicityCS, BaseCSTables.Types._VisitableCS);

		private static final @NonNull ExecutorFragment _MultiplicityStringCS__ElementCS = new ExecutorFragment(Types._MultiplicityStringCS, BaseCSTables.Types._ElementCS);
		private static final @NonNull ExecutorFragment _MultiplicityStringCS__MultiplicityCS = new ExecutorFragment(Types._MultiplicityStringCS, BaseCSTables.Types._MultiplicityCS);
		private static final @NonNull ExecutorFragment _MultiplicityStringCS__MultiplicityStringCS = new ExecutorFragment(Types._MultiplicityStringCS, BaseCSTables.Types._MultiplicityStringCS);
		private static final @NonNull ExecutorFragment _MultiplicityStringCS__OclAny = new ExecutorFragment(Types._MultiplicityStringCS, OCLstdlibTables.Types._OclAny);
		private static final @NonNull ExecutorFragment _MultiplicityStringCS__OclElement = new ExecutorFragment(Types._MultiplicityStringCS, OCLstdlibTables.Types._OclElement);
		private static final @NonNull ExecutorFragment _MultiplicityStringCS__VisitableCS = new ExecutorFragment(Types._MultiplicityStringCS, BaseCSTables.Types._VisitableCS);

		private static final @NonNull ExecutorFragment _NamedElementCS__ElementCS = new ExecutorFragment(Types._NamedElementCS, BaseCSTables.Types._ElementCS);
		private static final @NonNull ExecutorFragment _NamedElementCS__ModelElementCS = new ExecutorFragment(Types._NamedElementCS, BaseCSTables.Types._ModelElementCS);
		private static final @NonNull ExecutorFragment _NamedElementCS__Nameable = new ExecutorFragment(Types._NamedElementCS, PivotTables.Types._Nameable);
		private static final @NonNull ExecutorFragment _NamedElementCS__NamedElementCS = new ExecutorFragment(Types._NamedElementCS, BaseCSTables.Types._NamedElementCS);
		private static final @NonNull ExecutorFragment _NamedElementCS__OclAny = new ExecutorFragment(Types._NamedElementCS, OCLstdlibTables.Types._OclAny);
		private static final @NonNull ExecutorFragment _NamedElementCS__OclElement = new ExecutorFragment(Types._NamedElementCS, OCLstdlibTables.Types._OclElement);
		private static final @NonNull ExecutorFragment _NamedElementCS__Pivotable = new ExecutorFragment(Types._NamedElementCS, PivotTables.Types._Pivotable);
		private static final @NonNull ExecutorFragment _NamedElementCS__PivotableElementCS = new ExecutorFragment(Types._NamedElementCS, BaseCSTables.Types._PivotableElementCS);
		private static final @NonNull ExecutorFragment _NamedElementCS__VisitableCS = new ExecutorFragment(Types._NamedElementCS, BaseCSTables.Types._VisitableCS);

		private static final @NonNull ExecutorFragment _NamespaceCS__ElementCS = new ExecutorFragment(Types._NamespaceCS, BaseCSTables.Types._ElementCS);
		private static final @NonNull ExecutorFragment _NamespaceCS__ModelElementCS = new ExecutorFragment(Types._NamespaceCS, BaseCSTables.Types._ModelElementCS);
		private static final @NonNull ExecutorFragment _NamespaceCS__Nameable = new ExecutorFragment(Types._NamespaceCS, PivotTables.Types._Nameable);
		private static final @NonNull ExecutorFragment _NamespaceCS__NamedElementCS = new ExecutorFragment(Types._NamespaceCS, BaseCSTables.Types._NamedElementCS);
		private static final @NonNull ExecutorFragment _NamespaceCS__NamespaceCS = new ExecutorFragment(Types._NamespaceCS, BaseCSTables.Types._NamespaceCS);
		private static final @NonNull ExecutorFragment _NamespaceCS__OclAny = new ExecutorFragment(Types._NamespaceCS, OCLstdlibTables.Types._OclAny);
		private static final @NonNull ExecutorFragment _NamespaceCS__OclElement = new ExecutorFragment(Types._NamespaceCS, OCLstdlibTables.Types._OclElement);
		private static final @NonNull ExecutorFragment _NamespaceCS__Pivotable = new ExecutorFragment(Types._NamespaceCS, PivotTables.Types._Pivotable);
		private static final @NonNull ExecutorFragment _NamespaceCS__PivotableElementCS = new ExecutorFragment(Types._NamespaceCS, BaseCSTables.Types._PivotableElementCS);
		private static final @NonNull ExecutorFragment _NamespaceCS__VisitableCS = new ExecutorFragment(Types._NamespaceCS, BaseCSTables.Types._VisitableCS);

		private static final @NonNull ExecutorFragment _OperationCS__ElementCS = new ExecutorFragment(Types._OperationCS, BaseCSTables.Types._ElementCS);
		private static final @NonNull ExecutorFragment _OperationCS__FeatureCS = new ExecutorFragment(Types._OperationCS, BaseCSTables.Types._FeatureCS);
		private static final @NonNull ExecutorFragment _OperationCS__ModelElementCS = new ExecutorFragment(Types._OperationCS, BaseCSTables.Types._ModelElementCS);
		private static final @NonNull ExecutorFragment _OperationCS__Nameable = new ExecutorFragment(Types._OperationCS, PivotTables.Types._Nameable);
		private static final @NonNull ExecutorFragment _OperationCS__NamedElementCS = new ExecutorFragment(Types._OperationCS, BaseCSTables.Types._NamedElementCS);
		private static final @NonNull ExecutorFragment _OperationCS__OclAny = new ExecutorFragment(Types._OperationCS, OCLstdlibTables.Types._OclAny);
		private static final @NonNull ExecutorFragment _OperationCS__OclElement = new ExecutorFragment(Types._OperationCS, OCLstdlibTables.Types._OclElement);
		private static final @NonNull ExecutorFragment _OperationCS__OperationCS = new ExecutorFragment(Types._OperationCS, BaseCSTables.Types._OperationCS);
		private static final @NonNull ExecutorFragment _OperationCS__Pivotable = new ExecutorFragment(Types._OperationCS, PivotTables.Types._Pivotable);
		private static final @NonNull ExecutorFragment _OperationCS__PivotableElementCS = new ExecutorFragment(Types._OperationCS, BaseCSTables.Types._PivotableElementCS);
		private static final @NonNull ExecutorFragment _OperationCS__TemplateableElementCS = new ExecutorFragment(Types._OperationCS, BaseCSTables.Types._TemplateableElementCS);
		private static final @NonNull ExecutorFragment _OperationCS__TypedElementCS = new ExecutorFragment(Types._OperationCS, BaseCSTables.Types._TypedElementCS);
		private static final @NonNull ExecutorFragment _OperationCS__VisitableCS = new ExecutorFragment(Types._OperationCS, BaseCSTables.Types._VisitableCS);

		private static final @NonNull ExecutorFragment _PackageCS__ElementCS = new ExecutorFragment(Types._PackageCS, BaseCSTables.Types._ElementCS);
		private static final @NonNull ExecutorFragment _PackageCS__ModelElementCS = new ExecutorFragment(Types._PackageCS, BaseCSTables.Types._ModelElementCS);
		private static final @NonNull ExecutorFragment _PackageCS__Nameable = new ExecutorFragment(Types._PackageCS, PivotTables.Types._Nameable);
		private static final @NonNull ExecutorFragment _PackageCS__NamedElementCS = new ExecutorFragment(Types._PackageCS, BaseCSTables.Types._NamedElementCS);
		private static final @NonNull ExecutorFragment _PackageCS__NamespaceCS = new ExecutorFragment(Types._PackageCS, BaseCSTables.Types._NamespaceCS);
		private static final @NonNull ExecutorFragment _PackageCS__OclAny = new ExecutorFragment(Types._PackageCS, OCLstdlibTables.Types._OclAny);
		private static final @NonNull ExecutorFragment _PackageCS__OclElement = new ExecutorFragment(Types._PackageCS, OCLstdlibTables.Types._OclElement);
		private static final @NonNull ExecutorFragment _PackageCS__PackageCS = new ExecutorFragment(Types._PackageCS, BaseCSTables.Types._PackageCS);
		private static final @NonNull ExecutorFragment _PackageCS__PackageOwnerCS = new ExecutorFragment(Types._PackageCS, BaseCSTables.Types._PackageOwnerCS);
		private static final @NonNull ExecutorFragment _PackageCS__Pivotable = new ExecutorFragment(Types._PackageCS, PivotTables.Types._Pivotable);
		private static final @NonNull ExecutorFragment _PackageCS__PivotableElementCS = new ExecutorFragment(Types._PackageCS, BaseCSTables.Types._PivotableElementCS);
		private static final @NonNull ExecutorFragment _PackageCS__VisitableCS = new ExecutorFragment(Types._PackageCS, BaseCSTables.Types._VisitableCS);

		private static final @NonNull ExecutorFragment _PackageOwnerCS__ElementCS = new ExecutorFragment(Types._PackageOwnerCS, BaseCSTables.Types._ElementCS);
		private static final @NonNull ExecutorFragment _PackageOwnerCS__ModelElementCS = new ExecutorFragment(Types._PackageOwnerCS, BaseCSTables.Types._ModelElementCS);
		private static final @NonNull ExecutorFragment _PackageOwnerCS__OclAny = new ExecutorFragment(Types._PackageOwnerCS, OCLstdlibTables.Types._OclAny);
		private static final @NonNull ExecutorFragment _PackageOwnerCS__OclElement = new ExecutorFragment(Types._PackageOwnerCS, OCLstdlibTables.Types._OclElement);
		private static final @NonNull ExecutorFragment _PackageOwnerCS__PackageOwnerCS = new ExecutorFragment(Types._PackageOwnerCS, BaseCSTables.Types._PackageOwnerCS);
		private static final @NonNull ExecutorFragment _PackageOwnerCS__Pivotable = new ExecutorFragment(Types._PackageOwnerCS, PivotTables.Types._Pivotable);
		private static final @NonNull ExecutorFragment _PackageOwnerCS__PivotableElementCS = new ExecutorFragment(Types._PackageOwnerCS, BaseCSTables.Types._PivotableElementCS);
		private static final @NonNull ExecutorFragment _PackageOwnerCS__VisitableCS = new ExecutorFragment(Types._PackageOwnerCS, BaseCSTables.Types._VisitableCS);

		private static final @NonNull ExecutorFragment _ParameterCS__ElementCS = new ExecutorFragment(Types._ParameterCS, BaseCSTables.Types._ElementCS);
		private static final @NonNull ExecutorFragment _ParameterCS__ModelElementCS = new ExecutorFragment(Types._ParameterCS, BaseCSTables.Types._ModelElementCS);
		private static final @NonNull ExecutorFragment _ParameterCS__Nameable = new ExecutorFragment(Types._ParameterCS, PivotTables.Types._Nameable);
		private static final @NonNull ExecutorFragment _ParameterCS__NamedElementCS = new ExecutorFragment(Types._ParameterCS, BaseCSTables.Types._NamedElementCS);
		private static final @NonNull ExecutorFragment _ParameterCS__OclAny = new ExecutorFragment(Types._ParameterCS, OCLstdlibTables.Types._OclAny);
		private static final @NonNull ExecutorFragment _ParameterCS__OclElement = new ExecutorFragment(Types._ParameterCS, OCLstdlibTables.Types._OclElement);
		private static final @NonNull ExecutorFragment _ParameterCS__ParameterCS = new ExecutorFragment(Types._ParameterCS, BaseCSTables.Types._ParameterCS);
		private static final @NonNull ExecutorFragment _ParameterCS__Pivotable = new ExecutorFragment(Types._ParameterCS, PivotTables.Types._Pivotable);
		private static final @NonNull ExecutorFragment _ParameterCS__PivotableElementCS = new ExecutorFragment(Types._ParameterCS, BaseCSTables.Types._PivotableElementCS);
		private static final @NonNull ExecutorFragment _ParameterCS__TypedElementCS = new ExecutorFragment(Types._ParameterCS, BaseCSTables.Types._TypedElementCS);
		private static final @NonNull ExecutorFragment _ParameterCS__VisitableCS = new ExecutorFragment(Types._ParameterCS, BaseCSTables.Types._VisitableCS);

		private static final @NonNull ExecutorFragment _PathElementCS__ElementCS = new ExecutorFragment(Types._PathElementCS, BaseCSTables.Types._ElementCS);
		private static final @NonNull ExecutorFragment _PathElementCS__OclAny = new ExecutorFragment(Types._PathElementCS, OCLstdlibTables.Types._OclAny);
		private static final @NonNull ExecutorFragment _PathElementCS__OclElement = new ExecutorFragment(Types._PathElementCS, OCLstdlibTables.Types._OclElement);
		private static final @NonNull ExecutorFragment _PathElementCS__PathElementCS = new ExecutorFragment(Types._PathElementCS, BaseCSTables.Types._PathElementCS);
		private static final @NonNull ExecutorFragment _PathElementCS__Pivotable = new ExecutorFragment(Types._PathElementCS, PivotTables.Types._Pivotable);
		private static final @NonNull ExecutorFragment _PathElementCS__VisitableCS = new ExecutorFragment(Types._PathElementCS, BaseCSTables.Types._VisitableCS);

		private static final @NonNull ExecutorFragment _PathElementWithURICS__ElementCS = new ExecutorFragment(Types._PathElementWithURICS, BaseCSTables.Types._ElementCS);
		private static final @NonNull ExecutorFragment _PathElementWithURICS__OclAny = new ExecutorFragment(Types._PathElementWithURICS, OCLstdlibTables.Types._OclAny);
		private static final @NonNull ExecutorFragment _PathElementWithURICS__OclElement = new ExecutorFragment(Types._PathElementWithURICS, OCLstdlibTables.Types._OclElement);
		private static final @NonNull ExecutorFragment _PathElementWithURICS__PathElementCS = new ExecutorFragment(Types._PathElementWithURICS, BaseCSTables.Types._PathElementCS);
		private static final @NonNull ExecutorFragment _PathElementWithURICS__PathElementWithURICS = new ExecutorFragment(Types._PathElementWithURICS, BaseCSTables.Types._PathElementWithURICS);
		private static final @NonNull ExecutorFragment _PathElementWithURICS__Pivotable = new ExecutorFragment(Types._PathElementWithURICS, PivotTables.Types._Pivotable);
		private static final @NonNull ExecutorFragment _PathElementWithURICS__VisitableCS = new ExecutorFragment(Types._PathElementWithURICS, BaseCSTables.Types._VisitableCS);

		private static final @NonNull ExecutorFragment _PathNameCS__ElementCS = new ExecutorFragment(Types._PathNameCS, BaseCSTables.Types._ElementCS);
		private static final @NonNull ExecutorFragment _PathNameCS__OclAny = new ExecutorFragment(Types._PathNameCS, OCLstdlibTables.Types._OclAny);
		private static final @NonNull ExecutorFragment _PathNameCS__OclElement = new ExecutorFragment(Types._PathNameCS, OCLstdlibTables.Types._OclElement);
		private static final @NonNull ExecutorFragment _PathNameCS__PathNameCS = new ExecutorFragment(Types._PathNameCS, BaseCSTables.Types._PathNameCS);
		private static final @NonNull ExecutorFragment _PathNameCS__Pivotable = new ExecutorFragment(Types._PathNameCS, PivotTables.Types._Pivotable);
		private static final @NonNull ExecutorFragment _PathNameCS__VisitableCS = new ExecutorFragment(Types._PathNameCS, BaseCSTables.Types._VisitableCS);

		private static final @NonNull ExecutorFragment _PivotableElementCS__ElementCS = new ExecutorFragment(Types._PivotableElementCS, BaseCSTables.Types._ElementCS);
		private static final @NonNull ExecutorFragment _PivotableElementCS__OclAny = new ExecutorFragment(Types._PivotableElementCS, OCLstdlibTables.Types._OclAny);
		private static final @NonNull ExecutorFragment _PivotableElementCS__OclElement = new ExecutorFragment(Types._PivotableElementCS, OCLstdlibTables.Types._OclElement);
		private static final @NonNull ExecutorFragment _PivotableElementCS__Pivotable = new ExecutorFragment(Types._PivotableElementCS, PivotTables.Types._Pivotable);
		private static final @NonNull ExecutorFragment _PivotableElementCS__PivotableElementCS = new ExecutorFragment(Types._PivotableElementCS, BaseCSTables.Types._PivotableElementCS);
		private static final @NonNull ExecutorFragment _PivotableElementCS__VisitableCS = new ExecutorFragment(Types._PivotableElementCS, BaseCSTables.Types._VisitableCS);

		private static final @NonNull ExecutorFragment _PrimitiveTypeRefCS__ElementCS = new ExecutorFragment(Types._PrimitiveTypeRefCS, BaseCSTables.Types._ElementCS);
		private static final @NonNull ExecutorFragment _PrimitiveTypeRefCS__ElementRefCS = new ExecutorFragment(Types._PrimitiveTypeRefCS, BaseCSTables.Types._ElementRefCS);
		private static final @NonNull ExecutorFragment _PrimitiveTypeRefCS__Nameable = new ExecutorFragment(Types._PrimitiveTypeRefCS, PivotTables.Types._Nameable);
		private static final @NonNull ExecutorFragment _PrimitiveTypeRefCS__OclAny = new ExecutorFragment(Types._PrimitiveTypeRefCS, OCLstdlibTables.Types._OclAny);
		private static final @NonNull ExecutorFragment _PrimitiveTypeRefCS__OclElement = new ExecutorFragment(Types._PrimitiveTypeRefCS, OCLstdlibTables.Types._OclElement);
		private static final @NonNull ExecutorFragment _PrimitiveTypeRefCS__Pivotable = new ExecutorFragment(Types._PrimitiveTypeRefCS, PivotTables.Types._Pivotable);
		private static final @NonNull ExecutorFragment _PrimitiveTypeRefCS__PivotableElementCS = new ExecutorFragment(Types._PrimitiveTypeRefCS, BaseCSTables.Types._PivotableElementCS);
		private static final @NonNull ExecutorFragment _PrimitiveTypeRefCS__PrimitiveTypeRefCS = new ExecutorFragment(Types._PrimitiveTypeRefCS, BaseCSTables.Types._PrimitiveTypeRefCS);
		private static final @NonNull ExecutorFragment _PrimitiveTypeRefCS__TypeRefCS = new ExecutorFragment(Types._PrimitiveTypeRefCS, BaseCSTables.Types._TypeRefCS);
		private static final @NonNull ExecutorFragment _PrimitiveTypeRefCS__TypedRefCS = new ExecutorFragment(Types._PrimitiveTypeRefCS, BaseCSTables.Types._TypedRefCS);
		private static final @NonNull ExecutorFragment _PrimitiveTypeRefCS__VisitableCS = new ExecutorFragment(Types._PrimitiveTypeRefCS, BaseCSTables.Types._VisitableCS);

		private static final @NonNull ExecutorFragment _ReferenceCS__ElementCS = new ExecutorFragment(Types._ReferenceCS, BaseCSTables.Types._ElementCS);
		private static final @NonNull ExecutorFragment _ReferenceCS__FeatureCS = new ExecutorFragment(Types._ReferenceCS, BaseCSTables.Types._FeatureCS);
		private static final @NonNull ExecutorFragment _ReferenceCS__ModelElementCS = new ExecutorFragment(Types._ReferenceCS, BaseCSTables.Types._ModelElementCS);
		private static final @NonNull ExecutorFragment _ReferenceCS__Nameable = new ExecutorFragment(Types._ReferenceCS, PivotTables.Types._Nameable);
		private static final @NonNull ExecutorFragment _ReferenceCS__NamedElementCS = new ExecutorFragment(Types._ReferenceCS, BaseCSTables.Types._NamedElementCS);
		private static final @NonNull ExecutorFragment _ReferenceCS__OclAny = new ExecutorFragment(Types._ReferenceCS, OCLstdlibTables.Types._OclAny);
		private static final @NonNull ExecutorFragment _ReferenceCS__OclElement = new ExecutorFragment(Types._ReferenceCS, OCLstdlibTables.Types._OclElement);
		private static final @NonNull ExecutorFragment _ReferenceCS__Pivotable = new ExecutorFragment(Types._ReferenceCS, PivotTables.Types._Pivotable);
		private static final @NonNull ExecutorFragment _ReferenceCS__PivotableElementCS = new ExecutorFragment(Types._ReferenceCS, BaseCSTables.Types._PivotableElementCS);
		private static final @NonNull ExecutorFragment _ReferenceCS__ReferenceCS = new ExecutorFragment(Types._ReferenceCS, BaseCSTables.Types._ReferenceCS);
		private static final @NonNull ExecutorFragment _ReferenceCS__StructuralFeatureCS = new ExecutorFragment(Types._ReferenceCS, BaseCSTables.Types._StructuralFeatureCS);
		private static final @NonNull ExecutorFragment _ReferenceCS__TypedElementCS = new ExecutorFragment(Types._ReferenceCS, BaseCSTables.Types._TypedElementCS);
		private static final @NonNull ExecutorFragment _ReferenceCS__VisitableCS = new ExecutorFragment(Types._ReferenceCS, BaseCSTables.Types._VisitableCS);

		private static final @NonNull ExecutorFragment _RootCS__ElementCS = new ExecutorFragment(Types._RootCS, BaseCSTables.Types._ElementCS);
		private static final @NonNull ExecutorFragment _RootCS__ModelElementCS = new ExecutorFragment(Types._RootCS, BaseCSTables.Types._ModelElementCS);
		private static final @NonNull ExecutorFragment _RootCS__OclAny = new ExecutorFragment(Types._RootCS, OCLstdlibTables.Types._OclAny);
		private static final @NonNull ExecutorFragment _RootCS__OclElement = new ExecutorFragment(Types._RootCS, OCLstdlibTables.Types._OclElement);
		private static final @NonNull ExecutorFragment _RootCS__Pivotable = new ExecutorFragment(Types._RootCS, PivotTables.Types._Pivotable);
		private static final @NonNull ExecutorFragment _RootCS__PivotableElementCS = new ExecutorFragment(Types._RootCS, BaseCSTables.Types._PivotableElementCS);
		private static final @NonNull ExecutorFragment _RootCS__RootCS = new ExecutorFragment(Types._RootCS, BaseCSTables.Types._RootCS);
		private static final @NonNull ExecutorFragment _RootCS__VisitableCS = new ExecutorFragment(Types._RootCS, BaseCSTables.Types._VisitableCS);

		private static final @NonNull ExecutorFragment _RootPackageCS__ElementCS = new ExecutorFragment(Types._RootPackageCS, BaseCSTables.Types._ElementCS);
		private static final @NonNull ExecutorFragment _RootPackageCS__ModelElementCS = new ExecutorFragment(Types._RootPackageCS, BaseCSTables.Types._ModelElementCS);
		private static final @NonNull ExecutorFragment _RootPackageCS__OclAny = new ExecutorFragment(Types._RootPackageCS, OCLstdlibTables.Types._OclAny);
		private static final @NonNull ExecutorFragment _RootPackageCS__OclElement = new ExecutorFragment(Types._RootPackageCS, OCLstdlibTables.Types._OclElement);
		private static final @NonNull ExecutorFragment _RootPackageCS__PackageOwnerCS = new ExecutorFragment(Types._RootPackageCS, BaseCSTables.Types._PackageOwnerCS);
		private static final @NonNull ExecutorFragment _RootPackageCS__Pivotable = new ExecutorFragment(Types._RootPackageCS, PivotTables.Types._Pivotable);
		private static final @NonNull ExecutorFragment _RootPackageCS__PivotableElementCS = new ExecutorFragment(Types._RootPackageCS, BaseCSTables.Types._PivotableElementCS);
		private static final @NonNull ExecutorFragment _RootPackageCS__RootCS = new ExecutorFragment(Types._RootPackageCS, BaseCSTables.Types._RootCS);
		private static final @NonNull ExecutorFragment _RootPackageCS__RootPackageCS = new ExecutorFragment(Types._RootPackageCS, BaseCSTables.Types._RootPackageCS);
		private static final @NonNull ExecutorFragment _RootPackageCS__VisitableCS = new ExecutorFragment(Types._RootPackageCS, BaseCSTables.Types._VisitableCS);

		private static final @NonNull ExecutorFragment _ScopeFilter__OclAny = new ExecutorFragment(Types._ScopeFilter, OCLstdlibTables.Types._OclAny);
		private static final @NonNull ExecutorFragment _ScopeFilter__ScopeFilter = new ExecutorFragment(Types._ScopeFilter, BaseCSTables.Types._ScopeFilter);

		private static final @NonNull ExecutorFragment _SpecificationCS__ElementCS = new ExecutorFragment(Types._SpecificationCS, BaseCSTables.Types._ElementCS);
		private static final @NonNull ExecutorFragment _SpecificationCS__ModelElementCS = new ExecutorFragment(Types._SpecificationCS, BaseCSTables.Types._ModelElementCS);
		private static final @NonNull ExecutorFragment _SpecificationCS__OclAny = new ExecutorFragment(Types._SpecificationCS, OCLstdlibTables.Types._OclAny);
		private static final @NonNull ExecutorFragment _SpecificationCS__OclElement = new ExecutorFragment(Types._SpecificationCS, OCLstdlibTables.Types._OclElement);
		private static final @NonNull ExecutorFragment _SpecificationCS__Pivotable = new ExecutorFragment(Types._SpecificationCS, PivotTables.Types._Pivotable);
		private static final @NonNull ExecutorFragment _SpecificationCS__PivotableElementCS = new ExecutorFragment(Types._SpecificationCS, BaseCSTables.Types._PivotableElementCS);
		private static final @NonNull ExecutorFragment _SpecificationCS__SpecificationCS = new ExecutorFragment(Types._SpecificationCS, BaseCSTables.Types._SpecificationCS);
		private static final @NonNull ExecutorFragment _SpecificationCS__VisitableCS = new ExecutorFragment(Types._SpecificationCS, BaseCSTables.Types._VisitableCS);

		private static final @NonNull ExecutorFragment _StructuralFeatureCS__ElementCS = new ExecutorFragment(Types._StructuralFeatureCS, BaseCSTables.Types._ElementCS);
		private static final @NonNull ExecutorFragment _StructuralFeatureCS__FeatureCS = new ExecutorFragment(Types._StructuralFeatureCS, BaseCSTables.Types._FeatureCS);
		private static final @NonNull ExecutorFragment _StructuralFeatureCS__ModelElementCS = new ExecutorFragment(Types._StructuralFeatureCS, BaseCSTables.Types._ModelElementCS);
		private static final @NonNull ExecutorFragment _StructuralFeatureCS__Nameable = new ExecutorFragment(Types._StructuralFeatureCS, PivotTables.Types._Nameable);
		private static final @NonNull ExecutorFragment _StructuralFeatureCS__NamedElementCS = new ExecutorFragment(Types._StructuralFeatureCS, BaseCSTables.Types._NamedElementCS);
		private static final @NonNull ExecutorFragment _StructuralFeatureCS__OclAny = new ExecutorFragment(Types._StructuralFeatureCS, OCLstdlibTables.Types._OclAny);
		private static final @NonNull ExecutorFragment _StructuralFeatureCS__OclElement = new ExecutorFragment(Types._StructuralFeatureCS, OCLstdlibTables.Types._OclElement);
		private static final @NonNull ExecutorFragment _StructuralFeatureCS__Pivotable = new ExecutorFragment(Types._StructuralFeatureCS, PivotTables.Types._Pivotable);
		private static final @NonNull ExecutorFragment _StructuralFeatureCS__PivotableElementCS = new ExecutorFragment(Types._StructuralFeatureCS, BaseCSTables.Types._PivotableElementCS);
		private static final @NonNull ExecutorFragment _StructuralFeatureCS__StructuralFeatureCS = new ExecutorFragment(Types._StructuralFeatureCS, BaseCSTables.Types._StructuralFeatureCS);
		private static final @NonNull ExecutorFragment _StructuralFeatureCS__TypedElementCS = new ExecutorFragment(Types._StructuralFeatureCS, BaseCSTables.Types._TypedElementCS);
		private static final @NonNull ExecutorFragment _StructuralFeatureCS__VisitableCS = new ExecutorFragment(Types._StructuralFeatureCS, BaseCSTables.Types._VisitableCS);

		private static final @NonNull ExecutorFragment _StructuredClassCS__ClassCS = new ExecutorFragment(Types._StructuredClassCS, BaseCSTables.Types._ClassCS);
		private static final @NonNull ExecutorFragment _StructuredClassCS__ElementCS = new ExecutorFragment(Types._StructuredClassCS, BaseCSTables.Types._ElementCS);
		private static final @NonNull ExecutorFragment _StructuredClassCS__ModelElementCS = new ExecutorFragment(Types._StructuredClassCS, BaseCSTables.Types._ModelElementCS);
		private static final @NonNull ExecutorFragment _StructuredClassCS__Nameable = new ExecutorFragment(Types._StructuredClassCS, PivotTables.Types._Nameable);
		private static final @NonNull ExecutorFragment _StructuredClassCS__NamedElementCS = new ExecutorFragment(Types._StructuredClassCS, BaseCSTables.Types._NamedElementCS);
		private static final @NonNull ExecutorFragment _StructuredClassCS__NamespaceCS = new ExecutorFragment(Types._StructuredClassCS, BaseCSTables.Types._NamespaceCS);
		private static final @NonNull ExecutorFragment _StructuredClassCS__OclAny = new ExecutorFragment(Types._StructuredClassCS, OCLstdlibTables.Types._OclAny);
		private static final @NonNull ExecutorFragment _StructuredClassCS__OclElement = new ExecutorFragment(Types._StructuredClassCS, OCLstdlibTables.Types._OclElement);
		private static final @NonNull ExecutorFragment _StructuredClassCS__Pivotable = new ExecutorFragment(Types._StructuredClassCS, PivotTables.Types._Pivotable);
		private static final @NonNull ExecutorFragment _StructuredClassCS__PivotableElementCS = new ExecutorFragment(Types._StructuredClassCS, BaseCSTables.Types._PivotableElementCS);
		private static final @NonNull ExecutorFragment _StructuredClassCS__StructuredClassCS = new ExecutorFragment(Types._StructuredClassCS, BaseCSTables.Types._StructuredClassCS);
		private static final @NonNull ExecutorFragment _StructuredClassCS__TemplateableElementCS = new ExecutorFragment(Types._StructuredClassCS, BaseCSTables.Types._TemplateableElementCS);
		private static final @NonNull ExecutorFragment _StructuredClassCS__TypeCS = new ExecutorFragment(Types._StructuredClassCS, BaseCSTables.Types._TypeCS);
		private static final @NonNull ExecutorFragment _StructuredClassCS__VisitableCS = new ExecutorFragment(Types._StructuredClassCS, BaseCSTables.Types._VisitableCS);

		private static final @NonNull ExecutorFragment _TemplateBindingCS__ElementCS = new ExecutorFragment(Types._TemplateBindingCS, BaseCSTables.Types._ElementCS);
		private static final @NonNull ExecutorFragment _TemplateBindingCS__ElementRefCS = new ExecutorFragment(Types._TemplateBindingCS, BaseCSTables.Types._ElementRefCS);
		private static final @NonNull ExecutorFragment _TemplateBindingCS__OclAny = new ExecutorFragment(Types._TemplateBindingCS, OCLstdlibTables.Types._OclAny);
		private static final @NonNull ExecutorFragment _TemplateBindingCS__OclElement = new ExecutorFragment(Types._TemplateBindingCS, OCLstdlibTables.Types._OclElement);
		private static final @NonNull ExecutorFragment _TemplateBindingCS__Pivotable = new ExecutorFragment(Types._TemplateBindingCS, PivotTables.Types._Pivotable);
		private static final @NonNull ExecutorFragment _TemplateBindingCS__PivotableElementCS = new ExecutorFragment(Types._TemplateBindingCS, BaseCSTables.Types._PivotableElementCS);
		private static final @NonNull ExecutorFragment _TemplateBindingCS__TemplateBindingCS = new ExecutorFragment(Types._TemplateBindingCS, BaseCSTables.Types._TemplateBindingCS);
		private static final @NonNull ExecutorFragment _TemplateBindingCS__VisitableCS = new ExecutorFragment(Types._TemplateBindingCS, BaseCSTables.Types._VisitableCS);

		private static final @NonNull ExecutorFragment _TemplateParameterCS__ElementCS = new ExecutorFragment(Types._TemplateParameterCS, BaseCSTables.Types._ElementCS);
		private static final @NonNull ExecutorFragment _TemplateParameterCS__ModelElementCS = new ExecutorFragment(Types._TemplateParameterCS, BaseCSTables.Types._ModelElementCS);
		private static final @NonNull ExecutorFragment _TemplateParameterCS__Nameable = new ExecutorFragment(Types._TemplateParameterCS, PivotTables.Types._Nameable);
		private static final @NonNull ExecutorFragment _TemplateParameterCS__NamedElementCS = new ExecutorFragment(Types._TemplateParameterCS, BaseCSTables.Types._NamedElementCS);
		private static final @NonNull ExecutorFragment _TemplateParameterCS__OclAny = new ExecutorFragment(Types._TemplateParameterCS, OCLstdlibTables.Types._OclAny);
		private static final @NonNull ExecutorFragment _TemplateParameterCS__OclElement = new ExecutorFragment(Types._TemplateParameterCS, OCLstdlibTables.Types._OclElement);
		private static final @NonNull ExecutorFragment _TemplateParameterCS__Pivotable = new ExecutorFragment(Types._TemplateParameterCS, PivotTables.Types._Pivotable);
		private static final @NonNull ExecutorFragment _TemplateParameterCS__PivotableElementCS = new ExecutorFragment(Types._TemplateParameterCS, BaseCSTables.Types._PivotableElementCS);
		private static final @NonNull ExecutorFragment _TemplateParameterCS__TemplateParameterCS = new ExecutorFragment(Types._TemplateParameterCS, BaseCSTables.Types._TemplateParameterCS);
		private static final @NonNull ExecutorFragment _TemplateParameterCS__VisitableCS = new ExecutorFragment(Types._TemplateParameterCS, BaseCSTables.Types._VisitableCS);

		private static final @NonNull ExecutorFragment _TemplateParameterSubstitutionCS__ElementCS = new ExecutorFragment(Types._TemplateParameterSubstitutionCS, BaseCSTables.Types._ElementCS);
		private static final @NonNull ExecutorFragment _TemplateParameterSubstitutionCS__ModelElementCS = new ExecutorFragment(Types._TemplateParameterSubstitutionCS, BaseCSTables.Types._ModelElementCS);
		private static final @NonNull ExecutorFragment _TemplateParameterSubstitutionCS__OclAny = new ExecutorFragment(Types._TemplateParameterSubstitutionCS, OCLstdlibTables.Types._OclAny);
		private static final @NonNull ExecutorFragment _TemplateParameterSubstitutionCS__OclElement = new ExecutorFragment(Types._TemplateParameterSubstitutionCS, OCLstdlibTables.Types._OclElement);
		private static final @NonNull ExecutorFragment _TemplateParameterSubstitutionCS__Pivotable = new ExecutorFragment(Types._TemplateParameterSubstitutionCS, PivotTables.Types._Pivotable);
		private static final @NonNull ExecutorFragment _TemplateParameterSubstitutionCS__PivotableElementCS = new ExecutorFragment(Types._TemplateParameterSubstitutionCS, BaseCSTables.Types._PivotableElementCS);
		private static final @NonNull ExecutorFragment _TemplateParameterSubstitutionCS__TemplateParameterSubstitutionCS = new ExecutorFragment(Types._TemplateParameterSubstitutionCS, BaseCSTables.Types._TemplateParameterSubstitutionCS);
		private static final @NonNull ExecutorFragment _TemplateParameterSubstitutionCS__VisitableCS = new ExecutorFragment(Types._TemplateParameterSubstitutionCS, BaseCSTables.Types._VisitableCS);

		private static final @NonNull ExecutorFragment _TemplateSignatureCS__ElementCS = new ExecutorFragment(Types._TemplateSignatureCS, BaseCSTables.Types._ElementCS);
		private static final @NonNull ExecutorFragment _TemplateSignatureCS__ModelElementCS = new ExecutorFragment(Types._TemplateSignatureCS, BaseCSTables.Types._ModelElementCS);
		private static final @NonNull ExecutorFragment _TemplateSignatureCS__OclAny = new ExecutorFragment(Types._TemplateSignatureCS, OCLstdlibTables.Types._OclAny);
		private static final @NonNull ExecutorFragment _TemplateSignatureCS__OclElement = new ExecutorFragment(Types._TemplateSignatureCS, OCLstdlibTables.Types._OclElement);
		private static final @NonNull ExecutorFragment _TemplateSignatureCS__Pivotable = new ExecutorFragment(Types._TemplateSignatureCS, PivotTables.Types._Pivotable);
		private static final @NonNull ExecutorFragment _TemplateSignatureCS__PivotableElementCS = new ExecutorFragment(Types._TemplateSignatureCS, BaseCSTables.Types._PivotableElementCS);
		private static final @NonNull ExecutorFragment _TemplateSignatureCS__TemplateSignatureCS = new ExecutorFragment(Types._TemplateSignatureCS, BaseCSTables.Types._TemplateSignatureCS);
		private static final @NonNull ExecutorFragment _TemplateSignatureCS__VisitableCS = new ExecutorFragment(Types._TemplateSignatureCS, BaseCSTables.Types._VisitableCS);

		private static final @NonNull ExecutorFragment _TemplateableElementCS__ElementCS = new ExecutorFragment(Types._TemplateableElementCS, BaseCSTables.Types._ElementCS);
		private static final @NonNull ExecutorFragment _TemplateableElementCS__OclAny = new ExecutorFragment(Types._TemplateableElementCS, OCLstdlibTables.Types._OclAny);
		private static final @NonNull ExecutorFragment _TemplateableElementCS__OclElement = new ExecutorFragment(Types._TemplateableElementCS, OCLstdlibTables.Types._OclElement);
		private static final @NonNull ExecutorFragment _TemplateableElementCS__TemplateableElementCS = new ExecutorFragment(Types._TemplateableElementCS, BaseCSTables.Types._TemplateableElementCS);
		private static final @NonNull ExecutorFragment _TemplateableElementCS__VisitableCS = new ExecutorFragment(Types._TemplateableElementCS, BaseCSTables.Types._VisitableCS);

		private static final @NonNull ExecutorFragment _TuplePartCS__ElementCS = new ExecutorFragment(Types._TuplePartCS, BaseCSTables.Types._ElementCS);
		private static final @NonNull ExecutorFragment _TuplePartCS__ModelElementCS = new ExecutorFragment(Types._TuplePartCS, BaseCSTables.Types._ModelElementCS);
		private static final @NonNull ExecutorFragment _TuplePartCS__Nameable = new ExecutorFragment(Types._TuplePartCS, PivotTables.Types._Nameable);
		private static final @NonNull ExecutorFragment _TuplePartCS__NamedElementCS = new ExecutorFragment(Types._TuplePartCS, BaseCSTables.Types._NamedElementCS);
		private static final @NonNull ExecutorFragment _TuplePartCS__OclAny = new ExecutorFragment(Types._TuplePartCS, OCLstdlibTables.Types._OclAny);
		private static final @NonNull ExecutorFragment _TuplePartCS__OclElement = new ExecutorFragment(Types._TuplePartCS, OCLstdlibTables.Types._OclElement);
		private static final @NonNull ExecutorFragment _TuplePartCS__Pivotable = new ExecutorFragment(Types._TuplePartCS, PivotTables.Types._Pivotable);
		private static final @NonNull ExecutorFragment _TuplePartCS__PivotableElementCS = new ExecutorFragment(Types._TuplePartCS, BaseCSTables.Types._PivotableElementCS);
		private static final @NonNull ExecutorFragment _TuplePartCS__TuplePartCS = new ExecutorFragment(Types._TuplePartCS, BaseCSTables.Types._TuplePartCS);
		private static final @NonNull ExecutorFragment _TuplePartCS__TypedElementCS = new ExecutorFragment(Types._TuplePartCS, BaseCSTables.Types._TypedElementCS);
		private static final @NonNull ExecutorFragment _TuplePartCS__VisitableCS = new ExecutorFragment(Types._TuplePartCS, BaseCSTables.Types._VisitableCS);

		private static final @NonNull ExecutorFragment _TupleTypeCS__ElementCS = new ExecutorFragment(Types._TupleTypeCS, BaseCSTables.Types._ElementCS);
		private static final @NonNull ExecutorFragment _TupleTypeCS__ElementRefCS = new ExecutorFragment(Types._TupleTypeCS, BaseCSTables.Types._ElementRefCS);
		private static final @NonNull ExecutorFragment _TupleTypeCS__Nameable = new ExecutorFragment(Types._TupleTypeCS, PivotTables.Types._Nameable);
		private static final @NonNull ExecutorFragment _TupleTypeCS__OclAny = new ExecutorFragment(Types._TupleTypeCS, OCLstdlibTables.Types._OclAny);
		private static final @NonNull ExecutorFragment _TupleTypeCS__OclElement = new ExecutorFragment(Types._TupleTypeCS, OCLstdlibTables.Types._OclElement);
		private static final @NonNull ExecutorFragment _TupleTypeCS__Pivotable = new ExecutorFragment(Types._TupleTypeCS, PivotTables.Types._Pivotable);
		private static final @NonNull ExecutorFragment _TupleTypeCS__PivotableElementCS = new ExecutorFragment(Types._TupleTypeCS, BaseCSTables.Types._PivotableElementCS);
		private static final @NonNull ExecutorFragment _TupleTypeCS__TupleTypeCS = new ExecutorFragment(Types._TupleTypeCS, BaseCSTables.Types._TupleTypeCS);
		private static final @NonNull ExecutorFragment _TupleTypeCS__TypeRefCS = new ExecutorFragment(Types._TupleTypeCS, BaseCSTables.Types._TypeRefCS);
		private static final @NonNull ExecutorFragment _TupleTypeCS__TypedRefCS = new ExecutorFragment(Types._TupleTypeCS, BaseCSTables.Types._TypedRefCS);
		private static final @NonNull ExecutorFragment _TupleTypeCS__VisitableCS = new ExecutorFragment(Types._TupleTypeCS, BaseCSTables.Types._VisitableCS);

		private static final @NonNull ExecutorFragment _TypeCS__ElementCS = new ExecutorFragment(Types._TypeCS, BaseCSTables.Types._ElementCS);
		private static final @NonNull ExecutorFragment _TypeCS__ModelElementCS = new ExecutorFragment(Types._TypeCS, BaseCSTables.Types._ModelElementCS);
		private static final @NonNull ExecutorFragment _TypeCS__OclAny = new ExecutorFragment(Types._TypeCS, OCLstdlibTables.Types._OclAny);
		private static final @NonNull ExecutorFragment _TypeCS__OclElement = new ExecutorFragment(Types._TypeCS, OCLstdlibTables.Types._OclElement);
		private static final @NonNull ExecutorFragment _TypeCS__Pivotable = new ExecutorFragment(Types._TypeCS, PivotTables.Types._Pivotable);
		private static final @NonNull ExecutorFragment _TypeCS__PivotableElementCS = new ExecutorFragment(Types._TypeCS, BaseCSTables.Types._PivotableElementCS);
		private static final @NonNull ExecutorFragment _TypeCS__TypeCS = new ExecutorFragment(Types._TypeCS, BaseCSTables.Types._TypeCS);
		private static final @NonNull ExecutorFragment _TypeCS__VisitableCS = new ExecutorFragment(Types._TypeCS, BaseCSTables.Types._VisitableCS);

		private static final @NonNull ExecutorFragment _TypeParameterCS__ElementCS = new ExecutorFragment(Types._TypeParameterCS, BaseCSTables.Types._ElementCS);
		private static final @NonNull ExecutorFragment _TypeParameterCS__ModelElementCS = new ExecutorFragment(Types._TypeParameterCS, BaseCSTables.Types._ModelElementCS);
		private static final @NonNull ExecutorFragment _TypeParameterCS__Nameable = new ExecutorFragment(Types._TypeParameterCS, PivotTables.Types._Nameable);
		private static final @NonNull ExecutorFragment _TypeParameterCS__NamedElementCS = new ExecutorFragment(Types._TypeParameterCS, BaseCSTables.Types._NamedElementCS);
		private static final @NonNull ExecutorFragment _TypeParameterCS__OclAny = new ExecutorFragment(Types._TypeParameterCS, OCLstdlibTables.Types._OclAny);
		private static final @NonNull ExecutorFragment _TypeParameterCS__OclElement = new ExecutorFragment(Types._TypeParameterCS, OCLstdlibTables.Types._OclElement);
		private static final @NonNull ExecutorFragment _TypeParameterCS__Pivotable = new ExecutorFragment(Types._TypeParameterCS, PivotTables.Types._Pivotable);
		private static final @NonNull ExecutorFragment _TypeParameterCS__PivotableElementCS = new ExecutorFragment(Types._TypeParameterCS, BaseCSTables.Types._PivotableElementCS);
		private static final @NonNull ExecutorFragment _TypeParameterCS__TemplateParameterCS = new ExecutorFragment(Types._TypeParameterCS, BaseCSTables.Types._TemplateParameterCS);
		private static final @NonNull ExecutorFragment _TypeParameterCS__TypeCS = new ExecutorFragment(Types._TypeParameterCS, BaseCSTables.Types._TypeCS);
		private static final @NonNull ExecutorFragment _TypeParameterCS__TypeParameterCS = new ExecutorFragment(Types._TypeParameterCS, BaseCSTables.Types._TypeParameterCS);
		private static final @NonNull ExecutorFragment _TypeParameterCS__VisitableCS = new ExecutorFragment(Types._TypeParameterCS, BaseCSTables.Types._VisitableCS);

		private static final @NonNull ExecutorFragment _TypeRefCS__ElementCS = new ExecutorFragment(Types._TypeRefCS, BaseCSTables.Types._ElementCS);
		private static final @NonNull ExecutorFragment _TypeRefCS__ElementRefCS = new ExecutorFragment(Types._TypeRefCS, BaseCSTables.Types._ElementRefCS);
		private static final @NonNull ExecutorFragment _TypeRefCS__OclAny = new ExecutorFragment(Types._TypeRefCS, OCLstdlibTables.Types._OclAny);
		private static final @NonNull ExecutorFragment _TypeRefCS__OclElement = new ExecutorFragment(Types._TypeRefCS, OCLstdlibTables.Types._OclElement);
		private static final @NonNull ExecutorFragment _TypeRefCS__Pivotable = new ExecutorFragment(Types._TypeRefCS, PivotTables.Types._Pivotable);
		private static final @NonNull ExecutorFragment _TypeRefCS__PivotableElementCS = new ExecutorFragment(Types._TypeRefCS, BaseCSTables.Types._PivotableElementCS);
		private static final @NonNull ExecutorFragment _TypeRefCS__TypeRefCS = new ExecutorFragment(Types._TypeRefCS, BaseCSTables.Types._TypeRefCS);
		private static final @NonNull ExecutorFragment _TypeRefCS__VisitableCS = new ExecutorFragment(Types._TypeRefCS, BaseCSTables.Types._VisitableCS);

		private static final @NonNull ExecutorFragment _TypedElementCS__ElementCS = new ExecutorFragment(Types._TypedElementCS, BaseCSTables.Types._ElementCS);
		private static final @NonNull ExecutorFragment _TypedElementCS__ModelElementCS = new ExecutorFragment(Types._TypedElementCS, BaseCSTables.Types._ModelElementCS);
		private static final @NonNull ExecutorFragment _TypedElementCS__Nameable = new ExecutorFragment(Types._TypedElementCS, PivotTables.Types._Nameable);
		private static final @NonNull ExecutorFragment _TypedElementCS__NamedElementCS = new ExecutorFragment(Types._TypedElementCS, BaseCSTables.Types._NamedElementCS);
		private static final @NonNull ExecutorFragment _TypedElementCS__OclAny = new ExecutorFragment(Types._TypedElementCS, OCLstdlibTables.Types._OclAny);
		private static final @NonNull ExecutorFragment _TypedElementCS__OclElement = new ExecutorFragment(Types._TypedElementCS, OCLstdlibTables.Types._OclElement);
		private static final @NonNull ExecutorFragment _TypedElementCS__Pivotable = new ExecutorFragment(Types._TypedElementCS, PivotTables.Types._Pivotable);
		private static final @NonNull ExecutorFragment _TypedElementCS__PivotableElementCS = new ExecutorFragment(Types._TypedElementCS, BaseCSTables.Types._PivotableElementCS);
		private static final @NonNull ExecutorFragment _TypedElementCS__TypedElementCS = new ExecutorFragment(Types._TypedElementCS, BaseCSTables.Types._TypedElementCS);
		private static final @NonNull ExecutorFragment _TypedElementCS__VisitableCS = new ExecutorFragment(Types._TypedElementCS, BaseCSTables.Types._VisitableCS);

		private static final @NonNull ExecutorFragment _TypedRefCS__ElementCS = new ExecutorFragment(Types._TypedRefCS, BaseCSTables.Types._ElementCS);
		private static final @NonNull ExecutorFragment _TypedRefCS__ElementRefCS = new ExecutorFragment(Types._TypedRefCS, BaseCSTables.Types._ElementRefCS);
		private static final @NonNull ExecutorFragment _TypedRefCS__OclAny = new ExecutorFragment(Types._TypedRefCS, OCLstdlibTables.Types._OclAny);
		private static final @NonNull ExecutorFragment _TypedRefCS__OclElement = new ExecutorFragment(Types._TypedRefCS, OCLstdlibTables.Types._OclElement);
		private static final @NonNull ExecutorFragment _TypedRefCS__Pivotable = new ExecutorFragment(Types._TypedRefCS, PivotTables.Types._Pivotable);
		private static final @NonNull ExecutorFragment _TypedRefCS__PivotableElementCS = new ExecutorFragment(Types._TypedRefCS, BaseCSTables.Types._PivotableElementCS);
		private static final @NonNull ExecutorFragment _TypedRefCS__TypeRefCS = new ExecutorFragment(Types._TypedRefCS, BaseCSTables.Types._TypeRefCS);
		private static final @NonNull ExecutorFragment _TypedRefCS__TypedRefCS = new ExecutorFragment(Types._TypedRefCS, BaseCSTables.Types._TypedRefCS);
		private static final @NonNull ExecutorFragment _TypedRefCS__VisitableCS = new ExecutorFragment(Types._TypedRefCS, BaseCSTables.Types._VisitableCS);

		private static final @NonNull ExecutorFragment _TypedTypeRefCS__ElementCS = new ExecutorFragment(Types._TypedTypeRefCS, BaseCSTables.Types._ElementCS);
		private static final @NonNull ExecutorFragment _TypedTypeRefCS__ElementRefCS = new ExecutorFragment(Types._TypedTypeRefCS, BaseCSTables.Types._ElementRefCS);
		private static final @NonNull ExecutorFragment _TypedTypeRefCS__OclAny = new ExecutorFragment(Types._TypedTypeRefCS, OCLstdlibTables.Types._OclAny);
		private static final @NonNull ExecutorFragment _TypedTypeRefCS__OclElement = new ExecutorFragment(Types._TypedTypeRefCS, OCLstdlibTables.Types._OclElement);
		private static final @NonNull ExecutorFragment _TypedTypeRefCS__Pivotable = new ExecutorFragment(Types._TypedTypeRefCS, PivotTables.Types._Pivotable);
		private static final @NonNull ExecutorFragment _TypedTypeRefCS__PivotableElementCS = new ExecutorFragment(Types._TypedTypeRefCS, BaseCSTables.Types._PivotableElementCS);
		private static final @NonNull ExecutorFragment _TypedTypeRefCS__TypeRefCS = new ExecutorFragment(Types._TypedTypeRefCS, BaseCSTables.Types._TypeRefCS);
		private static final @NonNull ExecutorFragment _TypedTypeRefCS__TypedRefCS = new ExecutorFragment(Types._TypedTypeRefCS, BaseCSTables.Types._TypedRefCS);
		private static final @NonNull ExecutorFragment _TypedTypeRefCS__TypedTypeRefCS = new ExecutorFragment(Types._TypedTypeRefCS, BaseCSTables.Types._TypedTypeRefCS);
		private static final @NonNull ExecutorFragment _TypedTypeRefCS__VisitableCS = new ExecutorFragment(Types._TypedTypeRefCS, BaseCSTables.Types._VisitableCS);

		private static final @NonNull ExecutorFragment _VisitableCS__OclAny = new ExecutorFragment(Types._VisitableCS, OCLstdlibTables.Types._OclAny);
		private static final @NonNull ExecutorFragment _VisitableCS__OclElement = new ExecutorFragment(Types._VisitableCS, OCLstdlibTables.Types._OclElement);
		private static final @NonNull ExecutorFragment _VisitableCS__VisitableCS = new ExecutorFragment(Types._VisitableCS, BaseCSTables.Types._VisitableCS);

		private static final @NonNull ExecutorFragment _WildcardTypeRefCS__ElementCS = new ExecutorFragment(Types._WildcardTypeRefCS, BaseCSTables.Types._ElementCS);
		private static final @NonNull ExecutorFragment _WildcardTypeRefCS__ElementRefCS = new ExecutorFragment(Types._WildcardTypeRefCS, BaseCSTables.Types._ElementRefCS);
		private static final @NonNull ExecutorFragment _WildcardTypeRefCS__OclAny = new ExecutorFragment(Types._WildcardTypeRefCS, OCLstdlibTables.Types._OclAny);
		private static final @NonNull ExecutorFragment _WildcardTypeRefCS__OclElement = new ExecutorFragment(Types._WildcardTypeRefCS, OCLstdlibTables.Types._OclElement);
		private static final @NonNull ExecutorFragment _WildcardTypeRefCS__Pivotable = new ExecutorFragment(Types._WildcardTypeRefCS, PivotTables.Types._Pivotable);
		private static final @NonNull ExecutorFragment _WildcardTypeRefCS__PivotableElementCS = new ExecutorFragment(Types._WildcardTypeRefCS, BaseCSTables.Types._PivotableElementCS);
		private static final @NonNull ExecutorFragment _WildcardTypeRefCS__TypeRefCS = new ExecutorFragment(Types._WildcardTypeRefCS, BaseCSTables.Types._TypeRefCS);
		private static final @NonNull ExecutorFragment _WildcardTypeRefCS__VisitableCS = new ExecutorFragment(Types._WildcardTypeRefCS, BaseCSTables.Types._VisitableCS);
		private static final @NonNull ExecutorFragment _WildcardTypeRefCS__WildcardTypeRefCS = new ExecutorFragment(Types._WildcardTypeRefCS, BaseCSTables.Types._WildcardTypeRefCS);

		static {
			Init.initEnd();
		}

		/**
		 * Force initialization of the fields of BaseCSTables::Fragments and all preceding sub-packages.
		 */
		public static void init() {}
	}

	/**
	 *	The parameter lists shared by operations.
	 */
	public static class Parameters {
		static {
			Init.initStart();
			Fragments.init();
		}


		static {
			Init.initEnd();
		}

		/**
		 * Force initialization of the fields of BaseCSTables::Parameters and all preceding sub-packages.
		 */
		public static void init() {}
	}

	/**
	 *	The operation descriptors for each operation of each type.
	 */
	public static class Operations {
		static {
			Init.initStart();
			Parameters.init();
		}

		public static final @NonNull ExecutorOperation _ElementCS__getDescription = new ExecutorOperation("getDescription", TypeUtil.EMPTY_PARAMETER_TYPES, Types._ElementCS,
			0, TemplateParameters.EMPTY_LIST, null);

		public static final @NonNull ExecutorOperation _MultiplicityCS__getLower = new ExecutorOperation("getLower", TypeUtil.EMPTY_PARAMETER_TYPES, Types._MultiplicityCS,
			0, TemplateParameters.EMPTY_LIST, null);
		public static final @NonNull ExecutorOperation _MultiplicityCS__getUpper = new ExecutorOperation("getUpper", TypeUtil.EMPTY_PARAMETER_TYPES, Types._MultiplicityCS,
			1, TemplateParameters.EMPTY_LIST, null);

		static {
			Init.initEnd();
		}

		/**
		 * Force initialization of the fields of BaseCSTables::Operations and all preceding sub-packages.
		 */
		public static void init() {}
	}

	/**
	 *	The property descriptors for each property of each type.
	 */
	public static class Properties {
		static {
			Init.initStart();
			Operations.init();
		}

		public static final @NonNull ExecutorProperty _AnnotationCS__ownedContents = new EcoreExecutorProperty(BaseCSPackage.Literals.ANNOTATION_CS__OWNED_CONTENTS, Types._AnnotationCS, 0);
		public static final @NonNull ExecutorProperty _AnnotationCS__ownedReferences = new EcoreExecutorProperty(BaseCSPackage.Literals.ANNOTATION_CS__OWNED_REFERENCES, Types._AnnotationCS, 1);

		public static final @NonNull ExecutorProperty _AnnotationElementCS__ownedDetails = new EcoreExecutorProperty(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS, Types._AnnotationElementCS, 0);
		public static final @NonNull ExecutorProperty _AnnotationElementCS__ModelElementCS__ownedAnnotations = new ExecutorPropertyWithImplementation("ModelElementCS", Types._AnnotationElementCS, 1, new EcoreLibraryOppositeProperty(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS));

		public static final @NonNull ExecutorProperty _ClassCS__instanceClassName = new EcoreExecutorProperty(BaseCSPackage.Literals.CLASS_CS__INSTANCE_CLASS_NAME, Types._ClassCS, 0);
		public static final @NonNull ExecutorProperty _ClassCS__ownedConstraints = new EcoreExecutorProperty(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS, Types._ClassCS, 1);
		public static final @NonNull ExecutorProperty _ClassCS__owningPackage = new EcoreExecutorProperty(BaseCSPackage.Literals.CLASS_CS__OWNING_PACKAGE, Types._ClassCS, 2);

		public static final @NonNull ExecutorProperty _ConstraintCS__ownedMessageSpecification = new EcoreExecutorProperty(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION, Types._ConstraintCS, 0);
		public static final @NonNull ExecutorProperty _ConstraintCS__ownedSpecification = new EcoreExecutorProperty(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION, Types._ConstraintCS, 1);
		public static final @NonNull ExecutorProperty _ConstraintCS__stereotype = new EcoreExecutorProperty(BaseCSPackage.Literals.CONSTRAINT_CS__STEREOTYPE, Types._ConstraintCS, 2);
		public static final @NonNull ExecutorProperty _ConstraintCS__ClassCS__ownedConstraints = new ExecutorPropertyWithImplementation("ClassCS", Types._ConstraintCS, 3, new EcoreLibraryOppositeProperty(BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS));
		public static final @NonNull ExecutorProperty _ConstraintCS__OperationCS__ownedPostconditions = new ExecutorPropertyWithImplementation("OperationCS", Types._ConstraintCS, 4, new EcoreLibraryOppositeProperty(BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS));
		public static final @NonNull ExecutorProperty _ConstraintCS__OperationCS__ownedPreconditions = new ExecutorPropertyWithImplementation("OperationCS", Types._ConstraintCS, 5, new EcoreLibraryOppositeProperty(BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS));

		public static final @NonNull ExecutorProperty _DataTypeCS__isPrimitive = new EcoreExecutorProperty(BaseCSPackage.Literals.DATA_TYPE_CS__IS_PRIMITIVE, Types._DataTypeCS, 0);
		public static final @NonNull ExecutorProperty _DataTypeCS__isSerializable = new EcoreExecutorProperty(BaseCSPackage.Literals.DATA_TYPE_CS__IS_SERIALIZABLE, Types._DataTypeCS, 1);

		public static final @NonNull ExecutorProperty _DetailCS__values = new EcoreExecutorProperty(BaseCSPackage.Literals.DETAIL_CS__VALUES, Types._DetailCS, 0);
		public static final @NonNull ExecutorProperty _DetailCS__AnnotationElementCS__ownedDetails = new ExecutorPropertyWithImplementation("AnnotationElementCS", Types._DetailCS, 1, new EcoreLibraryOppositeProperty(BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS));

		public static final @NonNull ExecutorProperty _DocumentationCS__value = new EcoreExecutorProperty(BaseCSPackage.Literals.DOCUMENTATION_CS__VALUE, Types._DocumentationCS, 0);

		public static final @NonNull ExecutorProperty _ElementCS__csi = new EcoreExecutorProperty(BaseCSPackage.Literals.ELEMENT_CS__CSI, Types._ElementCS, 0);
		public static final @NonNull ExecutorProperty _ElementCS__parent = new EcoreExecutorProperty(BaseCSPackage.Literals.ELEMENT_CS__PARENT, Types._ElementCS, 1);
		public static final @NonNull ExecutorProperty _ElementCS__ElementCS__parent = new ExecutorPropertyWithImplementation("ElementCS", Types._ElementCS, 2, new EcoreLibraryOppositeProperty(BaseCSPackage.Literals.ELEMENT_CS__PARENT));

		public static final @NonNull ExecutorProperty _EnumerationCS__isSerializable = new EcoreExecutorProperty(BaseCSPackage.Literals.ENUMERATION_CS__IS_SERIALIZABLE, Types._EnumerationCS, 0);
		public static final @NonNull ExecutorProperty _EnumerationCS__ownedLiterals = new EcoreExecutorProperty(BaseCSPackage.Literals.ENUMERATION_CS__OWNED_LITERALS, Types._EnumerationCS, 1);

		public static final @NonNull ExecutorProperty _EnumerationLiteralCS__value = new EcoreExecutorProperty(BaseCSPackage.Literals.ENUMERATION_LITERAL_CS__VALUE, Types._EnumerationLiteralCS, 0);
		public static final @NonNull ExecutorProperty _EnumerationLiteralCS__EnumerationCS__ownedLiterals = new ExecutorPropertyWithImplementation("EnumerationCS", Types._EnumerationLiteralCS, 1, new EcoreLibraryOppositeProperty(BaseCSPackage.Literals.ENUMERATION_CS__OWNED_LITERALS));

		public static final @NonNull ExecutorProperty _ImportCS__isAll = new EcoreExecutorProperty(BaseCSPackage.Literals.IMPORT_CS__IS_ALL, Types._ImportCS, 0);
		public static final @NonNull ExecutorProperty _ImportCS__ownedPathName = new EcoreExecutorProperty(BaseCSPackage.Literals.IMPORT_CS__OWNED_PATH_NAME, Types._ImportCS, 1);
		public static final @NonNull ExecutorProperty _ImportCS__referredNamespace = new EcoreExecutorProperty(BaseCSPackage.Literals.IMPORT_CS__REFERRED_NAMESPACE, Types._ImportCS, 2);
		public static final @NonNull ExecutorProperty _ImportCS__RootCS__ownedImports = new ExecutorPropertyWithImplementation("RootCS", Types._ImportCS, 3, new EcoreLibraryOppositeProperty(BaseCSPackage.Literals.ROOT_CS__OWNED_IMPORTS));

		public static final @NonNull ExecutorProperty _LambdaTypeCS__name = new EcoreExecutorProperty(BaseCSPackage.Literals.LAMBDA_TYPE_CS__NAME, Types._LambdaTypeCS, 0);
		public static final @NonNull ExecutorProperty _LambdaTypeCS__ownedContextType = new EcoreExecutorProperty(BaseCSPackage.Literals.LAMBDA_TYPE_CS__OWNED_CONTEXT_TYPE, Types._LambdaTypeCS, 1);
		public static final @NonNull ExecutorProperty _LambdaTypeCS__ownedParameterTypes = new EcoreExecutorProperty(BaseCSPackage.Literals.LAMBDA_TYPE_CS__OWNED_PARAMETER_TYPES, Types._LambdaTypeCS, 2);
		public static final @NonNull ExecutorProperty _LambdaTypeCS__ownedResultType = new EcoreExecutorProperty(BaseCSPackage.Literals.LAMBDA_TYPE_CS__OWNED_RESULT_TYPE, Types._LambdaTypeCS, 3);

		public static final @NonNull ExecutorProperty _ModelElementCS__originalXmiId = new EcoreExecutorProperty(BaseCSPackage.Literals.MODEL_ELEMENT_CS__ORIGINAL_XMI_ID, Types._ModelElementCS, 0);
		public static final @NonNull ExecutorProperty _ModelElementCS__ownedAnnotations = new EcoreExecutorProperty(BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, Types._ModelElementCS, 1);
		public static final @NonNull ExecutorProperty _ModelElementCS__AnnotationCS__ownedContents = new ExecutorPropertyWithImplementation("AnnotationCS", Types._ModelElementCS, 2, new EcoreLibraryOppositeProperty(BaseCSPackage.Literals.ANNOTATION_CS__OWNED_CONTENTS));

		public static final @NonNull ExecutorProperty _ModelElementRefCS__ownedPathName = new EcoreExecutorProperty(BaseCSPackage.Literals.MODEL_ELEMENT_REF_CS__OWNED_PATH_NAME, Types._ModelElementRefCS, 0);
		public static final @NonNull ExecutorProperty _ModelElementRefCS__referredElement = new EcoreExecutorProperty(BaseCSPackage.Literals.MODEL_ELEMENT_REF_CS__REFERRED_ELEMENT, Types._ModelElementRefCS, 1);
		public static final @NonNull ExecutorProperty _ModelElementRefCS__AnnotationCS__ownedReferences = new ExecutorPropertyWithImplementation("AnnotationCS", Types._ModelElementRefCS, 2, new EcoreLibraryOppositeProperty(BaseCSPackage.Literals.ANNOTATION_CS__OWNED_REFERENCES));

		public static final @NonNull ExecutorProperty _MultiplicityBoundsCS__lowerBound = new EcoreExecutorProperty(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND, Types._MultiplicityBoundsCS, 0);
		public static final @NonNull ExecutorProperty _MultiplicityBoundsCS__upperBound = new EcoreExecutorProperty(BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__UPPER_BOUND, Types._MultiplicityBoundsCS, 1);

		public static final @NonNull ExecutorProperty _MultiplicityCS__isNullFree = new EcoreExecutorProperty(BaseCSPackage.Literals.MULTIPLICITY_CS__IS_NULL_FREE, Types._MultiplicityCS, 0);
		public static final @NonNull ExecutorProperty _MultiplicityCS__TemplateBindingCS__ownedMultiplicity = new ExecutorPropertyWithImplementation("TemplateBindingCS", Types._MultiplicityCS, 1, new EcoreLibraryOppositeProperty(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY));
		public static final @NonNull ExecutorProperty _MultiplicityCS__TypedRefCS__ownedMultiplicity = new ExecutorPropertyWithImplementation("TypedRefCS", Types._MultiplicityCS, 2, new EcoreLibraryOppositeProperty(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY));

		public static final @NonNull ExecutorProperty _MultiplicityStringCS__stringBounds = new EcoreExecutorProperty(BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS, Types._MultiplicityStringCS, 0);

		public static final @NonNull ExecutorProperty _NamedElementCS__name = new EcoreExecutorProperty(BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME, Types._NamedElementCS, 0);

		public static final @NonNull ExecutorProperty _OperationCS__ownedBodyExpressions = new EcoreExecutorProperty(BaseCSPackage.Literals.OPERATION_CS__OWNED_BODY_EXPRESSIONS, Types._OperationCS, 0);
		public static final @NonNull ExecutorProperty _OperationCS__ownedExceptions = new EcoreExecutorProperty(BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS, Types._OperationCS, 1);
		public static final @NonNull ExecutorProperty _OperationCS__ownedParameters = new EcoreExecutorProperty(BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS, Types._OperationCS, 2);
		public static final @NonNull ExecutorProperty _OperationCS__ownedPostconditions = new EcoreExecutorProperty(BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS, Types._OperationCS, 3);
		public static final @NonNull ExecutorProperty _OperationCS__ownedPreconditions = new EcoreExecutorProperty(BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS, Types._OperationCS, 4);
		public static final @NonNull ExecutorProperty _OperationCS__owningClass = new EcoreExecutorProperty(BaseCSPackage.Literals.OPERATION_CS__OWNING_CLASS, Types._OperationCS, 5);

		public static final @NonNull ExecutorProperty _PackageCS__nsPrefix = new EcoreExecutorProperty(BaseCSPackage.Literals.PACKAGE_CS__NS_PREFIX, Types._PackageCS, 0);
		public static final @NonNull ExecutorProperty _PackageCS__nsURI = new EcoreExecutorProperty(BaseCSPackage.Literals.PACKAGE_CS__NS_URI, Types._PackageCS, 1);
		public static final @NonNull ExecutorProperty _PackageCS__ownedClasses = new EcoreExecutorProperty(BaseCSPackage.Literals.PACKAGE_CS__OWNED_CLASSES, Types._PackageCS, 2);
		public static final @NonNull ExecutorProperty _PackageCS__PackageOwnerCS__ownedPackages = new ExecutorPropertyWithImplementation("PackageOwnerCS", Types._PackageCS, 3, new EcoreLibraryOppositeProperty(BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES));

		public static final @NonNull ExecutorProperty _PackageOwnerCS__ownedPackages = new EcoreExecutorProperty(BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES, Types._PackageOwnerCS, 0);

		public static final @NonNull ExecutorProperty _ParameterCS__owningOperation = new EcoreExecutorProperty(BaseCSPackage.Literals.PARAMETER_CS__OWNING_OPERATION, Types._ParameterCS, 0);

		public static final @NonNull ExecutorProperty _PathElementCS__elementType = new EcoreExecutorProperty(BaseCSPackage.Literals.PATH_ELEMENT_CS__ELEMENT_TYPE, Types._PathElementCS, 0);
		public static final @NonNull ExecutorProperty _PathElementCS__name = new EcoreExecutorProperty(BaseCSPackage.Literals.PATH_ELEMENT_CS__NAME, Types._PathElementCS, 1);
		public static final @NonNull ExecutorProperty _PathElementCS__owningPathName = new EcoreExecutorProperty(BaseCSPackage.Literals.PATH_ELEMENT_CS__OWNING_PATH_NAME, Types._PathElementCS, 2);
		public static final @NonNull ExecutorProperty _PathElementCS__referredElement = new EcoreExecutorProperty(BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, Types._PathElementCS, 3);

		public static final @NonNull ExecutorProperty _PathElementWithURICS__uri = new EcoreExecutorProperty(BaseCSPackage.Literals.PATH_ELEMENT_WITH_URICS__URI, Types._PathElementWithURICS, 0);

		public static final @NonNull ExecutorProperty _PathNameCS__context = new EcoreExecutorProperty(BaseCSPackage.Literals.PATH_NAME_CS__CONTEXT, Types._PathNameCS, 0);
		public static final @NonNull ExecutorProperty _PathNameCS__ownedPathElements = new EcoreExecutorProperty(BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, Types._PathNameCS, 1);
		public static final @NonNull ExecutorProperty _PathNameCS__referredElement = new EcoreExecutorProperty(BaseCSPackage.Literals.PATH_NAME_CS__REFERRED_ELEMENT, Types._PathNameCS, 2);
		public static final @NonNull ExecutorProperty _PathNameCS__scopeFilter = new EcoreExecutorProperty(BaseCSPackage.Literals.PATH_NAME_CS__SCOPE_FILTER, Types._PathNameCS, 3);
		public static final @NonNull ExecutorProperty _PathNameCS__ImportCS__ownedPathName = new ExecutorPropertyWithImplementation("ImportCS", Types._PathNameCS, 4, new EcoreLibraryOppositeProperty(BaseCSPackage.Literals.IMPORT_CS__OWNED_PATH_NAME));
		public static final @NonNull ExecutorProperty _PathNameCS__ModelElementRefCS__ownedPathName = new ExecutorPropertyWithImplementation("ModelElementRefCS", Types._PathNameCS, 5, new EcoreLibraryOppositeProperty(BaseCSPackage.Literals.MODEL_ELEMENT_REF_CS__OWNED_PATH_NAME));
		public static final @NonNull ExecutorProperty _PathNameCS__TypedTypeRefCS__ownedPathName = new ExecutorPropertyWithImplementation("TypedTypeRefCS", Types._PathNameCS, 6, new EcoreLibraryOppositeProperty(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME));

		public static final @NonNull ExecutorProperty _PivotableElementCS__pivot = new EcoreExecutorProperty(BaseCSPackage.Literals.PIVOTABLE_ELEMENT_CS__PIVOT, Types._PivotableElementCS, 0);

		public static final @NonNull ExecutorProperty _PrimitiveTypeRefCS__name = new EcoreExecutorProperty(BaseCSPackage.Literals.PRIMITIVE_TYPE_REF_CS__NAME, Types._PrimitiveTypeRefCS, 0);

		public static final @NonNull ExecutorProperty _ReferenceCS__referredKeys = new EcoreExecutorProperty(BaseCSPackage.Literals.REFERENCE_CS__REFERRED_KEYS, Types._ReferenceCS, 0);
		public static final @NonNull ExecutorProperty _ReferenceCS__referredOpposite = new EcoreExecutorProperty(BaseCSPackage.Literals.REFERENCE_CS__REFERRED_OPPOSITE, Types._ReferenceCS, 1);

		public static final @NonNull ExecutorProperty _RootCS__ownedImports = new EcoreExecutorProperty(BaseCSPackage.Literals.ROOT_CS__OWNED_IMPORTS, Types._RootCS, 0);

		public static final @NonNull ExecutorProperty _SpecificationCS__exprString = new EcoreExecutorProperty(BaseCSPackage.Literals.SPECIFICATION_CS__EXPR_STRING, Types._SpecificationCS, 0);
		public static final @NonNull ExecutorProperty _SpecificationCS__ConstraintCS__ownedMessageSpecification = new ExecutorPropertyWithImplementation("ConstraintCS", Types._SpecificationCS, 1, new EcoreLibraryOppositeProperty(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION));
		public static final @NonNull ExecutorProperty _SpecificationCS__ConstraintCS__ownedSpecification = new ExecutorPropertyWithImplementation("ConstraintCS", Types._SpecificationCS, 2, new EcoreLibraryOppositeProperty(BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION));
		public static final @NonNull ExecutorProperty _SpecificationCS__OperationCS__ownedBodyExpressions = new ExecutorPropertyWithImplementation("OperationCS", Types._SpecificationCS, 3, new EcoreLibraryOppositeProperty(BaseCSPackage.Literals.OPERATION_CS__OWNED_BODY_EXPRESSIONS));
		public static final @NonNull ExecutorProperty _SpecificationCS__StructuralFeatureCS__ownedDefaultExpressions = new ExecutorPropertyWithImplementation("StructuralFeatureCS", Types._SpecificationCS, 4, new EcoreLibraryOppositeProperty(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS));

		public static final @NonNull ExecutorProperty _StructuralFeatureCS__default = new EcoreExecutorProperty(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__DEFAULT, Types._StructuralFeatureCS, 0);
		public static final @NonNull ExecutorProperty _StructuralFeatureCS__ownedDefaultExpressions = new EcoreExecutorProperty(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS, Types._StructuralFeatureCS, 1);
		public static final @NonNull ExecutorProperty _StructuralFeatureCS__owningClass = new EcoreExecutorProperty(BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNING_CLASS, Types._StructuralFeatureCS, 2);

		public static final @NonNull ExecutorProperty _StructuredClassCS__isAbstract = new EcoreExecutorProperty(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__IS_ABSTRACT, Types._StructuredClassCS, 0);
		public static final @NonNull ExecutorProperty _StructuredClassCS__isInterface = new EcoreExecutorProperty(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__IS_INTERFACE, Types._StructuredClassCS, 1);
		public static final @NonNull ExecutorProperty _StructuredClassCS__ownedMetaclass = new EcoreExecutorProperty(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_METACLASS, Types._StructuredClassCS, 2);
		public static final @NonNull ExecutorProperty _StructuredClassCS__ownedOperations = new EcoreExecutorProperty(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_OPERATIONS, Types._StructuredClassCS, 3);
		public static final @NonNull ExecutorProperty _StructuredClassCS__ownedProperties = new EcoreExecutorProperty(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_PROPERTIES, Types._StructuredClassCS, 4);
		public static final @NonNull ExecutorProperty _StructuredClassCS__ownedSuperTypes = new EcoreExecutorProperty(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_SUPER_TYPES, Types._StructuredClassCS, 5);

		public static final @NonNull ExecutorProperty _TemplateBindingCS__ownedMultiplicity = new EcoreExecutorProperty(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY, Types._TemplateBindingCS, 0);
		public static final @NonNull ExecutorProperty _TemplateBindingCS__ownedSubstitutions = new EcoreExecutorProperty(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS, Types._TemplateBindingCS, 1);
		public static final @NonNull ExecutorProperty _TemplateBindingCS__owningElement = new EcoreExecutorProperty(BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNING_ELEMENT, Types._TemplateBindingCS, 2);

		public static final @NonNull ExecutorProperty _TemplateParameterCS__owningSignature = new EcoreExecutorProperty(BaseCSPackage.Literals.TEMPLATE_PARAMETER_CS__OWNING_SIGNATURE, Types._TemplateParameterCS, 0);

		public static final @NonNull ExecutorProperty _TemplateParameterSubstitutionCS__ownedActualParameter = new EcoreExecutorProperty(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER, Types._TemplateParameterSubstitutionCS, 0);
		public static final @NonNull ExecutorProperty _TemplateParameterSubstitutionCS__owningBinding = new EcoreExecutorProperty(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNING_BINDING, Types._TemplateParameterSubstitutionCS, 1);

		public static final @NonNull ExecutorProperty _TemplateSignatureCS__ownedParameters = new EcoreExecutorProperty(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS, Types._TemplateSignatureCS, 0);
		public static final @NonNull ExecutorProperty _TemplateSignatureCS__owningElement = new EcoreExecutorProperty(BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNING_ELEMENT, Types._TemplateSignatureCS, 1);

		public static final @NonNull ExecutorProperty _TemplateableElementCS__ownedSignature = new EcoreExecutorProperty(BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, Types._TemplateableElementCS, 0);

		public static final @NonNull ExecutorProperty _TuplePartCS__TupleTypeCS__ownedParts = new ExecutorPropertyWithImplementation("TupleTypeCS", Types._TuplePartCS, 0, new EcoreLibraryOppositeProperty(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS));

		public static final @NonNull ExecutorProperty _TupleTypeCS__name = new EcoreExecutorProperty(BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME, Types._TupleTypeCS, 0);
		public static final @NonNull ExecutorProperty _TupleTypeCS__ownedParts = new EcoreExecutorProperty(BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS, Types._TupleTypeCS, 1);

		public static final @NonNull ExecutorProperty _TypeParameterCS__ownedExtends = new EcoreExecutorProperty(BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS, Types._TypeParameterCS, 0);

		public static final @NonNull ExecutorProperty _TypeRefCS__TemplateParameterSubstitutionCS__ownedActualParameter = new ExecutorPropertyWithImplementation("TemplateParameterSubstitutionCS", Types._TypeRefCS, 0, new EcoreLibraryOppositeProperty(BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER));

		public static final @NonNull ExecutorProperty _TypedElementCS__isOptional = new EcoreExecutorProperty(BaseCSPackage.Literals.TYPED_ELEMENT_CS__IS_OPTIONAL, Types._TypedElementCS, 0);
		public static final @NonNull ExecutorProperty _TypedElementCS__ownedType = new EcoreExecutorProperty(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, Types._TypedElementCS, 1);
		public static final @NonNull ExecutorProperty _TypedElementCS__qualifiers = new EcoreExecutorProperty(BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS, Types._TypedElementCS, 2);

		public static final @NonNull ExecutorProperty _TypedRefCS__ownedMultiplicity = new EcoreExecutorProperty(BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, Types._TypedRefCS, 0);
		public static final @NonNull ExecutorProperty _TypedRefCS__LambdaTypeCS__ownedContextType = new ExecutorPropertyWithImplementation("LambdaTypeCS", Types._TypedRefCS, 1, new EcoreLibraryOppositeProperty(BaseCSPackage.Literals.LAMBDA_TYPE_CS__OWNED_CONTEXT_TYPE));
		public static final @NonNull ExecutorProperty _TypedRefCS__LambdaTypeCS__ownedParameterTypes = new ExecutorPropertyWithImplementation("LambdaTypeCS", Types._TypedRefCS, 2, new EcoreLibraryOppositeProperty(BaseCSPackage.Literals.LAMBDA_TYPE_CS__OWNED_PARAMETER_TYPES));
		public static final @NonNull ExecutorProperty _TypedRefCS__LambdaTypeCS__ownedResultType = new ExecutorPropertyWithImplementation("LambdaTypeCS", Types._TypedRefCS, 3, new EcoreLibraryOppositeProperty(BaseCSPackage.Literals.LAMBDA_TYPE_CS__OWNED_RESULT_TYPE));
		public static final @NonNull ExecutorProperty _TypedRefCS__OperationCS__ownedExceptions = new ExecutorPropertyWithImplementation("OperationCS", Types._TypedRefCS, 4, new EcoreLibraryOppositeProperty(BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS));
		public static final @NonNull ExecutorProperty _TypedRefCS__StructuredClassCS__ownedMetaclass = new ExecutorPropertyWithImplementation("StructuredClassCS", Types._TypedRefCS, 5, new EcoreLibraryOppositeProperty(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_METACLASS));
		public static final @NonNull ExecutorProperty _TypedRefCS__StructuredClassCS__ownedSuperTypes = new ExecutorPropertyWithImplementation("StructuredClassCS", Types._TypedRefCS, 6, new EcoreLibraryOppositeProperty(BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_SUPER_TYPES));
		public static final @NonNull ExecutorProperty _TypedRefCS__TypeParameterCS__ownedExtends = new ExecutorPropertyWithImplementation("TypeParameterCS", Types._TypedRefCS, 7, new EcoreLibraryOppositeProperty(BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS));
		public static final @NonNull ExecutorProperty _TypedRefCS__TypedElementCS__ownedType = new ExecutorPropertyWithImplementation("TypedElementCS", Types._TypedRefCS, 8, new EcoreLibraryOppositeProperty(BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE));
		public static final @NonNull ExecutorProperty _TypedRefCS__WildcardTypeRefCS__ownedExtends = new ExecutorPropertyWithImplementation("WildcardTypeRefCS", Types._TypedRefCS, 9, new EcoreLibraryOppositeProperty(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS));
		public static final @NonNull ExecutorProperty _TypedRefCS__WildcardTypeRefCS__ownedSuper = new ExecutorPropertyWithImplementation("WildcardTypeRefCS", Types._TypedRefCS, 10, new EcoreLibraryOppositeProperty(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_SUPER));

		public static final @NonNull ExecutorProperty _TypedTypeRefCS__isTypeof = new EcoreExecutorProperty(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__IS_TYPEOF, Types._TypedTypeRefCS, 0);
		public static final @NonNull ExecutorProperty _TypedTypeRefCS__ownedBinding = new EcoreExecutorProperty(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING, Types._TypedTypeRefCS, 1);
		public static final @NonNull ExecutorProperty _TypedTypeRefCS__ownedPathName = new EcoreExecutorProperty(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME, Types._TypedTypeRefCS, 2);
		public static final @NonNull ExecutorProperty _TypedTypeRefCS__referredType = new EcoreExecutorProperty(BaseCSPackage.Literals.TYPED_TYPE_REF_CS__REFERRED_TYPE, Types._TypedTypeRefCS, 3);

		public static final @NonNull ExecutorProperty _WildcardTypeRefCS__ownedExtends = new EcoreExecutorProperty(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS, Types._WildcardTypeRefCS, 0);
		public static final @NonNull ExecutorProperty _WildcardTypeRefCS__ownedSuper = new EcoreExecutorProperty(BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_SUPER, Types._WildcardTypeRefCS, 1);
		static {
			Init.initEnd();
		}

		/**
		 * Force initialization of the fields of BaseCSTables::Properties and all preceding sub-packages.
		 */
		public static void init() {}
	}

	/**
	 *	The fragments for all base types in depth order: OclAny first, OclSelf last.
	 */
	public static class TypeFragments {
		static {
			Init.initStart();
			Properties.init();
		}

		private static final @NonNull ExecutorFragment @NonNull [] _AnnotationCS =
		{
			Fragments._AnnotationCS__OclAny /* 0 */,
			Fragments._AnnotationCS__OclElement /* 1 */,
			Fragments._AnnotationCS__Nameable /* 2 */,
			Fragments._AnnotationCS__Pivotable /* 2 */,
			Fragments._AnnotationCS__VisitableCS /* 2 */,
			Fragments._AnnotationCS__ElementCS /* 3 */,
			Fragments._AnnotationCS__PivotableElementCS /* 4 */,
			Fragments._AnnotationCS__ModelElementCS /* 5 */,
			Fragments._AnnotationCS__NamedElementCS /* 6 */,
			Fragments._AnnotationCS__AnnotationElementCS /* 7 */,
			Fragments._AnnotationCS__AnnotationCS /* 8 */
		};
		private static final int @NonNull [] __AnnotationCS = { 1,1,3,1,1,1,1,1,1 };

		private static final @NonNull ExecutorFragment @NonNull [] _AnnotationElementCS =
		{
			Fragments._AnnotationElementCS__OclAny /* 0 */,
			Fragments._AnnotationElementCS__OclElement /* 1 */,
			Fragments._AnnotationElementCS__Nameable /* 2 */,
			Fragments._AnnotationElementCS__Pivotable /* 2 */,
			Fragments._AnnotationElementCS__VisitableCS /* 2 */,
			Fragments._AnnotationElementCS__ElementCS /* 3 */,
			Fragments._AnnotationElementCS__PivotableElementCS /* 4 */,
			Fragments._AnnotationElementCS__ModelElementCS /* 5 */,
			Fragments._AnnotationElementCS__NamedElementCS /* 6 */,
			Fragments._AnnotationElementCS__AnnotationElementCS /* 7 */
		};
		private static final int @NonNull [] __AnnotationElementCS = { 1,1,3,1,1,1,1,1 };

		private static final @NonNull ExecutorFragment @NonNull [] _AttributeCS =
		{
			Fragments._AttributeCS__OclAny /* 0 */,
			Fragments._AttributeCS__OclElement /* 1 */,
			Fragments._AttributeCS__Nameable /* 2 */,
			Fragments._AttributeCS__Pivotable /* 2 */,
			Fragments._AttributeCS__VisitableCS /* 2 */,
			Fragments._AttributeCS__ElementCS /* 3 */,
			Fragments._AttributeCS__PivotableElementCS /* 4 */,
			Fragments._AttributeCS__ModelElementCS /* 5 */,
			Fragments._AttributeCS__NamedElementCS /* 6 */,
			Fragments._AttributeCS__TypedElementCS /* 7 */,
			Fragments._AttributeCS__FeatureCS /* 8 */,
			Fragments._AttributeCS__StructuralFeatureCS /* 9 */,
			Fragments._AttributeCS__AttributeCS /* 10 */
		};
		private static final int @NonNull [] __AttributeCS = { 1,1,3,1,1,1,1,1,1,1,1 };

		private static final @NonNull ExecutorFragment @NonNull [] _BigNumber =
		{
			Fragments._BigNumber__OclAny /* 0 */,
			Fragments._BigNumber__BigNumber /* 1 */
		};
		private static final int @NonNull [] __BigNumber = { 1,1 };

		private static final @NonNull ExecutorFragment @NonNull [] _CSI =
		{
			Fragments._CSI__OclAny /* 0 */,
			Fragments._CSI__CSI /* 1 */
		};
		private static final int @NonNull [] __CSI = { 1,1 };

		private static final @NonNull ExecutorFragment @NonNull [] _ClassCS =
		{
			Fragments._ClassCS__OclAny /* 0 */,
			Fragments._ClassCS__OclElement /* 1 */,
			Fragments._ClassCS__Nameable /* 2 */,
			Fragments._ClassCS__Pivotable /* 2 */,
			Fragments._ClassCS__VisitableCS /* 2 */,
			Fragments._ClassCS__ElementCS /* 3 */,
			Fragments._ClassCS__PivotableElementCS /* 4 */,
			Fragments._ClassCS__TemplateableElementCS /* 4 */,
			Fragments._ClassCS__ModelElementCS /* 5 */,
			Fragments._ClassCS__NamedElementCS /* 6 */,
			Fragments._ClassCS__TypeCS /* 6 */,
			Fragments._ClassCS__ClassCS /* 7 */
		};
		private static final int @NonNull [] __ClassCS = { 1,1,3,1,2,1,2,1 };

		private static final @NonNull ExecutorFragment @NonNull [] _ConstraintCS =
		{
			Fragments._ConstraintCS__OclAny /* 0 */,
			Fragments._ConstraintCS__OclElement /* 1 */,
			Fragments._ConstraintCS__Nameable /* 2 */,
			Fragments._ConstraintCS__Pivotable /* 2 */,
			Fragments._ConstraintCS__VisitableCS /* 2 */,
			Fragments._ConstraintCS__ElementCS /* 3 */,
			Fragments._ConstraintCS__PivotableElementCS /* 4 */,
			Fragments._ConstraintCS__ModelElementCS /* 5 */,
			Fragments._ConstraintCS__NamedElementCS /* 6 */,
			Fragments._ConstraintCS__ConstraintCS /* 7 */
		};
		private static final int @NonNull [] __ConstraintCS = { 1,1,3,1,1,1,1,1 };

		private static final @NonNull ExecutorFragment @NonNull [] _ContextLessElementCS =
		{
			Fragments._ContextLessElementCS__OclAny /* 0 */,
			Fragments._ContextLessElementCS__OclElement /* 1 */,
			Fragments._ContextLessElementCS__VisitableCS /* 2 */,
			Fragments._ContextLessElementCS__ElementCS /* 3 */,
			Fragments._ContextLessElementCS__ContextLessElementCS /* 4 */
		};
		private static final int @NonNull [] __ContextLessElementCS = { 1,1,1,1,1 };

		private static final @NonNull ExecutorFragment @NonNull [] _DataTypeCS =
		{
			Fragments._DataTypeCS__OclAny /* 0 */,
			Fragments._DataTypeCS__OclElement /* 1 */,
			Fragments._DataTypeCS__Nameable /* 2 */,
			Fragments._DataTypeCS__Pivotable /* 2 */,
			Fragments._DataTypeCS__VisitableCS /* 2 */,
			Fragments._DataTypeCS__ElementCS /* 3 */,
			Fragments._DataTypeCS__PivotableElementCS /* 4 */,
			Fragments._DataTypeCS__TemplateableElementCS /* 4 */,
			Fragments._DataTypeCS__ModelElementCS /* 5 */,
			Fragments._DataTypeCS__NamedElementCS /* 6 */,
			Fragments._DataTypeCS__TypeCS /* 6 */,
			Fragments._DataTypeCS__ClassCS /* 7 */,
			Fragments._DataTypeCS__NamespaceCS /* 7 */,
			Fragments._DataTypeCS__DataTypeCS /* 8 */
		};
		private static final int @NonNull [] __DataTypeCS = { 1,1,3,1,2,1,2,2,1 };

		private static final @NonNull ExecutorFragment @NonNull [] _DetailCS =
		{
			Fragments._DetailCS__OclAny /* 0 */,
			Fragments._DetailCS__OclElement /* 1 */,
			Fragments._DetailCS__Nameable /* 2 */,
			Fragments._DetailCS__Pivotable /* 2 */,
			Fragments._DetailCS__VisitableCS /* 2 */,
			Fragments._DetailCS__ElementCS /* 3 */,
			Fragments._DetailCS__PivotableElementCS /* 4 */,
			Fragments._DetailCS__ModelElementCS /* 5 */,
			Fragments._DetailCS__NamedElementCS /* 6 */,
			Fragments._DetailCS__DetailCS /* 7 */
		};
		private static final int @NonNull [] __DetailCS = { 1,1,3,1,1,1,1,1 };

		private static final @NonNull ExecutorFragment @NonNull [] _DocumentationCS =
		{
			Fragments._DocumentationCS__OclAny /* 0 */,
			Fragments._DocumentationCS__OclElement /* 1 */,
			Fragments._DocumentationCS__Nameable /* 2 */,
			Fragments._DocumentationCS__Pivotable /* 2 */,
			Fragments._DocumentationCS__VisitableCS /* 2 */,
			Fragments._DocumentationCS__ElementCS /* 3 */,
			Fragments._DocumentationCS__PivotableElementCS /* 4 */,
			Fragments._DocumentationCS__ModelElementCS /* 5 */,
			Fragments._DocumentationCS__NamedElementCS /* 6 */,
			Fragments._DocumentationCS__AnnotationElementCS /* 7 */,
			Fragments._DocumentationCS__DocumentationCS /* 8 */
		};
		private static final int @NonNull [] __DocumentationCS = { 1,1,3,1,1,1,1,1,1 };

		private static final @NonNull ExecutorFragment @NonNull [] _ElementCS =
		{
			Fragments._ElementCS__OclAny /* 0 */,
			Fragments._ElementCS__OclElement /* 1 */,
			Fragments._ElementCS__VisitableCS /* 2 */,
			Fragments._ElementCS__ElementCS /* 3 */
		};
		private static final int @NonNull [] __ElementCS = { 1,1,1,1 };

		private static final @NonNull ExecutorFragment @NonNull [] _ElementRefCS =
		{
			Fragments._ElementRefCS__OclAny /* 0 */,
			Fragments._ElementRefCS__OclElement /* 1 */,
			Fragments._ElementRefCS__Pivotable /* 2 */,
			Fragments._ElementRefCS__VisitableCS /* 2 */,
			Fragments._ElementRefCS__ElementCS /* 3 */,
			Fragments._ElementRefCS__PivotableElementCS /* 4 */,
			Fragments._ElementRefCS__ElementRefCS /* 5 */
		};
		private static final int @NonNull [] __ElementRefCS = { 1,1,2,1,1,1 };

		private static final @NonNull ExecutorFragment @NonNull [] _EnumerationCS =
		{
			Fragments._EnumerationCS__OclAny /* 0 */,
			Fragments._EnumerationCS__OclElement /* 1 */,
			Fragments._EnumerationCS__Nameable /* 2 */,
			Fragments._EnumerationCS__Pivotable /* 2 */,
			Fragments._EnumerationCS__VisitableCS /* 2 */,
			Fragments._EnumerationCS__ElementCS /* 3 */,
			Fragments._EnumerationCS__PivotableElementCS /* 4 */,
			Fragments._EnumerationCS__TemplateableElementCS /* 4 */,
			Fragments._EnumerationCS__ModelElementCS /* 5 */,
			Fragments._EnumerationCS__NamedElementCS /* 6 */,
			Fragments._EnumerationCS__TypeCS /* 6 */,
			Fragments._EnumerationCS__ClassCS /* 7 */,
			Fragments._EnumerationCS__NamespaceCS /* 7 */,
			Fragments._EnumerationCS__EnumerationCS /* 8 */
		};
		private static final int @NonNull [] __EnumerationCS = { 1,1,3,1,2,1,2,2,1 };

		private static final @NonNull ExecutorFragment @NonNull [] _EnumerationLiteralCS =
		{
			Fragments._EnumerationLiteralCS__OclAny /* 0 */,
			Fragments._EnumerationLiteralCS__OclElement /* 1 */,
			Fragments._EnumerationLiteralCS__Nameable /* 2 */,
			Fragments._EnumerationLiteralCS__Pivotable /* 2 */,
			Fragments._EnumerationLiteralCS__VisitableCS /* 2 */,
			Fragments._EnumerationLiteralCS__ElementCS /* 3 */,
			Fragments._EnumerationLiteralCS__PivotableElementCS /* 4 */,
			Fragments._EnumerationLiteralCS__ModelElementCS /* 5 */,
			Fragments._EnumerationLiteralCS__NamedElementCS /* 6 */,
			Fragments._EnumerationLiteralCS__EnumerationLiteralCS /* 7 */
		};
		private static final int @NonNull [] __EnumerationLiteralCS = { 1,1,3,1,1,1,1,1 };

		private static final @NonNull ExecutorFragment @NonNull [] _FeatureCS =
		{
			Fragments._FeatureCS__OclAny /* 0 */,
			Fragments._FeatureCS__OclElement /* 1 */,
			Fragments._FeatureCS__Nameable /* 2 */,
			Fragments._FeatureCS__Pivotable /* 2 */,
			Fragments._FeatureCS__VisitableCS /* 2 */,
			Fragments._FeatureCS__ElementCS /* 3 */,
			Fragments._FeatureCS__PivotableElementCS /* 4 */,
			Fragments._FeatureCS__ModelElementCS /* 5 */,
			Fragments._FeatureCS__NamedElementCS /* 6 */,
			Fragments._FeatureCS__TypedElementCS /* 7 */,
			Fragments._FeatureCS__FeatureCS /* 8 */
		};
		private static final int @NonNull [] __FeatureCS = { 1,1,3,1,1,1,1,1,1 };

		private static final @NonNull ExecutorFragment @NonNull [] _ImportCS =
		{
			Fragments._ImportCS__OclAny /* 0 */,
			Fragments._ImportCS__OclElement /* 1 */,
			Fragments._ImportCS__Nameable /* 2 */,
			Fragments._ImportCS__Pivotable /* 2 */,
			Fragments._ImportCS__VisitableCS /* 2 */,
			Fragments._ImportCS__ElementCS /* 3 */,
			Fragments._ImportCS__PivotableElementCS /* 4 */,
			Fragments._ImportCS__ModelElementCS /* 5 */,
			Fragments._ImportCS__NamedElementCS /* 6 */,
			Fragments._ImportCS__NamespaceCS /* 7 */,
			Fragments._ImportCS__ImportCS /* 8 */
		};
		private static final int @NonNull [] __ImportCS = { 1,1,3,1,1,1,1,1,1 };

		private static final @NonNull ExecutorFragment @NonNull [] _LambdaTypeCS =
		{
			Fragments._LambdaTypeCS__OclAny /* 0 */,
			Fragments._LambdaTypeCS__OclElement /* 1 */,
			Fragments._LambdaTypeCS__Nameable /* 2 */,
			Fragments._LambdaTypeCS__Pivotable /* 2 */,
			Fragments._LambdaTypeCS__VisitableCS /* 2 */,
			Fragments._LambdaTypeCS__ElementCS /* 3 */,
			Fragments._LambdaTypeCS__PivotableElementCS /* 4 */,
			Fragments._LambdaTypeCS__TemplateableElementCS /* 4 */,
			Fragments._LambdaTypeCS__ElementRefCS /* 5 */,
			Fragments._LambdaTypeCS__TypeRefCS /* 6 */,
			Fragments._LambdaTypeCS__TypedRefCS /* 7 */,
			Fragments._LambdaTypeCS__LambdaTypeCS /* 8 */
		};
		private static final int @NonNull [] __LambdaTypeCS = { 1,1,3,1,2,1,1,1,1 };

		private static final @NonNull ExecutorFragment @NonNull [] _ModelElementCS =
		{
			Fragments._ModelElementCS__OclAny /* 0 */,
			Fragments._ModelElementCS__OclElement /* 1 */,
			Fragments._ModelElementCS__Pivotable /* 2 */,
			Fragments._ModelElementCS__VisitableCS /* 2 */,
			Fragments._ModelElementCS__ElementCS /* 3 */,
			Fragments._ModelElementCS__PivotableElementCS /* 4 */,
			Fragments._ModelElementCS__ModelElementCS /* 5 */
		};
		private static final int @NonNull [] __ModelElementCS = { 1,1,2,1,1,1 };

		private static final @NonNull ExecutorFragment @NonNull [] _ModelElementRefCS =
		{
			Fragments._ModelElementRefCS__OclAny /* 0 */,
			Fragments._ModelElementRefCS__OclElement /* 1 */,
			Fragments._ModelElementRefCS__Pivotable /* 2 */,
			Fragments._ModelElementRefCS__VisitableCS /* 2 */,
			Fragments._ModelElementRefCS__ElementCS /* 3 */,
			Fragments._ModelElementRefCS__PivotableElementCS /* 4 */,
			Fragments._ModelElementRefCS__ElementRefCS /* 5 */,
			Fragments._ModelElementRefCS__ModelElementRefCS /* 6 */
		};
		private static final int @NonNull [] __ModelElementRefCS = { 1,1,2,1,1,1,1 };

		private static final @NonNull ExecutorFragment @NonNull [] _MultiplicityBoundsCS =
		{
			Fragments._MultiplicityBoundsCS__OclAny /* 0 */,
			Fragments._MultiplicityBoundsCS__OclElement /* 1 */,
			Fragments._MultiplicityBoundsCS__VisitableCS /* 2 */,
			Fragments._MultiplicityBoundsCS__ElementCS /* 3 */,
			Fragments._MultiplicityBoundsCS__MultiplicityCS /* 4 */,
			Fragments._MultiplicityBoundsCS__MultiplicityBoundsCS /* 5 */
		};
		private static final int @NonNull [] __MultiplicityBoundsCS = { 1,1,1,1,1,1 };

		private static final @NonNull ExecutorFragment @NonNull [] _MultiplicityCS =
		{
			Fragments._MultiplicityCS__OclAny /* 0 */,
			Fragments._MultiplicityCS__OclElement /* 1 */,
			Fragments._MultiplicityCS__VisitableCS /* 2 */,
			Fragments._MultiplicityCS__ElementCS /* 3 */,
			Fragments._MultiplicityCS__MultiplicityCS /* 4 */
		};
		private static final int @NonNull [] __MultiplicityCS = { 1,1,1,1,1 };

		private static final @NonNull ExecutorFragment @NonNull [] _MultiplicityStringCS =
		{
			Fragments._MultiplicityStringCS__OclAny /* 0 */,
			Fragments._MultiplicityStringCS__OclElement /* 1 */,
			Fragments._MultiplicityStringCS__VisitableCS /* 2 */,
			Fragments._MultiplicityStringCS__ElementCS /* 3 */,
			Fragments._MultiplicityStringCS__MultiplicityCS /* 4 */,
			Fragments._MultiplicityStringCS__MultiplicityStringCS /* 5 */
		};
		private static final int @NonNull [] __MultiplicityStringCS = { 1,1,1,1,1,1 };

		private static final @NonNull ExecutorFragment @NonNull [] _NamedElementCS =
		{
			Fragments._NamedElementCS__OclAny /* 0 */,
			Fragments._NamedElementCS__OclElement /* 1 */,
			Fragments._NamedElementCS__Nameable /* 2 */,
			Fragments._NamedElementCS__Pivotable /* 2 */,
			Fragments._NamedElementCS__VisitableCS /* 2 */,
			Fragments._NamedElementCS__ElementCS /* 3 */,
			Fragments._NamedElementCS__PivotableElementCS /* 4 */,
			Fragments._NamedElementCS__ModelElementCS /* 5 */,
			Fragments._NamedElementCS__NamedElementCS /* 6 */
		};
		private static final int @NonNull [] __NamedElementCS = { 1,1,3,1,1,1,1 };

		private static final @NonNull ExecutorFragment @NonNull [] _NamespaceCS =
		{
			Fragments._NamespaceCS__OclAny /* 0 */,
			Fragments._NamespaceCS__OclElement /* 1 */,
			Fragments._NamespaceCS__Nameable /* 2 */,
			Fragments._NamespaceCS__Pivotable /* 2 */,
			Fragments._NamespaceCS__VisitableCS /* 2 */,
			Fragments._NamespaceCS__ElementCS /* 3 */,
			Fragments._NamespaceCS__PivotableElementCS /* 4 */,
			Fragments._NamespaceCS__ModelElementCS /* 5 */,
			Fragments._NamespaceCS__NamedElementCS /* 6 */,
			Fragments._NamespaceCS__NamespaceCS /* 7 */
		};
		private static final int @NonNull [] __NamespaceCS = { 1,1,3,1,1,1,1,1 };

		private static final @NonNull ExecutorFragment @NonNull [] _OperationCS =
		{
			Fragments._OperationCS__OclAny /* 0 */,
			Fragments._OperationCS__OclElement /* 1 */,
			Fragments._OperationCS__Nameable /* 2 */,
			Fragments._OperationCS__Pivotable /* 2 */,
			Fragments._OperationCS__VisitableCS /* 2 */,
			Fragments._OperationCS__ElementCS /* 3 */,
			Fragments._OperationCS__PivotableElementCS /* 4 */,
			Fragments._OperationCS__TemplateableElementCS /* 4 */,
			Fragments._OperationCS__ModelElementCS /* 5 */,
			Fragments._OperationCS__NamedElementCS /* 6 */,
			Fragments._OperationCS__TypedElementCS /* 7 */,
			Fragments._OperationCS__FeatureCS /* 8 */,
			Fragments._OperationCS__OperationCS /* 9 */
		};
		private static final int @NonNull [] __OperationCS = { 1,1,3,1,2,1,1,1,1,1 };

		private static final @NonNull ExecutorFragment @NonNull [] _PackageCS =
		{
			Fragments._PackageCS__OclAny /* 0 */,
			Fragments._PackageCS__OclElement /* 1 */,
			Fragments._PackageCS__Nameable /* 2 */,
			Fragments._PackageCS__Pivotable /* 2 */,
			Fragments._PackageCS__VisitableCS /* 2 */,
			Fragments._PackageCS__ElementCS /* 3 */,
			Fragments._PackageCS__PivotableElementCS /* 4 */,
			Fragments._PackageCS__ModelElementCS /* 5 */,
			Fragments._PackageCS__NamedElementCS /* 6 */,
			Fragments._PackageCS__PackageOwnerCS /* 6 */,
			Fragments._PackageCS__NamespaceCS /* 7 */,
			Fragments._PackageCS__PackageCS /* 8 */
		};
		private static final int @NonNull [] __PackageCS = { 1,1,3,1,1,1,2,1,1 };

		private static final @NonNull ExecutorFragment @NonNull [] _PackageOwnerCS =
		{
			Fragments._PackageOwnerCS__OclAny /* 0 */,
			Fragments._PackageOwnerCS__OclElement /* 1 */,
			Fragments._PackageOwnerCS__Pivotable /* 2 */,
			Fragments._PackageOwnerCS__VisitableCS /* 2 */,
			Fragments._PackageOwnerCS__ElementCS /* 3 */,
			Fragments._PackageOwnerCS__PivotableElementCS /* 4 */,
			Fragments._PackageOwnerCS__ModelElementCS /* 5 */,
			Fragments._PackageOwnerCS__PackageOwnerCS /* 6 */
		};
		private static final int @NonNull [] __PackageOwnerCS = { 1,1,2,1,1,1,1 };

		private static final @NonNull ExecutorFragment @NonNull [] _ParameterCS =
		{
			Fragments._ParameterCS__OclAny /* 0 */,
			Fragments._ParameterCS__OclElement /* 1 */,
			Fragments._ParameterCS__Nameable /* 2 */,
			Fragments._ParameterCS__Pivotable /* 2 */,
			Fragments._ParameterCS__VisitableCS /* 2 */,
			Fragments._ParameterCS__ElementCS /* 3 */,
			Fragments._ParameterCS__PivotableElementCS /* 4 */,
			Fragments._ParameterCS__ModelElementCS /* 5 */,
			Fragments._ParameterCS__NamedElementCS /* 6 */,
			Fragments._ParameterCS__TypedElementCS /* 7 */,
			Fragments._ParameterCS__ParameterCS /* 8 */
		};
		private static final int @NonNull [] __ParameterCS = { 1,1,3,1,1,1,1,1,1 };

		private static final @NonNull ExecutorFragment @NonNull [] _PathElementCS =
		{
			Fragments._PathElementCS__OclAny /* 0 */,
			Fragments._PathElementCS__OclElement /* 1 */,
			Fragments._PathElementCS__Pivotable /* 2 */,
			Fragments._PathElementCS__VisitableCS /* 2 */,
			Fragments._PathElementCS__ElementCS /* 3 */,
			Fragments._PathElementCS__PathElementCS /* 4 */
		};
		private static final int @NonNull [] __PathElementCS = { 1,1,2,1,1 };

		private static final @NonNull ExecutorFragment @NonNull [] _PathElementWithURICS =
		{
			Fragments._PathElementWithURICS__OclAny /* 0 */,
			Fragments._PathElementWithURICS__OclElement /* 1 */,
			Fragments._PathElementWithURICS__Pivotable /* 2 */,
			Fragments._PathElementWithURICS__VisitableCS /* 2 */,
			Fragments._PathElementWithURICS__ElementCS /* 3 */,
			Fragments._PathElementWithURICS__PathElementCS /* 4 */,
			Fragments._PathElementWithURICS__PathElementWithURICS /* 5 */
		};
		private static final int @NonNull [] __PathElementWithURICS = { 1,1,2,1,1,1 };

		private static final @NonNull ExecutorFragment @NonNull [] _PathNameCS =
		{
			Fragments._PathNameCS__OclAny /* 0 */,
			Fragments._PathNameCS__OclElement /* 1 */,
			Fragments._PathNameCS__Pivotable /* 2 */,
			Fragments._PathNameCS__VisitableCS /* 2 */,
			Fragments._PathNameCS__ElementCS /* 3 */,
			Fragments._PathNameCS__PathNameCS /* 4 */
		};
		private static final int @NonNull [] __PathNameCS = { 1,1,2,1,1 };

		private static final @NonNull ExecutorFragment @NonNull [] _PivotableElementCS =
		{
			Fragments._PivotableElementCS__OclAny /* 0 */,
			Fragments._PivotableElementCS__OclElement /* 1 */,
			Fragments._PivotableElementCS__Pivotable /* 2 */,
			Fragments._PivotableElementCS__VisitableCS /* 2 */,
			Fragments._PivotableElementCS__ElementCS /* 3 */,
			Fragments._PivotableElementCS__PivotableElementCS /* 4 */
		};
		private static final int @NonNull [] __PivotableElementCS = { 1,1,2,1,1 };

		private static final @NonNull ExecutorFragment @NonNull [] _PrimitiveTypeRefCS =
		{
			Fragments._PrimitiveTypeRefCS__OclAny /* 0 */,
			Fragments._PrimitiveTypeRefCS__OclElement /* 1 */,
			Fragments._PrimitiveTypeRefCS__Nameable /* 2 */,
			Fragments._PrimitiveTypeRefCS__Pivotable /* 2 */,
			Fragments._PrimitiveTypeRefCS__VisitableCS /* 2 */,
			Fragments._PrimitiveTypeRefCS__ElementCS /* 3 */,
			Fragments._PrimitiveTypeRefCS__PivotableElementCS /* 4 */,
			Fragments._PrimitiveTypeRefCS__ElementRefCS /* 5 */,
			Fragments._PrimitiveTypeRefCS__TypeRefCS /* 6 */,
			Fragments._PrimitiveTypeRefCS__TypedRefCS /* 7 */,
			Fragments._PrimitiveTypeRefCS__PrimitiveTypeRefCS /* 8 */
		};
		private static final int @NonNull [] __PrimitiveTypeRefCS = { 1,1,3,1,1,1,1,1,1 };

		private static final @NonNull ExecutorFragment @NonNull [] _ReferenceCS =
		{
			Fragments._ReferenceCS__OclAny /* 0 */,
			Fragments._ReferenceCS__OclElement /* 1 */,
			Fragments._ReferenceCS__Nameable /* 2 */,
			Fragments._ReferenceCS__Pivotable /* 2 */,
			Fragments._ReferenceCS__VisitableCS /* 2 */,
			Fragments._ReferenceCS__ElementCS /* 3 */,
			Fragments._ReferenceCS__PivotableElementCS /* 4 */,
			Fragments._ReferenceCS__ModelElementCS /* 5 */,
			Fragments._ReferenceCS__NamedElementCS /* 6 */,
			Fragments._ReferenceCS__TypedElementCS /* 7 */,
			Fragments._ReferenceCS__FeatureCS /* 8 */,
			Fragments._ReferenceCS__StructuralFeatureCS /* 9 */,
			Fragments._ReferenceCS__ReferenceCS /* 10 */
		};
		private static final int @NonNull [] __ReferenceCS = { 1,1,3,1,1,1,1,1,1,1,1 };

		private static final @NonNull ExecutorFragment @NonNull [] _RootCS =
		{
			Fragments._RootCS__OclAny /* 0 */,
			Fragments._RootCS__OclElement /* 1 */,
			Fragments._RootCS__Pivotable /* 2 */,
			Fragments._RootCS__VisitableCS /* 2 */,
			Fragments._RootCS__ElementCS /* 3 */,
			Fragments._RootCS__PivotableElementCS /* 4 */,
			Fragments._RootCS__ModelElementCS /* 5 */,
			Fragments._RootCS__RootCS /* 6 */
		};
		private static final int @NonNull [] __RootCS = { 1,1,2,1,1,1,1 };

		private static final @NonNull ExecutorFragment @NonNull [] _RootPackageCS =
		{
			Fragments._RootPackageCS__OclAny /* 0 */,
			Fragments._RootPackageCS__OclElement /* 1 */,
			Fragments._RootPackageCS__Pivotable /* 2 */,
			Fragments._RootPackageCS__VisitableCS /* 2 */,
			Fragments._RootPackageCS__ElementCS /* 3 */,
			Fragments._RootPackageCS__PivotableElementCS /* 4 */,
			Fragments._RootPackageCS__ModelElementCS /* 5 */,
			Fragments._RootPackageCS__PackageOwnerCS /* 6 */,
			Fragments._RootPackageCS__RootCS /* 6 */,
			Fragments._RootPackageCS__RootPackageCS /* 7 */
		};
		private static final int @NonNull [] __RootPackageCS = { 1,1,2,1,1,1,2,1 };

		private static final @NonNull ExecutorFragment @NonNull [] _ScopeFilter =
		{
			Fragments._ScopeFilter__OclAny /* 0 */,
			Fragments._ScopeFilter__ScopeFilter /* 1 */
		};
		private static final int @NonNull [] __ScopeFilter = { 1,1 };

		private static final @NonNull ExecutorFragment @NonNull [] _SpecificationCS =
		{
			Fragments._SpecificationCS__OclAny /* 0 */,
			Fragments._SpecificationCS__OclElement /* 1 */,
			Fragments._SpecificationCS__Pivotable /* 2 */,
			Fragments._SpecificationCS__VisitableCS /* 2 */,
			Fragments._SpecificationCS__ElementCS /* 3 */,
			Fragments._SpecificationCS__PivotableElementCS /* 4 */,
			Fragments._SpecificationCS__ModelElementCS /* 5 */,
			Fragments._SpecificationCS__SpecificationCS /* 6 */
		};
		private static final int @NonNull [] __SpecificationCS = { 1,1,2,1,1,1,1 };

		private static final @NonNull ExecutorFragment @NonNull [] _StructuralFeatureCS =
		{
			Fragments._StructuralFeatureCS__OclAny /* 0 */,
			Fragments._StructuralFeatureCS__OclElement /* 1 */,
			Fragments._StructuralFeatureCS__Nameable /* 2 */,
			Fragments._StructuralFeatureCS__Pivotable /* 2 */,
			Fragments._StructuralFeatureCS__VisitableCS /* 2 */,
			Fragments._StructuralFeatureCS__ElementCS /* 3 */,
			Fragments._StructuralFeatureCS__PivotableElementCS /* 4 */,
			Fragments._StructuralFeatureCS__ModelElementCS /* 5 */,
			Fragments._StructuralFeatureCS__NamedElementCS /* 6 */,
			Fragments._StructuralFeatureCS__TypedElementCS /* 7 */,
			Fragments._StructuralFeatureCS__FeatureCS /* 8 */,
			Fragments._StructuralFeatureCS__StructuralFeatureCS /* 9 */
		};
		private static final int @NonNull [] __StructuralFeatureCS = { 1,1,3,1,1,1,1,1,1,1 };

		private static final @NonNull ExecutorFragment @NonNull [] _StructuredClassCS =
		{
			Fragments._StructuredClassCS__OclAny /* 0 */,
			Fragments._StructuredClassCS__OclElement /* 1 */,
			Fragments._StructuredClassCS__Nameable /* 2 */,
			Fragments._StructuredClassCS__Pivotable /* 2 */,
			Fragments._StructuredClassCS__VisitableCS /* 2 */,
			Fragments._StructuredClassCS__ElementCS /* 3 */,
			Fragments._StructuredClassCS__PivotableElementCS /* 4 */,
			Fragments._StructuredClassCS__TemplateableElementCS /* 4 */,
			Fragments._StructuredClassCS__ModelElementCS /* 5 */,
			Fragments._StructuredClassCS__NamedElementCS /* 6 */,
			Fragments._StructuredClassCS__TypeCS /* 6 */,
			Fragments._StructuredClassCS__ClassCS /* 7 */,
			Fragments._StructuredClassCS__NamespaceCS /* 7 */,
			Fragments._StructuredClassCS__StructuredClassCS /* 8 */
		};
		private static final int @NonNull [] __StructuredClassCS = { 1,1,3,1,2,1,2,2,1 };

		private static final @NonNull ExecutorFragment @NonNull [] _TemplateBindingCS =
		{
			Fragments._TemplateBindingCS__OclAny /* 0 */,
			Fragments._TemplateBindingCS__OclElement /* 1 */,
			Fragments._TemplateBindingCS__Pivotable /* 2 */,
			Fragments._TemplateBindingCS__VisitableCS /* 2 */,
			Fragments._TemplateBindingCS__ElementCS /* 3 */,
			Fragments._TemplateBindingCS__PivotableElementCS /* 4 */,
			Fragments._TemplateBindingCS__ElementRefCS /* 5 */,
			Fragments._TemplateBindingCS__TemplateBindingCS /* 6 */
		};
		private static final int @NonNull [] __TemplateBindingCS = { 1,1,2,1,1,1,1 };

		private static final @NonNull ExecutorFragment @NonNull [] _TemplateParameterCS =
		{
			Fragments._TemplateParameterCS__OclAny /* 0 */,
			Fragments._TemplateParameterCS__OclElement /* 1 */,
			Fragments._TemplateParameterCS__Nameable /* 2 */,
			Fragments._TemplateParameterCS__Pivotable /* 2 */,
			Fragments._TemplateParameterCS__VisitableCS /* 2 */,
			Fragments._TemplateParameterCS__ElementCS /* 3 */,
			Fragments._TemplateParameterCS__PivotableElementCS /* 4 */,
			Fragments._TemplateParameterCS__ModelElementCS /* 5 */,
			Fragments._TemplateParameterCS__NamedElementCS /* 6 */,
			Fragments._TemplateParameterCS__TemplateParameterCS /* 7 */
		};
		private static final int @NonNull [] __TemplateParameterCS = { 1,1,3,1,1,1,1,1 };

		private static final @NonNull ExecutorFragment @NonNull [] _TemplateParameterSubstitutionCS =
		{
			Fragments._TemplateParameterSubstitutionCS__OclAny /* 0 */,
			Fragments._TemplateParameterSubstitutionCS__OclElement /* 1 */,
			Fragments._TemplateParameterSubstitutionCS__Pivotable /* 2 */,
			Fragments._TemplateParameterSubstitutionCS__VisitableCS /* 2 */,
			Fragments._TemplateParameterSubstitutionCS__ElementCS /* 3 */,
			Fragments._TemplateParameterSubstitutionCS__PivotableElementCS /* 4 */,
			Fragments._TemplateParameterSubstitutionCS__ModelElementCS /* 5 */,
			Fragments._TemplateParameterSubstitutionCS__TemplateParameterSubstitutionCS /* 6 */
		};
		private static final int @NonNull [] __TemplateParameterSubstitutionCS = { 1,1,2,1,1,1,1 };

		private static final @NonNull ExecutorFragment @NonNull [] _TemplateSignatureCS =
		{
			Fragments._TemplateSignatureCS__OclAny /* 0 */,
			Fragments._TemplateSignatureCS__OclElement /* 1 */,
			Fragments._TemplateSignatureCS__Pivotable /* 2 */,
			Fragments._TemplateSignatureCS__VisitableCS /* 2 */,
			Fragments._TemplateSignatureCS__ElementCS /* 3 */,
			Fragments._TemplateSignatureCS__PivotableElementCS /* 4 */,
			Fragments._TemplateSignatureCS__ModelElementCS /* 5 */,
			Fragments._TemplateSignatureCS__TemplateSignatureCS /* 6 */
		};
		private static final int @NonNull [] __TemplateSignatureCS = { 1,1,2,1,1,1,1 };

		private static final @NonNull ExecutorFragment @NonNull [] _TemplateableElementCS =
		{
			Fragments._TemplateableElementCS__OclAny /* 0 */,
			Fragments._TemplateableElementCS__OclElement /* 1 */,
			Fragments._TemplateableElementCS__VisitableCS /* 2 */,
			Fragments._TemplateableElementCS__ElementCS /* 3 */,
			Fragments._TemplateableElementCS__TemplateableElementCS /* 4 */
		};
		private static final int @NonNull [] __TemplateableElementCS = { 1,1,1,1,1 };

		private static final @NonNull ExecutorFragment @NonNull [] _TuplePartCS =
		{
			Fragments._TuplePartCS__OclAny /* 0 */,
			Fragments._TuplePartCS__OclElement /* 1 */,
			Fragments._TuplePartCS__Nameable /* 2 */,
			Fragments._TuplePartCS__Pivotable /* 2 */,
			Fragments._TuplePartCS__VisitableCS /* 2 */,
			Fragments._TuplePartCS__ElementCS /* 3 */,
			Fragments._TuplePartCS__PivotableElementCS /* 4 */,
			Fragments._TuplePartCS__ModelElementCS /* 5 */,
			Fragments._TuplePartCS__NamedElementCS /* 6 */,
			Fragments._TuplePartCS__TypedElementCS /* 7 */,
			Fragments._TuplePartCS__TuplePartCS /* 8 */
		};
		private static final int @NonNull [] __TuplePartCS = { 1,1,3,1,1,1,1,1,1 };

		private static final @NonNull ExecutorFragment @NonNull [] _TupleTypeCS =
		{
			Fragments._TupleTypeCS__OclAny /* 0 */,
			Fragments._TupleTypeCS__OclElement /* 1 */,
			Fragments._TupleTypeCS__Nameable /* 2 */,
			Fragments._TupleTypeCS__Pivotable /* 2 */,
			Fragments._TupleTypeCS__VisitableCS /* 2 */,
			Fragments._TupleTypeCS__ElementCS /* 3 */,
			Fragments._TupleTypeCS__PivotableElementCS /* 4 */,
			Fragments._TupleTypeCS__ElementRefCS /* 5 */,
			Fragments._TupleTypeCS__TypeRefCS /* 6 */,
			Fragments._TupleTypeCS__TypedRefCS /* 7 */,
			Fragments._TupleTypeCS__TupleTypeCS /* 8 */
		};
		private static final int @NonNull [] __TupleTypeCS = { 1,1,3,1,1,1,1,1,1 };

		private static final @NonNull ExecutorFragment @NonNull [] _TypeCS =
		{
			Fragments._TypeCS__OclAny /* 0 */,
			Fragments._TypeCS__OclElement /* 1 */,
			Fragments._TypeCS__Pivotable /* 2 */,
			Fragments._TypeCS__VisitableCS /* 2 */,
			Fragments._TypeCS__ElementCS /* 3 */,
			Fragments._TypeCS__PivotableElementCS /* 4 */,
			Fragments._TypeCS__ModelElementCS /* 5 */,
			Fragments._TypeCS__TypeCS /* 6 */
		};
		private static final int @NonNull [] __TypeCS = { 1,1,2,1,1,1,1 };

		private static final @NonNull ExecutorFragment @NonNull [] _TypeParameterCS =
		{
			Fragments._TypeParameterCS__OclAny /* 0 */,
			Fragments._TypeParameterCS__OclElement /* 1 */,
			Fragments._TypeParameterCS__Nameable /* 2 */,
			Fragments._TypeParameterCS__Pivotable /* 2 */,
			Fragments._TypeParameterCS__VisitableCS /* 2 */,
			Fragments._TypeParameterCS__ElementCS /* 3 */,
			Fragments._TypeParameterCS__PivotableElementCS /* 4 */,
			Fragments._TypeParameterCS__ModelElementCS /* 5 */,
			Fragments._TypeParameterCS__NamedElementCS /* 6 */,
			Fragments._TypeParameterCS__TypeCS /* 6 */,
			Fragments._TypeParameterCS__TemplateParameterCS /* 7 */,
			Fragments._TypeParameterCS__TypeParameterCS /* 8 */
		};
		private static final int @NonNull [] __TypeParameterCS = { 1,1,3,1,1,1,2,1,1 };

		private static final @NonNull ExecutorFragment @NonNull [] _TypeRefCS =
		{
			Fragments._TypeRefCS__OclAny /* 0 */,
			Fragments._TypeRefCS__OclElement /* 1 */,
			Fragments._TypeRefCS__Pivotable /* 2 */,
			Fragments._TypeRefCS__VisitableCS /* 2 */,
			Fragments._TypeRefCS__ElementCS /* 3 */,
			Fragments._TypeRefCS__PivotableElementCS /* 4 */,
			Fragments._TypeRefCS__ElementRefCS /* 5 */,
			Fragments._TypeRefCS__TypeRefCS /* 6 */
		};
		private static final int @NonNull [] __TypeRefCS = { 1,1,2,1,1,1,1 };

		private static final @NonNull ExecutorFragment @NonNull [] _TypedElementCS =
		{
			Fragments._TypedElementCS__OclAny /* 0 */,
			Fragments._TypedElementCS__OclElement /* 1 */,
			Fragments._TypedElementCS__Nameable /* 2 */,
			Fragments._TypedElementCS__Pivotable /* 2 */,
			Fragments._TypedElementCS__VisitableCS /* 2 */,
			Fragments._TypedElementCS__ElementCS /* 3 */,
			Fragments._TypedElementCS__PivotableElementCS /* 4 */,
			Fragments._TypedElementCS__ModelElementCS /* 5 */,
			Fragments._TypedElementCS__NamedElementCS /* 6 */,
			Fragments._TypedElementCS__TypedElementCS /* 7 */
		};
		private static final int @NonNull [] __TypedElementCS = { 1,1,3,1,1,1,1,1 };

		private static final @NonNull ExecutorFragment @NonNull [] _TypedRefCS =
		{
			Fragments._TypedRefCS__OclAny /* 0 */,
			Fragments._TypedRefCS__OclElement /* 1 */,
			Fragments._TypedRefCS__Pivotable /* 2 */,
			Fragments._TypedRefCS__VisitableCS /* 2 */,
			Fragments._TypedRefCS__ElementCS /* 3 */,
			Fragments._TypedRefCS__PivotableElementCS /* 4 */,
			Fragments._TypedRefCS__ElementRefCS /* 5 */,
			Fragments._TypedRefCS__TypeRefCS /* 6 */,
			Fragments._TypedRefCS__TypedRefCS /* 7 */
		};
		private static final int @NonNull [] __TypedRefCS = { 1,1,2,1,1,1,1,1 };

		private static final @NonNull ExecutorFragment @NonNull [] _TypedTypeRefCS =
		{
			Fragments._TypedTypeRefCS__OclAny /* 0 */,
			Fragments._TypedTypeRefCS__OclElement /* 1 */,
			Fragments._TypedTypeRefCS__Pivotable /* 2 */,
			Fragments._TypedTypeRefCS__VisitableCS /* 2 */,
			Fragments._TypedTypeRefCS__ElementCS /* 3 */,
			Fragments._TypedTypeRefCS__PivotableElementCS /* 4 */,
			Fragments._TypedTypeRefCS__ElementRefCS /* 5 */,
			Fragments._TypedTypeRefCS__TypeRefCS /* 6 */,
			Fragments._TypedTypeRefCS__TypedRefCS /* 7 */,
			Fragments._TypedTypeRefCS__TypedTypeRefCS /* 8 */
		};
		private static final int @NonNull [] __TypedTypeRefCS = { 1,1,2,1,1,1,1,1,1 };

		private static final @NonNull ExecutorFragment @NonNull [] _VisitableCS =
		{
			Fragments._VisitableCS__OclAny /* 0 */,
			Fragments._VisitableCS__OclElement /* 1 */,
			Fragments._VisitableCS__VisitableCS /* 2 */
		};
		private static final int @NonNull [] __VisitableCS = { 1,1,1 };

		private static final @NonNull ExecutorFragment @NonNull [] _WildcardTypeRefCS =
		{
			Fragments._WildcardTypeRefCS__OclAny /* 0 */,
			Fragments._WildcardTypeRefCS__OclElement /* 1 */,
			Fragments._WildcardTypeRefCS__Pivotable /* 2 */,
			Fragments._WildcardTypeRefCS__VisitableCS /* 2 */,
			Fragments._WildcardTypeRefCS__ElementCS /* 3 */,
			Fragments._WildcardTypeRefCS__PivotableElementCS /* 4 */,
			Fragments._WildcardTypeRefCS__ElementRefCS /* 5 */,
			Fragments._WildcardTypeRefCS__TypeRefCS /* 6 */,
			Fragments._WildcardTypeRefCS__WildcardTypeRefCS /* 7 */
		};
		private static final int @NonNull [] __WildcardTypeRefCS = { 1,1,2,1,1,1,1,1 };

		/**
		 *	Install the fragment descriptors in the class descriptors.
		 */
		static {
			Types._AnnotationCS.initFragments(_AnnotationCS, __AnnotationCS);
			Types._AnnotationElementCS.initFragments(_AnnotationElementCS, __AnnotationElementCS);
			Types._AttributeCS.initFragments(_AttributeCS, __AttributeCS);
			Types._BigNumber.initFragments(_BigNumber, __BigNumber);
			Types._CSI.initFragments(_CSI, __CSI);
			Types._ClassCS.initFragments(_ClassCS, __ClassCS);
			Types._ConstraintCS.initFragments(_ConstraintCS, __ConstraintCS);
			Types._ContextLessElementCS.initFragments(_ContextLessElementCS, __ContextLessElementCS);
			Types._DataTypeCS.initFragments(_DataTypeCS, __DataTypeCS);
			Types._DetailCS.initFragments(_DetailCS, __DetailCS);
			Types._DocumentationCS.initFragments(_DocumentationCS, __DocumentationCS);
			Types._ElementCS.initFragments(_ElementCS, __ElementCS);
			Types._ElementRefCS.initFragments(_ElementRefCS, __ElementRefCS);
			Types._EnumerationCS.initFragments(_EnumerationCS, __EnumerationCS);
			Types._EnumerationLiteralCS.initFragments(_EnumerationLiteralCS, __EnumerationLiteralCS);
			Types._FeatureCS.initFragments(_FeatureCS, __FeatureCS);
			Types._ImportCS.initFragments(_ImportCS, __ImportCS);
			Types._LambdaTypeCS.initFragments(_LambdaTypeCS, __LambdaTypeCS);
			Types._ModelElementCS.initFragments(_ModelElementCS, __ModelElementCS);
			Types._ModelElementRefCS.initFragments(_ModelElementRefCS, __ModelElementRefCS);
			Types._MultiplicityBoundsCS.initFragments(_MultiplicityBoundsCS, __MultiplicityBoundsCS);
			Types._MultiplicityCS.initFragments(_MultiplicityCS, __MultiplicityCS);
			Types._MultiplicityStringCS.initFragments(_MultiplicityStringCS, __MultiplicityStringCS);
			Types._NamedElementCS.initFragments(_NamedElementCS, __NamedElementCS);
			Types._NamespaceCS.initFragments(_NamespaceCS, __NamespaceCS);
			Types._OperationCS.initFragments(_OperationCS, __OperationCS);
			Types._PackageCS.initFragments(_PackageCS, __PackageCS);
			Types._PackageOwnerCS.initFragments(_PackageOwnerCS, __PackageOwnerCS);
			Types._ParameterCS.initFragments(_ParameterCS, __ParameterCS);
			Types._PathElementCS.initFragments(_PathElementCS, __PathElementCS);
			Types._PathElementWithURICS.initFragments(_PathElementWithURICS, __PathElementWithURICS);
			Types._PathNameCS.initFragments(_PathNameCS, __PathNameCS);
			Types._PivotableElementCS.initFragments(_PivotableElementCS, __PivotableElementCS);
			Types._PrimitiveTypeRefCS.initFragments(_PrimitiveTypeRefCS, __PrimitiveTypeRefCS);
			Types._ReferenceCS.initFragments(_ReferenceCS, __ReferenceCS);
			Types._RootCS.initFragments(_RootCS, __RootCS);
			Types._RootPackageCS.initFragments(_RootPackageCS, __RootPackageCS);
			Types._ScopeFilter.initFragments(_ScopeFilter, __ScopeFilter);
			Types._SpecificationCS.initFragments(_SpecificationCS, __SpecificationCS);
			Types._StructuralFeatureCS.initFragments(_StructuralFeatureCS, __StructuralFeatureCS);
			Types._StructuredClassCS.initFragments(_StructuredClassCS, __StructuredClassCS);
			Types._TemplateBindingCS.initFragments(_TemplateBindingCS, __TemplateBindingCS);
			Types._TemplateParameterCS.initFragments(_TemplateParameterCS, __TemplateParameterCS);
			Types._TemplateParameterSubstitutionCS.initFragments(_TemplateParameterSubstitutionCS, __TemplateParameterSubstitutionCS);
			Types._TemplateSignatureCS.initFragments(_TemplateSignatureCS, __TemplateSignatureCS);
			Types._TemplateableElementCS.initFragments(_TemplateableElementCS, __TemplateableElementCS);
			Types._TuplePartCS.initFragments(_TuplePartCS, __TuplePartCS);
			Types._TupleTypeCS.initFragments(_TupleTypeCS, __TupleTypeCS);
			Types._TypeCS.initFragments(_TypeCS, __TypeCS);
			Types._TypeParameterCS.initFragments(_TypeParameterCS, __TypeParameterCS);
			Types._TypeRefCS.initFragments(_TypeRefCS, __TypeRefCS);
			Types._TypedElementCS.initFragments(_TypedElementCS, __TypedElementCS);
			Types._TypedRefCS.initFragments(_TypedRefCS, __TypedRefCS);
			Types._TypedTypeRefCS.initFragments(_TypedTypeRefCS, __TypedTypeRefCS);
			Types._VisitableCS.initFragments(_VisitableCS, __VisitableCS);
			Types._WildcardTypeRefCS.initFragments(_WildcardTypeRefCS, __WildcardTypeRefCS);

			Init.initEnd();
		}

		/**
		 * Force initialization of the fields of BaseCSTables::TypeFragments and all preceding sub-packages.
		 */
		public static void init() {}
	}

	/**
	 *	The lists of local operations or local operation overrides for each fragment of each type.
	 */
	public static class FragmentOperations {
		static {
			Init.initStart();
			TypeFragments.init();
		}

		private static final @NonNull ExecutorOperation @NonNull [] _AnnotationCS__AnnotationCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _AnnotationCS__AnnotationElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _AnnotationCS__ElementCS = {
			BaseCSTables.Operations._ElementCS__getDescription /* getDescription() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _AnnotationCS__ModelElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _AnnotationCS__Nameable = {};
		private static final @NonNull ExecutorOperation @NonNull [] _AnnotationCS__NamedElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _AnnotationCS__OclAny = {
			OCLstdlibTables.Operations._OclAny___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclAny__oclAsType /* oclAsType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclAny__oclIsKindOf /* oclIsKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclAny__oclIsTypeOf /* oclIsTypeOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[?]) */,
			OCLstdlibTables.Operations._OclAny__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _AnnotationCS__OclElement = {
			OCLstdlibTables.Operations._OclElement__allInstances /* allInstances() */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _AnnotationCS__Pivotable = {};
		private static final @NonNull ExecutorOperation @NonNull [] _AnnotationCS__PivotableElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _AnnotationCS__VisitableCS = {};

		private static final @NonNull ExecutorOperation @NonNull [] _AnnotationElementCS__AnnotationElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _AnnotationElementCS__ElementCS = {
			BaseCSTables.Operations._ElementCS__getDescription /* getDescription() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _AnnotationElementCS__ModelElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _AnnotationElementCS__Nameable = {};
		private static final @NonNull ExecutorOperation @NonNull [] _AnnotationElementCS__NamedElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _AnnotationElementCS__OclAny = {
			OCLstdlibTables.Operations._OclAny___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclAny__oclAsType /* oclAsType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclAny__oclIsKindOf /* oclIsKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclAny__oclIsTypeOf /* oclIsTypeOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[?]) */,
			OCLstdlibTables.Operations._OclAny__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _AnnotationElementCS__OclElement = {
			OCLstdlibTables.Operations._OclElement__allInstances /* allInstances() */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _AnnotationElementCS__Pivotable = {};
		private static final @NonNull ExecutorOperation @NonNull [] _AnnotationElementCS__PivotableElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _AnnotationElementCS__VisitableCS = {};

		private static final @NonNull ExecutorOperation @NonNull [] _AttributeCS__AttributeCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _AttributeCS__ElementCS = {
			BaseCSTables.Operations._ElementCS__getDescription /* getDescription() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _AttributeCS__FeatureCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _AttributeCS__ModelElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _AttributeCS__Nameable = {};
		private static final @NonNull ExecutorOperation @NonNull [] _AttributeCS__NamedElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _AttributeCS__OclAny = {
			OCLstdlibTables.Operations._OclAny___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclAny__oclAsType /* oclAsType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclAny__oclIsKindOf /* oclIsKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclAny__oclIsTypeOf /* oclIsTypeOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[?]) */,
			OCLstdlibTables.Operations._OclAny__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _AttributeCS__OclElement = {
			OCLstdlibTables.Operations._OclElement__allInstances /* allInstances() */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _AttributeCS__Pivotable = {};
		private static final @NonNull ExecutorOperation @NonNull [] _AttributeCS__PivotableElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _AttributeCS__StructuralFeatureCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _AttributeCS__TypedElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _AttributeCS__VisitableCS = {};

		private static final @NonNull ExecutorOperation @NonNull [] _BigNumber__BigNumber = {};
		private static final @NonNull ExecutorOperation @NonNull [] _BigNumber__OclAny = {
			OCLstdlibTables.Operations._OclAny___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclAny__oclAsType /* oclAsType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclAny__oclIsKindOf /* oclIsKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclAny__oclIsTypeOf /* oclIsTypeOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[?]) */,
			OCLstdlibTables.Operations._OclAny__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};

		private static final @NonNull ExecutorOperation @NonNull [] _CSI__CSI = {};
		private static final @NonNull ExecutorOperation @NonNull [] _CSI__OclAny = {
			OCLstdlibTables.Operations._OclAny___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclAny__oclAsType /* oclAsType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclAny__oclIsKindOf /* oclIsKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclAny__oclIsTypeOf /* oclIsTypeOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[?]) */,
			OCLstdlibTables.Operations._OclAny__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};

		private static final @NonNull ExecutorOperation @NonNull [] _ClassCS__ClassCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _ClassCS__ElementCS = {
			BaseCSTables.Operations._ElementCS__getDescription /* getDescription() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _ClassCS__ModelElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _ClassCS__Nameable = {};
		private static final @NonNull ExecutorOperation @NonNull [] _ClassCS__NamedElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _ClassCS__OclAny = {
			OCLstdlibTables.Operations._OclAny___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclAny__oclAsType /* oclAsType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclAny__oclIsKindOf /* oclIsKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclAny__oclIsTypeOf /* oclIsTypeOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[?]) */,
			OCLstdlibTables.Operations._OclAny__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _ClassCS__OclElement = {
			OCLstdlibTables.Operations._OclElement__allInstances /* allInstances() */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _ClassCS__Pivotable = {};
		private static final @NonNull ExecutorOperation @NonNull [] _ClassCS__PivotableElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _ClassCS__TemplateableElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _ClassCS__TypeCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _ClassCS__VisitableCS = {};

		private static final @NonNull ExecutorOperation @NonNull [] _ConstraintCS__ConstraintCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _ConstraintCS__ElementCS = {
			BaseCSTables.Operations._ElementCS__getDescription /* getDescription() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _ConstraintCS__ModelElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _ConstraintCS__Nameable = {};
		private static final @NonNull ExecutorOperation @NonNull [] _ConstraintCS__NamedElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _ConstraintCS__OclAny = {
			OCLstdlibTables.Operations._OclAny___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclAny__oclAsType /* oclAsType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclAny__oclIsKindOf /* oclIsKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclAny__oclIsTypeOf /* oclIsTypeOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[?]) */,
			OCLstdlibTables.Operations._OclAny__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _ConstraintCS__OclElement = {
			OCLstdlibTables.Operations._OclElement__allInstances /* allInstances() */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _ConstraintCS__Pivotable = {};
		private static final @NonNull ExecutorOperation @NonNull [] _ConstraintCS__PivotableElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _ConstraintCS__VisitableCS = {};

		private static final @NonNull ExecutorOperation @NonNull [] _ContextLessElementCS__ContextLessElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _ContextLessElementCS__ElementCS = {
			BaseCSTables.Operations._ElementCS__getDescription /* getDescription() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _ContextLessElementCS__OclAny = {
			OCLstdlibTables.Operations._OclAny___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclAny__oclAsType /* oclAsType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclAny__oclIsKindOf /* oclIsKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclAny__oclIsTypeOf /* oclIsTypeOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[?]) */,
			OCLstdlibTables.Operations._OclAny__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _ContextLessElementCS__OclElement = {
			OCLstdlibTables.Operations._OclElement__allInstances /* allInstances() */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _ContextLessElementCS__VisitableCS = {};

		private static final @NonNull ExecutorOperation @NonNull [] _DataTypeCS__DataTypeCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _DataTypeCS__ClassCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _DataTypeCS__ElementCS = {
			BaseCSTables.Operations._ElementCS__getDescription /* getDescription() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _DataTypeCS__ModelElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _DataTypeCS__Nameable = {};
		private static final @NonNull ExecutorOperation @NonNull [] _DataTypeCS__NamedElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _DataTypeCS__NamespaceCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _DataTypeCS__OclAny = {
			OCLstdlibTables.Operations._OclAny___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclAny__oclAsType /* oclAsType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclAny__oclIsKindOf /* oclIsKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclAny__oclIsTypeOf /* oclIsTypeOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[?]) */,
			OCLstdlibTables.Operations._OclAny__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _DataTypeCS__OclElement = {
			OCLstdlibTables.Operations._OclElement__allInstances /* allInstances() */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _DataTypeCS__Pivotable = {};
		private static final @NonNull ExecutorOperation @NonNull [] _DataTypeCS__PivotableElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _DataTypeCS__TemplateableElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _DataTypeCS__TypeCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _DataTypeCS__VisitableCS = {};

		private static final @NonNull ExecutorOperation @NonNull [] _DetailCS__DetailCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _DetailCS__ElementCS = {
			BaseCSTables.Operations._ElementCS__getDescription /* getDescription() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _DetailCS__ModelElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _DetailCS__Nameable = {};
		private static final @NonNull ExecutorOperation @NonNull [] _DetailCS__NamedElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _DetailCS__OclAny = {
			OCLstdlibTables.Operations._OclAny___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclAny__oclAsType /* oclAsType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclAny__oclIsKindOf /* oclIsKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclAny__oclIsTypeOf /* oclIsTypeOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[?]) */,
			OCLstdlibTables.Operations._OclAny__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _DetailCS__OclElement = {
			OCLstdlibTables.Operations._OclElement__allInstances /* allInstances() */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _DetailCS__Pivotable = {};
		private static final @NonNull ExecutorOperation @NonNull [] _DetailCS__PivotableElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _DetailCS__VisitableCS = {};

		private static final @NonNull ExecutorOperation @NonNull [] _DocumentationCS__DocumentationCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _DocumentationCS__AnnotationElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _DocumentationCS__ElementCS = {
			BaseCSTables.Operations._ElementCS__getDescription /* getDescription() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _DocumentationCS__ModelElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _DocumentationCS__Nameable = {};
		private static final @NonNull ExecutorOperation @NonNull [] _DocumentationCS__NamedElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _DocumentationCS__OclAny = {
			OCLstdlibTables.Operations._OclAny___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclAny__oclAsType /* oclAsType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclAny__oclIsKindOf /* oclIsKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclAny__oclIsTypeOf /* oclIsTypeOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[?]) */,
			OCLstdlibTables.Operations._OclAny__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _DocumentationCS__OclElement = {
			OCLstdlibTables.Operations._OclElement__allInstances /* allInstances() */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _DocumentationCS__Pivotable = {};
		private static final @NonNull ExecutorOperation @NonNull [] _DocumentationCS__PivotableElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _DocumentationCS__VisitableCS = {};

		private static final @NonNull ExecutorOperation @NonNull [] _ElementCS__ElementCS = {
			BaseCSTables.Operations._ElementCS__getDescription /* getDescription() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _ElementCS__OclAny = {
			OCLstdlibTables.Operations._OclAny___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclAny__oclAsType /* oclAsType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclAny__oclIsKindOf /* oclIsKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclAny__oclIsTypeOf /* oclIsTypeOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[?]) */,
			OCLstdlibTables.Operations._OclAny__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _ElementCS__OclElement = {
			OCLstdlibTables.Operations._OclElement__allInstances /* allInstances() */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _ElementCS__VisitableCS = {};

		private static final @NonNull ExecutorOperation @NonNull [] _ElementRefCS__ElementRefCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _ElementRefCS__ElementCS = {
			BaseCSTables.Operations._ElementCS__getDescription /* getDescription() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _ElementRefCS__OclAny = {
			OCLstdlibTables.Operations._OclAny___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclAny__oclAsType /* oclAsType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclAny__oclIsKindOf /* oclIsKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclAny__oclIsTypeOf /* oclIsTypeOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[?]) */,
			OCLstdlibTables.Operations._OclAny__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _ElementRefCS__OclElement = {
			OCLstdlibTables.Operations._OclElement__allInstances /* allInstances() */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _ElementRefCS__Pivotable = {};
		private static final @NonNull ExecutorOperation @NonNull [] _ElementRefCS__PivotableElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _ElementRefCS__VisitableCS = {};

		private static final @NonNull ExecutorOperation @NonNull [] _EnumerationCS__EnumerationCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _EnumerationCS__ClassCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _EnumerationCS__ElementCS = {
			BaseCSTables.Operations._ElementCS__getDescription /* getDescription() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _EnumerationCS__ModelElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _EnumerationCS__Nameable = {};
		private static final @NonNull ExecutorOperation @NonNull [] _EnumerationCS__NamedElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _EnumerationCS__NamespaceCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _EnumerationCS__OclAny = {
			OCLstdlibTables.Operations._OclAny___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclAny__oclAsType /* oclAsType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclAny__oclIsKindOf /* oclIsKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclAny__oclIsTypeOf /* oclIsTypeOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[?]) */,
			OCLstdlibTables.Operations._OclAny__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _EnumerationCS__OclElement = {
			OCLstdlibTables.Operations._OclElement__allInstances /* allInstances() */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _EnumerationCS__Pivotable = {};
		private static final @NonNull ExecutorOperation @NonNull [] _EnumerationCS__PivotableElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _EnumerationCS__TemplateableElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _EnumerationCS__TypeCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _EnumerationCS__VisitableCS = {};

		private static final @NonNull ExecutorOperation @NonNull [] _EnumerationLiteralCS__EnumerationLiteralCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _EnumerationLiteralCS__ElementCS = {
			BaseCSTables.Operations._ElementCS__getDescription /* getDescription() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _EnumerationLiteralCS__ModelElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _EnumerationLiteralCS__Nameable = {};
		private static final @NonNull ExecutorOperation @NonNull [] _EnumerationLiteralCS__NamedElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _EnumerationLiteralCS__OclAny = {
			OCLstdlibTables.Operations._OclAny___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclAny__oclAsType /* oclAsType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclAny__oclIsKindOf /* oclIsKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclAny__oclIsTypeOf /* oclIsTypeOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[?]) */,
			OCLstdlibTables.Operations._OclAny__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _EnumerationLiteralCS__OclElement = {
			OCLstdlibTables.Operations._OclElement__allInstances /* allInstances() */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _EnumerationLiteralCS__Pivotable = {};
		private static final @NonNull ExecutorOperation @NonNull [] _EnumerationLiteralCS__PivotableElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _EnumerationLiteralCS__VisitableCS = {};

		private static final @NonNull ExecutorOperation @NonNull [] _FeatureCS__FeatureCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _FeatureCS__ElementCS = {
			BaseCSTables.Operations._ElementCS__getDescription /* getDescription() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _FeatureCS__ModelElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _FeatureCS__Nameable = {};
		private static final @NonNull ExecutorOperation @NonNull [] _FeatureCS__NamedElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _FeatureCS__OclAny = {
			OCLstdlibTables.Operations._OclAny___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclAny__oclAsType /* oclAsType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclAny__oclIsKindOf /* oclIsKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclAny__oclIsTypeOf /* oclIsTypeOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[?]) */,
			OCLstdlibTables.Operations._OclAny__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _FeatureCS__OclElement = {
			OCLstdlibTables.Operations._OclElement__allInstances /* allInstances() */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _FeatureCS__Pivotable = {};
		private static final @NonNull ExecutorOperation @NonNull [] _FeatureCS__PivotableElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _FeatureCS__TypedElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _FeatureCS__VisitableCS = {};

		private static final @NonNull ExecutorOperation @NonNull [] _ImportCS__ImportCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _ImportCS__ElementCS = {
			BaseCSTables.Operations._ElementCS__getDescription /* getDescription() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _ImportCS__ModelElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _ImportCS__Nameable = {};
		private static final @NonNull ExecutorOperation @NonNull [] _ImportCS__NamedElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _ImportCS__NamespaceCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _ImportCS__OclAny = {
			OCLstdlibTables.Operations._OclAny___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclAny__oclAsType /* oclAsType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclAny__oclIsKindOf /* oclIsKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclAny__oclIsTypeOf /* oclIsTypeOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[?]) */,
			OCLstdlibTables.Operations._OclAny__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _ImportCS__OclElement = {
			OCLstdlibTables.Operations._OclElement__allInstances /* allInstances() */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _ImportCS__Pivotable = {};
		private static final @NonNull ExecutorOperation @NonNull [] _ImportCS__PivotableElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _ImportCS__VisitableCS = {};

		private static final @NonNull ExecutorOperation @NonNull [] _LambdaTypeCS__LambdaTypeCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _LambdaTypeCS__ElementCS = {
			BaseCSTables.Operations._ElementCS__getDescription /* getDescription() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _LambdaTypeCS__ElementRefCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _LambdaTypeCS__Nameable = {};
		private static final @NonNull ExecutorOperation @NonNull [] _LambdaTypeCS__OclAny = {
			OCLstdlibTables.Operations._OclAny___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclAny__oclAsType /* oclAsType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclAny__oclIsKindOf /* oclIsKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclAny__oclIsTypeOf /* oclIsTypeOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[?]) */,
			OCLstdlibTables.Operations._OclAny__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _LambdaTypeCS__OclElement = {
			OCLstdlibTables.Operations._OclElement__allInstances /* allInstances() */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _LambdaTypeCS__Pivotable = {};
		private static final @NonNull ExecutorOperation @NonNull [] _LambdaTypeCS__PivotableElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _LambdaTypeCS__TemplateableElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _LambdaTypeCS__TypeRefCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _LambdaTypeCS__TypedRefCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _LambdaTypeCS__VisitableCS = {};

		private static final @NonNull ExecutorOperation @NonNull [] _ModelElementCS__ModelElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _ModelElementCS__ElementCS = {
			BaseCSTables.Operations._ElementCS__getDescription /* getDescription() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _ModelElementCS__OclAny = {
			OCLstdlibTables.Operations._OclAny___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclAny__oclAsType /* oclAsType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclAny__oclIsKindOf /* oclIsKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclAny__oclIsTypeOf /* oclIsTypeOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[?]) */,
			OCLstdlibTables.Operations._OclAny__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _ModelElementCS__OclElement = {
			OCLstdlibTables.Operations._OclElement__allInstances /* allInstances() */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _ModelElementCS__Pivotable = {};
		private static final @NonNull ExecutorOperation @NonNull [] _ModelElementCS__PivotableElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _ModelElementCS__VisitableCS = {};

		private static final @NonNull ExecutorOperation @NonNull [] _ModelElementRefCS__ModelElementRefCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _ModelElementRefCS__ElementCS = {
			BaseCSTables.Operations._ElementCS__getDescription /* getDescription() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _ModelElementRefCS__ElementRefCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _ModelElementRefCS__OclAny = {
			OCLstdlibTables.Operations._OclAny___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclAny__oclAsType /* oclAsType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclAny__oclIsKindOf /* oclIsKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclAny__oclIsTypeOf /* oclIsTypeOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[?]) */,
			OCLstdlibTables.Operations._OclAny__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _ModelElementRefCS__OclElement = {
			OCLstdlibTables.Operations._OclElement__allInstances /* allInstances() */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _ModelElementRefCS__Pivotable = {};
		private static final @NonNull ExecutorOperation @NonNull [] _ModelElementRefCS__PivotableElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _ModelElementRefCS__VisitableCS = {};

		private static final @NonNull ExecutorOperation @NonNull [] _MultiplicityBoundsCS__MultiplicityBoundsCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _MultiplicityBoundsCS__ElementCS = {
			BaseCSTables.Operations._ElementCS__getDescription /* getDescription() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _MultiplicityBoundsCS__MultiplicityCS = {
			BaseCSTables.Operations._MultiplicityCS__getLower /* getLower() */,
			BaseCSTables.Operations._MultiplicityCS__getUpper /* getUpper() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _MultiplicityBoundsCS__OclAny = {
			OCLstdlibTables.Operations._OclAny___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclAny__oclAsType /* oclAsType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclAny__oclIsKindOf /* oclIsKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclAny__oclIsTypeOf /* oclIsTypeOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[?]) */,
			OCLstdlibTables.Operations._OclAny__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _MultiplicityBoundsCS__OclElement = {
			OCLstdlibTables.Operations._OclElement__allInstances /* allInstances() */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _MultiplicityBoundsCS__VisitableCS = {};

		private static final @NonNull ExecutorOperation @NonNull [] _MultiplicityCS__MultiplicityCS = {
			BaseCSTables.Operations._MultiplicityCS__getLower /* getLower() */,
			BaseCSTables.Operations._MultiplicityCS__getUpper /* getUpper() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _MultiplicityCS__ElementCS = {
			BaseCSTables.Operations._ElementCS__getDescription /* getDescription() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _MultiplicityCS__OclAny = {
			OCLstdlibTables.Operations._OclAny___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclAny__oclAsType /* oclAsType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclAny__oclIsKindOf /* oclIsKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclAny__oclIsTypeOf /* oclIsTypeOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[?]) */,
			OCLstdlibTables.Operations._OclAny__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _MultiplicityCS__OclElement = {
			OCLstdlibTables.Operations._OclElement__allInstances /* allInstances() */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _MultiplicityCS__VisitableCS = {};

		private static final @NonNull ExecutorOperation @NonNull [] _MultiplicityStringCS__MultiplicityStringCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _MultiplicityStringCS__ElementCS = {
			BaseCSTables.Operations._ElementCS__getDescription /* getDescription() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _MultiplicityStringCS__MultiplicityCS = {
			BaseCSTables.Operations._MultiplicityCS__getLower /* getLower() */,
			BaseCSTables.Operations._MultiplicityCS__getUpper /* getUpper() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _MultiplicityStringCS__OclAny = {
			OCLstdlibTables.Operations._OclAny___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclAny__oclAsType /* oclAsType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclAny__oclIsKindOf /* oclIsKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclAny__oclIsTypeOf /* oclIsTypeOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[?]) */,
			OCLstdlibTables.Operations._OclAny__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _MultiplicityStringCS__OclElement = {
			OCLstdlibTables.Operations._OclElement__allInstances /* allInstances() */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _MultiplicityStringCS__VisitableCS = {};

		private static final @NonNull ExecutorOperation @NonNull [] _NamedElementCS__NamedElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _NamedElementCS__ElementCS = {
			BaseCSTables.Operations._ElementCS__getDescription /* getDescription() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _NamedElementCS__ModelElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _NamedElementCS__Nameable = {};
		private static final @NonNull ExecutorOperation @NonNull [] _NamedElementCS__OclAny = {
			OCLstdlibTables.Operations._OclAny___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclAny__oclAsType /* oclAsType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclAny__oclIsKindOf /* oclIsKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclAny__oclIsTypeOf /* oclIsTypeOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[?]) */,
			OCLstdlibTables.Operations._OclAny__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _NamedElementCS__OclElement = {
			OCLstdlibTables.Operations._OclElement__allInstances /* allInstances() */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _NamedElementCS__Pivotable = {};
		private static final @NonNull ExecutorOperation @NonNull [] _NamedElementCS__PivotableElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _NamedElementCS__VisitableCS = {};

		private static final @NonNull ExecutorOperation @NonNull [] _NamespaceCS__NamespaceCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _NamespaceCS__ElementCS = {
			BaseCSTables.Operations._ElementCS__getDescription /* getDescription() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _NamespaceCS__ModelElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _NamespaceCS__Nameable = {};
		private static final @NonNull ExecutorOperation @NonNull [] _NamespaceCS__NamedElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _NamespaceCS__OclAny = {
			OCLstdlibTables.Operations._OclAny___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclAny__oclAsType /* oclAsType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclAny__oclIsKindOf /* oclIsKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclAny__oclIsTypeOf /* oclIsTypeOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[?]) */,
			OCLstdlibTables.Operations._OclAny__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _NamespaceCS__OclElement = {
			OCLstdlibTables.Operations._OclElement__allInstances /* allInstances() */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _NamespaceCS__Pivotable = {};
		private static final @NonNull ExecutorOperation @NonNull [] _NamespaceCS__PivotableElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _NamespaceCS__VisitableCS = {};

		private static final @NonNull ExecutorOperation @NonNull [] _OperationCS__OperationCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _OperationCS__ElementCS = {
			BaseCSTables.Operations._ElementCS__getDescription /* getDescription() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _OperationCS__FeatureCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _OperationCS__ModelElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _OperationCS__Nameable = {};
		private static final @NonNull ExecutorOperation @NonNull [] _OperationCS__NamedElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _OperationCS__OclAny = {
			OCLstdlibTables.Operations._OclAny___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclAny__oclAsType /* oclAsType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclAny__oclIsKindOf /* oclIsKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclAny__oclIsTypeOf /* oclIsTypeOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[?]) */,
			OCLstdlibTables.Operations._OclAny__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _OperationCS__OclElement = {
			OCLstdlibTables.Operations._OclElement__allInstances /* allInstances() */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _OperationCS__Pivotable = {};
		private static final @NonNull ExecutorOperation @NonNull [] _OperationCS__PivotableElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _OperationCS__TemplateableElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _OperationCS__TypedElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _OperationCS__VisitableCS = {};

		private static final @NonNull ExecutorOperation @NonNull [] _PackageCS__PackageCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _PackageCS__ElementCS = {
			BaseCSTables.Operations._ElementCS__getDescription /* getDescription() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _PackageCS__ModelElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _PackageCS__Nameable = {};
		private static final @NonNull ExecutorOperation @NonNull [] _PackageCS__NamedElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _PackageCS__NamespaceCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _PackageCS__OclAny = {
			OCLstdlibTables.Operations._OclAny___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclAny__oclAsType /* oclAsType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclAny__oclIsKindOf /* oclIsKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclAny__oclIsTypeOf /* oclIsTypeOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[?]) */,
			OCLstdlibTables.Operations._OclAny__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _PackageCS__OclElement = {
			OCLstdlibTables.Operations._OclElement__allInstances /* allInstances() */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _PackageCS__PackageOwnerCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _PackageCS__Pivotable = {};
		private static final @NonNull ExecutorOperation @NonNull [] _PackageCS__PivotableElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _PackageCS__VisitableCS = {};

		private static final @NonNull ExecutorOperation @NonNull [] _PackageOwnerCS__PackageOwnerCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _PackageOwnerCS__ElementCS = {
			BaseCSTables.Operations._ElementCS__getDescription /* getDescription() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _PackageOwnerCS__ModelElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _PackageOwnerCS__OclAny = {
			OCLstdlibTables.Operations._OclAny___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclAny__oclAsType /* oclAsType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclAny__oclIsKindOf /* oclIsKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclAny__oclIsTypeOf /* oclIsTypeOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[?]) */,
			OCLstdlibTables.Operations._OclAny__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _PackageOwnerCS__OclElement = {
			OCLstdlibTables.Operations._OclElement__allInstances /* allInstances() */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _PackageOwnerCS__Pivotable = {};
		private static final @NonNull ExecutorOperation @NonNull [] _PackageOwnerCS__PivotableElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _PackageOwnerCS__VisitableCS = {};

		private static final @NonNull ExecutorOperation @NonNull [] _ParameterCS__ParameterCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _ParameterCS__ElementCS = {
			BaseCSTables.Operations._ElementCS__getDescription /* getDescription() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _ParameterCS__ModelElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _ParameterCS__Nameable = {};
		private static final @NonNull ExecutorOperation @NonNull [] _ParameterCS__NamedElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _ParameterCS__OclAny = {
			OCLstdlibTables.Operations._OclAny___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclAny__oclAsType /* oclAsType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclAny__oclIsKindOf /* oclIsKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclAny__oclIsTypeOf /* oclIsTypeOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[?]) */,
			OCLstdlibTables.Operations._OclAny__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _ParameterCS__OclElement = {
			OCLstdlibTables.Operations._OclElement__allInstances /* allInstances() */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _ParameterCS__Pivotable = {};
		private static final @NonNull ExecutorOperation @NonNull [] _ParameterCS__PivotableElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _ParameterCS__TypedElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _ParameterCS__VisitableCS = {};

		private static final @NonNull ExecutorOperation @NonNull [] _PathElementCS__PathElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _PathElementCS__ElementCS = {
			BaseCSTables.Operations._ElementCS__getDescription /* getDescription() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _PathElementCS__OclAny = {
			OCLstdlibTables.Operations._OclAny___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclAny__oclAsType /* oclAsType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclAny__oclIsKindOf /* oclIsKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclAny__oclIsTypeOf /* oclIsTypeOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[?]) */,
			OCLstdlibTables.Operations._OclAny__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _PathElementCS__OclElement = {
			OCLstdlibTables.Operations._OclElement__allInstances /* allInstances() */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _PathElementCS__Pivotable = {};
		private static final @NonNull ExecutorOperation @NonNull [] _PathElementCS__VisitableCS = {};

		private static final @NonNull ExecutorOperation @NonNull [] _PathElementWithURICS__PathElementWithURICS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _PathElementWithURICS__ElementCS = {
			BaseCSTables.Operations._ElementCS__getDescription /* getDescription() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _PathElementWithURICS__OclAny = {
			OCLstdlibTables.Operations._OclAny___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclAny__oclAsType /* oclAsType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclAny__oclIsKindOf /* oclIsKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclAny__oclIsTypeOf /* oclIsTypeOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[?]) */,
			OCLstdlibTables.Operations._OclAny__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _PathElementWithURICS__OclElement = {
			OCLstdlibTables.Operations._OclElement__allInstances /* allInstances() */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _PathElementWithURICS__PathElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _PathElementWithURICS__Pivotable = {};
		private static final @NonNull ExecutorOperation @NonNull [] _PathElementWithURICS__VisitableCS = {};

		private static final @NonNull ExecutorOperation @NonNull [] _PathNameCS__PathNameCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _PathNameCS__ElementCS = {
			BaseCSTables.Operations._ElementCS__getDescription /* getDescription() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _PathNameCS__OclAny = {
			OCLstdlibTables.Operations._OclAny___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclAny__oclAsType /* oclAsType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclAny__oclIsKindOf /* oclIsKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclAny__oclIsTypeOf /* oclIsTypeOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[?]) */,
			OCLstdlibTables.Operations._OclAny__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _PathNameCS__OclElement = {
			OCLstdlibTables.Operations._OclElement__allInstances /* allInstances() */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _PathNameCS__Pivotable = {};
		private static final @NonNull ExecutorOperation @NonNull [] _PathNameCS__VisitableCS = {};

		private static final @NonNull ExecutorOperation @NonNull [] _PivotableElementCS__PivotableElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _PivotableElementCS__ElementCS = {
			BaseCSTables.Operations._ElementCS__getDescription /* getDescription() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _PivotableElementCS__OclAny = {
			OCLstdlibTables.Operations._OclAny___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclAny__oclAsType /* oclAsType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclAny__oclIsKindOf /* oclIsKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclAny__oclIsTypeOf /* oclIsTypeOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[?]) */,
			OCLstdlibTables.Operations._OclAny__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _PivotableElementCS__OclElement = {
			OCLstdlibTables.Operations._OclElement__allInstances /* allInstances() */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _PivotableElementCS__Pivotable = {};
		private static final @NonNull ExecutorOperation @NonNull [] _PivotableElementCS__VisitableCS = {};

		private static final @NonNull ExecutorOperation @NonNull [] _PrimitiveTypeRefCS__PrimitiveTypeRefCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _PrimitiveTypeRefCS__ElementCS = {
			BaseCSTables.Operations._ElementCS__getDescription /* getDescription() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _PrimitiveTypeRefCS__ElementRefCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _PrimitiveTypeRefCS__Nameable = {};
		private static final @NonNull ExecutorOperation @NonNull [] _PrimitiveTypeRefCS__OclAny = {
			OCLstdlibTables.Operations._OclAny___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclAny__oclAsType /* oclAsType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclAny__oclIsKindOf /* oclIsKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclAny__oclIsTypeOf /* oclIsTypeOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[?]) */,
			OCLstdlibTables.Operations._OclAny__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _PrimitiveTypeRefCS__OclElement = {
			OCLstdlibTables.Operations._OclElement__allInstances /* allInstances() */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _PrimitiveTypeRefCS__Pivotable = {};
		private static final @NonNull ExecutorOperation @NonNull [] _PrimitiveTypeRefCS__PivotableElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _PrimitiveTypeRefCS__TypeRefCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _PrimitiveTypeRefCS__TypedRefCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _PrimitiveTypeRefCS__VisitableCS = {};

		private static final @NonNull ExecutorOperation @NonNull [] _ReferenceCS__ReferenceCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _ReferenceCS__ElementCS = {
			BaseCSTables.Operations._ElementCS__getDescription /* getDescription() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _ReferenceCS__FeatureCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _ReferenceCS__ModelElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _ReferenceCS__Nameable = {};
		private static final @NonNull ExecutorOperation @NonNull [] _ReferenceCS__NamedElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _ReferenceCS__OclAny = {
			OCLstdlibTables.Operations._OclAny___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclAny__oclAsType /* oclAsType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclAny__oclIsKindOf /* oclIsKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclAny__oclIsTypeOf /* oclIsTypeOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[?]) */,
			OCLstdlibTables.Operations._OclAny__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _ReferenceCS__OclElement = {
			OCLstdlibTables.Operations._OclElement__allInstances /* allInstances() */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _ReferenceCS__Pivotable = {};
		private static final @NonNull ExecutorOperation @NonNull [] _ReferenceCS__PivotableElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _ReferenceCS__StructuralFeatureCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _ReferenceCS__TypedElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _ReferenceCS__VisitableCS = {};

		private static final @NonNull ExecutorOperation @NonNull [] _RootCS__RootCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _RootCS__ElementCS = {
			BaseCSTables.Operations._ElementCS__getDescription /* getDescription() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _RootCS__ModelElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _RootCS__OclAny = {
			OCLstdlibTables.Operations._OclAny___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclAny__oclAsType /* oclAsType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclAny__oclIsKindOf /* oclIsKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclAny__oclIsTypeOf /* oclIsTypeOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[?]) */,
			OCLstdlibTables.Operations._OclAny__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _RootCS__OclElement = {
			OCLstdlibTables.Operations._OclElement__allInstances /* allInstances() */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _RootCS__Pivotable = {};
		private static final @NonNull ExecutorOperation @NonNull [] _RootCS__PivotableElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _RootCS__VisitableCS = {};

		private static final @NonNull ExecutorOperation @NonNull [] _RootPackageCS__RootPackageCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _RootPackageCS__ElementCS = {
			BaseCSTables.Operations._ElementCS__getDescription /* getDescription() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _RootPackageCS__ModelElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _RootPackageCS__OclAny = {
			OCLstdlibTables.Operations._OclAny___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclAny__oclAsType /* oclAsType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclAny__oclIsKindOf /* oclIsKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclAny__oclIsTypeOf /* oclIsTypeOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[?]) */,
			OCLstdlibTables.Operations._OclAny__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _RootPackageCS__OclElement = {
			OCLstdlibTables.Operations._OclElement__allInstances /* allInstances() */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _RootPackageCS__PackageOwnerCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _RootPackageCS__Pivotable = {};
		private static final @NonNull ExecutorOperation @NonNull [] _RootPackageCS__PivotableElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _RootPackageCS__RootCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _RootPackageCS__VisitableCS = {};

		private static final @NonNull ExecutorOperation @NonNull [] _ScopeFilter__ScopeFilter = {};
		private static final @NonNull ExecutorOperation @NonNull [] _ScopeFilter__OclAny = {
			OCLstdlibTables.Operations._OclAny___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclAny__oclAsType /* oclAsType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclAny__oclIsKindOf /* oclIsKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclAny__oclIsTypeOf /* oclIsTypeOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[?]) */,
			OCLstdlibTables.Operations._OclAny__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};

		private static final @NonNull ExecutorOperation @NonNull [] _SpecificationCS__SpecificationCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _SpecificationCS__ElementCS = {
			BaseCSTables.Operations._ElementCS__getDescription /* getDescription() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _SpecificationCS__ModelElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _SpecificationCS__OclAny = {
			OCLstdlibTables.Operations._OclAny___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclAny__oclAsType /* oclAsType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclAny__oclIsKindOf /* oclIsKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclAny__oclIsTypeOf /* oclIsTypeOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[?]) */,
			OCLstdlibTables.Operations._OclAny__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _SpecificationCS__OclElement = {
			OCLstdlibTables.Operations._OclElement__allInstances /* allInstances() */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _SpecificationCS__Pivotable = {};
		private static final @NonNull ExecutorOperation @NonNull [] _SpecificationCS__PivotableElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _SpecificationCS__VisitableCS = {};

		private static final @NonNull ExecutorOperation @NonNull [] _StructuralFeatureCS__StructuralFeatureCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _StructuralFeatureCS__ElementCS = {
			BaseCSTables.Operations._ElementCS__getDescription /* getDescription() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _StructuralFeatureCS__FeatureCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _StructuralFeatureCS__ModelElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _StructuralFeatureCS__Nameable = {};
		private static final @NonNull ExecutorOperation @NonNull [] _StructuralFeatureCS__NamedElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _StructuralFeatureCS__OclAny = {
			OCLstdlibTables.Operations._OclAny___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclAny__oclAsType /* oclAsType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclAny__oclIsKindOf /* oclIsKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclAny__oclIsTypeOf /* oclIsTypeOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[?]) */,
			OCLstdlibTables.Operations._OclAny__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _StructuralFeatureCS__OclElement = {
			OCLstdlibTables.Operations._OclElement__allInstances /* allInstances() */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _StructuralFeatureCS__Pivotable = {};
		private static final @NonNull ExecutorOperation @NonNull [] _StructuralFeatureCS__PivotableElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _StructuralFeatureCS__TypedElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _StructuralFeatureCS__VisitableCS = {};

		private static final @NonNull ExecutorOperation @NonNull [] _StructuredClassCS__StructuredClassCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _StructuredClassCS__ClassCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _StructuredClassCS__ElementCS = {
			BaseCSTables.Operations._ElementCS__getDescription /* getDescription() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _StructuredClassCS__ModelElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _StructuredClassCS__Nameable = {};
		private static final @NonNull ExecutorOperation @NonNull [] _StructuredClassCS__NamedElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _StructuredClassCS__NamespaceCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _StructuredClassCS__OclAny = {
			OCLstdlibTables.Operations._OclAny___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclAny__oclAsType /* oclAsType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclAny__oclIsKindOf /* oclIsKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclAny__oclIsTypeOf /* oclIsTypeOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[?]) */,
			OCLstdlibTables.Operations._OclAny__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _StructuredClassCS__OclElement = {
			OCLstdlibTables.Operations._OclElement__allInstances /* allInstances() */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _StructuredClassCS__Pivotable = {};
		private static final @NonNull ExecutorOperation @NonNull [] _StructuredClassCS__PivotableElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _StructuredClassCS__TemplateableElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _StructuredClassCS__TypeCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _StructuredClassCS__VisitableCS = {};

		private static final @NonNull ExecutorOperation @NonNull [] _TemplateBindingCS__TemplateBindingCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _TemplateBindingCS__ElementCS = {
			BaseCSTables.Operations._ElementCS__getDescription /* getDescription() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _TemplateBindingCS__ElementRefCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _TemplateBindingCS__OclAny = {
			OCLstdlibTables.Operations._OclAny___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclAny__oclAsType /* oclAsType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclAny__oclIsKindOf /* oclIsKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclAny__oclIsTypeOf /* oclIsTypeOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[?]) */,
			OCLstdlibTables.Operations._OclAny__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _TemplateBindingCS__OclElement = {
			OCLstdlibTables.Operations._OclElement__allInstances /* allInstances() */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _TemplateBindingCS__Pivotable = {};
		private static final @NonNull ExecutorOperation @NonNull [] _TemplateBindingCS__PivotableElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _TemplateBindingCS__VisitableCS = {};

		private static final @NonNull ExecutorOperation @NonNull [] _TemplateParameterCS__TemplateParameterCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _TemplateParameterCS__ElementCS = {
			BaseCSTables.Operations._ElementCS__getDescription /* getDescription() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _TemplateParameterCS__ModelElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _TemplateParameterCS__Nameable = {};
		private static final @NonNull ExecutorOperation @NonNull [] _TemplateParameterCS__NamedElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _TemplateParameterCS__OclAny = {
			OCLstdlibTables.Operations._OclAny___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclAny__oclAsType /* oclAsType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclAny__oclIsKindOf /* oclIsKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclAny__oclIsTypeOf /* oclIsTypeOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[?]) */,
			OCLstdlibTables.Operations._OclAny__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _TemplateParameterCS__OclElement = {
			OCLstdlibTables.Operations._OclElement__allInstances /* allInstances() */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _TemplateParameterCS__Pivotable = {};
		private static final @NonNull ExecutorOperation @NonNull [] _TemplateParameterCS__PivotableElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _TemplateParameterCS__VisitableCS = {};

		private static final @NonNull ExecutorOperation @NonNull [] _TemplateParameterSubstitutionCS__TemplateParameterSubstitutionCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _TemplateParameterSubstitutionCS__ElementCS = {
			BaseCSTables.Operations._ElementCS__getDescription /* getDescription() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _TemplateParameterSubstitutionCS__ModelElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _TemplateParameterSubstitutionCS__OclAny = {
			OCLstdlibTables.Operations._OclAny___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclAny__oclAsType /* oclAsType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclAny__oclIsKindOf /* oclIsKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclAny__oclIsTypeOf /* oclIsTypeOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[?]) */,
			OCLstdlibTables.Operations._OclAny__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _TemplateParameterSubstitutionCS__OclElement = {
			OCLstdlibTables.Operations._OclElement__allInstances /* allInstances() */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _TemplateParameterSubstitutionCS__Pivotable = {};
		private static final @NonNull ExecutorOperation @NonNull [] _TemplateParameterSubstitutionCS__PivotableElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _TemplateParameterSubstitutionCS__VisitableCS = {};

		private static final @NonNull ExecutorOperation @NonNull [] _TemplateSignatureCS__TemplateSignatureCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _TemplateSignatureCS__ElementCS = {
			BaseCSTables.Operations._ElementCS__getDescription /* getDescription() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _TemplateSignatureCS__ModelElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _TemplateSignatureCS__OclAny = {
			OCLstdlibTables.Operations._OclAny___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclAny__oclAsType /* oclAsType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclAny__oclIsKindOf /* oclIsKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclAny__oclIsTypeOf /* oclIsTypeOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[?]) */,
			OCLstdlibTables.Operations._OclAny__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _TemplateSignatureCS__OclElement = {
			OCLstdlibTables.Operations._OclElement__allInstances /* allInstances() */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _TemplateSignatureCS__Pivotable = {};
		private static final @NonNull ExecutorOperation @NonNull [] _TemplateSignatureCS__PivotableElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _TemplateSignatureCS__VisitableCS = {};

		private static final @NonNull ExecutorOperation @NonNull [] _TemplateableElementCS__TemplateableElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _TemplateableElementCS__ElementCS = {
			BaseCSTables.Operations._ElementCS__getDescription /* getDescription() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _TemplateableElementCS__OclAny = {
			OCLstdlibTables.Operations._OclAny___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclAny__oclAsType /* oclAsType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclAny__oclIsKindOf /* oclIsKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclAny__oclIsTypeOf /* oclIsTypeOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[?]) */,
			OCLstdlibTables.Operations._OclAny__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _TemplateableElementCS__OclElement = {
			OCLstdlibTables.Operations._OclElement__allInstances /* allInstances() */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _TemplateableElementCS__VisitableCS = {};

		private static final @NonNull ExecutorOperation @NonNull [] _TuplePartCS__TuplePartCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _TuplePartCS__ElementCS = {
			BaseCSTables.Operations._ElementCS__getDescription /* getDescription() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _TuplePartCS__ModelElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _TuplePartCS__Nameable = {};
		private static final @NonNull ExecutorOperation @NonNull [] _TuplePartCS__NamedElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _TuplePartCS__OclAny = {
			OCLstdlibTables.Operations._OclAny___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclAny__oclAsType /* oclAsType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclAny__oclIsKindOf /* oclIsKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclAny__oclIsTypeOf /* oclIsTypeOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[?]) */,
			OCLstdlibTables.Operations._OclAny__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _TuplePartCS__OclElement = {
			OCLstdlibTables.Operations._OclElement__allInstances /* allInstances() */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _TuplePartCS__Pivotable = {};
		private static final @NonNull ExecutorOperation @NonNull [] _TuplePartCS__PivotableElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _TuplePartCS__TypedElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _TuplePartCS__VisitableCS = {};

		private static final @NonNull ExecutorOperation @NonNull [] _TupleTypeCS__TupleTypeCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _TupleTypeCS__ElementCS = {
			BaseCSTables.Operations._ElementCS__getDescription /* getDescription() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _TupleTypeCS__ElementRefCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _TupleTypeCS__Nameable = {};
		private static final @NonNull ExecutorOperation @NonNull [] _TupleTypeCS__OclAny = {
			OCLstdlibTables.Operations._OclAny___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclAny__oclAsType /* oclAsType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclAny__oclIsKindOf /* oclIsKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclAny__oclIsTypeOf /* oclIsTypeOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[?]) */,
			OCLstdlibTables.Operations._OclAny__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _TupleTypeCS__OclElement = {
			OCLstdlibTables.Operations._OclElement__allInstances /* allInstances() */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _TupleTypeCS__Pivotable = {};
		private static final @NonNull ExecutorOperation @NonNull [] _TupleTypeCS__PivotableElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _TupleTypeCS__TypeRefCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _TupleTypeCS__TypedRefCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _TupleTypeCS__VisitableCS = {};

		private static final @NonNull ExecutorOperation @NonNull [] _TypeCS__TypeCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _TypeCS__ElementCS = {
			BaseCSTables.Operations._ElementCS__getDescription /* getDescription() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _TypeCS__ModelElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _TypeCS__OclAny = {
			OCLstdlibTables.Operations._OclAny___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclAny__oclAsType /* oclAsType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclAny__oclIsKindOf /* oclIsKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclAny__oclIsTypeOf /* oclIsTypeOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[?]) */,
			OCLstdlibTables.Operations._OclAny__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _TypeCS__OclElement = {
			OCLstdlibTables.Operations._OclElement__allInstances /* allInstances() */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _TypeCS__Pivotable = {};
		private static final @NonNull ExecutorOperation @NonNull [] _TypeCS__PivotableElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _TypeCS__VisitableCS = {};

		private static final @NonNull ExecutorOperation @NonNull [] _TypeParameterCS__TypeParameterCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _TypeParameterCS__ElementCS = {
			BaseCSTables.Operations._ElementCS__getDescription /* getDescription() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _TypeParameterCS__ModelElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _TypeParameterCS__Nameable = {};
		private static final @NonNull ExecutorOperation @NonNull [] _TypeParameterCS__NamedElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _TypeParameterCS__OclAny = {
			OCLstdlibTables.Operations._OclAny___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclAny__oclAsType /* oclAsType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclAny__oclIsKindOf /* oclIsKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclAny__oclIsTypeOf /* oclIsTypeOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[?]) */,
			OCLstdlibTables.Operations._OclAny__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _TypeParameterCS__OclElement = {
			OCLstdlibTables.Operations._OclElement__allInstances /* allInstances() */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _TypeParameterCS__Pivotable = {};
		private static final @NonNull ExecutorOperation @NonNull [] _TypeParameterCS__PivotableElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _TypeParameterCS__TemplateParameterCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _TypeParameterCS__TypeCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _TypeParameterCS__VisitableCS = {};

		private static final @NonNull ExecutorOperation @NonNull [] _TypeRefCS__TypeRefCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _TypeRefCS__ElementCS = {
			BaseCSTables.Operations._ElementCS__getDescription /* getDescription() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _TypeRefCS__ElementRefCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _TypeRefCS__OclAny = {
			OCLstdlibTables.Operations._OclAny___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclAny__oclAsType /* oclAsType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclAny__oclIsKindOf /* oclIsKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclAny__oclIsTypeOf /* oclIsTypeOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[?]) */,
			OCLstdlibTables.Operations._OclAny__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _TypeRefCS__OclElement = {
			OCLstdlibTables.Operations._OclElement__allInstances /* allInstances() */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _TypeRefCS__Pivotable = {};
		private static final @NonNull ExecutorOperation @NonNull [] _TypeRefCS__PivotableElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _TypeRefCS__VisitableCS = {};

		private static final @NonNull ExecutorOperation @NonNull [] _TypedElementCS__TypedElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _TypedElementCS__ElementCS = {
			BaseCSTables.Operations._ElementCS__getDescription /* getDescription() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _TypedElementCS__ModelElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _TypedElementCS__Nameable = {};
		private static final @NonNull ExecutorOperation @NonNull [] _TypedElementCS__NamedElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _TypedElementCS__OclAny = {
			OCLstdlibTables.Operations._OclAny___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclAny__oclAsType /* oclAsType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclAny__oclIsKindOf /* oclIsKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclAny__oclIsTypeOf /* oclIsTypeOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[?]) */,
			OCLstdlibTables.Operations._OclAny__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _TypedElementCS__OclElement = {
			OCLstdlibTables.Operations._OclElement__allInstances /* allInstances() */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _TypedElementCS__Pivotable = {};
		private static final @NonNull ExecutorOperation @NonNull [] _TypedElementCS__PivotableElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _TypedElementCS__VisitableCS = {};

		private static final @NonNull ExecutorOperation @NonNull [] _TypedRefCS__TypedRefCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _TypedRefCS__ElementCS = {
			BaseCSTables.Operations._ElementCS__getDescription /* getDescription() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _TypedRefCS__ElementRefCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _TypedRefCS__OclAny = {
			OCLstdlibTables.Operations._OclAny___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclAny__oclAsType /* oclAsType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclAny__oclIsKindOf /* oclIsKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclAny__oclIsTypeOf /* oclIsTypeOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[?]) */,
			OCLstdlibTables.Operations._OclAny__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _TypedRefCS__OclElement = {
			OCLstdlibTables.Operations._OclElement__allInstances /* allInstances() */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _TypedRefCS__Pivotable = {};
		private static final @NonNull ExecutorOperation @NonNull [] _TypedRefCS__PivotableElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _TypedRefCS__TypeRefCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _TypedRefCS__VisitableCS = {};

		private static final @NonNull ExecutorOperation @NonNull [] _TypedTypeRefCS__TypedTypeRefCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _TypedTypeRefCS__ElementCS = {
			BaseCSTables.Operations._ElementCS__getDescription /* getDescription() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _TypedTypeRefCS__ElementRefCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _TypedTypeRefCS__OclAny = {
			OCLstdlibTables.Operations._OclAny___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclAny__oclAsType /* oclAsType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclAny__oclIsKindOf /* oclIsKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclAny__oclIsTypeOf /* oclIsTypeOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[?]) */,
			OCLstdlibTables.Operations._OclAny__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _TypedTypeRefCS__OclElement = {
			OCLstdlibTables.Operations._OclElement__allInstances /* allInstances() */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _TypedTypeRefCS__Pivotable = {};
		private static final @NonNull ExecutorOperation @NonNull [] _TypedTypeRefCS__PivotableElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _TypedTypeRefCS__TypeRefCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _TypedTypeRefCS__TypedRefCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _TypedTypeRefCS__VisitableCS = {};

		private static final @NonNull ExecutorOperation @NonNull [] _VisitableCS__VisitableCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _VisitableCS__OclAny = {
			OCLstdlibTables.Operations._OclAny___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclAny__oclAsType /* oclAsType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclAny__oclIsKindOf /* oclIsKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclAny__oclIsTypeOf /* oclIsTypeOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[?]) */,
			OCLstdlibTables.Operations._OclAny__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _VisitableCS__OclElement = {
			OCLstdlibTables.Operations._OclElement__allInstances /* allInstances() */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */
		};

		private static final @NonNull ExecutorOperation @NonNull [] _WildcardTypeRefCS__WildcardTypeRefCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _WildcardTypeRefCS__ElementCS = {
			BaseCSTables.Operations._ElementCS__getDescription /* getDescription() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _WildcardTypeRefCS__ElementRefCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _WildcardTypeRefCS__OclAny = {
			OCLstdlibTables.Operations._OclAny___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclAny__oclAsType /* oclAsType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclAny__oclIsKindOf /* oclIsKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclAny__oclIsTypeOf /* oclIsTypeOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[?]) */,
			OCLstdlibTables.Operations._OclAny__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _WildcardTypeRefCS__OclElement = {
			OCLstdlibTables.Operations._OclElement__allInstances /* allInstances() */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */
		};
		private static final @NonNull ExecutorOperation @NonNull [] _WildcardTypeRefCS__Pivotable = {};
		private static final @NonNull ExecutorOperation @NonNull [] _WildcardTypeRefCS__PivotableElementCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _WildcardTypeRefCS__TypeRefCS = {};
		private static final @NonNull ExecutorOperation @NonNull [] _WildcardTypeRefCS__VisitableCS = {};

		/*
		 *	Install the operation descriptors in the fragment descriptors.
		 */
		static {
			Fragments._AnnotationCS__AnnotationCS.initOperations(_AnnotationCS__AnnotationCS);
			Fragments._AnnotationCS__AnnotationElementCS.initOperations(_AnnotationCS__AnnotationElementCS);
			Fragments._AnnotationCS__ElementCS.initOperations(_AnnotationCS__ElementCS);
			Fragments._AnnotationCS__ModelElementCS.initOperations(_AnnotationCS__ModelElementCS);
			Fragments._AnnotationCS__Nameable.initOperations(_AnnotationCS__Nameable);
			Fragments._AnnotationCS__NamedElementCS.initOperations(_AnnotationCS__NamedElementCS);
			Fragments._AnnotationCS__OclAny.initOperations(_AnnotationCS__OclAny);
			Fragments._AnnotationCS__OclElement.initOperations(_AnnotationCS__OclElement);
			Fragments._AnnotationCS__Pivotable.initOperations(_AnnotationCS__Pivotable);
			Fragments._AnnotationCS__PivotableElementCS.initOperations(_AnnotationCS__PivotableElementCS);
			Fragments._AnnotationCS__VisitableCS.initOperations(_AnnotationCS__VisitableCS);

			Fragments._AnnotationElementCS__AnnotationElementCS.initOperations(_AnnotationElementCS__AnnotationElementCS);
			Fragments._AnnotationElementCS__ElementCS.initOperations(_AnnotationElementCS__ElementCS);
			Fragments._AnnotationElementCS__ModelElementCS.initOperations(_AnnotationElementCS__ModelElementCS);
			Fragments._AnnotationElementCS__Nameable.initOperations(_AnnotationElementCS__Nameable);
			Fragments._AnnotationElementCS__NamedElementCS.initOperations(_AnnotationElementCS__NamedElementCS);
			Fragments._AnnotationElementCS__OclAny.initOperations(_AnnotationElementCS__OclAny);
			Fragments._AnnotationElementCS__OclElement.initOperations(_AnnotationElementCS__OclElement);
			Fragments._AnnotationElementCS__Pivotable.initOperations(_AnnotationElementCS__Pivotable);
			Fragments._AnnotationElementCS__PivotableElementCS.initOperations(_AnnotationElementCS__PivotableElementCS);
			Fragments._AnnotationElementCS__VisitableCS.initOperations(_AnnotationElementCS__VisitableCS);

			Fragments._AttributeCS__AttributeCS.initOperations(_AttributeCS__AttributeCS);
			Fragments._AttributeCS__ElementCS.initOperations(_AttributeCS__ElementCS);
			Fragments._AttributeCS__FeatureCS.initOperations(_AttributeCS__FeatureCS);
			Fragments._AttributeCS__ModelElementCS.initOperations(_AttributeCS__ModelElementCS);
			Fragments._AttributeCS__Nameable.initOperations(_AttributeCS__Nameable);
			Fragments._AttributeCS__NamedElementCS.initOperations(_AttributeCS__NamedElementCS);
			Fragments._AttributeCS__OclAny.initOperations(_AttributeCS__OclAny);
			Fragments._AttributeCS__OclElement.initOperations(_AttributeCS__OclElement);
			Fragments._AttributeCS__Pivotable.initOperations(_AttributeCS__Pivotable);
			Fragments._AttributeCS__PivotableElementCS.initOperations(_AttributeCS__PivotableElementCS);
			Fragments._AttributeCS__StructuralFeatureCS.initOperations(_AttributeCS__StructuralFeatureCS);
			Fragments._AttributeCS__TypedElementCS.initOperations(_AttributeCS__TypedElementCS);
			Fragments._AttributeCS__VisitableCS.initOperations(_AttributeCS__VisitableCS);

			Fragments._BigNumber__BigNumber.initOperations(_BigNumber__BigNumber);
			Fragments._BigNumber__OclAny.initOperations(_BigNumber__OclAny);

			Fragments._CSI__CSI.initOperations(_CSI__CSI);
			Fragments._CSI__OclAny.initOperations(_CSI__OclAny);

			Fragments._ClassCS__ClassCS.initOperations(_ClassCS__ClassCS);
			Fragments._ClassCS__ElementCS.initOperations(_ClassCS__ElementCS);
			Fragments._ClassCS__ModelElementCS.initOperations(_ClassCS__ModelElementCS);
			Fragments._ClassCS__Nameable.initOperations(_ClassCS__Nameable);
			Fragments._ClassCS__NamedElementCS.initOperations(_ClassCS__NamedElementCS);
			Fragments._ClassCS__OclAny.initOperations(_ClassCS__OclAny);
			Fragments._ClassCS__OclElement.initOperations(_ClassCS__OclElement);
			Fragments._ClassCS__Pivotable.initOperations(_ClassCS__Pivotable);
			Fragments._ClassCS__PivotableElementCS.initOperations(_ClassCS__PivotableElementCS);
			Fragments._ClassCS__TemplateableElementCS.initOperations(_ClassCS__TemplateableElementCS);
			Fragments._ClassCS__TypeCS.initOperations(_ClassCS__TypeCS);
			Fragments._ClassCS__VisitableCS.initOperations(_ClassCS__VisitableCS);

			Fragments._ConstraintCS__ConstraintCS.initOperations(_ConstraintCS__ConstraintCS);
			Fragments._ConstraintCS__ElementCS.initOperations(_ConstraintCS__ElementCS);
			Fragments._ConstraintCS__ModelElementCS.initOperations(_ConstraintCS__ModelElementCS);
			Fragments._ConstraintCS__Nameable.initOperations(_ConstraintCS__Nameable);
			Fragments._ConstraintCS__NamedElementCS.initOperations(_ConstraintCS__NamedElementCS);
			Fragments._ConstraintCS__OclAny.initOperations(_ConstraintCS__OclAny);
			Fragments._ConstraintCS__OclElement.initOperations(_ConstraintCS__OclElement);
			Fragments._ConstraintCS__Pivotable.initOperations(_ConstraintCS__Pivotable);
			Fragments._ConstraintCS__PivotableElementCS.initOperations(_ConstraintCS__PivotableElementCS);
			Fragments._ConstraintCS__VisitableCS.initOperations(_ConstraintCS__VisitableCS);

			Fragments._ContextLessElementCS__ContextLessElementCS.initOperations(_ContextLessElementCS__ContextLessElementCS);
			Fragments._ContextLessElementCS__ElementCS.initOperations(_ContextLessElementCS__ElementCS);
			Fragments._ContextLessElementCS__OclAny.initOperations(_ContextLessElementCS__OclAny);
			Fragments._ContextLessElementCS__OclElement.initOperations(_ContextLessElementCS__OclElement);
			Fragments._ContextLessElementCS__VisitableCS.initOperations(_ContextLessElementCS__VisitableCS);

			Fragments._DataTypeCS__ClassCS.initOperations(_DataTypeCS__ClassCS);
			Fragments._DataTypeCS__DataTypeCS.initOperations(_DataTypeCS__DataTypeCS);
			Fragments._DataTypeCS__ElementCS.initOperations(_DataTypeCS__ElementCS);
			Fragments._DataTypeCS__ModelElementCS.initOperations(_DataTypeCS__ModelElementCS);
			Fragments._DataTypeCS__Nameable.initOperations(_DataTypeCS__Nameable);
			Fragments._DataTypeCS__NamedElementCS.initOperations(_DataTypeCS__NamedElementCS);
			Fragments._DataTypeCS__NamespaceCS.initOperations(_DataTypeCS__NamespaceCS);
			Fragments._DataTypeCS__OclAny.initOperations(_DataTypeCS__OclAny);
			Fragments._DataTypeCS__OclElement.initOperations(_DataTypeCS__OclElement);
			Fragments._DataTypeCS__Pivotable.initOperations(_DataTypeCS__Pivotable);
			Fragments._DataTypeCS__PivotableElementCS.initOperations(_DataTypeCS__PivotableElementCS);
			Fragments._DataTypeCS__TemplateableElementCS.initOperations(_DataTypeCS__TemplateableElementCS);
			Fragments._DataTypeCS__TypeCS.initOperations(_DataTypeCS__TypeCS);
			Fragments._DataTypeCS__VisitableCS.initOperations(_DataTypeCS__VisitableCS);

			Fragments._DetailCS__DetailCS.initOperations(_DetailCS__DetailCS);
			Fragments._DetailCS__ElementCS.initOperations(_DetailCS__ElementCS);
			Fragments._DetailCS__ModelElementCS.initOperations(_DetailCS__ModelElementCS);
			Fragments._DetailCS__Nameable.initOperations(_DetailCS__Nameable);
			Fragments._DetailCS__NamedElementCS.initOperations(_DetailCS__NamedElementCS);
			Fragments._DetailCS__OclAny.initOperations(_DetailCS__OclAny);
			Fragments._DetailCS__OclElement.initOperations(_DetailCS__OclElement);
			Fragments._DetailCS__Pivotable.initOperations(_DetailCS__Pivotable);
			Fragments._DetailCS__PivotableElementCS.initOperations(_DetailCS__PivotableElementCS);
			Fragments._DetailCS__VisitableCS.initOperations(_DetailCS__VisitableCS);

			Fragments._DocumentationCS__AnnotationElementCS.initOperations(_DocumentationCS__AnnotationElementCS);
			Fragments._DocumentationCS__DocumentationCS.initOperations(_DocumentationCS__DocumentationCS);
			Fragments._DocumentationCS__ElementCS.initOperations(_DocumentationCS__ElementCS);
			Fragments._DocumentationCS__ModelElementCS.initOperations(_DocumentationCS__ModelElementCS);
			Fragments._DocumentationCS__Nameable.initOperations(_DocumentationCS__Nameable);
			Fragments._DocumentationCS__NamedElementCS.initOperations(_DocumentationCS__NamedElementCS);
			Fragments._DocumentationCS__OclAny.initOperations(_DocumentationCS__OclAny);
			Fragments._DocumentationCS__OclElement.initOperations(_DocumentationCS__OclElement);
			Fragments._DocumentationCS__Pivotable.initOperations(_DocumentationCS__Pivotable);
			Fragments._DocumentationCS__PivotableElementCS.initOperations(_DocumentationCS__PivotableElementCS);
			Fragments._DocumentationCS__VisitableCS.initOperations(_DocumentationCS__VisitableCS);

			Fragments._ElementCS__ElementCS.initOperations(_ElementCS__ElementCS);
			Fragments._ElementCS__OclAny.initOperations(_ElementCS__OclAny);
			Fragments._ElementCS__OclElement.initOperations(_ElementCS__OclElement);
			Fragments._ElementCS__VisitableCS.initOperations(_ElementCS__VisitableCS);

			Fragments._ElementRefCS__ElementCS.initOperations(_ElementRefCS__ElementCS);
			Fragments._ElementRefCS__ElementRefCS.initOperations(_ElementRefCS__ElementRefCS);
			Fragments._ElementRefCS__OclAny.initOperations(_ElementRefCS__OclAny);
			Fragments._ElementRefCS__OclElement.initOperations(_ElementRefCS__OclElement);
			Fragments._ElementRefCS__Pivotable.initOperations(_ElementRefCS__Pivotable);
			Fragments._ElementRefCS__PivotableElementCS.initOperations(_ElementRefCS__PivotableElementCS);
			Fragments._ElementRefCS__VisitableCS.initOperations(_ElementRefCS__VisitableCS);

			Fragments._EnumerationCS__ClassCS.initOperations(_EnumerationCS__ClassCS);
			Fragments._EnumerationCS__ElementCS.initOperations(_EnumerationCS__ElementCS);
			Fragments._EnumerationCS__EnumerationCS.initOperations(_EnumerationCS__EnumerationCS);
			Fragments._EnumerationCS__ModelElementCS.initOperations(_EnumerationCS__ModelElementCS);
			Fragments._EnumerationCS__Nameable.initOperations(_EnumerationCS__Nameable);
			Fragments._EnumerationCS__NamedElementCS.initOperations(_EnumerationCS__NamedElementCS);
			Fragments._EnumerationCS__NamespaceCS.initOperations(_EnumerationCS__NamespaceCS);
			Fragments._EnumerationCS__OclAny.initOperations(_EnumerationCS__OclAny);
			Fragments._EnumerationCS__OclElement.initOperations(_EnumerationCS__OclElement);
			Fragments._EnumerationCS__Pivotable.initOperations(_EnumerationCS__Pivotable);
			Fragments._EnumerationCS__PivotableElementCS.initOperations(_EnumerationCS__PivotableElementCS);
			Fragments._EnumerationCS__TemplateableElementCS.initOperations(_EnumerationCS__TemplateableElementCS);
			Fragments._EnumerationCS__TypeCS.initOperations(_EnumerationCS__TypeCS);
			Fragments._EnumerationCS__VisitableCS.initOperations(_EnumerationCS__VisitableCS);

			Fragments._EnumerationLiteralCS__ElementCS.initOperations(_EnumerationLiteralCS__ElementCS);
			Fragments._EnumerationLiteralCS__EnumerationLiteralCS.initOperations(_EnumerationLiteralCS__EnumerationLiteralCS);
			Fragments._EnumerationLiteralCS__ModelElementCS.initOperations(_EnumerationLiteralCS__ModelElementCS);
			Fragments._EnumerationLiteralCS__Nameable.initOperations(_EnumerationLiteralCS__Nameable);
			Fragments._EnumerationLiteralCS__NamedElementCS.initOperations(_EnumerationLiteralCS__NamedElementCS);
			Fragments._EnumerationLiteralCS__OclAny.initOperations(_EnumerationLiteralCS__OclAny);
			Fragments._EnumerationLiteralCS__OclElement.initOperations(_EnumerationLiteralCS__OclElement);
			Fragments._EnumerationLiteralCS__Pivotable.initOperations(_EnumerationLiteralCS__Pivotable);
			Fragments._EnumerationLiteralCS__PivotableElementCS.initOperations(_EnumerationLiteralCS__PivotableElementCS);
			Fragments._EnumerationLiteralCS__VisitableCS.initOperations(_EnumerationLiteralCS__VisitableCS);

			Fragments._FeatureCS__ElementCS.initOperations(_FeatureCS__ElementCS);
			Fragments._FeatureCS__FeatureCS.initOperations(_FeatureCS__FeatureCS);
			Fragments._FeatureCS__ModelElementCS.initOperations(_FeatureCS__ModelElementCS);
			Fragments._FeatureCS__Nameable.initOperations(_FeatureCS__Nameable);
			Fragments._FeatureCS__NamedElementCS.initOperations(_FeatureCS__NamedElementCS);
			Fragments._FeatureCS__OclAny.initOperations(_FeatureCS__OclAny);
			Fragments._FeatureCS__OclElement.initOperations(_FeatureCS__OclElement);
			Fragments._FeatureCS__Pivotable.initOperations(_FeatureCS__Pivotable);
			Fragments._FeatureCS__PivotableElementCS.initOperations(_FeatureCS__PivotableElementCS);
			Fragments._FeatureCS__TypedElementCS.initOperations(_FeatureCS__TypedElementCS);
			Fragments._FeatureCS__VisitableCS.initOperations(_FeatureCS__VisitableCS);

			Fragments._ImportCS__ElementCS.initOperations(_ImportCS__ElementCS);
			Fragments._ImportCS__ImportCS.initOperations(_ImportCS__ImportCS);
			Fragments._ImportCS__ModelElementCS.initOperations(_ImportCS__ModelElementCS);
			Fragments._ImportCS__Nameable.initOperations(_ImportCS__Nameable);
			Fragments._ImportCS__NamedElementCS.initOperations(_ImportCS__NamedElementCS);
			Fragments._ImportCS__NamespaceCS.initOperations(_ImportCS__NamespaceCS);
			Fragments._ImportCS__OclAny.initOperations(_ImportCS__OclAny);
			Fragments._ImportCS__OclElement.initOperations(_ImportCS__OclElement);
			Fragments._ImportCS__Pivotable.initOperations(_ImportCS__Pivotable);
			Fragments._ImportCS__PivotableElementCS.initOperations(_ImportCS__PivotableElementCS);
			Fragments._ImportCS__VisitableCS.initOperations(_ImportCS__VisitableCS);

			Fragments._LambdaTypeCS__ElementCS.initOperations(_LambdaTypeCS__ElementCS);
			Fragments._LambdaTypeCS__ElementRefCS.initOperations(_LambdaTypeCS__ElementRefCS);
			Fragments._LambdaTypeCS__LambdaTypeCS.initOperations(_LambdaTypeCS__LambdaTypeCS);
			Fragments._LambdaTypeCS__Nameable.initOperations(_LambdaTypeCS__Nameable);
			Fragments._LambdaTypeCS__OclAny.initOperations(_LambdaTypeCS__OclAny);
			Fragments._LambdaTypeCS__OclElement.initOperations(_LambdaTypeCS__OclElement);
			Fragments._LambdaTypeCS__Pivotable.initOperations(_LambdaTypeCS__Pivotable);
			Fragments._LambdaTypeCS__PivotableElementCS.initOperations(_LambdaTypeCS__PivotableElementCS);
			Fragments._LambdaTypeCS__TemplateableElementCS.initOperations(_LambdaTypeCS__TemplateableElementCS);
			Fragments._LambdaTypeCS__TypeRefCS.initOperations(_LambdaTypeCS__TypeRefCS);
			Fragments._LambdaTypeCS__TypedRefCS.initOperations(_LambdaTypeCS__TypedRefCS);
			Fragments._LambdaTypeCS__VisitableCS.initOperations(_LambdaTypeCS__VisitableCS);

			Fragments._ModelElementCS__ElementCS.initOperations(_ModelElementCS__ElementCS);
			Fragments._ModelElementCS__ModelElementCS.initOperations(_ModelElementCS__ModelElementCS);
			Fragments._ModelElementCS__OclAny.initOperations(_ModelElementCS__OclAny);
			Fragments._ModelElementCS__OclElement.initOperations(_ModelElementCS__OclElement);
			Fragments._ModelElementCS__Pivotable.initOperations(_ModelElementCS__Pivotable);
			Fragments._ModelElementCS__PivotableElementCS.initOperations(_ModelElementCS__PivotableElementCS);
			Fragments._ModelElementCS__VisitableCS.initOperations(_ModelElementCS__VisitableCS);

			Fragments._ModelElementRefCS__ElementCS.initOperations(_ModelElementRefCS__ElementCS);
			Fragments._ModelElementRefCS__ElementRefCS.initOperations(_ModelElementRefCS__ElementRefCS);
			Fragments._ModelElementRefCS__ModelElementRefCS.initOperations(_ModelElementRefCS__ModelElementRefCS);
			Fragments._ModelElementRefCS__OclAny.initOperations(_ModelElementRefCS__OclAny);
			Fragments._ModelElementRefCS__OclElement.initOperations(_ModelElementRefCS__OclElement);
			Fragments._ModelElementRefCS__Pivotable.initOperations(_ModelElementRefCS__Pivotable);
			Fragments._ModelElementRefCS__PivotableElementCS.initOperations(_ModelElementRefCS__PivotableElementCS);
			Fragments._ModelElementRefCS__VisitableCS.initOperations(_ModelElementRefCS__VisitableCS);

			Fragments._MultiplicityBoundsCS__ElementCS.initOperations(_MultiplicityBoundsCS__ElementCS);
			Fragments._MultiplicityBoundsCS__MultiplicityBoundsCS.initOperations(_MultiplicityBoundsCS__MultiplicityBoundsCS);
			Fragments._MultiplicityBoundsCS__MultiplicityCS.initOperations(_MultiplicityBoundsCS__MultiplicityCS);
			Fragments._MultiplicityBoundsCS__OclAny.initOperations(_MultiplicityBoundsCS__OclAny);
			Fragments._MultiplicityBoundsCS__OclElement.initOperations(_MultiplicityBoundsCS__OclElement);
			Fragments._MultiplicityBoundsCS__VisitableCS.initOperations(_MultiplicityBoundsCS__VisitableCS);

			Fragments._MultiplicityCS__ElementCS.initOperations(_MultiplicityCS__ElementCS);
			Fragments._MultiplicityCS__MultiplicityCS.initOperations(_MultiplicityCS__MultiplicityCS);
			Fragments._MultiplicityCS__OclAny.initOperations(_MultiplicityCS__OclAny);
			Fragments._MultiplicityCS__OclElement.initOperations(_MultiplicityCS__OclElement);
			Fragments._MultiplicityCS__VisitableCS.initOperations(_MultiplicityCS__VisitableCS);

			Fragments._MultiplicityStringCS__ElementCS.initOperations(_MultiplicityStringCS__ElementCS);
			Fragments._MultiplicityStringCS__MultiplicityCS.initOperations(_MultiplicityStringCS__MultiplicityCS);
			Fragments._MultiplicityStringCS__MultiplicityStringCS.initOperations(_MultiplicityStringCS__MultiplicityStringCS);
			Fragments._MultiplicityStringCS__OclAny.initOperations(_MultiplicityStringCS__OclAny);
			Fragments._MultiplicityStringCS__OclElement.initOperations(_MultiplicityStringCS__OclElement);
			Fragments._MultiplicityStringCS__VisitableCS.initOperations(_MultiplicityStringCS__VisitableCS);

			Fragments._NamedElementCS__ElementCS.initOperations(_NamedElementCS__ElementCS);
			Fragments._NamedElementCS__ModelElementCS.initOperations(_NamedElementCS__ModelElementCS);
			Fragments._NamedElementCS__Nameable.initOperations(_NamedElementCS__Nameable);
			Fragments._NamedElementCS__NamedElementCS.initOperations(_NamedElementCS__NamedElementCS);
			Fragments._NamedElementCS__OclAny.initOperations(_NamedElementCS__OclAny);
			Fragments._NamedElementCS__OclElement.initOperations(_NamedElementCS__OclElement);
			Fragments._NamedElementCS__Pivotable.initOperations(_NamedElementCS__Pivotable);
			Fragments._NamedElementCS__PivotableElementCS.initOperations(_NamedElementCS__PivotableElementCS);
			Fragments._NamedElementCS__VisitableCS.initOperations(_NamedElementCS__VisitableCS);

			Fragments._NamespaceCS__ElementCS.initOperations(_NamespaceCS__ElementCS);
			Fragments._NamespaceCS__ModelElementCS.initOperations(_NamespaceCS__ModelElementCS);
			Fragments._NamespaceCS__Nameable.initOperations(_NamespaceCS__Nameable);
			Fragments._NamespaceCS__NamedElementCS.initOperations(_NamespaceCS__NamedElementCS);
			Fragments._NamespaceCS__NamespaceCS.initOperations(_NamespaceCS__NamespaceCS);
			Fragments._NamespaceCS__OclAny.initOperations(_NamespaceCS__OclAny);
			Fragments._NamespaceCS__OclElement.initOperations(_NamespaceCS__OclElement);
			Fragments._NamespaceCS__Pivotable.initOperations(_NamespaceCS__Pivotable);
			Fragments._NamespaceCS__PivotableElementCS.initOperations(_NamespaceCS__PivotableElementCS);
			Fragments._NamespaceCS__VisitableCS.initOperations(_NamespaceCS__VisitableCS);

			Fragments._OperationCS__ElementCS.initOperations(_OperationCS__ElementCS);
			Fragments._OperationCS__FeatureCS.initOperations(_OperationCS__FeatureCS);
			Fragments._OperationCS__ModelElementCS.initOperations(_OperationCS__ModelElementCS);
			Fragments._OperationCS__Nameable.initOperations(_OperationCS__Nameable);
			Fragments._OperationCS__NamedElementCS.initOperations(_OperationCS__NamedElementCS);
			Fragments._OperationCS__OclAny.initOperations(_OperationCS__OclAny);
			Fragments._OperationCS__OclElement.initOperations(_OperationCS__OclElement);
			Fragments._OperationCS__OperationCS.initOperations(_OperationCS__OperationCS);
			Fragments._OperationCS__Pivotable.initOperations(_OperationCS__Pivotable);
			Fragments._OperationCS__PivotableElementCS.initOperations(_OperationCS__PivotableElementCS);
			Fragments._OperationCS__TemplateableElementCS.initOperations(_OperationCS__TemplateableElementCS);
			Fragments._OperationCS__TypedElementCS.initOperations(_OperationCS__TypedElementCS);
			Fragments._OperationCS__VisitableCS.initOperations(_OperationCS__VisitableCS);

			Fragments._PackageCS__ElementCS.initOperations(_PackageCS__ElementCS);
			Fragments._PackageCS__ModelElementCS.initOperations(_PackageCS__ModelElementCS);
			Fragments._PackageCS__Nameable.initOperations(_PackageCS__Nameable);
			Fragments._PackageCS__NamedElementCS.initOperations(_PackageCS__NamedElementCS);
			Fragments._PackageCS__NamespaceCS.initOperations(_PackageCS__NamespaceCS);
			Fragments._PackageCS__OclAny.initOperations(_PackageCS__OclAny);
			Fragments._PackageCS__OclElement.initOperations(_PackageCS__OclElement);
			Fragments._PackageCS__PackageCS.initOperations(_PackageCS__PackageCS);
			Fragments._PackageCS__PackageOwnerCS.initOperations(_PackageCS__PackageOwnerCS);
			Fragments._PackageCS__Pivotable.initOperations(_PackageCS__Pivotable);
			Fragments._PackageCS__PivotableElementCS.initOperations(_PackageCS__PivotableElementCS);
			Fragments._PackageCS__VisitableCS.initOperations(_PackageCS__VisitableCS);

			Fragments._PackageOwnerCS__ElementCS.initOperations(_PackageOwnerCS__ElementCS);
			Fragments._PackageOwnerCS__ModelElementCS.initOperations(_PackageOwnerCS__ModelElementCS);
			Fragments._PackageOwnerCS__OclAny.initOperations(_PackageOwnerCS__OclAny);
			Fragments._PackageOwnerCS__OclElement.initOperations(_PackageOwnerCS__OclElement);
			Fragments._PackageOwnerCS__PackageOwnerCS.initOperations(_PackageOwnerCS__PackageOwnerCS);
			Fragments._PackageOwnerCS__Pivotable.initOperations(_PackageOwnerCS__Pivotable);
			Fragments._PackageOwnerCS__PivotableElementCS.initOperations(_PackageOwnerCS__PivotableElementCS);
			Fragments._PackageOwnerCS__VisitableCS.initOperations(_PackageOwnerCS__VisitableCS);

			Fragments._ParameterCS__ElementCS.initOperations(_ParameterCS__ElementCS);
			Fragments._ParameterCS__ModelElementCS.initOperations(_ParameterCS__ModelElementCS);
			Fragments._ParameterCS__Nameable.initOperations(_ParameterCS__Nameable);
			Fragments._ParameterCS__NamedElementCS.initOperations(_ParameterCS__NamedElementCS);
			Fragments._ParameterCS__OclAny.initOperations(_ParameterCS__OclAny);
			Fragments._ParameterCS__OclElement.initOperations(_ParameterCS__OclElement);
			Fragments._ParameterCS__ParameterCS.initOperations(_ParameterCS__ParameterCS);
			Fragments._ParameterCS__Pivotable.initOperations(_ParameterCS__Pivotable);
			Fragments._ParameterCS__PivotableElementCS.initOperations(_ParameterCS__PivotableElementCS);
			Fragments._ParameterCS__TypedElementCS.initOperations(_ParameterCS__TypedElementCS);
			Fragments._ParameterCS__VisitableCS.initOperations(_ParameterCS__VisitableCS);

			Fragments._PathElementCS__ElementCS.initOperations(_PathElementCS__ElementCS);
			Fragments._PathElementCS__OclAny.initOperations(_PathElementCS__OclAny);
			Fragments._PathElementCS__OclElement.initOperations(_PathElementCS__OclElement);
			Fragments._PathElementCS__PathElementCS.initOperations(_PathElementCS__PathElementCS);
			Fragments._PathElementCS__Pivotable.initOperations(_PathElementCS__Pivotable);
			Fragments._PathElementCS__VisitableCS.initOperations(_PathElementCS__VisitableCS);

			Fragments._PathElementWithURICS__ElementCS.initOperations(_PathElementWithURICS__ElementCS);
			Fragments._PathElementWithURICS__OclAny.initOperations(_PathElementWithURICS__OclAny);
			Fragments._PathElementWithURICS__OclElement.initOperations(_PathElementWithURICS__OclElement);
			Fragments._PathElementWithURICS__PathElementCS.initOperations(_PathElementWithURICS__PathElementCS);
			Fragments._PathElementWithURICS__PathElementWithURICS.initOperations(_PathElementWithURICS__PathElementWithURICS);
			Fragments._PathElementWithURICS__Pivotable.initOperations(_PathElementWithURICS__Pivotable);
			Fragments._PathElementWithURICS__VisitableCS.initOperations(_PathElementWithURICS__VisitableCS);

			Fragments._PathNameCS__ElementCS.initOperations(_PathNameCS__ElementCS);
			Fragments._PathNameCS__OclAny.initOperations(_PathNameCS__OclAny);
			Fragments._PathNameCS__OclElement.initOperations(_PathNameCS__OclElement);
			Fragments._PathNameCS__PathNameCS.initOperations(_PathNameCS__PathNameCS);
			Fragments._PathNameCS__Pivotable.initOperations(_PathNameCS__Pivotable);
			Fragments._PathNameCS__VisitableCS.initOperations(_PathNameCS__VisitableCS);

			Fragments._PivotableElementCS__ElementCS.initOperations(_PivotableElementCS__ElementCS);
			Fragments._PivotableElementCS__OclAny.initOperations(_PivotableElementCS__OclAny);
			Fragments._PivotableElementCS__OclElement.initOperations(_PivotableElementCS__OclElement);
			Fragments._PivotableElementCS__Pivotable.initOperations(_PivotableElementCS__Pivotable);
			Fragments._PivotableElementCS__PivotableElementCS.initOperations(_PivotableElementCS__PivotableElementCS);
			Fragments._PivotableElementCS__VisitableCS.initOperations(_PivotableElementCS__VisitableCS);

			Fragments._PrimitiveTypeRefCS__ElementCS.initOperations(_PrimitiveTypeRefCS__ElementCS);
			Fragments._PrimitiveTypeRefCS__ElementRefCS.initOperations(_PrimitiveTypeRefCS__ElementRefCS);
			Fragments._PrimitiveTypeRefCS__Nameable.initOperations(_PrimitiveTypeRefCS__Nameable);
			Fragments._PrimitiveTypeRefCS__OclAny.initOperations(_PrimitiveTypeRefCS__OclAny);
			Fragments._PrimitiveTypeRefCS__OclElement.initOperations(_PrimitiveTypeRefCS__OclElement);
			Fragments._PrimitiveTypeRefCS__Pivotable.initOperations(_PrimitiveTypeRefCS__Pivotable);
			Fragments._PrimitiveTypeRefCS__PivotableElementCS.initOperations(_PrimitiveTypeRefCS__PivotableElementCS);
			Fragments._PrimitiveTypeRefCS__PrimitiveTypeRefCS.initOperations(_PrimitiveTypeRefCS__PrimitiveTypeRefCS);
			Fragments._PrimitiveTypeRefCS__TypeRefCS.initOperations(_PrimitiveTypeRefCS__TypeRefCS);
			Fragments._PrimitiveTypeRefCS__TypedRefCS.initOperations(_PrimitiveTypeRefCS__TypedRefCS);
			Fragments._PrimitiveTypeRefCS__VisitableCS.initOperations(_PrimitiveTypeRefCS__VisitableCS);

			Fragments._ReferenceCS__ElementCS.initOperations(_ReferenceCS__ElementCS);
			Fragments._ReferenceCS__FeatureCS.initOperations(_ReferenceCS__FeatureCS);
			Fragments._ReferenceCS__ModelElementCS.initOperations(_ReferenceCS__ModelElementCS);
			Fragments._ReferenceCS__Nameable.initOperations(_ReferenceCS__Nameable);
			Fragments._ReferenceCS__NamedElementCS.initOperations(_ReferenceCS__NamedElementCS);
			Fragments._ReferenceCS__OclAny.initOperations(_ReferenceCS__OclAny);
			Fragments._ReferenceCS__OclElement.initOperations(_ReferenceCS__OclElement);
			Fragments._ReferenceCS__Pivotable.initOperations(_ReferenceCS__Pivotable);
			Fragments._ReferenceCS__PivotableElementCS.initOperations(_ReferenceCS__PivotableElementCS);
			Fragments._ReferenceCS__ReferenceCS.initOperations(_ReferenceCS__ReferenceCS);
			Fragments._ReferenceCS__StructuralFeatureCS.initOperations(_ReferenceCS__StructuralFeatureCS);
			Fragments._ReferenceCS__TypedElementCS.initOperations(_ReferenceCS__TypedElementCS);
			Fragments._ReferenceCS__VisitableCS.initOperations(_ReferenceCS__VisitableCS);

			Fragments._RootCS__ElementCS.initOperations(_RootCS__ElementCS);
			Fragments._RootCS__ModelElementCS.initOperations(_RootCS__ModelElementCS);
			Fragments._RootCS__OclAny.initOperations(_RootCS__OclAny);
			Fragments._RootCS__OclElement.initOperations(_RootCS__OclElement);
			Fragments._RootCS__Pivotable.initOperations(_RootCS__Pivotable);
			Fragments._RootCS__PivotableElementCS.initOperations(_RootCS__PivotableElementCS);
			Fragments._RootCS__RootCS.initOperations(_RootCS__RootCS);
			Fragments._RootCS__VisitableCS.initOperations(_RootCS__VisitableCS);

			Fragments._RootPackageCS__ElementCS.initOperations(_RootPackageCS__ElementCS);
			Fragments._RootPackageCS__ModelElementCS.initOperations(_RootPackageCS__ModelElementCS);
			Fragments._RootPackageCS__OclAny.initOperations(_RootPackageCS__OclAny);
			Fragments._RootPackageCS__OclElement.initOperations(_RootPackageCS__OclElement);
			Fragments._RootPackageCS__PackageOwnerCS.initOperations(_RootPackageCS__PackageOwnerCS);
			Fragments._RootPackageCS__Pivotable.initOperations(_RootPackageCS__Pivotable);
			Fragments._RootPackageCS__PivotableElementCS.initOperations(_RootPackageCS__PivotableElementCS);
			Fragments._RootPackageCS__RootCS.initOperations(_RootPackageCS__RootCS);
			Fragments._RootPackageCS__RootPackageCS.initOperations(_RootPackageCS__RootPackageCS);
			Fragments._RootPackageCS__VisitableCS.initOperations(_RootPackageCS__VisitableCS);

			Fragments._ScopeFilter__OclAny.initOperations(_ScopeFilter__OclAny);
			Fragments._ScopeFilter__ScopeFilter.initOperations(_ScopeFilter__ScopeFilter);

			Fragments._SpecificationCS__ElementCS.initOperations(_SpecificationCS__ElementCS);
			Fragments._SpecificationCS__ModelElementCS.initOperations(_SpecificationCS__ModelElementCS);
			Fragments._SpecificationCS__OclAny.initOperations(_SpecificationCS__OclAny);
			Fragments._SpecificationCS__OclElement.initOperations(_SpecificationCS__OclElement);
			Fragments._SpecificationCS__Pivotable.initOperations(_SpecificationCS__Pivotable);
			Fragments._SpecificationCS__PivotableElementCS.initOperations(_SpecificationCS__PivotableElementCS);
			Fragments._SpecificationCS__SpecificationCS.initOperations(_SpecificationCS__SpecificationCS);
			Fragments._SpecificationCS__VisitableCS.initOperations(_SpecificationCS__VisitableCS);

			Fragments._StructuralFeatureCS__ElementCS.initOperations(_StructuralFeatureCS__ElementCS);
			Fragments._StructuralFeatureCS__FeatureCS.initOperations(_StructuralFeatureCS__FeatureCS);
			Fragments._StructuralFeatureCS__ModelElementCS.initOperations(_StructuralFeatureCS__ModelElementCS);
			Fragments._StructuralFeatureCS__Nameable.initOperations(_StructuralFeatureCS__Nameable);
			Fragments._StructuralFeatureCS__NamedElementCS.initOperations(_StructuralFeatureCS__NamedElementCS);
			Fragments._StructuralFeatureCS__OclAny.initOperations(_StructuralFeatureCS__OclAny);
			Fragments._StructuralFeatureCS__OclElement.initOperations(_StructuralFeatureCS__OclElement);
			Fragments._StructuralFeatureCS__Pivotable.initOperations(_StructuralFeatureCS__Pivotable);
			Fragments._StructuralFeatureCS__PivotableElementCS.initOperations(_StructuralFeatureCS__PivotableElementCS);
			Fragments._StructuralFeatureCS__StructuralFeatureCS.initOperations(_StructuralFeatureCS__StructuralFeatureCS);
			Fragments._StructuralFeatureCS__TypedElementCS.initOperations(_StructuralFeatureCS__TypedElementCS);
			Fragments._StructuralFeatureCS__VisitableCS.initOperations(_StructuralFeatureCS__VisitableCS);

			Fragments._StructuredClassCS__ClassCS.initOperations(_StructuredClassCS__ClassCS);
			Fragments._StructuredClassCS__ElementCS.initOperations(_StructuredClassCS__ElementCS);
			Fragments._StructuredClassCS__ModelElementCS.initOperations(_StructuredClassCS__ModelElementCS);
			Fragments._StructuredClassCS__Nameable.initOperations(_StructuredClassCS__Nameable);
			Fragments._StructuredClassCS__NamedElementCS.initOperations(_StructuredClassCS__NamedElementCS);
			Fragments._StructuredClassCS__NamespaceCS.initOperations(_StructuredClassCS__NamespaceCS);
			Fragments._StructuredClassCS__OclAny.initOperations(_StructuredClassCS__OclAny);
			Fragments._StructuredClassCS__OclElement.initOperations(_StructuredClassCS__OclElement);
			Fragments._StructuredClassCS__Pivotable.initOperations(_StructuredClassCS__Pivotable);
			Fragments._StructuredClassCS__PivotableElementCS.initOperations(_StructuredClassCS__PivotableElementCS);
			Fragments._StructuredClassCS__StructuredClassCS.initOperations(_StructuredClassCS__StructuredClassCS);
			Fragments._StructuredClassCS__TemplateableElementCS.initOperations(_StructuredClassCS__TemplateableElementCS);
			Fragments._StructuredClassCS__TypeCS.initOperations(_StructuredClassCS__TypeCS);
			Fragments._StructuredClassCS__VisitableCS.initOperations(_StructuredClassCS__VisitableCS);

			Fragments._TemplateBindingCS__ElementCS.initOperations(_TemplateBindingCS__ElementCS);
			Fragments._TemplateBindingCS__ElementRefCS.initOperations(_TemplateBindingCS__ElementRefCS);
			Fragments._TemplateBindingCS__OclAny.initOperations(_TemplateBindingCS__OclAny);
			Fragments._TemplateBindingCS__OclElement.initOperations(_TemplateBindingCS__OclElement);
			Fragments._TemplateBindingCS__Pivotable.initOperations(_TemplateBindingCS__Pivotable);
			Fragments._TemplateBindingCS__PivotableElementCS.initOperations(_TemplateBindingCS__PivotableElementCS);
			Fragments._TemplateBindingCS__TemplateBindingCS.initOperations(_TemplateBindingCS__TemplateBindingCS);
			Fragments._TemplateBindingCS__VisitableCS.initOperations(_TemplateBindingCS__VisitableCS);

			Fragments._TemplateParameterCS__ElementCS.initOperations(_TemplateParameterCS__ElementCS);
			Fragments._TemplateParameterCS__ModelElementCS.initOperations(_TemplateParameterCS__ModelElementCS);
			Fragments._TemplateParameterCS__Nameable.initOperations(_TemplateParameterCS__Nameable);
			Fragments._TemplateParameterCS__NamedElementCS.initOperations(_TemplateParameterCS__NamedElementCS);
			Fragments._TemplateParameterCS__OclAny.initOperations(_TemplateParameterCS__OclAny);
			Fragments._TemplateParameterCS__OclElement.initOperations(_TemplateParameterCS__OclElement);
			Fragments._TemplateParameterCS__Pivotable.initOperations(_TemplateParameterCS__Pivotable);
			Fragments._TemplateParameterCS__PivotableElementCS.initOperations(_TemplateParameterCS__PivotableElementCS);
			Fragments._TemplateParameterCS__TemplateParameterCS.initOperations(_TemplateParameterCS__TemplateParameterCS);
			Fragments._TemplateParameterCS__VisitableCS.initOperations(_TemplateParameterCS__VisitableCS);

			Fragments._TemplateParameterSubstitutionCS__ElementCS.initOperations(_TemplateParameterSubstitutionCS__ElementCS);
			Fragments._TemplateParameterSubstitutionCS__ModelElementCS.initOperations(_TemplateParameterSubstitutionCS__ModelElementCS);
			Fragments._TemplateParameterSubstitutionCS__OclAny.initOperations(_TemplateParameterSubstitutionCS__OclAny);
			Fragments._TemplateParameterSubstitutionCS__OclElement.initOperations(_TemplateParameterSubstitutionCS__OclElement);
			Fragments._TemplateParameterSubstitutionCS__Pivotable.initOperations(_TemplateParameterSubstitutionCS__Pivotable);
			Fragments._TemplateParameterSubstitutionCS__PivotableElementCS.initOperations(_TemplateParameterSubstitutionCS__PivotableElementCS);
			Fragments._TemplateParameterSubstitutionCS__TemplateParameterSubstitutionCS.initOperations(_TemplateParameterSubstitutionCS__TemplateParameterSubstitutionCS);
			Fragments._TemplateParameterSubstitutionCS__VisitableCS.initOperations(_TemplateParameterSubstitutionCS__VisitableCS);

			Fragments._TemplateSignatureCS__ElementCS.initOperations(_TemplateSignatureCS__ElementCS);
			Fragments._TemplateSignatureCS__ModelElementCS.initOperations(_TemplateSignatureCS__ModelElementCS);
			Fragments._TemplateSignatureCS__OclAny.initOperations(_TemplateSignatureCS__OclAny);
			Fragments._TemplateSignatureCS__OclElement.initOperations(_TemplateSignatureCS__OclElement);
			Fragments._TemplateSignatureCS__Pivotable.initOperations(_TemplateSignatureCS__Pivotable);
			Fragments._TemplateSignatureCS__PivotableElementCS.initOperations(_TemplateSignatureCS__PivotableElementCS);
			Fragments._TemplateSignatureCS__TemplateSignatureCS.initOperations(_TemplateSignatureCS__TemplateSignatureCS);
			Fragments._TemplateSignatureCS__VisitableCS.initOperations(_TemplateSignatureCS__VisitableCS);

			Fragments._TemplateableElementCS__ElementCS.initOperations(_TemplateableElementCS__ElementCS);
			Fragments._TemplateableElementCS__OclAny.initOperations(_TemplateableElementCS__OclAny);
			Fragments._TemplateableElementCS__OclElement.initOperations(_TemplateableElementCS__OclElement);
			Fragments._TemplateableElementCS__TemplateableElementCS.initOperations(_TemplateableElementCS__TemplateableElementCS);
			Fragments._TemplateableElementCS__VisitableCS.initOperations(_TemplateableElementCS__VisitableCS);

			Fragments._TuplePartCS__ElementCS.initOperations(_TuplePartCS__ElementCS);
			Fragments._TuplePartCS__ModelElementCS.initOperations(_TuplePartCS__ModelElementCS);
			Fragments._TuplePartCS__Nameable.initOperations(_TuplePartCS__Nameable);
			Fragments._TuplePartCS__NamedElementCS.initOperations(_TuplePartCS__NamedElementCS);
			Fragments._TuplePartCS__OclAny.initOperations(_TuplePartCS__OclAny);
			Fragments._TuplePartCS__OclElement.initOperations(_TuplePartCS__OclElement);
			Fragments._TuplePartCS__Pivotable.initOperations(_TuplePartCS__Pivotable);
			Fragments._TuplePartCS__PivotableElementCS.initOperations(_TuplePartCS__PivotableElementCS);
			Fragments._TuplePartCS__TuplePartCS.initOperations(_TuplePartCS__TuplePartCS);
			Fragments._TuplePartCS__TypedElementCS.initOperations(_TuplePartCS__TypedElementCS);
			Fragments._TuplePartCS__VisitableCS.initOperations(_TuplePartCS__VisitableCS);

			Fragments._TupleTypeCS__ElementCS.initOperations(_TupleTypeCS__ElementCS);
			Fragments._TupleTypeCS__ElementRefCS.initOperations(_TupleTypeCS__ElementRefCS);
			Fragments._TupleTypeCS__Nameable.initOperations(_TupleTypeCS__Nameable);
			Fragments._TupleTypeCS__OclAny.initOperations(_TupleTypeCS__OclAny);
			Fragments._TupleTypeCS__OclElement.initOperations(_TupleTypeCS__OclElement);
			Fragments._TupleTypeCS__Pivotable.initOperations(_TupleTypeCS__Pivotable);
			Fragments._TupleTypeCS__PivotableElementCS.initOperations(_TupleTypeCS__PivotableElementCS);
			Fragments._TupleTypeCS__TupleTypeCS.initOperations(_TupleTypeCS__TupleTypeCS);
			Fragments._TupleTypeCS__TypeRefCS.initOperations(_TupleTypeCS__TypeRefCS);
			Fragments._TupleTypeCS__TypedRefCS.initOperations(_TupleTypeCS__TypedRefCS);
			Fragments._TupleTypeCS__VisitableCS.initOperations(_TupleTypeCS__VisitableCS);

			Fragments._TypeCS__ElementCS.initOperations(_TypeCS__ElementCS);
			Fragments._TypeCS__ModelElementCS.initOperations(_TypeCS__ModelElementCS);
			Fragments._TypeCS__OclAny.initOperations(_TypeCS__OclAny);
			Fragments._TypeCS__OclElement.initOperations(_TypeCS__OclElement);
			Fragments._TypeCS__Pivotable.initOperations(_TypeCS__Pivotable);
			Fragments._TypeCS__PivotableElementCS.initOperations(_TypeCS__PivotableElementCS);
			Fragments._TypeCS__TypeCS.initOperations(_TypeCS__TypeCS);
			Fragments._TypeCS__VisitableCS.initOperations(_TypeCS__VisitableCS);

			Fragments._TypeParameterCS__ElementCS.initOperations(_TypeParameterCS__ElementCS);
			Fragments._TypeParameterCS__ModelElementCS.initOperations(_TypeParameterCS__ModelElementCS);
			Fragments._TypeParameterCS__Nameable.initOperations(_TypeParameterCS__Nameable);
			Fragments._TypeParameterCS__NamedElementCS.initOperations(_TypeParameterCS__NamedElementCS);
			Fragments._TypeParameterCS__OclAny.initOperations(_TypeParameterCS__OclAny);
			Fragments._TypeParameterCS__OclElement.initOperations(_TypeParameterCS__OclElement);
			Fragments._TypeParameterCS__Pivotable.initOperations(_TypeParameterCS__Pivotable);
			Fragments._TypeParameterCS__PivotableElementCS.initOperations(_TypeParameterCS__PivotableElementCS);
			Fragments._TypeParameterCS__TemplateParameterCS.initOperations(_TypeParameterCS__TemplateParameterCS);
			Fragments._TypeParameterCS__TypeCS.initOperations(_TypeParameterCS__TypeCS);
			Fragments._TypeParameterCS__TypeParameterCS.initOperations(_TypeParameterCS__TypeParameterCS);
			Fragments._TypeParameterCS__VisitableCS.initOperations(_TypeParameterCS__VisitableCS);

			Fragments._TypeRefCS__ElementCS.initOperations(_TypeRefCS__ElementCS);
			Fragments._TypeRefCS__ElementRefCS.initOperations(_TypeRefCS__ElementRefCS);
			Fragments._TypeRefCS__OclAny.initOperations(_TypeRefCS__OclAny);
			Fragments._TypeRefCS__OclElement.initOperations(_TypeRefCS__OclElement);
			Fragments._TypeRefCS__Pivotable.initOperations(_TypeRefCS__Pivotable);
			Fragments._TypeRefCS__PivotableElementCS.initOperations(_TypeRefCS__PivotableElementCS);
			Fragments._TypeRefCS__TypeRefCS.initOperations(_TypeRefCS__TypeRefCS);
			Fragments._TypeRefCS__VisitableCS.initOperations(_TypeRefCS__VisitableCS);

			Fragments._TypedElementCS__ElementCS.initOperations(_TypedElementCS__ElementCS);
			Fragments._TypedElementCS__ModelElementCS.initOperations(_TypedElementCS__ModelElementCS);
			Fragments._TypedElementCS__Nameable.initOperations(_TypedElementCS__Nameable);
			Fragments._TypedElementCS__NamedElementCS.initOperations(_TypedElementCS__NamedElementCS);
			Fragments._TypedElementCS__OclAny.initOperations(_TypedElementCS__OclAny);
			Fragments._TypedElementCS__OclElement.initOperations(_TypedElementCS__OclElement);
			Fragments._TypedElementCS__Pivotable.initOperations(_TypedElementCS__Pivotable);
			Fragments._TypedElementCS__PivotableElementCS.initOperations(_TypedElementCS__PivotableElementCS);
			Fragments._TypedElementCS__TypedElementCS.initOperations(_TypedElementCS__TypedElementCS);
			Fragments._TypedElementCS__VisitableCS.initOperations(_TypedElementCS__VisitableCS);

			Fragments._TypedRefCS__ElementCS.initOperations(_TypedRefCS__ElementCS);
			Fragments._TypedRefCS__ElementRefCS.initOperations(_TypedRefCS__ElementRefCS);
			Fragments._TypedRefCS__OclAny.initOperations(_TypedRefCS__OclAny);
			Fragments._TypedRefCS__OclElement.initOperations(_TypedRefCS__OclElement);
			Fragments._TypedRefCS__Pivotable.initOperations(_TypedRefCS__Pivotable);
			Fragments._TypedRefCS__PivotableElementCS.initOperations(_TypedRefCS__PivotableElementCS);
			Fragments._TypedRefCS__TypeRefCS.initOperations(_TypedRefCS__TypeRefCS);
			Fragments._TypedRefCS__TypedRefCS.initOperations(_TypedRefCS__TypedRefCS);
			Fragments._TypedRefCS__VisitableCS.initOperations(_TypedRefCS__VisitableCS);

			Fragments._TypedTypeRefCS__ElementCS.initOperations(_TypedTypeRefCS__ElementCS);
			Fragments._TypedTypeRefCS__ElementRefCS.initOperations(_TypedTypeRefCS__ElementRefCS);
			Fragments._TypedTypeRefCS__OclAny.initOperations(_TypedTypeRefCS__OclAny);
			Fragments._TypedTypeRefCS__OclElement.initOperations(_TypedTypeRefCS__OclElement);
			Fragments._TypedTypeRefCS__Pivotable.initOperations(_TypedTypeRefCS__Pivotable);
			Fragments._TypedTypeRefCS__PivotableElementCS.initOperations(_TypedTypeRefCS__PivotableElementCS);
			Fragments._TypedTypeRefCS__TypeRefCS.initOperations(_TypedTypeRefCS__TypeRefCS);
			Fragments._TypedTypeRefCS__TypedRefCS.initOperations(_TypedTypeRefCS__TypedRefCS);
			Fragments._TypedTypeRefCS__TypedTypeRefCS.initOperations(_TypedTypeRefCS__TypedTypeRefCS);
			Fragments._TypedTypeRefCS__VisitableCS.initOperations(_TypedTypeRefCS__VisitableCS);

			Fragments._VisitableCS__OclAny.initOperations(_VisitableCS__OclAny);
			Fragments._VisitableCS__OclElement.initOperations(_VisitableCS__OclElement);
			Fragments._VisitableCS__VisitableCS.initOperations(_VisitableCS__VisitableCS);

			Fragments._WildcardTypeRefCS__ElementCS.initOperations(_WildcardTypeRefCS__ElementCS);
			Fragments._WildcardTypeRefCS__ElementRefCS.initOperations(_WildcardTypeRefCS__ElementRefCS);
			Fragments._WildcardTypeRefCS__OclAny.initOperations(_WildcardTypeRefCS__OclAny);
			Fragments._WildcardTypeRefCS__OclElement.initOperations(_WildcardTypeRefCS__OclElement);
			Fragments._WildcardTypeRefCS__Pivotable.initOperations(_WildcardTypeRefCS__Pivotable);
			Fragments._WildcardTypeRefCS__PivotableElementCS.initOperations(_WildcardTypeRefCS__PivotableElementCS);
			Fragments._WildcardTypeRefCS__TypeRefCS.initOperations(_WildcardTypeRefCS__TypeRefCS);
			Fragments._WildcardTypeRefCS__VisitableCS.initOperations(_WildcardTypeRefCS__VisitableCS);
			Fragments._WildcardTypeRefCS__WildcardTypeRefCS.initOperations(_WildcardTypeRefCS__WildcardTypeRefCS);

			Init.initEnd();
		}

		/**
		 * Force initialization of the fields of BaseCSTables::FragmentOperations and all preceding sub-packages.
		 */
		public static void init() {}
	}

	/**
	 *	The lists of local properties for the local fragment of each type.
	 */
	public static class FragmentProperties {
		static {
			Init.initStart();
			FragmentOperations.init();
		}

		private static final @NonNull ExecutorProperty @NonNull [] _AnnotationCS = {
			BaseCSTables.Properties._ElementCS__csi,
			BaseCSTables.Properties._NamedElementCS__name,
			BaseCSTables.Properties._ModelElementCS__originalXmiId,
			BaseCSTables.Properties._ModelElementCS__ownedAnnotations,
			BaseCSTables.Properties._AnnotationCS__ownedContents,
			BaseCSTables.Properties._AnnotationElementCS__ownedDetails,
			BaseCSTables.Properties._AnnotationCS__ownedReferences,
			BaseCSTables.Properties._ElementCS__parent,
			BaseCSTables.Properties._PivotableElementCS__pivot,
			BaseCSTables.Properties._ModelElementCS__AnnotationCS__ownedContents,
			BaseCSTables.Properties._ElementCS__ElementCS__parent,
			BaseCSTables.Properties._AnnotationElementCS__ModelElementCS__ownedAnnotations
		};

		private static final @NonNull ExecutorProperty @NonNull [] _AnnotationElementCS = {
			BaseCSTables.Properties._ElementCS__csi,
			BaseCSTables.Properties._NamedElementCS__name,
			BaseCSTables.Properties._ModelElementCS__originalXmiId,
			BaseCSTables.Properties._ModelElementCS__ownedAnnotations,
			BaseCSTables.Properties._AnnotationElementCS__ownedDetails,
			BaseCSTables.Properties._ElementCS__parent,
			BaseCSTables.Properties._PivotableElementCS__pivot,
			BaseCSTables.Properties._ModelElementCS__AnnotationCS__ownedContents,
			BaseCSTables.Properties._ElementCS__ElementCS__parent,
			BaseCSTables.Properties._AnnotationElementCS__ModelElementCS__ownedAnnotations
		};

		private static final @NonNull ExecutorProperty @NonNull [] _AttributeCS = {
			BaseCSTables.Properties._ElementCS__csi,
			BaseCSTables.Properties._StructuralFeatureCS__default,
			BaseCSTables.Properties._TypedElementCS__isOptional,
			BaseCSTables.Properties._NamedElementCS__name,
			BaseCSTables.Properties._ModelElementCS__originalXmiId,
			BaseCSTables.Properties._ModelElementCS__ownedAnnotations,
			BaseCSTables.Properties._StructuralFeatureCS__ownedDefaultExpressions,
			BaseCSTables.Properties._TypedElementCS__ownedType,
			BaseCSTables.Properties._StructuralFeatureCS__owningClass,
			BaseCSTables.Properties._ElementCS__parent,
			BaseCSTables.Properties._PivotableElementCS__pivot,
			BaseCSTables.Properties._TypedElementCS__qualifiers,
			BaseCSTables.Properties._ModelElementCS__AnnotationCS__ownedContents,
			BaseCSTables.Properties._ElementCS__ElementCS__parent
		};

		private static final @NonNull ExecutorProperty @NonNull [] _BigNumber = {};

		private static final @NonNull ExecutorProperty @NonNull [] _CSI = {};

		private static final @NonNull ExecutorProperty @NonNull [] _ClassCS = {
			BaseCSTables.Properties._ElementCS__csi,
			BaseCSTables.Properties._ClassCS__instanceClassName,
			BaseCSTables.Properties._NamedElementCS__name,
			BaseCSTables.Properties._ModelElementCS__originalXmiId,
			BaseCSTables.Properties._ModelElementCS__ownedAnnotations,
			BaseCSTables.Properties._ClassCS__ownedConstraints,
			BaseCSTables.Properties._TemplateableElementCS__ownedSignature,
			BaseCSTables.Properties._ClassCS__owningPackage,
			BaseCSTables.Properties._ElementCS__parent,
			BaseCSTables.Properties._PivotableElementCS__pivot,
			BaseCSTables.Properties._ModelElementCS__AnnotationCS__ownedContents,
			BaseCSTables.Properties._ElementCS__ElementCS__parent
		};

		private static final @NonNull ExecutorProperty @NonNull [] _ConstraintCS = {
			BaseCSTables.Properties._ElementCS__csi,
			BaseCSTables.Properties._NamedElementCS__name,
			BaseCSTables.Properties._ModelElementCS__originalXmiId,
			BaseCSTables.Properties._ModelElementCS__ownedAnnotations,
			BaseCSTables.Properties._ConstraintCS__ownedMessageSpecification,
			BaseCSTables.Properties._ConstraintCS__ownedSpecification,
			BaseCSTables.Properties._ElementCS__parent,
			BaseCSTables.Properties._PivotableElementCS__pivot,
			BaseCSTables.Properties._ConstraintCS__stereotype,
			BaseCSTables.Properties._ModelElementCS__AnnotationCS__ownedContents,
			BaseCSTables.Properties._ConstraintCS__ClassCS__ownedConstraints,
			BaseCSTables.Properties._ElementCS__ElementCS__parent,
			BaseCSTables.Properties._ConstraintCS__OperationCS__ownedPostconditions,
			BaseCSTables.Properties._ConstraintCS__OperationCS__ownedPreconditions
		};

		private static final @NonNull ExecutorProperty @NonNull [] _ContextLessElementCS = {
			BaseCSTables.Properties._ElementCS__csi,
			BaseCSTables.Properties._ElementCS__parent,
			BaseCSTables.Properties._ElementCS__ElementCS__parent
		};

		private static final @NonNull ExecutorProperty @NonNull [] _DataTypeCS = {
			BaseCSTables.Properties._ElementCS__csi,
			BaseCSTables.Properties._ClassCS__instanceClassName,
			BaseCSTables.Properties._DataTypeCS__isPrimitive,
			BaseCSTables.Properties._DataTypeCS__isSerializable,
			BaseCSTables.Properties._NamedElementCS__name,
			BaseCSTables.Properties._ModelElementCS__originalXmiId,
			BaseCSTables.Properties._ModelElementCS__ownedAnnotations,
			BaseCSTables.Properties._ClassCS__ownedConstraints,
			BaseCSTables.Properties._TemplateableElementCS__ownedSignature,
			BaseCSTables.Properties._ClassCS__owningPackage,
			BaseCSTables.Properties._ElementCS__parent,
			BaseCSTables.Properties._PivotableElementCS__pivot,
			BaseCSTables.Properties._ModelElementCS__AnnotationCS__ownedContents,
			BaseCSTables.Properties._ElementCS__ElementCS__parent
		};

		private static final @NonNull ExecutorProperty @NonNull [] _DetailCS = {
			BaseCSTables.Properties._ElementCS__csi,
			BaseCSTables.Properties._NamedElementCS__name,
			BaseCSTables.Properties._ModelElementCS__originalXmiId,
			BaseCSTables.Properties._ModelElementCS__ownedAnnotations,
			BaseCSTables.Properties._ElementCS__parent,
			BaseCSTables.Properties._PivotableElementCS__pivot,
			BaseCSTables.Properties._DetailCS__values,
			BaseCSTables.Properties._ModelElementCS__AnnotationCS__ownedContents,
			BaseCSTables.Properties._DetailCS__AnnotationElementCS__ownedDetails,
			BaseCSTables.Properties._ElementCS__ElementCS__parent
		};

		private static final @NonNull ExecutorProperty @NonNull [] _DocumentationCS = {
			BaseCSTables.Properties._ElementCS__csi,
			BaseCSTables.Properties._NamedElementCS__name,
			BaseCSTables.Properties._ModelElementCS__originalXmiId,
			BaseCSTables.Properties._ModelElementCS__ownedAnnotations,
			BaseCSTables.Properties._AnnotationElementCS__ownedDetails,
			BaseCSTables.Properties._ElementCS__parent,
			BaseCSTables.Properties._PivotableElementCS__pivot,
			BaseCSTables.Properties._DocumentationCS__value,
			BaseCSTables.Properties._ModelElementCS__AnnotationCS__ownedContents,
			BaseCSTables.Properties._ElementCS__ElementCS__parent,
			BaseCSTables.Properties._AnnotationElementCS__ModelElementCS__ownedAnnotations
		};

		private static final @NonNull ExecutorProperty @NonNull [] _ElementCS = {
			BaseCSTables.Properties._ElementCS__csi,
			BaseCSTables.Properties._ElementCS__parent,
			BaseCSTables.Properties._ElementCS__ElementCS__parent
		};

		private static final @NonNull ExecutorProperty @NonNull [] _ElementRefCS = {
			BaseCSTables.Properties._ElementCS__csi,
			BaseCSTables.Properties._ElementCS__parent,
			BaseCSTables.Properties._PivotableElementCS__pivot,
			BaseCSTables.Properties._ElementCS__ElementCS__parent
		};

		private static final @NonNull ExecutorProperty @NonNull [] _EnumerationCS = {
			BaseCSTables.Properties._ElementCS__csi,
			BaseCSTables.Properties._ClassCS__instanceClassName,
			BaseCSTables.Properties._EnumerationCS__isSerializable,
			BaseCSTables.Properties._NamedElementCS__name,
			BaseCSTables.Properties._ModelElementCS__originalXmiId,
			BaseCSTables.Properties._ModelElementCS__ownedAnnotations,
			BaseCSTables.Properties._ClassCS__ownedConstraints,
			BaseCSTables.Properties._EnumerationCS__ownedLiterals,
			BaseCSTables.Properties._TemplateableElementCS__ownedSignature,
			BaseCSTables.Properties._ClassCS__owningPackage,
			BaseCSTables.Properties._ElementCS__parent,
			BaseCSTables.Properties._PivotableElementCS__pivot,
			BaseCSTables.Properties._ModelElementCS__AnnotationCS__ownedContents,
			BaseCSTables.Properties._ElementCS__ElementCS__parent
		};

		private static final @NonNull ExecutorProperty @NonNull [] _EnumerationLiteralCS = {
			BaseCSTables.Properties._ElementCS__csi,
			BaseCSTables.Properties._NamedElementCS__name,
			BaseCSTables.Properties._ModelElementCS__originalXmiId,
			BaseCSTables.Properties._ModelElementCS__ownedAnnotations,
			BaseCSTables.Properties._ElementCS__parent,
			BaseCSTables.Properties._PivotableElementCS__pivot,
			BaseCSTables.Properties._EnumerationLiteralCS__value,
			BaseCSTables.Properties._ModelElementCS__AnnotationCS__ownedContents,
			BaseCSTables.Properties._ElementCS__ElementCS__parent,
			BaseCSTables.Properties._EnumerationLiteralCS__EnumerationCS__ownedLiterals
		};

		private static final @NonNull ExecutorProperty @NonNull [] _FeatureCS = {
			BaseCSTables.Properties._ElementCS__csi,
			BaseCSTables.Properties._TypedElementCS__isOptional,
			BaseCSTables.Properties._NamedElementCS__name,
			BaseCSTables.Properties._ModelElementCS__originalXmiId,
			BaseCSTables.Properties._ModelElementCS__ownedAnnotations,
			BaseCSTables.Properties._TypedElementCS__ownedType,
			BaseCSTables.Properties._ElementCS__parent,
			BaseCSTables.Properties._PivotableElementCS__pivot,
			BaseCSTables.Properties._TypedElementCS__qualifiers,
			BaseCSTables.Properties._ModelElementCS__AnnotationCS__ownedContents,
			BaseCSTables.Properties._ElementCS__ElementCS__parent
		};

		private static final @NonNull ExecutorProperty @NonNull [] _ImportCS = {
			BaseCSTables.Properties._ElementCS__csi,
			BaseCSTables.Properties._ImportCS__isAll,
			BaseCSTables.Properties._NamedElementCS__name,
			BaseCSTables.Properties._ModelElementCS__originalXmiId,
			BaseCSTables.Properties._ModelElementCS__ownedAnnotations,
			BaseCSTables.Properties._ImportCS__ownedPathName,
			BaseCSTables.Properties._ElementCS__parent,
			BaseCSTables.Properties._PivotableElementCS__pivot,
			BaseCSTables.Properties._ImportCS__referredNamespace,
			BaseCSTables.Properties._ModelElementCS__AnnotationCS__ownedContents,
			BaseCSTables.Properties._ElementCS__ElementCS__parent,
			BaseCSTables.Properties._ImportCS__RootCS__ownedImports
		};

		private static final @NonNull ExecutorProperty @NonNull [] _LambdaTypeCS = {
			BaseCSTables.Properties._ElementCS__csi,
			BaseCSTables.Properties._LambdaTypeCS__name,
			BaseCSTables.Properties._LambdaTypeCS__ownedContextType,
			BaseCSTables.Properties._TypedRefCS__ownedMultiplicity,
			BaseCSTables.Properties._LambdaTypeCS__ownedParameterTypes,
			BaseCSTables.Properties._LambdaTypeCS__ownedResultType,
			BaseCSTables.Properties._TemplateableElementCS__ownedSignature,
			BaseCSTables.Properties._ElementCS__parent,
			BaseCSTables.Properties._PivotableElementCS__pivot,
			BaseCSTables.Properties._ElementCS__ElementCS__parent,
			BaseCSTables.Properties._TypedRefCS__LambdaTypeCS__ownedContextType,
			BaseCSTables.Properties._TypedRefCS__LambdaTypeCS__ownedParameterTypes,
			BaseCSTables.Properties._TypedRefCS__LambdaTypeCS__ownedResultType,
			BaseCSTables.Properties._TypedRefCS__OperationCS__ownedExceptions,
			BaseCSTables.Properties._TypedRefCS__StructuredClassCS__ownedMetaclass,
			BaseCSTables.Properties._TypedRefCS__StructuredClassCS__ownedSuperTypes,
			BaseCSTables.Properties._TypeRefCS__TemplateParameterSubstitutionCS__ownedActualParameter,
			BaseCSTables.Properties._TypedRefCS__TypeParameterCS__ownedExtends,
			BaseCSTables.Properties._TypedRefCS__TypedElementCS__ownedType,
			BaseCSTables.Properties._TypedRefCS__WildcardTypeRefCS__ownedExtends,
			BaseCSTables.Properties._TypedRefCS__WildcardTypeRefCS__ownedSuper
		};

		private static final @NonNull ExecutorProperty @NonNull [] _ModelElementCS = {
			BaseCSTables.Properties._ElementCS__csi,
			BaseCSTables.Properties._ModelElementCS__originalXmiId,
			BaseCSTables.Properties._ModelElementCS__ownedAnnotations,
			BaseCSTables.Properties._ElementCS__parent,
			BaseCSTables.Properties._PivotableElementCS__pivot,
			BaseCSTables.Properties._ModelElementCS__AnnotationCS__ownedContents,
			BaseCSTables.Properties._ElementCS__ElementCS__parent
		};

		private static final @NonNull ExecutorProperty @NonNull [] _ModelElementRefCS = {
			BaseCSTables.Properties._ElementCS__csi,
			BaseCSTables.Properties._ModelElementRefCS__ownedPathName,
			BaseCSTables.Properties._ElementCS__parent,
			BaseCSTables.Properties._PivotableElementCS__pivot,
			BaseCSTables.Properties._ModelElementRefCS__referredElement,
			BaseCSTables.Properties._ModelElementRefCS__AnnotationCS__ownedReferences,
			BaseCSTables.Properties._ElementCS__ElementCS__parent
		};

		private static final @NonNull ExecutorProperty @NonNull [] _MultiplicityBoundsCS = {
			BaseCSTables.Properties._ElementCS__csi,
			BaseCSTables.Properties._MultiplicityCS__isNullFree,
			BaseCSTables.Properties._MultiplicityBoundsCS__lowerBound,
			BaseCSTables.Properties._ElementCS__parent,
			BaseCSTables.Properties._MultiplicityBoundsCS__upperBound,
			BaseCSTables.Properties._ElementCS__ElementCS__parent,
			BaseCSTables.Properties._MultiplicityCS__TemplateBindingCS__ownedMultiplicity,
			BaseCSTables.Properties._MultiplicityCS__TypedRefCS__ownedMultiplicity
		};

		private static final @NonNull ExecutorProperty @NonNull [] _MultiplicityCS = {
			BaseCSTables.Properties._ElementCS__csi,
			BaseCSTables.Properties._MultiplicityCS__isNullFree,
			BaseCSTables.Properties._ElementCS__parent,
			BaseCSTables.Properties._ElementCS__ElementCS__parent,
			BaseCSTables.Properties._MultiplicityCS__TemplateBindingCS__ownedMultiplicity,
			BaseCSTables.Properties._MultiplicityCS__TypedRefCS__ownedMultiplicity
		};

		private static final @NonNull ExecutorProperty @NonNull [] _MultiplicityStringCS = {
			BaseCSTables.Properties._ElementCS__csi,
			BaseCSTables.Properties._MultiplicityCS__isNullFree,
			BaseCSTables.Properties._ElementCS__parent,
			BaseCSTables.Properties._MultiplicityStringCS__stringBounds,
			BaseCSTables.Properties._ElementCS__ElementCS__parent,
			BaseCSTables.Properties._MultiplicityCS__TemplateBindingCS__ownedMultiplicity,
			BaseCSTables.Properties._MultiplicityCS__TypedRefCS__ownedMultiplicity
		};

		private static final @NonNull ExecutorProperty @NonNull [] _NamedElementCS = {
			BaseCSTables.Properties._ElementCS__csi,
			BaseCSTables.Properties._NamedElementCS__name,
			BaseCSTables.Properties._ModelElementCS__originalXmiId,
			BaseCSTables.Properties._ModelElementCS__ownedAnnotations,
			BaseCSTables.Properties._ElementCS__parent,
			BaseCSTables.Properties._PivotableElementCS__pivot,
			BaseCSTables.Properties._ModelElementCS__AnnotationCS__ownedContents,
			BaseCSTables.Properties._ElementCS__ElementCS__parent
		};

		private static final @NonNull ExecutorProperty @NonNull [] _NamespaceCS = {
			BaseCSTables.Properties._ElementCS__csi,
			BaseCSTables.Properties._NamedElementCS__name,
			BaseCSTables.Properties._ModelElementCS__originalXmiId,
			BaseCSTables.Properties._ModelElementCS__ownedAnnotations,
			BaseCSTables.Properties._ElementCS__parent,
			BaseCSTables.Properties._PivotableElementCS__pivot,
			BaseCSTables.Properties._ModelElementCS__AnnotationCS__ownedContents,
			BaseCSTables.Properties._ElementCS__ElementCS__parent
		};

		private static final @NonNull ExecutorProperty @NonNull [] _OperationCS = {
			BaseCSTables.Properties._ElementCS__csi,
			BaseCSTables.Properties._TypedElementCS__isOptional,
			BaseCSTables.Properties._NamedElementCS__name,
			BaseCSTables.Properties._ModelElementCS__originalXmiId,
			BaseCSTables.Properties._ModelElementCS__ownedAnnotations,
			BaseCSTables.Properties._OperationCS__ownedBodyExpressions,
			BaseCSTables.Properties._OperationCS__ownedExceptions,
			BaseCSTables.Properties._OperationCS__ownedParameters,
			BaseCSTables.Properties._OperationCS__ownedPostconditions,
			BaseCSTables.Properties._OperationCS__ownedPreconditions,
			BaseCSTables.Properties._TemplateableElementCS__ownedSignature,
			BaseCSTables.Properties._TypedElementCS__ownedType,
			BaseCSTables.Properties._OperationCS__owningClass,
			BaseCSTables.Properties._ElementCS__parent,
			BaseCSTables.Properties._PivotableElementCS__pivot,
			BaseCSTables.Properties._TypedElementCS__qualifiers,
			BaseCSTables.Properties._ModelElementCS__AnnotationCS__ownedContents,
			BaseCSTables.Properties._ElementCS__ElementCS__parent
		};

		private static final @NonNull ExecutorProperty @NonNull [] _PackageCS = {
			BaseCSTables.Properties._ElementCS__csi,
			BaseCSTables.Properties._NamedElementCS__name,
			BaseCSTables.Properties._PackageCS__nsPrefix,
			BaseCSTables.Properties._PackageCS__nsURI,
			BaseCSTables.Properties._ModelElementCS__originalXmiId,
			BaseCSTables.Properties._ModelElementCS__ownedAnnotations,
			BaseCSTables.Properties._PackageCS__ownedClasses,
			BaseCSTables.Properties._PackageOwnerCS__ownedPackages,
			BaseCSTables.Properties._ElementCS__parent,
			BaseCSTables.Properties._PivotableElementCS__pivot,
			BaseCSTables.Properties._ModelElementCS__AnnotationCS__ownedContents,
			BaseCSTables.Properties._ElementCS__ElementCS__parent,
			BaseCSTables.Properties._PackageCS__PackageOwnerCS__ownedPackages
		};

		private static final @NonNull ExecutorProperty @NonNull [] _PackageOwnerCS = {
			BaseCSTables.Properties._ElementCS__csi,
			BaseCSTables.Properties._ModelElementCS__originalXmiId,
			BaseCSTables.Properties._ModelElementCS__ownedAnnotations,
			BaseCSTables.Properties._PackageOwnerCS__ownedPackages,
			BaseCSTables.Properties._ElementCS__parent,
			BaseCSTables.Properties._PivotableElementCS__pivot,
			BaseCSTables.Properties._ModelElementCS__AnnotationCS__ownedContents,
			BaseCSTables.Properties._ElementCS__ElementCS__parent
		};

		private static final @NonNull ExecutorProperty @NonNull [] _ParameterCS = {
			BaseCSTables.Properties._ElementCS__csi,
			BaseCSTables.Properties._TypedElementCS__isOptional,
			BaseCSTables.Properties._NamedElementCS__name,
			BaseCSTables.Properties._ModelElementCS__originalXmiId,
			BaseCSTables.Properties._ModelElementCS__ownedAnnotations,
			BaseCSTables.Properties._TypedElementCS__ownedType,
			BaseCSTables.Properties._ParameterCS__owningOperation,
			BaseCSTables.Properties._ElementCS__parent,
			BaseCSTables.Properties._PivotableElementCS__pivot,
			BaseCSTables.Properties._TypedElementCS__qualifiers,
			BaseCSTables.Properties._ModelElementCS__AnnotationCS__ownedContents,
			BaseCSTables.Properties._ElementCS__ElementCS__parent
		};

		private static final @NonNull ExecutorProperty @NonNull [] _PathElementCS = {
			BaseCSTables.Properties._ElementCS__csi,
			BaseCSTables.Properties._PathElementCS__elementType,
			BaseCSTables.Properties._PathElementCS__name,
			BaseCSTables.Properties._PathElementCS__owningPathName,
			BaseCSTables.Properties._ElementCS__parent,
			BaseCSTables.Properties._PathElementCS__referredElement,
			BaseCSTables.Properties._ElementCS__ElementCS__parent
		};

		private static final @NonNull ExecutorProperty @NonNull [] _PathElementWithURICS = {
			BaseCSTables.Properties._ElementCS__csi,
			BaseCSTables.Properties._PathElementCS__elementType,
			BaseCSTables.Properties._PathElementCS__name,
			BaseCSTables.Properties._PathElementCS__owningPathName,
			BaseCSTables.Properties._ElementCS__parent,
			BaseCSTables.Properties._PathElementCS__referredElement,
			BaseCSTables.Properties._PathElementWithURICS__uri,
			BaseCSTables.Properties._ElementCS__ElementCS__parent
		};

		private static final @NonNull ExecutorProperty @NonNull [] _PathNameCS = {
			BaseCSTables.Properties._PathNameCS__context,
			BaseCSTables.Properties._ElementCS__csi,
			BaseCSTables.Properties._PathNameCS__ownedPathElements,
			BaseCSTables.Properties._ElementCS__parent,
			BaseCSTables.Properties._PathNameCS__referredElement,
			BaseCSTables.Properties._PathNameCS__scopeFilter,
			BaseCSTables.Properties._ElementCS__ElementCS__parent,
			BaseCSTables.Properties._PathNameCS__ImportCS__ownedPathName,
			BaseCSTables.Properties._PathNameCS__ModelElementRefCS__ownedPathName,
			BaseCSTables.Properties._PathNameCS__TypedTypeRefCS__ownedPathName
		};

		private static final @NonNull ExecutorProperty @NonNull [] _PivotableElementCS = {
			BaseCSTables.Properties._ElementCS__csi,
			BaseCSTables.Properties._ElementCS__parent,
			BaseCSTables.Properties._PivotableElementCS__pivot,
			BaseCSTables.Properties._ElementCS__ElementCS__parent
		};

		private static final @NonNull ExecutorProperty @NonNull [] _PrimitiveTypeRefCS = {
			BaseCSTables.Properties._ElementCS__csi,
			BaseCSTables.Properties._PrimitiveTypeRefCS__name,
			BaseCSTables.Properties._TypedRefCS__ownedMultiplicity,
			BaseCSTables.Properties._ElementCS__parent,
			BaseCSTables.Properties._PivotableElementCS__pivot,
			BaseCSTables.Properties._ElementCS__ElementCS__parent,
			BaseCSTables.Properties._TypedRefCS__LambdaTypeCS__ownedContextType,
			BaseCSTables.Properties._TypedRefCS__LambdaTypeCS__ownedParameterTypes,
			BaseCSTables.Properties._TypedRefCS__LambdaTypeCS__ownedResultType,
			BaseCSTables.Properties._TypedRefCS__OperationCS__ownedExceptions,
			BaseCSTables.Properties._TypedRefCS__StructuredClassCS__ownedMetaclass,
			BaseCSTables.Properties._TypedRefCS__StructuredClassCS__ownedSuperTypes,
			BaseCSTables.Properties._TypeRefCS__TemplateParameterSubstitutionCS__ownedActualParameter,
			BaseCSTables.Properties._TypedRefCS__TypeParameterCS__ownedExtends,
			BaseCSTables.Properties._TypedRefCS__TypedElementCS__ownedType,
			BaseCSTables.Properties._TypedRefCS__WildcardTypeRefCS__ownedExtends,
			BaseCSTables.Properties._TypedRefCS__WildcardTypeRefCS__ownedSuper
		};

		private static final @NonNull ExecutorProperty @NonNull [] _ReferenceCS = {
			BaseCSTables.Properties._ElementCS__csi,
			BaseCSTables.Properties._StructuralFeatureCS__default,
			BaseCSTables.Properties._TypedElementCS__isOptional,
			BaseCSTables.Properties._NamedElementCS__name,
			BaseCSTables.Properties._ModelElementCS__originalXmiId,
			BaseCSTables.Properties._ModelElementCS__ownedAnnotations,
			BaseCSTables.Properties._StructuralFeatureCS__ownedDefaultExpressions,
			BaseCSTables.Properties._TypedElementCS__ownedType,
			BaseCSTables.Properties._StructuralFeatureCS__owningClass,
			BaseCSTables.Properties._ElementCS__parent,
			BaseCSTables.Properties._PivotableElementCS__pivot,
			BaseCSTables.Properties._TypedElementCS__qualifiers,
			BaseCSTables.Properties._ReferenceCS__referredKeys,
			BaseCSTables.Properties._ReferenceCS__referredOpposite,
			BaseCSTables.Properties._ModelElementCS__AnnotationCS__ownedContents,
			BaseCSTables.Properties._ElementCS__ElementCS__parent
		};

		private static final @NonNull ExecutorProperty @NonNull [] _RootCS = {
			BaseCSTables.Properties._ElementCS__csi,
			BaseCSTables.Properties._ModelElementCS__originalXmiId,
			BaseCSTables.Properties._ModelElementCS__ownedAnnotations,
			BaseCSTables.Properties._RootCS__ownedImports,
			BaseCSTables.Properties._ElementCS__parent,
			BaseCSTables.Properties._PivotableElementCS__pivot,
			BaseCSTables.Properties._ModelElementCS__AnnotationCS__ownedContents,
			BaseCSTables.Properties._ElementCS__ElementCS__parent
		};

		private static final @NonNull ExecutorProperty @NonNull [] _RootPackageCS = {
			BaseCSTables.Properties._ElementCS__csi,
			BaseCSTables.Properties._ModelElementCS__originalXmiId,
			BaseCSTables.Properties._ModelElementCS__ownedAnnotations,
			BaseCSTables.Properties._RootCS__ownedImports,
			BaseCSTables.Properties._PackageOwnerCS__ownedPackages,
			BaseCSTables.Properties._ElementCS__parent,
			BaseCSTables.Properties._PivotableElementCS__pivot,
			BaseCSTables.Properties._ModelElementCS__AnnotationCS__ownedContents,
			BaseCSTables.Properties._ElementCS__ElementCS__parent
		};

		private static final @NonNull ExecutorProperty @NonNull [] _ScopeFilter = {};

		private static final @NonNull ExecutorProperty @NonNull [] _SpecificationCS = {
			BaseCSTables.Properties._ElementCS__csi,
			BaseCSTables.Properties._SpecificationCS__exprString,
			BaseCSTables.Properties._ModelElementCS__originalXmiId,
			BaseCSTables.Properties._ModelElementCS__ownedAnnotations,
			BaseCSTables.Properties._ElementCS__parent,
			BaseCSTables.Properties._PivotableElementCS__pivot,
			BaseCSTables.Properties._ModelElementCS__AnnotationCS__ownedContents,
			BaseCSTables.Properties._SpecificationCS__ConstraintCS__ownedMessageSpecification,
			BaseCSTables.Properties._SpecificationCS__ConstraintCS__ownedSpecification,
			BaseCSTables.Properties._ElementCS__ElementCS__parent,
			BaseCSTables.Properties._SpecificationCS__OperationCS__ownedBodyExpressions,
			BaseCSTables.Properties._SpecificationCS__StructuralFeatureCS__ownedDefaultExpressions
		};

		private static final @NonNull ExecutorProperty @NonNull [] _StructuralFeatureCS = {
			BaseCSTables.Properties._ElementCS__csi,
			BaseCSTables.Properties._StructuralFeatureCS__default,
			BaseCSTables.Properties._TypedElementCS__isOptional,
			BaseCSTables.Properties._NamedElementCS__name,
			BaseCSTables.Properties._ModelElementCS__originalXmiId,
			BaseCSTables.Properties._ModelElementCS__ownedAnnotations,
			BaseCSTables.Properties._StructuralFeatureCS__ownedDefaultExpressions,
			BaseCSTables.Properties._TypedElementCS__ownedType,
			BaseCSTables.Properties._StructuralFeatureCS__owningClass,
			BaseCSTables.Properties._ElementCS__parent,
			BaseCSTables.Properties._PivotableElementCS__pivot,
			BaseCSTables.Properties._TypedElementCS__qualifiers,
			BaseCSTables.Properties._ModelElementCS__AnnotationCS__ownedContents,
			BaseCSTables.Properties._ElementCS__ElementCS__parent
		};

		private static final @NonNull ExecutorProperty @NonNull [] _StructuredClassCS = {
			BaseCSTables.Properties._ElementCS__csi,
			BaseCSTables.Properties._ClassCS__instanceClassName,
			BaseCSTables.Properties._StructuredClassCS__isAbstract,
			BaseCSTables.Properties._StructuredClassCS__isInterface,
			BaseCSTables.Properties._NamedElementCS__name,
			BaseCSTables.Properties._ModelElementCS__originalXmiId,
			BaseCSTables.Properties._ModelElementCS__ownedAnnotations,
			BaseCSTables.Properties._ClassCS__ownedConstraints,
			BaseCSTables.Properties._StructuredClassCS__ownedMetaclass,
			BaseCSTables.Properties._StructuredClassCS__ownedOperations,
			BaseCSTables.Properties._StructuredClassCS__ownedProperties,
			BaseCSTables.Properties._TemplateableElementCS__ownedSignature,
			BaseCSTables.Properties._StructuredClassCS__ownedSuperTypes,
			BaseCSTables.Properties._ClassCS__owningPackage,
			BaseCSTables.Properties._ElementCS__parent,
			BaseCSTables.Properties._PivotableElementCS__pivot,
			BaseCSTables.Properties._ModelElementCS__AnnotationCS__ownedContents,
			BaseCSTables.Properties._ElementCS__ElementCS__parent
		};

		private static final @NonNull ExecutorProperty @NonNull [] _TemplateBindingCS = {
			BaseCSTables.Properties._ElementCS__csi,
			BaseCSTables.Properties._TemplateBindingCS__ownedMultiplicity,
			BaseCSTables.Properties._TemplateBindingCS__ownedSubstitutions,
			BaseCSTables.Properties._TemplateBindingCS__owningElement,
			BaseCSTables.Properties._ElementCS__parent,
			BaseCSTables.Properties._PivotableElementCS__pivot,
			BaseCSTables.Properties._ElementCS__ElementCS__parent
		};

		private static final @NonNull ExecutorProperty @NonNull [] _TemplateParameterCS = {
			BaseCSTables.Properties._ElementCS__csi,
			BaseCSTables.Properties._NamedElementCS__name,
			BaseCSTables.Properties._ModelElementCS__originalXmiId,
			BaseCSTables.Properties._ModelElementCS__ownedAnnotations,
			BaseCSTables.Properties._TemplateParameterCS__owningSignature,
			BaseCSTables.Properties._ElementCS__parent,
			BaseCSTables.Properties._PivotableElementCS__pivot,
			BaseCSTables.Properties._ModelElementCS__AnnotationCS__ownedContents,
			BaseCSTables.Properties._ElementCS__ElementCS__parent
		};

		private static final @NonNull ExecutorProperty @NonNull [] _TemplateParameterSubstitutionCS = {
			BaseCSTables.Properties._ElementCS__csi,
			BaseCSTables.Properties._ModelElementCS__originalXmiId,
			BaseCSTables.Properties._TemplateParameterSubstitutionCS__ownedActualParameter,
			BaseCSTables.Properties._ModelElementCS__ownedAnnotations,
			BaseCSTables.Properties._TemplateParameterSubstitutionCS__owningBinding,
			BaseCSTables.Properties._ElementCS__parent,
			BaseCSTables.Properties._PivotableElementCS__pivot,
			BaseCSTables.Properties._ModelElementCS__AnnotationCS__ownedContents,
			BaseCSTables.Properties._ElementCS__ElementCS__parent
		};

		private static final @NonNull ExecutorProperty @NonNull [] _TemplateSignatureCS = {
			BaseCSTables.Properties._ElementCS__csi,
			BaseCSTables.Properties._ModelElementCS__originalXmiId,
			BaseCSTables.Properties._ModelElementCS__ownedAnnotations,
			BaseCSTables.Properties._TemplateSignatureCS__ownedParameters,
			BaseCSTables.Properties._TemplateSignatureCS__owningElement,
			BaseCSTables.Properties._ElementCS__parent,
			BaseCSTables.Properties._PivotableElementCS__pivot,
			BaseCSTables.Properties._ModelElementCS__AnnotationCS__ownedContents,
			BaseCSTables.Properties._ElementCS__ElementCS__parent
		};

		private static final @NonNull ExecutorProperty @NonNull [] _TemplateableElementCS = {
			BaseCSTables.Properties._ElementCS__csi,
			BaseCSTables.Properties._TemplateableElementCS__ownedSignature,
			BaseCSTables.Properties._ElementCS__parent,
			BaseCSTables.Properties._ElementCS__ElementCS__parent
		};

		private static final @NonNull ExecutorProperty @NonNull [] _TuplePartCS = {
			BaseCSTables.Properties._ElementCS__csi,
			BaseCSTables.Properties._TypedElementCS__isOptional,
			BaseCSTables.Properties._NamedElementCS__name,
			BaseCSTables.Properties._ModelElementCS__originalXmiId,
			BaseCSTables.Properties._ModelElementCS__ownedAnnotations,
			BaseCSTables.Properties._TypedElementCS__ownedType,
			BaseCSTables.Properties._ElementCS__parent,
			BaseCSTables.Properties._PivotableElementCS__pivot,
			BaseCSTables.Properties._TypedElementCS__qualifiers,
			BaseCSTables.Properties._ModelElementCS__AnnotationCS__ownedContents,
			BaseCSTables.Properties._ElementCS__ElementCS__parent,
			BaseCSTables.Properties._TuplePartCS__TupleTypeCS__ownedParts
		};

		private static final @NonNull ExecutorProperty @NonNull [] _TupleTypeCS = {
			BaseCSTables.Properties._ElementCS__csi,
			BaseCSTables.Properties._TupleTypeCS__name,
			BaseCSTables.Properties._TypedRefCS__ownedMultiplicity,
			BaseCSTables.Properties._TupleTypeCS__ownedParts,
			BaseCSTables.Properties._ElementCS__parent,
			BaseCSTables.Properties._PivotableElementCS__pivot,
			BaseCSTables.Properties._ElementCS__ElementCS__parent,
			BaseCSTables.Properties._TypedRefCS__LambdaTypeCS__ownedContextType,
			BaseCSTables.Properties._TypedRefCS__LambdaTypeCS__ownedParameterTypes,
			BaseCSTables.Properties._TypedRefCS__LambdaTypeCS__ownedResultType,
			BaseCSTables.Properties._TypedRefCS__OperationCS__ownedExceptions,
			BaseCSTables.Properties._TypedRefCS__StructuredClassCS__ownedMetaclass,
			BaseCSTables.Properties._TypedRefCS__StructuredClassCS__ownedSuperTypes,
			BaseCSTables.Properties._TypeRefCS__TemplateParameterSubstitutionCS__ownedActualParameter,
			BaseCSTables.Properties._TypedRefCS__TypeParameterCS__ownedExtends,
			BaseCSTables.Properties._TypedRefCS__TypedElementCS__ownedType,
			BaseCSTables.Properties._TypedRefCS__WildcardTypeRefCS__ownedExtends,
			BaseCSTables.Properties._TypedRefCS__WildcardTypeRefCS__ownedSuper
		};

		private static final @NonNull ExecutorProperty @NonNull [] _TypeCS = {
			BaseCSTables.Properties._ElementCS__csi,
			BaseCSTables.Properties._ModelElementCS__originalXmiId,
			BaseCSTables.Properties._ModelElementCS__ownedAnnotations,
			BaseCSTables.Properties._ElementCS__parent,
			BaseCSTables.Properties._PivotableElementCS__pivot,
			BaseCSTables.Properties._ModelElementCS__AnnotationCS__ownedContents,
			BaseCSTables.Properties._ElementCS__ElementCS__parent
		};

		private static final @NonNull ExecutorProperty @NonNull [] _TypeParameterCS = {
			BaseCSTables.Properties._ElementCS__csi,
			BaseCSTables.Properties._NamedElementCS__name,
			BaseCSTables.Properties._ModelElementCS__originalXmiId,
			BaseCSTables.Properties._ModelElementCS__ownedAnnotations,
			BaseCSTables.Properties._TypeParameterCS__ownedExtends,
			BaseCSTables.Properties._TemplateParameterCS__owningSignature,
			BaseCSTables.Properties._ElementCS__parent,
			BaseCSTables.Properties._PivotableElementCS__pivot,
			BaseCSTables.Properties._ModelElementCS__AnnotationCS__ownedContents,
			BaseCSTables.Properties._ElementCS__ElementCS__parent
		};

		private static final @NonNull ExecutorProperty @NonNull [] _TypeRefCS = {
			BaseCSTables.Properties._ElementCS__csi,
			BaseCSTables.Properties._ElementCS__parent,
			BaseCSTables.Properties._PivotableElementCS__pivot,
			BaseCSTables.Properties._ElementCS__ElementCS__parent,
			BaseCSTables.Properties._TypeRefCS__TemplateParameterSubstitutionCS__ownedActualParameter
		};

		private static final @NonNull ExecutorProperty @NonNull [] _TypedElementCS = {
			BaseCSTables.Properties._ElementCS__csi,
			BaseCSTables.Properties._TypedElementCS__isOptional,
			BaseCSTables.Properties._NamedElementCS__name,
			BaseCSTables.Properties._ModelElementCS__originalXmiId,
			BaseCSTables.Properties._ModelElementCS__ownedAnnotations,
			BaseCSTables.Properties._TypedElementCS__ownedType,
			BaseCSTables.Properties._ElementCS__parent,
			BaseCSTables.Properties._PivotableElementCS__pivot,
			BaseCSTables.Properties._TypedElementCS__qualifiers,
			BaseCSTables.Properties._ModelElementCS__AnnotationCS__ownedContents,
			BaseCSTables.Properties._ElementCS__ElementCS__parent
		};

		private static final @NonNull ExecutorProperty @NonNull [] _TypedRefCS = {
			BaseCSTables.Properties._ElementCS__csi,
			BaseCSTables.Properties._TypedRefCS__ownedMultiplicity,
			BaseCSTables.Properties._ElementCS__parent,
			BaseCSTables.Properties._PivotableElementCS__pivot,
			BaseCSTables.Properties._ElementCS__ElementCS__parent,
			BaseCSTables.Properties._TypedRefCS__LambdaTypeCS__ownedContextType,
			BaseCSTables.Properties._TypedRefCS__LambdaTypeCS__ownedParameterTypes,
			BaseCSTables.Properties._TypedRefCS__LambdaTypeCS__ownedResultType,
			BaseCSTables.Properties._TypedRefCS__OperationCS__ownedExceptions,
			BaseCSTables.Properties._TypedRefCS__StructuredClassCS__ownedMetaclass,
			BaseCSTables.Properties._TypedRefCS__StructuredClassCS__ownedSuperTypes,
			BaseCSTables.Properties._TypeRefCS__TemplateParameterSubstitutionCS__ownedActualParameter,
			BaseCSTables.Properties._TypedRefCS__TypeParameterCS__ownedExtends,
			BaseCSTables.Properties._TypedRefCS__TypedElementCS__ownedType,
			BaseCSTables.Properties._TypedRefCS__WildcardTypeRefCS__ownedExtends,
			BaseCSTables.Properties._TypedRefCS__WildcardTypeRefCS__ownedSuper
		};

		private static final @NonNull ExecutorProperty @NonNull [] _TypedTypeRefCS = {
			BaseCSTables.Properties._ElementCS__csi,
			BaseCSTables.Properties._TypedTypeRefCS__isTypeof,
			BaseCSTables.Properties._TypedTypeRefCS__ownedBinding,
			BaseCSTables.Properties._TypedRefCS__ownedMultiplicity,
			BaseCSTables.Properties._TypedTypeRefCS__ownedPathName,
			BaseCSTables.Properties._ElementCS__parent,
			BaseCSTables.Properties._PivotableElementCS__pivot,
			BaseCSTables.Properties._TypedTypeRefCS__referredType,
			BaseCSTables.Properties._ElementCS__ElementCS__parent,
			BaseCSTables.Properties._TypedRefCS__LambdaTypeCS__ownedContextType,
			BaseCSTables.Properties._TypedRefCS__LambdaTypeCS__ownedParameterTypes,
			BaseCSTables.Properties._TypedRefCS__LambdaTypeCS__ownedResultType,
			BaseCSTables.Properties._TypedRefCS__OperationCS__ownedExceptions,
			BaseCSTables.Properties._TypedRefCS__StructuredClassCS__ownedMetaclass,
			BaseCSTables.Properties._TypedRefCS__StructuredClassCS__ownedSuperTypes,
			BaseCSTables.Properties._TypeRefCS__TemplateParameterSubstitutionCS__ownedActualParameter,
			BaseCSTables.Properties._TypedRefCS__TypeParameterCS__ownedExtends,
			BaseCSTables.Properties._TypedRefCS__TypedElementCS__ownedType,
			BaseCSTables.Properties._TypedRefCS__WildcardTypeRefCS__ownedExtends,
			BaseCSTables.Properties._TypedRefCS__WildcardTypeRefCS__ownedSuper
		};

		private static final @NonNull ExecutorProperty @NonNull [] _VisitableCS = {};

		private static final @NonNull ExecutorProperty @NonNull [] _WildcardTypeRefCS = {
			BaseCSTables.Properties._ElementCS__csi,
			BaseCSTables.Properties._WildcardTypeRefCS__ownedExtends,
			BaseCSTables.Properties._WildcardTypeRefCS__ownedSuper,
			BaseCSTables.Properties._ElementCS__parent,
			BaseCSTables.Properties._PivotableElementCS__pivot,
			BaseCSTables.Properties._ElementCS__ElementCS__parent,
			BaseCSTables.Properties._TypeRefCS__TemplateParameterSubstitutionCS__ownedActualParameter
		};

		/**
		 *	Install the property descriptors in the fragment descriptors.
		 */
		static {
			Fragments._AnnotationCS__AnnotationCS.initProperties(_AnnotationCS);
			Fragments._AnnotationElementCS__AnnotationElementCS.initProperties(_AnnotationElementCS);
			Fragments._AttributeCS__AttributeCS.initProperties(_AttributeCS);
			Fragments._BigNumber__BigNumber.initProperties(_BigNumber);
			Fragments._CSI__CSI.initProperties(_CSI);
			Fragments._ClassCS__ClassCS.initProperties(_ClassCS);
			Fragments._ConstraintCS__ConstraintCS.initProperties(_ConstraintCS);
			Fragments._ContextLessElementCS__ContextLessElementCS.initProperties(_ContextLessElementCS);
			Fragments._DataTypeCS__DataTypeCS.initProperties(_DataTypeCS);
			Fragments._DetailCS__DetailCS.initProperties(_DetailCS);
			Fragments._DocumentationCS__DocumentationCS.initProperties(_DocumentationCS);
			Fragments._ElementCS__ElementCS.initProperties(_ElementCS);
			Fragments._ElementRefCS__ElementRefCS.initProperties(_ElementRefCS);
			Fragments._EnumerationCS__EnumerationCS.initProperties(_EnumerationCS);
			Fragments._EnumerationLiteralCS__EnumerationLiteralCS.initProperties(_EnumerationLiteralCS);
			Fragments._FeatureCS__FeatureCS.initProperties(_FeatureCS);
			Fragments._ImportCS__ImportCS.initProperties(_ImportCS);
			Fragments._LambdaTypeCS__LambdaTypeCS.initProperties(_LambdaTypeCS);
			Fragments._ModelElementCS__ModelElementCS.initProperties(_ModelElementCS);
			Fragments._ModelElementRefCS__ModelElementRefCS.initProperties(_ModelElementRefCS);
			Fragments._MultiplicityBoundsCS__MultiplicityBoundsCS.initProperties(_MultiplicityBoundsCS);
			Fragments._MultiplicityCS__MultiplicityCS.initProperties(_MultiplicityCS);
			Fragments._MultiplicityStringCS__MultiplicityStringCS.initProperties(_MultiplicityStringCS);
			Fragments._NamedElementCS__NamedElementCS.initProperties(_NamedElementCS);
			Fragments._NamespaceCS__NamespaceCS.initProperties(_NamespaceCS);
			Fragments._OperationCS__OperationCS.initProperties(_OperationCS);
			Fragments._PackageCS__PackageCS.initProperties(_PackageCS);
			Fragments._PackageOwnerCS__PackageOwnerCS.initProperties(_PackageOwnerCS);
			Fragments._ParameterCS__ParameterCS.initProperties(_ParameterCS);
			Fragments._PathElementCS__PathElementCS.initProperties(_PathElementCS);
			Fragments._PathElementWithURICS__PathElementWithURICS.initProperties(_PathElementWithURICS);
			Fragments._PathNameCS__PathNameCS.initProperties(_PathNameCS);
			Fragments._PivotableElementCS__PivotableElementCS.initProperties(_PivotableElementCS);
			Fragments._PrimitiveTypeRefCS__PrimitiveTypeRefCS.initProperties(_PrimitiveTypeRefCS);
			Fragments._ReferenceCS__ReferenceCS.initProperties(_ReferenceCS);
			Fragments._RootCS__RootCS.initProperties(_RootCS);
			Fragments._RootPackageCS__RootPackageCS.initProperties(_RootPackageCS);
			Fragments._ScopeFilter__ScopeFilter.initProperties(_ScopeFilter);
			Fragments._SpecificationCS__SpecificationCS.initProperties(_SpecificationCS);
			Fragments._StructuralFeatureCS__StructuralFeatureCS.initProperties(_StructuralFeatureCS);
			Fragments._StructuredClassCS__StructuredClassCS.initProperties(_StructuredClassCS);
			Fragments._TemplateBindingCS__TemplateBindingCS.initProperties(_TemplateBindingCS);
			Fragments._TemplateParameterCS__TemplateParameterCS.initProperties(_TemplateParameterCS);
			Fragments._TemplateParameterSubstitutionCS__TemplateParameterSubstitutionCS.initProperties(_TemplateParameterSubstitutionCS);
			Fragments._TemplateSignatureCS__TemplateSignatureCS.initProperties(_TemplateSignatureCS);
			Fragments._TemplateableElementCS__TemplateableElementCS.initProperties(_TemplateableElementCS);
			Fragments._TuplePartCS__TuplePartCS.initProperties(_TuplePartCS);
			Fragments._TupleTypeCS__TupleTypeCS.initProperties(_TupleTypeCS);
			Fragments._TypeCS__TypeCS.initProperties(_TypeCS);
			Fragments._TypeParameterCS__TypeParameterCS.initProperties(_TypeParameterCS);
			Fragments._TypeRefCS__TypeRefCS.initProperties(_TypeRefCS);
			Fragments._TypedElementCS__TypedElementCS.initProperties(_TypedElementCS);
			Fragments._TypedRefCS__TypedRefCS.initProperties(_TypedRefCS);
			Fragments._TypedTypeRefCS__TypedTypeRefCS.initProperties(_TypedTypeRefCS);
			Fragments._VisitableCS__VisitableCS.initProperties(_VisitableCS);
			Fragments._WildcardTypeRefCS__WildcardTypeRefCS.initProperties(_WildcardTypeRefCS);

			Init.initEnd();
		}

		/**
		 * Force initialization of the fields of BaseCSTables::FragmentProperties and all preceding sub-packages.
		 */
		public static void init() {}
	}

	/**
	 *	The lists of enumeration literals for each enumeration.
	 */
	public static class EnumerationLiterals {
		static {
			Init.initStart();
			FragmentProperties.init();
		}

		/**
		 *	Install the enumeration literals in the enumerations.
		 */
		static {

			Init.initEnd();
		}

		/**
		 * Force initialization of the fields of BaseCSTables::EnumerationLiterals and all preceding sub-packages.
		 */
		public static void init() {}
	}

	/**
	 * The multiple packages above avoid problems with the Java 65536 byte limit but introduce a difficulty in ensuring that
	 * static construction occurs in the disciplined order of the packages when construction may start in any of the packages.
	 * The problem is resolved by ensuring that the static construction of each package first initializes its immediate predecessor.
	 * On completion of predecessor initialization, the residual packages are initialized by starting an initialization in the last package.
	 * This class maintains a count so that the various predecessors can distinguish whether they are the starting point and so
	 * ensure that residual construction occurs just once after all predecessors.
	 */
	private static class Init {
		/**
		 * Counter of nested static constructions. On return to zero residual construction starts. -ve once residual construction started.
		 */
		private static int initCount = 0;

		/**
		 * Invoked at the start of a static construction to defer residual cobstruction until primary constructions complete.
		 */
		private static void initStart() {
			if (initCount >= 0) {
				initCount++;
			}
		}

		/**
		 * Invoked at the end of a static construction to activate residual cobstruction once primary constructions complete.
		 */
		private static void initEnd() {
			if (initCount > 0) {
				if (--initCount == 0) {
					initCount = -1;
					EnumerationLiterals.init();
				}
			}
		}
	}

	static {
		Init.initEnd();
	}

	/*
	 * Force initialization of outer fields. Inner fields are lazily initialized.
	 */
	public static void init() {}
}
