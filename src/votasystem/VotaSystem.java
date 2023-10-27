
package votasystem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.swing.JOptionPane;
import static votasystem.Helper.Helper.*;
import votasystem.Models.Elecciones;
import votasystem.Models.Persona;

/**
 *
 * @author Usuario
 */
public class VotaSystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException ,NullPointerException{
        if(!AdminExists()){
             CrearAdmin();
        }
        String opc = "";             
        opc = TiposRoles(Login());  
        
        System.err.println("desde el fronted "+opc);           
        MenuPrincipal(opc);
        //Elecciones();
               
    }

   
  
   
        
}
