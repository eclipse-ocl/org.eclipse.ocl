/*******************************************************************************
 * Copyright (c) 2011, 2020 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.xtext.oclinecore.serializer;

import com.google.inject.Inject;
import org.eclipse.ocl.xtext.base.cs2text.AbstractAnalysisProvider;
import org.eclipse.ocl.xtext.base.cs2text.enumerations.EnumerationValue;
import org.eclipse.ocl.xtext.base.cs2text.idioms.Segment;
import org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule;
import org.eclipse.ocl.xtext.base.cs2text.solutions.CardinalitySolution;
import org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep;
import org.eclipse.ocl.xtext.base.cs2text.user.RTGrammarAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.service.GrammarProvider;

//	import Inject;

	public class OCLinEcoreAnalysisProvider extends AbstractAnalysisProvider
	{
	//	@Inject
	//	private GrammarProvider grammarProvider;

		private static RTGrammarAnalysis analysis = null;

		@Override
		public RTGrammarAnalysis getAnalysis() {
			if (analysis == null) {
				analysis = new RTGrammarAnalysis();
				analysis.addEClassData(ec._00);
				analysis.addEClassData(ec._01);
				analysis.addEClassData(ec._02);
				analysis.addEClassData(ec._03);
				analysis.addEClassData(ec._04);
				analysis.addEClassData(ec._05);
				analysis.addEClassData(ec._06);
				analysis.addEClassData(ec._07);
				analysis.addEClassData(ec._08);
				analysis.addEClassData(ec._09);
				analysis.addEClassData(ec._10);
				analysis.addEClassData(ec._11);
				analysis.addEClassData(ec._12);
				analysis.addEClassData(ec._13);
				analysis.addEClassData(ec._14);
				analysis.addEClassData(ec._15);
				analysis.addEClassData(ec._16);
				analysis.addEClassData(ec._17);
				analysis.addEClassData(ec._18);
				analysis.addEClassData(ec._19);
				analysis.addEClassData(ec._20);
				analysis.addEClassData(ec._21);
				analysis.addEClassData(ec._22);
				analysis.addEClassData(ec._23);
				analysis.addEClassData(ec._24);
				analysis.addEClassData(ec._25);
				analysis.addEClassData(ec._26);
				analysis.addEClassData(ec._27);
				analysis.addEClassData(ec._28);
				analysis.addEClassData(ec._29);
				analysis.addEClassData(ec._30);
				analysis.addEClassData(ec._31);
				analysis.addEClassData(ec._32);
				analysis.addEClassData(ec._33);
				analysis.addEClassData(ec._34);
				analysis.addEClassData(ec._35);
				analysis.addEClassData(ec._36);
				analysis.addEClassData(ec._37);
				analysis.addEClassData(ec._38);
				analysis.addEClassData(ec._39);
				analysis.addEClassData(ec._40);
				analysis.addEClassData(ec._41);
				analysis.addEClassData(ec._42);
				analysis.addEClassData(ec._43);
				analysis.addEClassData(ec._44);
				analysis.addEClassData(ec._45);
				analysis.addEClassData(ec._46);
				analysis.addEClassData(ec._47);
				analysis.addEClassData(ec._48);
				analysis.addEClassData(ec._49);
				analysis.addEClassData(ec._50);
				analysis.addEClassData(ec._51);
				analysis.addEClassData(ec._52);
				analysis.addEClassData(ec._53);
				analysis.addEClassData(ec._54);
				analysis.addEClassData(ec._55);
				analysis.addEClassData(ec._56);
				analysis.addEClassData(ec._57);
				analysis.addEClassData(ec._58);
				analysis.addEClassData(ec._59);
				analysis.addEClassData(ec._60);
				analysis.addEClassData(ec._61);
				analysis.addEClassData(ec._62);
				analysis.addEClassData(ec._63);
				analysis.addEClassData(ec._64);
				analysis.addEClassData(ec._65);
				analysis.addEClassData(ec._66);
				analysis.addEClassData(ec._67);
				analysis.addEClassData(ec._68);
				//	analysis.addSerializationRule(sr._000);
				//	analysis.addSerializationRule(sr._001);
				//	analysis.addSerializationRule(sr._002);
				//	analysis.addSerializationRule(sr._003);
				//	analysis.addSerializationRule(sr._004);
				//	analysis.addSerializationRule(sr._005);
				//	analysis.addSerializationRule(sr._006);
				//	analysis.addSerializationRule(sr._007);
				//	analysis.addSerializationRule(sr._008);
				//	analysis.addSerializationRule(sr._009);
				//	analysis.addSerializationRule(sr._010);
				//	analysis.addSerializationRule(sr._011);
				//	analysis.addSerializationRule(sr._012);
				//	analysis.addSerializationRule(sr._013);
				//	analysis.addSerializationRule(sr._014);
				//	analysis.addSerializationRule(sr._015);
				//	analysis.addSerializationRule(sr._016);
				//	analysis.addSerializationRule(sr._017);
				//	analysis.addSerializationRule(sr._018);
				//	analysis.addSerializationRule(sr._019);
				//	analysis.addSerializationRule(sr._020);
				//	analysis.addSerializationRule(sr._021);
				//	analysis.addSerializationRule(sr._022);
				//	analysis.addSerializationRule(sr._023);
				//	analysis.addSerializationRule(sr._024);
				//	analysis.addSerializationRule(sr._025);
				//	analysis.addSerializationRule(sr._026);
				//	analysis.addSerializationRule(sr._027);
				//	analysis.addSerializationRule(sr._028);
				//	analysis.addSerializationRule(sr._029);
				//	analysis.addSerializationRule(sr._030);
				//	analysis.addSerializationRule(sr._031);
				//	analysis.addSerializationRule(sr._032);
				//	analysis.addSerializationRule(sr._033);
				//	analysis.addSerializationRule(sr._034);
				//	analysis.addSerializationRule(sr._035);
				//	analysis.addSerializationRule(sr._036);
				//	analysis.addSerializationRule(sr._037);
				//	analysis.addSerializationRule(sr._038);
				//	analysis.addSerializationRule(sr._039);
				//	analysis.addSerializationRule(sr._040);
				//	analysis.addSerializationRule(sr._041);
				//	analysis.addSerializationRule(sr._042);
				//	analysis.addSerializationRule(sr._043);
				//	analysis.addSerializationRule(sr._044);
				//	analysis.addSerializationRule(sr._045);
				//	analysis.addSerializationRule(sr._046);
				//	analysis.addSerializationRule(sr._047);
				//	analysis.addSerializationRule(sr._048);
				//	analysis.addSerializationRule(sr._049);
				//	analysis.addSerializationRule(sr._050);
				//	analysis.addSerializationRule(sr._051);
				//	analysis.addSerializationRule(sr._052);
				//	analysis.addSerializationRule(sr._053);
				//	analysis.addSerializationRule(sr._054);
				//	analysis.addSerializationRule(sr._055);
				//	analysis.addSerializationRule(sr._056);
				//	analysis.addSerializationRule(sr._057);
				//	analysis.addSerializationRule(sr._058);
				//	analysis.addSerializationRule(sr._059);
				//	analysis.addSerializationRule(sr._060);
				//	analysis.addSerializationRule(sr._061);
				//	analysis.addSerializationRule(sr._062);
				//	analysis.addSerializationRule(sr._063);
				//	analysis.addSerializationRule(sr._064);
				//	analysis.addSerializationRule(sr._065);
				//	analysis.addSerializationRule(sr._066);
				//	analysis.addSerializationRule(sr._067);
				//	analysis.addSerializationRule(sr._068);
				//	analysis.addSerializationRule(sr._069);
				//	analysis.addSerializationRule(sr._070);
				//	analysis.addSerializationRule(sr._071);
				//	analysis.addSerializationRule(sr._072);
				//	analysis.addSerializationRule(sr._073);
				//	analysis.addSerializationRule(sr._074);
				//	analysis.addSerializationRule(sr._075);
				//	analysis.addSerializationRule(sr._076);
				//	analysis.addSerializationRule(sr._077);
				//	analysis.addSerializationRule(sr._078);
				//	analysis.addSerializationRule(sr._079);
				//	analysis.addSerializationRule(sr._080);
				//	analysis.addSerializationRule(sr._081);
				//	analysis.addSerializationRule(sr._082);
				//	analysis.addSerializationRule(sr._083);
				//	analysis.addSerializationRule(sr._084);
				//	analysis.addSerializationRule(sr._085);
				//	analysis.addSerializationRule(sr._086);
				//	analysis.addSerializationRule(sr._087);
				//	analysis.addSerializationRule(sr._088);
				//	analysis.addSerializationRule(sr._089);
				//	analysis.addSerializationRule(sr._090);
				//	analysis.addSerializationRule(sr._091);
				//	analysis.addSerializationRule(sr._092);
				//	analysis.addSerializationRule(sr._093);
				//	analysis.addSerializationRule(sr._094);
				//	analysis.addSerializationRule(sr._095);
				//	analysis.addSerializationRule(sr._096);
				//	analysis.addSerializationRule(sr._097);
				//	analysis.addSerializationRule(sr._098);
				//	analysis.addSerializationRule(sr._099);
				//	analysis.addSerializationRule(sr._100);
				//	analysis.addSerializationRule(sr._101);
				//	analysis.addSerializationRule(sr._102);
				//	analysis.addSerializationRule(sr._103);
				//	analysis.addSerializationRule(sr._104);
				//	analysis.addSerializationRule(sr._105);
				//	analysis.addSerializationRule(sr._106);
				//	analysis.addSerializationRule(sr._107);
				//	analysis.addSerializationRule(sr._108);
				//	analysis.addSerializationRule(sr._109);
				//	analysis.addSerializationRule(sr._110);
				//	analysis.addSerializationRule(sr._111);
				//	analysis.addSerializationRule(sr._112);
				//	analysis.addSerializationRule(sr._113);
				//	analysis.addSerializationRule(sr._114);
				//	analysis.addSerializationRule(sr._115);
				//	analysis.addSerializationRule(sr._116);
				//	analysis.addSerializationRule(sr._117);
				//	analysis.addSerializationRule(sr._118);
				//	analysis.addSerializationRule(sr._119);
				//	analysis.addSerializationRule(sr._120);
				//	analysis.addSerializationRule(sr._121);
				//	analysis.addSerializationRule(sr._122);
				//	analysis.addSerializationRule(sr._123);
				//	analysis.addSerializationRule(sr._124);
				//	analysis.addSerializationRule(sr._125);
				//	analysis.addSerializationRule(sr._126);
				//	analysis.addSerializationRule(sr._127);
				//	analysis.addSerializationRule(sr._128);
				//	analysis.addSerializationRule(sr._129);
				//	analysis.addSerializationRule(sr._130);
				//	analysis.addSerializationRule(sr._131);
				//	analysis.addSerializationRule(sr._132);
				//	analysis.addSerializationRule(sr._133);
				//	analysis.addSerializationRule(sr._134);
				//	analysis.addSerializationRule(sr._135);
				//	analysis.addSerializationRule(sr._136);
				//	analysis.addSerializationRule(sr._137);
				//	analysis.addSerializationRule(sr._138);
				//	analysis.addSerializationRule(sr._139);
				//	analysis.addSerializationRule(sr._140);
				//	analysis.addSerializationRule(sr._141);
				//	analysis.addSerializationRule(sr._142);
				//	analysis.addSerializationRule(sr._143);
				//	analysis.addSerializationRule(sr._144);
				//	analysis.addSerializationRule(sr._145);
				//	analysis.addSerializationRule(sr._146);
				//	analysis.addSerializationRule(sr._147);
				//	analysis.addSerializationRule(sr._148);
				//	analysis.addSerializationRule(sr._149);
				//	analysis.addSerializationRule(sr._150);
				//	analysis.addSerializationRule(sr._151);
				//	analysis.addSerializationRule(sr._152);
				//	analysis.addSerializationRule(sr._153);
				//	analysis.addSerializationRule(sr._154);
				//	analysis.addSerializationRule(sr._155);
				//	analysis.addSerializationRule(sr._156);
				//	analysis.addSerializationRule(sr._157);
				//	analysis.addSerializationRule(sr._158);
				//	analysis.addSerializationRule(sr._159);
				//	analysis.addSerializationRule(sr._160);
				//	analysis.addSerializationRule(sr._161);
				//	analysis.addSerializationRule(sr._162);
				//	analysis.addSerializationRule(sr._163);
				//	analysis.addSerializationRule(sr._164);
				//	analysis.addSerializationRule(sr._165);
				//	analysis.addSerializationRule(sr._166);
				//	analysis.addSerializationRule(sr._167);
				//	analysis.addSerializationRule(sr._168);
				//	analysis.addSerializationRule(sr._169);
				//	analysis.addSerializationRule(sr._170);
				//	analysis.addSerializationRule(sr._171);
				//	analysis.addSerializationRule(sr._172);
				//	analysis.addSerializationRule(sr._173);
				//	analysis.addSerializationRule(sr._174);
				//	analysis.addSerializationRule(sr._175);
				//	analysis.addSerializationRule(sr._176);
				//	analysis.addSerializationRule(sr._177);
				//	analysis.addSerializationRule(sr._178);
				//	analysis.addSerializationRule(sr._179);
				//	analysis.addSerializationRule(sr._180);
				//	analysis.addSerializationRule(sr._181);
				//	analysis.addSerializationRule(sr._182);
				//	analysis.addSerializationRule(sr._183);
				//	analysis.addSerializationRule(sr._184);
				//	analysis.addSerializationRule(sr._185);
				//	analysis.addSerializationRule(sr._186);
				//	analysis.addSerializationRule(sr._187);
				//	analysis.addSerializationRule(sr._188);
				//	analysis.addSerializationRule(sr._189);
				//	analysis.addSerializationRule(sr._190);
				//	analysis.addSerializationRule(sr._191);
				//	analysis.addSerializationRule(sr._192);
				//	analysis.addSerializationRule(sr._193);
				//	analysis.addSerializationRule(sr._194);
				//	analysis.addSerializationRule(sr._195);
				//	analysis.addSerializationRule(sr._196);
				//	analysis.addSerializationRule(sr._197);
				//	analysis.addSerializationRule(sr._198);
				//	analysis.addSerializationRule(sr._199);
				//	analysis.addSerializationRule(sr._200);
				//	analysis.addSerializationRule(sr._201);
				//	analysis.addSerializationRule(sr._202);
				//	analysis.addSerializationRule(sr._203);
				//	analysis.addSerializationRule(sr._204);
				//	analysis.addSerializationRule(sr._205);
				//	analysis.addSerializationRule(sr._206);
				//	analysis.addSerializationRule(sr._207);
				//	analysis.addSerializationRule(sr._208);
				//	analysis.addSerializationRule(sr._209);
				//	analysis.addSerializationRule(sr._210);
				//	analysis.addSerializationRule(sr._211);
				//	analysis.addSerializationRule(sr._212);
				//	analysis.addSerializationRule(sr._213);
				//	analysis.addSerializationRule(sr._214);
				//	analysis.addSerializationRule(sr._215);
				//	analysis.addSerializationRule(sr._216);
				//	analysis.addSerializationRule(sr._217);
				//	analysis.addSerializationRule(sr._218);
				//	analysis.addSerializationRule(sr._219);
			}
			return analysis;
		}

		private class _EnumValues
		{
			private final /*@NonNull*/ EnumerationValue _00 // '!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'
				= new org.eclipse.ocl.xtext.base.cs2text.enumerations.MultipleEnumerationValue(new String[]{"!composes", "!derived", "!ordered", "!readonly", "!resolve", "!transient", "!unique", "!unsettable", "!volatile", "composes", "derived", "ordered", "readonly", "resolve", "transient", "unique", "unsettable", "volatile"});
			private final /*@NonNull*/ EnumerationValue _01 // '!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'
				= new org.eclipse.ocl.xtext.base.cs2text.enumerations.MultipleEnumerationValue(new String[]{"!derived", "!id", "!ordered", "!readonly", "!transient", "!unique", "!unsettable", "!volatile", "derived", "id", "ordered", "readonly", "transient", "unique", "unsettable", "volatile"});
			private final /*@NonNull*/ EnumerationValue _02 // '!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'
				= new org.eclipse.ocl.xtext.base.cs2text.enumerations.MultipleEnumerationValue(new String[]{"!derived", "!ordered", "!transient", "!unique", "derived", "ordered", "transient", "unique"});
			private final /*@NonNull*/ EnumerationValue _03 // '!ordered|!unique|ordered|unique'
				= new org.eclipse.ocl.xtext.base.cs2text.enumerations.MultipleEnumerationValue(new String[]{"!ordered", "!unique", "ordered", "unique"});
			private final /*@NonNull*/ EnumerationValue _04 // '*|+|?'
				= new org.eclipse.ocl.xtext.base.cs2text.enumerations.MultipleEnumerationValue(new String[]{"*", "+", "?"});
			private final /*@NonNull*/ EnumerationValue _05 // ','
				= new org.eclipse.ocl.xtext.base.cs2text.enumerations.SingleEnumerationValue(",");
			private final /*@NonNull*/ EnumerationValue _06 // '::*'
				= new org.eclipse.ocl.xtext.base.cs2text.enumerations.SingleEnumerationValue("::*");
			private final /*@NonNull*/ EnumerationValue _07 // ';'
				= new org.eclipse.ocl.xtext.base.cs2text.enumerations.SingleEnumerationValue(";");
			private final /*@NonNull*/ EnumerationValue _08 // '@'
				= new org.eclipse.ocl.xtext.base.cs2text.enumerations.SingleEnumerationValue("@");
			private final /*@NonNull*/ EnumerationValue _09 // 'Map'
				= new org.eclipse.ocl.xtext.base.cs2text.enumerations.SingleEnumerationValue("Map");
			private final /*@NonNull*/ EnumerationValue _10 // 'Tuple'
				= new org.eclipse.ocl.xtext.base.cs2text.enumerations.SingleEnumerationValue("Tuple");
			private final /*@NonNull*/ EnumerationValue _11 // 'abstract'
				= new org.eclipse.ocl.xtext.base.cs2text.enumerations.SingleEnumerationValue("abstract");
			private final /*@NonNull*/ EnumerationValue _12 // 'callable'
				= new org.eclipse.ocl.xtext.base.cs2text.enumerations.SingleEnumerationValue("callable");
			private final /*@NonNull*/ EnumerationValue _13 // 'definition'
				= new org.eclipse.ocl.xtext.base.cs2text.enumerations.SingleEnumerationValue("definition");
			private final /*@NonNull*/ EnumerationValue _14 // 'false|true'
				= new org.eclipse.ocl.xtext.base.cs2text.enumerations.MultipleEnumerationValue(new String[]{"false", "true"});
			private final /*@NonNull*/ EnumerationValue _15 // 'interface'
				= new org.eclipse.ocl.xtext.base.cs2text.enumerations.SingleEnumerationValue("interface");
			private final /*@NonNull*/ EnumerationValue _16 // 'invariant'
				= new org.eclipse.ocl.xtext.base.cs2text.enumerations.SingleEnumerationValue("invariant");
			private final /*@NonNull*/ EnumerationValue _17 // 'postcondition'
				= new org.eclipse.ocl.xtext.base.cs2text.enumerations.SingleEnumerationValue("postcondition");
			private final /*@NonNull*/ EnumerationValue _18 // 'precondition'
				= new org.eclipse.ocl.xtext.base.cs2text.enumerations.SingleEnumerationValue("precondition");
			private final /*@NonNull*/ EnumerationValue _19 // 'primitive'
				= new org.eclipse.ocl.xtext.base.cs2text.enumerations.SingleEnumerationValue("primitive");
			private final /*@NonNull*/ EnumerationValue _20 // 'serializable'
				= new org.eclipse.ocl.xtext.base.cs2text.enumerations.SingleEnumerationValue("serializable");
			private final /*@NonNull*/ EnumerationValue _21 // 'static'
				= new org.eclipse.ocl.xtext.base.cs2text.enumerations.SingleEnumerationValue("static");
			private final /*@NonNull*/ EnumerationValue _22 // '|'
				= new org.eclipse.ocl.xtext.base.cs2text.enumerations.SingleEnumerationValue("|");
			private final /*@NonNull*/ EnumerationValue _23 // '|1'
				= new org.eclipse.ocl.xtext.base.cs2text.enumerations.SingleEnumerationValue("|1");
		}

		private class _MatchTerms
		{
			private final /*@NonNull*/ CardinalitySolution _000 // 0
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.IntegerCardinalitySolution(0);
			private final /*@NonNull*/ CardinalitySolution _001 // 1
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.IntegerCardinalitySolution(1);
			private final /*@NonNull*/ CardinalitySolution _002 // V0
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.VariableCardinalitySolution(0);
			private final /*@NonNull*/ CardinalitySolution _003 // |default|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__DEFAULT);
			private final /*@NonNull*/ CardinalitySolution _004 // |exprString|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.SPECIFICATION_CS__EXPR_STRING);
			private final /*@NonNull*/ CardinalitySolution _005 // |instanceClassName|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.CLASS_CS__INSTANCE_CLASS_NAME);
			private final /*@NonNull*/ CardinalitySolution _006 // |isAbstract.'abstract'|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EAttributeSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.STRUCTURED_CLASS_CS__IS_ABSTRACT, ev._11);
			private final /*@NonNull*/ CardinalitySolution _007 // |isAll.'::*'|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EAttributeSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.IMPORT_CS__IS_ALL, ev._06);
			private final /*@NonNull*/ CardinalitySolution _008 // |isCallable.'callable'|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EAttributeSizeCardinalitySolution(org.eclipse.ocl.xtext.oclinecorecs.OCLinEcoreCSPackage.Literals.OC_LIN_ECORE_CONSTRAINT_CS__IS_CALLABLE, ev._12);
			private final /*@NonNull*/ CardinalitySolution _009 // |isInterface.'interface'|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EAttributeSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.STRUCTURED_CLASS_CS__IS_INTERFACE, ev._15);
			private final /*@NonNull*/ CardinalitySolution _010 // |isNullFree.'|1'|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EAttributeSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MULTIPLICITY_CS__IS_NULL_FREE, ev._23);
			private final /*@NonNull*/ CardinalitySolution _011 // |isPre.'@'|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EAttributeSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__IS_PRE, ev._08);
			private final /*@NonNull*/ CardinalitySolution _012 // |isPrimitive.'primitive'|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EAttributeSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.DATA_TYPE_CS__IS_PRIMITIVE, ev._19);
			private final /*@NonNull*/ CardinalitySolution _013 // |isSerializable.'serializable'|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EAttributeSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.DATA_TYPE_CS__IS_SERIALIZABLE, ev._20);
			private final /*@NonNull*/ CardinalitySolution _014 // |isSerializable.'serializable'|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EAttributeSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ENUMERATION_CS__IS_SERIALIZABLE, ev._20);
			private final /*@NonNull*/ CardinalitySolution _015 // |literal|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ENUMERATION_LITERAL_CS__LITERAL);
			private final /*@NonNull*/ CardinalitySolution _016 // |lowerBound|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND);
			private final /*@NonNull*/ CardinalitySolution _017 // |name.'Map'|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EAttributeSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.MAP_TYPE_CS__NAME, ev._09);
			private final /*@NonNull*/ CardinalitySolution _018 // |name.'Tuple'|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EAttributeSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TUPLE_TYPE_CS__NAME, ev._10);
			private final /*@NonNull*/ CardinalitySolution _019 // |name|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PRIMITIVE_TYPE_REF_CS__NAME);
			private final /*@NonNull*/ CardinalitySolution _020 // |name|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME);
			private final /*@NonNull*/ CardinalitySolution _021 // |name|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__NAME);
			private final /*@NonNull*/ CardinalitySolution _022 // |nsPrefix|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PACKAGE_CS__NS_PREFIX);
			private final /*@NonNull*/ CardinalitySolution _023 // |nsURI|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PACKAGE_CS__NS_URI);
			private final /*@NonNull*/ CardinalitySolution _024 // |ownedActualParameter|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER);
			private final /*@NonNull*/ CardinalitySolution _025 // |ownedAnnotations|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS);
			private final /*@NonNull*/ CardinalitySolution _026 // |ownedArguments|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS);
			private final /*@NonNull*/ CardinalitySolution _027 // |ownedBinding|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING);
			private final /*@NonNull*/ CardinalitySolution _028 // |ownedBodyExpressions|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS__OWNED_BODY_EXPRESSIONS);
			private final /*@NonNull*/ CardinalitySolution _029 // |ownedClasses|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PACKAGE_CS__OWNED_CLASSES);
			private final /*@NonNull*/ CardinalitySolution _030 // |ownedCoIterator|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR);
			private final /*@NonNull*/ CardinalitySolution _031 // |ownedCollectionMultiplicity|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY);
			private final /*@NonNull*/ CardinalitySolution _032 // |ownedCondition|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION);
			private final /*@NonNull*/ CardinalitySolution _033 // |ownedCondition|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_CONDITION);
			private final /*@NonNull*/ CardinalitySolution _034 // |ownedConstraints|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS);
			private final /*@NonNull*/ CardinalitySolution _035 // |ownedContents|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ANNOTATION_CS__OWNED_CONTENTS);
			private final /*@NonNull*/ CardinalitySolution _036 // |ownedCurlyBracketedClause|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE);
			private final /*@NonNull*/ CardinalitySolution _037 // |ownedCurlyBracketedClause|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE);
			private final /*@NonNull*/ CardinalitySolution _038 // |ownedDefaultExpressions|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS);
			private final /*@NonNull*/ CardinalitySolution _039 // |ownedDetails|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS);
			private final /*@NonNull*/ CardinalitySolution _040 // |ownedElseExpression|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION);
			private final /*@NonNull*/ CardinalitySolution _041 // |ownedExceptions|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS);
			private final /*@NonNull*/ CardinalitySolution _042 // |ownedExpressionCS|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS);
			private final /*@NonNull*/ CardinalitySolution _043 // |ownedExpression|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION);
			private final /*@NonNull*/ CardinalitySolution _044 // |ownedExpression|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION);
			private final /*@NonNull*/ CardinalitySolution _045 // |ownedExpression|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.EXP_SPECIFICATION_CS__OWNED_EXPRESSION);
			private final /*@NonNull*/ CardinalitySolution _046 // |ownedExpression|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION);
			private final /*@NonNull*/ CardinalitySolution _047 // |ownedExtends|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS);
			private final /*@NonNull*/ CardinalitySolution _048 // |ownedExtends|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS);
			private final /*@NonNull*/ CardinalitySolution _049 // |ownedIfThenExpressions|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS);
			private final /*@NonNull*/ CardinalitySolution _050 // |ownedImplicitOpposites|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.REFERENCE_CS__OWNED_IMPLICIT_OPPOSITES);
			private final /*@NonNull*/ CardinalitySolution _051 // |ownedImports|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ROOT_CS__OWNED_IMPORTS);
			private final /*@NonNull*/ CardinalitySolution _052 // |ownedInExpression|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION);
			private final /*@NonNull*/ CardinalitySolution _053 // |ownedInitExpression|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION);
			private final /*@NonNull*/ CardinalitySolution _054 // |ownedInitExpression|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION);
			private final /*@NonNull*/ CardinalitySolution _055 // |ownedInitExpression|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION);
			private final /*@NonNull*/ CardinalitySolution _056 // |ownedKeyType|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE);
			private final /*@NonNull*/ CardinalitySolution _057 // |ownedKey|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_KEY);
			private final /*@NonNull*/ CardinalitySolution _058 // |ownedLastExpression|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_LAST_EXPRESSION);
			private final /*@NonNull*/ CardinalitySolution _059 // |ownedLeft|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.INFIX_EXP_CS__OWNED_LEFT);
			private final /*@NonNull*/ CardinalitySolution _060 // |ownedLiterals|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ENUMERATION_CS__OWNED_LITERALS);
			private final /*@NonNull*/ CardinalitySolution _061 // |ownedMessageSpecification|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION);
			private final /*@NonNull*/ CardinalitySolution _062 // |ownedMultiplicity|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY);
			private final /*@NonNull*/ CardinalitySolution _063 // |ownedMultiplicity|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY);
			private final /*@NonNull*/ CardinalitySolution _064 // |ownedNameExpression|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION);
			private final /*@NonNull*/ CardinalitySolution _065 // |ownedOperations|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_OPERATIONS);
			private final /*@NonNull*/ CardinalitySolution _066 // |ownedPackages|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES);
			private final /*@NonNull*/ CardinalitySolution _067 // |ownedParameters|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS);
			private final /*@NonNull*/ CardinalitySolution _068 // |ownedParameters|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS);
			private final /*@NonNull*/ CardinalitySolution _069 // |ownedParts|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS);
			private final /*@NonNull*/ CardinalitySolution _070 // |ownedParts|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS);
			private final /*@NonNull*/ CardinalitySolution _071 // |ownedParts|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS);
			private final /*@NonNull*/ CardinalitySolution _072 // |ownedParts|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS);
			private final /*@NonNull*/ CardinalitySolution _073 // |ownedParts|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS);
			private final /*@NonNull*/ CardinalitySolution _074 // |ownedParts|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS);
			private final /*@NonNull*/ CardinalitySolution _075 // |ownedPathElements|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS);
			private final /*@NonNull*/ CardinalitySolution _076 // |ownedPathName|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME);
			private final /*@NonNull*/ CardinalitySolution _077 // |ownedPathName|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME);
			private final /*@NonNull*/ CardinalitySolution _078 // |ownedPathName|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME);
			private final /*@NonNull*/ CardinalitySolution _079 // |ownedPathName|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.IMPORT_CS__OWNED_PATH_NAME);
			private final /*@NonNull*/ CardinalitySolution _080 // |ownedPathName|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MODEL_ELEMENT_REF_CS__OWNED_PATH_NAME);
			private final /*@NonNull*/ CardinalitySolution _081 // |ownedPatternGuard|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD);
			private final /*@NonNull*/ CardinalitySolution _082 // |ownedPatternType|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE);
			private final /*@NonNull*/ CardinalitySolution _083 // |ownedPostconditions|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS);
			private final /*@NonNull*/ CardinalitySolution _084 // |ownedPreconditions|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS);
			private final /*@NonNull*/ CardinalitySolution _085 // |ownedProperties|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_PROPERTIES);
			private final /*@NonNull*/ CardinalitySolution _086 // |ownedReferences|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ANNOTATION_CS__OWNED_REFERENCES);
			private final /*@NonNull*/ CardinalitySolution _087 // |ownedRight|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT);
			private final /*@NonNull*/ CardinalitySolution _088 // |ownedRoundBracketedClause|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE);
			private final /*@NonNull*/ CardinalitySolution _089 // |ownedRoundBracketedClause|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE);
			private final /*@NonNull*/ CardinalitySolution _090 // |ownedSignature|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE);
			private final /*@NonNull*/ CardinalitySolution _091 // |ownedSpecification|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION);
			private final /*@NonNull*/ CardinalitySolution _092 // |ownedSquareBracketedClauses|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES);
			private final /*@NonNull*/ CardinalitySolution _093 // |ownedSubstitutions|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS);
			private final /*@NonNull*/ CardinalitySolution _094 // |ownedSuperTypes|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_SUPER_TYPES);
			private final /*@NonNull*/ CardinalitySolution _095 // |ownedTerms|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS);
			private final /*@NonNull*/ CardinalitySolution _096 // |ownedThenExpression|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION);
			private final /*@NonNull*/ CardinalitySolution _097 // |ownedThenExpression|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION);
			private final /*@NonNull*/ CardinalitySolution _098 // |ownedType|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE);
			private final /*@NonNull*/ CardinalitySolution _099 // |ownedType|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE);
			private final /*@NonNull*/ CardinalitySolution _100 // |ownedType|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE);
			private final /*@NonNull*/ CardinalitySolution _101 // |ownedType|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE);
			private final /*@NonNull*/ CardinalitySolution _102 // |ownedType|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE);
			private final /*@NonNull*/ CardinalitySolution _103 // |ownedType|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE);
			private final /*@NonNull*/ CardinalitySolution _104 // |ownedType|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE);
			private final /*@NonNull*/ CardinalitySolution _105 // |ownedType|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE);
			private final /*@NonNull*/ CardinalitySolution _106 // |ownedValueType|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE);
			private final /*@NonNull*/ CardinalitySolution _107 // |ownedValue|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_VALUE);
			private final /*@NonNull*/ CardinalitySolution _108 // |ownedVariables|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES);
			private final /*@NonNull*/ CardinalitySolution _109 // |patternVariableName|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__PATTERN_VARIABLE_NAME);
			private final /*@NonNull*/ CardinalitySolution _110 // |prefix.','|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EAttributeSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, ev._05);
			private final /*@NonNull*/ CardinalitySolution _111 // |prefix.';'|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EAttributeSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, ev._07);
			private final /*@NonNull*/ CardinalitySolution _112 // |prefix.'|'|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EAttributeSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__PREFIX, ev._22);
			private final /*@NonNull*/ CardinalitySolution _113 // |qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EAttributeSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS, ev._00);
			private final /*@NonNull*/ CardinalitySolution _114 // |qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EAttributeSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS, ev._01);
			private final /*@NonNull*/ CardinalitySolution _115 // |qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EAttributeSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS, ev._02);
			private final /*@NonNull*/ CardinalitySolution _116 // |qualifiers.'!ordered|!unique|ordered|unique'|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EAttributeSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS, ev._03);
			private final /*@NonNull*/ CardinalitySolution _117 // |qualifiers.'definition'|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EAttributeSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS, ev._13);
			private final /*@NonNull*/ CardinalitySolution _118 // |qualifiers.'static'|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EAttributeSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS, ev._21);
			private final /*@NonNull*/ CardinalitySolution _119 // |referredElement|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT);
			private final /*@NonNull*/ CardinalitySolution _120 // |referredKeys|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.REFERENCE_CS__REFERRED_KEYS);
			private final /*@NonNull*/ CardinalitySolution _121 // |referredOpposite|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.REFERENCE_CS__REFERRED_OPPOSITE);
			private final /*@NonNull*/ CardinalitySolution _122 // |referredProperty|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.SHADOW_PART_CS__REFERRED_PROPERTY);
			private final /*@NonNull*/ CardinalitySolution _123 // |restVariableName|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__REST_VARIABLE_NAME);
			private final /*@NonNull*/ CardinalitySolution _124 // |segments|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.STRING_LITERAL_EXP_CS__SEGMENTS);
			private final /*@NonNull*/ CardinalitySolution _125 // |stereotype.'invariant'|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EAttributeSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.CONSTRAINT_CS__STEREOTYPE, ev._16);
			private final /*@NonNull*/ CardinalitySolution _126 // |stereotype.'postcondition'|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EAttributeSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.CONSTRAINT_CS__STEREOTYPE, ev._17);
			private final /*@NonNull*/ CardinalitySolution _127 // |stereotype.'precondition'|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EAttributeSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.CONSTRAINT_CS__STEREOTYPE, ev._18);
			private final /*@NonNull*/ CardinalitySolution _128 // |stringBounds.'*|+|?'|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EAttributeSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS, ev._04);
			private final /*@NonNull*/ CardinalitySolution _129 // |symbol.'false|true'|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EAttributeSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS__SYMBOL, ev._14);
			private final /*@NonNull*/ CardinalitySolution _130 // |symbol|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NUMBER_LITERAL_EXP_CS__SYMBOL);
			private final /*@NonNull*/ CardinalitySolution _131 // |upperBound|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__UPPER_BOUND);
			private final /*@NonNull*/ CardinalitySolution _132 // |values|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.DETAIL_CS__VALUES);
			private final /*@NonNull*/ CardinalitySolution _133 // |value|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ENUMERATION_LITERAL_CS__VALUE);
			private final /*@NonNull*/ CardinalitySolution _134 // |value|
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.EStructuralFeatureSizeCardinalitySolution(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.DOCUMENTATION_CS__VALUE);
			private final /*@NonNull*/ CardinalitySolution _135 // (|exprString| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_004, _001);
			private final /*@NonNull*/ CardinalitySolution _136 // (|isInterface.'interface'| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.GreaterThanCardinalitySolution(_009, _000);
			private final /*@NonNull*/ CardinalitySolution _137 // (|isSerializable.'serializable'| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.GreaterThanCardinalitySolution(_013, _000);
			private final /*@NonNull*/ CardinalitySolution _138 // (|isSerializable.'serializable'| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.GreaterThanCardinalitySolution(_014, _000);
			private final /*@NonNull*/ CardinalitySolution _139 // (|lowerBound| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_016, _001);
			private final /*@NonNull*/ CardinalitySolution _140 // (|name.'Map'| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_017, _001);
			private final /*@NonNull*/ CardinalitySolution _141 // (|name.'Tuple'| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_018, _001);
			private final /*@NonNull*/ CardinalitySolution _142 // (|name| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_020, _001);
			private final /*@NonNull*/ CardinalitySolution _143 // (|name| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_021, _001);
			private final /*@NonNull*/ CardinalitySolution _144 // (|name| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_019, _001);
			private final /*@NonNull*/ CardinalitySolution _145 // (|ownedActualParameter| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_024, _001);
			private final /*@NonNull*/ CardinalitySolution _146 // (|ownedAnnotations| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.GreaterThanCardinalitySolution(_025, _000);
			private final /*@NonNull*/ CardinalitySolution _147 // (|ownedArguments| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_026, _001);
			private final /*@NonNull*/ CardinalitySolution _148 // (|ownedArguments| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.GreaterThanCardinalitySolution(_026, _000);
			private final /*@NonNull*/ CardinalitySolution _149 // (|ownedBinding| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_027, _001);
			private final /*@NonNull*/ CardinalitySolution _150 // (|ownedBodyExpressions| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.GreaterThanCardinalitySolution(_028, _000);
			private final /*@NonNull*/ CardinalitySolution _151 // (|ownedCoIterator| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_030, _001);
			private final /*@NonNull*/ CardinalitySolution _152 // (|ownedCondition| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_033, _001);
			private final /*@NonNull*/ CardinalitySolution _153 // (|ownedCondition| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_032, _001);
			private final /*@NonNull*/ CardinalitySolution _154 // (|ownedDefaultExpressions| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.GreaterThanCardinalitySolution(_038, _000);
			private final /*@NonNull*/ CardinalitySolution _155 // (|ownedDetails| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_039, _001);
			private final /*@NonNull*/ CardinalitySolution _156 // (|ownedDetails| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.GreaterThanCardinalitySolution(_039, _000);
			private final /*@NonNull*/ CardinalitySolution _157 // (|ownedElseExpression| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_040, _001);
			private final /*@NonNull*/ CardinalitySolution _158 // (|ownedExceptions| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_041, _001);
			private final /*@NonNull*/ CardinalitySolution _159 // (|ownedExceptions| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.GreaterThanCardinalitySolution(_041, _000);
			private final /*@NonNull*/ CardinalitySolution _160 // (|ownedExpressionCS| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_042, _001);
			private final /*@NonNull*/ CardinalitySolution _161 // (|ownedExpression| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_045, _001);
			private final /*@NonNull*/ CardinalitySolution _162 // (|ownedExpression| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_046, _001);
			private final /*@NonNull*/ CardinalitySolution _163 // (|ownedExpression| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_043, _001);
			private final /*@NonNull*/ CardinalitySolution _164 // (|ownedExpression| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_044, _001);
			private final /*@NonNull*/ CardinalitySolution _165 // (|ownedExtends| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_047, _001);
			private final /*@NonNull*/ CardinalitySolution _166 // (|ownedExtends| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.GreaterThanCardinalitySolution(_047, _000);
			private final /*@NonNull*/ CardinalitySolution _167 // (|ownedInExpression| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_052, _001);
			private final /*@NonNull*/ CardinalitySolution _168 // (|ownedInitExpression| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_054, _001);
			private final /*@NonNull*/ CardinalitySolution _169 // (|ownedInitExpression| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_053, _001);
			private final /*@NonNull*/ CardinalitySolution _170 // (|ownedInitExpression| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_055, _001);
			private final /*@NonNull*/ CardinalitySolution _171 // (|ownedKeyType| - V0)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_056, _002);
			private final /*@NonNull*/ CardinalitySolution _172 // (|ownedKey| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_057, _001);
			private final /*@NonNull*/ CardinalitySolution _173 // (|ownedLeft| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_059, _001);
			private final /*@NonNull*/ CardinalitySolution _174 // (|ownedNameExpression| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_064, _001);
			private final /*@NonNull*/ CardinalitySolution _175 // (|ownedParameters| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_068, _001);
			private final /*@NonNull*/ CardinalitySolution _176 // (|ownedParameters| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_067, _001);
			private final /*@NonNull*/ CardinalitySolution _177 // (|ownedParameters| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.GreaterThanCardinalitySolution(_068, _000);
			private final /*@NonNull*/ CardinalitySolution _178 // (|ownedParts| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_074, _001);
			private final /*@NonNull*/ CardinalitySolution _179 // (|ownedParts| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_073, _001);
			private final /*@NonNull*/ CardinalitySolution _180 // (|ownedParts| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_069, _001);
			private final /*@NonNull*/ CardinalitySolution _181 // (|ownedParts| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_072, _001);
			private final /*@NonNull*/ CardinalitySolution _182 // (|ownedParts| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_070, _001);
			private final /*@NonNull*/ CardinalitySolution _183 // (|ownedParts| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_071, _001);
			private final /*@NonNull*/ CardinalitySolution _184 // (|ownedParts| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.GreaterThanCardinalitySolution(_069, _000);
			private final /*@NonNull*/ CardinalitySolution _185 // (|ownedParts| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.GreaterThanCardinalitySolution(_071, _000);
			private final /*@NonNull*/ CardinalitySolution _186 // (|ownedParts| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.GreaterThanCardinalitySolution(_074, _000);
			private final /*@NonNull*/ CardinalitySolution _187 // (|ownedParts| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.GreaterThanCardinalitySolution(_072, _000);
			private final /*@NonNull*/ CardinalitySolution _188 // (|ownedPathElements| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_075, _001);
			private final /*@NonNull*/ CardinalitySolution _189 // (|ownedPathName| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_079, _001);
			private final /*@NonNull*/ CardinalitySolution _190 // (|ownedPathName| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_080, _001);
			private final /*@NonNull*/ CardinalitySolution _191 // (|ownedPathName| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_076, _001);
			private final /*@NonNull*/ CardinalitySolution _192 // (|ownedPathName| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_077, _001);
			private final /*@NonNull*/ CardinalitySolution _193 // (|ownedPathName| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_078, _001);
			private final /*@NonNull*/ CardinalitySolution _194 // (|ownedPatternType| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_082, _001);
			private final /*@NonNull*/ CardinalitySolution _195 // (|ownedRight| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_087, _001);
			private final /*@NonNull*/ CardinalitySolution _196 // (|ownedSubstitutions| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_093, _001);
			private final /*@NonNull*/ CardinalitySolution _197 // (|ownedSuperTypes| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_094, _001);
			private final /*@NonNull*/ CardinalitySolution _198 // (|ownedSuperTypes| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.GreaterThanCardinalitySolution(_094, _000);
			private final /*@NonNull*/ CardinalitySolution _199 // (|ownedTerms| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_095, _001);
			private final /*@NonNull*/ CardinalitySolution _200 // (|ownedThenExpression| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_096, _001);
			private final /*@NonNull*/ CardinalitySolution _201 // (|ownedThenExpression| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_097, _001);
			private final /*@NonNull*/ CardinalitySolution _202 // (|ownedType| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_103, _001);
			private final /*@NonNull*/ CardinalitySolution _203 // (|ownedType| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_104, _001);
			private final /*@NonNull*/ CardinalitySolution _204 // (|ownedType| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_105, _001);
			private final /*@NonNull*/ CardinalitySolution _205 // (|ownedType| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_099, _001);
			private final /*@NonNull*/ CardinalitySolution _206 // (|ownedType| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_101, _001);
			private final /*@NonNull*/ CardinalitySolution _207 // (|ownedType| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_098, _001);
			private final /*@NonNull*/ CardinalitySolution _208 // (|ownedValue| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_107, _001);
			private final /*@NonNull*/ CardinalitySolution _209 // (|ownedVariables| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_108, _001);
			private final /*@NonNull*/ CardinalitySolution _210 // (|prefix.','| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_110, _001);
			private final /*@NonNull*/ CardinalitySolution _211 // (|prefix.';'| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_111, _001);
			private final /*@NonNull*/ CardinalitySolution _212 // (|prefix.'|'| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_112, _001);
			private final /*@NonNull*/ CardinalitySolution _213 // (|qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.GreaterThanCardinalitySolution(_113, _000);
			private final /*@NonNull*/ CardinalitySolution _214 // (|qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.GreaterThanCardinalitySolution(_114, _000);
			private final /*@NonNull*/ CardinalitySolution _215 // (|qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.GreaterThanCardinalitySolution(_115, _000);
			private final /*@NonNull*/ CardinalitySolution _216 // (|qualifiers.'!ordered|!unique|ordered|unique'| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.GreaterThanCardinalitySolution(_116, _000);
			private final /*@NonNull*/ CardinalitySolution _217 // (|qualifiers.'definition'| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_117, _001);
			private final /*@NonNull*/ CardinalitySolution _218 // (|qualifiers.'static'| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_118, _001);
			private final /*@NonNull*/ CardinalitySolution _219 // (|referredElement| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_119, _001);
			private final /*@NonNull*/ CardinalitySolution _220 // (|referredKeys| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_120, _001);
			private final /*@NonNull*/ CardinalitySolution _221 // (|referredKeys| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.GreaterThanCardinalitySolution(_120, _000);
			private final /*@NonNull*/ CardinalitySolution _222 // (|referredProperty| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_122, _001);
			private final /*@NonNull*/ CardinalitySolution _223 // (|stereotype.'invariant'| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_125, _001);
			private final /*@NonNull*/ CardinalitySolution _224 // (|stereotype.'postcondition'| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_126, _001);
			private final /*@NonNull*/ CardinalitySolution _225 // (|stereotype.'precondition'| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_127, _001);
			private final /*@NonNull*/ CardinalitySolution _226 // (|stringBounds.'*|+|?'| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_128, _001);
			private final /*@NonNull*/ CardinalitySolution _227 // (|symbol.'false|true'| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_129, _001);
			private final /*@NonNull*/ CardinalitySolution _228 // (|symbol| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.solutions.SubtractCardinalitySolution(_130, _001);
		}

		private class _MatchSteps
		{
			private final /*@NonNull*/ CardinalitySolutionStep _000 // assert (|exprString| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._135);
			private final /*@NonNull*/ CardinalitySolutionStep _001 // assert (|lowerBound| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._139);
			private final /*@NonNull*/ CardinalitySolutionStep _002 // assert (|name.'Map'| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._140);
			private final /*@NonNull*/ CardinalitySolutionStep _003 // assert (|name.'Tuple'| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._141);
			private final /*@NonNull*/ CardinalitySolutionStep _004 // assert (|name| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._143);
			private final /*@NonNull*/ CardinalitySolutionStep _005 // assert (|name| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._142);
			private final /*@NonNull*/ CardinalitySolutionStep _006 // assert (|name| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._144);
			private final /*@NonNull*/ CardinalitySolutionStep _007 // assert (|ownedActualParameter| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._145);
			private final /*@NonNull*/ CardinalitySolutionStep _008 // assert (|ownedBinding| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._149);
			private final /*@NonNull*/ CardinalitySolutionStep _009 // assert (|ownedCoIterator| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._151);
			private final /*@NonNull*/ CardinalitySolutionStep _010 // assert (|ownedCondition| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._152);
			private final /*@NonNull*/ CardinalitySolutionStep _011 // assert (|ownedCondition| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._153);
			private final /*@NonNull*/ CardinalitySolutionStep _012 // assert (|ownedDetails| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._155);
			private final /*@NonNull*/ CardinalitySolutionStep _013 // assert (|ownedElseExpression| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._157);
			private final /*@NonNull*/ CardinalitySolutionStep _014 // assert (|ownedExpressionCS| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._160);
			private final /*@NonNull*/ CardinalitySolutionStep _015 // assert (|ownedExpression| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._162);
			private final /*@NonNull*/ CardinalitySolutionStep _016 // assert (|ownedExpression| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._163);
			private final /*@NonNull*/ CardinalitySolutionStep _017 // assert (|ownedExpression| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._161);
			private final /*@NonNull*/ CardinalitySolutionStep _018 // assert (|ownedExpression| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._164);
			private final /*@NonNull*/ CardinalitySolutionStep _019 // assert (|ownedInExpression| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._167);
			private final /*@NonNull*/ CardinalitySolutionStep _020 // assert (|ownedInitExpression| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._169);
			private final /*@NonNull*/ CardinalitySolutionStep _021 // assert (|ownedInitExpression| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._168);
			private final /*@NonNull*/ CardinalitySolutionStep _022 // assert (|ownedInitExpression| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._170);
			private final /*@NonNull*/ CardinalitySolutionStep _023 // assert (|ownedKeyType| - V0) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._171);
			private final /*@NonNull*/ CardinalitySolutionStep _024 // assert (|ownedKey| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._172);
			private final /*@NonNull*/ CardinalitySolutionStep _025 // assert (|ownedLeft| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._173);
			private final /*@NonNull*/ CardinalitySolutionStep _026 // assert (|ownedNameExpression| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._174);
			private final /*@NonNull*/ CardinalitySolutionStep _027 // assert (|ownedPathElements| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._188);
			private final /*@NonNull*/ CardinalitySolutionStep _028 // assert (|ownedPathName| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._191);
			private final /*@NonNull*/ CardinalitySolutionStep _029 // assert (|ownedPathName| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._189);
			private final /*@NonNull*/ CardinalitySolutionStep _030 // assert (|ownedPathName| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._192);
			private final /*@NonNull*/ CardinalitySolutionStep _031 // assert (|ownedPathName| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._193);
			private final /*@NonNull*/ CardinalitySolutionStep _032 // assert (|ownedPathName| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._190);
			private final /*@NonNull*/ CardinalitySolutionStep _033 // assert (|ownedPatternType| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._194);
			private final /*@NonNull*/ CardinalitySolutionStep _034 // assert (|ownedRight| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._195);
			private final /*@NonNull*/ CardinalitySolutionStep _035 // assert (|ownedThenExpression| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._201);
			private final /*@NonNull*/ CardinalitySolutionStep _036 // assert (|ownedThenExpression| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._200);
			private final /*@NonNull*/ CardinalitySolutionStep _037 // assert (|ownedType| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._202);
			private final /*@NonNull*/ CardinalitySolutionStep _038 // assert (|ownedType| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._204);
			private final /*@NonNull*/ CardinalitySolutionStep _039 // assert (|ownedType| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._207);
			private final /*@NonNull*/ CardinalitySolutionStep _040 // assert (|ownedType| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._205);
			private final /*@NonNull*/ CardinalitySolutionStep _041 // assert (|ownedType| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._206);
			private final /*@NonNull*/ CardinalitySolutionStep _042 // assert (|ownedType| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._203);
			private final /*@NonNull*/ CardinalitySolutionStep _043 // assert (|ownedValue| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._208);
			private final /*@NonNull*/ CardinalitySolutionStep _044 // assert (|prefix.','| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._210);
			private final /*@NonNull*/ CardinalitySolutionStep _045 // assert (|prefix.';'| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._211);
			private final /*@NonNull*/ CardinalitySolutionStep _046 // assert (|prefix.'|'| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._212);
			private final /*@NonNull*/ CardinalitySolutionStep _047 // assert (|qualifiers.'definition'| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._217);
			private final /*@NonNull*/ CardinalitySolutionStep _048 // assert (|qualifiers.'static'| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._218);
			private final /*@NonNull*/ CardinalitySolutionStep _049 // assert (|referredElement| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._219);
			private final /*@NonNull*/ CardinalitySolutionStep _050 // assert (|referredProperty| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._222);
			private final /*@NonNull*/ CardinalitySolutionStep _051 // assert (|stereotype.'invariant'| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._223);
			private final /*@NonNull*/ CardinalitySolutionStep _052 // assert (|stereotype.'postcondition'| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._224);
			private final /*@NonNull*/ CardinalitySolutionStep _053 // assert (|stereotype.'precondition'| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._225);
			private final /*@NonNull*/ CardinalitySolutionStep _054 // assert (|stringBounds.'*|+|?'| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._226);
			private final /*@NonNull*/ CardinalitySolutionStep _055 // assert (|symbol.'false|true'| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._227);
			private final /*@NonNull*/ CardinalitySolutionStep _056 // assert (|symbol| - 1) == 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assert(mt._228);
			private final /*@NonNull*/ CardinalitySolutionStep _057 // assign V0 = (|ownedArguments| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, mt._148);
			private final /*@NonNull*/ CardinalitySolutionStep _058 // assign V0 = (|ownedExtends| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, mt._166);
			private final /*@NonNull*/ CardinalitySolutionStep _059 // assign V0 = (|ownedParameters| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, mt._176);
			private final /*@NonNull*/ CardinalitySolutionStep _060 // assign V0 = (|ownedParts| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, mt._182);
			private final /*@NonNull*/ CardinalitySolutionStep _061 // assign V0 = (|ownedParts| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, mt._185);
			private final /*@NonNull*/ CardinalitySolutionStep _062 // assign V0 = (|ownedParts| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, mt._184);
			private final /*@NonNull*/ CardinalitySolutionStep _063 // assign V0 = (|ownedParts| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, mt._186);
			private final /*@NonNull*/ CardinalitySolutionStep _064 // assign V0 = (|ownedParts| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, mt._187);
			private final /*@NonNull*/ CardinalitySolutionStep _065 // assign V0 = (|ownedPathElements| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, mt._188);
			private final /*@NonNull*/ CardinalitySolutionStep _066 // assign V0 = (|ownedSubstitutions| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, mt._196);
			private final /*@NonNull*/ CardinalitySolutionStep _067 // assign V0 = (|ownedTerms| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, mt._199);
			private final /*@NonNull*/ CardinalitySolutionStep _068 // assign V0 = (|ownedVariables| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, mt._209);
			private final /*@NonNull*/ CardinalitySolutionStep _069 // assign V0 = (|qualifiers.'!ordered|!unique|ordered|unique'| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, mt._216);
			private final /*@NonNull*/ CardinalitySolutionStep _070 // assign V0 = 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, mt._000);
			private final /*@NonNull*/ CardinalitySolutionStep _071 // assign V0 = |isAbstract.'abstract'|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, mt._006);
			private final /*@NonNull*/ CardinalitySolutionStep _072 // assign V0 = |isCallable.'callable'|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, mt._008);
			private final /*@NonNull*/ CardinalitySolutionStep _073 // assign V0 = |isNullFree.'|1'|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, mt._010);
			private final /*@NonNull*/ CardinalitySolutionStep _074 // assign V0 = |isPrimitive.'primitive'|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, mt._012);
			private final /*@NonNull*/ CardinalitySolutionStep _075 // assign V0 = |literal|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, mt._015);
			private final /*@NonNull*/ CardinalitySolutionStep _076 // assign V0 = |name|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, mt._020);
			private final /*@NonNull*/ CardinalitySolutionStep _077 // assign V0 = |nsPrefix|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, mt._022);
			private final /*@NonNull*/ CardinalitySolutionStep _078 // assign V0 = |ownedCoIterator|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, mt._030);
			private final /*@NonNull*/ CardinalitySolutionStep _079 // assign V0 = |ownedCurlyBracketedClause|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, mt._036);
			private final /*@NonNull*/ CardinalitySolutionStep _080 // assign V0 = |ownedDetails|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, mt._039);
			private final /*@NonNull*/ CardinalitySolutionStep _081 // assign V0 = |ownedExtends|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, mt._048);
			private final /*@NonNull*/ CardinalitySolutionStep _082 // assign V0 = |ownedIfThenExpressions|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, mt._049);
			private final /*@NonNull*/ CardinalitySolutionStep _083 // assign V0 = |ownedInitExpression|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, mt._055);
			private final /*@NonNull*/ CardinalitySolutionStep _084 // assign V0 = |ownedLastExpression|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, mt._058);
			private final /*@NonNull*/ CardinalitySolutionStep _085 // assign V0 = |ownedMultiplicity|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, mt._062);
			private final /*@NonNull*/ CardinalitySolutionStep _086 // assign V0 = |ownedRoundBracketedClause|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, mt._088);
			private final /*@NonNull*/ CardinalitySolutionStep _087 // assign V0 = |ownedSignature|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, mt._090);
			private final /*@NonNull*/ CardinalitySolutionStep _088 // assign V0 = |ownedSquareBracketedClauses|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, mt._092);
			private final /*@NonNull*/ CardinalitySolutionStep _089 // assign V0 = |ownedType|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, mt._099);
			private final /*@NonNull*/ CardinalitySolutionStep _090 // assign V0 = |ownedType|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, mt._100);
			private final /*@NonNull*/ CardinalitySolutionStep _091 // assign V0 = |ownedType|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, mt._102);
			private final /*@NonNull*/ CardinalitySolutionStep _092 // assign V0 = |ownedType|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, mt._098);
			private final /*@NonNull*/ CardinalitySolutionStep _093 // assign V0 = |ownedValueType|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, mt._106);
			private final /*@NonNull*/ CardinalitySolutionStep _094 // assign V0 = |patternVariableName|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, mt._109);
			private final /*@NonNull*/ CardinalitySolutionStep _095 // assign V0 = |qualifiers.'definition'|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, mt._117);
			private final /*@NonNull*/ CardinalitySolutionStep _096 // assign V0 = |qualifiers.'static'|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, mt._118);
			private final /*@NonNull*/ CardinalitySolutionStep _097 // assign V0 = |referredOpposite|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, mt._121);
			private final /*@NonNull*/ CardinalitySolutionStep _098 // assign V0 = |restVariableName|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, mt._123);
			private final /*@NonNull*/ CardinalitySolutionStep _099 // assign V0 = |segments|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, mt._124);
			private final /*@NonNull*/ CardinalitySolutionStep _100 // assign V0 = |upperBound|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, mt._131);
			private final /*@NonNull*/ CardinalitySolutionStep _101 // assign V0 = |values|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, mt._132);
			private final /*@NonNull*/ CardinalitySolutionStep _102 // assign V0 = |value|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(0, mt._134);
			private final /*@NonNull*/ CardinalitySolutionStep _103 // assign V1 = (|ownedArguments| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(1, mt._147);
			private final /*@NonNull*/ CardinalitySolutionStep _104 // assign V1 = (|ownedDetails| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(1, mt._156);
			private final /*@NonNull*/ CardinalitySolutionStep _105 // assign V1 = (|ownedExtends| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(1, mt._165);
			private final /*@NonNull*/ CardinalitySolutionStep _106 // assign V1 = (|ownedParameters| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(1, mt._177);
			private final /*@NonNull*/ CardinalitySolutionStep _107 // assign V1 = (|ownedParts| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(1, mt._179);
			private final /*@NonNull*/ CardinalitySolutionStep _108 // assign V1 = (|ownedParts| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(1, mt._183);
			private final /*@NonNull*/ CardinalitySolutionStep _109 // assign V1 = (|ownedParts| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(1, mt._181);
			private final /*@NonNull*/ CardinalitySolutionStep _110 // assign V1 = (|ownedParts| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(1, mt._180);
			private final /*@NonNull*/ CardinalitySolutionStep _111 // assign V1 = (|ownedParts| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(1, mt._186);
			private final /*@NonNull*/ CardinalitySolutionStep _112 // assign V1 = (|qualifiers.'!ordered|!unique|ordered|unique'| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(1, mt._216);
			private final /*@NonNull*/ CardinalitySolutionStep _113 // assign V1 = |default|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(1, mt._003);
			private final /*@NonNull*/ CardinalitySolutionStep _114 // assign V1 = |instanceClassName|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(1, mt._005);
			private final /*@NonNull*/ CardinalitySolutionStep _115 // assign V1 = |isAll.'::*'|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(1, mt._007);
			private final /*@NonNull*/ CardinalitySolutionStep _116 // assign V1 = |isNullFree.'|1'|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(1, mt._010);
			private final /*@NonNull*/ CardinalitySolutionStep _117 // assign V1 = |name|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(1, mt._020);
			private final /*@NonNull*/ CardinalitySolutionStep _118 // assign V1 = |nsURI|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(1, mt._023);
			private final /*@NonNull*/ CardinalitySolutionStep _119 // assign V1 = |ownedCoIterator|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(1, mt._030);
			private final /*@NonNull*/ CardinalitySolutionStep _120 // assign V1 = |ownedCollectionMultiplicity|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(1, mt._031);
			private final /*@NonNull*/ CardinalitySolutionStep _121 // assign V1 = |ownedImports|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(1, mt._051);
			private final /*@NonNull*/ CardinalitySolutionStep _122 // assign V1 = |ownedInitExpression|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(1, mt._055);
			private final /*@NonNull*/ CardinalitySolutionStep _123 // assign V1 = |ownedMessageSpecification|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(1, mt._061);
			private final /*@NonNull*/ CardinalitySolutionStep _124 // assign V1 = |ownedMultiplicity|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(1, mt._063);
			private final /*@NonNull*/ CardinalitySolutionStep _125 // assign V1 = |ownedMultiplicity|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(1, mt._062);
			private final /*@NonNull*/ CardinalitySolutionStep _126 // assign V1 = |ownedPatternGuard|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(1, mt._081);
			private final /*@NonNull*/ CardinalitySolutionStep _127 // assign V1 = |ownedRoundBracketedClause|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(1, mt._089);
			private final /*@NonNull*/ CardinalitySolutionStep _128 // assign V1 = |ownedSignature|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(1, mt._090);
			private final /*@NonNull*/ CardinalitySolutionStep _129 // assign V1 = |ownedType|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(1, mt._102);
			private final /*@NonNull*/ CardinalitySolutionStep _130 // assign V1 = |ownedType|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(1, mt._098);
			private final /*@NonNull*/ CardinalitySolutionStep _131 // assign V1 = |qualifiers.'!ordered|!unique|ordered|unique'|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(1, mt._116);
			private final /*@NonNull*/ CardinalitySolutionStep _132 // assign V1 = |referredOpposite|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(1, mt._121);
			private final /*@NonNull*/ CardinalitySolutionStep _133 // assign V1 = |value|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(1, mt._133);
			private final /*@NonNull*/ CardinalitySolutionStep _134 // assign V10 = (|ownedBodyExpressions| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(10, mt._150);
			private final /*@NonNull*/ CardinalitySolutionStep _135 // assign V10 = (|ownedDefaultExpressions| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(10, mt._154);
			private final /*@NonNull*/ CardinalitySolutionStep _136 // assign V10 = 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(10, mt._000);
			private final /*@NonNull*/ CardinalitySolutionStep _137 // assign V10 = |ownedConstraints|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(10, mt._034);
			private final /*@NonNull*/ CardinalitySolutionStep _138 // assign V10 = |ownedPreconditions|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(10, mt._084);
			private final /*@NonNull*/ CardinalitySolutionStep _139 // assign V11 = (|ownedBodyExpressions| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(11, mt._150);
			private final /*@NonNull*/ CardinalitySolutionStep _140 // assign V11 = 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(11, mt._000);
			private final /*@NonNull*/ CardinalitySolutionStep _141 // assign V11 = |ownedBodyExpressions|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(11, mt._028);
			private final /*@NonNull*/ CardinalitySolutionStep _142 // assign V12 = 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(12, mt._000);
			private final /*@NonNull*/ CardinalitySolutionStep _143 // assign V12 = |ownedBodyExpressions|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(12, mt._028);
			private final /*@NonNull*/ CardinalitySolutionStep _144 // assign V12 = |ownedImplicitOpposites|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(12, mt._050);
			private final /*@NonNull*/ CardinalitySolutionStep _145 // assign V12 = |ownedPostconditions|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(12, mt._083);
			private final /*@NonNull*/ CardinalitySolutionStep _146 // assign V13 = |ownedImplicitOpposites|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(13, mt._050);
			private final /*@NonNull*/ CardinalitySolutionStep _147 // assign V13 = |ownedPostconditions|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(13, mt._083);
			private final /*@NonNull*/ CardinalitySolutionStep _148 // assign V2 = (|isSerializable.'serializable'| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(2, mt._138);
			private final /*@NonNull*/ CardinalitySolutionStep _149 // assign V2 = (|ownedDetails| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(2, mt._155);
			private final /*@NonNull*/ CardinalitySolutionStep _150 // assign V2 = (|ownedParameters| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(2, mt._175);
			private final /*@NonNull*/ CardinalitySolutionStep _151 // assign V2 = (|ownedParameters| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(2, mt._177);
			private final /*@NonNull*/ CardinalitySolutionStep _152 // assign V2 = (|ownedParts| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(2, mt._178);
			private final /*@NonNull*/ CardinalitySolutionStep _153 // assign V2 = (|ownedSuperTypes| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(2, mt._198);
			private final /*@NonNull*/ CardinalitySolutionStep _154 // assign V2 = (|qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(2, mt._214);
			private final /*@NonNull*/ CardinalitySolutionStep _155 // assign V2 = 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(2, mt._000);
			private final /*@NonNull*/ CardinalitySolutionStep _156 // assign V2 = |default|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(2, mt._003);
			private final /*@NonNull*/ CardinalitySolutionStep _157 // assign V2 = |instanceClassName|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(2, mt._005);
			private final /*@NonNull*/ CardinalitySolutionStep _158 // assign V2 = |ownedAnnotations|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(2, mt._025);
			private final /*@NonNull*/ CardinalitySolutionStep _159 // assign V2 = |ownedCurlyBracketedClause|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(2, mt._037);
			private final /*@NonNull*/ CardinalitySolutionStep _160 // assign V2 = |ownedMessageSpecification|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(2, mt._061);
			private final /*@NonNull*/ CardinalitySolutionStep _161 // assign V2 = |ownedMultiplicity|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(2, mt._062);
			private final /*@NonNull*/ CardinalitySolutionStep _162 // assign V2 = |ownedPackages|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(2, mt._066);
			private final /*@NonNull*/ CardinalitySolutionStep _163 // assign V2 = |ownedSpecification|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(2, mt._091);
			private final /*@NonNull*/ CardinalitySolutionStep _164 // assign V2 = |ownedType|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(2, mt._098);
			private final /*@NonNull*/ CardinalitySolutionStep _165 // assign V2 = |qualifiers.'!ordered|!unique|ordered|unique'|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(2, mt._116);
			private final /*@NonNull*/ CardinalitySolutionStep _166 // assign V3 = (|isSerializable.'serializable'| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(3, mt._137);
			private final /*@NonNull*/ CardinalitySolutionStep _167 // assign V3 = (|ownedAnnotations| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(3, mt._146);
			private final /*@NonNull*/ CardinalitySolutionStep _168 // assign V3 = (|ownedParameters| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(3, mt._175);
			private final /*@NonNull*/ CardinalitySolutionStep _169 // assign V3 = (|ownedSuperTypes| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(3, mt._197);
			private final /*@NonNull*/ CardinalitySolutionStep _170 // assign V3 = (|qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(3, mt._213);
			private final /*@NonNull*/ CardinalitySolutionStep _171 // assign V3 = (|qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(3, mt._214);
			private final /*@NonNull*/ CardinalitySolutionStep _172 // assign V3 = 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(3, mt._000);
			private final /*@NonNull*/ CardinalitySolutionStep _173 // assign V3 = |default|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(3, mt._003);
			private final /*@NonNull*/ CardinalitySolutionStep _174 // assign V3 = |isPre.'@'|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(3, mt._011);
			private final /*@NonNull*/ CardinalitySolutionStep _175 // assign V3 = |isSerializable.'serializable'|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(3, mt._014);
			private final /*@NonNull*/ CardinalitySolutionStep _176 // assign V3 = |ownedAnnotations|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(3, mt._025);
			private final /*@NonNull*/ CardinalitySolutionStep _177 // assign V3 = |ownedMultiplicity|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(3, mt._062);
			private final /*@NonNull*/ CardinalitySolutionStep _178 // assign V3 = |ownedPackages|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(3, mt._066);
			private final /*@NonNull*/ CardinalitySolutionStep _179 // assign V3 = |ownedSpecification|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(3, mt._091);
			private final /*@NonNull*/ CardinalitySolutionStep _180 // assign V3 = |ownedType|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(3, mt._098);
			private final /*@NonNull*/ CardinalitySolutionStep _181 // assign V3 = |qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(3, mt._114);
			private final /*@NonNull*/ CardinalitySolutionStep _182 // assign V4 = (|ownedExceptions| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(4, mt._159);
			private final /*@NonNull*/ CardinalitySolutionStep _183 // assign V4 = (|qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(4, mt._213);
			private final /*@NonNull*/ CardinalitySolutionStep _184 // assign V4 = |instanceClassName|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(4, mt._005);
			private final /*@NonNull*/ CardinalitySolutionStep _185 // assign V4 = |isSerializable.'serializable'|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(4, mt._013);
			private final /*@NonNull*/ CardinalitySolutionStep _186 // assign V4 = |ownedAnnotations|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(4, mt._025);
			private final /*@NonNull*/ CardinalitySolutionStep _187 // assign V4 = |ownedClasses|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(4, mt._029);
			private final /*@NonNull*/ CardinalitySolutionStep _188 // assign V4 = |ownedContents|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(4, mt._035);
			private final /*@NonNull*/ CardinalitySolutionStep _189 // assign V4 = |ownedLiterals|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(4, mt._060);
			private final /*@NonNull*/ CardinalitySolutionStep _190 // assign V4 = |ownedType|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(4, mt._098);
			private final /*@NonNull*/ CardinalitySolutionStep _191 // assign V4 = |qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(4, mt._113);
			private final /*@NonNull*/ CardinalitySolutionStep _192 // assign V4 = |qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(4, mt._114);
			private final /*@NonNull*/ CardinalitySolutionStep _193 // assign V5 = (|isInterface.'interface'| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(5, mt._136);
			private final /*@NonNull*/ CardinalitySolutionStep _194 // assign V5 = (|ownedExceptions| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(5, mt._158);
			private final /*@NonNull*/ CardinalitySolutionStep _195 // assign V5 = (|ownedExceptions| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(5, mt._159);
			private final /*@NonNull*/ CardinalitySolutionStep _196 // assign V5 = |ownedAnnotations|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(5, mt._025);
			private final /*@NonNull*/ CardinalitySolutionStep _197 // assign V5 = |ownedConstraints|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(5, mt._034);
			private final /*@NonNull*/ CardinalitySolutionStep _198 // assign V5 = |ownedDefaultExpressions|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(5, mt._038);
			private final /*@NonNull*/ CardinalitySolutionStep _199 // assign V5 = |ownedLiterals|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(5, mt._060);
			private final /*@NonNull*/ CardinalitySolutionStep _200 // assign V5 = |ownedReferences|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(5, mt._086);
			private final /*@NonNull*/ CardinalitySolutionStep _201 // assign V5 = |qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(5, mt._113);
			private final /*@NonNull*/ CardinalitySolutionStep _202 // assign V6 = (|ownedDefaultExpressions| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(6, mt._154);
			private final /*@NonNull*/ CardinalitySolutionStep _203 // assign V6 = (|ownedExceptions| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(6, mt._158);
			private final /*@NonNull*/ CardinalitySolutionStep _204 // assign V6 = (|qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(6, mt._215);
			private final /*@NonNull*/ CardinalitySolutionStep _205 // assign V6 = (|referredKeys| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(6, mt._221);
			private final /*@NonNull*/ CardinalitySolutionStep _206 // assign V6 = |isInterface.'interface'|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(6, mt._009);
			private final /*@NonNull*/ CardinalitySolutionStep _207 // assign V6 = |ownedAnnotations|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(6, mt._025);
			private final /*@NonNull*/ CardinalitySolutionStep _208 // assign V6 = |ownedConstraints|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(6, mt._034);
			private final /*@NonNull*/ CardinalitySolutionStep _209 // assign V6 = |ownedDefaultExpressions|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(6, mt._038);
			private final /*@NonNull*/ CardinalitySolutionStep _210 // assign V7 = (|ownedDefaultExpressions| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(7, mt._154);
			private final /*@NonNull*/ CardinalitySolutionStep _211 // assign V7 = (|qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(7, mt._215);
			private final /*@NonNull*/ CardinalitySolutionStep _212 // assign V7 = (|referredKeys| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(7, mt._220);
			private final /*@NonNull*/ CardinalitySolutionStep _213 // assign V7 = (|referredKeys| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(7, mt._221);
			private final /*@NonNull*/ CardinalitySolutionStep _214 // assign V7 = 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(7, mt._000);
			private final /*@NonNull*/ CardinalitySolutionStep _215 // assign V7 = |ownedAnnotations|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(7, mt._025);
			private final /*@NonNull*/ CardinalitySolutionStep _216 // assign V7 = |qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(7, mt._115);
			private final /*@NonNull*/ CardinalitySolutionStep _217 // assign V8 = (|referredKeys| - 1)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(8, mt._220);
			private final /*@NonNull*/ CardinalitySolutionStep _218 // assign V8 = 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(8, mt._000);
			private final /*@NonNull*/ CardinalitySolutionStep _219 // assign V8 = |ownedAnnotations|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(8, mt._025);
			private final /*@NonNull*/ CardinalitySolutionStep _220 // assign V8 = |ownedDefaultExpressions|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(8, mt._038);
			private final /*@NonNull*/ CardinalitySolutionStep _221 // assign V8 = |ownedOperations|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(8, mt._065);
			private final /*@NonNull*/ CardinalitySolutionStep _222 // assign V8 = |qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(8, mt._115);
			private final /*@NonNull*/ CardinalitySolutionStep _223 // assign V9 = (|ownedDefaultExpressions| > 0)
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(9, mt._154);
			private final /*@NonNull*/ CardinalitySolutionStep _224 // assign V9 = 0
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(9, mt._000);
			private final /*@NonNull*/ CardinalitySolutionStep _225 // assign V9 = |ownedAnnotations|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(9, mt._025);
			private final /*@NonNull*/ CardinalitySolutionStep _226 // assign V9 = |ownedDefaultExpressions|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(9, mt._038);
			private final /*@NonNull*/ CardinalitySolutionStep _227 // assign V9 = |ownedPreconditions|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(9, mt._084);
			private final /*@NonNull*/ CardinalitySolutionStep _228 // assign V9 = |ownedProperties|
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.Assign(9, mt._085);
			private final /*@NonNull*/ CardinalitySolutionStep _229 // check-rule basecs::AnnotationCS.ownedContents : ModelElementCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ANNOTATION_CS__OWNED_CONTENTS, new String[]{"rv._053"});
			private final /*@NonNull*/ CardinalitySolutionStep _230 // check-rule basecs::AnnotationCS.ownedContents : ModelElementCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ANNOTATION_CS__OWNED_CONTENTS, new String[]{"rv._053"});
			private final /*@NonNull*/ CardinalitySolutionStep _231 // check-rule basecs::AnnotationCS.ownedContents : ModelElementCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ANNOTATION_CS__OWNED_CONTENTS, new String[]{"rv._053"});
			private final /*@NonNull*/ CardinalitySolutionStep _232 // check-rule basecs::AnnotationCS.ownedReferences : ModelElementRefCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ANNOTATION_CS__OWNED_REFERENCES, new String[]{"rv._054"});
			private final /*@NonNull*/ CardinalitySolutionStep _233 // check-rule basecs::AnnotationCS.ownedReferences : ModelElementRefCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ANNOTATION_CS__OWNED_REFERENCES, new String[]{"rv._054"});
			private final /*@NonNull*/ CardinalitySolutionStep _234 // check-rule basecs::AnnotationCS.ownedReferences : ModelElementRefCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ANNOTATION_CS__OWNED_REFERENCES, new String[]{"rv._054"});
			private final /*@NonNull*/ CardinalitySolutionStep _235 // check-rule basecs::AnnotationElementCS.ownedDetails : DetailCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS, new String[]{"rv._016"});
			private final /*@NonNull*/ CardinalitySolutionStep _236 // check-rule basecs::AnnotationElementCS.ownedDetails : DetailCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS, new String[]{"rv._016"});
			private final /*@NonNull*/ CardinalitySolutionStep _237 // check-rule basecs::AnnotationElementCS.ownedDetails : DetailCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS, new String[]{"rv._016"});
			private final /*@NonNull*/ CardinalitySolutionStep _238 // check-rule basecs::AnnotationElementCS.ownedDetails : DetailCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS, new String[]{"rv._016"});
			private final /*@NonNull*/ CardinalitySolutionStep _239 // check-rule basecs::AnnotationElementCS.ownedDetails : DetailCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS, new String[]{"rv._016"});
			private final /*@NonNull*/ CardinalitySolutionStep _240 // check-rule basecs::AnnotationElementCS.ownedDetails : DetailCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS, new String[]{"rv._016"});
			private final /*@NonNull*/ CardinalitySolutionStep _241 // check-rule basecs::AnnotationElementCS.ownedDetails : DetailCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS, new String[]{"rv._016"});
			private final /*@NonNull*/ CardinalitySolutionStep _242 // check-rule basecs::AnnotationElementCS.ownedDetails : DetailCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS, new String[]{"rv._016"});
			private final /*@NonNull*/ CardinalitySolutionStep _243 // check-rule basecs::AnnotationElementCS.ownedDetails : DetailCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS, new String[]{"rv._016"});
			private final /*@NonNull*/ CardinalitySolutionStep _244 // check-rule basecs::AnnotationElementCS.ownedDetails : DetailCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS, new String[]{"rv._016"});
			private final /*@NonNull*/ CardinalitySolutionStep _245 // check-rule basecs::ClassCS.ownedConstraints : InvariantConstraintCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS, new String[]{"rv._041"});
			private final /*@NonNull*/ CardinalitySolutionStep _246 // check-rule basecs::ClassCS.ownedConstraints : InvariantConstraintCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS, new String[]{"rv._041"});
			private final /*@NonNull*/ CardinalitySolutionStep _247 // check-rule basecs::ClassCS.ownedConstraints : InvariantConstraintCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS, new String[]{"rv._041"});
			private final /*@NonNull*/ CardinalitySolutionStep _248 // check-rule basecs::ClassCS.ownedConstraints : InvariantConstraintCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS, new String[]{"rv._041"});
			private final /*@NonNull*/ CardinalitySolutionStep _249 // check-rule basecs::ClassCS.ownedConstraints : InvariantConstraintCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS, new String[]{"rv._041"});
			private final /*@NonNull*/ CardinalitySolutionStep _250 // check-rule basecs::ClassCS.ownedConstraints : InvariantConstraintCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS, new String[]{"rv._041"});
			private final /*@NonNull*/ CardinalitySolutionStep _251 // check-rule basecs::ClassCS.ownedConstraints : InvariantConstraintCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS, new String[]{"rv._041"});
			private final /*@NonNull*/ CardinalitySolutionStep _252 // check-rule basecs::ClassCS.ownedConstraints : InvariantConstraintCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS, new String[]{"rv._041"});
			private final /*@NonNull*/ CardinalitySolutionStep _253 // check-rule basecs::ClassCS.ownedConstraints : InvariantConstraintCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS, new String[]{"rv._041"});
			private final /*@NonNull*/ CardinalitySolutionStep _254 // check-rule basecs::ClassCS.ownedConstraints : InvariantConstraintCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS, new String[]{"rv._041"});
			private final /*@NonNull*/ CardinalitySolutionStep _255 // check-rule basecs::ClassCS.ownedConstraints : InvariantConstraintCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS, new String[]{"rv._041"});
			private final /*@NonNull*/ CardinalitySolutionStep _256 // check-rule basecs::ClassCS.ownedConstraints : InvariantConstraintCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS, new String[]{"rv._041"});
			private final /*@NonNull*/ CardinalitySolutionStep _257 // check-rule basecs::ClassCS.ownedConstraints : InvariantConstraintCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS, new String[]{"rv._041"});
			private final /*@NonNull*/ CardinalitySolutionStep _258 // check-rule basecs::ClassCS.ownedConstraints : InvariantConstraintCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS, new String[]{"rv._041"});
			private final /*@NonNull*/ CardinalitySolutionStep _259 // check-rule basecs::ConstraintCS.ownedMessageSpecification : SpecificationCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION, new String[]{"rv._092"});
			private final /*@NonNull*/ CardinalitySolutionStep _260 // check-rule basecs::ConstraintCS.ownedMessageSpecification : SpecificationCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION, new String[]{"rv._092"});
			private final /*@NonNull*/ CardinalitySolutionStep _261 // check-rule basecs::ConstraintCS.ownedMessageSpecification : SpecificationCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION, new String[]{"rv._092"});
			private final /*@NonNull*/ CardinalitySolutionStep _262 // check-rule basecs::ConstraintCS.ownedMessageSpecification : SpecificationCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION, new String[]{"rv._092"});
			private final /*@NonNull*/ CardinalitySolutionStep _263 // check-rule basecs::ConstraintCS.ownedSpecification : SpecificationCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION, new String[]{"rv._092"});
			private final /*@NonNull*/ CardinalitySolutionStep _264 // check-rule basecs::ConstraintCS.ownedSpecification : SpecificationCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION, new String[]{"rv._092"});
			private final /*@NonNull*/ CardinalitySolutionStep _265 // check-rule basecs::ConstraintCS.ownedSpecification : SpecificationCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION, new String[]{"rv._092"});
			private final /*@NonNull*/ CardinalitySolutionStep _266 // check-rule basecs::EnumerationCS.ownedLiterals : EnumerationLiteralCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ENUMERATION_CS__OWNED_LITERALS, new String[]{"rv._022"});
			private final /*@NonNull*/ CardinalitySolutionStep _267 // check-rule basecs::EnumerationCS.ownedLiterals : EnumerationLiteralCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ENUMERATION_CS__OWNED_LITERALS, new String[]{"rv._022"});
			private final /*@NonNull*/ CardinalitySolutionStep _268 // check-rule basecs::EnumerationCS.ownedLiterals : EnumerationLiteralCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ENUMERATION_CS__OWNED_LITERALS, new String[]{"rv._022"});
			private final /*@NonNull*/ CardinalitySolutionStep _269 // check-rule basecs::EnumerationCS.ownedLiterals : EnumerationLiteralCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ENUMERATION_CS__OWNED_LITERALS, new String[]{"rv._022"});
			private final /*@NonNull*/ CardinalitySolutionStep _270 // check-rule basecs::EnumerationCS.ownedLiterals : EnumerationLiteralCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ENUMERATION_CS__OWNED_LITERALS, new String[]{"rv._022"});
			private final /*@NonNull*/ CardinalitySolutionStep _271 // check-rule basecs::EnumerationCS.ownedLiterals : EnumerationLiteralCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ENUMERATION_CS__OWNED_LITERALS, new String[]{"rv._022"});
			private final /*@NonNull*/ CardinalitySolutionStep _272 // check-rule basecs::ImportCS.ownedPathName : URIPathNameCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.IMPORT_CS__OWNED_PATH_NAME, new String[]{"rv._123"});
			private final /*@NonNull*/ CardinalitySolutionStep _273 // check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationElementCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, new String[]{"rv._002"});
			private final /*@NonNull*/ CardinalitySolutionStep _274 // check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationElementCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, new String[]{"rv._002"});
			private final /*@NonNull*/ CardinalitySolutionStep _275 // check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationElementCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, new String[]{"rv._002"});
			private final /*@NonNull*/ CardinalitySolutionStep _276 // check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationElementCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, new String[]{"rv._002"});
			private final /*@NonNull*/ CardinalitySolutionStep _277 // check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationElementCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, new String[]{"rv._002"});
			private final /*@NonNull*/ CardinalitySolutionStep _278 // check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationElementCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, new String[]{"rv._002"});
			private final /*@NonNull*/ CardinalitySolutionStep _279 // check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationElementCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, new String[]{"rv._002"});
			private final /*@NonNull*/ CardinalitySolutionStep _280 // check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationElementCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, new String[]{"rv._002"});
			private final /*@NonNull*/ CardinalitySolutionStep _281 // check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationElementCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, new String[]{"rv._002"});
			private final /*@NonNull*/ CardinalitySolutionStep _282 // check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationElementCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, new String[]{"rv._002"});
			private final /*@NonNull*/ CardinalitySolutionStep _283 // check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationElementCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, new String[]{"rv._002"});
			private final /*@NonNull*/ CardinalitySolutionStep _284 // check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationElementCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, new String[]{"rv._002"});
			private final /*@NonNull*/ CardinalitySolutionStep _285 // check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationElementCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, new String[]{"rv._002"});
			private final /*@NonNull*/ CardinalitySolutionStep _286 // check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationElementCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, new String[]{"rv._002"});
			private final /*@NonNull*/ CardinalitySolutionStep _287 // check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationElementCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, new String[]{"rv._002"});
			private final /*@NonNull*/ CardinalitySolutionStep _288 // check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationElementCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, new String[]{"rv._002"});
			private final /*@NonNull*/ CardinalitySolutionStep _289 // check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationElementCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, new String[]{"rv._002"});
			private final /*@NonNull*/ CardinalitySolutionStep _290 // check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationElementCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, new String[]{"rv._002"});
			private final /*@NonNull*/ CardinalitySolutionStep _291 // check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationElementCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, new String[]{"rv._002"});
			private final /*@NonNull*/ CardinalitySolutionStep _292 // check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationElementCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, new String[]{"rv._002"});
			private final /*@NonNull*/ CardinalitySolutionStep _293 // check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationElementCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, new String[]{"rv._002"});
			private final /*@NonNull*/ CardinalitySolutionStep _294 // check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationElementCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, new String[]{"rv._002"});
			private final /*@NonNull*/ CardinalitySolutionStep _295 // check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationElementCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, new String[]{"rv._002"});
			private final /*@NonNull*/ CardinalitySolutionStep _296 // check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationElementCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, new String[]{"rv._002"});
			private final /*@NonNull*/ CardinalitySolutionStep _297 // check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationElementCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, new String[]{"rv._002"});
			private final /*@NonNull*/ CardinalitySolutionStep _298 // check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationElementCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, new String[]{"rv._002"});
			private final /*@NonNull*/ CardinalitySolutionStep _299 // check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationElementCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, new String[]{"rv._002"});
			private final /*@NonNull*/ CardinalitySolutionStep _300 // check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationElementCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, new String[]{"rv._002"});
			private final /*@NonNull*/ CardinalitySolutionStep _301 // check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationElementCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, new String[]{"rv._002"});
			private final /*@NonNull*/ CardinalitySolutionStep _302 // check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationElementCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, new String[]{"rv._002"});
			private final /*@NonNull*/ CardinalitySolutionStep _303 // check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationElementCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, new String[]{"rv._002"});
			private final /*@NonNull*/ CardinalitySolutionStep _304 // check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationElementCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, new String[]{"rv._002"});
			private final /*@NonNull*/ CardinalitySolutionStep _305 // check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationElementCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, new String[]{"rv._002"});
			private final /*@NonNull*/ CardinalitySolutionStep _306 // check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationElementCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, new String[]{"rv._002"});
			private final /*@NonNull*/ CardinalitySolutionStep _307 // check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationElementCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, new String[]{"rv._002"});
			private final /*@NonNull*/ CardinalitySolutionStep _308 // check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationElementCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, new String[]{"rv._002"});
			private final /*@NonNull*/ CardinalitySolutionStep _309 // check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationElementCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS, new String[]{"rv._002"});
			private final /*@NonNull*/ CardinalitySolutionStep _310 // check-rule basecs::ModelElementRefCS.ownedPathName : PathNameCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MODEL_ELEMENT_REF_CS__OWNED_PATH_NAME, new String[]{"rv._073"});
			private final /*@NonNull*/ CardinalitySolutionStep _311 // check-rule basecs::OperationCS.ownedBodyExpressions : SpecificationCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS__OWNED_BODY_EXPRESSIONS, new String[]{"rv._092"});
			private final /*@NonNull*/ CardinalitySolutionStep _312 // check-rule basecs::OperationCS.ownedBodyExpressions : SpecificationCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS__OWNED_BODY_EXPRESSIONS, new String[]{"rv._092"});
			private final /*@NonNull*/ CardinalitySolutionStep _313 // check-rule basecs::OperationCS.ownedBodyExpressions : SpecificationCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS__OWNED_BODY_EXPRESSIONS, new String[]{"rv._092"});
			private final /*@NonNull*/ CardinalitySolutionStep _314 // check-rule basecs::OperationCS.ownedBodyExpressions : SpecificationCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS__OWNED_BODY_EXPRESSIONS, new String[]{"rv._092"});
			private final /*@NonNull*/ CardinalitySolutionStep _315 // check-rule basecs::OperationCS.ownedBodyExpressions : SpecificationCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS__OWNED_BODY_EXPRESSIONS, new String[]{"rv._092"});
			private final /*@NonNull*/ CardinalitySolutionStep _316 // check-rule basecs::OperationCS.ownedBodyExpressions : SpecificationCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS__OWNED_BODY_EXPRESSIONS, new String[]{"rv._092"});
			private final /*@NonNull*/ CardinalitySolutionStep _317 // check-rule basecs::OperationCS.ownedExceptions : TypedRefCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS, new String[]{"rv._117"});
			private final /*@NonNull*/ CardinalitySolutionStep _318 // check-rule basecs::OperationCS.ownedExceptions : TypedRefCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS, new String[]{"rv._117"});
			private final /*@NonNull*/ CardinalitySolutionStep _319 // check-rule basecs::OperationCS.ownedExceptions : TypedRefCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS, new String[]{"rv._117"});
			private final /*@NonNull*/ CardinalitySolutionStep _320 // check-rule basecs::OperationCS.ownedExceptions : TypedRefCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS, new String[]{"rv._117"});
			private final /*@NonNull*/ CardinalitySolutionStep _321 // check-rule basecs::OperationCS.ownedExceptions : TypedRefCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS, new String[]{"rv._117"});
			private final /*@NonNull*/ CardinalitySolutionStep _322 // check-rule basecs::OperationCS.ownedExceptions : TypedRefCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS, new String[]{"rv._117"});
			private final /*@NonNull*/ CardinalitySolutionStep _323 // check-rule basecs::OperationCS.ownedExceptions : TypedRefCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS, new String[]{"rv._117"});
			private final /*@NonNull*/ CardinalitySolutionStep _324 // check-rule basecs::OperationCS.ownedExceptions : TypedRefCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS, new String[]{"rv._117"});
			private final /*@NonNull*/ CardinalitySolutionStep _325 // check-rule basecs::OperationCS.ownedExceptions : TypedRefCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS, new String[]{"rv._117"});
			private final /*@NonNull*/ CardinalitySolutionStep _326 // check-rule basecs::OperationCS.ownedExceptions : TypedRefCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS, new String[]{"rv._117"});
			private final /*@NonNull*/ CardinalitySolutionStep _327 // check-rule basecs::OperationCS.ownedExceptions : TypedRefCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS, new String[]{"rv._117"});
			private final /*@NonNull*/ CardinalitySolutionStep _328 // check-rule basecs::OperationCS.ownedExceptions : TypedRefCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS, new String[]{"rv._117"});
			private final /*@NonNull*/ CardinalitySolutionStep _329 // check-rule basecs::OperationCS.ownedParameters : ParameterCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS, new String[]{"rv._072"});
			private final /*@NonNull*/ CardinalitySolutionStep _330 // check-rule basecs::OperationCS.ownedParameters : ParameterCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS, new String[]{"rv._072"});
			private final /*@NonNull*/ CardinalitySolutionStep _331 // check-rule basecs::OperationCS.ownedParameters : ParameterCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS, new String[]{"rv._072"});
			private final /*@NonNull*/ CardinalitySolutionStep _332 // check-rule basecs::OperationCS.ownedParameters : ParameterCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS, new String[]{"rv._072"});
			private final /*@NonNull*/ CardinalitySolutionStep _333 // check-rule basecs::OperationCS.ownedParameters : ParameterCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS, new String[]{"rv._072"});
			private final /*@NonNull*/ CardinalitySolutionStep _334 // check-rule basecs::OperationCS.ownedParameters : ParameterCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS, new String[]{"rv._072"});
			private final /*@NonNull*/ CardinalitySolutionStep _335 // check-rule basecs::OperationCS.ownedParameters : ParameterCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS, new String[]{"rv._072"});
			private final /*@NonNull*/ CardinalitySolutionStep _336 // check-rule basecs::OperationCS.ownedParameters : ParameterCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS, new String[]{"rv._072"});
			private final /*@NonNull*/ CardinalitySolutionStep _337 // check-rule basecs::OperationCS.ownedParameters : ParameterCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS, new String[]{"rv._072"});
			private final /*@NonNull*/ CardinalitySolutionStep _338 // check-rule basecs::OperationCS.ownedParameters : ParameterCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS, new String[]{"rv._072"});
			private final /*@NonNull*/ CardinalitySolutionStep _339 // check-rule basecs::OperationCS.ownedParameters : ParameterCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS, new String[]{"rv._072"});
			private final /*@NonNull*/ CardinalitySolutionStep _340 // check-rule basecs::OperationCS.ownedParameters : ParameterCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS, new String[]{"rv._072"});
			private final /*@NonNull*/ CardinalitySolutionStep _341 // check-rule basecs::OperationCS.ownedPostconditions : PostconditionConstraintCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS, new String[]{"rv._075"});
			private final /*@NonNull*/ CardinalitySolutionStep _342 // check-rule basecs::OperationCS.ownedPostconditions : PostconditionConstraintCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS, new String[]{"rv._075"});
			private final /*@NonNull*/ CardinalitySolutionStep _343 // check-rule basecs::OperationCS.ownedPostconditions : PostconditionConstraintCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS, new String[]{"rv._075"});
			private final /*@NonNull*/ CardinalitySolutionStep _344 // check-rule basecs::OperationCS.ownedPostconditions : PostconditionConstraintCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS, new String[]{"rv._075"});
			private final /*@NonNull*/ CardinalitySolutionStep _345 // check-rule basecs::OperationCS.ownedPostconditions : PostconditionConstraintCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS, new String[]{"rv._075"});
			private final /*@NonNull*/ CardinalitySolutionStep _346 // check-rule basecs::OperationCS.ownedPostconditions : PostconditionConstraintCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS, new String[]{"rv._075"});
			private final /*@NonNull*/ CardinalitySolutionStep _347 // check-rule basecs::OperationCS.ownedPreconditions : PreconditionConstraintCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS, new String[]{"rv._076"});
			private final /*@NonNull*/ CardinalitySolutionStep _348 // check-rule basecs::OperationCS.ownedPreconditions : PreconditionConstraintCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS, new String[]{"rv._076"});
			private final /*@NonNull*/ CardinalitySolutionStep _349 // check-rule basecs::OperationCS.ownedPreconditions : PreconditionConstraintCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS, new String[]{"rv._076"});
			private final /*@NonNull*/ CardinalitySolutionStep _350 // check-rule basecs::OperationCS.ownedPreconditions : PreconditionConstraintCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS, new String[]{"rv._076"});
			private final /*@NonNull*/ CardinalitySolutionStep _351 // check-rule basecs::OperationCS.ownedPreconditions : PreconditionConstraintCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS, new String[]{"rv._076"});
			private final /*@NonNull*/ CardinalitySolutionStep _352 // check-rule basecs::OperationCS.ownedPreconditions : PreconditionConstraintCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS, new String[]{"rv._076"});
			private final /*@NonNull*/ CardinalitySolutionStep _353 // check-rule basecs::PackageCS.ownedClasses : ClassCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PACKAGE_CS__OWNED_CLASSES, new String[]{"rv._006"});
			private final /*@NonNull*/ CardinalitySolutionStep _354 // check-rule basecs::PackageCS.ownedClasses : ClassCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PACKAGE_CS__OWNED_CLASSES, new String[]{"rv._006"});
			private final /*@NonNull*/ CardinalitySolutionStep _355 // check-rule basecs::PackageOwnerCS.ownedPackages : PackageCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES, new String[]{"rv._071"});
			private final /*@NonNull*/ CardinalitySolutionStep _356 // check-rule basecs::PackageOwnerCS.ownedPackages : PackageCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES, new String[]{"rv._071"});
			private final /*@NonNull*/ CardinalitySolutionStep _357 // check-rule basecs::PackageOwnerCS.ownedPackages : PackageCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES, new String[]{"rv._071"});
			private final /*@NonNull*/ CardinalitySolutionStep _358 // check-rule basecs::PathNameCS.ownedPathElements : FirstPathElementCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, new String[]{"rv._031"});
			private final /*@NonNull*/ CardinalitySolutionStep _359 // check-rule basecs::PathNameCS.ownedPathElements : NextPathElementCS|FirstPathElementCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, new String[]{"rv._067", "rv._031"});
			private final /*@NonNull*/ CardinalitySolutionStep _360 // check-rule basecs::PathNameCS.ownedPathElements : URIFirstPathElementCS|NextPathElementCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS, new String[]{"rv._122", "rv._067"});
			private final /*@NonNull*/ CardinalitySolutionStep _361 // check-rule basecs::ReferenceCS.ownedImplicitOpposites : ImplicitOppositeCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.REFERENCE_CS__OWNED_IMPLICIT_OPPOSITES, new String[]{"rv._037"});
			private final /*@NonNull*/ CardinalitySolutionStep _362 // check-rule basecs::ReferenceCS.ownedImplicitOpposites : ImplicitOppositeCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.REFERENCE_CS__OWNED_IMPLICIT_OPPOSITES, new String[]{"rv._037"});
			private final /*@NonNull*/ CardinalitySolutionStep _363 // check-rule basecs::ReferenceCS.ownedImplicitOpposites : ImplicitOppositeCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.REFERENCE_CS__OWNED_IMPLICIT_OPPOSITES, new String[]{"rv._037"});
			private final /*@NonNull*/ CardinalitySolutionStep _364 // check-rule basecs::ReferenceCS.ownedImplicitOpposites : ImplicitOppositeCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.REFERENCE_CS__OWNED_IMPLICIT_OPPOSITES, new String[]{"rv._037"});
			private final /*@NonNull*/ CardinalitySolutionStep _365 // check-rule basecs::ReferenceCS.ownedImplicitOpposites : ImplicitOppositeCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.REFERENCE_CS__OWNED_IMPLICIT_OPPOSITES, new String[]{"rv._037"});
			private final /*@NonNull*/ CardinalitySolutionStep _366 // check-rule basecs::ReferenceCS.ownedImplicitOpposites : ImplicitOppositeCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.REFERENCE_CS__OWNED_IMPLICIT_OPPOSITES, new String[]{"rv._037"});
			private final /*@NonNull*/ CardinalitySolutionStep _367 // check-rule basecs::RootCS.ownedImports : ImportCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ROOT_CS__OWNED_IMPORTS, new String[]{"rv._038"});
			private final /*@NonNull*/ CardinalitySolutionStep _368 // check-rule basecs::StructuralFeatureCS.ownedDefaultExpressions : SpecificationCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS, new String[]{"rv._092"});
			private final /*@NonNull*/ CardinalitySolutionStep _369 // check-rule basecs::StructuralFeatureCS.ownedDefaultExpressions : SpecificationCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS, new String[]{"rv._092"});
			private final /*@NonNull*/ CardinalitySolutionStep _370 // check-rule basecs::StructuralFeatureCS.ownedDefaultExpressions : SpecificationCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS, new String[]{"rv._092"});
			private final /*@NonNull*/ CardinalitySolutionStep _371 // check-rule basecs::StructuralFeatureCS.ownedDefaultExpressions : SpecificationCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS, new String[]{"rv._092"});
			private final /*@NonNull*/ CardinalitySolutionStep _372 // check-rule basecs::StructuralFeatureCS.ownedDefaultExpressions : SpecificationCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS, new String[]{"rv._092"});
			private final /*@NonNull*/ CardinalitySolutionStep _373 // check-rule basecs::StructuralFeatureCS.ownedDefaultExpressions : SpecificationCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS, new String[]{"rv._092"});
			private final /*@NonNull*/ CardinalitySolutionStep _374 // check-rule basecs::StructuralFeatureCS.ownedDefaultExpressions : SpecificationCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS, new String[]{"rv._092"});
			private final /*@NonNull*/ CardinalitySolutionStep _375 // check-rule basecs::StructuralFeatureCS.ownedDefaultExpressions : SpecificationCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS, new String[]{"rv._092"});
			private final /*@NonNull*/ CardinalitySolutionStep _376 // check-rule basecs::StructuralFeatureCS.ownedDefaultExpressions : SpecificationCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS, new String[]{"rv._092"});
			private final /*@NonNull*/ CardinalitySolutionStep _377 // check-rule basecs::StructuredClassCS.ownedOperations : OperationCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_OPERATIONS, new String[]{"rv._070"});
			private final /*@NonNull*/ CardinalitySolutionStep _378 // check-rule basecs::StructuredClassCS.ownedOperations : OperationCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_OPERATIONS, new String[]{"rv._070"});
			private final /*@NonNull*/ CardinalitySolutionStep _379 // check-rule basecs::StructuredClassCS.ownedProperties : StructuralFeatureCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_PROPERTIES, new String[]{"rv._096"});
			private final /*@NonNull*/ CardinalitySolutionStep _380 // check-rule basecs::StructuredClassCS.ownedProperties : StructuralFeatureCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_PROPERTIES, new String[]{"rv._096"});
			private final /*@NonNull*/ CardinalitySolutionStep _381 // check-rule basecs::StructuredClassCS.ownedSuperTypes : TypedRefCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_SUPER_TYPES, new String[]{"rv._117"});
			private final /*@NonNull*/ CardinalitySolutionStep _382 // check-rule basecs::StructuredClassCS.ownedSuperTypes : TypedRefCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_SUPER_TYPES, new String[]{"rv._117"});
			private final /*@NonNull*/ CardinalitySolutionStep _383 // check-rule basecs::StructuredClassCS.ownedSuperTypes : TypedRefCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_SUPER_TYPES, new String[]{"rv._117"});
			private final /*@NonNull*/ CardinalitySolutionStep _384 // check-rule basecs::StructuredClassCS.ownedSuperTypes : TypedRefCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_SUPER_TYPES, new String[]{"rv._117"});
			private final /*@NonNull*/ CardinalitySolutionStep _385 // check-rule basecs::TemplateBindingCS.ownedMultiplicity : MultiplicityCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY, new String[]{"rv._056"});
			private final /*@NonNull*/ CardinalitySolutionStep _386 // check-rule basecs::TemplateBindingCS.ownedSubstitutions : TemplateParameterSubstitutionCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS, new String[]{"rv._100"});
			private final /*@NonNull*/ CardinalitySolutionStep _387 // check-rule basecs::TemplateParameterSubstitutionCS.ownedActualParameter : TypeRefCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER, new String[]{"rv._115"});
			private final /*@NonNull*/ CardinalitySolutionStep _388 // check-rule basecs::TemplateSignatureCS.ownedParameters : TypeParameterCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS, new String[]{"rv._114"});
			private final /*@NonNull*/ CardinalitySolutionStep _389 // check-rule basecs::TemplateSignatureCS.ownedParameters : TypeParameterCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS, new String[]{"rv._114"});
			private final /*@NonNull*/ CardinalitySolutionStep _390 // check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, new String[]{"rv._101"});
			private final /*@NonNull*/ CardinalitySolutionStep _391 // check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, new String[]{"rv._101"});
			private final /*@NonNull*/ CardinalitySolutionStep _392 // check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, new String[]{"rv._101"});
			private final /*@NonNull*/ CardinalitySolutionStep _393 // check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, new String[]{"rv._101"});
			private final /*@NonNull*/ CardinalitySolutionStep _394 // check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, new String[]{"rv._101"});
			private final /*@NonNull*/ CardinalitySolutionStep _395 // check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, new String[]{"rv._101"});
			private final /*@NonNull*/ CardinalitySolutionStep _396 // check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, new String[]{"rv._101"});
			private final /*@NonNull*/ CardinalitySolutionStep _397 // check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, new String[]{"rv._101"});
			private final /*@NonNull*/ CardinalitySolutionStep _398 // check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, new String[]{"rv._101"});
			private final /*@NonNull*/ CardinalitySolutionStep _399 // check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, new String[]{"rv._101"});
			private final /*@NonNull*/ CardinalitySolutionStep _400 // check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, new String[]{"rv._101"});
			private final /*@NonNull*/ CardinalitySolutionStep _401 // check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, new String[]{"rv._101"});
			private final /*@NonNull*/ CardinalitySolutionStep _402 // check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, new String[]{"rv._101"});
			private final /*@NonNull*/ CardinalitySolutionStep _403 // check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, new String[]{"rv._101"});
			private final /*@NonNull*/ CardinalitySolutionStep _404 // check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, new String[]{"rv._101"});
			private final /*@NonNull*/ CardinalitySolutionStep _405 // check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, new String[]{"rv._101"});
			private final /*@NonNull*/ CardinalitySolutionStep _406 // check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, new String[]{"rv._101"});
			private final /*@NonNull*/ CardinalitySolutionStep _407 // check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, new String[]{"rv._101"});
			private final /*@NonNull*/ CardinalitySolutionStep _408 // check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, new String[]{"rv._101"});
			private final /*@NonNull*/ CardinalitySolutionStep _409 // check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, new String[]{"rv._101"});
			private final /*@NonNull*/ CardinalitySolutionStep _410 // check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, new String[]{"rv._101"});
			private final /*@NonNull*/ CardinalitySolutionStep _411 // check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, new String[]{"rv._101"});
			private final /*@NonNull*/ CardinalitySolutionStep _412 // check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, new String[]{"rv._101"});
			private final /*@NonNull*/ CardinalitySolutionStep _413 // check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, new String[]{"rv._101"});
			private final /*@NonNull*/ CardinalitySolutionStep _414 // check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, new String[]{"rv._101"});
			private final /*@NonNull*/ CardinalitySolutionStep _415 // check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, new String[]{"rv._101"});
			private final /*@NonNull*/ CardinalitySolutionStep _416 // check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, new String[]{"rv._101"});
			private final /*@NonNull*/ CardinalitySolutionStep _417 // check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, new String[]{"rv._101"});
			private final /*@NonNull*/ CardinalitySolutionStep _418 // check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, new String[]{"rv._101"});
			private final /*@NonNull*/ CardinalitySolutionStep _419 // check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, new String[]{"rv._101"});
			private final /*@NonNull*/ CardinalitySolutionStep _420 // check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, new String[]{"rv._101"});
			private final /*@NonNull*/ CardinalitySolutionStep _421 // check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, new String[]{"rv._101"});
			private final /*@NonNull*/ CardinalitySolutionStep _422 // check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, new String[]{"rv._101"});
			private final /*@NonNull*/ CardinalitySolutionStep _423 // check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, new String[]{"rv._101"});
			private final /*@NonNull*/ CardinalitySolutionStep _424 // check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, new String[]{"rv._101"});
			private final /*@NonNull*/ CardinalitySolutionStep _425 // check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, new String[]{"rv._101"});
			private final /*@NonNull*/ CardinalitySolutionStep _426 // check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, new String[]{"rv._101"});
			private final /*@NonNull*/ CardinalitySolutionStep _427 // check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, new String[]{"rv._101"});
			private final /*@NonNull*/ CardinalitySolutionStep _428 // check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, new String[]{"rv._101"});
			private final /*@NonNull*/ CardinalitySolutionStep _429 // check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE, new String[]{"rv._101"});
			private final /*@NonNull*/ CardinalitySolutionStep _430 // check-rule basecs::TupleTypeCS.ownedParts : TuplePartCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS, new String[]{"rv._105"});
			private final /*@NonNull*/ CardinalitySolutionStep _431 // check-rule basecs::TupleTypeCS.ownedParts : TuplePartCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS, new String[]{"rv._105"});
			private final /*@NonNull*/ CardinalitySolutionStep _432 // check-rule basecs::TupleTypeCS.ownedParts : TuplePartCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS, new String[]{"rv._105"});
			private final /*@NonNull*/ CardinalitySolutionStep _433 // check-rule basecs::TupleTypeCS.ownedParts : TuplePartCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS, new String[]{"rv._105"});
			private final /*@NonNull*/ CardinalitySolutionStep _434 // check-rule basecs::TupleTypeCS.ownedParts : TuplePartCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS, new String[]{"rv._105"});
			private final /*@NonNull*/ CardinalitySolutionStep _435 // check-rule basecs::TypeParameterCS.ownedExtends : TypedRefCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS, new String[]{"rv._117"});
			private final /*@NonNull*/ CardinalitySolutionStep _436 // check-rule basecs::TypedElementCS.ownedType : TypeExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, new String[]{"rv._107"});
			private final /*@NonNull*/ CardinalitySolutionStep _437 // check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, new String[]{"rv._116"});
			private final /*@NonNull*/ CardinalitySolutionStep _438 // check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, new String[]{"rv._116"});
			private final /*@NonNull*/ CardinalitySolutionStep _439 // check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, new String[]{"rv._116"});
			private final /*@NonNull*/ CardinalitySolutionStep _440 // check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, new String[]{"rv._116"});
			private final /*@NonNull*/ CardinalitySolutionStep _441 // check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, new String[]{"rv._116"});
			private final /*@NonNull*/ CardinalitySolutionStep _442 // check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, new String[]{"rv._116"});
			private final /*@NonNull*/ CardinalitySolutionStep _443 // check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, new String[]{"rv._116"});
			private final /*@NonNull*/ CardinalitySolutionStep _444 // check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, new String[]{"rv._116"});
			private final /*@NonNull*/ CardinalitySolutionStep _445 // check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, new String[]{"rv._116"});
			private final /*@NonNull*/ CardinalitySolutionStep _446 // check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, new String[]{"rv._116"});
			private final /*@NonNull*/ CardinalitySolutionStep _447 // check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, new String[]{"rv._116"});
			private final /*@NonNull*/ CardinalitySolutionStep _448 // check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, new String[]{"rv._116"});
			private final /*@NonNull*/ CardinalitySolutionStep _449 // check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, new String[]{"rv._116"});
			private final /*@NonNull*/ CardinalitySolutionStep _450 // check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, new String[]{"rv._116"});
			private final /*@NonNull*/ CardinalitySolutionStep _451 // check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, new String[]{"rv._116"});
			private final /*@NonNull*/ CardinalitySolutionStep _452 // check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, new String[]{"rv._116"});
			private final /*@NonNull*/ CardinalitySolutionStep _453 // check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, new String[]{"rv._116"});
			private final /*@NonNull*/ CardinalitySolutionStep _454 // check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, new String[]{"rv._116"});
			private final /*@NonNull*/ CardinalitySolutionStep _455 // check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, new String[]{"rv._116"});
			private final /*@NonNull*/ CardinalitySolutionStep _456 // check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, new String[]{"rv._116"});
			private final /*@NonNull*/ CardinalitySolutionStep _457 // check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, new String[]{"rv._116"});
			private final /*@NonNull*/ CardinalitySolutionStep _458 // check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, new String[]{"rv._116"});
			private final /*@NonNull*/ CardinalitySolutionStep _459 // check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, new String[]{"rv._116"});
			private final /*@NonNull*/ CardinalitySolutionStep _460 // check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, new String[]{"rv._116"});
			private final /*@NonNull*/ CardinalitySolutionStep _461 // check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, new String[]{"rv._116"});
			private final /*@NonNull*/ CardinalitySolutionStep _462 // check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, new String[]{"rv._116"});
			private final /*@NonNull*/ CardinalitySolutionStep _463 // check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, new String[]{"rv._116"});
			private final /*@NonNull*/ CardinalitySolutionStep _464 // check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, new String[]{"rv._116"});
			private final /*@NonNull*/ CardinalitySolutionStep _465 // check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, new String[]{"rv._116"});
			private final /*@NonNull*/ CardinalitySolutionStep _466 // check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, new String[]{"rv._116"});
			private final /*@NonNull*/ CardinalitySolutionStep _467 // check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, new String[]{"rv._116"});
			private final /*@NonNull*/ CardinalitySolutionStep _468 // check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE, new String[]{"rv._116"});
			private final /*@NonNull*/ CardinalitySolutionStep _469 // check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, new String[]{"rv._056"});
			private final /*@NonNull*/ CardinalitySolutionStep _470 // check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, new String[]{"rv._056"});
			private final /*@NonNull*/ CardinalitySolutionStep _471 // check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, new String[]{"rv._056"});
			private final /*@NonNull*/ CardinalitySolutionStep _472 // check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, new String[]{"rv._056"});
			private final /*@NonNull*/ CardinalitySolutionStep _473 // check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, new String[]{"rv._056"});
			private final /*@NonNull*/ CardinalitySolutionStep _474 // check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, new String[]{"rv._056"});
			private final /*@NonNull*/ CardinalitySolutionStep _475 // check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, new String[]{"rv._056"});
			private final /*@NonNull*/ CardinalitySolutionStep _476 // check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, new String[]{"rv._056"});
			private final /*@NonNull*/ CardinalitySolutionStep _477 // check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, new String[]{"rv._056"});
			private final /*@NonNull*/ CardinalitySolutionStep _478 // check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, new String[]{"rv._056"});
			private final /*@NonNull*/ CardinalitySolutionStep _479 // check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, new String[]{"rv._056"});
			private final /*@NonNull*/ CardinalitySolutionStep _480 // check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, new String[]{"rv._056"});
			private final /*@NonNull*/ CardinalitySolutionStep _481 // check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, new String[]{"rv._056"});
			private final /*@NonNull*/ CardinalitySolutionStep _482 // check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, new String[]{"rv._056"});
			private final /*@NonNull*/ CardinalitySolutionStep _483 // check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, new String[]{"rv._056"});
			private final /*@NonNull*/ CardinalitySolutionStep _484 // check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, new String[]{"rv._056"});
			private final /*@NonNull*/ CardinalitySolutionStep _485 // check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY, new String[]{"rv._056"});
			private final /*@NonNull*/ CardinalitySolutionStep _486 // check-rule basecs::TypedTypeRefCS.ownedBinding : TemplateBindingCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING, new String[]{"rv._099"});
			private final /*@NonNull*/ CardinalitySolutionStep _487 // check-rule basecs::TypedTypeRefCS.ownedBinding : TemplateBindingCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING, new String[]{"rv._099"});
			private final /*@NonNull*/ CardinalitySolutionStep _488 // check-rule basecs::TypedTypeRefCS.ownedBinding : TemplateBindingCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING, new String[]{"rv._099"});
			private final /*@NonNull*/ CardinalitySolutionStep _489 // check-rule basecs::TypedTypeRefCS.ownedBinding : TemplateBindingCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING, new String[]{"rv._099"});
			private final /*@NonNull*/ CardinalitySolutionStep _490 // check-rule basecs::TypedTypeRefCS.ownedBinding : TemplateBindingCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING, new String[]{"rv._099"});
			private final /*@NonNull*/ CardinalitySolutionStep _491 // check-rule basecs::TypedTypeRefCS.ownedBinding : TemplateBindingCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING, new String[]{"rv._099"});
			private final /*@NonNull*/ CardinalitySolutionStep _492 // check-rule basecs::TypedTypeRefCS.ownedPathName : PathNameCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME, new String[]{"rv._073"});
			private final /*@NonNull*/ CardinalitySolutionStep _493 // check-rule basecs::TypedTypeRefCS.ownedPathName : PathNameCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME, new String[]{"rv._073"});
			private final /*@NonNull*/ CardinalitySolutionStep _494 // check-rule basecs::TypedTypeRefCS.ownedPathName : PathNameCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME, new String[]{"rv._073"});
			private final /*@NonNull*/ CardinalitySolutionStep _495 // check-rule basecs::TypedTypeRefCS.ownedPathName : PathNameCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME, new String[]{"rv._073"});
			private final /*@NonNull*/ CardinalitySolutionStep _496 // check-rule basecs::TypedTypeRefCS.ownedPathName : PathNameCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME, new String[]{"rv._073"});
			private final /*@NonNull*/ CardinalitySolutionStep _497 // check-rule basecs::TypedTypeRefCS.ownedPathName : PathNameCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME, new String[]{"rv._073"});
			private final /*@NonNull*/ CardinalitySolutionStep _498 // check-rule basecs::TypedTypeRefCS.ownedPathName : PathNameCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME, new String[]{"rv._073"});
			private final /*@NonNull*/ CardinalitySolutionStep _499 // check-rule basecs::TypedTypeRefCS.ownedPathName : PathNameCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME, new String[]{"rv._073"});
			private final /*@NonNull*/ CardinalitySolutionStep _500 // check-rule basecs::TypedTypeRefCS.ownedPathName : PathNameCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME, new String[]{"rv._073"});
			private final /*@NonNull*/ CardinalitySolutionStep _501 // check-rule basecs::WildcardTypeRefCS.ownedExtends : TypedRefCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS, new String[]{"rv._117"});
			private final /*@NonNull*/ CardinalitySolutionStep _502 // check-rule basecs::WildcardTypeRefCS.ownedExtends : TypedRefCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS, new String[]{"rv._117"});
			private final /*@NonNull*/ CardinalitySolutionStep _503 // check-rule essentialoclcs::AbstractNameExpCS.ownedCurlyBracketedClause : CurlyBracketedClauseCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, new String[]{"rv._013"});
			private final /*@NonNull*/ CardinalitySolutionStep _504 // check-rule essentialoclcs::AbstractNameExpCS.ownedCurlyBracketedClause : CurlyBracketedClauseCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, new String[]{"rv._013"});
			private final /*@NonNull*/ CardinalitySolutionStep _505 // check-rule essentialoclcs::AbstractNameExpCS.ownedCurlyBracketedClause : CurlyBracketedClauseCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, new String[]{"rv._013"});
			private final /*@NonNull*/ CardinalitySolutionStep _506 // check-rule essentialoclcs::AbstractNameExpCS.ownedPathName : PathNameCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME, new String[]{"rv._073"});
			private final /*@NonNull*/ CardinalitySolutionStep _507 // check-rule essentialoclcs::AbstractNameExpCS.ownedPathName : PathNameCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME, new String[]{"rv._073"});
			private final /*@NonNull*/ CardinalitySolutionStep _508 // check-rule essentialoclcs::AbstractNameExpCS.ownedPathName : PathNameCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME, new String[]{"rv._073"});
			private final /*@NonNull*/ CardinalitySolutionStep _509 // check-rule essentialoclcs::AbstractNameExpCS.ownedRoundBracketedClause : RoundBracketedClauseCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE, new String[]{"rv._084"});
			private final /*@NonNull*/ CardinalitySolutionStep _510 // check-rule essentialoclcs::AbstractNameExpCS.ownedRoundBracketedClause : RoundBracketedClauseCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE, new String[]{"rv._084"});
			private final /*@NonNull*/ CardinalitySolutionStep _511 // check-rule essentialoclcs::AbstractNameExpCS.ownedRoundBracketedClause : RoundBracketedClauseCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE, new String[]{"rv._084"});
			private final /*@NonNull*/ CardinalitySolutionStep _512 // check-rule essentialoclcs::AbstractNameExpCS.ownedSquareBracketedClauses : SquareBracketedClauseCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES, new String[]{"rv._093"});
			private final /*@NonNull*/ CardinalitySolutionStep _513 // check-rule essentialoclcs::AbstractNameExpCS.ownedSquareBracketedClauses : SquareBracketedClauseCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES, new String[]{"rv._093"});
			private final /*@NonNull*/ CardinalitySolutionStep _514 // check-rule essentialoclcs::AbstractNameExpCS.ownedSquareBracketedClauses : SquareBracketedClauseCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES, new String[]{"rv._093"});
			private final /*@NonNull*/ CardinalitySolutionStep _515 // check-rule essentialoclcs::CollectionLiteralExpCS.ownedParts : CollectionLiteralPartCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS, new String[]{"rv._009"});
			private final /*@NonNull*/ CardinalitySolutionStep _516 // check-rule essentialoclcs::CollectionLiteralExpCS.ownedParts : CollectionLiteralPartCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS, new String[]{"rv._009"});
			private final /*@NonNull*/ CardinalitySolutionStep _517 // check-rule essentialoclcs::CollectionLiteralExpCS.ownedType : CollectionTypeCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE, new String[]{"rv._011"});
			private final /*@NonNull*/ CardinalitySolutionStep _518 // check-rule essentialoclcs::CollectionLiteralExpCS.ownedType : CollectionTypeCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE, new String[]{"rv._011"});
			private final /*@NonNull*/ CardinalitySolutionStep _519 // check-rule essentialoclcs::CollectionLiteralPartCS.ownedExpression : ExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION, new String[]{"rv._030"});
			private final /*@NonNull*/ CardinalitySolutionStep _520 // check-rule essentialoclcs::CollectionLiteralPartCS.ownedExpression : PatternExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION, new String[]{"rv._074"});
			private final /*@NonNull*/ CardinalitySolutionStep _521 // check-rule essentialoclcs::CollectionLiteralPartCS.ownedLastExpression : ExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_LAST_EXPRESSION, new String[]{"rv._030"});
			private final /*@NonNull*/ CardinalitySolutionStep _522 // check-rule essentialoclcs::CollectionPatternCS.ownedParts : PatternExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS, new String[]{"rv._074"});
			private final /*@NonNull*/ CardinalitySolutionStep _523 // check-rule essentialoclcs::CollectionPatternCS.ownedParts : PatternExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS, new String[]{"rv._074"});
			private final /*@NonNull*/ CardinalitySolutionStep _524 // check-rule essentialoclcs::CollectionPatternCS.ownedType : CollectionTypeCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE, new String[]{"rv._011"});
			private final /*@NonNull*/ CardinalitySolutionStep _525 // check-rule essentialoclcs::CollectionPatternCS.ownedType : CollectionTypeCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE, new String[]{"rv._011"});
			private final /*@NonNull*/ CardinalitySolutionStep _526 // check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : MultiplicityCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY, new String[]{"rv._056"});
			private final /*@NonNull*/ CardinalitySolutionStep _527 // check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : MultiplicityCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY, new String[]{"rv._056"});
			private final /*@NonNull*/ CardinalitySolutionStep _528 // check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : MultiplicityCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY, new String[]{"rv._056"});
			private final /*@NonNull*/ CardinalitySolutionStep _529 // check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : MultiplicityCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY, new String[]{"rv._056"});
			private final /*@NonNull*/ CardinalitySolutionStep _530 // check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : MultiplicityCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY, new String[]{"rv._056"});
			private final /*@NonNull*/ CardinalitySolutionStep _531 // check-rule essentialoclcs::CollectionTypeCS.ownedType : TypeExpWithoutMultiplicityCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE, new String[]{"rv._108"});
			private final /*@NonNull*/ CardinalitySolutionStep _532 // check-rule essentialoclcs::CollectionTypeCS.ownedType : TypeExpWithoutMultiplicityCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE, new String[]{"rv._108"});
			private final /*@NonNull*/ CardinalitySolutionStep _533 // check-rule essentialoclcs::CollectionTypeCS.ownedType : TypeExpWithoutMultiplicityCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE, new String[]{"rv._108"});
			private final /*@NonNull*/ CardinalitySolutionStep _534 // check-rule essentialoclcs::CollectionTypeCS.ownedType : TypeExpWithoutMultiplicityCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE, new String[]{"rv._108"});
			private final /*@NonNull*/ CardinalitySolutionStep _535 // check-rule essentialoclcs::CollectionTypeCS.ownedType : TypeExpWithoutMultiplicityCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE, new String[]{"rv._108"});
			private final /*@NonNull*/ CardinalitySolutionStep _536 // check-rule essentialoclcs::ContextCS.ownedExpression : ExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION, new String[]{"rv._030"});
			private final /*@NonNull*/ CardinalitySolutionStep _537 // check-rule essentialoclcs::CurlyBracketedClauseCS.ownedParts : ShadowPartCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS, new String[]{"rv._090"});
			private final /*@NonNull*/ CardinalitySolutionStep _538 // check-rule essentialoclcs::ExpSpecificationCS.ownedExpression : ExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.EXP_SPECIFICATION_CS__OWNED_EXPRESSION, new String[]{"rv._030"});
			private final /*@NonNull*/ CardinalitySolutionStep _539 // check-rule essentialoclcs::IfExpCS.ownedCondition : ExpCS|PatternExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION, new String[]{"rv._030", "rv._074"});
			private final /*@NonNull*/ CardinalitySolutionStep _540 // check-rule essentialoclcs::IfExpCS.ownedCondition : ExpCS|PatternExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION, new String[]{"rv._030", "rv._074"});
			private final /*@NonNull*/ CardinalitySolutionStep _541 // check-rule essentialoclcs::IfExpCS.ownedCondition : ExpCS|PatternExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION, new String[]{"rv._030", "rv._074"});
			private final /*@NonNull*/ CardinalitySolutionStep _542 // check-rule essentialoclcs::IfExpCS.ownedElseExpression : ExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION, new String[]{"rv._030"});
			private final /*@NonNull*/ CardinalitySolutionStep _543 // check-rule essentialoclcs::IfExpCS.ownedElseExpression : ExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION, new String[]{"rv._030"});
			private final /*@NonNull*/ CardinalitySolutionStep _544 // check-rule essentialoclcs::IfExpCS.ownedElseExpression : ExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION, new String[]{"rv._030"});
			private final /*@NonNull*/ CardinalitySolutionStep _545 // check-rule essentialoclcs::IfExpCS.ownedIfThenExpressions : ElseIfThenExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS, new String[]{"rv._020"});
			private final /*@NonNull*/ CardinalitySolutionStep _546 // check-rule essentialoclcs::IfExpCS.ownedIfThenExpressions : ElseIfThenExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS, new String[]{"rv._020"});
			private final /*@NonNull*/ CardinalitySolutionStep _547 // check-rule essentialoclcs::IfExpCS.ownedIfThenExpressions : ElseIfThenExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS, new String[]{"rv._020"});
			private final /*@NonNull*/ CardinalitySolutionStep _548 // check-rule essentialoclcs::IfExpCS.ownedThenExpression : ExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION, new String[]{"rv._030"});
			private final /*@NonNull*/ CardinalitySolutionStep _549 // check-rule essentialoclcs::IfExpCS.ownedThenExpression : ExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION, new String[]{"rv._030"});
			private final /*@NonNull*/ CardinalitySolutionStep _550 // check-rule essentialoclcs::IfExpCS.ownedThenExpression : ExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION, new String[]{"rv._030"});
			private final /*@NonNull*/ CardinalitySolutionStep _551 // check-rule essentialoclcs::IfThenExpCS.ownedCondition : ExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_CONDITION, new String[]{"rv._030"});
			private final /*@NonNull*/ CardinalitySolutionStep _552 // check-rule essentialoclcs::IfThenExpCS.ownedThenExpression : ExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION, new String[]{"rv._030"});
			private final /*@NonNull*/ CardinalitySolutionStep _553 // check-rule essentialoclcs::InfixExpCS.ownedLeft : PrefixedPrimaryExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.INFIX_EXP_CS__OWNED_LEFT, new String[]{"rv._078"});
			private final /*@NonNull*/ CardinalitySolutionStep _554 // check-rule essentialoclcs::LambdaLiteralExpCS.ownedExpressionCS : ExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS, new String[]{"rv._030"});
			private final /*@NonNull*/ CardinalitySolutionStep _555 // check-rule essentialoclcs::LambdaLiteralExpCS.ownedExpressionCS : ExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS, new String[]{"rv._030"});
			private final /*@NonNull*/ CardinalitySolutionStep _556 // check-rule essentialoclcs::LambdaLiteralExpCS.ownedExpressionCS : ExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS, new String[]{"rv._030"});
			private final /*@NonNull*/ CardinalitySolutionStep _557 // check-rule essentialoclcs::LetExpCS.ownedInExpression : ExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION, new String[]{"rv._030"});
			private final /*@NonNull*/ CardinalitySolutionStep _558 // check-rule essentialoclcs::LetExpCS.ownedInExpression : ExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION, new String[]{"rv._030"});
			private final /*@NonNull*/ CardinalitySolutionStep _559 // check-rule essentialoclcs::LetExpCS.ownedVariables : LetVariableCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES, new String[]{"rv._046"});
			private final /*@NonNull*/ CardinalitySolutionStep _560 // check-rule essentialoclcs::LetExpCS.ownedVariables : LetVariableCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES, new String[]{"rv._046"});
			private final /*@NonNull*/ CardinalitySolutionStep _561 // check-rule essentialoclcs::LetVariableCS.ownedRoundBracketedClause : RoundBracketedClauseCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE, new String[]{"rv._084"});
			private final /*@NonNull*/ CardinalitySolutionStep _562 // check-rule essentialoclcs::MapLiteralExpCS.ownedParts : MapLiteralPartCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS, new String[]{"rv._050"});
			private final /*@NonNull*/ CardinalitySolutionStep _563 // check-rule essentialoclcs::MapLiteralExpCS.ownedParts : MapLiteralPartCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS, new String[]{"rv._050"});
			private final /*@NonNull*/ CardinalitySolutionStep _564 // check-rule essentialoclcs::MapLiteralExpCS.ownedParts : MapLiteralPartCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS, new String[]{"rv._050"});
			private final /*@NonNull*/ CardinalitySolutionStep _565 // check-rule essentialoclcs::MapLiteralExpCS.ownedType : MapTypeCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE, new String[]{"rv._051"});
			private final /*@NonNull*/ CardinalitySolutionStep _566 // check-rule essentialoclcs::MapLiteralExpCS.ownedType : MapTypeCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE, new String[]{"rv._051"});
			private final /*@NonNull*/ CardinalitySolutionStep _567 // check-rule essentialoclcs::MapLiteralExpCS.ownedType : MapTypeCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE, new String[]{"rv._051"});
			private final /*@NonNull*/ CardinalitySolutionStep _568 // check-rule essentialoclcs::MapLiteralPartCS.ownedKey : ExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_KEY, new String[]{"rv._030"});
			private final /*@NonNull*/ CardinalitySolutionStep _569 // check-rule essentialoclcs::MapLiteralPartCS.ownedValue : ExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_VALUE, new String[]{"rv._030"});
			private final /*@NonNull*/ CardinalitySolutionStep _570 // check-rule essentialoclcs::MapTypeCS.ownedKeyType : TypeExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE, new String[]{"rv._107"});
			private final /*@NonNull*/ CardinalitySolutionStep _571 // check-rule essentialoclcs::MapTypeCS.ownedKeyType : TypeExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE, new String[]{"rv._107"});
			private final /*@NonNull*/ CardinalitySolutionStep _572 // check-rule essentialoclcs::MapTypeCS.ownedKeyType : TypeExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE, new String[]{"rv._107"});
			private final /*@NonNull*/ CardinalitySolutionStep _573 // check-rule essentialoclcs::MapTypeCS.ownedKeyType : TypeExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE, new String[]{"rv._107"});
			private final /*@NonNull*/ CardinalitySolutionStep _574 // check-rule essentialoclcs::MapTypeCS.ownedKeyType : TypeExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE, new String[]{"rv._107"});
			private final /*@NonNull*/ CardinalitySolutionStep _575 // check-rule essentialoclcs::MapTypeCS.ownedValueType : TypeExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE, new String[]{"rv._107"});
			private final /*@NonNull*/ CardinalitySolutionStep _576 // check-rule essentialoclcs::MapTypeCS.ownedValueType : TypeExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE, new String[]{"rv._107"});
			private final /*@NonNull*/ CardinalitySolutionStep _577 // check-rule essentialoclcs::MapTypeCS.ownedValueType : TypeExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE, new String[]{"rv._107"});
			private final /*@NonNull*/ CardinalitySolutionStep _578 // check-rule essentialoclcs::MapTypeCS.ownedValueType : TypeExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE, new String[]{"rv._107"});
			private final /*@NonNull*/ CardinalitySolutionStep _579 // check-rule essentialoclcs::MapTypeCS.ownedValueType : TypeExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE, new String[]{"rv._107"});
			private final /*@NonNull*/ CardinalitySolutionStep _580 // check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : CoIteratorVariableCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR, new String[]{"rv._007"});
			private final /*@NonNull*/ CardinalitySolutionStep _581 // check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : CoIteratorVariableCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR, new String[]{"rv._007"});
			private final /*@NonNull*/ CardinalitySolutionStep _582 // check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : CoIteratorVariableCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR, new String[]{"rv._007"});
			private final /*@NonNull*/ CardinalitySolutionStep _583 // check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : CoIteratorVariableCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR, new String[]{"rv._007"});
			private final /*@NonNull*/ CardinalitySolutionStep _584 // check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : CoIteratorVariableCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR, new String[]{"rv._007"});
			private final /*@NonNull*/ CardinalitySolutionStep _585 // check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : CoIteratorVariableCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR, new String[]{"rv._007"});
			private final /*@NonNull*/ CardinalitySolutionStep _586 // check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : ExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION, new String[]{"rv._030"});
			private final /*@NonNull*/ CardinalitySolutionStep _587 // check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : ExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION, new String[]{"rv._030"});
			private final /*@NonNull*/ CardinalitySolutionStep _588 // check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : ExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION, new String[]{"rv._030"});
			private final /*@NonNull*/ CardinalitySolutionStep _589 // check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : ExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION, new String[]{"rv._030"});
			private final /*@NonNull*/ CardinalitySolutionStep _590 // check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : ExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION, new String[]{"rv._030"});
			private final /*@NonNull*/ CardinalitySolutionStep _591 // check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : ExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION, new String[]{"rv._030"});
			private final /*@NonNull*/ CardinalitySolutionStep _592 // check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : ExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION, new String[]{"rv._030"});
			private final /*@NonNull*/ CardinalitySolutionStep _593 // check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : ExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION, new String[]{"rv._030"});
			private final /*@NonNull*/ CardinalitySolutionStep _594 // check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : NavigatingArgExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, new String[]{"rv._061"});
			private final /*@NonNull*/ CardinalitySolutionStep _595 // check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : NavigatingArgExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, new String[]{"rv._061"});
			private final /*@NonNull*/ CardinalitySolutionStep _596 // check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : NavigatingArgExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, new String[]{"rv._061"});
			private final /*@NonNull*/ CardinalitySolutionStep _597 // check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : NavigatingArgExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, new String[]{"rv._061"});
			private final /*@NonNull*/ CardinalitySolutionStep _598 // check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : NavigatingArgExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, new String[]{"rv._061"});
			private final /*@NonNull*/ CardinalitySolutionStep _599 // check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : NavigatingArgExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, new String[]{"rv._061"});
			private final /*@NonNull*/ CardinalitySolutionStep _600 // check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : NavigatingArgExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, new String[]{"rv._061"});
			private final /*@NonNull*/ CardinalitySolutionStep _601 // check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : NavigatingArgExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, new String[]{"rv._061"});
			private final /*@NonNull*/ CardinalitySolutionStep _602 // check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : NavigatingArgExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, new String[]{"rv._061"});
			private final /*@NonNull*/ CardinalitySolutionStep _603 // check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : NavigatingArgExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION, new String[]{"rv._061"});
			private final /*@NonNull*/ CardinalitySolutionStep _604 // check-rule essentialoclcs::NavigatingArgCS.ownedType : TypeExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE, new String[]{"rv._107"});
			private final /*@NonNull*/ CardinalitySolutionStep _605 // check-rule essentialoclcs::NavigatingArgCS.ownedType : TypeExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE, new String[]{"rv._107"});
			private final /*@NonNull*/ CardinalitySolutionStep _606 // check-rule essentialoclcs::NavigatingArgCS.ownedType : TypeExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE, new String[]{"rv._107"});
			private final /*@NonNull*/ CardinalitySolutionStep _607 // check-rule essentialoclcs::NavigatingArgCS.ownedType : TypeExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE, new String[]{"rv._107"});
			private final /*@NonNull*/ CardinalitySolutionStep _608 // check-rule essentialoclcs::NavigatingArgCS.ownedType : TypeExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE, new String[]{"rv._107"});
			private final /*@NonNull*/ CardinalitySolutionStep _609 // check-rule essentialoclcs::NavigatingArgCS.ownedType : TypeExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE, new String[]{"rv._107"});
			private final /*@NonNull*/ CardinalitySolutionStep _610 // check-rule essentialoclcs::NavigatingArgCS.ownedType : TypeExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE, new String[]{"rv._107"});
			private final /*@NonNull*/ CardinalitySolutionStep _611 // check-rule essentialoclcs::NestedExpCS.ownedExpression : ExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION, new String[]{"rv._030"});
			private final /*@NonNull*/ CardinalitySolutionStep _612 // check-rule essentialoclcs::NestedExpCS.ownedExpression : ExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION, new String[]{"rv._030"});
			private final /*@NonNull*/ CardinalitySolutionStep _613 // check-rule essentialoclcs::NestedExpCS.ownedExpression : ExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION, new String[]{"rv._030"});
			private final /*@NonNull*/ CardinalitySolutionStep _614 // check-rule essentialoclcs::OperatorExpCS.ownedRight : ExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, new String[]{"rv._030"});
			private final /*@NonNull*/ CardinalitySolutionStep _615 // check-rule essentialoclcs::OperatorExpCS.ownedRight : PrefixedLetExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, new String[]{"rv._077"});
			private final /*@NonNull*/ CardinalitySolutionStep _616 // check-rule essentialoclcs::OperatorExpCS.ownedRight : PrefixedLetExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, new String[]{"rv._077"});
			private final /*@NonNull*/ CardinalitySolutionStep _617 // check-rule essentialoclcs::OperatorExpCS.ownedRight : PrefixedPrimaryExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, new String[]{"rv._078"});
			private final /*@NonNull*/ CardinalitySolutionStep _618 // check-rule essentialoclcs::OperatorExpCS.ownedRight : PrefixedPrimaryExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT, new String[]{"rv._078"});
			private final /*@NonNull*/ CardinalitySolutionStep _619 // check-rule essentialoclcs::PatternExpCS.ownedPatternType : TypeExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE, new String[]{"rv._107"});
			private final /*@NonNull*/ CardinalitySolutionStep _620 // check-rule essentialoclcs::RoundBracketedClauseCS.ownedArguments : NavigatingSemiArgCS|NavigatingBarArgCS|NavigatingCommaArgCS|NavigatingArgCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS, new String[]{"rv._064", "rv._062", "rv._063", "rv._060"});
			private final /*@NonNull*/ CardinalitySolutionStep _621 // check-rule essentialoclcs::ShadowPartCS.ownedInitExpression : ExpCS|PatternExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION, new String[]{"rv._030", "rv._074"});
			private final /*@NonNull*/ CardinalitySolutionStep _622 // check-rule essentialoclcs::ShadowPartCS.ownedInitExpression : StringLiteralExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION, new String[]{"rv._095"});
			private final /*@NonNull*/ CardinalitySolutionStep _623 // check-rule essentialoclcs::SquareBracketedClauseCS.ownedTerms : ExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS, new String[]{"rv._030"});
			private final /*@NonNull*/ CardinalitySolutionStep _624 // check-rule essentialoclcs::TupleLiteralExpCS.ownedParts : TupleLiteralPartCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS, new String[]{"rv._104"});
			private final /*@NonNull*/ CardinalitySolutionStep _625 // check-rule essentialoclcs::TupleLiteralExpCS.ownedParts : TupleLiteralPartCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS, new String[]{"rv._104"});
			private final /*@NonNull*/ CardinalitySolutionStep _626 // check-rule essentialoclcs::TupleLiteralExpCS.ownedParts : TupleLiteralPartCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS, new String[]{"rv._104"});
			private final /*@NonNull*/ CardinalitySolutionStep _627 // check-rule essentialoclcs::TypeLiteralExpCS.ownedType : TypeLiteralWithMultiplicityCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE, new String[]{"rv._112"});
			private final /*@NonNull*/ CardinalitySolutionStep _628 // check-rule essentialoclcs::TypeLiteralExpCS.ownedType : TypeLiteralWithMultiplicityCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE, new String[]{"rv._112"});
			private final /*@NonNull*/ CardinalitySolutionStep _629 // check-rule essentialoclcs::TypeLiteralExpCS.ownedType : TypeLiteralWithMultiplicityCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE, new String[]{"rv._112"});
			private final /*@NonNull*/ CardinalitySolutionStep _630 // check-rule essentialoclcs::TypeNameExpCS.ownedCurlyBracketedClause : CurlyBracketedClauseCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, new String[]{"rv._013"});
			private final /*@NonNull*/ CardinalitySolutionStep _631 // check-rule essentialoclcs::TypeNameExpCS.ownedCurlyBracketedClause : CurlyBracketedClauseCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, new String[]{"rv._013"});
			private final /*@NonNull*/ CardinalitySolutionStep _632 // check-rule essentialoclcs::TypeNameExpCS.ownedCurlyBracketedClause : CurlyBracketedClauseCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE, new String[]{"rv._013"});
			private final /*@NonNull*/ CardinalitySolutionStep _633 // check-rule essentialoclcs::TypeNameExpCS.ownedPathName : PathNameCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME, new String[]{"rv._073"});
			private final /*@NonNull*/ CardinalitySolutionStep _634 // check-rule essentialoclcs::TypeNameExpCS.ownedPathName : PathNameCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME, new String[]{"rv._073"});
			private final /*@NonNull*/ CardinalitySolutionStep _635 // check-rule essentialoclcs::TypeNameExpCS.ownedPathName : PathNameCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME, new String[]{"rv._073"});
			private final /*@NonNull*/ CardinalitySolutionStep _636 // check-rule essentialoclcs::TypeNameExpCS.ownedPatternGuard : ExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD, new String[]{"rv._030"});
			private final /*@NonNull*/ CardinalitySolutionStep _637 // check-rule essentialoclcs::TypeNameExpCS.ownedPatternGuard : ExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD, new String[]{"rv._030"});
			private final /*@NonNull*/ CardinalitySolutionStep _638 // check-rule essentialoclcs::TypeNameExpCS.ownedPatternGuard : ExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD, new String[]{"rv._030"});
			private final /*@NonNull*/ CardinalitySolutionStep _639 // check-rule essentialoclcs::VariableCS.ownedInitExpression : ExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION, new String[]{"rv._030"});
			private final /*@NonNull*/ CardinalitySolutionStep _640 // check-rule essentialoclcs::VariableCS.ownedInitExpression : ExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION, new String[]{"rv._030"});
			private final /*@NonNull*/ CardinalitySolutionStep _641 // check-rule essentialoclcs::VariableCS.ownedType : TypeExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE, new String[]{"rv._107"});
			private final /*@NonNull*/ CardinalitySolutionStep _642 // check-rule essentialoclcs::VariableCS.ownedType : TypeExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE, new String[]{"rv._107"});
			private final /*@NonNull*/ CardinalitySolutionStep _643 // check-rule essentialoclcs::VariableCS.ownedType : TypeExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep.RuleCheck(org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE, new String[]{"rv._107"});
		}

		private class _SerializationTerms
		{
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _000 // 1*'!serializable'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "!serializable");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _001 // 1*'#'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "#");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _002 // 1*'&&'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "&&");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _003 // 1*'('
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "(");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _004 // 1*')'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, ")");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _005 // 1*'*'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "*");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _006 // 1*'++'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "++");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _007 // 1*','
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, ",");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _008 // 1*'..'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "..");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _009 // 1*':'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, ":");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _010 // 1*'::'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "::");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _011 // 1*';'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, ";");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _012 // 1*'<'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "<");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _013 // 1*'<-'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "<-");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _014 // 1*'='
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "=");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _015 // 1*'>'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, ">");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _016 // 1*'?'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "?");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _017 // 1*'@'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "@");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _018 // 1*'Lambda'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "Lambda");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _019 // 1*'Map'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "Map");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _020 // 1*'Tuple'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "Tuple");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _021 // 1*'['
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "[");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _022 // 1*']'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "]");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _023 // 1*'annotation'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "annotation");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _024 // 1*'attribute'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "attribute");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _025 // 1*'body'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "body");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _026 // 1*'class'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "class");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _027 // 1*'datatype'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "datatype");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _028 // 1*'definition'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "definition");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _029 // 1*'derivation'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "derivation");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _030 // 1*'documentation'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "documentation");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _031 // 1*'else'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "else");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _032 // 1*'elseif'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "elseif");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _033 // 1*'endif'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "endif");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _034 // 1*'enum'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "enum");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _035 // 1*'extends'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "extends");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _036 // 1*'if'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "if");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _037 // 1*'import'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "import");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _038 // 1*'in'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "in");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _039 // 1*'initial'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "initial");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _040 // 1*'invalid'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "invalid");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _041 // 1*'invariant'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "invariant");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _042 // 1*'key'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "key");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _043 // 1*'let'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "let");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _044 // 1*'literal'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "literal");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _045 // 1*'module'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "module");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _046 // 1*'null'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "null");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _047 // 1*'operation'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "operation");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _048 // 1*'opposite'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "opposite");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _049 // 1*'package'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "package");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _050 // 1*'postcondition'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "postcondition");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _051 // 1*'pre'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "pre");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _052 // 1*'precondition'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "precondition");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _053 // 1*'property'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "property");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _054 // 1*'reference'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "reference");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _055 // 1*'self'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "self");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _056 // 1*'static'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "static");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _057 // 1*'sysml'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "sysml");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _058 // 1*'then'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "then");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _059 // 1*'throws'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "throws");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _060 // 1*'{'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "{");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _061 // 1*'|'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "|");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _062 // 1*'|?'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "|?");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _063 // 1*'}'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(-1, "}");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _064 // 1*default=SINGLE_QUOTED_STRING
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__DEFAULT);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _065 // 1*exprString=UNQUOTED_STRING
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.SPECIFICATION_CS__EXPR_STRING);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _066 // 1*instanceClassName=SINGLE_QUOTED_STRING
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.CLASS_CS__INSTANCE_CLASS_NAME);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _067 // 1*literal=SINGLE_QUOTED_STRING
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ENUMERATION_LITERAL_CS__LITERAL);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _068 // 1*lowerBound=LOWER
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__LOWER_BOUND);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _069 // 1*name=BinaryOperatorName
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _070 // 1*name=CollectionTypeIdentifier
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__NAME);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _071 // 1*name=EnumerationLiteralName
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _072 // 1*name=PrimitiveTypeIdentifier
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PRIMITIVE_TYPE_REF_CS__NAME);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _073 // 1*name=UnaryOperatorName
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _074 // 1*name=UnrestrictedName
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallsStep _075 // 1*name=UnrestrictedName|SINGLE_QUOTED_STRING
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallsStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					 new org.eclipse.ocl.xtext.base.cs2text.xtext.AbstractRuleValue [2]);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationSequenceStep _076 // 1*next-10-steps
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationSequenceStep(-1, 1, 11);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _077 // 1*nsPrefix=UnrestrictedName
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PACKAGE_CS__NS_PREFIX);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _078 // 1*nsURI=URI
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PACKAGE_CS__NS_URI);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _079 // 1*ownedActualParameter=TypeRefCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _080 // 1*ownedArguments+=NavigatingArgCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _081 // 1*ownedBinding=TemplateBindingCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_BINDING);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _082 // 1*ownedCoIterator=CoIteratorVariableCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_CO_ITERATOR);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _083 // 1*ownedCondition=ExpCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_CONDITION);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallsStep _084 // 1*ownedCondition=ExpCS|PatternExpCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallsStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_CONDITION,
					 new org.eclipse.ocl.xtext.base.cs2text.xtext.AbstractRuleValue [2]);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _085 // 1*ownedCurlyBracketedClause=CurlyBracketedClauseCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _086 // 1*ownedDetails+=DetailCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ANNOTATION_ELEMENT_CS__OWNED_DETAILS);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _087 // 1*ownedElseExpression=ExpCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_ELSE_EXPRESSION);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _088 // 1*ownedExceptions+=TypedRefCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS__OWNED_EXCEPTIONS);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _089 // 1*ownedExpression=ExpCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NESTED_EXP_CS__OWNED_EXPRESSION);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _090 // 1*ownedExpression=ExpCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.CONTEXT_CS__OWNED_EXPRESSION);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _091 // 1*ownedExpression=ExpCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _092 // 1*ownedExpression=ExpCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.EXP_SPECIFICATION_CS__OWNED_EXPRESSION);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _093 // 1*ownedExpression=PatternExpCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_EXPRESSION);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _094 // 1*ownedExpressionCS=ExpCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS__OWNED_EXPRESSION_CS);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _095 // 1*ownedExtends+=TypedRefCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPE_PARAMETER_CS__OWNED_EXTENDS);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _096 // 1*ownedExtends=TypedRefCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS__OWNED_EXTENDS);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _097 // 1*ownedImplicitOpposites+=ImplicitOppositeCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.REFERENCE_CS__OWNED_IMPLICIT_OPPOSITES);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _098 // 1*ownedInExpression=ExpCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_IN_EXPRESSION);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _099 // 1*ownedInitExpression=ExpCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_INIT_EXPRESSION);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _100 // 1*ownedInitExpression=ExpCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_INIT_EXPRESSION);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallsStep _101 // 1*ownedInitExpression=ExpCS|PatternExpCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallsStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION,
					 new org.eclipse.ocl.xtext.base.cs2text.xtext.AbstractRuleValue [2]);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _102 // 1*ownedInitExpression=StringLiteralExpCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.SHADOW_PART_CS__OWNED_INIT_EXPRESSION);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _103 // 1*ownedKey=ExpCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_KEY);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _104 // 1*ownedKeyType=TypeExpCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_KEY_TYPE);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _105 // 1*ownedLastExpression=ExpCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS__OWNED_LAST_EXPRESSION);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _106 // 1*ownedLeft=PrefixedPrimaryExpCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.INFIX_EXP_CS__OWNED_LEFT);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _107 // 1*ownedMessageSpecification=SpecificationCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _108 // 1*ownedNameExpression=NavigatingArgExpCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_NAME_EXPRESSION);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _109 // 1*ownedParameters+=ParameterCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS__OWNED_PARAMETERS);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _110 // 1*ownedParameters+=TypeParameterCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _111 // 1*ownedParts+=CollectionLiteralPartCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_PARTS);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _112 // 1*ownedParts+=MapLiteralPartCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_PARTS);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _113 // 1*ownedParts+=PatternExpCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_PARTS);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _114 // 1*ownedParts+=ShadowPartCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS__OWNED_PARTS);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _115 // 1*ownedParts+=TupleLiteralPartCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS__OWNED_PARTS);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _116 // 1*ownedParts+=TuplePartCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TUPLE_TYPE_CS__OWNED_PARTS);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _117 // 1*ownedPathElements+=FirstPathElementCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _118 // 1*ownedPathElements+=NextPathElementCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _119 // 1*ownedPathElements+=URIFirstPathElementCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PATH_NAME_CS__OWNED_PATH_ELEMENTS);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _120 // 1*ownedPathName=PathNameCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATH_NAME);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _121 // 1*ownedPathName=PathNameCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_TYPE_REF_CS__OWNED_PATH_NAME);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _122 // 1*ownedPathName=PathNameCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_PATH_NAME);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _123 // 1*ownedPathName=PathNameCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MODEL_ELEMENT_REF_CS__OWNED_PATH_NAME);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _124 // 1*ownedPathName=URIPathNameCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.IMPORT_CS__OWNED_PATH_NAME);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _125 // 1*ownedPatternGuard=ExpCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS__OWNED_PATTERN_GUARD);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _126 // 1*ownedPatternType=TypeExpCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__OWNED_PATTERN_TYPE);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _127 // 1*ownedRight=ExpCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _128 // 1*ownedRight=PrefixedLetExpCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _129 // 1*ownedRight=PrefixedPrimaryExpCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.OPERATOR_EXP_CS__OWNED_RIGHT);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _130 // 1*ownedSubstitutions+=TemplateParameterSubstitutionCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _131 // 1*ownedSuperTypes+=TypedRefCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_SUPER_TYPES);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _132 // 1*ownedTerms+=ExpCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS__OWNED_TERMS);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _133 // 1*ownedThenExpression=ExpCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS__OWNED_THEN_EXPRESSION);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _134 // 1*ownedThenExpression=ExpCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_THEN_EXPRESSION);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _135 // 1*ownedType=CollectionTypeCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__OWNED_TYPE);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _136 // 1*ownedType=CollectionTypeCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS__OWNED_TYPE);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _137 // 1*ownedType=MapTypeCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS__OWNED_TYPE);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _138 // 1*ownedType=TypeExpCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS__OWNED_TYPE);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _139 // 1*ownedType=TypeExpCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.VARIABLE_CS__OWNED_TYPE);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _140 // 1*ownedType=TypeExpCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _141 // 1*ownedType=TypeExpWithoutMultiplicityCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_TYPE);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _142 // 1*ownedType=TypeLiteralWithMultiplicityCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS__OWNED_TYPE);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _143 // 1*ownedType=TypedMultiplicityRefCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_ELEMENT_CS__OWNED_TYPE);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _144 // 1*ownedValue=ExpCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS__OWNED_VALUE);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _145 // 1*ownedValueType=TypeExpCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.MAP_TYPE_CS__OWNED_VALUE_TYPE);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _146 // 1*ownedVariables+=LetVariableCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.LET_EXP_CS__OWNED_VARIABLES);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignStep _147 // 1*qualifiers
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_ELEMENT_CS__QUALIFIERS);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationCrossReferenceStep _148 // 1*referredElement=URI
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationCrossReferenceStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, "rv._121");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationCrossReferenceStep _149 // 1*referredElement=UnreservedName
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationCrossReferenceStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, "rv._126");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationCrossReferenceStep _150 // 1*referredElement=UnrestrictedName
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationCrossReferenceStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, "rv._127");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationCrossReferenceStep _151 // 1*referredElement=UnrestrictedName
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationCrossReferenceStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PATH_ELEMENT_CS__REFERRED_ELEMENT, "rv._127");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationCrossReferenceStep _152 // 1*referredKeys+=UnrestrictedName
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationCrossReferenceStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.REFERENCE_CS__REFERRED_KEYS, "rv._127");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationCrossReferenceStep _153 // 1*referredKeys+=UnrestrictedName
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationCrossReferenceStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.REFERENCE_CS__REFERRED_KEYS, "rv._127");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationCrossReferenceStep _154 // 1*referredOpposite=UnrestrictedName
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationCrossReferenceStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.REFERENCE_CS__REFERRED_OPPOSITE, "rv._127");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationCrossReferenceStep _155 // 1*referredProperty=UnrestrictedName
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationCrossReferenceStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.SHADOW_PART_CS__REFERRED_PROPERTY, "rv._127");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _156 // 1*restVariableName=Identifier
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS__REST_VARIABLE_NAME);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignStep _157 // 1*stringBounds
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MULTIPLICITY_STRING_CS__STRING_BOUNDS);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignStep _158 // 1*symbol
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS__SYMBOL);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _159 // 1*symbol=NUMBER_LITERAL
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NUMBER_LITERAL_EXP_CS__SYMBOL);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _160 // 1*upperBound=UPPER
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS__UPPER_BOUND);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _161 // 1*value=SIGNED
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(-1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ENUMERATION_LITERAL_CS__VALUE);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _162 // V00*'abstract'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(0, "abstract");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _163 // V00*'callable'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(0, "callable");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _164 // V00*'definition'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(0, "definition");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _165 // V00*'primitive'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(0, "primitive");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _166 // V00*'static'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(0, "static");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _167 // V00*'|1'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(0, "|1");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallsStep _168 // V00*name=UnrestrictedName|SINGLE_QUOTED_STRING
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallsStep(0, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.NAMED_ELEMENT_CS__NAME,
					 new org.eclipse.ocl.xtext.base.cs2text.xtext.AbstractRuleValue [2]);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationSequenceStep _169 // V00*next-2-steps
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationSequenceStep(0, 4, 6);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _170 // V00*ownedIfThenExpressions+=ElseIfThenExpCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(0, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.IF_EXP_CS__OWNED_IF_THEN_EXPRESSIONS);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _171 // V00*ownedMultiplicity=MultiplicityCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(0, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _172 // V00*ownedRoundBracketedClause=RoundBracketedClauseCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(0, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.LET_VARIABLE_CS__OWNED_ROUND_BRACKETED_CLAUSE);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _173 // V00*ownedSignature=TemplateSignatureCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(0, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _174 // V00*ownedSquareBracketedClauses+=SquareBracketedClauseCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(0, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_SQUARE_BRACKETED_CLAUSES);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _175 // V00*patternVariableName=UnrestrictedName
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(0, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.PATTERN_EXP_CS__PATTERN_VARIABLE_NAME);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _176 // V00*segments+=StringLiteral
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(0, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.STRING_LITERAL_EXP_CS__SEGMENTS);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _177 // V00*value=SINGLE_QUOTED_STRING
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(0, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.DOCUMENTATION_CS__VALUE);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallsStep _178 // V00*values+=SINGLE_QUOTED_STRING|ML_SINGLE_QUOTED_STRING
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallsStep(0, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.DETAIL_CS__VALUES,
					 new org.eclipse.ocl.xtext.base.cs2text.xtext.AbstractRuleValue [2]);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _179 // V01*'::*'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(1, "::*");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _180 // V01*'|1'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(1, "|1");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationSequenceStep _181 // V01*next-6-steps
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationSequenceStep(1, 4, 10);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallsStep _182 // V01*ownedArguments+=NavigatingCommaArgCS|NavigatingSemiArgCS|NavigatingBarArgCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallsStep(1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS__OWNED_ARGUMENTS,
					 new org.eclipse.ocl.xtext.base.cs2text.xtext.AbstractRuleValue [3]);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _183 // V01*ownedCollectionMultiplicity=MultiplicityCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS__OWNED_COLLECTION_MULTIPLICITY);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _184 // V01*ownedImports+=ImportCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ROOT_CS__OWNED_IMPORTS);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _185 // V01*ownedMultiplicity=MultiplicityCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _186 // V01*ownedMultiplicity=MultiplicityCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATE_BINDING_CS__OWNED_MULTIPLICITY);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _187 // V01*ownedRoundBracketedClause=RoundBracketedClauseCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(1, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_ROUND_BRACKETED_CLAUSE);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _188 // V01*ownedSignature=TemplateSignatureCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(1, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationSequenceStep _189 // V02*next-2-steps
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationSequenceStep(2, 7, 9);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _190 // V02*ownedAnnotations+=AnnotationElementCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(2, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _191 // V02*ownedCurlyBracketedClause=CurlyBracketedClauseCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(2, org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.ABSTRACT_NAME_EXP_CS__OWNED_CURLY_BRACKETED_CLAUSE);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _192 // V02*ownedMultiplicity=MultiplicityCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(2, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _193 // V02*ownedPackages+=PackageCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(2, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _194 // V02*ownedSpecification=SpecificationCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(2, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _195 // V03*'serializable'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(3, "serializable");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationSequenceStep _196 // V03*next-1-steps
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationSequenceStep(3, 12, 13);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _197 // V03*ownedAnnotations+=AnnotationElementCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(3, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _198 // V03*ownedMultiplicity=MultiplicityCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(3, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_REF_CS__OWNED_MULTIPLICITY);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _199 // V03*ownedPackages+=PackageCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(3, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PACKAGE_OWNER_CS__OWNED_PACKAGES);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _200 // V03*ownedSpecification=SpecificationCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(3, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.CONSTRAINT_CS__OWNED_SPECIFICATION);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _201 // V04*'serializable'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(4, "serializable");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationSequenceStep _202 // V04*next-1-steps
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationSequenceStep(4, 14, 15);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _203 // V04*ownedAnnotations+=AnnotationElementCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(4, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _204 // V04*ownedClasses+=ClassCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(4, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PACKAGE_CS__OWNED_CLASSES);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _205 // V04*ownedContents+=ModelElementCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(4, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ANNOTATION_CS__OWNED_CONTENTS);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _206 // V04*ownedLiterals+=EnumerationLiteralCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(4, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ENUMERATION_CS__OWNED_LITERALS);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationSequenceStep _207 // V05*next-4-steps
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationSequenceStep(5, 17, 21);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _208 // V05*ownedAnnotations+=AnnotationElementCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(5, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _209 // V05*ownedConstraints+=InvariantConstraintCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(5, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _210 // V05*ownedLiterals+=EnumerationLiteralCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(5, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ENUMERATION_CS__OWNED_LITERALS);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _211 // V05*ownedReferences+=ModelElementRefCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(5, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ANNOTATION_CS__OWNED_REFERENCES);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep _212 // V06*'interface'
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep(6, "interface");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationSequenceStep _213 // V06*next-4-steps
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationSequenceStep(6, 19, 23);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _214 // V06*ownedAnnotations+=AnnotationElementCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(6, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _215 // V06*ownedConstraints+=InvariantConstraintCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(6, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _216 // V06*ownedDefaultExpressions+=SpecificationCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(6, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationSequenceStep _217 // V07*next-4-steps
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationSequenceStep(7, 22, 26);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _218 // V07*ownedAnnotations+=AnnotationElementCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(7, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _219 // V07*ownedDefaultExpressions+=SpecificationCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(7, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationSequenceStep _220 // V08*next-4-steps
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationSequenceStep(8, 24, 28);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _221 // V08*ownedAnnotations+=AnnotationElementCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(8, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _222 // V08*ownedDefaultExpressions+=SpecificationCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(8, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _223 // V08*ownedOperations+=OperationCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(8, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_OPERATIONS);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationSequenceStep _224 // V09*next-4-steps
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationSequenceStep(9, 29, 33);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _225 // V09*ownedAnnotations+=AnnotationElementCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(9, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MODEL_ELEMENT_CS__OWNED_ANNOTATIONS);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _226 // V09*ownedDefaultExpressions+=SpecificationCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(9, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _227 // V09*ownedPreconditions+=PreconditionConstraintCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(9, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _228 // V09*ownedProperties+=StructuralFeatureCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(9, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.STRUCTURED_CLASS_CS__OWNED_PROPERTIES);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationSequenceStep _229 // V10*next-4-steps
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationSequenceStep(10, 29, 33);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _230 // V10*ownedConstraints+=InvariantConstraintCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(10, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.CLASS_CS__OWNED_CONSTRAINTS);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _231 // V10*ownedDefaultExpressions+=SpecificationCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(10, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _232 // V10*ownedPreconditions+=PreconditionConstraintCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(10, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS__OWNED_PRECONDITIONS);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationSequenceStep _233 // V11*next-4-steps
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationSequenceStep(11, 31, 35);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _234 // V11*ownedBodyExpressions+=SpecificationCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(11, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS__OWNED_BODY_EXPRESSIONS);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _235 // V11*ownedDefaultExpressions+=SpecificationCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(11, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationSequenceStep _236 // V12*next-2-steps
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationSequenceStep(12, 37, 39);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _237 // V12*ownedBodyExpressions+=SpecificationCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(12, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS__OWNED_BODY_EXPRESSIONS);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _238 // V12*ownedDefaultExpressions+=SpecificationCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(12, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _239 // V12*ownedPostconditions+=PostconditionConstraintCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(12, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationSequenceStep _240 // V13*next-2-steps
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationSequenceStep(13, 39, 41);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep _241 // V13*ownedPostconditions+=PostconditionConstraintCS
										= new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep(13, org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS__OWNED_POSTCONDITIONS);

			private final void init() {
				_064.init(rv._087);
				_065.init(rv._119);
				_066.init(rv._087);
				_067.init(rv._087);
				_068.init(rv._043);
				_069.init(rv._004);
				_070.init(rv._012);
				_071.init(rv._023);
				_072.init(rv._082);
				_073.init(rv._124);
				_074.init(rv._127);
				_075.init(new org.eclipse.ocl.xtext.base.cs2text.xtext.AbstractRuleValue [] {rv._127/*UnrestrictedName*/, rv._087/*SINGLE_QUOTED_STRING*/});
				_077.init(rv._127);
				_078.init(rv._121);
				_079.init(rv._115);
				_080.init(rv._060);
				_081.init(rv._099);
				_082.init(rv._007);
				_083.init(rv._030);
				_084.init(new org.eclipse.ocl.xtext.base.cs2text.xtext.AbstractRuleValue [] {rv._030/*ExpCS*/, rv._074/*PatternExpCS*/});
				_085.init(rv._013);
				_086.init(rv._016);
				_087.init(rv._030);
				_088.init(rv._117);
				_089.init(rv._030);
				_090.init(rv._030);
				_091.init(rv._030);
				_092.init(rv._030);
				_093.init(rv._074);
				_094.init(rv._030);
				_095.init(rv._117);
				_096.init(rv._117);
				_097.init(rv._037);
				_098.init(rv._030);
				_099.init(rv._030);
				_100.init(rv._030);
				_101.init(new org.eclipse.ocl.xtext.base.cs2text.xtext.AbstractRuleValue [] {rv._030/*ExpCS*/, rv._074/*PatternExpCS*/});
				_102.init(rv._095);
				_103.init(rv._030);
				_104.init(rv._107);
				_105.init(rv._030);
				_106.init(rv._078);
				_107.init(rv._092);
				_108.init(rv._061);
				_109.init(rv._072);
				_110.init(rv._114);
				_111.init(rv._009);
				_112.init(rv._050);
				_113.init(rv._074);
				_114.init(rv._090);
				_115.init(rv._104);
				_116.init(rv._105);
				_117.init(rv._031);
				_118.init(rv._067);
				_119.init(rv._122);
				_120.init(rv._073);
				_121.init(rv._073);
				_122.init(rv._073);
				_123.init(rv._073);
				_124.init(rv._123);
				_125.init(rv._030);
				_126.init(rv._107);
				_127.init(rv._030);
				_128.init(rv._077);
				_129.init(rv._078);
				_130.init(rv._100);
				_131.init(rv._117);
				_132.init(rv._030);
				_133.init(rv._030);
				_134.init(rv._030);
				_135.init(rv._011);
				_136.init(rv._011);
				_137.init(rv._051);
				_138.init(rv._107);
				_139.init(rv._107);
				_140.init(rv._107);
				_141.init(rv._108);
				_142.init(rv._112);
				_143.init(rv._116);
				_144.init(rv._030);
				_145.init(rv._107);
				_146.init(rv._046);
				_156.init(rv._035);
				_159.init(rv._058);
				_160.init(rv._120);
				_161.init(rv._085);
				_168.init(new org.eclipse.ocl.xtext.base.cs2text.xtext.AbstractRuleValue [] {rv._127/*UnrestrictedName*/, rv._087/*SINGLE_QUOTED_STRING*/});
				_170.init(rv._020);
				_171.init(rv._056);
				_172.init(rv._084);
				_173.init(rv._101);
				_174.init(rv._093);
				_175.init(rv._127);
				_176.init(rv._094);
				_177.init(rv._087);
				_178.init(new org.eclipse.ocl.xtext.base.cs2text.xtext.AbstractRuleValue [] {rv._087/*SINGLE_QUOTED_STRING*/, rv._048/*ML_SINGLE_QUOTED_STRING*/});
				_182.init(new org.eclipse.ocl.xtext.base.cs2text.xtext.AbstractRuleValue [] {rv._063/*NavigatingCommaArgCS*/, rv._064/*NavigatingSemiArgCS*/, rv._062/*NavigatingBarArgCS*/});
				_183.init(rv._056);
				_184.init(rv._038);
				_185.init(rv._056);
				_186.init(rv._056);
				_187.init(rv._084);
				_188.init(rv._101);
				_190.init(rv._002);
				_191.init(rv._013);
				_192.init(rv._056);
				_193.init(rv._071);
				_194.init(rv._092);
				_197.init(rv._002);
				_198.init(rv._056);
				_199.init(rv._071);
				_200.init(rv._092);
				_203.init(rv._002);
				_204.init(rv._006);
				_205.init(rv._053);
				_206.init(rv._022);
				_208.init(rv._002);
				_209.init(rv._041);
				_210.init(rv._022);
				_211.init(rv._054);
				_214.init(rv._002);
				_215.init(rv._041);
				_216.init(rv._092);
				_218.init(rv._002);
				_219.init(rv._092);
				_221.init(rv._002);
				_222.init(rv._092);
				_223.init(rv._070);
				_225.init(rv._002);
				_226.init(rv._092);
				_227.init(rv._076);
				_228.init(rv._096);
				_230.init(rv._041);
				_231.init(rv._092);
				_232.init(rv._076);
				_234.init(rv._092);
				_235.init(rv._092);
				_237.init(rv._092);
				_238.init(rv._092);
				_239.init(rv._075);
				_241.init(rv._075);
			}
		}

		private class _SerializationSegments
		{
			private final /*@NonNull*/ Segment [] _0 // []
				= new org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] {
					}
				;
			private final /*@NonNull*/ Segment [] _1 // [supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport, «value»]
				= new org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] {
					org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsUtils.createCustomSegment(null, org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport.class) /* supported by org.eclipse.ocl.xtext.base.cs2text.idioms.BaseCommentSegmentSupport */,
					org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsUtils.VALUE /* «value» */}
				;
			private final /*@NonNull*/ Segment [] _2 // [«! », «value», «! »]
				= new org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] {
					org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsUtils.NO_SPACE /* «! » */,
					org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsUtils.VALUE /* «value» */,
					org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsUtils.NO_SPACE /* «! » */}
				;
			private final /*@NonNull*/ Segment [] _3 // [«! », «value», «? »]
				= new org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] {
					org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsUtils.NO_SPACE /* «! » */,
					org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsUtils.VALUE /* «value» */,
					org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsUtils.SOFT_SPACE /* «? » */}
				;
			private final /*@NonNull*/ Segment [] _4 // [«! », «value», «?\n»]
				= new org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] {
					org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsUtils.NO_SPACE /* «! » */,
					org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsUtils.VALUE /* «value» */,
					org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsUtils.SOFT_NEW_LINE /* «?\n» */}
				;
			private final /*@NonNull*/ Segment [] _5 // [«! », «value»]
				= new org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] {
					org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsUtils.NO_SPACE /* «! » */,
					org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsUtils.VALUE /* «value» */}
				;
			private final /*@NonNull*/ Segment [] _6 // [«-», «? », «value», «?\n»]
				= new org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] {
					org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsUtils.POP /* «-» */,
					org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsUtils.SOFT_SPACE /* «? » */,
					org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsUtils.VALUE /* «value» */,
					org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsUtils.SOFT_NEW_LINE /* «?\n» */}
				;
			private final /*@NonNull*/ Segment [] _7 // [«? », «value», «+», «?\n»]
				= new org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] {
					org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsUtils.SOFT_SPACE /* «? » */,
					org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsUtils.VALUE /* «value» */,
					org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsUtils.PUSH /* «+» */,
					org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsUtils.SOFT_NEW_LINE /* «?\n» */}
				;
			private final /*@NonNull*/ Segment [] _8 // [«? », «value», «? »]
				= new org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] {
					org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsUtils.SOFT_SPACE /* «? » */,
					org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsUtils.VALUE /* «value» */,
					org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsUtils.SOFT_SPACE /* «? » */}
				;
		}

		private class _RuleValues
		{
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.TerminalRuleValue _000 // ANY_OTHER
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.TerminalRuleValue(0, "ANY_OTHER");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _001 // AnnotationCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(1, "AnnotationCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._118 /* { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] ';' } */,
				sr._117 /* { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedContents+=ModelElementCS[*] ownedReferences+=ModelElementRefCS[+] '}' } */,
				sr._119 /* { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedContents+=ModelElementCS[+] ownedReferences+=ModelElementRefCS[*] '}' } */,
				sr._120 /* { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] '{' ownedAnnotations+=AnnotationElementCS[+] ownedContents+=ModelElementCS[*] ownedReferences+=ModelElementRefCS[*] '}' } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _002 // AnnotationElementCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(2, "AnnotationElementCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._203 /* { 'sysml' ownedDetails+=DetailCS ';' } */,
				sr._201 /* { 'sysml' '{' { ownedDetails+=DetailCS ';' }[*] '}' } */,
				sr._118 /* { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] ';' } */,
				sr._140 /* { 'documentation' value=SINGLE_QUOTED_STRING[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] ';' } */,
				sr._117 /* { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedContents+=ModelElementCS[*] ownedReferences+=ModelElementRefCS[+] '}' } */,
				sr._119 /* { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedContents+=ModelElementCS[+] ownedReferences+=ModelElementRefCS[*] '}' } */,
				sr._120 /* { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] '{' ownedAnnotations+=AnnotationElementCS[+] ownedContents+=ModelElementCS[*] ownedReferences+=ModelElementRefCS[*] '}' } */
				},
						new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(new long[]{
				0x20006L,0x400000000L})); // AnnotationCS,AnnotationElementCS,DocumentationCS,SysMLCS
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _003 // AttributeCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(3, "AttributeCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._124 /* { 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
				sr._123 /* { qualifiers+='definition' qualifiers+='static'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
				sr._126 /* { qualifiers+='static' qualifiers+='definition'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
				sr._125 /* { 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' } */,
				sr._122 /* { qualifiers+='definition' qualifiers+='static'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' } */,
				sr._121 /* { qualifiers+='static' qualifiers+='definition'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.DataTypeRuleValue _004 // BinaryOperatorName
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.DataTypeRuleValue(4, "BinaryOperatorName");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _005 // BooleanLiteralExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(5, "BooleanLiteralExpCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._016 /* symbol={'false|true'} */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _006 // ClassCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(6, "ClassCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._145 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] ';' } */,
				sr._131 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] ';' } */,
				sr._146 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] ';' } */,
				sr._144 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] ';' } */,
				sr._137 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] ';' } */,
				sr._129 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] ';' } */,
				sr._130 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
				sr._152 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
				sr._136 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
				sr._138 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
				sr._142 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
				sr._150 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
				sr._198 /* { isAbstract='abstract'[?] 'class' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { 'extends' ownedSuperTypes+=TypedRefCS { ',' ownedSuperTypes+=TypedRefCS }[*] }[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isInterface='interface'[?] '}' }[?] ';' } */,
				sr._196 /* { isAbstract='abstract'[?] 'class' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { 'extends' ownedSuperTypes+=TypedRefCS { ',' ownedSuperTypes+=TypedRefCS }[*] }[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isInterface='interface'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedOperations+=OperationCS[*] ownedProperties+=StructuralFeatureCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */
				},
						new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(new long[]{
				0x208040L,0x200000000L})); // ClassCS,DataTypeCS,EnumerationCS,StructuredClassCS
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _007 // CoIteratorVariableCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(7, "CoIteratorVariableCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._017 /* { name=UnrestrictedName { ':' ownedType=TypeExpCS }[?] } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _008 // CollectionLiteralExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(8, "CollectionLiteralExpCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._018 /* { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _009 // CollectionLiteralPartCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(9, "CollectionLiteralPartCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._019 /* ownedExpression=PatternExpCS */,
				sr._020 /* { ownedExpression=ExpCS { '..' ownedLastExpression=ExpCS }[?] } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _010 // CollectionPatternCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(10, "CollectionPatternCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._021 /* { ownedType=CollectionTypeCS '{' { ownedParts+=PatternExpCS { ',' ownedParts+=PatternExpCS }[*] '++' restVariableName=Identifier }[?] '}' } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _011 // CollectionTypeCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(11, "CollectionTypeCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._022 /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.DataTypeRuleValue _012 // CollectionTypeIdentifier
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.DataTypeRuleValue(12, "CollectionTypeIdentifier");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _013 // CurlyBracketedClauseCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(13, "CurlyBracketedClauseCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._024 /* { '{' { ownedParts+=ShadowPartCS { ',' ownedParts+=ShadowPartCS }[*] }[?] '}' } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.TerminalRuleValue _014 // DOUBLE_QUOTED_STRING
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.TerminalRuleValue(14, "DOUBLE_QUOTED_STRING");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _015 // DataTypeCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(15, "DataTypeCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._132 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] ';' } */,
				sr._133 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] ';' } */,
				sr._134 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] ';' } */,
				sr._127 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
				sr._135 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
				sr._128 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _016 // DetailCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(16, "DetailCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._139 /* { name=(UnrestrictedName|SINGLE_QUOTED_STRING) '=' values+=(SINGLE_QUOTED_STRING|ML_SINGLE_QUOTED_STRING)[*] } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _017 // DocumentationCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(17, "DocumentationCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._141 /* { 'documentation' value=SINGLE_QUOTED_STRING[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] ';' } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.TerminalRuleValue _018 // ESCAPED_CHARACTER
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.TerminalRuleValue(18, "ESCAPED_CHARACTER");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.TerminalRuleValue _019 // ESCAPED_ID
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.TerminalRuleValue(19, "ESCAPED_ID");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _020 // ElseIfThenExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(20, "ElseIfThenExpCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._025 /* { 'elseif' ownedCondition=ExpCS 'then' ownedThenExpression=ExpCS } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _021 // EnumerationCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(21, "EnumerationCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._149 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] ';' } */,
				sr._151 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] ';' } */,
				sr._153 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] ';' } */,
				sr._148 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
				sr._147 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
				sr._143 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _022 // EnumerationLiteralCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(22, "EnumerationLiteralCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._156 /* { name=EnumerationLiteralName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] ';' } */,
				sr._155 /* { 'literal' name=UnrestrictedName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] ';' } */,
				sr._154 /* { name=EnumerationLiteralName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] '{' ownedAnnotations+=AnnotationElementCS[*] '}' } */,
				sr._157 /* { 'literal' name=UnrestrictedName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] '{' ownedAnnotations+=AnnotationElementCS[*] '}' } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.DataTypeRuleValue _023 // EnumerationLiteralName
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.DataTypeRuleValue(23, "EnumerationLiteralName");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.DataTypeRuleValue _024 // EssentialOCLInfixOperatorName
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.DataTypeRuleValue(24, "EssentialOCLInfixOperatorName");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.DataTypeRuleValue _025 // EssentialOCLNavigationOperatorName
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.DataTypeRuleValue(25, "EssentialOCLNavigationOperatorName");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.DataTypeRuleValue _026 // EssentialOCLReservedKeyword
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.DataTypeRuleValue(26, "EssentialOCLReservedKeyword");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.DataTypeRuleValue _027 // EssentialOCLUnaryOperatorName
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.DataTypeRuleValue(27, "EssentialOCLUnaryOperatorName");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.DataTypeRuleValue _028 // EssentialOCLUnreservedName
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.DataTypeRuleValue(28, "EssentialOCLUnreservedName");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.DataTypeRuleValue _029 // EssentialOCLUnrestrictedName
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.DataTypeRuleValue(29, "EssentialOCLUnrestrictedName");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _030 // ExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(30, "ExpCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._042 /* symbol={'false|true'} */,
				sr._030 /* '*' */,
				sr._027 /* 'invalid' */,
				sr._039 /* 'null' */,
				sr._041 /* 'self' */,
				sr._028 /* symbol=NUMBER_LITERAL */,
				sr._033 /* segments+=StringLiteral[+] */,
				sr._036 /* ownedType=TypeLiteralWithMultiplicityCS */,
				sr._029 /* { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS } */,
				sr._078 /* { name=UnaryOperatorName ownedRight=PrefixedLetExpCS } */,
				sr._031 /* { ownedLeft=PrefixedPrimaryExpCS name=BinaryOperatorName ownedRight=ExpCS } */,
				sr._037 /* { '(' ownedExpression=ExpCS ')' } */,
				sr._040 /* { 'Lambda' '{' ownedExpressionCS=ExpCS '}' } */,
				sr._035 /* { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' } */,
				sr._038 /* { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] } */,
				sr._032 /* { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' } */,
				sr._050 /* { 'let' ownedVariables+=LetVariableCS { ',' ownedVariables+=LetVariableCS }[*] 'in' ownedInExpression=ExpCS } */,
				sr._026 /* { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' } */,
				sr._034 /* { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' } */
				},
						new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(new long[]{
				0x802311040000120L,0x200080808201e034L})); // BooleanLiteralExpCS,CollectionLiteralExpCS,ExpCS,IfExpCS,InvalidLiteralExpCS,LambdaLiteralExpCS,LetExpCS,MapLiteralExpCS,NameExpCS,NestedExpCS,NullLiteralExpCS,NumberLiteralExpCS,PrefixedLetExpCS,PrefixedPrimaryExpCS,PrimaryExpCS,PrimitiveLiteralExpCS,SelfExpCS,StringLiteralExpCS,TupleLiteralExpCS,TypeLiteralExpCS,UnlimitedNaturalLiteralExpCS
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _031 // FirstPathElementCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(31, "FirstPathElementCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._000 /* referredElement=UnrestrictedName */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.DataTypeRuleValue _032 // ID
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.DataTypeRuleValue(32, "ID");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.TerminalRuleValue _033 // INT
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.TerminalRuleValue(33, "INT");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.DataTypeRuleValue _034 // INTEGER
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.DataTypeRuleValue(34, "INTEGER");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.DataTypeRuleValue _035 // Identifier
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.DataTypeRuleValue(35, "Identifier");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _036 // IfExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(36, "IfExpCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._044 /* { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _037 // ImplicitOppositeCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(37, "ImplicitOppositeCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._158 /* { 'opposite' name=UnrestrictedName ':' ownedType=TypedMultiplicityRefCS { '{' { qualifiers+={'!ordered|!unique|ordered|unique'} }[+] '}' }[?] } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _038 // ImportCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(38, "ImportCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._159 /* { {'import'|'library'} { name=UnrestrictedName ':' }[?] ownedPathName=URIPathNameCS isAll='::*'[?] ';' } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.DataTypeRuleValue _039 // InfixOperatorName
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.DataTypeRuleValue(39, "InfixOperatorName");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _040 // InvalidLiteralExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(40, "InvalidLiteralExpCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._046 /* 'invalid' */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _041 // InvariantConstraintCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(41, "InvariantConstraintCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._160 /* { isCallable='callable'[?] stereotype='invariant' { name=UnrestrictedName { '(' ownedMessageSpecification=SpecificationCS ')' }[?] }[?] ';' } */,
				sr._161 /* { isCallable='callable'[?] stereotype='invariant' { name=UnrestrictedName { '(' ownedMessageSpecification=SpecificationCS ')' }[?] }[?] ':' ownedSpecification=SpecificationCS[?] ';' } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.TerminalRuleValue _042 // LETTER_CHARACTER
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.TerminalRuleValue(42, "LETTER_CHARACTER");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.DataTypeRuleValue _043 // LOWER
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.DataTypeRuleValue(43, "LOWER");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _044 // LambdaLiteralExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(44, "LambdaLiteralExpCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._048 /* { 'Lambda' '{' ownedExpressionCS=ExpCS '}' } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _045 // LetExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(45, "LetExpCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._049 /* { 'let' ownedVariables+=LetVariableCS { ',' ownedVariables+=LetVariableCS }[*] 'in' ownedInExpression=ExpCS } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _046 // LetVariableCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(46, "LetVariableCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._051 /* { name=UnrestrictedName ownedRoundBracketedClause=RoundBracketedClauseCS[?] { ':' ownedType=TypeExpCS }[?] '=' ownedInitExpression=ExpCS } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.TerminalRuleValue _047 // ML_COMMENT
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.TerminalRuleValue(47, "ML_COMMENT");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.TerminalRuleValue _048 // ML_SINGLE_QUOTED_STRING
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.TerminalRuleValue(48, "ML_SINGLE_QUOTED_STRING");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _049 // MapLiteralExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(49, "MapLiteralExpCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._052 /* { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _050 // MapLiteralPartCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(50, "MapLiteralPartCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._054 /* { ownedKey=ExpCS '<-' ownedValue=ExpCS } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _051 // MapTypeCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(51, "MapTypeCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._056 /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _052 // Model
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(52, "Model",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._057 /* ownedExpression=ExpCS */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _053 // ModelElementCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(53, "ModelElementCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._156 /* { name=EnumerationLiteralName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] ';' } */,
				sr._155 /* { 'literal' name=UnrestrictedName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] ';' } */,
				sr._175 /* { 'package' name=UnrestrictedName { ':' nsPrefix=UnrestrictedName }[?] { '=' nsURI=URI }[?] ';' } */,
				sr._145 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] ';' } */,
				sr._154 /* { name=EnumerationLiteralName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] '{' ownedAnnotations+=AnnotationElementCS[*] '}' } */,
				sr._131 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] ';' } */,
				sr._157 /* { 'literal' name=UnrestrictedName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] '{' ownedAnnotations+=AnnotationElementCS[*] '}' } */,
				sr._146 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] ';' } */,
				sr._144 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] ';' } */,
				sr._137 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] ';' } */,
				sr._129 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] ';' } */,
				sr._178 /* { 'package' name=UnrestrictedName { ':' nsPrefix=UnrestrictedName }[?] { '=' nsURI=URI }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPackages+=PackageCS[*] ownedClasses+=ClassCS[*] '}' } */,
				sr._130 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
				sr._152 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
				sr._136 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
				sr._138 /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
				sr._142 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
				sr._150 /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
				sr._124 /* { 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
				sr._123 /* { qualifiers+='definition' qualifiers+='static'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
				sr._126 /* { qualifiers+='static' qualifiers+='definition'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
				sr._183 /* { 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
				sr._190 /* { qualifiers+='definition' qualifiers+='static'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
				sr._193 /* { qualifiers+='static' qualifiers+='definition'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
				sr._198 /* { isAbstract='abstract'[?] 'class' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { 'extends' ownedSuperTypes+=TypedRefCS { ',' ownedSuperTypes+=TypedRefCS }[*] }[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isInterface='interface'[?] '}' }[?] ';' } */,
				sr._196 /* { isAbstract='abstract'[?] 'class' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { 'extends' ownedSuperTypes+=TypedRefCS { ',' ownedSuperTypes+=TypedRefCS }[*] }[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isInterface='interface'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedOperations+=OperationCS[*] ownedProperties+=StructuralFeatureCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
				sr._125 /* { 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' } */,
				sr._122 /* { qualifiers+='definition' qualifiers+='static'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' } */,
				sr._121 /* { qualifiers+='static' qualifiers+='definition'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' } */,
				sr._171 /* { 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] ';' } */,
				sr._168 /* { qualifiers+='definition' qualifiers+='static'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] ';' } */,
				sr._166 /* { qualifiers+='static' qualifiers+='definition'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] ';' } */,
				sr._167 /* { 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { 'body' ':' ownedBodyExpressions+=SpecificationCS[?] ';' }[*] ownedPostconditions+=PostconditionConstraintCS[*] '}' } */,
				sr._170 /* { qualifiers+='definition' qualifiers+='static'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { 'body' ':' ownedBodyExpressions+=SpecificationCS[?] ';' }[*] ownedPostconditions+=PostconditionConstraintCS[*] '}' } */,
				sr._165 /* { qualifiers+='static' qualifiers+='definition'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { 'body' ':' ownedBodyExpressions+=SpecificationCS[?] ';' }[*] ownedPostconditions+=PostconditionConstraintCS[*] '}' } */,
				sr._184 /* { 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' } */,
				sr._182 /* { qualifiers+='definition' qualifiers+='static'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' } */,
				sr._186 /* { qualifiers+='static' qualifiers+='definition'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' } */
				},
						new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(new long[]{
				0x20000000608048L,0x3000800c0L})); // AttributeCS,ClassCS,DataTypeCS,EnumerationCS,EnumerationLiteralCS,ModelElementCS,OperationCS,PackageCS,ReferenceCS,StructuralFeatureCS,StructuredClassCS
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _054 // ModelElementRefCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(54, "ModelElementRefCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._162 /* { 'reference' ownedPathName=PathNameCS ';' } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _055 // MultiplicityBoundsCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(55, "MultiplicityBoundsCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._001 /* { lowerBound=LOWER { '..' upperBound=UPPER }[?] } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _056 // MultiplicityCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(56, "MultiplicityCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._005 /* { '[' stringBounds={'*|+|?'} ']' } */,
				sr._002 /* { '[' stringBounds={'*|+|?'} '|?' ']' } */,
				sr._003 /* { '[' stringBounds={'*|+|?'} isNullFree='|1'[?] ']' } */,
				sr._004 /* { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] ']' } */,
				sr._006 /* { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] '|?' ']' } */,
				sr._007 /* { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] isNullFree='|1'[?] ']' } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _057 // MultiplicityStringCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(57, "MultiplicityStringCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._008 /* stringBounds={'*|+|?'} */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.DataTypeRuleValue _058 // NUMBER_LITERAL
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.DataTypeRuleValue(58, "NUMBER_LITERAL");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _059 // NameExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(59, "NameExpCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._059 /* { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _060 // NavigatingArgCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(60, "NavigatingArgCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._061 /* ownedNameExpression=NavigatingArgExpCS */,
				sr._062 /* { ':' ownedType=TypeExpCS } */,
				sr._064 /* { ownedNameExpression=NavigatingArgExpCS '<-' ownedCoIterator=CoIteratorVariableCS { '=' ownedInitExpression=ExpCS }[?] } */,
				sr._063 /* { ownedNameExpression=NavigatingArgExpCS ':' ownedType=TypeExpCS { '<-' ownedCoIterator=CoIteratorVariableCS }[?] { '=' ownedInitExpression=ExpCS }[?] } */,
				sr._060 /* { ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS }[?] { '<-' ownedCoIterator=CoIteratorVariableCS }[?] 'in' ownedInitExpression=ExpCS } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _061 // NavigatingArgExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(61, "NavigatingArgExpCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._042 /* symbol={'false|true'} */,
				sr._030 /* '*' */,
				sr._027 /* 'invalid' */,
				sr._039 /* 'null' */,
				sr._041 /* 'self' */,
				sr._028 /* symbol=NUMBER_LITERAL */,
				sr._033 /* segments+=StringLiteral[+] */,
				sr._036 /* ownedType=TypeLiteralWithMultiplicityCS */,
				sr._029 /* { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS } */,
				sr._078 /* { name=UnaryOperatorName ownedRight=PrefixedLetExpCS } */,
				sr._031 /* { ownedLeft=PrefixedPrimaryExpCS name=BinaryOperatorName ownedRight=ExpCS } */,
				sr._037 /* { '(' ownedExpression=ExpCS ')' } */,
				sr._040 /* { 'Lambda' '{' ownedExpressionCS=ExpCS '}' } */,
				sr._035 /* { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' } */,
				sr._038 /* { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] } */,
				sr._032 /* { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' } */,
				sr._050 /* { 'let' ownedVariables+=LetVariableCS { ',' ownedVariables+=LetVariableCS }[*] 'in' ownedInExpression=ExpCS } */,
				sr._026 /* { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' } */,
				sr._034 /* { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' } */
				},
						new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(new long[]{
				0x2802311040000120L,0x200080808201e034L})); // BooleanLiteralExpCS,CollectionLiteralExpCS,ExpCS,IfExpCS,InvalidLiteralExpCS,LambdaLiteralExpCS,LetExpCS,MapLiteralExpCS,NameExpCS,NavigatingArgExpCS,NestedExpCS,NullLiteralExpCS,NumberLiteralExpCS,PrefixedLetExpCS,PrefixedPrimaryExpCS,PrimaryExpCS,PrimitiveLiteralExpCS,SelfExpCS,StringLiteralExpCS,TupleLiteralExpCS,TypeLiteralExpCS,UnlimitedNaturalLiteralExpCS
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _062 // NavigatingBarArgCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(62, "NavigatingBarArgCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._065 /* { prefix='|' ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS { '=' ownedInitExpression=ExpCS }[?] }[?] } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _063 // NavigatingCommaArgCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(63, "NavigatingCommaArgCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._066 /* { prefix=',' ownedNameExpression=NavigatingArgExpCS } */,
				sr._069 /* { prefix=',' ownedNameExpression=NavigatingArgExpCS '<-' ownedCoIterator=CoIteratorVariableCS { '=' ownedInitExpression=ExpCS }[?] } */,
				sr._067 /* { prefix=',' ownedNameExpression=NavigatingArgExpCS ':' ownedType=TypeExpCS { '<-' ownedCoIterator=CoIteratorVariableCS }[?] { '=' ownedInitExpression=ExpCS }[?] } */,
				sr._068 /* { prefix=',' ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS }[?] { '<-' ownedCoIterator=CoIteratorVariableCS }[?] 'in' ownedInitExpression=ExpCS } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _064 // NavigatingSemiArgCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(64, "NavigatingSemiArgCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._070 /* { prefix=';' ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS { '=' ownedInitExpression=ExpCS }[?] }[?] } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.DataTypeRuleValue _065 // NavigationOperatorName
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.DataTypeRuleValue(65, "NavigationOperatorName");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _066 // NestedExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(66, "NestedExpCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._071 /* { '(' ownedExpression=ExpCS ')' } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _067 // NextPathElementCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(67, "NextPathElementCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._009 /* referredElement=UnreservedName */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _068 // NullLiteralExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(68, "NullLiteralExpCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._074 /* 'null' */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _069 // NumberLiteralExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(69, "NumberLiteralExpCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._075 /* symbol=NUMBER_LITERAL */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _070 // OperationCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(70, "OperationCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._174 /* { 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] ';' } */,
				sr._173 /* { qualifiers+='definition' qualifiers+='static'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] ';' } */,
				sr._172 /* { qualifiers+='static' qualifiers+='definition'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] ';' } */,
				sr._164 /* { 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { 'body' ':' ownedBodyExpressions+=SpecificationCS[?] ';' }[*] ownedPostconditions+=PostconditionConstraintCS[*] '}' } */,
				sr._169 /* { qualifiers+='definition' qualifiers+='static'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { 'body' ':' ownedBodyExpressions+=SpecificationCS[?] ';' }[*] ownedPostconditions+=PostconditionConstraintCS[*] '}' } */,
				sr._163 /* { qualifiers+='static' qualifiers+='definition'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { 'body' ':' ownedBodyExpressions+=SpecificationCS[?] ';' }[*] ownedPostconditions+=PostconditionConstraintCS[*] '}' } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _071 // PackageCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(71, "PackageCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._177 /* { 'package' name=UnrestrictedName { ':' nsPrefix=UnrestrictedName }[?] { '=' nsURI=URI }[?] ';' } */,
				sr._176 /* { 'package' name=UnrestrictedName { ':' nsPrefix=UnrestrictedName }[?] { '=' nsURI=URI }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPackages+=PackageCS[*] ownedClasses+=ClassCS[*] '}' } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _072 // ParameterCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(72, "ParameterCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._179 /* { name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '{' { qualifiers+={'!ordered|!unique|ordered|unique'} }[+] '}' }[?] { '{' ownedAnnotations+=AnnotationElementCS[*] '}' }[?] } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _073 // PathNameCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(73, "PathNameCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._010 /* { ownedPathElements+=FirstPathElementCS { '::' ownedPathElements+=NextPathElementCS }[*] } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _074 // PatternExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(74, "PatternExpCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._077 /* { patternVariableName=UnrestrictedName[?] ':' ownedPatternType=TypeExpCS } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _075 // PostconditionConstraintCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(75, "PostconditionConstraintCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._180 /* { stereotype='postcondition' { name=UnrestrictedName { '(' ownedMessageSpecification=SpecificationCS ')' }[?] }[?] ':' ownedSpecification=SpecificationCS[?] ';' } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _076 // PreconditionConstraintCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(76, "PreconditionConstraintCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._181 /* { stereotype='precondition' { name=UnrestrictedName { '(' ownedMessageSpecification=SpecificationCS ')' }[?] }[?] ':' ownedSpecification=SpecificationCS[?] ';' } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _077 // PrefixedLetExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(77, "PrefixedLetExpCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._079 /* { name=UnaryOperatorName ownedRight=PrefixedLetExpCS } */,
				sr._049 /* { 'let' ownedVariables+=LetVariableCS { ',' ownedVariables+=LetVariableCS }[*] 'in' ownedInExpression=ExpCS } */
				},
						new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(new long[]{
				0x200000000000L,0x2000L})); // LetExpCS,PrefixedLetExpCS
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _078 // PrefixedPrimaryExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(78, "PrefixedPrimaryExpCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._016 /* symbol={'false|true'} */,
				sr._045 /* 'invalid' */,
				sr._073 /* 'null' */,
				sr._076 /* symbol=NUMBER_LITERAL */,
				sr._085 /* 'self' */,
				sr._091 /* segments+=StringLiteral[+] */,
				sr._104 /* ownedType=TypeLiteralWithMultiplicityCS */,
				sr._116 /* '*' */,
				sr._080 /* { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS } */,
				sr._072 /* { '(' ownedExpression=ExpCS ')' } */,
				sr._047 /* { 'Lambda' '{' ownedExpressionCS=ExpCS '}' } */,
				sr._043 /* { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' } */,
				sr._058 /* { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] } */,
				sr._093 /* { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' } */,
				sr._018 /* { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' } */,
				sr._053 /* { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' } */
				},
						new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(new long[]{
				0x802111000000120L,0x200080808201c034L})); // BooleanLiteralExpCS,CollectionLiteralExpCS,IfExpCS,InvalidLiteralExpCS,LambdaLiteralExpCS,MapLiteralExpCS,NameExpCS,NestedExpCS,NullLiteralExpCS,NumberLiteralExpCS,PrefixedPrimaryExpCS,PrimaryExpCS,PrimitiveLiteralExpCS,SelfExpCS,StringLiteralExpCS,TupleLiteralExpCS,TypeLiteralExpCS,UnlimitedNaturalLiteralExpCS
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _079 // PrimaryExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(79, "PrimaryExpCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._016 /* symbol={'false|true'} */,
				sr._045 /* 'invalid' */,
				sr._073 /* 'null' */,
				sr._076 /* symbol=NUMBER_LITERAL */,
				sr._085 /* 'self' */,
				sr._091 /* segments+=StringLiteral[+] */,
				sr._104 /* ownedType=TypeLiteralWithMultiplicityCS */,
				sr._116 /* '*' */,
				sr._071 /* { '(' ownedExpression=ExpCS ')' } */,
				sr._048 /* { 'Lambda' '{' ownedExpressionCS=ExpCS '}' } */,
				sr._044 /* { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' } */,
				sr._059 /* { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] } */,
				sr._093 /* { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' } */,
				sr._018 /* { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' } */,
				sr._052 /* { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' } */
				},
						new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(new long[]{
				0x802111000000120L,0x2000808082018034L})); // BooleanLiteralExpCS,CollectionLiteralExpCS,IfExpCS,InvalidLiteralExpCS,LambdaLiteralExpCS,MapLiteralExpCS,NameExpCS,NestedExpCS,NullLiteralExpCS,NumberLiteralExpCS,PrimaryExpCS,PrimitiveLiteralExpCS,SelfExpCS,StringLiteralExpCS,TupleLiteralExpCS,TypeLiteralExpCS,UnlimitedNaturalLiteralExpCS
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _080 // PrimitiveLiteralExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(80, "PrimitiveLiteralExpCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._016 /* symbol={'false|true'} */,
				sr._046 /* 'invalid' */,
				sr._074 /* 'null' */,
				sr._075 /* symbol=NUMBER_LITERAL */,
				sr._091 /* segments+=StringLiteral[+] */,
				sr._116 /* '*' */
				},
						new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(new long[]{
				0x10000000020L,0x2000000080010030L})); // BooleanLiteralExpCS,InvalidLiteralExpCS,NullLiteralExpCS,NumberLiteralExpCS,PrimitiveLiteralExpCS,StringLiteralExpCS,UnlimitedNaturalLiteralExpCS
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _081 // PrimitiveTypeCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(81, "PrimitiveTypeCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._082 /* name=PrimitiveTypeIdentifier */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.DataTypeRuleValue _082 // PrimitiveTypeIdentifier
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.DataTypeRuleValue(82, "PrimitiveTypeIdentifier");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _083 // ReferenceCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(83, "ReferenceCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._187 /* { 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
				sr._192 /* { qualifiers+='definition' qualifiers+='static'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
				sr._189 /* { qualifiers+='static' qualifiers+='definition'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
				sr._188 /* { 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' } */,
				sr._191 /* { qualifiers+='definition' qualifiers+='static'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' } */,
				sr._185 /* { qualifiers+='static' qualifiers+='definition'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _084 // RoundBracketedClauseCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(84, "RoundBracketedClauseCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._083 /* { '(' { ownedArguments+=NavigatingArgCS ownedArguments+=(NavigatingCommaArgCS|NavigatingSemiArgCS|NavigatingBarArgCS)[*] }[?] ')' } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.DataTypeRuleValue _085 // SIGNED
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.DataTypeRuleValue(85, "SIGNED");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.TerminalRuleValue _086 // SIMPLE_ID
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.TerminalRuleValue(86, "SIMPLE_ID");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.TerminalRuleValue _087 // SINGLE_QUOTED_STRING
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.TerminalRuleValue(87, "SINGLE_QUOTED_STRING");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.TerminalRuleValue _088 // SL_COMMENT
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.TerminalRuleValue(88, "SL_COMMENT");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _089 // SelfExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(89, "SelfExpCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._084 /* 'self' */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _090 // ShadowPartCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(90, "ShadowPartCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._086 /* ownedInitExpression=StringLiteralExpCS */,
				sr._087 /* { referredProperty=UnrestrictedName '=' ownedInitExpression=(ExpCS|PatternExpCS) } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _091 // SimplePathNameCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(91, "SimplePathNameCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._088 /* ownedPathElements+=FirstPathElementCS */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _092 // SpecificationCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(92, "SpecificationCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._194 /* exprString=UNQUOTED_STRING */,
				sr._195 /* ownedExpression=ExpCS */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _093 // SquareBracketedClauseCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(93, "SquareBracketedClauseCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._089 /* { '[' ownedTerms+=ExpCS { ',' ownedTerms+=ExpCS }[*] ']' } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.DataTypeRuleValue _094 // StringLiteral
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.DataTypeRuleValue(94, "StringLiteral");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _095 // StringLiteralExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(95, "StringLiteralExpCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._090 /* segments+=StringLiteral[+] */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _096 // StructuralFeatureCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(96, "StructuralFeatureCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._124 /* { 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
				sr._123 /* { qualifiers+='definition' qualifiers+='static'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
				sr._126 /* { qualifiers+='static' qualifiers+='definition'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
				sr._187 /* { 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
				sr._192 /* { qualifiers+='definition' qualifiers+='static'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
				sr._189 /* { qualifiers+='static' qualifiers+='definition'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
				sr._125 /* { 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' } */,
				sr._122 /* { qualifiers+='definition' qualifiers+='static'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' } */,
				sr._121 /* { qualifiers+='static' qualifiers+='definition'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' } */,
				sr._188 /* { 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' } */,
				sr._191 /* { qualifiers+='definition' qualifiers+='static'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' } */,
				sr._185 /* { qualifiers+='static' qualifiers+='definition'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' } */
				},
						new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(new long[]{
				0x8L,0x100080000L})); // AttributeCS,ReferenceCS,StructuralFeatureCS
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _097 // StructuredClassCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(97, "StructuredClassCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._197 /* { isAbstract='abstract'[?] 'class' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { 'extends' ownedSuperTypes+=TypedRefCS { ',' ownedSuperTypes+=TypedRefCS }[*] }[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isInterface='interface'[?] '}' }[?] ';' } */,
				sr._199 /* { isAbstract='abstract'[?] 'class' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { 'extends' ownedSuperTypes+=TypedRefCS { ',' ownedSuperTypes+=TypedRefCS }[*] }[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isInterface='interface'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedOperations+=OperationCS[*] ownedProperties+=StructuralFeatureCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _098 // SysMLCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(98, "SysMLCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._200 /* { 'sysml' ownedDetails+=DetailCS ';' } */,
				sr._202 /* { 'sysml' '{' { ownedDetails+=DetailCS ';' }[*] '}' } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _099 // TemplateBindingCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(99, "TemplateBindingCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._011 /* { ownedSubstitutions+=TemplateParameterSubstitutionCS { ',' ownedSubstitutions+=TemplateParameterSubstitutionCS }[*] ownedMultiplicity=MultiplicityCS[?] } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _100 // TemplateParameterSubstitutionCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(100, "TemplateParameterSubstitutionCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._012 /* ownedActualParameter=TypeRefCS */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _101 // TemplateSignatureCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(101, "TemplateSignatureCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._204 /* { '(' ownedParameters+=TypeParameterCS { ',' ownedParameters+=TypeParameterCS }[*] ')' } */,
				sr._205 /* { '<' ownedParameters+=TypeParameterCS { ',' ownedParameters+=TypeParameterCS }[*] '>' } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _102 // TopLevelCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(102, "TopLevelCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._206 /* { { 'module' }[?] ownedImports+=ImportCS[*] ownedPackages+=PackageCS[*] } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _103 // TupleLiteralExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(103, "TupleLiteralExpCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._092 /* { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _104 // TupleLiteralPartCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(104, "TupleLiteralPartCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._094 /* { name=UnrestrictedName { ':' ownedType=TypeExpCS }[?] '=' ownedInitExpression=ExpCS } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _105 // TuplePartCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(105, "TuplePartCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._095 /* { name=UnrestrictedName ':' ownedType=TypeExpCS } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _106 // TupleTypeCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(106, "TupleTypeCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._096 /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _107 // TypeExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(107, "TypeExpCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._099 /* { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] } */,
				sr._102 /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
				sr._098 /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
				sr._100 /* { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { '{' ownedPatternGuard=ExpCS '}' }[?] }[?] ownedMultiplicity=MultiplicityCS[?] } */,
				sr._103 /* { ownedType=CollectionTypeCS '{' { ownedParts+=PatternExpCS { ',' ownedParts+=PatternExpCS }[*] '++' restVariableName=Identifier }[?] '}' ownedMultiplicity=MultiplicityCS[?] } */,
				sr._101 /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */
				},
						new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(new long[]{
				0x8000000000c00L,0x25c0000020000L})); // CollectionPatternCS,CollectionTypeCS,MapTypeCS,PrimitiveTypeCS,TupleTypeCS,TypeExpCS,TypeExpWithoutMultiplicityCS,TypeLiteralCS,TypeNameExpCS
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _108 // TypeExpWithoutMultiplicityCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(108, "TypeExpWithoutMultiplicityCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._081 /* name=PrimitiveTypeIdentifier */,
				sr._023 /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] } */,
				sr._055 /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] } */,
				sr._111 /* { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { '{' ownedPatternGuard=ExpCS '}' }[?] }[?] } */,
				sr._021 /* { ownedType=CollectionTypeCS '{' { ownedParts+=PatternExpCS { ',' ownedParts+=PatternExpCS }[*] '++' restVariableName=Identifier }[?] '}' } */,
				sr._097 /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] } */
				},
						new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(new long[]{
				0x8000000000c00L,0x2540000020000L})); // CollectionPatternCS,CollectionTypeCS,MapTypeCS,PrimitiveTypeCS,TupleTypeCS,TypeExpWithoutMultiplicityCS,TypeLiteralCS,TypeNameExpCS
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.DataTypeRuleValue _109 // TypeIdentifier
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.DataTypeRuleValue(109, "TypeIdentifier");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _110 // TypeLiteralCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(110, "TypeLiteralCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._082 /* name=PrimitiveTypeIdentifier */,
				sr._022 /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] } */,
				sr._056 /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] } */,
				sr._096 /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] } */
				},
						new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(new long[]{
				0x8000000000800L,0x440000020000L})); // CollectionTypeCS,MapTypeCS,PrimitiveTypeCS,TupleTypeCS,TypeLiteralCS
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _111 // TypeLiteralExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(111, "TypeLiteralExpCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._105 /* ownedType=TypeLiteralWithMultiplicityCS */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _112 // TypeLiteralWithMultiplicityCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(112, "TypeLiteralWithMultiplicityCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._109 /* { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] } */,
				sr._108 /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
				sr._107 /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
				sr._106 /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */
				},
						new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(new long[]{
				0x8000000000800L,0x1440000020000L})); // CollectionTypeCS,MapTypeCS,PrimitiveTypeCS,TupleTypeCS,TypeLiteralCS,TypeLiteralWithMultiplicityCS
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _113 // TypeNameExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(113, "TypeNameExpCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._110 /* { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { '{' ownedPatternGuard=ExpCS '}' }[?] }[?] } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _114 // TypeParameterCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(114, "TypeParameterCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._013 /* { name=UnrestrictedName { 'extends' ownedExtends+=TypedRefCS { '&&' ownedExtends+=TypedRefCS }[*] }[?] } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _115 // TypeRefCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(115, "TypeRefCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._081 /* name=PrimitiveTypeIdentifier */,
				sr._218 /* ownedPathName=PathNameCS */,
				sr._219 /* { ownedPathName=PathNameCS '(' ownedBinding=TemplateBindingCS ')' } */,
				sr._216 /* { ownedPathName=PathNameCS '<' ownedBinding=TemplateBindingCS '>' } */,
				sr._014 /* { '?' { 'extends' ownedExtends=TypedRefCS }[?] } */,
				sr._023 /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] } */,
				sr._055 /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] } */,
				sr._097 /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] } */
				},
						new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(new long[]{
				0x8000000000800L,0x68440000020000L,0x2L})); // CollectionTypeCS,MapTypeCS,PrimitiveTypeCS,TupleTypeCS,TypeLiteralCS,TypeRefCS,TypedRefCS,TypedTypeRefCS,WildcardTypeRefCS
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _116 // TypedMultiplicityRefCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(116, "TypedMultiplicityRefCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._209 /* { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] } */,
				sr._213 /* { ownedPathName=PathNameCS ownedMultiplicity=MultiplicityCS[?] } */,
				sr._211 /* { ownedPathName=PathNameCS '(' ownedBinding=TemplateBindingCS ')' ownedMultiplicity=MultiplicityCS[?] } */,
				sr._212 /* { ownedPathName=PathNameCS '<' ownedBinding=TemplateBindingCS '>' ownedMultiplicity=MultiplicityCS[?] } */,
				sr._207 /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
				sr._210 /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
				sr._208 /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */
				},
						new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(new long[]{
				0x8000000000800L,0x70440000020000L})); // CollectionTypeCS,MapTypeCS,PrimitiveTypeCS,TupleTypeCS,TypeLiteralCS,TypedMultiplicityRefCS,TypedRefCS,TypedTypeRefCS
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _117 // TypedRefCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(117, "TypedRefCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._082 /* name=PrimitiveTypeIdentifier */,
				sr._218 /* ownedPathName=PathNameCS */,
				sr._219 /* { ownedPathName=PathNameCS '(' ownedBinding=TemplateBindingCS ')' } */,
				sr._216 /* { ownedPathName=PathNameCS '<' ownedBinding=TemplateBindingCS '>' } */,
				sr._022 /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] } */,
				sr._056 /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] } */,
				sr._096 /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] } */
				},
						new org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector(new long[]{
				0x8000000000800L,0x60440000020000L})); // CollectionTypeCS,MapTypeCS,PrimitiveTypeCS,TupleTypeCS,TypeLiteralCS,TypedRefCS,TypedTypeRefCS
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _118 // TypedTypeRefCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(118, "TypedTypeRefCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._214 /* ownedPathName=PathNameCS */,
				sr._217 /* { ownedPathName=PathNameCS '(' ownedBinding=TemplateBindingCS ')' } */,
				sr._215 /* { ownedPathName=PathNameCS '<' ownedBinding=TemplateBindingCS '>' } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.TerminalRuleValue _119 // UNQUOTED_STRING
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.TerminalRuleValue(119, "UNQUOTED_STRING");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.DataTypeRuleValue _120 // UPPER
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.DataTypeRuleValue(120, "UPPER");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.DataTypeRuleValue _121 // URI
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.DataTypeRuleValue(121, "URI");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _122 // URIFirstPathElementCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(122, "URIFirstPathElementCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._113 /* referredElement=UnrestrictedName */,
				sr._112 /* referredElement=URI */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _123 // URIPathNameCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(123, "URIPathNameCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._114 /* { ownedPathElements+=URIFirstPathElementCS { '::' ownedPathElements+=NextPathElementCS }[*] } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.DataTypeRuleValue _124 // UnaryOperatorName
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.DataTypeRuleValue(124, "UnaryOperatorName");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _125 // UnlimitedNaturalLiteralExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(125, "UnlimitedNaturalLiteralExpCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._115 /* '*' */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.DataTypeRuleValue _126 // UnreservedName
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.DataTypeRuleValue(126, "UnreservedName");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.DataTypeRuleValue _127 // UnrestrictedName
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.DataTypeRuleValue(127, "UnrestrictedName");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.TerminalRuleValue _128 // WS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.TerminalRuleValue(128, "WS");
			private final /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue _129 // WildcardTypeRefCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue(129, "WildcardTypeRefCS",
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._015 /* { '?' { 'extends' ownedExtends=TypedRefCS }[?] } */
				}, (org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector)null);
		}

		private class _EClassData
		{
			private final /*@NonNull*/ EClassData _00 // AnnotationCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData("AnnotationCS", org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ANNOTATION_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._118 /* OCLinEcore::AnnotationCS(basecs::AnnotationCS): { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] ';' } */,
				sr._117 /* OCLinEcore::AnnotationCS(basecs::AnnotationCS): { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedContents+=ModelElementCS[*] ownedReferences+=ModelElementRefCS[+] '}' } */,
				sr._119 /* OCLinEcore::AnnotationCS(basecs::AnnotationCS): { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedContents+=ModelElementCS[+] ownedReferences+=ModelElementRefCS[*] '}' } */,
				sr._120 /* OCLinEcore::AnnotationCS(basecs::AnnotationCS): { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] '{' ownedAnnotations+=AnnotationElementCS[+] ownedContents+=ModelElementCS[*] ownedReferences+=ModelElementRefCS[*] '}' } */,
				sr._118 /* OCLinEcore::AnnotationCS(basecs::AnnotationCS): { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] ';' } */,
				sr._117 /* OCLinEcore::AnnotationCS(basecs::AnnotationCS): { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedContents+=ModelElementCS[*] ownedReferences+=ModelElementRefCS[+] '}' } */,
				sr._119 /* OCLinEcore::AnnotationCS(basecs::AnnotationCS): { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedContents+=ModelElementCS[+] ownedReferences+=ModelElementRefCS[*] '}' } */,
				sr._120 /* OCLinEcore::AnnotationCS(basecs::AnnotationCS): { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] '{' ownedAnnotations+=AnnotationElementCS[+] ownedContents+=ModelElementCS[*] ownedReferences+=ModelElementRefCS[*] '}' } */
				}, null);
			private final /*@NonNull*/ EClassData _01 // AttributeCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData("AttributeCS", org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ATTRIBUTE_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._124 /* OCLinEcore::AttributeCS(basecs::AttributeCS): { 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
				sr._123 /* OCLinEcore::AttributeCS(basecs::AttributeCS): { qualifiers+='definition' qualifiers+='static'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
				sr._126 /* OCLinEcore::AttributeCS(basecs::AttributeCS): { qualifiers+='static' qualifiers+='definition'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
				sr._125 /* OCLinEcore::AttributeCS(basecs::AttributeCS): { 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' } */,
				sr._122 /* OCLinEcore::AttributeCS(basecs::AttributeCS): { qualifiers+='definition' qualifiers+='static'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' } */,
				sr._121 /* OCLinEcore::AttributeCS(basecs::AttributeCS): { qualifiers+='static' qualifiers+='definition'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' } */,
				sr._124 /* OCLinEcore::AttributeCS(basecs::AttributeCS): { 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
				sr._123 /* OCLinEcore::AttributeCS(basecs::AttributeCS): { qualifiers+='definition' qualifiers+='static'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
				sr._126 /* OCLinEcore::AttributeCS(basecs::AttributeCS): { qualifiers+='static' qualifiers+='definition'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
				sr._125 /* OCLinEcore::AttributeCS(basecs::AttributeCS): { 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' } */,
				sr._122 /* OCLinEcore::AttributeCS(basecs::AttributeCS): { qualifiers+='definition' qualifiers+='static'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' } */,
				sr._121 /* OCLinEcore::AttributeCS(basecs::AttributeCS): { qualifiers+='static' qualifiers+='definition'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' } */,
				sr._124 /* OCLinEcore::AttributeCS(basecs::AttributeCS): { 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
				sr._123 /* OCLinEcore::AttributeCS(basecs::AttributeCS): { qualifiers+='definition' qualifiers+='static'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
				sr._126 /* OCLinEcore::AttributeCS(basecs::AttributeCS): { qualifiers+='static' qualifiers+='definition'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
				sr._125 /* OCLinEcore::AttributeCS(basecs::AttributeCS): { 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' } */,
				sr._122 /* OCLinEcore::AttributeCS(basecs::AttributeCS): { qualifiers+='definition' qualifiers+='static'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' } */,
				sr._121 /* OCLinEcore::AttributeCS(basecs::AttributeCS): { qualifiers+='static' qualifiers+='definition'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' } */
				}, null);
			private final /*@NonNull*/ EClassData _02 // BooleanLiteralExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData("BooleanLiteralExpCS", org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.BOOLEAN_LITERAL_EXP_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._016 /* EssentialOCL::BooleanLiteralExpCS(essentialoclcs::BooleanLiteralExpCS): symbol={'false|true'} */,
				sr._042 /* EssentialOCL::ExpCS(essentialoclcs::BooleanLiteralExpCS): symbol={'false|true'} */,
				sr._042 /* EssentialOCL::ExpCS(essentialoclcs::BooleanLiteralExpCS): symbol={'false|true'} */,
				sr._016 /* EssentialOCL::BooleanLiteralExpCS(essentialoclcs::BooleanLiteralExpCS): symbol={'false|true'} */,
				sr._016 /* EssentialOCL::BooleanLiteralExpCS(essentialoclcs::BooleanLiteralExpCS): symbol={'false|true'} */,
				sr._016 /* EssentialOCL::BooleanLiteralExpCS(essentialoclcs::BooleanLiteralExpCS): symbol={'false|true'} */
				}, null);
			private final /*@NonNull*/ EClassData _03 // CollectionLiteralExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData("CollectionLiteralExpCS", org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_EXP_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._018 /* EssentialOCL::CollectionLiteralExpCS(essentialoclcs::CollectionLiteralExpCS): { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' } */,
				sr._026 /* EssentialOCL::ExpCS(essentialoclcs::CollectionLiteralExpCS): { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' } */,
				sr._026 /* EssentialOCL::ExpCS(essentialoclcs::CollectionLiteralExpCS): { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' } */,
				sr._018 /* EssentialOCL::CollectionLiteralExpCS(essentialoclcs::CollectionLiteralExpCS): { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' } */,
				sr._018 /* EssentialOCL::CollectionLiteralExpCS(essentialoclcs::CollectionLiteralExpCS): { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' } */
				}, null);
			private final /*@NonNull*/ EClassData _04 // CollectionLiteralPartCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData("CollectionLiteralPartCS", org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_LITERAL_PART_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._019 /* EssentialOCL::CollectionLiteralPartCS(essentialoclcs::CollectionLiteralPartCS): ownedExpression=PatternExpCS */,
				sr._020 /* EssentialOCL::CollectionLiteralPartCS(essentialoclcs::CollectionLiteralPartCS): { ownedExpression=ExpCS { '..' ownedLastExpression=ExpCS }[?] } */
				}, null);
			private final /*@NonNull*/ EClassData _05 // CollectionPatternCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData("CollectionPatternCS", org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_PATTERN_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._021 /* EssentialOCL::CollectionPatternCS(essentialoclcs::CollectionPatternCS): { ownedType=CollectionTypeCS '{' { ownedParts+=PatternExpCS { ',' ownedParts+=PatternExpCS }[*] '++' restVariableName=Identifier }[?] '}' } */,
				sr._103 /* EssentialOCL::TypeExpCS(essentialoclcs::CollectionPatternCS): { ownedType=CollectionTypeCS '{' { ownedParts+=PatternExpCS { ',' ownedParts+=PatternExpCS }[*] '++' restVariableName=Identifier }[?] '}' ownedMultiplicity=MultiplicityCS[?] } */,
				sr._021 /* EssentialOCL::CollectionPatternCS(essentialoclcs::CollectionPatternCS): { ownedType=CollectionTypeCS '{' { ownedParts+=PatternExpCS { ',' ownedParts+=PatternExpCS }[*] '++' restVariableName=Identifier }[?] '}' } */
				}, null);
			private final /*@NonNull*/ EClassData _06 // CollectionTypeCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData("CollectionTypeCS", org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.COLLECTION_TYPE_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._023 /* EssentialOCL::CollectionTypeCS(essentialoclcs::CollectionTypeCS): { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] } */,
				sr._022 /* EssentialOCL::CollectionTypeCS(essentialoclcs::CollectionTypeCS): { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] } */,
				sr._102 /* EssentialOCL::TypeExpCS(essentialoclcs::CollectionTypeCS): { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
				sr._023 /* EssentialOCL::CollectionTypeCS(essentialoclcs::CollectionTypeCS): { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] } */,
				sr._022 /* EssentialOCL::CollectionTypeCS(essentialoclcs::CollectionTypeCS): { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] } */,
				sr._108 /* EssentialOCL::TypeLiteralWithMultiplicityCS(essentialoclcs::CollectionTypeCS): { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
				sr._207 /* OCLinEcore::TypedMultiplicityRefCS(essentialoclcs::CollectionTypeCS): { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
				sr._022 /* EssentialOCL::CollectionTypeCS(essentialoclcs::CollectionTypeCS): { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] } */
				}, null);
			private final /*@NonNull*/ EClassData _07 // ContextCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData("ContextCS", org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.CONTEXT_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._057 /* EssentialOCL::Model(essentialoclcs::ContextCS): ownedExpression=ExpCS */
				}, null);
			private final /*@NonNull*/ EClassData _08 // CurlyBracketedClauseCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData("CurlyBracketedClauseCS", org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.CURLY_BRACKETED_CLAUSE_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._024 /* EssentialOCL::CurlyBracketedClauseCS(essentialoclcs::CurlyBracketedClauseCS): { '{' { ownedParts+=ShadowPartCS { ',' ownedParts+=ShadowPartCS }[*] }[?] '}' } */
				}, null);
			private final /*@NonNull*/ EClassData _09 // DataTypeCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData("DataTypeCS", org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.DATA_TYPE_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._131 /* OCLinEcore::DataTypeCS(basecs::DataTypeCS): { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] ';' } */,
				sr._137 /* OCLinEcore::DataTypeCS(basecs::DataTypeCS): { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] ';' } */,
				sr._129 /* OCLinEcore::DataTypeCS(basecs::DataTypeCS): { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] ';' } */,
				sr._130 /* OCLinEcore::DataTypeCS(basecs::DataTypeCS): { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
				sr._136 /* OCLinEcore::DataTypeCS(basecs::DataTypeCS): { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
				sr._138 /* OCLinEcore::DataTypeCS(basecs::DataTypeCS): { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
				sr._132 /* OCLinEcore::DataTypeCS(basecs::DataTypeCS): { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] ';' } */,
				sr._133 /* OCLinEcore::DataTypeCS(basecs::DataTypeCS): { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] ';' } */,
				sr._134 /* OCLinEcore::DataTypeCS(basecs::DataTypeCS): { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] ';' } */,
				sr._127 /* OCLinEcore::DataTypeCS(basecs::DataTypeCS): { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
				sr._135 /* OCLinEcore::DataTypeCS(basecs::DataTypeCS): { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
				sr._128 /* OCLinEcore::DataTypeCS(basecs::DataTypeCS): { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
				sr._131 /* OCLinEcore::DataTypeCS(basecs::DataTypeCS): { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] ';' } */,
				sr._137 /* OCLinEcore::DataTypeCS(basecs::DataTypeCS): { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] ';' } */,
				sr._129 /* OCLinEcore::DataTypeCS(basecs::DataTypeCS): { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] ';' } */,
				sr._130 /* OCLinEcore::DataTypeCS(basecs::DataTypeCS): { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
				sr._136 /* OCLinEcore::DataTypeCS(basecs::DataTypeCS): { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
				sr._138 /* OCLinEcore::DataTypeCS(basecs::DataTypeCS): { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */
				}, null);
			private final /*@NonNull*/ EClassData _10 // DetailCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData("DetailCS", org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.DETAIL_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._139 /* OCLinEcore::DetailCS(basecs::DetailCS): { name=(UnrestrictedName|SINGLE_QUOTED_STRING) '=' values+=(SINGLE_QUOTED_STRING|ML_SINGLE_QUOTED_STRING)[*] } */
				}, null);
			private final /*@NonNull*/ EClassData _11 // DocumentationCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData("DocumentationCS", org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.DOCUMENTATION_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._140 /* OCLinEcore::DocumentationCS(basecs::DocumentationCS): { 'documentation' value=SINGLE_QUOTED_STRING[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] ';' } */,
				sr._141 /* OCLinEcore::DocumentationCS(basecs::DocumentationCS): { 'documentation' value=SINGLE_QUOTED_STRING[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] ';' } */
				}, null);
			private final /*@NonNull*/ EClassData _12 // EnumerationCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData("EnumerationCS", org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ENUMERATION_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._145 /* OCLinEcore::EnumerationCS(basecs::EnumerationCS): { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] ';' } */,
				sr._146 /* OCLinEcore::EnumerationCS(basecs::EnumerationCS): { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] ';' } */,
				sr._144 /* OCLinEcore::EnumerationCS(basecs::EnumerationCS): { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] ';' } */,
				sr._152 /* OCLinEcore::EnumerationCS(basecs::EnumerationCS): { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
				sr._142 /* OCLinEcore::EnumerationCS(basecs::EnumerationCS): { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
				sr._150 /* OCLinEcore::EnumerationCS(basecs::EnumerationCS): { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
				sr._149 /* OCLinEcore::EnumerationCS(basecs::EnumerationCS): { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] ';' } */,
				sr._151 /* OCLinEcore::EnumerationCS(basecs::EnumerationCS): { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] ';' } */,
				sr._153 /* OCLinEcore::EnumerationCS(basecs::EnumerationCS): { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] ';' } */,
				sr._148 /* OCLinEcore::EnumerationCS(basecs::EnumerationCS): { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
				sr._147 /* OCLinEcore::EnumerationCS(basecs::EnumerationCS): { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
				sr._143 /* OCLinEcore::EnumerationCS(basecs::EnumerationCS): { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
				sr._145 /* OCLinEcore::EnumerationCS(basecs::EnumerationCS): { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] ';' } */,
				sr._146 /* OCLinEcore::EnumerationCS(basecs::EnumerationCS): { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] ';' } */,
				sr._144 /* OCLinEcore::EnumerationCS(basecs::EnumerationCS): { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] ';' } */,
				sr._152 /* OCLinEcore::EnumerationCS(basecs::EnumerationCS): { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
				sr._142 /* OCLinEcore::EnumerationCS(basecs::EnumerationCS): { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
				sr._150 /* OCLinEcore::EnumerationCS(basecs::EnumerationCS): { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */
				}, null);
			private final /*@NonNull*/ EClassData _13 // EnumerationLiteralCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData("EnumerationLiteralCS", org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.ENUMERATION_LITERAL_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._156 /* OCLinEcore::EnumerationLiteralCS(basecs::EnumerationLiteralCS): { name=EnumerationLiteralName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] ';' } */,
				sr._155 /* OCLinEcore::EnumerationLiteralCS(basecs::EnumerationLiteralCS): { 'literal' name=UnrestrictedName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] ';' } */,
				sr._154 /* OCLinEcore::EnumerationLiteralCS(basecs::EnumerationLiteralCS): { name=EnumerationLiteralName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] '{' ownedAnnotations+=AnnotationElementCS[*] '}' } */,
				sr._157 /* OCLinEcore::EnumerationLiteralCS(basecs::EnumerationLiteralCS): { 'literal' name=UnrestrictedName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] '{' ownedAnnotations+=AnnotationElementCS[*] '}' } */,
				sr._156 /* OCLinEcore::EnumerationLiteralCS(basecs::EnumerationLiteralCS): { name=EnumerationLiteralName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] ';' } */,
				sr._155 /* OCLinEcore::EnumerationLiteralCS(basecs::EnumerationLiteralCS): { 'literal' name=UnrestrictedName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] ';' } */,
				sr._154 /* OCLinEcore::EnumerationLiteralCS(basecs::EnumerationLiteralCS): { name=EnumerationLiteralName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] '{' ownedAnnotations+=AnnotationElementCS[*] '}' } */,
				sr._157 /* OCLinEcore::EnumerationLiteralCS(basecs::EnumerationLiteralCS): { 'literal' name=UnrestrictedName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] '{' ownedAnnotations+=AnnotationElementCS[*] '}' } */
				}, null);
			private final /*@NonNull*/ EClassData _14 // ExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData("ExpCS", org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.EXP_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._030 /* EssentialOCL::ExpCS(essentialoclcs::ExpCS): '*' */,
				sr._027 /* EssentialOCL::ExpCS(essentialoclcs::ExpCS): 'invalid' */,
				sr._039 /* EssentialOCL::ExpCS(essentialoclcs::ExpCS): 'null' */,
				sr._041 /* EssentialOCL::ExpCS(essentialoclcs::ExpCS): 'self' */,
				sr._030 /* EssentialOCL::ExpCS(essentialoclcs::ExpCS): '*' */,
				sr._027 /* EssentialOCL::ExpCS(essentialoclcs::ExpCS): 'invalid' */,
				sr._039 /* EssentialOCL::ExpCS(essentialoclcs::ExpCS): 'null' */,
				sr._041 /* EssentialOCL::ExpCS(essentialoclcs::ExpCS): 'self' */
				}, null);
			private final /*@NonNull*/ EClassData _15 // ExpSpecificationCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData("ExpSpecificationCS", org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.EXP_SPECIFICATION_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._194 /* OCLinEcore::SpecificationCS(essentialoclcs::ExpSpecificationCS): exprString=UNQUOTED_STRING */,
				sr._195 /* OCLinEcore::SpecificationCS(essentialoclcs::ExpSpecificationCS): ownedExpression=ExpCS */
				}, null);
			private final /*@NonNull*/ EClassData _16 // IfExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData("IfExpCS", org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.IF_EXP_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._035 /* EssentialOCL::ExpCS(essentialoclcs::IfExpCS): { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' } */,
				sr._044 /* EssentialOCL::IfExpCS(essentialoclcs::IfExpCS): { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' } */,
				sr._035 /* EssentialOCL::ExpCS(essentialoclcs::IfExpCS): { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' } */,
				sr._043 /* EssentialOCL::IfExpCS(essentialoclcs::IfExpCS): { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' } */,
				sr._044 /* EssentialOCL::IfExpCS(essentialoclcs::IfExpCS): { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' } */
				}, null);
			private final /*@NonNull*/ EClassData _17 // IfThenExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData("IfThenExpCS", org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.IF_THEN_EXP_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._025 /* EssentialOCL::ElseIfThenExpCS(essentialoclcs::IfThenExpCS): { 'elseif' ownedCondition=ExpCS 'then' ownedThenExpression=ExpCS } */
				}, null);
			private final /*@NonNull*/ EClassData _18 // ImplicitOppositeCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData("ImplicitOppositeCS", org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.IMPLICIT_OPPOSITE_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._158 /* OCLinEcore::ImplicitOppositeCS(basecs::ImplicitOppositeCS): { 'opposite' name=UnrestrictedName ':' ownedType=TypedMultiplicityRefCS { '{' { qualifiers+={'!ordered|!unique|ordered|unique'} }[+] '}' }[?] } */
				}, null);
			private final /*@NonNull*/ EClassData _19 // ImportCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData("ImportCS", org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.IMPORT_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._159 /* OCLinEcore::ImportCS(basecs::ImportCS): { {'import'|'library'} { name=UnrestrictedName ':' }[?] ownedPathName=URIPathNameCS isAll='::*'[?] ';' } */
				}, null);
			private final /*@NonNull*/ EClassData _20 // InfixExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData("InfixExpCS", org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.INFIX_EXP_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._031 /* EssentialOCL::ExpCS(essentialoclcs::InfixExpCS): { ownedLeft=PrefixedPrimaryExpCS name=BinaryOperatorName ownedRight=ExpCS } */,
				sr._031 /* EssentialOCL::ExpCS(essentialoclcs::InfixExpCS): { ownedLeft=PrefixedPrimaryExpCS name=BinaryOperatorName ownedRight=ExpCS } */
				}, null);
			private final /*@NonNull*/ EClassData _21 // InvalidLiteralExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData("InvalidLiteralExpCS", org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.INVALID_LITERAL_EXP_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._046 /* EssentialOCL::InvalidLiteralExpCS(essentialoclcs::InvalidLiteralExpCS): 'invalid' */,
				sr._045 /* EssentialOCL::InvalidLiteralExpCS(essentialoclcs::InvalidLiteralExpCS): 'invalid' */,
				sr._045 /* EssentialOCL::InvalidLiteralExpCS(essentialoclcs::InvalidLiteralExpCS): 'invalid' */,
				sr._046 /* EssentialOCL::InvalidLiteralExpCS(essentialoclcs::InvalidLiteralExpCS): 'invalid' */
				}, null);
			private final /*@NonNull*/ EClassData _22 // LambdaLiteralExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData("LambdaLiteralExpCS", org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.LAMBDA_LITERAL_EXP_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._040 /* EssentialOCL::ExpCS(essentialoclcs::LambdaLiteralExpCS): { 'Lambda' '{' ownedExpressionCS=ExpCS '}' } */,
				sr._048 /* EssentialOCL::LambdaLiteralExpCS(essentialoclcs::LambdaLiteralExpCS): { 'Lambda' '{' ownedExpressionCS=ExpCS '}' } */,
				sr._040 /* EssentialOCL::ExpCS(essentialoclcs::LambdaLiteralExpCS): { 'Lambda' '{' ownedExpressionCS=ExpCS '}' } */,
				sr._047 /* EssentialOCL::LambdaLiteralExpCS(essentialoclcs::LambdaLiteralExpCS): { 'Lambda' '{' ownedExpressionCS=ExpCS '}' } */,
				sr._048 /* EssentialOCL::LambdaLiteralExpCS(essentialoclcs::LambdaLiteralExpCS): { 'Lambda' '{' ownedExpressionCS=ExpCS '}' } */
				}, null);
			private final /*@NonNull*/ EClassData _23 // LetExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData("LetExpCS", org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.LET_EXP_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._050 /* EssentialOCL::LetExpCS(essentialoclcs::LetExpCS): { 'let' ownedVariables+=LetVariableCS { ',' ownedVariables+=LetVariableCS }[*] 'in' ownedInExpression=ExpCS } */,
				sr._049 /* EssentialOCL::LetExpCS(essentialoclcs::LetExpCS): { 'let' ownedVariables+=LetVariableCS { ',' ownedVariables+=LetVariableCS }[*] 'in' ownedInExpression=ExpCS } */,
				sr._050 /* EssentialOCL::LetExpCS(essentialoclcs::LetExpCS): { 'let' ownedVariables+=LetVariableCS { ',' ownedVariables+=LetVariableCS }[*] 'in' ownedInExpression=ExpCS } */,
				sr._049 /* EssentialOCL::LetExpCS(essentialoclcs::LetExpCS): { 'let' ownedVariables+=LetVariableCS { ',' ownedVariables+=LetVariableCS }[*] 'in' ownedInExpression=ExpCS } */
				}, null);
			private final /*@NonNull*/ EClassData _24 // LetVariableCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData("LetVariableCS", org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.LET_VARIABLE_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._051 /* EssentialOCL::LetVariableCS(essentialoclcs::LetVariableCS): { name=UnrestrictedName ownedRoundBracketedClause=RoundBracketedClauseCS[?] { ':' ownedType=TypeExpCS }[?] '=' ownedInitExpression=ExpCS } */
				}, null);
			private final /*@NonNull*/ EClassData _25 // MapLiteralExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData("MapLiteralExpCS", org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.MAP_LITERAL_EXP_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._034 /* EssentialOCL::ExpCS(essentialoclcs::MapLiteralExpCS): { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' } */,
				sr._052 /* EssentialOCL::MapLiteralExpCS(essentialoclcs::MapLiteralExpCS): { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' } */,
				sr._034 /* EssentialOCL::ExpCS(essentialoclcs::MapLiteralExpCS): { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' } */,
				sr._053 /* EssentialOCL::MapLiteralExpCS(essentialoclcs::MapLiteralExpCS): { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' } */,
				sr._052 /* EssentialOCL::MapLiteralExpCS(essentialoclcs::MapLiteralExpCS): { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' } */
				}, null);
			private final /*@NonNull*/ EClassData _26 // MapLiteralPartCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData("MapLiteralPartCS", org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.MAP_LITERAL_PART_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._054 /* EssentialOCL::MapLiteralPartCS(essentialoclcs::MapLiteralPartCS): { ownedKey=ExpCS '<-' ownedValue=ExpCS } */
				}, null);
			private final /*@NonNull*/ EClassData _27 // MapTypeCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData("MapTypeCS", org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.MAP_TYPE_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._055 /* EssentialOCL::MapTypeCS(essentialoclcs::MapTypeCS): { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] } */,
				sr._056 /* EssentialOCL::MapTypeCS(essentialoclcs::MapTypeCS): { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] } */,
				sr._098 /* EssentialOCL::TypeExpCS(essentialoclcs::MapTypeCS): { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
				sr._055 /* EssentialOCL::MapTypeCS(essentialoclcs::MapTypeCS): { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] } */,
				sr._056 /* EssentialOCL::MapTypeCS(essentialoclcs::MapTypeCS): { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] } */,
				sr._107 /* EssentialOCL::TypeLiteralWithMultiplicityCS(essentialoclcs::MapTypeCS): { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
				sr._210 /* OCLinEcore::TypedMultiplicityRefCS(essentialoclcs::MapTypeCS): { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
				sr._056 /* EssentialOCL::MapTypeCS(essentialoclcs::MapTypeCS): { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] } */
				}, null);
			private final /*@NonNull*/ EClassData _28 // ModelElementRefCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData("ModelElementRefCS", org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MODEL_ELEMENT_REF_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._162 /* OCLinEcore::ModelElementRefCS(basecs::ModelElementRefCS): { 'reference' ownedPathName=PathNameCS ';' } */
				}, null);
			private final /*@NonNull*/ EClassData _29 // MultiplicityBoundsCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData("MultiplicityBoundsCS", org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MULTIPLICITY_BOUNDS_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._001 /* Base::MultiplicityBoundsCS(basecs::MultiplicityBoundsCS): { lowerBound=LOWER { '..' upperBound=UPPER }[?] } */,
				sr._004 /* Base::MultiplicityCS(basecs::MultiplicityBoundsCS): { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] ']' } */,
				sr._006 /* Base::MultiplicityCS(basecs::MultiplicityBoundsCS): { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] '|?' ']' } */,
				sr._007 /* Base::MultiplicityCS(basecs::MultiplicityBoundsCS): { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] isNullFree='|1'[?] ']' } */
				}, null);
			private final /*@NonNull*/ EClassData _30 // MultiplicityStringCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData("MultiplicityStringCS", org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.MULTIPLICITY_STRING_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._005 /* Base::MultiplicityCS(basecs::MultiplicityStringCS): { '[' stringBounds={'*|+|?'} ']' } */,
				sr._002 /* Base::MultiplicityCS(basecs::MultiplicityStringCS): { '[' stringBounds={'*|+|?'} '|?' ']' } */,
				sr._003 /* Base::MultiplicityCS(basecs::MultiplicityStringCS): { '[' stringBounds={'*|+|?'} isNullFree='|1'[?] ']' } */,
				sr._008 /* Base::MultiplicityStringCS(basecs::MultiplicityStringCS): stringBounds={'*|+|?'} */
				}, null);
			private final /*@NonNull*/ EClassData _31 // NameExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData("NameExpCS", org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NAME_EXP_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._038 /* EssentialOCL::ExpCS(essentialoclcs::NameExpCS): { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] } */,
				sr._059 /* EssentialOCL::NameExpCS(essentialoclcs::NameExpCS): { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] } */,
				sr._038 /* EssentialOCL::ExpCS(essentialoclcs::NameExpCS): { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] } */,
				sr._058 /* EssentialOCL::NameExpCS(essentialoclcs::NameExpCS): { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] } */,
				sr._059 /* EssentialOCL::NameExpCS(essentialoclcs::NameExpCS): { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] } */
				}, null);
			private final /*@NonNull*/ EClassData _32 // NavigatingArgCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData("NavigatingArgCS", org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NAVIGATING_ARG_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._061 /* EssentialOCL::NavigatingArgCS(essentialoclcs::NavigatingArgCS): ownedNameExpression=NavigatingArgExpCS */,
				sr._062 /* EssentialOCL::NavigatingArgCS(essentialoclcs::NavigatingArgCS): { ':' ownedType=TypeExpCS } */,
				sr._064 /* EssentialOCL::NavigatingArgCS(essentialoclcs::NavigatingArgCS): { ownedNameExpression=NavigatingArgExpCS '<-' ownedCoIterator=CoIteratorVariableCS { '=' ownedInitExpression=ExpCS }[?] } */,
				sr._063 /* EssentialOCL::NavigatingArgCS(essentialoclcs::NavigatingArgCS): { ownedNameExpression=NavigatingArgExpCS ':' ownedType=TypeExpCS { '<-' ownedCoIterator=CoIteratorVariableCS }[?] { '=' ownedInitExpression=ExpCS }[?] } */,
				sr._060 /* EssentialOCL::NavigatingArgCS(essentialoclcs::NavigatingArgCS): { ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS }[?] { '<-' ownedCoIterator=CoIteratorVariableCS }[?] 'in' ownedInitExpression=ExpCS } */,
				sr._065 /* EssentialOCL::NavigatingBarArgCS(essentialoclcs::NavigatingArgCS): { prefix='|' ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS { '=' ownedInitExpression=ExpCS }[?] }[?] } */,
				sr._066 /* EssentialOCL::NavigatingCommaArgCS(essentialoclcs::NavigatingArgCS): { prefix=',' ownedNameExpression=NavigatingArgExpCS } */,
				sr._069 /* EssentialOCL::NavigatingCommaArgCS(essentialoclcs::NavigatingArgCS): { prefix=',' ownedNameExpression=NavigatingArgExpCS '<-' ownedCoIterator=CoIteratorVariableCS { '=' ownedInitExpression=ExpCS }[?] } */,
				sr._067 /* EssentialOCL::NavigatingCommaArgCS(essentialoclcs::NavigatingArgCS): { prefix=',' ownedNameExpression=NavigatingArgExpCS ':' ownedType=TypeExpCS { '<-' ownedCoIterator=CoIteratorVariableCS }[?] { '=' ownedInitExpression=ExpCS }[?] } */,
				sr._068 /* EssentialOCL::NavigatingCommaArgCS(essentialoclcs::NavigatingArgCS): { prefix=',' ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS }[?] { '<-' ownedCoIterator=CoIteratorVariableCS }[?] 'in' ownedInitExpression=ExpCS } */,
				sr._070 /* EssentialOCL::NavigatingSemiArgCS(essentialoclcs::NavigatingArgCS): { prefix=';' ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS { '=' ownedInitExpression=ExpCS }[?] }[?] } */
				}, null);
			private final /*@NonNull*/ EClassData _33 // NestedExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData("NestedExpCS", org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NESTED_EXP_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._037 /* EssentialOCL::ExpCS(essentialoclcs::NestedExpCS): { '(' ownedExpression=ExpCS ')' } */,
				sr._037 /* EssentialOCL::ExpCS(essentialoclcs::NestedExpCS): { '(' ownedExpression=ExpCS ')' } */,
				sr._071 /* EssentialOCL::NestedExpCS(essentialoclcs::NestedExpCS): { '(' ownedExpression=ExpCS ')' } */,
				sr._072 /* EssentialOCL::NestedExpCS(essentialoclcs::NestedExpCS): { '(' ownedExpression=ExpCS ')' } */,
				sr._071 /* EssentialOCL::NestedExpCS(essentialoclcs::NestedExpCS): { '(' ownedExpression=ExpCS ')' } */
				}, null);
			private final /*@NonNull*/ EClassData _34 // NullLiteralExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData("NullLiteralExpCS", org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NULL_LITERAL_EXP_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._074 /* EssentialOCL::NullLiteralExpCS(essentialoclcs::NullLiteralExpCS): 'null' */,
				sr._073 /* EssentialOCL::NullLiteralExpCS(essentialoclcs::NullLiteralExpCS): 'null' */,
				sr._073 /* EssentialOCL::NullLiteralExpCS(essentialoclcs::NullLiteralExpCS): 'null' */,
				sr._074 /* EssentialOCL::NullLiteralExpCS(essentialoclcs::NullLiteralExpCS): 'null' */
				}, null);
			private final /*@NonNull*/ EClassData _35 // NumberLiteralExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData("NumberLiteralExpCS", org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.NUMBER_LITERAL_EXP_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._028 /* EssentialOCL::ExpCS(essentialoclcs::NumberLiteralExpCS): symbol=NUMBER_LITERAL */,
				sr._028 /* EssentialOCL::ExpCS(essentialoclcs::NumberLiteralExpCS): symbol=NUMBER_LITERAL */,
				sr._075 /* EssentialOCL::NumberLiteralExpCS(essentialoclcs::NumberLiteralExpCS): symbol=NUMBER_LITERAL */,
				sr._076 /* EssentialOCL::NumberLiteralExpCS(essentialoclcs::NumberLiteralExpCS): symbol=NUMBER_LITERAL */,
				sr._076 /* EssentialOCL::NumberLiteralExpCS(essentialoclcs::NumberLiteralExpCS): symbol=NUMBER_LITERAL */,
				sr._075 /* EssentialOCL::NumberLiteralExpCS(essentialoclcs::NumberLiteralExpCS): symbol=NUMBER_LITERAL */
				}, null);
			private final /*@NonNull*/ EClassData _36 // OCLinEcoreConstraintCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData("OCLinEcoreConstraintCS", org.eclipse.ocl.xtext.oclinecorecs.OCLinEcoreCSPackage.Literals.OC_LIN_ECORE_CONSTRAINT_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._160 /* OCLinEcore::InvariantConstraintCS(oclinecorecs::OCLinEcoreConstraintCS): { isCallable='callable'[?] stereotype='invariant' { name=UnrestrictedName { '(' ownedMessageSpecification=SpecificationCS ')' }[?] }[?] ';' } */,
				sr._161 /* OCLinEcore::InvariantConstraintCS(oclinecorecs::OCLinEcoreConstraintCS): { isCallable='callable'[?] stereotype='invariant' { name=UnrestrictedName { '(' ownedMessageSpecification=SpecificationCS ')' }[?] }[?] ':' ownedSpecification=SpecificationCS[?] ';' } */,
				sr._180 /* OCLinEcore::PostconditionConstraintCS(oclinecorecs::OCLinEcoreConstraintCS): { stereotype='postcondition' { name=UnrestrictedName { '(' ownedMessageSpecification=SpecificationCS ')' }[?] }[?] ':' ownedSpecification=SpecificationCS[?] ';' } */,
				sr._181 /* OCLinEcore::PreconditionConstraintCS(oclinecorecs::OCLinEcoreConstraintCS): { stereotype='precondition' { name=UnrestrictedName { '(' ownedMessageSpecification=SpecificationCS ')' }[?] }[?] ':' ownedSpecification=SpecificationCS[?] ';' } */
				}, null);
			private final /*@NonNull*/ EClassData _37 // OperationCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData("OperationCS", org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.OPERATION_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._171 /* OCLinEcore::OperationCS(basecs::OperationCS): { 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] ';' } */,
				sr._168 /* OCLinEcore::OperationCS(basecs::OperationCS): { qualifiers+='definition' qualifiers+='static'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] ';' } */,
				sr._166 /* OCLinEcore::OperationCS(basecs::OperationCS): { qualifiers+='static' qualifiers+='definition'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] ';' } */,
				sr._167 /* OCLinEcore::OperationCS(basecs::OperationCS): { 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { 'body' ':' ownedBodyExpressions+=SpecificationCS[?] ';' }[*] ownedPostconditions+=PostconditionConstraintCS[*] '}' } */,
				sr._170 /* OCLinEcore::OperationCS(basecs::OperationCS): { qualifiers+='definition' qualifiers+='static'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { 'body' ':' ownedBodyExpressions+=SpecificationCS[?] ';' }[*] ownedPostconditions+=PostconditionConstraintCS[*] '}' } */,
				sr._165 /* OCLinEcore::OperationCS(basecs::OperationCS): { qualifiers+='static' qualifiers+='definition'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { 'body' ':' ownedBodyExpressions+=SpecificationCS[?] ';' }[*] ownedPostconditions+=PostconditionConstraintCS[*] '}' } */,
				sr._174 /* OCLinEcore::OperationCS(basecs::OperationCS): { 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] ';' } */,
				sr._173 /* OCLinEcore::OperationCS(basecs::OperationCS): { qualifiers+='definition' qualifiers+='static'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] ';' } */,
				sr._172 /* OCLinEcore::OperationCS(basecs::OperationCS): { qualifiers+='static' qualifiers+='definition'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] ';' } */,
				sr._164 /* OCLinEcore::OperationCS(basecs::OperationCS): { 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { 'body' ':' ownedBodyExpressions+=SpecificationCS[?] ';' }[*] ownedPostconditions+=PostconditionConstraintCS[*] '}' } */,
				sr._169 /* OCLinEcore::OperationCS(basecs::OperationCS): { qualifiers+='definition' qualifiers+='static'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { 'body' ':' ownedBodyExpressions+=SpecificationCS[?] ';' }[*] ownedPostconditions+=PostconditionConstraintCS[*] '}' } */,
				sr._163 /* OCLinEcore::OperationCS(basecs::OperationCS): { qualifiers+='static' qualifiers+='definition'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { 'body' ':' ownedBodyExpressions+=SpecificationCS[?] ';' }[*] ownedPostconditions+=PostconditionConstraintCS[*] '}' } */
				}, null);
			private final /*@NonNull*/ EClassData _38 // PackageCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData("PackageCS", org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PACKAGE_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._175 /* OCLinEcore::PackageCS(basecs::PackageCS): { 'package' name=UnrestrictedName { ':' nsPrefix=UnrestrictedName }[?] { '=' nsURI=URI }[?] ';' } */,
				sr._178 /* OCLinEcore::PackageCS(basecs::PackageCS): { 'package' name=UnrestrictedName { ':' nsPrefix=UnrestrictedName }[?] { '=' nsURI=URI }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPackages+=PackageCS[*] ownedClasses+=ClassCS[*] '}' } */,
				sr._177 /* OCLinEcore::PackageCS(basecs::PackageCS): { 'package' name=UnrestrictedName { ':' nsPrefix=UnrestrictedName }[?] { '=' nsURI=URI }[?] ';' } */,
				sr._176 /* OCLinEcore::PackageCS(basecs::PackageCS): { 'package' name=UnrestrictedName { ':' nsPrefix=UnrestrictedName }[?] { '=' nsURI=URI }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPackages+=PackageCS[*] ownedClasses+=ClassCS[*] '}' } */
				}, null);
			private final /*@NonNull*/ EClassData _39 // ParameterCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData("ParameterCS", org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PARAMETER_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._179 /* OCLinEcore::ParameterCS(basecs::ParameterCS): { name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '{' { qualifiers+={'!ordered|!unique|ordered|unique'} }[+] '}' }[?] { '{' ownedAnnotations+=AnnotationElementCS[*] '}' }[?] } */
				}, null);
			private final /*@NonNull*/ EClassData _40 // PathElementCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData("PathElementCS", org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PATH_ELEMENT_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._000 /* Base::FirstPathElementCS(basecs::PathElementCS): referredElement=UnrestrictedName */,
				sr._009 /* Base::NextPathElementCS(basecs::PathElementCS): referredElement=UnreservedName */,
				sr._113 /* EssentialOCL::URIFirstPathElementCS(basecs::PathElementCS): referredElement=UnrestrictedName */
				}, null);
			private final /*@NonNull*/ EClassData _41 // PathElementWithURICS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData("PathElementWithURICS", org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PATH_ELEMENT_WITH_URICS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._112 /* EssentialOCL::URIFirstPathElementCS(basecs::PathElementWithURICS): referredElement=URI */
				}, null);
			private final /*@NonNull*/ EClassData _42 // PathNameCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData("PathNameCS", org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PATH_NAME_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._010 /* Base::PathNameCS(basecs::PathNameCS): { ownedPathElements+=FirstPathElementCS { '::' ownedPathElements+=NextPathElementCS }[*] } */,
				sr._088 /* EssentialOCL::SimplePathNameCS(basecs::PathNameCS): ownedPathElements+=FirstPathElementCS */,
				sr._114 /* EssentialOCL::URIPathNameCS(basecs::PathNameCS): { ownedPathElements+=URIFirstPathElementCS { '::' ownedPathElements+=NextPathElementCS }[*] } */
				}, null);
			private final /*@NonNull*/ EClassData _43 // PatternExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData("PatternExpCS", org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.PATTERN_EXP_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._077 /* EssentialOCL::PatternExpCS(essentialoclcs::PatternExpCS): { patternVariableName=UnrestrictedName[?] ':' ownedPatternType=TypeExpCS } */
				}, null);
			private final /*@NonNull*/ EClassData _44 // PrefixExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData("PrefixExpCS", org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.PREFIX_EXP_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._029 /* EssentialOCL::ExpCS(essentialoclcs::PrefixExpCS): { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS } */,
				sr._078 /* EssentialOCL::PrefixedLetExpCS(essentialoclcs::PrefixExpCS): { name=UnaryOperatorName ownedRight=PrefixedLetExpCS } */,
				sr._029 /* EssentialOCL::ExpCS(essentialoclcs::PrefixExpCS): { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS } */,
				sr._078 /* EssentialOCL::PrefixedLetExpCS(essentialoclcs::PrefixExpCS): { name=UnaryOperatorName ownedRight=PrefixedLetExpCS } */,
				sr._079 /* EssentialOCL::PrefixedLetExpCS(essentialoclcs::PrefixExpCS): { name=UnaryOperatorName ownedRight=PrefixedLetExpCS } */,
				sr._080 /* EssentialOCL::PrefixedPrimaryExpCS(essentialoclcs::PrefixExpCS): { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS } */
				}, null);
			private final /*@NonNull*/ EClassData _45 // PrimitiveTypeRefCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData("PrimitiveTypeRefCS", org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.PRIMITIVE_TYPE_REF_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._081 /* EssentialOCL::PrimitiveTypeCS(basecs::PrimitiveTypeRefCS): name=PrimitiveTypeIdentifier */,
				sr._082 /* EssentialOCL::PrimitiveTypeCS(basecs::PrimitiveTypeRefCS): name=PrimitiveTypeIdentifier */,
				sr._099 /* EssentialOCL::TypeExpCS(basecs::PrimitiveTypeRefCS): { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] } */,
				sr._081 /* EssentialOCL::PrimitiveTypeCS(basecs::PrimitiveTypeRefCS): name=PrimitiveTypeIdentifier */,
				sr._082 /* EssentialOCL::PrimitiveTypeCS(basecs::PrimitiveTypeRefCS): name=PrimitiveTypeIdentifier */,
				sr._109 /* EssentialOCL::TypeLiteralWithMultiplicityCS(basecs::PrimitiveTypeRefCS): { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] } */,
				sr._209 /* OCLinEcore::TypedMultiplicityRefCS(basecs::PrimitiveTypeRefCS): { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] } */,
				sr._082 /* EssentialOCL::PrimitiveTypeCS(basecs::PrimitiveTypeRefCS): name=PrimitiveTypeIdentifier */
				}, null);
			private final /*@NonNull*/ EClassData _46 // ReferenceCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData("ReferenceCS", org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.REFERENCE_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._183 /* OCLinEcore::ReferenceCS(basecs::ReferenceCS): { 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
				sr._190 /* OCLinEcore::ReferenceCS(basecs::ReferenceCS): { qualifiers+='definition' qualifiers+='static'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
				sr._193 /* OCLinEcore::ReferenceCS(basecs::ReferenceCS): { qualifiers+='static' qualifiers+='definition'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
				sr._184 /* OCLinEcore::ReferenceCS(basecs::ReferenceCS): { 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' } */,
				sr._182 /* OCLinEcore::ReferenceCS(basecs::ReferenceCS): { qualifiers+='definition' qualifiers+='static'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' } */,
				sr._186 /* OCLinEcore::ReferenceCS(basecs::ReferenceCS): { qualifiers+='static' qualifiers+='definition'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' } */,
				sr._187 /* OCLinEcore::ReferenceCS(basecs::ReferenceCS): { 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
				sr._192 /* OCLinEcore::ReferenceCS(basecs::ReferenceCS): { qualifiers+='definition' qualifiers+='static'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
				sr._189 /* OCLinEcore::ReferenceCS(basecs::ReferenceCS): { qualifiers+='static' qualifiers+='definition'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
				sr._188 /* OCLinEcore::ReferenceCS(basecs::ReferenceCS): { 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' } */,
				sr._191 /* OCLinEcore::ReferenceCS(basecs::ReferenceCS): { qualifiers+='definition' qualifiers+='static'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' } */,
				sr._185 /* OCLinEcore::ReferenceCS(basecs::ReferenceCS): { qualifiers+='static' qualifiers+='definition'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' } */,
				sr._187 /* OCLinEcore::ReferenceCS(basecs::ReferenceCS): { 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
				sr._192 /* OCLinEcore::ReferenceCS(basecs::ReferenceCS): { qualifiers+='definition' qualifiers+='static'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
				sr._189 /* OCLinEcore::ReferenceCS(basecs::ReferenceCS): { qualifiers+='static' qualifiers+='definition'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */,
				sr._188 /* OCLinEcore::ReferenceCS(basecs::ReferenceCS): { 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' } */,
				sr._191 /* OCLinEcore::ReferenceCS(basecs::ReferenceCS): { qualifiers+='definition' qualifiers+='static'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' } */,
				sr._185 /* OCLinEcore::ReferenceCS(basecs::ReferenceCS): { qualifiers+='static' qualifiers+='definition'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' } */
				}, null);
			private final /*@NonNull*/ EClassData _47 // RoundBracketedClauseCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData("RoundBracketedClauseCS", org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.ROUND_BRACKETED_CLAUSE_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._083 /* EssentialOCL::RoundBracketedClauseCS(essentialoclcs::RoundBracketedClauseCS): { '(' { ownedArguments+=NavigatingArgCS ownedArguments+=(NavigatingCommaArgCS|NavigatingSemiArgCS|NavigatingBarArgCS)[*] }[?] ')' } */
				}, null);
			private final /*@NonNull*/ EClassData _48 // SelfExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData("SelfExpCS", org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.SELF_EXP_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._085 /* EssentialOCL::SelfExpCS(essentialoclcs::SelfExpCS): 'self' */,
				sr._085 /* EssentialOCL::SelfExpCS(essentialoclcs::SelfExpCS): 'self' */,
				sr._084 /* EssentialOCL::SelfExpCS(essentialoclcs::SelfExpCS): 'self' */
				}, null);
			private final /*@NonNull*/ EClassData _49 // ShadowPartCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData("ShadowPartCS", org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.SHADOW_PART_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._086 /* EssentialOCL::ShadowPartCS(essentialoclcs::ShadowPartCS): ownedInitExpression=StringLiteralExpCS */,
				sr._087 /* EssentialOCL::ShadowPartCS(essentialoclcs::ShadowPartCS): { referredProperty=UnrestrictedName '=' ownedInitExpression=(ExpCS|PatternExpCS) } */
				}, null);
			private final /*@NonNull*/ EClassData _50 // SquareBracketedClauseCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData("SquareBracketedClauseCS", org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.SQUARE_BRACKETED_CLAUSE_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._089 /* EssentialOCL::SquareBracketedClauseCS(essentialoclcs::SquareBracketedClauseCS): { '[' ownedTerms+=ExpCS { ',' ownedTerms+=ExpCS }[*] ']' } */
				}, null);
			private final /*@NonNull*/ EClassData _51 // StringLiteralExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData("StringLiteralExpCS", org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.STRING_LITERAL_EXP_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._033 /* EssentialOCL::ExpCS(essentialoclcs::StringLiteralExpCS): segments+=StringLiteral[+] */,
				sr._033 /* EssentialOCL::ExpCS(essentialoclcs::StringLiteralExpCS): segments+=StringLiteral[+] */,
				sr._091 /* EssentialOCL::StringLiteralExpCS(essentialoclcs::StringLiteralExpCS): segments+=StringLiteral[+] */,
				sr._091 /* EssentialOCL::StringLiteralExpCS(essentialoclcs::StringLiteralExpCS): segments+=StringLiteral[+] */,
				sr._091 /* EssentialOCL::StringLiteralExpCS(essentialoclcs::StringLiteralExpCS): segments+=StringLiteral[+] */,
				sr._090 /* EssentialOCL::StringLiteralExpCS(essentialoclcs::StringLiteralExpCS): segments+=StringLiteral[+] */
				}, null);
			private final /*@NonNull*/ EClassData _52 // StructuredClassCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData("StructuredClassCS", org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.STRUCTURED_CLASS_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._198 /* OCLinEcore::StructuredClassCS(basecs::StructuredClassCS): { isAbstract='abstract'[?] 'class' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { 'extends' ownedSuperTypes+=TypedRefCS { ',' ownedSuperTypes+=TypedRefCS }[*] }[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isInterface='interface'[?] '}' }[?] ';' } */,
				sr._196 /* OCLinEcore::StructuredClassCS(basecs::StructuredClassCS): { isAbstract='abstract'[?] 'class' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { 'extends' ownedSuperTypes+=TypedRefCS { ',' ownedSuperTypes+=TypedRefCS }[*] }[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isInterface='interface'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedOperations+=OperationCS[*] ownedProperties+=StructuralFeatureCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
				sr._198 /* OCLinEcore::StructuredClassCS(basecs::StructuredClassCS): { isAbstract='abstract'[?] 'class' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { 'extends' ownedSuperTypes+=TypedRefCS { ',' ownedSuperTypes+=TypedRefCS }[*] }[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isInterface='interface'[?] '}' }[?] ';' } */,
				sr._196 /* OCLinEcore::StructuredClassCS(basecs::StructuredClassCS): { isAbstract='abstract'[?] 'class' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { 'extends' ownedSuperTypes+=TypedRefCS { ',' ownedSuperTypes+=TypedRefCS }[*] }[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isInterface='interface'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedOperations+=OperationCS[*] ownedProperties+=StructuralFeatureCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */,
				sr._197 /* OCLinEcore::StructuredClassCS(basecs::StructuredClassCS): { isAbstract='abstract'[?] 'class' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { 'extends' ownedSuperTypes+=TypedRefCS { ',' ownedSuperTypes+=TypedRefCS }[*] }[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isInterface='interface'[?] '}' }[?] ';' } */,
				sr._199 /* OCLinEcore::StructuredClassCS(basecs::StructuredClassCS): { isAbstract='abstract'[?] 'class' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { 'extends' ownedSuperTypes+=TypedRefCS { ',' ownedSuperTypes+=TypedRefCS }[*] }[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isInterface='interface'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedOperations+=OperationCS[*] ownedProperties+=StructuralFeatureCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */
				}, null);
			private final /*@NonNull*/ EClassData _53 // SysMLCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData("SysMLCS", org.eclipse.ocl.xtext.oclinecorecs.OCLinEcoreCSPackage.Literals.SYS_MLCS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._203 /* OCLinEcore::SysMLCS(oclinecorecs::SysMLCS): { 'sysml' ownedDetails+=DetailCS ';' } */,
				sr._201 /* OCLinEcore::SysMLCS(oclinecorecs::SysMLCS): { 'sysml' '{' { ownedDetails+=DetailCS ';' }[*] '}' } */,
				sr._200 /* OCLinEcore::SysMLCS(oclinecorecs::SysMLCS): { 'sysml' ownedDetails+=DetailCS ';' } */,
				sr._202 /* OCLinEcore::SysMLCS(oclinecorecs::SysMLCS): { 'sysml' '{' { ownedDetails+=DetailCS ';' }[*] '}' } */
				}, null);
			private final /*@NonNull*/ EClassData _54 // TemplateBindingCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData("TemplateBindingCS", org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATE_BINDING_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._011 /* Base::TemplateBindingCS(basecs::TemplateBindingCS): { ownedSubstitutions+=TemplateParameterSubstitutionCS { ',' ownedSubstitutions+=TemplateParameterSubstitutionCS }[*] ownedMultiplicity=MultiplicityCS[?] } */
				}, null);
			private final /*@NonNull*/ EClassData _55 // TemplateParameterSubstitutionCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData("TemplateParameterSubstitutionCS", org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATE_PARAMETER_SUBSTITUTION_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._012 /* Base::TemplateParameterSubstitutionCS(basecs::TemplateParameterSubstitutionCS): ownedActualParameter=TypeRefCS */
				}, null);
			private final /*@NonNull*/ EClassData _56 // TemplateSignatureCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData("TemplateSignatureCS", org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TEMPLATE_SIGNATURE_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._204 /* OCLinEcore::TemplateSignatureCS(basecs::TemplateSignatureCS): { '(' ownedParameters+=TypeParameterCS { ',' ownedParameters+=TypeParameterCS }[*] ')' } */,
				sr._205 /* OCLinEcore::TemplateSignatureCS(basecs::TemplateSignatureCS): { '<' ownedParameters+=TypeParameterCS { ',' ownedParameters+=TypeParameterCS }[*] '>' } */
				}, null);
			private final /*@NonNull*/ EClassData _57 // TopLevelCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData("TopLevelCS", org.eclipse.ocl.xtext.oclinecorecs.OCLinEcoreCSPackage.Literals.TOP_LEVEL_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._206 /* OCLinEcore::TopLevelCS(oclinecorecs::TopLevelCS): { { 'module' }[?] ownedImports+=ImportCS[*] ownedPackages+=PackageCS[*] } */
				}, null);
			private final /*@NonNull*/ EClassData _58 // TupleLiteralExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData("TupleLiteralExpCS", org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.TUPLE_LITERAL_EXP_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._032 /* EssentialOCL::ExpCS(essentialoclcs::TupleLiteralExpCS): { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' } */,
				sr._032 /* EssentialOCL::ExpCS(essentialoclcs::TupleLiteralExpCS): { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' } */,
				sr._093 /* EssentialOCL::TupleLiteralExpCS(essentialoclcs::TupleLiteralExpCS): { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' } */,
				sr._093 /* EssentialOCL::TupleLiteralExpCS(essentialoclcs::TupleLiteralExpCS): { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' } */,
				sr._092 /* EssentialOCL::TupleLiteralExpCS(essentialoclcs::TupleLiteralExpCS): { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' } */
				}, null);
			private final /*@NonNull*/ EClassData _59 // TupleLiteralPartCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData("TupleLiteralPartCS", org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.TUPLE_LITERAL_PART_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._094 /* EssentialOCL::TupleLiteralPartCS(essentialoclcs::TupleLiteralPartCS): { name=UnrestrictedName { ':' ownedType=TypeExpCS }[?] '=' ownedInitExpression=ExpCS } */
				}, null);
			private final /*@NonNull*/ EClassData _60 // TuplePartCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData("TuplePartCS", org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TUPLE_PART_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._095 /* EssentialOCL::TuplePartCS(basecs::TuplePartCS): { name=UnrestrictedName ':' ownedType=TypeExpCS } */
				}, null);
			private final /*@NonNull*/ EClassData _61 // TupleTypeCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData("TupleTypeCS", org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TUPLE_TYPE_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._097 /* EssentialOCL::TupleTypeCS(basecs::TupleTypeCS): { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] } */,
				sr._096 /* EssentialOCL::TupleTypeCS(basecs::TupleTypeCS): { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] } */,
				sr._101 /* EssentialOCL::TypeExpCS(basecs::TupleTypeCS): { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
				sr._097 /* EssentialOCL::TupleTypeCS(basecs::TupleTypeCS): { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] } */,
				sr._096 /* EssentialOCL::TupleTypeCS(basecs::TupleTypeCS): { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] } */,
				sr._106 /* EssentialOCL::TypeLiteralWithMultiplicityCS(basecs::TupleTypeCS): { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
				sr._208 /* OCLinEcore::TypedMultiplicityRefCS(basecs::TupleTypeCS): { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */,
				sr._096 /* EssentialOCL::TupleTypeCS(basecs::TupleTypeCS): { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] } */
				}, null);
			private final /*@NonNull*/ EClassData _62 // TypeLiteralExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData("TypeLiteralExpCS", org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.TYPE_LITERAL_EXP_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._036 /* EssentialOCL::ExpCS(essentialoclcs::TypeLiteralExpCS): ownedType=TypeLiteralWithMultiplicityCS */,
				sr._036 /* EssentialOCL::ExpCS(essentialoclcs::TypeLiteralExpCS): ownedType=TypeLiteralWithMultiplicityCS */,
				sr._104 /* EssentialOCL::TypeLiteralExpCS(essentialoclcs::TypeLiteralExpCS): ownedType=TypeLiteralWithMultiplicityCS */,
				sr._104 /* EssentialOCL::TypeLiteralExpCS(essentialoclcs::TypeLiteralExpCS): ownedType=TypeLiteralWithMultiplicityCS */,
				sr._105 /* EssentialOCL::TypeLiteralExpCS(essentialoclcs::TypeLiteralExpCS): ownedType=TypeLiteralWithMultiplicityCS */
				}, null);
			private final /*@NonNull*/ EClassData _63 // TypeNameExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData("TypeNameExpCS", org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.TYPE_NAME_EXP_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._100 /* EssentialOCL::TypeExpCS(essentialoclcs::TypeNameExpCS): { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { '{' ownedPatternGuard=ExpCS '}' }[?] }[?] ownedMultiplicity=MultiplicityCS[?] } */,
				sr._111 /* EssentialOCL::TypeNameExpCS(essentialoclcs::TypeNameExpCS): { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { '{' ownedPatternGuard=ExpCS '}' }[?] }[?] } */,
				sr._110 /* EssentialOCL::TypeNameExpCS(essentialoclcs::TypeNameExpCS): { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { '{' ownedPatternGuard=ExpCS '}' }[?] }[?] } */
				}, null);
			private final /*@NonNull*/ EClassData _64 // TypeParameterCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData("TypeParameterCS", org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPE_PARAMETER_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._013 /* Base::TypeParameterCS(basecs::TypeParameterCS): { name=UnrestrictedName { 'extends' ownedExtends+=TypedRefCS { '&&' ownedExtends+=TypedRefCS }[*] }[?] } */
				}, null);
			private final /*@NonNull*/ EClassData _65 // TypedTypeRefCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData("TypedTypeRefCS", org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.TYPED_TYPE_REF_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._218 /* OCLinEcore::TypedTypeRefCS(basecs::TypedTypeRefCS): ownedPathName=PathNameCS */,
				sr._219 /* OCLinEcore::TypedTypeRefCS(basecs::TypedTypeRefCS): { ownedPathName=PathNameCS '(' ownedBinding=TemplateBindingCS ')' } */,
				sr._216 /* OCLinEcore::TypedTypeRefCS(basecs::TypedTypeRefCS): { ownedPathName=PathNameCS '<' ownedBinding=TemplateBindingCS '>' } */,
				sr._213 /* OCLinEcore::TypedMultiplicityRefCS(basecs::TypedTypeRefCS): { ownedPathName=PathNameCS ownedMultiplicity=MultiplicityCS[?] } */,
				sr._211 /* OCLinEcore::TypedMultiplicityRefCS(basecs::TypedTypeRefCS): { ownedPathName=PathNameCS '(' ownedBinding=TemplateBindingCS ')' ownedMultiplicity=MultiplicityCS[?] } */,
				sr._212 /* OCLinEcore::TypedMultiplicityRefCS(basecs::TypedTypeRefCS): { ownedPathName=PathNameCS '<' ownedBinding=TemplateBindingCS '>' ownedMultiplicity=MultiplicityCS[?] } */,
				sr._218 /* OCLinEcore::TypedTypeRefCS(basecs::TypedTypeRefCS): ownedPathName=PathNameCS */,
				sr._219 /* OCLinEcore::TypedTypeRefCS(basecs::TypedTypeRefCS): { ownedPathName=PathNameCS '(' ownedBinding=TemplateBindingCS ')' } */,
				sr._216 /* OCLinEcore::TypedTypeRefCS(basecs::TypedTypeRefCS): { ownedPathName=PathNameCS '<' ownedBinding=TemplateBindingCS '>' } */,
				sr._214 /* OCLinEcore::TypedTypeRefCS(basecs::TypedTypeRefCS): ownedPathName=PathNameCS */,
				sr._217 /* OCLinEcore::TypedTypeRefCS(basecs::TypedTypeRefCS): { ownedPathName=PathNameCS '(' ownedBinding=TemplateBindingCS ')' } */,
				sr._215 /* OCLinEcore::TypedTypeRefCS(basecs::TypedTypeRefCS): { ownedPathName=PathNameCS '<' ownedBinding=TemplateBindingCS '>' } */
				}, null);
			private final /*@NonNull*/ EClassData _66 // UnlimitedNaturalLiteralExpCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData("UnlimitedNaturalLiteralExpCS", org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.UNLIMITED_NATURAL_LITERAL_EXP_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._116 /* EssentialOCL::UnlimitedNaturalLiteralExpCS(essentialoclcs::UnlimitedNaturalLiteralExpCS): '*' */,
				sr._116 /* EssentialOCL::UnlimitedNaturalLiteralExpCS(essentialoclcs::UnlimitedNaturalLiteralExpCS): '*' */,
				sr._116 /* EssentialOCL::UnlimitedNaturalLiteralExpCS(essentialoclcs::UnlimitedNaturalLiteralExpCS): '*' */,
				sr._115 /* EssentialOCL::UnlimitedNaturalLiteralExpCS(essentialoclcs::UnlimitedNaturalLiteralExpCS): '*' */
				}, null);
			private final /*@NonNull*/ EClassData _67 // VariableCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData("VariableCS", org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage.Literals.VARIABLE_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._017 /* EssentialOCL::CoIteratorVariableCS(essentialoclcs::VariableCS): { name=UnrestrictedName { ':' ownedType=TypeExpCS }[?] } */
				}, null);
			private final /*@NonNull*/ EClassData _68 // WildcardTypeRefCS
				= new org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData("WildcardTypeRefCS", org.eclipse.ocl.xtext.basecs.BaseCSPackage.Literals.WILDCARD_TYPE_REF_CS,
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule [] {
				sr._014 /* Base::WildcardTypeRefCS(basecs::WildcardTypeRefCS): { '?' { 'extends' ownedExtends=TypedRefCS }[?] } */,
				sr._015 /* Base::WildcardTypeRefCS(basecs::WildcardTypeRefCS): { '?' { 'extends' ownedExtends=TypedRefCS }[?] } */
				}, null);
		}

		private class _SerializationRules
		{
			private final /*@NonNull*/ RTSerializationRule _000
				= /* referredElement=UnrestrictedName */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(31, /* Base::FirstPathElementCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._049 /* assert (|referredElement| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._150 /* 1*referredElement=UnrestrictedName */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						ss._8 /* «? » + «value» + «? » */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _001
				= /* { lowerBound=LOWER { '..' upperBound=UPPER }[?] } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(55, /* Base::MultiplicityBoundsCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._100 /* assign V0 = |upperBound| */,
						ms._001 /* assert (|lowerBound| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-4-steps */,
						st._068 /* 1*lowerBound=LOWER */,
						st._169 /* V00*next-2-steps */,
						st._008 /* 1*'..' */,
						st._160 /* 1*upperBound=UPPER */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _002
				= /* { '[' stringBounds={'*|+|?'} '|?' ']' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(56, /* Base::MultiplicityCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._054 /* assert (|stringBounds.'*|+|?'| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-4-steps */,
						st._021 /* 1*'[' */,
						st._157 /* 1*stringBounds */,
						st._062 /* 1*'|?' */,
						st._022 /* 1*']' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._2 /* «! » + «value» + «! » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._5 /* «! » + «value» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _003
				= /* { '[' stringBounds={'*|+|?'} isNullFree='|1'[?] ']' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(56, /* Base::MultiplicityCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._073 /* assign V0 = |isNullFree.'|1'| */,
						ms._054 /* assert (|stringBounds.'*|+|?'| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-4-steps */,
						st._021 /* 1*'[' */,
						st._157 /* 1*stringBounds */,
						st._167 /* V00*'|1' */,
						st._022 /* 1*']' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._2 /* «! » + «value» + «! » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._5 /* «! » + «value» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _004
				= /* { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] ']' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(56, /* Base::MultiplicityCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._100 /* assign V0 = |upperBound| */,
						ms._001 /* assert (|lowerBound| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-6-steps */,
						st._021 /* 1*'[' */,
						st._068 /* 1*lowerBound=LOWER */,
						st._169 /* V00*next-2-steps */,
						st._008 /* 1*'..' */,
						st._160 /* 1*upperBound=UPPER */,
						st._022 /* 1*']' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._2 /* «! » + «value» + «! » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._5 /* «! » + «value» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _005
				= /* { '[' stringBounds={'*|+|?'} ']' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(56, /* Base::MultiplicityCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._054 /* assert (|stringBounds.'*|+|?'| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-3-steps */,
						st._021 /* 1*'[' */,
						st._157 /* 1*stringBounds */,
						st._022 /* 1*']' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._2 /* «! » + «value» + «! » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._5 /* «! » + «value» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _006
				= /* { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] '|?' ']' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(56, /* Base::MultiplicityCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._100 /* assign V0 = |upperBound| */,
						ms._001 /* assert (|lowerBound| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-7-steps */,
						st._021 /* 1*'[' */,
						st._068 /* 1*lowerBound=LOWER */,
						st._169 /* V00*next-2-steps */,
						st._008 /* 1*'..' */,
						st._160 /* 1*upperBound=UPPER */,
						st._062 /* 1*'|?' */,
						st._022 /* 1*']' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._2 /* «! » + «value» + «! » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._5 /* «! » + «value» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _007
				= /* { '[' lowerBound=LOWER { '..' upperBound=UPPER }[?] isNullFree='|1'[?] ']' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(56, /* Base::MultiplicityCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._116 /* assign V1 = |isNullFree.'|1'| */,
						ms._100 /* assign V0 = |upperBound| */,
						ms._001 /* assert (|lowerBound| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-7-steps */,
						st._021 /* 1*'[' */,
						st._068 /* 1*lowerBound=LOWER */,
						st._169 /* V00*next-2-steps */,
						st._008 /* 1*'..' */,
						st._160 /* 1*upperBound=UPPER */,
						st._180 /* V01*'|1' */,
						st._022 /* 1*']' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._2 /* «! » + «value» + «! » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._5 /* «! » + «value» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _008
				= /* stringBounds={'*|+|?'} */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(57, /* Base::MultiplicityStringCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._054 /* assert (|stringBounds.'*|+|?'| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._157 /* 1*stringBounds */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						ss._8 /* «? » + «value» + «? » */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _009
				= /* referredElement=UnreservedName */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(67, /* Base::NextPathElementCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._049 /* assert (|referredElement| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._149 /* 1*referredElement=UnreservedName */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						ss._8 /* «? » + «value» + «? » */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _010
				= /* { ownedPathElements+=FirstPathElementCS { '::' ownedPathElements+=NextPathElementCS }[*] } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(73, /* Base::PathNameCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._359 /* check-rule basecs::PathNameCS.ownedPathElements : NextPathElementCS|FirstPathElementCS */,
						ms._065 /* assign V0 = (|ownedPathElements| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-4-steps */,
						st._117 /* 1*ownedPathElements+=FirstPathElementCS */,
						st._169 /* V00*next-2-steps */,
						st._010 /* 1*'::' */,
						st._118 /* 1*ownedPathElements+=NextPathElementCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						null,
						null,
						ss._2 /* «! » + «value» + «! » */,
						null
					}
				);
			private final /*@NonNull*/ RTSerializationRule _011
				= /* { ownedSubstitutions+=TemplateParameterSubstitutionCS { ',' ownedSubstitutions+=TemplateParameterSubstitutionCS }[*] ownedMultiplicity=MultiplicityCS[?] } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(99, /* Base::TemplateBindingCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._385 /* check-rule basecs::TemplateBindingCS.ownedMultiplicity : MultiplicityCS */,
						ms._386 /* check-rule basecs::TemplateBindingCS.ownedSubstitutions : TemplateParameterSubstitutionCS */,
						ms._124 /* assign V1 = |ownedMultiplicity| */,
						ms._066 /* assign V0 = (|ownedSubstitutions| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-5-steps */,
						st._130 /* 1*ownedSubstitutions+=TemplateParameterSubstitutionCS */,
						st._169 /* V00*next-2-steps */,
						st._007 /* 1*',' */,
						st._130 /* 1*ownedSubstitutions+=TemplateParameterSubstitutionCS */,
						st._186 /* V01*ownedMultiplicity=MultiplicityCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						null
					}
				);
			private final /*@NonNull*/ RTSerializationRule _012
				= /* ownedActualParameter=TypeRefCS */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(100, /* Base::TemplateParameterSubstitutionCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._387 /* check-rule basecs::TemplateParameterSubstitutionCS.ownedActualParameter : TypeRefCS */,
						ms._007 /* assert (|ownedActualParameter| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._079 /* 1*ownedActualParameter=TypeRefCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null
					}
				);
			private final /*@NonNull*/ RTSerializationRule _013
				= /* { name=UnrestrictedName { 'extends' ownedExtends+=TypedRefCS { '&&' ownedExtends+=TypedRefCS }[*] }[?] } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(114, /* Base::TypeParameterCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._435 /* check-rule basecs::TypeParameterCS.ownedExtends : TypedRefCS */,
						ms._005 /* assert (|name| - 1) == 0 */,
						ms._058 /* assign V0 = (|ownedExtends| > 0) */,
						ms._105 /* assign V1 = (|ownedExtends| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-7-steps */,
						st._074 /* 1*name=UnrestrictedName */,
						st._169 /* V00*next-5-steps */,
						st._035 /* 1*'extends' */,
						st._095 /* 1*ownedExtends+=TypedRefCS */,
						st._181 /* V01*next-2-steps */,
						st._002 /* 1*'&&' */,
						st._095 /* 1*ownedExtends+=TypedRefCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null
					}
				);
			private final /*@NonNull*/ RTSerializationRule _014
				= /* { '?' { 'extends' ownedExtends=TypedRefCS }[?] } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(129, /* Base::WildcardTypeRefCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._501 /* check-rule basecs::WildcardTypeRefCS.ownedExtends : TypedRefCS */,
						ms._081 /* assign V0 = |ownedExtends| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-4-steps */,
						st._016 /* 1*'?' */,
						st._169 /* V00*next-2-steps */,
						st._035 /* 1*'extends' */,
						st._096 /* 1*ownedExtends=TypedRefCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null
					}
				);
			private final /*@NonNull*/ RTSerializationRule _015
				= /* { '?' { 'extends' ownedExtends=TypedRefCS }[?] } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(129, /* Base::WildcardTypeRefCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._502 /* check-rule basecs::WildcardTypeRefCS.ownedExtends : TypedRefCS */,
						ms._081 /* assign V0 = |ownedExtends| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-4-steps */,
						st._016 /* 1*'?' */,
						st._169 /* V00*next-2-steps */,
						st._035 /* 1*'extends' */,
						st._096 /* 1*ownedExtends=TypedRefCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null
					}
				);
			private final /*@NonNull*/ RTSerializationRule _016
				= /* symbol={'false|true'} */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(5, /* EssentialOCL::BooleanLiteralExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._055 /* assert (|symbol.'false|true'| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._158 /* 1*symbol */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						ss._8 /* «? » + «value» + «? » */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _017
				= /* { name=UnrestrictedName { ':' ownedType=TypeExpCS }[?] } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(7, /* EssentialOCL::CoIteratorVariableCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._643 /* check-rule essentialoclcs::VariableCS.ownedType : TypeExpCS */,
						ms._091 /* assign V0 = |ownedType| */,
						ms._005 /* assert (|name| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-4-steps */,
						st._074 /* 1*name=UnrestrictedName */,
						st._169 /* V00*next-2-steps */,
						st._009 /* 1*':' */,
						st._139 /* 1*ownedType=TypeExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null
					}
				);
			private final /*@NonNull*/ RTSerializationRule _018
				= /* { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(8, /* EssentialOCL::CollectionLiteralExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._517 /* check-rule essentialoclcs::CollectionLiteralExpCS.ownedType : CollectionTypeCS */,
						ms._515 /* check-rule essentialoclcs::CollectionLiteralExpCS.ownedParts : CollectionLiteralPartCS */,
						ms._037 /* assert (|ownedType| - 1) == 0 */,
						ms._061 /* assign V0 = (|ownedParts| > 0) */,
						ms._108 /* assign V1 = (|ownedParts| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-8-steps */,
						st._136 /* 1*ownedType=CollectionTypeCS */,
						st._060 /* 1*'{' */,
						st._169 /* V00*next-4-steps */,
						st._111 /* 1*ownedParts+=CollectionLiteralPartCS */,
						st._181 /* V01*next-2-steps */,
						st._007 /* 1*',' */,
						st._111 /* 1*ownedParts+=CollectionLiteralPartCS */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _019
				= /* ownedExpression=PatternExpCS */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(9, /* EssentialOCL::CollectionLiteralPartCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._520 /* check-rule essentialoclcs::CollectionLiteralPartCS.ownedExpression : PatternExpCS */,
						ms._015 /* assert (|ownedExpression| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._093 /* 1*ownedExpression=PatternExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null
					}
				);
			private final /*@NonNull*/ RTSerializationRule _020
				= /* { ownedExpression=ExpCS { '..' ownedLastExpression=ExpCS }[?] } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(9, /* EssentialOCL::CollectionLiteralPartCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._521 /* check-rule essentialoclcs::CollectionLiteralPartCS.ownedLastExpression : ExpCS */,
						ms._519 /* check-rule essentialoclcs::CollectionLiteralPartCS.ownedExpression : ExpCS */,
						ms._084 /* assign V0 = |ownedLastExpression| */,
						ms._015 /* assert (|ownedExpression| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-4-steps */,
						st._091 /* 1*ownedExpression=ExpCS */,
						st._169 /* V00*next-2-steps */,
						st._008 /* 1*'..' */,
						st._105 /* 1*ownedLastExpression=ExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null
					}
				);
			private final /*@NonNull*/ RTSerializationRule _021
				= /* { ownedType=CollectionTypeCS '{' { ownedParts+=PatternExpCS { ',' ownedParts+=PatternExpCS }[*] '++' restVariableName=Identifier }[?] '}' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(10, /* EssentialOCL::CollectionPatternCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._524 /* check-rule essentialoclcs::CollectionPatternCS.ownedType : CollectionTypeCS */,
						ms._522 /* check-rule essentialoclcs::CollectionPatternCS.ownedParts : PatternExpCS */,
						ms._098 /* assign V0 = |restVariableName| */,
						ms._107 /* assign V1 = (|ownedParts| - 1) */,
						ms._041 /* assert (|ownedType| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-10-steps */,
						st._135 /* 1*ownedType=CollectionTypeCS */,
						st._060 /* 1*'{' */,
						st._169 /* V00*next-6-steps */,
						st._113 /* 1*ownedParts+=PatternExpCS */,
						st._181 /* V01*next-2-steps */,
						st._007 /* 1*',' */,
						st._113 /* 1*ownedParts+=PatternExpCS */,
						st._006 /* 1*'++' */,
						st._156 /* 1*restVariableName=Identifier */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _022
				= /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(11, /* EssentialOCL::CollectionTypeCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._527 /* check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : MultiplicityCS */,
						ms._532 /* check-rule essentialoclcs::CollectionTypeCS.ownedType : TypeExpWithoutMultiplicityCS */,
						ms._090 /* assign V0 = |ownedType| */,
						ms._004 /* assert (|name| - 1) == 0 */,
						ms._120 /* assign V1 = |ownedCollectionMultiplicity| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-6-steps */,
						st._070 /* 1*name=CollectionTypeIdentifier */,
						st._169 /* V00*next-4-steps */,
						st._003 /* 1*'(' */,
						st._141 /* 1*ownedType=TypeExpWithoutMultiplicityCS */,
						st._183 /* V01*ownedCollectionMultiplicity=MultiplicityCS */,
						st._004 /* 1*')' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._2 /* «! » + «value» + «! » */,
						null,
						null,
						ss._5 /* «! » + «value» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _023
				= /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(11, /* EssentialOCL::CollectionTypeCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._526 /* check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : MultiplicityCS */,
						ms._531 /* check-rule essentialoclcs::CollectionTypeCS.ownedType : TypeExpWithoutMultiplicityCS */,
						ms._090 /* assign V0 = |ownedType| */,
						ms._004 /* assert (|name| - 1) == 0 */,
						ms._120 /* assign V1 = |ownedCollectionMultiplicity| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-6-steps */,
						st._070 /* 1*name=CollectionTypeIdentifier */,
						st._169 /* V00*next-4-steps */,
						st._003 /* 1*'(' */,
						st._141 /* 1*ownedType=TypeExpWithoutMultiplicityCS */,
						st._183 /* V01*ownedCollectionMultiplicity=MultiplicityCS */,
						st._004 /* 1*')' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._2 /* «! » + «value» + «! » */,
						null,
						null,
						ss._5 /* «! » + «value» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _024
				= /* { '{' { ownedParts+=ShadowPartCS { ',' ownedParts+=ShadowPartCS }[*] }[?] '}' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(13, /* EssentialOCL::CurlyBracketedClauseCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._537 /* check-rule essentialoclcs::CurlyBracketedClauseCS.ownedParts : ShadowPartCS */,
						ms._064 /* assign V0 = (|ownedParts| > 0) */,
						ms._109 /* assign V1 = (|ownedParts| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-7-steps */,
						st._060 /* 1*'{' */,
						st._169 /* V00*next-4-steps */,
						st._114 /* 1*ownedParts+=ShadowPartCS */,
						st._181 /* V01*next-2-steps */,
						st._007 /* 1*',' */,
						st._114 /* 1*ownedParts+=ShadowPartCS */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _025
				= /* { 'elseif' ownedCondition=ExpCS 'then' ownedThenExpression=ExpCS } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(20, /* EssentialOCL::ElseIfThenExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._551 /* check-rule essentialoclcs::IfThenExpCS.ownedCondition : ExpCS */,
						ms._552 /* check-rule essentialoclcs::IfThenExpCS.ownedThenExpression : ExpCS */,
						ms._035 /* assert (|ownedThenExpression| - 1) == 0 */,
						ms._010 /* assert (|ownedCondition| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-4-steps */,
						st._032 /* 1*'elseif' */,
						st._083 /* 1*ownedCondition=ExpCS */,
						st._058 /* 1*'then' */,
						st._133 /* 1*ownedThenExpression=ExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null
					}
				);
			private final /*@NonNull*/ RTSerializationRule _026
				= /* { ownedType=CollectionTypeCS '{' { ownedParts+=CollectionLiteralPartCS { ',' ownedParts+=CollectionLiteralPartCS }[*] }[?] '}' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(30, /* EssentialOCL::ExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._518 /* check-rule essentialoclcs::CollectionLiteralExpCS.ownedType : CollectionTypeCS */,
						ms._516 /* check-rule essentialoclcs::CollectionLiteralExpCS.ownedParts : CollectionLiteralPartCS */,
						ms._037 /* assert (|ownedType| - 1) == 0 */,
						ms._061 /* assign V0 = (|ownedParts| > 0) */,
						ms._108 /* assign V1 = (|ownedParts| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-8-steps */,
						st._136 /* 1*ownedType=CollectionTypeCS */,
						st._060 /* 1*'{' */,
						st._169 /* V00*next-4-steps */,
						st._111 /* 1*ownedParts+=CollectionLiteralPartCS */,
						st._181 /* V01*next-2-steps */,
						st._007 /* 1*',' */,
						st._111 /* 1*ownedParts+=CollectionLiteralPartCS */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _027
				= /* 'invalid' */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(30, /* EssentialOCL::ExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._040 /* 1*'invalid' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						ss._8 /* «? » + «value» + «? » */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _028
				= /* symbol=NUMBER_LITERAL */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(30, /* EssentialOCL::ExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._056 /* assert (|symbol| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._159 /* 1*symbol=NUMBER_LITERAL */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						ss._8 /* «? » + «value» + «? » */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _029
				= /* { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(30, /* EssentialOCL::ExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._617 /* check-rule essentialoclcs::OperatorExpCS.ownedRight : PrefixedPrimaryExpCS */,
						ms._034 /* assert (|ownedRight| - 1) == 0 */,
						ms._005 /* assert (|name| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-2-steps */,
						st._073 /* 1*name=UnaryOperatorName */,
						st._129 /* 1*ownedRight=PrefixedPrimaryExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						null
					}
				);
			private final /*@NonNull*/ RTSerializationRule _030
				= /* '*' */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(30, /* EssentialOCL::ExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._005 /* 1*'*' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						ss._8 /* «? » + «value» + «? » */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _031
				= /* { ownedLeft=PrefixedPrimaryExpCS name=BinaryOperatorName ownedRight=ExpCS } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(30, /* EssentialOCL::ExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._553 /* check-rule essentialoclcs::InfixExpCS.ownedLeft : PrefixedPrimaryExpCS */,
						ms._614 /* check-rule essentialoclcs::OperatorExpCS.ownedRight : ExpCS */,
						ms._034 /* assert (|ownedRight| - 1) == 0 */,
						ms._005 /* assert (|name| - 1) == 0 */,
						ms._025 /* assert (|ownedLeft| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-3-steps */,
						st._106 /* 1*ownedLeft=PrefixedPrimaryExpCS */,
						st._069 /* 1*name=BinaryOperatorName */,
						st._127 /* 1*ownedRight=ExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null
					}
				);
			private final /*@NonNull*/ RTSerializationRule _032
				= /* { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(30, /* EssentialOCL::ExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._624 /* check-rule essentialoclcs::TupleLiteralExpCS.ownedParts : TupleLiteralPartCS */,
						ms._060 /* assign V0 = (|ownedParts| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-7-steps */,
						st._020 /* 1*'Tuple' */,
						st._060 /* 1*'{' */,
						st._115 /* 1*ownedParts+=TupleLiteralPartCS */,
						st._169 /* V00*next-2-steps */,
						st._007 /* 1*',' */,
						st._115 /* 1*ownedParts+=TupleLiteralPartCS */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _033
				= /* segments+=StringLiteral[+] */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(30, /* EssentialOCL::ExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._099 /* assign V0 = |segments| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._176 /* V00*segments+=StringLiteral */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						ss._8 /* «? » + «value» + «? » */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _034
				= /* { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(30, /* EssentialOCL::ExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._562 /* check-rule essentialoclcs::MapLiteralExpCS.ownedParts : MapLiteralPartCS */,
						ms._565 /* check-rule essentialoclcs::MapLiteralExpCS.ownedType : MapTypeCS */,
						ms._042 /* assert (|ownedType| - 1) == 0 */,
						ms._062 /* assign V0 = (|ownedParts| > 0) */,
						ms._110 /* assign V1 = (|ownedParts| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-8-steps */,
						st._137 /* 1*ownedType=MapTypeCS */,
						st._060 /* 1*'{' */,
						st._169 /* V00*next-4-steps */,
						st._112 /* 1*ownedParts+=MapLiteralPartCS */,
						st._181 /* V01*next-2-steps */,
						st._007 /* 1*',' */,
						st._112 /* 1*ownedParts+=MapLiteralPartCS */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _035
				= /* { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(30, /* EssentialOCL::ExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._545 /* check-rule essentialoclcs::IfExpCS.ownedIfThenExpressions : ElseIfThenExpCS */,
						ms._539 /* check-rule essentialoclcs::IfExpCS.ownedCondition : ExpCS|PatternExpCS */,
						ms._548 /* check-rule essentialoclcs::IfExpCS.ownedThenExpression : ExpCS */,
						ms._542 /* check-rule essentialoclcs::IfExpCS.ownedElseExpression : ExpCS */,
						ms._013 /* assert (|ownedElseExpression| - 1) == 0 */,
						ms._082 /* assign V0 = |ownedIfThenExpressions| */,
						ms._036 /* assert (|ownedThenExpression| - 1) == 0 */,
						ms._011 /* assert (|ownedCondition| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-8-steps */,
						st._036 /* 1*'if' */,
						st._084 /* 1*ownedCondition=ExpCS|PatternExpCS */,
						st._058 /* 1*'then' */,
						st._134 /* 1*ownedThenExpression=ExpCS */,
						st._170 /* V00*ownedIfThenExpressions+=ElseIfThenExpCS */,
						st._031 /* 1*'else' */,
						st._087 /* 1*ownedElseExpression=ExpCS */,
						st._033 /* 1*'endif' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _036
				= /* ownedType=TypeLiteralWithMultiplicityCS */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(30, /* EssentialOCL::ExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._627 /* check-rule essentialoclcs::TypeLiteralExpCS.ownedType : TypeLiteralWithMultiplicityCS */,
						ms._038 /* assert (|ownedType| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._142 /* 1*ownedType=TypeLiteralWithMultiplicityCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null
					}
				);
			private final /*@NonNull*/ RTSerializationRule _037
				= /* { '(' ownedExpression=ExpCS ')' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(30, /* EssentialOCL::ExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._611 /* check-rule essentialoclcs::NestedExpCS.ownedExpression : ExpCS */,
						ms._018 /* assert (|ownedExpression| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-3-steps */,
						st._003 /* 1*'(' */,
						st._089 /* 1*ownedExpression=ExpCS */,
						st._004 /* 1*')' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._2 /* «! » + «value» + «! » */,
						null,
						ss._5 /* «! » + «value» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _038
				= /* { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(30, /* EssentialOCL::ExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._512 /* check-rule essentialoclcs::AbstractNameExpCS.ownedSquareBracketedClauses : SquareBracketedClauseCS */,
						ms._503 /* check-rule essentialoclcs::AbstractNameExpCS.ownedCurlyBracketedClause : CurlyBracketedClauseCS */,
						ms._509 /* check-rule essentialoclcs::AbstractNameExpCS.ownedRoundBracketedClause : RoundBracketedClauseCS */,
						ms._506 /* check-rule essentialoclcs::AbstractNameExpCS.ownedPathName : PathNameCS */,
						ms._174 /* assign V3 = |isPre.'@'| */,
						ms._159 /* assign V2 = |ownedCurlyBracketedClause| */,
						ms._127 /* assign V1 = |ownedRoundBracketedClause| */,
						ms._088 /* assign V0 = |ownedSquareBracketedClauses| */,
						ms._030 /* assert (|ownedPathName| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-7-steps */,
						st._122 /* 1*ownedPathName=PathNameCS */,
						st._174 /* V00*ownedSquareBracketedClauses+=SquareBracketedClauseCS */,
						st._187 /* V01*ownedRoundBracketedClause=RoundBracketedClauseCS */,
						st._191 /* V02*ownedCurlyBracketedClause=CurlyBracketedClauseCS */,
						st._196 /* V03*next-2-steps */,
						st._017 /* 1*'@' */,
						st._051 /* 1*'pre' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						null,
						null,
						null,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _039
				= /* 'null' */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(30, /* EssentialOCL::ExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._046 /* 1*'null' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						ss._8 /* «? » + «value» + «? » */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _040
				= /* { 'Lambda' '{' ownedExpressionCS=ExpCS '}' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(30, /* EssentialOCL::ExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._554 /* check-rule essentialoclcs::LambdaLiteralExpCS.ownedExpressionCS : ExpCS */,
						ms._014 /* assert (|ownedExpressionCS| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-4-steps */,
						st._018 /* 1*'Lambda' */,
						st._060 /* 1*'{' */,
						st._094 /* 1*ownedExpressionCS=ExpCS */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _041
				= /* 'self' */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(30, /* EssentialOCL::ExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._055 /* 1*'self' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						ss._8 /* «? » + «value» + «? » */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _042
				= /* symbol={'false|true'} */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(30, /* EssentialOCL::ExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._055 /* assert (|symbol.'false|true'| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._158 /* 1*symbol */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						ss._8 /* «? » + «value» + «? » */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _043
				= /* { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(36, /* EssentialOCL::IfExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._547 /* check-rule essentialoclcs::IfExpCS.ownedIfThenExpressions : ElseIfThenExpCS */,
						ms._541 /* check-rule essentialoclcs::IfExpCS.ownedCondition : ExpCS|PatternExpCS */,
						ms._550 /* check-rule essentialoclcs::IfExpCS.ownedThenExpression : ExpCS */,
						ms._544 /* check-rule essentialoclcs::IfExpCS.ownedElseExpression : ExpCS */,
						ms._013 /* assert (|ownedElseExpression| - 1) == 0 */,
						ms._082 /* assign V0 = |ownedIfThenExpressions| */,
						ms._036 /* assert (|ownedThenExpression| - 1) == 0 */,
						ms._011 /* assert (|ownedCondition| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-8-steps */,
						st._036 /* 1*'if' */,
						st._084 /* 1*ownedCondition=ExpCS|PatternExpCS */,
						st._058 /* 1*'then' */,
						st._134 /* 1*ownedThenExpression=ExpCS */,
						st._170 /* V00*ownedIfThenExpressions+=ElseIfThenExpCS */,
						st._031 /* 1*'else' */,
						st._087 /* 1*ownedElseExpression=ExpCS */,
						st._033 /* 1*'endif' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _044
				= /* { 'if' ownedCondition=(ExpCS|PatternExpCS) 'then' ownedThenExpression=ExpCS ownedIfThenExpressions+=ElseIfThenExpCS[*] 'else' ownedElseExpression=ExpCS 'endif' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(36, /* EssentialOCL::IfExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._546 /* check-rule essentialoclcs::IfExpCS.ownedIfThenExpressions : ElseIfThenExpCS */,
						ms._540 /* check-rule essentialoclcs::IfExpCS.ownedCondition : ExpCS|PatternExpCS */,
						ms._549 /* check-rule essentialoclcs::IfExpCS.ownedThenExpression : ExpCS */,
						ms._543 /* check-rule essentialoclcs::IfExpCS.ownedElseExpression : ExpCS */,
						ms._013 /* assert (|ownedElseExpression| - 1) == 0 */,
						ms._082 /* assign V0 = |ownedIfThenExpressions| */,
						ms._036 /* assert (|ownedThenExpression| - 1) == 0 */,
						ms._011 /* assert (|ownedCondition| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-8-steps */,
						st._036 /* 1*'if' */,
						st._084 /* 1*ownedCondition=ExpCS|PatternExpCS */,
						st._058 /* 1*'then' */,
						st._134 /* 1*ownedThenExpression=ExpCS */,
						st._170 /* V00*ownedIfThenExpressions+=ElseIfThenExpCS */,
						st._031 /* 1*'else' */,
						st._087 /* 1*ownedElseExpression=ExpCS */,
						st._033 /* 1*'endif' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _045
				= /* 'invalid' */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(40, /* EssentialOCL::InvalidLiteralExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._040 /* 1*'invalid' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						ss._8 /* «? » + «value» + «? » */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _046
				= /* 'invalid' */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(40, /* EssentialOCL::InvalidLiteralExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._040 /* 1*'invalid' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						ss._8 /* «? » + «value» + «? » */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _047
				= /* { 'Lambda' '{' ownedExpressionCS=ExpCS '}' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(44, /* EssentialOCL::LambdaLiteralExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._556 /* check-rule essentialoclcs::LambdaLiteralExpCS.ownedExpressionCS : ExpCS */,
						ms._014 /* assert (|ownedExpressionCS| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-4-steps */,
						st._018 /* 1*'Lambda' */,
						st._060 /* 1*'{' */,
						st._094 /* 1*ownedExpressionCS=ExpCS */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _048
				= /* { 'Lambda' '{' ownedExpressionCS=ExpCS '}' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(44, /* EssentialOCL::LambdaLiteralExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._555 /* check-rule essentialoclcs::LambdaLiteralExpCS.ownedExpressionCS : ExpCS */,
						ms._014 /* assert (|ownedExpressionCS| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-4-steps */,
						st._018 /* 1*'Lambda' */,
						st._060 /* 1*'{' */,
						st._094 /* 1*ownedExpressionCS=ExpCS */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _049
				= /* { 'let' ownedVariables+=LetVariableCS { ',' ownedVariables+=LetVariableCS }[*] 'in' ownedInExpression=ExpCS } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(45, /* EssentialOCL::LetExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._560 /* check-rule essentialoclcs::LetExpCS.ownedVariables : LetVariableCS */,
						ms._558 /* check-rule essentialoclcs::LetExpCS.ownedInExpression : ExpCS */,
						ms._019 /* assert (|ownedInExpression| - 1) == 0 */,
						ms._068 /* assign V0 = (|ownedVariables| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-7-steps */,
						st._043 /* 1*'let' */,
						st._146 /* 1*ownedVariables+=LetVariableCS */,
						st._169 /* V00*next-2-steps */,
						st._007 /* 1*',' */,
						st._146 /* 1*ownedVariables+=LetVariableCS */,
						st._038 /* 1*'in' */,
						st._098 /* 1*ownedInExpression=ExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null
					}
				);
			private final /*@NonNull*/ RTSerializationRule _050
				= /* { 'let' ownedVariables+=LetVariableCS { ',' ownedVariables+=LetVariableCS }[*] 'in' ownedInExpression=ExpCS } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(45, /* EssentialOCL::LetExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._559 /* check-rule essentialoclcs::LetExpCS.ownedVariables : LetVariableCS */,
						ms._557 /* check-rule essentialoclcs::LetExpCS.ownedInExpression : ExpCS */,
						ms._019 /* assert (|ownedInExpression| - 1) == 0 */,
						ms._068 /* assign V0 = (|ownedVariables| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-7-steps */,
						st._043 /* 1*'let' */,
						st._146 /* 1*ownedVariables+=LetVariableCS */,
						st._169 /* V00*next-2-steps */,
						st._007 /* 1*',' */,
						st._146 /* 1*ownedVariables+=LetVariableCS */,
						st._038 /* 1*'in' */,
						st._098 /* 1*ownedInExpression=ExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null
					}
				);
			private final /*@NonNull*/ RTSerializationRule _051
				= /* { name=UnrestrictedName ownedRoundBracketedClause=RoundBracketedClauseCS[?] { ':' ownedType=TypeExpCS }[?] '=' ownedInitExpression=ExpCS } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(46, /* EssentialOCL::LetVariableCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._561 /* check-rule essentialoclcs::LetVariableCS.ownedRoundBracketedClause : RoundBracketedClauseCS */,
						ms._641 /* check-rule essentialoclcs::VariableCS.ownedType : TypeExpCS */,
						ms._639 /* check-rule essentialoclcs::VariableCS.ownedInitExpression : ExpCS */,
						ms._020 /* assert (|ownedInitExpression| - 1) == 0 */,
						ms._129 /* assign V1 = |ownedType| */,
						ms._086 /* assign V0 = |ownedRoundBracketedClause| */,
						ms._005 /* assert (|name| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-7-steps */,
						st._074 /* 1*name=UnrestrictedName */,
						st._172 /* V00*ownedRoundBracketedClause=RoundBracketedClauseCS */,
						st._181 /* V01*next-2-steps */,
						st._009 /* 1*':' */,
						st._139 /* 1*ownedType=TypeExpCS */,
						st._014 /* 1*'=' */,
						st._100 /* 1*ownedInitExpression=ExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null
					}
				);
			private final /*@NonNull*/ RTSerializationRule _052
				= /* { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(49, /* EssentialOCL::MapLiteralExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._564 /* check-rule essentialoclcs::MapLiteralExpCS.ownedParts : MapLiteralPartCS */,
						ms._566 /* check-rule essentialoclcs::MapLiteralExpCS.ownedType : MapTypeCS */,
						ms._042 /* assert (|ownedType| - 1) == 0 */,
						ms._062 /* assign V0 = (|ownedParts| > 0) */,
						ms._110 /* assign V1 = (|ownedParts| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-8-steps */,
						st._137 /* 1*ownedType=MapTypeCS */,
						st._060 /* 1*'{' */,
						st._169 /* V00*next-4-steps */,
						st._112 /* 1*ownedParts+=MapLiteralPartCS */,
						st._181 /* V01*next-2-steps */,
						st._007 /* 1*',' */,
						st._112 /* 1*ownedParts+=MapLiteralPartCS */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _053
				= /* { ownedType=MapTypeCS '{' { ownedParts+=MapLiteralPartCS { ',' ownedParts+=MapLiteralPartCS }[*] }[?] '}' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(49, /* EssentialOCL::MapLiteralExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._563 /* check-rule essentialoclcs::MapLiteralExpCS.ownedParts : MapLiteralPartCS */,
						ms._567 /* check-rule essentialoclcs::MapLiteralExpCS.ownedType : MapTypeCS */,
						ms._042 /* assert (|ownedType| - 1) == 0 */,
						ms._062 /* assign V0 = (|ownedParts| > 0) */,
						ms._110 /* assign V1 = (|ownedParts| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-8-steps */,
						st._137 /* 1*ownedType=MapTypeCS */,
						st._060 /* 1*'{' */,
						st._169 /* V00*next-4-steps */,
						st._112 /* 1*ownedParts+=MapLiteralPartCS */,
						st._181 /* V01*next-2-steps */,
						st._007 /* 1*',' */,
						st._112 /* 1*ownedParts+=MapLiteralPartCS */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _054
				= /* { ownedKey=ExpCS '<-' ownedValue=ExpCS } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(50, /* EssentialOCL::MapLiteralPartCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._568 /* check-rule essentialoclcs::MapLiteralPartCS.ownedKey : ExpCS */,
						ms._569 /* check-rule essentialoclcs::MapLiteralPartCS.ownedValue : ExpCS */,
						ms._043 /* assert (|ownedValue| - 1) == 0 */,
						ms._024 /* assert (|ownedKey| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-3-steps */,
						st._103 /* 1*ownedKey=ExpCS */,
						st._013 /* 1*'<-' */,
						st._144 /* 1*ownedValue=ExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null
					}
				);
			private final /*@NonNull*/ RTSerializationRule _055
				= /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(51, /* EssentialOCL::MapTypeCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._570 /* check-rule essentialoclcs::MapTypeCS.ownedKeyType : TypeExpCS */,
						ms._575 /* check-rule essentialoclcs::MapTypeCS.ownedValueType : TypeExpCS */,
						ms._093 /* assign V0 = |ownedValueType| */,
						ms._023 /* assert (|ownedKeyType| - V0) == 0 */,
						ms._002 /* assert (|name.'Map'| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-7-steps */,
						st._019 /* 1*'Map' */,
						st._169 /* V00*next-5-steps */,
						st._003 /* 1*'(' */,
						st._104 /* 1*ownedKeyType=TypeExpCS */,
						st._007 /* 1*',' */,
						st._145 /* 1*ownedValueType=TypeExpCS */,
						st._004 /* 1*')' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._2 /* «! » + «value» + «! » */,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						ss._5 /* «! » + «value» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _056
				= /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(51, /* EssentialOCL::MapTypeCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._571 /* check-rule essentialoclcs::MapTypeCS.ownedKeyType : TypeExpCS */,
						ms._576 /* check-rule essentialoclcs::MapTypeCS.ownedValueType : TypeExpCS */,
						ms._093 /* assign V0 = |ownedValueType| */,
						ms._023 /* assert (|ownedKeyType| - V0) == 0 */,
						ms._002 /* assert (|name.'Map'| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-7-steps */,
						st._019 /* 1*'Map' */,
						st._169 /* V00*next-5-steps */,
						st._003 /* 1*'(' */,
						st._104 /* 1*ownedKeyType=TypeExpCS */,
						st._007 /* 1*',' */,
						st._145 /* 1*ownedValueType=TypeExpCS */,
						st._004 /* 1*')' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._2 /* «! » + «value» + «! » */,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						ss._5 /* «! » + «value» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _057
				= /* ownedExpression=ExpCS */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(52, /* EssentialOCL::Model */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._536 /* check-rule essentialoclcs::ContextCS.ownedExpression : ExpCS */,
						ms._016 /* assert (|ownedExpression| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._090 /* 1*ownedExpression=ExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null
					}
				);
			private final /*@NonNull*/ RTSerializationRule _058
				= /* { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(59, /* EssentialOCL::NameExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._514 /* check-rule essentialoclcs::AbstractNameExpCS.ownedSquareBracketedClauses : SquareBracketedClauseCS */,
						ms._505 /* check-rule essentialoclcs::AbstractNameExpCS.ownedCurlyBracketedClause : CurlyBracketedClauseCS */,
						ms._511 /* check-rule essentialoclcs::AbstractNameExpCS.ownedRoundBracketedClause : RoundBracketedClauseCS */,
						ms._508 /* check-rule essentialoclcs::AbstractNameExpCS.ownedPathName : PathNameCS */,
						ms._174 /* assign V3 = |isPre.'@'| */,
						ms._159 /* assign V2 = |ownedCurlyBracketedClause| */,
						ms._127 /* assign V1 = |ownedRoundBracketedClause| */,
						ms._088 /* assign V0 = |ownedSquareBracketedClauses| */,
						ms._030 /* assert (|ownedPathName| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-7-steps */,
						st._122 /* 1*ownedPathName=PathNameCS */,
						st._174 /* V00*ownedSquareBracketedClauses+=SquareBracketedClauseCS */,
						st._187 /* V01*ownedRoundBracketedClause=RoundBracketedClauseCS */,
						st._191 /* V02*ownedCurlyBracketedClause=CurlyBracketedClauseCS */,
						st._196 /* V03*next-2-steps */,
						st._017 /* 1*'@' */,
						st._051 /* 1*'pre' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						null,
						null,
						null,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _059
				= /* { ownedPathName=PathNameCS ownedSquareBracketedClauses+=SquareBracketedClauseCS[*] ownedRoundBracketedClause=RoundBracketedClauseCS[?] ownedCurlyBracketedClause=CurlyBracketedClauseCS[?] { isPre='@' 'pre' }[?] } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(59, /* EssentialOCL::NameExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._513 /* check-rule essentialoclcs::AbstractNameExpCS.ownedSquareBracketedClauses : SquareBracketedClauseCS */,
						ms._504 /* check-rule essentialoclcs::AbstractNameExpCS.ownedCurlyBracketedClause : CurlyBracketedClauseCS */,
						ms._510 /* check-rule essentialoclcs::AbstractNameExpCS.ownedRoundBracketedClause : RoundBracketedClauseCS */,
						ms._507 /* check-rule essentialoclcs::AbstractNameExpCS.ownedPathName : PathNameCS */,
						ms._174 /* assign V3 = |isPre.'@'| */,
						ms._159 /* assign V2 = |ownedCurlyBracketedClause| */,
						ms._127 /* assign V1 = |ownedRoundBracketedClause| */,
						ms._088 /* assign V0 = |ownedSquareBracketedClauses| */,
						ms._030 /* assert (|ownedPathName| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-7-steps */,
						st._122 /* 1*ownedPathName=PathNameCS */,
						st._174 /* V00*ownedSquareBracketedClauses+=SquareBracketedClauseCS */,
						st._187 /* V01*ownedRoundBracketedClause=RoundBracketedClauseCS */,
						st._191 /* V02*ownedCurlyBracketedClause=CurlyBracketedClauseCS */,
						st._196 /* V03*next-2-steps */,
						st._017 /* 1*'@' */,
						st._051 /* 1*'pre' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						null,
						null,
						null,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _060
				= /* { ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS }[?] { '<-' ownedCoIterator=CoIteratorVariableCS }[?] 'in' ownedInitExpression=ExpCS } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(60, /* EssentialOCL::NavigatingArgCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._598 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : NavigatingArgExpCS */,
						ms._582 /* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : CoIteratorVariableCS */,
						ms._606 /* check-rule essentialoclcs::NavigatingArgCS.ownedType : TypeExpCS */,
						ms._590 /* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : ExpCS */,
						ms._022 /* assert (|ownedInitExpression| - 1) == 0 */,
						ms._119 /* assign V1 = |ownedCoIterator| */,
						ms._089 /* assign V0 = |ownedType| */,
						ms._026 /* assert (|ownedNameExpression| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-9-steps */,
						st._108 /* 1*ownedNameExpression=NavigatingArgExpCS */,
						st._169 /* V00*next-2-steps */,
						st._009 /* 1*':' */,
						st._138 /* 1*ownedType=TypeExpCS */,
						st._181 /* V01*next-2-steps */,
						st._013 /* 1*'<-' */,
						st._082 /* 1*ownedCoIterator=CoIteratorVariableCS */,
						st._038 /* 1*'in' */,
						st._099 /* 1*ownedInitExpression=ExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null
					}
				);
			private final /*@NonNull*/ RTSerializationRule _061
				= /* ownedNameExpression=NavigatingArgExpCS */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(60, /* EssentialOCL::NavigatingArgCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._595 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : NavigatingArgExpCS */,
						ms._026 /* assert (|ownedNameExpression| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._108 /* 1*ownedNameExpression=NavigatingArgExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null
					}
				);
			private final /*@NonNull*/ RTSerializationRule _062
				= /* { ':' ownedType=TypeExpCS } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(60, /* EssentialOCL::NavigatingArgCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._604 /* check-rule essentialoclcs::NavigatingArgCS.ownedType : TypeExpCS */,
						ms._040 /* assert (|ownedType| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-2-steps */,
						st._009 /* 1*':' */,
						st._138 /* 1*ownedType=TypeExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						null
					}
				);
			private final /*@NonNull*/ RTSerializationRule _063
				= /* { ownedNameExpression=NavigatingArgExpCS ':' ownedType=TypeExpCS { '<-' ownedCoIterator=CoIteratorVariableCS }[?] { '=' ownedInitExpression=ExpCS }[?] } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(60, /* EssentialOCL::NavigatingArgCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._597 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : NavigatingArgExpCS */,
						ms._581 /* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : CoIteratorVariableCS */,
						ms._605 /* check-rule essentialoclcs::NavigatingArgCS.ownedType : TypeExpCS */,
						ms._589 /* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : ExpCS */,
						ms._122 /* assign V1 = |ownedInitExpression| */,
						ms._078 /* assign V0 = |ownedCoIterator| */,
						ms._040 /* assert (|ownedType| - 1) == 0 */,
						ms._026 /* assert (|ownedNameExpression| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-9-steps */,
						st._108 /* 1*ownedNameExpression=NavigatingArgExpCS */,
						st._009 /* 1*':' */,
						st._138 /* 1*ownedType=TypeExpCS */,
						st._169 /* V00*next-2-steps */,
						st._013 /* 1*'<-' */,
						st._082 /* 1*ownedCoIterator=CoIteratorVariableCS */,
						st._181 /* V01*next-2-steps */,
						st._014 /* 1*'=' */,
						st._099 /* 1*ownedInitExpression=ExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null
					}
				);
			private final /*@NonNull*/ RTSerializationRule _064
				= /* { ownedNameExpression=NavigatingArgExpCS '<-' ownedCoIterator=CoIteratorVariableCS { '=' ownedInitExpression=ExpCS }[?] } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(60, /* EssentialOCL::NavigatingArgCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._596 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : NavigatingArgExpCS */,
						ms._580 /* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : CoIteratorVariableCS */,
						ms._587 /* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : ExpCS */,
						ms._083 /* assign V0 = |ownedInitExpression| */,
						ms._009 /* assert (|ownedCoIterator| - 1) == 0 */,
						ms._026 /* assert (|ownedNameExpression| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-6-steps */,
						st._108 /* 1*ownedNameExpression=NavigatingArgExpCS */,
						st._013 /* 1*'<-' */,
						st._082 /* 1*ownedCoIterator=CoIteratorVariableCS */,
						st._169 /* V00*next-2-steps */,
						st._014 /* 1*'=' */,
						st._099 /* 1*ownedInitExpression=ExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null
					}
				);
			private final /*@NonNull*/ RTSerializationRule _065
				= /* { prefix='|' ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS { '=' ownedInitExpression=ExpCS }[?] }[?] } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(62, /* EssentialOCL::NavigatingBarArgCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._599 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : NavigatingArgExpCS */,
						ms._607 /* check-rule essentialoclcs::NavigatingArgCS.ownedType : TypeExpCS */,
						ms._586 /* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : ExpCS */,
						ms._089 /* assign V0 = |ownedType| */,
						ms._026 /* assert (|ownedNameExpression| - 1) == 0 */,
						ms._046 /* assert (|prefix.'|'| - 1) == 0 */,
						ms._122 /* assign V1 = |ownedInitExpression| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-8-steps */,
						st._061 /* 1*'|' */,
						st._108 /* 1*ownedNameExpression=NavigatingArgExpCS */,
						st._169 /* V00*next-5-steps */,
						st._009 /* 1*':' */,
						st._138 /* 1*ownedType=TypeExpCS */,
						st._181 /* V01*next-2-steps */,
						st._014 /* 1*'=' */,
						st._099 /* 1*ownedInitExpression=ExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null
					}
				);
			private final /*@NonNull*/ RTSerializationRule _066
				= /* { prefix=',' ownedNameExpression=NavigatingArgExpCS } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(63, /* EssentialOCL::NavigatingCommaArgCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._600 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : NavigatingArgExpCS */,
						ms._026 /* assert (|ownedNameExpression| - 1) == 0 */,
						ms._044 /* assert (|prefix.','| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-2-steps */,
						st._007 /* 1*',' */,
						st._108 /* 1*ownedNameExpression=NavigatingArgExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._3 /* «! » + «value» + «? » */,
						null
					}
				);
			private final /*@NonNull*/ RTSerializationRule _067
				= /* { prefix=',' ownedNameExpression=NavigatingArgExpCS ':' ownedType=TypeExpCS { '<-' ownedCoIterator=CoIteratorVariableCS }[?] { '=' ownedInitExpression=ExpCS }[?] } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(63, /* EssentialOCL::NavigatingCommaArgCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._601 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : NavigatingArgExpCS */,
						ms._585 /* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : CoIteratorVariableCS */,
						ms._608 /* check-rule essentialoclcs::NavigatingArgCS.ownedType : TypeExpCS */,
						ms._593 /* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : ExpCS */,
						ms._122 /* assign V1 = |ownedInitExpression| */,
						ms._078 /* assign V0 = |ownedCoIterator| */,
						ms._040 /* assert (|ownedType| - 1) == 0 */,
						ms._026 /* assert (|ownedNameExpression| - 1) == 0 */,
						ms._044 /* assert (|prefix.','| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-10-steps */,
						st._007 /* 1*',' */,
						st._108 /* 1*ownedNameExpression=NavigatingArgExpCS */,
						st._009 /* 1*':' */,
						st._138 /* 1*ownedType=TypeExpCS */,
						st._169 /* V00*next-2-steps */,
						st._013 /* 1*'<-' */,
						st._082 /* 1*ownedCoIterator=CoIteratorVariableCS */,
						st._181 /* V01*next-2-steps */,
						st._014 /* 1*'=' */,
						st._099 /* 1*ownedInitExpression=ExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null
					}
				);
			private final /*@NonNull*/ RTSerializationRule _068
				= /* { prefix=',' ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS }[?] { '<-' ownedCoIterator=CoIteratorVariableCS }[?] 'in' ownedInitExpression=ExpCS } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(63, /* EssentialOCL::NavigatingCommaArgCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._603 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : NavigatingArgExpCS */,
						ms._584 /* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : CoIteratorVariableCS */,
						ms._609 /* check-rule essentialoclcs::NavigatingArgCS.ownedType : TypeExpCS */,
						ms._591 /* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : ExpCS */,
						ms._022 /* assert (|ownedInitExpression| - 1) == 0 */,
						ms._119 /* assign V1 = |ownedCoIterator| */,
						ms._089 /* assign V0 = |ownedType| */,
						ms._026 /* assert (|ownedNameExpression| - 1) == 0 */,
						ms._044 /* assert (|prefix.','| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-10-steps */,
						st._007 /* 1*',' */,
						st._108 /* 1*ownedNameExpression=NavigatingArgExpCS */,
						st._169 /* V00*next-2-steps */,
						st._009 /* 1*':' */,
						st._138 /* 1*ownedType=TypeExpCS */,
						st._181 /* V01*next-2-steps */,
						st._013 /* 1*'<-' */,
						st._082 /* 1*ownedCoIterator=CoIteratorVariableCS */,
						st._038 /* 1*'in' */,
						st._099 /* 1*ownedInitExpression=ExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null
					}
				);
			private final /*@NonNull*/ RTSerializationRule _069
				= /* { prefix=',' ownedNameExpression=NavigatingArgExpCS '<-' ownedCoIterator=CoIteratorVariableCS { '=' ownedInitExpression=ExpCS }[?] } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(63, /* EssentialOCL::NavigatingCommaArgCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._594 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : NavigatingArgExpCS */,
						ms._583 /* check-rule essentialoclcs::NavigatingArgCS.ownedCoIterator : CoIteratorVariableCS */,
						ms._588 /* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : ExpCS */,
						ms._083 /* assign V0 = |ownedInitExpression| */,
						ms._009 /* assert (|ownedCoIterator| - 1) == 0 */,
						ms._026 /* assert (|ownedNameExpression| - 1) == 0 */,
						ms._044 /* assert (|prefix.','| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-7-steps */,
						st._007 /* 1*',' */,
						st._108 /* 1*ownedNameExpression=NavigatingArgExpCS */,
						st._013 /* 1*'<-' */,
						st._082 /* 1*ownedCoIterator=CoIteratorVariableCS */,
						st._169 /* V00*next-2-steps */,
						st._014 /* 1*'=' */,
						st._099 /* 1*ownedInitExpression=ExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null
					}
				);
			private final /*@NonNull*/ RTSerializationRule _070
				= /* { prefix=';' ownedNameExpression=NavigatingArgExpCS { ':' ownedType=TypeExpCS { '=' ownedInitExpression=ExpCS }[?] }[?] } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(64, /* EssentialOCL::NavigatingSemiArgCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._602 /* check-rule essentialoclcs::NavigatingArgCS.ownedNameExpression : NavigatingArgExpCS */,
						ms._610 /* check-rule essentialoclcs::NavigatingArgCS.ownedType : TypeExpCS */,
						ms._592 /* check-rule essentialoclcs::NavigatingArgCS.ownedInitExpression : ExpCS */,
						ms._089 /* assign V0 = |ownedType| */,
						ms._026 /* assert (|ownedNameExpression| - 1) == 0 */,
						ms._045 /* assert (|prefix.';'| - 1) == 0 */,
						ms._122 /* assign V1 = |ownedInitExpression| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-8-steps */,
						st._011 /* 1*';' */,
						st._108 /* 1*ownedNameExpression=NavigatingArgExpCS */,
						st._169 /* V00*next-5-steps */,
						st._009 /* 1*':' */,
						st._138 /* 1*ownedType=TypeExpCS */,
						st._181 /* V01*next-2-steps */,
						st._014 /* 1*'=' */,
						st._099 /* 1*ownedInitExpression=ExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._4 /* «! » + «value» + «?\n» */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null
					}
				);
			private final /*@NonNull*/ RTSerializationRule _071
				= /* { '(' ownedExpression=ExpCS ')' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(66, /* EssentialOCL::NestedExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._612 /* check-rule essentialoclcs::NestedExpCS.ownedExpression : ExpCS */,
						ms._018 /* assert (|ownedExpression| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-3-steps */,
						st._003 /* 1*'(' */,
						st._089 /* 1*ownedExpression=ExpCS */,
						st._004 /* 1*')' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._2 /* «! » + «value» + «! » */,
						null,
						ss._5 /* «! » + «value» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _072
				= /* { '(' ownedExpression=ExpCS ')' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(66, /* EssentialOCL::NestedExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._613 /* check-rule essentialoclcs::NestedExpCS.ownedExpression : ExpCS */,
						ms._018 /* assert (|ownedExpression| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-3-steps */,
						st._003 /* 1*'(' */,
						st._089 /* 1*ownedExpression=ExpCS */,
						st._004 /* 1*')' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._2 /* «! » + «value» + «! » */,
						null,
						ss._5 /* «! » + «value» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _073
				= /* 'null' */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(68, /* EssentialOCL::NullLiteralExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._046 /* 1*'null' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						ss._8 /* «? » + «value» + «? » */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _074
				= /* 'null' */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(68, /* EssentialOCL::NullLiteralExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._046 /* 1*'null' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						ss._8 /* «? » + «value» + «? » */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _075
				= /* symbol=NUMBER_LITERAL */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(69, /* EssentialOCL::NumberLiteralExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._056 /* assert (|symbol| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._159 /* 1*symbol=NUMBER_LITERAL */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						ss._8 /* «? » + «value» + «? » */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _076
				= /* symbol=NUMBER_LITERAL */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(69, /* EssentialOCL::NumberLiteralExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._056 /* assert (|symbol| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._159 /* 1*symbol=NUMBER_LITERAL */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						ss._8 /* «? » + «value» + «? » */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _077
				= /* { patternVariableName=UnrestrictedName[?] ':' ownedPatternType=TypeExpCS } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(74, /* EssentialOCL::PatternExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._619 /* check-rule essentialoclcs::PatternExpCS.ownedPatternType : TypeExpCS */,
						ms._033 /* assert (|ownedPatternType| - 1) == 0 */,
						ms._094 /* assign V0 = |patternVariableName| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-3-steps */,
						st._175 /* V00*patternVariableName=UnrestrictedName */,
						st._009 /* 1*':' */,
						st._126 /* 1*ownedPatternType=TypeExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null
					}
				);
			private final /*@NonNull*/ RTSerializationRule _078
				= /* { name=UnaryOperatorName ownedRight=PrefixedLetExpCS } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(77, /* EssentialOCL::PrefixedLetExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._615 /* check-rule essentialoclcs::OperatorExpCS.ownedRight : PrefixedLetExpCS */,
						ms._034 /* assert (|ownedRight| - 1) == 0 */,
						ms._005 /* assert (|name| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-2-steps */,
						st._073 /* 1*name=UnaryOperatorName */,
						st._128 /* 1*ownedRight=PrefixedLetExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						null
					}
				);
			private final /*@NonNull*/ RTSerializationRule _079
				= /* { name=UnaryOperatorName ownedRight=PrefixedLetExpCS } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(77, /* EssentialOCL::PrefixedLetExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._616 /* check-rule essentialoclcs::OperatorExpCS.ownedRight : PrefixedLetExpCS */,
						ms._034 /* assert (|ownedRight| - 1) == 0 */,
						ms._005 /* assert (|name| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-2-steps */,
						st._073 /* 1*name=UnaryOperatorName */,
						st._128 /* 1*ownedRight=PrefixedLetExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						null
					}
				);
			private final /*@NonNull*/ RTSerializationRule _080
				= /* { name=UnaryOperatorName ownedRight=PrefixedPrimaryExpCS } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(78, /* EssentialOCL::PrefixedPrimaryExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._618 /* check-rule essentialoclcs::OperatorExpCS.ownedRight : PrefixedPrimaryExpCS */,
						ms._034 /* assert (|ownedRight| - 1) == 0 */,
						ms._005 /* assert (|name| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-2-steps */,
						st._073 /* 1*name=UnaryOperatorName */,
						st._129 /* 1*ownedRight=PrefixedPrimaryExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						null
					}
				);
			private final /*@NonNull*/ RTSerializationRule _081
				= /* name=PrimitiveTypeIdentifier */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(81, /* EssentialOCL::PrimitiveTypeCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._006 /* assert (|name| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._072 /* 1*name=PrimitiveTypeIdentifier */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						ss._8 /* «? » + «value» + «? » */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _082
				= /* name=PrimitiveTypeIdentifier */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(81, /* EssentialOCL::PrimitiveTypeCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._006 /* assert (|name| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._072 /* 1*name=PrimitiveTypeIdentifier */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						ss._8 /* «? » + «value» + «? » */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _083
				= /* { '(' { ownedArguments+=NavigatingArgCS ownedArguments+=(NavigatingCommaArgCS|NavigatingSemiArgCS|NavigatingBarArgCS)[*] }[?] ')' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(84, /* EssentialOCL::RoundBracketedClauseCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._620 /* check-rule essentialoclcs::RoundBracketedClauseCS.ownedArguments : NavigatingSemiArgCS|NavigatingBarArgCS|NavigatingCommaArgCS|NavigatingArgCS */,
						ms._057 /* assign V0 = (|ownedArguments| > 0) */,
						ms._103 /* assign V1 = (|ownedArguments| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-5-steps */,
						st._003 /* 1*'(' */,
						st._169 /* V00*next-2-steps */,
						st._080 /* 1*ownedArguments+=NavigatingArgCS */,
						st._182 /* V01*ownedArguments+=NavigatingCommaArgCS|NavigatingSemiArgCS|NavigatingBarArgCS */,
						st._004 /* 1*')' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._2 /* «! » + «value» + «! » */,
						null,
						null,
						null,
						ss._5 /* «! » + «value» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _084
				= /* 'self' */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(89, /* EssentialOCL::SelfExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._055 /* 1*'self' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						ss._8 /* «? » + «value» + «? » */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _085
				= /* 'self' */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(89, /* EssentialOCL::SelfExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._055 /* 1*'self' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						ss._8 /* «? » + «value» + «? » */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _086
				= /* ownedInitExpression=StringLiteralExpCS */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(90, /* EssentialOCL::ShadowPartCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._622 /* check-rule essentialoclcs::ShadowPartCS.ownedInitExpression : StringLiteralExpCS */,
						ms._021 /* assert (|ownedInitExpression| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._102 /* 1*ownedInitExpression=StringLiteralExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null
					}
				);
			private final /*@NonNull*/ RTSerializationRule _087
				= /* { referredProperty=UnrestrictedName '=' ownedInitExpression=(ExpCS|PatternExpCS) } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(90, /* EssentialOCL::ShadowPartCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._621 /* check-rule essentialoclcs::ShadowPartCS.ownedInitExpression : ExpCS|PatternExpCS */,
						ms._021 /* assert (|ownedInitExpression| - 1) == 0 */,
						ms._050 /* assert (|referredProperty| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-3-steps */,
						st._155 /* 1*referredProperty=UnrestrictedName */,
						st._014 /* 1*'=' */,
						st._101 /* 1*ownedInitExpression=ExpCS|PatternExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null
					}
				);
			private final /*@NonNull*/ RTSerializationRule _088
				= /* ownedPathElements+=FirstPathElementCS */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(91, /* EssentialOCL::SimplePathNameCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._358 /* check-rule basecs::PathNameCS.ownedPathElements : FirstPathElementCS */,
						ms._027 /* assert (|ownedPathElements| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._117 /* 1*ownedPathElements+=FirstPathElementCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null
					}
				);
			private final /*@NonNull*/ RTSerializationRule _089
				= /* { '[' ownedTerms+=ExpCS { ',' ownedTerms+=ExpCS }[*] ']' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(93, /* EssentialOCL::SquareBracketedClauseCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._623 /* check-rule essentialoclcs::SquareBracketedClauseCS.ownedTerms : ExpCS */,
						ms._067 /* assign V0 = (|ownedTerms| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-6-steps */,
						st._021 /* 1*'[' */,
						st._132 /* 1*ownedTerms+=ExpCS */,
						st._169 /* V00*next-2-steps */,
						st._007 /* 1*',' */,
						st._132 /* 1*ownedTerms+=ExpCS */,
						st._022 /* 1*']' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._2 /* «! » + «value» + «! » */,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						ss._5 /* «! » + «value» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _090
				= /* segments+=StringLiteral[+] */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(95, /* EssentialOCL::StringLiteralExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._099 /* assign V0 = |segments| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._176 /* V00*segments+=StringLiteral */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						ss._8 /* «? » + «value» + «? » */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _091
				= /* segments+=StringLiteral[+] */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(95, /* EssentialOCL::StringLiteralExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._099 /* assign V0 = |segments| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._176 /* V00*segments+=StringLiteral */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						ss._8 /* «? » + «value» + «? » */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _092
				= /* { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(103, /* EssentialOCL::TupleLiteralExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._626 /* check-rule essentialoclcs::TupleLiteralExpCS.ownedParts : TupleLiteralPartCS */,
						ms._060 /* assign V0 = (|ownedParts| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-7-steps */,
						st._020 /* 1*'Tuple' */,
						st._060 /* 1*'{' */,
						st._115 /* 1*ownedParts+=TupleLiteralPartCS */,
						st._169 /* V00*next-2-steps */,
						st._007 /* 1*',' */,
						st._115 /* 1*ownedParts+=TupleLiteralPartCS */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _093
				= /* { 'Tuple' '{' ownedParts+=TupleLiteralPartCS { ',' ownedParts+=TupleLiteralPartCS }[*] '}' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(103, /* EssentialOCL::TupleLiteralExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._625 /* check-rule essentialoclcs::TupleLiteralExpCS.ownedParts : TupleLiteralPartCS */,
						ms._060 /* assign V0 = (|ownedParts| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-7-steps */,
						st._020 /* 1*'Tuple' */,
						st._060 /* 1*'{' */,
						st._115 /* 1*ownedParts+=TupleLiteralPartCS */,
						st._169 /* V00*next-2-steps */,
						st._007 /* 1*',' */,
						st._115 /* 1*ownedParts+=TupleLiteralPartCS */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _094
				= /* { name=UnrestrictedName { ':' ownedType=TypeExpCS }[?] '=' ownedInitExpression=ExpCS } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(104, /* EssentialOCL::TupleLiteralPartCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._642 /* check-rule essentialoclcs::VariableCS.ownedType : TypeExpCS */,
						ms._640 /* check-rule essentialoclcs::VariableCS.ownedInitExpression : ExpCS */,
						ms._020 /* assert (|ownedInitExpression| - 1) == 0 */,
						ms._091 /* assign V0 = |ownedType| */,
						ms._005 /* assert (|name| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-6-steps */,
						st._074 /* 1*name=UnrestrictedName */,
						st._169 /* V00*next-2-steps */,
						st._009 /* 1*':' */,
						st._139 /* 1*ownedType=TypeExpCS */,
						st._014 /* 1*'=' */,
						st._100 /* 1*ownedInitExpression=ExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null
					}
				);
			private final /*@NonNull*/ RTSerializationRule _095
				= /* { name=UnrestrictedName ':' ownedType=TypeExpCS } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(105, /* EssentialOCL::TuplePartCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._436 /* check-rule basecs::TypedElementCS.ownedType : TypeExpCS */,
						ms._039 /* assert (|ownedType| - 1) == 0 */,
						ms._005 /* assert (|name| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-3-steps */,
						st._074 /* 1*name=UnrestrictedName */,
						st._009 /* 1*':' */,
						st._140 /* 1*ownedType=TypeExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null
					}
				);
			private final /*@NonNull*/ RTSerializationRule _096
				= /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(106, /* EssentialOCL::TupleTypeCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._431 /* check-rule basecs::TupleTypeCS.ownedParts : TuplePartCS */,
						ms._003 /* assert (|name.'Tuple'| - 1) == 0 */,
						ms._063 /* assign V0 = (|ownedParts| > 0) */,
						ms._111 /* assign V1 = (|ownedParts| > 0) */,
						ms._152 /* assign V2 = (|ownedParts| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-9-steps */,
						st._020 /* 1*'Tuple' */,
						st._169 /* V00*next-7-steps */,
						st._003 /* 1*'(' */,
						st._181 /* V01*next-4-steps */,
						st._116 /* 1*ownedParts+=TuplePartCS */,
						st._189 /* V02*next-2-steps */,
						st._007 /* 1*',' */,
						st._116 /* 1*ownedParts+=TuplePartCS */,
						st._004 /* 1*')' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._2 /* «! » + «value» + «! » */,
						null,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						ss._5 /* «! » + «value» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _097
				= /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(106, /* EssentialOCL::TupleTypeCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._430 /* check-rule basecs::TupleTypeCS.ownedParts : TuplePartCS */,
						ms._003 /* assert (|name.'Tuple'| - 1) == 0 */,
						ms._063 /* assign V0 = (|ownedParts| > 0) */,
						ms._111 /* assign V1 = (|ownedParts| > 0) */,
						ms._152 /* assign V2 = (|ownedParts| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-9-steps */,
						st._020 /* 1*'Tuple' */,
						st._169 /* V00*next-7-steps */,
						st._003 /* 1*'(' */,
						st._181 /* V01*next-4-steps */,
						st._116 /* 1*ownedParts+=TuplePartCS */,
						st._189 /* V02*next-2-steps */,
						st._007 /* 1*',' */,
						st._116 /* 1*ownedParts+=TuplePartCS */,
						st._004 /* 1*')' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._2 /* «! » + «value» + «! » */,
						null,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						ss._5 /* «! » + «value» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _098
				= /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(107, /* EssentialOCL::TypeExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._572 /* check-rule essentialoclcs::MapTypeCS.ownedKeyType : TypeExpCS */,
						ms._577 /* check-rule essentialoclcs::MapTypeCS.ownedValueType : TypeExpCS */,
						ms._477 /* check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS */,
						ms._125 /* assign V1 = |ownedMultiplicity| */,
						ms._093 /* assign V0 = |ownedValueType| */,
						ms._023 /* assert (|ownedKeyType| - V0) == 0 */,
						ms._002 /* assert (|name.'Map'| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-8-steps */,
						st._019 /* 1*'Map' */,
						st._169 /* V00*next-5-steps */,
						st._003 /* 1*'(' */,
						st._104 /* 1*ownedKeyType=TypeExpCS */,
						st._007 /* 1*',' */,
						st._145 /* 1*ownedValueType=TypeExpCS */,
						st._004 /* 1*')' */,
						st._185 /* V01*ownedMultiplicity=MultiplicityCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._2 /* «! » + «value» + «! » */,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						ss._5 /* «! » + «value» */,
						null
					}
				);
			private final /*@NonNull*/ RTSerializationRule _099
				= /* { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(107, /* EssentialOCL::TypeExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._483 /* check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS */,
						ms._085 /* assign V0 = |ownedMultiplicity| */,
						ms._006 /* assert (|name| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-2-steps */,
						st._072 /* 1*name=PrimitiveTypeIdentifier */,
						st._171 /* V00*ownedMultiplicity=MultiplicityCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						null
					}
				);
			private final /*@NonNull*/ RTSerializationRule _100
				= /* { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { '{' ownedPatternGuard=ExpCS '}' }[?] }[?] ownedMultiplicity=MultiplicityCS[?] } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(107, /* EssentialOCL::TypeExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._633 /* check-rule essentialoclcs::TypeNameExpCS.ownedPathName : PathNameCS */,
						ms._630 /* check-rule essentialoclcs::TypeNameExpCS.ownedCurlyBracketedClause : CurlyBracketedClauseCS */,
						ms._636 /* check-rule essentialoclcs::TypeNameExpCS.ownedPatternGuard : ExpCS */,
						ms._475 /* check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS */,
						ms._161 /* assign V2 = |ownedMultiplicity| */,
						ms._079 /* assign V0 = |ownedCurlyBracketedClause| */,
						ms._028 /* assert (|ownedPathName| - 1) == 0 */,
						ms._126 /* assign V1 = |ownedPatternGuard| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-8-steps */,
						st._120 /* 1*ownedPathName=PathNameCS */,
						st._169 /* V00*next-5-steps */,
						st._085 /* 1*ownedCurlyBracketedClause=CurlyBracketedClauseCS */,
						st._181 /* V01*next-3-steps */,
						st._060 /* 1*'{' */,
						st._125 /* 1*ownedPatternGuard=ExpCS */,
						st._063 /* 1*'}' */,
						st._192 /* V02*ownedMultiplicity=MultiplicityCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						null,
						null,
						null,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						null
					}
				);
			private final /*@NonNull*/ RTSerializationRule _101
				= /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(107, /* EssentialOCL::TypeExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._432 /* check-rule basecs::TupleTypeCS.ownedParts : TuplePartCS */,
						ms._482 /* check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS */,
						ms._177 /* assign V3 = |ownedMultiplicity| */,
						ms._003 /* assert (|name.'Tuple'| - 1) == 0 */,
						ms._063 /* assign V0 = (|ownedParts| > 0) */,
						ms._111 /* assign V1 = (|ownedParts| > 0) */,
						ms._152 /* assign V2 = (|ownedParts| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-10-steps */,
						st._020 /* 1*'Tuple' */,
						st._169 /* V00*next-7-steps */,
						st._003 /* 1*'(' */,
						st._181 /* V01*next-4-steps */,
						st._116 /* 1*ownedParts+=TuplePartCS */,
						st._189 /* V02*next-2-steps */,
						st._007 /* 1*',' */,
						st._116 /* 1*ownedParts+=TuplePartCS */,
						st._004 /* 1*')' */,
						st._198 /* V03*ownedMultiplicity=MultiplicityCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._2 /* «! » + «value» + «! » */,
						null,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						ss._5 /* «! » + «value» */,
						null
					}
				);
			private final /*@NonNull*/ RTSerializationRule _102
				= /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(107, /* EssentialOCL::TypeExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._528 /* check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : MultiplicityCS */,
						ms._533 /* check-rule essentialoclcs::CollectionTypeCS.ownedType : TypeExpWithoutMultiplicityCS */,
						ms._469 /* check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS */,
						ms._161 /* assign V2 = |ownedMultiplicity| */,
						ms._090 /* assign V0 = |ownedType| */,
						ms._004 /* assert (|name| - 1) == 0 */,
						ms._120 /* assign V1 = |ownedCollectionMultiplicity| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-7-steps */,
						st._070 /* 1*name=CollectionTypeIdentifier */,
						st._169 /* V00*next-4-steps */,
						st._003 /* 1*'(' */,
						st._141 /* 1*ownedType=TypeExpWithoutMultiplicityCS */,
						st._183 /* V01*ownedCollectionMultiplicity=MultiplicityCS */,
						st._004 /* 1*')' */,
						st._192 /* V02*ownedMultiplicity=MultiplicityCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._2 /* «! » + «value» + «! » */,
						null,
						null,
						ss._5 /* «! » + «value» */,
						null
					}
				);
			private final /*@NonNull*/ RTSerializationRule _103
				= /* { ownedType=CollectionTypeCS '{' { ownedParts+=PatternExpCS { ',' ownedParts+=PatternExpCS }[*] '++' restVariableName=Identifier }[?] '}' ownedMultiplicity=MultiplicityCS[?] } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(107, /* EssentialOCL::TypeExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._525 /* check-rule essentialoclcs::CollectionPatternCS.ownedType : CollectionTypeCS */,
						ms._523 /* check-rule essentialoclcs::CollectionPatternCS.ownedParts : PatternExpCS */,
						ms._470 /* check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS */,
						ms._161 /* assign V2 = |ownedMultiplicity| */,
						ms._098 /* assign V0 = |restVariableName| */,
						ms._107 /* assign V1 = (|ownedParts| - 1) */,
						ms._041 /* assert (|ownedType| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-11-steps */,
						st._135 /* 1*ownedType=CollectionTypeCS */,
						st._060 /* 1*'{' */,
						st._169 /* V00*next-6-steps */,
						st._113 /* 1*ownedParts+=PatternExpCS */,
						st._181 /* V01*next-2-steps */,
						st._007 /* 1*',' */,
						st._113 /* 1*ownedParts+=PatternExpCS */,
						st._006 /* 1*'++' */,
						st._156 /* 1*restVariableName=Identifier */,
						st._063 /* 1*'}' */,
						st._192 /* V02*ownedMultiplicity=MultiplicityCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						null
					}
				);
			private final /*@NonNull*/ RTSerializationRule _104
				= /* ownedType=TypeLiteralWithMultiplicityCS */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(111, /* EssentialOCL::TypeLiteralExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._628 /* check-rule essentialoclcs::TypeLiteralExpCS.ownedType : TypeLiteralWithMultiplicityCS */,
						ms._038 /* assert (|ownedType| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._142 /* 1*ownedType=TypeLiteralWithMultiplicityCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null
					}
				);
			private final /*@NonNull*/ RTSerializationRule _105
				= /* ownedType=TypeLiteralWithMultiplicityCS */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(111, /* EssentialOCL::TypeLiteralExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._629 /* check-rule essentialoclcs::TypeLiteralExpCS.ownedType : TypeLiteralWithMultiplicityCS */,
						ms._038 /* assert (|ownedType| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._142 /* 1*ownedType=TypeLiteralWithMultiplicityCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null
					}
				);
			private final /*@NonNull*/ RTSerializationRule _106
				= /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(112, /* EssentialOCL::TypeLiteralWithMultiplicityCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._433 /* check-rule basecs::TupleTypeCS.ownedParts : TuplePartCS */,
						ms._476 /* check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS */,
						ms._177 /* assign V3 = |ownedMultiplicity| */,
						ms._003 /* assert (|name.'Tuple'| - 1) == 0 */,
						ms._063 /* assign V0 = (|ownedParts| > 0) */,
						ms._111 /* assign V1 = (|ownedParts| > 0) */,
						ms._152 /* assign V2 = (|ownedParts| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-10-steps */,
						st._020 /* 1*'Tuple' */,
						st._169 /* V00*next-7-steps */,
						st._003 /* 1*'(' */,
						st._181 /* V01*next-4-steps */,
						st._116 /* 1*ownedParts+=TuplePartCS */,
						st._189 /* V02*next-2-steps */,
						st._007 /* 1*',' */,
						st._116 /* 1*ownedParts+=TuplePartCS */,
						st._004 /* 1*')' */,
						st._198 /* V03*ownedMultiplicity=MultiplicityCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._2 /* «! » + «value» + «! » */,
						null,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						ss._5 /* «! » + «value» */,
						null
					}
				);
			private final /*@NonNull*/ RTSerializationRule _107
				= /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(112, /* EssentialOCL::TypeLiteralWithMultiplicityCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._573 /* check-rule essentialoclcs::MapTypeCS.ownedKeyType : TypeExpCS */,
						ms._578 /* check-rule essentialoclcs::MapTypeCS.ownedValueType : TypeExpCS */,
						ms._478 /* check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS */,
						ms._125 /* assign V1 = |ownedMultiplicity| */,
						ms._093 /* assign V0 = |ownedValueType| */,
						ms._023 /* assert (|ownedKeyType| - V0) == 0 */,
						ms._002 /* assert (|name.'Map'| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-8-steps */,
						st._019 /* 1*'Map' */,
						st._169 /* V00*next-5-steps */,
						st._003 /* 1*'(' */,
						st._104 /* 1*ownedKeyType=TypeExpCS */,
						st._007 /* 1*',' */,
						st._145 /* 1*ownedValueType=TypeExpCS */,
						st._004 /* 1*')' */,
						st._185 /* V01*ownedMultiplicity=MultiplicityCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._2 /* «! » + «value» + «! » */,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						ss._5 /* «! » + «value» */,
						null
					}
				);
			private final /*@NonNull*/ RTSerializationRule _108
				= /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(112, /* EssentialOCL::TypeLiteralWithMultiplicityCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._529 /* check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : MultiplicityCS */,
						ms._534 /* check-rule essentialoclcs::CollectionTypeCS.ownedType : TypeExpWithoutMultiplicityCS */,
						ms._471 /* check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS */,
						ms._161 /* assign V2 = |ownedMultiplicity| */,
						ms._090 /* assign V0 = |ownedType| */,
						ms._004 /* assert (|name| - 1) == 0 */,
						ms._120 /* assign V1 = |ownedCollectionMultiplicity| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-7-steps */,
						st._070 /* 1*name=CollectionTypeIdentifier */,
						st._169 /* V00*next-4-steps */,
						st._003 /* 1*'(' */,
						st._141 /* 1*ownedType=TypeExpWithoutMultiplicityCS */,
						st._183 /* V01*ownedCollectionMultiplicity=MultiplicityCS */,
						st._004 /* 1*')' */,
						st._192 /* V02*ownedMultiplicity=MultiplicityCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._2 /* «! » + «value» + «! » */,
						null,
						null,
						ss._5 /* «! » + «value» */,
						null
					}
				);
			private final /*@NonNull*/ RTSerializationRule _109
				= /* { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(112, /* EssentialOCL::TypeLiteralWithMultiplicityCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._484 /* check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS */,
						ms._085 /* assign V0 = |ownedMultiplicity| */,
						ms._006 /* assert (|name| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-2-steps */,
						st._072 /* 1*name=PrimitiveTypeIdentifier */,
						st._171 /* V00*ownedMultiplicity=MultiplicityCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						null
					}
				);
			private final /*@NonNull*/ RTSerializationRule _110
				= /* { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { '{' ownedPatternGuard=ExpCS '}' }[?] }[?] } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(113, /* EssentialOCL::TypeNameExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._635 /* check-rule essentialoclcs::TypeNameExpCS.ownedPathName : PathNameCS */,
						ms._632 /* check-rule essentialoclcs::TypeNameExpCS.ownedCurlyBracketedClause : CurlyBracketedClauseCS */,
						ms._638 /* check-rule essentialoclcs::TypeNameExpCS.ownedPatternGuard : ExpCS */,
						ms._079 /* assign V0 = |ownedCurlyBracketedClause| */,
						ms._028 /* assert (|ownedPathName| - 1) == 0 */,
						ms._126 /* assign V1 = |ownedPatternGuard| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-7-steps */,
						st._120 /* 1*ownedPathName=PathNameCS */,
						st._169 /* V00*next-5-steps */,
						st._085 /* 1*ownedCurlyBracketedClause=CurlyBracketedClauseCS */,
						st._181 /* V01*next-3-steps */,
						st._060 /* 1*'{' */,
						st._125 /* 1*ownedPatternGuard=ExpCS */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						null,
						null,
						null,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _111
				= /* { ownedPathName=PathNameCS { ownedCurlyBracketedClause=CurlyBracketedClauseCS { '{' ownedPatternGuard=ExpCS '}' }[?] }[?] } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(113, /* EssentialOCL::TypeNameExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._634 /* check-rule essentialoclcs::TypeNameExpCS.ownedPathName : PathNameCS */,
						ms._631 /* check-rule essentialoclcs::TypeNameExpCS.ownedCurlyBracketedClause : CurlyBracketedClauseCS */,
						ms._637 /* check-rule essentialoclcs::TypeNameExpCS.ownedPatternGuard : ExpCS */,
						ms._079 /* assign V0 = |ownedCurlyBracketedClause| */,
						ms._028 /* assert (|ownedPathName| - 1) == 0 */,
						ms._126 /* assign V1 = |ownedPatternGuard| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-7-steps */,
						st._120 /* 1*ownedPathName=PathNameCS */,
						st._169 /* V00*next-5-steps */,
						st._085 /* 1*ownedCurlyBracketedClause=CurlyBracketedClauseCS */,
						st._181 /* V01*next-3-steps */,
						st._060 /* 1*'{' */,
						st._125 /* 1*ownedPatternGuard=ExpCS */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						null,
						null,
						null,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _112
				= /* referredElement=URI */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(122, /* EssentialOCL::URIFirstPathElementCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._049 /* assert (|referredElement| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._148 /* 1*referredElement=URI */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						ss._8 /* «? » + «value» + «? » */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _113
				= /* referredElement=UnrestrictedName */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(122, /* EssentialOCL::URIFirstPathElementCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._049 /* assert (|referredElement| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._151 /* 1*referredElement=UnrestrictedName */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						ss._8 /* «? » + «value» + «? » */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _114
				= /* { ownedPathElements+=URIFirstPathElementCS { '::' ownedPathElements+=NextPathElementCS }[*] } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(123, /* EssentialOCL::URIPathNameCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._360 /* check-rule basecs::PathNameCS.ownedPathElements : URIFirstPathElementCS|NextPathElementCS */,
						ms._065 /* assign V0 = (|ownedPathElements| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-4-steps */,
						st._119 /* 1*ownedPathElements+=URIFirstPathElementCS */,
						st._169 /* V00*next-2-steps */,
						st._010 /* 1*'::' */,
						st._118 /* 1*ownedPathElements+=NextPathElementCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						null,
						null,
						ss._2 /* «! » + «value» + «! » */,
						null
					}
				);
			private final /*@NonNull*/ RTSerializationRule _115
				= /* '*' */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(125, /* EssentialOCL::UnlimitedNaturalLiteralExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._005 /* 1*'*' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						ss._8 /* «? » + «value» + «? » */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _116
				= /* '*' */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(125, /* EssentialOCL::UnlimitedNaturalLiteralExpCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._005 /* 1*'*' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						ss._8 /* «? » + «value» + «? » */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _117
				= /* { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedContents+=ModelElementCS[*] ownedReferences+=ModelElementRefCS[+] '}' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(1, /* OCLinEcore::AnnotationCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._275 /* check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationElementCS */,
						ms._229 /* check-rule basecs::AnnotationCS.ownedContents : ModelElementCS */,
						ms._232 /* check-rule basecs::AnnotationCS.ownedReferences : ModelElementRefCS */,
						ms._235 /* check-rule basecs::AnnotationElementCS.ownedDetails : DetailCS */,
						ms._200 /* assign V5 = |ownedReferences| */,
						ms._188 /* assign V4 = |ownedContents| */,
						ms._176 /* assign V3 = |ownedAnnotations| */,
						ms._076 /* assign V0 = |name| */,
						ms._104 /* assign V1 = (|ownedDetails| > 0) */,
						ms._149 /* assign V2 = (|ownedDetails| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-14-steps */,
						st._023 /* 1*'annotation' */,
						st._168 /* V00*name=UnrestrictedName|SINGLE_QUOTED_STRING */,
						st._181 /* V01*next-6-steps */,
						st._003 /* 1*'(' */,
						st._086 /* 1*ownedDetails+=DetailCS */,
						st._189 /* V02*next-2-steps */,
						st._007 /* 1*',' */,
						st._086 /* 1*ownedDetails+=DetailCS */,
						st._004 /* 1*')' */,
						st._060 /* 1*'{' */,
						st._197 /* V03*ownedAnnotations+=AnnotationElementCS */,
						st._205 /* V04*ownedContents+=ModelElementCS */,
						st._211 /* V05*ownedReferences+=ModelElementRefCS */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._2 /* «! » + «value» + «! » */,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						ss._5 /* «! » + «value» */,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						null,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _118
				= /* { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] ';' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(1, /* OCLinEcore::AnnotationCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._236 /* check-rule basecs::AnnotationElementCS.ownedDetails : DetailCS */,
						ms._076 /* assign V0 = |name| */,
						ms._104 /* assign V1 = (|ownedDetails| > 0) */,
						ms._149 /* assign V2 = (|ownedDetails| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-10-steps */,
						st._023 /* 1*'annotation' */,
						st._168 /* V00*name=UnrestrictedName|SINGLE_QUOTED_STRING */,
						st._181 /* V01*next-6-steps */,
						st._003 /* 1*'(' */,
						st._086 /* 1*ownedDetails+=DetailCS */,
						st._189 /* V02*next-2-steps */,
						st._007 /* 1*',' */,
						st._086 /* 1*ownedDetails+=DetailCS */,
						st._004 /* 1*')' */,
						st._011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._2 /* «! » + «value» + «! » */,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						ss._5 /* «! » + «value» */,
						ss._4 /* «! » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _119
				= /* { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedContents+=ModelElementCS[+] ownedReferences+=ModelElementRefCS[*] '}' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(1, /* OCLinEcore::AnnotationCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._278 /* check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationElementCS */,
						ms._230 /* check-rule basecs::AnnotationCS.ownedContents : ModelElementCS */,
						ms._233 /* check-rule basecs::AnnotationCS.ownedReferences : ModelElementRefCS */,
						ms._237 /* check-rule basecs::AnnotationElementCS.ownedDetails : DetailCS */,
						ms._200 /* assign V5 = |ownedReferences| */,
						ms._188 /* assign V4 = |ownedContents| */,
						ms._176 /* assign V3 = |ownedAnnotations| */,
						ms._076 /* assign V0 = |name| */,
						ms._104 /* assign V1 = (|ownedDetails| > 0) */,
						ms._149 /* assign V2 = (|ownedDetails| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-14-steps */,
						st._023 /* 1*'annotation' */,
						st._168 /* V00*name=UnrestrictedName|SINGLE_QUOTED_STRING */,
						st._181 /* V01*next-6-steps */,
						st._003 /* 1*'(' */,
						st._086 /* 1*ownedDetails+=DetailCS */,
						st._189 /* V02*next-2-steps */,
						st._007 /* 1*',' */,
						st._086 /* 1*ownedDetails+=DetailCS */,
						st._004 /* 1*')' */,
						st._060 /* 1*'{' */,
						st._197 /* V03*ownedAnnotations+=AnnotationElementCS */,
						st._205 /* V04*ownedContents+=ModelElementCS */,
						st._211 /* V05*ownedReferences+=ModelElementRefCS */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._2 /* «! » + «value» + «! » */,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						ss._5 /* «! » + «value» */,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						null,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _120
				= /* { 'annotation' name=(UnrestrictedName|SINGLE_QUOTED_STRING)[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] '{' ownedAnnotations+=AnnotationElementCS[+] ownedContents+=ModelElementCS[*] ownedReferences+=ModelElementRefCS[*] '}' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(1, /* OCLinEcore::AnnotationCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._281 /* check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationElementCS */,
						ms._231 /* check-rule basecs::AnnotationCS.ownedContents : ModelElementCS */,
						ms._234 /* check-rule basecs::AnnotationCS.ownedReferences : ModelElementRefCS */,
						ms._238 /* check-rule basecs::AnnotationElementCS.ownedDetails : DetailCS */,
						ms._200 /* assign V5 = |ownedReferences| */,
						ms._188 /* assign V4 = |ownedContents| */,
						ms._176 /* assign V3 = |ownedAnnotations| */,
						ms._076 /* assign V0 = |name| */,
						ms._104 /* assign V1 = (|ownedDetails| > 0) */,
						ms._149 /* assign V2 = (|ownedDetails| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-14-steps */,
						st._023 /* 1*'annotation' */,
						st._168 /* V00*name=UnrestrictedName|SINGLE_QUOTED_STRING */,
						st._181 /* V01*next-6-steps */,
						st._003 /* 1*'(' */,
						st._086 /* 1*ownedDetails+=DetailCS */,
						st._189 /* V02*next-2-steps */,
						st._007 /* 1*',' */,
						st._086 /* 1*ownedDetails+=DetailCS */,
						st._004 /* 1*')' */,
						st._060 /* 1*'{' */,
						st._197 /* V03*ownedAnnotations+=AnnotationElementCS */,
						st._205 /* V04*ownedContents+=ModelElementCS */,
						st._211 /* V05*ownedReferences+=ModelElementRefCS */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._2 /* «! » + «value» + «! » */,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						ss._5 /* «! » + «value» */,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						null,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _121
				= /* { qualifiers+='static' qualifiers+='definition'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(3, /* OCLinEcore::AttributeCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._290 /* check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationElementCS */,
						ms._370 /* check-rule basecs::StructuralFeatureCS.ownedDefaultExpressions : SpecificationCS */,
						ms._464 /* check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS */,
						ms._196 /* assign V5 = |ownedAnnotations| */,
						ms._156 /* assign V2 = |default| */,
						ms._130 /* assign V1 = |ownedType| */,
						ms._005 /* assert (|name| - 1) == 0 */,
						ms._095 /* assign V0 = |qualifiers.'definition'| */,
						ms._048 /* assert (|qualifiers.'static'| - 1) == 0 */,
						ms._171 /* assign V3 = (|qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| > 0) */,
						ms._192 /* assign V4 = |qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| */,
						ms._209 /* assign V6 = |ownedDefaultExpressions| */,
						ms._210 /* assign V7 = (|ownedDefaultExpressions| > 0) */,
						ms._218 /* assign V8 = 0 */,
						ms._224 /* assign V9 = 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-28-steps */,
						st._056 /* 1*'static' */,
						st._164 /* V00*'definition' */,
						st._024 /* 1*'attribute' */,
						st._074 /* 1*name=UnrestrictedName */,
						st._181 /* V01*next-2-steps */,
						st._009 /* 1*':' */,
						st._143 /* 1*ownedType=TypedMultiplicityRefCS */,
						st._189 /* V02*next-2-steps */,
						st._014 /* 1*'=' */,
						st._064 /* 1*default=SINGLE_QUOTED_STRING */,
						st._196 /* V03*next-4-steps */,
						st._060 /* 1*'{' */,
						st._202 /* V04*next-1-steps */,
						st._147 /* 1*qualifiers */,
						st._063 /* 1*'}' */,
						st._060 /* 1*'{' */,
						st._208 /* V05*ownedAnnotations+=AnnotationElementCS */,
						st._213 /* V06*next-4-steps */,
						st._039 /* 1*'initial' */,
						st._009 /* 1*':' */,
						st._219 /* V07*ownedDefaultExpressions+=SpecificationCS */,
						st._011 /* 1*';' */,
						st._220 /* V08*next-4-steps */,
						st._029 /* 1*'derivation' */,
						st._009 /* 1*':' */,
						st._226 /* V09*ownedDefaultExpressions+=SpecificationCS */,
						st._011 /* 1*';' */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._4 /* «! » + «value» + «?\n» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._4 /* «! » + «value» + «?\n» */,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _122
				= /* { qualifiers+='definition' qualifiers+='static'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(3, /* OCLinEcore::AttributeCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._274 /* check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationElementCS */,
						ms._368 /* check-rule basecs::StructuralFeatureCS.ownedDefaultExpressions : SpecificationCS */,
						ms._463 /* check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS */,
						ms._196 /* assign V5 = |ownedAnnotations| */,
						ms._156 /* assign V2 = |default| */,
						ms._130 /* assign V1 = |ownedType| */,
						ms._005 /* assert (|name| - 1) == 0 */,
						ms._096 /* assign V0 = |qualifiers.'static'| */,
						ms._047 /* assert (|qualifiers.'definition'| - 1) == 0 */,
						ms._171 /* assign V3 = (|qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| > 0) */,
						ms._192 /* assign V4 = |qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| */,
						ms._209 /* assign V6 = |ownedDefaultExpressions| */,
						ms._210 /* assign V7 = (|ownedDefaultExpressions| > 0) */,
						ms._218 /* assign V8 = 0 */,
						ms._224 /* assign V9 = 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-28-steps */,
						st._028 /* 1*'definition' */,
						st._166 /* V00*'static' */,
						st._024 /* 1*'attribute' */,
						st._074 /* 1*name=UnrestrictedName */,
						st._181 /* V01*next-2-steps */,
						st._009 /* 1*':' */,
						st._143 /* 1*ownedType=TypedMultiplicityRefCS */,
						st._189 /* V02*next-2-steps */,
						st._014 /* 1*'=' */,
						st._064 /* 1*default=SINGLE_QUOTED_STRING */,
						st._196 /* V03*next-4-steps */,
						st._060 /* 1*'{' */,
						st._202 /* V04*next-1-steps */,
						st._147 /* 1*qualifiers */,
						st._063 /* 1*'}' */,
						st._060 /* 1*'{' */,
						st._208 /* V05*ownedAnnotations+=AnnotationElementCS */,
						st._213 /* V06*next-4-steps */,
						st._039 /* 1*'initial' */,
						st._009 /* 1*':' */,
						st._219 /* V07*ownedDefaultExpressions+=SpecificationCS */,
						st._011 /* 1*';' */,
						st._220 /* V08*next-4-steps */,
						st._029 /* 1*'derivation' */,
						st._009 /* 1*':' */,
						st._226 /* V09*ownedDefaultExpressions+=SpecificationCS */,
						st._011 /* 1*';' */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._4 /* «! » + «value» + «?\n» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._4 /* «! » + «value» + «?\n» */,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _123
				= /* { qualifiers+='definition' qualifiers+='static'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(3, /* OCLinEcore::AttributeCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._439 /* check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS */,
						ms._156 /* assign V2 = |default| */,
						ms._130 /* assign V1 = |ownedType| */,
						ms._005 /* assert (|name| - 1) == 0 */,
						ms._096 /* assign V0 = |qualifiers.'static'| */,
						ms._047 /* assert (|qualifiers.'definition'| - 1) == 0 */,
						ms._171 /* assign V3 = (|qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| > 0) */,
						ms._192 /* assign V4 = |qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-16-steps */,
						st._028 /* 1*'definition' */,
						st._166 /* V00*'static' */,
						st._024 /* 1*'attribute' */,
						st._074 /* 1*name=UnrestrictedName */,
						st._181 /* V01*next-2-steps */,
						st._009 /* 1*':' */,
						st._143 /* 1*ownedType=TypedMultiplicityRefCS */,
						st._189 /* V02*next-2-steps */,
						st._014 /* 1*'=' */,
						st._064 /* 1*default=SINGLE_QUOTED_STRING */,
						st._196 /* V03*next-4-steps */,
						st._060 /* 1*'{' */,
						st._202 /* V04*next-1-steps */,
						st._147 /* 1*qualifiers */,
						st._063 /* 1*'}' */,
						st._011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._4 /* «! » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _124
				= /* { 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(3, /* OCLinEcore::AttributeCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._438 /* check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS */,
						ms._113 /* assign V1 = |default| */,
						ms._092 /* assign V0 = |ownedType| */,
						ms._005 /* assert (|name| - 1) == 0 */,
						ms._154 /* assign V2 = (|qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| > 0) */,
						ms._181 /* assign V3 = |qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-14-steps */,
						st._024 /* 1*'attribute' */,
						st._074 /* 1*name=UnrestrictedName */,
						st._169 /* V00*next-2-steps */,
						st._009 /* 1*':' */,
						st._143 /* 1*ownedType=TypedMultiplicityRefCS */,
						st._181 /* V01*next-2-steps */,
						st._014 /* 1*'=' */,
						st._064 /* 1*default=SINGLE_QUOTED_STRING */,
						st._189 /* V02*next-4-steps */,
						st._060 /* 1*'{' */,
						st._196 /* V03*next-1-steps */,
						st._147 /* 1*qualifiers */,
						st._063 /* 1*'}' */,
						st._011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._4 /* «! » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _125
				= /* { 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] '}' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(3, /* OCLinEcore::AttributeCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._287 /* check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationElementCS */,
						ms._369 /* check-rule basecs::StructuralFeatureCS.ownedDefaultExpressions : SpecificationCS */,
						ms._457 /* check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS */,
						ms._186 /* assign V4 = |ownedAnnotations| */,
						ms._113 /* assign V1 = |default| */,
						ms._092 /* assign V0 = |ownedType| */,
						ms._005 /* assert (|name| - 1) == 0 */,
						ms._154 /* assign V2 = (|qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| > 0) */,
						ms._181 /* assign V3 = |qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| */,
						ms._198 /* assign V5 = |ownedDefaultExpressions| */,
						ms._202 /* assign V6 = (|ownedDefaultExpressions| > 0) */,
						ms._214 /* assign V7 = 0 */,
						ms._218 /* assign V8 = 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-26-steps */,
						st._024 /* 1*'attribute' */,
						st._074 /* 1*name=UnrestrictedName */,
						st._169 /* V00*next-2-steps */,
						st._009 /* 1*':' */,
						st._143 /* 1*ownedType=TypedMultiplicityRefCS */,
						st._181 /* V01*next-2-steps */,
						st._014 /* 1*'=' */,
						st._064 /* 1*default=SINGLE_QUOTED_STRING */,
						st._189 /* V02*next-4-steps */,
						st._060 /* 1*'{' */,
						st._196 /* V03*next-1-steps */,
						st._147 /* 1*qualifiers */,
						st._063 /* 1*'}' */,
						st._060 /* 1*'{' */,
						st._203 /* V04*ownedAnnotations+=AnnotationElementCS */,
						st._207 /* V05*next-4-steps */,
						st._039 /* 1*'initial' */,
						st._009 /* 1*':' */,
						st._216 /* V06*ownedDefaultExpressions+=SpecificationCS */,
						st._011 /* 1*';' */,
						st._217 /* V07*next-4-steps */,
						st._029 /* 1*'derivation' */,
						st._009 /* 1*':' */,
						st._222 /* V08*ownedDefaultExpressions+=SpecificationCS */,
						st._011 /* 1*';' */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._4 /* «! » + «value» + «?\n» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._4 /* «! » + «value» + «?\n» */,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _126
				= /* { qualifiers+='static' qualifiers+='definition'[?] 'attribute' name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(3, /* OCLinEcore::AttributeCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._449 /* check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS */,
						ms._156 /* assign V2 = |default| */,
						ms._130 /* assign V1 = |ownedType| */,
						ms._005 /* assert (|name| - 1) == 0 */,
						ms._095 /* assign V0 = |qualifiers.'definition'| */,
						ms._048 /* assert (|qualifiers.'static'| - 1) == 0 */,
						ms._171 /* assign V3 = (|qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| > 0) */,
						ms._192 /* assign V4 = |qualifiers.'!derived|!id|!ordered|!readonly|!transient|!unique|!unsettable|!volatile|derived|id|ordered|readonly|transient|unique|unsettable|volatile'| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-16-steps */,
						st._056 /* 1*'static' */,
						st._164 /* V00*'definition' */,
						st._024 /* 1*'attribute' */,
						st._074 /* 1*name=UnrestrictedName */,
						st._181 /* V01*next-2-steps */,
						st._009 /* 1*':' */,
						st._143 /* 1*ownedType=TypedMultiplicityRefCS */,
						st._189 /* V02*next-2-steps */,
						st._014 /* 1*'=' */,
						st._064 /* 1*default=SINGLE_QUOTED_STRING */,
						st._196 /* V03*next-4-steps */,
						st._060 /* 1*'{' */,
						st._202 /* V04*next-1-steps */,
						st._147 /* 1*qualifiers */,
						st._063 /* 1*'}' */,
						st._011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._4 /* «! » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _127
				= /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(15, /* OCLinEcore::DataTypeCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._292 /* check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationElementCS */,
						ms._397 /* check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS */,
						ms._251 /* check-rule basecs::ClassCS.ownedConstraints : InvariantConstraintCS */,
						ms._197 /* assign V5 = |ownedConstraints| */,
						ms._186 /* assign V4 = |ownedAnnotations| */,
						ms._157 /* assign V2 = |instanceClassName| */,
						ms._128 /* assign V1 = |ownedSignature| */,
						ms._005 /* assert (|name| - 1) == 0 */,
						ms._074 /* assign V0 = |isPrimitive.'primitive'| */,
						ms._172 /* assign V3 = 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-14-steps */,
						st._165 /* V00*'primitive' */,
						st._027 /* 1*'datatype' */,
						st._074 /* 1*name=UnrestrictedName */,
						st._188 /* V01*ownedSignature=TemplateSignatureCS */,
						st._189 /* V02*next-2-steps */,
						st._009 /* 1*':' */,
						st._066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						st._196 /* V03*next-2-steps */,
						st._060 /* 1*'{' */,
						st._063 /* 1*'}' */,
						st._060 /* 1*'{' */,
						st._203 /* V04*ownedAnnotations+=AnnotationElementCS */,
						st._209 /* V05*ownedConstraints+=InvariantConstraintCS */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _128
				= /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(15, /* OCLinEcore::DataTypeCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._300 /* check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationElementCS */,
						ms._410 /* check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS */,
						ms._253 /* check-rule basecs::ClassCS.ownedConstraints : InvariantConstraintCS */,
						ms._208 /* assign V6 = |ownedConstraints| */,
						ms._196 /* assign V5 = |ownedAnnotations| */,
						ms._157 /* assign V2 = |instanceClassName| */,
						ms._128 /* assign V1 = |ownedSignature| */,
						ms._005 /* assert (|name| - 1) == 0 */,
						ms._074 /* assign V0 = |isPrimitive.'primitive'| */,
						ms._166 /* assign V3 = (|isSerializable.'serializable'| > 0) */,
						ms._185 /* assign V4 = |isSerializable.'serializable'| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-15-steps */,
						st._165 /* V00*'primitive' */,
						st._027 /* 1*'datatype' */,
						st._074 /* 1*name=UnrestrictedName */,
						st._188 /* V01*ownedSignature=TemplateSignatureCS */,
						st._189 /* V02*next-2-steps */,
						st._009 /* 1*':' */,
						st._066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						st._196 /* V03*next-3-steps */,
						st._060 /* 1*'{' */,
						st._201 /* V04*'serializable' */,
						st._063 /* 1*'}' */,
						st._060 /* 1*'{' */,
						st._208 /* V05*ownedAnnotations+=AnnotationElementCS */,
						st._215 /* V06*ownedConstraints+=InvariantConstraintCS */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _129
				= /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] ';' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(15, /* OCLinEcore::DataTypeCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._391 /* check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS */,
						ms._157 /* assign V2 = |instanceClassName| */,
						ms._128 /* assign V1 = |ownedSignature| */,
						ms._005 /* assert (|name| - 1) == 0 */,
						ms._074 /* assign V0 = |isPrimitive.'primitive'| */,
						ms._166 /* assign V3 = (|isSerializable.'serializable'| > 0) */,
						ms._185 /* assign V4 = |isSerializable.'serializable'| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-12-steps */,
						st._165 /* V00*'primitive' */,
						st._027 /* 1*'datatype' */,
						st._074 /* 1*name=UnrestrictedName */,
						st._188 /* V01*ownedSignature=TemplateSignatureCS */,
						st._189 /* V02*next-2-steps */,
						st._009 /* 1*':' */,
						st._066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						st._196 /* V03*next-3-steps */,
						st._060 /* 1*'{' */,
						st._201 /* V04*'serializable' */,
						st._063 /* 1*'}' */,
						st._011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._4 /* «! » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _130
				= /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(15, /* OCLinEcore::DataTypeCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._273 /* check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationElementCS */,
						ms._396 /* check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS */,
						ms._246 /* check-rule basecs::ClassCS.ownedConstraints : InvariantConstraintCS */,
						ms._197 /* assign V5 = |ownedConstraints| */,
						ms._186 /* assign V4 = |ownedAnnotations| */,
						ms._157 /* assign V2 = |instanceClassName| */,
						ms._128 /* assign V1 = |ownedSignature| */,
						ms._005 /* assert (|name| - 1) == 0 */,
						ms._074 /* assign V0 = |isPrimitive.'primitive'| */,
						ms._172 /* assign V3 = 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-14-steps */,
						st._165 /* V00*'primitive' */,
						st._027 /* 1*'datatype' */,
						st._074 /* 1*name=UnrestrictedName */,
						st._188 /* V01*ownedSignature=TemplateSignatureCS */,
						st._189 /* V02*next-2-steps */,
						st._009 /* 1*':' */,
						st._066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						st._196 /* V03*next-2-steps */,
						st._060 /* 1*'{' */,
						st._063 /* 1*'}' */,
						st._060 /* 1*'{' */,
						st._203 /* V04*ownedAnnotations+=AnnotationElementCS */,
						st._209 /* V05*ownedConstraints+=InvariantConstraintCS */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _131
				= /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] ';' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(15, /* OCLinEcore::DataTypeCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._392 /* check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS */,
						ms._157 /* assign V2 = |instanceClassName| */,
						ms._128 /* assign V1 = |ownedSignature| */,
						ms._005 /* assert (|name| - 1) == 0 */,
						ms._074 /* assign V0 = |isPrimitive.'primitive'| */,
						ms._172 /* assign V3 = 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-11-steps */,
						st._165 /* V00*'primitive' */,
						st._027 /* 1*'datatype' */,
						st._074 /* 1*name=UnrestrictedName */,
						st._188 /* V01*ownedSignature=TemplateSignatureCS */,
						st._189 /* V02*next-2-steps */,
						st._009 /* 1*':' */,
						st._066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						st._196 /* V03*next-2-steps */,
						st._060 /* 1*'{' */,
						st._063 /* 1*'}' */,
						st._011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._4 /* «! » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _132
				= /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] ';' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(15, /* OCLinEcore::DataTypeCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._403 /* check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS */,
						ms._157 /* assign V2 = |instanceClassName| */,
						ms._128 /* assign V1 = |ownedSignature| */,
						ms._005 /* assert (|name| - 1) == 0 */,
						ms._074 /* assign V0 = |isPrimitive.'primitive'| */,
						ms._172 /* assign V3 = 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-11-steps */,
						st._165 /* V00*'primitive' */,
						st._027 /* 1*'datatype' */,
						st._074 /* 1*name=UnrestrictedName */,
						st._188 /* V01*ownedSignature=TemplateSignatureCS */,
						st._189 /* V02*next-2-steps */,
						st._009 /* 1*':' */,
						st._066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						st._196 /* V03*next-2-steps */,
						st._060 /* 1*'{' */,
						st._063 /* 1*'}' */,
						st._011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._4 /* «! » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _133
				= /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] ';' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(15, /* OCLinEcore::DataTypeCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._419 /* check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS */,
						ms._157 /* assign V2 = |instanceClassName| */,
						ms._128 /* assign V1 = |ownedSignature| */,
						ms._005 /* assert (|name| - 1) == 0 */,
						ms._074 /* assign V0 = |isPrimitive.'primitive'| */,
						ms._172 /* assign V3 = 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-12-steps */,
						st._165 /* V00*'primitive' */,
						st._027 /* 1*'datatype' */,
						st._074 /* 1*name=UnrestrictedName */,
						st._188 /* V01*ownedSignature=TemplateSignatureCS */,
						st._189 /* V02*next-2-steps */,
						st._009 /* 1*':' */,
						st._066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						st._196 /* V03*next-3-steps */,
						st._060 /* 1*'{' */,
						st._000 /* 1*'!serializable' */,
						st._063 /* 1*'}' */,
						st._011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._4 /* «! » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _134
				= /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] ';' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(15, /* OCLinEcore::DataTypeCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._424 /* check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS */,
						ms._157 /* assign V2 = |instanceClassName| */,
						ms._128 /* assign V1 = |ownedSignature| */,
						ms._005 /* assert (|name| - 1) == 0 */,
						ms._074 /* assign V0 = |isPrimitive.'primitive'| */,
						ms._166 /* assign V3 = (|isSerializable.'serializable'| > 0) */,
						ms._185 /* assign V4 = |isSerializable.'serializable'| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-12-steps */,
						st._165 /* V00*'primitive' */,
						st._027 /* 1*'datatype' */,
						st._074 /* 1*name=UnrestrictedName */,
						st._188 /* V01*ownedSignature=TemplateSignatureCS */,
						st._189 /* V02*next-2-steps */,
						st._009 /* 1*':' */,
						st._066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						st._196 /* V03*next-3-steps */,
						st._060 /* 1*'{' */,
						st._201 /* V04*'serializable' */,
						st._063 /* 1*'}' */,
						st._011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._4 /* «! » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _135
				= /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(15, /* OCLinEcore::DataTypeCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._304 /* check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationElementCS */,
						ms._420 /* check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS */,
						ms._252 /* check-rule basecs::ClassCS.ownedConstraints : InvariantConstraintCS */,
						ms._197 /* assign V5 = |ownedConstraints| */,
						ms._186 /* assign V4 = |ownedAnnotations| */,
						ms._157 /* assign V2 = |instanceClassName| */,
						ms._128 /* assign V1 = |ownedSignature| */,
						ms._005 /* assert (|name| - 1) == 0 */,
						ms._074 /* assign V0 = |isPrimitive.'primitive'| */,
						ms._172 /* assign V3 = 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-15-steps */,
						st._165 /* V00*'primitive' */,
						st._027 /* 1*'datatype' */,
						st._074 /* 1*name=UnrestrictedName */,
						st._188 /* V01*ownedSignature=TemplateSignatureCS */,
						st._189 /* V02*next-2-steps */,
						st._009 /* 1*':' */,
						st._066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						st._196 /* V03*next-3-steps */,
						st._060 /* 1*'{' */,
						st._000 /* 1*'!serializable' */,
						st._063 /* 1*'}' */,
						st._060 /* 1*'{' */,
						st._203 /* V04*ownedAnnotations+=AnnotationElementCS */,
						st._209 /* V05*ownedConstraints+=InvariantConstraintCS */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _136
				= /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(15, /* OCLinEcore::DataTypeCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._298 /* check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationElementCS */,
						ms._401 /* check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS */,
						ms._247 /* check-rule basecs::ClassCS.ownedConstraints : InvariantConstraintCS */,
						ms._197 /* assign V5 = |ownedConstraints| */,
						ms._186 /* assign V4 = |ownedAnnotations| */,
						ms._157 /* assign V2 = |instanceClassName| */,
						ms._128 /* assign V1 = |ownedSignature| */,
						ms._005 /* assert (|name| - 1) == 0 */,
						ms._074 /* assign V0 = |isPrimitive.'primitive'| */,
						ms._172 /* assign V3 = 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-15-steps */,
						st._165 /* V00*'primitive' */,
						st._027 /* 1*'datatype' */,
						st._074 /* 1*name=UnrestrictedName */,
						st._188 /* V01*ownedSignature=TemplateSignatureCS */,
						st._189 /* V02*next-2-steps */,
						st._009 /* 1*':' */,
						st._066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						st._196 /* V03*next-3-steps */,
						st._060 /* 1*'{' */,
						st._000 /* 1*'!serializable' */,
						st._063 /* 1*'}' */,
						st._060 /* 1*'{' */,
						st._203 /* V04*ownedAnnotations+=AnnotationElementCS */,
						st._209 /* V05*ownedConstraints+=InvariantConstraintCS */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _137
				= /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] ';' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(15, /* OCLinEcore::DataTypeCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._395 /* check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS */,
						ms._157 /* assign V2 = |instanceClassName| */,
						ms._128 /* assign V1 = |ownedSignature| */,
						ms._005 /* assert (|name| - 1) == 0 */,
						ms._074 /* assign V0 = |isPrimitive.'primitive'| */,
						ms._172 /* assign V3 = 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-12-steps */,
						st._165 /* V00*'primitive' */,
						st._027 /* 1*'datatype' */,
						st._074 /* 1*name=UnrestrictedName */,
						st._188 /* V01*ownedSignature=TemplateSignatureCS */,
						st._189 /* V02*next-2-steps */,
						st._009 /* 1*':' */,
						st._066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						st._196 /* V03*next-3-steps */,
						st._060 /* 1*'{' */,
						st._000 /* 1*'!serializable' */,
						st._063 /* 1*'}' */,
						st._011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._4 /* «! » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _138
				= /* { isPrimitive='primitive'[?] 'datatype' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(15, /* OCLinEcore::DataTypeCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._303 /* check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationElementCS */,
						ms._390 /* check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS */,
						ms._245 /* check-rule basecs::ClassCS.ownedConstraints : InvariantConstraintCS */,
						ms._208 /* assign V6 = |ownedConstraints| */,
						ms._196 /* assign V5 = |ownedAnnotations| */,
						ms._157 /* assign V2 = |instanceClassName| */,
						ms._128 /* assign V1 = |ownedSignature| */,
						ms._005 /* assert (|name| - 1) == 0 */,
						ms._074 /* assign V0 = |isPrimitive.'primitive'| */,
						ms._166 /* assign V3 = (|isSerializable.'serializable'| > 0) */,
						ms._185 /* assign V4 = |isSerializable.'serializable'| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-15-steps */,
						st._165 /* V00*'primitive' */,
						st._027 /* 1*'datatype' */,
						st._074 /* 1*name=UnrestrictedName */,
						st._188 /* V01*ownedSignature=TemplateSignatureCS */,
						st._189 /* V02*next-2-steps */,
						st._009 /* 1*':' */,
						st._066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						st._196 /* V03*next-3-steps */,
						st._060 /* 1*'{' */,
						st._201 /* V04*'serializable' */,
						st._063 /* 1*'}' */,
						st._060 /* 1*'{' */,
						st._208 /* V05*ownedAnnotations+=AnnotationElementCS */,
						st._215 /* V06*ownedConstraints+=InvariantConstraintCS */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _139
				= /* { name=(UnrestrictedName|SINGLE_QUOTED_STRING) '=' values+=(SINGLE_QUOTED_STRING|ML_SINGLE_QUOTED_STRING)[*] } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(16, /* OCLinEcore::DetailCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._101 /* assign V0 = |values| */,
						ms._005 /* assert (|name| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-3-steps */,
						st._075 /* 1*name=UnrestrictedName|SINGLE_QUOTED_STRING */,
						st._014 /* 1*'=' */,
						st._178 /* V00*values+=SINGLE_QUOTED_STRING|ML_SINGLE_QUOTED_STRING */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _140
				= /* { 'documentation' value=SINGLE_QUOTED_STRING[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] ';' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(17, /* OCLinEcore::DocumentationCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._239 /* check-rule basecs::AnnotationElementCS.ownedDetails : DetailCS */,
						ms._102 /* assign V0 = |value| */,
						ms._104 /* assign V1 = (|ownedDetails| > 0) */,
						ms._149 /* assign V2 = (|ownedDetails| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-10-steps */,
						st._030 /* 1*'documentation' */,
						st._177 /* V00*value=SINGLE_QUOTED_STRING */,
						st._181 /* V01*next-6-steps */,
						st._003 /* 1*'(' */,
						st._086 /* 1*ownedDetails+=DetailCS */,
						st._189 /* V02*next-2-steps */,
						st._007 /* 1*',' */,
						st._086 /* 1*ownedDetails+=DetailCS */,
						st._004 /* 1*')' */,
						st._011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._2 /* «! » + «value» + «! » */,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						ss._5 /* «! » + «value» */,
						ss._4 /* «! » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _141
				= /* { 'documentation' value=SINGLE_QUOTED_STRING[?] { '(' ownedDetails+=DetailCS { ',' ownedDetails+=DetailCS }[*] ')' }[?] ';' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(17, /* OCLinEcore::DocumentationCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._240 /* check-rule basecs::AnnotationElementCS.ownedDetails : DetailCS */,
						ms._102 /* assign V0 = |value| */,
						ms._104 /* assign V1 = (|ownedDetails| > 0) */,
						ms._149 /* assign V2 = (|ownedDetails| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-10-steps */,
						st._030 /* 1*'documentation' */,
						st._177 /* V00*value=SINGLE_QUOTED_STRING */,
						st._181 /* V01*next-6-steps */,
						st._003 /* 1*'(' */,
						st._086 /* 1*ownedDetails+=DetailCS */,
						st._189 /* V02*next-2-steps */,
						st._007 /* 1*',' */,
						st._086 /* 1*ownedDetails+=DetailCS */,
						st._004 /* 1*')' */,
						st._011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._2 /* «! » + «value» + «! » */,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						ss._5 /* «! » + «value» */,
						ss._4 /* «! » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _142
				= /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(21, /* OCLinEcore::EnumerationCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._305 /* check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationElementCS */,
						ms._267 /* check-rule basecs::EnumerationCS.ownedLiterals : EnumerationLiteralCS */,
						ms._418 /* check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS */,
						ms._255 /* check-rule basecs::ClassCS.ownedConstraints : InvariantConstraintCS */,
						ms._197 /* assign V5 = |ownedConstraints| */,
						ms._189 /* assign V4 = |ownedLiterals| */,
						ms._176 /* assign V3 = |ownedAnnotations| */,
						ms._114 /* assign V1 = |instanceClassName| */,
						ms._087 /* assign V0 = |ownedSignature| */,
						ms._005 /* assert (|name| - 1) == 0 */,
						ms._155 /* assign V2 = 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-15-steps */,
						st._034 /* 1*'enum' */,
						st._074 /* 1*name=UnrestrictedName */,
						st._173 /* V00*ownedSignature=TemplateSignatureCS */,
						st._181 /* V01*next-2-steps */,
						st._009 /* 1*':' */,
						st._066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						st._189 /* V02*next-3-steps */,
						st._060 /* 1*'{' */,
						st._000 /* 1*'!serializable' */,
						st._063 /* 1*'}' */,
						st._060 /* 1*'{' */,
						st._197 /* V03*ownedAnnotations+=AnnotationElementCS */,
						st._206 /* V04*ownedLiterals+=EnumerationLiteralCS */,
						st._209 /* V05*ownedConstraints+=InvariantConstraintCS */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						null,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _143
				= /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(21, /* OCLinEcore::EnumerationCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._308 /* check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationElementCS */,
						ms._271 /* check-rule basecs::EnumerationCS.ownedLiterals : EnumerationLiteralCS */,
						ms._404 /* check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS */,
						ms._250 /* check-rule basecs::ClassCS.ownedConstraints : InvariantConstraintCS */,
						ms._208 /* assign V6 = |ownedConstraints| */,
						ms._199 /* assign V5 = |ownedLiterals| */,
						ms._186 /* assign V4 = |ownedAnnotations| */,
						ms._114 /* assign V1 = |instanceClassName| */,
						ms._087 /* assign V0 = |ownedSignature| */,
						ms._005 /* assert (|name| - 1) == 0 */,
						ms._148 /* assign V2 = (|isSerializable.'serializable'| > 0) */,
						ms._175 /* assign V3 = |isSerializable.'serializable'| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-15-steps */,
						st._034 /* 1*'enum' */,
						st._074 /* 1*name=UnrestrictedName */,
						st._173 /* V00*ownedSignature=TemplateSignatureCS */,
						st._181 /* V01*next-2-steps */,
						st._009 /* 1*':' */,
						st._066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						st._189 /* V02*next-3-steps */,
						st._060 /* 1*'{' */,
						st._195 /* V03*'serializable' */,
						st._063 /* 1*'}' */,
						st._060 /* 1*'{' */,
						st._203 /* V04*ownedAnnotations+=AnnotationElementCS */,
						st._210 /* V05*ownedLiterals+=EnumerationLiteralCS */,
						st._215 /* V06*ownedConstraints+=InvariantConstraintCS */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						null,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _144
				= /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] ';' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(21, /* OCLinEcore::EnumerationCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._426 /* check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS */,
						ms._114 /* assign V1 = |instanceClassName| */,
						ms._087 /* assign V0 = |ownedSignature| */,
						ms._005 /* assert (|name| - 1) == 0 */,
						ms._148 /* assign V2 = (|isSerializable.'serializable'| > 0) */,
						ms._175 /* assign V3 = |isSerializable.'serializable'| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-11-steps */,
						st._034 /* 1*'enum' */,
						st._074 /* 1*name=UnrestrictedName */,
						st._173 /* V00*ownedSignature=TemplateSignatureCS */,
						st._181 /* V01*next-2-steps */,
						st._009 /* 1*':' */,
						st._066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						st._189 /* V02*next-3-steps */,
						st._060 /* 1*'{' */,
						st._195 /* V03*'serializable' */,
						st._063 /* 1*'}' */,
						st._011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._4 /* «! » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _145
				= /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] ';' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(21, /* OCLinEcore::EnumerationCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._398 /* check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS */,
						ms._114 /* assign V1 = |instanceClassName| */,
						ms._087 /* assign V0 = |ownedSignature| */,
						ms._005 /* assert (|name| - 1) == 0 */,
						ms._155 /* assign V2 = 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-10-steps */,
						st._034 /* 1*'enum' */,
						st._074 /* 1*name=UnrestrictedName */,
						st._173 /* V00*ownedSignature=TemplateSignatureCS */,
						st._181 /* V01*next-2-steps */,
						st._009 /* 1*':' */,
						st._066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						st._189 /* V02*next-2-steps */,
						st._060 /* 1*'{' */,
						st._063 /* 1*'}' */,
						st._011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._4 /* «! » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _146
				= /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] ';' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(21, /* OCLinEcore::EnumerationCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._429 /* check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS */,
						ms._114 /* assign V1 = |instanceClassName| */,
						ms._087 /* assign V0 = |ownedSignature| */,
						ms._005 /* assert (|name| - 1) == 0 */,
						ms._155 /* assign V2 = 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-11-steps */,
						st._034 /* 1*'enum' */,
						st._074 /* 1*name=UnrestrictedName */,
						st._173 /* V00*ownedSignature=TemplateSignatureCS */,
						st._181 /* V01*next-2-steps */,
						st._009 /* 1*':' */,
						st._066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						st._189 /* V02*next-3-steps */,
						st._060 /* 1*'{' */,
						st._000 /* 1*'!serializable' */,
						st._063 /* 1*'}' */,
						st._011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._4 /* «! » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _147
				= /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(21, /* OCLinEcore::EnumerationCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._309 /* check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationElementCS */,
						ms._270 /* check-rule basecs::EnumerationCS.ownedLiterals : EnumerationLiteralCS */,
						ms._421 /* check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS */,
						ms._258 /* check-rule basecs::ClassCS.ownedConstraints : InvariantConstraintCS */,
						ms._197 /* assign V5 = |ownedConstraints| */,
						ms._189 /* assign V4 = |ownedLiterals| */,
						ms._176 /* assign V3 = |ownedAnnotations| */,
						ms._114 /* assign V1 = |instanceClassName| */,
						ms._087 /* assign V0 = |ownedSignature| */,
						ms._005 /* assert (|name| - 1) == 0 */,
						ms._155 /* assign V2 = 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-15-steps */,
						st._034 /* 1*'enum' */,
						st._074 /* 1*name=UnrestrictedName */,
						st._173 /* V00*ownedSignature=TemplateSignatureCS */,
						st._181 /* V01*next-2-steps */,
						st._009 /* 1*':' */,
						st._066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						st._189 /* V02*next-3-steps */,
						st._060 /* 1*'{' */,
						st._000 /* 1*'!serializable' */,
						st._063 /* 1*'}' */,
						st._060 /* 1*'{' */,
						st._197 /* V03*ownedAnnotations+=AnnotationElementCS */,
						st._206 /* V04*ownedLiterals+=EnumerationLiteralCS */,
						st._209 /* V05*ownedConstraints+=InvariantConstraintCS */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						null,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _148
				= /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(21, /* OCLinEcore::EnumerationCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._289 /* check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationElementCS */,
						ms._269 /* check-rule basecs::EnumerationCS.ownedLiterals : EnumerationLiteralCS */,
						ms._412 /* check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS */,
						ms._248 /* check-rule basecs::ClassCS.ownedConstraints : InvariantConstraintCS */,
						ms._197 /* assign V5 = |ownedConstraints| */,
						ms._189 /* assign V4 = |ownedLiterals| */,
						ms._176 /* assign V3 = |ownedAnnotations| */,
						ms._114 /* assign V1 = |instanceClassName| */,
						ms._087 /* assign V0 = |ownedSignature| */,
						ms._005 /* assert (|name| - 1) == 0 */,
						ms._155 /* assign V2 = 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-14-steps */,
						st._034 /* 1*'enum' */,
						st._074 /* 1*name=UnrestrictedName */,
						st._173 /* V00*ownedSignature=TemplateSignatureCS */,
						st._181 /* V01*next-2-steps */,
						st._009 /* 1*':' */,
						st._066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						st._189 /* V02*next-2-steps */,
						st._060 /* 1*'{' */,
						st._063 /* 1*'}' */,
						st._060 /* 1*'{' */,
						st._197 /* V03*ownedAnnotations+=AnnotationElementCS */,
						st._206 /* V04*ownedLiterals+=EnumerationLiteralCS */,
						st._209 /* V05*ownedConstraints+=InvariantConstraintCS */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						null,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _149
				= /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] ';' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(21, /* OCLinEcore::EnumerationCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._399 /* check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS */,
						ms._114 /* assign V1 = |instanceClassName| */,
						ms._087 /* assign V0 = |ownedSignature| */,
						ms._005 /* assert (|name| - 1) == 0 */,
						ms._155 /* assign V2 = 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-10-steps */,
						st._034 /* 1*'enum' */,
						st._074 /* 1*name=UnrestrictedName */,
						st._173 /* V00*ownedSignature=TemplateSignatureCS */,
						st._181 /* V01*next-2-steps */,
						st._009 /* 1*':' */,
						st._066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						st._189 /* V02*next-2-steps */,
						st._060 /* 1*'{' */,
						st._063 /* 1*'}' */,
						st._011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._4 /* «! » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _150
				= /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(21, /* OCLinEcore::EnumerationCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._285 /* check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationElementCS */,
						ms._268 /* check-rule basecs::EnumerationCS.ownedLiterals : EnumerationLiteralCS */,
						ms._413 /* check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS */,
						ms._256 /* check-rule basecs::ClassCS.ownedConstraints : InvariantConstraintCS */,
						ms._208 /* assign V6 = |ownedConstraints| */,
						ms._199 /* assign V5 = |ownedLiterals| */,
						ms._186 /* assign V4 = |ownedAnnotations| */,
						ms._114 /* assign V1 = |instanceClassName| */,
						ms._087 /* assign V0 = |ownedSignature| */,
						ms._005 /* assert (|name| - 1) == 0 */,
						ms._148 /* assign V2 = (|isSerializable.'serializable'| > 0) */,
						ms._175 /* assign V3 = |isSerializable.'serializable'| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-15-steps */,
						st._034 /* 1*'enum' */,
						st._074 /* 1*name=UnrestrictedName */,
						st._173 /* V00*ownedSignature=TemplateSignatureCS */,
						st._181 /* V01*next-2-steps */,
						st._009 /* 1*':' */,
						st._066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						st._189 /* V02*next-3-steps */,
						st._060 /* 1*'{' */,
						st._195 /* V03*'serializable' */,
						st._063 /* 1*'}' */,
						st._060 /* 1*'{' */,
						st._203 /* V04*ownedAnnotations+=AnnotationElementCS */,
						st._210 /* V05*ownedLiterals+=EnumerationLiteralCS */,
						st._215 /* V06*ownedConstraints+=InvariantConstraintCS */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						null,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _151
				= /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '!serializable' '}' }[?] ';' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(21, /* OCLinEcore::EnumerationCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._417 /* check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS */,
						ms._114 /* assign V1 = |instanceClassName| */,
						ms._087 /* assign V0 = |ownedSignature| */,
						ms._005 /* assert (|name| - 1) == 0 */,
						ms._155 /* assign V2 = 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-11-steps */,
						st._034 /* 1*'enum' */,
						st._074 /* 1*name=UnrestrictedName */,
						st._173 /* V00*ownedSignature=TemplateSignatureCS */,
						st._181 /* V01*next-2-steps */,
						st._009 /* 1*':' */,
						st._066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						st._189 /* V02*next-3-steps */,
						st._060 /* 1*'{' */,
						st._000 /* 1*'!serializable' */,
						st._063 /* 1*'}' */,
						st._011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._4 /* «! » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _152
				= /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedLiterals+=EnumerationLiteralCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(21, /* OCLinEcore::EnumerationCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._297 /* check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationElementCS */,
						ms._266 /* check-rule basecs::EnumerationCS.ownedLiterals : EnumerationLiteralCS */,
						ms._402 /* check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS */,
						ms._254 /* check-rule basecs::ClassCS.ownedConstraints : InvariantConstraintCS */,
						ms._197 /* assign V5 = |ownedConstraints| */,
						ms._189 /* assign V4 = |ownedLiterals| */,
						ms._176 /* assign V3 = |ownedAnnotations| */,
						ms._114 /* assign V1 = |instanceClassName| */,
						ms._087 /* assign V0 = |ownedSignature| */,
						ms._005 /* assert (|name| - 1) == 0 */,
						ms._155 /* assign V2 = 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-14-steps */,
						st._034 /* 1*'enum' */,
						st._074 /* 1*name=UnrestrictedName */,
						st._173 /* V00*ownedSignature=TemplateSignatureCS */,
						st._181 /* V01*next-2-steps */,
						st._009 /* 1*':' */,
						st._066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						st._189 /* V02*next-2-steps */,
						st._060 /* 1*'{' */,
						st._063 /* 1*'}' */,
						st._060 /* 1*'{' */,
						st._197 /* V03*ownedAnnotations+=AnnotationElementCS */,
						st._206 /* V04*ownedLiterals+=EnumerationLiteralCS */,
						st._209 /* V05*ownedConstraints+=InvariantConstraintCS */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						null,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _153
				= /* { 'enum' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isSerializable='serializable'[?] '}' }[?] ';' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(21, /* OCLinEcore::EnumerationCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._405 /* check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS */,
						ms._114 /* assign V1 = |instanceClassName| */,
						ms._087 /* assign V0 = |ownedSignature| */,
						ms._005 /* assert (|name| - 1) == 0 */,
						ms._148 /* assign V2 = (|isSerializable.'serializable'| > 0) */,
						ms._175 /* assign V3 = |isSerializable.'serializable'| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-11-steps */,
						st._034 /* 1*'enum' */,
						st._074 /* 1*name=UnrestrictedName */,
						st._173 /* V00*ownedSignature=TemplateSignatureCS */,
						st._181 /* V01*next-2-steps */,
						st._009 /* 1*':' */,
						st._066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						st._189 /* V02*next-3-steps */,
						st._060 /* 1*'{' */,
						st._195 /* V03*'serializable' */,
						st._063 /* 1*'}' */,
						st._011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._4 /* «! » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _154
				= /* { name=EnumerationLiteralName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] '{' ownedAnnotations+=AnnotationElementCS[*] '}' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(22, /* OCLinEcore::EnumerationLiteralCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._286 /* check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationElementCS */,
						ms._158 /* assign V2 = |ownedAnnotations| */,
						ms._133 /* assign V1 = |value| */,
						ms._075 /* assign V0 = |literal| */,
						ms._005 /* assert (|name| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-10-steps */,
						st._071 /* 1*name=EnumerationLiteralName */,
						st._169 /* V00*next-2-steps */,
						st._009 /* 1*':' */,
						st._067 /* 1*literal=SINGLE_QUOTED_STRING */,
						st._181 /* V01*next-2-steps */,
						st._014 /* 1*'=' */,
						st._161 /* 1*value=SIGNED */,
						st._060 /* 1*'{' */,
						st._190 /* V02*ownedAnnotations+=AnnotationElementCS */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _155
				= /* { 'literal' name=UnrestrictedName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] ';' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(22, /* OCLinEcore::EnumerationLiteralCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._133 /* assign V1 = |value| */,
						ms._075 /* assign V0 = |literal| */,
						ms._005 /* assert (|name| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-9-steps */,
						st._044 /* 1*'literal' */,
						st._074 /* 1*name=UnrestrictedName */,
						st._169 /* V00*next-2-steps */,
						st._009 /* 1*':' */,
						st._067 /* 1*literal=SINGLE_QUOTED_STRING */,
						st._181 /* V01*next-2-steps */,
						st._014 /* 1*'=' */,
						st._161 /* 1*value=SIGNED */,
						st._011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._4 /* «! » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _156
				= /* { name=EnumerationLiteralName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] ';' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(22, /* OCLinEcore::EnumerationLiteralCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._133 /* assign V1 = |value| */,
						ms._075 /* assign V0 = |literal| */,
						ms._005 /* assert (|name| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-8-steps */,
						st._071 /* 1*name=EnumerationLiteralName */,
						st._169 /* V00*next-2-steps */,
						st._009 /* 1*':' */,
						st._067 /* 1*literal=SINGLE_QUOTED_STRING */,
						st._181 /* V01*next-2-steps */,
						st._014 /* 1*'=' */,
						st._161 /* 1*value=SIGNED */,
						st._011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._4 /* «! » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _157
				= /* { 'literal' name=UnrestrictedName { ':' literal=SINGLE_QUOTED_STRING }[?] { '=' value=SIGNED }[?] '{' ownedAnnotations+=AnnotationElementCS[*] '}' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(22, /* OCLinEcore::EnumerationLiteralCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._299 /* check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationElementCS */,
						ms._158 /* assign V2 = |ownedAnnotations| */,
						ms._133 /* assign V1 = |value| */,
						ms._075 /* assign V0 = |literal| */,
						ms._005 /* assert (|name| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-11-steps */,
						st._044 /* 1*'literal' */,
						st._074 /* 1*name=UnrestrictedName */,
						st._169 /* V00*next-2-steps */,
						st._009 /* 1*':' */,
						st._067 /* 1*literal=SINGLE_QUOTED_STRING */,
						st._181 /* V01*next-2-steps */,
						st._014 /* 1*'=' */,
						st._161 /* 1*value=SIGNED */,
						st._060 /* 1*'{' */,
						st._190 /* V02*ownedAnnotations+=AnnotationElementCS */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _158
				= /* { 'opposite' name=UnrestrictedName ':' ownedType=TypedMultiplicityRefCS { '{' { qualifiers+={'!ordered|!unique|ordered|unique'} }[+] '}' }[?] } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(37, /* OCLinEcore::ImplicitOppositeCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._437 /* check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS */,
						ms._039 /* assert (|ownedType| - 1) == 0 */,
						ms._005 /* assert (|name| - 1) == 0 */,
						ms._069 /* assign V0 = (|qualifiers.'!ordered|!unique|ordered|unique'| > 0) */,
						ms._131 /* assign V1 = |qualifiers.'!ordered|!unique|ordered|unique'| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-9-steps */,
						st._048 /* 1*'opposite' */,
						st._074 /* 1*name=UnrestrictedName */,
						st._009 /* 1*':' */,
						st._143 /* 1*ownedType=TypedMultiplicityRefCS */,
						st._169 /* V00*next-4-steps */,
						st._060 /* 1*'{' */,
						st._181 /* V01*next-1-steps */,
						st._147 /* 1*qualifiers */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _159
				= /* { {'import'|'library'} { name=UnrestrictedName ':' }[?] ownedPathName=URIPathNameCS isAll='::*'[?] ';' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(38, /* OCLinEcore::ImportCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._272 /* check-rule basecs::ImportCS.ownedPathName : URIPathNameCS */,
						ms._115 /* assign V1 = |isAll.'::*'| */,
						ms._029 /* assert (|ownedPathName| - 1) == 0 */,
						ms._076 /* assign V0 = |name| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-7-steps */,
						st._037 /* 1*'import' */,
						st._169 /* V00*next-2-steps */,
						st._074 /* 1*name=UnrestrictedName */,
						st._009 /* 1*':' */,
						st._124 /* 1*ownedPathName=URIPathNameCS */,
						st._179 /* V01*'::*' */,
						st._011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._4 /* «! » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _160
				= /* { isCallable='callable'[?] stereotype='invariant' { name=UnrestrictedName { '(' ownedMessageSpecification=SpecificationCS ')' }[?] }[?] ';' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(41, /* OCLinEcore::InvariantConstraintCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._259 /* check-rule basecs::ConstraintCS.ownedMessageSpecification : SpecificationCS */,
						ms._117 /* assign V1 = |name| */,
						ms._051 /* assert (|stereotype.'invariant'| - 1) == 0 */,
						ms._072 /* assign V0 = |isCallable.'callable'| */,
						ms._160 /* assign V2 = |ownedMessageSpecification| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-9-steps */,
						st._163 /* V00*'callable' */,
						st._041 /* 1*'invariant' */,
						st._181 /* V01*next-5-steps */,
						st._074 /* 1*name=UnrestrictedName */,
						st._189 /* V02*next-3-steps */,
						st._003 /* 1*'(' */,
						st._107 /* 1*ownedMessageSpecification=SpecificationCS */,
						st._004 /* 1*')' */,
						st._011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._2 /* «! » + «value» + «! » */,
						null,
						ss._5 /* «! » + «value» */,
						ss._4 /* «! » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _161
				= /* { isCallable='callable'[?] stereotype='invariant' { name=UnrestrictedName { '(' ownedMessageSpecification=SpecificationCS ')' }[?] }[?] ':' ownedSpecification=SpecificationCS[?] ';' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(41, /* OCLinEcore::InvariantConstraintCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._260 /* check-rule basecs::ConstraintCS.ownedMessageSpecification : SpecificationCS */,
						ms._263 /* check-rule basecs::ConstraintCS.ownedSpecification : SpecificationCS */,
						ms._179 /* assign V3 = |ownedSpecification| */,
						ms._117 /* assign V1 = |name| */,
						ms._051 /* assert (|stereotype.'invariant'| - 1) == 0 */,
						ms._072 /* assign V0 = |isCallable.'callable'| */,
						ms._160 /* assign V2 = |ownedMessageSpecification| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-11-steps */,
						st._163 /* V00*'callable' */,
						st._041 /* 1*'invariant' */,
						st._181 /* V01*next-5-steps */,
						st._074 /* 1*name=UnrestrictedName */,
						st._189 /* V02*next-3-steps */,
						st._003 /* 1*'(' */,
						st._107 /* 1*ownedMessageSpecification=SpecificationCS */,
						st._004 /* 1*')' */,
						st._009 /* 1*':' */,
						st._200 /* V03*ownedSpecification=SpecificationCS */,
						st._011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._2 /* «! » + «value» + «! » */,
						null,
						ss._5 /* «! » + «value» */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._4 /* «! » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _162
				= /* { 'reference' ownedPathName=PathNameCS ';' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(54, /* OCLinEcore::ModelElementRefCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._310 /* check-rule basecs::ModelElementRefCS.ownedPathName : PathNameCS */,
						ms._032 /* assert (|ownedPathName| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-3-steps */,
						st._054 /* 1*'reference' */,
						st._123 /* 1*ownedPathName=PathNameCS */,
						st._011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._4 /* «! » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _163
				= /* { qualifiers+='static' qualifiers+='definition'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { 'body' ':' ownedBodyExpressions+=SpecificationCS[?] ';' }[*] ownedPostconditions+=PostconditionConstraintCS[*] '}' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(70, /* OCLinEcore::OperationCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._301 /* check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationElementCS */,
						ms._334 /* check-rule basecs::OperationCS.ownedParameters : ParameterCS */,
						ms._352 /* check-rule basecs::OperationCS.ownedPreconditions : PreconditionConstraintCS */,
						ms._316 /* check-rule basecs::OperationCS.ownedBodyExpressions : SpecificationCS */,
						ms._346 /* check-rule basecs::OperationCS.ownedPostconditions : PostconditionConstraintCS */,
						ms._409 /* check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS */,
						ms._327 /* check-rule basecs::OperationCS.ownedExceptions : TypedRefCS */,
						ms._466 /* check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS */,
						ms._147 /* assign V13 = |ownedPostconditions| */,
						ms._138 /* assign V10 = |ownedPreconditions| */,
						ms._225 /* assign V9 = |ownedAnnotations| */,
						ms._190 /* assign V4 = |ownedType| */,
						ms._005 /* assert (|name| - 1) == 0 */,
						ms._128 /* assign V1 = |ownedSignature| */,
						ms._095 /* assign V0 = |qualifiers.'definition'| */,
						ms._048 /* assert (|qualifiers.'static'| - 1) == 0 */,
						ms._139 /* assign V11 = (|ownedBodyExpressions| > 0) */,
						ms._143 /* assign V12 = |ownedBodyExpressions| */,
						ms._195 /* assign V5 = (|ownedExceptions| > 0) */,
						ms._203 /* assign V6 = (|ownedExceptions| - 1) */,
						ms._151 /* assign V2 = (|ownedParameters| > 0) */,
						ms._168 /* assign V3 = (|ownedParameters| - 1) */,
						ms._211 /* assign V7 = (|qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| > 0) */,
						ms._222 /* assign V8 = |qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-36-steps */,
						st._056 /* 1*'static' */,
						st._164 /* V00*'definition' */,
						st._047 /* 1*'operation' */,
						st._188 /* V01*ownedSignature=TemplateSignatureCS */,
						st._074 /* 1*name=UnrestrictedName */,
						st._003 /* 1*'(' */,
						st._189 /* V02*next-4-steps */,
						st._109 /* 1*ownedParameters+=ParameterCS */,
						st._196 /* V03*next-2-steps */,
						st._007 /* 1*',' */,
						st._109 /* 1*ownedParameters+=ParameterCS */,
						st._004 /* 1*')' */,
						st._202 /* V04*next-2-steps */,
						st._009 /* 1*':' */,
						st._143 /* 1*ownedType=TypedMultiplicityRefCS */,
						st._207 /* V05*next-5-steps */,
						st._059 /* 1*'throws' */,
						st._088 /* 1*ownedExceptions+=TypedRefCS */,
						st._213 /* V06*next-2-steps */,
						st._007 /* 1*',' */,
						st._088 /* 1*ownedExceptions+=TypedRefCS */,
						st._217 /* V07*next-4-steps */,
						st._060 /* 1*'{' */,
						st._220 /* V08*next-1-steps */,
						st._147 /* 1*qualifiers */,
						st._063 /* 1*'}' */,
						st._060 /* 1*'{' */,
						st._225 /* V09*ownedAnnotations+=AnnotationElementCS */,
						st._232 /* V10*ownedPreconditions+=PreconditionConstraintCS */,
						st._233 /* V11*next-4-steps */,
						st._025 /* 1*'body' */,
						st._009 /* 1*':' */,
						st._237 /* V12*ownedBodyExpressions+=SpecificationCS */,
						st._011 /* 1*';' */,
						st._241 /* V13*ownedPostconditions+=PostconditionConstraintCS */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._2 /* «! » + «value» + «! » */,
						null,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						ss._5 /* «! » + «value» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._4 /* «! » + «value» + «?\n» */,
						null,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _164
				= /* { 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { 'body' ':' ownedBodyExpressions+=SpecificationCS[?] ';' }[*] ownedPostconditions+=PostconditionConstraintCS[*] '}' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(70, /* OCLinEcore::OperationCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._288 /* check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationElementCS */,
						ms._336 /* check-rule basecs::OperationCS.ownedParameters : ParameterCS */,
						ms._350 /* check-rule basecs::OperationCS.ownedPreconditions : PreconditionConstraintCS */,
						ms._314 /* check-rule basecs::OperationCS.ownedBodyExpressions : SpecificationCS */,
						ms._344 /* check-rule basecs::OperationCS.ownedPostconditions : PostconditionConstraintCS */,
						ms._425 /* check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS */,
						ms._325 /* check-rule basecs::OperationCS.ownedExceptions : TypedRefCS */,
						ms._462 /* check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS */,
						ms._145 /* assign V12 = |ownedPostconditions| */,
						ms._227 /* assign V9 = |ownedPreconditions| */,
						ms._219 /* assign V8 = |ownedAnnotations| */,
						ms._180 /* assign V3 = |ownedType| */,
						ms._005 /* assert (|name| - 1) == 0 */,
						ms._087 /* assign V0 = |ownedSignature| */,
						ms._134 /* assign V10 = (|ownedBodyExpressions| > 0) */,
						ms._141 /* assign V11 = |ownedBodyExpressions| */,
						ms._204 /* assign V6 = (|qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| > 0) */,
						ms._216 /* assign V7 = |qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| */,
						ms._182 /* assign V4 = (|ownedExceptions| > 0) */,
						ms._194 /* assign V5 = (|ownedExceptions| - 1) */,
						ms._106 /* assign V1 = (|ownedParameters| > 0) */,
						ms._150 /* assign V2 = (|ownedParameters| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-34-steps */,
						st._047 /* 1*'operation' */,
						st._173 /* V00*ownedSignature=TemplateSignatureCS */,
						st._074 /* 1*name=UnrestrictedName */,
						st._003 /* 1*'(' */,
						st._181 /* V01*next-4-steps */,
						st._109 /* 1*ownedParameters+=ParameterCS */,
						st._189 /* V02*next-2-steps */,
						st._007 /* 1*',' */,
						st._109 /* 1*ownedParameters+=ParameterCS */,
						st._004 /* 1*')' */,
						st._196 /* V03*next-2-steps */,
						st._009 /* 1*':' */,
						st._143 /* 1*ownedType=TypedMultiplicityRefCS */,
						st._202 /* V04*next-5-steps */,
						st._059 /* 1*'throws' */,
						st._088 /* 1*ownedExceptions+=TypedRefCS */,
						st._207 /* V05*next-2-steps */,
						st._007 /* 1*',' */,
						st._088 /* 1*ownedExceptions+=TypedRefCS */,
						st._213 /* V06*next-4-steps */,
						st._060 /* 1*'{' */,
						st._217 /* V07*next-1-steps */,
						st._147 /* 1*qualifiers */,
						st._063 /* 1*'}' */,
						st._060 /* 1*'{' */,
						st._221 /* V08*ownedAnnotations+=AnnotationElementCS */,
						st._227 /* V09*ownedPreconditions+=PreconditionConstraintCS */,
						st._229 /* V10*next-4-steps */,
						st._025 /* 1*'body' */,
						st._009 /* 1*':' */,
						st._234 /* V11*ownedBodyExpressions+=SpecificationCS */,
						st._011 /* 1*';' */,
						st._239 /* V12*ownedPostconditions+=PostconditionConstraintCS */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._2 /* «! » + «value» + «! » */,
						null,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						ss._5 /* «! » + «value» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._4 /* «! » + «value» + «?\n» */,
						null,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _165
				= /* { qualifiers+='static' qualifiers+='definition'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { 'body' ':' ownedBodyExpressions+=SpecificationCS[?] ';' }[*] ownedPostconditions+=PostconditionConstraintCS[*] '}' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(70, /* OCLinEcore::OperationCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._293 /* check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationElementCS */,
						ms._338 /* check-rule basecs::OperationCS.ownedParameters : ParameterCS */,
						ms._349 /* check-rule basecs::OperationCS.ownedPreconditions : PreconditionConstraintCS */,
						ms._313 /* check-rule basecs::OperationCS.ownedBodyExpressions : SpecificationCS */,
						ms._343 /* check-rule basecs::OperationCS.ownedPostconditions : PostconditionConstraintCS */,
						ms._423 /* check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS */,
						ms._323 /* check-rule basecs::OperationCS.ownedExceptions : TypedRefCS */,
						ms._440 /* check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS */,
						ms._147 /* assign V13 = |ownedPostconditions| */,
						ms._138 /* assign V10 = |ownedPreconditions| */,
						ms._225 /* assign V9 = |ownedAnnotations| */,
						ms._190 /* assign V4 = |ownedType| */,
						ms._005 /* assert (|name| - 1) == 0 */,
						ms._128 /* assign V1 = |ownedSignature| */,
						ms._095 /* assign V0 = |qualifiers.'definition'| */,
						ms._048 /* assert (|qualifiers.'static'| - 1) == 0 */,
						ms._139 /* assign V11 = (|ownedBodyExpressions| > 0) */,
						ms._143 /* assign V12 = |ownedBodyExpressions| */,
						ms._195 /* assign V5 = (|ownedExceptions| > 0) */,
						ms._203 /* assign V6 = (|ownedExceptions| - 1) */,
						ms._151 /* assign V2 = (|ownedParameters| > 0) */,
						ms._168 /* assign V3 = (|ownedParameters| - 1) */,
						ms._211 /* assign V7 = (|qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| > 0) */,
						ms._222 /* assign V8 = |qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-36-steps */,
						st._056 /* 1*'static' */,
						st._164 /* V00*'definition' */,
						st._047 /* 1*'operation' */,
						st._188 /* V01*ownedSignature=TemplateSignatureCS */,
						st._074 /* 1*name=UnrestrictedName */,
						st._003 /* 1*'(' */,
						st._189 /* V02*next-4-steps */,
						st._109 /* 1*ownedParameters+=ParameterCS */,
						st._196 /* V03*next-2-steps */,
						st._007 /* 1*',' */,
						st._109 /* 1*ownedParameters+=ParameterCS */,
						st._004 /* 1*')' */,
						st._202 /* V04*next-2-steps */,
						st._009 /* 1*':' */,
						st._143 /* 1*ownedType=TypedMultiplicityRefCS */,
						st._207 /* V05*next-5-steps */,
						st._059 /* 1*'throws' */,
						st._088 /* 1*ownedExceptions+=TypedRefCS */,
						st._213 /* V06*next-2-steps */,
						st._007 /* 1*',' */,
						st._088 /* 1*ownedExceptions+=TypedRefCS */,
						st._217 /* V07*next-4-steps */,
						st._060 /* 1*'{' */,
						st._220 /* V08*next-1-steps */,
						st._147 /* 1*qualifiers */,
						st._063 /* 1*'}' */,
						st._060 /* 1*'{' */,
						st._225 /* V09*ownedAnnotations+=AnnotationElementCS */,
						st._232 /* V10*ownedPreconditions+=PreconditionConstraintCS */,
						st._233 /* V11*next-4-steps */,
						st._025 /* 1*'body' */,
						st._009 /* 1*':' */,
						st._237 /* V12*ownedBodyExpressions+=SpecificationCS */,
						st._011 /* 1*';' */,
						st._241 /* V13*ownedPostconditions+=PostconditionConstraintCS */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._2 /* «! » + «value» + «! » */,
						null,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						ss._5 /* «! » + «value» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._4 /* «! » + «value» + «?\n» */,
						null,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _166
				= /* { qualifiers+='static' qualifiers+='definition'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] ';' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(70, /* OCLinEcore::OperationCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._332 /* check-rule basecs::OperationCS.ownedParameters : ParameterCS */,
						ms._400 /* check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS */,
						ms._319 /* check-rule basecs::OperationCS.ownedExceptions : TypedRefCS */,
						ms._467 /* check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS */,
						ms._190 /* assign V4 = |ownedType| */,
						ms._005 /* assert (|name| - 1) == 0 */,
						ms._128 /* assign V1 = |ownedSignature| */,
						ms._095 /* assign V0 = |qualifiers.'definition'| */,
						ms._048 /* assert (|qualifiers.'static'| - 1) == 0 */,
						ms._195 /* assign V5 = (|ownedExceptions| > 0) */,
						ms._203 /* assign V6 = (|ownedExceptions| - 1) */,
						ms._151 /* assign V2 = (|ownedParameters| > 0) */,
						ms._168 /* assign V3 = (|ownedParameters| - 1) */,
						ms._211 /* assign V7 = (|qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| > 0) */,
						ms._222 /* assign V8 = |qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-27-steps */,
						st._056 /* 1*'static' */,
						st._164 /* V00*'definition' */,
						st._047 /* 1*'operation' */,
						st._188 /* V01*ownedSignature=TemplateSignatureCS */,
						st._074 /* 1*name=UnrestrictedName */,
						st._003 /* 1*'(' */,
						st._189 /* V02*next-4-steps */,
						st._109 /* 1*ownedParameters+=ParameterCS */,
						st._196 /* V03*next-2-steps */,
						st._007 /* 1*',' */,
						st._109 /* 1*ownedParameters+=ParameterCS */,
						st._004 /* 1*')' */,
						st._202 /* V04*next-2-steps */,
						st._009 /* 1*':' */,
						st._143 /* 1*ownedType=TypedMultiplicityRefCS */,
						st._207 /* V05*next-5-steps */,
						st._059 /* 1*'throws' */,
						st._088 /* 1*ownedExceptions+=TypedRefCS */,
						st._213 /* V06*next-2-steps */,
						st._007 /* 1*',' */,
						st._088 /* 1*ownedExceptions+=TypedRefCS */,
						st._217 /* V07*next-4-steps */,
						st._060 /* 1*'{' */,
						st._220 /* V08*next-1-steps */,
						st._147 /* 1*qualifiers */,
						st._063 /* 1*'}' */,
						st._011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._2 /* «! » + «value» + «! » */,
						null,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						ss._5 /* «! » + «value» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._4 /* «! » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _167
				= /* { 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { 'body' ':' ownedBodyExpressions+=SpecificationCS[?] ';' }[*] ownedPostconditions+=PostconditionConstraintCS[*] '}' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(70, /* OCLinEcore::OperationCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._284 /* check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationElementCS */,
						ms._329 /* check-rule basecs::OperationCS.ownedParameters : ParameterCS */,
						ms._347 /* check-rule basecs::OperationCS.ownedPreconditions : PreconditionConstraintCS */,
						ms._311 /* check-rule basecs::OperationCS.ownedBodyExpressions : SpecificationCS */,
						ms._341 /* check-rule basecs::OperationCS.ownedPostconditions : PostconditionConstraintCS */,
						ms._393 /* check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS */,
						ms._320 /* check-rule basecs::OperationCS.ownedExceptions : TypedRefCS */,
						ms._461 /* check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS */,
						ms._145 /* assign V12 = |ownedPostconditions| */,
						ms._227 /* assign V9 = |ownedPreconditions| */,
						ms._219 /* assign V8 = |ownedAnnotations| */,
						ms._180 /* assign V3 = |ownedType| */,
						ms._005 /* assert (|name| - 1) == 0 */,
						ms._087 /* assign V0 = |ownedSignature| */,
						ms._134 /* assign V10 = (|ownedBodyExpressions| > 0) */,
						ms._141 /* assign V11 = |ownedBodyExpressions| */,
						ms._204 /* assign V6 = (|qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| > 0) */,
						ms._216 /* assign V7 = |qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| */,
						ms._182 /* assign V4 = (|ownedExceptions| > 0) */,
						ms._194 /* assign V5 = (|ownedExceptions| - 1) */,
						ms._106 /* assign V1 = (|ownedParameters| > 0) */,
						ms._150 /* assign V2 = (|ownedParameters| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-34-steps */,
						st._047 /* 1*'operation' */,
						st._173 /* V00*ownedSignature=TemplateSignatureCS */,
						st._074 /* 1*name=UnrestrictedName */,
						st._003 /* 1*'(' */,
						st._181 /* V01*next-4-steps */,
						st._109 /* 1*ownedParameters+=ParameterCS */,
						st._189 /* V02*next-2-steps */,
						st._007 /* 1*',' */,
						st._109 /* 1*ownedParameters+=ParameterCS */,
						st._004 /* 1*')' */,
						st._196 /* V03*next-2-steps */,
						st._009 /* 1*':' */,
						st._143 /* 1*ownedType=TypedMultiplicityRefCS */,
						st._202 /* V04*next-5-steps */,
						st._059 /* 1*'throws' */,
						st._088 /* 1*ownedExceptions+=TypedRefCS */,
						st._207 /* V05*next-2-steps */,
						st._007 /* 1*',' */,
						st._088 /* 1*ownedExceptions+=TypedRefCS */,
						st._213 /* V06*next-4-steps */,
						st._060 /* 1*'{' */,
						st._217 /* V07*next-1-steps */,
						st._147 /* 1*qualifiers */,
						st._063 /* 1*'}' */,
						st._060 /* 1*'{' */,
						st._221 /* V08*ownedAnnotations+=AnnotationElementCS */,
						st._227 /* V09*ownedPreconditions+=PreconditionConstraintCS */,
						st._229 /* V10*next-4-steps */,
						st._025 /* 1*'body' */,
						st._009 /* 1*':' */,
						st._234 /* V11*ownedBodyExpressions+=SpecificationCS */,
						st._011 /* 1*';' */,
						st._239 /* V12*ownedPostconditions+=PostconditionConstraintCS */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._2 /* «! » + «value» + «! » */,
						null,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						ss._5 /* «! » + «value» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._4 /* «! » + «value» + «?\n» */,
						null,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _168
				= /* { qualifiers+='definition' qualifiers+='static'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] ';' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(70, /* OCLinEcore::OperationCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._331 /* check-rule basecs::OperationCS.ownedParameters : ParameterCS */,
						ms._422 /* check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS */,
						ms._317 /* check-rule basecs::OperationCS.ownedExceptions : TypedRefCS */,
						ms._468 /* check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS */,
						ms._190 /* assign V4 = |ownedType| */,
						ms._005 /* assert (|name| - 1) == 0 */,
						ms._128 /* assign V1 = |ownedSignature| */,
						ms._096 /* assign V0 = |qualifiers.'static'| */,
						ms._047 /* assert (|qualifiers.'definition'| - 1) == 0 */,
						ms._195 /* assign V5 = (|ownedExceptions| > 0) */,
						ms._203 /* assign V6 = (|ownedExceptions| - 1) */,
						ms._151 /* assign V2 = (|ownedParameters| > 0) */,
						ms._168 /* assign V3 = (|ownedParameters| - 1) */,
						ms._211 /* assign V7 = (|qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| > 0) */,
						ms._222 /* assign V8 = |qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-27-steps */,
						st._028 /* 1*'definition' */,
						st._166 /* V00*'static' */,
						st._047 /* 1*'operation' */,
						st._188 /* V01*ownedSignature=TemplateSignatureCS */,
						st._074 /* 1*name=UnrestrictedName */,
						st._003 /* 1*'(' */,
						st._189 /* V02*next-4-steps */,
						st._109 /* 1*ownedParameters+=ParameterCS */,
						st._196 /* V03*next-2-steps */,
						st._007 /* 1*',' */,
						st._109 /* 1*ownedParameters+=ParameterCS */,
						st._004 /* 1*')' */,
						st._202 /* V04*next-2-steps */,
						st._009 /* 1*':' */,
						st._143 /* 1*ownedType=TypedMultiplicityRefCS */,
						st._207 /* V05*next-5-steps */,
						st._059 /* 1*'throws' */,
						st._088 /* 1*ownedExceptions+=TypedRefCS */,
						st._213 /* V06*next-2-steps */,
						st._007 /* 1*',' */,
						st._088 /* 1*ownedExceptions+=TypedRefCS */,
						st._217 /* V07*next-4-steps */,
						st._060 /* 1*'{' */,
						st._220 /* V08*next-1-steps */,
						st._147 /* 1*qualifiers */,
						st._063 /* 1*'}' */,
						st._011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._2 /* «! » + «value» + «! » */,
						null,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						ss._5 /* «! » + «value» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._4 /* «! » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _169
				= /* { qualifiers+='definition' qualifiers+='static'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { 'body' ':' ownedBodyExpressions+=SpecificationCS[?] ';' }[*] ownedPostconditions+=PostconditionConstraintCS[*] '}' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(70, /* OCLinEcore::OperationCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._294 /* check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationElementCS */,
						ms._333 /* check-rule basecs::OperationCS.ownedParameters : ParameterCS */,
						ms._351 /* check-rule basecs::OperationCS.ownedPreconditions : PreconditionConstraintCS */,
						ms._315 /* check-rule basecs::OperationCS.ownedBodyExpressions : SpecificationCS */,
						ms._345 /* check-rule basecs::OperationCS.ownedPostconditions : PostconditionConstraintCS */,
						ms._407 /* check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS */,
						ms._328 /* check-rule basecs::OperationCS.ownedExceptions : TypedRefCS */,
						ms._452 /* check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS */,
						ms._147 /* assign V13 = |ownedPostconditions| */,
						ms._138 /* assign V10 = |ownedPreconditions| */,
						ms._225 /* assign V9 = |ownedAnnotations| */,
						ms._190 /* assign V4 = |ownedType| */,
						ms._005 /* assert (|name| - 1) == 0 */,
						ms._128 /* assign V1 = |ownedSignature| */,
						ms._096 /* assign V0 = |qualifiers.'static'| */,
						ms._047 /* assert (|qualifiers.'definition'| - 1) == 0 */,
						ms._139 /* assign V11 = (|ownedBodyExpressions| > 0) */,
						ms._143 /* assign V12 = |ownedBodyExpressions| */,
						ms._195 /* assign V5 = (|ownedExceptions| > 0) */,
						ms._203 /* assign V6 = (|ownedExceptions| - 1) */,
						ms._151 /* assign V2 = (|ownedParameters| > 0) */,
						ms._168 /* assign V3 = (|ownedParameters| - 1) */,
						ms._211 /* assign V7 = (|qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| > 0) */,
						ms._222 /* assign V8 = |qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-36-steps */,
						st._028 /* 1*'definition' */,
						st._166 /* V00*'static' */,
						st._047 /* 1*'operation' */,
						st._188 /* V01*ownedSignature=TemplateSignatureCS */,
						st._074 /* 1*name=UnrestrictedName */,
						st._003 /* 1*'(' */,
						st._189 /* V02*next-4-steps */,
						st._109 /* 1*ownedParameters+=ParameterCS */,
						st._196 /* V03*next-2-steps */,
						st._007 /* 1*',' */,
						st._109 /* 1*ownedParameters+=ParameterCS */,
						st._004 /* 1*')' */,
						st._202 /* V04*next-2-steps */,
						st._009 /* 1*':' */,
						st._143 /* 1*ownedType=TypedMultiplicityRefCS */,
						st._207 /* V05*next-5-steps */,
						st._059 /* 1*'throws' */,
						st._088 /* 1*ownedExceptions+=TypedRefCS */,
						st._213 /* V06*next-2-steps */,
						st._007 /* 1*',' */,
						st._088 /* 1*ownedExceptions+=TypedRefCS */,
						st._217 /* V07*next-4-steps */,
						st._060 /* 1*'{' */,
						st._220 /* V08*next-1-steps */,
						st._147 /* 1*qualifiers */,
						st._063 /* 1*'}' */,
						st._060 /* 1*'{' */,
						st._225 /* V09*ownedAnnotations+=AnnotationElementCS */,
						st._232 /* V10*ownedPreconditions+=PreconditionConstraintCS */,
						st._233 /* V11*next-4-steps */,
						st._025 /* 1*'body' */,
						st._009 /* 1*':' */,
						st._237 /* V12*ownedBodyExpressions+=SpecificationCS */,
						st._011 /* 1*';' */,
						st._241 /* V13*ownedPostconditions+=PostconditionConstraintCS */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._2 /* «! » + «value» + «! » */,
						null,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						ss._5 /* «! » + «value» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._4 /* «! » + «value» + «?\n» */,
						null,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _170
				= /* { qualifiers+='definition' qualifiers+='static'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPreconditions+=PreconditionConstraintCS[*] { 'body' ':' ownedBodyExpressions+=SpecificationCS[?] ';' }[*] ownedPostconditions+=PostconditionConstraintCS[*] '}' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(70, /* OCLinEcore::OperationCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._279 /* check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationElementCS */,
						ms._337 /* check-rule basecs::OperationCS.ownedParameters : ParameterCS */,
						ms._348 /* check-rule basecs::OperationCS.ownedPreconditions : PreconditionConstraintCS */,
						ms._312 /* check-rule basecs::OperationCS.ownedBodyExpressions : SpecificationCS */,
						ms._342 /* check-rule basecs::OperationCS.ownedPostconditions : PostconditionConstraintCS */,
						ms._394 /* check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS */,
						ms._322 /* check-rule basecs::OperationCS.ownedExceptions : TypedRefCS */,
						ms._446 /* check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS */,
						ms._147 /* assign V13 = |ownedPostconditions| */,
						ms._138 /* assign V10 = |ownedPreconditions| */,
						ms._225 /* assign V9 = |ownedAnnotations| */,
						ms._190 /* assign V4 = |ownedType| */,
						ms._005 /* assert (|name| - 1) == 0 */,
						ms._128 /* assign V1 = |ownedSignature| */,
						ms._096 /* assign V0 = |qualifiers.'static'| */,
						ms._047 /* assert (|qualifiers.'definition'| - 1) == 0 */,
						ms._139 /* assign V11 = (|ownedBodyExpressions| > 0) */,
						ms._143 /* assign V12 = |ownedBodyExpressions| */,
						ms._195 /* assign V5 = (|ownedExceptions| > 0) */,
						ms._203 /* assign V6 = (|ownedExceptions| - 1) */,
						ms._151 /* assign V2 = (|ownedParameters| > 0) */,
						ms._168 /* assign V3 = (|ownedParameters| - 1) */,
						ms._211 /* assign V7 = (|qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| > 0) */,
						ms._222 /* assign V8 = |qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-36-steps */,
						st._028 /* 1*'definition' */,
						st._166 /* V00*'static' */,
						st._047 /* 1*'operation' */,
						st._188 /* V01*ownedSignature=TemplateSignatureCS */,
						st._074 /* 1*name=UnrestrictedName */,
						st._003 /* 1*'(' */,
						st._189 /* V02*next-4-steps */,
						st._109 /* 1*ownedParameters+=ParameterCS */,
						st._196 /* V03*next-2-steps */,
						st._007 /* 1*',' */,
						st._109 /* 1*ownedParameters+=ParameterCS */,
						st._004 /* 1*')' */,
						st._202 /* V04*next-2-steps */,
						st._009 /* 1*':' */,
						st._143 /* 1*ownedType=TypedMultiplicityRefCS */,
						st._207 /* V05*next-5-steps */,
						st._059 /* 1*'throws' */,
						st._088 /* 1*ownedExceptions+=TypedRefCS */,
						st._213 /* V06*next-2-steps */,
						st._007 /* 1*',' */,
						st._088 /* 1*ownedExceptions+=TypedRefCS */,
						st._217 /* V07*next-4-steps */,
						st._060 /* 1*'{' */,
						st._220 /* V08*next-1-steps */,
						st._147 /* 1*qualifiers */,
						st._063 /* 1*'}' */,
						st._060 /* 1*'{' */,
						st._225 /* V09*ownedAnnotations+=AnnotationElementCS */,
						st._232 /* V10*ownedPreconditions+=PreconditionConstraintCS */,
						st._233 /* V11*next-4-steps */,
						st._025 /* 1*'body' */,
						st._009 /* 1*':' */,
						st._237 /* V12*ownedBodyExpressions+=SpecificationCS */,
						st._011 /* 1*';' */,
						st._241 /* V13*ownedPostconditions+=PostconditionConstraintCS */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._2 /* «! » + «value» + «! » */,
						null,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						ss._5 /* «! » + «value» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._4 /* «! » + «value» + «?\n» */,
						null,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _171
				= /* { 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] ';' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(70, /* OCLinEcore::OperationCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._330 /* check-rule basecs::OperationCS.ownedParameters : ParameterCS */,
						ms._427 /* check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS */,
						ms._318 /* check-rule basecs::OperationCS.ownedExceptions : TypedRefCS */,
						ms._465 /* check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS */,
						ms._180 /* assign V3 = |ownedType| */,
						ms._005 /* assert (|name| - 1) == 0 */,
						ms._087 /* assign V0 = |ownedSignature| */,
						ms._204 /* assign V6 = (|qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| > 0) */,
						ms._216 /* assign V7 = |qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| */,
						ms._182 /* assign V4 = (|ownedExceptions| > 0) */,
						ms._194 /* assign V5 = (|ownedExceptions| - 1) */,
						ms._106 /* assign V1 = (|ownedParameters| > 0) */,
						ms._150 /* assign V2 = (|ownedParameters| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-25-steps */,
						st._047 /* 1*'operation' */,
						st._173 /* V00*ownedSignature=TemplateSignatureCS */,
						st._074 /* 1*name=UnrestrictedName */,
						st._003 /* 1*'(' */,
						st._181 /* V01*next-4-steps */,
						st._109 /* 1*ownedParameters+=ParameterCS */,
						st._189 /* V02*next-2-steps */,
						st._007 /* 1*',' */,
						st._109 /* 1*ownedParameters+=ParameterCS */,
						st._004 /* 1*')' */,
						st._196 /* V03*next-2-steps */,
						st._009 /* 1*':' */,
						st._143 /* 1*ownedType=TypedMultiplicityRefCS */,
						st._202 /* V04*next-5-steps */,
						st._059 /* 1*'throws' */,
						st._088 /* 1*ownedExceptions+=TypedRefCS */,
						st._207 /* V05*next-2-steps */,
						st._007 /* 1*',' */,
						st._088 /* 1*ownedExceptions+=TypedRefCS */,
						st._213 /* V06*next-4-steps */,
						st._060 /* 1*'{' */,
						st._217 /* V07*next-1-steps */,
						st._147 /* 1*qualifiers */,
						st._063 /* 1*'}' */,
						st._011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._2 /* «! » + «value» + «! » */,
						null,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						ss._5 /* «! » + «value» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._4 /* «! » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _172
				= /* { qualifiers+='static' qualifiers+='definition'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] ';' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(70, /* OCLinEcore::OperationCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._335 /* check-rule basecs::OperationCS.ownedParameters : ParameterCS */,
						ms._415 /* check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS */,
						ms._321 /* check-rule basecs::OperationCS.ownedExceptions : TypedRefCS */,
						ms._447 /* check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS */,
						ms._190 /* assign V4 = |ownedType| */,
						ms._005 /* assert (|name| - 1) == 0 */,
						ms._128 /* assign V1 = |ownedSignature| */,
						ms._095 /* assign V0 = |qualifiers.'definition'| */,
						ms._048 /* assert (|qualifiers.'static'| - 1) == 0 */,
						ms._195 /* assign V5 = (|ownedExceptions| > 0) */,
						ms._203 /* assign V6 = (|ownedExceptions| - 1) */,
						ms._151 /* assign V2 = (|ownedParameters| > 0) */,
						ms._168 /* assign V3 = (|ownedParameters| - 1) */,
						ms._211 /* assign V7 = (|qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| > 0) */,
						ms._222 /* assign V8 = |qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-27-steps */,
						st._056 /* 1*'static' */,
						st._164 /* V00*'definition' */,
						st._047 /* 1*'operation' */,
						st._188 /* V01*ownedSignature=TemplateSignatureCS */,
						st._074 /* 1*name=UnrestrictedName */,
						st._003 /* 1*'(' */,
						st._189 /* V02*next-4-steps */,
						st._109 /* 1*ownedParameters+=ParameterCS */,
						st._196 /* V03*next-2-steps */,
						st._007 /* 1*',' */,
						st._109 /* 1*ownedParameters+=ParameterCS */,
						st._004 /* 1*')' */,
						st._202 /* V04*next-2-steps */,
						st._009 /* 1*':' */,
						st._143 /* 1*ownedType=TypedMultiplicityRefCS */,
						st._207 /* V05*next-5-steps */,
						st._059 /* 1*'throws' */,
						st._088 /* 1*ownedExceptions+=TypedRefCS */,
						st._213 /* V06*next-2-steps */,
						st._007 /* 1*',' */,
						st._088 /* 1*ownedExceptions+=TypedRefCS */,
						st._217 /* V07*next-4-steps */,
						st._060 /* 1*'{' */,
						st._220 /* V08*next-1-steps */,
						st._147 /* 1*qualifiers */,
						st._063 /* 1*'}' */,
						st._011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._2 /* «! » + «value» + «! » */,
						null,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						ss._5 /* «! » + «value» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._4 /* «! » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _173
				= /* { qualifiers+='definition' qualifiers+='static'[?] 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] ';' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(70, /* OCLinEcore::OperationCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._340 /* check-rule basecs::OperationCS.ownedParameters : ParameterCS */,
						ms._411 /* check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS */,
						ms._324 /* check-rule basecs::OperationCS.ownedExceptions : TypedRefCS */,
						ms._456 /* check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS */,
						ms._190 /* assign V4 = |ownedType| */,
						ms._005 /* assert (|name| - 1) == 0 */,
						ms._128 /* assign V1 = |ownedSignature| */,
						ms._096 /* assign V0 = |qualifiers.'static'| */,
						ms._047 /* assert (|qualifiers.'definition'| - 1) == 0 */,
						ms._195 /* assign V5 = (|ownedExceptions| > 0) */,
						ms._203 /* assign V6 = (|ownedExceptions| - 1) */,
						ms._151 /* assign V2 = (|ownedParameters| > 0) */,
						ms._168 /* assign V3 = (|ownedParameters| - 1) */,
						ms._211 /* assign V7 = (|qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| > 0) */,
						ms._222 /* assign V8 = |qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-27-steps */,
						st._028 /* 1*'definition' */,
						st._166 /* V00*'static' */,
						st._047 /* 1*'operation' */,
						st._188 /* V01*ownedSignature=TemplateSignatureCS */,
						st._074 /* 1*name=UnrestrictedName */,
						st._003 /* 1*'(' */,
						st._189 /* V02*next-4-steps */,
						st._109 /* 1*ownedParameters+=ParameterCS */,
						st._196 /* V03*next-2-steps */,
						st._007 /* 1*',' */,
						st._109 /* 1*ownedParameters+=ParameterCS */,
						st._004 /* 1*')' */,
						st._202 /* V04*next-2-steps */,
						st._009 /* 1*':' */,
						st._143 /* 1*ownedType=TypedMultiplicityRefCS */,
						st._207 /* V05*next-5-steps */,
						st._059 /* 1*'throws' */,
						st._088 /* 1*ownedExceptions+=TypedRefCS */,
						st._213 /* V06*next-2-steps */,
						st._007 /* 1*',' */,
						st._088 /* 1*ownedExceptions+=TypedRefCS */,
						st._217 /* V07*next-4-steps */,
						st._060 /* 1*'{' */,
						st._220 /* V08*next-1-steps */,
						st._147 /* 1*qualifiers */,
						st._063 /* 1*'}' */,
						st._011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._2 /* «! » + «value» + «! » */,
						null,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						ss._5 /* «! » + «value» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._4 /* «! » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _174
				= /* { 'operation' ownedSignature=TemplateSignatureCS[?] name=UnrestrictedName '(' { ownedParameters+=ParameterCS { ',' ownedParameters+=ParameterCS }[*] }[?] ')' { ':' ownedType=TypedMultiplicityRefCS }[?] { 'throws' ownedExceptions+=TypedRefCS { ',' ownedExceptions+=TypedRefCS }[*] }[?] { '{' { qualifiers+={'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'} }[+] '}' }[?] ';' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(70, /* OCLinEcore::OperationCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._339 /* check-rule basecs::OperationCS.ownedParameters : ParameterCS */,
						ms._428 /* check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS */,
						ms._326 /* check-rule basecs::OperationCS.ownedExceptions : TypedRefCS */,
						ms._453 /* check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS */,
						ms._180 /* assign V3 = |ownedType| */,
						ms._005 /* assert (|name| - 1) == 0 */,
						ms._087 /* assign V0 = |ownedSignature| */,
						ms._204 /* assign V6 = (|qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| > 0) */,
						ms._216 /* assign V7 = |qualifiers.'!derived|!ordered|!transient|!unique|derived|ordered|transient|unique'| */,
						ms._182 /* assign V4 = (|ownedExceptions| > 0) */,
						ms._194 /* assign V5 = (|ownedExceptions| - 1) */,
						ms._106 /* assign V1 = (|ownedParameters| > 0) */,
						ms._150 /* assign V2 = (|ownedParameters| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-25-steps */,
						st._047 /* 1*'operation' */,
						st._173 /* V00*ownedSignature=TemplateSignatureCS */,
						st._074 /* 1*name=UnrestrictedName */,
						st._003 /* 1*'(' */,
						st._181 /* V01*next-4-steps */,
						st._109 /* 1*ownedParameters+=ParameterCS */,
						st._189 /* V02*next-2-steps */,
						st._007 /* 1*',' */,
						st._109 /* 1*ownedParameters+=ParameterCS */,
						st._004 /* 1*')' */,
						st._196 /* V03*next-2-steps */,
						st._009 /* 1*':' */,
						st._143 /* 1*ownedType=TypedMultiplicityRefCS */,
						st._202 /* V04*next-5-steps */,
						st._059 /* 1*'throws' */,
						st._088 /* 1*ownedExceptions+=TypedRefCS */,
						st._207 /* V05*next-2-steps */,
						st._007 /* 1*',' */,
						st._088 /* 1*ownedExceptions+=TypedRefCS */,
						st._213 /* V06*next-4-steps */,
						st._060 /* 1*'{' */,
						st._217 /* V07*next-1-steps */,
						st._147 /* 1*qualifiers */,
						st._063 /* 1*'}' */,
						st._011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._2 /* «! » + «value» + «! » */,
						null,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						ss._5 /* «! » + «value» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._4 /* «! » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _175
				= /* { 'package' name=UnrestrictedName { ':' nsPrefix=UnrestrictedName }[?] { '=' nsURI=URI }[?] ';' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(71, /* OCLinEcore::PackageCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._118 /* assign V1 = |nsURI| */,
						ms._077 /* assign V0 = |nsPrefix| */,
						ms._005 /* assert (|name| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-9-steps */,
						st._049 /* 1*'package' */,
						st._074 /* 1*name=UnrestrictedName */,
						st._169 /* V00*next-2-steps */,
						st._009 /* 1*':' */,
						st._077 /* 1*nsPrefix=UnrestrictedName */,
						st._181 /* V01*next-2-steps */,
						st._014 /* 1*'=' */,
						st._078 /* 1*nsURI=URI */,
						st._011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._4 /* «! » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _176
				= /* { 'package' name=UnrestrictedName { ':' nsPrefix=UnrestrictedName }[?] { '=' nsURI=URI }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPackages+=PackageCS[*] ownedClasses+=ClassCS[*] '}' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(71, /* OCLinEcore::PackageCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._280 /* check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationElementCS */,
						ms._356 /* check-rule basecs::PackageOwnerCS.ownedPackages : PackageCS */,
						ms._354 /* check-rule basecs::PackageCS.ownedClasses : ClassCS */,
						ms._187 /* assign V4 = |ownedClasses| */,
						ms._178 /* assign V3 = |ownedPackages| */,
						ms._158 /* assign V2 = |ownedAnnotations| */,
						ms._118 /* assign V1 = |nsURI| */,
						ms._077 /* assign V0 = |nsPrefix| */,
						ms._005 /* assert (|name| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-13-steps */,
						st._049 /* 1*'package' */,
						st._074 /* 1*name=UnrestrictedName */,
						st._169 /* V00*next-2-steps */,
						st._009 /* 1*':' */,
						st._077 /* 1*nsPrefix=UnrestrictedName */,
						st._181 /* V01*next-2-steps */,
						st._014 /* 1*'=' */,
						st._078 /* 1*nsURI=URI */,
						st._060 /* 1*'{' */,
						st._190 /* V02*ownedAnnotations+=AnnotationElementCS */,
						st._199 /* V03*ownedPackages+=PackageCS */,
						st._204 /* V04*ownedClasses+=ClassCS */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						null,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _177
				= /* { 'package' name=UnrestrictedName { ':' nsPrefix=UnrestrictedName }[?] { '=' nsURI=URI }[?] ';' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(71, /* OCLinEcore::PackageCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._118 /* assign V1 = |nsURI| */,
						ms._077 /* assign V0 = |nsPrefix| */,
						ms._005 /* assert (|name| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-9-steps */,
						st._049 /* 1*'package' */,
						st._074 /* 1*name=UnrestrictedName */,
						st._169 /* V00*next-2-steps */,
						st._009 /* 1*':' */,
						st._077 /* 1*nsPrefix=UnrestrictedName */,
						st._181 /* V01*next-2-steps */,
						st._014 /* 1*'=' */,
						st._078 /* 1*nsURI=URI */,
						st._011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._4 /* «! » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _178
				= /* { 'package' name=UnrestrictedName { ':' nsPrefix=UnrestrictedName }[?] { '=' nsURI=URI }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedPackages+=PackageCS[*] ownedClasses+=ClassCS[*] '}' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(71, /* OCLinEcore::PackageCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._295 /* check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationElementCS */,
						ms._355 /* check-rule basecs::PackageOwnerCS.ownedPackages : PackageCS */,
						ms._353 /* check-rule basecs::PackageCS.ownedClasses : ClassCS */,
						ms._187 /* assign V4 = |ownedClasses| */,
						ms._178 /* assign V3 = |ownedPackages| */,
						ms._158 /* assign V2 = |ownedAnnotations| */,
						ms._118 /* assign V1 = |nsURI| */,
						ms._077 /* assign V0 = |nsPrefix| */,
						ms._005 /* assert (|name| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-13-steps */,
						st._049 /* 1*'package' */,
						st._074 /* 1*name=UnrestrictedName */,
						st._169 /* V00*next-2-steps */,
						st._009 /* 1*':' */,
						st._077 /* 1*nsPrefix=UnrestrictedName */,
						st._181 /* V01*next-2-steps */,
						st._014 /* 1*'=' */,
						st._078 /* 1*nsURI=URI */,
						st._060 /* 1*'{' */,
						st._190 /* V02*ownedAnnotations+=AnnotationElementCS */,
						st._199 /* V03*ownedPackages+=PackageCS */,
						st._204 /* V04*ownedClasses+=ClassCS */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						null,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _179
				= /* { name=UnrestrictedName { ':' ownedType=TypedMultiplicityRefCS }[?] { '{' { qualifiers+={'!ordered|!unique|ordered|unique'} }[+] '}' }[?] { '{' ownedAnnotations+=AnnotationElementCS[*] '}' }[?] } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(72, /* OCLinEcore::ParameterCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._276 /* check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationElementCS */,
						ms._454 /* check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS */,
						ms._092 /* assign V0 = |ownedType| */,
						ms._005 /* assert (|name| - 1) == 0 */,
						ms._167 /* assign V3 = (|ownedAnnotations| > 0) */,
						ms._186 /* assign V4 = |ownedAnnotations| */,
						ms._112 /* assign V1 = (|qualifiers.'!ordered|!unique|ordered|unique'| > 0) */,
						ms._165 /* assign V2 = |qualifiers.'!ordered|!unique|ordered|unique'| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-13-steps */,
						st._074 /* 1*name=UnrestrictedName */,
						st._169 /* V00*next-2-steps */,
						st._009 /* 1*':' */,
						st._143 /* 1*ownedType=TypedMultiplicityRefCS */,
						st._181 /* V01*next-4-steps */,
						st._060 /* 1*'{' */,
						st._189 /* V02*next-1-steps */,
						st._147 /* 1*qualifiers */,
						st._063 /* 1*'}' */,
						st._196 /* V03*next-3-steps */,
						st._060 /* 1*'{' */,
						st._203 /* V04*ownedAnnotations+=AnnotationElementCS */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _180
				= /* { stereotype='postcondition' { name=UnrestrictedName { '(' ownedMessageSpecification=SpecificationCS ')' }[?] }[?] ':' ownedSpecification=SpecificationCS[?] ';' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(75, /* OCLinEcore::PostconditionConstraintCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._261 /* check-rule basecs::ConstraintCS.ownedMessageSpecification : SpecificationCS */,
						ms._264 /* check-rule basecs::ConstraintCS.ownedSpecification : SpecificationCS */,
						ms._163 /* assign V2 = |ownedSpecification| */,
						ms._076 /* assign V0 = |name| */,
						ms._052 /* assert (|stereotype.'postcondition'| - 1) == 0 */,
						ms._123 /* assign V1 = |ownedMessageSpecification| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-10-steps */,
						st._050 /* 1*'postcondition' */,
						st._169 /* V00*next-5-steps */,
						st._074 /* 1*name=UnrestrictedName */,
						st._181 /* V01*next-3-steps */,
						st._003 /* 1*'(' */,
						st._107 /* 1*ownedMessageSpecification=SpecificationCS */,
						st._004 /* 1*')' */,
						st._009 /* 1*':' */,
						st._194 /* V02*ownedSpecification=SpecificationCS */,
						st._011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._2 /* «! » + «value» + «! » */,
						null,
						ss._5 /* «! » + «value» */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._4 /* «! » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _181
				= /* { stereotype='precondition' { name=UnrestrictedName { '(' ownedMessageSpecification=SpecificationCS ')' }[?] }[?] ':' ownedSpecification=SpecificationCS[?] ';' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(76, /* OCLinEcore::PreconditionConstraintCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._262 /* check-rule basecs::ConstraintCS.ownedMessageSpecification : SpecificationCS */,
						ms._265 /* check-rule basecs::ConstraintCS.ownedSpecification : SpecificationCS */,
						ms._163 /* assign V2 = |ownedSpecification| */,
						ms._076 /* assign V0 = |name| */,
						ms._053 /* assert (|stereotype.'precondition'| - 1) == 0 */,
						ms._123 /* assign V1 = |ownedMessageSpecification| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-10-steps */,
						st._052 /* 1*'precondition' */,
						st._169 /* V00*next-5-steps */,
						st._074 /* 1*name=UnrestrictedName */,
						st._181 /* V01*next-3-steps */,
						st._003 /* 1*'(' */,
						st._107 /* 1*ownedMessageSpecification=SpecificationCS */,
						st._004 /* 1*')' */,
						st._009 /* 1*':' */,
						st._194 /* V02*ownedSpecification=SpecificationCS */,
						st._011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._2 /* «! » + «value» + «! » */,
						null,
						ss._5 /* «! » + «value» */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._4 /* «! » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _182
				= /* { qualifiers+='definition' qualifiers+='static'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(83, /* OCLinEcore::ReferenceCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._296 /* check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationElementCS */,
						ms._362 /* check-rule basecs::ReferenceCS.ownedImplicitOpposites : ImplicitOppositeCS */,
						ms._372 /* check-rule basecs::StructuralFeatureCS.ownedDefaultExpressions : SpecificationCS */,
						ms._460 /* check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS */,
						ms._146 /* assign V13 = |ownedImplicitOpposites| */,
						ms._207 /* assign V6 = |ownedAnnotations| */,
						ms._173 /* assign V3 = |default| */,
						ms._164 /* assign V2 = |ownedType| */,
						ms._132 /* assign V1 = |referredOpposite| */,
						ms._005 /* assert (|name| - 1) == 0 */,
						ms._096 /* assign V0 = |qualifiers.'static'| */,
						ms._047 /* assert (|qualifiers.'definition'| - 1) == 0 */,
						ms._213 /* assign V7 = (|referredKeys| > 0) */,
						ms._217 /* assign V8 = (|referredKeys| - 1) */,
						ms._183 /* assign V4 = (|qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0) */,
						ms._201 /* assign V5 = |qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| */,
						ms._226 /* assign V9 = |ownedDefaultExpressions| */,
						ms._135 /* assign V10 = (|ownedDefaultExpressions| > 0) */,
						ms._140 /* assign V11 = 0 */,
						ms._142 /* assign V12 = 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-41-steps */,
						st._028 /* 1*'definition' */,
						st._166 /* V00*'static' */,
						st._053 /* 1*'property' */,
						st._074 /* 1*name=UnrestrictedName */,
						st._181 /* V01*next-2-steps */,
						st._001 /* 1*'#' */,
						st._154 /* 1*referredOpposite=UnrestrictedName */,
						st._189 /* V02*next-2-steps */,
						st._009 /* 1*':' */,
						st._143 /* 1*ownedType=TypedMultiplicityRefCS */,
						st._196 /* V03*next-2-steps */,
						st._014 /* 1*'=' */,
						st._064 /* 1*default=SINGLE_QUOTED_STRING */,
						st._202 /* V04*next-4-steps */,
						st._060 /* 1*'{' */,
						st._207 /* V05*next-1-steps */,
						st._147 /* 1*qualifiers */,
						st._063 /* 1*'}' */,
						st._060 /* 1*'{' */,
						st._214 /* V06*ownedAnnotations+=AnnotationElementCS */,
						st._217 /* V07*next-6-steps */,
						st._042 /* 1*'key' */,
						st._153 /* 1*referredKeys+=UnrestrictedName */,
						st._220 /* V08*next-2-steps */,
						st._007 /* 1*',' */,
						st._152 /* 1*referredKeys+=UnrestrictedName */,
						st._011 /* 1*';' */,
						st._224 /* V09*next-4-steps */,
						st._039 /* 1*'initial' */,
						st._009 /* 1*':' */,
						st._231 /* V10*ownedDefaultExpressions+=SpecificationCS */,
						st._011 /* 1*';' */,
						st._233 /* V11*next-4-steps */,
						st._029 /* 1*'derivation' */,
						st._009 /* 1*':' */,
						st._238 /* V12*ownedDefaultExpressions+=SpecificationCS */,
						st._011 /* 1*';' */,
						st._240 /* V13*next-2-steps */,
						st._097 /* 1*ownedImplicitOpposites+=ImplicitOppositeCS */,
						st._011 /* 1*';' */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._2 /* «! » + «value» + «! » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._3 /* «! » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._4 /* «! » + «value» + «?\n» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._4 /* «! » + «value» + «?\n» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._4 /* «! » + «value» + «?\n» */,
						null,
						null,
						ss._4 /* «! » + «value» + «?\n» */,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _183
				= /* { 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(83, /* OCLinEcore::ReferenceCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._441 /* check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS */,
						ms._156 /* assign V2 = |default| */,
						ms._130 /* assign V1 = |ownedType| */,
						ms._097 /* assign V0 = |referredOpposite| */,
						ms._005 /* assert (|name| - 1) == 0 */,
						ms._170 /* assign V3 = (|qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0) */,
						ms._191 /* assign V4 = |qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-17-steps */,
						st._053 /* 1*'property' */,
						st._074 /* 1*name=UnrestrictedName */,
						st._169 /* V00*next-2-steps */,
						st._001 /* 1*'#' */,
						st._154 /* 1*referredOpposite=UnrestrictedName */,
						st._181 /* V01*next-2-steps */,
						st._009 /* 1*':' */,
						st._143 /* 1*ownedType=TypedMultiplicityRefCS */,
						st._189 /* V02*next-2-steps */,
						st._014 /* 1*'=' */,
						st._064 /* 1*default=SINGLE_QUOTED_STRING */,
						st._196 /* V03*next-4-steps */,
						st._060 /* 1*'{' */,
						st._202 /* V04*next-1-steps */,
						st._147 /* 1*qualifiers */,
						st._063 /* 1*'}' */,
						st._011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._2 /* «! » + «value» + «! » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._4 /* «! » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _184
				= /* { 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(83, /* OCLinEcore::ReferenceCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._291 /* check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationElementCS */,
						ms._361 /* check-rule basecs::ReferenceCS.ownedImplicitOpposites : ImplicitOppositeCS */,
						ms._371 /* check-rule basecs::StructuralFeatureCS.ownedDefaultExpressions : SpecificationCS */,
						ms._445 /* check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS */,
						ms._144 /* assign V12 = |ownedImplicitOpposites| */,
						ms._196 /* assign V5 = |ownedAnnotations| */,
						ms._156 /* assign V2 = |default| */,
						ms._130 /* assign V1 = |ownedType| */,
						ms._097 /* assign V0 = |referredOpposite| */,
						ms._005 /* assert (|name| - 1) == 0 */,
						ms._205 /* assign V6 = (|referredKeys| > 0) */,
						ms._212 /* assign V7 = (|referredKeys| - 1) */,
						ms._170 /* assign V3 = (|qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0) */,
						ms._191 /* assign V4 = |qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| */,
						ms._220 /* assign V8 = |ownedDefaultExpressions| */,
						ms._223 /* assign V9 = (|ownedDefaultExpressions| > 0) */,
						ms._136 /* assign V10 = 0 */,
						ms._140 /* assign V11 = 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-39-steps */,
						st._053 /* 1*'property' */,
						st._074 /* 1*name=UnrestrictedName */,
						st._169 /* V00*next-2-steps */,
						st._001 /* 1*'#' */,
						st._154 /* 1*referredOpposite=UnrestrictedName */,
						st._181 /* V01*next-2-steps */,
						st._009 /* 1*':' */,
						st._143 /* 1*ownedType=TypedMultiplicityRefCS */,
						st._189 /* V02*next-2-steps */,
						st._014 /* 1*'=' */,
						st._064 /* 1*default=SINGLE_QUOTED_STRING */,
						st._196 /* V03*next-4-steps */,
						st._060 /* 1*'{' */,
						st._202 /* V04*next-1-steps */,
						st._147 /* 1*qualifiers */,
						st._063 /* 1*'}' */,
						st._060 /* 1*'{' */,
						st._208 /* V05*ownedAnnotations+=AnnotationElementCS */,
						st._213 /* V06*next-6-steps */,
						st._042 /* 1*'key' */,
						st._153 /* 1*referredKeys+=UnrestrictedName */,
						st._217 /* V07*next-2-steps */,
						st._007 /* 1*',' */,
						st._152 /* 1*referredKeys+=UnrestrictedName */,
						st._011 /* 1*';' */,
						st._220 /* V08*next-4-steps */,
						st._039 /* 1*'initial' */,
						st._009 /* 1*':' */,
						st._226 /* V09*ownedDefaultExpressions+=SpecificationCS */,
						st._011 /* 1*';' */,
						st._229 /* V10*next-4-steps */,
						st._029 /* 1*'derivation' */,
						st._009 /* 1*':' */,
						st._235 /* V11*ownedDefaultExpressions+=SpecificationCS */,
						st._011 /* 1*';' */,
						st._236 /* V12*next-2-steps */,
						st._097 /* 1*ownedImplicitOpposites+=ImplicitOppositeCS */,
						st._011 /* 1*';' */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._2 /* «! » + «value» + «! » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._3 /* «! » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._4 /* «! » + «value» + «?\n» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._4 /* «! » + «value» + «?\n» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._4 /* «! » + «value» + «?\n» */,
						null,
						null,
						ss._4 /* «! » + «value» + «?\n» */,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _185
				= /* { qualifiers+='static' qualifiers+='definition'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(83, /* OCLinEcore::ReferenceCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._307 /* check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationElementCS */,
						ms._366 /* check-rule basecs::ReferenceCS.ownedImplicitOpposites : ImplicitOppositeCS */,
						ms._376 /* check-rule basecs::StructuralFeatureCS.ownedDefaultExpressions : SpecificationCS */,
						ms._459 /* check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS */,
						ms._146 /* assign V13 = |ownedImplicitOpposites| */,
						ms._207 /* assign V6 = |ownedAnnotations| */,
						ms._173 /* assign V3 = |default| */,
						ms._164 /* assign V2 = |ownedType| */,
						ms._132 /* assign V1 = |referredOpposite| */,
						ms._005 /* assert (|name| - 1) == 0 */,
						ms._095 /* assign V0 = |qualifiers.'definition'| */,
						ms._048 /* assert (|qualifiers.'static'| - 1) == 0 */,
						ms._213 /* assign V7 = (|referredKeys| > 0) */,
						ms._217 /* assign V8 = (|referredKeys| - 1) */,
						ms._183 /* assign V4 = (|qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0) */,
						ms._201 /* assign V5 = |qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| */,
						ms._226 /* assign V9 = |ownedDefaultExpressions| */,
						ms._135 /* assign V10 = (|ownedDefaultExpressions| > 0) */,
						ms._140 /* assign V11 = 0 */,
						ms._142 /* assign V12 = 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-41-steps */,
						st._056 /* 1*'static' */,
						st._164 /* V00*'definition' */,
						st._053 /* 1*'property' */,
						st._074 /* 1*name=UnrestrictedName */,
						st._181 /* V01*next-2-steps */,
						st._001 /* 1*'#' */,
						st._154 /* 1*referredOpposite=UnrestrictedName */,
						st._189 /* V02*next-2-steps */,
						st._009 /* 1*':' */,
						st._143 /* 1*ownedType=TypedMultiplicityRefCS */,
						st._196 /* V03*next-2-steps */,
						st._014 /* 1*'=' */,
						st._064 /* 1*default=SINGLE_QUOTED_STRING */,
						st._202 /* V04*next-4-steps */,
						st._060 /* 1*'{' */,
						st._207 /* V05*next-1-steps */,
						st._147 /* 1*qualifiers */,
						st._063 /* 1*'}' */,
						st._060 /* 1*'{' */,
						st._214 /* V06*ownedAnnotations+=AnnotationElementCS */,
						st._217 /* V07*next-6-steps */,
						st._042 /* 1*'key' */,
						st._153 /* 1*referredKeys+=UnrestrictedName */,
						st._220 /* V08*next-2-steps */,
						st._007 /* 1*',' */,
						st._152 /* 1*referredKeys+=UnrestrictedName */,
						st._011 /* 1*';' */,
						st._224 /* V09*next-4-steps */,
						st._039 /* 1*'initial' */,
						st._009 /* 1*':' */,
						st._231 /* V10*ownedDefaultExpressions+=SpecificationCS */,
						st._011 /* 1*';' */,
						st._233 /* V11*next-4-steps */,
						st._029 /* 1*'derivation' */,
						st._009 /* 1*':' */,
						st._238 /* V12*ownedDefaultExpressions+=SpecificationCS */,
						st._011 /* 1*';' */,
						st._240 /* V13*next-2-steps */,
						st._097 /* 1*ownedImplicitOpposites+=ImplicitOppositeCS */,
						st._011 /* 1*';' */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._2 /* «! » + «value» + «! » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._3 /* «! » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._4 /* «! » + «value» + «?\n» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._4 /* «! » + «value» + «?\n» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._4 /* «! » + «value» + «?\n» */,
						null,
						null,
						ss._4 /* «! » + «value» + «?\n» */,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _186
				= /* { qualifiers+='static' qualifiers+='definition'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(83, /* OCLinEcore::ReferenceCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._306 /* check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationElementCS */,
						ms._363 /* check-rule basecs::ReferenceCS.ownedImplicitOpposites : ImplicitOppositeCS */,
						ms._373 /* check-rule basecs::StructuralFeatureCS.ownedDefaultExpressions : SpecificationCS */,
						ms._448 /* check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS */,
						ms._146 /* assign V13 = |ownedImplicitOpposites| */,
						ms._207 /* assign V6 = |ownedAnnotations| */,
						ms._173 /* assign V3 = |default| */,
						ms._164 /* assign V2 = |ownedType| */,
						ms._132 /* assign V1 = |referredOpposite| */,
						ms._005 /* assert (|name| - 1) == 0 */,
						ms._095 /* assign V0 = |qualifiers.'definition'| */,
						ms._048 /* assert (|qualifiers.'static'| - 1) == 0 */,
						ms._213 /* assign V7 = (|referredKeys| > 0) */,
						ms._217 /* assign V8 = (|referredKeys| - 1) */,
						ms._183 /* assign V4 = (|qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0) */,
						ms._201 /* assign V5 = |qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| */,
						ms._226 /* assign V9 = |ownedDefaultExpressions| */,
						ms._135 /* assign V10 = (|ownedDefaultExpressions| > 0) */,
						ms._140 /* assign V11 = 0 */,
						ms._142 /* assign V12 = 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-41-steps */,
						st._056 /* 1*'static' */,
						st._164 /* V00*'definition' */,
						st._053 /* 1*'property' */,
						st._074 /* 1*name=UnrestrictedName */,
						st._181 /* V01*next-2-steps */,
						st._001 /* 1*'#' */,
						st._154 /* 1*referredOpposite=UnrestrictedName */,
						st._189 /* V02*next-2-steps */,
						st._009 /* 1*':' */,
						st._143 /* 1*ownedType=TypedMultiplicityRefCS */,
						st._196 /* V03*next-2-steps */,
						st._014 /* 1*'=' */,
						st._064 /* 1*default=SINGLE_QUOTED_STRING */,
						st._202 /* V04*next-4-steps */,
						st._060 /* 1*'{' */,
						st._207 /* V05*next-1-steps */,
						st._147 /* 1*qualifiers */,
						st._063 /* 1*'}' */,
						st._060 /* 1*'{' */,
						st._214 /* V06*ownedAnnotations+=AnnotationElementCS */,
						st._217 /* V07*next-6-steps */,
						st._042 /* 1*'key' */,
						st._153 /* 1*referredKeys+=UnrestrictedName */,
						st._220 /* V08*next-2-steps */,
						st._007 /* 1*',' */,
						st._152 /* 1*referredKeys+=UnrestrictedName */,
						st._011 /* 1*';' */,
						st._224 /* V09*next-4-steps */,
						st._039 /* 1*'initial' */,
						st._009 /* 1*':' */,
						st._231 /* V10*ownedDefaultExpressions+=SpecificationCS */,
						st._011 /* 1*';' */,
						st._233 /* V11*next-4-steps */,
						st._029 /* 1*'derivation' */,
						st._009 /* 1*':' */,
						st._238 /* V12*ownedDefaultExpressions+=SpecificationCS */,
						st._011 /* 1*';' */,
						st._240 /* V13*next-2-steps */,
						st._097 /* 1*ownedImplicitOpposites+=ImplicitOppositeCS */,
						st._011 /* 1*';' */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._2 /* «! » + «value» + «! » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._3 /* «! » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._4 /* «! » + «value» + «?\n» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._4 /* «! » + «value» + «?\n» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._4 /* «! » + «value» + «?\n» */,
						null,
						null,
						ss._4 /* «! » + «value» + «?\n» */,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _187
				= /* { 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(83, /* OCLinEcore::ReferenceCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._443 /* check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS */,
						ms._156 /* assign V2 = |default| */,
						ms._130 /* assign V1 = |ownedType| */,
						ms._097 /* assign V0 = |referredOpposite| */,
						ms._005 /* assert (|name| - 1) == 0 */,
						ms._170 /* assign V3 = (|qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0) */,
						ms._191 /* assign V4 = |qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-17-steps */,
						st._053 /* 1*'property' */,
						st._074 /* 1*name=UnrestrictedName */,
						st._169 /* V00*next-2-steps */,
						st._001 /* 1*'#' */,
						st._154 /* 1*referredOpposite=UnrestrictedName */,
						st._181 /* V01*next-2-steps */,
						st._009 /* 1*':' */,
						st._143 /* 1*ownedType=TypedMultiplicityRefCS */,
						st._189 /* V02*next-2-steps */,
						st._014 /* 1*'=' */,
						st._064 /* 1*default=SINGLE_QUOTED_STRING */,
						st._196 /* V03*next-4-steps */,
						st._060 /* 1*'{' */,
						st._202 /* V04*next-1-steps */,
						st._147 /* 1*qualifiers */,
						st._063 /* 1*'}' */,
						st._011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._2 /* «! » + «value» + «! » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._4 /* «! » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _188
				= /* { 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(83, /* OCLinEcore::ReferenceCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._282 /* check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationElementCS */,
						ms._364 /* check-rule basecs::ReferenceCS.ownedImplicitOpposites : ImplicitOppositeCS */,
						ms._374 /* check-rule basecs::StructuralFeatureCS.ownedDefaultExpressions : SpecificationCS */,
						ms._444 /* check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS */,
						ms._144 /* assign V12 = |ownedImplicitOpposites| */,
						ms._196 /* assign V5 = |ownedAnnotations| */,
						ms._156 /* assign V2 = |default| */,
						ms._130 /* assign V1 = |ownedType| */,
						ms._097 /* assign V0 = |referredOpposite| */,
						ms._005 /* assert (|name| - 1) == 0 */,
						ms._205 /* assign V6 = (|referredKeys| > 0) */,
						ms._212 /* assign V7 = (|referredKeys| - 1) */,
						ms._170 /* assign V3 = (|qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0) */,
						ms._191 /* assign V4 = |qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| */,
						ms._220 /* assign V8 = |ownedDefaultExpressions| */,
						ms._223 /* assign V9 = (|ownedDefaultExpressions| > 0) */,
						ms._136 /* assign V10 = 0 */,
						ms._140 /* assign V11 = 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-39-steps */,
						st._053 /* 1*'property' */,
						st._074 /* 1*name=UnrestrictedName */,
						st._169 /* V00*next-2-steps */,
						st._001 /* 1*'#' */,
						st._154 /* 1*referredOpposite=UnrestrictedName */,
						st._181 /* V01*next-2-steps */,
						st._009 /* 1*':' */,
						st._143 /* 1*ownedType=TypedMultiplicityRefCS */,
						st._189 /* V02*next-2-steps */,
						st._014 /* 1*'=' */,
						st._064 /* 1*default=SINGLE_QUOTED_STRING */,
						st._196 /* V03*next-4-steps */,
						st._060 /* 1*'{' */,
						st._202 /* V04*next-1-steps */,
						st._147 /* 1*qualifiers */,
						st._063 /* 1*'}' */,
						st._060 /* 1*'{' */,
						st._208 /* V05*ownedAnnotations+=AnnotationElementCS */,
						st._213 /* V06*next-6-steps */,
						st._042 /* 1*'key' */,
						st._153 /* 1*referredKeys+=UnrestrictedName */,
						st._217 /* V07*next-2-steps */,
						st._007 /* 1*',' */,
						st._152 /* 1*referredKeys+=UnrestrictedName */,
						st._011 /* 1*';' */,
						st._220 /* V08*next-4-steps */,
						st._039 /* 1*'initial' */,
						st._009 /* 1*':' */,
						st._226 /* V09*ownedDefaultExpressions+=SpecificationCS */,
						st._011 /* 1*';' */,
						st._229 /* V10*next-4-steps */,
						st._029 /* 1*'derivation' */,
						st._009 /* 1*':' */,
						st._235 /* V11*ownedDefaultExpressions+=SpecificationCS */,
						st._011 /* 1*';' */,
						st._236 /* V12*next-2-steps */,
						st._097 /* 1*ownedImplicitOpposites+=ImplicitOppositeCS */,
						st._011 /* 1*';' */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._2 /* «! » + «value» + «! » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._3 /* «! » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._4 /* «! » + «value» + «?\n» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._4 /* «! » + «value» + «?\n» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._4 /* «! » + «value» + «?\n» */,
						null,
						null,
						ss._4 /* «! » + «value» + «?\n» */,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _189
				= /* { qualifiers+='static' qualifiers+='definition'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(83, /* OCLinEcore::ReferenceCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._451 /* check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS */,
						ms._173 /* assign V3 = |default| */,
						ms._164 /* assign V2 = |ownedType| */,
						ms._132 /* assign V1 = |referredOpposite| */,
						ms._005 /* assert (|name| - 1) == 0 */,
						ms._095 /* assign V0 = |qualifiers.'definition'| */,
						ms._048 /* assert (|qualifiers.'static'| - 1) == 0 */,
						ms._183 /* assign V4 = (|qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0) */,
						ms._201 /* assign V5 = |qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-19-steps */,
						st._056 /* 1*'static' */,
						st._164 /* V00*'definition' */,
						st._053 /* 1*'property' */,
						st._074 /* 1*name=UnrestrictedName */,
						st._181 /* V01*next-2-steps */,
						st._001 /* 1*'#' */,
						st._154 /* 1*referredOpposite=UnrestrictedName */,
						st._189 /* V02*next-2-steps */,
						st._009 /* 1*':' */,
						st._143 /* 1*ownedType=TypedMultiplicityRefCS */,
						st._196 /* V03*next-2-steps */,
						st._014 /* 1*'=' */,
						st._064 /* 1*default=SINGLE_QUOTED_STRING */,
						st._202 /* V04*next-4-steps */,
						st._060 /* 1*'{' */,
						st._207 /* V05*next-1-steps */,
						st._147 /* 1*qualifiers */,
						st._063 /* 1*'}' */,
						st._011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._2 /* «! » + «value» + «! » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._4 /* «! » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _190
				= /* { qualifiers+='definition' qualifiers+='static'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(83, /* OCLinEcore::ReferenceCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._442 /* check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS */,
						ms._173 /* assign V3 = |default| */,
						ms._164 /* assign V2 = |ownedType| */,
						ms._132 /* assign V1 = |referredOpposite| */,
						ms._005 /* assert (|name| - 1) == 0 */,
						ms._096 /* assign V0 = |qualifiers.'static'| */,
						ms._047 /* assert (|qualifiers.'definition'| - 1) == 0 */,
						ms._183 /* assign V4 = (|qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0) */,
						ms._201 /* assign V5 = |qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-19-steps */,
						st._028 /* 1*'definition' */,
						st._166 /* V00*'static' */,
						st._053 /* 1*'property' */,
						st._074 /* 1*name=UnrestrictedName */,
						st._181 /* V01*next-2-steps */,
						st._001 /* 1*'#' */,
						st._154 /* 1*referredOpposite=UnrestrictedName */,
						st._189 /* V02*next-2-steps */,
						st._009 /* 1*':' */,
						st._143 /* 1*ownedType=TypedMultiplicityRefCS */,
						st._196 /* V03*next-2-steps */,
						st._014 /* 1*'=' */,
						st._064 /* 1*default=SINGLE_QUOTED_STRING */,
						st._202 /* V04*next-4-steps */,
						st._060 /* 1*'{' */,
						st._207 /* V05*next-1-steps */,
						st._147 /* 1*qualifiers */,
						st._063 /* 1*'}' */,
						st._011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._2 /* «! » + «value» + «! » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._4 /* «! » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _191
				= /* { qualifiers+='definition' qualifiers+='static'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] { 'key' referredKeys+=UnrestrictedName { ',' referredKeys+=UnrestrictedName }[*] ';' }[*] { 'initial' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { 'derivation' ':' ownedDefaultExpressions+=SpecificationCS[?] ';' }[*] { ownedImplicitOpposites+=ImplicitOppositeCS ';' }[*] '}' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(83, /* OCLinEcore::ReferenceCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._283 /* check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationElementCS */,
						ms._365 /* check-rule basecs::ReferenceCS.ownedImplicitOpposites : ImplicitOppositeCS */,
						ms._375 /* check-rule basecs::StructuralFeatureCS.ownedDefaultExpressions : SpecificationCS */,
						ms._455 /* check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS */,
						ms._146 /* assign V13 = |ownedImplicitOpposites| */,
						ms._207 /* assign V6 = |ownedAnnotations| */,
						ms._173 /* assign V3 = |default| */,
						ms._164 /* assign V2 = |ownedType| */,
						ms._132 /* assign V1 = |referredOpposite| */,
						ms._005 /* assert (|name| - 1) == 0 */,
						ms._096 /* assign V0 = |qualifiers.'static'| */,
						ms._047 /* assert (|qualifiers.'definition'| - 1) == 0 */,
						ms._213 /* assign V7 = (|referredKeys| > 0) */,
						ms._217 /* assign V8 = (|referredKeys| - 1) */,
						ms._183 /* assign V4 = (|qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0) */,
						ms._201 /* assign V5 = |qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| */,
						ms._226 /* assign V9 = |ownedDefaultExpressions| */,
						ms._135 /* assign V10 = (|ownedDefaultExpressions| > 0) */,
						ms._140 /* assign V11 = 0 */,
						ms._142 /* assign V12 = 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-41-steps */,
						st._028 /* 1*'definition' */,
						st._166 /* V00*'static' */,
						st._053 /* 1*'property' */,
						st._074 /* 1*name=UnrestrictedName */,
						st._181 /* V01*next-2-steps */,
						st._001 /* 1*'#' */,
						st._154 /* 1*referredOpposite=UnrestrictedName */,
						st._189 /* V02*next-2-steps */,
						st._009 /* 1*':' */,
						st._143 /* 1*ownedType=TypedMultiplicityRefCS */,
						st._196 /* V03*next-2-steps */,
						st._014 /* 1*'=' */,
						st._064 /* 1*default=SINGLE_QUOTED_STRING */,
						st._202 /* V04*next-4-steps */,
						st._060 /* 1*'{' */,
						st._207 /* V05*next-1-steps */,
						st._147 /* 1*qualifiers */,
						st._063 /* 1*'}' */,
						st._060 /* 1*'{' */,
						st._214 /* V06*ownedAnnotations+=AnnotationElementCS */,
						st._217 /* V07*next-6-steps */,
						st._042 /* 1*'key' */,
						st._153 /* 1*referredKeys+=UnrestrictedName */,
						st._220 /* V08*next-2-steps */,
						st._007 /* 1*',' */,
						st._152 /* 1*referredKeys+=UnrestrictedName */,
						st._011 /* 1*';' */,
						st._224 /* V09*next-4-steps */,
						st._039 /* 1*'initial' */,
						st._009 /* 1*':' */,
						st._231 /* V10*ownedDefaultExpressions+=SpecificationCS */,
						st._011 /* 1*';' */,
						st._233 /* V11*next-4-steps */,
						st._029 /* 1*'derivation' */,
						st._009 /* 1*':' */,
						st._238 /* V12*ownedDefaultExpressions+=SpecificationCS */,
						st._011 /* 1*';' */,
						st._240 /* V13*next-2-steps */,
						st._097 /* 1*ownedImplicitOpposites+=ImplicitOppositeCS */,
						st._011 /* 1*';' */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._2 /* «! » + «value» + «! » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._3 /* «! » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._4 /* «! » + «value» + «?\n» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._4 /* «! » + «value» + «?\n» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._4 /* «! » + «value» + «?\n» */,
						null,
						null,
						ss._4 /* «! » + «value» + «?\n» */,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _192
				= /* { qualifiers+='definition' qualifiers+='static'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(83, /* OCLinEcore::ReferenceCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._450 /* check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS */,
						ms._173 /* assign V3 = |default| */,
						ms._164 /* assign V2 = |ownedType| */,
						ms._132 /* assign V1 = |referredOpposite| */,
						ms._005 /* assert (|name| - 1) == 0 */,
						ms._096 /* assign V0 = |qualifiers.'static'| */,
						ms._047 /* assert (|qualifiers.'definition'| - 1) == 0 */,
						ms._183 /* assign V4 = (|qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0) */,
						ms._201 /* assign V5 = |qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-19-steps */,
						st._028 /* 1*'definition' */,
						st._166 /* V00*'static' */,
						st._053 /* 1*'property' */,
						st._074 /* 1*name=UnrestrictedName */,
						st._181 /* V01*next-2-steps */,
						st._001 /* 1*'#' */,
						st._154 /* 1*referredOpposite=UnrestrictedName */,
						st._189 /* V02*next-2-steps */,
						st._009 /* 1*':' */,
						st._143 /* 1*ownedType=TypedMultiplicityRefCS */,
						st._196 /* V03*next-2-steps */,
						st._014 /* 1*'=' */,
						st._064 /* 1*default=SINGLE_QUOTED_STRING */,
						st._202 /* V04*next-4-steps */,
						st._060 /* 1*'{' */,
						st._207 /* V05*next-1-steps */,
						st._147 /* 1*qualifiers */,
						st._063 /* 1*'}' */,
						st._011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._2 /* «! » + «value» + «! » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._4 /* «! » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _193
				= /* { qualifiers+='static' qualifiers+='definition'[?] 'property' name=UnrestrictedName { '#' referredOpposite=UnrestrictedName }[?] { ':' ownedType=TypedMultiplicityRefCS }[?] { '=' default=SINGLE_QUOTED_STRING }[?] { '{' { qualifiers+={'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'} }[+] '}' }[?] ';' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(83, /* OCLinEcore::ReferenceCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._458 /* check-rule basecs::TypedElementCS.ownedType : TypedMultiplicityRefCS */,
						ms._173 /* assign V3 = |default| */,
						ms._164 /* assign V2 = |ownedType| */,
						ms._132 /* assign V1 = |referredOpposite| */,
						ms._005 /* assert (|name| - 1) == 0 */,
						ms._095 /* assign V0 = |qualifiers.'definition'| */,
						ms._048 /* assert (|qualifiers.'static'| - 1) == 0 */,
						ms._183 /* assign V4 = (|qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| > 0) */,
						ms._201 /* assign V5 = |qualifiers.'!composes|!derived|!ordered|!readonly|!resolve|!transient|!unique|!unsettable|!volatile|composes|derived|ordered|readonly|resolve|transient|unique|unsettable|volatile'| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-19-steps */,
						st._056 /* 1*'static' */,
						st._164 /* V00*'definition' */,
						st._053 /* 1*'property' */,
						st._074 /* 1*name=UnrestrictedName */,
						st._181 /* V01*next-2-steps */,
						st._001 /* 1*'#' */,
						st._154 /* 1*referredOpposite=UnrestrictedName */,
						st._189 /* V02*next-2-steps */,
						st._009 /* 1*':' */,
						st._143 /* 1*ownedType=TypedMultiplicityRefCS */,
						st._196 /* V03*next-2-steps */,
						st._014 /* 1*'=' */,
						st._064 /* 1*default=SINGLE_QUOTED_STRING */,
						st._202 /* V04*next-4-steps */,
						st._060 /* 1*'{' */,
						st._207 /* V05*next-1-steps */,
						st._147 /* 1*qualifiers */,
						st._063 /* 1*'}' */,
						st._011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._2 /* «! » + «value» + «! » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._4 /* «! » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _194
				= /* exprString=UNQUOTED_STRING */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(92, /* OCLinEcore::SpecificationCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._000 /* assert (|exprString| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._065 /* 1*exprString=UNQUOTED_STRING */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						ss._8 /* «? » + «value» + «? » */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _195
				= /* ownedExpression=ExpCS */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(92, /* OCLinEcore::SpecificationCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._538 /* check-rule essentialoclcs::ExpSpecificationCS.ownedExpression : ExpCS */,
						ms._017 /* assert (|ownedExpression| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._092 /* 1*ownedExpression=ExpCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null
					}
				);
			private final /*@NonNull*/ RTSerializationRule _196
				= /* { isAbstract='abstract'[?] 'class' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { 'extends' ownedSuperTypes+=TypedRefCS { ',' ownedSuperTypes+=TypedRefCS }[*] }[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isInterface='interface'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedOperations+=OperationCS[*] ownedProperties+=StructuralFeatureCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(97, /* OCLinEcore::StructuredClassCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._277 /* check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationElementCS */,
						ms._382 /* check-rule basecs::StructuredClassCS.ownedSuperTypes : TypedRefCS */,
						ms._377 /* check-rule basecs::StructuredClassCS.ownedOperations : OperationCS */,
						ms._416 /* check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS */,
						ms._249 /* check-rule basecs::ClassCS.ownedConstraints : InvariantConstraintCS */,
						ms._379 /* check-rule basecs::StructuredClassCS.ownedProperties : StructuralFeatureCS */,
						ms._137 /* assign V10 = |ownedConstraints| */,
						ms._228 /* assign V9 = |ownedProperties| */,
						ms._221 /* assign V8 = |ownedOperations| */,
						ms._215 /* assign V7 = |ownedAnnotations| */,
						ms._184 /* assign V4 = |instanceClassName| */,
						ms._128 /* assign V1 = |ownedSignature| */,
						ms._005 /* assert (|name| - 1) == 0 */,
						ms._071 /* assign V0 = |isAbstract.'abstract'| */,
						ms._193 /* assign V5 = (|isInterface.'interface'| > 0) */,
						ms._206 /* assign V6 = |isInterface.'interface'| */,
						ms._153 /* assign V2 = (|ownedSuperTypes| > 0) */,
						ms._169 /* assign V3 = (|ownedSuperTypes| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-23-steps */,
						st._162 /* V00*'abstract' */,
						st._026 /* 1*'class' */,
						st._074 /* 1*name=UnrestrictedName */,
						st._188 /* V01*ownedSignature=TemplateSignatureCS */,
						st._189 /* V02*next-5-steps */,
						st._035 /* 1*'extends' */,
						st._131 /* 1*ownedSuperTypes+=TypedRefCS */,
						st._196 /* V03*next-2-steps */,
						st._007 /* 1*',' */,
						st._131 /* 1*ownedSuperTypes+=TypedRefCS */,
						st._202 /* V04*next-2-steps */,
						st._009 /* 1*':' */,
						st._066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						st._207 /* V05*next-3-steps */,
						st._060 /* 1*'{' */,
						st._212 /* V06*'interface' */,
						st._063 /* 1*'}' */,
						st._060 /* 1*'{' */,
						st._218 /* V07*ownedAnnotations+=AnnotationElementCS */,
						st._223 /* V08*ownedOperations+=OperationCS */,
						st._228 /* V09*ownedProperties+=StructuralFeatureCS */,
						st._230 /* V10*ownedConstraints+=InvariantConstraintCS */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						null,
						null,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _197
				= /* { isAbstract='abstract'[?] 'class' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { 'extends' ownedSuperTypes+=TypedRefCS { ',' ownedSuperTypes+=TypedRefCS }[*] }[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isInterface='interface'[?] '}' }[?] ';' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(97, /* OCLinEcore::StructuredClassCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._383 /* check-rule basecs::StructuredClassCS.ownedSuperTypes : TypedRefCS */,
						ms._408 /* check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS */,
						ms._184 /* assign V4 = |instanceClassName| */,
						ms._128 /* assign V1 = |ownedSignature| */,
						ms._005 /* assert (|name| - 1) == 0 */,
						ms._071 /* assign V0 = |isAbstract.'abstract'| */,
						ms._193 /* assign V5 = (|isInterface.'interface'| > 0) */,
						ms._206 /* assign V6 = |isInterface.'interface'| */,
						ms._153 /* assign V2 = (|ownedSuperTypes| > 0) */,
						ms._169 /* assign V3 = (|ownedSuperTypes| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-18-steps */,
						st._162 /* V00*'abstract' */,
						st._026 /* 1*'class' */,
						st._074 /* 1*name=UnrestrictedName */,
						st._188 /* V01*ownedSignature=TemplateSignatureCS */,
						st._189 /* V02*next-5-steps */,
						st._035 /* 1*'extends' */,
						st._131 /* 1*ownedSuperTypes+=TypedRefCS */,
						st._196 /* V03*next-2-steps */,
						st._007 /* 1*',' */,
						st._131 /* 1*ownedSuperTypes+=TypedRefCS */,
						st._202 /* V04*next-2-steps */,
						st._009 /* 1*':' */,
						st._066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						st._207 /* V05*next-3-steps */,
						st._060 /* 1*'{' */,
						st._212 /* V06*'interface' */,
						st._063 /* 1*'}' */,
						st._011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._4 /* «! » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _198
				= /* { isAbstract='abstract'[?] 'class' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { 'extends' ownedSuperTypes+=TypedRefCS { ',' ownedSuperTypes+=TypedRefCS }[*] }[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isInterface='interface'[?] '}' }[?] ';' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(97, /* OCLinEcore::StructuredClassCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._381 /* check-rule basecs::StructuredClassCS.ownedSuperTypes : TypedRefCS */,
						ms._406 /* check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS */,
						ms._184 /* assign V4 = |instanceClassName| */,
						ms._128 /* assign V1 = |ownedSignature| */,
						ms._005 /* assert (|name| - 1) == 0 */,
						ms._071 /* assign V0 = |isAbstract.'abstract'| */,
						ms._193 /* assign V5 = (|isInterface.'interface'| > 0) */,
						ms._206 /* assign V6 = |isInterface.'interface'| */,
						ms._153 /* assign V2 = (|ownedSuperTypes| > 0) */,
						ms._169 /* assign V3 = (|ownedSuperTypes| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-18-steps */,
						st._162 /* V00*'abstract' */,
						st._026 /* 1*'class' */,
						st._074 /* 1*name=UnrestrictedName */,
						st._188 /* V01*ownedSignature=TemplateSignatureCS */,
						st._189 /* V02*next-5-steps */,
						st._035 /* 1*'extends' */,
						st._131 /* 1*ownedSuperTypes+=TypedRefCS */,
						st._196 /* V03*next-2-steps */,
						st._007 /* 1*',' */,
						st._131 /* 1*ownedSuperTypes+=TypedRefCS */,
						st._202 /* V04*next-2-steps */,
						st._009 /* 1*':' */,
						st._066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						st._207 /* V05*next-3-steps */,
						st._060 /* 1*'{' */,
						st._212 /* V06*'interface' */,
						st._063 /* 1*'}' */,
						st._011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._4 /* «! » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _199
				= /* { isAbstract='abstract'[?] 'class' name=UnrestrictedName ownedSignature=TemplateSignatureCS[?] { 'extends' ownedSuperTypes+=TypedRefCS { ',' ownedSuperTypes+=TypedRefCS }[*] }[?] { ':' instanceClassName=SINGLE_QUOTED_STRING }[?] { '{' isInterface='interface'[?] '}' }[?] '{' ownedAnnotations+=AnnotationElementCS[*] ownedOperations+=OperationCS[*] ownedProperties+=StructuralFeatureCS[*] ownedConstraints+=InvariantConstraintCS[*] '}' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(97, /* OCLinEcore::StructuredClassCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._302 /* check-rule basecs::ModelElementCS.ownedAnnotations : AnnotationElementCS */,
						ms._384 /* check-rule basecs::StructuredClassCS.ownedSuperTypes : TypedRefCS */,
						ms._378 /* check-rule basecs::StructuredClassCS.ownedOperations : OperationCS */,
						ms._414 /* check-rule basecs::TemplateableElementCS.ownedSignature : TemplateSignatureCS */,
						ms._257 /* check-rule basecs::ClassCS.ownedConstraints : InvariantConstraintCS */,
						ms._380 /* check-rule basecs::StructuredClassCS.ownedProperties : StructuralFeatureCS */,
						ms._137 /* assign V10 = |ownedConstraints| */,
						ms._228 /* assign V9 = |ownedProperties| */,
						ms._221 /* assign V8 = |ownedOperations| */,
						ms._215 /* assign V7 = |ownedAnnotations| */,
						ms._184 /* assign V4 = |instanceClassName| */,
						ms._128 /* assign V1 = |ownedSignature| */,
						ms._005 /* assert (|name| - 1) == 0 */,
						ms._071 /* assign V0 = |isAbstract.'abstract'| */,
						ms._193 /* assign V5 = (|isInterface.'interface'| > 0) */,
						ms._206 /* assign V6 = |isInterface.'interface'| */,
						ms._153 /* assign V2 = (|ownedSuperTypes| > 0) */,
						ms._169 /* assign V3 = (|ownedSuperTypes| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-23-steps */,
						st._162 /* V00*'abstract' */,
						st._026 /* 1*'class' */,
						st._074 /* 1*name=UnrestrictedName */,
						st._188 /* V01*ownedSignature=TemplateSignatureCS */,
						st._189 /* V02*next-5-steps */,
						st._035 /* 1*'extends' */,
						st._131 /* 1*ownedSuperTypes+=TypedRefCS */,
						st._196 /* V03*next-2-steps */,
						st._007 /* 1*',' */,
						st._131 /* 1*ownedSuperTypes+=TypedRefCS */,
						st._202 /* V04*next-2-steps */,
						st._009 /* 1*':' */,
						st._066 /* 1*instanceClassName=SINGLE_QUOTED_STRING */,
						st._207 /* V05*next-3-steps */,
						st._060 /* 1*'{' */,
						st._212 /* V06*'interface' */,
						st._063 /* 1*'}' */,
						st._060 /* 1*'{' */,
						st._218 /* V07*ownedAnnotations+=AnnotationElementCS */,
						st._223 /* V08*ownedOperations+=OperationCS */,
						st._228 /* V09*ownedProperties+=StructuralFeatureCS */,
						st._230 /* V10*ownedConstraints+=InvariantConstraintCS */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						ss._8 /* «? » + «value» + «? » */,
						ss._6 /* «-» + «? » + «value» + «?\n» */,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						null,
						null,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _200
				= /* { 'sysml' ownedDetails+=DetailCS ';' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(98, /* OCLinEcore::SysMLCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._243 /* check-rule basecs::AnnotationElementCS.ownedDetails : DetailCS */,
						ms._012 /* assert (|ownedDetails| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-3-steps */,
						st._057 /* 1*'sysml' */,
						st._086 /* 1*ownedDetails+=DetailCS */,
						st._011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._4 /* «! » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _201
				= /* { 'sysml' '{' { ownedDetails+=DetailCS ';' }[*] '}' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(98, /* OCLinEcore::SysMLCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._242 /* check-rule basecs::AnnotationElementCS.ownedDetails : DetailCS */,
						ms._080 /* assign V0 = |ownedDetails| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-6-steps */,
						st._057 /* 1*'sysml' */,
						st._060 /* 1*'{' */,
						st._169 /* V00*next-2-steps */,
						st._086 /* 1*ownedDetails+=DetailCS */,
						st._011 /* 1*';' */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						ss._4 /* «! » + «value» + «?\n» */,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _202
				= /* { 'sysml' '{' { ownedDetails+=DetailCS ';' }[*] '}' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(98, /* OCLinEcore::SysMLCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._244 /* check-rule basecs::AnnotationElementCS.ownedDetails : DetailCS */,
						ms._080 /* assign V0 = |ownedDetails| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-6-steps */,
						st._057 /* 1*'sysml' */,
						st._060 /* 1*'{' */,
						st._169 /* V00*next-2-steps */,
						st._086 /* 1*ownedDetails+=DetailCS */,
						st._011 /* 1*';' */,
						st._063 /* 1*'}' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						ss._7 /* «? » + «value» + «+» + «?\n» */,
						null,
						null,
						ss._4 /* «! » + «value» + «?\n» */,
						ss._6 /* «-» + «? » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _203
				= /* { 'sysml' ownedDetails+=DetailCS ';' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(98, /* OCLinEcore::SysMLCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._241 /* check-rule basecs::AnnotationElementCS.ownedDetails : DetailCS */,
						ms._012 /* assert (|ownedDetails| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-3-steps */,
						st._057 /* 1*'sysml' */,
						st._086 /* 1*ownedDetails+=DetailCS */,
						st._011 /* 1*';' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._4 /* «! » + «value» + «?\n» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _204
				= /* { '(' ownedParameters+=TypeParameterCS { ',' ownedParameters+=TypeParameterCS }[*] ')' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(101, /* OCLinEcore::TemplateSignatureCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._388 /* check-rule basecs::TemplateSignatureCS.ownedParameters : TypeParameterCS */,
						ms._059 /* assign V0 = (|ownedParameters| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-6-steps */,
						st._003 /* 1*'(' */,
						st._110 /* 1*ownedParameters+=TypeParameterCS */,
						st._169 /* V00*next-2-steps */,
						st._007 /* 1*',' */,
						st._110 /* 1*ownedParameters+=TypeParameterCS */,
						st._004 /* 1*')' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._2 /* «! » + «value» + «! » */,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						ss._5 /* «! » + «value» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _205
				= /* { '<' ownedParameters+=TypeParameterCS { ',' ownedParameters+=TypeParameterCS }[*] '>' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(101, /* OCLinEcore::TemplateSignatureCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._389 /* check-rule basecs::TemplateSignatureCS.ownedParameters : TypeParameterCS */,
						ms._059 /* assign V0 = (|ownedParameters| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-6-steps */,
						st._012 /* 1*'<' */,
						st._110 /* 1*ownedParameters+=TypeParameterCS */,
						st._169 /* V00*next-2-steps */,
						st._007 /* 1*',' */,
						st._110 /* 1*ownedParameters+=TypeParameterCS */,
						st._015 /* 1*'>' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _206
				= /* { { 'module' }[?] ownedImports+=ImportCS[*] ownedPackages+=PackageCS[*] } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(102, /* OCLinEcore::TopLevelCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._367 /* check-rule basecs::RootCS.ownedImports : ImportCS */,
						ms._357 /* check-rule basecs::PackageOwnerCS.ownedPackages : PackageCS */,
						ms._162 /* assign V2 = |ownedPackages| */,
						ms._121 /* assign V1 = |ownedImports| */,
						ms._070 /* assign V0 = 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-4-steps */,
						st._169 /* V00*next-1-steps */,
						st._045 /* 1*'module' */,
						st._184 /* V01*ownedImports+=ImportCS */,
						st._193 /* V02*ownedPackages+=PackageCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						null
					}
				);
			private final /*@NonNull*/ RTSerializationRule _207
				= /* { name=CollectionTypeIdentifier { '(' ownedType=TypeExpWithoutMultiplicityCS ownedCollectionMultiplicity=MultiplicityCS[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(116, /* OCLinEcore::TypedMultiplicityRefCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._530 /* check-rule essentialoclcs::CollectionTypeCS.ownedCollectionMultiplicity : MultiplicityCS */,
						ms._535 /* check-rule essentialoclcs::CollectionTypeCS.ownedType : TypeExpWithoutMultiplicityCS */,
						ms._473 /* check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS */,
						ms._161 /* assign V2 = |ownedMultiplicity| */,
						ms._090 /* assign V0 = |ownedType| */,
						ms._004 /* assert (|name| - 1) == 0 */,
						ms._120 /* assign V1 = |ownedCollectionMultiplicity| */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-7-steps */,
						st._070 /* 1*name=CollectionTypeIdentifier */,
						st._169 /* V00*next-4-steps */,
						st._003 /* 1*'(' */,
						st._141 /* 1*ownedType=TypeExpWithoutMultiplicityCS */,
						st._183 /* V01*ownedCollectionMultiplicity=MultiplicityCS */,
						st._004 /* 1*')' */,
						st._192 /* V02*ownedMultiplicity=MultiplicityCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._2 /* «! » + «value» + «! » */,
						null,
						null,
						ss._5 /* «! » + «value» */,
						null
					}
				);
			private final /*@NonNull*/ RTSerializationRule _208
				= /* { name='Tuple' { '(' { ownedParts+=TuplePartCS { ',' ownedParts+=TuplePartCS }[*] }[?] ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(116, /* OCLinEcore::TypedMultiplicityRefCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._434 /* check-rule basecs::TupleTypeCS.ownedParts : TuplePartCS */,
						ms._472 /* check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS */,
						ms._177 /* assign V3 = |ownedMultiplicity| */,
						ms._003 /* assert (|name.'Tuple'| - 1) == 0 */,
						ms._063 /* assign V0 = (|ownedParts| > 0) */,
						ms._111 /* assign V1 = (|ownedParts| > 0) */,
						ms._152 /* assign V2 = (|ownedParts| - 1) */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-10-steps */,
						st._020 /* 1*'Tuple' */,
						st._169 /* V00*next-7-steps */,
						st._003 /* 1*'(' */,
						st._181 /* V01*next-4-steps */,
						st._116 /* 1*ownedParts+=TuplePartCS */,
						st._189 /* V02*next-2-steps */,
						st._007 /* 1*',' */,
						st._116 /* 1*ownedParts+=TuplePartCS */,
						st._004 /* 1*')' */,
						st._198 /* V03*ownedMultiplicity=MultiplicityCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._2 /* «! » + «value» + «! » */,
						null,
						null,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						ss._5 /* «! » + «value» */,
						null
					}
				);
			private final /*@NonNull*/ RTSerializationRule _209
				= /* { name=PrimitiveTypeIdentifier ownedMultiplicity=MultiplicityCS[?] } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(116, /* OCLinEcore::TypedMultiplicityRefCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._474 /* check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS */,
						ms._085 /* assign V0 = |ownedMultiplicity| */,
						ms._006 /* assert (|name| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-2-steps */,
						st._072 /* 1*name=PrimitiveTypeIdentifier */,
						st._171 /* V00*ownedMultiplicity=MultiplicityCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						null
					}
				);
			private final /*@NonNull*/ RTSerializationRule _210
				= /* { name='Map' { '(' ownedKeyType=TypeExpCS ',' ownedValueType=TypeExpCS ')' }[?] ownedMultiplicity=MultiplicityCS[?] } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(116, /* OCLinEcore::TypedMultiplicityRefCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._574 /* check-rule essentialoclcs::MapTypeCS.ownedKeyType : TypeExpCS */,
						ms._579 /* check-rule essentialoclcs::MapTypeCS.ownedValueType : TypeExpCS */,
						ms._479 /* check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS */,
						ms._125 /* assign V1 = |ownedMultiplicity| */,
						ms._093 /* assign V0 = |ownedValueType| */,
						ms._023 /* assert (|ownedKeyType| - V0) == 0 */,
						ms._002 /* assert (|name.'Map'| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-8-steps */,
						st._019 /* 1*'Map' */,
						st._169 /* V00*next-5-steps */,
						st._003 /* 1*'(' */,
						st._104 /* 1*ownedKeyType=TypeExpCS */,
						st._007 /* 1*',' */,
						st._145 /* 1*ownedValueType=TypeExpCS */,
						st._004 /* 1*')' */,
						st._185 /* V01*ownedMultiplicity=MultiplicityCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._2 /* «! » + «value» + «! » */,
						null,
						ss._3 /* «! » + «value» + «? » */,
						null,
						ss._5 /* «! » + «value» */,
						null
					}
				);
			private final /*@NonNull*/ RTSerializationRule _211
				= /* { ownedPathName=PathNameCS '(' ownedBinding=TemplateBindingCS ')' ownedMultiplicity=MultiplicityCS[?] } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(116, /* OCLinEcore::TypedMultiplicityRefCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._488 /* check-rule basecs::TypedTypeRefCS.ownedBinding : TemplateBindingCS */,
						ms._496 /* check-rule basecs::TypedTypeRefCS.ownedPathName : PathNameCS */,
						ms._481 /* check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS */,
						ms._085 /* assign V0 = |ownedMultiplicity| */,
						ms._008 /* assert (|ownedBinding| - 1) == 0 */,
						ms._031 /* assert (|ownedPathName| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-5-steps */,
						st._121 /* 1*ownedPathName=PathNameCS */,
						st._003 /* 1*'(' */,
						st._081 /* 1*ownedBinding=TemplateBindingCS */,
						st._004 /* 1*')' */,
						st._171 /* V00*ownedMultiplicity=MultiplicityCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						null,
						ss._2 /* «! » + «value» + «! » */,
						null,
						ss._5 /* «! » + «value» */,
						null
					}
				);
			private final /*@NonNull*/ RTSerializationRule _212
				= /* { ownedPathName=PathNameCS '<' ownedBinding=TemplateBindingCS '>' ownedMultiplicity=MultiplicityCS[?] } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(116, /* OCLinEcore::TypedMultiplicityRefCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._489 /* check-rule basecs::TypedTypeRefCS.ownedBinding : TemplateBindingCS */,
						ms._497 /* check-rule basecs::TypedTypeRefCS.ownedPathName : PathNameCS */,
						ms._485 /* check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS */,
						ms._085 /* assign V0 = |ownedMultiplicity| */,
						ms._008 /* assert (|ownedBinding| - 1) == 0 */,
						ms._031 /* assert (|ownedPathName| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-5-steps */,
						st._121 /* 1*ownedPathName=PathNameCS */,
						st._012 /* 1*'<' */,
						st._081 /* 1*ownedBinding=TemplateBindingCS */,
						st._015 /* 1*'>' */,
						st._171 /* V00*ownedMultiplicity=MultiplicityCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null
					}
				);
			private final /*@NonNull*/ RTSerializationRule _213
				= /* { ownedPathName=PathNameCS ownedMultiplicity=MultiplicityCS[?] } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(116, /* OCLinEcore::TypedMultiplicityRefCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._495 /* check-rule basecs::TypedTypeRefCS.ownedPathName : PathNameCS */,
						ms._480 /* check-rule basecs::TypedRefCS.ownedMultiplicity : MultiplicityCS */,
						ms._085 /* assign V0 = |ownedMultiplicity| */,
						ms._031 /* assert (|ownedPathName| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-2-steps */,
						st._121 /* 1*ownedPathName=PathNameCS */,
						st._171 /* V00*ownedMultiplicity=MultiplicityCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						null,
						null
					}
				);
			private final /*@NonNull*/ RTSerializationRule _214
				= /* ownedPathName=PathNameCS */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(118, /* OCLinEcore::TypedTypeRefCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._498 /* check-rule basecs::TypedTypeRefCS.ownedPathName : PathNameCS */,
						ms._031 /* assert (|ownedPathName| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._121 /* 1*ownedPathName=PathNameCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null
					}
				);
			private final /*@NonNull*/ RTSerializationRule _215
				= /* { ownedPathName=PathNameCS '<' ownedBinding=TemplateBindingCS '>' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(118, /* OCLinEcore::TypedTypeRefCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._491 /* check-rule basecs::TypedTypeRefCS.ownedBinding : TemplateBindingCS */,
						ms._500 /* check-rule basecs::TypedTypeRefCS.ownedPathName : PathNameCS */,
						ms._008 /* assert (|ownedBinding| - 1) == 0 */,
						ms._031 /* assert (|ownedPathName| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-4-steps */,
						st._121 /* 1*ownedPathName=PathNameCS */,
						st._012 /* 1*'<' */,
						st._081 /* 1*ownedBinding=TemplateBindingCS */,
						st._015 /* 1*'>' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _216
				= /* { ownedPathName=PathNameCS '<' ownedBinding=TemplateBindingCS '>' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(118, /* OCLinEcore::TypedTypeRefCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._487 /* check-rule basecs::TypedTypeRefCS.ownedBinding : TemplateBindingCS */,
						ms._492 /* check-rule basecs::TypedTypeRefCS.ownedPathName : PathNameCS */,
						ms._008 /* assert (|ownedBinding| - 1) == 0 */,
						ms._031 /* assert (|ownedPathName| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-4-steps */,
						st._121 /* 1*ownedPathName=PathNameCS */,
						st._012 /* 1*'<' */,
						st._081 /* 1*ownedBinding=TemplateBindingCS */,
						st._015 /* 1*'>' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						null,
						ss._8 /* «? » + «value» + «? » */,
						null,
						ss._8 /* «? » + «value» + «? » */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _217
				= /* { ownedPathName=PathNameCS '(' ownedBinding=TemplateBindingCS ')' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(118, /* OCLinEcore::TypedTypeRefCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._490 /* check-rule basecs::TypedTypeRefCS.ownedBinding : TemplateBindingCS */,
						ms._499 /* check-rule basecs::TypedTypeRefCS.ownedPathName : PathNameCS */,
						ms._008 /* assert (|ownedBinding| - 1) == 0 */,
						ms._031 /* assert (|ownedPathName| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-4-steps */,
						st._121 /* 1*ownedPathName=PathNameCS */,
						st._003 /* 1*'(' */,
						st._081 /* 1*ownedBinding=TemplateBindingCS */,
						st._004 /* 1*')' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						null,
						ss._2 /* «! » + «value» + «! » */,
						null,
						ss._5 /* «! » + «value» */
					}
				);
			private final /*@NonNull*/ RTSerializationRule _218
				= /* ownedPathName=PathNameCS */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(118, /* OCLinEcore::TypedTypeRefCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._493 /* check-rule basecs::TypedTypeRefCS.ownedPathName : PathNameCS */,
						ms._031 /* assert (|ownedPathName| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._121 /* 1*ownedPathName=PathNameCS */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null
					}
				);
			private final /*@NonNull*/ RTSerializationRule _219
				= /* { ownedPathName=PathNameCS '(' ownedBinding=TemplateBindingCS ')' } */
				new org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule(118, /* OCLinEcore::TypedTypeRefCS */
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.user.CardinalitySolutionStep /*@NonNull*/ []{
						ms._486 /* check-rule basecs::TypedTypeRefCS.ownedBinding : TemplateBindingCS */,
						ms._494 /* check-rule basecs::TypedTypeRefCS.ownedPathName : PathNameCS */,
						ms._008 /* assert (|ownedBinding| - 1) == 0 */,
						ms._031 /* assert (|ownedPathName| - 1) == 0 */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep /*@NonNull*/ []{
						st._076 /* 1*next-4-steps */,
						st._121 /* 1*ownedPathName=PathNameCS */,
						st._003 /* 1*'(' */,
						st._081 /* 1*ownedBinding=TemplateBindingCS */,
						st._004 /* 1*')' */
					},
					new /*@NonNull*/ org.eclipse.ocl.xtext.base.cs2text.idioms.Segment /*@NonNull*/ [] []{
						null,
						null,
						ss._2 /* «! » + «value» + «! » */,
						null,
						ss._5 /* «! » + «value» */
					}
				);
		}

		private _EnumValues ev;
		private _MatchTerms mt;
		private _MatchSteps ms;
		private _SerializationTerms st;
		private _SerializationSegments ss;
		private _SerializationRules sr;
		private _RuleValues rv;
		private _EClassData ec;

		@Inject
		public void init() {
		//	Grammar grammar = grammarProvider.getGrammar(this);
			ev = new _EnumValues();
			mt = new _MatchTerms();
			ms = new _MatchSteps();
			st = new _SerializationTerms();
			ss = new _SerializationSegments();
			sr = new _SerializationRules();
			rv = new _RuleValues();
			ec = new _EClassData();
			st.init();
		}
	}
