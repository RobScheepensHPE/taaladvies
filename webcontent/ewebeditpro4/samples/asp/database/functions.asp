
<% ' function.asp (01/31/03)
   
   ' This file is included to provide basic support for database
   ' connectivity in ASP.  In addition it contains a few constants
   ' that might need to be changed for your installation.

	Dim objConn, objRS
	Dim dsn
	Dim g_objDataConn  ' Connection used for storing file in database.	
	Dim g_iOpenRequests ' count of how many requests to open so that we don't close until done.
	
	''''''''''''''''''''''''''''''''''''''''''''''''''''
	''  If the DNS name is changed,
	''  then change this value to match.
	''''''''''''''''''''''''''''''''''''''''''''''''''''
	dsn = "ewebeditpro4"
	''''''''''''''''''''''''''''''''''''''''''''''''''''
	
	g_iOpenRequests = 0
	Set g_objDataConn = Nothing
	
	
Function DescriptionExists(strDescription)
	
	Dim SQL, Record, objMediaRS
	
	OpenFileDatabase
	
	SQL = "SELECT media_title" _
				& " FROM	media_tbl" _
				& " WHERE	media_title = '" & strDescription & "'"
	
	Set objMediaRS = Server.CreateObject("ADODB.RecordSet")
	objMediaRS.Open SQL, g_objDataConn
	
	DescriptionExists = (False = objMediaRS.EOF)
	
	objMediaRS.Close
	Set objMediaRS = Nothing
	
	CloseFileDatabase
End Function 

Function ContentIDFromTitle(strTitle)
	
	Dim SQL, Record, objMediaRS
	
	OpenFileDatabase
	
	SQL = "SELECT edit_id" _
				& " FROM	wysiwyg_tbl" _
				& " WHERE	edit_title = '" & strTitle & "'"
	
	Set objMediaRS = Server.CreateObject("ADODB.RecordSet")
	objMediaRS.Open SQL, g_objDataConn
	
	If (False = objMediaRS.EOF) Then
		ContentIDFromTitle = objMediaRS("edit_id")
	End If
	
	objMediaRS.Close
	Set objMediaRS = Nothing
	
	CloseFileDatabase
End Function 

Function AddNewContentToDatabase(strTitle, strHtml)
	Dim SQL
	
	' Creates SQL statement, and then inserts it into the database
	SQL = "INSERT INTO wysiwyg_tbl (edit_title, edit_html) Values ('" & strTitle & "','" & strHtml & "')" 
	
	DBConnect SQL

End Function

Function UpdateContentInDatabase(strTitle, strHtml, strID)
	Dim SQL
	
	' Creates SQL statement based on above variables and executes then
	' the command
	If len(trim(strTitle)) > 0 then
		SQL = SQL & "UPDATE wysiwyg_tbl SET edit_title = '" & strTitle & "', edit_html = '" & strHtml & "' WHERE edit_id = " & strID
	Else
		SQL = SQL & "UPDATE wysiwyg_tbl SET edit_html = '" & strHtml & "' WHERE edit_id = " & strID
	End If
		
	DBConnect SQL

End Function

Function AddFileToDatabase(strFileTitle, strWebMediaPath, strFileName, strImageDate, iFileSize, iExtensionID, iWidth, iHeight)
	Dim SQL, strUseTitle, strTempTitle, iLoop
	Dim objMediaConn
	
	OpenFileDatabase
	
	strUseTitle = strFileTitle   ' this may change if it already exists
	If Len(strUseTitle) = 0 Then
		strUseTitle = strFileName
	End If
	
	' First check if the file already exists.
	' The database wants these to be unique.
	if DescriptionExists(strUseTitle) then
		' Since this may be called from the automated
		' upload, we need to change the title to be unique.
		strTempTitle = strUseTitle
		iLoop = 0
		do
			iLoop = iLoop + 1
			strUseTitle = strTempTitle & " (" & iLoop & ")" 
		loop while(DescriptionExists(strUseTitle))
	End If
	
	' Uncomment these if you wish to directly see the values.
	'Response.Write("<p> strUseTitle : [" & strUseTitle & "]</p>")
	'Response.Write("<p> strWebMediaPath : [" & strWebMediaPath & "]</p>")
	'Response.Write("<p> strFileName : [" & strFileName & "]</p>")
	'Response.Write("<p> strImageDate : [" & strImageDate & "]</p>")
	'Response.Write("<p> iFileSize : [" & iFileSize & "]</p>")
	'Response.Write("<p> iExtensionID : [" & iExtensionID & "]</p>")
	'Response.Write("<p> iWidth : [" & iWidth & "]</p>")
	'Response.Write("<p> iHeight : [" & iHeight & "]</p>")

	' The SQL database wants actual data for these, so check.
	If Len(iFileSize) = 0 Then iFileSize = 0
	If Len(iExtensionID) = 0 Then iExtensionID = 0
	If Len(iWidth) = 0 Then iWidth = 0
	If Len(iHeight) = 0 Then iHeight = 0
	If Len(strImageDate) = 0 Then strImageDate = "01/01/2003"

	SQL = "INSERT INTO	media_tbl (media_title, media_path, media_filename, media_upload_date, media_filesize, user_name," _
				& "  site_id, media_deleted, extension_id, media_width, media_height)" _
				& " VALUES	('" & strUseTitle & "', '" & strWebMediaPath & "/', '" _
				& strFileName & "', '" & strImageDate & "', "  & iFileSize & "," _
				& " 'user name', 0, 0, " & iExtensionID & ", " & iWidth & ", " _
				& iHeight & ")"
	
	g_objDataConn.Execute SQL

	CloseFileDatabase
End Function

Function OpenFileDatabase()
	If g_objDataConn Is Nothing Then
		Set g_objDataConn = Server.CreateObject("ADODB.Connection")
		If Not g_objDataConn Is Nothing Then			
			'objConn.ConnectionString = "PROVIDER=MICROSOFT.JET.OLEDB.4.0;DATA SOURCE=" & Server.MapPath("ewebeditpro.mdb")
			g_objDataConn.ConnectionString = "DSN=" & dsn
			g_objDataConn.Open
		End If
	End If

	If Not g_objDataConn Is Nothing Then	
		g_iOpenRequests = g_iOpenRequests + 1
	End If
End Function

Function CloseFileDatabase()
	If Not g_objDataConn Is Nothing Then
		g_iOpenRequests = g_iOpenRequests - 1
	
		If 0 = g_iOpenRequests Then
			g_objDataConn.Close
			Set g_objDataConn = Nothing
		End If
	End If
End Function
    
Function MakeMediaPathName(strWebMediaPath, strFileName)
	MakeMediaPathName = strWebMediaPath & "/" & strFileName
End Function 

Function DBConnect (SQL)
   ' The DBConnect function has one parameter (SQL), and performs the
   ' work of connecting to, and querying the database.  The results
   ' are in the named objects objRS

	' Define ADO object, and attribue Data Source Name (DSN) to that object
	' Alternatively you may use DSN-less connection, which maps the 
	' physical path for the database to the connection string
	
	Set objConn = Server.CreateObject("ADODB.Connection")
	'objConn.ConnectionString = "PROVIDER=MICROSOFT.JET.OLEDB.4.0;DATA SOURCE=" & Server.MapPath("ewebeditpro.mdb")
	objConn.ConnectionString = "DSN=" & dsn 
	objConn.Open	

	' Create record set and execute SQL command
	Set objRS = Server.CreateObject("ADODB.RecordSet")
	objRS.Open SQL, objConn
End Function


Function DBClose
	'This function closes and destroys objects for the database.  It
	'should be called exactly once per page,
	
	objRS.Close
	objConn.Close
	
	Set objRS = Nothing
	Set objConn = Nothing
End Function


Function SQLFilter (Txt)
	'The SQLFilter function makes any string SQL safe, by
	'replacing single quotes with double quotes
	
	TXT = Replace(Txt, "'", "''")
	SQLFilter = TXT
End Function 

%>
 