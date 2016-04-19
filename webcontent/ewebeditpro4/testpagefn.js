function clientEnv(obj, sName, sOCXName)
{
	if (typeof obj == "object")
	{
		obj.refreshStatus();
		var eskerNeeded = (obj.instanceTypes["activex"].isSupported && obj.isNetscape);
		
		var strOsName = "unknown";
		var strOsReq = "(Windows 95 or later required)";
		if (obj.isWindows)
		{
			strOsName = "Microsoft Windows";
			strOsReq = "(Windows 95 or later required)";
		}
		else if (obj.isMac)
		{
			strOsName = "Mac OS";
			strOsReq = "";
		}
		else if (obj.isSun)
		{
			strOsName = "Sun Solaris";
			strOsReq = "";
		}
		else if (obj.isUnix)
		{
			strOsName = "Unix";
			strOsReq = "";
		}
		document.writeln("Operating System: " + strOsName + " " + strOsReq + "<br>");
		if (navigator.systemLanguage)
		{
			document.writeln("System language: " + navigator.systemLanguage + "<br>");
		}
		if (navigator.userLanguage)
		{
			document.writeln("User language: " + navigator.userLanguage + "<br>");
		}
		
		var strBrowserName = "unknown";
		var strBrowserReq = "(IE or Netscape required)";
		if (obj.isIE)
		{
			strBrowserName = "Internet Explorer";
			strBrowserReq = "(IE 4.01 or later required)";
		}
		else if (obj.isNetscape)
		{
			strBrowserName = "Netscape";
			strBrowserReq = "(4.7 or later required, IE 4.01 or later must also be installed)";
		}
		else if (obj.isOpera)
		{
			strBrowserName = "Opera";
			strBrowserReq = "";
		}
		document.writeln("Browser: " + strBrowserName + ", version " + obj.browserVersion + " " + strBrowserReq + "<br>");
		var strLanguageCode = "";
		if (navigator.language) // for Netscape
		{
	    	strLanguageCode = navigator.language;
		}
	 	if (navigator.userLanguage) // for IE
		{
	    	strLanguageCode = navigator.userLanguage;
		}
		document.writeln("Language: " + strLanguageCode + "<br>");
		
		if (!obj.isSupported)
		{
			document.writeln("<i>This OS or browser version is not yet supported.</i><br>");
		}
	
		document.write(sName + " Instance Types: <br>");
		for (var i = 0; i < obj.instanceTypes.length; i++)
		{
			document.write("&nbsp;&nbsp;&nbsp;&nbsp;" + obj.instanceTypes[i].type + " supported? " + obj.instanceTypes[i].isSupported + "<br>");
		}
		document.write(sName + " Selected Type: " + obj.selectedType + "<br>");
		document.write(sName + " supported? " + obj.isSupported + "<br>");
		document.write("Automatic installation supported? " + obj.isAutoInstallSupported + "<br>");
		document.write(sName + " installed? ");
		if (obj.isAutoInstallSupported && !obj.isInstalled)
		{
			document.write("false, automatic installation failed");
		}
		else if (eskerNeeded && obj.isInstalled) 
		{
			document.write("probably, because the Ektron plug-in for " + sName + " is installed");
		}
		else
		{
			document.write(obj.isInstalled);
		}
		document.write("<br>");
		
		var strVersion = "unknown";
		if (obj.versionInstalled)
		{
			strVersion = obj.versionInstalled;
		}
		else if (!obj.isInstalled)
		{
			strVersion = "not installed";
		}
		document.write("Version of " + sOCXName + " actually installed: <span class=value>" + strVersion + "</span><br>");
		
		document.write("Esker Netscape plug-in required? " + eskerNeeded);
		if (eskerNeeded)
		{
			document.write(" Installed? " + obj.isInstalled);
			document.write("<br>(NS 4.x requires Esker 4.5, NS 6.0 requires Esker 6.4, NS 6.1 requires Esker 6.5, NS 6.2 requires Esker 6.6, NS 7 requires Esker 7, NS 7.1 requires Esker 7.2, NS 7.2 requires Esker 7.9)");
		}
		document.write("<br>");
		
		if (obj.isNetscape)
		{
			/* Display info on Esker plugin */
			/*source: Netscape's about:plugins */
			navigator.plugins.refresh(false);
			
			var numPlugins = navigator.plugins.length;
			
			if (numPlugins == 0)
			{
				document.writeln("<b><font size=+2>No plug-ins are installed.</font></b><br>");
			}
			
			for (i = 0; i < numPlugins; i++)
			{
		        var plugin = navigator.plugins[i];
				
				if (plugin.name.indexOf("Esker", 0) != -1 || plugin.name.indexOf("Ektron", 0) != -1)
				{
			        document.write("<p><font size=+1><b>");
			        document.write(plugin.name);
			        document.writeln("</b></font></p>");
			
			        document.writeln("<dl><dd>File name:");
			        document.write(plugin.filename);
			        document.write("<dd><br>");
			        document.write(plugin.description);
			        document.writeln("</dl><p>");
			
			        document.writeln("<table width=100% border=2 cellpadding=5>");
			        document.writeln("<tr><th width=20%><font size=-1>Mime Type</font></th>");
			        document.writeln("<th width=50%><font size=-1>Description</font></th>");
			        document.writeln("<th width=20%><font size=-1>Suffixes</font></th>");
			        document.writeln("<th><font size=-1>Enabled</th></tr>");
					
			        var numTypes = plugin.length;
			        for (j = 0; j < numTypes; j++)
			        {
		                var mimetype = plugin[j];
		                if (mimetype)
		                {
		                       var enabled = "No";
		                       var enabledPlugin = mimetype.enabledPlugin;
		                       if (enabledPlugin && (enabledPlugin.name == plugin.name))
							{
		                        enabled = "Yes";
							}
		                       document.writeln("<tr align=center>");
		                       document.writeln("<td>" + mimetype.type + "</td>");
		                       document.writeln("<td>" + mimetype.description + "</td>");
		                       document.writeln("<td>" + mimetype.suffixes + "</td>");
		                       document.writeln("<td>" + enabled + "</td>");
		                       document.writeln("</tr>");
		                }
			        }
			        document.write("</table>");
				}
			}
		}
	}
}