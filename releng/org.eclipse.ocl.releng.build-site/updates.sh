#!/bin/bash -xv
#
#	Promote the PUBLISH__URL to an updates repository.
#
#	PUBLISH__URL			The zip to be published e.g. https://hudson.eclipse.org/ocl/job/ocl-photon-master/38/artifact/releng/org.eclipse.ocl.releng.build-site/target/org.eclipse.ocl-6.4.0.201710211702.zip
#	PUBLISH__VERSION		Unqualified version e.g. 6.4.0
#	PUBLISH__BUILD_T		Build type N/I/S, blank suppresses promotion
#	PUBLISH__QUALIFIER		Version qualifier e.g. 201710201234
#
FOLDER="/home/data/httpd/download.eclipse.org/modeling/mdt/ocl/updates/"
GROUP="modeling.mdt.ocl"
LOCAL_ZIP="ocl.zip"
ANT="/shared/common/apache-ant-latest/bin/ant"
PROJECT_NAME="OCL"
MANAGE_COMPOSITE="${ANT} -f /shared/modeling/tools/promotion/manage-composite.xml"

if [ -n "${PUBLISH__BUILD_T}" ]
then

  if [ "${PUBLISH__BUILD_T}" = "N" ]
  then
	FOLDER="${FOLDER}nightly"
	REPO_NAME="Nightly"
  elif [ "${PUBLISH__BUILD_T}" = "I" ]
  then
	FOLDER="${FOLDER}interim"
	REPO_NAME="Interim"
  elif [ "${PUBLISH__BUILD_T}" = "S" ]
  then
	FOLDER="${FOLDER}milestones"
	REPO_NAME="Milestones"
  else
	FOLDER="${FOLDER}other"
	REPO_NAME="Other"
  fi
  
  if [ ! -d "${FOLDER}" ]
  then
    mkdir -p ${FOLDER}
  fi
  
  pushd ${FOLDER}
    if [ ! -d "${PUBLISH__VERSION}" ]
    then
      mkdir ${PUBLISH__VERSION}
	  VERSION_COMPOSITE_NAME="${PROJECT_NAME} ${REPO_NAME} Repository"
	  ${MANAGE_COMPOSITE} add -Dchild.repository=${PUBLISH__VERSION} -Dcomposite.name="${VERSION_COMPOSITE_NAME}"
    fi

    pushd ${FOLDER}/${PUBLISH__VERSION}

	  T_QUALIFIER="${PUBLISH__BUILD_T}${PUBLISH__QUALIFIER}"
	  VERSION_FOLDER="${FOLDER}/${T_QUALIFIER}"
	  if [ ! -d "${T_QUALIFIER}" ]
	  then
	    mkdir ${T_QUALIFIER}
	  fi
	  
      curl -s -k ${PUBLISH__URL} > ${LOCAL_ZIP}
	  unzip ${LOCAL_ZIP} -d ${T_QUALIFIER}
	  rm ${LOCAL_ZIP}

	  VERSION_NAME="${T_QUALIFIER}"
	  ${MANAGE_COMPOSITE} add -Dchild.repository=${VERSION_NAME} -Dcomposite.name="${PROJECT_NAME} ${PUBLISH__VERSION} ${REPO_NAME} Repository"

	  chgrp -R ${GROUP} ${T_QUALIFIER}
	  chmod -R g+w ${T_QUALIFIER}
    
	  if [ ! -d "latest" ]
	  then
	    mkdir latest
	  fi
	  pushd ${FOLDER}/${PUBLISH__VERSION}/latest
	    rm -rf ${FOLDER}/${PUBLISH__VERSION}/latest/*
	    cp -pr ../${T_QUALIFIER}/* .
	  popd
	  
    popd
    
  popd

fi