#!/bin/bash -xv
#
#	Promote the PUBLISH__URL to the downloads 'page'.
#
#	PUBLISH__URL			The zip to be published e.g. https://hudson.eclipse.org/ocl/job/ocl-photon-master/38/artifact/releng/org.eclipse.ocl.releng.build-site/target/org.eclipse.ocl-6.4.0.201710211702.zip
#	PUBLISH__VERSION		Unqualified version e.g. 6.4.0
#	PUBLISH__BUILD_T		Build type N/I/S, blank suppresses promotion
#	PUBLISH__QUALIFIER		Version qualifier e.g. 201710201234
#	PUBLISH__ALIAS			Non blank to use alias as part of final name
#
dropsFolder="/home/data/httpd/download.eclipse.org/modeling/mdt/ocl/downloads/drops/"
group="modeling.mdt.ocl"
zipPrefix="mdt-ocl-Update-"

if [ -n "${PUBLISH__BUILD_T}" ]
then

  versionFolder="${dropsFolder}${PUBLISH__VERSION}/${PUBLISH__BUILD_T}${PUBLISH__QUALIFIER}"
  if [ ! -d "${versionFolder}" ]
  then
    mkdir -p ${versionFolder}
  fi

  fileStem="${PUBLISH__BUILD_T}${PUBLISH__QUALIFIER}"
  if [ -n "${PUBLISH__ALIAS}" ]
  then
    fileStem=${PUBLISH__ALIAS}
  fi
  zipFile="${zipPrefix}${fileStem}.zip"

  pushd ${versionFolder}
    curl -s -k ${PUBLISH__URL} > ${zipFile}
    md5sum -b ${zipFile} > ${zipFile}.md5
    sha512sum -b ${zipFile} > ${zipFile}.sha1
    # make sure permissions are for the intended group
    chgrp -R ${group} ${zipFile} ${zipFile}.md5 ${zipFile}.sha1
    chmod -R g+w ${zipFile} ${zipFile}.md5 ${zipFile}.sha1
  popd

fi