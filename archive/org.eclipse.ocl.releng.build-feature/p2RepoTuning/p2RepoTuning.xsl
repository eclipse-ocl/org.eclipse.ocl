<xsl:stylesheet xmlns:xsl='http://www.w3.org/1999/XSL/Transform' version="1.0">
	<xsl:output encoding="UTF-8" method="xml" indent="yes" />
	<xsl:strip-space elements="*" />
	<xsl:param name="repo_type" />
	<xsl:param name="version" />
	<xsl:param name="qualifier" />

	<xsl:template match="/">
		<xsl:processing-instruction name="artifactRepository">version='1.1.0'</xsl:processing-instruction>
		<xsl:apply-templates />
	</xsl:template>

	<xsl:template match="repository/properties">
		<properties size='{@size+2}'>
			<xsl:copy-of select="property" />
			<property name='p2.statsURI' value='http://download.eclipse.org/stats/modeling/mdt/ocl/updates/{$repo_type}/{$qualifier}' />
			<property name='p2.mirrorsURL' value='http://www.eclipse.org/downloads/download.php?file=/modeling/mdt/ocl/updates/{$repo_type}/{$version}&amp;format=xml&amp;protocol=http' />
		</properties>
	</xsl:template>

	<xsl:template 
		match="artifact[@classifier='org.eclipse.update.feature' and @id='org.eclipse.ocl']/properties">
		<xsl:call-template name="features_properties" />
	</xsl:template>

	<xsl:template
		match="artifact[@classifier='org.eclipse.update.feature' and @id='org.eclipse.ocl.uml']/properties">
		<xsl:call-template name="features_properties" />
	</xsl:template>

	<xsl:template 
		match="artifact[@classifier='org.eclipse.update.feature' and @id='org.eclipse.ocl.all']/properties">
		<xsl:call-template name="features_properties" />
	</xsl:template>

	<xsl:template
		match="artifact[@classifier='org.eclipse.update.feature' and @id='org.eclipse.ocl.core.sdk']/properties">
		<xsl:call-template name="features_properties" />
	</xsl:template>

	<xsl:template
		match="artifact[@classifier='org.eclipse.update.feature' and @id='org.eclipse.ocl.all.sdk']/properties">
		<xsl:call-template name="features_properties" />
	</xsl:template>

	<xsl:template
		match="artifact[@classifier='org.eclipse.update.feature' and @id='org.eclipse.ocl.master']/properties">
		<xsl:call-template name="features_properties" />
	</xsl:template>
	
	<xsl:template
		match="artifact[@classifier='org.eclipse.update.feature' and @id='org.eclipse.ocl.examples']/properties">
		<xsl:call-template name="features_properties" />
	</xsl:template>	
	
	<xsl:template
		match="artifact[@classifier='osgi.bundle' and @id='org.eclipse.ocl']/properties">
		<xsl:call-template name="bundles_properties" />
	</xsl:template>
	
	<xsl:template name="bundles_properties">
		<properties size='{@size+1}'>
			<xsl:copy-of select="property" />
			<property name='download.stats' value='{../@id}_{$qualifier}_bundle'/>
		</properties>
	</xsl:template>
	
	<xsl:template name="features_properties">
		<properties size='{@size+1}'>
			<xsl:copy-of select="property" />
			<property name='download.stats' value='{../@id}_{$qualifier}_feature'/>
		</properties>
	</xsl:template>

	<xsl:template match="*">
		<xsl:copy>
			<xsl:for-each select="@*">
				<xsl:copy-of select="." />
			</xsl:for-each>
			<xsl:apply-templates />
		</xsl:copy>
	</xsl:template>

</xsl:stylesheet>
