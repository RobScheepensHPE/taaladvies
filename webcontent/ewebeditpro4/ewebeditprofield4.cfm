<!--- ColdFusion Custom Tag --->
<!---
	Ektron, Inc.
	Revision Date: 2004-03-24
--->

<cfsetting enablecfoutputonly="Yes">

<cfparam name="Attributes.Path" default="/ewebeditpro4/">
<cfparam name="Attributes.Name" default="undefined"> <!--- alternative to EditorName --->
<cfparam name="Attributes.EditorName" default="undefined">
<cfparam name="Attributes.FieldName" default="undefined">
<cfparam name="Attributes.SetType" default="">
<cfparam name="Attributes.GetType" default="">
<cfparam name="Attributes.Value" default="">
<cfsetting enablecfoutputonly="No">

<cfif Attributes.Name neq "undefined">
<cfset Attributes.EditorName=Attributes.Name>
</cfif>

<cfoutput>

<script language="JavaScript1.2" type="text/javascript" src="#Attributes.Path#ewebeditpro.js"></script>

<cfif Attributes.EditorName neq Attributes.FieldName>
<input type="hidden" name="#Attributes.FieldName#" value="#HTMLEditFormat(Attributes.Value)#">
</cfif>

<script language="JavaScript1.2" type="text/javascript">
<!--
eWebEditPro.defineField("#Attributes.EditorName#", "#Attributes.FieldName#", "#Attributes.SetType#", "#Attributes.GetType#");
//-->
</script>

</cfoutput>