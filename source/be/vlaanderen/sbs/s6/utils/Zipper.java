package be.vlaanderen.sbs.s6.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zipper {

	public static void createZip(String zipfilename, String[] contents) {
		try {
			ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipfilename));

			for (int i = 0; i < contents.length; i++) {
				ZipEntry ze = new ZipEntry(contents[i]);
				zos.setMethod(ZipOutputStream.DEFLATED);
				zos.setLevel(5);
				zos.putNextEntry(ze);

				File fin = new File(contents[i]);

				FileInputStream ins = new FileInputStream(fin);
				int bread;
				byte[] bin = new byte[4096];

				while ((bread = ins.read(bin, 0, 4096)) != -1) {
					zos.write(bin, 0, bread);
				}

				zos.closeEntry();
			}
			zos.close();
		} catch (IOException x) {
			x.printStackTrace(System.err);
		}

	}

}