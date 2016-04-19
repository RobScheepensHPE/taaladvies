// Copyright (c) 2000-2001 Quadralay Corporation.  All rights reserved.
// 

function  WWHelp_Object()
{
  var  BrowserDetect = new BrowserDetect_Object();


  this.mBrowser          = BrowserDetect.mBrowser;
  this.mPlatform         = BrowserDetect.mPlatform;
  this.mbLoadData        = false;
  this.mbInitialized     = false;
  this.mMessages         = new Array();
  this.mBookDirList      = new Array();
  this.mBookList         = new Array();
  this.mBaseURL          = location.href.substring(0, location.href.lastIndexOf("/wwhelp/js/html/frames.htm")) + "/";
  this.mCurrentBookIndex = -1;
  this.mTabs             = new Tabs_Object();
  this.mSync             = new Sync_Object();
  this.mContents         = new Outline_Object(this.mBrowser, this.mPlatform, this.mBaseURL);
  this.mIndex            = new Index_Object();
  this.mSearch           = new Search_Object();

  this.fAddBookDir            = WWHelp_AddBookDir;
  this.fAddBook               = WWHelp_AddBook;
  this.fIncrementCurBookIndex = WWHelp_IncrementCurBookIndex;
  this.fTrimHREF              = WWHelp_TrimHREF;
  this.fHREFToTitle           = WWHelp_HREFToTitle;
  this.fParseHREF             = WWHelp_ParseHREF;
  this.fSyncTOC               = WWHelp_SyncTOC;
  this.fDataHTML              = WWHelp_DataHTML;
  this.fDataHead              = WWHelp_DataHead;
  this.fDataBody              = WWHelp_DataBody;

  // Load up messages
  //
  WWHelp_Messages(this.mMessages);
}

function  WWHelp_AddBookDir(ParamBookDir)
{
  if ( ! this.mbInitialized)
  {
    if ((ParamBookDir != null) &&
        (ParamBookDir.length > 0))
    {
      this.mBookDirList.length++;

      if (ParamBookDir == ".")
      {
        this.mBookDirList[this.mBookDirList.length - 1] = "";
      }
      else
      {
        this.mBookDirList[this.mBookDirList.length - 1] = escape(ParamBookDir) + "/";
      }
    }
  }
}

function  WWHelp_AddBook(ParamNewBook)
{
  var  NewBookTOCEntry;
  var  NewBookIndexEntry;


  this.mBookList.length++;
  this.mBookList[this.mBookList.length - 1] = ParamNewBook;

  // Create TOC
  //
  if (this.mMessages["Tabs TOC"].length > 0)
  {
    NewBookTOCEntry = this.mContents.fNewBook(this.mBookList.length - 1, ParamNewBook.mBookTitle);
    ParamNewBook.fAddTOCEntries(NewBookTOCEntry);
  }

  // Add Index
  //
  if (this.mMessages["Tabs Index"].length > 0)
  {
    this.mIndex.fNewBook(ParamNewBook);
  }
}

function  WWHelp_IncrementCurBookIndex()
{
  // Increment CurrentBookIndex
  //
  if (this.mCurrentBookIndex != -1)
  {
    if (this.mCurrentBookIndex < this.mBookDirList.length)
    {
      this.mCurrentBookIndex++;
    }
    else  // We're done!
    {
      this.mCurrentBookIndex = -1;
    }
  }
}

function  WWHelp_TrimHREF(ParamHREF)
{
  var  PoundIndex;
  var  TrimmedHREF;


  // Remove named anchor entries from link
  //
  PoundIndex = ParamHREF.lastIndexOf("#");
  if (PoundIndex > 0)
  {
    TrimmedHREF = ParamHREF.substring(0, PoundIndex);
  }
  else
  {
    TrimmedHREF = ParamHREF;
  }

  return TrimmedHREF;
}

function  WWHelp_HREFToTitle(ParamBookIndex,
                             ParamHREF)
{
  var  Title;
  var  MaxIndex;
  var  Index;


  Title = "";
  MaxIndex = this.mBookList.length;
  Index = 0;
  while ((Title.length == 0) &&
         (Index < MaxIndex))
  {
    Title = this.mBookList[Index].fHREFToTitle(ParamHREF);

    Index++;
  }

  return Title;
}

function  WWHelp_ParseHREF(ParamHREF)
{
  var  ResultArray = new Array("", "");


  if ((this.mBaseURL.length > 0) &&
      (ParamHREF.length > this.mBaseURL.length))  // Make certain this can match
  {
    var  Prefix;
    var  Suffix;


    Prefix = ParamHREF.substring(0, this.mBaseURL.length);
    Suffix = ParamHREF.substring(this.mBaseURL.length, ParamHREF.length);

    // Confirm we're under the same hierarchy
    //
    if (Prefix == this.mBaseURL)
    {
      var  LongestMatchIndex;
      var  MaxIndex;
      var  Index;


      // Fix for differences between URL escapes and JavaScript
      //
      Suffix = unescape(Suffix);
      Suffix = escape(Suffix);

      // Find the book directory
      //
      LongestMatchIndex = -1;
      for (MaxIndex = this.mBookDirList.length, Index = 0 ; Index < MaxIndex ; Index++)
      {
        if (Suffix.indexOf(this.mBookDirList[Index]) == 0)
        {
          if (LongestMatchIndex == -1)
          {
            LongestMatchIndex = Index;
          }
          else if (this.mBookDirList[Index].length > this.mBookDirList[LongestMatchIndex].length)
          {
            LongestMatchIndex = Index;
          }
        }
      }

      // If LongestMatchIndex is valid, we found our book directory
      //
      if (LongestMatchIndex != -1)
      {
        var  Parts;


        // Reset suffix to be just the file portion
        //
        if (this.mBookDirList[LongestMatchIndex].length > 0)
        {
          Suffix = Suffix.substring(this.mBookDirList[LongestMatchIndex].length, Suffix.length);
        }

        // Fixup any escaped "#"s
        //
        Suffix = unescape(Suffix);
        Parts = Suffix.split("#");
        for (MaxIndex = Parts.length, Index = 0 ; Index < MaxIndex ; Index++)
        {
          Parts[Index] = escape(Parts[Index]);
        }
        Suffix = Parts.join("#");

        ResultArray[0] = this.mBookDirList[LongestMatchIndex];
        ResultArray[1] = Suffix;
      }
    }
  }

  return ResultArray;
}

function  WWHelp_SyncTOC(ParamHREF)
{
  // Only sync if TOC is available
  // and selected
  //
  if ((ParamHREF != null) &&
      (this.mMessages["Tabs TOC"].length > 0))
  {
    var  ParsedHREF;
    var  BookDir;
    var  FileHREF;
    var  MaxIndex;
    var  Index;
    var  BookID;


    // Get BookDir and FileHREF
    //
    ParsedHREF = this.fParseHREF(ParamHREF);
    BookDir  = ParsedHREF[0];
    FileHREF = ParsedHREF[1];

    // Determine BookID
    //
    BookID = -1;
    MaxIndex = this.mBookList.length;
    Index = 0;
    while ((BookID == -1) &&
           (Index < MaxIndex))
    {
      if (this.mBookList[Index].mBookDir == BookDir)
      {
        BookID = Index;
      }

      Index++;
    }

    // Display the selection
    //
    if (BookID != -1)
    {
      var  bVisible = false;


      if (this.mTabs.mCurrentTab == 0)  // Outline view
      {
        bVisible = true;
      }

      this.mContents.fSync(BookID, FileHREF, bVisible);
    }
  }
}

function  WWHelp_DataHTML()
{
  if ( ! this.mbInitialized)
  {
    WWHelpTopFrame.WWHNavigation.WWHData.document.open("text/html", "replace");
    WWHelpTopFrame.WWHNavigation.WWHData.document.writeln("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0//EN\"");
    WWHelpTopFrame.WWHNavigation.WWHData.document.writeln("			\"http://www.w3.org/TR/REC-html40/strict.dtd\">");
    WWHelpTopFrame.WWHNavigation.WWHData.document.writeln("<html>");
    WWHelpTopFrame.WWHNavigation.WWHData.document.writeln("<head>");
    WWHelpTopFrame.WWHNavigation.WWHData.document.writeln("<title>Data</title>");

    this.fDataHead();

    WWHelpTopFrame.WWHNavigation.WWHData.document.writeln("</head>");

    WWHelpTopFrame.WWHNavigation.WWHData.document.writeln("<body>");

    this.fDataBody();

    WWHelpTopFrame.WWHNavigation.WWHData.document.writeln("</body>");
    WWHelpTopFrame.WWHNavigation.WWHData.document.writeln("</html>");
    WWHelpTopFrame.WWHNavigation.WWHData.document.close();
  }
}

function  WWHelp_DataHead()
{
  if ( ! this.mbInitialized)
  {
    WWHelpTopFrame.WWHNavigation.WWHData.document.writeln("<script language=\"JavaScript\" src=\"" + this.mBaseURL + "wwhelp/js/scripts/bookf.js\"></script>");
  }
}

function  WWHelp_DataBody()
{
  if ( ! this.mbInitialized)
  {
    var  MaxIndex;
    var  Index;


    this.mCurrentBookIndex = 0;
    for (MaxIndex = this.mBookDirList.length, Index = 0 ; Index < MaxIndex ; Index++)
    {
      // Add entries for Book info
      //
      WWHelpTopFrame.WWHNavigation.WWHData.document.writeln("<script language=\"JavaScript\" src=\"" + this.mBaseURL + this.mBookDirList[Index] + "wwhdata/js/book.js\"></script>");
      WWHelpTopFrame.WWHNavigation.WWHData.document.writeln("<script language=\"JavaScript\" src=\"" + this.mBaseURL + this.mBookDirList[Index] + "wwhdata/js/search.js\"></script>");
      WWHelpTopFrame.WWHNavigation.WWHData.document.writeln("<script language=\"JavaScript\" src=\"" + this.mBaseURL + this.mBookDirList[Index] + "wwhdata/js/files.js\"></script>");
      WWHelpTopFrame.WWHNavigation.WWHData.document.writeln("<script language=\"JavaScript\" src=\"" + this.mBaseURL + this.mBookDirList[Index] + "wwhdata/js/toc.js\"></script>");
      WWHelpTopFrame.WWHNavigation.WWHData.document.writeln("<script language=\"JavaScript\" src=\"" + this.mBaseURL + this.mBookDirList[Index] + "wwhdata/js/index.js\"></script>");

      // Create new book
      //
      WWHelpTopFrame.WWHNavigation.WWHData.document.writeln("<script language=\"JavaScript\" src=\"" + this.mBaseURL + "wwhelp/js/scripts/data1s.js\"></script>");

      // Increment this.mCurrentBookIndex
      //
      WWHelpTopFrame.WWHNavigation.WWHData.document.writeln("<script language=\"JavaScript\" src=\"" + this.mBaseURL + "wwhelp/js/scripts/data2s.js\"></script>");
    }

    // Set initialized flag
    //
    this.mbInitialized = true;

    // Update Tabs and Sync frames
    //
    WWHelpTopFrame.WWHNavigation.WWHData.document.writeln("<script language=\"JavaScript\" src=\"" + this.mBaseURL + "wwhelp/js/scripts/data3s.js\"></script>");
  }
}
