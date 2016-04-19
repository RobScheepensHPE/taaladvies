<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<?xml version="1.0" encoding="iso-8859-1"?>
<config product="eWebEditPro">
	<!-- Valid positive values are:  yes, true, 1 -->
	<!-- Valid negative values are:  no, false, 0 -->
	<interface name="standard" allowCustomize="false">
		<menu name="editbar" newRow="false" showButtonsCaptions="false" wrap="true">
			<caption localeRef="mnuEdit"/>
			<button command="cmdcut"/>
			<button command="cmdcopy"/>
			<button command="cmdpaste"/>
			<button command="cmdfindreplace"/>
			<bar/>
			<button command="cmdundo"/>
			<button command="cmdredo"/>
			<bar/>
			<button command="cmdheaderlevel"/>
			<button command="cmdfontname"/>
			<button command="cmdfontsize"/>
			<bar/>
			<button command="cmdbold"/>
			<button command="cmditalic"/>
			<button command="cmdunderline"/>
			<bar/>
			<button command="cmdtable" popup="tablepopup"/>
			<bar/>
			<button command="cmdbullets"/>
			<button command="cmdnumbered"/>			
			<button command="cmdindentleft"/>
			<button command="cmdindentright"/>
			<bar/>
			<button command="cmdspellcheck"/>
			<button command="cmdspellayt"/>
			<button command="cmdextchars" popup="extcharspopup"/>
			<button command="cmdchr"/>
			<bar/>
			<button command="cmdmsword"/>
			<button command="sjwa"/>
			<button command="cmdabout"/>
			<bar/>
		</menu>		
		
		<button command="jshyperlink"/>
		<button command="cmdopen"/>
		<button command="cmdsaveas"/>
		<button command="cmdstrike"/>
		<button command="cmddelete"/>
		<button command="cmdnojustify"/>
		<button command="cmdselectall"/>
		<button command="cmdselectnone"/>
		<button command="cmdshowborders"/>
		<button command="cmdshowdetails"/>
		<button command="cmdviewasproperties"/>			
		<button command="cmdprop"/>
		<button command="cmdmfueditimage"/> <!-- only available if installed -->
		<popup name="extcharspopup">
			<button command="cmdchr149"/>
			<button command="cmdchr150"/>
			<button command="cmdchr151"/>
			<button command="cmdchr134"/>
			<button command="cmdchr135"/>
			<button command="cmdchr131"/>
			<button command="cmdchr133"/>
			<button command="cmdchr137"/>
			<button command="cmdchr128"/>
			<button command="cmdchr130"/>
			<button command="cmdchr132"/>
			<button command="cmdchr145"/>
			<button command="cmdchr146"/>
			<button command="cmdchr147"/>
			<button command="cmdchr148"/>
			<button command="cmdchr139"/>
			<button command="cmdchr155"/>
			<button command="cmdchr156"/>
			<button command="cmdchr140"/>
			<button command="cmdchr154"/>
			<button command="cmdchr138"/>
			<button command="cmdchr158"/>
			<button command="cmdchr142"/>
			<button command="cmdchr159"/>
		</popup>
		
		<popup name="tablepopup">
			<button command="cmdinserttable"/>
			<button command="cmdappendrow"/>
			<button command="cmdappendcolumn"/>
			<button command="cmdinsertrow"/>
			<button command="cmdinsertcolumn"/>
			<button command="cmdinsertcell"/>
			<button command="cmddeleterows"/>
			<button command="cmddeletecolumns"/>
			<button command="cmddeletecells"/>
			<button command="cmdmergecells"/>
			<button command="cmdsplitcell"/>
			<button command="cmdtableproperties"/>
			<button command="cmdcellproperties"/>
		</popup>
		
	</interface>
	<%@ include file="/ewebeditproConfig/general.jsp" %>
</config>