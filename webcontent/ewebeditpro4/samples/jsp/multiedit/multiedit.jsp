<!--  
	Copyright 2001-2003,	Ektron Inc.  Amherst NH  
	Latest Revision: 2003-12-17
-->

<html>
<head>
<title>eWebEditPro</title>

<%@ include file="../../../ewebeditpro.jsp" %>

</head>
<body>
<script language="JavaScript1.2">
<!--
if (typeof eWebEditPro != "object")
{
	var sMsg = "Failed to create the eWebEditPro JavaScript object.";
	sMsg += "\nMost likely the path (eWebEditProPath) in file ewebeditpro.js is not correct.";
	alert(sMsg);
}
//-->
</script>
<P> 
<font face="Arial" size="3">
This is a sample web page with:<br>
<ul>
<li>Two eWebEditPro editors</li>
<li>A 'Paste' button to insert HTML text into the corresponding editor</li>
<li>An 'Edit' button to popup a window with the editor</li>
</ul></font>

<p>
<form name="frmMain" method="post">
	<br>
	<br>
	<% String strContent1 = "<p>Hello Editor 1</p>"; %>
	<%= eWebEditProEditor("MyContent1", "100%", "250", strContent1) %>
	<%= eWebEditProField("MyContent1", "MyContent1", "htmlbody", "htmlbody", "") %>
	<%= eWebEditProField("MyContent1", "TextOnly1", "", "text", "") %>
	<br>
	<input type=text name="Text1" value="&lt;i&gt;paste&lt;/i&gt; &lt;b&gt;this&lt;/b&gt;"> 
	<input type="button" name="btnPaste1" value="Paste" onclick="eWebEditPro.MyContent1.pasteHTML(Text1.value)">
	<br>
	<% String strContent2 = "<p>Hello Editor 2</p>";	%>
	<%= eWebEditProEditor("MyContent2", "100%", "250", strContent2) %>
	<BR>
	<textarea name="ta1" cols=80 rows=5>Click 'Edit' to edit this content with the eWebEditPro editor.</textarea><br>
	<!-- 

	The following JSP Code a button that, when clicked, will open a page with
	the editor in a popup window.
	
	The name of the button does not matter.
	
	The second argument (e.g., "ta1") MUST MATCH the name of the standard HTML element 
	(typically a TEXTAREA).
	
	-->
	<%= eWebEditProButton("btnEdit", "ta1") %>
	<br>
</form>


</body>
</html>
