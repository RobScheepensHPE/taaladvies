// Copyright (c) 2000-2001 Quadralay Corporation.  All rights reserved.
// 

function  SearchBlank_Object()
{
}

function  Search_Object()
{
  this.mSearchWords      = new SearchBlank_Object();
  this.mNewSearchWords   = new Array();
  this.mCurrentBookIndex = -1;
  this.mResults          = new Array();

  this.fSearchBook            = Search_SearchBook;
  this.fIncrementCurBookIndex = Search_IncrementCurBookIndex;
  this.fAddResults            = Search_AddResults;
  this.fSearchBody            = Search_SearchBody;
  this.fSearchSubmitted       = Search_SearchSubmitted;
  this.fPerformSearch         = Search_PerformSearch;
  this.fGenerateStyles        = Search_GenerateStyles;
  this.fDisplayResults        = Search_DisplayResults;
  this.fDisplaySearchForm     = Search_DisplaySearchForm;
  this.fJumpTo                = Search_JumpTo;
}

function  Search_SearchBook(ParamSearchFunc)
{
  var  SearchWord;
  var  Results = null;
  var  MatchedWord = null;


  for (SearchWord in this.mSearchWords)
  {
    Results = ParamSearchFunc(SearchWord);

    if (Results != null)
    {
      this.fAddResults(SearchWord, Results);

      if (this.mSearchWords[SearchWord] != null)
      {
        this.mSearchWords[SearchWord] = true;
      }
    }
  }
}

function  Search_IncrementCurBookIndex()
{
  // Increment CurrentBookIndex
  //
  if (this.mCurrentBookIndex != -1)
  {
    if (this.mCurrentBookIndex < WWHelpTopFrame.WWHelp.mBookList.length)
    {
      this.mCurrentBookIndex++;
    }
    else  // We're done!
    {
      this.mCurrentBookIndex = -1;
    }
  }
}

function  Search_AddResults(ParamWord,
                            ParamHREFs)
{
  // Make certain we've got a book selected
  //
  if ((this.mCurrentBookIndex >= 0) &&
      (ParamHREFs != null) &&
      (ParamHREFs.length > 0))
  {
    var  BookResults;
    var  MaxIndex;
    var  Index;


    // Get books containing this word
    //
    BookResults = this.mResults[this.mCurrentBookIndex];
    if (BookResults == null)
    {
      BookResults = new Array();
      this.mResults[this.mCurrentBookIndex] = BookResults;

      // Add all entries from ParamHREFs
      //
      for (MaxIndex = ParamHREFs.length, Index = 0 ; Index < MaxIndex ; Index++)
      {
        BookResults.length++;
        BookResults[BookResults.length - 1] = ParamHREFs[Index];
      }
    }
    else  // Remove entries which don't appear in ParamHREFs
    {
      var  TestHREFs      = new Array();
      var  NewBookResults = new Array();


      // Put ParamHREFs into an associative array for easy checking
      //
      for (MaxIndex = ParamHREFs.length, Index = 0 ; Index < MaxIndex ; Index++)
      {
        TestHREFs.length++;
        TestHREFs[ParamHREFs[Index]] = true;
      }

      // Remove entries from BookResults that aren't in TestHREFs
      //
      for (MaxIndex = BookResults.length, Index = 0 ; Index < MaxIndex ; Index++)
      {
        if (TestHREFs[BookResults[Index]])
        {
          NewBookResults.length++;
          NewBookResults[NewBookResults.length - 1] = BookResults[Index];
        }
      }

      // Update results entry
      //
      this.mResults[this.mCurrentBookIndex] = NewBookResults;
    }
  }
}

function  Search_SearchBody()
{
  this.fPerformSearch();

  WWHelpTopFrame.WWHNavigation.WWHSync.document.writeln("<script language=\"JavaScript\" src=\"" + WWHelpTopFrame.WWHelp.mBaseURL + "wwhelp/js/scripts/search3s.js\"></script>");
}

function  Search_SearchSubmitted()
{
  // Check for new search words
  //
  if (WWHelpTopFrame.WWHNavigation.WWHSync.document.forms[0] != null)
  {
    var  SearchString;


    // Stuff new search words into mNewSearchWords array
    //
    SearchString = WWHelpTopFrame.WWHNavigation.WWHSync.document.forms[0].SearchWords.value;
    if ((SearchString != null) &&
        (SearchString.length > 0))
    {
      var  SearchStringArray;
      var  MaxIndex;
      var  Index;


      // Copy over search words
      //
      SearchStringArray = SearchString.split(" ");
      this.mNewSearchWords.length = 0;
      for (MaxIndex = SearchStringArray.length, Index = 0 ; Index < MaxIndex ; Index++)
      {
        // Need to eliminate zero length words
        //
        if (SearchStringArray[Index].length > 0)
        {
          // Convert them to lowercase before adding them
          //
          this.mNewSearchWords.length++;
          this.mNewSearchWords[this.mNewSearchWords.length - 1] = SearchStringArray[Index].toLowerCase();
        }
      }
    }
  }

  return true;
}

function  Search_PerformSearch()
{
  if (this.mNewSearchWords.length > 0)
  {
    var  MaxIndex;
    var  Index;
    var  CurrentBook;
    var  MaxCount;
    var  Count;


    // Reset search results
    //
    this.mResults = new Array();
    this.mResults.length = WWHelpTopFrame.WWHelp.mBookList.length;

    // Reset search words
    //
    this.mSearchWords = new SearchBlank_Object();
    for (MaxIndex = this.mNewSearchWords.length, Index = 0 ; Index < MaxIndex ; Index++)
    {
      this.mSearchWords[this.mNewSearchWords[Index]] = false;  // Indicates whether they have been found
    }
    this.mNewSearchWords.length = 0;

    // Search all books for matches
    //

    // Reset this.mCurrentBookIndex to 0
    //
    this.mCurrentBookIndex = 0;
    for (MaxIndex = WWHelpTopFrame.WWHelp.mBookList.length, Index = 0 ; Index < MaxIndex ; Index++)
    {
      CurrentBook = WWHelpTopFrame.WWHelp.mBookList[Index];

      for (MaxCount = CurrentBook.mSearchFileCount, Count = 0 ; Count < MaxCount ; Count++)
      {
        // Include search script
        //
        WWHelpTopFrame.WWHNavigation.WWHSync.document.writeln("<script language=\"JavaScript\" src=\"" + WWHelpTopFrame.WWHelp.mBaseURL + CurrentBook.mBookDir + "wwhdata/js/search" + Count + ".js\"></script>");

        // Search file
        //
        WWHelpTopFrame.WWHNavigation.WWHSync.document.writeln("<script language=\"JavaScript\" src=\"" + WWHelpTopFrame.WWHelp.mBaseURL + "wwhelp/js/scripts/search1s.js\"></script>");
      }

      // Increment this.mCurrentBookIndex
      //
      WWHelpTopFrame.WWHNavigation.WWHSync.document.writeln("<script language=\"JavaScript\" src=\"" + WWHelpTopFrame.WWHelp.mBaseURL + "wwhelp/js/scripts/search2s.js\"></script>");
    }
  }
}

function  Search_GenerateStyles()
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
  for (ChildLevel = 1 ; ChildLevel < 2 ; ChildLevel++)
  {
    WWHelpTopFrame.WWHNavigation.WWHSync.document.writeln("  p.l" + ChildLevel + " { margin-left: " + (WWHelpTopFrame.WWHelp.mMessages["Search Indent"] * ChildLevel) + "pt }");
  }
  WWHelpTopFrame.WWHNavigation.WWHSync.document.writeln(" -->");
  WWHelpTopFrame.WWHNavigation.WWHSync.document.writeln("</style>");
}

function  Search_DisplayResults()
{
  var  bSearchWordsExist;
  var  SearchWord;


  // Check to see if results exist
  //
  bSearchWordsExist = false;
  for (SearchWord in this.mSearchWords)
  {
    bSearchWordsExist = true;
  }

  // Display results or prompt user to enter search words
  //
  if (bSearchWordsExist == false)
  {
    WWHelpTopFrame.WWHNavigation.WWHSync.document.writeln("<h3>" + WWHelpTopFrame.WWHelp.mMessages["Search EnterSearch"] + "</h3>");
  }
  else
  {
    var  bInvalidResults;
    var  MaxIndex;
    var  Index;


    bInvalidResults = false;
    for (SearchWord in this.mSearchWords)
    {
      if (this.mSearchWords[SearchWord] != true)
      {
        bInvalidResults = true;
      }
    }

    // Check to see if entries exist for each book
    //
    if ( ! bInvalidResults)
    {
      bInvalidResults = true;
      for (MaxIndex = this.mResults.length, Index = 0 ; Index < MaxIndex ; Index++)
      {
        if ((this.mResults[Index] != null) &&
            (this.mResults[Index].length > 0))
        {
          bInvalidResults = false;
        }
      }
    }

    if (bInvalidResults)
    {
      WWHelpTopFrame.WWHNavigation.WWHSync.document.writeln("<h3>" + WWHelpTopFrame.WWHelp.mMessages["Search NoMatch"] + "</h3>");
    }
    else
    {
      var  BookResults;
      var  bNeedSpacer = false;
      var  MaxHREFIndex;
      var  HREFIndex;
      var  Title;


      for (MaxIndex = this.mResults.length, Index = 0 ; Index < MaxIndex ; Index++)
      {
        BookResults = this.mResults[Index];
        if (BookResults != null)
        {
          // Put spacers between books
          //
          if (bNeedSpacer)
          {
            WWHelpTopFrame.WWHNavigation.WWHSync.document.writeln("<p>&nbsp;</p>");
          }
          else
          {
            bNeedSpacer = true;
          }

          // Display book title
          //
          WWHelpTopFrame.WWHNavigation.WWHSync.document.writeln("<p><nobr><b>" + WWHelpTopFrame.WWHelp.mBookList[Index].mBookTitle + "</b></nobr></p>");

          for (MaxHREFIndex = BookResults.length, HREFIndex = 0 ; HREFIndex < MaxHREFIndex ; HREFIndex++)
          {
            // Translate HREF to file title
            //
            Title = WWHelpTopFrame.WWHelp.fHREFToTitle(Index, BookResults[HREFIndex]);
            if (Title.length == 0)
            {
              Title = BookResults[HREFIndex];
            }

            WWHelpTopFrame.WWHNavigation.WWHSync.document.writeln("<p class=l1><nobr><a href=\"javascript:WWHelpTopFrame.WWHelp.mSearch.fJumpTo(" + Index + ", '" + BookResults[HREFIndex] + "');\">" + Title + "</a></nobr></p>");
          }
        }
      }
    }
  }
}

function  Search_DisplaySearchForm()
{
  var  SearchWord;
  var  SearchWordsString;


  // Get search words
  //
  SearchWordsString = "";
  for (SearchWord in this.mSearchWords)
  {
    if (SearchWordsString.length > 0)
    {
      SearchWordsString += " ";
    }

    SearchWordsString += SearchWord;
  }

  WWHelpTopFrame.WWHNavigation.WWHSync.document.writeln("<form onSubmit=\"WWHelpTopFrame.WWHelp.mSearch.fSearchSubmitted();\">");
  WWHelpTopFrame.WWHNavigation.WWHSync.document.writeln("<nobr>");
  WWHelpTopFrame.WWHNavigation.WWHSync.document.writeln("<input type=\"text\" name=\"SearchWords\" size=20 value=\"" + SearchWordsString + "\">");
  WWHelpTopFrame.WWHNavigation.WWHSync.document.writeln("<input type=\"submit\" value=\"" + WWHelpTopFrame.WWHelp.mMessages["Search Search"] + "\">");
  WWHelpTopFrame.WWHNavigation.WWHSync.document.writeln("</nobr>");
  WWHelpTopFrame.WWHNavigation.WWHSync.document.writeln("</form>");
}

function  Search_JumpTo(ParamBookIndex,
                        ParamHREF)
{
  var  JumpToHREF = "";


  JumpToHREF += WWHelpTopFrame.WWHelp.mBaseURL;
  JumpToHREF += WWHelpTopFrame.WWHelp.mBookList[ParamBookIndex].mBookDir;
  JumpToHREF += ParamHREF;

  WWHelpTopFrame.WWHDocument.location.href = JumpToHREF;
}
