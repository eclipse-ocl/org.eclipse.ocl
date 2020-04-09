#!/bin/bash -xv
#*******************************************************************************
# Copyright (c) 2020 Willink Transformations and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v2.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v20.html
#
# Contributors:
#     E.D.Willink - initial API and implementation
#*******************************************************************************
# Local OCL publish script for use by the promoter job. It prepares and invokes remote scripts.
#
rm -f downloads.sh javadoc.zip pdfdoc.zip publish.zip updates.sh
curl -s -k ${PUBLISH__DOWNLOADS_SH} > downloads.sh
curl -s -k ${PUBLISH__JAVADOC} > javadoc.zip
curl -s -k ${PUBLISH__PDFDOC} > pdfdoc.zip
curl -s -k ${PUBLISH__UPDATES_SH} > updates.sh
curl -s -k ${PUBLISH__URL} > publish.zip
ssh genie.ocl@projects-storage.eclipse.org rm -f downloads.sh publish.zip updates.sh
scp downloads.sh genie.ocl@projects-storage.eclipse.org:downloads.sh
scp javadoc.zip genie.ocl@projects-storage.eclipse.org:javadoc.zip
scp pdfdoc.zip genie.ocl@projects-storage.eclipse.org:pdfdoc.zip
scp publish.zip genie.ocl@projects-storage.eclipse.org:publish.zip
scp updates.sh genie.ocl@projects-storage.eclipse.org:updates.sh
ssh genie.ocl@projects-storage.eclipse.org chmod +x downloads.sh updates.sh
ssh genie.ocl@projects-storage.eclipse.org bash -ex downloads.sh -v "'${PUBLISH__VERSION}'" -t "'${PUBLISH__BUILD_T}'" -q "'${PUBLISH__QUALIFIER}'" -a "'${PUBLISH__ALIAS}'"
ssh genie.ocl@projects-storage.eclipse.org bash -ex updates.sh -v "'${PUBLISH__VERSION}'" -t "'${PUBLISH__BUILD_T}'" -q "'${PUBLISH__QUALIFIER}'"
