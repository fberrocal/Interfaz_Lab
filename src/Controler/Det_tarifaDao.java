package Controler;

import Herramientas.DatosConexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 * @author fberrocalm Creado en: 16/02/2015
 */
public class Det_tarifaDao {

    //1
    DatosConexion datosconexion;

    String examen, codigo_alt;

    public Det_tarifaDao() {
        datosconexion = new DatosConexion();
    }
    double precio;

    Connection conn;

    public String getExamen() {
        return examen;
    }

    public void setExamen(String examen) {
        this.examen = examen;
    }

    public String getCodigo_alt() {
        return codigo_alt;
    }

    public void setCodigo_alt(String codigo_alt) {
        this.codigo_alt = codigo_alt;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
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
            JOptionPane.showMessageDialog(null, "Det_tarifaDao:\n" + ex.getMessage());
        }
    }

    public void valorTarifa(String codExamen) {
        try {
            String sql = "select examen, codigo_alt,precio from det_tarifa where tarifa_cod='IMAT' and examen = ?";
            PreparedStatement stmt;
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, codExamen);
            ResultSet resul = stmt.executeQuery();
            if (resul.next()) {
                this.setExamen(resul.getString("examen"));
                this.setCodigo_alt(resul.getString("codigo_alt"));
                this.setPrecio(resul.getDouble("precio"));
                //resul.close();
            } else {
                this.setExamen(codExamen); ///Se coloca la variable examen de este objeto como vacía
                this.setCodigo_alt("");
                this.setPrecio(0);
            }
            stmt.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Det_tarifaDao:\n" + ex.getMessage());
        }
    }

}
