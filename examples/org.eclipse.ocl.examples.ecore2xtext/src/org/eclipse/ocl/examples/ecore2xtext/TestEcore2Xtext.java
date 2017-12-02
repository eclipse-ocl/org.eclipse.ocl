package org.eclipse.ocl.examples.ecore2xtext;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ExtensibleURIConverterImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.xtext.tests.TestUtil;
import org.eclipse.ocl.pivot.internal.resource.StandaloneProjectMap;
import org.eclipse.xtext.util.StringInputStream;
import org.junit.Test;

import lpg.runtime.ILexStream;
import lpg.runtime.IPrsStream;

public class TestEcore2Xtext {

	@Test
	public void test() throws IOException, InterruptedException {
		//		Map<String, URI> platformResourceMap = EcorePlugin.getPlatformResourceMap();
		StandaloneProjectMap standaloneProjectMap = new StandaloneProjectMap(false);
		standaloneProjectMap.initializePlatformResourceMap(true);;
		String testFile = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
				"<ecore:EPackage xmi:version=\"2.0\" xmlns:xmi=\"http://www.omg.org/XMI\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
				"    xmlns:ecore=\"http://www.eclipse.org/emf/2002/Ecore\" name=\"XBNF\" nsURI=\"http://www.eclipse.org/ocl/XBNF\" nsPrefix=\"xbnf\">\n" +
				"  <eAnnotations source=\"http://www.eclipse.org/OCL/Import\">\n" +
				"    <details key=\"ecore\" value=\"http://www.eclipse.org/emf/2002/Ecore\"/>\n" +
				"  </eAnnotations>\n" +
				"  <eAnnotations source=\"http://www.eclipse.org/emf/2002/Ecore\">\n" +
				"    <details key=\"invocationDelegates\" value=\"http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot\"/>\n" +
				"    <details key=\"settingDelegates\" value=\"http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot\"/>\n" +
				"    <details key=\"validationDelegates\" value=\"http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot\"/>\n" +
				"  </eAnnotations>\n" +
				"  <eClassifiers xsi:type=\"ecore:EClass\" name=\"AbstractElement\" abstract=\"true\">\n" +
				"    <eStructuralFeatures xsi:type=\"ecore:EAttribute\" name=\"debug\" eType=\"ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EString\"/>\n" +
				"    <eStructuralFeatures xsi:type=\"ecore:EReference\" name=\"parentRule\" eType=\"#//AbstractRule\"\n" +
				"        resolveProxies=\"false\" eOpposite=\"#//AbstractRule/element\"/>\n" +
				"  </eClassifiers>\n" +
				"  <eClassifiers xsi:type=\"ecore:EClass\" name=\"AbstractRule\">\n" +
				"    <eStructuralFeatures xsi:type=\"ecore:EAttribute\" name=\"name\" lowerBound=\"1\" eType=\"ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EString\"/>\n" +
				"    <eStructuralFeatures xsi:type=\"ecore:EReference\" name=\"element\" lowerBound=\"1\"\n" +
				"        eType=\"#//AbstractElement\" containment=\"true\" eOpposite=\"#//AbstractElement/parentRule\"/>\n" +
				"    <eStructuralFeatures xsi:type=\"ecore:EAttribute\" name=\"debug\" eType=\"ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EString\"/>\n" +
				"    <eStructuralFeatures xsi:type=\"ecore:EAttribute\" name=\"kind\" eType=\"ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EString\"/>\n" +
				"  </eClassifiers>\n" +
				"  <eClassifiers xsi:type=\"ecore:EClass\" name=\"ActionAssignment\" eSuperTypes=\"#//Assignment\">\n" +
				"    <eStructuralFeatures xsi:type=\"ecore:EReference\" name=\"type\" lowerBound=\"1\" eType=\"ecore:EClass http://www.eclipse.org/emf/2002/Ecore#//EClassifier\"/>\n" +
				"  </eClassifiers>\n" +
				"  <eClassifiers xsi:type=\"ecore:EClass\" name=\"Assignment\" abstract=\"true\" eSuperTypes=\"#//AbstractElement\">\n" +
				"    <eStructuralFeatures xsi:type=\"ecore:EReference\" name=\"feature\" lowerBound=\"1\"\n" +
				"        eType=\"ecore:EClass http://www.eclipse.org/emf/2002/Ecore#//EStructuralFeature\"/>\n" +
				"    <eStructuralFeatures xsi:type=\"ecore:EAttribute\" name=\"operator\" lowerBound=\"1\"\n" +
				"        eType=\"ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EString\"/>\n" +
				"  </eClassifiers>\n" +
				"  <eClassifiers xsi:type=\"ecore:EClass\" name=\"CharacterRange\" eSuperTypes=\"#//AbstractElement\">\n" +
				"    <eStructuralFeatures xsi:type=\"ecore:EAttribute\" name=\"left\" lowerBound=\"1\" eType=\"ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EString\"/>\n" +
				"    <eStructuralFeatures xsi:type=\"ecore:EAttribute\" name=\"right\" lowerBound=\"1\" eType=\"ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EString\"/>\n" +
				"  </eClassifiers>\n" +
				"  <eClassifiers xsi:type=\"ecore:EClass\" name=\"Conjunction\" eSuperTypes=\"#//AbstractElement\">\n" +
				"    <eStructuralFeatures xsi:type=\"ecore:EReference\" name=\"elements\" lowerBound=\"1\"\n" +
				"        upperBound=\"-1\" eType=\"#//AbstractElement\" containment=\"true\"/>\n" +
				"  </eClassifiers>\n" +
				"  <eClassifiers xsi:type=\"ecore:EClass\" name=\"Disjunction\" eSuperTypes=\"#//AbstractElement\">\n" +
				"    <eStructuralFeatures xsi:type=\"ecore:EReference\" name=\"conjunctions\" lowerBound=\"1\"\n" +
				"        upperBound=\"-1\" eType=\"#//Conjunction\" containment=\"true\"/>\n" +
				"  </eClassifiers>\n" +
				"  <eClassifiers xsi:type=\"ecore:EClass\" name=\"EOF\" eSuperTypes=\"#//AbstractElement\"/>\n" +
				"  <eClassifiers xsi:type=\"ecore:EClass\" name=\"Epsilon\" eSuperTypes=\"#//AbstractElement\"/>\n" +
				"  <eClassifiers xsi:type=\"ecore:EClass\" name=\"Grammar\">\n" +
				"    <eStructuralFeatures xsi:type=\"ecore:EAttribute\" name=\"name\" lowerBound=\"1\" eType=\"ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EString\"/>\n" +
				"    <eStructuralFeatures xsi:type=\"ecore:EReference\" name=\"syntax\" lowerBound=\"1\"\n" +
				"        eType=\"#//Syntax\" resolveProxies=\"false\" eOpposite=\"#//Syntax/grammars\"/>\n" +
				"    <eStructuralFeatures xsi:type=\"ecore:EReference\" name=\"goals\" ordered=\"false\"\n" +
				"        upperBound=\"-1\" eType=\"#//TypedRule\"/>\n" +
				"    <eStructuralFeatures xsi:type=\"ecore:EReference\" name=\"rules\" ordered=\"false\"\n" +
				"        upperBound=\"-1\" eType=\"#//TypedRule\" containment=\"true\" eOpposite=\"#//TypedRule/grammar\"/>\n" +
				"    <eStructuralFeatures xsi:type=\"ecore:EAttribute\" name=\"debug\" eType=\"ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EString\"/>\n" +
				"  </eClassifiers>\n" +
				"  <eClassifiers xsi:type=\"ecore:EClass\" name=\"Keyword\" eSuperTypes=\"#//AbstractElement\">\n" +
				"    <eStructuralFeatures xsi:type=\"ecore:EAttribute\" name=\"value\" lowerBound=\"1\" eType=\"ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EString\"/>\n" +
				"  </eClassifiers>\n" +
				"  <eClassifiers xsi:type=\"ecore:EClass\" name=\"KeywordAssignment\" eSuperTypes=\"#//Assignment #//Keyword\"/>\n" +
				"  <eClassifiers xsi:type=\"ecore:EClass\" name=\"LexerGrammar\" eSuperTypes=\"#//Grammar\"/>\n" +
				"  <eClassifiers xsi:type=\"ecore:EClass\" name=\"NegatedToken\" eSuperTypes=\"#//AbstractElement\">\n" +
				"    <eStructuralFeatures xsi:type=\"ecore:EReference\" name=\"terminal\" lowerBound=\"1\"\n" +
				"        eType=\"#//AbstractElement\" containment=\"true\"/>\n" +
				"  </eClassifiers>\n" +
				"  <eClassifiers xsi:type=\"ecore:EClass\" name=\"ParserGrammar\" eSuperTypes=\"#//Grammar\"/>\n" +
				"  <eClassifiers xsi:type=\"ecore:EClass\" name=\"ParserRule\" eSuperTypes=\"#//TypedRule\"/>\n" +
				"  <eClassifiers xsi:type=\"ecore:EClass\" name=\"RuleCall\" eSuperTypes=\"#//AbstractElement\">\n" +
				"    <eStructuralFeatures xsi:type=\"ecore:EReference\" name=\"referredRule\" lowerBound=\"1\"\n" +
				"        eType=\"#//AbstractRule\"/>\n" +
				"    <eStructuralFeatures xsi:type=\"ecore:EAttribute\" name=\"name\" eType=\"ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EString\"\n" +
				"        changeable=\"false\" volatile=\"true\" transient=\"true\">\n" +
				"      <eAnnotations source=\"http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot\">\n" +
				"        <details key=\"derivation\" value=\"if referredRule &lt;> null then referredRule.name else '' endif\"/>\n" +
				"      </eAnnotations>\n" +
				"    </eStructuralFeatures>\n" +
				"  </eClassifiers>\n" +
				"  <eClassifiers xsi:type=\"ecore:EClass\" name=\"RuleCallAssignment\" eSuperTypes=\"#//Assignment #//RuleCall\"/>\n" +
				"  <eClassifiers xsi:type=\"ecore:EClass\" name=\"Syntax\">\n" +
				"    <eStructuralFeatures xsi:type=\"ecore:EAttribute\" name=\"name\" lowerBound=\"1\" eType=\"ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EString\"/>\n" +
				"    <eStructuralFeatures xsi:type=\"ecore:EReference\" name=\"grammars\" upperBound=\"-1\"\n" +
				"        eType=\"#//Grammar\" containment=\"true\" eOpposite=\"#//Grammar/syntax\"/>\n" +
				"    <eStructuralFeatures xsi:type=\"ecore:EAttribute\" name=\"debug\" eType=\"ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EString\"/>\n" +
				"  </eClassifiers>\n" +
				"  <eClassifiers xsi:type=\"ecore:EClass\" name=\"TerminalRule\" eSuperTypes=\"#//TypedRule\"/>\n" +
				"  <eClassifiers xsi:type=\"ecore:EClass\" name=\"TypedRule\" abstract=\"true\" eSuperTypes=\"#//AbstractRule\">\n" +
				"    <eStructuralFeatures xsi:type=\"ecore:EReference\" name=\"type\" lowerBound=\"1\" eType=\"ecore:EClass http://www.eclipse.org/emf/2002/Ecore#//EClassifier\"/>\n" +
				"    <eStructuralFeatures xsi:type=\"ecore:EReference\" name=\"subrules\" upperBound=\"-1\"\n" +
				"        eType=\"#//UntypedRule\" containment=\"true\" eOpposite=\"#//UntypedRule/typedRule\"/>\n" +
				"    <eStructuralFeatures xsi:type=\"ecore:EReference\" name=\"grammar\" lowerBound=\"1\"\n" +
				"        eType=\"#//Grammar\" eOpposite=\"#//Grammar/rules\"/>\n" +
				"  </eClassifiers>\n" +
				"  <eClassifiers xsi:type=\"ecore:EClass\" name=\"UntilToken\" eSuperTypes=\"#//AbstractElement\">\n" +
				"    <eStructuralFeatures xsi:type=\"ecore:EReference\" name=\"terminal\" lowerBound=\"1\"\n" +
				"        eType=\"#//AbstractElement\" containment=\"true\"/>\n" +
				"  </eClassifiers>\n" +
				"  <eClassifiers xsi:type=\"ecore:EClass\" name=\"UntypedRule\" eSuperTypes=\"#//AbstractRule\">\n" +
				"    <eStructuralFeatures xsi:type=\"ecore:EReference\" name=\"typedRule\" eType=\"#//TypedRule\"\n" +
				"        eOpposite=\"#//TypedRule/subrules\"/>\n" +
				"  </eClassifiers>\n" +
				"  <eClassifiers xsi:type=\"ecore:EClass\" name=\"Wildcard\" eSuperTypes=\"#//AbstractElement\"/>\n" +
				"</ecore:EPackage>\n" +
				"";
		int repeats = 2;
		XMLResource xmlResource2 = null;
		for (int i = 0; i < repeats; i++) {
			xmlResource2 = parseEcore(standaloneProjectMap, testFile);
		}

		Ecore2XtextParser parser = null;
		XMLResource xmlResource = null;
		for (int i = 0; i < repeats; i++) {
			URI uri = URI.createPlatformResourceURI("org.eclipse.emf.ecore/model/Ecore.ecore", true);
			InputStream inputStream = new ExtensibleURIConverterImpl().createInputStream(uri);
			@SuppressWarnings("unused")
			InputStreamReader reader = new InputStreamReader(inputStream);
			ResourceSet resourceSet = new ResourceSetImpl();
			resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());
			standaloneProjectMap.initializeResourceSet(resourceSet);			// FIXME shared
			xmlResource = (XMLResource) resourceSet.createResource(URI.createFileURI("testFile.ecore"));
			parser = createParser(xmlResource, testFile);
		}
		if (parser != null) {
			parser.printStats();
		}
		assert xmlResource != null;
		assert xmlResource2 != null;
		TestUtil.assertSameModel(xmlResource2, xmlResource);
		//		Readable
		/*		IParseResult parseResult = ecore2XtextParser.doParse(reader);
		Iterable<INode> syntaxErrors = parseResult.getSyntaxErrors();
		StringBuilder s = null;
		for (INode iNode : syntaxErrors) {
			if (s == null) {
				s = new StringBuilder();
			}
			else {
				s.append("\n");
			}
			s.append(iNode.getStartLine());
			s.append(": \"");
			s.append(iNode.getText());
			s.append("\": ");
			s.append(iNode.getSyntaxErrorMessage());
		}
		if (s != null) {
			fail(s.toString());
		} */
	}

	protected XMLResource parseEcore(StandaloneProjectMap standaloneProjectMap, String testFile) throws IOException {
		ResourceSet resourceSet2 = new ResourceSetImpl();
		resourceSet2.getResourceFactoryRegistry().getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());
		standaloneProjectMap.initializeResourceSet(resourceSet2);			// FIXME shared
		XMLResource xmlResource2 = (XMLResource) resourceSet2.createResource(URI.createFileURI("testFile.ecore"));
		System.gc();
		long startTime = System.nanoTime();
		xmlResource2.load(new StringInputStream(testFile), null);
		long endTime = System.nanoTime();
		System.out.printf("Ecore Time = %6.6f\n", (endTime-startTime) / 1.0e9);
		return xmlResource2;
	}

	protected Ecore2XtextParser createParser(@NonNull XMLResource xmlResource, String testFile) {
		System.gc();
		long startTime = System.nanoTime();
		//		new Ecore2XtextLexer(lexStream);
		Ecore2XtextLexer lexer = new Ecore2XtextLexer(testFile.toCharArray(), "internalTestFile");
		//		lexer.printTokens = true;
		ILexStream lexStream = lexer.getILexStream();
		Ecore2XtextParser parser = new Ecore2XtextParser(xmlResource, lexStream);
		IPrsStream prsStream = parser.getIPrsStream();
		long initTime = System.nanoTime();
		lexer.lexer(prsStream);
		long lexTime = System.nanoTime();
		parser.parser(1);
		long parseTime = System.nanoTime();
		parser.resolveReferences();
		long resolveTime = System.nanoTime();
		parser = null;
		lexer = null;
		prsStream= null;
		lexStream= null;
		System.out.printf("InitTime = %6.6f, ", (initTime-startTime) / 1.0e9);
		System.out.printf("LexTime = %6.6f, ", (lexTime-initTime) / 1.0e9);
		System.out.printf("ParseTime = %6.6f, ", (parseTime-lexTime) / 1.0e9);
		System.out.printf("ResolveTime = %6.6f, ", (resolveTime-parseTime) / 1.0e9);
		System.out.printf("Total Time = %6.6f\n", (resolveTime-startTime) / 1.0e9);
		return parser;
	}
}
