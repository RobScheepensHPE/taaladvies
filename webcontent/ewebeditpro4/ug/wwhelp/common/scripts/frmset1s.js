// Copyright (c) 2000-2001 Quadralay Corporation.  All rights reserved.
// 
  // Check for context match
  //
  if ((WWHelpFrameSet.mBookTag  != null) &&
      (WWHelpFrameSet.mTopicTag != null))
  {
    WWHelpFrameSet.fProcessBook(Book_Context, Book_Popups, Book_MatchTopic);
  }
  else
  {
    WWHelpFrameSet.fProcessBook(Book_Context, Book_Popups, null);
  }
