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
package org.eclipse.ocl.xtext.base.cs2text.idioms;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.xtext.base.cs2text.SerializationBuilder;
import org.eclipse.ocl.xtext.base.cs2text.idioms.impl.CustomSegmentImpl;
import org.eclipse.ocl.xtext.base.cs2text.idioms.impl.IdiomImpl;

public class IdiomsUtils
{
	public static @NonNull IdiomModel IDIOM_MODEL = getIdiomModel(URI.createPlatformResourceURI("org.eclipse.ocl.xtext.base/model/BaseIdioms.xmi", true));

	public static @NonNull IdiomModel getIdiomModel(@NonNull URI idiomURI) {
	//	ResourceSet resourceSet = new ResourceSetImpl();
	//	IdiomsPackageImpl.eINSTANCE.getClass();
	//	Resource resource = resourceSet.getResource(idiomURI, true);
	//	return (IdiomModel) resource.getContents().get(0);
		return createIdiomModel();
	}

	public static final @NonNull StringSegment HALF_NEW_LINE = createStringSegment(IDIOM_MODEL, SerializationBuilder.HALF_NEW_LINE);
	public static final @NonNull StringSegment NO_SPACE = createStringSegment(IDIOM_MODEL, SerializationBuilder.NO_SPACE);
	public static final @NonNull StringSegment POP = createStringSegment(IDIOM_MODEL, SerializationBuilder.POP);
	public static final @NonNull StringSegment PUSH = createStringSegment(IDIOM_MODEL, SerializationBuilder.PUSH);
	public static final @NonNull StringSegment SOFT_NEW_LINE = createStringSegment(IDIOM_MODEL, SerializationBuilder.SOFT_NEW_LINE);
	public static final @NonNull StringSegment SOFT_SPACE = createStringSegment(IDIOM_MODEL, SerializationBuilder.SOFT_SPACE);
	public static final @NonNull ValueSegment VALUE = createValueSegment(IDIOM_MODEL);

/*	private static final @NonNull SubIdiom SUB_CLOSE_BRACE = createSubIdiom(createKeywordLocator(IDIOM_MODEL, "}", null), POP, SOFT_SPACE, VALUE_SEGMENT, SOFT_NEW_LINE);
	private static final @NonNull SubIdiom SUB_CLOSE_PARENTHESIS = createSubIdiom(createKeywordLocator(IDIOM_MODEL, ")", null), NO_SPACE, VALUE_SEGMENT, NO_SPACE);
	private static final @NonNull SubIdiom SUB_CLOSE_SQUARE = createSubIdiom(createKeywordLocator(IDIOM_MODEL, "]", null), NO_SPACE, VALUE_SEGMENT);
//	private static final @NonNull SubIdiom SUB_COLON_IN_OPERATIONCONTEXTDECLCS = createSubIdiom(createKeywordLocator(IDIOM_MODEL, " :", CompleteOCL), IdiomUtils.NO_SPACE, IdiomUtils.SERIALIZED_VALUE);
	private static final @NonNull SubIdiom SUB_COMMA = createSubIdiom(createKeywordLocator(IDIOM_MODEL, ",", null), NO_SPACE, VALUE_SEGMENT, SOFT_SPACE);
	private static final @NonNull SubIdiom SUB_DEFAULT = createSubIdiom(createDefaultLocator(IDIOM_MODEL), SOFT_SPACE, VALUE_SEGMENT, SOFT_SPACE);
	private static final @NonNull SubIdiom SUB_DOUBLE_COLON = createSubIdiom(createKeywordLocator(IDIOM_MODEL, "::", null), NO_SPACE, VALUE_SEGMENT, NO_SPACE);
	private static final @NonNull SubIdiom SUB_DOT_DOT = createSubIdiom(createKeywordLocator(IDIOM_MODEL, "..", null), NO_SPACE, VALUE_SEGMENT, NO_SPACE);
	private static final @NonNull SubIdiom SUB_OPEN_BRACE = createSubIdiom(createKeywordLocator(IDIOM_MODEL, "{", null), SOFT_SPACE, VALUE_SEGMENT, PUSH, SOFT_NEW_LINE);
	private static final @NonNull SubIdiom SUB_OPEN_PARENTHESIS = createSubIdiom(createKeywordLocator(IDIOM_MODEL, "(", null), NO_SPACE, VALUE_SEGMENT, NO_SPACE);
	private static final @NonNull SubIdiom SUB_OPEN_SQUARE = createSubIdiom(createKeywordLocator(IDIOM_MODEL, "[", null), NO_SPACE, VALUE_SEGMENT, NO_SPACE);
	private static final @NonNull SubIdiom SUB_SEMI_COLON = createSubIdiom(createKeywordLocator(IDIOM_MODEL, ";", null), NO_SPACE, VALUE_SEGMENT, SOFT_NEW_LINE);

	private static final @NonNull SubIdiom SUB_VALUE = createSubIdiom(null, VALUE_SEGMENT);
//	private static final @NonNull SubIdiom SUB_COMMENTED_PACKAGE = createSubIdiom(createKeywordLocator(IDIOM_MODEL, " enum"), new CustomSegment(BaseCommentSegment.class), IdiomUtils.SERIALIZED_VALUE);
	private static final @NonNull SubIdiom SUB_COMMENTED_RULE = createSubIdiom(createProducedEClassLocator(IDIOM_MODEL, BaseCSPackage.Literals.MODEL_ELEMENT_CS), createCustomSegment(IDIOM_MODEL, BaseCommentSegment.class), VALUE_SEGMENT);

	private static final @NonNull SubIdiom SUB_PackagesCS_ownedClasses = createSubIdiom(createAssignmentLocator(IDIOM_MODEL, BaseCSPackage.Literals.PACKAGE_CS__OWNED_CLASSES), HALF_NEW_LINE, VALUE_SEGMENT, HALF_NEW_LINE);

	private static final @NonNull Idiom BRACES = createIdiom(IDIOM_MODEL, SUB_OPEN_BRACE, SUB_CLOSE_BRACE);
	private static final @NonNull Idiom COMMA = createIdiom(IDIOM_MODEL, SUB_COMMA);
	private static final @NonNull Idiom DEFAULT = createIdiom(IDIOM_MODEL, SUB_DEFAULT);
	private static final @NonNull Idiom DOUBLE_COLON = createIdiom(IDIOM_MODEL, SUB_DOUBLE_COLON);
	private static final @NonNull Idiom DOT_DOT = createIdiom(IDIOM_MODEL, SUB_DOT_DOT);
	private static final @NonNull Idiom PARENTHESES = createIdiom(IDIOM_MODEL, SUB_OPEN_PARENTHESIS, SUB_CLOSE_PARENTHESIS);
	private static final @NonNull Idiom SEMI_COLON = createIdiom(IDIOM_MODEL, SUB_SEMI_COLON);
	private static final @NonNull Idiom SQUARES = createIdiom(IDIOM_MODEL, SUB_OPEN_SQUARE, SUB_CLOSE_SQUARE);

	private static final @NonNull Idiom COMMENTED_RULE = createIdiom(IDIOM_MODEL, SUB_COMMENTED_RULE);

	private static final @NonNull Idiom INTER_CLASSSES = createDebugIdiom(SUB_PackagesCS_ownedClasses);//, SubIdiom.PackagesCS_ownedClasses);

	public static final @NonNull Idiom @NonNull [] IDIOMS = new @NonNull Idiom[] { COMMENTED_RULE, BRACES, PARENTHESES, SQUARES, COMMA, DOUBLE_COLON, DOT_DOT, SEMI_COLON, INTER_CLASSSES, DEFAULT};

	public static final @NonNull Idiom @NonNull [] IDIOMS = getIdioms(IDIOM_MODEL);

	protected static @NonNull Idiom @NonNull [] getIdioms(@NonNull IdiomModel idiomModel) {
		List<Idiom> ownedSegments = idiomModel.getOwnedIdioms();
		return ownedSegments.toArray(new @NonNull Idiom[ownedSegments.size()]);
	} */

	public static @NonNull AssignmentLocator createAssignmentLocator(@NonNull IdiomModel idiomModel, EStructuralFeature eStructuralFeature) {
		AssignmentLocator locator = IdiomsFactory.eINSTANCE.createAssignmentLocator();
		locator.setEStructuralFeature(eStructuralFeature);
		idiomModel.getOwnedLocators().add(locator);
		return locator;
	}

	/*	private static @NonNull CustomSegment createCustomSegment(@NonNull IdiomModel idiomModel, @NonNull Class<@NonNull BaseCommentSegment> customSegmentClass) {
	CustomSegmentImpl segment = (CustomSegmentImpl) IdiomsFactory.eINSTANCE.createCustomSegment();
//	segment.setDelegate(customSegmentClass);
	idiomModel.getOwnedSegments().add(segment);
	return segment;
} */

	public static @NonNull CustomSegment createCustomSegment(@Nullable IdiomModel idiomModel, @NonNull Class<? extends CustomSegmentSupport> customSegmentClass) {
		CustomSegmentImpl segment = (CustomSegmentImpl) IdiomsFactory.eINSTANCE.createCustomSegment();
		segment.setSupportClass(customSegmentClass);
		if (idiomModel != null) {
			idiomModel.getOwnedSegments().add(segment);
		}
		return segment;
	}

	public static @NonNull Idiom createDebugIdiom(@NonNull SubIdiom @NonNull ... subIdioms) {
		Idiom idiom = new IdiomImpl.DebugIdiom();
		for (@NonNull SubIdiom subIdiom : subIdioms) {
			idiom.getOwnedSubIdioms().add(subIdiom);
		}
		return idiom;
	}

	public static @NonNull Idiom createIdiom(@NonNull IdiomModel idiomModel, @NonNull SubIdiom @NonNull ... subIdioms) {
		Idiom idiom = IdiomsFactory.eINSTANCE.createIdiom();
		for (@NonNull SubIdiom subIdiom : subIdioms) {
			idiom.getOwnedSubIdioms().add(subIdiom);
		}
		idiomModel.getOwnedIdioms().add(idiom);
		return idiom;
	}

	public static @NonNull IdiomModel createIdiomModel() {
		IdiomModel idiomModel = IdiomsFactory.eINSTANCE.createIdiomModel();
		return idiomModel;
	}

	public static @NonNull KeywordLocator createKeywordLocator(@NonNull IdiomModel idiomModel, @NonNull String string, EClass inEClass) {
		KeywordLocator locator = IdiomsFactory.eINSTANCE.createKeywordLocator();
		locator.setString(string);
		locator.setInEClass(inEClass);
		idiomModel.getOwnedLocators().add(locator);
		return locator;
	}

	public static @NonNull ProducedEClassLocator createProducedEClassLocator(@NonNull IdiomModel idiomModel, EClass eClass) {
		ProducedEClassLocator locator = IdiomsFactory.eINSTANCE.createProducedEClassLocator();
		locator.setEClass(eClass);
		idiomModel.getOwnedLocators().add(locator);
		return locator;
	}

	public static @NonNull StringSegment createStringSegment(@NonNull IdiomModel idiomModel, @NonNull String string) {
		return createStringSegment(idiomModel, string, true);
	}

	public static @NonNull StringSegment createStringSegment(@NonNull IdiomModel idiomModel, @NonNull String string, boolean printable) {
		StringSegment segment = IdiomsFactory.eINSTANCE.createStringSegment();
		segment.setString(string);
		segment.setPrintable(printable);
		idiomModel.getOwnedSegments().add(segment);
		return segment;
	}

	public static @NonNull SubIdiom createSubIdiom(@Nullable Locator locator, @NonNull Segment @NonNull ... segments) {
		SubIdiom subIdiom = IdiomsFactory.eINSTANCE.createSubIdiom();
		subIdiom.setLocator(locator);
		for (@NonNull Segment segment : segments) {
			subIdiom.getSegments().add(segment);
		}
		assert subIdiom.getSegments().size() == segments.length;	// Detect Bug 89325
		return subIdiom;
	}

	public static @NonNull ValueSegment createValueSegment(@NonNull IdiomModel idiomModel) {
		ValueSegment segment = IdiomsFactory.eINSTANCE.createValueSegment();
		idiomModel.getOwnedSegments().add(segment);
		return segment;
	}
}
