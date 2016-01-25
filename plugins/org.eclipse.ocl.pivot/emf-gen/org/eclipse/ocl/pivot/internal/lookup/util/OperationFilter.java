/*******************************************************************************
 * «codeGenHelper.getCopyright(' * ')»
 *
 * This code is 100% auto-generated
 * using: org.eclipse.ocl.examples.autogen.lookup.LookupFilterGenerator
 *
 * Do not edit it.
 ********************************************************************************/

package org.eclipse.ocl.pivot.internal.lookup.util;

import java.util.Iterator;
import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Parameter;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.ids.IdManager;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.library.classifier.OclTypeConformsToOperation;
import org.eclipse.ocl.pivot.library.collection.CollectionSizeOperation;
import org.eclipse.ocl.pivot.library.collection.OrderedCollectionAtOperation;
import org.eclipse.ocl.pivot.library.collection.OrderedCollectionIndexOfOperation;
import org.eclipse.ocl.pivot.library.logical.BooleanAndOperation;
import org.eclipse.ocl.pivot.messages.PivotMessages;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.InvalidValueException;
import org.eclipse.ocl.pivot.values.OrderedSetValue;

public class OperationFilter
	extends AbstractPivotLookupFilter<Operation>
{
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull RootPackageId PACKid_$metamodel$ = IdManager.getRootPackageId("$metamodel$");
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull NsURIPackageId PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_2015_s_Pivot = IdManager.getNsURIPackageId("http://www.eclipse.org/ocl/2015/Pivot", null, PivotPackage.eINSTANCE);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull RootPackageId PACKid_java_c_s_s_org_eclipse_ocl_pivot_internal_lookup_util = IdManager.getRootPackageId("java://org.eclipse.ocl.pivot.internal.lookup.util");
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull RootPackageId PACKid_org_eclipse_ocl_pivot_evaluation = IdManager.getRootPackageId("org.eclipse.ocl.pivot.evaluation");
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull RootPackageId PACKid_org_eclipse_ocl_pivot_ids = IdManager.getRootPackageId("org.eclipse.ocl.pivot.ids");
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_Executor = PACKid_org_eclipse_ocl_pivot_evaluation.getClassId("Executor", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_IdResolver = PACKid_org_eclipse_ocl_pivot_ids.getClassId("IdResolver", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_Operation = PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_2015_s_Pivot.getClassId("Operation", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_OperationFilter = PACKid_java_c_s_s_org_eclipse_ocl_pivot_internal_lookup_util.getClassId("OperationFilter", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_Operation_0 = PACKid_$metamodel$.getClassId("Operation", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_Parameter = PACKid_$metamodel$.getClassId("Parameter", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_Type = PACKid_$metamodel$.getClassId("Type", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull CollectionTypeId ORD_CLSSid_Parameter = TypeId.ORDERED_SET.getSpecializedId(CLSSid_Parameter);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull CollectionTypeId ORD_CLSSid_Type = TypeId.ORDERED_SET.getSpecializedId(CLSSid_Type);
    
    protected final /*@Thrown*/ org.eclipse.ocl.pivot.evaluation.@org.eclipse.jdt.annotation.NonNull Executor executor;
    protected final /*@Thrown*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull IdResolver idResolver;
    protected final /*@Thrown*/ List<Type> argTypes;
    
    public OperationFilter(@NonNull Executor executor,final /*@Thrown*/ List<Type> argTypes) {
        super(Operation.class);
        this.argTypes =  argTypes;
        this.executor = executor;
        this.idResolver = executor.getIdResolver();
    }
    
    /**
     * _'org.eclipse.ocl.pivot.internal.lookup.util'::OperationFilter::_matches(element : Operation[1]) : Boolean[?]
     * 
     * _'null' : Boolean[?]
     */
    public /*@NonInvalid*/ Boolean _matches(final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Operation element) {
        /*@Caught*/ @NonNull Object CAUGHT_argTypes;
        try {
            final /*@Thrown*/ List<Type> argTypes = (List<Type>)this.argTypes;
            CAUGHT_argTypes = argTypes;
        }
        catch (Exception e) {
            CAUGHT_argTypes = ValueUtil.createInvalidValue(e);
        }
        /*@Caught*/ @NonNull Object CAUGHT_eq;
        try {
            final /*@Caught*/ @NonNull Object BOXED_argTypes = idResolver.createOrderedSetOfAll(ORD_CLSSid_Type, (Iterable)CAUGHT_argTypes);
            if (BOXED_argTypes instanceof InvalidValueException) {
                throw (InvalidValueException)BOXED_argTypes;
            }
            final /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull IntegerValue size = CollectionSizeOperation.INSTANCE.evaluate(BOXED_argTypes);
            final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<Parameter> ownedParameters = element.getOwnedParameters();
            final /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull OrderedSetValue BOXED_ownedParameters = idResolver.createOrderedSetOfAll(ORD_CLSSid_Parameter, ownedParameters);
            final /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull IntegerValue size_0 = CollectionSizeOperation.INSTANCE.evaluate(BOXED_ownedParameters);
            final /*@Thrown*/ boolean eq = size.equals(size_0);
            CAUGHT_eq = eq;
        }
        catch (Exception e) {
            CAUGHT_eq = ValueUtil.createInvalidValue(e);
        }
        /*@Caught*/ @NonNull Object CAUGHT_forAll;
        try {
            final /*@Caught*/ @NonNull Object BOXED_argTypes_0 = idResolver.createOrderedSetOfAll(ORD_CLSSid_Type, (Iterable)CAUGHT_argTypes);
            if (BOXED_argTypes_0 instanceof InvalidValueException) {
                throw (InvalidValueException)BOXED_argTypes_0;
            }
            /*@Thrown*/ java.lang.@org.eclipse.jdt.annotation.Nullable Object accumulator = ValueUtil.TRUE_VALUE;
            @Nullable Iterator<?> ITERATOR_x = ((OrderedSetValue)BOXED_argTypes_0).iterator();
            /*@Thrown*/ boolean forAll;
            while (true) {
                if (!ITERATOR_x.hasNext()) {
                    if (accumulator == ValueUtil.TRUE_VALUE) {
                        forAll = ValueUtil.TRUE_VALUE;
                    }
                    else {
                        throw (InvalidValueException)accumulator;
                    }
                    break;
                }
                /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Type x = (Type)ITERATOR_x.next();
                /**
                 * _'null' : Boolean[1]
                 */
                /*@Caught*/ @NonNull Object CAUGHT_conformsTo;
                try {
                    final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<Parameter> ownedParameters_0 = element.getOwnedParameters();
                    final /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull OrderedSetValue BOXED_ownedParameters_0 = idResolver.createOrderedSetOfAll(ORD_CLSSid_Parameter, ownedParameters_0);
                    final /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull IntegerValue indexOf = OrderedCollectionIndexOfOperation.INSTANCE.evaluate(BOXED_argTypes_0, x);
                    final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Parameter at = (Parameter)OrderedCollectionAtOperation.INSTANCE.evaluate(BOXED_ownedParameters_0, indexOf);
                    if (at == null) {
                        throw new InvalidValueException("Null source for \'TypedElement::type\'");
                    }
                    final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Type type = at.getType();
                    final /*@Thrown*/ boolean conformsTo = OclTypeConformsToOperation.INSTANCE.evaluate(executor, x, type).booleanValue();
                    CAUGHT_conformsTo = conformsTo;
                }
                catch (Exception e) {
                    CAUGHT_conformsTo = ValueUtil.createInvalidValue(e);
                }
                //
                if (CAUGHT_conformsTo == ValueUtil.FALSE_VALUE) {					// Normal unsuccessful body evaluation result
                    forAll = ValueUtil.FALSE_VALUE;
                    break;														// Stop immediately 
                }
                else if (CAUGHT_conformsTo == ValueUtil.TRUE_VALUE) {				// Normal successful body evaluation result
                    ;															// Carry on
                }
                else if (CAUGHT_conformsTo instanceof InvalidValueException) {		// Abnormal exception evaluation result
                    accumulator = CAUGHT_conformsTo;									// Cache an exception failure
                }
                else {															// Impossible badly typed result
                    accumulator = new InvalidValueException(PivotMessages.NonBooleanBody, "forAll");
                }
            }
            CAUGHT_forAll = forAll;
        }
        catch (Exception e) {
            CAUGHT_forAll = ValueUtil.createInvalidValue(e);
        }
        final /*@Thrown*/ java.lang.@org.eclipse.jdt.annotation.Nullable Boolean and = BooleanAndOperation.INSTANCE.evaluate(CAUGHT_eq, CAUGHT_forAll);
        return and;
    }
}
