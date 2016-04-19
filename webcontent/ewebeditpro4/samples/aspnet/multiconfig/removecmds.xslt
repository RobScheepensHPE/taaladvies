<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:param name="commands"></xsl:param>

<xsl:variable name="command-list" select="concat('|',translate(normalize-space($commands),', ','||'),'|')"/>

<xsl:output method="xml" version="1.0" encoding="UTF-16" indent="no" omit-xml-declaration="yes"/>
<xsl:strip-space elements="*"/>

<xsl:template name="filter-command">
	<xsl:param name="command-name"/>
	<xsl:choose>
		<xsl:when test="contains($command-list,concat('|',$command-name,'|'))">
			<!-- remove element -->
		</xsl:when>
		<xsl:otherwise>
			<xsl:copy>
				<xsl:apply-templates select="@*|node()"/>
			</xsl:copy>
		</xsl:otherwise>
	</xsl:choose>
</xsl:template>

<xsl:template match="cmd|command">
	<xsl:call-template name="filter-command">
		<xsl:with-param name="command-name" select="@name"/>
	</xsl:call-template>
</xsl:template>

<xsl:template match="button">
	<xsl:call-template name="filter-command">
		<xsl:with-param name="command-name" select="@command"/>
	</xsl:call-template>
</xsl:template>

<!-- strip comments -->
<xsl:template match="@*|*|text()">
	<xsl:copy>
		<xsl:apply-templates select="@*|*|text()"/>
	</xsl:copy>
</xsl:template>

</xsl:stylesheet>
