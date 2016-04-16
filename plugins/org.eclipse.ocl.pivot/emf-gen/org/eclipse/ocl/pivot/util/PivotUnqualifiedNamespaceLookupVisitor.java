/*******************************************************************************
 * «codeGenHelper.getCopyright(' * ')»
 *
 * This code is 100% auto-generated
 * using: org.eclipse.ocl.examples.autogen.lookup.LookupUnqualifiedCodeGenerator
 *
 * Do not edit it.
 ********************************************************************************/

package org.eclipse.ocl.pivot.util;

import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Behavior;
import org.eclipse.ocl.pivot.Class;
import org.eclipse.ocl.pivot.DataType;
import org.eclipse.ocl.pivot.Import;
import org.eclipse.ocl.pivot.Namespace;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Package;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.ids.IdManager;
import org.eclipse.ocl.pivot.ids.IdResolver;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.library.executor.ExecutorSingleIterationManager;
import org.eclipse.ocl.pivot.internal.lookup.LookupEnvironment;
import org.eclipse.ocl.pivot.internal.lookup.LookupPackage;
import org.eclipse.ocl.pivot.library.AbstractBinaryOperation;
import org.eclipse.ocl.pivot.library.LibraryIteration;
import org.eclipse.ocl.pivot.library.logical.BooleanNotOperation;
import org.eclipse.ocl.pivot.library.oclany.OclAnyOclAsSetOperation;
import org.eclipse.ocl.pivot.oclstdlib.OCLstdlibTables;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.BagValue;
import org.eclipse.ocl.pivot.values.InvalidValueException;
import org.eclipse.ocl.pivot.values.OrderedSetValue;
import org.eclipse.ocl.pivot.values.SequenceValue;
import org.eclipse.ocl.pivot.values.SetValue;

public class PivotUnqualifiedNamespaceLookupVisitor
	extends AbstractPivotCommonLookupVisitor
{
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull RootPackageId PACKid_$metamodel$ = IdManager.getRootPackageId("$metamodel$");
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull NsURIPackageId PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_2015_s_Lookup = IdManager.getNsURIPackageId("http://www.eclipse.org/ocl/2015/Lookup", null, LookupPackage.eINSTANCE);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull NsURIPackageId PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_2015_s_Pivot = IdManager.getNsURIPackageId("http://www.eclipse.org/ocl/2015/Pivot", null, PivotPackage.eINSTANCE);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull RootPackageId PACKid_java_c_s_s_org_eclipse_ocl_pivot_util = IdManager.getRootPackageId("java://org.eclipse.ocl.pivot.util");
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull RootPackageId PACKid_org_eclipse_ocl_pivot_evaluation = IdManager.getRootPackageId("org.eclipse.ocl.pivot.evaluation");
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull RootPackageId PACKid_org_eclipse_ocl_pivot_ids = IdManager.getRootPackageId("org.eclipse.ocl.pivot.ids");
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_Behavior = PACKid_$metamodel$.getClassId("Behavior", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_Class = PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_2015_s_Pivot.getClassId("Class", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_Class_0 = PACKid_$metamodel$.getClassId("Class", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_DataType = PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_2015_s_Pivot.getClassId("DataType", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_DataType_0 = PACKid_$metamodel$.getClassId("DataType", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_Executor = PACKid_org_eclipse_ocl_pivot_evaluation.getClassId("Executor", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_IdResolver = PACKid_org_eclipse_ocl_pivot_ids.getClassId("IdResolver", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_Import = PACKid_$metamodel$.getClassId("Import", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_LookupEnvironment = PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_2015_s_Lookup.getClassId("LookupEnvironment", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_Model = PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_2015_s_Pivot.getClassId("Model", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_Model_0 = PACKid_$metamodel$.getClassId("Model", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_Namespace = PACKid_$metamodel$.getClassId("Namespace", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_OclElement = PACKid_$metamodel$.getClassId("OclElement", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_Operation = PACKid_$metamodel$.getClassId("Operation", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_Package = PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_2015_s_Pivot.getClassId("Package", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_Package_0 = PACKid_$metamodel$.getClassId("Package", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_PivotUnqualifiedNamespaceLookupVisitor = PACKid_java_c_s_s_org_eclipse_ocl_pivot_util.getClassId("PivotUnqualifiedNamespaceLookupVisitor", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_Property = PACKid_$metamodel$.getClassId("Property", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull CollectionTypeId BAG_CLSSid_Behavior = TypeId.BAG.getSpecializedId(CLSSid_Behavior);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull CollectionTypeId BAG_CLSSid_Operation = TypeId.BAG.getSpecializedId(CLSSid_Operation);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull CollectionTypeId BAG_CLSSid_Property = TypeId.BAG.getSpecializedId(CLSSid_Property);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull CollectionTypeId ORD_CLSSid_Import = TypeId.ORDERED_SET.getSpecializedId(CLSSid_Import);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull CollectionTypeId ORD_CLSSid_Operation = TypeId.ORDERED_SET.getSpecializedId(CLSSid_Operation);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull CollectionTypeId ORD_CLSSid_Property = TypeId.ORDERED_SET.getSpecializedId(CLSSid_Property);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull CollectionTypeId SEQ_CLSSid_Namespace = TypeId.SEQUENCE.getSpecializedId(CLSSid_Namespace);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull CollectionTypeId SET_CLSSid_Behavior = TypeId.SET.getSpecializedId(CLSSid_Behavior);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull CollectionTypeId SET_CLSSid_Class = TypeId.SET.getSpecializedId(CLSSid_Class_0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull CollectionTypeId SET_CLSSid_DataType = TypeId.SET.getSpecializedId(CLSSid_DataType_0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull CollectionTypeId SET_CLSSid_Package = TypeId.SET.getSpecializedId(CLSSid_Package_0);
    
    protected final /*@Thrown*/ org.eclipse.ocl.pivot.evaluation.@org.eclipse.jdt.annotation.NonNull Executor executor;
    protected final /*@Thrown*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull IdResolver idResolver;
    protected /*@Thrown*/ java.lang.@org.eclipse.jdt.annotation.Nullable Object child;
    
    public PivotUnqualifiedNamespaceLookupVisitor(@NonNull LookupEnvironment context) {
        super(context);
        this.executor = ClassUtil.nonNull(context.getExecutor());
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
     * visitClass(element : Class[1]) : lookup::LookupEnvironment[?]
     * 
     * _'null' : lookup::LookupEnvironment[1]
     */
    @Override
    public /*@NonInvalid*/ LookupEnvironment visitClass(final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class element) {
        final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull IdResolver idResolver = executor.getIdResolver();
        final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull StandardLibrary standardLibrary = idResolver.getStandardLibrary();
        final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<Behavior> ownedBehaviors = element.getOwnedBehaviors();
        @SuppressWarnings("null")
        final /*@Thrown*/ org.eclipse.ocl.pivot.internal.lookup.@org.eclipse.jdt.annotation.NonNull LookupEnvironment inner = context.addElements(ownedBehaviors);
        final /*@Thrown*/ boolean hasFinalResult = inner.hasFinalResult();
        /*@Thrown*/ org.eclipse.ocl.pivot.internal.lookup.@org.eclipse.jdt.annotation.Nullable LookupEnvironment symbol_6;
        if (hasFinalResult) {
            symbol_6 = inner;
        }
        else {
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull SetValue oclAsSet = OclAnyOclAsSetOperation.INSTANCE.evaluate(executor, SET_CLSSid_Class, element);
            final org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class TYPE_allSuperClasses_2 = executor.getStaticTypeOf(oclAsSet);
            final LibraryIteration.@org.eclipse.jdt.annotation.NonNull LibraryIterationExtension IMPL_allSuperClasses_2 = (LibraryIteration.LibraryIterationExtension)TYPE_allSuperClasses_2.lookupImplementation(standardLibrary, OCLstdlibTables.Operations._Set__closure);
            final @NonNull Object ACC_allSuperClasses_2 = IMPL_allSuperClasses_2.createAccumulatorValue(executor, SET_CLSSid_Class, SET_CLSSid_Class);
            /**
             * Implementation of the iterator body.
             */
            final @NonNull AbstractBinaryOperation BODY_allSuperClasses_2 = new AbstractBinaryOperation()
            {
                /**
                 * _'null' : Set(Class)
                 */
                @Override
                public @Nullable Object evaluate(final @NonNull Executor executor, final @NonNull TypeId typeId, final @Nullable Object oclAsSet, final /*@NonInvalid*/ java.lang.@org.eclipse.jdt.annotation.Nullable Object _1_0) {
                    final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Class symbol_0 = (Class)_1_0;
                    if (symbol_0 == null) {
                        throw new InvalidValueException("Null source for \'Class::superClasses\'");
                    }
                    final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<Class> superClasses = symbol_0.getSuperClasses();
                    final /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull SetValue BOXED_superClasses = idResolver.createSetOfAll(SET_CLSSid_Class, superClasses);
                    return BOXED_superClasses;
                }
            };
            final @NonNull  ExecutorSingleIterationManager MGR_allSuperClasses_2 = new ExecutorSingleIterationManager(executor, SET_CLSSid_Class, BODY_allSuperClasses_2, oclAsSet, ACC_allSuperClasses_2);
            final /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull SetValue allSuperClasses = ClassUtil.nonNullState((SetValue)IMPL_allSuperClasses_2.evaluateIteration(MGR_allSuperClasses_2));
            final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<Operation> ownedOperations = element.getOwnedOperations();
            final /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull OrderedSetValue BOXED_ownedOperations = idResolver.createOrderedSetOfAll(ORD_CLSSid_Operation, ownedOperations);
            /*@Thrown*/ OrderedSetValue.@org.eclipse.jdt.annotation.NonNull Accumulator accumulator = ValueUtil.createOrderedSetAccumulatorValue(ORD_CLSSid_Operation);
            @Nullable Iterator<Object> ITERATOR__1 = BOXED_ownedOperations.iterator();
            /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull OrderedSetValue select;
            while (true) {
                if (!ITERATOR__1.hasNext()) {
                    select = accumulator;
                    break;
                }
                /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Operation _1 = (Operation)ITERATOR__1.next();
                /**
                 * _'not' : Boolean[?]
                 */
                if (_1 == null) {
                    throw new InvalidValueException("Null source for \'Feature::isStatic\'");
                }
                final /*@Thrown*/ boolean isStatic = _1.isIsStatic();
                final /*@Thrown*/ java.lang.@org.eclipse.jdt.annotation.Nullable Boolean not = BooleanNotOperation.INSTANCE.evaluate(isStatic);
                if (not == null) {
                    throw new InvalidValueException("Null body for \'OrderedSet(T).select(OrderedSet.T[?] | Lambda T() : Boolean[1]) : OrderedSet(T)\'");
                }
                //
                if (not == ValueUtil.TRUE_VALUE) {
                    accumulator.add(_1);
                }
            }
            final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<Operation> ECORE_select = ((IdResolver.IdResolverExtension)idResolver).ecoreValueOfAll(Operation.class, select);
            @SuppressWarnings("null")
            final /*@Thrown*/ org.eclipse.ocl.pivot.internal.lookup.@org.eclipse.jdt.annotation.NonNull LookupEnvironment addElements = context.addElements(ECORE_select);
            /*@Thrown*/ BagValue.@org.eclipse.jdt.annotation.NonNull Accumulator accumulator_0 = ValueUtil.createBagAccumulatorValue(BAG_CLSSid_Behavior);
            @Nullable Iterator<Object> ITERATOR__1_1 = allSuperClasses.iterator();
            /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull BagValue collect;
            while (true) {
                if (!ITERATOR__1_1.hasNext()) {
                    collect = accumulator_0;
                    break;
                }
                /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Class _1_1 = (Class)ITERATOR__1_1.next();
                /**
                 * _'null' : Set(Behavior)
                 */
                if (_1_1 == null) {
                    throw new InvalidValueException("Null source for \'Class::ownedBehaviors\'");
                }
                final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<Behavior> ownedBehaviors_0 = _1_1.getOwnedBehaviors();
                final /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull SetValue BOXED_ownedBehaviors_0 = idResolver.createSetOfAll(SET_CLSSid_Behavior, ownedBehaviors_0);
                //
                for (Object value : BOXED_ownedBehaviors_0.flatten().getElements()) {
                    accumulator_0.add(value);
                }
            }
            final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<Behavior> ECORE_collect = ((IdResolver.IdResolverExtension)idResolver).ecoreValueOfAll(Behavior.class, collect);
            @SuppressWarnings("null")
            final /*@Thrown*/ org.eclipse.ocl.pivot.internal.lookup.@org.eclipse.jdt.annotation.NonNull LookupEnvironment inner_0 = addElements.addElements(ECORE_collect);
            final /*@Thrown*/ boolean hasFinalResult_0 = inner_0.hasFinalResult();
            /*@Thrown*/ org.eclipse.ocl.pivot.internal.lookup.@org.eclipse.jdt.annotation.Nullable LookupEnvironment symbol_5;
            if (hasFinalResult_0) {
                symbol_5 = inner_0;
            }
            else {
                final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<Property> ownedProperties = element.getOwnedProperties();
                final /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull OrderedSetValue BOXED_ownedProperties = idResolver.createOrderedSetOfAll(ORD_CLSSid_Property, ownedProperties);
                /*@Thrown*/ OrderedSetValue.@org.eclipse.jdt.annotation.NonNull Accumulator accumulator_1 = ValueUtil.createOrderedSetAccumulatorValue(ORD_CLSSid_Property);
                @Nullable Iterator<Object> ITERATOR__1_2 = BOXED_ownedProperties.iterator();
                /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull OrderedSetValue select_0;
                while (true) {
                    if (!ITERATOR__1_2.hasNext()) {
                        select_0 = accumulator_1;
                        break;
                    }
                    /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Property _1_2 = (Property)ITERATOR__1_2.next();
                    /**
                     * _'not' : Boolean[?]
                     */
                    if (_1_2 == null) {
                        throw new InvalidValueException("Null source for \'Feature::isStatic\'");
                    }
                    final /*@Thrown*/ boolean isStatic_0 = _1_2.isIsStatic();
                    final /*@Thrown*/ java.lang.@org.eclipse.jdt.annotation.Nullable Boolean not_0 = BooleanNotOperation.INSTANCE.evaluate(isStatic_0);
                    if (not_0 == null) {
                        throw new InvalidValueException("Null body for \'OrderedSet(T).select(OrderedSet.T[?] | Lambda T() : Boolean[1]) : OrderedSet(T)\'");
                    }
                    //
                    if (not_0 == ValueUtil.TRUE_VALUE) {
                        accumulator_1.add(_1_2);
                    }
                }
                final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<Property> ECORE_select_0 = ((IdResolver.IdResolverExtension)idResolver).ecoreValueOfAll(Property.class, select_0);
                @SuppressWarnings("null")
                final /*@Thrown*/ org.eclipse.ocl.pivot.internal.lookup.@org.eclipse.jdt.annotation.NonNull LookupEnvironment addElements_0 = context.addElements(ECORE_select_0);
                /*@Thrown*/ BagValue.@org.eclipse.jdt.annotation.NonNull Accumulator accumulator_2 = ValueUtil.createBagAccumulatorValue(BAG_CLSSid_Operation);
                @Nullable Iterator<Object> ITERATOR__1_4 = allSuperClasses.iterator();
                /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull BagValue collect_0;
                while (true) {
                    if (!ITERATOR__1_4.hasNext()) {
                        collect_0 = accumulator_2;
                        break;
                    }
                    /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Class _1_4 = (Class)ITERATOR__1_4.next();
                    /**
                     * _'null' : OrderedSet(Operation)
                     */
                    if (_1_4 == null) {
                        throw new InvalidValueException("Null source for \'Class::ownedOperations\'");
                    }
                    final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<Operation> ownedOperations_0 = _1_4.getOwnedOperations();
                    final /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull OrderedSetValue BOXED_ownedOperations_0 = idResolver.createOrderedSetOfAll(ORD_CLSSid_Operation, ownedOperations_0);
                    //
                    for (Object value : BOXED_ownedOperations_0.flatten().getElements()) {
                        accumulator_2.add(value);
                    }
                }
                /*@Thrown*/ BagValue.@org.eclipse.jdt.annotation.NonNull Accumulator accumulator_3 = ValueUtil.createBagAccumulatorValue(BAG_CLSSid_Operation);
                @Nullable Iterator<Object> ITERATOR__1_5 = collect_0.iterator();
                /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull BagValue select_1;
                while (true) {
                    if (!ITERATOR__1_5.hasNext()) {
                        select_1 = accumulator_3;
                        break;
                    }
                    /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Operation _1_5 = (Operation)ITERATOR__1_5.next();
                    /**
                     * _'not' : Boolean[?]
                     */
                    if (_1_5 == null) {
                        throw new InvalidValueException("Null source for \'Feature::isStatic\'");
                    }
                    final /*@Thrown*/ boolean isStatic_1 = _1_5.isIsStatic();
                    final /*@Thrown*/ java.lang.@org.eclipse.jdt.annotation.Nullable Boolean not_1 = BooleanNotOperation.INSTANCE.evaluate(isStatic_1);
                    if (not_1 == null) {
                        throw new InvalidValueException("Null body for \'Bag(T).select(Bag.T[?] | Lambda T() : Boolean[1]) : Bag(T)\'");
                    }
                    //
                    if (not_1 == ValueUtil.TRUE_VALUE) {
                        accumulator_3.add(_1_5);
                    }
                }
                final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<Operation> ECORE_select_1 = ((IdResolver.IdResolverExtension)idResolver).ecoreValueOfAll(Operation.class, select_1);
                @SuppressWarnings("null")
                final /*@Thrown*/ org.eclipse.ocl.pivot.internal.lookup.@org.eclipse.jdt.annotation.NonNull LookupEnvironment inner_1 = addElements_0.addElements(ECORE_select_1);
                final /*@Thrown*/ boolean hasFinalResult_1 = inner_1.hasFinalResult();
                /*@Thrown*/ org.eclipse.ocl.pivot.internal.lookup.@org.eclipse.jdt.annotation.Nullable LookupEnvironment symbol_4;
                if (hasFinalResult_1) {
                    symbol_4 = inner_1;
                }
                else {
                    /*@Thrown*/ BagValue.@org.eclipse.jdt.annotation.NonNull Accumulator accumulator_4 = ValueUtil.createBagAccumulatorValue(BAG_CLSSid_Property);
                    @Nullable Iterator<Object> ITERATOR__1_7 = allSuperClasses.iterator();
                    /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull BagValue collect_1;
                    while (true) {
                        if (!ITERATOR__1_7.hasNext()) {
                            collect_1 = accumulator_4;
                            break;
                        }
                        /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Class _1_7 = (Class)ITERATOR__1_7.next();
                        /**
                         * _'null' : OrderedSet(Property)
                         */
                        if (_1_7 == null) {
                            throw new InvalidValueException("Null source for \'Class::ownedProperties\'");
                        }
                        final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<Property> ownedProperties_0 = _1_7.getOwnedProperties();
                        final /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull OrderedSetValue BOXED_ownedProperties_0 = idResolver.createOrderedSetOfAll(ORD_CLSSid_Property, ownedProperties_0);
                        //
                        for (Object value : BOXED_ownedProperties_0.flatten().getElements()) {
                            accumulator_4.add(value);
                        }
                    }
                    /*@Thrown*/ BagValue.@org.eclipse.jdt.annotation.NonNull Accumulator accumulator_5 = ValueUtil.createBagAccumulatorValue(BAG_CLSSid_Property);
                    @Nullable Iterator<Object> ITERATOR__1_8 = collect_1.iterator();
                    /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull BagValue select_2;
                    while (true) {
                        if (!ITERATOR__1_8.hasNext()) {
                            select_2 = accumulator_5;
                            break;
                        }
                        /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Property _1_8 = (Property)ITERATOR__1_8.next();
                        /**
                         * _'not' : Boolean[?]
                         */
                        if (_1_8 == null) {
                            throw new InvalidValueException("Null source for \'Feature::isStatic\'");
                        }
                        final /*@Thrown*/ boolean isStatic_2 = _1_8.isIsStatic();
                        final /*@Thrown*/ java.lang.@org.eclipse.jdt.annotation.Nullable Boolean not_2 = BooleanNotOperation.INSTANCE.evaluate(isStatic_2);
                        if (not_2 == null) {
                            throw new InvalidValueException("Null body for \'Bag(T).select(Bag.T[?] | Lambda T() : Boolean[1]) : Bag(T)\'");
                        }
                        //
                        if (not_2 == ValueUtil.TRUE_VALUE) {
                            accumulator_5.add(_1_8);
                        }
                    }
                    final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<Property> ECORE_select_2 = ((IdResolver.IdResolverExtension)idResolver).ecoreValueOfAll(Property.class, select_2);
                    @SuppressWarnings("null")
                    final /*@Thrown*/ org.eclipse.ocl.pivot.internal.lookup.@org.eclipse.jdt.annotation.NonNull LookupEnvironment inner_2 = context.addElements(ECORE_select_2);
                    final /*@Thrown*/ boolean hasFinalResult_2 = inner_2.hasFinalResult();
                    /*@Thrown*/ org.eclipse.ocl.pivot.internal.lookup.@org.eclipse.jdt.annotation.Nullable LookupEnvironment symbol_3;
                    if (hasFinalResult_2) {
                        symbol_3 = inner_2;
                    }
                    else {
                        final /*@Thrown*/ org.eclipse.ocl.pivot.internal.lookup.@org.eclipse.jdt.annotation.Nullable LookupEnvironment parentEnv = this.parentEnv(element);
                        symbol_3 = parentEnv;
                    }
                    symbol_4 = symbol_3;
                }
                symbol_5 = symbol_4;
            }
            symbol_6 = symbol_5;
        }
        return symbol_6;
    }
    
    /**
     * visitDataType(element : DataType[1]) : lookup::LookupEnvironment[?]
     * 
     * _'null' : lookup::LookupEnvironment[1]
     */
    @Override
    public /*@NonInvalid*/ LookupEnvironment visitDataType(final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull DataType element_0) {
        final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull IdResolver idResolver = executor.getIdResolver();
        final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull StandardLibrary standardLibrary = idResolver.getStandardLibrary();
        final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<Operation> ownedOperations = element_0.getOwnedOperations();
        final /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull OrderedSetValue BOXED_ownedOperations = idResolver.createOrderedSetOfAll(ORD_CLSSid_Operation, ownedOperations);
        /*@Thrown*/ OrderedSetValue.@org.eclipse.jdt.annotation.NonNull Accumulator accumulator = ValueUtil.createOrderedSetAccumulatorValue(ORD_CLSSid_Operation);
        @Nullable Iterator<Object> ITERATOR__1 = BOXED_ownedOperations.iterator();
        /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull OrderedSetValue select;
        while (true) {
            if (!ITERATOR__1.hasNext()) {
                select = accumulator;
                break;
            }
            /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Operation _1 = (Operation)ITERATOR__1.next();
            /**
             * _'not' : Boolean[?]
             */
            if (_1 == null) {
                throw new InvalidValueException("Null source for \'Feature::isStatic\'");
            }
            final /*@Thrown*/ boolean isStatic = _1.isIsStatic();
            final /*@Thrown*/ java.lang.@org.eclipse.jdt.annotation.Nullable Boolean not = BooleanNotOperation.INSTANCE.evaluate(isStatic);
            if (not == null) {
                throw new InvalidValueException("Null body for \'OrderedSet(T).select(OrderedSet.T[?] | Lambda T() : Boolean[1]) : OrderedSet(T)\'");
            }
            //
            if (not == ValueUtil.TRUE_VALUE) {
                accumulator.add(_1);
            }
        }
        final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<Operation> ECORE_select = ((IdResolver.IdResolverExtension)idResolver).ecoreValueOfAll(Operation.class, select);
        @SuppressWarnings("null")
        final /*@Thrown*/ org.eclipse.ocl.pivot.internal.lookup.@org.eclipse.jdt.annotation.NonNull LookupEnvironment inner = context.addElements(ECORE_select);
        final /*@Thrown*/ boolean hasFinalResult = inner.hasFinalResult();
        /*@Thrown*/ org.eclipse.ocl.pivot.internal.lookup.@org.eclipse.jdt.annotation.Nullable LookupEnvironment symbol_4;
        if (hasFinalResult) {
            symbol_4 = inner;
        }
        else {
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull SetValue oclAsSet = OclAnyOclAsSetOperation.INSTANCE.evaluate(executor, SET_CLSSid_DataType, element_0);
            final org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class TYPE_allSuperClasses_1 = executor.getStaticTypeOf(oclAsSet);
            final LibraryIteration.@org.eclipse.jdt.annotation.NonNull LibraryIterationExtension IMPL_allSuperClasses_1 = (LibraryIteration.LibraryIterationExtension)TYPE_allSuperClasses_1.lookupImplementation(standardLibrary, OCLstdlibTables.Operations._Set__closure);
            final @NonNull Object ACC_allSuperClasses_1 = IMPL_allSuperClasses_1.createAccumulatorValue(executor, SET_CLSSid_Class, SET_CLSSid_Class);
            /**
             * Implementation of the iterator body.
             */
            final @NonNull AbstractBinaryOperation BODY_allSuperClasses_1 = new AbstractBinaryOperation()
            {
                /**
                 * _'null' : Set(Class)
                 */
                @Override
                public @Nullable Object evaluate(final @NonNull Executor executor, final @NonNull TypeId typeId, final @Nullable Object oclAsSet, final /*@NonInvalid*/ java.lang.@org.eclipse.jdt.annotation.Nullable Object _1_1) {
                    final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable DataType symbol_0 = (DataType)_1_1;
                    if (symbol_0 == null) {
                        throw new InvalidValueException("Null source for \'Class::superClasses\'");
                    }
                    final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<Class> superClasses = symbol_0.getSuperClasses();
                    final /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull SetValue BOXED_superClasses = idResolver.createSetOfAll(SET_CLSSid_Class, superClasses);
                    return BOXED_superClasses;
                }
            };
            final @NonNull  ExecutorSingleIterationManager MGR_allSuperClasses_1 = new ExecutorSingleIterationManager(executor, SET_CLSSid_Class, BODY_allSuperClasses_1, oclAsSet, ACC_allSuperClasses_1);
            final /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull SetValue allSuperClasses = ClassUtil.nonNullState((SetValue)IMPL_allSuperClasses_1.evaluateIteration(MGR_allSuperClasses_1));
            final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<Property> ownedProperties = element_0.getOwnedProperties();
            final /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull OrderedSetValue BOXED_ownedProperties = idResolver.createOrderedSetOfAll(ORD_CLSSid_Property, ownedProperties);
            /*@Thrown*/ OrderedSetValue.@org.eclipse.jdt.annotation.NonNull Accumulator accumulator_0 = ValueUtil.createOrderedSetAccumulatorValue(ORD_CLSSid_Property);
            @Nullable Iterator<Object> ITERATOR__1_0 = BOXED_ownedProperties.iterator();
            /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull OrderedSetValue select_0;
            while (true) {
                if (!ITERATOR__1_0.hasNext()) {
                    select_0 = accumulator_0;
                    break;
                }
                /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Property _1_0 = (Property)ITERATOR__1_0.next();
                /**
                 * _'not' : Boolean[?]
                 */
                if (_1_0 == null) {
                    throw new InvalidValueException("Null source for \'Feature::isStatic\'");
                }
                final /*@Thrown*/ boolean isStatic_0 = _1_0.isIsStatic();
                final /*@Thrown*/ java.lang.@org.eclipse.jdt.annotation.Nullable Boolean not_0 = BooleanNotOperation.INSTANCE.evaluate(isStatic_0);
                if (not_0 == null) {
                    throw new InvalidValueException("Null body for \'OrderedSet(T).select(OrderedSet.T[?] | Lambda T() : Boolean[1]) : OrderedSet(T)\'");
                }
                //
                if (not_0 == ValueUtil.TRUE_VALUE) {
                    accumulator_0.add(_1_0);
                }
            }
            final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<Property> ECORE_select_0 = ((IdResolver.IdResolverExtension)idResolver).ecoreValueOfAll(Property.class, select_0);
            @SuppressWarnings("null")
            final /*@Thrown*/ org.eclipse.ocl.pivot.internal.lookup.@org.eclipse.jdt.annotation.NonNull LookupEnvironment addElements = context.addElements(ECORE_select_0);
            /*@Thrown*/ BagValue.@org.eclipse.jdt.annotation.NonNull Accumulator accumulator_1 = ValueUtil.createBagAccumulatorValue(BAG_CLSSid_Operation);
            @Nullable Iterator<Object> ITERATOR__1_2 = allSuperClasses.iterator();
            /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull BagValue collect;
            while (true) {
                if (!ITERATOR__1_2.hasNext()) {
                    collect = accumulator_1;
                    break;
                }
                /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Class _1_2 = (Class)ITERATOR__1_2.next();
                /**
                 * _'null' : OrderedSet(Operation)
                 */
                if (_1_2 == null) {
                    throw new InvalidValueException("Null source for \'Class::ownedOperations\'");
                }
                final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<Operation> ownedOperations_0 = _1_2.getOwnedOperations();
                final /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull OrderedSetValue BOXED_ownedOperations_0 = idResolver.createOrderedSetOfAll(ORD_CLSSid_Operation, ownedOperations_0);
                //
                for (Object value : BOXED_ownedOperations_0.flatten().getElements()) {
                    accumulator_1.add(value);
                }
            }
            /*@Thrown*/ BagValue.@org.eclipse.jdt.annotation.NonNull Accumulator accumulator_2 = ValueUtil.createBagAccumulatorValue(BAG_CLSSid_Operation);
            @Nullable Iterator<Object> ITERATOR__1_3 = collect.iterator();
            /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull BagValue select_1;
            while (true) {
                if (!ITERATOR__1_3.hasNext()) {
                    select_1 = accumulator_2;
                    break;
                }
                /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Operation _1_3 = (Operation)ITERATOR__1_3.next();
                /**
                 * _'not' : Boolean[?]
                 */
                if (_1_3 == null) {
                    throw new InvalidValueException("Null source for \'Feature::isStatic\'");
                }
                final /*@Thrown*/ boolean isStatic_1 = _1_3.isIsStatic();
                final /*@Thrown*/ java.lang.@org.eclipse.jdt.annotation.Nullable Boolean not_1 = BooleanNotOperation.INSTANCE.evaluate(isStatic_1);
                if (not_1 == null) {
                    throw new InvalidValueException("Null body for \'Bag(T).select(Bag.T[?] | Lambda T() : Boolean[1]) : Bag(T)\'");
                }
                //
                if (not_1 == ValueUtil.TRUE_VALUE) {
                    accumulator_2.add(_1_3);
                }
            }
            final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<Operation> ECORE_select_1 = ((IdResolver.IdResolverExtension)idResolver).ecoreValueOfAll(Operation.class, select_1);
            @SuppressWarnings("null")
            final /*@Thrown*/ org.eclipse.ocl.pivot.internal.lookup.@org.eclipse.jdt.annotation.NonNull LookupEnvironment inner_0 = addElements.addElements(ECORE_select_1);
            final /*@Thrown*/ boolean hasFinalResult_0 = inner_0.hasFinalResult();
            /*@Thrown*/ org.eclipse.ocl.pivot.internal.lookup.@org.eclipse.jdt.annotation.Nullable LookupEnvironment symbol_3;
            if (hasFinalResult_0) {
                symbol_3 = inner_0;
            }
            else {
                /*@Thrown*/ BagValue.@org.eclipse.jdt.annotation.NonNull Accumulator accumulator_3 = ValueUtil.createBagAccumulatorValue(BAG_CLSSid_Property);
                @Nullable Iterator<Object> ITERATOR__1_5 = allSuperClasses.iterator();
                /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull BagValue collect_0;
                while (true) {
                    if (!ITERATOR__1_5.hasNext()) {
                        collect_0 = accumulator_3;
                        break;
                    }
                    /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Class _1_5 = (Class)ITERATOR__1_5.next();
                    /**
                     * _'null' : OrderedSet(Property)
                     */
                    if (_1_5 == null) {
                        throw new InvalidValueException("Null source for \'Class::ownedProperties\'");
                    }
                    final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<Property> ownedProperties_0 = _1_5.getOwnedProperties();
                    final /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull OrderedSetValue BOXED_ownedProperties_0 = idResolver.createOrderedSetOfAll(ORD_CLSSid_Property, ownedProperties_0);
                    //
                    for (Object value : BOXED_ownedProperties_0.flatten().getElements()) {
                        accumulator_3.add(value);
                    }
                }
                /*@Thrown*/ BagValue.@org.eclipse.jdt.annotation.NonNull Accumulator accumulator_4 = ValueUtil.createBagAccumulatorValue(BAG_CLSSid_Property);
                @Nullable Iterator<Object> ITERATOR__1_6 = collect_0.iterator();
                /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull BagValue select_2;
                while (true) {
                    if (!ITERATOR__1_6.hasNext()) {
                        select_2 = accumulator_4;
                        break;
                    }
                    /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Property _1_6 = (Property)ITERATOR__1_6.next();
                    /**
                     * _'not' : Boolean[?]
                     */
                    if (_1_6 == null) {
                        throw new InvalidValueException("Null source for \'Feature::isStatic\'");
                    }
                    final /*@Thrown*/ boolean isStatic_2 = _1_6.isIsStatic();
                    final /*@Thrown*/ java.lang.@org.eclipse.jdt.annotation.Nullable Boolean not_2 = BooleanNotOperation.INSTANCE.evaluate(isStatic_2);
                    if (not_2 == null) {
                        throw new InvalidValueException("Null body for \'Bag(T).select(Bag.T[?] | Lambda T() : Boolean[1]) : Bag(T)\'");
                    }
                    //
                    if (not_2 == ValueUtil.TRUE_VALUE) {
                        accumulator_4.add(_1_6);
                    }
                }
                final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<Property> ECORE_select_2 = ((IdResolver.IdResolverExtension)idResolver).ecoreValueOfAll(Property.class, select_2);
                @SuppressWarnings("null")
                final /*@Thrown*/ org.eclipse.ocl.pivot.internal.lookup.@org.eclipse.jdt.annotation.NonNull LookupEnvironment inner_1 = context.addElements(ECORE_select_2);
                final /*@Thrown*/ boolean hasFinalResult_1 = inner_1.hasFinalResult();
                /*@Thrown*/ org.eclipse.ocl.pivot.internal.lookup.@org.eclipse.jdt.annotation.Nullable LookupEnvironment symbol_2;
                if (hasFinalResult_1) {
                    symbol_2 = inner_1;
                }
                else {
                    final /*@Thrown*/ org.eclipse.ocl.pivot.internal.lookup.@org.eclipse.jdt.annotation.Nullable LookupEnvironment parentEnv = this.parentEnv(element_0);
                    symbol_2 = parentEnv;
                }
                symbol_3 = symbol_2;
            }
            symbol_4 = symbol_3;
        }
        return symbol_4;
    }
    
    /**
     * visitModel(element : Model[1]) : lookup::LookupEnvironment[?]
     * 
     * _'null' : lookup::LookupEnvironment[1]
     */
    @Override
    public /*@NonInvalid*/ LookupEnvironment visitModel(final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Model element_1) {
        final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<Package> ownedPackages = element_1.getOwnedPackages();
        @SuppressWarnings("null")
        final /*@Thrown*/ org.eclipse.ocl.pivot.internal.lookup.@org.eclipse.jdt.annotation.NonNull LookupEnvironment addElements = context.addElements(ownedPackages);
        final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<Import> ownedImports = element_1.getOwnedImports();
        final /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull OrderedSetValue BOXED_ownedImports = idResolver.createOrderedSetOfAll(ORD_CLSSid_Import, ownedImports);
        /*@Thrown*/ SequenceValue.@org.eclipse.jdt.annotation.NonNull Accumulator accumulator = ValueUtil.createSequenceAccumulatorValue(SEQ_CLSSid_Namespace);
        @Nullable Iterator<Object> ITERATOR__1 = BOXED_ownedImports.iterator();
        /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull SequenceValue collect;
        while (true) {
            if (!ITERATOR__1.hasNext()) {
                collect = accumulator;
                break;
            }
            /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Import _1 = (Import)ITERATOR__1.next();
            /**
             * _'null' : Namespace[1]
             */
            if (_1 == null) {
                throw new InvalidValueException("Null source for \'Import::importedNamespace\'");
            }
            @SuppressWarnings("null")
            final /*@Thrown*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Namespace importedNamespace = _1.getImportedNamespace();
            //
            accumulator.add(importedNamespace);
        }
        final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<Namespace> ECORE_collect = ((IdResolver.IdResolverExtension)idResolver).ecoreValueOfAll(Namespace.class, collect);
        @SuppressWarnings("null")
        final /*@Thrown*/ org.eclipse.ocl.pivot.internal.lookup.@org.eclipse.jdt.annotation.NonNull LookupEnvironment inner = addElements.addElements(ECORE_collect);
        final /*@Thrown*/ boolean hasFinalResult = inner.hasFinalResult();
        /*@Thrown*/ org.eclipse.ocl.pivot.internal.lookup.@org.eclipse.jdt.annotation.Nullable LookupEnvironment symbol_0;
        if (hasFinalResult) {
            symbol_0 = inner;
        }
        else {
            final /*@Thrown*/ org.eclipse.ocl.pivot.internal.lookup.@org.eclipse.jdt.annotation.Nullable LookupEnvironment parentEnv = this.parentEnv(element_1);
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
    public /*@NonInvalid*/ LookupEnvironment visitPackage(final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Package element_2) {
        final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<Class> ownedClasses = element_2.getOwnedClasses();
        @SuppressWarnings("null")
        final /*@Thrown*/ org.eclipse.ocl.pivot.internal.lookup.@org.eclipse.jdt.annotation.NonNull LookupEnvironment inner = context.addElements(ownedClasses);
        final /*@Thrown*/ boolean hasFinalResult = inner.hasFinalResult();
        /*@Thrown*/ org.eclipse.ocl.pivot.internal.lookup.@org.eclipse.jdt.annotation.Nullable LookupEnvironment symbol_0;
        if (hasFinalResult) {
            symbol_0 = inner;
        }
        else {
            final /*@Thrown*/ org.eclipse.ocl.pivot.internal.lookup.@org.eclipse.jdt.annotation.Nullable LookupEnvironment parentEnv = this.parentEnv(element_2);
            symbol_0 = parentEnv;
        }
        return symbol_0;
    }
}
