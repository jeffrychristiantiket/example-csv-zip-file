package com.example.demo.csv;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WriteCsvFileHelper {

  public static String convertToCSV(String[] data, String delimiter) {
    return Stream.of(data)
        .map(WriteCsvFileHelper::escapeSpecialCharacters)
        .collect(Collectors.joining(delimiter));
  }

  public static String escapeSpecialCharacters(String data) {
    String escapedData = data.replaceAll("\\R", " ");
    if (data.contains(",") || data.contains("\"") || data.contains("'")) {
      data = data.replace("\"", "\"\"");
      escapedData = "\"" + data + "\"";
    }
    return escapedData;
  }

  public static void writeCsvFile(List<String[]> dataLines, String csvFileDir, String fileName) {
    //create dir
    File dir = new File(csvFileDir);
    if (!dir.exists()) {
      dir.mkdir();
    }
    //write file
    File csvOutputFile = new File(csvFileDir + fileName);
    try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
      dataLines.stream()
          .map(data -> WriteCsvFileHelper.convertToCSV(data, ";"))
          .forEach(pw::println);
    } catch (FileNotFoundException e) {
      System.out.println(e.getLocalizedMessage());
    }
  }
}
