<script language="JavaScript" src="/ewebeditpro4/ewebeditpro.js"></script>

<% String szODBCNAME = "ewebeditpro4"; %>
<%@ page import="java.sql.*" %>
<%! 

public String eWebEditProField(String EditorName, String FieldName, String SetType, String GetType, String ContentHtml)
{
	String strRender = "";
	if (EditorName != FieldName)
	{
		String strHtml = eWebEditProEscape(ContentHtml);
		strRender = strRender + "<input type='hidden' name='" + FieldName + "' value =\"" + strHtml + "\">";
	}

	strRender = strRender + "\n<script language='JavaScript1.2' type='text/javascript'>";
	strRender = strRender + "\n<!--";
	strRender = strRender + "\neWebEditPro.defineField('" + EditorName + "', '" + FieldName + "', '" + SetType + "', '" + GetType + "');";
	strRender = strRender + "\n//-->";
	strRender = strRender + "\n</script>";
	
	return(strRender);
}

public String eWebEditProEditor(String FieldName, String Width, String Height, String ContentHtml)
	{
	String strHtml = eWebEditProEscape(ContentHtml);
	String test = "<input type='hidden' name='" + FieldName + "' value =\"" + strHtml + "\">";
	test = test + "\n<script language='JavaScript1.2'>";
	test = test + "\n<!--";
	test = test + "\neWebEditPro.create('" + FieldName + "','" + Width + "','" + Height + "');";
	test = test + "\n//-->";
	test = test + "\n</script>";
		
	return(test);
} 
public String eWebEditProButton(String ButtonName, String FieldName)
	{
    String test = "<script language='JavaScript1.2'>";
	test = test + "\n<!--";
	test = test + "\neWebEditPro.createButton('" + ButtonName + "','" + FieldName + "');";
	test = test + "\n//-->";
	test = test + "\n</script>";
		
	return(test);
}

public String replaceString(String str, String pattern, String replace) {
   int s = 0;
   int e = 0;
 StringBuffer result = new StringBuffer();
    
  while ((e = str.indexOf(pattern, s)) >= 0) {
    result.append(str.substring(s, e));
    result.append(replace);
    s = e+pattern.length();
      }
    result.append(str.substring(s));
    return result.toString();
    }

public String eWebEditProEscape(String html) {
	    String szContent = html;
	  	   return replaceString(replaceString(replaceString(replaceString(szContent,"&", "&amp;"),"<", "&lt;"),"\"", "&quot;"),">","&gt;");
    }



public String actionDatabase(String ODBCName, String fieldName, String sqlString, String action)
	{
	String strString = "ok";
   	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;
	ResultSetMetaData rsmd = null;
	
	try {
		// Load the jdbc driver
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		// Connect to the database
		con = DriverManager.getConnection("jdbc:odbc:"+ODBCName);
		// Create a statement object
		stmt = con.createStatement();
		// Execute query	
		String sqlquery = sqlString; 
		rs = stmt.executeQuery(sqlquery);
		// Display result set
		if (action == "read"){
			rsmd = rs.getMetaData();
			while (rs.next()) {
						// 1 is the first Column
						strString = rs.getString(fieldName);
			}
		}
	}
	catch (ClassNotFoundException cnfe) {
		return "Could not find database " + ODBCName;
	}
	catch (SQLException sqle) {
	return "SQL Error with database " + ODBCName + " : " + sqlString;
		}
	finally {
		try {
			if (rs != null) rs.close();
			if (stmt != null) stmt.close();
			if (con != null) con.close();
		}
		catch (SQLException e) {
			// ignore
		}
	}
	return strString;
}

public String getContent(String ODBCName, String id)
{
	String strSQL = "SELECT edit_html FROM wysiwyg_tbl WHERE edit_id = " + id;
	String strContent = actionDatabase(ODBCName, "edit_html", strSQL, "read");
	return strContent;
}

public String getTitle(String ODBCName, String id)
{
	String strSQL = "SELECT edit_title FROM wysiwyg_tbl WHERE edit_id = " + id;
	String strTitle = actionDatabase(ODBCName, "edit_title", strSQL, "read");
	return strTitle;
}

public String getImageName(String ODBCName, String id)
{
	String strSQL = "SELECT  media_path & media_filename as media_filepath FROM media_tbl WHERE media_id =" + id;
	String strfilename = actionDatabase(ODBCName, "media_filepath", strSQL, "read");
	return strfilename;
}

public String DeleteImage(String ODBCName, String id)
{
	String strSQL = "DELETE FROM media_tbl WHERE media_id =" + id;
	actionDatabase(ODBCName, "media_id", strSQL, "delete");
	return id;
}

public String getImageWidth(String ODBCName, String id)
{
	String strSQL = "Select media_width FROM media_tbl WHERE media_id =" + id;
	String strImageWidth = actionDatabase(ODBCName, "media_width", strSQL, "read");
	return strImageWidth;
}

public String getImageHeight(String ODBCName, String id)
{
	String strSQL = "Select media_height FROM media_tbl WHERE media_id =" + id;
	String strImageHeight = actionDatabase(ODBCName, "media_height", strSQL, "read");
	return strImageHeight;
}

public String getImageSize(String ODBCName, String id)
{
	String strSQL = "Select media_filesize FROM media_tbl WHERE media_id =" + id;
	String strImageSize = actionDatabase(ODBCName, "media_filesize", strSQL, "read");
	return strImageSize;
}

%>
