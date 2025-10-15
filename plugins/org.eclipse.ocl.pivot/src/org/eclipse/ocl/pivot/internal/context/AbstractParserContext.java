/*******************************************************************************
 * Copyright (c) 2012, 2025 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.context;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Factory.Registry;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.LanguageExpression;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.PivotFactory;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.internal.messages.PivotMessagesInternal;
import org.eclipse.ocl.pivot.internal.utilities.PivotConstantsInternal;
import org.eclipse.ocl.pivot.resource.CSResource;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;
import org.eclipse.ocl.pivot.utilities.MetamodelManager;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.ParserContext;
import org.eclipse.ocl.pivot.utilities.ParserException;
import org.eclipse.ocl.pivot.utilities.PivotConstants;
import org.eclipse.ocl.pivot.utilities.PivotHelper;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.Pivotable;
import org.eclipse.ocl.pivot.utilities.StringUtil;

/**
 * AbstractParserContext provides the default implementation of the ParserContext API that all clients
 * should extend.
 */
public abstract class AbstractParserContext implements ParserContext
{
	private static final class ParsingResourceSet extends ResourceSetImpl
	{
		private final Resource.Factory.@NonNull Registry resourceFactoryRegistry;

		protected ParsingResourceSet(Resource.Factory.@NonNull Registry resourceFactoryRegistry) {
			this.resourceFactoryRegistry = resourceFactoryRegistry;
		}

		@Override
		public Registry getResourceFactoryRegistry() {
			return resourceFactoryRegistry;
		}
	}

	protected final @NonNull EnvironmentFactory environmentFactory;
	protected final @NonNull URI uri;
	protected @Nullable Element rootElement = null;
	private @Nullable ResourceSet parsingResourceSet;

	protected AbstractParserContext(@NonNull EnvironmentFactory environmentFactory, @Nullable URI uri) {
		this.environmentFactory = environmentFactory;
		if (uri != null) {
			this.uri = uri;
		}
		else {
			this.uri = ClassUtil.requireNonNull(URI.createURI(EcoreUtil.generateUUID() + "." + PivotConstants.ESSENTIAL_OCL_FILE_EXTENSION));
		}
	}

	@Override
	public @NonNull CSResource createBaseResource(@Nullable String expression) throws IOException, ParserException {
		InputStream inputStream = expression != null ? new URIConverter.ReadableInputStream(expression, "UTF-8") : null;
		try {
			ResourceSet parsingResourceSet2 = parsingResourceSet;
			if (parsingResourceSet2 == null) {
				Resource.Factory.Registry resourceFactoryRegistry = environmentFactory.getResourceSet().getResourceFactoryRegistry();
				assert resourceFactoryRegistry != null;
				this.parsingResourceSet = parsingResourceSet2 = new ParsingResourceSet(resourceFactoryRegistry);
			}
			Resource resource = parsingResourceSet2.createResource(uri);
			if (resource == null) {
				throw new ParserException("Failed to load '" + uri + "'" + getDoSetupMessage());
			}
			if (!(resource instanceof CSResource)) {
				throw new ParserException("Failed to create Xtext resource for '" + uri + "'" + getDoSetupMessage());
			}
			CSResource baseResource = (CSResource)resource;
			baseResource.setParserContext(this);
			if (inputStream != null) {
				baseResource.load(inputStream, null);
			}
			else {
				baseResource.load(null);
			}
			return baseResource;
		}
		finally {
			if (inputStream != null) {
				inputStream.close();
			}
		}
	}

	@Override
	public @Nullable Type getClassContext() {
		return null;
	}

	@Override
	public @Nullable Element getElementContext() {
		return getClassContext();
	}

	protected @NonNull String getDoSetupMessage() {
		if (EcorePlugin.IS_ECLIPSE_RUNNING) {
			return "";
		}
		String doSetup = environmentFactory.getDoSetupName(uri);
		if (doSetup == null) {
			return "";
		}
		return "\n\tMake sure " + doSetup + " has been called.";
	}

	@Override
	public @NonNull EnvironmentFactory getEnvironmentFactory() {
		return environmentFactory;
	}

	@Override
	public @NonNull ExpressionInOCL getExpression(@NonNull CSResource resource) throws ParserException {
		List<EObject> contents = resource.getContents();
		int size = contents.size();
		if (size < 1) {
			throw new ParserException("Missing parse returns");
		}
		if (size > 1) {
			throw new ParserException("Extra parse returns");
		}
		EObject csObject = contents.get(0);
		if (csObject instanceof Pivotable) {
			Element pivotElement = ((Pivotable)csObject).getPivot();
			if (pivotElement instanceof ExpressionInOCL) {
				return (ExpressionInOCL) pivotElement;
			}
		}
		throw new ParserException("Non-expression ignored");
	}

	public @Nullable Type getInstanceContext() {
		return null;
	}

	@Override
	public @NonNull MetamodelManager getMetamodelManager() {
		return environmentFactory.getMetamodelManager();
	}

	/**
	 * @since 1.4
	 */
	protected @NonNull String getRole() {
		if (rootElement instanceof LanguageExpression) {
			return PivotUtil.getSpecificationRole((LanguageExpression) rootElement);
		}
		else {
			return PivotConstantsInternal.UNKNOWN_ROLE;
		}
	}

	@Override
	public @Nullable Element getRootElement() {
		return rootElement;
	}

	@Override
	public void initialize(@NonNull Base2ASConversion conversion, @NonNull ExpressionInOCL expression) {
		//		List<String> language = expression.getLanguage();
		//		language.clear();
		//		language.add(PivotConstants.OCL_LANGUAGE);
	}

	@Override
	public @NonNull ExpressionInOCL parse(@Nullable EObject owner, @NonNull String expression) throws ParserException {
		CSResource resource = null;
		try {
			resource = createBaseResource(expression);
			String role = getRole();
			String contextName = NameUtil.qualifiedNameFor(getMessageContext());
			String invalidMessage = StringUtil.bind(PivotMessagesInternal.ValidationConstraintIsInvalid_ERROR_, role, contextName, expression.trim());
			PivotUtil.checkResourceErrors(invalidMessage, resource);
			ExpressionInOCL expressionInOCL = getExpression(resource);
			expressionInOCL.setBody(expression);
			return expressionInOCL;
		} catch (IOException e) {
			//				throw new ParserException("Failed to load expression", e);
			@NonNull ExpressionInOCL specification = PivotFactory.eINSTANCE.createExpressionInOCL();
			OCLExpression invalidValueBody = new PivotHelper(environmentFactory).createInvalidExpression();
			PivotUtil.setBody(specification, invalidValueBody, null);
			return specification;
		} finally {
			if (resource != null) {
				resource.unload();
				ResourceSet resourceSet = resource.getResourceSet();
				if (resourceSet != null) {
					resourceSet.getResources().remove(resource);
				}
			}
		}
	}

	/**
	 * @since 1.4
	 */
	protected Element getMessageContext() {
		return rootElement != null ? (Element) rootElement.eContainer() : getClassContext();
	}

	@Override
	public void setRootElement(@Nullable Element rootElement) {
		this.rootElement = rootElement;
	}
}
