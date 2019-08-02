package Model;

import java.sql.Time;
import java.util.Date;

/**
 *
 * @author ADMINIMAT
 */
public class Pacodbc {

    String cod_odbc, tipodcto_cod, nit, cod_enla1, cod_enla2, cod_enla3;
    Time hora;
    Date fecha;
    String historia;
    boolean urgencias;
    String cat_codigo, ape1, nom1, acte_codigo;
    boolean activo;
    String direccion, telefono, medico_cod, medico, email, clte_codigo, clte_nombre;
    Date nacio;
    String sexo, ccosto_cod, detalle_ccosto, tipo_a;
    double por_copago, vr_copago;
    String n_carnet, ciudad_cod, zona, bonos, soc_ppal, tel_soc;
    double peso, talla, volumen12, volumen24;
    String conse_emp, doc_socio, celular, cama_cod, detalle_cama;
    Date fecha_grabo;
    Time hora_grabo;
    Date fecha_cruce;
    Time hora_cruce;
    String usuario_cruce, sede_cruce, cod_pac_cruce;
    boolean procesado;

    public Pacodbc() {
    }

    public Pacodbc(String cod_odbc, String tipodcto_cod, String nit, String cod_enla1, String cod_enla2, String cod_enla3, Time hora, Date fecha, String historia, boolean urgencias, String cat_codigo, String ape1, String nom1, String acte_codigo, boolean activo, String direccion, String telefono, String medico_cod, String medico, String email, String clte_codigo, String clte_nombre, Date nacio, String sexo, String ccosto_cod, String detalle_ccosto, String tipo_a, double por_copago, double vr_copago, String n_carnet, String ciudad_cod, String zona, String bonos, String soc_ppal, String tel_soc, double peso, double talla, double volumen12, double volumen24, String conse_emp, String doc_socio, String celular, String cama_cod, String detalle_cama, Date fecha_grabo, Time hora_grabo, Date fecha_cruce, Time hora_cruce, String usuario_cruce, String sede_cruce, String cod_pac_cruce, boolean procesado) {
        this.cod_odbc = cod_odbc;
        this.tipodcto_cod = tipodcto_cod;
        this.nit = nit;
        this.cod_enla1 = cod_enla1;
        this.cod_enla2 = cod_enla2;
        this.cod_enla3 = cod_enla3;
        this.hora = hora;
        this.fecha = fecha;
        this.historia = historia;
        this.urgencias = urgencias;
        this.cat_codigo = cat_codigo;
        this.ape1 = ape1;
        this.nom1 = nom1;
        this.acte_codigo = acte_codigo;
        this.activo = activo;
        this.direccion = direccion;
        this.telefono = telefono;
        this.medico_cod = medico_cod;
        this.medico = medico;
        this.email = email;
        this.clte_codigo = clte_codigo;
        this.clte_nombre = clte_nombre;
        this.nacio = nacio;
        this.sexo = sexo;
        this.ccosto_cod = ccosto_cod;
        this.detalle_ccosto = detalle_ccosto;
        this.tipo_a = tipo_a;
        this.por_copago = por_copago;
        this.vr_copago = vr_copago;
        this.n_carnet = n_carnet;
        this.ciudad_cod = ciudad_cod;
        this.zona = zona;
        this.bonos = bonos;
        this.soc_ppal = soc_ppal;
        this.tel_soc = tel_soc;
        this.peso = peso;
        this.talla = talla;
        this.volumen12 = volumen12;
        this.volumen24 = volumen24;
        this.conse_emp = conse_emp;
        this.doc_socio = doc_socio;
        this.celular = celular;
        this.cama_cod = cama_cod;
        this.detalle_cama = detalle_cama;
        this.fecha_grabo = fecha_grabo;
        this.hora_grabo = hora_grabo;
        this.fecha_cruce = fecha_cruce;
        this.hora_cruce = hora_cruce;
        this.usuario_cruce = usuario_cruce;
        this.sede_cruce = sede_cruce;
        this.cod_pac_cruce = cod_pac_cruce;
        this.procesado = procesado;
    }

    public void setAll(String cod_odbc, String tipodcto_cod, String nit, String cod_enla1, String cod_enla2, String cod_enla3, Time hora, Date fecha, String historia, boolean urgencias, String cat_codigo, String ape1, String nom1, String acte_codigo, boolean activo, String direccion, String telefono, String medico_cod, String medico, String email, String clte_codigo, String clte_nombre, Date nacio, String sexo, String ccosto_cod, String detalle_ccosto, String tipo_a, double por_copago, double vr_copago, String n_carnet, String ciudad_cod, String zona, String bonos, String soc_ppal, String tel_soc, double peso, double talla, double volumen12, double volumen24, String conse_emp, String doc_socio, String celular, String cama_cod, String detalle_cama, Date fecha_grabo, Time hora_grabo, Date fecha_cruce, Time hora_cruce, String usuario_cruce, String sede_cruce, String cod_pac_cruce, boolean procesado) {
        this.cod_odbc = cod_odbc;
        this.tipodcto_cod = tipodcto_cod;
        this.nit = nit;
        this.cod_enla1 = cod_enla1;
        this.cod_enla2 = cod_enla2;
        this.cod_enla3 = cod_enla3;
        this.hora = hora;
        this.fecha = fecha;
        this.historia = historia;
        this.urgencias = urgencias;
        this.cat_codigo = cat_codigo;
        this.ape1 = ape1;
        this.nom1 = nom1;
        this.acte_codigo = acte_codigo;
        this.activo = activo;
        this.direccion = direccion;
        this.telefono = telefono;
        this.medico_cod = medico_cod;
        this.medico = medico;
        this.email = email;
        this.clte_codigo = clte_codigo;
        this.clte_nombre = clte_nombre;
        this.nacio = nacio;
        this.sexo = sexo;
        this.ccosto_cod = ccosto_cod;
        this.detalle_ccosto = detalle_ccosto;
        this.tipo_a = tipo_a;
        this.por_copago = por_copago;
        this.vr_copago = vr_copago;
        this.n_carnet = n_carnet;
        this.ciudad_cod = ciudad_cod;
        this.zona = zona;
        this.bonos = bonos;
        this.soc_ppal = soc_ppal;
        this.tel_soc = tel_soc;
        this.peso = peso;
        this.talla = talla;
        this.volumen12 = volumen12;
        this.volumen24 = volumen24;
        this.conse_emp = conse_emp;
        this.doc_socio = doc_socio;
        this.celular = celular;
        this.cama_cod = cama_cod;
        this.detalle_cama = detalle_cama;
        this.fecha_grabo = fecha_grabo;
        this.hora_grabo = hora_grabo;
        this.fecha_cruce = fecha_cruce;
        this.hora_cruce = hora_cruce;
        this.usuario_cruce = usuario_cruce;
        this.sede_cruce = sede_cruce;
        this.cod_pac_cruce = cod_pac_cruce;
        this.procesado = procesado;
    }

    public String getCod_odbc() {
        return cod_odbc;
    }

    public void setCod_odbc(String cod_odbc) {
        this.cod_odbc = cod_odbc;
    }

    public String getTipodcto_cod() {
        return tipodcto_cod;
    }

    public void setTipodcto_cod(String tipodcto_cod) {
        this.tipodcto_cod = tipodcto_cod;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getCod_enla1() {
        return cod_enla1;
    }

    public void setCod_enla1(String cod_enla1) {
        this.cod_enla1 = cod_enla1;
    }

    public String getCod_enla2() {
        return cod_enla2;
    }

    public void setCod_enla2(String cod_enla2) {
        this.cod_enla2 = cod_enla2;
    }

    public String getCod_enla3() {
        return cod_enla3;
    }

    public void setCod_enla3(String cod_enla3) {
        this.cod_enla3 = cod_enla3;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getHistoria() {
        return historia;
    }

    public void setHistoria(String historia) {
        this.historia = historia;
    }

    public boolean getUrgencias() {
        return urgencias;
    }

    public void setUrgencias(boolean urgencias) {
        this.urgencias = urgencias;
    }

    public String getCat_codigo() {
        return cat_codigo;
    }

    public void setCat_codigo(String cat_codigo) {
        this.cat_codigo = cat_codigo;
    }

    public String getApe1() {
        return ape1;
    }

    public void setApe1(String ape1) {
        this.ape1 = ape1;
    }

    public String getNom1() {
        return nom1;
    }

    public void setNom1(String nom1) {
        this.nom1 = nom1;
    }

    public String getActe_codigo() {
        return acte_codigo;
    }

    public void setActe_codigo(String acte_codigo) {
        this.acte_codigo = acte_codigo;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getMedico_cod() {
        return medico_cod;
    }

    public void setMedico_cod(String medico_cod) {
        this.medico_cod = medico_cod;
    }

    public String getMedico() {
        return medico;
    }

    public void setMedico(String medico) {
        this.medico = medico;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClte_codigo() {
        return clte_codigo;
    }

    public void setClte_codigo(String clte_codigo) {
        this.clte_codigo = clte_codigo;
    }

    public String getClte_nombre() {
        return clte_nombre;
    }

    public void setClte_nombre(String clte_nombre) {
        this.clte_nombre = clte_nombre;
    }

    public Date getNacio() {
        return nacio;
    }

    public void setNacio(Date nacio) {
        this.nacio = nacio;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCcosto_cod() {
        return ccosto_cod;
    }

    public void setCcosto_cod(String ccosto_cod) {
        this.ccosto_cod = ccosto_cod;
    }

    public String getDetalle_ccosto() {
        return detalle_ccosto;
    }

    public void setDetalle_ccosto(String detalle_ccosto) {
        this.detalle_ccosto = detalle_ccosto;
    }

    public String getTipo_a() {
        return tipo_a;
    }

    public void setTipo_a(String tipo_a) {
        this.tipo_a = tipo_a;
    }

    public double getPor_copago() {
        return por_copago;
    }

    public void setPor_copago(double por_copago) {
        this.por_copago = por_copago;
    }

    public double getVr_copago() {
        return vr_copago;
    }

    public void setVr_copago(double vr_copago) {
        this.vr_copago = vr_copago;
    }

    public String getN_carnet() {
        return n_carnet;
    }

    public void setN_carnet(String n_carnet) {
        this.n_carnet = n_carnet;
    }

    public String getCiudad_cod() {
        return ciudad_cod;
    }

    public void setCiudad_cod(String ciudad_cod) {
        this.ciudad_cod = ciudad_cod;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public String getBonos() {
        return bonos;
    }

    public void setBonos(String bonos) {
        this.bonos = bonos;
    }

    public String getSoc_ppal() {
        return soc_ppal;
    }

    public void setSoc_ppal(String soc_ppal) {
        this.soc_ppal = soc_ppal;
    }

    public String getTel_soc() {
        return tel_soc;
    }

    public void setTel_soc(String tel_soc) {
        this.tel_soc = tel_soc;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getTalla() {
        return talla;
    }

    public void setTalla(double talla) {
        this.talla = talla;
    }

    public double getVolumen12() {
        return volumen12;
    }

    public void setVolumen12(double volumen12) {
        this.volumen12 = volumen12;
    }

    public double getVolumen24() {
        return volumen24;
    }

    public void setVolumen24(double volumen24) {
        this.volumen24 = volumen24;
    }

    public String getConse_emp() {
        return conse_emp;
    }

    public void setConse_emp(String conse_emp) {
        this.conse_emp = conse_emp;
    }

    public String getDoc_socio() {
        return doc_socio;
    }

    public void setDoc_socio(String doc_socio) {
        this.doc_socio = doc_socio;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getCama_cod() {
        return cama_cod;
    }

    public void setCama_cod(String cama_cod) {
        this.cama_cod = cama_cod;
    }

    public String getDetalle_cama() {
        return detalle_cama;
    }

    public void setDetalle_cama(String detalle_cama) {
        this.detalle_cama = detalle_cama;
    }

    public Date getFecha_grabo() {
        return fecha_grabo;
    }

    public void setFecha_grabo(Date fecha_grabo) {
        this.fecha_grabo = fecha_grabo;
    }

    public Time getHora_grabo() {
        return hora_grabo;
    }

    public void setHora_grabo(Time hora_grabo) {
        this.hora_grabo = hora_grabo;
    }

    public Date getFecha_cruce() {
        return fecha_cruce;
    }

    public void setFecha_cruce(Date fecha_cruce) {
        this.fecha_cruce = fecha_cruce;
    }

    public Time getHora_cruce() {
        return hora_cruce;
    }

    public void setHora_cruce(Time hora_cruce) {
        this.hora_cruce = hora_cruce;
    }

    public String getUsuario_cruce() {
        return usuario_cruce;
    }

    public void setUsuario_cruce(String usuario_cruce) {
        this.usuario_cruce = usuario_cruce;
    }

    public String getSede_cruce() {
        return sede_cruce;
    }

    public void setSede_cruce(String sede_cruce) {
        this.sede_cruce = sede_cruce;
    }

    public String getCod_pac_cruce() {
        return cod_pac_cruce;
    }

    public void setCod_pac_cruce(String cod_pac_cruce) {
        this.cod_pac_cruce = cod_pac_cruce;
    }

    public boolean getProcesado() {
        return procesado;
    }

    public void setProcesado(boolean procesado) {
        this.procesado = procesado;
    }

    @Override
    public String toString() {
        return "pacodbc{" + "cod_odbc=" + cod_odbc + ", tipodcto_cod=" + tipodcto_cod + ", nit=" + nit + ", cod_enla1=" + cod_enla1
                + ", cod_enla2=" + cod_enla2 + ", cod_enla3=" + cod_enla3 + ", hora=" + hora + ", fecha=" + fecha + ", historia=" + historia
                + ", urgencias=" + urgencias + ", cat_codigo=" + cat_codigo + ", ape1=" + ape1 + ", nom1=" + nom1 + ", acte_codigo=" + acte_codigo
                + ", activo=" + activo + ", direccion=" + direccion + ", telefono=" + telefono + ", medico_cod=" + medico_cod
                + ", medico=" + medico + ", email=" + email + ", clte_codigo=" + clte_codigo + ", clte_nombre=" + clte_nombre + ", nacio=" + nacio
                + ", sexo=" + sexo + ", ccosto_cod=" + ccosto_cod + ", detalle_ccosto=" + detalle_ccosto + ", tipo_a=" + tipo_a
                + ", por_copago=" + por_copago + ", vr_copago=" + vr_copago + ", n_carnet=" + n_carnet + ", ciudad_cod=" + ciudad_cod
                + ", zona=" + zona + ", bonos=" + bonos + ", soc_ppal=" + soc_ppal + ", tel_soc=" + tel_soc + ", peso=" + peso
                + ", talla=" + talla + ", volumen12=" + volumen12 + ", volumen24=" + volumen24 + ", conse_emp=" + conse_emp
                + ", doc_socio=" + doc_socio + ", celular=" + celular + ", cama_cod=" + cama_cod + ", detalle_cama=" + detalle_cama
                + ", fecha_grabo=" + fecha_grabo + ", hora_grabo=" + hora_grabo + ", fecha_cruce=" + fecha_cruce + ", hora_cruce=" + hora_cruce
                + ", usuario_cruce=" + usuario_cruce + ", sede_cruce=" + sede_cruce + ", cod_pac_cruce=" + cod_pac_cruce + ", procesado=" + procesado + '}';
    }

    public String createSQL() {
        String sql;
        sql = "INSERT INTO pacodbc (cod_odbc,tipodcto_cod,nit,cod_enla1,cod_enla2,cod_enla3,hora,fecha,historia"
                + ",urgencias,cat_codigo,ape1,nom1,acte_codigo,activo,direccion,telefono,medico_cod"
                + ",medico,email,clte_codigo,clte_nombre,nacio,sexo,ccosto_cod,detalle_ccosto,tipo_a"
                + ",por_copago,vr_copago,n_carnet,ciudad_cod,zona,bonos,soc_ppal,tel_soc,peso"
                + ",talla,volumen12,volumen24,conse_emp,doc_socio,celular,cama_cod,detalle_cama"
                + ",fecha_grabo,hora_grabo,fecha_cruce,hora_cruce,usuario_cruce,sede_cruce,cod_pac_cruce,procesado)"
                + "VALUES(" + cod_odbc + "," + tipodcto_cod + "," + nit + "," + cod_enla1 + "," + cod_enla2 + "," + cod_enla3 + "," + hora + "," + fecha + "," + historia + "," + urgencias
                + "," + cat_codigo + "," + "," + ape1 + "," + nom1 + "," + acte_codigo + "," + activo + "," + direccion + "," + telefono + "," + medico_cod
                + "," + medico + "," + email + "," + clte_codigo + "," + clte_nombre + "," + nacio + "," + sexo + "," + ccosto_cod + "," + detalle_ccosto
                + "," + tipo_a + "," + por_copago + "," + vr_copago + "," + n_carnet + "," + ciudad_cod + "," + zona + "," + bonos + "," + soc_ppal + "," + tel_soc
                + "," + peso + "," + talla + "," + volumen12 + "," + volumen24 + "," + conse_emp + "," + doc_socio + "," + celular + "," + cama_cod + "," + detalle_cama
                + "," + fecha_grabo + "," + hora_grabo + "," + fecha_cruce + "," + hora_cruce + "," + usuario_cruce + "," + sede_cruce + "," + cod_pac_cruce + "," + procesado
                + ")";
        return sql;
    }

}
