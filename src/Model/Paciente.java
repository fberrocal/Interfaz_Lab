package Model;

/**
 * Paciente Value Object.
 *
 * @author fberrocalm Creado en: 13/02/2015
 */
import java.awt.Image;
import java.io.Serializable;
import java.sql.Time;

public class Paciente implements Cloneable, Serializable {

    private String paciente_cod;
    private Time hora;
    private java.util.Date fecha;
    private String sede_codigo;
    private String historia;
    private String tipodcto_cod;
    private String nit;
    private String horario;
    private boolean urgencias;
    private String cat_codigo;
    private String ape1;
    private String nom1;
    private String acte_codigo;
    private boolean activo;
    private java.util.Date fec_ent1;
    private java.util.Date fec_ent2;
    private Time hora_ent1;
    private Time hora_ent2;
    private String sede_codigo_in;
    private String sede_codigo_re;
    private String direccion;
    private String telefono;
    private String medico_cod;
    private String medico;
    private String email;
    private String clte_codigo;
    private java.util.Date nacio;
    private int edad;
    private String med_edad;
    private String sexo;
    private String observaciones;
    private String ccosto_cod;
    private String factura;
    private String telmed;
    private boolean facturado;
    private String tipo_a;
    private java.util.Date fecha_fac;
    private double por_copago;
    private double vr_copago;
    private double abono1;
    private double abono2;
    private String tarifa;
    private double pordes;
    private double vr_total;
    private double desto;
    private double destop;
    private double cuotam;
    private String cod_enla1;
    private String cod_enla2;
    private String cod_enla3;
    private String caracteristicas;
    private String n_carnet;
    private String ciudad_cod;
    private String zona;
    private String n_fac;
    private String n_facdef;
    private java.util.Date fecha_facdef;
    private String n_rec;
    private String autorizacion;
    private String turno_prec;
    private String bonos;
    private String soc_ppal;
    private String tel_soc;
    private double peso;
    private double talla;
    private double volumen12;
    private double volumen24;
    private double libl1;
    private double libl2;
    private double desctosr1;
    private double desctosr2;
    private double desctosr3;
    private double libr1;
    private double libr2;
    private double libr3;
    private boolean si_inactivo, si_remi, si_anul, si_factu, si_band01, si_band02, si_band03, si_band04, si_band05, si_band06, si_band07;
    private boolean si_band08, si_band09, si_band10, si_band11, si_band12, si_band13, si_band14, si_band15, si_band16, si_band17, si_band18;
    private boolean si_band19, si_envio_res_email;
    private String conse_emp;
    private String doc_socio;
    private String autorizo_des;
    private String celular;
    private java.util.Date fecha_factura;
    private Image foto;
    private Image huella;
    private Image firma;
    private Image documentos;
    private String usr_codigo;
    private String fteing_cod;
    private String cama_cod;
    private java.util.Date fecha_grabo;
    private Time hora_grabo;
    private String usu_facdef;
    private String num_remision;
    private java.util.Date fecha_remision;
    private String usu_remision;

    public Paciente() {
    }

    public Paciente(String paciente_codIn) {
        this.paciente_cod = paciente_codIn;
    }

    public String getPaciente_cod() {
        return this.paciente_cod;
    }

    public void setPaciente_cod(String paciente_codIn) {
        this.paciente_cod = paciente_codIn;
    }

    public Time getHora() {
        return this.hora;
    }

    public void setHora(Time horaIn) {
        this.hora = horaIn;
    }

    public java.util.Date getFecha() {
        return this.fecha;
    }

    public void setFecha(java.util.Date fechaIn) {
        this.fecha = fechaIn;
    }

    public String getSede_codigo() {
        return this.sede_codigo;
    }

    public void setSede_codigo(String sede_codigoIn) {
        this.sede_codigo = sede_codigoIn;
    }

    public String getHistoria() {
        return this.historia;
    }

    public void setHistoria(String historiaIn) {
        this.historia = historiaIn;
    }

    public String getTipodcto_cod() {
        return this.tipodcto_cod;
    }

    public void setTipodcto_cod(String tipodcto_codIn) {
        this.tipodcto_cod = tipodcto_codIn;
    }

    public String getNit() {
        return this.nit;
    }

    public void setNit(String nitIn) {
        this.nit = nitIn;
    }

    public String getHorario() {
        return this.horario;
    }

    public void setHorario(String horarioIn) {
        this.horario = horarioIn;
    }

    public boolean getUrgencias() {
        return this.urgencias;
    }

    public void setUrgencias(boolean urgenciasIn) {
        this.urgencias = urgenciasIn;
    }

    public String getCat_codigo() {
        return this.cat_codigo;
    }

    public void setCat_codigo(String cat_codigoIn) {
        this.cat_codigo = cat_codigoIn;
    }

    public String getApe1() {
        return this.ape1;
    }

    public void setApe1(String ape1In) {
        this.ape1 = ape1In;
    }

    public String getNom1() {
        return this.nom1;
    }

    public void setNom1(String nom1In) {
        this.nom1 = nom1In;
    }

    public String getActe_codigo() {
        return this.acte_codigo;
    }

    public void setActe_codigo(String acte_codigoIn) {
        this.acte_codigo = acte_codigoIn;
    }

    public boolean getActivo() {
        return this.activo;
    }

    public void setActivo(boolean activoIn) {
        this.activo = activoIn;
    }

    public java.util.Date getFec_ent1() {
        return this.fec_ent1;
    }

    public void setFec_ent1(java.util.Date fec_ent1In) {
        this.fec_ent1 = fec_ent1In;
    }

    public java.util.Date getFec_ent2() {
        return this.fec_ent2;
    }

    public void setFec_ent2(java.util.Date fec_ent2In) {
        this.fec_ent2 = fec_ent2In;
    }

    public Time getHora_ent1() {
        return this.hora_ent1;
    }

    public void setHora_ent1(Time hora_ent1In) {
        this.hora_ent1 = hora_ent1In;
    }

    public Time getHora_ent2() {
        return this.hora_ent2;
    }

    public void setHora_ent2(Time hora_ent2In) {
        this.hora_ent2 = hora_ent2In;
    }

    public String getSede_codigo_in() {
        return this.sede_codigo_in;
    }

    public void setSede_codigo_in(String sede_codigo_inIn) {
        this.sede_codigo_in = sede_codigo_inIn;
    }

    public String getSede_codigo_re() {
        return this.sede_codigo_re;
    }

    public void setSede_codigo_re(String sede_codigo_reIn) {
        this.sede_codigo_re = sede_codigo_reIn;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public void setDireccion(String direccionIn) {
        this.direccion = direccionIn;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public void setTelefono(String telefonoIn) {
        this.telefono = telefonoIn;
    }

    public String getMedico_cod() {
        return this.medico_cod;
    }

    public void setMedico_cod(String medico_codIn) {
        this.medico_cod = medico_codIn;
    }

    public String getMedico() {
        return this.medico;
    }

    public void setMedico(String medicoIn) {
        this.medico = medicoIn;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String emailIn) {
        this.email = emailIn;
    }

    public String getClte_codigo() {
        return this.clte_codigo;
    }

    public void setClte_codigo(String clte_codigoIn) {
        this.clte_codigo = clte_codigoIn;
    }

    public java.util.Date getNacio() {
        return this.nacio;
    }

    public void setNacio(java.util.Date nacioIn) {
        this.nacio = nacioIn;
    }

    public int getEdad() {
        return this.edad;
    }

    public void setEdad(int edadIn) {
        this.edad = edadIn;
    }

    public String getMed_edad() {
        return this.med_edad;
    }

    public void setMed_edad(String med_edadIn) {
        this.med_edad = med_edadIn;
    }

    public String getSexo() {
        return this.sexo;
    }

    public void setSexo(String sexoIn) {
        this.sexo = sexoIn;
    }

    public String getObservaciones() {
        return this.observaciones;
    }

    public void setObservaciones(String observacionesIn) {
        this.observaciones = observacionesIn;
    }

    public String getCcosto_cod() {
        return this.ccosto_cod;
    }

    public void setCcosto_cod(String ccosto_codIn) {
        this.ccosto_cod = ccosto_codIn;
    }

    public String getFactura() {
        return this.factura;
    }

    public void setFactura(String facturaIn) {
        this.factura = facturaIn;
    }

    public String getTelmed() {
        return this.telmed;
    }

    public void setTelmed(String telmedIn) {
        this.telmed = telmedIn;
    }

    public boolean getFacturado() {
        return this.facturado;
    }

    public void setFacturado(boolean facturadoIn) {
        this.facturado = facturadoIn;
    }

    public String getTipo_a() {
        return this.tipo_a;
    }

    public void setTipo_a(String tipo_aIn) {
        this.tipo_a = tipo_aIn;
    }

    public java.util.Date getFecha_fac() {
        return this.fecha_fac;
    }

    public void setFecha_fac(java.util.Date fecha_facIn) {
        this.fecha_fac = fecha_facIn;
    }

    public double getPor_copago() {
        return this.por_copago;
    }

    public void setPor_copago(double por_copagoIn) {
        this.por_copago = por_copagoIn;
    }

    public double getVr_copago() {
        return this.vr_copago;
    }

    public void setVr_copago(double vr_copagoIn) {
        this.vr_copago = vr_copagoIn;
    }

    public double getAbono1() {
        return this.abono1;
    }

    public void setAbono1(double abono1In) {
        this.abono1 = abono1In;
    }

    public double getAbono2() {
        return this.abono2;
    }

    public void setAbono2(double abono2In) {
        this.abono2 = abono2In;
    }

    public String getTarifa() {
        return this.tarifa;
    }

    public void setTarifa(String tarifaIn) {
        this.tarifa = tarifaIn;
    }

    public double getPordes() {
        return this.pordes;
    }

    public void setPordes(double pordesIn) {
        this.pordes = pordesIn;
    }

    public double getVr_total() {
        return this.vr_total;
    }

    public void setVr_total(double vr_totalIn) {
        this.vr_total = vr_totalIn;
    }

    public double getDesto() {
        return this.desto;
    }

    public void setDesto(double destoIn) {
        this.desto = destoIn;
    }

    public double getDestop() {
        return this.destop;
    }

    public void setDestop(double destopIn) {
        this.destop = destopIn;
    }

    public double getCuotam() {
        return this.cuotam;
    }

    public void setCuotam(double cuotamIn) {
        this.cuotam = cuotamIn;
    }

    public String getCod_enla1() {
        return this.cod_enla1;
    }

    public void setCod_enla1(String cod_enla1In) {
        this.cod_enla1 = cod_enla1In;
    }

    public String getCod_enla2() {
        return this.cod_enla2;
    }

    public void setCod_enla2(String cod_enla2In) {
        this.cod_enla2 = cod_enla2In;
    }

    public String getCod_enla3() {
        return this.cod_enla3;
    }

    public void setCod_enla3(String cod_enla3In) {
        this.cod_enla3 = cod_enla3In;
    }

    public String getCaracteristicas() {
        return this.caracteristicas;
    }

    public void setCaracteristicas(String caracteristicasIn) {
        this.caracteristicas = caracteristicasIn;
    }

    public String getN_carnet() {
        return this.n_carnet;
    }

    public void setN_carnet(String n_carnetIn) {
        this.n_carnet = n_carnetIn;
    }

    public String getCiudad_cod() {
        return this.ciudad_cod;
    }

    public void setCiudad_cod(String ciudad_codIn) {
        this.ciudad_cod = ciudad_codIn;
    }

    public String getZona() {
        return this.zona;
    }

    public void setZona(String zonaIn) {
        this.zona = zonaIn;
    }

    public String getN_fac() {
        return this.n_fac;
    }

    public void setN_fac(String n_facIn) {
        this.n_fac = n_facIn;
    }

    public String getN_facdef() {
        return this.n_facdef;
    }

    public void setN_facdef(String n_facdefIn) {
        this.n_facdef = n_facdefIn;
    }

    public java.util.Date getFecha_facdef() {
        return this.fecha_facdef;
    }

    public void setFecha_facdef(java.util.Date fecha_facdefIn) {
        this.fecha_facdef = fecha_facdefIn;
    }

    public String getN_rec() {
        return this.n_rec;
    }

    public void setN_rec(String n_recIn) {
        this.n_rec = n_recIn;
    }

    public String getAutorizacion() {
        return this.autorizacion;
    }

    public void setAutorizacion(String autorizacionIn) {
        this.autorizacion = autorizacionIn;
    }

    public String getTurno_prec() {
        return this.turno_prec;
    }

    public void setTurno_prec(String turno_precIn) {
        this.turno_prec = turno_precIn;
    }

    public String getBonos() {
        return this.bonos;
    }

    public void setBonos(String bonosIn) {
        this.bonos = bonosIn;
    }

    public String getSoc_ppal() {
        return this.soc_ppal;
    }

    public void setSoc_ppal(String soc_ppalIn) {
        this.soc_ppal = soc_ppalIn;
    }

    public String getTel_soc() {
        return this.tel_soc;
    }

    public void setTel_soc(String tel_socIn) {
        this.tel_soc = tel_socIn;
    }

    public double getPeso() {
        return this.peso;
    }

    public void setPeso(double pesoIn) {
        this.peso = pesoIn;
    }

    public double getTalla() {
        return this.talla;
    }

    public void setTalla(double tallaIn) {
        this.talla = tallaIn;
    }

    public double getVolumen12() {
        return this.volumen12;
    }

    public void setVolumen12(double volumen12In) {
        this.volumen12 = volumen12In;
    }

    public double getVolumen24() {
        return this.volumen24;
    }

    public void setVolumen24(double volumen24In) {
        this.volumen24 = volumen24In;
    }

    public double getLibl1() {
        return this.libl1;
    }

    public void setLibl1(double libl1In) {
        this.libl1 = libl1In;
    }

    public double getLibl2() {
        return this.libl2;
    }

    public void setLibl2(double libl2In) {
        this.libl2 = libl2In;
    }

    public double getDesctosr1() {
        return this.desctosr1;
    }

    public void setDesctosr1(double desctosr1In) {
        this.desctosr1 = desctosr1In;
    }

    public double getDesctosr2() {
        return this.desctosr2;
    }

    public void setDesctosr2(double desctosr2In) {
        this.desctosr2 = desctosr2In;
    }

    public double getDesctosr3() {
        return this.desctosr3;
    }

    public void setDesctosr3(double desctosr3In) {
        this.desctosr3 = desctosr3In;
    }

    public double getLibr1() {
        return this.libr1;
    }

    public void setLibr1(double libr1In) {
        this.libr1 = libr1In;
    }

    public double getLibr2() {
        return this.libr2;
    }

    public void setLibr2(double libr2In) {
        this.libr2 = libr2In;
    }

    public double getLibr3() {
        return this.libr3;
    }

    public void setLibr3(double libr3In) {
        this.libr3 = libr3In;
    }

    public boolean getSi_inactivo() {
        return this.si_inactivo;
    }

    public void setSi_inactivo(boolean si_inactivoIn) {
        this.si_inactivo = si_inactivoIn;
    }

    public boolean getSi_remi() {
        return this.si_remi;
    }

    public void setSi_remi(boolean si_remiIn) {
        this.si_remi = si_remiIn;
    }

    public boolean getSi_anul() {
        return this.si_anul;
    }

    public void setSi_anul(boolean si_anulIn) {
        this.si_anul = si_anulIn;
    }

    public boolean getSi_factu() {
        return this.si_factu;
    }

    public void setSi_factu(boolean si_factuIn) {
        this.si_factu = si_factuIn;
    }

    public boolean getSi_band01() {
        return this.si_band01;
    }

    public void setSi_band01(boolean si_band01In) {
        this.si_band01 = si_band01In;
    }

    public boolean getSi_band02() {
        return this.si_band02;
    }

    public void setSi_band02(boolean si_band02In) {
        this.si_band02 = si_band02In;
    }

    public boolean getSi_band03() {
        return this.si_band03;
    }

    public void setSi_band03(boolean si_band03In) {
        this.si_band03 = si_band03In;
    }

    public boolean getSi_band04() {
        return this.si_band04;
    }

    public void setSi_band04(boolean si_band04In) {
        this.si_band04 = si_band04In;
    }

    public boolean getSi_band05() {
        return this.si_band05;
    }

    public void setSi_band05(boolean si_band05In) {
        this.si_band05 = si_band05In;
    }

    public boolean getSi_band06() {
        return this.si_band06;
    }

    public void setSi_band06(boolean si_band06In) {
        this.si_band06 = si_band06In;
    }

    public boolean getSi_band07() {
        return this.si_band07;
    }

    public void setSi_band07(boolean si_band07In) {
        this.si_band07 = si_band07In;
    }

    public boolean getSi_band08() {
        return this.si_band08;
    }

    public void setSi_band08(boolean si_band08In) {
        this.si_band08 = si_band08In;
    }

    public boolean getSi_band09() {
        return this.si_band09;
    }

    public void setSi_band09(boolean si_band09In) {
        this.si_band09 = si_band09In;
    }

    public boolean getSi_band10() {
        return this.si_band10;
    }

    public void setSi_band10(boolean si_band10In) {
        this.si_band10 = si_band10In;
    }

    public boolean getSi_band11() {
        return this.si_band11;
    }

    public void setSi_band11(boolean si_band11In) {
        this.si_band11 = si_band11In;
    }

    public boolean getSi_band12() {
        return this.si_band12;
    }

    public void setSi_band12(boolean si_band12In) {
        this.si_band12 = si_band12In;
    }

    public boolean getSi_band13() {
        return this.si_band13;
    }

    public void setSi_band13(boolean si_band13In) {
        this.si_band13 = si_band13In;
    }

    public boolean getSi_band14() {
        return this.si_band14;
    }

    public void setSi_band14(boolean si_band14In) {
        this.si_band14 = si_band14In;
    }

    public boolean getSi_band15() {
        return this.si_band15;
    }

    public void setSi_band15(boolean si_band15In) {
        this.si_band15 = si_band15In;
    }

    public boolean getSi_band16() {
        return this.si_band16;
    }

    public void setSi_band16(boolean si_band16In) {
        this.si_band16 = si_band16In;
    }

    public boolean getSi_band17() {
        return this.si_band17;
    }

    public void setSi_band17(boolean si_band17In) {
        this.si_band17 = si_band17In;
    }

    public boolean getSi_band18() {
        return this.si_band18;
    }

    public void setSi_band18(boolean si_band18In) {
        this.si_band18 = si_band18In;
    }

    public boolean getSi_band19() {
        return this.si_band19;
    }

    public void setSi_band19(boolean si_band19In) {
        this.si_band19 = si_band19In;
    }

    public boolean getSi_envio_res_email() {
        return this.si_envio_res_email;
    }

    public void setSi_envio_res_email(boolean si_envio_res_emailIn) {
        this.si_envio_res_email = si_envio_res_emailIn;
    }

    public String getConse_emp() {
        return this.conse_emp;
    }

    public void setConse_emp(String conse_empIn) {
        this.conse_emp = conse_empIn;
    }

    public String getDoc_socio() {
        return this.doc_socio;
    }

    public void setDoc_socio(String doc_socioIn) {
        this.doc_socio = doc_socioIn;
    }

    public String getAutorizo_des() {
        return this.autorizo_des;
    }

    public void setAutorizo_des(String autorizo_desIn) {
        this.autorizo_des = autorizo_desIn;
    }

    public String getCelular() {
        return this.celular;
    }

    public void setCelular(String celularIn) {
        this.celular = celularIn;
    }

    public java.util.Date getFecha_factura() {
        return this.fecha_factura;
    }

    public void setFecha_factura(java.util.Date fecha_facturaIn) {
        this.fecha_factura = fecha_facturaIn;
    }

    public Image getFoto() {
        return this.foto;
    }

    public void setFoto(Image fotoIn) {
        this.foto = fotoIn;
    }

    public Image getHuella() {
        return this.huella;
    }

    public void setHuella(Image huellaIn) {
        this.huella = huellaIn;
    }

    public Image getFirma() {
        return this.firma;
    }

    public void setFirma(Image firmaIn) {
        this.firma = firmaIn;
    }

    public Image getDocumentos() {
        return this.documentos;
    }

    public void setDocumentos(Image documentosIn) {
        this.documentos = documentosIn;
    }

    public String getUsr_codigo() {
        return this.usr_codigo;
    }

    public void setUsr_codigo(String usr_codigoIn) {
        this.usr_codigo = usr_codigoIn;
    }

    public String getFteing_cod() {
        return this.fteing_cod;
    }

    public void setFteing_cod(String fteing_codIn) {
        this.fteing_cod = fteing_codIn;
    }

    public String getCama_cod() {
        return this.cama_cod;
    }

    public void setCama_cod(String cama_codIn) {
        this.cama_cod = cama_codIn;
    }

    public java.util.Date getFecha_grabo() {
        return this.fecha_grabo;
    }

    public void setFecha_grabo(java.util.Date fecha_graboIn) {
        this.fecha_grabo = fecha_graboIn;
    }

    public Time getHora_grabo() {
        return this.hora_grabo;
    }

    public void setHora_grabo(Time hora_graboIn) {
        this.hora_grabo = hora_graboIn;
    }

    public String getUsu_facdef() {
        return this.usu_facdef;
    }

    public void setUsu_facdef(String usu_facdefIn) {
        this.usu_facdef = usu_facdefIn;
    }

    public String getNum_remision() {
        return this.num_remision;
    }

    public void setNum_remision(String num_remisionIn) {
        this.num_remision = num_remisionIn;
    }

    public java.util.Date getFecha_remision() {
        return this.fecha_remision;
    }

    public void setFecha_remision(java.util.Date fecha_remisionIn) {
        this.fecha_remision = fecha_remisionIn;
    }

    public String getUsu_remision() {
        return this.usu_remision;
    }

    public void setUsu_remision(String usu_remisionIn) {
        this.usu_remision = usu_remisionIn;
    }

    public void setAll(String paciente_codIn,
            Time horaIn,
            java.util.Date fechaIn,
            String sede_codigoIn,
            String historiaIn,
            String tipodcto_codIn,
            String nitIn,
            String horarioIn,
            boolean urgenciasIn,
            String cat_codigoIn,
            String ape1In,
            String nom1In,
            String acte_codigoIn,
            boolean activoIn,
            java.util.Date fec_ent1In,
            java.util.Date fec_ent2In,
            Time hora_ent1In,
            Time hora_ent2In,
            String sede_codigo_inIn,
            String sede_codigo_reIn,
            String direccionIn,
            String telefonoIn,
            String medico_codIn,
            String medicoIn,
            String emailIn,
            String clte_codigoIn,
            java.util.Date nacioIn,
            int edadIn,
            String med_edadIn,
            String sexoIn,
            String observacionesIn,
            String ccosto_codIn,
            String facturaIn,
            String telmedIn,
            boolean facturadoIn,
            String tipo_aIn,
            java.util.Date fecha_facIn,
            double por_copagoIn,
            double vr_copagoIn,
            double abono1In,
            double abono2In,
            String tarifaIn,
            double pordesIn,
            double vr_totalIn,
            double destoIn,
            double destopIn,
            double cuotamIn,
            String cod_enla1In,
            String cod_enla2In,
            String cod_enla3In,
            String caracteristicasIn,
            String n_carnetIn,
            String ciudad_codIn,
            String zonaIn,
            String n_facIn,
            String n_facdefIn,
            java.util.Date fecha_facdefIn,
            String n_recIn,
            String autorizacionIn,
            String turno_precIn,
            String bonosIn,
            String soc_ppalIn,
            String tel_socIn,
            double pesoIn,
            double tallaIn,
            double volumen12In,
            double volumen24In,
            double libl1In,
            double libl2In,
            double desctosr1In,
            double desctosr2In,
            double desctosr3In,
            double libr1In,
            double libr2In,
            double libr3In,
            boolean si_inactivoIn,
            boolean si_remiIn,
            boolean si_anulIn,
            boolean si_factuIn,
            boolean si_band01In,
            boolean si_band02In,
            boolean si_band03In,
            boolean si_band04In,
            boolean si_band05In,
            boolean si_band06In,
            boolean si_band07In,
            boolean si_band08In,
            boolean si_band09In,
            boolean si_band10In,
            boolean si_band11In,
            boolean si_band12In,
            boolean si_band13In,
            boolean si_band14In,
            boolean si_band15In,
            boolean si_band16In,
            boolean si_band17In,
            boolean si_band18In,
            boolean si_band19In,
            boolean si_envio_res_emailIn,
            String conse_empIn,
            String doc_socioIn,
            String autorizo_desIn,
            String celularIn,
            java.util.Date fecha_facturaIn,
            String usr_codigoIn,
            String fteing_codIn,
            String cama_codIn,
            java.util.Date fecha_graboIn,
            Time hora_graboIn,
            String usu_facdefIn,
            String num_remisionIn,
            java.util.Date fecha_remisionIn,
            String usu_remisionIn) {
        this.paciente_cod = paciente_codIn;
        this.hora = horaIn;
        this.fecha = fechaIn;
        this.sede_codigo = sede_codigoIn;
        this.historia = historiaIn;
        this.tipodcto_cod = tipodcto_codIn;
        this.nit = nitIn;
        this.horario = horarioIn;
        this.urgencias = urgenciasIn;
        this.cat_codigo = cat_codigoIn;
        this.ape1 = ape1In;
        this.nom1 = nom1In;
        this.acte_codigo = acte_codigoIn;
        this.activo = activoIn;
        this.fec_ent1 = fec_ent1In;
        this.fec_ent2 = fec_ent2In;
        this.hora_ent1 = hora_ent1In;
        this.hora_ent2 = hora_ent2In;
        this.sede_codigo_in = sede_codigo_inIn;
        this.sede_codigo_re = sede_codigo_reIn;
        this.direccion = direccionIn;
        this.telefono = telefonoIn;
        this.medico_cod = medico_codIn;
        this.medico = medicoIn;
        this.email = emailIn;
        this.clte_codigo = clte_codigoIn;
        this.nacio = nacioIn;
        this.edad = edadIn;
        this.med_edad = med_edadIn;
        this.sexo = sexoIn;
        this.observaciones = observacionesIn;
        this.ccosto_cod = ccosto_codIn;
        this.factura = facturaIn;
        this.telmed = telmedIn;
        this.facturado = facturadoIn;
        this.tipo_a = tipo_aIn;
        this.fecha_fac = fecha_facIn;
        this.por_copago = por_copagoIn;
        this.vr_copago = vr_copagoIn;
        this.abono1 = abono1In;
        this.abono2 = abono2In;
        this.tarifa = tarifaIn;
        this.pordes = pordesIn;
        this.vr_total = vr_totalIn;
        this.desto = destoIn;
        this.destop = destopIn;
        this.cuotam = cuotamIn;
        this.cod_enla1 = cod_enla1In;
        this.cod_enla2 = cod_enla2In;
        this.cod_enla3 = cod_enla3In;
        this.caracteristicas = caracteristicasIn;
        this.n_carnet = n_carnetIn;
        this.ciudad_cod = ciudad_codIn;
        this.zona = zonaIn;
        this.n_fac = n_facIn;
        this.n_facdef = n_facdefIn;
        this.fecha_facdef = fecha_facdefIn;
        this.n_rec = n_recIn;
        this.autorizacion = autorizacionIn;
        this.turno_prec = turno_precIn;
        this.bonos = bonosIn;
        this.soc_ppal = soc_ppalIn;
        this.tel_soc = tel_socIn;
        this.peso = pesoIn;
        this.talla = tallaIn;
        this.volumen12 = volumen12In;
        this.volumen24 = volumen24In;
        this.libl1 = libl1In;
        this.libl2 = libl2In;
        this.desctosr1 = desctosr1In;
        this.desctosr2 = desctosr2In;
        this.desctosr3 = desctosr3In;
        this.libr1 = libr1In;
        this.libr2 = libr2In;
        this.libr3 = libr3In;
        this.si_inactivo = si_inactivoIn;
        this.si_remi = si_remiIn;
        this.si_anul = si_anulIn;
        this.si_factu = si_factuIn;
        this.si_band01 = si_band01In;
        this.si_band02 = si_band02In;
        this.si_band03 = si_band03In;
        this.si_band04 = si_band04In;
        this.si_band05 = si_band05In;
        this.si_band06 = si_band06In;
        this.si_band07 = si_band07In;
        this.si_band08 = si_band08In;
        this.si_band09 = si_band09In;
        this.si_band10 = si_band10In;
        this.si_band11 = si_band11In;
        this.si_band12 = si_band12In;
        this.si_band13 = si_band13In;
        this.si_band14 = si_band14In;
        this.si_band15 = si_band15In;
        this.si_band16 = si_band16In;
        this.si_band17 = si_band17In;
        this.si_band18 = si_band18In;
        this.si_band19 = si_band19In;
        this.si_envio_res_email = si_envio_res_emailIn;
        this.conse_emp = conse_empIn;
        this.doc_socio = doc_socioIn;
        this.autorizo_des = autorizo_desIn;
        this.celular = celularIn;
        this.fecha_factura = fecha_facturaIn;
        this.usr_codigo = usr_codigoIn;
        this.fteing_cod = fteing_codIn;
        this.cama_cod = cama_codIn;
        this.fecha_grabo = fecha_graboIn;
        this.hora_grabo = hora_graboIn;
        this.usu_facdef = usu_facdefIn;
        this.num_remision = num_remisionIn;
        this.fecha_remision = fecha_remisionIn;
        this.usu_remision = usu_remisionIn;
    }

    @Override
    public String toString() {
        return "Paciente{" + "paciente_cod=" + paciente_cod + ", hora=" + hora + ", fecha=" + fecha + ", sede_codigo="
                + sede_codigo + ", historia=" + historia + ", tipodcto_cod=" + tipodcto_cod + ", nit=" + nit + ", horario="
                + horario + ", urgencias=" + urgencias + ", cat_codigo=" + cat_codigo + ", ape1=" + ape1 + ", nom1=" + nom1
                + ", acte_codigo=" + acte_codigo + ", activo=" + activo + ", fec_ent1=" + fec_ent1 + ", fec_ent2=" + fec_ent2
                + ", hora_ent1=" + hora_ent1 + ", hora_ent2=" + hora_ent2 + ", sede_codigo_in=" + sede_codigo_in
                + ", sede_codigo_re=" + sede_codigo_re + ", direccion=" + direccion + ", telefono=" + telefono + ", medico_cod="
                + medico_cod + ", medico=" + medico + ", email=" + email + ", clte_codigo=" + clte_codigo + ", nacio=" + nacio
                + ", edad=" + edad + ", med_edad=" + med_edad + ", sexo=" + sexo + ", observaciones=" + observaciones
                + ", ccosto_cod=" + ccosto_cod + ", factura=" + factura + ", telmed=" + telmed + ", facturado=" + facturado
                + ", tipo_a=" + tipo_a + ", fecha_fac=" + fecha_fac + ", por_copago=" + por_copago + ", vr_copago=" + vr_copago
                + ", abono1=" + abono1 + ", abono2=" + abono2 + ", tarifa=" + tarifa + ", pordes=" + pordes + ", vr_total="
                + vr_total + ", desto=" + desto + ", destop=" + destop + ", cuotam=" + cuotam + ", cod_enla1=" + cod_enla1
                + ", cod_enla2=" + cod_enla2 + ", cod_enla3=" + cod_enla3 + ", caracteristicas=" + caracteristicas + ", n_carnet="
                + n_carnet + ", ciudad_cod=" + ciudad_cod + ", zona=" + zona + ", n_fac=" + n_fac + ", n_facdef=" + n_facdef
                + ", fecha_facdef=" + fecha_facdef + ", n_rec=" + n_rec + ", autorizacion=" + autorizacion + ", turno_prec="
                + turno_prec + ", bonos=" + bonos + ", soc_ppal=" + soc_ppal + ", tel_soc=" + tel_soc + ", peso=" + peso + ", talla="
                + talla + ", volumen12=" + volumen12 + ", volumen24=" + volumen24 + ", libl1=" + libl1 + ", libl2=" + libl2
                + ", desctosr1=" + desctosr1 + ", desctosr2=" + desctosr2 + ", desctosr3=" + desctosr3 + ", libr1=" + libr1
                + ", libr2=" + libr2 + ", libr3=" + libr3 + ", si_inactivo=" + si_inactivo + ", si_remi=" + si_remi + ", si_anul="
                + si_anul + ", si_factu=" + si_factu + ", si_band01=" + si_band01 + ", si_band02=" + si_band02 + ", si_band03="
                + si_band03 + ", si_band04=" + si_band04 + ", si_band05=" + si_band05 + ", si_band06=" + si_band06 + ", si_band07="
                + si_band07 + ", si_band08=" + si_band08 + ", si_band09=" + si_band09 + ", si_band10=" + si_band10 + ", si_band11="
                + si_band11 + ", si_band12=" + si_band12 + ", si_band13=" + si_band13 + ", si_band14=" + si_band14 + ", si_band15="
                + si_band15 + ", si_band16=" + si_band16 + ", si_band17=" + si_band17 + ", si_band18=" + si_band18 + ", si_band19="
                + si_band19 + ", si_envio_res_email=" + si_envio_res_email + ", conse_emp=" + conse_emp + ", doc_socio=" + doc_socio
                + ", autorizo_des=" + autorizo_des + ", celular=" + celular + ", fecha_factura=" + fecha_factura + ", foto=" + foto
                + ", huella=" + huella + ", firma=" + firma + ", documentos=" + documentos + ", usr_codigo=" + usr_codigo
                + ", fteing_cod=" + fteing_cod + ", cama_cod=" + cama_cod + ", fecha_grabo=" + fecha_grabo + ", hora_grabo="
                + hora_grabo + ", usu_facdef=" + usu_facdef + ", num_remision=" + num_remision + ", fecha_remision=" + fecha_remision
                + ", usu_remision=" + usu_remision + '}';
    }

}
