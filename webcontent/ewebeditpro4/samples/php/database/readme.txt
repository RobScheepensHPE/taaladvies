                          **************PHP image upload sample readme**************

This code is only sample code and has not gone through any QA so there maybe some unknown issues with it.  You may modify this code as you see fit.

It has been tested with IE 6, Netscape 4.7 and netscape 6.2


How it works

This sample is just a very basic example to show you how it can be done.  When you upload an image, a reference to that image is stored in our sample database.  When it is inserted, we just read that information out of the database and call one of the editor's Media File object methods called insertMediaFile to insert the image.  So, the actual file is stored on the file system and a reference to that image is stored in the DB.  I've tried to use comments in the source code to help you better understand what is happening and how you can modify it.

The database that this sample is setup to use is our sample database.  All of the queries are hard coded in the php pages.  You may modify them if you wish. 

About the files

Globals.php
Contains the Datasource Name, image upload directory, host name and physical upload path

ewebeditprofunctions.php
Contains various functions that are called to create the editor and for uploading the image and inserting it into the DB

imagemanager.php
File consists of the frame layout for the upload pages

imageuploader.php
File that performs the upload.  This file lets you browse the file system, select a file and then when you click upload, it calls a function from ewebeditprofunctions.php to upload the file.  Also verifies image file attributes such as allowable upload extensions and file size.

imagepreview.php
Previews the selected image

imagelist.php
Presents the user with the list of images from the DB.  It reads the DB to get this list and when you select an image, performs the insert into the DB.

imageinformation.php
Shows the properties of the image such as file size, width and height.

