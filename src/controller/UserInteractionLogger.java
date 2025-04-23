package controller;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class UserInteractionLogger {

    // logging messages with a timestamp
    public void log(String message) {
        String LOG_FILE = "src/resources/user_interactions.log";

        try(FileWriter fileWriter = new FileWriter(LOG_FILE, true)) {
            fileWriter.write(LocalDateTime.now() + " - " + message + "\n");
        } catch (IOException e) {
            System.err.println("Logging error: " + e.getMessage());
        }
    }
}
