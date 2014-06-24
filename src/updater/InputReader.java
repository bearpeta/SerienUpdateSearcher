/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package updater;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException; 
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alessandro
 */
public class InputReader {

    private final List<String> inputList;


    public InputReader() {
        inputList = new ArrayList<>();
    }

    public List<String> readInput(File file) throws IOException {
        if(!inputList.isEmpty()){
            inputList.clear();
        }
        String zeile;
        try {
            FileReader reader = new FileReader(file);
            BufferedReader bufferReader = new BufferedReader(reader);

            do {
                zeile = bufferReader.readLine();
                if (zeile != null) {
                    inputList.add(zeile);
                }
            } while (zeile != null);

            bufferReader.close();
            
            return inputList;
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
            return inputList;
        }
    }
    public List<String> getInput(){
        return inputList;
    }
}
