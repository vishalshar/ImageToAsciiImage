ImageToAsciiImage
=================

Project aims at converting a JPEG image to an ASCII Image. A JPEG Image is converted to a PPM then PPM is used to convert it into ASCII image.


What is a PPM Image ?
The first line is always “P3” which indicates the pixel values are in ASCII format (instead of binary). After
that there can be an optional comment line, which must start with a ‘#’. Next are three numbers: width,
height, and maximum intensity value. After that are the RGB values for each pixel starting from (0,0).
