<!-- Ektron Inc.  Amherst NH  Copyright ©1999-2004 -->
<!--
  Name: multiedit.cfm
   
  Descr:  Example on showing multi-editors.  Examples of in-line editors and an example of a popup editor.
  
  Revision Date: 2004-02-09
  
-->
<html>
<head>
<title>eWebEditPro</title>
<script language="JavaScript1.2">
<!--
function PopUpWindow (url, hWind, nWidth, nHeight, nScroll) {
	var cToolBar = "toolbar=0,location=0,directories=0,status=0,menubar=0,scrollbars=" + nScroll + ",resizable=0,width=" + nWidth + ",height=" + nHeight
 		var popupwin = window.open(url, hWind, cToolBar);
		if (null == popupwin)
		{
			alert("Unable to open pop-up window.  Please turn off your pop-up blocker and try again.");
		}
}
//-->
</script>
</head>
<body bgcolor="White">
	<P> 
	<font face="Arial" size="3">
	This is a sample web page with:<br>
	<ul>
	<li>Two eWebEditPro editors</li>
	<li>A 'Paste' button to insert HTML text into the corresponding editor</li>
	<li>An 'Edit' button to popup a window with the editor</li>
	</ul>
	</font>
	<form method="post">
		<!--- Display the editors with the contents --->
		<!--- Editor 1 --->
		<cfset editor1 = "<p><b>This is Editor1</b></p>">
		<cfmodule template="../../../ewebeditpro4.cfm" EditorName="myContent1" Width="95%" Height="220" Value="#variables.editor1#">
		<cfmodule template="../../../ewebeditprofield4.cfm" EditorName="myContent1" FieldName="myContent1" SetType="htmlbody" GetType="htmlbody" Value="">
		<cfmodule template="../../../ewebeditprofield4.cfm" EditorName="myContent1" FieldName="myPlainText1" GetType="text" Value="">
		<br>
		<input type=text name="Text1" value="<i>paste</i> <b>this</b>"> 
		<input type="button" name="btnPaste1" value="Paste" onclick="eWebEditPro.myContent1.pasteHTML(Text1.value)">
		<br><br>
		
		<!--- Editor 2 --->
		<cfset editor2 = "<p><i>This is Editor2</i></p>">	
		<cfmodule template="../../../ewebeditpro4.cfm" EditorName="myContent2" Width="95%" Height="220" Value="#variables.editor2#">
		<input type=text name="Text2" value="<i>paste</i> <b>this</b>"> 
		<input type="button" name="btnPaste2" value="Paste" onclick="eWebEditPro.myContent2.pasteHTML(Text2.value)">
		<br>
		<br>
		
		<!-- 
		The following CF custom tag creates a button that, when clicked, will open a page with
		the editor in a popup window.
		
		The name of the button does not matter.
		
		The second argument (e.g., "ta1") MUST MATCH the name of the standard HTML element 
		(typically a TEXTAREA).
		-->
		<textarea name="ta1" cols=80 rows=5>Click 'Edit' to edit this content with the eWebEditPro editor.</textarea>
		<br>
		<cfmodule template="../../../ewebeditpropopupbutton4.cfm" ButtonName="btnEdit" FieldName="ta1">
	</form>
	</p>
</body>
</html>
