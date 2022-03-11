/*******************************************************************************
 * Copyright (c) 2010, 2021 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.test.xtext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.common.internal.options.CommonOptions;
import org.eclipse.ocl.examples.xtext.tests.TestCaseAppender;
import org.eclipse.ocl.examples.xtext.tests.TestUtil;
import org.eclipse.ocl.examples.xtext.tests.XtextTestCase;
import org.eclipse.ocl.pivot.SequenceType;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.internal.complete.CompleteClassInternal;
import org.eclipse.ocl.pivot.internal.complete.CompleteModelInternal;
import org.eclipse.ocl.pivot.internal.context.ModelContext;
import org.eclipse.ocl.pivot.internal.ecore.es2as.Ecore2AS;
import org.eclipse.ocl.pivot.internal.manager.MetamodelManagerInternal;
import org.eclipse.ocl.pivot.internal.messages.PivotMessagesInternal;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.internal.utilities.OCLInternal;
import org.eclipse.ocl.pivot.internal.utilities.PivotConstantsInternal;
import org.eclipse.ocl.pivot.internal.values.CollectionTypeParametersImpl;
import org.eclipse.ocl.pivot.library.LibraryConstants;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.eclipse.ocl.pivot.resource.CSResource;
import org.eclipse.ocl.pivot.uml.UMLStandaloneSetup;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.OCL;
import org.eclipse.ocl.pivot.utilities.PivotConstants;
import org.eclipse.ocl.pivot.utilities.StringUtil;
import org.eclipse.ocl.pivot.utilities.ThreadLocalExecutor;
import org.eclipse.ocl.pivot.utilities.XMIUtil;
import org.eclipse.ocl.pivot.values.CollectionTypeParameters;
import org.eclipse.ocl.xtext.base.utilities.BaseCSResource;
import org.eclipse.ocl.xtext.base.utilities.ElementUtil;
import org.eclipse.ocl.xtext.basecs.ElementCS;
import org.eclipse.ocl.xtext.essentialocl.utilities.EssentialOCLCSResource;
import org.eclipse.ocl.xtext.oclinecorecs.OCLinEcoreCSPackage;
import org.eclipse.xtext.resource.impl.ListBasedDiagnosticConsumer;
import org.junit.Before;

/**
 * Tests that load a model and verify that there are no unresolved proxies as a result.
 */
public class EditTests extends XtextTestCase
{
	public class DebugRef
	{
		public final Object object;

		DebugRef(Object object) {
			this.object = object;
		}
	}

	@Override
	@Before public void setUp() throws Exception {
		TestUtil.doCompleteOCLSetup();
		TestUtil.doOCLstdlibSetup();
		super.setUp();
	}

	private void assertHasComments(@NonNull Resource aResource, @NonNull String @NonNull [] comments) {
		Map<String, Integer> expected = new HashMap<String, Integer>();
		for (String comment : comments) {
			Integer count = expected.get(comment);
			count = (count != null) ? (count + 1) : 1;
			expected.put(comment, count);
		}
		for (TreeIterator<EObject> tit = aResource.getAllContents(); tit.hasNext(); ) {
			EObject eObject = tit.next();
			if (eObject instanceof EModelElement) {
				EAnnotation eAnnotation = ((EModelElement)eObject).getEAnnotation(PivotConstantsInternal.DOCUMENTATION_ANNOTATION_SOURCE);
				if (eAnnotation != null) {
					String comment = eAnnotation.getDetails().get(PivotConstantsInternal.DOCUMENTATION_ANNOTATION_KEY);
					Integer count = expected.get(comment);
					assertTrue("Expected comment '" + comment + "' exists", (count != null) && (count > 0));
					expected.put(comment, (count != null ? count : 0) -1);
				}
			}
		}
		for (String comment : comments) {
			Integer occurences = expected.get(comment);
			assert occurences != null;
			assertEquals("Expected comment '" + comment + "' extra occurences", 0, occurences.intValue());
		}
	}

	public boolean debugStateRef(WeakReference<Type> sequenceMyType) {
		DebugRef debugRef = new DebugRef(sequenceMyType.get());
		return debugRef.object == null;
	}

	protected @NonNull Resource doRename(@NonNull EnvironmentFactory environmentFactory, @NonNull CSResource xtextResource, @NonNull Resource asResource, @NonNull String oldString, @NonNull String newString,
			@NonNull String @NonNull[] asErrors, @NonNull String @NonNull[] ecoreErrors) throws IOException {
		String contextMessage = "Renaming '" + oldString + "' to '" + newString + "'";
		//		System.out.println("-----------------" + contextMessage + "----------------");
		replace(xtextResource, oldString, newString);
		assertResourceErrors(contextMessage, xtextResource, asErrors);
		assertNoResourceErrors(contextMessage, asResource);
		boolean validSave = asErrors.length == 0;
		if (validSave) {
			assertNoValidationErrors(contextMessage, asResource);
		}
		Resource ecoreResource = as2ecore(environmentFactory, asResource, getTestFileURI("test.ecore"), ecoreErrors);
		assertNoResourceErrors(contextMessage, ecoreResource);
		return ecoreResource;
	}

	protected @NonNull Resource getEcoreFromCS(@NonNull OCL ocl, @NonNull String testDocument, @NonNull URI ecoreURI) throws IOException {
		@NonNull URI xtextURI = URI.createURI("test.oclinecore");
		CSResource xtextResource = ocl.getCSResource(xtextURI, testDocument);
		assertNoResourceErrors("Loading Xtext", xtextResource);
		Resource asResource = cs2as(xtextResource, null);
		Resource ecoreResource = as2ecore(ocl.getEnvironmentFactory(), asResource, ecoreURI, NO_MESSAGES);
		return ecoreResource;
	}

	protected void replace(@NonNull CSResource xtextResource, String oldString, String newString) {
		String xtextContent = ElementUtil.getRawText((ElementCS) xtextResource.getContents().get(0));
		int index = xtextContent.indexOf(oldString);
		assert index >= 0;
		xtextResource.update(index, oldString.length(), newString);
	}

	public void testEdit_Paste_NsURI() throws Exception {
		OCL ocl = OCL.newInstance(getProjectMap());
		EnvironmentFactory environmentFactory = ocl.getEnvironmentFactory();
		CommonOptions.DEFAULT_DELEGATION_MODE.setDefaultValue(PivotConstants.OCL_DELEGATE_URI_PIVOT);
		String testDocument =
				"package example : ex = 'http://www.example.org/examples/example.ecore'\n" +
						"{\n" +
						"	class Example\n" +
						"	{\n" +
						"		attribute name : String[?];\n" +
						"		property children : Example[*] { ordered composes };\n" +
						"	}\n" +
						"}\n";
		CSResource xtextResource;
		Resource asResource;
		{
			URI ecoreURI1 = getTestFileURI("test1.ecore");
			URI outputURI = getTestFileURI("test.oclinecore");
			xtextResource = ocl.getCSResource(outputURI, testDocument);
			asResource = cs2as(xtextResource, null);
			@SuppressWarnings("unused") Resource ecoreResource1 = as2ecore(environmentFactory, asResource, ecoreURI1, NO_MESSAGES);
		}
		//
		//	Change NsURI.
		//
		{
			replace(xtextResource, "http://www.example.org/examples/example.ecore", "http://www.example.org/examples/example1.ecore");
			assertNoResourceErrors("Pasting operation", xtextResource);
			assertNoValidationErrors("Pasting operation", xtextResource);
			assertNoResourceErrors("Pasting operation", asResource);
			assertNoValidationErrors("Pasting operation", asResource);
			URI ecoreURI2 = getTestFileURI("test2.ecore");
			@SuppressWarnings("unused") Resource ecoreResource2 = as2ecore(environmentFactory, asResource, ecoreURI2, SUPPRESS_VALIDATION);
		}
		//
		//	Revert NsURI.
		//
		{
			replace(xtextResource, "http://www.example.org/examples/example1.ecore", "http://www.example.org/examples/example.ecore");
			assertNoResourceErrors("Unpasting operation", xtextResource);
			assertNoValidationErrors("Unpasting operation", xtextResource);
			assertNoResourceErrors("Unpasting operation", asResource);
			assertNoValidationErrors("Unpasting operation", asResource);
			URI ecoreURI3 = getTestFileURI("test3.ecore");
			@SuppressWarnings("unused") Resource ecoreResource3 = as2ecore(environmentFactory, asResource, ecoreURI3, NO_MESSAGES);
		}
		ocl.dispose();
	}

	public void testEdit_Paste_OCLinEcore() throws Exception {
		OCL ocl = OCL.newInstance(getProjectMap());
		EnvironmentFactory environmentFactory = ocl.getEnvironmentFactory();
		CommonOptions.DEFAULT_DELEGATION_MODE.setDefaultValue(PivotConstants.OCL_DELEGATE_URI_PIVOT);
		String oldDocument =
				"package example : ex = 'http://www.example.org/examples/example.ecore'\n" +
						"{\n" +
						"	class Example\n" +
						"	{\n" +
						"		attribute name : String[?];\n" +
						"		property children : Example[*] { ordered composes };\n" +
						"	}\n" +
						"}\n";
		String newDocument =
				"import ecore : 'http://www.eclipse.org/emf/2002/Ecore#/';\n" +
						"\n" +
						"package tutorial : tut = 'http://www.eclipse.org/mdt/ocl/oclinecore/tutorial'\n" +
						"{\n" +
						"  class Library\n" +
						"  {\n" +
						"    attribute name : String;\n" +
						"    property loans : Loan[*] { composes };\n" +
						"  }\n" +
						"  \n" +
						"  class Loan\n" +
						"  {\n" +
						"  }\n" +
						"}\n";
		CSResource xtextResource;
		Resource asResource;
		{
			URI ecoreURI1 = getTestFileURI("test1.ecore");
			URI outputURI = getTestFileURI("test.oclinecore");
			xtextResource = ocl.getCSResource(outputURI, oldDocument);
			asResource = cs2as(xtextResource, null);
			@SuppressWarnings("unused") Resource ecoreResource1 = as2ecore(environmentFactory, asResource, ecoreURI1, NO_MESSAGES);
		}
		//
		//	Change NsURI.
		//
		{
			replace(xtextResource, oldDocument.trim(), newDocument.trim());
			assertNoResourceErrors("Pasting operation", xtextResource);
			assertNoValidationErrors("Pasting operation", xtextResource);
			assertNoResourceErrors("Pasting operation", asResource);
			assertNoValidationErrors("Pasting operation", asResource);
			URI ecoreURI2 = getTestFileURI("models2/test2.ecore");
			@SuppressWarnings("unused") Resource ecoreResource2 = as2ecore(environmentFactory, asResource, ecoreURI2, SUPPRESS_VALIDATION);
		}
		//
		//	Revert NsURI.
		//
		{
			replace(xtextResource, newDocument.trim(), oldDocument.trim());
			assertNoResourceErrors("Unpasting operation", xtextResource);
			assertNoValidationErrors("Unpasting operation", xtextResource);
			assertNoResourceErrors("Unpasting operation", asResource);
			assertNoValidationErrors("Unpasting operation", asResource);
			URI ecoreURI3 = getTestFileURI("models2/test3.ecore");
			@SuppressWarnings("unused") Resource ecoreResource3 = as2ecore(environmentFactory, asResource, ecoreURI3, NO_MESSAGES);
		}
		ocl.dispose();
	}

	public void testEdit_Paste_OCLstdlib_541380() throws Exception {
		OCL ocl = OCL.newInstance(getProjectMap());
		EnvironmentFactory environmentFactory = ocl.getEnvironmentFactory();
		CommonOptions.DEFAULT_DELEGATION_MODE.setDefaultValue(PivotConstants.OCL_DELEGATE_URI_PIVOT);
		String testDocument =
				"library lib : lib = 'http://Bug541380.oclstdlib'\n"
				+ "{\n"
				+ "	type Bag(T) : BagType conformsTo Collection(T) {}\n"
				+ "	type Boolean : BooleanType conformsTo OclAny => 'java.lang.Boolean' {}\n"
				+ "	type Class conformsTo OclAny {}	\n"
				+ "	type Collection(T) : CollectionType conformsTo OclAny {}\n"
				+ "	type Integer : PrimitiveType conformsTo Real => 'org.eclipse.ocl.pivot.values.IntegerValue' {}\n"
				+ "	type Map(K,V) : MapType conformsTo OclAny {}\n"
				+ "	type OclAny : AnyType {\n"
	//			+ "		operation \"=\"(object2 : OclAny) : Boolean;\n"
				+ "	}\n"
				+ "	type OclComparable conformsTo OclAny {\n"
				+ "		operation compareTo(that : OclSelf) : Integer[1] => 'org.eclipse.ocl.pivot.library.oclany.OclComparableCompareToOperation';	\n"
				+ "	}\n"
				+ "	type OclElement conformsTo OclAny {}	\n"
				+ "	abstract type OclEnumeration conformsTo OclType {}\n"
				+ "	type OclInvalid : InvalidType conformsTo OclVoid {}\n"
				+ "	type OclLambda conformsTo OclAny {}	\n"
				+ "	type OclSelf : SelfType conformsTo OclAny {}\n"
				+ "	type OclTuple conformsTo OclAny {}	\n"
				+ "	abstract type OclType conformsTo OclElement {}\n"
				+ "	type OclVoid : VoidType conformsTo OclAny {}\n"
				+ "	type OrderedCollection(T) : CollectionType conformsTo Collection(T) {}\n"
				+ "	type OrderedSet(T) : OrderedSetType conformsTo Collection(T) {}\n"
				+ "	type Real : PrimitiveType conformsTo OclAny => 'org.eclipse.ocl.pivot.values.RealValue' {}\n"
				+ "	type Sequence(T) : SequenceType conformsTo Collection(T) {}\n"
				+ "	type Set(T) : SetType conformsTo Collection(T) {}\n"
				+ "	type String : PrimitiveType conformsTo OclAny => 'java.lang.String' {}\n"
				+ "	type UniqueCollection(T) : CollectionType conformsTo Collection(T) {}\n"
				+ "	type UnlimitedNatural : PrimitiveType conformsTo Integer => 'org.eclipse.ocl.pivot.values.UnlimitedNaturalValue' {}\n"
				+ "}\n";
		CSResource xtextResource;
		Resource asResource;
		{
			URI outputURI = getTestFileURI("Bug541380.oclstdlib");
			xtextResource = ocl.getCSResource(outputURI, testDocument);
			asResource = cs2as(xtextResource, null);
//			@SuppressWarnings("unused") Resource ecoreResource1 = as2ecore(ocl, asResource, ecoreURI1, NO_MESSAGES);
			assertNoResourceErrors("Loading operation", xtextResource);
			assertNoValidationErrors("Loading operation", xtextResource);
			assertNoResourceErrors("Loading operation", asResource);
			assertNoValidationErrors("Loading operation", asResource);
			URI ecoreURI1 = getTestFileURI("Bug541380.oclstdlib1.ecore");
			@SuppressWarnings("unused") Resource ecoreResource1 = as2ecore(environmentFactory, asResource, ecoreURI1, NO_MESSAGES);
		}
		//
		//	Change metatype.
		//
		@SuppressWarnings("unused")
		String newDocument = testDocument.replace("type Boolean : PrimitiveType conformsTo", "type BooleanType conformsTo");
		{
			TestCaseAppender.INSTANCE.uninstall();
			replace(xtextResource, "type Boolean : BooleanType conformsTo", "type Boolean conformsTo");
		//	assertNoResourceErrors("Pasting operation", xtextResource);
		//	assertNoValidationErrors("Pasting operation", xtextResource);
		//	assertNoResourceErrors("Pasting operation", asResource);
		//	assertNoValidationErrors("Pasting operation", asResource);
			URI ecoreURI2 = getTestFileURI("Bug541380.oclstdlib2.ecore");
			@SuppressWarnings("unused") Resource ecoreResource2 = as2ecore(environmentFactory, asResource, ecoreURI2, SUPPRESS_VALIDATION);
			TestCaseAppender.INSTANCE.install();
		}
		//
		//	Revert metatype.
		//
		{
			replace(xtextResource, "type Boolean conformsTo", "type Boolean : BooleanType conformsTo");
			assertNoResourceErrors("Unpasting operation", xtextResource);
			assertNoValidationErrors("Unpasting operation", xtextResource);
			assertNoResourceErrors("Unpasting operation", asResource);
			assertNoValidationErrors("Unpasting operation", asResource);
			URI ecoreURI3 = getTestFileURI("Bug541380.oclstdlib3.ecore");
			@SuppressWarnings("unused") Resource ecoreResource3 = as2ecore(environmentFactory, asResource, ecoreURI3, NO_MESSAGES);
		}
		ocl.dispose();
	}

	public void testEdit_Paste_operation_394057() throws Exception {
		OCL ocl = OCL.newInstance(getProjectMap());
		EnvironmentFactory environmentFactory = ocl.getEnvironmentFactory();
		//		OCLDelegateDomain.initialize(null);
		//		OCLDelegateDomain.initialize(null, OCLConstants.OCL_DELEGATE_URI);
		CommonOptions.DEFAULT_DELEGATION_MODE.setDefaultValue(PivotConstants.OCL_DELEGATE_URI_PIVOT);
		String testDocument =
				"package tutorial : tuttut = 'http://www.eclipse.org/mdt/ocl/oclinecore/tutorial'\n" +
						"{\n" +
						"	class Library\n" +
						"	{\n" +
						"		property books#library : Book[*] { composes };\n" +
						"		/*$$*/\n" +
						"	}\n" +
						"	class Book\n" +
						"	{\n" +
						"		attribute name : String;\n" +
						"		property library#books : Library[?];\n" +
						"	}\n" +
						"}\n";
		String pasteText =
				"operation packageLabels(packages : Book[*] { !unique, ordered }) : String\n" +
						"{\n" +
						"	body: packages?->sortedBy(name)?->iterate(p; acc : String = '' | acc + ' ' + p.name);\n" +		// FIXME should not need second ?->
						"}";
		CSResource xtextResource;
		Resource asResource;
		{
			URI ecoreURI1 = getTestFileURI("test1.ecore");
			URI outputURI = getTestFileURI("test.oclinecore");
			xtextResource = ocl.getCSResource(outputURI, testDocument);
			asResource = cs2as(xtextResource, null);
			@SuppressWarnings("unused") Resource ecoreResource1 = as2ecore(environmentFactory, asResource, ecoreURI1, NO_MESSAGES);
		}
		//
		//	Change "/*$$*/" to "pasteText".
		//
		{
			replace(xtextResource, "/*$$*/", pasteText);
			assertNoResourceErrors("Pasting operation", xtextResource);
			assertNoValidationErrors("Pasting operation", xtextResource);
			assertNoResourceErrors("Pasting operation", asResource);
			assertNoValidationErrors("Pasting operation", asResource);
			URI ecoreURI2 = getTestFileURI("test2.ecore");
			@SuppressWarnings("unused") Resource ecoreResource2 = as2ecore(environmentFactory, asResource, ecoreURI2, SUPPRESS_VALIDATION);
		}
		//
		//	Change "pasteText" back to "/*$$*/".
		//
		{
			replace(xtextResource, pasteText, "/*$$*/");
			assertNoResourceErrors("Unpasting operation", xtextResource);
			assertNoValidationErrors("Unpasting operation", xtextResource);
			assertNoResourceErrors("Unpasting operation", asResource);
			assertNoValidationErrors("Unpasting operation", asResource);
			URI ecoreURI3 = getTestFileURI("test3.ecore");
			@SuppressWarnings("unused") Resource ecoreResource3 = as2ecore(environmentFactory, asResource, ecoreURI3, NO_MESSAGES);
		}
		ocl.dispose();
	}

	public void testEdit_Paste_473249() throws Exception {
//		BaseLinkingService.DEBUG_RETRY.setState(true);
		UMLStandaloneSetup.init();
		OCL ocl = OCL.newInstance(getProjectMap());
		EnvironmentFactory environmentFactory = ocl.getEnvironmentFactory();
		//		OCLDelegateDomain.initialize(null);
		//		OCLDelegateDomain.initialize(null, OCLConstants.OCL_DELEGATE_URI);
		CommonOptions.DEFAULT_DELEGATION_MODE.setDefaultValue(PivotConstants.OCL_DELEGATE_URI_PIVOT);
		String testDocument =
				"import ecore : 'http://www.eclipse.org/emf/2002/Ecore' ;\n" +
						"import uml : 'http://www.eclipse.org/uml2/5.0.0/UML' ;\n" +
						"\n" +
						"package UML2EcoreMapping : u2e = 'http://www.eclipse.org/ocl/2012/UML2EcoreMapping'\n" +
						"{\n" +
						"	class CreateOperation extends OperationMapping\n" +
						"	{\n" +
						"		operation label() : String[1]\n" +
						"		{\n" +
						"			body: nameLabel(newName) + ' <= ' + 'operationLabels(oldOperations)';\n" +
						"		}\n" +
						"		attribute newName : String[1];\n" +
						"	}\n" +
						"	class CreatePackage extends PackageMapping\n" +
						"	{\n" +
						"		operation label() : String[1]\n" +
						"		{\n" +
						"			body: nameLabel(newName) + ' <= ' + packageLabels(oldPackages);\n" +
						"		}\n" +
						"		attribute newName : String[1];\n" +
						"		attribute nsPrefix : String[1];\n" +
						"		attribute nsURI : String[1];\n" +
						"		attribute isASmetamodel : Boolean[1] = 'false';\n" +
						"		attribute ecoreFileStem : String[?];\n" +
						"		property typeMappings : TypeMapping[*] { ordered composes };\n" +
						"	}\n" +
						"	class CreateProperty extends PropertyMapping\n" +
						"	{\n" +
						"		operation label() : String[1]\n" +
						"		{\n" +
						"			body: nameLabel(newName) + ' <= ' + propertyLabels(oldProperties);\n" +
						"		}\n" +
						"		attribute newName : String[1];\n" +
						"		attribute newIsDerived : ecore::EBooleanObject[?];\n" +
						"		attribute newIsNullFree : ecore::EBooleanObject[?];\n" +
						"		attribute newIsResolveProxies : ecore::EBooleanObject[?];\n" +
						"		attribute newIsTransient : ecore::EBooleanObject[?];\n" +
						"		attribute newIsUnsettable : ecore::EBooleanObject[?];\n" +
						"		attribute newIsVolatile : ecore::EBooleanObject[?];\n" +
						"		attribute newLowerBound : ecore::EIntegerObject[?];\n" +
						"		property opposite : uml::Property[?];\n" +
						"	}\n" +
						"	class CreateType extends TypeMapping\n" +
						"	{\n" +
						"		operation label() : String[1]\n" +
						"		{\n" +
						"			body: nameLabel(newName) + ' <= ' + typeLabels(oldTypes);\n" +
						"		}\n" +
						"		attribute newName : String[1];\n" +
						"		attribute newInstanceTypeName : String[?];\n" +
						"		attribute newIsSerializable : ecore::EBooleanObject[?];\n" +
						"		property orderedSuperTypes : uml::Type[*] { ordered };\n" +
						"		property excludeProperties : uml::Property[*] { ordered };\n" +
						"		property includeProperties : uml::Property[*] { ordered };\n" +
						"		property excludeTypes : uml::Type[*] { ordered };\n" +
						"		property operationMappings : OperationMapping[*] { ordered composes };\n" +
						"		property propertyMappings : PropertyMapping[*] { ordered composes };\n" +
						"		invariant UniqueCreatePropertyNames: propertyMappings->selectByKind(CreateProperty)->isUnique(newName);\n" +
						"	}\n" +
						"	class DeleteOperation extends OperationMapping\n" +
						"	{\n" +
						"		operation label() : String[1]\n" +
						"		{\n" +
						"			body: nameLabel(oldName) + ' <= ' + operationLabels(oldOperations);\n" +
						"		}\n" +
						"		attribute oldName : String[1];\n" +
						"	}\n" +
						"	class DeletePackage extends PackageMapping\n" +
						"	{\n" +
						"		operation label() : String[1]\n" +
						"		{\n" +
						"			body: oldName;\n" +
						"		}\n" +
						"		attribute oldName : String[1];\n" +
						"		property deleteTypes : DeleteType[*] { ordered composes };\n" +
						"	}\n" +
						"	class DeleteProperty extends PropertyMapping\n" +
						"	{\n" +
						"		operation label() : String[1]\n" +
						"		{\n" +
						"			body: nameLabel(oldName) + ' <= ' + propertyLabels(oldProperties);\n" +
						"		}\n" +
						"		attribute oldName : String[1];\n" +
						"	}\n" +
						"	class DeleteType extends TypeMapping\n" +
						"	{\n" +
						"		operation label() : String[1]\n" +
						"		{\n" +
						"			body: nameLabel(oldName) + ' <= ' + typeLabels(oldTypes);\n" +
						"		}\n" +
						"		attribute oldName : String[1];\n" +
						"		property operationMappings : OperationMapping[*] { ordered composes };\n" +
						"		property propertyMappings : PropertyMapping[*] { ordered composes };\n" +
						"	}\n" +
						"	abstract class Mapping\n" +
						"	{\n" +
						"		operation label() : String[1]\n" +
						"		{\n" +
						"			body: 'default-label';\n" +
						"		}\n" +
						"		operation nameLabel(name : String[?]) : String[1]\n" +
						"		{\n" +
						"			body: if name = null then 'null' else name endif;\n" +
						"		}\n" +
						"		operation operationLabels(operations : uml::Operation[*] { ordered }) : String[1]\n" +
						"		{\n" +
						"			body: operations->sortedBy(nameLabel(name))->iterate(p; acc : String = '' | let type : uml::Type = if p.class <> null then p.class else p.interface endif in acc + ' ' + nameLabel(p.class.package.name) + '::' + nameLabel(p.class.name) + '::' + nameLabel(p.name));\n" +
						"		}\n" +
						"		operation packageLabel(p : uml::Package[?]) : String[1]\n" +
						"		{\n" +
						"			body: if p <> null then nameLabel(p.name) else 'null' endif;\n" +
						"		}\n" +
						"		operation packageLabels(packages : uml::Package[*] { ordered }) : String[1]\n" +
						"		{\n" +
						"			body: packages->sortedBy(nameLabel(name))->iterate(p; acc : String = '' | acc + ' ' + packageLabel(p));\n" +
						"		}\n" +
						"		operation propertyLabel(p : uml::Property[?]) : String[1]\n" +
						"		{\n" +
						"			body: if p <> null then let t = if p.class <> null then p.class else p.association endif in typeLabel(t) + '::' + nameLabel(p.name) else 'null' endif;\n" +
						"		}\n" +
						"		operation propertyLabels(properties : uml::Property[*] { ordered }) : String[1]\n" +
						"		{\n" +
						"			body: properties->sortedBy(nameLabel(name))->iterate(p; acc : String = '' | acc + ' ' + propertyLabel(p));\n" +
						"		}\n" +
						"		operation typeLabel(t : uml::Type[?]) : String[1]\n" +
						"		{\n" +
						"			body: if t <> null then packageLabel(t.package) + '::' + nameLabel(t.name) else 'null' endif;\n" +
						"		}\n" +
						"		operation typeLabels(types : uml::Type[*] { ordered }) : String[1]\n" +
						"		{\n" +
						"			body: types->sortedBy(nameLabel(name))->iterate(t; acc : String = '' | acc + ' ' + typeLabel(t));\n" +
						"		}\n" +
						"		attribute label : String[1] { transient }\n" +
						"		{\n" +
						"			initial: label();\n" +
						"		}\n" +
						"	}\n" +
						"	class Mappings\n" +
						"	{\n" +
						"		property mappings : Mapping[*] { ordered composes };\n" +
						"	}\n" +
						"	abstract class PackageMapping extends Mapping\n" +
						"	{\n" +
						"		operation label() : String[1]\n" +
						"		{\n" +
						"			body: packageLabels(oldPackages);\n" +
						"		}\n" +
						"		property oldPackages : uml::Package[*] { ordered };\n" +
						"	}\n" +
						"	abstract class OperationMapping extends Mapping\n" +
						"	{\n" +
						"		property oldOperations : uml::Operation[*] { ordered };\n" +
						"	}\n" +
						"	abstract class PropertyMapping extends Mapping\n" +
						"	{\n" +
						"		property oldProperties : uml::Property[*] { ordered };\n" +
						"	}\n" +
						"	abstract class TypeMapping extends Mapping\n" +
						"	{\n" +
						"		operation label() : String[1]\n" +
						"		{\n" +
						"			body: typeLabels(oldTypes);\n" +
						"		}\n" +
						"		property oldTypes : uml::Type[*] { ordered };\n" +
						"	}\n" +
						"}";
		CSResource xtextResource;
		//		System.out.println("------Load--------------------------------");
		Resource asResource;
		{
			URI ecoreURI1 = getTestFileURI("Bug473249a.ecore");
			URI outputURI = getTestFileURI("Bug473249.oclinecore");
			xtextResource = ocl.getCSResource(outputURI, testDocument);
			//			System.out.println("------cs2as--------------------------------");
			asResource = cs2as(xtextResource, null);
			//			System.out.println("------as2ecore--------------------------------");
			@SuppressWarnings("unused") Resource ecoreResource1 = as2ecore(environmentFactory, asResource, ecoreURI1, NO_MESSAGES);
		}
		//
		//	Change "/*$$*/" to "pasteText".
		//
		{
			//			System.out.println("------replace--------------------------------");
			replace(xtextResource, "'operationLabels(oldOperations)'", "operationLabels(oldOperations)");
			assertNoResourceErrors("Pasting operation", xtextResource);
			//			System.out.println("------validate--------------------------------");
			assertNoValidationErrors("Pasting operation", xtextResource);
			assertNoResourceErrors("Pasting operation", asResource);
			assertNoValidationErrors("Pasting operation", asResource);
			URI ecoreURI2 = getTestFileURI("Bug473249b.ecore");
			//			System.out.println("------as2ecore--------------------------------");
			@SuppressWarnings("unused") Resource ecoreResource2 = as2ecore(environmentFactory, asResource, ecoreURI2, SUPPRESS_VALIDATION);
		}
		ocl.dispose();
	}

	public void testEdit_Reclass_ecore_383285() throws Exception {
		String testDocument_class =
				"package p1 : p2 = 'p3' {\n" +
						"    class C : 'java.lang.Object';\n" +
						"}\n";
		String testDocument_datatype =
				"package p1 : p2 = 'p3' {\n" +
						"    datatype C : 'java.lang.Object';\n" +
						"}\n";
		URI ecoreURI_class = getTestFileURI("test-class.ecore");
		URI ecoreURI_datatype = getTestFileURI("test-datatype.ecore");
		OCL ocl_class = OCL.newInstance(getProjectMap());
		Resource ecoreResource_class = getEcoreFromCS(ocl_class, testDocument_class, ecoreURI_class);
		ThreadLocalExecutor.resetEnvironmentFactory();
		OCL ocl_datatype = OCL.newInstance(getProjectMap());
		Resource ecoreResource_datatype = getEcoreFromCS(ocl_datatype, testDocument_datatype, ecoreURI_datatype);
		ThreadLocalExecutor.resetEnvironmentFactory();
		OCL ocl = OCL.newInstance(getProjectMap());
		EnvironmentFactory environmentFactory = ocl.getEnvironmentFactory();
		CSResource xtextResource;
		Resource asResource;
		{
			URI ecoreURI1 = getTestFileURI("test1.ecore");
			URI outputURI = getTestFileURI("test.oclinecore");
			xtextResource = ocl.getCSResource(outputURI, testDocument_class);
			asResource = cs2as(xtextResource, null);
			Resource ecoreResource1 = as2ecore(environmentFactory, asResource, ecoreURI1, NO_MESSAGES);
			TestUtil.assertSameModel(ecoreResource_class, ecoreResource1);
		}
		//
		//	Change "class" to "datatype" and see EClass change to EDataType.
		//
		{
			replace(xtextResource, "class", "datatype");
			assertNoResourceErrors("Reclassing to datatype", xtextResource);
			URI ecoreURI2 = getTestFileURI("test2.ecore");
			Resource ecoreResource2 = as2ecore(environmentFactory, asResource, ecoreURI2, SUPPRESS_VALIDATION);
			TestUtil.assertSameModel(ecoreResource_datatype, ecoreResource2);
		}
		//
		//	Change "datatype" back to "class" and see EDataType change back to EClass.
		//
		{
			replace(xtextResource, "datatype", "class");
			assertNoResourceErrors("Reclassing to class", xtextResource);
			URI ecoreURI3 = getTestFileURI("test3.ecore");
			Resource ecoreResource3 = as2ecore(environmentFactory, asResource, ecoreURI3, NO_MESSAGES);
			TestUtil.assertSameModel(ecoreResource_class, ecoreResource3);
		}
		ocl_class.dispose();
		ocl_datatype.dispose();
		ocl.dispose();
	}

	public void testEdit_Comments() throws Exception {
		String testDocument_uncommented =
				"package p1 : p2 = 'p3' {\n" +
						"    class C : 'java.lang.Object';\n" +
						"}\n";
		String testDocument_commented =
				"package p1 : p2 = 'p3' {\n" +
						"    /* a comment */\n" +
						"    class C : 'java.lang.Object';\n" +
						"}\n";
		String testDocument_recommented =
				"package p1 : p2 = 'p3' {\n" +
						"    /*\n" +
						"	  *	yet \n" +
						"	  *	another \n" +
						"	  *	comment\n" +
						"	  */\n" +
						"    class C : 'java.lang.Object';\n" +
						"}\n";
		URI ecoreURI_uncommented = getTestFileURI("test-uncommented.ecore");
		URI ecoreURI_commented = getTestFileURI("test-commented.ecore");
		URI ecoreURI_recommented = getTestFileURI("test-recommented.ecore");
		OCL ocl_uncommented = OCL.newInstance(getProjectMap());
		Resource ecoreResource_uncommented = getEcoreFromCS(ocl_uncommented, testDocument_uncommented, ecoreURI_uncommented);
		ThreadLocalExecutor.resetEnvironmentFactory();
		OCL ocl_commented = OCL.newInstance(getProjectMap());
		Resource ecoreResource_commented = getEcoreFromCS(ocl_commented, testDocument_commented, ecoreURI_commented);
		ThreadLocalExecutor.resetEnvironmentFactory();
		OCL ocl_recommented = OCL.newInstance(getProjectMap());
		Resource ecoreResource_recommented = getEcoreFromCS(ocl_recommented, testDocument_recommented, ecoreURI_recommented);
		ThreadLocalExecutor.resetEnvironmentFactory();
		assertHasComments(ecoreResource_uncommented, new @NonNull String @NonNull []{});
		assertHasComments(ecoreResource_commented, new @NonNull String @NonNull []{"a comment"});
		assertHasComments(ecoreResource_recommented, new @NonNull String @NonNull []{"yet\nanother\ncomment"});
		OCL ocl = OCL.newInstance(getProjectMap());
		EnvironmentFactory environmentFactory = ocl.getEnvironmentFactory();
		CSResource xtextResource;
		Resource asResource;
		{
			URI ecoreURI1 = getTestFileURI("test1.ecore");
			URI outputURI = getTestFileURI("test.oclinecore");
			xtextResource = ocl.getCSResource(outputURI, testDocument_uncommented);
			asResource = cs2as(xtextResource, null);
			Resource ecoreResource1 = as2ecore(environmentFactory, asResource, ecoreURI1, NO_MESSAGES);
			TestUtil.assertSameModel(ecoreResource_uncommented, ecoreResource1);
		}
		//
		//	Change "class" to "/* a comment */class".
		//
		{
			replace(xtextResource, "class", "/* a comment */class");
			assertNoResourceErrors("Adding comment", xtextResource);
			URI ecoreURI2 = getTestFileURI("test2.ecore");
			Resource ecoreResource2 = as2ecore(environmentFactory, asResource, ecoreURI2, SUPPRESS_VALIDATION);
			TestUtil.assertSameModel(ecoreResource_commented, ecoreResource2);
		}
		//
		//	Change "/* a comment */" to "/* yet\n* another\n * comment */".
		//
		{
			replace(xtextResource, "/* a comment */", "/* yet\n* another\n * comment */");
			assertNoResourceErrors("Changing comment", xtextResource);
			URI ecoreURI3 = getTestFileURI("test3.ecore");
			Resource ecoreResource3 = as2ecore(environmentFactory, asResource, ecoreURI3, NO_MESSAGES);
			TestUtil.assertSameModel(ecoreResource_recommented, ecoreResource3);
		}
		//
		//	Change "/* yet\n* another\n * comment */" back to nothing.
		//
		{
			replace(xtextResource, "/* yet\n* another\n * comment */", "");
			assertNoResourceErrors("Removing comment", xtextResource);
			URI ecoreURI4 = getTestFileURI("test4.ecore");
			Resource ecoreResource4 = as2ecore(environmentFactory, asResource, ecoreURI4, NO_MESSAGES);
			TestUtil.assertSameModel(ecoreResource_uncommented, ecoreResource4);
		}
		ocl_uncommented.dispose();
		ocl_commented.dispose();
		ocl_recommented.dispose();
		ocl.dispose();
	}

	public void testEdit_Refresh_ecore_382230() throws Exception {
		//		OCLDelegateDomain.initialize(null);
		//		OCLDelegateDomain.initialize(null, OCLConstants.OCL_DELEGATE_URI);
		CommonOptions.DEFAULT_DELEGATION_MODE.setDefaultValue(PivotConstants.OCL_DELEGATE_URI_PIVOT);
		OCL ocl0 = OCL.newInstance(getProjectMap());
		String testDocument =
				"package tutorial : tuttut = 'http://www.eclipse.org/mdt/ocl/oclinecore/tutorial'\n" +
						"{\n" +
						"	class Library\n" +
						"	{\n" +
						"		property books#library : Book[*] { composes };\n" +
						"	}\n" +
						"	class Book\n" +
						"	{\n" +
						"		property library#books : Library[?];\n" +
						"		property name : String;\n" +
						"		invariant NameNotEmpty: name->notEmpty();\n" +
						"	}\n" +
						"}\n";
		URI ecoreURI = createEcoreFile(ocl0, "RefreshTest.ecore", testDocument, true);
		ocl0.dispose();
		//
		//	Load and instrument test document
		//
		OCLInternal ocl1 = OCLInternal.newInstance(getProjectMap(), null);
		XMLResource ecoreResource = (XMLResource) ClassUtil.nonNullEMF(ocl1.getResourceSet().getResource(ecoreURI, true));
		assertNoResourceErrors("Ecore load", ecoreResource);
		assertNoValidationErrors("Ecore load", ecoreResource);
		ASResource asResource = ocl1.ecore2as(ecoreResource);
		assertNoResourceErrors("Pivot load", asResource);
		assertNoValidationErrors("Pivot load", asResource);
		Set<EObject> loadPivotContent = new HashSet<EObject>();
		for (TreeIterator<EObject> tit = asResource.getAllContents(); tit.hasNext(); ) {
			EObject eObject = tit.next();
			//			System.out.println(ClassUtil.debugSimpleName(eObject));
			loadPivotContent.add(eObject);
		}
		{
			BaseCSResource xtextResource1 = (BaseCSResource) ocl1.getResourceSet().createResource(ecoreURI.appendFileExtension("oclinecore"), OCLinEcoreCSPackage.eCONTENT_TYPE);
			xtextResource1.setURI(ecoreURI);
			ocl1.as2cs(asResource, xtextResource1);
			assertNoResourceErrors("Xtext load", xtextResource1);
			assertNoValidationErrors("Xtext load", xtextResource1);
			ListBasedDiagnosticConsumer diagnosticsConsumer1 = new ListBasedDiagnosticConsumer();
			xtextResource1.update(diagnosticsConsumer1);
			Set<EObject> parsePivotContent = new HashSet<EObject>();
			for (TreeIterator<EObject> tit = asResource.getAllContents(); tit.hasNext(); ) {
				EObject eObject = tit.next();
				//				System.out.println(ClassUtil.debugSimpleName(eObject));
				parsePivotContent.add(eObject);
			}
			assertEquals(loadPivotContent.size(), parsePivotContent.size());
			assertEquals(loadPivotContent, parsePivotContent);
		}

		//
		//	Reload and re-instrument test document
		//
		StringWriter writer = new StringWriter();
		OutputStream outputStream = new URIConverter.WriteableOutputStream(writer, "UTF-8");
		ecoreResource.save(outputStream, XMIUtil.createSaveOptions(ecoreResource));
		ecoreResource.unload();
		InputStream inputStream = new URIConverter.ReadableInputStream(writer.toString().replace("tuttut",  "tut"), "UTF-8");
		ecoreResource.load(inputStream, null);
		assertNoResourceErrors("Ecore reload", ecoreResource);
		assertNoValidationErrors("Ecore reload", ecoreResource);
		Ecore2AS ecore2as = Ecore2AS.getAdapter(ecoreResource, ocl1.getEnvironmentFactory());
		ecore2as.update(asResource, ClassUtil.nonNullEMF(ecoreResource.getContents()));
		assertNoResourceErrors("Pivot reload", ecoreResource);
		assertNoValidationErrors("Pivot reload", ecoreResource);
		Set<EObject> newPivotContent = new HashSet<EObject>();
		for (TreeIterator<EObject> tit = asResource.getAllContents(); tit.hasNext(); ) {
			EObject eObject = tit.next();
			//			System.out.println(PivotUtil.debugSimpleName(eObject));
			newPivotContent.add(eObject);
		}
		assertEquals(loadPivotContent.size(), newPivotContent.size());
		assertEquals(loadPivotContent, newPivotContent);
		{
			BaseCSResource xtextResource2 = (BaseCSResource) ocl1.getResourceSet().createResource(ecoreURI.appendFileExtension("oclinecore"), OCLinEcoreCSPackage.eCONTENT_TYPE);
			xtextResource2.setURI(ecoreURI);
			ocl1.as2cs(asResource, xtextResource2);
			assertNoResourceErrors("Xtext load", xtextResource2);
			assertNoValidationErrors("Xtext load", xtextResource2);
			ListBasedDiagnosticConsumer diagnosticsConsumer2 = new ListBasedDiagnosticConsumer();
			xtextResource2.update(diagnosticsConsumer2);
			Set<EObject> reparsePivotContent = new HashSet<EObject>();
			for (TreeIterator<EObject> tit = asResource.getAllContents(); tit.hasNext(); ) {
				EObject eObject = tit.next();
				//				System.out.println(PivotUtil.debugSimpleName(eObject));
				reparsePivotContent.add(eObject);
			}
			assertEquals(loadPivotContent.size(), reparsePivotContent.size());
			assertEquals(loadPivotContent, reparsePivotContent);
		}
	}

	public void testEdit_Rename_ecore() throws Exception {
		String testDocument =
				"module m1 \n" +
						"package p1 : p2 = 'p3' {\n" +
						"}\n";
		URI ecoreURI0 = getTestFileURI("test0.ecore");
		OCL ocl1 = OCL.newInstance(getProjectMap());
		Resource ecoreResource0 = getEcoreFromCS(ocl1, testDocument, ecoreURI0);
		ThreadLocalExecutor.resetEnvironmentFactory();
		OCL ocl = OCL.newInstance(getProjectMap());
		EnvironmentFactory environmentFactory = ocl.getEnvironmentFactory();
		CSResource xtextResource;
		Resource asResource;
		{
			URI ecoreURI1 = getTestFileURI("test1.ecore");
			URI outputURI = getTestFileURI("test.oclinecore");
			xtextResource = ocl.getCSResource(outputURI, testDocument);
			asResource = cs2as(xtextResource, null);
			Resource ecoreResource1 = as2ecore(environmentFactory, asResource, ecoreURI1, NO_MESSAGES);
			TestUtil.assertSameModel(ecoreResource0, ecoreResource1);
		}
		//
		//	Inserting a leading space has no Ecore effect.
		//
		{
			xtextResource.update(0, 0, " ");
			assertNoResourceErrors("Adding space", xtextResource);
			URI ecoreURI2 = getTestFileURI("test2.ecore");
			Resource ecoreResource2 = as2ecore(environmentFactory, asResource, ecoreURI2, NO_MESSAGES);
			TestUtil.assertSameModel(ecoreResource0, ecoreResource2);
		}
		//
		//	Deleting the leading space has no Ecore effect.
		//
		{
			xtextResource.update(0, 1, "");
			assertNoResourceErrors("Deleting space", xtextResource);
			URI ecoreURI3 = getTestFileURI("test3.ecore");
			Resource ecoreResource3 = as2ecore(environmentFactory, asResource, ecoreURI3, NO_MESSAGES);
			TestUtil.assertSameModel(ecoreResource0, ecoreResource3);
		}
		//
		//	Changing "p1" to "pkg" renames the package.
		//
		{
			replace(xtextResource, "p1", "pkg");
			assertNoResourceErrors("Renaming", xtextResource);
			URI ecoreURI4 = getTestFileURI("test4.ecore");
			Resource ecoreResource4 = as2ecore(environmentFactory, asResource, ecoreURI4, NO_MESSAGES);
			((EPackage)ecoreResource0.getContents().get(0)).setName("pkg");
			TestUtil.assertSameModel(ecoreResource0, ecoreResource4);
		}
		ocl1.dispose();
		ocl.dispose();
	}

	public void testEdit_Rename_Restore_ecore() throws Exception {
		String testDocument =
				"package TestPackage : tp = 'TestPackage'\n" +
						"{\n" +
						"	class TestClass1 {\n" +
						"		property testProperty1 : Integer;\n" +
						"		operation testOperation(i : Integer) : Integer;\n" +
						"		invariant testInvariant: 1 = 0;\n" +
						"	}\n" +
						"	class TestClass2 {\n" +
						"		property testProperty2 : TestClass1[1];\n" +
						"		property testProperty3 : Integer[*];\n" +
						"		invariant testInvariant: testProperty2.testProperty1 = testProperty2.testOperation(123456);\n" +
						"	}\n" +
						"}\n";
		URI ecoreURI0 = getTestFileURI("test0.ecore");
		OCL ocl1 = OCL.newInstance(getProjectMap());
		Resource ecoreResource0 = getEcoreFromCS(ocl1, testDocument, ecoreURI0);
		ThreadLocalExecutor.resetEnvironmentFactory();
		OCLInternal ocl = OCLInternal.newInstance(getProjectMap(), null);
		EnvironmentFactoryInternal environmentFactory = ocl.getEnvironmentFactory();
		URI ecoreURI1 = getTestFileURI("test1.ecore");
		URI outputURI = getTestFileURI("test.oclinecore");
		CSResource xtextResource = ClassUtil.nonNullState(ocl.getCSResource(outputURI, testDocument));
		Resource asResource = cs2as(xtextResource, null);
		{
			Resource ecoreResource1 = as2ecore(environmentFactory, asResource, ecoreURI1, NO_MESSAGES);
			TestUtil.assertSameModel(ecoreResource0, ecoreResource1);
		}
		Type pivotTestClass1 = ClassUtil.nonNullState(ocl.getMetamodelManager().getPrimaryType("TestPackage", "TestClass1"));
		//
		//	Changing "TestClass1" to "Testing" renames a type and breaks the invariant.
		//
		doRename(environmentFactory, xtextResource, asResource, "TestClass1", "Testing",
			//			ClassUtil.bind(OCLMessages.Unresolved_ERROR_, "Type", pivotTestClass1.getName()),
			getMessages(StringUtil.bind(PivotMessagesInternal.UnresolvedType_ERROR_, "", pivotTestClass1.getName()),
				StringUtil.bind(PivotMessagesInternal.UnresolvedProperty_ERROR_, "OclInvalid", "testProperty1"),
				StringUtil.bind(PivotMessagesInternal.UnresolvedOperationCall_ERROR_, "OclInvalid", "testOperation", "123456")),
			SUPPRESS_VALIDATION);
		//
		//	Changing "Testing" back to "TestClass1" restores the type and the invariant.
		//
		TestUtil.assertSameModel(ecoreResource0, doRename(environmentFactory, xtextResource, asResource, "Testing", "TestClass1", NO_MESSAGES, NO_MESSAGES));
		pivotTestClass1 = ocl.getMetamodelManager().getPrimaryType("TestPackage", "TestClass1");
		//
		//	Changing "testProperty1" to "tProperty" renames the property and breaks the invariant.
		//
		doRename(environmentFactory, xtextResource, asResource, "testProperty1", "tProperty",
			getMessages(StringUtil.bind(PivotMessagesInternal.UnresolvedProperty_ERROR_, pivotTestClass1 + "", "testProperty1")),
			SUPPRESS_VALIDATION);
		//
		//	Changing "tProperty" back to "testProperty" restores the property and the invariant.
		//
		TestUtil.assertSameModel(ecoreResource0, doRename(environmentFactory, xtextResource, asResource, "tProperty", "testProperty1", NO_MESSAGES, NO_MESSAGES));
		//
		//	Changing "testOperation" to "tOperation" renames the operation and breaks the invariant.
		//
		doRename(environmentFactory, xtextResource, asResource, "testOperation", "tOperation",
			getMessages(StringUtil.bind(PivotMessagesInternal.UnresolvedOperationCall_ERROR_, pivotTestClass1 + "", "testOperation", "123456")),
			SUPPRESS_VALIDATION);
		//
		//	Changing "tOperation" back to "testOperation" restores the operation and the invariant.
		//
		TestUtil.assertSameModel(ecoreResource0, doRename(environmentFactory, xtextResource, asResource, "tOperation", "testOperation", NO_MESSAGES, NO_MESSAGES));
		//
		//	Changing "testOperation(i : Integer)" to "testOperation()" mismatches the operation signature and breaks the invariant.
		//
		doRename(environmentFactory, xtextResource, asResource, "testOperation(i : Integer)", "testOperation()",
			getMessages(StringUtil.bind(PivotMessagesInternal.UnresolvedOperationCall_ERROR_, pivotTestClass1 + "", "testOperation", "123456")),
			SUPPRESS_VALIDATION);
		//
		//	Changing "testOperation()" back to "testOperation(i : Integer)" restores the operation and the invariant.
		//
		TestUtil.assertSameModel(ecoreResource0, doRename(environmentFactory, xtextResource, asResource, "testOperation()", "testOperation(i : Integer)", NO_MESSAGES, NO_MESSAGES));
		//
		//	Changing "testOperation(i : Integer)" to "testOperation(s : String)" mismatches the operation signature and breaks the invariant.
		//
		doRename(environmentFactory, xtextResource, asResource, "testOperation(i : Integer)", "testOperation(s : String)",
			getMessages(StringUtil.bind(PivotMessagesInternal.UnresolvedOperationCall_ERROR_, pivotTestClass1 + "", "testOperation", "Integer")),
			SUPPRESS_VALIDATION);
		//
		//	Changing "testOperation()" back to "testOperation(i : Integer)" restores the operation and the invariant.
		//
		TestUtil.assertSameModel(ecoreResource0, doRename(environmentFactory, xtextResource, asResource, "testOperation(s : String)", "testOperation(i : Integer)", NO_MESSAGES, NO_MESSAGES));
		//
		ocl1.dispose();
		ocl.dispose();
	}

	public void testEdit_StaleReference_ecore() throws Exception {
		String targetRelease = System.getProperty("targetRelease");
		if (targetRelease != null) {
			System.err.println(getTestName() + " skipped for " + targetRelease + " - parse failure");
			return;
		}
		String testDocument =
				"package TestPackage : tp = 'TestPackage'\n" +
						"{\n" +
						"	class TestClass1 {\n" +
						"		property testProperty1 : Integer;\n" +
						"		operation testOperation() : Integer;\n" +
						"		invariant testInvariant: 1 = 0;\n" +
						"	}\n" +
						"	class TestClass2 {\n" +
						"		property testProperty2 : TestClass1[*];\n" +
						"		invariant testInvariant: testProperty2?->select(testOperation() = testProperty1)->isEmpty();\n" +
						"	}\n" +
						"}\n";
		URI ecoreURI0 = getTestFileURI("test0.ecore");
		//		System.out.println("*************load-reference*********************************************************");
		OCL ocl1 = OCL.newInstance(getProjectMap());
		Resource ecoreResource0 = getEcoreFromCS(ocl1, testDocument, ecoreURI0);
		ThreadLocalExecutor.resetEnvironmentFactory();
		URI ecoreURI1 = getTestFileURI("test1.ecore");
		URI outputURI = getTestFileURI("test.oclinecore");
		OCLInternal ocl = OCLInternal.newInstance(getProjectMap(), null);
		EnvironmentFactoryInternal environmentFactory = ocl.getEnvironmentFactory();
		CSResource xtextResource = ocl.getCSResource(outputURI, testDocument);
		Resource asResource = cs2as(xtextResource, null);
		{
			Resource ecoreResource1 = as2ecore(environmentFactory, asResource, ecoreURI1, NO_MESSAGES);
			TestUtil.assertSameModel(ecoreResource0, ecoreResource1);
		}
		MetamodelManagerInternal metamodelManager = ocl.getMetamodelManager();
		Type pivotTestClass1 = ClassUtil.nonNullState(metamodelManager.getPrimaryType("TestPackage", "TestClass1"));
		String testClassName2 = NameUtil.qualifiedNameFor(metamodelManager.getPrimaryType("TestPackage", "TestClass2"));
		//
		//	Changing "TestClass1" to "Testing" renames a type and breaks the referredProperty/referredOperation.
		//
		String message2 = StringUtil.bind(PivotMessagesInternal.ValidationConstraintIsInvalid_ERROR_, PivotConstantsInternal.INVARIANT_ROLE, testClassName2,
			"\"testProperty2?->select(testOperation() = testProperty1)->isEmpty()\"\n" +
					"	The 'CallExp::TypeIsNotInvalid' constraint is violated for '1_.oclBadOperation()'\n" +
					"	The 'VariableExp::TypeIsNotInvalid' constraint is violated for '1_'\n" +
				"	The 'VariableDeclaration::TypeIsNotInvalid' constraint is violated for '1_ : OclInvalid[1]'");
		doRename(environmentFactory, xtextResource, asResource, "TestClass1", "Testing",
			getMessages(StringUtil.bind(PivotMessagesInternal.UnresolvedType_ERROR_, "", pivotTestClass1.getName())),
			getMessages(message2));
		//
		//	Changing "Testing" back to "TestClass1" restores the type and the referredProperty/referredOperation.
		//
		TestUtil.assertSameModel(ecoreResource0, doRename(environmentFactory, xtextResource, asResource, "Testing", "TestClass1", NO_MESSAGES, NO_MESSAGES));
		pivotTestClass1 = ClassUtil.nonNullState(metamodelManager.getPrimaryType("TestPackage", "TestClass1"));
		//
		//	Changing "TestClass1" to "Testing" renames a type and breaks the referredProperty/referredOperation.
		//
		doRename(environmentFactory, xtextResource, asResource, "TestClass1", "Testing",
			getMessages(StringUtil.bind(PivotMessagesInternal.UnresolvedType_ERROR_, "", pivotTestClass1.getName())),
			getMessages(message2));
		//
		//	Changing "Testing" back to "TestClass1" restores the type and the referredProperty/referredOperation.
		//
		TestUtil.assertSameModel(ecoreResource0, doRename(environmentFactory, xtextResource, asResource, "Testing", "TestClass1", NO_MESSAGES, NO_MESSAGES));
		pivotTestClass1 = metamodelManager.getPrimaryType("TestPackage", "TestClass1");
		//
		ocl1.dispose();
		ocl.dispose();
	}

	public void testEdit_StaleSpecialization() throws Exception {
		OCLInternal ocl = OCLInternal.newInstance(getProjectMap(), null);
		EnvironmentFactoryInternal environmentFactory = ocl.getEnvironmentFactory();
		MetamodelManagerInternal metamodelManager = environmentFactory.getMetamodelManager();
		CompleteModelInternal completeModel = environmentFactory.getCompleteModel();
		String testDocument =
				"import '" + LibraryConstants.STDLIB_URI + "';\n" +
						"library ocl : ocl = '" + LibraryConstants.STDLIB_URI + "' {\n" +
						"type MyType conformsTo OclAny{\n" +
						"operation testFunction() : Boolean;\n" +
						"}\n" +
						"}\n";
		URI outputURI = getTestFileURI("test.oclstdlib");
		ModelContext modelContext = new ModelContext(environmentFactory, outputURI);
		EssentialOCLCSResource xtextResource = (EssentialOCLCSResource) modelContext.createBaseResource(testDocument);
		Resource asResource = cs2as(xtextResource, null);
		assertResourceErrors("Loading input", xtextResource);
		assertNoResourceErrors("Loading input", asResource);
		//
		Type myType = ClassUtil.nonNullState(metamodelManager.getPrimaryType(LibraryConstants.STDLIB_URI, "MyType"));
		SequenceType sequenceType = ocl.getStandardLibrary().getSequenceType();
		CollectionTypeParameters<@NonNull Type> typeParameters = new CollectionTypeParametersImpl<@NonNull Type>(myType, true, null, null);
		CompleteClassInternal sequenceCompleteClass = metamodelManager.getCompleteClass(sequenceType);
		WeakReference<Type> sequenceMyType = new WeakReference<Type>(completeModel.findCollectionType(sequenceCompleteClass, typeParameters));
		assertNull(sequenceMyType.get());
		//
		doRename(environmentFactory, xtextResource, asResource, "Boolean", "Sequence(MyType)", NO_MESSAGES, NO_MESSAGES);
		sequenceMyType = new WeakReference<Type>(completeModel.findCollectionType(sequenceCompleteClass, typeParameters));
		assertNotNull(sequenceMyType.get());
		//
		doRename(environmentFactory, xtextResource, asResource, "Sequence(MyType)", "Set(MyType)", NO_MESSAGES, NO_MESSAGES);
		System.gc();
		sequenceMyType = new WeakReference<Type>(completeModel.findCollectionType(sequenceCompleteClass, typeParameters));
		boolean isNull = debugStateRef(sequenceMyType);
		sequenceMyType = null;
		assertTrue(isNull);
		ocl.dispose();
	}

	public void testEdit_Paste_CompleteOCL() throws Exception {
		OCL ocl = OCL.newInstance(getProjectMap());
		//		OCLDelegateDomain.initialize(null);
		//		OCLDelegateDomain.initialize(null, OCLConstants.OCL_DELEGATE_URI);
		CommonOptions.DEFAULT_DELEGATION_MODE.setDefaultValue(PivotConstants.OCL_DELEGATE_URI_PIVOT);
		String testDocument =
				"package ocl\n" +
						"context IterateExp\n" +
						"inv True: true\n" +
						"/*$$*/\n" +
						"context IteratorExp\n" +
						"def: IsTrue() : Boolean = true\n" +
						"inv True: IsTrue()\n" +
						"endpackage\n";
		String pasteText =
				"context IterateExp\n" +
						"inv False: true\n";
		CSResource xtextResource;
		Resource asResource;
		{
			//			URI ecoreURI1 = getTestFileURI("test1.ecore");
			URI outputURI = getTestFileURI("test.ocl");
			xtextResource = ocl.getCSResource(outputURI, testDocument);
			asResource = cs2as(xtextResource, null);
			assertNoResourceErrors("Loading", xtextResource);
			assertNoValidationErrors("Loading", xtextResource);
			assertNoResourceErrors("Loading", asResource);
			assertNoValidationErrors("Loading", asResource);
			//			@SuppressWarnings("unused") Resource ecoreResource1 = as2ecore(ocl.getEnvironmentFactory(), asResource, ecoreURI1, true);
		}
		//
		//	Change "/*$$*/" to "pasteText".
		//
		{
			replace(xtextResource, "/*$$*/", pasteText);
			assertNoResourceErrors("Pasting operation", xtextResource);
			assertNoValidationErrors("Pasting operation", xtextResource);
			assertNoResourceErrors("Pasting operation", asResource);
			assertNoValidationErrors("Pasting operation", asResource);
			//			URI ecoreURI2 = getTestFileURI("test2.ecore");
			//			@SuppressWarnings("unused") Resource ecoreResource2 = as2ecore(ocl.getEnvironmentFactory(), asResource, ecoreURI2, false);
		}
		//
		//	Change "pasteText" back to "/*$$*/".
		//
		{
			//			replace(xtextResource, pasteText, "/*$$*/");
			//			assertNoResourceErrors("Unpasting operation", xtextResource);
			//			assertNoValidationErrors("Unpasting operation", xtextResource);
			//			assertNoResourceErrors("Unpasting operation", asResource);
			//			assertNoValidationErrors("Unpasting operation", asResource);
			//			URI ecoreURI3 = getTestFileURI("test3.ecore");
			//			@SuppressWarnings("unused") Resource ecoreResource3 = as2ecore(ocl.getEnvironmentFactory(), asResource, ecoreURI3, true);
		}
		ocl.dispose();
	}

	public void testEdit_Paste_OCLstdlib() throws Exception {
		OCL ocl = OCL.newInstance(getProjectMap());
		String goodString = "coercion toUnlimitedNatural";
		String badString = "coer cion toUnlimitedNatural";
		CommonOptions.DEFAULT_DELEGATION_MODE.setDefaultValue(PivotConstants.OCL_DELEGATE_URI_PIVOT);
		CSResource xtextResource;
		Resource asResource;
		{
			@NonNull URI libURI = URI.createPlatformResourceURI("org.eclipse.ocl.pivot/model/OCL-2.5.oclstdlib", true);
			xtextResource = ocl.getCSResource(libURI);
			asResource = cs2as(xtextResource, null);
			assertNoResourceErrors("Loading", xtextResource);
			assertNoValidationErrors("Loading", xtextResource);
			assertNoResourceErrors("Loading", asResource);
			assertNoValidationErrors("Loading", asResource);
			//			@SuppressWarnings("unused") Resource ecoreResource1 = as2ecore(ocl.getEnvironmentFactory(), asResource, ecoreURI1, true);
		}
		//
		//	Change "coercion" to "coer cion" - a catastrophic syntax error
		//
		{
			replace(xtextResource, goodString, badString);
			assertResourceErrors("Pasting operation", xtextResource, "mismatched input 'coer' expecting '}'", "missing EOF at 'type'");
			//			assertNoValidationErrors("Pasting operation", xtextResource);
			assertNoResourceErrors("Pasting operation", asResource);
			assertNoValidationErrors("Pasting operation", asResource);
			//			URI ecoreURI2 = getTestFileURI("test2.ecore");
			//			@SuppressWarnings("unused") Resource ecoreResource2 = as2ecore(ocl.getEnvironmentFactory(), asResource, ecoreURI2, false);
		}
		//
		//	Change "coer cion" back to "coercion".
		//
		{
			replace(xtextResource, badString, goodString);
			assertNoResourceErrors("Unpasting operation", xtextResource);
			assertNoValidationErrors("Unpasting operation", xtextResource);
			assertNoResourceErrors("Unpasting operation", asResource);
			assertNoValidationErrors("Unpasting operation", asResource);
			//			URI ecoreURI3 = getTestFileURI("test3.ecore");
			//			@SuppressWarnings("unused") Resource ecoreResource3 = as2ecore(ocl.getEnvironmentFactory(), asResource, ecoreURI3, true);
		}
		ocl.dispose();
	}
}
