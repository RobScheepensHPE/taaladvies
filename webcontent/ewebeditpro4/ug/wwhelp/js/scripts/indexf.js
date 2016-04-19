// Copyright (c) 2000-2001 Quadralay Corporation.  All rights reserved.
// 

function  Index_Object()
{
  this.mIndicies       = new Array();
  this.mJoinedIndicies = new Array();
  this.mMaxLevel       = 0;
  this.mJumpToArray    = null;

  this.fNewBook                 = Index_NewBook;
  this.fJumpTo                  = Index_JumpTo;
  this.fJumpToGenerateStyles    = Index_JumpToGenerateStyles;
  this.fJumpToDisplaySelections = Index_JumpToDisplaySelections;
  this.fSetDocumentDisplay      = Index_SetDocumentDisplay;
  this.fGenerateStyles          = Index_GenerateStyles;
  this.fDisplayIndex            = Index_DisplayIndex;
  this.fJoinIndicies            = Index_JoinIndicies;
  this.fDisplayJoinedIndicies   = Index_DisplayJoinedIndicies;
}

function  IndexJumpTo_Object(ParamBookIndex,
                             ParamLinkIndex)
{
  this.mBookIndex = ParamBookIndex;
  this.mLinkIndex = ParamLinkIndex;
}

function  IndexDisplayEntry_Object(ParamLevel,
                                   ParamEntry,
                                   ParamLinksString)
{
  this.mLevel       = ParamLevel;
  this.mEntry       = ParamEntry;
  this.mLinksString = ParamLinksString;
}

function  Index_NewBook(ParamBook)
{
  var  NewIndex;


  NewIndex = new Links_Object(ParamBook.mBookDir, ParamBook.mBookTitle);

  this.mIndicies.length++;
  this.mIndicies[this.mIndicies.length - 1] = NewIndex;

  NewIndex.fLoadIndex(ParamBook.fAddIndexEntries);

  return NewIndex;
}

function  Index_JumpTo(ParamJumpTo)
{
  var  BooksArray = ParamJumpTo.split("#");
  var  MaxBookIndex;
  var  BookIndex;
  var  BookLinkArray;


  // Build up list of all index links
  //
  this.mJumpToArray = new Array();
  for (MaxBookIndex = BooksArray.length, BookIndex = 0 ; BookIndex < MaxBookIndex ; BookIndex++)
  {
    BookLinkArray = BooksArray[BookIndex].split("=");

    this.mJumpToArray.length++;
    this.mJumpToArray[this.mJumpToArray.length - 1] = new IndexJumpTo_Object(BookLinkArray[0],   // Book Index
                                                                             BookLinkArray[1]);  // Link Index
  }

  // If index list exists, jump to single entry or display selection list
  //
  if (this.mJumpToArray.length > 0)
  {
    // Present selection dialog if necessary
    //
    if ((this.mJumpToArray.length == 1) &&
        (this.mIndicies[this.mJumpToArray[0].mBookIndex].mLinks[this.mJumpToArray[0].mLinkIndex].mHREFs.length == 1))
    {
      // Just display the entry
      //
      this.fSetDocumentDisplay(this.mJumpToArray[0].mBookIndex, this.mJumpToArray[0].mLinkIndex, 0);
    }
    else
    {
      // Display selection list
      //
      WWHelpTopFrame.WWHDocument.location.href = WWHelpTopFrame.WWHelp.mBaseURL + "wwhelp/js/html/index.htm";
    }
  }
}

function  Index_JumpToGenerateStyles()
{
  var  ChildLevel;


  WWHelpTopFrame.WWHDocument.document.writeln("<style type=\"text/css\">");
  WWHelpTopFrame.WWHDocument.document.writeln(" <!--");
  WWHelpTopFrame.WWHDocument.document.writeln("  a { text-decoration: none ;");
  WWHelpTopFrame.WWHDocument.document.writeln("      color: " + WWHelpTopFrame.WWHelp.mMessages["Anchor Color"] + " }");
  WWHelpTopFrame.WWHDocument.document.writeln("  p { margin-top: 1pt ;");
  WWHelpTopFrame.WWHDocument.document.writeln("      margin-bottom: 1pt ;");
  WWHelpTopFrame.WWHDocument.document.writeln("      font-family: " + WWHelpTopFrame.WWHelp.mMessages["Font Family"] + " ;");
  WWHelpTopFrame.WWHDocument.document.writeln("      font-size: " + WWHelpTopFrame.WWHelp.mMessages["Font Size"] + " }");
  for (ChildLevel = 1 ; ChildLevel < 3 ; ChildLevel++)
  {
    WWHelpTopFrame.WWHDocument.document.writeln("  p.l" + ChildLevel + " { margin-left: " + (WWHelpTopFrame.WWHelp.mMessages["Index Indent"] * ChildLevel) + "pt }");
  }
  WWHelpTopFrame.WWHDocument.document.writeln(" -->");
  WWHelpTopFrame.WWHDocument.document.writeln("</style>");
}

function  Index_JumpToDisplaySelections()
{
  var  MaxJumpToIndex;
  var  JumpToIndex;


  WWHelpTopFrame.WWHDocument.document.writeln("<p><b>" + this.mIndicies[this.mJumpToArray[0].mBookIndex].mLinks[this.mJumpToArray[0].mLinkIndex].mKey + "</b></p>");

  for (MaxJumpToIndex = this.mJumpToArray.length, JumpToIndex = 0 ; JumpToIndex < MaxJumpToIndex ; JumpToIndex++)
  {
    var  JumpToEntry;
    var  LinkEntry;
    var  MaxHREFIndex;
    var  HREFIndex;
    var  CopyHREFs = new Array();


    JumpToEntry = this.mJumpToArray[JumpToIndex];
    LinkEntry = this.mIndicies[JumpToEntry.mBookIndex].mLinks[JumpToEntry.mLinkIndex];

    CopyHREFs.length = LinkEntry.mHREFs.length;
    for (MaxHREFIndex = LinkEntry.mHREFs.length, HREFIndex = 0 ; HREFIndex < MaxHREFIndex ; HREFIndex++)
    {
      CopyHREFs[HREFIndex] = new String(LinkEntry.mHREFs[HREFIndex]);
    }

    WWHelpTopFrame.WWHDocument.document.writeln("<p>&nbsp;</p>");
    WWHelpTopFrame.WWHDocument.document.writeln("<p class=l1><nobr><b>" + this.mIndicies[JumpToEntry.mBookIndex].mBookTitle + "</b></nobr></p>");

    for (MaxHREFIndex = CopyHREFs.length, HREFIndex = 0 ; HREFIndex < MaxHREFIndex ; HREFIndex++)
    {
      if (CopyHREFs[HREFIndex] != null)
      {
        var  AltHREFIndex;
        var  LinkHREF;
        var  LinkHREFAnchorIndex;
        var  LinkHREFTrimmed;
        var  SomeHREFTrimmed;
        var  LinkTitle;


        // Find link without named anchors attached
        //
        LinkHREFTrimmed = WWHelpTopFrame.WWHelp.fTrimHREF(CopyHREFs[HREFIndex]);

        // Find link title
        //
        LinkTitle = WWHelpTopFrame.WWHelp.fHREFToTitle(JumpToEntry.mBookIndex, LinkHREFTrimmed);

        // If no match found, default to index word
        //
        if (LinkTitle.length <= 0)
        {
          LinkTitle = LinkHREFTrimmed;
        }

        // Drop the link
        //
        WWHelpTopFrame.WWHDocument.document.write("<p class=l2><nobr><a href=\"javascript:WWHelpTopFrame.WWHelp.mIndex.fSetDocumentDisplay(" + JumpToEntry.mBookIndex + "," + JumpToEntry.mLinkIndex + "," + HREFIndex + ");\">" + LinkTitle + "</a>");

        // Look for links to the same document
        //
        for (AltHREFIndex = (HREFIndex + 1) ; AltHREFIndex < MaxHREFIndex ; AltHREFIndex++)
        {
          if (CopyHREFs[AltHREFIndex] != null)
          {
            // Find link without named anchors attached
            //
            SomeHREFTrimmed = WWHelpTopFrame.WWHelp.fTrimHREF(CopyHREFs[AltHREFIndex]);

            if (SomeHREFTrimmed == LinkHREFTrimmed)
            {
              // Found a link in the same document
              //
              WWHelpTopFrame.WWHDocument.document.write(",&nbsp;<a href=\"javascript:WWHelpTopFrame.WWHelp.mIndex.fSetDocumentDisplay(" + JumpToEntry.mBookIndex + "," + JumpToEntry.mLinkIndex + "," + AltHREFIndex + ");\">" + (AltHREFIndex - HREFIndex + 1) + "</a>");

              // Remove entry so it won't be added more than once
              //
              CopyHREFs[AltHREFIndex] = null;
            }
          }
        }

        WWHelpTopFrame.WWHDocument.document.writeln("</nobr></p>");
      }
    }
  }
}

function  Index_SetDocumentDisplay(ParamBookIndex,
                                   ParamLinkIndex,
                                   ParamHREFIndex)
{
  var  NewHREF;


  NewHREF = WWHelpTopFrame.WWHelp.mBaseURL + this.mIndicies[ParamBookIndex].mBookDir + this.mIndicies[ParamBookIndex].mLinks[ParamLinkIndex].mHREFs[ParamHREFIndex];

  WWHelpTopFrame.WWHDocument.location.href = NewHREF;
}

function  Index_GenerateStyles()
{
  var  ChildLevel;


  // Build up display list
  //
  if (this.mJoinedIndicies.length == 0)
  {
    this.fJoinIndicies();
  }

  // Generate style section
  //
  WWHelpTopFrame.WWHNavigation.WWHSync.document.writeln("<style type=\"text/css\">");
  WWHelpTopFrame.WWHNavigation.WWHSync.document.writeln(" <!--");
  WWHelpTopFrame.WWHNavigation.WWHSync.document.writeln("  a { text-decoration: none ;");
  WWHelpTopFrame.WWHNavigation.WWHSync.document.writeln("      color: " + WWHelpTopFrame.WWHelp.mMessages["Anchor Color"] + " }");
  WWHelpTopFrame.WWHNavigation.WWHSync.document.writeln("  p { margin-top: 1pt ;");
  WWHelpTopFrame.WWHNavigation.WWHSync.document.writeln("      margin-bottom: 1pt ;");
  WWHelpTopFrame.WWHNavigation.WWHSync.document.writeln("      font-family: " + WWHelpTopFrame.WWHelp.mMessages["Font Family"] + " ;");
  WWHelpTopFrame.WWHNavigation.WWHSync.document.writeln("      font-size: " + WWHelpTopFrame.WWHelp.mMessages["Font Size"] + " }");
  for (ChildLevel = 1 ; ChildLevel <= this.mMaxLevel ; ChildLevel++)
  {
    WWHelpTopFrame.WWHNavigation.WWHSync.document.writeln("  p.l" + ChildLevel + " { margin-left: " + (WWHelpTopFrame.WWHelp.mMessages["Index Indent"] * ChildLevel) + "pt }");
  }
  WWHelpTopFrame.WWHNavigation.WWHSync.document.writeln(" -->");
  WWHelpTopFrame.WWHNavigation.WWHSync.document.writeln("</style>");
}

function  Index_DisplayIndex()
{
  this.fDisplayJoinedIndicies();
}

function  Index_JoinIndicies()
{
  var  CurPosArray = new Array();
  var  MaxIndex;
  var  Index;
  var  bMergeDone = true;
  var  SmallestEntry;
  var  LinksString;
  var  CurrentLinkEntry;
  var  BookSeparator;
  var  DisplayLevel;
  var  DisplayString;
  var  SplitStrings;
  var  NewDisplayEntry;


  // Reset this.mMaxLevel
  //
  this.mMaxLevel = 0;

  this.mJoinedIndicies.length = 0;

  // Sort each index individually and
  // initialize current position array for each index
  //
  CurPosArray.length = this.mIndicies.length;
  MaxIndex = CurPosArray.length;
  for (Index = 0 ; Index < MaxIndex ; Index++)
  {
    CurPosArray[Index] = 0;  // Current position in all indicies starts at 0

    if (this.mIndicies[Index].mLinks.length <= 0)
    {
      CurPosArray[Index] = -1;  // Indicates the end of index reached
    }
    else  // Entries to process exist
    {
      bMergeDone = false;

      // Sort the index
      //
      this.mIndicies[Index].fSortEntries();
    }
  }

  // Perform an n-way merge sort
  //
  while ( ! bMergeDone)
  {
    // Set initial value for SmallestEntry
    // Need to do this before adding links together to avoid
    // combined entry problems when we discover a smaller entry
    // later on in the merge process.  This avoids the problem
    // by finding the smallest entry first.
    // MaxIndex still set to CurPosArray.length
    //
    bMergeDone = true;
    SmallestEntry = null;
    for (Index = 0 ; Index < MaxIndex ; Index++)
    {
      if (CurPosArray[Index] >= 0)  // Check for indicies that are done
      {
        CurrentLinkEntry = this.mIndicies[Index].mLinks[CurPosArray[Index]];

        if ((SmallestEntry == null) ||
            (LinksEntry_SortFunction(CurrentLinkEntry, SmallestEntry) == -1))
        {
          bMergeDone = false;
          SmallestEntry = CurrentLinkEntry;
        }
      }
    }

    // Add links for the smallest entry
    //
    if (SmallestEntry != null)
    {
      // For all valid positions (not -1, > 0), find the smallest entry key
      // MaxIndex still set to CurPosArray.length
      //
      LinksString = "";
      for (Index = 0 ; Index < MaxIndex ; Index++)
      {
        // Only add links if current entry is equal to the smallest entry
        //
        while ((CurPosArray[Index] >= 0) &&  // Check for indicies that are done
               (CurrentLinkEntry = this.mIndicies[Index].mLinks[CurPosArray[Index]]) &&  // Intentinal assignment
               (LinksEntry_SortFunction(CurrentLinkEntry, SmallestEntry) == 0))
        {
          // Add links via numerical entries
          //
          HREFsArray = CurrentLinkEntry.mHREFs;

          if (HREFsArray.length > 0)  // Only add entries if there are links
          {
            if (LinksString.length > 0)
            {
              BookSeparator = "#";  // Add separator between book entries
            }
            else
            {
              BookSeparator = "";  // Add separator between book entries
            }

            // Format is:
            //
            //  [Book Index]=[Link Index]#[Book Index]=[Link Index]
            //
            LinksString += BookSeparator + Index + "=" + CurPosArray[Index];
          }

          // Increment position
          //
          CurPosArray[Index] += 1;

          // Test for reaching end of index
          //
          if (CurPosArray[Index] >= this.mIndicies[Index].mLinks.length)
          {
            CurPosArray[Index] = -1;  // Indicates the end of index reached
          }
        }
      }

      // Display smallest entry and bump the position counter
      //

      // Set display entry level and string
      //
      if (SmallestEntry.mKey.indexOf("\t") != -1)
      {
        SplitStrings  = SmallestEntry.mKey.split("\t");

        DisplayString = SplitStrings[SplitStrings.length - 1];
        DisplayLevel  = SplitStrings.length;
      }
      else
      {
        DisplayString = SmallestEntry.mKey;
        DisplayLevel  = 1;
      }

      // Display index entry
      //
      NewDisplayEntry = new IndexDisplayEntry_Object(DisplayLevel, DisplayString, LinksString);

      this.mJoinedIndicies.length++;
      this.mJoinedIndicies[this.mJoinedIndicies.length - 1] = NewDisplayEntry;

      // Update MaxLevel
      //
      if (DisplayLevel > this.mMaxLevel)
      {
        this.mMaxLevel = DisplayLevel;
      }
    }
  }
}

function  Index_DisplayJoinedIndicies()
{
  var  MaxIndex;
  var  Index;
  var  PrevCharSection = "";
  var  CurCharSection;
  var  DocumentObj = eval("WWHelpTopFrame.WWHNavigation.WWHSync.document");


  for (MaxIndex = this.mJoinedIndicies.length, Index = 0 ; Index < MaxIndex ; Index++)
  {
    // Insert breaks between sections
    //
    if (this.mJoinedIndicies[Index].mLevel == 1)
    {
      CurCharSection = this.mJoinedIndicies[Index].mEntry.charAt(0).toUpperCase();
      if (CurCharSection != PrevCharSection)
      {
        if (PrevCharSection.length > 0)
        {
          DocumentObj.writeln("<p>&nbsp;</p>");
        }
        DocumentObj.writeln("<p><b>" + CurCharSection.toUpperCase() + "</b></p>");

        PrevCharSection = CurCharSection;
      }
    }

    // Display index entry
    //
    if (this.mJoinedIndicies[Index].mLinksString.length == 0)  // Check for items with no entries
    {
      DocumentObj.writeln("<p class=l" + this.mJoinedIndicies[Index].mLevel + "><nobr>"  + this.mJoinedIndicies[Index].mEntry + "</nobr></p>");
    }
    else
    {
      DocumentObj.writeln("<p class=l" + this.mJoinedIndicies[Index].mLevel + "><nobr><a href=\"javascript:WWHelpTopFrame.WWHelp.mIndex.fJumpTo('" + this.mJoinedIndicies[Index].mLinksString + "');\">" + this.mJoinedIndicies[Index].mEntry + "</a></nobr></p>");
    }
  }
}
