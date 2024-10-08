/*******************************************************************************
 * Copyright (c) 2020, 2024 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.xtext.base.serializer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.xtext.base.serializer.DiagnosticStringBuilder.SerializationMetaDataDiagnosticStringBuilder;
import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.conversion.IValueConverterService;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.serializer.tokens.ICrossReferenceSerializer;

import com.google.inject.Inject;

/**
 * The UserModelAnalysis provides the working context to assist in the determination of the Xtext grammar rule
 * that can produce and assign a user model element.
 */
public class UserModelAnalysis
{
	@Inject
	private @NonNull IValueConverterService valueConverterService;

	@Inject
	private @NonNull ICrossReferenceSerializer crossReferenceSerializer;

	@Inject
	private SerializationMetaData.@NonNull Provider serializationMetaDataProvider;

	private @Nullable SerializationMetaData serializationMetaData;

	private @Nullable CommentSegmentSupport commentSegmentSupport = null;


	/**
	 * The analysis of each user model element.
	 */
	private final @NonNull Map<@NonNull EObject, @NonNull UserElementAnalysis> element2elementAnalysis = new HashMap<>();

	private int debugUserElementAnalysisCount = 0;
	private int debugSerializeCount = 0;
	private int debugDynamicRuleMatchCount = 0;
	private int depth = 0;

	public UserModelAnalysis() {
		super();
	}

	public void addCommentSupport(@NonNull CommentSegmentSupport commentSegmentSupport) {
		if (this.commentSegmentSupport == null) {
			this.commentSegmentSupport = commentSegmentSupport;
		}
		else if (this.commentSegmentSupport != commentSegmentSupport) {
			System.out.println("Conflicting CommentSegmentSupport");
		}
	}

	/**
	 * Perform analysis of each user model element.
	 */
	public void analyze(@NonNull EObject model) {
		assert model.eContainer() == null;
		UserElementAnalysis rootElementAnalysis = new UserElementAnalysis(this, null, model);
		analyzeHierarchy(rootElementAnalysis, model);
		rootElementAnalysis.getSerializationRules();		// Avoid lazy serializationRules being omitted by a toString().
	}

	private void analyzeHierarchy(@NonNull UserElementAnalysis parentAnalysis, @NonNull EObject eParent) {
		element2elementAnalysis.put(eParent, parentAnalysis);
		for (EObject eChild : eParent.eContents()) {
			EReference eContainmentFeature = eChild.eContainmentFeature();
			assert eContainmentFeature.isContainment() && !eContainmentFeature.isDerived() && !eContainmentFeature.isTransient() && !eContainmentFeature.isVolatile();
			UserElementAnalysis childAnalysis = new UserElementAnalysis(this, parentAnalysis, eChild);
			analyzeHierarchy(childAnalysis, eChild);
		//	if (Iterables.size(elementAnalysis.getSerializationRules()) > 1) {
		//		unresolvedModelObjects.add(elementAnalysis);
		//	}
		}
	}

	public @NonNull UserElementFormatter createUserElementFormatter(@NonNull INode node, @NonNull AbstractElement formattedGrammarElement, @NonNull EObject eObject) {
		return new UserElementFormatter(node, formattedGrammarElement, this, eObject);
	}

	public @NonNull UserElementSerializer createUserElementSerializer(@NonNull DynamicRuleMatch dynamicRuleMatch, @NonNull EObject eObject) {
		return new UserElementSerializer(dynamicRuleMatch, eObject);
	}

	public void debugAddDynamicRuleMatch(@NonNull DynamicRuleMatch dynamicRuleMatch) {
		debugDynamicRuleMatchCount++;
	}

	public void debugAddUserElementAnalysis(@NonNull UserElementAnalysis userElementAnalysis) {
		debugUserElementAnalysisCount++;
	}

	public @NonNull String diagnose() {
		StringBuilder s = new StringBuilder();
		s.append("debugUserElementAnalysisCount = " + debugUserElementAnalysisCount + "\n");
		s.append("debugSerializeCount = " + debugSerializeCount + "\n");
		s.append("debugDynamicRuleMatchCount = " + debugDynamicRuleMatchCount + "\n");
		return s.toString();
	}

	/**
	 * Descend the user model containment tree diagnosing the elements that failed to serialize depth-first.
	 */
	private boolean diagnose(@NonNull DiagnosticStringBuilder s, @NonNull EObject eObject, int depth) {
		boolean childDiagnosed = false;
		for (EObject eChild : eObject.eContents()) {
			assert eChild != null;
			if (diagnose(s, eChild, depth+1)) {
				childDiagnosed = true;
			}
		}
		if (childDiagnosed) {
			return true;
		}
		UserElementAnalysis elementAnalysis = getElementAnalysis(eObject);
		return elementAnalysis.diagnose(s);
	}

	public @Nullable CommentSegmentSupport getCommentSegmentSupport() {
		return commentSegmentSupport;
	}

	public @NonNull ICrossReferenceSerializer getCrossReferenceSerializer() {
		return crossReferenceSerializer;
	}

	/**
	 * Return the analysis of a user model element.
	 */
	public @NonNull UserElementAnalysis getElementAnalysis(@NonNull EObject element) {
		return ClassUtil.nonNullState(element2elementAnalysis.get(element));
	}

	public @NonNull String getIndent() {
		return getIndent(0);
	}

	public @NonNull String  getIndent(int extraDepth) {
		return SerializationUtils.getIndent(depth + extraDepth);
	}

	public @NonNull SerializationMetaData getSerializationMetaData() {
		SerializationMetaData serializationMetaData2 = serializationMetaData;
		if (serializationMetaData2 == null) {
			serializationMetaData = serializationMetaData2 = serializationMetaDataProvider.get();
		}
		return serializationMetaData2;
	}

	public @NonNull IValueConverterService getValueConverterService() {
		return valueConverterService;
	}

	public int popDepth() {
		return --depth;
	}

	public int pushDepth() {
		return ++depth;
	}

	/**
	 * Create a Serializer for the appropriate configuration of element, then use it to serialize it and its descendants
	 * to the serializationBuilder.
	 */
	public void serialize(@NonNull SerializationBuilder serializationBuilder, @NonNull EObject eObject, @Nullable ParserRuleValue targetRuleValue) {
		debugSerializeCount++;
		UserElementAnalysis elementAnalysis = getElementAnalysis(eObject);
		DynamicRuleMatch okMatch = elementAnalysis.basicCreateDynamicRuleMatch(targetRuleValue);
		if (okMatch != null) {
			int savedDepth = this.depth++;
			UserElementSerializer serializer = createUserElementSerializer(okMatch, eObject);
			serializer.serialize(serializationBuilder);
			this.depth = savedDepth;
		}
		else {
			DiagnosticStringBuilder s = new SerializationMetaDataDiagnosticStringBuilder(getSerializationMetaData());
			boolean hasContext = diagnose(s, eObject, 0);
			s.append("\n\n«incompatible");
			if (!hasContext) {
				elementAnalysis.diagnoseEObject(s, eObject);
				s.append("\n");
			}
			else {
				s.append("'");
				s.appendObject(eObject.eClass().getName());
				s.append("'\n");
			}
			if (!elementAnalysis.diagnose(s) && (targetRuleValue != null)) {
				s.append(" - ");
				s.appendObject(targetRuleValue);
				s.append(" required.");
			}
			s.append("»\n\n");
			@NonNull String castString = s.toString();
			serializationBuilder.appendError(castString);
		}
	}

/*	public void serialize(@NonNull SerializationBuilder serializationBuilder, Object object, @NonNull DataTypeRuleValue targetRuleValue) {
		debugSerializeCount++;
		UserElementAnalysis elementAnalysis = getElementAnalysis(object);
		DynamicRuleMatch okMatch = elementAnalysis.basicCreateDynamicRuleMatch(targetRuleValue != null ? targetRuleValue : null);
		if (okMatch != null) {
			UserElementSerializer serializer = createUserElementSerializer(okMatch, object);
			serializer.serialize(serializationBuilder);
		}
		else {
			DiagnosticStringBuilder s = new SerializationMetaDataDiagnosticStringBuilder(getSerializationMetaData());
			boolean hasContext = diagnose(s, object, 0);
			s.append("\n\n«incompatible");
			if (!hasContext) {
				elementAnalysis.diagnoseEObject(s, object);
				s.append("\n");
			}
			else {
				s.append("'");
				s.appendObject(object != null ? object.getClass().getName() : "null");
				s.append("'\n");
			}
			if (!elementAnalysis.diagnose(s) && (targetRuleValue != null)) {
				s.append(" - ");
				s.appendObject(targetRuleValue);
				s.append(" required.");
			}
			s.append("»\n\n");
			@NonNull String castString = s.toString();
			serializationBuilder.appendError(castString);
		}
	} */

	@Override
	public @NonNull String toString() {
		DiagnosticStringBuilder s = new SerializationMetaDataDiagnosticStringBuilder(getSerializationMetaData());
		s.append("User object <=> Xtext containing assignment(s) : Xtext production rule\n");
		List<@NonNull UserElementAnalysis> elementAnalyses = new ArrayList<>(element2elementAnalysis.values());
		Collections.sort(elementAnalyses, NameUtil.NAMEABLE_COMPARATOR);
		boolean isFirst = true;
		for (@NonNull UserElementAnalysis elementAnalysis : elementAnalyses) {
			if (!isFirst) {
				s.append("\n");
			}
			s.append("  ");
			elementAnalysis.toString(s, 1);
			isFirst = false;
		}
		return s.toString();
	}
}
