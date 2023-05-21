package pl.rutkowski;

import org.w3c.dom.Document;

import java.io.*;
import java.util.*;

public class FileManager {

    private final XmlCreator xmlCreator = new XmlCreator();
    private final CsvReader csvReader = new CsvReader();


    public void convertFromCsvToXml() throws IOException {
        String csvPath = "/Users/adrian/Desktop/Csv";
        String xmlPath = "/Users/adrian/Desktop/Xml/";
        List<String> listOfXml = getXmlFilesFromDirectory(xmlPath);
        File csvFile = new File(csvPath);
        File[] matchingFile = csvFile.listFiles((dir, name) -> name.endsWith("csv"));
        List<String> filesList = new ArrayList<>();
        assert matchingFile != null;
        for (File file : matchingFile) {
            if (!listOfXml.contains(file.getName())) {
                List<Book> books = csvReader.readFile(file);
                getBooks(filesList, books, file.getName());
            }
        }

    }

    private List<String> getXmlFilesFromDirectory(String xmlPath) {
        File folder = new File(xmlPath);
        File[] listOfFiles = folder.listFiles();
        List<String> listOfXml = new ArrayList<>();
        assert listOfFiles != null;
        for (File listOfFile : listOfFiles) {
            listOfXml.add(listOfFile.getName().replace("xml", "csv"));
        }
        return listOfXml;
    }

    private void getBooks(List<String> filesList, List<Book> books, String fileName) {
        Document xml = xmlCreator.createXml(books);
        xmlCreator.saveXmlFile(xml, fileName);
        filesList.add(fileName);
    }


}
