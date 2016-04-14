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
import org.eclipse.ocl.pivot.Class;
import org.eclipse.ocl.pivot.DataType;
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
import org.eclipse.ocl.pivot.values.SetValue;

public class PivotUnqualifiedPropertyLookupVisitor
	extends AbstractPivotCommonLookupVisitor
{
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull RootPackageId PACKid_$metamodel$ = IdManager.getRootPackageId("$metamodel$");
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull NsURIPackageId PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_2015_s_Lookup = IdManager.getNsURIPackageId("http://www.eclipse.org/ocl/2015/Lookup", null, LookupPackage.eINSTANCE);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull NsURIPackageId PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_2015_s_Pivot = IdManager.getNsURIPackageId("http://www.eclipse.org/ocl/2015/Pivot", null, PivotPackage.eINSTANCE);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull RootPackageId PACKid_java_c_s_s_org_eclipse_ocl_pivot_util = IdManager.getRootPackageId("java://org.eclipse.ocl.pivot.util");
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull RootPackageId PACKid_org_eclipse_ocl_pivot_evaluation = IdManager.getRootPackageId("org.eclipse.ocl.pivot.evaluation");
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull RootPackageId PACKid_org_eclipse_ocl_pivot_ids = IdManager.getRootPackageId("org.eclipse.ocl.pivot.ids");
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_Class = PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_2015_s_Pivot.getClassId("Class", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_Class_0 = PACKid_$metamodel$.getClassId("Class", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_DataType = PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_2015_s_Pivot.getClassId("DataType", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_DataType_0 = PACKid_$metamodel$.getClassId("DataType", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_Enumeration = PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_2015_s_Pivot.getClassId("Enumeration", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_Enumeration_0 = PACKid_$metamodel$.getClassId("Enumeration", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_Executor = PACKid_org_eclipse_ocl_pivot_evaluation.getClassId("Executor", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_IdResolver = PACKid_org_eclipse_ocl_pivot_ids.getClassId("IdResolver", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_LookupEnvironment = PACKid_http_c_s_s_www_eclipse_org_s_ocl_s_2015_s_Lookup.getClassId("LookupEnvironment", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_OclElement = PACKid_$metamodel$.getClassId("OclElement", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_PivotUnqualifiedPropertyLookupVisitor = PACKid_java_c_s_s_org_eclipse_ocl_pivot_util.getClassId("PivotUnqualifiedPropertyLookupVisitor", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_Property = PACKid_$metamodel$.getClassId("Property", 0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull CollectionTypeId BAG_CLSSid_Property = TypeId.BAG.getSpecializedId(CLSSid_Property);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull CollectionTypeId ORD_CLSSid_Property = TypeId.ORDERED_SET.getSpecializedId(CLSSid_Property);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull CollectionTypeId SET_CLSSid_Class = TypeId.SET.getSpecializedId(CLSSid_Class_0);
    public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull CollectionTypeId SET_CLSSid_DataType = TypeId.SET.getSpecializedId(CLSSid_DataType_0);
    
    protected final /*@Thrown*/ org.eclipse.ocl.pivot.evaluation.@org.eclipse.jdt.annotation.NonNull Executor executor;
    protected final /*@Thrown*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull IdResolver idResolver;
    protected /*@Thrown*/ java.lang.@org.eclipse.jdt.annotation.Nullable Object child;
    
    public PivotUnqualifiedPropertyLookupVisitor(@NonNull LookupEnvironment context) {
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
     * visitClass(element : Class[1]) : lookup::LookupEnvironment[?]
     * 
     * _'null' : lookup::LookupEnvironment[1]
     */
    @Override
    public /*@NonInvalid*/ LookupEnvironment visitClass(final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class element) {
        final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull IdResolver idResolver = executor.getIdResolver();
        final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull StandardLibrary standardLibrary = idResolver.getStandardLibrary();
        final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<Property> ownedProperties = element.getOwnedProperties();
        final /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull OrderedSetValue BOXED_ownedProperties = idResolver.createOrderedSetOfAll(ORD_CLSSid_Property, ownedProperties);
        /*@Thrown*/ OrderedSetValue.@org.eclipse.jdt.annotation.NonNull Accumulator accumulator = ValueUtil.createOrderedSetAccumulatorValue(ORD_CLSSid_Property);
        @Nullable Iterator<Object> ITERATOR__1 = BOXED_ownedProperties.iterator();
        /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull OrderedSetValue select;
        while (true) {
            if (!ITERATOR__1.hasNext()) {
                select = accumulator;
                break;
            }
            /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Property _1 = (Property)ITERATOR__1.next();
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
        final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<Property> ECORE_select = ((IdResolver.IdResolverExtension)idResolver).ecoreValueOfAll(Property.class, select);
        @SuppressWarnings("null")
        final /*@Thrown*/ org.eclipse.ocl.pivot.internal.lookup.@org.eclipse.jdt.annotation.NonNull LookupEnvironment inner = context.addElements(ECORE_select);
        final /*@Thrown*/ boolean hasFinalResult = inner.hasFinalResult();
        /*@Thrown*/ org.eclipse.ocl.pivot.internal.lookup.@org.eclipse.jdt.annotation.Nullable LookupEnvironment symbol_2;
        if (hasFinalResult) {
            symbol_2 = inner;
        }
        else {
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull SetValue oclAsSet = OclAnyOclAsSetOperation.INSTANCE.evaluate(executor, SET_CLSSid_Class, element);
            final org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class TYPE_allSuperClasses_0 = executor.getStaticTypeOf(oclAsSet);
            final LibraryIteration.@org.eclipse.jdt.annotation.NonNull LibraryIterationExtension IMPL_allSuperClasses_0 = (LibraryIteration.LibraryIterationExtension)TYPE_allSuperClasses_0.lookupImplementation(standardLibrary, OCLstdlibTables.Operations._Set__closure);
            final @NonNull Object ACC_allSuperClasses_0 = IMPL_allSuperClasses_0.createAccumulatorValue(executor, SET_CLSSid_Class, SET_CLSSid_Class);
            /**
             * Implementation of the iterator body.
             */
            final @NonNull AbstractBinaryOperation BODY_allSuperClasses_0 = new AbstractBinaryOperation()
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
            final @NonNull  ExecutorSingleIterationManager MGR_allSuperClasses_0 = new ExecutorSingleIterationManager(executor, SET_CLSSid_Class, BODY_allSuperClasses_0, oclAsSet, ACC_allSuperClasses_0);
            final /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull SetValue allSuperClasses = ClassUtil.nonNullState((SetValue)IMPL_allSuperClasses_0.evaluateIteration(MGR_allSuperClasses_0));
            /*@Thrown*/ BagValue.@org.eclipse.jdt.annotation.NonNull Accumulator accumulator_0 = ValueUtil.createBagAccumulatorValue(BAG_CLSSid_Property);
            @Nullable Iterator<Object> ITERATOR__1_1 = allSuperClasses.iterator();
            /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull BagValue collect;
            while (true) {
                if (!ITERATOR__1_1.hasNext()) {
                    collect = accumulator_0;
                    break;
                }
                /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Class _1_1 = (Class)ITERATOR__1_1.next();
                /**
                 * _'null' : OrderedSet(Property)
                 */
                if (_1_1 == null) {
                    throw new InvalidValueException("Null source for \'Class::ownedProperties\'");
                }
                final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<Property> ownedProperties_0 = _1_1.getOwnedProperties();
                final /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull OrderedSetValue BOXED_ownedProperties_0 = idResolver.createOrderedSetOfAll(ORD_CLSSid_Property, ownedProperties_0);
                //
                for (Object value : BOXED_ownedProperties_0.flatten().getElements()) {
                    accumulator_0.add(value);
                }
            }
            /*@Thrown*/ BagValue.@org.eclipse.jdt.annotation.NonNull Accumulator accumulator_1 = ValueUtil.createBagAccumulatorValue(BAG_CLSSid_Property);
            @Nullable Iterator<Object> ITERATOR__1_2 = collect.iterator();
            /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull BagValue select_0;
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
                    throw new InvalidValueException("Null body for \'Bag(T).select(Bag.T[?] | Lambda T() : Boolean[1]) : Bag(T)\'");
                }
                //
                if (not_0 == ValueUtil.TRUE_VALUE) {
                    accumulator_1.add(_1_2);
                }
            }
            final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<Property> ECORE_select_0 = ((IdResolver.IdResolverExtension)idResolver).ecoreValueOfAll(Property.class, select_0);
            @SuppressWarnings("null")
            final /*@Thrown*/ org.eclipse.ocl.pivot.internal.lookup.@org.eclipse.jdt.annotation.NonNull LookupEnvironment inner_0 = context.addElements(ECORE_select_0);
            final /*@Thrown*/ boolean hasFinalResult_0 = inner_0.hasFinalResult();
            /*@Thrown*/ org.eclipse.ocl.pivot.internal.lookup.@org.eclipse.jdt.annotation.Nullable LookupEnvironment symbol_1;
            if (hasFinalResult_0) {
                symbol_1 = inner_0;
            }
            else {
                final /*@Thrown*/ org.eclipse.ocl.pivot.internal.lookup.@org.eclipse.jdt.annotation.Nullable LookupEnvironment parentEnv = this.parentEnv(element);
                symbol_1 = parentEnv;
            }
            symbol_2 = symbol_1;
        }
        return symbol_2;
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
        final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<Property> ownedProperties = element_0.getOwnedProperties();
        final /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull OrderedSetValue BOXED_ownedProperties = idResolver.createOrderedSetOfAll(ORD_CLSSid_Property, ownedProperties);
        /*@Thrown*/ OrderedSetValue.@org.eclipse.jdt.annotation.NonNull Accumulator accumulator = ValueUtil.createOrderedSetAccumulatorValue(ORD_CLSSid_Property);
        @Nullable Iterator<Object> ITERATOR__1 = BOXED_ownedProperties.iterator();
        /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull OrderedSetValue select;
        while (true) {
            if (!ITERATOR__1.hasNext()) {
                select = accumulator;
                break;
            }
            /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Property _1 = (Property)ITERATOR__1.next();
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
        final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<Property> ECORE_select = ((IdResolver.IdResolverExtension)idResolver).ecoreValueOfAll(Property.class, select);
        @SuppressWarnings("null")
        final /*@Thrown*/ org.eclipse.ocl.pivot.internal.lookup.@org.eclipse.jdt.annotation.NonNull LookupEnvironment inner = context.addElements(ECORE_select);
        final /*@Thrown*/ boolean hasFinalResult = inner.hasFinalResult();
        /*@Thrown*/ org.eclipse.ocl.pivot.internal.lookup.@org.eclipse.jdt.annotation.Nullable LookupEnvironment symbol_2;
        if (hasFinalResult) {
            symbol_2 = inner;
        }
        else {
            final /*@NonInvalid*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull SetValue oclAsSet = OclAnyOclAsSetOperation.INSTANCE.evaluate(executor, SET_CLSSid_DataType, element_0);
            final org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Class TYPE_allSuperClasses_0 = executor.getStaticTypeOf(oclAsSet);
            final LibraryIteration.@org.eclipse.jdt.annotation.NonNull LibraryIterationExtension IMPL_allSuperClasses_0 = (LibraryIteration.LibraryIterationExtension)TYPE_allSuperClasses_0.lookupImplementation(standardLibrary, OCLstdlibTables.Operations._Set__closure);
            final @NonNull Object ACC_allSuperClasses_0 = IMPL_allSuperClasses_0.createAccumulatorValue(executor, SET_CLSSid_Class, SET_CLSSid_Class);
            /**
             * Implementation of the iterator body.
             */
            final @NonNull AbstractBinaryOperation BODY_allSuperClasses_0 = new AbstractBinaryOperation()
            {
                /**
                 * _'null' : Set(Class)
                 */
                @Override
                public @Nullable Object evaluate(final @NonNull Executor executor, final @NonNull TypeId typeId, final @Nullable Object oclAsSet, final /*@NonInvalid*/ java.lang.@org.eclipse.jdt.annotation.Nullable Object _1_0) {
                    final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable DataType symbol_0 = (DataType)_1_0;
                    if (symbol_0 == null) {
                        throw new InvalidValueException("Null source for \'Class::superClasses\'");
                    }
                    final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<Class> superClasses = symbol_0.getSuperClasses();
                    final /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull SetValue BOXED_superClasses = idResolver.createSetOfAll(SET_CLSSid_Class, superClasses);
                    return BOXED_superClasses;
                }
            };
            final @NonNull  ExecutorSingleIterationManager MGR_allSuperClasses_0 = new ExecutorSingleIterationManager(executor, SET_CLSSid_Class, BODY_allSuperClasses_0, oclAsSet, ACC_allSuperClasses_0);
            final /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull SetValue allSuperClasses = ClassUtil.nonNullState((SetValue)IMPL_allSuperClasses_0.evaluateIteration(MGR_allSuperClasses_0));
            /*@Thrown*/ BagValue.@org.eclipse.jdt.annotation.NonNull Accumulator accumulator_0 = ValueUtil.createBagAccumulatorValue(BAG_CLSSid_Property);
            @Nullable Iterator<Object> ITERATOR__1_1 = allSuperClasses.iterator();
            /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull BagValue collect;
            while (true) {
                if (!ITERATOR__1_1.hasNext()) {
                    collect = accumulator_0;
                    break;
                }
                /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Class _1_1 = (Class)ITERATOR__1_1.next();
                /**
                 * _'null' : OrderedSet(Property)
                 */
                if (_1_1 == null) {
                    throw new InvalidValueException("Null source for \'Class::ownedProperties\'");
                }
                final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<Property> ownedProperties_0 = _1_1.getOwnedProperties();
                final /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull OrderedSetValue BOXED_ownedProperties_0 = idResolver.createOrderedSetOfAll(ORD_CLSSid_Property, ownedProperties_0);
                //
                for (Object value : BOXED_ownedProperties_0.flatten().getElements()) {
                    accumulator_0.add(value);
                }
            }
            /*@Thrown*/ BagValue.@org.eclipse.jdt.annotation.NonNull Accumulator accumulator_1 = ValueUtil.createBagAccumulatorValue(BAG_CLSSid_Property);
            @Nullable Iterator<Object> ITERATOR__1_2 = collect.iterator();
            /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull BagValue select_0;
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
                    throw new InvalidValueException("Null body for \'Bag(T).select(Bag.T[?] | Lambda T() : Boolean[1]) : Bag(T)\'");
                }
                //
                if (not_0 == ValueUtil.TRUE_VALUE) {
                    accumulator_1.add(_1_2);
                }
            }
            final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<Property> ECORE_select_0 = ((IdResolver.IdResolverExtension)idResolver).ecoreValueOfAll(Property.class, select_0);
            @SuppressWarnings("null")
            final /*@Thrown*/ org.eclipse.ocl.pivot.internal.lookup.@org.eclipse.jdt.annotation.NonNull LookupEnvironment inner_0 = context.addElements(ECORE_select_0);
            final /*@Thrown*/ boolean hasFinalResult_0 = inner_0.hasFinalResult();
            /*@Thrown*/ org.eclipse.ocl.pivot.internal.lookup.@org.eclipse.jdt.annotation.Nullable LookupEnvironment symbol_1;
            if (hasFinalResult_0) {
                symbol_1 = inner_0;
            }
            else {
                final /*@Thrown*/ org.eclipse.ocl.pivot.internal.lookup.@org.eclipse.jdt.annotation.Nullable LookupEnvironment parentEnv = this.parentEnv(element_0);
                symbol_1 = parentEnv;
            }
            symbol_2 = symbol_1;
        }
        return symbol_2;
    }
    
    /**
     * visitEnumeration(element : Enumeration[1]) : lookup::LookupEnvironment[?]
     * 
     * _'null' : lookup::LookupEnvironment[1]
     */
    @Override
    public /*@NonInvalid*/ LookupEnvironment visitEnumeration(final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Enumeration element_1) {
        final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<Property> ownedProperties = element_1.getOwnedProperties();
        final /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull OrderedSetValue BOXED_ownedProperties = idResolver.createOrderedSetOfAll(ORD_CLSSid_Property, ownedProperties);
        /*@Thrown*/ OrderedSetValue.@org.eclipse.jdt.annotation.NonNull Accumulator accumulator = ValueUtil.createOrderedSetAccumulatorValue(ORD_CLSSid_Property);
        @Nullable Iterator<Object> ITERATOR__1 = BOXED_ownedProperties.iterator();
        /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull OrderedSetValue select;
        while (true) {
            if (!ITERATOR__1.hasNext()) {
                select = accumulator;
                break;
            }
            /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Property _1 = (Property)ITERATOR__1.next();
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
        final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<Property> ECORE_select = ((IdResolver.IdResolverExtension)idResolver).ecoreValueOfAll(Property.class, select);
        @SuppressWarnings("null")
        final /*@Thrown*/ org.eclipse.ocl.pivot.internal.lookup.@org.eclipse.jdt.annotation.NonNull LookupEnvironment inner = context.addElements(ECORE_select);
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
}
