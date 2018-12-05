/*******************************************************************************
 * Copyright (c) 2013, 2018 CEA LIST and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink(CEA LIST) - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.asm5;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Handle;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.TypePath;
import org.objectweb.asm.TypeReference;

/**
 * JavaAnnotationReader supports determination of the declared @NonNull, @Nullable return annotation of a method.
 *
 * (The internal processing also determines the parameter type annotations, but as yet there is no API to exploit this.)
 */
public class ASM5JavaAnnotationReader
{
	private static final Logger logger = Logger.getLogger(ASM5JavaAnnotationReader.class);

	private final @NonNull Map<@NonNull String, Map<@NonNull Integer, @Nullable Boolean>> desc2typerefValue2state = new HashMap<@NonNull String, Map<@NonNull Integer, @Nullable Boolean>>();
	private final @NonNull Set<String> readClasses = new HashSet<String>();
	@SuppressWarnings("null")
	private final @NonNull String nonNullDesc = Type.getDescriptor(NonNull.class);
	@SuppressWarnings("null")
	private final @NonNull String nullableDesc = Type.getDescriptor(Nullable.class);

	/**
	 * Set true if ASM has Java 9 support, else false and Java 9 classes will be downgraded to look like Java 8.
	 */
	private boolean hasOpcodes_V1_9 = false;

	/**
	 * Return true for an @NonNull annotation, false for an @Nullable annotation, null otherwise.
	 */
	public ASM5JavaAnnotationReader() {
		try {
			hasOpcodes_V1_9 = Opcodes.class.getField("V1_9") != null;
		}
		catch (Exception e) {}
	}

	public @Nullable Boolean getIsNonNull(@NonNull Method method) {
		final String className = method.getDeclaringClass().getName();
		final String requiredDesc = getMethodKey(className, method.getName(), Type.getMethodDescriptor(method));
		Map<@NonNull Integer, @Nullable Boolean> typeref2state = desc2typerefValue2state.get(requiredDesc);
		Integer returnTypeReference = TypeReference.newTypeReference(TypeReference.METHOD_RETURN).getValue();
		if (typeref2state != null) {
			return typeref2state.get(returnTypeReference);
		}
		if (!readClasses.add(className)) {
			return null;
		}
		//		System.out.println("getIsNonNull: " + requiredDesc + " " + Integer.toHexString(returnTypeReference));
		InputStream classStream = null;
		try {
			final int flags = ClassReader.SKIP_DEBUG | ClassReader.SKIP_FRAMES | ClassReader.SKIP_CODE;
			ClassLoader methodClassLoader = method.getDeclaringClass().getClassLoader();
			String classFileName = className.replace('.', '/') + ".class";
			classStream = methodClassLoader.getResourceAsStream(classFileName);
			final ClassReader cr = new ClassReader(classStream)
			{
				@Override
				public void accept(ClassVisitor classVisitor, int flags) {
					super.accept(classVisitor, flags);
				}

				@Override
				public void accept(ClassVisitor classVisitor, Attribute[] attrs, int flags) {
					super.accept(classVisitor, attrs, flags);
				}

				@Override
				public short readShort(int index) {
					short readShort = super.readShort(index);
					if ((index == 6) && !hasOpcodes_V1_9 && (readShort > Opcodes.V1_8)) {		// FIXME Bug 513663, eliminate once Opcodes.V1_9 available and supported
						return Opcodes.V1_8;
					}
					return readShort;
				}
			};
			ClassVisitor cv = new ClassVisitor(Opcodes.ASM5)
			{
				@Override
				public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {}

				@Override
				public AnnotationVisitor visitAnnotation(String desc,boolean visible) {
					return null;
				}

				@Override
				public void visitAttribute(Attribute attr) {}

				@Override
				public void visitEnd() {}

				@Override
				public FieldVisitor visitField(int access, String name,String desc, String signature, Object value) {
					return null;
				}

				@Override
				public void visitInnerClass(String name, String outerName,String innerName, int access) {}

				@Override
				public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
					final String methodDesc = getMethodKey(className, name, desc);// + " " + signature;
					//					System.out.println("  ClassVisitor.visitMethod: " + methodDesc);
					final HashMap<@NonNull Integer, @Nullable Boolean> typerefValue2state = new HashMap<@NonNull Integer, @Nullable Boolean>();
					desc2typerefValue2state.put(methodDesc, typerefValue2state);
					return new MethodVisitor(Opcodes.ASM5)
					{
						@Override
						public AnnotationVisitor visitAnnotation(String annotationDesc, boolean visible) {
							return null;
						}

						@Override
						public AnnotationVisitor visitAnnotationDefault() {
							return null;
						}

						@Override
						public void visitAttribute(Attribute attr) {}

						@Override
						public void visitCode() {}

						@Override
						public void visitEnd() {}

						@Override
						public void visitFieldInsn(int opcode, String owner, String name, String desc) {}

						@Override
						public void visitFrame(int type, int nLocal, Object[] local, int nStack, Object[] stack) {}

						@Override
						public void visitIincInsn(int var, int increment) {}

						@Override
						public void visitInsn(int opcode) {}

						@Override
						public AnnotationVisitor visitInsnAnnotation(int typeRef, TypePath typePath, String desc, boolean visible) {
							return null;
						}

						@Override
						public void visitIntInsn(int opcode, int operand) {}

						@Override
						public void visitInvokeDynamicInsn(String name, String desc, Handle bsm, Object... bsmArgs) { }

						@Override
						public void visitJumpInsn(int opcode, Label label) {}

						@Override
						public void visitLabel(Label label) {}

						@Override
						public void visitLdcInsn(Object cst) {}

						@Override
						public void visitLineNumber(int line, Label start) {}

						@Override
						public void visitLocalVariable(String name, String desc, String signature, Label start, Label end, int index) {}

						@Override
						public AnnotationVisitor visitLocalVariableAnnotation(int typeRef, TypePath typePath, Label[] start, Label[] end, int[] index, String desc, boolean visible) {
							return null;
						}

						@Override
						public void visitLookupSwitchInsn(Label dflt, int[] keys, Label[] labels) {}

						@Override
						public void visitMaxs(int maxStack, int maxLocals) {}

						@Override
						public void visitMethodInsn(int opcode, String owner, String name, String desc) {}

						@Override
						public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) { }

						@Override
						public void visitMultiANewArrayInsn(String desc, int dims) {}

						@Override
						public void visitParameter(String name, int access) { }

						@Override
						public AnnotationVisitor visitParameterAnnotation(int parameter, String desc, boolean visible) {
							return null;
						}

						@Override
						public void visitTableSwitchInsn(int min, int max, Label dflt, Label... labels) {}

						@Override
						public AnnotationVisitor visitTryCatchAnnotation(int typeRef, TypePath typePath, String desc, boolean visible) {
							return null;
						}

						@Override
						public void visitTryCatchBlock(Label start, Label end,Label handler, String type) {}

						@Override
						public AnnotationVisitor visitTypeAnnotation(int typeRef, TypePath typePath, String desc, boolean visible) {
							//							System.out.println("    MethodVisitor-TypeAnnotation:" + Integer.toHexString(typeRef) + " " + typePath + " " + desc + " " + visible);
							//							TypeReference typeReference = new TypeReference(typeRef);
							//							System.out.println("    : " + Integer.toHexString(typeReference.getValue()) + ":" + typeReference.getSort() + ":" + typeReference.getTypeParameterIndex());
							if (desc.equals(nonNullDesc)) {
								//								System.out.println("    MethodVisitor-TypeAnnotation:" + Integer.toHexString(typeRef) + " " + typePath + " " + desc);
								typerefValue2state.put(typeRef, true);
							}
							else if (desc.equals(nullableDesc)) {
								//								System.out.println("    MethodVisitor-TypeAnnotation:" + Integer.toHexString(typeRef) + " " + typePath + " " + desc);
								typerefValue2state.put(typeRef, false);
							}
							return null;
						}

						@Override
						public void visitTypeInsn(int opcode, String type) {}

						@Override
						public void visitVarInsn(int opcode, int var) {}
					};
				}

				@Override
				public void visitOuterClass(String owner, String name, String desc) {}

				@Override
				public void visitSource(String source, String debug) {}

				@Override
				public AnnotationVisitor visitTypeAnnotation(int typeRef, TypePath typePath, String desc, boolean visible) {
					//					System.out.println("  ClassVisitor-TypeAnnotation:" + typeRef + " " + typePath + " " + desc + " " + visible);
					return null;
				}
			};
			cr.accept(cv, flags);
		} catch (IOException e) {
			logger.error("Failed to read '" + className + "'", e);
		} finally {
			if (classStream != null) {
				try {
					classStream.close();
				} catch (IOException e) {}
			}
		}
		typeref2state = desc2typerefValue2state.get(requiredDesc);
		if (typeref2state == null) {
			return null;
		}
		Boolean state = typeref2state.get(returnTypeReference);
		//		System.out.println("  => " + state);
		return state;
	}

	protected @NonNull String getMethodKey(/*@NonNull*/ String className, /*@NonNull*/ String name, /*@NonNull*/ String desc) {
		return className + " " + name + " " + desc;
	}
}
