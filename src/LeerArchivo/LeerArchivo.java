package LeerArchivo;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Clase LeerArchivo que implementa la lectura secuencial de un archivo .txt 
 * mediante FileInputStream, InputStreamReader y BufferedReader.
 */
public class LeerArchivo {

    public static String leerContenido(String rutaArchivo) throws FileNotFoundException, IOException {
        StringBuilder contenido = new StringBuilder();
        
        FileInputStream fileInput = new FileInputStream(rutaArchivo);
        InputStreamReader reader = new InputStreamReader(fileInput, "UTF-8");
        BufferedReader bufferedReader = new BufferedReader(reader);

        String linea;
        while ((linea = bufferedReader.readLine()) != null) {
            contenido.append(linea).append("\n");
        }

        bufferedReader.close();
        reader.close();
        fileInput.close();

        return contenido.toString();
    }
}
