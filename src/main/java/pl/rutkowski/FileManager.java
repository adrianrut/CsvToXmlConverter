package pl.rutkowski;

import org.w3c.dom.Document;

import java.io.*;
import java.util.*;

public class FileManager {

    private final XmlCreator xmlCreator;
    private final CsvReader csvReader;

    public FileManager() {
        this.xmlCreator = new XmlCreator();
        this.csvReader = new CsvReader();
    }


    public void convertFromCsvToXml(String csvPath, String xmlPath) throws IOException {
        List<String> listOfXml = getXmlFilesFromDirectory(xmlPath);
        File csvFile = new File(csvPath);
        File[] matchingFile = csvFile.listFiles((dir, name) -> name.endsWith("csv"));
        assert matchingFile != null;
        List<String> filesList = new ArrayList<>();
        for (File file : matchingFile) {
            if (!listOfXml.contains(file.getName())) {
                List<Book> books = csvReader.readFile(file);
                getBooks(filesList, books, file.getName(), xmlPath);
            }
        }
    }

    private List<String> getXmlFilesFromDirectory(String xmlPath) {
        File folder = new File(xmlPath);
        File[] listOfFiles = folder.listFiles();
        assert listOfFiles != null;
        List<String> listOfXml = new ArrayList<>();
        for (File listOfFile : listOfFiles) {
            listOfXml.add(listOfFile.getName().replace("xml", "csv"));
        }
        return listOfXml;
    }

    private void getBooks(List<String> filesList, List<Book> books, String fileName, String xmlPath) {
        Document xml = xmlCreator.createXml(books);
        xmlCreator.saveXmlFile(xml, fileName, xmlPath);
        filesList.add(fileName);
    }


}
