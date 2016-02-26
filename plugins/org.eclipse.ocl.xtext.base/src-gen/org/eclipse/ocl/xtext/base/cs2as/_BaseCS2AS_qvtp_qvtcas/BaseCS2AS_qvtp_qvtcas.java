/*******************************************************************************
 * «codeGenHelper.getCopyright(' * ')»
 *
 * This code is 100% auto-generated
 * using: org.eclipse.qvtd.cs2as.compiler.internal.CS2ASJavaCompilerImpl$1
 *
 * Do not edit it.
 ********************************************************************************/

package org.eclipse.ocl.xtext.base.cs2as._BaseCS2AS_qvtp_qvtcas;

import java.util.Iterator;
import java.util.List;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Annotation;
import org.eclipse.ocl.pivot.Class;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.DataType;
import org.eclipse.ocl.pivot.Detail;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.Enumeration;
import org.eclipse.ocl.pivot.EnumerationLiteral;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.Import;
import org.eclipse.ocl.pivot.LambdaType;
import org.eclipse.ocl.pivot.LanguageExpression;
import org.eclipse.ocl.pivot.Namespace;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Package;
import org.eclipse.ocl.pivot.Parameter;
import org.eclipse.ocl.pivot.PivotFactory;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.PrimitiveType;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.TemplateBinding;
import org.eclipse.ocl.pivot.TemplateParameter;
import org.eclipse.ocl.pivot.TemplateParameterSubstitution;
import org.eclipse.ocl.pivot.TemplateSignature;
import org.eclipse.ocl.pivot.TupleType;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.ids.ClassId;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.ids.DataTypeId;
import org.eclipse.ocl.pivot.ids.IdManager;
import org.eclipse.ocl.pivot.ids.IdResolver;
import org.eclipse.ocl.pivot.ids.NsURIPackageId;
import org.eclipse.ocl.pivot.ids.PropertyId;
import org.eclipse.ocl.pivot.ids.RootPackageId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.lookup.util.PivotLookupResult;
import org.eclipse.ocl.pivot.internal.lookup.util.PivotLookupSolver;
import org.eclipse.ocl.pivot.library.collection.CollectionIncludesOperation;
import org.eclipse.ocl.pivot.library.collection.CollectionNotEmptyOperation;
import org.eclipse.ocl.pivot.library.collection.CollectionSizeOperation;
import org.eclipse.ocl.pivot.library.collection.OrderedCollectionAtOperation;
import org.eclipse.ocl.pivot.library.collection.OrderedCollectionFirstOperation;
import org.eclipse.ocl.pivot.library.collection.OrderedCollectionIndexOfOperation;
import org.eclipse.ocl.pivot.library.collection.OrderedCollectionLastOperation;
import org.eclipse.ocl.pivot.library.collection.OrderedSetSubOrderedSetOperation;
import org.eclipse.ocl.pivot.library.logical.BooleanNotOperation;
import org.eclipse.ocl.pivot.library.numeric.NumericMinusOperation;
import org.eclipse.ocl.pivot.library.oclany.OclAnyOclAsTypeOperation;
import org.eclipse.ocl.pivot.messages.PivotMessages;
import org.eclipse.ocl.pivot.util.Visitable;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.IntegerValue;
import org.eclipse.ocl.pivot.values.InvalidValueException;
import org.eclipse.ocl.pivot.values.OrderedSetValue;
import org.eclipse.ocl.pivot.values.SequenceValue;
import org.eclipse.ocl.pivot.values.SetValue;
import org.eclipse.ocl.xtext.basecs.AnnotationCS;
import org.eclipse.ocl.xtext.basecs.AnnotationElementCS;
import org.eclipse.ocl.xtext.basecs.AttributeCS;
import org.eclipse.ocl.xtext.basecs.BaseCSPackage;
import org.eclipse.ocl.xtext.basecs.ClassCS;
import org.eclipse.ocl.xtext.basecs.ConstraintCS;
import org.eclipse.ocl.xtext.basecs.DataTypeCS;
import org.eclipse.ocl.xtext.basecs.DetailCS;
import org.eclipse.ocl.xtext.basecs.EnumerationCS;
import org.eclipse.ocl.xtext.basecs.EnumerationLiteralCS;
import org.eclipse.ocl.xtext.basecs.ImportCS;
import org.eclipse.ocl.xtext.basecs.LambdaTypeCS;
import org.eclipse.ocl.xtext.basecs.ModelElementCS;
import org.eclipse.ocl.xtext.basecs.ModelElementRefCS;
import org.eclipse.ocl.xtext.basecs.OperationCS;
import org.eclipse.ocl.xtext.basecs.PackageCS;
import org.eclipse.ocl.xtext.basecs.ParameterCS;
import org.eclipse.ocl.xtext.basecs.PathElementCS;
import org.eclipse.ocl.xtext.basecs.PathNameCS;
import org.eclipse.ocl.xtext.basecs.PrimitiveTypeRefCS;
import org.eclipse.ocl.xtext.basecs.ReferenceCS;
import org.eclipse.ocl.xtext.basecs.RootPackageCS;
import org.eclipse.ocl.xtext.basecs.SpecificationCS;
import org.eclipse.ocl.xtext.basecs.StructuralFeatureCS;
import org.eclipse.ocl.xtext.basecs.StructuredClassCS;
import org.eclipse.ocl.xtext.basecs.TemplateBindingCS;
import org.eclipse.ocl.xtext.basecs.TemplateParameterCS;
import org.eclipse.ocl.xtext.basecs.TemplateParameterSubstitutionCS;
import org.eclipse.ocl.xtext.basecs.TemplateSignatureCS;
import org.eclipse.ocl.xtext.basecs.TuplePartCS;
import org.eclipse.ocl.xtext.basecs.TupleTypeCS;
import org.eclipse.ocl.xtext.basecs.TypeParameterCS;
import org.eclipse.ocl.xtext.basecs.TypeRefCS;
import org.eclipse.ocl.xtext.basecs.TypedRefCS;
import org.eclipse.ocl.xtext.basecs.TypedTypeRefCS;
import org.eclipse.ocl.xtext.base.cs2as.tx.AbstractCS2ASTransformer;
import org.eclipse.ocl.pivot.library.model.ModelObjectsOfKindOperation;

/**
 * The BaseCS2AS_qvtp_qvtcas transformation:
 * <p>
 * Construct with an evaluator
 * <br>
 * Populate each input model with {@link addRootObjects(String,List)}
 * <br>
 * {@link run()}
 * <br>
 * Extract each output model with {@link getRootObjects(String)}
 */
@SuppressWarnings("nls")
public class BaseCS2AS_qvtp_qvtcas extends AbstractCS2ASTransformer
{
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull RootPackageId PACKid_$metamodel$ = IdManager.getRootPackageId("$metamodel$");
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull NsURIPackageId PACKid_http_c_s_s_www_eclipse_org_s_emf_s_2002_s_Ecore = IdManager.getNsURIPackageId("http://www.eclipse.org/emf/2002/Ecore", null, EcorePackage.eINSTANCE);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull NsURIPackageId PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_2015_s_BaseCS = IdManager.getNsURIPackageId("http://www.eclipse.org/ocl/2015/BaseCS", null, BaseCSPackage.eINSTANCE);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull NsURIPackageId PACKid_http_c_s_s_www_eclipse_org_s_qvt_s_2015_s_QVTbaseLibrary = IdManager.getNsURIPackageId("http://www.eclipse.org/qvt/2015/QVTbaseLibrary", "qvtbaselib", null);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_Annotation = PACKid_$metamodel$.getClassId("Annotation", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_AnnotationCS = PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_2015_s_BaseCS.getClassId("AnnotationCS", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_AnnotationElementCS = PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_2015_s_BaseCS.getClassId("AnnotationElementCS", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_AttributeCS = PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_2015_s_BaseCS.getClassId("AttributeCS", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_Class = PACKid_$metamodel$.getClassId("Class", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_ClassCS = PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_2015_s_BaseCS.getClassId("ClassCS", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_Constraint = PACKid_$metamodel$.getClassId("Constraint", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_ConstraintCS = PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_2015_s_BaseCS.getClassId("ConstraintCS", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_DataType = PACKid_$metamodel$.getClassId("DataType", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_DataTypeCS = PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_2015_s_BaseCS.getClassId("DataTypeCS", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_Detail = PACKid_$metamodel$.getClassId("Detail", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_DetailCS = PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_2015_s_BaseCS.getClassId("DetailCS", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_Element = PACKid_$metamodel$.getClassId("Element", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_Enumeration = PACKid_$metamodel$.getClassId("Enumeration", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_EnumerationCS = PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_2015_s_BaseCS.getClassId("EnumerationCS", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_EnumerationLiteral = PACKid_$metamodel$.getClassId("EnumerationLiteral", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_EnumerationLiteralCS = PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_2015_s_BaseCS.getClassId("EnumerationLiteralCS", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_ExpressionInOCL = PACKid_$metamodel$.getClassId("ExpressionInOCL", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_Import = PACKid_$metamodel$.getClassId("Import", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_ImportCS = PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_2015_s_BaseCS.getClassId("ImportCS", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_LambdaType = PACKid_$metamodel$.getClassId("LambdaType", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_LambdaTypeCS = PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_2015_s_BaseCS.getClassId("LambdaTypeCS", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_LanguageExpression = PACKid_$metamodel$.getClassId("LanguageExpression", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_Model = PACKid_http_c_s_s_www_eclipse_org_s_qvt_s_2015_s_QVTbaseLibrary.getClassId("Model", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_ModelElementCS = PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_2015_s_BaseCS.getClassId("ModelElementCS", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_ModelElementRefCS = PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_2015_s_BaseCS.getClassId("ModelElementRefCS", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_Model_0 = PACKid_$metamodel$.getClassId("Model", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_NamedElement = PACKid_$metamodel$.getClassId("NamedElement", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_Namespace = PACKid_$metamodel$.getClassId("Namespace", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_OclElement = PACKid_$metamodel$.getClassId("OclElement", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_Operation = PACKid_$metamodel$.getClassId("Operation", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_OperationCS = PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_2015_s_BaseCS.getClassId("OperationCS", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_Package = PACKid_$metamodel$.getClassId("Package", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_PackageCS = PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_2015_s_BaseCS.getClassId("PackageCS", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_Parameter = PACKid_$metamodel$.getClassId("Parameter", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_ParameterCS = PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_2015_s_BaseCS.getClassId("ParameterCS", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_PathElementCS = PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_2015_s_BaseCS.getClassId("PathElementCS", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_PathNameCS = PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_2015_s_BaseCS.getClassId("PathNameCS", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_PrimitiveType = PACKid_$metamodel$.getClassId("PrimitiveType", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_PrimitiveTypeRefCS = PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_2015_s_BaseCS.getClassId("PrimitiveTypeRefCS", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_Property = PACKid_$metamodel$.getClassId("Property", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_ReferenceCS = PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_2015_s_BaseCS.getClassId("ReferenceCS", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_RootPackageCS = PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_2015_s_BaseCS.getClassId("RootPackageCS", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_SpecificationCS = PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_2015_s_BaseCS.getClassId("SpecificationCS", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_StructuralFeatureCS = PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_2015_s_BaseCS.getClassId("StructuralFeatureCS", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_StructuredClassCS = PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_2015_s_BaseCS.getClassId("StructuredClassCS", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_TemplateBinding = PACKid_$metamodel$.getClassId("TemplateBinding", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_TemplateBindingCS = PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_2015_s_BaseCS.getClassId("TemplateBindingCS", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_TemplateParameter = PACKid_$metamodel$.getClassId("TemplateParameter", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_TemplateParameterCS = PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_2015_s_BaseCS.getClassId("TemplateParameterCS", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_TemplateParameterSubstitution = PACKid_$metamodel$.getClassId("TemplateParameterSubstitution", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_TemplateParameterSubstitutionCS = PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_2015_s_BaseCS.getClassId("TemplateParameterSubstitutionCS", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_TemplateSignature = PACKid_$metamodel$.getClassId("TemplateSignature", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_TemplateSignatureCS = PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_2015_s_BaseCS.getClassId("TemplateSignatureCS", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_TuplePartCS = PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_2015_s_BaseCS.getClassId("TuplePartCS", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_TupleType = PACKid_$metamodel$.getClassId("TupleType", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_TupleTypeCS = PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_2015_s_BaseCS.getClassId("TupleTypeCS", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_TypeParameterCS = PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_2015_s_BaseCS.getClassId("TypeParameterCS", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_TypeRefCS = PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_2015_s_BaseCS.getClassId("TypeRefCS", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_TypedElement = PACKid_$metamodel$.getClassId("TypedElement", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_TypedRefCS = PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_2015_s_BaseCS.getClassId("TypedRefCS", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_TypedTypeRefCS = PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_2015_s_BaseCS.getClassId("TypedTypeRefCS", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_Visitable = PACKid_$metamodel$.getClassId("Visitable", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull DataTypeId DATAid_EInt = PACKid_http_c_s_s_www_eclipse_org_s_emf_s_2002_s_Ecore.getDataTypeId("EInt", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull IntegerValue INT_1 = ValueUtil.integerValueOf("1");
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull CollectionTypeId ORD_PRIMid_String = TypeId.ORDERED_SET.getSpecializedId(TypeId.STRING);
    public static final /*@NonInvalid*/ java.lang.@org.eclipse.jdt.annotation.NonNull String STR__33_resolve = "!resolve";
    public static final /*@NonInvalid*/ java.lang.@org.eclipse.jdt.annotation.NonNull String STR_compososes = "compososes";
    public static final /*@NonInvalid*/ java.lang.@org.eclipse.jdt.annotation.NonNull String STR_derived = "derived";
    public static final /*@NonInvalid*/ java.lang.@org.eclipse.jdt.annotation.NonNull String STR_id = "id";
    public static final /*@NonInvalid*/ java.lang.@org.eclipse.jdt.annotation.NonNull String STR_readonly = "readonly";
    public static final /*@NonInvalid*/ java.lang.@org.eclipse.jdt.annotation.NonNull String STR_static = "static";
    public static final /*@NonInvalid*/ java.lang.@org.eclipse.jdt.annotation.NonNull String STR_transient = "transient";
    public static final /*@NonInvalid*/ java.lang.@org.eclipse.jdt.annotation.NonNull String STR_unsettable = "unsettable";
    public static final /*@NonInvalid*/ java.lang.@org.eclipse.jdt.annotation.NonNull String STR_volatile = "volatile";
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull CollectionTypeId COL_CLSSid_Class = TypeId.COLLECTION.getSpecializedId(CLSSid_Class);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull CollectionTypeId ORD_CLSSid_AnnotationElementCS = TypeId.ORDERED_SET.getSpecializedId(CLSSid_AnnotationElementCS);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull CollectionTypeId ORD_CLSSid_ClassCS = TypeId.ORDERED_SET.getSpecializedId(CLSSid_ClassCS);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull CollectionTypeId ORD_CLSSid_ConstraintCS = TypeId.ORDERED_SET.getSpecializedId(CLSSid_ConstraintCS);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull CollectionTypeId ORD_CLSSid_DetailCS = TypeId.ORDERED_SET.getSpecializedId(CLSSid_DetailCS);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull CollectionTypeId ORD_CLSSid_EnumerationLiteralCS = TypeId.ORDERED_SET.getSpecializedId(CLSSid_EnumerationLiteralCS);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull CollectionTypeId ORD_CLSSid_ImportCS = TypeId.ORDERED_SET.getSpecializedId(CLSSid_ImportCS);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull CollectionTypeId ORD_CLSSid_ModelElementCS = TypeId.ORDERED_SET.getSpecializedId(CLSSid_ModelElementCS);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull CollectionTypeId ORD_CLSSid_ModelElementRefCS = TypeId.ORDERED_SET.getSpecializedId(CLSSid_ModelElementRefCS);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull CollectionTypeId ORD_CLSSid_OperationCS = TypeId.ORDERED_SET.getSpecializedId(CLSSid_OperationCS);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull CollectionTypeId ORD_CLSSid_PackageCS = TypeId.ORDERED_SET.getSpecializedId(CLSSid_PackageCS);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull CollectionTypeId ORD_CLSSid_ParameterCS = TypeId.ORDERED_SET.getSpecializedId(CLSSid_ParameterCS);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull CollectionTypeId ORD_CLSSid_PathElementCS = TypeId.ORDERED_SET.getSpecializedId(CLSSid_PathElementCS);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull CollectionTypeId ORD_CLSSid_Property = TypeId.ORDERED_SET.getSpecializedId(CLSSid_Property);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull CollectionTypeId ORD_CLSSid_SpecificationCS = TypeId.ORDERED_SET.getSpecializedId(CLSSid_SpecificationCS);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull CollectionTypeId ORD_CLSSid_StructuralFeatureCS = TypeId.ORDERED_SET.getSpecializedId(CLSSid_StructuralFeatureCS);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull CollectionTypeId ORD_CLSSid_TemplateParameter = TypeId.ORDERED_SET.getSpecializedId(CLSSid_TemplateParameter);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull CollectionTypeId ORD_CLSSid_TemplateParameterCS = TypeId.ORDERED_SET.getSpecializedId(CLSSid_TemplateParameterCS);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull CollectionTypeId ORD_CLSSid_TemplateParameterSubstitutionCS = TypeId.ORDERED_SET.getSpecializedId(CLSSid_TemplateParameterSubstitutionCS);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull CollectionTypeId ORD_CLSSid_TuplePartCS = TypeId.ORDERED_SET.getSpecializedId(CLSSid_TuplePartCS);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull CollectionTypeId ORD_CLSSid_TypedRefCS = TypeId.ORDERED_SET.getSpecializedId(CLSSid_TypedRefCS);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull PropertyId PROPid_isImplicit = CLSSid_Property.getPropertyId("isImplicit");
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull PropertyId PROPid_isRequired = CLSSid_TypedElement.getPropertyId("isRequired");
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull PropertyId PROPid_name = CLSSid_NamedElement.getPropertyId("name");
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull PropertyId PROPid_owningClass = CLSSid_Property.getPropertyId("owningClass");
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull CollectionTypeId SEQ_CLSSid_Class = TypeId.SEQUENCE.getSpecializedId(CLSSid_Class);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull CollectionTypeId SEQ_CLSSid_Constraint = TypeId.SEQUENCE.getSpecializedId(CLSSid_Constraint);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull CollectionTypeId SEQ_CLSSid_Detail = TypeId.SEQUENCE.getSpecializedId(CLSSid_Detail);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull CollectionTypeId SEQ_CLSSid_Element = TypeId.SEQUENCE.getSpecializedId(CLSSid_Element);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull CollectionTypeId SEQ_CLSSid_EnumerationLiteral = TypeId.SEQUENCE.getSpecializedId(CLSSid_EnumerationLiteral);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull CollectionTypeId SEQ_CLSSid_Import = TypeId.SEQUENCE.getSpecializedId(CLSSid_Import);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull CollectionTypeId SEQ_CLSSid_LanguageExpression = TypeId.SEQUENCE.getSpecializedId(CLSSid_LanguageExpression);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull CollectionTypeId SEQ_CLSSid_Operation = TypeId.SEQUENCE.getSpecializedId(CLSSid_Operation);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull CollectionTypeId SEQ_CLSSid_Package = TypeId.SEQUENCE.getSpecializedId(CLSSid_Package);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull CollectionTypeId SEQ_CLSSid_Parameter = TypeId.SEQUENCE.getSpecializedId(CLSSid_Parameter);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull CollectionTypeId SEQ_CLSSid_Property = TypeId.SEQUENCE.getSpecializedId(CLSSid_Property);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull CollectionTypeId SEQ_CLSSid_TemplateParameter = TypeId.SEQUENCE.getSpecializedId(CLSSid_TemplateParameter);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull CollectionTypeId SEQ_CLSSid_TemplateParameterSubstitution = TypeId.SEQUENCE.getSpecializedId(CLSSid_TemplateParameterSubstitution);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull CollectionTypeId SET_CLSSid_AnnotationCS = TypeId.SET.getSpecializedId(CLSSid_AnnotationCS);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull CollectionTypeId SET_CLSSid_AttributeCS = TypeId.SET.getSpecializedId(CLSSid_AttributeCS);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull CollectionTypeId SET_CLSSid_Class = TypeId.SET.getSpecializedId(CLSSid_Class);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull CollectionTypeId SET_CLSSid_ConstraintCS = TypeId.SET.getSpecializedId(CLSSid_ConstraintCS);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull CollectionTypeId SET_CLSSid_DataTypeCS = TypeId.SET.getSpecializedId(CLSSid_DataTypeCS);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull CollectionTypeId SET_CLSSid_DetailCS = TypeId.SET.getSpecializedId(CLSSid_DetailCS);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull CollectionTypeId SET_CLSSid_EnumerationCS = TypeId.SET.getSpecializedId(CLSSid_EnumerationCS);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull CollectionTypeId SET_CLSSid_EnumerationLiteralCS = TypeId.SET.getSpecializedId(CLSSid_EnumerationLiteralCS);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull CollectionTypeId SET_CLSSid_ImportCS = TypeId.SET.getSpecializedId(CLSSid_ImportCS);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull CollectionTypeId SET_CLSSid_LambdaTypeCS = TypeId.SET.getSpecializedId(CLSSid_LambdaTypeCS);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull CollectionTypeId SET_CLSSid_OperationCS = TypeId.SET.getSpecializedId(CLSSid_OperationCS);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull CollectionTypeId SET_CLSSid_PackageCS = TypeId.SET.getSpecializedId(CLSSid_PackageCS);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull CollectionTypeId SET_CLSSid_ParameterCS = TypeId.SET.getSpecializedId(CLSSid_ParameterCS);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull CollectionTypeId SET_CLSSid_PrimitiveTypeRefCS = TypeId.SET.getSpecializedId(CLSSid_PrimitiveTypeRefCS);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull CollectionTypeId SET_CLSSid_ReferenceCS = TypeId.SET.getSpecializedId(CLSSid_ReferenceCS);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull CollectionTypeId SET_CLSSid_RootPackageCS = TypeId.SET.getSpecializedId(CLSSid_RootPackageCS);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull CollectionTypeId SET_CLSSid_SpecificationCS = TypeId.SET.getSpecializedId(CLSSid_SpecificationCS);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull CollectionTypeId SET_CLSSid_StructuredClassCS = TypeId.SET.getSpecializedId(CLSSid_StructuredClassCS);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull CollectionTypeId SET_CLSSid_TemplateBindingCS = TypeId.SET.getSpecializedId(CLSSid_TemplateBindingCS);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull CollectionTypeId SET_CLSSid_TemplateParameterSubstitutionCS = TypeId.SET.getSpecializedId(CLSSid_TemplateParameterSubstitutionCS);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull CollectionTypeId SET_CLSSid_TemplateSignatureCS = TypeId.SET.getSpecializedId(CLSSid_TemplateSignatureCS);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull CollectionTypeId SET_CLSSid_TuplePartCS = TypeId.SET.getSpecializedId(CLSSid_TuplePartCS);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull CollectionTypeId SET_CLSSid_TupleTypeCS = TypeId.SET.getSpecializedId(CLSSid_TupleTypeCS);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull CollectionTypeId SET_CLSSid_TypeParameterCS = TypeId.SET.getSpecializedId(CLSSid_TypeParameterCS);
    
    /*
     * Array of the ClassIds of each class for which allInstances() may be invoked. Array index is the ClassIndex.
     */
    private static final @NonNull ClassId[] classIndex2classId = new @NonNull ClassId[]{
        CLSSid_AnnotationCS,            // 0 => AnnotationCS
        CLSSid_AttributeCS,             // 1 => AttributeCS
        CLSSid_ConstraintCS,            // 2 => ConstraintCS
        CLSSid_DataTypeCS,              // 3 => DataTypeCS
        CLSSid_DetailCS,                // 4 => DetailCS
        CLSSid_EnumerationCS,           // 5 => EnumerationCS
        CLSSid_EnumerationLiteralCS,    // 6 => EnumerationLiteralCS
        CLSSid_ImportCS,                // 7 => ImportCS
        CLSSid_LambdaTypeCS,            // 8 => LambdaTypeCS
        CLSSid_OperationCS,             // 9 => OperationCS
        CLSSid_PackageCS,               // 10 => PackageCS
        CLSSid_ParameterCS,             // 11 => ParameterCS
        CLSSid_PrimitiveTypeRefCS,      // 12 => PrimitiveTypeRefCS
        CLSSid_ReferenceCS,             // 13 => ReferenceCS
        CLSSid_RootPackageCS,           // 14 => RootPackageCS
        CLSSid_SpecificationCS,         // 15 => SpecificationCS
        CLSSid_StructuredClassCS,       // 16 => StructuredClassCS
        CLSSid_TemplateBindingCS,       // 17 => TemplateBindingCS
        CLSSid_TemplateParameterSubstitutionCS,// 18 => TemplateParameterSubstitutionCS
        CLSSid_TemplateSignatureCS,     // 19 => TemplateSignatureCS
        CLSSid_TuplePartCS,             // 20 => TuplePartCS
        CLSSid_TupleTypeCS,             // 21 => TupleTypeCS
        CLSSid_TypeParameterCS          // 22 => TypeParameterCS
    };
    
    /*
     * Mapping from each ClassIndex to all the ClassIndexes to which an object of the outer index
     * may contribute results to an allInstances() invocation.
     * Non trivial inner arrays arise when one ClassId is a derivation of another and so an
     * instance of the derived classId contributes to derived and inherited ClassIndexes.
     */
    private final static int @NonNull [] @NonNull [] classIndex2allClassIndexes = new int @NonNull [] @NonNull [] {
        {0},                    // 0 : AnnotationCS -> {AnnotationCS}
        {1},                    // 1 : AttributeCS -> {AttributeCS}
        {2},                    // 2 : ConstraintCS -> {ConstraintCS}
        {3},                    // 3 : DataTypeCS -> {DataTypeCS}
        {4},                    // 4 : DetailCS -> {DetailCS}
        {5},                    // 5 : EnumerationCS -> {EnumerationCS}
        {6},                    // 6 : EnumerationLiteralCS -> {EnumerationLiteralCS}
        {7},                    // 7 : ImportCS -> {ImportCS}
        {8},                    // 8 : LambdaTypeCS -> {LambdaTypeCS}
        {9},                    // 9 : OperationCS -> {OperationCS}
        {10},                   // 10 : PackageCS -> {PackageCS}
        {11},                   // 11 : ParameterCS -> {ParameterCS}
        {12},                   // 12 : PrimitiveTypeRefCS -> {PrimitiveTypeRefCS}
        {13},                   // 13 : ReferenceCS -> {ReferenceCS}
        {14},                   // 14 : RootPackageCS -> {RootPackageCS}
        {15},                   // 15 : SpecificationCS -> {SpecificationCS}
        {16},                   // 16 : StructuredClassCS -> {StructuredClassCS}
        {17},                   // 17 : TemplateBindingCS -> {TemplateBindingCS}
        {18},                   // 18 : TemplateParameterSubstitutionCS -> {TemplateParameterSubstitutionCS}
        {19},                   // 19 : TemplateSignatureCS -> {TemplateSignatureCS}
        {20},                   // 20 : TuplePartCS -> {TuplePartCS}
        {21},                   // 21 : TupleTypeCS -> {TupleTypeCS}
        {22}                    // 22 : TypeParameterCS -> {TypeParameterCS}
    };
    
    private final PivotLookupSolver lookupSolver = new PivotLookupSolver(executor);
    
    public BaseCS2AS_qvtp_qvtcas(final @NonNull Executor executor) throws ReflectiveOperationException {
        super(executor, new @NonNull String[] {"leftCS", "rightAS"}, null, classIndex2classId, classIndex2allClassIndexes);
    }
    
    @Override
    public boolean run() throws ReflectiveOperationException {
        return MAP___root__() && invocationManager.flush();
    }
    
    /**
     * pivot::Visitable::lookupNamespace(segments : OrderedSet(basecs::PathElementCS)) : Namespace[?]
     * 
     * 
     * if segments->size() = 1
     * then lookupNamespace(segments->first())
     * else
     *   let
     *     qualifierSegments : OrderedSet(basecs::PathElementCS) = segments->subOrderedSet(1,
     *       segments->size() - 1)
     *   in
     *     let
     *       qualifier : Namespace[?] = lookupNamespace(qualifierSegments)
     *     in qualifier?.lookupQualifiedNamespace(segments->last())
     * endif
     */
    public /*@NonInvalid*/ Namespace lookupNamespace(final /*@NonInvalid*/ org.eclipse.ocl.pivot.util.@org.eclipse.jdt.annotation.NonNull Visitable self_0, final /*@NonInvalid*/ List<PathElementCS> segments) {
        final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull IdResolver idResolver = executor.getIdResolver();
        final /*@NonInvalid*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull OrderedSetValue BOXED_segments = idResolver.createOrderedSetOfAll(ORD_CLSSid_PathElementCS, segments);
        final /*@NonInvalid*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull IntegerValue size = CollectionSizeOperation.INSTANCE.evaluate(BOXED_segments);
        final /*@NonInvalid*/ boolean eq = size.equals(INT_1);
        /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Namespace symbol_1;
        if (eq) {
            final /*@Thrown*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.Nullable PathElementCS aPathElementCS = (PathElementCS)OrderedCollectionFirstOperation.INSTANCE.evaluate(BOXED_segments);
            if (aPathElementCS == null) {
                throw new InvalidValueException("Null source for \'\'http://www.eclipse.org/ocl/2015/BaseCS\'::PathElementCS::name\'");
            }
            @SuppressWarnings("null")
            final /*@Thrown*/ java.lang.@org.eclipse.jdt.annotation.NonNull String name = aPathElementCS.getName();
            PivotLookupResult<Namespace> _lookupResult = lookupSolver._lookupUnqualifiedNamespace(self_0, name);
            Namespace _lookupUnqualifiedNamespace = null;
            if (_lookupResult.size() == 1) {
                _lookupUnqualifiedNamespace = _lookupResult.getSingleResult();
            } else {
                handleLookupError(aPathElementCS,name);
            };
            symbol_1 = _lookupUnqualifiedNamespace;
        }
        else {
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull IntegerValue diff = (IntegerValue)NumericMinusOperation.INSTANCE.evaluate(size, INT_1);
            final /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull OrderedSetValue qualifierSegments = OrderedSetSubOrderedSetOperation.INSTANCE.evaluate(BOXED_segments, INT_1, diff);
            final List<PathElementCS> UNBOXED_qualifierSegments = qualifierSegments.asEcoreObjects(idResolver, PathElementCS.class);
            assert UNBOXED_qualifierSegments != null;
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Namespace qualifier = this.lookupNamespace(self_0, UNBOXED_qualifierSegments);
            /*@Caught*/ @Nullable Object CAUGHT_qualifier;
            try {
                CAUGHT_qualifier = qualifier;
            }
            catch (Exception e) {
                CAUGHT_qualifier = ValueUtil.createInvalidValue(e);
            }
            final /*@NonInvalid*/ @NonNull Object symbol_0 = CAUGHT_qualifier == null;
            /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Namespace safe_lookupQualifiedNamespace_source;
            if (symbol_0 == Boolean.TRUE) {
                safe_lookupQualifiedNamespace_source = null;
            }
            else {
                if (qualifier == null) {
                    throw new InvalidValueException("Null where non-null value required");
                }
                final /*@Thrown*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.Nullable PathElementCS aPathElementCS_0 = (PathElementCS)OrderedCollectionLastOperation.INSTANCE.evaluate(BOXED_segments);
                if (aPathElementCS_0 == null) {
                    throw new InvalidValueException("Null source for \'\'http://www.eclipse.org/ocl/2015/BaseCS\'::PathElementCS::name\'");
                }
                @SuppressWarnings("null")
                final /*@Thrown*/ java.lang.@org.eclipse.jdt.annotation.NonNull String name_0 = aPathElementCS_0.getName();
                PivotLookupResult<Namespace> _lookupResult_0 = lookupSolver._lookupQualifiedNamespace(qualifier, name_0);
                Namespace _lookupQualifiedNamespace = null;
                if (_lookupResult_0.size() == 1) {
                    _lookupQualifiedNamespace = _lookupResult_0.getSingleResult();
                } else {
                    handleLookupError(aPathElementCS_0,name_0);
                };
                safe_lookupQualifiedNamespace_source = _lookupQualifiedNamespace;
            }
            symbol_1 = safe_lookupQualifiedNamespace_source;
        }
        return symbol_1;
    }
    
    /**
     * 
     * map __root__ in BaseCS2AS_qvtp_qvtcas {
     *   leftCS ( |)
     * { |}
     * where ( |)
     * {_'\u00ABAnnotationCS\u00BB' : Set(basecs::AnnotationCS) = leftCS.objectsOfKind(basecs::AnnotationCS)
     *   ;
     * _'\u00ABAttributeCS\u00BB' : Set(basecs::AttributeCS) = leftCS.objectsOfKind(basecs::AttributeCS)
     *   ;
     * _'\u00ABConstraintCS\u00BB' : Set(basecs::ConstraintCS) = leftCS.objectsOfKind(basecs::ConstraintCS)
     *   ;
     * _'\u00ABDataTypeCS\u00BB' : Set(basecs::DataTypeCS) = leftCS.objectsOfKind(basecs::DataTypeCS)
     *   ;
     * _'\u00ABDetailCS\u00BB' : Set(basecs::DetailCS) = leftCS.objectsOfKind(basecs::DetailCS)
     *   ;
     * _'\u00ABEnumerationCS\u00BB' : Set(basecs::EnumerationCS) = leftCS.objectsOfKind(basecs::EnumerationCS)
     *   ;
     * _'\u00ABEnumerationLiteralCS\u00BB' : Set(basecs::EnumerationLiteralCS) = leftCS.objectsOfKind(basecs::EnumerationLiteralCS)
     *   ;
     * _'\u00ABImportCS\u00BB' : Set(basecs::ImportCS) = leftCS.objectsOfKind(basecs::ImportCS)
     *   ;
     * _'\u00ABLambdaTypeCS\u00BB' : Set(basecs::LambdaTypeCS) = leftCS.objectsOfKind(basecs::LambdaTypeCS)
     *   ;
     * _'\u00ABOperationCS\u00BB' : Set(basecs::OperationCS) = leftCS.objectsOfKind(basecs::OperationCS)
     *   ;
     * _'\u00ABPackageCS\u00BB' : Set(basecs::PackageCS) = leftCS.objectsOfKind(basecs::PackageCS)
     *   ;
     * _'\u00ABParameterCS\u00BB' : Set(basecs::ParameterCS) = leftCS.objectsOfKind(basecs::ParameterCS)
     *   ;
     * _'\u00ABPrimitiveTypeRefCS\u00BB' : Set(basecs::PrimitiveTypeRefCS) = leftCS.objectsOfKind(basecs::PrimitiveTypeRefCS)
     *   ;
     * _'\u00ABReferenceCS\u00BB' : Set(basecs::ReferenceCS) = leftCS.objectsOfKind(basecs::ReferenceCS)
     *   ;
     * _'\u00ABRootPackageCS\u00BB' : Set(basecs::RootPackageCS) = leftCS.objectsOfKind(basecs::RootPackageCS)
     *   ;
     * _'\u00ABSpecificationCS\u00BB' : Set(basecs::SpecificationCS) = leftCS.objectsOfKind(basecs::SpecificationCS)
     *   ;
     * _'\u00ABStructuredClassCS\u00BB' : Set(basecs::StructuredClassCS) = leftCS.objectsOfKind(basecs::StructuredClassCS)
     *   ;
     * _'\u00ABTemplateBindingCS\u00BB' : Set(basecs::TemplateBindingCS) = leftCS.objectsOfKind(basecs::TemplateBindingCS)
     *   ;
     * _'\u00ABTemplateParameterSubstitutionCS\u00BB' : Set(basecs::TemplateParameterSubstitutionCS) = leftCS.objectsOfKind(basecs::TemplateParameterSubstitutionCS)
     *   ;
     * _'\u00ABTemplateSignatureCS\u00BB' : Set(basecs::TemplateSignatureCS) = leftCS.objectsOfKind(basecs::TemplateSignatureCS)
     *   ;
     * _'\u00ABTuplePartCS\u00BB' : Set(basecs::TuplePartCS) = leftCS.objectsOfKind(basecs::TuplePartCS)
     *   ;
     * _'\u00ABTupleTypeCS\u00BB' : Set(basecs::TupleTypeCS) = leftCS.objectsOfKind(basecs::TupleTypeCS)
     *   ;
     * _'\u00ABTypeParameterCS\u00BB' : Set(basecs::TypeParameterCS) = leftCS.objectsOfKind(basecs::TypeParameterCS)
     *   ;
     * ji_AnnotationCS : Set(basecs::AnnotationCS[*|1]) = _'\u00ABAnnotationCS\u00BB';
     * ji_AttributeCS : Set(basecs::AttributeCS[*|1]) = _'\u00ABAttributeCS\u00BB';
     * ji_ConstraintCS : Set(basecs::ConstraintCS[*|1]) = _'\u00ABConstraintCS\u00BB';
     * ji_DataTypeCS : Set(basecs::DataTypeCS[*|1]) = _'\u00ABDataTypeCS\u00BB';
     * ji_DetailCS : Set(basecs::DetailCS[*|1]) = _'\u00ABDetailCS\u00BB';
     * ji_EnumerationCS : Set(basecs::EnumerationCS[*|1]) = _'\u00ABEnumerationCS\u00BB';
     * ji_EnumerationLiteralCS : Set(basecs::EnumerationLiteralCS[*|1]) = _'\u00ABEnumerationLiteralCS\u00BB';
     * ji_ImportCS : Set(basecs::ImportCS[*|1]) = _'\u00ABImportCS\u00BB';
     * ji_LambdaTypeCS : Set(basecs::LambdaTypeCS[*|1]) = _'\u00ABLambdaTypeCS\u00BB';
     * ji_OperationCS : Set(basecs::OperationCS[*|1]) = _'\u00ABOperationCS\u00BB';
     * ji_PackageCS : Set(basecs::PackageCS[*|1]) = _'\u00ABPackageCS\u00BB';
     * ji_ParameterCS : Set(basecs::ParameterCS[*|1]) = _'\u00ABParameterCS\u00BB';
     * ji_PrimitiveTypeRefCS : Set(basecs::PrimitiveTypeRefCS[*|1]) = _'\u00ABPrimitiveTypeRefCS\u00BB';
     * ji_ReferenceCS : Set(basecs::ReferenceCS[*|1]) = _'\u00ABReferenceCS\u00BB';
     * ji_RootPackageCS : Set(basecs::RootPackageCS[*|1]) = _'\u00ABRootPackageCS\u00BB';
     * ji_SpecificationCS : Set(basecs::SpecificationCS[*|1]) = _'\u00ABSpecificationCS\u00BB';
     * ji_StructuredClassCS : Set(basecs::StructuredClassCS[*|1]) = _'\u00ABStructuredClassCS\u00BB';
     * ji_TemplateBindingCS : Set(basecs::TemplateBindingCS[*|1]) = _'\u00ABTemplateBindingCS\u00BB';
     * ji_TemplateParameterSubstitutionCS : Set(basecs::TemplateParameterSubstitutionCS[*|1]) = _'\u00ABTemplateParameterSubstitutionCS\u00BB';
     * ji_TemplateParameterSubstitutionCS_1 : Set(basecs::TemplateParameterSubstitutionCS[*|1]) = _'\u00ABTemplateParameterSubstitutionCS\u00BB'->select(i | i.owningBinding <> null)
     *   ;
     * ji_TemplateSignatureCS : Set(basecs::TemplateSignatureCS[*|1]) = _'\u00ABTemplateSignatureCS\u00BB';
     * ji_TuplePartCS : Set(basecs::TuplePartCS[*|1]) = _'\u00ABTuplePartCS\u00BB';
     * ji_TupleTypeCS : Set(basecs::TupleTypeCS[*|1]) = _'\u00ABTupleTypeCS\u00BB';
     * ji_TypeParameterCS : Set(basecs::TypeParameterCS[*|1]) = _'\u00ABTypeParameterCS\u00BB';
     *  |}
     * for loop0 : basecs::AnnotationCS in ji_AnnotationCS {
     *     call m_AnnotationCS_pivot {
     * lAnnotationCS := loop0;
     * }}
     *   for loop0 : basecs::AttributeCS in ji_AttributeCS {
     *     call m_AttributeCS_pivot {
     * lAttributeCS := loop0;
     * }}
     *   for loop0 : basecs::ConstraintCS in ji_ConstraintCS {
     *     call m_ConstraintCS_pivot {
     * lConstraintCS := loop0;
     * }}
     *   for loop0 : basecs::DataTypeCS in ji_DataTypeCS {
     *     call m_DataTypeCS_pivot {
     * lDataTypeCS := loop0;
     * }}
     *   for loop0 : basecs::DetailCS in ji_DetailCS {
     *     call m_DetailCS_pivot {
     * lDetailCS := loop0;
     * }}
     *   for loop0 : basecs::EnumerationCS in ji_EnumerationCS {
     *     call m_EnumerationCS_pivot {
     * lEnumerationCS := loop0;
     * }}
     *   for loop0 : basecs::EnumerationLiteralCS in ji_EnumerationLiteralCS {
     *     call m_EnumerationLiteralCS_pivot {
     * lEnumerationLiteralCS := loop0;
     * }}
     *   for loop0 : basecs::ImportCS in ji_ImportCS {
     *     call m_ImportCS_pivot {
     * lImportCS := loop0;
     * }}
     *   for loop0 : basecs::LambdaTypeCS in ji_LambdaTypeCS {
     *     call m_LambdaTypeCS_pivot {
     * lLambdaTypeCS := loop0;
     * }}
     *   for loop0 : basecs::OperationCS in ji_OperationCS {
     *     call m_OperationCS_pivot {
     * lOperationCS := loop0;
     * }}
     *   for loop0 : basecs::PackageCS in ji_PackageCS {
     *     call m_PackageCS_pivot {
     * lPackageCS := loop0;
     * }}
     *   for loop0 : basecs::ParameterCS in ji_ParameterCS {
     *     call m_ParameterCS_pivot {
     * lParameterCS := loop0;
     * }}
     *   for loop0 : basecs::PrimitiveTypeRefCS in ji_PrimitiveTypeRefCS {
     *     call m_PrimitiveTypeRefCS_pivot {
     * lPrimitiveTypeRefCS := loop0;
     * }}
     *   for loop0 : basecs::ReferenceCS in ji_ReferenceCS {
     *     call m_ReferenceCS_pivot {
     * lReferenceCS := loop0;
     * }}
     *   for loop0 : basecs::RootPackageCS in ji_RootPackageCS {
     *     call m_RootPackageCS_pivot {
     * lRootPackageCS := loop0;
     * }}
     *   for loop0 : basecs::SpecificationCS in ji_SpecificationCS {
     *     call m_SpecificationCS_pivot {
     * lSpecificationCS := loop0;
     * }}
     *   for loop0 : basecs::StructuredClassCS in ji_StructuredClassCS {
     *     call m_StructuredClassCS_pivot {
     * lStructuredClassCS := loop0;
     * }}
     *   for loop0 : basecs::TemplateBindingCS in ji_TemplateBindingCS {
     *     call m_TemplateBindingCS_pivot {
     * lTemplateBindingCS := loop0;
     * }}
     *   for loop0 : basecs::TemplateParameterSubstitutionCS in ji_TemplateParameterSubstitutionCS {
     *     call m_TemplateParameterSubstitutionCS_pivot {
     * lTemplateParameterSubstitutionCS := loop0;
     * }}
     *   for loop0 : basecs::TemplateSignatureCS in ji_TemplateSignatureCS {
     *     call m_TemplateSignatureCS_pivot {
     * lTemplateSignatureCS := loop0;
     * }}
     *   for loop0 : basecs::TuplePartCS in ji_TuplePartCS {
     *     call m_TuplePartCS_pivot {
     * lTuplePartCS := loop0;
     * }}
     *   for loop0 : basecs::TupleTypeCS in ji_TupleTypeCS {
     *     call m_TupleTypeCS_pivot {
     * lTupleTypeCS := loop0;
     * }}
     *   for loop0 : basecs::TypeParameterCS in ji_TypeParameterCS {
     *     call m_TypeParameterCS_pivot {
     * lTypeParameterCS := loop0;
     * }}
     *   for loop0 : basecs::AnnotationCS in ji_AnnotationCS {
     *     call m_AnnotationCS_name_ownedAnnotations_ownedContents {
     * lAnnotationCS := loop0;
     * }}
     *   for loop0 : basecs::AttributeCS in ji_AttributeCS {
     *     call m_AttributeCS_default_name_ownedAnnotations_pivot_ {
     * lAttributeCS := loop0;
     * }}
     *   for loop0 : basecs::AttributeCS in ji_AttributeCS {
     *     call m_AttributeCS_ownedDefaultExpressions_pivot {
     * lAttributeCS := loop0;
     * }}
     *   for loop0 : basecs::AttributeCS in ji_AttributeCS {
     *     call m_AttributeCS_ownedType_pivot {
     * lAttributeCS := loop0;
     * }}
     *   for loop0 : basecs::ConstraintCS in ji_ConstraintCS {
     *     call m_ConstraintCS_ownedSpecification_pivot {
     * lConstraintCS := loop0;
     * }}
     *   for loop0 : basecs::DataTypeCS in ji_DataTypeCS {
     *     call m_DataTypeCS_instanceClassName_isSerializable_name {
     * lDataTypeCS := loop0;
     * }}
     *   for loop0 : basecs::DataTypeCS in ji_DataTypeCS {
     *     call m_DataTypeCS_ownedSignature_pivot {
     * lDataTypeCS := loop0;
     * }}
     *   for loop0 : basecs::DetailCS in ji_DetailCS {
     *     call m_DetailCS_name_pivot_values {
     * lDetailCS := loop0;
     * }}
     *   for loop0 : basecs::EnumerationCS in ji_EnumerationCS {
     *     call m_EnumerationCS_instanceClassName_isSerializable_n {
     * lEnumerationCS := loop0;
     * }}
     *   for loop0 : basecs::EnumerationCS in ji_EnumerationCS {
     *     call m_EnumerationCS_ownedSignature_pivot {
     * lEnumerationCS := loop0;
     * }}
     *   for loop0 : basecs::EnumerationLiteralCS in ji_EnumerationLiteralCS {
     *     call m_EnumerationLiteralCS_name_ownedAnnotations_pivot {
     * lEnumerationLiteralCS := loop0;
     * }}
     *   for loop0 : basecs::ImportCS in ji_ImportCS {
     *     call m_ImportCS_name_pivot {
     * lImportCS := loop0;
     * }}
     *   for loop0 : basecs::LambdaTypeCS in ji_LambdaTypeCS {
     *     call m_LambdaTypeCS_ownedContextType_pivot {
     * lLambdaTypeCS := loop0;
     * }}
     *   for loop0 : basecs::LambdaTypeCS in ji_LambdaTypeCS {
     *     call m_LambdaTypeCS_name_ownedParameterTypes_pivot {
     * lLambdaTypeCS := loop0;
     * }}
     *   for loop0 : basecs::LambdaTypeCS in ji_LambdaTypeCS {
     *     call m_LambdaTypeCS_ownedResultType_pivot {
     * lLambdaTypeCS := loop0;
     * }}
     *   for loop0 : basecs::OperationCS in ji_OperationCS {
     *     call m_OperationCS_ownedBodyExpressions_pivot {
     * lOperationCS := loop0;
     * }}
     *   for loop0 : basecs::OperationCS in ji_OperationCS {
     *     call m_OperationCS_name_ownedAnnotations_ownedException {
     * lOperationCS := loop0;
     * }}
     *   for loop0 : basecs::OperationCS in ji_OperationCS {
     *     call m_OperationCS_ownedSignature_pivot {
     * lOperationCS := loop0;
     * }}
     *   for loop0 : basecs::OperationCS in ji_OperationCS {
     *     call m_OperationCS_ownedType_pivot {
     * lOperationCS := loop0;
     * }}
     *   for loop0 : basecs::PackageCS in ji_PackageCS {
     *     call m_PackageCS_name_nsPrefix_nsURI_ownedAnnotations_o {
     * lPackageCS := loop0;
     * }}
     *   for loop0 : basecs::ParameterCS in ji_ParameterCS {
     *     call m_ParameterCS_name_ownedAnnotations_pivot {
     * lParameterCS := loop0;
     * }}
     *   for loop0 : basecs::ParameterCS in ji_ParameterCS {
     *     call m_ParameterCS_ownedType_pivot {
     * lParameterCS := loop0;
     * }}
     *   for loop0 : basecs::PrimitiveTypeRefCS in ji_PrimitiveTypeRefCS {
     *     call m_PrimitiveTypeRefCS_name_pivot {
     * lPrimitiveTypeRefCS := loop0;
     * }}
     *   for loop0 : basecs::ReferenceCS in ji_ReferenceCS {
     *     call m_ReferenceCS_default_name_ownedAnnotations_pivot_ {
     * lReferenceCS := loop0;
     * }}
     *   for loop0 : basecs::ReferenceCS in ji_ReferenceCS {
     *     call m_ReferenceCS_ownedDefaultExpressions_ownedDefault {
     * lReferenceCS := loop0;
     * }}
     *   for loop0 : basecs::ReferenceCS in ji_ReferenceCS {
     *     call m_ReferenceCS_ownedType_pivot {
     * lReferenceCS := loop0;
     * }}
     *   for loop0 : basecs::RootPackageCS in ji_RootPackageCS {
     *     call m_RootPackageCS_ownedImports_ownedPackages_pivot {
     * lRootPackageCS := loop0;
     * }}
     *   for loop0 : basecs::SpecificationCS in ji_SpecificationCS {
     *     call m_SpecificationCS_exprString_pivot {
     * lSpecificationCS := loop0;
     * }}
     *   for loop0 : basecs::StructuredClassCS in ji_StructuredClassCS {
     *     call m_StructuredClassCS_instanceClassName_isAbstract_i {
     * lStructuredClassCS := loop0;
     * }}
     *   for loop0 : basecs::ReferenceCS in ji_ReferenceCS {
     *     call m_ReferenceCS_ownedType_pivot_referredOpposite {
     * lReferenceCS := loop0;
     * }}
     *   for loop0 : basecs::StructuredClassCS in ji_StructuredClassCS {
     *     call m_StructuredClassCS_ownedSignature_pivot {
     * lStructuredClassCS := loop0;
     * }}
     *   for loop0 : basecs::TemplateBindingCS in ji_TemplateBindingCS {
     *     call m_TemplateBindingCS_ownedSubstitutions_pivot {
     * lTemplateBindingCS := loop0;
     * }}
     *   for loop0 : basecs::TemplateParameterSubstitutionCS in ji_TemplateParameterSubstitutionCS {
     *     call m_TemplateParameterSubstitutionCS_ownedActualParam {
     * lTemplateParameterSubstitutionCS := loop0;
     * }}
     *   for loop0 : basecs::TemplateParameterSubstitutionCS in ji_TemplateParameterSubstitutionCS_1 {
     *     call m_TemplateParameterSubstitutionCS_owningBinding_pi {
     * lTemplateParameterSubstitutionCS := loop0;
     * }}
     *   for loop0 : basecs::TuplePartCS in ji_TuplePartCS {
     *     call m_TuplePartCS_name_pivot {
     * lTuplePartCS := loop0;
     * }}
     *   for loop0 : basecs::TuplePartCS in ji_TuplePartCS {
     *     call m_TuplePartCS_ownedType_pivot {
     * lTuplePartCS := loop0;
     * }}
     *   for loop0 : basecs::TupleTypeCS in ji_TupleTypeCS {
     *     call m_TupleTypeCS_name_ownedParts_pivot {
     * lTupleTypeCS := loop0;
     * }}
     *   for loop0 : basecs::ImportCS in ji_ImportCS {
     *     call m_ImportCS_ownedPathName_pivot {
     * lImportCS := loop0;
     * }}
     *   for loop0 : basecs::TypeParameterCS in ji_TypeParameterCS {
     *     call m_TypeParameterCS_name_ownedExtends_pivot {
     * lTypeParameterCS := loop0;
     * }}
     */
    protected boolean MAP___root__() throws ReflectiveOperationException {
        try {
            // predicates and unrealized variables
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull IdResolver idResolver = executor.getIdResolver();
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class TYP_basecs_c_c_AnnotationCS_0 = idResolver.getClass(CLSSid_AnnotationCS, null);
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull SetValue _171_AnnotationCS_187 = ModelObjectsOfKindOperation.INSTANCE.evaluate(executor, SET_CLSSid_AnnotationCS, models[0/*leftCS*/], TYP_basecs_c_c_AnnotationCS_0);
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class TYP_basecs_c_c_AttributeCS_0 = idResolver.getClass(CLSSid_AttributeCS, null);
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull SetValue _171_AttributeCS_187 = ModelObjectsOfKindOperation.INSTANCE.evaluate(executor, SET_CLSSid_AttributeCS, models[0/*leftCS*/], TYP_basecs_c_c_AttributeCS_0);
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class TYP_basecs_c_c_ConstraintCS_0 = idResolver.getClass(CLSSid_ConstraintCS, null);
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull SetValue _171_ConstraintCS_187 = ModelObjectsOfKindOperation.INSTANCE.evaluate(executor, SET_CLSSid_ConstraintCS, models[0/*leftCS*/], TYP_basecs_c_c_ConstraintCS_0);
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class TYP_basecs_c_c_DataTypeCS_0 = idResolver.getClass(CLSSid_DataTypeCS, null);
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull SetValue _171_DataTypeCS_187 = ModelObjectsOfKindOperation.INSTANCE.evaluate(executor, SET_CLSSid_DataTypeCS, models[0/*leftCS*/], TYP_basecs_c_c_DataTypeCS_0);
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class TYP_basecs_c_c_DetailCS_0 = idResolver.getClass(CLSSid_DetailCS, null);
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull SetValue _171_DetailCS_187 = ModelObjectsOfKindOperation.INSTANCE.evaluate(executor, SET_CLSSid_DetailCS, models[0/*leftCS*/], TYP_basecs_c_c_DetailCS_0);
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class TYP_basecs_c_c_EnumerationCS_0 = idResolver.getClass(CLSSid_EnumerationCS, null);
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull SetValue _171_EnumerationCS_187 = ModelObjectsOfKindOperation.INSTANCE.evaluate(executor, SET_CLSSid_EnumerationCS, models[0/*leftCS*/], TYP_basecs_c_c_EnumerationCS_0);
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class TYP_basecs_c_c_EnumerationLiteralCS_0 = idResolver.getClass(CLSSid_EnumerationLiteralCS, null);
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull SetValue _171_EnumerationLiteralCS_187 = ModelObjectsOfKindOperation.INSTANCE.evaluate(executor, SET_CLSSid_EnumerationLiteralCS, models[0/*leftCS*/], TYP_basecs_c_c_EnumerationLiteralCS_0);
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class TYP_basecs_c_c_ImportCS_0 = idResolver.getClass(CLSSid_ImportCS, null);
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull SetValue _171_ImportCS_187 = ModelObjectsOfKindOperation.INSTANCE.evaluate(executor, SET_CLSSid_ImportCS, models[0/*leftCS*/], TYP_basecs_c_c_ImportCS_0);
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class TYP_basecs_c_c_LambdaTypeCS_0 = idResolver.getClass(CLSSid_LambdaTypeCS, null);
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull SetValue _171_LambdaTypeCS_187 = ModelObjectsOfKindOperation.INSTANCE.evaluate(executor, SET_CLSSid_LambdaTypeCS, models[0/*leftCS*/], TYP_basecs_c_c_LambdaTypeCS_0);
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class TYP_basecs_c_c_OperationCS_0 = idResolver.getClass(CLSSid_OperationCS, null);
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull SetValue _171_OperationCS_187 = ModelObjectsOfKindOperation.INSTANCE.evaluate(executor, SET_CLSSid_OperationCS, models[0/*leftCS*/], TYP_basecs_c_c_OperationCS_0);
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class TYP_basecs_c_c_PackageCS_0 = idResolver.getClass(CLSSid_PackageCS, null);
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull SetValue _171_PackageCS_187 = ModelObjectsOfKindOperation.INSTANCE.evaluate(executor, SET_CLSSid_PackageCS, models[0/*leftCS*/], TYP_basecs_c_c_PackageCS_0);
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class TYP_basecs_c_c_ParameterCS_0 = idResolver.getClass(CLSSid_ParameterCS, null);
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull SetValue _171_ParameterCS_187 = ModelObjectsOfKindOperation.INSTANCE.evaluate(executor, SET_CLSSid_ParameterCS, models[0/*leftCS*/], TYP_basecs_c_c_ParameterCS_0);
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class TYP_basecs_c_c_PrimitiveTypeRefCS_0 = idResolver.getClass(CLSSid_PrimitiveTypeRefCS, null);
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull SetValue _171_PrimitiveTypeRefCS_187 = ModelObjectsOfKindOperation.INSTANCE.evaluate(executor, SET_CLSSid_PrimitiveTypeRefCS, models[0/*leftCS*/], TYP_basecs_c_c_PrimitiveTypeRefCS_0);
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class TYP_basecs_c_c_ReferenceCS_0 = idResolver.getClass(CLSSid_ReferenceCS, null);
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull SetValue _171_ReferenceCS_187 = ModelObjectsOfKindOperation.INSTANCE.evaluate(executor, SET_CLSSid_ReferenceCS, models[0/*leftCS*/], TYP_basecs_c_c_ReferenceCS_0);
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class TYP_basecs_c_c_RootPackageCS_0 = idResolver.getClass(CLSSid_RootPackageCS, null);
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull SetValue _171_RootPackageCS_187 = ModelObjectsOfKindOperation.INSTANCE.evaluate(executor, SET_CLSSid_RootPackageCS, models[0/*leftCS*/], TYP_basecs_c_c_RootPackageCS_0);
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class TYP_basecs_c_c_SpecificationCS_0 = idResolver.getClass(CLSSid_SpecificationCS, null);
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull SetValue _171_SpecificationCS_187 = ModelObjectsOfKindOperation.INSTANCE.evaluate(executor, SET_CLSSid_SpecificationCS, models[0/*leftCS*/], TYP_basecs_c_c_SpecificationCS_0);
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class TYP_basecs_c_c_StructuredClassCS_0 = idResolver.getClass(CLSSid_StructuredClassCS, null);
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull SetValue _171_StructuredClassCS_187 = ModelObjectsOfKindOperation.INSTANCE.evaluate(executor, SET_CLSSid_StructuredClassCS, models[0/*leftCS*/], TYP_basecs_c_c_StructuredClassCS_0);
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class TYP_basecs_c_c_TemplateBindingCS_0 = idResolver.getClass(CLSSid_TemplateBindingCS, null);
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull SetValue _171_TemplateBindingCS_187 = ModelObjectsOfKindOperation.INSTANCE.evaluate(executor, SET_CLSSid_TemplateBindingCS, models[0/*leftCS*/], TYP_basecs_c_c_TemplateBindingCS_0);
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class TYP_basecs_c_c_TemplateParameterSubstitutionCS_0 = idResolver.getClass(CLSSid_TemplateParameterSubstitutionCS, null);
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull SetValue _171_TemplateParameterSubstitutionCS_187 = ModelObjectsOfKindOperation.INSTANCE.evaluate(executor, SET_CLSSid_TemplateParameterSubstitutionCS, models[0/*leftCS*/], TYP_basecs_c_c_TemplateParameterSubstitutionCS_0);
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class TYP_basecs_c_c_TemplateSignatureCS_0 = idResolver.getClass(CLSSid_TemplateSignatureCS, null);
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull SetValue _171_TemplateSignatureCS_187 = ModelObjectsOfKindOperation.INSTANCE.evaluate(executor, SET_CLSSid_TemplateSignatureCS, models[0/*leftCS*/], TYP_basecs_c_c_TemplateSignatureCS_0);
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class TYP_basecs_c_c_TuplePartCS_0 = idResolver.getClass(CLSSid_TuplePartCS, null);
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull SetValue _171_TuplePartCS_187 = ModelObjectsOfKindOperation.INSTANCE.evaluate(executor, SET_CLSSid_TuplePartCS, models[0/*leftCS*/], TYP_basecs_c_c_TuplePartCS_0);
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class TYP_basecs_c_c_TupleTypeCS_0 = idResolver.getClass(CLSSid_TupleTypeCS, null);
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull SetValue _171_TupleTypeCS_187 = ModelObjectsOfKindOperation.INSTANCE.evaluate(executor, SET_CLSSid_TupleTypeCS, models[0/*leftCS*/], TYP_basecs_c_c_TupleTypeCS_0);
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class TYP_basecs_c_c_TypeParameterCS_0 = idResolver.getClass(CLSSid_TypeParameterCS, null);
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull SetValue _171_TypeParameterCS_187 = ModelObjectsOfKindOperation.INSTANCE.evaluate(executor, SET_CLSSid_TypeParameterCS, models[0/*leftCS*/], TYP_basecs_c_c_TypeParameterCS_0);
            // connection variables
            final SetValue.@NonNull Accumulator ji_AnnotationCS = (SetValue.Accumulator)ValueUtil.createCollectionAccumulatorValue(SET_CLSSid_AnnotationCS);
            for (org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull AnnotationCS iterator : ValueUtil.typedIterable(AnnotationCS.class, _171_AnnotationCS_187)) {
                ji_AnnotationCS.add(iterator);
            }
            final SetValue.@NonNull Accumulator ji_AttributeCS = (SetValue.Accumulator)ValueUtil.createCollectionAccumulatorValue(SET_CLSSid_AttributeCS);
            for (org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull AttributeCS iterator_0 : ValueUtil.typedIterable(AttributeCS.class, _171_AttributeCS_187)) {
                ji_AttributeCS.add(iterator_0);
            }
            final SetValue.@NonNull Accumulator ji_ConstraintCS = (SetValue.Accumulator)ValueUtil.createCollectionAccumulatorValue(SET_CLSSid_ConstraintCS);
            for (org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull ConstraintCS iterator_1 : ValueUtil.typedIterable(ConstraintCS.class, _171_ConstraintCS_187)) {
                ji_ConstraintCS.add(iterator_1);
            }
            final SetValue.@NonNull Accumulator ji_DataTypeCS = (SetValue.Accumulator)ValueUtil.createCollectionAccumulatorValue(SET_CLSSid_DataTypeCS);
            for (org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull DataTypeCS iterator_2 : ValueUtil.typedIterable(DataTypeCS.class, _171_DataTypeCS_187)) {
                ji_DataTypeCS.add(iterator_2);
            }
            final SetValue.@NonNull Accumulator ji_DetailCS = (SetValue.Accumulator)ValueUtil.createCollectionAccumulatorValue(SET_CLSSid_DetailCS);
            for (org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull DetailCS iterator_3 : ValueUtil.typedIterable(DetailCS.class, _171_DetailCS_187)) {
                ji_DetailCS.add(iterator_3);
            }
            final SetValue.@NonNull Accumulator ji_EnumerationCS = (SetValue.Accumulator)ValueUtil.createCollectionAccumulatorValue(SET_CLSSid_EnumerationCS);
            for (org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull EnumerationCS iterator_4 : ValueUtil.typedIterable(EnumerationCS.class, _171_EnumerationCS_187)) {
                ji_EnumerationCS.add(iterator_4);
            }
            final SetValue.@NonNull Accumulator ji_EnumerationLiteralCS = (SetValue.Accumulator)ValueUtil.createCollectionAccumulatorValue(SET_CLSSid_EnumerationLiteralCS);
            for (org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull EnumerationLiteralCS iterator_5 : ValueUtil.typedIterable(EnumerationLiteralCS.class, _171_EnumerationLiteralCS_187)) {
                ji_EnumerationLiteralCS.add(iterator_5);
            }
            final SetValue.@NonNull Accumulator ji_ImportCS = (SetValue.Accumulator)ValueUtil.createCollectionAccumulatorValue(SET_CLSSid_ImportCS);
            for (org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull ImportCS iterator_6 : ValueUtil.typedIterable(ImportCS.class, _171_ImportCS_187)) {
                ji_ImportCS.add(iterator_6);
            }
            final SetValue.@NonNull Accumulator ji_LambdaTypeCS = (SetValue.Accumulator)ValueUtil.createCollectionAccumulatorValue(SET_CLSSid_LambdaTypeCS);
            for (org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull LambdaTypeCS iterator_7 : ValueUtil.typedIterable(LambdaTypeCS.class, _171_LambdaTypeCS_187)) {
                ji_LambdaTypeCS.add(iterator_7);
            }
            final SetValue.@NonNull Accumulator ji_OperationCS = (SetValue.Accumulator)ValueUtil.createCollectionAccumulatorValue(SET_CLSSid_OperationCS);
            for (org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull OperationCS iterator_8 : ValueUtil.typedIterable(OperationCS.class, _171_OperationCS_187)) {
                ji_OperationCS.add(iterator_8);
            }
            final SetValue.@NonNull Accumulator ji_PackageCS = (SetValue.Accumulator)ValueUtil.createCollectionAccumulatorValue(SET_CLSSid_PackageCS);
            for (org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull PackageCS iterator_9 : ValueUtil.typedIterable(PackageCS.class, _171_PackageCS_187)) {
                ji_PackageCS.add(iterator_9);
            }
            final SetValue.@NonNull Accumulator ji_ParameterCS = (SetValue.Accumulator)ValueUtil.createCollectionAccumulatorValue(SET_CLSSid_ParameterCS);
            for (org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull ParameterCS iterator_10 : ValueUtil.typedIterable(ParameterCS.class, _171_ParameterCS_187)) {
                ji_ParameterCS.add(iterator_10);
            }
            final SetValue.@NonNull Accumulator ji_PrimitiveTypeRefCS = (SetValue.Accumulator)ValueUtil.createCollectionAccumulatorValue(SET_CLSSid_PrimitiveTypeRefCS);
            for (org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull PrimitiveTypeRefCS iterator_11 : ValueUtil.typedIterable(PrimitiveTypeRefCS.class, _171_PrimitiveTypeRefCS_187)) {
                ji_PrimitiveTypeRefCS.add(iterator_11);
            }
            final SetValue.@NonNull Accumulator ji_ReferenceCS = (SetValue.Accumulator)ValueUtil.createCollectionAccumulatorValue(SET_CLSSid_ReferenceCS);
            for (org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull ReferenceCS iterator_12 : ValueUtil.typedIterable(ReferenceCS.class, _171_ReferenceCS_187)) {
                ji_ReferenceCS.add(iterator_12);
            }
            final SetValue.@NonNull Accumulator ji_RootPackageCS = (SetValue.Accumulator)ValueUtil.createCollectionAccumulatorValue(SET_CLSSid_RootPackageCS);
            for (org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull RootPackageCS iterator_13 : ValueUtil.typedIterable(RootPackageCS.class, _171_RootPackageCS_187)) {
                ji_RootPackageCS.add(iterator_13);
            }
            final SetValue.@NonNull Accumulator ji_SpecificationCS = (SetValue.Accumulator)ValueUtil.createCollectionAccumulatorValue(SET_CLSSid_SpecificationCS);
            for (org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull SpecificationCS iterator_14 : ValueUtil.typedIterable(SpecificationCS.class, _171_SpecificationCS_187)) {
                ji_SpecificationCS.add(iterator_14);
            }
            final SetValue.@NonNull Accumulator ji_StructuredClassCS = (SetValue.Accumulator)ValueUtil.createCollectionAccumulatorValue(SET_CLSSid_StructuredClassCS);
            for (org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull StructuredClassCS iterator_15 : ValueUtil.typedIterable(StructuredClassCS.class, _171_StructuredClassCS_187)) {
                ji_StructuredClassCS.add(iterator_15);
            }
            final SetValue.@NonNull Accumulator ji_TemplateBindingCS = (SetValue.Accumulator)ValueUtil.createCollectionAccumulatorValue(SET_CLSSid_TemplateBindingCS);
            for (org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull TemplateBindingCS iterator_16 : ValueUtil.typedIterable(TemplateBindingCS.class, _171_TemplateBindingCS_187)) {
                ji_TemplateBindingCS.add(iterator_16);
            }
            final SetValue.@NonNull Accumulator ji_TemplateParameterSubstitutionCS = (SetValue.Accumulator)ValueUtil.createCollectionAccumulatorValue(SET_CLSSid_TemplateParameterSubstitutionCS);
            for (org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull TemplateParameterSubstitutionCS iterator_17 : ValueUtil.typedIterable(TemplateParameterSubstitutionCS.class, _171_TemplateParameterSubstitutionCS_187)) {
                ji_TemplateParameterSubstitutionCS.add(iterator_17);
            }
            final SetValue.@NonNull Accumulator ji_TemplateParameterSubstitutionCS_1 = (SetValue.Accumulator)ValueUtil.createCollectionAccumulatorValue(SET_CLSSid_TemplateParameterSubstitutionCS);
            /*@Thrown*/ SetValue.@org.eclipse.jdt.annotation.NonNull Accumulator accumulator = ValueUtil.createSetAccumulatorValue(SET_CLSSid_TemplateParameterSubstitutionCS);
            @NonNull Iterator<Object> ITERATOR_i = _171_TemplateParameterSubstitutionCS_187.iterator();
            /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull SetValue select;
            while (true) {
                if (!ITERATOR_i.hasNext()) {
                    select = accumulator;
                    break;
                }
                @SuppressWarnings("null")
                /*@NonInvalid*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull TemplateParameterSubstitutionCS i = (TemplateParameterSubstitutionCS)ITERATOR_i.next();
                /**
                 * i.owningBinding <> null
                 */
                final /*@Thrown*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.Nullable TemplateBindingCS owningBinding = i.getOwningBinding();
                final /*@Thrown*/ boolean symbol_63 = owningBinding != null;
                //
                if (symbol_63 == ValueUtil.TRUE_VALUE) {
                    accumulator.add(i);
                }
            }
            for (org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull TemplateParameterSubstitutionCS iterator_18 : ValueUtil.typedIterable(TemplateParameterSubstitutionCS.class, select)) {
                ji_TemplateParameterSubstitutionCS_1.add(iterator_18);
            }
            final SetValue.@NonNull Accumulator ji_TemplateSignatureCS = (SetValue.Accumulator)ValueUtil.createCollectionAccumulatorValue(SET_CLSSid_TemplateSignatureCS);
            for (org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull TemplateSignatureCS iterator_19 : ValueUtil.typedIterable(TemplateSignatureCS.class, _171_TemplateSignatureCS_187)) {
                ji_TemplateSignatureCS.add(iterator_19);
            }
            final SetValue.@NonNull Accumulator ji_TuplePartCS = (SetValue.Accumulator)ValueUtil.createCollectionAccumulatorValue(SET_CLSSid_TuplePartCS);
            for (org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull TuplePartCS iterator_20 : ValueUtil.typedIterable(TuplePartCS.class, _171_TuplePartCS_187)) {
                ji_TuplePartCS.add(iterator_20);
            }
            final SetValue.@NonNull Accumulator ji_TupleTypeCS = (SetValue.Accumulator)ValueUtil.createCollectionAccumulatorValue(SET_CLSSid_TupleTypeCS);
            for (org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull TupleTypeCS iterator_21 : ValueUtil.typedIterable(TupleTypeCS.class, _171_TupleTypeCS_187)) {
                ji_TupleTypeCS.add(iterator_21);
            }
            final SetValue.@NonNull Accumulator ji_TypeParameterCS = (SetValue.Accumulator)ValueUtil.createCollectionAccumulatorValue(SET_CLSSid_TypeParameterCS);
            for (org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull TypeParameterCS iterator_22 : ValueUtil.typedIterable(TypeParameterCS.class, _171_TypeParameterCS_187)) {
                ji_TypeParameterCS.add(iterator_22);
            }
            // mapping statements
            for (org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull AnnotationCS loop0 : ValueUtil.typedIterable(AnnotationCS.class, ji_AnnotationCS)) {
                MAP_m_AnnotationCS_pivot(loop0);
            }
            for (org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull AttributeCS loop0_0 : ValueUtil.typedIterable(AttributeCS.class, ji_AttributeCS)) {
                MAP_m_AttributeCS_pivot(loop0_0);
            }
            for (org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull ConstraintCS loop0_1 : ValueUtil.typedIterable(ConstraintCS.class, ji_ConstraintCS)) {
                MAP_m_ConstraintCS_pivot(loop0_1);
            }
            for (org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull DataTypeCS loop0_2 : ValueUtil.typedIterable(DataTypeCS.class, ji_DataTypeCS)) {
                MAP_m_DataTypeCS_pivot(loop0_2);
            }
            for (org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull DetailCS loop0_3 : ValueUtil.typedIterable(DetailCS.class, ji_DetailCS)) {
                MAP_m_DetailCS_pivot(loop0_3);
            }
            for (org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull EnumerationCS loop0_4 : ValueUtil.typedIterable(EnumerationCS.class, ji_EnumerationCS)) {
                MAP_m_EnumerationCS_pivot(loop0_4);
            }
            for (org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull EnumerationLiteralCS loop0_5 : ValueUtil.typedIterable(EnumerationLiteralCS.class, ji_EnumerationLiteralCS)) {
                MAP_m_EnumerationLiteralCS_pivot(loop0_5);
            }
            for (org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull ImportCS loop0_6 : ValueUtil.typedIterable(ImportCS.class, ji_ImportCS)) {
                MAP_m_ImportCS_pivot(loop0_6);
            }
            for (org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull LambdaTypeCS loop0_7 : ValueUtil.typedIterable(LambdaTypeCS.class, ji_LambdaTypeCS)) {
                MAP_m_LambdaTypeCS_pivot(loop0_7);
            }
            for (org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull OperationCS loop0_8 : ValueUtil.typedIterable(OperationCS.class, ji_OperationCS)) {
                MAP_m_OperationCS_pivot(loop0_8);
            }
            for (org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull PackageCS loop0_9 : ValueUtil.typedIterable(PackageCS.class, ji_PackageCS)) {
                MAP_m_PackageCS_pivot(loop0_9);
            }
            for (org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull ParameterCS loop0_10 : ValueUtil.typedIterable(ParameterCS.class, ji_ParameterCS)) {
                MAP_m_ParameterCS_pivot(loop0_10);
            }
            for (org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull PrimitiveTypeRefCS loop0_11 : ValueUtil.typedIterable(PrimitiveTypeRefCS.class, ji_PrimitiveTypeRefCS)) {
                MAP_m_PrimitiveTypeRefCS_pivot(loop0_11);
            }
            for (org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull ReferenceCS loop0_12 : ValueUtil.typedIterable(ReferenceCS.class, ji_ReferenceCS)) {
                MAP_m_ReferenceCS_pivot(loop0_12);
            }
            for (org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull RootPackageCS loop0_13 : ValueUtil.typedIterable(RootPackageCS.class, ji_RootPackageCS)) {
                MAP_m_RootPackageCS_pivot(loop0_13);
            }
            for (org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull SpecificationCS loop0_14 : ValueUtil.typedIterable(SpecificationCS.class, ji_SpecificationCS)) {
                MAP_m_SpecificationCS_pivot(loop0_14);
            }
            for (org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull StructuredClassCS loop0_15 : ValueUtil.typedIterable(StructuredClassCS.class, ji_StructuredClassCS)) {
                MAP_m_StructuredClassCS_pivot(loop0_15);
            }
            for (org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull TemplateBindingCS loop0_16 : ValueUtil.typedIterable(TemplateBindingCS.class, ji_TemplateBindingCS)) {
                MAP_m_TemplateBindingCS_pivot(loop0_16);
            }
            for (org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull TemplateParameterSubstitutionCS loop0_17 : ValueUtil.typedIterable(TemplateParameterSubstitutionCS.class, ji_TemplateParameterSubstitutionCS)) {
                MAP_m_TemplateParameterSubstitutionCS_pivot(loop0_17);
            }
            for (org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull TemplateSignatureCS loop0_18 : ValueUtil.typedIterable(TemplateSignatureCS.class, ji_TemplateSignatureCS)) {
                MAP_m_TemplateSignatureCS_pivot(loop0_18);
            }
            for (org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull TuplePartCS loop0_19 : ValueUtil.typedIterable(TuplePartCS.class, ji_TuplePartCS)) {
                MAP_m_TuplePartCS_pivot(loop0_19);
            }
            for (org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull TupleTypeCS loop0_20 : ValueUtil.typedIterable(TupleTypeCS.class, ji_TupleTypeCS)) {
                MAP_m_TupleTypeCS_pivot(loop0_20);
            }
            for (org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull TypeParameterCS loop0_21 : ValueUtil.typedIterable(TypeParameterCS.class, ji_TypeParameterCS)) {
                MAP_m_TypeParameterCS_pivot(loop0_21);
            }
            for (org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull AnnotationCS loop0_22 : ValueUtil.typedIterable(AnnotationCS.class, ji_AnnotationCS)) {
                MAP_m_AnnotationCS_name_ownedAnnotations_ownedContents(loop0_22);
            }
            for (org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull AttributeCS loop0_23 : ValueUtil.typedIterable(AttributeCS.class, ji_AttributeCS)) {
                MAP_m_AttributeCS_default_name_ownedAnnotations_pivot_(loop0_23);
            }
            for (org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull AttributeCS loop0_24 : ValueUtil.typedIterable(AttributeCS.class, ji_AttributeCS)) {
                MAP_m_AttributeCS_ownedDefaultExpressions_pivot(loop0_24);
            }
            for (org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull AttributeCS loop0_25 : ValueUtil.typedIterable(AttributeCS.class, ji_AttributeCS)) {
                MAP_m_AttributeCS_ownedType_pivot(loop0_25);
            }
            for (org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull ConstraintCS loop0_26 : ValueUtil.typedIterable(ConstraintCS.class, ji_ConstraintCS)) {
                MAP_m_ConstraintCS_ownedSpecification_pivot(loop0_26);
            }
            for (org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull DataTypeCS loop0_27 : ValueUtil.typedIterable(DataTypeCS.class, ji_DataTypeCS)) {
                MAP_m_DataTypeCS_instanceClassName_isSerializable_name(loop0_27);
            }
            for (org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull DataTypeCS loop0_28 : ValueUtil.typedIterable(DataTypeCS.class, ji_DataTypeCS)) {
                MAP_m_DataTypeCS_ownedSignature_pivot(loop0_28);
            }
            for (org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull DetailCS loop0_29 : ValueUtil.typedIterable(DetailCS.class, ji_DetailCS)) {
                MAP_m_DetailCS_name_pivot_values(loop0_29);
            }
            for (org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull EnumerationCS loop0_30 : ValueUtil.typedIterable(EnumerationCS.class, ji_EnumerationCS)) {
                MAP_m_EnumerationCS_instanceClassName_isSerializable_n(loop0_30);
            }
            for (org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull EnumerationCS loop0_31 : ValueUtil.typedIterable(EnumerationCS.class, ji_EnumerationCS)) {
                MAP_m_EnumerationCS_ownedSignature_pivot(loop0_31);
            }
            for (org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull EnumerationLiteralCS loop0_32 : ValueUtil.typedIterable(EnumerationLiteralCS.class, ji_EnumerationLiteralCS)) {
                MAP_m_EnumerationLiteralCS_name_ownedAnnotations_pivot(loop0_32);
            }
            for (org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull ImportCS loop0_33 : ValueUtil.typedIterable(ImportCS.class, ji_ImportCS)) {
                MAP_m_ImportCS_name_pivot(loop0_33);
            }
            for (org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull LambdaTypeCS loop0_34 : ValueUtil.typedIterable(LambdaTypeCS.class, ji_LambdaTypeCS)) {
                MAP_m_LambdaTypeCS_ownedContextType_pivot(loop0_34);
            }
            for (org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull LambdaTypeCS loop0_35 : ValueUtil.typedIterable(LambdaTypeCS.class, ji_LambdaTypeCS)) {
                MAP_m_LambdaTypeCS_name_ownedParameterTypes_pivot(loop0_35);
            }
            for (org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull LambdaTypeCS loop0_36 : ValueUtil.typedIterable(LambdaTypeCS.class, ji_LambdaTypeCS)) {
                MAP_m_LambdaTypeCS_ownedResultType_pivot(loop0_36);
            }
            for (org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull OperationCS loop0_37 : ValueUtil.typedIterable(OperationCS.class, ji_OperationCS)) {
                MAP_m_OperationCS_ownedBodyExpressions_pivot(loop0_37);
            }
            for (org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull OperationCS loop0_38 : ValueUtil.typedIterable(OperationCS.class, ji_OperationCS)) {
                MAP_m_OperationCS_name_ownedAnnotations_ownedException(loop0_38);
            }
            for (org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull OperationCS loop0_39 : ValueUtil.typedIterable(OperationCS.class, ji_OperationCS)) {
                MAP_m_OperationCS_ownedSignature_pivot(loop0_39);
            }
            for (org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull OperationCS loop0_40 : ValueUtil.typedIterable(OperationCS.class, ji_OperationCS)) {
                MAP_m_OperationCS_ownedType_pivot(loop0_40);
            }
            for (org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull PackageCS loop0_41 : ValueUtil.typedIterable(PackageCS.class, ji_PackageCS)) {
                MAP_m_PackageCS_name_nsPrefix_nsURI_ownedAnnotations_o(loop0_41);
            }
            for (org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull ParameterCS loop0_42 : ValueUtil.typedIterable(ParameterCS.class, ji_ParameterCS)) {
                MAP_m_ParameterCS_name_ownedAnnotations_pivot(loop0_42);
            }
            for (org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull ParameterCS loop0_43 : ValueUtil.typedIterable(ParameterCS.class, ji_ParameterCS)) {
                MAP_m_ParameterCS_ownedType_pivot(loop0_43);
            }
            for (org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull PrimitiveTypeRefCS loop0_44 : ValueUtil.typedIterable(PrimitiveTypeRefCS.class, ji_PrimitiveTypeRefCS)) {
                MAP_m_PrimitiveTypeRefCS_name_pivot(loop0_44);
            }
            for (org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull ReferenceCS loop0_45 : ValueUtil.typedIterable(ReferenceCS.class, ji_ReferenceCS)) {
                MAP_m_ReferenceCS_default_name_ownedAnnotations_pivot_(loop0_45);
            }
            for (org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull ReferenceCS loop0_46 : ValueUtil.typedIterable(ReferenceCS.class, ji_ReferenceCS)) {
                MAP_m_ReferenceCS_ownedDefaultExpressions_ownedDefault(loop0_46);
            }
            for (org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull ReferenceCS loop0_47 : ValueUtil.typedIterable(ReferenceCS.class, ji_ReferenceCS)) {
                MAP_m_ReferenceCS_ownedType_pivot(loop0_47);
            }
            for (org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull RootPackageCS loop0_48 : ValueUtil.typedIterable(RootPackageCS.class, ji_RootPackageCS)) {
                MAP_m_RootPackageCS_ownedImports_ownedPackages_pivot(loop0_48);
            }
            for (org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull SpecificationCS loop0_49 : ValueUtil.typedIterable(SpecificationCS.class, ji_SpecificationCS)) {
                MAP_m_SpecificationCS_exprString_pivot(loop0_49);
            }
            for (org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull StructuredClassCS loop0_50 : ValueUtil.typedIterable(StructuredClassCS.class, ji_StructuredClassCS)) {
                MAP_m_StructuredClassCS_instanceClassName_isAbstract_i(loop0_50);
            }
            for (org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull ReferenceCS loop0_51 : ValueUtil.typedIterable(ReferenceCS.class, ji_ReferenceCS)) {
                MAP_m_ReferenceCS_ownedType_pivot_referredOpposite(loop0_51);
            }
            for (org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull StructuredClassCS loop0_52 : ValueUtil.typedIterable(StructuredClassCS.class, ji_StructuredClassCS)) {
                MAP_m_StructuredClassCS_ownedSignature_pivot(loop0_52);
            }
            for (org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull TemplateBindingCS loop0_53 : ValueUtil.typedIterable(TemplateBindingCS.class, ji_TemplateBindingCS)) {
                MAP_m_TemplateBindingCS_ownedSubstitutions_pivot(loop0_53);
            }
            for (org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull TemplateParameterSubstitutionCS loop0_54 : ValueUtil.typedIterable(TemplateParameterSubstitutionCS.class, ji_TemplateParameterSubstitutionCS)) {
                MAP_m_TemplateParameterSubstitutionCS_ownedActualParam(loop0_54);
            }
            for (org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull TemplateParameterSubstitutionCS loop0_55 : ValueUtil.typedIterable(TemplateParameterSubstitutionCS.class, ji_TemplateParameterSubstitutionCS_1)) {
                MAP_m_TemplateParameterSubstitutionCS_owningBinding_pi(loop0_55);
            }
            for (org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull TuplePartCS loop0_56 : ValueUtil.typedIterable(TuplePartCS.class, ji_TuplePartCS)) {
                MAP_m_TuplePartCS_name_pivot(loop0_56);
            }
            for (org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull TuplePartCS loop0_57 : ValueUtil.typedIterable(TuplePartCS.class, ji_TuplePartCS)) {
                MAP_m_TuplePartCS_ownedType_pivot(loop0_57);
            }
            for (org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull TupleTypeCS loop0_58 : ValueUtil.typedIterable(TupleTypeCS.class, ji_TupleTypeCS)) {
                MAP_m_TupleTypeCS_name_ownedParts_pivot(loop0_58);
            }
            for (org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull ImportCS loop0_59 : ValueUtil.typedIterable(ImportCS.class, ji_ImportCS)) {
                MAP_m_ImportCS_ownedPathName_pivot(loop0_59);
            }
            for (org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull TypeParameterCS loop0_60 : ValueUtil.typedIterable(TypeParameterCS.class, ji_TypeParameterCS)) {
                MAP_m_TypeParameterCS_name_ownedExtends_pivot(loop0_60);
            }
            boolean __root__ = ValueUtil.TRUE_VALUE;
            return __root__;
        } catch (Throwable e) {
            return handleExecutionFailure("MAP___root__", e);
        }
    }
    
    /**
     * 
     * map m_AnnotationCS_name_ownedAnnotations_ownedContents in BaseCS2AS_qvtp_qvtcas {
     *   leftCS (lAnnotationCS : basecs::AnnotationCS[?];
     *  |)
     * { |}
     * rightAS ( |)
     * { |}
     * where ( |)
     * {aAnnotation : Annotation[1] = lAnnotationCS.pivot.oclAsType(Annotation)
     *   ;
     * name : String[?] = lAnnotationCS.name;
     * ownedAnnotations : OrderedSet(basecs::AnnotationElementCS) = lAnnotationCS.ownedAnnotations;
     * ownedContents : OrderedSet(basecs::ModelElementCS) = lAnnotationCS.ownedContents;
     * ownedDetails : OrderedSet(basecs::DetailCS) = lAnnotationCS.ownedDetails;
     * ownedReferences : OrderedSet(basecs::ModelElementRefCS) = lAnnotationCS.ownedReferences;
     * pivot : Element[?] = lAnnotationCS.pivot;
     * _'\u00ABcollect\u00BB' : Sequence(Element) = ownedContents->collect(_'1_' | _'1_'.pivot)
     *   ;
     * _'\u00ABcollect\u00BB1' : Sequence(Detail) = ownedDetails->collect(_'1_' |
     *     _'1_'.pivot.oclAsType(Detail))
     *   ;
     * _'\u00ABcollect\u00BB2' : Sequence(Element) = ownedAnnotations->collect(_'1_' | _'1_'.pivot)
     *   ;
     * _'\u00ABcollect\u00BB3' : Sequence(Element) = ownedReferences->collect(_'1_' | _'1_'.pivot);
     *  |
     * aAnnotation.name := name;
     * aAnnotation.ownedAnnotations := _'\u00ABcollect\u00BB2';
     * aAnnotation.ownedContents := _'\u00ABcollect\u00BB';
     * aAnnotation.ownedDetails := _'\u00ABcollect\u00BB1';
     * aAnnotation.references := _'\u00ABcollect\u00BB3';
     * }
     * 
     */
    protected boolean MAP_m_AnnotationCS_name_ownedAnnotations_ownedContents(final /*@NonInvalid*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull AnnotationCS lAnnotationCS) throws ReflectiveOperationException {
        try {
            // predicates and unrealized variables
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull IdResolver idResolver = executor.getIdResolver();
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class TYP_Annotation_0 = idResolver.getClass(CLSSid_Annotation, null);
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot = lAnnotationCS.getPivot();
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Annotation aAnnotation = ClassUtil.nonNullState((Annotation)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, pivot, TYP_Annotation_0));
            final /*@Thrown*/ java.lang.@org.eclipse.jdt.annotation.Nullable String name = lAnnotationCS.getName();
            @SuppressWarnings("null")
            final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<AnnotationElementCS> ownedAnnotations = lAnnotationCS.getOwnedAnnotations();
            @SuppressWarnings("null")
            final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<ModelElementCS> ownedContents = lAnnotationCS.getOwnedContents();
            @SuppressWarnings("null")
            final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<DetailCS> ownedDetails = lAnnotationCS.getOwnedDetails();
            @SuppressWarnings("null")
            final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<ModelElementRefCS> ownedReferences = lAnnotationCS.getOwnedReferences();
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot_0 = lAnnotationCS.getPivot();
            final /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull OrderedSetValue BOXED_ownedContents = idResolver.createOrderedSetOfAll(ORD_CLSSid_ModelElementCS, ownedContents);
            /*@Thrown*/ SequenceValue.@org.eclipse.jdt.annotation.NonNull Accumulator accumulator = ValueUtil.createSequenceAccumulatorValue(SEQ_CLSSid_Element);
            @Nullable Iterator<Object> ITERATOR__1 = BOXED_ownedContents.iterator();
            /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull SequenceValue _171_collect_187;
            while (true) {
                if (!ITERATOR__1.hasNext()) {
                    _171_collect_187 = accumulator;
                    break;
                }
                /*@NonInvalid*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.Nullable ModelElementCS _1 = (ModelElementCS)ITERATOR__1.next();
                /**
                 * _'1_'.pivot
                 */
                if (_1 == null) {
                    throw throwNull(lAnnotationCS, "Null source for \'\'http://www.eclipse.org/ocl/2015/BaseCS\'::PivotableElementCS::pivot\'");
                }
                final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot_1 = _1.getPivot();
                //
                accumulator.add(pivot_1);
            }
            final /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull OrderedSetValue BOXED_ownedDetails = idResolver.createOrderedSetOfAll(ORD_CLSSid_DetailCS, ownedDetails);
            /*@Thrown*/ SequenceValue.@org.eclipse.jdt.annotation.NonNull Accumulator accumulator_0 = ValueUtil.createSequenceAccumulatorValue(SEQ_CLSSid_Detail);
            @Nullable Iterator<Object> ITERATOR__1_0 = BOXED_ownedDetails.iterator();
            /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull SequenceValue _171_collect_187_1;
            while (true) {
                if (!ITERATOR__1_0.hasNext()) {
                    _171_collect_187_1 = accumulator_0;
                    break;
                }
                /*@NonInvalid*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.Nullable DetailCS _1_0 = (DetailCS)ITERATOR__1_0.next();
                /**
                 * _'1_'.pivot.oclAsType(Detail)
                 */
                final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class TYP_Detail_0 = idResolver.getClass(CLSSid_Detail, null);
                if (_1_0 == null) {
                    throw throwNull(lAnnotationCS, "Null source for \'\'http://www.eclipse.org/ocl/2015/BaseCS\'::PivotableElementCS::pivot\'");
                }
                final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot_2 = _1_0.getPivot();
                final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Detail oclAsType = ClassUtil.nonNullState((Detail)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, pivot_2, TYP_Detail_0));
                //
                accumulator_0.add(oclAsType);
            }
            final /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull OrderedSetValue BOXED_ownedAnnotations = idResolver.createOrderedSetOfAll(ORD_CLSSid_AnnotationElementCS, ownedAnnotations);
            /*@Thrown*/ SequenceValue.@org.eclipse.jdt.annotation.NonNull Accumulator accumulator_1 = ValueUtil.createSequenceAccumulatorValue(SEQ_CLSSid_Element);
            @Nullable Iterator<Object> ITERATOR__1_1 = BOXED_ownedAnnotations.iterator();
            /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull SequenceValue _171_collect_187_2;
            while (true) {
                if (!ITERATOR__1_1.hasNext()) {
                    _171_collect_187_2 = accumulator_1;
                    break;
                }
                /*@NonInvalid*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.Nullable AnnotationElementCS _1_1 = (AnnotationElementCS)ITERATOR__1_1.next();
                /**
                 * _'1_'.pivot
                 */
                if (_1_1 == null) {
                    throw throwNull(lAnnotationCS, "Null source for \'\'http://www.eclipse.org/ocl/2015/BaseCS\'::PivotableElementCS::pivot\'");
                }
                final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot_3 = _1_1.getPivot();
                //
                accumulator_1.add(pivot_3);
            }
            final /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull OrderedSetValue BOXED_ownedReferences = idResolver.createOrderedSetOfAll(ORD_CLSSid_ModelElementRefCS, ownedReferences);
            /*@Thrown*/ SequenceValue.@org.eclipse.jdt.annotation.NonNull Accumulator accumulator_2 = ValueUtil.createSequenceAccumulatorValue(SEQ_CLSSid_Element);
            @Nullable Iterator<Object> ITERATOR__1_2 = BOXED_ownedReferences.iterator();
            /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull SequenceValue _171_collect_187_3;
            while (true) {
                if (!ITERATOR__1_2.hasNext()) {
                    _171_collect_187_3 = accumulator_2;
                    break;
                }
                /*@NonInvalid*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.Nullable ModelElementRefCS _1_2 = (ModelElementRefCS)ITERATOR__1_2.next();
                /**
                 * _'1_'.pivot
                 */
                if (_1_2 == null) {
                    throw throwNull(lAnnotationCS, "Null source for \'\'http://www.eclipse.org/ocl/2015/BaseCS\'::PivotableElementCS::pivot\'");
                }
                final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot_4 = _1_2.getPivot();
                //
                accumulator_2.add(pivot_4);
            }
            // property assignments
            aAnnotation.setName(name);
            final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<Element> ECORE__171_collect_187_2 = ((IdResolver.IdResolverExtension)idResolver).ecoreValueOfAll(Element.class, _171_collect_187_2);
            aAnnotation.getOwnedAnnotations().addAll(ECORE__171_collect_187_2);
            final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<Element> ECORE__171_collect_187 = ((IdResolver.IdResolverExtension)idResolver).ecoreValueOfAll(Element.class, _171_collect_187);
            aAnnotation.getOwnedContents().addAll(ECORE__171_collect_187);
            final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<Detail> ECORE__171_collect_187_1 = ((IdResolver.IdResolverExtension)idResolver).ecoreValueOfAll(Detail.class, _171_collect_187_1);
            aAnnotation.getOwnedDetails().addAll(ECORE__171_collect_187_1);
            final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<Element> ECORE__171_collect_187_3 = ((IdResolver.IdResolverExtension)idResolver).ecoreValueOfAll(Element.class, _171_collect_187_3);
            aAnnotation.getReferences().addAll(ECORE__171_collect_187_3);
            boolean m_AnnotationCS_name_ownedAnnotations_ownedContents = ValueUtil.TRUE_VALUE;
            return m_AnnotationCS_name_ownedAnnotations_ownedContents;
        } catch (Throwable e) {
            return handleExecutionFailure("MAP_m_AnnotationCS_name_ownedAnnotations_ownedContents", e);
        }
    }
    
    /**
     * 
     * map m_AnnotationCS_pivot in BaseCS2AS_qvtp_qvtcas {leftCS (lAnnotationCS : basecs::AnnotationCS[?];
     *  |)
     * { |}
     * rightAS ( |)
     * {realize rAnnotation : Annotation[1];
     *  |}
     * where ( |)
     * { |
     * lAnnotationCS.pivot := rAnnotation;
     * }
     * 
     */
    protected boolean MAP_m_AnnotationCS_pivot(final /*@NonInvalid*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull AnnotationCS lAnnotationCS_0) throws ReflectiveOperationException {
        try {
            // predicates and unrealized variables
            // creations
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Annotation rAnnotation = PivotFactory.eINSTANCE.createAnnotation();
            assert rAnnotation != null;
            models[1/*rightAS*/].add(rAnnotation);
            // property assignments
            lAnnotationCS_0.setPivot(rAnnotation);
            boolean m_AnnotationCS_pivot = ValueUtil.TRUE_VALUE;
            return m_AnnotationCS_pivot;
        } catch (Throwable e) {
            return handleExecutionFailure("MAP_m_AnnotationCS_pivot", e);
        }
    }
    
    /**
     * 
     * map m_AttributeCS_default_name_ownedAnnotations_pivot_ in BaseCS2AS_qvtp_qvtcas {
     *   leftCS (lAttributeCS : basecs::AttributeCS[?];
     *  |)
     * { |}
     * rightAS ( |)
     * { |}
     * where ( |)
     * {aProperty : Property[1] = lAttributeCS.pivot.oclAsType(Property)
     *   ;
     * default : String[?] = lAttributeCS.default;
     * name : String[?] = lAttributeCS.name;
     * ownedAnnotations : OrderedSet(basecs::AnnotationElementCS) = lAttributeCS.ownedAnnotations;
     * pivot : Element[?] = lAttributeCS.pivot;
     * qualifiers : OrderedSet(String) = lAttributeCS.qualifiers;
     * includes : Boolean[1] = qualifiers->includes('unsettable')
     *   ;
     * includes1 : Boolean[1] = qualifiers->includes('readonly')
     *   ;
     * includes2 : Boolean[1] = qualifiers->includes('static')
     *   ;
     * includes3 : Boolean[1] = qualifiers->includes('id')
     *   ;
     * includes4 : Boolean[1] = qualifiers->includes('transient')
     *   ;
     * includes5 : Boolean[1] = qualifiers->includes('volatile')
     *   ;
     * includes6 : Boolean[1] = qualifiers->includes('derived')
     *   ;
     * _'\u00ABcollect\u00BB' : Sequence(Element) = ownedAnnotations->collect(_'1_' | _'1_'.pivot);
     *  |
     * aProperty.defaultValueString := default;
     * aProperty.isDerived := includes6;
     * aProperty.isID := includes3;
     * aProperty.isReadOnly := includes1;
     * aProperty.isStatic := includes2;
     * aProperty.isTransient := includes4;
     * aProperty.isUnsettable := includes;
     * aProperty.isVolatile := includes5;
     * aProperty.name := name;
     * aProperty.ownedAnnotations := _'\u00ABcollect\u00BB';
     * }
     * 
     */
    protected boolean MAP_m_AttributeCS_default_name_ownedAnnotations_pivot_(final /*@NonInvalid*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull AttributeCS lAttributeCS) throws ReflectiveOperationException {
        try {
            // predicates and unrealized variables
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull IdResolver idResolver = executor.getIdResolver();
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class TYP_Property_0 = idResolver.getClass(CLSSid_Property, null);
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot = lAttributeCS.getPivot();
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Property aProperty = ClassUtil.nonNullState((Property)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, pivot, TYP_Property_0));
            final /*@Thrown*/ java.lang.@org.eclipse.jdt.annotation.Nullable String symbol_0 = lAttributeCS.getDefault();
            final /*@Thrown*/ java.lang.@org.eclipse.jdt.annotation.Nullable String name = lAttributeCS.getName();
            @SuppressWarnings("null")
            final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<AnnotationElementCS> ownedAnnotations = lAttributeCS.getOwnedAnnotations();
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot_0 = lAttributeCS.getPivot();
            @SuppressWarnings("null")
            final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<String> qualifiers = lAttributeCS.getQualifiers();
            final /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull OrderedSetValue BOXED_qualifiers = idResolver.createOrderedSetOfAll(ORD_PRIMid_String, qualifiers);
            final /*@Thrown*/ boolean includes = CollectionIncludesOperation.INSTANCE.evaluate(BOXED_qualifiers, STR_unsettable).booleanValue();
            final /*@Thrown*/ boolean includes1 = CollectionIncludesOperation.INSTANCE.evaluate(BOXED_qualifiers, STR_readonly).booleanValue();
            final /*@Thrown*/ boolean includes2 = CollectionIncludesOperation.INSTANCE.evaluate(BOXED_qualifiers, STR_static).booleanValue();
            final /*@Thrown*/ boolean includes3 = CollectionIncludesOperation.INSTANCE.evaluate(BOXED_qualifiers, STR_id).booleanValue();
            final /*@Thrown*/ boolean includes4 = CollectionIncludesOperation.INSTANCE.evaluate(BOXED_qualifiers, STR_transient).booleanValue();
            final /*@Thrown*/ boolean includes5 = CollectionIncludesOperation.INSTANCE.evaluate(BOXED_qualifiers, STR_volatile).booleanValue();
            final /*@Thrown*/ boolean includes6 = CollectionIncludesOperation.INSTANCE.evaluate(BOXED_qualifiers, STR_derived).booleanValue();
            final /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull OrderedSetValue BOXED_ownedAnnotations = idResolver.createOrderedSetOfAll(ORD_CLSSid_AnnotationElementCS, ownedAnnotations);
            /*@Thrown*/ SequenceValue.@org.eclipse.jdt.annotation.NonNull Accumulator accumulator = ValueUtil.createSequenceAccumulatorValue(SEQ_CLSSid_Element);
            @Nullable Iterator<Object> ITERATOR__1 = BOXED_ownedAnnotations.iterator();
            /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull SequenceValue _171_collect_187;
            while (true) {
                if (!ITERATOR__1.hasNext()) {
                    _171_collect_187 = accumulator;
                    break;
                }
                /*@NonInvalid*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.Nullable AnnotationElementCS _1 = (AnnotationElementCS)ITERATOR__1.next();
                /**
                 * _'1_'.pivot
                 */
                if (_1 == null) {
                    throw throwNull(lAttributeCS, "Null source for \'\'http://www.eclipse.org/ocl/2015/BaseCS\'::PivotableElementCS::pivot\'");
                }
                final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot_1 = _1.getPivot();
                //
                accumulator.add(pivot_1);
            }
            // property assignments
            aProperty.setDefaultValueString(symbol_0);
            aProperty.setIsDerived(includes6);
            aProperty.setIsID(includes3);
            aProperty.setIsReadOnly(includes1);
            aProperty.setIsStatic(includes2);
            aProperty.setIsTransient(includes4);
            aProperty.setIsUnsettable(includes);
            aProperty.setIsVolatile(includes5);
            aProperty.setName(name);
            final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<Element> ECORE__171_collect_187 = ((IdResolver.IdResolverExtension)idResolver).ecoreValueOfAll(Element.class, _171_collect_187);
            aProperty.getOwnedAnnotations().addAll(ECORE__171_collect_187);
            boolean m_AttributeCS_default_name_ownedAnnotations_pivot_ = ValueUtil.TRUE_VALUE;
            return m_AttributeCS_default_name_ownedAnnotations_pivot_;
        } catch (Throwable e) {
            return handleExecutionFailure("MAP_m_AttributeCS_default_name_ownedAnnotations_pivot_", e);
        }
    }
    
    /**
     * 
     * map m_AttributeCS_ownedDefaultExpressions_pivot in BaseCS2AS_qvtp_qvtcas {
     *   leftCS (lAttributeCS : basecs::AttributeCS[?];
     *  |)
     * { |}
     * rightAS ( |)
     * { |}
     * where ( |)
     * {aProperty : Property[1] = lAttributeCS.pivot.oclAsType(Property)
     *   ;
     * ownedDefaultExpressions : OrderedSet(basecs::SpecificationCS) = lAttributeCS.ownedDefaultExpressions;
     * pivot : Element[?] = lAttributeCS.pivot;
     * ownedExpression : LanguageExpression[1] = ownedDefaultExpressions->collect(_'1_' |
     *     _'1_'.pivot.oclAsType(LanguageExpression))
     *   ->oclAsType(LanguageExpression);
     *  |
     * aProperty.ownedExpression := ownedExpression;
     * }
     * 
     */
    protected boolean MAP_m_AttributeCS_ownedDefaultExpressions_pivot(final /*@NonInvalid*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull AttributeCS lAttributeCS_0) throws ReflectiveOperationException {
        try {
            // predicates and unrealized variables
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull IdResolver idResolver = executor.getIdResolver();
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class TYP_Property_0 = idResolver.getClass(CLSSid_Property, null);
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot = lAttributeCS_0.getPivot();
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Property aProperty = ClassUtil.nonNullState((Property)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, pivot, TYP_Property_0));
            @SuppressWarnings("null")
            final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<SpecificationCS> ownedDefaultExpressions = lAttributeCS_0.getOwnedDefaultExpressions();
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot_0 = lAttributeCS_0.getPivot();
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class TYP_LanguageExpression_1 = idResolver.getClass(CLSSid_LanguageExpression, null);
            final /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull OrderedSetValue BOXED_ownedDefaultExpressions = idResolver.createOrderedSetOfAll(ORD_CLSSid_SpecificationCS, ownedDefaultExpressions);
            /*@Thrown*/ SequenceValue.@org.eclipse.jdt.annotation.NonNull Accumulator accumulator = ValueUtil.createSequenceAccumulatorValue(SEQ_CLSSid_LanguageExpression);
            @Nullable Iterator<Object> ITERATOR__1 = BOXED_ownedDefaultExpressions.iterator();
            /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull SequenceValue collect;
            while (true) {
                if (!ITERATOR__1.hasNext()) {
                    collect = accumulator;
                    break;
                }
                /*@NonInvalid*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.Nullable SpecificationCS _1 = (SpecificationCS)ITERATOR__1.next();
                /**
                 * _'1_'.pivot.oclAsType(LanguageExpression)
                 */
                if (_1 == null) {
                    throw throwNull(lAttributeCS_0, "Null source for \'\'http://www.eclipse.org/ocl/2015/BaseCS\'::PivotableElementCS::pivot\'");
                }
                final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot_1 = _1.getPivot();
                final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull LanguageExpression oclAsType = ClassUtil.nonNullState((LanguageExpression)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, pivot_1, TYP_LanguageExpression_1));
                //
                accumulator.add(oclAsType);
            }
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull LanguageExpression ownedExpression = ClassUtil.nonNullState((LanguageExpression)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, collect, TYP_LanguageExpression_1));
            // property assignments
            aProperty.setOwnedExpression(ownedExpression);
            boolean m_AttributeCS_ownedDefaultExpressions_pivot = ValueUtil.TRUE_VALUE;
            return m_AttributeCS_ownedDefaultExpressions_pivot;
        } catch (Throwable e) {
            return handleExecutionFailure("MAP_m_AttributeCS_ownedDefaultExpressions_pivot", e);
        }
    }
    
    /**
     * 
     * map m_AttributeCS_ownedType_pivot in BaseCS2AS_qvtp_qvtcas {
     *   leftCS (lAttributeCS : basecs::AttributeCS[?];
     *  |)
     * { |}
     * rightAS ( |)
     * { |}
     * where ( |)
     * {aProperty : Property[1] = lAttributeCS.pivot.oclAsType(Property)
     *   ;
     * ownedType : basecs::TypedRefCS[?] = lAttributeCS.ownedType;
     * pivot : Element[?] = lAttributeCS.pivot;
     * aClass : Class[1] = ownedType.pivot.oclAsType(Class);
     * pivot1 : Element[?] = ownedType.pivot;
     *  |
     * aProperty.type := aClass;
     * }
     * 
     */
    protected boolean MAP_m_AttributeCS_ownedType_pivot(final /*@NonInvalid*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull AttributeCS lAttributeCS_1) throws ReflectiveOperationException {
        try {
            // predicates and unrealized variables
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull IdResolver idResolver = executor.getIdResolver();
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class TYP_Property_0 = idResolver.getClass(CLSSid_Property, null);
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot = lAttributeCS_1.getPivot();
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Property aProperty = ClassUtil.nonNullState((Property)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, pivot, TYP_Property_0));
            final /*@Thrown*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.Nullable TypedRefCS ownedType = lAttributeCS_1.getOwnedType();
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot_0 = lAttributeCS_1.getPivot();
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class TYP_Class_0 = idResolver.getClass(CLSSid_Class, null);
            if (ownedType == null) {
                throw throwNull(lAttributeCS_1, "Null source for \'\'http://www.eclipse.org/ocl/2015/BaseCS\'::PivotableElementCS::pivot\'");
            }
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot_1 = ownedType.getPivot();
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class aClass = ClassUtil.nonNullState((Class)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, pivot_1, TYP_Class_0));
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot1 = ownedType.getPivot();
            // property assignments
            aProperty.setType(aClass);
            boolean m_AttributeCS_ownedType_pivot = ValueUtil.TRUE_VALUE;
            return m_AttributeCS_ownedType_pivot;
        } catch (Throwable e) {
            return handleExecutionFailure("MAP_m_AttributeCS_ownedType_pivot", e);
        }
    }
    
    /**
     * 
     * map m_AttributeCS_pivot in BaseCS2AS_qvtp_qvtcas {leftCS (lAttributeCS : basecs::AttributeCS[?];
     *  |)
     * { |}
     * rightAS ( |)
     * {realize rProperty : Property[1];
     *  |}
     * where ( |)
     * { |
     * lAttributeCS.pivot := rProperty;
     * }
     * 
     */
    protected boolean MAP_m_AttributeCS_pivot(final /*@NonInvalid*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull AttributeCS lAttributeCS_2) throws ReflectiveOperationException {
        try {
            // predicates and unrealized variables
            // creations
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Property rProperty = PivotFactory.eINSTANCE.createProperty();
            assert rProperty != null;
            models[1/*rightAS*/].add(rProperty);
            // property assignments
            lAttributeCS_2.setPivot(rProperty);
            boolean m_AttributeCS_pivot = ValueUtil.TRUE_VALUE;
            return m_AttributeCS_pivot;
        } catch (Throwable e) {
            return handleExecutionFailure("MAP_m_AttributeCS_pivot", e);
        }
    }
    
    /**
     * 
     * map m_ConstraintCS_ownedSpecification_pivot in BaseCS2AS_qvtp_qvtcas {
     *   leftCS (lConstraintCS : basecs::ConstraintCS[?];
     *  |)
     * { |}
     * rightAS ( |)
     * { |}
     * where ( |)
     * {aConstraint : Constraint[1] = lConstraintCS.pivot.oclAsType(Constraint)
     *   ;
     * ownedSpecification : basecs::SpecificationCS[?] = lConstraintCS.ownedSpecification;
     * pivot : Element[?] = lConstraintCS.pivot;
     * aLanguageExpression : LanguageExpression[1] = ownedSpecification.pivot.oclAsType(LanguageExpression);
     * pivot1 : Element[?] = ownedSpecification.pivot;
     *  |
     * aLanguageExpression.owningConstraint := aConstraint;
     * }
     * 
     */
    protected boolean MAP_m_ConstraintCS_ownedSpecification_pivot(final /*@NonInvalid*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull ConstraintCS lConstraintCS) throws ReflectiveOperationException {
        try {
            // predicates and unrealized variables
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull IdResolver idResolver = executor.getIdResolver();
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class TYP_Constraint_0 = idResolver.getClass(CLSSid_Constraint, null);
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot = lConstraintCS.getPivot();
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Constraint aConstraint = ClassUtil.nonNullState((Constraint)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, pivot, TYP_Constraint_0));
            final /*@Thrown*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.Nullable SpecificationCS ownedSpecification = lConstraintCS.getOwnedSpecification();
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot_0 = lConstraintCS.getPivot();
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class TYP_LanguageExpression_0 = idResolver.getClass(CLSSid_LanguageExpression, null);
            if (ownedSpecification == null) {
                throw throwNull(lConstraintCS, "Null source for \'\'http://www.eclipse.org/ocl/2015/BaseCS\'::PivotableElementCS::pivot\'");
            }
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot_1 = ownedSpecification.getPivot();
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull LanguageExpression aLanguageExpression = ClassUtil.nonNullState((LanguageExpression)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, pivot_1, TYP_LanguageExpression_0));
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot1 = ownedSpecification.getPivot();
            // property assignments
            aLanguageExpression.setOwningConstraint(aConstraint);
            boolean m_ConstraintCS_ownedSpecification_pivot = ValueUtil.TRUE_VALUE;
            return m_ConstraintCS_ownedSpecification_pivot;
        } catch (Throwable e) {
            return handleExecutionFailure("MAP_m_ConstraintCS_ownedSpecification_pivot", e);
        }
    }
    
    /**
     * 
     * map m_ConstraintCS_pivot in BaseCS2AS_qvtp_qvtcas {leftCS (lConstraintCS : basecs::ConstraintCS[?];
     *  |)
     * { |}
     * rightAS ( |)
     * {realize rConstraint : Constraint[1];
     *  |}
     * where ( |)
     * { |
     * lConstraintCS.pivot := rConstraint;
     * }
     * 
     */
    protected boolean MAP_m_ConstraintCS_pivot(final /*@NonInvalid*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull ConstraintCS lConstraintCS_0) throws ReflectiveOperationException {
        try {
            // predicates and unrealized variables
            // creations
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Constraint rConstraint = PivotFactory.eINSTANCE.createConstraint();
            assert rConstraint != null;
            models[1/*rightAS*/].add(rConstraint);
            // property assignments
            lConstraintCS_0.setPivot(rConstraint);
            boolean m_ConstraintCS_pivot = ValueUtil.TRUE_VALUE;
            return m_ConstraintCS_pivot;
        } catch (Throwable e) {
            return handleExecutionFailure("MAP_m_ConstraintCS_pivot", e);
        }
    }
    
    /**
     * 
     * map m_DataTypeCS_instanceClassName_isSerializable_name in BaseCS2AS_qvtp_qvtcas {
     *   leftCS (lDataTypeCS : basecs::DataTypeCS[?];
     *  |)
     * { |}
     * rightAS ( |)
     * { |}
     * where ( |)
     * {OclElement : Class[1] = OclElement;
     * aDataType : DataType[1] = lDataTypeCS.pivot.oclAsType(DataType)
     *   ;
     * instanceClassName : String[?] = lDataTypeCS.instanceClassName;
     * isSerializable : Boolean[?] = lDataTypeCS.isSerializable;
     * name : String[?] = lDataTypeCS.name;
     * ownedAnnotations : OrderedSet(basecs::AnnotationElementCS) = lDataTypeCS.ownedAnnotations;
     * ownedConstraints : OrderedSet(basecs::ConstraintCS) = lDataTypeCS.ownedConstraints;
     * pivot : Element[?] = lDataTypeCS.pivot;
     * Set : Set(Class[*|1]) = Set{OclElement
     *   }
     *   ;
     * _'\u00ABcollect\u00BB' : Sequence(Element) = ownedAnnotations->collect(_'1_' | _'1_'.pivot)
     *   ;
     * _'\u00ABcollect\u00BB1' : Sequence(Constraint) = ownedConstraints->collect(_'1_' |
     *     _'1_'.pivot.oclAsType(Constraint));
     *  |
     * aDataType.instanceClassName := instanceClassName;
     * aDataType.isSerializable := isSerializable;
     * aDataType.name := name;
     * aDataType.ownedAnnotations := _'\u00ABcollect\u00BB';
     * aDataType.ownedInvariants := _'\u00ABcollect\u00BB1';
     * aDataType.superClasses := Set;
     * }
     * 
     */
    protected boolean MAP_m_DataTypeCS_instanceClassName_isSerializable_name(final /*@NonInvalid*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull DataTypeCS lDataTypeCS) throws ReflectiveOperationException {
        try {
            // predicates and unrealized variables
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull IdResolver idResolver = executor.getIdResolver();
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class TYP_OclElement_0 = idResolver.getClass(CLSSid_OclElement, null);
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class TYP_DataType_0 = idResolver.getClass(CLSSid_DataType, null);
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot = lDataTypeCS.getPivot();
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull DataType aDataType = ClassUtil.nonNullState((DataType)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, pivot, TYP_DataType_0));
            final /*@Thrown*/ java.lang.@org.eclipse.jdt.annotation.Nullable String instanceClassName = lDataTypeCS.getInstanceClassName();
            final /*@Thrown*/ java.lang.@org.eclipse.jdt.annotation.Nullable Boolean isSerializable = lDataTypeCS.isIsSerializable();
            final /*@Thrown*/ java.lang.@org.eclipse.jdt.annotation.Nullable String name = lDataTypeCS.getName();
            @SuppressWarnings("null")
            final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<AnnotationElementCS> ownedAnnotations = lDataTypeCS.getOwnedAnnotations();
            @SuppressWarnings("null")
            final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<ConstraintCS> ownedConstraints = lDataTypeCS.getOwnedConstraints();
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot_0 = lDataTypeCS.getPivot();
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull SetValue Set = ValueUtil.createSetOfEach(SET_CLSSid_Class, TYP_OclElement_0);
            final /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull OrderedSetValue BOXED_ownedAnnotations = idResolver.createOrderedSetOfAll(ORD_CLSSid_AnnotationElementCS, ownedAnnotations);
            /*@Thrown*/ SequenceValue.@org.eclipse.jdt.annotation.NonNull Accumulator accumulator = ValueUtil.createSequenceAccumulatorValue(SEQ_CLSSid_Element);
            @Nullable Iterator<Object> ITERATOR__1 = BOXED_ownedAnnotations.iterator();
            /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull SequenceValue _171_collect_187;
            while (true) {
                if (!ITERATOR__1.hasNext()) {
                    _171_collect_187 = accumulator;
                    break;
                }
                /*@NonInvalid*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.Nullable AnnotationElementCS _1 = (AnnotationElementCS)ITERATOR__1.next();
                /**
                 * _'1_'.pivot
                 */
                if (_1 == null) {
                    throw throwNull(lDataTypeCS, "Null source for \'\'http://www.eclipse.org/ocl/2015/BaseCS\'::PivotableElementCS::pivot\'");
                }
                final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot_1 = _1.getPivot();
                //
                accumulator.add(pivot_1);
            }
            final /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull OrderedSetValue BOXED_ownedConstraints = idResolver.createOrderedSetOfAll(ORD_CLSSid_ConstraintCS, ownedConstraints);
            /*@Thrown*/ SequenceValue.@org.eclipse.jdt.annotation.NonNull Accumulator accumulator_0 = ValueUtil.createSequenceAccumulatorValue(SEQ_CLSSid_Constraint);
            @Nullable Iterator<Object> ITERATOR__1_0 = BOXED_ownedConstraints.iterator();
            /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull SequenceValue _171_collect_187_1;
            while (true) {
                if (!ITERATOR__1_0.hasNext()) {
                    _171_collect_187_1 = accumulator_0;
                    break;
                }
                /*@NonInvalid*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.Nullable ConstraintCS _1_0 = (ConstraintCS)ITERATOR__1_0.next();
                /**
                 * _'1_'.pivot.oclAsType(Constraint)
                 */
                final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class TYP_Constraint_0 = idResolver.getClass(CLSSid_Constraint, null);
                if (_1_0 == null) {
                    throw throwNull(lDataTypeCS, "Null source for \'\'http://www.eclipse.org/ocl/2015/BaseCS\'::PivotableElementCS::pivot\'");
                }
                final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot_2 = _1_0.getPivot();
                final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Constraint oclAsType = ClassUtil.nonNullState((Constraint)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, pivot_2, TYP_Constraint_0));
                //
                accumulator_0.add(oclAsType);
            }
            // property assignments
            aDataType.setInstanceClassName(instanceClassName);
            if (isSerializable == null) {
                throw throwNull(lDataTypeCS, "Null value");
            }
            aDataType.setIsSerializable(isSerializable);
            aDataType.setName(name);
            final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<Element> ECORE__171_collect_187 = ((IdResolver.IdResolverExtension)idResolver).ecoreValueOfAll(Element.class, _171_collect_187);
            aDataType.getOwnedAnnotations().addAll(ECORE__171_collect_187);
            final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<Constraint> ECORE__171_collect_187_1 = ((IdResolver.IdResolverExtension)idResolver).ecoreValueOfAll(Constraint.class, _171_collect_187_1);
            aDataType.getOwnedInvariants().addAll(ECORE__171_collect_187_1);
            final /*@NonInvalid*/ java.util.@org.eclipse.jdt.annotation.NonNull List<Class> ECORE_Set = ((IdResolver.IdResolverExtension)idResolver).ecoreValueOfAll(Class.class, Set);
            aDataType.getSuperClasses().addAll(ECORE_Set);
            boolean m_DataTypeCS_instanceClassName_isSerializable_name = ValueUtil.TRUE_VALUE;
            return m_DataTypeCS_instanceClassName_isSerializable_name;
        } catch (Throwable e) {
            return handleExecutionFailure("MAP_m_DataTypeCS_instanceClassName_isSerializable_name", e);
        }
    }
    
    /**
     * 
     * map m_DataTypeCS_ownedSignature_pivot in BaseCS2AS_qvtp_qvtcas {
     *   leftCS (lDataTypeCS : basecs::DataTypeCS[?];
     *  |)
     * { |}
     * rightAS ( |)
     * { |}
     * where ( |)
     * {aDataType : DataType[1] = lDataTypeCS.pivot.oclAsType(DataType)
     *   ;
     * ownedSignature : basecs::TemplateSignatureCS[?] = lDataTypeCS.ownedSignature;
     * pivot : Element[?] = lDataTypeCS.pivot;
     * aTemplateSignature : TemplateSignature[1] = ownedSignature.pivot.oclAsType(TemplateSignature)
     *   ;
     * ownedParameters : OrderedSet(basecs::TemplateParameterCS) = ownedSignature.ownedParameters;
     * pivot1 : Element[?] = ownedSignature.pivot;
     * _'\u00ABcollect\u00BB' : Sequence(TemplateParameter) = ownedParameters->collect(_'1_' |
     *     _'1_'.pivot.oclAsType(TemplateParameter));
     *  |
     * aTemplateSignature.ownedParameters := _'\u00ABcollect\u00BB';
     * aDataType.ownedSignature := aTemplateSignature;
     * }
     * 
     */
    protected boolean MAP_m_DataTypeCS_ownedSignature_pivot(final /*@NonInvalid*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull DataTypeCS lDataTypeCS_0) throws ReflectiveOperationException {
        try {
            // predicates and unrealized variables
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull IdResolver idResolver = executor.getIdResolver();
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class TYP_DataType_0 = idResolver.getClass(CLSSid_DataType, null);
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot = lDataTypeCS_0.getPivot();
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull DataType aDataType = ClassUtil.nonNullState((DataType)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, pivot, TYP_DataType_0));
            final /*@Thrown*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.Nullable TemplateSignatureCS ownedSignature = lDataTypeCS_0.getOwnedSignature();
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot_0 = lDataTypeCS_0.getPivot();
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class TYP_TemplateSignature_0 = idResolver.getClass(CLSSid_TemplateSignature, null);
            if (ownedSignature == null) {
                throw throwNull(lDataTypeCS_0, "Null source for \'\'http://www.eclipse.org/ocl/2015/BaseCS\'::TemplateSignatureCS::ownedParameters\'");
            }
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot_1 = ownedSignature.getPivot();
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull TemplateSignature aTemplateSignature = ClassUtil.nonNullState((TemplateSignature)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, pivot_1, TYP_TemplateSignature_0));
            @SuppressWarnings("null")
            final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<TemplateParameterCS> ownedParameters = ownedSignature.getOwnedParameters();
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot1 = ownedSignature.getPivot();
            final /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull OrderedSetValue BOXED_ownedParameters = idResolver.createOrderedSetOfAll(ORD_CLSSid_TemplateParameterCS, ownedParameters);
            /*@Thrown*/ SequenceValue.@org.eclipse.jdt.annotation.NonNull Accumulator accumulator = ValueUtil.createSequenceAccumulatorValue(SEQ_CLSSid_TemplateParameter);
            @Nullable Iterator<Object> ITERATOR__1 = BOXED_ownedParameters.iterator();
            /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull SequenceValue _171_collect_187;
            while (true) {
                if (!ITERATOR__1.hasNext()) {
                    _171_collect_187 = accumulator;
                    break;
                }
                /*@NonInvalid*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.Nullable TemplateParameterCS _1 = (TemplateParameterCS)ITERATOR__1.next();
                /**
                 * _'1_'.pivot.oclAsType(TemplateParameter)
                 */
                final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class TYP_TemplateParameter_0 = idResolver.getClass(CLSSid_TemplateParameter, null);
                if (_1 == null) {
                    throw throwNull(lDataTypeCS_0, "Null source for \'\'http://www.eclipse.org/ocl/2015/BaseCS\'::PivotableElementCS::pivot\'");
                }
                final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot_2 = _1.getPivot();
                final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull TemplateParameter oclAsType = ClassUtil.nonNullState((TemplateParameter)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, pivot_2, TYP_TemplateParameter_0));
                //
                accumulator.add(oclAsType);
            }
            // property assignments
            final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<TemplateParameter> ECORE__171_collect_187 = ((IdResolver.IdResolverExtension)idResolver).ecoreValueOfAll(TemplateParameter.class, _171_collect_187);
            aTemplateSignature.getOwnedParameters().addAll(ECORE__171_collect_187);
            aDataType.setOwnedSignature(aTemplateSignature);
            objectManager.assigned(aDataType, PivotPackage.Literals.TEMPLATEABLE_ELEMENT__OWNED_SIGNATURE, aTemplateSignature, null);
            boolean m_DataTypeCS_ownedSignature_pivot = ValueUtil.TRUE_VALUE;
            return m_DataTypeCS_ownedSignature_pivot;
        } catch (Throwable e) {
            return handleExecutionFailure("MAP_m_DataTypeCS_ownedSignature_pivot", e);
        }
    }
    
    /**
     * 
     * map m_DataTypeCS_pivot in BaseCS2AS_qvtp_qvtcas {leftCS (lDataTypeCS : basecs::DataTypeCS[?];
     *  |)
     * { |}
     * rightAS ( |)
     * {realize rDataType : DataType[1];
     *  |}
     * where ( |)
     * { |
     * lDataTypeCS.pivot := rDataType;
     * }
     * 
     */
    protected boolean MAP_m_DataTypeCS_pivot(final /*@NonInvalid*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull DataTypeCS lDataTypeCS_1) throws ReflectiveOperationException {
        try {
            // predicates and unrealized variables
            // creations
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable DataType rDataType = PivotFactory.eINSTANCE.createDataType();
            assert rDataType != null;
            models[1/*rightAS*/].add(rDataType);
            // property assignments
            lDataTypeCS_1.setPivot(rDataType);
            boolean m_DataTypeCS_pivot = ValueUtil.TRUE_VALUE;
            return m_DataTypeCS_pivot;
        } catch (Throwable e) {
            return handleExecutionFailure("MAP_m_DataTypeCS_pivot", e);
        }
    }
    
    /**
     * 
     * map m_DetailCS_name_pivot_values in BaseCS2AS_qvtp_qvtcas {
     *   leftCS (lDetailCS : basecs::DetailCS[?];
     *  |)
     * { |}
     * rightAS ( |)
     * { |}
     * where ( |)
     * {aDetail : Detail[1] = lDetailCS.pivot.oclAsType(Detail);
     * name : String[?] = lDetailCS.name;
     * pivot : Element[?] = lDetailCS.pivot;
     * values : OrderedSet(String) = lDetailCS.values;
     *  |
     * aDetail.name := name;
     * aDetail.values := values;
     * }
     * 
     */
    protected boolean MAP_m_DetailCS_name_pivot_values(final /*@NonInvalid*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull DetailCS lDetailCS) throws ReflectiveOperationException {
        try {
            // predicates and unrealized variables
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull IdResolver idResolver = executor.getIdResolver();
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class TYP_Detail_0 = idResolver.getClass(CLSSid_Detail, null);
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot = lDetailCS.getPivot();
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Detail aDetail = ClassUtil.nonNullState((Detail)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, pivot, TYP_Detail_0));
            final /*@Thrown*/ java.lang.@org.eclipse.jdt.annotation.Nullable String name = lDetailCS.getName();
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot_0 = lDetailCS.getPivot();
            @SuppressWarnings("null")
            final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<String> values = lDetailCS.getValues();
            // property assignments
            aDetail.setName(name);
            aDetail.getValues().addAll(values);
            boolean m_DetailCS_name_pivot_values = ValueUtil.TRUE_VALUE;
            return m_DetailCS_name_pivot_values;
        } catch (Throwable e) {
            return handleExecutionFailure("MAP_m_DetailCS_name_pivot_values", e);
        }
    }
    
    /**
     * 
     * map m_DetailCS_pivot in BaseCS2AS_qvtp_qvtcas {leftCS (lDetailCS : basecs::DetailCS[?];
     *  |)
     * { |}
     * rightAS ( |)
     * {realize rDetail : Detail[1];
     *  |}
     * where ( |)
     * { |
     * lDetailCS.pivot := rDetail;
     * }
     * 
     */
    protected boolean MAP_m_DetailCS_pivot(final /*@NonInvalid*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull DetailCS lDetailCS_0) throws ReflectiveOperationException {
        try {
            // predicates and unrealized variables
            // creations
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Detail rDetail = PivotFactory.eINSTANCE.createDetail();
            assert rDetail != null;
            models[1/*rightAS*/].add(rDetail);
            // property assignments
            lDetailCS_0.setPivot(rDetail);
            boolean m_DetailCS_pivot = ValueUtil.TRUE_VALUE;
            return m_DetailCS_pivot;
        } catch (Throwable e) {
            return handleExecutionFailure("MAP_m_DetailCS_pivot", e);
        }
    }
    
    /**
     * 
     * map m_EnumerationCS_instanceClassName_isSerializable_n in BaseCS2AS_qvtp_qvtcas {
     *   leftCS (lEnumerationCS : basecs::EnumerationCS[?];
     *  |)
     * { |}
     * rightAS ( |)
     * { |}
     * where ( |)
     * {OclElement : Class[1] = OclElement;
     * aEnumeration : Enumeration[1] = lEnumerationCS.pivot.oclAsType(Enumeration)
     *   ;
     * instanceClassName : String[?] = lEnumerationCS.instanceClassName;
     * isSerializable : Boolean[?] = lEnumerationCS.isSerializable;
     * name : String[?] = lEnumerationCS.name;
     * ownedAnnotations : OrderedSet(basecs::AnnotationElementCS) = lEnumerationCS.ownedAnnotations;
     * ownedConstraints : OrderedSet(basecs::ConstraintCS) = lEnumerationCS.ownedConstraints;
     * ownedLiterals : OrderedSet(basecs::EnumerationLiteralCS) = lEnumerationCS.ownedLiterals;
     * pivot : Element[?] = lEnumerationCS.pivot;
     * Set : Set(Class[*|1]) = Set{OclElement
     *   }
     *   ;
     * _'\u00ABcollect\u00BB' : Sequence(Constraint) = ownedConstraints->collect(_'1_' |
     *     _'1_'.pivot.oclAsType(Constraint))
     *   ;
     * _'\u00ABcollect\u00BB1' : Sequence(Element) = ownedAnnotations->collect(_'1_' | _'1_'.pivot)
     *   ;
     * _'\u00ABcollect\u00BB2' : Sequence(EnumerationLiteral) = ownedLiterals->collect(_'1_' |
     *     _'1_'.pivot.oclAsType(EnumerationLiteral));
     *  |
     * aEnumeration.instanceClassName := instanceClassName;
     * aEnumeration.isSerializable := isSerializable;
     * aEnumeration.name := name;
     * aEnumeration.ownedAnnotations := _'\u00ABcollect\u00BB1';
     * aEnumeration.ownedInvariants := _'\u00ABcollect\u00BB';
     * aEnumeration.ownedLiterals := _'\u00ABcollect\u00BB2';
     * aEnumeration.superClasses := Set;
     * }
     * 
     */
    protected boolean MAP_m_EnumerationCS_instanceClassName_isSerializable_n(final /*@NonInvalid*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull EnumerationCS lEnumerationCS) throws ReflectiveOperationException {
        try {
            // predicates and unrealized variables
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull IdResolver idResolver = executor.getIdResolver();
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class TYP_OclElement_0 = idResolver.getClass(CLSSid_OclElement, null);
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class TYP_Enumeration_0 = idResolver.getClass(CLSSid_Enumeration, null);
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot = lEnumerationCS.getPivot();
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Enumeration aEnumeration = ClassUtil.nonNullState((Enumeration)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, pivot, TYP_Enumeration_0));
            final /*@Thrown*/ java.lang.@org.eclipse.jdt.annotation.Nullable String instanceClassName = lEnumerationCS.getInstanceClassName();
            final /*@Thrown*/ java.lang.@org.eclipse.jdt.annotation.Nullable Boolean isSerializable = lEnumerationCS.isIsSerializable();
            final /*@Thrown*/ java.lang.@org.eclipse.jdt.annotation.Nullable String name = lEnumerationCS.getName();
            @SuppressWarnings("null")
            final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<AnnotationElementCS> ownedAnnotations = lEnumerationCS.getOwnedAnnotations();
            @SuppressWarnings("null")
            final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<ConstraintCS> ownedConstraints = lEnumerationCS.getOwnedConstraints();
            @SuppressWarnings("null")
            final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<EnumerationLiteralCS> ownedLiterals = lEnumerationCS.getOwnedLiterals();
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot_0 = lEnumerationCS.getPivot();
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull SetValue Set = ValueUtil.createSetOfEach(SET_CLSSid_Class, TYP_OclElement_0);
            final /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull OrderedSetValue BOXED_ownedConstraints = idResolver.createOrderedSetOfAll(ORD_CLSSid_ConstraintCS, ownedConstraints);
            /*@Thrown*/ SequenceValue.@org.eclipse.jdt.annotation.NonNull Accumulator accumulator = ValueUtil.createSequenceAccumulatorValue(SEQ_CLSSid_Constraint);
            @Nullable Iterator<Object> ITERATOR__1 = BOXED_ownedConstraints.iterator();
            /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull SequenceValue _171_collect_187;
            while (true) {
                if (!ITERATOR__1.hasNext()) {
                    _171_collect_187 = accumulator;
                    break;
                }
                /*@NonInvalid*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.Nullable ConstraintCS _1 = (ConstraintCS)ITERATOR__1.next();
                /**
                 * _'1_'.pivot.oclAsType(Constraint)
                 */
                final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class TYP_Constraint_0 = idResolver.getClass(CLSSid_Constraint, null);
                if (_1 == null) {
                    throw throwNull(lEnumerationCS, "Null source for \'\'http://www.eclipse.org/ocl/2015/BaseCS\'::PivotableElementCS::pivot\'");
                }
                final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot_1 = _1.getPivot();
                final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Constraint oclAsType = ClassUtil.nonNullState((Constraint)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, pivot_1, TYP_Constraint_0));
                //
                accumulator.add(oclAsType);
            }
            final /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull OrderedSetValue BOXED_ownedAnnotations = idResolver.createOrderedSetOfAll(ORD_CLSSid_AnnotationElementCS, ownedAnnotations);
            /*@Thrown*/ SequenceValue.@org.eclipse.jdt.annotation.NonNull Accumulator accumulator_0 = ValueUtil.createSequenceAccumulatorValue(SEQ_CLSSid_Element);
            @Nullable Iterator<Object> ITERATOR__1_0 = BOXED_ownedAnnotations.iterator();
            /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull SequenceValue _171_collect_187_1;
            while (true) {
                if (!ITERATOR__1_0.hasNext()) {
                    _171_collect_187_1 = accumulator_0;
                    break;
                }
                /*@NonInvalid*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.Nullable AnnotationElementCS _1_0 = (AnnotationElementCS)ITERATOR__1_0.next();
                /**
                 * _'1_'.pivot
                 */
                if (_1_0 == null) {
                    throw throwNull(lEnumerationCS, "Null source for \'\'http://www.eclipse.org/ocl/2015/BaseCS\'::PivotableElementCS::pivot\'");
                }
                final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot_2 = _1_0.getPivot();
                //
                accumulator_0.add(pivot_2);
            }
            final /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull OrderedSetValue BOXED_ownedLiterals = idResolver.createOrderedSetOfAll(ORD_CLSSid_EnumerationLiteralCS, ownedLiterals);
            /*@Thrown*/ SequenceValue.@org.eclipse.jdt.annotation.NonNull Accumulator accumulator_1 = ValueUtil.createSequenceAccumulatorValue(SEQ_CLSSid_EnumerationLiteral);
            @Nullable Iterator<Object> ITERATOR__1_1 = BOXED_ownedLiterals.iterator();
            /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull SequenceValue _171_collect_187_2;
            while (true) {
                if (!ITERATOR__1_1.hasNext()) {
                    _171_collect_187_2 = accumulator_1;
                    break;
                }
                /*@NonInvalid*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.Nullable EnumerationLiteralCS _1_1 = (EnumerationLiteralCS)ITERATOR__1_1.next();
                /**
                 * _'1_'.pivot.oclAsType(EnumerationLiteral)
                 */
                final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class TYP_EnumerationLiteral_0 = idResolver.getClass(CLSSid_EnumerationLiteral, null);
                if (_1_1 == null) {
                    throw throwNull(lEnumerationCS, "Null source for \'\'http://www.eclipse.org/ocl/2015/BaseCS\'::PivotableElementCS::pivot\'");
                }
                final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot_3 = _1_1.getPivot();
                final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull EnumerationLiteral oclAsType_0 = ClassUtil.nonNullState((EnumerationLiteral)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, pivot_3, TYP_EnumerationLiteral_0));
                //
                accumulator_1.add(oclAsType_0);
            }
            // property assignments
            aEnumeration.setInstanceClassName(instanceClassName);
            if (isSerializable == null) {
                throw throwNull(lEnumerationCS, "Null value");
            }
            aEnumeration.setIsSerializable(isSerializable);
            aEnumeration.setName(name);
            final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<Element> ECORE__171_collect_187_1 = ((IdResolver.IdResolverExtension)idResolver).ecoreValueOfAll(Element.class, _171_collect_187_1);
            aEnumeration.getOwnedAnnotations().addAll(ECORE__171_collect_187_1);
            final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<Constraint> ECORE__171_collect_187 = ((IdResolver.IdResolverExtension)idResolver).ecoreValueOfAll(Constraint.class, _171_collect_187);
            aEnumeration.getOwnedInvariants().addAll(ECORE__171_collect_187);
            final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<EnumerationLiteral> ECORE__171_collect_187_2 = ((IdResolver.IdResolverExtension)idResolver).ecoreValueOfAll(EnumerationLiteral.class, _171_collect_187_2);
            aEnumeration.getOwnedLiterals().addAll(ECORE__171_collect_187_2);
            final /*@NonInvalid*/ java.util.@org.eclipse.jdt.annotation.NonNull List<Class> ECORE_Set = ((IdResolver.IdResolverExtension)idResolver).ecoreValueOfAll(Class.class, Set);
            aEnumeration.getSuperClasses().addAll(ECORE_Set);
            boolean m_EnumerationCS_instanceClassName_isSerializable_n = ValueUtil.TRUE_VALUE;
            return m_EnumerationCS_instanceClassName_isSerializable_n;
        } catch (Throwable e) {
            return handleExecutionFailure("MAP_m_EnumerationCS_instanceClassName_isSerializable_n", e);
        }
    }
    
    /**
     * 
     * map m_EnumerationCS_ownedSignature_pivot in BaseCS2AS_qvtp_qvtcas {
     *   leftCS (lEnumerationCS : basecs::EnumerationCS[?];
     *  |)
     * { |}
     * rightAS ( |)
     * { |}
     * where ( |)
     * {aEnumeration : Enumeration[1] = lEnumerationCS.pivot.oclAsType(Enumeration)
     *   ;
     * ownedSignature : basecs::TemplateSignatureCS[?] = lEnumerationCS.ownedSignature;
     * pivot : Element[?] = lEnumerationCS.pivot;
     * aTemplateSignature : TemplateSignature[1] = ownedSignature.pivot.oclAsType(TemplateSignature);
     * pivot1 : Element[?] = ownedSignature.pivot;
     *  |
     * aTemplateSignature.owningElement := aEnumeration;
     * }
     * 
     */
    protected boolean MAP_m_EnumerationCS_ownedSignature_pivot(final /*@NonInvalid*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull EnumerationCS lEnumerationCS_0) throws ReflectiveOperationException {
        try {
            // predicates and unrealized variables
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull IdResolver idResolver = executor.getIdResolver();
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class TYP_Enumeration_0 = idResolver.getClass(CLSSid_Enumeration, null);
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot = lEnumerationCS_0.getPivot();
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Enumeration aEnumeration = ClassUtil.nonNullState((Enumeration)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, pivot, TYP_Enumeration_0));
            final /*@Thrown*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.Nullable TemplateSignatureCS ownedSignature = lEnumerationCS_0.getOwnedSignature();
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot_0 = lEnumerationCS_0.getPivot();
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class TYP_TemplateSignature_0 = idResolver.getClass(CLSSid_TemplateSignature, null);
            if (ownedSignature == null) {
                throw throwNull(lEnumerationCS_0, "Null source for \'\'http://www.eclipse.org/ocl/2015/BaseCS\'::PivotableElementCS::pivot\'");
            }
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot_1 = ownedSignature.getPivot();
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull TemplateSignature aTemplateSignature = ClassUtil.nonNullState((TemplateSignature)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, pivot_1, TYP_TemplateSignature_0));
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot1 = ownedSignature.getPivot();
            // property assignments
            aTemplateSignature.setOwningElement(aEnumeration);
            objectManager.assigned(aTemplateSignature, PivotPackage.Literals.TEMPLATE_SIGNATURE__OWNING_ELEMENT, aEnumeration, null);
            boolean m_EnumerationCS_ownedSignature_pivot = ValueUtil.TRUE_VALUE;
            return m_EnumerationCS_ownedSignature_pivot;
        } catch (Throwable e) {
            return handleExecutionFailure("MAP_m_EnumerationCS_ownedSignature_pivot", e);
        }
    }
    
    /**
     * 
     * map m_EnumerationCS_pivot in BaseCS2AS_qvtp_qvtcas {leftCS (lEnumerationCS : basecs::EnumerationCS[?];
     *  |)
     * { |}
     * rightAS ( |)
     * {realize rEnumeration : Enumeration[1];
     *  |}
     * where ( |)
     * { |
     * lEnumerationCS.pivot := rEnumeration;
     * }
     * 
     */
    protected boolean MAP_m_EnumerationCS_pivot(final /*@NonInvalid*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull EnumerationCS lEnumerationCS_1) throws ReflectiveOperationException {
        try {
            // predicates and unrealized variables
            // creations
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Enumeration rEnumeration = PivotFactory.eINSTANCE.createEnumeration();
            assert rEnumeration != null;
            models[1/*rightAS*/].add(rEnumeration);
            // property assignments
            lEnumerationCS_1.setPivot(rEnumeration);
            boolean m_EnumerationCS_pivot = ValueUtil.TRUE_VALUE;
            return m_EnumerationCS_pivot;
        } catch (Throwable e) {
            return handleExecutionFailure("MAP_m_EnumerationCS_pivot", e);
        }
    }
    
    /**
     * 
     * map m_EnumerationLiteralCS_name_ownedAnnotations_pivot in BaseCS2AS_qvtp_qvtcas {
     *   leftCS (lEnumerationLiteralCS : basecs::EnumerationLiteralCS[?];
     *  |)
     * { |}
     * rightAS ( |)
     * { |}
     * where ( |)
     * {aEnumerationLiteral : EnumerationLiteral[1] = lEnumerationLiteralCS.pivot.oclAsType(EnumerationLiteral)
     *   ;
     * name : String[?] = lEnumerationLiteralCS.name;
     * ownedAnnotations : OrderedSet(basecs::AnnotationElementCS) = lEnumerationLiteralCS.ownedAnnotations;
     * pivot : Element[?] = lEnumerationLiteralCS.pivot;
     * value : ecore::EInt[?] = lEnumerationLiteralCS.value;
     * _'\u00ABcollect\u00BB' : Sequence(Element) = ownedAnnotations->collect(_'1_' | _'1_'.pivot);
     *  |
     * aEnumerationLiteral.name := name;
     * aEnumerationLiteral.ownedAnnotations := _'\u00ABcollect\u00BB';
     * aEnumerationLiteral.value := value;
     * }
     * 
     */
    protected boolean MAP_m_EnumerationLiteralCS_name_ownedAnnotations_pivot(final /*@NonInvalid*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull EnumerationLiteralCS lEnumerationLiteralCS) throws ReflectiveOperationException {
        try {
            // predicates and unrealized variables
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull IdResolver idResolver = executor.getIdResolver();
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class TYP_EnumerationLiteral_0 = idResolver.getClass(CLSSid_EnumerationLiteral, null);
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot = lEnumerationLiteralCS.getPivot();
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull EnumerationLiteral aEnumerationLiteral = ClassUtil.nonNullState((EnumerationLiteral)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, pivot, TYP_EnumerationLiteral_0));
            final /*@Thrown*/ java.lang.@org.eclipse.jdt.annotation.Nullable String name = lEnumerationLiteralCS.getName();
            @SuppressWarnings("null")
            final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<AnnotationElementCS> ownedAnnotations = lEnumerationLiteralCS.getOwnedAnnotations();
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot_0 = lEnumerationLiteralCS.getPivot();
            final /*@Thrown*/ int value = lEnumerationLiteralCS.getValue();
            final /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull OrderedSetValue BOXED_ownedAnnotations = idResolver.createOrderedSetOfAll(ORD_CLSSid_AnnotationElementCS, ownedAnnotations);
            /*@Thrown*/ SequenceValue.@org.eclipse.jdt.annotation.NonNull Accumulator accumulator = ValueUtil.createSequenceAccumulatorValue(SEQ_CLSSid_Element);
            @Nullable Iterator<Object> ITERATOR__1 = BOXED_ownedAnnotations.iterator();
            /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull SequenceValue _171_collect_187;
            while (true) {
                if (!ITERATOR__1.hasNext()) {
                    _171_collect_187 = accumulator;
                    break;
                }
                /*@NonInvalid*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.Nullable AnnotationElementCS _1 = (AnnotationElementCS)ITERATOR__1.next();
                /**
                 * _'1_'.pivot
                 */
                if (_1 == null) {
                    throw throwNull(lEnumerationLiteralCS, "Null source for \'\'http://www.eclipse.org/ocl/2015/BaseCS\'::PivotableElementCS::pivot\'");
                }
                final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot_1 = _1.getPivot();
                //
                accumulator.add(pivot_1);
            }
            // property assignments
            aEnumerationLiteral.setName(name);
            final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<Element> ECORE__171_collect_187 = ((IdResolver.IdResolverExtension)idResolver).ecoreValueOfAll(Element.class, _171_collect_187);
            aEnumerationLiteral.getOwnedAnnotations().addAll(ECORE__171_collect_187);
            aEnumerationLiteral.setValue(value);
            boolean m_EnumerationLiteralCS_name_ownedAnnotations_pivot = ValueUtil.TRUE_VALUE;
            return m_EnumerationLiteralCS_name_ownedAnnotations_pivot;
        } catch (Throwable e) {
            return handleExecutionFailure("MAP_m_EnumerationLiteralCS_name_ownedAnnotations_pivot", e);
        }
    }
    
    /**
     * 
     * map m_EnumerationLiteralCS_pivot in BaseCS2AS_qvtp_qvtcas {leftCS (lEnumerationLiteralCS : basecs::EnumerationLiteralCS[?];
     *  |)
     * { |}
     * rightAS ( |)
     * {realize rEnumerationLiteral : EnumerationLiteral[1];
     *  |}
     * where ( |)
     * { |
     * lEnumerationLiteralCS.pivot := rEnumerationLiteral;
     * }
     * 
     */
    protected boolean MAP_m_EnumerationLiteralCS_pivot(final /*@NonInvalid*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull EnumerationLiteralCS lEnumerationLiteralCS_0) throws ReflectiveOperationException {
        try {
            // predicates and unrealized variables
            // creations
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable EnumerationLiteral rEnumerationLiteral = PivotFactory.eINSTANCE.createEnumerationLiteral();
            assert rEnumerationLiteral != null;
            models[1/*rightAS*/].add(rEnumerationLiteral);
            // property assignments
            lEnumerationLiteralCS_0.setPivot(rEnumerationLiteral);
            boolean m_EnumerationLiteralCS_pivot = ValueUtil.TRUE_VALUE;
            return m_EnumerationLiteralCS_pivot;
        } catch (Throwable e) {
            return handleExecutionFailure("MAP_m_EnumerationLiteralCS_pivot", e);
        }
    }
    
    /**
     * 
     * map m_ImportCS_name_pivot in BaseCS2AS_qvtp_qvtcas {
     *   leftCS (lImportCS : basecs::ImportCS[?];
     *  |)
     * { |}
     * rightAS ( |)
     * { |}
     * where ( |)
     * {aImport : Import[1] = lImportCS.pivot.oclAsType(Import);
     * name : String[?] = lImportCS.name;
     * pivot : Element[?] = lImportCS.pivot;
     *  |
     * aImport.name := name;
     * }
     * 
     */
    protected boolean MAP_m_ImportCS_name_pivot(final /*@NonInvalid*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull ImportCS lImportCS) throws ReflectiveOperationException {
        try {
            // predicates and unrealized variables
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull IdResolver idResolver = executor.getIdResolver();
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class TYP_Import_0 = idResolver.getClass(CLSSid_Import, null);
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot = lImportCS.getPivot();
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Import aImport = ClassUtil.nonNullState((Import)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, pivot, TYP_Import_0));
            final /*@Thrown*/ java.lang.@org.eclipse.jdt.annotation.Nullable String name = lImportCS.getName();
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot_0 = lImportCS.getPivot();
            // property assignments
            aImport.setName(name);
            boolean m_ImportCS_name_pivot = ValueUtil.TRUE_VALUE;
            return m_ImportCS_name_pivot;
        } catch (Throwable e) {
            return handleExecutionFailure("MAP_m_ImportCS_name_pivot", e);
        }
    }
    
    /**
     * 
     * map m_ImportCS_ownedPathName_pivot in BaseCS2AS_qvtp_qvtcas {
     *   leftCS (lImportCS : basecs::ImportCS[?];
     *  |)
     * { |}
     * rightAS ( |)
     * { |}
     * where ( |)
     * {aImport : Import[1] = lImportCS.pivot.oclAsType(Import)
     *   ;
     * ownedPathName : basecs::PathNameCS[?] = lImportCS.ownedPathName;
     * pivot : Element[?] = lImportCS.pivot;
     * importedNamespace : Namespace[?] = aImport.lookupNamespace(ownedPathName);
     *  |
     * aImport.importedNamespace := importedNamespace;
     * }
     * 
     */
    protected boolean MAP_m_ImportCS_ownedPathName_pivot(final /*@NonInvalid*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull ImportCS lImportCS_0) throws ReflectiveOperationException {
        try {
            // predicates and unrealized variables
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull IdResolver idResolver = executor.getIdResolver();
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class TYP_Import_0 = idResolver.getClass(CLSSid_Import, null);
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot = lImportCS_0.getPivot();
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Import aImport = ClassUtil.nonNullState((Import)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, pivot, TYP_Import_0));
            final /*@Thrown*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.Nullable PathNameCS ownedPathName = lImportCS_0.getOwnedPathName();
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot_0 = lImportCS_0.getPivot();
            if (ownedPathName == null) {
                throw throwNull(lImportCS_0, "Null source for \'\'http://www.eclipse.org/ocl/2015/BaseCS\'::PathNameCS::ownedPathElements\'");
            }
            @SuppressWarnings("null")
            final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<PathElementCS> ownedPathElements = ownedPathName.getOwnedPathElements();
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Namespace lookupNamespace = this.lookupNamespace(aImport, (List<PathElementCS>)ownedPathElements);
            // property assignments
            if (lookupNamespace == null) {
                throw throwNull(lImportCS_0, "Null value");
            }
            aImport.setImportedNamespace(lookupNamespace);
            boolean m_ImportCS_ownedPathName_pivot = ValueUtil.TRUE_VALUE;
            return m_ImportCS_ownedPathName_pivot;
        } catch (Throwable e) {
            return handleExecutionFailure("MAP_m_ImportCS_ownedPathName_pivot", e);
        }
    }
    
    /**
     * 
     * map m_ImportCS_pivot in BaseCS2AS_qvtp_qvtcas {leftCS (lImportCS : basecs::ImportCS[?];
     *  |)
     * { |}
     * rightAS ( |)
     * {realize rImport : Import[1];
     *  |}
     * where ( |)
     * { |
     * lImportCS.pivot := rImport;
     * }
     * 
     */
    protected boolean MAP_m_ImportCS_pivot(final /*@NonInvalid*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull ImportCS lImportCS_1) throws ReflectiveOperationException {
        try {
            // predicates and unrealized variables
            // creations
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Import rImport = PivotFactory.eINSTANCE.createImport();
            assert rImport != null;
            models[1/*rightAS*/].add(rImport);
            // property assignments
            lImportCS_1.setPivot(rImport);
            boolean m_ImportCS_pivot = ValueUtil.TRUE_VALUE;
            return m_ImportCS_pivot;
        } catch (Throwable e) {
            return handleExecutionFailure("MAP_m_ImportCS_pivot", e);
        }
    }
    
    /**
     * 
     * map m_LambdaTypeCS_name_ownedParameterTypes_pivot in BaseCS2AS_qvtp_qvtcas {
     *   leftCS (lLambdaTypeCS : basecs::LambdaTypeCS[?];
     *  |)
     * { |}
     * rightAS ( |)
     * { |}
     * where ( |)
     * {aLambdaType : LambdaType[1] = lLambdaTypeCS.pivot.oclAsType(LambdaType)
     *   ;
     * name : String[?] = lLambdaTypeCS.name;
     * ownedParameterTypes : OrderedSet(basecs::TypedRefCS) = lLambdaTypeCS.ownedParameterTypes;
     * pivot : Element[?] = lLambdaTypeCS.pivot;
     * _'\u00ABcollect\u00BB' : Sequence(Class) = ownedParameterTypes->collect(_'1_' |
     *     _'1_'.pivot.oclAsType(Class));
     *  |
     * aLambdaType.name := name;
     * aLambdaType.parameterType := _'\u00ABcollect\u00BB';
     * }
     * 
     */
    protected boolean MAP_m_LambdaTypeCS_name_ownedParameterTypes_pivot(final /*@NonInvalid*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull LambdaTypeCS lLambdaTypeCS) throws ReflectiveOperationException {
        try {
            // predicates and unrealized variables
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull IdResolver idResolver = executor.getIdResolver();
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class TYP_LambdaType_0 = idResolver.getClass(CLSSid_LambdaType, null);
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot = lLambdaTypeCS.getPivot();
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull LambdaType aLambdaType = ClassUtil.nonNullState((LambdaType)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, pivot, TYP_LambdaType_0));
            final /*@Thrown*/ java.lang.@org.eclipse.jdt.annotation.Nullable String name = lLambdaTypeCS.getName();
            @SuppressWarnings("null")
            final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<TypedRefCS> ownedParameterTypes = lLambdaTypeCS.getOwnedParameterTypes();
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot_0 = lLambdaTypeCS.getPivot();
            final /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull OrderedSetValue BOXED_ownedParameterTypes = idResolver.createOrderedSetOfAll(ORD_CLSSid_TypedRefCS, ownedParameterTypes);
            /*@Thrown*/ SequenceValue.@org.eclipse.jdt.annotation.NonNull Accumulator accumulator = ValueUtil.createSequenceAccumulatorValue(SEQ_CLSSid_Class);
            @Nullable Iterator<Object> ITERATOR__1 = BOXED_ownedParameterTypes.iterator();
            /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull SequenceValue _171_collect_187;
            while (true) {
                if (!ITERATOR__1.hasNext()) {
                    _171_collect_187 = accumulator;
                    break;
                }
                /*@NonInvalid*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.Nullable TypedRefCS _1 = (TypedRefCS)ITERATOR__1.next();
                /**
                 * _'1_'.pivot.oclAsType(Class)
                 */
                final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class TYP_Class_0 = idResolver.getClass(CLSSid_Class, null);
                if (_1 == null) {
                    throw throwNull(lLambdaTypeCS, "Null source for \'\'http://www.eclipse.org/ocl/2015/BaseCS\'::PivotableElementCS::pivot\'");
                }
                final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot_1 = _1.getPivot();
                final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class oclAsType = ClassUtil.nonNullState((Class)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, pivot_1, TYP_Class_0));
                //
                accumulator.add(oclAsType);
            }
            // property assignments
            aLambdaType.setName(name);
            final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<Class> ECORE__171_collect_187 = ((IdResolver.IdResolverExtension)idResolver).ecoreValueOfAll(Class.class, _171_collect_187);
            aLambdaType.getParameterType().addAll(ECORE__171_collect_187);
            boolean m_LambdaTypeCS_name_ownedParameterTypes_pivot = ValueUtil.TRUE_VALUE;
            return m_LambdaTypeCS_name_ownedParameterTypes_pivot;
        } catch (Throwable e) {
            return handleExecutionFailure("MAP_m_LambdaTypeCS_name_ownedParameterTypes_pivot", e);
        }
    }
    
    /**
     * 
     * map m_LambdaTypeCS_ownedContextType_pivot in BaseCS2AS_qvtp_qvtcas {
     *   leftCS (lLambdaTypeCS : basecs::LambdaTypeCS[?];
     *  |)
     * { |}
     * rightAS ( |)
     * { |}
     * where ( |)
     * {aLambdaType : LambdaType[1] = lLambdaTypeCS.pivot.oclAsType(LambdaType)
     *   ;
     * ownedContextType : basecs::TypedRefCS[?] = lLambdaTypeCS.ownedContextType;
     * pivot : Element[?] = lLambdaTypeCS.pivot;
     * aClass : Class[1] = ownedContextType.pivot.oclAsType(Class);
     * pivot1 : Element[?] = ownedContextType.pivot;
     *  |
     * aLambdaType.contextType := aClass;
     * }
     * 
     */
    protected boolean MAP_m_LambdaTypeCS_ownedContextType_pivot(final /*@NonInvalid*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull LambdaTypeCS lLambdaTypeCS_0) throws ReflectiveOperationException {
        try {
            // predicates and unrealized variables
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull IdResolver idResolver = executor.getIdResolver();
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class TYP_LambdaType_0 = idResolver.getClass(CLSSid_LambdaType, null);
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot = lLambdaTypeCS_0.getPivot();
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull LambdaType aLambdaType = ClassUtil.nonNullState((LambdaType)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, pivot, TYP_LambdaType_0));
            final /*@Thrown*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.Nullable TypedRefCS ownedContextType = lLambdaTypeCS_0.getOwnedContextType();
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot_0 = lLambdaTypeCS_0.getPivot();
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class TYP_Class_0 = idResolver.getClass(CLSSid_Class, null);
            if (ownedContextType == null) {
                throw throwNull(lLambdaTypeCS_0, "Null source for \'\'http://www.eclipse.org/ocl/2015/BaseCS\'::PivotableElementCS::pivot\'");
            }
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot_1 = ownedContextType.getPivot();
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class aClass = ClassUtil.nonNullState((Class)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, pivot_1, TYP_Class_0));
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot1 = ownedContextType.getPivot();
            // property assignments
            aLambdaType.setContextType(aClass);
            boolean m_LambdaTypeCS_ownedContextType_pivot = ValueUtil.TRUE_VALUE;
            return m_LambdaTypeCS_ownedContextType_pivot;
        } catch (Throwable e) {
            return handleExecutionFailure("MAP_m_LambdaTypeCS_ownedContextType_pivot", e);
        }
    }
    
    /**
     * 
     * map m_LambdaTypeCS_ownedResultType_pivot in BaseCS2AS_qvtp_qvtcas {
     *   leftCS (lLambdaTypeCS : basecs::LambdaTypeCS[?];
     *  |)
     * { |}
     * rightAS ( |)
     * { |}
     * where ( |)
     * {aLambdaType : LambdaType[1] = lLambdaTypeCS.pivot.oclAsType(LambdaType)
     *   ;
     * ownedResultType : basecs::TypedRefCS[?] = lLambdaTypeCS.ownedResultType;
     * pivot : Element[?] = lLambdaTypeCS.pivot;
     * aClass : Class[1] = ownedResultType.pivot.oclAsType(Class);
     * pivot1 : Element[?] = ownedResultType.pivot;
     *  |
     * aLambdaType.resultType := aClass;
     * }
     * 
     */
    protected boolean MAP_m_LambdaTypeCS_ownedResultType_pivot(final /*@NonInvalid*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull LambdaTypeCS lLambdaTypeCS_1) throws ReflectiveOperationException {
        try {
            // predicates and unrealized variables
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull IdResolver idResolver = executor.getIdResolver();
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class TYP_LambdaType_0 = idResolver.getClass(CLSSid_LambdaType, null);
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot = lLambdaTypeCS_1.getPivot();
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull LambdaType aLambdaType = ClassUtil.nonNullState((LambdaType)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, pivot, TYP_LambdaType_0));
            final /*@Thrown*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.Nullable TypedRefCS ownedResultType = lLambdaTypeCS_1.getOwnedResultType();
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot_0 = lLambdaTypeCS_1.getPivot();
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class TYP_Class_0 = idResolver.getClass(CLSSid_Class, null);
            if (ownedResultType == null) {
                throw throwNull(lLambdaTypeCS_1, "Null source for \'\'http://www.eclipse.org/ocl/2015/BaseCS\'::PivotableElementCS::pivot\'");
            }
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot_1 = ownedResultType.getPivot();
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class aClass = ClassUtil.nonNullState((Class)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, pivot_1, TYP_Class_0));
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot1 = ownedResultType.getPivot();
            // property assignments
            aLambdaType.setResultType(aClass);
            boolean m_LambdaTypeCS_ownedResultType_pivot = ValueUtil.TRUE_VALUE;
            return m_LambdaTypeCS_ownedResultType_pivot;
        } catch (Throwable e) {
            return handleExecutionFailure("MAP_m_LambdaTypeCS_ownedResultType_pivot", e);
        }
    }
    
    /**
     * 
     * map m_LambdaTypeCS_pivot in BaseCS2AS_qvtp_qvtcas {leftCS (lLambdaTypeCS : basecs::LambdaTypeCS[?];
     *  |)
     * { |}
     * rightAS ( |)
     * {realize rLambdaType : LambdaType[1];
     *  |}
     * where ( |)
     * { |
     * lLambdaTypeCS.pivot := rLambdaType;
     * }
     * 
     */
    protected boolean MAP_m_LambdaTypeCS_pivot(final /*@NonInvalid*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull LambdaTypeCS lLambdaTypeCS_2) throws ReflectiveOperationException {
        try {
            // predicates and unrealized variables
            // creations
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable LambdaType rLambdaType = PivotFactory.eINSTANCE.createLambdaType();
            assert rLambdaType != null;
            models[1/*rightAS*/].add(rLambdaType);
            // property assignments
            lLambdaTypeCS_2.setPivot(rLambdaType);
            boolean m_LambdaTypeCS_pivot = ValueUtil.TRUE_VALUE;
            return m_LambdaTypeCS_pivot;
        } catch (Throwable e) {
            return handleExecutionFailure("MAP_m_LambdaTypeCS_pivot", e);
        }
    }
    
    /**
     * 
     * map m_OperationCS_name_ownedAnnotations_ownedException in BaseCS2AS_qvtp_qvtcas {
     *   leftCS (lOperationCS : basecs::OperationCS[?];
     *  |)
     * { |}
     * rightAS ( |)
     * { |}
     * where ( |)
     * {aOperation : Operation[1] = lOperationCS.pivot.oclAsType(Operation)
     *   ;
     * name : String[?] = lOperationCS.name;
     * ownedAnnotations : OrderedSet(basecs::AnnotationElementCS) = lOperationCS.ownedAnnotations;
     * ownedExceptions : OrderedSet(basecs::TypedRefCS) = lOperationCS.ownedExceptions;
     * ownedParameters : OrderedSet(basecs::ParameterCS) = lOperationCS.ownedParameters;
     * ownedPostconditions : OrderedSet(basecs::ConstraintCS) = lOperationCS.ownedPostconditions;
     * ownedPreconditions : OrderedSet(basecs::ConstraintCS) = lOperationCS.ownedPreconditions;
     * pivot : Element[?] = lOperationCS.pivot;
     * qualifiers : OrderedSet(String) = lOperationCS.qualifiers;
     * includes : Boolean[1] = qualifiers->includes('static')
     *   ;
     * _'\u00ABcollect\u00BB' : Sequence(Class) = ownedExceptions->collect(_'1_' |
     *     _'1_'.pivot.oclAsType(Class))
     *   ;
     * _'\u00ABcollect\u00BB1' : Sequence(Constraint) = ownedPreconditions->collect(_'1_' |
     *     _'1_'.pivot.oclAsType(Constraint))
     *   ;
     * _'\u00ABcollect\u00BB2' : Sequence(Element) = ownedAnnotations->collect(_'1_' | _'1_'.pivot)
     *   ;
     * _'\u00ABcollect\u00BB3' : Sequence(Parameter) = ownedParameters->collect(_'1_' |
     *     _'1_'.pivot.oclAsType(Parameter))
     *   ;
     * _'\u00ABcollect\u00BB4' : Sequence(Constraint) = ownedPostconditions->collect(_'1_' |
     *     _'1_'.pivot.oclAsType(Constraint));
     *  |
     * aOperation.isStatic := includes;
     * aOperation.name := name;
     * aOperation.ownedAnnotations := _'\u00ABcollect\u00BB2';
     * aOperation.ownedParameters := _'\u00ABcollect\u00BB3';
     * aOperation.ownedPostconditions := _'\u00ABcollect\u00BB4';
     * aOperation.ownedPreconditions := _'\u00ABcollect\u00BB1';
     * aOperation.raisedExceptions := _'\u00ABcollect\u00BB';
     * }
     * 
     */
    protected boolean MAP_m_OperationCS_name_ownedAnnotations_ownedException(final /*@NonInvalid*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull OperationCS lOperationCS) throws ReflectiveOperationException {
        try {
            // predicates and unrealized variables
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull IdResolver idResolver = executor.getIdResolver();
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class TYP_Operation_0 = idResolver.getClass(CLSSid_Operation, null);
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot = lOperationCS.getPivot();
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Operation aOperation = ClassUtil.nonNullState((Operation)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, pivot, TYP_Operation_0));
            final /*@Thrown*/ java.lang.@org.eclipse.jdt.annotation.Nullable String name = lOperationCS.getName();
            @SuppressWarnings("null")
            final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<AnnotationElementCS> ownedAnnotations = lOperationCS.getOwnedAnnotations();
            @SuppressWarnings("null")
            final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<TypedRefCS> ownedExceptions = lOperationCS.getOwnedExceptions();
            @SuppressWarnings("null")
            final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<ParameterCS> ownedParameters = lOperationCS.getOwnedParameters();
            @SuppressWarnings("null")
            final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<ConstraintCS> ownedPostconditions = lOperationCS.getOwnedPostconditions();
            @SuppressWarnings("null")
            final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<ConstraintCS> ownedPreconditions = lOperationCS.getOwnedPreconditions();
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot_0 = lOperationCS.getPivot();
            @SuppressWarnings("null")
            final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<String> qualifiers = lOperationCS.getQualifiers();
            final /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull OrderedSetValue BOXED_qualifiers = idResolver.createOrderedSetOfAll(ORD_PRIMid_String, qualifiers);
            final /*@Thrown*/ boolean includes = CollectionIncludesOperation.INSTANCE.evaluate(BOXED_qualifiers, STR_static).booleanValue();
            final /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull OrderedSetValue BOXED_ownedExceptions = idResolver.createOrderedSetOfAll(ORD_CLSSid_TypedRefCS, ownedExceptions);
            /*@Thrown*/ SequenceValue.@org.eclipse.jdt.annotation.NonNull Accumulator accumulator = ValueUtil.createSequenceAccumulatorValue(SEQ_CLSSid_Class);
            @Nullable Iterator<Object> ITERATOR__1 = BOXED_ownedExceptions.iterator();
            /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull SequenceValue _171_collect_187;
            while (true) {
                if (!ITERATOR__1.hasNext()) {
                    _171_collect_187 = accumulator;
                    break;
                }
                /*@NonInvalid*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.Nullable TypedRefCS _1 = (TypedRefCS)ITERATOR__1.next();
                /**
                 * _'1_'.pivot.oclAsType(Class)
                 */
                final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class TYP_Class_0 = idResolver.getClass(CLSSid_Class, null);
                if (_1 == null) {
                    throw throwNull(lOperationCS, "Null source for \'\'http://www.eclipse.org/ocl/2015/BaseCS\'::PivotableElementCS::pivot\'");
                }
                final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot_1 = _1.getPivot();
                final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class oclAsType = ClassUtil.nonNullState((Class)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, pivot_1, TYP_Class_0));
                //
                accumulator.add(oclAsType);
            }
            final /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull OrderedSetValue BOXED_ownedPreconditions = idResolver.createOrderedSetOfAll(ORD_CLSSid_ConstraintCS, ownedPreconditions);
            /*@Thrown*/ SequenceValue.@org.eclipse.jdt.annotation.NonNull Accumulator accumulator_0 = ValueUtil.createSequenceAccumulatorValue(SEQ_CLSSid_Constraint);
            @Nullable Iterator<Object> ITERATOR__1_0 = BOXED_ownedPreconditions.iterator();
            /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull SequenceValue _171_collect_187_1;
            while (true) {
                if (!ITERATOR__1_0.hasNext()) {
                    _171_collect_187_1 = accumulator_0;
                    break;
                }
                /*@NonInvalid*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.Nullable ConstraintCS _1_0 = (ConstraintCS)ITERATOR__1_0.next();
                /**
                 * _'1_'.pivot.oclAsType(Constraint)
                 */
                final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class TYP_Constraint_0 = idResolver.getClass(CLSSid_Constraint, null);
                if (_1_0 == null) {
                    throw throwNull(lOperationCS, "Null source for \'\'http://www.eclipse.org/ocl/2015/BaseCS\'::PivotableElementCS::pivot\'");
                }
                final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot_2 = _1_0.getPivot();
                final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Constraint oclAsType_0 = ClassUtil.nonNullState((Constraint)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, pivot_2, TYP_Constraint_0));
                //
                accumulator_0.add(oclAsType_0);
            }
            final /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull OrderedSetValue BOXED_ownedAnnotations = idResolver.createOrderedSetOfAll(ORD_CLSSid_AnnotationElementCS, ownedAnnotations);
            /*@Thrown*/ SequenceValue.@org.eclipse.jdt.annotation.NonNull Accumulator accumulator_1 = ValueUtil.createSequenceAccumulatorValue(SEQ_CLSSid_Element);
            @Nullable Iterator<Object> ITERATOR__1_1 = BOXED_ownedAnnotations.iterator();
            /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull SequenceValue _171_collect_187_2;
            while (true) {
                if (!ITERATOR__1_1.hasNext()) {
                    _171_collect_187_2 = accumulator_1;
                    break;
                }
                /*@NonInvalid*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.Nullable AnnotationElementCS _1_1 = (AnnotationElementCS)ITERATOR__1_1.next();
                /**
                 * _'1_'.pivot
                 */
                if (_1_1 == null) {
                    throw throwNull(lOperationCS, "Null source for \'\'http://www.eclipse.org/ocl/2015/BaseCS\'::PivotableElementCS::pivot\'");
                }
                final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot_3 = _1_1.getPivot();
                //
                accumulator_1.add(pivot_3);
            }
            final /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull OrderedSetValue BOXED_ownedParameters = idResolver.createOrderedSetOfAll(ORD_CLSSid_ParameterCS, ownedParameters);
            /*@Thrown*/ SequenceValue.@org.eclipse.jdt.annotation.NonNull Accumulator accumulator_2 = ValueUtil.createSequenceAccumulatorValue(SEQ_CLSSid_Parameter);
            @Nullable Iterator<Object> ITERATOR__1_2 = BOXED_ownedParameters.iterator();
            /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull SequenceValue _171_collect_187_3;
            while (true) {
                if (!ITERATOR__1_2.hasNext()) {
                    _171_collect_187_3 = accumulator_2;
                    break;
                }
                /*@NonInvalid*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.Nullable ParameterCS _1_2 = (ParameterCS)ITERATOR__1_2.next();
                /**
                 * _'1_'.pivot.oclAsType(Parameter)
                 */
                final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class TYP_Parameter_0 = idResolver.getClass(CLSSid_Parameter, null);
                if (_1_2 == null) {
                    throw throwNull(lOperationCS, "Null source for \'\'http://www.eclipse.org/ocl/2015/BaseCS\'::PivotableElementCS::pivot\'");
                }
                final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot_4 = _1_2.getPivot();
                final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Parameter oclAsType_1 = ClassUtil.nonNullState((Parameter)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, pivot_4, TYP_Parameter_0));
                //
                accumulator_2.add(oclAsType_1);
            }
            final /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull OrderedSetValue BOXED_ownedPostconditions = idResolver.createOrderedSetOfAll(ORD_CLSSid_ConstraintCS, ownedPostconditions);
            /*@Thrown*/ SequenceValue.@org.eclipse.jdt.annotation.NonNull Accumulator accumulator_3 = ValueUtil.createSequenceAccumulatorValue(SEQ_CLSSid_Constraint);
            @Nullable Iterator<Object> ITERATOR__1_3 = BOXED_ownedPostconditions.iterator();
            /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull SequenceValue _171_collect_187_4;
            while (true) {
                if (!ITERATOR__1_3.hasNext()) {
                    _171_collect_187_4 = accumulator_3;
                    break;
                }
                /*@NonInvalid*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.Nullable ConstraintCS _1_3 = (ConstraintCS)ITERATOR__1_3.next();
                /**
                 * _'1_'.pivot.oclAsType(Constraint)
                 */
                final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class TYP_Constraint_1 = idResolver.getClass(CLSSid_Constraint, null);
                if (_1_3 == null) {
                    throw throwNull(lOperationCS, "Null source for \'\'http://www.eclipse.org/ocl/2015/BaseCS\'::PivotableElementCS::pivot\'");
                }
                final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot_5 = _1_3.getPivot();
                final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Constraint oclAsType_2 = ClassUtil.nonNullState((Constraint)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, pivot_5, TYP_Constraint_1));
                //
                accumulator_3.add(oclAsType_2);
            }
            // property assignments
            aOperation.setIsStatic(includes);
            aOperation.setName(name);
            final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<Element> ECORE__171_collect_187_2 = ((IdResolver.IdResolverExtension)idResolver).ecoreValueOfAll(Element.class, _171_collect_187_2);
            aOperation.getOwnedAnnotations().addAll(ECORE__171_collect_187_2);
            final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<Parameter> ECORE__171_collect_187_3 = ((IdResolver.IdResolverExtension)idResolver).ecoreValueOfAll(Parameter.class, _171_collect_187_3);
            aOperation.getOwnedParameters().addAll(ECORE__171_collect_187_3);
            final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<Constraint> ECORE__171_collect_187_4 = ((IdResolver.IdResolverExtension)idResolver).ecoreValueOfAll(Constraint.class, _171_collect_187_4);
            aOperation.getOwnedPostconditions().addAll(ECORE__171_collect_187_4);
            final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<Constraint> ECORE__171_collect_187_1 = ((IdResolver.IdResolverExtension)idResolver).ecoreValueOfAll(Constraint.class, _171_collect_187_1);
            aOperation.getOwnedPreconditions().addAll(ECORE__171_collect_187_1);
            final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<Class> ECORE__171_collect_187 = ((IdResolver.IdResolverExtension)idResolver).ecoreValueOfAll(Class.class, _171_collect_187);
            aOperation.getRaisedExceptions().addAll(ECORE__171_collect_187);
            boolean m_OperationCS_name_ownedAnnotations_ownedException = ValueUtil.TRUE_VALUE;
            return m_OperationCS_name_ownedAnnotations_ownedException;
        } catch (Throwable e) {
            return handleExecutionFailure("MAP_m_OperationCS_name_ownedAnnotations_ownedException", e);
        }
    }
    
    /**
     * 
     * map m_OperationCS_ownedBodyExpressions_pivot in BaseCS2AS_qvtp_qvtcas {
     *   leftCS (lOperationCS : basecs::OperationCS[?];
     *  |)
     * { |}
     * rightAS ( |)
     * { |}
     * where ( |)
     * {aOperation : Operation[1] = lOperationCS.pivot.oclAsType(Operation)
     *   ;
     * ownedBodyExpressions : OrderedSet(basecs::SpecificationCS) = lOperationCS.ownedBodyExpressions;
     * pivot : Element[?] = lOperationCS.pivot;
     * bodyExpression : LanguageExpression[1] = ownedBodyExpressions->collect(_'1_' |
     *     _'1_'.pivot.oclAsType(LanguageExpression))
     *   ->oclAsType(LanguageExpression);
     *  |
     * aOperation.bodyExpression := bodyExpression;
     * }
     * 
     */
    protected boolean MAP_m_OperationCS_ownedBodyExpressions_pivot(final /*@NonInvalid*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull OperationCS lOperationCS_0) throws ReflectiveOperationException {
        try {
            // predicates and unrealized variables
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull IdResolver idResolver = executor.getIdResolver();
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class TYP_Operation_0 = idResolver.getClass(CLSSid_Operation, null);
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot = lOperationCS_0.getPivot();
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Operation aOperation = ClassUtil.nonNullState((Operation)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, pivot, TYP_Operation_0));
            @SuppressWarnings("null")
            final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<SpecificationCS> ownedBodyExpressions = lOperationCS_0.getOwnedBodyExpressions();
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot_0 = lOperationCS_0.getPivot();
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class TYP_LanguageExpression_1 = idResolver.getClass(CLSSid_LanguageExpression, null);
            final /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull OrderedSetValue BOXED_ownedBodyExpressions = idResolver.createOrderedSetOfAll(ORD_CLSSid_SpecificationCS, ownedBodyExpressions);
            /*@Thrown*/ SequenceValue.@org.eclipse.jdt.annotation.NonNull Accumulator accumulator = ValueUtil.createSequenceAccumulatorValue(SEQ_CLSSid_LanguageExpression);
            @Nullable Iterator<Object> ITERATOR__1 = BOXED_ownedBodyExpressions.iterator();
            /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull SequenceValue collect;
            while (true) {
                if (!ITERATOR__1.hasNext()) {
                    collect = accumulator;
                    break;
                }
                /*@NonInvalid*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.Nullable SpecificationCS _1 = (SpecificationCS)ITERATOR__1.next();
                /**
                 * _'1_'.pivot.oclAsType(LanguageExpression)
                 */
                if (_1 == null) {
                    throw throwNull(lOperationCS_0, "Null source for \'\'http://www.eclipse.org/ocl/2015/BaseCS\'::PivotableElementCS::pivot\'");
                }
                final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot_1 = _1.getPivot();
                final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull LanguageExpression oclAsType = ClassUtil.nonNullState((LanguageExpression)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, pivot_1, TYP_LanguageExpression_1));
                //
                accumulator.add(oclAsType);
            }
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull LanguageExpression bodyExpression = ClassUtil.nonNullState((LanguageExpression)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, collect, TYP_LanguageExpression_1));
            // property assignments
            aOperation.setBodyExpression(bodyExpression);
            boolean m_OperationCS_ownedBodyExpressions_pivot = ValueUtil.TRUE_VALUE;
            return m_OperationCS_ownedBodyExpressions_pivot;
        } catch (Throwable e) {
            return handleExecutionFailure("MAP_m_OperationCS_ownedBodyExpressions_pivot", e);
        }
    }
    
    /**
     * 
     * map m_OperationCS_ownedSignature_pivot in BaseCS2AS_qvtp_qvtcas {
     *   leftCS (lOperationCS : basecs::OperationCS[?];
     *  |)
     * { |}
     * rightAS ( |)
     * { |}
     * where ( |)
     * {aOperation : Operation[1] = lOperationCS.pivot.oclAsType(Operation)
     *   ;
     * ownedSignature : basecs::TemplateSignatureCS[?] = lOperationCS.ownedSignature;
     * pivot : Element[?] = lOperationCS.pivot;
     * aTemplateSignature : TemplateSignature[1] = ownedSignature.pivot.oclAsType(TemplateSignature);
     * pivot1 : Element[?] = ownedSignature.pivot;
     *  |
     * aTemplateSignature.owningElement := aOperation;
     * }
     * 
     */
    protected boolean MAP_m_OperationCS_ownedSignature_pivot(final /*@NonInvalid*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull OperationCS lOperationCS_1) throws ReflectiveOperationException {
        try {
            // predicates and unrealized variables
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull IdResolver idResolver = executor.getIdResolver();
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class TYP_Operation_0 = idResolver.getClass(CLSSid_Operation, null);
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot = lOperationCS_1.getPivot();
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Operation aOperation = ClassUtil.nonNullState((Operation)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, pivot, TYP_Operation_0));
            final /*@Thrown*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.Nullable TemplateSignatureCS ownedSignature = lOperationCS_1.getOwnedSignature();
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot_0 = lOperationCS_1.getPivot();
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class TYP_TemplateSignature_0 = idResolver.getClass(CLSSid_TemplateSignature, null);
            if (ownedSignature == null) {
                throw throwNull(lOperationCS_1, "Null source for \'\'http://www.eclipse.org/ocl/2015/BaseCS\'::PivotableElementCS::pivot\'");
            }
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot_1 = ownedSignature.getPivot();
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull TemplateSignature aTemplateSignature = ClassUtil.nonNullState((TemplateSignature)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, pivot_1, TYP_TemplateSignature_0));
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot1 = ownedSignature.getPivot();
            // property assignments
            aTemplateSignature.setOwningElement(aOperation);
            boolean m_OperationCS_ownedSignature_pivot = ValueUtil.TRUE_VALUE;
            return m_OperationCS_ownedSignature_pivot;
        } catch (Throwable e) {
            return handleExecutionFailure("MAP_m_OperationCS_ownedSignature_pivot", e);
        }
    }
    
    /**
     * 
     * map m_OperationCS_ownedType_pivot in BaseCS2AS_qvtp_qvtcas {
     *   leftCS (lOperationCS : basecs::OperationCS[?];
     *  |)
     * { |}
     * rightAS ( |)
     * { |}
     * where ( |)
     * {aOperation : Operation[1] = lOperationCS.pivot.oclAsType(Operation)
     *   ;
     * ownedType : basecs::TypedRefCS[?] = lOperationCS.ownedType;
     * pivot : Element[?] = lOperationCS.pivot;
     * aClass : Class[1] = ownedType.pivot.oclAsType(Class);
     * pivot1 : Element[?] = ownedType.pivot;
     *  |
     * aOperation.type := aClass;
     * }
     * 
     */
    protected boolean MAP_m_OperationCS_ownedType_pivot(final /*@NonInvalid*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull OperationCS lOperationCS_2) throws ReflectiveOperationException {
        try {
            // predicates and unrealized variables
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull IdResolver idResolver = executor.getIdResolver();
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class TYP_Operation_0 = idResolver.getClass(CLSSid_Operation, null);
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot = lOperationCS_2.getPivot();
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Operation aOperation = ClassUtil.nonNullState((Operation)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, pivot, TYP_Operation_0));
            final /*@Thrown*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.Nullable TypedRefCS ownedType = lOperationCS_2.getOwnedType();
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot_0 = lOperationCS_2.getPivot();
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class TYP_Class_0 = idResolver.getClass(CLSSid_Class, null);
            if (ownedType == null) {
                throw throwNull(lOperationCS_2, "Null source for \'\'http://www.eclipse.org/ocl/2015/BaseCS\'::PivotableElementCS::pivot\'");
            }
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot_1 = ownedType.getPivot();
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class aClass = ClassUtil.nonNullState((Class)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, pivot_1, TYP_Class_0));
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot1 = ownedType.getPivot();
            // property assignments
            aOperation.setType(aClass);
            boolean m_OperationCS_ownedType_pivot = ValueUtil.TRUE_VALUE;
            return m_OperationCS_ownedType_pivot;
        } catch (Throwable e) {
            return handleExecutionFailure("MAP_m_OperationCS_ownedType_pivot", e);
        }
    }
    
    /**
     * 
     * map m_OperationCS_pivot in BaseCS2AS_qvtp_qvtcas {leftCS (lOperationCS : basecs::OperationCS[?];
     *  |)
     * { |}
     * rightAS ( |)
     * {realize rOperation : Operation[1];
     *  |}
     * where ( |)
     * { |
     * lOperationCS.pivot := rOperation;
     * }
     * 
     */
    protected boolean MAP_m_OperationCS_pivot(final /*@NonInvalid*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull OperationCS lOperationCS_3) throws ReflectiveOperationException {
        try {
            // predicates and unrealized variables
            // creations
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Operation rOperation = PivotFactory.eINSTANCE.createOperation();
            assert rOperation != null;
            models[1/*rightAS*/].add(rOperation);
            // property assignments
            lOperationCS_3.setPivot(rOperation);
            boolean m_OperationCS_pivot = ValueUtil.TRUE_VALUE;
            return m_OperationCS_pivot;
        } catch (Throwable e) {
            return handleExecutionFailure("MAP_m_OperationCS_pivot", e);
        }
    }
    
    /**
     * 
     * map m_PackageCS_name_nsPrefix_nsURI_ownedAnnotations_o in BaseCS2AS_qvtp_qvtcas {
     *   leftCS (lPackageCS : basecs::PackageCS[?];
     *  |)
     * { |}
     * rightAS ( |)
     * { |}
     * where ( |)
     * {aPackage : Package[1] = lPackageCS.pivot.oclAsType(Package)
     *   ;
     * name : String[?] = lPackageCS.name;
     * nsPrefix : String[?] = lPackageCS.nsPrefix;
     * nsURI : String[?] = lPackageCS.nsURI;
     * ownedAnnotations : OrderedSet(basecs::AnnotationElementCS) = lPackageCS.ownedAnnotations;
     * ownedClasses : OrderedSet(basecs::ClassCS) = lPackageCS.ownedClasses;
     * ownedPackages : OrderedSet(basecs::PackageCS) = lPackageCS.ownedPackages;
     * pivot : Element[?] = lPackageCS.pivot;
     * _'\u00ABcollect\u00BB' : Sequence(Element) = ownedAnnotations->collect(_'1_' | _'1_'.pivot)
     *   ;
     * _'\u00ABcollect\u00BB1' : Sequence(Package) = ownedPackages->collect(_'1_' |
     *     _'1_'.pivot.oclAsType(Package))
     *   ;
     * _'\u00ABcollect\u00BB2' : Sequence(Class) = ownedClasses->collect(_'1_' |
     *     _'1_'.pivot.oclAsType(Class));
     *  |
     * aPackage.URI := nsURI;
     * aPackage.name := name;
     * aPackage.nsPrefix := nsPrefix;
     * aPackage.ownedAnnotations := _'\u00ABcollect\u00BB';
     * aPackage.ownedClasses := _'\u00ABcollect\u00BB2';
     * aPackage.ownedPackages := _'\u00ABcollect\u00BB1';
     * }
     * 
     */
    protected boolean MAP_m_PackageCS_name_nsPrefix_nsURI_ownedAnnotations_o(final /*@NonInvalid*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull PackageCS lPackageCS) throws ReflectiveOperationException {
        try {
            // predicates and unrealized variables
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull IdResolver idResolver = executor.getIdResolver();
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class TYP_Package_0 = idResolver.getClass(CLSSid_Package, null);
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot = lPackageCS.getPivot();
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Package aPackage = ClassUtil.nonNullState((Package)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, pivot, TYP_Package_0));
            final /*@Thrown*/ java.lang.@org.eclipse.jdt.annotation.Nullable String name = lPackageCS.getName();
            final /*@Thrown*/ java.lang.@org.eclipse.jdt.annotation.Nullable String nsPrefix = lPackageCS.getNsPrefix();
            final /*@Thrown*/ java.lang.@org.eclipse.jdt.annotation.Nullable String nsURI = lPackageCS.getNsURI();
            @SuppressWarnings("null")
            final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<AnnotationElementCS> ownedAnnotations = lPackageCS.getOwnedAnnotations();
            @SuppressWarnings("null")
            final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<ClassCS> ownedClasses = lPackageCS.getOwnedClasses();
            @SuppressWarnings("null")
            final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<PackageCS> ownedPackages = lPackageCS.getOwnedPackages();
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot_0 = lPackageCS.getPivot();
            final /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull OrderedSetValue BOXED_ownedAnnotations = idResolver.createOrderedSetOfAll(ORD_CLSSid_AnnotationElementCS, ownedAnnotations);
            /*@Thrown*/ SequenceValue.@org.eclipse.jdt.annotation.NonNull Accumulator accumulator = ValueUtil.createSequenceAccumulatorValue(SEQ_CLSSid_Element);
            @Nullable Iterator<Object> ITERATOR__1 = BOXED_ownedAnnotations.iterator();
            /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull SequenceValue _171_collect_187;
            while (true) {
                if (!ITERATOR__1.hasNext()) {
                    _171_collect_187 = accumulator;
                    break;
                }
                /*@NonInvalid*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.Nullable AnnotationElementCS _1 = (AnnotationElementCS)ITERATOR__1.next();
                /**
                 * _'1_'.pivot
                 */
                if (_1 == null) {
                    throw throwNull(lPackageCS, "Null source for \'\'http://www.eclipse.org/ocl/2015/BaseCS\'::PivotableElementCS::pivot\'");
                }
                final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot_1 = _1.getPivot();
                //
                accumulator.add(pivot_1);
            }
            final /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull OrderedSetValue BOXED_ownedPackages = idResolver.createOrderedSetOfAll(ORD_CLSSid_PackageCS, ownedPackages);
            /*@Thrown*/ SequenceValue.@org.eclipse.jdt.annotation.NonNull Accumulator accumulator_0 = ValueUtil.createSequenceAccumulatorValue(SEQ_CLSSid_Package);
            @Nullable Iterator<Object> ITERATOR__1_0 = BOXED_ownedPackages.iterator();
            /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull SequenceValue _171_collect_187_1;
            while (true) {
                if (!ITERATOR__1_0.hasNext()) {
                    _171_collect_187_1 = accumulator_0;
                    break;
                }
                /*@NonInvalid*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.Nullable PackageCS _1_0 = (PackageCS)ITERATOR__1_0.next();
                /**
                 * _'1_'.pivot.oclAsType(Package)
                 */
                if (_1_0 == null) {
                    throw throwNull(lPackageCS, "Null source for \'\'http://www.eclipse.org/ocl/2015/BaseCS\'::PivotableElementCS::pivot\'");
                }
                final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot_2 = _1_0.getPivot();
                final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Package oclAsType = ClassUtil.nonNullState((Package)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, pivot_2, TYP_Package_0));
                //
                accumulator_0.add(oclAsType);
            }
            final /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull OrderedSetValue BOXED_ownedClasses = idResolver.createOrderedSetOfAll(ORD_CLSSid_ClassCS, ownedClasses);
            /*@Thrown*/ SequenceValue.@org.eclipse.jdt.annotation.NonNull Accumulator accumulator_1 = ValueUtil.createSequenceAccumulatorValue(SEQ_CLSSid_Class);
            @Nullable Iterator<Object> ITERATOR__1_1 = BOXED_ownedClasses.iterator();
            /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull SequenceValue _171_collect_187_2;
            while (true) {
                if (!ITERATOR__1_1.hasNext()) {
                    _171_collect_187_2 = accumulator_1;
                    break;
                }
                /*@NonInvalid*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.Nullable ClassCS _1_1 = (ClassCS)ITERATOR__1_1.next();
                /**
                 * _'1_'.pivot.oclAsType(Class)
                 */
                final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class TYP_Class_0 = idResolver.getClass(CLSSid_Class, null);
                if (_1_1 == null) {
                    throw throwNull(lPackageCS, "Null source for \'\'http://www.eclipse.org/ocl/2015/BaseCS\'::PivotableElementCS::pivot\'");
                }
                final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot_3 = _1_1.getPivot();
                final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class oclAsType_0 = ClassUtil.nonNullState((Class)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, pivot_3, TYP_Class_0));
                //
                accumulator_1.add(oclAsType_0);
            }
            // property assignments
            aPackage.setURI(nsURI);
            aPackage.setName(name);
            aPackage.setNsPrefix(nsPrefix);
            final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<Element> ECORE__171_collect_187 = ((IdResolver.IdResolverExtension)idResolver).ecoreValueOfAll(Element.class, _171_collect_187);
            aPackage.getOwnedAnnotations().addAll(ECORE__171_collect_187);
            final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<Class> ECORE__171_collect_187_2 = ((IdResolver.IdResolverExtension)idResolver).ecoreValueOfAll(Class.class, _171_collect_187_2);
            aPackage.getOwnedClasses().addAll(ECORE__171_collect_187_2);
            final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<Package> ECORE__171_collect_187_1 = ((IdResolver.IdResolverExtension)idResolver).ecoreValueOfAll(Package.class, _171_collect_187_1);
            aPackage.getOwnedPackages().addAll(ECORE__171_collect_187_1);
            boolean m_PackageCS_name_nsPrefix_nsURI_ownedAnnotations_o = ValueUtil.TRUE_VALUE;
            return m_PackageCS_name_nsPrefix_nsURI_ownedAnnotations_o;
        } catch (Throwable e) {
            return handleExecutionFailure("MAP_m_PackageCS_name_nsPrefix_nsURI_ownedAnnotations_o", e);
        }
    }
    
    /**
     * 
     * map m_PackageCS_pivot in BaseCS2AS_qvtp_qvtcas {leftCS (lPackageCS : basecs::PackageCS[?];
     *  |)
     * { |}
     * rightAS ( |)
     * {realize rPackage : Package[1];
     *  |}
     * where ( |)
     * { |
     * lPackageCS.pivot := rPackage;
     * }
     * 
     */
    protected boolean MAP_m_PackageCS_pivot(final /*@NonInvalid*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull PackageCS lPackageCS_0) throws ReflectiveOperationException {
        try {
            // predicates and unrealized variables
            // creations
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Package rPackage = PivotFactory.eINSTANCE.createPackage();
            assert rPackage != null;
            models[1/*rightAS*/].add(rPackage);
            // property assignments
            lPackageCS_0.setPivot(rPackage);
            boolean m_PackageCS_pivot = ValueUtil.TRUE_VALUE;
            return m_PackageCS_pivot;
        } catch (Throwable e) {
            return handleExecutionFailure("MAP_m_PackageCS_pivot", e);
        }
    }
    
    /**
     * 
     * map m_ParameterCS_name_ownedAnnotations_pivot in BaseCS2AS_qvtp_qvtcas {
     *   leftCS (lParameterCS : basecs::ParameterCS[?];
     *  |)
     * { |}
     * rightAS ( |)
     * { |}
     * where ( |)
     * {aParameter : Parameter[1] = lParameterCS.pivot.oclAsType(Parameter)
     *   ;
     * name : String[?] = lParameterCS.name;
     * ownedAnnotations : OrderedSet(basecs::AnnotationElementCS) = lParameterCS.ownedAnnotations;
     * pivot : Element[?] = lParameterCS.pivot;
     * _'\u00ABcollect\u00BB' : Sequence(Element) = ownedAnnotations->collect(_'1_' | _'1_'.pivot);
     *  |
     * aParameter.name := name;
     * aParameter.ownedAnnotations := _'\u00ABcollect\u00BB';
     * }
     * 
     */
    protected boolean MAP_m_ParameterCS_name_ownedAnnotations_pivot(final /*@NonInvalid*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull ParameterCS lParameterCS) throws ReflectiveOperationException {
        try {
            // predicates and unrealized variables
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull IdResolver idResolver = executor.getIdResolver();
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class TYP_Parameter_0 = idResolver.getClass(CLSSid_Parameter, null);
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot = lParameterCS.getPivot();
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Parameter aParameter = ClassUtil.nonNullState((Parameter)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, pivot, TYP_Parameter_0));
            final /*@Thrown*/ java.lang.@org.eclipse.jdt.annotation.Nullable String name = lParameterCS.getName();
            @SuppressWarnings("null")
            final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<AnnotationElementCS> ownedAnnotations = lParameterCS.getOwnedAnnotations();
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot_0 = lParameterCS.getPivot();
            final /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull OrderedSetValue BOXED_ownedAnnotations = idResolver.createOrderedSetOfAll(ORD_CLSSid_AnnotationElementCS, ownedAnnotations);
            /*@Thrown*/ SequenceValue.@org.eclipse.jdt.annotation.NonNull Accumulator accumulator = ValueUtil.createSequenceAccumulatorValue(SEQ_CLSSid_Element);
            @Nullable Iterator<Object> ITERATOR__1 = BOXED_ownedAnnotations.iterator();
            /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull SequenceValue _171_collect_187;
            while (true) {
                if (!ITERATOR__1.hasNext()) {
                    _171_collect_187 = accumulator;
                    break;
                }
                /*@NonInvalid*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.Nullable AnnotationElementCS _1 = (AnnotationElementCS)ITERATOR__1.next();
                /**
                 * _'1_'.pivot
                 */
                if (_1 == null) {
                    throw throwNull(lParameterCS, "Null source for \'\'http://www.eclipse.org/ocl/2015/BaseCS\'::PivotableElementCS::pivot\'");
                }
                final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot_1 = _1.getPivot();
                //
                accumulator.add(pivot_1);
            }
            // property assignments
            aParameter.setName(name);
            final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<Element> ECORE__171_collect_187 = ((IdResolver.IdResolverExtension)idResolver).ecoreValueOfAll(Element.class, _171_collect_187);
            aParameter.getOwnedAnnotations().addAll(ECORE__171_collect_187);
            boolean m_ParameterCS_name_ownedAnnotations_pivot = ValueUtil.TRUE_VALUE;
            return m_ParameterCS_name_ownedAnnotations_pivot;
        } catch (Throwable e) {
            return handleExecutionFailure("MAP_m_ParameterCS_name_ownedAnnotations_pivot", e);
        }
    }
    
    /**
     * 
     * map m_ParameterCS_ownedType_pivot in BaseCS2AS_qvtp_qvtcas {
     *   leftCS (lParameterCS : basecs::ParameterCS[?];
     *  |)
     * { |}
     * rightAS ( |)
     * { |}
     * where ( |)
     * {aParameter : Parameter[1] = lParameterCS.pivot.oclAsType(Parameter)
     *   ;
     * ownedType : basecs::TypedRefCS[?] = lParameterCS.ownedType;
     * pivot : Element[?] = lParameterCS.pivot;
     * aClass : Class[1] = ownedType.pivot.oclAsType(Class);
     * pivot1 : Element[?] = ownedType.pivot;
     *  |
     * aParameter.type := aClass;
     * }
     * 
     */
    protected boolean MAP_m_ParameterCS_ownedType_pivot(final /*@NonInvalid*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull ParameterCS lParameterCS_0) throws ReflectiveOperationException {
        try {
            // predicates and unrealized variables
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull IdResolver idResolver = executor.getIdResolver();
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class TYP_Parameter_0 = idResolver.getClass(CLSSid_Parameter, null);
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot = lParameterCS_0.getPivot();
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Parameter aParameter = ClassUtil.nonNullState((Parameter)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, pivot, TYP_Parameter_0));
            final /*@Thrown*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.Nullable TypedRefCS ownedType = lParameterCS_0.getOwnedType();
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot_0 = lParameterCS_0.getPivot();
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class TYP_Class_0 = idResolver.getClass(CLSSid_Class, null);
            if (ownedType == null) {
                throw throwNull(lParameterCS_0, "Null source for \'\'http://www.eclipse.org/ocl/2015/BaseCS\'::PivotableElementCS::pivot\'");
            }
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot_1 = ownedType.getPivot();
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class aClass = ClassUtil.nonNullState((Class)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, pivot_1, TYP_Class_0));
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot1 = ownedType.getPivot();
            // property assignments
            aParameter.setType(aClass);
            boolean m_ParameterCS_ownedType_pivot = ValueUtil.TRUE_VALUE;
            return m_ParameterCS_ownedType_pivot;
        } catch (Throwable e) {
            return handleExecutionFailure("MAP_m_ParameterCS_ownedType_pivot", e);
        }
    }
    
    /**
     * 
     * map m_ParameterCS_pivot in BaseCS2AS_qvtp_qvtcas {leftCS (lParameterCS : basecs::ParameterCS[?];
     *  |)
     * { |}
     * rightAS ( |)
     * {realize rParameter : Parameter[1];
     *  |}
     * where ( |)
     * { |
     * lParameterCS.pivot := rParameter;
     * }
     * 
     */
    protected boolean MAP_m_ParameterCS_pivot(final /*@NonInvalid*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull ParameterCS lParameterCS_1) throws ReflectiveOperationException {
        try {
            // predicates and unrealized variables
            // creations
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Parameter rParameter = PivotFactory.eINSTANCE.createParameter();
            assert rParameter != null;
            models[1/*rightAS*/].add(rParameter);
            // property assignments
            lParameterCS_1.setPivot(rParameter);
            boolean m_ParameterCS_pivot = ValueUtil.TRUE_VALUE;
            return m_ParameterCS_pivot;
        } catch (Throwable e) {
            return handleExecutionFailure("MAP_m_ParameterCS_pivot", e);
        }
    }
    
    /**
     * 
     * map m_PrimitiveTypeRefCS_name_pivot in BaseCS2AS_qvtp_qvtcas {
     *   leftCS (lPrimitiveTypeRefCS : basecs::PrimitiveTypeRefCS[?];
     *  |)
     * { |}
     * rightAS ( |)
     * { |}
     * where ( |)
     * {aPrimitiveType : PrimitiveType[1] = lPrimitiveTypeRefCS.pivot.oclAsType(PrimitiveType);
     * name : String[?] = lPrimitiveTypeRefCS.name;
     * pivot : Element[?] = lPrimitiveTypeRefCS.pivot;
     *  |
     * aPrimitiveType.name := name;
     * }
     * 
     */
    protected boolean MAP_m_PrimitiveTypeRefCS_name_pivot(final /*@NonInvalid*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull PrimitiveTypeRefCS lPrimitiveTypeRefCS) throws ReflectiveOperationException {
        try {
            // predicates and unrealized variables
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull IdResolver idResolver = executor.getIdResolver();
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class TYP_PrimitiveType_0 = idResolver.getClass(CLSSid_PrimitiveType, null);
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot = lPrimitiveTypeRefCS.getPivot();
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull PrimitiveType aPrimitiveType = ClassUtil.nonNullState((PrimitiveType)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, pivot, TYP_PrimitiveType_0));
            final /*@Thrown*/ java.lang.@org.eclipse.jdt.annotation.Nullable String name = lPrimitiveTypeRefCS.getName();
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot_0 = lPrimitiveTypeRefCS.getPivot();
            // property assignments
            aPrimitiveType.setName(name);
            boolean m_PrimitiveTypeRefCS_name_pivot = ValueUtil.TRUE_VALUE;
            return m_PrimitiveTypeRefCS_name_pivot;
        } catch (Throwable e) {
            return handleExecutionFailure("MAP_m_PrimitiveTypeRefCS_name_pivot", e);
        }
    }
    
    /**
     * 
     * map m_PrimitiveTypeRefCS_pivot in BaseCS2AS_qvtp_qvtcas {leftCS (lPrimitiveTypeRefCS : basecs::PrimitiveTypeRefCS[?];
     *  |)
     * { |}
     * rightAS ( |)
     * {realize rPrimitiveType : PrimitiveType[1];
     *  |}
     * where ( |)
     * { |
     * lPrimitiveTypeRefCS.pivot := rPrimitiveType;
     * }
     * 
     */
    protected boolean MAP_m_PrimitiveTypeRefCS_pivot(final /*@NonInvalid*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull PrimitiveTypeRefCS lPrimitiveTypeRefCS_0) throws ReflectiveOperationException {
        try {
            // predicates and unrealized variables
            // creations
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable PrimitiveType rPrimitiveType = PivotFactory.eINSTANCE.createPrimitiveType();
            assert rPrimitiveType != null;
            models[1/*rightAS*/].add(rPrimitiveType);
            // property assignments
            lPrimitiveTypeRefCS_0.setPivot(rPrimitiveType);
            boolean m_PrimitiveTypeRefCS_pivot = ValueUtil.TRUE_VALUE;
            return m_PrimitiveTypeRefCS_pivot;
        } catch (Throwable e) {
            return handleExecutionFailure("MAP_m_PrimitiveTypeRefCS_pivot", e);
        }
    }
    
    /**
     * 
     * map m_ReferenceCS_default_name_ownedAnnotations_pivot_ in BaseCS2AS_qvtp_qvtcas {
     *   leftCS (lReferenceCS : basecs::ReferenceCS[?];
     *  |)
     * { |}
     * rightAS ( |)
     * { |}
     * where ( |)
     * {aProperty : Property[1] = lReferenceCS.pivot.oclAsType(Property)
     *   ;
     * default : String[?] = lReferenceCS.default;
     * name : String[?] = lReferenceCS.name;
     * ownedAnnotations : OrderedSet(basecs::AnnotationElementCS) = lReferenceCS.ownedAnnotations;
     * pivot : Element[?] = lReferenceCS.pivot;
     * qualifiers : OrderedSet(String) = lReferenceCS.qualifiers;
     * referredKeys : OrderedSet(Property) = lReferenceCS.referredKeys;
     * includes : Boolean[1] = qualifiers->includes('volatile')
     *   ;
     * includes1 : Boolean[1] = qualifiers->includes('static')
     *   ;
     * includes2 : Boolean[1] = qualifiers->includes('readonly')
     *   ;
     * includes3 : Boolean[1] = qualifiers->includes('compososes')
     *   ;
     * includes4 : Boolean[1] = qualifiers->includes('unsettable')
     *   ;
     * includes5 : Boolean[1] = qualifiers->includes('derived')
     *   ;
     * includes6 : Boolean[1] = qualifiers->includes('transient')
     *   ;
     * _'\u00ABcollect\u00BB' : Sequence(Element) = ownedAnnotations->collect(_'1_' | _'1_'.pivot)
     *   ;
     * _'\u00ABexists\u00BB' : Boolean[?] = qualifiers->exists(x | x = '!resolve');
     * _'not' : Boolean[?] = not _'\u00ABexists\u00BB';
     *  |
     * aProperty.defaultValueString := default;
     * aProperty.isComposite := includes3;
     * aProperty.isDerived := includes5;
     * aProperty.isReadOnly := includes2;
     * aProperty.isResolveProxies := _'not';
     * aProperty.isStatic := includes1;
     * aProperty.isTransient := includes6;
     * aProperty.isUnsettable := includes4;
     * aProperty.isVolatile := includes;
     * aProperty.keys := referredKeys;
     * aProperty.name := name;
     * aProperty.ownedAnnotations := _'\u00ABcollect\u00BB';
     * }
     * 
     */
    protected boolean MAP_m_ReferenceCS_default_name_ownedAnnotations_pivot_(final /*@NonInvalid*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull ReferenceCS lReferenceCS) throws ReflectiveOperationException {
        try {
            // predicates and unrealized variables
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull IdResolver idResolver = executor.getIdResolver();
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class TYP_Property_0 = idResolver.getClass(CLSSid_Property, null);
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot = lReferenceCS.getPivot();
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Property aProperty = ClassUtil.nonNullState((Property)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, pivot, TYP_Property_0));
            final /*@Thrown*/ java.lang.@org.eclipse.jdt.annotation.Nullable String symbol_0 = lReferenceCS.getDefault();
            final /*@Thrown*/ java.lang.@org.eclipse.jdt.annotation.Nullable String name = lReferenceCS.getName();
            @SuppressWarnings("null")
            final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<AnnotationElementCS> ownedAnnotations = lReferenceCS.getOwnedAnnotations();
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot_0 = lReferenceCS.getPivot();
            @SuppressWarnings("null")
            final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<String> qualifiers = lReferenceCS.getQualifiers();
            @SuppressWarnings("null")
            final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<Property> referredKeys = lReferenceCS.getReferredKeys();
            final /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull OrderedSetValue BOXED_qualifiers = idResolver.createOrderedSetOfAll(ORD_PRIMid_String, qualifiers);
            final /*@Thrown*/ boolean includes = CollectionIncludesOperation.INSTANCE.evaluate(BOXED_qualifiers, STR_volatile).booleanValue();
            final /*@Thrown*/ boolean includes1 = CollectionIncludesOperation.INSTANCE.evaluate(BOXED_qualifiers, STR_static).booleanValue();
            final /*@Thrown*/ boolean includes2 = CollectionIncludesOperation.INSTANCE.evaluate(BOXED_qualifiers, STR_readonly).booleanValue();
            final /*@Thrown*/ boolean includes3 = CollectionIncludesOperation.INSTANCE.evaluate(BOXED_qualifiers, STR_compososes).booleanValue();
            final /*@Thrown*/ boolean includes4 = CollectionIncludesOperation.INSTANCE.evaluate(BOXED_qualifiers, STR_unsettable).booleanValue();
            final /*@Thrown*/ boolean includes5 = CollectionIncludesOperation.INSTANCE.evaluate(BOXED_qualifiers, STR_derived).booleanValue();
            final /*@Thrown*/ boolean includes6 = CollectionIncludesOperation.INSTANCE.evaluate(BOXED_qualifiers, STR_transient).booleanValue();
            final /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull OrderedSetValue BOXED_ownedAnnotations = idResolver.createOrderedSetOfAll(ORD_CLSSid_AnnotationElementCS, ownedAnnotations);
            /*@Thrown*/ SequenceValue.@org.eclipse.jdt.annotation.NonNull Accumulator accumulator = ValueUtil.createSequenceAccumulatorValue(SEQ_CLSSid_Element);
            @Nullable Iterator<Object> ITERATOR__1 = BOXED_ownedAnnotations.iterator();
            /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull SequenceValue _171_collect_187;
            while (true) {
                if (!ITERATOR__1.hasNext()) {
                    _171_collect_187 = accumulator;
                    break;
                }
                /*@NonInvalid*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.Nullable AnnotationElementCS _1 = (AnnotationElementCS)ITERATOR__1.next();
                /**
                 * _'1_'.pivot
                 */
                if (_1 == null) {
                    throw throwNull(lReferenceCS, "Null source for \'\'http://www.eclipse.org/ocl/2015/BaseCS\'::PivotableElementCS::pivot\'");
                }
                final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot_1 = _1.getPivot();
                //
                accumulator.add(pivot_1);
            }
            /*@Thrown*/ java.lang.@org.eclipse.jdt.annotation.Nullable Object accumulator_0 = ValueUtil.FALSE_VALUE;
            @Nullable Iterator<Object> ITERATOR_x = BOXED_qualifiers.iterator();
            /*@Thrown*/ boolean _171_exists_187;
            while (true) {
                if (!ITERATOR_x.hasNext()) {
                    if (accumulator_0 == ValueUtil.FALSE_VALUE) {
                        _171_exists_187 = ValueUtil.FALSE_VALUE;
                    }
                    else {
                        throw (InvalidValueException)accumulator_0;
                    }
                    break;
                }
                /*@NonInvalid*/ java.lang.@org.eclipse.jdt.annotation.Nullable String x = (String)ITERATOR_x.next();
                /**
                 * x = '!resolve'
                 */
                final /*@NonInvalid*/ boolean symbol_1 = STR__33_resolve.equals(x);
                //
                if (symbol_1 == ValueUtil.TRUE_VALUE) {					// Normal successful body evaluation result
                    _171_exists_187 = ValueUtil.TRUE_VALUE;
                    break;														// Stop immediately 
                }
                else if (symbol_1 == ValueUtil.FALSE_VALUE) {				// Normal unsuccessful body evaluation result
                    ;															// Carry on
                }
                else {															// Impossible badly typed result
                    accumulator_0 = new InvalidValueException(PivotMessages.NonBooleanBody, "exists");
                }
            }
            final /*@Thrown*/ java.lang.@org.eclipse.jdt.annotation.Nullable Boolean not = BooleanNotOperation.INSTANCE.evaluate(_171_exists_187);
            // property assignments
            aProperty.setDefaultValueString(symbol_0);
            aProperty.setIsComposite(includes3);
            aProperty.setIsDerived(includes5);
            aProperty.setIsReadOnly(includes2);
            if (not == null) {
                throw throwNull(lReferenceCS, "Null value");
            }
            aProperty.setIsResolveProxies(not);
            aProperty.setIsStatic(includes1);
            aProperty.setIsTransient(includes6);
            aProperty.setIsUnsettable(includes4);
            aProperty.setIsVolatile(includes);
            aProperty.getKeys().addAll(referredKeys);
            aProperty.setName(name);
            final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<Element> ECORE__171_collect_187 = ((IdResolver.IdResolverExtension)idResolver).ecoreValueOfAll(Element.class, _171_collect_187);
            aProperty.getOwnedAnnotations().addAll(ECORE__171_collect_187);
            boolean m_ReferenceCS_default_name_ownedAnnotations_pivot_ = ValueUtil.TRUE_VALUE;
            return m_ReferenceCS_default_name_ownedAnnotations_pivot_;
        } catch (Throwable e) {
            return handleExecutionFailure("MAP_m_ReferenceCS_default_name_ownedAnnotations_pivot_", e);
        }
    }
    
    /**
     * 
     * map m_ReferenceCS_ownedDefaultExpressions_ownedDefault in BaseCS2AS_qvtp_qvtcas {
     *   leftCS (lReferenceCS : basecs::ReferenceCS[?];
     *  |)
     * { |}
     * rightAS ( |)
     * { |}
     * where ( |)
     * {aProperty : Property[1] = lReferenceCS.pivot.oclAsType(Property)
     *   ;
     * ownedDefaultExpressions : OrderedSet(basecs::SpecificationCS) = lReferenceCS.ownedDefaultExpressions;
     * pivot : Element[?] = lReferenceCS.pivot;
     * notEmpty : Boolean[1] = ownedDefaultExpressions->notEmpty()
     *   ;
     * ownedExpression : LanguageExpression[?] = if notEmpty
     *   then
     *     ownedDefaultExpressions->collect(_'1_' |
     *       _'1_'.pivot.oclAsType(LanguageExpression))
     *     ->first()
     *   else null
     *   endif;
     *  |
     * aProperty.ownedExpression := ownedExpression;
     * }
     * 
     */
    protected boolean MAP_m_ReferenceCS_ownedDefaultExpressions_ownedDefault(final /*@NonInvalid*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull ReferenceCS lReferenceCS_0) throws ReflectiveOperationException {
        try {
            // predicates and unrealized variables
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull IdResolver idResolver = executor.getIdResolver();
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class TYP_Property_0 = idResolver.getClass(CLSSid_Property, null);
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot = lReferenceCS_0.getPivot();
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Property aProperty = ClassUtil.nonNullState((Property)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, pivot, TYP_Property_0));
            @SuppressWarnings("null")
            final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<SpecificationCS> ownedDefaultExpressions = lReferenceCS_0.getOwnedDefaultExpressions();
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot_0 = lReferenceCS_0.getPivot();
            final /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull OrderedSetValue BOXED_ownedDefaultExpressions = idResolver.createOrderedSetOfAll(ORD_CLSSid_SpecificationCS, ownedDefaultExpressions);
            final /*@Thrown*/ boolean notEmpty = CollectionNotEmptyOperation.INSTANCE.evaluate(BOXED_ownedDefaultExpressions).booleanValue();
            /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable LanguageExpression ownedExpression;
            if (notEmpty) {
                /*@Thrown*/ SequenceValue.@org.eclipse.jdt.annotation.NonNull Accumulator accumulator = ValueUtil.createSequenceAccumulatorValue(SEQ_CLSSid_LanguageExpression);
                @Nullable Iterator<Object> ITERATOR__1 = BOXED_ownedDefaultExpressions.iterator();
                /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull SequenceValue collect;
                while (true) {
                    if (!ITERATOR__1.hasNext()) {
                        collect = accumulator;
                        break;
                    }
                    /*@NonInvalid*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.Nullable SpecificationCS _1 = (SpecificationCS)ITERATOR__1.next();
                    /**
                     * _'1_'.pivot.oclAsType(LanguageExpression)
                     */
                    final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class TYP_LanguageExpression_0 = idResolver.getClass(CLSSid_LanguageExpression, null);
                    if (_1 == null) {
                        throw throwNull(lReferenceCS_0, "Null source for \'\'http://www.eclipse.org/ocl/2015/BaseCS\'::PivotableElementCS::pivot\'");
                    }
                    final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot_1 = _1.getPivot();
                    final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull LanguageExpression oclAsType = ClassUtil.nonNullState((LanguageExpression)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, pivot_1, TYP_LanguageExpression_0));
                    //
                    accumulator.add(oclAsType);
                }
                final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable LanguageExpression first = (LanguageExpression)OrderedCollectionFirstOperation.INSTANCE.evaluate(collect);
                ownedExpression = first;
            }
            else {
                ownedExpression = null;
            }
            // property assignments
            aProperty.setOwnedExpression(ownedExpression);
            boolean m_ReferenceCS_ownedDefaultExpressions_ownedDefault = ValueUtil.TRUE_VALUE;
            return m_ReferenceCS_ownedDefaultExpressions_ownedDefault;
        } catch (Throwable e) {
            return handleExecutionFailure("MAP_m_ReferenceCS_ownedDefaultExpressions_ownedDefault", e);
        }
    }
    
    /**
     * 
     * map m_ReferenceCS_ownedType_pivot in BaseCS2AS_qvtp_qvtcas {
     *   leftCS (lReferenceCS : basecs::ReferenceCS[?];
     *  |)
     * { |}
     * rightAS ( |)
     * { |}
     * where ( |)
     * {aProperty : Property[1] = lReferenceCS.pivot.oclAsType(Property)
     *   ;
     * ownedType : basecs::TypedRefCS[?] = lReferenceCS.ownedType;
     * pivot : Element[?] = lReferenceCS.pivot;
     * aClass : Class[1] = ownedType.pivot.oclAsType(Class);
     * pivot1 : Element[?] = ownedType.pivot;
     *  |
     * aProperty.type := aClass;
     * }
     * 
     */
    protected boolean MAP_m_ReferenceCS_ownedType_pivot(final /*@NonInvalid*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull ReferenceCS lReferenceCS_1) throws ReflectiveOperationException {
        try {
            // predicates and unrealized variables
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull IdResolver idResolver = executor.getIdResolver();
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class TYP_Property_0 = idResolver.getClass(CLSSid_Property, null);
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot = lReferenceCS_1.getPivot();
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Property aProperty = ClassUtil.nonNullState((Property)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, pivot, TYP_Property_0));
            final /*@Thrown*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.Nullable TypedRefCS ownedType = lReferenceCS_1.getOwnedType();
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot_0 = lReferenceCS_1.getPivot();
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class TYP_Class_0 = idResolver.getClass(CLSSid_Class, null);
            if (ownedType == null) {
                throw throwNull(lReferenceCS_1, "Null source for \'\'http://www.eclipse.org/ocl/2015/BaseCS\'::PivotableElementCS::pivot\'");
            }
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot_1 = ownedType.getPivot();
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class aClass = ClassUtil.nonNullState((Class)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, pivot_1, TYP_Class_0));
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot1 = ownedType.getPivot();
            // property assignments
            aProperty.setType(aClass);
            boolean m_ReferenceCS_ownedType_pivot = ValueUtil.TRUE_VALUE;
            return m_ReferenceCS_ownedType_pivot;
        } catch (Throwable e) {
            return handleExecutionFailure("MAP_m_ReferenceCS_ownedType_pivot", e);
        }
    }
    
    /**
     * 
     * map m_ReferenceCS_ownedType_pivot_referredOpposite in BaseCS2AS_qvtp_qvtcas {
     *   leftCS (lReferenceCS : basecs::ReferenceCS[?];
     *  |)
     * { |}
     * rightAS ( |)
     * { |}
     * where ( |)
     * {aProperty : Property[1] = lReferenceCS.pivot.oclAsType(Property);
     * pivot : Element[?] = lReferenceCS.pivot;
     * referredOpposite : Property[?] = lReferenceCS.referredOpposite;
     * _'<>' : Boolean[1] = referredOpposite <>
     *   null;
     * opposite : Property[?] = if _'<>'
     *   then referredOpposite
     *   else
     *     Property{
     *       name = aProperty.owningClass.name, isImplicit = true, isRequired = false, owningClass = lReferenceCS.ownedType.pivot.oclAsType(Class)
     *     }
     *   endif;
     *  |
     * aProperty.opposite := opposite;
     * }
     * 
     */
    protected boolean MAP_m_ReferenceCS_ownedType_pivot_referredOpposite(final /*@NonInvalid*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull ReferenceCS lReferenceCS_2) throws ReflectiveOperationException {
        try {
            // predicates and unrealized variables
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull IdResolver idResolver = executor.getIdResolver();
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class TYP_Property_0 = idResolver.getClass(CLSSid_Property, null);
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot = lReferenceCS_2.getPivot();
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Property aProperty = ClassUtil.nonNullState((Property)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, pivot, TYP_Property_0));
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot_0 = lReferenceCS_2.getPivot();
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Property referredOpposite = lReferenceCS_2.getReferredOpposite();
            final /*@Thrown*/ boolean ne = referredOpposite != null;
            /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Property opposite;
            if (ne) {
                opposite = referredOpposite;
            }
            else {
                final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Property CTORid_isImplicit = idResolver.getProperty(PROPid_isImplicit);
                final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Property CTORid_isRequired = idResolver.getProperty(PROPid_isRequired);
                final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Property CTORid_name = idResolver.getProperty(PROPid_name);
                final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Property CTORid_owningClass = idResolver.getProperty(PROPid_owningClass);
                final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class TYP_Class_0 = idResolver.getClass(CLSSid_Class, null);
                final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Property symbol_4 = (Property)TYP_Property_0.createInstance();
                final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Class owningClass = aProperty.getOwningClass();
                if (owningClass == null) {
                    throw throwNull(lReferenceCS_2, "Null source for \'NamedElement::name\'");
                }
                final /*@Thrown*/ java.lang.@org.eclipse.jdt.annotation.Nullable String name = owningClass.getName();
                CTORid_name.initValue(symbol_4, name);
                CTORid_isImplicit.initValue(symbol_4, ValueUtil.TRUE_VALUE);
                CTORid_isRequired.initValue(symbol_4, ValueUtil.FALSE_VALUE);
                final /*@Thrown*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.Nullable TypedRefCS ownedType = lReferenceCS_2.getOwnedType();
                if (ownedType == null) {
                    throw throwNull(lReferenceCS_2, "Null source for \'\'http://www.eclipse.org/ocl/2015/BaseCS\'::PivotableElementCS::pivot\'");
                }
                final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot_1 = ownedType.getPivot();
                final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class oclAsType = ClassUtil.nonNullState((Class)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, pivot_1, TYP_Class_0));
                CTORid_owningClass.initValue(symbol_4, oclAsType);
                opposite = symbol_4;
            }
            // property assignments
            aProperty.setOpposite(opposite);
            boolean m_ReferenceCS_ownedType_pivot_referredOpposite = ValueUtil.TRUE_VALUE;
            return m_ReferenceCS_ownedType_pivot_referredOpposite;
        } catch (Throwable e) {
            return handleExecutionFailure("MAP_m_ReferenceCS_ownedType_pivot_referredOpposite", e);
        }
    }
    
    /**
     * 
     * map m_ReferenceCS_pivot in BaseCS2AS_qvtp_qvtcas {leftCS (lReferenceCS : basecs::ReferenceCS[?];
     *  |)
     * { |}
     * rightAS ( |)
     * {realize rProperty : Property[1];
     *  |}
     * where ( |)
     * { |
     * lReferenceCS.pivot := rProperty;
     * }
     * 
     */
    protected boolean MAP_m_ReferenceCS_pivot(final /*@NonInvalid*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull ReferenceCS lReferenceCS_3) throws ReflectiveOperationException {
        try {
            // predicates and unrealized variables
            // creations
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Property rProperty = PivotFactory.eINSTANCE.createProperty();
            assert rProperty != null;
            models[1/*rightAS*/].add(rProperty);
            // property assignments
            lReferenceCS_3.setPivot(rProperty);
            boolean m_ReferenceCS_pivot = ValueUtil.TRUE_VALUE;
            return m_ReferenceCS_pivot;
        } catch (Throwable e) {
            return handleExecutionFailure("MAP_m_ReferenceCS_pivot", e);
        }
    }
    
    /**
     * 
     * map m_RootPackageCS_ownedImports_ownedPackages_pivot in BaseCS2AS_qvtp_qvtcas {
     *   leftCS (lRootPackageCS : basecs::RootPackageCS[?];
     *  |)
     * { |}
     * rightAS ( |)
     * { |}
     * where ( |)
     * {aModel : Model[1] = lRootPackageCS.pivot.oclAsType(Model)
     *   ;
     * ownedImports : OrderedSet(basecs::ImportCS) = lRootPackageCS.ownedImports;
     * ownedPackages : OrderedSet(basecs::PackageCS) = lRootPackageCS.ownedPackages;
     * pivot : Element[?] = lRootPackageCS.pivot;
     * _'\u00ABcollect\u00BB' : Sequence(Import) = ownedImports->collect(_'1_' |
     *     _'1_'.pivot.oclAsType(Import))
     *   ;
     * _'\u00ABcollect\u00BB1' : Sequence(Package) = ownedPackages->collect(_'1_' |
     *     _'1_'.pivot.oclAsType(Package));
     *  |
     * aModel.ownedImports := _'\u00ABcollect\u00BB';
     * aModel.ownedPackages := _'\u00ABcollect\u00BB1';
     * }
     * 
     */
    protected boolean MAP_m_RootPackageCS_ownedImports_ownedPackages_pivot(final /*@NonInvalid*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull RootPackageCS lRootPackageCS) throws ReflectiveOperationException {
        try {
            // predicates and unrealized variables
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull IdResolver idResolver = executor.getIdResolver();
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class TYP_Model_0 = idResolver.getClass(CLSSid_Model_0, null);
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot = lRootPackageCS.getPivot();
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Model aModel = ClassUtil.nonNullState((org.eclipse.ocl.pivot.Model)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, pivot, TYP_Model_0));
            @SuppressWarnings("null")
            final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<ImportCS> ownedImports = lRootPackageCS.getOwnedImports();
            @SuppressWarnings("null")
            final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<PackageCS> ownedPackages = lRootPackageCS.getOwnedPackages();
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot_0 = lRootPackageCS.getPivot();
            final /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull OrderedSetValue BOXED_ownedImports = idResolver.createOrderedSetOfAll(ORD_CLSSid_ImportCS, ownedImports);
            /*@Thrown*/ SequenceValue.@org.eclipse.jdt.annotation.NonNull Accumulator accumulator = ValueUtil.createSequenceAccumulatorValue(SEQ_CLSSid_Import);
            @Nullable Iterator<Object> ITERATOR__1 = BOXED_ownedImports.iterator();
            /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull SequenceValue _171_collect_187;
            while (true) {
                if (!ITERATOR__1.hasNext()) {
                    _171_collect_187 = accumulator;
                    break;
                }
                /*@NonInvalid*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.Nullable ImportCS _1 = (ImportCS)ITERATOR__1.next();
                /**
                 * _'1_'.pivot.oclAsType(Import)
                 */
                final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class TYP_Import_0 = idResolver.getClass(CLSSid_Import, null);
                if (_1 == null) {
                    throw throwNull(lRootPackageCS, "Null source for \'\'http://www.eclipse.org/ocl/2015/BaseCS\'::PivotableElementCS::pivot\'");
                }
                final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot_1 = _1.getPivot();
                final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Import oclAsType = ClassUtil.nonNullState((Import)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, pivot_1, TYP_Import_0));
                //
                accumulator.add(oclAsType);
            }
            final /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull OrderedSetValue BOXED_ownedPackages = idResolver.createOrderedSetOfAll(ORD_CLSSid_PackageCS, ownedPackages);
            /*@Thrown*/ SequenceValue.@org.eclipse.jdt.annotation.NonNull Accumulator accumulator_0 = ValueUtil.createSequenceAccumulatorValue(SEQ_CLSSid_Package);
            @Nullable Iterator<Object> ITERATOR__1_0 = BOXED_ownedPackages.iterator();
            /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull SequenceValue _171_collect_187_1;
            while (true) {
                if (!ITERATOR__1_0.hasNext()) {
                    _171_collect_187_1 = accumulator_0;
                    break;
                }
                /*@NonInvalid*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.Nullable PackageCS _1_0 = (PackageCS)ITERATOR__1_0.next();
                /**
                 * _'1_'.pivot.oclAsType(Package)
                 */
                final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class TYP_Package_0 = idResolver.getClass(CLSSid_Package, null);
                if (_1_0 == null) {
                    throw throwNull(lRootPackageCS, "Null source for \'\'http://www.eclipse.org/ocl/2015/BaseCS\'::PivotableElementCS::pivot\'");
                }
                final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot_2 = _1_0.getPivot();
                final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Package oclAsType_0 = ClassUtil.nonNullState((Package)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, pivot_2, TYP_Package_0));
                //
                accumulator_0.add(oclAsType_0);
            }
            // property assignments
            final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<Import> ECORE__171_collect_187 = ((IdResolver.IdResolverExtension)idResolver).ecoreValueOfAll(Import.class, _171_collect_187);
            aModel.getOwnedImports().addAll(ECORE__171_collect_187);
            final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<Package> ECORE__171_collect_187_1 = ((IdResolver.IdResolverExtension)idResolver).ecoreValueOfAll(Package.class, _171_collect_187_1);
            aModel.getOwnedPackages().addAll(ECORE__171_collect_187_1);
            boolean m_RootPackageCS_ownedImports_ownedPackages_pivot = ValueUtil.TRUE_VALUE;
            return m_RootPackageCS_ownedImports_ownedPackages_pivot;
        } catch (Throwable e) {
            return handleExecutionFailure("MAP_m_RootPackageCS_ownedImports_ownedPackages_pivot", e);
        }
    }
    
    /**
     * 
     * map m_RootPackageCS_pivot in BaseCS2AS_qvtp_qvtcas {leftCS (lRootPackageCS : basecs::RootPackageCS[?];
     *  |)
     * { |}
     * rightAS ( |)
     * {realize rModel : Model[1];
     *  |}
     * where ( |)
     * { |
     * lRootPackageCS.pivot := rModel;
     * }
     * 
     */
    protected boolean MAP_m_RootPackageCS_pivot(final /*@NonInvalid*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull RootPackageCS lRootPackageCS_0) throws ReflectiveOperationException {
        try {
            // predicates and unrealized variables
            // creations
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Model rModel = PivotFactory.eINSTANCE.createModel();
            assert rModel != null;
            models[1/*rightAS*/].add(rModel);
            // property assignments
            lRootPackageCS_0.setPivot(rModel);
            boolean m_RootPackageCS_pivot = ValueUtil.TRUE_VALUE;
            return m_RootPackageCS_pivot;
        } catch (Throwable e) {
            return handleExecutionFailure("MAP_m_RootPackageCS_pivot", e);
        }
    }
    
    /**
     * 
     * map m_SpecificationCS_exprString_pivot in BaseCS2AS_qvtp_qvtcas {
     *   leftCS (lSpecificationCS : basecs::SpecificationCS[?];
     *  |)
     * { |}
     * rightAS ( |)
     * { |}
     * where ( |)
     * {aExpressionInOCL : ExpressionInOCL[1] = lSpecificationCS.pivot.oclAsType(ExpressionInOCL);
     * exprString : String[?] = lSpecificationCS.exprString;
     * pivot : Element[?] = lSpecificationCS.pivot;
     *  |
     * aExpressionInOCL.body := exprString;
     * }
     * 
     */
    protected boolean MAP_m_SpecificationCS_exprString_pivot(final /*@NonInvalid*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull SpecificationCS lSpecificationCS) throws ReflectiveOperationException {
        try {
            // predicates and unrealized variables
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull IdResolver idResolver = executor.getIdResolver();
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class TYP_ExpressionInOCL_0 = idResolver.getClass(CLSSid_ExpressionInOCL, null);
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot = lSpecificationCS.getPivot();
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull ExpressionInOCL aExpressionInOCL = ClassUtil.nonNullState((ExpressionInOCL)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, pivot, TYP_ExpressionInOCL_0));
            final /*@Thrown*/ java.lang.@org.eclipse.jdt.annotation.Nullable String exprString = lSpecificationCS.getExprString();
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot_0 = lSpecificationCS.getPivot();
            // property assignments
            aExpressionInOCL.setBody(exprString);
            boolean m_SpecificationCS_exprString_pivot = ValueUtil.TRUE_VALUE;
            return m_SpecificationCS_exprString_pivot;
        } catch (Throwable e) {
            return handleExecutionFailure("MAP_m_SpecificationCS_exprString_pivot", e);
        }
    }
    
    /**
     * 
     * map m_SpecificationCS_pivot in BaseCS2AS_qvtp_qvtcas {leftCS (lSpecificationCS : basecs::SpecificationCS[?];
     *  |)
     * { |}
     * rightAS ( |)
     * {realize rExpressionInOCL : ExpressionInOCL[1];
     *  |}
     * where ( |)
     * { |
     * lSpecificationCS.pivot := rExpressionInOCL;
     * }
     * 
     */
    protected boolean MAP_m_SpecificationCS_pivot(final /*@NonInvalid*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull SpecificationCS lSpecificationCS_0) throws ReflectiveOperationException {
        try {
            // predicates and unrealized variables
            // creations
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable ExpressionInOCL rExpressionInOCL = PivotFactory.eINSTANCE.createExpressionInOCL();
            assert rExpressionInOCL != null;
            models[1/*rightAS*/].add(rExpressionInOCL);
            // property assignments
            lSpecificationCS_0.setPivot(rExpressionInOCL);
            boolean m_SpecificationCS_pivot = ValueUtil.TRUE_VALUE;
            return m_SpecificationCS_pivot;
        } catch (Throwable e) {
            return handleExecutionFailure("MAP_m_SpecificationCS_pivot", e);
        }
    }
    
    /**
     * 
     * map m_StructuredClassCS_instanceClassName_isAbstract_i in BaseCS2AS_qvtp_qvtcas {
     *   leftCS (lStructuredClassCS : basecs::StructuredClassCS[?];
     *  |)
     * { |}
     * rightAS ( |)
     * { |}
     * where ( |)
     * {aClass : Class[1] = lStructuredClassCS.pivot.oclAsType(Class);
     * instanceClassName : String[?] = lStructuredClassCS.instanceClassName;
     * isAbstract : Boolean[?] = lStructuredClassCS.isAbstract;
     * isInterface : Boolean[?] = lStructuredClassCS.isInterface;
     * name : String[?] = lStructuredClassCS.name;
     * ownedAnnotations : OrderedSet(basecs::AnnotationElementCS) = lStructuredClassCS.ownedAnnotations;
     * ownedConstraints : OrderedSet(basecs::ConstraintCS) = lStructuredClassCS.ownedConstraints;
     * ownedOperations : OrderedSet(basecs::OperationCS) = lStructuredClassCS.ownedOperations;
     * ownedProperties : OrderedSet(basecs::StructuralFeatureCS) = lStructuredClassCS.ownedProperties;
     * ownedSuperTypes : OrderedSet(basecs::TypedRefCS) = lStructuredClassCS.ownedSuperTypes;
     * pivot : Element[?] = lStructuredClassCS.pivot;
     * _'=' : Boolean[1] = ownedSuperTypes =
     *   null;
     * _'\u00ABcollect\u00BB' : Sequence(Element) = ownedAnnotations->collect(_'1_' | _'1_'.pivot)
     *   ;
     * _'\u00ABcollect\u00BB1' : Sequence(Operation) = ownedOperations->collect(_'1_' |
     *     _'1_'.pivot.oclAsType(Operation))
     *   ;
     * _'\u00ABcollect\u00BB2' : Sequence(Constraint) = ownedConstraints->collect(_'1_' |
     *     _'1_'.pivot.oclAsType(Constraint))
     *   ;
     * _'\u00ABcollect\u00BB3' : Sequence(Property) = ownedProperties->collect(_'1_' |
     *     _'1_'.pivot.oclAsType(Property))
     *   ;
     * _'if' : Collection(Class) = if _'='
     *   then Set{OclElement}
     *   else
     *     ownedSuperTypes->collect(_'1_' | _'1_'.pivot.oclAsType(Class))
     *   endif;
     *  |
     * aClass.instanceClassName := instanceClassName;
     * aClass.isAbstract := isAbstract;
     * aClass.isInterface := isInterface;
     * aClass.name := name;
     * aClass.ownedAnnotations := _'\u00ABcollect\u00BB';
     * aClass.ownedInvariants := _'\u00ABcollect\u00BB2';
     * aClass.ownedOperations := _'\u00ABcollect\u00BB1';
     * aClass.ownedProperties := _'\u00ABcollect\u00BB3';
     * aClass.superClasses := _'if';
     * }
     * 
     */
    protected boolean MAP_m_StructuredClassCS_instanceClassName_isAbstract_i(final /*@NonInvalid*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull StructuredClassCS lStructuredClassCS) throws ReflectiveOperationException {
        try {
            // predicates and unrealized variables
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull IdResolver idResolver = executor.getIdResolver();
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class TYP_Class_0 = idResolver.getClass(CLSSid_Class, null);
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot = lStructuredClassCS.getPivot();
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class aClass = ClassUtil.nonNullState((Class)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, pivot, TYP_Class_0));
            final /*@Thrown*/ java.lang.@org.eclipse.jdt.annotation.Nullable String instanceClassName = lStructuredClassCS.getInstanceClassName();
            final /*@Thrown*/ java.lang.@org.eclipse.jdt.annotation.Nullable Boolean isAbstract = lStructuredClassCS.isIsAbstract();
            final /*@Thrown*/ java.lang.@org.eclipse.jdt.annotation.Nullable Boolean isInterface = lStructuredClassCS.isIsInterface();
            final /*@Thrown*/ java.lang.@org.eclipse.jdt.annotation.Nullable String name = lStructuredClassCS.getName();
            @SuppressWarnings("null")
            final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<AnnotationElementCS> ownedAnnotations = lStructuredClassCS.getOwnedAnnotations();
            @SuppressWarnings("null")
            final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<ConstraintCS> ownedConstraints = lStructuredClassCS.getOwnedConstraints();
            @SuppressWarnings("null")
            final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<OperationCS> ownedOperations = lStructuredClassCS.getOwnedOperations();
            @SuppressWarnings("null")
            final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<StructuralFeatureCS> ownedProperties = lStructuredClassCS.getOwnedProperties();
            @SuppressWarnings("null")
            final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<TypedRefCS> ownedSuperTypes = lStructuredClassCS.getOwnedSuperTypes();
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot_0 = lStructuredClassCS.getPivot();
            final /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull OrderedSetValue BOXED_ownedAnnotations = idResolver.createOrderedSetOfAll(ORD_CLSSid_AnnotationElementCS, ownedAnnotations);
            /*@Thrown*/ SequenceValue.@org.eclipse.jdt.annotation.NonNull Accumulator accumulator = ValueUtil.createSequenceAccumulatorValue(SEQ_CLSSid_Element);
            @Nullable Iterator<Object> ITERATOR__1 = BOXED_ownedAnnotations.iterator();
            /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull SequenceValue _171_collect_187;
            while (true) {
                if (!ITERATOR__1.hasNext()) {
                    _171_collect_187 = accumulator;
                    break;
                }
                /*@NonInvalid*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.Nullable AnnotationElementCS _1 = (AnnotationElementCS)ITERATOR__1.next();
                /**
                 * _'1_'.pivot
                 */
                if (_1 == null) {
                    throw throwNull(lStructuredClassCS, "Null source for \'\'http://www.eclipse.org/ocl/2015/BaseCS\'::PivotableElementCS::pivot\'");
                }
                final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot_1 = _1.getPivot();
                //
                accumulator.add(pivot_1);
            }
            final /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull OrderedSetValue BOXED_ownedOperations = idResolver.createOrderedSetOfAll(ORD_CLSSid_OperationCS, ownedOperations);
            /*@Thrown*/ SequenceValue.@org.eclipse.jdt.annotation.NonNull Accumulator accumulator_0 = ValueUtil.createSequenceAccumulatorValue(SEQ_CLSSid_Operation);
            @Nullable Iterator<Object> ITERATOR__1_0 = BOXED_ownedOperations.iterator();
            /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull SequenceValue _171_collect_187_1;
            while (true) {
                if (!ITERATOR__1_0.hasNext()) {
                    _171_collect_187_1 = accumulator_0;
                    break;
                }
                /*@NonInvalid*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.Nullable OperationCS _1_0 = (OperationCS)ITERATOR__1_0.next();
                /**
                 * _'1_'.pivot.oclAsType(Operation)
                 */
                final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class TYP_Operation_0 = idResolver.getClass(CLSSid_Operation, null);
                if (_1_0 == null) {
                    throw throwNull(lStructuredClassCS, "Null source for \'\'http://www.eclipse.org/ocl/2015/BaseCS\'::PivotableElementCS::pivot\'");
                }
                final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot_2 = _1_0.getPivot();
                final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Operation oclAsType = ClassUtil.nonNullState((Operation)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, pivot_2, TYP_Operation_0));
                //
                accumulator_0.add(oclAsType);
            }
            final /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull OrderedSetValue BOXED_ownedConstraints = idResolver.createOrderedSetOfAll(ORD_CLSSid_ConstraintCS, ownedConstraints);
            /*@Thrown*/ SequenceValue.@org.eclipse.jdt.annotation.NonNull Accumulator accumulator_1 = ValueUtil.createSequenceAccumulatorValue(SEQ_CLSSid_Constraint);
            @Nullable Iterator<Object> ITERATOR__1_1 = BOXED_ownedConstraints.iterator();
            /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull SequenceValue _171_collect_187_2;
            while (true) {
                if (!ITERATOR__1_1.hasNext()) {
                    _171_collect_187_2 = accumulator_1;
                    break;
                }
                /*@NonInvalid*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.Nullable ConstraintCS _1_1 = (ConstraintCS)ITERATOR__1_1.next();
                /**
                 * _'1_'.pivot.oclAsType(Constraint)
                 */
                final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class TYP_Constraint_0 = idResolver.getClass(CLSSid_Constraint, null);
                if (_1_1 == null) {
                    throw throwNull(lStructuredClassCS, "Null source for \'\'http://www.eclipse.org/ocl/2015/BaseCS\'::PivotableElementCS::pivot\'");
                }
                final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot_3 = _1_1.getPivot();
                final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Constraint oclAsType_0 = ClassUtil.nonNullState((Constraint)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, pivot_3, TYP_Constraint_0));
                //
                accumulator_1.add(oclAsType_0);
            }
            final /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull OrderedSetValue BOXED_ownedProperties = idResolver.createOrderedSetOfAll(ORD_CLSSid_StructuralFeatureCS, ownedProperties);
            /*@Thrown*/ SequenceValue.@org.eclipse.jdt.annotation.NonNull Accumulator accumulator_2 = ValueUtil.createSequenceAccumulatorValue(SEQ_CLSSid_Property);
            @Nullable Iterator<Object> ITERATOR__1_2 = BOXED_ownedProperties.iterator();
            /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull SequenceValue _171_collect_187_3;
            while (true) {
                if (!ITERATOR__1_2.hasNext()) {
                    _171_collect_187_3 = accumulator_2;
                    break;
                }
                /*@NonInvalid*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.Nullable StructuralFeatureCS _1_2 = (StructuralFeatureCS)ITERATOR__1_2.next();
                /**
                 * _'1_'.pivot.oclAsType(Property)
                 */
                final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class TYP_Property_0 = idResolver.getClass(CLSSid_Property, null);
                if (_1_2 == null) {
                    throw throwNull(lStructuredClassCS, "Null source for \'\'http://www.eclipse.org/ocl/2015/BaseCS\'::PivotableElementCS::pivot\'");
                }
                final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot_4 = _1_2.getPivot();
                final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Property oclAsType_1 = ClassUtil.nonNullState((Property)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, pivot_4, TYP_Property_0));
                //
                accumulator_2.add(oclAsType_1);
            }
            final /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull OrderedSetValue BOXED_ownedSuperTypes = idResolver.createOrderedSetOfAll(ORD_CLSSid_TypedRefCS, ownedSuperTypes);
            /*@Thrown*/ SequenceValue.@org.eclipse.jdt.annotation.NonNull Accumulator accumulator_3 = ValueUtil.createSequenceAccumulatorValue(SEQ_CLSSid_Class);
            @Nullable Iterator<Object> ITERATOR__1_3 = BOXED_ownedSuperTypes.iterator();
            /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull SequenceValue collect;
            while (true) {
                if (!ITERATOR__1_3.hasNext()) {
                    collect = accumulator_3;
                    break;
                }
                /*@NonInvalid*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.Nullable TypedRefCS _1_3 = (TypedRefCS)ITERATOR__1_3.next();
                /**
                 * _'1_'.pivot.oclAsType(Class)
                 */
                if (_1_3 == null) {
                    throw throwNull(lStructuredClassCS, "Null source for \'\'http://www.eclipse.org/ocl/2015/BaseCS\'::PivotableElementCS::pivot\'");
                }
                final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot_5 = _1_3.getPivot();
                final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class oclAsType_2 = ClassUtil.nonNullState((Class)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, pivot_5, TYP_Class_0));
                //
                accumulator_3.add(oclAsType_2);
            }
            // property assignments
            aClass.setInstanceClassName(instanceClassName);
            if (isAbstract == null) {
                throw throwNull(lStructuredClassCS, "Null value");
            }
            aClass.setIsAbstract(isAbstract);
            if (isInterface == null) {
                throw throwNull(lStructuredClassCS, "Null value");
            }
            aClass.setIsInterface(isInterface);
            aClass.setName(name);
            final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<Element> ECORE__171_collect_187 = ((IdResolver.IdResolverExtension)idResolver).ecoreValueOfAll(Element.class, _171_collect_187);
            aClass.getOwnedAnnotations().addAll(ECORE__171_collect_187);
            final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<Constraint> ECORE__171_collect_187_2 = ((IdResolver.IdResolverExtension)idResolver).ecoreValueOfAll(Constraint.class, _171_collect_187_2);
            aClass.getOwnedInvariants().addAll(ECORE__171_collect_187_2);
            final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<Operation> ECORE__171_collect_187_1 = ((IdResolver.IdResolverExtension)idResolver).ecoreValueOfAll(Operation.class, _171_collect_187_1);
            aClass.getOwnedOperations().addAll(ECORE__171_collect_187_1);
            final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<Property> ECORE__171_collect_187_3 = ((IdResolver.IdResolverExtension)idResolver).ecoreValueOfAll(Property.class, _171_collect_187_3);
            aClass.getOwnedProperties().addAll(ECORE__171_collect_187_3);
            final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<Class> ECORE_collect = ((IdResolver.IdResolverExtension)idResolver).ecoreValueOfAll(Class.class, collect);
            aClass.getSuperClasses().addAll(ECORE_collect);
            boolean m_StructuredClassCS_instanceClassName_isAbstract_i = ValueUtil.TRUE_VALUE;
            return m_StructuredClassCS_instanceClassName_isAbstract_i;
        } catch (Throwable e) {
            return handleExecutionFailure("MAP_m_StructuredClassCS_instanceClassName_isAbstract_i", e);
        }
    }
    
    /**
     * 
     * map m_StructuredClassCS_ownedSignature_pivot in BaseCS2AS_qvtp_qvtcas {
     *   leftCS (lStructuredClassCS : basecs::StructuredClassCS[?];
     *  |)
     * { |}
     * rightAS ( |)
     * { |}
     * where ( |)
     * {aClass : Class[1] = lStructuredClassCS.pivot.oclAsType(Class)
     *   ;
     * ownedSignature : basecs::TemplateSignatureCS[?] = lStructuredClassCS.ownedSignature;
     * pivot : Element[?] = lStructuredClassCS.pivot;
     * aTemplateSignature : TemplateSignature[1] = ownedSignature.pivot.oclAsType(TemplateSignature);
     * pivot1 : Element[?] = ownedSignature.pivot;
     *  |
     * aClass.ownedSignature := aTemplateSignature;
     * }
     * 
     */
    protected boolean MAP_m_StructuredClassCS_ownedSignature_pivot(final /*@NonInvalid*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull StructuredClassCS lStructuredClassCS_0) throws ReflectiveOperationException {
        try {
            // predicates and unrealized variables
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull IdResolver idResolver = executor.getIdResolver();
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class TYP_Class_0 = idResolver.getClass(CLSSid_Class, null);
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot = lStructuredClassCS_0.getPivot();
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class aClass = ClassUtil.nonNullState((Class)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, pivot, TYP_Class_0));
            final /*@Thrown*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.Nullable TemplateSignatureCS ownedSignature = lStructuredClassCS_0.getOwnedSignature();
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot_0 = lStructuredClassCS_0.getPivot();
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class TYP_TemplateSignature_0 = idResolver.getClass(CLSSid_TemplateSignature, null);
            if (ownedSignature == null) {
                throw throwNull(lStructuredClassCS_0, "Null source for \'\'http://www.eclipse.org/ocl/2015/BaseCS\'::PivotableElementCS::pivot\'");
            }
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot_1 = ownedSignature.getPivot();
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull TemplateSignature aTemplateSignature = ClassUtil.nonNullState((TemplateSignature)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, pivot_1, TYP_TemplateSignature_0));
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot1 = ownedSignature.getPivot();
            // property assignments
            aClass.setOwnedSignature(aTemplateSignature);
            objectManager.assigned(aClass, PivotPackage.Literals.TEMPLATEABLE_ELEMENT__OWNED_SIGNATURE, aTemplateSignature, null);
            boolean m_StructuredClassCS_ownedSignature_pivot = ValueUtil.TRUE_VALUE;
            return m_StructuredClassCS_ownedSignature_pivot;
        } catch (Throwable e) {
            return handleExecutionFailure("MAP_m_StructuredClassCS_ownedSignature_pivot", e);
        }
    }
    
    /**
     * 
     * map m_StructuredClassCS_pivot in BaseCS2AS_qvtp_qvtcas {leftCS (lStructuredClassCS : basecs::StructuredClassCS[?];
     *  |)
     * { |}
     * rightAS ( |)
     * {realize rClass : Class[1];
     *  |}
     * where ( |)
     * { |
     * lStructuredClassCS.pivot := rClass;
     * }
     * 
     */
    protected boolean MAP_m_StructuredClassCS_pivot(final /*@NonInvalid*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull StructuredClassCS lStructuredClassCS_1) throws ReflectiveOperationException {
        try {
            // predicates and unrealized variables
            // creations
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Class rClass = PivotFactory.eINSTANCE.createClass();
            assert rClass != null;
            models[1/*rightAS*/].add(rClass);
            // property assignments
            lStructuredClassCS_1.setPivot(rClass);
            boolean m_StructuredClassCS_pivot = ValueUtil.TRUE_VALUE;
            return m_StructuredClassCS_pivot;
        } catch (Throwable e) {
            return handleExecutionFailure("MAP_m_StructuredClassCS_pivot", e);
        }
    }
    
    /**
     * 
     * map m_TemplateBindingCS_ownedSubstitutions_pivot in BaseCS2AS_qvtp_qvtcas {
     *   leftCS (lTemplateBindingCS : basecs::TemplateBindingCS[?];
     *  |)
     * { |}
     * rightAS ( |)
     * { |}
     * where ( |)
     * {aTemplateBinding : TemplateBinding[1] = lTemplateBindingCS.pivot.oclAsType(TemplateBinding)
     *   ;
     * ownedSubstitutions : OrderedSet(basecs::TemplateParameterSubstitutionCS) = lTemplateBindingCS.ownedSubstitutions;
     * pivot : Element[?] = lTemplateBindingCS.pivot;
     * _'\u00ABcollect\u00BB' : Sequence(TemplateParameterSubstitution) = ownedSubstitutions->collect(_'1_' |
     *     _'1_'.pivot.oclAsType(TemplateParameterSubstitution));
     *  |
     * aTemplateBinding.ownedSubstitutions := _'\u00ABcollect\u00BB';
     * }
     * 
     */
    protected boolean MAP_m_TemplateBindingCS_ownedSubstitutions_pivot(final /*@NonInvalid*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull TemplateBindingCS lTemplateBindingCS) throws ReflectiveOperationException {
        try {
            // predicates and unrealized variables
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull IdResolver idResolver = executor.getIdResolver();
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class TYP_TemplateBinding_0 = idResolver.getClass(CLSSid_TemplateBinding, null);
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot = lTemplateBindingCS.getPivot();
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull TemplateBinding aTemplateBinding = ClassUtil.nonNullState((TemplateBinding)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, pivot, TYP_TemplateBinding_0));
            @SuppressWarnings("null")
            final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<TemplateParameterSubstitutionCS> ownedSubstitutions = lTemplateBindingCS.getOwnedSubstitutions();
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot_0 = lTemplateBindingCS.getPivot();
            final /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull OrderedSetValue BOXED_ownedSubstitutions = idResolver.createOrderedSetOfAll(ORD_CLSSid_TemplateParameterSubstitutionCS, ownedSubstitutions);
            /*@Thrown*/ SequenceValue.@org.eclipse.jdt.annotation.NonNull Accumulator accumulator = ValueUtil.createSequenceAccumulatorValue(SEQ_CLSSid_TemplateParameterSubstitution);
            @Nullable Iterator<Object> ITERATOR__1 = BOXED_ownedSubstitutions.iterator();
            /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull SequenceValue _171_collect_187;
            while (true) {
                if (!ITERATOR__1.hasNext()) {
                    _171_collect_187 = accumulator;
                    break;
                }
                /*@NonInvalid*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.Nullable TemplateParameterSubstitutionCS _1 = (TemplateParameterSubstitutionCS)ITERATOR__1.next();
                /**
                 * _'1_'.pivot.oclAsType(TemplateParameterSubstitution)
                 */
                final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class TYP_TemplateParameterSubstitution_0 = idResolver.getClass(CLSSid_TemplateParameterSubstitution, null);
                if (_1 == null) {
                    throw throwNull(lTemplateBindingCS, "Null source for \'\'http://www.eclipse.org/ocl/2015/BaseCS\'::PivotableElementCS::pivot\'");
                }
                final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot_1 = _1.getPivot();
                final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull TemplateParameterSubstitution oclAsType = ClassUtil.nonNullState((TemplateParameterSubstitution)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, pivot_1, TYP_TemplateParameterSubstitution_0));
                //
                accumulator.add(oclAsType);
            }
            // property assignments
            final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<TemplateParameterSubstitution> ECORE__171_collect_187 = ((IdResolver.IdResolverExtension)idResolver).ecoreValueOfAll(TemplateParameterSubstitution.class, _171_collect_187);
            aTemplateBinding.getOwnedSubstitutions().addAll(ECORE__171_collect_187);
            boolean m_TemplateBindingCS_ownedSubstitutions_pivot = ValueUtil.TRUE_VALUE;
            return m_TemplateBindingCS_ownedSubstitutions_pivot;
        } catch (Throwable e) {
            return handleExecutionFailure("MAP_m_TemplateBindingCS_ownedSubstitutions_pivot", e);
        }
    }
    
    /**
     * 
     * map m_TemplateBindingCS_pivot in BaseCS2AS_qvtp_qvtcas {leftCS (lTemplateBindingCS : basecs::TemplateBindingCS[?];
     *  |)
     * { |}
     * rightAS ( |)
     * {realize rTemplateBinding : TemplateBinding[1];
     *  |}
     * where ( |)
     * { |
     * lTemplateBindingCS.pivot := rTemplateBinding;
     * }
     * 
     */
    protected boolean MAP_m_TemplateBindingCS_pivot(final /*@NonInvalid*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull TemplateBindingCS lTemplateBindingCS_0) throws ReflectiveOperationException {
        try {
            // predicates and unrealized variables
            // creations
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable TemplateBinding rTemplateBinding = PivotFactory.eINSTANCE.createTemplateBinding();
            assert rTemplateBinding != null;
            models[1/*rightAS*/].add(rTemplateBinding);
            // property assignments
            lTemplateBindingCS_0.setPivot(rTemplateBinding);
            boolean m_TemplateBindingCS_pivot = ValueUtil.TRUE_VALUE;
            return m_TemplateBindingCS_pivot;
        } catch (Throwable e) {
            return handleExecutionFailure("MAP_m_TemplateBindingCS_pivot", e);
        }
    }
    
    /**
     * 
     * map m_TemplateParameterSubstitutionCS_ownedActualParam in BaseCS2AS_qvtp_qvtcas {
     *   leftCS (lTemplateParameterSubstitutionCS : basecs::TemplateParameterSubstitutionCS[?];
     *  |)
     * { |}
     * rightAS ( |)
     * { |}
     * where ( |)
     * {aTemplateParameterSubstitution : TemplateParameterSubstitution[1] = lTemplateParameterSubstitutionCS.pivot.oclAsType(TemplateParameterSubstitution)
     *   ;
     * ownedActualParameter : basecs::TypeRefCS[?] = lTemplateParameterSubstitutionCS.ownedActualParameter;
     * pivot : Element[?] = lTemplateParameterSubstitutionCS.pivot;
     * aClass : Class[1] = ownedActualParameter.pivot.oclAsType(Class);
     * pivot1 : Element[?] = ownedActualParameter.pivot;
     *  |
     * aTemplateParameterSubstitution.actual := aClass;
     * }
     * 
     */
    protected boolean MAP_m_TemplateParameterSubstitutionCS_ownedActualParam(final /*@NonInvalid*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull TemplateParameterSubstitutionCS lTemplateParameterSubstitutionCS) throws ReflectiveOperationException {
        try {
            // predicates and unrealized variables
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull IdResolver idResolver = executor.getIdResolver();
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class TYP_TemplateParameterSubstitution_0 = idResolver.getClass(CLSSid_TemplateParameterSubstitution, null);
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot = lTemplateParameterSubstitutionCS.getPivot();
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull TemplateParameterSubstitution aTemplateParameterSubstitution = ClassUtil.nonNullState((TemplateParameterSubstitution)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, pivot, TYP_TemplateParameterSubstitution_0));
            final /*@Thrown*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.Nullable TypeRefCS ownedActualParameter = lTemplateParameterSubstitutionCS.getOwnedActualParameter();
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot_0 = lTemplateParameterSubstitutionCS.getPivot();
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class TYP_Class_0 = idResolver.getClass(CLSSid_Class, null);
            if (ownedActualParameter == null) {
                throw throwNull(lTemplateParameterSubstitutionCS, "Null source for \'\'http://www.eclipse.org/ocl/2015/BaseCS\'::PivotableElementCS::pivot\'");
            }
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot_1 = ownedActualParameter.getPivot();
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class aClass = ClassUtil.nonNullState((Class)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, pivot_1, TYP_Class_0));
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot1 = ownedActualParameter.getPivot();
            // property assignments
            aTemplateParameterSubstitution.setActual(aClass);
            boolean m_TemplateParameterSubstitutionCS_ownedActualParam = ValueUtil.TRUE_VALUE;
            return m_TemplateParameterSubstitutionCS_ownedActualParam;
        } catch (Throwable e) {
            return handleExecutionFailure("MAP_m_TemplateParameterSubstitutionCS_ownedActualParam", e);
        }
    }
    
    /**
     * 
     * map m_TemplateParameterSubstitutionCS_owningBinding_pi in BaseCS2AS_qvtp_qvtcas {
     *   leftCS (lTemplateParameterSubstitutionCS : basecs::TemplateParameterSubstitutionCS[?];
     *  |)
     * { |}
     * rightAS ( |)
     * { |}
     * where ( |)
     * {aTemplateParameterSubstitution : TemplateParameterSubstitution[1] = lTemplateParameterSubstitutionCS.pivot.oclAsType(TemplateParameterSubstitution)
     *   ;
     * owningBinding : basecs::TemplateBindingCS[?] = lTemplateParameterSubstitutionCS.owningBinding;
     * pivot : Element[?] = lTemplateParameterSubstitutionCS.pivot;
     * ownedSubstitutions : OrderedSet(basecs::TemplateParameterSubstitutionCS) = owningBinding.ownedSubstitutions;
     * owningElement : basecs::TypedTypeRefCS[?] = owningBinding.owningElement;
     * aClass : Class[1] = owningElement.pivot.oclAsType(Class)
     *   ;
     * indexOf : Integer[1] = ownedSubstitutions->indexOf(lTemplateParameterSubstitutionCS)
     *   ;
     * pivot1 : Element[?] = owningElement.pivot;
     * ownedSignature : TemplateSignature[?] = aClass.ownedSignature;
     * ownedParameters : OrderedSet(TemplateParameter) = ownedSignature.ownedParameters;
     * formal : TemplateParameter[?] = ownedParameters->at(indexOf);
     *  |
     * aTemplateParameterSubstitution.formal := formal;
     * }
     * 
     */
    protected boolean MAP_m_TemplateParameterSubstitutionCS_owningBinding_pi(final /*@NonInvalid*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull TemplateParameterSubstitutionCS lTemplateParameterSubstitutionCS_0) throws ReflectiveOperationException {
        try {
            // predicates and unrealized variables
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull IdResolver idResolver = executor.getIdResolver();
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class TYP_TemplateParameterSubstitution_0 = idResolver.getClass(CLSSid_TemplateParameterSubstitution, null);
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot = lTemplateParameterSubstitutionCS_0.getPivot();
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull TemplateParameterSubstitution aTemplateParameterSubstitution = ClassUtil.nonNullState((TemplateParameterSubstitution)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, pivot, TYP_TemplateParameterSubstitution_0));
            final /*@Thrown*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.Nullable TemplateBindingCS owningBinding = lTemplateParameterSubstitutionCS_0.getOwningBinding();
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot_0 = lTemplateParameterSubstitutionCS_0.getPivot();
            if (owningBinding == null) {
                throw throwNull(lTemplateParameterSubstitutionCS_0, "Null source for \'\'http://www.eclipse.org/ocl/2015/BaseCS\'::TemplateBindingCS::ownedSubstitutions\'");
            }
            @SuppressWarnings("null")
            final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<TemplateParameterSubstitutionCS> ownedSubstitutions = owningBinding.getOwnedSubstitutions();
            final /*@Thrown*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.Nullable TypedTypeRefCS owningElement = owningBinding.getOwningElement();
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class TYP_Class_0 = idResolver.getClass(CLSSid_Class, null);
            if (owningElement == null) {
                throw throwNull(lTemplateParameterSubstitutionCS_0, "Null source for \'\'http://www.eclipse.org/ocl/2015/BaseCS\'::PivotableElementCS::pivot\'");
            }
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot_1 = owningElement.getPivot();
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class aClass = ClassUtil.nonNullState((Class)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, pivot_1, TYP_Class_0));
            final /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull OrderedSetValue BOXED_ownedSubstitutions = idResolver.createOrderedSetOfAll(ORD_CLSSid_TemplateParameterSubstitutionCS, ownedSubstitutions);
            final /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull IntegerValue indexOf = OrderedCollectionIndexOfOperation.INSTANCE.evaluate(BOXED_ownedSubstitutions, lTemplateParameterSubstitutionCS_0);
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot1 = owningElement.getPivot();
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable TemplateSignature ownedSignature = aClass.getOwnedSignature();
            if (ownedSignature == null) {
                throw throwNull(lTemplateParameterSubstitutionCS_0, "Null source for \'TemplateSignature::ownedParameters\'");
            }
            final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<TemplateParameter> ownedParameters = ownedSignature.getOwnedParameters();
            final /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull OrderedSetValue BOXED_ownedParameters = idResolver.createOrderedSetOfAll(ORD_CLSSid_TemplateParameter, ownedParameters);
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable TemplateParameter formal = (TemplateParameter)OrderedCollectionAtOperation.INSTANCE.evaluate(BOXED_ownedParameters, indexOf);
            // property assignments
            if (formal == null) {
                throw throwNull(lTemplateParameterSubstitutionCS_0, "Null value");
            }
            aTemplateParameterSubstitution.setFormal(formal);
            boolean m_TemplateParameterSubstitutionCS_owningBinding_pi = ValueUtil.TRUE_VALUE;
            return m_TemplateParameterSubstitutionCS_owningBinding_pi;
        } catch (Throwable e) {
            return handleExecutionFailure("MAP_m_TemplateParameterSubstitutionCS_owningBinding_pi", e);
        }
    }
    
    /**
     * 
     * map m_TemplateParameterSubstitutionCS_pivot in BaseCS2AS_qvtp_qvtcas {leftCS (lTemplateParameterSubstitutionCS : basecs::TemplateParameterSubstitutionCS[?];
     *  |)
     * { |}
     * rightAS ( |)
     * {realize rTemplateParameterSubstitution : TemplateParameterSubstitution[1];
     *  |}
     * where ( |)
     * { |
     * lTemplateParameterSubstitutionCS.pivot := rTemplateParameterSubstitution;
     * }
     * 
     */
    protected boolean MAP_m_TemplateParameterSubstitutionCS_pivot(final /*@NonInvalid*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull TemplateParameterSubstitutionCS lTemplateParameterSubstitutionCS_1) throws ReflectiveOperationException {
        try {
            // predicates and unrealized variables
            // creations
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable TemplateParameterSubstitution rTemplateParameterSubstitution = PivotFactory.eINSTANCE.createTemplateParameterSubstitution();
            assert rTemplateParameterSubstitution != null;
            models[1/*rightAS*/].add(rTemplateParameterSubstitution);
            // property assignments
            lTemplateParameterSubstitutionCS_1.setPivot(rTemplateParameterSubstitution);
            boolean m_TemplateParameterSubstitutionCS_pivot = ValueUtil.TRUE_VALUE;
            return m_TemplateParameterSubstitutionCS_pivot;
        } catch (Throwable e) {
            return handleExecutionFailure("MAP_m_TemplateParameterSubstitutionCS_pivot", e);
        }
    }
    
    /**
     * 
     * map m_TemplateSignatureCS_pivot in BaseCS2AS_qvtp_qvtcas {leftCS (lTemplateSignatureCS : basecs::TemplateSignatureCS[?];
     *  |)
     * { |}
     * rightAS ( |)
     * {realize rTemplateSignature : TemplateSignature[1];
     *  |}
     * where ( |)
     * { |
     * lTemplateSignatureCS.pivot := rTemplateSignature;
     * }
     * 
     */
    protected boolean MAP_m_TemplateSignatureCS_pivot(final /*@NonInvalid*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull TemplateSignatureCS lTemplateSignatureCS) throws ReflectiveOperationException {
        try {
            // predicates and unrealized variables
            // creations
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable TemplateSignature rTemplateSignature = PivotFactory.eINSTANCE.createTemplateSignature();
            assert rTemplateSignature != null;
            models[1/*rightAS*/].add(rTemplateSignature);
            // property assignments
            lTemplateSignatureCS.setPivot(rTemplateSignature);
            boolean m_TemplateSignatureCS_pivot = ValueUtil.TRUE_VALUE;
            return m_TemplateSignatureCS_pivot;
        } catch (Throwable e) {
            return handleExecutionFailure("MAP_m_TemplateSignatureCS_pivot", e);
        }
    }
    
    /**
     * 
     * map m_TuplePartCS_name_pivot in BaseCS2AS_qvtp_qvtcas {
     *   leftCS (lTuplePartCS : basecs::TuplePartCS[?];
     *  |)
     * { |}
     * rightAS ( |)
     * { |}
     * where ( |)
     * {aProperty : Property[1] = lTuplePartCS.pivot.oclAsType(Property);
     * name : String[?] = lTuplePartCS.name;
     * pivot : Element[?] = lTuplePartCS.pivot;
     *  |
     * aProperty.name := name;
     * }
     * 
     */
    protected boolean MAP_m_TuplePartCS_name_pivot(final /*@NonInvalid*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull TuplePartCS lTuplePartCS) throws ReflectiveOperationException {
        try {
            // predicates and unrealized variables
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull IdResolver idResolver = executor.getIdResolver();
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class TYP_Property_0 = idResolver.getClass(CLSSid_Property, null);
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot = lTuplePartCS.getPivot();
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Property aProperty = ClassUtil.nonNullState((Property)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, pivot, TYP_Property_0));
            final /*@Thrown*/ java.lang.@org.eclipse.jdt.annotation.Nullable String name = lTuplePartCS.getName();
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot_0 = lTuplePartCS.getPivot();
            // property assignments
            aProperty.setName(name);
            boolean m_TuplePartCS_name_pivot = ValueUtil.TRUE_VALUE;
            return m_TuplePartCS_name_pivot;
        } catch (Throwable e) {
            return handleExecutionFailure("MAP_m_TuplePartCS_name_pivot", e);
        }
    }
    
    /**
     * 
     * map m_TuplePartCS_ownedType_pivot in BaseCS2AS_qvtp_qvtcas {
     *   leftCS (lTuplePartCS : basecs::TuplePartCS[?];
     *  |)
     * { |}
     * rightAS ( |)
     * { |}
     * where ( |)
     * {aProperty : Property[1] = lTuplePartCS.pivot.oclAsType(Property)
     *   ;
     * ownedType : basecs::TypedRefCS[?] = lTuplePartCS.ownedType;
     * pivot : Element[?] = lTuplePartCS.pivot;
     * aClass : Class[1] = ownedType.pivot.oclAsType(Class);
     * pivot1 : Element[?] = ownedType.pivot;
     *  |
     * aProperty.type := aClass;
     * }
     * 
     */
    protected boolean MAP_m_TuplePartCS_ownedType_pivot(final /*@NonInvalid*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull TuplePartCS lTuplePartCS_0) throws ReflectiveOperationException {
        try {
            // predicates and unrealized variables
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull IdResolver idResolver = executor.getIdResolver();
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class TYP_Property_0 = idResolver.getClass(CLSSid_Property, null);
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot = lTuplePartCS_0.getPivot();
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Property aProperty = ClassUtil.nonNullState((Property)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, pivot, TYP_Property_0));
            final /*@Thrown*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.Nullable TypedRefCS ownedType = lTuplePartCS_0.getOwnedType();
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot_0 = lTuplePartCS_0.getPivot();
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class TYP_Class_0 = idResolver.getClass(CLSSid_Class, null);
            if (ownedType == null) {
                throw throwNull(lTuplePartCS_0, "Null source for \'\'http://www.eclipse.org/ocl/2015/BaseCS\'::PivotableElementCS::pivot\'");
            }
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot_1 = ownedType.getPivot();
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class aClass = ClassUtil.nonNullState((Class)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, pivot_1, TYP_Class_0));
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot1 = ownedType.getPivot();
            // property assignments
            aProperty.setType(aClass);
            boolean m_TuplePartCS_ownedType_pivot = ValueUtil.TRUE_VALUE;
            return m_TuplePartCS_ownedType_pivot;
        } catch (Throwable e) {
            return handleExecutionFailure("MAP_m_TuplePartCS_ownedType_pivot", e);
        }
    }
    
    /**
     * 
     * map m_TuplePartCS_pivot in BaseCS2AS_qvtp_qvtcas {leftCS (lTuplePartCS : basecs::TuplePartCS[?];
     *  |)
     * { |}
     * rightAS ( |)
     * {realize rProperty : Property[1];
     *  |}
     * where ( |)
     * { |
     * lTuplePartCS.pivot := rProperty;
     * }
     * 
     */
    protected boolean MAP_m_TuplePartCS_pivot(final /*@NonInvalid*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull TuplePartCS lTuplePartCS_1) throws ReflectiveOperationException {
        try {
            // predicates and unrealized variables
            // creations
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Property rProperty = PivotFactory.eINSTANCE.createProperty();
            assert rProperty != null;
            models[1/*rightAS*/].add(rProperty);
            // property assignments
            lTuplePartCS_1.setPivot(rProperty);
            boolean m_TuplePartCS_pivot = ValueUtil.TRUE_VALUE;
            return m_TuplePartCS_pivot;
        } catch (Throwable e) {
            return handleExecutionFailure("MAP_m_TuplePartCS_pivot", e);
        }
    }
    
    /**
     * 
     * map m_TupleTypeCS_name_ownedParts_pivot in BaseCS2AS_qvtp_qvtcas {
     *   leftCS (lTupleTypeCS : basecs::TupleTypeCS[?];
     *  |)
     * { |}
     * rightAS ( |)
     * { |}
     * where ( |)
     * {aTupleType : TupleType[1] = lTupleTypeCS.pivot.oclAsType(TupleType)
     *   ;
     * name : String[?] = lTupleTypeCS.name;
     * ownedParts : OrderedSet(basecs::TuplePartCS) = lTupleTypeCS.ownedParts;
     * pivot : Element[?] = lTupleTypeCS.pivot;
     * _'\u00ABcollect\u00BB' : Sequence(Property) = ownedParts->collect(_'1_' |
     *     _'1_'.pivot.oclAsType(Property));
     *  |
     * aTupleType.name := name;
     * aTupleType.ownedProperties := _'\u00ABcollect\u00BB';
     * }
     * 
     */
    protected boolean MAP_m_TupleTypeCS_name_ownedParts_pivot(final /*@NonInvalid*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull TupleTypeCS lTupleTypeCS) throws ReflectiveOperationException {
        try {
            // predicates and unrealized variables
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull IdResolver idResolver = executor.getIdResolver();
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class TYP_TupleType_0 = idResolver.getClass(CLSSid_TupleType, null);
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot = lTupleTypeCS.getPivot();
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull TupleType aTupleType = ClassUtil.nonNullState((TupleType)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, pivot, TYP_TupleType_0));
            final /*@Thrown*/ java.lang.@org.eclipse.jdt.annotation.Nullable String name = lTupleTypeCS.getName();
            @SuppressWarnings("null")
            final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<TuplePartCS> ownedParts = lTupleTypeCS.getOwnedParts();
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot_0 = lTupleTypeCS.getPivot();
            final /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull OrderedSetValue BOXED_ownedParts = idResolver.createOrderedSetOfAll(ORD_CLSSid_TuplePartCS, ownedParts);
            /*@Thrown*/ SequenceValue.@org.eclipse.jdt.annotation.NonNull Accumulator accumulator = ValueUtil.createSequenceAccumulatorValue(SEQ_CLSSid_Property);
            @Nullable Iterator<Object> ITERATOR__1 = BOXED_ownedParts.iterator();
            /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull SequenceValue _171_collect_187;
            while (true) {
                if (!ITERATOR__1.hasNext()) {
                    _171_collect_187 = accumulator;
                    break;
                }
                /*@NonInvalid*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.Nullable TuplePartCS _1 = (TuplePartCS)ITERATOR__1.next();
                /**
                 * _'1_'.pivot.oclAsType(Property)
                 */
                final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class TYP_Property_0 = idResolver.getClass(CLSSid_Property, null);
                if (_1 == null) {
                    throw throwNull(lTupleTypeCS, "Null source for \'\'http://www.eclipse.org/ocl/2015/BaseCS\'::PivotableElementCS::pivot\'");
                }
                final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot_1 = _1.getPivot();
                final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Property oclAsType = ClassUtil.nonNullState((Property)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, pivot_1, TYP_Property_0));
                //
                accumulator.add(oclAsType);
            }
            // property assignments
            aTupleType.setName(name);
            final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<Property> ECORE__171_collect_187 = ((IdResolver.IdResolverExtension)idResolver).ecoreValueOfAll(Property.class, _171_collect_187);
            aTupleType.getOwnedProperties().addAll(ECORE__171_collect_187);
            boolean m_TupleTypeCS_name_ownedParts_pivot = ValueUtil.TRUE_VALUE;
            return m_TupleTypeCS_name_ownedParts_pivot;
        } catch (Throwable e) {
            return handleExecutionFailure("MAP_m_TupleTypeCS_name_ownedParts_pivot", e);
        }
    }
    
    /**
     * 
     * map m_TupleTypeCS_pivot in BaseCS2AS_qvtp_qvtcas {leftCS (lTupleTypeCS : basecs::TupleTypeCS[?];
     *  |)
     * { |}
     * rightAS ( |)
     * {realize rTupleType : TupleType[1];
     *  |}
     * where ( |)
     * { |
     * lTupleTypeCS.pivot := rTupleType;
     * }
     * 
     */
    protected boolean MAP_m_TupleTypeCS_pivot(final /*@NonInvalid*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull TupleTypeCS lTupleTypeCS_0) throws ReflectiveOperationException {
        try {
            // predicates and unrealized variables
            // creations
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable TupleType rTupleType = PivotFactory.eINSTANCE.createTupleType();
            assert rTupleType != null;
            models[1/*rightAS*/].add(rTupleType);
            // property assignments
            lTupleTypeCS_0.setPivot(rTupleType);
            boolean m_TupleTypeCS_pivot = ValueUtil.TRUE_VALUE;
            return m_TupleTypeCS_pivot;
        } catch (Throwable e) {
            return handleExecutionFailure("MAP_m_TupleTypeCS_pivot", e);
        }
    }
    
    /**
     * 
     * map m_TypeParameterCS_name_ownedExtends_pivot in BaseCS2AS_qvtp_qvtcas {
     *   leftCS (lTypeParameterCS : basecs::TypeParameterCS[?];
     *  |)
     * { |}
     * rightAS ( |)
     * { |}
     * where ( |)
     * {aTemplateParameter : TemplateParameter[1] = lTypeParameterCS.pivot.oclAsType(TemplateParameter)
     *   ;
     * name : String[?] = lTypeParameterCS.name;
     * ownedExtends : OrderedSet(basecs::TypedRefCS) = lTypeParameterCS.ownedExtends;
     * pivot : Element[?] = lTypeParameterCS.pivot;
     * _'\u00ABcollect\u00BB' : Sequence(Class) = ownedExtends->collect(_'1_' |
     *     _'1_'.pivot.oclAsType(Class));
     *  |
     * aTemplateParameter.constrainingClasses := _'\u00ABcollect\u00BB';
     * aTemplateParameter.name := name;
     * }
     * 
     */
    protected boolean MAP_m_TypeParameterCS_name_ownedExtends_pivot(final /*@NonInvalid*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull TypeParameterCS lTypeParameterCS) throws ReflectiveOperationException {
        try {
            // predicates and unrealized variables
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull IdResolver idResolver = executor.getIdResolver();
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class TYP_TemplateParameter_0 = idResolver.getClass(CLSSid_TemplateParameter, null);
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot = lTypeParameterCS.getPivot();
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull TemplateParameter aTemplateParameter = ClassUtil.nonNullState((TemplateParameter)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, pivot, TYP_TemplateParameter_0));
            final /*@Thrown*/ java.lang.@org.eclipse.jdt.annotation.Nullable String name = lTypeParameterCS.getName();
            @SuppressWarnings("null")
            final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<TypedRefCS> ownedExtends = lTypeParameterCS.getOwnedExtends();
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot_0 = lTypeParameterCS.getPivot();
            final /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull OrderedSetValue BOXED_ownedExtends = idResolver.createOrderedSetOfAll(ORD_CLSSid_TypedRefCS, ownedExtends);
            /*@Thrown*/ SequenceValue.@org.eclipse.jdt.annotation.NonNull Accumulator accumulator = ValueUtil.createSequenceAccumulatorValue(SEQ_CLSSid_Class);
            @Nullable Iterator<Object> ITERATOR__1 = BOXED_ownedExtends.iterator();
            /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull SequenceValue _171_collect_187;
            while (true) {
                if (!ITERATOR__1.hasNext()) {
                    _171_collect_187 = accumulator;
                    break;
                }
                /*@NonInvalid*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.Nullable TypedRefCS _1 = (TypedRefCS)ITERATOR__1.next();
                /**
                 * _'1_'.pivot.oclAsType(Class)
                 */
                final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class TYP_Class_0 = idResolver.getClass(CLSSid_Class, null);
                if (_1 == null) {
                    throw throwNull(lTypeParameterCS, "Null source for \'\'http://www.eclipse.org/ocl/2015/BaseCS\'::PivotableElementCS::pivot\'");
                }
                final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Element pivot_1 = _1.getPivot();
                final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class oclAsType = ClassUtil.nonNullState((Class)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, pivot_1, TYP_Class_0));
                //
                accumulator.add(oclAsType);
            }
            // property assignments
            final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<Class> ECORE__171_collect_187 = ((IdResolver.IdResolverExtension)idResolver).ecoreValueOfAll(Class.class, _171_collect_187);
            aTemplateParameter.getConstrainingClasses().addAll(ECORE__171_collect_187);
            aTemplateParameter.setName(name);
            boolean m_TypeParameterCS_name_ownedExtends_pivot = ValueUtil.TRUE_VALUE;
            return m_TypeParameterCS_name_ownedExtends_pivot;
        } catch (Throwable e) {
            return handleExecutionFailure("MAP_m_TypeParameterCS_name_ownedExtends_pivot", e);
        }
    }
    
    /**
     * 
     * map m_TypeParameterCS_pivot in BaseCS2AS_qvtp_qvtcas {leftCS (lTypeParameterCS : basecs::TypeParameterCS[?];
     *  |)
     * { |}
     * rightAS ( |)
     * {realize rTemplateParameter : TemplateParameter[1];
     *  |}
     * where ( |)
     * { |
     * lTypeParameterCS.pivot := rTemplateParameter;
     * }
     * 
     */
    protected boolean MAP_m_TypeParameterCS_pivot(final /*@NonInvalid*/ org.eclipse.ocl.xtext.basecs.@org.eclipse.jdt.annotation.NonNull TypeParameterCS lTypeParameterCS_0) throws ReflectiveOperationException {
        try {
            // predicates and unrealized variables
            // creations
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable TemplateParameter rTemplateParameter = PivotFactory.eINSTANCE.createTemplateParameter();
            assert rTemplateParameter != null;
            models[1/*rightAS*/].add(rTemplateParameter);
            // property assignments
            lTypeParameterCS_0.setPivot(rTemplateParameter);
            boolean m_TypeParameterCS_pivot = ValueUtil.TRUE_VALUE;
            return m_TypeParameterCS_pivot;
        } catch (Throwable e) {
            return handleExecutionFailure("MAP_m_TypeParameterCS_pivot", e);
        }
    }
}
