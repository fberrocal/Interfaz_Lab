package Controler;

/**
 * @author fberrocalm creado en: 05/02/2015
 */
import Herramientas.DatosConexion;
import Model.Pacodbc_det;
import java.sql.*;
import java.util.*;
import javax.swing.JOptionPane;

public class Pacodbc_detDao {

    //1
    DatosConexion datosconexion;

    Connection conn;

    public Pacodbc_detDao() {
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
            JOptionPane.showMessageDialog(null, "pacodbc_det:\n" + ex.getMessage());
        }
    }

    public Pacodbc_det createValueObject() {
        return new Pacodbc_det();
    }

    public Pacodbc_det getObject(String cod_odbc) throws NotFoundException, SQLException {
        Pacodbc_det valueObject = createValueObject();
        valueObject.setCod_odbc(cod_odbc);
        load(valueObject);
        return valueObject;
    }

    public void load(Pacodbc_det valueObject) throws NotFoundException, SQLException {

        if (valueObject.getCod_odbc() == null) {
            //System.out.println("Can not select without Primary-Key!");
            throw new NotFoundException("pacodbc_det:\nCan not select without Primary-Key!");
        }

        String sql = "SELECT * FROM pacodbc_det WHERE (cod_odbc = ? ) ";
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, valueObject.getCod_odbc());

            singleQuery(stmt, valueObject);

        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public List loadAll() throws SQLException {
        String sql = "SELECT * FROM pacodbc_det ORDER BY cod_odbc ASC ";
        List searchResults = listQuery(conn.prepareStatement(sql));
        return searchResults;
    }

    public synchronized void create(Pacodbc_det valueObject) throws SQLException {
        String sql = "";
        PreparedStatement stmt = null;
        try {
            sql = "INSERT INTO pacodbc_det ( cod_odbc, tipodcto_cod, nit, "
                    + "cod_enla1, examen_cod, secuencia, "
                    + "detalle, cantidad, precio, "
                    + "copago, alterno_exa1, alterno_exa2, "
                    + "alterno_exa3, procesado) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, valueObject.getCod_odbc());
            stmt.setString(2, valueObject.getTipodcto_cod());
            stmt.setString(3, valueObject.getNit());
            stmt.setString(4, valueObject.getCod_enla1());
            stmt.setString(5, valueObject.getExamen_cod());
            stmt.setString(6, valueObject.getSecuencia());
            stmt.setString(7, valueObject.getDetalle());
            stmt.setInt(8, valueObject.getCantidad());
            stmt.setDouble(9, valueObject.getPrecio());
            stmt.setDouble(10, valueObject.getCopago());
            stmt.setString(11, valueObject.getAlterno_exa1());
            stmt.setString(12, valueObject.getAlterno_exa2());
            stmt.setString(13, valueObject.getAlterno_exa3());
            stmt.setBoolean(14, valueObject.getProcesado());

            int rowcount = databaseUpdate(stmt);
            if (rowcount != 1) {
                //System.out.println("PrimaryKey Error when updating DB!");
                throw new SQLException("pacodbc_det:\nPrimaryKey Error when updating DB!");
            }
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public void save(Pacodbc_det valueObject)
            throws NotFoundException, SQLException {

        String sql = "UPDATE pacodbc_det SET tipodcto_cod = ?, nit = ?, cod_enla1 = ?, "
                + "examen_cod = ?, secuencia = ?, detalle = ?, "
                + "cantidad = ?, precio = ?, copago = ?, "
                + "alterno_exa1 = ?, alterno_exa2 = ?, alterno_exa3 = ?, "
                + "procesado = ? WHERE (cod_odbc = ? ) ";
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, valueObject.getTipodcto_cod());
            stmt.setString(2, valueObject.getNit());
            stmt.setString(3, valueObject.getCod_enla1());
            stmt.setString(4, valueObject.getExamen_cod());
            stmt.setString(5, valueObject.getSecuencia());
            stmt.setString(6, valueObject.getDetalle());
            stmt.setInt(7, valueObject.getCantidad());
            stmt.setDouble(8, valueObject.getPrecio());
            stmt.setDouble(9, valueObject.getCopago());
            stmt.setString(10, valueObject.getAlterno_exa1());
            stmt.setString(11, valueObject.getAlterno_exa2());
            stmt.setString(12, valueObject.getAlterno_exa3());
            stmt.setBoolean(13, valueObject.getProcesado());

            stmt.setString(14, valueObject.getCod_odbc());

            int rowcount = databaseUpdate(stmt);
            if (rowcount == 0) {
                //System.out.println("Object could not be saved! (PrimaryKey not found)");
                throw new NotFoundException("pacodbc_det:\nObject could not be saved! (PrimaryKey not found)");
            }
            if (rowcount > 1) {
                //System.out.println("PrimaryKey Error when updating DB! (Many objects were affected!)");
                throw new SQLException("pacodbc_det:\nPrimaryKey Error when updating DB! (Many objects were affected!)");
            }
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public void delete(Pacodbc_det valueObject)
            throws NotFoundException, SQLException {

        if (valueObject.getCod_odbc() == null) {
            //System.out.println("Can not delete without Primary-Key!");
            throw new NotFoundException("pacodbc_det:\nCan not delete without Primary-Key!");
        }

        String sql = "DELETE FROM pacodbc_det WHERE (cod_odbc = ? ) ";
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, valueObject.getCod_odbc());

            int rowcount = databaseUpdate(stmt);
            if (rowcount == 0) {
                //System.out.println("Object could not be deleted (PrimaryKey not found)");
                throw new NotFoundException("pacodbc_det:\nObject could not be deleted! (PrimaryKey not found)");
            }
            if (rowcount > 1) {
                //System.out.println("PrimaryKey Error when updating DB! (Many objects were deleted!)");
                throw new SQLException("pacodbc_det:\nPrimaryKey Error when updating DB! (Many objects were deleted!)");
            }
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public void deleteAll() throws SQLException {

        String sql = "DELETE FROM pacodbc_det";
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);
            int rowcount = databaseUpdate(stmt);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    protected int databaseUpdate(PreparedStatement stmt) throws SQLException {
        int result = stmt.executeUpdate();
        return result;
    }

    protected void singleQuery(PreparedStatement stmt, Pacodbc_det valueObject)
            throws NotFoundException, SQLException {

        ResultSet result = null;

        try {
            result = stmt.executeQuery();

            if (result.next()) {

                valueObject.setCod_odbc(result.getString("cod_odbc"));
                valueObject.setTipodcto_cod(result.getString("tipodcto_cod"));
                valueObject.setNit(result.getString("nit"));
                valueObject.setCod_enla1(result.getString("cod_enla1"));
                valueObject.setExamen_cod(result.getString("examen_cod"));
                valueObject.setSecuencia(result.getString("secuencia"));
                valueObject.setDetalle(result.getString("detalle"));
                valueObject.setCantidad(result.getInt("cantidad"));
                valueObject.setPrecio(result.getDouble("precio"));
                valueObject.setCopago(result.getDouble("copago"));
                valueObject.setAlterno_exa1(result.getString("alterno_exa1"));
                valueObject.setAlterno_exa2(result.getString("alterno_exa2"));
                valueObject.setAlterno_exa3(result.getString("alterno_exa3"));
                valueObject.setProcesado(result.getBoolean("procesado"));

            } else {
                throw new NotFoundException("pacodbc_det:\nPacodbc_det Object Not Found!");
            }
        } finally {
            if (result != null) {
                result.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    protected List listQuery(PreparedStatement stmt) throws SQLException {

        ArrayList searchResults = new ArrayList();
        ResultSet result = null;

        try {
            result = stmt.executeQuery();

            while (result.next()) {
                Pacodbc_det temp = createValueObject();

                temp.setCod_odbc(result.getString("cod_odbc"));
                temp.setTipodcto_cod(result.getString("tipodcto_cod"));
                temp.setNit(result.getString("nit"));
                temp.setCod_enla1(result.getString("cod_enla1"));
                temp.setExamen_cod(result.getString("examen_cod"));
                temp.setSecuencia(result.getString("secuencia"));
                temp.setDetalle(result.getString("detalle"));
                temp.setCantidad(result.getInt("cantidad"));
                temp.setPrecio(result.getDouble("precio"));
                temp.setCopago(result.getDouble("copago"));
                temp.setAlterno_exa1(result.getString("alterno_exa1"));
                temp.setAlterno_exa2(result.getString("alterno_exa2"));
                temp.setAlterno_exa3(result.getString("alterno_exa3"));
                temp.setProcesado(result.getBoolean("procesado"));

                searchResults.add(temp);
            }

        } finally {
            if (result != null) {
                result.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }

        return (List) searchResults;
    }

    public boolean existeDetalle(String codOdbc, String codExamen, String sec) {
        String sql = "SELECT * FROM Pacodbc_det WHERE cod_odbc = ? and examen_cod = ? and secuencia = ?";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, codOdbc);
            stmt.setString(2, codExamen);
            stmt.setString(3, sec);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "pacodbc_det:\n" + ex.toString());
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                    //rs.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "pacodbc_det:\n" + ex.toString());
                }
            }
        }
        return false;
    }

    //Conforma la existencia de un enxamen específico en la tabla pacodbc_det
    public boolean confirma_examen(String alterno1, String alterno2, String cod) {
        String sql = "select alterno_exa1,alterno_exa2,cod_odbc,cod_enla1 "
                + "from pacodbc_det "
                + "where alterno_exa1=? and alterno_exa2=? and cod_odbc=?";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, alterno1);
            stmt.setString(2, alterno2);
            stmt.setString(3, cod);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "pacodbc_det:\n" + ex.toString());
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                    //rs.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "pacodbc_det:\n" + ex.toString());
                }
            }
        }
        return false;
    }

}
