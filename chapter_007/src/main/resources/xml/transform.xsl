<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:template match="/">
    <xsl:text>&#10;</xsl:text>
    <entries>
      <xsl:for-each select="element/entryList">
        <xsl:text>&#10;</xsl:text>
        <entry>
          <xsl:attribute name="href">
            <xsl:apply-templates select="value"  />
          </xsl:attribute>
        </entry>
      </xsl:for-each>
      <xsl:text>&#10;</xsl:text>
    </entries>
  </xsl:template>
</xsl:stylesheet>