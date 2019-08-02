package Model;

/**
 * @author fberrocalm creado en: 27/02/2015
 */
import java.io.*;

public class Homo_exa_imat implements Cloneable, Serializable {

    private String id_servicio;
    private String detalle_servicio;
    private String id_alterna;
    private String id_winsis;
    private String id_compwinsis;

    public Homo_exa_imat() {
    }

    public Homo_exa_imat(String id_servicioIn) {
        this.id_servicio = id_servicioIn;
    }

    public String getId_servicio() {
        return this.id_servicio;
    }

    public void setId_servicio(String id_servicioIn) {
        this.id_servicio = id_servicioIn;
    }

    public String getDetalle_servicio() {
        return this.detalle_servicio;
    }

    public void setDetalle_servicio(String detalle_servicioIn) {
        this.detalle_servicio = detalle_servicioIn;
    }

    public String getId_alterna() {
        return this.id_alterna;
    }

    public void setId_alterna(String id_alternaIn) {
        this.id_alterna = id_alternaIn;
    }

    public String getId_winsis() {
        return this.id_winsis;
    }

    public void setId_winsis(String id_winsisIn) {
        this.id_winsis = id_winsisIn;
    }

    public String getId_compwinsis() {
        return this.id_compwinsis;
    }

    public void setId_compwinsis(String id_compwinsisIn) {
        this.id_compwinsis = id_compwinsisIn;
    }

    public void setAll(String id_servicioIn,
            String detalle_servicioIn,
            String id_alternaIn,
            String id_winsisIn,
            String id_compwinsisIn) {
        this.id_servicio = id_servicioIn;
        this.detalle_servicio = detalle_servicioIn;
        this.id_alterna = id_alternaIn;
        this.id_winsis = id_winsisIn;
        this.id_compwinsis = id_compwinsisIn;
    }

    @Override
    public String toString() {
        return "Homo_exa_imat{" + "id_servicio=" + id_servicio + ", detalle_servicio=" + detalle_servicio + ", id_alterna=" + id_alterna
                + ", id_winsis=" + id_winsis + ", id_compwinsis=" + id_compwinsis + '}';
    }

}
