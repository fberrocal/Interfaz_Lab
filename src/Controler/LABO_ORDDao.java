/*
 * Esta clase ejecuta las tareas de bases de datos sobre la tabla LABO_ORD de la base de datos Agilis
 */
package Controler;

import Herramientas.DatosConexion;
import Model.LABO_ORD;
import java.sql.*;
import java.util.*;
import javax.swing.JOptionPane;

import javax.swing.table.DefaultTableModel;

/**
 * @author ADMINIMAT creado en: 03/02/2015
 */
public class LABO_ORDDao {

    //1
    DatosConexion datosconexion;

    Connection conn;

    public LABO_ORDDao() {
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
            JOptionPane.showMessageDialog(null, "LABO_ORDDao:\n" + ex.getMessage());
        }
    }

    /**
     * Crea un objeto de tipo LABO_ORD.
     */
    public LABO_ORD createValueObject() {
        return new LABO_ORD();
    }

    public LABO_ORD getObject(int ID) throws NotFoundException, SQLException {
        LABO_ORD valueObject = createValueObject();
        valueObject.setID(ID);
        listarLABO_ORD(valueObject);
        return valueObject;
    }

    public void listarLABO_ORD(LABO_ORD valueObject) throws NotFoundException, SQLException {
        String sql = "SELECT * FROM LABO_ORD WHERE ID = ?";
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

    public List lisarLABO_ORDTodas() throws SQLException {
        String sql = "SELECT * FROM LABO_ORD ORDER BY ID ASC ";
        List searchResults = listQuery(conn.prepareStatement(sql));
        return searchResults;
    }

    public synchronized void nuevoLABO_ORD(LABO_ORD valueObject) throws SQLException {

        String sql = "";
        PreparedStatement stmt = null;
        ResultSet result = null;

        try {
            sql = "INSERT INTO LABO_ORD ( ID, NOADMISION, NUM_ORDEN, "
                    + "FECHA_RESULTADO, TIPO_DOC, DOCUMENTO, "
                    + "APELLIDO1, APELLIDO2, NOMBRE1, "
                    + "NOMBRE2, SEXO, FECHANAC, "
                    + "DIRECCION, TELEFONO, COD_CIUDAD, "
                    + "COD_ZONA, CELULAR, EMAIL, "
                    + "COD_EXAMEN, NOM_EXAMEN, CANTIDAD, "
                    + "NUM_PETICION, PISO, EN_EMBARAZO, "
                    + "TIPO_USUARIO, TIPOSER, COD_MEDICO, "
                    + "NOM_MEDICO, COD_CLIENTE, NOM_CLIENTE, "
                    + "COD_CENCOS, NOM_CENCOS, COD_SEDE, "
                    + "ESTADO) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, valueObject.getID());
            stmt.setString(2, valueObject.getNOADMISION());
            stmt.setString(3, valueObject.getNUM_ORDEN());
            stmt.setTimestamp(4, valueObject.getFECHA_RESULTADO());
            stmt.setString(5, valueObject.getTIPO_DOC());
            stmt.setString(6, valueObject.getDOCUMENTO());
            stmt.setString(7, valueObject.getAPELLIDO1());
            stmt.setString(8, valueObject.getAPELLIDO2());
            stmt.setString(9, valueObject.getNOMBRE1());
            stmt.setString(10, valueObject.getNOMBRE2());
            stmt.setString(11, valueObject.getSEXO());
            stmt.setTimestamp(12, valueObject.getFECHANAC());
            stmt.setString(13, valueObject.getDIRECCION());
            stmt.setString(14, valueObject.getTELEFONO());
            stmt.setString(15, valueObject.getCOD_CIUDAD());
            stmt.setString(16, valueObject.getCOD_ZONA());
            stmt.setString(17, valueObject.getCELULAR());
            stmt.setString(18, valueObject.getEMAIL());
            stmt.setString(19, valueObject.getCOD_EXAMEN());
            stmt.setString(20, valueObject.getNOM_EXAMEN());
            stmt.setInt(21, valueObject.getCANTIDAD());
            stmt.setInt(22, valueObject.getNUM_PETICION());
            stmt.setString(23, valueObject.getPISO());
            stmt.setInt(24, valueObject.getEN_EMBARAZO());
            stmt.setString(25, valueObject.getTIPO_USUARIO());
            stmt.setString(26, valueObject.getTIPOSER());
            stmt.setString(27, valueObject.getCOD_MEDICO());
            stmt.setString(28, valueObject.getNOM_MEDICO());
            stmt.setString(29, valueObject.getCOD_CLIENTE());
            stmt.setString(30, valueObject.getNOM_CLIENTE());
            stmt.setString(31, valueObject.getCOD_CENCOS());
            stmt.setString(32, valueObject.getNOM_CENCOS());
            stmt.setString(33, valueObject.getCOD_SEDE());
            stmt.setInt(34, valueObject.getESTADO());

            int rowcount = databaseUpdate(stmt);
            if (rowcount != 1) {
                //System.out.println("PrimaryKey Error when updating DB!");
                throw new SQLException("LABO_ORDDao:\nError de clave primaria al ingresar registro!");
            }
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }

    }

    public void actualizarLABO_ORD(LABO_ORD valueObject)
            throws NotFoundException, SQLException {
        String sql = "UPDATE LABO_ORD SET NOADMISION = ?, NUM_ORDEN = ?, FECHA_RESULTADO = ?, "
                + "TIPO_DOC = ?, DOCUMENTO = ?, APELLIDO1 = ?, "
                + "APELLIDO2 = ?, NOMBRE1 = ?, NOMBRE2 = ?, "
                + "SEXO = ?, FECHANAC = ?, DIRECCION = ?, "
                + "TELEFONO = ?, COD_CIUDAD = ?, COD_ZONA = ?, "
                + "CELULAR = ?, EMAIL = ?, COD_EXAMEN = ?, "
                + "NOM_EXAMEN = ?, CANTIDAD = ?, NUM_PETICION = ?, "
                + "PISO = ?, EN_EMBARAZO = ?, TIPO_USUARIO = ?, "
                + "TIPOSER = ?, COD_MEDICO = ?, NOM_MEDICO = ?, "
                + "COD_CLIENTE = ?, NOM_CLIENTE = ?, COD_CENCOS = ?, "
                + "NOM_CENCOS = ?, COD_SEDE = ?, ESTADO = ? WHERE (ID = ? ) ";
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, valueObject.getNOADMISION());
            stmt.setString(2, valueObject.getNUM_ORDEN());
            stmt.setTimestamp(3, valueObject.getFECHA_RESULTADO());
            stmt.setString(4, valueObject.getTIPO_DOC());
            stmt.setString(5, valueObject.getDOCUMENTO());
            stmt.setString(6, valueObject.getAPELLIDO1());
            stmt.setString(7, valueObject.getAPELLIDO2());
            stmt.setString(8, valueObject.getNOMBRE1());
            stmt.setString(9, valueObject.getNOMBRE2());
            stmt.setString(10, valueObject.getSEXO());
            stmt.setTimestamp(11, valueObject.getFECHANAC());
            stmt.setString(12, valueObject.getDIRECCION());
            stmt.setString(13, valueObject.getTELEFONO());
            stmt.setString(14, valueObject.getCOD_CIUDAD());
            stmt.setString(15, valueObject.getCOD_ZONA());
            stmt.setString(16, valueObject.getCELULAR());
            stmt.setString(17, valueObject.getEMAIL());
            stmt.setString(18, valueObject.getCOD_EXAMEN());
            stmt.setString(19, valueObject.getNOM_EXAMEN());
            stmt.setInt(20, valueObject.getCANTIDAD());
            stmt.setInt(21, valueObject.getNUM_PETICION());
            stmt.setString(22, valueObject.getPISO());
            stmt.setInt(23, valueObject.getEN_EMBARAZO());
            stmt.setString(24, valueObject.getTIPO_USUARIO());
            stmt.setString(25, valueObject.getTIPOSER());
            stmt.setString(26, valueObject.getCOD_MEDICO());
            stmt.setString(27, valueObject.getNOM_MEDICO());
            stmt.setString(28, valueObject.getCOD_CLIENTE());
            stmt.setString(29, valueObject.getNOM_CLIENTE());
            stmt.setString(30, valueObject.getCOD_CENCOS());
            stmt.setString(31, valueObject.getNOM_CENCOS());
            stmt.setString(32, valueObject.getCOD_SEDE());
            stmt.setInt(33, valueObject.getESTADO());
            stmt.setInt(34, valueObject.getID());

            int rowcount = databaseUpdate(stmt);
            if (rowcount == 0) {
                //System.out.println("Object could not be saved! (PrimaryKey not found)");
                throw new NotFoundException("LABO_ORDDao:\nRegistro no puede ser actualizado! (PrimaryKey no encontrada)");
            }
            if (rowcount > 1) {
                //System.out.println("PrimaryKey Error when updating DB! (Many objects were affected!)");
                throw new SQLException("LABO_ORDDao:\nError de PrimaryKey al actualizar e lregistro! (Varios objetos fueron afectados!)");
            }
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    //Elimina un registro de la tabla LABO_ORD
    public void eliminar(LABO_ORD valueObject)
            throws NotFoundException, SQLException {

        String sql = "DELETE FROM LABO_ORD WHERE (ID = ? ) ";
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, valueObject.getID());

            int rowcount = databaseUpdate(stmt);
            if (rowcount == 0) {
                //System.out.println("Object could not be deleted (PrimaryKey not found)");
                throw new NotFoundException("LABO_ORDDao:\nObjeto no puede ser eliminado! (PrimaryKey no encontrada)");
            }
            if (rowcount > 1) {
                //System.out.println("PrimaryKey Error when updating DB! (Many objects were deleted!)");
                throw new SQLException("LABO_ORDDao:\nError de PrimaryKey al actualizar el registro! (Varios objetos fueron eliminados!)");
            }
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    //Elimina todo el contenido de la tabla LABO_ORD
    public void eliminarTodo() throws SQLException {

        String sql = "DELETE FROM LABO_ORD";
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

    //Cuenta los registros de la tabla LABO_ORD
    public int countAll() throws SQLException {

        String sql = "SELECT count(*) FROM LABO_ORD";
        PreparedStatement stmt = null;
        ResultSet result = null;
        int allRows = 0;

        try {
            stmt = conn.prepareStatement(sql);
            result = stmt.executeQuery();

            if (result.next()) {
                allRows = result.getInt(1);
            }
        } finally {
            if (result != null) {
                result.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
        return allRows;
    }

    protected int databaseUpdate(PreparedStatement stmt) throws SQLException {
        int result = stmt.executeUpdate();
        return result;
    }

    //consulta sencilla sobre la tabla LABO_ORD
    protected void singleQuery(PreparedStatement stmt, LABO_ORD valueObject)
            throws NotFoundException, SQLException {
        ResultSet result = null;
        try {
            result = stmt.executeQuery();
            if (result.next()) {
                valueObject.setID(result.getInt("ID"));
                valueObject.setNOADMISION(result.getString("NOADMISION"));
                valueObject.setNUM_ORDEN(result.getString("NUM_ORDEN"));
                valueObject.setFECHA_RESULTADO(result.getTimestamp("FECHA_RESULTADO"));
                valueObject.setTIPO_DOC(result.getString("TIPO_DOC"));
                valueObject.setDOCUMENTO(result.getString("DOCUMENTO"));
                valueObject.setAPELLIDO1(result.getString("APELLIDO1"));
                valueObject.setAPELLIDO2(result.getString("APELLIDO2"));
                valueObject.setNOMBRE1(result.getString("NOMBRE1"));
                valueObject.setNOMBRE2(result.getString("NOMBRE2"));
                valueObject.setSEXO(result.getString("SEXO"));
                valueObject.setFECHANAC(result.getTimestamp("FECHANAC"));
                valueObject.setDIRECCION(result.getString("DIRECCION"));
                valueObject.setTELEFONO(result.getString("TELEFONO"));
                valueObject.setCOD_CIUDAD(result.getString("COD_CIUDAD"));
                valueObject.setCOD_ZONA(result.getString("COD_ZONA"));
                valueObject.setCELULAR(result.getString("CELULAR"));
                valueObject.setEMAIL(result.getString("EMAIL"));
                valueObject.setCOD_EXAMEN(result.getString("COD_EXAMEN"));
                valueObject.setNOM_EXAMEN(result.getString("NOM_EXAMEN"));
                valueObject.setCANTIDAD(result.getInt("CANTIDAD"));
                valueObject.setNUM_PETICION(result.getInt("NUM_PETICION"));
                valueObject.setPISO(result.getString("PISO"));
                valueObject.setEN_EMBARAZO(result.getInt("EN_EMBARAZO"));
                valueObject.setTIPO_USUARIO(result.getString("TIPO_USUARIO"));
                valueObject.setTIPOSER(result.getString("TIPOSER"));
                valueObject.setCOD_MEDICO(result.getString("COD_MEDICO"));
                valueObject.setNOM_MEDICO(result.getString("NOM_MEDICO"));
                valueObject.setCOD_CLIENTE(result.getString("COD_CLIENTE"));
                valueObject.setNOM_CLIENTE(result.getString("NOM_CLIENTE"));
                valueObject.setCOD_CENCOS(result.getString("COD_CENCOS"));
                valueObject.setNOM_CENCOS(result.getString("NOM_CENCOS"));
                valueObject.setCOD_SEDE(result.getString("COD_SEDE"));
                valueObject.setESTADO(result.getInt("ESTADO"));
            } else {
                //System.out.println("LABO_ORD Object Not Found!");
                throw new NotFoundException("LABO_ORDDao:\nLABO_ORD Object Not Found!");
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

    //Consulta pára listar datos de la tabla LABO_ORD
    protected List listQuery(PreparedStatement stmt) throws SQLException {

        ArrayList searchResults = new ArrayList();
        ResultSet result = null;

        try {
            result = stmt.executeQuery();

            while (result.next()) {
                LABO_ORD temp = createValueObject();

                temp.setID(result.getInt("ID"));
                temp.setNOADMISION(result.getString("NOADMISION"));
                temp.setNUM_ORDEN(result.getString("NUM_ORDEN"));
                temp.setFECHA_RESULTADO(result.getTimestamp("FECHA_RESULTADO"));
                temp.setTIPO_DOC(result.getString("TIPO_DOC"));
                temp.setDOCUMENTO(result.getString("DOCUMENTO"));
                temp.setAPELLIDO1(result.getString("APELLIDO1"));
                temp.setAPELLIDO2(result.getString("APELLIDO2"));
                temp.setNOMBRE1(result.getString("NOMBRE1"));
                temp.setNOMBRE2(result.getString("NOMBRE2"));
                temp.setSEXO(result.getString("SEXO"));
                temp.setFECHANAC(result.getTimestamp("FECHANAC"));
                temp.setDIRECCION(result.getString("DIRECCION"));
                temp.setTELEFONO(result.getString("TELEFONO"));
                temp.setCOD_CIUDAD(result.getString("COD_CIUDAD"));
                temp.setCOD_ZONA(result.getString("COD_ZONA"));
                temp.setCELULAR(result.getString("CELULAR"));
                temp.setEMAIL(result.getString("EMAIL"));
                temp.setCOD_EXAMEN(result.getString("COD_EXAMEN"));
                temp.setNOM_EXAMEN(result.getString("NOM_EXAMEN"));
                temp.setCANTIDAD(result.getInt("CANTIDAD"));
                temp.setNUM_PETICION(result.getInt("NUM_PETICION"));
                temp.setPISO(result.getString("PISO"));
                temp.setEN_EMBARAZO(result.getInt("EN_EMBARAZO"));
                temp.setTIPO_USUARIO(result.getString("TIPO_USUARIO"));
                temp.setTIPOSER(result.getString("TIPOSER"));
                temp.setCOD_MEDICO(result.getString("COD_MEDICO"));
                temp.setNOM_MEDICO(result.getString("NOM_MEDICO"));
                temp.setCOD_CLIENTE(result.getString("COD_CLIENTE"));
                temp.setNOM_CLIENTE(result.getString("NOM_CLIENTE"));
                temp.setCOD_CENCOS(result.getString("COD_CENCOS"));
                temp.setNOM_CENCOS(result.getString("NOM_CENCOS"));
                temp.setCOD_SEDE(result.getString("COD_SEDE"));
                temp.setESTADO(result.getInt("ESTADO"));

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

    public DefaultTableModel listarOrdenes() {
        DefaultTableModel modelo = new DefaultTableModel();
        ResultSet rs;
        modelo.addColumn("ID");
        modelo.addColumn("NOADMISION");
        modelo.addColumn("NUM_ORDEN");
        modelo.addColumn("FECHA_RESULTADO");
        modelo.addColumn("TIPO_DOC");
        modelo.addColumn("DOCUMENTO");
        modelo.addColumn("APELLIDO1");
        modelo.addColumn("APELLIDO2");
        modelo.addColumn("NOMBRE1");
        modelo.addColumn("NOMBRE2");
        modelo.addColumn("SEXO");
        modelo.addColumn("FECHANAC");
        modelo.addColumn("DIRECCION");
        modelo.addColumn("TELEFONO");
        modelo.addColumn("COD_CIUDAD");
        modelo.addColumn("COD_ZONA");
        modelo.addColumn("CELULAR");
        modelo.addColumn("EMAIL");
        modelo.addColumn("COD_EXAMEN");
        modelo.addColumn("NOM_EXAMEN");
        modelo.addColumn("CANTIDAD");
        modelo.addColumn("NUM_PETICION");
        modelo.addColumn("PISO");
        modelo.addColumn("EN_EMBARAZO");
        modelo.addColumn("TIPO_USUARIO");
        modelo.addColumn("TIPOSER");
        modelo.addColumn("COD_MEDICO");
        modelo.addColumn("NOM_MEDICO");
        modelo.addColumn("COD_CLIENTE");
        modelo.addColumn("NOM_CLIENTE");
        modelo.addColumn("COD_CENCOS");
        modelo.addColumn("NOM_CENCOS");
        modelo.addColumn("COD_SEDE");
        modelo.addColumn("ESTADO");
        try {
            rs = conn.createStatement().executeQuery("SELECT * FROM LABO_ORD ORDER BY ID ASC");
            while (rs.next()) {
                Object fila[] = new Object[34];
                for (int i = 0; i < 34; i++) {
                    fila[i] = rs.getObject(i + 1);
                }
                modelo.addRow(fila);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "LABO_ORDDao:\n:" + ex.toString());
        }
        return modelo;
    }

    public DefaultTableModel listarOrdenesEnCero() {
        String col[] = {"N. ADMISION", "N. ORDEN", "FECHA", "TIPO DOC", "DOCUMENTO", "APELLIDO", "NOMBRE", "COD EXAMEN", "NOM EXAMEN", "COD CENCOS", "NOM CENCOS", "ESTADO"};
        DefaultTableModel modelo = new DefaultTableModel();
        ResultSet rs;
        modelo.addColumn("ID");
        modelo.addColumn("NOADMISION");
        modelo.addColumn("NUM_ORDEN");
        modelo.addColumn("FECHA_RESULTADO");
        modelo.addColumn("TIPO_DOC");
        modelo.addColumn("DOCUMENTO");
        modelo.addColumn("APELLIDO1");
        modelo.addColumn("APELLIDO2");
        modelo.addColumn("NOMBRE1");
        modelo.addColumn("NOMBRE2");
        modelo.addColumn("SEXO");
        modelo.addColumn("FECHANAC");
        modelo.addColumn("DIRECCION");
        modelo.addColumn("TELEFONO");
        modelo.addColumn("COD_CIUDAD");
        modelo.addColumn("COD_ZONA");
        modelo.addColumn("CELULAR");
        modelo.addColumn("EMAIL");
        modelo.addColumn("COD_EXAMEN");
        modelo.addColumn("NOM_EXAMEN");
        modelo.addColumn("CANTIDAD");
        modelo.addColumn("NUM_PETICION");
        modelo.addColumn("PISO");
        modelo.addColumn("EN_EMBARAZO");
        modelo.addColumn("TIPO_USUARIO");
        modelo.addColumn("TIPOSER");
        modelo.addColumn("COD_MEDICO");
        modelo.addColumn("NOM_MEDICO");
        modelo.addColumn("COD_CLIENTE");
        modelo.addColumn("NOM_CLIENTE");
        modelo.addColumn("COD_CENCOS");
        modelo.addColumn("NOM_CENCOS");
        modelo.addColumn("COD_SEDE");
        modelo.addColumn("ESTADO");
        try {
            rs = conn.createStatement().executeQuery("SELECT * FROM LABO_ORD WHERE ESTADO=0 ORDER BY ID ASC");
            while (rs.next()) {
                Object fila[] = new Object[34];
                for (int i = 0; i < 34; i++) {
                    fila[i] = rs.getObject(i + 1);
                }
                modelo.addRow(fila);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "LABO_ORDDao:\n" + ex.toString());
        }
        return modelo;
    }

    public DefaultTableModel listarOrdenesEnCero1() {
        String col[] = {"ID", "N. ADMISION", "N. ORDEN", "FECHA", "TIPO DOC", "DOCUMENTO", "APELLIDO", "NOMBRE", "COD EXAMEN", "NOM EXAMEN", "COD CENCOS", "NOM CENCOS", "ESTADO"};
        DefaultTableModel modelo = new DefaultTableModel(col, 0) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        this.conectar();
        ResultSet rs;
        try {
            rs = conn.createStatement().executeQuery("SELECT ID,NOADMISION,NUM_ORDEN,FECHA_RESULTADO,TIPO_DOC,DOCUMENTO,APELLIDO1,NOMBRE1,COD_EXAMEN,NOM_EXAMEN"
                    + ",COD_CENCOS,NOM_CENCOS,ESTADO FROM LABO_ORD WHERE ESTADO=0 ORDER BY ID ASC");
            while (rs.next()) {
                Object fila[] = new Object[col.length];
                for (int i = 0; i < fila.length; i++) {
                    fila[i] = rs.getObject(i + 1);
                }
                modelo.addRow(fila);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "LABO_ORDDao:\n" + ex.toString());
            this.desconectar();
        }
        this.desconectar();
        return modelo;
    }

    public ArrayList ordenesNoProcesadas(String sql) throws SQLException {
        ArrayList searchResults = new ArrayList();
        ResultSet result = null;
        try {
            result = conn.createStatement().executeQuery(sql);
            while (result.next()) {
                LABO_ORD temp = createValueObject();
                temp.setID(result.getInt("ID"));
                temp.setNOADMISION(result.getString("NOADMISION"));
                temp.setNUM_ORDEN(result.getString("NUM_ORDEN"));
                temp.setFECHA_RESULTADO(result.getTimestamp("FECHA_RESULTADO"));
                temp.setTIPO_DOC(result.getString("TIPO_DOC"));
                temp.setDOCUMENTO(result.getString("DOCUMENTO"));
                temp.setAPELLIDO1(result.getString("APELLIDO1"));
                temp.setAPELLIDO2(result.getString("APELLIDO2"));
                temp.setNOMBRE1(result.getString("NOMBRE1"));
                temp.setNOMBRE2(result.getString("NOMBRE2"));
                temp.setSEXO(result.getString("SEXO"));
                temp.setFECHANAC(result.getTimestamp("FECHANAC"));
                temp.setDIRECCION(result.getString("DIRECCION"));
                temp.setTELEFONO(result.getString("TELEFONO"));
                temp.setCOD_CIUDAD(result.getString("COD_CIUDAD"));
                temp.setCOD_ZONA(result.getString("COD_ZONA"));
                temp.setCELULAR(result.getString("CELULAR"));
                temp.setEMAIL(result.getString("EMAIL"));
                temp.setCOD_EXAMEN(result.getString("COD_EXAMEN"));
                temp.setNOM_EXAMEN(result.getString("NOM_EXAMEN"));
                temp.setCANTIDAD(result.getInt("CANTIDAD"));
                temp.setNUM_PETICION(result.getInt("NUM_PETICION"));
                temp.setPISO(result.getString("PISO"));
                temp.setEN_EMBARAZO(result.getInt("EN_EMBARAZO"));
                temp.setTIPO_USUARIO(result.getString("TIPO_USUARIO"));
                temp.setTIPOSER(result.getString("TIPOSER"));
                temp.setCOD_MEDICO(result.getString("COD_MEDICO"));
                temp.setNOM_MEDICO(result.getString("NOM_MEDICO"));
                temp.setCOD_CLIENTE(result.getString("COD_CLIENTE"));
                temp.setNOM_CLIENTE(result.getString("NOM_CLIENTE"));
                temp.setCOD_CENCOS(result.getString("COD_CENCOS"));
                temp.setNOM_CENCOS(result.getString("NOM_CENCOS"));
                temp.setCOD_SEDE(result.getString("COD_SEDE"));
                temp.setESTADO(result.getInt("ESTADO"));
                searchResults.add(temp);
            }
        } finally {
            if (result != null) {
                result.close();
            }
        }
        return searchResults;
    }

    public DefaultTableModel modeloTabla() {
        String col[] = {"ID", "N. ADMISION", "N. ORDEN", "FECHA", "TIPO DOC", "DOCUMENTO", "APELLIDO", "NOMBRE", "COD EXAMEN", "NOM EXAMEN", "COD CENCOS", "NOM CENCOS", "ESTADO"};
        DefaultTableModel modelo = new DefaultTableModel(col, 1) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        modelo.addRow(new Object[]{"", "", "", "", "", "", "", "", "", "", "", "", ""});
        return modelo;
    }

    public String retornaDocumento(Connection c, String num_orden) {
        String sql = "SELECT DOCUMENTO FROM LABO_ORD WHERE NUM_ORDEN=?";
        PreparedStatement stmt = null;
        ResultSet rs;
        try {
            stmt = c.prepareStatement(sql);
            stmt.setString(1, num_orden);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("DOCUMENTO");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error al consultar la \ntabla Ciudades de la Bd Winsislab:\n" + ex.toString());
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                    //rs.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error al intentar cerrar\nel Statement de la tabla numeros_nousados:\n" + ex.toString());
                }
            }
        }
        return null;
    }

}
