/*******************************************************************************
 * Copyright (c) 2007, 2024 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors: 
 *   E.D.Willink - Initial API and implementation
 *   IBM - Javadoc and NLS updates
 *******************************************************************************/
package org.eclipse.ocl.lpg;

import java.util.List;

import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.ocl.types.AnyType;
import org.eclipse.ocl.types.CollectionType;
import org.eclipse.ocl.types.InvalidType;
import org.eclipse.ocl.types.TypeType;
import org.eclipse.ocl.types.VoidType;

/**
 * Some default formatting algorithms with support for some basic OCL and Ecore
 * constructs.
 * 
 * @since 1.2
 */
public class AbstractFormattingHelper implements FormattingHelper
{	 
	/**
	 * Shared instance implementing the default formatting algorithms.
	 */
    public static final AbstractFormattingHelper INSTANCE = new AbstractFormattingHelper();

	@Override
	public String formatClass(Object object) {
		if (object == null) {
            return "<null-class>"; //$NON-NLS-1$
        } else {
            return object.getClass().getName();
        }
	}

	@Override
	public String formatEClassName(EObject eObject) {
		if (eObject == null) {
            return "<null-eObject>"; //$NON-NLS-1$
        } else {
            return formatName(eObject.eClass());
        }
	}

	@Override
	public String formatName(Object object) {
		if (object == null) {
            return formatString(null);
        } else if (object instanceof ENamedElement) {
            return formatString(((ENamedElement) object).getName());
        } else {
            return object.getClass().getName();
        }
	}
	
	/**
	 * Obtains the name of the separator between namespaces in a qualified name.
	 * The default separator is <tt>"::"</tt>.
	 * 
	 * @return the namespace separator
	 */
	protected String getSeparator() {
	    return "::"; //$NON-NLS-1$
	}
	
    @Override
	public String formatQualifiedName(Object object) {
        if (object instanceof EObject) {
            Object container = ((EObject)object).eContainer();
            if (container != null) {
                return formatQualifiedName(container) + getSeparator() + formatName(object);
            }
        }
        
        return formatName(object);
    }

	@Override
	public String formatPath(List<String> pathName) {
		StringBuilder s = new StringBuilder();
		if (pathName != null) {
			int iMax = pathName.size();
			for (int i = 0; i < iMax; i++) {
				s.append(formatString(pathName.get(i)));
				if ((i+1) < iMax) {
                    s.append("::"); //$NON-NLS-1$
                }
			}
		}
		return s.toString();
	}

	@Override
	public String formatPath(List<String> pathName, String name) {
		StringBuilder s = new StringBuilder();
		if (pathName != null) {
			int iMax = pathName.size();
			for (int i = 0; i < iMax; i++) {
				s.append(formatString(pathName.get(i)));
				s.append((i+1) < iMax ? getSeparator() : "."); //$NON-NLS-1$
			}
		}
		s.append(formatString(name));
		return s.toString();
	}

	@Override
	public String formatString(String name) {
		return name != null ? name : "<null>"; //$NON-NLS-1$
	}
	
	@Override
	public String formatType(Object type) {
		if (type instanceof VoidType<?>) {
            return "<void-type>"; //$NON-NLS-1$
        } else if (type instanceof TypeType<?, ?>) {
            return "<type-type>"; //$NON-NLS-1$
        } else if (type instanceof InvalidType<?>) {
            return "<invalid-type>"; //$NON-NLS-1$
        } else if (type instanceof AnyType<?>) {
            return "<any-type>"; //$NON-NLS-1$
        } else if (type instanceof CollectionType<?, ?>) {
			StringBuilder s = new StringBuilder();
			s.append(((CollectionType<?, ?>)type).getKind().toString());
			s.append('(');
			s.append(formatType(((CollectionType<?, ?>)type).getElementType()));
			s.append(')');
			return s.toString();
		} else {
            return formatName(type);
        }
	}
}
