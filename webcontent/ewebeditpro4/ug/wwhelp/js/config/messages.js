function  WWHelp_Messages_UserOptions(ParamMessages)
{
  // Link Color
  //
  ParamMessages["Font Family"] = "Verdana, Arial, Helvetica, sans-serif";
  ParamMessages["Font Size"]   = "10pt";

  // Link Color
  //
  ParamMessages["Anchor Color"] = "#003399";

  // Tab Colors
  //
  ParamMessages["Tabs Selected Tab Color"]  = "#3366CC";
  ParamMessages["Tabs Selected Text Color"] = "white";
  ParamMessages["Tabs Default Tab Color"]   = "#FFFFFF";
  ParamMessages["Tabs Default Text Color"]  = "black";

  // Indentation
  //
  ParamMessages["TOC Indent"]    = 17;
  ParamMessages["Index Indent"]  = 17;
  ParamMessages["Search Indent"] = 17;
}

function  WWHelp_Messages(ParamMessages)
{
  var  Locale;
  var  MessagesFunction = null;


  // Set font and color options along with default messages
  //
  WWHelp_Messages_InitMessages(ParamMessages);

  // Set user defined font and color options
  //
  WWHelp_Messages_UserOptions(ParamMessages);

  // Determine locale
  //
  Locale = WWHelp_Messages_GetLocale();

  // Set message method based on locale
  //
  while (MessagesFunction == null)
  {
    if (Locale == "en")
    {
      MessagesFunction = WWHelp_Messages_en;
    }
    else if (Locale == "fr")
    {
      MessagesFunction = WWHelp_Messages_fr;
    }
    else if (Locale == "de")
    {
      MessagesFunction = WWHelp_Messages_de;
    }
    else if (Locale == "ja")
    {
      MessagesFunction = WWHelp_Messages_ja;
    }

    // If we didn't match on the full locale, i.e. "en_US",
    // try matching on shorter locale, i.e. "en"
    //
    if (MessagesFunction == null)
    {
      if (Locale.length > 2)  // Failed with 5 char, i.e. "en_US"
      {
        Locale = Locale.substring(0, 2);  // Try with 2 char, i.e. "en"
      }
      else if (Locale.length <= 2)  // Tried 2 and 5 char locales, no match, set default
      {
        MessagesFunction = WWHelp_Messages_en;  // Use English for default
      }
    }
  }

  // Set messages
  //
  MessagesFunction(ParamMessages);

  // "Favorites tab not yet implemented
  // always disable it for now
  //
  ParamMessages["Tabs Favorites"] = "";

  // Uncomment to disable the specified tab
  //
  // ParamMessages["Tabs TOC"]       = "";
  // ParamMessages["Tabs Index"]     = "";
  // ParamMessages["Tabs Search"]    = "";
  // ParamMessages["Tabs Favorites"] = "";
}

function  WWHelp_Messages_InitMessages(ParamMessages)
{
  // Link Color
  //
  ParamMessages["Font Family"] = "Verdana, Arial, Helvetica, sans-serif";
  ParamMessages["Font Size"]   = "10pt";

  // Link Color
  //
  ParamMessages["Anchor Color"] = "#003399";

  // Tab Colors
  //
  ParamMessages["Tabs Selected Tab Color"]  = "#3366CC";
  ParamMessages["Tabs Selected Text Color"] = "white";
  ParamMessages["Tabs Default Tab Color"]   = "#FFFFFF";
  ParamMessages["Tabs Default Text Color"]  = "black";

  // Tab Labels
  //
  ParamMessages["Tabs TOC"]       = "Contents";
  ParamMessages["Tabs Index"]     = "Index";
  ParamMessages["Tabs Search"]    = "Search";
  ParamMessages["Tabs Favorites"] = "Favorites";

  // Index Messages
  //
  ParamMessages["Index SelectFrom"] = "Choose link";

  // Search Messages
  //
  ParamMessages["Search Search"]       = "Search";
  ParamMessages["Search EnterSearch"]  = "Entry search words.";
  ParamMessages["Search NoMatch"]      = "No results for specified words.";
}

function  WWHelp_Messages_GetLocale()
{
  var  Locale = "en";
  var  Parts;


  // Reset locale to correct language value
  //
  if (navigator.language != null)
  {
    Locale = navigator.language;
  }
  else if (navigator.userLanguage != null)
  {
    Locale = navigator.userLanguage;
  }

  // Convert everything to lowercase
  //
  Locale = Locale.toLowerCase();

  // Replace '-'s with '_'s
  //
  Parts = Locale.split("-");
  Locale = Parts.join("_");

  return Locale;
}

function  WWHelp_Messages_en(ParamMessages)
{
  // Tab Labels
  //
  ParamMessages["Tabs TOC"]       = "Contents";
  ParamMessages["Tabs Index"]     = "Index";
  ParamMessages["Tabs Search"]    = "Search";
  ParamMessages["Tabs Favorites"] = "Favorites";

  // Index Messages
  //
  ParamMessages["Index SelectFrom"] = "The index word or phrase you chose occurs in multiple documents.";

  // Search Messages
  //
  ParamMessages["Search Search"]      = "Search";
  ParamMessages["Search EnterSearch"] = "Type in the word(s) to search for:";
  ParamMessages["Search NoMatch"]     = "No results for specified words.";
}

function  WWHelp_Messages_fr(ParamMessages)
{
  // Tab Labels
  //
  ParamMessages["Tabs TOC"]       = "Sommaire";
  ParamMessages["Tabs Index"]     = "Index";
  ParamMessages["Tabs Search"]    = "Rechercher";
  ParamMessages["Tabs Favorites"] = "Favoris";

  // Index Messages
  //
  ParamMessages["Index SelectFrom"] = "Le mot ou la phrase choisie figure dans plusieurs documents.";

  // Search Messages
  //
  ParamMessages["Search Search"]      = "Rechercher";
  ParamMessages["Search EnterSearch"] = "Tapez le(s) mot(s) \u00e0 rechercher:";
  ParamMessages["Search NoMatch"]     = "fr No results for specified words.";
}

function  WWHelp_Messages_de(ParamMessages)
{
  // Tab Labels
  //
  ParamMessages["Tabs TOC"]       = "Inhalt";
  ParamMessages["Tabs Index"]     = "Index";
  ParamMessages["Tabs Search"]    = "Suchen";
  ParamMessages["Tabs Favorites"] = "Favoriten";

  // Index Messages
  //
  ParamMessages["Index SelectFrom"] = "Der von Ihnen gew\u00e4hlte Indexeintrag wurde in mehreren Dokumenten gefunden.";

  // Search Messages
  //
  ParamMessages["Search Search"]      = "Suchen";
  ParamMessages["Search EnterSearch"] = "Geben Sie die Suchbegriffe ein:";
  ParamMessages["Search NoMatch"]     = "de No results for specified words.";
}

function  WWHelp_Messages_ja(ParamMessages)
{
  // Tab Labels
  //
  ParamMessages["Tabs TOC"]       = "\u76ee\u6b21";
  ParamMessages["Tabs Index"]     = "\u7d22\u5f15";
  ParamMessages["Tabs Search"]    = "\u691c\u7d22";
  ParamMessages["Tabs Favorites"] = "\u304a\u6c17\u306b\u5165\u308a";

  // Index Messages
  //
  ParamMessages["Index SelectFrom"] = "\u5165\u529b\u3057\u305f\u6587\u5b57\u5217\u306f\u3001\u8907\u6570\u306e\u30c9\u30ad\u30e5\u30e1\u30f3\u30c8\u306b\u5b58\u5728\u3057\u307e\u3059\u3002";

  // Search Messages
  //
  ParamMessages["Search Search"]      = "\u691c\u7d22";
  ParamMessages["Search EnterSearch"] = "\u691c\u7d22\u3059\u308b\u6587\u5b57\u5217\u3092\u5165\u529b\u3057\u3066\u304f\u3060\u3055\u3044:";
  ParamMessages["Search NoMatch"]     = "ja No results for specified words.";
}
