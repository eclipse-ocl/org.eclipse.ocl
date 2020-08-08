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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.xtext.base.cs2text.SerializationBuilder;
import org.eclipse.ocl.xtext.base.cs2text.idioms.impl.CustomSegmentImpl;
import org.eclipse.ocl.xtext.base.cs2text.idioms.impl.IdiomImpl;
import org.eclipse.ocl.xtext.basecs.BaseCSPackage;

public class IdiomsUtils
{

	public static @NonNull SubIdiom SUB_CLOSE_BRACE = createSubIdiom(createKeywordLocator("}", null), createStringSegment(SerializationBuilder.POP), createStringSegment(SerializationBuilder.SOFT_SPACE), createValueSegment(), createStringSegment(SerializationBuilder.SOFT_NEW_LINE));

	public static @NonNull SubIdiom SUB_CLOSE_PARENTHESIS = createSubIdiom(createKeywordLocator(")", null), createStringSegment(SerializationBuilder.NO_SPACE), createValueSegment(), createStringSegment(SerializationBuilder.NO_SPACE));
	public static @NonNull SubIdiom SUB_CLOSE_SQUARE = createSubIdiom(createKeywordLocator("]", null), createStringSegment(SerializationBuilder.NO_SPACE), createValueSegment());
//	public static @NonNull SubIdiom SUB_COLON_IN_OPERATIONCONTEXTDECLCS = createSubIdiom(createKeywordLocator(":", CompleteOCL), IdiomUtils.NO_SPACE, IdiomUtils.SERIALIZED_VALUE);
	public static @NonNull SubIdiom SUB_COMMA = createSubIdiom(createKeywordLocator(",", null), createStringSegment(SerializationBuilder.NO_SPACE), createValueSegment(), createStringSegment(SerializationBuilder.SOFT_SPACE));
	public static @NonNull SubIdiom SUB_DEFAULT = createSubIdiom(createDefaultLocator(), createStringSegment(SerializationBuilder.SOFT_SPACE), createValueSegment(), createStringSegment(SerializationBuilder.SOFT_SPACE));
	public static @NonNull SubIdiom SUB_DOUBLE_COLON = createSubIdiom(createKeywordLocator("::", null), createStringSegment(SerializationBuilder.NO_SPACE), createValueSegment(), createStringSegment(SerializationBuilder.NO_SPACE));
	public static @NonNull SubIdiom SUB_DOT_DOT = createSubIdiom(createKeywordLocator("..", null), createStringSegment(SerializationBuilder.NO_SPACE), createValueSegment(), createStringSegment(SerializationBuilder.NO_SPACE));
	public static @NonNull SubIdiom SUB_OPEN_BRACE = createSubIdiom(createKeywordLocator("{", null), createStringSegment(SerializationBuilder.SOFT_SPACE), createValueSegment(), createStringSegment(SerializationBuilder.PUSH), createStringSegment(SerializationBuilder.SOFT_NEW_LINE));
	public static @NonNull SubIdiom SUB_OPEN_PARENTHESIS = createSubIdiom(createKeywordLocator("(", null), createStringSegment(SerializationBuilder.NO_SPACE), createValueSegment(), createStringSegment(SerializationBuilder.NO_SPACE));
	public static @NonNull SubIdiom SUB_OPEN_SQUARE = createSubIdiom(createKeywordLocator("[", null), createStringSegment(SerializationBuilder.NO_SPACE), createValueSegment(), createStringSegment(SerializationBuilder.NO_SPACE));
	public static @NonNull SubIdiom SUB_SEMI_COLON = createSubIdiom(createKeywordLocator(";", null), createStringSegment(SerializationBuilder.NO_SPACE), createValueSegment(), createStringSegment(SerializationBuilder.SOFT_NEW_LINE));
	public static @NonNull SubIdiom SUB_VALUE = createSubIdiom(null, createValueSegment());
//	public static @NonNull SubIdiom SUB_COMMENTED_PACKAGE = createSubIdiom(createKeywordLocator("enum"), new CustomSegment(BaseCommentSegment.class), IdiomUtils.SERIALIZED_VALUE);
	public static @NonNull SubIdiom SUB_COMMENTED_RULE = createSubIdiom(createProducedEClassLocator(BaseCSPackage.Literals.MODEL_ELEMENT_CS), createCustomSegment(BaseCommentSegment.class), createValueSegment());


	public static @NonNull SubIdiom SUB_PackagesCS_ownedClasses = createSubIdiom(createAssignmentLocator(BaseCSPackage.Literals.PACKAGE_CS__OWNED_CLASSES), createStringSegment(SerializationBuilder.HALF_NEW_LINE), createValueSegment(), createStringSegment(SerializationBuilder.HALF_NEW_LINE));




	public static final @NonNull Idiom BRACES = createIdiom(SUB_OPEN_BRACE, SUB_CLOSE_BRACE);
	public static final @NonNull Idiom COMMA = createIdiom(SUB_COMMA);
	public static final @NonNull Idiom DEFAULT = createIdiom(SUB_DEFAULT);
	public static final @NonNull Idiom DOUBLE_COLON = createIdiom(SUB_DOUBLE_COLON);
	public static final @NonNull Idiom DOT_DOT = createIdiom(SUB_DOT_DOT);
	public static final @NonNull Idiom PARENTHESES = createIdiom(SUB_OPEN_PARENTHESIS, SUB_CLOSE_PARENTHESIS);
	public static final @NonNull Idiom SEMI_COLON = createIdiom(SUB_SEMI_COLON);
	public static final @NonNull Idiom SQUARES = createIdiom(SUB_OPEN_SQUARE, SUB_CLOSE_SQUARE);

	public static final @NonNull Idiom COMMENTED_RULE = createIdiom(SUB_COMMENTED_RULE);

	public static final @NonNull Idiom INTER_CLASSSES = createDebugIdiom(SUB_PackagesCS_ownedClasses);//, SubIdiom.PackagesCS_ownedClasses);

	public static final @NonNull Idiom @NonNull [] IDIOMS = new @NonNull Idiom[] { COMMENTED_RULE, BRACES, PARENTHESES, SQUARES, COMMA, DOUBLE_COLON, DOT_DOT, SEMI_COLON, INTER_CLASSSES, DEFAULT};


	public static @NonNull AssignmentLocator createAssignmentLocator(EStructuralFeature eStructuralFeature) {
		AssignmentLocator locator = IdiomsFactory.eINSTANCE.createAssignmentLocator();
		locator.setEStructuralFeature(eStructuralFeature);
		return locator;
	}

	private static @NonNull CustomSegment createCustomSegment(@NonNull Class<@NonNull BaseCommentSegment> customSegmentClass) {
		CustomSegmentImpl segment = (CustomSegmentImpl) IdiomsFactory.eINSTANCE.createCustomSegment();
		segment.setDelegate(customSegmentClass);
		return segment;
	}

	public static @NonNull Idiom createDebugIdiom(@NonNull SubIdiom @NonNull ... subIdioms) {
		Idiom idiom = new IdiomImpl.DebugIdiom();
		for (@NonNull SubIdiom subIdiom : subIdioms) {
			idiom.getSubIdioms().add(subIdiom);
		}
		return idiom;
	}

	public static @NonNull DefaultLocator createDefaultLocator() {
		DefaultLocator locator = IdiomsFactory.eINSTANCE.createDefaultLocator();
		return locator;
	}

	public static @NonNull Idiom createIdiom(@NonNull SubIdiom @NonNull ... subIdioms) {
		Idiom idiom = IdiomsFactory.eINSTANCE.createIdiom();
		for (@NonNull SubIdiom subIdiom : subIdioms) {
			idiom.getSubIdioms().add(subIdiom);
		}
		return idiom;
	}

	public static @NonNull KeywordLocator createKeywordLocator(@NonNull String string, EClass inEClass) {
		KeywordLocator locator = IdiomsFactory.eINSTANCE.createKeywordLocator();
		locator.setString(string);
		locator.setInEClass(inEClass);
		return locator;
	}

	public static @NonNull ProducedEClassLocator createProducedEClassLocator(EClass eClass) {
		ProducedEClassLocator locator = IdiomsFactory.eINSTANCE.createProducedEClassLocator();
		locator.setEClass(eClass);
		return locator;
	}

	public static @NonNull StringSegment createStringSegment(@NonNull String string) {
		return createStringSegment(string, true);
	}

	public static @NonNull StringSegment createStringSegment(@NonNull String string, boolean printable) {
		StringSegment segment = IdiomsFactory.eINSTANCE.createStringSegment();
		segment.setString(string);
		segment.setPrintable(printable);
		return segment;
	}

	public static @NonNull SubIdiom createSubIdiom(@Nullable Locator locator, @NonNull Segment @NonNull ... segments) {
		SubIdiom subIdiom = IdiomsFactory.eINSTANCE.createSubIdiom();
		subIdiom.setLocator(locator);
		for (@NonNull Segment segment : segments) {
			subIdiom.getSegments().add(segment);
		}
		return subIdiom;
	}

	public static @NonNull ValueSegment createValueSegment() {
		ValueSegment segment = IdiomsFactory.eINSTANCE.createValueSegment();
		return segment;
	}
}
