                          **************JSP image upload sample readme**************

WARNING*****************WARNING****************WARNING****************WARNING****************WARNING****************WARNING

You must download JSP smart upload.  This sample was built using it.   It can be downloaded from www.jspsmart.com.

***************************************************************************************************************************

This code is only sample code and has not gone through any QA so there maybe some unknown issues with it.  You may modify this code as you see fit.

It has been tested with IE5.5, IE 6, Netscape 4.7 and netscape 6.2

***************************************************************************************************************************


How it works

This sample will copy the image to the directory you specify.  It will also read the image properties such as size, height and width.  It is setup to work with our sample database so if you try using it with any other database, you will have to modify all of the queries.  For the most part, the source is fairly well commented.  The image will be stored on the file system and in the database, we will write the path to that image as well as store the image properties.

About the files

ewebeditpro.jsp
Contains the functions for creating the instance of the editor using JSP and also contains several database functions.

imagemanager.jsp
File consists of the frame layout for the upload pages

imageuploader.jsp
This file contains the upload form as well as the javascript that verifies image information.

imagesave.jsp
This page contains some JSP Smart upload code used for performing the file transfer and getting the values from the form fields.  Also contains the query for storing the image information in the database.

imagepreview.jsp
Previews the selected image

imagelist.jsp
Presents the user with the list of images from the DB.  It reads the DB to get this list and when you select an image, performs the insert into the DB.

imageinformation.jsp
Shows the properties of the image such as file size, width and height.

