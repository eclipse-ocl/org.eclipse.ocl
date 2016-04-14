/*******************************************************************************
 * «codeGenHelper.getCopyright(' * ')»
 *
 * This code is 100% auto-generated
 * using: org.eclipse.ocl.examples.autogen.lookup.LookupUnqualifiedCodeGenerator
 *
 * Do not edit it.
 ********************************************************************************/

package org.eclipse.ocl.pivot.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Class;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.ids.IdManager;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.lookup.LookupEnvironment;
import org.eclipse.ocl.pivot.internal.lookup.LookupPackage;

public class PivotUnqualifiedClassLookupVisitor
	extends AbstractPivotCommonLookupVisitor
{
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull RootPackageId PACKid_$metamodel$ = IdManager.getRootPackageId("$metamodel$");
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull NsURIPackageId PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_2015_s_Lookup = IdManager.getNsURIPackageId("http://www.eclipse.org/ocl/2015/Lookup", null, LookupPackage.eINSTANCE);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull NsURIPackageId PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_2015_s_Pivot = IdManager.getNsURIPackageId("http://www.eclipse.org/ocl/2015/Pivot", null, PivotPackage.eINSTANCE);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull RootPackageId PACKid_java_c_s_s_org_eclipse_ocl_pivot_util = IdManager.getRootPackageId("java://org.eclipse.ocl.pivot.util");
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull RootPackageId PACKid_org_eclipse_ocl_pivot_evaluation = IdManager.getRootPackageId("org.eclipse.ocl.pivot.evaluation");
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull RootPackageId PACKid_org_eclipse_ocl_pivot_ids = IdManager.getRootPackageId("org.eclipse.ocl.pivot.ids");
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_Class = PACKid_$metamodel$.getClassId("Class", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_Executor = PACKid_org_eclipse_ocl_pivot_evaluation.getClassId("Executor", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_IdResolver = PACKid_org_eclipse_ocl_pivot_ids.getClassId("IdResolver", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_Library = PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_2015_s_Pivot.getClassId("Library", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_Library_0 = PACKid_$metamodel$.getClassId("Library", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_LookupEnvironment = PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_2015_s_Lookup.getClassId("LookupEnvironment", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_OclElement = PACKid_$metamodel$.getClassId("OclElement", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_Package = PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_2015_s_Pivot.getClassId("Package", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_Package_0 = PACKid_$metamodel$.getClassId("Package", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_PivotUnqualifiedClassLookupVisitor = PACKid_java_c_s_s_org_eclipse_ocl_pivot_util.getClassId("PivotUnqualifiedClassLookupVisitor", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull CollectionTypeId SET_CLSSid_Class = TypeId.SET.getSpecializedId(CLSSid_Class);
    
    protected final /*@Thrown*/ org.eclipse.ocl.pivot.evaluation.@org.eclipse.jdt.annotation.NonNull Executor executor;
    protected final /*@Thrown*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull IdResolver idResolver;
    protected /*@Thrown*/ java.lang.@org.eclipse.jdt.annotation.Nullable Object child;
    
    public PivotUnqualifiedClassLookupVisitor(@NonNull LookupEnvironment context) {
        super(context);
        this.executor = context.getExecutor();
        this.idResolver = executor.getIdResolver();
    }
    
    @Override
    protected @Nullable LookupEnvironment doVisiting(@NonNull Visitable visitable) {
        return parentEnv((EObject)visitable);
    }
    
    /**
     * Continue the search for matches in the parent of element.
     */
    protected @Nullable LookupEnvironment parentEnv(@NonNull EObject element) {
        EObject parent = element.eContainer();
        if (parent instanceof Visitable) {
            this.child = element;
            return ((Visitable)parent).accept(this);
        }
        else {
            return context;
        }
    }
    
    /**
     * visitLibrary(element : Library[1]) : lookup::LookupEnvironment[?]
     * 
     * _'null' : lookup::LookupEnvironment[1]
     */
    @Override
    public /*@NonInvalid*/ LookupEnvironment visitLibrary(final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Library element) {
        final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<Class> ownedClasses = element.getOwnedClasses();
        @SuppressWarnings("null")
        final /*@Thrown*/ org.eclipse.ocl.pivot.internal.lookup.@org.eclipse.jdt.annotation.NonNull LookupEnvironment inner = context.addElements(ownedClasses);
        final /*@Thrown*/ boolean hasFinalResult = inner.hasFinalResult();
        /*@Thrown*/ org.eclipse.ocl.pivot.internal.lookup.@org.eclipse.jdt.annotation.Nullable LookupEnvironment symbol_0;
        if (hasFinalResult) {
            symbol_0 = inner;
        }
        else {
            final /*@Thrown*/ org.eclipse.ocl.pivot.internal.lookup.@org.eclipse.jdt.annotation.Nullable LookupEnvironment parentEnv = this.parentEnv(element);
            symbol_0 = parentEnv;
        }
        return symbol_0;
    }
    
    /**
     * visitPackage(element : Package[1]) : lookup::LookupEnvironment[?]
     * 
     * _'null' : lookup::LookupEnvironment[1]
     */
    @Override
    public /*@NonInvalid*/ LookupEnvironment visitPackage(final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Package element_0) {
        final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<Class> ownedClasses = element_0.getOwnedClasses();
        @SuppressWarnings("null")
        final /*@Thrown*/ org.eclipse.ocl.pivot.internal.lookup.@org.eclipse.jdt.annotation.NonNull LookupEnvironment inner = context.addElements(ownedClasses);
        final /*@Thrown*/ boolean hasFinalResult = inner.hasFinalResult();
        /*@Thrown*/ org.eclipse.ocl.pivot.internal.lookup.@org.eclipse.jdt.annotation.Nullable LookupEnvironment symbol_0;
        if (hasFinalResult) {
            symbol_0 = inner;
        }
        else {
            final /*@Thrown*/ org.eclipse.ocl.pivot.internal.lookup.@org.eclipse.jdt.annotation.Nullable LookupEnvironment parentEnv = this.parentEnv(element_0);
            symbol_0 = parentEnv;
        }
        return symbol_0;
    }
}
