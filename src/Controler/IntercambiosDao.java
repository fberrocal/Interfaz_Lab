package Controler;

/**
 * @author fberrocalm Creado en: 20/02/2015
 */
import Herramientas.DatosConexion;
import Model.Intercambios;
import java.sql.*;
import java.util.*;
import javax.swing.JOptionPane;

public class IntercambiosDao {

    //1
    DatosConexion datosconexion;
    Connection conn;

    public IntercambiosDao() {
        datosconexion = new DatosConexion();
    }

    public void conectar() {
        datosconexion.datosConexionWinsislab();
        conn = new Conexion(datosconexion.getDriver(), "jdbc:postgresql://" + datosconexion.getUrl(),
                datosconexion.getUs(), datosconexion.getPas()).getCon();
    }

    public void desconectar() {
        try {
            conn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Homo_exa_ImatDao:\n" + ex.toString());
        }
    }

    public Intercambios createValueObject() {
        return new Intercambios();
    }

    public Intercambios getObject(Connection conn, int consecutivo) throws NotFoundException, SQLException {
        Intercambios valueObject = createValueObject();
        valueObject.setConsecutivo(consecutivo);
        load(conn, valueObject);
        return valueObject;
    }

    public void load(Connection conn, Intercambios valueObject) throws NotFoundException, SQLException {
        String sql = "SELECT * FROM intercambios WHERE (consecutivo = ? ) ";
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, valueObject.getConsecutivo());
            singleQuery(conn, stmt, valueObject);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public List loadAll(Connection conn) throws SQLException {

        String sql = "SELECT * FROM intercambios ORDER BY consecutivo ASC ";
        List searchResults = listQuery(conn, conn.prepareStatement(sql));

        return searchResults;
    }

    public synchronized void create(Connection conn, Intercambios valueObject) throws SQLException {
        String sql = "";
        PreparedStatement stmt = null;
        ResultSet result = null;
        try {
            sql = "INSERT INTO intercambios ( consecutivo, sede_origen, proceso, "
                    + "cod_tabla, campo, evento, "
                    + "usuario, llave1, llave2, "
                    + "llave3, llave4, llave5, "
                    + "estado1, estado2, estado3, "
                    + "estado4, estado5, estado6, "
                    + "estado7, estado8, estado9, "
                    + "estado10, estado11, estado12, "
                    + "estado13, estado14, estado15, "
                    + "estado16, estado17, estado18, "
                    + "estado19, estado20, fecha) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, valueObject.getConsecutivo());
            stmt.setString(2, valueObject.getSede_origen());
            stmt.setString(3, valueObject.getProceso());
            stmt.setString(4, valueObject.getCod_tabla());
            stmt.setString(5, valueObject.getCampo());
            stmt.setString(6, valueObject.getEvento());
            stmt.setString(7, valueObject.getUsuario());
            stmt.setString(8, valueObject.getLlave1());
            stmt.setString(9, valueObject.getLlave2());
            stmt.setString(10, valueObject.getLlave3());
            stmt.setString(11, valueObject.getLlave4());
            stmt.setString(12, valueObject.getLlave5());
            stmt.setBoolean(13, valueObject.getEstado1());
            stmt.setBoolean(14, valueObject.getEstado2());
            stmt.setBoolean(15, valueObject.getEstado3());
            stmt.setBoolean(16, valueObject.getEstado4());
            stmt.setBoolean(17, valueObject.getEstado5());
            stmt.setBoolean(18, valueObject.getEstado6());
            stmt.setBoolean(19, valueObject.getEstado7());
            stmt.setBoolean(20, valueObject.getEstado8());
            stmt.setBoolean(21, valueObject.getEstado9());
            stmt.setBoolean(22, valueObject.getEstado10());
            stmt.setBoolean(23, valueObject.getEstado11());
            stmt.setBoolean(24, valueObject.getEstado12());
            stmt.setBoolean(25, valueObject.getEstado13());
            stmt.setBoolean(26, valueObject.getEstado14());
            stmt.setBoolean(27, valueObject.getEstado15());
            stmt.setBoolean(28, valueObject.getEstado16());
            stmt.setBoolean(29, valueObject.getEstado17());
            stmt.setBoolean(30, valueObject.getEstado18());
            stmt.setBoolean(31, valueObject.getEstado19());
            stmt.setBoolean(32, valueObject.getEstado20());
            stmt.setTimestamp(33, valueObject.getFecha());

            int rowcount = databaseUpdate(conn, stmt);
            if (rowcount != 1) {
                //System.out.println("PrimaryKey Error when updating DB!");
                throw new SQLException("IntercambiosDao:\nPrimaryKey Error when updating DB!");
            }

        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public void save(Connection conn, Intercambios valueObject)
            throws NotFoundException, SQLException {

        String sql = "UPDATE intercambios SET sede_origen = ?, proceso = ?, cod_tabla = ?, "
                + "campo = ?, evento = ?, usuario = ?, "
                + "llave1 = ?, llave2 = ?, llave3 = ?, "
                + "llave4 = ?, llave5 = ?, estado1 = ?, "
                + "estado2 = ?, estado3 = ?, estado4 = ?, "
                + "estado5 = ?, estado6 = ?, estado7 = ?, "
                + "estado8 = ?, estado9 = ?, estado10 = ?, "
                + "estado11 = ?, estado12 = ?, estado13 = ?, "
                + "estado14 = ?, estado15 = ?, estado16 = ?, "
                + "estado17 = ?, estado18 = ?, estado19 = ?, "
                + "estado20 = ?, fecha = ? WHERE (consecutivo = ? ) ";
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, valueObject.getSede_origen());
            stmt.setString(2, valueObject.getProceso());
            stmt.setString(3, valueObject.getCod_tabla());
            stmt.setString(4, valueObject.getCampo());
            stmt.setString(5, valueObject.getEvento());
            stmt.setString(6, valueObject.getUsuario());
            stmt.setString(7, valueObject.getLlave1());
            stmt.setString(8, valueObject.getLlave2());
            stmt.setString(9, valueObject.getLlave3());
            stmt.setString(10, valueObject.getLlave4());
            stmt.setString(11, valueObject.getLlave5());
            stmt.setBoolean(12, valueObject.getEstado1());
            stmt.setBoolean(13, valueObject.getEstado2());
            stmt.setBoolean(14, valueObject.getEstado3());
            stmt.setBoolean(15, valueObject.getEstado4());
            stmt.setBoolean(16, valueObject.getEstado5());
            stmt.setBoolean(17, valueObject.getEstado6());
            stmt.setBoolean(18, valueObject.getEstado7());
            stmt.setBoolean(19, valueObject.getEstado8());
            stmt.setBoolean(20, valueObject.getEstado9());
            stmt.setBoolean(21, valueObject.getEstado10());
            stmt.setBoolean(22, valueObject.getEstado11());
            stmt.setBoolean(23, valueObject.getEstado12());
            stmt.setBoolean(24, valueObject.getEstado13());
            stmt.setBoolean(25, valueObject.getEstado14());
            stmt.setBoolean(26, valueObject.getEstado15());
            stmt.setBoolean(27, valueObject.getEstado16());
            stmt.setBoolean(28, valueObject.getEstado17());
            stmt.setBoolean(29, valueObject.getEstado18());
            stmt.setBoolean(30, valueObject.getEstado19());
            stmt.setBoolean(31, valueObject.getEstado20());
            stmt.setTimestamp(32, valueObject.getFecha());

            stmt.setInt(33, valueObject.getConsecutivo());

            int rowcount = databaseUpdate(conn, stmt);
            if (rowcount == 0) {
                //System.out.println("Object could not be saved! (PrimaryKey not found)");
                throw new NotFoundException("IntercambiosDao:\nObject could not be saved! (PrimaryKey not found)");
            }
            if (rowcount > 1) {
                //System.out.println("PrimaryKey Error when updating DB! (Many objects were affected!)");
                throw new SQLException("IntercambiosDao:\nPrimaryKey Error when updating DB! (Many objects were affected!)");
            }
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public void delete(Connection conn, Intercambios valueObject)
            throws NotFoundException, SQLException {

        String sql = "DELETE FROM intercambios WHERE (consecutivo = ? ) ";
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, valueObject.getConsecutivo());

            int rowcount = databaseUpdate(conn, stmt);
            if (rowcount == 0) {
                //System.out.println("Object could not be deleted (PrimaryKey not found)");
                throw new NotFoundException("IntercambiosDao:\nObject could not be deleted! (PrimaryKey not found)");
            }
            if (rowcount > 1) {
                //System.out.println("PrimaryKey Error when updating DB! (Many objects were deleted!)");
                throw new SQLException("IntercambiosDao:\nPrimaryKey Error when updating DB! (Many objects were deleted!)");
            }
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public void deleteAll(Connection conn) throws SQLException {

        String sql = "DELETE FROM intercambios";
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);
            int rowcount = databaseUpdate(conn, stmt);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    protected int databaseUpdate(Connection c, PreparedStatement stmt) throws SQLException {
        int result = stmt.executeUpdate();
        return result;
    }

    protected void singleQuery(Connection conn, PreparedStatement stmt, Intercambios valueObject)
            throws NotFoundException, SQLException {

        ResultSet result = null;

        try {
            result = stmt.executeQuery();

            if (result.next()) {

                valueObject.setConsecutivo(result.getInt("consecutivo"));
                valueObject.setSede_origen(result.getString("sede_origen"));
                valueObject.setProceso(result.getString("proceso"));
                valueObject.setCod_tabla(result.getString("cod_tabla"));
                valueObject.setCampo(result.getString("campo"));
                valueObject.setEvento(result.getString("evento"));
                valueObject.setUsuario(result.getString("usuario"));
                valueObject.setLlave1(result.getString("llave1"));
                valueObject.setLlave2(result.getString("llave2"));
                valueObject.setLlave3(result.getString("llave3"));
                valueObject.setLlave4(result.getString("llave4"));
                valueObject.setLlave5(result.getString("llave5"));
                valueObject.setEstado1(result.getBoolean("estado1"));
                valueObject.setEstado2(result.getBoolean("estado2"));
                valueObject.setEstado3(result.getBoolean("estado3"));
                valueObject.setEstado4(result.getBoolean("estado4"));
                valueObject.setEstado5(result.getBoolean("estado5"));
                valueObject.setEstado6(result.getBoolean("estado6"));
                valueObject.setEstado7(result.getBoolean("estado7"));
                valueObject.setEstado8(result.getBoolean("estado8"));
                valueObject.setEstado9(result.getBoolean("estado9"));
                valueObject.setEstado10(result.getBoolean("estado10"));
                valueObject.setEstado11(result.getBoolean("estado11"));
                valueObject.setEstado12(result.getBoolean("estado12"));
                valueObject.setEstado13(result.getBoolean("estado13"));
                valueObject.setEstado14(result.getBoolean("estado14"));
                valueObject.setEstado15(result.getBoolean("estado15"));
                valueObject.setEstado16(result.getBoolean("estado16"));
                valueObject.setEstado17(result.getBoolean("estado17"));
                valueObject.setEstado18(result.getBoolean("estado18"));
                valueObject.setEstado19(result.getBoolean("estado19"));
                valueObject.setEstado20(result.getBoolean("estado20"));
                valueObject.setFecha(result.getTimestamp("fecha"));

            } else {
                //System.out.println("Intercambios Object Not Found!");
                throw new NotFoundException("IntercambiosDao:\nIntercambios Object Not Found!");
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

    protected List listQuery(Connection conn, PreparedStatement stmt) throws SQLException {

        ArrayList searchResults = new ArrayList();
        ResultSet result = null;

        try {
            result = stmt.executeQuery();

            while (result.next()) {
                Intercambios temp = createValueObject();

                temp.setConsecutivo(result.getInt("consecutivo"));
                temp.setSede_origen(result.getString("sede_origen"));
                temp.setProceso(result.getString("proceso"));
                temp.setCod_tabla(result.getString("cod_tabla"));
                temp.setCampo(result.getString("campo"));
                temp.setEvento(result.getString("evento"));
                temp.setUsuario(result.getString("usuario"));
                temp.setLlave1(result.getString("llave1"));
                temp.setLlave2(result.getString("llave2"));
                temp.setLlave3(result.getString("llave3"));
                temp.setLlave4(result.getString("llave4"));
                temp.setLlave5(result.getString("llave5"));
                temp.setEstado1(result.getBoolean("estado1"));
                temp.setEstado2(result.getBoolean("estado2"));
                temp.setEstado3(result.getBoolean("estado3"));
                temp.setEstado4(result.getBoolean("estado4"));
                temp.setEstado5(result.getBoolean("estado5"));
                temp.setEstado6(result.getBoolean("estado6"));
                temp.setEstado7(result.getBoolean("estado7"));
                temp.setEstado8(result.getBoolean("estado8"));
                temp.setEstado9(result.getBoolean("estado9"));
                temp.setEstado10(result.getBoolean("estado10"));
                temp.setEstado11(result.getBoolean("estado11"));
                temp.setEstado12(result.getBoolean("estado12"));
                temp.setEstado13(result.getBoolean("estado13"));
                temp.setEstado14(result.getBoolean("estado14"));
                temp.setEstado15(result.getBoolean("estado15"));
                temp.setEstado16(result.getBoolean("estado16"));
                temp.setEstado17(result.getBoolean("estado17"));
                temp.setEstado18(result.getBoolean("estado18"));
                temp.setEstado19(result.getBoolean("estado19"));
                temp.setEstado20(result.getBoolean("estado20"));
                temp.setFecha(result.getTimestamp("fecha"));

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

    //Se buscan en intercambio los registros que están pendientes por pasar a Agilis (Tabla LABO_RES)
    protected ArrayList pendientes(Connection c, String sql) throws SQLException {
        ArrayList searchResults = new ArrayList(); //En este array se retornan los registros devueltos
        ResultSet result = null; //Registros que resultan al ejecutar el query
        
        /*
        System.out.println("PROCESANDO RESULTADOS ");
        System.out.println("SQL: " + sql);
        System.out.println("----------------------------------");
        */                    

        try {
            result = c.createStatement().executeQuery(sql);
            while (result.next()) {
                Intercambios temp = createValueObject();
                temp.setSede_origen(result.getString("sede_origen"));
                temp.setProceso(result.getString("proceso"));
                temp.setCod_tabla(result.getString("cod_tabla"));
                temp.setLlave1(result.getString("llave1"));
                temp.setLlave2(result.getString("llave2"));
                temp.setLlave3(result.getString("llave3"));
                temp.setLlave4(result.getString("llave4"));
                temp.setLlave5(result.getString("llave5"));
                temp.setEstado8(result.getBoolean("estado8"));
                temp.setFecha(result.getTimestamp("fecha"));
                searchResults.add(temp); //Se adiciona cada registro como un objeto independiente al array de resultados
            }
        } finally {
            if (result != null) {
                result.close();
            }
        }
        return searchResults;
    }

    public void CambiarEstado(Connection c, Intercambios valueObject, String tablasp)
            throws NotFoundException, SQLException {
        String sql = "UPDATE intercambios SET estado8=?"
                + " WHERE sede_origen=? and proceso=? and cod_tabla IN ("+tablasp+") and llave1=? and llave2=? and llave3=?"
                + " and llave4=? and llave5=? and estado8=FALSE and fecha = ?::timestamp";
        PreparedStatement stmt = null;
        try {
            stmt = c.prepareStatement(sql);

            stmt.setBoolean(1, valueObject.getEstado8());
            stmt.setString(2, valueObject.getSede_origen());
            stmt.setString(3, valueObject.getProceso());
            stmt.setString(4, valueObject.getLlave1());
            stmt.setString(5, valueObject.getLlave2());
            stmt.setString(6, valueObject.getLlave3());
            stmt.setString(7, valueObject.getLlave4());
            stmt.setString(8, valueObject.getLlave5());
            stmt.setTimestamp(9, valueObject.getFecha());

            int rowcount = databaseUpdate(c, stmt);

            if (rowcount == 0) {
                //System.out.println("Object could not be saved! (PrimaryKey not found)");
                throw new NotFoundException("IntercambiosDao:\nObject could not be saved! (PrimaryKey not found)");
            }
            if (rowcount > 1) {
                //System.out.println("PrimaryKey Error when updating DB! (Many objects were affected!)");
                throw new SQLException("IntercambiosDao:\nPrimaryKey Error when updating DB! (Many objects were affected!)");
            } else {
                /* System.out.println("CAMBIAMOS EL ESTADO EN INTERCAMBIOS: " + valueObject.getLlave2()); 
                System.out.println("----------------------------------"); */
            }
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public boolean existeIntercambio(Connection c, String llave2) {
        String sql = "SELECT * FROM intercambios WHERE llave2=?";
        PreparedStatement stmt = null;
        ResultSet rs;
        try {
            stmt = c.prepareStatement(sql);
            stmt.setString(1, llave2);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error al consultar la \ntabla intercambios de la Bd Winsislab:\n" + ex.toString());
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                    //rs.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error al intentar cerrar\nel Statement de la tabla intercambios:\n" + ex.toString());
                }
            }
        }
        return false;
    }

}
