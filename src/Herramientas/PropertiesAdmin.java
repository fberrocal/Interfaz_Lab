package Herramientas;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import javax.swing.JOptionPane;

public class PropertiesAdmin {

    String driverMS, urlMS, usuarioMS, passwordMS;
    String driverPos, urlPos, usuarioPos, passwordPos;
    String usuarioSis, claveSis;

    Properties prop;
    File ruta;

    public String getUsuarioSis() {
        return usuarioSis;
    }

    public void setUsuarioSis(String usuarioSis) {
        this.usuarioSis = usuarioSis;
    }

    public String getClaveSis() {
        return claveSis;
    }

    public void setClaveSis(String claveSis) {
        this.claveSis = claveSis;
    }

    public PropertiesAdmin() {
        prop = new Properties();
        ruta = new File("etc/config.properties");
    }

    public void loadConfiguraciónSERVERS() {
        try {
            prop = PropertiesIO.read(ruta);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error durante la carga del archivo de propiedades:\n" + ex);
        }
        driverMS = prop.getProperty("driverMS");
        urlMS = prop.getProperty("urlMS");
        usuarioMS = prop.getProperty("usuarioMS");
        passwordMS = prop.getProperty("passwordMS");
        driverPos = prop.getProperty("driverPos");
        urlPos = prop.getProperty("urlPos");
        usuarioPos = prop.getProperty("usuarioPos");
        passwordPos = prop.getProperty("passwordPos");
    }

    public void loadConfigutrationSystemUser() {
        try {
            prop = PropertiesIO.read(ruta);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error durante la carga del archivo de propiedades:\n" + ex);
        }
        usuarioSis = prop.getProperty("usuarioSis");
        claveSis = prop.getProperty("claveSis");
    }

    public void configurarConexiones(String driverMSIn, String urlMSIn, String usuarioMSIn, String passwordMSIn,
            String driverPosIn, String urlPosIn, String usuarioPosIn, String passwordPosIn) {
        prop.setProperty("driverMS", driverMSIn);
        prop.setProperty("urlMS", urlMSIn);
        prop.setProperty("usuarioMS", usuarioMSIn);
        prop.setProperty("passwordMS", passwordMSIn);
        prop.setProperty("driverPos", driverPosIn);
        prop.setProperty("urlPos", urlPosIn);
        prop.setProperty("usuarioPos", usuarioPosIn);
        prop.setProperty("passwordPos", passwordPosIn);
        try {
            PropertiesIO.write(prop, ruta);
            JOptionPane.showMessageDialog(null, "Configuraciones de conexión guardadas");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }

    public PropertiesAdmin(String driverMS, String urlMS, String usuarioMS, String passwordMS, String driverPos, String urlPos, String usuarioPos, String passwordPos) {
        this.driverMS = driverMS;
        this.urlMS = urlMS;
        this.usuarioMS = usuarioMS;
        this.passwordMS = passwordMS;
        this.driverPos = driverPos;
        this.urlPos = urlPos;
        this.usuarioPos = usuarioPos;
        this.passwordPos = passwordPos;
    }

    public String getDriverMS() {
        return driverMS;
    }

    public void setDriverMS(String driverMS) {
        this.driverMS = driverMS;
    }

    public String getUrlMS() {
        return urlMS;
    }

    public void setUrlMS(String urlMS) {
        this.urlMS = urlMS;
    }

    public String getUsuarioMS() {
        return usuarioMS;
    }

    public void setUsuarioMS(String usuarioMS) {
        this.usuarioMS = usuarioMS;
    }

    public String getPasswordMS() {
        return passwordMS;
    }

    public void setPasswordMS(String passwordMS) {
        this.passwordMS = passwordMS;
    }

    public String getDriverPos() {
        return driverPos;
    }

    public void setDriverPos(String driverPos) {
        this.driverPos = driverPos;
    }

    public String getUrlPos() {
        return urlPos;
    }

    public void setUrlPos(String urlPos) {
        this.urlPos = urlPos;
    }

    public String getUsuarioPos() {
        return usuarioPos;
    }

    public void setUsuarioPos(String usuarioPos) {
        this.usuarioPos = usuarioPos;
    }

    public String getPasswordPos() {
        return passwordPos;
    }

    public void setPasswordPos(String passwordPos) {
        this.passwordPos = passwordPos;
    }

    @Override
    public String toString() {
        return "PropertiesIO{" + "driverMS=" + driverMS + ", urlMS=" + urlMS + ", usuarioMS=" + usuarioMS + ", passwordMS=" + passwordMS + ", driverPos=" + driverPos + ", urlPos=" + urlPos + ", usuarioPos=" + usuarioPos + ", passwordPos=" + passwordPos + '}';
    }

    public String EstadoConexionMSSERVER() {
        return "MSSERVER{" + "driverMS=" + driverMS + ", urlMS=" + urlMS + ", usuarioMS=" + usuarioMS + ", passwordMS=" + passwordMS + '}';
    }

    public String EstadoConexionPOSTGRESQL() {
        return "POSTGRESQL{" + "driverPos=" + driverPos + ", urlPos=" + urlPos + ", usuarioPos=" + usuarioPos + ", passwordPos=" + passwordPos + '}';
    }

}
