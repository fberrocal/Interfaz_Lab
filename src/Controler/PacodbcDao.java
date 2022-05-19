package Controler;

import Herramientas.DatosConexion;
import Model.Pacodbc;
import java.sql.*;
import java.util.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * @author ADMINIMAT
 */
public class PacodbcDao {

    //1
    DatosConexion datosconexion;

    Connection conn;

    public PacodbcDao() {
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
        //conn = new Conexion("org.postgresql.Driver", "jdbc:postgresql://172.16.20.80:5432/PRUEBA", "labimat", "L4bIm4t").getCon();
    }

    public void desconectar() {
        try {
            this.conn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "pacodbcDao:\n" + ex.getMessage());
        }
    }

    public Pacodbc createValueObject() {
        return new Pacodbc();
    }

    public Pacodbc getObject(String cod_odbc) throws NotFoundException, SQLException {
        Pacodbc valueObject = createValueObject();
        valueObject.setCod_odbc(cod_odbc);
        load(valueObject);
        return valueObject;
    }

    public void load(Pacodbc valueObject) throws NotFoundException, SQLException {

        if (valueObject.getCod_odbc() == null) {
            //System.out.println("Can not select without Primary-Key!");
            throw new NotFoundException("pacodbcDao:\nCan not select without Primary-Key!");
        }

        String sql = "SELECT * FROM pacodbc WHERE (cod_odbc = ? ) ";
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

        String sql = "SELECT * FROM pacodbc ORDER BY cod_odbc ASC ";
        List searchResults = listQuery(conn.prepareStatement(sql));

        return searchResults;
    }

    public synchronized void create(Pacodbc valueObject) throws SQLException {
        String sql = "";
        PreparedStatement stmt = null;
        ResultSet result = null;
        try {
            sql = "INSERT INTO pacodbc ( cod_odbc, tipodcto_cod, nit, "
                    + "cod_enla1, cod_enla2, cod_enla3, "
                    + "hora, fecha, historia, "
                    + "urgencias, cat_codigo, ape1, "
                    + "nom1, acte_codigo, activo, "
                    + "direccion, telefono, medico_cod, "
                    + "medico, email, clte_codigo, "
                    + "clte_nombre, nacio, sexo, "
                    + "ccosto_cod, detalle_ccosto, tipo_a, "
                    + "por_copago, vr_copago, n_carnet, "
                    + "ciudad_cod, zona, bonos, "
                    + "soc_ppal, tel_soc, peso, "
                    + "talla, volumen12, volumen24, "
                    + "conse_emp, doc_socio, celular, "
                    + "cama_cod, detalle_cama, fecha_grabo, "
                    + "hora_grabo, fecha_cruce, hora_cruce, "
                    + "usuario_cruce, sede_cruce, cod_pac_cruce, "
                    + "procesado) VALUES (?, ?, ?, ?, ?, ?, ?::time, ?::date, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?::date, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?::date, ?::time, ?::date, ?::time, ?, ?, ?, ?) ";
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, valueObject.getCod_odbc());
            stmt.setString(2, valueObject.getTipodcto_cod());
            stmt.setString(3, valueObject.getNit());
            stmt.setString(4, valueObject.getCod_enla1());
            stmt.setString(5, valueObject.getCod_enla2());
            stmt.setString(6, valueObject.getCod_enla3());
            stmt.setTime(7, valueObject.getHora());
            stmt.setObject(8, valueObject.getFecha());
            stmt.setString(9, valueObject.getHistoria());
            stmt.setBoolean(10, valueObject.getUrgencias());
            stmt.setString(11, valueObject.getCat_codigo());
            stmt.setString(12, valueObject.getApe1());
            stmt.setString(13, valueObject.getNom1());
            stmt.setString(14, valueObject.getActe_codigo());
            stmt.setBoolean(15, valueObject.getActivo());
            stmt.setString(16, valueObject.getDireccion());
            stmt.setString(17, valueObject.getTelefono());
            stmt.setString(18, valueObject.getMedico_cod());
            stmt.setString(19, valueObject.getMedico());
            stmt.setString(20, valueObject.getEmail());
            stmt.setString(21, valueObject.getClte_codigo());
            stmt.setString(22, valueObject.getClte_nombre());
            stmt.setObject(23, valueObject.getNacio());
            stmt.setString(24, valueObject.getSexo());
            stmt.setString(25, valueObject.getCcosto_cod());
            stmt.setString(26, valueObject.getDetalle_ccosto());
            stmt.setString(27, valueObject.getTipo_a());
            stmt.setDouble(28, valueObject.getPor_copago());
            stmt.setDouble(29, valueObject.getVr_copago());
            stmt.setString(30, valueObject.getN_carnet());
            stmt.setString(31, valueObject.getCiudad_cod());
            stmt.setString(32, valueObject.getZona());
            stmt.setString(33, valueObject.getBonos());
            stmt.setString(34, valueObject.getSoc_ppal());
            stmt.setString(35, valueObject.getTel_soc());
            stmt.setDouble(36, valueObject.getPeso());
            stmt.setDouble(37, valueObject.getTalla());
            stmt.setDouble(38, valueObject.getVolumen12());
            stmt.setDouble(39, valueObject.getVolumen24());
            stmt.setString(40, valueObject.getConse_emp());
            stmt.setString(41, valueObject.getDoc_socio());
            stmt.setString(42, valueObject.getCelular());
            stmt.setString(43, valueObject.getCama_cod());
            stmt.setString(44, valueObject.getDetalle_cama());
            stmt.setObject(45, valueObject.getFecha_grabo());
            stmt.setTime(46, valueObject.getHora_grabo());
            stmt.setObject(47, valueObject.getFecha_cruce());
            stmt.setTime(48, valueObject.getHora_cruce());
            stmt.setString(49, valueObject.getUsuario_cruce());
            stmt.setString(50, valueObject.getSede_cruce());
            stmt.setString(51, valueObject.getCod_pac_cruce());
            stmt.setBoolean(52, valueObject.getProcesado());

            int rowcount = databaseUpdate(stmt);
            if (rowcount != 1) {
                //System.out.println("PrimaryKey Error when updating DB!");
                throw new SQLException("pacodbcDao:\nPrimaryKey Error when updating DB!");
            } else {
                //System.out.println("PacodbcDao\nAlmacenado en pacodbc");
            }
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public void save(Pacodbc valueObject)
            throws NotFoundException, SQLException {

        String sql = "UPDATE pacodbc SET tipodcto_cod = ?, nit = ?, cod_enla1 = ?, "
                + "cod_enla2 = ?, cod_enla3 = ?, hora = ?::time, "
                + "fecha = ?::date, historia = ?, urgencias = ?, "
                + "cat_codigo = ?, ape1 = ?, nom1 = ?, "
                + "acte_codigo = ?, activo = ?, direccion = ?, "
                + "telefono = ?, medico_cod = ?, medico = ?, "
                + "email = ?, clte_codigo = ?, clte_nombre = ?, "
                + "nacio = ?::date, sexo = ?, ccosto_cod = ?, "
                + "detalle_ccosto = ?, tipo_a = ?, por_copago = ?, "
                + "vr_copago = ?, n_carnet = ?, ciudad_cod = ?, "
                + "zona = ?, bonos = ?, soc_ppal = ?, "
                + "tel_soc = ?, peso = ?, talla = ?, "
                + "volumen12 = ?, volumen24 = ?, conse_emp = ?, "
                + "doc_socio = ?, celular = ?, cama_cod = ?, "
                + "detalle_cama = ?, fecha_grabo = ?, hora_grabo = ?, "
                + "fecha_cruce = ?::date, hora_cruce = ?::time, usuario_cruce = ?, "
                + "sede_cruce = ?, cod_pac_cruce = ?, procesado = ? WHERE (cod_odbc = ? ) ";
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, valueObject.getTipodcto_cod());
            stmt.setString(2, valueObject.getNit());
            stmt.setString(3, valueObject.getCod_enla1());
            stmt.setString(4, valueObject.getCod_enla2());
            stmt.setString(5, valueObject.getCod_enla3());
            stmt.setTime(6, valueObject.getHora());
            stmt.setObject(7, valueObject.getFecha());
            stmt.setString(8, valueObject.getHistoria());
            stmt.setBoolean(9, valueObject.getUrgencias());
            stmt.setString(10, valueObject.getCat_codigo());
            stmt.setString(11, valueObject.getApe1());
            stmt.setString(12, valueObject.getNom1());
            stmt.setString(13, valueObject.getActe_codigo());
            stmt.setBoolean(14, valueObject.getActivo());
            stmt.setString(15, valueObject.getDireccion());
            stmt.setString(16, valueObject.getTelefono());
            stmt.setString(17, valueObject.getMedico_cod());
            stmt.setString(18, valueObject.getMedico());
            stmt.setString(19, valueObject.getEmail());
            stmt.setString(20, valueObject.getClte_codigo());
            stmt.setString(21, valueObject.getClte_nombre());
            stmt.setObject(22, valueObject.getNacio());
            stmt.setString(23, valueObject.getSexo());
            stmt.setString(24, valueObject.getCcosto_cod());
            stmt.setString(25, valueObject.getDetalle_ccosto());
            stmt.setString(26, valueObject.getTipo_a());
            stmt.setDouble(27, valueObject.getPor_copago());
            stmt.setDouble(28, valueObject.getVr_copago());
            stmt.setString(29, valueObject.getN_carnet());
            stmt.setString(30, valueObject.getCiudad_cod());
            stmt.setString(31, valueObject.getZona());
            stmt.setString(32, valueObject.getBonos());
            stmt.setString(33, valueObject.getSoc_ppal());
            stmt.setString(34, valueObject.getTel_soc());
            stmt.setDouble(35, valueObject.getPeso());
            stmt.setDouble(36, valueObject.getTalla());
            stmt.setDouble(37, valueObject.getVolumen12());
            stmt.setDouble(38, valueObject.getVolumen24());
            stmt.setString(39, valueObject.getConse_emp());
            stmt.setString(40, valueObject.getDoc_socio());
            stmt.setString(41, valueObject.getCelular());
            stmt.setString(42, valueObject.getCama_cod());
            stmt.setString(43, valueObject.getDetalle_cama());
            stmt.setObject(44, valueObject.getFecha_grabo());
            stmt.setTime(45, valueObject.getHora_grabo());
            stmt.setObject(46, valueObject.getFecha_cruce());
            stmt.setTime(47, valueObject.getHora_cruce());
            stmt.setString(48, valueObject.getUsuario_cruce());
            stmt.setString(49, valueObject.getSede_cruce());
            stmt.setString(50, valueObject.getCod_pac_cruce());
            stmt.setBoolean(51, valueObject.getProcesado());

            stmt.setString(52, valueObject.getCod_odbc());

            int rowcount = databaseUpdate(stmt);
            if (rowcount == 0) {
                //System.out.println("Object could not be saved! (PrimaryKey not found)");
                throw new NotFoundException("pacodbcDao:\nObject could not be saved! (PrimaryKey not found)");
            }
            if (rowcount > 1) {
                //System.out.println("PrimaryKey Error when updating DB! (Many objects were affected!)");
                throw new SQLException("pacodbcDao:\nPrimaryKey Error when updating DB! (Many objects were affected!)");
            }
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public void delete(Pacodbc valueObject)
            throws NotFoundException, SQLException {

        if (valueObject.getCod_odbc() == null) {
            //System.out.println("Can not delete without Primary-Key!");
            throw new NotFoundException("pacodbcDao:\nCan not delete without Primary-Key!");
        }

        String sql = "DELETE FROM pacodbc WHERE (cod_odbc = ? ) ";
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, valueObject.getCod_odbc());

            int rowcount = databaseUpdate(stmt);
            if (rowcount == 0) {
                //System.out.println("Object could not be deleted (PrimaryKey not found)");
                throw new NotFoundException("pacodbcDao:\nObject could not be deleted! (PrimaryKey not found)");
            }
            if (rowcount > 1) {
                //System.out.println("PrimaryKey Error when updating DB! (Many objects were deleted!)");
                throw new SQLException("pacodbcDao:\nPrimaryKey Error when updating DB! (Many objects were deleted!)");
            }
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public void deleteAll() throws SQLException {

        String sql = "DELETE FROM pacodbc";
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

        String sql = "SELECT count(*) FROM pacodbc";
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

    protected void singleQuery(PreparedStatement stmt, Pacodbc valueObject)
            throws NotFoundException, SQLException {

        ResultSet result = null;

        try {
            result = stmt.executeQuery();

            if (result.next()) {

                valueObject.setCod_odbc(result.getString("cod_odbc"));
                valueObject.setTipodcto_cod(result.getString("tipodcto_cod"));
                valueObject.setNit(result.getString("nit"));
                valueObject.setCod_enla1(result.getString("cod_enla1"));
                valueObject.setCod_enla2(result.getString("cod_enla2"));
                valueObject.setCod_enla3(result.getString("cod_enla3"));
                valueObject.setHora(result.getTime("hora"));
                valueObject.setFecha(result.getDate("fecha"));
                valueObject.setHistoria(result.getString("historia"));
                valueObject.setUrgencias(result.getBoolean("urgencias"));
                valueObject.setCat_codigo(result.getString("cat_codigo"));
                valueObject.setApe1(result.getString("ape1"));
                valueObject.setNom1(result.getString("nom1"));
                valueObject.setActe_codigo(result.getString("acte_codigo"));
                valueObject.setActivo(result.getBoolean("activo"));
                valueObject.setDireccion(result.getString("direccion"));
                valueObject.setTelefono(result.getString("telefono"));
                valueObject.setMedico_cod(result.getString("medico_cod"));
                valueObject.setMedico(result.getString("medico"));
                valueObject.setEmail(result.getString("email"));
                valueObject.setClte_codigo(result.getString("clte_codigo"));
                valueObject.setClte_nombre(result.getString("clte_nombre"));
                valueObject.setNacio(result.getDate("nacio"));
                valueObject.setSexo(result.getString("sexo"));
                valueObject.setCcosto_cod(result.getString("ccosto_cod"));
                valueObject.setDetalle_ccosto(result.getString("detalle_ccosto"));
                valueObject.setTipo_a(result.getString("tipo_a"));
                valueObject.setPor_copago(result.getDouble("por_copago"));
                valueObject.setVr_copago(result.getDouble("vr_copago"));
                valueObject.setN_carnet(result.getString("n_carnet"));
                valueObject.setCiudad_cod(result.getString("ciudad_cod"));
                valueObject.setZona(result.getString("zona"));
                valueObject.setBonos(result.getString("bonos"));
                valueObject.setSoc_ppal(result.getString("soc_ppal"));
                valueObject.setTel_soc(result.getString("tel_soc"));
                valueObject.setPeso(result.getDouble("peso"));
                valueObject.setTalla(result.getDouble("talla"));
                valueObject.setVolumen12(result.getDouble("volumen12"));
                valueObject.setVolumen24(result.getDouble("volumen24"));
                valueObject.setConse_emp(result.getString("conse_emp"));
                valueObject.setDoc_socio(result.getString("doc_socio"));
                valueObject.setCelular(result.getString("celular"));
                valueObject.setCama_cod(result.getString("cama_cod"));
                valueObject.setDetalle_cama(result.getString("detalle_cama"));
                valueObject.setFecha_grabo(result.getDate("fecha_grabo"));
                valueObject.setHora_grabo(result.getTime("hora_grabo"));
                valueObject.setFecha_cruce(result.getDate("fecha_cruce"));
                valueObject.setHora_cruce(result.getTime("hora_cruce"));
                valueObject.setUsuario_cruce(result.getString("usuario_cruce"));
                valueObject.setSede_cruce(result.getString("sede_cruce"));
                valueObject.setCod_pac_cruce(result.getString("cod_pac_cruce"));
                valueObject.setProcesado(result.getBoolean("procesado"));

            } else {
                //System.out.println("Pacodbc Object Not Found!");
                throw new NotFoundException("pacodbcDao:\nPacodbc Object Not Found!");
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
                Pacodbc temp = createValueObject();

                temp.setCod_odbc(result.getString("cod_odbc"));
                temp.setTipodcto_cod(result.getString("tipodcto_cod"));
                temp.setNit(result.getString("nit"));
                temp.setCod_enla1(result.getString("cod_enla1"));
                temp.setCod_enla2(result.getString("cod_enla2"));
                temp.setCod_enla3(result.getString("cod_enla3"));
                temp.setHora(result.getTime("hora"));
                temp.setFecha(result.getDate("fecha"));
                temp.setHistoria(result.getString("historia"));
                temp.setUrgencias(result.getBoolean("urgencias"));
                temp.setCat_codigo(result.getString("cat_codigo"));
                temp.setApe1(result.getString("ape1"));
                temp.setNom1(result.getString("nom1"));
                temp.setActe_codigo(result.getString("acte_codigo"));
                temp.setActivo(result.getBoolean("activo"));
                temp.setDireccion(result.getString("direccion"));
                temp.setTelefono(result.getString("telefono"));
                temp.setMedico_cod(result.getString("medico_cod"));
                temp.setMedico(result.getString("medico"));
                temp.setEmail(result.getString("email"));
                temp.setClte_codigo(result.getString("clte_codigo"));
                temp.setClte_nombre(result.getString("clte_nombre"));
                temp.setNacio(result.getDate("nacio"));
                temp.setSexo(result.getString("sexo"));
                temp.setCcosto_cod(result.getString("ccosto_cod"));
                temp.setDetalle_ccosto(result.getString("detalle_ccosto"));
                temp.setTipo_a(result.getString("tipo_a"));
                temp.setPor_copago(result.getDouble("por_copago"));
                temp.setVr_copago(result.getDouble("vr_copago"));
                temp.setN_carnet(result.getString("n_carnet"));
                temp.setCiudad_cod(result.getString("ciudad_cod"));
                temp.setZona(result.getString("zona"));
                temp.setBonos(result.getString("bonos"));
                temp.setSoc_ppal(result.getString("soc_ppal"));
                temp.setTel_soc(result.getString("tel_soc"));
                temp.setPeso(result.getDouble("peso"));
                temp.setTalla(result.getDouble("talla"));
                temp.setVolumen12(result.getDouble("volumen12"));
                temp.setVolumen24(result.getDouble("volumen24"));
                temp.setConse_emp(result.getString("conse_emp"));
                temp.setDoc_socio(result.getString("doc_socio"));
                temp.setCelular(result.getString("celular"));
                temp.setCama_cod(result.getString("cama_cod"));
                temp.setDetalle_cama(result.getString("detalle_cama"));
                temp.setFecha_grabo(result.getDate("fecha_grabo"));
                temp.setHora_grabo(result.getTime("hora_grabo"));
                temp.setFecha_cruce(result.getDate("fecha_cruce"));
                temp.setHora_cruce(result.getTime("hora_cruce"));
                temp.setUsuario_cruce(result.getString("usuario_cruce"));
                temp.setSede_cruce(result.getString("sede_cruce"));
                temp.setCod_pac_cruce(result.getString("cod_pac_cruce"));
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

    public DefaultTableModel listarDatos() {
        DefaultTableModel modelo = new DefaultTableModel();
        ResultSet rs;
        modelo.addColumn("cod_odbc");
        modelo.addColumn("tipodcto_cod");
        modelo.addColumn("nit");
        modelo.addColumn("hora");
        modelo.addColumn("fecha");
        modelo.addColumn("historia");
        modelo.addColumn("urgencias");
        modelo.addColumn("cat_codigo");
        modelo.addColumn("ape1");
        modelo.addColumn("nom1");
        modelo.addColumn("activo");
        modelo.addColumn("direccion");
        modelo.addColumn("telefono");
        modelo.addColumn("medico_cod");
        modelo.addColumn("medico");
        modelo.addColumn("clte_codigo");
        modelo.addColumn("nacio");
        modelo.addColumn("peso");
        modelo.addColumn("talla");
        modelo.addColumn("fecha_grabo");
        modelo.addColumn("hora_grabo");
        modelo.addColumn("fecha_cruce");
        modelo.addColumn("hora_cruce");
        modelo.addColumn("procesado");
        try {
            rs = conn.createStatement().executeQuery("SELECT "
                    + "cod_odbc,tipodcto_cod,nit,hora,fecha,historia,urgencias,cat_codigo,ape1,nom1,activo,direccion,telefono,medico_cod,"
                    + "medico,clte_codigo,nacio,peso,talla,fecha_grabo,hora_grabo,fecha_cruce,hora_cruce,procesado"
                    + " FROM pacodbc ORDER BY cod_odbc ASC");
            while (rs.next()) {
                Object fila[] = new Object[24];
                for (int i = 0; i < 24; i++) {
                    fila[i] = rs.getObject(i + 1);
                }
                modelo.addRow(fila);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error:" + ex.toString());
        }
        return modelo;
    }

    public DefaultTableModel listarDatos2() {
        DefaultTableModel modelo = new DefaultTableModel();
        ResultSet rs;
        modelo.addColumn("cod_odbc");
        modelo.addColumn("nit");
        modelo.addColumn("hora");
        modelo.addColumn("fecha");
        modelo.addColumn("historia");
        modelo.addColumn("ape1");
        modelo.addColumn("nom1");
        modelo.addColumn("fecha_grabo");
        modelo.addColumn("hora_grabo");
        modelo.addColumn("fecha_cruce");
        modelo.addColumn("hora_cruce");
        modelo.addColumn("procesado");

        /*
         modelo.addColumn("activo"); modelo.addColumn("direccion"); modelo.addColumn("telefono"); modelo.addColumn("medico_cod"); modelo.addColumn("medico");
         modelo.addColumn("clte_codigo"); modelo.addColumn("nacio"); modelo.addColumn("peso"); modelo.addColumn("talla");  modelo.addColumn("fecha_grabo");
         modelo.addColumn("hora_grabo"); modelo.addColumn("fecha_cruce");  modelo.addColumn("hora_cruce"); modelo.addColumn("procesado"); */
        try {
            rs = conn.createStatement().executeQuery("SELECT "
                    + "cod_odbc,nit,hora,fecha,historia,ape1,nom1,fecha_grabo,hora_grabo,fecha_cruce,hora_cruce,procesado"
                    + " FROM pacodbc ORDER BY cod_odbc ASC");
            while (rs.next()) {
                Object fila[] = new Object[12];
                for (int i = 0; i < 12; i++) {
                    fila[i] = rs.getObject(i + 1);
                }
                modelo.addRow(fila);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error:" + ex.toString());
        }
        return modelo;
    }

    protected ArrayList ordenesNoProcesadas(String sql) throws SQLException {

        ArrayList searchResults = new ArrayList();
        ResultSet result = null;

        try {
            result = conn.createStatement().executeQuery(sql);

            while (result.next()) {
                Pacodbc temp = createValueObject();

                temp.setCod_odbc(result.getString("cod_odbc"));
                temp.setTipodcto_cod(result.getString("tipodcto_cod"));
                temp.setNit(result.getString("nit"));
                temp.setCod_enla1(result.getString("cod_enla1"));
                temp.setCod_enla2(result.getString("cod_enla2"));
                temp.setCod_enla3(result.getString("cod_enla3"));
                temp.setHora(result.getTime("hora"));
                temp.setFecha(result.getDate("fecha"));
                temp.setHistoria(result.getString("historia"));
                temp.setUrgencias(result.getBoolean("urgencias"));
                temp.setCat_codigo(result.getString("cat_codigo"));
                temp.setApe1(result.getString("ape1"));
                temp.setNom1(result.getString("nom1"));
                temp.setActe_codigo(result.getString("acte_codigo"));
                temp.setActivo(result.getBoolean("activo"));
                temp.setDireccion(result.getString("direccion"));
                temp.setTelefono(result.getString("telefono"));
                temp.setMedico_cod(result.getString("medico_cod"));
                temp.setMedico(result.getString("medico"));
                temp.setEmail(result.getString("email"));
                temp.setClte_codigo(result.getString("clte_codigo"));
                temp.setClte_nombre(result.getString("clte_nombre"));
                temp.setNacio(result.getDate("nacio"));
                temp.setSexo(result.getString("sexo"));
                temp.setCcosto_cod(result.getString("ccosto_cod"));
                temp.setDetalle_ccosto(result.getString("detalle_ccosto"));
                temp.setTipo_a(result.getString("tipo_a"));
                temp.setPor_copago(result.getDouble("por_copago"));
                temp.setVr_copago(result.getDouble("vr_copago"));
                temp.setN_carnet(result.getString("n_carnet"));
                temp.setCiudad_cod(result.getString("ciudad_cod"));
                temp.setZona(result.getString("zona"));
                temp.setBonos(result.getString("bonos"));
                temp.setSoc_ppal(result.getString("soc_ppal"));
                temp.setTel_soc(result.getString("tel_soc"));
                temp.setPeso(result.getDouble("peso"));
                temp.setTalla(result.getDouble("talla"));
                temp.setVolumen12(result.getDouble("volumen12"));
                temp.setVolumen24(result.getDouble("volumen24"));
                temp.setConse_emp(result.getString("conse_emp"));
                temp.setDoc_socio(result.getString("doc_socio"));
                temp.setCelular(result.getString("celular"));
                temp.setCama_cod(result.getString("cama_cod"));
                temp.setDetalle_cama(result.getString("detalle_cama"));
                temp.setFecha_grabo(result.getDate("fecha_grabo"));
                temp.setHora_grabo(result.getTime("hora_grabo"));
                temp.setFecha_cruce(result.getDate("fecha_cruce"));
                temp.setHora_cruce(result.getTime("hora_cruce"));
                temp.setUsuario_cruce(result.getString("usuario_cruce"));
                temp.setSede_cruce(result.getString("sede_cruce"));
                temp.setCod_pac_cruce(result.getString("cod_pac_cruce"));
                temp.setProcesado(result.getBoolean("procesado"));

                searchResults.add(temp);
            }

        } finally {
            if (result != null) {
                result.close();
            }
        }
        return searchResults;
    }

    public boolean fueTrasladada(String id) {
        String sql = "SELECT * FROM pacodbc WHERE (cod_odbc = ? )";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "pacodbcDao:\n" + ex.toString());
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                    //rs.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "pacodbcDao:\n" + ex.toString());
                }
            }
        }
        return false;
    }
    
    public String retornaCodigoPaciente(String numero_peticion) {
        String codigo_paciente = "";
        String sql = "SELECT cod_pac_cruce FROM pacodbc WHERE (cod_odbc = ?)";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, numero_peticion);
            rs = stmt.executeQuery();
            if (rs.next()) {
                codigo_paciente = rs.getString("cod_pac_cruce");
            }
        } catch (SQLException ex) {
            System.out.println("PacienteDao:\n" + ex.toString());
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                    if (rs != null) {
                        rs.close();
                    }
                } catch (SQLException ex) {
                    System.out.println("PacienteDao:\n" + ex.toString());
                }
            }
        }

        return codigo_paciente;
    }
}
