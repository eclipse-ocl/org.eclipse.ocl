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
FOLDER="/home/data/httpd/download.eclipse.org/modeling/mdt/ocl/downloads/drops/"
GROUP="modeling.mdt.ocl"
ZIP_PREFIX="mdt-ocl-Update-"

if [ -n "${PUBLISH__BUILD_T}" ]
then

  FOLDER="${FOLDER}${PUBLISH__VERSION}/${PUBLISH__BUILD_T}${PUBLISH__QUALIFIER}"
  if [ ! -d "${FOLDER}" ]
  then
    mkdir -p ${FOLDER}
  fi

  FILE_STEM="${PUBLISH__BUILD_T}${PUBLISH__QUALIFIER}"
  if [ -n "${PUBLISH__ALIAS}" ]
  then
    FILE_STEM=${PUBLISH__ALIAS}
  fi
  ZIP_FILE="${ZIP_PREFIX}${FILE_STEM}.zip"

  pushd ${FOLDER}
    curl -s -k ${PUBLISH__URL} > ${ZIP_FILE}
    md5sum -b ${ZIP_FILE} > ${ZIP_FILE}.md5
    sha512sum -b ${ZIP_FILE} > ${ZIP_FILE}.sha1
    # make sure permissions are for the intended group
    chgrp -R ${GROUP} ${ZIP_FILE} ${ZIP_FILE}.md5 ${ZIP_FILE}.sha1
    chmod -R g+w ${ZIP_FILE} ${ZIP_FILE}.md5 ${ZIP_FILE}.sha1
  popd

fi