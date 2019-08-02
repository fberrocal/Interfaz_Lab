package interfaz_lab;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.swing.JOptionPane;

/**
 * @author fberrocalm Creado en: 02/03/2015
 */
/**
 * ESTA CLASE SE ENCARGA DE CONTROLAR QUE NO SE EJECUTEN AL TIEMPO MAS DE UNA
 * INSTANCIA DE LA INTERFAZ AGILIS - WINSISLAB
 */
public class Control {
    /*Archivo de control de ejecición de interfaz TMP*/

    private String appPath = System.getProperties().getProperty("user.dir");
    private File archivo = new File(appPath + "\\miApp.tmp");
    /*el archivo miApp.tmp se actualizará cada 20 segundos*/
    private int segundos = 20;
    /*constructor de la Clase*/

    public Control() {
    }
    /*Este método comprueba que el archivo miApp.tmp existe, si no lo crea e inicia los valores*/

    public boolean comprobar() {
        if (archivo.exists()) {
            long tiempo = leer();
            
            if( tiempo == 0 ){
                /*el archivo se eliminó*/
                crearTMP();
                programar_tarea();
                return true;
            }else{
                
                long res = restarTiempo(tiempo);
                if (res < segundos) {
                    JOptionPane.showMessageDialog(null, "Error: Ya existe una instancia\n de la App en ejecución");
                    return false;
                } else {
                    programar_tarea();
                    return true;
                }
            
            }
        } else {
            /*No existe el archivo*/
            crearTMP();
            programar_tarea();
            return true;
        }
    } 
    /*Este método lee miApp.tmp y retora su valor long cantidad en milisegundos*/

    public long leer() {
        boolean bCentinela;
        String linea = "0";
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader(archivo));
            while (bufferedReader.ready()) {
                linea = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error: al intentar leer el archivo miApp.tmp\n" + e.getMessage());
        }
        
        bCentinela = true;
        
        for( int i=0; i<linea.length(); i++ ){
            if( ! Character.isDigit( linea.charAt( i ) ) ){
                bCentinela = false;
                break;
            }
        }
        
        if( bCentinela ){
            return Long.parseLong(linea);
        }else{
            if (archivo.exists()) {
                archivo.delete();
            }
            return 0;
        }
 
    }
    /*PROGRAMAMOS UNA TAREA QUE SE EJECUTARÁ CADA CIERTO TIEMPO*/

    public void programar_tarea() {
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(
                new Runnable() {
                    @Override
                    public void run() {
                        crearTMP();
                    }
                }, 1000, segundos * 1000, TimeUnit.MILLISECONDS);
    }
    /*Este método crea un archivo miApp.tmp con un unico valor que es el tiempo en milisegundos*/

    public void crearTMP() {
        Date fecha = new Date();
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(archivo));
            writer.write(String.valueOf(fecha.getTime()));
            writer.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error: al intentar escribir el archivo miApp.tmp\n" + e.getMessage());
        }
    }
    /*Este método resta el tiempo expresado en milisegundos*/

    public long restarTiempo(long tiempoactual) {
        Date date = new Date();
        long tiempoTMP = date.getTime();
        long tiempo = tiempoTMP - tiempoactual;
        tiempo = tiempo / 1000;
        return tiempo;
    }
    /*Elimina el archivo TMP si existe*/

    public void cerrarApp() {
        if (archivo.exists()) {
            archivo.delete();
        }
        System.exit(0);
    }
}//--> Fin de la clase Control
