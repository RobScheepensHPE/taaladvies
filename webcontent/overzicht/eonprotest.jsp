<html>

  <head>

   <title>edit-on Pro 4 integration sample</title>

 <script type="text/javascript" language="javascript" src="eopro/editonpro.js"></script>

  ....

 <script type="text/javascript" language="javascript">

            //-------------------------------------------------

            // Applet settings

            //-------------------------------------------------

            //Basic Settings

            eop = new editOnPro(898, 400, "myEditor", "myId", "eop");

            eop.setCodebase("eopro");

            eop.setUIConfigURL("toolbar-taaladvies.xml");

            eop.setConfigURL("config-taaladvies.xml");

            eop.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");

  </script>



</head>

<body>

<h1>RealObjects edit-on Pro 4 integration sample page</h1>



 <script type="text/javascript" language="javascript">

  // the editor will be inserted here:

  eop.loadEditor();

</script>

...

<p>Please also review the readme.txt file.</p>

</body>

</html> 