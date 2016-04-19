function  Book_Search(ParamSearchWord)
{
  var  Results = null;


  if (ParamSearchWord == "\u0009\u0009")
    Results = new Array("introduc.htm","introdu2.htm","introdu3.htm","dialogbo.htm","dialogb2.htm","dialogb3.htm","dialogb4.htm","dialogb5.htm","customiz.htm","customi2.htm","customi3.htm","customi4.htm","customi5.htm","customi6.htm","customi7.htm","customi8.htm","introtou.htm","html2.htm","html3.htm","html4.htm","html5.htm","html6.htm","tables.htm","html.htm","bookmara.htm","bookmar2.htm","bookmar3.htm","imageedi.htm","imageed2.htm","imageed3.htm","imageed4.htm","imageed5.htm","imageed6.htm","imageed7.htm","imageed8.htm","imageed9.htm","imagee10.htm","imagee11.htm","imagee12.htm","imagee13.htm","imagee14.htm","imagee15.htm","imagee16.htm","imagee17.htm","imagee18.htm","imagee19.htm","imagee20.htm","imagee21.htm","imagee22.htm","imagee23.htm","imagee24.htm","imagee25.htm","imagee26.htm","imagee27.htm","imagee28.htm","imagee29.htm","imagee30.htm","imagee31.htm","imagee32.htm","imagee33.htm","imagee34.htm","imagee35.htm","imagee36.htm","imagee37.htm","imagee38.htm","imagee39.htm","imagee40.htm","imagee41.htm","imagee42.htm","tableoft.htm","tableof2.htm","tableof3.htm","tableof4.htm","tableof5.htm","tableof6.htm","tableof7.htm","tableof8.htm","pictures.htm","picture2.htm","picture3.htm","picture4.htm","picture5.htm","picture6.htm","picture7.htm","picture8.htm","picture9.htm","pictur10.htm","pictur11.htm","pictur12.htm","pictur13.htm","pictur14.htm","pictur15.htm","contexts.htm","copya.htm","spellchb.htm","spellch2.htm","spellch3.htm","spellch4.htm","spellch5.htm","spellch6.htm","samplewe.htm","samplew2.htm","samplew3.htm","finda.htm","find2.htm","find3.htm","find4.htm","find5.htm","find6.htm","find7.htm","msworda.htm","msword2.htm","msword3.htm","hyperlib.htm","hyperli2.htm","hyperli3.htm","hyperli4.htm","hyperli5.htm","hyperli6.htm","hyperli7.htm","hyperli8.htm","hyperli9.htm","508.htm","tables2.htm","tables3.htm","tables4.htm","cellprop.htm","cellpro2.htm","cellpro3.htm","cellpro4.htm","cellpro5.htm","cellpro6.htm","cellpro7.htm","cellpro8.htm","cellpro9.htm","cellpr10.htm","cellpr11.htm","cellpr12.htm","cellpr13.htm","cellpr14.htm","cellpr15.htm","cellpr16.htm","cellpr17.htm","manipula.htm","manipul2.htm","manipul3.htm","manipul4.htm","manipul5.htm","manipul6.htm","manipul7.htm","manipul8.htm","manipul9.htm","manipu10.htm","manipu11.htm","manipu12.htm","5082.htm","5083.htm","5084.htm");
  else if (ParamSearchWord == "layout")
    Results = new Array("dialogb3.htm","picture6.htm","cellpro2.htm","manipul4.htm","manipul5.htm","manipul6.htm");
  else if (ParamSearchWord == "closed")
    Results = new Array("imageed4.htm","imagee25.htm");
  else if (ParamSearchWord == "previously")
    Results = new Array("imagee36.htm","picture3.htm");
  else if (ParamSearchWord == "stop")
    Results = new Array("tableof3.htm","spellch4.htm","spellch5.htm");
  else if (ParamSearchWord == "searches")
    Results = new Array("tableof4.htm");
  else if (ParamSearchWord == "manual")
    Results = new Array("tableof4.htm","spellch5.htm");
  else if (ParamSearchWord == "enables")
    Results = new Array("tableof4.htm");
  else if (ParamSearchWord == "margin")
    Results = new Array("tableof4.htm","picture9.htm");
  else if (ParamSearchWord == "left-right")
    Results = new Array("tableof6.htm");
  else if (ParamSearchWord == "books")
    Results = new Array("tableof7.htm","cellpr12.htm");
  else if (ParamSearchWord == "textarea")
    Results = new Array("tableof7.htm","tableof8.htm");
  else if (ParamSearchWord == "codes")
    Results = new Array("picture5.htm");
  else if (ParamSearchWord == "dictionary")
    Results = new Array("spellch3.htm","spellch4.htm","spellch5.htm","spellch6.htm");
  else if (ParamSearchWord == "misspelled")
    Results = new Array("spellch3.htm","spellch5.htm");
  else if (ParamSearchWord == "cancel")
    Results = new Array("spellch4.htm");
  else if (ParamSearchWord == "addresses")
    Results = new Array("spellch6.htm","hyperli3.htm");
  else if (ParamSearchWord == "inches")
    Results = new Array("manipul4.htm");
  else if (ParamSearchWord == "perform")
    Results = new Array("introduc.htm","introdu2.htm","dialogb5.htm","imageed4.htm","imagee24.htm","imagee34.htm","imagee36.htm","tableoft.htm","cellprop.htm","5083.htm");
  else if (ParamSearchWord == "surrounded")
    Results = new Array("dialogb2.htm","imageed4.htm","imagee25.htm");
  else if (ParamSearchWord == "thumbnails")
    Results = new Array("imageed4.htm");
  else if (ParamSearchWord == "degrees")
    Results = new Array("imageed4.htm","imagee29.htm");
  else if (ParamSearchWord == "rectangle's")
    Results = new Array("imagee26.htm");
  else if (ParamSearchWord == "entered")
    Results = new Array("tableof4.htm","tableof6.htm","pictur12.htm","tables2.htm");
  else if (ParamSearchWord == "distance")
    Results = new Array("tableof4.htm");
  else if (ParamSearchWord == "nbsp")
    Results = new Array("tableof4.htm");
  else if (ParamSearchWord == "european")
    Results = new Array("tableof6.htm");
  else if (ParamSearchWord == "old")
    Results = new Array("tableof7.htm");
  else if (ParamSearchWord == "myselectbox")
    Results = new Array("tableof8.htm");
  else if (ParamSearchWord == "option's")
    Results = new Array("tableof8.htm");
  else if (ParamSearchWord == "part")
    Results = new Array("cellpro2.htm");
  else if (ParamSearchWord == "guarantees")
    Results = new Array("cellpro2.htm");
  else if (ParamSearchWord == "clicked")
    Results = new Array("cellpro3.htm","cellpro4.htm");
  else if (ParamSearchWord == "window")
    Results = new Array("introduc.htm","customi6.htm","html6.htm","bookmara.htm","bookmar2.htm","bookmar3.htm","imagee21.htm","tableoft.htm","tableof6.htm","spellch2.htm","spellch6.htm","find6.htm","hyperli3.htm","hyperli4.htm","manipul3.htm");
  else if (ParamSearchWord == "spell")
    Results = new Array("introdu2.htm","tableof4.htm","spellchb.htm","spellch2.htm","spellch3.htm","spellch4.htm","spellch5.htm","spellch6.htm");
  else if (ParamSearchWord == "checking")
    Results = new Array("introdu2.htm","introtou.htm","tableof4.htm","contexts.htm","spellchb.htm","spellch2.htm","spellch3.htm","spellch4.htm","spellch5.htm","spellch6.htm");
  else if (ParamSearchWord == "alternatively")
    Results = new Array("imageed3.htm");
  else if (ParamSearchWord == "modifies")
    Results = new Array("imageed4.htm");
  else if (ParamSearchWord == "blurs")
    Results = new Array("imageed4.htm","imageed5.htm");
  else if (ParamSearchWord == "cameras")
    Results = new Array("imagee36.htm");
  else if (ParamSearchWord == "session")
    Results = new Array("imagee38.htm");
  else if (ParamSearchWord == "helpful")
    Results = new Array("tableof4.htm","contexts.htm");
  else if (ParamSearchWord == "compatible")
    Results = new Array("tableof5.htm","msworda.htm");
  else if (ParamSearchWord == "useful")
    Results = new Array("tableof6.htm");
  else if (ParamSearchWord == "updating")
    Results = new Array("tableof7.htm");
  else if (ParamSearchWord == "replaced")
    Results = new Array("tableof7.htm");
  else if (ParamSearchWord == "illustrates")
    Results = new Array("pictur11.htm","cellpro5.htm","cellpr12.htm","cellpr13.htm");
  else if (ParamSearchWord == "sentences")
    Results = new Array("samplew3.htm");
  else if (ParamSearchWord == "further")
    Results = new Array("hyperli6.htm");
  else if (ParamSearchWord == "sales")
    Results = new Array("hyperli9.htm");
  else if (ParamSearchWord == "complies")
    Results = new Array("508.htm");
  else if (ParamSearchWord == "arrange")
    Results = new Array("tables4.htm");
  else if (ParamSearchWord == "exists")
    Results = new Array("cellpro7.htm");
  else if (ParamSearchWord == "zero")
    Results = new Array("manipu12.htm");
  else if (ParamSearchWord == "boxes")
    Results = new Array("dialogbo.htm","dialogb2.htm","dialogb3.htm","dialogb4.htm","dialogb5.htm","tables.htm","pictures.htm");
  else if (ParamSearchWord == "place")
    Results = new Array("customi2.htm","customi3.htm","customi4.htm","customi5.htm","customi6.htm","customi7.htm","html4.htm","bookmara.htm","bookmar2.htm","imageed7.htm","imageed8.htm","imagee17.htm","imagee20.htm","imagee22.htm","imagee25.htm","imagee26.htm","imagee34.htm","tableof2.htm","tableof4.htm","tableof8.htm","pictures.htm","picture4.htm","pictur12.htm","contexts.htm","find5.htm","find6.htm","tables4.htm","cellpro2.htm","cellpr10.htm","cellpr12.htm","cellpr13.htm","cellpr14.htm","cellpr16.htm","manipul2.htm");
  else if (ParamSearchWord == "restoring")
    Results = new Array("customi4.htm");
  else if (ParamSearchWord == "indicate")
    Results = new Array("customi5.htm","customi7.htm","bookmara.htm","hyperlib.htm");
  else if (ParamSearchWord == "bookmarks")
    Results = new Array("introtou.htm","bookmara.htm","bookmar2.htm","bookmar3.htm","tableof4.htm","hyperlib.htm","hyperli6.htm");
  else if (ParamSearchWord == "area")
    Results = new Array("bookmar3.htm","imageed4.htm","imageed5.htm","imageed6.htm","imagee10.htm","imagee11.htm","imagee13.htm","imagee14.htm","imagee21.htm","imagee23.htm","imagee32.htm","imagee33.htm","tableof7.htm","picture6.htm","cellpro6.htm","cellpro8.htm","manipul6.htm","manipul8.htm","manipu11.htm");
  else if (ParamSearchWord == "scanning")
    Results = new Array("imageed4.htm");
  else if (ParamSearchWord == "freehand")
    Results = new Array("imageed4.htm","imagee17.htm");
  else if (ParamSearchWord == "shape")
    Results = new Array("imageed4.htm","imagee17.htm");
  else if (ParamSearchWord == "completed")
    Results = new Array("imagee38.htm");
  else if (ParamSearchWord == "pasted")
    Results = new Array("tableof4.htm","contexts.htm");
  else if (ParamSearchWord == "reader's")
    Results = new Array("tableof4.htm");
  else if (ParamSearchWord == "mycheckbox")
    Results = new Array("tableof8.htm");
  else if (ParamSearchWord == "makes")
    Results = new Array("spellch6.htm");
  else if (ParamSearchWord == "restart")
    Results = new Array("find3.htm");
  else if (ParamSearchWord == "loaded")
    Results = new Array("msword3.htm");
  else if (ParamSearchWord == "placement")
    Results = new Array("manipul2.htm");
  else if (ParamSearchWord == "vertical")
    Results = new Array("dialogb4.htm","customiz.htm","customi5.htm","customi7.htm","customi8.htm","imageed4.htm","imagee39.htm","tableof6.htm","picture9.htm","pictur11.htm","cellprop.htm","cellpr13.htm","manipul2.htm","5084.htm");
  else if (ParamSearchWord == "pasting")
    Results = new Array("html5.htm","imagee14.htm","tableoft.htm");
  else if (ParamSearchWord == "consistent")
    Results = new Array("html5.htm");
  else if (ParamSearchWord == "destination")
    Results = new Array("bookmara.htm","bookmar2.htm","bookmar3.htm","hyperli2.htm","hyperli3.htm","hyperli4.htm","hyperli6.htm","hyperli7.htm");
  else if (ParamSearchWord == "explained")
    Results = new Array("bookmar3.htm","finda.htm","cellpro2.htm","5083.htm","5084.htm");
  else if (ParamSearchWord == "rest")
    Results = new Array("imageed2.htm","spellchb.htm","spellch5.htm","samplew3.htm","cellpro7.htm","manipul5.htm","manipul9.htm","manipu11.htm");
  else if (ParamSearchWord == "fast")
    Results = new Array("imageed4.htm");
  else if (ParamSearchWord == "darkest")
    Results = new Array("imageed6.htm");
  else if (ParamSearchWord == "decide")
    Results = new Array("imagee27.htm","msworda.htm","msword3.htm");
  else if (ParamSearchWord == "re-applies")
    Results = new Array("imagee27.htm");
  else if (ParamSearchWord == "mypicture.gif")
    Results = new Array("imagee31.htm");
  else if (ParamSearchWord == "search")
    Results = new Array("tableof4.htm","find3.htm","find4.htm","find5.htm","find6.htm","find7.htm");
  else if (ParamSearchWord == "post")
    Results = new Array("tableof8.htm");
  else if (ParamSearchWord == "proceed")
    Results = new Array("pictures.htm","msword3.htm","cellpro4.htm");
  else if (ParamSearchWord == "segments")
    Results = new Array("picture9.htm");
  else if (ParamSearchWord == "decision")
    Results = new Array("spellch6.htm");
  else if (ParamSearchWord == "address")
    Results = new Array("hyperli2.htm","hyperli4.htm","hyperli6.htm","hyperli9.htm");
  else if (ParamSearchWord == "adjusts")
    Results = new Array("manipul3.htm");
  else if (ParamSearchWord == "resized")
    Results = new Array("manipul4.htm");
  else if (ParamSearchWord == "lines")
    Results = new Array("introduc.htm","imageed4.htm","imagee25.htm","tableof4.htm","tables4.htm","manipu12.htm");
  else if (ParamSearchWord == "adjust")
    Results = new Array("introduc.htm","imageed6.htm","picture5.htm","picture6.htm","pictur10.htm","cellprop.htm","manipul3.htm","manipu12.htm");
  else if (ParamSearchWord == "text's")
    Results = new Array("introdu2.htm","imagee34.htm");
  else if (ParamSearchWord == "fields")
    Results = new Array("dialogb3.htm","dialogb4.htm","tableof7.htm","picture6.htm","pictur11.htm","copya.htm","spellch6.htm","tables4.htm","5082.htm","5084.htm");
  else if (ParamSearchWord == "copies")
    Results = new Array("dialogb5.htm","imageed4.htm","imagee11.htm");
  else if (ParamSearchWord == "occupy")
    Results = new Array("customi7.htm","cellpro2.htm","cellpro4.htm","5084.htm");
  else if (ParamSearchWord == "entire")
    Results = new Array("customi7.htm","imagee11.htm","manipul9.htm");
  else if (ParamSearchWord == "target")
    Results = new Array("bookmar2.htm","bookmar3.htm","hyperli3.htm","hyperli4.htm","hyperli7.htm");
  else if (ParamSearchWord == "local")
    Results = new Array("imageed3.htm","imagee21.htm","pictures.htm","picture4.htm","picture5.htm");
  else if (ParamSearchWord == "sets")
    Results = new Array("imageed4.htm");
  else if (ParamSearchWord == "strikethrough")
    Results = new Array("imagee34.htm");
  else if (ParamSearchWord == "string")
    Results = new Array("tableof4.htm");
  else if (ParamSearchWord == "segment")
    Results = new Array("picture9.htm");
  else if (ParamSearchWord == "fix")
    Results = new Array("spellch3.htm");
  else if (ParamSearchWord == "third")
    Results = new Array("samplew2.htm");
  else if (ParamSearchWord == "compliance")
    Results = new Array("508.htm","5082.htm","5083.htm","5084.htm");
  else if (ParamSearchWord == "wanted")
    Results = new Array("tables2.htm");
  else if (ParamSearchWord == "headed")
    Results = new Array("5084.htm");
  else if (ParamSearchWord == "manipulate")
    Results = new Array("dialogbo.htm","dialogb3.htm","dialogb4.htm","manipula.htm");
  else if (ParamSearchWord == "cell")
    Results = new Array("dialogbo.htm","dialogb2.htm","dialogb3.htm","dialogb4.htm","dialogb5.htm","tables4.htm","cellprop.htm","cellpro2.htm","cellpro3.htm","cellpro4.htm","cellpro5.htm","cellpro6.htm","cellpro7.htm","cellpro9.htm","cellpr10.htm","cellpr11.htm","cellpr12.htm","cellpr13.htm","cellpr14.htm","cellpr15.htm","cellpr16.htm","cellpr17.htm","manipul2.htm","manipul3.htm","manipul9.htm","manipu11.htm");
  else if (ParamSearchWord == "characters")
    Results = new Array("customi2.htm","bookmar2.htm","tableof4.htm","tableof8.htm","spellch6.htm","cellpr16.htm");
  else if (ParamSearchWord == "remain")
    Results = new Array("customi5.htm","manipul5.htm");
  else if (ParamSearchWord == "moving")
    Results = new Array("customi7.htm","tableof4.htm","pictur14.htm","5082.htm","5084.htm");
  else if (ParamSearchWord == "light")
    Results = new Array("imageed4.htm","imagee10.htm","manipul8.htm");
  else if (ParamSearchWord == "second")
    Results = new Array("imagee32.htm","imagee38.htm","tableof4.htm","pictures.htm","picture5.htm","samplew2.htm");
  else if (ParamSearchWord == "attribute")
    Results = new Array("tableof3.htm","tableof5.htm","manipul4.htm");
  else if (ParamSearchWord == "arranged")
    Results = new Array("tableof4.htm","picture7.htm");

  return Results;
}
