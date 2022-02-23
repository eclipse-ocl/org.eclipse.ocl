The Tycho build automatically promotes downloads and updates, so no cron job help is necessary.
The Tycho build minimises the need for shell activity by auto-registering in P2 repos including the latest.

The ocl.aggrcon file is in the ssh://'committer-name'@git.eclipse.org:29418/simrel/org.eclipse.simrel.build.git repo.

The updates can be checked by looking for the new entry on http://www.eclipse.org/modeling/mdt/downloads/?project=ocl
or installing new software from e.g. http://download.eclipse.org/modeling/mdt/ocl/updates/milestones/6.10.0/S201408191307
or installing new software from e.g. http://download.eclipse.org/modeling/mdt/ocl/updates/releases/6.10.0/

A new milestone build was formerly manually added to the composite repository by:

logon to build.eclipse.org
cd ~/downloads/modeling/mdt/ocl/updates/milestones/6.10.0
ant -f /shared/modeling/tools/promotion/manage-composite.xml add -Dchild.repository=S201408191307 [ -Dcomposite.name="OCL 6.10.0 milestones" ]

The SimRel aggregator is configured by GIT\org.eclipse.simrel.build\ocl.aggrcon to use an explicit milestone entry

So edit ocl.aggrcon to update 
location="http://download.eclipse.org/modeling/mdt/ocl/updates/milestones/6.10.0/S201408191307"
commit with a comment such as [ocl] 6.10.0M1 for 2019-09 and Push to Gerrit (refs/for/master)
The Push dialog identifies a Gerrit such as https://git.eclipse.org/r/149210
Open the Gerrit, Open the Buld job and its console
When the build succeeds, refresh the Gerrit, Click CodeReview+2, Click Submit.
Refresh 

RC builds are just aliases for regular S builds.
The final R build rebuilds the final RC build and is built as late as possible for contribution to the final SimRel build.
For the R  build update qvtd.aggrcon to
location="http://download.eclipse.org/modeling/mdt/ocl/updates/releases/3.10.0"

After a few hours the mirrors can be checked by:
https://www.eclipse.org/downloads/download.php?file=/modeling/mdt/ocl/updates/releases/6.10.0&format=xml

Disable the Promoter job until GIT has been updated for the next release number.

After each first repo contribution, remember to update the aggregates e.g.
cd ~/downloads/modeling/mdt/ocl/updates/milestones
ant -f /shared/modeling/tools/promotion/manage-composite.xml add -Dchild.repository=6.10.0

Repos overview: https://download.eclipse.org/oomph/archive/p2-index/modeling.mdt.ocl.html

-- Drops maintenance -- https://download.eclipse.org/modeling/mdt/ocl/downloads/drops
ssh genie.ocl@projects-storage.eclipse.org ls -la /home/data/httpd/download.eclipse.org/modeling/mdt/ocl/downloads/drops/6.12.0
ssh genie.ocl@projects-storage.eclipse.org rm -rf  /home/data/httpd/download.eclipse.org/modeling/mdt/ocl/downloads/drops/6.12.0/N201909*

-- Updates maintenance -- https://download.eclipse.org/modeling/mdt/ocl/updates/releases
ssh genie.ocl@projects-storage.eclipse.org ls -la /home/data/httpd/download.eclipse.org/modeling/mdt/ocl/updates/releases
ssh genie.ocl@projects-storage.eclipse.org pwd ; cd /home/data/httpd/download.eclipse.org/modeling/mdt/ocl/updates/releases ; pwd ; ls -la
ssh genie.ocl@projects-storage.eclipse.org cd /home/data/httpd/download.eclipse.org/modeling/mdt/ocl/updates/releases ; export JAVA_HOME=/shared/common/jdk1.8.0_x64-latest ; java -version ; /shared/common/apache-ant-latest/bin/ant -f /shared/modeling/tools/promotion/manage-composite.xml remove -Dchild.repository=6.12.0
ssh genie.ocl@projects-storage.eclipse.org rm -rf /home/data/httpd/download.eclipse.org/modeling/mdt/ocl/updates/releases/6.12.0

ssh genie.ocl@projects-storage.eclipse.org cd /home/data/httpd/download.eclipse.org/modeling/mdt/ocl/updates/nightly ; export JAVA_HOME=/shared/common/jdk1.8.0_x64-latest ; java -version ; /shared/common/apache-ant-latest/bin/ant -f /shared/modeling/tools/promotion/manage-composite.xml remove -Dchild.repository=6.12.0
ssh genie.ocl@projects-storage.eclipse.org rm -rf /home/data/httpd/download.eclipse.org/modeling/mdt/ocl/updates/nightly/6.12.0

ssh genie.ocl@projects-storage.eclipse.org cd /home/data/httpd/download.eclipse.org/modeling/mdt/ocl/updates/interim ; export JAVA_HOME=/shared/common/jdk1.8.0_x64-latest ; java -version ; /shared/common/apache-ant-latest/bin/ant -f /shared/modeling/tools/promotion/manage-composite.xml remove -Dchild.repository=6.12.0
ssh genie.ocl@projects-storage.eclipse.org rm -rf /home/data/httpd/download.eclipse.org/modeling/mdt/ocl/updates/interim/6.12.0

ssh genie.ocl@projects-storage.eclipse.org cd /home/data/httpd/download.eclipse.org/modeling/mdt/ocl/updates/milestones ; export JAVA_HOME=/shared/common/jdk1.8.0_x64-latest ; java -version ; /shared/common/apache-ant-latest/bin/ant -f /shared/modeling/tools/promotion/manage-composite.xml remove -Dchild.repository=6.12.0
ssh genie.ocl@projects-storage.eclipse.org rm -rf /home/data/httpd/download.eclipse.org/modeling/mdt/ocl/updates/milestones/6.12.0


-- Drops archiving -- https://archive.eclipse.org/modeling/mdt/ocl/downloads/drops ---- and edit GIT\mdt\downloads\extras-ocl.php
ssh genie.ocl@projects-storage.eclipse.org ls -la /home/data/httpd/archive.eclipse.org/modeling/mdt/ocl/downloads/drops
ssh genie.ocl@projects-storage.eclipse.org cd /home/data/httpd/download.eclipse.org/modeling/mdt/ocl/downloads/drops ; mv 6.12.0 /home/data/httpd/archive.eclipse.org/modeling/mdt/ocl/downloads/drops

-- Updates archiving -- https://archive.eclipse.org/modeling/mdt/ocl/updates/releases
ssh genie.ocl@projects-storage.eclipse.org ls -la /home/data/httpd/archive.eclipse.org/modeling/mdt/ocl/updates/releases
ssh genie.ocl@projects-storage.eclipse.org rm -rf /home/data/httpd/archive.eclipse.org/modeling/mdt/ocl/updates/releases/zz*

ssh genie.ocl@projects-storage.eclipse.org cd /home/data/httpd/download.eclipse.org/modeling/mdt/ocl/updates/releases ; export JAVA_HOME=/shared/common/jdk1.8.0_x64-latest ; java -version ; /shared/common/apache-ant-latest/bin/ant -f /shared/modeling/tools/promotion/manage-composite.xml remove -Dchild.repository=6.12.0
ssh genie.ocl@projects-storage.eclipse.org cd /home/data/httpd/download.eclipse.org/modeling/mdt/ocl/updates/releases ; mv 6.12.0 /home/data/httpd/archive.eclipse.org/modeling/mdt/ocl/updates/releases
ssh genie.ocl@projects-storage.eclipse.org cd /home/data/httpd/archive.eclipse.org/modeling/mdt/ocl/updates/releases ; export JAVA_HOME=/shared/common/jdk1.8.0_x64-latest ; java -version ; /shared/common/apache-ant-latest/bin/ant -f /shared/modeling/tools/promotion/manage-composite.xml add -Dchild.repository=6.12.0


-- Doc maintenance -- https://download.eclipse.org/ocl/doc/
ssh genie.ocl@projects-storage.eclipse.org ls -la /home/data/httpd/download.eclipse.org/ocl/doc/drops/6.12.0







--------

GIT repo: /gitroot/ocl/org.eclipse.ocl.git

Build periodically: H 2 * * 0
Poll SCM schedule: H */6 * * 1-6

Run XVNC during build

Execute:

/shared/common/apache-maven-latest/bin/mvn clean verify -V -B -e -DBUILD_ALIAS=$BUILD_ALIAS -DBUILD_TYPE=$BUILD_TYPE -Dmaven.repo.local=/home/hudson/genie.ocl/.hudson/jobs/ocl-master/workspace/.maven/repo -f releng/org.eclipse.ocl.releng.tycho/pom.xml -P ${BUILD_TYPE} -P sign

Path: releng/org.eclipse.ocl.releng.build-site/target/repository
Name: OCL Tycho %BUILD_TYPE Repository

Publish JUnit test report: tests/*.test*/target/surefire-reports/*.xml,tests/*.test*/target/surefire-reports/*/*.xml

Archive the artefacts: releng/org.eclipse.ocl.releng.build-site/target/*.zip,releng/org.eclipse.ocl.releng.build-site/target/publisher.properties,releng/org.eclipse.ocl.releng.build-site/target/downloads.sh,releng/org.eclipse.ocl.releng.build-site/target/updates.sh

Trigger Promoter when table using releng/org.eclipse.ocl.releng.build-site/target/publisher.properties