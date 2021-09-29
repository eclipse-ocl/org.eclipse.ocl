/*******************************************************************************
 * Copyright (c) 2015, 2021 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.build.xtend;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.mwe.core.WorkflowContext;
import org.eclipse.emf.mwe.core.issues.Issues;
import org.eclipse.emf.mwe.core.lib.AbstractWorkflowComponent;
import org.eclipse.emf.mwe.core.monitor.ProgressMonitor;
import org.eclipse.emf.mwe.utils.StandaloneSetup;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.codegen.generator.EcoreGenModelHelper;
import org.eclipse.ocl.examples.codegen.generator.GenModelHelper;
import org.eclipse.ocl.pivot.internal.manager.PivotMetamodelManager;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.OCL;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.TreeIterable;
import org.eclipse.xtext.AbstractRule;
import org.eclipse.xtext.EnumRule;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.ParserRule;
import org.eclipse.xtext.ReferencedMetamodel;
import org.eclipse.xtext.TerminalRule;
import org.eclipse.xtext.resource.impl.BinaryGrammarResourceFactoryImpl;
import org.eclipse.xtext.util.Strings;

public abstract class GenerateGrammar extends AbstractWorkflowComponent
{
	protected Logger log = Logger.getLogger(getClass());
	protected ResourceSet resourceSet = null;
	protected String languageName;
	protected String javaFolder;
	protected String javaPackageName;
	protected String grammarFileStem;

	protected String sourceFile;

	protected GenModelHelper genModelHelper;
	private Map<EClassifier, Map<Notifier, String>> nameMaps = new HashMap<EClassifier, Map<Notifier, String>>();
	private Map<String, Grammar> name2grammar = new HashMap<String, Grammar>();
	private int indent = 0;

	@Override
	public void checkConfiguration(Issues issues) {
		if (javaFolder == null) {
			issues.addError(this, "javaFolder not specified.");
		}
		if (javaPackageName == null) {
			issues.addError(this, "javaPackageName not specified.");
		}
		if (grammarFileStem == null) {
			issues.addError(this, "grammarFileStem not specified.");
		}
		if (languageName == null) {
			languageName = javaPackageName + "." + grammarFileStem;
		}
	}

	protected @NonNull String emitEClassifierLiteral(@NonNull EClassifier eClassifier) {
	//	EPackage ePackage = ClassUtil.nonNullEMF(eClassifier.getEPackage());
	//	GenPackage genPackage = genModelHelper.getGenPackage(ePackage);
	//	if (genPackage == null) {
	//		return "<<" + ePackage.getNsURI() + ">>";
	//	}
		return genModelHelper.getFullyQualifiedEcoreLiteralName(eClassifier);
	}

	protected @NonNull String emitEPackageLiteral(@NonNull EPackage ePackage) {
		GenPackage genPackage = genModelHelper.getGenPackage(ePackage);
		if (genPackage == null) {
			return "<<" + ePackage.getNsURI() + ">>";
		}
		return genPackage.getQualifiedPackageInterfaceName() + ".eINSTANCE";
	}

	protected @NonNull String emitEEnumLiteral(@NonNull EEnumLiteral enumLiteral) {
		EClassifier eClassifier = ClassUtil.nonNullState(enumLiteral.getEEnum());
	//	EPackage ePackage = ClassUtil.nonNullEMF(eClassifier.getEPackage());
	//	GenPackage genPackage = genModelHelper.getGenPackage(ePackage);
	//	if (genPackage == null) {
	//		return "<<" + ePackage.getNsURI() + ">>";
	//	}
		return genModelHelper.getQualifiedEcoreLiteralName(eClassifier)+".getEEnumLiteral(\""+ enumLiteral.getName() + "\")";
	}

	/*
	 * Emit the prevailing indent
	 */
	protected @NonNull String emitIndent() {
		StringBuilder s = new StringBuilder();
		s.append("\n");
		for (int i = 0; i < indent; i++) {
			s.append("\t");
		}
		return s.toString();
	}

	protected @NonNull String emitParserRuleLiteral(@NonNull Grammar grammar, @NonNull ParserRule parserRule) {
		Grammar referencedGrammar = (Grammar)parserRule.eContainer();
		if ((referencedGrammar == null) || (referencedGrammar == grammar)) {
			return "PR_" + parserRule.getName();
		}
		else {
			return getGrammarPackageName(referencedGrammar)+ ".PR_" + parserRule.getName();
		}
	}

	protected @NonNull String emitEnumRuleLiteral(@NonNull Grammar grammar, @NonNull EnumRule enumRule) {
		Grammar referencedGrammar = (Grammar)enumRule.eContainer();
		if ((referencedGrammar == null) || (referencedGrammar == grammar)) {
			return "ER_" + enumRule.getName();
		}
		else {
			return getGrammarPackageName(referencedGrammar)+ ".ER_" + enumRule.getName();
		}
	}

	protected @NonNull String emitReferencedMetamodelName(@NonNull Grammar grammar, @NonNull ReferencedMetamodel referencedMetamodel) {
		String alias = referencedMetamodel.getAlias();
		if (alias == null) {
			return "MM";
		}
		else {
			return "MM_" + alias;
		}
	}

	protected @NonNull String emitSymbol(EClassifier eClass, @NonNull Notifier eObject) {
		Map<Notifier, String> names = nameMaps.get(eClass);
		if (names == null) {
			names = new HashMap<Notifier, String>();
			nameMaps.put(eClass, names);
		}
		String name = names.get(eObject);
		if (name == null) {
			name = eClass.getName() + "_" + names.size();
			names.put(eObject, name);
		}
		return name;
	}

	protected @NonNull String emitTerminalRuleLiteral(@NonNull Grammar grammar, @NonNull TerminalRule terminalRule) {
		Grammar referencedGrammar = (Grammar)terminalRule.eContainer();
		if ((referencedGrammar == null) || (referencedGrammar == grammar)) {
			return "TR_" + terminalRule.getName();
		}
		else {
			return getGrammarPackageName(referencedGrammar)+ ".TR_" + terminalRule.getName();
		}
	}

	@SuppressWarnings("null")
	protected @NonNull String emitValue(Object value) {
		if (value instanceof Boolean) {
			return value.toString();
		}
		if (value instanceof Number) {
			return value.toString();
		}
		return value != null ? ("\"" + Strings.convertToJavaString(value.toString())+ "\"") : "null";
	}

	protected abstract /*@NonNull*/ String generate(/*@NonNull*/ Resource grammarResource);

	protected String getGetAccessorName(@NonNull EStructuralFeature eStructuralFeature) {
		return genModelHelper.getGetAccessor(eStructuralFeature);
	}

	protected @NonNull String getGrammarPackageName(@NonNull Grammar grammar) {
		String name = grammar.getName();
		int index = name.lastIndexOf(".");
		if (index >= 0) {
			name = "_" + name.substring(index+1);
		}
		else {
			name = "_" + name;
		}
		Grammar theGrammar = name2grammar.get(name);
		if (theGrammar == null) {
			theGrammar = grammar;
			name2grammar.put(name, theGrammar);
		}
		if (theGrammar == grammar) {
			return name;
		}
		return "_" + grammar.getName().replaceAll("\\.", "_");
	}

	protected ResourceSet getResourceSet() {
		if (resourceSet == null) {
			resourceSet = new ResourceSetImpl();
		}
		return resourceSet;
	}

	protected String getSetAccessorName(@NonNull EStructuralFeature eStructuralFeature) {
		return genModelHelper.getSetAccessor(eStructuralFeature);
	}

	protected <AR extends AbstractRule> List<AR> getSortedAbstractRules(@NonNull Grammar grammar, Class<AR> type) {
		List<AR> abstractRules = new ArrayList<AR>();
		for (TreeIterator<EObject> tit = grammar.eAllContents(); tit.hasNext(); ) {
			EObject eObject = tit.next();
			if (type.isInstance(eObject)) {
				abstractRules.add(type.cast(eObject));
			}
		}
		Collections.sort(abstractRules, new Comparator<@NonNull AR>()
		{
			@Override
			public int compare(@NonNull AR o1, @NonNull AR o2) {
				return ClassUtil.safeCompareTo(o1.getName(), o2.getName());
			}
		});
		return abstractRules;
	}

	protected List<ReferencedMetamodel> getSortedReferencedMetamodels(@NonNull Grammar grammar) {
		List<ReferencedMetamodel> referencedMetamodels = new ArrayList<ReferencedMetamodel>();
		for (TreeIterator<EObject> tit = grammar.eAllContents(); tit.hasNext(); ) {
			EObject eObject = tit.next();
			if (eObject instanceof ReferencedMetamodel) {
				referencedMetamodels.add((ReferencedMetamodel)eObject);
			}
		}
		Collections.sort(referencedMetamodels, new Comparator<ReferencedMetamodel>()
		{
			@Override
			public int compare(ReferencedMetamodel o1, ReferencedMetamodel o2) {
				String n1 = o1.getAlias();
				String n2 = o2.getAlias();
				if (n1 == null) n1 = "";
				if (n2 == null) n2 = "";
				return ClassUtil.safeCompareTo(n1, n2);
			}
		});
		return referencedMetamodels;
	}

	protected boolean hasRules(@NonNull Resource grammarResource, Class<?> ruleClass) {
		for (EObject eObject : new TreeIterable(grammarResource)) {
			if (ruleClass.isAssignableFrom(eObject.getClass())) {
				return true;
			}
		}
		return false;
	}

	@Override
	protected void invokeInternal(WorkflowContext ctx, ProgressMonitor monitor, Issues issues) {
		OCL ocl = OCL.newInstance();
		PivotMetamodelManager metamodelManager = (PivotMetamodelManager) ocl.getMetamodelManager();
		genModelHelper = new EcoreGenModelHelper(metamodelManager);
		String rootPath = StandaloneSetup.getPlatformRootPath();
		File folder = new File(rootPath + javaFolder + "/" + javaPackageName.replace(".", "/"));
		try {
			sourceFile = new File(folder, grammarFileStem + ".xtextbin").toString();
			URI fileURI = URI.createFileURI(sourceFile);
			log.info("Loading Grammar '" + fileURI);
			ResourceSet resourceSet = getResourceSet();
			resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xtextbin", new BinaryGrammarResourceFactoryImpl());
			Resource grammarResource = resourceSet.getResource(fileURI, true);
			String message = PivotUtil.formatResourceDiagnostics(ClassUtil.nonNullEMF(grammarResource.getErrors()), "Grammar load failure", "\n");
			if (message != null) {
				issues.addError(this, message, null, null, null);
				return;
			}
			String fileName = folder + "/" + grammarFileStem + "GrammarResource.java";
			//			log.info("Generating '" + fileName + "'");
			@SuppressWarnings("null")@NonNull String metamodel = generate(grammarResource);
			MergeWriter fw = new MergeWriter(fileName);
			fw.append(metamodel);
			fw.close();
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException("Problems running " + getClass().getSimpleName(), e);
		} finally {
			ocl.dispose(true);
		}
	}

	protected @NonNull String pushIndent() {
		indent++;
		return "";
	}

	protected @NonNull String popIndent() {
		assert indent > 0;
		indent--;
		return "";
	}

	/**
	 * The stem for grammr files such as grammarFileStem.xtextbin. (e.g. "EssentialOCL")
	 */
	public void setGrammarFileStem(String grammarFileStem) {
		this.grammarFileStem = grammarFileStem;
	}

	/**
	 * The platform relative path to the Java generated source folder (e.g. "/org.eclipse.ocl.pivot/emf-src")
	 */
	public void setJavaFolder(String javaFolder) {
		this.javaFolder = javaFolder;
	}

	/**
	 * The Java package path for the grammar. (e.g. "org.eclipse.ocl.pivot.path")
	 */
	public void setJavaPackageName(String javaPackageName) {
		this.javaPackageName = javaPackageName;
	}

	/**
	 * The languageName for the grammar. (e.g. "org.eclipse.ocl.xtext.essentialocl.EssentialOCL")
	 */
	public void setLanguageName(String languageName) {
		this.languageName = languageName;
	}

	/**
	 * An optional ResourceSet that MWE components may share to reduce model loading.
	 */
	public void setResourceSet(ResourceSet resourceSet) {
		this.resourceSet = resourceSet;
	}
}
