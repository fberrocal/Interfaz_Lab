package Controler;

import Herramientas.DatosConexion;
import Model.Paciente_consecutivo;
import java.sql.*;
import java.util.*;
import javax.swing.JOptionPane;

/**
 * Paciente_consecutivo Data Access Object (DAO). creado en: 13/02/2015
 */
public class Paciente_consecutivoDao {

    //1
    DatosConexion datosconexion;

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    Connection conn;

    public Paciente_consecutivoDao() {
        datosconexion = new DatosConexion();
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
            JOptionPane.showMessageDialog(null, "Paciente_consecutivoDao:\n" + ex.toString());
        }
    }

    public Paciente_consecutivo createValueObject() {
        return new Paciente_consecutivo();
    }

    public Paciente_consecutivo getObject(String paciente_cod) throws NotFoundException, SQLException {
        Paciente_consecutivo valueObject = createValueObject();
        valueObject.setPaciente_cod(paciente_cod);
        load(valueObject);
        return valueObject;
    }

    public void load(Paciente_consecutivo valueObject) throws NotFoundException, SQLException {

        if (valueObject.getPaciente_cod() == null) {
            //System.out.println("Can not select without Primary-Key!");
            throw new NotFoundException("Paciente_consecutivoDao:\nCan not select without Primary-Key!");
        }

        String sql = "SELECT * FROM paciente_consecutivo WHERE (paciente_cod = ? ) ";
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, valueObject.getPaciente_cod());

            singleQuery(stmt, valueObject);

        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public List loadAll() throws SQLException {

        String sql = "SELECT * FROM paciente_consecutivo ORDER BY paciente_cod ASC ";
        List searchResults = listQuery(conn.prepareStatement(sql));

        return searchResults;
    }

    /*GUARDAR EN PACIENTE_CONSECUTIVO*/
    public synchronized void create(Paciente_consecutivo valueObject) throws SQLException {
        String sql = "";
        PreparedStatement stmt = null;
        ResultSet result = null;
        try {
            sql = "INSERT INTO paciente_consecutivo ( paciente_cod, fecha, sede_codigo) VALUES (?, ?::date, ?) ";
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, valueObject.getPaciente_cod());
            stmt.setObject(2, valueObject.getFecha());
            stmt.setString(3, valueObject.getSede_codigo());

            int rowcount = databaseUpdate(stmt);
            if (rowcount != 1) {
                //System.out.println("PrimaryKey Error when updating DB!");
                throw new SQLException("Paciente_consecutivoDao:\nPrimaryKey Error when updating DB!");
            }
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    /*ACTUALIZAR EN PACIENTE_CONSECUTIVO*/
    public void save(Paciente_consecutivo valueObject)
            throws NotFoundException, SQLException {

        String sql = "UPDATE paciente_consecutivo SET fecha = ?, sede_codigo = ? WHERE (paciente_cod = ? ) ";
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setObject(1, valueObject.getFecha());
            stmt.setString(2, valueObject.getSede_codigo());

            stmt.setString(3, valueObject.getPaciente_cod());

            int rowcount = databaseUpdate(stmt);
            if (rowcount == 0) {
                //System.out.println("Object could not be saved! (PrimaryKey not found)");
                throw new NotFoundException("Paciente_consecutivoDao:\nObject could not be saved! (PrimaryKey not found)");
            }
            if (rowcount > 1) {
                //System.out.println("PrimaryKey Error when updating DB! (Many objects were affected!)");
                throw new SQLException("Paciente_consecutivoDao:\nPrimaryKey Error when updating DB! (Many objects were affected!)");
            }
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    /*ELIMINAR EN PACIENTE_CONSECUTIVO*/
    public void delete(Paciente_consecutivo valueObject)
            throws NotFoundException, SQLException {

        if (valueObject.getPaciente_cod() == null) {
            //System.out.println("Can not delete without Primary-Key!");
            throw new NotFoundException("Paciente_consecutivoDao:\nCan not delete without Primary-Key!");
        }

        String sql = "DELETE FROM paciente_consecutivo WHERE (paciente_cod = ? ) ";
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, valueObject.getPaciente_cod());

            int rowcount = databaseUpdate(stmt);
            if (rowcount == 0) {
                //System.out.println("Object could not be deleted (PrimaryKey not found)");
                throw new NotFoundException("Paciente_consecutivoDao:\nObject could not be deleted! (PrimaryKey not found)");
            }
            if (rowcount > 1) {
                //System.out.println("PrimaryKey Error when updating DB! (Many objects were deleted!)");
                throw new SQLException("Paciente_consecutivoDao:\nPrimaryKey Error when updating DB! (Many objects were deleted!)");
            }
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    /*VACIAR LA TABLA PACIENTE_CONSECUTIVO*/
    public void deleteAll() throws SQLException {

        String sql = "DELETE FROM paciente_consecutivo";
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

    /* CONSULTA SIMPLE SOBRE LA TABLA  PACIENTE_CONSECUTIVO*/
    protected void singleQuery(PreparedStatement stmt, Paciente_consecutivo valueObject)
            throws NotFoundException, SQLException {

        ResultSet result = null;

        try {
            result = stmt.executeQuery();

            if (result.next()) {

                valueObject.setPaciente_cod(result.getString("paciente_cod"));
                valueObject.setFecha(result.getDate("fecha"));
                valueObject.setSede_codigo(result.getString("sede_codigo"));

            } else {
                //System.out.println("Paciente_consecutivo Object Not Found!");
                throw new NotFoundException("Paciente_consecutivoDao:\nPaciente_consecutivo Object Not Found!");
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

    /*LISTA TODOS LOS REGISTROS DE LA TABLA PACIENTE_CONSECUTIVO*/
    protected List listQuery(PreparedStatement stmt) throws SQLException {

        ArrayList searchResults = new ArrayList();
        ResultSet result = null;

        try {
            result = stmt.executeQuery();

            while (result.next()) {
                Paciente_consecutivo temp = createValueObject();

                temp.setPaciente_cod(result.getString("paciente_cod"));
                temp.setFecha(result.getDate("fecha"));
                temp.setSede_codigo(result.getString("sede_codigo"));

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

    public boolean existePaciente_cosecutivo(String idpaciente, String sedecodigo) {
        String sql = "SELECT * FROM paciente_consecutivo WHERE paciente_cod=? AND sede_codigo=?";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, idpaciente);
            stmt.setString(2, sedecodigo);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Paciente_consecutivoDao:\n" + ex.toString());
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                    //rs.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Paciente_consecutivoDao:\n" + ex.toString());
                }
            }
        }
        return false;
    }

}
