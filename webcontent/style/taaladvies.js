// Taaladvies Javascript Bib

function test()   //test gebruikt in admin/herkomstenbeheer.jsp
{
//document.MediaBeheerForm.reset();
b=document.HerkomstenBeheerForm.elements["herkomst.omschrijving"];
alert(b);alert(b.value);
}

function leegmaken()
{
//document.MediaBeheerForm.reset();
document.HerkomstenBeheerForm.elements["herkomst.omschrijving"].value="";
document.HerkomstenBeheerForm.elements["herkomst.actief"].checked=false;
//document.MediaBeheerForm.elements["medium.id"].value="";
}

function selecteer()   //selecteer gebruikt in admin/mediabeheer.jsp
{
//document.MediaBeheerForm.reset();
//b=document.MediaBeheerForm.elements["medium.omschrijving"];
//alert(b);alert(b.value);
a = document.forms[0].elements[0].options[document.forms[0].elements[0].selectedIndex].value;
b=window.location.href;
if (b.indexOf('?id=') != 0){b=b.substring(0,b.lastIndexOf('?id='));}
window.location.href = b +  '?id=' + a;
}

function eWebEditProExecCommand(sEditorName, strCmdName, strTextData, lData)
{
	if("sjwa" == strCmdName)
	{
		eWebEditPro.instances[sEditorName].editor.pasteHTML("&#601;");
	}
	return (true) ;
}