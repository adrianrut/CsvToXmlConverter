package pl.rutkowski;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {

    public List<Book> readFile(File file) throws IOException {
        List<Book> bookList = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))){
            String nextLine;
            while ((nextLine = bufferedReader.readLine()) != null) {
                String[] split = nextLine.split(",");
                if (isValid(split)) {
                    bookList.add(new Book(split[0].trim(), split[1].trim(), split[2].trim(), split[3].trim()));
                } else {
                    System.err.println("Exception caught. Incorrect book format");
                    System.exit(0);
                }
            }
        }
        return bookList;
    }

    private boolean isValid(String[] book) {
        if (book != null && book.length == 4) {
            for (String s : book) {
                if (!s.isBlank()) {
                    return true;
                }
            }
        }
        return false;
    }
}
