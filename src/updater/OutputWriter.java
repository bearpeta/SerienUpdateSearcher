/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package updater;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Alessandro
 */
public class OutputWriter {

    public OutputWriter() {

    }

    public void writeToFile(File file, List<String> series) throws IOException {
        FileWriter writer = new FileWriter(file);
        BufferedWriter bufferWriter = new BufferedWriter(writer);

        for (String serie : series) {
            bufferWriter.write(serie);
            bufferWriter.newLine();
        }

        bufferWriter.close();
    }
}
