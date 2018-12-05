/*******************************************************************************
 * Copyright (c) 2009, 2018 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *     SAP AG - initial API and implementation
 ******************************************************************************/
package org.eclipse.ocl.examples.eventmanager.tests.framework;


import java.util.Collection;
import java.util.Iterator;

import junit.framework.TestCase;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.DynamicEObjectImpl;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.ocl.examples.eventmanager.EventFilter;
import org.eclipse.ocl.examples.eventmanager.EventManager;
import org.eclipse.ocl.examples.eventmanager.EventManagerFactory;
import org.eclipse.ocl.examples.eventmanager.framework.EventAdapter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class RecursiveContaimentNotificationCreatorTest extends TestCase {

    @Override
    @Before
    public void setUp() {
        try {
			super.setUp();
		} catch (Exception e) {
			/*...*/
		}
    }

    @Override
    @After
    public void tearDown(){
        try {
			super.tearDown();
		} catch (Exception e) {
			/*...*/
		}
    }

    @Test
    public void testCreateNotificationForComposites() {
    	EPackage root = EcoreFactory.eINSTANCE.createEPackage();
    	EClass containerCls = EcoreFactory.eINSTANCE.createEClass();
    	root.getEClassifiers().add(containerCls);
    	EClass childCls = EcoreFactory.eINSTANCE.createEClass();
    	root.getEClassifiers().add(childCls);

    	EReference conRef = EcoreFactory.eINSTANCE.createEReference();
    	conRef.setContainment(true);
    	conRef.setEType(childCls);
    	conRef.setLowerBound(0);
    	conRef.setUpperBound(1);
    	conRef.setName("contained");
    	
    	EAttribute attr = EcoreFactory.eINSTANCE.createEAttribute();
    	attr.setEType(EcorePackage.eINSTANCE.getEInt());
    	childCls.getEStructuralFeatures().add(attr);
    	containerCls.getEStructuralFeatures().add(conRef);
    	
    	EObject container = new DynamicEObjectImpl(containerCls);
    	EObject child = new DynamicEObjectImpl(childCls);
    	container.eSet(conRef, child);
    	child.eSet(attr, 2);

    	
    	Notification noti = new ENotificationImpl((InternalEObject) container, Notification.ADD, conRef, null, child);
    	Collection<Notification> list = EventManagerFactory.eINSTANCE.createNotificationForComposites(noti
    	);
        /*
         * Expect
         * SET for child attr
         * ADD of child
         */
        assertTrue("Get "+list.size()+" events, expected 2",list.size()==2);
    }

    public void testCreateNotificationForMultipleInsertedElements(){
    	EPackage root = EcoreFactory.eINSTANCE.createEPackage();
    	EClass container = EcoreFactory.eINSTANCE.createEClass();
    	root.getEClassifiers().add(container);

    	container.setName("container");
    	EReference conRef = EcoreFactory.eINSTANCE.createEReference();
    	conRef.setName("conRef");
    	conRef.setContainment(true);
    	conRef.setLowerBound(0);
    	conRef.setUpperBound(5);
    	container.getEStructuralFeatures().add(conRef);
    	EClass child = EcoreFactory.eINSTANCE.createEClass();
    	root.getEClassifiers().add(child);
    	child.setName("child");
    	conRef.setEType(child);

    	EObject con = new DynamicEObjectImpl(container);
    	EList<EObject> list = new BasicEList<EObject>();
    	for(int i = 0; i<5; i++){
    		list.add(new DynamicEObjectImpl(child));
    	}
    	con.eSet(conRef, list);
    	
    	EList<EObject> l = new BasicEList<EObject>();
    	l.add(con);
    	Notification n = new ENotificationImpl(new DynamicEObjectImpl(container), Notification.ADD, conRef, null, con);
    	Collection<Notification> result = EventManagerFactory.eINSTANCE.createNotificationForComposites(n);
        /*
         * Expect
         * ADD for container
         * 1 ADD_MANY for 5 children
         */
        assertEquals("Get not expected events",2,result.size());
        Iterator<Notification> iterator = result.iterator();
        Notification n1 = iterator.next();
        Notification n2 = iterator.next();
        /*
         * Expect
         * ADD for container
         * 1 ADD_MANY for 5 children
         */
        assertTrue("Expect Add and Add_Many", 
        		(n1.getEventType() == Notification.ADD && n2.getEventType() == Notification.ADD_MANY) 
        		||
        		(n2.getEventType() == Notification.ADD && n1.getEventType() == Notification.ADD_MANY) 
        		);
        
        EList<EObject> list2 = new BasicEList<EObject>();
    	for(int i = 0; i<1; i++){
    		list2.add(new DynamicEObjectImpl(child));
    	}
    	con.eSet(conRef, list2);
    	n = new ENotificationImpl(new DynamicEObjectImpl(container), Notification.ADD, conRef, null, con);
    	result = EventManagerFactory.eINSTANCE.createNotificationForComposites(n);
        /*
         * Expect
         * ADD for container
         * 1 ADD for 1 children
         */
        assertEquals("Get not expected Events",2,result.size());
        iterator = result.iterator();
        n1 = iterator.next();
        n2 = iterator.next();
        /*
         * Expect
         * ADD for container
         * 1 ADD_MANY for 5 children
         */
        assertTrue("Expect Add and Add", 
        		(n1.getEventType() == Notification.ADD && n2.getEventType() == Notification.ADD) 
        		);
    }
    
    
    /**
     * This test assures that the {@link EventAdapter} correctly maintains adapters on elements within a resourceSet,
     * even if these elements are moved to a new containment / removed from an existing containment.
     */
    public void testContainmentAdapter(){
        
        class Adapter extends AdapterImpl{
            public int count = 0;
            
            @Override
            public void notifyChanged(Notification msg) {
                this.count++;
            }
        }
        
        ResourceSet set = new ResourceSetImpl();
        Resource r = new ResourceImpl();
        set.getResources().add(r);
        
        // Setup class: int attribute and a containment reference.
        EClass cls = EcoreFactory.eINSTANCE.createEClass();
        EAttribute attr = EcoreFactory.eINSTANCE.createEAttribute();
        attr.setEType(EcorePackage.eINSTANCE.getEInt());
        cls.getEStructuralFeatures().add(attr);
        EReference containmentRef = EcoreFactory.eINSTANCE.createEReference();
        containmentRef.setContainment(true);
        containmentRef.setEType(cls);
        cls.getEStructuralFeatures().add(containmentRef);
        EPackage pkg = EcoreFactory.eINSTANCE.createEPackage();
        pkg.getEClassifiers().add(cls);
        
        // Setup fixture
        EObject anObject = new DynamicEObjectImpl(cls);
        EObject anotherObject = new DynamicEObjectImpl(cls);
        r.getContents().add(anObject);
        r.getContents().add(anotherObject);

        // Setup event manager. Subscribe to changes of the int attribute defined above.
        EventManager m = EventManagerFactory.eINSTANCE.createEventManagerFor(set);
        EventFilter filter = EventManagerFactory.eINSTANCE.createStructuralFeatureFilter(attr);
        Adapter adapter = new Adapter();
        m.subscribe(filter, adapter);

        // In the following: Run modifications with and without set containment reference
        anObject.eSet(containmentRef, anotherObject);
        
        adapter.count = 0;
        anObject.eSet(attr, 10);
        anotherObject.eSet(attr, 10);
        assertEquals("Expected to see both object modifications", 2, adapter.count);
        
        anObject.eUnset(containmentRef);
        assertTrue(r.getContents().contains(anObject));
        assertTrue(r.getContents().contains(anotherObject));

        adapter.count = 0;
        anObject.eSet(attr, 20);
        anotherObject.eSet(attr, 20);
        assertEquals("Still expected to see both object modifications", 2, adapter.count);
    }

}
