/*******************************************************************************
 * Copyright (c) 2008, 2024 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.parser;

import java.util.List;
import java.util.Set;

import org.eclipse.ocl.expressions.AssociationClassCallExp;
import org.eclipse.ocl.expressions.BooleanLiteralExp;
import org.eclipse.ocl.expressions.CollectionItem;
import org.eclipse.ocl.expressions.CollectionKind;
import org.eclipse.ocl.expressions.CollectionLiteralExp;
import org.eclipse.ocl.expressions.CollectionRange;
import org.eclipse.ocl.expressions.EnumLiteralExp;
import org.eclipse.ocl.expressions.IfExp;
import org.eclipse.ocl.expressions.IntegerLiteralExp;
import org.eclipse.ocl.expressions.InvalidLiteralExp;
import org.eclipse.ocl.expressions.IterateExp;
import org.eclipse.ocl.expressions.IteratorExp;
import org.eclipse.ocl.expressions.LetExp;
import org.eclipse.ocl.expressions.MessageExp;
import org.eclipse.ocl.expressions.NullLiteralExp;
import org.eclipse.ocl.expressions.OperationCallExp;
import org.eclipse.ocl.expressions.PropertyCallExp;
import org.eclipse.ocl.expressions.RealLiteralExp;
import org.eclipse.ocl.expressions.StateExp;
import org.eclipse.ocl.expressions.StringLiteralExp;
import org.eclipse.ocl.expressions.TupleLiteralExp;
import org.eclipse.ocl.expressions.TupleLiteralPart;
import org.eclipse.ocl.expressions.TypeExp;
import org.eclipse.ocl.expressions.UnlimitedNaturalLiteralExp;
import org.eclipse.ocl.expressions.UnspecifiedValueExp;
import org.eclipse.ocl.expressions.Variable;
import org.eclipse.ocl.expressions.VariableExp;
import org.eclipse.ocl.types.BagType;
import org.eclipse.ocl.types.CollectionType;
import org.eclipse.ocl.types.MessageType;
import org.eclipse.ocl.types.OrderedSetType;
import org.eclipse.ocl.types.SequenceType;
import org.eclipse.ocl.types.SetType;
import org.eclipse.ocl.types.TupleType;
import org.eclipse.ocl.types.TypeType;
import org.eclipse.ocl.util.ObjectUtil;
import org.eclipse.ocl.utilities.OCLFactory;
import org.eclipse.ocl.utilities.TypedElement;

/**
 * A wrapper for {@link OCLFactory}s that records a history of the objects
 * created by it.  This is useful in case any objects that were created ended
 * up not being used, because an error occurred in parsing, and thus need to
 * be {@linkplain ObjectUtil#dispose(Object) disposed}.
 * 
 * @author Christian W. Damus (cdamus)
 * 
 * @since 3.1
 */
public class OCLFactoryWithHistory implements OCLFactory {

    protected final OCLFactory delegate;
    private List<Object> history = new java.util.ArrayList<Object>();
    private Set<TypedElement<?>> errorNodes = new java.util.HashSet<TypedElement<?>>();
    
    private boolean disposable;
    
    public OCLFactoryWithHistory(OCLFactory delegate) {
        this.delegate = delegate;
    }

    public void clear() {
        if (isDisposable()) {
            for (Object next : history) {
                ObjectUtil.dispose(next);
            }
        }
        
        history.clear();
        errorNodes.clear();
    }
    
    boolean isDisposable() {
        return disposable;
    }
    
    void setDisposable() {
        disposable = true;
    }
    
    protected <T> T record(T object) {
        history.add(object);
        return object;
    }
    
    void markAsErrorNode(TypedElement<?> expr) {
    	errorNodes.add(expr);
    }
    
    boolean isErrorNode(TypedElement<?> expr) {
    	return errorNodes.contains(expr);
    }
    
    @Override
	public <C, P> AssociationClassCallExp<C, P> createAssociationClassCallExp() {
        return record(delegate.<C, P>createAssociationClassCallExp());
    }

    @Override
	public <C, O> BagType<C, O> createBagType(C elementType) {
        return record(delegate.<C, O>createBagType(elementType));
    }

    @Override
	public <C> BooleanLiteralExp<C> createBooleanLiteralExp() {
        return record(delegate.<C>createBooleanLiteralExp());
    }

    @Override
	public <C> CollectionItem<C> createCollectionItem() {
        return record(delegate.<C>createCollectionItem());
    }

    @Override
	public <C> CollectionLiteralExp<C> createCollectionLiteralExp() {
        return record(delegate.<C>createCollectionLiteralExp());
    }

    @Override
	public <C> CollectionRange<C> createCollectionRange() {
        return record(delegate.<C>createCollectionRange());
    }

    @Override
	public <C, O> CollectionType<C, O> createCollectionType(C elementType) {
        return record(delegate.<C, O>createCollectionType(elementType));
    }

    @Override
	public <C, O> CollectionType<C, O> createCollectionType(
            CollectionKind kind, C elementType) {
        return record(delegate.<C, O>createCollectionType(kind, elementType));
    }

    @Override
	public <C, EL> EnumLiteralExp<C, EL> createEnumLiteralExp() {
        return record(delegate.<C, EL>createEnumLiteralExp());
    }

    @Override
	public <C> IfExp<C> createIfExp() {
        return record(delegate.<C>createIfExp());
    }

    @Override
	public <C> IntegerLiteralExp<C> createIntegerLiteralExp() {
        return record(delegate.<C>createIntegerLiteralExp());
    }

    @Override
	public <C> InvalidLiteralExp<C> createInvalidLiteralExp() {
        return record(delegate.<C>createInvalidLiteralExp());
    }

    @Override
	public <C, PM> IterateExp<C, PM> createIterateExp() {
        return record(delegate.<C, PM>createIterateExp());
    }

    @Override
	public <C, PM> IteratorExp<C, PM> createIteratorExp() {
        return record(delegate.<C, PM>createIteratorExp());
    }

    @Override
	public <C, PM> LetExp<C, PM> createLetExp() {
        return record(delegate.<C, PM>createLetExp());
    }

    @Override
	public <C, COA, SSA> MessageExp<C, COA, SSA> createMessageExp() {
        return record(delegate.<C, COA, SSA>createMessageExp());
    }

    @Override
	public <C> NullLiteralExp<C> createNullLiteralExp() {
        return record(delegate.<C>createNullLiteralExp());
    }

    @Override
	public <C, O> OperationCallExp<C, O> createOperationCallExp() {
        return record(delegate.<C, O>createOperationCallExp());
    }

    @Override
	public <C, O, P> MessageType<C, O, P> createOperationMessageType(O operation) {
        return record(delegate.<C, O, P>createOperationMessageType(operation));
    }

    @Override
	public <C, O> OrderedSetType<C, O> createOrderedSetType(C elementType) {
        return record(delegate.<C, O>createOrderedSetType(elementType));
    }

    @Override
	public <C, P> PropertyCallExp<C, P> createPropertyCallExp() {
        return record(delegate.<C, P>createPropertyCallExp());
    }

    @Override
	public <C> RealLiteralExp<C> createRealLiteralExp() {
        return record(delegate.<C>createRealLiteralExp());
    }

    @Override
	public <C, O> SequenceType<C, O> createSequenceType(C elementType) {
        return record(delegate.<C, O>createSequenceType(elementType));
    }

    @Override
	public <C, O> SetType<C, O> createSetType(C elementType) {
        return record(delegate.<C, O>createSetType(elementType));
    }

    @Override
	public <C, O, P> MessageType<C, O, P> createSignalMessageType(C signal) {
        return record(delegate.<C, O, P>createSignalMessageType(signal));
    }

    @Override
	public <C, S> StateExp<C, S> createStateExp() {
        return record(delegate.<C, S>createStateExp());
    }

    @Override
	public <C> StringLiteralExp<C> createStringLiteralExp() {
        return record(delegate.<C>createStringLiteralExp());
    }

    @Override
	public <C, P> TupleLiteralExp<C, P> createTupleLiteralExp() {
        return record(delegate.<C, P>createTupleLiteralExp());
    }

    @Override
	public <C, P> TupleLiteralPart<C, P> createTupleLiteralPart() {
        return record(delegate.<C, P>createTupleLiteralPart());
    }

    @Override
	public <C, O, P> TupleType<O, P> createTupleType(
            List<? extends TypedElement<C>> parts) {
        return record(delegate.<C, O, P>createTupleType(parts));
    }

    @Override
	public <C> TypeExp<C> createTypeExp() {
        return record(delegate.<C>createTypeExp());
    }

    @Override
	public <C, O> TypeType<C, O> createTypeType(C type) {
        return record(delegate.<C, O>createTypeType(type));
    }

    @Override
	public <C> UnlimitedNaturalLiteralExp<C> createUnlimitedNaturalLiteralExp() {
        return record(delegate.<C>createUnlimitedNaturalLiteralExp());
    }

    @Override
	public <C> UnspecifiedValueExp<C> createUnspecifiedValueExp() {
        return record(delegate.<C>createUnspecifiedValueExp());
    }

    @Override
	public <C, PM> Variable<C, PM> createVariable() {
        return record(delegate.<C, PM>createVariable());
    }

    @Override
	public <C, PM> VariableExp<C, PM> createVariableExp() {
        return record(delegate.<C, PM>createVariableExp());
    }
}
