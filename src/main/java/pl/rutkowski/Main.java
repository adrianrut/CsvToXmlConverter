package pl.rutkowski;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
    public static void main(String[] args) {

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                FileManager fileManager = new FileManager();
                try {
                    fileManager.convertFromCsvToXml();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        timer.scheduleAtFixedRate(task, 0, 5000);
    }
}