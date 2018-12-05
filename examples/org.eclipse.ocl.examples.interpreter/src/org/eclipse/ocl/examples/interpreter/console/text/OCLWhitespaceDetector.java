/*******************************************************************************
 * Copyright (c) 2007, 2018 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *******************************************************************************/

package org.eclipse.ocl.examples.interpreter.console.text;

import org.eclipse.jface.text.rules.IWhitespaceDetector;


/**
 * Detector for whitespace in OCL text.
 * 
 * @author Christian W. Damus (cdamus)
 */
class OCLWhitespaceDetector
    implements IWhitespaceDetector {

    public boolean isWhitespace(char c) {
        switch (c) {
            case ' ':
            case '\t':
            case '\n':
            case '\r':
                return true;
            default:
                return false;
        }
    }

}
