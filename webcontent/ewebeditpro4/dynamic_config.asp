<% Response.Buffer = TRUE %>
<%
'  dynamic_config.asp
' --------------------------------------------------------------------------------
' Copyright 2004, Ektron, Inc.
' Latest revision date: 17-Jun-2004
'
' Permission given by Ektron to eWebEditPro customers to modify this file.
' --------------------------------------------------------------------------------
'
' Example of how the eWebOperation object can be used to 
' produce configuration data for eWebEditPro.
'
' To view the full Dynamic Configuration API description please refer to the 
' eWebEditPro documentation or the DynamicConfigAPI.txt file installed with 
' the eWebEditPro product.
'
' !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
' This file DOES NOT need to be used to dynamically create a configuration.
' It is ONLY meant as an example of how the API can be used by an ASP page
' to transmit a configuration from parameters.
' !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
'
' This is how this example script is called from the 
' client side HTML:
'
'   <input type=hidden name="MyContent1" value="&lt;p&gt;Initial content&lt;/p&gt;"> 
'   <script language="JavaScript1.2">
'   if (typeof eWebEditPro == "object")
'    {
'       eWebEditPro.parameters.config = "dynamic_config.asp?ui=Minimal&lk=" + escape(LicenseKeys)
'       eWebEditPro.create("MyContent1", "100%", 400);
'    }
'   </script> 
'
' --------------------------------------------------------------------------------
'
' Here is a description of each of the URL parameters:
'
' lk         - The license key from Ektron.  This is used to determine what options are
'              enabled by the key value.  Enabled options are automatically addeed to the 
'              editor's configuration data.  If this value is not given, then the local
'              copy of the license should be used.  If this value is not given or is empty
'              then only the default functionality is added.  The other functionality can
'              be added programmatically by modifying this script.
'      
' ui         - The level of complexity to the configuration data.  These values can be:
'              (NOTE:  This parameter value is case sensative.)
'                   None     - No configuration.  Start fresh.
'                   Minimal  - Basic functionality for basic content formatting.
'	          (def) Standard - Standard feature set as given by the installed configuration file.
'                   Maximum  - Full featured where all options are endabled and all toolbars shown.
'   
' dd         - If the value is "Design" then the Data Designer settings are added to the configuration.
'              If the value is "Entry" then the Data Entry settings are added to the configuration.
'              Any other values are ignored.
'
' btn        - The extra command button to place on a new toolbar.
'
' cap        - The caption to use with the command.
'
' img        - The image to use with the command.  If not included, then the caption is used.
'       
' ---------------------------------------------------------------

%>
<% 

    dim objCfg 
    Dim cfgxml
    
    cfgxml = CreateConfigFromOptions
    
    Response.ContentType = "text/xml"
    Response.Write(cfgxml)
 
' ================================================

' ------------------------------------------------
Function CreateConfigFromOptions()  

    dim sLicenseKey
    dim cfgType
    dim startupenum
    
    sLicenseKey = RetrieveLicense
    cfgType = RetrieveStartConfig
    
    set objCfg = CreateObject("eWepOperation.Configuration")
    
    ' The enumerations for the Configuration object are:
    ' (Remember, these are case sensative.)
    '    None
    '    Minimal
    '    Standard
    '    Maximum
    startupenum = objCfg.Constant(cfgType)  ' Converts the string into a constant.
    objCfg.CreateSettings startupenum, sLicenseKey
    
    CreateDataDesignSettings
    CreateOptionalCommand
    
    CreateConfigFromOptions = sDebugMessage & objCfg.Configuration()
    
    set objCfg = Nothing
End Function  

Sub CreateOptionalCommand()
    ' objCfg is used as a global
    ' and must be set before this is called.
    
    dim strCmdName
    dim strCaption
    dim strImg
 
    if Request.QueryString("btn").Count > 0 then
        strCmdName = Request.QueryString("btn")
        
        if Request.QueryString("cap").Count > 0 then
            strCaption = Request.QueryString("cap")
        else
            strCaption = strCmdName
        end if
        
        if Request.QueryString("img").Count > 0 then
            strImg = Request.QueryString("img")
        else
            strImg = ""
        end if
   
	    dim oCmd
        set oCmd = objCfg.CommandSet.CreateCommand()
	    oCmd.Name = strCmdName
	    oCmd.Caption = strCaption
        oCmd.ToolTip = strCaption  ' the tool tip is the same as the caption, in this example
	    oCmd.ImageLink = strImg
	    objCfg.CommandSet.Add(oCmd)
        
        ' We are just creating a special toolbar for this command,
        ' however, an existing toolbar could be used.
	    dim oTB
        set oTB = objCfg.ToolbarSet.CreateToolbar()
	    oTB.Name = "mycustomtoolbar"
	    oTB.Add(oCmd)
	    'objCfg.ToolbarSet.AddAt(oTB, 1) ' use to specify location
        objCfg.ToolbarSet.Add oTB  ' appends
    end if  
    
End Sub

Function CreateDataDesignSettings()
    ' objCfg is used as a global
    ' and must be set before this is called.

    dim startupenum
    dim iDataDesign

    iDataDesign = RetrieveDataDesign
   
    ' This is tied to XML, so check that first.
    ' XML Tags are determined by values in the license key
    ' which was used when the initial options were 
    ' created using CreateSettings.
    '
    ' Enumeration values for the Functionality object are:
    ' (Remember, these are case sensative.)
    '    FormElements
    '    SpellCheck
    '    OfficeIntegration
    '    TableEditor
    '    Media
    '    XMLTags
    '    XMLDesign
    '    XMLEntry
    '    EditHTML
    startupenum = objCfg.Constant("XMLTags")
    if objCfg.Functionality.IsEnabled(startupenum) then
      select case iDataDesign
        case "Design", "design"
            startupenum = objCfg.Functionality.Constant("XMLDesign")
            objCfg.Functionality.Include startupenum, True
            
            ' Enumeration values for the view are:
            '    Wysiwyg 
            '    Source 
            '    DataDesign 
            '    DataEntry
            startupenum = objCfg.Functionality.Constant("DataDesign")
            objCfg.ExpertUser.View = startupenum
        
        case "Entry", "entry"
            startupenum = objCfg.Functionality.Constant("XMLEntry")
            objCfg.Functionality.Include startupenum, True
    
            startupenum = objCfg.Functionality.Constant("DataEntry")
            objCfg.ExpertUser.View = startupenum
        
      end select
    end if
 
End Function

' ------------------------------------------------
Function RetrieveStartConfig()
    if Request.QueryString("ui").Count > 0 then
        RetrieveStartConfig = Request.QueryString("ui")
    else
        RetrieveStartConfig = "Standard"
    end if  
End Function

' ------------------------------------------------
' Retrieve the license.
'
' The license key spedifies some functionality that is
' available to the editor.  If this functionality is 
' included in the key then that section of the configuration
' is automatically added.
'
' For this example, the license can be in the locations
' listed below, and the locations are searched in the
' order shown below.
'
'    1.  Passed as the 'lk' URL parameter.
'    2.  The ewebeditprolicensekey.txt file located in
'        the server directory where eWebEditPro is 
'        installed from.
'    3.  A database entry.  This is not implemented in
'        this example.  The implementation is site 
'        specific so it is outside of this samples scope.
'
Function RetrieveLicense()
    if (Request.QueryString("lk").Count > 0) then
        RetrieveLicense = Request.QueryString("lk")
    end if
    
    if len(RetrieveLicense) = 0 then
        ' If no license was given, get from the local system.
        RetrieveLicense = RetrieveLocalLicense
    end if
End Function

' ------------------------------------------------
' Retrieves the license from the local license key
' file or from a database.
Function RetrieveLocalLicense()
    dim sLicense
    
    sLicense = RetrieveLicenseFromLocalFile
    if len(sLicense) = 0 then
        ' No local file, so maybe we have it stored
        ' in a database.
        sLicense = RetreiveLicenseFromDatabase
    end if
  
    RetrieveLocalLicense = sLicense
End Function

' ------------------------------------------------
' Retrieves the setting for whether Data Design
' or Data Entry should be included.
Function RetrieveDataDesign()
    if (Request.QueryString("dd").Count > 0) then
        RetrieveDataDesign = Request.QueryString("dd")
    end if  
End Function

' ------------------------------------------------
' Retrieve the license from the local license
' file.
Function RetrieveLicenseFromLocalFile()
    ' Local File
    dim objFS  'fsObject
    dim objFile  'fileObject
    dim strBuffer
    dim strFileName
    dim pLoc
    dim pComment
    dim pStart
    dim pEnd
    dim sQuote
    
    Const ForReading = 1
    Const TristateFalse = 0
    Const LicenseKeySetting = "var LicenseKeys"
    
    strFileName = Server.MapPath(".") & "\ewebeditprolicensekey.txt"
    
    set objFS = Server.CreateObject("Scripting.FileSystemObject")    
	if isObject(objFS) then
		set objFile = objFS.OpenTextFile(strFileName, ForReading, tristateFalse)
		if Not objFile.AtEndOfStream then
			do While Not objFile.AtEndOfStream
                ' Look for the license key entry.
				strBuffer = objFile.ReadLine
				pLoc = instr(1, strBuffer, LicenseKeySetting)
                if pLoc > 0 then  
                    ' Check if commented out
                    ' A more extensive check should be made for both
                    ' types of comments.
                    pComment = instr(1, strBuffer, "//")
                    if 0 = pComment or pComment > pLoc then
                        ' Parse out the license value
                        sQuote = Chr(34)
                        pStart = instr(pLoc, strBuffer, sQuote)
                        if 0 = pStart then
                            sQuote = Chr(39)
                            pStart = instr(pLoc, strBuffer, sQuote)
                        end if
                        
                        if pStart > 0 then
                            pStart = pStart + 1  ' skip the quote
                            pEnd = instr(pStart + 1, strBuffer, sQuote)
                            if pEnd > 0 then
                                RetrieveLicenseFromLocalFile = mid(strbuffer, pStart, pEnd - pStart)
                                Exit Function
                            end if
                        end if
                    end if
                    
                    'pLoc = pLoc + len(LicenseKeySetting)
                end if
			Loop
		end if	
	end if
    
End Function

' ------------------------------------------------
' Retrieve the license from a database where it
' is stored.
Function RetreiveLicenseFromDatabase()
    
    ' --------------------------------------
    ' Add your database functionality here.
    ' --------------------------------------

End Function
%>

