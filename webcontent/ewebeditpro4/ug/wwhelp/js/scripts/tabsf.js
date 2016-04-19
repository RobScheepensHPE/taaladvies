// Copyright (c) 2000-2001 Quadralay Corporation.  All rights reserved.
// 

function  Tabs_Object()
{
  this.mMessageKeys = new Array("Tabs TOC", "Tabs Index", "Tabs Search", "Tabs Favorites");
  this.mXYPosition  = new Array(new Array(0,0), new Array(0,0), new Array(0,0), new Array(0,0));
  this.mCurrentTab  = -1;

  this.fChangeTab          = Tabs_ChangeTab;
  this.fChangeTabWithDelay = Tabs_ChangeTabWithDelay;
  this.fUpdateXYPosition   = Tabs_UpdateXYPosition;
  this.fGenerateStyles     = Tabs_GenerateStyles;
  this.fDisplayTabs        = Tabs_DisplayTabs;
}

function  Tabs_ChangeTab(ParamTabIndex)
{
  if ((this.mCurrentTab == -1) ||
      (this.mCurrentTab != ParamTabIndex))
  {
    this.fUpdateXYPosition();

    this.mCurrentTab = ParamTabIndex;

    WWHelpTopFrame.WWHNavigation.WWHTabs.location.href = WWHelpTopFrame.WWHNavigation.WWHTabs.location.href;
    WWHelpTopFrame.WWHNavigation.WWHSync.location.href = WWHelpTopFrame.WWHNavigation.WWHSync.location.href;
  }
}

function  Tabs_ChangeTabWithDelay(ParamTabIndex)
{
  setTimeout("WWHelpTopFrame.WWHelp.mTabs.fChangeTab(" + ParamTabIndex + ");", 100);
}

function  Tabs_UpdateXYPosition()
{
  if (this.mCurrentTab != -1)
  {
    if ((WWHelpTopFrame.WWHelp.mBrowser == 1) ||  // Shorthand for Netscape
        (WWHelpTopFrame.WWHelp.mBrowser == 4))  // Shorthand for Netscape
    {
      this.mXYPosition[this.mCurrentTab][0] = WWHelpTopFrame.WWHNavigation.WWHSync.window.pageXOffset;
      this.mXYPosition[this.mCurrentTab][1] = WWHelpTopFrame.WWHNavigation.WWHSync.window.pageYOffset;
    }
    else if (WWHelpTopFrame.WWHelp.mBrowser == 2)  // Shorthand for IE
    {
      // Test required to avoid JavaScript error under IE5.5 on Windows
      //
      if (WWHelpTopFrame.WWHNavigation.WWHSync.document.body == null)
      {
        this.mXYPosition[this.mCurrentTab][0] = 0;
        this.mXYPosition[this.mCurrentTab][1] = 0;
      }
      else
      {
        this.mXYPosition[this.mCurrentTab][0] = WWHelpTopFrame.WWHNavigation.WWHSync.document.body.scrollLeft;
        this.mXYPosition[this.mCurrentTab][1] = WWHelpTopFrame.WWHNavigation.WWHSync.document.body.scrollTop;
      }
    }
  }
}

function  Tabs_GenerateStyles()
{
  // Generate style section
  //
  WWHelpTopFrame.WWHNavigation.WWHTabs.document.writeln("<style type=\"text/css\">");
  WWHelpTopFrame.WWHNavigation.WWHTabs.document.writeln(" <!--");
  WWHelpTopFrame.WWHNavigation.WWHTabs.document.writeln("  a  { text-decoration: none ;");
  WWHelpTopFrame.WWHNavigation.WWHTabs.document.writeln("       color: " + WWHelpTopFrame.WWHelp.mMessages["Tabs Default Text Color"] + " }");
  WWHelpTopFrame.WWHNavigation.WWHTabs.document.writeln("  th { color: " + WWHelpTopFrame.WWHelp.mMessages["Tabs Selected Text Color"] + " ;");
  WWHelpTopFrame.WWHNavigation.WWHTabs.document.writeln("       font-family: " + WWHelpTopFrame.WWHelp.mMessages["Font Family"] + " ;");
  WWHelpTopFrame.WWHNavigation.WWHTabs.document.writeln("       font-size: " + WWHelpTopFrame.WWHelp.mMessages["Font Size"] + " }");
  WWHelpTopFrame.WWHNavigation.WWHTabs.document.writeln("  td { color: " + WWHelpTopFrame.WWHelp.mMessages["Tabs Default Text Color"] + " ;");
  WWHelpTopFrame.WWHNavigation.WWHTabs.document.writeln("       font-family: " + WWHelpTopFrame.WWHelp.mMessages["Font Family"] + " ;");
  WWHelpTopFrame.WWHNavigation.WWHTabs.document.writeln("       font-size: " + WWHelpTopFrame.WWHelp.mMessages["Font Size"] + " }");
  WWHelpTopFrame.WWHNavigation.WWHTabs.document.writeln(" -->");
  WWHelpTopFrame.WWHNavigation.WWHTabs.document.writeln("</style>");
}

function  Tabs_DisplayTabs()
{
  var  NumDisplayedTabs;
  var  MaxIndex;
  var  Index;
  var  Height = 24;
  var  Width  = 80;
  var  CellType;
  var  BackgoundColor;
  var  WrapPrefix;
  var  WrapSuffix;
  var  OnClick;
  var  InitialTab = -1


  // Calculate width based on number of displayed tabs
  //
  NumDisplayedTabs = 0;
  for (MaxIndex = this.mMessageKeys.length, Index = 0 ; Index < MaxIndex ; Index++)
  {
    if (WWHelpTopFrame.WWHelp.mMessages[this.mMessageKeys[Index]].length > 0)
    {
      NumDisplayedTabs++;
    }
  }

  if (NumDisplayedTabs < 0)
  {
    NumDisplayedTabs = 1;
  }

  Width = "\"" + 100/NumDisplayedTabs + "%\"";

  // Setup table for tab display
  //
  WWHelpTopFrame.WWHNavigation.WWHTabs.document.writeln("<table border=0 cellspacing=2 cellpadding=0 width=\"100%\">");
  WWHelpTopFrame.WWHNavigation.WWHTabs.document.writeln("<tr>");

  for (MaxIndex = this.mMessageKeys.length, Index = 0 ; Index < MaxIndex ; Index++)
  {
    // Only display tabs with display names
    //
    if (WWHelpTopFrame.WWHelp.mMessages[this.mMessageKeys[Index]])
    {
      // Initialize mCurrentTab (only occurs on initial load)
      //
      if ((this.mCurrentTab == -1) &&
          (InitialTab == -1))
      {
        // Update tab area and sync area as well
        //
        InitialTab = Index;
      }

      // Display anchor only if not selected
      //
      if (Index == this.mCurrentTab)
      {
        CellType = "th";
        BackgroundColor = WWHelpTopFrame.WWHelp.mMessages["Tabs Selected Tab Color"];
        WrapPrefix = "<b>";
        WrapSuffix = "</b>";
        OnClick = "";
      }
      else
      {
        CellType = "td";
        BackgroundColor = WWHelpTopFrame.WWHelp.mMessages["Tabs Default Tab Color"];
        WrapPrefix = "<b><a href=\"javascript:WWHelpTopFrame.WWHelp.mTabs.fChangeTab(" + Index + ");\">";
        WrapSuffix = "</a></b>";
        OnClick = " onClick=\"WWHelpTopFrame.WWHelp.mTabs.fChangeTabWithDelay(" + Index + ");\"";
      }

      WWHelpTopFrame.WWHNavigation.WWHTabs.document.write("<" + CellType + " nowrap align=center height=" + Height + " width=" + Width + " bgcolor=\"" + BackgroundColor + "\"" + OnClick + ">");
      WWHelpTopFrame.WWHNavigation.WWHTabs.document.write(WrapPrefix);
      WWHelpTopFrame.WWHNavigation.WWHTabs.document.write(WWHelpTopFrame.WWHelp.mMessages[this.mMessageKeys[Index]]);
      WWHelpTopFrame.WWHNavigation.WWHTabs.document.write(WrapSuffix);
      WWHelpTopFrame.WWHNavigation.WWHTabs.document.writeln("</" + CellType + ">");
    }
  }

  WWHelpTopFrame.WWHNavigation.WWHTabs.document.writeln("</tr>");
  WWHelpTopFrame.WWHNavigation.WWHTabs.document.writeln("</table>");

  // Initialize display by calling change tab (only occurs on initial load)
  //
  if (InitialTab != -1)
  {
    setTimeout("WWHelpTopFrame.WWHelp.mTabs.fChangeTab(" + InitialTab + ");", 100);
  }
}
