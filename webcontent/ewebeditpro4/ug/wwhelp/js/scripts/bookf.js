// Copyright (c) 2000-2001 Quadralay Corporation.  All rights reserved.
// 

function  BookFile_Object(ParamTitle,
                          ParamHREF)
{
  this.mTitle = ParamTitle;
  this.mHREF  = ParamHREF;
}

function  Book_Object(ParamBookDir)
{
  this.mBookDir         = ParamBookDir;
  this.mBookTitle       = Book_BookTitle();
  this.mSearchFileCount = Book_SearchFileCount();

  this.fAddFiles        = Book_AddFiles;
  this.fAddTOCEntries   = Book_AddTOCEntries;
  this.fAddIndexEntries = Book_AddIndexEntries;

  this.fNewFile     = Book_NewFile;
  this.fHREFToTitle = Book_HREFToTitle;

  this.mFiles = new Array();

  this.fAddFiles();
}

function  Book_NewFile(ParamTitle,
                       ParamHREF)
{
  this.mFiles.length++;
  this.mFiles[this.mFiles.length - 1] = new BookFile_Object(ParamTitle, ParamHREF);
}

function  Book_HREFToTitle(ParamHREF)
{
  var  Title;
  var  MaxIndex;
  var  Index;


  Title = "";
  MaxIndex = this.mFiles.length;
  Index = 0;
  while ((Title.length == 0) &&
         (Index < MaxIndex))
  {
    if (this.mFiles[Index].mHREF == ParamHREF)
    {
      Title = this.mFiles[Index].mTitle;
    }

    Index++;
  }

  return Title;
}
