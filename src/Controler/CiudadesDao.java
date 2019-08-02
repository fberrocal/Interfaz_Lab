package Controler;

import Model.Ciudades;
import java.sql.*;
import java.util.*;

/**
 *
 * @author fberrocalm 15/04/2015
 */
public class CiudadesDao {

    public Ciudades createValueObject() {
        return new Ciudades();
    }

    public Ciudades getObject(Connection conn, String ciudad_cod) throws NotFoundException, SQLException {
        Ciudades valueObject = createValueObject();
        valueObject.setCiudad_cod(ciudad_cod);
        load(conn, valueObject);
        return valueObject;
    }

    /**
     * @param conn This method requires working database connection.
     * @param valueObject This parameter contains the class instance to be
     * loaded. Primary-key field must be set for this to work properly.
     */
    public void load(Connection conn, Ciudades valueObject) throws NotFoundException, SQLException {
        if (valueObject.getCiudad_cod() == null) {
            //System.out.println("Can not select without Primary-Key!");
            throw new NotFoundException("Can not select without Primary-Key!");
        }
        String sql = "SELECT * FROM ciudades WHERE (ciudad_cod = ? ) ";
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, valueObject.getCiudad_cod());

            singleQuery(conn, stmt, valueObject);

        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public List loadAll(Connection conn) throws SQLException {

        String sql = "SELECT * FROM ciudades ORDER BY ciudad_cod ASC ";
        List searchResults = listQuery(conn, conn.prepareStatement(sql));

        return searchResults;
    }

    public synchronized void create(Connection conn, Ciudades valueObject) throws SQLException {

        String sql = "";
        PreparedStatement stmt = null;
        ResultSet result = null;

        try {
            sql = "INSERT INTO ciudades ( ciudad_cod, ciudad_descrip, ciudad_activo) VALUES (?, ?, ?) ";
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, valueObject.getCiudad_cod());
            stmt.setString(2, valueObject.getCiudad_descrip());
            stmt.setBoolean(3, valueObject.getCiudad_activo());

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

    public void save(Connection conn, Ciudades valueObject)
            throws NotFoundException, SQLException {

        String sql = "UPDATE ciudades SET ciudad_descrip = ?, ciudad_activo = ? WHERE (ciudad_cod = ? ) ";
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, valueObject.getCiudad_descrip());
            stmt.setBoolean(2, valueObject.getCiudad_activo());
            stmt.setString(3, valueObject.getCiudad_cod());

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

    public void delete(Connection conn, Ciudades valueObject)
            throws NotFoundException, SQLException {

        if (valueObject.getCiudad_cod() == null) {
            //System.out.println("Can not delete without Primary-Key!");
            throw new NotFoundException("Can not delete without Primary-Key!");
        }

        String sql = "DELETE FROM ciudades WHERE (ciudad_cod = ? ) ";
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, valueObject.getCiudad_cod());

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
        String sql = "DELETE FROM ciudades";
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

    protected void singleQuery(Connection conn, PreparedStatement stmt, Ciudades valueObject)
            throws NotFoundException, SQLException {

        ResultSet result = null;

        try {
            result = stmt.executeQuery();

            if (result.next()) {

                valueObject.setCiudad_cod(result.getString("ciudad_cod"));
                valueObject.setCiudad_descrip(result.getString("ciudad_descrip"));
                valueObject.setCiudad_activo(result.getBoolean("ciudad_activo"));

            } else {
                //System.out.println("Ciudades Object Not Found!");
                throw new NotFoundException("Ciudades Object Not Found!");
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
                Ciudades temp = createValueObject();

                temp.setCiudad_cod(result.getString("ciudad_cod"));
                temp.setCiudad_descrip(result.getString("ciudad_descrip"));
                temp.setCiudad_activo(result.getBoolean("ciudad_activo"));

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
