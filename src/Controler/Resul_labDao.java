package Controler;

/**
 * @author fberrocalm Creado en: 20/02/2015
 */
import Model.Resul_lab;
import java.sql.*;
import java.util.*;

public class Resul_labDao {

    public Resul_lab createValueObject() {
        return new Resul_lab();
    }

    public Resul_lab getObject(Connection conn, String paciente_cod) throws NotFoundException, SQLException {
        Resul_lab valueObject = createValueObject();
        valueObject.setPaciente_cod(paciente_cod);
        load(conn, valueObject);
        return valueObject;
    }

    public void load(Connection conn, Resul_lab valueObject) throws NotFoundException, SQLException {

        if (valueObject.getPaciente_cod() == null) {
            //System.out.println("Can not select without Primary-Key!");
            throw new NotFoundException("Result_labDao:\nCan not select without Primary-Key!");
        }

        String sql = "SELECT * FROM resul_lab WHERE (paciente_cod = ? ) ";
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, valueObject.getPaciente_cod());

            singleQuery(conn, stmt, valueObject);

        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public List loadAll(Connection conn) throws SQLException {

        String sql = "SELECT * FROM resul_lab ORDER BY paciente_cod ASC ";
        List searchResults = listQuery(conn, conn.prepareStatement(sql));

        return searchResults;
    }

    public synchronized void create(Connection conn, Resul_lab valueObject) throws SQLException {

        String sql = "";
        PreparedStatement stmt = null;
        ResultSet result = null;

        try {
            sql = "INSERT INTO resul_lab ( paciente_cod, hora, fecha, "
                    + "sede_codigo, historia, tipodcto_cod, "
                    + "nit, pac_examen, reg_exa, "
                    + "posiexa, analito_cod, examen_cod, "
                    + "secuiencia, codigo, resultado, "
                    + "analito, minimo, intermedio, "
                    + "maximo, unidades, equipo, "
                    + "reactivo, tablav, tablaa, "
                    + "tabla1, tabla2, activo) VALUES (?, ?::time, ?::date, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, valueObject.getPaciente_cod());
            stmt.setTime(2, valueObject.getHora());
            stmt.setDate(3, valueObject.getFecha());
            stmt.setString(4, valueObject.getSede_codigo());
            stmt.setString(5, valueObject.getHistoria());
            stmt.setString(6, valueObject.getTipodcto_cod());
            stmt.setString(7, valueObject.getNit());
            stmt.setString(8, valueObject.getPac_examen());
            stmt.setInt(9, valueObject.getReg_exa());
            stmt.setInt(10, valueObject.getPosiexa());
            stmt.setString(11, valueObject.getAnalito_cod());
            stmt.setString(12, valueObject.getExamen_cod());
            stmt.setDouble(13, valueObject.getSecuiencia());
            stmt.setInt(14, valueObject.getCodigo());
            stmt.setString(15, valueObject.getResultado());
            stmt.setString(16, valueObject.getAnalito());
            stmt.setString(17, valueObject.getMinimo());
            stmt.setString(18, valueObject.getIntermedio());
            stmt.setString(19, valueObject.getMaximo());
            stmt.setString(20, valueObject.getUnidades());
            stmt.setString(21, valueObject.getEquipo());
            stmt.setString(22, valueObject.getReactivo());
            stmt.setString(23, valueObject.getTablav());
            stmt.setString(24, valueObject.getTablaa());
            stmt.setString(25, valueObject.getTabla1());
            stmt.setString(26, valueObject.getTabla2());
            stmt.setBoolean(27, valueObject.getActivo());

            int rowcount = databaseUpdate(conn, stmt);
            if (rowcount != 1) {
                //System.out.println("PrimaryKey Error when updating DB!");
                throw new SQLException("Result_labDao:\nPrimaryKey Error when updating DB!");
            }

        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public void save(Connection conn, Resul_lab valueObject)
            throws NotFoundException, SQLException {

        String sql = "UPDATE resul_lab SET hora = ?::time, fecha = ?::date, sede_codigo = ?, "
                + "historia = ?, tipodcto_cod = ?, nit = ?, "
                + "pac_examen = ?, reg_exa = ?, posiexa = ?, "
                + "analito_cod = ?, examen_cod = ?, secuiencia = ?, "
                + "codigo = ?, resultado = ?, analito = ?, "
                + "minimo = ?, intermedio = ?, maximo = ?, "
                + "unidades = ?, equipo = ?, reactivo = ?, "
                + "tablav = ?, tablaa = ?, tabla1 = ?, "
                + "tabla2 = ?, activo = ? WHERE (paciente_cod = ? ) ";
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setTime(1, valueObject.getHora());
            stmt.setDate(2, valueObject.getFecha());
            stmt.setString(3, valueObject.getSede_codigo());
            stmt.setString(4, valueObject.getHistoria());
            stmt.setString(5, valueObject.getTipodcto_cod());
            stmt.setString(6, valueObject.getNit());
            stmt.setString(7, valueObject.getPac_examen());
            stmt.setInt(8, valueObject.getReg_exa());
            stmt.setInt(9, valueObject.getPosiexa());
            stmt.setString(10, valueObject.getAnalito_cod());
            stmt.setString(11, valueObject.getExamen_cod());
            stmt.setDouble(12, valueObject.getSecuiencia());
            stmt.setInt(13, valueObject.getCodigo());
            stmt.setString(14, valueObject.getResultado());
            stmt.setString(15, valueObject.getAnalito());
            stmt.setString(16, valueObject.getMinimo());
            stmt.setString(17, valueObject.getIntermedio());
            stmt.setString(18, valueObject.getMaximo());
            stmt.setString(19, valueObject.getUnidades());
            stmt.setString(20, valueObject.getEquipo());
            stmt.setString(21, valueObject.getReactivo());
            stmt.setString(22, valueObject.getTablav());
            stmt.setString(23, valueObject.getTablaa());
            stmt.setString(24, valueObject.getTabla1());
            stmt.setString(25, valueObject.getTabla2());
            stmt.setBoolean(26, valueObject.getActivo());

            stmt.setString(27, valueObject.getPaciente_cod());

            int rowcount = databaseUpdate(conn, stmt);
            if (rowcount == 0) {
                //System.out.println("Object could not be saved! (PrimaryKey not found)");
                throw new NotFoundException("Result_labDao:\nObject could not be saved! (PrimaryKey not found)");
            }
            if (rowcount > 1) {
                //System.out.println("PrimaryKey Error when updating DB! (Many objects were affected!)");
                throw new SQLException("Result_labDao:\nPrimaryKey Error when updating DB! (Many objects were affected!)");
            }
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public void delete(Connection conn, Resul_lab valueObject)
            throws NotFoundException, SQLException {

        if (valueObject.getPaciente_cod() == null) {
            //System.out.println("Can not delete without Primary-Key!");
            throw new NotFoundException("Result_labDao:\nCan not delete without Primary-Key!");
        }

        String sql = "DELETE FROM resul_lab WHERE (paciente_cod = ? ) ";
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, valueObject.getPaciente_cod());

            int rowcount = databaseUpdate(conn, stmt);
            if (rowcount == 0) {
                //System.out.println("Object could not be deleted (PrimaryKey not found)");
                throw new NotFoundException("Result_labDao:\nObject could not be deleted! (PrimaryKey not found)");
            }
            if (rowcount > 1) {
                //System.out.println("PrimaryKey Error when updating DB! (Many objects were deleted!)");
                throw new SQLException("Result_labDao:\nPrimaryKey Error when updating DB! (Many objects were deleted!)");
            }
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public void deleteAll(Connection conn) throws SQLException {

        String sql = "DELETE FROM resul_lab";
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

    protected void singleQuery(Connection conn, PreparedStatement stmt, Resul_lab valueObject)
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
                valueObject.setPac_examen(result.getString("pac_examen"));
                valueObject.setReg_exa(result.getInt("reg_exa"));
                valueObject.setPosiexa(result.getInt("posiexa"));
                valueObject.setAnalito_cod(result.getString("analito_cod"));
                valueObject.setExamen_cod(result.getString("examen_cod"));
                valueObject.setSecuiencia(result.getDouble("secuiencia"));
                valueObject.setCodigo(result.getInt("codigo"));
                valueObject.setResultado(result.getString("resultado"));
                valueObject.setAnalito(result.getString("analito"));
                valueObject.setMinimo(result.getString("minimo"));
                valueObject.setIntermedio(result.getString("intermedio"));
                valueObject.setMaximo(result.getString("maximo"));
                valueObject.setUnidades(result.getString("unidades"));
                valueObject.setEquipo(result.getString("equipo"));
                valueObject.setReactivo(result.getString("reactivo"));
                valueObject.setTablav(result.getString("tablav"));
                valueObject.setTablaa(result.getString("tablaa"));
                valueObject.setTabla1(result.getString("tabla1"));
                valueObject.setTabla2(result.getString("tabla2"));
                valueObject.setActivo(result.getBoolean("activo"));

            } else {
                //System.out.println("Resul_lab Object Not Found!");
                throw new NotFoundException("Result_labDao:\nResul_lab Object Not Found!");
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
                Resul_lab temp = createValueObject();

                temp.setPaciente_cod(result.getString("paciente_cod"));
                temp.setHora(result.getTime("hora"));
                temp.setFecha(result.getDate("fecha"));
                temp.setSede_codigo(result.getString("sede_codigo"));
                temp.setHistoria(result.getString("historia"));
                temp.setTipodcto_cod(result.getString("tipodcto_cod"));
                temp.setNit(result.getString("nit"));
                temp.setPac_examen(result.getString("pac_examen"));
                temp.setReg_exa(result.getInt("reg_exa"));
                temp.setPosiexa(result.getInt("posiexa"));
                temp.setAnalito_cod(result.getString("analito_cod"));
                temp.setExamen_cod(result.getString("examen_cod"));
                temp.setSecuiencia(result.getDouble("secuiencia"));
                temp.setCodigo(result.getInt("codigo"));
                temp.setResultado(result.getString("resultado"));
                temp.setAnalito(result.getString("analito"));
                temp.setMinimo(result.getString("minimo"));
                temp.setIntermedio(result.getString("intermedio"));
                temp.setMaximo(result.getString("maximo"));
                temp.setUnidades(result.getString("unidades"));
                temp.setEquipo(result.getString("equipo"));
                temp.setReactivo(result.getString("reactivo"));
                temp.setTablav(result.getString("tablav"));
                temp.setTablaa(result.getString("tablaa"));
                temp.setTabla1(result.getString("tabla1"));
                temp.setTabla2(result.getString("tabla2"));
                temp.setActivo(result.getBoolean("activo"));

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
