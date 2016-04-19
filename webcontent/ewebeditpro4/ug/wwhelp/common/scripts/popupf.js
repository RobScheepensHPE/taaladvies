// Copyright (c) 2000-2001 Quadralay Corporation.  All rights reserved.
// 

function  Popup_Object(ParamThisAccessor,
                       ParamWindowReferenceAsString,
                       ParamPopupTranslateFunc,
                       ParamPopupFormatFunc,
                       ParamDivID,
                       ParamTextID,
                       ParamTimeout,
                       ParamOffsetX,
                       ParamOffsetY,
                       ParamWidth)
{
  var  BrowserDetect = new BrowserDetect_Object();


  this.mThisAccessor    = ParamThisAccessor;
  this.mWindowRefString = ParamWindowReferenceAsString;
  this.mWindowReference = null;
  this.mDocReference    = null;
  this.mDivID           = ParamDivID;
  this.mTextID          = ParamTextID;
  this.mTimeout         = (ParamTimeout > 0) ? ParamTimeout : 0;
  this.mOffsetX         = ParamOffsetX;
  this.mOffsetY         = ParamOffsetY;
  this.mWidth           = ParamWidth;
  this.mBrowser         = BrowserDetect.mBrowser;
  this.mPlatform        = BrowserDetect.mPlatform;
  this.mbWindowsIE40    = BrowserDetect.mbWindowsIE40;


  // Updated when popup triggered
  //
  this.mbVisible  = false;
  this.mPositionX = 0;
  this.mPositionY = 0;
  this.mText      = "";

  this.fTranslate     = ParamPopupTranslateFunc;
  this.fFormat        = ParamPopupFormatFunc;
  this.fWriteDivTag   = Popup_WriteDivTag;
  this.fShow          = Popup_Show;
  this.fPositionPopup = Popup_PositionPopup;
  this.fPopup         = Popup_Popup;
  this.fHide          = Popup_Hide;
}

function  Popup_WriteDivTag()
{
  var  VisibleAttribute = "visibility: hidden";


  // Need to update our reference everytime
  // in case document gets rewritten via JavaScript
  //
  this.mDocReference = eval(this.mWindowRefString + ".document");

  // Update VisibleAttribute based on browser
  //
  if ((this.mBrowser == 3) ||  // Shorthand for iCab
      (this.mBrowser == 4))    // Shorthand for Netscape 6.0 (Mozilla)
  {
    VisibleAttribute = "display: none";
  }

  // Open DIV tag
  //
  this.mDocReference.writeln("<div id=\"" + this.mDivID + "\" style=\"position: absolute ; z-index: 1 ; " + VisibleAttribute + " ; top: 0px ; left: 0px\">");

  // Expand out popup in browsers that support innerHTML accessor
  //
  if ((this.mBrowser == 2) ||  // Shortcut for IE
      (this.mBrowser == 3) ||  // Shortcut for iCab
      (this.mBrowser == 4))    // Shortcut for Netscape 6 (Mozilla)
  {
    this.mDocReference.writeln(this.fFormat("Popup"));
  }

  // Close out DIV tag
  //
  this.mDocReference.writeln("</div>");    
}

function  Popup_Show(ParamText,
                     ParamEvent)
{
  // Reset the timeout operation to display the popup
  //
  if (this.mSetTimeoutID != null)
  {
    clearTimeout(this.mSetTimeoutID);

    this.mSetTimeoutID = null;
  }

  // Check to see if there is anything to display
  //
  if ((ParamText != null) &&
      (ParamEvent != null))
  {
    var  bStartTimer = false;


    // Need to update our reference everytime
    // in case document gets rewritten via JavaScript
    //
    this.mWindowReference = eval(this.mWindowRefString);
    this.mDocReference    = eval(this.mWindowRefString + ".document");

    if (this.mBrowser == 1)  // Shorthand for Netscape 4.x
    {
      this.mPositionX = ParamEvent.layerX;
      this.mPositionY = ParamEvent.layerY;

      this.mText = ParamText;

      bStartTimer = true;
    }
    else if (this.mBrowser == 2)  // Shorthand for IE
    {
      var  TranslatedText;


      this.mPositionX = this.mDocReference.body.scrollLeft + ParamEvent.x;
      this.mPositionY = this.mDocReference.body.scrollTop  + ParamEvent.y;

      // Workaround for IE 4.0 on Windows
      //
      if (this.mbWindowsIE40)
      {
        this.mPositionX = ParamEvent.x;
        this.mPositionY = ParamEvent.y;
      }

      this.mText = ParamText;

      if (this.mPlatform == 2)  // Shorthand for Macintosh
      {
        // Setting the position here before it is displayed
        // corrects a bug under IE 5 on the Macintosh
        //
        this.mDocReference.all[this.mDivID].style.pixelLeft = 0;
        this.mDocReference.all[this.mDivID].style.pixelTop  = 0;
        TranslatedText = this.fTranslate(this.mText);
        this.mDocReference.all[this.mTextID].innerHTML = TranslatedText;
        this.fPositionPopup();
      }

      bStartTimer = true;
    }
    else if (this.mBrowser == 4)  // Shorthand for Netscape 6.x (Mozilla)
    {
      this.mPositionX = ParamEvent.layerX;
      this.mPositionY = ParamEvent.layerY;

      this.mText = ParamText;

      bStartTimer = true;
    }

    if (bStartTimer == true)
    {
      this.mSetTimeoutID = setTimeout(this.mThisAccessor + ".fPopup()", this.mTimeout);
    }
  }
}

function  Popup_PositionPopup()
{
  var  NewPositionX;
  var  NewPositionY;
  var  VisibleOffsetX;
  var  VisibleOffsetY;


  // Calculate new position for popup
  //
  NewPositionX = this.mPositionX + this.mOffsetX;
  NewPositionY = this.mPositionY + this.mOffsetY;

  if (this.mBrowser == 1)  // Shorthand for Netscape 4.x
  {
    // Calculate maximum values for X and Y such that the
    // popup will remain visible
    //
    VisibleOffsetX = this.mWindowReference.innerWidth  - this.mOffsetX - this.mDocReference.layers[this.mDivID].clip.width;
    if (VisibleOffsetX < 0)
    {
      VisibleOffsetX = 0;
    }
    VisibleOffsetY = this.mWindowReference.innerHeight - this.mOffsetY - this.mDocReference.layers[this.mDivID].clip.height;
    if (VisibleOffsetY < 0)
    {
      VisibleOffsetY = 0;
    }

    // Confirm popup will be visible and adjust if necessary
    //
    if (NewPositionX > (this.mWindowReference.pageXOffset + VisibleOffsetX))
    {
      NewPositionX = this.mWindowReference.pageXOffset + VisibleOffsetX;
    }
    if (NewPositionY > (this.mWindowReference.pageYOffset + VisibleOffsetY))
    {
      NewPositionY = this.mWindowReference.pageYOffset + VisibleOffsetY;
    }

    // Set popup position
    //
    this.mDocReference.layers[this.mDivID].left = NewPositionX;
    this.mDocReference.layers[this.mDivID].top  = NewPositionY;
  }
  else if (this.mBrowser == 2)  // Shorthand for IE
  {
    // Calculate maximum values for X and Y such that the
    // popup will remain visible
    //
    VisibleOffsetX = this.mDocReference.body.clientWidth  - this.mOffsetX - this.mDocReference.all[this.mDivID].offsetWidth;
    if (VisibleOffsetX < 0)
    {
      VisibleOffsetX = 0;
    }
    VisibleOffsetY = this.mDocReference.body.clientHeight - this.mOffsetY - this.mDocReference.all[this.mDivID].offsetHeight;
    if (VisibleOffsetY < 0)
    {
      VisibleOffsetY = 0;
    }

    // Confirm popup will be visible and adjust if necessary
    //
    if (NewPositionX > (this.mDocReference.body.scrollLeft + VisibleOffsetX))
    {
      NewPositionX = this.mDocReference.body.scrollLeft + VisibleOffsetX;
    }
    if (NewPositionY > (this.mDocReference.body.scrollTop + VisibleOffsetY))
    {
      NewPositionY = this.mDocReference.body.scrollTop + VisibleOffsetY;
    }

    // Set popup position
    //
    this.mDocReference.all[this.mDivID].style.pixelLeft = NewPositionX;
    this.mDocReference.all[this.mDivID].style.pixelTop  = NewPositionY;
  }
  else if (this.mBrowser == 4)  // Shorthand for Netscape 6.x (Mozilla)
  {
    var  PopupWidth;
    var  PopupHeight;


    // Attempt to determine DIV tag dimensions
    //
    PopupWidth = this.mWidth;
    if (parseInt(this.mDocReference.getElementById(this.mDivID).style.width) > PopupWidth)
    {
      PopupWidth = this.mDocReference.getElementById(this.mDivID).style.width;
    }
    PopupHeight = 24;  // Guess a value
    if (parseInt(this.mDocReference.getElementById(this.mDivID).style.height) > PopupHeight)
    {
      PopupHeight = this.mDocReference.getElementById(this.mDivID).height;
    }

    // Calculate maximum values for X and Y such that the
    // popup will remain visible
    //
    VisibleOffsetX = this.mWindowReference.innerWidth  - this.mOffsetX - PopupWidth;
    if (VisibleOffsetX < 0)
    {
      VisibleOffsetX = 0;
    }
    VisibleOffsetY = this.mWindowReference.innerHeight - this.mOffsetY - PopupHeight;
    if (VisibleOffsetY < 0)
    {
      VisibleOffsetY = 0;
    }

    // Confirm popup will be visible and adjust if necessary
    //
    if (NewPositionX > (this.mWindowReference.scrollX + VisibleOffsetX))
    {
      NewPositionX = this.mWindowReference.scrollX + VisibleOffsetX;
    }
    if (NewPositionY > (this.mWindowReference.scrollY + VisibleOffsetY))
    {
      NewPositionY = this.mWindowReference.scrollY + VisibleOffsetY;
    }

    // Set popup position
    //
    this.mDocReference.getElementById(this.mDivID).style.left = NewPositionX + "px";
    this.mDocReference.getElementById(this.mDivID).style.top  = NewPositionY + "px";
  }
}

function  Popup_Popup()
{
  if (this.mSetTimeoutID != null)
  {
    if (this.mBrowser == 1)  // Shorthand for Netscape 4.x
    {
      var  FormattedText;


      // Format popup contents for browser
      //
      FormattedText = this.fFormat(this.fTranslate(this.mText));

      // Set popup contents
      //
      this.mDocReference.layers[this.mDivID].document.open();
      this.mDocReference.layers[this.mDivID].document.write(FormattedText);
      this.mDocReference.layers[this.mDivID].document.close();

      // Position the popup
      //
      this.fPositionPopup();

      // Show the popup
      //
      this.mDocReference.layers[this.mDivID].visibility = "visible";
      this.mbVisible = true;
    }
    else if (this.mBrowser == 2)  // Shorthand for IE
    {
      var  TranslatedText;


      // Format popup contents for browser
      // Set popup contents
      //
      TranslatedText = this.fTranslate(this.mText);
      this.mDocReference.all[this.mTextID].innerHTML = TranslatedText;

      // Position the popup
      //
      this.fPositionPopup();

      // Show the popup
      //
      this.mDocReference.all[this.mDivID].style.visibility = "visible";
      this.mbVisible = true;
    }
    else if (this.mBrowser == 4)  // Shorthand for Netscape 6.x (Mozilla)
    {
      var  TranslatedText;


      // Format popup contents for browser
      // Set popup contents
      //
      TranslatedText = this.fTranslate(this.mText);
      this.mDocReference.getElementById(this.mTextID).innerHTML = TranslatedText;

      // Position the popup
      //
      this.fPositionPopup();

      // Show the popup
      //
      this.mDocReference.getElementById(this.mDivID).style.display = "block";
      this.mbVisible = true;
    }
  }

  // Clear the setTimeout ID tracking field
  // to indicate that we're done.
  //
  this.mSetTimeoutID = null;
}

function  Popup_Hide()
{
  // Cancel the setTimeout value that would have
  // displayed the popup
  //
  if (this.mSetTimeoutID != null)
  {
    clearTimeout(this.mSetTimeoutID);

    this.mSetTimeoutID = null;
  }

  // Shutdown the popup
  //
  if (this.mbVisible == true)
  {
    if (this.mBrowser == 1)  // Shorthand for Netscape 4.x
    {
      this.mDocReference.layers[this.mDivID].visibility = "hidden";
    }
    else if (this.mBrowser == 2)  // Shorthand for IE
    {
      this.mDocReference.all[this.mDivID].style.visibility = "hidden";
    }
    else if (this.mBrowser == 4)  // Shorthand for Netscape 6.x (Mozilla)
    {
      this.mDocReference.getElementById(this.mDivID).style.display = "none";
    }
  }

  this.mbVisible = false;
}
