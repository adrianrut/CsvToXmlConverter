package pl.rutkowski;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
    public static void main(String[] args) {

        String csvPath = "/Users/adrian/Desktop/Csv";
        String xmlPath = "/Users/adrian/Desktop/Xml/";

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                FileManager fileManager = new FileManager();
                try {
                    fileManager.convertFromCsvToXml(csvPath, xmlPath);
                } catch (IOException e) {
                    timer.cancel();
                }
            }
        };
        timer.scheduleAtFixedRate(task, 0, 5000);
    }
}