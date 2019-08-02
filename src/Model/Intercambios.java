package Model;

/**
 *
 * @author fberrocalm Creado en: 20/02/2015
 */
import java.io.*;
import java.sql.*;

public class Intercambios implements Cloneable, Serializable {

    private int consecutivo;
    private String sede_origen;
    private String proceso;
    private String cod_tabla;
    private String campo;
    private String evento;
    private String usuario;
    private String llave1;
    private String llave2;
    private String llave3;
    private String llave4;
    private String llave5;
    private boolean estado1;
    private boolean estado2;
    private boolean estado3;
    private boolean estado4;
    private boolean estado5;
    private boolean estado6;
    private boolean estado7;
    private boolean estado8;
    private boolean estado9;
    private boolean estado10;
    private boolean estado11;
    private boolean estado12;
    private boolean estado13;
    private boolean estado14;
    private boolean estado15;
    private boolean estado16;
    private boolean estado17;
    private boolean estado18;
    private boolean estado19;
    private boolean estado20;
    private Timestamp fecha;

    public Intercambios() {
    }

    public Intercambios(int consecutivoIn) {
        this.consecutivo = consecutivoIn;
    }

    public int getConsecutivo() {
        return this.consecutivo;
    }

    public void setConsecutivo(int consecutivoIn) {
        this.consecutivo = consecutivoIn;
    }

    public String getSede_origen() {
        return this.sede_origen;
    }

    public void setSede_origen(String sede_origenIn) {
        this.sede_origen = sede_origenIn;
    }

    public String getProceso() {
        return this.proceso;
    }

    public void setProceso(String procesoIn) {
        this.proceso = procesoIn;
    }

    public String getCod_tabla() {
        return this.cod_tabla;
    }

    public void setCod_tabla(String cod_tablaIn) {
        this.cod_tabla = cod_tablaIn;
    }

    public String getCampo() {
        return this.campo;
    }

    public void setCampo(String campoIn) {
        this.campo = campoIn;
    }

    public String getEvento() {
        return this.evento;
    }

    public void setEvento(String eventoIn) {
        this.evento = eventoIn;
    }

    public String getUsuario() {
        return this.usuario;
    }

    public void setUsuario(String usuarioIn) {
        this.usuario = usuarioIn;
    }

    public String getLlave1() {
        return this.llave1;
    }

    public void setLlave1(String llave1In) {
        this.llave1 = llave1In;
    }

    public String getLlave2() {
        return this.llave2;
    }

    public void setLlave2(String llave2In) {
        this.llave2 = llave2In;
    }

    public String getLlave3() {
        return this.llave3;
    }

    public void setLlave3(String llave3In) {
        this.llave3 = llave3In;
    }

    public String getLlave4() {
        return this.llave4;
    }

    public void setLlave4(String llave4In) {
        this.llave4 = llave4In;
    }

    public String getLlave5() {
        return this.llave5;
    }

    public void setLlave5(String llave5In) {
        this.llave5 = llave5In;
    }

    public boolean getEstado1() {
        return this.estado1;
    }

    public void setEstado1(boolean estado1In) {
        this.estado1 = estado1In;
    }

    public boolean getEstado2() {
        return this.estado2;
    }

    public void setEstado2(boolean estado2In) {
        this.estado2 = estado2In;
    }

    public boolean getEstado3() {
        return this.estado3;
    }

    public void setEstado3(boolean estado3In) {
        this.estado3 = estado3In;
    }

    public boolean getEstado4() {
        return this.estado4;
    }

    public void setEstado4(boolean estado4In) {
        this.estado4 = estado4In;
    }

    public boolean getEstado5() {
        return this.estado5;
    }

    public void setEstado5(boolean estado5In) {
        this.estado5 = estado5In;
    }

    public boolean getEstado6() {
        return this.estado6;
    }

    public void setEstado6(boolean estado6In) {
        this.estado6 = estado6In;
    }

    public boolean getEstado7() {
        return this.estado7;
    }

    public void setEstado7(boolean estado7In) {
        this.estado7 = estado7In;
    }

    public boolean getEstado8() {
        return this.estado8;
    }

    public void setEstado8(boolean estado8In) {
        this.estado8 = estado8In;
    }

    public boolean getEstado9() {
        return this.estado9;
    }

    public void setEstado9(boolean estado9In) {
        this.estado9 = estado9In;
    }

    public boolean getEstado10() {
        return this.estado10;
    }

    public void setEstado10(boolean estado10In) {
        this.estado10 = estado10In;
    }

    public boolean getEstado11() {
        return this.estado11;
    }

    public void setEstado11(boolean estado11In) {
        this.estado11 = estado11In;
    }

    public boolean getEstado12() {
        return this.estado12;
    }

    public void setEstado12(boolean estado12In) {
        this.estado12 = estado12In;
    }

    public boolean getEstado13() {
        return this.estado13;
    }

    public void setEstado13(boolean estado13In) {
        this.estado13 = estado13In;
    }

    public boolean getEstado14() {
        return this.estado14;
    }

    public void setEstado14(boolean estado14In) {
        this.estado14 = estado14In;
    }

    public boolean getEstado15() {
        return this.estado15;
    }

    public void setEstado15(boolean estado15In) {
        this.estado15 = estado15In;
    }

    public boolean getEstado16() {
        return this.estado16;
    }

    public void setEstado16(boolean estado16In) {
        this.estado16 = estado16In;
    }

    public boolean getEstado17() {
        return this.estado17;
    }

    public void setEstado17(boolean estado17In) {
        this.estado17 = estado17In;
    }

    public boolean getEstado18() {
        return this.estado18;
    }

    public void setEstado18(boolean estado18In) {
        this.estado18 = estado18In;
    }

    public boolean getEstado19() {
        return this.estado19;
    }

    public void setEstado19(boolean estado19In) {
        this.estado19 = estado19In;
    }

    public boolean getEstado20() {
        return this.estado20;
    }

    public void setEstado20(boolean estado20In) {
        this.estado20 = estado20In;
    }

    public Timestamp getFecha() {
        return this.fecha;
    }

    public void setFecha(Timestamp fechaIn) {
        this.fecha = fechaIn;
    }

    public void setAll(int consecutivoIn,
            String sede_origenIn,
            String procesoIn,
            String cod_tablaIn,
            String campoIn,
            String eventoIn,
            String usuarioIn,
            String llave1In,
            String llave2In,
            String llave3In,
            String llave4In,
            String llave5In,
            boolean estado1In,
            boolean estado2In,
            boolean estado3In,
            boolean estado4In,
            boolean estado5In,
            boolean estado6In,
            boolean estado7In,
            boolean estado8In,
            boolean estado9In,
            boolean estado10In,
            boolean estado11In,
            boolean estado12In,
            boolean estado13In,
            boolean estado14In,
            boolean estado15In,
            boolean estado16In,
            boolean estado17In,
            boolean estado18In,
            boolean estado19In,
            boolean estado20In,
            Timestamp fechaIn) {
        this.consecutivo = consecutivoIn;
        this.sede_origen = sede_origenIn;
        this.proceso = procesoIn;
        this.cod_tabla = cod_tablaIn;
        this.campo = campoIn;
        this.evento = eventoIn;
        this.usuario = usuarioIn;
        this.llave1 = llave1In;
        this.llave2 = llave2In;
        this.llave3 = llave3In;
        this.llave4 = llave4In;
        this.llave5 = llave5In;
        this.estado1 = estado1In;
        this.estado2 = estado2In;
        this.estado3 = estado3In;
        this.estado4 = estado4In;
        this.estado5 = estado5In;
        this.estado6 = estado6In;
        this.estado7 = estado7In;
        this.estado8 = estado8In;
        this.estado9 = estado9In;
        this.estado10 = estado10In;
        this.estado11 = estado11In;
        this.estado12 = estado12In;
        this.estado13 = estado13In;
        this.estado14 = estado14In;
        this.estado15 = estado15In;
        this.estado16 = estado16In;
        this.estado17 = estado17In;
        this.estado18 = estado18In;
        this.estado19 = estado19In;
        this.estado20 = estado20In;
        this.fecha = fechaIn;
    }

    @Override
    public String toString() {
        return "Intercambios{" + "consecutivo=" + consecutivo + ", sede_origen=" + sede_origen + ", proceso=" + proceso + ", cod_tabla="
                + cod_tabla + ", campo=" + campo + ", evento=" + evento + ", usuario=" + usuario + ", llave1=" + llave1 + ", llave2=" + llave2
                + ", llave3=" + llave3 + ", llave4=" + llave4 + ", llave5=" + llave5 + ", estado1=" + estado1 + ", estado2=" + estado2
                + ", estado3=" + estado3 + ", estado4=" + estado4 + ", estado5=" + estado5 + ", estado6=" + estado6 + ", estado7=" + estado7
                + ", estado8=" + estado8 + ", estado9=" + estado9 + ", estado10=" + estado10 + ", estado11=" + estado11 + ", estado12=" + estado12
                + ", estado13=" + estado13 + ", estado14=" + estado14 + ", estado15=" + estado15 + ", estado16=" + estado16 + ", estado17="
                + estado17 + ", estado18=" + estado18 + ", estado19=" + estado19 + ", estado20=" + estado20 + ", fecha=" + fecha + '}';
    }

}
