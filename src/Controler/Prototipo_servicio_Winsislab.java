/**
 * HILO DE PROCESO QUE ENVIA DATOS DE WINSISLAB A AGILIS 
 */
package Controler;

import Herramientas.DatosConexion;
import Model.Intercambios;
import Model.LABO_RES;
import Model.xLABO_RES;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
// import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import view.Prueba1;

/**
 * @author Ing. Francisco Berrocal Machado Creado en: 24/02/2015
 * Made in: MyBrain
 */
public class Prototipo_servicio_Winsislab implements Runnable {
    SimpleDateFormat dtf = new SimpleDateFormat("yyyy-MM-dd");
    public boolean centinela = true;
    DatosConexion infoConn;
    Conexion cagilis, cwinsislab;
    Intercambios intercambios;
    IntercambiosDao gIntercambios;
    Pacodbc_detDao gPacodbc_det;
    LABO_RES labo_res;
    LABO_RESDao gLabo_res;
    xLABO_RES xlabo_res;
    xLABO_RESDao gxLabo_res;
    controladorBD_generico objetoBD;
    PacienteDao gPaciente;
    LABO_ORDDao gLabo_ord;
    PacodbcDao p;
    Iterator i;
    Iterator co;
    SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
    Timestamp fecha;
    String documentoCliente;
    String tablasResult;
    PreparedStatement stmt;
    ResultSet rs, aux;
    DefaultTableModel tabLab;
    String qry;
    String resBiopsias [];
    String codigos[];
    String cod_paciente;
    ResultSet rs2;
    PreparedStatement stmt2;
    String nCarnet, autoriz, tipoDoc, analito, alterno, usrval; 
    int j;
    int diasRes;
    boolean controlado;
    
    @Override
    public void run() {
        infoConn      = new DatosConexion();
        gIntercambios = new IntercambiosDao();
        gPacodbc_det  = new Pacodbc_detDao();
        labo_res      = new LABO_RES();
        gLabo_res     = new LABO_RESDao();
        xlabo_res     = new xLABO_RES();
        gxLabo_res    = new xLABO_RESDao();
        objetoBD      = new controladorBD_generico();
        gPaciente     = new PacienteDao();
        p             = new PacodbcDao();
        gLabo_ord     = new LABO_ORDDao();
        controlado    = false;

        infoConn.datosConexionWinsislab();
        cwinsislab = new Conexion(infoConn.getDriver(), "jdbc:postgresql://" + infoConn.getUrl(),
                infoConn.getUs(), infoConn.getPas());
        infoConn.datosConexionAgilis();
        cagilis = new Conexion(infoConn.getDriver(), "jdbc:sqlserver://" + infoConn.getUrl(),
                infoConn.getUs(), infoConn.getPas());

        stmt = null;
        rs = null;

        presionarBoton();

        while (centinela) {
            try {
                try {
                    infoConn.datoResxDias();
                    diasRes      = infoConn.getDiasRes();
                    tablasResult = objetoBD.valorVariable(cagilis.getCon(), "@TABLASRESULTLAB");
                    
                    setProgress(10);
                    
                    if (cwinsislab.getCon() == null || cwinsislab.getCon().isClosed()) {
                        infoConn.datosConexionWinsislab();
                        cwinsislab = new Conexion(infoConn.getDriver(), "jdbc:postgresql://" + infoConn.getUrl(),
                                infoConn.getUs(), infoConn.getPas());
                    }
                    if (cagilis.getCon() == null || cagilis.getCon().isClosed()) {
                        infoConn.datosConexionAgilis();
                        cagilis = new Conexion(infoConn.getDriver(), "jdbc:postgresql://" + infoConn.getUrl(),
                                infoConn.getUs(), infoConn.getPas());
                    }
                    
                    co = objetoBD.getLabscontrolados(cagilis.getCon(),"LABORES", "VALIDAEX").iterator();
                    
                    i = gIntercambios.pendientes(cwinsislab.getCon(), "select sede_origen, proceso, cod_tabla,llave1,llave2,llave3,llave4,llave5,estado8, fecha from intercambios "
                            + "where cod_tabla in (" + tablasResult + ") and estado8 is false and fecha>='" + fechaListado() + " 00:00:00'::timestamp").iterator();
                    
                    setProgress(60);
                    if (i.hasNext()) {
                        while (i.hasNext()) {
                            if (cwinsislab.getCon() == null || cwinsislab.getCon().isClosed()) {
                                infoConn.datosConexionWinsislab();
                                cwinsislab = new Conexion(infoConn.getDriver(), "jdbc:postgresql://" + infoConn.getUrl(),
                                        infoConn.getUs(), infoConn.getPas());
                            }
                            if (cagilis.getCon() == null || cagilis.getCon().isClosed()) {
                                infoConn.datosConexionAgilis();
                                cagilis = new Conexion(infoConn.getDriver(), "jdbc:postgresql://" + infoConn.getUrl(),
                                        infoConn.getUs(), infoConn.getPas());
                            }
                            
                            intercambios = (Intercambios) i.next();
                            cod_paciente = intercambios.getLlave2();
                            
                            String sql = "select r.examen_cod,r.reg_exa,r.secuencia,r.analito_cod,r.resultado,r.analito,r.minimo,r.intermedio,r.maximo,r.unidades,r.tablav,"
                                    + "pe.validado_por,pa.autorizacion,pa.tipodcto_cod,pa.n_carnet,pe.alterno,pe.nit,pe.nro_muestra1,r.fecha,r.hora,r.paciente_cod,pe.fec_val,pe.hora_val"
                                    + " from resul_lab r "
                                    + "left join paciente_examenes pe "
                                    + "on r.paciente_cod = pe.paciente_cod and r.sede_codigo = pe.sede_codigo and r.fecha = pe.fecha and r.examen_cod = pe.examen and r.reg_exa = pe.reg_exa"
                                    + " left join paciente pa "
                                    + "on r.paciente_cod = pa.paciente_cod and r.sede_codigo = pa.sede_codigo and r.fecha = pa.fecha "
                                    + "where pe.validado is true and(pa.autorizacion <> '' or pa.autorizacion is not null) and (r.analito <> '' and r.analito is not null) "
                                    + "and r.paciente_cod =? and r.sede_codigo =? and r.fecha =? ::date and r.examen_cod =? and r.reg_exa =? and r.secuencia = 1"
                                    + " and(pe.nro_muestra1 <>'' or pe.nro_muestra1 is not null) "
                                    + "order by r.reg_exa,r.analito_cod";
                            
                            stmt = cwinsislab.getCon().prepareStatement(sql);
                            stmt.setString(1, intercambios.getLlave2());
                            stmt.setString(2, intercambios.getSede_origen());
                            stmt.setObject(3, intercambios.getLlave3());
                            stmt.setString(4, intercambios.getLlave4());
                            stmt.setInt(5, Integer.parseInt(intercambios.getLlave5()));

                            if (!objetoBD.tieneResultados(stmt)) {
                                actualizarBarraDeEstado("Winsislab: No existen resultados para retornar a Clintos");
                                // cambiarEstadoResultado(intercambios, true);
                            } else {
                                objetoBD.ejecutaQuery(stmt);
                                rs = objetoBD.getRs();
                                gPacodbc_det.setConn(cwinsislab.getCon());
                                if (gPacodbc_det.confirma_examen(intercambios.getLlave4(), intercambios.getLlave5(), gPaciente.retornaCodigoPaciente(intercambios.getLlave2(), diasRes))) {
                                    documentoCliente = gLabo_ord.retornaDocumento(cagilis.getCon(), gPaciente.retornaAutorizacionPaciente(cwinsislab.getCon(), intercambios.getLlave2(), diasRes));
                                    while (rs.next()) {
                                        fecha   = Timestamp.valueOf(rs.getString("fec_val") + " " + rs.getString("hora_val") + ".000");
                                        nCarnet = rs.getString("n_carnet");
                                        autoriz = rs.getString("autorizacion");
                                        analito = rs.getString("analito_cod");
                                        
                                        if (co.hasNext()) {
                                           while (co.hasNext()) {
                                               if (rs.getString("alterno").equalsIgnoreCase(co.next().toString())) {
                                                   controlado = true;
                                               }
                                           }
                                        }
                                        
                                        if (controlado == true) {
                                            xlabo_res.setAll(0, rs.getString("n_carnet"), rs.getString("autorizacion"), rs.getString("tipodcto_cod"),
                                                    documentoCliente, rs.getString("alterno"), Integer.parseInt(rs.getString("secuencia")), fecha, rs.getString("analito_cod"),
                                                    rs.getString("analito"), rs.getString("resultado"), rs.getString("minimo"), rs.getString("maximo"), rs.getString("unidades"),
                                                    rs.getString("validado_por"), 0, 0);
                                            gxLabo_res.setConn(cagilis.getCon());
                                            gxLabo_res.create(xlabo_res);
                                            controlado = false;
                                        } else {
                                            labo_res.setAll(0, rs.getString("n_carnet"), rs.getString("autorizacion"), rs.getString("tipodcto_cod"),
                                                    documentoCliente, rs.getString("alterno"), Integer.parseInt(rs.getString("secuencia")), fecha, rs.getString("analito_cod"),
                                                    rs.getString("analito"), rs.getString("resultado"), rs.getString("minimo"), rs.getString("maximo"), rs.getString("unidades"),
                                                    rs.getString("validado_por"), 0);
                                            gLabo_res.setConn(cagilis.getCon());
                                            gLabo_res.create(labo_res);
                                        }
                                    }

                                    qry = "";
                                    qry = "SELECT x.*, y.alterno FROM resul_lab_text x left join paciente_examenes y on x.paciente_cod=y.paciente_cod ";
                                    qry = qry + "WHERE x.paciente_cod=? AND x.activo=true";
                                    
                                    stmt2 = cwinsislab.getCon().prepareStatement(qry);
                                    stmt2.setString(1, cod_paciente);
                                    
                                    resBiopsias = null;
                                    
                                    if (objetoBD.tieneResultados(stmt2)) {
                                        objetoBD.ejecutaQuery(stmt2);
                                        rs2 = objetoBD.getRs();
                                        
                                        while (rs2.next()) {
                                            fecha   = Timestamp.valueOf(rs2.getString("fecha") + " " + rs2.getString("hora") + ".000");
                                            tipoDoc = rs2.getString("tipodcto_cod");
                                            alterno = rs2.getString("alterno");
                                            usrval  = rs2.getString("usr_valida");
                                            
                                            String rBiopsias [] = {rs2.getString("result01"),rs2.getString("result02"),rs2.getString("result03"),rs2.getString("result04"),rs2.getString("result05"),rs2.getString("result06"),rs2.getString("result07")};
                                            resBiopsias = rBiopsias;
                                        }
                                    }
                                    
                                    if(resBiopsias != null) {
                                        if(resBiopsias.length > 0) {
                                            for(int k=0 ; k<resBiopsias.length ; k++) {
                                               if(!(String.valueOf(resBiopsias[k]).isEmpty())){
                                                    if( resBiopsias[k] != null) {    
                                                         labo_res.setAll(0,nCarnet,autoriz,tipoDoc,documentoCliente,alterno,k+1,fecha,analito,"RESULTADO",resBiopsias[k],"","","",usrval,0);
                                                         gLabo_res.setConn(cagilis.getCon());
                                                         gLabo_res.create(labo_res);
                                                    }
                                               }
                                            }
                                        }
                                    }
                                    
                                    intercambios.setEstado8(true);
                                    try {
                                        gIntercambios.CambiarEstado(cwinsislab.getCon(), intercambios);
                                    } catch (NotFoundException ex) {
                                        Logger.getLogger(Prototipo_servicio_Winsislab.class.getName()).log(Level.SEVERE, "Error intentando cambiar el estado en la tabla intercambios \n" + ex.toString(), ex);
                                        System.out.print("Error intentando cambiar el estado en la tabla intercambios \n" + ex.getMessage());
                                    }
                                }
                                rs = null;
                                actualizarBarraDeEstado("Winsislab: Resultados de Laboratorio enviados a Clintos");
                            }
                        }
                    } else {
                        actualizarBarraDeEstado("Winsislab: No existen laboratorios pendientes");
                    }
                    setProgress(100);
                    presionarBoton();
                    i = null;
                    try {
                        setProgress(0);
                        infoConn.datoTimeWinsislab();
                        Thread.sleep(Integer.parseInt(infoConn.getTimeWinsislab()));
                    } catch (NumberFormatException | InterruptedException e) {
                        Logger.getLogger(Prototipo_servicio_Winsislab.class.getName()).log(Level.SEVERE, "Ocurrió la siguiente excepción:\n" + e.toString(), e);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Prototipo_servicio_Winsislab.class.getName()).log(Level.SEVERE, "---****Excepcion de base de datos: \n" + ex.toString(), ex);
                }
            } catch (Exception e) {
                Logger.getLogger(Prototipo_servicio_Winsislab.class.getName()).log(Level.SEVERE, "Exceptien del sistema: \n" + e.toString(), e);
                System.out.print("Exception del sistema: \n" + e.toString());
                infoConn.datosConexionWinsislab();
                cwinsislab = null;
                cwinsislab = new Conexion(infoConn.getDriver(), "jdbc:postgresql://" + infoConn.getUrl(),
                        infoConn.getUs(), infoConn.getPas());
                infoConn.datosConexionAgilis();
                cagilis = null;
                cagilis = new Conexion(infoConn.getDriver(), "jdbc:sqlserver://" + infoConn.getUrl(),
                        infoConn.getUs(), infoConn.getPas());
            }
        }
        cagilis.CerrarCon();
        cwinsislab.CerrarCon();
    }

    private void setProgress(int percent) {
        //SwingUtilities.invokeLater(() -> {
            Prueba1.pb_winsis.setValue(percent);
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(Prototipo_servicio_Winsislab.class.getName()).log(Level.SEVERE, null, ex);
            }
        //});
    }

    public void actualizarBarraDeEstado(String msg) {
        SwingUtilities.invokeLater(() -> {
            Prueba1.statusLabel2.setText(msg);
        });
    }

    public void presionarBoton() {
        SwingUtilities.invokeLater(() -> {
            Prueba1.btn_actualizaTabLab.doClick();
        });
    }
    
    private String fechaListado() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new java.util.Date());
        calendar.add( Calendar.DAY_OF_YEAR, (diasRes * -1));
        return formato.format(calendar.getTime());
    }
    
    // Cambia el estado de un registro de la tabla [intercambios]
    public void cambiarEstadoResultado(Intercambios registroRes, boolean estado) {
        registroRes.setEstado8(estado);
        try {
            gIntercambios.CambiarEstado(cwinsislab.getCon(), registroRes);
        } catch (NotFoundException ex) {
            Logger.getLogger(Prototipo_servicio_Winsislab.class.getName()).log(Level.SEVERE, 
                             "Se ha lanzado un [NotFoundException] intentando cambiar el estado en la tabla intercambios \n" + ex.toString(), ex);
            System.out.print("Error intentando cambiar el estado en la tabla intercambios \n" + ex.getMessage());
        } catch (SQLException ex) {
            Logger.getLogger(Prototipo_servicio_Winsislab.class.getName()).log(Level.SEVERE, 
                              "Se ha lanzado un [SQLException] intentando cambiar el estado en la tabla intercambios \n" + ex.toString(), ex);
        }
    }
}
