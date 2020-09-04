package Controler;

/**
 * 03.09.2020
 * @author fberrocalm
 */

import Herramientas.DatosConexion;
import Model.Paciente_Adicionales;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.JOptionPane;

public class Paciente_adicionalesDao {
    
    SimpleDateFormat dtf = new SimpleDateFormat("yyyy-MM-dd");
    
    private boolean cacheOk;
    private List cacheData;
    DatosConexion datosconexion;
    Connection conn;
    
    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public Paciente_adicionalesDao() {
        resetCache();
        datosconexion = new DatosConexion();
    }

    public void resetCache() {
        cacheOk = false;
        cacheData = null;
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

    public Paciente_Adicionales createValueObject() {
          return new Paciente_Adicionales();
    }

//    public Paciente_Adicionales getObject(Connection conn, int ID) throws NotFoundException, SQLException {
//          Paciente_Adicionales valueObject = createValueObject();
//          valueObject.setID(ID);
//          load(conn, valueObject);
//          return valueObject;
//    }

//    public void load(Connection conn, Paciente_Adicionales valueObject) throws NotFoundException, SQLException {
//
//          String sql = "SELECT * FROM paciente_adicionales WHERE (ID = ? ) "; 
//          PreparedStatement stmt = null;
//
//          try {
//               stmt = conn.prepareStatement(sql);
//               stmt.setInt(1, valueObject.getID()); 
//
//               singleQuery(conn, stmt, valueObject);
//
//          } finally {
//              if (stmt != null)
//                  stmt.close();
//          }
//    }

    public List loadAll() throws SQLException {

          if (cacheOk) {
              return cacheData;
          }

          String sql = "SELECT * FROM paciente_adicionales ORDER BY paciente_cod ASC";
          List searchResults = listQuery(conn.prepareStatement(sql));

          cacheData = searchResults;
          cacheOk = true;

          return searchResults;
    }

    public synchronized void create(Paciente_Adicionales valueObject) throws SQLException {

          String sql = "";
          PreparedStatement stmt = null;
          ResultSet result       = null;
          try {
               sql = "INSERT INTO paciente_adicionales ( paciente_cod, hora, "
               + "fecha, sede_codigo, historia, "
               + "tipodcto_cod, nit, concap_cod, "
               + "contenido, activo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
               stmt = conn.prepareStatement(sql);

               stmt.setString(1, valueObject.getPaciente_cod()); 
               stmt.setTime(2, valueObject.getHora()); 
               stmt.setObject(3, valueObject.getFecha()); 
               stmt.setString(4, valueObject.getSede_codigo()); 
               stmt.setString(5, valueObject.getHistoria()); 
               stmt.setString(6, valueObject.getTipodcto_cod()); 
               stmt.setString(7, valueObject.getNit()); 
               stmt.setString(8, valueObject.getConcap_cod()); 
               stmt.setString(9, valueObject.getContenido()); 
               stmt.setBoolean(10, valueObject.getActivo()); 

               int rowcount = databaseUpdate(stmt);
               if (rowcount != 1) {
                    //System.out.println("PrimaryKey Error when updating DB!");
                    throw new SQLException("Paciente_Adicionales:\nError de clave primaria, actualizando DB!");
               }

          } finally {
              if (stmt != null)
                  stmt.close();
          }
          
    }

    public void save(Paciente_Adicionales valueObject) 
          throws NotFoundException, SQLException {

          String sql = "UPDATE paciente_adicionales SET hora = ?, fecha = ?, "
               + "sede_codigo = ?, historia = ?, tipodcto_cod = ?, "
               + "nit = ?, activo = ?, contenido = ? WHERE (paciente_cod = ? AND concap_cod = ?) ";
          PreparedStatement stmt = null;

          try {
              stmt = conn.prepareStatement(sql);
              
              
              stmt.setTime(1, valueObject.getHora()); 
              stmt.setObject(2, valueObject.getFecha()); 
              stmt.setString(3, valueObject.getSede_codigo()); 
              stmt.setString(4, valueObject.getHistoria()); 
              stmt.setString(5, valueObject.getTipodcto_cod()); 
              stmt.setString(6, valueObject.getNit());  
              stmt.setBoolean(7, valueObject.getActivo());                
              stmt.setString(8, valueObject.getContenido()); 
              
              stmt.setString(9, valueObject.getPaciente_cod()); 
              stmt.setString(10, valueObject.getConcap_cod());

              int rowcount = databaseUpdate(stmt);
              if (rowcount == 0) {
                   //System.out.println("Object could not be saved! (PrimaryKey not found)");
                   throw new NotFoundException("No se puede actualizar paciente_adicionales! (PrimaryKey no encontrada!)");
              }
              if (rowcount > 1) {
                   //System.out.println("PrimaryKey Error when updating DB! (Many objects were affected!)");
                   throw new SQLException("Error de PrimaryKey al actualizar paciente_adicionales! (Muchos registros fueron afectados!)");
              }
          } finally {
              if (stmt != null)
                  stmt.close();
          }
    }

//    public void delete(Connection conn, Paciente_Adicionales valueObject) 
//          throws NotFoundException, SQLException {
//
//          String sql = "DELETE FROM paciente_adicionales WHERE (ID = ? ) ";
//          PreparedStatement stmt = null;
//
//          try {
//              stmt = conn.prepareStatement(sql);
//              stmt.setInt(1, valueObject.getID()); 
//
//              int rowcount = databaseUpdate(conn, stmt);
//              if (rowcount == 0) {
//                   //System.out.println("Object could not be deleted (PrimaryKey not found)");
//                   throw new NotFoundException("Object could not be deleted! (PrimaryKey not found)");
//              }
//              if (rowcount > 1) {
//                   //System.out.println("PrimaryKey Error when updating DB! (Many objects were deleted!)");
//                   throw new SQLException("PrimaryKey Error when updating DB! (Many objects were deleted!)");
//              }
//          } finally {
//              if (stmt != null)
//                  stmt.close();
//          }
//    }


    public void deleteAll() throws SQLException {
          String sql = "DELETE FROM paciente_adicionales";
          PreparedStatement stmt = null;

          try {
              stmt = conn.prepareStatement(sql);
              int rowcount = databaseUpdate(stmt);
          } finally {
              if (stmt != null)
                  stmt.close();
          }
    }

    public int countAll() throws SQLException {
          if (cacheOk) {
              return cacheData.size();
          }

          String sql = "SELECT count(*) FROM paciente_adicionales";
          PreparedStatement stmt = null;
          ResultSet result = null;
          int allRows = 0;

          try {
              stmt = conn.prepareStatement(sql);
              result = stmt.executeQuery();

              if (result.next())
                  allRows = result.getInt(1);
          } finally {
              if (result != null)
                  result.close();
              if (stmt != null)
                  stmt.close();
          }
          return allRows;
    }

    public List searchMatching(Paciente_Adicionales valueObject) throws SQLException {

          List searchResults;

          boolean first = true;
          StringBuilder sql = new StringBuilder("SELECT * FROM paciente_adicionales WHERE 1=1 ");

          if (valueObject.getPaciente_cod() != null) {
              if (first) { first = false; }
              sql.append("AND paciente_cod LIKE '").append(valueObject.getPaciente_cod()).append("%' ");
          }

          if (valueObject.getHora() != null) {
              if (first) { first = false; }
              sql.append("AND hora = '").append(valueObject.getHora()).append("' ");
          }

          if (valueObject.getFecha() != null) {
              if (first) { first = false; }
              sql.append("AND fecha = '").append(valueObject.getFecha()).append("' ");
          }

          if (valueObject.getSede_codigo() != null) {
              if (first) { first = false; }
              sql.append("AND sede_codigo LIKE '").append(valueObject.getSede_codigo()).append("%' ");
          }

          if (valueObject.getHistoria() != null) {
              if (first) { first = false; }
              sql.append("AND historia LIKE '").append(valueObject.getHistoria()).append("%' ");
          }

          if (valueObject.getTipodcto_cod() != null) {
              if (first) { first = false; }
              sql.append("AND tipodcto_cod LIKE '").append(valueObject.getTipodcto_cod()).append("%' ");
          }

          if (valueObject.getNit() != null) {
              if (first) { first = false; }
              sql.append("AND nit LIKE '").append(valueObject.getNit()).append("%' ");
          }

          if (valueObject.getConcap_cod() != null) {
              if (first) { first = false; }
              sql.append("AND concap_cod LIKE '").append(valueObject.getConcap_cod()).append("%' ");
          }

          if (valueObject.getContenido() != null) {
              if (first) { first = false; }
              sql.append("AND contenido LIKE '").append(valueObject.getContenido()).append("%' ");
          }

          sql.append("ORDER BY paciente_cod ASC ");
          
          if (first)
               searchResults = new ArrayList();
          else
               searchResults = listQuery(conn.prepareStatement(sql.toString()));

          return searchResults;
    }

    protected int databaseUpdate(PreparedStatement stmt) throws SQLException {
          int result = stmt.executeUpdate();
          resetCache();
          return result;
    }

    protected void singleQuery(PreparedStatement stmt, Paciente_Adicionales valueObject) 
          throws NotFoundException, SQLException {

          ResultSet result = null;

          try {
              result = stmt.executeQuery();

              if (result.next()) {

                   valueObject.setActivo(result.getBoolean("activo")); 
                   valueObject.setPaciente_cod(result.getString("paciente_cod")); 
                   valueObject.setHora(result.getTime("hora")); 
                   valueObject.setFecha(result.getDate("fecha")); 
                   valueObject.setSede_codigo(result.getString("sede_codigo")); 
                   valueObject.setHistoria(result.getString("historia")); 
                   valueObject.setTipodcto_cod(result.getString("tipodcto_cod")); 
                   valueObject.setNit(result.getString("nit")); 
                   valueObject.setConcap_cod(result.getString("concap_cod")); 
                   valueObject.setContenido(result.getString("contenido")); 

              } else {
                    //System.out.println("Paciente_adicionales Object Not Found!");
                    throw new NotFoundException("Paciente_adicionales Object Not Found!");
              }
          } finally {
              if (result != null)
                  result.close();
              if (stmt != null)
                  stmt.close();
          }
    }

    protected List listQuery(PreparedStatement stmt) throws SQLException {

          ArrayList searchResults = new ArrayList();
          ResultSet result = null;

          try {
              result = stmt.executeQuery();

              while (result.next()) {
                   Paciente_Adicionales temp = createValueObject();

                   temp.setActivo(result.getBoolean("activo")); 
                   temp.setPaciente_cod(result.getString("paciente_cod")); 
                   temp.setHora(result.getTime("hora")); 
                   temp.setFecha(result.getDate("fecha")); 
                   temp.setSede_codigo(result.getString("sede_codigo")); 
                   temp.setHistoria(result.getString("historia")); 
                   temp.setTipodcto_cod(result.getString("tipodcto_cod")); 
                   temp.setNit(result.getString("nit")); 
                   temp.setConcap_cod(result.getString("concap_cod")); 
                   temp.setContenido(result.getString("contenido")); 

                   searchResults.add(temp);
              }

          } finally {
              if (result != null)
                  result.close();
              if (stmt != null)
                  stmt.close();
          }

          return (List)searchResults;
    }
}
