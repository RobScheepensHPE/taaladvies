// Copyright (c) 2000-2001 Quadralay Corporation.  All rights reserved.
// 

function  OutlineEntry_Object(ParamOutline,
                              ParamHREF,
                              ParamText)
{
  if ((ParamHREF != null) &&
      (ParamHREF.length > 0))
  {
    this.mHREF = ParamHREF;
  }
  else
  {
    this.mHREF = "";
  }

  if ((ParamText != null) &&
      (ParamText.length > 0))
  {
    this.mText = ParamText;
  }
  else
  {
    this.mText = "";
  }

  this.mOutline = ParamOutline;
  this.mID = 0;
  this.mParent = null;
  this.mChildren = null;
  this.mbExpanded = false;
  this.mLevel = 0;

  this.fPopupActionString = OutlineEntry_PopupActionString;
  this.fNewChild          = OutlineEntry_NewChild;
  this.fLinkEntry         = OutlineEntry_LinkEntry;
}

function  OutlineEntry_PopupActionString()
{
  var  EventString;
  var  ActionString;


  if ((WWHelpTopFrame.WWHelp.mBrowser == 1) ||  // Shorthand for Netscape
      (WWHelpTopFrame.WWHelp.mBrowser == 2) ||  // Shorthand for IE
      (WWHelpTopFrame.WWHelp.mBrowser == 4))    // Shorthand for Netscape 6.0
  {
    EventString = "event";
  }
  else
  {
    EventString = "null";
  }

  ActionString = "onMouseOver=\"WWHelpTopFrame.WWHelp.mContents.mPopup.fShow(" + this.mID + ", " + EventString + ");\" onMouseOut=\"WWHelpTopFrame.WWHelp.mContents.mPopup.fHide();\"";

  return ActionString;
}

function  OutlineEntry_NewChild(ParamText,
                                ParamHREF)
{
  var  NewEntry;


  NewEntry = WWHelpTopFrame.WWHelp.mContents.fNewEntry(ParamText, ParamHREF);
  WWHelpTopFrame.WWHelp.mContents.fAddChild(this, NewEntry);

  return NewEntry;
}

function  OutlineEntry_LinkEntry(ParamPrefixText)
{
  var  Prefix;
  var  Link;


  if (ParamPrefixText != null)
  {
    Prefix = ParamPrefixText;
  }
  else
  {
    Prefix = "";
  }

  if (this.mHREF.length > 0)
  {
    Link = "<a href=\"javascript:WWHelpTopFrame.WWHelp.mContents.fDisplayDoc(" + this.mID + ");\" " + this.fPopupActionString() + ">" + Prefix + this.mText + "</a>";
  }
  else
  {
    Link = Prefix + this.mText;
  }

  return Link;
}

function  Outline_Object(ParamBrowser,
                         ParamPlatform,
                         ParamBaseURL)
{
  var  bBeFast = false;


  this.mEntries    = new Array();
  this.mTop        = null;
  this.mMaxLevel   = 0;
  this.mbStoreHTML = false;
  this.mStoredHTML = "";
  this.mImgSrcDir  = "../";
  this.mPopup      = new Popup_Object("WWHelpTopFrame.WWHelp.mContents.mPopup",
                                      "WWHelpTopFrame.WWHNavigation.WWHSync",
                                      Outline_PopupTranslate, Outline_PopupFormat,
                                      "WWHOutlinePopupDIV", "WWHOutlinePopupText", 1000, 12, 20, 150);
 
  this.fHead              = Outline_Head;
  this.fBody              = Outline_Body;
  this.fNewBook           = Outline_NewBook;
  this.fNewEntry          = Outline_NewEntry;
  this.fAddChild          = Outline_AddChild;
  this.fDisplayDoc        = Outline_DisplayDoc;
  this.fExpand            = Outline_Expand;
  this.fCollapse          = Outline_Collapse;
  this.fDumpHTML          = Outline_DumpHTML;
  this.fFindClosedParents = Outline_FindClosedParents;
  this.fSync              = Outline_Sync;
  this.fDisplayEntry      = Outline_DisplayEntrySafe;
  this.fDisplayOutline    = Outline_DisplayOutlineSafe;
  this.fGenerateStyles    = Outline_GenerateStylesSafe;
  this.fUpdateOutline     = Outline_UpdateOutlineSafe;
  this.fRevealEntry       = Outline_RevealEntrySafe;

  // Create top level entry to hold everything else
  //
  this.mTop = this.fNewEntry("Top Level", "");
  this.mTop.mbExpanded = true;

  // Determine display method based on browser type
  //
  if ((ParamBrowser == 2) ||  // Shorthand for IE
      (ParamBrowser == 3) ||  // Shorthand for iCab
      (ParamBrowser == 4))    // Shorthand for Netscape 6.0 (Mozilla)
  {
    bBeFast = true;
  }

  // Determine img src reference based on browser type and platform
  //
  if ((ParamBrowser == 2) &&  // Shorthand for IE
      (ParamPlatform == 2))   // Shorthand for Macintosh
  {
    this.mImgSrcDir = ParamBaseURL + "wwhelp/js/";
  }

  // Set display methods
  //
  if (bBeFast)
  {
    this.fDisplayEntry   = Outline_DisplayEntryFast;
    this.fDisplayOutline = Outline_DisplayOutlineFast;
    this.fGenerateStyles = Outline_GenerateStylesFast;
    this.fUpdateOutline  = Outline_UpdateOutlineFast;
    this.fRevealEntry    = Outline_RevealEntryFast;
  }
}

function  Outline_Head()
{
  // Write formatting styles
  //
  this.fGenerateStyles();
}

function  Outline_Body()
{
  // Write the outline data
  //
  this.fDisplayOutline();

  // Write div tag for popup
  //
  this.mPopup.fWriteDivTag();
}

function  Outline_PopupTranslate(ParamEntryID)
{
  var  Translation = "";


  if (ParamEntryID != null)
  {
    Translation = WWHelpTopFrame.WWHelp.mContents.mEntries[ParamEntryID].mText;
  }

  return Translation;
}

function  Outline_PopupFormat(ParamText)
{
  var  FormattedText = "";
  var  BorderColor   = "#999999";
  var  ImageDir      = WWHelpTopFrame.WWHelp.mBaseURL + "wwhelp/common/images";


  FormattedText += "<table width=\"" + this.mWidth + "\" border=0 cellspacing=0 cellpadding=0 width=\"100%\" bgcolor=\"#FFFFCC\">";
  FormattedText += " <tr>";
  FormattedText += "  <td colspan=5 bgcolor=\"" + BorderColor + "\"><img src=\"" + ImageDir + "/spc1w2h.gif\" width=1 height=2></td>";
  FormattedText += " </tr>";

  FormattedText += " <tr>";
  FormattedText += "  <td bgcolor=\"" + BorderColor + "\"><img src=\"" + ImageDir + "/spc2w1h.gif\" width=2 height=1></td>";
  FormattedText += "  <td colspan=3><img src=\"" + ImageDir + "/spc1w7h.gif\" width=1 height=7></td>";
  FormattedText += "  <td bgcolor=\"" + BorderColor + "\"><img src=\"" + ImageDir + "/spc2w1h.gif\" width=2 height=1></td>";
  FormattedText += " </tr>";

  FormattedText += " <tr>";
  FormattedText += "  <td bgcolor=\"" + BorderColor + "\"><img src=\"" + ImageDir + "/spc2w1h.gif\" width=2 height=1></td>";
  FormattedText += "  <td><img src=\"" + ImageDir + "/spc5w1h.gif\" width=5 height=1></td>";
  FormattedText += "  <td width=\"100%\" id=\"" + this.mTextID + "\">" + ParamText + "</td>";
  FormattedText += "  <td><img src=\"" + ImageDir + "/spc5w1h.gif\" width=5 height=1></td>";
  FormattedText += "  <td bgcolor=\"" + BorderColor + "\"><img src=\"" + ImageDir + "/spc2w1h.gif\" width=2 height=1></td>";
  FormattedText += " </tr>";

  FormattedText += " <tr>";
  FormattedText += "  <td bgcolor=\"" + BorderColor + "\"><img src=\"" + ImageDir + "/spc2w1h.gif\" width=2 height=1></td>";
  FormattedText += "  <td colspan=3><img src=\"" + ImageDir + "/spc1w7h.gif\" width=1 height=7></td>";
  FormattedText += "  <td bgcolor=\"" + BorderColor + "\"><img src=\"" + ImageDir + "/spc2w1h.gif\" width=2 height=1></td>";
  FormattedText += " </tr>";

  FormattedText += " <tr>";
  FormattedText += "  <td colspan=5 bgcolor=\"" + BorderColor + "\"><img src=\"" + ImageDir + "/spc1w2h.gif\" width=1 height=2></td>";
  FormattedText += " </tr>";
  FormattedText += "</table>";

  return FormattedText;
}

function  Outline_NewBook(ParamBookID,
                          ParamBookTitle)
{
  var  NewEntry;


  NewEntry = this.fNewEntry(ParamBookTitle, "");

  NewEntry.mBookID = ParamBookID;

  if (this.mMaxLevel < NewEntry.mLevel)
  {
    this.mMaxLevel = NewEntry.mLevel;
  }

  // Expand first book
  //
  if (this.mTop.mChildren == null)
  {
    NewEntry.mbExpanded = true;
  }

  // Add to tree at top level
  //
  this.fAddChild(this.mTop, NewEntry);

  return NewEntry;
}

function  Outline_NewEntry(ParamText,
                           ParamHREF)
{
  var  NewEntry;


  // Create new object
  //
  NewEntry = new OutlineEntry_Object(this, ParamHREF, ParamText);

  // Set unique ID
  //
  NewEntry.mID = this.mEntries.length;
  this.mEntries.length++;
  this.mEntries[NewEntry.mID] = NewEntry;

  return NewEntry;
}

function  Outline_AddChild(ParamParent,
                           ParamChild)
{
  if (ParamParent.mChildren == null)
  {
    ParamParent.mChildren = new Array(ParamChild);
  }
  else
  {
    ParamParent.mChildren.length++;
    ParamParent.mChildren[ParamParent.mChildren.length - 1] = ParamChild;
  }

  ParamChild.mParent = ParamParent;
  ParamChild.mLevel  = ParamParent.mLevel + 1;

  if (ParamChild.mLevel > this.mMaxLevel)
  {
    this.mMaxLevel = ParamChild.mLevel;
  }
}

function  Outline_DisplayDoc(ParamID)
{
  var  Entry;
  var  Parent;
  var  NewHREF;


  Entry = this.mEntries[ParamID];

  // Expand if folder
  //
  if (( ! Entry.mbExpanded) &&
      (Entry.mChildren != null))
  {
    this.fExpand(ParamID);
  }

  // Determine which book this document belongs to
  //
  Parent = Entry;
  while ((Parent.mParent != null) &&
         (Parent.mBookID == null))
  {
    Parent = Parent.mParent;
  }

  NewHREF = WWHelpTopFrame.WWHelp.mBaseURL + WWHelpTopFrame.WWHelp.mBookList[Parent.mBookID].mBookDir + this.mEntries[ParamID].mHREF;

  WWHelpTopFrame.WWHDocument.location.href = NewHREF;
}

function  Outline_Expand(ParamID)
{
  this.mEntries[ParamID].mbExpanded = true;

  this.fUpdateOutline(ParamID);
}

function  Outline_Collapse(ParamID)
{
  this.mEntries[ParamID].mbExpanded = false;

  this.fUpdateOutline(ParamID);
}

function  Outline_DumpHTML(ParamHTML)
{
  if (this.mbStoreHTML)
  {
    this.mStoredHTML += ParamHTML;
  }
  else
  {
    WWHelpTopFrame.WWHNavigation.WWHSync.document.write(ParamHTML);
  }
}

function  Outline_FindClosedParents(ParamEntryID)
{
  var  SomeEntry;
  var  ClosedParents = new Array();


  SomeEntry = this.mEntries[ParamEntryID];
  if (SomeEntry != null)
  {
    var  SomeParent;


    SomeParent = SomeEntry.mParent;
    while (SomeParent != null)
    {
      if ( ! SomeParent.mbExpanded)
      {
        ClosedParents.length += 1;
        ClosedParents[ClosedParents.length - 1] = SomeParent;
      }

      SomeParent = SomeParent.mParent;
    }
  }

  return ClosedParents;
}

function  Outline_Sync(ParamBookID,
                       ParamFileHREF,
                       bParamVisible)
{
  var  EntryID = -1;
  var  BookStartIndex;
  var  MaxIndex;
  var  Index;
  var  BookEndIndex;
  var  TrimmedEntryHREF;


  // Find start of book entries
  //
  BookStartIndex = -1;
  MaxIndex = this.mEntries.length;
  Index = 0;
  while ((BookStartIndex == -1) &&
         (Index < MaxIndex))
  {
    if ((this.mEntries[Index].mBookID != null) &&
        (this.mEntries[Index].mBookID == ParamBookID))
    {
      BookStartIndex = Index;
    }

    Index++;
  }

  // Search book entries for match
  //
  BookEndIndex = this.mEntries.length;
  Index = BookStartIndex;
  while ((EntryID == -1) &&
         (Index < BookEndIndex))
  {
    // Check for FileHREF match
    //
    if (this.mEntries[Index].mHREF == ParamFileHREF)
    {
      EntryID = this.mEntries[Index].mID;
    }

    // Check to see if we can improve our guess
    // for the BookEndIndex
    //
    if ((this.mEntries[Index].mBookID != null) &&
        (this.mEntries[Index].mBookID != ParamBookID))
    {
      BookEndIndex = Index;
    }

    Index++;
  }

  // If we haven't matched yet, try trimming off "#" entries
  // and check for a match again
  //
  TrimmedFileHREF = WWHelpTopFrame.WWHelp.fTrimHREF(ParamFileHREF)
  Index = BookStartIndex;
  while ((EntryID == -1) &&
         (Index < BookEndIndex))
  {
    // Trim the entry's HREF and check for match
    //
    TrimmedEntryHREF = WWHelpTopFrame.WWHelp.fTrimHREF(this.mEntries[Index].mHREF);
    if (TrimmedEntryHREF == TrimmedFileHREF)
    {
      EntryID = this.mEntries[Index].mID;
    }

    Index++;
  }

  // If valid, reveal the entry
  //
  if (EntryID != -1)
  {
    this.fRevealEntry(EntryID, bParamVisible);
  }
}

function  Outline_DisplayEntrySafe(ParamEntry)
{
  if (ParamEntry.mChildren == null)
  {
    WWHelpTopFrame.WWHNavigation.WWHSync.document.write("<p class=l" + ParamEntry.mLevel + ">");
    WWHelpTopFrame.WWHNavigation.WWHSync.document.write("<nobr>");
    WWHelpTopFrame.WWHNavigation.WWHSync.document.write(ParamEntry.fLinkEntry("<img width=17 height=17 border=0 src=\"" + this.mImgSrcDir + "images/doc.gif\"> "));
    WWHelpTopFrame.WWHNavigation.WWHSync.document.write("</nobr>");
    WWHelpTopFrame.WWHNavigation.WWHSync.document.writeln("</p>");
  }
  else
  {
    if (ParamEntry.mbExpanded)
    {
      var  MaxChildIndex;
      var  ChildIndex;


      WWHelpTopFrame.WWHNavigation.WWHSync.document.write("<p class=l" + ParamEntry.mLevel + ">");
      WWHelpTopFrame.WWHNavigation.WWHSync.document.write("<nobr>");
      WWHelpTopFrame.WWHNavigation.WWHSync.document.write("<a name=\"toc" + ParamEntry.mID + "\" href=\"javascript:WWHelpTopFrame.WWHelp.mContents.fCollapse(" + ParamEntry.mID + ");\" " + ParamEntry.fPopupActionString() + "><img width=17 height=17 border=0 src=\"" + this.mImgSrcDir + "images/fo.gif\"></a>");
      WWHelpTopFrame.WWHNavigation.WWHSync.document.write(" " + ParamEntry.fLinkEntry());
      WWHelpTopFrame.WWHNavigation.WWHSync.document.write("</nobr>");
      WWHelpTopFrame.WWHNavigation.WWHSync.document.writeln("</p>");

      MaxChildIndex = ParamEntry.mChildren.length;
      for (ChildIndex = 0 ; ChildIndex < MaxChildIndex ; ChildIndex++)
      {
        this.fDisplayEntry(ParamEntry.mChildren[ChildIndex]);
      }
    }
    else
    {
      WWHelpTopFrame.WWHNavigation.WWHSync.document.write("<p class=l" + ParamEntry.mLevel + ">");
      WWHelpTopFrame.WWHNavigation.WWHSync.document.write("<nobr>");
      WWHelpTopFrame.WWHNavigation.WWHSync.document.write("<a name=\"toc" + ParamEntry.mID + "\" href=\"javascript:WWHelpTopFrame.WWHelp.mContents.fExpand(" + ParamEntry.mID + ");\" " + ParamEntry.fPopupActionString() + "><img width=17 height=17 border=0 src=\"" + this.mImgSrcDir + "images/fc.gif\"></a>");
      WWHelpTopFrame.WWHNavigation.WWHSync.document.write(" " + ParamEntry.fLinkEntry());
      WWHelpTopFrame.WWHNavigation.WWHSync.document.write("</nobr>");
      WWHelpTopFrame.WWHNavigation.WWHSync.document.writeln("</p>");
    }
  }
}

function  Outline_DisplayOutlineSafe()
{
  var  MaxChildIndex;
  var  ChildIndex;


  MaxChildIndex = this.mTop.mChildren.length;
  for (ChildIndex = 0 ; ChildIndex < MaxChildIndex ; ChildIndex++)
  {
    this.fDisplayEntry(this.mTop.mChildren[ChildIndex]);
  }
}

function  Outline_GenerateStylesSafe()
{
  var  ChildLevel;


  WWHelpTopFrame.WWHNavigation.WWHSync.document.writeln("<style type=\"text/css\">");
  WWHelpTopFrame.WWHNavigation.WWHSync.document.writeln(" <!--");
  WWHelpTopFrame.WWHNavigation.WWHSync.document.writeln("  a { text-decoration: none ;");
  WWHelpTopFrame.WWHNavigation.WWHSync.document.writeln("      color: " + WWHelpTopFrame.WWHelp.mMessages["Anchor Color"] + " }");
  WWHelpTopFrame.WWHNavigation.WWHSync.document.writeln("  p { margin-top: 1pt ;");
  WWHelpTopFrame.WWHNavigation.WWHSync.document.writeln("      margin-bottom: 1pt ;");
  WWHelpTopFrame.WWHNavigation.WWHSync.document.writeln("      font-family: " + WWHelpTopFrame.WWHelp.mMessages["Font Family"] + " ;");
  WWHelpTopFrame.WWHNavigation.WWHSync.document.writeln("      font-size: " + WWHelpTopFrame.WWHelp.mMessages["Font Size"] + " }");
  for (ChildLevel = 0 ; ChildLevel < this.mMaxLevel ; ChildLevel++)
  {
    WWHelpTopFrame.WWHNavigation.WWHSync.document.writeln("  p.l" + (ChildLevel + 1) + " { margin-left: " + (WWHelpTopFrame.WWHelp.mMessages["TOC Indent"] * ChildLevel) + "pt }");
  }
  WWHelpTopFrame.WWHNavigation.WWHSync.document.writeln(" -->");
  WWHelpTopFrame.WWHNavigation.WWHSync.document.writeln("</style>");
}

function  Outline_UpdateOutlineSafe(ParamJumpToID)
{
  // Can't find the position of the target element in Netscape,
  // so we'll use a named anchor to jump there.
  //
// HACK BEN
//  WWHelpTopFrame.WWHNavigation.WWHSync.location.href = WWHelpTopFrame.WWHelp.mBaseURL + "wwhelp/js/html/sync.htm";
//  WWHelpTopFrame.WWHNavigation.WWHSync.location.href = "#toc" + ParamJumpToID;
  WWHelpTopFrame.WWHelp.mTabs.fUpdateXYPosition();

  // Close down any popups we had going to prevent JavaScript errors
  //
  this.mPopup.fHide();

  WWHelpTopFrame.WWHNavigation.WWHSync.location.href = WWHelpTopFrame.WWHelp.mBaseURL + "wwhelp/js/html/sync.htm";
}

function  Outline_RevealEntrySafe(ParamEntryID,
                                  bParamVisible)
{
  var  ExpandList;
  var  SomeParent;


  // Expand out enclosing entries
  //
  ExpandList = this.fFindClosedParents(ParamEntryID);
  while (ExpandList.length > 0)
  {
    SomeParent = ExpandList[ExpandList.length - 1];

    SomeParent.mbExpanded = true;

    ExpandList.length -= 1;
  }

  // Scroll to the entry not possible in Netscape, so just preserve current layout
  //
  if (bParamVisible)
  {
    this.fUpdateOutline(ParamEntryID);
  }
}

function  Outline_DisplayEntryFast(ParamEntry)
{
  if (ParamEntry.mChildren == null)
  {
    this.fDumpHTML("<nobr>");
    this.fDumpHTML(ParamEntry.fLinkEntry("<img width=17 height=17 border=0 src=\"" + this.mImgSrcDir + "images/doc.gif\"> ") + "\n");
    this.fDumpHTML("</nobr>");
  }
  else
  {
    if (ParamEntry.mbExpanded)
    {
      var  MaxChildIndex;
      var  ChildIndex;


      this.fDumpHTML("<nobr>");
      this.fDumpHTML("<a href=\"javascript:WWHelpTopFrame.WWHelp.mContents.fCollapse(" + ParamEntry.mID + ");\" " + ParamEntry.fPopupActionString() + "><img width=17 height=17 border=0 src=\"" + this.mImgSrcDir + "images/fo.gif\"></a>");
      this.fDumpHTML(" " + ParamEntry.fLinkEntry() + "\n");
      this.fDumpHTML("</nobr>");

      this.fDumpHTML("<ul>\n");
      MaxChildIndex = ParamEntry.mChildren.length;
      for (ChildIndex = 0 ; ChildIndex < MaxChildIndex ; ChildIndex++)
      {
        this.fDumpHTML("<li id=toc" + ParamEntry.mChildren[ChildIndex].mID + ">");
        this.fDisplayEntry(ParamEntry.mChildren[ChildIndex]);
        this.fDumpHTML("</li>\n");
      }
      this.fDumpHTML("</ul>\n");
    }
    else
    {
      this.fDumpHTML("<nobr>");
      this.fDumpHTML("<a href=\"javascript:WWHelpTopFrame.WWHelp.mContents.fExpand(" + ParamEntry.mID + ");\" " + ParamEntry.fPopupActionString() + "><img width=17 height=17 border=0 src=\"" + this.mImgSrcDir + "images/fc.gif\"></a>");
      this.fDumpHTML(" " + ParamEntry.fLinkEntry() + "\n");
      this.fDumpHTML("</nobr>");
    }
  }
}

function  Outline_DisplayOutlineFast()
{
  var  MaxChildIndex;
  var  ChildIndex;


  WWHelpTopFrame.WWHNavigation.WWHSync.document.writeln("<ul>");

  this.mbStoreHTML = false;

  MaxChildIndex = this.mTop.mChildren.length;
  for (ChildIndex = 0 ; ChildIndex < MaxChildIndex ; ChildIndex++)
  {
    this.fDumpHTML("<li class=book id=toc" + this.mTop.mChildren[ChildIndex].mID + ">");
    this.fDisplayEntry(this.mTop.mChildren[ChildIndex]);
    this.fDumpHTML("</li>\n");
  }

  WWHelpTopFrame.WWHNavigation.WWHSync.document.writeln("</ul>");
}

function  Outline_GenerateStylesFast()
{
  WWHelpTopFrame.WWHNavigation.WWHSync.document.writeln("<style type=\"text/css\">");
  WWHelpTopFrame.WWHNavigation.WWHSync.document.writeln(" <!--");
  WWHelpTopFrame.WWHNavigation.WWHSync.document.writeln("  a  { text-decoration: none ;");
  WWHelpTopFrame.WWHNavigation.WWHSync.document.writeln("       color: " + WWHelpTopFrame.WWHelp.mMessages["Anchor Color"] + " }");
  WWHelpTopFrame.WWHNavigation.WWHSync.document.writeln("  ul { list-style-type: none ;");
  WWHelpTopFrame.WWHNavigation.WWHSync.document.writeln("       padding-left: 0pt ;");
  WWHelpTopFrame.WWHNavigation.WWHSync.document.writeln("       margin-top: 1pt ;");
  WWHelpTopFrame.WWHNavigation.WWHSync.document.writeln("       margin-bottom: 1pt ;");
  WWHelpTopFrame.WWHNavigation.WWHSync.document.writeln("       margin-left: 0pt }");
  WWHelpTopFrame.WWHNavigation.WWHSync.document.writeln("  li { margin-top: 1pt ;");
  WWHelpTopFrame.WWHNavigation.WWHSync.document.writeln("       margin-bottom: 1pt ;");
  WWHelpTopFrame.WWHNavigation.WWHSync.document.writeln("       font-family: " + WWHelpTopFrame.WWHelp.mMessages["Font Family"] + " ;");
  WWHelpTopFrame.WWHNavigation.WWHSync.document.writeln("       font-size: " + WWHelpTopFrame.WWHelp.mMessages["Font Size"] + " ;");
  WWHelpTopFrame.WWHNavigation.WWHSync.document.writeln("       margin-left: " + WWHelpTopFrame.WWHelp.mMessages["TOC Indent"] + "pt }");
  WWHelpTopFrame.WWHNavigation.WWHSync.document.writeln("  li.book { margin-left: 0pt }");
  WWHelpTopFrame.WWHNavigation.WWHSync.document.writeln(" -->");
  WWHelpTopFrame.WWHNavigation.WWHSync.document.writeln("</style>");
}

function  Outline_UpdateOutlineFast(ParamJumpToID)
{
  var  ElementID;


  this.mStoredHTML = "";
  this.mbStoreHTML = true;

  this.fDisplayEntry(this.mEntries[ParamJumpToID]);
  ElementID = "toc" + ParamJumpToID;
  if ((WWHelpTopFrame.WWHelp.mBrowser == 1) ||  // Shorthand for Netscape
      (WWHelpTopFrame.WWHelp.mBrowser == 2))    // Shorthand for IE
  {
    WWHelpTopFrame.WWHNavigation.WWHSync.document.all[ElementID].innerHTML = this.mStoredHTML;
  }
  else if (WWHelpTopFrame.WWHelp.mBrowser == 4)  // Shorthand for Netscape 6.0
  {
    WWHelpTopFrame.WWHNavigation.WWHSync.document.getElementById(ElementID).innerHTML = this.mStoredHTML;
  }

  this.mStoredHTML = "";
  this.mbStoreHTML = false;
}

function  Outline_RevealEntryFast(ParamEntryID,
                                  bParamVisible)
{
  var  ExpandList;
  var  SomeParent;


  // Expand out enclosing entries
  //
  ExpandList = this.fFindClosedParents(ParamEntryID);
  while (ExpandList.length > 0)
  {
    SomeParent = ExpandList[ExpandList.length - 1];

    SomeParent.mbExpanded = true;
    if (bParamVisible)
    {
      this.fUpdateOutline(SomeParent.mID);
    }

    ExpandList.length -= 1;
  }

  // Scroll to the entry
  //
  if (bParamVisible)
  {
    if (WWHelpTopFrame.WWHelp.mBrowser == 2)  // Shorthand for IE
    {
      WWHelpTopFrame.WWHelp.mTabs.mXYPosition[0][1] = WWHelpTopFrame.WWHNavigation.WWHSync.document.all["toc" + ParamEntryID].offsetTop;

      setTimeout("WWHelpTopFrame.WWHNavigation.WWHSync.window.scroll(" + WWHelpTopFrame.WWHelp.mTabs.mXYPosition[0][0]
                                                                       + ", "
                                                                       + WWHelpTopFrame.WWHelp.mTabs.mXYPosition[0][1]
                                                                       + ");", 100);
    }
    else if (WWHelpTopFrame.WWHelp.mBrowser == 4)  // Shorthand for Netscape 6.0
    {
      WWHelpTopFrame.WWHelp.mTabs.mXYPosition[0][1] = WWHelpTopFrame.WWHNavigation.WWHSync.document.getElementById("toc" + ParamEntryID).offsetTop;

      setTimeout("WWHelpTopFrame.WWHNavigation.WWHSync.window.scroll(" + WWHelpTopFrame.WWHelp.mTabs.mXYPosition[0][0]
                                                                       + ", "
                                                                       + WWHelpTopFrame.WWHelp.mTabs.mXYPosition[0][1]
                                                                       + ");", 100);
    }
  }
}
