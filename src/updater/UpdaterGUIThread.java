/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package updater;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alessandro
 */
public class UpdaterGUIThread implements Runnable{

    @Override
    public void run() {
        try {
            new UpdaterGUI().setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(UpdaterGUIThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
