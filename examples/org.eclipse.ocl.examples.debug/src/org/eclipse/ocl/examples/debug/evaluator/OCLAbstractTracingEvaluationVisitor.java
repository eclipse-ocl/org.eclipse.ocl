/*******************************************************************************
 * Copyright (c) 2014, 2016 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     R.Dvorak and others - QVTo debugger framework
 *     E.D.Willink - revised API for OCL debugger framework
 *******************************************************************************/
package org.eclipse.ocl.examples.debug.evaluator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.evaluation.EvaluationVisitor;
import org.eclipse.ocl.pivot.internal.prettyprint.PrettyPrinter;


/**
 * The Class OCLAbstractTracingEvaluationVisitor is a decorator visitor that prints
 * AST, variable values and other information useful for debugging.
 * 
 * @author Horacio Hoyos
 */
public abstract class OCLAbstractTracingEvaluationVisitor extends OCLVMEvaluationVisitorDecorator
{
	
	protected static Logger logger = Logger.getLogger(OCLAbstractTracingEvaluationVisitor.class);
	
	/** The Constant DEFAULT_INDENT. */
	protected static final String DEFAULT_INDENT = "  ";
	
	/** The verbose level low. */
	static int VERBOSE_LEVEL_LOW = 0;
	
	/** The verbose level med. */
	static int VERBOSE_LEVEL_MED = 1;
	
	/** The verbose level high. */
	static int VERBOSE_LEVEL_HIGH = 2;
	
	/** The verbose level. */
	protected static int verboseLevel = VERBOSE_LEVEL_LOW;
	
	/** The indent level. */
	private int indentLevel = 0;
	

	/**
	 * Instantiates a new OCL tracing evaluation visitor.
	 *
	 * @param decorated the decorated
	 */
	public OCLAbstractTracingEvaluationVisitor(@NonNull EvaluationVisitor decorated) {
		
		this(decorated, 0);
		if (logger.getAppender("OCLTrace") == null) {
			FileAppender appender = new FileAppender();
			// configure the appender here, with file location, etc
			appender.setFile("OCLTrace.log");
			appender.setName("OCLTrace");
			appender.setLayout(new PatternLayout(PatternLayout.DEFAULT_CONVERSION_PATTERN));
			appender.setAppend(false);
			appender.activateOptions();
			logger.addAppender(appender);
		}
		logger.setAdditivity(false);
		logger.setLevel(Level.DEBUG);
	}
	
	/**
	 * Instantiates a new OCL tracing evaluation visitor.
	 *
	 * @param decorated the decorated
	 * @param indentLevel the indent level
	 */
	protected OCLAbstractTracingEvaluationVisitor(@NonNull EvaluationVisitor decorated, int indentLevel) {
		
		super(decorated);
		this.indentLevel = indentLevel;
	}
	
		
	/**
	 * Gets the verbose level.
	 *
	 * @return the verbose level
	 */
	public int getVerboseLevel() {
		return verboseLevel;
	}

	
	/**
	 * Sets the verbose level.
	 *
	 * @param verboseLevel the new verbose level
	 */
	public void setVerboseLevel(int verboseLevel) {
		OCLAbstractTracingEvaluationVisitor.verboseLevel = verboseLevel;
	}
	
	/**
	 * Gets the indent.
	 *
	 * @return the indent
	 */
	protected @NonNull String getIndent() {
		
		StringBuilder outputBuffer = new StringBuilder(indentLevel);
		for (int i = 0; i < indentLevel; i++){
		   outputBuffer.append(DEFAULT_INDENT);
		}
		return outputBuffer.toString();
	}
	
//	public @NonNull Pattern getRegexPattern(@NonNull String regex) {
//		return delegate.getRegexPattern(regex);
//	}

	/**
	 * Safe print.
	 *
	 * @param element the element
	 * @return the string
	 */
	protected @NonNull String safePrint(@Nullable Element element) {
		return element != null ? PrettyPrinter.print(element) : "null";
	}
	
	
	/**
	 * Pretty print Objects. EObjects are printed at 1 level depth (i.e. no 
	 * navigation through associations). Other objects are printed as Strings
	 *
	 * @param object the object
	 * @return the string
	 */
	protected String prettyPrint(Object object) {
		
		List<String> lines = new ArrayList<String>();
		if (object instanceof EObject) {
			EObject eo = (EObject) object;
			EClass eClass = eo.eClass();
			lines.add(eClass.getName() + " [" + Integer.toHexString(eo.hashCode()) + "] {"); // 
			indentLevel++;
			for (EStructuralFeature feature : eClass.getEAllStructuralFeatures()) {
				Object value = eo.eGet(feature);
				String line = getIndent() + feature.getName() + " = ";
				if (value instanceof Collection<?>) {
					int size = ((Collection<?>)value).size(); 
					if (size > 0) {
						line += "[" + ((EObject)((Iterable<@NonNull ?>) value).iterator().next()).eClass().getName() + "] x " + size;
					} else {
						line += "[ ]";
					}
				} else if (value instanceof EObject) {
					eClass = ((EObject) value).eClass();
					line += eClass.getName() + " [" + Integer.toHexString(value.hashCode()) + "]";
				} else {
					if (value == null) {
						line += "[ null ]";
					} else {
						line += String.valueOf(value);
					}
				}
				lines.add(line);
			}
			lines.add(getIndent() + "}");
			indentLevel--;
			StringBuilder sb = new StringBuilder();
			for (String line : lines) {
				sb.append(line).append('\n');
			}
			sb.delete(sb.length()-1, sb.length());
			return sb.toString();
		} else {
			return String.valueOf(object);
		}
	}
	
	protected String prettyPrintUnident(Object object) {
		List<String> lines = new ArrayList<String>();
		if (object instanceof EObject) {
			EObject eo = (EObject) object;
			EClass eClass = eo.eClass();
			lines.add(eClass.getName() + " [" + Integer.toHexString(eo.hashCode()) + "] {"); // 
			indentLevel++;
			for (EStructuralFeature feature : eClass.getEAllStructuralFeatures()) {
				Object value = eo.eGet(feature);
				String line =  DEFAULT_INDENT + feature.getName() + " = ";
				if (value instanceof Collection<?>) {
					int size = ((Collection<?>)value).size(); 
					if (size > 0) {
						line += "[" + ((EObject)((Iterable<@NonNull ?>) value).iterator().next()).eClass().getName() + "] x " + size;
					} else {
						line += "[ ]";
					}
				} else if (value instanceof EObject) {
					eClass = ((EObject) value).eClass();
					line += eClass.getName() + " [" + Integer.toHexString(value.hashCode()) + "]";
				} else {
					if (value == null) {
						line += "[ null ]";
					} else {
						line += String.valueOf(value);
					}
				}
				lines.add(line);
			}
			lines.add(DEFAULT_INDENT + "}");
			indentLevel--;
			StringBuilder sb = new StringBuilder();
			for (String line : lines) {
				sb.append(line).append('\n');
			}
			sb.delete(sb.length()-1, sb.length());
			return sb.toString();
		} else {
			return String.valueOf(object);
		}
		
		
	}
}
