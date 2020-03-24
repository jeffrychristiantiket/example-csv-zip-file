package com.example.demo.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipHelper {

  public static void zip(String zipFilePath, String zipFileName, String csvFileDir, String[] filePaths) throws IOException {
    File dir = new File(zipFilePath);
    if (!dir.exists()) {
      dir.mkdir();
    }
    final FileOutputStream fos = new FileOutputStream(zipFilePath + zipFileName);
    final ZipOutputStream zipOut = new ZipOutputStream(fos);
    for (final String srcFile : filePaths) {
      final File fileToZip = new File(csvFileDir + srcFile);
      final FileInputStream fis = new FileInputStream(fileToZip);
      final ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
      zipOut.putNextEntry(zipEntry);
      final byte[] bytes = new byte[1024];
      int length;
      while ((length = fis.read(bytes)) >= 0) {
        zipOut.write(bytes, 0, length);
      }
      fis.close();
    }
    zipOut.close();
    fos.close();
  }
}
