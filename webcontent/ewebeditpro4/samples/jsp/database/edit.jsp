<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<!-- modify date: 2001-03-19 -->
<head>
<title>Edit Page JSP</title>

<% String szContent = ""; %>
<% String szTitle = ""; %>
<%@ include file="../../../ewebeditpro.jsp" %>
<script language="JavaScript">
		function PopUpWindow (url, hWind, nWidth, nHeight, nScroll) {
			var cToolBar = "toolbar=0,location=0,directories=0,status=0,menubar=0,scrollbars=" + nScroll + ",resizable=0,width=" + nWidth + ",height=" + nHeight
	  		var popupwin = window.open(url, hWind, cToolBar);
			if (null == popupwin)
			{
				alert("Unable to open pop-up window.  Please turn off your pop-up blocker and try again.");
			}
		}
</script>
</head>
<body>

<a href="index.jsp"><font face="verdana, arial, helvetica" size="2">Back</font></a> 
<form action="save.jsp" method="POST">
<% if(request.getParameter("id") != null) { %>  <!--- If we are not editing content, but new document --->
	<% szContent = getContent(szODBCNAME, request.getParameter("id")); %>
	<% szTitle = getTitle(szODBCNAME, request.getParameter("id")); %>  <!---  passing ODBC name and Content ID  --->
	<INPUT TYPE='HIDDEN' NAME='id' VALUE='<%= request.getParameter("id") %>'>
<%  } %>
<input type="text" size=50 name="edit_title" value="<%= szTitle %>">&nbsp;&nbsp;&nbsp;<input type="Submit" name="btnSave" value="Save">&nbsp;<input type="Button" name="btnCancel" value="Cancel" onclick="location.href='index.jsp';"><br>
	<%= eWebEditProEditor("MyContent1", "100%", "85%", szContent) %>  
	<script language="JavaScript1.2">
	<!--
	eWebEditPro.addEventHandler("onready", "initTransferMethod(eWebEditPro.event.srcName, 'imagemanager.jsp?editorname=' + eWebEditPro.event.srcName)");
	function initTransferMethod(sEditor, strURL)
	{
		if (eWebEditPro.instances[sEditor].editor)
		{
			eWebEditPro.instances[sEditor].editor.MediaFile().setProperty("TransferMethod", strURL);
		}
	}
	// -->
	</script>
</form>
</body>
</html>
