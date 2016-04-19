function  WWHelpPopup_PopupTranslate(ParamText)
{
  return ParamText;
}

function  WWHelpPopup_PopupFormat(ParamText)
{
  var  FormattedText = "";
  var  BorderColor   = "#999999";
  var  ImageDir      = WWHelpTopFrame.WWHelpFrameSet.mBaseURL + "wwhelp/common/images";


  FormattedText += "<table width=\"" + this.mWidth + "\" border=0 cellspacing=0 cellpadding=0 width=\"100%\" bgcolor=\"#FFFFCC\">";
  FormattedText += " <tr>";
  FormattedText += "  <td colspan=5 bgcolor=\"" + BorderColor + "\"><img src=\"" + ImageDir + "/spc1w2h.gif\" width=1 height=2></td>";
  FormattedText += " </tr>";

  FormattedText += " <tr>";
  FormattedText += "  <td bgcolor=\"" + BorderColor + "\"><img src=\"" + ImageDir + "/spc2w1h.gif\" width=2 height=1></td>";
  FormattedText += "  <td colspan=3><img src=\"" + ImageDir + "/spc1w7h.gif\" width=1 height=7></td>";
  FormattedText += "  <td bgcolor=\"" + BorderColor + "\"><img src=\"" + ImageDir + "/spc2w1h.gif\" width=2 height=1></td>";
  FormattedText += " </tr>";

  FormattedText += " <tr>";
  FormattedText += "  <td bgcolor=\"" + BorderColor + "\"><img src=\"" + ImageDir + "/spc2w1h.gif\" width=2 height=1></td>";
  FormattedText += "  <td><img src=\"" + ImageDir + "/spc5w1h.gif\" width=5 height=1></td>";
  FormattedText += "  <td width=\"100%\" id=\"" + this.mTextID + "\">" + ParamText + "</td>";
  FormattedText += "  <td><img src=\"" + ImageDir + "/spc5w1h.gif\" width=5 height=1></td>";
  FormattedText += "  <td bgcolor=\"" + BorderColor + "\"><img src=\"" + ImageDir + "/spc2w1h.gif\" width=2 height=1></td>";
  FormattedText += " </tr>";

  FormattedText += " <tr>";
  FormattedText += "  <td bgcolor=\"" + BorderColor + "\"><img src=\"" + ImageDir + "/spc2w1h.gif\" width=2 height=1></td>";
  FormattedText += "  <td colspan=3><img src=\"" + ImageDir + "/spc1w7h.gif\" width=1 height=7></td>";
  FormattedText += "  <td bgcolor=\"" + BorderColor + "\"><img src=\"" + ImageDir + "/spc2w1h.gif\" width=2 height=1></td>";
  FormattedText += " </tr>";

  FormattedText += " <tr>";
  FormattedText += "  <td colspan=5 bgcolor=\"" + BorderColor + "\"><img src=\"" + ImageDir + "/spc1w2h.gif\" width=1 height=2></td>";
  FormattedText += " </tr>";
  FormattedText += "</table>";

  return FormattedText;
}
