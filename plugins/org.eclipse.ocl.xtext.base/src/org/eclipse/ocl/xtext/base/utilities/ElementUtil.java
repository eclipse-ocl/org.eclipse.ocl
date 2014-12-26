/*******************************************************************************
 * Copyright (c) 2010, 2014 E.D.Willink and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   E.D.Willink - initial API and implementation
 * 	 E.D.Willink (Obeo) - Bug 416287 - tuple-valued constraints
 *******************************************************************************/
package org.eclipse.ocl.xtext.base.utilities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Diagnostic;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.LanguageExpression;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.Namespace;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.ParserException;
import org.eclipse.ocl.pivot.TemplateBinding;
import org.eclipse.ocl.pivot.TemplateParameter;
import org.eclipse.ocl.pivot.TemplateSignature;
import org.eclipse.ocl.pivot.TemplateableElement;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.manager.MetaModelManager;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.eclipse.ocl.pivot.scoping.Attribution;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.xtext.base.attributes.RootCSAttribution;
import org.eclipse.ocl.xtext.base.cs2as.CS2AS;
import org.eclipse.ocl.xtext.base.cs2as.ImportDiagnostic;
import org.eclipse.ocl.xtext.base.cs2as.LibraryDiagnostic;
import org.eclipse.ocl.xtext.basecs.BaseCSFactory;
import org.eclipse.ocl.xtext.basecs.ElementCS;
import org.eclipse.ocl.xtext.basecs.ModelElementCS;
import org.eclipse.ocl.xtext.basecs.MultiplicityCS;
import org.eclipse.ocl.xtext.basecs.NamedElementCS;
import org.eclipse.ocl.xtext.basecs.OperationCS;
import org.eclipse.ocl.xtext.basecs.PathElementCS;
import org.eclipse.ocl.xtext.basecs.PathNameCS;
import org.eclipse.ocl.xtext.basecs.StructuredClassCS;
import org.eclipse.ocl.xtext.basecs.TemplateBindingCS;
import org.eclipse.ocl.xtext.basecs.TemplateParameterSubstitutionCS;
import org.eclipse.ocl.xtext.basecs.TypeRefCS;
import org.eclipse.ocl.xtext.basecs.TypedElementCS;
import org.eclipse.ocl.xtext.basecs.TypedRefCS;
import org.eclipse.ocl.xtext.basecs.TypedTypeRefCS;
import org.eclipse.ocl.xtext.basecs.WildcardTypeRefCS;
import org.eclipse.xtext.nodemodel.BidiIterator;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.nodemodel.ILeafNode;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.impl.AbstractNode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.resource.XtextSyntaxDiagnostic;
import org.eclipse.xtext.util.ITextRegion;

public class ElementUtil
{
	private static final String delegateExtensionPoints[] = {
//		EcorePlugin.CONVERSION_DELEGATE_PPID, -- not available in EMF 2.7
		EcorePlugin.INVOCATION_DELEGATE_PPID,
		EcorePlugin.QUERY_DELEGATE_PPID,
		EcorePlugin.SETTING_DELEGATE_PPID,
		EcorePlugin.VALIDATION_DELEGATE_PPID
	};

	private static String[][] delegationModes = null;

	public static void appendTextRegion(@NonNull StringBuilder s, @Nullable ITextRegion textRegion, boolean isSignificant) {
		s.append(isSignificant ? "[" : "(");
		if (textRegion != null) {
			s.append(textRegion.getOffset() + "," + textRegion.getLength());
		}
		else {
			s.append("null");
		}
		s.append(isSignificant ? "]" : ")");
	}

	public static @Nullable String getCollectionTypeName(@NonNull TypedElementCS csTypedElement) {
		TypedRefCS csTypeRef = csTypedElement.getOwnedType();
		if (csTypeRef == null) {
			return null;
		}
//		if (csTypeRef instanceof CollectionTypeRefCS) {
//			Type csType = ((CollectionTypeRefCS)csTypeRef).getType();
//			if (csType instanceof CollectionType) {
//				return ((CollectionType)csType).getName();
//			}
//		}
		//FIXME Obsolete compatibility
		MultiplicityCS csMultiplicity = csTypeRef.getOwnedMultiplicity();
		if (csMultiplicity == null) {
			return null;
		}
		int upper = csMultiplicity.getUpper();
		if (upper == 1) {
			return null;
		}
	boolean isOrdered = csTypedElement.isIsOrdered();
		boolean isUnique = !csTypedElement.isIsNotUnique();
		return getCollectionName(isOrdered, isUnique);
	}

	public static @NonNull String getCollectionName(boolean ordered, boolean unique) {
		if (ordered) {
			return unique ? TypeId.ORDERED_SET_NAME : TypeId.SEQUENCE_NAME;
		}
		else {
			return unique ? TypeId.SET_NAME : TypeId.BAG_NAME;
		}
	}
	
	public static @Nullable ModelElementCS getCsElement(@NonNull Element obj) {
		Resource resource = obj.eResource();
		if (resource == null) {
			return null;
		}
		ResourceSet resourceSet = resource.getResourceSet();
		if (resourceSet == null) {
			return null;
		}
		CS2AS cs2as = CS2AS.findAdapter(resourceSet);
		if (cs2as == null) {
			return null;
		}
		return cs2as.getCSElement(obj);
	}

	// FIXME share with common.ui once promoted from examples
	public static String[][] getDelegateURIs() {
		if (delegationModes == null) {
			Set<String> uris = new HashSet<String>();
			IExtensionRegistry pluginRegistry = Platform.getExtensionRegistry();
			String pluginID = EcorePlugin.getPlugin().getBundle().getSymbolicName();
			for (String extensionPointID : delegateExtensionPoints) {
				IExtensionPoint point = pluginRegistry.getExtensionPoint(pluginID, extensionPointID);
				if (point != null) {
					IConfigurationElement[] elements = point.getConfigurationElements();
					for (int i = 0; i < elements.length; i++) {
						String uri = elements[i].getAttribute("uri"); //$NON-NLS-1$
						if (uri != null) {
							uris.add(uri);
						}
					}
				}
			}
			List<String> uriList = new ArrayList<String>(uris);
			Collections.sort(uriList);
			delegationModes = new String[uriList.size()][2];
			for (int i = 0; i < uris.size(); i++) {
				delegationModes[i][0] = uriList.get(i);
				delegationModes[i][1] = uriList.get(i);
			}
		}
		return delegationModes;
	}
	
	public static @Nullable RootCSAttribution getDocumentAttribution(@NonNull ElementCS context) {
		for (ElementCS target = context, parent; (parent = target.getParent()) != null; target = parent) {
			Attribution attribution = PivotUtil.getAttribution(parent);
			if (attribution instanceof RootCSAttribution) {
				return (RootCSAttribution) attribution;
			}
		}
		return null;
	}

	/**
	 * Return the user text for csElement preserving all surrounding whitespace.
	 * <br>
	 * Except that Carriage Returns are removed.
	 * <br>
	 * Except that a first space is removed since it originates from the auto-formatter.
	 * <br>
	 * The leading whitespace of the next element is included since the folloowing token
	 * is expected to be a semicolon.
	 */
	public static @NonNull String getExpressionText(@NonNull ElementCS csElement) {
		ICompositeNode parserNode = NodeModelUtils.getNode(csElement);
		if (parserNode != null) {
			String text = parserNode.getText().replace("\r", "");
			if ((text.length() > 0) && text.charAt(0) == ' ') {
				text = text.substring(1);		// Step over the leading separator.
			}
			INode nextNode = parserNode.getNextSibling();
			for (INode parent = parserNode.getParent(); parent != null; parent = parent.getParent()) {
				nextNode = parent.getNextSibling();
				if (nextNode != null) {
					String nextText = nextNode.getText().replace("\r", "");
					int i = 0;
					int iMax = nextText.length();
					for ( ; i < iMax; i++) {	// Step up to the leading separator.
						if (!Character.isWhitespace(nextText.charAt(i))) {
							break;
						}
					}
					return text + nextText.substring(0, i);
				}
			}
			assert text != null;
			return text;
		}
		return "null";
	}

	/**
	 * Extract the first embedded ExpressionInOCL.
	 * @throws ParserException 
	 */
	public static @Nullable ExpressionInOCL getFirstQuery(@NonNull MetaModelManager metaModelManager, BaseCSResource csResource) throws ParserException {
		CS2ASResourceAdapter cs2asAdapter = csResource.findCS2ASAdapter();
		if (cs2asAdapter != null) {
			ASResource asResource = cs2asAdapter.getASResource(csResource);
			for (EObject eRoot: asResource.getContents()) {
				if (eRoot instanceof Model) {
					for (org.eclipse.ocl.pivot.Package asPackage: ((Model)eRoot).getOwnedPackages()) {
						for (org.eclipse.ocl.pivot.Class asType: asPackage.getOwnedClasses()) {
							for (Constraint asConstraint : asType.getOwnedInvariants()) {
								LanguageExpression specification = asConstraint.getOwnedSpecification();
								if (specification != null) {
									return metaModelManager.getQueryOrThrow(specification);
								}
							}
							for (Operation asOperation : asType.getOwnedOperations()) {
								LanguageExpression specification = asOperation.getBodyExpression();
								if (specification != null) {
									return metaModelManager.getQueryOrThrow(specification);
								}
							}
						}
					}
				}
			}
		}
		return null;
	}

	public static @Nullable TemplateParameter getFormalTemplateParameter(@NonNull TemplateParameterSubstitutionCS csTemplateParameterSubstitution) {
		TemplateBindingCS csTemplateBinding = csTemplateParameterSubstitution.getOwningBinding();
		int index = csTemplateBinding.getOwnedSubstitutions().indexOf(csTemplateParameterSubstitution);
		if (index < 0) {
			return null;
		}
		TemplateBinding templateBinding = (TemplateBinding) csTemplateBinding.getPivot();
		TemplateSignature templateSignature = templateBinding.getTemplateSignature();
		List<TemplateParameter> templateParameters = templateSignature.getOwnedParameters();
		if (templateParameters.size() <= index) {
			return null;
		}
		return templateParameters.get(index);
	}

	public static @Nullable ILeafNode getLeafNode(@NonNull INode node) {
		ILeafNode leafNode = null;
		if (node instanceof ILeafNode) {
			return (ILeafNode) node;
		}
		else {
			for (ILeafNode lNode : node.getLeafNodes()) {
				if (!lNode.isHidden()) {
					leafNode = lNode;
					return leafNode;
				}
			}
		}
		return null;
	}

	public static int getLower(@NonNull TypedElementCS csTypedElement) {
		TypedRefCS csTypeRef = csTypedElement.getOwnedType();
		if (csTypeRef == null) {
			return 0;		// e.g. missing Operation return type
		}
		MultiplicityCS csMultiplicity = csTypeRef.getOwnedMultiplicity();
		if (csMultiplicity == null) {
			return 1;
		}
		return csMultiplicity.getLower();
	}

	public static @Nullable <T extends NamedElementCS> T getNamedElementCS(@NonNull Collection<T> namedElements, @NonNull String name) {
		for (T namedElement : namedElements) {
			if (name.equals(namedElement.getName())) {
				return namedElement;
			}
		}
		return null;
	}

	/** This makes INode.getEndOffset from Xtext 2.5 available on 2.3 */
	public static int getEndOffset(@NonNull INode iNode) {
		if (!(iNode instanceof AbstractNode)) {
			return -1;
		}
		AbstractNode node = (AbstractNode) iNode;
		BidiIterator<AbstractNode> iter = node .basicIterator();
		while(iter.hasPrevious()) {
			INode prev = iter.previous();
			if (prev instanceof ILeafNode && !((ILeafNode) prev).isHidden()) {
				return prev.getTotalEndOffset();
			}
		}
		return node.getTotalEndOffset();
	}

	public static boolean getQualifier(@NonNull List<String> qualifiers, @NonNull String trueString, @NonNull String falseString, boolean defaultValue) {
		if (qualifiers.contains(trueString)) {
			return true;
		}
		else if (qualifiers.contains(falseString)) {
			return false;
		}
		else {
			return defaultValue;
		}
	}

	/**
	 * Return the raw text associated with a csElement.
	 */
	public static @Nullable String getText(@NonNull ElementCS csElement) {
		ICompositeNode node = NodeModelUtils.getNode(csElement);
		return node != null ? NodeModelUtils.getTokenText(node) : null;
	}

	public static @Nullable String getText(@NonNull TypedTypeRefCS csElement, @NonNull EReference feature) {
		@SuppressWarnings("null")@NonNull List<INode> nodes = NodeModelUtils.findNodesForFeature(csElement, feature);
//		assert (nodes.size() == 1;
		if (nodes.isEmpty()) {
			return null;
		}
		else if (nodes.size() == 1) {
			return NodeModelUtils.getTokenText(nodes.get(0));
		}
		else {
			StringBuilder s = new StringBuilder();
			for (INode node : nodes) {
				s.append(NodeModelUtils.getTokenText(node));
			}
			return s.toString();
		}
	}

	/**
	 * Return the logical text associated with a csElement. (EScaped identifers are unescaped.)
	 */
	public static @Nullable String getTextName(@NonNull ElementCS csElement) {
		String text = getText(csElement);
		if (text == null) {
			return null;
		}
		int length = text.length();
		if ((length >= 3) && text.startsWith("_'") && text.endsWith("'")) {
			return text.substring(2, length-1);
		}
		else {
			return text;
		}
	}

	public static int getUpper(@NonNull TypedElementCS csTypedElement) {
		TypedRefCS csTypeRef = csTypedElement.getOwnedType();
		if (csTypeRef == null) {
			return 1;
		}
		MultiplicityCS csMultiplicity = csTypeRef.getOwnedMultiplicity();
		if (csMultiplicity == null) {
			return 1;
		}
		return csMultiplicity.getUpper();
	}

	public static boolean hasSyntaxError(@NonNull List<Diagnostic> diagnostics) {
		for (Diagnostic diagnostic : diagnostics) {
			if (diagnostic instanceof LibraryDiagnostic) {
				return true;
			}
			else if (diagnostic instanceof XtextSyntaxDiagnostic) {
				return true;
			}
			else if (diagnostic instanceof ImportDiagnostic) {
				return true;
			}
		}
		return false;
	}

	public static boolean isInOperation(@NonNull ElementCS csElement) {
		for (EObject eObject = csElement; eObject != null; eObject = eObject.eContainer()) {
			if (eObject instanceof OperationCS) {
				return true;
			}
			else if (eObject instanceof StructuredClassCS) {
				return false;
			}
		}
		return false;
	}

	public static boolean isSpecialization(@NonNull TemplateBindingCS csTemplateBinding) {
		TypedTypeRefCS csTypedTypeRef = csTemplateBinding.getOwningElement();
		Element type = csTypedTypeRef.getPivot();
		for (TemplateParameterSubstitutionCS csTemplateParameterSubstitution : csTemplateBinding.getOwnedSubstitutions()) {
			TypeRefCS ownedActualParameter = csTemplateParameterSubstitution.getOwnedActualParameter();
			if (ownedActualParameter instanceof WildcardTypeRefCS) {
				return true;
			}
			Type actualParameterClass = (Type) ownedActualParameter.getPivot();
			TemplateParameter templateParameter = actualParameterClass.isTemplateParameter();
			if (templateParameter == null) {
				return true;
			}
			TemplateSignature signature = templateParameter.getOwningSignature();
			TemplateableElement template = signature.getOwningElement();
			if (template != type) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Assign a sequence of one or more path elements to csPathName that identify element with respect
	 * to scope.
	 * <br>
	 * For example A::B::C::D::E with respect to A::B::C::X::Y is D::E.
	 * <br>
	 * No validation is performed to check that the shortened name resolves to the same
	 * element.
	 * <br>
	 * For example if there is also an A::B::C::X::D::E, the caller must shorten the scope
	 * reference to A::B to avoid the ambiguity.
	 * 
	 * @Depreacted. Functionality moved to sole caller in AS2CSConversion.refreshPathName()
	 */
	public static void setPathName(@NonNull PathNameCS csPathName, @NonNull Element element, Namespace scope) {
		List<PathElementCS> csPath = csPathName.getOwnedPathElements();
		csPath.clear();		// FIXME re-use
		PathElementCS csSimpleRef = BaseCSFactory.eINSTANCE.createPathElementCS();
		csPath.add(csSimpleRef);
		csSimpleRef.setReferredElement(element);
		Resource csResource = csPathName.eResource();
//		if (csResource == null) {
//			getCsElement(element)
//		}
		if (!(csResource instanceof BaseCSResource) || (((BaseCSResource)csResource).isPathable(element) == null)) {
			return;
		}
		for (EObject eContainer = element.eContainer(); eContainer instanceof Element; eContainer = eContainer.eContainer()) {
			if (eContainer instanceof Model) {
				return;				// Skip root package
			}
			for (EObject aScope = scope; aScope != null; aScope = aScope.eContainer()) {
				if (aScope == eContainer) { 		// If element ancestor is scope or an ancestor
					return;							// no need for further qualification
				}
			}
			csSimpleRef = BaseCSFactory.eINSTANCE.createPathElementCS();
			csPath.add(0, csSimpleRef);
			csSimpleRef.setReferredElement((Element) eContainer);
		}
	}
}
