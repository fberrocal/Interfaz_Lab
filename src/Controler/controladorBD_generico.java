package Controler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * @author fberrocalm Creado en: 24/02/2015
 */
public class controladorBD_generico {

    ResultSet rs, aux;
    PreparedStatement stmt;
    DefaultTableModel mod;
    Calendar fechaSys;
    int año, mes, dia;

    public controladorBD_generico() {
        fechaSys = new GregorianCalendar();
    }

    //Toma el resultSet generado por la consulta
    public ResultSet getRs() {
        return rs;
    }

    public void setRs(ResultSet rs) {
        this.rs = rs;
    }

    //Ejecuta cualquier consulta que se le envíe y la carga en un ResultSet
    public void ejecutaQuery(PreparedStatement stmt) {
        try {
            rs = stmt.executeQuery();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "M1. Excepción ControladorBD: \n" + ex.getMessage());
        }
    }

    //Cierra el result set despues de ser utilizado
    public void cerrarRs() {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "M2. Excepción ControladorBD: \n" + ex.getMessage());
            }
        }
    }

    public DefaultTableModel actualizaTablaLaboratorio(Connection con, boolean activo, String f) throws SQLException {
        String col[] = {"Paciente", "Apellidos", "Nombres", "Fecha", "Hora", "Código", "Alterno", "Examen", "Contestado", "Validado", "Modificado"};
        mod = new DefaultTableModel(col, 0) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        if (activo) {
            String sql = "select pe.paciente_cod,pe.hora,pe.fecha,pe.examen,pe.alterno,pe.contestado,pe.validado,pe.modificado,e.nombre,p.ape1,p.nom1 "
                    + "from paciente_examenes pe left join paciente p "
                    + "on pe.paciente_cod = p.paciente_cod and p.fecha >= ?::date"
                    + " left join examenes e "
                    + "on pe.examen = e.examen_cod "
                    + "where pe.fecha >= ?::date and pe.contestado = false and pe.validado = false and pe.modificado = false "
                    + "and pe.paciente_cod not in(select paciente_cod from paciente_causa_anula where paciente_cod = pe.paciente_cod) ORDER BY pe.paciente_cod DESC";
            stmt = con.prepareStatement(sql);
            stmt.setDate(1, java.sql.Date.valueOf(f));
            stmt.setDate(2, java.sql.Date.valueOf(f));
            ejecutaQuery(stmt);
        } else {
            String sql = "select pe.paciente_cod,pe.hora,pe.fecha,pe.examen,pe.alterno,pe.contestado,pe.validado,pe.modificado,e.nombre,p.ape1,p.nom1 "
                    + "from paciente_examenes pe left join paciente p "
                    + "on pe.paciente_cod = p.paciente_cod and p.fecha >= ?::date"
                    + " left join examenes e "
                    + "on pe.examen = e.examen_cod "
                    + "where pe.fecha >= ?::DATE "
                    + "and pe.paciente_cod not in(select paciente_cod from paciente_causa_anula where paciente_cod = pe.paciente_cod) ORDER BY pe.paciente_cod DESC";
            stmt = con.prepareStatement(sql);
            stmt.setDate(1, java.sql.Date.valueOf(f));
            stmt.setDate(2, java.sql.Date.valueOf(f));
            ejecutaQuery(stmt);
        }

        while (this.rs.next()) {
            Object fila[] = new Object[11];
            fila[0] = rs.getObject("paciente_cod");
            fila[1] = rs.getObject("ape1");
            fila[2] = rs.getObject("nom1");
            fila[3] = rs.getObject("fecha");
            fila[4] = rs.getObject("hora");
            fila[5] = rs.getObject("examen");
            fila[6] = rs.getObject("alterno");
            fila[7] = rs.getObject("nombre");
            if (rs.getObject("contestado").equals(true)) {
                fila[8] = "SI";
            } else {
                fila[8] = "NO";
            }
            if (rs.getObject("validado").equals(true)) {
                fila[9] = "SI";
            } else {
                fila[9] = "NO";
            }
            if (rs.getObject("modificado").equals(true)) {
                fila[10] = "SI";
            } else {
                fila[10] = "NO";
            }
            mod.addRow(fila);
        }
        return mod;
    }

    public void actualizaTablaLaboratorioNuevo(Connection con, boolean activo, String f, DefaultTableModel mode) throws SQLException {
        if (activo) {
            String sql = "select pe.paciente_cod,pe.hora,pe.fecha,pe.examen,pe.alterno,pe.contestado,pe.validado,pe.modificado,e.nombre,p.ape1,p.nom1 "
                    + "from paciente_examenes pe left join paciente p "
                    + "on pe.paciente_cod = p.paciente_cod and p.fecha >= ?::date"
                    + " left join examenes e "
                    + "on pe.examen = e.examen_cod "
                    + "where pe.fecha >= ?::date and pe.contestado = false and pe.validado = false and pe.modificado = false "
                    + "and pe.paciente_cod not in(select paciente_cod from paciente_causa_anula where paciente_cod = pe.paciente_cod) ORDER BY pe.paciente_cod DESC";
            stmt = con.prepareStatement(sql);
            stmt.setDate(1, java.sql.Date.valueOf(f));
            stmt.setDate(2, java.sql.Date.valueOf(f));
            ejecutaQuery(stmt);
        } else {
            String sql = "select pe.paciente_cod,pe.hora,pe.fecha,pe.examen,pe.alterno,pe.contestado,pe.validado,pe.modificado,e.nombre,p.ape1,p.nom1 "
                    + "from paciente_examenes pe left join paciente p "
                    + "on pe.paciente_cod = p.paciente_cod and p.fecha >= ?::date"
                    + " left join examenes e "
                    + "on pe.examen = e.examen_cod "
                    + "where pe.fecha >= ?::DATE "
                    + "and pe.paciente_cod not in(select paciente_cod from paciente_causa_anula where paciente_cod = pe.paciente_cod) ORDER BY pe.paciente_cod DESC";
            stmt = con.prepareStatement(sql);
            stmt.setDate(1, java.sql.Date.valueOf(f));
            stmt.setDate(2, java.sql.Date.valueOf(f));
            ejecutaQuery(stmt);
        }

        while (this.rs.next()) {
            Object fila[] = new Object[11];
            fila[0] = rs.getObject("paciente_cod");
            fila[1] = rs.getObject("ape1");
            fila[2] = rs.getObject("nom1");
            fila[3] = rs.getObject("fecha");
            fila[4] = rs.getObject("hora");
            fila[5] = rs.getObject("examen");
            fila[6] = rs.getObject("alterno");
            fila[7] = rs.getObject("nombre");
            if (rs.getObject("contestado").equals(true)) {
                fila[8] = "SI";
            } else {
                fila[8] = "NO";
            }
            if (rs.getObject("validado").equals(true)) {
                fila[9] = "SI";
            } else {
                fila[9] = "NO";
            }
            if (rs.getObject("modificado").equals(true)) {
                fila[10] = "SI";
            } else {
                fila[10] = "NO";
            }
            mode.addRow(fila);
        }
    }

    public boolean tieneResultados(PreparedStatement stmt) {
        try {
            aux = stmt.executeQuery();
            if (aux.next()) {
                aux = null;
                return true;
            } else {
                aux = null;
                return false;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "M3. Excepción ControladorBD: \n" + ex.getMessage());
        }
        return false;
    }

    public boolean existeNumeros_nousados(Connection c, String numero) {
        String sql = "M4. SELECT * FROM numeros_nousados WHERE numero=?";
        PreparedStatement stmt = null;
        ResultSet rs;
        try {
            stmt = c.prepareStatement(sql);
            stmt.setString(1, numero);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error al consultar la \ntabla numeros_nousados de la Bd Winsislab:\n" + ex.toString());
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

    public boolean existeCiudad(Connection c, String ciudad_cod) {
        String sql = "SELECT * FROM ciudades WHERE ciudad_cod=?";
        PreparedStatement stmt = null;
        ResultSet rs;
        try {
            stmt = c.prepareStatement(sql);
            stmt.setString(1, ciudad_cod);
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

    //RETORNA EL NOMBRE DE LA CIUDAD PARA SER ALMACENADA EN LA BASE DE DATOS DE WINSISLAB
    public String retornaNomCiudad(Connection c, String ciudad_cod) {
        String sql = "SELECT c.NOMBRE,d.NOMBRE FROM CIU c,DPTOS d WHERE c.CIUDAD=? AND c.DPTO=d.IDDEPARTAMENTO";
        PreparedStatement stmt = null;
        ResultSet rs;
        try {
            stmt = c.prepareStatement(sql);
            stmt.setString(1, ciudad_cod);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString(1) + "-" + rs.getString(2);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error al consultar la \ntabla Ciudades de la Bd Winsislab:\n" + ex.toString());
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                    //rs.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error al intentar cerrar\nel Statement de la tabla ciudades:\n" + ex.toString());
                }
            }
        }
        return null;
    }

    //RETORNA EL NOMBRE DEL CENTRO DE COSTO PARA SER ALMACENADA EN LA BASE DE DATOS DE WINSISLAB
    public String retornaCentroCosto(Connection c, String ccosto) {
        String sql = "SELECT DESCRIPCION FROM CEN WHERE CCOSTO=?";
        PreparedStatement stmt = null;
        ResultSet rs;
        try {
            stmt = c.prepareStatement(sql);
            stmt.setString(1, ccosto);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("DESCRIPCION");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error al consultar la \ntabla Ciudades de la Bd Winsislab:\n" + ex.toString());
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                    //rs.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error al intentar cerrar\nel Statement de la tabla ciudades:\n" + ex.toString());
                }
            }
        }
        return null;
    }

    public DefaultTableModel listadoTablaExamenes(Connection con) {
        try {
            String col[] = {"Cod. Examen", "Cod. Facturación", "Nombre", "Abreviatura"};
            DefaultTableModel modeloLista = new DefaultTableModel(col, 0);
            String sql = "select examen_cod,cod_fact,nombre,nemonico from examenes";
            stmt = con.prepareStatement(sql);
            ejecutaQuery(stmt);
            while (this.rs.next()) {
                Object fila[] = new Object[4];
                fila[0] = rs.getObject("examen_cod");
                fila[1] = rs.getObject("cod_fact");
                fila[2] = rs.getObject("nombre");
                fila[3] = rs.getObject("nemonico");
                modeloLista.addRow(fila);
            }
            return modeloLista;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "M5. SQLException: \n" + e.toString());
        }
        return null;
    }

    public DefaultTableModel filtrolistadoTablaExamenes(Connection con, String cad, String c) {
        String col[] = {"Cod. Examen", "Cod. Facturación", "Nombre", "Abreviatura"};
        DefaultTableModel modeloLista = new DefaultTableModel(col, 0);

        try {
            rs = con.createStatement().executeQuery("SELECT examen_cod,cod_fact,nombre,nemonico FROM examenes WHERE " + c + " LIKE '" + cad + "%'");
            while (rs.next()) {
                Object fila[] = new Object[col.length];
                for (int i = 0; i < fila.length; i++) {
                    fila[i] = rs.getObject(i + 1);
                }
                modeloLista.addRow(fila);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "M6. Homo_Exa_ImatDao:\n" + ex.toString());
        }
        return modeloLista;
    }
    
    public ArrayList getLabscontrolados(Connection con, String tabla, String campo) {
        boolean res       = false;
        int cantFilas     = 0;  // Cantidad de Filas encontradas
        ArrayList codigos = new ArrayList();
        
        try {

            cantFilas = contarCodReg(con, tabla, campo);
            // JOptionPane.showMessageDialog(null, cantFilas);
            
            if (cantFilas > 0) {
                res = true;
                rs = con.createStatement().executeQuery("SELECT CODIGO FROM TGEN WHERE TGEN.TABLA='"+tabla+"' AND TGEN.CAMPO='"+campo+"'");
                while (rs.next()) {
                    codigos.add(rs.getString("CODIGO"));
                }
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error al consultar la \ntabla Tgen de la Bd Clintos 1:\n" + ex.toString());
        }
        return codigos;
    }
    
    /**
     * Cuenta el número de registros en la tabla TGEN de Codigos controlados
     * @param con
     * @param tabla
     * @param campo
     * @return 
     */
    public int contarCodReg(Connection con, String tabla, String campo) {
        int count = 0;

        try {
            this.aux  = con.createStatement().executeQuery("SELECT COUNT(*) AS CANTIDAD FROM TGEN WHERE TGEN.TABLA='"+tabla+"' AND TGEN.CAMPO='"+campo+"'");
            while (aux.next()) {
                count = aux.getInt("CANTIDAD");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error al consultar la \ntabla Tgen de la Bd Clintos 2:\n" + ex.toString());
        }
        return count;
    }

}
