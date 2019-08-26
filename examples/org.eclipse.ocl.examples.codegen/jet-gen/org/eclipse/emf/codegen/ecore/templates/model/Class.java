package org.eclipse.emf.codegen.ecore.templates.model;

import java.util.*;
import org.eclipse.emf.codegen.ecore.genmodel.*;
import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.eclipse.ocl.examples.codegen.genmodel.OCLGenModelUtil;
import org.eclipse.ocl.examples.codegen.genmodel.ModelSpec;

public class Class
{
  protected static String nl;
  public static synchronized Class create(String lineSeparator)
  {
    nl = lineSeparator;
    Class result = new Class();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = "/**";
  protected final String TEXT_3 = NL + " * ";
  protected final String TEXT_4 = NL + " */";
  protected final String TEXT_5 = NL + "package ";
  protected final String TEXT_6 = ";";
  protected final String TEXT_7 = NL;
  protected final String TEXT_8 = NL + "/**" + NL + " * <!-- begin-user-doc -->" + NL + " * A representation of the model object '<em><b>";
  protected final String TEXT_9 = "</b></em>'." + NL + " * <!-- end-user-doc -->";
  protected final String TEXT_10 = NL + " *" + NL + " * <!-- begin-model-doc -->" + NL + " * ";
  protected final String TEXT_11 = NL + " * <!-- end-model-doc -->";
  protected final String TEXT_12 = NL + " *";
  protected final String TEXT_13 = NL + " * <p>" + NL + " * The following features are supported:" + NL + " * </p>" + NL + " * <ul>";
  protected final String TEXT_14 = NL + " *   <li>{@link ";
  protected final String TEXT_15 = "#";
  protected final String TEXT_16 = " <em>";
  protected final String TEXT_17 = "</em>}</li>";
  protected final String TEXT_18 = NL + " * </ul>";
  protected final String TEXT_19 = NL + " * @see ";
  protected final String TEXT_20 = "#get";
  protected final String TEXT_21 = "()";
  protected final String TEXT_22 = NL + " * @model ";
  protected final String TEXT_23 = NL + " *        ";
  protected final String TEXT_24 = NL + " * @model";
  protected final String TEXT_25 = NL + " * @extends ";
  protected final String TEXT_26 = NL + " * @generated" + NL + " */";
  protected final String TEXT_27 = NL + "/**" + NL + " * <!-- begin-user-doc -->" + NL + " * An implementation of the model object '<em><b>";
  protected final String TEXT_28 = NL + " * <p>" + NL + " * The following features are implemented:" + NL + " * </p>" + NL + " * <ul>";
  protected final String TEXT_29 = NL + "@Deprecated";
  protected final String TEXT_30 = NL + "@SuppressWarnings(\"deprecation\")";
  protected final String TEXT_31 = NL + "public";
  protected final String TEXT_32 = " abstract";
  protected final String TEXT_33 = " class ";
  protected final String TEXT_34 = NL + "public interface ";
  protected final String TEXT_35 = NL + "{";
  protected final String TEXT_36 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\t";
  protected final String TEXT_37 = " copyright = ";
  protected final String TEXT_38 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static final ";
  protected final String TEXT_39 = " mofDriverNumber = \"";
  protected final String TEXT_40 = "\";";
  protected final String TEXT_41 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate static final long serialVersionUID = 1L;" + NL;
  protected final String TEXT_42 = NL + "\t/**" + NL + "\t * An array of objects representing the values of non-primitive features." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_43 = NL + "\t@";
  protected final String TEXT_44 = NL + "\tprotected Object[] ";
  protected final String TEXT_45 = ";" + NL;
  protected final String TEXT_46 = NL + "\t/**" + NL + "\t * A bit field representing the indices of non-primitive feature values." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_47 = NL + "\tprotected int ";
  protected final String TEXT_48 = NL + "\t/**" + NL + "\t * A set of bit flags representing the values of boolean attributes and whether unsettable features have been set." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_49 = " = 0;" + NL;
  protected final String TEXT_50 = NL + "\t/**" + NL + "\t * The number of structural features of the '<em>";
  protected final String TEXT_51 = "</em>' class." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_52 = NL + "\t * ";
  protected final String TEXT_53 = NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_54 = NL + "\t@Deprecated";
  protected final String TEXT_55 = NL + "\t";
  protected final String TEXT_56 = "int ";
  protected final String TEXT_57 = " = ";
  protected final String TEXT_58 = NL + "\t/**" + NL + "\t * The number of operations of the '<em>";
  protected final String TEXT_59 = NL + "\t/**" + NL + "\t * The empty value for the '{@link #";
  protected final String TEXT_60 = "() <em>";
  protected final String TEXT_61 = "</em>}' array accessor." + NL + "\t * This is specialized for the more specific element type known in this context." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_62 = NL + "\t@SuppressWarnings(\"rawtypes\")";
  protected final String TEXT_63 = NL + "\tprotected static final ";
  protected final String TEXT_64 = "[] ";
  protected final String TEXT_65 = "_EEMPTY_ARRAY = new ";
  protected final String TEXT_66 = " [0]";
  protected final String TEXT_67 = NL + "\t/**" + NL + "\t * The cached setting delegate for the '{@link #";
  protected final String TEXT_68 = "</em>}' ";
  protected final String TEXT_69 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_70 = NL + "\tprotected ";
  protected final String TEXT_71 = ".Internal.SettingDelegate ";
  protected final String TEXT_72 = "__ESETTING_DELEGATE = ((";
  protected final String TEXT_73 = ".Internal)";
  protected final String TEXT_74 = ").getSettingDelegate();" + NL;
  protected final String TEXT_75 = NL + "\t/**" + NL + "\t * The cached value of the '{@link #";
  protected final String TEXT_76 = " ";
  protected final String TEXT_77 = "</em>}' array accessor." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_78 = NL + "\t/**" + NL + "\t * The default value of the '{@link #";
  protected final String TEXT_79 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_80 = "; // TODO The default value literal \"";
  protected final String TEXT_81 = "\" is not valid.";
  protected final String TEXT_82 = NL + "\t/**" + NL + "\t * An additional set of bit flags representing the values of boolean attributes and whether unsettable features have been set." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_83 = NL + "\t/**" + NL + "\t * The offset of the flags representing the value of the '{@link #";
  protected final String TEXT_84 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_85 = NL + "\tprotected static final int ";
  protected final String TEXT_86 = "_EFLAG_OFFSET = ";
  protected final String TEXT_87 = ";" + NL + "" + NL + "\t/**" + NL + "\t * The flags representing the default value of the '{@link #";
  protected final String TEXT_88 = "_EFLAG_DEFAULT = ";
  protected final String TEXT_89 = ".ordinal()";
  protected final String TEXT_90 = ".VALUES.indexOf(";
  protected final String TEXT_91 = ")";
  protected final String TEXT_92 = " << ";
  protected final String TEXT_93 = "_EFLAG_OFFSET;" + NL + "" + NL + "\t/**" + NL + "\t * The array of enumeration values for '{@link ";
  protected final String TEXT_94 = "}'" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_95 = NL + "\tprivate static final ";
  protected final String TEXT_96 = "_EFLAG_VALUES = ";
  protected final String TEXT_97 = ".values()";
  protected final String TEXT_98 = "(";
  protected final String TEXT_99 = "[])";
  protected final String TEXT_100 = ".VALUES.toArray(new ";
  protected final String TEXT_101 = "[";
  protected final String TEXT_102 = ".VALUES.size()])";
  protected final String TEXT_103 = NL + "\t/**" + NL + "\t * The flag";
  protected final String TEXT_104 = " representing the value of the '{@link #";
  protected final String TEXT_105 = "_EFLAG = ";
  protected final String TEXT_106 = "_EFLAG_OFFSET";
  protected final String TEXT_107 = NL + "\t/**" + NL + "\t * The flag representing whether the ";
  protected final String TEXT_108 = " has been set." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_109 = "_ESETFLAG = 1 << ";
  protected final String TEXT_110 = NL + "\t/**" + NL + "\t * This is true if the ";
  protected final String TEXT_111 = NL + "\tprotected boolean ";
  protected final String TEXT_112 = "ESet;" + NL;
  protected final String TEXT_113 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate static final int ";
  protected final String TEXT_114 = ".getFeatureID(";
  protected final String TEXT_115 = ") - ";
  protected final String TEXT_116 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_117 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_118 = NL + "\tprivate static final int ";
  protected final String TEXT_119 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate static final int EOPERATION_OFFSET_CORRECTION = ";
  protected final String TEXT_120 = ".getOperationID(";
  protected final String TEXT_121 = "public";
  protected final String TEXT_122 = "protected";
  protected final String TEXT_123 = "()" + NL + "\t{" + NL + "\t\tsuper();";
  protected final String TEXT_124 = NL + "\t\t";
  protected final String TEXT_125 = " |= ";
  protected final String TEXT_126 = "_EFLAG";
  protected final String TEXT_127 = "_DEFAULT";
  protected final String TEXT_128 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_129 = NL + "\t@Override";
  protected final String TEXT_130 = " eStaticClass()" + NL + "\t{" + NL + "\t\treturn ";
  protected final String TEXT_131 = ";" + NL + "\t}" + NL;
  protected final String TEXT_132 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected static final int ESTATIC_FEATURE_COUNT = ";
  protected final String TEXT_133 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_134 = NL + "\tprotected int eStaticFeatureCount()" + NL + "\t{" + NL + "\t\treturn ";
  protected final String TEXT_135 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * This is specialized for the more specific element type known in this context." + NL + "\t * @see #";
  protected final String TEXT_136 = NL + "\tpublic ";
  protected final String TEXT_137 = "()" + NL + "\t{";
  protected final String TEXT_138 = " list = (";
  protected final String TEXT_139 = "();" + NL + "\t\tif (list.isEmpty()) return ";
  protected final String TEXT_140 = "_EEMPTY_ARRAY;";
  protected final String TEXT_141 = NL + "\t\tif (";
  protected final String TEXT_142 = " == null || ";
  protected final String TEXT_143 = ".isEmpty()) return ";
  protected final String TEXT_144 = "_EEMPTY_ARRAY;" + NL + "\t\t";
  protected final String TEXT_145 = NL + "\t\tlist.shrink();" + NL + "\t\treturn (";
  protected final String TEXT_146 = "[])list.data();" + NL + "\t}" + NL;
  protected final String TEXT_147 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * This is specialized for the more specific element type known in this context.";
  protected final String TEXT_148 = "_";
  protected final String TEXT_149 = " = (";
  protected final String TEXT_150 = ")eVirtualGet(";
  protected final String TEXT_151 = ");";
  protected final String TEXT_152 = " == null)" + NL + "\t\t{";
  protected final String TEXT_153 = NL + "\t\t\teVirtualSet(";
  protected final String TEXT_154 = ", ";
  protected final String TEXT_155 = " = new ";
  protected final String TEXT_156 = NL + "\t\t\t";
  protected final String TEXT_157 = NL + "\t\t}" + NL + "\t\treturn ";
  protected final String TEXT_158 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * This is specialized for the more specific type known in this context.";
  protected final String TEXT_159 = " basicSet";
  protected final String TEXT_160 = " new";
  protected final String TEXT_161 = " msgs)" + NL + "\t{" + NL + "\t\treturn super.basicSet";
  protected final String TEXT_162 = "(new";
  protected final String TEXT_163 = ", msgs);" + NL + "\t}" + NL;
  protected final String TEXT_164 = NL + "\tpublic void set";
  protected final String TEXT_165 = ")" + NL + "\t{" + NL + "\t\tsuper.set";
  protected final String TEXT_166 = ");" + NL + "\t}" + NL;
  protected final String TEXT_167 = "();" + NL;
  protected final String TEXT_168 = " get";
  protected final String TEXT_169 = "(int index);" + NL;
  protected final String TEXT_170 = "(int index)" + NL + "\t{" + NL + "\t\treturn ";
  protected final String TEXT_171 = "().get(index);" + NL + "\t}" + NL;
  protected final String TEXT_172 = NL + "\tint get";
  protected final String TEXT_173 = "Length();" + NL;
  protected final String TEXT_174 = NL + "\tpublic int get";
  protected final String TEXT_175 = "Length()" + NL + "\t{";
  protected final String TEXT_176 = NL + "\t\treturn ";
  protected final String TEXT_177 = "().size();";
  protected final String TEXT_178 = " == null ? 0 : ";
  protected final String TEXT_179 = ".size();";
  protected final String TEXT_180 = NL + "\t}" + NL;
  protected final String TEXT_181 = NL + "\tvoid set";
  protected final String TEXT_182 = "[] new";
  protected final String TEXT_183 = ");" + NL;
  protected final String TEXT_184 = ")" + NL + "\t{" + NL + "\t\t((";
  protected final String TEXT_185 = "()).setData(new";
  protected final String TEXT_186 = ".length, new";
  protected final String TEXT_187 = "(int index, ";
  protected final String TEXT_188 = " element);" + NL;
  protected final String TEXT_189 = " element)" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_190 = "().set(index, element);" + NL + "\t}" + NL;
  protected final String TEXT_191 = NL + "\t/**" + NL + "\t * Returns the value of the '<em><b>";
  protected final String TEXT_192 = "</b></em>' ";
  protected final String TEXT_193 = ".";
  protected final String TEXT_194 = NL + "\t * The key is of type ";
  protected final String TEXT_195 = "list of {@link ";
  protected final String TEXT_196 = "}";
  protected final String TEXT_197 = "{@link ";
  protected final String TEXT_198 = "," + NL + "\t * and the value is of type ";
  protected final String TEXT_199 = ",";
  protected final String TEXT_200 = NL + "\t * The list contents are of type {@link ";
  protected final String TEXT_201 = NL + "\t * The default value is <code>";
  protected final String TEXT_202 = "</code>.";
  protected final String TEXT_203 = NL + "\t * The literals are from the enumeration {@link ";
  protected final String TEXT_204 = "}.";
  protected final String TEXT_205 = NL + "\t * It is bidirectional and its opposite is '{@link ";
  protected final String TEXT_206 = "</em>}'.";
  protected final String TEXT_207 = NL + "\t * <!-- begin-user-doc -->";
  protected final String TEXT_208 = NL + "\t * <p>" + NL + "\t * If the meaning of the '<em>";
  protected final String TEXT_209 = "</em>' ";
  protected final String TEXT_210 = " isn't clear," + NL + "\t * there really should be more of a description here..." + NL + "\t * </p>";
  protected final String TEXT_211 = NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_212 = NL + "\t * <!-- begin-model-doc -->" + NL + "\t * ";
  protected final String TEXT_213 = NL + "\t * <!-- end-model-doc -->";
  protected final String TEXT_214 = NL + "\t * @return the value of the '<em>";
  protected final String TEXT_215 = NL + "\t * @see ";
  protected final String TEXT_216 = NL + "\t * @see #isSet";
  protected final String TEXT_217 = NL + "\t * @see #unset";
  protected final String TEXT_218 = NL + "\t * @see #set";
  protected final String TEXT_219 = NL + "\t * @model ";
  protected final String TEXT_220 = NL + "\t *        ";
  protected final String TEXT_221 = NL + "\t * @model";
  protected final String TEXT_222 = ")eDynamicGet(";
  protected final String TEXT_223 = " - ESTATIC_FEATURE_COUNT";
  protected final String TEXT_224 = ", true, ";
  protected final String TEXT_225 = ").";
  protected final String TEXT_226 = ")eGet(";
  protected final String TEXT_227 = ", true)";
  protected final String TEXT_228 = "__ESETTING_DELEGATE.dynamicGet(this, null, 0, true, false)";
  protected final String TEXT_229 = NL + "\t\tif (eContainerFeatureID() != (";
  protected final String TEXT_230 = ")) return null;" + NL + "\t\treturn (";
  protected final String TEXT_231 = "eContainer";
  protected final String TEXT_232 = "eInternalContainer";
  protected final String TEXT_233 = "();";
  protected final String TEXT_234 = " != null && ";
  protected final String TEXT_235 = ".eIsProxy())" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_236 = " old";
  protected final String TEXT_237 = ";" + NL + "\t\t\t";
  protected final String TEXT_238 = "eResolveProxy(old";
  protected final String TEXT_239 = ");" + NL + "\t\t\tif (";
  protected final String TEXT_240 = " != old";
  protected final String TEXT_241 = ")" + NL + "\t\t\t{";
  protected final String TEXT_242 = NL + "\t\t\t\t";
  protected final String TEXT_243 = " msgs = old";
  protected final String TEXT_244 = ".eInverseRemove(this, EOPPOSITE_FEATURE_BASE - (";
  protected final String TEXT_245 = "), null, null);";
  protected final String TEXT_246 = " msgs =  old";
  protected final String TEXT_247 = ".eInverseRemove(this, ";
  protected final String TEXT_248 = ".class, null);";
  protected final String TEXT_249 = NL + "\t\t\t\tif (new";
  protected final String TEXT_250 = ".eInternalContainer() == null)" + NL + "\t\t\t\t{";
  protected final String TEXT_251 = NL + "\t\t\t\t\tmsgs = new";
  protected final String TEXT_252 = ".eInverseAdd(this, EOPPOSITE_FEATURE_BASE - (";
  protected final String TEXT_253 = "), null, msgs);";
  protected final String TEXT_254 = NL + "\t\t\t\t\tmsgs =  new";
  protected final String TEXT_255 = ".eInverseAdd(this, ";
  protected final String TEXT_256 = ".class, msgs);";
  protected final String TEXT_257 = NL + "\t\t\t\t}" + NL + "\t\t\t\tif (msgs != null) msgs.dispatch();";
  protected final String TEXT_258 = NL + "\t\t\t\teVirtualSet(";
  protected final String TEXT_259 = NL + "\t\t\t\tif (eNotificationRequired())" + NL + "\t\t\t\t\teNotify(new ";
  protected final String TEXT_260 = "(this, ";
  protected final String TEXT_261 = ".RESOLVE, ";
  protected final String TEXT_262 = ", old";
  protected final String TEXT_263 = "));";
  protected final String TEXT_264 = NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_265 = NL + "\t\treturn (";
  protected final String TEXT_266 = " & ";
  protected final String TEXT_267 = "_EFLAG) != 0;";
  protected final String TEXT_268 = "_EFLAG_VALUES[(";
  protected final String TEXT_269 = "_EFLAG) >>> ";
  protected final String TEXT_270 = "_EFLAG_OFFSET];";
  protected final String TEXT_271 = " = basicGet";
  protected final String TEXT_272 = "();" + NL + "\t\treturn ";
  protected final String TEXT_273 = ".eIsProxy() ? ";
  protected final String TEXT_274 = "eResolveProxy((";
  protected final String TEXT_275 = ") : ";
  protected final String TEXT_276 = NL + "\t\treturn new ";
  protected final String TEXT_277 = "((";
  protected final String TEXT_278 = ".Internal)((";
  protected final String TEXT_279 = ".Internal.Wrapper)get";
  protected final String TEXT_280 = "()).featureMap().";
  protected final String TEXT_281 = "list(";
  protected final String TEXT_282 = ")get";
  protected final String TEXT_283 = "().";
  protected final String TEXT_284 = NL + "\t\treturn ((";
  protected final String TEXT_285 = "()).featureMap().list(";
  protected final String TEXT_286 = NL + "\t\treturn get";
  protected final String TEXT_287 = "().list(";
  protected final String TEXT_288 = "()).featureMap().get(";
  protected final String TEXT_289 = "get";
  protected final String TEXT_290 = "().get(";
  protected final String TEXT_291 = NL + "\t\t// TODO: implement this method to return the '";
  protected final String TEXT_292 = "' ";
  protected final String TEXT_293 = NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT";
  protected final String TEXT_294 = NL + "\t\t// The list is expected to implement org.eclipse.emf.ecore.util.InternalEList and org.eclipse.emf.ecore.EStructuralFeature.Setting" + NL + "\t\t// so it's likely that an appropriate subclass of org.eclipse.emf.ecore.util.";
  protected final String TEXT_295 = "EcoreEMap";
  protected final String TEXT_296 = "BasicFeatureMap";
  protected final String TEXT_297 = "EcoreEList";
  protected final String TEXT_298 = " should be used.";
  protected final String TEXT_299 = NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_300 = " basicGet";
  protected final String TEXT_301 = ", false, ";
  protected final String TEXT_302 = "__ESETTING_DELEGATE.dynamicGet(this, null, 0, false, false)";
  protected final String TEXT_303 = ")eInternalContainer();";
  protected final String TEXT_304 = ")((";
  protected final String TEXT_305 = ", false);";
  protected final String TEXT_306 = NL + "\t\t// -> do not perform proxy resolution" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_307 = " msgs)" + NL + "\t{";
  protected final String TEXT_308 = NL + "\t\tmsgs = eBasicSetContainer((";
  protected final String TEXT_309 = ")new";
  protected final String TEXT_310 = ", msgs);";
  protected final String TEXT_311 = NL + "\t\treturn msgs;";
  protected final String TEXT_312 = NL + "\t\tmsgs = eDynamicInverseAdd((";
  protected final String TEXT_313 = NL + "\t\tObject old";
  protected final String TEXT_314 = " = eVirtualSet(";
  protected final String TEXT_315 = ", new";
  protected final String TEXT_316 = ";" + NL + "\t\t";
  protected final String TEXT_317 = " = new";
  protected final String TEXT_318 = NL + "\t\tboolean isSetChange = old";
  protected final String TEXT_319 = " == EVIRTUAL_NO_VALUE;";
  protected final String TEXT_320 = NL + "\t\tboolean old";
  protected final String TEXT_321 = "ESet = (";
  protected final String TEXT_322 = "_ESETFLAG) != 0;";
  protected final String TEXT_323 = "_ESETFLAG;";
  protected final String TEXT_324 = "ESet = ";
  protected final String TEXT_325 = "ESet;";
  protected final String TEXT_326 = "ESet = true;";
  protected final String TEXT_327 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t{";
  protected final String TEXT_328 = " notification = new ";
  protected final String TEXT_329 = ".SET, ";
  protected final String TEXT_330 = "isSetChange ? null : old";
  protected final String TEXT_331 = "old";
  protected final String TEXT_332 = "isSetChange";
  protected final String TEXT_333 = "!old";
  protected final String TEXT_334 = "ESet";
  protected final String TEXT_335 = " == EVIRTUAL_NO_VALUE ? null : old";
  protected final String TEXT_336 = NL + "\t\t\tif (msgs == null) msgs = notification; else msgs.add(notification);" + NL + "\t\t}";
  protected final String TEXT_337 = "()).featureMap()).basicAdd(";
  protected final String TEXT_338 = ".Internal)get";
  protected final String TEXT_339 = "()).basicAdd(";
  protected final String TEXT_340 = NL + "\t\t// TODO: implement this method to set the contained '";
  protected final String TEXT_341 = NL + "\t\t// -> this method is automatically invoked to keep the containment relationship in synch" + NL + "\t\t// -> do not modify other features" + NL + "\t\t// -> return msgs, after adding any generated Notification to it (if it is null, a NotificationChain object must be created first)" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_342 = NL + "\t/**" + NL + "\t * Sets the value of the '{@link ";
  protected final String TEXT_343 = NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @param value the new value of the '<em>";
  protected final String TEXT_344 = NL + "\t * @see #";
  protected final String TEXT_345 = " value);" + NL;
  protected final String TEXT_346 = ")" + NL + "\t{";
  protected final String TEXT_347 = NL + "\t\teDynamicSet(";
  protected final String TEXT_348 = "new ";
  protected final String TEXT_349 = "new";
  protected final String TEXT_350 = NL + "\t\teSet(";
  protected final String TEXT_351 = "__ESETTING_DELEGATE.dynamicSet(this, null, 0, ";
  protected final String TEXT_352 = NL + "\t\tif (new";
  protected final String TEXT_353 = " != eInternalContainer() || (eContainerFeatureID() != (";
  protected final String TEXT_354 = ") && new";
  protected final String TEXT_355 = " != null))" + NL + "\t\t{" + NL + "\t\t\tif (";
  protected final String TEXT_356 = ".isAncestor(this, ";
  protected final String TEXT_357 = "))" + NL + "\t\t\t\tthrow new ";
  protected final String TEXT_358 = "(\"Recursive containment not allowed for \" + toString());";
  protected final String TEXT_359 = " msgs = null;" + NL + "\t\t\tif (eInternalContainer() != null)" + NL + "\t\t\t\tmsgs = eBasicRemoveFromContainer(msgs);" + NL + "\t\t\tif (new";
  protected final String TEXT_360 = " != null)" + NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_361 = ").eInverseAdd(this, ";
  protected final String TEXT_362 = ".class, msgs);" + NL + "\t\t\tmsgs = basicSet";
  protected final String TEXT_363 = ", msgs);" + NL + "\t\t\tif (msgs != null) msgs.dispatch();" + NL + "\t\t}";
  protected final String TEXT_364 = NL + "\t\telse if (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_365 = " != ";
  protected final String TEXT_366 = ")" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_367 = " msgs = null;" + NL + "\t\t\tif (";
  protected final String TEXT_368 = " != null)";
  protected final String TEXT_369 = NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_370 = ").eInverseRemove(this, EOPPOSITE_FEATURE_BASE - (";
  protected final String TEXT_371 = "), null, msgs);" + NL + "\t\t\tif (new";
  protected final String TEXT_372 = ").eInverseAdd(this, EOPPOSITE_FEATURE_BASE - (";
  protected final String TEXT_373 = ").eInverseRemove(this, ";
  protected final String TEXT_374 = ".class, msgs);" + NL + "\t\t\tif (new";
  protected final String TEXT_375 = NL + "\t\t\tmsgs = basicSet";
  protected final String TEXT_376 = NL + "\t\telse" + NL + "\t\t{";
  protected final String TEXT_377 = NL + "\t\t\tboolean old";
  protected final String TEXT_378 = "ESet = eVirtualIsSet(";
  protected final String TEXT_379 = NL + "\t\t\tif (eNotificationRequired())" + NL + "\t\t\t\teNotify(new ";
  protected final String TEXT_380 = ", !old";
  protected final String TEXT_381 = "ESet));";
  protected final String TEXT_382 = NL + "\t\t}";
  protected final String TEXT_383 = ") ";
  protected final String TEXT_384 = "_EFLAG; else ";
  protected final String TEXT_385 = " &= ~";
  protected final String TEXT_386 = "_EFLAG;";
  protected final String TEXT_387 = " == null) new";
  protected final String TEXT_388 = "_EDEFAULT;" + NL + "\t\t";
  protected final String TEXT_389 = " & ~";
  protected final String TEXT_390 = "_EFLAG | ";
  protected final String TEXT_391 = ".VALUES.indexOf(new";
  protected final String TEXT_392 = "_EFLAG_OFFSET;";
  protected final String TEXT_393 = " == null ? ";
  protected final String TEXT_394 = " : new";
  protected final String TEXT_395 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_396 = "isSetChange ? ";
  protected final String TEXT_397 = " : old";
  protected final String TEXT_398 = " == EVIRTUAL_NO_VALUE ? ";
  protected final String TEXT_399 = NL + "\t\t((";
  protected final String TEXT_400 = "()).featureMap()).set(";
  protected final String TEXT_401 = "()).set(";
  protected final String TEXT_402 = NL + "\t\t// TODO: implement this method to set the '";
  protected final String TEXT_403 = NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_404 = " basicUnset";
  protected final String TEXT_405 = NL + "\t\treturn eDynamicInverseRemove((";
  protected final String TEXT_406 = "basicGet";
  protected final String TEXT_407 = "(), ";
  protected final String TEXT_408 = "Object old";
  protected final String TEXT_409 = "eVirtualUnset(";
  protected final String TEXT_410 = " = null;";
  protected final String TEXT_411 = " != EVIRTUAL_NO_VALUE;";
  protected final String TEXT_412 = "ESet = false;";
  protected final String TEXT_413 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_414 = ".UNSET, ";
  protected final String TEXT_415 = "isSetChange ? old";
  protected final String TEXT_416 = " : null";
  protected final String TEXT_417 = ", null, ";
  protected final String TEXT_418 = ");" + NL + "\t\t\tif (msgs == null) msgs = notification; else msgs.add(notification);" + NL + "\t\t}" + NL + "\t\treturn msgs;";
  protected final String TEXT_419 = NL + "\t\t// TODO: implement this method to unset the contained '";
  protected final String TEXT_420 = NL + "\t/**" + NL + "\t * Unsets the value of the '{@link ";
  protected final String TEXT_421 = NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_422 = NL + "\tvoid unset";
  protected final String TEXT_423 = NL + "\tpublic void unset";
  protected final String TEXT_424 = NL + "\t\teDynamicUnset(";
  protected final String TEXT_425 = NL + "\t\teUnset(";
  protected final String TEXT_426 = "__ESETTING_DELEGATE.dynamicUnset(this, null, 0);";
  protected final String TEXT_427 = " != null) ((";
  protected final String TEXT_428 = ".Unsettable";
  protected final String TEXT_429 = ").unset();";
  protected final String TEXT_430 = " != null)" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_431 = " msgs = null;";
  protected final String TEXT_432 = NL + "\t\t\tmsgs = ((";
  protected final String TEXT_433 = NL + "\t\t\tmsgs = basicUnset";
  protected final String TEXT_434 = "(msgs);" + NL + "\t\t\tif (msgs != null) msgs.dispatch();" + NL + "\t\t}" + NL + "\t\telse" + NL + "\t\t{";
  protected final String TEXT_435 = ", null, null, old";
  protected final String TEXT_436 = " = eVirtualUnset(";
  protected final String TEXT_437 = "_EFLAG_DEFAULT;";
  protected final String TEXT_438 = " : ";
  protected final String TEXT_439 = "()).featureMap()).clear(";
  protected final String TEXT_440 = "()).clear(";
  protected final String TEXT_441 = NL + "\t\t// TODO: implement this method to unset the '";
  protected final String TEXT_442 = NL + "\t/**" + NL + "\t * Returns whether the value of the '{@link ";
  protected final String TEXT_443 = " is set.";
  protected final String TEXT_444 = NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @return whether the value of the '<em>";
  protected final String TEXT_445 = NL + "\tboolean isSet";
  protected final String TEXT_446 = NL + "\tpublic boolean isSet";
  protected final String TEXT_447 = NL + "\t\treturn eDynamicIsSet(";
  protected final String TEXT_448 = NL + "\t\treturn eIsSet(";
  protected final String TEXT_449 = "__ESETTING_DELEGATE.dynamicIsSet(this, null, 0);";
  protected final String TEXT_450 = " != null && ((";
  protected final String TEXT_451 = ").isSet();";
  protected final String TEXT_452 = NL + "\t\treturn eVirtualIsSet(";
  protected final String TEXT_453 = NL + "\t\treturn !((";
  protected final String TEXT_454 = "()).featureMap()).isEmpty(";
  protected final String TEXT_455 = "()).isEmpty(";
  protected final String TEXT_456 = NL + "\t\t// TODO: implement this method to return whether the '";
  protected final String TEXT_457 = " is set" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_458 = NL + "\t/**" + NL + "\t * The cached validation expression for the '{@link #";
  protected final String TEXT_459 = ") <em>";
  protected final String TEXT_460 = "</em>}' invariant operation." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_461 = "__EEXPRESSION = \"";
  protected final String TEXT_462 = NL + "\t/**" + NL + "\t * The cached invocation delegate for the '{@link #";
  protected final String TEXT_463 = "</em>}' operation." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_464 = ".Internal.InvocationDelegate ";
  protected final String TEXT_465 = "__EINVOCATION_DELEGATE = ((";
  protected final String TEXT_466 = ").getInvocationDelegate();" + NL;
  protected final String TEXT_467 = NL + "\t/**";
  protected final String TEXT_468 = NL + "\t * <!-- begin-model-doc -->";
  protected final String TEXT_469 = NL + "\t * @param ";
  protected final String TEXT_470 = NL + "\t *   ";
  protected final String TEXT_471 = NL + "\t{";
  protected final String TEXT_472 = NL + "\t\treturn" + NL + "\t\t\t";
  protected final String TEXT_473 = ".validate" + NL + "\t\t\t\t(";
  protected final String TEXT_474 = "," + NL + "\t\t\t\t this," + NL + "\t\t\t\t ";
  protected final String TEXT_475 = "," + NL + "\t\t\t\t ";
  protected final String TEXT_476 = "," + NL + "\t\t\t\t \"";
  protected final String TEXT_477 = "\",";
  protected final String TEXT_478 = NL + "\t\t\t\t ";
  protected final String TEXT_479 = "__EEXPRESSION," + NL + "\t\t\t\t ";
  protected final String TEXT_480 = ".ERROR," + NL + "\t\t\t\t ";
  protected final String TEXT_481 = ".DIAGNOSTIC_SOURCE," + NL + "\t\t\t\t ";
  protected final String TEXT_482 = NL + "\t\t// TODO: implement this method" + NL + "\t\t// -> specify the condition that violates the invariant" + NL + "\t\t// -> verify the details of the diagnostic, including severity and message" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tif (false)" + NL + "\t\t{" + NL + "\t\t\tif (";
  protected final String TEXT_483 = " != null)" + NL + "\t\t\t{" + NL + "\t\t\t\t";
  protected final String TEXT_484 = ".add" + NL + "\t\t\t\t\t(new ";
  protected final String TEXT_485 = NL + "\t\t\t\t\t\t(";
  protected final String TEXT_486 = ".ERROR," + NL + "\t\t\t\t\t\t ";
  protected final String TEXT_487 = ".DIAGNOSTIC_SOURCE," + NL + "\t\t\t\t\t\t ";
  protected final String TEXT_488 = "," + NL + "\t\t\t\t\t\t ";
  protected final String TEXT_489 = ".INSTANCE.getString(\"_UI_GenericInvariant_diagnostic\", new Object[] { \"";
  protected final String TEXT_490 = "\", ";
  protected final String TEXT_491 = ".getObjectLabel(this, ";
  protected final String TEXT_492 = ") }),";
  protected final String TEXT_493 = NL + "\t\t\t\t\t\t new Object [] { this }));" + NL + "\t\t\t}" + NL + "\t\t\treturn false;" + NL + "\t\t}" + NL + "\t\treturn true;";
  protected final String TEXT_494 = NL + "\t\ttry" + NL + "\t\t{";
  protected final String TEXT_495 = "__EINVOCATION_DELEGATE.dynamicInvoke(this, ";
  protected final String TEXT_496 = ".UnmodifiableEList<Object>(";
  protected final String TEXT_497 = "null";
  protected final String TEXT_498 = NL + "\t\t\treturn ";
  protected final String TEXT_499 = NL + "\t\t}" + NL + "\t\tcatch (";
  protected final String TEXT_500 = " ite)" + NL + "\t\t{" + NL + "\t\t\tthrow new ";
  protected final String TEXT_501 = "(ite);" + NL + "\t\t}";
  protected final String TEXT_502 = NL + "\t\t// TODO: implement this method" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_503 = " eInverseAdd(";
  protected final String TEXT_504 = " otherEnd, int featureID, ";
  protected final String TEXT_505 = " msgs)" + NL + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_506 = ")" + NL + "\t\t{";
  protected final String TEXT_507 = NL + "\t\t\tcase ";
  protected final String TEXT_508 = ":";
  protected final String TEXT_509 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_510 = ".InternalMapView";
  protected final String TEXT_511 = "()).eMap()).basicAdd(otherEnd, msgs);";
  protected final String TEXT_512 = NL + "\t\t\t\treturn (";
  protected final String TEXT_513 = "()).basicAdd(otherEnd, msgs);";
  protected final String TEXT_514 = NL + "\t\t\t\tif (eInternalContainer() != null)" + NL + "\t\t\t\t\tmsgs = eBasicRemoveFromContainer(msgs);";
  protected final String TEXT_515 = NL + "\t\t\t\treturn basicSet";
  protected final String TEXT_516 = ")otherEnd, msgs);";
  protected final String TEXT_517 = NL + "\t\t\t\treturn eBasicSetContainer(otherEnd, ";
  protected final String TEXT_518 = NL + "\t\t\t\tif (";
  protected final String TEXT_519 = NL + "\t\t\t\t\tmsgs = ((";
  protected final String TEXT_520 = NL + "\t\treturn super.eInverseAdd(otherEnd, featureID, msgs);";
  protected final String TEXT_521 = NL + "\t\treturn eDynamicInverseAdd(otherEnd, featureID, msgs);";
  protected final String TEXT_522 = " eInverseRemove(";
  protected final String TEXT_523 = "()).eMap()).basicRemove(otherEnd, msgs);";
  protected final String TEXT_524 = ".Internal.Wrapper)";
  protected final String TEXT_525 = "()).featureMap()).basicRemove(otherEnd, msgs);";
  protected final String TEXT_526 = "()).basicRemove(otherEnd, msgs);";
  protected final String TEXT_527 = NL + "\t\t\t\treturn eBasicSetContainer(null, ";
  protected final String TEXT_528 = NL + "\t\t\t\treturn basicUnset";
  protected final String TEXT_529 = "(msgs);";
  protected final String TEXT_530 = "(null, msgs);";
  protected final String TEXT_531 = NL + "\t\treturn super.eInverseRemove(otherEnd, featureID, msgs);";
  protected final String TEXT_532 = NL + "\t\treturn eDynamicInverseRemove(otherEnd, featureID, msgs);";
  protected final String TEXT_533 = " eBasicRemoveFromContainerFeature(";
  protected final String TEXT_534 = " msgs)" + NL + "\t{" + NL + "\t\tswitch (eContainerFeatureID()";
  protected final String TEXT_535 = ":" + NL + "\t\t\t\treturn eInternalContainer().eInverseRemove(this, ";
  protected final String TEXT_536 = NL + "\t\treturn super.eBasicRemoveFromContainerFeature(msgs);";
  protected final String TEXT_537 = NL + "\t\treturn eDynamicBasicRemoveFromContainer(msgs);";
  protected final String TEXT_538 = NL + "\tpublic Object eGet(int featureID, boolean resolve, boolean coreType)" + NL + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_539 = NL + "\t\t\t\treturn ";
  protected final String TEXT_540 = "() ? Boolean.TRUE : Boolean.FALSE;";
  protected final String TEXT_541 = NL + "\t\t\t\treturn new ";
  protected final String TEXT_542 = "());";
  protected final String TEXT_543 = NL + "\t\t\t\tif (resolve) return ";
  protected final String TEXT_544 = "();" + NL + "\t\t\t\treturn basicGet";
  protected final String TEXT_545 = NL + "\t\t\t\tif (coreType) return ((";
  protected final String TEXT_546 = "()).eMap();" + NL + "\t\t\t\telse return ";
  protected final String TEXT_547 = NL + "\t\t\t\tif (coreType) return ";
  protected final String TEXT_548 = "();" + NL + "\t\t\t\telse return ";
  protected final String TEXT_549 = "().map();";
  protected final String TEXT_550 = "()).featureMap();" + NL + "\t\t\t\treturn ";
  protected final String TEXT_551 = "();" + NL + "\t\t\t\treturn ((";
  protected final String TEXT_552 = "()).getWrapper();";
  protected final String TEXT_553 = NL + "\t\treturn super.eGet(featureID, resolve, coreType);";
  protected final String TEXT_554 = NL + "\t\treturn eDynamicGet(featureID, resolve, coreType);";
  protected final String TEXT_555 = NL + "\tpublic void eSet(int featureID, Object newValue)" + NL + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_556 = NL + "\t\t\t\t((";
  protected final String TEXT_557 = "()).featureMap()).set(newValue);";
  protected final String TEXT_558 = "()).set(newValue);";
  protected final String TEXT_559 = ".Setting)((";
  protected final String TEXT_560 = "()).eMap()).set(newValue);";
  protected final String TEXT_561 = ".Setting)";
  protected final String TEXT_562 = "().clear();" + NL + "\t\t\t\t";
  protected final String TEXT_563 = "().addAll((";
  protected final String TEXT_564 = "<? extends ";
  protected final String TEXT_565 = ">";
  protected final String TEXT_566 = ")newValue);";
  protected final String TEXT_567 = NL + "\t\t\t\tset";
  protected final String TEXT_568 = "(((";
  protected final String TEXT_569 = ")newValue).";
  protected final String TEXT_570 = "newValue);";
  protected final String TEXT_571 = NL + "\t\t\t\treturn;";
  protected final String TEXT_572 = NL + "\t\tsuper.eSet(featureID, newValue);";
  protected final String TEXT_573 = NL + "\t\teDynamicSet(featureID, newValue);";
  protected final String TEXT_574 = NL + "\tpublic void eUnset(int featureID)" + NL + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_575 = "()).featureMap().clear();";
  protected final String TEXT_576 = "().clear();";
  protected final String TEXT_577 = NL + "\t\t\t\tunset";
  protected final String TEXT_578 = ")null);";
  protected final String TEXT_579 = NL + "\t\tsuper.eUnset(featureID);";
  protected final String TEXT_580 = NL + "\t\teDynamicUnset(featureID);";
  protected final String TEXT_581 = NL + "\tpublic boolean eIsSet(int featureID)" + NL + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_582 = NL + "\t\t\t\treturn isSet";
  protected final String TEXT_583 = NL + "\t\t\t\treturn !((";
  protected final String TEXT_584 = "()).featureMap().isEmpty();";
  protected final String TEXT_585 = " != null && !";
  protected final String TEXT_586 = ".featureMap().isEmpty();";
  protected final String TEXT_587 = ".isEmpty();";
  protected final String TEXT_588 = ");" + NL + "\t\t\t\treturn ";
  protected final String TEXT_589 = NL + "\t\t\t\treturn !";
  protected final String TEXT_590 = "().isEmpty();";
  protected final String TEXT_591 = " != null;";
  protected final String TEXT_592 = NL + "\t\t\t\treturn eVirtualGet((";
  protected final String TEXT_593 = ")) != null;";
  protected final String TEXT_594 = NL + "\t\t\t\treturn basicGet";
  protected final String TEXT_595 = "() != null;";
  protected final String TEXT_596 = "_EFLAG) != 0) != ";
  protected final String TEXT_597 = "_EFLAG) != ";
  protected final String TEXT_598 = NL + "\t\t\t\treturn eVirtualGet(";
  protected final String TEXT_599 = ") != ";
  protected final String TEXT_600 = "() != ";
  protected final String TEXT_601 = " != null : !";
  protected final String TEXT_602 = ".equals(";
  protected final String TEXT_603 = "() != null : !";
  protected final String TEXT_604 = NL + "\t\treturn super.eIsSet(featureID);";
  protected final String TEXT_605 = NL + "\t\treturn eDynamicIsSet(featureID);";
  protected final String TEXT_606 = NL + "\tpublic int eBaseStructuralFeatureID(int derivedFeatureID, Class";
  protected final String TEXT_607 = " baseClass)" + NL + "\t{";
  protected final String TEXT_608 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_609 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (derivedFeatureID";
  protected final String TEXT_610 = NL + "\t\t\t\tcase ";
  protected final String TEXT_611 = ": return ";
  protected final String TEXT_612 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_613 = NL + "\t\treturn super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);" + NL + "\t}";
  protected final String TEXT_614 = NL + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_615 = NL + "\tpublic int eDerivedStructuralFeatureID(int baseFeatureID, Class";
  protected final String TEXT_616 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (baseFeatureID)" + NL + "\t\t\t{";
  protected final String TEXT_617 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (baseFeatureID";
  protected final String TEXT_618 = NL + "\t\treturn super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);" + NL + "\t}" + NL;
  protected final String TEXT_619 = NL + "\tpublic int eDerivedOperationID(int baseOperationID, Class";
  protected final String TEXT_620 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (baseOperationID)" + NL + "\t\t\t{";
  protected final String TEXT_621 = NL + "\t\t\t\tdefault: return super.eDerivedOperationID(baseOperationID, baseClass);" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_622 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (baseOperationID";
  protected final String TEXT_623 = NL + "\t\treturn super.eDerivedOperationID(baseOperationID, baseClass);" + NL + "\t}" + NL;
  protected final String TEXT_624 = NL + "\tprotected Object[] eVirtualValues()" + NL + "\t{" + NL + "\t\treturn ";
  protected final String TEXT_625 = ";" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_626 = NL + "\tprotected void eSetVirtualValues(Object[] newValues)" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_627 = " = newValues;" + NL + "\t}" + NL;
  protected final String TEXT_628 = NL + "\tprotected int eVirtualIndexBits(int offset)" + NL + "\t{" + NL + "\t\tswitch (offset)" + NL + "\t\t{";
  protected final String TEXT_629 = " :" + NL + "\t\t\t\treturn ";
  protected final String TEXT_630 = NL + "\t\t\tdefault :" + NL + "\t\t\t\tthrow new IndexOutOfBoundsException();" + NL + "\t\t}" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_631 = NL + "\tprotected void eSetVirtualIndexBits(int offset, int newIndexBits)" + NL + "\t{" + NL + "\t\tswitch (offset)" + NL + "\t\t{";
  protected final String TEXT_632 = " :" + NL + "\t\t\t\t";
  protected final String TEXT_633 = " = newIndexBits;" + NL + "\t\t\t\tbreak;";
  protected final String TEXT_634 = NL + "\t\t\tdefault :" + NL + "\t\t\t\tthrow new IndexOutOfBoundsException();" + NL + "\t\t}" + NL + "\t}" + NL;
  protected final String TEXT_635 = NL + "\t@SuppressWarnings(";
  protected final String TEXT_636 = "\"unchecked\"";
  protected final String TEXT_637 = "{\"rawtypes\", \"unchecked\" }";
  protected final String TEXT_638 = NL + "\tpublic Object eInvoke(int operationID, ";
  protected final String TEXT_639 = " arguments) throws ";
  protected final String TEXT_640 = NL + "\t{" + NL + "\t\tswitch (operationID";
  protected final String TEXT_641 = NL + "\t\t\t\ttry" + NL + "\t\t\t\t{";
  protected final String TEXT_642 = "arguments.get(";
  protected final String TEXT_643 = ");" + NL + "\t\t\t\t";
  protected final String TEXT_644 = "return null;";
  protected final String TEXT_645 = "return ";
  protected final String TEXT_646 = NL + "\t\t\t\t}" + NL + "\t\t\t\tcatch (";
  protected final String TEXT_647 = " throwable)" + NL + "\t\t\t\t{" + NL + "\t\t\t\t\tthrow new ";
  protected final String TEXT_648 = "(throwable);" + NL + "\t\t\t\t}";
  protected final String TEXT_649 = NL + "\t\treturn super.eInvoke(operationID, arguments);";
  protected final String TEXT_650 = NL + "\t\treturn eDynamicInvoke(operationID, arguments);";
  protected final String TEXT_651 = NL + "\tpublic String toString()" + NL + "\t{" + NL + "\t\tif (eIsProxy()) return super.toString();" + NL + "" + NL + "\t\t";
  protected final String TEXT_652 = " result = new ";
  protected final String TEXT_653 = "(super.toString());";
  protected final String TEXT_654 = NL + "\t\tresult.append(\" (";
  protected final String TEXT_655 = ": \");";
  protected final String TEXT_656 = NL + "\t\tresult.append(\", ";
  protected final String TEXT_657 = NL + "\t\tif (eVirtualIsSet(";
  protected final String TEXT_658 = ")) result.append(eVirtualGet(";
  protected final String TEXT_659 = ")); else result.append(\"<unset>\");";
  protected final String TEXT_660 = "_ESETFLAG) != 0";
  protected final String TEXT_661 = ") result.append((";
  protected final String TEXT_662 = "_EFLAG) != 0); else result.append(\"<unset>\");";
  protected final String TEXT_663 = ") result.append(";
  protected final String TEXT_664 = "_EFLAG_OFFSET]); else result.append(\"<unset>\");";
  protected final String TEXT_665 = "); else result.append(\"<unset>\");";
  protected final String TEXT_666 = NL + "\t\tresult.append(eVirtualGet(";
  protected final String TEXT_667 = NL + "\t\tresult.append((";
  protected final String TEXT_668 = "_EFLAG) != 0);";
  protected final String TEXT_669 = NL + "\t\tresult.append(";
  protected final String TEXT_670 = "_EFLAG_OFFSET]);";
  protected final String TEXT_671 = NL + "\t\tresult.append(')');" + NL + "\t\treturn result.toString();" + NL + "\t}" + NL;
  protected final String TEXT_672 = NL + "\tprotected int hash = -1;" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_673 = NL + "\tpublic int getHash()" + NL + "\t{" + NL + "\t\tif (hash == -1)" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_674 = " theKey = getKey();" + NL + "\t\t\thash = (theKey == null ? 0 : theKey.hashCode());" + NL + "\t\t}" + NL + "\t\treturn hash;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_675 = NL + "\tpublic void setHash(int hash)" + NL + "\t{" + NL + "\t\tthis.hash = hash;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_676 = " getKey()" + NL + "\t{";
  protected final String TEXT_677 = "(getTypedKey());";
  protected final String TEXT_678 = NL + "\t\treturn getTypedKey();";
  protected final String TEXT_679 = NL + "\tpublic void setKey(";
  protected final String TEXT_680 = " key)" + NL + "\t{";
  protected final String TEXT_681 = NL + "\t\tgetTypedKey().addAll(";
  protected final String TEXT_682 = "key);";
  protected final String TEXT_683 = NL + "\t\tsetTypedKey(key);";
  protected final String TEXT_684 = NL + "\t\tsetTypedKey(((";
  protected final String TEXT_685 = ")key).";
  protected final String TEXT_686 = NL + "\t\tsetTypedKey((";
  protected final String TEXT_687 = ")key);";
  protected final String TEXT_688 = " getValue()" + NL + "\t{";
  protected final String TEXT_689 = "(getTypedValue());";
  protected final String TEXT_690 = NL + "\t\treturn getTypedValue();";
  protected final String TEXT_691 = " setValue(";
  protected final String TEXT_692 = " value)" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_693 = " oldValue = getValue();";
  protected final String TEXT_694 = NL + "\t\tgetTypedValue().clear();" + NL + "\t\tgetTypedValue().addAll(";
  protected final String TEXT_695 = "value);";
  protected final String TEXT_696 = NL + "\t\tsetTypedValue(value);";
  protected final String TEXT_697 = NL + "\t\tsetTypedValue(((";
  protected final String TEXT_698 = ")value).";
  protected final String TEXT_699 = NL + "\t\tsetTypedValue((";
  protected final String TEXT_700 = ")value);";
  protected final String TEXT_701 = NL + "\t\treturn oldValue;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_702 = " getEMap()" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_703 = " container = eContainer();" + NL + "\t\treturn container == null ? null : (";
  protected final String TEXT_704 = ")container.eGet(eContainmentFeature());" + NL + "\t}" + NL;
  protected final String TEXT_705 = NL + "\t/**" + NL + "\t * Accept a visit from a visitor and return the result of a call to the derived type-specific visitXXX in the visitor." + NL + "\t * @generated" + NL + "\t */" + NL + "\t// Generated from org.eclipse.ocl.examples.build/templates/model/Class/insert.javajetinc" + NL + "\tpublic <R> R accept(";
  protected final String TEXT_706 = "<R> visitor);";
  protected final String TEXT_707 = NL + "\t/**" + NL + "\t * {@inheritDoc}" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_708 = NL + "\tpublic <R> R accept(";
  protected final String TEXT_709 = "<R> visitor) {";
  protected final String TEXT_710 = NL + "\t\tif (visitor instanceof ";
  protected final String TEXT_711 = ") {" + NL + "\t\t\treturn (R) ((";
  protected final String TEXT_712 = "<?>)visitor).visit";
  protected final String TEXT_713 = "(this);" + NL + "\t\t}" + NL + "\t\telse {" + NL + "\t\t\treturn super.accept(visitor);" + NL + "\t\t}";
  protected final String TEXT_714 = NL + "\t\treturn visitor.visit";
  protected final String TEXT_715 = "(this);";
  protected final String TEXT_716 = NL + "\t}";
  protected final String TEXT_717 = NL + "} //";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
/**
 * Copyright (c) 2002-2019 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */

    final GenClass genClass = (GenClass)((Object[])argument)[0]; final GenPackage genPackage = genClass.getGenPackage(); final GenModel genModel=genPackage.getGenModel();
    final boolean isJDK50 = genModel.getComplianceLevel().getValue() >= GenJDKLevel.JDK50;
    final boolean isInterface = Boolean.TRUE.equals(((Object[])argument)[1]); final boolean isImplementation = Boolean.TRUE.equals(((Object[])argument)[2]); final boolean useInterfaceOverrideAnnotation = OCLGenModelUtil.INSTANCE.useInterfaceOverrideAnnotation(genModel) && !(isInterface && isImplementation);
    final boolean isGWT = genModel.getRuntimePlatform() == GenRuntimePlatform.GWT;
    final String publicStaticFinalFlag = isImplementation ? "public static final " : "";
    final String singleWildcard = isJDK50 ? "<?>" : "";
    final String negativeOffsetCorrection = genClass.hasOffsetCorrection() ? " - " + genClass.getOffsetCorrectionField(null) : "";
    final String positiveOffsetCorrection = genClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(null) : "";
    final String negativeOperationOffsetCorrection = genClass.hasOffsetCorrection() ? " - EOPERATION_OFFSET_CORRECTION" : "";
    final String positiveOperationOffsetCorrection = genClass.hasOffsetCorrection() ? " + EOPERATION_OFFSET_CORRECTION" : "";
    stringBuffer.append(TEXT_1);
    stringBuffer.append(TEXT_2);
    {GenBase copyrightHolder = argument instanceof GenBase ? (GenBase)argument : argument instanceof Object[] && ((Object[])argument)[0] instanceof GenBase ? (GenBase)((Object[])argument)[0] : null;
    if (copyrightHolder != null && copyrightHolder.hasCopyright()) {
    stringBuffer.append(TEXT_3);
    stringBuffer.append(copyrightHolder.getCopyright(copyrightHolder.getGenModel().getIndentation(stringBuffer)));
    }}
    stringBuffer.append(TEXT_4);
    if (isInterface) {
    stringBuffer.append(TEXT_5);
    stringBuffer.append(genPackage.getInterfacePackageName());
    stringBuffer.append(TEXT_6);
    } else {
    stringBuffer.append(TEXT_5);
    stringBuffer.append(genPackage.getClassPackageName());
    stringBuffer.append(TEXT_6);
    }
    stringBuffer.append(TEXT_7);
    genModel.markImportLocation(stringBuffer, genPackage);
    if (isImplementation) { genClass.addClassPsuedoImports(); }
    stringBuffer.append(TEXT_7);
    if (isInterface) {
    stringBuffer.append(TEXT_8);
    stringBuffer.append(genClass.getFormattedName());
    stringBuffer.append(TEXT_9);
    if (genClass.hasDocumentation()) {
    stringBuffer.append(TEXT_10);
    stringBuffer.append(genClass.getDocumentation(genModel.getIndentation(stringBuffer)));
    stringBuffer.append(TEXT_11);
    }
    stringBuffer.append(TEXT_12);
    if (!genClass.getGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_13);
    for (GenFeature genFeature : genClass.getGenFeatures()) {
    if (!genFeature.isSuppressedGetVisibility()) {
    stringBuffer.append(TEXT_14);
    stringBuffer.append(OCLGenModelUtil.INSTANCE.getRawQualifiedInterfaceName(genClass));
    stringBuffer.append(TEXT_15);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_16);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_17);
    }
    }
    stringBuffer.append(TEXT_18);
    }
    stringBuffer.append(TEXT_12);
    if (!genModel.isSuppressEMFMetaData()) {
    stringBuffer.append(TEXT_19);
    stringBuffer.append(genPackage.getQualifiedPackageInterfaceName());
    stringBuffer.append(TEXT_20);
    stringBuffer.append(genClass.getClassifierAccessorName());
    stringBuffer.append(TEXT_21);
    }
    if (!genModel.isSuppressEMFModelTags()) { boolean first = true; for (StringTokenizer stringTokenizer = new StringTokenizer(genClass.getModelInfo(), "\n\r"); stringTokenizer.hasMoreTokens(); ) { String modelInfo = stringTokenizer.nextToken(); if (first) { first = false;
    stringBuffer.append(TEXT_22);
    stringBuffer.append(modelInfo);
    } else {
    stringBuffer.append(TEXT_23);
    stringBuffer.append(modelInfo);
    }} if (first) {
    stringBuffer.append(TEXT_24);
    }}
    if (genClass.needsRootExtendsInterfaceExtendsTag()) {
    stringBuffer.append(TEXT_25);
    stringBuffer.append(genModel.getImportedName(genModel.getRootExtendsInterface()));
    }
    if (OCLGenModelUtil.INSTANCE.hasImplicitAPITags(genClass, true)) {
    stringBuffer.append(TEXT_3);
    stringBuffer.append(OCLGenModelUtil.INSTANCE.getImplicitAPITags(genClass, genModel.getIndentation(stringBuffer), true));
    }
    stringBuffer.append(TEXT_26);
    //Class/interface.javadoc.override.javajetinc
    } else {
    stringBuffer.append(TEXT_27);
    stringBuffer.append(genClass.getFormattedName());
    stringBuffer.append(TEXT_9);
    if (!genClass.getImplementedGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_28);
    for (GenFeature genFeature : genClass.getImplementedGenFeatures()) {
    stringBuffer.append(TEXT_14);
    stringBuffer.append(genClass.getQualifiedClassName());
    stringBuffer.append(TEXT_15);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_16);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_17);
    }
    stringBuffer.append(TEXT_18);
    }
    stringBuffer.append(TEXT_12);
    if (OCLGenModelUtil.INSTANCE.hasImplicitAPITags(genClass)) {
    stringBuffer.append(TEXT_3);
    stringBuffer.append(OCLGenModelUtil.INSTANCE.getImplicitAPITags(genClass, genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_26);
    }
    if (isJDK50 && OCLGenModelUtil.INSTANCE.hasImplicitAPIDeprecatedTag(genClass)) {
    stringBuffer.append(TEXT_29);
    }
    if (isImplementation) {
    if (isJDK50 && !OCLGenModelUtil.INSTANCE.hasAPIDeprecatedTag(genClass) && OCLGenModelUtil.INSTANCE.hasImplicitAPIDeprecatedTag(genClass.getEGetGenFeatures(), genClass.getEIsSetGenFeatures(), genClass.getESetGenFeatures(), genClass.getEUnsetGenFeatures(), genClass.getEInverseAddGenFeatures(), genClass.getEInverseRemoveGenFeatures(), genClass.getEBasicRemoveFromContainerGenFeatures(), genClass.getToStringGenFeatures())) {
    stringBuffer.append(TEXT_30);
    }
    stringBuffer.append(TEXT_31);
    if (genClass.isAbstract()) {
    stringBuffer.append(TEXT_32);
    }
    stringBuffer.append(TEXT_33);
    stringBuffer.append(genClass.getClassName());
    stringBuffer.append(genClass.getTypeParameters().trim());
    stringBuffer.append(genClass.getClassExtends());
    stringBuffer.append(genClass.getClassImplements());
    } else {
    stringBuffer.append(TEXT_34);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(genClass.getTypeParameters().trim());
    stringBuffer.append(genClass.getInterfaceExtends());
    }
    stringBuffer.append(TEXT_35);
    if (genModel.hasCopyrightField()) {
    stringBuffer.append(TEXT_36);
    stringBuffer.append(publicStaticFinalFlag);
    stringBuffer.append(genModel.getImportedName("java.lang.String"));
    stringBuffer.append(TEXT_37);
    stringBuffer.append(genModel.getCopyrightFieldLiteral());
    stringBuffer.append(TEXT_6);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_7);
    }
    if (isImplementation && genModel.getDriverNumber() != null) {
    stringBuffer.append(TEXT_38);
    stringBuffer.append(genModel.getImportedName("java.lang.String"));
    stringBuffer.append(TEXT_39);
    stringBuffer.append(genModel.getDriverNumber());
    stringBuffer.append(TEXT_40);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_7);
    }
    if (isImplementation && genClass.isJavaIOSerializable()) {
    stringBuffer.append(TEXT_41);
    }
    if (isImplementation && genModel.isVirtualDelegation()) { String eVirtualValuesField = genClass.getEVirtualValuesField();
    if (eVirtualValuesField != null) {
    stringBuffer.append(TEXT_42);
    if (isGWT) {
    stringBuffer.append(TEXT_43);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    stringBuffer.append(TEXT_44);
    stringBuffer.append(eVirtualValuesField);
    stringBuffer.append(TEXT_45);
    }
    { List<String> eVirtualIndexBitFields = genClass.getEVirtualIndexBitFields(new ArrayList<String>());
    if (!eVirtualIndexBitFields.isEmpty()) {
    for (String eVirtualIndexBitField : eVirtualIndexBitFields) {
    stringBuffer.append(TEXT_46);
    if (isGWT) {
    stringBuffer.append(TEXT_43);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    stringBuffer.append(TEXT_47);
    stringBuffer.append(eVirtualIndexBitField);
    stringBuffer.append(TEXT_45);
    }
    }
    }
    }
    if (isImplementation && genClass.isModelRoot() && genModel.isBooleanFlagsEnabled() && genModel.getBooleanFlagsReservedBits() == -1) {
    stringBuffer.append(TEXT_48);
    if (isGWT) {
    stringBuffer.append(TEXT_43);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    stringBuffer.append(TEXT_47);
    stringBuffer.append(genModel.getBooleanFlagsField());
    stringBuffer.append(TEXT_49);
    }
    if (isImplementation && !genModel.isReflectiveDelegation()) {
    stringBuffer.append(TEXT_50);
    stringBuffer.append(genClass.getFormattedName());
    stringBuffer.append(TEXT_51);
    if (OCLGenModelUtil.INSTANCE.hasAPITags(genClass)) {
    stringBuffer.append(TEXT_52);
    stringBuffer.append(OCLGenModelUtil.INSTANCE.getAPITags(genClass, genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_53);
    if (isJDK50 && OCLGenModelUtil.INSTANCE.hasAPIDeprecatedTag(genClass)) {
    stringBuffer.append(TEXT_54);
    }
    stringBuffer.append(TEXT_55);
    stringBuffer.append(publicStaticFinalFlag);
    stringBuffer.append(TEXT_56);
    stringBuffer.append(genClass.getFeatureCountID());
    stringBuffer.append(TEXT_57);
    stringBuffer.append(OCLGenModelUtil.getFeatureCountValue(genClass));
    stringBuffer.append(TEXT_45);
    if (genModel.isOperationReflection()) {
    stringBuffer.append(TEXT_58);
    stringBuffer.append(genClass.getFormattedName());
    stringBuffer.append(TEXT_51);
    if (OCLGenModelUtil.INSTANCE.hasAPITags(genClass)) {
    stringBuffer.append(TEXT_52);
    stringBuffer.append(OCLGenModelUtil.INSTANCE.getAPITags(genClass, genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_53);
    if (isJDK50 && OCLGenModelUtil.INSTANCE.hasAPIDeprecatedTag(genClass)) {
    stringBuffer.append(TEXT_54);
    }
    stringBuffer.append(TEXT_55);
    stringBuffer.append(publicStaticFinalFlag);
    stringBuffer.append(TEXT_56);
    stringBuffer.append(genClass.getOperationCountID());
    stringBuffer.append(TEXT_57);
    stringBuffer.append(OCLGenModelUtil.getOperationCountValue(genClass));
    stringBuffer.append(TEXT_45);
    }
    stringBuffer.append(TEXT_7);
    for (GenFeature genFeature : genClass.getReifiedGenFeatures()) {
    if (genModel.isArrayAccessors() && genFeature.isListType() && !genFeature.isFeatureMapType() && !genFeature.isMapType()) { String rawListItemType = genFeature.getRawListItemType(genClass); int index = rawListItemType.indexOf('['); String head = rawListItemType; String tail = ""; if (index != -1) { head = rawListItemType.substring(0, index); tail = rawListItemType.substring(index); } 
    stringBuffer.append(TEXT_59);
    stringBuffer.append(genFeature.getGetArrayAccessor());
    stringBuffer.append(TEXT_60);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_61);
    stringBuffer.append(genFeature.getGetArrayAccessor());
    stringBuffer.append(TEXT_21);
    if (OCLGenModelUtil.INSTANCE.hasAPITags(genFeature)) {
    stringBuffer.append(TEXT_52);
    stringBuffer.append(OCLGenModelUtil.INSTANCE.getAPITags(genFeature, genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_53);
    if (isJDK50 && OCLGenModelUtil.INSTANCE.hasAPIDeprecatedTag(genFeature)) {
    stringBuffer.append(TEXT_54);
    }
    if (genFeature.getQualifiedListItemType(genClass).contains("<") || genFeature.getArrayItemType(genClass).contains("<")) {
    stringBuffer.append(TEXT_62);
    }
    stringBuffer.append(TEXT_63);
    stringBuffer.append(rawListItemType);
    stringBuffer.append(TEXT_64);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_65);
    stringBuffer.append(head);
    stringBuffer.append(TEXT_66);
    stringBuffer.append(tail);
    stringBuffer.append(TEXT_45);
    }
    }
    for (GenFeature genFeature : genClass.getDeclaredFieldGenFeatures()) {
    if (genFeature.hasSettingDelegate()) {
    stringBuffer.append(TEXT_67);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_60);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_68);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_69);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_21);
    if (OCLGenModelUtil.INSTANCE.hasAPITags(genFeature)) {
    stringBuffer.append(TEXT_52);
    stringBuffer.append(OCLGenModelUtil.INSTANCE.getAPITags(genFeature, genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_53);
    if (isGWT) {
    stringBuffer.append(TEXT_43);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    if (isJDK50 && OCLGenModelUtil.INSTANCE.hasAPIDeprecatedTag(genFeature)) {
    stringBuffer.append(TEXT_54);
    }
    stringBuffer.append(TEXT_70);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_71);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_72);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_73);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_74);
    } else if (genFeature.isListType() || genFeature.isReferenceType()) {
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_75);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_60);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_68);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_69);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_21);
    if (OCLGenModelUtil.INSTANCE.hasAPITags(genFeature)) {
    stringBuffer.append(TEXT_52);
    stringBuffer.append(OCLGenModelUtil.INSTANCE.getAPITags(genFeature, genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_53);
    if (isGWT) {
    stringBuffer.append(TEXT_43);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    if (isJDK50 && OCLGenModelUtil.INSTANCE.hasAPIDeprecatedTag(genFeature)) {
    stringBuffer.append(TEXT_54);
    }
    stringBuffer.append(TEXT_70);
    stringBuffer.append(genFeature.getImportedInternalType(genClass));
    stringBuffer.append(TEXT_76);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_45);
    }
    if (genModel.isArrayAccessors() && genFeature.isListType() && !genFeature.isFeatureMapType() && !genFeature.isMapType()) { String rawListItemType = genFeature.getRawListItemType(genClass); int index = rawListItemType.indexOf('['); String head = rawListItemType; String tail = ""; if (index != -1) { head = rawListItemType.substring(0, index); tail = rawListItemType.substring(index); } 
    stringBuffer.append(TEXT_59);
    stringBuffer.append(genFeature.getGetArrayAccessor());
    stringBuffer.append(TEXT_60);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_77);
    stringBuffer.append(genFeature.getGetArrayAccessor());
    stringBuffer.append(TEXT_21);
    if (OCLGenModelUtil.INSTANCE.hasAPITags(genFeature)) {
    stringBuffer.append(TEXT_52);
    stringBuffer.append(OCLGenModelUtil.INSTANCE.getAPITags(genFeature, genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_53);
    if (genFeature.getQualifiedListItemType(genClass).contains("<") || genFeature.getArrayItemType(genClass).contains("<")) {
    stringBuffer.append(TEXT_62);
    }
    if (isJDK50 && OCLGenModelUtil.INSTANCE.hasAPIDeprecatedTag(genFeature)) {
    stringBuffer.append(TEXT_54);
    }
    stringBuffer.append(TEXT_63);
    stringBuffer.append(rawListItemType);
    stringBuffer.append(TEXT_64);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_65);
    stringBuffer.append(head);
    stringBuffer.append(TEXT_66);
    stringBuffer.append(tail);
    stringBuffer.append(TEXT_45);
    }
    } else {
    if (genFeature.hasEDefault() && (!genFeature.isVolatile() || !genModel.isReflectiveDelegation() && (!genFeature.hasDelegateFeature() || !genFeature.isUnsettable()))) { String staticDefaultValue = genFeature.getStaticDefaultValue();
    stringBuffer.append(TEXT_78);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_60);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_68);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_69);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_21);
    if (OCLGenModelUtil.INSTANCE.hasAPITags(genFeature)) {
    stringBuffer.append(TEXT_52);
    stringBuffer.append(OCLGenModelUtil.INSTANCE.getAPITags(genFeature, genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_53);
    if (genModel.useGenerics() && genFeature.isListDataType() && genFeature.isSetDefaultValue()) {
    stringBuffer.append(TEXT_79);
    }
    if (isJDK50 && OCLGenModelUtil.INSTANCE.hasAPIDeprecatedTag(genFeature)) {
    stringBuffer.append(TEXT_54);
    }
    stringBuffer.append(TEXT_63);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_76);
    stringBuffer.append(genFeature.getEDefault());
    if ("".equals(staticDefaultValue)) {
    stringBuffer.append(TEXT_80);
    stringBuffer.append(genFeature.getEcoreFeature().getDefaultValueLiteral());
    stringBuffer.append(TEXT_81);
    } else {
    stringBuffer.append(TEXT_57);
    stringBuffer.append(staticDefaultValue);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(genModel.getNonNLS(staticDefaultValue));
    }
    stringBuffer.append(TEXT_7);
    }
    if (genClass.isField(genFeature)) {
    if (genClass.isFlag(genFeature)) { int flagIndex = genClass.getFlagIndex(genFeature);
    if (flagIndex > 31 && flagIndex % 32 == 0) {
    stringBuffer.append(TEXT_82);
    if (isGWT) {
    stringBuffer.append(TEXT_43);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    stringBuffer.append(TEXT_47);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_49);
    }
    if (genFeature.isEnumType()) {
    stringBuffer.append(TEXT_83);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_60);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_68);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_84);
    if (OCLGenModelUtil.INSTANCE.hasAPITags(genFeature)) {
    stringBuffer.append(TEXT_52);
    stringBuffer.append(OCLGenModelUtil.INSTANCE.getAPITags(genFeature, genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_53);
    if (isJDK50 && OCLGenModelUtil.INSTANCE.hasAPIDeprecatedTag(genFeature)) {
    stringBuffer.append(TEXT_54);
    }
    stringBuffer.append(TEXT_85);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_86);
    stringBuffer.append(flagIndex % 32);
    stringBuffer.append(TEXT_87);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_60);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_68);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_84);
    if (OCLGenModelUtil.INSTANCE.hasAPITags(genFeature)) {
    stringBuffer.append(TEXT_52);
    stringBuffer.append(OCLGenModelUtil.INSTANCE.getAPITags(genFeature, genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_53);
    if (isJDK50 && OCLGenModelUtil.INSTANCE.hasAPIDeprecatedTag(genFeature)) {
    stringBuffer.append(TEXT_54);
    }
    stringBuffer.append(TEXT_85);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_88);
    if (isJDK50) {
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_89);
    } else {
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_90);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_91);
    }
    stringBuffer.append(TEXT_92);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_93);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_76);
    stringBuffer.append(genFeature.getTypeGenClassifier().getFormattedName());
    stringBuffer.append(TEXT_94);
    if (OCLGenModelUtil.INSTANCE.hasAPITags(genFeature)) {
    stringBuffer.append(TEXT_52);
    stringBuffer.append(OCLGenModelUtil.INSTANCE.getAPITags(genFeature, genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_53);
    if (isJDK50 && OCLGenModelUtil.INSTANCE.hasAPIDeprecatedTag(genFeature)) {
    stringBuffer.append(TEXT_54);
    }
    stringBuffer.append(TEXT_95);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_64);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_96);
    if (isJDK50) {
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_97);
    } else {
    stringBuffer.append(TEXT_98);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_99);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_100);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_101);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_102);
    }
    stringBuffer.append(TEXT_45);
    }
    stringBuffer.append(TEXT_103);
    stringBuffer.append(genClass.getFlagSize(genFeature) > 1 ? "s" : "");
    stringBuffer.append(TEXT_104);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_60);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_68);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_69);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_21);
    if (OCLGenModelUtil.INSTANCE.hasAPITags(genFeature)) {
    stringBuffer.append(TEXT_52);
    stringBuffer.append(OCLGenModelUtil.INSTANCE.getAPITags(genFeature, genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_53);
    if (isJDK50 && OCLGenModelUtil.INSTANCE.hasAPIDeprecatedTag(genFeature)) {
    stringBuffer.append(TEXT_54);
    }
    stringBuffer.append(TEXT_85);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_105);
    stringBuffer.append(genClass.getFlagMask(genFeature));
    stringBuffer.append(TEXT_92);
    if (genFeature.isEnumType()) {
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_106);
    } else {
    stringBuffer.append(flagIndex % 32);
    }
    stringBuffer.append(TEXT_45);
    } else {
    stringBuffer.append(TEXT_75);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_60);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_68);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_69);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_21);
    if (OCLGenModelUtil.INSTANCE.hasAPITags(genFeature)) {
    stringBuffer.append(TEXT_52);
    stringBuffer.append(OCLGenModelUtil.INSTANCE.getAPITags(genFeature, genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_53);
    if (isGWT) {
    stringBuffer.append(TEXT_43);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    if (isJDK50 && OCLGenModelUtil.INSTANCE.hasAPIDeprecatedTag(genFeature)) {
    stringBuffer.append(TEXT_54);
    }
    stringBuffer.append(TEXT_70);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_76);
    stringBuffer.append(genFeature.getSafeName());
    if (genFeature.hasEDefault()) {
    stringBuffer.append(TEXT_57);
    stringBuffer.append(genFeature.getEDefault());
    }
    stringBuffer.append(TEXT_45);
    }
    }
    }
    if (genClass.isESetField(genFeature)) {
    if (genClass.isESetFlag(genFeature)) { int flagIndex = genClass.getESetFlagIndex(genFeature);
    if (flagIndex > 31 && flagIndex % 32 == 0) {
    stringBuffer.append(TEXT_82);
    if (isGWT) {
    stringBuffer.append(TEXT_43);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    stringBuffer.append(TEXT_47);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_49);
    }
    stringBuffer.append(TEXT_107);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_76);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_108);
    if (OCLGenModelUtil.INSTANCE.hasAPITags(genFeature)) {
    stringBuffer.append(TEXT_52);
    stringBuffer.append(OCLGenModelUtil.INSTANCE.getAPITags(genFeature, genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_53);
    if (OCLGenModelUtil.INSTANCE.hasAPITags(genFeature)) {
    stringBuffer.append(TEXT_52);
    stringBuffer.append(OCLGenModelUtil.INSTANCE.getAPITags(genFeature, genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_85);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_109);
    stringBuffer.append(flagIndex % 32 );
    stringBuffer.append(TEXT_45);
    } else {
    stringBuffer.append(TEXT_110);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_76);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_108);
    if (OCLGenModelUtil.INSTANCE.hasAPITags(genFeature)) {
    stringBuffer.append(TEXT_52);
    stringBuffer.append(OCLGenModelUtil.INSTANCE.getAPITags(genFeature, genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_53);
    if (isGWT) {
    stringBuffer.append(TEXT_43);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    if (isJDK50 && OCLGenModelUtil.INSTANCE.hasAPIDeprecatedTag(genFeature)) {
    stringBuffer.append(TEXT_54);
    }
    stringBuffer.append(TEXT_111);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_112);
    }
    }
    //Class/declaredFieldGenFeature.override.javajetinc
    }
    }
    if (isImplementation && genClass.hasOffsetCorrection() && !genClass.getImplementedGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_113);
    stringBuffer.append(genClass.getOffsetCorrectionField(null));
    stringBuffer.append(TEXT_57);
    stringBuffer.append(genClass.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_114);
    stringBuffer.append(genClass.getImplementedGenFeatures().get(0).getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_115);
    stringBuffer.append(OCLGenModelUtil.getQualifiedFeatureValue(genClass, genClass.getImplementedGenFeatures().get(0)));
    stringBuffer.append(TEXT_45);
    }
    if (isImplementation && !genModel.isReflectiveDelegation()) {
    for (GenFeature genFeature : genClass.getImplementedGenFeatures()) { GenFeature reverseFeature = genFeature.getReverse();
    if (reverseFeature != null && reverseFeature.getGenClass().hasOffsetCorrection()) {
    stringBuffer.append(TEXT_116);
    if (OCLGenModelUtil.INSTANCE.hasAPITags(genFeature)) {
    stringBuffer.append(TEXT_52);
    stringBuffer.append(OCLGenModelUtil.INSTANCE.getAPITags(genFeature, genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_117);
    if (isJDK50 && OCLGenModelUtil.INSTANCE.hasAPIDeprecatedTag(genFeature)) {
    stringBuffer.append(TEXT_54);
    }
    stringBuffer.append(TEXT_118);
    stringBuffer.append(genClass.getOffsetCorrectionField(genFeature));
    stringBuffer.append(TEXT_57);
    stringBuffer.append(reverseFeature.getGenClass().getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_114);
    stringBuffer.append(reverseFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_115);
    stringBuffer.append(OCLGenModelUtil.getQualifiedFeatureValue(reverseFeature.getGenClass(), reverseFeature));
    stringBuffer.append(TEXT_45);
    }
    }
    }
    if (genModel.isOperationReflection() && isImplementation && genClass.hasOffsetCorrection() && !genClass.getImplementedGenOperations().isEmpty()) {
    stringBuffer.append(TEXT_119);
    stringBuffer.append(genClass.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_120);
    stringBuffer.append(genClass.getImplementedGenOperations().get(0).getQualifiedOperationAccessor());
    stringBuffer.append(TEXT_115);
    stringBuffer.append(OCLGenModelUtil.getQualifiedOperationValue(genClass, genClass.getImplementedGenOperations().get(0)));
    stringBuffer.append(TEXT_45);
    }
    if (isImplementation) {
    stringBuffer.append(TEXT_36);
    if (genModel.isPublicConstructors()) {
    stringBuffer.append(TEXT_121);
    } else {
    stringBuffer.append(TEXT_122);
    }
    stringBuffer.append(TEXT_76);
    stringBuffer.append(genClass.getClassName());
    stringBuffer.append(TEXT_123);
    for (GenFeature genFeature : genClass.getFlagGenFeaturesWithDefault()) {
    stringBuffer.append(TEXT_124);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_125);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_126);
    if (!genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_127);
    }
    stringBuffer.append(TEXT_6);
    }
    stringBuffer.append(TEXT_128);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_129);
    }
    stringBuffer.append(TEXT_70);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EClass"));
    stringBuffer.append(TEXT_130);
    stringBuffer.append(genClass.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_131);
    }
    if (isImplementation && genModel.isDynamicDelegation()) {
    }
    if (isImplementation && (genModel.getFeatureDelegation() == GenDelegationKind.REFLECTIVE_LITERAL || genModel.isDynamicDelegation()) && (genClass.getClassExtendsGenClass() == null || (genClass.getClassExtendsGenClass().getGenModel().getFeatureDelegation() != GenDelegationKind.REFLECTIVE_LITERAL && !genClass.getClassExtendsGenClass().getGenModel().isDynamicDelegation()))) {
    if (genClass.hasStaticFeatures()) {
    stringBuffer.append(TEXT_132);
    stringBuffer.append(genClass.getClassExtendsGenClass() == null ? 0 : genClass.getClassExtendsGenClass().getAllGenFeatures().size());
    stringBuffer.append(TEXT_45);
    }
    stringBuffer.append(TEXT_133);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_129);
    }
    stringBuffer.append(TEXT_134);
    stringBuffer.append(genClass.getClassExtendsGenClass() == null ? "0" : genClass.hasStaticFeatures() ? "ESTATIC_FEATURE_COUNT" : Integer.toString(genClass.getClassExtendsGenClass().getAllGenFeatures().size()));
    stringBuffer.append(TEXT_131);
    }
    //Class/reflectiveDelegation.override.javajetinc
    if (isImplementation) {
    new Runnable() { public void run() { GenClass classExtendsGenClass = genClass.getClassExtendsGenClass(); List<GenFeature> classExtendsAllGenFeatures = classExtendsGenClass == null? Collections.<GenFeature>emptyList() : classExtendsGenClass.getAllGenFeatures();
    for (GenFeature genFeature : genClass.getReifiedGenFeatures()) {
    if (genModel.isArrayAccessors() && genFeature.isListType() && !genFeature.isFeatureMapType() && !genFeature.isMapType()) { String arrayElementType = genFeature.getArrayItemType(genClass);
    stringBuffer.append(TEXT_135);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_21);
    if (OCLGenModelUtil.INSTANCE.hasAPITags(genFeature)) {
    stringBuffer.append(TEXT_52);
    stringBuffer.append(OCLGenModelUtil.INSTANCE.getAPITags(genFeature, genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_117);
    if (genModel.useGenerics() && CodeGenUtil.isUncheckedCast(arrayElementType)) {
    stringBuffer.append(TEXT_79);
    }
    if (isJDK50 && OCLGenModelUtil.INSTANCE.hasAPIDeprecatedTag(genFeature)) {
    stringBuffer.append(TEXT_54);
    }
    if (useInterfaceOverrideAnnotation || classExtendsAllGenFeatures.contains(genFeature)) {
    stringBuffer.append(TEXT_129);
    }
    stringBuffer.append(TEXT_136);
    stringBuffer.append(arrayElementType);
    stringBuffer.append(TEXT_64);
    stringBuffer.append(genFeature.getGetArrayAccessor());
    stringBuffer.append(TEXT_137);
    if (genFeature.isVolatile()) {
    stringBuffer.append(TEXT_124);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(genFeature.getListTemplateArguments(genClass));
    stringBuffer.append(TEXT_138);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(genFeature.getListTemplateArguments(genClass));
    stringBuffer.append(TEXT_91);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_139);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_140);
    } else {
    stringBuffer.append(TEXT_141);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_142);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_143);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_144);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(genFeature.getListTemplateArguments(genClass));
    stringBuffer.append(TEXT_138);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(genFeature.getListTemplateArguments(genClass));
    stringBuffer.append(TEXT_91);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_6);
    }
    stringBuffer.append(TEXT_145);
    stringBuffer.append(arrayElementType);
    stringBuffer.append(TEXT_146);
    }
    if (genFeature.isGet() && genFeature.isListType()) {
    stringBuffer.append(TEXT_147);
    if (OCLGenModelUtil.INSTANCE.hasAPITags(genFeature)) {
    stringBuffer.append(TEXT_52);
    stringBuffer.append(OCLGenModelUtil.INSTANCE.getAPITags(genFeature, genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_117);
    if (isJDK50) { //Class/getGenFeature.annotations.insert.javajetinc
    }
    if (genFeature.isListType() && genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_79);
    }
    if (isJDK50 && OCLGenModelUtil.INSTANCE.hasAPIDeprecatedTag(genFeature)) {
    stringBuffer.append(TEXT_54);
    }
    if (useInterfaceOverrideAnnotation || classExtendsAllGenFeatures.contains(genFeature)) {
    stringBuffer.append(TEXT_129);
    }
    stringBuffer.append(TEXT_136);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_76);
    stringBuffer.append(genFeature.getGetAccessor());
    if (genClass.hasCollidingGetAccessorOperation(genFeature)) {
    stringBuffer.append(TEXT_148);
    }
    stringBuffer.append(TEXT_137);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_124);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_76);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_149);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_150);
    stringBuffer.append(OCLGenModelUtil.getQualifiedFeatureValue(genClass, genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_151);
    }
    stringBuffer.append(TEXT_141);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_152);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_153);
    stringBuffer.append(OCLGenModelUtil.getQualifiedFeatureValue(genClass, genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_154);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_155);
    stringBuffer.append(OCLGenModelUtil.getListConstructor(genClass, genFeature));
    stringBuffer.append(TEXT_151);
    } else {
    stringBuffer.append(TEXT_156);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_155);
    stringBuffer.append(OCLGenModelUtil.getListConstructor(genClass, genFeature));
    stringBuffer.append(TEXT_6);
    }
    stringBuffer.append(TEXT_157);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes() ? ".map()" : "");
    stringBuffer.append(TEXT_131);
    }
    if (!genModel.isReflectiveDelegation() && genFeature.isBasicSet()) {
    stringBuffer.append(TEXT_158);
    if (OCLGenModelUtil.INSTANCE.hasAPITags(genFeature)) {
    stringBuffer.append(TEXT_52);
    stringBuffer.append(OCLGenModelUtil.INSTANCE.getAPITags(genFeature, genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_117);
    if (isJDK50) { //Class/basicSetGenFeature.annotations.insert.javajetinc
    }
    if (isJDK50 && OCLGenModelUtil.INSTANCE.hasAPIDeprecatedTag(genFeature)) {
    stringBuffer.append(TEXT_54);
    }
    if (classExtendsAllGenFeatures.contains(genFeature)) {
    stringBuffer.append(TEXT_129);
    }
    stringBuffer.append(TEXT_136);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_159);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_98);
    stringBuffer.append(genFeature.getImportedInternalType(genClass));
    stringBuffer.append(TEXT_160);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_154);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_161);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_162);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_163);
    }
    if (genFeature.isSet() && !(!genModel.isReflectiveDelegation() && genFeature.isBasicSet())) {
    stringBuffer.append(TEXT_158);
    if (OCLGenModelUtil.INSTANCE.hasAPITags(genFeature)) {
    stringBuffer.append(TEXT_52);
    stringBuffer.append(OCLGenModelUtil.INSTANCE.getAPITags(genFeature, genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_117);
    if (isJDK50) { //Class/setGenFeature.annotations.insert.javajetinc
    }
    { GenOperation setAccessorOperation = genClass.getSetAccessorOperation(genFeature);
    if (isJDK50 && OCLGenModelUtil.INSTANCE.hasAPIDeprecatedTag(genFeature)) {
    stringBuffer.append(TEXT_54);
    }
    if (classExtendsAllGenFeatures.contains(genFeature)) {
    stringBuffer.append(TEXT_129);
    }
    stringBuffer.append(TEXT_164);
    stringBuffer.append(genFeature.getAccessorName());
    if (genClass.hasCollidingSetAccessorOperation(genFeature)) {
    stringBuffer.append(TEXT_148);
    }
    stringBuffer.append(TEXT_98);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_76);
    stringBuffer.append(setAccessorOperation == null ? "new" + genFeature.getCapName() : setAccessorOperation.getGenParameters().get(0).getName());
    stringBuffer.append(TEXT_165);
    stringBuffer.append(genFeature.getAccessorName());
    if (genClass.hasCollidingSetAccessorOperation(genFeature)) {
    stringBuffer.append(TEXT_148);
    }
    stringBuffer.append(TEXT_98);
    stringBuffer.append(setAccessorOperation == null ? "new" + genFeature.getCapName() : setAccessorOperation.getGenParameters().get(0).getName());
    stringBuffer.append(TEXT_166);
    }
    }
    //Class/genFeatureReified.override.javajetinc
    }}}.run();}
    new Runnable() { public void run() {
    for (GenFeature genFeature : (isImplementation ? genClass.getImplementedGenFeatures() : genClass.getDeclaredGenFeatures())) {
    if (genModel.isArrayAccessors() && genFeature.isListType() && !genFeature.isFeatureMapType() && !genFeature.isMapType()) { String arrayElementType = genFeature.getArrayItemType(genClass);
    stringBuffer.append(TEXT_116);
    if (OCLGenModelUtil.INSTANCE.hasAPITags(genFeature)) {
    stringBuffer.append(TEXT_52);
    stringBuffer.append(OCLGenModelUtil.INSTANCE.getAPITags(genFeature, genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_117);
    if (!isImplementation) {
    stringBuffer.append(TEXT_55);
    stringBuffer.append(arrayElementType);
    stringBuffer.append(TEXT_64);
    stringBuffer.append(genFeature.getGetArrayAccessor());
    stringBuffer.append(TEXT_167);
    } else {
    if (genModel.useGenerics() && CodeGenUtil.isUncheckedCast(arrayElementType)) {
    stringBuffer.append(TEXT_79);
    }
    if (isJDK50 && OCLGenModelUtil.INSTANCE.hasAPIDeprecatedTag(genFeature)) {
    stringBuffer.append(TEXT_54);
    }
    if (useInterfaceOverrideAnnotation) {
    stringBuffer.append(TEXT_129);
    }
    stringBuffer.append(TEXT_136);
    stringBuffer.append(arrayElementType);
    stringBuffer.append(TEXT_64);
    stringBuffer.append(genFeature.getGetArrayAccessor());
    stringBuffer.append(TEXT_137);
    if (genFeature.isVolatile()) {
    stringBuffer.append(TEXT_124);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(genFeature.getListTemplateArguments(genClass));
    stringBuffer.append(TEXT_138);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(genFeature.getListTemplateArguments(genClass));
    stringBuffer.append(TEXT_91);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_139);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_140);
    } else {
    stringBuffer.append(TEXT_141);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_142);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_143);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_144);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(genFeature.getListTemplateArguments(genClass));
    stringBuffer.append(TEXT_138);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(genFeature.getListTemplateArguments(genClass));
    stringBuffer.append(TEXT_91);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_6);
    }
    stringBuffer.append(TEXT_145);
    stringBuffer.append(arrayElementType);
    stringBuffer.append(TEXT_146);
    }
    stringBuffer.append(TEXT_116);
    if (OCLGenModelUtil.INSTANCE.hasAPITags(genFeature)) {
    stringBuffer.append(TEXT_52);
    stringBuffer.append(OCLGenModelUtil.INSTANCE.getAPITags(genFeature, genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_117);
    if (isJDK50 && OCLGenModelUtil.INSTANCE.hasAPIDeprecatedTag(genFeature)) {
    stringBuffer.append(TEXT_54);
    }
    if (!isImplementation) {
    stringBuffer.append(TEXT_55);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_168);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_169);
    } else {
    if (useInterfaceOverrideAnnotation) {
    stringBuffer.append(TEXT_129);
    }
    stringBuffer.append(TEXT_136);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_168);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_170);
    if (!genModel.useGenerics()) {
    stringBuffer.append(TEXT_98);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_91);
    }
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_171);
    }
    stringBuffer.append(TEXT_116);
    if (OCLGenModelUtil.INSTANCE.hasAPITags(genFeature)) {
    stringBuffer.append(TEXT_52);
    stringBuffer.append(OCLGenModelUtil.INSTANCE.getAPITags(genFeature, genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_117);
    if (isJDK50 && OCLGenModelUtil.INSTANCE.hasAPIDeprecatedTag(genFeature)) {
    stringBuffer.append(TEXT_54);
    }
    if (!isImplementation) {
    stringBuffer.append(TEXT_172);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_173);
    } else {
    if (useInterfaceOverrideAnnotation) {
    stringBuffer.append(TEXT_129);
    }
    stringBuffer.append(TEXT_174);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_175);
    if (genFeature.isVolatile()) {
    stringBuffer.append(TEXT_176);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_177);
    } else {
    stringBuffer.append(TEXT_176);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_178);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_179);
    }
    stringBuffer.append(TEXT_180);
    }
    stringBuffer.append(TEXT_116);
    if (OCLGenModelUtil.INSTANCE.hasAPITags(genFeature)) {
    stringBuffer.append(TEXT_52);
    stringBuffer.append(OCLGenModelUtil.INSTANCE.getAPITags(genFeature, genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_117);
    if (isJDK50 && OCLGenModelUtil.INSTANCE.hasAPIDeprecatedTag(genFeature)) {
    stringBuffer.append(TEXT_54);
    }
    if (!isImplementation) {
    stringBuffer.append(TEXT_181);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_98);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_182);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_183);
    } else {
    if (useInterfaceOverrideAnnotation) {
    stringBuffer.append(TEXT_129);
    }
    stringBuffer.append(TEXT_164);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_98);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_182);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_184);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(genFeature.getListTemplateArguments(genClass));
    stringBuffer.append(TEXT_91);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_185);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_186);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_166);
    }
    stringBuffer.append(TEXT_116);
    if (OCLGenModelUtil.INSTANCE.hasAPITags(genFeature)) {
    stringBuffer.append(TEXT_52);
    stringBuffer.append(OCLGenModelUtil.INSTANCE.getAPITags(genFeature, genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_117);
    if (isJDK50 && OCLGenModelUtil.INSTANCE.hasAPIDeprecatedTag(genFeature)) {
    stringBuffer.append(TEXT_54);
    }
    if (!isImplementation) {
    stringBuffer.append(TEXT_181);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_187);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_188);
    } else {
    if (useInterfaceOverrideAnnotation) {
    stringBuffer.append(TEXT_129);
    }
    stringBuffer.append(TEXT_164);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_187);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_189);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_190);
    }
    }
    if (genFeature.isGet() && (isImplementation || !genFeature.isSuppressedGetVisibility())) {
    if (isInterface) {
    stringBuffer.append(TEXT_191);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_192);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_193);
    if (genFeature.isListType() && genFeature.getEcoreFeature().getEGenericType().getETypeParameter() == null) {
    if (genFeature.isMapType()) { GenFeature keyFeature = genFeature.getMapEntryTypeGenClass().getMapEntryKeyFeature(); GenFeature valueFeature = genFeature.getMapEntryTypeGenClass().getMapEntryValueFeature(); 
    stringBuffer.append(TEXT_194);
    if (keyFeature.isListType()) {
    stringBuffer.append(TEXT_195);
    stringBuffer.append(keyFeature.getQualifiedListItemType(genClass));
    stringBuffer.append(TEXT_196);
    } else {
    stringBuffer.append(TEXT_197);
    stringBuffer.append(keyFeature.getType(genClass));
    stringBuffer.append(TEXT_196);
    }
    stringBuffer.append(TEXT_198);
    if (valueFeature.isListType()) {
    stringBuffer.append(TEXT_195);
    stringBuffer.append(valueFeature.getQualifiedListItemType(genClass));
    stringBuffer.append(TEXT_196);
    } else {
    stringBuffer.append(TEXT_197);
    stringBuffer.append(valueFeature.getType(genClass));
    stringBuffer.append(TEXT_196);
    }
    stringBuffer.append(TEXT_199);
    } else if (!genFeature.isWrappedFeatureMapType() && !(genModel.isSuppressEMFMetaData() && "org.eclipse.emf.ecore.EObject".equals(genFeature.getQualifiedListItemType(genClass)))) {
String typeName = genFeature.getQualifiedListItemType(genClass); String head = typeName; String tail = ""; int index = typeName.indexOf('<'); if (index == -1) { index = typeName.indexOf('['); } 
if (index != -1) { head = typeName.substring(0, index); tail = "<code>" + CodeGenUtil.xmlEscapeEncode(typeName.substring(index)) + "</code>"; }

    stringBuffer.append(TEXT_200);
    stringBuffer.append(head);
    stringBuffer.append(TEXT_196);
    stringBuffer.append(tail);
    stringBuffer.append(TEXT_193);
    }
    } else if (genFeature.isSetDefaultValue()) {
    stringBuffer.append(TEXT_201);
    stringBuffer.append(genFeature.getDefaultValue());
    stringBuffer.append(TEXT_202);
    }
    if (genFeature.getTypeGenEnum() != null) {
    stringBuffer.append(TEXT_203);
    stringBuffer.append(genFeature.getTypeGenEnum().getQualifiedName());
    stringBuffer.append(TEXT_204);
    }
    if (genFeature.isBidirectional() && !genFeature.getReverse().getGenClass().isMapEntry()) { GenFeature reverseGenFeature = genFeature.getReverse(); 
    if (!reverseGenFeature.isSuppressedGetVisibility()) {
    stringBuffer.append(TEXT_205);
    stringBuffer.append(OCLGenModelUtil.INSTANCE.getRawQualifiedInterfaceName(reverseGenFeature.getGenClass()));
    stringBuffer.append(TEXT_15);
    stringBuffer.append(reverseGenFeature.getGetAccessor());
    stringBuffer.append(TEXT_16);
    stringBuffer.append(reverseGenFeature.getFormattedName());
    stringBuffer.append(TEXT_206);
    }
    }
    stringBuffer.append(TEXT_207);
    if (!genFeature.hasDocumentation()) {
    stringBuffer.append(TEXT_208);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_209);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_210);
    }
    stringBuffer.append(TEXT_211);
    if (genFeature.hasDocumentation()) {
    stringBuffer.append(TEXT_212);
    stringBuffer.append(genFeature.getDocumentation(genModel.getIndentation(stringBuffer)));
    stringBuffer.append(TEXT_213);
    }
    stringBuffer.append(TEXT_214);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_209);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_193);
    if (genFeature.getTypeGenEnum() != null) {
    stringBuffer.append(TEXT_215);
    stringBuffer.append(genFeature.getTypeGenEnum().getQualifiedName());
    }
    if (genFeature.isUnsettable()) {
    if (!genFeature.isSuppressedIsSetVisibility()) {
    stringBuffer.append(TEXT_216);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_21);
    }
    if (genFeature.isChangeable() && !genFeature.isSuppressedUnsetVisibility()) {
    stringBuffer.append(TEXT_217);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_21);
    }
    }
    if (genFeature.isChangeable() && !genFeature.isListType() && !genFeature.isSuppressedSetVisibility()) {
    stringBuffer.append(TEXT_218);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_98);
    stringBuffer.append(genFeature.getRawImportedBoundType());
    stringBuffer.append(TEXT_91);
    }
    if (!genModel.isSuppressEMFMetaData()) {
    stringBuffer.append(TEXT_215);
    stringBuffer.append(genPackage.getQualifiedPackageInterfaceName());
    stringBuffer.append(TEXT_20);
    stringBuffer.append(genFeature.getFeatureAccessorName());
    stringBuffer.append(TEXT_21);
    }
    if (genFeature.isBidirectional() && !genFeature.getReverse().getGenClass().isMapEntry()) { GenFeature reverseGenFeature = genFeature.getReverse(); 
    if (!reverseGenFeature.isSuppressedGetVisibility()) {
    stringBuffer.append(TEXT_215);
    stringBuffer.append(OCLGenModelUtil.INSTANCE.getRawQualifiedInterfaceName(reverseGenFeature.getGenClass()));
    stringBuffer.append(TEXT_15);
    stringBuffer.append(reverseGenFeature.getGetAccessor());
    }
    }
    if (!genModel.isSuppressEMFModelTags()) { boolean first = true; for (StringTokenizer stringTokenizer = new StringTokenizer(genFeature.getModelInfo(), "\n\r"); stringTokenizer.hasMoreTokens(); ) { String modelInfo = stringTokenizer.nextToken(); if (first) { first = false;
    stringBuffer.append(TEXT_219);
    stringBuffer.append(modelInfo);
    } else {
    stringBuffer.append(TEXT_220);
    stringBuffer.append(modelInfo);
    }} if (first) {
    stringBuffer.append(TEXT_221);
    }}
    stringBuffer.append(TEXT_117);
    //Class/getGenFeature.javadoc.override.javajetinc
    } else {
    stringBuffer.append(TEXT_116);
    if (OCLGenModelUtil.INSTANCE.hasAPITags(genFeature)) {
    stringBuffer.append(TEXT_52);
    stringBuffer.append(OCLGenModelUtil.INSTANCE.getAPITags(genFeature, genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_117);
    if (isJDK50) { //Class/getGenFeature.annotations.insert.javajetinc
    }
    }
    if (isJDK50 && OCLGenModelUtil.INSTANCE.hasAPIDeprecatedTag(genFeature)) {
    stringBuffer.append(TEXT_54);
    }
    if (!isImplementation) {
    stringBuffer.append(TEXT_55);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_76);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_167);
    } else {
    if (genModel.useGenerics() && ((genFeature.isContainer() || genFeature.isResolveProxies()) && !genFeature.isListType() && genFeature.isUncheckedCast(genClass) || genFeature.isListType() && !genFeature.isFeatureMapType() && (genModel.isReflectiveDelegation() || genModel.isVirtualDelegation() || (genModel.isDynamicDelegation() && !genFeature.isVolatile())) || genFeature.isListDataType() && genFeature.hasDelegateFeature() || genFeature.isListType() && genFeature.hasSettingDelegate())) {
    stringBuffer.append(TEXT_79);
    }
    if (useInterfaceOverrideAnnotation && !genClass.isMapEntry() && !genClass.hasCollidingGetAccessorOperation(genFeature) && !genFeature.isSuppressedGetVisibility()) {
    stringBuffer.append(TEXT_129);
    }
    stringBuffer.append(TEXT_136);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_76);
    stringBuffer.append(genFeature.getGetAccessor());
    if (genClass.hasCollidingGetAccessorOperation(genFeature)) {
    stringBuffer.append(TEXT_148);
    }
    stringBuffer.append(TEXT_137);
    if (genModel.isDynamicDelegation() && !genFeature.isVolatile()) {
    stringBuffer.append(TEXT_176);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_98);
    }
    stringBuffer.append(TEXT_98);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_222);
    stringBuffer.append(OCLGenModelUtil.getQualifiedFeatureValue(genClass, genFeature));
    if (genClass.hasStaticFeatures()){
    stringBuffer.append(TEXT_223);
    }
    stringBuffer.append(TEXT_154);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_224);
    stringBuffer.append(!genFeature.isEffectiveSuppressEMFTypes());
    stringBuffer.append(TEXT_91);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_225);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_21);
    }
    stringBuffer.append(TEXT_6);
    } else if (genModel.isReflectiveDelegation()) {
    if (genFeature.hasGetterBody()) {
    stringBuffer.append(TEXT_124);
    stringBuffer.append(genFeature.getGetterBody(genModel.getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_176);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_98);
    }
    stringBuffer.append(TEXT_98);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_226);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_227);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_225);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_21);
    }
    stringBuffer.append(TEXT_6);
    }
    } else if (genFeature.hasSettingDelegate()) {
    stringBuffer.append(TEXT_176);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_98);
    }
    stringBuffer.append(TEXT_98);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_91);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_228);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_225);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_21);
    }
    stringBuffer.append(TEXT_6);
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isListType()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_124);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_76);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_149);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_150);
    stringBuffer.append(OCLGenModelUtil.getQualifiedFeatureValue(genClass, genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_151);
    }
    stringBuffer.append(TEXT_141);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_152);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_153);
    stringBuffer.append(OCLGenModelUtil.getQualifiedFeatureValue(genClass, genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_154);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_155);
    stringBuffer.append(OCLGenModelUtil.getListConstructor(genClass, genFeature));
    stringBuffer.append(TEXT_151);
    } else {
    stringBuffer.append(TEXT_156);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_155);
    stringBuffer.append(OCLGenModelUtil.getListConstructor(genClass, genFeature));
    stringBuffer.append(TEXT_6);
    }
    stringBuffer.append(TEXT_157);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes() ? ".map()" : "");
    stringBuffer.append(TEXT_6);
    } else if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_229);
    stringBuffer.append(OCLGenModelUtil.getQualifiedFeatureValue(genClass, genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_230);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_91);
    if (genFeature.isResolveProxies()) {
    stringBuffer.append(TEXT_231);
    } else {
    stringBuffer.append(TEXT_232);
    }
    stringBuffer.append(TEXT_233);
    } else {
    if (genFeature.isResolveProxies()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_124);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_76);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_149);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_150);
    stringBuffer.append(OCLGenModelUtil.getQualifiedFeatureValue(genClass, genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    if (genFeature.hasEDefault()) {
    stringBuffer.append(TEXT_154);
    stringBuffer.append(genFeature.getEDefault());
    }
    stringBuffer.append(TEXT_151);
    }
    stringBuffer.append(TEXT_141);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_234);
    stringBuffer.append(genFeature.getSafeNameAsEObject());
    stringBuffer.append(TEXT_235);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_236);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_57);
    if (!genFeature.getTypeGenPackage().getGenModel().isSuppressInterfaces()) {
    stringBuffer.append(TEXT_98);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_91);
    }
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_237);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_57);
    stringBuffer.append(genFeature.getNonEObjectInternalTypeCast(genClass));
    stringBuffer.append(TEXT_238);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_239);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_240);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_241);
    if (genFeature.isEffectiveContains()) {
    stringBuffer.append(TEXT_242);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_160);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_149);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_91);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_6);
    if (!genFeature.isBidirectional()) {
    stringBuffer.append(TEXT_242);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_243);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_244);
    stringBuffer.append(OCLGenModelUtil.getQualifiedFeatureValue(genClass, genFeature));
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_245);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(genFeature) : "";
    stringBuffer.append(TEXT_242);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_246);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_247);
    stringBuffer.append(OCLGenModelUtil.getQualifiedFeatureValue(targetClass, reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_154);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_248);
    }
    stringBuffer.append(TEXT_249);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_250);
    if (!genFeature.isBidirectional()) {
    stringBuffer.append(TEXT_251);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_252);
    stringBuffer.append(OCLGenModelUtil.getQualifiedFeatureValue(genClass, genFeature));
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_253);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(genFeature) : "";
    stringBuffer.append(TEXT_254);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_255);
    stringBuffer.append(OCLGenModelUtil.getQualifiedFeatureValue(targetClass, reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_154);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_256);
    }
    stringBuffer.append(TEXT_257);
    } else if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_258);
    stringBuffer.append(OCLGenModelUtil.getQualifiedFeatureValue(genClass, genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_154);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_151);
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_259);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_260);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_261);
    stringBuffer.append(OCLGenModelUtil.getQualifiedFeatureValue(genClass, genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_262);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_154);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_263);
    }
    stringBuffer.append(TEXT_264);
    }
    if (!genFeature.isResolveProxies() && genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_265);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_150);
    stringBuffer.append(OCLGenModelUtil.getQualifiedFeatureValue(genClass, genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    if (genFeature.hasEDefault()) {
    stringBuffer.append(TEXT_154);
    stringBuffer.append(genFeature.getEDefault());
    }
    stringBuffer.append(TEXT_151);
    } else if (genClass.isFlag(genFeature)) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_265);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_266);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_267);
    } else {
    stringBuffer.append(TEXT_176);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_268);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_266);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_269);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_270);
    }
    } else {
    stringBuffer.append(TEXT_176);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_6);
    }
    }
    } else {//volatile
    if (genFeature.isResolveProxies() && !genFeature.isListType()) {
    stringBuffer.append(TEXT_124);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_76);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_271);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_272);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_234);
    stringBuffer.append(genFeature.getSafeNameAsEObject());
    stringBuffer.append(TEXT_273);
    stringBuffer.append(genFeature.getNonEObjectInternalTypeCast(genClass));
    stringBuffer.append(TEXT_274);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_91);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_275);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_6);
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (genFeature.isFeatureMapType()) {
    String featureMapEntryTemplateArgument = isJDK50 ? "<" + genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap") + ".Entry>" : "";
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_276);
    stringBuffer.append(genFeature.getImportedEffectiveFeatureMapWrapperClass());
    stringBuffer.append(TEXT_277);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_278);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_279);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_280);
    stringBuffer.append(featureMapEntryTemplateArgument);
    stringBuffer.append(TEXT_281);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_263);
    } else {
    stringBuffer.append(TEXT_265);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_282);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_283);
    stringBuffer.append(featureMapEntryTemplateArgument);
    stringBuffer.append(TEXT_281);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_151);
    }
    } else if (genFeature.isListType()) {
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_284);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_279);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_285);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_151);
    } else {
    stringBuffer.append(TEXT_286);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_287);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_151);
    }
    } else {
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_176);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_98);
    }
    if (genFeature.getTypeGenDataType() == null || !genFeature.getTypeGenDataType().isObjectType()) {
    stringBuffer.append(TEXT_98);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_91);
    }
    stringBuffer.append(TEXT_277);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_279);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_288);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_227);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_225);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_21);
    }
    stringBuffer.append(TEXT_6);
    } else {
    stringBuffer.append(TEXT_176);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_98);
    }
    if (genFeature.getTypeGenDataType() == null || !genFeature.getTypeGenDataType().isObjectType()) {
    stringBuffer.append(TEXT_98);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_91);
    }
    stringBuffer.append(TEXT_289);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_290);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_227);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_225);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_21);
    }
    stringBuffer.append(TEXT_6);
    }
    }
    } else if (genClass.getGetAccessorOperation(genFeature) != null) {
    stringBuffer.append(TEXT_124);
    stringBuffer.append(genClass.getGetAccessorOperation(genFeature).getBody(genModel.getIndentation(stringBuffer)));
    } else if (genFeature.hasGetterBody()) {
    stringBuffer.append(TEXT_124);
    stringBuffer.append(genFeature.getGetterBody(genModel.getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_291);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_292);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_293);
    if (genFeature.isListType()) {
    stringBuffer.append(TEXT_294);
    if (genFeature.isMapType()) {
    stringBuffer.append(TEXT_295);
    } else if (genFeature.isFeatureMapType()) {
    stringBuffer.append(TEXT_296);
    } else {
    stringBuffer.append(TEXT_297);
    }
    stringBuffer.append(TEXT_298);
    }
    stringBuffer.append(TEXT_299);
    //Class/getGenFeature.todo.override.javajetinc
    }
    }
    stringBuffer.append(TEXT_180);
    }
    //Class/getGenFeature.override.javajetinc
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genFeature.isBasicGet()) {
    stringBuffer.append(TEXT_116);
    if (OCLGenModelUtil.INSTANCE.hasAPITags(genFeature)) {
    stringBuffer.append(TEXT_52);
    stringBuffer.append(OCLGenModelUtil.INSTANCE.getAPITags(genFeature, genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_117);
    if (isJDK50) { //Class/basicGetGenFeature.annotations.insert.javajetinc
    }
    if (isJDK50 && OCLGenModelUtil.INSTANCE.hasAPIDeprecatedTag(genFeature)) {
    stringBuffer.append(TEXT_54);
    }
    stringBuffer.append(TEXT_136);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_300);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_137);
    if (genModel.isDynamicDelegation() && !genFeature.isVolatile()) {
    stringBuffer.append(TEXT_265);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_222);
    stringBuffer.append(OCLGenModelUtil.getQualifiedFeatureValue(genClass, genFeature));
    if (genClass.hasStaticFeatures()){
    stringBuffer.append(TEXT_223);
    }
    stringBuffer.append(TEXT_154);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_301);
    stringBuffer.append(!genFeature.isEffectiveSuppressEMFTypes());
    stringBuffer.append(TEXT_151);
    } else if (genFeature.hasSettingDelegate()) {
    stringBuffer.append(TEXT_176);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_98);
    }
    stringBuffer.append(TEXT_98);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_91);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_302);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_225);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_21);
    }
    stringBuffer.append(TEXT_6);
    } else if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_229);
    stringBuffer.append(OCLGenModelUtil.getQualifiedFeatureValue(genClass, genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_230);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_303);
    } else if (!genFeature.isVolatile()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_265);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_150);
    stringBuffer.append(OCLGenModelUtil.getQualifiedFeatureValue(genClass, genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_151);
    } else {
    stringBuffer.append(TEXT_176);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_6);
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_265);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_304);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_279);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_288);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_305);
    } else {
    stringBuffer.append(TEXT_265);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_282);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_290);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_305);
    }
    } else if (genFeature.hasGetterBody()) {
    stringBuffer.append(TEXT_124);
    stringBuffer.append(genFeature.getGetterBody(genModel.getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_291);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_292);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_306);
    //Class/basicGetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_180);
    //Class/basicGetGenFeature.override.javajetinc
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genFeature.isBasicSet()) {
    stringBuffer.append(TEXT_116);
    if (OCLGenModelUtil.INSTANCE.hasAPITags(genFeature)) {
    stringBuffer.append(TEXT_52);
    stringBuffer.append(OCLGenModelUtil.INSTANCE.getAPITags(genFeature, genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_117);
    if (isJDK50) { //Class/basicSetGenFeature.annotations.insert.javajetinc
    }
    if (isJDK50 && OCLGenModelUtil.INSTANCE.hasAPIDeprecatedTag(genFeature)) {
    stringBuffer.append(TEXT_54);
    }
    stringBuffer.append(TEXT_136);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_159);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_98);
    stringBuffer.append(genFeature.getImportedInternalType(genClass));
    stringBuffer.append(TEXT_160);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_154);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_307);
    if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_308);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_309);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_154);
    stringBuffer.append(OCLGenModelUtil.getQualifiedFeatureValue(genClass, genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_310);
    stringBuffer.append(TEXT_311);
    } else if (genModel.isDynamicDelegation()) {
    stringBuffer.append(TEXT_312);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_309);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_154);
    stringBuffer.append(OCLGenModelUtil.getQualifiedFeatureValue(genClass, genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_310);
    stringBuffer.append(TEXT_311);
    } else if (!genFeature.isVolatile()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_313);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_314);
    stringBuffer.append(OCLGenModelUtil.getQualifiedFeatureValue(genClass, genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_315);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_151);
    } else {
    stringBuffer.append(TEXT_124);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_236);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_57);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_316);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_317);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_6);
    }
    if (genFeature.isUnsettable()) {
    if (genModel.isVirtualDelegation()) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_318);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_319);
    }
    } else if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_320);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_321);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_266);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_322);
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_124);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_125);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_323);
    }
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_320);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_324);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_325);
    }
    stringBuffer.append(TEXT_124);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_326);
    }
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_327);
    if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_156);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_328);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_260);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_329);
    stringBuffer.append(OCLGenModelUtil.getQualifiedFeatureValue(genClass, genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_154);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_330);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(TEXT_331);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_315);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_154);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_332);
    } else {
    stringBuffer.append(TEXT_333);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_334);
    }
    stringBuffer.append(TEXT_151);
    } else {
    stringBuffer.append(TEXT_156);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_328);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_260);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_329);
    stringBuffer.append(OCLGenModelUtil.getQualifiedFeatureValue(genClass, genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_154);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_331);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_335);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(TEXT_331);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_315);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_151);
    }
    stringBuffer.append(TEXT_336);
    }
    stringBuffer.append(TEXT_311);
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_284);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_278);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_279);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_337);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_315);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_310);
    } else {
    stringBuffer.append(TEXT_284);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_338);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_339);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_315);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_310);
    }
    } else {
    stringBuffer.append(TEXT_340);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_292);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_341);
    //Class/basicSetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_180);
    //Class/basicSetGenFeature.override.javajetinc
    }
    if (genFeature.isSet() && (isImplementation || !genFeature.isSuppressedSetVisibility())) {
    if (isInterface) { 
    stringBuffer.append(TEXT_342);
    stringBuffer.append(OCLGenModelUtil.INSTANCE.getRawQualifiedInterfaceName(genClass));
    stringBuffer.append(TEXT_15);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_16);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_68);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_193);
    stringBuffer.append(TEXT_343);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_209);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_193);
    if (genFeature.isEnumType()) {
    stringBuffer.append(TEXT_215);
    stringBuffer.append(genFeature.getTypeGenEnum().getQualifiedName());
    }
    if (genFeature.isUnsettable()) {
    if (!genFeature.isSuppressedIsSetVisibility()) {
    stringBuffer.append(TEXT_216);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_21);
    }
    if (!genFeature.isSuppressedUnsetVisibility()) {
    stringBuffer.append(TEXT_217);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_21);
    }
    }
    stringBuffer.append(TEXT_344);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_21);
    if (OCLGenModelUtil.INSTANCE.hasAPITags(genFeature)) {
    stringBuffer.append(TEXT_52);
    stringBuffer.append(OCLGenModelUtil.INSTANCE.getAPITags(genFeature, genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_117);
    //Class/setGenFeature.javadoc.override.javajetinc
    } else {
    stringBuffer.append(TEXT_116);
    if (OCLGenModelUtil.INSTANCE.hasAPITags(genFeature)) {
    stringBuffer.append(TEXT_52);
    stringBuffer.append(OCLGenModelUtil.INSTANCE.getAPITags(genFeature, genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_117);
    if (isJDK50) { //Class/setGenFeature.annotations.insert.javajetinc
    }
    }
    if (isJDK50 && OCLGenModelUtil.INSTANCE.hasAPIDeprecatedTag(genFeature)) {
    stringBuffer.append(TEXT_54);
    }
    if (!isImplementation) { 
    stringBuffer.append(TEXT_181);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_98);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_345);
    } else { GenOperation setAccessorOperation = genClass.getSetAccessorOperation(genFeature);
    if (useInterfaceOverrideAnnotation && !genClass.isMapEntry() && !genClass.hasCollidingSetAccessorOperation(genFeature) && !genFeature.isSuppressedSetVisibility()) {
    stringBuffer.append(TEXT_129);
    }
    stringBuffer.append(TEXT_164);
    stringBuffer.append(genFeature.getAccessorName());
    if (genClass.hasCollidingSetAccessorOperation(genFeature)) {
    stringBuffer.append(TEXT_148);
    }
    stringBuffer.append(TEXT_98);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_76);
    stringBuffer.append(setAccessorOperation == null ? "new" + genFeature.getCapName() : setAccessorOperation.getGenParameters().get(0).getName());
    stringBuffer.append(TEXT_346);
    if (genModel.isDynamicDelegation() && !genFeature.isVolatile()) {
    stringBuffer.append(TEXT_347);
    stringBuffer.append(OCLGenModelUtil.getQualifiedFeatureValue(genClass, genFeature));
    if (genClass.hasStaticFeatures()){
    stringBuffer.append(TEXT_223);
    }
    stringBuffer.append(TEXT_154);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_154);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_348);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_98);
    }
    stringBuffer.append(TEXT_349);
    stringBuffer.append(genFeature.getCapName());
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_91);
    }
    stringBuffer.append(TEXT_151);
    } else if (genModel.isReflectiveDelegation()) {
    stringBuffer.append(TEXT_350);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_154);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_348);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_98);
    }
    stringBuffer.append(TEXT_349);
    stringBuffer.append(genFeature.getCapName());
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_91);
    }
    stringBuffer.append(TEXT_151);
    } else if (genFeature.hasSettingDelegate()) {
    stringBuffer.append(TEXT_124);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_351);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_348);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_98);
    }
    stringBuffer.append(TEXT_349);
    stringBuffer.append(genFeature.getCapName());
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_91);
    }
    stringBuffer.append(TEXT_151);
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isContainer()) { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(genFeature) : "";
    stringBuffer.append(TEXT_352);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_353);
    stringBuffer.append(OCLGenModelUtil.getQualifiedFeatureValue(genClass, genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_354);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_355);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.EcoreUtil"));
    stringBuffer.append(TEXT_356);
    stringBuffer.append(genFeature.getEObjectCast());
    stringBuffer.append(TEXT_349);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_357);
    stringBuffer.append(genModel.getImportedName("java.lang.IllegalArgumentException"));
    stringBuffer.append(TEXT_358);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_156);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_359);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_360);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_309);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_361);
    stringBuffer.append(OCLGenModelUtil.getQualifiedFeatureValue(targetClass, reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_154);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_362);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_98);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_349);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_363);
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_364);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_260);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_329);
    stringBuffer.append(OCLGenModelUtil.getQualifiedFeatureValue(genClass, genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_315);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_315);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_263);
    }
    } else if (genFeature.isBidirectional() || genFeature.isEffectiveContains()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_124);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_76);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_149);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_150);
    stringBuffer.append(OCLGenModelUtil.getQualifiedFeatureValue(genClass, genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_151);
    }
    stringBuffer.append(TEXT_352);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_365);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_366);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_367);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_368);
    if (!genFeature.isBidirectional()) {
    stringBuffer.append(TEXT_369);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_91);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_370);
    stringBuffer.append(OCLGenModelUtil.getQualifiedFeatureValue(genClass, genFeature));
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_371);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_360);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_309);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_372);
    stringBuffer.append(OCLGenModelUtil.getQualifiedFeatureValue(genClass, genFeature));
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_253);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(genFeature) : "";
    stringBuffer.append(TEXT_369);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_91);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_373);
    stringBuffer.append(OCLGenModelUtil.getQualifiedFeatureValue(reverseFeature.getGenClass(), reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_154);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_374);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_360);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_309);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_361);
    stringBuffer.append(OCLGenModelUtil.getQualifiedFeatureValue(targetClass, reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_154);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_256);
    }
    stringBuffer.append(TEXT_375);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_98);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_349);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_363);
    if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_376);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_377);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_378);
    stringBuffer.append(OCLGenModelUtil.getQualifiedFeatureValue(genClass, genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_151);
    } else if (genClass.isESetFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_377);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_321);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_266);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_322);
    }
    stringBuffer.append(TEXT_156);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_125);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_323);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_377);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_324);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_325);
    }
    stringBuffer.append(TEXT_156);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_326);
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_379);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_260);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_329);
    stringBuffer.append(OCLGenModelUtil.getQualifiedFeatureValue(genClass, genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_315);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_315);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_380);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_381);
    }
    stringBuffer.append(TEXT_382);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_364);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_260);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_329);
    stringBuffer.append(OCLGenModelUtil.getQualifiedFeatureValue(genClass, genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_315);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_315);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_263);
    }
    }
    } else {
    if (genClass.isFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_124);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_236);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_149);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_266);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_267);
    } else {
    stringBuffer.append(TEXT_124);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_236);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_57);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_268);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_266);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_269);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_270);
    }
    }
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_352);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_383);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_125);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_384);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_385);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_386);
    } else {
    stringBuffer.append(TEXT_352);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_387);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_57);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_388);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_57);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_389);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_390);
    if (isJDK50) {
    stringBuffer.append(TEXT_349);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_89);
    } else {
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_391);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_91);
    }
    stringBuffer.append(TEXT_92);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_392);
    }
    } else {
    if (!genModel.isVirtualDelegation() || genFeature.isPrimitiveType()) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_124);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_236);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_57);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_6);
    }
    }
    if (genFeature.isEnumType()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_124);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_76);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_317);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_393);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_394);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_6);
    } else {
    stringBuffer.append(TEXT_124);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_317);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_393);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_394);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_6);
    }
    } else {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_124);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_76);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_57);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_349);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_6);
    } else {
    stringBuffer.append(TEXT_124);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_57);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_349);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_6);
    }
    }
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_313);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_314);
    stringBuffer.append(OCLGenModelUtil.getQualifiedFeatureValue(genClass, genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_154);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_151);
    }
    }
    if (genFeature.isUnsettable()) {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_318);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_319);
    } else if (genClass.isESetFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_320);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_321);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_266);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_322);
    }
    stringBuffer.append(TEXT_124);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_125);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_323);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_320);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_324);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_325);
    }
    stringBuffer.append(TEXT_124);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_326);
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_395);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_260);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_329);
    stringBuffer.append(OCLGenModelUtil.getQualifiedFeatureValue(genClass, genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_154);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_396);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_397);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(TEXT_331);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_154);
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_349);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(genFeature.getSafeName());
    }
    stringBuffer.append(TEXT_154);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_332);
    } else {
    stringBuffer.append(TEXT_333);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_334);
    }
    stringBuffer.append(TEXT_263);
    }
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_395);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_260);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_329);
    stringBuffer.append(OCLGenModelUtil.getQualifiedFeatureValue(genClass, genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_154);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_331);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_398);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_397);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(TEXT_331);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_154);
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_349);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(genFeature.getSafeName());
    }
    stringBuffer.append(TEXT_263);
    }
    }
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_399);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_278);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_279);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_400);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_154);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_348);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_98);
    }
    stringBuffer.append(TEXT_349);
    stringBuffer.append(genFeature.getCapName());
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_91);
    }
    stringBuffer.append(TEXT_151);
    } else {
    stringBuffer.append(TEXT_399);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_338);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_401);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_154);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_348);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_98);
    }
    stringBuffer.append(TEXT_349);
    stringBuffer.append(genFeature.getCapName());
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_91);
    }
    stringBuffer.append(TEXT_151);
    }
    } else if (setAccessorOperation != null) {
    stringBuffer.append(TEXT_124);
    stringBuffer.append(setAccessorOperation.getBody(genModel.getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_402);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_292);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_403);
    //Class/setGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_180);
    }
    //Class/setGenFeature.override.javajetinc
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genFeature.isBasicUnset()) {
    stringBuffer.append(TEXT_116);
    if (OCLGenModelUtil.INSTANCE.hasAPITags(genFeature)) {
    stringBuffer.append(TEXT_52);
    stringBuffer.append(OCLGenModelUtil.INSTANCE.getAPITags(genFeature, genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_117);
    if (isJDK50) { //Class/basicUnsetGenFeature.annotations.insert.javajetinc
    }
    if (isJDK50 && OCLGenModelUtil.INSTANCE.hasAPIDeprecatedTag(genFeature)) {
    stringBuffer.append(TEXT_54);
    }
    stringBuffer.append(TEXT_136);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_404);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_98);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_307);
    if (genModel.isDynamicDelegation()) {
    stringBuffer.append(TEXT_405);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_91);
    if (genFeature.isResolveProxies()) {
    stringBuffer.append(TEXT_406);
    stringBuffer.append(genFeature.getAccessorName());
    } else {
    stringBuffer.append(genFeature.getGetAccessor());
    }
    stringBuffer.append(TEXT_407);
    stringBuffer.append(OCLGenModelUtil.getQualifiedFeatureValue(genClass, genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_310);
    } else if (!genFeature.isVolatile()) {
    if (genModel.isVirtualDelegation()) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_408);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_57);
    }
    stringBuffer.append(TEXT_409);
    stringBuffer.append(OCLGenModelUtil.getQualifiedFeatureValue(genClass, genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_151);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_124);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_236);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_57);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_6);
    }
    stringBuffer.append(TEXT_124);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_410);
    }
    if (genModel.isVirtualDelegation()) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_318);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_411);
    }
    } else if (genClass.isESetFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_320);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_321);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_266);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_322);
    }
    stringBuffer.append(TEXT_124);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_385);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_323);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_320);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_324);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_325);
    }
    stringBuffer.append(TEXT_124);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_412);
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_413);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_328);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_260);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_414);
    stringBuffer.append(OCLGenModelUtil.getQualifiedFeatureValue(genClass, genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_154);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_415);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_416);
    } else {
    stringBuffer.append(TEXT_331);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_417);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_332);
    } else {
    stringBuffer.append(TEXT_331);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_334);
    }
    stringBuffer.append(TEXT_418);
    }
    } else {
    stringBuffer.append(TEXT_419);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_292);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_341);
    //Class/basicUnsetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_180);
    //Class.basicUnsetGenFeature.override.javajetinc
    }
    if (genFeature.isUnset() && (isImplementation || !genFeature.isSuppressedUnsetVisibility())) {
    if (isInterface) {
    stringBuffer.append(TEXT_420);
    stringBuffer.append(OCLGenModelUtil.INSTANCE.getRawQualifiedInterfaceName(genClass));
    stringBuffer.append(TEXT_15);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_16);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_68);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_193);
    stringBuffer.append(TEXT_421);
    if (!genFeature.isSuppressedIsSetVisibility()) {
    stringBuffer.append(TEXT_216);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_21);
    }
    stringBuffer.append(TEXT_344);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_21);
    if (!genFeature.isListType() && !genFeature.isSuppressedSetVisibility()) {
    stringBuffer.append(TEXT_218);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_98);
    stringBuffer.append(genFeature.getRawImportedBoundType());
    stringBuffer.append(TEXT_91);
    }
    if (OCLGenModelUtil.INSTANCE.hasAPITags(genFeature)) {
    stringBuffer.append(TEXT_52);
    stringBuffer.append(OCLGenModelUtil.INSTANCE.getAPITags(genFeature, genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_117);
    //Class/unsetGenFeature.javadoc.override.javajetinc
    } else {
    stringBuffer.append(TEXT_116);
    if (OCLGenModelUtil.INSTANCE.hasAPITags(genFeature)) {
    stringBuffer.append(TEXT_52);
    stringBuffer.append(OCLGenModelUtil.INSTANCE.getAPITags(genFeature, genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_117);
    if (isJDK50) { //Class/unsetGenFeature.annotations.insert.javajetinc
    }
    }
    if (isJDK50 && OCLGenModelUtil.INSTANCE.hasAPIDeprecatedTag(genFeature)) {
    stringBuffer.append(TEXT_54);
    }
    if (!isImplementation) {
    stringBuffer.append(TEXT_422);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_167);
    } else {
    if (useInterfaceOverrideAnnotation && !genClass.isMapEntry() && !genClass.hasCollidingUnsetAccessorOperation(genFeature) && !genFeature.isSuppressedUnsetVisibility()) {
    stringBuffer.append(TEXT_129);
    }
    stringBuffer.append(TEXT_423);
    stringBuffer.append(genFeature.getAccessorName());
    if (genClass.hasCollidingUnsetAccessorOperation(genFeature)) {
    stringBuffer.append(TEXT_148);
    }
    stringBuffer.append(TEXT_137);
    if (genModel.isDynamicDelegation() && !genFeature.isVolatile()) {
    stringBuffer.append(TEXT_424);
    stringBuffer.append(OCLGenModelUtil.getQualifiedFeatureValue(genClass, genFeature));
    if (genClass.hasStaticFeatures()){
    stringBuffer.append(TEXT_223);
    }
    stringBuffer.append(TEXT_154);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_151);
    } else if (genModel.isReflectiveDelegation()) {
    stringBuffer.append(TEXT_425);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_151);
    } else if (genFeature.hasSettingDelegate()) {
    stringBuffer.append(TEXT_124);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_426);
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isListType()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_124);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_76);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_149);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_150);
    stringBuffer.append(OCLGenModelUtil.getQualifiedFeatureValue(genClass, genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_151);
    }
    stringBuffer.append(TEXT_141);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_427);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(TEXT_428);
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_91);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_429);
    } else if (genFeature.isBidirectional() || genFeature.isEffectiveContains()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_124);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_76);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_149);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_150);
    stringBuffer.append(OCLGenModelUtil.getQualifiedFeatureValue(genClass, genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_151);
    }
    stringBuffer.append(TEXT_141);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_430);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_431);
    if (!genFeature.isBidirectional()) {
    stringBuffer.append(TEXT_432);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_91);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_370);
    stringBuffer.append(OCLGenModelUtil.getQualifiedFeatureValue(genClass, genFeature));
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_253);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(genFeature) : "";
    stringBuffer.append(TEXT_432);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_91);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_373);
    stringBuffer.append(OCLGenModelUtil.getQualifiedFeatureValue(targetClass, reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_154);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_256);
    }
    stringBuffer.append(TEXT_433);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_434);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_377);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_378);
    stringBuffer.append(OCLGenModelUtil.getQualifiedFeatureValue(genClass, genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_151);
    } else if (genClass.isESetFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_377);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_321);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_266);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_322);
    }
    stringBuffer.append(TEXT_156);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_385);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_323);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_377);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_324);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_325);
    }
    stringBuffer.append(TEXT_156);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_412);
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_379);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_260);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_414);
    stringBuffer.append(OCLGenModelUtil.getQualifiedFeatureValue(genClass, genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_435);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_381);
    }
    stringBuffer.append(TEXT_382);
    } else {
    if (genClass.isFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_124);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_236);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_149);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_266);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_267);
    } else {
    stringBuffer.append(TEXT_124);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_236);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_57);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_268);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_266);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_269);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_270);
    }
    }
    } else if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_313);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_436);
    stringBuffer.append(OCLGenModelUtil.getQualifiedFeatureValue(genClass, genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_151);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_124);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_236);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_57);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_6);
    }
    }
    if (!genModel.isSuppressNotification()) {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_318);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_411);
    } else if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_320);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_321);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_266);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_322);
    } else {
    stringBuffer.append(TEXT_320);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_324);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_325);
    }
    }
    if (genFeature.isReferenceType()) {
    stringBuffer.append(TEXT_124);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_410);
    if (!genModel.isVirtualDelegation()) {
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_124);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_385);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_323);
    } else {
    stringBuffer.append(TEXT_124);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_412);
    }
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_395);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_260);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_414);
    stringBuffer.append(OCLGenModelUtil.getQualifiedFeatureValue(genClass, genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_154);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_415);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_416);
    } else {
    stringBuffer.append(TEXT_331);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_417);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_332);
    } else {
    stringBuffer.append(TEXT_331);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_334);
    }
    stringBuffer.append(TEXT_263);
    }
    } else {
    if (genClass.isFlag(genFeature)) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_141);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_383);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_125);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_384);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_385);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_386);
    } else {
    stringBuffer.append(TEXT_124);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_57);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_389);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_390);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_437);
    }
    } else if (!genModel.isVirtualDelegation() || genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_124);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_57);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_6);
    }
    if (!genModel.isVirtualDelegation() || genFeature.isPrimitiveType()) {
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_124);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_385);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_323);
    } else {
    stringBuffer.append(TEXT_124);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_412);
    }
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_395);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_260);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_414);
    stringBuffer.append(OCLGenModelUtil.getQualifiedFeatureValue(genClass, genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_154);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_415);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_438);
    stringBuffer.append(genFeature.getEDefault());
    } else {
    stringBuffer.append(TEXT_331);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_154);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_154);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_332);
    } else {
    stringBuffer.append(TEXT_331);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_334);
    }
    stringBuffer.append(TEXT_263);
    }
    }
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_399);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_278);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_279);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_439);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_151);
    } else {
    stringBuffer.append(TEXT_399);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_338);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_440);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_151);
    }
    } else if (genClass.getUnsetAccessorOperation(genFeature) != null) {
    stringBuffer.append(TEXT_124);
    stringBuffer.append(genClass.getUnsetAccessorOperation(genFeature).getBody(genModel.getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_441);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_292);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_403);
    //Class/unsetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_180);
    }
    //Class/unsetGenFeature.override.javajetinc
    }
    if (genFeature.isIsSet() && (isImplementation || !genFeature.isSuppressedIsSetVisibility())) {
    if (isInterface) {
    stringBuffer.append(TEXT_442);
    stringBuffer.append(OCLGenModelUtil.INSTANCE.getRawQualifiedInterfaceName(genClass));
    stringBuffer.append(TEXT_15);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_16);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_68);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_443);
    stringBuffer.append(TEXT_444);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_209);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_443);
    if (genFeature.isChangeable() && !genFeature.isSuppressedUnsetVisibility()) {
    stringBuffer.append(TEXT_217);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_21);
    }
    stringBuffer.append(TEXT_344);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_21);
    if (!genFeature.isListType() && genFeature.isChangeable() && !genFeature.isSuppressedSetVisibility()) {
    stringBuffer.append(TEXT_218);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_98);
    stringBuffer.append(genFeature.getRawImportedBoundType());
    stringBuffer.append(TEXT_91);
    }
    if (OCLGenModelUtil.INSTANCE.hasAPITags(genFeature)) {
    stringBuffer.append(TEXT_52);
    stringBuffer.append(OCLGenModelUtil.INSTANCE.getAPITags(genFeature, genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_117);
    //Class/isSetGenFeature.javadoc.override.javajetinc
    } else {
    stringBuffer.append(TEXT_116);
    if (OCLGenModelUtil.INSTANCE.hasAPITags(genFeature)) {
    stringBuffer.append(TEXT_52);
    stringBuffer.append(OCLGenModelUtil.INSTANCE.getAPITags(genFeature, genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_117);
    if (isJDK50) { //Class/isSetGenFeature.annotations.insert.javajetinc
    }
    }
    if (isJDK50 && OCLGenModelUtil.INSTANCE.hasAPIDeprecatedTag(genFeature)) {
    stringBuffer.append(TEXT_54);
    }
    if (!isImplementation) {
    stringBuffer.append(TEXT_445);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_167);
    } else {
    if (useInterfaceOverrideAnnotation  && !genClass.isMapEntry() && !genClass.hasCollidingIsSetAccessorOperation(genFeature) && !genFeature.isSuppressedIsSetVisibility()) {
    stringBuffer.append(TEXT_129);
    }
    stringBuffer.append(TEXT_446);
    stringBuffer.append(genFeature.getAccessorName());
    if (genClass.hasCollidingIsSetAccessorOperation(genFeature)) {
    stringBuffer.append(TEXT_148);
    }
    stringBuffer.append(TEXT_137);
    if (genModel.isDynamicDelegation() && !genFeature.isVolatile()) {
    stringBuffer.append(TEXT_447);
    stringBuffer.append(OCLGenModelUtil.getQualifiedFeatureValue(genClass, genFeature));
    if (genClass.hasStaticFeatures()){
    stringBuffer.append(TEXT_223);
    }
    stringBuffer.append(TEXT_154);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_151);
    } else if (genModel.isReflectiveDelegation()) {
    stringBuffer.append(TEXT_448);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_151);
    } else if (genFeature.hasSettingDelegate()) {
    stringBuffer.append(TEXT_176);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_449);
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isListType()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_124);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_76);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_149);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_150);
    stringBuffer.append(OCLGenModelUtil.getQualifiedFeatureValue(genClass, genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_151);
    }
    stringBuffer.append(TEXT_176);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_450);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(TEXT_428);
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_91);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_451);
    } else {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_452);
    stringBuffer.append(OCLGenModelUtil.getQualifiedFeatureValue(genClass, genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_151);
    } else if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_265);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_266);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_322);
    } else {
    stringBuffer.append(TEXT_176);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_325);
    }
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_453);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_278);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_279);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_454);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_151);
    } else {
    stringBuffer.append(TEXT_453);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_338);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_455);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_151);
    }
    } else if (genClass.getIsSetAccessorOperation(genFeature) != null) {
    stringBuffer.append(TEXT_124);
    stringBuffer.append(genClass.getIsSetAccessorOperation(genFeature).getBody(genModel.getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_456);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_292);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_457);
    //Class/isSetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_180);
    }
    //Class/isSetGenFeature.override.javajetinc
    }
    //Class/genFeature.override.javajetinc
    }//for
    }}.run();
    for (GenOperation genOperation : (isImplementation ? genClass.getImplementedGenOperations() : genClass.getDeclaredGenOperations())) {
    if (isImplementation) {
    if (genOperation.isInvariant() && genOperation.hasInvariantExpression()) {
    stringBuffer.append(TEXT_458);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_98);
    stringBuffer.append(genOperation.getParameterTypes(", "));
    stringBuffer.append(TEXT_459);
    stringBuffer.append(genOperation.getFormattedName());
    stringBuffer.append(TEXT_460);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_98);
    stringBuffer.append(genOperation.getParameterTypes(", "));
    stringBuffer.append(TEXT_91);
    if (OCLGenModelUtil.INSTANCE.hasAPITags(genOperation)) {
    stringBuffer.append(TEXT_52);
    stringBuffer.append(OCLGenModelUtil.INSTANCE.getAPITags(genOperation, genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_53);
    if (isJDK50 && OCLGenModelUtil.INSTANCE.hasAPIDeprecatedTag(genOperation)) {
    stringBuffer.append(TEXT_54);
    }
    stringBuffer.append(TEXT_63);
    stringBuffer.append(genModel.getImportedName("java.lang.String"));
    stringBuffer.append(TEXT_76);
    stringBuffer.append(CodeGenUtil.upperName(genClass.getUniqueName(genOperation), genModel.getLocale()));
    stringBuffer.append(TEXT_461);
    stringBuffer.append(genOperation.getInvariantExpression("\t\t"));
    stringBuffer.append(TEXT_40);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_7);
    } else if (genOperation.hasInvocationDelegate()) {
    stringBuffer.append(TEXT_462);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_98);
    stringBuffer.append(genOperation.getParameterTypes(", "));
    stringBuffer.append(TEXT_459);
    stringBuffer.append(genOperation.getFormattedName());
    stringBuffer.append(TEXT_463);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_98);
    stringBuffer.append(genOperation.getParameterTypes(", "));
    stringBuffer.append(TEXT_91);
    if (OCLGenModelUtil.INSTANCE.hasAPITags(genOperation)) {
    stringBuffer.append(TEXT_52);
    stringBuffer.append(OCLGenModelUtil.INSTANCE.getAPITags(genOperation, genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_53);
    if (isJDK50 && OCLGenModelUtil.INSTANCE.hasAPIDeprecatedTag(genOperation)) {
    stringBuffer.append(TEXT_54);
    }
    stringBuffer.append(TEXT_63);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EOperation"));
    stringBuffer.append(TEXT_464);
    stringBuffer.append(CodeGenUtil.upperName(genClass.getUniqueName(genOperation), genModel.getLocale()));
    stringBuffer.append(TEXT_465);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EOperation"));
    stringBuffer.append(TEXT_73);
    stringBuffer.append(genOperation.getQualifiedOperationAccessor());
    stringBuffer.append(TEXT_466);
    }
    }
    if (isInterface) {
    stringBuffer.append(TEXT_467);
    stringBuffer.append(TEXT_421);
    if (genOperation.hasDocumentation() || genOperation.hasParameterDocumentation()) {
    stringBuffer.append(TEXT_468);
    if (genOperation.hasDocumentation()) {
    stringBuffer.append(TEXT_52);
    stringBuffer.append(genOperation.getDocumentation(genModel.getIndentation(stringBuffer)));
    }
    for (GenParameter genParameter : genOperation.getGenParameters()) {
    if (genParameter.hasDocumentation()) { String documentation = genParameter.getDocumentation("");
    if (documentation.contains("\n") || documentation.contains("\r")) {
    stringBuffer.append(TEXT_469);
    stringBuffer.append(genParameter.getName());
    stringBuffer.append(TEXT_470);
    stringBuffer.append(genParameter.getDocumentation(genModel.getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_469);
    stringBuffer.append(genParameter.getName());
    stringBuffer.append(TEXT_76);
    stringBuffer.append(genParameter.getDocumentation(genModel.getIndentation(stringBuffer)));
    }
    }
    }
    stringBuffer.append(TEXT_213);
    }
    if (!genModel.isSuppressEMFModelTags()) { boolean first = true; for (StringTokenizer stringTokenizer = new StringTokenizer(genOperation.getModelInfo(), "\n\r"); stringTokenizer.hasMoreTokens(); ) { String modelInfo = stringTokenizer.nextToken(); if (first) { first = false;
    stringBuffer.append(TEXT_219);
    stringBuffer.append(modelInfo);
    } else {
    stringBuffer.append(TEXT_220);
    stringBuffer.append(modelInfo);
    }} if (first) {
    stringBuffer.append(TEXT_221);
    }}
    stringBuffer.append(TEXT_117);
    //Class/genOperation.javadoc.override.javajetinc
    } else {
    stringBuffer.append(TEXT_116);
    if (OCLGenModelUtil.INSTANCE.hasAPITags(genOperation)) {
    stringBuffer.append(TEXT_52);
    stringBuffer.append(OCLGenModelUtil.INSTANCE.getAPITags(genOperation, genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_117);
    if (isJDK50) { //Class/genOperation.annotations.insert.javajetinc
    }
    }
    if (isJDK50 && OCLGenModelUtil.INSTANCE.hasAPIDeprecatedTag(genOperation)) {
    stringBuffer.append(TEXT_54);
    }
    if (!isImplementation) {
    stringBuffer.append(TEXT_55);
    stringBuffer.append(genOperation.getTypeParameters(genClass));
    stringBuffer.append(genOperation.getImportedType(genClass));
    stringBuffer.append(TEXT_76);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_98);
    stringBuffer.append(genOperation.getParameters(genClass));
    stringBuffer.append(TEXT_91);
    stringBuffer.append(genOperation.getThrows(genClass));
    stringBuffer.append(TEXT_45);
    } else {
    if (genModel.useGenerics() && !genOperation.hasBody() && !genOperation.isInvariant() && genOperation.hasInvocationDelegate() && genOperation.isUncheckedCast(genClass)) {
    stringBuffer.append(TEXT_79);
    }
    if (useInterfaceOverrideAnnotation && !genClass.isMapEntry() && !genOperation.isSuppressedVisibility()) {
    stringBuffer.append(TEXT_129);
    }
    stringBuffer.append(TEXT_136);
    stringBuffer.append(genOperation.getTypeParameters(genClass));
    stringBuffer.append(genOperation.getImportedType(genClass));
    stringBuffer.append(TEXT_76);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_98);
    stringBuffer.append(genOperation.getParameters(isImplementation, genClass));
    stringBuffer.append(TEXT_91);
    stringBuffer.append(genOperation.getThrows(genClass));
    stringBuffer.append(TEXT_471);
    if (genOperation.hasBody()) {
    stringBuffer.append(TEXT_124);
    stringBuffer.append(genOperation.getBody(genModel.getIndentation(stringBuffer)));
    } else if (genOperation.isInvariant()) {GenClass opClass = genOperation.getGenClass(); String diagnostics = genOperation.getGenParameters().get(0).getName(); String context = genOperation.getGenParameters().get(1).getName();
    if (genOperation.hasInvariantExpression()) {
    stringBuffer.append(TEXT_472);
    stringBuffer.append(opClass.getGenPackage().getImportedValidatorClassName());
    stringBuffer.append(TEXT_473);
    stringBuffer.append(genClass.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_474);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_475);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_476);
    stringBuffer.append(genOperation.getValidationDelegate());
    stringBuffer.append(TEXT_477);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_478);
    stringBuffer.append(genOperation.getQualifiedOperationAccessor());
    stringBuffer.append(TEXT_475);
    stringBuffer.append(CodeGenUtil.upperName(genClass.getUniqueName(genOperation), genModel.getLocale()));
    stringBuffer.append(TEXT_479);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.Diagnostic"));
    stringBuffer.append(TEXT_480);
    stringBuffer.append(opClass.getGenPackage().getImportedValidatorClassName());
    stringBuffer.append(TEXT_481);
    stringBuffer.append(opClass.getGenPackage().getImportedValidatorClassName());
    stringBuffer.append(TEXT_193);
    stringBuffer.append(opClass.getOperationID(genOperation));
    stringBuffer.append(TEXT_151);
    } else {
    stringBuffer.append(TEXT_482);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_483);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_484);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicDiagnostic"));
    stringBuffer.append(TEXT_485);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.Diagnostic"));
    stringBuffer.append(TEXT_486);
    stringBuffer.append(opClass.getGenPackage().getImportedValidatorClassName());
    stringBuffer.append(TEXT_487);
    stringBuffer.append(opClass.getGenPackage().getImportedValidatorClassName());
    stringBuffer.append(TEXT_193);
    stringBuffer.append(opClass.getOperationID(genOperation));
    stringBuffer.append(TEXT_488);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.plugin.EcorePlugin"));
    stringBuffer.append(TEXT_489);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_490);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.EObjectValidator"));
    stringBuffer.append(TEXT_491);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_492);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    stringBuffer.append(TEXT_493);
    }
    } else if (genOperation.hasInvocationDelegate()) { int size = genOperation.getGenParameters().size();
    stringBuffer.append(TEXT_494);
    if (genOperation.isVoid()) {
    stringBuffer.append(TEXT_156);
    stringBuffer.append(CodeGenUtil.upperName(genClass.getUniqueName(genOperation), genModel.getLocale()));
    stringBuffer.append(TEXT_495);
    if (size > 0) {
    stringBuffer.append(TEXT_348);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(TEXT_496);
    stringBuffer.append(size);
    stringBuffer.append(TEXT_154);
    stringBuffer.append(genOperation.getParametersArray(genClass));
    stringBuffer.append(TEXT_91);
    } else {
    stringBuffer.append(TEXT_497);
    }
    stringBuffer.append(TEXT_151);
    } else {
    stringBuffer.append(TEXT_498);
    if (!isJDK50 && genOperation.isPrimitiveType()) {
    stringBuffer.append(TEXT_98);
    }
    stringBuffer.append(TEXT_98);
    stringBuffer.append(genOperation.getObjectType(genClass));
    stringBuffer.append(TEXT_91);
    stringBuffer.append(CodeGenUtil.upperName(genClass.getUniqueName(genOperation), genModel.getLocale()));
    stringBuffer.append(TEXT_495);
    if (size > 0) {
    stringBuffer.append(TEXT_348);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(TEXT_496);
    stringBuffer.append(size);
    stringBuffer.append(TEXT_154);
    stringBuffer.append(genOperation.getParametersArray(genClass));
    stringBuffer.append(TEXT_91);
    } else {
    stringBuffer.append(TEXT_497);
    }
    stringBuffer.append(TEXT_91);
    if (!isJDK50 && genOperation.isPrimitiveType()) {
    stringBuffer.append(TEXT_225);
    stringBuffer.append(genOperation.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_21);
    }
    stringBuffer.append(TEXT_6);
    }
    stringBuffer.append(TEXT_499);
    stringBuffer.append(genModel.getImportedName(isGWT ? "org.eclipse.emf.common.util.InvocationTargetException" : "java.lang.reflect.InvocationTargetException"));
    stringBuffer.append(TEXT_500);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.WrappedException"));
    stringBuffer.append(TEXT_501);
    } else {
    stringBuffer.append(TEXT_502);
    //Class/implementedGenOperation.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_180);
    }
    //Class/implementedGenOperation.override.javajetinc
    }//for
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEInverseAddGenFeatures())) {
    stringBuffer.append(TEXT_133);
    if (genModel.useGenerics()) {
    for (GenFeature genFeature : genClass.getEInverseAddGenFeatures()) {
    if (genFeature.isUncheckedCast(genClass)) {
    stringBuffer.append(TEXT_79);
    break; }
    }
    }
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_129);
    }
    stringBuffer.append(TEXT_136);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_503);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_504);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_505);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_506);
    for (GenFeature genFeature : genClass.getEInverseAddGenFeatures()) {
    stringBuffer.append(TEXT_507);
    stringBuffer.append(OCLGenModelUtil.getQualifiedFeatureValue(genClass, genFeature));
    stringBuffer.append(TEXT_508);
    if (genFeature.isListType()) { String cast = "("  + genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList") + (!genModel.useGenerics() ? ")" : "<" + genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject") + ">)(" + genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList") + "<?>)");
    if (genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_509);
    stringBuffer.append(cast);
    stringBuffer.append(TEXT_98);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_510);
    stringBuffer.append(genFeature.getImportedMapTemplateArguments(genClass));
    stringBuffer.append(TEXT_91);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_511);
    } else {
    stringBuffer.append(TEXT_512);
    stringBuffer.append(cast);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_513);
    }
    } else if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_514);
    if (genFeature.isBasicSet()) {
    stringBuffer.append(TEXT_515);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_277);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_516);
    } else {
    stringBuffer.append(TEXT_517);
    stringBuffer.append(OCLGenModelUtil.getQualifiedFeatureValue(genClass, genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_310);
    }
    } else {
    if (genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_242);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_76);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_149);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_150);
    stringBuffer.append(OCLGenModelUtil.getQualifiedFeatureValue(genClass, genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_151);
    } else if (genFeature.isVolatile() || genClass.getImplementingGenModel(genFeature).isDynamicDelegation()) {
    stringBuffer.append(TEXT_242);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_76);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_57);
    if (genFeature.isResolveProxies()) {
    stringBuffer.append(TEXT_406);
    stringBuffer.append(genFeature.getAccessorName());
    } else {
    stringBuffer.append(genFeature.getGetAccessor());
    }
    stringBuffer.append(TEXT_233);
    }
    stringBuffer.append(TEXT_518);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_368);
    if (genFeature.isEffectiveContains()) {
    stringBuffer.append(TEXT_519);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_91);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_370);
    stringBuffer.append(OCLGenModelUtil.getQualifiedFeatureValue(genClass, genFeature));
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_253);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(genFeature) : "";
    stringBuffer.append(TEXT_519);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_91);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_373);
    stringBuffer.append(OCLGenModelUtil.getQualifiedFeatureValue(targetClass, reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_154);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_256);
    }
    stringBuffer.append(TEXT_515);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_277);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_516);
    }
    }
    stringBuffer.append(TEXT_382);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_520);
    } else {
    stringBuffer.append(TEXT_521);
    }
    stringBuffer.append(TEXT_180);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEInverseRemoveGenFeatures())) {
    stringBuffer.append(TEXT_133);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_129);
    }
    stringBuffer.append(TEXT_136);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_522);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_504);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_505);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_506);
    for (GenFeature genFeature : genClass.getEInverseRemoveGenFeatures()) {
    stringBuffer.append(TEXT_507);
    stringBuffer.append(OCLGenModelUtil.getQualifiedFeatureValue(genClass, genFeature));
    stringBuffer.append(TEXT_508);
    if (genFeature.isListType()) {
    if (genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_509);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_304);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_510);
    stringBuffer.append(genFeature.getImportedMapTemplateArguments(genClass));
    stringBuffer.append(TEXT_91);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_523);
    } else if (genFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_509);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_304);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_524);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_525);
    } else {
    stringBuffer.append(TEXT_509);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_91);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_526);
    }
    } else if (genFeature.isContainer() && !genFeature.isBasicSet()) {
    stringBuffer.append(TEXT_527);
    stringBuffer.append(OCLGenModelUtil.getQualifiedFeatureValue(genClass, genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_310);
    } else if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_528);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_529);
    } else {
    stringBuffer.append(TEXT_515);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_530);
    }
    }
    stringBuffer.append(TEXT_382);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_531);
    } else {
    stringBuffer.append(TEXT_532);
    }
    stringBuffer.append(TEXT_180);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEBasicRemoveFromContainerGenFeatures())) {
    stringBuffer.append(TEXT_133);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_129);
    }
    stringBuffer.append(TEXT_136);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_533);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_534);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_506);
    for (GenFeature genFeature : genClass.getEBasicRemoveFromContainerGenFeatures()) {
    GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(genFeature) : "";
    stringBuffer.append(TEXT_507);
    stringBuffer.append(OCLGenModelUtil.getQualifiedFeatureValue(genClass, genFeature));
    stringBuffer.append(TEXT_535);
    stringBuffer.append(OCLGenModelUtil.getQualifiedFeatureValue(targetClass, reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_154);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_256);
    }
    stringBuffer.append(TEXT_382);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_536);
    } else {
    stringBuffer.append(TEXT_537);
    }
    stringBuffer.append(TEXT_180);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEGetGenFeatures())) {
    stringBuffer.append(TEXT_133);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_129);
    }
    stringBuffer.append(TEXT_538);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_506);
    for (GenFeature genFeature : genClass.getEGetGenFeatures()) {
    stringBuffer.append(TEXT_507);
    stringBuffer.append(OCLGenModelUtil.getQualifiedFeatureValue(genClass, genFeature));
    stringBuffer.append(TEXT_508);
    if (genFeature.isPrimitiveType()) {
    if (isJDK50) {
    stringBuffer.append(TEXT_539);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_233);
    } else if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_539);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_540);
    } else {
    stringBuffer.append(TEXT_541);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_98);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_542);
    }
    } else if (genFeature.isResolveProxies() && !genFeature.isListType()) {
    stringBuffer.append(TEXT_543);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_544);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_233);
    } else if (genFeature.isMapType()) {
    if (genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_545);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_510);
    stringBuffer.append(genFeature.getImportedMapTemplateArguments(genClass));
    stringBuffer.append(TEXT_91);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_546);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_233);
    } else {
    stringBuffer.append(TEXT_547);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_548);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_549);
    }
    } else if (genFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_545);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_524);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_550);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_233);
    } else if (genFeature.isFeatureMapType()) {
    stringBuffer.append(TEXT_547);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_551);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_73);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_552);
    } else {
    stringBuffer.append(TEXT_539);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_233);
    }
    }
    stringBuffer.append(TEXT_382);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_553);
    } else {
    stringBuffer.append(TEXT_554);
    }
    stringBuffer.append(TEXT_180);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getESetGenFeatures())) {
    stringBuffer.append(TEXT_133);
    if (genModel.useGenerics()) {
    for (GenFeature genFeature : genClass.getESetGenFeatures()) {
    if (genFeature.isUncheckedCast(genClass) && !genFeature.isFeatureMapType() && !genFeature.isMapType()) {
    stringBuffer.append(TEXT_79);
    break; }
    }
    }
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_129);
    }
    stringBuffer.append(TEXT_555);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_506);
    for (GenFeature genFeature : genClass.getESetGenFeatures()) {
    stringBuffer.append(TEXT_507);
    stringBuffer.append(OCLGenModelUtil.getQualifiedFeatureValue(genClass, genFeature));
    stringBuffer.append(TEXT_508);
    if (genFeature.isListType()) {
    if (genFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_556);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_278);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_524);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_557);
    } else if (genFeature.isFeatureMapType()) {
    stringBuffer.append(TEXT_556);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_73);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_558);
    } else if (genFeature.isMapType()) {
    if (genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_556);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_559);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_510);
    stringBuffer.append(genFeature.getImportedMapTemplateArguments(genClass));
    stringBuffer.append(TEXT_91);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_560);
    } else {
    stringBuffer.append(TEXT_556);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_561);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_558);
    }
    } else {
    stringBuffer.append(TEXT_242);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_562);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_563);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    if (isJDK50) {
    stringBuffer.append(TEXT_564);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_565);
    }
    stringBuffer.append(TEXT_566);
    }
    } else if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_567);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_568);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_569);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_542);
    } else {
    stringBuffer.append(TEXT_567);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_98);
    if (genFeature.getTypeGenDataType() == null || !genFeature.getTypeGenDataType().isObjectType() || !genFeature.getRawType().equals(genFeature.getType(genClass))) {
    stringBuffer.append(TEXT_98);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_91);
    }
    stringBuffer.append(TEXT_570);
    }
    stringBuffer.append(TEXT_571);
    }
    stringBuffer.append(TEXT_382);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_572);
    } else {
    stringBuffer.append(TEXT_573);
    }
    stringBuffer.append(TEXT_180);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEUnsetGenFeatures())) {
    stringBuffer.append(TEXT_133);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_129);
    }
    stringBuffer.append(TEXT_574);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_506);
    for (GenFeature genFeature : genClass.getEUnsetGenFeatures()) {
    stringBuffer.append(TEXT_507);
    stringBuffer.append(OCLGenModelUtil.getQualifiedFeatureValue(genClass, genFeature));
    stringBuffer.append(TEXT_508);
    if (genFeature.isListType() && !genFeature.isUnsettable()) {
    if (genFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_556);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_524);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_575);
    } else {
    stringBuffer.append(TEXT_242);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_576);
    }
    } else if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_577);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_233);
    } else if (!genFeature.hasEDefault()) {
    stringBuffer.append(TEXT_567);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_277);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_578);
    } else if (genFeature.hasSettingDelegate()) {
    stringBuffer.append(TEXT_242);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_426);
    } else {
    stringBuffer.append(TEXT_567);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_98);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_151);
    }
    stringBuffer.append(TEXT_571);
    }
    stringBuffer.append(TEXT_382);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_579);
    } else {
    stringBuffer.append(TEXT_580);
    }
    stringBuffer.append(TEXT_180);
    //Class/eUnset.override.javajetinc
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEIsSetGenFeatures())) {
    stringBuffer.append(TEXT_133);
    if (genModel.useGenerics()) {
    for (GenFeature genFeature : genClass.getEIsSetGenFeatures()) {
    if (genFeature.isListType() && !genFeature.isUnsettable() && !genFeature.isWrappedFeatureMapType() && !genClass.isField(genFeature) && genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_79);
    break; }
    }
    }
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_129);
    }
    stringBuffer.append(TEXT_581);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_506);
    for (GenFeature genFeature : genClass.getEIsSetGenFeatures()) { String safeNameAccessor = genFeature.getSafeName(); if ("featureID".equals(safeNameAccessor)) { safeNameAccessor = "this." + safeNameAccessor; }
    stringBuffer.append(TEXT_507);
    stringBuffer.append(OCLGenModelUtil.getQualifiedFeatureValue(genClass, genFeature));
    stringBuffer.append(TEXT_508);
    if (genFeature.hasSettingDelegate()) {
    if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_582);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_233);
    } else {
    stringBuffer.append(TEXT_539);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_449);
    }
    } else if (genFeature.isListType() && !genFeature.isUnsettable()) {
    if (genFeature.isWrappedFeatureMapType()) {
    if (genFeature.isVolatile()) {
    stringBuffer.append(TEXT_583);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_524);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_584);
    } else {
    stringBuffer.append(TEXT_539);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_585);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_586);
    }
    } else {
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_539);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_585);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_587);
    } else {
    if (genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_242);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_76);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_149);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_150);
    stringBuffer.append(OCLGenModelUtil.getQualifiedFeatureValue(genClass, genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_588);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_585);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_587);
    } else {
    stringBuffer.append(TEXT_589);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_590);
    }
    }
    }
    } else if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_582);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_233);
    } else if (genFeature.isResolveProxies()) {
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_539);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_591);
    } else {
    if (genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_592);
    stringBuffer.append(OCLGenModelUtil.getQualifiedFeatureValue(genClass, genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_593);
    } else {
    stringBuffer.append(TEXT_594);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_595);
    }
    }
    } else if (!genFeature.hasEDefault()) {
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_539);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_591);
    } else {
    if (genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_592);
    stringBuffer.append(OCLGenModelUtil.getQualifiedFeatureValue(genClass, genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_593);
    } else {
    stringBuffer.append(TEXT_539);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_595);
    }
    }
    } else if (genFeature.isPrimitiveType() || genFeature.isEnumType()) {
    if (genClass.isField(genFeature)) {
    if (genClass.isFlag(genFeature)) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_509);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_266);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_596);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_6);
    } else {
    stringBuffer.append(TEXT_512);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_266);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_597);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_437);
    }
    } else {
    stringBuffer.append(TEXT_539);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_365);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_6);
    }
    } else {
    if (genFeature.isEnumType() && genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_598);
    stringBuffer.append(OCLGenModelUtil.getQualifiedFeatureValue(genClass, genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_154);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_599);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_6);
    } else {
    stringBuffer.append(TEXT_539);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_600);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_6);
    }
    }
    } else {//datatype
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_539);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_393);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_601);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_602);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_151);
    } else {
    if (genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_242);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_76);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_149);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_150);
    stringBuffer.append(OCLGenModelUtil.getQualifiedFeatureValue(genClass, genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_154);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_588);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_393);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_601);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_602);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_151);
    } else {
    stringBuffer.append(TEXT_539);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_393);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_603);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_602);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_542);
    }
    }
    }
    }
    stringBuffer.append(TEXT_382);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_604);
    } else {
    stringBuffer.append(TEXT_605);
    }
    stringBuffer.append(TEXT_180);
    //Class/eIsSet.override.javajetinc
    }
    if (isImplementation && (!genClass.getMixinGenFeatures().isEmpty() || genClass.hasOffsetCorrection() && !genClass.getGenFeatures().isEmpty())) {
    if (!genClass.getMixinGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_133);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_129);
    }
    stringBuffer.append(TEXT_606);
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_607);
    for (GenClass mixinGenClass : genClass.getMixinGenClasses()) {
    stringBuffer.append(TEXT_608);
    stringBuffer.append(mixinGenClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_609);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_241);
    for (GenFeature genFeature : mixinGenClass.getGenFeatures()) {
    stringBuffer.append(TEXT_610);
    stringBuffer.append(OCLGenModelUtil.getQualifiedFeatureValue(genClass, genFeature));
    stringBuffer.append(TEXT_611);
    stringBuffer.append(OCLGenModelUtil.getQualifiedFeatureValue(mixinGenClass, genFeature));
    stringBuffer.append(TEXT_6);
    }
    stringBuffer.append(TEXT_612);
    }
    stringBuffer.append(TEXT_613);
    }
    stringBuffer.append(TEXT_614);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_129);
    }
    stringBuffer.append(TEXT_615);
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_607);
    for (GenClass mixinGenClass : genClass.getMixinGenClasses()) {
    stringBuffer.append(TEXT_608);
    stringBuffer.append(mixinGenClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_616);
    for (GenFeature genFeature : mixinGenClass.getGenFeatures()) {
    stringBuffer.append(TEXT_610);
    stringBuffer.append(OCLGenModelUtil.getQualifiedFeatureValue(mixinGenClass, genFeature));
    stringBuffer.append(TEXT_611);
    stringBuffer.append(OCLGenModelUtil.getQualifiedFeatureValue(genClass, genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_6);
    }
    stringBuffer.append(TEXT_612);
    }
    if (genClass.hasOffsetCorrection() && !genClass.getGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_608);
    stringBuffer.append(genClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_617);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_241);
    for (GenFeature genFeature : genClass.getGenFeatures()) {
    stringBuffer.append(TEXT_610);
    stringBuffer.append(OCLGenModelUtil.getQualifiedFeatureValue(genClass, genFeature));
    stringBuffer.append(TEXT_611);
    stringBuffer.append(OCLGenModelUtil.getQualifiedFeatureValue(genClass, genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_6);
    }
    stringBuffer.append(TEXT_612);
    }
    stringBuffer.append(TEXT_618);
    }
    if (genModel.isOperationReflection() && isImplementation && (!genClass.getMixinGenOperations().isEmpty() || !genClass.getOverrideGenOperations(genClass.getExtendedGenOperations(), genClass.getImplementedGenOperations()).isEmpty() || genClass.hasOffsetCorrection() && !genClass.getGenOperations().isEmpty())) {
    stringBuffer.append(TEXT_133);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_129);
    }
    stringBuffer.append(TEXT_619);
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_607);
    for (GenClass extendedGenClass : genClass.getExtendedGenClasses()) { List<GenOperation> extendedImplementedGenOperations = extendedGenClass.getImplementedGenOperations(); List<GenOperation> implementedGenOperations = genClass.getImplementedGenOperations();
    if (!genClass.getOverrideGenOperations(extendedImplementedGenOperations, implementedGenOperations).isEmpty()) {
    stringBuffer.append(TEXT_608);
    stringBuffer.append(extendedGenClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_620);
    for (GenOperation genOperation : extendedImplementedGenOperations) { GenOperation overrideGenOperation = genClass.getOverrideGenOperation(genOperation);
    if (implementedGenOperations.contains(overrideGenOperation)) {
    stringBuffer.append(TEXT_610);
    stringBuffer.append(OCLGenModelUtil.getQualifiedOperationValue(extendedGenClass, genOperation));
    stringBuffer.append(TEXT_611);
    stringBuffer.append(OCLGenModelUtil.getQualifiedOperationValue(genClass, overrideGenOperation));
    stringBuffer.append(positiveOperationOffsetCorrection);
    stringBuffer.append(TEXT_6);
    }
    }
    stringBuffer.append(TEXT_621);
    }
    }
    for (GenClass mixinGenClass : genClass.getMixinGenClasses()) {
    stringBuffer.append(TEXT_608);
    stringBuffer.append(mixinGenClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_620);
    for (GenOperation genOperation : mixinGenClass.getGenOperations()) { GenOperation overrideGenOperation = genClass.getOverrideGenOperation(genOperation);
    stringBuffer.append(TEXT_610);
    stringBuffer.append(OCLGenModelUtil.getQualifiedOperationValue(mixinGenClass, genOperation));
    stringBuffer.append(TEXT_611);
    stringBuffer.append(OCLGenModelUtil.getQualifiedOperationValue(genClass, overrideGenOperation != null ? overrideGenOperation : genOperation));
    stringBuffer.append(positiveOperationOffsetCorrection);
    stringBuffer.append(TEXT_6);
    }
    stringBuffer.append(TEXT_612);
    }
    if (genClass.hasOffsetCorrection() && !genClass.getGenOperations().isEmpty()) {
    stringBuffer.append(TEXT_608);
    stringBuffer.append(genClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_622);
    stringBuffer.append(negativeOperationOffsetCorrection);
    stringBuffer.append(TEXT_241);
    for (GenOperation genOperation : genClass.getGenOperations()) {
    stringBuffer.append(TEXT_610);
    stringBuffer.append(OCLGenModelUtil.getQualifiedOperationValue(genClass, genOperation));
    stringBuffer.append(TEXT_611);
    stringBuffer.append(OCLGenModelUtil.getQualifiedOperationValue(genClass, genOperation));
    stringBuffer.append(positiveOperationOffsetCorrection);
    stringBuffer.append(TEXT_6);
    }
    stringBuffer.append(TEXT_612);
    }
    stringBuffer.append(TEXT_623);
    }
    if (isImplementation && genModel.isVirtualDelegation()) { String eVirtualValuesField = genClass.getEVirtualValuesField();
    if (eVirtualValuesField != null) {
    stringBuffer.append(TEXT_133);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_129);
    }
    stringBuffer.append(TEXT_624);
    stringBuffer.append(eVirtualValuesField);
    stringBuffer.append(TEXT_625);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_129);
    }
    stringBuffer.append(TEXT_626);
    stringBuffer.append(eVirtualValuesField);
    stringBuffer.append(TEXT_627);
    }
    { List<String> eVirtualIndexBitFields = genClass.getEVirtualIndexBitFields(new ArrayList<String>());
    if (!eVirtualIndexBitFields.isEmpty()) { List<String> allEVirtualIndexBitFields = genClass.getAllEVirtualIndexBitFields(new ArrayList<String>());
    stringBuffer.append(TEXT_133);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_129);
    }
    stringBuffer.append(TEXT_628);
    for (int i = 0; i < allEVirtualIndexBitFields.size(); i++) {
    stringBuffer.append(TEXT_507);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_629);
    stringBuffer.append(allEVirtualIndexBitFields.get(i));
    stringBuffer.append(TEXT_6);
    }
    stringBuffer.append(TEXT_630);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_129);
    }
    stringBuffer.append(TEXT_631);
    for (int i = 0; i < allEVirtualIndexBitFields.size(); i++) {
    stringBuffer.append(TEXT_507);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_632);
    stringBuffer.append(allEVirtualIndexBitFields.get(i));
    stringBuffer.append(TEXT_633);
    }
    stringBuffer.append(TEXT_634);
    }
    }
    }
    if (genModel.isOperationReflection() && isImplementation && !genClass.getImplementedGenOperations().isEmpty()) {
    stringBuffer.append(TEXT_133);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_129);
    }
    if (genModel.useGenerics()) {
    boolean isUnchecked = false; boolean isRaw = false; LOOP: for (GenOperation genOperation : (genModel.isMinimalReflectiveMethods() ? genClass.getImplementedGenOperations() : genClass.getAllGenOperations())) { for (GenParameter genParameter : genOperation.getGenParameters()) { if (genParameter.isUncheckedCast()) { if (genParameter.getTypeGenDataType() == null || !genParameter.getTypeGenDataType().isObjectType()) { isUnchecked = true; } if (genParameter.usesOperationTypeParameters() && !genParameter.getEcoreParameter().getEGenericType().getETypeArguments().isEmpty()) { isRaw = true; break LOOP; }}}}
    if (isUnchecked) {
    stringBuffer.append(TEXT_635);
    if (!isRaw) {
    stringBuffer.append(TEXT_636);
    } else {
    stringBuffer.append(TEXT_637);
    }
    stringBuffer.append(TEXT_91);
    }
    }
    stringBuffer.append(TEXT_638);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EList"));
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_639);
    stringBuffer.append(genModel.getImportedName(isGWT ? "org.eclipse.emf.common.util.InvocationTargetException" : "java.lang.reflect.InvocationTargetException"));
    stringBuffer.append(TEXT_640);
    stringBuffer.append(negativeOperationOffsetCorrection);
    stringBuffer.append(TEXT_506);
    for (GenOperation genOperation : (genModel.isMinimalReflectiveMethods() ? genClass.getImplementedGenOperations() : genClass.getAllGenOperations())) { List<GenParameter> genParameters = genOperation.getGenParameters(); int size = genParameters.size();  boolean hasCheckedException = genOperation.hasCheckedException(); String indent = hasCheckedException ? "\t" : ""; GenOperation overrideGenOperation = genClass.getOverrideGenOperation(genOperation);
    stringBuffer.append(TEXT_507);
    stringBuffer.append(OCLGenModelUtil.getQualifiedOperationValue(genClass, overrideGenOperation != null ? overrideGenOperation : genOperation));
    stringBuffer.append(TEXT_508);
    if (hasCheckedException) {
    stringBuffer.append(TEXT_641);
    /*}*/}
    if (genOperation.isVoid()) {
    stringBuffer.append(TEXT_242);
    stringBuffer.append(indent);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_98);
    for (int i = 0; i < size; i++) { GenParameter genParameter = genParameters.get(i);
    if (!isJDK50 && genParameter.isPrimitiveType()) {
    stringBuffer.append(TEXT_98);
    }
    if (genParameter.getTypeGenDataType() == null || !genParameter.getTypeGenDataType().isObjectType() || !genParameter.usesOperationTypeParameters() && !genParameter.getRawType().equals(genParameter.getType(genClass))) {
    stringBuffer.append(TEXT_98);
    stringBuffer.append(genParameter.usesOperationTypeParameters() ? genParameter.getRawImportedType() : genParameter.getObjectType(genClass));
    stringBuffer.append(TEXT_91);
    }
    stringBuffer.append(TEXT_642);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_91);
    if (!isJDK50 && genParameter.isPrimitiveType()) {
    stringBuffer.append(TEXT_225);
    stringBuffer.append(genParameter.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_21);
    }
    if (i < (size - 1)) {
    stringBuffer.append(TEXT_154);
    }
    }
    stringBuffer.append(TEXT_643);
    stringBuffer.append(indent);
    stringBuffer.append(TEXT_644);
    } else {
    stringBuffer.append(TEXT_242);
    stringBuffer.append(indent);
    stringBuffer.append(TEXT_645);
    if (!isJDK50 && genOperation.isPrimitiveType()) {
    stringBuffer.append(TEXT_348);
    stringBuffer.append(genOperation.getObjectType(genClass));
    stringBuffer.append(TEXT_98);
    }
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_98);
    for (int i = 0; i < size; i++) { GenParameter genParameter = genParameters.get(i);
    if (!isJDK50 && genParameter.isPrimitiveType()) {
    stringBuffer.append(TEXT_98);
    }
    if (genParameter.getTypeGenDataType() == null || !genParameter.getTypeGenDataType().isObjectType() || !genParameter.usesOperationTypeParameters() && !genParameter.getRawType().equals(genParameter.getType(genClass))) {
    stringBuffer.append(TEXT_98);
    stringBuffer.append(genParameter.usesOperationTypeParameters() ? genParameter.getRawImportedType() : genParameter.getObjectType(genClass));
    stringBuffer.append(TEXT_91);
    }
    stringBuffer.append(TEXT_642);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_91);
    if (!isJDK50 && genParameter.isPrimitiveType()) {
    stringBuffer.append(TEXT_225);
    stringBuffer.append(genParameter.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_21);
    }
    if (i < (size - 1)) {
    stringBuffer.append(TEXT_154);
    }
    }
    stringBuffer.append(TEXT_91);
    if (!isJDK50 && genOperation.isPrimitiveType()) {
    stringBuffer.append(TEXT_91);
    }
    stringBuffer.append(TEXT_6);
    }
    if (hasCheckedException) {/*{*/
    stringBuffer.append(TEXT_646);
    stringBuffer.append(genModel.getImportedName("java.lang.Throwable"));
    stringBuffer.append(TEXT_647);
    stringBuffer.append(genModel.getImportedName(isGWT ? "org.eclipse.emf.common.util.InvocationTargetException" : "java.lang.reflect.InvocationTargetException"));
    stringBuffer.append(TEXT_648);
    }
    }
    stringBuffer.append(TEXT_382);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_649);
    } else {
    stringBuffer.append(TEXT_650);
    }
    stringBuffer.append(TEXT_180);
    }
    if (!genClass.hasImplementedToStringGenOperation() && isImplementation && !genModel.isReflectiveDelegation() && !genModel.isDynamicDelegation() && !genClass.getToStringGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_133);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_129);
    }
    stringBuffer.append(TEXT_651);
    stringBuffer.append(genModel.useGenerics() ? "StringBuilder" : "StringBuffer");
    stringBuffer.append(TEXT_652);
    stringBuffer.append(genModel.useGenerics() ? "StringBuilder" : "StringBuffer");
    stringBuffer.append(TEXT_653);
    { boolean first = true;
    for (GenFeature genFeature : genClass.getToStringGenFeatures()) {
    if (first) { first = false;
    stringBuffer.append(TEXT_654);
    stringBuffer.append(genFeature.getName());
    stringBuffer.append(TEXT_655);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    stringBuffer.append(TEXT_656);
    stringBuffer.append(genFeature.getName());
    stringBuffer.append(TEXT_655);
    stringBuffer.append(genModel.getNonNLS());
    }
    if (genFeature.isUnsettable() && !genFeature.isListType()) {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_657);
    stringBuffer.append(OCLGenModelUtil.getQualifiedFeatureValue(genClass, genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_658);
    stringBuffer.append(OCLGenModelUtil.getQualifiedFeatureValue(genClass, genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_659);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    if (genClass.isFlag(genFeature)) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_141);
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_98);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_266);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_660);
    } else {
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_334);
    }
    stringBuffer.append(TEXT_661);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_266);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_662);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    stringBuffer.append(TEXT_141);
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_98);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_266);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_660);
    } else {
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_334);
    }
    stringBuffer.append(TEXT_663);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_268);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_266);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_269);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_664);
    stringBuffer.append(genModel.getNonNLS());
    }
    } else {
    stringBuffer.append(TEXT_141);
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_98);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_266);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_660);
    } else {
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_334);
    }
    stringBuffer.append(TEXT_663);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_665);
    stringBuffer.append(genModel.getNonNLS());
    }
    }
    } else {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_666);
    stringBuffer.append(OCLGenModelUtil.getQualifiedFeatureValue(genClass, genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    if (!genFeature.isListType() && !genFeature.isReferenceType()){
    stringBuffer.append(TEXT_154);
    stringBuffer.append(genFeature.getEDefault());
    }
    stringBuffer.append(TEXT_263);
    } else {
    if (genClass.isFlag(genFeature)) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_667);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_266);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_668);
    } else {
    stringBuffer.append(TEXT_669);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_268);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_266);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_269);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_670);
    }
    } else {
    stringBuffer.append(TEXT_669);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_151);
    }
    }
    }
    }
    }
    stringBuffer.append(TEXT_671);
    }
    if (isImplementation && genClass.isMapEntry()) { GenFeature keyFeature = genClass.getMapEntryKeyFeature(); GenFeature valueFeature = genClass.getMapEntryValueFeature();
    String objectType = genModel.getImportedName("java.lang.Object");
    String keyType = isJDK50 ? keyFeature.getObjectType(genClass) : objectType;
    String valueType = isJDK50 ? valueFeature.getObjectType(genClass) : objectType;
    String eMapType = genModel.getImportedName("org.eclipse.emf.common.util.EMap") + (isJDK50 ? "<" + keyType + ", " + valueType + ">" : "");
    stringBuffer.append(TEXT_133);
    if (isGWT) {
    stringBuffer.append(TEXT_43);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    stringBuffer.append(TEXT_672);
    if (useInterfaceOverrideAnnotation) {
    stringBuffer.append(TEXT_129);
    }
    stringBuffer.append(TEXT_673);
    stringBuffer.append(objectType);
    stringBuffer.append(TEXT_674);
    if (useInterfaceOverrideAnnotation) {
    stringBuffer.append(TEXT_129);
    }
    stringBuffer.append(TEXT_675);
    if (useInterfaceOverrideAnnotation) {
    stringBuffer.append(TEXT_129);
    }
    stringBuffer.append(TEXT_136);
    stringBuffer.append(keyType);
    stringBuffer.append(TEXT_676);
    if (!isJDK50 && keyFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_276);
    stringBuffer.append(keyFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_677);
    } else {
    stringBuffer.append(TEXT_678);
    }
    stringBuffer.append(TEXT_128);
    if (useInterfaceOverrideAnnotation) {
    stringBuffer.append(TEXT_129);
    }
    stringBuffer.append(TEXT_679);
    stringBuffer.append(keyType);
    stringBuffer.append(TEXT_680);
    if (keyFeature.isListType()) {
    stringBuffer.append(TEXT_681);
    if (!genModel.useGenerics()) {
    stringBuffer.append(TEXT_98);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    stringBuffer.append(TEXT_91);
    }
    stringBuffer.append(TEXT_682);
    } else if (isJDK50) {
    stringBuffer.append(TEXT_683);
    } else if (keyFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_684);
    stringBuffer.append(keyFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_685);
    stringBuffer.append(keyFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_542);
    } else {
    stringBuffer.append(TEXT_686);
    stringBuffer.append(keyFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_687);
    }
    stringBuffer.append(TEXT_128);
    if (useInterfaceOverrideAnnotation) {
    stringBuffer.append(TEXT_129);
    }
    stringBuffer.append(TEXT_136);
    stringBuffer.append(valueType);
    stringBuffer.append(TEXT_688);
    if (!isJDK50 && valueFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_276);
    stringBuffer.append(valueFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_689);
    } else {
    stringBuffer.append(TEXT_690);
    }
    stringBuffer.append(TEXT_128);
    if (useInterfaceOverrideAnnotation) {
    stringBuffer.append(TEXT_129);
    }
    stringBuffer.append(TEXT_136);
    stringBuffer.append(valueType);
    stringBuffer.append(TEXT_691);
    stringBuffer.append(valueType);
    stringBuffer.append(TEXT_692);
    stringBuffer.append(valueType);
    stringBuffer.append(TEXT_693);
    if (valueFeature.isListType()) {
    stringBuffer.append(TEXT_694);
    if (!genModel.useGenerics()) {
    stringBuffer.append(TEXT_98);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    stringBuffer.append(TEXT_91);
    }
    stringBuffer.append(TEXT_695);
    } else if (isJDK50) {
    stringBuffer.append(TEXT_696);
    } else if (valueFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_697);
    stringBuffer.append(valueFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_698);
    stringBuffer.append(valueFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_542);
    } else {
    stringBuffer.append(TEXT_699);
    stringBuffer.append(valueFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_700);
    }
    stringBuffer.append(TEXT_701);
    if (genModel.useGenerics()) {
    stringBuffer.append(TEXT_79);
    }
    stringBuffer.append(TEXT_136);
    stringBuffer.append(eMapType);
    stringBuffer.append(TEXT_702);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EObject"));
    stringBuffer.append(TEXT_703);
    stringBuffer.append(eMapType);
    stringBuffer.append(TEXT_704);
    }
    /*-------------------------------------------------------------------------------------
     * http://www.eclipse.org/OCL/GenModel/Visitor support for non-abstract classes
     *-------------------------------------------------------------------------------------*/
    if (isInterface) {
    boolean isRootVisitable = OCLGenModelUtil.isRootVisitableClass(genClass);
    GenAnnotation visitorAnnotation = genModel.getGenAnnotation(OCLGenModelUtil.OCL_GENMODEL_VISITOR_URI);
    if (visitorAnnotation != null) {
    if (isRootVisitable) {
    String rootVisitorClassName = visitorAnnotation.getDetails().get(OCLGenModelUtil.ROOT_VISITOR_CLASS);
    stringBuffer.append(TEXT_705);
    stringBuffer.append(OCLGenModelUtil.atNonNull(genModel));
    stringBuffer.append(genModel.getImportedName(rootVisitorClassName));
    stringBuffer.append(TEXT_706);
    }
    }
    }
    if (isImplementation) {
    boolean isRootVisitable = OCLGenModelUtil.isRootVisitableClass(genClass);
    GenAnnotation visitorAnnotation = genModel.getGenAnnotation(OCLGenModelUtil.OCL_GENMODEL_VISITOR_URI);
    if (visitorAnnotation != null) {
    if (!genClass.getEcoreClass().isAbstract() || isRootVisitable) {
    String rootVisitorClassName = visitorAnnotation.getDetails().get(OCLGenModelUtil.ROOT_VISITOR_CLASS);
    String derivedVisitorClassName = visitorAnnotation.getDetails().get(OCLGenModelUtil.DERIVED_VISITOR_CLASS);
    boolean isDerivedVisitor = (derivedVisitorClassName != null) && !rootVisitorClassName.equals(derivedVisitorClassName);
    stringBuffer.append(TEXT_707);
    if (isDerivedVisitor && genModel.useGenerics()) {
    stringBuffer.append(TEXT_79);
    }
    if (genModel.useClassOverrideAnnotation() && !isRootVisitable) {
    stringBuffer.append(TEXT_129);
    }
    stringBuffer.append(TEXT_708);
    stringBuffer.append(OCLGenModelUtil.atNonNull(genModel));
    stringBuffer.append(genModel.getImportedName(rootVisitorClassName));
    stringBuffer.append(TEXT_709);
    if (isDerivedVisitor) {
    stringBuffer.append(TEXT_710);
    stringBuffer.append(genModel.getImportedName(derivedVisitorClassName));
    stringBuffer.append(TEXT_711);
    stringBuffer.append(genModel.getImportedName(derivedVisitorClassName));
    stringBuffer.append(TEXT_712);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_713);
    } else {
    stringBuffer.append(TEXT_714);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_715);
    }
    stringBuffer.append(TEXT_716);
    }
    }
    }
    /*-------------------------------------------------------------------------------------
     * http://www.eclipse.org/OCL/GenModel/CopyAndPaste support
     *-------------------------------------------------------------------------------------*/
    if (isImplementation) {
    stringBuffer.append(TEXT_7);
    stringBuffer.append(OCLGenModelUtil.copyAndPaste(genClass));
    }
    /*-------------------------------------------------------------------------------------
     * Contributions from CGModelSpec instances
     *-------------------------------------------------------------------------------------*/
    if (isInterface) {
    stringBuffer.append(ModelSpec.generate(genClass, false));
    }
    if (isImplementation) {
    stringBuffer.append(ModelSpec.generate(genClass, true));
    }
    stringBuffer.append(TEXT_717);
    stringBuffer.append(isInterface ? " " + genClass.getInterfaceName() : genClass.getClassName());
    // TODO fix the space above
    genModel.emitSortedImports();
    stringBuffer.append(TEXT_7);
    return stringBuffer.toString();
  }
}
