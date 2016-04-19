<%@ Control Language="vb" AutoEventWireup="false" %>

<!--  
	Copyright 2002,	Ektron Inc.  Amherst NH  
	Latest Revision: 01-Apr-02
-->

<script language="VB" runat="server">
	Public Width As String
	Public Height As String
	Public Property Text As String
 	 	Get
 			Return _Value
		End Get
		Set
	   		_Value = Value
		End Set
	End Property
	Public Property Value As String
 	 	Get
 			Return _Value
		End Get
		Set
	   		_Value = Value
		End Set
	End Property
	
	Private _Value as String = ""
</script>

<script language="JavaScript1.2" src="/ewebeditpro4/ewebeditpro.js"></script>

<input type="hidden" name="<% =id %>" value="<% =Server.HTMLEncode(_Value) %>"> 

<script language="JavaScript1.2">
<!--
	eWebEditPro.create("<% =id %>", "<% =Width %>", "<% =Height %>");
//-->
</script>






	
