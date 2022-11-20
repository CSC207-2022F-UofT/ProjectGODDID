package useCases;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class EndChat {
    public boolean endChat(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename + ".txt"));
        int lines = 0;
        while (reader.readLine() != null) {
            lines++;
        }
        reader.close();


        if (lines >= 11){
            PrintWriter writer = new PrintWriter(filename + ".txt");
            writer.print("");
            writer.close();
            return true;
        }

        return false;
    }
}
