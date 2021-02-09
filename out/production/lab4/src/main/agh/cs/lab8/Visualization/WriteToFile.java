package agh.cs.lab8.Visualization;

import java.io.*;

public class WriteToFile {
    String contentToWrite;

    public WriteToFile(String contentToWrite,int id) throws FileNotFoundException {
        this.contentToWrite = contentToWrite;
        PrintWriter writer = new PrintWriter("statistic" + id + ".txt");
        writer.println(this.contentToWrite);
        writer.close();
    }
}
