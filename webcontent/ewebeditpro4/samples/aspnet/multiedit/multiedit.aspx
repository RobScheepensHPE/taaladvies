<%@ Register TagPrefix="ewepnet" Namespace="eWebEditProNet" Assembly="eWebEditProNet" %>
<%@ Page language="c#" Codebehind="multiedit.aspx.cs" AutoEventWireup="false" Inherits="multiedit.WebForm1" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN" >
<HTML xmlns:ewepnet="urn:eWebEditProNet">
	<HEAD>
		<title>eWebEditPro MultiEdit Sample</title>
		<meta name="GENERATOR" Content="Microsoft Visual Studio 7.0">
		<meta name="CODE_LANGUAGE" Content="C#">
		<meta name="vs_defaultClientScript" content="JavaScript">
		<meta name="vs_targetSchema" content="http://schemas.microsoft.com/intellisense/ie5">
	</HEAD>
	<body>
		<form id="Form1" method="post" runat="server">
			Area 1:<br>
			<ewepnet:eWebEditProControl id="TextHTML1" runat="server" height="250" width="100%">
				<Fields>
					<ewepnet:eWebEditProField GetContentType="htmlbody" Name="TextHTML" SetContentType="htmlbody"></ewepnet:eWebEditProField>
					<ewepnet:eWebEditProField GetContentType="text" Name="PlainText"></ewepnet:eWebEditProField>
				</Fields>
			</ewepnet:eWebEditProControl>
			<br>
			<hr>
			Area 2:<br>
			<ewepnet:eWebEditProControl id="TextHTML2" runat="server" height="250" width="750"></ewepnet:eWebEditProControl>
			<br>
			<center><input type="submit" name="btnSubmit" value="Preview"></center>
			<hr>
			Preview
			<br>
			<hr>
			<asp:Label id="PreviewArea1" runat="server" />
			<br>
			<hr>
			<asp:Panel id="PlainTextPanel1" runat="server" Visible="False">
				<asp:Label id="PreviewAreaPlainText1" runat="server"></asp:Label>
				<BR>
				<HR>
			</asp:Panel>
			<br>
			<asp:Label id="PreviewArea2" runat="server" />
			<br>
		</form>
	</body>
</HTML>
