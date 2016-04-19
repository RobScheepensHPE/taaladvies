<!-- registering tag name with associated control for instance of eWebEditProEditor -->
<%@ Register TagPrefix="ewep" TagName="eWebEditProEditor" src="../../../ewebeditpro.ascx" %>

<!-- registering tag name with associated control for instance of eWebEditProPopupButton -->
<%@ Register TagPrefix="ewep" TagName="eWebEditProPopupButton" src="../../../ewebeditpropopupbutton.ascx" %>

<!--  
	Copyright 2002,	Ektron Inc.  Amherst NH  
	Latest Revision: 01-Apr-02
-->

<html>
	<head>
		<title>eWebEditPro MultiEdit Sample</title>
		<script language="VB" runat="server">
		Sub Page_Load(Sender As Object, E As EventArgs)
			If (Page.IsPostBack)
				PreviewText1.Text = Text1.Text
				PreviewArea1.Text = Request.Form("TextHTML1")
				PreviewArea2.Text = Request.Form("TextHTML2")
				TextHTML1.Text = Request.Form("TextHTML1")
				TextHTML2.Text = Request.Form("TextHTML2")
			Else
				TextHTML1.Text = "<p>Initial content 1</p>"
				TextHTML2.Text = "<p>Initial content 2</p>"
			End If
		End Sub
		</script>
	</head>
	
	<body MS_POSITIONING="GridLayout">
	
		<form id="Form1" method="post" runat="server">
		
			Enter some text: <asp:textbox id="Text1" runat="server" size="80" text="Sample text"></asp:textbox>
		
			<ewep:eWebEditProPopupButton id="btnEditText1" FieldName="Text1" runat="server"></ewep:eWebEditProPopupButton>
			
			<br>
			<hr>
			Area 1:<br>
			
			<ewep:eWebEditProEditor id="TextHTML1" runat="server" height="250" width="100%"></ewep:eWebEditProEditor>
			
			<br>
			<hr>
			Area 2:<br>
			
			<ewep:eWebEditProEditor id="TextHTML2" runat="server" height="250" width="750"></ewep:eWebEditProEditor>
			
			<br><center><input type="submit" name="btnSubmit" value="Preview"></center>
			
			<hr>
			Preview
			<hr>	
			<asp:Label id="PreviewText1" runat="server" />
			<br><hr>
			<asp:Label id="PreviewArea1" runat="server" />
			<br><hr>
			<asp:Label id="PreviewArea2" runat="server" />
			<br>
		</form>
	</body>
</html>
