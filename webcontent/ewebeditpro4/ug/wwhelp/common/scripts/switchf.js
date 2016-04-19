// Copyright (c) 2000-2001 Quadralay Corporation.  All rights reserved.
// 

function  WWHelpSwitch_Object()
{
  var  BrowserDetect = new BrowserDetect_Object();


  this.mBrowser  = BrowserDetect.mBrowser;
  this.mPlatform = BrowserDetect.mPlatform;

  this.mbJavaEnabled = false;

  this.fForceJavaScript  = WWHelpSwitch_ForceJavaScript;
  this.fCheckJavaEnabled = WWHelpSwitch_CheckJavaEnabled;
  this.fGetFrameSet      = WWHelpSwitch_GetFrameSet;
  this.fSwitchFrameSet   = WWHelpSwitch_SwitchFrameSet;
}

function  WWHelpSwitch_CheckJavaEnabled()
{
  if (navigator != null)
  {
    this.mbJavaEnabled = navigator.javaEnabled();
  }
  else
  {
    this.mbJavaEnabled = false;
  }
}

function  WWHelpSwitch_GetFrameSet()
{
  var  WhichFrameSet = "wwhelp/js/html/frames.htm";


  // Detect current environment and use appropriate frameset
  // if JavaScript isn't forced on.
  //
  if (this.fForceJavaScript() == false)
  {
    // Check to see if Java is enabled
    //
    if (this.mbJavaEnabled)
    {
      // We'll try to run the Java version unless
      // a particular platform doesn't support it.
      //
      if (this.mBrowser == 1)  // Shorthand for Netscape
      {
        if (this.mPlatform == 1)  // Shorthand for Windows
        {
          WhichFrameSet = "wwhelp/java/html/wwhnsfs.htm";  // Java works on NS for Windows
        }
        else if (this.mPlatform == 2)  // Shorthand for Macintosh
        {
          WhichFrameSet = "wwhelp/js/html/frames.htm";  // Java doesn't work on NS for Macintosh
        }
        else
        {
          WhichFrameSet = "wwhelp/java/html/wwhnsfs.htm";  // Java works on NS for UNIX
        }
      }
      else if (this.mBrowser == 4)  // Shorthand for Netscape
      {
        // LiveConnect broken under Netscape 6.0 on Windows and Mac
        //
        WhichFrameSet = "wwhelp/js/html/frames.htm";  // Play it safe.
      }
      else  // Assume IE
      {
        if (this.mPlatform == 1)  // Shorthand for Windows
        {
          WhichFrameSet = "wwhelp/java/html/wwhiefs.htm";  // Java works on IE for Windows
        }
        else if (this.mPlatform == 2)  // Shorthand for Macintosh
        {
          WhichFrameSet = "wwhelp/java/html/wwhmacfs.htm";  // Java works on IE and iCab for Macintosh (no favorites)
        }
        else
        {
          WhichFrameSet = "wwhelp/js/html/frames.htm";  // Java doesn't work on IE for UNIX
        }
      }
    }
  }

  return WhichFrameSet;
}

function  WWHelpSwitch_SwitchFrameSet()
{
  var  NewLocation;


  NewLocation = this.fGetFrameSet();

  if (location.href.indexOf("?") != -1)
  {
    var  Parts;


    Parts = location.href.split("?");

    NewLocation += "?" + Parts[1];
  }

  location.href = NewLocation;
}
