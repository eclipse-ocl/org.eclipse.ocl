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
package org.eclipse.ocl.examples.eventmanager.framework;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.ocl.examples.eventmanager.filters.ContainmentFilter;
import org.eclipse.ocl.examples.eventmanager.filters.StructuralFeatureFilter;

/**
 * The AttributeFilterTable manages all Registrations containing {@link StructuralFeatureFilter}.
 * 
 * @see org.eclipse.ocl.examples.eventmanager.framework.TableForEventFilter
 * @author Daniel Vocke (D044825)
 */
public class TableForContainmentFilter extends TableForEventFilter {

    public TableForContainmentFilter(int numberOfFilterTables) {
        super(numberOfFilterTables);
    }

    /**
     * @return the affected meta object of <code>AttributeValueEvents</code>. If the event is not of type
     * <code>AttributeValueEvent</code>, <code>null</code> is returned.
     */
    public Object getAffectedObject(Notification event) {
        if(event.getFeature()==null && (event.getNotifier() instanceof Resource))
            return true;
        if (isEmpty() || !(event.getFeature()!=null && event.getFeature() instanceof EReference))
            return null;
        //TODO check if source obj is the right one
        return ((EReference)event.getFeature()).isContainment();
    }

    @Override
    public Class<ContainmentFilter> getIdentifier() {
        return ContainmentFilter.class;
    }

    
}
