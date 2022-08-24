/*******************************************************************************
 * Copyright (c) 2012, 2019 CEA LIST and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink(CEA LIST) - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.java;

import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.ids.BindingsId;
import org.eclipse.ocl.pivot.ids.ClassId;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.ids.DataTypeId;
import org.eclipse.ocl.pivot.ids.ElementId;
import org.eclipse.ocl.pivot.ids.EnumerationId;
import org.eclipse.ocl.pivot.ids.EnumerationLiteralId;
import org.eclipse.ocl.pivot.ids.IdManager;
import org.eclipse.ocl.pivot.ids.IdVisitor;
import org.eclipse.ocl.pivot.ids.LambdaTypeId;
import org.eclipse.ocl.pivot.ids.MapTypeId;
import org.eclipse.ocl.pivot.ids.NestedPackageId;
import org.eclipse.ocl.pivot.ids.NsURIPackageId;
import org.eclipse.ocl.pivot.ids.OclInvalidTypeId;
import org.eclipse.ocl.pivot.ids.OclVoidTypeId;
import org.eclipse.ocl.pivot.ids.OperationId;
import org.eclipse.ocl.pivot.ids.PrimitiveTypeId;
import org.eclipse.ocl.pivot.ids.PropertyId;
import org.eclipse.ocl.pivot.ids.RootPackageId;
import org.eclipse.ocl.pivot.ids.SpecializedId;
import org.eclipse.ocl.pivot.ids.TemplateBinding;
import org.eclipse.ocl.pivot.ids.TemplateParameterId;
import org.eclipse.ocl.pivot.ids.TemplateableTypeId;
import org.eclipse.ocl.pivot.ids.TuplePartId;
import org.eclipse.ocl.pivot.ids.TupleTypeId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.ids.UnspecifiedId;
import org.eclipse.ocl.pivot.ids.WildcardId;
import org.eclipse.ocl.pivot.internal.manager.PivotMetamodelManager;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.IntegerValue;
import org.eclipse.ocl.pivot.values.NullValue;
import org.eclipse.ocl.pivot.values.UnlimitedNaturalValue;

/**
 * An Id2JavaExpressionVisitor appends the expression body of an Id declaration.
 */
public class Id2JavaExpressionVisitor implements IdVisitor<@Nullable Object>
{
	protected final @NonNull JavaStream js;
	protected final @NonNull PivotMetamodelManager metamodelManager;

	public Id2JavaExpressionVisitor(@NonNull JavaStream js) {
		this.js = js;
		this.metamodelManager = js.getCodeGenerator().getEnvironmentFactory().getMetamodelManager();
	}

	protected void appendSpecializedId(@NonNull TypeId typeId) {
		js.append(".getSpecializedId(");
		BindingsId templateBindings = ((SpecializedId)typeId).getTemplateBindings();
		for (int i = 0; i < templateBindings.elementIdSize(); i++) {
			if (i > 0) {
				js.append(", ");
			}
			ElementId elementId = ClassUtil.nonNullModel(templateBindings.getElementId(i));
			js.appendIdReference(elementId);
		}
		for (int i = 0; i < templateBindings.valuesSize(); i++) {
			js.append(", ");
			Object value = ClassUtil.nonNullModel(templateBindings.getValue(i));
			if (value instanceof Boolean) {				// FIXME Re-use constant functionality
				js.appendBooleanString((boolean) value);
			}
			else if (value instanceof NullValue) {
				js.append("null");
			}
			else if (value instanceof IntegerValue) {
				IntegerValue integerValue = (IntegerValue)value;
				js.appendClassReference(null, ValueUtil.class);
				if (ValueUtil.ZERO_VALUE.equals(integerValue)) {
					js.append(".ZERO_VALUE");
				}
				else if (ValueUtil.ONE_VALUE.equals(integerValue)) {
					js.append(".ONE_VALUE");
				}
				else {
					js.append(".integerValueOf(" + integerValue + ")");
				}
			}
			else if (value instanceof UnlimitedNaturalValue) {
				UnlimitedNaturalValue unlimitedNaturalValue = (UnlimitedNaturalValue)value;
				js.appendClassReference(null, ValueUtil.class);
				if (ValueUtil.UNLIMITED_VALUE.equals(unlimitedNaturalValue)) {
					js.append(".UNLIMITED_VALUE");
				}
				else if (ValueUtil.UNLIMITED_ZERO_VALUE.equals(unlimitedNaturalValue)) {
					js.append(".UNLIMITED_ZERO_VALUE");
				}
				else if (ValueUtil.UNLIMITED_ONE_VALUE.equals(unlimitedNaturalValue)) {
					js.append(".UNLIMITED_ONE_VALUE");
				}
				else {
					js.append(".unlimitedNaturalValueValueOf(" + unlimitedNaturalValue + ")");
				}
			}
			else {
				js.append(value.toString());
			}
		}
		js.append(")");
	}

	@Override
	public @Nullable Object visitClassId(@NonNull ClassId id) {
		js.appendIdReference(id.getParent());
		js.append(".getClassId(");
		js.appendString(id.getName());
		js.append(", " + id.getTemplateParameters() + ")");
		return null;
	}

	@Override
	public @Nullable Object visitCollectionTypeId(@NonNull CollectionTypeId id) {
		js.appendClassReference(null, TypeId.class);
		CollectionTypeId generalizedId = id.getGeneralizedId();
		String idName = generalizedId.getLiteralName();
		if (idName == null) {
			idName = "COLLECTION";
		}
		js.append("." + idName);
		if (id instanceof SpecializedId) {
			appendSpecializedId(id);
		}
		return null;
	}

	@Override
	public @Nullable Object visitDataTypeId(@NonNull DataTypeId id) {
		js.appendIdReference(id.getParent());
		js.append(".getDataTypeId(");
		js.appendString(id.getName());
		js.append(", " + id.getTemplateParameters() + ")");
		return null;
	}

	@Override
	public @Nullable Object visitEnumerationId(@NonNull EnumerationId id) {
		js.appendIdReference(id.getParent());
		js.append(".getEnumerationId(");
		js.appendString(id.getName());
		js.append(")");
		return null;
	}

	@Override
	public @Nullable Object visitEnumerationLiteralId(@NonNull EnumerationLiteralId id) {
		js.appendIdReference(id.getParentId());
		js.append(".getEnumerationLiteralId(");
		js.appendString(id.getName());
		js.append(")");
		return null;
	}

	@Override
	public @Nullable Object visitInvalidId(@NonNull OclInvalidTypeId id) {
		js.appendClassReference(null, TypeId.class);
		js.append(".");
		js.append(id.getLiteralName());
		return null;
	}

	@Override
	public @Nullable Object visitLambdaTypeId(@NonNull LambdaTypeId id) {
		// TODO Auto-generated method stub
		return visiting(id);
	}

	@Override
	public @Nullable Object visitMapTypeId(@NonNull MapTypeId id) {
		js.appendClassReference(null, TypeId.class);
		MapTypeId generalizedId = id.getGeneralizedId();
		String idName = generalizedId.getLiteralName();
		if (idName == null) {
			idName = "MAP";
		}
		js.append("." + idName);
		if (id instanceof SpecializedId) {
			appendSpecializedId(id);
		}
		return null;
	}

	@Override
	public @Nullable Object visitNestedPackageId(@NonNull NestedPackageId id) {
		js.appendIdReference(id.getParent());
		js.append(".getNestedPackageId(");
		js.appendString(id.getName());
		js.append(")");
		return null;
	}

	@Override
	public @Nullable Object visitNsURIPackageId(@NonNull NsURIPackageId id) {
		String nsURI = id.getNsURI();
		String nsPrefix = id.getNsPrefix();
		GenPackage genPackage = metamodelManager.getGenPackage(nsURI);
		js.appendClassReference(null, IdManager.class);
		js.append(".getNsURIPackageId(");
		js.appendString(nsURI);
		js.append(", ");
		if (nsPrefix != null) {
			js.appendString(nsPrefix);
		}
		else {
			js.append("null");
		}
		js.append(", ");
		if (genPackage != null) {
			js.appendClassReference(null, genPackage.getQualifiedPackageInterfaceName());
			js.append(".eINSTANCE");
		}
		else {
			js.append("null");
		}
		js.append(")");
		return null;
	}

	@Override
	public @Nullable Object visitNullId(@NonNull OclVoidTypeId id) {
		js.appendClassReference(null, TypeId.class);
		js.append(".");
		js.append(id.getLiteralName());
		return null;
	}

	@Override
	public @Nullable Object visitOperationId(@NonNull OperationId id) {
		js.appendIdReference(id.getParent());
		js.append(".getOperationId(" + id.getTemplateParameters() + ", ");
		js.appendString(id.getName());
		js.append(", ");
		js.appendClassReference(null, IdManager.class);
		js.append(".getParametersId(");
		boolean isFirst = true;
		for (@NonNull TypeId parameterId : id.getParametersId()) {
			if (!isFirst) {
				js.append(", ");
			}
			js.appendIdReference(parameterId);
			isFirst = false;
		}
		js.append("))");
		return null;
	}

	@Override
	public @Nullable Object visitPrimitiveTypeId(@NonNull PrimitiveTypeId id) {
		js.appendClassReference(null, TypeId.class);
		js.append(".");
		js.append(id.getLiteralName());
		return null;
	}

	@Override
	public @Nullable Object visitPropertyId(@NonNull PropertyId id) {
		js.appendIdReference(id.getParent());
		js.append(".getPropertyId(");
		js.appendString(id.getName());
		js.append(")");
		return null;
	}

	@Override
	public @Nullable Object visitRootPackageId(@NonNull RootPackageId id) {
		js.appendClassReference(null, IdManager.class);
		js.append(".getRootPackageId(");
		js.appendString(id.getName());
		js.append(")");
		return null;
	}

	@Override
	public @Nullable Object visitTemplateBinding(@NonNull TemplateBinding id) {
		// TODO Auto-generated method stub
		return visiting(id);
	}

	@Override
	public @Nullable Object visitTemplateParameterId(@NonNull TemplateParameterId id) {
		js.appendClassReference(null, IdManager.class);
		js.append(".getTemplateParameterId(" + id.getIndex() + ")");
		return null;
	}

	@Override
	public @Nullable Object visitTemplateableTypeId(@NonNull TemplateableTypeId id) {
		// TODO Auto-generated method stub
		return visiting(id);
	}

	@Override
	public @Nullable Object visitTuplePartId(@NonNull TuplePartId id) {
		js.appendClassReference(null, IdManager.class);
		js.append(".getTuplePartId(" + id.getIndex() + ", ");
		js.appendString(id.getName());
		js.append(", ");
		js.appendIdReference(id.getTypeId());
		js.append(")");
		return null;
	}

	@Override
	public @Nullable Object visitTupleTypeId(@NonNull TupleTypeId id) {
		js.appendClassReference(null, IdManager.class);
		js.append(".getTupleTypeId(");
		js.appendString(id.getName());
		for (TuplePartId partId : id.getPartIds()) {
			js.append(", ");
			js.appendIdReference(partId);
		}
		js.append(")");
		return null;
	}

	@Override
	public @Nullable Object visitUnspecifiedId(@NonNull UnspecifiedId id) {
		// TODO Auto-generated method stub
		return visiting(id);
	}

	@Override
	public @Nullable Object visitWildcardId(@NonNull WildcardId id) {
		js.appendClassReference(null, IdManager.class);
		js.append(".getWildcardId()");
		return null;
	}

	public @Nullable Object visiting(@NonNull ElementId id) {
		throw new UnsupportedOperationException(getClass().getSimpleName() + ": " + id.getClass().getName());
	}
}
