// Copyright (c) 2000-2001 Quadralay Corporation.  All rights reserved.
// 

function  Sync_Object()
{
  this.fSyncHTML          = Sync_SyncHTML;
  this.fSyncHead          = Sync_SyncHead;
  this.fSyncBody          = Sync_SyncBody;
  this.fSyncTOCHead       = Sync_SyncTOCHead;
  this.fSyncTOCBody       = Sync_SyncTOCBody;
  this.fSyncIndexHead     = Sync_SyncIndexHead;
  this.fSyncIndexBody     = Sync_SyncIndexBody;
  this.fSyncSearchHead    = Sync_SyncSearchHead;
  this.fSyncSearchBody    = Sync_SyncSearchBody;
  this.fSyncFavoritesHead = Sync_SyncFavoritesHead;
  this.fSyncFavoritesBody = Sync_SyncFavoritesBody;
}

function  Sync_SyncHTML()
{
  WWHelpTopFrame.WWHNavigation.WWHSync.document.open("text/html", "replace");
  WWHelpTopFrame.WWHNavigation.WWHSync.document.writeln("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0//EN\"");
  WWHelpTopFrame.WWHNavigation.WWHSync.document.writeln("			\"http://www.w3.org/TR/REC-html40/strict.dtd\">");
  WWHelpTopFrame.WWHNavigation.WWHSync.document.writeln("<html>");
  WWHelpTopFrame.WWHNavigation.WWHSync.document.writeln("<head>");
  WWHelpTopFrame.WWHNavigation.WWHSync.document.writeln("<title>Sync</title>");

  this.fSyncHead();

  WWHelpTopFrame.WWHNavigation.WWHSync.document.writeln("</head>");

  WWHelpTopFrame.WWHNavigation.WWHSync.document.writeln("<body>");

  this.fSyncBody();

  WWHelpTopFrame.WWHNavigation.WWHSync.document.writeln("</body>");
  WWHelpTopFrame.WWHNavigation.WWHSync.document.writeln("</html>");
  WWHelpTopFrame.WWHNavigation.WWHSync.document.close();
}

function  Sync_SyncHead()
{
  if (WWHelpTopFrame.WWHelp.mTabs.mCurrentTab == 0)
  {
    this.fSyncTOCHead();
  }
  else if (WWHelpTopFrame.WWHelp.mTabs.mCurrentTab == 1)
  {
    this.fSyncIndexHead();
  }
  else if (WWHelpTopFrame.WWHelp.mTabs.mCurrentTab == 2)
  {
    this.fSyncSearchHead();
  }
  else if (WWHelpTopFrame.WWHelp.mTabs.mCurrentTab == 3)
  {
    this.fSyncFavoritesHead();
  }
}

function  Sync_SyncBody()
{
  if (WWHelpTopFrame.WWHelp.mTabs.mCurrentTab == 0)
  {
    this.fSyncTOCBody();
  }
  else if (WWHelpTopFrame.WWHelp.mTabs.mCurrentTab == 1)
  {
    this.fSyncIndexBody();
  }
  else if (WWHelpTopFrame.WWHelp.mTabs.mCurrentTab == 2)
  {
    this.fSyncSearchBody();
  }
  else if (WWHelpTopFrame.WWHelp.mTabs.mCurrentTab == 3)
  {
    this.fSyncFavoritesBody();
  }
}

function  Sync_SyncTOCHead()
{
  WWHelpTopFrame.WWHelp.mContents.fHead();
}

function  Sync_SyncTOCBody()
{
  WWHelpTopFrame.WWHelp.mContents.fBody();

  // Restore previous view
  //
  setTimeout("WWHelpTopFrame.WWHNavigation.WWHSync.window.scroll(" + WWHelpTopFrame.WWHelp.mTabs.mXYPosition[0][0]
                                                                   + ", "
                                                                   + WWHelpTopFrame.WWHelp.mTabs.mXYPosition[0][1]
                                                                   + ");", 100);
}

function  Sync_SyncIndexHead()
{
  WWHelpTopFrame.WWHelp.mIndex.fGenerateStyles();
}

function  Sync_SyncIndexBody()
{
  WWHelpTopFrame.WWHelp.mIndex.fDisplayIndex();

  // Restore previous view
  //
  setTimeout("WWHelpTopFrame.WWHNavigation.WWHSync.window.scroll(" + WWHelpTopFrame.WWHelp.mTabs.mXYPosition[1][0]
                                                                   + ", "
                                                                   + WWHelpTopFrame.WWHelp.mTabs.mXYPosition[1][1]
                                                                   + ");", 100);
}

function  Sync_SyncSearchHead()
{
  WWHelpTopFrame.WWHelp.mSearch.fGenerateStyles();
}

function  Sync_SyncSearchBody()
{
  WWHelpTopFrame.WWHelp.mSearch.fSearchBody();

  // Restore previous view
  //
  setTimeout("WWHelpTopFrame.WWHNavigation.WWHSync.window.scroll(" + WWHelpTopFrame.WWHelp.mTabs.mXYPosition[2][0]
                                                                   + ", "
                                                                   + WWHelpTopFrame.WWHelp.mTabs.mXYPosition[2][1]
                                                                   + ");", 100);
}

function  Sync_SyncFavoritesHead()
{
}

function  Sync_SyncFavoritesBody()
{
  WWHelpTopFrame.WWHNavigation.WWHSync.document.writeln("<h2>Favorites are here.</h2>");

  // Restore previous view
  //
  setTimeout("WWHelpTopFrame.WWHNavigation.WWHSync.window.scroll(" + WWHelpTopFrame.WWHelp.mTabs.mXYPosition[3][0]
                                                                   + ", "
                                                                   + WWHelpTopFrame.WWHelp.mTabs.mXYPosition[3][1]
                                                                   + ");", 100);
}
