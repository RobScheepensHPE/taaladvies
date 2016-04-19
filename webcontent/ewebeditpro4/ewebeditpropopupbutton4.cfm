<!--- ColdFusion Custom Tag --->
<!---
	Ektron, Inc.
	Revision Date: 2003/08/25
--->

<cfsetting enablecfoutputonly="Yes">

<cfparam name="Attributes.Path" default="/ewebeditpro4/">
<cfparam name="Attributes.ButtonName">
<cfparam name="Attributes.FieldName">

<cfsetting enablecfoutputonly="No">

<cfoutput>
<script language="JavaScript1.2" src="#Attributes.Path#ewebeditpro.js"></script>

<script language="JavaScript1.2">
<!--
eWebEditPro.createButton("#Attributes.ButtonName#", "#Attributes.FieldName#");
//-->
</script>

</cfoutput>
