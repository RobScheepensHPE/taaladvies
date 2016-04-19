<cfinclude template="#trim(replace(form.editor_media_path, "-", "", "ALL"))#/imagepath.cfm">

<cfset variable.uploadcommand="#form.actiontyp#">

<cfif variable.uploadcommand eq "uploadcontent">
	<cfset variable.contenttitle="#form.content_title#">
	<cfset variable.contentsize="#form.content_size#">
	<cfset variable.contentdesc="#form.content_description#">
	<html>
	<body>
	<H1>Content Successfully Received</h1>
	<p style='color:red'>However, the sample page that received the content <i>does not</i> save the posted content on the server.</p>
	<p style='color:red; font:bold'>The Content is not saved.</p>
	<p style='color:red'>Modify the sample receiving page to save the content or specify another receiving page that does save the content.</p>
	<p style='color:red; font:bold'>Click on 'Undo' to restore your content.</p>
	<br>
	Content Title:&nbsp;&nbsp;
	<cfoutput>#variable.contenttitle#</cfoutput>
	<br>
	Content Size:&nbsp;&nbsp;
	<cfoutput>#variable.contentsize#</cfoutput>
	<br>
	Content Description:&nbsp;&nbsp;" 
	<cfoutput>#variable.contentdesc#</cfoutput>
	<br>
	
	</body>
	</html>
</cfif>
<cfif variable.uploadcommand eq "uploadfile">
	<CF_ewebeditprouploadfile 
		allowexts="#trim(replace(form.extensions, "-", "", "ALL"))#" 
		destdir="#variable.DestDir#"
		renamefile="Yes" 
		uploadfile="uploadfilephoto" 
        nameconflict="MAKEUNIQUE"
		TempDir="#variable.DestDir#">
		
<XML ID="EktronFileIO">
<?xml version="1.0"?>
<UPLOAD>
  <FILEINFO ID="0" discard="False">
    <FSRC></FSRC>
    <FURL><cfoutput>#trim(replace(form.editor_media_path, "-", "", "ALL"))#/#uploadedfilename#</cfoutput></FURL>
    <FID></FID>
    <FSIZE><cfoutput>#form.file_size#</cfoutput></FSIZE>
    <DESC><cfoutput>#form.file_title#</cfoutput></DESC>
    <THUMBURL></THUMBURL>
    <THUMBHREF></THUMBHREF>
    <FTYPE><cfoutput>#form.file_type#</cfoutput></FTYPE>
    <DWIDTH><cfoutput>#form.width#</cfoutput></DWIDTH>
    <DHEIGHT><cfoutput>#form.height#</cfoutput></DHEIGHT>
	<!---
	Note:
		ColdFusion chokes on DBORDER because ColdFusion tags used to start with DB
		so it automatically converts DB to CF which becomes CFORDER which is an invalid tag
	--->	
    <cfoutput><DB</cfoutput>ORDER>Ignores this</DB<cfoutput>ORDER></cfoutput>
    <FRAGMENT></FRAGMENT>
    <FERROR value="0"></FERROR>
  </FILEINFO>
</UPLOAD>
</XML>	
	
	
</cfif>

