package com.techelevator.models;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    private File logFile;
    private PrintWriter writer;

    public Logger(){
        this.logFile = new File("Log.txt");
        try {
            this.writer = new PrintWriter(new FileWriter(this.logFile, true));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void write(String message){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a");
        this.writer.println(LocalDateTime.now().format(formatter).toString() + " " + message);
        this.writer.flush();
    }
    public void close(){
        this.writer.close();
    }
}
