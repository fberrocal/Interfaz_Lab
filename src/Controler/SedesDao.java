package Controler;

/**
 * Sedes Data Access Object (DAO).
 *
 * @author fberrocalm Creado en: 13/02/2015
 */
import Herramientas.DatosConexion;
import Model.Sedes;
import java.sql.*;
import java.util.*;
import javax.swing.JOptionPane;
import view.Prueba1;

public class SedesDao {

    //1
    DatosConexion datosconexion;

    Connection conn;

    public SedesDao() {
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
            JOptionPane.showMessageDialog(null, "SedesDao:\n" + ex.getMessage());
        }
    }

    public Sedes createValueObject() {
        return new Sedes();
    }

    public Sedes getObject(String sede_codigo) throws NotFoundException, SQLException {
        Sedes valueObject = createValueObject();
        valueObject.setSede_codigo(sede_codigo);
        load(valueObject);
        return valueObject;
    }

    public void load(Sedes valueObject) throws NotFoundException, SQLException {

        if (valueObject.getSede_codigo() == null) {
            //System.out.println("Can not select without Primary-Key!");
            throw new NotFoundException("SedesDao:\nNo se puede seleccionar registro sin Primary-Key!");
        }

        String sql = "SELECT * FROM sedes WHERE (sede_codigo = ? ) ";
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, valueObject.getSede_codigo());
            singleQuery(stmt, valueObject);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public List loadAll() throws SQLException {
        String sql = "SELECT * FROM sedes ORDER BY sede_codigo ASC ";
        List searchResults = listQuery(conn.prepareStatement(sql));
        return searchResults;
    }

    public synchronized void create(Sedes valueObject) throws SQLException {
        String sql = "";
        PreparedStatement stmt = null;
        ResultSet result = null;

        try {
            sql = "INSERT INTO sedes ( sede_codigo, prefor, orden, "
                    + "preo1, langel_actual) VALUES (?, ?, ?, ?, ?) ";
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, valueObject.getSede_codigo());
            stmt.setString(2, valueObject.getPrefor());
            stmt.setLong(3, valueObject.getOrden());
            stmt.setString(4, valueObject.getPreo1());
            stmt.setLong(5, valueObject.getLangel_actual());

            int rowcount = databaseUpdate(stmt);
            if (rowcount != 1) {
                //System.out.println("PrimaryKey Error when updating DB!");
                throw new SQLException("SedesDao:\nError de clave primaria al actualizar el registro!");
            }

        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }

    }

    public void save(Sedes valueObject) throws NotFoundException, SQLException {
        String sql = "UPDATE sedes SET prefor = ?, orden = ?, preo1 = ?, "
                + "langel_actual = ? WHERE (sede_codigo = ? ) ";
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, valueObject.getPrefor());
            stmt.setLong(2, valueObject.getOrden());
            stmt.setString(3, valueObject.getPreo1());
            stmt.setLong(4, valueObject.getLangel_actual());
            stmt.setString(5, valueObject.getSede_codigo());
            int rowcount = databaseUpdate(stmt);
            if (rowcount == 0) {
                //System.out.println("Object could not be saved! (PrimaryKey not found)");
                throw new NotFoundException("SedesDao:\nObject could not be saved! (PrimaryKey not found)");
            }
            if (rowcount > 1) {
                //System.out.println("PrimaryKey Error when updating DB! (Many objects were affected!)");
                throw new SQLException("SedesDao:\nPrimaryKey Error when updating DB! (Many objects were affected!)");
            }
            if (rowcount == 1) {
                //System.out.println("PrimaryKey Error when updating DB! (Many objects were affected!)");
                Prueba1.statusLabel.setText("El registro ha sido actualizado...");
            }
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public void delete(Sedes valueObject)
            throws NotFoundException, SQLException {

        if (valueObject.getSede_codigo() == null) {
            //System.out.println("Can not delete without Primary-Key!");
            throw new NotFoundException("SedesDao:\nNo se puede eliminar sin primary key!");
        }

        String sql = "DELETE FROM sedes WHERE (sede_codigo = ? ) ";
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, valueObject.getSede_codigo());

            int rowcount = databaseUpdate(stmt);
            if (rowcount == 0) {
                //System.out.println("Object could not be deleted (PrimaryKey not found)");
                throw new NotFoundException("SedesDao:\nObject no puede ser eliminado! (PrimaryKey no encontrada)");
            }
            if (rowcount > 1) {
                //System.out.println("PrimaryKey Error when updating DB! (Many objects were deleted!)");
                throw new SQLException("SedesDao:\nPrimaryKey Error when updating DB! (Many objects were deleted!)");
            }
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public void deleteAll() throws SQLException {
        String sql = "DELETE FROM sedes";
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

    protected void singleQuery(PreparedStatement stmt, Sedes valueObject) throws NotFoundException, SQLException {

        ResultSet result = null;

        try {
            result = stmt.executeQuery();

            if (result.next()) {

                valueObject.setSede_codigo(result.getString("sede_codigo"));
                valueObject.setPrefor(result.getString("prefor"));
                valueObject.setOrden(result.getLong("orden"));
                valueObject.setPreo1(result.getString("preo1"));
                valueObject.setLangel_actual(result.getLong("langel_actual"));

            } else {
                //System.out.println("Sedes Object Not Found!");
                throw new NotFoundException("SedesDao:\nSedes Object Not Found!");
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
                Sedes temp = createValueObject();

                temp.setSede_codigo(result.getString("sede_codigo"));
                temp.setPrefor(result.getString("prefor"));
                temp.setOrden(result.getLong("orden"));
                temp.setPreo1(result.getString("preo1"));
                temp.setLangel_actual(result.getLong("langel_actual"));

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
    
    /**
     * Desc: Devuelve la id de la sede configurada en Winsislab
     * Dev: FMBM - 18/01/2020
     * @param valueObject  
     */
    
    public void getSede(Sedes valueObject) throws NotFoundException, SQLException {
 
        String sql = "SELECT * FROM sedes LIMIT 1";
        PreparedStatement stmt = null;
            
        try {
            stmt = conn.prepareStatement(sql);
            singleQuery(stmt, valueObject);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

}
