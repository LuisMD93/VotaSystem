
package votasystem.Models;

/**
 *
 * @author Usuario
 */
public class Persona{
    
    
    private String nombres_completos; 
    private String apellidos_completos;
    private String CUI;
    private String sexo; 
    private String fecha_de_nacimiento; 
    private String direccion_residencia; 
    private String departamento_residencia;
    private String municipio_residencia;
    private String pais_esidencia;
        
    
     public Persona(){
         
     }
    
     public Persona(String nombres_completos, String apellidos_completos){
        
         this.nombres_completos = nombres_completos;
         this.apellidos_completos = apellidos_completos;
         
     }

    public Persona(String nombres_completos, String apellidos_completos, String CUI, String sexo, String fecha_de_nacimiento, String direccion_residencia, String departamento_residencia, String municipio_residencia, String pais_esidencia) {
        this.nombres_completos = nombres_completos;
        this.apellidos_completos = apellidos_completos;
        this.CUI = CUI;
        this.sexo = sexo;
        this.fecha_de_nacimiento = fecha_de_nacimiento;
        this.direccion_residencia = direccion_residencia;
        this.departamento_residencia = departamento_residencia;
        this.municipio_residencia = municipio_residencia;
        this.pais_esidencia = pais_esidencia;
    }

    public String getNombres_completos() {
        return nombres_completos;
    }

    public void setNombres_completos(String nombres_completos) {
        this.nombres_completos = nombres_completos;
    }

    public String getApellidos_completos() {
        return apellidos_completos;
    }

    public void setApellidos_completos(String apellidos_completos) {
        this.apellidos_completos = apellidos_completos;
    }

    public String getCUI() {
        return CUI;
    }

    public void setCUI(String CUI) {
        this.CUI = CUI;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getFecha_de_nacimiento() {
        return fecha_de_nacimiento;
    }

    public void setFecha_de_nacimiento(String fecha_de_nacimiento) {
        this.fecha_de_nacimiento = fecha_de_nacimiento;
    }

    public String getDireccion_residencia() {
        return direccion_residencia;
    }

    public void setDireccion_residencia(String direccion_residencia) {
        this.direccion_residencia = direccion_residencia;
    }

    public String getDepartamento_residencia() {
        return departamento_residencia;
    }

    public void setDepartamento_residencia(String departamento_residencia) {
        this.departamento_residencia = departamento_residencia;
    }

    public String getMunicipio_residencia() {
        return municipio_residencia;
    }

    public void setMunicipio_residencia(String municipio_residencia) {
        this.municipio_residencia = municipio_residencia;
    }

    public String getPais_esidencia() {
        return pais_esidencia;
    }

    public void setPais_esidencia(String pais_esidencia) {
        this.pais_esidencia = pais_esidencia;
    }

        

   
   
    
    
    
    
    
}
