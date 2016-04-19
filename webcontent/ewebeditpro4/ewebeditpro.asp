<% 
'
' DO NOT CHANGE THIS CODE
' Copyright 2001-2003, Ektron, Inc.
'
' Latest revision date: 19-Nov-2003
'
%>
<script language="JavaScript1.2" src="/ewebeditpro4/ewebeditpro.js"></script>
<%

Function eWebEditProField(EditorName, FieldName, SetType, GetType, ContentHtml)
	If EditorName <> FieldName Then
%>
<input type="hidden" name="<% =FieldName %>" value="<% =Server.HTMLEncode(ContentHtml) %>">
<%
	End If
%>
<script language="JavaScript1.2" type="text/javascript">
<!--
	eWebEditPro.defineField("<% =EditorName %>", "<% =FieldName %>", "<% =SetType %>", "<% =GetType %>");
//-->
</script>
<%
End Function

Function eWebEditProEditor(FieldName, Width, Height, ContentHtml)
%>
<input type="hidden" name="<% =FieldName %>" value="<% =Server.HTMLEncode(ContentHtml) %>">
<script language="JavaScript1.2">
<!--
	<%	
	If TypeName(Width) = "String" Then
		Width = """" & Width & """"
	End If
	If TypeName(Height) = "String" Then
		Height = """" & Height & """"
	End If
	%>
	eWebEditPro.create("<% =FieldName %>", <% =Width %>, <% =Height %>);
//-->
</script>
<%
End Function

Function eWebEditProPopupButton(ButtonName, FieldName)
%>
<script language="JavaScript1.2">
<!--
	eWebEditPro.createButton("<% =ButtonName %>", "<% =FieldName %>");
//-->
</script>
<%
End Function
%>
