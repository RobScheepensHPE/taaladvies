// Copyright (c) 2000-2001 Quadralay Corporation.  All rights reserved.
// 

function  LinksEntry_Object(ParamEntry,
                            ParamHREFArray)
{
  this.mKey   = ParamEntry;
  this.mHREFs = ParamHREFArray;
}

function  LinksEntry_SortFunction(ParamAlpha,
                                  ParamBeta)
{
  var  Result = 0;


  if (ParamAlpha.mSortKey < ParamBeta.mSortKey)
  {
    Result = -1;
  }
  else if (ParamAlpha.mSortKey > ParamBeta.mSortKey)
  {
    Result = 1;
  }
  else if (ParamAlpha.mKey < ParamBeta.mKey)
  {
    Result = -1;
  }
  else if (ParamAlpha.mKey > ParamBeta.mKey)
  {
    Result = 1;
  }

  return Result;
}

function  Links_Object(ParamBookDir,
                       ParamBookTitle)
{
  this.mBookDir   = ParamBookDir;
  this.mBookTitle = ParamBookTitle;
  this.mLinks     = new Array();
  this.mLinksSize = 0;

  this.fLoadIndex       = Links_LoadIndex;
  this.fSetTotalEntries = Links_SetTotalEntries;
  this.fAddEntry        = Links_AddEntry;
  this.fSortEntries     = Links_SortEntries;
}

function  Links_LoadIndex(ParamAddLinksFunc)
{
  this.mLinksSize = 0;

  ParamAddLinksFunc(this);

  if (this.mLinks.length > this.mLinksSize)
  {
    this.mLinks.length = this.mLinksSize;  // Resize to correct length
  }
}

function  Links_SetTotalEntries(ParamTotal)
{
  this.mLinks.length = ParamTotal;
}

function  Links_AddEntry(ParamEntry,
                         ParamHREFArray)
{
  var  NewLink;


  NewLink = new LinksEntry_Object(ParamEntry, ParamHREFArray);

  this.mLinksSize++;

  // Make sure we grow big enough
  //
  if (this.mLinksSize > this.mLinks.length)
  {
    this.mLinks.length++;
  }

  this.mLinks[this.mLinksSize - 1] = NewLink;
}

function  Links_SortEntries()
{
  var  MaxIndex;
  var  Index;
  var  CurrentEntry;


  for (MaxIndex = this.mLinks.length, Index = 0 ; Index < MaxIndex ; Index++)
  {
    CurrentEntry = this.mLinks[Index];

    if (CurrentEntry.mSortKey == null)
    {
      CurrentEntry.mSortKey = CurrentEntry.mKey.toUpperCase();
    }
  }

  this.mLinks.sort(LinksEntry_SortFunction);
}
