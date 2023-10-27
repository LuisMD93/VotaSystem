package votasystem.Models;

/**
 *
 * @author Usuario
 */
public class Elecciones {
    
       private String tituloEleccion; 
       private String proposito;
       private String descripcion;
       private int codigoEleccion;
       private String fechaInicio;
       private String fechaFin;
       private String horaInicio;
       private String horafin;
       
       private String fechaLmiteIncripciones;
       
    public Elecciones(){
        
    }   

    public Elecciones(int codigoEleccion) {
        this.codigoEleccion = codigoEleccion;
    }

    
    public Elecciones(String fechaLmiteIncripciones) {
        this.fechaLmiteIncripciones = fechaLmiteIncripciones;
    }

    public Elecciones(String tituloEleccion, String proposito, String descripcion, int codigoEleccion, String fechaInicio, String fechaFin, String horaInicio, String horafin) {
        this.tituloEleccion = tituloEleccion;
        this.proposito = proposito;
        this.descripcion = descripcion;
        this.codigoEleccion = codigoEleccion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.horaInicio = horaInicio;
        this.horafin = horafin;
    }
    
    
    
 

    public Elecciones(String fechaInicio, String fechaFin, String horaInicio, String horafin) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.horaInicio = horaInicio;
        this.horafin = horafin;
    }
    
    

    public String getTituloEleccion() {
        return tituloEleccion;
    }

    public void setTituloEleccion(String tituloElección) {
        this.tituloEleccion = tituloElección;
    }

    public String getProposito() {
        return proposito;
    }

    public void setProposito(String proposito) {
        this.proposito = proposito;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCodigoEleccion() {
        return codigoEleccion;
    }

    public void setCodigoEleccion(int codigoEleccion) {
        this.codigoEleccion = codigoEleccion;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHorafin() {
        return horafin;
    }

    public void setHorafin(String horafin) {
        this.horafin = horafin;
    }

    public String getFechaLmiteIncripciones() {
        return fechaLmiteIncripciones;
    }

    public void setFechaLmiteIncripciones(String fechaLmiteIncripciones) {
        this.fechaLmiteIncripciones = fechaLmiteIncripciones;
    }
    
    

       
       
    
}
