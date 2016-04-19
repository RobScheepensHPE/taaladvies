<?php
// Ektron, Inc. Copyright 2001-2004
	//
	//	File: ewebeditpro.php
	//

	// Insert the necessary Javascript include files for calling the editor
	// Pass the time to insure that the js lib is never servered from browser cache
	$randnum = time();
	echo "<script language=\"JavaScript1.2\" src=\"/ewebeditpro4/ewebeditpro.js?$randnum\"></script>\n";

	// Javascript for calling popup windows
	echo "<script language=\"JavaScript1.2\">\n";
	echo "	function PopUpWindow (url, hWind, nHeight, nWidth, nScroll, nResize) {\n";
	echo "	var cToolBar = \"toolbar=0,location=0,directories=0,status=0,menubar=0,scrollbars=\" + nScroll + \",resizable=0,width=\" + nWidth + \",height=\" + nHeight;\n";
	echo "		var popupwin = window.open(url, hWind, cToolBar);\n";
	echo "		if (null == popupwin)\n";
	echo "		{\n";
	echo "			alert(\"Unable to open pop-up window.  Please turn off your pop-up blocker and try again.\")\n";
	echo " 		}\n";
	echo "	}\n";
	echo "</script>\n";

	// Function: eWebEditProField
	// Purpose:  Insert the necessary JavaScript to define a content field for the editor
	// Parameters:
	//		EditorName:		(String) The name of the editor associated with the content
	//		FieldName:		(String) The name of the hidden field that will be passed in the post
	//		SetType:		(String) The type of the content to load into the editor
	//		GetType:		(String) The type of the content to get from the editor
	//		ContentHTML:	(String) The HTML being loaded into the editor
	function eWebEditProField( $EditorName, $FieldName, $SetType, $GetType, $ContentHtml, $CharacterSet="ISO-8859-1" ) {
		if($EditorName != $FieldName) {
			$strContentHTML = htmlentities ($ContentHtml,ENT_COMPAT, $CharacterSet);
			echo "<input type=\"hidden\" name=\"$FieldName\" value=\"$strContentHTML\">\n";
		}
		echo "<script language=\"JavaScript1.2\" type=\"text/javascript\">\n";
		echo "eWebEditPro.defineField(\"$EditorName\", \"$FieldName\", \"$SetType\", \"$GetType\" );\n";
		echo "</script>\n";
	}

	// Function: eWebEditProEditor
	// Purpose:  Insert the necessary Javascript to call the editor
	// Parameters:
	//		FieldName:		(String) The name of the hidden field that will be passed in the post
	//		Width:			(String) The physical width the editor in the content form
	//								 may be a number of percentage
	//		Height:			(String) The physical height the editor in the content form
	//								 may be a number of percentage
	//		ContentHTML:	(String) The HTML being loaded into the editor
	function eWebEditProEditor ( $FieldName, $Height, $Width, $ContentHtml,$CharacterSet = "ISO-8859-1" ) {
		$strContentHTML = htmlentities ($ContentHtml,ENT_COMPAT, $CharacterSet);
		echo "
			<input type=\"hidden\" name=\"$FieldName\" value=\"$strContentHTML\">	
			<script language=\"JavaScript1.2\">
				eWebEditPro.create(\"$FieldName\", \"$Width\", \"$Height\");
			</script>
		\n";
	}

	// Function: eWebEditProPopupButton
	// Purpose:  Insert the necessary HTML to call the popup version of the editor
	// Parameters:
	//		ButtonName:		(String) The name of the hidden field that will be passed in the post
	//		FieldName:		(String) The physical width the editor in the content form
	//								 may be a number of percentage
	function eWebEditProPopupButton  ( $ButtonName, $FieldName ) {
		echo "<script language=\"JavaScript1.2\">\n";
		echo "		eWebEditPro.createButton(\"$ButtonName\", \"$FieldName\")\n";
		echo "	</script>\n";
	}
?>
