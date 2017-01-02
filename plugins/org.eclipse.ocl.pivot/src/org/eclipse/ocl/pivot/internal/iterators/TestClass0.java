package org.eclipse.ocl.pivot.internal.iterators;

import org.eclipse.ocl.pivot.Package;
import org.eclipse.ocl.pivot.ids.IdManager;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.library.AbstractUnaryOperation;
import org.eclipse.ocl.pivot.library.collection.CollectionExcludingOperation;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.CollectionValue;
import org.eclipse.ocl.pivot.values.InvalidValueException;

/**
 * TestClass0 provides the Java implementation for
 *
 * ownedPackages?->select(p | p.name <> 'bob')
 */
public class TestClass0 extends AbstractUnaryOperation
{
	public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull RootPackageId PACKid_$metamodel$ = IdManager.getRootPackageId("$metamodel$");
	public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull ClassId CLSSid_Package = PACKid_$metamodel$.getClassId("Package", 0);
	public static final /*@NonInvalid*/ java.lang.@org.eclipse.jdt.annotation.NonNull String STR_bob = "bob";
	public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull CollectionTypeId SET_CLSSid_Package = TypeId.SET.getSpecializedId(CLSSid_Package);

	@Override
	public /*@NonInvalid*/ CollectionValue evaluate(final /*@NonInvalid*/ org.eclipse.ocl.pivot.evaluation.@org.eclipse.jdt.annotation.NonNull Executor executor, final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull TypeId typeId, final /*@NonInvalid*/ java.lang.@org.eclipse.jdt.annotation.Nullable Object self_0) {
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@org.eclipse.jdt.annotation.NonNull IdResolver idResolver = executor.getIdResolver();
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.Nullable Package symbol_0 = (Package)self_0;
		if (symbol_0 == null) {
			throw new InvalidValueException("Null source for \'Package::ownedPackages\'");
		}
		final /*@Thrown*/ java.util.@org.eclipse.jdt.annotation.NonNull List<Package> ownedPackages = symbol_0.getOwnedPackages();
		final /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull CollectionValue BOXED_ownedPackages = idResolver.createSetOfAll(SET_CLSSid_Package, ownedPackages);
		final /*@Thrown*/ org.eclipse.ocl.pivot.values.@org.eclipse.jdt.annotation.NonNull CollectionValue safe_null_sources = CollectionExcludingOperation.INSTANCE.evaluate(BOXED_ownedPackages, (Object)null);
		SelectIterator selectIterator = new SelectIterator(SET_CLSSid_Package, safe_null_sources) {
			@Override
			protected boolean body(Object next) {
				/*@NonInvalid*/ @SuppressWarnings("null")
				org.eclipse.ocl.pivot.@org.eclipse.jdt.annotation.NonNull Package p = (org.eclipse.ocl.pivot.Package)next;
				/**
				 * p.name <> 'bob'
				 */
				final /*@Thrown*/ java.lang.@org.eclipse.jdt.annotation.Nullable String name = p.getName();
				final /*@Thrown*/ boolean ne = !STR_bob.equals(name);
				//
				return ne == ValueUtil.TRUE_VALUE;
			}
		};
		return selectIterator;
	}
}
