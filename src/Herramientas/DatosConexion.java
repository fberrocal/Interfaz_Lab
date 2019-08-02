package Herramientas;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import javax.swing.JOptionPane;

/**
 *
 * @author fberrocalm creado en 04/03/2015
 */
public class DatosConexion {

    //1
    Properties prop = new Properties();
    File ruta = new File("etc/config.properties");
    String driver, url, us, pas;
    String timeAgilis, timeWinsislab;
    int diasLab;
    
    public DatosConexion() {
    }

    public int getDiasLab() {
        return diasLab;
    }

    public void setDiasLab(int diasLab) {
        this.diasLab = diasLab;
    }
    
    public String getTimeAgilis() {
        return timeAgilis;
    }

    public void setTimeAgilis(String timeAgilis) {
        this.timeAgilis = timeAgilis;
    }

    public String getTimeWinsislab() {
        return timeWinsislab;
    }

    public void setTimeWinsislab(String timeWinsislab) {
        this.timeWinsislab = timeWinsislab;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUs() {
        return us;
    }

    public void setUs(String us) {
        this.us = us;
    }

    public String getPas() {
        return pas;
    }

    public void setPas(String pas) {
        this.pas = pas;
    }

    public void datosConexionWinsislab() {
        try {
            prop = PropertiesIO.read(ruta);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error durante la carga del archivo de propiedades: " + ex);
        }
        driver = prop.getProperty("driverPos");
        url = prop.getProperty("urlPos");
        us = prop.getProperty("usuarioPos");
        pas = prop.getProperty("passwordPos");
    }

    public void datosConexionAgilis() {
        try {
            prop = PropertiesIO.read(ruta);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error durante la carga del archivo de propiedades: " + ex);
        }
        driver = prop.getProperty("driverMS");
        url = prop.getProperty("urlMS");
        us = prop.getProperty("usuarioMS");
        pas = prop.getProperty("passwordMS");
    }

    public void datoTimeAgilis() {
        try {
            prop = PropertiesIO.read(ruta);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error durante la carga del archivo de propiedades: " + ex);
        }
        timeAgilis = prop.getProperty("timeAgilis");
    }

    public void datoTimeWinsislab() {
        try {
            prop = PropertiesIO.read(ruta);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error durante la carga del archivo de propiedades: " + ex);
        }
        timeWinsislab = prop.getProperty("timeWinsislab");
    }
    
    public void datoLabxDias() {
        try {
            prop = PropertiesIO.read(ruta);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error durante la carga del archivo de propiedades: " + ex);
        }
        diasLab = Integer.parseInt( prop.getProperty("labxAnt") ); 
    }

}
