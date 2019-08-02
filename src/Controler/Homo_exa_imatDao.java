/*
 * Archivo para acceder a la tabla homo_exa_imat de la base de datos WINSISLAB
 */
package Controler;

import Herramientas.DatosConexion;
import Model.Homo_exa_imat;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * @author fberrocalm Creado en: 06/02/2015
 */
public class Homo_exa_imatDao {

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    //1
    DatosConexion datosconexion;

    String id_servicio, detalle_servicio, id_alterna, id_winsis, id_compwinsis;
    Connection conn;

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

    public Homo_exa_imatDao() {
        datosconexion = new DatosConexion();
    }

    public Homo_exa_imat createValueObject() {
        return new Homo_exa_imat();
    }

    public Homo_exa_imat getObject(Connection conn, String id_servicio) throws NotFoundException, SQLException {
        Homo_exa_imat valueObject = createValueObject();
        valueObject.setId_servicio(id_servicio);
        load(conn, valueObject);
        return valueObject;
    }

    public Homo_exa_imatDao(String id_servicio) {
        this.id_servicio = id_servicio;
    }

    public void setAll(String id_servicio, String detalle_servicio, String id_alterna, String id_winsis, String id_compwinsis) {
        this.id_servicio = id_servicio;
        this.detalle_servicio = detalle_servicio;
        this.id_alterna = id_alterna;
        this.id_winsis = id_winsis;
        this.id_compwinsis = id_compwinsis;
    }

    public String getId_servicio() {
        return id_servicio;
    }

    public void setId_servicio(String id_servicio) {
        this.id_servicio = id_servicio;
    }

    public String getDetalle_servicio() {
        return detalle_servicio;
    }

    public void setDetalle_servicio(String detalle_servicio) {
        this.detalle_servicio = detalle_servicio;
    }

    public String getId_alterna() {
        return id_alterna;
    }

    public void setId_alterna(String id_alterna) {
        this.id_alterna = id_alterna;
    }

    public String getId_winsis() {
        return id_winsis;
    }

    public void setId_winsis(String id_winsis) {
        this.id_winsis = id_winsis;
    }

    public String getId_compwinsis() {
        return id_compwinsis;
    }

    public void setId_compwinsis(String id_compwinsis) {
        this.id_compwinsis = id_compwinsis;
    }

    public String retornaCodigoWinsisLab(String cod_exam_Agilis) {
        //no todos los codigos estan en winsislab
        String id_winsis1 = null;
        try {
            String sql = "SELECT id_winsis FROM homo_exa_imat WHERE (id_servicio = ? ) ";
            PreparedStatement stmt;
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, cod_exam_Agilis);
            ResultSet resul = stmt.executeQuery();
            if (resul.next()) {
                id_winsis1 = resul.getString("id_winsis");
                resul.close();
            } else {
                id_winsis1 = "";
                resul.close();
            }
            stmt.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Homo_exa_ImatDao:\n" + ex.getMessage());
        }
        return id_winsis1;
    }

    public String retornaIdAlterna(String cod_exam_Agilis) {
        //no todos los codigos estan en winsislab
        String id_alterna = null;
        try {
            String sql = "SELECT id_servicio FROM homo_exa_imat WHERE (id_servicio = ?) ";
            PreparedStatement stmt;
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, cod_exam_Agilis);
            ResultSet resul = stmt.executeQuery();
            if (resul.next()) {
                id_alterna = resul.getString("id_servicio");
                resul.close();
            } else {
                id_alterna = "";
                resul.close();
            }
            stmt.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Homo_exa_ImatDao:\n" + ex.getMessage());
        }
        return id_alterna;
    }

    public void load(Connection conn, Homo_exa_imat valueObject) throws NotFoundException, SQLException {
        if (valueObject.getId_servicio() == null) {
            //System.out.println("Can not select without Primary-Key!");
            throw new NotFoundException("Can not select without Primary-Key!");
        }
        String sql = "SELECT * FROM homo_exa_imat WHERE (id_servicio = ? ) ";
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, valueObject.getId_servicio());

            singleQuery(conn, stmt, valueObject);

        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public List loadAll(Connection conn) throws SQLException {

        String sql = "SELECT * FROM homo_exa_imat ORDER BY id_servicio ASC ";
        List searchResults = listQuery(conn, conn.prepareStatement(sql));

        return searchResults;
    }

    public synchronized void create(Homo_exa_imat valueObject) throws SQLException {

        String sql = "";
        PreparedStatement stmt = null;
        ResultSet result = null;

        try {
            sql = "INSERT INTO homo_exa_imat ( id_servicio, detalle_servicio, id_alterna, "
                    + "id_winsis, id_compwinsis) VALUES (?, ?, ?, ?, ?) ";
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, valueObject.getId_servicio());
            stmt.setString(2, valueObject.getDetalle_servicio());
            stmt.setString(3, valueObject.getId_alterna());
            stmt.setString(4, valueObject.getId_winsis());
            stmt.setString(5, valueObject.getId_compwinsis());

            int rowcount = databaseUpdate(conn, stmt);
            if (rowcount != 1) {
                //System.out.println("PrimaryKey Error when updating DB!");
                throw new SQLException("PrimaryKey Error when updating DB!");
            }

        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }

    }

    public void save(Homo_exa_imat valueObject)
            throws NotFoundException, SQLException {

        String sql = "UPDATE homo_exa_imat SET detalle_servicio = ?, id_alterna = ?, id_winsis = ?, "
                + "id_compwinsis = ? WHERE (id_servicio = ? ) ";
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, valueObject.getDetalle_servicio());
            stmt.setString(2, valueObject.getId_alterna());
            stmt.setString(3, valueObject.getId_winsis());
            stmt.setString(4, valueObject.getId_compwinsis());

            stmt.setString(5, valueObject.getId_servicio());

            int rowcount = databaseUpdate(conn, stmt);
            if (rowcount == 0) {
                //System.out.println("Object could not be saved! (PrimaryKey not found)");
                throw new NotFoundException("Object could not be saved! (PrimaryKey not found)");
            }
            if (rowcount > 1) {
                //System.out.println("PrimaryKey Error when updating DB! (Many objects were affected!)");
                throw new SQLException("PrimaryKey Error when updating DB! (Many objects were affected!)");
            }
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public void delete(Connection conn, Homo_exa_imat valueObject)
            throws NotFoundException, SQLException {

        if (valueObject.getId_servicio() == null) {
            //System.out.println("Can not delete without Primary-Key!");
            throw new NotFoundException("Can not delete without Primary-Key!");
        }

        String sql = "DELETE FROM homo_exa_imat WHERE (id_servicio = ? ) ";
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, valueObject.getId_servicio());

            int rowcount = databaseUpdate(conn, stmt);
            if (rowcount == 0) {
                //System.out.println("Object could not be deleted (PrimaryKey not found)");
                throw new NotFoundException("Object could not be deleted! (PrimaryKey not found)");
            }
            if (rowcount > 1) {
                //System.out.println("PrimaryKey Error when updating DB! (Many objects were deleted!)");
                throw new SQLException("PrimaryKey Error when updating DB! (Many objects were deleted!)");
            }
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public void deleteAll(Connection conn) throws SQLException {

        String sql = "DELETE FROM homo_exa_imat";
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

    protected int databaseUpdate(Connection conn, PreparedStatement stmt) throws SQLException {
        int result = stmt.executeUpdate();
        return result;
    }

    protected void singleQuery(Connection conn, PreparedStatement stmt, Homo_exa_imat valueObject)
            throws NotFoundException, SQLException {

        ResultSet result = null;

        try {
            result = stmt.executeQuery();

            if (result.next()) {

                valueObject.setId_servicio(result.getString("id_servicio"));
                valueObject.setDetalle_servicio(result.getString("detalle_servicio"));
                valueObject.setId_alterna(result.getString("id_alterna"));
                valueObject.setId_winsis(result.getString("id_winsis"));
                valueObject.setId_compwinsis(result.getString("id_compwinsis"));

            } else {
                //System.out.println("Homo_exa_imat Object Not Found!");
                throw new NotFoundException("Homo_exa_imat Object Not Found!");
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
                Homo_exa_imat temp = createValueObject();

                temp.setId_servicio(result.getString("id_servicio"));
                temp.setDetalle_servicio(result.getString("detalle_servicio"));
                temp.setId_alterna(result.getString("id_alterna"));
                temp.setId_winsis(result.getString("id_winsis"));
                temp.setId_compwinsis(result.getString("id_compwinsis"));

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

    public DefaultTableModel listarExamenesHomologados() {
        String col[] = {"Id Servicio", "Servicio", "Id Alterna", "Id Winsislab", "Id Compwinsis"};
        DefaultTableModel modelo = new DefaultTableModel(col, 0) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        ResultSet rs;
        try {
            rs = conn.createStatement().executeQuery("SELECT * FROM homo_exa_imat");
            while (rs.next()) {
                Object fila[] = new Object[col.length];
                for (int i = 0; i < fila.length; i++) {
                    fila[i] = rs.getObject(i + 1);
                }
                modelo.addRow(fila);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Homo_Exa_ImatDao:\n" + ex.toString());
        }
        return modelo;
    }

    public DefaultTableModel filtroTabla(String cadena, String campo) {
        String col[] = {"Id Servicio", "Servicio", "Id Alterna", "Id Winsislab", "Id Compwinsis"};
        DefaultTableModel modelo = new DefaultTableModel(col, 0) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        ResultSet rs;
        try {
            rs = conn.createStatement().executeQuery("SELECT * FROM homo_exa_imat WHERE " + campo + " LIKE '" + cadena + "%' ORDER BY id_servicio");
            while (rs.next()) {
                Object fila[] = new Object[col.length];
                for (int i = 0; i < fila.length; i++) {
                    fila[i] = rs.getObject(i + 1);
                }
                modelo.addRow(fila);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Homo_Exa_ImatDao:\n" + ex.toString());
        }
        return modelo;
    }

    //CARGA DATOS DESDE UNA ARCHIVO ESPECIFICADO EN RUTA A LA TABAL HOMO_EXA_IMAT
    public boolean cargar_desde_archivo_CSV(String ruta) throws SQLException {
        int rowcount;
        String sql = "copy homo_exa_imat FROM '" + ruta + "' WITH DELIMITER AS ';' CSV HEADER";
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(sql);
            rowcount = databaseUpdate(conn, stmt);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        if (rowcount > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean existeIdWinsislab(String idWinsislab) {
        try {
            String sql = "SELECT * FROM examenes WHERE (examen_cod = ?) ";
            PreparedStatement stmt;
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, idWinsislab);
            ResultSet resul = stmt.executeQuery();
            if (resul.next()) {
                stmt.close();
                return true;
            } else {
                stmt.close();
                return false;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Homo_exa_ImatDao:\n" + ex.getMessage());
        }
        return false;
    }
}
