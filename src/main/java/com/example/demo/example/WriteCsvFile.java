package com.example.demo.example;

import com.example.demo.csv.WriteCsvFileHelper;
import com.example.demo.file.ZipHelper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;

@Service
public class WriteCsvFile {

  @PostConstruct
  void init() throws IOException {
    String csvFileDir = "src/main/resources/mycsv/";
    String fileName = "myFile.csv";
    String fileName2 = "myFile2.csv";
    String zipFileDir = "src/main/resources/zip/";
    String zipFileName = "myZipFile.zip";

    List<String[]> dataLines = new ArrayList<>();

    dataLines.add(new String[] { "John", "Doe", "38", "Comment Data\nAnother line of comment data" });
    dataLines.add(new String[] { "Jane", "Doe, Jr.", "19", "She said \"I'm being quoted\"" });

    WriteCsvFileHelper.writeCsvFile(dataLines, csvFileDir, fileName);
    WriteCsvFileHelper.writeCsvFile(dataLines, csvFileDir, fileName2);

    ZipHelper.zip(zipFileDir, zipFileName, csvFileDir, new String[]{fileName, fileName2});

//    delete unused files
    //todo
  }
}
