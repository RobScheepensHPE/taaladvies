// Copyright (c) 2000-2001 Quadralay Corporation.  All rights reserved.
// 

function  BrowserDetect_Object()
{
  var  Agent = "";
  var  MajorVersion = 0;


  // Determine browser
  //
  this.mBrowser = 0;      // Shorthand for Unknown
  this.mbWindowIE40 = 0;  // Needed for special case handling

  Agent = navigator.userAgent.toLowerCase();
  if ((Agent.indexOf("mozilla") != -1) &&
      (Agent.indexOf("spoofer") == -1) &&
      (Agent.indexOf("compatible") == -1))
  {
    MajorVersion = parseInt(navigator.appVersion)

    if (MajorVersion >= 5)
    {
      this.mBrowser = 4;  // Shorthand for Netscape 6.0
    }
    else if (MajorVersion >= 4)
    {
      this.mBrowser = 1;  // Shorthand for Netscape
    }
  }
  else if (Agent.indexOf("msie") != -1)
  {
    MajorVersion = parseInt(navigator.appVersion)
    if (MajorVersion >= 4)
    {
      var  VersionString;


      this.mBrowser = 2;  // Shorthand for IE

      // Additional info needed for popups
      //
      VersionString = navigator.appVersion.toLowerCase();
      MSIEVersionString = VersionString.substring(VersionString.indexOf("msie") + 4);
      Version = parseFloat(MSIEVersionString);
      if ((Version >= 4.0) &&
          (Version < 4.1))
      {
        this.mbWindowsIE40 = true;
      }
    }
  }
  else if (Agent.indexOf("icab") != -1)
  {
    this.mBrowser = 3;  // Shorthand for iCab
  }

  // Determine platform
  //
  this.mPlatform = 0;  // Shorthand for Unknown

  if ((Agent.indexOf("win") != -1) ||
      (Agent.indexOf("16bit") != -1))
  {
    this.mPlatform = 1;  // Shorthand for Windows
  }
  else if (Agent.indexOf("mac") != -1)
  {
    this.mPlatform = 2;  // Shorthand for Macintosh
  }
}
