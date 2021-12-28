package Controler;

/**
 * Paciente Data Access Object (DAO).
 *
 * @author fberrocalm creado en: 13/02/2015
 */
import Herramientas.DatosConexion;
import Model.Paciente;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.JOptionPane;

public class PacienteDao {

    SimpleDateFormat dtf = new SimpleDateFormat("yyyy-MM-dd");
    
    //1
    DatosConexion datosconexion;
    Connection conn;

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public PacienteDao() {
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
            JOptionPane.showMessageDialog(null, "PacienteDao:\n" + ex.getMessage());
        }
    }

    public Paciente createValueObject() {
        return new Paciente();
    }

    public Paciente getObject(String paciente_cod) throws NotFoundException, SQLException {
        Paciente valueObject = createValueObject();
        valueObject.setPaciente_cod(paciente_cod);
        load(valueObject);
        return valueObject;
    }

    public void load(Paciente valueObject) throws NotFoundException, SQLException {

        if (valueObject.getPaciente_cod() == null) {
            throw new NotFoundException("PacienteDao:\nCan not select without Primary-Key!");
        }

        String sql = "SELECT * FROM paciente WHERE (paciente_cod = ? ) ";
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
        String sql = "SELECT * FROM paciente ORDER BY paciente_cod ASC ";
        List searchResults = listQuery(conn.prepareStatement(sql));
        return searchResults;
    }

    public synchronized void create(Paciente valueObject) throws SQLException {
        String sql = "";
        PreparedStatement stmt = null;
        ResultSet result = null;
        try {
            sql = "INSERT INTO paciente ( paciente_cod, hora, fecha, "
                    + "sede_codigo, historia, tipodcto_cod, "
                    + "nit, horario, urgencias, "
                    + "cat_codigo, ape1, nom1, "
                    + "acte_codigo, activo, fec_ent1, "
                    + "fec_ent2, hora_ent1, hora_ent2, "
                    + "sede_codigo_in, sede_codigo_re, direccion, "
                    + "telefono, medico_cod, medico, "
                    + "email, clte_codigo, nacio, "
                    + "edad, med_edad, sexo, "
                    + "observaciones, ccosto_cod, factura, "
                    + "telmed, facturado, tipo_a, "
                    + "fecha_fac, por_copago, vr_copago, "
                    + "abono1, abono2, tarifa, "
                    + "pordes, vr_total, desto, "
                    + "destop, cuotam, cod_enla1, "
                    + "cod_enla2, cod_enla3, caracteristicas, "
                    + "n_carnet, ciudad_cod, zona, "
                    + "n_fac, n_facdef, fecha_facdef, "
                    + "n_rec, autorizacion, turno_prec, "
                    + "bonos, soc_ppal, tel_soc, "
                    + "peso, talla, volumen12, "
                    + "volumen24, libl1, libl2, "
                    + "desctosr1, desctosr2, desctosr3, "
                    + "libr1, libr2, libr3, "
                    + "si_inactivo, si_remi, si_anul, "
                    + "si_factu, si_band01, si_band02, "
                    + "si_band03, si_band04, si_band05, "
                    + "si_band06, si_band07, si_band08, "
                    + "si_band09, si_band10, si_band11, "
                    + "si_band12, si_band13, si_band14, "
                    + "si_band15, si_band16, si_band17, "
                    + "si_band18, si_band19, si_envio_res_email, "
                    + "conse_emp, doc_socio, autorizo_des, "
                    + "celular, fecha_factura, "
                    + "usr_codigo, fteing_cod, cama_cod, "
                    + "fecha_grabo, hora_grabo, usu_facdef, "
                    + "num_remision, fecha_remision, usu_remision) VALUES (?, ?::time, ?::date, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?::date, ?::date, ?::time, ?::time, ?, ?, ?, ?, ?, ?, ?, ?, ?::date, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?::date, ?::time, ?::date, ?, ?, ?) ";
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, valueObject.getPaciente_cod());
            stmt.setTime(2, valueObject.getHora());
            stmt.setObject(3, valueObject.getFecha());
            stmt.setString(4, valueObject.getSede_codigo());
            stmt.setString(5, valueObject.getHistoria());
            stmt.setString(6, valueObject.getTipodcto_cod());
            stmt.setString(7, valueObject.getNit());
            stmt.setString(8, valueObject.getHorario());
            stmt.setBoolean(9, valueObject.getUrgencias());
            stmt.setString(10, valueObject.getCat_codigo());
            stmt.setString(11, valueObject.getApe1());
            stmt.setString(12, valueObject.getNom1());
            stmt.setString(13, valueObject.getActe_codigo());
            stmt.setBoolean(14, valueObject.getActivo());
            stmt.setObject(15, valueObject.getFec_ent1());
            stmt.setObject(16, valueObject.getFec_ent2());
            stmt.setTime(17, valueObject.getHora_ent1());
            stmt.setTime(18, valueObject.getHora_ent2());
            stmt.setString(19, valueObject.getSede_codigo_in());
            stmt.setString(20, valueObject.getSede_codigo_re());
            stmt.setString(21, valueObject.getDireccion());
            stmt.setString(22, valueObject.getTelefono());
            stmt.setString(23, valueObject.getMedico_cod());
            stmt.setString(24, valueObject.getMedico());
            stmt.setString(25, valueObject.getEmail());
            stmt.setString(26, valueObject.getClte_codigo());
            stmt.setObject(27, valueObject.getNacio());
            stmt.setInt(28, valueObject.getEdad());
            stmt.setString(29, valueObject.getMed_edad());
            stmt.setString(30, valueObject.getSexo());
            stmt.setString(31, valueObject.getObservaciones());
            stmt.setString(32, valueObject.getCcosto_cod());
            stmt.setString(33, valueObject.getFactura());
            stmt.setString(34, valueObject.getTelmed());
            stmt.setBoolean(35, valueObject.getFacturado());
            stmt.setString(36, valueObject.getTipo_a());
            stmt.setObject(37, valueObject.getFecha_fac());
            stmt.setDouble(38, valueObject.getPor_copago());
            stmt.setDouble(39, valueObject.getVr_copago());
            stmt.setDouble(40, valueObject.getAbono1());
            stmt.setDouble(41, valueObject.getAbono2());
            stmt.setString(42, valueObject.getTarifa());
            stmt.setDouble(43, valueObject.getPordes());
            stmt.setDouble(44, valueObject.getVr_total());
            stmt.setDouble(45, valueObject.getDesto());
            stmt.setDouble(46, valueObject.getDestop());
            stmt.setDouble(47, valueObject.getCuotam());
            stmt.setString(48, valueObject.getCod_enla1());
            stmt.setString(49, valueObject.getCod_enla2());
            stmt.setString(50, valueObject.getCod_enla3());
            stmt.setString(51, valueObject.getCaracteristicas());
            stmt.setString(52, valueObject.getN_carnet());
            stmt.setString(53, valueObject.getCiudad_cod());
            stmt.setString(54, valueObject.getZona());
            stmt.setString(55, valueObject.getN_fac());
            stmt.setString(56, valueObject.getN_facdef());
            stmt.setObject(57, valueObject.getFecha_facdef());
            stmt.setString(58, valueObject.getN_rec());
            stmt.setString(59, valueObject.getAutorizacion());
            stmt.setString(60, valueObject.getTurno_prec());
            stmt.setString(61, valueObject.getBonos());
            stmt.setString(62, valueObject.getSoc_ppal());
            stmt.setString(63, valueObject.getTel_soc());
            stmt.setDouble(64, valueObject.getPeso());
            stmt.setDouble(65, valueObject.getTalla());
            stmt.setDouble(66, valueObject.getVolumen12());
            stmt.setDouble(67, valueObject.getVolumen24());
            stmt.setDouble(68, valueObject.getLibl1());
            stmt.setDouble(69, valueObject.getLibl2());
            stmt.setDouble(70, valueObject.getDesctosr1());
            stmt.setDouble(71, valueObject.getDesctosr2());
            stmt.setDouble(72, valueObject.getDesctosr3());
            stmt.setDouble(73, valueObject.getLibr1());
            stmt.setDouble(74, valueObject.getLibr2());
            stmt.setDouble(75, valueObject.getLibr3());
            stmt.setBoolean(76, valueObject.getSi_inactivo());
            stmt.setBoolean(77, valueObject.getSi_remi());
            stmt.setBoolean(78, valueObject.getSi_anul());
            stmt.setBoolean(79, valueObject.getSi_factu());
            stmt.setBoolean(80, valueObject.getSi_band01());
            stmt.setBoolean(81, valueObject.getSi_band02());
            stmt.setBoolean(82, valueObject.getSi_band03());
            stmt.setBoolean(83, valueObject.getSi_band04());
            stmt.setBoolean(84, valueObject.getSi_band05());
            stmt.setBoolean(85, valueObject.getSi_band06());
            stmt.setBoolean(86, valueObject.getSi_band07());
            stmt.setBoolean(87, valueObject.getSi_band08());
            stmt.setBoolean(88, valueObject.getSi_band09());
            stmt.setBoolean(89, valueObject.getSi_band10());
            stmt.setBoolean(90, valueObject.getSi_band11());
            stmt.setBoolean(91, valueObject.getSi_band12());
            stmt.setBoolean(92, valueObject.getSi_band13());
            stmt.setBoolean(93, valueObject.getSi_band14());
            stmt.setBoolean(94, valueObject.getSi_band15());
            stmt.setBoolean(95, valueObject.getSi_band16());
            stmt.setBoolean(96, valueObject.getSi_band17());
            stmt.setBoolean(97, valueObject.getSi_band18());
            stmt.setBoolean(98, valueObject.getSi_band19());
            stmt.setBoolean(99, valueObject.getSi_envio_res_email());
            stmt.setString(100, valueObject.getConse_emp());
            stmt.setString(101, valueObject.getDoc_socio());
            stmt.setString(102, valueObject.getAutorizo_des());
            stmt.setString(103, valueObject.getCelular());
            stmt.setObject(104, valueObject.getFecha_factura());
            stmt.setString(105, valueObject.getUsr_codigo());
            stmt.setString(106, valueObject.getFteing_cod());
            stmt.setString(107, valueObject.getCama_cod());
            stmt.setObject(108, valueObject.getFecha_grabo());
            stmt.setTime(109, valueObject.getHora_grabo());
            stmt.setString(110, valueObject.getUsu_facdef());
            stmt.setString(111, valueObject.getNum_remision());
            stmt.setObject(112, valueObject.getFecha_remision());
            stmt.setString(113, valueObject.getUsu_remision());

            int rowcount = databaseUpdate(stmt);
            if (rowcount != 1) {
                //System.out.println("PrimaryKey Error when updating DB!");
                throw new SQLException("PacienteDao:\nPrimaryKey Error when updating DB!");
            }
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public void save(Paciente valueObject)
            throws NotFoundException, SQLException {

        String sql = "UPDATE paciente SET hora = ?::time, fecha = ?::date, sede_codigo = ?, "
                + "historia = ?, tipodcto_cod = ?, nit = ?, "
                + "horario = ?, urgencias = ?, cat_codigo = ?, "
                + "ape1 = ?, nom1 = ?, acte_codigo = ?, "
                + "activo = ?, fec_ent1 = ?::date, fec_ent2 = ?::date, "
                + "hora_ent1 = ?::time, hora_ent2 = ?::time, sede_codigo_in = ?, "
                + "sede_codigo_re = ?, direccion = ?, telefono = ?, "
                + "medico_cod = ?, medico = ?, email = ?, "
                + "clte_codigo = ?, nacio = ?::date, edad = ?, "
                + "med_edad = ?, sexo = ?, observaciones = ?, "
                + "ccosto_cod = ?, factura = ?, telmed = ?, "
                + "facturado = ?, tipo_a = ?, fecha_fac = ?, "
                + "por_copago = ?, vr_copago = ?, abono1 = ?, "
                + "abono2 = ?, tarifa = ?, pordes = ?, "
                + "vr_total = ?, desto = ?, destop = ?, "
                + "cuotam = ?, cod_enla1 = ?, cod_enla2 = ?, "
                + "cod_enla3 = ?, caracteristicas = ?, n_carnet = ?, "
                + "ciudad_cod = ?, zona = ?, n_fac = ?, "
                + "n_facdef = ?, fecha_facdef = ?, n_rec = ?, "
                + "autorizacion = ?, turno_prec = ?, bonos = ?, "
                + "soc_ppal = ?, tel_soc = ?, peso = ?, "
                + "talla = ?, volumen12 = ?, volumen24 = ?, "
                + "libl1 = ?, libl2 = ?, desctosr1 = ?, "
                + "desctosr2 = ?, desctosr3 = ?, libr1 = ?, "
                + "libr2 = ?, libr3 = ?, si_inactivo = ?, "
                + "si_remi = ?, si_anul = ?, si_factu = ?, "
                + "si_band01 = ?, si_band02 = ?, si_band03 = ?, "
                + "si_band04 = ?, si_band05 = ?, si_band06 = ?, "
                + "si_band07 = ?, si_band08 = ?, si_band09 = ?, "
                + "si_band10 = ?, si_band11 = ?, si_band12 = ?, "
                + "si_band13 = ?, si_band14 = ?, si_band15 = ?, "
                + "si_band16 = ?, si_band17 = ?, si_band18 = ?, "
                + "si_band19 = ?, si_envio_res_email = ?, conse_emp = ?, "
                + "doc_socio = ?, autorizo_des = ?, celular = ?, "
                + "fecha_factura = ?, "
                + "usr_codigo = ?, "
                + "fteing_cod = ?, cama_cod = ?, fecha_grabo = ?::date, "
                + "hora_grabo = ?::time, usu_facdef = ?, num_remision = ?, "
                + "fecha_remision = ?::date, usu_remision = ? WHERE (paciente_cod = ? ) ";
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setTime(1, valueObject.getHora());
            stmt.setObject(2, valueObject.getFecha());
            stmt.setString(3, valueObject.getSede_codigo());
            stmt.setString(4, valueObject.getHistoria());
            stmt.setString(5, valueObject.getTipodcto_cod());
            stmt.setString(6, valueObject.getNit());
            stmt.setString(7, valueObject.getHorario());
            stmt.setBoolean(8, valueObject.getUrgencias());
            stmt.setString(9, valueObject.getCat_codigo());
            stmt.setString(10, valueObject.getApe1());
            stmt.setString(11, valueObject.getNom1());
            stmt.setString(12, valueObject.getActe_codigo());
            stmt.setBoolean(13, valueObject.getActivo());
            stmt.setObject(14, valueObject.getFec_ent1());
            stmt.setObject(15, valueObject.getFec_ent2());
            stmt.setTime(16, valueObject.getHora_ent1());
            stmt.setTime(17, valueObject.getHora_ent2());
            stmt.setString(18, valueObject.getSede_codigo_in());
            stmt.setString(19, valueObject.getSede_codigo_re());
            stmt.setString(20, valueObject.getDireccion());
            stmt.setString(21, valueObject.getTelefono());
            stmt.setString(22, valueObject.getMedico_cod());
            stmt.setString(23, valueObject.getMedico());
            stmt.setString(24, valueObject.getEmail());
            stmt.setString(25, valueObject.getClte_codigo());
            stmt.setObject(26, valueObject.getNacio());
            stmt.setInt(27, valueObject.getEdad());
            stmt.setString(28, valueObject.getMed_edad());
            stmt.setString(29, valueObject.getSexo());
            stmt.setString(30, valueObject.getObservaciones());
            stmt.setString(31, valueObject.getCcosto_cod());
            stmt.setString(32, valueObject.getFactura());
            stmt.setString(33, valueObject.getTelmed());
            stmt.setBoolean(34, valueObject.getFacturado());
            stmt.setString(35, valueObject.getTipo_a());
            stmt.setObject(36, valueObject.getFecha_fac());
            stmt.setDouble(37, valueObject.getPor_copago());
            stmt.setDouble(38, valueObject.getVr_copago());
            stmt.setDouble(39, valueObject.getAbono1());
            stmt.setDouble(40, valueObject.getAbono2());
            stmt.setString(41, valueObject.getTarifa());
            stmt.setDouble(42, valueObject.getPordes());
            stmt.setDouble(43, valueObject.getVr_total());
            stmt.setDouble(44, valueObject.getDesto());
            stmt.setDouble(45, valueObject.getDestop());
            stmt.setDouble(46, valueObject.getCuotam());
            stmt.setString(47, valueObject.getCod_enla1());
            stmt.setString(48, valueObject.getCod_enla2());
            stmt.setString(49, valueObject.getCod_enla3());
            stmt.setString(50, valueObject.getCaracteristicas());
            stmt.setString(51, valueObject.getN_carnet());
            stmt.setString(52, valueObject.getCiudad_cod());
            stmt.setString(53, valueObject.getZona());
            stmt.setString(54, valueObject.getN_fac());
            stmt.setString(55, valueObject.getN_facdef());
            stmt.setObject(56, valueObject.getFecha_facdef());
            stmt.setString(57, valueObject.getN_rec());
            stmt.setString(58, valueObject.getAutorizacion());
            stmt.setString(59, valueObject.getTurno_prec());
            stmt.setString(60, valueObject.getBonos());
            stmt.setString(61, valueObject.getSoc_ppal());
            stmt.setString(62, valueObject.getTel_soc());
            stmt.setDouble(63, valueObject.getPeso());
            stmt.setDouble(64, valueObject.getTalla());
            stmt.setDouble(65, valueObject.getVolumen12());
            stmt.setDouble(66, valueObject.getVolumen24());
            stmt.setDouble(67, valueObject.getLibl1());
            stmt.setDouble(68, valueObject.getLibl2());
            stmt.setDouble(69, valueObject.getDesctosr1());
            stmt.setDouble(70, valueObject.getDesctosr2());
            stmt.setDouble(71, valueObject.getDesctosr3());
            stmt.setDouble(72, valueObject.getLibr1());
            stmt.setDouble(73, valueObject.getLibr2());
            stmt.setDouble(74, valueObject.getLibr3());
            stmt.setBoolean(75, valueObject.getSi_inactivo());
            stmt.setBoolean(76, valueObject.getSi_remi());
            stmt.setBoolean(77, valueObject.getSi_anul());
            stmt.setBoolean(78, valueObject.getSi_factu());
            stmt.setBoolean(79, valueObject.getSi_band01());
            stmt.setBoolean(80, valueObject.getSi_band02());
            stmt.setBoolean(81, valueObject.getSi_band03());
            stmt.setBoolean(82, valueObject.getSi_band04());
            stmt.setBoolean(83, valueObject.getSi_band05());
            stmt.setBoolean(84, valueObject.getSi_band06());
            stmt.setBoolean(85, valueObject.getSi_band07());
            stmt.setBoolean(86, valueObject.getSi_band08());
            stmt.setBoolean(87, valueObject.getSi_band09());
            stmt.setBoolean(88, valueObject.getSi_band10());
            stmt.setBoolean(89, valueObject.getSi_band11());
            stmt.setBoolean(90, valueObject.getSi_band12());
            stmt.setBoolean(91, valueObject.getSi_band13());
            stmt.setBoolean(92, valueObject.getSi_band14());
            stmt.setBoolean(93, valueObject.getSi_band15());
            stmt.setBoolean(94, valueObject.getSi_band16());
            stmt.setBoolean(95, valueObject.getSi_band17());
            stmt.setBoolean(96, valueObject.getSi_band18());
            stmt.setBoolean(97, valueObject.getSi_band19());
            stmt.setBoolean(98, valueObject.getSi_envio_res_email());
            stmt.setString(99, valueObject.getConse_emp());
            stmt.setString(100, valueObject.getDoc_socio());
            stmt.setString(101, valueObject.getAutorizo_des());
            stmt.setString(102, valueObject.getCelular());
            stmt.setObject(103, valueObject.getFecha_factura());
            stmt.setString(104, valueObject.getUsr_codigo());
            stmt.setString(105, valueObject.getFteing_cod());
            stmt.setString(106, valueObject.getCama_cod());
            stmt.setObject(107, valueObject.getFecha_grabo());
            stmt.setTime(108, valueObject.getHora_grabo());
            stmt.setString(109, valueObject.getUsu_facdef());
            stmt.setString(110, valueObject.getNum_remision());
            stmt.setObject(111, valueObject.getFecha_remision());
            stmt.setString(112, valueObject.getUsu_remision());
            stmt.setString(113, valueObject.getPaciente_cod());

            int rowcount = databaseUpdate(stmt);
            if (rowcount == 0) {
                //System.out.println("Object could not be saved! (PrimaryKey not found)");
                throw new NotFoundException("PacienteDao:\nObject could not be saved! (PrimaryKey not found)");
            }
            if (rowcount > 1) {
                //System.out.println("PrimaryKey Error when updating DB! (Many objects were affected!)");
                throw new SQLException("PacienteDao:\nPrimaryKey Error when updating DB! (Many objects were affected!)");
            }
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public void delete(Paciente valueObject) throws NotFoundException, SQLException {

        if (valueObject.getPaciente_cod() == null) {
            throw new NotFoundException("PacienteDao:\nSe requiere campo paciente_cod para eliminar!");
        }

        String sql = "DELETE FROM paciente WHERE (paciente_cod = ? ) ";
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, valueObject.getPaciente_cod());

            int rowcount = databaseUpdate(stmt);
            if (rowcount == 0) {
                //System.out.println("Object could not be deleted (PrimaryKey not found)");
                throw new NotFoundException("PacienteDao:\nEl objeto no pudo ser eliminado! (PrimaryKey not found)");
            }
            if (rowcount > 1) {
                //System.out.println("PrimaryKey Error when updating DB! (Many objects were deleted!)");
                throw new SQLException("PacienteDao:\nError de claves primarias! (Many objects were deleted!)");
            }
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public void deleteAll() throws SQLException {

        String sql = "DELETE FROM paciente";
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

    public int countAll() throws SQLException {

        String sql = "SELECT count(*) FROM paciente";
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

    protected void singleQuery(PreparedStatement stmt, Paciente valueObject) throws NotFoundException, SQLException {
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
                valueObject.setHorario(result.getString("horario"));
                valueObject.setUrgencias(result.getBoolean("urgencias"));
                valueObject.setCat_codigo(result.getString("cat_codigo"));
                valueObject.setApe1(result.getString("ape1"));
                valueObject.setNom1(result.getString("nom1"));
                valueObject.setActe_codigo(result.getString("acte_codigo"));
                valueObject.setActivo(result.getBoolean("activo"));
                valueObject.setFec_ent1(result.getDate("fec_ent1"));
                valueObject.setFec_ent2(result.getDate("fec_ent2"));
                valueObject.setHora_ent1(result.getTime("hora_ent1"));
                valueObject.setHora_ent2(result.getTime("hora_ent2"));
                valueObject.setSede_codigo_in(result.getString("sede_codigo_in"));
                valueObject.setSede_codigo_re(result.getString("sede_codigo_re"));
                valueObject.setDireccion(result.getString("direccion"));
                valueObject.setTelefono(result.getString("telefono"));
                valueObject.setMedico_cod(result.getString("medico_cod"));
                valueObject.setMedico(result.getString("medico"));
                valueObject.setEmail(result.getString("email"));
                valueObject.setClte_codigo(result.getString("clte_codigo"));
                valueObject.setNacio(result.getDate("nacio"));
                valueObject.setEdad(result.getInt("edad"));
                valueObject.setMed_edad(result.getString("med_edad"));
                valueObject.setSexo(result.getString("sexo"));
                valueObject.setObservaciones(result.getString("observaciones"));
                valueObject.setCcosto_cod(result.getString("ccosto_cod"));
                valueObject.setFactura(result.getString("factura"));
                valueObject.setTelmed(result.getString("telmed"));
                valueObject.setFacturado(result.getBoolean("facturado"));
                valueObject.setTipo_a(result.getString("tipo_a"));
                valueObject.setFecha_fac(result.getDate("fecha_fac"));
                valueObject.setPor_copago(result.getDouble("por_copago"));
                valueObject.setVr_copago(result.getDouble("vr_copago"));
                valueObject.setAbono1(result.getDouble("abono1"));
                valueObject.setAbono2(result.getDouble("abono2"));
                valueObject.setTarifa(result.getString("tarifa"));
                valueObject.setPordes(result.getDouble("pordes"));
                valueObject.setVr_total(result.getDouble("vr_total"));
                valueObject.setDesto(result.getDouble("desto"));
                valueObject.setDestop(result.getDouble("destop"));
                valueObject.setCuotam(result.getDouble("cuotam"));
                valueObject.setCod_enla1(result.getString("cod_enla1"));
                valueObject.setCod_enla2(result.getString("cod_enla2"));
                valueObject.setCod_enla3(result.getString("cod_enla3"));
                valueObject.setCaracteristicas(result.getString("caracteristicas"));
                valueObject.setN_carnet(result.getString("n_carnet"));
                valueObject.setCiudad_cod(result.getString("ciudad_cod"));
                valueObject.setZona(result.getString("zona"));
                valueObject.setN_fac(result.getString("n_fac"));
                valueObject.setN_facdef(result.getString("n_facdef"));
                valueObject.setFecha_facdef(result.getDate("fecha_facdef"));
                valueObject.setN_rec(result.getString("n_rec"));
                valueObject.setAutorizacion(result.getString("autorizacion"));
                valueObject.setTurno_prec(result.getString("turno_prec"));
                valueObject.setBonos(result.getString("bonos"));
                valueObject.setSoc_ppal(result.getString("soc_ppal"));
                valueObject.setTel_soc(result.getString("tel_soc"));
                valueObject.setPeso(result.getDouble("peso"));
                valueObject.setTalla(result.getDouble("talla"));
                valueObject.setVolumen12(result.getDouble("volumen12"));
                valueObject.setVolumen24(result.getDouble("volumen24"));
                valueObject.setLibl1(result.getDouble("libl1"));
                valueObject.setLibl2(result.getDouble("libl2"));
                valueObject.setDesctosr1(result.getDouble("desctosr1"));
                valueObject.setDesctosr2(result.getDouble("desctosr2"));
                valueObject.setDesctosr3(result.getDouble("desctosr3"));
                valueObject.setLibr1(result.getDouble("libr1"));
                valueObject.setLibr2(result.getDouble("libr2"));
                valueObject.setLibr3(result.getDouble("libr3"));
                valueObject.setSi_inactivo(result.getBoolean("si_inactivo"));
                valueObject.setSi_remi(result.getBoolean("si_remi"));
                valueObject.setSi_anul(result.getBoolean("si_anul"));
                valueObject.setSi_factu(result.getBoolean("si_factu"));
                valueObject.setSi_band01(result.getBoolean("si_band01"));
                valueObject.setSi_band02(result.getBoolean("si_band02"));
                valueObject.setSi_band03(result.getBoolean("si_band03"));
                valueObject.setSi_band04(result.getBoolean("si_band04"));
                valueObject.setSi_band05(result.getBoolean("si_band05"));
                valueObject.setSi_band06(result.getBoolean("si_band06"));
                valueObject.setSi_band07(result.getBoolean("si_band07"));
                valueObject.setSi_band08(result.getBoolean("si_band08"));
                valueObject.setSi_band09(result.getBoolean("si_band09"));
                valueObject.setSi_band10(result.getBoolean("si_band10"));
                valueObject.setSi_band11(result.getBoolean("si_band11"));
                valueObject.setSi_band12(result.getBoolean("si_band12"));
                valueObject.setSi_band13(result.getBoolean("si_band13"));
                valueObject.setSi_band14(result.getBoolean("si_band14"));
                valueObject.setSi_band15(result.getBoolean("si_band15"));
                valueObject.setSi_band16(result.getBoolean("si_band16"));
                valueObject.setSi_band17(result.getBoolean("si_band17"));
                valueObject.setSi_band18(result.getBoolean("si_band18"));
                valueObject.setSi_band19(result.getBoolean("si_band19"));
                valueObject.setSi_envio_res_email(result.getBoolean("si_envio_res_email"));
                valueObject.setConse_emp(result.getString("conse_emp"));
                valueObject.setDoc_socio(result.getString("doc_socio"));
                valueObject.setAutorizo_des(result.getString("autorizo_des"));
                valueObject.setCelular(result.getString("celular"));
                valueObject.setFecha_factura(result.getDate("fecha_factura"));
                //valueObject.setFoto((Image) result.getObject("foto"));
                //valueObject.setHuella((Image) result.getObject("huella"));
                //valueObject.setFirma((Image) result.getObject("firma"));
                //valueObject.setDocumentos((Image) result.getObject("documentos"));
                valueObject.setUsr_codigo(result.getString("usr_codigo"));
                valueObject.setFteing_cod(result.getString("fteing_cod"));
                valueObject.setCama_cod(result.getString("cama_cod"));
                valueObject.setFecha_grabo(result.getDate("fecha_grabo"));
                valueObject.setHora_grabo(result.getTime("hora_grabo"));
                valueObject.setUsu_facdef(result.getString("usu_facdef"));
                valueObject.setNum_remision(result.getString("num_remision"));
                valueObject.setFecha_remision(result.getDate("fecha_remision"));
                valueObject.setUsu_remision(result.getString("usu_remision"));

            } else {
                //System.out.println("Paciente Object Not Found!");
                throw new NotFoundException("PacienteDao:\nPaciente Object Not Found!");
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
                Paciente temp = createValueObject();

                temp.setPaciente_cod(result.getString("paciente_cod"));
                temp.setHora(result.getTime("hora"));
                temp.setFecha(result.getDate("fecha"));
                temp.setSede_codigo(result.getString("sede_codigo"));
                temp.setHistoria(result.getString("historia"));
                temp.setTipodcto_cod(result.getString("tipodcto_cod"));
                temp.setNit(result.getString("nit"));
                temp.setHorario(result.getString("horario"));
                temp.setUrgencias(result.getBoolean("urgencias"));
                temp.setCat_codigo(result.getString("cat_codigo"));
                temp.setApe1(result.getString("ape1"));
                temp.setNom1(result.getString("nom1"));
                temp.setActe_codigo(result.getString("acte_codigo"));
                temp.setActivo(result.getBoolean("activo"));
                temp.setFec_ent1(result.getDate("fec_ent1"));
                temp.setFec_ent2(result.getDate("fec_ent2"));
                temp.setHora_ent1(result.getTime("hora_ent1"));
                temp.setHora_ent2(result.getTime("hora_ent2"));
                temp.setSede_codigo_in(result.getString("sede_codigo_in"));
                temp.setSede_codigo_re(result.getString("sede_codigo_re"));
                temp.setDireccion(result.getString("direccion"));
                temp.setTelefono(result.getString("telefono"));
                temp.setMedico_cod(result.getString("medico_cod"));
                temp.setMedico(result.getString("medico"));
                temp.setEmail(result.getString("email"));
                temp.setClte_codigo(result.getString("clte_codigo"));
                temp.setNacio(result.getDate("nacio"));
                temp.setEdad(result.getInt("edad"));
                temp.setMed_edad(result.getString("med_edad"));
                temp.setSexo(result.getString("sexo"));
                temp.setObservaciones(result.getString("observaciones"));
                temp.setCcosto_cod(result.getString("ccosto_cod"));
                temp.setFactura(result.getString("factura"));
                temp.setTelmed(result.getString("telmed"));
                temp.setFacturado(result.getBoolean("facturado"));
                temp.setTipo_a(result.getString("tipo_a"));
                temp.setFecha_fac(result.getDate("fecha_fac"));
                temp.setPor_copago(result.getDouble("por_copago"));
                temp.setVr_copago(result.getDouble("vr_copago"));
                temp.setAbono1(result.getDouble("abono1"));
                temp.setAbono2(result.getDouble("abono2"));
                temp.setTarifa(result.getString("tarifa"));
                temp.setPordes(result.getDouble("pordes"));
                temp.setVr_total(result.getDouble("vr_total"));
                temp.setDesto(result.getDouble("desto"));
                temp.setDestop(result.getDouble("destop"));
                temp.setCuotam(result.getDouble("cuotam"));
                temp.setCod_enla1(result.getString("cod_enla1"));
                temp.setCod_enla2(result.getString("cod_enla2"));
                temp.setCod_enla3(result.getString("cod_enla3"));
                temp.setCaracteristicas(result.getString("caracteristicas"));
                temp.setN_carnet(result.getString("n_carnet"));
                temp.setCiudad_cod(result.getString("ciudad_cod"));
                temp.setZona(result.getString("zona"));
                temp.setN_fac(result.getString("n_fac"));
                temp.setN_facdef(result.getString("n_facdef"));
                temp.setFecha_facdef(result.getDate("fecha_facdef"));
                temp.setN_rec(result.getString("n_rec"));
                temp.setAutorizacion(result.getString("autorizacion"));
                temp.setTurno_prec(result.getString("turno_prec"));
                temp.setBonos(result.getString("bonos"));
                temp.setSoc_ppal(result.getString("soc_ppal"));
                temp.setTel_soc(result.getString("tel_soc"));
                temp.setPeso(result.getDouble("peso"));
                temp.setTalla(result.getDouble("talla"));
                temp.setVolumen12(result.getDouble("volumen12"));
                temp.setVolumen24(result.getDouble("volumen24"));
                temp.setLibl1(result.getDouble("libl1"));
                temp.setLibl2(result.getDouble("libl2"));
                temp.setDesctosr1(result.getDouble("desctosr1"));
                temp.setDesctosr2(result.getDouble("desctosr2"));
                temp.setDesctosr3(result.getDouble("desctosr3"));
                temp.setLibr1(result.getDouble("libr1"));
                temp.setLibr2(result.getDouble("libr2"));
                temp.setLibr3(result.getDouble("libr3"));
                temp.setSi_inactivo(result.getBoolean("si_inactivo"));
                temp.setSi_remi(result.getBoolean("si_remi"));
                temp.setSi_anul(result.getBoolean("si_anul"));
                temp.setSi_factu(result.getBoolean("si_factu"));
                temp.setSi_band01(result.getBoolean("si_band01"));
                temp.setSi_band02(result.getBoolean("si_band02"));
                temp.setSi_band03(result.getBoolean("si_band03"));
                temp.setSi_band04(result.getBoolean("si_band04"));
                temp.setSi_band05(result.getBoolean("si_band05"));
                temp.setSi_band06(result.getBoolean("si_band06"));
                temp.setSi_band07(result.getBoolean("si_band07"));
                temp.setSi_band08(result.getBoolean("si_band08"));
                temp.setSi_band09(result.getBoolean("si_band09"));
                temp.setSi_band10(result.getBoolean("si_band10"));
                temp.setSi_band11(result.getBoolean("si_band11"));
                temp.setSi_band12(result.getBoolean("si_band12"));
                temp.setSi_band13(result.getBoolean("si_band13"));
                temp.setSi_band14(result.getBoolean("si_band14"));
                temp.setSi_band15(result.getBoolean("si_band15"));
                temp.setSi_band16(result.getBoolean("si_band16"));
                temp.setSi_band17(result.getBoolean("si_band17"));
                temp.setSi_band18(result.getBoolean("si_band18"));
                temp.setSi_band19(result.getBoolean("si_band19"));
                temp.setSi_envio_res_email(result.getBoolean("si_envio_res_email"));
                temp.setConse_emp(result.getString("conse_emp"));
                temp.setDoc_socio(result.getString("doc_socio"));
                temp.setAutorizo_des(result.getString("autorizo_des"));
                temp.setCelular(result.getString("celular"));
                temp.setFecha_factura(result.getDate("fecha_factura"));
                //temp.setFoto((Image) result.getObject("foto"));
                //temp.setHuella((Image) result.getObject("huella"));
                //temp.setFirma((Image) result.getObject("firma"));
                //temp.setDocumentos((Image) result.getObject("documentos"));
                temp.setUsr_codigo(result.getString("usr_codigo"));
                temp.setFteing_cod(result.getString("fteing_cod"));
                temp.setCama_cod(result.getString("cama_cod"));
                temp.setFecha_grabo(result.getDate("fecha_grabo"));
                temp.setHora_grabo(result.getTime("hora_grabo"));
                temp.setUsu_facdef(result.getString("usu_facdef"));
                temp.setNum_remision(result.getString("num_remision"));
                temp.setFecha_remision(result.getDate("fecha_remision"));
                temp.setUsu_remision(result.getString("usu_remision"));

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
     *
     * @param idpaciente
     * @param fecha
     * @param sedecodigo
     * @return
     */
    public boolean existePaciente(String idpaciente, java.util.Date fecha, String sedecodigo) {
        String sql = "SELECT * FROM paciente WHERE paciente_cod=? AND fecha=? AND sede_codigo=?";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, idpaciente);
            stmt.setObject(2, fecha);
            stmt.setString(3, sedecodigo);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "PacienteDao:\n" + ex.toString());
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                    //rs.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "PacienteDao:\n" + ex.toString());
                }
            }
        }
        return false;
    }

    public String retornaCodigoPaciente(String idpaciente, int numeroDias) {

        String cod_enla3 = null;

        String sql = "SELECT cod_enla3 FROM paciente WHERE paciente_cod=? and fecha >= ?::date";
        PreparedStatement stmt = null;
        ResultSet rs = null;

        this.conectar();

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, idpaciente);
            stmt.setDate(2, java.sql.Date.valueOf( fechaListado(numeroDias) ) );
            rs = stmt.executeQuery();
            if (rs.next()) {
                cod_enla3 = rs.getString("cod_enla3");
                this.desconectar();
                return cod_enla3;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "PacienteDao:\n" + ex.toString());
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                    rs.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "PacienteDao:\n" + ex.toString());
                }
            }
        }
        this.desconectar();
        return null;
    }

    //Si existe el registro con el código del paciente para actualizar el consecutivo
    public boolean existeCodPaciente(String paciente_cod, int numeroDias) {
        String sql = "SELECT * FROM paciente WHERE paciente_cod=? and fecha >= ?::date";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, paciente_cod);
            stmt.setDate(2, java.sql.Date.valueOf( fechaListado(numeroDias) ) );
            rs = stmt.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al ejecutar select sobre la\ntabla Paciente de la Bd Winsislab:\n" + ex.toString());
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                    //rs.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error al intentar cerrar el statement\nde la tabla Paciente de Winsislab:\n" + ex.toString());
                }
            }
        }
        return false;
    }

    public String retornaAutorizacionPaciente(Connection c, String idpaciente, int numeroDias) {
        String sql = "SELECT autorizacion FROM paciente WHERE paciente_cod=? and fecha >= ?::date";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = c.prepareStatement(sql);
            stmt.setString(1, idpaciente);
            stmt.setDate(2, java.sql.Date.valueOf( fechaListado(numeroDias) ) );
            rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("autorizacion");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "PacienteDao:\n" + ex.toString());
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "PacienteDao:\n" + ex.toString());
                }
            }
        }
        return null;
    }
    
    /**
     * Descripción: Retorna una fecha corrida hacia el pasado según en número de días recibidos
     * @param nroDias
     */
    private String fechaListado(int nroDias) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new java.util.Date());
        
        if (nroDias > 0) {
            calendar.add( Calendar.DAY_OF_YEAR, (nroDias * -1));
        } else {
            calendar.add(Calendar.DAY_OF_YEAR, -90);
        }
        
        return dtf.format(calendar.getTime());
    }

}
