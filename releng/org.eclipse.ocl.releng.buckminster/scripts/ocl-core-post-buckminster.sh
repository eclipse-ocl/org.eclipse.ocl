#*******************************************************************************
# Copyright (c) 2013 E.D.Willink and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#
# Contributors:
#     E.D.Willink - initial API and implementation
#*******************************************************************************
#!/bin/bash

mv buildroot/buckminster.output/org.eclipse.ocl.releng.core.build_*-eclipse.feature/site.p2 MDT-OCL.p2.repository
mv buildroot/buckminster.output/org.eclipse.ocl.releng.core.build_*-eclipse.feature/zips MDT-OCL.downloads

/opt/public/common/apache-ant-1.8.1/bin/ant -f publishroot/publisher.ant -Dbuild.archives=${WORKSPACE}
