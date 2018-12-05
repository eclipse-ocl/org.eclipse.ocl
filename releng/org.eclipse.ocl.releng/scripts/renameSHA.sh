#!/bin/bash
#*******************************************************************************
# Copyright (c) 2013, 2018 Willink Transformations, University of York and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v2.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v20.html
#
# Contributors:
#     Adolfo Sanchez-Barbudo Herrera (Univerisity of York) - Initial API and implementation
#*******************************************************************************
if [ $# -ne 1 ]
then
   echo "usage example: ./renameSHA.sh RC1"
   exit;
fi

for i in *.sha1
do
  iContent=`cat $i`
  newName=${i/$1/}
  newContent=${iContent/$1/}
  echo "Introducing $newContent into $newName"
  echo "$newContent" > "$newName"
  rm $i
done
