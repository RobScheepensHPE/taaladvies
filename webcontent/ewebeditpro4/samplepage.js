// Copyright 2004 Ektron, Inc.
// Revision Date: 2004-July-30

//
var g_WifxClientVersion = "";
if (typeof ActiveXVersionInstalled != "undefined")
{
	g_WifxClientVersion = ActiveXVersionInstalled("WebImageFX.ImageEditor") +"";
}

function IsWifxSvrInstalled()
{
	var IsWifxSvrInstalled = false;
	if (typeof cIMGEDVERSION != "undefined")
	{
		IsWifxSvrInstalled = true;
	}
	return IsWifxSvrInstalled;
}

function IsWifxClientInstalled()
{
	var IsWifxClientInstalled = false;
	if (g_WifxClientVersion != "")
	{
		IsWifxClientInstalled = true;
	}
	return IsWifxClientInstalled
}

function writeMain()
{
	with (document)
	{
		var sHTML = '<center><table><tr><td>';
		sHTML += '<a href="http://www.ektron.com" target="_blank"><img src="ektronlogo.gif" border="1"></a></td><td>';
		sHTML += '&nbsp;&nbsp;<a href="samplepage.htm#EWEP">' + g_sEWEPProduct + ' Information</a><br>';
		if (IsWifxSvrInstalled() || IsWifxClientInstalled())
		{
			sHTML += '&nbsp;&nbsp;<a href="samplepage.htm#WIFX">WebImageFX Information</a><br>';
		}
		sHTML += '&nbsp;&nbsp;<a href="samplepage.htm#Samples">Samples Information</a><br>';
		sHTML += '&nbsp;&nbsp;<a href="samplepage.htm#Ektron">Ektron\'s Other Products</a><br>';
		sHTML += '</td></tr></table></center>'
		write(sHTML);
	}
}

// Perform a quick check to get the availablity of the eWebEditPro object and display its configuration. 
function writeProductsInfo(sLocation)	
{
	with (document)
	{
		if (typeof eWebEditProPath == "undefined")
		{
			write("<span class=errmsg>");
			write("The path to the eWebEditPro directory is missing.<br>");
			write("Most likely, the ewebeditpro.js file could not be found.<br>");
			write("This file, ewebeditpro.js, and related files should all be in the same directory.<br>");
			write("</span>");
			writeln("<br>");
		}
		else
		{
			var sPath = GetHostName(sLocation);
			write("<h2><a name='EWEP'>" + g_sEWEPProduct + " Information</a></h2>");
			writeEWEPInfo(sPath);
			if (IsWifxSvrInstalled() || IsWifxClientInstalled())
			{
				write("<br><hr width='80%'>");
				write("<h2><a name='WIFX'>WebImageFX Information</a></h2>");
				writeWIFXInfo(sPath);
			}
			writeln("<br>");
		}
	}
}

// Display eWebEditPro Information
function writeEWEPInfo(sPath)
{
	with (document)
	{
		var sProductPage = GetAddress(g_sEWEPProduct);
		write('<a href="' + sProductPage + '" target="_blank"><img src="ewebeditpro.gif" border="1"></a>');
		
		if (typeof eWebEditPro != "object")
		{
			write("<br><span class=errmsg>");
			write("Failed to create the eWebEditPro JavaScript object.<br>");
			write("Most likely the path (eWebEditProPath) in file ewebeditpro.js is not correct.<br>");
			write("All the eWebEditPro JavaScript (*.js) files should be in the same directory.<br>");
			write("The directory must be specified in the eWebEditProPath variable defined in ewebeditpro.js.<br>");
			write("</span>");
			writeln("<br>");
		}
		else
		{
			write("<h3>Version of software installed in the server: " + eWebEditPro.version + "</h3>"); 
			writeln("<br>");
		
			write("<h3>License Keys</h3>");
			write("<span class=value>" + EncryptKey(LicenseKeys, "EWEP") + "</span><br>");
			write("The license keys can be updated by modifying the LicenseKeys variable in <i>ewebeditprolicensekey.txt</i> file that is installed to " + sPath + ".<br>");
			writeln("<br>");
			write("<h3>Installation Path</h3>");
			write("The eWebEditProPath variable: <span class=value>" + eWebEditProPath + "</span><br>");
			write("This is the path to the eWebEditPro files that is relative to the host name.<br>");
			write("The File Path: <span class=value>" + sPath + "</span><br>");
			write("<span class=important>NOTE: If the site is moved, the above path would change. In this case, you must modify the eWebEditProPath variable in the <i>ewebeditpro.js</i> file that is installed to " + sPath + ".</span><br>");
		}
		if (typeof eWebEditProPath != "string")
		{
			write("<span class=errmsg>");
			write("eWebEditProPath MUST be a string.<br>");
			write("</span>");
		}
		else if (eWebEditProPath.length > 0)
		{
			if (eWebEditProPath.substring(0, 1) != "/")
			{
				write("eWebEditProPath may need a leading '/', click on 'Open ewebeditpro.js' below to test the path.<br>");
			}
			var len = eWebEditProPath.length;
			if (eWebEditProPath.substring(len - 1, len) != "/")
			{
				write("eWebEditProPath MUST end with a '/'.<br>");
			}
		}
		writeln("<br>");
		if (typeof eWebEditPro != "object")
		{
			write("<span class=errmsg>");
			write("Failed to create the eWebEditPro JavaScript object.<br>");
			write("Most likely the path (eWebEditProPath) in file ewebeditpro.js is not correct.<br>");
			write("All the eWebEditPro JavaScript (*.js) files should be in the same directory.<br>");
			write("The directory must be specified in the eWebEditProPath variable defined in ewebeditpro.js.<br>");
			write("</span>");
			writeln("<br>");
		}
		else
		{
			var strVersion = "unknown";
			var sNextStep = 'A web content editor is shown at <a href="' + eWebEditProPath + 'test.htm">eWebEditPro Test</a> if it is installed.';
			var strEWEPVersion = "";
			if (this.isIE)
			{
				strEWEPVersion = ActiveXVersionInstalled(cPROGID) +""; //eWebEditProLibCtl4.eWebEditPro
				sNextStep = '<a href="' + eWebEditProDefaults.clientInstall + '">install eWebEditPro Client</a>.<br>';
				sNextStep = sNextStep + 'Or try the <a href="test.htm">automatically download and install</a> with the Editor.';
				if (strEWEPVersion != "")
				{
					strVersion = strEWEPVersion;
					if (strVersion < strEWEPVersion)
					{
						sNextStep = 'To upgrade the ewebeditpro4.ocx on the client, ' + sNextStep;
					}
					else
					{
						sNextStep = 'A web content editor is shown at <a href="' + eWebEditProPath + 'test.htm">eWebEditPro Test</a>';
					}
				}
				else if (!eWebEditPro.isInstalled)
				{
					strVersion = "not installed";
					sNextStep = 'To ' + sNextStep;
				}
			}
			write("Version of ewebeditpro4.ocx installed on the <b>client</b>: <span class=value>" + strVersion + "</span><br>");
			write(sNextStep + "<br>");
			writeln("<br>");
		}
		write("<h3>More Useful Links</h3>");
		write('<li><a href="' + g_sReleaseNote + '" target="_blank">' + g_sEWEPProduct + ' Release Notes</a>');
		write('<li><a href="' + g_sSupportPage + '" target="_blank">' + g_sEWEPProduct + ' Support Page</a>');
		write('<li><a href="developerguide.pdf" target="_blank">' + g_sEWEPProduct + ' Developer Guide</a>');
		write('<li><a href="userguide.pdf" target="_blank">' + g_sEWEPProduct + ' User Guide</a>');
		write('<li><span class=important>Windows XP SP2: </span><a href="http://www.ektron.com/developers/ewebeditprokb.cfm?id=1778" target="_blank">KB article on the effects of Windows XP SP2 on eWebEditPro</a>');
		writeln("<br>");
	}
}

function writeWIFXInfo(sPath)
{
	if (WebImageFXPath != eWebEditProPath)
	{
		sPath = sPath.replace(eWebEditProPath, WebImageFXPath);
	}
	with (document)
	{
		write('<a href="' + g_sWIFXPage +  '" target="_blank"><img src="webimagefx.gif" border="1"></a>');
		var strWIFXVersion = "Not Found";
		if (IsWifxSvrInstalled())
		{
			strWIFXVersion = cIMGEDVERSION;
			write("<h3>Version of software installed in the server: " + strWIFXVersion + "</h3>"); 
			write("<h3>License Keys</h3>");
			write("<span class=value>" + EncryptKey(LicenseKeys, "WIFX") + "</span><br>");
			write("The license keys can be updated by modifying the WifxLicenseKeys variable in <i>webimagefxlicensekey.txt</i> file that is installed to " + sPath + ".<br>");
			writeln("<br>");
			write("<h3>Installation Path</h3>");
			write("The WebImageFXPath variable: <span class=value>" + WebImageFXPath + "</span><br>");
			write("This is the path to the WebImageFX files that is relative to the host name.<br>");
			write("The File Path: <span class=value>" + sPath + "</span><br>");
			write("<span class=important>NOTE: If the site is moved, the above path would change. In this case, you must modify the eWebEditProPath variable in the <i>ewebeditpro.js</i> file that is installed to " + sPath + ".</span><br>");
		}
		writeln("<br>");
		var strVersion = "unknown";
		var strWifxTestPage = "test.htm";
		var sNextStep = "";
		if (IsWifxSvrInstalled())
		{
			strWifxTestPage = "webimagefx.htm";
			sNextStep += 'A web image editor is shown at <a href="' + eWebEditProPath + strWifxTestPage + '">WebImageFX Test</a> if it is installed.';
		}
		var strWIFXVersion = "";

		if (this.isIE)
		{
			strWIFXVersion = g_WifxClientVersion;//ActiveXVersionInstalled("WebImageFX.ImageEditor") +""; //cIMGEDPROGID
			sNextStep = '<a href="' + eWebEditProDefaults.clientInstall + '">install WebImageFX Client</a>.<br>';
			sNextStep += 'Or try the <a href="' + strWifxTestPage + '">automatically download and install</a> with the Image Editor.';
			if (strWIFXVersion != "")
			{
				strVersion = strWIFXVersion;
				if  (IsWifxSvrInstalled())
				{
					if (strVersion < cIMGEDVERSION)
					{
						sNextStep = 'To upgrade the webimagefx.ocx on the client, ' + sNextStep;
					}
					else if (IsWifxSvrInstalled())
					{
						sNextStep = 'A web image editor is shown at <a href="' + eWebEditProPath + strWifxTestPage + '">WebImageFX Test</a>';
					}
				}
				else if (IsWifxClientInstalled())
				{
					sNextStep = '';
				}
			}
			else 
			{
				strVersion = "not installed";
				sNextStep = 'To ' + sNextStep;
			}
			sNextStep += "<br>";
		}
		write("Version of webimagefx.ocx installed on the <b>client</b>: <span class=value>" + strVersion + "</span><br>");
		write(sNextStep);

		write("<h3>More Useful Links</h3>");
		write('<li><a href="' + g_sWIFXReleaseNote + '" target="_blank">WebImageFX Release Notes</a>');
		write('<li><a href="http://www.ektron.com/support/webimagefx_support.aspx" target="_blank">WebImageFX Support Page</a>');
		if (IsWifxSvrInstalled())
		{
			write('<li><a href="developerguide_webimagefx.pdf" target="_blank">WebImageFX Developer Guide</a>');
			write('<li><a href="userguide_webimagefx.pdf" target="_blank">WebImageFX User Guide</a>');
		}
		writeln("<br>");
	}
}

// Perform a quick check on the samples variables and display the sample groups installed in the server.
function writeSamplesInfo()
{
	if (typeof g_samplesInstalled != "undefined" && g_samplesInstalled == true)
	{
		with (document)
		{
			write("<br><hr width='80%'>");
			write("<h2><a name='Samples'>Samples installed</a></h2>");
			write("Some samples require the server platform to be installed in order to run the application in that server environment.");
			if (true == g_samplesASP)
				write("<li>ASP Samples");
			if (true == g_samplesASPNET)
				write("<li>ASP.NET Samples (Microsoft .NET server needs to be installed.)");
			if (true == g_samplesColdFusion)
				write("<li>ColdFusion Samples (ColdFusion server needs to be installed.)");
			if (true == g_samplesHTML)
				write("<li>HTML Samples");
			if (true == g_samplesJSP)
				write("<li>JSP Samples (JSP server needs to be installed.)");
			if (true == g_samplesPHP)
				write("<li>PHP Samples (PHP server needs to be installed.)");
			if (true == g_samplesLasso)
				write("<li>Lasso Samples (Lasso server needs to be installed.)");
			if (true == g_samplesXML)
				write("<li>XML Samples");
			if (true == g_samplesDataDesign)
				write("<li>Data Design Samples");
			if (true == g_samplesWebImageFX)
				write("<li>WebImageFX Samples");
			writeln("<br>");
		}
	}
}

// This function will block out the encryption part of the license keys for display.
function EncryptKey(keys, sProduct)
{
	var lStart, lEnd, lWIFX;
	var bNeedExt;
	var sEncrytedKeys = "";
	var sHolder = "";
	lStart = keys.indexOf("?");
	while (lStart > 0)
	{
		sHolder = keys.substr(0, lStart).toLowerCase();
		lWIFX = sHolder.indexOf("(wifx)");
		bNeedExt = false;
		if ("WIFX" == sProduct) // encrypt WIFX key
		{
			if (lWIFX > 0)
			{
				sEncrytedKeys = sEncrytedKeys + keys.substring(0, lStart + 1) + "**********";
				bNeedExt = true;
			}
		}
		else // encrypt EWEP key
		{
			if (-1 == lWIFX)
			{
				sEncrytedKeys = sEncrytedKeys + keys.substring(0, lStart + 1) + "**********";
				bNeedExt = true;
			}
		}
		keys = keys.substr(lStart + 1);
		lEnd = keys.indexOf(",");
		if (-1 == lEnd)
		{
			lEnd = keys.length;
		}
		if (true == bNeedExt)
		{
			bNeedExt = false;
			sEncrytedKeys = sEncrytedKeys +  + keys.substring(lEnd -2, lEnd);
		}
		keys = keys.substr(lEnd);
		if (keys != "")
			lStart = keys.indexOf("?");
		else
			lStart = -1;
	}

	if ("" == sEncrytedKeys)
		sEncrytedKeys = "<i>Not Found</i>";
	else 
	{
		sEncrytedKeys = sEncrytedKeys.replace("Enter license key here...", "");
		sEncrytedKeys = sEncrytedKeys.replace(",,", ",");
		if ("," == sEncrytedKeys.substr(0,1))
			sEncrytedKeys = sEncrytedKeys.substr(1,sEncrytedKeys.length);
		if ("," == sEncrytedKeys.substr(sEncrytedKeys.length - 1, sEncrytedKeys.length))
			sEncrytedKeys = sEncrytedKeys.substr(0,sEncrytedKeys.length -1);
	}
	
	return sEncrytedKeys;
}

// display Ektron Content Management Solution hyperlinks
function writeEktronCMSInfo()
{
	with (document)
	{
		write("<br><hr width='80%'>");
		write("<h2><a name='Ektron'>Ektron's Other Products</a></h2>");
		write('<a href="' + g_sCMS300Page + '" target="_blank"><img src="cms300.gif" border="1"></a>');
		write('<li><a href="' + g_sCMS300Page + '" target="_blank">Ektron CMS300 Home Page</a><br>');
		write('<li><a href="' + g_sCMS400Page + '" target="_blank">Ektron CMS400 Home Page</a><br>');
		write('<li><a href="' + g_sDMS400Page + '" target="_blank">Ektron DMS400 Home Page</a><br>');
		/*write('<li><a href="' + GetAddress(sProduct) + '" target="_blank">Web Content Editors Home Page</a><br>');
		write('<li><a href="http://www.ektron.com/webimagefx.aspx" target="_blank">Web Image Editor Home Page</a><br>');*/
		write('<li><a href="http://www.ektron.com/support/index.aspx" target="_blank">Ektron Support Site</a><br>');
		write('<li><a href="http://www.ektron.com/developers/index.cfm" target="_blank">Ektron Developers Site</a><br>');
		write('<li><a href="http://www.ektron.com" target="_blank">Ektron Site</a><br>');
		writeln("<br>");
	}
}

// return the host name of the current page.
function GetHostName(sLocation)
{
	var sHostName = sLocation.substr(0, sLocation.length - "samplepage.htm".length);
	return sHostName;
}

// return the web address of the product according to the product name.
function GetAddress(sProduct)
{
	var sAddress = "http://www.ektron.com/ewebeditpro.aspx";
	if ("eWebEditPro+XML" == sProduct)
	{
		sAddress = "http://www.ektron.com/ewebeditproxml.aspx";
	}
	return sAddress;
}