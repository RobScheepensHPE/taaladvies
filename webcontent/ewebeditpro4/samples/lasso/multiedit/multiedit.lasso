<!--
	Copyright 2003,	Ektron Inc.  Amherst NH  
	Latest Revision: 08-25-2003
-->

<html>
<head>
<title>eWebEditPro</title>

<!-- Include the JavaScript file which contains the license key and the file locations of all the core files -->
<script language="JavaScript1.2" src="/ewebeditpro4/ewebeditpro.js"></script>
</head>
<body>
<script language="JavaScript1.2">
<!--
if (typeof eWebEditPro != "object")	{
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
<li>Three eWebEditPro editors</li>
<li>A 'Paste' button to insert HTML text into the corresponding editor</li>
<li>A 'Insert Superscript' button and a 'Insert Subscript' button to insert HTML text into the corresponding editor in forms of superscript and subscript</li>
<li>An 'Edit' button to popup a window with the editor</li>
</ul></font>
<p>
<form name="frmMain" method="post">
	<br>
	<!-- Inline editor 1 -->
	[var_set: 'MyContent1'='<p>Hello Editor 1 with special character: <"></p>']
	<input type=hidden name="MyContent1" value="[var:'MyContent1']"> 
	<script language="JavaScript1.2">
	<!--
	if (typeof eWebEditPro == "object")		{
		eWebEditPro.create("MyContent1", "100%", 200);
	}
	//-->
	</script> 
	<br>
	<input type=text name="Text1" value="<i>paste</i> <b>this</b>"> 
	<input type="button" name="btnPaste1" value="Paste" onclick="eWebEditPro.MyContent1.pasteHTML(Text1.value)">
	<br>
	<!-- Inline editor 2 -->
	[var_set: 'MyContent2'='<p>Hello Editor 2</p>']
	<input type=hidden name="MyContent2" value="[var:'MyContent2']"> 
	<script language="JavaScript1.2">
	<!--
	if (typeof eWebEditPro == "object")		{
		eWebEditPro.create("MyContent2", "100%", 200);
	}
	//-->
	</script> 
	<br>
	<input type=text name="Text2" value="<i>paste</i> <b>this</b>"> 
	<INPUT type="button" value="Insert Superscript" id=button1 name=button1 
	onClick="eWebEditPro.MyContent2.pasteHTML('<SUP>' + Text2.value + '</SUP>');">&nbsp;&nbsp;&nbsp;<INPUT type="button" value="Insert Subscript" id=button2 name=button2 
	onClick="eWebEditPro.MyContent2.pasteHTML('<SUB>' + Text2.value + '</SUB>');">
	<BR>
	<input type=button value="&copy;Copyright Symbol" onclick="eWebEditPro.MyContent2.pasteHTML('<sup>&copy;</sup>')">
	<input type=button value="&#153;Trademark Symbol" onclick="eWebEditPro.MyContent2.pasteHTML('<sup>&#153;</sup>')">
	<input type=button value="&reg;Registered trademark Symbol" onclick="eWebEditPro.MyContent2.pasteHTML('<sup>&reg;</sup>')">
	<br>
	
	<textarea name="ta1" cols=80 rows=5>Click 'Edit' to edit this content with the eWebEditPro editor.</textarea><br>
	<!--
	The following JavaScript creates a button that, when clicked, will open a page with
	the editor in a popup window.
	
	The name of the button does not matter.
	
	The second argument (e.g., "ta1") MUST MATCH the name of the standard HTML element 
	(typically a TEXTAREA).
	-->
	<script language="JavaScript1.2">
	<!--
		eWebEditPro.createButton("btnEdit", "ta1");
	//-->
	</script> 
	<br>

	<!--
	Because this is a static HTML page, there is not form action or submit button.
	Normally, a submit button would as shown:
	
	<input type=submit name="btnSubmit" value="Submit"> 
	
	When the form is submitted, all editors will save their content to their standard
	HTML elements (e.g., hidden fields) to be posted to the dynamic web server.
	-->
</form>


</body>
</html>


