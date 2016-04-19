<script language="JavaScript1.2" src="/ewebeditpro4/ewebeditpro.js"></script>

<%@ Page aspcompat="true" Debug=false trace="false" %>
<script language="vb" runat="server">
' DO NOT CHANGE THIS CODE
' Copyright 2002, Ektron, Inc.
'
' Latest revision date: 23-Sept-02

Function eWebEditProEditor(FieldName, Width, Height, ContentHtml)
response.write("<input type=""hidden"" name=""" & FieldName & """ value=""" & Server.HTMLEncode(ContentHtml) & """>") 
response.write("<script language=""JavaScript1.2"">" & VBCrLf)
response.write("<!--" & VBCrLf)
	If TypeName(Width) = "String" Then
		Width = """" & Width & """"
	End If
	If TypeName(Height) = "String" Then
		Height = """" & Height & """"
	End If
response.write("eWebEditPro.create(""" & FieldName & """, " & Width & ", " & Height & ");" & VBCrLf)
response.write("//-->" & VBCrLf)
response.write("</scr")
response.write("ipt>")
End Function


Function eWebEditProPopupButton(ButtonName, FieldName)
response.write("<script language=""JavaScript1.2"">" & VBCrLf)
response.write("<!--" & VBCrLf)
response.write("eWebEditPro.createButton(""" & ButtonName & """, """ & FieldName & """);" & VBCrLf)
response.write("//-->" & VBCrLf)
response.write("</scr")
response.write("ipt>")
End Function
</script>