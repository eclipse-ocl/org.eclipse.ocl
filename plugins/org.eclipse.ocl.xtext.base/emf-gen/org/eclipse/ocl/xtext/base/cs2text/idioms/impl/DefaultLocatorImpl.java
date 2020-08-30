/**
 * Copyright (c) 2020 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 */
package org.eclipse.ocl.xtext.base.cs2text.idioms.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.xtext.base.cs2text.elements.AlternativeAssignedKeywordsSerializationNode;
import org.eclipse.ocl.xtext.base.cs2text.elements.AlternativeAssignedRuleCallsSerializationNode;
import org.eclipse.ocl.xtext.base.cs2text.elements.AssignedCrossReferenceSerializationNode;
import org.eclipse.ocl.xtext.base.cs2text.elements.AssignedKeywordSerializationNode;
import org.eclipse.ocl.xtext.base.cs2text.elements.AssignedRuleCallSerializationNode;
import org.eclipse.ocl.xtext.base.cs2text.elements.AssignedSerializationNode;
import org.eclipse.ocl.xtext.base.cs2text.elements.SerializationNode;
import org.eclipse.ocl.xtext.base.cs2text.elements.SerializationRuleAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.elements.UnassignedKeywordSerializationNode;
import org.eclipse.ocl.xtext.base.cs2text.idioms.DefaultLocator;
import org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsPackage;
import org.eclipse.ocl.xtext.base.cs2text.xtext.AbstractRuleAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector;
import org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleAnalysis;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Default Locator</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public class DefaultLocatorImpl extends LocatorImpl implements DefaultLocator
{
	/**
	 * The number of structural features of the '<em>Default Locator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int DEFAULT_LOCATOR_FEATURE_COUNT = LocatorImpl.LOCATOR_FEATURE_COUNT + 0;


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DefaultLocatorImpl()
	{
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass()
	{
		return IdiomsPackage.Literals.DEFAULT_LOCATOR;
	}

	@Override
	public boolean matches(SerializationNode serializationNode, SerializationRuleAnalysis serializationRule) {
		if (serializationNode instanceof AlternativeAssignedKeywordsSerializationNode) {
			return true;
		}
		else if (serializationNode instanceof AssignedCrossReferenceSerializationNode) {
			return true;
		}
		else if ((serializationNode instanceof AssignedRuleCallSerializationNode) || (serializationNode instanceof AlternativeAssignedRuleCallsSerializationNode)){
			IndexVector assignedRuleAnalyses = ((AssignedSerializationNode)serializationNode).getAssignedRuleIndexes();
			if (assignedRuleAnalyses != null) {
				for (@NonNull Integer ruleIndex : assignedRuleAnalyses) {
					AbstractRuleAnalysis assignedRuleAnalysis = serializationRule.getRuleAnalysis().getGrammarAnalysis().getRuleAnalysis(ruleIndex);
					if (!(assignedRuleAnalysis instanceof ParserRuleAnalysis)) {
						return true;
					}
				}
			}
			return false;		// XXX Is this still right ??
		}
		else if (serializationNode instanceof AssignedKeywordSerializationNode) {
			return true;
		}
		else if (serializationNode instanceof UnassignedKeywordSerializationNode) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "«default»";
	}
} //DefaultLocatorImpl
