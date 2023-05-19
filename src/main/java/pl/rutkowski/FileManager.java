package pl.rutkowski;

import org.w3c.dom.Document;

import java.io.*;
import java.util.*;

public class FileManager {

    private File csvFile;
    private File[] matchingFile;
    private String csvPath;

    public FileManager(File csvFile, File[] matchingFile, String csvPath) {
        this.csvFile = csvFile;
        this.matchingFile = matchingFile;
        this.csvPath = csvPath;
    }

    private final XmlCreator xmlCreator = new XmlCreator();
    private final CsvReader csvReader = new CsvReader();

    public FileManager() {

    }


    public void convertFromCsvToXml() throws IOException {
        csvPath = "/Users/adrian/Desktop/Csv";
        csvFile = new File(csvPath);
        matchingFile = csvFile.listFiles((dir, name) -> name.endsWith("csv"));
        List<String> filesList = new ArrayList<>();
        assert matchingFile != null;
        boolean ifFileExist = checkIfFileExist(matchingFile, filesList);
        if(!ifFileExist){
            assert matchingFile != null;
            for (File file : matchingFile) {
                List<Book> books = csvReader.readFile(file);
                getBooks(filesList, books, file.getName());
            }
        }
    }

    private void getBooks(List<String> filesList, List<Book> books, String fileName) {
        Document xml = xmlCreator.createXml(books);
        xmlCreator.saveXmlFile(xml, fileName);
        filesList.add(fileName);
    }

    private boolean checkIfFileExist(File[] matchingFile, List<String> filesList) {
        assert matchingFile != null;
        for (File file : matchingFile) {
            if(filesList.contains(file.getName())) {
                return true;
            }
        }
        return false;
    }

}
