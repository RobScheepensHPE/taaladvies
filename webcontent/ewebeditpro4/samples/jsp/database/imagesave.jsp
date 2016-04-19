<%@ page language="java" import="com.jspsmart.upload.*"%>
<jsp:useBean id="mySmartUpload" scope="page" class="com.jspsmart.upload.SmartUpload" />
<%@ include file="../../../ewebeditpro.jsp" %>

<HTML>
<head>
<script language="JavaScript1.2">
	function updatelist(editor){
		var place='imagelist.jsp?editor=' + editor;
		parent.library.location.href = place;
	}
</script>
</head>
<BODY bgcolor="d3d3d3">

<%
	int count=0;     // Initialization
	String strSQL = "";
	//String szTitle = "";
	String szEditor = "";
	String retest = "";
	String strTitle = "";
	String strWidth = "";
	String strHeight = "";
	String strFileSize = "";
	
	String ImageFilePathFromRoot = "/ewebeditpro4/upload/";
	mySmartUpload.initialize(pageContext);
	mySmartUpload.setAllowedFilesList("jpg,gif");
	mySmartUpload.setTotalMaxFileSize(5000000);
	// Upload	
	try {
		mySmartUpload.upload();
		// Save the files with their original names in the virtual path "/ewebeditpro4/upload"
		// if it doesn't exist try to save in the physical path "/upload"
		 count = mySmartUpload.save(ImageFilePathFromRoot);
		// Save the files with their original names in the virtual path "/upload"
		// count = mySmartUpload.save("/upload", mySmartUpload.SAVE_VIRTUAL);
		// Display the number of files uploaded 
		out.println(count + " file uploaded.");
	} catch (Exception e) { 
		out.println(e.toString());
	}
		
		// when using SmartUpload, you got get the parmameters this way
		strTitle = mySmartUpload.getRequest().getParameter("imagetitle"); //get image title
		strWidth = mySmartUpload.getRequest().getParameter("width");  //get width
		strHeight = mySmartUpload.getRequest().getParameter("height"); //get height
		strFileSize = mySmartUpload.getRequest().getParameter("file_size");  //get file size
		
		java.util.Enumeration ee = mySmartUpload.getRequest().getParameterNames();
		String key = (String)ee.nextElement();
		String[] values = mySmartUpload.getRequest().getParameterValues(key);	
		//szTitle = values[0];
		String ekey = (String)ee.nextElement();
		String[] evalues = mySmartUpload.getRequest().getParameterValues(ekey);	
		szEditor =  evalues[0];
	if (!mySmartUpload.getFiles().getFile(0).isMissing()){
		//String ImageFileName = ImageFilePathFromRoot + "/" + mySmartUpload.getFiles().getFile(0).getFileName();
		String ImageFileName = mySmartUpload.getFiles().getFile(0).getFileName();
		// Retreive Requests' names
	%>

	<%
		strSQL = "INSERT INTO media_tbl (media_title, media_filename, media_path, media_width, media_height, media_filesize, site_id, media_deleted,  extension_id) VALUES ('" + strTitle + "','" + ImageFileName + "','" + ImageFilePathFromRoot + "','" + strWidth + "','" + strHeight + "','" + strFileSize + "',0,0,1)";
		retest = actionDatabase(szODBCNAME, "", strSQL, "write"); %>
 		<script language="javascript">
			top.opener.eWebEditPro.instances.MyContent1.insertMediaFile('<%= ImageFilePathFromRoot %><%= ImageFileName %>', 0, '<%= strTitle %>');
			top.close();		
		</script>
		 
		<%
		}
		%>
<script language="JavaScript1.2">
	var timeID = setTimeout("updatelist('<%= szEditor %>')",1000)
</script>
<font face="Verdana" size="2"><div align="center">Would you like to upload another file?<br><br><a href="imageuploader.jsp?editor=<%= szEditor %>">Yes</a> <a href="#" onClick="parent.close();">No</a></div></font>
</BODY>
</HTML>