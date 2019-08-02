package Controler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Conexion {

    private Connection con;
    String driver;
    String url;
    String user;
    String pass;

    //CREA UNA NUEVA CONEXION A UNA BASE DE DATOS (CUALQUIERA)
    public Conexion(String driver1, String url1, String us, String pas) {
        driver = driver1;
        url = url1;
        user = us;
        pass = pas;
        try {
            try {
                Class.forName(driver).newInstance();
            } catch (InstantiationException | IllegalAccessException ex) {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            }
            con = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, "Conexion:\n Error de conexión: ", ex);
        }
    }

    //RETORNA LA CONEXION A ESA BASE DE DATOS
    public Connection getCon() {
        return con;
    }

    //PRUEBA LA CONEXION ESTABLECIDA
    public boolean testCon() {
        return (con != null);
    }

    //CIERRA LA CONEXION ESTABLECIDA
    public void CerrarCon() {
        try {
            con.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Conexion:\n: " + ex.getMessage());
        }
    }

    public void conexionPostgresql() {

    }

    @Override
    @SuppressWarnings("FinalizeDeclaration")
    protected void finalize() throws Throwable {
        try {
            con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        super.finalize();
    }
}
