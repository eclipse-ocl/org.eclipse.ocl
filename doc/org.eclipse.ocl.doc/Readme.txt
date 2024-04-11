It is no longer necessary to put JARS in the libs directory.

Instead just use Install New Software to install:
from SimRel
    Mylyn Wikmitext
    
from Orbit 2024-03
	Orbit Maven BND xalan
	Orbit Maven BND xerces
	
Apache XML Commons Serializer should be installed 'for free'

If an xalan / xml.serializer version changes edit the xalan provcressor creation fails -
edit textilebuild.xml