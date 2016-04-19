<% Response.Buffer = TRUE %>
<% 
    ' dynamicconfig.asp
    '
    ' This page provides an example of how a configuration can
    ' be dynamically generated.  
    '
    ' This example uses the dynamic_config.asp page that exists
    ' in the installation directory of eWebEditPro.  That page
    ' is provided as an example of how the functionality can be
    ' used.  That page is not required for dynamic configurations.
    '
    ' In this exmaple the dynamic_config.asp page is given as
    ' the configuration file for the editor.  URL Parameters are 
    ' added to specify what we want to have in the configuration.  
    ' The URL parameters created follow what the sample 
    ' dyamic_config.asp requires.
    '
    ' The user can select what type of configuration to load and
    ' the page will reload with the selected configuration settings
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

    ' The routines in this section will extract the URL parameters.
    Dim g_strStartConfigParam
    Dim g_strDataDesignParam
    Dim g_strAddCustCommand
    
    ' Command Settings
    Dim g_strCmdName
    Dim g_strCmdCaption
    Dim g_strCmdImg
        
    ' These functions are for retrieving the parameters
    Function StartConfigParam()
        if Request.QueryString("ui").Count > 0 then
            StartConfigParam = Request.QueryString("ui")
        else
            StartConfigParam = "Standard"
        end if  
    End Function
    Function DataDesignParam()
        if (Request.QueryString("dd").Count > 0) then
            DataDesignParam = Request.QueryString("dd")
        else
            DataDesignParam = "None"
        end if
    End Function
    Function AddCustCommand()
        if (Request.QueryString("cmd").Count > 0) then
            AddCustCommand = Request.QueryString("cmd")
        else
            AddCustCommand = "None"
        end if
    End Function
    
    Sub SetCommandSettings(strCommand)
        select case strCommand
            case "imglib"
                g_strCmdName = "jsimagelibrary"
                g_strCmdCaption = "Image Library"
                g_strCmdImg = "../../../btnimageonly.gif"
           
            case "snipsel"
                g_strCmdName = "jssnippetselection"
                g_strCmdCaption = "Snippen Selection"
                g_strCmdImg = "../../../btnedit.gif"
            
            case else
                g_strCmdName = ""
                g_strCmdCaption = ""
                g_strCmdImg = ""
        end select
    End Sub
    
    g_strStartConfigParam = StartConfigParam
    g_strDataDesignParam = DataDesignParam
    g_strAddCustCommand = AddCustCommand
    SetCommandSettings g_strAddCustCommand
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
	<title>Create Your Own eWebEditPro Configuration</title>
    <!-- #include virtual="/ewebeditpro4/ewebeditpro.asp" -->
</head>

<body>

<form name="frmMain" method="post">
                    
<h2>Create Your Own eWebEditPro Configuration</h2>

<% ' These are the selectable settings which the user can use
   ' for generating an editor configuration to their specifications.
%>
Feature Level:
<select name="FeatureLevel" title="Feature Level">
    <option <% if "None" = g_strStartConfigParam then %>selected<% end if %> value="None">None 
    <option <% if "Minimal" = g_strStartConfigParam then %>selected<% end if %> value="Minimal">Minimal
    <option <% if "Standard" = g_strStartConfigParam then %>selected<% end if %> value="Standard">Standard
    <option <% if "Maximum" = g_strStartConfigParam then %>selected<% end if %> value="Maximum">Maximum
</select>
&nbsp;&nbsp;&nbsp;&nbsp;

Special Functionality:
<select name="SpecFunc" title="Special Functionality">
    <option <% if "None" = g_strDataDesignParam then %>selected<% end if %> value="None">None 
    <option <% if "Design" = g_strDataDesignParam then %>selected<% end if %> value="Design">Data Designer
    <option <% if "Entry" = g_strDataDesignParam then %>selected<% end if %> value="Entry">Data Entry
</select>
&nbsp;&nbsp;&nbsp;&nbsp;

Extra Command:
<select name="ExtCmd" title="Extra Command">
    <option <% if "None" = g_strAddCustCommand then %>selected<% end if %> value="None">None 
    <option <% if "imglib" = g_strAddCustCommand then %>selected<% end if %> value="imglib">Image Library
    <option <% if "snipsel" = g_strAddCustCommand then %>selected<% end if %> value="snipsel">Snippet Selection
</select>
&nbsp;&nbsp;&nbsp;&nbsp;

<input type="Button" onclick="ReloadPage()" value="Create Configuration"/>

<br>
<br>

<% ' The JavaScript below informs the eWebEditPro objects
   ' which configuration to use.  It specifies the dynamic
   ' configuration ASP page with parameters. %>
<script language="JavaScript1.2">
<!--
    var sSep = "?";
    
    eWebEditPro.parameters.config = "../../../dynamic_config.asp";
    
    if("<% = g_strStartConfigParam %>" != "")
    {    eWebEditPro.parameters.config += sSep + "ui=" + escape("<% = g_strStartConfigParam %>"); sSep = "&"; }
    if(("<% = g_strDataDesignParam %>" != "") && ("<% = g_strDataDesignParam %>" != "None"))
    {    eWebEditPro.parameters.config += sSep + "dd=" + escape("<% = g_strDataDesignParam %>"); sSep = "&"; }
    
    // Commands need a host of data.
    if("<% = g_strCmdName %>" != "")
    {    eWebEditPro.parameters.config += sSep + "btn=" + escape("<% = g_strCmdName %>"); sSep = "&"; }
    if("<% = g_strCmdCaption %>" != "")
    {    eWebEditPro.parameters.config += sSep + "cap=" + escape("<% = g_strCmdCaption %>"); sSep = "&"; }
    if("<% = g_strCmdImg %>" != "")
    {    eWebEditPro.parameters.config += sSep + "img=" + escape("<% = g_strCmdImg %>"); sSep = "&"; }
    
    // The configuration parameters created above are displayed on the page below here
    
    <% if "jsimagelibrary" = g_strCmdName then %>
    // These are the functions that will run with the optional commands.
    eWebEditProExecCommandHandlers["jsimagelibrary"] = function(sEditorName, strCmdName, strTextData, lData) 
    { 
    	alert("The Image Library button was clicked on.");
    } 
    <% elseif g_strCmdName = "jssnippetselection" then %>
    eWebEditProExecCommandHandlers["jssnippetselection"] = function(sEditorName, strCmdName, strTextData, lData) 
    { 
    	alert("The Snippet Selection button was clicked on.");
    } 
    <% end if %>
//-->
</script>

<% ' The following line creates the an editor object. %>
<% =eWebEditProEditor("MyContent", "100%", "80%", "<h3>Initial Content</h3>") %>

<br>

<h3>Configuration Creation Information</h3>

<% ' Other scripting functionality %>
<script language="JavaScript1.2">
<!--
    document.write("<p><b>URL Parameters:  </b>" + eWebEditPro.parameters.config + "</p>");
    
    function ReloadPage()
    {
        // Makes:
        //     dynamicconfig.asp?ui=Standard&dd=Design&cmd=None
    
        var strLevel = window.document.frmMain.FeatureLevel.value;
        var strFunc = window.document.frmMain.SpecFunc.value;
        var strCmd = window.document.frmMain.ExtCmd.value;
        var strDocName = LoadedPage();
    	
    	var strPageLoad = strDocName + "?ui=" + escape(strLevel) + "&dd=" + escape(strFunc) + "&cmd=" + escape(strCmd);
    	//alert(strPageLoad);
        
    	window.document.location.href = strPageLoad;
    }
    
    function LoadedPage()
    {
        // Returns the name of the document
    	var strUrl = window.document.URL;
    	var strName = "";
    	var pair = [];
    	pair = strUrl.split("?");
    	strName = pair[0];
    	return(strName);
    }
//-->
</script>

</form>

</body>
</html>
