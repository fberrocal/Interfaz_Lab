package Controler;

import Model.Centro_costo;
import java.sql.*;
import java.util.*;
import javax.swing.JOptionPane;

/**
 *
 * @author fberrocalm 17/04/2015 14:06 Agilis S.A.S.
 */
public class Centro_costoDao {

    //Crea un objeto del tipo Centro_costo
    public Centro_costo createValueObject() {
        return new Centro_costo();
    }

    //Retorna un objeto del tipo Centro_costo
    public Centro_costo getObject(Connection conn, String ccosto_cod) throws NotFoundException, SQLException {
        Centro_costo valueObject = createValueObject();
        valueObject.setCcosto_cod(ccosto_cod);
        load(conn, valueObject);
        return valueObject;
    }

    public void load(Connection conn, Centro_costo valueObject) throws NotFoundException, SQLException {

        if (valueObject.getCcosto_cod() == null) {
            //System.out.println("Can not select without Primary-Key!");
            throw new NotFoundException("Can not select without Primary-Key!");
        }

        String sql = "SELECT * FROM centro_costo WHERE (ccosto_cod = ? ) ";
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, valueObject.getCcosto_cod());

            singleQuery(conn, stmt, valueObject);

        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public List loadAll(Connection conn) throws SQLException {

        String sql = "SELECT * FROM centro_costo ORDER BY ccosto_cod ASC ";
        List searchResults = listQuery(conn, conn.prepareStatement(sql));

        return searchResults;
    }

    //CREA UN NUEVO REGISTRO EN LA TABLA CENTRO_COSTO
    public synchronized void create(Connection conn, Centro_costo valueObject) throws SQLException {
        String sql = "";
        PreparedStatement stmt = null;
        ResultSet result = null;

        try {
            sql = "INSERT INTO centro_costo ( ccosto_cod, ccosto_desc, ccosto_estado, "
                    + "cod_ambito) VALUES (?, ?, ?, ?) ";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, valueObject.getCcosto_cod());
            stmt.setString(2, valueObject.getCcosto_desc());
            stmt.setBoolean(3, valueObject.getCcosto_estado());
            stmt.setString(4, valueObject.getCod_ambito());

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

    //ACTUALIZA UN REGISTRO DE LA TABLA CENTRO_COSTO
    public void save(Connection conn, Centro_costo valueObject) throws NotFoundException, SQLException {

        String sql = "UPDATE centro_costo SET ccosto_desc = ?, ccosto_estado = ?, cod_ambito = ? WHERE (ccosto_cod = ? ) ";
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, valueObject.getCcosto_desc());
            stmt.setBoolean(2, valueObject.getCcosto_estado());
            stmt.setString(3, valueObject.getCod_ambito());

            stmt.setString(4, valueObject.getCcosto_cod());

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

    //ELIMINA REGISTROS DE LA TABAL CENTRO_COSTO
    public void delete(Connection conn, Centro_costo valueObject) throws NotFoundException, SQLException {
        if (valueObject.getCcosto_cod() == null) {
            //System.out.println("Can not delete without Primary-Key!");
            throw new NotFoundException("Can not delete without Primary-Key!");
        }

        String sql = "DELETE FROM centro_costo WHERE (ccosto_cod = ? ) ";
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, valueObject.getCcosto_cod());

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

    //LIMPIA LA TABLA CENTRO_COSTO
    public void deleteAll(Connection conn) throws SQLException {

        String sql = "DELETE FROM centro_costo";
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

    public int countAll(Connection conn) throws SQLException {

        String sql = "SELECT count(*) FROM centro_costo";
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

    protected int databaseUpdate(Connection conn, PreparedStatement stmt) throws SQLException {
        int result = stmt.executeUpdate();
        return result;
    }

    protected void singleQuery(Connection conn, PreparedStatement stmt, Centro_costo valueObject) throws NotFoundException, SQLException {
        ResultSet result = null;

        try {
            result = stmt.executeQuery();

            if (result.next()) {

                valueObject.setCcosto_cod(result.getString("ccosto_cod"));
                valueObject.setCcosto_desc(result.getString("ccosto_desc"));
                valueObject.setCcosto_estado(result.getBoolean("ccosto_estado"));
                valueObject.setCod_ambito(result.getString("cod_ambito"));

            } else {
                //System.out.println("Centro_costo Object Not Found!");
                throw new NotFoundException("Centro_costo Object Not Found!");
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
                Centro_costo temp = createValueObject();

                temp.setCcosto_cod(result.getString("ccosto_cod"));
                temp.setCcosto_desc(result.getString("ccosto_desc"));
                temp.setCcosto_estado(result.getBoolean("ccosto_estado"));
                temp.setCod_ambito(result.getString("cod_ambito"));

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

    //Verifica si existe el centro de costo en la base de datos WINSISLAB
    public boolean existeCentrocosto(Connection c, String ccosto_cod) {
        String sql = "SELECT * FROM centro_costo WHERE ccosto_cod=?";
        PreparedStatement stmt = null;
        ResultSet rs;
        try {
            stmt = c.prepareStatement(sql);
            stmt.setString(1, ccosto_cod);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return true;
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
        return false;
    }
}
