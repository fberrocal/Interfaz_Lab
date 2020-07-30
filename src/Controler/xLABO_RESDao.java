package Controler;

/**
 * xLABO_RES Data Access Object (DAO).
 *
 * @author fberrocalm Creado en: 19/02/2015
 */
import Herramientas.DatosConexion;
import Model.xLABO_RES;
import java.sql.*;
import java.util.*;
import javax.swing.JOptionPane;

public class xLABO_RESDao {

    //1
    DatosConexion datosconexion;

    Connection conn;

    public xLABO_RESDao() {
        datosconexion = new DatosConexion();
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public void conectar() {
        datosconexion.datosConexionAgilis();
        conn = new Conexion(datosconexion.getDriver(), "jdbc:sqlserver://" + datosconexion.getUrl(),
                datosconexion.getUs(), datosconexion.getPas()).getCon();
    }

    public void desconectar() {
        try {
            this.conn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "LABO_RESDao:\n" + ex.getMessage());
        }
    }

    public xLABO_RES createValueObject() {
        return new xLABO_RES();
    }

    public xLABO_RES getObject(int ID) throws NotFoundException, SQLException {
        xLABO_RES valueObject = createValueObject();
        valueObject.setID(ID);
        load(valueObject);
        return valueObject;
    }

    public void load(xLABO_RES valueObject) throws NotFoundException, SQLException {

        String sql = "SELECT * FROM XLABO_RES WHERE (ID = ? ) ";
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, valueObject.getID());

            singleQuery(stmt, valueObject);

        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public List loadAll() throws SQLException {
        String sql = "SELECT * FROM XLABO_RES ORDER BY ID ASC ";
        List searchResults = listQuery(conn.prepareStatement(sql));
        return searchResults;
    }

    public synchronized void create(xLABO_RES valueObject) throws SQLException {
        String sql = "";
        PreparedStatement stmt = null;
        try {
            sql = "INSERT INTO XLABO_RES (NOADMISION, NUM_ORDEN, "
                    + "TIPO_DOC, DOCUMENTO, COD_EXAMEN, "
                    + "NUM_PETICION, FECHA_RESULTADO, COD_ANALITO, "
                    + "NOM_ANALITO, RESULTADO, VR_MINIMO, "
                    + "VR_MAXIMO, UNIDADES, USU_VALIDA, "
                    + "ESTADO, VALIDADO) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, valueObject.getID());
            stmt.setString(1, valueObject.getNOADMISION());
            stmt.setString(2, valueObject.getNUM_ORDEN());
            stmt.setString(3, valueObject.getTIPO_DOC());
            stmt.setString(4, valueObject.getDOCUMENTO());
            stmt.setString(5, valueObject.getCOD_EXAMEN());
            stmt.setInt(6, valueObject.getNUM_PETICION());
            stmt.setTimestamp(7, valueObject.getFECHA_RESULTADO());
            stmt.setString(8, valueObject.getCOD_ANALITO());
            stmt.setString(9, valueObject.getNOM_ANALITO());
            stmt.setString(10, valueObject.getRESULTADO());
            stmt.setString(11, valueObject.getVR_MINIMO());
            stmt.setString(12, valueObject.getVR_MAXIMO());
            stmt.setString(13, valueObject.getUNIDADES());
            stmt.setString(14, valueObject.getUSU_VALIDA());
            stmt.setInt(15, valueObject.getESTADO());
            stmt.setInt(16, 0);

            int rowcount = databaseUpdate(stmt);
            if (rowcount != 1) {
                //System.out.println("PrimaryKey Error when updating DB!");
                throw new SQLException("xLABO_RESDao:\nPrimaryKey Error when updating DB!");
            }
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public void save(xLABO_RES valueObject)
            throws NotFoundException, SQLException {

        String sql = "UPDATE XLABO_RES SET NOADMISION = ?, NUM_ORDEN = ?, TIPO_DOC = ?, "
                + "DOCUMENTO = ?, COD_EXAMEN = ?, NUM_PETICION = ?, "
                + "FECHA_RESULTADO = ?, COD_ANALITO = ?, NOM_ANALITO = ?, "
                + "RESULTADO = ?, VR_MINIMO = ?, VR_MAXIMO = ?, "
                + "UNIDADES = ?, USU_VALIDA = ?, ESTADO = ?, VALIDADO = ? WHERE (ID = ? ) ";
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, valueObject.getNOADMISION());
            stmt.setString(2, valueObject.getNUM_ORDEN());
            stmt.setString(3, valueObject.getTIPO_DOC());
            stmt.setString(4, valueObject.getDOCUMENTO());
            stmt.setString(5, valueObject.getCOD_EXAMEN());
            stmt.setInt(6, valueObject.getNUM_PETICION());
            stmt.setTimestamp(7, valueObject.getFECHA_RESULTADO());
            stmt.setString(8, valueObject.getCOD_ANALITO());
            stmt.setString(9, valueObject.getNOM_ANALITO());
            stmt.setString(10, valueObject.getRESULTADO());
            stmt.setString(11, valueObject.getVR_MINIMO());
            stmt.setString(12, valueObject.getVR_MAXIMO());
            stmt.setString(13, valueObject.getUNIDADES());
            stmt.setString(14, valueObject.getUSU_VALIDA());
            stmt.setInt(15, valueObject.getESTADO());
            stmt.setInt(16, valueObject.getVALIDADO());

            stmt.setInt(17, valueObject.getID());

            int rowcount = databaseUpdate(stmt);
            if (rowcount == 0) {
                //System.out.println("Object could not be saved! (PrimaryKey not found)");
                throw new NotFoundException("xLABO_RESDao:\nObject could not be saved! (PrimaryKey not found)");
            }
            if (rowcount > 1) {
                //System.out.println("PrimaryKey Error when updating DB! (Many objects were affected!)");
                throw new SQLException("xLABO_RESDao:\nPrimaryKey Error when updating DB! (Many objects were affected!)");
            }
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public void delete(xLABO_RES valueObject)
            throws NotFoundException, SQLException {

        String sql = "DELETE FROM XLABO_RES WHERE (ID = ? ) ";
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, valueObject.getID());

            int rowcount = databaseUpdate(stmt);
            if (rowcount == 0) {
                //System.out.println("Object could not be deleted (PrimaryKey not found)");
                throw new NotFoundException("xLABO_RESDao:\nObject could not be deleted! (PrimaryKey not found)");
            }
            if (rowcount > 1) {
                //System.out.println("PrimaryKey Error when updating DB! (Many objects were deleted!)");
                throw new SQLException("xLABO_RESDao:\nPrimaryKey Error when updating DB! (Many objects were deleted!)");
            }
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public void deleteAll() throws SQLException {

        String sql = "DELETE FROM XLABO_RES";
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

    protected void singleQuery(PreparedStatement stmt, xLABO_RES valueObject)
            throws NotFoundException, SQLException {

        ResultSet result = null;

        try {
            result = stmt.executeQuery();

            if (result.next()) {

                valueObject.setID(result.getInt("ID"));
                valueObject.setNOADMISION(result.getString("NOADMISION"));
                valueObject.setNUM_ORDEN(result.getString("NUM_ORDEN"));
                valueObject.setTIPO_DOC(result.getString("TIPO_DOC"));
                valueObject.setDOCUMENTO(result.getString("DOCUMENTO"));
                valueObject.setCOD_EXAMEN(result.getString("COD_EXAMEN"));
                valueObject.setNUM_PETICION(result.getInt("NUM_PETICION"));
                valueObject.setFECHA_RESULTADO(result.getTimestamp("FECHA_RESULTADO"));
                valueObject.setCOD_ANALITO(result.getString("COD_ANALITO"));
                valueObject.setNOM_ANALITO(result.getString("NOM_ANALITO"));
                valueObject.setRESULTADO(result.getString("RESULTADO"));
                valueObject.setVR_MINIMO(result.getString("VR_MINIMO"));
                valueObject.setVR_MAXIMO(result.getString("VR_MAXIMO"));
                valueObject.setUNIDADES(result.getString("UNIDADES"));
                valueObject.setUSU_VALIDA(result.getString("USU_VALIDA"));
                valueObject.setESTADO(result.getInt("ESTADO"));
                valueObject.setVALIDADO(result.getInt("VALIDADO"));

            } else {
                //System.out.println("LABO_RES Object Not Found!");
                throw new NotFoundException("XLABO_RESDao:\nLABO_RES Object Not Found!");
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
                xLABO_RES temp = createValueObject();

                temp.setID(result.getInt("ID"));
                temp.setNOADMISION(result.getString("NOADMISION"));
                temp.setNUM_ORDEN(result.getString("NUM_ORDEN"));
                temp.setTIPO_DOC(result.getString("TIPO_DOC"));
                temp.setDOCUMENTO(result.getString("DOCUMENTO"));
                temp.setCOD_EXAMEN(result.getString("COD_EXAMEN"));
                temp.setNUM_PETICION(result.getInt("NUM_PETICION"));
                temp.setFECHA_RESULTADO(result.getTimestamp("FECHA_RESULTADO"));
                temp.setCOD_ANALITO(result.getString("COD_ANALITO"));
                temp.setNOM_ANALITO(result.getString("NOM_ANALITO"));
                temp.setRESULTADO(result.getString("RESULTADO"));
                temp.setVR_MINIMO(result.getString("VR_MINIMO"));
                temp.setVR_MAXIMO(result.getString("VR_MAXIMO"));
                temp.setUNIDADES(result.getString("UNIDADES"));
                temp.setUSU_VALIDA(result.getString("USU_VALIDA"));
                temp.setESTADO(result.getInt("ESTADO"));
                temp.setVALIDADO(result.getInt("VALIDADO"));

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

}
