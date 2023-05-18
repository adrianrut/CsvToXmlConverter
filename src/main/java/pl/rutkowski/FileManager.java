package pl.rutkowski;

import org.w3c.dom.Document;

import java.io.*;
import java.util.*;

public class FileManager {

    private final File csvFile = new File("/Users/adrian/Desktop/Csv");
    private final File[] matchingFile = csvFile.listFiles((dir, name) -> name.endsWith("csv"));
    private final XmlCreator xmlCreator = new XmlCreator();


    public void convertFromCsvToXml() throws IOException {
        List<String> filesList = new ArrayList<>();
        boolean ifFileExist = checkIfFileExist(matchingFile, filesList);
        if(!ifFileExist){
            List<Book> books = new ArrayList<>();
            assert matchingFile != null;
            for (File file : matchingFile) {
                readFile(books, file);
                books = getBooks(filesList, books, file);
            }
        }
    }

    private void readFile(List<Book> books, File file) throws IOException {
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String nextLine;
        while ((nextLine = bufferedReader.readLine()) != null) {
            String[] split = nextLine.split(",");
            books.add(new Book(split[0].trim(), split[1].trim(), split[2].trim(), split[3].trim()));
        }
    }

    private List<Book> getBooks(List<String> filesList, List<Book> books, File file) {
        Document xml = xmlCreator.createXml(books);
        xmlCreator.saveXmlFile(xml, file.getName());
        books = new ArrayList<>();
        filesList.add(file.getName());
        return books;
    }

    private boolean checkIfFileExist(File[] matchingFile, List<String> filesList) {
        for (String s : filesList) {
            assert matchingFile != null;
            for (File file : matchingFile) {
                if(Objects.equals(s, file.getName())) {
                    return true;
                }
            }
        }
        return false;
    }

}
