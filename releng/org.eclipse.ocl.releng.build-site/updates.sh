#!/bin/bash -xv
#
#    Promote the PUBLISH__URL to an updates repository.
#
#    PUBLISH__URL            The zip to be published e.g. https://hudson.eclipse.org/ocl/job/ocl-photon-master/38/artifact/releng/org.eclipse.ocl.releng.build-site/target/org.eclipse.ocl-6.4.0.v20171021-1702.zip
#    PUBLISH__VERSION        Unqualified version e.g. 6.4.0
#    PUBLISH__BUILD_T        Build type N/I/S, blank suppresses promotion
#    PUBLISH__QUALIFIER        Version qualifier e.g. v20171020-1234
#
updatesFolder="/home/data/httpd/download.eclipse.org/modeling/mdt/ocl/updates/"
group="modeling.mdt.ocl"
localZip="ocl.zip"
projectRepoName="OCL"
manageComposite="/shared/common/apache-ant-latest/bin/ant -f /shared/modeling/tools/promotion/manage-composite.xml"

if [ -n "${PUBLISH__BUILD_T}" ]
then

  if [ "${PUBLISH__BUILD_T}" = "N" ]
  then
    buildFolder="${updatesFolder}nightly"
    buildRepoName="Nightly"
  elif [ "${PUBLISH__BUILD_T}" = "I" ]
  then
    buildFolder="${updatesFolder}interim"
    buildRepoName="Interim"
  elif [ "${PUBLISH__BUILD_T}" = "S" ]
  then
    buildFolder="${updatesFolder}milestones"
    buildRepoName="Milestones"
  else
    buildFolder="${updatesFolder}other"
    buildRepoName="Other"
  fi

  if [ ! -d "${buildFolder}" ]
  then
    mkdir -p ${buildFolder}
  fi

  pushd ${buildFolder}
    if [ ! -d "${PUBLISH__VERSION}" ]
    then
      mkdir ${PUBLISH__VERSION}
      versionCompositeName="${projectRepoName} ${buildRepoName} Repository"
      ${manageComposite} add -Dchild.repository=${PUBLISH__VERSION} -Dcomposite.name="${versionCompositeName}"
    fi

    if [ "${PUBLISH__BUILD_T}" = "N" ]
    then
      rm -rf ${buildFolder}/${PUBLISH__VERSION}/*
      curl -s -k ${PUBLISH__URL} > ${localZip}
      unzip -ou ${localZip} -d ${PUBLISH__VERSION}
      rm ${localZip}

      chgrp -R ${group} ${PUBLISH__VERSION}
      chmod -R g+w ${PUBLISH__VERSION}
    elif [ "${PUBLISH__BUILD_T}" = "I" ]
    then
      rm -rf ${buildFolder}/${PUBLISH__VERSION}/*
      curl -s -k ${PUBLISH__URL} > ${localZip}
      unzip -ou ${localZip} -d ${PUBLISH__VERSION}
      rm ${localZip}

      chgrp -R ${group} ${PUBLISH__VERSION}
      chmod -R g+w ${PUBLISH__VERSION}
    elif [ "${PUBLISH__BUILD_T}" = "S" ]
    then
      pushd ${buildFolder}/${PUBLISH__VERSION}

        tQualifier="${PUBLISH__BUILD_T}${PUBLISH__QUALIFIER:1:8}${PUBLISH__QUALIFIER:10:4}"
        versionFolder="${buildFolder}/${tQualifier}"
        if [ ! -d "${tQualifier}" ]
        then
          mkdir ${tQualifier}
        fi

        curl -s -k ${PUBLISH__URL} > ${localZip}
        unzip ${localZip} -d ${tQualifier}
        rm ${localZip}

        chgrp -R ${group} ${tQualifier}
        chmod -R g+w ${tQualifier}
        ${manageComposite} add -Dchild.repository=${tQualifier} -Dcomposite.name="${projectRepoName} ${PUBLISH__VERSION} ${buildRepoName} Repository"

      popd
    fi
   
  popd

fi