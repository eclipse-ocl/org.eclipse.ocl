The Tycho build automatically promotes downloads and updates, so no cron job help is necessary.

The updates can be checked by looking for the new entry on http://www.eclipse.org/modeling/mdt/downloads/?project=ocl
or installing new software from e.g. http://download.eclipse.org/modeling/mdt/ocl/updates/milestones/6.5.0/S201408191307

However operations on composite repositories are not automated, partly because they are sufficiently important to deserve manual attention. 

A new milestone build can be added to the composite repository by:

logon to build.eclipse.org
cd downloads/modeling/mdt/ocl/updates/milestones/6.5.0
ant -f /shared/modeling/tools/promotion/manage-composite.xml add -Dchild.repository=S201408191307 [ -Dcomposite.name="OCL 6.5.0 milestones" ]

(This can be checked by installing new software from e.g. http://download.eclipse.org/modeling/mdt/ocl/updates/milestones/6.5.0)

The Photon aggregator is configured by GIT\org.eclipse.simrel.build\ocl.aggrcon to use an explicit milestone entry

So edit ocl.aggrcon to update 
location="http://download.eclipse.org/modeling/mdt/ocl/updates/milestones/6.5.0/S201408191307"
commit with a comment sych as [ocl] 6.5.0M5 for Photon and Push to Gerrit (refs/for/master)
If that comes back wuth SUCCESS, push to master

Once a release has been promoted update ocl.aggrcon to the final release
location="http://download.eclipse.org/modeling/mdt/ocl/updates/releases/6.5.0"

Downloads are accessible at
cd downloads/modeling/mdt/ocl/downloads/drops/6.5.0

Archives are accessible at
cd /home/data/httpd/archive.eclipse.org/modeling/mdt/ocl/downloads/drops

--------

GIT repo: /gitroot/ocl/org.eclipse.ocl.git

Build periodically: H 2 * * 0
Poll SCM schedule: H */6 * * 1-6

Run XVNC during build

Execute:

/shared/common/apache-maven-latest/bin/mvn clean verify -V -B -e -DBUILD_ALIAS=$BUILD_ALIAS -DBUILD_TYPE=$BUILD_TYPE -Dmaven.repo.local=/home/hudson/genie.ocl/.hudson/jobs/ocl-photon-master/workspace/.maven/repo -f releng/org.eclipse.ocl.releng.tycho/pom.xml -P ${BUILD_TYPE} -P sign

Path: releng/org.eclipse.ocl.releng.build-site/target/repository
Name: OCL Tycho %BUILD_TYPE Repository

Publish JUnit test report: tests/*.test*/target/surefire-reports/*.xml,tests/*.test*/target/surefire-reports/*/*.xml

Archive the artefacts: releng/org.eclipse.ocl.releng.build-site/target/*.zip,releng/org.eclipse.ocl.releng.build-site/target/publisher.properties,releng/org.eclipse.ocl.releng.build-site/target/downloads.sh,releng/org.eclipse.ocl.releng.build-site/target/updates.sh

Trigger Promoter when table using releng/org.eclipse.ocl.releng.build-site/target/publisher.properties