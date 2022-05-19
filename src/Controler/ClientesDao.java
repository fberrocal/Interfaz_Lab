/*
 * Controlador de clientes
 * Ofrece funcionalidades para acceder a la tabla clientes de la BD Winsislab
 */
package Controler;

import Herramientas.DatosConexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author fberrocalm
 * 27.enero.2022
 */
public class ClientesDao {
    DatosConexion datosconexion;
    Connection conn;

    public ClientesDao() {
        datosconexion = new DatosConexion();
    }
    
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
            Logger.getLogger(medicosDao.class.getName()).log(Level.SEVERE, "ClientesDao:\n" + ex.getMessage(), ex);
        }
    }

    /**
     * Verifica la existencia de un cliente con el código recibido como parámetro
     * @param cod_cliente
     * @return 
     */
    public boolean existe_cliente(String cod_cliente) {
        ResultSet rs;
        boolean existe;
        String sql;
        PreparedStatement stmt;
        
        sql = "select * from clientes where clte_codigo=?";
        stmt = null;
        existe = false;
        
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, cod_cliente);
            rs = stmt.executeQuery();
            if (rs.next()) {
                existe = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(medicosDao.class.getName()).log(Level.SEVERE, "ClientesDao:\n" + ex.getMessage(), ex);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(medicosDao.class.getName()).log(Level.SEVERE, "ClientesDao:\n" + ex.getMessage(), ex);
                }
            }
        }
        
        return existe;
    }
    
}
