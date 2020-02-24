package persistence;

import java.io.*;

public class FileWriter {
    // MODIFIES: this
    // EFFECTS: writes w to file
    public static void write(String fileName, Writable w) throws FileNotFoundException, UnsupportedEncodingException {
        Writer writer = null;
        try {
            writer = new PrintWriter(new File(fileName), "UTF-8");
            w.write(writer);
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
