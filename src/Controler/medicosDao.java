package Controler;

import Herramientas.DatosConexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author fberrocalm
 */
public class medicosDao {

    //1
    DatosConexion datosconexion;

    public medicosDao() {
        datosconexion = new DatosConexion();
    }

    Connection conn;

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public void conectar() {
        datosconexion.datosConexionWinsislab();
        conn = new Conexion(datosconexion.getDriver(), "jdbc:postgresql://" + datosconexion.getUrl(),
                datosconexion.getUs(), datosconexion.getPas()).getCon();
    }

    public void desconectar() {
        try {
            this.conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(medicosDao.class.getName()).log(Level.SEVERE, "MedicosDao:\n" + ex.getMessage(), ex);
        }
    }

    public boolean existe_medico(String cod_med) {
        String sql = "select * from medicos where medico_cod=?";
        PreparedStatement stmt = null;
        ResultSet rs;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, cod_med);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(medicosDao.class.getName()).log(Level.SEVERE, "MedicosDao:\n" + ex.getMessage(), ex);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                    //rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(medicosDao.class.getName()).log(Level.SEVERE, "MedicosDao:\n" + ex.getMessage(), ex);
                }
            }
        }
        return false;
    }

}
