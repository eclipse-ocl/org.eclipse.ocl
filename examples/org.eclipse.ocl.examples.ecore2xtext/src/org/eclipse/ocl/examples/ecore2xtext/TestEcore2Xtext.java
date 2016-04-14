package org.eclipse.ocl.examples.ecore2xtext;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.impl.ExtensibleURIConverterImpl;
import org.eclipse.ocl.pivot.internal.resource.StandaloneProjectMap;
import org.junit.Test;

import lpg.runtime.ILexStream;
import lpg.runtime.IPrsStream;

public class TestEcore2Xtext {

	@Test
	public void test() throws IOException {
//		Map<String, URI> platformResourceMap = EcorePlugin.getPlatformResourceMap();
		new StandaloneProjectMap(false).initializePlatformResourceMap(true);;
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
		
		URI uri = URI.createPlatformResourceURI("org.eclipse.emf.ecore/model/Ecore.ecore", true);
		InputStream inputStream = new ExtensibleURIConverterImpl().createInputStream(uri);
		InputStreamReader reader = new InputStreamReader(inputStream);

		
//		new Ecore2XtextLexer(lexStream);
		Ecore2XtextLexer lexer = new Ecore2XtextLexer(testFile.toCharArray(), "internalTestFile");
		ILexStream lexStream = lexer.getILexStream();
		Ecore2XtextParser parser = new Ecore2XtextParser(lexStream);
		IPrsStream prsStream = parser.getIPrsStream();
		lexer.lexer(prsStream);
		CSTNode cstNode = parser.parser(1);
		System.out.println(cstNode);
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
}
