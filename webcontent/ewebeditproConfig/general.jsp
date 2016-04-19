<features>
		<!-- Possible command styles are:
                Icon Button:   "default", "icon", not specified
                Icon Toggle:   "toggle"
                Text Listbox:  "list", "listbox"
                Text Editbox:  "edit", "text"
     	-->
		<external>
			<command name="jstm">
				<caption localeRef="btnCapTM"/>
				<tooltiptext localeRef="cmdTM"/>
			</command>
			<cmd name="jshyperlink" key="hyperlinkstar" ref="cmdNewHyp" />
			<cmd name="sjwa" ref ="cmdSjwa" />
			<!-- Place custom commands to be processed by JavaScript here. -->
		</external>
		<!-- values for charencode: utf-8, binary, entityname, charref, special, latin -->
		<clean charencode="charref" cr="cr" lf="lf" showonsize="5000" preferfonttag="false" reducetags="true" showdonemsg="true" prompt="true" hideobject="true" mswordfilter="true">
			<remove>
				<tagWoAttr>SPAN</tagWoAttr>
			</remove>
		</clean>
		<formelements>
			<cmd name="cmdformform" key="form" ref="frmForm" />
			<cmd name="cmdformradio" key="optionbox" ref="frmOptionBox" />
			<cmd name="cmdformcheckbox" key="checkbox" ref="frmCheckbox" />
			<cmd name="cmdformbutton" key="bbtn" ref="frmBBtn" />
			<cmd name="cmdformsubmit" key="sbtn" ref="frmSBtn" />
			<cmd name="cmdformreset" key="rbtn" ref="frmRBtn" />
			<cmd name="cmdformhidden" key="hiddenfld" ref="frmHiddenFld" />
			<cmd name="cmdformtext" key="textfld" ref="frmTextFld" />
			<cmd name="cmdformpassword" key="pwdfld" ref="frmPasswordFld" />
			<cmd name="cmdformtextarea" key="textbox" ref="frmTextarea" />
			<cmd name="cmdformfile" key="fileup" ref="frmFormFile" />
			<cmd name="cmdformselect" key="droplist" ref="frmDropList" />
		</formelements>
		<!-- publish options are "cleanhtml" and "xhtml" -->
		<standard mode="htmlbody" autoclean="true" publish="xhtml" publishinvalid="false" publishviewassource="true" continueparagraph="false">
			<!-- equivClass options are "strict", "loose" and "all" -->
			<style publishstyles="false" href="[eWebEditProPath]/ektnormal.css" equivClass="strict" wrapstylewithdiv="false" preservewordstyles="false" preservewordclasses="true">
				
			</style>			
			<cmd name="cmdprint" key="print" ref="sPrint"/>
			<cmd name="cmdbackcolor" key="bgcolor" ref="cmdBC"/>
			<command name="cmdbackcolorvalue" style="list">
				<caption localeRef="cmdBCVal"/>
				<tooltiptext localeRef="cmdBCVal"/>
				<selections name="backcolorlist">
					<listchoice command="cmdbackcolor" localeRef="colorPal"/>
					<listchoice data="-1" localeRef="sTransparent"/>
					<listchoice data="#00FFFF" localeRef="colorAqua"/>
					<listchoice data="#000000" localeRef="colorBlack"/>
					<listchoice data="#0000FF" localeRef="colorBlue"/>
					<listchoice data="#FF00FF" localeRef="colorFuchsia"/>
					<listchoice data="#808080" localeRef="colorGray"/>
					<listchoice data="#008000" localeRef="colorGreen"/>
					<listchoice data="#00FF00" localeRef="colorLime"/>
					<listchoice data="#800000" localeRef="colorMaroon"/>
					<listchoice data="#000080" localeRef="colorNavy"/>
					<listchoice data="#808000" localeRef="colorOlive"/>
					<listchoice data="#800080" localeRef="colorPurple"/>
					<listchoice data="#FF0000" localeRef="colorRed"/>
					<listchoice data="#C0C0C0" localeRef="colorSilver"/>
					<listchoice data="#008080" localeRef="colorTeal"/>
					<listchoice data="#FFFF00" localeRef="colorYellow"/>
					<listchoice data="#FFFFFF" localeRef="colorWhite"/>
				</selections>
			</command>
			<cmd name="cmdfontcolor" key="fontcolor" ref="cmdFC"/>
			<command name="cmdfontcolorvalue" style="list">
				<caption localeRef="cmdFCVal"/>
				<tooltiptext localeRef="cmdFCVal"/>
				<selections name="fontcolorlist">
					<listchoice command="cmdfontcolor" localeRef="colorPal"/>
					<listchoice data="#00FFFF" localeRef="colorAqua"/>
					<listchoice data="#000000" localeRef="colorBlack"/>
					<listchoice data="#0000FF" localeRef="colorBlue"/>
					<listchoice data="#FF00FF" localeRef="colorFuchsia"/>
					<listchoice data="#808080" localeRef="colorGray"/>
					<listchoice data="#008000" localeRef="colorGreen"/>
					<listchoice data="#00FF00" localeRef="colorLime"/>
					<listchoice data="#800000" localeRef="colorMaroon"/>
					<listchoice data="#000080" localeRef="colorNavy"/>
					<listchoice data="#808000" localeRef="colorOlive"/>
					<listchoice data="#800080" localeRef="colorPurple"/>
					<listchoice data="#FF0000" localeRef="colorRed"/>
					<listchoice data="#C0C0C0" localeRef="colorSilver"/>
					<listchoice data="#008080" localeRef="colorTeal"/>
					<listchoice data="#FFFF00" localeRef="colorYellow"/>
					<listchoice data="#FFFFFF" localeRef="colorWhite"/>
				</selections>
			</command>
			<command name="cmdfontname" style="list" maxwidth="12">
				<caption localeRef="cmdFNm"/>
				<tooltiptext localeRef="cmdFNm"/>
				<selections name="fontnamelist" sorted="true">
					<listchoice>Arial, Helvetica</listchoice>
					<listchoice>Comic Sans MS</listchoice>
					<listchoice>Courier New, Courier</listchoice>
					<listchoice>Symbol</listchoice>
					<listchoice>Times New Roman, Times</listchoice>
					<listchoice>Verdana, Helvetica</listchoice>
				</selections>
			</command>
			<command name="cmdfontsize" style="list" maxwidth="6">
				<caption localeRef="cmdFS"/>
				<tooltiptext localeRef="cmdFS"/>
				<!-- There can only be seven selections for sizes -->
				<selections name="fontsizelist">
					<listchoice command="cmdfontsize1" localeRef="mnuFS1"/>
					<listchoice command="cmdfontsize2" localeRef="mnuFS2"/>
					<listchoice command="cmdfontsize3" localeRef="mnuFS3"/>
					<listchoice command="cmdfontsize4" localeRef="mnuFS4"/>
					<listchoice command="cmdfontsize5" localeRef="mnuFS5"/>
					<listchoice command="cmdfontsize6" localeRef="mnuFS6"/>
					<listchoice command="cmdfontsize7" localeRef="mnuFS7"/>
				</selections>
			</command>
			<command name="cmdselstyle" style="list">
				<caption localeRef="cmdStyle"/>
				<tooltiptext localeRef="cmdStyle"/>
				<selections name="stylelist" sorted="true"/>
			</command>
			<cmd name="cmdunstyle" key="removestyle" ref="cmdUnSty"/>
			<command name="cmdheaderlevel" style="list">
				<caption localeRef="btnCapHdg"/>
				<tooltiptext localeRef="cmdHdg"/>
				<selections name="headinglist">
					<listchoice command="cmdheadingstd" localeRef="txtHdgNorm"/>
					<listchoice command="cmdheading1" localeRef="txtHdg1"/>
					<listchoice command="cmdheading2" localeRef="txtHdg2"/>
					<listchoice command="cmdheading3" localeRef="txtHdg3"/>
					<listchoice command="cmdheading4" localeRef="txtHdg4"/>
					<listchoice command="cmdheading5" localeRef="txtHdg5"/>
					<listchoice command="cmdheading6" localeRef="txtHdg6"/>
					<listchoice command="cmdaddress" localeRef="cmdAddr"/>
				</selections>
			</command>
			<cmd name="cmdabout" key="about" ref="cmdAbt"/>
			<cmd name="cmdopen" key="open" ref="cmdOpen"/>
			<cmd name="cmdsaveas" key="save" ref="cmdSave"/>
			<cmd name="cmdcut" key="cut" ref="cmdCut"/>
			<cmd name="cmdcopy" key="copy" ref="cmdCp"/>
			<cmd name="cmddelete" key="delete" ref="cmdDel"/>
			<cmd name="cmdpaste" key="paste" ref="cmdPas"/>
			<cmd name="cmdpastetext" key="pastetext" ref="cmdPasTxt"/>
			<cmd name="cmdundo" key="undo" ref="cmdUndo"/>
			<cmd name="cmdredo" key="redo" ref="cmdRedo"/>
			<cmd name="cmdfind" key="find" ref="cmdFind"/>
			<cmd name="cmdfindreplace" key="find" ref="cmdReplace"/>
			<cmd name="cmdfindnext" key="findnext" ref="cmdFindNext"/>
			<cmd name="cmdbold" key="bold" ref="cmdBold" style="toggle"/>
			<cmd name="cmditalic" key="italic" ref="cmdItal" style="toggle"/>
			<cmd name="cmdunderline" key="underline" ref="cmdUndln" style="toggle"/>
			<cmd name="cmdremoveformat" key="plain" ref="sNormal"/>
			<command name="cmdnumbered" style="toggle">
				<image key="numbered"/>
				<caption localeRef="btnCapNumL"/>
				<tooltiptext localeRef="cmdNumL"/>
			</command>
			<command name="cmdbullets" style="toggle">
				<image key="bullets"/>
				<caption localeRef="btnCapBul"/>
				<tooltiptext localeRef="cmdBul"/>
			</command>
			<command name="cmdindentleft">
				<image key="indentleft"/>
				<caption localeRef="btnCapIndl"/>
				<tooltiptext localeRef="cmdIndl"/>
			</command>
			<command name="cmdindentright">
				<image key="indentright"/>
				<caption localeRef="btnCapIndr"/>
				<tooltiptext localeRef="cmdIndr"/>
			</command>
			<command name="cmdleft" style="toggle">
				<image key="left"/>
				<caption localeRef="btnCapAL"/>
				<tooltiptext localeRef="cmdAL"/>
			</command>
			<command name="cmdcenter" style="toggle">
				<image key="center"/>
				<caption localeRef="btnCapAC"/>
				<tooltiptext localeRef="cmdAC"/>
			</command>
			<command name="cmdright" style="toggle">
				<image key="right"/>
				<caption localeRef="btnCapAR"/>
				<tooltiptext localeRef="cmdAR"/>
			</command>
			<cmd name="cmdjustify" key="justify" ref="sJustify" style="toggle"/>
			<cmd name="cmdnojustify" key="nojustify" ref="cmdANo" style="toggle"/>
			<cmd name="cmdselectnone" key="selectnone" ref="cmdSelNo"/>
			<cmd name="cmdselectall" key="selectall" ref="cmdSelA"/>
			<cmd name="cmdbookmark" key="bookmark" ref="cmdBkm"/>
			<command name="cmdhyperlink">
				<image key="hyperlink"/>
				<caption localeRef="cmdHyp"/>
				<tooltiptext localeRef="cmdHyp"/>
			</command>
			<cmd name="cmdunlink" key="removelink" ref="cmdUnlnk"/>
			<cmd name="cmdstrike" key="strikethrough" ref="cmdStrike" style="toggle"/>
			<cmd name="cmdsub" key="subscript" ref="cmdSub" style="toggle"/>
			<cmd name="cmdsup" key="superscript" ref="cmdSup" style="toggle"/>
			<command name="cmdhr">
				<image key="horzrule"/>
				<caption localeRef="btnCapHR"/>
				<tooltiptext localeRef="cmdHR"/>
			</command>
			<command name="cmdclean">
				<image key="clean"/>
				<caption localeRef="btnCapClean"/>
				<tooltiptext localeRef="cmdClean"/>
			</command>
			<command name="cmdshowborders" style="toggle">
				<image key="borders"/>
				<caption localeRef="btnCapShBord"/>
				<tooltiptext localeRef="cmdShBord"/>
			</command>
			<command name="cmdshowdetails" style="toggle">
				<image key="glyphs"/>
				<caption localeRef="btnCapGlyph"/>
				<tooltiptext localeRef="cmdGlyph"/>
			</command>
			<cmd name="cmdprop" key="properties" ref="sProp"/>
			<cmd name="cmdabspos" key="abspos" style="toggle" ref="cmdAbsPos"/>
			<cmd name="cmdlock" key="lock" style="toggle" ref="sLock"/>
			<cmd name="cmdzfront" key="front" ref="cmdZF"/>
			<cmd name="cmdzback" key="back" ref="cmdZB"/>
			<cmd name="cmdzforward" key="forward" ref="cmdZFw"/>
			<cmd name="cmdzbackward" key="backward" ref="cmdZBw"/>
			<cmd name="cmdzabovetext" key="abovetext" ref="cmdZAT"/>
			<cmd name="cmdzbelowtext" key="belowtext" ref="cmdZBT"/>
			<cmd name="cmdzorder" key="zordermenu" ref="sMove"/>
			<cmd name="cmdltrblk" key="ltrblock" style="toggle" ref="cmdLRB"/>
			<cmd name="cmdrtlblk" key="rtlblock" style="toggle" ref="cmdRLB"/>
			<cmd name="cmdltredit" key="ltredit" style="toggle" ref="cmdLRE"/>
			<cmd name="cmdrtledit" key="rtledit" style="toggle" ref="cmdRLE"/>
			<command name="cmdchr160">
				<caption localeRef="btnCapnbsp"/>
				<tooltiptext localeRef="cmdnbsp"/>
			</command>
			<command name="cmdchr169">
				<caption>&#169;</caption>
				<tooltiptext localeRef="cmdC169"/>
			</command>
			<command name="cmdchr174">
				<caption>&#174;</caption>
				<tooltiptext localeRef="cmdC174"/>
			</command>
			<command name="cmdchr" style="list" maxwidth="16">
				<caption localeRef="cmdMore"/>
				<tooltiptext localeRef="cmdMore"/>
				<selections name="chrlist">
					<listchoice delimited="|">&#161;|&#8706;|&#162;|&#163;|&#164;|&#165;</listchoice>
					<listchoice delimited="|">&#167;|&#168;|&#169;|&#170;|&#171;|&#172;</listchoice>
					<listchoice delimited="|">&#173;|&#174;|&#175;|&#176;|&#177;|&#178;</listchoice>
					<listchoice delimited="|">&#179;|&#180;|&#181;|&#182;|&#183;|&#184;</listchoice>
					<listchoice delimited="|">&#185;|&#186;|&#187;|&#188;|&#189;|&#190;</listchoice>
					<listchoice delimited="|">&#191;|&#192;|&#193;|&#194;|&#195;|&#196;</listchoice>
					<listchoice delimited="|">&#197;|&#198;|&#199;|&#200;|&#201;|&#202;</listchoice>
					<listchoice delimited="|">&#203;|&#204;|&#205;|&#206;|&#207;|&#208;</listchoice>
					<listchoice delimited="|">&#209;|&#210;|&#211;|&#212;|&#213;|&#214;</listchoice>
					<listchoice delimited="|">&#215;|&#216;|&#217;|&#218;|&#219;|&#220;</listchoice>
					<listchoice delimited="|">&#221;|&#222;|&#223;|&#224;|&#225;|&#226;</listchoice>
					<listchoice delimited="|">&#227;|&#228;|&#229;|&#230;|&#231;|&#232;</listchoice>
					<listchoice delimited="|">&#233;|&#234;|&#235;|&#236;|&#237;|&#238;</listchoice>
					<listchoice delimited="|">&#239;|&#240;|&#241;|&#242;|&#243;|&#244;</listchoice>
					<listchoice delimited="|">&#245;|&#246;|&#247;|&#248;|&#249;|&#250;</listchoice>
					<listchoice delimited="|">&#251;|&#252;|&#253;|&#254;|&#255;|&#166;</listchoice>
				</selections>
			</command>
			<cmd name="cmdextchars" key="charsmenu" ref="cmdExtCh"/>
			<cmd name="cmdchr128" key="euro"/>
			<cmd name="cmdchr130" key="lsquor"/>
			<cmd name="cmdchr131" key="fnof"/>
			<cmd name="cmdchr132" key="ldquor"/>
			<cmd name="cmdchr133" key="hellip"/>
			<cmd name="cmdchr134" key="dagger"/>
			<cmd name="cmdchr135" key="ddagger"/>
			<cmd name="cmdchr137" key="permil"/>
			<cmd name="cmdchr138" key="sscaron"/>
			<cmd name="cmdchr139" key="lsaquo"/>
			<cmd name="cmdchr140" key="oeoelig"/>
			<cmd name="cmdchr142" key="zzcaron"/>
			<cmd name="cmdchr145" key="lsquo"/>
			<cmd name="cmdchr146" key="rsquo"/>
			<cmd name="cmdchr147" key="ldquo"/>
			<cmd name="cmdchr148" key="rdquo"/>			
			<cmd name="cmdchr149" key="bull"/>
			<cmd name="cmdchr150" key="ndash"/>
			<cmd name="cmdchr151" key="mdash"/>
			<cmd name="cmdchr153" key="trade"/>
			<cmd name="cmdchr154" key="scaron"/>
			<cmd name="cmdchr155" key="rsaquo"/>
			<cmd name="cmdchr156" key="oelig"/>
			<cmd name="cmdchr158" key="zcaron"/>
			<cmd name="cmdchr159" key="yyuml"/>
		</standard>
		<!-- publish options are "cleanhtml" and "xhtml" -->
		<!-- mode options are "body" and "whole" -->
		<!-- view options are "wysiwyg" and "source" -->
		<viewas view="wysiwyg" publish="xhtml" mode="body" unicode="false">
			<cmd name="cmdviewaswysiwyg" key="page" ref="cmdVAW"/>
			<cmd name="cmdviewashtml" key="pagetag" ref="cmdVAH"/>
			<cmd name="cmdviewasproperties" key="viewprop" ref="cmdVAProp"/>			
		</viewas>
		<edithtml />
		<spellcheck langid="0" dictionary2="WinterTreeSC.CWinterTreeSC">
			<spellayt autostart="false" markmisspelledsrc="[eWebEditProPath]/wavyred.gif" delay="20"/>
			<spellingsuggestion max="4"/>
			<cmd name="cmdspellayt" key="spellayt" ref="cmdSplayt" style="toggle"/>
			<cmd name="cmdspellcheck" key="spellcheck" ref="cmdSplck"/>
		</spellcheck>	
		<msword>
			<cmd name="cmdmsword" key="msword" ref="cmdMSW" style="toggle"/>
		</msword>	
		<table>
			<cmd name="cmdtable" key="tablemenu" ref="cmdTbl"/>
			<cmd name="cmdinserttable" key="instable" ref="mnuITbl"/>
			<cmd name="cmdappendrow" key="addrow" ref="mnuARow"/>
			<cmd name="cmdappendcolumn" key="addcol" ref="mnuACol"/>
			<cmd name="cmdinsertrow" key="insabove" ref="mnuIRow"/>
			<cmd name="cmdinsertcolumn" key="insleft" ref="mnuICol"/>
			<cmd name="cmdinsertcell" key="inscell" ref="mnuICell"/>
			<cmd name="cmddeleterows" key="delrow" ref="mnuDRow"/>
			<cmd name="cmddeletecolumns" key="delcol" ref="mnuDCol"/>
			<cmd name="cmddeletecells" key="delcell" ref="mnuDCell"/>
			<cmd name="cmdmergecells" key="mergecell" ref="mnuMC"/>
			<cmd name="cmdsplitcell" key="splitcell" ref="mnuSC"/>
			<cmd name="cmdtableproperties" key="tableprop" ref="mnuTProp"/>
			<cmd name="cmdcellproperties" key="cellprop" ref="mnuCProp"/>
			<cmd name="cmd508table" key="table508" ref="mnu508table" />
		</table>
		<mediafiles>
			<cmd name="cmdmfuuploadcontent" key="contupload" ref="cmdcontupload" />
			<cmd name="cmdmfumedia" key="picture" ref="cmdPic" />
			<cmd name="cmdmfuuploadall" key="upload" ref="cmdUpldFiles" />
			<!-- The command below will only be enabled when the Ektron WebImageFX tool is installed. -->
			<cmd name="cmdmfueditimage" key="freehand" ref="cmdImgEdit"/>
			<!-- 0 is unlimited size -->
			<maxsizek>0</maxsizek>
			<validext>gif,jpg,png,jpeg,jpe,txt,doc</validext>
			<mediaconfig allowedit="true" />
			<!-- If this section is not defined it will default to FTP with no settings -->
			<!-- The attribute 'type' values "ftp", "file", "post", and "none" are handled within the editor. -->
			<!-- If a page is specified in the type attribute then it is used for GUI file selections. -->
			<transport allowupload="true" type="post" xfer="binary" pasv="true">
				<!-- autoupload defines the upload action when local files exist in the content. -->
				<!-- If this section is not defined it will default to ASP,  -->
				<!-- unless ASP is not available, then it defaults to FTP. -->
				<!-- The attribute 'type' values "ftp" and "none" are handled within the editor. -->
				<!-- If a page is specified in the type attribute then it is used to receive files -->
				<!-- automatically sent up by the client.  (User intervention is always required.) -->
				<!-- Set showlistonsave to false to skip the pending upload image list dialog when -->
				<!-- collecting publish content.  This value is default to TRUE (show the image list dialog). -->
				<autoupload type="[eWebEditProPath]/ewepreceive.asp" showlistonsave="false" />
				<!-- Encrypt username and password using Ektron's encrypt.exe program. -->
				<!-- Leave blank to force the user to enter the values. -->
				<username encrypted="false"></username>
				<password encrypted="false"></password>
				<!-- Set to 0 for default port number -->
				<port>0</port>
				<!-- The domain to use for upload.  This is normally used by FTP. -->
				<!-- Upload location is: [domain]+[xferdir]+[filename] -->
				<!-- e.g., ftp.mydomain.com -->
				<!-- If this is blank then the domain specified in xferdir is used. -->
				<domain></domain>
				<!-- The logical FTP/Web/other directory to transfer into. -->
				<!-- (FTP upload directories normally do not match the corresponding web directory.) -->
				<!-- svrlocaleref is the Localization for the FTP/Server folder displayed. -->
				<!-- Its string value is in the locale files. -->
				<xferdir src="[eWebEditProPath]/upload" svrlocaleref="xferDispName" />
				<!-- The directory where a file is referenced by a browser once uploaded. -->
				<!-- Referencing a file through HTTP is: [webroot]+[filename] -->
				<!-- If webroot is blank then it defaults to xferdir value. -->
				<webroot src="" />
				<!-- Possible values for resolvepath are: full, host, local, given -->
				<resolvemethod value="local" src="" resolve="true" allowoverride="true" />
			</transport>
			<imageedit>
				<control src="[WebImageFXPath]/ImageEditConfig.xml" />
			</imageedit>
		</mediafiles>
		
	</features>