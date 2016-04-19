function  Book_Search(ParamSearchWord)
{
  var  Results = null;


  if (ParamSearchWord == "editing")
    Results = new Array("introduc.htm","dialogb5.htm","introtou.htm","html2.htm","html3.htm","html.htm","imageedi.htm","imageed2.htm","imageed3.htm","imageed4.htm","imageed5.htm","imageed6.htm","imageed7.htm","imageed8.htm","imageed9.htm","imagee10.htm","imagee11.htm","imagee12.htm","imagee13.htm","imagee14.htm","imagee15.htm","imagee16.htm","imagee17.htm","imagee18.htm","imagee19.htm","imagee20.htm","imagee21.htm","imagee22.htm","imagee23.htm","imagee24.htm","imagee25.htm","imagee26.htm","imagee27.htm","imagee28.htm","imagee29.htm","imagee30.htm","imagee31.htm","imagee32.htm","imagee33.htm","imagee34.htm","imagee35.htm","imagee36.htm","imagee37.htm","imagee38.htm","imagee39.htm","imagee40.htm","imagee41.htm","imagee42.htm","tableof4.htm","tableof6.htm","picture4.htm","pictur12.htm","contexts.htm","msworda.htm","msword2.htm","msword3.htm","hyperlib.htm","hyperli7.htm","manipu12.htm");
  else if (ParamSearchWord == "inside")
    Results = new Array("introduc.htm","dialogb2.htm");
  else if (ParamSearchWord == "explains")
    Results = new Array("introdu3.htm","dialogbo.htm","dialogb3.htm","dialogb4.htm","introtou.htm","tables.htm","html.htm","bookmara.htm","tableoft.htm","tableof4.htm","contexts.htm","spellchb.htm","samplewe.htm","hyperlib.htm","508.htm","cellpro6.htm","cellpro7.htm","manipula.htm","5083.htm");
  else if (ParamSearchWord == "ewebeditpro's")
    Results = new Array("introdu3.htm","introtou.htm");
  else if (ParamSearchWord == "down")
    Results = new Array("dialogb4.htm","customi3.htm","customi5.htm","customi7.htm","bookmar2.htm","imagee21.htm","tableof2.htm","tableof4.htm","picture5.htm","samplew3.htm","find5.htm","hyperli3.htm","hyperli6.htm","cellpro7.htm","cellpr10.htm","cellpr12.htm","cellpr13.htm","cellpr16.htm","manipul4.htm","manipul9.htm","5083.htm");
  else if (ParamSearchWord == "clipboard")
    Results = new Array("dialogb5.htm","tableof4.htm","contexts.htm","hyperli6.htm");
  else if (ParamSearchWord == "copied")
    Results = new Array("dialogb5.htm","imageed4.htm","imagee11.htm","tableof4.htm","picture5.htm","contexts.htm","copya.htm","msworda.htm","hyperli6.htm");
  else if (ParamSearchWord == "however")
    Results = new Array("introtou.htm","html.htm","cellprop.htm","cellpro5.htm","manipu11.htm");
  else if (ParamSearchWord == "whenever")
    Results = new Array("html2.htm","spellchb.htm","spellch2.htm","spellch4.htm","5084.htm");
  else if (ParamSearchWord == "browser")
    Results = new Array("html5.htm","html.htm","bookmar3.htm","tableof4.htm","cellpro2.htm","manipul3.htm","manipul4.htm","manipul5.htm","manipul6.htm","5082.htm");
  else if (ParamSearchWord == "yankees")
    Results = new Array("tables.htm","cellpro9.htm");
  else if (ParamSearchWord == "followed")
    Results = new Array("imageed4.htm");
  else if (ParamSearchWord == "brightest")
    Results = new Array("imageed6.htm");
  else if (ParamSearchWord == "formats")
    Results = new Array("imagee31.htm");
  else if (ParamSearchWord == "form")
    Results = new Array("tableof7.htm","tableof8.htm");
  else if (ParamSearchWord == "multiple")
    Results = new Array("tableof8.htm");
  else if (ParamSearchWord == "identify")
    Results = new Array("picture4.htm","spellch6.htm");
  else if (ParamSearchWord == "connection")
    Results = new Array("picture5.htm","pictur13.htm","samplew3.htm");
  else if (ParamSearchWord == "extend")
    Results = new Array("picture9.htm");
  else if (ParamSearchWord == "midpoint")
    Results = new Array("picture9.htm");
  else if (ParamSearchWord == "sections")
    Results = new Array("samplewe.htm","samplew3.htm");
  else if (ParamSearchWord == "becoming")
    Results = new Array("hyperlib.htm","hyperli9.htm");
  else if (ParamSearchWord == "hyperlink's")
    Results = new Array("hyperli7.htm");
  else if (ParamSearchWord == "regardless")
    Results = new Array("cellpro5.htm");
  else if (ParamSearchWord == "don't")
    Results = new Array("manipul2.htm");
  else if (ParamSearchWord == "non-visual")
    Results = new Array("5084.htm");
  else if (ParamSearchWord == "text")
    Results = new Array("introduc.htm","introdu2.htm","dialogb3.htm","dialogb4.htm","dialogb5.htm","customi2.htm","introtou.htm","html5.htm","html6.htm","html.htm","bookmara.htm","bookmar2.htm","imageedi.htm","imageed4.htm","imageed8.htm","imagee34.htm","tableoft.htm","tableof2.htm","tableof3.htm","tableof4.htm","tableof5.htm","tableof6.htm","tableof7.htm","tableof8.htm","picture4.htm","picture9.htm","pictur12.htm","pictur14.htm","contexts.htm","spellchb.htm","spellch5.htm","samplew2.htm","samplew3.htm","finda.htm","find2.htm","find3.htm","find4.htm","find5.htm","find6.htm","find7.htm","hyperlib.htm","hyperli2.htm","hyperli3.htm","hyperli4.htm","hyperli6.htm","hyperli8.htm","tables4.htm","cellpro7.htm","cellpr11.htm","cellpr12.htm","cellpr13.htm","cellpr16.htm","manipul6.htm","manipul8.htm","manipul9.htm","manipu11.htm","5083.htm");
  else if (ParamSearchWord == "require")
    Results = new Array("introdu3.htm");
  else if (ParamSearchWord == "original")
    Results = new Array("dialogb2.htm","dialogb5.htm","tableof4.htm","picture5.htm","pictur10.htm","contexts.htm","copya.htm","cellpr14.htm");
  else if (ParamSearchWord == "default")
    Results = new Array("dialogb3.htm","dialogb4.htm","customi6.htm","html2.htm","bookmar3.htm","imagee12.htm","imagee35.htm","tableof8.htm","picture9.htm","find6.htm","find7.htm","cellpro5.htm","cellpr16.htm","manipu11.htm");
  else if (ParamSearchWord == "pixels")
    Results = new Array("dialogb3.htm","imagee15.htm","imagee19.htm","imagee35.htm","picture6.htm","picture7.htm","picture8.htm","pictur11.htm","cellpro2.htm","cellpr17.htm","manipul3.htm","manipul5.htm","manipu12.htm");
  else if (ParamSearchWord == "removes")
    Results = new Array("dialogb5.htm","html5.htm","imageed4.htm","imagee13.htm","imagee14.htm","tableof8.htm");
  else if (ParamSearchWord == "customization")
    Results = new Array("customi3.htm","customi6.htm");
  else if (ParamSearchWord == "product")
    Results = new Array("imageedi.htm","samplew3.htm");
  else if (ParamSearchWord == "checks")
    Results = new Array("imageed3.htm","spellch4.htm");
  else if (ParamSearchWord == "back")
    Results = new Array("imageed4.htm","imagee16.htm","tableof5.htm","msworda.htm");
  else if (ParamSearchWord == "increases")
    Results = new Array("imageed4.htm","imageed6.htm","imagee10.htm","imagee33.htm","imagee40.htm");
  else if (ParamSearchWord == "height")
    Results = new Array("imageed4.htm","imagee15.htm","imagee19.htm","picture3.htm","picture4.htm","picture5.htm","picture6.htm","pictur10.htm");
  else if (ParamSearchWord == "automatically")
    Results = new Array("imagee15.htm","pictur10.htm","hyperli9.htm");
  else if (ParamSearchWord == "wraps")
    Results = new Array("imagee34.htm");
  else if (ParamSearchWord == "limitations")
    Results = new Array("imagee36.htm");
  else if (ParamSearchWord == "checker")
    Results = new Array("tableof4.htm","spellch2.htm","spellch3.htm","spellch5.htm");
  else if (ParamSearchWord == "lock")
    Results = new Array("tableof5.htm");
  else if (ParamSearchWord == "needed")
    Results = new Array("tableof7.htm","picture5.htm","find3.htm","hyperli7.htm","tables4.htm","5084.htm");
  else if (ParamSearchWord == "exceeds")
    Results = new Array("tableof8.htm","picture4.htm");
  else if (ParamSearchWord == "resetting")
    Results = new Array("pictur10.htm");
  else if (ParamSearchWord == "speed")
    Results = new Array("spellch3.htm");
  else if (ParamSearchWord == "18")
    Results = new Array("samplew2.htm","samplew3.htm");
  else if (ParamSearchWord == "access")
    Results = new Array("introduc.htm","dialogb2.htm","pictures.htm","contexts.htm","tables2.htm","5083.htm");
  else if (ParamSearchWord == "advanced")
    Results = new Array("introdu3.htm","introtou.htm","picture5.htm");
  else if (ParamSearchWord == "contents")
    Results = new Array("dialogb2.htm","dialogb5.htm","tableof4.htm","contexts.htm","5084.htm");
  else if (ParamSearchWord == "horizontal")
    Results = new Array("dialogb3.htm","dialogb4.htm","customi7.htm","customi8.htm","imageed4.htm","imagee18.htm","tableof4.htm","pictur11.htm","tables2.htm","cellprop.htm","cellpr12.htm","manipula.htm","manipul2.htm","manipul6.htm","5084.htm");
  else if (ParamSearchWord == "spanning")
    Results = new Array("dialogb4.htm","cellpro9.htm","cellpr10.htm");
  else if (ParamSearchWord == "link")
    Results = new Array("dialogb5.htm","bookmar2.htm","contexts.htm","samplew3.htm","hyperli3.htm","hyperli4.htm","hyperli6.htm","hyperli7.htm");
  else if (ParamSearchWord == "consists")
    Results = new Array("customiz.htm","imageedi.htm","5083.htm");
  else if (ParamSearchWord == "objects")
    Results = new Array("customi2.htm","tableof5.htm");
  else if (ParamSearchWord == "items")
    Results = new Array("customi3.htm");
  else if (ParamSearchWord == "etc.this")
    Results = new Array("introtou.htm");
  else if (ParamSearchWord == "cleans")
    Results = new Array("html2.htm","html5.htm");
  else if (ParamSearchWord == "details")
    Results = new Array("bookmar2.htm","samplew3.htm","hyperli3.htm","hyperli4.htm","manipula.htm","manipul4.htm");
  else if (ParamSearchWord == "single")
    Results = new Array("imageed4.htm","imagee36.htm","tableof2.htm","picture7.htm","spellch5.htm");
  else if (ParamSearchWord == "preview")
    Results = new Array("imagee35.htm","picture3.htm","picture4.htm","picture5.htm");
  else if (ParamSearchWord == "fonts")
    Results = new Array("tableof4.htm","samplew3.htm");
  else if (ParamSearchWord == "element")
    Results = new Array("tableof7.htm");
  else if (ParamSearchWord == "\u0009\u0009\u0009\u0009\u0009\u0009\u0009\u0009\u0009\u0009")
    Results = new Array("hyperli6.htm");
  else if (ParamSearchWord == "turned")
    Results = new Array("cellpr16.htm","manipul4.htm");
  else if (ParamSearchWord == "low")
    Results = new Array("manipul5.htm");
  else if (ParamSearchWord == "lands")
    Results = new Array("5082.htm");
  else if (ParamSearchWord == "\u0009  \u000a")
    Results = new Array("introduc.htm","introdu2.htm","introdu3.htm","dialogbo.htm","dialogb2.htm","dialogb3.htm","dialogb4.htm","dialogb5.htm","customiz.htm","customi2.htm","customi3.htm","customi4.htm","customi5.htm","customi6.htm","customi7.htm","customi8.htm","introtou.htm","html2.htm","html3.htm","html4.htm","html5.htm","html6.htm","tables.htm","html.htm","bookmara.htm","bookmar2.htm","bookmar3.htm","imageedi.htm","imageed2.htm","imageed3.htm","imageed4.htm","imageed5.htm","imageed6.htm","imageed7.htm","imageed8.htm","imageed9.htm","imagee10.htm","imagee11.htm","imagee12.htm","imagee13.htm","imagee14.htm","imagee15.htm","imagee16.htm","imagee17.htm","imagee18.htm","imagee19.htm","imagee20.htm","imagee21.htm","imagee22.htm","imagee23.htm","imagee24.htm","imagee25.htm","imagee26.htm","imagee27.htm","imagee28.htm","imagee29.htm","imagee30.htm","imagee31.htm","imagee32.htm","imagee33.htm","imagee34.htm","imagee35.htm","imagee36.htm","imagee37.htm","imagee38.htm","imagee39.htm","imagee40.htm","imagee41.htm","imagee42.htm","tableoft.htm","tableof2.htm","tableof3.htm","tableof4.htm","tableof5.htm","tableof6.htm","tableof7.htm","tableof8.htm","pictures.htm","picture2.htm","picture3.htm","picture4.htm","picture5.htm","picture6.htm","picture7.htm","picture8.htm","picture9.htm","pictur10.htm","pictur11.htm","pictur12.htm","pictur13.htm","pictur14.htm","pictur15.htm","contexts.htm","copya.htm","spellchb.htm","spellch2.htm","spellch3.htm","spellch4.htm","spellch5.htm","spellch6.htm","samplewe.htm","samplew2.htm","samplew3.htm","finda.htm","find2.htm","find3.htm","find4.htm","find5.htm","find6.htm","find7.htm","msworda.htm","msword2.htm","msword3.htm","hyperlib.htm","hyperli2.htm","hyperli3.htm","hyperli4.htm","hyperli5.htm","hyperli6.htm","hyperli7.htm","hyperli8.htm","hyperli9.htm","508.htm","tables2.htm","tables3.htm","tables4.htm","cellprop.htm","cellpro2.htm","cellpro3.htm","cellpro4.htm","cellpro5.htm","cellpro6.htm","cellpro7.htm","cellpro8.htm","cellpro9.htm","cellpr10.htm","cellpr11.htm","cellpr12.htm","cellpr13.htm","cellpr14.htm","cellpr15.htm","cellpr16.htm","cellpr17.htm","manipula.htm","manipul2.htm","manipul3.htm","manipul4.htm","manipul5.htm","manipul6.htm","manipul7.htm","manipul8.htm","manipul9.htm","manipu10.htm","manipu11.htm","manipu12.htm","5082.htm","5083.htm","5084.htm");
  else if (ParamSearchWord == "designed")
    Results = new Array("introduc.htm");
  else if (ParamSearchWord == "publish")
    Results = new Array("introduc.htm","msword2.htm");
  else if (ParamSearchWord == "size")
    Results = new Array("introduc.htm","dialogb2.htm","dialogb3.htm","dialogb5.htm","introtou.htm","html2.htm","imageed4.htm","imagee15.htm","imagee17.htm","imagee20.htm","imagee22.htm","imagee25.htm","imagee26.htm","imagee28.htm","imagee34.htm","imagee35.htm","tableof4.htm","tableof8.htm","picture3.htm","picture4.htm","picture7.htm","samplew3.htm","tables2.htm","cellpro2.htm","cellpr14.htm","manipula.htm","manipul2.htm","manipul3.htm","manipul5.htm","manipu10.htm","manipu12.htm");
  else if (ParamSearchWord == "color")
    Results = new Array("introduc.htm","dialogb3.htm","dialogb4.htm","html2.htm","bookmara.htm","imageedi.htm","imageed4.htm","imageed6.htm","imageed7.htm","imageed8.htm","imageed9.htm","imagee12.htm","imagee17.htm","imagee19.htm","imagee20.htm","imagee22.htm","imagee25.htm","imagee26.htm","imagee34.htm","imagee42.htm","tableof4.htm","picture8.htm","samplew3.htm","hyperlib.htm","tables2.htm","cellprop.htm","cellpro5.htm","cellpro6.htm","cellpro8.htm","manipula.htm","manipul7.htm","manipul8.htm","manipu10.htm","manipu11.htm");
  else if (ParamSearchWord == "top")
    Results = new Array("introduc.htm","bookmara.htm","bookmar3.htm","imageed4.htm","imagee39.htm","tableoft.htm","picture3.htm","picture4.htm","picture9.htm","spellch4.htm","find5.htm","hyperlib.htm","cellpro7.htm","cellpro8.htm","cellpr13.htm","manipul8.htm","manipul9.htm","manipu11.htm","5084.htm");
  else if (ParamSearchWord == "includes")
    Results = new Array("bookmara.htm","picture5.htm","samplew2.htm","msword3.htm","hyperlib.htm");
  else if (ParamSearchWord == "frame")
    Results = new Array("bookmar2.htm","bookmar3.htm","hyperli3.htm","hyperli4.htm","hyperli7.htm");
  else if (ParamSearchWord == "wif50a.jpg")
    Results = new Array("imageed3.htm");
  else if (ParamSearchWord == "usually")
    Results = new Array("imagee36.htm","manipul3.htm");
  else if (ParamSearchWord == "license")
    Results = new Array("tableof4.htm");
  else if (ParamSearchWord == "backward")
    Results = new Array("tableof5.htm");
  else if (ParamSearchWord == "substantially")
    Results = new Array("picture6.htm");
  else if (ParamSearchWord == "it's")
    Results = new Array("copya.htm");
  else if (ParamSearchWord == "document")
    Results = new Array("spellch5.htm","msworda.htm","msword2.htm","msword3.htm");
  else if (ParamSearchWord == "choice")
    Results = new Array("cellpro7.htm","cellpr12.htm","cellpr13.htm","manipul9.htm");
  else if (ParamSearchWord == "justified")
    Results = new Array("cellpr12.htm");
  else if (ParamSearchWord == "compliant")
    Results = new Array("5084.htm");
  else if (ParamSearchWord == "lets")
    Results = new Array("introduc.htm","dialogb2.htm","dialogb3.htm","dialogb4.htm","dialogb5.htm","html5.htm","imageedi.htm","imageed7.htm","imageed8.htm","imagee12.htm","imagee17.htm","imagee29.htm","imagee36.htm","tableof4.htm","tableof5.htm","tableof7.htm","tableof8.htm","picture2.htm","picture6.htm","contexts.htm","find4.htm","cellprop.htm");
  else if (ParamSearchWord == "clicking")
    Results = new Array("dialogb2.htm","tableof5.htm","tableof7.htm","tableof8.htm");
  else if (ParamSearchWord == "combines")
    Results = new Array("dialogb2.htm","dialogb5.htm");

  return Results;
}
