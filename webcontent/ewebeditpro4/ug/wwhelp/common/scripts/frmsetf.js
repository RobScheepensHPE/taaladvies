// Copyright (c) 2000-2001 Quadralay Corporation.  All rights reserved.
// 

function  WWHelpFrameSet_WWHelp_Object()
{
  this.mBookDirList = new Array();

  this.fAddBookDir = WWHelpFrameSet_WWHelp_AddBookDir;
}

function  WWHelpFrameSet_WWHelp_AddBookDir(ParamBookDir)
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

function  WWHelpFrameSet_Popup_Object()
{
}

function  WWHelpFrameSet_PopupBook_Object(ParamBookDir)
{
  this.mBookDir = ParamBookDir;
  this.mPopups  = new WWHelpFrameSet_Popup_Object();
}

function  WWHelpFrameSet_Object(ParamDocumentFrame)
{
  var  BrowserDetect = new BrowserDetect_Object();
  var  Parts;


  this.mBrowser  = BrowserDetect.mBrowser;
  this.mPlatform = BrowserDetect.mPlatform;

  this.mBaseURL = location.href.substring(0, location.href.lastIndexOf("/"));
  Parts = this.mBaseURL.split("/wwhelp/java/html");
  if (Parts[0] == this.mBaseURL)
  {
    Parts = this.mBaseURL.split("/wwhelp/js/html");
  }
  this.mBaseURL = Parts[0] + "/";

  this.mBookDirList      = null;
  this.mCurrentBookIndex = -1;
  this.mPopups           = new WWHelpFrameSet_Popup_Object();
  this.mBookTag          = null;
  this.mTopicTag         = null;
  this.mContextHREF      = null;
  this.mLinkHREF         = null;
  this.mPopup            = new Popup_Object("WWHelpTopFrame.WWHelpFrameSet.mPopup", ParamDocumentFrame,
                                            WWHelpPopup_PopupTranslate, WWHelpPopup_PopupFormat,
                                            "WWHelpPopupDIV", "WWHelpPopupText", 500, 12, 20, 200);

  // Included from "handlers.js"
  //
  this.fUpdateFavorite = WWHelpFrameSet_UpdateFavorite;
  this.fSyncTOC        = WWHelpFrameSet_SyncTOC;

  // Functions defined in this file
  //
  this.fExecute               = WWHelpFrameSet_Execute;
  this.fParseHREF             = WWHelpFrameSet_ParseHREF;
  this.fLoadData              = WWHelpFrameSet_LoadData;
  this.fProcessBook           = WWHelpFrameSet_ProcessBook;
  this.fLoadPopups            = WWHelpFrameSet_LoadPopups;
  this.fCheckContext          = WWHelpFrameSet_CheckContext;
  this.fIncrementCurBookIndex = WWHelpFrameSet_IncrementCurBookIndex;
  this.fGetPopup              = WWHelpFrameSet_GetPopup;
  this.fShowPopup             = WWHelpFrameSet_ShowPopup;
  this.fHidePopup             = WWHelpFrameSet_HidePopup;
  this.fClickedPopup          = WWHelpFrameSet_ClickedPopup;
}

function  WWHelpFrameSet_Execute(ParamHREF,
                                 ParamBookDirList)
{
  this.mBookDirList = ParamBookDirList;

  this.fParseHREF(ParamHREF);
  this.fLoadData();
}

function  WWHelpFrameSet_ParseHREF(ParamHREF)
{
  // Reset context tags
  //
  this.mBookTag  = null;
  this.mTopicTag = null;

  // Check for possible context specification
  //
  if (ParamHREF.indexOf("?") != -1)
  {
    var  Parts;
    var  ContextMarker = "context=";
    var  TopicMarker   = "topic=";


    Parts = ParamHREF.split("?");

    // Check for our context tags
    //
    if ((Parts[1].indexOf("&") != -1) &&
        (Parts[1].indexOf(ContextMarker) != -1) &&
        (Parts[1].indexOf(TopicMarker) != -1))
    {
      var  MaxIndex;
      var  Index;


      Parts = Parts[1].split("&");

      // Set context and topic, if specified
      //
      for (MaxIndex = Parts.length, Index = 0 ; Index < MaxIndex ; Index++)
      {
        if (Parts[Index].indexOf(ContextMarker) == 0)
        {
          this.mBookTag  = Parts[Index].substring(ContextMarker.length, Parts[Index].length);
        }
        if (Parts[Index].indexOf(TopicMarker) == 0)
        {
          this.mTopicTag = Parts[Index].substring(TopicMarker.length, Parts[Index].length);
        }
      }

      // Make certain we have both a BookTag and TopicTag
      // Otherwise, reset them
      //
      if ((this.mBookTag  == null) ||
          (this.mTopicTag == null))
      {
        this.mBookTag  = null;
        this.mTopicTag = null;
      }
    }
  }
}

function  WWHelpFrameSet_LoadData()
{
  var  MaxIndex;
  var  Index;


  this.mContextHREF = null;

  this.mCurrentBookIndex = 0;
  for (MaxIndex = this.mBookDirList.length, Index = 0 ; Index < MaxIndex ; Index++)
  {
    document.writeln("<script type=\"text/javascript\" language=\"JavaScript\" src=\"../../../" + this.mBookDirList[Index] + "wwhdata/common/context.js\"></script>");
    document.writeln("<script type=\"text/javascript\" language=\"JavaScript\" src=\"../../../" + this.mBookDirList[Index] + "wwhdata/common/popups.js\"></script>");

    if ((this.mBookTag  != null) &&
        (this.mTopicTag != null))
    {
      document.writeln("<script type=\"text/javascript\" language=\"JavaScript\" src=\"../../../" + this.mBookDirList[Index] + "wwhdata/common/topics.js\"></script>");
    }

    document.writeln("<script type=\"text/javascript\" language=\"JavaScript\" src=\"../../common/scripts/frmset1s.js\"></script>");
    document.writeln("<script type=\"text/javascript\" language=\"JavaScript\" src=\"../../common/scripts/frmset2s.js\"></script>");
  }
}

function  WWHelpFrameSet_ProcessBook(ParamBookContextFunc,
                                     ParamPopupsFunc,
                                     ParamMatchTopicFunc)
{
  var  Context;


  // Get current book context
  //
  Context = ParamBookContextFunc();

  // Load up popups for this book
  //
  this.fLoadPopups(Context, ParamPopupsFunc);

  // Check for context match
  //
  this.fCheckContext(Context, ParamMatchTopicFunc);
}

function  WWHelpFrameSet_LoadPopups(ParamContext,
                                    ParamPopupsFunc)
{
  var  ContextPopups = new WWHelpFrameSet_PopupBook_Object(this.mBookDirList[this.mCurrentBookIndex]);


  // Add entry for the current book dir and context
  //
  this.mPopups[ParamContext] = ContextPopups;

  // Now load the popups for the book
  //
  ParamPopupsFunc(ContextPopups.mPopups);
}

function  WWHelpFrameSet_CheckContext(ParamContext,
                                      ParamMatchTopicFunc)
{
  if (ParamMatchTopicFunc != null)
  {
    if ((this.mBookTag  != null) &&
        (this.mTopicTag != null))
    {
      if (ParamContext == this.mBookTag)
      {
        if (this.mContextHREF == null)
        {
          this.mContextHREF = ParamMatchTopicFunc(this.mTopicTag);

          if (this.mContextHREF != null)
          {
            this.mContextHREF = "../../../" + this.mBookDirList[this.mCurrentBookIndex] + this.mContextHREF;
          }
        }
      }
    }
  }
}

function  WWHelpFrameSet_IncrementCurBookIndex()
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

function  WWHelpFrameSet_GetPopup(ParamContext,
                                  ParamLink)
{
  var  ContextPopups;
  var  BookDir = "";
  var  PopupText = "";


  // Match the context
  //
  ContextPopups = this.mPopups[ParamContext];
  if (ContextPopups != null)
  {
    // Match the link
    //
    BookDir   = ContextPopups.mBookDir;
    PopupText = ContextPopups.mPopups[ParamLink];
  }

  return new Array(BookDir, PopupText);
}

function  WWHelpFrameSet_ShowPopup(ParamContext,
                                   ParamLink,
                                   ParamEvent)
{
  var  PopupData;
  var  PopupText;


  // Get the popup text
  //
  PopupData = this.fGetPopup(ParamContext, ParamLink);
  PopupText = PopupData[1];

  if ((PopupData != null) &&
      (PopupText != null))
  {
    if (PopupText.length > 0)
    {
      this.mPopup.fShow(PopupText, ParamEvent);
    }
  }
}

function  WWHelpFrameSet_HidePopup()
{
  this.mPopup.fHide();
}

function  WWHelpFrameSet_ClickedPopup(ParamContext,
                                      ParamLink)
{
  var  LinkHREF = "#";
  var  PopupData;


  // IE unescapes stuff for us, so we'll escape it right back
  //
  if (this.mBrowser == 2)  // Shorthand for IE
  {
    var  Parts;


    Parts = ParamLink.split("#");
    Parts[0] = escape(Parts[0]);
    ParamLink = Parts.join("#");
  }

  // Get the popup text
  //
  PopupData = this.fGetPopup(ParamContext, ParamLink);

  if (PopupData[0] != null)  // BookDir is valid
  {
    // Trim off the base filename and then up two levels
    //
    LinkHREF = this.mBaseURL + PopupData[0] + ParamLink;
  }

  // Hide the popup if it is visible
  //
  this.mPopup.fHide();

  return LinkHREF;
}
