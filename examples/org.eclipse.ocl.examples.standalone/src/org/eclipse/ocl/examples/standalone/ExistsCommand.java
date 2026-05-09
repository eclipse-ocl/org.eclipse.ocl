/*******************************************************************************
 * Copyright (c) 2026 Willink Transformations and others..
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.standalone;

import java.io.IOException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.standalone.messages.StandaloneMessages;

/**
 * The ExistsCommand tests the accessibility of a file-name
 */
public class ExistsCommand extends StandaloneCommand
{
	public static class FileToken extends StringToken
	{
		private @Nullable String fileName = null;

		public FileToken(@NonNull StandaloneApplication standaloneApplication) {
			super(standaloneApplication, "-file", StandaloneMessages.ExistsCommand_File_Help, "<file-name>");
		}

		@Override
		public boolean analyze(@Nullable String string) {
			fileName  = string;
			return true;
		}

		public String getFileName() {
			return fileName;
		}
	}

	public final @NonNull FileToken fileToken = new FileToken(standaloneApplication);

	public ExistsCommand(@NonNull StandaloneApplication standaloneApplication) {
		super(standaloneApplication, "exists", StandaloneMessages.ExistsCommand_Help);
		addToken(fileToken);
	}

	@Override
	public @NonNull StandaloneResponse execute() {
		String fileName = fileToken.getFileName();
		URI modelURI = getModelUri(fileName);
		final @NonNull URIConverter uriConverter = standaloneApplication.getURIConverter();
		boolean exists = uriConverter.exists(modelURI, null);
		try {
			StringBuilder s = new StringBuilder();
			s.append("\"");
			s.append(String.valueOf(modelURI));
			s.append(exists ? "\" exists\n" : "\" does not exist\n");
			DEFAULT_OUTPUT_STREAM.append(s.toString());
			if (exists) {
				return StandaloneResponse.OK;
			}
		} catch (IOException e) {}
		return StandaloneResponse.FAIL;
	}
}
