package Controler;

/**
 * Eventos_paciente_exam Data Access Object (DAO).
 *
 * @author fberrocalm Creado en: 17/02/2015
 */
import Herramientas.DatosConexion;
import Model.Eventos_paciente_exam;
import java.sql.*;
import java.util.*;
import javax.swing.JOptionPane;

public class Eventos_paciente_examDao {

    //1
    DatosConexion datosconexion;

    public Eventos_paciente_examDao() {
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
            JOptionPane.showMessageDialog(null, "Eventos_paciente_examDao:\n" + ex.getMessage());
        }
    }

    public Eventos_paciente_exam createValueObject() {
        return new Eventos_paciente_exam();
    }

    public Eventos_paciente_exam getObject(String paciente_cod) throws NotFoundException, SQLException {

        Eventos_paciente_exam valueObject = createValueObject();
        valueObject.setPaciente_cod(paciente_cod);
        load(valueObject);
        return valueObject;
    }

    public void load(Eventos_paciente_exam valueObject) throws NotFoundException, SQLException {

        if (valueObject.getPaciente_cod() == null) {
            //System.out.println("Can not select without Primary-Key!");
            throw new NotFoundException("Eventos_paciente_examDao:\nCan not select without Primary-Key!");
        }

        String sql = "SELECT * FROM eventos_paciente_exam WHERE (paciente_cod = ? ) ";
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

        String sql = "SELECT * FROM eventos_paciente_exam ORDER BY paciente_cod ASC ";
        List searchResults = listQuery(conn.prepareStatement(sql));

        return searchResults;
    }

    public synchronized void create(Eventos_paciente_exam valueObject) throws SQLException {
        String sql = "";
        PreparedStatement stmt = null;
        try {
            sql = "INSERT INTO eventos_paciente_exam ( paciente_cod, hora, fecha, "
                    + "sede_codigo, historia, tipodcto_cod, "
                    + "nit, examen, reg_exa, "
                    + "tipo_even_cod, fecha_event, hora_event, "
                    + "observ_event, usr_codigo, activo, "
                    + "secuencia) VALUES (?, ?::time, ?::date, ?, ?, ?, ?, ?, ?, ?, ?::date, ?::time, ?, ?, ?, ?) ";
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, valueObject.getPaciente_cod());
            stmt.setTime(2, valueObject.getHora());
            stmt.setObject(3, valueObject.getFecha());
            stmt.setString(4, valueObject.getSede_codigo());
            stmt.setString(5, valueObject.getHistoria());
            stmt.setString(6, valueObject.getTipodcto_cod());
            stmt.setString(7, valueObject.getNit());
            stmt.setString(8, valueObject.getExamen());
            stmt.setInt(9, valueObject.getReg_exa());
            stmt.setString(10, valueObject.getTipo_even_cod());
            stmt.setObject(11, valueObject.getFecha_event());
            stmt.setTime(12, valueObject.getHora_event());
            stmt.setString(13, valueObject.getObserv_event());
            stmt.setString(14, valueObject.getUsr_codigo());
            stmt.setBoolean(15, valueObject.getActivo());
            stmt.setInt(16, valueObject.getSecuencia());

            int rowcount = databaseUpdate(stmt);
            if (rowcount != 1) {
                //System.out.println("PrimaryKey Error when updating DB!");
                throw new SQLException("Eventos_paciente_examDao:\nPrimaryKey Error when updating DB!");
            }
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public void save(Eventos_paciente_exam valueObject) throws NotFoundException, SQLException {

        String sql = "UPDATE eventos_paciente_exam SET hora = ?::time, fecha = ?::date, sede_codigo = ?, "
                + "historia = ?, tipodcto_cod = ?, nit = ?, "
                + "examen = ?, reg_exa = ?, tipo_even_cod = ?, "
                + "fecha_event = ?::time, hora_event = ?::date, observ_event = ?, "
                + "usr_codigo = ?, activo = ?, secuencia = ? WHERE (paciente_cod = ? ) ";
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setTime(1, valueObject.getHora());
            stmt.setObject(2, valueObject.getFecha());
            stmt.setString(3, valueObject.getSede_codigo());
            stmt.setString(4, valueObject.getHistoria());
            stmt.setString(5, valueObject.getTipodcto_cod());
            stmt.setString(6, valueObject.getNit());
            stmt.setString(7, valueObject.getExamen());
            stmt.setInt(8, valueObject.getReg_exa());
            stmt.setString(9, valueObject.getTipo_even_cod());
            stmt.setObject(10, valueObject.getFecha_event());
            stmt.setTime(11, valueObject.getHora_event());
            stmt.setString(12, valueObject.getObserv_event());
            stmt.setString(13, valueObject.getUsr_codigo());
            stmt.setBoolean(14, valueObject.getActivo());
            stmt.setInt(15, valueObject.getSecuencia());

            stmt.setString(16, valueObject.getPaciente_cod());

            int rowcount = databaseUpdate(stmt);
            if (rowcount == 0) {
                //System.out.println("Object could not be saved! (PrimaryKey not found)");
                throw new NotFoundException("Eventos_paciente_examDao:\nObject could not be saved! (PrimaryKey not found)");
            }
            if (rowcount > 1) {
                //System.out.println("PrimaryKey Error when updating DB! (Many objects were affected!)");
                throw new SQLException("Eventos_paciente_examDao:\nPrimaryKey Error when updating DB! (Many objects were affected!)");
            }
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public void delete(Eventos_paciente_exam valueObject)
            throws NotFoundException, SQLException {

        if (valueObject.getPaciente_cod() == null) {
            //System.out.println("Can not delete without Primary-Key!");
            throw new NotFoundException("Eventos_paciente_examDao:\nCan not delete without Primary-Key!");
        }

        String sql = "DELETE FROM eventos_paciente_exam WHERE (paciente_cod = ? ) ";
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, valueObject.getPaciente_cod());

            int rowcount = databaseUpdate(stmt);
            if (rowcount == 0) {
                //System.out.println("Object could not be deleted (PrimaryKey not found)");
                throw new NotFoundException("Eventos_paciente_examDao:\nObject could not be deleted! (PrimaryKey not found)");
            }
            if (rowcount > 1) {
                //System.out.println("PrimaryKey Error when updating DB! (Many objects were deleted!)");
                throw new SQLException("Eventos_paciente_examDao:\nPrimaryKey Error when updating DB! (Many objects were deleted!)");
            }
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public void deleteAll() throws SQLException {

        String sql = "DELETE FROM eventos_paciente_exam";
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

    protected void singleQuery(PreparedStatement stmt, Eventos_paciente_exam valueObject)
            throws NotFoundException, SQLException {

        ResultSet result = null;

        try {
            result = stmt.executeQuery();

            if (result.next()) {

                valueObject.setPaciente_cod(result.getString("paciente_cod"));
                valueObject.setHora(result.getTime("hora"));
                valueObject.setFecha(result.getDate("fecha"));
                valueObject.setSede_codigo(result.getString("sede_codigo"));
                valueObject.setHistoria(result.getString("historia"));
                valueObject.setTipodcto_cod(result.getString("tipodcto_cod"));
                valueObject.setNit(result.getString("nit"));
                valueObject.setExamen(result.getString("examen"));
                valueObject.setReg_exa(result.getInt("reg_exa"));
                valueObject.setTipo_even_cod(result.getString("tipo_even_cod"));
                valueObject.setFecha_event(result.getDate("fecha_event"));
                valueObject.setHora_event(result.getTime("hora_event"));
                valueObject.setObserv_event(result.getString("observ_event"));
                valueObject.setUsr_codigo(result.getString("usr_codigo"));
                valueObject.setActivo(result.getBoolean("activo"));
                valueObject.setSecuencia(result.getInt("secuencia"));

            } else {
                //System.out.println("Eventos_paciente_exam Object Not Found!");
                throw new NotFoundException("Eventos_paciente_examDao:\nEventos_paciente_exam Object Not Found!");
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
                Eventos_paciente_exam temp = createValueObject();

                temp.setPaciente_cod(result.getString("paciente_cod"));
                temp.setHora(result.getTime("hora"));
                temp.setFecha(result.getDate("fecha"));
                temp.setSede_codigo(result.getString("sede_codigo"));
                temp.setHistoria(result.getString("historia"));
                temp.setTipodcto_cod(result.getString("tipodcto_cod"));
                temp.setNit(result.getString("nit"));
                temp.setExamen(result.getString("examen"));
                temp.setReg_exa(result.getInt("reg_exa"));
                temp.setTipo_even_cod(result.getString("tipo_even_cod"));
                temp.setFecha_event(result.getDate("fecha_event"));
                temp.setHora_event(result.getTime("hora_event"));
                temp.setObserv_event(result.getString("observ_event"));
                temp.setUsr_codigo(result.getString("usr_codigo"));
                temp.setActivo(result.getBoolean("activo"));
                temp.setSecuencia(result.getInt("secuencia"));

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

    public boolean existe_eventos_paciente_exam(String cod_pac, java.util.Date fecha, String examen, int reg) {
        String sql = "select * from eventos_paciente_exam where paciente_cod=? and fecha=? and sede_codigo='40' "
                + "and examen=? and reg_exa=?";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, cod_pac);
            stmt.setObject(2, fecha);
            stmt.setObject(3, examen);
            stmt.setInt(4, reg);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Eventos_paciente_examDao:\n" + ex.toString());
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
//                    rs.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Eventos_paciente_examDao:\n" + ex.toString());
                }
            }
        }
        return false;
    }

}
