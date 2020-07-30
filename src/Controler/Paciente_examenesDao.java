package Controler;

/**
 * Paciente_examenes Data Access Object (DAO).
 *
 * @author fberrocalm creado en: 16/02/2015
 */
import Herramientas.DatosConexion;
import Model.Paciente_examenes;
import java.sql.*;
import java.util.*;
import javax.swing.JOptionPane;

public class Paciente_examenesDao {

    //1
    DatosConexion datosconexion;

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
            JOptionPane.showMessageDialog(null, "Paciente_examenesDao:\n" + ex.getMessage());
        }
    }

    public Paciente_examenes createValueObject() {
        return new Paciente_examenes();
    }

    public Paciente_examenesDao() {
        datosconexion = new DatosConexion();
    }

    public Paciente_examenes getObject(String paciente_cod) throws NotFoundException, SQLException {

        Paciente_examenes valueObject = createValueObject();
        valueObject.setPaciente_cod(paciente_cod);
        load(valueObject);
        return valueObject;
    }

    public void load(Paciente_examenes valueObject) throws NotFoundException, SQLException {

        if (valueObject.getPaciente_cod() == null) {
            //System.out.println("Can not select without Primary-Key!");
            throw new NotFoundException("Paciente_examenesDao:\nCan not select without Primary-Key!");
        }

        String sql = "SELECT * FROM paciente_examenes WHERE (paciente_cod = ? ) ";
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

        String sql = "SELECT * FROM paciente_examenes ORDER BY paciente_cod ASC ";
        List searchResults = listQuery(conn.prepareStatement(sql));

        return searchResults;
    }

    public synchronized void create(Paciente_examenes valueObject) throws SQLException {
        String sql = "";
        PreparedStatement stmt = null;
        ResultSet result = null;
        try {
            sql = "INSERT INTO paciente_examenes ( paciente_cod, hora, fecha, "
                    + "sede_codigo, historia, tipodcto_cod, "
                    + "nit, examen, reg_exa, "
                    + "activo, valor, alterno, "
                    + "entrega, autoriza, clase, "
                    + "plan, si_recsuero, si_impcodbar, "
                    + "si_band01, si_band02, si_band03, "
                    + "si_band04, si_band05, si_band06, "
                    + "si_band07, si_band08, copias, "
                    + "activo_exa, contestado, validado, "
                    + "modificado, impreso, entregado, "
                    + "fec_res, fec_val, fec_imp, "
                    + "fec_imp1, fec_mod, fec_ent, "
                    + "hora_resp, hora_val, hora_imp, "
                    + "hora_imp1, hora_mod, hora_ent, "
                    + "parte_anal, respondido_por, validado_por, "
                    + "repitio, sedepro, usr_copio, "
                    + "archivo, num_cita, exig_sda_firma, "
                    + "usr_sda_firma, "
                    + "fecha_prometida, ultimo_previo, "
                    + "val_parcial, fec_val_parcial, hora_val_parcial, "
                    + "usu_val_parcial, nro_muestra1, nro_muestra2, "
                    + "codigo_barras, fecha_recepcion) VALUES (?, ?::time, ?::date, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?::date, ?, ?, ?, ?::time, ?, ?, ?, ?, ?::date) ";
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
            stmt.setBoolean(10, valueObject.getActivo());
            stmt.setDouble(11, valueObject.getValor());
            stmt.setString(12, valueObject.getAlterno());
            stmt.setObject(13, valueObject.getEntrega());
            stmt.setString(14, valueObject.getAutoriza());
            stmt.setString(15, valueObject.getClase());
            stmt.setString(16, valueObject.getPlan());
            stmt.setBoolean(17, valueObject.getSi_recsuero());
            stmt.setBoolean(18, valueObject.getSi_impcodbar());
            stmt.setBoolean(19, valueObject.getSi_band01());
            stmt.setBoolean(20, valueObject.getSi_band02());
            stmt.setBoolean(21, valueObject.getSi_band03());
            stmt.setBoolean(22, valueObject.getSi_band04());
            stmt.setBoolean(23, valueObject.getSi_band05());
            stmt.setBoolean(24, valueObject.getSi_band06());
            stmt.setBoolean(25, valueObject.getSi_band07());
            stmt.setBoolean(26, valueObject.getSi_band08());
            stmt.setInt(27, valueObject.getCopias());
            stmt.setInt(28, valueObject.getActivo_exa());
            stmt.setBoolean(29, valueObject.getContestado());
            stmt.setBoolean(30, valueObject.getValidado());
            stmt.setBoolean(31, valueObject.getModificado());
            stmt.setBoolean(32, valueObject.getImpreso());
            stmt.setBoolean(33, valueObject.getEntregado());
            stmt.setObject(34, valueObject.getFec_res());
            stmt.setObject(35, valueObject.getFec_val());
            stmt.setObject(36, valueObject.getFec_imp());
            stmt.setObject(37, valueObject.getFec_imp1());
            stmt.setObject(38, valueObject.getFec_mod());
            stmt.setObject(39, valueObject.getFec_ent());
            stmt.setTime(40, valueObject.getHora_resp());
            stmt.setTime(41, valueObject.getHora_val());
            stmt.setTime(42, valueObject.getHora_imp());
            stmt.setTime(43, valueObject.getHora_imp1());
            stmt.setTime(44, valueObject.getHora_mod());
            stmt.setObject(45, valueObject.getHora_ent());
            stmt.setString(46, valueObject.getParte_anal());
            stmt.setString(47, valueObject.getRespondido_por());
            stmt.setString(48, valueObject.getValidado_por());
            stmt.setInt(49, valueObject.getRepitio());
            stmt.setString(50, valueObject.getSedepro());
            stmt.setString(51, valueObject.getUsr_copio());
            stmt.setString(52, valueObject.getArchivo());
            stmt.setString(53, valueObject.getNum_cita());
            stmt.setBoolean(54, valueObject.getExig_sda_firma());
            stmt.setString(55, valueObject.getUsr_sda_firma());
            stmt.setObject(56, valueObject.getFecha_prometida());
            stmt.setInt(57, valueObject.getUltimo_previo());
            stmt.setBoolean(58, valueObject.getVal_parcial());
            stmt.setObject(59, valueObject.getFec_val_parcial());
            stmt.setTime(60, valueObject.getHora_val_parcial());
            stmt.setString(61, valueObject.getUsu_val_parcial());
            stmt.setString(62, valueObject.getNro_muestra1());
            stmt.setString(63, valueObject.getNro_muestra2());
            stmt.setString(64, valueObject.getCodigo_barras());
            stmt.setObject(65, valueObject.getFecha_recepcion());
            int rowcount = databaseUpdate(stmt);
            if (rowcount != 1) {
                //System.out.println("PrimaryKey Error when updating DB!");
                throw new SQLException("Paciente_examenesDao:\nPrimaryKey Error when updating DB!");
            }
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public void save(Paciente_examenes valueObject) throws NotFoundException, SQLException {

        String sql = "UPDATE paciente_examenes SET hora = ?::time, fecha = ?::date, sede_codigo = ?, "
                + "historia = ?, tipodcto_cod = ?, nit = ?, "
                + "examen = ?, reg_exa = ?, activo = ?, "
                + "valor = ?, alterno = ?, entrega = ?, "
                + "autoriza = ?, clase = ?, plan = ?, "
                + "si_recsuero = ?, si_impcodbar = ?, si_band01 = ?, "
                + "si_band02 = ?, si_band03 = ?, si_band04 = ?, "
                + "si_band05 = ?, si_band06 = ?, si_band07 = ?, "
                + "si_band08 = ?, copias = ?, activo_exa = ?, "
                + "contestado = ?, validado = ?, modificado = ?, "
                + "impreso = ?, entregado = ?, fec_res = ?, "
                + "fec_val = ?, fec_imp = ?, fec_imp1 = ?, "
                + "fec_mod = ?, fec_ent = ?, hora_resp = ?, "
                + "hora_val = ?, hora_imp = ?, hora_imp1 = ?, "
                + "hora_mod = ?, hora_ent = ?, parte_anal = ?, "
                + "respondido_por = ?, validado_por = ?, repitio = ?, "
                + "sedepro = ?, usr_copio = ?, archivo = ?, "
                + "num_cita = ?, exig_sda_firma = ?, usr_sda_firma = ?, "
                + "fecha_prometida = ?::date, ultimo_previo = ?, val_parcial = ?, "
                + "fec_val_parcial = ?::date, hora_val_parcial = ?::time, usu_val_parcial = ?, "
                + "nro_muestra1 = ?, nro_muestra2 = ?, codigo_barras = ?, "
                + "fecha_recepcion = ?::date WHERE (paciente_cod = ? ) ";
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
            stmt.setBoolean(9, valueObject.getActivo());
            stmt.setDouble(10, valueObject.getValor());
            stmt.setString(11, valueObject.getAlterno());
            stmt.setObject(12, valueObject.getEntrega());
            stmt.setString(13, valueObject.getAutoriza());
            stmt.setString(14, valueObject.getClase());
            stmt.setString(15, valueObject.getPlan());
            stmt.setBoolean(16, valueObject.getSi_recsuero());
            stmt.setBoolean(17, valueObject.getSi_impcodbar());
            stmt.setBoolean(18, valueObject.getSi_band01());
            stmt.setBoolean(19, valueObject.getSi_band02());
            stmt.setBoolean(20, valueObject.getSi_band03());
            stmt.setBoolean(21, valueObject.getSi_band04());
            stmt.setBoolean(22, valueObject.getSi_band05());
            stmt.setBoolean(23, valueObject.getSi_band06());
            stmt.setBoolean(24, valueObject.getSi_band07());
            stmt.setBoolean(25, valueObject.getSi_band08());
            stmt.setInt(26, valueObject.getCopias());
            stmt.setInt(27, valueObject.getActivo_exa());
            stmt.setBoolean(28, valueObject.getContestado());
            stmt.setBoolean(29, valueObject.getValidado());
            stmt.setBoolean(30, valueObject.getModificado());
            stmt.setBoolean(31, valueObject.getImpreso());
            stmt.setBoolean(32, valueObject.getEntregado());
            stmt.setObject(33, valueObject.getFec_res());
            stmt.setObject(34, valueObject.getFec_val());
            stmt.setObject(35, valueObject.getFec_imp());
            stmt.setObject(36, valueObject.getFec_imp1());
            stmt.setObject(37, valueObject.getFec_mod());
            stmt.setObject(38, valueObject.getFec_ent());
            stmt.setTime(39, valueObject.getHora_resp());
            stmt.setTime(40, valueObject.getHora_val());
            stmt.setTime(41, valueObject.getHora_imp());
            stmt.setTime(42, valueObject.getHora_imp1());
            stmt.setTime(43, valueObject.getHora_mod());
            stmt.setObject(44, valueObject.getHora_ent());
            stmt.setString(45, valueObject.getParte_anal());
            stmt.setString(46, valueObject.getRespondido_por());
            stmt.setString(47, valueObject.getValidado_por());
            stmt.setInt(48, valueObject.getRepitio());
            stmt.setString(49, valueObject.getSedepro());
            stmt.setString(50, valueObject.getUsr_copio());
            stmt.setString(51, valueObject.getArchivo());
            stmt.setString(52, valueObject.getNum_cita());
            stmt.setBoolean(53, valueObject.getExig_sda_firma());
            stmt.setString(54, valueObject.getUsr_sda_firma());
            stmt.setObject(55, valueObject.getFecha_prometida());
            stmt.setInt(56, valueObject.getUltimo_previo());
            stmt.setBoolean(57, valueObject.getVal_parcial());
            stmt.setObject(58, valueObject.getFec_val_parcial());
            stmt.setTime(59, valueObject.getHora_val_parcial());
            stmt.setString(60, valueObject.getUsu_val_parcial());
            stmt.setString(61, valueObject.getNro_muestra1());
            stmt.setString(62, valueObject.getNro_muestra2());
            stmt.setString(63, valueObject.getCodigo_barras());
            stmt.setObject(64, valueObject.getFecha_recepcion());
            stmt.setString(65, valueObject.getPaciente_cod());

            int rowcount = databaseUpdate(stmt);
            if (rowcount == 0) {
                //System.out.println("Object could not be saved! (PrimaryKey not found)");
                throw new NotFoundException("Paciente_examenesDao:\nObject could not be saved! (PrimaryKey not found)");
            }
            if (rowcount > 1) {
                //System.out.println("PrimaryKey Error when updating DB! (Many objects were affected!)");
                throw new SQLException("Paciente_examenesDao:\nPrimaryKey Error when updating DB! (Many objects were affected!)");
            }
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public void delete(Paciente_examenes valueObject) throws NotFoundException, SQLException {

        if (valueObject.getPaciente_cod() == null) {
            //System.out.println("Can not delete without Primary-Key!");
            throw new NotFoundException("Paciente_examenesDao:\nCan not delete without Primary-Key!");
        }

        String sql = "DELETE FROM paciente_examenes WHERE (paciente_cod = ? ) ";
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, valueObject.getPaciente_cod());

            int rowcount = databaseUpdate(stmt);
            if (rowcount == 0) {
                //System.out.println("Object could not be deleted (PrimaryKey not found)");
                throw new NotFoundException("Paciente_examenesDao:\nObject could not be deleted! (PrimaryKey not found)");
            }
            if (rowcount > 1) {
                //System.out.println("PrimaryKey Error when updating DB! (Many objects were deleted!)");
                throw new SQLException("Paciente_examenesDao:\nPrimaryKey Error when updating DB! (Many objects were deleted!)");
            }
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public void deleteAll() throws SQLException {

        String sql = "DELETE FROM paciente_examenes";
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

    protected void singleQuery(PreparedStatement stmt, Paciente_examenes valueObject) throws NotFoundException, SQLException {

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
                valueObject.setActivo(result.getBoolean("activo"));
                valueObject.setValor(result.getDouble("valor"));
                valueObject.setAlterno(result.getString("alterno"));
                valueObject.setEntrega(result.getDate("entrega"));
                valueObject.setAutoriza(result.getString("autoriza"));
                valueObject.setClase(result.getString("clase"));
                valueObject.setPlan(result.getString("plan"));
                valueObject.setSi_recsuero(result.getBoolean("si_recsuero"));
                valueObject.setSi_impcodbar(result.getBoolean("si_impcodbar"));
                valueObject.setSi_band01(result.getBoolean("si_band01"));
                valueObject.setSi_band02(result.getBoolean("si_band02"));
                valueObject.setSi_band03(result.getBoolean("si_band03"));
                valueObject.setSi_band04(result.getBoolean("si_band04"));
                valueObject.setSi_band05(result.getBoolean("si_band05"));
                valueObject.setSi_band06(result.getBoolean("si_band06"));
                valueObject.setSi_band07(result.getBoolean("si_band07"));
                valueObject.setSi_band08(result.getBoolean("si_band08"));
                valueObject.setCopias(result.getInt("copias"));
                valueObject.setActivo_exa(result.getInt("activo_exa"));
                valueObject.setContestado(result.getBoolean("contestado"));
                valueObject.setValidado(result.getBoolean("validado"));
                valueObject.setModificado(result.getBoolean("modificado"));
                valueObject.setImpreso(result.getBoolean("impreso"));
                valueObject.setEntregado(result.getBoolean("entregado"));
                valueObject.setFec_res(result.getDate("fec_res"));
                valueObject.setFec_val(result.getDate("fec_val"));
                valueObject.setFec_imp(result.getDate("fec_imp"));
                valueObject.setFec_imp1(result.getDate("fec_imp1"));
                valueObject.setFec_mod(result.getDate("fec_mod"));
                valueObject.setFec_ent(result.getDate("fec_ent"));
                valueObject.setHora_resp(result.getTime("hora_resp"));
                valueObject.setHora_val(result.getTime("hora_val"));
                valueObject.setHora_imp(result.getTime("hora_imp"));
                valueObject.setHora_imp1(result.getTime("hora_imp1"));
                valueObject.setHora_mod(result.getTime("hora_mod"));
                valueObject.setHora_ent(result.getTime("hora_ent"));
                valueObject.setParte_anal(result.getString("parte_anal"));
                valueObject.setRespondido_por(result.getString("respondido_por"));
                valueObject.setValidado_por(result.getString("validado_por"));
                valueObject.setRepitio(result.getInt("repitio"));
                valueObject.setSedepro(result.getString("sedepro"));
                valueObject.setUsr_copio(result.getString("usr_copio"));
                valueObject.setArchivo(result.getString("archivo"));
                valueObject.setNum_cita(result.getString("num_cita"));
                valueObject.setExig_sda_firma(result.getBoolean("exig_sda_firma"));
                valueObject.setUsr_sda_firma(result.getString("usr_sda_firma"));
                valueObject.setFecha_prometida(result.getDate("fecha_prometida"));
                valueObject.setUltimo_previo(result.getInt("ultimo_previo"));
                valueObject.setVal_parcial(result.getBoolean("val_parcial"));
                valueObject.setFec_val_parcial(result.getDate("fec_val_parcial"));
                valueObject.setHora_val_parcial(result.getTime("hora_val_parcial"));
                valueObject.setUsu_val_parcial(result.getString("usu_val_parcial"));
                valueObject.setNro_muestra1(result.getString("nro_muestra1"));
                valueObject.setNro_muestra2(result.getString("nro_muestra2"));
                valueObject.setCodigo_barras(result.getString("codigo_barras"));
                valueObject.setFecha_recepcion(result.getDate("fecha_recepcion"));
            } else {
                //System.out.println("Paciente_examenes Object Not Found!");
                throw new NotFoundException("Paciente_examenesDao:\nPaciente_examenes Object Not Found!");
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
                Paciente_examenes temp = createValueObject();

                temp.setPaciente_cod(result.getString("paciente_cod"));
                temp.setHora(result.getTime("hora"));
                temp.setFecha(result.getDate("fecha"));
                temp.setSede_codigo(result.getString("sede_codigo"));
                temp.setHistoria(result.getString("historia"));
                temp.setTipodcto_cod(result.getString("tipodcto_cod"));
                temp.setNit(result.getString("nit"));
                temp.setExamen(result.getString("examen"));
                temp.setReg_exa(result.getInt("reg_exa"));
                temp.setActivo(result.getBoolean("activo"));
                temp.setValor(result.getDouble("valor"));
                temp.setAlterno(result.getString("alterno"));
                temp.setEntrega(result.getDate("entrega"));
                temp.setAutoriza(result.getString("autoriza"));
                temp.setClase(result.getString("clase"));
                temp.setPlan(result.getString("plan"));
                temp.setSi_recsuero(result.getBoolean("si_recsuero"));
                temp.setSi_impcodbar(result.getBoolean("si_impcodbar"));
                temp.setSi_band01(result.getBoolean("si_band01"));
                temp.setSi_band02(result.getBoolean("si_band02"));
                temp.setSi_band03(result.getBoolean("si_band03"));
                temp.setSi_band04(result.getBoolean("si_band04"));
                temp.setSi_band05(result.getBoolean("si_band05"));
                temp.setSi_band06(result.getBoolean("si_band06"));
                temp.setSi_band07(result.getBoolean("si_band07"));
                temp.setSi_band08(result.getBoolean("si_band08"));
                temp.setCopias(result.getInt("copias"));
                temp.setActivo_exa(result.getInt("activo_exa"));
                temp.setContestado(result.getBoolean("contestado"));
                temp.setValidado(result.getBoolean("validado"));
                temp.setModificado(result.getBoolean("modificado"));
                temp.setImpreso(result.getBoolean("impreso"));
                temp.setEntregado(result.getBoolean("entregado"));
                temp.setFec_res(result.getDate("fec_res"));
                temp.setFec_val(result.getDate("fec_val"));
                temp.setFec_imp(result.getDate("fec_imp"));
                temp.setFec_imp1(result.getDate("fec_imp1"));
                temp.setFec_mod(result.getDate("fec_mod"));
                temp.setFec_ent(result.getDate("fec_ent"));
                temp.setHora_resp(result.getTime("hora_resp"));
                temp.setHora_val(result.getTime("hora_val"));
                temp.setHora_imp(result.getTime("hora_imp"));
                temp.setHora_imp1(result.getTime("hora_imp1"));
                temp.setHora_mod(result.getTime("hora_mod"));
                temp.setHora_ent(result.getTime("hora_ent"));
                temp.setParte_anal(result.getString("parte_anal"));
                temp.setRespondido_por(result.getString("respondido_por"));
                temp.setValidado_por(result.getString("validado_por"));
                temp.setRepitio(result.getInt("repitio"));
                temp.setSedepro(result.getString("sedepro"));
                temp.setUsr_copio(result.getString("usr_copio"));
                temp.setArchivo(result.getString("archivo"));
                temp.setNum_cita(result.getString("num_cita"));
                temp.setExig_sda_firma(result.getBoolean("exig_sda_firma"));
                temp.setUsr_sda_firma(result.getString("usr_sda_firma"));
                temp.setFecha_prometida(result.getDate("fecha_prometida"));
                temp.setUltimo_previo(result.getInt("ultimo_previo"));
                temp.setVal_parcial(result.getBoolean("val_parcial"));
                temp.setFec_val_parcial(result.getDate("fec_val_parcial"));
                temp.setHora_val_parcial(result.getTime("hora_val_parcial"));
                temp.setUsu_val_parcial(result.getString("usu_val_parcial"));
                temp.setNro_muestra1(result.getString("nro_muestra1"));
                temp.setNro_muestra2(result.getString("nro_muestra2"));
                temp.setCodigo_barras(result.getString("codigo_barras"));
                temp.setFecha_recepcion(result.getDate("fecha_recepcion"));

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

    public boolean existe_paciente_examenes(String consecutivo, java.util.Date fecha, String examen, int reg, String csede) {
        String sql = "select * from paciente_examenes where paciente_cod=? and fecha=? and sede_codigo=? and examen=? and reg_exa=?";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, consecutivo);
            stmt.setObject(2, fecha);
            stmt.setString(3, csede);
            stmt.setObject(4, examen);
            stmt.setInt(5, reg);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error tabla pacinete_examens \n al intentar ejecutar la consulta\n" + ex.toString());
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                    //rs.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Paciente_examenesDao:\n" + ex.toString());
                }
            }
        }
        return false;
    }

}
